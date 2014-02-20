/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import org.joda.time.DateTime;

import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.TemporalEntityException;
import com.quinsoft.zeidon.UnknownViewAttributeException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * @author dgc
 *
 */
class AttributeInstanceImpl implements AttributeInstance
{
    private       View               view;
    private final ViewAttribute      viewAttribute;
    private final AttributeValue     attributeValue;
    private final EntityInstanceImpl entityInstance;


    AttributeInstanceImpl( ViewAttribute viewAttribute,
                           AttributeValue attributeValue,
                           EntityInstanceImpl entityInstance )
    {
        super();
        this.viewAttribute = viewAttribute;
        this.attributeValue = attributeValue;
        this.entityInstance = entityInstance;
    }

    AttributeInstanceImpl setView( View view )
    {
        this.view = view;
        return this;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getViewAttribute()
     */
    @Override
    public ViewAttribute getViewAttribute()
    {
        return viewAttribute;
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
        if ( ! viewAttribute.isDerived() )
            return;

        viewAttribute.executeDerivedAttributeForGet( view );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getString()
     */
    @Override
    public String getString()
    {
        executeDerivedOper();
        return attributeValue.getString( getTask(), viewAttribute );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getLong()
     */
    @Override
    public Integer getInteger()
    {
        executeDerivedOper();
        return attributeValue.getInteger( getTask(), viewAttribute );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getDate()
     */
    @Override
    public DateTime getDateTime()
    {
        executeDerivedOper();
        return attributeValue.getDateTime( getTask(), viewAttribute );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getString(java.lang.String)
     */
    @Override
    public String getString( String contextName )
    {
        executeDerivedOper();
        return attributeValue.getString( getTask(), viewAttribute, contextName );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getLong(java.lang.String)
     */
    @Override
    public Integer getInteger( String contextName )
    {
        executeDerivedOper();
        return attributeValue.getInteger( getTask(), viewAttribute, contextName );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.AttributeInstance#getDate(java.lang.String)
     */
    @Override
    public DateTime getDateTime( String contextName )
    {
        executeDerivedOper();
        return attributeValue.getDateTime( getTask(), viewAttribute, contextName );
    }

    @Override
    public Double getDouble()
    {
        executeDerivedOper();
        return attributeValue.getDouble( getTask(), viewAttribute );
    }

    @Override
    public Double getDouble( String contextName )
    {
        executeDerivedOper();
        return attributeValue.getDouble( getTask(), viewAttribute, contextName );
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

    private ViewEntity getViewEntity()
    {
        return viewAttribute.getViewEntity();
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
     * @param viewAttribute
     */
    private void validateUpdateAttribute()
    {
        // If this EI is versioned they we'll assume that it's ok because the versioning
        // code should have already verified that none of the other instances are versioned.
        if ( entityInstance.isVersioned() )
            return;

        // If the attribute is derived or work, then we do not need to check if the
        // attribute can be updated.
        if ( viewAttribute.isDerived() )
            return;

        // If the attribute is Persistent, then it is an attribute from the ER.
        // Otherwise it's a work attribute and can be updated even if the
        // object is read only.
        if ( ! viewAttribute.isPersistent() )
            return;

        if ( ! viewAttribute.isUpdate() )
            throw new ZeidonException( "Attribute is defined as read-only" )
                                .prependViewAttribute( viewAttribute );

        // If the entity is derived or work, then we do not need to check if the
        // attribute can be updated.
        if ( getViewEntity().isDerived() || getViewEntity().isDerivedPath() )
            return;

        if ( getObjectInstance().isReadOnly() )
            throw new ZeidonException( "Object Instance is read-only" )
                                .prependViewEntity( getViewEntity() );

        for ( EntityInstanceImpl linked : entityInstance.getLinkedInstances() )
        {
            if ( ! entityInstance.temporalVersionMatch( linked ) )
                throw new TemporalEntityException( entityInstance,
                            "Attempting to update an entity that is linked to a versioned instance" );
        }
    }

    /**
     * Validate the context name.  If the context name is null or "", returns the default context.
     * @param viewAttribute
     * @param contextName
     * @return
     */
    private String checkContextName( String contextName )
    {
        Domain domain = viewAttribute.getDomain();
        return domain.getContext( getTask(), contextName ).getName();
    }

    @Override
    public EntityInstance setValue( Object value, String contextName )
    {
        if ( viewAttribute.isHidden() )
            throw new UnknownViewAttributeException( getViewEntity(), viewAttribute.getName() );

        validateUpdateAttribute();
        contextName = checkContextName( contextName );
        Object oldValue = attributeValue.getInternalValue();
        if ( attributeValue.set( getTask(), viewAttribute, value, contextName ) )
        {
            if ( ! viewAttribute.isDerived() )
                entityInstance.setUpdated( true, true, viewAttribute.isPersistent() );

            entityInstance.updateHashKeyAttributeToMap( viewAttribute, oldValue );
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
        return attributeValue.isNull( getTask(), viewAttribute );
    }

    @Override
    public EntityInstanceImpl setInternalValue( Object value, boolean setIncremental )
    {
        Object oldValue = attributeValue.getInternalValue();
        if ( attributeValue.setInternalValue( getTask(), viewAttribute, value, setIncremental ) )
        {
            if ( ! viewAttribute.isDerived() )
                entityInstance.setUpdated( true, true, viewAttribute.isPersistent() );

            entityInstance.updateHashKeyAttributeToMap( viewAttribute, oldValue );
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
        {
            view = new ViewImpl( getObjectInstance() );
            view.cursor( viewAttribute.getViewEntity() ).setCursor( entityInstance );
        }

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
        return attributeValue.compare( getTask(), viewAttribute, value );
    }
}
