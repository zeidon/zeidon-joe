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
package com.quinsoft.zeidon.standardoe;

import com.quinsoft.zeidon.objectdefinition.RelField;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * A helper class with some logic for parsing a RelField and determining
 * the source and rel instances.
 *
 * @author dgc
 *
 */
class RelFieldParser
{
    AttributeDef srcAttributeDef;
    EntityDef srcEntityDef;
    AttributeDef relAttributeDef;
    EntityDef relEntityDef;
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
     * @return
     */
    RelFieldParser parse( RelField relField, EntityInstanceImpl ei )
    {
        srcAttributeDef = relField.getSrcDataField().getAttributeDef();
        srcEntityDef = srcAttributeDef.getEntityDef();
        relAttributeDef = relField.getRelDataField().getAttributeDef();
        relEntityDef = relAttributeDef.getEntityDef();

        // We now have the attributes--the source and relationship (i.e. target)
        // attributes.  One is part of the current entity (lpEntityDef) and
        // the other is a parent of the current entity.  Find the entity
        // instance of the parent entity.

        if ( relEntityDef == ei.getEntityDef() )
        {
            relInstance = ei;
            srcInstance = ei.findMatchingParent( srcEntityDef );
        }
        else
        {
            srcInstance = ei;
            relInstance = ei.findMatchingParent( relEntityDef );
        }

        return this;
    }

    /**
     * Copies the value of the source rel attribute to the target (ie. rel) attribute.
     */
    void copySrcToRel()
    {
        Object value = srcInstance.getAttribute( srcAttributeDef ).getValue();
        relInstance.getAttribute( relAttributeDef ).setInternalValue( value, true );
        
        // Set flag to indicate that we need to update the target instance.
        relInstance.dbhForeignKey = true;
    }
}
