package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon.SelectSet
import com.quinsoft.zeidon.Task
import com.quinsoft.zeidon.ObjectEngine
import com.quinsoft.zeidon.DeserializeOi
import com.typesafe.scalalogging.Logger

/**
 * @author dgc
 */

object Implicits {
    implicit class ScalaSelectSet( val selectSet : SelectSet ) {
        def selectAll( list: Iterable[com.quinsoft.zeidon.scala.EntityInstance] ) = {
            list.foreach( selectSet.select( _ ) )
        }

        def deselectAll( list: Iterable[com.quinsoft.zeidon.scala.EntityInstance] ) = {
            list.foreach( selectSet.deselect( _ ) )
        }

        def selectWhere( qual : (SelectQualification) => SelectQualTerminator ) = {
            qual( new SelectQualification( selectSet ) )
        }

        def selectSubobjectWhere( qual : (SelectQualification) => SelectQualTerminator ) = {
            qual( new SelectQualification( selectSet, true, true ) )
        }

        def selectSubobject( ei: com.quinsoft.zeidon.EntityInstance ) = selectSet.select(ei, true)
        def deselectSubobject( ei: com.quinsoft.zeidon.EntityInstance ) = selectSet.deselect(ei, true)

        def each[T]( looper: => T ): T = {
            val iter = new EntityInstanceIterator( selectSet.eachEntity )
            iter.each( looper )
        }

        def each( looper: (AbstractEntity) => Unit ) = {
            val iter = new EntityInstanceIterator( selectSet.eachEntity )
            iter.foreach( looper( _ ) )
        }

        def foreach( looper: (EntityInstance ) => Unit ) = {
            val iter = new EntityInstanceIterator( selectSet.eachEntity )
            iter.foreach( ei => looper( ei ) )
        }
    }

    implicit class ScalaTask( val task : Task ) {
        /**
         * Get a view by name in the task.
         */
        def getView( viewName: String ): View = new View( task.getViewByName( viewName ) )
        def id = task.getTaskId

        /**
         * Creates a Scala wrapper around the Zeidon Logger.  It is occasionally useful
         * to avoid ambiguous method errors.
         */
        def slog = Logger( task.log() )

        def activate( lodName: String, addQual: (QualBuilder) => QualBuilder ): View = {
          val view = new View( task ) basedOn lodName
          val qb = view.buildQual()
          addQual( qb )
          qb.activate
        }

        def activate( lodName: String): TaskActivator = {
          new TaskActivator( task, lodName )
        }

        def dropLocks( lodName: String, addQual: (QualBuilder) => Unit ): Unit = {
          val view = new View( task ) basedOn lodName
          val qb = view.buildQual()
          addQual( qb )
          qb.dropLocks
        }

        def newView( lodName: String ): View = new View( task ) basedOn lodName
    }

    implicit class ScalaObjectEngine( val oe : ObjectEngine ) {

        /**
         * Run a function inside a task.
         *
         * This is a convenience method to create a task, execute a method, and then
         * automatically drop the task.
         * {{{
         * objectEngine.forTask( "Northwind" ) { task =>
         *   val order = View( task ) basedOn "Order"
         *   order.activateWhere( _.Order.OrderID = orderId )
         *   order.Order.OrderDate
         * }
         * }}}
         */
        def forTask( appName : String ) : TaskRunner = {
            val task = oe.createTask( appName );
            TaskRunner( task )
        }
    }

    implicit class ScalaDeserializeOi( val deserializer: DeserializeOi ) {
        /**
         * A convenience method to deserialize directly to a Scala view using
         * Scala (de-)serialization nomenclature.
         */
        def unpickle : View = new View( deserializer.activateFirst() )
    }

    /**
     * Convenience class for ScalaObjectEngine.forTask
     */
    case class TaskRunner( val task: Task ) {
        def apply[T]( runTask: Task => T ): T = {
            try {
                runTask( task )
            }
            finally {
                task.dropTask()
            }
        }
    }

    case class TaskActivator( val task: Task, val lodName: String ) {
        val view: View = task.newView(lodName)

        def apply( addQual: ( EntityQualBuilder ) => QualificationTerminator ): View = {
            val builder = new QualBuilder( view, view.lodDef )
            addQual( builder.entityQualBuilder )
            return builder.activate
        }

        def using( addQual: (QualBuilder) => QualBuilder ): View = {
          val qb = view.buildQual()
          addQual( qb )
          qb.activate
        }

	def all() : View = { view.activateAll() }
    }
}
