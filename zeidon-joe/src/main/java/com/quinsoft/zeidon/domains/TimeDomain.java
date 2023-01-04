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

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * This domain handles any differences needed to deal with time.
 * @author DG
 *
 */
public class TimeDomain extends DateTimeDomain
{
    protected DateTimeFormatter defaultTimeFormatter = DateTimeFormatter.ofPattern("HHmmssS a");

    public TimeDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }

    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue)
    {
        if ( internalValue == null )
            return StringDomain.checkNullString( attributeDef.getDomain().getApplication(), null );

        return defaultTimeFormatter.format( (ZonedDateTime) internalValue );
    }


    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException
    {
            return;
    }

    static public int compareZonedDateTime( ZonedDateTime thisTime, ZonedDateTime thatTime )
    {
        if ( thisTime.getHour() != thatTime.getHour() )
            return Integer.compare( thisTime.getHour(), thatTime.getHour() );

        if ( thisTime.getMinute() != thatTime.getMinute() )
            return Integer.compare( thisTime.getMinute(), thatTime.getMinute() );

        if ( thisTime.getSecond() != thatTime.getSecond() )
            return Integer.compare( thisTime.getSecond(), thatTime.getSecond() );

        return Integer.compare( thisTime.getNano(), thatTime.getNano() );
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

            ZonedDateTime thisTime = (ZonedDateTime) internalValue;
            ZonedDateTime thatTime = (ZonedDateTime) value;
            return compareZonedDateTime( thisTime, thatTime );
        }
        catch ( Throwable t )
        {
            throw ZeidonException.wrapException( t ).prependAttributeDef( attributeDef );
        }
    }

/*
    @Override
    public Object convertExternalValue(Task task, attributeDef attributeDef, String contextName, Object externalValue)
    {
    	if ( externalValue == null )
    		return null;
    	//
    	// Is there any chance that I want to use the instanceof ZonedDateTime not to do a return but to
    	// make sure that the externalValue is a ZonedDateTime so that I know I can get the time from it???
        //if ( externalValue instanceof ZonedDateTime )
        //    return externalValue;

        //if ( externalValue instanceof Date )
        //    return new ZonedDateTime( externalValue );
        //

        // VML operations use "" as synonymous with null.
        if ( externalValue instanceof String && StringUtils.isBlank( (String) externalValue ) )
            return null;

        //if ( externalValue instanceof CharSequence )
        if ( externalValue instanceof ZonedDateTime )
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

            return formatter.format( (ZonedDateTime) internalValue );
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

            return formatter.format( (ZonedDateTime) value );
        }

        @Override
        public void setAttribute(PortableFileReader reader)
        {
            case "JavaEditString" ) )
            {
                editString = reader.getAttributeValue();
                formatter = DateTimeFormatter.ofPattern( editString );
            }
            else
                super.setAttribute( reader );
        }
    }
*/
}
