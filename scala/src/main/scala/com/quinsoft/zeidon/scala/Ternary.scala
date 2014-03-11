/**
 *
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