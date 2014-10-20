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

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * This domain handles any differences needed to deal with time.
 * @author DG
 *
 */
public class TimeDomain extends DateDomain
{
    protected DateTimeFormatter defaultTimeFormatter = DateTimeFormat.forPattern("HHmmssS a");
    
    public TimeDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }
   
    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue)
    {
        if ( internalValue == null )
            return StringDomain.checkNullString( attributeDef.getDomain().getApplication(), null );

        return defaultTimeFormatter.print( (DateTime) internalValue );
    }


    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException
    {
            return;      
    }
/*   
    @Override
    public Object convertExternalValue(Task task, attributeDef attributeDef, String contextName, Object externalValue)
    {
    	if ( externalValue == null )
    		return null;
    	//
    	// Is there any chance that I want to use the instanceof DateTime not to do a return but to
    	// make sure that the externalValue is a DateTime so that I know I can get the time from it???
        //if ( externalValue instanceof DateTime )
        //    return externalValue;
    	
        //if ( externalValue instanceof Date )
        //    return new DateTime( externalValue );
        //
    	
        // VML operations use "" as synonymous with null.
        if ( externalValue instanceof String && StringUtils.isBlank( (String) externalValue ) )
            return null;

        //if ( externalValue instanceof CharSequence )
        if ( externalValue instanceof DateTime )
        {
            DomainContext context = getContext( task, contextName );
            return context.convertExternalValue( task, attributeDef, externalValue );
        }

        throw new InvalidAttributeValueException( attributeDef, externalValue, 
                                                  "Invalid object: Domain %s cannot convert value for context %s.",
                                                  this.getClass().getName(), contextName );
    }

    @Override
    public TimeContext newContext(Task task)
    {
        return new TimeContext( this );
    }

    private class TimeContext extends BaseDomainContext
    {
        public TimeContext(Domain domain)
        {
            super( domain );
        }

        private String            editString;
        private DateTimeFormatter formatter;
        
        @Override
        public String convertToString(Task task, attributeDef attributeDef, Object internalValue)
        {
        	if ( internalValue == null )
        		return StringDomain.checkNullString(task.getApplication(), null);

            return formatter.print( (DateTime) internalValue );
        }

        @Override
        public Object convertExternalValue(Task task, attributeDef attributeDef, Object value) throws InvalidAttributeValueException
        {
        	if ( value == null )
        		return null;
        	
            //String s = (String) value;

            // VML operations use "" as synonymous with null.
        	//if ( StringUtils.isBlank( s ) )
        		//return null;
       	        	
            return formatter.print( (DateTime) value );
        }

        @Override
        public void setAttribute(PortableFileReader reader)
        {
            if ( reader.getAttributeName().equals( "JavaEditString" ) )
            {
                editString = reader.getAttributeValue();
                formatter = DateTimeFormat.forPattern( editString );
            }
            else
                super.setAttribute( reader );
        }
    }
*/
}
