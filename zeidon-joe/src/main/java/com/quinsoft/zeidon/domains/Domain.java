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


import org.joda.time.DateTime;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.DomainType;
import com.quinsoft.zeidon.objectdefinition.InternalType;

/**
 * Interface for all domains.
 *
 * @author DG
 *
 */
public interface Domain
{
    Application getApplication();
    DomainType getDomainType();
    InternalType getDataType();
    DomainContext getContext(Task task, String contextName);
    String getName();

    /**
     * Takes an external value and converts it into a value used internally value using domain processing.
     *
     * @param task TODO
     * @param attributeInstance If not null, the is the target attribute.  May be null; this can happen
     * when converting a value to be used in generated SQL.
     * @param attributeDef TODO
     * @param contextName - Context name.
     * @param externalValue - External value
     * @return internal value.
     */
    Object convertExternalValue( Task              task,
                                 AttributeInstance attributeInstance,
                                 AttributeDef      attributeDef,
                                 String            contextName,
                                 Object            externalValue ) throws InvalidAttributeValueException;

    /**
     * Takes a value that is an internal value but may may be of a different type and converts it
     * into an internal value data type.  This is used when setting the attribute value from the DB.
     *
     * Normally this method is the same as convertExternalValue but some domains require different
     * conversion algorithms.  For example, the PasswordDomain normally encrypts an external string
     * but when loaded from the DB the encrypting is not necessary.
     *
     * @param task
     * @param attributeInstance TODO
     * @param value
     * @return
     * @throws InvalidAttributeValueException
     */
    Object convertInternalValue( Task          task,
                                 AttributeInstance attributeInstance,
                                 Object        value ) throws InvalidAttributeValueException;

    /**
     * Compares an external value to an internal attribute value.
     *
     * @param task
     * @param attributeInstance If not null, the is the target attribute.  May be null; this can happen
     * when converting a value to be used in generated SQL.
     * @param attributeDef TODO
     * @param internalValue - Internal attribute value.
     * @param externalValue
     * @return -1 if o1 < o2
     *          0 if o1 = o2
     *          1 if o1 > o2
     */
    int compare( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object internalValue,
                 Object externalValue );

    /**
     * Validates that the object 'value' is a valid class and value for this domain.
     *
     * @param task
     * @param attributeDef
     * @param internalValue
     * @throws InvalidAttributeValueException
     */
    void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException;

    boolean isNull( Task task, AttributeDef attributeDef, Object value );

    /**
     * Converts the internal value of the domain to a string.
     *
     * @param task
     * @param attributeDef
     * @param internalValue
     * @param contextName
     * @return
     */
    String   convertToString( Task task, AttributeDef attributeDef, Object internalValue, String contextName );
    Integer  convertToInteger( Task task, AttributeDef attributeDef, Object internalValue, String contextName );
    Double   convertToDouble( Task task, AttributeDef attributeDef, Object internalValue, String contextName );
    DateTime convertToDate( Task task, AttributeDef attributeDef, Object internalValue, String contextName );
    Blob     convertToBlob( Task task, AttributeDef attributeDef, Object internalValue, String contextName );
    Boolean  convertToBoolean( Task task, AttributeDef attributeDef, Object internalValue, String contextName );

    String   convertToString( Task task, AttributeDef attributeDef, Object internalValue );
    Integer  convertToInteger( Task task, AttributeDef attributeDef, Object internalValue );
    Double   convertToDouble( Task task, AttributeDef attributeDef, Object internalValue );
    DateTime convertToDate( Task task, AttributeDef attributeDef, Object internalValue );
    Blob     convertToBlob( Task task, AttributeDef attributeDef, Object internalValue );
    Boolean  convertToBoolean( Task task, AttributeDef attributeDef, Object internalValue );

    Object addToAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand );
    Object multiplyAttribute( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object currentValue, Object operand );

    /**
     * Creates an empty context which will then be initialized from values in zeidon.xdm.  Used when
     * loading domains from zeidon.xdm.
     * @param task TODO
     *
     * @return
     */
    DomainContext newContext(Task task);

    void addContext( Task task, DomainContext context );
}