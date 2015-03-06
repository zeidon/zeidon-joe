/**
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
package com.quinsoft.zeidon.standardoe;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.MapMaker;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.BrowserStarter;
import com.quinsoft.zeidon.CacheMap;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.ObjectEngineEventListener;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.UnknownApplicationException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.ZeidonLogger;
import com.quinsoft.zeidon.config.HomeDirectory;
import com.quinsoft.zeidon.config.UuidGenerator;
import com.quinsoft.zeidon.config.ZeidonPreferences;
import com.quinsoft.zeidon.config.ZeidonPreferencesFactory;
import com.quinsoft.zeidon.domains.DomainClassLoader;
import com.quinsoft.zeidon.utils.CacheMapImpl;
import com.quinsoft.zeidon.utils.JoeUtils;

/**
 * <p>
 * This is the standard implementation of the ObjectEngine.  The JavaObjectInstance is
 * instantiated using a set of options that the engine uses for configuration.  Typical
 * instantiation:
 * </p>
 *
 * <pre><code>
 *  JavaOeConfiguration configuration = new DefaultJavaOeConfiguration();
 *  configuration.set....;
 *  ObjectEngine oe = new JavaObjectEngine( configuration );
 * </code></pre>
 *
 */
public class JavaObjectEngine implements ObjectEngine
{
    private static final String JOE_VERSION = "1.0";

    private static ObjectEngine s_objectEngine = null;

    private final ApplicationList   applicationList;
    private final TaskImpl          systemTask;
    private final AtomicLong        taskCounter = new AtomicLong( 0 );
    private final String            id;
    private final ZeidonPreferencesFactory zeidonPreferencesFactory;

    /**
     * This is a Concurrent hashmap that can be used by application as an engine-level cache.
     */
    private final CacheMap cacheMap = new CacheMapImpl();

    /**
     * List of tasks.  This will be stored in a weak hash map so that if a task is dropped
     * it will be automatically removed from this list.  We need to keep a taskList that
     * is separate from the persistentTaskList because the persistent list is only
     * guaranteed to contain user tasks (not the System task).
     *
     * Key is the task ID.
     */
    private final ConcurrentMap<String, TaskImpl> taskList;

    /**
     * This is a list of all persistent tasks.  This is a subset of taskList.  It's only
     * job is to prevent persistent tasks from being cleaned up by the GC.  Do not use it
     * for any other purpose.
     *
     * Key is the task ID.
     */
    private final ConcurrentMap<String, Task> persistentTaskList;

    private final HomeDirectory zeidonHomeDir;

    /**
     * Every new entity is given a unique key.
     */
    private final AtomicLong currentEntityKey = new AtomicLong();

    /**
     * A listener that gets notified by some events in the Object Engine.
     */
    private final ObjectEngineEventListener oeListener;

    private final ClassLoader classLoader = JavaObjectEngine.class.getClassLoader();
    private final DomainClassLoader domainClassLoader;
    private final ExecutorService threadPool;
    private final UuidGenerator uuidGenerator;

    public final static synchronized ObjectEngine getInstance()
    {
        if ( s_objectEngine == null )
            return getInstance( new DefaultJavaOeConfiguration() );

        return s_objectEngine;
    }

    public final static synchronized ObjectEngine getInstance( DefaultJavaOeConfiguration options )
    {
        if ( s_objectEngine == null )
            s_objectEngine = new JavaObjectEngine( options );

        return s_objectEngine;
    }

    /**
     * Sets the static instance to null to give us a way to create a new JOE instance.
     *
     * @return
     */
    public final static synchronized void resetInstance()
    {
        s_objectEngine = null;
    }

    @Override
    public String getVersion()
    {
        // TODO: change this to use the version from Maven.
        return JOE_VERSION;
    }

