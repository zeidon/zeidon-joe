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
class QualBuilder private [scala] ( private [this]  val view: View,
                                    private [scala] val jlodDef: com.quinsoft.zeidon.objectdefinition.LodDef ) {

    private [this]  val jtask = view.task.jtask
    private [scala] val jqual = new com.quinsoft.zeidon.utils.QualificationBuilder( jtask )
    jqual.setLodDef( jlodDef )

    private [scala]  val entityQualBuilder = new EntityQualBuilder( this )

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
     * WHERE ( USER.ID = 100 OR USER.ID = 200 )
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
     *  val mUser = VIEW basedOn "mUser"
     *  mUser.buildQual( _.User.ID > 0 )
     *       .conditional(id != 0, _.and( _.User.ID < id ) )
     *       .activate()
     * }}}
     *
     * Generates the following SQL:
     * {{{
     * SELECT ID...
     * FROM  USER
     * WHERE USER.ID > 0 AND USER.ID < 10;
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
     * USER.USERNAME = 'Smith' AND ( USER.EMAILUSERNAME = 'Tom' OR USER.EMAILUSERNAME = 'Harry' ) ;
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

    /**
     * Initialize qualification with a series of AND predicates WITHOUT adding a
     * conjunction to the existing qualification.  This
     * is intended to be used when building qualification over a series of steps.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      val qual = mUser.buildQual()
     *      if ( <true> )
     *          qual.all( _.User.ID > 100, _.User.ID < 200 )
     *      else
     *          qual.all( _.User.ID > 400, _.User.ID < 500 )
     *
     *      qual.activate
     * }}}
     *
     * Generates the following SQL:
     * {{{
     * SELECT ID...
     * FROM  USER
     * WHERE ( USER.ID > 100 AND USER.ID < 200 )
     * }}}
     */
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

    /**
     * Add a group of qualifications separated by 'AND' after adding an 'OR'
     * conjunction to the existing qualification.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.buildQual( _.User.ID = 400 )
     *                 .orAll( _.User.ID = 500, _.User.UserName = "Smith" )
     *                 .activate
     * }}}
     *
     * Generates the following SQL:
     * {{{
     * SELECT ID...
     * FROM  USER
     * WHERE ( USER.ID = 400 OR ( USER.ID = 500 AND USER.USERNAME = 'Smith' ) );
     * }}}
     */
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

    /**
     * Activate only the root entities and ignore all children.  Entities loaded this
     * way usually can not be deleted because it will violate referential integrity.
     */
    def rootOnlyMultiple(): QualBuilder = {
        jqual.rootOnly().multipleRoots()
        this
    }

    /**
     * Activate only a single root entity (the first one in the result set) and
     * ignore all children.  Entities loaded this way usually can not be deleted
     * because it will violate referential integrity.
     */
    def rootOnly(): QualBuilder = {
        jqual.rootOnly()
        this
    }

    /**
     * Activate all roots that match the qualification (and their children).
     */
    def multiple(): QualBuilder = {
        jqual.multipleRoots()
        this
    }

    /**
     * Limit the number of entities that are loaded.  If this is part of a restrict()
     * qualification clause then this will limit the number of child entities loaded
     * under its parent.  I.e. this qualification
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.buildQual( _.User.ID = 400 )
     *                  .or( _.User.ID = 500 )
     *                  .restrict( _.UserGroup )
     *                      .limitCountTo( 5 )
     *                 .activate
     * }}}
     *
     * Could activate a total of 10 UserGroup entities, 5 for each parent.
     */
    def limitCountTo( limit: Integer ): QualBuilder = {
        jqual.limitCountTo(limit)
        this
    }

    /**
     * Activate all entites that are normally lazy loaded.
     */
    def includeLazy(): QualBuilder = {
        jqual.setFlag( ActivateFlags.fINCLUDE_LAZYLOAD )
        this
    }

    /**
     * Activates children that match the qualification; i.e. this restricts what
     * children are loaded.
     *
     * For example, this qualifcation:
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.buildQual( _.User.ID = 400 )
     *                  .restrict( _.UserGroup )
     *                      .to( _.UserGroup.GroupName like "P%" )
     *                 .activate
     * }}}
     *
     * Generates the following SQL:
     * {{{
     * SELECT ID...
     * FROM  USER
     * WHERE ( USER.ID = 400  );
     * ...
     * SELECT USERGROUP.ID...
     * FROM  MM_USERGROUP_CONTANSMMBR_USER JOIN
     *       USERGROUP ON USERGROUP.ID = MM_USERGROUP_CONTANSMMBR_USER.FK_ID_USERGROUP
     * WHERE (MM_USERGROUP_CONTANSMMBR_USER.FK_ID_USER = 10 AND
     *       (USERGROUP.GROUPNAME LIKE 'P%') );
     * }}}
     *
     * Note: depending on how the LOD is defined this could prevent child entities
     * from being deleted because that would violate referential integrity.
     */
    def restrict( restrictTo: (EntitySelector) => com.quinsoft.zeidon.objectdefinition.EntityDef ) = restricting( restrictTo )

    /**
     * Synonym for restrict( ... ) method.
     */
    def restricting( restrictTo: (EntitySelector) => com.quinsoft.zeidon.objectdefinition.EntityDef ): QualBuilder = {
        val jentityDef = restrictTo( new EntitySelector )
        jqual.restricting( jentityDef.getName() )
        this
    }

    /**
     * Exclude the specified entity from the activate.
     *
     * Note: depending on how the LOD is defined this could prevent child entities
     * from being deleted because that would violate referential integrity.
     */
    def exclude( excludeEntity: (EntitySelector) => com.quinsoft.zeidon.objectdefinition.EntityDef ): QualBuilder = {
        val jentityDef = excludeEntity( new EntitySelector )
        jqual.excludeEntity( jentityDef.getName() )
        this
    }

    /**
     * Used with the restrict() method for adding qualification on child entities.
     *
     * See restrict() for more information.
     */
    def to( addQual: (EntityQualBuilder) => QualBuilder ): QualBuilder = {
        addQual( entityQualBuilder )
    }

    /**
     * Caches the resulting view in the specified task.  The next time a view is
     * activated using this cache name the cached view will be returned and there is
     * no DB activity.
     * {{{
     *      val id = 10
     *      val mUser = VIEW basedOn "mUser"
     *
     *      // This call loads the User from the DB.
     *      mUser.buildQual( _.User.ID = id ).cachedAs( "mUser" + id ).activate
     *
     *      // This call loads the User from the cache and doesn't access the DB.
     *      mUser.buildQual( _.User.ID = id ).cachedAs( "mUser" + id ).activate
     * }}}
     *
     * The view is cached by using the cache name to name the view in the task.
     * To clear the view simply drop the view name.
     *
     * Note that the cached view is dropped if its owning task is dropped.
     */
    def cachedAs( cacheName: String, qualtask: com.quinsoft.zeidon.Task = jtask ): QualBuilder = {
        jqual.cachedAs( cacheName, qualtask )
        this
    }

    /**
     * Caches the resulting view in the SYSTEM task.  The next time a view is
     * activated using this cache name the cached view will be returned and there is
     * no DB activity.  Caching at the system level means the cached view will never
     * be dropped (unless it's done explicitly).
     * {{{
     *      val id = 10
     *      val mUser = VIEW basedOn "mUser"
     *
     *      // This call loads the User from the DB.
     *      mUser.buildQual( _.User.ID = id ).systemCachedAs( "mUser" + id ).activate
     *
     *      // This call loads the User from the cache and doesn't access the DB.
     *      mUser.buildQual( _.User.ID = id ).systemCachedAs( "mUser" + id ).activate
     * }}}
     *
     * The view is cached by using the cache name to name the view in the system task.
     * To clear the view simply drop the view name.
     */
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
     *
     * An asynchronous activate will create a view that will block the first
     * time it is used until the activate finishes.  The following will
     * activate two views at the same time.
     * {{{
     *      // Starts an activate on mUser but returns immediately.
     *      val mUser1 = VIEW basedOn "mUser"
     *      mUser1.buildQual( _.User.ID = 1 ).asynchronous.activate
     *
     *      // Starts another activate on mUser but returns immediately.
     *      val mUser2 = VIEW basedOn "mUser"
     *      mUser2.buildQual( _.User.ID = 2 ).asynchronous.activate
     *
     *      // Following blocks until mUser1 finishes activating.
     *      println( "mUser1.User.ID = " + mUser1.User.ID.toString )
     * }}}
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

    def in ( values: List[Any] ): QualBuilder = {
        jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), "IN", null )
        values.foreach( value => jqual.newEntityKey( value.toString() ) )
        return qualBuilder
    }

    def in ( values: Any* ): QualBuilder = {
        jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), "IN", null )
        values.foreach( value => jqual.newEntityKey( value.toString() ) )
        return qualBuilder
    }

    def notIn ( values: Any* ): QualBuilder = {
        jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), "NOT IN", null )
        values.foreach( value => jqual.newEntityKey( value.toString() ) )
        return qualBuilder
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
