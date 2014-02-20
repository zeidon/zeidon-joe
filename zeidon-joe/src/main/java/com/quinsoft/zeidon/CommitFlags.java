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

package com.quinsoft.zeidon;

import java.util.EnumSet;

/**
 * Enum of commit flags.
 * 
 * @author DG
 *
 */
public enum CommitFlags
{
    /**
     * Don't commit OIs but drop any pessimistic locks.
     */
    fDROP_LOCKS_ONLY,
    
    /**
     * Force commit to be in its own transaction.
     */
    fFORCE_TRANSACTION,
    
    /**
     * Don't drop pessimistic locks
     */
    fKEEP_LOCKS,
    
    /**
     * Do not execute commit constraints.
     */
    fNO_CONSTRAINTS,

    /**
     * Do not update entity flags after commit.  This is intended to be used
     * internally by client-server processing to send new flags to client.
     */
    fNO_CLEANUP;
    
    public static final EnumSet<CommitFlags> NONE = EnumSet.noneOf( CommitFlags.class );
}
