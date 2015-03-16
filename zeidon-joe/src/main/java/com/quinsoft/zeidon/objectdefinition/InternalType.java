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
/**
 * 
 */
package com.quinsoft.zeidon.objectdefinition;

import java.util.HashMap;
import java.util.Map;

import com.quinsoft.zeidon.ZeidonException;

/**
 * Indicates the internal type for a domain and is read from the .XDM.  Mostly used for
 * legacy interaction with c OE.  The blob type is used when reading/writing portable files.
 * 
 * @author DG
 */
public enum InternalType
{
    INTEGER( 'L' ),
    DECIMAL( 'M' ),
    STRING( 'S' ),
    DATE( 'T' ),
    BLOB( 'B' );
    
    private final char code;
    
    private InternalType( char code )
    {
        this.code = code;
    }
    
    public char getCodeChar()
    {
        return code;
    }
    
    private static Map<Character, InternalType> hash = new HashMap<Character, InternalType>();
    static
    {
        for ( InternalType type : InternalType.values() )
        {
            hash.put( type.code, type );
        }
    }
    
    public static InternalType mapCode( String code )
    {
        InternalType type = hash.get( code.charAt( 0 ) );
        if ( type == null )
            throw new ZeidonException("InternalType code of '%s' is undefined.", code );
        
        return type;
    }
}