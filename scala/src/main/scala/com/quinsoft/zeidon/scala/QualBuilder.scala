/**
 *
 */
package com.quinsoft.zeidon.scala

import scala.language.dynamics
import com.quinsoft.zeidon._

/**
 * Used to build qualification for Scala code and then activate the OI.  A typical
 * use case:
 * {{{
 *      val mUser = VIEW basedOn "mUser"
 *      mUser.buildQual( _.User.ID = 490 )
 *                  .or( _.User.ID = 491 )
 *                  .asynchronous
 *                  .activate
 * }}}
 *
 */
class QualBuilder( val view: View,
                   val jlodDef: com.quinsoft.zeidon.objectdefinition.LodDef ) {

    val jtask = view.task.jtask
    val jqual = new com.quinsoft.zeidon.utils.QualificationBuilder( jtask )
    jqual.setLodDef( jlodDef )
    val entityQualBuilder = new EntityQualBuilder( this )

    /**
     * Add qualification as 'and'.
     */
    def and( addQual: (EntityQualBuilder) => Unit ): QualBuilder = {
        jqual.addAttribQual( "AND" )
        addQual( entityQualBuilder )
        this
    }

    def any( addQual: (EntityQualBuilder) => Unit* ): QualBuilder = {
        jqual.addAttribQual( "(" )

        val iter = addQual.iterator
        while ( iter.hasNext ) {
            iter.next()( entityQualBuilder )
            if ( iter.hasNext )
                jqual.addAttribQual( "OR" )
        }

        jqual.addAttribQual( ")" )
        return this
    }

    def conditional( predicate: Boolean, addQual: (QualBuilder) => Unit ): QualBuilder = {
        if ( predicate )
            addQual( this )
            
        this
    }
    
    /**
     * Adds multiple attribute qualifications as a single group
     * of OR statements.
     *
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.buildQual( _.User.LastName = "Smith" )
     *              .andAny( _.User.FirstName = "Tom", _.User.FirstName = "Harry" )
     *              .activate
     * }}}
     *
     */
    def andAny( addQual: (EntityQualBuilder) => Unit* ): QualBuilder = {
        jqual.addAttribQual( "AND" )
        jqual.addAttribQual( "(" )

        val iter = addQual.iterator
        while ( iter.hasNext ) {
            iter.next()( entityQualBuilder )
            if ( iter.hasNext )
                jqual.addAttribQual( "OR" )
        }

        jqual.addAttribQual( ")" )
        return this
    }

    def or( addQual: (EntityQualBuilder) => Unit ): QualBuilder = {
        jqual.addAttribQual( "OR" )
        addQual( entityQualBuilder )
        this
    }

    def all( addQual: (EntityQualBuilder) => Unit* ): QualBuilder = {
        jqual.addAttribQual( "(" )

        val iter = addQual.iterator
        while ( iter.hasNext ) {
            iter.next()( entityQualBuilder )
            if ( iter.hasNext )
                jqual.addAttribQual( "AND" )
        }

        jqual.addAttribQual( ")" )
        return this
    }

    def orAll( addQual: (EntityQualBuilder) => Unit* ): QualBuilder = {
        jqual.addAttribQual( "OR" )
        jqual.addAttribQual( "(" )

        val iter = addQual.iterator
        while ( iter.hasNext ) {
            iter.next()( entityQualBuilder )
            if ( iter.hasNext )
                jqual.addAttribQual( "AND" )
        }

        jqual.addAttribQual( ")" )
        return this
    }

    def rootOnlyMultiple(): QualBuilder = {
        jqual.rootOnly().multipleRoots()
        this
    }

    def rootOnly(): QualBuilder = {
        jqual.rootOnly()
        this
    }

    def multiple(): QualBuilder = {
        jqual.multipleRoots()
        this
    }

    def limitCountTo( limit: Integer ): QualBuilder = {
        jqual.limitCountTo(limit)
        this
    }

    def includeLazy(): QualBuilder = {
        jqual.setFlag( ActivateFlags.fINCLUDE_LAZYLOAD )
        this
    }

    def restrict( restrictTo: (EntitySelector) => com.quinsoft.zeidon.objectdefinition.EntityDef ) = restricting( restrictTo )
    def restricting( restrictTo: (EntitySelector) => com.quinsoft.zeidon.objectdefinition.EntityDef ): QualBuilder = {
        val jentityDef = restrictTo( new EntitySelector )
        jqual.restricting( jentityDef.getName() )
        this
    }

    def exclude( excludeEntity: (EntitySelector) => com.quinsoft.zeidon.objectdefinition.EntityDef ): QualBuilder = {
        val jentityDef = excludeEntity( new EntitySelector )
        jqual.excludeEntity( jentityDef.getName() )
        this
    }

    def to( addQual: (EntityQualBuilder) => QualBuilder ): QualBuilder = {
        addQual( entityQualBuilder )
    }

    def cachedAs( cacheName: String, qualtask: com.quinsoft.zeidon.Task = jtask ): QualBuilder = {
        jqual.cachedAs( cacheName, qualtask )
        this
    }

    def systemCachedAs( cacheName: String ): QualBuilder = {
        jqual.cachedAs( cacheName, jtask.getSystemTask() )
        this
    }

    /**
     * Indicates that the resulting view (and backing OI) should be read-only.
     */
    def readOnly = {
        jqual.readOnly()
        this
    }

    /**
     * Indicates this activate should be done asynchronously.
     */
    def asynchronous = {
        jqual.asynchronous()
        this
    }

    /**
     * Performs the activate on the view using the specified qualification.
     *
     * Returns the view for convenience.
     */
    def activate(): View = {
        view.jview = jqual.activate()
        return view
    }


    class EntitySelector() extends Dynamic {
        def selectDynamic( entityName: String ): com.quinsoft.zeidon.objectdefinition.EntityDef = {
            jlodDef.getEntityDef( entityName )
        }
    }
}

