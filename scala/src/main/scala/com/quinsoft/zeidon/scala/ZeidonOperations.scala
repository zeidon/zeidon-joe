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
    val NEXT = CursorPosition.NEXT
    
    def VIEW: View = new View( task )

}
