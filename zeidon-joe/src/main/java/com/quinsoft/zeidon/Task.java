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


/**
 * All methods operating on a task.
 *
 * When a task is no longer referenced all of its OIs will be dropped.
 *
 * @author DG
 *
 */
public interface Task extends TaskQualification, CacheMap
{
    /**
     * Get the list of views for this task.  Includes views without names.
     *
     * @return
     */
    Collection<? extends View> getViewList();

    /**
     * Returns an approximate count of views for this task.  It is approximate because
     * the GC may not have cleaned up some views.
     *
     * @return
     */
    int getViewCount();

    /**
     * Get a string ID that uniquely defines this task.  Is not restricted to digits.
     * @return
     */
    String getTaskId();

    String getUserId();
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

    int commitMultipleOis( View...views );
    int commitMultipleOis( CommitOptions options, View...views );
    int commitMultipleOis( Collection<View> views );
    int commitMultipleOis( CommitOptions options, Collection<View> views );

    void dropNameForView( String name, View view );
    List<String> getViewNameList(View view);
    void setNameForView( String name, View view );

    /**
     * Sets a description for the task.
     *
     * @param description
     */
    void setDescription( String description );
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


}
