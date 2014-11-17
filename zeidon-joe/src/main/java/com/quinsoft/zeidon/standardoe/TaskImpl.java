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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.common.collect.MapMaker;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.Lockable;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskLogger;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.ZeidonLogger;
import com.quinsoft.zeidon.utils.LazyLoadLock;

/**
 * Comparable interface is implemented so that we can easily sort tasks by ID.
 *
 * @author DG
 *
 */
class TaskImpl extends AbstractTaskQualification implements Task, Comparable<TaskImpl>
{
    private final TaskLogger           logger;
    private final ZeidonLogger         dblogger;
    private final ViewNameList         viewNameList;
    private final ConcurrentMap<ViewImpl, Boolean> viewList;
    private final String               taskId;
    private final JavaObjectEngine     objectEngine;
    private boolean                    isValid;
    private final LazyLoadLock         lock;
    private final NamedLockableList    lockList = new NamedLockableList();

    /**
     * List of views that have pessimistic locks.
     */
    private ConcurrentMap<ViewImpl, Boolean> lockedViews;

    private       String               tempDir;
    private       String               userId;
    private       String               description;

    /**
     * New versions of entities (created by the createTemperal... methods) are given
     * a unique version number so the OE can verify if all linked instances have been
     * versioned.
     **/
    private final AtomicLong versionCounter = new AtomicLong();

    TaskImpl(JavaObjectEngine objectEngine, Application app, String taskId)
    {
        super(app);
        isValid = true;
        this.taskId = taskId.intern();
        this.objectEngine = objectEngine;

        // viewList is a weak map.  This way we can get a list of views if necessary
        // (the browser needs this) but it won't hold onto views once they are no longer
        // in use.
        viewList = new MapMaker().concurrencyLevel( 2 ).weakKeys().makeMap();
        viewNameList = new ViewNameList();
        description = this.taskId;

        String prefix = String.format( " [%4s] ", taskId );
        logger = new TaskLogger( prefix );
        dblogger = new TaskLogger( prefix );
        lock = new LazyLoadLock();

        log().info( "Created new task for app %s, task ID = %s", app.getName(), taskId );
    }

    /**
     * This creates a boot-strap task used only for logging.
     */
    TaskImpl( JavaObjectEngine objectEngine )
    {
        super(null);
        taskId = "null";
        this.objectEngine = objectEngine;
        viewList = null;
        viewNameList = null;
        logger = new TaskLogger( " [bootstrap] " );
        dblogger = new TaskLogger( " [bootstrap] " );
        lock = null;
    }

    @Override
    public TaskImpl getTask()
    {
        return this;
    }

    @Override
    public TaskLogger log()
    {
        return logger;
    }

    @Override
    public ZeidonLogger dblog()
    {
        return dblogger;
    }

    /**
     * Adds the view to the internal weak viewList map.
     *
     * @param view
     */
    void addNewView( ViewImpl view )
    {
        viewList.put( view, Boolean.TRUE );
        if ( log().isTraceEnabled() )
            log().trace( "ViewList count: %d/%d", viewList.size(), viewList.keySet().size() );
    }

    void dropView( ViewImpl view )
    {
        // Try removing the view from all the name lists.  Note that this won't drop
        // the view name if it belongs to a different application list other than default.
        viewNameList.dropView( view );
        getSystemTask().viewNameList.dropView( view );
        getApplication().dropView( view );

        // Don't remove the view from viewList.  It has a weak reference to the view so
        // it will eventually get cleaned up.  If the view doesn't disappear from the
        // viewList then the application must have a hold on the reference.  This will
        // make it easier to find resource leaks.
    }

    private synchronized ConcurrentMap<ViewImpl, Boolean> getLockedViews()
    {
        if ( lockedViews == null )
            lockedViews = new MapMaker().concurrencyLevel( 2 ).makeMap();

        return lockedViews;
    }

