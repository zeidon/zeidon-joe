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

package com.quinsoft.opencuas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import org.joda.time.DateTime;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.BaseDomainContext;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.domains.DomainContext;
import com.quinsoft.zeidon.domains.DynamicTableDomain;
import com.quinsoft.zeidon.domains.TableDomainContext;
import com.quinsoft.zeidon.domains.TableEntry;
import com.quinsoft.zeidon.domains.TableListContext;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.utils.QualificationBuilder;

/**
 * This domain 
 * @author DG
 *
 */
public class AdminDivisionDynamicTableDomain extends DynamicTableDomain
{
    public AdminDivisionDynamicTableDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }
    
/******** KELLY ********/
    protected View activateApplicationDomain( Task task, DomainContext context )
    {
        LodDef lodDef = task.getApplication().getLodDef( task, "DomainT" );
        QualificationBuilder qual = new QualificationBuilder( task.getSystemTask() )
                                            .setApplication( task.getApplication() )
                                            .setLodDef( lodDef )
                                            .forEntity( "Domain" )
                                            .addAttribQual( "Name", context.getName() );
        View view = task.activateObjectInstance( lodDef, qual.getView(), ActivateFlags.MULTIPLE );
        if ( view.cursor( "Domain" ).getEntityCount() == 0 )
            throw new ZeidonException( "Dynamic domain '%s' has no values in the DB", this.toString() );
        
        return view;
    }
    
    protected View loadApplicationDomainView( Task task, DomainContext context, String viewName )
    {
        Application app = getApplication();
        ReentrantReadWriteLock appLock = app.getNamedLock( viewName ).getLock();
        WriteLock lock = appLock.writeLock();
        try 
        {
            lock.lock();

            View domainView = app.getViewByName( viewName );
            if ( domainView != null )
                return domainView;
            
            domainView = activateApplicationDomain( task, context );
            task.setNameForView(viewName, domainView);
            return domainView;
        }
        finally
        {
            lock.unlock();
        }
    }

/******** KELLY *******/
    @Override
    public DomainContext newContext(Task task)
    {
        return new AdminContext( this );
    }

    private void setDomainCursor( View domainView )
    {
    	Integer id = 0;
        EntityCursor domainCsr = domainView.cursor( "Domain" );
        
        if ( domainCsr.getEntityCount() > 1 )
        {
            View mUser = domainView.getViewByName( "mUser" );
            // If mUser doesn't exist, then use Main Campus (id = 1)
            if ( mUser == null )
            	id = 1;
            else
            {
	            // If CurrentAdministrativeDivision doesn't exist, then use Main Campus (id = 1)
	            if ( mUser.cursor("CurrentAdministrativeDivision").checkExistenceOfEntity() == CursorResult.SET)
	            	id = mUser.cursor( "CurrentAdministrativeDivision" ).getIntegerFromAttribute( "ID" );
	            else
	            	if ( mUser.cursor("PreferedAdministrativeDivision").checkExistenceOfEntity() == CursorResult.SET)
		            	id = mUser.cursor( "PreferedAdministrativeDivision" ).getIntegerFromAttribute( "ID" );
	            if ( id.equals(0))
	            	id = 1;
           }
            
            // Drop admin div domain for the admin div that is not being used.
            for ( @SuppressWarnings("unused") EntityInstance domain : domainView.cursor( "Domain" ).eachEntity() )
            {
            	if ( ! domainView.cursor("AdministrativeDivision").getIntegerFromAttribute("ID").equals(id) )
            		domainView.cursor("Domain").dropEntity( CursorPosition.NONE );        		
            }
            domainView.cursor("Domain").setFirst();
        }
    }
    
    @Override
    protected void loadTableEntries( Task task, TableDomainContext context, View domainView )
    {
        synchronized ( task )
        {
            setDomainCursor( domainView );
        }
        
        super.loadTableEntries( task, context, domainView );
    }
    
    private String getKey( DomainContext context )
    {
        return this.getName() + "/" + context.getName();
    }
    
    /**
     * Contexts are saved in the task cache.
     * 
     * @param task
     * @param context
     * @return
     */
    @SuppressWarnings("unchecked") // For cast of getTaskCache to map
    private TableListContext getTaskContext( Task task, TableDomainContext context )
    {
        TaskData taskData = task.getCacheMap( TaskData.class );
        if ( taskData == null )
        {
            taskData = task.putCacheMap( TaskData.class, new TaskData() );
        }
            
        synchronized ( taskData )
        {
            String key = getKey( context );
            TableListContext taskContext = taskData.map.get( key );
            if ( taskContext == null )
            {
                taskContext = new TableListContext( this, task );
                taskData.map.put( key, taskContext );
            }
            
            return taskContext;
        }
    }

    /**
     * Simple class for storing data in Task CacheMap
     */
    private class TaskData
    {
        private final Map<String,TableListContext> map = new HashMap<String, TableListContext>();
    }
    
    /**
     * Contexts for this domain are stored at the task level.  Each method forwards the call
     * to the task context.
     * 
     * @author DG
     *
     */
    private class AdminContext extends BaseDomainContext implements TableDomainContext
    {
        public AdminContext(Domain domain)
        {
            super( domain );
        }

        @Override
        public void addTableEntry(Task task, TableEntry tableEntry)
        {
            getTaskContext( task, this ).addTableEntry( task, tableEntry );
        }

        @Override
        public void addTableEntry(Task task, String internalValue, String externalValue)
        {
            getTaskContext( task, this ).addTableEntry( task, internalValue, externalValue );
        }

        @Override
        public List<TableEntry> getTableEntries(Task task)
        {
            return getTaskContext( task, this ).getTableEntries( task );
        }

        @Override
        public void resetTableEntries(Task task)
        {
            getTaskContext( task, this ).resetTableEntries( task );
        }

        @Override
        public Object convertExternalValue(Task task, AttributeDef attributeDef, Object value)
                throws InvalidAttributeValueException
        {
            return getTaskContext( task, this ).convertExternalValue( task, attributeDef, value );
        }

        @Override
        public Blob convertToBlob(Task task, AttributeDef attributeDef, Object internalValue)
        {
            return getTaskContext( task, this ).convertToBlob( task, attributeDef, internalValue );
        }

        @Override
        public DateTime convertToDate(Task task, AttributeDef attributeDef, Object internalValue)
        {
            return getTaskContext( task, this ).convertToDate( task, attributeDef, internalValue );
        }

        @Override
        public Double convertToDouble(Task task, AttributeDef attributeDef, Object internalValue)
        {
            return getTaskContext( task, this ).convertToDouble( task, attributeDef, internalValue );
        }

        @Override
        public Integer convertToInteger(Task task, AttributeDef attributeDef, Object internalValue)
        {
            return getTaskContext( task, this ).convertToInteger( task, attributeDef, internalValue );
        }

        @Override
        public String convertToString(Task task, AttributeDef attributeDef, Object internalValue)
        {
            return getTaskContext( task, this ).convertToString( task, attributeDef, internalValue );
        }

        @Override
        public void validateInternalValue(Task task, AttributeDef attributeDef, Object value)
                throws InvalidAttributeValueException
        {
            getTaskContext( task, this ).validateInternalValue( task, attributeDef, value );
        }

        @Override
        public TableEntry getTableEntryByInternalValue(Task task, String internalValue)
        {
            return getTaskContext( task, this ).getTableEntryByInternalValue( task, internalValue );
        }
        
        @Override
        public int compare(Task task, Object o1, Object o2)
        {
            return getTaskContext( task, this ).compare( task, o1, o2 );
        }
    }
}
