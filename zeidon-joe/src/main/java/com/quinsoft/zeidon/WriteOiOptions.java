/**
 * 
 */
package com.quinsoft.zeidon;

import java.util.EnumSet;

/**
 * @author dg
 *
 */
public class WriteOiOptions
{
    private EnumSet<WriteOiFlags> flags = EnumSet.noneOf( WriteOiFlags.class );

    public EnumSet<WriteOiFlags> getFlags()
    {
        return flags;
    }

    public WriteOiOptions setFlags( EnumSet<WriteOiFlags> flags )
    {
        this.flags = flags;
        return this;
    }
    
    public WriteOiOptions setIncremental()
    {
        flags.add( WriteOiFlags.fINCREMENTAL );
        return this;
    }
}
