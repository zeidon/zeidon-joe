package com.quinsoft.northwind.scala

import org.junit.Assert._
import org.junit.Before
import org.junit.Test
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.funsuite.AnyFunSuite

import com.quinsoft.zeidon.scala.Implicits._
import com.quinsoft.zeidon.standardoe.JavaObjectEngine
import com.quinsoft.zeidon.scala.View
import com.quinsoft.zeidon.objectdefinition.KeyValidator
import com.quinsoft.zeidon.objectdefinition.KeyValidator.KeyValidationError
import com.quinsoft.zeidon.ZeidonException
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class TestNorthwind extends AnyFunSuite {

    val oe = JavaObjectEngine.getInstance()
    var task = oe.createScalaTask("northwind")

//    oe.startBrowser

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
        val order = task.Order.empty()
        order.Order.create()
        order.Order.OrderDate = "NOW"

        val customer = task.Customer.activate( _.Customer.CustomerId = "BLAUS" )
        order.Customer include customer.Customer

        assertFalse( "Customer shouldn't be updated", customer.Customer.isUpdated )
        assertFalse( "Customer shouldn't be updated", order.Customer.isUpdated )

        val companyName: String = customer.Customer.CompanyName
        customer.Customer.CompanyName = "New name"
        assertEquals( order.Customer.CompanyName, customer.Customer.CompanyName )

        assertTrue( "Customer should be updated", customer.Customer.isUpdated )
        assertTrue( "Customer should be updated", order.Customer.isUpdated )

        val empl = task.Employee.activate( _.Employee.EmployeeId = 1 )
        order.Employee include empl.Employee

        val shipper = task.Shipper.activate( _.Shipper.ShipperId = 1 )
        order.Shipper include shipper.Shipper

        order.commit()

        val customer2 = task.Customer.activate( _.Customer.CustomerId = "BLAUS" )
        assertEquals( companyName, customer2.Customer.CompanyName.getString() )

        order.Order.delete()
        order.commit()
    }

    @Test
    def testCommitWithTamperedKeys() {
        // Verifies that a serialized OI that attempts to update a key will trigger an error.
        var order = task.deserializeOi().fromFile("testdata/northwind/data/order-with-key-tampering4.json").unpickle
        try {
            order.commit()
            fail()
        } catch {
            case e: ZeidonException => assertTrue( e.getMessage.startsWith( "Attribute is defined as read-only and/or hidden and is not linked to updatable entity.") );
        }
    }

    @Test
    def testKeyValidator() {
        var order = task.deserializeOi().fromFile("testdata/northwind/data/order-with-no-key-tampering.json").unpickle
        var validator = new KeyValidator( order )
        validator.validate()

        try {
            order = task.deserializeOi().fromFile("testdata/northwind/data/order-with-key-tampering2.json").unpickle
            validator = new KeyValidator( order )
            validator.validate()
            fail()
        } catch {
            case e: KeyValidationError => // Expected
        }

        try {
            order = task.deserializeOi().fromFile("testdata/northwind/data/order-with-key-tampering3.json").unpickle
            order.OrderDetail.Quantity = 11
            validator = new KeyValidator( order )
            validator.validate()
            fail()
        } catch {
            case e: KeyValidationError => // Expected
        }
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

    test( "testManyToManyJoins" ) {
        val customerList = task.Customer.all
        assert( customerList.getTotalRootCount() == 93 )
    }
}
