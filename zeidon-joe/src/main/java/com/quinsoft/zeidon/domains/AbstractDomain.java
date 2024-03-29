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

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hjson.JsonObject;
import org.hjson.JsonObject.Member;
import org.hjson.JsonValue;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.InvalidAttributeConversionException;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.DomainType;
import com.quinsoft.zeidon.objectdefinition.InternalType;


/**
 * Implements default domain processing.
 *
 */
public abstract class AbstractDomain implements Domain
{
    private final Application application;
    private final String name;
    private final Map<String, DomainContext> contextList = new HashMap<String, DomainContext>();
    private DomainContext defaultContext = null;

    /**
     * Internal domain type read from the XDM.
     */
    private final DomainType    domainType;
    private final InternalType  dataType;
    private final String        description;
    private final String        constraintRule;
    private       Map<String, String>  constraintRuleMap;
    private int                 maxLength = 0;

    public AbstractDomain( Application app, Map<String, Object> domainProperties, Task task )
    {
        application = app;
        name = ((String) domainProperties.get( "Name" ) ).intern();
        domainType = DomainType.mapCode( ((String) domainProperties.get( "DomainType" )).charAt( 0 ) );
        dataType = InternalType.mapCode( (String) domainProperties.get( "DataType" ) );
        description = (String) domainProperties.get( "Desc" );
        constraintRule = (String) domainProperties.get( "ConstraintRule" );
        if ( domainProperties.containsKey( "MaxStringLth" ))
        	maxLength = Integer.parseInt(domainProperties.get("MaxStringLth").toString());
    }

    /**
     * Called by a subclass to parse the constraint rule string as relaxed JSON.
     */
    protected void parseConstraintRuleJson()
    {
        constraintRuleMap = new HashMap<>();

        String json = constraintRule.trim();
        if ( StringUtils.isBlank( json ) )
            return;

        // For convenience, handle strings without wrapping {}.
        if ( ! json.startsWith( "{" ) )
            json = "{" + json + "}";

        JsonObject values = JsonValue.readHjson( constraintRule ).asObject();

        Iterator<Member> iter = values.iterator();
        while ( iter.hasNext() )
        {
            Member member = iter.next();
            JsonValue value = member.getValue();
            constraintRuleMap.put( member.getName().toLowerCase(), value.toString() );
        }
    }

    protected String constraintRuleValue( String name, String defaultValue )
    {
        String value = constraintRuleMap.get( name.toLowerCase() );
        if ( StringUtils.isBlank( value ) )
            value = defaultValue;

        return value;
    }

