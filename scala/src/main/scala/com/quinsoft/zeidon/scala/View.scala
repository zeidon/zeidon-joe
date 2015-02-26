/**
 *
 */
package com.quinsoft.zeidon.scala

import scala.language.dynamics

import com.quinsoft.zeidon.ActivateFlags
import com.quinsoft.zeidon.ZeidonException
import com.quinsoft.zeidon.objectdefinition.EntityDef
import com.quinsoft.zeidon.objectdefinition.LodDef

/**
 * A Scala wrapper for the JOE View.  This object uses dynamic methods that allows
 * users to write code using VML-like view.entity.attribute syntax.
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

    /**
     * Sets the LOD definition for this View.
     */
    def basedOn( viewDef: ViewDef ) = setLod( viewDef.lodName , viewDef.applicationName  )

    /**
     * Sets the LOD definition for this View.
     */
    def basedOn( lodName: String ) = setLod( lodName )
    def basedOn( lodDef : LodDef ) = { jlodDef = lodDef; this }

    /**
     * Sets the LOD definition for this view as BASED ON LOD lod-name
     */
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

    /**
     * Returns the LodDef for this view.  MAY BE NULL if the View was created without
     * basedOn.
     */
    def lodDef = jlodDef

    /**
     * Sets the jview and lodDef from srcView.
     */
    private def from( srcView: View ) = {
        jview = srcView.jview.newView()
        jlodDef = jview.getLodDef()
        this
    }

    /**
     * Creates a new View that has the same cursor positions as the current view.
     */
    def duplicate = { validateNonEmpty; new View( jview.newView ) }

    /**
     * Set the cursors from sourceView to 'this'.
     */
    def copyCursors( sourceView: View ) = {
        if ( jview == null )
            throw new ZeidonException( "View has no OI" )

        jview.copyCursors( sourceView.jview )
        this
    }

    /**
     * Creates an empty OI for this view.  The LOD must have been previously
     * specified with basedOn.
     */
    def activateEmpty() = {
        validateLodDef
        jview = jtask.activateEmptyObjectInstance( jlodDef )
        this
    }

    /**
     * Activates an OI from the DB with simple qualification.
     *
     * Returns 'this' for convenience.
     */
    def activateWhere( addQual: ( EntityQualBuilder ) => Unit ): View = {
        validateLodDef
        val builder = new QualBuilder( this, jlodDef )
        addQual( builder.entityQualBuilder )
        builder.activate
        this
    }

    /**
     * Activates all data from the DB for this LOD.  I.e. activates without
     * qualification.  Use carefully.
     *
     * Returns 'this' for convenience.
     */
    def activateAll(): View = {
        validateLodDef
        val builder = new QualBuilder( this, jlodDef )
        builder.activate
        this
    }

    /**
     * Creates a QualBuilder for creating activate qualification.
     */
    def buildQual( addQual: ( EntityQualBuilder ) => Unit ): QualBuilder = {
        val builder = buildQual()
        addQual( builder.entityQualBuilder )
        builder
    }

    /**
     * Creates a QualBuilder for creating activate qualification.
     */
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
     * Sets the name of this view in its owning task.
     */
    def name( viewName: String ) = { validateNonEmpty; jview.setName( viewName ) }

    def assert = new AssertView( this )

    /**
     * Returns the name of the LOD associated with this View or "*not specified*"
     * if it hasn't been specified.
     */
    def lodName = if ( jlodDef == null ) "*not specified*" else jlodDef.getName

    /**
     * Returns true if the OI is empty (i.e. has no root entities).
     */
    def isEmpty = { validateNonEmpty; jview.isEmpty() }

    /**
     * Writes the entire OI to the log file.
     */
    def logObjectInstance = { validateNonEmpty; jview.logObjectInstance() }

    /**
     * Returns the options that were used to activate this OI.
     */
    def activateOptions = { validateNonEmpty; jview.getActivateOptions() }

    /**
     * Returns a serializer that can be used to serialize this OI.
     */
    def serializeOi = { validateNonEmpty; jview.serializeOi() }

    /**
     * Returns a deserializer for that can be used to deserialize an OI.
     */
    override def deserializeOi = { validateNonEmpty; jview.deserializeOi() }

    /**
     * Creates a SelectSet for this View.
     */
    def createSelectSet() = { validateNonEmpty; jview.createSelectSet() }

    /**
     * Returns an EntityCursor for the entity specified by entityDef.
     */
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
     *
     * Not intended to be called directly by user code.
     */
    def selectDynamic( entityName: String ): EntityCursor = {
        validateNonEmpty

        val jentityDef = jlodDef.getEntityDef( entityName )
        val jcur = jview.cursor( jentityDef )
        new EntityCursor( this, jcur )
    }

    /**
     * Called dynamically to process a Object Operation.
     *
     * Not intended to be called directly by user code.
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
}

/**
 * A class who's sole purpose is to allow Scala syntax to mirror VML syntax.  For example, this
 * is used to allow "VIEW BASED ON LOD lod-name" syntax.
 */
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