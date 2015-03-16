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
 * Defines the types of OI locking to prevent applications from updating the
 * same data in two different threads.
 * 
 * @author DG
 *
 */
public enum LockingLevel
{
    NONE( 0 ),
    OPTIMISTIC_NOINC( 1 ),
    OPTIMISTIC_CMPRALL( 2 ),
    PESSIMISTIC( 3, true ),
    PESSIMISTIC_WITHREAD( 4, true ),
    PESSIMISTIC_NOREAD( 5, true );
    
    /**
     * A static hashmap to convert numbers to enum.
     */
    private final static Map<Integer, LockingLevel> NUMBER_LOOKUP = new HashMap<Integer, LockingLevel>();
    
    static 
    {
        for ( LockingLevel level : LockingLevel.values() )
        {
            NUMBER_LOOKUP.put( level.getNumber(), level );
        }
    }

    private final int     number;
    
    /**
     * True if this is any one of the pessimistic locks.
     */
    private final boolean isPessimisticLock;

    private LockingLevel( int number )
    {
        this( number, false );
    }
    
    private LockingLevel( int number, boolean isPessimistic )
    {
        this.number = number;
        isPessimisticLock = isPessimistic;
    }
    
    /**
     * This returns the numeric value of the locking level as stored in the XOD.
     * 
     * @return the number
     */
    public int getNumber()
    {
        return number;
    }
    
    /**
     * @param number
     * @return Returns the LockingLevel associated with 'number'.
     */
    public static LockingLevel lookup( int number )
    {
        LockingLevel level = NUMBER_LOOKUP.get( number );
        if ( level == null )
            throw new ZeidonException( "Invalid LockingLevel number: %d", number );
        
        return level;
    }

    /**
     * @return the isPessimisticLock
     */
    public boolean isPessimisticLock()
    {
        return isPessimisticLock;
    }
}
