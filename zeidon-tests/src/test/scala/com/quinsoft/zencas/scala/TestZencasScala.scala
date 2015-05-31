package com.quinsoft.zencas.scala

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Before
import com.quinsoft.zeidon.scala.ObjectEngine
import org.junit.Test
import com.quinsoft.zeidon.scala.ZeidonOperations
import com.quinsoft.zeidon.scala.View

class TestZencasScala extends AssertionsForJUnit with ZeidonOperations {

    val oe = ObjectEngine.getInstance
    var task = oe.createTask("ZENCAs")

    @Before
    def initialize() {
//        oe.startBrowser
    }

    @Test
    def testEntityCache() {
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
        userList.College.set( _.ID == id )
        assertTrue( userList.College.isLinked( classes2.College ) )
        assertFalse( userList.College.isLinked( classes.College ) )
        assertEquals( classes.getEntityCount(false), classes2.getEntityCount( false ) )

        userList.logOi
    }
}