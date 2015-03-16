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

/**
 * @author DG
 * 
 */
public enum CursorResult
{
    NULL(-3 ),
    UNDEFINED( -2 ),
    UNCHANGED( -1 ),
    SET( 0 ),
    SET_NEWPARENT( 1 ),
    SET_RECURSIVE_CHILD( 2 );

    /**
     * This represents the integer code used in the C object engine.  It's intended
     * to be used by the VML generator.
     */
    private final int coeCode;
    
    private CursorResult( int vmlCode )
    {
        this.coeCode = vmlCode;
    }
    
    /**
     * Returns true if the cursor is set to a value, regardless if
     * it's set under a new parent.
     * 
     * @return
     */
    public boolean isSet()
    {
        return coeCode >= 0;
    }
    
    public boolean isSetNewParent()
    {
        return coeCode >= 1;
    }
    
    public boolean isSetRecursiveChild()
    {
        return coeCode >= 2;
    }
    
    /**
     * Return the C OE equivalent integer value.
     * 
     * @return
     */
    public int toInt()
    {
        return coeCode;
    }
}