    protected int constraintRuleInt( String name, String defaultValue )
    {
        String value = constraintRuleValue( name, defaultValue );
        try
        {
            return Integer.parseInt( value );
        }
        catch( NumberFormatException e )
        {
            throw ZeidonException.wrapException( e ).appendMessage( "Name = %s, Value = %s", name, value );
        }
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.domains.Domain#convertInternalValue(com.quinsoft.zeidon.Task, com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object)
     */
    @Override
    public Object convertInternalValue(Task task, AttributeInstance attributeInstance, Object value) throws InvalidAttributeValueException
    {
        // For most domains converting an internal value is the same as converting an external value.
        return convertExternalValue( task, attributeInstance, attributeInstance.getAttributeDef(), null, value );
    }

    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException
    {
        if ( internalValue instanceof String )
            return;

        throw new InvalidAttributeValueException( attributeDef, internalValue, "Attribute is expecting a String, got %s", internalValue.getClass() );
    }

    @Override
    public Object convertValueForDb( Task task, AttributeDef attributeDef, Object value )
    {
        return value;
    }

    @Override
    public Blob convertToBlob(Task task, AttributeDef attributeDef, Object internalValue)
    {
        if ( internalValue == null || internalValue.equals(""))
            return null;

        if ( internalValue instanceof Blob )
            return (Blob) internalValue;

        throw new InvalidAttributeConversionException( attributeDef, "Cannot convert internal value of %s to Blob", attributeDef.toString() );
    }

    @Override
    public Boolean convertToBoolean(Task task, AttributeDef attributeDef, Object internalValue )
    {
        if ( internalValue == null )
            return null;

        if ( internalValue instanceof Boolean )
            return (Boolean) internalValue;

        if ( internalValue instanceof CharSequence )
        {
            String lower = internalValue.toString().toLowerCase();
            switch ( lower )
            {
                case "true":
                case "t":
                case "1":
                case "y":
                case "yes":
                case "on":
                    return Boolean.TRUE;

                case "false":
                case "f":
                case "0":
                case "n":
                case "no":
                case "off":
                    return Boolean.FALSE;
            }
        }

        throw new InvalidAttributeConversionException( attributeDef, "Cannot convert %s to Boolean", attributeDef.toString() );
    }

    @Override
    public ZonedDateTime convertToDate(Task task, AttributeDef attributeDef, Object internalValue)
    {
    	if ( internalValue == null )
    		return null;

    	if ( internalValue instanceof ZonedDateTime )
            return (ZonedDateTime) internalValue;

        if ( internalValue instanceof java.sql.Date )
            return ( (java.sql.Date) internalValue).toLocalDate().atStartOfDay( ZoneId.systemDefault() ) ;

        if ( internalValue instanceof java.util.Date )
            return ZonedDateTime.ofInstant( ((java.util.Date) internalValue).toInstant(), ZoneId.systemDefault() );

        throw new InvalidAttributeConversionException( attributeDef, "Cannot convert internal value of %s to Date", attributeDef.toString() );
    }

    @Override
    public Double convertToDouble(Task task, AttributeDef attributeDef, Object internalValue)
    {
    	if ( internalValue == null )
    		return null;

        if ( internalValue instanceof Double )
            return (Double) internalValue;

        try
        {
            return new Double( internalValue.toString() );
        }
        catch ( NumberFormatException e )
        {
            throw new InvalidAttributeConversionException( attributeDef, "Cannot convert internal value of %s (%s) to Double", internalValue.toString(), attributeDef.toString() );
        }
    }

    @Override
    public Integer convertToInteger(Task task, AttributeDef attributeDef, Object internalValue)
    {
        if ( internalValue == null )
            return null;

        if ( internalValue instanceof Integer )
            return (Integer) internalValue;

        try
        {
            return new Integer( internalValue.toString() );
        }
        catch ( NumberFormatException e )
        {
            throw new InvalidAttributeConversionException( attributeDef, "Cannot convert internal value of %s (%s) to Integer", internalValue.toString(), attributeDef.toString() );
        }
    }

    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue)
    {
        if ( internalValue == null )
            return StringDomain.checkNullString( attributeDef.getDomain().getApplication(), null );

        return internalValue.toString();
    }

    @Override
    public Blob convertToBlob(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        return convertToBlob( task, attributeDef, internalValue );
    }

    @Override
    public Boolean convertToBoolean(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        return convertToBoolean( task, attributeDef, internalValue );
    }

    @Override
    public ZonedDateTime convertToDate(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        return convertToDate( task, attributeDef, internalValue );
    }

    @Override
    public Double convertToDouble(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        return convertToDouble( task, attributeDef, internalValue );
    }

    @Override
    public Integer convertToInteger(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        return convertToInteger( task, attributeDef, internalValue );
    }

    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        if ( internalValue == null )
            return null;

