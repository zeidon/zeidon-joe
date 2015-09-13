---
title: What is Zeidon
layout: default
---

## What is Zeidon?

Zeidon is an application framework for developing data-oriented applications.  It has two aspects that give it power:

1) Zeidon abstracts the structure of the ER model and makes it directly accessable from application code.  There is no need to build objects (e.g. Java objects) to retrieve and store the data in memory because Zeidon does this.

2) The abstraction lets the developer create "logical objects".  Logical objects group multiple entities from the ER model into structures that are based on business logic.  Because of the abstraction the structures have many aspects of object-oriented programming.  The entities in the logical object are chosen to perform some business logic, like create an order or update user information.  This allows multiple entities (i.e. SQL tables) to be accessed like a single object instead of a group of related-but-separate objects.

The structure of the logical object is laid out in the Logical Object Definition (lOD) which is the fundamental unit in Zeidon.  The LOD, combined with the abstraction, are the basis of Zeidon's power.  For a quick demonstration of a Zeidon application [check out the walk-through](QuickWalkThrough.html).  Some of the advantages of Zeidon over traditional methods for application development include the following.

## Software Transactions

## Entity Context

## Eliminates Impedance Mismatch

> Object/relational mapping is the Vietnam of Computer Science

– Ted Neward, author of Effective Enterprise Java

Industry standard engineering solutions such as Ruby on Rails and Hibernate are excellent for writing simple and medium-complixity applications.  They begin to break down, however, when the data behind the applications become large and complex with a myriad of business rules.  The break down for a few reasons:

  * They don't solve the impedance mismatch between object-oriented programming and relational DBs.
  * Most solutions do little to help you design the structure of your data.

Zeidon solves the mismatch problem with the LOD.  The LOD is built directly from the ER model and is used to load data from the DB.  The application logic, using a Scala DSL, then accesses the data in the LOD.  The code is thus written against the entities in the ER model instead of an intermediate Java object.

## Full Development-cycle

* *DB Design* - Zeidon's ER Diagrammer and LOD tool help the developer design the DB and how it will be accessed.
* *Implementation* - Zeidon's DSL make it easy to write complex queries and implement business logic.
* *Debugging* - The Object Browser lets the developer view the data loaded into objects instances.

## Better Control of Joins

## Sane Lazy Loading


