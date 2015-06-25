/**
  * This file is part of the Zeidon Java Object Engine (Zeidon JOE).
  *
  * Zeidon JOE is free software: you can redistribute it and/or modify
  * it under the terms of the GNU Lesser General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * Zeidon JOE is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public License
  * along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.
  *
  * Copyright 2009-2015 QuinSoft
  */
package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon.scala.Ternary._
import scala.language.dynamics
import com.quinsoft.zeidon.objectdefinition.EntityDef

/**
 * @author dgc
 *
 */
case class Task ( val jtask: com.quinsoft.zeidon.Task, oe: ObjectEngine = null ) extends Dynamic {

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
     * Drops the name for a view.
     *
     * This does not automatically drop the view; the view may have another name.
     * If a view is specified then an exception will be thrown if the view is
     * different from the one that is named.
     */
    def dropViewName( viewName: String, view: View = null ) = {
        jtask.dropNameForView( viewName, view )
        this
    }

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