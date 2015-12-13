#Accessing LDAP from Java code

### Steps to run the project.

1. Install Apache Directory server.

2. Install Apache Directory Studio.

3. Create LDAP server instance from Studio [https://directory.apache.org/apacheds/basic-user-guide.html]
    * Use Studio to create the instance.
    * Create partition in the LDAP server as mentioned in the document [https://directory.apache.org/apacheds/basic-ug/1.4.3-adding-partition.html]
    * Import the .ldif file from /doc/captain_hook.ldif *ldif - light weight directory access interchange format*
4. Now you should see the added entries in server from Studio.
5. Run LDAPUserAuthentication.class.     




