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
 */
public interface JavaOeConfiguration
{
    /**
     * The Zeidon home directory is used override the classpath for determining
     * the location of application files.  Use this with caution.
     *
     * @return object for determining the Zeidon home directory.
     */
    HomeDirectory             getHomeDirectory();

    DomainClassLoader         getDomainClassLoader();
    ObjectEngineEventListener getObjectEngineListener();

    /**
     * Returns the logger used while bootstrapping the Object Engine.
     *
     * @return the logger used while bootstrapping the Object Engine.
     */
    ZeidonLogger              getZeidonLogger();

    /**
     * Returns the factory that the OE uses for loading application preferences.
     *
     * @return the factory that the OE uses for loading application preferences.
     */
    ZeidonPreferencesFactory  getPreferencesFactory();

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
