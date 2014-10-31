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
/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import org.joda.time.DateTime;

import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.utils.JoeUtils;

/**
 * This class instantiates the value of an attribute.  It can be shared by multiple
 * entitiy instances if they are linked.
 *
 * @author DG
 *
 */
class AttributeValue
{
    /**
     * The AttributeDef that created this AttributeInstance.  This is used *only* for
     * debugging and to supply the ER attribute token.
     */
    private final AttributeDef createAttribute;
    private final Domain        domain;
    private boolean             updated;     // Updated?
    private volatile Object     attributeValue;

    AttributeValue( AttributeDef attributeDef )
    {
        // We can't save attributeDef as part of this attribute instance because it might
        // be linked with a different entity.  Linked attribute instances can have
        // different AttributeDefs.  They should have the same:
        this.domain = attributeDef.getDomain();
        createAttribute = attributeDef;
    }

    /**
     * Creates an empty attribute value if one doesn't exist.
     * @return
     */
    Object getInternalValue( )
    {
        return attributeValue;
    }

    String getString( Task task, AttributeDef attributeDef, String contextName )
    {
        return domain.convertToString( task, attributeDef, getInternalValue(), contextName );
    }

    String getString( Task task, AttributeDef attributeDef )
    {
        return domain.convertToString( task, attributeDef, getInternalValue() );
    }

    Integer getInteger( Task task, AttributeDef attributeDef, String contextName )
    {
        return domain.convertToInteger( task, attributeDef, getInternalValue(), contextName );
    }

    Integer getInteger( Task task, AttributeDef attributeDef )
    {
        return domain.convertToInteger( task, attributeDef, getInternalValue() );
    }

    Double getDouble( Task task, AttributeDef attributeDef, String contextName )
    {
        return domain.convertToDouble( task, attributeDef, getInternalValue(), contextName );
    }

    Double getDouble( Task task, AttributeDef attributeDef )
    {
        return domain.convertToDouble( task, attributeDef, getInternalValue() );
    }

    Boolean getBoolean( Task task, AttributeDef attributeDef, String contextName )
    {
        return domain.convertToBoolean( task, attributeDef, getInternalValue(), contextName );
    }

    Boolean getBoolean( Task task, AttributeDef attributeDef )
    {
        return domain.convertToBoolean( task, attributeDef, getInternalValue() );
    }

    DateTime getDateTime( Task task, AttributeDef attributeDef, String contextName )
    {
        return domain.convertToDate( task, attributeDef, getInternalValue(), contextName );
    }

    DateTime getDateTime( Task task, AttributeDef attributeDef )
    {
        return domain.convertToDate( task, attributeDef, getInternalValue() );
    }

    Blob getBlob( Task task, AttributeDef attributeDef )
    {
        return domain.convertToBlob( task, attributeDef, getInternalValue() );
    }

    void setUpdated( boolean value )
    {
        updated = value;
    }

    private boolean areEqual( Task task, AttributeDef attributeDef, Object value )
    {
        if ( isNull( task, attributeDef ) )
            return domain.isNull( task, attributeDef, value );

        return getInternalValue().equals( value );
    }

    /**
     * Sets the internal value of the attribute to newValue.  Assumes that newValue has been converted
     * to the correct object type.
     * @param setIncremental If true, then set the update incremental flag.
     *
     * @return True if the value was changed, false otherwise.
     */
    boolean setInternalValue( TaskImpl task, AttributeDef attributeDef, Object newValue, boolean setIncremental )
    {
        assert createAttribute.getErAttributeToken().equals( attributeDef.getErAttributeToken() );

        if ( areEqual( task, attributeDef, newValue ) )
            return false;

        try
        {
            if ( domain.isNull( task, attributeDef, newValue ) )
            {
                if ( attributeDef.isRequired() )
                    throw new InvalidAttributeValueException( attributeDef, newValue, "Attribute is required" );
            }
            else
                domain.validateInternalValue( task, attributeDef, newValue );
        }
        catch ( Throwable e )
        {
            throw ZeidonException.wrapException( e ).prependAttributeDef( attributeDef );
        }

        if ( attributeDef.isDebugChange() )
        {
            String msg = String.format( "Changing attribute %s to %s", attributeDef, newValue );
            JoeUtils.sysMessageBox( "Changing an attribute", msg );
        }

        attributeValue = newValue;
        if ( setIncremental )
            setUpdated( true );

        return true;
    }

    /**
     *
     * @param task
     * @param attributeDef - Needed for error processing.
     * @param newValue
     * @return
     */
    boolean set( TaskImpl task, AttributeInstance attributeInstance, Object newValue, String contextName )
    {
        Object o;
        try
        {
            if ( newValue == null )
                o = null;
            else
                o = domain.convertExternalValue( task, attributeInstance, attributeInstance.getAttributeDef(),
                                                 contextName, newValue );
        }
        catch ( Throwable t )
        {
            throw ZeidonException.wrapException( t )
                                 .prependMessage( "New value = %s\nDomain = %s\nContext = %s", newValue, domain, contextName )
                                 .prependAttributeDef( attributeInstance.getAttributeDef() );
        }

        return setInternalValue( task, attributeInstance.getAttributeDef(), o, true );
    }

    Object convertInternalValue( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object value )
    {
        try
        {
            Object newValue = domain.convertInternalValue( task, attributeInstance, value );
            if ( domain.isNull(task, attributeDef, newValue) )
            {
                if ( attributeDef.isRequired() )
                    throw new InvalidAttributeValueException( attributeDef, newValue, "Attribute is required" );
            }
            else
                domain.validateInternalValue( task, attributeDef, newValue );

            return newValue;
        }
        catch ( Throwable t )
        {
            throw ZeidonException.prependMessage( t, "New value = %s\nDomain = %s", value, domain )
                                 .prependAttributeDef( attributeDef );
        }
    }

    boolean isUpdated()
    {
        return updated;
    }

    long getAttributeFlags()
    {
        long flags = 0;

//        if ( activated )
//            flags |= 0x00000001;

        if ( updated )
            flags |= 0x00000002;

        return flags;
    }

    boolean isNull(Task task, AttributeDef attributeDef)
    {
        Object value = getInternalValue();
        return domain.isNull( task, attributeDef, value );
    }

    int compare(TaskImpl task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object o)
    {
        try
        {
            return domain.compare( task, attributeInstance, attributeDef, this.getInternalValue(), o );
        }
        catch ( Throwable t )
        {
            throw ZeidonException.wrapException( t ).prependAttributeDef( attributeDef );
        }
    }

    long getErAttributeToken()
    {
        return createAttribute.getErAttributeToken();
    }

    @Override
    public String toString()
    {
        return attributeValue == null ? "NULL" : attributeValue.toString();
    }

    Object addToAttribute( TaskImpl task, AttributeInstance attributeInstance, Object value )
    {
        Object newValue = domain.addToAttribute( task, attributeInstance, attributeInstance.getAttributeDef(), getInternalValue(), value );
        setInternalValue( task, attributeInstance.getAttributeDef(), newValue, true );
        return newValue;
    }

    Object multiplyAttribute( TaskImpl task, AttributeInstance attributeInstance, Object value )
    {
        Object newValue = domain.multiplyAttribute( task, attributeInstance, attributeInstance.getAttributeDef(), getInternalValue(), value );
        setInternalValue( task, attributeInstance.getAttributeDef(), newValue, true );
        return newValue;
    }

    void copyUpdateFlags( AttributeValue source )
    {
        setUpdated( source.isUpdated() );
    }
}