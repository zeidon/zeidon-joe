/**
 *
 */
package com.quinsoft.zeidon;

import org.joda.time.DateTime;

import com.quinsoft.zeidon.objectdefinition.ViewAttribute;

/**
 * @author dgc
 *
 */
public interface AttributeInstance
{
    ViewAttribute getViewAttribute();
    EntityInstance getEntityInstance();

    String getString();
    Integer getInteger();
    DateTime getDateTime();
    Double getDouble();

    String getString( String contextName );
    Integer getInteger( String contextName );
    DateTime getDateTime( String contextName );
    Double getDouble( String contextName );

    /**
     * @deprecated Use getValue() instead.
     */
    @Deprecated
    Object getInternalAttributeValue();

    /**
     * Returns the object that represents the attribute value.  For most attributes the value
     * will be immutable or it will be a copy of the value.  Attribute values can only be
     * changed via setValue().
     *
     * @return attribute value.
     */
    Object getValue();

    /**
     * Set the attribute value to 'value'.  This uses domain processing to validate and
     * potential convert the value to the internal representation.
     *
     * @param value
     * @return this
     */
    EntityInstance setValue( Object value );

    /**
     * Set the attribute value to 'value'.  This uses domain processing to validate and
     * potential convert the value to the internal representation.  If contextName is
     * supplied it will be used to help in the conversion.
     *
     * @param value
     * @param contextName
     * @return this
     */
    EntityInstance setValue( Object value, String contextName );

    /**
     * Sets the value of the attribute without attempting to convert it first.  This still validates that the
     * value is well-formed.
     *
     * @param value
     * @return this
     */
    EntityInstance setDerivedValue( Object value );

    /**
     *
     * Sets the value of the attribute without attempting to convert it first.  This still validates that the
     * value is well-formed.
     *
     * @param value
     * @param setIncremental if 'false' then don't update attribute/entity update flags.
     *
     * @return
     */
    EntityInstance setInternalValue( Object value, boolean setIncremental );

    /**
     * Compares the attribute to 'value'.  This will using domain processing to convert
     * the value to the same type as the attribute.
     *
     * @param value to be compared.
     *
     * @return -1, 0, or 1
     */
    int compare( Object value );

    boolean isNull();
    boolean isUpdated();

    /**
     * Returns a view associated with this attribute instance.  Note that this may cause
     * a view to be created.
     *
     * @return
     */
    View getView();

    Task getTask();
}
