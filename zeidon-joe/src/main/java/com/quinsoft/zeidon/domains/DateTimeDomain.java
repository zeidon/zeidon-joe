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

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.utils.JoeUtils;

/**
 * This domain handles any differences needed to deal with dates+time.
 */
public class DateTimeDomain extends AbstractDomain
{
    protected DateTimeFormatter defaultDateTimeFormatter = DateTimeFormat.forPattern( ObjectEngine.INTERNAL_DATE_STRING_FORMAT + "HHmmss");

    public DateTimeDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }

    @Override
    public Object convertExternalValue(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, String contextName, Object externalValue)
    {
        // If external value is an AttributeInstance then get *its* internal value.
        if ( externalValue instanceof AttributeInstance )
            externalValue = ((AttributeInstance) externalValue).getValue();

        // KJS - Added 01/27/11 because of line 2836 in lTrnscpt_Object.java
        // OrderEntityForView( lTrnscpt, "StudentMajorDegreeTrack", "wPrimarySortOrder A GraduationDate A" );
        // value = null so we are getting to the exception.  Will try returning null, see what happens.
        if ( externalValue == null )
            return null;

        if ( externalValue instanceof DateTime )
            return externalValue;

        if ( externalValue instanceof Date )
            return new DateTime( externalValue );

        // VML operations use "" as synonymous with null.
        if ( externalValue instanceof String && StringUtils.isBlank( (String) externalValue ) )
            return null;

        if ( externalValue instanceof CharSequence )
        {
            // If string is "NOW" then we'll use current datetime.
            if ( externalValue.toString().equals( "NOW" ) )
                return new DateTime();

            DomainContext context = getContext( task, contextName );
            return context.convertExternalValue( task, attributeDef, externalValue.toString() );
        }

        throw new InvalidAttributeValueException( attributeDef, externalValue,
                                                  "Invalid object: Domain %s cannot convert value for context %s.",
                                                  this.getClass().getName(), contextName );
    }

    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue)
    {
        if ( internalValue == null )
            return StringDomain.checkNullString( attributeDef.getDomain().getApplication(), null );

        if ( internalValue.toString().isEmpty())
            return internalValue.toString();

        return defaultDateTimeFormatter.print( (DateTime) internalValue );
    }

    /**
     * Attempt to get the context.  This differs from the normal getContext() by attempting to create
     * a SimpleDateFormatter using the contextName as the format if a context is not found by name.
     */
    @Override
    public DomainContext getContext(Task task, String contextName)
    {
        DomainContext context = getContext( contextName );
        if ( context != null )
            return context;  // We found one by name.

        if ( StringUtils.isBlank( contextName ) )
            throw new ZeidonException("Domain '%s' does not have a default context defined.", getName() );

        // Create a temporary new one and set its edit string to the context name.
        DateContext dateContext = new DateContext( this );
        dateContext.setName( contextName );
        dateContext.setEditString( contextName );
        return dateContext;
    }

    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException
    {
      if ( internalValue instanceof DateTime )
            return;

        throw new InvalidAttributeValueException( attributeDef, internalValue, "Internal value must be Joda DateTime, not %s",
                                                  internalValue.getClass().getName() );
    }

    /**
     * Adds milliseconds to the datetime value.
     */
    @Override
    public Object addToAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object addValue )
    {
        DateTime date1 = (DateTime) convertExternalValue( task, attributeInstance, attributeDef, null, currentValue );
        if ( date1 == null )
            throw new ZeidonException( "Target attribute for add is NULL" )
                            .prependAttributeDef( attributeDef );

        if ( addValue == null )
            return date1;

        if ( addValue instanceof Number )
        {
            int millis = ((Number) addValue).intValue();
            return date1.plusMillis( millis );
        }

        throw new ZeidonException( "Value type of %s not supported for add to DateDomain", addValue.getClass().getName() );
    }

    @Override
    public Object addToAttribute( Task task,
                                  AttributeInstance attributeInstance,
                                  AttributeDef attributeDef,
                                  Object currentValue,
                                  Object operand,
                                  String contextName )
    {
        if ( ! StringUtils.isBlank( contextName ) )
        {
            return addWithContext( task,
                                   attributeInstance,
                                   attributeDef,
                                   currentValue,
                                   operand,
                                   contextName );
        }

        return addToAttribute( task, attributeInstance, attributeDef, currentValue, operand );
    }

    private Object addWithContext( Task task,
                                   AttributeInstance attributeInstance,
                                   AttributeDef attributeDef,
                                   Object currentValue,
                                   Object operand,
                                   String contextName )
    {
        assert ! StringUtils.isBlank( contextName );

        if ( operand instanceof AttributeInstance )
            operand = ((AttributeInstance) operand).getValue();

        if ( ! ( operand instanceof Integer ) && ! ( operand instanceof Long ) )
        {
            throw new ZeidonException( "When adding to DateTime with a context, operand must be integer or long value.  " +
                                       "Type of operand = %s", operand.getClass().getName() )
                            .prependAttributeDef( attributeDef );
        }

        int value = ((Number) operand).intValue();
        DateTime dt = (DateTime) currentValue;

        switch ( contextName.toLowerCase() )
        {
            case "day":
            case "days":
                return dt.plusDays( value );

            case "hour":
            case "hours":
                return dt.plusHours( value );

            case "minute":
            case "minutes":
                return dt.plusMinutes( value );

            case "milli":
            case "millis":
            case "millisecond":
            case "milliseconds":
                return dt.plus( ((Number) operand).longValue() );

            case "month":
            case "months":
                return dt.plusMonths( value );

            case "second":
            case "seconds":
                return dt.plusSeconds( value );

            case "week":
            case "weeks":
                return dt.plusWeeks( value );

            case "year":
            case "years":
                return dt.plusYears( value );
        }

        // TODO Auto-generated method stub
        throw new ZeidonException( "Unknown context name '%s' for DateTime domain", contextName )
                        .prependAttributeDef( attributeDef );
    }

    @Override
    public DomainContext newContext(Task task)
    {
        return new DateContext( this );
    }

    /**
     * Generate a random test value for this domain.  This is used by test code to create random
     * test data.
     *
     * @param task current task
     * @param attributeDef def of the attribute.
     * @param entityInstance if not null this is the EntityInstance that will store the test data.
     *
     * @return random test data for this domain.
     */
    @Override
    public Object generateRandomTestValue( Task task, AttributeDef attributeDef, EntityInstance entityInstance )
    {
        DomainContext context = getDefaultContext();
        return context.convertToString( task, attributeDef, new DateTime() );
    }

    private class DateContext extends BaseDomainContext
    {
        /**
         * URL for Java SimpleDateFormat help.
         */
        private static final String JAVA_DATE_FORMATTING_URL = "http://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html";

        public DateContext(Domain domain)
        {
            super( domain );
        }

        private DateTimeFormatter formatter;

        @Override
        public String convertToString(Task task, AttributeDef attributeDef, Object internalValue) throws ZeidonException
        {
            if ( internalValue == null )
                return StringDomain.checkNullString(task.getApplication(), null);

            if ( internalValue.toString().isEmpty())
                return internalValue.toString();

            if ( formatter == null )
                throw new ZeidonException( "JavaEditString is not set for context %s", this.toString() );

            return formatter.print( (DateTime) internalValue );
        }

        /**
         * Assumes a string.
         */
        @Override
        public Object convertExternalValue(Task task, AttributeDef attributeDef, Object value) throws InvalidAttributeValueException
        {
            if ( value == null )
                return null;

            // If external value is an AttributeInstance then get *its* internal value.
            if ( value instanceof AttributeInstance )
                value = ((AttributeInstance) value).getValue();

            String s = (String) value;

            // VML operations use "" as synonymous with null.
            if ( StringUtils.isBlank( s ) )
                return null;

            // A common internal date/time format is the DefaultTimeFormat.  Let's try
            // a quick check to see if 's' is the same length and only digits.
            if ( s.length() >= ObjectEngine.INTERNAL_DATE_STRING_FORMAT.length() && JoeUtils.onlyDigits( s ) )
                return JoeUtils.parseStandardDateString( s );

            if ( formatter == null )
                throw new ZeidonException( "JavaEditString is not set for context %s", this.toString() );

            try
            {
                return formatter.parseDateTime( s );
            }
            catch ( Exception e )
            {
                throw new InvalidAttributeValueException( attributeDef, s, e.getMessage() )
                                     .appendMessage( "Format string = %s", getEditString() )
                                     .appendMessage( "See %s for help on Java Date formatting", JAVA_DATE_FORMATTING_URL )
                                     .setCause( e );
            }
        }

        @Override
        protected void setEditString( String editString )
        {
            super.setEditString( editString );
            formatter = JoeUtils.createDateFormatterFromEditString( editString );
        }
    }
}
