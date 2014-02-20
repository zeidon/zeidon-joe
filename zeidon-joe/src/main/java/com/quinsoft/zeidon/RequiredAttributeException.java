/**
 * 
 */
package com.quinsoft.zeidon;

import com.quinsoft.zeidon.objectdefinition.ViewAttribute;

/**
 * Thrown by validateSubobject(), this indicates a required attribute is null.
 * 
 * @author DG
 *
 */
public class RequiredAttributeException extends ZeidonException
{
    private static final long serialVersionUID = 1L;
    
    public RequiredAttributeException( ViewAttribute viewAttrib )
    {
        super( "Required attribute %s is null.", viewAttrib.getName() );
        prependViewAttribute( viewAttrib );
    }
}
