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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;
/**
 * This relinks multiple OIs.
 *
 * @author DG
 *
 */
public class OiRelinker
{
    private final TaskQualification task;
    private final Set<ObjectInstance> oiList = new HashSet<ObjectInstance>( 4 );

    /**
     * The root map of all the entities.
     *      Key = ViewEntity Entity tokens.
     *      Value = map of all entities for that entity token.
     *
     * The inner map is map of entities stored by key.
     *      Key = concatenated string of all key values of the entity instance.
     *      Value = an entity instance.
     *
     * We use TIntObjectHashMap because it is faster/smaller when dealing with integer keys.
     */
    private final Map<Integer,Map<String,EntityInstance>> entityTokens;

    public OiRelinker( TaskQualification taskQual )
    {
        task = taskQual;
        entityTokens = new HashMap<Integer, Map<String,EntityInstance>>( 100 );
    }

    OiRelinker add( ObjectInstance oi )
    {
        oiList.add( oi );
        return this;
    }

    /**
     * Adds the entity instance to the relinker cache.  If an entity with the same key already
     * exists then the ei will be relinked with the one in the cache.
     * @param ei
     * @param entityKeyString a string representation of the EI's keys.  May not be null.
     * @return true if the entity was relinked, false otherwise.
     */
    private boolean addEntity( final EntityInstance ei, final String entityKeyString )
    {
        // Asserting that the key not be blank may be wrong because some day we may support
        // string keys but since everybody uses an integer as the key we can be a bit more
        // restrictive for now.
        assert ! StringUtils.isBlank( entityKeyString );
        ViewEntity viewEntity = ei.getViewEntity();
        int token = viewEntity.getErEntityToken();

        Map<String, EntityInstance> tokenMap = entityTokens.get( token );
        if ( tokenMap == null )
        {
            tokenMap = new HashMap<String, EntityInstance>( 1000 );
            entityTokens.put( token, tokenMap );
        }

        EntityInstance cachedEntity = tokenMap.get( entityKeyString );
        if ( cachedEntity == null )
        {
            // We haven't seen this key before so just store the EI and move on to the next one.
            tokenMap.put( entityKeyString, ei );
            return false;
        }

        // If we get here then we've found an entity that matches the key values in 'ei'.
        // Relink ei with cachedEntity.
        return ei.linkInstances( cachedEntity ); // Returns false if they're already linked.
    }

    int relinkOis()
    {
        int totalRelinked = 0; // We'll keep track of the number of entities we relink.

        for ( final ObjectInstance oi : oiList )
        {
            for ( final EntityInstanceImpl ei : oi.getEntities() )
            {
                String entityKeyString = ei.getKeyString();
                if ( entityKeyString == null )
                    continue; // We can't relink entities with null keys.

                if ( addEntity( ei, entityKeyString ) )
                    totalRelinked++;
            }
        }

        task.log().debug( "Linked %d instances", totalRelinked );
        return totalRelinked;
    } // relinkOis()
}
