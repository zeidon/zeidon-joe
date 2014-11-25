/**
 *
 */
package com.quinsoft.zeidon.scala

import scala.language.dynamics
import com.quinsoft.zeidon._

/**
 * Used to build qualification.
 *
 * @author dgc
 *
 */
class QualBuilder( private val view: View,
                   private val jlodDef: com.quinsoft.zeidon.objectdefinition.LodDef )
            extends Dynamic {

    val jtask = view.jtask
    val jqual = new com.quinsoft.zeidon.utils.QualificationBuilder( jtask )
    jqual.setLodDef( jlodDef )

    def selectDynamic(entityName: String): EntityQualBuilder = {
        val jentityDef = jlodDef.getEntityDef(entityName)
        new EntityQualBuilder( this, jentityDef )
    }

    def and( addQual: (QualBuilder) => QualBuilder ): QualBuilder = {
        jqual.addAttribQual( "AND" )
        addQual( this )
    }

    def any( addQual: (QualBuilder) => QualBuilder* ): QualBuilder = {
        jqual.addAttribQual( "(" )

        val iter = addQual.iterator
        while ( iter.hasNext ) {
            iter.next()( this )
            if ( iter.hasNext )
                jqual.addAttribQual( "OR" )
        }

        jqual.addAttribQual( ")" )
        return this
    }

    def andAny( addQual: (QualBuilder) => QualBuilder* ): QualBuilder = {
        jqual.addAttribQual( "AND" )
        jqual.addAttribQual( "(" )

        val iter = addQual.iterator
        while ( iter.hasNext ) {
            iter.next()( this )
            if ( iter.hasNext )
                jqual.addAttribQual( "OR" )
        }

        jqual.addAttribQual( ")" )
        return this
    }

    def or( addQual: (QualBuilder) => QualBuilder ): QualBuilder = {
        jqual.addAttribQual( "OR" )
        addQual( this )
    }

    def all( addQual: (QualBuilder) => QualBuilder* ): QualBuilder = {
        jqual.addAttribQual( "(" )

        val iter = addQual.iterator
        while ( iter.hasNext ) {
            iter.next()( this )
            if ( iter.hasNext )
                jqual.addAttribQual( "AND" )
        }

        jqual.addAttribQual( ")" )
        return this
    }

    def orAll( addQual: (QualBuilder) => QualBuilder* ): QualBuilder = {
        jqual.addAttribQual( "OR" )
        jqual.addAttribQual( "(" )

        val iter = addQual.iterator
        while ( iter.hasNext ) {
            iter.next()( this )
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

    def to( addQual: (QualBuilder) => QualBuilder ): QualBuilder = {
        addQual( this )
    }

    def cachedAs( cacheName: String, qualtask: com.quinsoft.zeidon.Task = jtask ): QualBuilder = {
        jqual.cachedAs( cacheName, qualtask )
        this
    }

    def systemCachedAs( cacheName: String ): QualBuilder = {
        jqual.cachedAs( cacheName, jtask.getSystemTask() )
        this
    }

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

    def activate(): View = {
        view.jview = jqual.activate()
        return view
    }

    /**
     * Builder for setting entity values.
     */
    class EntityQualBuilder( val qualBuilder: QualBuilder,
                             val jentityDef: com.quinsoft.zeidon.objectdefinition.EntityDef )
            extends Dynamic {

        var jattributeDef: com.quinsoft.zeidon.objectdefinition.AttributeDef = null

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

        def selectDynamic( attributeName: String): EntityQualBuilder = {
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

    class EntitySelector() extends Dynamic {
        def selectDynamic( entityName: String ): com.quinsoft.zeidon.objectdefinition.EntityDef = {
            jlodDef.getEntityDef( entityName )
        }
    }
}