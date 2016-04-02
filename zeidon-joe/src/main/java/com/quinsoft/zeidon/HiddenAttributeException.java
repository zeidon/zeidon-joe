/**
 *
 */
package com.quinsoft.zeidon;

import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * This exception is thrown when an application attempts to reference a hidden
 * attribute by name.  Hidden attributes can only be referenced via AttributeDef.<br/>
 * <br/>
 * <strong>Java example:</strong><br/><br/>
 *
 * Instead of
 * <pre>
 * {@code
 * attrInstance = view.cursor( "MyEntity" ).getAttribute( "MyHiddenEntity" );
 * }
 * </pre>
 * Use
 * <pre>
 * {@code
 * AttributeDef attributeDef = view.cursor( "MyEntity" ).getEntityDef().getAttribute( "MyHiddenAttribute" );
 * attrInstance = view.cursor( "MyEntity" ).getAttribute( attributeDef );
 * }
 * </pre>
 *
 * <strong>Scala example:</strong><br/>
 * <br/>
 * Instead of
 * <pre>
 * {@code
 * val attrInstance = view.MyEntity.MyHiddenAttribute
 * }
 * </pre>
 * Use
 * <pre>
 * {@code
 * val attrDef = request.MyEntity.entityDef.getAttribute( "MyHiddenAttribute" )
 * val attrInstance = request.MyEntity.getAttribute( attrDef )
 * }
 * </pre>
 */
public class HiddenAttributeException extends ZeidonException
{
    private static final long serialVersionUID = 1L;

    public HiddenAttributeException( AttributeDef attributeDef )
    {
        super( "Attemping to reference a hidden attribute by name.  To use a hidden attribute "
                + "you must first get the AttributeDef." );
        prependAttributeDef( attributeDef );
    }

}
