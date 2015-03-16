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
 *
 * @author DG
 *
 */
public enum CreateEntityFlags
{
    /**
     * Do not spawn the created entity.  This is intended to be used during activates, either
     * from the DB or files, or when cloning OIs.
     */
    fNO_SPAWNING,

    /**
     * Ignore the max cardinality when creating entities.  Used to make debugging easier.
     */
    fIGNORE_MAX_CARDINALITY,

    /**
     * Normally the create sets the Update flag for the OI.  This flag prevents the
     * Update flag from changing.
     */
    fDONT_UPDATE_OI,

    /**
     * Ignore the CREATE flag permissions.  Intended to be used by the DBHandler to create
     * entities that are loaded from the DB.
     */
    fIGNORE_PERMISSIONS,

    /**
     * Don't initialize attributes.
     */
    fDONT_INITIALIZE_ATTRIBUTES,

    /**
     * Indicates this entity is being created by the DBHandler.
     */
    fDBHANDLER;

    public static final EnumSet<CreateEntityFlags> DEFAULT     = EnumSet.noneOf( CreateEntityFlags.class );
    public static final EnumSet<CreateEntityFlags> NO_SPAWNING = EnumSet.of( CreateEntityFlags.fNO_SPAWNING );
}
