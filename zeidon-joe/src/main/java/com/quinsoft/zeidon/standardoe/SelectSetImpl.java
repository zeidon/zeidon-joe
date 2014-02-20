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

    Copyright 2009-2012 QuinSoft
 */

package com.quinsoft.zeidon.standardoe;

import java.util.HashSet;
import java.util.Set;

import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.SelectSet;

/**
 * @author DG
 *
 */
public class SelectSetImpl implements SelectSet
{
    private final Set<EntityInstance> selectSet = new HashSet<EntityInstance>();
    
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.SelectSet#isSelected(com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public boolean isSelected(EntityInstance ei)
    {
        return selectSet.contains( ei );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.SelectSet#isSelected(com.quinsoft.zeidon.EntityCursor)
     */
    @Override
    public boolean isSelected(EntityCursor cursor)
    {
        return isSelected( cursor.getEntityInstance() );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.SelectSet#select(com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public void select(EntityInstance ei)
    {
        selectSet.add( ei );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.SelectSet#select(com.quinsoft.zeidon.EntityCursor)
     */
    @Override
    public void select(EntityCursor cursor)
    {
        select( cursor.getEntityInstance() );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.SelectSet#deselect(com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public void deselect(EntityInstance ei)
    {
        selectSet.remove( ei );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.SelectSet#deselect(com.quinsoft.zeidon.EntityCursor)
     */
    @Override
    public void deselect(EntityCursor cursor)
    {
        deselect( cursor.getEntityInstance() );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.SelectSet#clear()
     */
    @Override
    public void clear()
    {
        selectSet.clear();
    }
}
