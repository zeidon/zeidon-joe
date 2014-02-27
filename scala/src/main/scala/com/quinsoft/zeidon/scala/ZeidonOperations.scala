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

    val NEXT = CursorPosition.NEXT

    def VIEW: View = new View( task )

    def log = task.jtask.log()
}
