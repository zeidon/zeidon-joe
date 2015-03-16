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

package com.quinsoft.zeidon;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Zeidon objects that may be locked for exclusive access implement this interface.
 * 
 * @author DG
 *
 */
public interface Lockable
{
    /**
     * @return Returns a reentrant read-write lock that can be used to control access
     * to different Zeidon objects.
     */
    ReentrantReadWriteLock getLock();
}
