/**
 *
 */
package com.quinsoft.zeidon.scala

import scala.language.dynamics

/**
 * @author dgc
 *
 */
case class Task ( val jtask: com.quinsoft.zeidon.Task ) extends Dynamic {

  def this( task: Task ) = this( task.jtask )
}