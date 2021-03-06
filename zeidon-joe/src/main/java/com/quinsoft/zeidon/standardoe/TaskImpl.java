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
package com.quinsoft.zeidon.standardoe;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.MapMaker;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.DropTaskCleanup;
import com.quinsoft.zeidon.EntityCache;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.OiSourceSelector;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskLogger;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.ZeidonLogger;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.utils.StringInterpolator;

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
    private final boolean              isSystemTask;

    private       String               tempDir;
    private       String               userId;
    private       String               description;

    /**
     * New versions of entities (created by the createTemperal... methods) are given
     * a unique version number so the OE can verify if all linked instances have been
     * versioned.
     **/
    private final AtomicLong versionCounter = new AtomicLong();
    private ScalaHelper scalaHelper;

    /**
     * Keep track of entity caches for this task.
     */
    private Map<String, EntityCache> entityCacheMap;

    private Map<Application, Map<String, Map<String, String>>> configOverrideMap;

    private List<DropTaskCleanup> cleanupWork;

    private final OiSourceSelector oiSourceSelector = new DefaultOiSourceSelector();

    TaskImpl(JavaObjectEngine objectEngine, Application app, String taskId)
    {
        super(app);
        isValid = true;
        this.taskId = taskId;
        this.objectEngine = objectEngine;

        // Check to see if this is the system task.  It's ok to use '==' instead of .equals()
        // because we control the creation of the system task.
        isSystemTask = ( taskId == ObjectEngine.ZEIDON_SYSTEM_APP_NAME );

        // viewList is a weak map.  This way we can get a list of views if necessary
        // (the browser needs this) but it won't hold onto views once they are no longer
        // in use.
        viewList = new MapMaker().concurrencyLevel( 2 ).weakKeys().makeMap();
        viewNameList = new ViewNameList();
        description = this.taskId;

        String prefix = String.format( " [%4s] ", taskId );
        logger = new TaskLogger( prefix );
        dblogger = new TaskLogger( prefix );
        String logLevel = this.readZeidonConfig( app.getName(), "InitialLogLevel" );
        if ( ! StringUtils.isBlank( logLevel ) )
        {
            logger.setLevel( logLevel );
            dblogger.setLevel( logLevel );
        }

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
        isSystemTask = false;
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
        view.getApplication().dropView( view );

        // Don't remove the view from viewList.  It has a weak reference to the view so
        // it will eventually get cleaned up.  If the view doesn't disappear from the
        // viewList then the application must have a hold on the reference.  This will
        // make it easier to find resource leaks.
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

    /**
     * Calls dropTask().  This is implemented to support Closeable interface.
     */
    @Override
    public void close()
    {
        dropTask();
    }

    @Override
    public synchronized void dropTask()
    {
        // If this has already been dropped return silently.
        if ( ! isValid )
            return;

        log().debug( "Dropping task %s", taskId );

        if ( cleanupWork != null )
        {
            for ( DropTaskCleanup work : cleanupWork )
                work.taskDropped( this );
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
        CommitMultipleOIs committer = new CommitMultipleOIs( this, options, views );
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

    @SuppressWarnings("unchecked")
    @Override
    public synchronized ScalaHelper getScalaHelper()
    {
        if ( scalaHelper == null )
        {
            String className = "com.quinsoft.zeidon.scala.ScalaHelperImpl";
            ClassLoader classLoader = getObjectEngine().getClassLoader( className );
            Class<ScalaHelper> operationsClass;
            try
            {
                operationsClass = (Class<ScalaHelper>) classLoader.loadClass( className );
                scalaHelper = operationsClass.newInstance();
                scalaHelper.setClassLoader( classLoader );
            }
            catch ( ClassNotFoundException e )
            {
                throw new ZeidonException("Couldn't load %s.  Do you have zeidon-scala in your classpath?", className );
            }
            catch ( Exception e )
            {
                throw ZeidonException.wrapException( e );
            }
        }

        return scalaHelper;
    }

    @Override
    public synchronized EntityCache getEntityCache( EntityDef entityDef )
    {
        if ( entityCacheMap != null )
        {
            EntityCache entityCache = entityCacheMap.get( entityDef.getErEntityToken() );
            if ( entityCache != null )
                return entityCache;
        }

        if ( isSystemTask )
            return null;

        return getSystemTask().getEntityCache( entityDef );
    }

    @Override
    public synchronized boolean setEntityCache( EntityCache entityCache )
    {
        if ( entityCacheMap == null )
            entityCacheMap = new HashMap<>();

        boolean b = entityCacheMap.containsKey( entityCache.getErEntityToken() );
        entityCacheMap.put( entityCache.getErEntityToken(), entityCache );
        return b;
    }

    @Override
    public synchronized String readZeidonConfig(Application application, String group, String key, String defaultValue)
    {
        String g = normalizeGroup( group );

        // Check to see if the value has been overridden for this task.
        if ( configOverrideMap != null )
        {
            Map<String, Map<String, String>> appMap = configOverrideMap.get( application );
            if ( appMap != null )
            {
                Map<String, String> groupMap = appMap.get( group );
                if ( groupMap != null )
                {
                    if ( groupMap.containsKey( key ) )
                        return groupMap.get( key ); // If we get here then the value has been overridden.
                }
            }
        }

        return getObjectEngine().getZeidonPreferences( application ).get( g, key, defaultValue );
    }

    /**
     * Synchronized so it can be used for the system task.
     */
    @Override
    public synchronized void overrideZeidonConfig( Application application, String group, String key, String value )
    {
        group = normalizeGroup( group );

        if ( configOverrideMap == null )
            configOverrideMap = new HashMap<>();

        Map<String, Map<String, String>> appMap = configOverrideMap.get( application );
        if ( appMap == null )
        {
            appMap = new HashMap<>();
            configOverrideMap.put( application, appMap );
        }

        Map<String, String> groupMap = appMap.get( group );
        if ( groupMap == null )
        {
            groupMap = new HashMap<>();
            appMap.put( group, groupMap );
        }

        groupMap.put( key, value );
    }

    @Override
    public void addTaskCleanupWork( DropTaskCleanup work )
    {
        if ( cleanupWork == null )
            cleanupWork = new ArrayList<>();

        cleanupWork.add( work );
    }

    @Override
    public DeserializeOi deserializeOi()
    {
        return new DeserializeOi( this ).setApplication( this.getApplication() );
    }

    @Override
    public String interpolate( String string )
    {
        return interpolate( string, null );
    }

    @Override
    public String interpolate( String string, Map<String, Object> variables )
    {
        StringInterpolator interpolator = new StringInterpolator();
        interpolator.setTask( this ).setVariables( variables );
        return interpolator.interpolate( string );
    }

    @Override
    public OiSourceSelector getOiSourceSelector()
    {
        return oiSourceSelector;
    }
}
