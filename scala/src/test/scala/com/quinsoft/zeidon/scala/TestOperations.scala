/**
 *
 */
package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon._
import com.quinsoft.zeidon.standardoe.JavaObjectEngine

/**
 * @author dgc
 *
 */
class TestOperations ( task: Task ) extends ZeidonOperations( task ) {
    
	def TestOper() {
		val view = VIEW basedOnLod "mPerson"
		view activateEmpty
		
		
        view.Person create NEXT
		view.Person.PersonID = 1

		view.Person.create.PersonID = 2

		view.Person include view.Person
		view.Person.setFirst
		var personId: String = view.Person.PersonID

		val v = VIEW from view

		view.Person.setFirst( _.Person.PersonID == 2 )
		var id = view.Person.PersonID
		personId = view.Person.PersonID
		
		view.Person.foreach(ei => println(view.Person.PersonID) )
        view.Person.foreach(ei => println( ei.PersonID) )
        view.Person.iterator.withFilter(ei => ei.PersonID == 2 ).foreach( ei => println( ei ) )
        view.Person.iterator.withFilter( _.PersonID == 2 ).foreach( println( _ ) )
        
        view.Person.where { ei => view.Person.PersonID == 1 }
		           .foreach( ei => println( ei ) )
		
		view.activateWhere( _.Person.PersonID = 1 )
		               .orAll( _.Person.PersonID > 2, 
		                       _.Person.PersonID < 5 )
		               .and( q => q.Person.PersonID = q.Person.LastName )
		               .activate
		
		println( view )
	}
}

/*
import com.quinsoft.zeidon._
import com.quinsoft.zeidon.standardoe.JavaObjectEngine
import com.quinsoft.zeidon.scala._
val oe = JavaObjectEngine.getInstance()
val task = oe.createTask("Cheetah")
 */
object TestOperations {

    def main(args: Array[String]): Unit = {
        val oe = JavaObjectEngine.getInstance()
        val task = oe.createTask("Cheetah")
        val stask = Task(task)
        val to = new TestOperations( stask )
        to.TestOper
    }
}