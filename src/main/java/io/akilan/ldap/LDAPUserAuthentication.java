package io.akilan.ldap;

import java.util.Hashtable;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;


/**
 * Created by akilan on 13/12/15.
 */
public class LDAPUserAuthentication {

    public static void main(String s[]) {
        try {

            String url = "ldap://localhost:10389/o=sevenSeas";
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, url);

            LdapContext ctx = new InitialLdapContext(env, null);
            ctx.setRequestControls(null);

            String verifyUserName = "James Hook";
            String verifyUserPassword = "peterPan";

            NamingEnumeration<?> namingEnum = ctx.search("ou=people", "(cn=" + verifyUserName + ")", getSimpleSearchControls());
            while (namingEnum.hasMore()) {
                SearchResult result = (SearchResult) namingEnum.next();
                Attributes attrs = result.getAttributes();
                System.out.println(attrs.get("cn"));
                String nameInNamespace = result.getNameInNamespace();
                System.out.println(nameInNamespace);
                System.out.println(attrs.get("description"));
                System.out.println(attrs.get("userPassword"));
                verifyPassword(url, nameInNamespace, verifyUserPassword);
            }
            namingEnum.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void verifyPassword(String url, String nameInNamespace, String verifyUserPassword) throws NamingException {
        Properties env1 = new Properties();
        env1.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env1.put(Context.PROVIDER_URL, url);
        env1.put(Context.SECURITY_PRINCIPAL, nameInNamespace);
        env1.put(Context.SECURITY_CREDENTIALS, verifyUserPassword);

        new InitialDirContext(env1);
    }

    private static SearchControls getSimpleSearchControls() {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchControls.setTimeLimit(30000);
        //String[] attrIDs = {"objectGUID"};
        //searchControls.setReturningAttributes(attrIDs);
        return searchControls;
    }
}
