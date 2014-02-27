/**
 *
 */
package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon._

/**
 * @author dgc
 *
 */
class ZeidonOperations ( val task: Task ) {

    def this( jtask: com.quinsoft.zeidon.Task ) = this( new Task( jtask ) )

    val NEXT  = CursorPosition.NEXT
    val PREV  = CursorPosition.PREV
    val FIRST = CursorPosition.FIRST
    val LAST  = CursorPosition.LAST

    def VIEW: View = new View( task )

    def FOREACH( cursor: EntityCursor ) = {
      val iterator = new EntityIterator( cursor )
      iterator
    }

    def log = task.jtask.log()

    class EntityIterator( val cursor: EntityCursor ) {

      var scopingEntity: String = null

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

      def DO( func: => Unit ) = {
        if ( scopingEntity != null )
            cursor.iterator( scopingEntity ).foreach( ei => func )
        else
            cursor.iterator.foreach( ei => func )
      }

//      def DO( func: ( EntityInstance ) => Unit ) = {
//        cursor.iterator.foreach( ei => func )
//      }

    }

}
