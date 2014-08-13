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
    
    public SetMatchingFlagsBuilder from( EntityInstance sourceEi )
    {
        srcEntityInstance = sourceEi;
        return this;
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
