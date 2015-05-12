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
     * Add qualification after adding an 'AND' conjunction to the existing qualification.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.buildQual( _.User.ID > 400 )
     *                 .and( _.User.ID < 500 )
     *                 .activate
     * }}}
     *
     * Generates the following SQL:
     * {{{
     * SELECT ID...
     * FROM  USER
     * WHERE USER.ID > 400 AND USER.ID < 500;
     * }}}
     */
    def and( addQual: (EntityQualBuilder) => Unit ): QualBuilder = {
        jqual.addAttribQual( "AND" )
        addQual( entityQualBuilder )
        this
    }

    /**
     * Initialize qualification with a series of OR predicates WITHOUT adding a
     * conjunction to the existing qualification.  This
     * is intended to be used when building qualification over a series of steps.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      val qual = mUser.buildQual()
     *      if ( <true> )
     *          qual.any( _.User.ID = 100, _.User.ID = 200 )
     *      else
     *          qual.any( _.User.ID = 400, _.User.ID = 500 )
     *
     *      qual.activate
     * }}}
     *
     * Generates the following SQL:
     * {{{
     * SELECT ID...
     * FROM  USER
     * WHERE ( z_USER.ID = 100 OR z_USER.ID = 200 )
     * }}}
     */
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

    /**
     * EXPERIMENTAL
     *
     * Add qualification if a predicate evaluates to true.
     *
     * {{{
     *  val id = 10
        mUser.buildQual( _.User.ID > 0 )
             .conditional(id != 0, _.and(  _.User.ID < id ) )
             .activate()
     * }}}
     *
     * Generates the following SQL:
     * {{{
     * SELECT ID...
     * FROM  USER
     * WHERE z_USER.ID > 0 AND z_USER.ID < 10;
     * }}}
     */
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
     *      mUser.buildQual( _.User.UserName = "Smith" )
     *              .andAny( _.User.eMailUserName = "Tom", _.User.eMailUserName = "Harry" )
     *              .activate
     * }}}
     *
     * Generates the following SQL:
     * {{{
     * SELECT ID...
     * FROM  USER
     * z_USER.USERNAME = 'Smith' AND ( z_USER.EMAILUSERNAME = 'Tom' OR z_USER.EMAILUSERNAME = 'Harry' ) ;
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

    /**
     * Add qualification after adding an 'OR' conjunction to the existing qualification.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.buildQual( _.User.ID = 400 )
     *                 .or( _.User.ID = 500 )
     *                 .activate
     * }}}
     *
     * Generates the following SQL:
     * {{{
     * SELECT ID...
     * FROM  USER
     * WHERE ( USER.ID = 400 OR USER.ID = 500 );
     * }}}
     */
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
        return addQual( ">", value )
    }

    def <> ( value: Any ): QualBuilder = {
        return addQual( "!=", value )
    }

    def >= ( value: Any ): QualBuilder = {
        return addQual( ">=", value )
    }

    def < ( value: Any ): QualBuilder = {
        return addQual( "<", value )
    }

    def <= ( value: Any ): QualBuilder = {
        return addQual( "<=", value )
    }

    def like ( value: String ): QualBuilder = {
        return addQual( "LIKE", value )
    }

    def exists: QualBuilder = {
        jqual.addAttribQualEntityExists( jentityDef.getName() )
       return qualBuilder
    }

    /**
     * This adds qualification on an attribute using another attribute from the same
     * query.  In SQL terms this qualifies a column on using a different column from
     * the same SELECT statement.
     */
    private def addQualFromAttributeBuilder( oper: String, any: Any ): QualBuilder = {
        val attr = any.asInstanceOf[AttributeQualBuilder]
        //TODO: We are using a short-term hack of prepending the entity.attr name with a '@'.
        // Once KZDBHQUA.XOD is updated to have a separate attribute for this value this
        // needs to change.
        val colName = "@" + attr.jattributeDef.getEntityDef().getName() + "." + attr.jattributeDef.getName();
        jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), oper, colName )
        return qualBuilder
    }

    private def addQual( oper: String, value: Any ): QualBuilder = {
        if ( value.isInstanceOf[AttributeQualBuilder] ) {
            addQualFromAttributeBuilder( oper, value )
        } else {
            jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), oper, value )
        }
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
//        println( s"method '$attributeName' called with argument ${value}" )
        jattributeDef = jentityDef.getAttribute( attributeName )
        if ( value.isInstanceOf[AttributeQualBuilder] ) {
            addQualFromAttributeBuilder( "=", value )
        } else {
            jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), "=", value )
        }
        return qualBuilder
    }
}
