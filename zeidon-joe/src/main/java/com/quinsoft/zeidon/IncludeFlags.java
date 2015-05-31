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
 * List of options available when including a subobject.
 *
 */
public enum IncludeFlags
{
    /**
     * Indicates that the Include has been called as part of an activate.  This
     * will disable permission validation and spawning.  This allows the DBHandler
     * to create entities from a cache using include.
     */
    FROM_ACTIVATE;

    public static final EnumSet<IncludeFlags> EMPTY = EnumSet.noneOf( IncludeFlags.class );
}
