/**
 * This file is part of the Zeidon Java Object Engine (Zeidon JOE).
 * 
 * Zeidon JOE is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Zeidon JOE is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Zeidon JOE. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Copyright 2009-2012 QuinSoft
 */

package com.quinsoft.zeidon.utils;

import java.util.ArrayList;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.LRUMap;

/**
 * An LRU cache that will automatically remove items that haven't been used for
 * a pre-determined amount of time.
 * 
 * @author dgc
 * 
 * @param <K>
 * @param <T>
 */
@Deprecated // Use Google's MapMaker instead.
public class TimedLruCache<K, T>
{
    private long   timeToLiveInMillis;

    private LRUMap cacheMap;

    private class CachedObject
    {
        private long lastAccessed;
        private T    value;

        protected CachedObject(T value)
        {
            this.value = value;
            lastAccessed = System.currentTimeMillis();
        }
    }

    public TimedLruCache(int maxItems)
    {
        this( 0, 0, maxItems );
    }

    public TimedLruCache(long timeToLiveInSeconds, final long timerIntervalInSeconds, int maxItems)
    {
        this.timeToLiveInMillis = timeToLiveInSeconds * 1000;

        cacheMap = new LRUMap( maxItems );

        if ( timeToLiveInMillis > 0 && timerIntervalInSeconds > 0 )
        {

            Thread t = new Thread( new Runnable()
            {
                @Override
                public void run()
                {
                    while ( true )
                    {
                        try
                        {
                            Thread.sleep( timerIntervalInSeconds * 1000 );
                        }
                        catch ( InterruptedException ex )
                        {
                        }

                        cleanup();
                        Thread.yield();
                    }
                }
            } );

            t.setDaemon( true );
            t.start();
        }
    }

    public void put(K key, T value)
    {
        synchronized ( cacheMap )
        {
            cacheMap.put( key, new CachedObject( value ) );
        }
    }

    @SuppressWarnings("unchecked")
    public T get(K key)
    {
        synchronized ( cacheMap )
        {
            CachedObject c = (CachedObject) cacheMap.get( key );

            if ( c == null )
                return null;
            
            c.lastAccessed = System.currentTimeMillis();
            return c.value;
        }
    }

    public void remove(K key)
    {
        synchronized ( cacheMap )
        {
            cacheMap.remove( key );
        }
    }

    public int size()
    {
        synchronized ( cacheMap )
        {
            return cacheMap.size();
        }
    }

    @SuppressWarnings("unchecked")
    public void cleanup()
    {

        // Determine a time when objects haven't been accessed in a while and
        // need
        // to be removed.
        long tooOld = System.currentTimeMillis() - timeToLiveInMillis;
        ArrayList<K> keysToDelete = null;

        synchronized ( cacheMap )
        {
            MapIterator itr = cacheMap.mapIterator();

            keysToDelete = new ArrayList<K>( ( cacheMap.size() / 2 ) + 1 );

            while ( itr.hasNext() )
            {
                K key = (K) itr.next();
                CachedObject c = (CachedObject) itr.getValue();

                if ( c != null )
                {
                    if ( c.lastAccessed < tooOld )
                        keysToDelete.add( key );
                    else
                        break; // All the rest of the objects are younger than
                               // time-to-live.
                }
            }

            for ( K k : keysToDelete )
                cacheMap.remove( k );
        }
    }
}