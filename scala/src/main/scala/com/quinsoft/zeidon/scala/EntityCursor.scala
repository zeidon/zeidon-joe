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

/**
 * Scala wrapper around a Zeiden EntityCursor.
 */
class EntityCursor( private[this]  val view: View,
                    private[scala] val jentityCursor: com.quinsoft.zeidon.EntityCursor )
            extends AbstractEntity( jentityCursor.getEntityDef() )
            with Iterable[EntityInstance]
{
    def getEntityInstance: com.quinsoft.zeidon.EntityInstance = {
        val instance = jentityCursor.getEntityInstance()
        if ( instance == null )
            throw new ZeidonException( "Cursor is null.  Did you forget to create the entity?" )
                                .prependEntityDef( jentityDef )
        instance
    }

    def create: EntityCursor = {
        jentityCursor.createEntity()
        this
    }

    def create( position: CursorPosition = CursorPosition.NEXT ): EntityCursor = {
        jentityCursor.createEntity()
        this
    }

    def exists = jentityCursor.checkExistenceOfEntity().isSet()
    def drop: CursorResult = jentityCursor.dropEntity()
    def drop( reposition: CursorPosition ): CursorResult = jentityCursor.dropEntity( reposition )
    def delete(): CursorResult = jentityCursor.deleteEntity()
    def delete( reposition: CursorPosition ): CursorResult = jentityCursor.deleteEntity( reposition )
    def exclude( position: CursorPosition = CursorPosition.NEXT ) = jentityCursor.excludeEntity( position )
    def include( source: AbstractEntity, position: CursorPosition = CursorPosition.NEXT ) = {
        jentityCursor.includeSubobject( source.getEntityInstance, position )
    }
    def copySubobject( source: AbstractEntity, position: CursorPosition = CursorPosition.NEXT ) = jentityCursor.copySubobject( source, position )
    def count = jentityCursor.getEntityCount()

    def setFirst = new CursorResult( jentityCursor.setFirst() )
    def setNext  = new CursorResult( jentityCursor.setNext() )
    def setLast  = new CursorResult( jentityCursor.setLast() )

    def setFirst( predicate:  => Boolean, scopingEntity: AbstractEntity = null ): CursorResult = {
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
    def set( setter: (HashSetter) => Any ): CursorResult = {
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
    def setMax( f_attr: (AbstractEntity ) => AttributeInstance ): AttributeInstance = {
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
    def setMin( f_attr: (AbstractEntity ) => AttributeInstance ): AttributeInstance = {
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

    def each( looper: => Any ) = {
        val iter = new EntityInstanceIterator( jentityCursor.eachEntity ).setCursor( this )
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
}

object EntityCursor {
    val CURSOR_SET = CursorResult( com.quinsoft.zeidon.CursorResult.SET )
    val CURSOR_UNCHANGED = CursorResult( com.quinsoft.zeidon.CursorResult.UNCHANGED )

    /**
     * Convert a Scala EntityCursor to a Scala EntityInstance.
     */
    implicit def cursor2instance( cursor: EntityCursor ) = new EntityInstance( cursor.getEntityInstance )
    implicit def cursorResult2boolean( result: CursorResult ) = result.jcursorResult.isSet()
}