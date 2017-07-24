import java.lang.System;
import java.io.PrintWriter;
import java.security.Security;
import javax.net.ssl.*;


public class SSLCapabilities{

    public static void main(String[] args) throws Exception {
        PrintWriter writer = new PrintWriter("ldap.log", "UTF-8");
        
        writer.format("======== %s ========\n\n", "System Properties");
        System.getProperties().list(writer); 
        
        try{
            writer.format("\n\n======== %s ========\n\n", "SSL Capabilities");
            GetSSLCapabilities(writer);
        }
        finally{
            writer.close();
        }
    }
    
    public static void GetSSLCapabilities(PrintWriter writer) throws Exception{
      
        SSLSocketFactory factory = (SSLSocketFactory)SSLContext.getDefault().getSocketFactory();
        SSLSocket socket = (SSLSocket)factory.createSocket();
        String[] supported = socket.getSupportedProtocols();
        String[] enabled = socket.getEnabledProtocols();
        
        writer.println("Supported Protocols: " + supported.length);
        for (int i = 0; i < supported.length; i++){
            writer.println("  " + supported[i]);
        }
        
        writer.println("\nEnabled protocols of the: Default");
        for (int i = 0; i < enabled.length; i++){
            writer.println("  " + enabled[i]);
        }
        
        String vendor = System.getProperty("java.vendor").toLowerCase();
        String[] vendor_protocols = null;
        if (vendor.contains("oracle")){                    
            vendor_protocols = supported;  
        }
        else if (vendor.contains("ibm")){
            vendor_protocols = ibm_protocols;
        }
        else{
            vendor_protocols = supported;  
        }
        
        for (int i = 0; i < vendor_protocols.length; i++){
            writer.println("Enabled protocols of the: " + vendor_protocols[i]);
            try{
                SSLContext context = SSLContext.getInstance(vendor_protocols[i]);
                context.init(null, null, null);
                factory = (SSLSocketFactory)context.getSocketFactory();
            }
            catch(Exception e){
                writer.format("  Don't support: %s\n", e.toString());
                continue;
            }
            socket = (SSLSocket)factory.createSocket();
            enabled = socket.getEnabledProtocols();
            
            for (int j = 0; j < enabled.length; j++){
                writer.println("  " + enabled[j]);
            }            
        }
        
    }
    
    private static final String[] ibm_protocols = {"SSL", "SSLv3", "TLS", "TLSv1", "TLSv1.1", "TLSv1.2", "SSL_TLS", "SSL_TLSv2"};
    
}    


