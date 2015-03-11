/**
 * This file is part of the Zeidon Java Object Engine (Zeidon JOE).
 *
 * Zeidon JOE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Zeidon JOE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009-2014 QuinSoft
 */
package com.quinsoft.zeidon.scala

import scala.collection.Iterable
import scala.language.dynamics
import scala.util.control.Breaks._
import com.quinsoft.zeidon.objectdefinition._
import com.quinsoft.zeidon.CursorPosition
import com.quinsoft.zeidon.ZeidonException
import com.quinsoft.zeidon.EntityCursor.CursorStatus

/**
 * Scala wrapper around a Zeiden EntityCursor.
 */
class EntityCursor( private[this]  val view: View,
                    private[scala] val jentityCursor: com.quinsoft.zeidon.EntityCursor )
            extends AbstractEntity( jentityCursor.getEntityDef() )
            with Iterable[EntityInstance]
{
    /**
      * Returns the underlying Java EntityCursor.
      */
    def getEntityInstance: com.quinsoft.zeidon.EntityInstance = {
        val instance = jentityCursor.getEntityInstance()
        if ( instance == null )
            throw new ZeidonException( "Cursor is null.  Did you forget to create the entity?" )
                                .prependEntityDef( jentityDef )
        instance
    }

    /** Creates a new entity instance.
      *
      * Creates a new entity instance that is positioned after the currently selected
      * entity instance.
      *
      * @returns this
      */
    def create: EntityCursor = {
        jentityCursor.createEntity()
        this
    }

    /** Creates a new entity instance.
      *
      * Creates a new entity instance.  The position of the new instance is determined
      * by CursorPosition.
      *
      * @returns this
      */
    def create( position: CursorPosition = CursorPosition.NEXT ): EntityCursor = {
        jentityCursor.createEntity()
        this
    }

    /**
      *  Returns true if there are any valid twins for this cursor.  Does NOT change the cursor.
      */
    def exists = jentityCursor.hasAny()

    /**
     * Returns true if this cursor points to a non-hidden entity instance.
     */
    def isSet = jentityCursor.checkExistenceOfEntity().isSet()

    /**
     * Removes the selected entity from the OI but does not flag it for deletion.
     *
     * Note: If this entity is persistent then it may prevent the parent entity from
     * being deleted.
     *
     * @param position specifies the new position of the cursor.
     * @return the result of the reposition.
     */
    def drop( reposition: CursorPosition = CursorPosition.NEXT ): CursorResult = jentityCursor.dropEntity( reposition )

    /**
     * Deletes the Entity Instance refered to by this cursor.
     */
    def delete( reposition: CursorPosition = CursorPosition.NEXT ): CursorResult = jentityCursor.deleteEntity( reposition )

    /**
     * Excludes the Entity Instance refered to by this cursor.
     */
    def exclude( position: CursorPosition = CursorPosition.NEXT ) = jentityCursor.excludeEntity( position )

    /**
     * Includes the entity source to this entity.  This will also include any child entities and
     * potentially spawn the include to other linked entities.  By default the newly included
     * entity will be after the current entity.
     *
     * @param source the source subobject to include
     * @param position indicates relative position of where newly included entity will be inserted.
     */
    def include( source: AbstractEntity, position: CursorPosition = CursorPosition.NEXT ) = {
        jentityCursor.includeSubobject( source.getEntityInstance, position )
    }

    /**
     * This will create a new entity and copy non-key attributes from 'source'.  This
     * will also create child entities.  If a child is not create-able but is include-able
     * then the new child entity will be included from 'source' instead of created.
     *
     * @param source the source subobject to copy.
     * @param position where to copy
     * @return
     */
    def copySubobject( source: AbstractEntity, position: CursorPosition = CursorPosition.NEXT ) = jentityCursor.copySubobject( source, position )

    /**
     * Returns the number of twins for this entity cursor.
     */
    def count = jentityCursor.getEntityCount()

    /**
     * Returns the status of this entity cursor.  The status can be used to query a cursor
     * without triggering a lazy load.
     */
    def status: CursorStatus = jentityCursor.getStatus()

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
     */
    def sort( orderKeys: String ) = jentityCursor.orderEntities( orderKeys )

    /**
     * Sorts the entities in ascending order by an attribute.
     *
     * Example: to sort by Name use
     * {{{
     *      view.MyEntity.sortBy( _.Name )
     * }}}
     */
    def sortBy( attr : (AbstractEntity) => AttributeInstance ) = {
        sortWith( (ei1, ei2) => attr(ei1) <= attr( ei2 ) )
    }

    /**
     * Sorts the entities using Scala syntax.  Example:
     * {{{
     *      view.MyEntity.sortWith( _.MyAttribute < _.MyAttribute )
     * }}}
     * Will sort MyEntity entities in ascending order of MyAttribute attribute values.
     */
    def sortWith( comparator : ( AbstractEntity, AbstractEntity ) => Boolean ) = {

        // The comparitor wrapper that we use depends on which Java version we're running.
        val jcomparitor = EntityCursor.JAVA_VERSION match {
            case v if v.startsWith("1.5") => new Java6Comparitor( comparator )
            case v if v.startsWith("1.6") => new Java6Comparitor( comparator )
            case _ => new JavaComparitor( comparator )
        }

        jentityCursor.orderEntities( jcomparitor )
    }

    /**
     * Sets the cursor to point to the first twin.
     */
    def setFirst = new CursorResult( jentityCursor.setFirst() )

    /**
     * Sets the cursor to point to the next twin.
     */
    def setNext  = new CursorResult( jentityCursor.setNext() )

    /**
     * Sets the cursor to point to the previous twin.
     */
    def setPrev = new CursorResult( jentityCursor.setPrev() )

    /**
     * Sets the cursor to point to the last twin.
     */
    def setLast  = new CursorResult( jentityCursor.setLast() )

    /**
     * Sets the cursor to the first entity instance for which the specified predicate
     * is true.
     *
     * Following example sets the cursor to point to the first entity with the
     * attribute FirstName equal to "Fred":
     * {{{
     * val result = myView.SomeEntity.setFirst( myView.SomeEntity.FirstName == "Fred" )
     * }}}
     *
     * The return value can be automatically coerced to a boolean:
     * {{{
     * if ( myView.SomeEntity.setFirst( myView.SomeEntity.FirstName == "Fred" ) ) {
     *    // Found 'Fred'.  Do something here.
     * }
     * }}}
     */
    def setFirst( predicate: => Boolean, scopingEntity: AbstractEntity = null ): CursorResult = {
        val iter = jentityCursor.eachEntity( if ( scopingEntity == null ) null else scopingEntity.entityDef )
        while ( iter.hasNext() )
        {
            iter.next()
            if ( predicate )
                return EntityCursor.CURSOR_SET
        }

        return new CursorResult( com.quinsoft.zeidon.CursorResult.UNCHANGED )
    }

    def setFirst( predicate: (EntityInstance) => Boolean ): CursorResult = {
        val iter = jentityCursor.eachEntity()
        while ( iter.hasNext() )
        {
            val ei = iter.next()
            if ( predicate( new EntityInstance( ei ) ) )
                return EntityCursor.CURSOR_SET
        }

        return EntityCursor.CURSOR_UNCHANGED
    }

    def setFirst( attributeName: String, value: Any): CursorResult = {
        if ( value.isInstanceOf[ AttributeInstance ] )
            // If the value is of type AttributeInstance then convert it to an internal value.
            jentityCursor.setFirst( attributeName, value.asInstanceOf[ AttributeInstance ].
                                                   jattributeInstance.getValue )
        else
            jentityCursor.setFirst( attributeName, value )
    }

    /**
      * Set the cursor using a hashkey attribute value.
      */
    def set( setter: ( HashSetter ) => Any ): CursorResult = {
        val hashSetter = new HashSetter()
        setter( hashSetter )
        hashSetter.getResult
    }

    /**
      * Set the cursor to reference the max attribute value for the specified attribute.
      * If more than one attribute has the same max value then the first attribute is used.
      * Returns the AttributeInstance.
      *
      * Example: sets the cursor to the oldest child:
      *
      *      view.Child.setMax( _.Age )
      */
    def setMax( f_attr: ( AbstractEntity ) => AttributeInstance ): AttributeInstance = {
        var maxAttr: AttributeInstance = null
        each {
            val attr = f_attr( this )
            maxAttr =
                if ( maxAttr == null )
                    attr
                else
                if ( attr @> maxAttr )
                    attr
                else
                    maxAttr
        }

        if ( maxAttr == null )
            throw new ZeidonException( "setMax called on empty cursor or all attributes are null" )

        jentityCursor.setCursor( maxAttr.jattributeInstance.getEntityInstance() )
        maxAttr
    }

    /**
      * Set the cursor to reference the minimum attribute value for the specified attribute.
      * If more than one attribute has the same min value then the first attribute is used.
      * Returns the AttributeInstance.
      *
      * Example: sets the cursor to the youngest child:
      *
      *      view.Child.setMin( _.Age )
      */
    def setMin( f_attr: ( AbstractEntity ) => AttributeInstance ): AttributeInstance = {
        var minAttr: AttributeInstance = null
        each {
            val attr = f_attr( this )
            minAttr =
                if ( minAttr == null )
                    attr
                else
                if ( attr @< minAttr )
                    attr
                else
                    minAttr
        }

        if ( minAttr == null )
            throw new ZeidonException( "setmin called on empty cursor or all attributes are null" )

        jentityCursor.setCursor( minAttr.jattributeInstance.getEntityInstance() )
        minAttr
    }

    /**
     * Loop through all entities of a single type under the current parent.
     */
    def each( looper: => Any ) = {
        val iter = new EntityInstanceIterator( jentityCursor.eachEntity ).setCursor( this )
        iter.each( looper )
    }

    /**
     * Loop through all entities of a single type in the OI
     */
    def all( looper: => Any ) = {
        val iter = new EntityInstanceIterator( jentityCursor.allEntities() ).setCursor( this )
        iter.each( looper )
    }

    def iterator = new EntityInstanceIterator( jentityCursor.eachEntity ).setCursor( this ).iterator

    def under( scopingEntity: String ) = {
        new EntityInstanceIterator( jentityCursor.eachEntity( scopingEntity ) ).setCursor( this )
    }

    def under( scopingEntity: AbstractEntity ) = {
        new EntityInstanceIterator( jentityCursor.eachEntity( scopingEntity.entityDef ) ).setCursor( this )
    }

    /**
     * Returns an iterator that will iterate through all the descendants of the current entity.
     */
    override def childrenHier: EntityInstanceIterator = super.childrenHier.setCursor(this)

    /**
     * Returns an iterator that will iterate through the direct children of the current entity.
     */
    override def directChildren: EntityInstanceIterator = super.directChildren.setCursor(this)

    /**
     * Set the cursor using the Attribute Hash Key.
     */
    class HashSetter extends AbstractEntity( jentityDef ) {
        var rc: CursorResult = null

        def getEntityInstance: com.quinsoft.zeidon.EntityInstance = jentityCursor.getEntityInstance()
        def getResult = rc

        override def setValue( jattributeDef: AttributeDef, value: Any ): Any = {
            if ( jattributeDef.getHashKeyType() == AttributeHashKeyType.NONE )
                throw new ZeidonException( "Cursor.set() can only be used on attributes defined with a hashkey" )
                                  .prependAttributeDef( jattributeDef )

            val attributeName = jattributeDef.getName()
            rc = {
                if ( value.isInstanceOf[ AttributeInstance ] )
                    // If the value is of type AttributeInstance then convert it to an internal value.
                    jentityCursor.setFirst( attributeName, value.asInstanceOf[ AttributeInstance ].
                                            jattributeInstance.getValue )
                else
                    jentityCursor.setFirst( attributeName, value )
            }
        }
    }

    /**
     * This class is a java.util.Comparator wrapper around the Scala scomparitor so
     * that we can use Java's sort method with Scala code.
     */
    private[this] class Java6Comparitor( scomparitor: ( AbstractEntity, AbstractEntity ) => Boolean )
                        extends java.util.Comparator[com.quinsoft.zeidon.EntityInstance] {

        /**
         * This code relies on the Java 6 MergeSort algorithm using just "compare(...) > 0" and
         * "compare(...) <= 0".  If Java 6 ever uses a different algorithm this will break.
         */
        def compare(ei1: com.quinsoft.zeidon.EntityInstance, ei2: com.quinsoft.zeidon.EntityInstance): Int = {
            val sei1 = new EntityInstance( ei1 )
            val sei2 = new EntityInstance( ei2 )
            if ( scomparitor( sei1, sei2 ) )
                0 // This indicates that sei1 and sei2 are in order.
            else
                1 // This indicates that they are not in order and should be swapped.
        }
    }

    /**
     * This class is a java.util.Comparator wrapper around the Scala scomparitor so
     * that we can use Java's sort method with Scala code.
     */
    private[this] class JavaComparitor( scomparitor: ( AbstractEntity, AbstractEntity ) => Boolean )
                        extends java.util.Comparator[com.quinsoft.zeidon.EntityInstance] {

        /**
         * This code relies on the Java 7+ TisSort algorithm using just "compare(...) < 0" and
         * "compare(...) >= 0".  If Java 7 ever uses a different algorithm this will break.
         */
        def compare(ei1: com.quinsoft.zeidon.EntityInstance, ei2: com.quinsoft.zeidon.EntityInstance): Int = {
            val sei1 = new EntityInstance( ei1 )
            val sei2 = new EntityInstance( ei2 )
            if ( scomparitor( sei1, sei2 ) )
                -1 // This indicates that sei1 and sei2 are in order.
            else
               0 // This indicates that they are not in order and should be swapped.
        }
    }
}

object EntityCursor {
    private val JAVA_VERSION = System.getProperty("java.version");

    val CURSOR_SET = CursorResult( com.quinsoft.zeidon.CursorResult.SET )
    val CURSOR_UNCHANGED = CursorResult( com.quinsoft.zeidon.CursorResult.UNCHANGED )

    /**
     * Convert a Scala EntityCursor to a Scala EntityInstance.
     */
    implicit def cursor2instance( cursor: EntityCursor ) = new EntityInstance( cursor.getEntityInstance )
    implicit def cursorResult2boolean( result: CursorResult ) = result.jcursorResult.isSet()
}