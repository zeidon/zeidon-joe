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
package com.quinsoft.zeidon;

import java.util.EnumSet;

/**
 * Convenience class to build flags for setMatchingAttributesByName.
 * 
 * @author dgc
 *
 */
public class SetMatchingFlagsBuilder
{
    private EntityInstance srcEntityInstance;
    private EntityInstance tgtEntityInstance;
    
    private EnumSet<SetMatchingFlags> flags = EnumSet.noneOf( SetMatchingFlags.class );
    
    public int from( EntityInstance sourceEi )
    {
        srcEntityInstance = sourceEi;
        return copy();
    }
    
    public SetMatchingFlagsBuilder to( EntityInstance targetEi )
    {
        tgtEntityInstance = targetEi;
        return this;
    }

    /**
     * Ignore source attributes if they are null.  This only applies if overwriteNonNull()
     * option is set.
     * 
     * @return
     */
    public SetMatchingFlagsBuilder ignoreNull()
    {
        flags.add( SetMatchingFlags.fSET_SRCNOTNULL );
        return this;
    }
    
    /**
     * This causes non-null attributes in target EI to be overwritten.
     * Default processing is to not overwrite non-null values.
     * 
     * @return
     */
    public SetMatchingFlagsBuilder overwriteNonNull()
    {
        flags.add( SetMatchingFlags.fSET_NOTNULL );
        return this;
    }
    
    public SetMatchingFlagsBuilder copyKeys()
    {
        flags.add( SetMatchingFlags.fSET_KEYS );
        return this;
    }

    public int copy()
    {
        if ( srcEntityInstance == null )
            throw new ZeidonException( "Source Entity Instance was not specified" );
        
        if ( tgtEntityInstance == null )
            throw new ZeidonException( "Target Entity Instance was not specified" );
        
        return tgtEntityInstance.setMatchingAttributesByName( srcEntityInstance, flags );
    }
}
