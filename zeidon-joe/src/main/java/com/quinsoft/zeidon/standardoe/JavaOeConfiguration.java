package com.quinsoft.zeidon.standardoe;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

import com.quinsoft.zeidon.ObjectEngineEventListener;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonLogger;
import com.quinsoft.zeidon.config.HomeDirectory;
import com.quinsoft.zeidon.config.UuidGenerator;
import com.quinsoft.zeidon.config.ZeidonPreferencesFactory;
import com.quinsoft.zeidon.domains.DomainClassLoader;


/**
 * Defines the configuration options for instantiating a JavaObjectEngine.
 *
 * @author dg
 *
 */
public interface JavaOeConfiguration
{
    HomeDirectory             getHomeDirectory();
    DomainClassLoader         getDomainClassLoader();
    ZeidonLogger              getZeidonLogger();
    ZeidonPreferencesFactory  getPreferencesFactory();
    ObjectEngineEventListener getObjectEngineListener();

    /**
     * Returns the thread pool used for asynchronous activates.
     *
     * @return
     */
    ExecutorService           getActivateThreadPool();

    /**
     * Returns UUID generator.  UUIDs are generated for many internal Zeidon objects
     * so this should be fast.
     *
     * @return
     */
    UuidGenerator   getUuidGenerator();

    /**
     * This is the map that stores persistent Zeidon tasks.  It's only job is to prevent
     * persistent tasks from being cleaned up.  It may allow tasks to expire if configured
     * to do so.
     *
     * @return
     */
    ConcurrentMap<String, Task> getPersistentTaskCacheMap();

    /**
     * Specifies the app name for JMX object names that are created.  This is intended to be
     * used in environments that might have multiple ObjectEngines loaded, like Tomcat.  Then each
     * OE will have JMX objects created with a different name.  A sample might be the context name
     * in Tomcat (e.g. resteay-1.10.100).
     *
     * @return
     */
    String getJmxAppName();
}
