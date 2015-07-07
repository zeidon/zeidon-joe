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

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * A version of DateTimeDoman that will initialize an attribute to the
 * current time.
 *
 */
public class CreatedDateTimeDomain extends DateTimeDomain
{
    /**
     * @param application
     * @param domainProperties
     * @param task
     */
    public CreatedDateTimeDomain( Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }

    @Override
    public boolean hasInitialValue( Task task, AttributeDef attributeDef )
    {
        return true;
    }

    @Override
    public void setInitialValue( AttributeInstance attributeInstance  )
    {
        attributeInstance.setValue( "NOW" );
    }
}
