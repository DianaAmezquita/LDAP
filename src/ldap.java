import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.*;

public class ldap
{
    public static void main(String[]args)
    {
    	// Configurar el entorno para crear el contexto inicial
        Hashtable<String, String> environment = new Hashtable<String, String>();

        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, "ldap://<hostname>:389");
        
         // Autenticar como S. Usuario y contraseña "mysecret"
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
        environment.put(Context.SECURITY_PRINCIPAL, "<Login DN>");
        // "uid=admin,ou=system"    "cn = S. Usuario, ou = NewHires, o = JNDITutorial"
        environment.put(Context.SECURITY_CREDENTIALS, "<password>");
        //"secret"

        try 
        {
        	// Crea el contexto inicial
            DirContext context = new InitialDirContext(environment);
            System.out.println("Connected..");
            System.out.println(context.getEnvironment());
            context.close();
        } 
        catch (AuthenticationNotSupportedException exception) 
        {
            System.out.println("The authentication is not supported by the server");
        }

        catch (AuthenticationException exception)
        {
            System.out.println("Incorrect password or username");
        }

        catch (NamingException exception)
        {
            System.out.println("Error when trying to create the context");
        }
    }
}