class EntityQualBuilder( val qualBuilder: QualBuilder ) extends Dynamic {
    def selectDynamic(entityName: String): AttributeQualBuilder = {
        val jentityDef = qualBuilder.jlodDef.getEntityDef(entityName)
        new AttributeQualBuilder( qualBuilder, jentityDef )
    }
}

/**
 * Builder for setting attribute values for an entity.
 */
class AttributeQualBuilder( val qualBuilder: QualBuilder,
                            val jentityDef: com.quinsoft.zeidon.objectdefinition.EntityDef )
        extends Dynamic {

    var jattributeDef: com.quinsoft.zeidon.objectdefinition.AttributeDef = null
    val jqual = qualBuilder.jqual

    def > ( value: Any ): QualBuilder = {
        jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), ">", value )
        return qualBuilder
    }

    def <> ( value: Any ): QualBuilder = {
        jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), "!=", value )
        return qualBuilder
    }

    def >= ( value: Any ): QualBuilder = {
        jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), ">=", value )
        return qualBuilder
    }

    def < ( value: Any ): QualBuilder = {
        jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), "<", value )
        return qualBuilder
    }

    def <= ( value: Any ): QualBuilder = {
        jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), "<=", value )
        return qualBuilder
    }

    def exists: QualBuilder = {
        jqual.addAttribQualEntityExists( jentityDef.getName() )
       return qualBuilder
    }

    def selectDynamic( attributeName: String): AttributeQualBuilder = {
        jattributeDef = jentityDef.getAttribute( attributeName )
        return this
    }

    def applyDynamic( attributeName: String)(args: Any*): QualBuilder = {
        //println( s"method '$attributeName' called with arguments ${args.mkString("'", "', '", "'")}" )
        jattributeDef = jentityDef.getAttribute( attributeName )
        return qualBuilder
    }

    def updateDynamic( attributeName: String)(value: Any): QualBuilder = {
        jattributeDef = jentityDef.getAttribute( attributeName )
        jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), "=", value )
        return qualBuilder
    }
}