    /**
     * Instantiates a Java Object Engine using the options specified.
     *
     * @param options
     */
    public JavaObjectEngine( JavaOeConfiguration options )
    {
        ZeidonLogger logger = options.getZeidonLogger();
        zeidonHomeDir = options.getHomeDirectory();
        domainClassLoader = options.getDomainClassLoader();
        threadPool = options.getActivateThreadPool();
        zeidonPreferencesFactory = options.getPreferencesFactory();
        uuidGenerator = options.getUuidGenerator();
        oeListener = options.getObjectEngineListener();

        Package _package = this.getClass().getPackage();
        String version = _package.getImplementationVersion();
        String builtDate = _package.getImplementationTitle();
        logger.info( "Zeidon JOE Version: %s  Build Date: %s", version, builtDate );
        logger.info( "classpath = %s", getClassPath( logger ) );
        logger.info(  "User.dir = %s", System.getProperty("user.dir") );

        // Generate a UUID as a task ID.
        id = uuidGenerator.generate().toString();

        // Create HashMap with weak values so that tasks will be automatically GC'd when they are no longer being used.
        taskList = new MapMaker().concurrencyLevel( 10 ).weakValues().makeMap();
        persistentTaskList = options.getPersistentTaskCacheMap();

        applicationList = new ApplicationList( zeidonHomeDir, logger );
        systemTask = createTask( ObjectEngine.ZEIDON_SYSTEM_APP_NAME, true, ObjectEngine.ZEIDON_SYSTEM_APP_NAME );
        oeListener.setObjectEngine( this );

        // Check to see if we should start the browser.
        String startBrowser = JoeUtils.getEnvProperty( "zeidon.start.browser" );
        if ( StringUtils.isBlank( startBrowser ) )
            startBrowser = systemTask.readZeidonConfig( "Browser", "Start", "" );
        else
            // Log a message in case the ENVVAR overrides the value from zeidon.ini
            logger.info( "startBrowser indicator is from zeidon.start.browser: %s", startBrowser );

        if ( ! StringUtils.isBlank( startBrowser ) && startBrowser.toUpperCase().startsWith( "Y" ) )
            startBrowser();

        assert logAssertMessage( systemTask ); // Write a message to the log if assertions are on.
    }

    private boolean logAssertMessage( TaskImpl systemTask )
    {
        systemTask.log().warn( "Assertions are *ON*" );
        return true;
    }

    /**
     * @param logger
     */
    private static String getClassPath( ZeidonLogger logger )
    {
        try
        {
            StringBuilder classpath = new StringBuilder();
            ClassLoader classLoader = classpath.getClass().getClassLoader();
            if ( classLoader == null )
                classLoader = ClassLoader.getSystemClassLoader();

            URL[] urls = ((URLClassLoader) classLoader).getURLs();
            for ( URL url : urls )
                classpath.append( url.getFile() ).append( "\n" );

            return classpath.toString();
        }
        catch( Exception e )
        {
            if ( logger != null )
                logger.error( "Error trying to log classpath", e, (Object[]) null );

            return "<Error retrieving classpath>";
        }
    }

    /**
     * Starts the browser.  This method is public so that it can be called directly from Eclipse.
     */
    @Override
    @SuppressWarnings("unchecked") // for classLoader.
    public boolean startBrowser()
    {
        String browserClassName = "com.quinsoft.zeidon.objectbrowser.Starter";
        try
        {
            // Load browser class dynamically so we don't have to have the browser to compile.
            ClassLoader classLoader = getClassLoader( browserClassName );
            Class<BrowserStarter> starterClass;
            starterClass = (Class<BrowserStarter>) classLoader.loadClass( browserClassName );
            BrowserStarter starter = starterClass.newInstance();
            starter.startBrowser( this );
            return true;
        }
        catch ( Exception e )
        {
            systemTask.log().error( "Couldn't find browser class %s", e, browserClassName );
            return false;
        }
    }

    @Override
    public List<? extends Application> getApplicationList()
    {
        return applicationList.getList();
    }

