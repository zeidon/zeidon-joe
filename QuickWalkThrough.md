---
title: Quick Walk-through of Zeidon application
layout: default
---

# Walk-through of Zeidon Application Using Northwind

This page will take you through a quick walk-through of a Zeidon application based on Microsoft's [Northwind DB](https://northwinddatabase.codeplex.com).  You can find the final Northwind application written in Zeidon here (to be added).

The structure of the application is created using the Zeidon Tools which can be found here (to be added).  This walk-through skips over some of the mundane tasks but you can find a complete tutorial for creating the Northwind application here (to be added).

## Create the ER Model

All Zeidon applications start by creating an [ER Model](https://en.wikipedia.org/wiki/Entity%E2%80%93relationship_model) using Zeidon's ER Diagram tool.  Zeidon's ERD helps you create a typical ER model with entities, attributes, and relationships.  The Northwind ER model looks like this:

![NorthwindERM](images/walkthrough/NorthwindERM.png?raw=true)

## Create Logical Object Definition (LOD)

All data access and manipulation is controlled using Zeidon LODs.  The LOD defines an object that groups related entities from the ERM into an hierarchical tree.  At runtime the data is loaded into memory as an Object Instance which is based on the structure defined in the LOD.  Creating a LOD is simple and fast; start with an ERM entity as the root and then add related entities as children.  The LOD for working with Northwind orders looks like this:

![OrderLod](images/walkthrough/OrderLod.png?raw=true)

Don't worry about the different colors for the entities for they will be explained later.  The structure of the LOD is a tree; when the data is loaded from the DB the root ("Order") is loaded first.  Then, for each order loaded, all of the child entities (e.g. OrderDetail and Customer) are loaded.  Thus all the data that is associated with an order is loaded into a single logical object.

The LOD for working with product data looks like this:

![NorthwindERM](images/walkthrough/ProductLod.png?raw=true)

Note that the Product entity is part of both LODs.  In most cases entities will be included in multiple LODs.  This is an important point with Zeidon: most ORMs associate one object with one table in the DB.  One problem with this solution is that it reduces the context for which the table is used.  With Zeidon an ER entity can appear in multiple LODs which preserves its context.

## Load Data From the DB into an Object Instance.

Loading data from the DB is called an "activate" in Zeidon.  This will instantiate a LOD using data from the DB into an "object instance" (OI).  Access to the data in an OI is through a View.  The code for doing all of this is written in a Scala DSL.

To load the data for order # 10250 you would use the following code:

```scala
val order = View( task ) basedOn "Order"
order activateWhere( _.Order.OrderId = 10250 )
```

The first line creates an empty View that is based on the LOD "Order" shown above.  The second line loads all the data from the entities (i.e. SQL tables) that make up the LOD.  The qualification is specified as `_.Order.OrderId = 10250` which means "load the Orders with OrderId = 10250" which should only be one.  Since the data in a LOD is instantiated as a tree it can easily be serialized as JSON (as well as XML).  The following code writes the OI as JSON to a file:

```scala
order.serializeOi.toTempDir( "order.json" )
```

The resulting JSON looks like this:

```json
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
```

As you can see only one order was loaded (ID: 10250) and all of its associated data.  

## Manipulating the Data From Code

The data in an OI is accessed through a view using `view.EntityName.AttributeName` notation.  The entity and attribute names are the names specified in the LOD.  For example, to print out the ShipName from the Order entity and First/LastName from the Employee entity use:

```scala
println( "Order ShipName = " + order.Order.ShipName )
println( "Employee Name = " + order.Employee.FirstName + " " + order.Employee.LastName )
```

Results in:

```console
Order ShipName = Hanari Carnes
Employee Name = Margaret Peacock
```

A view is a set of cursors, one cursor for each entity specified in the LOD.  Cursors are used to loop through entities that have multiple instances using the "each" method..  The `view.EntityName.AttributeName` notation uses the entity cursor to determine which entity instance is the current instance.  The following code prints out the products for an order:

```scala
println( "Products in order # " + order.Order.OrderId )
order.OrderDetail.each {
    println( "   " + order.Product.ProductName + ": " + order.OrderDetail.Quantity )
}
```

This results in:

```console
Products in order # 10250
   Jack's New England Clam Chowder: 10
   Manjimup Dried Apples: 35
   Louisiana Fiery Hot Pepper Sauce: 15
```

One thing to note is that changing a cursor resets all child cursors.  The above example shows that the Product cursor is changed while iterating through the OrderDetail cursor.

## Updating the Data and Committing to the DB
Updating data in an OI uses the same `view.EntityName.AttributeName` notation.  Changing the quantity could be like:

```scala
order.OrderDetail.Quantity = order.OrderDetail.Quantity + 1
println( "New quantity = " + order.OrderDetail.Quantity )
order.commit()
```

The output from running this is

```console
New quantity = 16
```

Saving the change to the DB uses the `commit()` method.  All changes made to the OI since it was activated are then written to the DB.  Unlike most ORMs Zeidon uses software-based transactions instead of a DB transaction and does not hold any DB connections open during the life of the OI.  Instead Zeidon closes the DB connection immediately after the activate and uses a new (or pooled) connection during the commit.  Zeidon keeps track of the changes and performs the SQL commands.  The above `commit()` results in the following SQL:

```sql
UPDATE orderdetails
       SET    QUANTITY = 16
WHERE orderdetails.PRODUCTID = 65 AND orderdetails.ORDERID = 10250;
```

## Creating an Entity Instance
To create an entity, call the `create()` method on the entity cursor and set the attributes.

```scala
order.OrderDetail create()
order.OrderDetail.UnitPrice = 10.0
order.OrderDetail.Quantity = 5
order.OrderDetail.Discount = 0.0
```

To save this, call `commit()` again.  However, with just this change the commit will trigger an error:

```scala
order commit()
```

Throws

```console
Subobject Validation exception.
1) com.quinsoft.zeidon.RequiredEntityMissingException: Required child entity has no instances.
LodDef  = Northwind.Order
EntityDef  = Northwind.Order.Product
    at com.quinsoft.zeidon.standardoe.CommitMultiplOIs.validateCommit(CommitMultiplOIs.java:178)
    at com.quinsoft.zeidon.standardoe.CommitMultiplOIs.commit(CommitMultiplOIs.java:402)
```

This error is telling us that the OI is missing the required entity Product.  This error is thrown because in the ERM we specified that the minimum number of Products for an OrderDetail is one and we haven't specified a Product for the new OrderDetail.  We could try creating a new Product like this:

```scala
order.OrderDetail create()
order.OrderDetail.UnitPrice = 10.0
order.OrderDetail.Quantity = 5
order.OrderDetail.Discount = 0.0

order.Product create()
order.Product.ProductId = 48
order commit()
```

This, however, throws a different error when we call the `create()`:

```console
Entity is not flagged for create.
EntityDef = Northwind.Order.Product
    at com.quinsoft.zeidon.standardoe.EntityCursorImpl.createEntity(EntityCursorImpl.java:370)
    at com.quinsoft.zeidon.standardoe.EntityCursorImpl.createEntity(EntityCursorImpl.java:59)
    at com.quinsoft.zeidon.scala.EntityCursor.create(EntityCursor.scala:64)
    ...
```

Let's look again at our Order LOD:

![OrderLod](images/walkthrough/OrderLod2.png?raw=true)

Order and OrderDetail are green, indicating that new entities can be created at run time.  However, Product (and Customer) are yellow which means that when using the Order LOD we can't create Products, we can only create a relationship between OrderDetail and Product.  To see all the permissions, double-click on Product to bring up the entity details for Product:

![OrderLod](images/walkthrough/ProductDetails.png?raw=true)

Note that run-time permissions Create, Delete, and Update are turned off.  However, Include and Exclude are turned on, which is how Zeidon creates relationships.

## Creating Relationships With Include
Creating a relationship between two entities is called "including" an entity.  To perform an include we first need to activate the entity we want to include.  This is where the Product LOD comes in:

```scala
val product = View( task ) basedOn "Product"
product.activateWhere( _.Product.ProductId = 48 )

order.Product include product.Product

order.commit()
```

This will activate product 48 from the DB, include the Product entity from the product view into Order, and then commit.  The SQL that gets executed to perform the commit is:

```sql
INSERT INTO orderdetails ( UNITPRICE, QUANTITY, DISCOUNT, PRODUCTID, ORDERID  ) VALUES 
       ( 10.0, 5, 0.0, 48, 10250 );
```

## Using a Cache for Product
The above code works fine but it could be better.  If we creat a lot of OrderDetails we'll have to activate each Product separately, even if we use the same Product multiple times.  Instead we'd like to load the Products into a cache and chose the correct product.  Zeidon makes this easy with more advanced qualification:

```scala
val products = View( task ) basedOn "Product"
products.buildQual( _.Product.Discontinued = false )
        .cachedAs( "ProductsList" )
        .activate()

products.Product setFirst( _.ProductId == 48)
order.Product include products.Product

order.commit()
```

The first four lines activate all the products that haven't been discontinued.  The first time this code is run it will load the products from the DB and then cache the results using the name "ProductsList".  Thereafter the product list in the cache will be used instead of loading the data from the DB.

The next line sets the cursor for the Product entity in the products view to point to the product with ProductId = 48.  That product is then included into the order OI.

## Deleting Entities

Call the `delete()` method on an entity to delete an entity instance:

```scala
order.OrderDetail setFirst()
order.OrderDetail delete()
```

Calling `delete()` will delete the first entity and remove its children from the OI.  If the above line is called on the first OrderDetail in our example, the OI now looks like this (there are now only two OrderDetails):

```json
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
```

If the OI is committed the following SQL is executed:

```sql
DELETE 
FROM  orderdetails
WHERE orderdetails.PRODUCTID = 65 AND orderdetails.ORDERID = 10250;
```

Note that the commit didn't remove anything from the Products table.  Why not?  

## Parent Delete Behavior

Take another look at the properties for the Product entity in the Order LOD:

![OrderLod](images/walkthrough/ProductDetails.png?raw=true)

Under "Parent Delete Behavior" is says "Exclude".  This indicates that when the parent entity (OrderDetail in this example) is deleted the Product entity will be excluded; i.e. instead of deleting the record from the DB the relationship between Product and OrderDetail will be removed, leaving Product intact.  This is a typical configuration in LODs; if an entity can only be included then its parent delete behavior is exclude.

Let's contrast that with what happens if we delete the root entity, Order:

```scala
        order.Order delete()
        order commit()
```

Results in the following SQL:

```sql
DELETE 
FROM  orderdetails
WHERE orderdetails.PRODUCTID = 65 AND orderdetails.ORDERID = 10250;

DELETE 
FROM  orderdetails
WHERE orderdetails.PRODUCTID = 51 AND orderdetails.ORDERID = 10250;

DELETE 
FROM  orderdetails
WHERE orderdetails.PRODUCTID = 41 AND orderdetails.ORDERID = 10250;

DELETE 
FROM  orders
WHERE orders.ORDERID = 10250;
```

As you would expect, the OrderDetail records are removed along with the order.  Like Product, the Customer, Employee, and Shipper entities have a parent delete behavior of "exclude" which prevents them from being deleted.

Parent delete behavior is how a LOD can easily control cascading deletes when a parent entity is deleted.

## More Complex Activation Qualification
A LOD is designed directly from the ERM and thus it knows about the relationships between the entities.  This makes it much easier to create complex qualification because the user doesn't need to specify how to join the tables.

For example, the following query loads all orders that contain a discontinued product:

```scala
val orders = View( task ) basedOn "Order"
orders.activateWhere( _.Product.Discontinued = true )
```

Since the Order/OrderDetail/Product path is part of the LOD Zeidon knows what tables to join.  This will generate the following SQL to load the orders:

```sql
SELECT orders.ORDERID, orders.ORDERDATE, orders.SHIPPEDDATE, orders.REQUIREDDATE, orders.FREIGHT, orders.SHIPNAME,
       orders.SHIPADDRESS, orders.SHIPCITY, orders.SHIPREGION, orders.SHIPPOSTALCODE, orders.SHIPCOUNTRY,
       orders.EMPLOYEEID, orders.CUSTOMERID, orders.SHIPPERID
FROM orders JOIN
     orderdetails ON orderdetails.ORDERID = orders.ORDERID JOIN
     products ON products.PRODUCTID = orderdetails.PRODUCTID
WHERE products.DISCONTINUED = true;
```

Note that by default qualification is for the root of the LOD.  The above activate will load only orders that contain discontinued products but it will load all the products for those orders.  If you want to load just discontinued products you need to add a "restricting" filter:

```scala
orders.buildQual( _.Product.Discontinued = true )
      .restrict( _.OrderDetail ).to( _.Product.Discontinued = true )
      .activate()
```

The `.restrict()` filter specifies that only OrderDetails with discontinued products should be loaded.  The SQL for loading the orders is the same as above but when loading OrderDetail the SQL is:

```sql
SELECT orderdetails.UNITPRICE, orderdetails.QUANTITY, orderdetails.DISCOUNT, orderdetails.PRODUCTID,
       orderdetails.ORDERID
FROM  orderdetails JOIN
       products ON products.PRODUCTID = orderdetails.PRODUCTID
WHERE (orderdetails.ORDERID = 10248 AND ( products.DISCONTINUED = true));
```

Qualification can reference multiple entities in the LOD and any of the attributes, like this:

```scala
orders.buildQual( _.Order.OrderDate > "2015-01-01" )
      .and( _.Employee.LastName = "Smith" )
      .and( _.Product.Discontinued = true )
      .activate()
```

Zeidon generates:

```sql
SELECT orders.ORDERID, orders.ORDERDATE, orders.SHIPPEDDATE, orders.REQUIREDDATE, orders.FREIGHT, orders.SHIPNAME,
       orders.SHIPADDRESS, orders.SHIPCITY, orders.SHIPREGION, orders.SHIPPOSTALCODE, orders.SHIPCOUNTRY,
       orders.EMPLOYEEID, orders.CUSTOMERID, orders.SHIPPERID
FROM  orders JOIN
       employees ON employees.EMPLOYEEID = orders.EMPLOYEEID JOIN
       orderdetails ON orderdetails.ORDERID = orders.ORDERID JOIN
       products ON products.PRODUCTID = orderdetails.PRODUCTID
WHERE orders.ORDERDATE > '2015-01-01 00:00:00.000' AND employees.LASTNAME = 'Smith' AND products.DISCONTINUED = 1;
```

## What Makes a LOD an "Object"?


One important thing to note is that the code uses the same entity and attribute names as specified in the ERM.  This allows the business logic to be close to the relational model.



