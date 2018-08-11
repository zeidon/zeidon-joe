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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * Used by the (de-)serializers to map LOD entity/attributes names to different names
 * in the external XML/JSON.
 *
 */
public class SerializationMapping
{
    public static final SerializationMapping NOOP_MAPPING = new SerializationMapping();

    private final Map<String, EntityMapping> entityMappings = new HashMap<>();
    private final ListMultimap<String, EntityMapping> recordMappings = ArrayListMultimap.create();

    /**
     *
     */
    public SerializationMapping()
    {
    }

    /**
     * Returns an entity if the recordName matches either an EntityName or a entity mapping.
     * It is valid to pass in a recordName that does not match any entity; in this case
     * return null.
     *
     * @param recordName
     * @return
     */
    public EntityDef getEntityFromRecord( String recordName, EntityDef parent, LodDef lodDef )
    {
        List<EntityMapping> list = recordMappings.get( recordName );
        if ( list.size() == 1 )
        {
            // If there's only 1 record->entity mapping we'll just get the entity directly.
            return lodDef.getEntityDef( list.get( 0 ).entityName, false );
        }
        else
        if ( list.size() > 1 )
        {
            // We have multiple record->entity mappings.  To determine which one we want we need
            // to go through the mappings and find the entity that has a parent matching 'parent'.
            for ( EntityMapping emap : list )
            {
                EntityDef entityDef = lodDef.getEntityDef( emap.entityName, false );
                if ( entityDef != null && entityDef.getParent() == parent )
                    return entityDef;
            }
        }

        return lodDef.getEntityDef( recordName, false );
    }

    public String entityToRecord( EntityDef entityDef )
    {
        EntityMapping entityMapping = entityMappings.get( entityDef.getName() );
        if ( entityMapping == null )
            return entityDef.getName();

        return entityMapping.recordName;
    }

    private SerializationMapping addEntityMapping( EntityMapping entityMapping )
    {
        this.entityMappings.put( entityMapping.entityName, entityMapping );
        this.recordMappings.put( entityMapping.recordName, entityMapping );
        return this;
    }

    public static SerializationMapping fromOi( View serializationMapping )
    {
        LodDef lod = serializationMapping.getLodDef();
        if ( ! StringUtils.equals( lod.getName(), "SerializationMapping" ) )
            throw new ZeidonException( "Must be called with instance of SerializationMapping" );

        if ( serializationMapping.cursor( "SerializationMapping" ).checkExistenceOfEntity().isEmpty() )
            throw new ZeidonException( "SerializationMapping root is null" );

        if ( serializationMapping.cursor( "Entity" ).checkExistenceOfEntity().isEmpty() )
            throw new ZeidonException( "No 'Entity' mappings specified" );

        SerializationMapping mapping = new SerializationMapping();
        for ( EntityInstance entity : serializationMapping.cursor( "Entity" ).eachEntity() )
        {
            EntityMapping emap = new EntityMapping();
            emap.entityName = entity.getAttribute( "EntityName" ).getString();
            emap.recordName = entity.getAttribute( "RecordName" ).getString();
            mapping.addEntityMapping( emap );
        }

        return mapping;
    }

    public static class EntityMapping
    {
        public String entityName;
        public String recordName;
    }
}