        DomainContext context = getContext( task, contextName );
        return context.convertToString( task, attributeDef, internalValue );
    }

    @Override
    public Object addToAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand )
    {
        throw new ZeidonException( "addToAttribute not supported for this domain" );
    }

    @Override
    public Object addToAttribute( Task task,
                                  AttributeInstance attributeInstance,
                                  AttributeDef attributeDef,
                                  Object currentValue,
                                  Object operand,
                                  String contextName )
    {
        throw new ZeidonException( "addToAttribute with context not supported for this domain" );
    }

    @Override
    public Object multiplyAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand )
    {
        throw new ZeidonException( "multiplyAttribute not supported for this domain" );
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public int getMaxLength()
    {
        return maxLength;
    }

    /**
     * Looks for the context in contextList.  Returns null if it isn't found.
     * @param contextName
     * @return
     */
    protected DomainContext getContext( String contextName )
    {
        // Convert contextName to lower case so we can find the context irrespective of case.
        String lowerName;
        if ( StringUtils.isBlank( contextName ) )
            return getDefaultContext();

        lowerName = contextName.toLowerCase();
        DomainContext context = contextList.get( lowerName );
        return context;
    }

    protected DomainContext getDefaultContext()
    {
        if ( defaultContext != null )
            return defaultContext;

        // We'll support domains that don't specify the default context if
        // there is only one context.
        if ( contextList.size() == 1 )
        {
            for ( DomainContext dc : contextList.values() )
            {
                // Get the first context and return it.
                defaultContext = dc;
                return defaultContext;
            }
        }

        return null;
    }

    @Override
    public DomainContext getContext(Task task, String contextName)
    {
        return getContext( task, contextName, true );
    }

    @Override
    public DomainContext getContext(Task task, String contextName, boolean required )
    {
        DomainContext context = getContext( contextName );
        if ( context == null && required )
        {
            if ( StringUtils.isBlank( contextName ) )
                throw new ZeidonException("Domain '%s' does not have a default context defined.", getName() );

            throw new ZeidonException("Couldn't find context '%s' for domain '%s'", contextName, getName() );
        }

        return context;
    }

    @Override
    public void addContext(Task task, DomainContext context)
    {
        contextList.put( context.getName().toLowerCase(), context );
        if ( context.isDefault() )
            defaultContext = context;
    }

    @Override
    public DomainType getDomainType()
    {
        return domainType;
    }

    @Override
    public InternalType getDataType()
    {
        return dataType;
    }

    protected Integer compareNull(Task task, AttributeDef attributeDef, Object internalValue, Object externalValue)
    {
        if ( isNull( task, attributeDef, internalValue ) )
        {
            if ( isNull( task, attributeDef, externalValue ) )
                return 0;

            return -1;
        }

        if ( isNull( task, attributeDef, externalValue ) )
            return 1;

        return null;
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

    @Override
    public DomainContext newContext(Task task)
    {
        return new BaseDomainContext( this );
    }

    @Override
    public Application getApplication()
    {
        return application;
    }

    @Override
    public String toString()
    {
        return getApplication().getName() + "." + getName() + " [" + this.getClass().getName() + "]";
    }

    public String getDescription()
    {
        return description;
    }

    protected String getDefaultedProperty( Map<String, Object> domainProperties, String propName, String defaultValue )
    {
        if ( domainProperties.containsKey( propName ) )
            return (String) domainProperties.get( propName );

        return defaultValue;
    }

    @Override
    public boolean isNull( Task task, AttributeDef attributeDef, Object value )
    {
        if ( value == null )  // Null values are always null (duh).
            return true;

        if ( value instanceof String &&
             attributeDef.getEntityDef().getLodDef().getApplication().nullStringEqualsEmptyString() &&
             StringUtils.isBlank( (String) value ) )
        {
            return true;
        }

        return false;
        // old isNull code...
        //return value == null;
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
        throw new ZeidonException( "Must be implemented for this domain" ).appendMessage( "Domain = %s", getName() );
    }

    public String getConstraintRule()
    {
        return constraintRule;
    }

    @Override
    public boolean hasInitialValue( Task task, AttributeDef attributeDef )
    {
        return false;
    }

    @Override
    public void setInitialValue( AttributeInstance attributeInstance  )
    {
        throw new ZeidonException( "setInitialValue not implemented for domain %s", getName() );
    }
}