    /**
     * Keep track of views with pessimistic locks so we can drop the locks.
     *
     * @param view
     */
    void addLockedView( ViewImpl view )
    {
        assert ! getLockedViews().containsKey( view ) : "Attempting to add a pessimistic lock twice.";
        getLockedViews().putIfAbsent( view, Boolean.TRUE );
    }

    void removePessimisticLock( ViewImpl view )
    {
        assert getLockedViews().containsKey( view ) : "Attempting to remove a non-existent pessimistic lock.";
        getLockedViews().remove( view );
    }

    @Override
    public Collection<ViewImpl> getViewList()
    {
        // Return a copy of the view list.  This way the user application (most likely the
        //  browser) doesn't have to worry about views being deleted while iterating.
        ArrayList<ViewImpl> list = new ArrayList<ViewImpl>( viewList.keySet() );
        Collections.sort( list );
        return Collections.unmodifiableList( list );
    }

    @Override
    public int getViewCount()
    {
        return viewList.keySet().size();
    }

    @Override
    public String getTaskId()
    {
        return taskId;
    }

    @Override
    public String getUserId()
    {
        return userId;
    }

    @Override
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    @Override
    public void dropTask()
    {
        // If this has already been dropped return silently.
        if ( ! isValid )
            return;

        log().info( "Dropping task %s", taskId );

        if ( lockedViews != null )
        {
            for ( ViewImpl view : lockedViews.keySet() )
            {
                log().warn( "View %s still has pessimistic locks.  Dropping them.", view.toString() );
                view.dropDbLocks();
            }
        }

        isValid = false;

        assert assertDropTask();

        // All we need to do is remove the task from the Object Engine's task list.
        // The GC will do everything else.
        objectEngine.dropTask( this );
    }

    /**
     * @return
     */
    private boolean assertDropTask()
    {
        // Make sure that no views named at the application level are associated with this task.
        for ( Application app : objectEngine.getApplicationList() )
        {
            for ( View view : app.getAllNamedViews() )
            {
                if ( ! view.getTask().isValid() )
                {
                    getSystemTask().log().error( "View %d is named at app level but is associated with an invalid task %d",
                                                  view.getId(), view.getTask().getTaskId() );
                    assert false : "View named at app level is associated with a invalid task";
                }

                // Is this a valid check?
                if ( view.getTask() != getSystemTask() )
                {
                    getSystemTask().log().error( "View %d is associated with an invalid task %d",
                                                  view.getId(), view.getTask().getTaskId() );
                    assert false : "There's a named view that is associated with a invalid task";
                }
            }
        }

        // If we get here then everythings ok.
        return true;
    }

    @Override
    public TaskImpl getSystemTask()
    {
        return objectEngine.getSystemTask();
    }

