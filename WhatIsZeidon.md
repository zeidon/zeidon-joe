---
title: What is Zeidon
layout: default
---

# What is Zeidon?

Zeidon is an application framework for developing data-oriented applications.  It has two aspects that give it power:

1. Zeidon abstracts the structure of the ER model and makes it directly accessable from application code.  There is no need to build objects (e.g. Java objects) to retrieve and store the data in memory because Zeidon does this.

2. The abstraction lets the developer create "logical objects".  Logical objects group multiple entities from the ER model into structures that are based on business logic.  Because of the abstraction the structures have many aspects of object-oriented programming.  The entities in the logical object are chosen to perform some business logic, like create an order or update user information.  This allows multiple entities (i.e. SQL tables) to be accessed like a single object instead of a group of related-but-separate objects.

The structure of the logical object is laid out in the Logical Object Definition (lOD) which is the fundamental unit in Zeidon.  The LOD, combined with the abstraction, are the basis of Zeidon's power.  For a quick demonstration of a Zeidon application [check out the walk-through](QuickWalkThrough.html).  Some of the advantages of Zeidon over traditional methods for application development include the following.

# Why Use Zeidon?
The combination of abstraction and logical objects brings many benefits.

## Software Transactions
Since a logical object brings together all the data used for a transaction changes can usually be isolated to a single logical object.  Zeidon keeps track of changes made to the data so that they can be committed together.  This allows transactions to be controlled by Zeidon instead of the DB which means that DB connections are not held open, increasing throughput.

## Entity Context
Entities (e.g. SQL tables) are used differently depending on the context.  A report might not need all the columns and tables should not be lazy-loaded.  In another situation the UI might need all the columns but tables can be lazy-loaded because it depends on the user's actions.  In traditional ORMs it can be difficult to handle all the contexts in an optimal way.  However, Zeidon allows entities to be included in multiple logical objects and keeps the context inherent in the design of the object.


## Eliminates Impedance Mismatch

> Object/relational mapping is the Vietnam of Computer Science

– Ted Neward, author of Effective Enterprise Java

Industry standard engineering solutions such as Ruby on Rails and Hibernate are excellent for writing simple and medium-complixity applications.  They begin to break down, however, when the data behind the applications become large and complex with a myriad of business rules.  The break down for a few reasons:

  * They don't solve the impedance mismatch between object-oriented programming and relational DBs.
  * Most solutions do little to help you design the structure of your data.

Zeidon solves the mismatch problem with the LOD.  The LOD is built directly from the ER model and is used to load data from the DB.  The application logic, using a Scala DSL, then accesses the data in the LOD.  The code is thus written against the entities in the ER model instead of an intermediate Java object.

## Full Development-cycle

* *DB Design* - Zeidon's ER Diagrammer and LOD tool helps you design the DB and how it will be accessed.
* *Implementation* - Zeidon's DSL make it easy to write complex queries and implement business logic.
* *Debugging* - The Object Browser lets the developer view the data loaded into objects instances.

## Better Control over DB Optimization
The context of the logical object makes it easier to tailor DB optimization like joins and lazy loading to the problem.  For more details on the different options for optimization see wiki page on [improving performance](https://github.com/DeegC/zeidon-joe/wiki/Improving-Performance).

## Logical Data Locking
Logical objects give fine-grained control over what ER entities can be updated.  This usually allows for simpler locking in situations that can lead to race conditions when updating data.  See the "Locking" section in the [Qual Walk Through](QuickWalkThrough.html) for an example.

## Trivial Asynchronous DB Loads
Asynchronous loads are trivial with Zeidon.  Simply add the ".synchronous" qualification to the activate.  The call will return immediately; the first time the view is used it will block until the load has finished.

```scala
val orders = View( task ) basedOn "Order"
orders.buildQual( _.Product.Discontinued = true )
      .asynchronous
      .activate()

// Do other work here.

println( "Id = " + order.Order.OrderId ) // This will block until load has finished.
```
