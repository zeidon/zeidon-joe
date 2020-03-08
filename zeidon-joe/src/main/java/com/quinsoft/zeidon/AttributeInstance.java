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
package com.quinsoft.zeidon;

import org.joda.time.DateTime;

import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * Represents an attribute value in a Zeidon OI.
 *
 * <p>
 * The attribute value is stored internally and can be any object.  The AttributeInstance
 * can be used to convert the value to standard classes.  When the value is set through
 * an AttributeInstance the new value is verified (and possibly converted) by the associated
 * domain.
 * </p>
 * <p>
 * The AttributeInstance for an attribute is normally retrieved like this:
 * <pre>
 * {@code
 * View myView = ...;
 * AttributeInstance myAttr = myView.cursor( "MyEntity" ).getAttribute( "MyAttribute" );
 * String strValue = myAttr.getString();
 * }
 * </pre>
 *
 */
public interface AttributeInstance
{
    /**
     * Returns the AttributeDef for this attribute.
     *
     * @return the AttributeDef for this attribute.
     */
    AttributeDef getAttributeDef();

    /**
     * Returns the EntityInstance that contains this attribute.
     *
     * @return the EntityInstance that contains this attribute.
     */
    EntityInstance getEntityInstance();

    /**
     * Returns the internal attribute value as a string.  Note that no context is used
     * to convert the string.  If you want to use the default context call getString( "" )
     * instead.
     *
     * @return the internal attribute value as a string.
     */
    String getString();

    /**
     * Returns the attribute value as an integer if possible.  Otherwise throws
     * InvalidAttributeValueException.
     *
     * @return the attribute value as an integer.
     * @throws InvalidAttributeValueException
     */
    Integer getInteger();

    /**
     * Returns the attribute value as a DateTime if possible.  Otherwise throws
     * InvalidAttributeValueException.  Note that no context is used
     * to convert the DateTime.  If you want to use the default context call getDateTime( "" )
     * instead.
     *
     * @return the attribute value as a DateTime.
     * @throws InvalidAttributeValueException
     */
    DateTime getDateTime();

    /**
     * Returns the attribute value as an double if possible.  Otherwise throws
     * InvalidAttributeValueException.
     *
     * @return the attribute value as an double.
     * @throws InvalidAttributeValueException
     */
    Double getDouble();

    /**
     * Returns the attribute value as an boolean if possible.  Otherwise throws
     * InvalidAttributeValueException.
     *
     * @return the attribute value as an boolean.
     * @throws InvalidAttributeValueException
     */
    Boolean getBoolean();

    /**
     * Returns the internal attribute value as a string.  The supplied contextName
     * will be used to convert the value.  To specify the default context use ""
     * (empty string).
     *
     * @return the internal attribute value as a string.
     */
    String getString( String contextName );

    /**
     * Returns the attribute value as an integer if possible.  Otherwise throws
     * InvalidAttributeValueException.  The supplied contextName
     * will be used to convert the value.  To specify the default context use ""
     * (empty string).
     *
     * @return the attribute value as an integer.
     * @throws InvalidAttributeValueException
     */
    Integer getInteger( String contextName );

    /**
     * Returns the attribute value as a DateTime if possible.  Otherwise throws
     * InvalidAttributeValueException.  The supplied contextName
     * will be used to convert the value.  To specify the default context use ""
     * (empty string).
     *
     * @return the attribute value as a DateTime.
     * @throws InvalidAttributeValueException
     */
    DateTime getDateTime( String contextName );

    /**
     * Returns the attribute value as an double if possible.  Otherwise throws
     * InvalidAttributeValueException.  The supplied contextName
     * will be used to convert the value.  To specify the default context use ""
     * (empty string).
     *
     * @return the attribute value as an double.
     * @throws InvalidAttributeValueException
     */
    Double getDouble( String contextName );

