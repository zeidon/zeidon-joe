package com.quinsoft.zencas.scalasamples

import com.quinsoft.zeidon.scala.ZeidonOperations
import com.quinsoft.zeidon.ObjectEngine
import com.quinsoft.zeidon.scala.Task
import com.quinsoft.zeidon.standardoe.JavaObjectEngine
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

/**
 * Examples of how to execute Zeidon activates using Scala.
 */
@RunWith(classOf[JUnitRunner])
class SampleActivates extends AnyFunSuite with ZeidonOperations {

    val oe = JavaObjectEngine.getInstance()
    var task = oe.createTask("Northwind")

    /**
     * This method shows how to execute a simple activate.  The activate uses the
     * "activateWhere" method for activating on a single attribute.
     */
    test( "activateSimple" ) {

        /* VML:

        VIEW order BASED ON Order
        ACTIVATE order WHERE Order.OrderId.ID = 11009

        */

        val order = VIEW basedOn "Order"
        order.activateWhere( _.Order.OrderId = 11009 )

        /*
         * Comments:
         *   - The "_" is the Scala placeholder for the View.
         *   - Note that the comparison is done with "=", not "==".
         *   - activateWhere builds the qualification and executes the activation.
         *     See below for a contrasting example.
         */

        /**
         * To activate all root entities, use .activateAll.
         */
        val region = VIEW basedOn "Region"
        region.activateAll

        /*
        Scala way: you can use the task with the LOD name directly.
        */
        val order2 = task.Order.activate( _.Order.OrderId = 11009 )
    }

    /**
     * An activate with an OR statement.  This can not use "activateWhere" because
     * there are multiple elements in the qualification.
     */
    test( "activateWithOr" ) {
        /* VML:

        VIEW order BASED ON Order
        ACTIVATE order WHERE order.Order.OrderId = 11009 OR order.Order.OrderId = 11010

        * Comments:
        *  - Note that the qualification is built with buildQual and the final activate
        *    is done with .activate().  The activate will not be executed without it.
        */

        val order = VIEW basedOn "Order"
        order.buildQual( _.Order.OrderId = 11009 )
                    .or( _.Order.OrderId = 11010 ).activate
        assert( order.getTotalRootCount() == 2 )

        /*
            Complex qualification requires using the BuildQual:
         */
        val order2 = task.Order.activateWith(
                   _.where( _.Order.OrderId = 11009 )
                    .or( _.Order.OrderId = 11010 ) )
        assert( order2.getTotalRootCount() == 2 )
    }

    /**
     * An activate with logical grouping.  In VML this would use parenthesis.
     */
    test( "activateWithGrouping" ) {
        /* VML:

        VIEW order BASED ON Order
        ACTIVATE order WHERE ( ... AND ... )
                          OR ( ... AND ... )
        */

        val order = VIEW basedOn "Order"
        order.buildQual()
                    .all( _.Order.OrderId = 11009, _.Order.ShipCountry = "Spain" )
                    .orAll( _.Order.OrderId = 11010, _.Order.ShipCountry = "Italy" )
                    .activate

        /*
         * Comments:
         *  - There are no arguments to buildQual
         *
         */
    }

    /**
     * An activate with RESTRICTING clause.
     */
    test( "activateWithRestricting" ) {
        /* VML:

        VIEW order BASED ON Order
        ACTIVATE order WHERE order.Order.OrderId = 11009
            RESTRICTING order.OrderDetail TO ( order.OrderDetail.Quantity = 0 OR
                                               order.OrderDetail.Quantity = 1 )
        */

        val order = VIEW basedOn "Order"
        order.buildQual( _.Order.OrderId = 11009 )
                    .restricting( _.OrderDetail )
                        .to( _.OrderDetail.Quantity = 0 )
                        .or( _.OrderDetail.Quantity = 2 )
                    .activate
    }

    /**
     * Executing an activate asynchronously.
     */
    test( "asynchronousActivate" ) {
        /* VML:
         *
         * No equivalent in VML.
         */

        val order = VIEW basedOn "Order"
        order.buildQual( _.Order.OrderId = 11009 )
                    .or( _.Order.OrderId = 11010 )
                    .asynchronous
                    .activate

        // ...do other work here...

        // First reference of mUser will block until activate is finished.
        println( "ID = " + order.Order.OrderId )
    }

    /**
     * Activate an entity by comparing two of its attributes.  This generates SQL that
     * compares two columns.
     */
    test( "activateWithColumnQualification" ) {
        /* VML:
         *
         * No equivalent in VML.
         */

        /*
         * Load all Orders that have have been OrderDate same as ShippedDate
         *
         * Note: the QualBuilder must be explicitly named ('qual' in example below)
         * because it is used twice.  Using "_" would cause a Scala compile error.
         */
        val order = VIEW basedOn "Order"
        order.buildQual( qual => qual.Order.OrderDate = qual.Order.ShippedDate )
                    .rootOnlyMultiple()
                    .activate

        /*
         * Children can be qualified on attributes from a parent.
         */
        order.buildQual( qual => qual.OrderDetail.UnitPrice = qual.Product.UnitPrice )
            .rootOnlyMultiple()
            .activate
    }

    test( "miscActivates" ) {
        /*
         * Activate using LIKE
         */
        val order = VIEW basedOn "Order"
        order.activateWhere( _.Order.ShipCountry like "North%" )

        // Conditional qualification--only adds a qualification if a predicate
        // is true.
        val id = 10

        order.buildQual( _.Order.OrderId > 0 )
             .when( id > 0, _.and( _.Order.OrderId < id ),
                            _.and( _.Order.OrderId > id ) )
             .rootOnlyMultiple()
             .activate()

        order.buildQual( _.Order.OrderId > 0 )
             .when( id > 0, _.and( _.Order.OrderId < id ) )
             .rootOnlyMultiple()
             .activate()

        order.buildQual( _.Order.OrderId in (1,2,3,4) ).rootOnly().activate()
        order.buildQual( _.Order.OrderId.not() in (1,2,3,4) ).and( _.Order.OrderId < 10 ).rootOnly().activate()
        order.buildQual( _.Order.ShipCountry in ( "Spain", "Italy") ).rootOnly().activate()

        order.activate{ _.fromJson( """{ "OrderId": 11009 } """) }
    }
}
