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

import scala.language.dynamics
import com.quinsoft.zeidon.CursorPosition
import util.control.Breaks._
import com.quinsoft.zeidon.scala.Nexts._

/**
 * A trait that is added to a Scala object to give the object VML-like syntax.
 * Requires a task.
 */
trait ZeidonOperations {

    val NEXT = CursorPosition.NEXT
    val PREV = CursorPosition.PREV
    val FIRST = CursorPosition.FIRST
    val LAST = CursorPosition.LAST
    val zCURSOR_SET = com.quinsoft.zeidon.CursorResult.SET.toInt()

    var task: Task

    def REGISTERED = new Register

    val ON = View.ON // Used to build "BASED ON LOD" syntax.


    def VIEW: View = new View( task )

    def GETVIEW( viewName: String ) = task.getViewByName( viewName )

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

    def log = task.log()

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

    /**
     * Syntax filler class to handle REGISTERED AS "viewName"
     */
    class Register {
        def AS( viewName: String ) = GETVIEW( viewName )
    }
}

object ZeidonOperations {
    val AS = new VmlSyntaxFiller
}
