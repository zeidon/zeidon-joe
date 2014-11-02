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
    val task: Task
    private var cursorResult: SetCursorResult = null

    val ON = View.ON // Used to build "BASED ON LOD" syntax.

    val NEXT  = CursorPosition.NEXT
    val PREV  = CursorPosition.PREV
    val FIRST = CursorPosition.FIRST
    val LAST  = CursorPosition.LAST
    val zCURSOR_SET = CursorResult.SET.toInt()

    def VIEW: View = new View( task )

    def GETVIEW( viewName: String ) = task.jtask.getViewByName( viewName )

    def FOREACH( cursor: EntityCursor ) = {
        val iterator = new EntityIterator( cursor )
        iterator
    }

    def RESULT = cursorResult

    def SETFIRST( cursor: EntityCursor ): SetCursorResult = {
        cursorResult = new SetCursorResult( cursor.jentityCursor.setFirst(), cursor )
        cursorResult
    }

    def SETNEXT( cursor: EntityCursor ): SetCursorResult = {
        cursorResult = new SetCursorResult( cursor.jentityCursor.setNext(), cursor )
        cursorResult
    }

    /**
     * For use inside of view.Entity.each{} loops, this will break execution of the
     * current entity cursor and continue with the next.
     */
    def next() = Nexts.next()

    def break() = util.control.Breaks.break()

    def log = task.jtask.log()

    class EntityIterator( val cursor: EntityCursor ) {

      var scopingEntity: String = null
      var predicate: () => Boolean = {() => true}

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
            cursor.under( scopingEntity ).foreach( ei => { if ( predicate() ) func } )
        else
            cursor.iterator.foreach( ei => { if ( predicate() ) func} )
      }

      /*
      def apply( func: => Unit ) = {
        if ( scopingEntity != null )
            cursor.iterator( scopingEntity ).foreach( ei => { if ( predicate() ) func } )
        else
            cursor.iterator.foreach( ei => { if ( predicate() ) func } )
      }
*/

    }
}
