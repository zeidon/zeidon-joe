/**
 *
 */
package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon.ZeidonException
import com.quinsoft.zeidon.objectdefinition.LodDef
import scala.language.dynamics
import com.quinsoft.zeidon.ActivateFlags
import com.quinsoft.zeidon.objectdefinition.EntityDef

/**
 * A Scala wrapper for the JOE View.  This object uses dynamic methods that allows
 * users to write code using VML-like view.entity.attribute syntax.
 *
 * @author dgc
 *
 */
class View( val task: Task ) extends Task( task ) with Dynamic {

    var jlodDef: LodDef = null
    var jview: com.quinsoft.zeidon.View = null

    def this( jv: com.quinsoft.zeidon.View ) = {
        this( new Task( jv.getTask() ) )
        jlodDef = jv.getLodDef()
        jview = jv
    }

    def this( jtask: com.quinsoft.zeidon.Task ) = this( new Task( jtask ) )
    def this( view: com.quinsoft.zeidon.scala.View ) = this( view.jview )

    def basedOn( viewDef: ViewDef ) = setLod( viewDef.lodName , viewDef.applicationName  )
    def basedOn( lodName: String ) = setLod( lodName )
    def BASED( f: VmlSyntaxFiller ) = this
    def LOD( lodName: String ): View = setLod( lodName )
    def LOD( viewDef: ViewDef ): View = setLod( viewDef.lodName , viewDef.applicationName  )

    @deprecated("Use basedOn instead")
    def basedOnLod( lodName: String ) = setLod( lodName )

    private def setLod( lodName: String, appName: String = null ): View = {
        if ( appName == null )
            jlodDef = task.jtask.getApplication().getLodDef( task.jtask, lodName )
        else
            jlodDef = task.jtask.getApplication( appName ).getLodDef( task.jtask, lodName )

        if ( jview != null && jview.getLodDef() != jlodDef )
            throw new ZeidonException( "LodDef set by basedOnLod doesn't match view." )

        return this
    }

    def lodDef = jlodDef

    def from( view: View ) = {
        jview = view.jview.newView()
        jlodDef = jview.getLodDef()
        this
    }

    def copyCursors( view: View ) = {
        if ( jview == null )
            throw new ZeidonException( "View has no OI" )

        jview.copyCursors( view.jview )
        this
    }

    def activateEmpty() = {
        validateLodDef
        jview = jtask.activateEmptyObjectInstance( jlodDef )
        this
    }

    def activateWhere( addQual: ( QualBuilder ) => QualBuilder ): View = {
        validateLodDef
        val builder = new QualBuilder( this, jlodDef )
        addQual( builder )
        builder.activate
        this
    }

    def activateAll(): View = {
        validateLodDef
        val builder = new QualBuilder( this, jlodDef )
        builder.activate
        this
    }

    def buildQual( addQual: ( QualBuilder ) => QualBuilder ): QualBuilder = {
        val builder = buildQual()
        addQual( builder )
    }

    def buildQual(): QualBuilder = {
        validateLodDef
        val builder = new QualBuilder( this, jlodDef )
        return builder
    }

    /**
     * Creates a new OI that is a copy of the current OI.  If there are
     * multiple roots then only the currently selected root is cloned.
     * Returns a view to the cloned OI.
     */
    def cloneRoot: View = {
        validateNonEmpty
        val cloned = jview.activateOiFromOi( ActivateFlags.fSINGLE )
        new View( cloned )
    }

    /**
     * Creates a new view with a different owning task.
     */
    def newView( task: Task = this.task ) = { validateNonEmpty; new View( task ).from( this ) }

    /**
     * Creates a new View that has the same cursor positions as the current view.
     */
    def duplicate = { validateNonEmpty; new View( jview.newView ) }
    def name( viewName: String ) = { validateNonEmpty; jview.setName( viewName ) }
    def assert = new AssertView( this )
    def odName = if ( jlodDef == null ) "*not specified*" else jlodDef.getName
    def isEmpty = { validateNonEmpty; jview.isEmpty() }
    def logObjectInstance = { validateNonEmpty; jview.logObjectInstance() }
    def activateOptions = { validateNonEmpty; jview.getActivateOptions() }
    def serializeOi = { validateNonEmpty; jview.serializeOi() }
    override def deserializeOi = { validateNonEmpty; jview.deserializeOi() }
    def createSelectSet() = { validateNonEmpty; jview.createSelectSet() }

    def cursor( entityDef: EntityDef ) = {
        validateNonEmpty
        new EntityCursor( this, jview.cursor( entityDef ) )
    }

    /**
     * Returns the cursor of the root entity..
     */
    def root = {
        validateNonEmpty
        new EntityCursor( this, jview.cursor( jlodDef.getRoot() ) )
    }

    /**
     * This is called when the compiler doesn't recognize a method name.  This
     * is used to find the entity cursor for a view.
     */
    def selectDynamic( entityName: String ): EntityCursor = {
        validateNonEmpty

        val jentityDef = jlodDef.getEntityDef( entityName )
        val jcur = jview.cursor( jentityDef )
        new EntityCursor( this, jcur )
    }

    /**
     * Called dynamically to process a Object Operation.
     */
    def applyDynamic( operationName: String )( args: AnyRef* ): AnyRef = {
//        println( s"method '$operationName' called with arguments ${args.mkString( "'", "', '", "'" )}" )
        validateNonEmpty

        val oe = task.objectEngine
        val oper = oe.objectOperationMap.getObjectOperation( operationName, jlodDef, args: _* )
        return oper.invokeOperation( this, args: _* )
    }

    override def toString = if ( jview != null ) jview.toString() else "*undefined*"

    /**
     * Validates that the LodDef is specified.
     */
    private def validateLodDef: Unit = {
        if ( jlodDef == null )
            throw new ZeidonException( "LOD name not established for this View" )
    }

    private def validateNonEmpty: Unit = {
        if ( jview == null )
            throw new ZeidonException( "View does not have a valid OI.  Did you forget to call activateEmpty()?" )
    }

    class LodChooser extends Dynamic {

        private def setLod( lodName: String ): View = {
            jlodDef = task.jtask.getApplication().getLodDef( task.jtask, lodName )
            if ( jview != null && jview.getLodDef() != jlodDef )
                throw new ZeidonException( "LodDef set by basedOnLod doesn't match view." )

            View.this
        }

        /**
         * This is called when the compiler doesn't recognize a method name.  This
         * is used to find the LOD name for basedOnLod
         */
        def selectDynamic( lodName: String ): View = setLod( lodName )
        //        def applyDynamic( lodName: String)(args: Any* ) = setLod( lodName )
    }
}

class VmlSyntaxFiller {}

object View {
    /**
     * Convert a Java View to a Scala View
     */
    implicit def jview2view( jview: com.quinsoft.zeidon.View ) = new com.quinsoft.zeidon.scala.View( jview )

    /**
     * Convert Scala View to Java View.
     */
    implicit def view2jview( view: com.quinsoft.zeidon.scala.View ) = view.jview

    val ON = new VmlSyntaxFiller
}