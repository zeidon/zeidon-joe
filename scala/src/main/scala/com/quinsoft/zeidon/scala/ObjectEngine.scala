/**
 *
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