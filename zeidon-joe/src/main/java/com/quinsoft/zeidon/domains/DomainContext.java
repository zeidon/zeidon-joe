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

    Copyright 2009-2012 QuinSoft
 */

package com.quinsoft.zeidon.domains;

import org.joda.time.DateTime;

import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;

/**
 * @author DG
 *
 */
public interface DomainContext extends PortableFileAttributeHandler
{
    String getName();
    boolean isDefault();
    
    /**
     * Takes an external value and converts it into an internal value using domain processing.
     * @param task
     * @param viewAttribute 
     * @param value - External value
     * @return internal value.
     */
    Object convertExternalValue(Task task, ViewAttribute viewAttribute, Object value) throws InvalidAttributeValueException;
    int compare( Task task, Object o1, Object o2 );
    
    void validateInternalValue( Task task, ViewAttribute viewAttribute, Object value ) throws InvalidAttributeValueException;

    /**
     * Converts the internal value of the attribute to a string.
     * @return String or null.
     */
    String   convertToString( Task task, ViewAttribute viewAttribute, Object internalValue );
    Integer  convertToInteger( Task task, ViewAttribute viewAttribute, Object internalValue );
    Double   convertToDouble( Task task, ViewAttribute viewAttribute, Object internalValue );
    DateTime convertToDate( Task task, ViewAttribute viewAttribute, Object internalValue );
    Blob     convertToBlob( Task task, ViewAttribute viewAttribute, Object internalValue );
}