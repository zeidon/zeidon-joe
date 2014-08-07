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
    fASYNCHRONOUS;

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
}
