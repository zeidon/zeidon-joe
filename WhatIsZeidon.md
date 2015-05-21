---
title: What is Zeidon
layout: default
---

## What is Zeidon?

Zeidon is an application framework for developing data-oriented applications. It enables engineers to design their solutions at a higher level of abstraction to separate the business logic from the underlying storage system. The abstraction eliminates the the impedance mismatch that occurs between relational DBs and OOP applications.

The abstraction also helps engineers manage the complexity of applications with many tables and relationships.

### Why use Zeidon

Industry standard engineering solutions such as Ruby on Rails and Hibernate are excellent for writing simple and medium-complixity applications.  They begin to break down, however, when the data behind the applications become large and complex with a myriad of business rules.  The break down for a few reasons:

  * They don't solve the impedance mismatch between object-oriented programming and relational DBs.
  * Most solutions do little to help you design the structure of your data.

### Impedance Mismatch

> Object/relational mapping is the Vietnam of Computer Science

– Ted Neward, author of Effective Enterprise Java

### Industry response to Impedance Mismatch

The industry has tried to solve the problem with multiple solutions:

  * Object-oriented DBs.
  * Translators like stored procedures.
  * NoSQL/Document DBs like MongoDB
  * ORB/ORMs like Hibernate.
  * Microsoft's Entity Framework.

All of these solutions have their own problems. Except for Entity Framework, none of them attempt to solve the problem with data abstraction.

### Zeidon eliminates Impedance Mismatch

Zeidon solves the mismatch problem by using a data abstraction--the Logical Object Definition (LOD)--that straddles both the Relational DB and OOP business logic.  All Zeidon applications start with an ERD which models the data.  A LOD is a logical grouping of entities from the ERD into an object to perform business logic on those entities.

Perhaps an example is in order.  The following is a simple ERD for an application that keeps track of professors, students, and their classes.  It's a standard ERD with entities and attributes (i.e. fields or columns).

![SampleERD](images/sample-erd.png?raw=true)

This is a LOD created to edit information about a professor:  The colors mean something but that's not important now.

![SampleERD](images/prof-lod-full.png?raw=true)

To load the information for a Professor, use Scala to perform a Zeidon activate:

    val prof = new View( task ) basedOn "Professor"
    prof.activateWhere( _.Professor.Id = 10 )

Zeidon will generate the SQL required to load professor 10 from the DB and all the child
data that is associated with professor 10.