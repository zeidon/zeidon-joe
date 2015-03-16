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

package com.quinsoft.zeidon.objectdefinition;

import java.util.HashMap;
import java.util.Map;

import com.quinsoft.zeidon.ZeidonException;

/**
 * Defines the valid domain types.
 * 
 * @author DG
 *
 */
public enum DomainType
{
    TABLE( 'T' ),
    ALGORITHM( 'A' ),
    EXPRESSION( 'E' ),
    FORMAT( 'F' );

    private final char code;

    private DomainType( char code )
    {
        this.code = code;
    }
    public char getCodeChar()
    {
        return code;
    }
    
    private static Map<Character, DomainType> hash = new HashMap<Character, DomainType>();
    static
    {
        for ( DomainType type : DomainType.values() )
        {
            hash.put( type.code, type );
        }
    }
    
    public static DomainType mapCode( char code )
    {
        DomainType type = hash.get( code );
        if ( type == null )
            throw new ZeidonException("DomainType code of '%c' is undefined.", code );
        
        return type;
    }
}
