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
package com.quinsoft.zeidon;

import java.util.Comparator;
import java.util.EnumSet;

import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * References the currently selected entity instance in a view.  This extends EntityInstance
 * because an EntityCursor can be used any place an EntityInstance can be used.  The only
 * difference is that EntityCursor methods can throw NullCursorException.
 *
 */
public interface EntityCursor extends EntityInstance
{
    public enum CursorStatus
    {
        OUT_OF_SCOPE, NULL, SET, NOT_LOADED, HIDDEN
    }

    /**
     * Returns the currently selected entity instance or null.  Does NOT throw
     * NullCursorException.
     *
     * @return  Selected entity instance or null.
     */
    @Override
    EntityInstance getEntityInstance();

    CursorStatus getStatus();

    /**
     * @return true if the cursor does not point to a valid entity instance.
     */
    boolean isNull();

    /**
     * Checks to see if the cursor points to a valid entity.  Unlike isNull()
     * if the current entity instance is hidden then this will attempt to find
     * a twin (starting with next twins) that is not hidden.  This will change
     * the cursor setting.
     *
     * Lazy loaded entities will be loaded if necessary.
     *
     * @return CusorResult.SET if the cursor points to a valid entity<br>
     *         CursorResult.UNDEFINED if the cursor is out of scope,<br>
     *         otherwise returns CursorResult.NULL.
     */
    CursorResult checkExistenceOfEntity();

    /**
     * Create a new entity instance as the last child under its parent.
     *
     * @return the new EntityInstance
     */
    EntityInstance createEntity();

    /**
     * Create a new entity instance.  The location of the new instance is
     * determined by CursorPosition.
     *
     * @param position specifies the location of the new instance relative to
     * the currently selected instance.
     *
     * @return the new instance.
     */
    EntityInstance createEntity( CursorPosition position );

    /**
     * Create a new entity instance.  The location of the new instance is
     * determined by CursorPosition.
     *
     * @param position specifies the location of the new instance relative to
     * the currently selected instance.
     * @param flags set of flags to control how the new instance is crated.
     *
     * @return the new instance.
     */
    EntityInstance createEntity( CursorPosition position, EnumSet<CreateEntityFlags> flags );

    /**
     * Create a new entity instance as the last child under its parent.
     *
     * @param flags set of flags to control how the new instance is crated.
     *
     * @return the new instance.
     */
    EntityInstance createEntity( EnumSet<CreateEntityFlags> flags );

    /**
     * Create a new entity instance as the last child under its parent.
     *
     * @param flags set of flags to control how the new instance is crated.
     *
     * @return the new instance.
     */
    EntityInstance createEntity( CreateEntityFlags... flags );

    /**
     * Creates a new temporal entity that cannot be committed until it is accepted.
     * If it is cancelled it is removed from the object instance.  The new entity
     * is positioned as the last entity under its parent.
     *
     * @return the new temporal entity.
     */
    EntityInstance createTemporalEntity();

    /**
     * Creates a new temporal entity that cannot be committed until it is accepted.
     * If it is cancelled it is removed from the object instance.  The position of
     * the new entity is determined by the CursorPosition relative to the currently
     * selected entity instance.
     *
     * @return the new temporal entity.
     */
    EntityInstance createTemporalEntity( CursorPosition position );


    /**
     * Cancels the selected temporal entity and repositions the cursor.  If position =
     * CursorPosition.NONE then the cursor is NOT repositioned and is considered to
     * point to be NULL.
     *
     * @param position specifies the new position of the cursor.
     * @return the result of the reposition.
     */
    CursorResult   cancelTemporalEntity( CursorPosition position );

   /**
    * Deletes the selected entity and repositions the cursor.  If position =
    * CursorPosition.NONE then the cursor is NOT repositioned and is considered to
    * be NULL.
    *
    * A deleted entity and all its children will be removed from the DB if the
    * OI is committed unless parent-delete behavior is set to Restrict.
    *
    * @param position specifies the new position of the cursor.
    * @return the result of the reposition.
    */
    CursorResult   deleteEntity( CursorPosition position );

    /**
     * Convenience method for deleteEntity( CursorPosition.NEXT );
     */
    @Override
    CursorResult  deleteEntity();

    /**
     * Deletes the currently selected entity and all its twins.
     *
     * @return CursorResult.SET if any entities were deleted, otherwise CursorResult.UNCHANGED
     */
    CursorResult   deleteAll();

    /**
     * Removes the selected entity from the OI but does not flag it for deletion.
     *
     * Note: If this entity is persistent then it may prevent the parent entity from
     * being deleted.
     *
     * @param position specifies the new position of the cursor.
     * @return the result of the reposition.
     */
    CursorResult   dropEntity( CursorPosition position );

    /**
     * Excludes the selected entity instance from its parent.  If committed then the relationship
     * between this entity and its parent is removed but this entity is not deleted.
     *
     * @param position specifies the new position of the cursor.
     * @return the result of the reposition.
     */
    CursorResult   excludeEntity( CursorPosition position );


    void includeSubobject( EntityInstance source, CursorPosition position );
    void includeSubobject( EntityInstance source );

