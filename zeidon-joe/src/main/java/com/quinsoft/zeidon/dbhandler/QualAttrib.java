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

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.domains.StringDomain;
import com.quinsoft.zeidon.domains.TableDomain;
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

    QualAttrib( QualAttrib qualAttrib )
    {
        super();
        value = qualAttrib.value;
        oper = qualAttrib.oper;
        entityDef = qualAttrib.entityDef;
        attributeDef = qualAttrib.attributeDef;
        valueList = qualAttrib.valueList;
        columnAttributeValue = qualAttrib.columnAttributeValue;
    }

    /**
     * Returns true if oper is a direct (in)equality (e.g. '=', '!=', 'IN').
     * @return
     */
    public boolean operIsSomeEquality()
    {
        if ( oper.equals( "=" ) )
            return true;
        
        if ( oper.equals( "<>" ) )
            return true;
        
        if ( oper.equals( "IN" ) )
            return true;
        
        return false;
    }
    
    public boolean operIsInequality()
    {
        return oper.equals( "<>" );
    }
    
    /**
     * Returns true if this qual attrib uses equality to compare a null/empty string AND
     * the application considers null and empty strings to be the same.
     * 
     * @return
     */
    public boolean isNullAndEmptyString()
    {
       if ( ! operIsSomeEquality() )
           return false;
       
       Domain domain = attributeDef.getDomain();
       if ( ! ( domain instanceof StringDomain ) )
           return false;
       
       Application app = domain.getApplication();
       if ( ! app.nullStringEqualsEmptyString() )
           return false;
       
       return value == null || StringUtils.isBlank( value.toString() );
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