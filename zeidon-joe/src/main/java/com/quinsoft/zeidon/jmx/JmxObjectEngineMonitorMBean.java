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

package com.quinsoft.zeidon.jmx;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;


/**
 * Interface for standard JMX operations with the Object Engine.
 *
 * @author dg
 *
 */
public interface JmxObjectEngineMonitorMBean
{
    /**
     * Returns a list of view names for each task.
     *
     * @return
     */
    Collection<Map<String, String>> getViewList();

    /**
     * Returns a list of all views and their names.
     *
     * @param taskId
     * @return
     */
    Collection<String> getViewList( String taskId );

    /**
     * Downloads the view serialized as JSON.
     *
     * @param taskId
     * @param viewId
     * @return
     */
    String getSerializedView( String taskId, Long viewId );

    /**
     * @return A list of task IDs for all active tasks.
     */
    Collection<String> getTaskList();

    /**
     * Write the task list to the logs.
     *
     * @return debug message.
     */
    String logTaskList();

    /**
     * Write all tasks and view information to the log.
     *
     * @return debug message.
     */
    String logAllTasksAndViews();

    /**
     * Writes a list of all active views to the Zeidon log for the supplied task.
     *
     * @param taskId
     * @return Status message.
     */
    String logViews( String taskId );

    /**
     * Drops the view specified by taskId and viewName.
     *
     * @param taskId
     * @param viewName
     * @return
     */
    String dropViewByName( String taskId, String viewName );

    /**
     * Drops the view specified by viewName from the system task.  This
     * effectively drops a cached OI.
     *
     * @param viewName
     * @return
     */
    String dropCachedViewByName( String viewName );

    /**
     * @return runtime Properties.
     */
    Properties getRuntimeProperties();

    /**
     * Starts the object browser.
     *
     * @return
     */
    String startObjectBrowser();
}
