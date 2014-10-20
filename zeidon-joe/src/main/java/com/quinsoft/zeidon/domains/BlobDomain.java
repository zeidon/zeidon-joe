/*
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

import java.util.Map;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * @author DG
 *
 */
public class BlobDomain extends AbstractDomain
{
    public BlobDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }

    @Override
    public Object convertExternalValue(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, String contextName, Object externalValue)
    {
        if ( externalValue instanceof byte[] )
            return new Blob( (byte[]) externalValue );
        
        if ( externalValue instanceof Blob )
            return externalValue;
        
        if ( externalValue instanceof String )
            return new Blob( ((String) externalValue).getBytes() );
        
        throw new InvalidAttributeValueException( attributeDef, externalValue, "Can't convert '%s' to Blob", externalValue.getClass().getName() );
    }
    
    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException
    {
        @SuppressWarnings("unused")
        Blob blob = (Blob) internalValue;
        
        //TODO: Should blobs have a max value?  The length defined in the attributeDef is the number of bytes
        // the old C OE needed to store a blob pointer.
//        if ( blob.getBytes().length > attributeDef.getLength() )
//            throw new InvalidAttributeValueException( attributeDef, value,
//                                                      "Max length of %d exceeded.  Length = %d",
//                                                      attributeDef.getLength(), blob.getBytes().length );
    }
}
