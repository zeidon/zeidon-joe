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
class SampleCursorManipulation( var task: Task ) extends ZeidonOperations {

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
        if ( mUser.User.setFirst( mUser.User.ID == 490 || mUser.User.ID == 491 ) ) {
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
            println( "Cursor was set to " + mUser.Report.ID )
        }

        // Scala way 1
        if ( mUser.Report.under( mUser.UserGroup ).setFirst( mUser.Report.ID == 589 ) ) {
            println( "Cursor was set to " + mUser.Report.ID )
        }

        // Scala way 2
        if ( mUser.Report.setFirst( mUser.Report.ID == 589, mUser.UserGroup ) ) {
            println( "Cursor was set to " + mUser.Report.ID )
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
        FOREACH( mUser.User ) WHERE ( mUser.User.ID == 490 || mUser.User.ID == 491 ) DO {

            println( "User ID = " + mUser.User.ID )

            if ( mUser.User.ID == 490 ) {
                next() // This skips the following processing and continues with the next mUser.User
            }

            if ( mUser.Report.ID == 491 ) {
                break() // This breaks the FOREACH loop.
            }
        }

        // Scala way #1.  Note that this does not have an explicit WHERE clause.
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

        // Scala way #2.
        // Scala has a standard 'foreach' method for running through an iterator.
        // The difference is that using foreach must specify the object that is being returned
        // for each iterator (which is an EntityInstance for cursors).
        // Note that this does not have an explicit WHERE clause.
        mUser.User.foreach { ei => {
            // You can use either the full cursor or 'ei'.
            if ( mUser.User.ID == 490 || ei.ID == 491 ) {
                println( "User ID = " + mUser.User.ID )

                if ( mUser.User.ID == 490 ) {
                    next() // This skips the following processing and continues with the next mUser.User
                }

                if ( ei.ID == 491 ) {
                    break() // This breaks the FOREACH loop.
                }
            } }
        }

        // Scala way #3
        // The 'for' comprehension can be used to run through all entities.  It can be used
        // to generate a list of attributes.
        val ids = ( for ( e <- mUser.Report ) yield e.ID ).mkString( "," )
        println( "ID list = " + ids )
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

    def sortEntities( mUser: View @basedOn( "mUser") ) = {

        // Sort Report entities by Name in ascending order.
        mUser.Report.sort( "Name A" )
        println( "\nSort Name A: " )
        mUser.Report.each( println( mUser.Report.Name ) )

        // Sort Report entities by Name in DESCENDING order using sortWith.
        mUser.Report.sortWith( _.Name > _.Name )
        println( "\nSort Name descending: " )
        mUser.Report.each( println( mUser.Report.Name ) )

        // Sort Report entities by Name in ASCENDING order using sortBy.
        mUser.Report.sortBy( _.Name )
        println( "\nSort Name ascending: " )
        mUser.Report.each( println( mUser.Report.Name ) )

        mUser.Report.sortWith( _.ID > _.ID )
        println( "\nSort ID descending: " )
        mUser.Report.each( println( mUser.Report.ID ) )

        // Sort by Name and ID
        mUser.Report.sortWith( (ei1, ei2) => {
            if ( ei1.Name == ei2.Name )
                ei1.ID < ei2.ID
            else
                ei1.Name < ei2.Name
        } )
        println( "\nSort multiple: " )
        mUser.Report.each( println( mUser.Report.Name ) )
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
        sampler.sortEntities(mUser)
//        mUser.logObjectInstance
    }

}