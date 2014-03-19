/**
 *
 */
package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon._

/**
 * @author dgc
 *
 */
class ZeidonOperations ( ) {

    def this( task: Task ) = { this(); this.task = task }
    def this( jtask: com.quinsoft.zeidon.Task ) = { this(); task = new Task( jtask ) }

    var task: Task = null
    val NEXT  = CursorPosition.NEXT
    val PREV  = CursorPosition.PREV
    val FIRST = CursorPosition.FIRST
    val LAST  = CursorPosition.LAST

    def VIEW: View = new View( task )

    def FOREACH( cursor: EntityCursor ) = {
      val iterator = new EntityIterator( cursor )
      iterator
    }

    def SETFIRST( cursor: EntityCursor ): SetCursorResult = {
      new SetCursorResult( cursor.jentityCursor.setFirst(), cursor )
    }

    def SETNEXT( cursor: EntityCursor ): SetCursorResult = {
      new SetCursorResult( cursor.jentityCursor.setNext(), cursor )
    }

    def log = task.jtask.log()

    class EntityIterator( val cursor: EntityCursor ) {

      var scopingEntity: String = null
      var predicate: () => Boolean = {() => true}

      /**
       * Set the scoping entity.
       */
      def UNDER( ei: AbstractEntity ) = {
        scopingEntity = ei.jviewEntity.getName()
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
            cursor.iterator( scopingEntity ).foreach( ei => { if ( predicate() ) func } )
        else
            cursor.iterator.foreach( ei => { if ( predicate() ) func } )
      }

      def apply( func: => Unit ) = {
        if ( scopingEntity != null )
            cursor.iterator( scopingEntity ).foreach( ei => { if ( predicate() ) func } )
        else
            cursor.iterator.foreach( ei => { if ( predicate() ) func } )
      }

//      def DO( func: ( EntityInstance ) => Unit ) = {
//        cursor.iterator.foreach( ei => func )
//      }

    }

}
