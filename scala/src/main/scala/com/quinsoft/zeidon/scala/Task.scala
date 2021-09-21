package com.quinsoft.zeidon.scala

import scala.language.dynamics
import com.quinsoft.zeidon.scala.Implicits._
import com.quinsoft.zeidon.objectdefinition.LodDef

class Task( task : com.quinsoft.zeidon.Task ) extends ScalaTask( task ) with Dynamic {

    /**
     * Called dynamically to convert an lod name into a empty View.
     *
     * This allows the dynamic use of an LODs.
     * {{{
     * val attr = task.User.activate(....)
     * }}}
     */
    def selectDynamic( lodName: String): DynamicTaskActivator = {
        new DynamicTaskActivator( task, lodName )
    }
}

/**
 * Use com.quinsoft.zeidon.scala.Task instead.
 */
@deprecated
class DynamicTask( task : com.quinsoft.zeidon.Task ) extends Task( task ) {
}

case class DynamicTaskActivator( val task: com.quinsoft.zeidon.Task, val lodName: String ) {
    val view: View = new View( task ) basedOn lodName

    def activateWith( addQual: (QualBuilder) => QualBuilder): View = {
        val qb = view.buildQual()
        addQual(qb)
        qb.activate
    }

    def buildQual() : QualBuilder = view.buildQual()

    def all() : View = view.activateAll()

    def empty() : View = view.activateEmpty

    def createRoot() : View = {
        view.activateEmpty
        view.root.create()
        return view
    }

    def activate( addQual: ( EntityQualBuilder ) => QualificationTerminator ): View = {
        val builder = new QualBuilder( view )
        builder.jqual.forEntity( view.lodDef.getRoot )

        addQual( builder.entityQualBuilder )
        return builder.activate
    }

    def fromJson( json: String ) : View = {
        return task.deserializeOi.asJson.setLodDef( view.lodDef ).fromString( json ).unpickle
    }

    def getLodDef : LodDef = view.lodDef
}

object Task {
    implicit def dynamicTask2Task( dtask: Task ) = dtask.task
    implicit def Task2DynamicTask( task: com.quinsoft.zeidon.Task ) = new Task( task )
}

object DynamicTask {
    implicit def dynamicTask2Task( dtask: DynamicTask ) = dtask.task
    implicit def Task2DynamicTask( task: com.quinsoft.zeidon.Task ) = new DynamicTask( task )
    implicit def ScalaTask2DynamicTask( task: Task ) = new DynamicTask( task.task )
}
