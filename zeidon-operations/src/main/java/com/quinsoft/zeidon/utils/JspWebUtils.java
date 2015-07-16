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

package com.quinsoft.zeidon.utils;

import java.util.List;
import javax.servlet.http.HttpSession;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.domains.TableDomain;
import com.quinsoft.zeidon.domains.TableEntry;
import com.quinsoft.zeidon.objectdefinition.DomainType;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import org.apache.commons.lang3.StringUtils;

/**
 * @author DG
 *
 */
public class JspWebUtils
{
   public static final String WEB_SESSION_VIEW_NAME = "_KZXMLPGO";

   public static View createWebSession( Object s, TaskQualification qual, String userId )
   {
      View view = null;

      if ( qual != null )
      {
         Task task = qual.getTask( );
         view = task.getViewByName( WEB_SESSION_VIEW_NAME );
         if ( view == null )
         {
            view = task.activateEmptyObjectInstance( "KZXMLPGO", ObjectEngine.ZEIDON_SYSTEM_APP_NAME );
            view.setName( WEB_SESSION_VIEW_NAME );
            if ( ! view.cursor( "Session" ).setFirst( "TaskID", task.toString() ).isSet() )
            {
               view.cursor( "Session" ).createEntity().getAttribute( "TaskID").setValue( task.toString() ) 
                                       .getAttribute( "UserID").setValue( userId ) ; // initialize UserID (even if blank)
            }
         }
      }

      if ( StringUtils.isBlank( userId ) == false ) // always reset the UserID (if not blank)
      {
         if ( view != null )
            view.cursor( "Session" ).getAttribute( "UserID").setValue( userId ) ;
      }

      return view;
   }

   public static String getWebSessionUserId( Object s, TaskQualification qual )
   {
      HttpSession session = (HttpSession) s;
      String userId = "";

      if ( session != null ) // try to get the userId from the session if possible
         userId = (String) session.getAttribute( "UserId" );
      else
      if ( qual != null )
      {
         Task task = qual.getTask();
         View view = task.getViewByName( WEB_SESSION_VIEW_NAME );
         if ( view != null && view.cursor( "Session" ).setFirst( "TaskID", task ).isSet() )
            userId = view.cursor( "Session" ).getAttribute( "UserID" ).getString();
      }

      return userId;
   }

    public static List<TableEntry> getTableDomainValues( View view, String entityName, String attribName, String contextName )
    {
    	Task task = view.getTask();
    	LodDef lodDef = view.getLodDef();
    	EntityDef entityDef = lodDef.getEntityDef(entityName);
    	AttributeDef attributeDef = entityDef.getAttribute(attribName);
    	Domain domain = attributeDef.getDomain();
    	if ( domain.getDomainType() != DomainType.TABLE )
    		throw new ZeidonException( "Domain %s is not a Table Domain for attribute %s ", 
    									domain.getName(), attributeDef.toString() );
    	

    	TableDomain tableDomain = (TableDomain) domain;
    	return tableDomain.getTableEntries(task, contextName);
    }
    
    public void TestKellysJspCode()
    {
    	List<TableEntry> list = getTableDomainValues(null, "entityName", "attribName", "contextName");
    	for ( TableEntry entry : list )
    	{
    		String externalValue = entry.getExternalValue();
    		String internalValue = entry.getInternalValue();
    	}
    }
}
