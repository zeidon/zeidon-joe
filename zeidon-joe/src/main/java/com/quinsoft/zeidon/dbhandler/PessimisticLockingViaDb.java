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

package com.quinsoft.zeidon.dbhandler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.Pagination;
import com.quinsoft.zeidon.PessimisticLockingException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.UnknownLodDefException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.DataRecord;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.standardoe.IncrementalEntityFlags;
import com.quinsoft.zeidon.utils.KeyStringBuilder;

/**
 * Handles pessimistic View locking by writing records to the DB to lock OIs.
 * Assumes that ZPLOCKO LOD exists in the application.
 *
 * @author DG
 *
 */
public class PessimisticLockingViaDb implements PessimisticLockingHandler
{
    private final Task task;
    private final LodDef lodDef;
    private final Application application;

    /**
     * This is the ZPLOCK OI that has the lock entities that will be written to the DB.
     */
    private View lockOi;
    private EntityCursor lockCursor;
    /**
     * If true then we've created the pessimistic lock and nothing needs to be done.
     */
    private boolean lockPerformed = false;
    private boolean lockedByQual = false;
    private final EntityDef rootEntityDef;
    private final Map<EntityDef, QualEntity> qualMap;
    private final ActivateOptions activateOptions;
    private GlobalJavaLock javaLock;

    public PessimisticLockingViaDb( ActivateOptions options, Map<EntityDef, QualEntity> qualMap  ) throws PessimisticLockingException
    {
        task = options.getTask();
        lodDef = options.getLodDef();
        application = lodDef.getApplication();
        rootEntityDef = lodDef.getRoot();
        this.qualMap = qualMap;
        activateOptions = options;

        // If we are activating with rolling pagination then replace the root cursor
        // with a special one that will attempt to load the next page when required.
        Pagination pagingOptions = activateOptions.getPagingOptions();
        if ( pagingOptions != null && pagingOptions.isRollingPagination() )
        {
            throw new ZeidonException( "Pessimistic locking is not supported with rolling pagination."
                                     + "  Use read-only option on the activate." );
        }
    }

    private String getUserName()
    {
        String user = task.getUserId(); // TODO: We need to get this value from activateOptions.
        if ( StringUtils.isBlank( user ) )
            user = "unknown";

        return user;
    }

    /**
     * Adds a global lock to the lock OI to prevent another task from attempting
     * to lock the same LOD.
     */
    private void addGlobalLockToLockOi()
    {
        createLockOi( task );

        DataRecord dataRecord = rootEntityDef.getDataRecord();
        String tableName = dataRecord.getRecordName();
        lockOi.cursor( "ZeidonLock" ).createEntity()
                                    .getAttribute( "LOD_Name" ).setValue ( lodDef.getName() + "-GlobalLock" )
                                    .getAttribute( "KeyValue" ).setValue ( tableName )
                                    .getAttribute( "UserName" ).setValue ( getUserName() )
                                    .getAttribute( "Timestamp" ).setValue ( new Date() )
                                    .getAttribute( "AllowRead" ).setValue ( "N" );

        addCallStack( lockOi.cursor( "ZeidonLock" ) );
        addHostname( lockOi.cursor( "ZeidonLock" ) );
    }

    /**
     * Adds locking to the
     */
    private void addQualLocksToLockOi()
    {
        // We can only implement this if we have qualification on the keys
        // and
        QualEntity rootQual = qualMap.get( rootEntityDef );
        if ( rootQual == null || ! rootQual.isKeyQualification() )
            return;

        // Currently we only handle a single key.  Someday we could add more.
        if ( rootQual.qualAttribs.size() != 1 )
            return;

        KeyStringBuilder builder = new KeyStringBuilder();
        QualAttrib qualAttrib = rootQual.qualAttribs.get( 0 );
        builder.appendKey( task, qualAttrib.attributeDef, qualAttrib.value );

        lockOi.cursor( "ZeidonLock" ).createEntity()
                                     .getAttribute( "LOD_Name" ).setValue ( lodDef.getName() )
                                     .getAttribute( "KeyValue" ).setValue ( builder.toString() )
                                     .getAttribute( "UserName" ).setValue ( getUserName() )
                                     .getAttribute( "Timestamp" ).setValue ( new Date() )
                                     .getAttribute( "AllowRead" ).setValue ( "Y" );

        addCallStack( lockOi.cursor( "ZeidonLock" ) );
        addHostname( lockOi.cursor( "ZeidonLock" ) );

        // Indicate that the lock of the roots has been performed.
        lockPerformed = true;
        lockedByQual = true;
    }

    private View createLockOi( Task task )
    {
        if ( lockOi != null )
            return lockOi;

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

        lockOi = task.activateEmptyObjectInstance( "ZPLOCKO", application );
        lockCursor = lockOi.cursor( "ZeidonLock" );
        return lockOi;
    }

    private void addRootsToLockOi( View view )
    {
        EntityDef root = lodDef.getRoot();

        // For each root entity, create a locking record in ZPLOCKO
        for ( EntityInstance ei : view.cursor( root ).eachEntity() )
        {
            lockCursor.createEntity()
                      .getAttribute( "LOD_Name" ).setValue ( lodDef.getName() )
                      .getAttribute( "KeyValue" ).setValue ( ei.getKeyString() )
                      .getAttribute( "UserName" ).setValue ( getUserName() )
                      .getAttribute( "Timestamp" ).setValue ( new Date() )
                      .getAttribute( "AllowRead" ).setValue ( "Y" );

            addCallStack( lockCursor );
            addHostname( lockCursor );
        }
    }

