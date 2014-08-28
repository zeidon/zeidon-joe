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

import com.google.common.primitives.Ints;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;

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
    public Object convertExternalValue(Task task, ViewAttribute viewAttribute, String contextName, Object externalValue)
    {
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
        		num = Integer.parseInt( ((CharSequence) externalValue ).toString() );
        	}
        	catch( Exception e )
        	{
                throw new InvalidAttributeValueException( viewAttribute, externalValue, "Can't convert '%s' to Integer",
                        externalValue.getClass().getName() );
        	}
            return num;
        }

        throw new InvalidAttributeValueException( viewAttribute, externalValue, "Can't convert '%s' to Integer",
                                                  externalValue.getClass().getName() );
    }

    @Override
    public void validateInternalValue( Task task, ViewAttribute viewAttribute, Object internalValue ) throws InvalidAttributeValueException
    {
        if ( internalValue instanceof Integer )
            return;

        throw new InvalidAttributeValueException( viewAttribute, internalValue, "'%s' is an invalid Object for IntegerDomain",
                                                  internalValue.getClass().getName() );
    }

    @Override
    public Object addToAttribute( Task task, ViewAttribute viewAttribute, Object currentValue, Object operand )
    {
        Integer num = (Integer) convertExternalValue( task, viewAttribute, null, operand );
        Integer value = (Integer) currentValue;
        return value + num;
    }

    @Override
    public Object multiplyAttribute( Task task, ViewAttribute viewAttribute, Object currentValue, Object operand )
    {
        Integer num = (Integer) convertExternalValue( task, viewAttribute, null, operand );
        Integer value = (Integer) currentValue;
        return value * num;
    }


    @Override
    public String convertToString(Task task, ViewAttribute viewAttribute, Object internalValue, String contextName)
    {
     	if ( internalValue == null )
    		return null;

     	return internalValue.toString();
    }
}