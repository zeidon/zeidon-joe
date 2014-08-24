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

import java.util.EnumSet;

import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * References the currently selected entity instance in a view.  This extends EntityInstance
 * because an EntityCursor can be used any place an EntityInstance can be used.  The only
 * difference is that EntityCursor methods can throw NullCursorException.
 *
 * @author DG
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
     * @return CusorResult.SET if the cursor points to a valid entity, otherwise
     * returns CursorResult.NULL.
     */
    CursorResult checkExistenceOfEntity();

    EntityInstance createEntity();
    EntityInstance createEntity( CursorPosition position );
    EntityInstance createEntity( CursorPosition position, EnumSet<CreateEntityFlags> flags );
    EntityInstance createEntity( EnumSet<CreateEntityFlags> flags );
    EntityInstance createEntity( CreateEntityFlags... flags );
    EntityInstance createTemporalEntity();
    EntityInstance createTemporalEntity( CursorPosition position );
    CursorResult   cancelTemporalEntity( CursorPosition position );
    CursorResult   deleteEntity( CursorPosition position );
    CursorResult   deleteAll();
    CursorResult   dropEntity( CursorPosition position );
    CursorResult   excludeEntity( CursorPosition position );
    void           includeSubobject( EntityInstance source, CursorPosition position );
    void           includeSubobject( EntityInstance source );

    /**
     * This will create a new entity and copy non-key attributes from 'source'.  This
     * will also create child entities.  If a child is non-createable but is includable
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
     * @return
     */
    EntityIterator<? extends EntityInstance> eachEntity();
    EntityIterator<? extends EntityInstance> eachEntity( String scopingEntityName );
    EntityIterator<? extends EntityInstance> eachEntity( ViewEntity scopingEntity );

    CursorResult setFirst();
    CursorResult setFirst( String scopingEntityName );
    CursorResult setFirst( ViewEntity scopingEntity );
    CursorResult setFirst( String attributeName, Object value );
    CursorResult setFirst( String attributeName, Object value, String scopingEntityName );
    CursorResult setFirst( ViewAttribute attribute, Object value );
    CursorResult setFirst( ViewAttribute attribute, Object value, ViewEntity scopingEntity );
    CursorResult setFirstWithinOi();
    CursorResult setFirstWithinOi( String attributeName, Object value );
    CursorResult setFirstWithinOi( ViewAttribute viewAttribute, Object value );

    CursorResult setNext();
    CursorResult setNextContinue();
    CursorResult setNext( String scopingEntityName );
    CursorResult setNext( ViewEntity scopingEntity );
    CursorResult setNext( String attributeName, Object value );
    CursorResult setNext( ViewAttribute attribute, Object value );
    CursorResult setNext( String attributeName, Object value, String scopingEntityName );
    CursorResult setNext( ViewAttribute attributeName, Object value, ViewEntity scopingEntityName );
    CursorResult setNextWithinOi();
    CursorResult setNextWithinOi( String attributeName, Object value );
    CursorResult setNextWithinOi( ViewAttribute attribute, Object value );

    /**
     * TODO: Is this the same as getNextTwin() != null?
     *
     * Checks to see if setNextContinue() will change the cursor.
     */
    boolean hasNext();

    /**
     * Checks to see if setPrevContinue() will change the cursor.
     */
    boolean hasPrev();

    boolean hasAny();
    boolean hasAny( String scopingEntityName );
    boolean hasAny( ViewEntity viewEntity );
    boolean hasAny( String attributeName, Object value );
    boolean hasAny( String attributeName, Object value, String scopingEntityName );
    boolean hasAny( ViewAttribute viewAttribute, Object value );
    boolean hasAny( ViewAttribute viewAttribute, Object value, ViewEntity scopingViewEntity );
    boolean hasAnyWithinOi();
    boolean hasAnyWithinOi( String attributeName, Object value );
    boolean hasAnyWithinOi( ViewAttribute viewAttribute, Object value );

    CursorResult setLast();
    CursorResult setLast( String scopingEntityName );
    CursorResult setLast( ViewEntity scopingEntity );
    CursorResult setLast( String attributeName, Object value );
    CursorResult setLast( String attributeName, Object value, String scopingEntityName );
    CursorResult setLast( ViewAttribute attribute, Object value );
    CursorResult setLast( ViewAttribute attribute, Object value, ViewEntity scopingEntity );
    CursorResult setLastWithinOi();
    CursorResult setLastWithinOi( String attributeName, Object value );
    CursorResult setLastWithinOi( ViewAttribute viewAttribute, Object value );

    CursorResult setPrev();
    CursorResult setPrevContinue();
    CursorResult setPrev( String scopingEntityName );
    CursorResult setPrev( ViewEntity scopingEntity );
    CursorResult setPrev( String attributeName, Object value );
    CursorResult setPrev( ViewAttribute attribute, Object value );
    CursorResult setPrev( String attributeName, Object value, String scopingEntityName );
    CursorResult setPrev( ViewAttribute attributeName, Object value, ViewEntity scopingEntityName );
    CursorResult setPrevWithinOi();
    CursorResult setPrevWithinOi( ViewAttribute attribute, Object value );

    CursorResult setByEntityKey( long entityKey );

    /**
     * Sets cursor by relative position (i.e. index) starting with 0.
     *
     * @param position
     * @return
     */
    CursorResult setPosition( int position );
    CursorResult setPosition( int position, String scopingEntity );
    boolean setToSubobject();
    boolean resetSubobjectToParent();

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

    CursorResult moveSubobject( CursorPosition position, EntityCursor source, CursorPosition sourceReposition );
    CursorResult moveSubobject( CursorPosition position, EntityInstance source );
}