/**
 * 
 */
package com.quinsoft.zeidon;

import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * @author DG
 *
 */
public class MaxCardinalityException extends ZeidonException
{
    private static final long serialVersionUID = 1L;

    /**
     * @param format
     * @param strings
     */
    public MaxCardinalityException( ViewEntity viewEntity )
    {
        super( "Entity violates max cardinality" );
        prependViewEntity( viewEntity );
        appendMessage( "Max number of child entities = %s", viewEntity.getMaxCardinality() );
    }
}
