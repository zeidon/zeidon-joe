/**
    This file is part of the Zeidon Java Object Engine (Zeidon JOE).

    Zeidon JOE is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Zeidon JOE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2009-2015 QuinSoft
 */
package com.quinsoft.zeidon.scala

import scala.language.dynamics
import scala.collection.JavaConversions.asScalaIterator

import com.quinsoft.zeidon._
import com.quinsoft.zeidon.objectdefinition.EntityDef
import com.quinsoft.zeidon.objectdefinition.LodDef
import org.apache.commons.lang3.StringUtils


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

    private [this]  val jtask = view.task
    private [scala] val jqual = new com.quinsoft.zeidon.utils.QualificationBuilder( jtask )
    jqual.setLodDef( jlodDef )

    private [scala] var jcurrentEntityDef = jlodDef.getRoot

    private [scala] val entityQualBuilder = new EntityQualBuilder( this )
    private var firstOperator = true
    private var totalRootCount: Integer = null

    private [scala] def callAddQual( addQual: (EntityQualBuilder) => QualificationTerminator ) = {
        firstOperator = false
        addQual( entityQualBuilder )
        this
    }

    private [scala] def callBuilder( builder: (QualBuilder) => QualBuilder ) = {
        firstOperator = false
        builder( this )
        this
    }

    /**
     * Returns the underlying qualification OI.
     */
    def qualOi: View = jqual.getView()
    
    /**
     * Create the qual object from a simple JSON string.
     *
     * Sample JSON:
     * {{{
     {
         "ID": [10, 11, 12],
         "MaidenName": "Alice",
         "$or": [ { "FirstName": "Bob" }, { "$and" : [ { "LastName": "Smith" }, { "MaidenName": { "$neq": "Smith" } } ] } ]
         "DateOfBirth": { "$gt": "01/01/2001", "<": "01/01/2010" }
         "$restricting": {
             "Address": {
                 "Description": { "<>": "" }
             }
         }
     }
     * }}}
     * @param json
     * @return this
     */
    def fromJson( jsonString: String ) : QualBuilder = {
        jqual.loadFromJsonString( jsonString )
        this    
    }
    
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
    def and( addQual: (EntityQualBuilder) => QualificationTerminator ): QualBuilder = {
        if ( ! firstOperator )
            jqual.addAttribQual( "AND" )

        callAddQual( addQual )
    }

    def where( addQual: (EntityQualBuilder) => QualificationTerminator ): QualBuilder = {
      and( addQual )
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
    def any( addQual: (EntityQualBuilder) => QualificationTerminator* ): QualBuilder = {
        jqual.addAttribQual( "(" )

        val iter = addQual.iterator
        while ( iter.hasNext ) {
            callAddQual( iter.next() )
            if ( iter.hasNext )
                jqual.addAttribQual( "OR" )
        }

        jqual.addAttribQual( ")" )
        return this
    }

    /**
     *
     * Add qualification if a predicate evaluates to true.
     *
     * {{{
     *  val id = 10
     *  val mUser = VIEW basedOn "mUser"
     *  mUser.buildQual( _.User.ID > 0 )
     *       .when(id != 0, _.and( _.User.ID < id ) )
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
    def when( predicate: Boolean,
              addQualIfTrue: (QualBuilder) => QualBuilder,
              addQualIfFalse: (QualBuilder) => QualBuilder = null ): QualBuilder = {

        if ( predicate )
            callBuilder( addQualIfTrue )
        else
        if ( addQualIfFalse != null )
            callBuilder( addQualIfFalse )

        this
    }

    private def ifNotNull( value: AnyRef ) = value match {
        case ei: EntityCursor => ! ei.isNull
        case ei: com.quinsoft.zeidon.EntityCursor  => ! ei.isNull
        case attr: AttributeInstance => ! attr.isNull
        case attr: com.quinsoft.zeidon.AttributeInstance => ! attr.isNull
        case str: String => ! StringUtils.isEmpty( str )
        case _ => value != null
    }

    def whenNotNull( value: AnyRef,
                     addQualIfTrue: (QualBuilder) => QualBuilder,
                     addQualIfFalse: (QualBuilder) => QualBuilder = null ): QualBuilder = {

        if ( ifNotNull( value ))
            callBuilder( addQualIfTrue )
        else
        if ( addQualIfFalse != null )
            callBuilder( addQualIfFalse )

        this
    }

    def whenNotBlank( value: String,
                      addQualIfTrue: (QualBuilder) => QualBuilder,
                      addQualIfFalse: (QualBuilder) => QualBuilder = null ): QualBuilder = {

        if ( ! StringUtils.isBlank( value ) )
            callBuilder( addQualIfTrue )
        else
        if ( addQualIfFalse != null )
            callBuilder( addQualIfFalse )

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
    def andAny( addQual: (EntityQualBuilder) => QualificationTerminator* ): QualBuilder = {
        if ( ! firstOperator )
            jqual.addAttribQual( "AND" )

        jqual.addAttribQual( "(" )

        val iter = addQual.iterator
        while ( iter.hasNext ) {
            callAddQual( iter.next() )
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
    def or( addQual: (EntityQualBuilder) => QualificationTerminator ): QualBuilder = {
        if ( ! firstOperator )
            jqual.addAttribQual( "OR" )

        callAddQual( addQual )
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
    def all( addQual: (EntityQualBuilder) => QualificationTerminator* ): QualBuilder = {
        jqual.addAttribQual( "(" )

        val iter = addQual.iterator
        while ( iter.hasNext ) {
            callAddQual( iter.next() )
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

    def andAll( addQual: (EntityQualBuilder) => QualificationTerminator* ): QualBuilder = {
        if ( ! firstOperator )
            jqual.addAttribQual( "AND" )

        jqual.addAttribQual( "(" )

        val iter = addQual.iterator
        while ( iter.hasNext ) {
            callAddQual( iter.next() )
            if ( iter.hasNext )
                jqual.addAttribQual( "AND" )
        }

        jqual.addAttribQual( ")" )
        return this
    }


    def orAll( addQual: (EntityQualBuilder) => QualificationTerminator* ): QualBuilder = {
        if ( ! firstOperator )
            jqual.addAttribQual( "OR" )

        jqual.addAttribQual( "(" )

        val iter = addQual.iterator
        while ( iter.hasNext ) {
            callAddQual( iter.next() )
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
     *                      .limit( 5 )
     *                 .activate
     * }}}
     *
     * Could activate a total of 10 UserGroup entities, 5 for each parent.
     */
    def limit( limit: Integer ): QualBuilder = {
        jqual.limitCountTo(limit)
        this
    }

    def withPaging( pageSize: Int, page: Int = 1, getTotalCount: Boolean = false ): QualBuilder = {
        jqual.getPagination().setPageSize(pageSize).setPageNumber( page ).setTotalCount( getTotalCount )
        this
    }

    /**
     * Activate all entities that are normally lazy loaded.
     */
    def includeLazy(): QualBuilder = {
        jqual.setFlag( ActivateFlags.fINCLUDE_LAZYLOAD )
        this
    }

    /**
     * Activates children that match the qualification; i.e. this restricts what
     * children are loaded.
     *
     * For example, this qualification:
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
    def restrict( restrictTo: (EntitySelector) => EntityDef ) = restricting( restrictTo )

    /**
     * Synonym for restrict( ... ) method.
     */
    def restricting( restrictTo: (EntitySelector) => EntityDef ): QualBuilder = {
        jcurrentEntityDef = restrictTo( new EntitySelector( jlodDef ) )
        jqual.restricting( jcurrentEntityDef.getName() )
        firstOperator = ! jqual.hasQualAttrib()
        this
    }

    /**
     * Specifies how entity is to be ordered when retrieved from DB.
     *
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.buildQual( _.User.ID > 400 )
     *             .orderBy( _.Name DESC )
     *             .activate
     * }}}
     *
     * Overrides ordering specified in the LOD.
     */
    def orderBy( selectAttr: (AttributeOrderByBuilder) => OrderByTerminator ) : QualBuilder = {
        val builder = new AttributeOrderByBuilder( this, jcurrentEntityDef )
        selectAttr( builder )
        jqual.addActivateOrdering( jcurrentEntityDef.getName, builder.jattributeDef.getName, builder.descending )
        this
    }

    /**
     * Specifies which entity the following qualification is for.  Used to build
     * qualification programatically.
     */
    def forEntity( restrictTo: (EntitySelector) => EntityDef ): QualBuilder = {
        jcurrentEntityDef = restrictTo( new EntitySelector( jlodDef ) )
        jqual.forEntity( jcurrentEntityDef.getName() )
        firstOperator = ! jqual.hasQualAttrib()
        this
    }

    /**
     * Exclude the specified entity from the activate.
     *
     * Note: depending on how the LOD is defined this could prevent child entities
     * from being deleted because that would violate referential integrity.
     */
    def exclude( excludeEntity: (EntitySelector) => EntityDef ): QualBuilder = {
        val jentityDef = excludeEntity( new EntitySelector( jlodDef ) )
        jqual.excludeEntity( jentityDef.getName() )
        this
    }

    /**
     * Used with the restrict() method for adding qualification on child entities.
     *
     * See restrict() for more information.
     */
    def to( addQual: (EntityQualBuilder) => QualificationTerminator ): QualBuilder = {
        // to() is really just an .and() but easier to understand in its context.
        and( addQual )
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

    def useSingleTransaction = {
        jqual.setSingleTransaction()
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
     * Indicates that the resulting view (and backing OI) should be read-only if 'readOnly'
     * is true.
     */
    def readOnly( readOnly: Boolean = true ) = {
        if ( readOnly )
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

    def setAsEntityCache() = {
        jqual.setAsEntityCache()
        this
    }

    /**
     * Performs the activate on the view using the specified qualification.
     *
     * Returns the view for convenience.
     */
    def activate(): View = {
        view.jview = jqual.activate()

        val paging = jqual.getPagination(false)
        if ( paging != null ) {
            // Are we getting the total count?
            if ( paging.isTotalCount() ) {
                totalRootCount = view.jview.getTotalRootCount
                paging.setTotalCount( false ) // Don't get it again.
            }
            else
            // We didn't load the root count as part of the activate.  If we
            // have it from a previous activate then set it.
            if ( totalRootCount != null )
                view.jview.setTotalRootCount( totalRootCount )
        }

        return view
    }

    /**
     * Performs the activate on the view using the specified qualification.
     *
     * Returns the view for convenience.
     */
    def activatePage( page: Int ): View = {
        val paging = jqual.getPagination(false)
        if ( paging == null )
            throw new ZeidonException( "Page size was not set")

        paging.setPageNumber( page )
        return activate()
    }

    /**
     * Overrides the oiSourceUrl config value that is retrieved from Zeidon configuration
     * (usually zeidon.ini).
     */
    def useOiSourceUrl( oiSourceUrl : String ) = {
        jqual.setOiSourceUrl( oiSourceUrl )
        this
    }

    def useConfigValue( key: String, value: String ) = {
        jqual.overrideConfigValue( key, value )
        this
    }

    def withRollingPagination( pageSize: Int = 1000 ) = {
        jqual.setPagination( new Pagination().setRollingPagination( true ).setPageSize( pageSize ) )
        this
    }
    
    /**
     * Sets the internal qualifcation OI from the seralized JSON string.
     */
    def setQualFromJson( jsonString: String ) = {
        jqual.loadFromSerializedString( jsonString )
        this
    }
}

class EntityQualBuilder private[scala] ( val qualBuilder: QualBuilder ) extends Dynamic {
    def selectDynamic(entityName: String): AttributeQualBuilder = {
        val jentityDef =  EntitySelector.getEntityDef( qualBuilder.jlodDef, entityName )
        new AttributeQualBuilder( qualBuilder, jentityDef )
    }

    /**
     * This allows code to specify the entity/attr by string.  E.g.
     * {{{
     *  view.buildQual( _.withName( "Entity.Attr" ) eq 10 )
     * }}}
     * is the same as:
     * {{{
     *  view.buildQual( _.Entity.Attr = 10 )
     * }}}
     *
     * Note that "eq" must be used instead of "=".  All other comparators can be
     * used as normal.
     */
    def withName( entityAttrName: String ): AttributeQualOperators = {
        val parts = entityAttrName.split("\\.")
        if ( parts.length == 2 ) {
            val jentityDef =  qualBuilder.jlodDef.getEntityDef( parts(0) )
            val ab = new AttributeQualBuilder( qualBuilder, jentityDef )
            ab.withName( parts(1) )
        }
        else
        {
            val ab = new AttributeQualBuilder( qualBuilder, qualBuilder.jcurrentEntityDef )
            ab.withName( parts(0) )
        }
    }
}

/**
 * Builder for setting attribute values for an entity.
 */
class AttributeQualBuilder( val qualBuilder: QualBuilder,
                            val jentityDef: EntityDef )
        extends Dynamic {

    var jattributeDef: com.quinsoft.zeidon.objectdefinition.AttributeDef = null
    val jqual = qualBuilder.jqual

    def exists : QualificationTerminator = {
        jqual.addAttribQualEntityExists( jentityDef.getName() )
        return QualBuilder.TERMINATOR
    }

    /**
     * This adds qualification on an attribute using another attribute from the same
     * query.  In SQL terms this qualifies a column on using a different column from
     * the same SELECT statement.
     */
    private[scala] def addQualFromAttributeBuilder( oper: String, any: Any ): QualBuilder = {
        val attr = any.asInstanceOf[AttributeQualOperators]
        jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), oper, null)
        jqual.setSourceAttribute( attr.jattributeDef )

        return qualBuilder
    }

    /**
     * This allows code to specify the attr by string.  E.g.
     * {{{
     *  view.buildQual( _.Entity.withName( "Attr" ) eq 10 )
     * }}}
     * is the same as:
     * {{{
     *  view.buildQual( _.Entity.Attr = 10 )
     * }}}
     *
     * Note that "eq" must be used instead of "=".  All other comparators can be
     * used as normal.
     */
    def withName( attributeName: String ): AttributeQualOperators = {
        jattributeDef = AbstractEntity.getAttributeDef( jentityDef, attributeName )
        if ( jattributeDef.isHidden() )
            throw new HiddenAttributeException( jattributeDef );

        return new AttributeQualOperators( this )
    }

    /**
     * Adds dynamic support for qualifying on an attribute.
     */
    def selectDynamic( attributeName: String): AttributeQualOperators = {
        withName( attributeName )
    }

//    def applyDynamic( attributeName: String)(args: Any*): QualBuilder = {
//        //println( s"method '$attributeName' called with arguments ${args.mkString("'", "', '", "'")}" )
//        jattributeDef = AbstractEntity.getAttributeDef( jentityDef, attributeName )
//        return qualBuilder
//    }
//
    /**
     * Adds support for qualifying an attribute using '=':
    * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID = 20 )
     * }}}
      */
    def updateDynamic( attributeName: String)(value: Any): QualificationTerminator = {
//        println( s"method '$attributeName' called with argument ${value}" )
        jattributeDef = AbstractEntity.getAttributeDef( jentityDef, attributeName )
        if ( jattributeDef.isHidden() )
            throw new HiddenAttributeException( jattributeDef );

        if ( value.isInstanceOf[AttributeQualOperators] ) {
            addQualFromAttributeBuilder( "=", value )
        } else {
            jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), "=", value )
        }
        return QualBuilder.TERMINATOR
    }
}

