---
title: Quick Walk-through of Zeidon application
layout: default
---

## Northwind application

This page will take you through a quick walk-through of creating a Zeidon application.  The application is a sample using Microsoft's Northwind DB as the backing store.  You can find the final Northwind application written in Zeidon here.

The structure of the application is creating using the Zeidon Tools which can be found here.  This walk-through skips over some of the mundane tasks but you can find a complete tutorial for creating the Northwind application here.

### Create the ER Model

All Zeidon applications start by creating an [ER Model](https://en.wikipedia.org/wiki/Entity%E2%80%93relationship_model) using Zeidon's ER Diagram (ERD) tool.  Zeidon's ERD helps you create a standard ER model with entities, attributes, and relationships.  The Northwind ER model looks like this:

![NorthwindERM](images/walkthrough/NorthwindERM.png?raw=true)

### Create Logical Object Definition (LOD)

Zeidon access is handled using LODs.  A LOD is an object that groups related entities from the ERM into a hierarchical object.  At runtime the data is loaded into memory as an Object Instance which is based on the structure defined in the LOD.  The LOD for working with Northwind orders looks like this:

![OrderLod](images/walkthrough/OrderLod.png?raw=true)

Don't worry about the different colors for the entities, they will be explained later.  The structure of the LOD is a tree; when the data is loaded from the DB the root ("Order") is loaded first.  Then, for each order loaded, all of the child entities (e.g. OrderDetail and Customer) are loaded.  Thus all the data that is associeated with an order is loaded into a single object.

The LOD for changing product data looks like this:

![NorthwindERM](images/walkthrough/ProductLod.png?raw=true)

Note that an entity from the ERM ("Product" in this case) can appear in multiple LODs.  In most cases entities will be included in many LODs.

### Load Data From the DB into an Object Instance.

Loading data from the DB is called an "activate" in Zeidon.  This will create an instantiation of a LOD called an "object instance" (OI).  Access to the data in an OI is through a view.  The code for doing all of this is written in a Scala DSL.

To load the data for order # 10250 you would use the following code:

    val order = View( task ) basedOn "Order"
    order activateWhere( _.Order.OrderId = 10250 )

The first line create an empty View that is based on the LOD "Order" shown above.  The second line loads all the data from the entities (i.e. SQL tables) specified in the LOD.  The qualification is specified as "_.Order.OrderId = 10250" which means only load the Orders with OrderId = 10250, which should only be one.  Since the data in a LOD is instantiated as a tree it can easily be represented as JSON.  Here is the data loaded from the above activate:

    {
      "Order" : [ {
        "OrderId" : "10250",
        "OrderDate" : "1996-07-08",
        "ShippedDate" : "1996-07-12",
        "RequiredDate" : "1996-08-05",
        "Freight" : "65.8",
        "ShipName" : "Hanari Carnes",
        "ShipAddress" : "Rua do Paço, 67",
        "ShipCity" : "Rio de Janeiro",
        "ShipRegion" : "RJ",
        "ShipPostalCode" : "05454-876",
        "ShipCountry" : "Brazil",
        "OrderDetail" : [ {
          "UnitPrice" : "7.7",
          "Quantity" : "10",
          "Discount" : "0",
          "Product" : {
            "ProductId" : "41",
            "ProductName" : "Jack's New England Clam Chowder",
            "ReorderLevel" : "10",
            "QuantityPerUnit" : "12 - 12 oz cans",
            "Discontinued" : "false",
            "UnitPrice" : "9.6",
            "UnitsInStock" : "85",
            "UnitsOnOrder" : "0"
          }
        }, {
          "UnitPrice" : "42.4",
          "Quantity" : "35",
          "Discount" : "0.2",
          "Product" : {
            "ProductId" : "51",
            "ProductName" : "Manjimup Dried Apples",
            "ReorderLevel" : "10",
            "QuantityPerUnit" : "50 - 300 g pkgs.",
            "Discontinued" : "false",
            "UnitPrice" : "53",
            "UnitsInStock" : "20",
            "UnitsOnOrder" : "0"
          }
        }, {
          "UnitPrice" : "16.8",
          "Quantity" : "15",
          "Discount" : "0.2",
          "Product" : {
            "ProductId" : "65",
            "ProductName" : "Louisiana Fiery Hot Pepper Sauce",
            "ReorderLevel" : "0",
            "QuantityPerUnit" : "32 - 8 oz bottles",
            "Discontinued" : "false",
            "UnitPrice" : "21",
            "UnitsInStock" : "76",
            "UnitsOnOrder" : "0"
          }
        } ],
        "Customer" : {
          "CustomerId" : "HANAR",
          "CompanyName" : "Hanari Carnes",
          "ContactName" : "Mario Pontes",
          "ContactTitle" : "Accounting Manager",
          "Address" : "Rua do Paço, 67",
          "City" : "Rio de Janeiro",
          "Region" : "RJ",
          "PostalCode" : "05454-876",
          "Country" : "Brazil",
          "Phone" : "(21) 555-0091",
          "Fax" : "(21) 555-8765"
        },
        "Employee" : {
          "EmployeeId" : "4",
          "LastName" : "Peacock",
          "FirstName" : "Margaret",
          "Title" : "Sales Representative",
          "TitleOfCourtesy" : "Mrs.",
          "BirthDate" : "1937-09-19",
          "HireDate" : "1993-05-03",
          "HomePhone" : "(206) 555-8122",
          "Address" : "4110 Old Redmond Rd.",
          "City" : "Redmond",
          "Region" : "WA",
          "PostalCode" : "98052",
          "Country" : "USA",
          "Extension" : "5176",
          "Notes" : "Margaret holds a BA in English literature from Concordia College (1958) and an MA from the American Institute of Culinary Arts (1966).  She was assigned to the London office temporarily from July through November 1992.",
          "Photo" : "Blob data: 12121 bytes (����\u0000\u0010JFIF)",
          "PhotoPath" : "http://accweb/emmployees/peacock.bmp"
        },
        "Shipper" : {
          "ShipperId" : "2",
          "CompanyName" : "United Package",
          "Phone" : "(503) 555-3199"
        }
      } ]
    }

Note that only one order was loaded (ID: 10250) and all of its associated data.  

### Manipulating the Data From Code

The data in an OI is accessed through a view using "view.EntityName.AttributeName" notation as specified in the LOD.

        println( "Order ShipName = " + order.Order.ShipName )
        println( "Employee Name = " + order.Employee.FirstName + " " + order.Employee.LastName )

Results in:

    Order ShipName = Hanari Carnes
    Employee Name = Margaret Peacock

A view is a set of cursors, one cursor for each entity specified in the LOD.  Cursors are used to loop through entities that have multiple instances using the "each" method..  The view.EntityName.AttributeName notation uses the entity cursor to determine which entity instance is the current instance.  The following code prints out the products for an order:

        println( "Products in order # " + order.Order.OrderId )
        order.OrderDetail.each {
            println( "   " + order.Product.ProductName + ": " + order.OrderDetail.Quantity )
        }

This results in:

    Products in order # 10250
       Jack's New England Clam Chowder: 10
       Manjimup Dried Apples: 35
       Louisiana Fiery Hot Pepper Sauce: 15

### Updating the Data and Committing It
Updating data in an OI uses the same "view.EntityName.AttributeName" notation.  Changing the quantity could be like:

        order.OrderDetail.Quantity = order.OrderDetail.Quantity + 1
        println( "New quantity = " + order.OrderDetail.Quantity )
        order.commit()

The output from running this is

    New quantity = 16

Saving the change to the DB uses the commit() method.  All changes made to the OI since it was activated are then written to the DB.  Unlike most ORMs Zeidon uses a software-based transaction instead of a DB transaction; it does not hold any DB connections open during the life of the OI.  Instead Zeidon closes the DB connection immediately after the activate and uses a new (or pooled) connection during the commit.  Zeidon keeps track of the changes and performs the SQL commands.  The above "commit()" results in the following SQL:

    UPDATE orderdetails
           SET    QUANTITY = 16
    WHERE orderdetails.PRODUCTID = 65 AND orderdetails.ORDERID = 10250;

### Creating an Entity Instance
To create an entity, call the create() method on the entity cursor and set the attributes.

        order.OrderDetail create()
        order.OrderDetail.UnitPrice = 10.0
        order.OrderDetail.Quantity = 5
        order.OrderDetail.Discount = 0.0

To save this, call commit() again.  However, with just this change the commit will throw an error:

    order commit()

    Subobject Validation exception.
    1) com.quinsoft.zeidon.RequiredEntityMissingException: Required child entity has no instances.
    LodDef  = Northwind.Order
    EntityDef  = Northwind.Order.Product
	    at com.quinsoft.zeidon.standardoe.CommitMultiplOIs.validateCommit(CommitMultiplOIs.java:178)
	    at com.quinsoft.zeidon.standardoe.CommitMultiplOIs.commit(CommitMultiplOIs.java:402)
	    at com.quinsoft.zeidon.standardoe.TaskImpl.commitMultipleOis(TaskImpl.java:335)
	    at com.quinsoft.zeidon.standardoe.TaskImpl.commitMultipleOis(TaskImpl.java:316)
	    at com.quinsoft.zeidon.standardoe.ViewImpl.commit(ViewImpl.java:359)
	    at com.quinsoft.northwind.SampleActivates.test(SampleActivates.scala:128)
	    at com.quinsoft.northwind.SampleActivates$.main(SampleActivates.scala:144)
	    at com.quinsoft.northwind.SampleActivates.main(SampleActivates.scala)

