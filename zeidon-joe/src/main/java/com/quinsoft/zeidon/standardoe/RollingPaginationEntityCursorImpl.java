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
package com.quinsoft.zeidon.standardoe;

import java.util.Iterator;

import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.EntityIterator;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * This cursor is used for rolling pagination.
 *
 */
public class RollingPaginationEntityCursorImpl extends EntityCursorImpl
{
    private ActivateOptions activateOptions;

    /**
     * @param viewCursor
     * @param entityDef
     * @param options
     */
    public RollingPaginationEntityCursorImpl( ViewCursor viewCursor, EntityDef entityDef, ActivateOptions options )
    {
        super( viewCursor, entityDef );
        assert entityDef.getParent() == null : "Rolling pagination only allowed on the root";
        this.activateOptions = options;
    }

    private CursorResult loadNextPage()
    {
        EntityInstanceImpl ei = getEntityInstance();
        return CursorResult.UNCHANGED;
    }

    @Override
    public CursorResult setNext()
    {
        CursorResult result = super.setNext();
        if ( result.isSet() )
            return result;

        return loadNextPage();
    }

    @Override
    public EntityIterator<EntityInstanceImpl> eachEntity()
    {
        return new RollingIterator();
    }

    @Override
    public EntityIterator<? extends EntityInstance> eachEntity( String scopingEntityName )
    {
        throw new ZeidonException( "Scoping entity not allowed on the root" );
    }

    @Override
    public EntityIterator<? extends EntityInstance> eachEntity( EntityDef scopingEntity )
    {
        throw new ZeidonException( "Scoping entity not allowed on the root" );
    }

    @Override
    public EntityIterator<? extends EntityInstance> allEntities()
    {
        throw new ZeidonException( "Method allEntities() is not supported on root of rolling pagination" );
    }

    private class RollingIterator implements EntityIterator<EntityInstanceImpl>
    {
        /**
         * We could use getEntityInstance() to get the current EI but that involves
         * extra processing so we'll just keep it here.
         */
        private EntityInstanceImpl ei;

        private RollingIterator()
        {
            ei = getView().getObjectInstance().getRootEntityInstance();
        }

        private void findNext()
        {
            while ( ei != null && ei.isHidden() )
                ei = ei.getNextTwin();

            if ( ei == null )
                loadNextPage();
        }

        @Override
        public boolean hasNext()
        {
            return ei != null;
        }

        @Override
        public EntityInstanceImpl next()
        {
            setCursor( ei );
            EntityInstanceImpl returnEi = ei;
            ei = ei.getNextTwin();
            findNext();
            return returnEi;
        }

        @Override
        public void remove()
        {
        }

        @Override
        public EntityInstanceImpl prev()
        {
            return null;
        }

        @Override
        public boolean hasPrev()
        {
            return false;
        }

        /* (non-Javadoc)
         * @see java.lang.Iterable#iterator()
         */
        @Override
        public Iterator<EntityInstanceImpl> iterator()
        {
            return this;
        }
    }
}
