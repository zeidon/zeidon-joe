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

import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.Lock;

import com.quinsoft.zeidon.standardoe.ScalaHelper;


/**
 * <p>
 * All methods operating on a task.  A task is the basic container for Zeidon Object
 * Instances (OIs). When a task is no longer referenced all of its OIs will be dropped.
 * </p>
 *
 * <p>
 * A task is created by the Object Engine and has three main properties:
 * <ul>
 *    <li>
 *    <b>Default application</b>: when calling methods that take a LOD name then the LOD is
 *            retrieved from the default application.  (Some methods allow for a
 *            different application to be specified.)
 *    </li>
 *    <li>
 *    <b>Task ID</b>: A unique string that can be used to retrieve the task from the OE.  If
 *            the ID is not specified when the task is created it will default to a
 *            unique integer.
 *    </li>
 *    <li>
 *    <b>Persistent</b>: It a task is persistent then the ObjectEngine will keep an internal
 *            reference to the task to prevent the task from being garbage-collected.
 *            By default tasks are NOT persistent.
 *    </li>
 * </ul></p>
 *
 * Tasks are usually created like:
 * <pre><code>
 *      Task task = objectEngine.createTask( "DefaultApplication" );
 * </code></pre>
 *
 */
public interface Task extends TaskQualification, CacheMap
{
    /**
     * Get the list of views for this task.  Includes views without names.  Use this
     * method carefully.  It is possible to create a memory leak by holding on to
     * the list that is returned.
     *
     * @return list of views for this task.
     */
    Collection<? extends View> getViewList();

    /**
     * Returns an approximate count of views for this task.  It is approximate because
     * the GC may not have cleaned up some views.
     *
     * @return view count
     */
    int getViewCount();

    /**
     * Get a string ID that uniquely defines this task.  Is not restricted to digits.
     *
     * @return task ID
     */
    String getTaskId();

    /**
     * @deprecated
     */
    @Deprecated
    String getUserId();

    /**
     * @deprecated
     */
    @Deprecated
    void setUserId( String userId );

    /**
     * If the task is persistent, this makes it non-persistent.  The task will then
     * be cleaned up when there are no longer any references to the task.  This DOES NOT
     * explicitly drop the task.
     */
    void dropTask();

    /**
     * If false then dropTask() has been called on this task.
     * @return
     */
    boolean isValid();

    /**
     * Commit the list of view in a single transaction.  If the commit fails for one
     * view then it fails for all of them.
     *
     * @param views list of views to commit.
     *
     * @return 0
     */
    int commitMultipleOis( View...views );

    /**
     * Commit the list of view in a single transaction.  If the commit fails for one
     * view then it fails for all of them.
     *
     * @param views list of views to commit.
     *
     * @return 0
     */
    int commitMultipleOis( CommitOptions options, View...views );

    /**
     * Commit the list of view in a single transaction.  If the commit fails for one
     * view then it fails for all of them.
     *
     * @param views list of views to commit.
     *
     * @return 0
     */
    int commitMultipleOis( Collection<View> views );

    /**
     * Commit the list of view in a single transaction.  If the commit fails for one
     * view then it fails for all of them.
     *
     * @param views list of views to commit.
     *
     * @return 0
     */
    int commitMultipleOis( CommitOptions options, Collection<View> views );

    /**
     * Drop the name for the specified view.
     *
     * @param name name to drop.
     * @param view view that should have this name.  TODO: is this necessary?
     */
    void dropNameForView( String name, View view );

    /**
     * Return list of names for the specified view.
     *
     * @param view view to find views.
     *
     * @return list of names for the specified view.
     */
    List<String> getViewNameList(View view);

    /**
     * Set the name of the view inside this task.
     *
     * @param name name of the view
     *
     * @param view view to be named.
     */
    void setNameForView( String name, View view );

    /**
     * Sets a description for this task.  This is for debugging purposes only.
     *
     * @param description
     */
    void setDescription( String description );

    /**
     * Gets the description for this task.  This is for debugging purposes only.
     *
     * @return task description
     */
    String getDescription();

    Lockable getNamedLock( String name );

    /**
     * Will attempt to lock all the locks passed in.
     *
     * @param locks
     * @return true if all locks are locked, false otherwise.
     */
    boolean lockAll( Lock...locks );

    /**
     * Will unlock all locks passed in.
     *
     * @param locks
     */
    void unlockAll( Lock...locks );

    /**
     * Returns a helper object for calling Scala code from the JOE.  Creates one per task.
     *
     * @return a task-specific Scala helper.
     */
    ScalaHelper getScalaHelper();
}