This error is telling us that the OI is missing the required entity Product.  This error is thrown because in the ERM we specified that the minimum number of Products that an OrderDetail entity can have is one and we haven't specified a Product for the new OrderDetail.  We could try creating a new Product like this:

        order.OrderDetail create()
        order.OrderDetail.UnitPrice = 10.0
        order.OrderDetail.Quantity = 5
        order.OrderDetail.Discount = 0.0
        
        order.Product create()
        order.Product.ProductId = 48
        order commit()

This, however, throws a different error when we call the create():

    Entity is not flagged for create.
    EntityDef  = Northwind.Order.Product
	    at com.quinsoft.zeidon.standardoe.EntityCursorImpl.createEntity(EntityCursorImpl.java:370)
	    at com.quinsoft.zeidon.standardoe.EntityCursorImpl.createEntity(EntityCursorImpl.java:59)
	    at com.quinsoft.zeidon.scala.EntityCursor.create(EntityCursor.scala:64)
	    at com.quinsoft.northwind.SampleActivates.test(SampleActivates.scala:128)
	    at com.quinsoft.northwind.SampleActivates$.main(SampleActivates.scala:147)
	    at com.quinsoft.northwind.SampleActivates.main(SampleActivates.scala)

Let's look again at our Order LOD:
![OrderLod](images/walkthrough/OrderLod2.png?raw=true)

