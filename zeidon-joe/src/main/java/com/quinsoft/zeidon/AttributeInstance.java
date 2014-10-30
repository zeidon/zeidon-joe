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
package com.quinsoft.zeidon;

import org.joda.time.DateTime;

import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * @author dgc
 *
 */
public interface AttributeInstance
{
    AttributeDef getAttributeDef();
    EntityInstance getEntityInstance();

    String getString();
    Integer getInteger();
    DateTime getDateTime();
    Double getDouble();
    Boolean getBoolean();

    String getString( String contextName );
    Integer getInteger( String contextName );
    DateTime getDateTime( String contextName );
    Double getDouble( String contextName );
    Boolean getBoolean( String contextName );

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
     * value is well-formed.  This is intended to be used inside derived operations to set the value.
     *
     * @param value
     * @return this
     */
    EntityInstance setDerivedValue( Object value );

    /**
     * Add a value to the current attribute value.
     *
     * @param value
     * @return
     */
    EntityInstance add( Object value );

    /**
     * Multiply an attribute by a value.
     * @param value
     * @return
     */
    EntityInstance multiply( Object value );

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
