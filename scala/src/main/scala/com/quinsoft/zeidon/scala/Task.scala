/**
 *
 */
package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon.scala.Ternary._
/**
 * @author dgc
 *
 */
case class Task ( val jtask: com.quinsoft.zeidon.Task, oe: ObjectEngine = null ) {

    val objectEngine: ObjectEngine = oe ?: new ObjectEngine( jtask.getObjectEngine() )

    def this( task: Task ) = this( task.jtask, task.objectEngine  )

    def getView( viewName: String ): View = {
        val jview = jtask.getViewByName( viewName )
        if ( jview == null )
            return null

        new View( jview )
    }

    def deserializeOi = jtask.deserializeOi()
}

object Task {
    /**
     * Convert a Scala task to a Java task.
     */
    implicit def task2jtask( task: com.quinsoft.zeidon.scala.Task ) = task.jtask
}