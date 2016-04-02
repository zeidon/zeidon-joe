/**
 * 
 */
package com.quinsoft.zeidon.utils;

import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * A class used to build consistent key strings.
 *
 */
public class KeyStringBuilder
{
    private final StringBuilder keyString = new StringBuilder();
    
    /**
     * Keeps track of whether we've added anything to the builder.
     */
    private boolean first = true;
    
    /**
     * Keeps track if any key added is non-null.
     */
    private boolean nonNull = false;
    
    public KeyStringBuilder()
    {
    }
    
    private KeyStringBuilder appendString( String str )
    {
        if ( first )
            first = false;
        else
            keyString.append( "|" );
        
        keyString.append( str );
        
        return this;
    }
    
    public KeyStringBuilder appendKey( String str )
    {
        return appendString( str );
    }
    
    public KeyStringBuilder appendKey( Task task, AttributeDef attributeDef, Object internalValue )
    {
        Domain domain = attributeDef.getDomain();
        String str = domain.convertToString( task, attributeDef, internalValue );
        
        return appendString( str );
    }
    
    public KeyStringBuilder appendKey( AttributeInstance attr )
    {
        if ( ! attr.getAttributeDef().isKey() )
            throw new ZeidonException( "Adding non-key to a KeyStringBuilder" )
                        .prependAttributeDef( attr.getAttributeDef() );
        
        if ( ! attr.isNull() )
            nonNull = true;
        
        return appendString( attr.getString() );
    }

    public boolean isNull()
    {
        return ! nonNull;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return keyString.toString();
    }
}
