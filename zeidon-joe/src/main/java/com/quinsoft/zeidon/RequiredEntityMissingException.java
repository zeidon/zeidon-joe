package com.quinsoft.zeidon;

import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * @author DG
 *
 */
public class RequiredEntityMissingException extends ZeidonException
{
    private static final long serialVersionUID = 1L;

    /**
     * @param format
     * @param strings
     */
    public RequiredEntityMissingException( ViewEntity viewEntity )
    {
        super( "Required child entity has no instances." );
        prependViewEntity( viewEntity );
        prependViewOd( viewEntity.getViewOd() );
    }

}
