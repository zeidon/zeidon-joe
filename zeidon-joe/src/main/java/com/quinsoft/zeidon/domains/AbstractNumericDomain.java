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

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.EntityInstance;
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
    private final Random random = new Random();

    public AbstractNumericDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task  );

        //TODO: Read these from the domain EditString/JavaEditString.
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
        return random.nextInt( 256 );
    }

    public class NumericContext extends BaseDomainContext
    {
        private NumberFormat formatter;

        public NumericContext( Domain domain )
        {
            super( domain );
        }

        @Override
        public void setEditString( String editString )
        {
            if ( editString.startsWith( ":" ) )
            {
                super.setEditString( editString.substring( 1 ) );
                return;
            }

            if ( StringUtils.equalsIgnoreCase( editString, "default" ) )
                formatter = NumberFormat.getInstance( Locale.getDefault() );
            else
                formatter = NumberFormat.getInstance( Locale.forLanguageTag( editString ) );
        }

        protected boolean hasFormatter()
        {
            return formatter != null;
        }

        @Override
        public Object convertExternalValue( Task task, AttributeDef attributeDef, Object value )
                                      throws InvalidAttributeValueException
        {
            try
            {
                return formatter.parse( value.toString() );
            }
            catch ( ParseException e )
            {
                throw new InvalidAttributeValueException( attributeDef, value, "Parse exception: %s", e.getMessage() );
            }
        }

        @Override
        public String convertToString(Task task, AttributeDef attributeDef, Object internalValue)
        {
            if ( internalValue == null )
                return null;

            if ( formatter != null )
                return formatter.format( internalValue );

            if ( getEditString() != null )
                return String.format( getEditString(), internalValue );

            return internalValue.toString();
        }
    }
}