/**
 *
 */
package com.quinsoft.northwind

import com.quinsoft.zeidon.standardoe.JavaObjectEngine
import com.quinsoft.zeidon.scala.ZeidonOperations
import com.quinsoft.zeidon.Task
import com.quinsoft.zeidon.scala.View
import com.quinsoft.zeidon.scala.basedOn
import com.quinsoft.zeidon.ZeidonException

/**
 * This contains all the code used in the Zeidon Quick Walk Through:
 *     http://deegc.github.io/zeidon-joe/QuickWalkThrough.html
 */
class WalkThroughSamples( var task: Task ) extends ZeidonOperations {

    def simpleActivate() {
        val myOrder = View( task ) basedOn "Order"
        myOrder activateWhere( _.Order.OrderId = 10250 )        
        myOrder.serializeOi.toTempDir( "order.json" )
        
        println( "Order ShipName = " + myOrder.Order.ShipName )
        println( "Employee Name = " + myOrder.Employee.FirstName + " " + myOrder.Employee.LastName )
        
        println( "Products in order # " + myOrder.Order.OrderId )
        myOrder.OrderDetail.each {
            println( "   " + myOrder.Product.ProductName + ": " + myOrder.OrderDetail.Quantity )
        }
    }
    
    def simpleUpdate() {
        val myOrder = View( task ) basedOn "Order"
        myOrder activateWhere( _.Order.OrderId = 10250 )        

        myOrder.OrderDetail.Quantity = myOrder.OrderDetail.Quantity + 1
        println( "New quantity = " + myOrder.OrderDetail.Quantity )
        myOrder.commit()
    }
    
    def errorUpdate1() {
        try {
            val myOrder = View( task ) basedOn "Order"
            myOrder activateWhere( _.Order.OrderId = 10250 )        
    
            myOrder.OrderDetail create()
            myOrder.OrderDetail.UnitPrice = 10.0
            myOrder.OrderDetail.Quantity = 5
            myOrder.OrderDetail.Discount = 0.0
            
            myOrder.commit() // Throws exception.
        }
        catch {
            case e: Exception => e.printStackTrace()
        }
    }
    
    def errorUpdate2() {
        try {
            val myOrder = View( task ) basedOn "Order"
            myOrder activateWhere( _.Order.OrderId = 10250 )        
    
            myOrder.OrderDetail create()
            myOrder.OrderDetail.UnitPrice = 10.0
            myOrder.OrderDetail.Quantity = 5
            myOrder.OrderDetail.Discount = 0.0
            
            myOrder.Product create() // Throws exception.
            myOrder.Product.ProductId = 48
            myOrder commit()
        }
        catch {
            case e: Exception => e.printStackTrace()
        }
    }
    
    def includeProduct() {
        val myOrder = View( task ) basedOn "Order"
        myOrder activateWhere( _.Order.OrderId = 10250 )        

        myOrder.OrderDetail create()
        myOrder.OrderDetail.UnitPrice = 10.0
        myOrder.OrderDetail.Quantity = 5
        myOrder.OrderDetail.Discount = 0.0
        
        val product = View( task ) basedOn "Product"
        product.activateWhere( _.Product.ProductId = 48 )
        
        myOrder.Product include product.Product
        
        myOrder.commit()        
    }
    
    def includeProductWithCache() {
        val myOrder = View( task ) basedOn "Order"
        myOrder activateWhere( _.Order.OrderId = 10250 )        

        myOrder.OrderDetail create()
        myOrder.OrderDetail.UnitPrice = 10.0
        myOrder.OrderDetail.Quantity = 5
        myOrder.OrderDetail.Discount = 0.0
        
        val products = View( task ) basedOn "Product"
        products.buildQual( _.Product.Discontinued = false )
                .cachedAs( "ProductsList" )
                .activate()
        
        products.Product setFirst( _.ProductId == 48)
        myOrder.Product include products.Product
        
        myOrder.commit()        
    }

    def deleteEntity() {
        val myOrder = View( task ) basedOn "Order"
        myOrder activateWhere( _.Order.OrderId = 10250 )
        
        myOrder.OrderDetail setFirst()
        myOrder.OrderDetail delete()
        
        myOrder commit()
    }
    
    def deleteParentEntity() {
        val myOrder = View( task ) basedOn "Order"
        myOrder activateWhere( _.Order.OrderId = 10250 )
        
        myOrder.Order delete()
        
        myOrder commit()
    }
    
    def complexQualifications() {
        val orders = View( task ) basedOn "Order"
        orders.activateWhere( _.Product.Discontinued = true )        
        
        orders.buildQual( _.Product.Discontinued = true )
              .restrict( _.OrderDetail ).to( _.Product.Discontinued = true )
              .asynchronous
              .activate()
              
        orders.buildQual( _.Order.OrderDate > "2015-01-01" )
              .and( _.Employee.LastName = "Smith" )
              .and( _.Product.Discontinued = true )
              .activate()
    }
    
    def loadShippers() : View @basedOn( "Shipper" ) = {
        val shippers = View( task ) basedOn "Shipper"
        shippers.activateAll()
    }
    
    def loadEmployees() = {
        val employees = View( task ) basedOn "Employee"    
        employees.buildQual.cachedAs( "AllEmployees" ).activate()
    }
    
    def loadCustomers() = {
        val customers = View ( task ) basedOn "Customer"
        customers.buildQual.cachedAs( "AllCustomers" ).activate()
    }

    def createOrder( productId: Int, 
                     quantity: Int,
                     customerId: String,
                     employeeId: Int,
                     shipperId: Int ) = {
        
        val shippers = loadShippers()
        val employees = loadEmployees()
        val customers = loadCustomers()
        
        val newOrder = View( task ) basedOn "Order"
        newOrder activateEmpty()
        newOrder.Order create()
        newOrder.Order.ShipName = "Joe Smith"
        newOrder.Order.ShipAddress = "1 Main St"
        newOrder.Order.ShipPostalCode = "01234"
        
        val product = View( task ) basedOn "Product"
        try {
            // This will activate the OI with pessimistic locking.
            product.activateWhere( _.Product.ProductId = productId )
            
            // Make sure we have enough units in stock.
            if ( product.Product.UnitsInStock < quantity )
                throw new ZeidonException( "Not enough units in stock to buy # %s", quantity.toString )

            product.Product.UnitsInStock -= quantity
            
            newOrder.OrderDetail create()
            newOrder.OrderDetail.UnitPrice = product.Product.UnitPrice
            newOrder.OrderDetail.Quantity = quantity
            newOrder.OrderDetail.Discount = 0.0
            newOrder.Product include product.Product
            
            customers.Customer setFirst( _.CustomerId == customerId )
            newOrder.Customer include customers.Customer
            
            employees.Employee setFirst( _.EmployeeId == employeeId )
            newOrder.Employee include employees.Employee
            
            shippers.Shipper set( _.ShipperId = shipperId )
            newOrder.Shipper include shippers.Shipper
            
            task.commitMultipleOis( newOrder, product )
            
            println( "New Order ID = " + newOrder.Order.OrderId )
        }
        finally {
            // This will remove the pessimistic lock. 
            product drop()
        }
    }
}

object WalkThroughSamples {

    def main(args: Array[String]) {
        // Instantiate JOE using standard configuration.  This requires zeidon.ini
        // in the classpath.
        val objectEngine = JavaObjectEngine.getInstance
        objectEngine.startBrowser()

        val task = objectEngine.createTask("Northwind")
        task.log.info("Loaded task")
        
        val testLogic = new WalkThroughSamples( task )
        testLogic.complexQualifications()
    }
  
}