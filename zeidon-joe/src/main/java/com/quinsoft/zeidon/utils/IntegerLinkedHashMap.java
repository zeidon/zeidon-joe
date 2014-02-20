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

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.collect.ForwardingMap;

/**
 * Convenience integer map that will default values to 0.
 * 
 * @author DG
 *
 */
public class IntegerLinkedHashMap<K> extends ForwardingMap<K, Integer>
{
    private final Integer ZERO = 0;
    private final LinkedHashMap<K, Integer> map = new LinkedHashMap<K, Integer>();
    
    /* (non-Javadoc)
     * @see java.util.LinkedHashMap#get(java.lang.Object)
     */
    @Override
    public Integer get( Object key )
    {
        Integer i = super.get( key );
        if ( i == null )
            return ZERO;
        
        return i;
    }
    
    public Integer increment( K key )
    {
        Integer i = get( key );
        i += 1;
        put( key, i );
        return i;
    }

    /* (non-Javadoc)
     * @see com.google.common.collect.ForwardingMap#delegate()
     */
    @Override
    protected Map<K, Integer> delegate()
    {
        return map;
    }
}
