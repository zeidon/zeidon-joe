package com.quinsoft.northwind.scala

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Before
import org.junit.Test
import com.quinsoft.zeidon.scala.ZeidonOperations
import com.quinsoft.zeidon.scala.View
import com.quinsoft.zeidon.scala.Implicits._
import com.quinsoft.zeidon.standardoe.JavaObjectEngine

class TestNorthwind extends AssertionsForJUnit {

    val oe = JavaObjectEngine.getInstance()
    var task = oe.createTask("northwind")

    @Before
    def initialize() {
//        oe.startBrowser
    }

    @Test
    def testLoadFromJson() {
        val order = task.deserializeOi().fromFile("testdata/northwind/data/order1.json").unpickle
        assertTrue( order.Employee.exists )
        assertTrue( order.Customer.exists )
        assertTrue( order.Shipper.exists )
        order.logObjectInstance
    }
}