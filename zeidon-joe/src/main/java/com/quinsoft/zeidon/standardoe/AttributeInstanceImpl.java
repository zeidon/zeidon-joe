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
package com.quinsoft.zeidon.standardoe;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.TemporalEntityException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * @author dgc
 *
 */
class AttributeInstanceImpl implements AttributeInstance
{
    private       View               view;
    private final AttributeDef       attributeDef;
    private final AttributeValue     attributeValue;
    private final EntityInstanceImpl entityInstance;


    AttributeInstanceImpl( AttributeDef       attributeDef,
                           AttributeValue     attributeValue,
                           View               view,
                           EntityInstanceImpl entityInstance )
    {
        super();
        this.attributeDef = attributeDef;
        this.attributeValue = attributeValue;
        this.view = view;
        this.entityInstance = entityInstance;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getAttributeDef()
     */
    @Override
    public AttributeDef getAttributeDef()
    {
        return attributeDef;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getEntityInstance()
     */
    @Override
    public EntityInstance getEntityInstance()
    {
        return entityInstance;
    }

    /**
     * Calls the derived operation if this is a derived attribute.
     */
    private void executeDerivedOper()
    {
        if ( ! attributeDef.isDerived() )
            return;

        attributeDef.executeDerivedAttributeForGet( this );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getString()
     */
    @Override
    public String getString()
    {
        executeDerivedOper();
        return attributeValue.getString( getTask(), attributeDef );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getLong()
     */
    @Override
    public Integer getInteger()
    {
        executeDerivedOper();
        return attributeValue.getInteger( getTask(), attributeDef );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getDate()
     */
    @Override
    public DateTime getDateTime()
    {
        executeDerivedOper();
        return attributeValue.getDateTime( getTask(), attributeDef );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getString(java.lang.String)
     */
    @Override
    public String getString( String contextName )
    {
        executeDerivedOper();
        return attributeValue.getString( getTask(), attributeDef, contextName );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getLong(java.lang.String)
     */
    @Override
    public Integer getInteger( String contextName )
    {
        executeDerivedOper();
        return attributeValue.getInteger( getTask(), attributeDef, contextName );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getDate(java.lang.String)
     */
    @Override
    public DateTime getDateTime( String contextName )
    {
        executeDerivedOper();
        return attributeValue.getDateTime( getTask(), attributeDef, contextName );
    }

    @Override
    public Double getDouble()
    {
        executeDerivedOper();
        return attributeValue.getDouble( getTask(), attributeDef );
    }

    @Override
    public Double getDouble( String contextName )
    {
        executeDerivedOper();
        return attributeValue.getDouble( getTask(), attributeDef, contextName );
    }

    @Override
    public Boolean getBoolean()
    {
        executeDerivedOper();
        return attributeValue.getBoolean( getTask(), attributeDef );
    }

    @Override
    public Boolean getBoolean( String contextName )
    {
        executeDerivedOper();
        return attributeValue.getBoolean( getTask(), attributeDef, contextName );
    }

    /**
     * @deprecated Use getValue() instead.
     */
    @Deprecated
    @Override
    public Object getInternalAttributeValue()
    {
        executeDerivedOper();
        return attributeValue.getInternalValue();
    }

    @Override
    public Object getValue()
    {
        executeDerivedOper();
        return attributeValue.getInternalValue();
    }

    private EntityDef getEntityDef()
    {
        return attributeDef.getEntityDef();
    }

    private ObjectInstance getObjectInstance()
    {
        return entityInstance.getObjectInstance();
    }

    @Override
    public TaskImpl getTask()
    {
        return entityInstance.getTask();
    }

    /**
     * Makes sure the attribute can be updated.
     *
     * @param attributeDef
     */
    void validateUpdateAttribute()
    {
        validateUpdateAttribute( null );
    }

    /**
     * Makes sure the attribute can be updated.
     * @param oiSet If non-null, then permission can be found in a linked attribute in the oiSet.
     * @param attributeDef
     */
    void validateUpdateAttribute(Set<ObjectInstance> oiSet)
    {
        // If the attribute is derived or work, then we do not need to check if the
        // attribute can be updated.
        if ( attributeDef.isDerived() )
            return;

        // If the attribute is Persistent, then it is an attribute from the ER.
        // Otherwise it's a work attribute and can be updated even if the
        // object is read only.
        if ( ! attributeDef.isPersistent() )
            return;

        // If the entity is derived or work, then we do not need to check if the
        // attribute can be updated.
        if ( getEntityDef().isDerived() || getEntityDef().isDerivedPath() )
            return;

        // We'll allow updates to autoseq so the dbhandler can change them.
        if ( attributeDef.isAutoSeq() )
            return;

        if ( ! attributeDef.isUpdate() )
        {
            if ( oiSet == null || linkedInstanceHasUpdateAttribute( oiSet ) == false )
                throw new ZeidonException( "Attribute is defined as read-only and/or hidden and is not linked to updatable entity." )
                                    .prependAttributeDef( attributeDef );
        }

        if ( ! attributeDef.getEntityDef().isUpdate() )
            throw new ZeidonException( "Entity may not be udpated." )
                                .prependAttributeDef( attributeDef );

        if ( getObjectInstance().isReadOnly() )
            throw new ZeidonException( "Object Instance is read-only" )
                                .prependEntityDef( getEntityDef() );

        // Don't allow updates to keys unless the EI is being created.
        if ( attributeDef.isKey() && ! entityInstance.isCreated() )
            throw new ZeidonException( "Cannot update key attributes." )
                            .prependAttributeDef( attributeDef );

        for ( EntityInstanceImpl linked : entityInstance.getLinkedInstances() )
        {
            if ( ! entityInstance.temporalVersionMatch( linked ) )
                throw new TemporalEntityException( entityInstance,
                            "Attempting to update an entity that is linked to a versioned instance" );
        }
    }

    private boolean linkedInstanceHasUpdateAttribute( Set<ObjectInstance> oiSet )
    {
        for ( EntityInstanceImpl linked : entityInstance.getLinkedInstances() )
        {
            if ( ! oiSet.contains( linked.getObjectInstance() ) )
                continue;

            EntityDef linkedEntityDef = linked.getEntityDef();
            AttributeDef linkedAttributeDef = linkedEntityDef.getAttributeByErToken( attributeDef.getErAttributeToken() );
            if ( linkedAttributeDef == null )
                continue;

            if ( linkedAttributeDef.isUpdate() )
                return true;
        }

        // If we get here we never found an attribute with update authority.
        return false;
    }

    /**
     * Validate the context name.  If the context name is null or "", returns the default context.
     * @param attributeDef
     * @param contextName
     * @return
     */
    private String checkContextName( String contextName )
    {
        Domain domain = attributeDef.getDomain();
        return domain.getContext( getTask(), contextName ).getName();
    }

    @Override
    public EntityInstance setValue( Object value, String contextName )
    {
        validateUpdateAttribute();
        contextName = checkContextName( contextName );
        Object oldValue = attributeValue.getInternalValue();
        if ( attributeValue.set( getTask(), this, value, contextName ) )
        {
            if ( ! attributeDef.isDerived() )
                entityInstance.setUpdated( true, true, attributeDef.isPersistent() );

            entityInstance.updateHashKeyAttributeToMap( attributeDef, oldValue );
        }
        return entityInstance;
    }

    @Override
    public EntityInstance setValue( Object value )
    {
        return setValue( value, null );
    }

    @Override
    public boolean isNull()
    {
        executeDerivedOper();
        return attributeValue.isNull( getTask(), attributeDef );
    }

    @Override
    public boolean setInternalValue( Object value, boolean setIncremental )
    {
        Object oldValue = attributeValue.getInternalValue();
        Object internalValue = attributeDef.getDomain().convertInternalValue( getTask(), this, value );
        if ( attributeValue.setInternalValue( getTask(), attributeDef, internalValue, setIncremental ) )
        {
            if ( ! attributeDef.isDerived() && setIncremental )
                entityInstance.setUpdated( true, true, attributeDef.isPersistent() );

            entityInstance.updateHashKeyAttributeToMap( attributeDef, oldValue );
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean isUpdated()
    {
        return attributeValue.isUpdated();
    }

    @Override
    public EntityInstance setIsUpdated( boolean isUpdated )
    {
        attributeValue.setUpdated( isUpdated );
        return entityInstance;
    }

    @Override
    public View getView()
    {
        if ( view == null )
            view = getObjectInstance().createView( entityInstance );

        return view;
    }

    @Override
    public EntityInstance setDerivedValue( Object value )
    {
        setInternalValue( value, false );
        return entityInstance;
    }

    @Override
    public int compare( Object value )
    {
    	executeDerivedOper( );
        return attributeValue.compare( getTask(), this, attributeDef, value );
    }

    @Override
    public String toString()
    {
        return attributeDef.toString() + ": " + getValue();
    }

    @Override
    public EntityInstance add( Object value )
    {
        Domain domain = attributeDef.getDomain();
        Object newValue = domain.addToAttribute( getTask(), this, attributeDef, getValue(), value );
        return setValue( newValue );
    }

    @Override
    public EntityInstance add( Object value, String contextName )
    {
        Domain domain = attributeDef.getDomain();
        Object newValue = domain.addToAttribute( getTask(), this, attributeDef, getValue(), value, contextName );
        return setValue( newValue );
    }

    @Override
    public EntityInstance multiply( Object value )
    {
        Domain domain = attributeDef.getDomain();
        Object newValue = domain.multiplyAttribute( getTask(), this, attributeDef, getValue(), value );
        return setValue( newValue );
    }

    @Override
    public boolean isBlank()
    {
        return isEmpty() || StringUtils.isBlank( getString() );
    }

    @Override
    public boolean isEmpty()
    {
        return isNull() || compare( "" ) == 0;
    }

    @Override
    public int hashCode()
    {
        if ( isNull() )
            return -1;

        return attributeValue.getInternalValue().hashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        return compare( obj ) == 0;
    }

    @Override
    public Blob getBlob()
    {
        executeDerivedOper();
        return attributeValue.getBlob( getTask(), attributeDef );
    }
}
