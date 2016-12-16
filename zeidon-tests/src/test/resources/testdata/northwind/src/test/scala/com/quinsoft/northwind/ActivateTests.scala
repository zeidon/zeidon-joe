package com.quinsoft.northwind

import org.scalatest._
import com.quinsoft.zeidon.scala.ZeidonOperations
import com.quinsoft.zeidon.scala.View
import com.quinsoft.zeidon.scala.Implicits._
import com.quinsoft.zeidon.standardoe.JavaObjectEngine
import com.quinsoft.zeidon.ZeidonException
import com.quinsoft.zeidon.PessimisticLockingException

class ActivateTests extends FlatSpec with Matchers {

    val oe = JavaObjectEngine.getInstance()
    oe.startBrowser()
    var task = oe.createTask("Northwind")

    "Activate" should "Should use paging" in {
        val order = View( task ) basedOn "Order"
        val qual = order.buildQual()
                        .withPaging( 10, 1, getTotalCount = true )
                        .readOnly

        qual.activate()

        order.root.count should be (10)
        order.totalRootCount should be (830)
        order.Order.OrderId.toString should be ("10248")

        // Test de/serializing to JSON
        order.serializeOi.toTempDir( "paged-orders.json" )
        var order2 = task.deserializeOi()
                         .setLodDef("Order")
                         .fromTempDir( "paged-orders.json" )
                         .unpickle

        order.root.setFirst
        order2.root.setFirst
        order2.root.count should be (10)
        order2.totalRootCount should be (830)
        order2.Employee.EmployeeId.value should be ( order.Employee.key.value )
        order2.Employee.Photo.value should be ( order.Employee.Photo.value )

        // Test de/serializing to JSON with incrementals
        order.serializeOi.withIncremental.toTempDir( "paged-orders.json" )
        order2 = task.deserializeOi()
                     .fromTempDir( "paged-orders.json" )
                     .unpickle

        order.root.setFirst
        order2.root.setFirst
        order2.root.count should be (10)
        order2.totalRootCount should be (830)
        order2.Employee.EmployeeId.value should be ( order.Employee.key.value )
        order2.Employee.Photo.value should be ( order.Employee.Photo.value )

        // Test de/serializing to XML
        order.serializeOi.toTempDir( "paged-orders.xml" )
        var order3 = task.deserializeOi()
                         .setLodDef("Order")
                         .fromTempDir( "paged-orders.xml" )
                         .unpickle

        order3.root.count should be (10)
        order3.totalRootCount should be (830)

        order3.root.setFirst
        order3.Employee.EmployeeId.value should be ( order.Employee.key.value )
        order3.Employee.Photo.value should be ( order.Employee.Photo.value )

        order.serializeOi.withIncremental.toTempDir( "paged-orders.xml" )
        order3 = task.deserializeOi()
                         .fromTempDir( "paged-orders.xml" )
                         .unpickle

        order3.root.count should be (10)
        order3.totalRootCount should be (830)

        order3.root.setFirst
        order3.Employee.EmployeeId.value should be ( order.Employee.key.value )
        order3.Employee.Photo.value should be ( order.Employee.Photo.value )

        qual.activatePage( 2 )
        order.root.count should be (10)
        order.totalRootCount should be (830)
        order.Order.OrderId.toString should be ("10258")

        val order4 = order.buildQual()
                          .withPaging( 20, 3 )
                          .readOnly
                          .activate()

        order4.root.count should be (20)
        order4.totalRootCount should be (null)
    }

    it should "activate by string name" in {
        val order = View( task ) basedOn "Order"
        order.buildQual( _.withName( "Order.OrderId" ) eq "10248" )
                        .readOnly
                        .activate()
        order.Order.OrderId.toString should be ("10248")
        order.root.count should be ( 1 )

        order.buildQual( _.Order.withName( "OrderId" ) eq 10249 )
                        .readOnly
                        .activate()
        order.Order.OrderId.toString should be ("10249")
        order.root.count should be ( 1 )
    }
}