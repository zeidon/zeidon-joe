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
class QualBuilder(private val view: View,
                   private val jviewOd: com.quinsoft.zeidon.objectdefinition.ViewOd )
            extends Dynamic {

    val jtask = view.jtask
    val jqual = new com.quinsoft.zeidon.utils.QualificationBuilder( jtask )
    jqual.setViewOd( jviewOd )

    def selectDynamic(entityName: String): EntityQualBuilder = {
        val jviewEntity = jviewOd.getViewEntity(entityName)
        new EntityQualBuilder( this, jviewEntity )
    }

    def and( addQual: (QualBuilder) => QualBuilder ): QualBuilder = {
        jqual.addAttribQual( "AND" )
        addQual( this )
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

    def includeLazy(): QualBuilder = {
        jqual.setFlag( ActivateFlags.fINCLUDE_LAZYLOAD )
        this
    }

    def restricting( restrictTo: (EntitySelector) => com.quinsoft.zeidon.objectdefinition.ViewEntity ): QualBuilder = {
        val entity = restrictTo( new EntitySelector )
        jqual.restricting( entity.getName() )
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

    def activate(): Integer = {
        jqual.getView().logObjectInstance()
        view.jview = jqual.activate()
        return 0
    }

    /**
     * Builder for setting entity values.
     */
    class EntityQualBuilder( val qualBuilder: QualBuilder,
                             val jviewEntity: com.quinsoft.zeidon.objectdefinition.ViewEntity )
            extends Dynamic {

        var jviewAttribute: com.quinsoft.zeidon.objectdefinition.ViewAttribute = null

        def > ( value: Any ): QualBuilder = {
            jqual.addAttribQual(jviewEntity.getName(), jviewAttribute.getName(), ">", value )
            println( "> " + value.toString)
            return qualBuilder
        }

        def >= ( value: Any ): QualBuilder = {
            jqual.addAttribQual(jviewEntity.getName(), jviewAttribute.getName(), ">=", value )
            return qualBuilder
        }

        def < ( value: Any ): QualBuilder = {
            jqual.addAttribQual(jviewEntity.getName(), jviewAttribute.getName(), "<", value )
            return qualBuilder
        }

        def <= ( value: Any ): QualBuilder = {
            jqual.addAttribQual(jviewEntity.getName(), jviewAttribute.getName(), "<=", value )
            return qualBuilder
        }

        /*
        def exists: QualBuilder = {
           return qualBuilder
        }
         */

        def selectDynamic( attributeName: String): EntityQualBuilder = {
            jviewAttribute = jviewEntity.getAttribute( attributeName )
            return this
        }

        def applyDynamic( attributeName: String)(args: Any*): QualBuilder = {
            println( s"method '$attributeName' called with arguments ${args.mkString("'", "', '", "'")}" )
            jviewAttribute = jviewEntity.getAttribute( attributeName )
            return qualBuilder
        }

        def updateDynamic( attributeName: String)(value: Any): QualBuilder = {
            jviewAttribute = jviewEntity.getAttribute( attributeName )
            jqual.addAttribQual(jviewEntity.getName(), jviewAttribute.getName(), "=", value )
            println( "Adding " + jviewAttribute + " = " + value)
            return qualBuilder
        }
    }

    class EntitySelector() extends Dynamic {
        def selectDynamic( entityName: String ): com.quinsoft.zeidon.objectdefinition.ViewEntity = {
            jviewOd.getViewEntity( entityName )
        }
    }
}