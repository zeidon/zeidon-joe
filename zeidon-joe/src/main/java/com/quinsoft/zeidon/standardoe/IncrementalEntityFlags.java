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

package com.quinsoft.zeidon.standardoe;

import java.util.EnumSet;

/**
 * Enum of IncrementalEntity flags.  Used to explicitly set the incremental
 * flags for an entity instance.
 *
 */
public enum IncrementalEntityFlags
{
    fCREATED,
    fUPDATED,
    fDELETED,
    fEXCLUDED,
    fINCLUDED,
    fHIDDEN;

    public static final EnumSet<IncrementalEntityFlags> NONE = EnumSet.noneOf( IncrementalEntityFlags.class );
    public static final EnumSet<IncrementalEntityFlags> CREATED = EnumSet.of( fCREATED );
    public static final EnumSet<IncrementalEntityFlags> UPDATED = EnumSet.of( fUPDATED );
    public static final EnumSet<IncrementalEntityFlags> DELETED = EnumSet.of( fDELETED );
    public static final EnumSet<IncrementalEntityFlags> EXCLUDED = EnumSet.of( fEXCLUDED );
    public static final EnumSet<IncrementalEntityFlags> INCLUDED = EnumSet.of( fINCLUDED );
    public static final EnumSet<IncrementalEntityFlags> HIDDEN = EnumSet.of( fHIDDEN );
}
