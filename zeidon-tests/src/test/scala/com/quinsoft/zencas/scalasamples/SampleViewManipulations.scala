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

/**
 * Examples of how to make miscellaneous View calls. For sample Cursor manipulations
 * see SampleCursorManipulation.
 */
class SampleViewManipulations( val task: Task ) extends ZeidonOperations  {

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

}

object SampleViewManipulations {

    def main(args: Array[String]): Unit = {

        // Load the object engine and create a task.
        val oe = ObjectEngine.getInstance
        val task = oe.createTask("ZENCAs")

        // Activate an mUser that we can use for samples.
        val sample = new SampleActivates( task )
        var mUser = sample.activateSimple
    }

}