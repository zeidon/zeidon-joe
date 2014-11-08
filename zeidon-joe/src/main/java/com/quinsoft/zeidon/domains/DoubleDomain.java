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

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.MapMaker;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.utils.PortableFileReader;

/**
 * Decimal domain, which stores values as Double.
 *
 * Uses DecimalFormat for formatting values to string.  See
 * http://java.sun.com/docs/books/tutorial/i18n/format/decimalFormat.html
 *
 * @author DG
 *
 */
public class DoubleDomain extends AbstractNumericDomain
{
    private final DecimalFormat parser = new DecimalFormat( "#,###.#" );

    /**
     * A map for keeping track of "ad-hoc" contexts so we don't have to keep recreating
     * them.  An ad-hoc context is one that isn't listed in zeidon.xdm but is a Java
     * format string for DecimalFormat.  If a context name is a valid format we'll accept
     * it.
     */
    private ConcurrentMap<String, DomainContext> adhocContexts;

    public DoubleDomain( Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }

    @Override
    public Object convertExternalValue(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, String contextName, Object externalValue)
    {
    	if ( externalValue == null )
    		return null;

        // If external value is an AttributeInstance then get *its* internal value.
        if ( externalValue instanceof AttributeInstance )
            externalValue = ((AttributeInstance) externalValue).getValue();

        DomainContext context = getContext( task, contextName );
        return context.convertExternalValue( task, attributeDef, externalValue );
    }

    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue )
        throws InvalidAttributeValueException
    {
        if ( ! ( internalValue instanceof Double ) )
            throw new InvalidAttributeValueException( attributeDef, internalValue, "'%s' is an invalid Object for DoubleDomain",
                                                      internalValue.getClass().getName() );

        super.validateInternalValue( task, attributeDef, internalValue );
    }

    @Override
    public Object addToAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand )
    {
        Double num = (Double) convertExternalValue( task, attributeInstance, attributeDef, null, operand );
        Double value = (Double) currentValue;
        return value + num;
    }

    @Override
    public Object multiplyAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand )
    {
        Double num = (Double) convertExternalValue( task, attributeInstance, attributeDef, null, operand );
        Double value = (Double) currentValue;
        return value * num;
    }

    private synchronized ConcurrentMap<String, DomainContext> getAdhocContextMap()
    {
        if ( adhocContexts == null )
            adhocContexts = new MapMaker().concurrencyLevel( 2 ).makeMap();

        return adhocContexts;
    }

    @Override
    protected DomainContext getContext(String contextName)
    {
        DomainContext context = super.getContext( contextName );
        if ( context == null )
        {
            // If we get here then we searched have a context name but we didn't find
            // one.  This probably means that the user is using an "adhoc" context where
            // the context name is a format string. Try creating a context that uses the
            // contextName as a format string.

            // Have we already created one?
            context = getAdhocContextMap().get( contextName );
            if ( context == null )
            {
                // Doesn't exist, so create it.
                try
                {
                    context = new DoubleContext( this, contextName );
                    getAdhocContextMap().putIfAbsent( contextName, context );
                    if ( getAdhocContextMap().size() > 1000 )
                    {
                        throw new ZeidonException( "Ad-hoc context list is too large for this domain." )
                                            .appendMessage( "Domain = %s", this.toString() );
                    }
                }
                catch ( Exception e )
                {
                    throw new ZeidonException( "Unknown Double context and context is not valid format string." ).setCause( e );
                }
            }
        }

        return context;
    }

    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        DomainContext context = getContext( contextName );
        return context.convertToString( task, attributeDef, internalValue );
    }

    @Override
    public Integer convertToInteger(Task task, AttributeDef attributeDef, Object internalValue)
    {
        if ( internalValue == null )
            return null;

        Double d = (Double) internalValue;
        return d.intValue();
    }

    @Override
    public DomainContext newContext(Task task)
    {
        return new DoubleContext( this );
    }

    private class DoubleContext extends BaseDomainContext
    {
        private DecimalFormat format = parser;
        private String        formatPattern = null;
        private Integer       decimalPlaces;

        /**
         * @param domain
         */
        DoubleContext(Domain domain)
        {
            super( domain );
        }

        private DoubleContext( Domain domain, String formatPattern )
        {
            super( domain );
            setName( formatPattern );
            this.formatPattern = formatPattern;
            format = new DecimalFormat( formatPattern );
        }

        @Override
        public String convertToString(Task task, AttributeDef attributeDef, Object internalValue)
        {
         	if ( internalValue == null )
        		return null;

            Double d = (Double) internalValue;
            synchronized ( format )
            {
                return format.format( d );
            }
        }

        @Override
        public Object convertExternalValue( Task task, AttributeDef attributeDef, Object externalValue )
                                throws InvalidAttributeValueException
        {
            // If external value is an AttributeInstance then get *its* internal value.
            if ( externalValue instanceof AttributeInstance )
                externalValue = ((AttributeInstance) externalValue).getValue();

            if ( externalValue instanceof Number )
            {
                if ( decimalPlaces == null )
                    return ((Number) externalValue).doubleValue();

                // KJS 01/10/14 - In the c world, we round our decimals based on
                // the decimal precision value.
                // so I am adding decimal precision here.
                String formatPattern = decimalPlaces == 0 ? "#" : "#." + StringUtils.repeat( "#", decimalPlaces );
                DecimalFormat df = new DecimalFormat( formatPattern );
                String formate = df.format( externalValue );
                double finalValue;
                try
                {
                    // If the externalValue has no decimal values, the df.format
                    // above fails because it
                    // thinks the number is a Long. When this happens, do the
                    // following.
                    finalValue = (Double) df.parse( formate );
                }
                catch ( Exception e )
                {
                    return ( (Number) externalValue ).doubleValue();
                }

                return finalValue;
            }

            if ( externalValue instanceof CharSequence )
            {
                String str = externalValue.toString();

                // VML uses "" as a synonym for null.
                if ( StringUtils.isBlank( str ) )
                    return null;

                ParsePosition ps = new ParsePosition( 0 );
                Double d;
                try
                {
                    synchronized ( parser )
                    {
                        d = parser.parse( str, ps ).doubleValue();
                    }
                }
                catch ( Exception e )
                {
                    throw new InvalidAttributeValueException( attributeDef, str, e.getMessage() )
                            .setCause( e );
                }

                int idx = Math.max( ps.getErrorIndex(), ps.getIndex() );
                if ( idx != str.length() )
                {
                    throw new InvalidAttributeValueException( attributeDef, str, "Error parsing '" + str
                            + "' at position " + ( idx + 1 ) );
                }

                return d;
            }

            throw new InvalidAttributeValueException( attributeDef, externalValue,
                                                      "Can't convert '%s' to Double",
                                                      externalValue.getClass().getName() );
       }

        @Override
        public void setAttribute(PortableFileReader reader)
        {
            String name = reader.getAttributeName();
            if ( name.equals( "DecimalFormat" ) )
            {
                decimalPlaces = Integer.parseInt( reader.getAttributeValue() );
                formatPattern = decimalPlaces == 0 ? "#" : "#." + StringUtils.repeat( "#", decimalPlaces );
                format = new DecimalFormat( formatPattern );
            }
            else
            if ( name.equals( "JavaEditString" ) )
            {
                formatPattern = reader.getAttributeValue();
                format = new DecimalFormat( formatPattern );
            }
            else
                super.setAttribute( reader );
        }
    }
}