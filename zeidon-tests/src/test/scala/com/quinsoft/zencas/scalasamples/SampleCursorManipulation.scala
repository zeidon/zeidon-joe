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
 * Copyright 2009-2014 QuinSoft
 */
package com.quinsoft.zencas.scalasamples

import com.quinsoft.zeidon.scala.ZeidonOperations
import com.quinsoft.zeidon.scala.Task
import com.quinsoft.zeidon.scala.ObjectEngine
import com.quinsoft.zeidon.scala.View
import com.quinsoft.zeidon.scala.VmlCursorResult
import com.quinsoft.zeidon.scala.basedOn

/**
 *  This gives examples of how to manipulate cursors.  Usually there are two
 *  ways to manipulate cursors in Scala, the "VML" way and the "Scala" way.
 *  When appropriate both ways will be shown.
 */
class SampleCursorManipulation( val task: Task ) extends ZeidonOperations {

    /**
     * Simple SET CURSOR FIRST
     */
    def setCursorFirst( mUser: View @basedOn( "mUser") ) = {
        /* VML:
         *          SET CURSOR FIRST mUser.User
         *          IF RESULT >= zCURSOR_SET THEN
         *              ...
         *          END
         */

        // VML way 1
        var RESULT = SETFIRST( mUser.User )
        if ( RESULT >= zCURSOR_SET ) {
            println( "Cursor was set" )
        }

        // VML way 2
        if ( SETFIRST( mUser.User ) ) {
            println( "Cursor was set" )
        }

        // Scala way
        if ( mUser.User.setFirst ) {
            println( "Cursor was set" )
        }
    }

    /**
     * Set cursor with a WHERE predicate
     */
    def setCursorFirstWhere( mUser: View @basedOn( "mUser") ) = {
        /* VML:
         *          SET CURSOR FIRST mUser.User WHERE mUser.User.ID = 490
         *          IF RESULT >= zCURSOR_SET THEN
         *              ...
         *          END
         */

        // VML way
        if ( SETFIRST( mUser.User ) WHERE( mUser.User.ID == 490 ) ) {
            println( "Cursor was set" )
        }

        // Scala way
        if ( mUser.User.setFirst( mUser.User.ID == 490 ) ) {
            println( "Cursor was set" )
        }
    }

    /**
     * Set cursor with a multipart WHERE predicate
     */
    def setCursorFirstCompoundWhere( mUser: View @basedOn( "mUser") ) = {
        /* VML:
         *          SET CURSOR FIRST mUser.User WHERE mUser.User.ID = 490 OR mUser.User.ID = 491
         *          IF RESULT >= zCURSOR_SET THEN
         *              ...
         *          END
         */

        // VML way
        if ( SETFIRST( mUser.User ) WHERE( mUser.User.ID == 490 || mUser.User.ID == 491 ) ) {
            println( "Cursor was set" )
        }

        // Scala way
        if ( mUser.User.setFirst( mUser.User.ID == 490  || mUser.User.ID == 491 ) ) {
            println( "Cursor was set" )
        }
    }

    /**
     * Set cursor with a multipart WHERE predicate
     */
    def setCursorFirstWithScoping( mUser: View @basedOn( "mUser") ) = {
        /* VML:
         *          SET CURSOR FIRST mUser.User WHERE mUser.User.ID = 490 OR mUser.User.ID = 491
         *          IF RESULT >= zCURSOR_SET THEN
         *              ...
         *          END
         */

        // VML way
        if ( SETFIRST( mUser.Report ) UNDER( mUser.UserGroup ) WHERE( mUser.Report.ID == 589 ) ) {
            println( "Cursor was set" )
        }

        // Scala way
        if ( mUser.Report.setFirst( mUser.Report.ID == 589, mUser.UserGroup ) ) {
            println( "Cursor was set" )
        }
    }

    /**
     * A hashkey is specified in the LOD for attribute values that are unique.  Zeidon JOE
     * keeps a backing hashmap of the values that allow SET FIRST processing to set the
     * cursor with a single lookup instead of looping through all the entities.
     */
    def setCursorFirstUsingHashkey( mUser: View @basedOn( "mUser") ) = {
        if ( mUser.User.set( _.ID = 409 ) )
            println( "Cursor is set" )
    }

    def forEachCursor( mUser: View @basedOn( "mUser") ) = {

        /* VML:
         *          FOR EACH mUser.User WHERE mUser.User.ID = 490 OR mUser.User.ID = 491
         *              ...
         *          END
         */

        // VML way
        FOREACH( mUser.User ) WHERE( mUser.User.ID == 490 || mUser.User.ID == 491 ) DO {

            println( "User ID = " + mUser.User.ID )

            if ( mUser.User.ID == 490 ) {
                next() // This skips the following processing and continues with the next mUser.User
            }

            if ( mUser.Report.ID == 491 ) {
                break() // This breaks the FOREACH loop.
            }
        }

        // Scala way.  Note that this does not have an explicit WHERE clause.
        mUser.User.each {
            if ( mUser.User.ID == 490 || mUser.User.ID == 491 ) {
                println( "User ID = " + mUser.User.ID )

                if ( mUser.User.ID == 490 ) {
                    next() // This skips the following processing and continues with the next mUser.User
                }

                if ( mUser.Report.ID == 491 ) {
                    break() // This breaks the FOREACH loop.
                }
            }
        }
    }

    def forEachCursorWithScoping( mUser: View @basedOn( "mUser") ) = {

        // VML way
        FOREACH( mUser.Report ) UNDER( mUser.UserGroup ) WHERE( mUser.Report.ID == 589 ) DO {
            if ( mUser.Report.ID == 594 ) {
                println( "skipping " + mUser.Report.ID )
                next()
            }

            println( "Report ID = " + mUser.Report.ID )

            if ( mUser.Report.ID == 560 ) {
                println( "Stopping after " + mUser.Report.ID )
                break()
            }
        }

        println( "Final ID = " + mUser.Report.ID )
        println( "==================================================" )

        // Scala way
        mUser.Report.under( mUser.UserGroup ).each {
            if ( mUser.Report.ID == 594 ) {
                println( "skipping " + mUser.Report.ID )
                next()
            }

            println( "Report ID = " + mUser.Report.ID )

            if ( mUser.Report.ID == 560 ) {
                println( "Stopping after " + mUser.Report.ID )
                break()
            }
        }

        println( "Final ID = " + mUser.Report.ID )
    }
}

object SampleCursorManipulation {

   def main(args: Array[String]): Unit = {

        // Load the object engine and create a task.
        val oe = ObjectEngine.getInstance
        val task = oe.createTask("ZENCAs")

        val activator = new SampleActivates( task )
        var mUser = activator.activateSimple

//        oe.startBrowser
        val sampler = new SampleCursorManipulation( task )
        sampler.setCursorFirst(mUser)
        sampler.setCursorFirstWhere(mUser)
        sampler.setCursorFirstWithScoping(mUser)

        sampler.forEachCursor(mUser)
//        mUser.logObjectInstance
    }

}