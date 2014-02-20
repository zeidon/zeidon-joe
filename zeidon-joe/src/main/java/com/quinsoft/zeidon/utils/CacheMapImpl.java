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

package com.quinsoft.zeidon.utils;

import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.MapMaker;
import com.quinsoft.zeidon.CacheMap;

/**
 * Implementation of CacheMap using Google Guava MapMaker.
 *
 * @author DG
 *
 */
public class CacheMapImpl implements CacheMap
{
    private final ConcurrentMap<Class<?>, Object> cache = new MapMaker().concurrencyLevel( 2 ).makeMap();

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.CacheMap#putCacheMap(java.lang.Class, java.lang.Object)
     */
    @Override
    public <T> T putCacheMap(Class<T> key, T value)
    {
        cache.putIfAbsent( key, value );
        return getCacheMap( key );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.CacheMap#getCacheMap(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCacheMap(Class<T> key)
    {
        return (T) cache.get( key );
    }
}
