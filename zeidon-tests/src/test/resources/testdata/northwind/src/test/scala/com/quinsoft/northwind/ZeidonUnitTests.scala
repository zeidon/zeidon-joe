/**
 *
 */
package com.quinsoft.northwind

import org.junit.Assert._
import org.junit.Before
import org.junit.Test
import com.quinsoft.zeidon.Blob
import com.quinsoft.zeidon.PessimisticLockingException
import com.quinsoft.zeidon.ZeidonException
import com.quinsoft.zeidon.scala.View
import com.quinsoft.zeidon.standardoe.JavaObjectEngine
import org.apache.commons.io.FileUtils
import java.io.File

/**
 * Unit tests that use Northwind to test Zeidon JOE.
 *
 */
class ZeidonUnitTests {
    val oe = JavaObjectEngine.getInstance()
    var task = oe.createTask("Northwind")


    @Before
    def initialize() {
//        oe.startBrowser
    }

    @Test
    def testActivateWithNull() {
        val order = View( task ) basedOn "Order"
        order.buildQual( _.Product.ProductId = 10 )
            .restricting( _.Product )
                .to( _.Product.ProductName isNotNull )
            .readOnly
            .activate()

        order.serializeOi.withIncremental.toFile("/tmp/orders.json")
    }

    @Test
    def testPessimisticLocks() {
        val product = View( task ) basedOn "Product"
        val productId = 10

        try {
            // Activate the OI with pessimistic locking.
            product.activateWhere( _.Product.ProductId = productId )

            // Make sure we can activate a read-only OI.
            val productReadOnly = View( task ) basedOn "Product"
            productReadOnly.buildQual( _.Product.ProductId = productId )
                           .readOnly
                           .activate()

            // Verify that it's read-only but trying to update it.
            try {
                productReadOnly.Product.ProductName = "New name"
            }
            catch {
                case e: Exception => {
                    val msg = e.getMessage
                    if ( ! msg.contains( "Object Instance is read-only" ) )
                        throw new ZeidonException( "Expected OI to be read-only" )
                }
            }

            // Try activating.  It should fail because OI is locked.
            try {
                productReadOnly.activateWhere( _.Product.ProductId = productId )
            }
            catch {
                case e: PessimisticLockingException => {}
                case e: Exception => {
                    if ( ! e.getMessage.equals( "Object Instance is read-only" ) )
                        throw new ZeidonException( "Expected OI to be read-only" )
                }
            }

            product.drop()
            productReadOnly.activateWhere( _.Product.ProductId = productId )
            productReadOnly.drop()
        }
        finally {
            // This will remove the pessimistic lock.
            product drop()
        }
    }

    @Test
    def testBlobs() {
        val empl = View( task ) basedOn "Employee"
        empl.activateWhere( _.Employee.EmployeeId = 1 )

        import java.nio.file.{Files, Paths}
        val byteArray = Files.readAllBytes(Paths.get("src/test/resources/picture.jpg"))
        empl.Employee.Photo = byteArray
        empl commit()

        val empl2 = View( task ) basedOn "Employee"
        empl2.activateWhere( _.Employee.EmployeeId = 1 )

        assertEquals( empl.Employee.Photo, empl2.Employee.Photo )
        Files.write(Paths.get("target/picture.jpg"), empl2.Employee.Photo.getBlob.getBytes )
        FileUtils.write( new File( "target/picture.txt"), empl2.Employee.Photo.getString() )
    }
}