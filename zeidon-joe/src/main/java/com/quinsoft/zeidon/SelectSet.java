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

package com.quinsoft.zeidon;

/**
 * An interface for keeping track of selected entities.
 * 
 * @author DG 
 *
 */
public interface SelectSet
{
    /**
     * Returns true if the entity is selected in this set.
     * 
     * @param ei
     * @return
     */
    boolean isSelected( EntityInstance ei );

    /**
     * Returns true if the entity for this cursor is selected.
     * 
     * @param cursor
     * @return
     */
    boolean isSelected( EntityCursor cursor );

    /**
     * Adds the entity to the select set.
     * 
     * @param ei
     */
    void select( EntityInstance ei );
    
    /**
     * Adds the current entity for this cursor to the select set.
     * 
     * @param cursor
     */
    void select( EntityCursor cursor );

    /**
     * Removes the entity from the select set.
     * 
     * @param ei
     */
    void deselect( EntityInstance ei );
    
    /**
     * Removes the entity from the select set.
     * 
     * @param cursor
     */
    void deselect( EntityCursor cursor );
    
    /**
     * Remove all the entities from the select set.
     */
    void clear();
}