    /**
     * This will create a new entity and copy non-key attributes from 'source'.  This
     * will also create child entities.  If a child is not create-able but is include-able
     * then the new child entity will be included from 'source' instead of created.
     *
     * @param source
     * @param position
     * @return
     */
    EntityInstance copySubobject( EntityInstance source, CursorPosition position );

    /**
     * Loops through all twin entities of this cursor, starting with the first.
     * Sets the cursor.
     *
     * @return EntityInstance iterator
     */
    EntityIterator<? extends EntityInstance> eachEntity();

    /**
     * Loops through all twin entities of this cursor with scopingEntityName specifying
     * the scoping, starting with the first.  Sets the cursor.
     *
     * @param scopingEntityName the name of the scoping entity
     * @return EntityInstance iterator
     */
    EntityIterator<? extends EntityInstance> eachEntity( String scopingEntityName );

    /**
     * Loops through all twin entities of this cursor with scopingEntity specifying
     * the scoping, starting with the first.  Sets the cursor.
     *
     * @param scopingEntity the scoping entity
     * @return EntityInstance iterator
     */
    EntityIterator<? extends EntityInstance> eachEntity( EntityDef scopingEntity );

    /**
     * Loops through all the entities in the OI.
     *
     * @return EntityInstance iterator
     */
    EntityIterator<? extends EntityInstance> allEntities();

    /**
     * Sets the cursor to the first twin.
     *
     * @return CursorResult.SET if the cursor is set<br>
     *         CursorResult.NULL if there are no twins.
     */
    CursorResult setFirst();

    /**
     * Sets the cursor to the first twin under the scoping entity.
     *
     * @param scopingEntityName name of scopingEntity
     *
     * @return CursorResult.SET if the cursor is set<br>
     *         CursorResult.NEW_PARENT if the cursor is set and the new twin has a different parent<br>
     *         CursorResult.NULL if there are no twins.
     */
    CursorResult setFirst( String scopingEntityName );

    /**
     * Sets the cursor to the first twin under the scoping entity.
     *
     * @param scopingEntity the scoping entity
     *
     * @return CursorResult.SET if the cursor is set<br>
     *         CursorResult.NEW_PARENT if the cursor is set and the new twin has a different parent<br>
     *         CursorResult.NULL if there are no twins.
     */
    CursorResult setFirst( EntityDef scopingEntity );

    /**
     * Sets the cursor to the first entity with an attribute value equal
     * to 'value'.
     *
     * @param attributeName name of the attribute to compare
     * @param value value to compare to the attribute.
     *
     * @return CursorResult.SET if the cursor is set<br>
     *         CursorResult.UNCHANGED if no matching value is found.
     */
    CursorResult setFirst( String attributeName, Object value );

    /**
     * Sets the cursor to the first entity with an attribute value equal
     * to 'value' using a scoping entity.
     *
     * @param attributeName name of the attribute to compare
     * @param value value to compare to the attribute.
     * @param scopingEntityName name of scopingEntity
     *
     * @return CursorResult.SET if the cursor is set<br>
     *         CursorResult.NEW_PARENT if the cursor is set and the new twin has a different parent<br>
     *         CursorResult.UNCHANGED if no matching value is found.
     */
    CursorResult setFirst( String attributeName, Object value, String scopingEntityName );

    /**
     * Sets the cursor to the first entity with an attribute value equal
     * to 'value'.
     *
     * @param attribute the attribute to compare
     * @param value value to compare to the attribute.
     * @param scopingEntity the scoping entity.
     *
     * @return CursorResult.SET if the cursor is set<br>
     *         CursorResult.NEW_PARENT if the cursor is set and the new twin has a different parent<br>
     *         CursorResult.UNCHANGED if no matching value is found.
     */
    CursorResult setFirst( AttributeDef attribute, Object value );

    /**
     * Sets the cursor to the first entity with an attribute value equal
     * to 'value' using a scoping entity.
     *
     * @param attribute attribute to compare
     * @param value value to compare to the attribute.
     * @param scopingEntity the scoping entity.
     *
     * @return CursorResult.SET if the cursor is set<br>
     *         CursorResult.NEW_PARENT if the cursor is set and the new twin has a different parent<br>
     *         CursorResult.UNCHANGED if no matching value is found.
     */
    CursorResult setFirst( AttributeDef attribute, Object value, EntityDef scopingEntity );

    /**
     * Set the cursor to point to the first entity in the entire object instances.  This
     * method is used because scoping entity doesn't work.
     *
     * @return CursorResult.SET if the cursor is set<br>
     *         CursorResult.NULL if there are no twins.
     */
    CursorResult setFirstWithinOi();
    CursorResult setFirstWithinOi( String attributeName, Object value );
    CursorResult setFirstWithinOi( AttributeDef attributeDef, Object value );

    CursorResult setNext();