/**
 * Used to qualify on an attribute using an operator like '<>' or 'in'.
 */
class AttributeQualOperators private[scala] ( val attrQualBuilder: AttributeQualBuilder ) {
    val jqual = attrQualBuilder.jqual
    val qualBuilder = attrQualBuilder.qualBuilder
    val jentityDef = attrQualBuilder.jentityDef
    val jattributeDef = attrQualBuilder.jattributeDef

    var _not = false;

    override def equals(value: Any) = {
        throw new ZeidonException("Operators '==' and '!=' are invalid when building qualification.  Use '=' and '<>' instead" )
    }

    /**
     * Negates the operator that follows.  Can be used with any
     * attribute qualification.
     *
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID not() in (490, 491) )
     *
     *      mUser.activateWhere( _.User.ID not() > 490 )
     * }}}
     */
    def not(): AttributeQualOperators = {
         _not != _not
         this
    }

    /**
     * Returns the current value of _not but sets it to false.
     */
    private def checkNot = {
        if ( _not ) {
            _not = false
            true
        }
        else
            false
    }

    /**
     * Activates entities with attributes that are equal to the specified value.
     *
     * The value is converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID eq 490 )
     * }}}
     */
    def eq( value: Any ): QualificationTerminator = {
        if ( checkNot )
            return addQual( "<>", value )
        else
            return addQual( "=", value )
    }

