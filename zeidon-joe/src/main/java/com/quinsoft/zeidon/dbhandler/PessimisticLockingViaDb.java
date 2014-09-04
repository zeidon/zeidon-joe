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

package com.quinsoft.zeidon.dbhandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.PessimisticLockingException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.UnknownViewOdException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;
import com.quinsoft.zeidon.objectdefinition.ViewOd;
import com.quinsoft.zeidon.standardoe.IncrementalEntityFlags;

/**
 * Handles pessimistic View locking by writing records to the DB to lock OIs.
 * Assumes that ZPLOCKO LOD exists in the application.
 *
 * @author DG
 *
 */
public class PessimisticLockingViaDb implements PessimisticLockingHandler
{
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.PessimisticLockingHandler#acquireLocks(releaseLock(com.quinsoft.zeidon.View)
     */
    @Override
    public void acquireLocks( View view ) throws PessimisticLockingException
    {
        Task task = view.getTask();
        ViewOd viewOd = view.getViewOd();

        Application application = view.getApplication();

        // See if the locking view exists.
        try
        {
            application.getViewOd( view, "ZPLOCKO" );
        }
        catch ( UnknownViewOdException e )
        {
            throw new ZeidonException( "LOD for pessimistic locking (ZPLOCKO) does not exist in the application.  " +
            		                   "To create one use the Utilities menu in the ER diagram tool." )
                                       .setCause( e );
        }

        View vlock = task.activateEmptyObjectInstance( "ZPLOCKO", application );
        ViewEntity root = viewOd.getRoot();

        // For each root entity, create a locking record in ZPLOCKO
        for ( EntityInstance ei : view.cursor( root ).eachEntity() )
        {
            String user = task.getUserId();
            if ( StringUtils.isBlank( user ) )
                user = "none";

            vlock.cursor( "ZeidonLock" ).createEntity()
                                        .setAttribute( "LOD_Name", viewOd.getName() )
                                        .setAttribute( "KeyValue", ei.getKeyString() )
                                        .setAttribute( "UserName", user )
                                        .setAttribute( "Timestamp", new Date() )
                                        .setAttribute( "AllowRead", "Y" );
        }

        try
        {
            vlock.commit();
        }
        catch ( Exception e )
        {
            vlock.logObjectInstance();
            throw new PessimisticLockingException( view, "Error creating pessimistic locking semaphore" )
                            .setCause( e );
        }
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.PessimisticLockingHandler#releaseLocks(java.util.Collection)
     */
    @Override
    public void releaseLocks( Collection<View> views )
    {
        if ( views.size() == 0 )
            return; // Nothing to do.

        Task task = null;
        Application application = null;
        View vlock = null;
        for ( View view : views )
        {
            // Get the task from the first view.
            if ( task == null )
            {
                task = view.getTask();
                application = view.getApplication();
                vlock = task.activateEmptyObjectInstance( "ZPLOCKO", application );
            }
            else
            {
                // TODO: Is this an error?  Any reason we can't handle unlocking of
                // multiple tasks at once?
                if ( task != view.getTask() )
                    throw new PessimisticLockingException( views, "Views are from different tasks" );

                // TODO: Someday we should handle multiple applications.
                if ( application != view.getApplication() )
                    throw new PessimisticLockingException( views, "Views are from different applications" );
            }

            ViewOd     viewOd = view.getViewOd();
            ViewEntity root   = viewOd.getRoot();

            // For each root entity, create a locking record in ZPLOCKO.  Normally we'd activate the locking
            // records, delete them from the OI, and then commit it.  Instead we will set the incremental
            // flags for each entity to DELETE.  This will save us the time of doing the activate.
            for ( EntityInstance ei : view.cursor( root ).eachEntity() )
            {
                vlock.cursor( "ZeidonLock" ).createEntity()
                                            .setAttribute( "LOD_Name", viewOd.getName() )
                                            .setAttribute( "KeyValue", ei.getKeyString() )
                                            .setIncrementalFlags( IncrementalEntityFlags.DELETED );
            }
        }

        try
        {
            vlock.commit();
        }
        catch ( Exception e )
        {
            vlock.logObjectInstance();
            throw new PessimisticLockingException( views, "Error creating pessimistic locking semaphore" )
                            .setCause( e );
        }
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.PessimisticLockingHandler#releaseLock(com.quinsoft.zeidon.View)
     */
    @Override
    public void releaseLocks( View view )
    {
        ArrayList<View> list = new ArrayList<View>();
        list.add( view );
        releaseLocks( list );
    }
}
