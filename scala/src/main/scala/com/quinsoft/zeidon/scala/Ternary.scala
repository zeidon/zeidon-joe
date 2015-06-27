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
 * Adds ternary operator ?: (the "elvis" operator).  To use, add the following
 * import:
 *      import com.quinsoft.zeidon.scala.Ternary._
 *
 * Usage:
 *
 *      val someval = "CheckThisForNull" ?: "UseThisIfOtherIsNull"
 *
 * Copied from http://www.codecommit.com/blog/scala/implementing-groovys-elvis-operator-in-scala
 *
 * @author dgc
 *
 */
object Ternary {

  /**
   * Following is for C#'s syntax.
  implicit def coalescingOperator[T](pred: T) = new {
    def ??[A >: T](alt: =>A) = if (pred == null) alt else pred
  }
   */

  implicit def elvisOperator[T](alt: =>T) = new {
    def ?:[A >: T](pred: A) = if (pred == null) alt else pred
  }

}