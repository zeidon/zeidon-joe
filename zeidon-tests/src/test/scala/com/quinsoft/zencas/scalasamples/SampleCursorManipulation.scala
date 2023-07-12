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

import com.quinsoft.zeidon.ObjectEngine
import com.quinsoft.zeidon.ZeidonException
import com.quinsoft.zeidon.scala.AbstractEntity
import com.quinsoft.zeidon.scala.EntityCursor
import com.quinsoft.zeidon.scala.EntityInstance
import com.quinsoft.zeidon.scala.Implicits._
import com.quinsoft.zeidon.scala.Nexts
import com.quinsoft.zeidon.scala.Task
import com.quinsoft.zeidon.scala.View
import com.quinsoft.zeidon.scala.VmlCursorResult
import com.quinsoft.zeidon.scala.ZeidonOperations
import com.quinsoft.zeidon.scala.basedOn
import com.quinsoft.zeidon.standardoe.JavaObjectEngine
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

/**
 *  This gives examples of how to manipulate cursors.  Usually there are two
 *  ways to manipulate cursors in Scala, the "VML" way and the "Scala" way.
 *  When appropriate both ways will be shown.
 */
@RunWith(classOf[JUnitRunner])
class SampleCursorManipulation extends AnyFunSuite with ZeidonOperations {

    val oe = JavaObjectEngine.getInstance()
    var task = oe.createTask("ZENCAs")
    val mUser = task.deserializeOi().setLodDef( "mUser" ).fromFile("testdata/ZENCAs/data/mUser-SampleData.json").unpickle

    /**
     * Simple SET CURSOR FIRST
     */
    test( "setCursorFirst" ) { setCursorFirst( mUser ) }

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
    test( "setCursorFirstWhere" ) { setCursorFirstWhere( mUser ) }
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
    test( "setCursorFirstCompoundWhere" ) { setCursorFirstCompoundWhere( mUser ) }
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
    test( "setCursorFirstWithScoping" ) { setCursorFirstWithScoping( mUser ) }
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
    test( "setCursorFirstUsingHashKey" ) { setCursorFirstUsingHashkey( mUser ) }
    def setCursorFirstUsingHashkey( mUser: View @basedOn( "mUser") ) = {
        val colleges = new View( task ) basedOn "mCollege-withHash"
        colleges.buildQual().activate()
        assert( colleges.College.set( _.ID = 4 ) )
        assert( colleges.College.ID == 4 )
    }

    test( "forEachCursor" ) { forEachCursor( mUser ) }
    def forEachCursor( mUser: View @basedOn( "mUser") ) = {

        /* VML:
         *          FOR EACH mUser.User WHERE mUser.User.ID = 490 OR mUser.User.ID = 491
         *              ...
         *          END
         */

        // VML way
        FOREACH( mUser.User ) WHERE ( mUser.User.ID == 490 || mUser.User.ID == 491 ) DO {
            if ( mUser.User.ID == 490 ) {
                next() // This skips the following processing and continues with the next mUser.User
            }

            if ( mUser.User.ID == 491 ) {
                break() // This breaks the FOREACH loop.
            }
        }

        // Scala way #1.  Note that this does not have an explicit WHERE clause.
        mUser.User.each {
            if ( mUser.User.ID == 490 || mUser.User.ID == 491 ) {
                if ( mUser.User.ID == 490 ) {
                    next() // This skips the following processing and continues with the next mUser.User
                }

                if ( mUser.User.ID == 491 ) {
                    break() // This breaks the FOREACH loop.
                }
            }
        }

        // Scala way #2.
        // Scala has a standard 'foreach' method for running through an iterator.
        // The difference is that using foreach must specify the object that is being returned
        // for each iterator (which is an EntityInstance for cursors).
        // Note that this does not have an explicit WHERE clause.
        var found491 = false
        mUser.User.foreach { ei =>
            // You can use either the full cursor or 'ei'.
            if ( mUser.User.ID == 490 || ei.ID == 491 ) {
                if ( mUser.User.ID == 490 ) {
                    next() // This skips the following processing and continues with the next mUser.User
                }

                if ( ei.ID == 491 ) {
                    found491 = true
                    break() // This breaks the FOREACH loop.
                }
            }
        }

        if ( ! found491 )
            throw new ZeidonException( "Problem with foreach; didn't find ID 491" )

        // Scala way #3
        // The 'for' comprehension can be used to run through all entities.  It can be used
        // to generate a list of attributes.
        val ids = ( for ( e <- mUser.User ) yield e.ID ).mkString( "," )
        println( "ID list = " + ids )
    }

    test( "forEachCursorWithScoping" ) { forEachCursorWithScoping( mUser ) }
    def forEachCursorWithScoping( mUser: View @basedOn( "mUser") ) = {
        val mUser = task.mUser.activateWith( _.where( _.Report.exists ).limit( 10 ) )
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

    test( "sortEntities" ) { sortEntities( mUser ) }
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

    test( "deleteEntities" ) {
        // Activate 10 users with a faculty
        val mUser = task.mUser.activateWith( _.where( _.Faculty.exists ).limit( 10 ) )
        assert( mUser.Faculty.all.size == 10 )

        // To delete a single entity
        mUser.Faculty.delete()
        assert( mUser.Faculty.all.size == 9 )

        // To delete all entities UNDER THE PARENT that match a predicate
        mUser.Faculty.filter( _.ID == 242 ).foreach( _.delete )
        val c1 =  mUser.Faculty.all.size

        // For a more complex predicate use the view in the predicate.
        mUser.Faculty.deleteWhere( mUser.Faculty.Status == "A" && mUser.Faculty.ID == 100 )
        val c2 =  mUser.Faculty.all.size

        // To delete all
        mUser.Faculty.all.foreach( _.delete )
        assert( mUser.Faculty.all.size == 0 )
    }

    test( "usingFilter" ) {
        mUser.Report.filter { _.ID > 0 }.foreach( e => println( e ) )
    }

    test( "countEntities" ) {
        val count = mUser.Report.count
        val count2 = mUser.Report.count( _.ID >< (338, 400) )
        val count3 = mUser.Report.count( ei => { print( ei.ID + " " ); ei.ID < 10 } )
        print( count3 )
    }
}
