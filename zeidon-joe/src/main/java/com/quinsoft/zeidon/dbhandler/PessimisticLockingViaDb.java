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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.MapMaker;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.PessimisticLockingException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.UnknownLodDefException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;
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
    private static final Collection<View> EMPTY_VIEWS = Collections.emptyList();
    private static final String GLOBAL_KEY = "*GLOBAL*";

    private boolean locked = false;
    private Task task;
    private LodDef lodDef;

    @Override
    public void initialize( ActivateOptions options ) throws PessimisticLockingException
    {
        task = options.getTask();
        lodDef = options.getLodDef();
        acquireGlobalLockViaReentrantLock();
    }

    private void acquireGlobalLockViaReentrantLock()
    {
        ReentrantLock lock = getLock( task, lodDef );
        try
        {
            if ( ! lock.tryLock(15L, TimeUnit.SECONDS) )
                throw new PessimisticLockingException( EMPTY_VIEWS,
                        "Timeout while attempting to acquire global pessimistic lock" );

            locked = true;
        }
        catch ( InterruptedException e )
        {
            throw new PessimisticLockingException( EMPTY_VIEWS,
                                                   "Error attempting to acquire global pessimistic lock" )
                                                  .setCause( e );
        }
    }

    /**
     * Acquire a global lock to prevent any other task from reading the same LOD
     * as lodDef by writing a record to the DB.
     *
     * @param task
     * @param lodDef
     */
    private void acquireGlobalLockViaDb()
    {
        View vlock = createLockOi( task, lodDef.getApplication() );

        String user = task.getUserId();
        if ( StringUtils.isBlank( user ) )
            user = "unknown";

        vlock.cursor( "ZeidonLock" ).createEntity()
                                    .getAttribute( "LOD_Name" ).setValue ( lodDef.getName() )
                                    .getAttribute( "KeyValue" ).setValue ( GLOBAL_KEY )
                                    .getAttribute( "UserName" ).setValue ( user )
                                    .getAttribute( "Timestamp" ).setValue ( new Date() )
                                    .getAttribute( "AllowRead" ).setValue ( "N" );

        addCallStack( vlock.cursor( "ZeidonLock" ) );
        addHostname( vlock.cursor( "ZeidonLock" ) );

        // TODO: Finish this.
        throw new ZeidonException( "Finish implementing" );
    }

    @Override
    public void cleanup()
    {
        if ( locked )
        {
            ReentrantLock lock = getLock( task, lodDef );
            lock.unlock();
            locked = false;
        }
    }

    private View createLockOi( Task task, Application application )
    {
        // See if the locking view exists.
        try
        {
            application.getLodDef( task, "ZPLOCKO" );
        }
        catch ( UnknownLodDefException e )
        {
            throw new ZeidonException( "LOD for pessimistic locking (ZPLOCKO) does not exist in the application.  " +
                                       "To create one use the Utilities menu in the ER diagram tool." )
                                       .setCause( e );
        }

        View vlock = task.activateEmptyObjectInstance( "ZPLOCKO", application );
        return vlock;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.PessimisticLockingHandler#acquireLocks(releaseLock(com.quinsoft.zeidon.View)
     */
    @Override
    public void acquireLocks( View view ) throws PessimisticLockingException
    {
        Task task = view.getTask();
        LodDef lodDef = view.getLodDef();
        Application application = view.getApplication();

        View vlock = createLockOi( task, application );
        EntityDef root = lodDef.getRoot();

        // For each root entity, create a locking record in ZPLOCKO
        for ( EntityInstance ei : view.cursor( root ).eachEntity() )
        {
            String user = task.getUserId();
            if ( StringUtils.isBlank( user ) )
                user = "unknown";

            vlock.cursor( "ZeidonLock" ).createEntity()
                                        .getAttribute( "LOD_Name" ).setValue ( lodDef.getName() )
                                        .getAttribute( "KeyValue" ).setValue ( ei.getKeyString() )
                                        .getAttribute( "UserName" ).setValue ( user )
                                        .getAttribute( "Timestamp" ).setValue ( new Date() )
                                        .getAttribute( "AllowRead" ).setValue ( "Y" );

            addCallStack( vlock.cursor( "ZeidonLock" ) );
            addHostname( vlock.cursor( "ZeidonLock" ) );
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
        finally
        {
            // We don't need the global lock so release it now.
            cleanup();
        }
    }

    private void addHostname( EntityCursor cursor )
    {
        EntityDef zeidonLock = cursor.getEntityDef();
        if ( zeidonLock.getAttribute( "Hostname", false ) != null )
            return;

        String hostname;
        try
        {
            hostname = InetAddress.getLocalHost().getHostName();
        }
        catch ( UnknownHostException e )
        {
            hostname = "unknown";
        }

        int lth = cursor.getAttribute( "Hostname" ).getAttributeDef().getLength();
        if ( hostname.length() > lth )
            hostname = hostname.substring( 0, lth - 1 );

        cursor.getAttribute( "Hostname" ).setValue( hostname );

    }

    /**
     * Adds a string to the locking table that is a partial call stack.
     *
     * @param cursor
     */
    private void addCallStack( EntityCursor cursor )
    {
        EntityDef zeidonLock = cursor.getEntityDef();
        if ( zeidonLock.getAttribute( "CallStack", false ) != null )
            return;

        StringBuilder sb = new StringBuilder();
        int count = 0;
        StackTraceElement[] stack = new RuntimeException().getStackTrace();
        for ( StackTraceElement element : stack )
        {
            String classname = element.getClassName();
            if ( classname.startsWith( "com.quinsoft.zeidon" ) )
                continue;

            sb.append( element.toString() ).append( "\n" );
            if ( ++count > 5 )
                break;
        }

        // Make sure the string lenth isn't too long.
        int lth = cursor.getAttribute( "CallStack" ).getAttributeDef().getLength();
        if ( sb.length() > lth )
            sb.setLength( lth );

        cursor.getAttribute( "CallStack" ).setValue( sb.toString() );
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

            LodDef     lodDef = view.getLodDef();
            EntityDef root   = lodDef.getRoot();

            // For each root entity, create a locking record in ZPLOCKO.  Normally we'd activate the locking
            // records, delete them from the OI, and then commit it.  Instead we will set the incremental
            // flags for each entity to DELETE.  This will save us the time of doing the activate.
            for ( EntityInstance ei : view.cursor( root ).eachEntity() )
            {
                vlock.cursor( "ZeidonLock" ).createEntity()
                                            .getAttribute( "LOD_Name" ).setValue( lodDef.getName() )
                                            .getAttribute( "KeyValue" ).setValue( ei.getKeyString() )
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

    private ReentrantLock getLock( Task task, LodDef lodDef )
    {
        ObjectEngine oe = task.getObjectEngine();
        GlobalLocks glocks = oe.getCacheMap( GlobalLocks.class );
        if ( glocks == null )
            glocks = oe.putCacheMap( GlobalLocks.class, new GlobalLocks() );

        return glocks.getLock( lodDef );
    }

    private static class GlobalLocks
    {
        private final ConcurrentMap<LodDef, ReentrantLock> locks = new MapMaker().concurrencyLevel( 2 ).makeMap();

        private ReentrantLock getLock( LodDef lodDef )
        {
            if ( ! locks.containsKey( lodDef ) )
                locks.putIfAbsent( lodDef, new ReentrantLock() );

            return locks.get( lodDef );
        }
    }
}
