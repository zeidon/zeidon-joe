/**
 *
 */
package com.quinsoft.zeidon.scala

/**
 * @author dgc
 *
 */
case class Task ( val jtask: com.quinsoft.zeidon.Task ) {

    private[scala] var objectEngine: ObjectEngine = null

    def this( jtask: com.quinsoft.zeidon.Task, oe: ObjectEngine ) = {
        this(jtask)
        objectEngine = oe
    }

    def this( task: Task ) = this( task.jtask )

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