    @Override
    public int commitMultipleOis(CommitOptions options, View... views)
    {
        return commitMultipleOis( options, Arrays.asList( views ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Task#commitMultipleOis(long, com.quinsoft.zeidon.dbhandler.DbConfiguration, com.quinsoft.zeidon.View[])
     */
    @Override
    public int commitMultipleOis( View... views)
    {
        return commitMultipleOis( new CommitOptions( this ), Arrays.asList( views ) );
    }

    @Override
    public int commitMultipleOis(Collection<View> views)
    {
        return commitMultipleOis( new CommitOptions( this ), views );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Task#commitMultipleOis(long, com.quinsoft.zeidon.dbhandler.DbConfiguration, com.quinsoft.zeidon.View[])
     */
    @Override
    public int commitMultipleOis( CommitOptions options, Collection<View> views)
    {
        ArrayList<View> list = new ArrayList<View>();
        list.addAll( views );
        options.setViewList( list );
        CommitMultiplOIs committer = new CommitMultiplOIs( this, options, views );
        return committer.commit();
    }

    @Override
    public JavaObjectEngine getObjectEngine()
    {
        return objectEngine;
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
    public View getViewByKey( long key )
    {
        for ( View v : viewList.keySet() )
        {
            if ( v.getId() == key )
                return v;
        }

        return null;
    }

    @Override
    public void setNameForView(String name, View view)
    {
        viewNameList.setNameForView( name, view );
    }

    @Override
    public List<String> getViewNameList(View view)
    {
        return viewNameList.getAllViewNames( view );
    }

    @Override
    public String toString()
    {
        return String.format( "Task %s app %s", getTaskId(), getApplication() );
    }

    @Override
    public boolean isValid()
    {
        return isValid;
    }

    @Override
    public synchronized ReentrantReadWriteLock getLock()
    {
        return lock.getLock();
    }

    @Override
    public Lockable getNamedLock(String name)
    {
        return lockList.getNamedLock( name );
    }

    @Override
    public String getTempDirectory()
    {
        if ( tempDir != null )
            return tempDir;

        tempDir = System.getProperty( "java.io.tmpdir" );
        if ( ! new File( tempDir ).exists() )
            throw new ZeidonException("Temp directory '%s' does not exist.  Create the directory or set ZEIDON_TEMP");

        tempDir = tempDir + File.separator;
        return tempDir;
    }

    @Override
    public int compareTo(TaskImpl o)
    {
        return taskId.compareTo( o.getTaskId() );
    }

    @Override
    public boolean equals( Object o )
    {
        if ( o instanceof TaskImpl )
            return compareTo( (TaskImpl) o ) == 0;

        return false;
    }

    long getNextVersionNumber()
    {
        //TODO: This should probably be set for the entire OE because we could have entities
        // from two different tasks with different versions.
        return versionCounter.incrementAndGet();
    }

    private void unlockAll( int length, Lock...locks)
    {
        assert length <= locks.length;

        for ( int i = 0; i < length; i++ )
        {
            try
            {
                locks[i].unlock();
            }
            catch ( Throwable t )
            {
                try
                {
                    log().error( "Error trying to unlock lock", t );
                }
                catch ( Throwable tt )
                {
                    // We need to make sure we unlock everything so if we catch an error while writing the
                    // error message we'll swallow it so we can keep going.
                }
            }
        }
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Task#lockAll(java.util.concurrent.locks.Lock[])
     */
    @Override
    public boolean lockAll(Lock... locks)
    {
        int lockCount = 0;
        try
        {
            while ( lockCount < locks.length )
            {
                if ( ! locks[lockCount].tryLock() )
                {
                    unlockAll( lockCount, locks );
                    return false;
                }
                lockCount++;
            }
        }
        catch ( Throwable t )
        {
            unlockAll( lockCount, locks );
            throw ZeidonException.wrapException( t );
        }

        return true;
    }

    /**
     * Attempt to lock all the locks. If there is contention then the logic will sleep a short amount of
     * time and try again.  After totalRetries times the method will throw an exception.
     *
     * @param retryWait time in milliseconds.
     * @param totalRetries total number of times to retry acquiring locks.
     * @param locks
     *
     * @throws ZeidonException if locks could not be acquired.
     */
    void lockAll( int retryWait, int totalRetries, Lock...locks)
    {
        for ( int count = 0; count < totalRetries; count++ )
        {
            if ( lockAll( locks ) )
                return;

            try
            {
                Thread.sleep( retryWait );
            }
            catch ( InterruptedException e )
            {
                throw ZeidonException.wrapException( e );
            }
        }

        // If we get here then we were never able to get all the locks.
        throw new ZeidonException( "Unable to acquire all locks" );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Task#unlockAll(java.util.concurrent.locks.Lock[])
     */
    @Override
    public void unlockAll(Lock... locks)
    {
        unlockAll( locks.length, locks );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Task#setDescription(java.lang.String)
     */
    @Override
    public void setDescription( String description )
    {
        this.description = description;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Task#getDescription()
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public SerializeOi serializeOi()
    {
        return new SerializeOi();
    }
}
