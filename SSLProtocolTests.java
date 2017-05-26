import javax.net.ssl.*;

//test for git

public class SSLProtocolTests {
    public static void main(String[] args) throws Exception {
        Properties p = System.getProperties();
        p.list(System.out);
        
        System.out.println("\nPrint SSL info of the default :");
        printSSL(SSLContext.getDefault());
        
        System.out.println("\nPrint SSL info of the TLSv1.2 :");
        SSLContext context2 = SSLContext.getInstance("TLSv1.2");
        context2.init(null, null, null);
        printSSL(context2);

    }
    
    public static void printSSL(SSLContext context) throws Exception {
        SSLSocketFactory factory = (SSLSocketFactory)context.getSocketFactory();
        SSLSocket socket = (SSLSocket)factory.createSocket();

        String[] protocols = socket.getSupportedProtocols();

        System.out.println("Supported Protocols: " + protocols.length);
        for(int i = 0; i < protocols.length; i++)
        {
            System.out.println(" " + protocols[i]);
        }

        protocols = socket.getEnabledProtocols();

        System.out.println("Enabled Protocols: " + protocols.length);
        for(int i = 0; i < protocols.length; i++)
        {
            System.out.println(" " + protocols[i]);
        }


        String[] ciphers = socket.getSupportedCipherSuites();
        System.out.println("Enabled Ciphers: " + ciphers.length);
        for(int i = 0; i < ciphers.length; i++)
        {
            System.out.println(" " + ciphers[i]);
        }
    }
}
