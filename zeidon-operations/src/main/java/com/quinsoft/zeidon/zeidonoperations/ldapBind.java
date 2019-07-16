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
 * ldapbind.java
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

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.Control;

import com.quinsoft.zeidon.ZeidonException;


public class ldapBind
{
	public Hashtable<String, String> env = null;
	//public LdapContext ctx = null;
	public DirContext ctx = null;
	public Control[] connCtls = null;

	public ldapBind(String ldapurl)
	{
		env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION,"simple");
		env.put(Context.PROVIDER_URL,ldapurl);

	}
	public boolean Authenticate(String username, String password) {
			//ctx.addToEnvironment(Context.SECURITY_PRINCIPAL,username);
			//ctx.addToEnvironment(Context.SECURITY_CREDENTIALS,password);
			env.put(Context.SECURITY_PRINCIPAL,username);
			env.put(Context.SECURITY_CREDENTIALS,password);
			try
			{
				//ctx = new InitialLdapContext(env,connCtls);
				ctx = new InitialDirContext(env);
			}
			catch (NamingException e) {
				return false;
			}
			//ctx.reconnect(connCtls);
			//ctx.close();
			//ctx.modifyAttributes(name, mods);
			return true;

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
        ModificationItem[] mods = new ModificationItem[1];
        String newQuotedPassword = "\"" + password + "\"";
        byte[] newUnicodePassword = newQuotedPassword.getBytes();
        try {
                newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");
        } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw ZeidonException.wrapException( e );
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
