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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.common.primitives.Ints;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * Domain for storing Integers.
 *
 */
public class IntegerDomain extends AbstractNumericDomain
{
    static final private Pattern BETWEEN = Pattern.compile( "\\((\\d+)(<|<=)x(<|<=)(\\d+)\\)" );
    static final private Pattern RANGE   = Pattern.compile( "\\((\\d+)\\.\\.(\\d+)\\)" );

    private final IntChecker intChecker;

    public IntegerDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
        intChecker = configure( task, domainProperties );
    }

    private IntChecker configure( Task task, Map<String,Object> domainProperties )
    {
        if ( StringUtils.isBlank( getConfigString() ) )
            return null;

        String config = getConfigString();

        // Check for BETWEEN config
        Matcher m = BETWEEN.matcher( config );
        if ( m.matches() )
        {
            task.log().info( "Domain %s: using between logic '%s'", getName(), config );

            int lowerBound = Integer.parseInt( m.group( 1 ) );
            int upperBound = Integer.parseInt( m.group( 4 ) );
            String lowerCompare = m.group( 2 );
            String upperCompare = m.group( 3 );
            switch ( lowerCompare )
            {
                case "<":
                    if ( upperCompare.equals( "<" ) )
                        return new BetweenLL( lowerBound, upperBound );
                    else
                        return new BetweenLLe( lowerBound, upperBound );

                case "<=":
                    if ( upperCompare.equals( "<" ) )
                        return new BetweenLeL( lowerBound, upperBound );
                    else
                        return new BetweenLeLe( lowerBound, upperBound );
            }
        }

        m = RANGE.matcher( config );
        if ( m.matches() )
        {
            return new Range( m.group( 1 ), m.group( 2 ) );
        }

        throw new ZeidonException("Unknown config string '%s' for IntegerDomain", config );
    }

    private Integer check( AttributeDef attributeDef, int value )
    {
        if ( intChecker != null )
            intChecker.checkValue( attributeDef, value );

        return value;
    }

    @Override
    public Object convertExternalValue(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, String contextName, Object externalValue)
    {
        // If external value is an AttributeInstance then get *its* internal value.
        if ( externalValue instanceof AttributeInstance )
            externalValue = ((AttributeInstance) externalValue).getValue();

        if ( externalValue == null )
            return null;

        if ( externalValue instanceof Long )
            return check( attributeDef, Ints.checkedCast( (Long) externalValue ) );

        if ( externalValue instanceof Number )
            return check( attributeDef, ((Number) externalValue).intValue() );

        if ( externalValue instanceof CharSequence )
        {
            // VML uses "" as a synonym for null.
        	if ( externalValue instanceof String && StringUtils.isBlank( (String) externalValue) )
        		return null;

        	if ( ! StringUtils.isBlank( contextName ) )
        	{
        	    NumericContext context = (NumericContext) getContext( contextName );
        	    if ( context.hasFormatter() )
        	    {
            	    Number number = (Number) context.convertExternalValue( task, attributeDef, externalValue );
            	    return check( attributeDef, number.intValue() );
        	    }
        	}

        	Integer num;
        	try
        	{
        		num = Integer.parseInt( externalValue.toString().trim() );
        	}
        	catch( Exception e )
        	{
                throw new InvalidAttributeValueException( attributeDef, externalValue, "Can't convert '%s' to Integer",
                        externalValue.getClass().getName() );
        	}

            return check( attributeDef, num );
        }

        throw new InvalidAttributeValueException( attributeDef, externalValue, "Can't convert '%s' to Integer",
                                                  externalValue.getClass().getName() );
    }

    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException
    {
        if ( internalValue instanceof Integer )
            return;

        throw new InvalidAttributeValueException( attributeDef, internalValue, "'%s' is an invalid Object for IntegerDomain",
                                                  internalValue.getClass().getName() );
    }

    @Override
    public Object addToAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand )
    {
        Integer num = (Integer) convertExternalValue( task, attributeInstance, attributeDef, null, operand );
        Integer value = (Integer) currentValue;
        return value + num;
    }

    @Override
    public Object multiplyAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand )
    {
        Integer num = (Integer) convertExternalValue( task, attributeInstance, attributeDef, null, operand );
        Integer value = (Integer) currentValue;
        return value * num;
    }

    @Override
    public DomainContext newContext(Task task)
    {
        return new NumericContext( this );
    }

    @Override
    public DomainContext getContext(Task task, String contextName)
    {
        DomainContext context = getContext( contextName );
        if ( context != null )
            return context;  // We found one by name.

        if ( StringUtils.isBlank( contextName ) )
            throw new ZeidonException("Domain '%s' does not have a default context defined.", getName() );

        // Create a temporary new one and set its edit string to the context name.
        NumericContext intContext = new NumericContext( this );
        intContext.setName( contextName );
        intContext.setEditString( contextName );
        return intContext;
    }

    private interface IntChecker
    {
        void checkValue( AttributeDef attributeDef, int value );
    }

    private class BetweenLL implements IntChecker
    {
        private final int lowerBound;
        private final int upperBound;

        public BetweenLL( int lowerBound, int upperBound ) // 0 < x < 100
        {
            super();
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }


        @Override
        public void checkValue( AttributeDef attributeDef, int value )
        {
            if ( value <= lowerBound )
                throw new InvalidAttributeValueException( attributeDef, value,
                                                          "Value is less than lower bound %d", lowerBound );
            if ( value >= upperBound )
                throw new InvalidAttributeValueException( attributeDef, value,
                                                          "Value is greater than upper bound %d", lowerBound );
        }
    }

    private class BetweenLeL implements IntChecker
    {
        private final int lowerBound;
        private final int upperBound;

        public BetweenLeL( int lowerBound, int upperBound ) // 0 <= x < 100
        {
            super();
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        @Override
        public void checkValue( AttributeDef attributeDef, int value )
        {
            if ( value < lowerBound )
                throw new InvalidAttributeValueException( attributeDef, value,
                                                          "Value is less than lower bound %d", lowerBound );
            if ( value >= upperBound )
                throw new InvalidAttributeValueException( attributeDef, value,
                                                          "Value is greater than upper bound %d", lowerBound );
        }
    }

    private class BetweenLeLe implements IntChecker
    {
        private final int lowerBound;
        private final int upperBound;

        public BetweenLeLe( int lowerBound, int upperBound ) // 0 <= x <= 100
        {
            super();
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        @Override
        public void checkValue( AttributeDef attributeDef, int value )
        {
            if ( value < lowerBound )
                throw new InvalidAttributeValueException( attributeDef, value,
                                                          "Value is less than lower bound %d", lowerBound );
            if ( value > upperBound )
                throw new InvalidAttributeValueException( attributeDef, value,
                                                          "Value is greater than upper bound %d", lowerBound );
        }
    }

    private class BetweenLLe implements IntChecker
    {
        private final int lowerBound;
        private final int upperBound;

        public BetweenLLe( int lowerBound, int upperBound ) // 0 < x <= 100
        {
            super();
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }


        @Override
        public void checkValue( AttributeDef attributeDef, int value )
        {
            if ( value <= lowerBound )
                throw new InvalidAttributeValueException( attributeDef, value,
                                                          "Value is less than lower bound %d", lowerBound );
            if ( value > upperBound )
                throw new InvalidAttributeValueException( attributeDef, value,
                                                          "Value is greater than upper bound %d", lowerBound );
        }
    }

    private class Range implements IntChecker
    {
        private final int lowerBound;
        private final int upperBound;

        public Range( String lowerBound, String upperBound ) // 0 <= x <= 100
        {
            super();
            this.lowerBound = Integer.parseInt( lowerBound );
            this.upperBound = Integer.parseInt( upperBound );
        }

        @Override
        public void checkValue( AttributeDef attributeDef, int value )
        {
            if ( value < lowerBound || value > upperBound )
                throw new InvalidAttributeValueException( attributeDef, value,
                                                          "Value is outside of range %s", getConfigString() );
        }
    }

}