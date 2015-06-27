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

import java.util.HashSet;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.EntityIterator;
import com.quinsoft.zeidon.SelectSet;
import com.quinsoft.zeidon.View;

/**
 * @author DG
 *
 */
public class SelectSetImpl extends HashSet<EntityInstance> implements SelectSet
{
    private static final long serialVersionUID = 1L;
    private final ViewImpl view;

    protected SelectSetImpl( View view )
    {
        this.view = ((InternalView) view).getViewImpl();
    }

    protected SelectSetImpl( View view, SelectSetImpl sourceSelectSet )
    {
        this( view );
        addAll( sourceSelectSet );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.SelectSet#isSelected(com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public boolean isSelected(EntityInstance ei)
    {
        return contains( ei.getEntityInstance() );
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
    public void select(EntityInstance entityInstance, boolean includeChildren )
    {
        EntityInstanceImpl ei = (EntityInstanceImpl) entityInstance.getEntityInstance();
        add( ei );
        if ( includeChildren )
        {
            // Add all the children.  Force a lazy load so that they get selected.
            for ( EntityInstanceImpl child : ei.getChildrenHier( false, false, true ) )
                add( child );
        }
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.SelectSet#deselect(com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public void deselect(EntityInstance ei)
    {
        deselect( ei, false );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.SelectSet#deselect(com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public void deselect( EntityInstance entityInstance, boolean removeChildren )
    {
        EntityInstanceImpl ei = (EntityInstanceImpl) entityInstance.getEntityInstance();
        remove( ei );
        if ( removeChildren )
        {
            // Loop through all the children.  We don't need to force lazy load because
            // if the EI is in the select set it must have already been loaded.
            for ( EntityInstanceImpl child : ei.getChildrenHier( false, false, false ) )
            {
                if ( contains( child ) )
                    remove( child );
            }
        }
    }

    @Override
    public EntityIterator<? extends EntityInstance> eachEntity()
    {
        return new IteratorBuilder(view.getObjectInstance())
                        .withOiScoping( view.getObjectInstance() )
                        .containedInSet( this )
                        .setView( view )
                        .build();
    }

    @Override
    public View getView()
    {
        return view;
    }
}
