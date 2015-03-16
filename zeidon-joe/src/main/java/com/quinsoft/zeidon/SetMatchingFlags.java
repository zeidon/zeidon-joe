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

import java.util.EnumSet;

/**
 * Flags used for calling setMatchingAttributesByName.
 * 
 * @author dgc
 *
 */
public enum SetMatchingFlags
{
    fSET_NOTNULL,
    fSET_KEYS,
    fSET_SRCNOTNULL;
    
    public static final EnumSet<SetMatchingFlags> DEFAULT = EnumSet.noneOf( SetMatchingFlags.class );
    public static final EnumSet<SetMatchingFlags> SET_NOTNULL = EnumSet.of( fSET_NOTNULL );
    public static final EnumSet<SetMatchingFlags> SET_KEYS = EnumSet.of( fSET_KEYS );
    public static final EnumSet<SetMatchingFlags> SET_SRCNOTNULL = EnumSet.of( fSET_SRCNOTNULL );
    public static final EnumSet<SetMatchingFlags> SET_ALL  = EnumSet.of( fSET_KEYS, fSET_NOTNULL );
}
