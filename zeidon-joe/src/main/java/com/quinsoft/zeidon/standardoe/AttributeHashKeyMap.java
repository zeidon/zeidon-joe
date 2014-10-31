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

import java.util.HashMap;
import java.util.Map;

import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.AttributeHashKeyType;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * This class keeps track of attribute hash keys.  AHKs are attributes that have values
 * that are unique in an OI or unique among siblings under a parent.  The uniqueness allows
 * us to keep a hashmap of the attributes values pointing to the entity instance.  This can
 * make cursor processing much faster if we're setting the cursor by the attribute value.
 *
 * @author dg
 *
 */
class AttributeHashKeyMap
{
    private final ObjectInstance objectInstance;

    private Map<AttributeDef,Map<Object,EntityInstanceImpl>> hashKeyAttributeMap;

    /**
     * This constructor is used in many places and should be as low-impact as possible.
     *
     * @param oi
     */
    AttributeHashKeyMap( ObjectInstance oi )
    {
        objectInstance = oi;
    }

    private TaskImpl getTask()
    {
        return objectInstance.getTask();
    }

    private Map<Object, EntityInstanceImpl> getHashMapForAttribute( AttributeDef attributeDef )
    {
        if ( hashKeyAttributeMap == null )
            hashKeyAttributeMap = new HashMap<AttributeDef, Map<Object,EntityInstanceImpl>>();

        // Check to see if the attribute is part of a recursive child.  If it is then
        // we will use the recursive parent attribute to store values in the map.
        EntityDef recursiveParent = attributeDef.getEntityDef().getRecursiveParentEntityDef();
        if ( recursiveParent != null )
            attributeDef = recursiveParent.getAttributeByErToken( attributeDef.getErAttributeToken() );

        Map<Object, EntityInstanceImpl> map = hashKeyAttributeMap.get( attributeDef );
        if ( map == null )
        {
            map = new HashMap<Object, EntityInstanceImpl>();
            hashKeyAttributeMap.put( attributeDef, map );
        }

        return map;
    }

    /**
     * Called by an assert to verify that the hashkey map has everything it should and nothing more.
     *
     * @return true if everything ok, otherwise it will use an assert to throw an error.
     */
    private boolean assertHashKeyCorrectness()
    {
        if ( hashKeyAttributeMap == null )
            return true;

        // Loop through all the entities and make sure all attrib keys are in the
        // map.
        int hashKeyAttrCount = 0;
        for ( EntityInstanceImpl ei : objectInstance.getEntities( false ) )
        {
            if ( ei.getEntityDef().getHashKeyAttributes() == null )
                continue;

            for ( AttributeDef attributeDef : ei.getEntityDef().getHashKeyAttributes() )
            {
                if ( attributeDef.getHashKeyType() == AttributeHashKeyType.NONE )
                    continue;

                Object value = ei.getAttribute( attributeDef ).getValue();
                AttributeHashKeyMap hashkeyMap = ei.getAttributeHashkeyMap( attributeDef );
                Map<Object, EntityInstanceImpl> map = hashkeyMap.getHashMapForAttribute( attributeDef );
                EntityInstanceImpl hashEi = map.get( value );

                if ( hashkeyMap == this )
                    hashKeyAttrCount++;

                if ( hashEi == null )
                    throw new ZeidonException( "Attribute hashkey value is missing from table." )
                                    .prependEntityInstance( ei )
                                    .prependAttributeDef( attributeDef )
                                    .appendMessage( "HashKey value = %s", value );

                if ( ei != hashEi )
                    throw new ZeidonException( "Attribute hashkey returns wrong EI." )
                                    .prependEntityInstance( ei )
                                    .prependAttributeDef( attributeDef )
                                    .appendMessage( "HashKey value = %s", value.toString() )
                                    .appendMessage( "Wrong EI = %s", hashEi.toString() );
            }
        }

        int count = 0;
        for ( Map<Object, EntityInstanceImpl> list : hashKeyAttributeMap.values() )
            count += list.size();

        if ( count != hashKeyAttrCount )
            throw new ZeidonException( "Unequal hash count values." );

        return true;
    }

