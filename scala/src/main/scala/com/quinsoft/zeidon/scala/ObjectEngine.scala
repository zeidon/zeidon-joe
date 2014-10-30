/**
 *
 */
package com.quinsoft.zeidon.scala

/**
 * @author dgc
 *
 */
class ObjectEngine( val joe: com.quinsoft.zeidon.ObjectEngine ) {
    val systemTask = new Task( joe.getSystemTask(), this )

    private [scala] val objectOperationMap = new ObjectOperationMap

    def getTask( taskId: String ): Task = {
        val jtask = joe.getTaskById( taskId )
        if ( jtask == null )
            return null

        new Task( jtask )
    }

    def createTask( appName: String, persistent: Boolean = true, taskId: String = null ) =
                    new Task( joe.createTask( appName, persistent, taskId ), this )
    def taskList = joe.getTaskList()
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