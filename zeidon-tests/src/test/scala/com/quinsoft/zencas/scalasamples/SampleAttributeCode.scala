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
import com.quinsoft.zeidon.scala.Task
import com.quinsoft.zeidon.scala.ObjectEngine
import com.quinsoft.zeidon.scala.basedOn
import com.quinsoft.zeidon.scala.View

/**
 * Sample code for dealing with Zeidon attributes in Scala.
 */
class SampleAttributeCode( var task: Task ) extends ZeidonOperations {

    def attributeValues( mUser : View @basedOn( "mUser" ) ) {

        // Explicitly get a string value.
        val s1 = mUser.User.ID.toString

        // Automatically convert an attribute to a string.
        val s2: String = mUser.User.ID

        // Adding values to attributes is easy.
        val int1 = mUser.User.ID + 10

        // In some cases you must explicitly convert an attribute value to a type
        // because otherwise the desired type is ambiguous to the compiler.
        val int2 = 10 + mUser.User.ID.toInt

        // Get a value using a context.
        val date = mUser.User.LastLoginDateTime.toString( "yyyy-MM-dd")
        println( "Date = " + date )

        // Setting an attribute value.  The source value will be automatically
        // converted by the attribute's domain processing.
        mUser.User.UserName = "foo"
        mUser.User.UserName = 10       // Will be converted to a string.
        mUser.User.UserName = mUser.UserGroup.GroupName

        // Setting a value using a context.
        mUser.User.LastLoginDateTime.setValue( "01/01/2014", "MM/dd/yyyy")
        println( "New date = " + mUser.User.LastLoginDateTime.toString( "yyyy-MM-dd") )
    }

    def compareAttribute( mUser : View @basedOn( "mUser" ) ) {
        if ( mUser.User.ID > 10 )
            println( "Greater than 10" )

        if ( mUser.User.ID < "10" )
            println( "Less than 10" )

        // Compare an attribute to a value.  This code will convert the attribute
        // to an integer and then compare.  This will throw a NullAttributeValueException
        // if the attribute is null.
        if ( mUser.User.ID == 10 )
            println( "Equal to 10" )

        if ( mUser.User.ID in (10, 21, 32 ) )
            println( "ID is 10, 21, or 32" )
            
        // This will compare the attribute using Zeidon domain processing.  If the domain
        // comparison handles nulls then this line will not throw an exception.
        if ( mUser.User.Active @== "Y" )
            println( "Equal to Y." )
        else
            println( "Not 'Y'.  May be null.")

        // isTruthy will attempt to convert the attribute value to a boolean.  E.g. if the
        // attribute is an integer then != 0 is true.
        if ( mUser.User.Active.isTruthy )
            println( "Is truthy" )
    }

    /**
     * Information about an attribute other than its value.
     */
    def metaInformation( mUser : View @basedOn( "mUser" ) ) {
        // Check to see if an attribute has changed since it was read from the DB.
        if ( mUser.User.UserName.isUpdated )
            println( "Attribute has been changed" )

        if ( ! mUser.User.ID.isReadOnly )
            mUser.User.ID = 200

        // The AttributeDef has most of the meta information about an attribute.
        val attributeDef = mUser.User.ID.attributeDef
        if ( attributeDef.isKey() )
            println( "Attribute is a key" )

        if ( attributeDef.isPersistent() )
            println( "Attribute is stored in the DB" )
    }

    /**
     * The AttributeInstance is the object that stores an attribute value
     * and its meta information.
     */
    def attributeInstance( mUser : View @basedOn( "mUser" ) ) {

        // Get the AttributeInstance for an attribute
        val attr = mUser.User.ID
        val x = attr + 10

        // AttributeInstances are automatically converted to strings.
        println( "Value = " + attr ) // attr is automatically converted to a string.

        // Explicitly getting the value.
        val i = 10 + attr.toInt      // attr can be converted to int

        // Use setValue to change the attribute value.  "attr = 100" will not work
        // because that will attempt to reassign the attr variable.
        attr.setValue( 100 )
        mUser.User.ID = 100 // This works, however.

        // A gotcha: following code does NOT change the value of mUser.User.Name
        var attr2 = mUser.User.UserName
        attr2 = attr // This re-assigns attr2.
    }

    def attributeLists( mUser : View @basedOn( "mUser" ) ) {
        mUser.User.attributes.filter { _.attributeDef.isKey() }.foreach { attr => println( attr ) }
    }
    
    def runAll( mUser: View ) = {
        attributeValues( mUser )
        metaInformation( mUser )
        compareAttribute( mUser )
        attributeInstance( mUser )
        attributeLists( mUser )
    }
}

object SampleAttributeCode {

    def main(args: Array[String]): Unit = {

        // Load the object engine and create a task.
        val oe = ObjectEngine.getInstance
        val task = oe.createTask("ZENCAs")

        val activator = new SampleActivates( task )
        var mUser = activator.activateSimple

        val attributeCode = new SampleAttributeCode(task)
        attributeCode.runAll( mUser )

//        oe.startBrowser
    }

}