    /**
     * Returns the attribute value as an boolean if possible.  Otherwise throws
     * InvalidAttributeValueException.  The supplied contextName
     * will be used to convert the value.  To specify the default context use ""
     * (empty string).
     *
     * @return the attribute value as an boolean.
     * @throws InvalidAttributeValueException
     */

    Boolean getBoolean( String contextName );

    Blob getBlob();

    /**
     * @deprecated Use getValue() instead.
     */
    @Deprecated
    Object getInternalAttributeValue();

    /**
     * Returns the internal object that represents the attribute value.  For most attributes the value
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
     * @param value the value to set
     * @return this
     * @throws InvalidAttributeValueException
     */
    EntityInstance setValue( Object value ) throws InvalidAttributeValueException;

    /**
     * Set the attribute value to 'value'.  This uses domain processing to validate and
     * potential convert the value to the internal representation.  If contextName is
     * supplied it will be used to help in the conversion.
     *
     * @param value the value to set
     * @param contextName the name of the context.
     * @return this
     * @throws InvalidAttributeValueException
     */
    EntityInstance setValue( Object value, String contextName ) throws InvalidAttributeValueException;

    /**
     * Sets the value of the attribute without attempting to convert it first.  This still validates that the
     * value is well-formed.  This is intended to be used inside derived operations to set the value.
     *
     * @param value
     * @return this
     */
    EntityInstance setDerivedValue( Object value );

    /**
     * Add a value to the current attribute value.  What "add" means is domain-specific.  For
     * example, for string domains it will likely mean concatenation.
     *
     * @param value the value to add to the attribute
     * @return this
     */
    EntityInstance add( Object value );

    /**
     * Add a value to the current attribute value with a context.  What "add" means is domain-specific.  For
     * example, for string domains it will likely mean concatenation.  The context name is also domain-specific.
     *
     * @param value the value to add to the attribute
     * @return this
     */
    EntityInstance add( Object value, String contextName );

    /**
     * Multiply an attribute by a value.  What "multiply" means is domain-specific.
     *
     * @param value the current attribute value will be multiplied by this value.
     *
     * @return this
     */
    EntityInstance multiply( Object value );

    /**
     *
     * Sets the value of the attribute without attempting to convert it first.  This still validates that the
     * value is well-formed.
     *
     * @param value
     * @param setIncremental if 'false' then don't update attribute/entity update flags.
     * @return true if the value was updated, false if it wasn't (i.e. current value matches value).
     */
    boolean setInternalValue( Object value, boolean setIncremental );

    /**
     * Compares the attribute to 'value'.  This will using domain processing to convert
     * the value to the same type as the attribute.
     *
     * @param value to be compared.
     *
     * @return -1, 0, or 1
     */
    int compare( Object value );

    /**
     * Determines if the attribute value is null.
     *
     * @return true if attribute value is null.
     */
    boolean isNull();

    /**
     * Returns true if attribute value is:
     *  Null
     *  -or-
     *  Comparison with "" is true (which is domain-specific).
     *  -or-
     *  String value is all white-space.
     *
     * @return true if Null, empty string, or whitespace.
     */
    boolean isBlank();

    /**
     * Returns true if attribute value is:
     *  Null
     *  -or-
     *  Comparison with "" is true (which is domain-specific).
     *
     * @return true if Null or empty string.
     */
    boolean isEmpty();

    /**
     * Determines if the attribute value has been updated since it was loaded from the
     * database.  I.e. this can be considered as a 'dirty' flag.  If the OI is committed
     * this attribute will be updated.
     *
     * @return true if attribute has been updated.
     */
    boolean isUpdated();

    EntityInstance setIsUpdated( boolean isUpdated );

    /**
     * Returns a view associated with this attribute instance.  Note that this may cause
     * a view to be created.
     *
     * @return the owning view.
     */
    View getView();

    /**
     * Return the owning task.
     *
     * @return the owning task.
     */
    Task getTask();
}
