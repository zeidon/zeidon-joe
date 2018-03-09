package com.quinsoft.zeidon.scala

import scala.language.dynamics
import com.quinsoft.zeidon.Task

class DynamicTask( val task : Task ) extends Dynamic {

    def using( lodName: String ): DynamicTaskActivator = {
        new DynamicTaskActivator( task, lodName )
    }

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

    case class DynamicTaskActivator( val task: Task, val lodName: String ) {
        val view: View = new View( task ) basedOn lodName

        def activateWith( addQual: (QualBuilder) => QualBuilder): View = {
            val qb = view.buildQual()
            addQual(qb)
            qb.activate
        }

        def all() : View = view.activateAll()

        def empty() : View = view.activateEmpty

        def activate( addQual: ( EntityQualBuilder ) => QualificationTerminator ): View = {
            val builder = new QualBuilder( view, view.lodDef )
            addQual( builder.entityQualBuilder )
            return builder.activate
        }
    }
}

object DynamicTask {
    implicit def dynamicTask2Task( dtask: DynamicTask ) = dtask.task
}
