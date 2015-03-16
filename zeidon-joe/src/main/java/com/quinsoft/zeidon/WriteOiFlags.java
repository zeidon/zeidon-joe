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
 * @author DG
 *
 */
public enum WriteOiFlags
{
    INCREMENTAL,
    ENTITY_TAGS,
    ENTITY_KEYS,
    COMPRESSED,
    KEYS_ONLY;

    static final long CONTROL_INCREMENTAL = 0x00000001;
    static final long CONTROL_ENTITY_TAGS = 0x00000002;
    static final long CONTROL_ENTITY_KEYS = 0x00000004;
    static final long CONTROL_KEYS_ONLY   = 0x00000008;

    public static EnumSet<WriteOiFlags> empty()
    {
        return EnumSet.noneOf( WriteOiFlags.class );
    }

    public static EnumSet<WriteOiFlags> convertLongFlags( long control )
    {
        EnumSet<WriteOiFlags> flags = empty();
        if ( ( control & CONTROL_INCREMENTAL ) != 0 )
            flags.add( WriteOiFlags.INCREMENTAL );

        if ( ( control & CONTROL_ENTITY_KEYS ) != 0 )
            flags.add( WriteOiFlags.ENTITY_KEYS );

        if ( ( control & CONTROL_ENTITY_TAGS) != 0 )
            flags.add( WriteOiFlags.ENTITY_TAGS );

        if ( ( control & CONTROL_KEYS_ONLY ) != 0 )
            flags.add( WriteOiFlags.KEYS_ONLY);

        return flags;
    }
}
