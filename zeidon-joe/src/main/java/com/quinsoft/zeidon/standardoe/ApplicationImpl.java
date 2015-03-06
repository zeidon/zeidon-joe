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
/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Lockable;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.UnknownLodDefException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;

/**
 * @author DG
 *
 */
class ApplicationImpl implements Application, PortableFileAttributeHandler
{
    /**
     * List of attribute values loaded from zeidon.app.  They are stored in a hash
     * map to make it easier to load them.
     */
    private final Map<String, String> attributes = new LinkedHashMap<String,String>();
    private final Map<String, LodDef> lodDefList = new HashMap<String, LodDef>();
    private final String              zeidonRootDir;
    private final ViewNameList        viewNameList = new ViewNameList();
    private final NamedLockableList   lockList = new NamedLockableList();
    private       DomainList          domainList;

    /**
     * Create an application and get the name from zeidon.app.
     *
     * @param zeidonRootDir
     */
    ApplicationImpl( String zeidonRootDir )
    {
        this.zeidonRootDir = zeidonRootDir;
    }

    @Override
    public String getName()
    {
        return attributes.get( "APP_NAME" );
    }

    @Override
    public String getObjectDir()
    {
        return attributes.get( "APP_ADOBIN" );
    }

    @Override
    public synchronized LodDef getLodDef(TaskQualification taskQual, String name) throws UnknownLodDefException
    {
        String lowerName = name.toLowerCase();
        if ( lodDefList.containsKey( lowerName ))
            return lodDefList.get( lowerName );

        LodDef lodDef = new LodDef( taskQual.getTask(), this, name );
        lodDefList.put( lowerName, lodDef );

        return lodDef;
    }

    @Override
    public void setAttribute(PortableFileReader reader )
    {
        String attName = reader.getAttributeName();

        // If the attr name begins with ZEIDON then it's part of the system app and we can
        // ignore those attributes.
        if ( attName.startsWith( "ZEIDON" ) )
            return;

        String value = reader.getAttributeValue();
        if ( value.startsWith( "." ) )
            value = zeidonRootDir + value.substring( 1 );

        attributes.put( attName, value );
    }

    @Override
    public void dropNameForView(String name, View view)
    {
        viewNameList.dropNameForView( name, view );
    }

    @Override
    public View getViewByName(String name)
    {
        return viewNameList.getViewByName( name );
    }

    @Override
    public void setNameForView(String name, View view)
    {
        // The task for the OI referenced by this view should be the system task.  This is
        // necessary because the view is expected to have a life-time longer than the current task
        // and if it isn't changed then it will hold on to a dead task and the GC can't clean up.
    	// If the task is not the system task, then throw an exception.
        if ( view.getTask() != view.getTask().getSystemTask() ) // Already system task?
        {
            throw new ZeidonException("The Application view '%s' should be assigned to the system task but is not.",name);
        }

        viewNameList.setNameForView( name, view );
    }

    @Override
    public Collection<String> getAllViewNames(View view)
    {
        return viewNameList.getAllViewNames( view );
    }

    @Override
    public String toString()
    {
        return getName();
    }

    ViewNameList getViewNameList()
    {
        return viewNameList;
    }

    synchronized void loadDomains( Task task )
    {
        if ( domainList != null )
            return;  // Already loaded the domains.

        domainList = new DomainList( this );
        domainList.loadDomains( task );
   }

	@Override
	public Domain getDomain(String name)
	{
		return domainList.getDomain(name);
	}

    @Override
    public Lockable getNamedLock(String name)
    {
        return lockList.getNamedLock( name );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#getPackage()
     */
    @Override
    public String getPackage()
    {
        return attributes.get( "APP_PACKAGE" );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#nullStringEqualsEmptyString()
     */
    @Override
    public boolean nullStringEqualsEmptyString()
    {
        return true;  //TODO: Shouldn't this be read from config?
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#getAllNamedViews()
     */
    @Override
    public Collection<? extends View> getAllNamedViews()
    {
        return getViewNameList().getAllNamedViews();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#dropView(com.quinsoft.zeidon.View)
     */
    @Override
    public void dropView( View view )
    {
        getViewNameList().dropView( view );
    }

    @Override
    public boolean isSystemApp()
    {
        return false;
    }
}
