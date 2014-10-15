/**
 *
 */
package com.quinsoft.zeidon.scala

import scala.language.dynamics
import scala.collection.Iterable
import com.quinsoft.zeidon._
import com.quinsoft.zeidon.objectdefinition._
import util.control.Breaks._

/**
 * Scala wrapper around a Ze
 * @author dgc
 *
 */
class EntityCursor( private[this]  val view: View,
                    private[scala] val jentityCursor: com.quinsoft.zeidon.EntityCursor )
            extends AbstractEntity( jentityCursor.getEntityDef() )
            with Iterable[EntityInstance]
{
    def getEntityInstance: com.quinsoft.zeidon.EntityInstance = {
        val instance = jentityCursor.getEntityInstance()
        if ( instance == null )
            throw new ZeidonException( "Cursor for '%s' is null.  Did you forget to create the entity?", jentityDef.getName() )
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

    def setFirst = jentityCursor.setFirst().isSet()
    def setNext  = jentityCursor.setNext().isSet()
    def setLast  = jentityCursor.setLast().isSet()

    def setFirst( predicate:  => Boolean, scopingEntity: String = null ): Boolean = {
        val iter = jentityCursor.eachEntity( scopingEntity )
        while ( iter.hasNext() )
        {
            iter.next()
            if ( predicate )
                return true
        }

        return false
    }

    def setFirst( predicate: (EntityInstance) => Boolean ): Boolean = {
        val iter = jentityCursor.eachEntity()
        while ( iter.hasNext() )
        {
            val ei = iter.next()
            if ( predicate( new EntityInstance( ei ) ) )
                return true
        }

        return false
    }

    def setFirst( attributeName: String, value: Any): Boolean = {
        if ( value.isInstanceOf[ AttributeInstance ] )
            // If the value is of type AttributeInstance then convert it to an internal value.
            jentityCursor.setFirst( attributeName, value.asInstanceOf[ AttributeInstance ].
                                                   jattributeInstance.getValue ) == CursorResult.SET
        else
            jentityCursor.setFirst( attributeName, value ) == CursorResult.SET
    }

    /**
     * Set the cursor using a hashkey attribute value.
     */
    def set( setter: (HashSetter) => Any ): Boolean = {
        val hashSetter = new HashSetter()
        setter( hashSetter )
        hashSetter.rc
    }

    def each( looper: => Any ) = {
        val iter = jentityCursor.eachEntity()
        while ( iter.hasNext() )
        {
            val ei = iter.next()
            breakable {
                looper
            }
        }
    }

    def iterator = {
        val iter = jentityCursor.eachEntity
        new Iterator[EntityInstance] {
            def hasNext = iter.hasNext()
            def next = new EntityInstance( iter.next() )
        }
    }

    def iterator( scopingEntity: String ) = {
        val iter = jentityCursor.eachEntity( scopingEntity )
        new Iterator[EntityInstance] {
            def hasNext = iter.hasNext()
            def next = new EntityInstance( iter.next() )
        }
    }

    override def childrenHier = super.childrenHier.setCursor(this)

    /**
     * Returns an iterator that will iterate through the direct children of the current entity.
     */
    override def directChildren = super.directChildren.setCursor(this)

    class HashSetter extends AbstractEntity( jentityDef ) {
        var rc: Boolean = false

        def getEntityInstance: com.quinsoft.zeidon.EntityInstance = jentityCursor.getEntityInstance()

        override def setValue( jattributeDef: AttributeDef, value: Any ): Any = {
            if ( jattributeDef.getHashKeyType() == AttributeHashKeyType.NONE )
                throw new ZeidonException( "Cursor.set() can only be used on attributes defined with a hashkey" )
                                  .prependAttributeDef( jattributeDef )

            val attributeName = jattributeDef.getName()
            rc = {
                if ( value.isInstanceOf[ AttributeInstance ] )
                    // If the value is of type AttributeInstance then convert it to an internal value.
                    jentityCursor.setFirst( attributeName, value.asInstanceOf[ AttributeInstance ].
                                            jattributeInstance.getValue ) == CursorResult.SET
                else
                    jentityCursor.setFirst( attributeName, value ) == CursorResult.SET
            }
        }
    }
}

object EntityCursor {
    /**
     * Convert a Scala EntityCursor to a Scala EntityInstance.
     */
    implicit def cursor2instance( cursor: EntityCursor ) = new EntityInstance( cursor.getEntityInstance )
}