    /**
     * Activates entities with attributes that are > than the specified value.
     *
     * The value is converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID > 490 )
     * }}}
     */
    def > ( value: Any ): QualificationTerminator = {
        if ( checkNot )
            return addQual( "<=", value )
        else
            return addQual( ">", value )
    }

    /**
     * Activates entities with attributes that are not equal than the specified value.
     *
     * The value is converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID <> 490 )
     * }}}
     */
    def <> ( value: Any ): QualificationTerminator = {
        if ( checkNot )
            return addQual( "=", value )
        else
            return addQual( "!=", value )
    }

    /**
     * Activates entities with attributes that are >= than the specified value.
     *
     * The value is converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID >= 490 )
     * }}}
     */
    def >= ( value: Any ): QualificationTerminator = {
        if ( checkNot )
            return addQual( "<", value )
        else
            return addQual( ">=", value )
    }

    /**
     * Activates entities with attributes that are < than the specified value.
     *
     * The value is converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID < 490 )
     * }}}
     */
    def < ( value: Any ): QualificationTerminator = {
        if ( checkNot )
            return addQual( ">=", value )
        else
            return addQual( "<", value )
    }

    /**
     * Activates entities with attributes that are <= than the specified value.
     *
     * The value is converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID <= 490 )
     * }}}
     */
    def <= ( value: Any ): QualificationTerminator = {
        if ( checkNot )
            return addQual( ">", value )
        else
            return addQual( "<=", value )
    }