    synchronized void addHashKey( AttributeDef attributeDef, EntityInstanceImpl entityInstance )
    {
        Map<Object, EntityInstanceImpl> map = getHashMapForAttribute( attributeDef );
        Object internalValue = entityInstance.getAttribute( attributeDef ).getValue();
        if ( internalValue == null )
        {
            throw new ZeidonException( "Attempting to add null attribute value to attribute hashmap" )
                            .prependAttributeDef( attributeDef );
        }

        if ( map.containsKey( internalValue ) )
        {
                throw new ZeidonException( "Attempting to add duplicate attribute values to attribute hashmap" )
                            .prependAttributeDef( attributeDef )
                            .appendMessage( "Attribute value = %s", internalValue );
        }

        map.put( internalValue, entityInstance );
    }

    synchronized void removeHashKey( AttributeDef attributeDef, EntityInstanceImpl entityInstance )
    {
        Map<Object, EntityInstanceImpl> map = getHashMapForAttribute( attributeDef );
        Object internalValue = entityInstance.getAttribute( attributeDef ).getValue();
        if ( internalValue == null )
        {
            throw new ZeidonException( "Attempting to remove null attribute value from attribute hashmap" )
                            .prependAttributeDef( attributeDef );
        }

        if ( ! map.containsKey( internalValue ) )
        {
                throw new ZeidonException( "Attempting to remove non-existent attribute value from attribute hashmap" )
                            .prependAttributeDef( attributeDef )
                            .appendMessage( "Attribute value = %s", internalValue );
        }

        map.remove( internalValue );
    }

    /**
     * This removes a key value from the map and reinserts an entity instance with a new value.
     * This can handle null.
     *
     * @param attributeDef
     * @param entityInstance
     */
    synchronized void updateHashKey( Object oldValue, AttributeDef attributeDef, EntityInstanceImpl entityInstance )
    {
        Map<Object, EntityInstanceImpl> map = getHashMapForAttribute( attributeDef );

        Domain domain = attributeDef.getDomain();
        if ( ! domain.isNull( getTask(), attributeDef, oldValue ) )
        {
            if ( ! map.containsKey( oldValue ) )
            {
                    throw new ZeidonException( "Attempting to update attribute value but old value doesn't exist." )
                                .prependAttributeDef( attributeDef )
                                .appendMessage( "Old Attribute value = %s", oldValue );
            }

            map.remove( oldValue );
        }

        Object internalValue = entityInstance.getAttribute( attributeDef ).getValue();
        if ( domain.isNull( getTask(), attributeDef, internalValue ) )
        {
            throw new ZeidonException( "Attempting to update null attribute value from attribute hashmap" )
                            .prependAttributeDef( attributeDef );
        }

        if ( map.containsKey( internalValue ) )
        {
                throw new ZeidonException( "Attempting to add duplicate attribute values to attribute hashmap" )
                            .prependAttributeDef( attributeDef )
                            .appendMessage( "Attribute value = %s", internalValue );
        }

        map.put( internalValue, entityInstance );
    }

    /**
     * Replaces the entity currently associated with a key with a new one.
     *
     * @param attributeDef
     * @param entityInstance
     */
    synchronized void replaceHashKey( AttributeDef attributeDef, EntityInstanceImpl entityInstance )
    {
        Map<Object, EntityInstanceImpl> map = getHashMapForAttribute( attributeDef );
        Object internalValue = entityInstance.getAttribute( attributeDef ).getValue();
        if ( internalValue == null )
        {
            throw new ZeidonException( "Attempting to replace null attribute value from attribute hashmap" )
                            .prependAttributeDef( attributeDef );
        }

        if ( ! map.containsKey( internalValue ) )
        {
                throw new ZeidonException( "Attempting to replace non-existent attribute value from attribute hashmap" )
                            .prependAttributeDef( attributeDef )
                            .appendMessage( "Attribute value = %s", internalValue );
        }

        map.put( internalValue, entityInstance );
    }

    /**
     * @param attributeDef
     * @param externalValue
     * @return
     */
    synchronized EntityInstanceImpl getEntityInstanceUsingHashAttribute( AttributeDef attributeDef, Object externalValue )
    {
        assert assertHashKeyCorrectness();

        Domain domain = attributeDef.getDomain();
        if ( domain.isNull( getTask(), attributeDef, externalValue ) )
            throw new ZeidonException( "Attempting to find null attribute hash value" )
                            .prependAttributeDef( attributeDef );

        Object internalValue = domain.convertExternalValue( getTask(), null, attributeDef, null, externalValue );

        Map<Object, EntityInstanceImpl> map = getHashMapForAttribute( attributeDef );
        EntityInstanceImpl ei = map.get( internalValue );

        assert ei == null || ! ei.isDropped() : "HashKey entity is flagged as dropped.";

        return ei;
    }
}
