/**
 * This file is part of the Zeidon Java Object Engine (Zeidon JOE).
 *
 * Zeidon JOE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Zeidon JOE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009-2015 QuinSoft
 */
package com.quinsoft.zencas.scalasamples

import com.quinsoft.zeidon.scala.ZeidonOperations
import com.quinsoft.zeidon.scala.basedOn
import com.quinsoft.zeidon.scala.View
import com.quinsoft.zeidon.ObjectEngine
import com.quinsoft.zeidon.scala.Task
import com.quinsoft.zeidon.scala.Implicits._
import com.quinsoft.zeidon.standardoe.JavaObjectEngine

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

/**
 * Sample code for dealing with Zeidon attributes in Scala.
 */
@RunWith(classOf[JUnitRunner])
class SampleAttributeCode extends AnyFunSuite with ZeidonOperations {

    val oe = JavaObjectEngine.getInstance()
    var task = oe.createTask("Northwind")
    val order = task.Order.activate( _.Order.OrderId = 11009 )

    test( "attributeValues" ) {
        // Explicitly get a string value.
        val s1 = order.Order.OrderId.toString
        assert( s1 == "11009" )

        // Automatically convert an attribute to a string.
        val s2: String = order.Order.OrderId
        assert( s2 == "11009" )

        // Adding values to attributes is easy.
        val int1 = order.OrderDetail.Quantity + 10

        // In some cases you must explicitly convert an attribute value to a type
        // because otherwise the desired type is ambiguous to the compiler.
        val int2 = 10 + order.OrderDetail.Quantity.toInt

        // Get a value using a context.
        val date = order.Order.ShippedDate.toString( "yyyy-MM-dd")
        println( "Date = " + date )

        // Setting an attribute value.  The source value will be automatically
        // converted by the attribute's domain processing.
        order.Order.ShipCity = "foo"
        order.Order.ShipCity = 10       // Will be converted to a string.
        assert( order.Order.ShipCity == "10" )
        order.Order.ShipCity = order.Order.ShipRegion

        // Setting a value using a context.
        order.Order.ShippedDate.setValue( "01/01/2014", "MM/dd/yyyy")
        println( "New date = " + order.Order.ShippedDate.toString( "yyyy-MM-dd") )
    }

    test( "compareAttributes" ) { compareAttribute( order ) }
    def compareAttribute( mUser : View @basedOn( "Order" ) ) {
        if ( order.Order.OrderId > 10 )
            println( "Greater than 10" )

        if ( order.Order.OrderId < "10" )  // Can automatically convert using domain processing.
            println( "Less than 10" )

        // Compare an attribute to a value.  This code will convert the attribute
        // to an integer and then compare.  This will throw a NullAttributeValueException
        // if the attribute is null.
        if ( order.Order.OrderId == 11009 )
            println( "Equal to 11009" )

        if ( order.Order.OrderId in (11009, 21, 32 ) )
            println( "ID is 11009, 21, or 32" )

        // This will compare the attribute using Zeidon domain processing.  If the domain
        // comparison handles nulls then this line will not throw an exception.
        if ( order.Order.ShippedDate @== "2014-01-01" )
            println( "Equal to 01/01/2014." )
        else
            println( "Not '01/01/2014'.  May be null.")

        // isTruthy will attempt to convert the attribute value to a boolean.  E.g. if the
        // attribute is an integer then != 0 is true.
        assert( order.OrderDetail.Quantity != 0 )
        assert( order.OrderDetail.Quantity.isTruthy ) // Non-zero
        order.OrderDetail.Quantity = 0
        assert( ! order.OrderDetail.Quantity.isTruthy ) // Non-zero

        assert( ! order.Order.ShipName.isBlank ) // Non-blank
        assert( order.Order.ShipName.isTruthy ) // Non-blank
        order.Order.ShipName = ""
        assert( ! order.Order.ShipName.isTruthy ) // Blank string returns false.
        order.Order.ShipName = "false"
        assert( ! order.Order.ShipName.isTruthy ) // String value of "false" returns false.

        assert( order.Order.ShippedDate.isTruthy ) // Non-null
        order.Order.ShippedDate = null
        assert( ! order.Order.ShippedDate.isTruthy )
    }

    test( "dates" ) {
        order.Order.ShippedDate = "NOW"  // Sets attribute to current date.

        // Use a context to get a formatted string.
        val formattedDate =  order.Order.ShippedDate.getString( "yyyy-MM-dd" )

        // To add milliseconds to a time, use add() or +=
        order.Order.ShippedDate += 1000 // Adds 1000 milliseconds.

        // To add time to a date, use the add() method and specify a context.
        order.Order.ShippedDate.add( 1, "YEAR" )
    }

    /**
     * Information about an attribute other than its value.
     */
    test( "metaInformation" ) {
        // Check to see if an attribute has changed since it was read from the DB.
        val order = task.Order.activate( _.Order.OrderId = 11009 )

        assert( ! order.Order.ShipName.isUpdated )
        order.Order.ShipName = "foo"
        assert( order.Order.ShipName.isUpdated )

        assert( order.Order.OrderId.isReadOnly )

        // The AttributeDef has most of the meta information about an attribute.
        val attributeDef = order.Order.OrderId.attributeDef
        assert( attributeDef.isKey() )

        assert( attributeDef.isPersistent() ) // Attribute is stored on the DB.
    }
}
