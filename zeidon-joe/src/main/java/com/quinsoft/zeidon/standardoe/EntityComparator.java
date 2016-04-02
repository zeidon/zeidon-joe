/**
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
package com.quinsoft.zeidon.standardoe;

import java.util.Set;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * @author DG
 *
 */
class EntityComparator
{
    private static final Object ANY_VALUE = new Object();

    private final AttributeDef attributeDef;
    private final boolean       allowHidden;
    private final Object        compareValue;

    EntityComparator(AttributeDef attributeDef, boolean allowHidden, Object compareValue)
    {
        super();
        this.attributeDef = attributeDef;
        this.allowHidden = allowHidden;
        this.compareValue = compareValue;
    }

    boolean isEqual( EntityInstanceImpl entityInstance )
    {
        if ( ! allowHidden && entityInstance.isHidden() )
            return false;

        // If compareValue is ANY_VALUE then return true.  This allows us to have a
        // comparator that checks just the hidden flag.
        if ( compareValue == ANY_VALUE )
            return true;

        return entityInstance.getAttribute( attributeDef ).compare( compareValue ) == 0;
    }

    AttributeDef getAttributeDef()
    {
        return attributeDef;
    }

    /**
     * Returns true for any non-hidden entity.
     *
     * @author DG
     *
     */
    static class NonHiddenComparator extends EntityComparator
    {
        NonHiddenComparator( )
        {
            super( null, false, ANY_VALUE );
        }
    }

    static class AlwaysTrueComparator extends EntityComparator
    {
        AlwaysTrueComparator( )
        {
            super( null, true, ANY_VALUE );
        }
    }

    /**
     * Returns true if the entity instance has the same ER token.
     */
    static class EntityDefComparator extends EntityComparator
    {
        private final EntityDef entityDef;

        EntityDefComparator( EntityDef entity )
        {
            super( null, false, ANY_VALUE );
            entityDef = entity;
        }

        @Override
        boolean isEqual( EntityInstanceImpl entityInstance )
        {
            if ( ! super.isEqual( entityInstance ) )
                return false;

            if ( entityInstance.getEntityDef() == entityDef )
                return true;

            //TODO: Handle recursive entities.

            return false;
        }
    }

    /**
     * Returns true if the entity instance is contained in a set.
     */
    static class InSetComparator extends EntityComparator
    {
        private final Set<EntityInstance> selectSet;

        InSetComparator( Set<EntityInstance> selectSet )
        {
            super( null, false, ANY_VALUE );
            this.selectSet = selectSet;
        }

        @Override
        boolean isEqual( EntityInstanceImpl entityInstance )
        {
            return selectSet.contains( entityInstance );
        }
    }
}
