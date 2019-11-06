package com.quinsoft.northwind.scala

import org.junit.Assert._
import org.junit.Before
import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit

import com.quinsoft.zeidon.scala.Implicits._
import com.quinsoft.zeidon.standardoe.JavaObjectEngine
import com.quinsoft.zeidon.scala.View
import com.quinsoft.zeidon.scala.DynamicTask
import com.quinsoft.zeidon.objectdefinition.KeyValidator

class TestNorthwind extends AssertionsForJUnit {

    val oe = JavaObjectEngine.getInstance()
    var task = oe.createScalaTask("northwind")

    @Before
    def initialize() {
//        oe.startBrowser
    }

    @Test
    def testLoadFromJson() {
        val order = task.deserializeOi().fromFile("testdata/northwind/data/order1.json").unpickle
        assertTrue( order.Employee.exists )
        assertTrue( order.Customer.exists )
        assertTrue( order.Shipper.exists )
        order.logObjectInstance
    }

    @Test
    def testCreateDeleteEntity() {
        val order = task.using( "Order" ).empty()
        order.Order.create()
        order.Order.OrderDate = "NOW"

        val customer = task.using( "Customer" ).activate( _.Customer.CustomerId = "BLAUS" )
        order.Customer include customer.Customer

        val empl = task.using( "Employee" ).activate( _.Employee.EmployeeId = 1 )
        order.Employee include empl.Employee

        val shipper = task.using( "Shipper" ).activate( _.Shipper.ShipperId = 1 )
        order.Shipper include shipper.Shipper

        order.commit()

        order.Order.delete()
        order.commit()
    }


    @Test
    def testKeyValidator() {
        val order = task.deserializeOi().fromFile("testdata/northwind/data/order-with-key-tampering.json").unpickle
        val validator = new KeyValidator( order )
        validator.validate()
    }

    @Test
    def testSerializingHiddenEntities() {
        val order = new View( task ) basedOn "Order"
        order.activateWhere( _.Order.OrderId = 10250 )
        assertTrue( order.Order.exists )
        order.OrderDetail.deleteEntity()
        assertEquals( 2, order.OrderDetail.count( false ) )
        assertEquals( 3, order.OrderDetail.count( true ) )

        order.serializeOi.asJson().withIncremental().toTempDir( "testDelete.json" )
        val order2 = task.deserializeOi().fromTempDir("testDelete.json").unpickle
        assertEquals( 2, order2.OrderDetail.count( false ) )
        assertEquals( 3, order2.OrderDetail.count( true ) )

        order.serializeOi.asXml().withIncremental().toTempDir( "testDelete.xml" )
        val order3 = task.deserializeOi().fromTempDir("testDelete.xml").unpickle
        assertEquals( 2, order3.OrderDetail.count( false ) )
        assertEquals( 3, order3.OrderDetail.count( true ) )

/* TODO: We don't currently handle hidden entities in .por files.
        order.serializeOi.withIncremental().toTempDir( "testDelete.por" )
        val order4 = task.deserializeOi().fromTempDir("testDelete.por").unpickle
        assertEquals( 2, order4.OrderDetail.count( false ) )
        assertEquals( 3, order4.OrderDetail.count( true ) )
*/
    }
}
