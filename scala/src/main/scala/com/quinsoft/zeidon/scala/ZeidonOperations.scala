/**
 *
 */
package com.quinsoft.zeidon.scala

import scala.language.dynamics
import com.quinsoft.zeidon._
import util.control.Breaks._
import com.quinsoft.zeidon.scala.Nexts._

/**
 * A trait that is added to a Scala object to give the object VML-like syntax.
 * Requires a task.
 *
 * @author dgc
 *
 */
trait ZeidonOperations {

    val NEXT = CursorPosition.NEXT
    val PREV = CursorPosition.PREV
    val FIRST = CursorPosition.FIRST
    val LAST = CursorPosition.LAST
    val zCURSOR_SET = com.quinsoft.zeidon.CursorResult.SET.toInt()

    var task: Task

    val ON = View.ON // Used to build "BASED ON LOD" syntax.


    def VIEW: View = new View( task )

    def GETVIEW( viewName: String ) = task.jtask.getViewByName( viewName )

    def FOREACH( cursor: EntityCursor ) = {
        val iterator = new EntityIterator( cursor )
        iterator
    }

    def SETFIRST( cursor: EntityCursor ): VmlCursorResult = {
        new VmlCursorResult( cursor.jentityCursor.setFirst(), cursor )
    }

    def SETNEXT( cursor: EntityCursor ): VmlCursorResult = {
        new VmlCursorResult( cursor.jentityCursor.setNext(), cursor )
    }

    /**
     * Includes an entity.  Use like:
     * {{{
     * INCLUDE( targetView.EntityName ) FROM sourceView.EntityName
     * }}}
     * To specify a cursor position, use:
     * {{{
     * INCLUDE( targetView.EntityName ) FROM( sourceView.EntityName, CursorPosition.LAST )
     * }}}
     */
    def INCLUDE( cursor: EntityCursor ) = new EntityIncluder( cursor )
    
    /**
     * For use inside of view.Entity.each{} and FOREACH loops, this will break execution of the
     * current entity cursor and continue with the next.
     */
    def next() = Nexts.next()

    /**
     * For use inside of view.Entity.each{} and FOREACH loops, this will break execution of the
     * current entity and stop looping.
     */
    def break() = util.control.Breaks.break()

    def log = task.jtask.log()

    class EntityIterator( val cursor: EntityCursor ) {

        var scopingEntity: String = null
        var predicate: () => Boolean = { () => true }

        /**
         * Set the scoping entity.
         */
        def UNDER( ei: AbstractEntity ) = {
            scopingEntity = ei.jentityDef.getName()
            this
        }

        def UNDER( scoping: String ) = {
            scopingEntity = scoping
            this
        }

        def WHERE( func: => Boolean ) = {
            predicate = () => { func }
            this
        }

        def DO( func: => Unit ) = {
            if ( scopingEntity != null )
                breakable {
                    cursor.under( scopingEntity ).foreach( ei => { if ( predicate() ) nextable { func } } )
                }
            else
                breakable {
                    cursor.iterator.foreach( ei => { if ( predicate() ) nextable { func } } )
                }
        }
    }
    
    /**
     * Short-lived class to allow Scala code to mirror VML when including an entity.
     */
    class EntityIncluder( val targetCursor: EntityCursor ) {
        def FROM( source: EntityCursor ) = targetCursor.include( targetCursor )
        def FROM( source: EntityCursor, position: CursorPosition ) = targetCursor.include( targetCursor, position )
    }
}
