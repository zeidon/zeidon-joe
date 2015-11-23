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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.MapMaker;
import com.quinsoft.zeidon.CacheMap;
import com.quinsoft.zeidon.ZeidonException;

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
     * @see com.quinsoft.zeidon.CacheMap#put(java.lang.Class, java.lang.Object)
     */
    @Override
    public <T> T put(Class<T> key, T value)
    {
        cache.putIfAbsent( key, value );
        return get( key );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.CacheMap#get(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Class<T> key)
    {
        return (T) cache.get( key );
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public <T> T getOrCreate( Class<T> key )
    {
        T value = (T) cache.get( key );
        if ( value == null )
        {
            try
            {
                // Get the first constructor.  We'll assume it's the no-args constructor.
                Constructor<?> ctor = key.getDeclaredConstructors()[0]; 
                ctor.setAccessible( true ); // So we can call private constructors.
                value = (T) ctor.newInstance();
                value = put( key, value );
            }
            catch ( Exception e )
            {
                throw ZeidonException.wrapException( e )
                                     .prependMessage( "If the class \"%s\" is an inner class it must be declared as static", key.getCanonicalName() );
            }
        }

        return value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T put( T value )
    {
        return put( (Class<T>) value.getClass(), value );
    }
}
