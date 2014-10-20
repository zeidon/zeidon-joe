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

import java.util.List;
import java.util.Map;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * Common domain code for static and dynamic table domains.
 * 
 * @author DG
 *
 */
public class AbstractTableDomain extends AbstractDomain implements TableDomain
{
    public AbstractTableDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }

    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException
    {
        DomainContext context = getContext( task, null );
        context.validateInternalValue( task, attributeDef, internalValue );
    }

    @Override
    public Object convertExternalValue(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, String contextName, Object externalValue)
    {
        DomainContext context = getContext( task, contextName );
        return context.convertExternalValue( task, attributeDef, externalValue );
    }

    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        DomainContext context = getContext( task, contextName );
        return context.convertToString( task, attributeDef, internalValue );
    }
    
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.objectdefinition.TableDomain#getTableEntries()
     */
    @Override
    public List<TableEntry> getTableEntries( Task task, String contextName )
    {
        TableDomainContext context = (TableDomainContext) getContext( task, contextName );
        return context.getTableEntries(task);
    }

    @Override
    public DomainContext newContext( Task task )
    {
        return new TableListContext( this, task );
    }

    @Override
    public String toString()
    {
        return "Table domain: " + getName();
    }
}
