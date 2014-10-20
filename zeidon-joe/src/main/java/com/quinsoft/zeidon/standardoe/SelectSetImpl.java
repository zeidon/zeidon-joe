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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.EntityIterator;
import com.quinsoft.zeidon.SelectSet;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * @author DG
 *
 */
class SelectSetImpl implements SelectSet
{
    private final Set<EntityInstance> selectSet = new HashSet<EntityInstance>();
    private final ViewImpl view;
    private       EntityInstance commonParent;
    private       EntityDef entityDef;
    private       EntityInstance referenceInstance;

    /**
     * Currently we can only iterate through the selected entities if they all
     * have the same EntityDef and parent.  If we want to widen support we'll have to
     * add some logic to the entity iterator.
     */
    private       boolean multipleEntityDefs = false;

    SelectSetImpl( ViewImpl view )
    {
        this.view = view;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.SelectSet#isSelected(com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public boolean isSelected(EntityInstance ei)
    {
        return selectSet.contains( ei.getEntityInstance() );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.SelectSet#select(com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public void select(EntityInstance ei)
    {
        select( ei, false );
    }

    @Override
    public void select(EntityInstance ei, boolean includeChildren )
    {
        ei = ei.getEntityInstance(); // Get EI from the cursor if a cursor was used.

        if ( entityDef == null )
        {
            entityDef = ei.getEntityDef();
            commonParent = ei.getParent();
            referenceInstance = ei;
        }
        else
        {
            if ( ! entityDef.equals( ei.getEntityDef() ) )
                multipleEntityDefs = true;
            else
            // Find the greatest common parent.  If the commonParent is currently null then
            // we must be dealing with root entities.
            if ( commonParent != null )
            {
                // Currently we only support entities under the same parent.
                if ( ei.getParent() != commonParent )
                    throw new ZeidonException( "SelectSet only supports entities under the same parent" );
            }
        }

        selectSet.add( ei.getEntityInstance() );
        if ( includeChildren )
        {
            multipleEntityDefs = true;
            for ( EntityInstance child : ei.getChildrenHier( false ) )
                selectSet.add( child );
        }
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.SelectSet#deselect(com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public void deselect(EntityInstance ei)
    {
        selectSet.remove( ei.getEntityInstance() );
        commonParent = null;
        entityDef = null;
        referenceInstance = null;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.SelectSet#clear()
     */
    @Override
    public void clear()
    {
        selectSet.clear();
    }

    @Override
    public EntityIterator<? extends EntityInstance> eachEntity()
    {
        if ( multipleEntityDefs )
            throw new ZeidonException( "SelectSets may (currently) only iterate through entities with the same parent. " )
                                        .prependEntityDef( entityDef );

        return new IteratorBuilder(view.getObjectInstance())
                        .setCursor( view.cursor( entityDef ) )
                        .forTwinsOf( (EntityInstanceImpl) referenceInstance )
                        .forEntityDef( entityDef )
                        .containedInSet( selectSet )
                        .build();
    }

    @Override
    public Set<EntityInstance> getSet()
    {
        return Collections.unmodifiableSet( selectSet );
    }

    @Override
    public String toString()
    {
        return selectSet.toString();
    }
}
