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
package com.quinsoft.zeidon.domains;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * UUID.
 *
 * Set to "$GENERATE" to generate a new UUID.  Eg.
 *
 *      view.Entity.Attribute = "$GENERATE"
 *
 * $GENERATE may also be used to initialize a UUID.
 *
 */
public class UuidDomain extends AbstractDomain
{

    /**
     * @param app
     * @param domainProperties
     * @param task
     */
    public UuidDomain( Application app, Map<String, Object> domainProperties, Task task )
    {
        super( app, domainProperties, task );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.domains.Domain#convertExternalValue(com.quinsoft.zeidon.Task, com.quinsoft.zeidon.AttributeInstance, com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.String, java.lang.Object)
     */
    @Override
    public Object convertExternalValue( Task task, AttributeInstance attributeInstance, AttributeDef attributeDef,
                                        String contextName, Object externalValue ) throws InvalidAttributeValueException
    {
        // If external value is an AttributeInstance then get *its* internal value.
        if ( externalValue instanceof AttributeInstance )
            externalValue = ((AttributeInstance) externalValue).getValue();

        if ( externalValue == null )
            return null;

        if ( externalValue instanceof UUID )
            return externalValue;

        if ( externalValue instanceof CharSequence )
        {
            if ( StringUtils.isBlank( externalValue.toString() ) )
                return null;

            if ( "$GENERATE".equals( externalValue.toString() ) )
                return UUID.randomUUID();

            return UUID.fromString( externalValue.toString() );
        }

        if ( externalValue instanceof Blob )
            externalValue = ((Blob) externalValue ).getBytes();

        if ( externalValue instanceof byte[] )
        {
            byte[] bytes = (byte[]) externalValue;
            ByteBuffer bb = ByteBuffer.wrap(bytes);
            long high = bb.getLong();
            long low = bb.getLong();
            return new UUID(high, low);
        }

        throw new InvalidAttributeValueException( attributeDef, externalValue, "Can't convert '%s' (class: %s) to UUID",
                                                  externalValue, externalValue.getClass().getName() );
    }

    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object internalValue ) throws InvalidAttributeValueException
    {
        if ( internalValue instanceof UUID )
            return;

        throw new InvalidAttributeValueException( attributeDef, internalValue, "Attribute is expecting a UUID, got %s", internalValue.getClass() );
    }

    @Override
    public Blob convertToBlob(Task task, AttributeDef attributeDef, Object internalValue, String contextName)
    {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        UUID uuid = (UUID) internalValue;
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return new Blob( bb.array() );
    }

    @Override
    public int compare( Task task,
                        AttributeInstance attributeInstance,
                        AttributeDef attributeDef,
                        Object internalValue,
                        Object externalValue )
    {
        UUID externalUuid = (UUID) convertExternalValue( task, attributeInstance, attributeDef, null, externalValue );
        if ( internalValue == null )
            return externalValue == null ? 0 : 1;

        UUID internalUuid = (UUID) internalValue;
        return internalUuid.compareTo( externalUuid );
    }
}
