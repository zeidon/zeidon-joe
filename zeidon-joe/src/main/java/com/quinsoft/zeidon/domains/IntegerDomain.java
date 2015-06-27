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

package com.quinsoft.zeidon.domains;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.primitives.Ints;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * @author DG
 *
 */
public class IntegerDomain extends AbstractNumericDomain
{
    public IntegerDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }

    @Override
    public Object convertExternalValue(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, String contextName, Object externalValue)
    {
        // If external value is an AttributeInstance then get *its* internal value.
        if ( externalValue instanceof AttributeInstance )
            externalValue = ((AttributeInstance) externalValue).getValue();

        if ( externalValue == null )
            return null;

        if ( externalValue instanceof Long )
            return Ints.checkedCast( (Long) externalValue );

        if ( externalValue instanceof Number )
            return ((Number) externalValue).intValue();

        if ( externalValue instanceof CharSequence )
        {
            // VML uses "" as a synonym for null.
        	if ( externalValue instanceof String && StringUtils.isBlank( (String) externalValue) )
        		return null;

        	Integer num;
        	try
        	{
        		num = Integer.parseInt( externalValue.toString().trim() );
        	}
        	catch( Exception e )
        	{
                throw new InvalidAttributeValueException( attributeDef, externalValue, "Can't convert '%s' to Integer",
                        externalValue.getClass().getName() );
        	}
            return num;
        }

        throw new InvalidAttributeValueException( attributeDef, externalValue, "Can't convert '%s' to Integer",
                                                  externalValue.getClass().getName() );
    }

    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException
    {
        if ( internalValue instanceof Integer )
            return;

        throw new InvalidAttributeValueException( attributeDef, internalValue, "'%s' is an invalid Object for IntegerDomain",
                                                  internalValue.getClass().getName() );
    }

    @Override
    public Object addToAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand )
    {
        Integer num = (Integer) convertExternalValue( task, attributeInstance, attributeDef, null, operand );
        Integer value = (Integer) currentValue;
        return value + num;
    }

    @Override
    public Object multiplyAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand )
    {
        Integer num = (Integer) convertExternalValue( task, attributeInstance, attributeDef, null, operand );
        Integer value = (Integer) currentValue;
        return value * num;
    }

    @Override
    public IntegerContext newContext(Task task)
    {
        return new IntegerContext( this );
    }

    @Override
    public DomainContext getContext(Task task, String contextName)
    {
        DomainContext context = getContext( contextName );
        if ( context != null )
            return context;  // We found one by name.

        if ( StringUtils.isBlank( contextName ) )
            throw new ZeidonException("Domain '%s' does not have a default context defined.", getName() );

        // Does the context name contain formatting chars?
        if ( ! contextName.contains( "%" ) )
            throw new ZeidonException("Unknown context '%s' for domain '%s' ", contextName, getName() );

        // Create a temporary new one and set its edit string to the context name.
        IntegerContext intContext = new IntegerContext( this );
        intContext.setName( contextName );
        intContext.setFormat( contextName );
        return intContext;
    }

    private class IntegerContext extends BaseDomainContext
    {
        private String format;

        public IntegerContext( Domain domain )
        {
            super( domain );
        }

        @Override
        public String convertToString(Task task, AttributeDef attributeDef, Object internalValue)
        {
            if ( internalValue == null )
                return null;

            if ( format != null )
                return String.format( format, internalValue );

            return internalValue.toString();
        }

        private void setFormat( String format )
        {
            this.format = format;
        }
    }
}