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

package com.quinsoft.altdomain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.time.ZonedDateTime;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.BaseDomainContext;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.domains.DomainContext;
import com.quinsoft.zeidon.domains.StaticTableDomain;
import com.quinsoft.zeidon.domains.TableDomainContext;
import com.quinsoft.zeidon.domains.TableEntry;
import com.quinsoft.zeidon.domains.TableListContext;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * This domain 
 * @author DG
 *
 */
public class AltStaticTableDomain extends StaticTableDomain
{
    public AltStaticTableDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }

    
    @Override
    public int compare(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object internalValue, Object externalValue)
    {
    	Object value = null;
        
    	try
    	{
    		// See if we can convert the externalValue to an internal value. But if not, then
    		// I don't want to throw the exception because,I just want to say that the values
    		// don't compare. In other words, in code we might be comparing a date to an invalid
    		// date value but since I'm not actually setting the value, then I don't want to 
    		// throw the exception.
    		// KJS 02/06/14 - Since this is a static table domain, I believe "value" will always
    		// be a string. 
            value = convertExternalValue( task, attributeInstance, attributeDef, null, externalValue );   		
    	}
    	catch ( Throwable t )
    	{
            String strIgnore = task.readZeidonConfig( task.getApplication().getName(), "IgnoreDomainCompareError" );
            //String strIgnore = task.readZeidonConfig( application.getName(), "IgnoreDomainCompareError" );

            if ( strIgnore.equals("Y"))
            {
    		   return -1;
            }
            else
            {
                throw ZeidonException.wrapException( t ).prependAttributeDef( attributeDef );
            }
    	}
        try
        {
            //Object value = convertExternalValue( task, attributeDef, null, externalValue );
            //Object value =  externalValue;
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
