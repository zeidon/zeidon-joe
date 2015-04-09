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
 */
public class CopyAttributesFlagsBuilder
{
    private EntityInstance srcEntityInstance;
    private EntityInstance tgtEntityInstance;
    
    private EnumSet<SetMatchingFlags> flags = EnumSet.noneOf( SetMatchingFlags.class );
    
    private boolean copyKeys = false;
    private boolean copyNulls = false;
    private boolean copyWork = true;
    private boolean copyHidden = false;
    private boolean keepNonNull = true;
    private boolean copyPersistent = true;
    
    public int from( EntityInstance sourceEi )
    {
        srcEntityInstance = sourceEi;
        return copy();
    }
    
    public CopyAttributesFlagsBuilder to( EntityInstance targetEi )
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
    public CopyAttributesFlagsBuilder ignoreNull()
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
    public CopyAttributesFlagsBuilder overwriteNonNull()
    {
        flags.add( SetMatchingFlags.fSET_NOTNULL );
        return this;
    }
    
    public CopyAttributesFlagsBuilder copyKeys()
    {
        flags.add( SetMatchingFlags.fSET_KEYS );
        return this;
    }

    /**
     * @return the copyKeys
     */
    public boolean isCopyKeys()
    {
        return copyKeys;
    }

    /**
     * If set to true, then copy key attributes.
     * 
     * Default: false
     * 
     * @param copyKeys the copyKeys to set
     * 
     * @return this 
     */
    public CopyAttributesFlagsBuilder setCopyKeys( boolean copyKeys )
    {
        this.copyKeys = copyKeys;
        return this;
    }

    /**
     * @return the copyNulls
     */
    public boolean isCopyNulls()
    {
        return copyNulls;
    }

    /**
     * If set to true, then copy null attributes (will override non-null attributes
     * in the target entity instance).
     * 
     * Default: false
     * 
     * @param copyNulls the copyNulls to set
     * 
     * @return this 
     */
    public CopyAttributesFlagsBuilder setCopyNulls( boolean copyNulls )
    {
        this.copyNulls = copyNulls;
        return this;
    }

    /**
     * @return the copyWork
     */
    public boolean isCopyWork()
    {
        return copyWork;
    }

    /**
     * If set to true, then copy work attributes.
     * 
     * @param copyWork the copyWork to set
     * 
     * @return this 
     */
    public CopyAttributesFlagsBuilder setCopyWork( boolean copyWork )
    {
        this.copyWork = copyWork;
        return this;
    }

    public boolean isCopyHidden()
    {
        return copyHidden;
    }

    public void setCopyHidden( boolean copyHidden )
    {
        this.copyHidden = copyHidden;
    }

    public EntityInstance getSourceInstance()
    {
        return srcEntityInstance;
    }
    
    public int copy()
    {
        if ( srcEntityInstance == null )
            throw new ZeidonException( "Source Entity Instance was not specified" );
        
        if ( tgtEntityInstance == null )
            throw new ZeidonException( "Target Entity Instance was not specified" );
        
        tgtEntityInstance.copyAttributes( this );
        return 0;
    }

    public boolean isKeepNonNull()
    {
        return keepNonNull;
    }

    /**
     * If set to true, then non-null attributes in the target instance will NOT be
     * updated.
     * 
     * Default: false
     * 
     * @param keepNonNull
     * 
     * @return this
     */
    public CopyAttributesFlagsBuilder setKeepNonNull( boolean keepNonNull )
    {
        this.keepNonNull = keepNonNull;
        return this;
    }

    public boolean isCopyPersistent()
    {
        return copyPersistent;
    }

    public CopyAttributesFlagsBuilder setCopyPersistent( boolean copyPersistent )
    {
        this.copyPersistent = copyPersistent;
        return this;
    }
}
