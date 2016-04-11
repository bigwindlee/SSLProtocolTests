import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;

public class JndiTest {

    public static void main(String[] args) throws Exception {	
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS");
        try
        {
            Hashtable env = new Hashtable();

            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            //env.put(Context.PROVIDER_URL, "ldap://10.240.124.187:3898");     // correct one
            env.put(Context.PROVIDER_URL, "ldap://10.241.124.187:3898");       // incorrect one to test timeout or connection refused
            env.put(Context.SECURITY_PRINCIPAL, "cn=Admin,o=Administration");
            env.put(Context.SECURITY_CREDENTIALS, "Admin");
            env.put("com.sun.jndi.ldap.connect.timeout", "10000");             // don't take effect if it is greater than the tcp timeout of the platform
            
            System.out.println(formatter.format(new Date()) + " | " + "Create Context");
            InitialLdapContext context = new InitialLdapContext(env, null);
            System.out.println(formatter.format(new Date()) + " | " + "Success");

        } catch (NamingException e)
        {
            System.out.println(formatter.format(new Date()) + " | " + e.getRootCause().getMessage());
        }
    }

}