Order and OrderDetail are green, indicating that new entities can be created at run time.  However, Product (and Customer) are yellow which means that when using the Order LOD we can't create Products, we can only create a relationship between OrderDetail and Product.  To see all the permissions, double-click on Product to bring up the entity details for Product:

![OrderLod](images/walkthrough/ProductDetails.png?raw=true)

Note that run-time permissions Create, Delete, and Update are turned off.  However, Include and Exclude are turned on, which is how Zeidon creates relationships.

### Creating Relationships With Include
Creating a relationship between two entities is called "including" an entity.  To perform an include we first need to activate the entity we want to include.  This is where the Product LOD comes in:

        val product = View( task ) basedOn "Product"
        product.activateWhere( _.Product.ProductId = 48 )
        
        order.Product include product.Product
        
        order.commit()

This will activate product 48 from the DB, include the Product entity from the product view into order, and then commit.  The SQL that gets executed to perform the commit is:

    INSERT INTO orderdetails ( UNITPRICE, QUANTITY, DISCOUNT, PRODUCTID, ORDERID  ) VALUES 
           ( 10.0, 5, 0.0, 48, 10250 );

### Using a Cache for Product
The above code works fine but has a potential issue.  If we created a lot of OrderDetails we'd have to load each Product separately, even if we're using the same Product multiple times.  Instead we'd like to load the Products into a cache and chose the correct product.  Zeidon makes this easy with more advanced qualification:


One important thing to note is that the code uses the same entity and attribute names as specified in the ERM.  This allows the business logic to be close to the relational model.



