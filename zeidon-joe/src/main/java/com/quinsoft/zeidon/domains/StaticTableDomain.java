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
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;



/**
 * @author DG
 *
 */
public class StaticTableDomain extends AbstractTableDomain
{
    public StaticTableDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }
    
    @Override
    public int compare(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object internalValue, Object externalValue)
    {
        try
        {
            Object value = convertExternalValue( task, attributeInstance, attributeDef, null, externalValue );
            Integer rc = compareNull( task, attributeDef, internalValue, value);
            if ( rc != null )
                return rc;

    		// KJS 02/06/14 - Since this is a static table domain, I believe "value" will always
    		// be returned as a string. But internalValue might be something else (like an integer) so
            // convert it to a string so that we always compare equal classes.
            String value2 = internalValue.toString();
                        
            return value2.compareTo((String) value);
            
            // KJS 02/07/14 - Do we need the following lines?
            // I tried these lines instead of the above lines and I also get an error in .compare,
            // because of class issue. Wondering if there should be a ".toString" in comare...
            // What does DG think?
            //DomainContext context = getContext( task, null ); // Get the default context.
            //return context.compare( task, internalValue, value );
        }
        catch ( Throwable t )
        {
            throw ZeidonException.wrapException( t ).prependAttributeDef( attributeDef );
        }
    }
}