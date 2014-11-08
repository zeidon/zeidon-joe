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

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * @author DG
 *
 */
public class LongDomain extends AbstractNumericDomain
{
    public LongDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }

    @Override
    public Object convertExternalValue(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, String contextName, Object externalValue)
    {
    	if ( externalValue == null )
    		return null;
    	
        // If external value is an AttributeInstance then get *its* internal value.
        if ( externalValue instanceof AttributeInstance )
            externalValue = ((AttributeInstance) externalValue).getValue();

        if ( externalValue instanceof Number )
            return ((Number) externalValue).longValue();
        
        if ( externalValue instanceof CharSequence )
        {
            // VML uses "" as a synonym for null.
        	if ( externalValue instanceof String && StringUtils.isBlank( (String) externalValue) )
        		return null;
        	
        	Long num;
        	try
        	{
        		num = Long.parseLong( ((CharSequence) externalValue ).toString() );
        	}
        	catch( Exception e )
        	{
                throw new InvalidAttributeValueException( attributeDef, externalValue, "Can't convert '%s' to Long", 
                                                          externalValue.getClass().getName() );        	
        	}
            return num;
        }
        
        throw new InvalidAttributeValueException( attributeDef, externalValue, "Can't convert '%s' to Long", 
                                                  externalValue.getClass().getName() );
    }
    
    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException
    {
        if ( internalValue instanceof Long )
            return;
        
        throw new InvalidAttributeValueException( attributeDef, internalValue, "'%s' is an invalid Object for LongDomain", 
                                                  internalValue.getClass().getName() );
    }

    @Override
    public Object addToAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand )
    {
        Long num = (Long) convertExternalValue( task, attributeInstance, attributeDef, null, operand );
        Long value = (Long) currentValue;
        return value + num;
    }

    @Override
    public Object multiplyAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand )
    {
        Long num = (Long) convertExternalValue( task, attributeInstance, attributeDef, null, operand );
        Long value = (Long) currentValue;
        return value * num;
    }

   
    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
     	if ( internalValue == null )
    		return null;
     	
     	return internalValue.toString();	
    }
}