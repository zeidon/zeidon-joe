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

    Copyright 2009-2014 QuinSoft
 */

package com.quinsoft.zeidon.domains;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.utils.QualificationBuilder;

/**
 * Implementation of standard dynamic table domains.
 * 
 * This class is designed for user application to subclass so they can add/change
 * functionality to their own needs.
 * 
 * @author DG
 *
 */
public class DynamicTableDomain extends AbstractTableDomain
{
    public DynamicTableDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }

    protected String getDomainViewName( DomainContext context )
    {
        return "_DM_" + getName() + "_" + context.getName();
    }
    
    protected View activateApplicationDomain( Task task, DomainContext context )
    {
        LodDef lodDef = task.getApplication().getLodDef( task, "DomainT" );
        View view = new QualificationBuilder( task.getSystemTask() )
                                            .setApplication( task.getApplication() )
                                            .setLodDef( lodDef )
                                            .forEntity( "Domain" )
                                            .addAttribQual( "Name", context.getName() )
                                            .multipleRoots()
                                            .activate();
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
            app.setNameForView( viewName, domainView );
            return domainView;
        }
        finally
        {
            lock.unlock();
        }
    }
    
    protected void loadTableEntries( Task task, TableDomainContext context, View domainView )
    {
        context.resetTableEntries(task);
        for ( EntityInstance entity : domainView.cursor( "Domain" ).getChildren( "DomainValue" ) )
        {
            String internalValue = entity.getStringFromAttribute( "InternalStringValue" );
            if ( StringUtils.isBlank( internalValue ) )
                internalValue = entity.getStringFromAttribute( "InternalIntegerValue" );
            String externalValue = entity.getStringFromAttribute( "ExternalDescription" );
            context.addTableEntry( task, internalValue, externalValue );
        }
    }
    
    protected synchronized View loadDomainView( Task task, DomainContext context )
    {
        View domainView = null;
        String viewName = getDomainViewName( context );
        domainView = task.getViewByName( viewName );
        if ( domainView == null )
        {
            View appView = loadApplicationDomainView( task, context, viewName );
            domainView = appView.newView( task );
            domainView.setName( viewName );
            loadTableEntries( task, (TableDomainContext) context, domainView );
        }
        
        return domainView;
    }

    //
    // Override AbstractTableDomain methods to load the domain view first.
    //
    
    @Override
    public List<TableEntry> getTableEntries(Task task, String contextName)
    {
        loadDomainView( task, getContext( task, contextName ) );
        return super.getTableEntries( task, contextName );
    }

    @Override
    public Object convertExternalValue(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, String contextName, Object externalValue)
            throws InvalidAttributeValueException
    {
        // If external value is an AttributeInstance then get *its* internal value.
        if ( externalValue instanceof AttributeInstance )
            externalValue = ((AttributeInstance) externalValue).getValue();

        loadDomainView( task, getContext( task, contextName ) );
        return super.convertExternalValue( task, attributeInstance, attributeDef, contextName, externalValue );
    }

    @Override
    public Blob convertToBlob(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        loadDomainView( task, getContext( task, contextName ) );
        return super.convertToBlob( task, attributeDef, internalValue, contextName );
    }

    @Override
    public DateTime convertToDate(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        loadDomainView( task, getContext( task, contextName ) );
        return super.convertToDate( task, attributeDef, internalValue, contextName );
    }

    @Override
    public Double convertToDouble(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        loadDomainView( task, getContext( task, contextName ) );
        return super.convertToDouble( task, attributeDef, internalValue, contextName );
    }

    @Override
    public Integer convertToInteger(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        loadDomainView( task, getContext( task, contextName ) );
        return super.convertToInteger( task, attributeDef, internalValue, contextName );
    }

    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        loadDomainView( task, getContext( task, contextName ) );
        return super.convertToString( task, attributeDef, internalValue, contextName );
    }

    @Override
    public void validateInternalValue(Task task, AttributeDef attributeDef, Object internalValue)
            throws InvalidAttributeValueException
    {
        loadDomainView( task, getContext( task, null ) );
        super.validateInternalValue( task, attributeDef, internalValue );
    }
}
