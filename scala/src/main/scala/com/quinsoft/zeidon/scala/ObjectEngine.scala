/**
 *
 */
package com.quinsoft.zeidon.scala

/**
 * @author dgc
 *
 */
class ObjectEngine( val joe: com.quinsoft.zeidon.ObjectEngine ) {

    def getTask( taskId: String ): Task = {
        val jtask = joe.getTaskById( taskId )
        if ( jtask == null )
            return null

        new Task( jtask )
    }

    def createTask( appName: String, taskId: String = null ) = new Task( joe.createTask( appName, taskId ) )

}
