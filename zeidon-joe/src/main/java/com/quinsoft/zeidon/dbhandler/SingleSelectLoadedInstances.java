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
package com.quinsoft.zeidon.dbhandler;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.objectdefinition.DataField;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Keeps track of entity instances loaded by a sql handler as part of single-select
 * processing.
 */
class SingleSelectLoadedInstances
{
    /**
     * This is the list of instances that have been loaded.  It is limited to instances
     * that have children that can be loaded in one SELECT.
     *
     * The sub-map is keyed by the key value of the entity instance.
     */
    private final Map<DataField, Map<Object, List<EntityInstance>>> parentInstances = new HashMap<>();

    /**
     * If a entityDef is in this set it can be loaded in a single select for this activate.
     */
    private final Set<EntityDef> loadInOneSelect = new HashSet<>();

    private final Set<DataField> sourceDataFields = new HashSet<>();

    SingleSelectLoadedInstances()
    {
    }

    boolean isLoadInSingleSelect( EntityDef entityDef )
    {
        return loadInOneSelect.contains( entityDef );
    }

    boolean isParentKeyForSingleSelect( DataField dataField )
    {
        return sourceDataFields.contains( dataField );
    }

    void addEntityDef( EntityDef entityDef )
    {
        loadInOneSelect.add( entityDef );
        DataField parentField = entityDef.getDataRecord().getSingleActivateParentKey();
        sourceDataFields.add( parentField );
        parentInstances.putIfAbsent( parentField, new HashMap<>() );
    }

    /**
     * Returns the list of instances that match the key.
     */
    List<EntityInstance> getParentInstances( DataField keyField, Object key )
    {
        return parentInstances.get( keyField ).get( key );
    }

    Set<Object> getParentInstanceKeys( DataField keyField )
    {
        return parentInstances.get( keyField ).keySet();
    }

    void addParentInstance( EntityInstance entityInstance, DataField keyField, Object key )
    {
        Map<Object, List<EntityInstance>> entityMap = parentInstances.get( keyField );
        if ( ! entityMap.containsKey( key ) )
            entityMap.put( key, new ArrayList<>() );

        entityMap.get( key ).add( entityInstance );
    }
}
