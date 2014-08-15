/**
 *
 */
package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon.ZeidonException
import com.quinsoft.zeidon.objectdefinition.ViewOd

/**
 * A Scala wrapper for the JOE View.  This object uses dynamic methods that allows
 * users to write code using VML-like view.entity.attribute syntax.
 *
 * @author dgc
 *
 */
class View( val task: Task ) extends Task(task) {

    var jviewOd: ViewOd = null
    var jview:   com.quinsoft.zeidon.View = null

    def this( jv: com.quinsoft.zeidon.View ) = {
      this( new Task( jv.getTask() ) )
      jviewOd = jv.getViewOd()
      jview = jv
    }

    def this( jtask: com.quinsoft.zeidon.Task ) = {
      this( new Task( jtask ) )
    }

    def BASEDONLOD( lodName: String ): View = basedOnLod( lodName )
	def basedOnLod( lodName: String ): View = {
		jviewOd = task.jtask.getApplication().getViewOd( task.jtask, lodName )
		if ( jview != null && jview.getViewOd() != jviewOd )
		  throw new ZeidonException( "ViewOD set by basedOnLod doesn't match view." )

		return this
	}

    def from( view: View ) = {
        jview = view.jview.newView()
        jviewOd = jview.getViewOd()
        this
    }

    def copyCursors( view: View ) = {
        if ( jview == null )
            throw new ZeidonException( "View has no OI" )

        jview.copyCursors(view.jview)
        this
    }

    def activateEmpty() = {
        validateViewOd
        jview = jtask.activateEmptyObjectInstance( jviewOd )
        this
    }

    def activateWhere( addQual: (QualBuilder) => QualBuilder ): View = {
        validateViewOd
        val builder = new QualBuilder( this, jviewOd )
        addQual( builder )
        builder.activate
        this
    }

    def buildQual( addQual: (QualBuilder) => QualBuilder ): QualBuilder = {
        val builder = buildQual()
        addQual( builder )
    }

    def buildQual(): QualBuilder = {
        validateViewOd
        val builder = new QualBuilder( this, jviewOd )
        return builder
    }

    def name( viewName: String ) = jview.setName( viewName )
    def assert = new AssertView( this )
    def odName = if ( jviewOd  == null ) "*not specified*" else jviewOd.getName
    def isEmpty = jview.isEmpty()
    def logObjectInstance = jview.logObjectInstance()
    def activateOptions = jview.getActivateOptions()
    def serializeOi = jview.serializeOi()
    override def deserializeOi = jview.deserializeOi()

    /**
     * This is called when the compiler doesn't recognize a method name.  This
     * is used to find the entity cursor for a view.
     */
    def selectDynamic(entityName: String): EntityCursor = {
        validateViewOd

        val jviewEntity = jviewOd.getViewEntity(entityName)
        val jcur = jview.cursor(jviewEntity)
        new EntityCursor( this, jcur )
    }

    /**
     * Called dynamically to process a Object Operation.
     */
    def applyDynamic( operationName: String)(args: AnyRef*): AnyRef = {
        println( s"method '$operationName' called with arguments ${args.mkString("'", "', '", "'")}" )
        validateViewOd

        val oe = task.objectEngine
        val oper = oe.objectOperationMap.getObjectOperation(operationName, jviewOd, args: _*)
        return oper.invokeOperation(this, args:_*)
    }

    override def toString = if ( jview != null ) jview.toString() else "*undefined*"

    /**
     * Validates that the View OD is specified.
     */
    private def validateViewOd: Unit = {
        if ( jviewOd == null )
            throw new ZeidonException( "LOD name not established for this View" )
    }
}

object View {
    /**
     * Convert a Java View to a Scala View
     */
    implicit def jview2view( jview: com.quinsoft.zeidon.View ) = new com.quinsoft.zeidon.scala.View( jview )

    /**
     * Convert Scala View to Java View.
     */
    implicit def view2jview( view: com.quinsoft.zeidon.scala.View ) = view.jview

}