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

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Enum of Activate flags.
 *
 * @author DG
 *
 */
public enum ActivateFlags
{
    fSINGLE,
    fMULTIPLE,
    fROOT_ONLY,
    fREAD_ONLY,
    fIGNORE_ENTITY_ERRORS,
    fIGNORE_ATTRIB_ERRORS,
    fIGNORE_JOINS,
    fIGNORE_ACTIVATE_CONSTRAINTS,
    fINCLUDE_LAZYLOAD,
    fASYNCHRONOUS,

    /**
     * If set, then we won't load all EntityDef instances in one SELECT statement.
     */
    fIGNORE_LOAD_OPTIMIZATION;

    public static final EnumSet<ActivateFlags> ROOT_ONLY = EnumSet.of( fROOT_ONLY );
    public static final EnumSet<ActivateFlags> ROOT_ONLY_MULTIPLE = EnumSet.of( fROOT_ONLY, fMULTIPLE );
    public static final EnumSet<ActivateFlags> MULTIPLE = EnumSet.of( fMULTIPLE );
    public static final EnumSet<ActivateFlags> SINGLE = EnumSet.of( fSINGLE );
    public static final EnumSet<ActivateFlags> IGNORE_ENTITY_ERRORS = EnumSet.of( fIGNORE_ENTITY_ERRORS );
    public static final EnumSet<ActivateFlags> IGNORE_ATTRIB_ERRORS = EnumSet.of( fIGNORE_ATTRIB_ERRORS );
    public static final EnumSet<ActivateFlags> IGNORE_ERRORS = EnumSet.of( fIGNORE_ENTITY_ERRORS, fIGNORE_ATTRIB_ERRORS );
    public static final EnumSet<ActivateFlags> IGNORE_JOINS = EnumSet.of( fIGNORE_JOINS );
    public static final EnumSet<ActivateFlags> ASYNCHRONOUS = EnumSet.of( fASYNCHRONOUS );
    public static final EnumSet<ActivateFlags> MULTIPLE_IGNORE_ERRORS = EnumSet.of( fMULTIPLE, fIGNORE_ENTITY_ERRORS, fIGNORE_ATTRIB_ERRORS );
    public static final EnumSet<ActivateFlags> SINGLE_IGNORE_ERRORS = EnumSet.of( fSINGLE, fIGNORE_ENTITY_ERRORS, fIGNORE_ATTRIB_ERRORS );

    /**
     * Deserialize a string into an EnumSet<ActivateFlags>
     *
     * @param str
     * @return
     */
    public static EnumSet<ActivateFlags> deserialize( String str )
    {
        EnumSet<ActivateFlags> returnSet = EnumSet.noneOf( ActivateFlags.class );
        String[] tokens = str.split( "[\\[\\], ]" );
        for ( String token : tokens )
        {
            if ( StringUtils.isBlank( token ) )
                continue;

            try
            {
                returnSet.add( ActivateFlags.valueOf( token ) );
            }
            catch ( Exception e )
            {
                throw ZeidonException.wrapException( e ).appendMessage( "String = '%s'", token );
            }
        }

        return returnSet;
    }

    private static final int zSINGLE                 = 0;          // 0x00000000L
    private static final int zAPPLICATION            = 4;          // 0x00000004L
    private static final int zMULTIPLE               = 256;        // 0x00000100L
    private static final int zIGNORE_ENTITY_ERRORS   = 4096;       // 0x00001000L
    private static final int zIGNORE_ATTRIB_ERRORS   = 8192;       // 0x00002000L
    private static final int zIGNORE_ERRORS          = 12288;      // 0x00003000L
    private static final int zSINGLE_FOR_UPDATE      = 67108864;   // 0x04000000L
    private static final int zACTIVATE_ROOTONLY      = 268435456;  // 0x10000000L
    private static final int zACTIVATE_ROOTONLY_MULTIPLE = 268435712;  // 0x10000000L + 0x00000100L
    private static final int zIGNORE_JOINS           = 1048576;    // 0x00100000L
    private static final int zASYNCHRONOUS           = 2097152;    // 0x00200000L

    /**
     * A map to convert C-style activate flags (int) to JOE-style (enum).
     */
    private static final Map<Integer, EnumSet<ActivateFlags>> ACTIVATE_CONTROL =
        Collections.unmodifiableMap( new HashMap<Integer, EnumSet<ActivateFlags>>() {
        private static final long serialVersionUID = 2L;
    {
        put( zSINGLE, ActivateFlags.SINGLE );
        put( zMULTIPLE, ActivateFlags.MULTIPLE );
        put( zACTIVATE_ROOTONLY_MULTIPLE, ActivateFlags.ROOT_ONLY_MULTIPLE );
        put( zACTIVATE_ROOTONLY, ActivateFlags.ROOT_ONLY );
        put( zIGNORE_ENTITY_ERRORS, ActivateFlags.IGNORE_ENTITY_ERRORS );
        put( zIGNORE_ATTRIB_ERRORS, ActivateFlags.IGNORE_ATTRIB_ERRORS );
        put( zIGNORE_ERRORS, ActivateFlags.IGNORE_ERRORS );
        put( zSINGLE_FOR_UPDATE, ActivateFlags.SINGLE );
        put( zIGNORE_JOINS, ActivateFlags.IGNORE_JOINS );
        put( zASYNCHRONOUS, ActivateFlags.ASYNCHRONOUS );

        // Ignore zAPPLICATION whan paired with SINGLE
        put( zSINGLE + zAPPLICATION, ActivateFlags.SINGLE );
    }} );

    // Convert C-style write flags to JOE style.
    public static EnumSet<ActivateFlags> convertLongFlags( Integer control )
    {
        if ( ACTIVATE_CONTROL.containsKey( control ) )
            return ACTIVATE_CONTROL.get( control );

        throw new ZeidonException( "Unknown control for converting to flags: %d", control );
    }
}
