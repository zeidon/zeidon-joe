/**
    This file is part of the Zeidon Java Object Engine (Zeidon JOE).

    Zeidon JOE is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Zeidon JOE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2009-2015 QuinSoft
 */
package com.quinsoft.zeidon.zeidonoperations;

/**
 * ldapfastbind.java
 * 
 * Sample JNDI application to use Active Directory LDAP_SERVER_FAST_BIND connection control
 * 
 * got this data from
 * http://jeftek.com/222/using-java-code-with-active-directory/
 * also saw this...
 * http://stackoverflow.com/questions/11493742/fastbind-for-authentication-against-active-directory-using-spring-ldap
 */
 
//https://forums.oracle.com/forums/thread.jspa?threadID=1155584&tstart=0

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import javax.naming.*;
import javax.naming.ldap.*;
import javax.naming.directory.*;

class FastBindConnectionControl implements Control 
{
	public byte[] getEncodedValue() {
        	return null;
	}
  	public String getID() {
		return "1.2.840.113556.1.4.1781";
	}
 	public boolean isCritical() {
		return true;
	}
}

 
public class ldapFastBind 
{
	public Hashtable<String, String> env = null;
	public LdapContext ctx = null;
	public Control[] connCtls = null;
 
	public ldapFastBind(String ldapurl) 
	{
		env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION,"simple");
		env.put(Context.PROVIDER_URL,ldapurl);
 
		connCtls = new Control[] {new FastBindConnectionControl()};
 
		//first time we initialize the context, no credentials are supplied
		//therefore it is an anonymous bind.		
 
		try 
		{
			ctx = new InitialLdapContext(env,connCtls);
		}
		catch (NamingException e) {
			System.out.println("Naming exception " + e);
		}
	}
	public boolean Authenticate(String username, String password) {
		try {
			ctx.addToEnvironment(Context.SECURITY_PRINCIPAL,username);
			ctx.addToEnvironment(Context.SECURITY_CREDENTIALS,password);
			ctx.reconnect(connCtls);
			//ctx.modifyAttributes(name, mods);
			System.out.println(username + " is authenticated");
			return true;
		}
 
		catch (AuthenticationException e) {
			System.out.println(username + " is not authenticated");
			return false;
		}
		catch (NamingException e) {
			System.out.println(username + " is not authenticated");
			return false;
		}
	}
	public void finito() {
		try {
			ctx.close();
			System.out.println("Context is closed");
		}
		catch (NamingException e) {
			System.out.println("Context close failure " + e);
		}
	}
	public void changePassword(String username, String password) 
	{
		// here is a sample from the web:
		// How To Change a Windows 2008 User's Password Through LDAP
		//https://forums.oracle.com/forums/thread.jspa?threadID=1155445
		//http://serverfault.com/questions/423345/changing-active-directory-password-over-ldap-using-passwd-ldappasswd-samba
		//http://msdn.microsoft.com/en-us/library/cc223248.aspx
        ModificationItem[] mods = new ModificationItem[1];
        String newQuotedPassword = "\"" + password + "\"";
        byte[] newUnicodePassword = newQuotedPassword.getBytes();
        try {
                newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");
        } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
        }
        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("unicodePwd", newUnicodePassword));
        try {

            ctx.modifyAttributes(username, mods);
            //ldapContext.modifyAttributes(username, mods);
        } catch (NamingException e) {
                System.out.println("Error changing password for '" + username + "': " + e.getMessage());
                e.printStackTrace();
        }                       
}
}
