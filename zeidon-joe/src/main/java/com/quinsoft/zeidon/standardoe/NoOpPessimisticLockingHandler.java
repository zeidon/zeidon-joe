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
package com.quinsoft.zeidon.standardoe;

import com.quinsoft.zeidon.PessimisticLockingException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.dbhandler.PessimisticLockingHandler;

/**
 * A locking handler that does nothing.
 *
 */
public class NoOpPessimisticLockingHandler implements PessimisticLockingHandler
{
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.DropViewCleanup#viewDropped(com.quinsoft.zeidon.View)
     */
    @Override
    public void viewDropped( View view )
    {
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.PessimisticLockingHandler#acquireGlobalLock(com.quinsoft.zeidon.View)
     */
    @Override
    public void acquireGlobalLock( View view ) throws PessimisticLockingException
    {
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.PessimisticLockingHandler#releaseGlobalLock(com.quinsoft.zeidon.View)
     */
    @Override
    public void releaseGlobalLock( View view )
    {
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.PessimisticLockingHandler#acquireLocks(com.quinsoft.zeidon.View)
     */
    @Override
    public void acquireRootLocks( View view ) throws PessimisticLockingException
    {
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.PessimisticLockingHandler#releaseLocks(com.quinsoft.zeidon.View)
     */
    @Override
    public void releaseLocks( View view )
    {
    }

    @Override
    public void acquireOiLocks( View view ) throws PessimisticLockingException
    {
    }

    @Override
    public void dropOutstandingLocks()
    {
    }
}
