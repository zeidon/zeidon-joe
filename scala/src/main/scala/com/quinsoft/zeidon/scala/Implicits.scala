package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon.SelectSet
import com.quinsoft.zeidon.Task
import com.quinsoft.zeidon.ObjectEngine

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

        def each( looper: => Unit ) = {
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
        def getView( viewName: String ): View = new View( task.getViewByName( viewName ) )
    }
}