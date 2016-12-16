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
class SampleCode( var task: Task ) extends ZeidonOperations {

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
    
    
    def deleteTerritory( territoryId: String ) {
        val region = View( task ) basedOn "Region"
        region.activateWhere( _.Territory.TerritoryId = territoryId )
        if ( ! region.isEmpty ) {
            region.Territory setFirst( _.TerritoryId == territoryId )            
            region.Territory delete()
            region commit()
        }
    }
    
    def createTerritory( regionId: Int, territoryId: String, desc : String ) {
        
        val region = View( task ) basedOn "Region"
        region.activateWhere( _.Region.RegionId = regionId )
        if ( region.isEmpty )
            throw new ZeidonException( "Unknown Region ID = %s", regionId.toString() )
        
        region.Territory create()
        region.Territory.TerritoryId = territoryId
        region.Territory.TerritoryDescription = desc
        
        region commit()
    }
    
    def spawning = {
        val supervisor = View( task ) basedOn "Employee"
        supervisor activateWhere( _.Employee.EmployeeId = 5 ) 
        supervisor name "Supervisor"

        val newEmployee = View( task ) basedOn "Employee"
        newEmployee activateEmpty() 
        newEmployee name "New Employee"
        newEmployee.Employee create()
        newEmployee.Employee.FirstName = "John"
        newEmployee.Employee.LastName = "Smith"

        println( "Supervisor reports count = " + supervisor.DirectReport.count )
        newEmployee.Supervisor include supervisor.Employee
        println( "Supervisor reports count = " + supervisor.DirectReport.count )
    }
}

object SampleCode {

    def main(args: Array[String]) {
        // Instantiate JOE using standard configuration.  This requires zeidon.ini
        // in the classpath.
        val objectEngine = JavaObjectEngine.getInstance
        objectEngine.startBrowser()
        
        val task = objectEngine.createTask("Northwind")
        task.log.info("Loaded task")
        
        val testLogic = new SampleCode( task )
        testLogic.spawning
        
//        testLogic.deleteTerritory( "99999" )
//        testLogic.createTerritory( 1, "99999", "Quincy" )
    }
  
}