    @Override
    public TaskImpl createTask( String applicationName, boolean persistent, String taskId )
    {
        TaskImpl systemTask = getSystemTask() == null ? new TaskImpl( this ) : getSystemTask();
        ApplicationImpl app = applicationList.getApplication( systemTask, applicationName );
        long counter = taskCounter.incrementAndGet();
        if ( StringUtils.isBlank( taskId ) )
            taskId = Long.toString( counter );

        if ( taskList.containsKey( taskId ) )
            throw new ZeidonException( "Task already exists with specified ID: %s", taskId );

        TaskImpl task = new TaskImpl(this, app, taskId );
        taskList.put( task.getTaskId(), task );
        if ( persistent )
            persistentTaskList.put( task.getTaskId(), task );

        if ( task.log().isTraceEnabled() )
            task.log().trace( "Task list count: %d, Persistent count: %d",
                              taskList.size(), persistentTaskList.size() );

        return task;
    }

    @Override
    public TaskImpl createTask( String applicationName, boolean persistent )
    {
        return createTask( applicationName, persistent, null );
    }

    @Override
    public TaskImpl createTask( String applicationName )
    {
        return createTask( applicationName, false, null );
    }

    @Override
    public TaskImpl createTask( String applicationName, String taskId )
    {
        return createTask( applicationName, true, taskId );
    }

    @Override
    public ApplicationImpl getApplication(String appName) throws UnknownApplicationException
    {
        return applicationList.getApplication( getSystemTask(), appName );
    }

    @Override
    public TaskImpl getSystemTask()
    {
        return systemTask;
    }

    @Override
    public List<? extends Task> getTaskList()
    {
        // Return a copy of the task list.  This way if a task is dropped the copy of the
        // task list won't be affected.
        ArrayList<TaskImpl> list = new ArrayList<TaskImpl>( taskList.values() );
        Collections.sort( list );
        return list;
    }

    /**
     * Removes the task from the persistent task list.
     * @param task
     */
    void dropTask( TaskImpl task )
    {
        taskList.remove( task.getTaskId() );
        persistentTaskList.remove( task.getTaskId() );
    }

    @Override
    public TaskImpl getTaskById(String id)
    {
    	// If the persistent cache map has a timeout, we need to do the following persistentTaskList.get
    	// to reset it so the expired task gets removed from the list.
    	persistentTaskList.get( id );
        return taskList.get( id );
    }

    @Override
    public String getHomeDirectory()
    {
        return zeidonHomeDir.getHomeDirectory();
    }

    /**
     * Returns a unique long key that can be used to uniquely identify the object
     * in the current ObjectEngine.
     *
     * @return
     */
    long getNextObjectKey()
    {
        return currentEntityKey.incrementAndGet();
    }

    @Override
    public ClassLoader getClassLoader( String className )
    {
        return classLoader;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ObjectEngine#getDomainClassLoader()
     */
    @Override
    public DomainClassLoader getDomainClassLoader()
    {
        return domainClassLoader;
    }

    @Override
    public String getId()
    {
        return id;
    }

    UUID generateUuid()
    {
        UUID uuid = uuidGenerator.generate();
        return uuid;
    }

    public int countAllEntities( View view )
    {
        ViewImpl v = ((InternalView) view).getViewImpl();
        return v.countAllEntities();
    }

    public ExecutorService getThreadPool()
    {
        return threadPool;
    }

    @Override
    public ZeidonPreferences getZeidonPreferences( Application app )
    {
        return zeidonPreferencesFactory.getPreferences( app );
    }

    ObjectEngineEventListener getOeEventListener()
    {
        return oeListener;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.CacheMap#getCacheMap(java.lang.Class)
     */
    @Override
    public <T> T getCacheMap(Class<T> key)
    {
        return cacheMap.getCacheMap( key );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.CacheMap#putCacheMap(java.lang.Class, java.lang.Object)
     */
    @Override
    public <T> T putCacheMap(Class<T> key, T value)
    {
        return cacheMap.putCacheMap( key, value );
    }
}
