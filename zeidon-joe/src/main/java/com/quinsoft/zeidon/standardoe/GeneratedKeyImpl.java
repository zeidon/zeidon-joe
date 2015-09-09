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

import com.quinsoft.zeidon.GeneratedKey;
import com.quinsoft.zeidon.ZeidonException;

/**
 * Standard implementation of a GeneratedKey.
 */
public class GeneratedKeyImpl implements GeneratedKey
{
    private final    Comparable<Object>  nativeValue;
    private volatile String stringValue;

    public GeneratedKeyImpl( Comparable<Object> value )
    {
        nativeValue = value;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.GeneratedKey#isNull()
     */
    @Override
    public boolean isNull()
    {
        return nativeValue == null;
    }

    @Override
    public String getString()
    {
        if ( isNull() )
            throw new ZeidonException( "Attempting to convert null GeneratedKey to a string" );

        if ( stringValue == null )
            stringValue = nativeValue.toString();

        return stringValue;
    }

    @Override
    public String toString()
    {
        if ( isNull() )
            return "Key: (null)";

        if ( stringValue == null )
            stringValue = nativeValue.toString();

        return "Key: " + stringValue;
    }

    @Override
    public Object getNativeValue()
    {
        return nativeValue;
    }

    @Override
    public int hashCode()
    {
        if ( nativeValue == null )
            return 0;
        
        return nativeValue.hashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( nativeValue == null )
        {
            if ( obj == null )
                return true;
            else
                return false;
        }
        
        return nativeValue.equals( obj );
    }

    @Override
    public int compareTo( Object o )
    {
        if ( o instanceof GeneratedKeyImpl )
            o = ((GeneratedKeyImpl) o).getNativeValue();
        
        return nativeValue.compareTo( o );
    }
}
