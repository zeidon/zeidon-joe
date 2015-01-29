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

/**
 * Interface that defines methods used to commit an object to a DB.  DB handlers
 * will implement this interface.
 *
 */
public interface Committer
{
    /**
     * Initialize the committer to commit.
     * @param task the owning task.
     * @param viewList the list of views that will be committed.  When applicable all
     * views should be committed in the same transaction.
     * @param options commit options.
     */
    void init( Task task, List<? extends View> viewList, CommitOptions options );

    /**
     * Commit the views specified in the init() call.
     *
     * @return the list of committed views that correspond to the original views.
     * Usually they will be the same views but it is not guaranteed.
     */
    List<? extends View> commit();
}
