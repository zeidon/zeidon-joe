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
package com.quinsoft.zeidon;

import java.util.List;

import com.quinsoft.zeidon.config.ZeidonPreferences;
import com.quinsoft.zeidon.domains.DomainClassLoader;


/**
 * This is the main container of all Zeidon objects.  User applications generally
 * use it just to create Tasks.  The standard implementation of the ObjectEngine
 * is JavaObjectEngine.
 *
 */
public interface ObjectEngine extends CacheMap
{
    /**
     * Name of the application for the Zeidon System.
     */
    static final String ZEIDON_SYSTEM_APP_NAME = "ZeidonSystem";

    static final String INTERNAL_DATETIME_STRING_FORMAT = "yyyyMMddHHmmssSSS";
    static final String INTERNAL_DATE_STRING_FORMAT     = "yyyyMMdd";

    /**
     * A string representation of an ID that is (virtually) guaranteed to be globally unique.
     *
     * @return unique ID.
     */
    String getId();

    /**
     * Returns the list of all known applications.  The list of known applications
     * is found by searching through the classpath and opening all zeidon.app resources
     * that are found.
     *
     * @return the list of known applications.
     */
    List<? extends Application> getApplicationList();

    /**
     * Returns the application object for the specified application.
     *
     * @param appName name of the application.
     * @return Application object.
     * @throws UnknownApplicationException
     */
    Application getApplication(String appName ) throws UnknownApplicationException;

    /**
     * Create a NON-persistent Zeidon task.  See
     * createTask( String applicationName, boolean persistent )
     * for more information about persistent tasks.
     *
     * @param applicationName
     * @throws UnknownApplicationException
     * @return new NON-persistent task.
     */
    Task createTask( String applicationName ) throws UnknownApplicationException;

    /**
     * Create a persistent Zeidon task and assigning it the specified taskId.
     *
     * @param applicationName
     * @param taskId
     * @return a new persistent task.
     * @throws UnknownApplicationException
     */
    Task createTask( String applicationName, String taskId ) throws UnknownApplicationException;

    /**
     * Creates a Zeidon task.  If persistent = true then the Object Engine will keep
     * a persistent reference to the task.  This means that task.dropTask() *must* be
     * called for it to be released.  A non-persistent task will be automatically cleaned
     * up once there are no more application references to the task.
     *
     * @param applicationName
     * @param persistent
     * @throws UnknownApplicationException
     * @return
     */
    Task createTask( String applicationName, boolean persistent ) throws UnknownApplicationException;

    /**
     * Create a Zeidon task and assigning it the specified taskId.
     *
     * @param applicationName
     * @param taskId
     * @param persistent
     * @return
     * @throws UnknownApplicationException
     */
    Task createTask( String applicationName, boolean persistent, String taskId  ) throws UnknownApplicationException;

    /**
     * @return the Zeidon System task that is registered when the Object Engine is instantiated.
     */
    Task getSystemTask();

    /**
     * Looks for a task with a matching ID.
     *
     * @param id Task ID.
     * @return Task or null if ID is not found.
     */
    Task getTaskById( String id );

    /**
     * Return the list of current tasks sorted by task ID.  Note that holding on
     * to this list could prevent non-persistent tasks from being garbage-collected.
     *
     * @return list of current tasks.
     */
    List<? extends Task> getTaskList();

    /**
     * @return ZEIDON_HOME directory.
     */
    String getHomeDirectory();

    /**
     * @return the current version of the OE.
     */
    String getVersion();

    /**
     * Used to dynamically load classes.
     * @param className Name of class that will be loaded.  Usually ignored.
     * @return
     */
    ClassLoader getClassLoader( String className );

    /**
     *
     * @return returns the class loader for domains.
     *
     * TODO: It would be nice if this could be injected directly into the objects that use it
     *       instead of getting it from the OE.
     */
    DomainClassLoader getDomainClassLoader();

    /**
     * Start the Object Browser.
     *
     * @return true if started, false if there was an exception.
     */
    boolean startBrowser();

    /**
     * Get the ZeidonPreferences for an application.
     *
     * @param app
     * @return
     */
    ZeidonPreferences getZeidonPreferences( Application app );

}