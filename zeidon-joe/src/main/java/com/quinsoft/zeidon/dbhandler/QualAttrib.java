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

package com.quinsoft.zeidon.dbhandler;

import java.util.List;

import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * Keeps track of qualification information for an attribute.
 */
class QualAttrib
{
    Object        value;
    String        oper;
    EntityDef     entityDef;
    AttributeDef  attributeDef;

    /**
     * If non-null then this specifies a list of value that should be used
     * as part of an "IN" clause.
     */
    List<Object>  valueList;

    /**
     * If non-null this value specifies an attribute in the target LOD that will
     * be used as qualification.  It allows qualification to refer to another
     * column in the query.
     */
    AttributeDef  columnAttributeValue;

    QualAttrib(String oper)
    {
        super();

        if ( oper.trim().toUpperCase().equals("!=" ) )
            this.oper = "<>";
        else
            this.oper = oper.trim().toUpperCase();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        if ( entityDef != null )
            sb.append( entityDef.getName() ).append( "." ).append( attributeDef.getName() ).append( " "  );
        sb.append( oper ).append( " " );
        if ( value != null )
            sb.append( value );
        return sb.toString();
    }
}