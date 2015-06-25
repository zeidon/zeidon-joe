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
package com.quinsoft.zeidon.scala

/**
  * A wrapper class around the AnyRef returned by an Zeidon Object Operation.
  * This will automatically convert the value to standard Scala types.
  *
  */
case class ObjectOperationResult( val value: AnyRef ) {

}

object ObjectOperationResult {
   implicit def result2String( result: ObjectOperationResult) = result.value.toString
   implicit def result2View( result: ObjectOperationResult) = result.value.asInstanceOf[View]
   
   implicit def result2Int( result: ObjectOperationResult): Int = result.value match {
       case a: AttributeInstance => a.toInt
       case _ => result.value.asInstanceOf[Int]
   }
}