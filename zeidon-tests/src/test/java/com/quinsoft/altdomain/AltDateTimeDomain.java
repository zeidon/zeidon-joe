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

package com.quinsoft.altdomain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

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
import com.quinsoft.zeidon.domains.DateTimeDomain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * This domain 
 * @author DG
 *
 */
public class AltDateTimeDomain extends DateTimeDomain
{
    public AltDateTimeDomain(Application application, Map<String, Object> domainProperties, Task task )
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
            
            if ( internalValue instanceof Comparable )
            {
                assert internalValue.getClass() == value.getClass();
                
                @SuppressWarnings("unchecked")
                Comparable<Object> c = (Comparable<Object>) internalValue;
                return c.compareTo( value );
            }
            
            DomainContext context = getContext( task, null ); // Get the default context.
            return context.compare( task, internalValue, value );
        }
        catch ( Throwable t )
        {
            throw ZeidonException.wrapException( t ).prependAttributeDef( attributeDef );
        }
    }
}
