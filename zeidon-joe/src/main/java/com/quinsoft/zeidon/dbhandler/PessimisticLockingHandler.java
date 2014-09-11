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

package com.quinsoft.zeidon.dbhandler;

import java.util.Collection;

import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.PessimisticLockingException;
import com.quinsoft.zeidon.View;

/**
 * Specifies the interface for locking OIs using pessimistic locking.
 *
 * @author DG
 *
 */
public interface PessimisticLockingHandler
{
    /**
     * Performs any initialization necessary for acquiring pessimistic locks.
     * Called at the beginning of activation.
     *
     * @param options
     * @throws PessimisticLockingException
     */
    void initialize( ActivateOptions options ) throws PessimisticLockingException;

    /**
     * Called at the end of activation to release any resources.
     */
    void cleanup();

    /**
     * Acquires the pessimistic locks for a view.  This is called after the root
     * entities are loaded.
     *
     * @param view View to lock
     *
     * @throws PessimisticLockingException
     */
    void acquireLocks( View view ) throws PessimisticLockingException;

    /**
     * Release the pessimistic locks for the views.  This is called when a view is
     * dropped and is not part of activation.
     *
     * @param views List of views to release.
     */
    void releaseLocks( Collection<View> views );

    /**
     * Release the pessimistic locks for this view.  This is called when a view is
     * dropped and is not part of activation.
     *
     * @param view
     */
    void releaseLocks( View view );
}