    /**
     * Activates entities with attributes that are null.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.Name isNull )
     * }}}
     */
    def isNull(): QualificationTerminator = {
        if ( checkNot )
            return addQual( "!=", null )
        else
            return addQual( "=", null )
    }

    /**
     * Activates entities with attributes that are not null.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.Name isNotNull )
     * }}}
     */
    def isNotNull(): QualificationTerminator = {
        if ( checkNot )
            return addQual( "=", null )
        else
            return addQual( "!=", null )
    }

    /**
     * Uses SQL like to qualify activation.
     *
     * Note: The value is NOT converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.UserName like 'John%' )
     * }}}
     */
    def like ( value: String ): QualificationTerminator = {
        if ( checkNot )
            return addQual( "NOT LIKE", value )
        else
            return addQual( "LIKE", value )
    }

    /**
     * Activates entities with attributes that are in the list of
     * specified values.
     *
     * The values are converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID in (123, 456, 789) )
     * }}}
     */
    def in ( values: Any* ): QualificationTerminator = {
        if ( checkNot )
            return notIn( values )

        jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), "IN", null )
        addValues( values )
        return QualBuilder.TERMINATOR
    }

    /**
     * Activates entities with attributes that are in all the entities
     * in the specified cursor.
     *
     * The values are converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUserList = VIEW basedOn "mUser"...
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID in mUserList.User )
     * }}}
     */
    def in ( valueCursor: EntityCursor ): QualificationTerminator = {
        if ( checkNot )
            return notIn( valueCursor )

        // Get the list of attribute values.  Note that we use the attr NAME.
        // This allows the user to use a different target entity.
        val attrs = ( for ( e <- valueCursor ) yield e.getAttribute( jattributeDef.getName() ) )
        return in( attrs )
    }

    def in ( selectSet: SelectSet ): QualificationTerminator = {
        if ( checkNot )
            return notIn( selectSet )

        // Get the list of attribute values.  Note that we use the attr NAME.
        // This allows the user to use a different target entity.
        val attrs = for ( e <- selectSet.iterator() ) yield e.getAttribute( jattributeDef.getName() )

        return in( attrs )
    }

    /**
     * Activates entities with attributes that are not in than the list of
     * specified values.
     *
     * The values are converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID notIn (123, 456, 789) )
     * }}}
     */
    def notIn ( values: Any* ): QualificationTerminator = {
        if ( checkNot )
            return in( values )

        jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), "NOT IN", null )
        addValues( values )
        return QualBuilder.TERMINATOR
    }

    /**
     * Activates entities with attributes that are in all the entities
     * in the specified cursor.
     *
     * The values are converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUserList = VIEW basedOn "mUser"...
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID notIn mUserList.User )
     * }}}
     */
    def notIn ( valueCursor: EntityCursor ): QualificationTerminator = {
        if ( checkNot )
            return in( valueCursor )

        // Get the list of attribute values.  Note that we use the attr NAME.
        // This allows the user to use a different target entity.
        val attrs = ( for ( e <- valueCursor ) yield e.getAttribute( jattributeDef.getName() ) )
        return notIn( attrs )
    }

    def notIn ( selectSet: SelectSet ): QualificationTerminator = {
        if ( checkNot )
            return in( selectSet )

        // Get the list of attribute values.  Note that we use the attr NAME.
        // This allows the user to use a different target entity.
        val attrs = for ( e <- selectSet.iterator() ) yield e.getAttribute( jattributeDef.getName() )
        return notIn( attrs )
    }

    /**
     * Add the values in Seq[Any] to the IN/NOT IN clause.  If any of the values in the
     * Seq are also a SEQ we'll run through those individually as well (i.e. we'll
     * flatten 'values')
     */
    private def addValues(values: Seq[Any]): Unit = {
        values.foreach { value =>
            if ( value.isInstanceOf[Seq[_]] )
                addValues( value.asInstanceOf[Seq[_]] )
            else
                jqual.newEntityKey( value.toString() )
        }
    }

    private def between( values: Tuple2[Any,Any],
                         leftOper: String,
                         rightOper: String,
                         conjunction: String = "AND" ) = {
        jqual.addAttribQual( "(" )
        addQual( leftOper, values._1)
        jqual.addAttribQual( conjunction )
        addQual( rightOper, values._2)
        jqual.addAttribQual( ")" )
        qualBuilder
    }

    /**
     * Activates entities with attributes that are between the two values
     * specified in the tuple.  The attribute must be a < attribute < b
     *
     * The values are converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID >< (10, 20) )
     * }}}
     *
     * Generates the following SQL:
     * {{{
     * SELECT ID...
     * FROM  USER
     * WHERE USER.ID > 10 AND USER.ID < 20;
     * }}}
     */
    def >< (values: Tuple2[Any,Any]): QualBuilder = {
        if ( checkNot )
            return between( values, "<=", ">=", "OR" )
        else
            return between( values, ">", "<" )
    }

    /**
     * Activates entities with attributes that are between the two values
     * specified in the tuple.  The attribute must be a <= attribute < b
     *
     * The values are converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID >=< (10, 20) )
     * }}}
     *
     * Generates the following SQL:
     * {{{
     * SELECT ID...
     * FROM  USER
     * WHERE USER.ID >= 10 AND USER.ID < 20;
     * }}}
     */
    def <=< (values: Tuple2[Any,Any]): QualBuilder = {
        if ( checkNot )
            return between( values, "<", ">=", "OR" )
        else
            return between( values, ">=", "<" )
    }

    /**
     * Activates entities with attributes that are between the two values
     * specified in the tuple.  The attribute must be a <= attribute <= b
     *
     * The values are converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID >=<= (10, 20) )
     * }}}
     *
     * Generates the following SQL:
     * {{{
     * SELECT ID...
     * FROM  USER
     * WHERE USER.ID >= 10 AND USER.ID <= 20;
     * }}}
     */
    def >=<= (values: Tuple2[Any,Any]): QualBuilder = {
        if ( checkNot )
            return between( values, "<", ">", "OR" )
        else
            return between( values, ">=", "<=" )
    }

    /**
     * Activates entities with attributes that are between the two values
     * specified in the tuple.  The attribute must be a < attribute <= b
     *
     * The values are converted by domain processing before being added
     * to the SQL.
     * {{{
     *      val mUser = VIEW basedOn "mUser"
     *      mUser.activateWhere( _.User.ID ><= (10, 20) )
     * }}}
     *
     * Generates the following SQL:
     * {{{
     * SELECT ID...
     * FROM  USER
     * WHERE USER.ID > 10 AND USER.ID <= 20;
     * }}}
     */
    def ><= (values: Tuple2[Any,Any]): QualBuilder = {
        if ( checkNot )
            return between( values, "<=", ">", "OR" )
        else
            return between( values, ">", "<=" )
    }

    private[scala] def addQual( oper: String, value: Any ): QualificationTerminator = {
        if ( value.isInstanceOf[AttributeQualBuilder] ) {
            attrQualBuilder.addQualFromAttributeBuilder( oper, value )
        } else {
            jqual.addAttribQual(jentityDef.getName(), jattributeDef.getName(), oper, value )
        }

        return QualBuilder.TERMINATOR
    }
}

/**
 * Builder for setting ORDER BY attribute.
 */
class AttributeOrderByBuilder( val qualBuilder: QualBuilder,
                               val jentityDef: EntityDef )
        extends Dynamic {

    var jattributeDef: com.quinsoft.zeidon.objectdefinition.AttributeDef = null
    val jqual = qualBuilder.jqual
    var descending = false

    /**
     * Adds dynamic support for qualifying on an attribute.
     */
    def selectDynamic( attributeName: String ): OrderByTerminator = {
        jattributeDef = AbstractEntity.getAttributeDef( jentityDef, attributeName )
        if ( jattributeDef.isHidden() )
            throw new HiddenAttributeException( jattributeDef );
        new OrderByTerminator( this )
    }
}

/**
 * A class to indicate that qualification has been correctly specified.
 */
class QualificationTerminator {

}

/**
 * A class to indicate that qualification has been correctly specified.
 */
class OrderByTerminator( val builder : AttributeOrderByBuilder ) {
    def DESC = {
        builder.descending = true
        this
    }

    def ASC = {
        builder.descending = false
        this
    }
}

object QualBuilder {
    val TERMINATOR = new QualificationTerminator
}
