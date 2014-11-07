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

import java.math.BigDecimal;
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
public class BigDecimalDomain extends AbstractNumericDomain
{
    public BigDecimalDomain(Application application, Map<String, Object> domainProperties, Task task )
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

        // VML operations use "" as synonymous with null.
        if ( externalValue instanceof String && StringUtils.isBlank( (String) externalValue ) )
            return null;

        if ( externalValue instanceof CharSequence )
            return new BigDecimal( externalValue.toString() );
        
        if ( externalValue instanceof Number )
            return new BigDecimal( externalValue.toString() );
        
        throw new InvalidAttributeValueException( attributeDef, externalValue, "Can't convert '%s' to BigDecimalDomain", 
                                                  externalValue.getClass().getName() );
    }
    
    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) 
        throws InvalidAttributeValueException
    {
        if ( ! ( internalValue instanceof BigDecimal ) )
            throw new InvalidAttributeValueException( attributeDef, internalValue, "'%s' is an invalid Object for BigDecimalDomain", 
                                                      internalValue.getClass().getName() );
    }

    @Override
    public Object addToAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand )
    {
        BigDecimal num = (BigDecimal) convertExternalValue( task, attributeInstance, attributeDef, null, operand );
        BigDecimal value = (BigDecimal) currentValue;
        return value.add( num );
    }
    
    @Override
    public Object multiplyAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand )
    {
        BigDecimal num = (BigDecimal) convertExternalValue( task, attributeInstance, attributeDef, null, operand );
        BigDecimal value = (BigDecimal) currentValue;
        return value.multiply( num );
    }
}