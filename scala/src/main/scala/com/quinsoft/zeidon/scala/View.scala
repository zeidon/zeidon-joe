/**
  * This file is part of the Zeidon Java Object Engine (Zeidon JOE).
  *
  * Zeidon JOE is free software: you can redistribute it and/or modify
  * it under the terms of the GNU Lesser General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * Zeidon JOE is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public License
  * along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.
  *
  * Copyright 2009-2015 QuinSoft
  */
package com.quinsoft.zeidon.scala

import scala.language.dynamics
import com.quinsoft.zeidon.ActivateFlags
import com.quinsoft.zeidon.ZeidonException
import com.quinsoft.zeidon.objectdefinition.EntityDef
import com.quinsoft.zeidon.objectdefinition.LodDef
import com.quinsoft.zeidon.SelectSet
import com.quinsoft.zeidon.SubobjectValidationException
import com.quinsoft.zeidon.Task
import scala.collection.concurrent.TrieMap
import org.apache.commons.lang3.StringUtils

/**
 * A Scala wrapper for the JOE View.  This object uses dynamic methods that allows
 * users to write code using VML-like view.entity.attribute syntax.
 */
case class View( val task: Task ) extends Dynamic {

    /**
     * The underlying EntityDef for this View.  May be null.
     */
    private var jlodDef: LodDef = null

    /**
     * The underlying Java view for this Scala View.  May be null.
     */
    var jview: com.quinsoft.zeidon.View = null

    def this( jv: com.quinsoft.zeidon.View ) = {
        this( jv.getTask() )
        jlodDef = jv.getLodDef()
        jview = jv
    }

    def this( view: com.quinsoft.zeidon.scala.View ) = this( view.jview )
    def this( task: com.quinsoft.zeidon.TaskQualification ) = this( task.getTask() )

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
            jlodDef = task.getApplication().getLodDef( task, lodName )
        else
            jlodDef = task.getApplication( appName ).getLodDef( task, lodName )

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
    def duplicate = { validateNonNull; new View( jview.newView ) }

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
     * Validates the OI by running accept() on all the entity instances.
     */
    def validate( throwException: Boolean = true ) = {
        val validateExceptions = jview.validateOi()
        if ( validateExceptions != null && throwException )
            throw new SubobjectValidationException( validateExceptions )

        validateExceptions
    }

    /**
     * Creates an empty OI for this view.  The LOD must have been previously
     * specified with basedOn.
     * {{{
     *      val mUser = new View( task ) basedOn "mUser"
     *      mUser activateEmpty()
     * }}}
     */
    def activateEmpty() = {
        validateLodDef
        jview = task.activateEmptyObjectInstance( jlodDef )
        this
    }

    /**
     * Activates an OI from the DB with simple qualification.
     * Returns 'this' for convenience.
     *
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID = 490 )
     * }}}
     */
    def activateWhere( addQual: ( EntityQualBuilder ) => QualificationTerminator ): View = {
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
     * A typical use case:
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.buildQual( _.User.ID = 490 )
     *                  .or( _.User.ID = 491 )
     *                  .asynchronous
     *                  .activate
     * }}}
     */
    def buildQual( initialQual: ( EntityQualBuilder ) => QualificationTerminator ): QualBuilder = {
        val builder = buildQual()
        builder.callAddQual( initialQual )
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
        validateNonNull
        val cloned = jview.activateOiFromOi( ActivateFlags.fSINGLE )
        new View( cloned )
    }

    /**
     * Creates a new view, potentially with a different owning task.
     */
    def newView( task: Task = this.task ) = { validateNonNull; new View( task ).from( this ) }

    /**
     * Sets the name of this view in its owning task.
     * Note: this will NOT drop other names this view might have.  I.e. a view
     * can have multiple names.
     *
     * {{{
     * val myView = new View( task ) basedOn MyLOD
     * myView.activateEmpty
     * myView.name( "This Is a Name" )
     * }}}
     *
     * This is a synonym for view.name = "..."
     */
    def name( viewName: String ): View = {
        validateNonNull;
        jview.setName( viewName );
        this
    }

    /**
     * Drops the specified name for this view.
     */
    def dropName( viewName: String ) = {
        validateNonNull
        jview.dropNameForView( viewName )
        this
    }

    /**
     * Drops the view and all its names.
     */
    def drop() = {
        if ( jview != null )
            jview.drop
        this
    }

    /**
     * Sets the name of the view as an attribute.
     * Note: this will NOT drop other names this view might have.  I.e. a view
     * can have multiple names.
     *
     * {{{
     * val myView = new View( task ) basedOn MyLOD
     * myView.activateEmpty
     * myView.name = "This Is a Name"
     * }}}
     *
     * This is a synonym for view.name( "..." )
     */
    def name_= ( viewName: String ):Unit = name( viewName )

    def assert = new AssertView( this )

    /**
     * Returns the name of the LOD associated with this View or "*not specified*"
     * if it hasn't been specified.
     */
    def lodName = if ( jlodDef == null ) "*not specified*" else jlodDef.getName

    /**
     * Returns true if the OI is empty (i.e. has no root entities).
     */
    def isEmpty = { validateNonNull; jview.isEmpty() }

    /**
     * Returns true if no OI has been associated with this view.
     */
    def isNull = jview == null

    /**
     * Writes the entire OI to the log file.
     */
    def logObjectInstance = { validateNonNull; jview.logObjectInstance() }

    /**
     * Writes the entire OI to the log file.
     */
    def logOi = logObjectInstance

    /**
     * Returns the options that were used to activate this OI.
     */
    def activateOptions = { validateNonNull; jview.getActivateOptions() }

    def getSelectSet( name: String = null ) = {
        if ( name == null )
            jview.createSelectSet()
        else
            jview.getSelectSet( name )
    }

    /**
     * Returns a serializer that can be used to serialize this OI.
     *
     * To serialize the OI as JSON to a file:
     * {{{
     * myView.serializeOi.toFile( "myfile.json" )
     * }}}
     *
     * To serialize the OI as XML to a string.
     * {{{
     * val str = myView.serializeOi.asXml.toString
     * }}}
     */
    def serializeOi = { validateNonNull; jview.serializeOi() }

    /**
     * Creates a SelectSet for this View.
     */
    def createSelectSet() = { validateNonNull; jview.createSelectSet() }

    /**
     * Returns an EntityCursor for the entity specified by entityDef.
     */
    def cursor( entityDef: EntityDef ) = {
        validateNonNull
        new EntityCursor( this, jview.cursor( entityDef ) )
    }

    /**
     * Returns an EntityCursor for the entity specified by entityDef.
     */
    def cursor( entityName: String ) = {
        validateNonNull
        new EntityCursor( this, jview.cursor( entityName ) )
    }

    /**
     * Returns the cursor of the root entity.  This allows code to generically
     * refer to the root entity of a View.
     *
     * The following example counts the number of root entities for
     * any myView regardless of the LOD.
     *
     * {{{
     *      val count = myView.root.count
     * }}}
     */
    def root = {
        validateNonNull
        new EntityCursor( this, jview.cursor( jlodDef.getRoot() ) )
    }

    /**
     * This is called when the compiler doesn't recognize a method name.  This
     * is used to find the entity cursor for a view.
     *
     * Not intended to be called directly by user code.
     */
    def selectDynamic( entityName: String ): EntityCursor = {
        validateNonNull

        val jentityDef =  EntitySelector.getEntityDef( jlodDef, entityName )
        val jcur = jview.cursor( jentityDef )
        new EntityCursor( this, jcur )
    }

    /**
     * Called dynamically to process a Object Operation.
     *
     * Not intended to be called directly by user code.
     */
    def applyDynamic( operationName: String )( args: AnyRef* ): ObjectOperationResult = {
//        println( s"method '$operationName' called with arguments ${args.mkString( "'", "', '", "'" )}" )
        validateNonNull

        val oe = task.getObjectEngine
        var operMap = oe.getCacheMap().getOrCreate( classOf[ObjectOperationMap] )
        val oper = operMap.getObjectOperation( operationName, jlodDef, args: _* )
        val value = oper.invokeOperation( this, args: _* )
        ObjectOperationResult( value )
    }

    override def toString = {
       if ( jview != null )
           jview.toString()
       else
       if ( jlodDef != null )
           "null " + jlodDef.getName()
       else
           "*undefined*"
    }

    /**
     * Validates that the LodDef is specified.
     */
    private def validateLodDef: Unit = {
        if ( jlodDef == null )
            throw new ZeidonException( "LOD name not established for this View" )
    }

    private def validateNonNull: Unit = {
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
