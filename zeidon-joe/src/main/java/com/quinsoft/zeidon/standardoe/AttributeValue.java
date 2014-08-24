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

import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
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
     * The ViewAttribute that created this AttributeInstance.  This is used *only* for
     * debugging and to supply the ER attribute token.
     */
    private final ViewAttribute createAttribute;
    private final Domain        domain;
    private boolean             updated;     // Updated?
    private volatile Object     attributeValue;

    AttributeValue( ViewAttribute viewAttribute )
    {
        // We can't save viewAttribute as part of this attribute instance because it might
        // be linked with a different entity.  Linked attribute instances can have
        // different view attributes.  They should have the same:
        this.domain = viewAttribute.getDomain();
        createAttribute = viewAttribute;
    }

    /**
     * Creates an empty attribute value if one doesn't exist.
     * @return
     */
    Object getInternalValue( )
    {
        return attributeValue;
    }

    String getString( Task task, ViewAttribute viewAttribute, String contextName )
    {
        return domain.convertToString( task, viewAttribute, getInternalValue(), contextName );
    }

    String getString( Task task, ViewAttribute viewAttribute )
    {
        return domain.convertToString( task, viewAttribute, getInternalValue() );
    }

    Integer getInteger( Task task, ViewAttribute viewAttribute, String contextName )
    {
        return domain.convertToInteger( task, viewAttribute, getInternalValue(), contextName );
    }

    Integer getInteger( Task task, ViewAttribute viewAttribute )
    {
        return domain.convertToInteger( task, viewAttribute, getInternalValue() );
    }

    Double getDouble( Task task, ViewAttribute viewAttribute, String contextName )
    {
        return domain.convertToDouble( task, viewAttribute, getInternalValue(), contextName );
    }

    Double getDouble( Task task, ViewAttribute viewAttribute )
    {
        return domain.convertToDouble( task, viewAttribute, getInternalValue() );
    }

    Boolean getBoolean( Task task, ViewAttribute viewAttribute, String contextName )
    {
        return domain.convertToBoolean( task, viewAttribute, getInternalValue(), contextName );
    }

    Boolean getBoolean( Task task, ViewAttribute viewAttribute )
    {
        return domain.convertToBoolean( task, viewAttribute, getInternalValue() );
    }

    DateTime getDateTime( Task task, ViewAttribute viewAttribute, String contextName )
    {
        return domain.convertToDate( task, viewAttribute, getInternalValue(), contextName );
    }

    DateTime getDateTime( Task task, ViewAttribute viewAttribute )
    {
        return domain.convertToDate( task, viewAttribute, getInternalValue() );
    }

    Blob getBlob( Task task, ViewAttribute viewAttribute )
    {
        return domain.convertToBlob( task, viewAttribute, getInternalValue() );
    }

    void setUpdated( boolean value )
    {
        updated = value;
    }

    private boolean areEqual( Task task, ViewAttribute viewAttribute, Object value )
    {
        if ( isNull( task, viewAttribute ) )
            return domain.isNull( task, viewAttribute, value );

        return getInternalValue().equals( value );
    }

    /**
     * Sets the internal value of the attribute to newValue.  Assumes that newValue has been converted
     * to the correct object type.
     * @param setIncremental If true, then set the update incremental flag.
     *
     * @return True if the value was changed, false otherwise.
     */
    boolean setInternalValue( TaskImpl task, ViewAttribute viewAttribute, Object newValue, boolean setIncremental )
    {
        assert createAttribute.getErAttributeToken().equals( viewAttribute.getErAttributeToken() );

        if ( areEqual( task, viewAttribute, newValue ) )
            return false;

        try
        {
            if ( domain.isNull( task, viewAttribute, newValue ) )
            {
                if ( viewAttribute.isRequired() )
                    throw new InvalidAttributeValueException( viewAttribute, newValue, "Attribute is required" );
            }
            else
                domain.validateInternalValue( task, viewAttribute, newValue );
        }
        catch ( Throwable e )
        {
            throw ZeidonException.wrapException( e ).prependViewAttribute( viewAttribute );
        }

        if ( viewAttribute.isDebugChange() )
        {
            String msg = String.format( "Changing attribute %s to %s", viewAttribute, newValue );
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
     * @param viewAttribute - Needed for error processing.
     * @param newValue
     * @return
     */
    boolean set( TaskImpl task, ViewAttribute viewAttribute, Object newValue, String contextName )
    {
        Object o;
        try
        {
            if ( newValue == null )
                o = null;
            else
                o = domain.convertExternalValue( task, viewAttribute, contextName, newValue );
        }
        catch ( Throwable t )
        {
            throw ZeidonException.wrapException( t )
                                 .prependMessage( "New value = %s\nDomain = %s\nContext = %s", newValue, domain, contextName )
                                 .prependViewAttribute( viewAttribute );
        }

        return setInternalValue( task, viewAttribute, o, true );
    }

    Object convertInternalValue( Task task, ViewAttribute viewAttribute, Object value )
    {
        try
        {
            Object newValue = domain.convertInternalValue( task, viewAttribute, value );
            if ( domain.isNull(task, viewAttribute, newValue) )
            {
                if ( viewAttribute.isRequired() )
                    throw new InvalidAttributeValueException( viewAttribute, newValue, "Attribute is required" );
            }
            else
                domain.validateInternalValue( task, viewAttribute, newValue );

            return newValue;
        }
        catch ( Throwable t )
        {
            throw ZeidonException.prependMessage( t, "New value = %s\nDomain = %s", value, domain )
                                 .prependViewAttribute( viewAttribute );
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

    boolean isNull(Task task, ViewAttribute viewAttribute)
    {
        Object value = getInternalValue();
        return domain.isNull( task, viewAttribute, value );
    }

    int compare(TaskImpl task, ViewAttribute viewAttribute, Object o)
    {
        try
        {
            return domain.compare( task, viewAttribute, this.getInternalValue(), o );
        }
        catch ( Throwable t )
        {
            throw ZeidonException.wrapException( t ).prependViewAttribute( viewAttribute );
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

    Object addToAttribute( TaskImpl task, ViewAttribute viewAttribute, Object value )
    {
        Object newValue = domain.addToAttribute( task, viewAttribute, getInternalValue(), value );
        setInternalValue( task, viewAttribute, newValue, true );
        return newValue;
    }

    Object multiplyAttribute( TaskImpl task, ViewAttribute viewAttribute, Object value )
    {
        Object newValue = domain.multiplyAttribute( task, viewAttribute, getInternalValue(), value );
        setInternalValue( task, viewAttribute, newValue, true );
        return newValue;
    }

    void copyUpdateFlags( AttributeValue source )
    {
        setUpdated( source.isUpdated() );
    }
}