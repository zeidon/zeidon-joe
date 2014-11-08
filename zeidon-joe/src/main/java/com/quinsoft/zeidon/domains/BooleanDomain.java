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

    Copyright 2009-2014 QuinSoft
 */
package com.quinsoft.zeidon.domains;

import java.util.Map;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * @author dgc
 *
 */
public class BooleanDomain extends AbstractDomain
{

    /**
     * @param app
     * @param domainProperties
     * @param task
     */
    public BooleanDomain( Application app, Map<String, Object> domainProperties, Task task )
    {
        super( app, domainProperties, task );
    }

    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException
    {
        if ( internalValue instanceof Boolean )
            return;

        throw new InvalidAttributeValueException( attributeDef, internalValue, "Attribute isn't expecting a String, got %s", internalValue.getClass() );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.domains.Domain#convertExternalValue(com.quinsoft.zeidon.Task, com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.String, java.lang.Object)
     */
    @Override
    public Object convertExternalValue( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef,
                                        String contextName, Object externalValue ) throws InvalidAttributeValueException
    {
        if ( externalValue == null )
            return null;

        // If external value is an AttributeInstance then get *its* internal value.
        if ( externalValue instanceof AttributeInstance )
            externalValue = ((AttributeInstance) externalValue).getValue();

        if ( externalValue instanceof Boolean )
            return externalValue;

        if ( externalValue instanceof CharSequence )
        {
            String lower = externalValue.toString().toLowerCase();
            switch ( lower )
            {
                case "true":
                case "t":
                case "1":
                case "y":
                case "yes":
                case "on":
                    return Boolean.TRUE;

                case "false":
                case "f":
                case "0":
                case "n":
                case "no":
                case "off":
                    return Boolean.FALSE;

                case "":
                    return null;
            }
        }

        if ( externalValue instanceof Number )
        {
            int b = ((Number) externalValue).intValue();
            return ! ( b == 0 );
        }

        throw new InvalidAttributeValueException( attributeDef, externalValue, "Can't convert '%s' to Boolean",
                                                  externalValue.getClass().getName() );
    }
}
