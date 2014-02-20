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

    Copyright 2009-2012 QuinSoft
 */

package com.quinsoft.zeidon;

/**
 * Cache maps are a way for Zeidon logic to store task-specific objects
 * in Zeidon objects.  Each CacheMap has a user cache that can store
 * any object.  The key is a class object to help reduce the number of
 * possible key collisions.
 *
 * @author DG
 *
 */
public interface CacheMap
{
    /**
     * putTaskCache puts 'value' into the cache.  If the value already exists in the
     * cache for the specified key then 'value' is *not* added and the current value
     * from the cache map is returned.
     *
     * @param <T>
     * @param key
     * @param value
     * @return
     */
    <T> T putCacheMap( Class<T> key, T value );
    <T> T getCacheMap( Class<T> key );
}
