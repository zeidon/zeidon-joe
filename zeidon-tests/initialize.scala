// List of Scala commands to initialize REPL instance.
// Load with:
//    :load initialize.scala

import com.quinsoft.zeidon.scala._

// Load the object engine and create a task.
val oe = ObjectEngine.getInstance
val task = oe.createTask("ZENCAs")
