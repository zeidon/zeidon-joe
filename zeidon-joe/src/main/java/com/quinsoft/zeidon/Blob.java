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

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * A simple wrapper around byte[] so that it can be treated as a comparable object.
 * @author DG
 *
 */
public final class Blob implements Comparable<Object>, Serializable
{
    private static final long serialVersionUID = 1L;

    private final byte[] bytes;

    public Blob(byte[] bytes)
    {
        super();
        this.bytes = bytes;
    }

    @Override
    public int compareTo(Object o)
    {
        byte[] compare;

        if ( o == null )
            return 1;

        // When o == "", then compare = (byte[]) o fails.
        if ( o instanceof String && ((String) o).isEmpty() )
        		return 1;

        if ( o instanceof Blob )
            compare = ((Blob)o).getBytes();
        else
            compare = (byte[]) o;

        int minLth = Math.min( bytes.length, compare.length );
        for ( int i = 0; i < minLth; i++ )
        {
            if ( bytes[i] < compare[i] )
                return -1;

            if ( bytes[i] > compare[i] )
                return 1;
        }

        if ( bytes.length == compare.length )
            return 0;

        if ( bytes.length > compare.length )
            return 1;

        return -1;
    }

    @Override
    public boolean equals( Object o )
    {
        return compareTo( o ) == 0;
    }

    public byte[] getBytes()
    {
        return bytes;
    }

    public long getSize()
    {
        if ( bytes == null )
            return 0;

        return bytes.length;
    }

    @Override
    public String toString()
    {
        String data;
		try {
			data = new String( bytes, 0, Math.min( bytes.length, 10 ), "UTF8" );
	        return String.format( "Blob data: %d bytes (%s)", bytes.length, data );
		} catch (UnsupportedEncodingException e) {
            throw ZeidonException.wrapException( e );
		}
    }
}
