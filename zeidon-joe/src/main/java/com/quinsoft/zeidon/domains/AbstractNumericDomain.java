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
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * A Domain class so that all numeric domains can be grouped together.
 * 
 * @author DG
 *
 */
public abstract class AbstractNumericDomain extends AbstractDomain
{
    private final Double min;
    private final Double max;
    @SuppressWarnings("unused")
    private final String editString;
    
    public AbstractNumericDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task  );
        
        //TODO: Read these from the domain value map.
        min = null;
        max = null;
        editString = null;
    }

    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) 
        throws InvalidAttributeValueException
    {
        Double d = convertToDouble( task, attributeDef, internalValue, null );
        if ( min != null && d < min )
            throw new InvalidAttributeValueException( attributeDef, internalValue, "Value may not be below %d", min );

        if ( max != null && d > max )
            throw new InvalidAttributeValueException( attributeDef, internalValue, "Value may not be more than %d", max );
    }
}