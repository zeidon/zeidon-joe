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

import com.quinsoft.zeidon.scala.ObjectEngine
import com.quinsoft.zeidon.scala.Task
import com.quinsoft.zeidon.scala.View
import com.quinsoft.zeidon.scala.View._
import com.quinsoft.zeidon.scala.ZeidonOperations
import com.quinsoft.zeidon.scala.EntitySelector

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
        val mUser2 = new View( task ) basedOn "mUser"

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

    def selectSets( mUser: View ) = {
        val set = mUser.getSelectSet()
        set.select( mUser.root )
        set.selectAll( for ( e <- mUser.Report.under( mUser.root ) if e.ID > 300 ) yield e )

        set.selectWhere( _.User.ID = 490 )
        set.selectWhere( _.UserGroup.ID < 1 )
        set.selectWhere( _.UserGroup.ID > 1 )
        set.selectWhere( _.UserGroup.ID <> 1 )
        set.selectWhere( _.Report.under( "User" ).ID > 400 )
        set.selectWhere( _.Report.under( _.User ).ID > 400 )
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
        val oe = ObjectEngine.getInstance
        val task = oe.createTask("ZENCAs")

        // Activate an mUser that we can use for samples.
        val sampleActivates = new SampleActivates( task )
        val mUser = sampleActivates.activateSimple

        val sample = new SampleViewManipulations( task )
        sample.selectSets( mUser )
//        sample.runAll( mUser )
    }

}