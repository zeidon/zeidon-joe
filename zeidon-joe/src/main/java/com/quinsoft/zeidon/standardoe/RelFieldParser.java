/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import com.quinsoft.zeidon.objectdefinition.RelField;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * A helper class with some logic for parsing a RelField and determining
 * the source and rel instances.
 *
 * @author dgc
 *
 */
class RelFieldParser
{
    ViewAttribute srcViewAttrib;
    ViewEntity srcViewEntity;
    ViewAttribute relViewAttrib;
    ViewEntity relViewEntity;
    EntityInstanceImpl relInstance;
    EntityInstanceImpl srcInstance;

    RelFieldParser()
    {
    }

    RelFieldParser( RelField relField, EntityInstanceImpl ei )
    {
        parse( relField, ei );
    }

    /**
     * Determine the src and rel values for relField and ei.  The values will be
     * stored in member variables.
     *
     * @param relField
     * @param ei
     */
    void parse( RelField relField, EntityInstanceImpl ei )
    {
        srcViewAttrib = relField.getSrcDataField().getViewAttribute();
        srcViewEntity = srcViewAttrib.getViewEntity();
        relViewAttrib = relField.getRelDataField().getViewAttribute();
        relViewEntity = relViewAttrib.getViewEntity();

        // We now have the attributes--the source and relationship (i.e. target)
        // attributes.  One is part of the current entity (lpViewEntity) and
        // the other is a parent of the current entity.  Find the entity
        // instance of the parent entity.

        if ( relViewEntity == ei.getViewEntity() )
        {
            relInstance = ei;
            srcInstance = ei.findMatchingParent( srcViewEntity );
        }
        else
        {
            srcInstance = ei;
            relInstance = ei.findMatchingParent( relViewEntity );
        }
    }
}
