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

import java.util.Set;

/**
 * An interface for keeping track of selected entities.  This is largely just
 * a Java Set<EntityInstance>.  The main difference is that when an EI is
 * added to the set with select(...) there is an option to also add all of
 * its children.
 */
public interface SelectSet extends Set<EntityInstance>
{
    /**
     * Returns true if the entity is selected in this set.
     *
     * @param ei
     * @return
     */
    boolean isSelected( EntityInstance ei );

    /**
     * Adds the entity to the select set.
     *
     * @param ei
     */
    void select( EntityInstance ei );

    /**
     * Adds the entity to the select set and potentially all its children.
     *
     * @param ei
     * @param selectChildren if true then also select the children.
     */
    void select( EntityInstance ei, boolean selectChildren );

    /**
     * Removes the entity from the select set.
     *
     * @param ei
     */
    void deselect( EntityInstance ei );

    /**
     * Removes the entity from the select set.
     *
     * @param ei
     * @param removeChildren if true then remove all of the children of ei.
     */
    void deselect( EntityInstance ei, boolean removeChildren );

    /**
     * Iterates over each of the entities in the set in hierarchical order.
     */
    EntityIterator<? extends EntityInstance> eachEntity();
}
