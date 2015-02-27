/**
 *
 */
package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon.scala.Ternary._
import scala.language.dynamics
import com.quinsoft.zeidon.objectdefinition.EntityDef

/**
 * @author dgc
 *
 */
case class Task private[zeidon] ( val jtask: com.quinsoft.zeidon.Task, oe: ObjectEngine = null ) extends Dynamic {

    val objectEngine: ObjectEngine = oe ?: new ObjectEngine( jtask.getObjectEngine() )

    private[zeidon] def this( task: Task ) = this( task.jtask, task.objectEngine  )

    /**
     * Retrieves a view by name.  If no view exists with the specified name, returns null.
     */
    def getView( viewName: String ): View = {
        val jview = jtask.getViewByName( viewName )
        if ( jview == null )
            return null

        new View( jview )
    }

    /**
     * Creates a deserializer for deserializing an input stream.
     */
    def deserializeOi = jtask.deserializeOi()
    
    /**
     * This is called when the compiler doesn't recognize a method name.  This
     * is used to create an empty View based on a LOD name.  This allows user
     * code to do:
     * {{{
     * val view = task.mUser activateEmpty
     * }}}
     *
     * Not intended to be called directly by user code.
     */
    def selectDynamic( entityName: String ): View = {
        val lod = jtask.getApplication().getLodDef( jtask, entityName )
        new View( this ) basedOn lod
    }
}

object Task {
    /**
     * Convert a Scala task to a Java task.
     */
    implicit def task2jtask( task: com.quinsoft.zeidon.scala.Task ) = task.jtask
}