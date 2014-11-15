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
package com.quinsoft.zeidon.standardoe;

import org.joda.time.DateTime;

import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.TemporalEntityException;
import com.quinsoft.zeidon.UnknownAttributeDefException;
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
    private final AttributeDef      attributeDef;
    private final AttributeValue     attributeValue;
    private final EntityInstanceImpl entityInstance;


    AttributeInstanceImpl( AttributeDef attributeDef,
                           AttributeValue attributeValue,
                           EntityInstanceImpl entityInstance )
    {
        super();
        this.attributeDef = attributeDef;
        this.attributeValue = attributeValue;
        this.entityInstance = entityInstance;
    }

    AttributeInstanceImpl setView( View view )
    {
        this.view = view;
        return this;
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
        return attributeValue.getInternalValue();
    }

    @Override
    public Object getValue()
    {
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
    private void validateUpdateAttribute()
    {
        // If this EI is versioned they we'll assume that it's ok because the versioning
        // code should have already verified that none of the other instances are versioned.
        if ( entityInstance.isVersioned() )
            return;

        // If the attribute is derived or work, then we do not need to check if the
        // attribute can be updated.
        if ( attributeDef.isDerived() )
            return;

        // If the attribute is Persistent, then it is an attribute from the ER.
        // Otherwise it's a work attribute and can be updated even if the
        // object is read only.
        if ( ! attributeDef.isPersistent() )
            return;

        if ( ! attributeDef.isUpdate() )
            throw new ZeidonException( "Attribute is defined as read-only" )
                                .prependAttributeDef( attributeDef );

        // If the entity is derived or work, then we do not need to check if the
        // attribute can be updated.
        if ( getEntityDef().isDerived() || getEntityDef().isDerivedPath() )
            return;

        if ( getObjectInstance().isReadOnly() )
            throw new ZeidonException( "Object Instance is read-only" )
                                .prependEntityDef( getEntityDef() );

        for ( EntityInstanceImpl linked : entityInstance.getLinkedInstances() )
        {
            if ( ! entityInstance.temporalVersionMatch( linked ) )
                throw new TemporalEntityException( entityInstance,
                            "Attempting to update an entity that is linked to a versioned instance" );
        }
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
        if ( attributeDef.isHidden() )
            throw new UnknownAttributeDefException( getEntityDef(), attributeDef.getName() );

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
        return attributeValue.isNull( getTask(), attributeDef );
    }

    @Override
    public EntityInstanceImpl setInternalValue( Object value, boolean setIncremental )
    {
        Object oldValue = attributeValue.getInternalValue();
        Object internalValue = attributeDef.getDomain().convertInternalValue( getTask(), this, value );
        if ( attributeValue.setInternalValue( getTask(), attributeDef, internalValue, setIncremental ) )
        {
            if ( ! attributeDef.isDerived() )
                entityInstance.setUpdated( true, true, attributeDef.isPersistent() );

            entityInstance.updateHashKeyAttributeToMap( attributeDef, oldValue );
        }

        return entityInstance;
    }

    @Override
    public boolean isUpdated()
    {
        return attributeValue.isUpdated();
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
        return setInternalValue( value, false );
    }

    @Override
    public int compare( Object value )
    {
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
        attributeValue.addToAttribute( getTask(), this, value );
        return entityInstance;
    }

    @Override
    public EntityInstance multiply( Object value )
    {
        attributeValue.multiplyAttribute( getTask(), this, value );
        return entityInstance;
    }
}