    private void addHostname( EntityCursor cursor )
    {
        EntityDef zeidonLock = cursor.getEntityDef();
        if ( zeidonLock.getAttribute( "Hostname", false ) == null )
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
        if ( zeidonLock.getAttribute( "CallStack", false ) == null )
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

    // Dunno if we'll ever need this.  Saving for now.
    @SuppressWarnings("unused")
    private void createOiToDropLocks( View view )
    {
        createLockOi( task );

        // For each root entity, create a locking record in ZPLOCKO.  Normally we'd activate the locking
        // records, delete them from the OI, and then commit it.  Instead we will set the incremental
        // flags for each entity to DELETE.  This will save us the time of doing the activate.
        for ( EntityInstance ei : view.cursor( rootEntityDef ).eachEntity() )
        {
            lockCursor.createEntity()
                      .getAttribute( "LOD_Name" ).setValue( lodDef.getName() )
                      .getAttribute( "KeyValue" ).setValue( ei.getKeyString() )
                      .setIncrementalFlags( IncrementalEntityFlags.DELETED );
        }
    }

    /**
     * This gets called when a view is dropped.  Release the locks.
     */
    @Override
    public void viewDropped( View view )
    {
        releaseLocks( view );
    }

    private GlobalJavaLock getJavaLock()
    {
        if ( javaLock == null )
        {
            javaLock = lodDef.getCacheMap( GlobalJavaLock.class );
            if ( javaLock == null )
                javaLock = lodDef.putCacheMap( GlobalJavaLock.class, new GlobalJavaLock() );
        }

        return javaLock;
    }

    @Override
    public void acquireGlobalLock( View view ) throws PessimisticLockingException
    {
        createLockOi( task );
        addGlobalLockToLockOi();
        addQualLocksToLockOi();

        // To minimize attempts to write to the DB we'll use a global Java
        // lock to single-thread writes for the current JVM.
        view.log().trace( "Locking global Java lock" );
        getJavaLock().lock.lock();
        view.log().trace( "Global Java acquired" );

        writeLocks( view );
    }

    @Override
    public void releaseGlobalLock( View view )
    {
        try
        {
            // Delete the global lock.
            lockCursor.setFirst();
            lockCursor.deleteEntity();

            // If we lockedByQual then we've also locked the entities we tried to activate.
            // If the activated view is empty then we didn't find anything so drop all the locks.
            if ( lockedByQual && view.isEmpty() )
                lockCursor.deleteAll();

            lockOi.commit();
        }
        finally
        {
            // Make sure we remove the java lock.
            getJavaLock().lock.unlock();
            view.log().trace( "Global Java unlocked" );
        }
    }

    /**
     * This will attempt to write the locks to the DB.  It will re-try a few times before
     * giving up.
     */
    private void writeLocks( View view )
    {
        int retryCount = 4;
        Exception exception = null;;
        for ( int i = 0; i < retryCount; i++ )
        {
            try
            {
                lockOi.commit();
                return;
            }
            catch ( Exception e )
            {
                exception = e;

                // We'll log message.  The level of the message will depend on the # of tries.
                switch ( i )
                {
                    case 0:
                        task.log().debug( "Caught exception writing pessimisic locks on %s.  Trying again", lodDef );
                        break;

                    case 1:
                        task.log().info( "Caught exception writing pessimisic locks on %s.  Trying again", lodDef );
                        break;

                    default:
                        task.log().warn( "Caught exception writing pessimisic locks on %s.  Trying again", lodDef );
                        lockOi.logObjectInstance();
                }

                try
                {
                    if ( i < retryCount )
                        Thread.sleep( 10 * i * i );
                }
                catch ( InterruptedException e1 )
                {
                }
            }
        }

        // If we get here then none of the commits succeeded and we're giving up.
        throw new PessimisticLockingException( view, "Unable to acquire pessimistic locks" ).setCause( exception );
    }

    private void acquireLocksFromView( View view )
    {
        if ( lockPerformed )
            return; // We've already locked the view.

        createLockOi( task );
        addRootsToLockOi( view );

        writeLocks( view );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.PessimisticLockingHandler#acquireLocks(releaseLock(com.quinsoft.zeidon.View)
     */
    @Override
    public void acquireRootLocks( View view ) throws PessimisticLockingException
    {
        acquireLocksFromView( view );
    }

    @Override
    public void acquireOiLocks( View view ) throws PessimisticLockingException
    {
        // This call is for DB handlers that can't have more than one open connection
        // at a time.  For normal processing this doesn't do anything.  Those DB handlers
        // would call acquireLocksFromView( view ) from here.
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.PessimisticLockingHandler#releaseLocks(com.quinsoft.zeidon.View)
     */
    @Override
    public void releaseLocks( View view )
    {
        lockCursor.deleteAll();
        lockOi.commit();
    }

    private static class GlobalJavaLock
    {
        private final Lock lock = new ReentrantLock();
    }
}
