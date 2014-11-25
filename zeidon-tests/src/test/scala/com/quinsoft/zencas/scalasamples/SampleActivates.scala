package com.quinsoft.zencas.scalasamples

import com.quinsoft.zeidon.scala.ObjectEngine
import com.quinsoft.zeidon.scala.ZeidonOperations
import com.quinsoft.zeidon.scala.Task
import com.quinsoft.zencas.scalasamples.SampleActivates;

/**
 * Examples of how to execute Zeidon activates using Scala.
 */
class SampleActivates( val task: Task ) extends ZeidonOperations {

    /**
     * This method shows how to execute a simple activate.  The activate uses the
     * "activateWhere" method for activating on a single attribute.
     */
    def activateSimple = {

        /* VML:

        VIEW mUser BASED ON muser
        ACTIVATE mUser WHERE mUser.User.ID = 490

        */

        val mUser = VIEW basedOn "mUser"
        mUser.activateWhere( _.User.ID = 490 )

        /*
         * Comments:
         *   - The "_" is the Scala placeholder for the View.
         *   - Note that the comparison is done with "=", not "==".
         *   - activateWhere builds the qualification and executes the activation.
         *     See below for a contrasting example.
         */
    }

    /**
     * Qualification can be done with operators besides "="
     */
    def activateSimpleWithOtherComparators = {

        /* VML:

        VIEW mUser BASED ON muser
        ACTIVATE mUser WHERE mUser.User.ID < 10

        */

        val mUser = VIEW basedOn "mUser"
        mUser.activateWhere( _.User.ID < 10 )

        /*
         * Comments:
         *   - <= > >= != are also valid comparitors.
         */
    }

    /**
     * An activate with an OR statement.  This can not use "activateWhere" because
     * there are multiple elements in the qualification.
     */
    def activateWithOr = {
        /* VML:

        VIEW mUser BASED ON muser
        ACTIVATE mUser WHERE mUser.User.ID = 490 OR mUser.User.ID = 491

        */

        val mUser = VIEW basedOn "mUser"
        mUser.buildQual( _.User.ID = 490 )
                    .or( _.User.ID = 491 ).activate

        /*
         * Comments:
         *  - Note that the qualification is built with buildQual and the final activate
         *    is done with .activate().  The activate will not be executed without it.
         */
    }

    /**
     * An activate with logical grouping.  In VML this would use parenthesis.
     */
    def activateWithGrouping = {
        /* VML:

        VIEW mUser BASED ON muser
        ACTIVATE mUser WHERE ( mUser.User.ID = 490 AND mUser.User.UserName = "milburnr" )
                          OR ( mUser.User.ID = 491 AND mUser.User.UserName = "jblow" )
        */

        val mUser = VIEW basedOn "mUser"
        mUser.buildQual()
                    .all( _.User.ID = 490, _.User.UserName = "milburnr" )
                    .orAll( _.User.ID = 491, _.User.UserName = "jblow" )
                    .activate

        /*
         * Comments:
         *  - There are no arguments to buildQual
         *
         * The generated WHERE clause looks like this:
         *
         *  WHERE  ( z_USER.ID = 490 AND z_USER.USERNAME = 'milburnr' )  OR  ( z_USER.ID = 491 AND z_USER.USERNAME = 'jblow' ) ;
         *
         */
    }

    /**
     * An activate with RESTRICTING clause.
     */
    def activateWithRestricting = {
        /* VML:

        VIEW mUser BASED ON muser
        ACTIVATE mUser WHERE mUser.User.ID = 490
            RESTRICTING mUser.Report TO ( mUser.Report.Name = "Pre-Law" OR
                                          mUser.Report.Name = "Social Work" )
        */

        val mUser = VIEW basedOn "mUser"
        mUser.buildQual( _.User.ID = 490 )
                    .restricting( _.Report )
                        .to( _.Report.Name = "Pre-Law" )
                        .or( _.Report.Name = "Social Work" )
                    .activate

        /*
         * Comments:
         *  -
         */
    }

    def asynchronousActivate = {
        /* VML:
         *
         * No equivalent in VML.
         */

        val mUser = VIEW basedOn "mUser"
        mUser.buildQual( _.User.ID = 490 )
                    .or( _.User.ID = 491 )
                    .asynchronous
                    .activate

        // ...do other work here...

        // First reference of mUser will block until activate is finished.
        println( "ID = " + mUser.User.ID )

        mUser
    }
}

object SampleActivates {

    def main(args: Array[String]): Unit = {

        // Load the object engine and create a task.
        val oe = ObjectEngine.getInstance
        val task = oe.createTask("ZENCAs")

        val sample = new SampleActivates( task )

        var mUser = sample.activateSimple
        mUser = sample.activateSimpleWithOtherComparators
        mUser = sample.activateWithOr
        mUser = sample.activateWithGrouping
        mUser = sample.activateWithRestricting
        mUser = sample.asynchronousActivate

//        mUser.logObjectInstance
    }

}