/**
 *
 */
package com.quinsoft.zeidon.scala

import scala.language.dynamics
import scala.collection.Iterable
import com.quinsoft.zeidon._
import com.quinsoft.zeidon.objectdefinition._

/**
 * @author dgc
 *
 */
class EntityCursor( private[this] val view: View, 
                     val jentityCursor: com.quinsoft.zeidon.EntityCursor ) 
            extends AbstractEntity( jentityCursor.getViewEntity() ) 
            with Iterable[EntityInstance]
{
    def getEntityInstance: com.quinsoft.zeidon.EntityInstance = jentityCursor.getEntityInstance() 
        
    def create(): EntityCursor = {
        jentityCursor.createEntity()
        this
    }
    
    def create( position: CursorPosition = CursorPosition.NEXT ): EntityCursor = {
        jentityCursor.createEntity()
        this
    }

    def delete( reposition: CursorPosition ): CursorResult = jentityCursor.deleteEntity( reposition )

    def include( source: AbstractEntity, position: CursorPosition = CursorPosition.NEXT ) = { 
        jentityCursor.includeSubobject( source.getEntityInstance, position )
    }
    
    def where( predicate: (EntityInstance) => Boolean ) = iterator.filter( predicate )
    
    def setFirst: CursorResult = jentityCursor.setFirst()

    def setFirst( predicate: (View) => Boolean, scopingEntity: String = null ): Boolean = {
        val iter = jentityCursor.eachEntity( scopingEntity )
        while ( iter.hasNext() )
        {
            iter.next()
            if ( predicate == null || predicate(view) )
                return true
        }

        return false
    }

    def iterator = {
        val iter = jentityCursor.eachEntity
        new Iterator[EntityInstance] {
            def hasNext = iter.hasNext()
            def next = new EntityInstance( iter.next() )
        }
    }
}