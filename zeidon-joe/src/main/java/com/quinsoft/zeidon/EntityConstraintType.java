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
 * List of possible EntityConstraint types.
 *
 */
public enum EntityConstraintType
{
    ACCEPT( 1 ),
    CANCEL( 2 ),
    CREATE( 3 ),
    DELETE( 4 ),
    EXCLUDE( 5 ),
    INCLUDE( 6 );

    /**
     * This represents the integer code used in the C object engine.  It's intended
     * to be used by the VML generator.
     */
    private final int coeCode;

    private EntityConstraintType( int vmlCode )
    {
        this.coeCode = vmlCode;
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
