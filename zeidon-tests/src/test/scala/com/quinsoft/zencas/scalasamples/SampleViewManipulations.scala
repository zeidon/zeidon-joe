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

import scala.collection.JavaConversions._

import com.quinsoft.zeidon.ObjectEngine
import com.quinsoft.zeidon.Task
import com.quinsoft.zeidon.scala.EntityInstance
import com.quinsoft.zeidon.scala.View
import com.quinsoft.zeidon.scala.Implicits._
import com.quinsoft.zeidon.scala.View.jview2view
import com.quinsoft.zeidon.scala.ZeidonOperations
import com.quinsoft.zeidon.standardoe.JavaObjectEngine

/**
 * Examples of how to make miscellaneous View calls. For sample Cursor manipulations
 * see SampleCursorManipulation.
 */
class SampleViewManipulations( var task: Task ) extends ZeidonOperations  {

    def creatingViews = {
        /*
         * Creating a new view.
         *
         * VML: VIEW mUser BASED ON LOD mUser
         */

        // VML-like syntax:
        val mUser = VIEW BASED ON LOD "mUser"

        // Scala syntax
        val mUser2 = View( task ) basedOn "mUser"

        // These views have no OI attached to them.
        println( mUser.isNull ) // Prints "true"

        // Instantiate an empty OI.
        mUser activateEmpty()
        println( mUser.isNull )  // Prints "false"
        println( mUser.isEmpty ) // Prints "true"

        mUser.User create()
        println( mUser.isEmpty ) // Prints "false"
    }

    def gettingViewsByName = {
        /*
         * Creating a new view.
         *
         * VML: VIEW mUser REGISTERED AS mUser
         */

        // VML-like syntax:
        val mUser = REGISTERED AS "mUser"

        // Scala syntax
        val mUser2 = task.getView( "mUser" )

    }

    /**
     * Serialize an OI to a temporary file.  This writes the incremental values
     * to preserve the OI's meta information.
     *
     * Returns the filename.
     */
    def serializeSingleOiToJsonFile( view: View ) : String = {
        view.serializeOi.asJson.withIncremental.toTempFile()
    }

    /**
     * Deserialize an OI from a JSON file.  This assumes that the JSON file was written
     * with incremental flags and thus the LOD name does not need to be specified.
     */
    def deserializeOiFromFile( filename: String ) : View = {
        task.deserializeOi.fromFile( filename ).asJson().activateFirst()
    }

    /**
     * Examples of using SelectSets.
     */
    def selectSets( mUser: View ) = {

        // Create a named SelectSet.
        val set = mUser.getSelectSet( "MySelectSet")

        // Select the current root.  This does not select any children.
        set.select( mUser.root )
        assert(   set.isSelected( mUser.root ),      "Root is not selected! " )
        assert( ! set.isSelected( mUser.UserGroup ), "UserGroup is selected! " )

        // Remove all EIs from a select set.
        set.clear()
        assert( ! set.isSelected( mUser.root ),      "Root is selected! " )

        // Select a root and all its children.
        set.selectSubobject( mUser.root )
        assert( set.isSelected( mUser.root ),      "Root is not selected! " )
        assert( set.isSelected( mUser.UserGroup ), "UserGroup is not selected! " )

        set.clear()

        // Re-retrieve the named SelectSet by name.
        val set2 = mUser.getSelectSet( "MySelectSet")
        assert( set eq set2, "SelectSets are not the same!" )

        // Use selectWhere to select multiple entities at once.  This will loop through the
        // specified entities and select the ones that match a predicate.  This selects
        // all UserGroup entities with ID > 1
        set.selectWhere( _.UserGroup.ID > 1 )
        set.clear()

        // Use "under()" to specify scoping.  This example will select all Report entities under
        // the current User with ID > 400
        set.selectWhere( _.Report.under( _.User ).ID > 400 )
        set.clear()

        // selectWhere can only be used with a single operator.  To select EIs matching
        // multiple predicates use Scala 'for' comprehension.
        // Following selects all Report entities with ID > 300 and a Description.
        set.selectAll( for ( e <- mUser.Report if e.ID > 300 && ! e.Description.isNull ) yield e )
        set.clear()

        // Multiple entity types can be selected.
        mUser.Report.all {
            if ( ! mUser.Report.Description.isNull || mUser.Report.ID == 1045) {
                set.select( mUser.Report )
                set.select( mUser.ReportGroup )
            }
        }

        // SelectSets may be used to loop through the selected entities.
        // NOTE: this will change the underlying View (mUser in this example).
        set.foreach { ei => println( ei.name ) }

        // Since the underlying view is changed as part of the each() it can be
        // used in the lambda function.
        set.each { println( mUser.Report.Name ) }

        // The SelectSet iterator will loop through all the EIs in the OI to preserve
        // hierarchical order, which may be slow.  A faster iteration is to use the
        // underlying Set iterator directly.  However this does not preserve ordering.
        // This also uses the Java EntityInstance instead of the Scala EntityInstance.
        set.iterator().foreach { ei => println( ei.getAttribute( "Name" ) ) }

        // Another way is to loop through the cursor explicitly and check to see if
        // the EI is selected.  This preserves OI ordering and is faster than looping
        // through all the entities in the OI.
        mUser.Report.each {
            if ( set.isSelected( mUser.Report ) )
                println( mUser.Report )
        }

        // Removing an entity from the SelectSet can be done with deselect (or remove).
        set.deselect( mUser.ReportGroup )

        // Note that above deselect does not remove children.
        assert( set.isSelected( mUser.Report ), "Report is not selected!" )

        // To remove an entity and its children, use:
        set.deselectSubobject( mUser.ReportGroup )
        assert( ! set.isSelected( mUser.Report ), "Report is still selected!" )
    }

    def runAll( view: View ) = {
        creatingViews
        val filename = serializeSingleOiToJsonFile( view )
        deserializeOiFromFile( filename )
        selectSets( view )
    }

}

object SampleViewManipulations {

    def main(args: Array[String]): Unit = {

        // Load the object engine and create a task.
        val oe = JavaObjectEngine.getInstance()
        val task = oe.createTask("ZENCAs")

        // Activate an mUser that we can use for samples.
        val sampleActivates = new SampleActivates( task )
        val mUser = sampleActivates.activateSimple

        val sample = new SampleViewManipulations( task )
        sample.runAll( mUser )
    }

}