    /**
     * Sets the cursor to the next entity using the search configuration of the most
     * recent setFirst/setNext call.  This allows for faster iteration through entities.
     *
     * @return
     */
    CursorResult setNextContinue();
    CursorResult setNext( String scopingEntityName );
    CursorResult setNext( EntityDef scopingEntity );
    CursorResult setNext( String attributeName, Object value );
    CursorResult setNext( AttributeDef attribute, Object value );
    CursorResult setNext( String attributeName, Object value, String scopingEntityName );
    CursorResult setNext( AttributeDef attributeName, Object value, EntityDef scopingEntityName );
    CursorResult setNextWithinOi();
    CursorResult setNextWithinOi( String attributeName, Object value );
    CursorResult setNextWithinOi( AttributeDef attribute, Object value );

    /**
     * Checks to see if setNextContinue() will change the cursor.
     */
    boolean hasNext();

    /**
     * Checks to see if setPrevContinue() will change the cursor.
     */
    boolean hasPrev();

    /**
     * Returns true if there are any non-hidden twins for this cursor.
     *
     * @return true if there are any non-hidden twins for this cursor.
     */
    boolean hasAny();

    boolean hasAny( String scopingEntityName );
    boolean hasAny( EntityDef entityDef );
    boolean hasAny( String attributeName, Object value );
    boolean hasAny( String attributeName, Object value, String scopingEntityName );
    boolean hasAny( AttributeDef attributeDef, Object value );
    boolean hasAny( AttributeDef attributeDef, Object value, EntityDef scopingEntityDef );
    boolean hasAnyWithinOi();
    boolean hasAnyWithinOi( String attributeName, Object value );
    boolean hasAnyWithinOi( AttributeDef attributeDef, Object value );

    CursorResult setLast();
    CursorResult setLast( String scopingEntityName );
    CursorResult setLast( EntityDef scopingEntity );
    CursorResult setLast( String attributeName, Object value );
    CursorResult setLast( String attributeName, Object value, String scopingEntityName );
    CursorResult setLast( AttributeDef attribute, Object value );
    CursorResult setLast( AttributeDef attribute, Object value, EntityDef scopingEntity );
    CursorResult setLastWithinOi();
    CursorResult setLastWithinOi( String attributeName, Object value );
    CursorResult setLastWithinOi( AttributeDef attributeDef, Object value );

    CursorResult setPrev();
    CursorResult setPrevContinue();
    CursorResult setPrev( String scopingEntityName );
    CursorResult setPrev( EntityDef scopingEntity );
    CursorResult setPrev( String attributeName, Object value );
    CursorResult setPrev( AttributeDef attribute, Object value );
    CursorResult setPrev( String attributeName, Object value, String scopingEntityName );
    CursorResult setPrev( AttributeDef attributeName, Object value, EntityDef scopingEntityName );
    CursorResult setPrevWithinOi();
    CursorResult setPrevWithinOi( AttributeDef attribute, Object value );

    CursorResult setByEntityKey( long entityKey );

    /**
     * Sets cursor by relative position (i.e. index) starting with 0.
     *
     * @param position index to set.
     * @return CursorResult.SET
     */
    CursorResult setPosition( int position );

    /**
     * Sets cursor by relative position (i.e. index) starting with 0.  Uses a scoping
     * entity so the selected entity instance could be under a different parent.
     *
     * @param position index to set.
     * @return CursorResult.SET
     */
    CursorResult setPosition( int position, String scopingEntity );

    /**
     * Sets the cursor to a recursive subobject child entity.
     */
    void setToSubobject();

    /**
     * Reset to parent subobject.
     *
     * @return true if set to parent, false if parent was already root of recursive subobject.
     */
    boolean resetSubobjectToParent();

    /**
     * Sets the cursor to the specified entity instance.
     * @param entityInstance entity instance to set.
     *
     * @return CursorResult.SET
     */
    CursorResult setCursor( EntityInstance entityInstance );

    /**
     * Sorts the entities according to the value of orderKeys.
     *      orderKeys = String of paired 'words', consisting of "AttributeName x",
     *      where x is A for ascending, or D for descending. i.e.,
     *          "LastName A FirstName A".
     *
     *      A context may be specified for the sorting attribute by putting the
     *      context name in square brackets ("[" and "]" after the sort order:
     *          "LastName A State A [Abbrev]"
     *
     * @param orderKeys
     */
    void orderEntities( String orderKeys );

    /**
     * Sorts the entities using the comparator.
     */
    void orderEntities( Comparator<? extends EntityInstance> comparator );

    /**
     * Get the view associated with this entity cursor.
     *
     * @return
     */
    View getView();

    /**
     * Returns the number of twin entities for the current cursor.
     *
     * @return
     * @throws NullCursorException
     */
    int getEntityCount() throws NullCursorException;

    /**
     * Move an entity instance to a new position under its parent and potentially change
     * the current cursor.
     *
     * @param position relative position to source
     * @param source current instance is moved adjacent to this as specified by position
     * @param sourceReposition reposition cursor depending on this.
     *
     * @return CursorResult.SET
     */
    CursorResult moveSubobject( CursorPosition position, EntityCursor source, CursorPosition sourceReposition );

    /**
     * Move an entity instance to a new position under its parent
     *
     * @param position relative position to source
     * @param source current instance is moved adjacent to this as specified by position
     *
     * @return CursorResult.SET
     */
    CursorResult moveSubobject( CursorPosition position, EntityInstance source );
}