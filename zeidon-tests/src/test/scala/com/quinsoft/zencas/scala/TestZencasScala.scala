package com.quinsoft.zencas.scala

import org.junit.Assert._
import org.junit.Before
import org.junit.Test
import com.quinsoft.zeidon.scala.ZeidonOperations
import com.quinsoft.zeidon.scala.View
import com.quinsoft.zeidon.scala.Implicits._
import com.quinsoft.zeidon.standardoe.JavaObjectEngine
import com.quinsoft.zeidon.scala.Task
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import com.quinsoft.zeidon.ZeidonException

@RunWith(classOf[JUnitRunner])
class TestZencasScala extends AnyFunSuite with ZeidonOperations {

    val oe = JavaObjectEngine.getInstance()
    var task = oe.createTask("ZENCAs")

    test( "Entity Cache" ) {
        // First activate mClass without the EntityCache.
        val classes = new View( task ) basedOn "mClass"
        classes.activateWhere( _.Class.ID < 1000 )
        classes.name( "classes" )

        // Now activate the entity cache.
        val userList = new View( task ) basedOn "mCollege-withHash"
        userList.buildQual()
                .cachedAs( "CollegeList")
                .setAsEntityCache
                .activate()

        // Activate mClass with EntityCache.
        val classes2 = new View( task ) basedOn "mClass"
        classes2.activateWhere( _.Class.ID < 1000 )
        classes2.name( "classes2" )

        val id = classes2.College.ID.toInt
        userList.College.set( _.ID = id )
        assertTrue( userList.College.isLinked( classes2.College ) )
        assertFalse( userList.College.isLinked( classes.College ) )
        assertEquals( classes.getEntityCount(false), classes2.getEntityCount( false ) )

        userList.logOi
    }

    test( "testMultipleWhenNull" ) {
        val userList = new View( task ) basedOn "mAdmDiv"
        val adminId: Integer = 0
        val personId: Integer = 10

        userList.buildQual( _.AdministrativeDivision.ID < 10 )
                .whenNotNull( adminId, _.restrict { _.Cohort }.to { _.Cohort.ID > adminId } )
                .whenNotNull( personId, _.restrict { _.Cohort }.to { _.Cohort.ID < personId } )
                .activate()
    }

    test( "testStringInterpolation" ) {
        val classes = new View( task ) basedOn "mClass"
        classes.activateWhere( _.Class.ID = 118 )
        var x = classes.interpolate( "${Class.ID}" )
        assertEquals( "118", x )

        val vars = Map( "class" -> classes )
        x = classes.interpolate( "${class.Class.ID}", vars )
        assertEquals( "118", x )
    }

    test( "testTaskImplicits" ) {
        val dtask = new Task( task )
        val userList = dtask.mAdmDiv.activate( _.AdministrativeDivision.ID < 10 )
    }
}
