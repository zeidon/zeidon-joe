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
 * The main class for creating Zeidon tasks in Scala.
 *
 * This object is a simple wrapper around the JOE ObjectEngine.  It is typically
 * created by first creating a Java ObjectEngine and then wrapping it with the
 * Scala Object Engine
 */
class ObjectEngine( val joe: com.quinsoft.zeidon.ObjectEngine ) {
    val systemTask = new Task( joe.getSystemTask(), this )

    private [scala] val objectOperationMap = new ObjectOperationMap

    /**
     * Returns the Scala task with the ID specified by taskId.
     * @return Task or null if no tas with matching ID was found.
     */
    def getTask( taskId: String ): Task = {
        val jtask = joe.getTaskById( taskId )
        if ( jtask == null )
            return null

        new Task( jtask )
    }

    /**
     * Creates a new Zeidon task.
     * @param appName Name of the default application for the new task.
     * @param persistent Creates a persistent task if true.  A persistent task *must* be
     *        explicitly dropped.  It will not be dropped otherwise.  A non-persistent task
     *        will be dropped when the reference is no longer used.
     * @param taskId If not null the ID of the new task will be set to this.  Otherwise the
     *        ID will be set to a unique integer.
     */
    def createTask( appName: String, persistent: Boolean = true, taskId: String = null ) =
                    new Task( joe.createTask( appName, persistent, taskId ), this )

    /**
     * Returns list of current tasks.
     */
    def taskList = joe.getTaskList()

    /**
     * Starts the Object Browser.
     */
    def startBrowser = joe.startBrowser()
}

object ObjectEngine {

    /**
     * Returns a Scala ObjectEngine created by calling getInstance on the Java OE.
     */
    def getInstance = {
        val joe = com.quinsoft.zeidon.standardoe.JavaObjectEngine.getInstance()
        new ObjectEngine( joe )
    }
}