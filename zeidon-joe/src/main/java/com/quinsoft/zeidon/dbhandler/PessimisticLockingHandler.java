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

package com.quinsoft.zeidon.dbhandler;

import java.util.Map;

import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.DropViewCleanup;
import com.quinsoft.zeidon.PessimisticLockingException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * Specifies the interface for locking OIs using pessimistic locking.
 *
 * @author DG
 *
 */
public interface PessimisticLockingHandler extends DropViewCleanup
{
    void initialize( ActivateOptions options, Map<EntityDef, QualEntity> qualMap  );

    /**
     * For situations that require it, acquire a global lock that prevents
     * other tasks from performing pessimistic locking on this view.
     *
     * @param view View to lock
     *
     * @throws PessimisticLockingException
     */
    void acquireGlobalLock( View view ) throws PessimisticLockingException;

    /**
     * Release the global locks needed for this view.
     *
     * @param view
     * @throws PessimisticLockingException
     */
    void releaseGlobalLock( View view );

    /**
     * Acquires the pessimistic locks for a view.  This is called after the root
     * entities are loaded but before the rest of the OI.
     *
     * @param view View to lock
     *
     * @throws PessimisticLockingException
     */
    void acquireRootLocks( View view ) throws PessimisticLockingException;

    /**
     * Acquires the pessimistic locks for a view.  This is called after the entire
     * OI has been loaded.  This call is intended for dbhandlers that can't have
     * more than one open connection at once.  This will be called after the
     * connection that loaded the OI has been closed.
     *
     * @param view View to lock
     *
     * @throws PessimisticLockingException
     */
    void acquireOiLocks( View view ) throws PessimisticLockingException;

    /**
     * Release the pessimistic locks for this view.
     *
     * @param view
     */
    void releaseLocks( View view );

    /**
     * Drops any outstanding locks on OIs specified by the activate options.  Intended to
     * be used in situations where the OI no longer exists in memory (like REST servers).
     *
     * @param activateOptions Qualification for OIs that will have their locks dropped.
     */
    void dropOutstandingLocks();
}
