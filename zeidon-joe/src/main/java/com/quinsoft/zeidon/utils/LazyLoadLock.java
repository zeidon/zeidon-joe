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

package com.quinsoft.zeidon.utils;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.quinsoft.zeidon.Lockable;

/**
 * A simple class that implements Lockable by lazy-loading a lock.
 * 
 * @author DG
 *
 */
public class LazyLoadLock implements Lockable
{
    private ReentrantReadWriteLock lock;

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Lockable#getLock()
     */
    @Override
    public synchronized ReentrantReadWriteLock getLock()
    {
        if ( lock == null )
            lock = new ReentrantReadWriteLock();
        
        return lock;
    }
}
