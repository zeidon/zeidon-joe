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

/**
 * <p>
 * Cache maps are a way to store objects in Zeidon objects in a thread-safe way.  The
 * life of the CacheMap is tied to the implementing object.  For example, a value stored
 * in the CacheMap of a view will live until the View is garbage collected.
 * The key is a class object to help reduce the number of possible key collisions.
 * </p>
 * For example, application code could cache a value in a task like this:
 * <pre><code>
 * Task task = ...
 * MyCache value = task.getCacheMap( MyCache.class );
 * if ( value == null ) {
 *     value = ...
 *     value = task.putCacheMap( MyCache.class, value );
 * }
 * </code></pre>
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

    /**
     * Retrieves a value from the cache map.
     *
     * @param key the map key.
     * @return the value stored in the cache map.
     */
    <T> T getCacheMap( Class<T> key );
}
