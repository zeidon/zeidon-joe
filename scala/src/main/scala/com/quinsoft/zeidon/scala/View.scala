/**
 *
 */
package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon._
import com.quinsoft.zeidon.objectdefinition._
import com.quinsoft.zeidon.scala.EntityCursor

/**
 * @author dgc
 *
 */
class View( val task: Task ) extends Task(task) {

    def this( jv: com.quinsoft.zeidon.View ) = {
      this( new Task( jv.getTask() ) )
      jviewOd = jv.getViewOd()
      jview = jv
    }

    var jviewOd: ViewOd = null
    var jview:   com.quinsoft.zeidon.View = null

	def basedOnLod( lodName: String ): View = {
		jviewOd = task.jtask.getApplication().getViewOd( task.jtask, lodName )
		return this
	}

    def from( view: View ) {
        jview = view.jview.newView()
        jviewOd = jview.getViewOd()
    }

    def copyCursors( view: View ) {
        if ( jview == null )
            throw new ZeidonException( "View has no OI" )

        jview.copyCursors(view.jview)
    }

    def activateEmpty(): Unit = {
        validateViewOd
        jview = jtask.activateEmptyObjectInstance( jviewOd )
    }

    def activateWhere( addQual: (QualBuilder) => QualBuilder ): QualBuilder = {
        validateViewOd
        val builder = new QualBuilder( this, jviewOd )
        addQual( builder )
    }

    def logObjectInstance = jview.logObjectInstance()

    def selectDynamic(entityName: String): EntityCursor = {
        validateViewOd
        val jviewEntity = jviewOd.getViewEntity(entityName)
        val jcur = jview.cursor(jviewEntity)
        new EntityCursor( this, jcur )
    }

    private def validateViewOd: Unit = {
        if ( jviewOd == null )
            throw new ZeidonException( "LOD name not established for this View" )
    }
}