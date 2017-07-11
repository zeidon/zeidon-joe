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
package com.quinsoft.zeidon.dbhandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.CharSetUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.quinsoft.zeidon.AbstractOptionsConfiguration;
import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.ActivateOptions.ActivateOrder;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityCache;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.GenKeyHandler;
import com.quinsoft.zeidon.IncludeFlags;
import com.quinsoft.zeidon.Pagination;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.DataField;
import com.quinsoft.zeidon.objectdefinition.DataRecord;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LockingLevel;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.objectdefinition.RelField;
import com.quinsoft.zeidon.objectdefinition.RelRecord;
import com.quinsoft.zeidon.standardoe.NoOpPessimisticLockingHandler;
import com.quinsoft.zeidon.standardoe.OiRelinker;

/**
 * @author DG
 *
 */
public abstract class AbstractSqlHandler implements DbHandler, GenKeyHandler
{
    public static final PessimisticLockingHandler NOOP_PESSIMISTIC_LOCKING_HANDLER = new NoOpPessimisticLockingHandler();

    /**
     * These are the flags to use when creating an entity.  It prevents some
     * normal processing for occuring that we don't need when activating.
     */
    protected static final EnumSet<CreateEntityFlags> CREATE_FLAGS = EnumSet.of( CreateEntityFlags.fNO_SPAWNING,
                                                                                 CreateEntityFlags.fIGNORE_MAX_CARDINALITY,
                                                                                 CreateEntityFlags.fDONT_UPDATE_OI,
                                                                                 CreateEntityFlags.fDONT_INITIALIZE_ATTRIBUTES,
                                                                                 CreateEntityFlags.fDBHANDLER,
                                                                                 CreateEntityFlags.fIGNORE_PERMISSIONS );

    /**
     * Flags that are used to create EIs in the new OI from a cache of EIs.
     */
    protected static final EnumSet<IncludeFlags> INCLUDE_FLAGS = EnumSet.of(  IncludeFlags.FROM_ACTIVATE );

    private static final long COL_KEYS_ONLY = 0x00000001;
    private static final long COL_FULL_QUAL = 0x00000004;

    protected enum SqlCommand
    {
        INSERT, SELECT, UPDATE, DELETE;
    }

    protected final Task task;
    protected final Application application;
    protected final OiRelinker entityLinker;
    protected View qual;
    protected Map<EntityDef, QualEntity> qualMap;
    protected EnumSet<ActivateFlags> activateFlags;
    protected AbstractOptionsConfiguration options;

    /**
     * When activating, this is a cast of options as ActivateOptions.
     * I.e. activateOptions = (ActivateOptions) options.
     */
    protected ActivateOptions activateOptions;
    protected boolean closeTransaction = true;

    /**
     * Keeps a list of entities that are joinable for this activate.
     */
    protected Map<EntityDef, List<EntityDef>> joinableChildren = new HashMap<EntityDef, List<EntityDef>>();

    private final Map<EntityDef, SqlStatement> cachedStmts;

    /**
     * If true then the DB is generating the row keys.
     */
    private boolean isDbGenerateKeys = false;
    private Integer insertCount;
    private Boolean isBindAllValues;
    private Boolean ignoreJoins;

    /**
     * This is the list of instances that have been loaded.  It is limited to instances
     * that have children that can be loaded in one SELECT.
     *
     * The sub-map is keyed by the key value of the entity instance.
     */
    protected Map<EntityDef, Map<Object, EntityInstance>> loadedInstances;

    /**
     * This hash set keeps track of view entities that have been loaded.  It's used to
     * determine if we've already loaded the instances of a EntityDef.
     */
    protected Set<EntityDef> loadedViewEntities;

    /**
     * If a entityDef is in this set it can be loaded in a single select.
     */
    private Set<EntityDef> loadInOneSelect;
    private HashMap<EntityCache, View> entityCacheViewMap;

    /**
     * If set, this is the paging options for the activate.
     */
    private Pagination pagingOptions;

    protected AbstractSqlHandler( Task task, AbstractOptionsConfiguration options )
    {
        this.task = task;
        this.application = options.getApplication();
        this.options = options;
        entityLinker = new OiRelinker( task );
        cachedStmts = new HashMap<EntityDef, AbstractSqlHandler.SqlStatement>();
    }

    protected SqlStatement initializeCommand( SqlCommand sqlCommand, View view )
    {
        return initializeCommand( sqlCommand, view, null );
    }

    protected SqlStatement initializeCommand( SqlCommand sqlCommand, View view, EntityDef selectRoot )
    {
        return new SqlStatement( sqlCommand, view, selectRoot );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.DbHandler#insertEntity(com.quinsoft.zeidon.View, com.quinsoft.zeidon.EntityInstance, java.lang.Object)
     */
    @Override
    public int insertEntity(View view, List<? extends EntityInstance> entityInstances)
    {
        // All the instances should have the same EntityDef.
        EntityDef entityDef = entityInstances.get(0).getEntityDef();
        DataRecord dataRecord = entityDef.getDataRecord();

        task.dblog().debug( "Inserting entity %s, table name = %s", entityDef.getName(), dataRecord.getRecordName() );

        int numInserts = entityInstances.size();
        int maxInserts = numInserts;
        Integer max = getInsertCount();
        if ( max != null && max > 0 )
            maxInserts = Math.min( max, numInserts );

        int idx = 0;
        while( idx < numInserts )
        {
            SqlStatement stmt = initializeCommand( SqlCommand.INSERT, view );
            stmt.appendCmd( "INSERT INTO ", dataRecord.getRecordName(), " ( " );
            addColumnList( stmt, entityInstances.get( idx ), dataRecord, 0 );
            stmt.appendColumn( "  )" );
            stmt.appendInsertValues( " VALUES \n (" );

            EntityInstance entityInstance = null;
            for ( int count = 0; count < maxInserts && idx < numInserts; count++ )
            {
                if ( count > 0 ) // First row?
                    stmt.appendInsertValues( " ),\n ( " );

                entityInstance = entityInstances.get( idx );
                addColumnValueList( stmt, entityInstance );

                idx++;
            }

            stmt.appendInsertValues( " )" );
            executeStatement( view, entityDef, entityInstance, stmt );
        }

        return 0;
    }

    private void addColumnValueList(SqlStatement stmt, EntityInstance entityInstance)
    {
        boolean firstColumn = true;
        for ( DataField dataField : stmt.columns.keySet() )
        {
            // If the DB is generating the keys then don't add generated keys to
            // the list of columns.
            AttributeDef attributeDef = dataField.getAttributeDef();
            if ( ! addGeneratedKeyForInsert() && attributeDef.isGenKey() && entityInstance.getAttribute( attributeDef ).isNull() )
                continue;

            if ( firstColumn )
                firstColumn = false;
            else
                stmt.appendInsertValues( ", " );

            getAttributeValue( stmt, stmt.insertValues, dataField, entityInstance );
        }
    }

    /**
     * Copies the value of the attribute into buffer.
     * @param stmt TODO
     * @param domain
     * @param attributeDef TODO
     * @param buffer
     * @param value
     */
    protected abstract void getSqlValue( SqlStatement stmt,
                                         Domain domain,
                                         AttributeDef attributeDef,
                                         StringBuilder buffer,
                                         Object value );

    protected abstract String getConfigValue( String key );

    @Override
    public void setDbGenerateKeys( boolean set )
    {
        isDbGenerateKeys  = set;
    }

    protected boolean useDbGenerateKeys()
    {
        return isDbGenerateKeys;
    }

    /**
     * Indicates whether we should add the generated key as part of the insert.
     * By default the value is 'false' if we're using generated keys because
     * the DB will infer it.  This means the genkey will not appear at all
     * in the INSERT statements.
     *
     * However some DBs (e.g. SQLite) require us to insert a NULL value for
     * the key.  Sqlite will then generate its own key.
     *
     * @return
     */
    protected boolean addGeneratedKeyForInsert()
    {
        return ! isDbGenerateKeys;
    }

    /**
     * For applications that equate empty strings to null we need to convert any input
     * strings from "" to null.
     */
    static Object convertEmptyStringValue( Object value, AttributeDef attributeDef )
    {
        if ( value == null )
            return null;


        if ( ! ( value instanceof CharSequence ) )
            return value;


        if ( StringUtils.isBlank( (CharSequence ) value ) &&
             attributeDef.getDomain().getApplication().nullStringEqualsEmptyString() )
       {
           return null;
       }

       return value;
    }


    /**
     * Add the attribute value to the buffer.
     * @param stmt TODO
     * @param buffer
     * @param dataField
     * @param entityInstance
     */
    void getAttributeValue(SqlStatement stmt, StringBuilder buffer, DataField dataField, EntityInstance entityInstance, boolean ignoreBind )
    {
        AttributeDef attributeDef = dataField.getAttributeDef();
        if ( ignoreBind == false && isBindAllValues() )
        {
            if ( stmt.commandType != SqlCommand.INSERT )
            {
                stmt.addBoundAttribute( buffer, dataField );
                return;
            }

            // There can be multiple INSERT statements for a single SQL command.  We need to bind the attribute
            // value instead of the data field.
            Object value = entityInstance.getAttribute( attributeDef ).getValue();
            value = convertEmptyStringValue( value, attributeDef );

            stmt.addBoundAttribute( buffer, value );
            return;
        }

        Object value = entityInstance.getAttribute( attributeDef ).getValue();
        value = convertEmptyStringValue( value, attributeDef );
        getSqlValue( stmt, attributeDef.getDomain(), attributeDef, buffer, value );
    }

    void getAttributeValue(SqlStatement stmt, StringBuilder buffer, DataField dataField, EntityInstance entityInstance )
    {
        getAttributeValue( stmt, buffer, dataField, entityInstance, false );
    }

    protected Task getTask()
    {
        return task;
    }

    /**
     * Used for setting qualification: get the attribute value and assign it using equals.
     * This method is necessary to use
     * @param stmt TODO
     * @param buffer
     * @param dataField
     * @param entityInstance
     *
     * @return
     */
    boolean getAttributeValueEquality(SqlStatement stmt, StringBuilder buffer, DataField dataField, EntityInstance entityInstance)
    {
        if ( ! isBindAllValues() )
        {
            if ( entityInstance.getAttribute( dataField.getAttributeDef() ).isNull() )
            {
                buffer.append( " IS null" );
                return false;
            }
        }

        buffer.append( " = " );
        getAttributeValue( stmt, buffer, dataField, entityInstance );
        return true;
    }

    private void addColumnList(SqlStatement   stmt,
                               EntityInstance entityInstance,
                               DataRecord     dataRecord,
                               long           control)
    {
        RelRecord relRecord = dataRecord.getRelRecord();

        for ( DataField dataField : dataRecord.dataFields() )
        {
            AttributeDef attributeDef = dataField.getAttributeDef();
            if ( ( control & COL_KEYS_ONLY ) != 0 && attributeDef.isKey() )
                continue;

            if ( ! attributeDef.isActivate() )
                continue;

            // If the attribute is an Auto Seq attribute and the relationship
            // is many-to-many then the attribute is stored in the corresponding
            // table.  If the command type is also INSERT then the attribute is
            // not to be included in this list.
            if ( attributeDef.isAutoSeq() &&
                 relRecord != null && relRecord.getRelationshipType() == RelRecord.MANY_TO_MANY &&
                 stmt.commandType == SqlCommand.INSERT )
            {
                continue;
            }

            // Skip the attribute if it wasn't updated.
            if ( entityInstance != null && stmt.commandType != SqlCommand.INSERT)
            {
                if ( ! entityInstance.getAttribute( attributeDef ).isUpdated() )
                    continue;
            }

            if ( attributeDef.isGenKey() && stmt.commandType == SqlCommand.INSERT && ! addGeneratedKeyForInsert() )
            {
                // If the DB is generating the keys then don't add generated keys to
                // the list of columns UNLESS the key is not null.  If the key is not
                // null then someone must have set it on purpose and we'll use that value.
                if ( entityInstance.getAttribute( attributeDef ).isNull() )
                    continue;
            }

            // TODO: Add dbOper code?

            StringBuilder colName = new StringBuilder();

            // Add table name.
            if ( ( control & COL_FULL_QUAL ) != 0 )
            {
                String tableName;
                if ( attributeDef.isAutoSeq() &&
                     relRecord != null && relRecord.getRelationshipType() == RelRecord.MANY_TO_MANY )
                {
                    // This is the autoseq attribute.  The autoseq is stored in the correspondance
                    // table for m-to-m relationships.
                    tableName = stmt.getTableName( relRecord );
                }
                else
                {
                    tableName = stmt.getTableName( dataRecord );
                }
                colName.append( tableName ).append( "." );
            }

            colName.append( dataField.getName() );
            stmt.addColumn( colName, dataRecord, dataField );
        }

        // If entityDef can be loaded all at once and this is a many-to-many relationship
        // we need to add the key of the parent table in the column list so we can use it
        // later to set the cursor when we're loading attributes.
        EntityDef entityDef = dataRecord.getEntityDef();
        if ( stmt.commandType == SqlCommand.SELECT &&
             selectAllInstances( entityDef ) && relRecord.getRelationshipType().isManyToMany() )
        {
            RelField relField = relRecord.getParentRelField();
            StringBuilder colName = new StringBuilder();
            String tableName = stmt.getTableName( relRecord );
            colName.append( tableName ).append( "." );
            colName.append( relField.getFieldName() );
            if ( stmt.columns.size() > 0 )
                stmt.appendCmd( ", " );

            stmt.addColumn( colName, dataRecord, relField.getSrcDataField() );
        }
    }

    /**
     * Parses the qualification object and puts the information in the qualMap.
     * The
     * @param view
     */
    private void loadQualificationObject( LodDef lodDef )
    {
        qualMap = new HashMap<EntityDef, QualEntity>();
        if ( qual == null )
            return;

        for ( EntityInstance entitySpec : qual.cursor( "EntitySpec" ).eachEntity() )
        {
            if ( entitySpec.getAttribute( "EntityName" ).isNull() )
                throw new ZeidonException("Qualification view is missing entity name in EntitySpec" );

            String entityName = entitySpec.getAttribute( "EntityName" ).getString();
            if ( StringUtils.isBlank( entityName ) )
                throw new ZeidonException("Qualification view is missing entity name in EntitySpec" );

            EntityDef entityDef = lodDef.getEntityDef( entityName );
            QualEntity qualEntity = new QualEntity( entitySpec, entityDef );
            qualMap.put( entityDef, qualEntity );

            int parenCount = 0;

            EntityDef qualAttribDef = entitySpec.getEntityDef().getLodDef().getEntityDef( "QualAttrib" );
            for ( EntityInstance qualAttribInstance : entitySpec.getChildren( qualAttribDef, true ) )
            {
                //
                // Verify Oper
                //
                if ( qualAttribInstance.getAttribute( "Oper" ).isNull() )
                    throw new ZeidonException( "QualAttrib for " + entityName + " is missing Oper" );

                QualAttrib qualAttrib = new QualAttrib( qualAttribInstance.getAttribute( "Oper" ).getString() );
                if ( qualAttrib.oper.equals( "EXCLUDE" ) )
                {
                    qualEntity.exclude = true;
                    continue;
                }

                if ( qualEntity.exclude )
                    throw new ZeidonException( "Entity '%s' has EXCLUDE but has additional qualification",
                                               qualEntity.entityDef.getName() );

                parenCount += CharSetUtils.count( qualAttrib.oper, "(" );
                parenCount -= CharSetUtils.count( qualAttrib.oper, ")" );

                //
                // Verify EntityName
                //
                if ( ! qualAttribInstance.getAttribute( "EntityName"  ).isNull() )
                {
                    String qualEntityName = qualAttribInstance.getAttribute( "EntityName" ).getString();
                    qualAttrib.entityDef = lodDef.getEntityDef( qualEntityName );

                    if ( qualAttrib.entityDef.isDerived() || qualAttrib.entityDef.isDerivedPath() ||
                         qualAttrib.entityDef.getDataRecord() == null )
                    {
                        throw new ZeidonException( "Entity " + entityName + " is derived or doesn't have DB information.");
                    }

                    // This entity better be a child of what we are qualifying.
//                    EntityDef search = qualAttrib.entityDef.getParent();
//                    while ( search != null && search != entityDef )
//                        search = search.getParent();
//                    if ( search == null )
//                        throw new ZeidonException( "EntityName " + qualEntityName + " for QualAttrib " +
//                                                   qualEntityName + " is not a child of " + entityName );
                }

                if ( qualAttrib.oper.equals( "EXISTS" ) || qualAttrib.oper.equals( "NOT EXISTS" ) )
                {
                    if ( qualAttrib.entityDef == null )
                        throw new ZeidonException("Oper 'EXISTS'/'NOT EXISTS' requires an entity specification");

                    // Change the oper to =/!=
                    if ( qualAttrib.oper.equals( "EXISTS" ) )
                        qualAttrib.oper = "!=";
                    else
                    {
                        qualAttrib.oper = "=";
                        qualEntity.hasDoesNotExist = true;
                    }

                    qualAttrib.attributeDef = qualAttrib.entityDef.getKeys().get( 0 );
                }

                //
                // Verify AttribName
                //
                if ( ! qualAttribInstance.getAttribute( "AttributeName"  ).isNull() || qualAttrib.attributeDef != null )
                {
                    if ( qualAttrib.attributeDef == null )
                    {
                        String attribName = qualAttribInstance.getAttribute( "AttributeName" ).getString();
                        if ( qualAttrib.entityDef == null )
                            throw new ZeidonException( "QualAttrib has attribute defined but no valid entity" );

                        qualAttrib.attributeDef = qualAttrib.entityDef.getAttribute( attribName );
                    }

                    // In some cases, we might be qualifying an entity using an attribute
                    // from a child entity.  If the child attribute is a key AND that key
                    // is the source attribute for a many-to-one relationship then the
                    // attribute's value is also stored in the parent entity (the entity
                    // we are qualifying) as a foreign key.  It will be much quicker to
                    // perform qualification on just the foreign key, so change the
                    // qualification to reference the foreign key.
                    DataRecord dataRecord = qualAttrib.entityDef.getDataRecord();
                    RelRecord relRecord = dataRecord.getRelRecord();
                    while ( qualAttrib.attributeDef.isKey() &&
                            qualAttrib.entityDef != qualEntity.entityDef &&
                            relRecord != null &&
                            relRecord.getRelationshipType() == RelRecord.CHILD_IS_SOURCE )
                    {
                        assert relRecord.getRelFields().size() == 1;

                        // Find the rel field for the qualifying attribute.
                        RelField relField = relRecord.getRelFields().get( 0 );

                        // Change the column we are qualifying on.
                        DataField dataField = relField.getRelDataField();
                        qualAttrib.attributeDef = dataField.getAttributeDef();
                        qualAttrib.entityDef = qualAttrib.attributeDef.getEntityDef();

                        dataRecord = qualAttrib.entityDef.getDataRecord();
                        relRecord = dataRecord.getRelRecord();
                    }
                }

                if ( qualAttrib.oper.equals( "ORDERBY" ) )
                {
                    if ( qualAttrib.attributeDef == null )
                        throw new ZeidonException("Using Order By in qualification requires an attribute name" );

                    boolean descending = false;

                    if ( ! qualAttribInstance.getAttribute( "Value"  ).isNull() ) {
                        String str = qualAttribInstance.getAttribute( "Value"  ).getString().toLowerCase();
                        descending =  str.startsWith( "desc" );
                    }

                    qualEntity.addOrderBy( qualAttrib.attributeDef, descending );
                    continue;
                }


                //
                // Verify Value
                //
                if ( ! qualAttribInstance.getAttribute( "Value"  ).isNull() )
                {
                    if ( qualAttrib.attributeDef == null )
                        throw new ZeidonException("QualAttrib with value requires Entity.Attrib");

                    String value = qualAttribInstance.getAttribute( "Value" ).getString();

                    if ( qualAttrib.oper.equals( "LIKE" ) )
                    {
                        // If oper is "LIKE" then a qualification value that is invalid for the domain
                        // is possible.  E.g. "(617)%' is valid qualification for a phone number.  We'll
                        // assume the user knows what she's doing so we'll skip domain processing.
                        qualAttrib.value = value;
                    }
                    else
                    {
                        // Get the string value from the qualification object, convert it to the
                        // domain's internal value, and then to an a string representation of the
                        // internal value.
                        Domain domain = qualAttrib.attributeDef.getDomain();
                        qualAttrib.value = domain.convertExternalValue( task, null, qualAttrib.attributeDef, null, value );
                    }
                }

                //
                // Verify KeyList
                //
                for ( EntityInstance kl : qualAttribInstance.getChildren( "KeyList" ) )
                {
                    if ( qualAttrib.valueList == null )
                        qualAttrib.valueList = new ArrayList<>();

                    String value = null;
                    if ( ! kl.getAttribute( "StringValue" ).isNull() )
                        value = kl.getAttribute( "StringValue" ).getString();
                    else
                    if ( ! kl.getAttribute( "IntegerValue" ).isNull() )
                        value = kl.getAttribute( "IntegerValue" ).getString();
                    else
                        throw new ZeidonException( "KeyList entity doesn't have IntegerValue or StringValue" );

                    Domain domain = qualAttrib.attributeDef.getDomain();
                    Object objectValue = domain.convertExternalValue( task, null, qualAttrib.attributeDef, null, value );

                    qualAttrib.valueList.add( objectValue );
                }

                if ( qualAttrib.valueList != null )
                {
                    if ( qualAttrib.value != null )
                        throw new ZeidonException( "KeyList is specified for a QualAttrib that also has a value attribute" );
                }

                //
                // Verify SourceViewName
                //
                if ( ! qualAttribInstance.getAttribute( "SourceViewName"  ).isNull() )
                    throw new ZeidonException( "SourceViewName is currently unsupported for Activate Qualification" );

                if ( ! qualAttribInstance.getAttribute( "SourceViewID"  ).isNull() )
                    throw new ZeidonException( "SourceViewID is not supported by Java Object Engine" );

                //
                // Verify SourceEntityName
                //
                if ( ! qualAttribInstance.getAttribute( "SourceEntityName"  ).isNull() )
                {
                    if ( qualAttribInstance.getAttribute( "SourceAttributeName"  ).isNull() )
                        throw new ZeidonException( "SourceEntityName is specified but SourceAttributeName is not." );

                    loadQualAttributeColumn( lodDef, qualAttribInstance, qualAttrib );
                }


                // =================================================================
                // ===
                // ===  Validate Qualification attributes.
                // ===
                // =================================================================

                // TODO: Add the rest of the checks in fnSqlRetrieveQualAttrib.

                // Check to see if we should add qual for both "" and NULL.
                if ( addCheckForNullAndEmptyString( qualEntity, qualAttrib ) )
                    qualEntity.addQualAttrib( qualAttrib ); // No; just add qualAttrib as normal.

            } // for each QualAttrib...

            assert parenCount == 0;

        } // for each EntitySpec...

        // For pagination we *must* have the keys as part of the ordering, even if
        // it's the last value to order by.  This is so sorting will always have
        // the same results.
        if ( pagingOptions != null )
        {
            EntityDef root = lodDef.getRoot();
            QualEntity qualRootEntity = qualMap.get( root );
            if ( qualRootEntity == null )
            {
                qualRootEntity = new QualEntity( null, root );
                qualMap.put( root, qualRootEntity );
            }

            if ( qualRootEntity.checkForKeysInOrderBy() ) // Add the keys if they aren't there.
                getTask().log().trace( "Key(s) added to ordering for pagination" );

            qualRootEntity.activateLimit = pagingOptions.getPageSize();

            // Copy the root ordering back to the ActivateOptions so we can use
            // them later when attempting to load the next page.
            activateOptions.setRootActivateOrdering( qualRootEntity.ordering );
        }

    } // method loadQualificationObject

    /**
     * Take a single qualAttrib that looks for ""/null string and add two qualAttribs
     * to the qualEntity to explicitly look for "" and null string.
     *
     * return: True if qual attrib needs to be added (i.e. it wasn't added here).
     */
    private boolean addCheckForNullAndEmptyString( QualEntity qualEntity, QualAttrib qualAttrib )
    {
        if ( ! qualAttrib.isNullAndEmptyString() )
            return true;

        qualEntity.addQualAttrib( new QualAttrib( "(" ) );

        qualAttrib.value = null;
        qualEntity.addQualAttrib( qualAttrib );

        if ( qualAttrib.operIsInequality() )
            qualEntity.addQualAttrib( new QualAttrib( "AND" ) );
        else
            qualEntity.addQualAttrib( new QualAttrib( "OR" ) );

        qualAttrib = new QualAttrib( qualAttrib ); // Clone so we can change the value.
        qualAttrib.value = "";
        qualEntity.addQualAttrib( qualAttrib );

        qualEntity.addQualAttrib( new QualAttrib( ")" ) );
        return false;
    }

    /**
     * The value in the qualification is a column name, not a text value.  This means that the
     * qualification is comparing the column to another column.  Load the values in qualAttrib.
     *
     * @param lodDef
     * @param qualAttribInstance
     * @param qualAttrib
     */
    private void loadQualAttributeColumn( LodDef lodDef, EntityInstance qualAttribInstance, QualAttrib qualAttrib )
    {
        String srcEntityName = qualAttribInstance.getAttribute( "SourceEntityName" ).getString();
        String srcAttributeName = qualAttribInstance.getAttribute( "SourceAttributeName" ).getString();

        EntityDef entityDef = lodDef.getEntityDef( srcEntityName, false );
        if ( entityDef == null )
            throw new ZeidonException( "EntityName specified in qualification is unknown: %s", srcEntityName );

        AttributeDef attributeDef = entityDef.getAttribute( srcAttributeName, false );
        if ( attributeDef == null )
            throw new ZeidonException( "AttributeName specified in qualification is unknown: %s", srcAttributeName );

        qualAttrib.columnAttributeValue = attributeDef;

        // Verify that columnAttributeValue.getViewEntity is a child of qualAttrib.viewEntity or
        // vice-versa.  We could potentially support siblings if they have 1-to-1 relationships
        // with their parents.
        if ( entityDef != qualAttrib.entityDef &&
           ! entityDef.isAncestorOf( qualAttrib.entityDef ) && ! qualAttrib.entityDef.isAncestorOf( entityDef ) )
        {
            throw new ZeidonException( "When qualifying an attribute with another attribute in the same query, " +
                                       "one attribute must be a descendant of the other (i.e. they may not be " +
                                       "siblings." )
                                       .appendMessage( "Attribute 1 = %s.%s", qualAttrib.entityDef.getName(),
                                                                              qualAttrib.attributeDef.getName() )
                                       .appendMessage( "Attribute 2 = %s.%s", entityDef.getName(),
                                                                              attributeDef.getName() );
        }
    }

    @Override
    public int beginActivate( View view, View qual, EnumSet<ActivateFlags> control )
    {
        this.qual = qual;
        this.activateFlags = control;
        activateOptions = (ActivateOptions) options;
        pagingOptions = activateOptions.getPagingOptions();
        loadQualificationObject( view.getLodDef() );
        loadedViewEntities = new HashSet<>();
        determineEntitiesThatCanBeLoadedInOneSelect( view );
        if ( activateOptions.isSingleTransaction() )
            closeTransaction = false;

        return 0;
    }

    /**
     * This determines what ViewEntities can be loaded in a single SELECT statement.
     * @param view
     */
    private void determineEntitiesThatCanBeLoadedInOneSelect(View view)
    {
        // If an entity can be loaded in one SELECT we need to keep a map of its parent
        // EIs so we can set cursors appropriately.
        loadedInstances = new HashMap<EntityDef, Map<Object,EntityInstance>>();
        loadInOneSelect = new HashSet<>();

        for ( EntityDef entityDef : view.getLodDef().getEntityDefs() )
        {
            if ( entityCanBeLoadedWithSingleSelect( entityDef ) )
            {
                loadInOneSelect.add( entityDef );

                EntityDef parent = entityDef.getParent();
                if ( parent != null && ! loadedInstances.containsKey( parent ) )
                    loadedInstances.put( parent, new HashMap<Object, EntityInstance>() );
            }
        }
    }

    /**
     * Returns true if all the instances of EntityDef can be loaded in a single select.
     *
     * @param entityDef
     */
    protected boolean selectAllInstances( EntityDef entityDef )
    {
        // Root entities are handled differently.
        if ( entityDef.getParent() == null )
            return false;

        return loadInOneSelect.contains( entityDef );
    }

    protected boolean activatingWithJoins()
    {
        if ( activateFlags.contains( ActivateFlags.fIGNORE_JOINS ) )
            return false;

//        if ( activateFlags.contains( ActivateFlags.fROOT_ONLY ) )
//            return false;

        // Check the zeidon.ini file.
        if ( ignoreJoins() )
            return false;

        return true;
    }

    /**
     * Returns the list of children that are joinable with entityDef.  This may be different
     * per activate because of qualification.
     *
     * @param entityDef
     * @return
     */
    protected List<EntityDef> getJoinableChildren( EntityDef entityDef )
    {
        if ( ! activatingWithJoins() )
            return Collections.emptyList();

        // Have we already determined who can be joined?
        List<EntityDef> jc = joinableChildren.get( entityDef );
        if ( jc != null )
            return jc; // It's already been calculated.  Return it.

        // We need to keep the set of children joinable with entityDef.
        jc = new ArrayList<EntityDef>();

        // Keep track of entityDefs that are not joinable.  None of their
        // children are joinable either.
        Set<EntityDef> notJoinable = new HashSet<EntityDef>();

        Set<QualEntity> quals = new HashSet<>();
        QualEntity qualEntity = qualMap.get( entityDef );
        if ( qualEntity != null )
            quals.add( qualEntity );

        // Get the list of all children that can be joinable.
        for ( EntityDef child : getEntityDefData( entityDef ).getJoinedChildren() )
        {
            boolean ignoreBecauseOfParent = false;
            for ( EntityDef parent = child.getParent(); parent != entityDef; parent = parent.getParent() )
            {
                if ( notJoinable.contains( parent ) )
                {
                    ignoreBecauseOfParent = true;
                    break;
                }
            }

            if ( ignoreBecauseOfParent || ! isJoinable( child ) )
            {
                notJoinable.add( child );
                continue;
            }

            // If the child has qualification on one of its children then it can't
            // be joined.
            QualEntity childQualEntity = qualMap.get( child );
            if ( childQualEntity != null && childQualEntity.usesChildQualification )
            {
                notJoinable.add( child );
                continue;
            }

            // We can't join a child if that child is used to qualify an entityDef
            // that is part of this join.
            for ( QualEntity qual : quals )
            {
                if ( qual.usesEntityDefs.contains( child ) )
                {
                    notJoinable.add( child );
                    break;
                }
            }

            // If child isn't in the notJoinable set then it must be ok to join.
            if ( ! notJoinable.contains( child ) )
            {
                jc.add( child );
                if ( childQualEntity != null )
                    quals.add( childQualEntity );
            }
        }

        joinableChildren.put( entityDef, Collections.unmodifiableList( jc ) );
        return jc;
    }

    private EntityCache getEntityCache( EntityDef entityDef )
    {
        // We can't load a root from cache because we don't know which ones to load.
        if ( entityDef.getParent() == null )
            return null;

        DataRecord dataRecord = entityDef.getDataRecord();
        if ( dataRecord == null )
            return null;

        // Currently we only handle many-to-one relationships.
        // TODO: It would be nice to handle m-to-m relationships.  That is more complex
        // because it requires the correspondence table being loaded.
        RelRecord relRecord = dataRecord.getRelRecord();
        if ( ! relRecord.getRelationshipType().isManyToOne() )
            return null;

        // Check to see if there is an entity cache for this entity.
        EntityCache entityCache = task.getEntityCache( entityDef );
        return entityCache;
    }
    /**
     * Returns true if entityDef can be loaded by joining with its parent.
     *
     * @param entityDef
     * @return
     */
    protected boolean isJoinable( EntityDef entityDef )
    {
        // If this entity can be loaded from a cache then we don't need to
        // join it.
        if ( getEntityCache( entityDef ) != null )
            return false;

        if ( activateFlags.contains( ActivateFlags.fROOT_ONLY ) && entityDef.getParent() != null )
            return false;

        // We can't join a child to its parent if there is an activate limit on the
        // parent AND if the child has a x-to-many relationship.  Joining the parent
        // and child could result in a result-set that has more rows than expected.
        if ( entityDef.getParent() != null && entityDef.getMaxCardinality() > 1 )
        {
            QualEntity parentQualEntity = qualMap.get( entityDef.getParent() );
            if ( parentQualEntity != null && parentQualEntity.activateLimit != null )
                return false;

            // If the parent of entityDef is the root and we're activating with pagination
            // then we can't join because the join will throw off the row count.
            if ( entityDef.getParent() == activateOptions.getLodDef().getRoot() && pagingOptions != null )
            {
                getTask().log().trace( "Can't join %s with parent because of pagination", entityDef.getName() );
                return false;
            }
        }

        return getEntityDefData( entityDef ).isJoinable( entityDef );
    }

    /**
     * Generate the SELECT statement to load entityDef (and, if using joins, its children).
     *
     * @param stmt
     * @param view
     * @param entityDef
     * @return  If null, then everything generated ok.  If non-null, then the activate should
     *          be short-circuited with returned RC.
     */
    private Integer generateLoadStatement( SqlStatement stmt, View view, EntityDef entityDef )
    {
        DataRecord dataRecord = entityDef.getDataRecord();
        RelRecord  relRecord  = dataRecord.getRelRecord();

        // This will contain a list of children with x-to-1 relationship.  We can load them with a single join.
        List<EntityDef> joinedChildren = getJoinableChildren( entityDef );

        QualEntity qualEntity = qualMap.get( entityDef );
        stmt.appendCmd( "SELECT " );

        // If the entity we're loading is not the root of the OD then add parent FKs to WHERE clause.
        if ( entityDef.getParent() != null )  // Is it the root?
        {
            // Not the root.
            // If the table is qualified then we're going to add more stuff later
            // so let's add an opening paren.
            if ( qualEntity != null )
                stmt.appendWhere( "(" );

            if ( ! addForeignKeys( stmt, view, entityDef, entityDef ) )
            {
                task.dblog().trace( "FK was null--skipping load entity for SQL statement:\n%s", stmt.toString() );
                return DbHandler.LOAD_NO_ENTITIES; // A FK was null so don't load it.
            }
        }
        else
            stmt.addTableToFrom( entityDef.getDataRecord().getRecordName(), entityDef.getDataRecord(), true );

        long control = 0;
        if ( qualEntity != null && qualEntity.usesChildQualification )
            control |= COL_FULL_QUAL;
        else
        if ( relRecord != null && relRecord.getRelationshipType() == RelRecord.MANY_TO_MANY )
            control |= COL_FULL_QUAL;  // Fully qualify to avoid conflicts with correspondence table.

        if ( joinedChildren.size() > 0 )
            control |= COL_FULL_QUAL;  // We're joining tables so use fully qualified names.

        addColumnList( stmt, null, dataRecord, control );

        for ( EntityDef child : joinedChildren )
        {
            DataRecord childDataRecord = child.getDataRecord();
            addLeftJoinKeys( stmt, view, child );
            addColumnList( stmt, null, childDataRecord, control );
        }

        if ( qualEntity != null )
        {
            addQualification( qualEntity, stmt, view, entityDef );

            // If the parent is non-null then we added foreign keys above.  Add
            // the closing paren.
            if ( entityDef.getParent() != null )
                stmt.appendWhere( ")" );
        }

        addOrdering( entityDef, stmt );
        for ( EntityDef child : joinedChildren )
            addOrdering( child, stmt );

        if ( qualEntity != null && qualEntity.activateLimit != null )
            addActivateLimit( qualEntity.activateLimit, stmt );
        else
        if ( entityDef.getActivateLimit() != null )
            addActivateLimit( entityDef.getActivateLimit(), stmt );

        if ( pagingOptions != null && ! pagingOptions.isRollingPagination() && entityDef.getParent() == null )
            addPageOffset( pagingOptions, stmt );

        // Add all the children that will be joined to the loaded set.  This
        // indicates that the joined children will be loaded as part of the
        // parent load and will not need to be loaded separately.
        loadedViewEntities.addAll( joinedChildren );
        return null;
    }

    private void addOrdering( EntityDef entityDef, SqlStatement stmt )
    {
        // Do we have ordering specified in the qualification object?
        QualEntity qualEntity = qualMap.get( entityDef );
        if ( qualEntity == null || ! qualEntity.hasOrdering() )
        {
            // No qualification order.  If entity has any ordering specified
            // in the LOD, add it.
            stmt.appendOrdering( entityDef );
        }
        else
        {
            // Ordering has been specified in the qual object.  Add it.
            for ( ActivateOrder order : qualEntity.getOrdering().values() )
                stmt.appendOrdering( order.attributeDef, order.descending );
        }
    }

    /**
     * Returns true if all instances of entityDef can be loaded in a single SELECT
     * statement.
     *
     * @param entityDef
     * @return
     */
    private boolean entityCanBeLoadedWithSingleSelect( EntityDef entityDef )
    {
        // Root entity is handled differently from other entities but we'll pretend it's
        // loadable to make things easier.
        if ( entityDef.getParent() == null )
            return true;

        // If we're activating as part of a lazy load then we shouldn't be loading all
        // instances because the lazy load indicates we want partial loading.
        if ( activateOptions.isPerformingLazyLoad() )
            return false;

        if ( activateOptions.getActivateFlags().contains( ActivateFlags.fIGNORE_LOAD_OPTIMIZATION ) )
            return false;

        // If the parent can't be loaded in a single select then neither
        // can this one.  If the parent isn't in loadInOneSelect then it
        // can't be loaded.
        if ( ! loadInOneSelect.contains( entityDef.getParent() ) )
            return false;

        // If this entity is qualified we can't do it.
        // TODO: logically we should be able to load entities even if they are qualified;
        // it will just take a lot of work to get right.
        if ( qualMap.containsKey( entityDef ) )
            return false;

        DataRecord childRecord = entityDef.getDataRecord();
        // Work entity, childRecord is null
        if ( childRecord == null )
            return false;

        return childRecord.isActivateWithSingleSelect();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.DbHandler#loadEntity(com.quinsoft.zeidon.View, com.quinsoft.zeidon.objectdefinition.EntityDef, com.quinsoft.zeidon.View, java.lang.Object, long)
     */
    @Override
    public int loadEntity( View view, EntityDef entityDef )
    {
        QualEntity qualEntity = qualMap.get( entityDef );
        if ( qualEntity != null && qualEntity.exclude )
        {
            view.dblog().debug( "Excluding '%s' because qualification says so", entityDef.getName() );
            return DbHandler.LOAD_NO_ENTITIES;
        }

        SqlStatement stmt = null;

        DataRecord dataRecord = entityDef.getDataRecord();
        task.dblog().trace( "Selecting entity %s, table name = %s", entityDef.getName(), dataRecord.getRecordName() );

        // Check to see if we've loaded this entityDef already.  This can happen if
        // this entity def was loaded via join or all instances were loaded at once.
        if ( loadedViewEntities.contains( entityDef ) )
            return DbHandler.LOAD_DONE;

        EntityCache entityCache = getEntityCache( entityDef );
        if ( entityCache != null )
            return loadFromEntityCache( view, entityDef, entityCache );

        if ( selectAllInstances( entityDef ) )
            loadedViewEntities.add( entityDef );

        if ( entityDef.isAutoloadFromParent() && autoloadFromParent( view, entityDef) )
            return DbHandler.LOAD_DONE;

        if ( entityDef.isDuplicateEntity() && loadFromDuplicateCache( view, entityDef ) )
            return DbHandler.LOAD_DONE;

        // TODO: We can't handle cached recursive statements because the cached statement is
        //       caching the value of the parent instead of the DataField of the parent.

        // Look to see if we've already generated this SQL.
        if ( isBindAllValues() &&
             cachedStmts.containsKey( entityDef ) &&
             ! entityDef.isRecursive() ) // We can't handle cached recursive statements.
        {
            task.dblog().trace( "Using cached stmt" );
            stmt = cachedStmts.get( entityDef );
        }
        else
        {
            stmt = initializeCommand( SqlCommand.SELECT, view, entityDef );
            Integer rc = generateLoadStatement( stmt, view, entityDef );
            if ( rc != null )
                return rc;

            cachedStmts.put( entityDef, stmt );
        }

        // If we're loading multiple entities at the same time we may need to reposition
        // on the current parent.  Get it and save it.
        EntityDef parent = entityDef.getParent();
        EntityInstance parentEi = null;
        if ( parent != null && loadInOneSelect.contains( entityDef ) )
            parentEi = view.cursor( parent ).getEntityInstance();

        executeLoad( view, entityDef, stmt );

        // Set the total root count if we're loading the root.
        if ( parent == null )
        {
            if ( pagingOptions != null )
            {
                if ( pagingOptions.isTotalCount() )
                    getTotalCount( view, entityDef, stmt );
            }
            else
                view.setTotalRootCount( view.cursor( view.getLodDef().getRoot() ).getEntityCount() );
        }

        if ( parentEi != null )
            view.cursor( parent ).setCursor( parentEi );

        assert assertNotNullKey( view, entityDef ) : "Activated entity has null key";

        return DbHandler.LOAD_DONE;
    }

    /**
     * Change the query for the root entity so the count can be loaded.
     */
    private void getTotalCount( View view, EntityDef root, SqlStatement stmt )
    {
        assert root.getParent() == null;
        assert  pagingOptions != null;

        // If the entity count is less than the page size then we don't need to
        // execute a query to determine the total root count.
        if ( view.root().getEntityCount() < pagingOptions.getPageSize() )
        {
            view.setTotalRootCount( view.root().getEntityCount() );
            return;
        }

        // Reset the assembled command so it will be recreated after the changes.
        stmt.assembledCommand = null;
        stmt.ordering.setLength( 0 );
        stmt.suffix.setLength( 0 );

        if ( root.getKeys().size() != 1 )
            throw new ZeidonException( "getTotalCount currently only supported for entities with a single key" );

        DataRecord dataRecord = root.getDataRecord();
        DataField keyField = dataRecord.getDataField( root.getKeys().get( 0 ) );

        stmt.columnList.setLength( 0 ); // Clear out the column list.
        stmt.columnList.append( "count( distinct " )
                       .append(  dataRecord.getRecordName() )
                       .append(  "." )
                       .append( keyField.getName() )
                       .append( "  ) " );

        int count = executeStatement(view, root, stmt);
        view.setTotalRootCount( count );
        System.out.println( "count = " + count );
    }

    /**
     * Loads the entityDef from the cache specified in entityCache.
     * Uses include processing.
     * @return
     */
    private int loadFromEntityCache( View view, EntityDef entityDef, EntityCache entityCache )
    {
        if ( entityCacheViewMap == null )
            entityCacheViewMap = new HashMap<>();

        // entityCacheViewMap keeps track of the views we've created for loading from
        // the cache.  This prevents us from creating lots of views for the same OI.
        View cacheView = entityCacheViewMap.get( entityCache );
        if ( cacheView == null )
        {
            cacheView = entityCache.getEntityCacheView(); // Creates a new view.
            entityCacheViewMap.put( entityCache, cacheView );

            // We will include the EntityCache into the target OI, which will also
            // include the children.  Go through the children if entityDef; if their
            // relationships are part of the EntityCache then we will assume they
            // are also being loaded.
            for ( EntityDef child : entityDef.getChildrenHier() )
            {
                if ( entityCache.getRelationships().contains( child.getErRelToken() ) )
                    loadedViewEntities.add( child );

            }
        }

        RelRecord relRecord = entityDef.getDataRecord().getRelRecord();
        assert relRecord.getRelFields().size() == 1; // We currently only handle 1 key.
        assert relRecord.getRelationshipType().isManyToOne();
        assert entityDef.getKeys().size() == 1;

        AttributeDef fk = relRecord.getRelFields().get( 0 ).getRelDataField().getAttributeDef();

        // Get the value of the FK from the parent.
        EntityDef parent = entityDef.getParent();
        Object keyValue = view.cursor( parent ).getAttribute( fk ).getValue();

        // Now set the cursor in the entity cache.
        entityCache.setCursor( cacheView, keyValue );

        view.cursor( entityDef ).includeSubobject( cacheView.cursor( entityCache.getRoot() ),
                                                   CursorPosition.LAST, INCLUDE_FLAGS );

        return DbHandler.LOAD_DONE;
    }

    /**
     * The only attribute(s) in entityDef are keys and this entity can be
     * loaded by retrieving the key from the parent.
     */
    private boolean autoloadFromParent( View view, EntityDef entityDef )
    {
        assert entityDef.getParent() != null;
        DataRecord dataRecord = entityDef.getDataRecord();
        RelRecord  relRecord  = dataRecord.getRelRecord();

        // Autoload is only valid if the key is contained in the parent.
        // TODO: We only handle many-to-one relationships.  We could also handle
        // m-to-m relationships by loading just the correspondence table.
        if ( ! relRecord.getRelationshipType().isManyToOne() )
            return false;

        EntityInstance entityInstance = null;
        EntityCursor parent = view.cursor( entityDef.getParent() );
        for ( RelField relField : relRecord.getRelFields() )
        {
            AttributeInstance sourceAttr = parent.getAttribute( relField.getRelDataField().getAttributeDef() );
            if ( sourceAttr.isNull() )
                continue;

            if ( entityInstance == null )
                entityInstance = view.cursor( entityDef ).createEntity( CursorPosition.LAST, CREATE_FLAGS );

            entityInstance.getAttribute( relField.getSrcDataField().getAttributeDef() ).setInternalValue( sourceAttr, false );
        }

        if ( entityInstance != null )
            getTask().dblog().trace( "Auto Loaded %s from parent keys", entityDef.getName() );

        return true;
    }

    /**
     * See if we can load entityDef from the the duplicate-entity cache, which is
     * kept in the entityLinker.  We can load an EI from the cache if the relationship
     * is many-to-one (which means the FK is in the parent and has been loaded).  We'll
     * check to see if that key has already been loaded.
     *
     * @param view
     * @param entityDef
     * @return
     */
    private boolean loadFromDuplicateCache( View view, EntityDef entityDef )
    {
        if ( entityDef.getParent() == null )
            return false;

        DataRecord dataRecord = entityDef.getDataRecord();
        RelRecord relRecord = dataRecord.getRelRecord();
        if ( ! relRecord.getRelationshipType().isManyToOne() )
            return false;  // Can only do it if it's many-to-one.

        // For now we can only handle entities with a single key.
        if ( entityDef.getKeys().size() > 1 )
            return false;

        assert relRecord.getRelFields().size() == 1;

        // Get the FK that's in the parent.
        AttributeDef parentAttributeFk = relRecord.getRelFields().get( 0 ).getRelDataField().getAttributeDef();

        // Create the key string.  We need to match what entityInstance.getKeyString().
        String keyString = view.cursor( entityDef.getParent() ).getAttribute( parentAttributeFk ).getString();

        // Has it been loaded?
        EntityInstance cachedEi = entityLinker.getInstance( entityDef, keyString );
        if ( cachedEi == null )
            return false; // No.

        view.cursor( entityDef ).includeSubobject( cachedEi, CursorPosition.LAST, INCLUDE_FLAGS );

        getTask().dblog().debug( "Auto Loaded %s from duplicate cache", entityDef.getName() );
        return true;
    }

    @Override
    public boolean isQualified( EntityDef entityDef )
    {
        return qualMap.containsKey( entityDef );
    }

    protected boolean assertNotNullKey( View view, EntityDef entityDef )
    {
        if ( view.cursor( entityDef ).isNull() )
            return true;

        String keyString = getKeyString(view, entityDef);
        if ( StringUtils.isBlank( keyString ) )
        {
            view.cursor( entityDef ).logEntity();
            return false;
        }

        return true;
    }

    /**
     * Returns a string representation of the key values of this entity.  If all keys are
     * null then returns null.
     *
     * @return
     */
    String getKeyString( View view, EntityDef entityDef )
    {
        return view.cursor( entityDef ).getKeyString();
    }

    private StringBuilder buildQualString(SqlStatement stmt, View view, EntityDef entityDef, boolean conjunctionNeeded )
    {
        QualEntity qualEntity = qualMap.get( entityDef );
        StringBuilder qualString = new StringBuilder();

        if ( conjunctionNeeded )
            qualString.append( " AND ( " );

        //===
        //===  At this point, all tables that are needed in the select have
        //===  been included in the SELECT.  All that remains to do is to
        //===  add the QualAttrib expressions to the SELECT statement.
        //===
        for ( QualAttrib qualAttrib : qualEntity.qualAttribs )
        {
            // If there is no entity name for QualAttrib then the QualAttrib
            // has just an Oper.  Tack it on to the end of the WHERE clause.
            if ( qualAttrib.entityDef == null )
            {
                qualString.append( " " ).append( qualAttrib.oper ).append( " " );
                continue;
            }

            // QualAttrib has an entity name, so this is an expression.  Add
            // the expression to the WHERE clause.

            // TODO : add code for subselect commands (like "EXISTS")

            DataRecord dataRecord = qualAttrib.entityDef.getDataRecord();
            DataField  dataField = dataRecord.getDataField( qualAttrib.attributeDef );

            String col = dataField.getName();
            qualString.append( stmt.getTableName( dataRecord ) ).append( "." ).append( col ).append( " " );

            if ( qualAttrib.valueList != null )
            {
                Domain domain = dataField.getAttributeDef().getDomain();
                qualString.append( qualAttrib.oper ).append( " ( " );
                int count = 0;
                for ( Object value : qualAttrib.valueList )
                {
                    if ( count++ > 0 )
                        qualString.append( ", " );

                    StringBuilder buffer = new StringBuilder();
                    getSqlValue( stmt, domain, qualAttrib.attributeDef, buffer, value );
                    qualString.append( buffer.toString() );
                }

                qualString.append( " ) " );
                continue;
            }

            if ( qualAttrib.columnAttributeValue != null )
            {
                DataRecord srcDataRecord = qualAttrib.columnAttributeValue.getEntityDef().getDataRecord();
                String table = stmt.getTableName( srcDataRecord );
                DataField srcDataField = srcDataRecord.getDataField( qualAttrib.columnAttributeValue );
                qualString.append( qualAttrib.oper ).append( " " ).append( table ).append( "." ).append( srcDataField.getName() );
                continue;
            }

            if ( qualAttrib.value == null || StringUtils.isBlank( qualAttrib.value.toString() ) )
            {
                // Value is NULL.  Create appropriate SQL.
                if ( qualAttrib.oper.equals( "<>" ) || qualAttrib.oper.equals( "!=" ) )
                    qualString.append( " IS NOT NULL " );
                else
                    qualString.append( " IS NULL " );
            }
            else
            {
                Domain domain = dataField.getAttributeDef().getDomain();
                StringBuilder buffer = new StringBuilder();
                getSqlValue( stmt, domain, qualAttrib.attributeDef, buffer, qualAttrib.value );
                qualString.append( qualAttrib.oper ).append( " " ).append( buffer.toString() );
            }
        }


        if ( conjunctionNeeded )
            qualString.append( ")" );

        return qualString;
    }

    private void addQualification( QualEntity qualEntity, SqlStatement stmt, View view, EntityDef entityDef)
    {
        // Go through each of the QualAttrib's looking for tables that are not
        // already part of the select.
        for ( QualAttrib qualAttrib : qualEntity.qualAttribs )
        {
            // Add the table to the SELECT statement only if the QualAttrib has
            // a lpEntityDef and a lpAttributeDef.
            if ( qualAttrib.entityDef == null || qualAttrib.attributeDef == null )
                continue;

            addForeignKeys( stmt, view, qualAttrib.entityDef, entityDef );

            if ( qualAttrib.columnAttributeValue != null )
                addForeignKeys( stmt, view, qualAttrib.columnAttributeValue.getEntityDef(), entityDef );
        }

        stmt.appendWhere( buildQualString( stmt, view, entityDef, stmt.conjunctionNeeded ).toString() );
    }

    protected abstract int executeLoad(View view, EntityDef entityDef, SqlStatement stmt);
    protected abstract int executeStatement(View view, EntityDef entityDef, SqlStatement stmt);
    protected abstract void addActivateLimit( int limit, SqlStatement stmt );
    protected abstract void addPageOffset( Pagination pagingOptions, SqlStatement stmt );

    protected int executeStatement(View view, EntityDef entityDef, EntityInstance entityInstance, SqlStatement stmt)
    {
        return executeStatement( view, entityDef, stmt );
    }

    /**
     * Executes the SQL as a single transaction.  Used to create DB locks.
     * @param sql
     * @return
     */
    protected abstract int executeSql(String sql);

    /**
     * Write a row with a specific key to the genkey table.  A successful write means we
     * have locked the table for this commit.
     *
     * @return
     */
    protected void writeGenkeyLockRow( View kzgkhwob, List<? extends View> viewList )
    {
        for ( int tries = 0; tries < 5; tries++ )
        {
            try
            {
                executeSql( "INSERT INTO ZEIDONGENKEYTABLE (TABLENAME, CURRENTGENKEY ) " +
                            "VALUES ('..ENQ KZHGENKY', 999)");
                return;
            }
            catch ( Exception e )
            {
                // For good or ill we'll assume that this exception is caused by the lock
                // being held.  We'll pause a brief time and try again.
                getTask().log().warn( "Exception attempting to lock GENKEY table: %s\n     %s",
                                      e.getMessage(), StringUtils.join( e.getStackTrace(), "\n     " ) );
            }

            try
            {
                Thread.sleep( tries * tries * 100 );
            }
            catch ( InterruptedException e2 )
            {
                throw ZeidonException.wrapException( e2 );
            }
            getTask().log().warn( "Attempting to acquire GenKey lock again #%d.", tries );
        }

        // If we get here then we didn't acquire the lock.
        throw new GenkeyLockException("Unable to acquire GENKEY lock.  See logs for possible explanation.");
    }

    /**
     * Acquire the genkey lock.  It is expected that this method will be overridden by other
     * dbhandlers.
     *
     * @param kzgkhwob
     * @param viewList
     */
    protected void acquireGenkeyLock( View kzgkhwob, List<? extends View> viewList )
    {
        // Standard genkey lock is to write a record to the genkey table.
        writeGenkeyLockRow( kzgkhwob, viewList );
    }

    protected void deleteGenkeyLockRow(  )
    {
        for ( int tries = 0; tries < 5; tries++ )
        {
            try
            {
                executeSql( "DELETE FROM ZEIDONGENKEYTABLE WHERE TABLENAME = '..ENQ KZHGENKY'");
                return;
            }
            catch ( Exception e )
            {
                // Danger danger Will Robinson!  We couldn't delete the lock, which will
                // prevent anybody else from creating new entities.  Log a warning and try again.
                getTask().log().warn( "Exception attempting to delete GENKEY lock entry: %s\n     %s",
                                      e.getMessage(), StringUtils.join( e.getStackTrace(), "\n     " ) );
            }

            try
            {
                Thread.sleep( tries * tries * 100 );
            }
            catch ( InterruptedException e2 )
            {
                // Ignore this error.  If we somehow get a crazy error here we still want
                // to try and unlock the tables.
            }
            getTask().log().warn( "Attempting to delete Genkey lock again #%d.", tries );
        }

        // If we get here then we didn't acquire the lock.
        throw new GenkeyLockException("Unable to delete GENKEY lock.  See logs for possible explanation.");
    }

    /**
     * Release the genkey lock.  It is expected that this method will be overridden by other
     * dbhandlers.
     *
     * @param kzgkhwob
     * @param viewList
     * @return
     */
    protected void releaseGenkeyLock()
    {
        deleteGenkeyLockRow();
    }

    @Override
    public Map<String, Integer> setGenKeys(View kzgkhwob, List<? extends View> viewList )
    {
        // This map will keep track of the next assignable genkey.  The map key is
        // ER entity token.
        Map<String, Integer> genkeyValues = new HashMap<>();

        acquireGenkeyLock( kzgkhwob, viewList );

        try
        {
            // Get the current genkey values from the DB.
            View genkeys = task.activateObjectInstance( "GENKEYWO", null, EnumSet.of( ActivateFlags.fMULTIPLE ) );

            // Add the counts from kzgkhwob to the values from the DB.
            EntityCursor cursor = genkeys.cursor( "ZeidonGenkeyTable" );
            for ( EntityInstance genkey : kzgkhwob.cursor( "Genkey" ).eachEntity() )
            {
                String tableName = genkey.getAttribute( "TableName" ).getString();
                CursorResult rc = cursor.setFirst( "TableName", tableName );
                if ( ! rc.isSet() )
                {
                    // Genkey entry doesn't exist, so add it.
                    cursor.createEntity().getAttribute( "TableName" ).setValue( tableName  )
                                         .getAttribute( "CurrentGenkey" ).setValue( 0 );
                }

                genkeyValues.put( kzgkhwob.cursor( "Genkey" ).getAttribute( "EntityID" ).getString(),
                                  cursor.getAttribute( "CurrentGenkey" ).getInteger() + 1 );
                Integer count = genkey.getAttribute( "EntityCount" ).getInteger();
                Integer c = cursor.getAttribute( "CurrentGenkey" ).getInteger();
                cursor.getAttribute( "CurrentGenkey" ).setValue( count + c );
            }

            genkeys.commit();
        }
        finally
        {
            releaseGenkeyLock();
        }

        return genkeyValues;
    }

    private boolean addMmLeftJoinKeys(SqlStatement stmt, View view, EntityDef entityDef )
    {
        DataRecord dataRecord = entityDef.getDataRecord();
        RelRecord  relRecord  = dataRecord.getRelRecord();

        // Add the correspondance table.
        stmt.from.append( " LEFT JOIN\n" );
        stmt.addTableToFrom( relRecord.getRecordName(), relRecord, false );

        stmt.from.append( " ON " );

        // Add all the data fields that match the parent.
        boolean first = true;
        for ( RelField relField : relRecord.getRelFields() )
        {
            DataField     srcDataField  = relField.getSrcDataField();
            AttributeDef srcAttributeDef = srcDataField.getAttributeDef();
            EntityDef    srcEntityDef = srcAttributeDef.getEntityDef();
            if ( srcEntityDef == entityDef )
                continue;

            if ( !first )
                stmt.from.append( " AND " );
            else
                first = false;

            DataRecord    srcDataRecord = srcEntityDef.getDataRecord();
            stmt.from.append( stmt.getTableName( relRecord ) )
                     .append( "." )
                     .append( relField.getFieldName() )
                     .append( " = " )
                     .append( stmt.getTableName( srcDataRecord ) )
                     .append( "." )
                     .append(  srcDataField.getName() );
        }

        // Add main table.
        stmt.from.append( " LEFT JOIN\n" );

        // If the table name for entityDef is not already part of the SELECT
        // statement then add it to the FROM clause.
        stmt.addTableToFrom( dataRecord.getRecordName(), dataRecord, false );

        stmt.from.append( " ON " );

        // Add the rel fields.
        first = true;
        for ( RelField relField : relRecord.getRelFields() )
        {
            DataField     srcDataField  = relField.getSrcDataField();
            AttributeDef srcAttributeDef = srcDataField.getAttributeDef();
            EntityDef    srcEntityDef = srcAttributeDef.getEntityDef();
            if ( srcEntityDef != entityDef )
                continue;

            if ( !first )
                stmt.from.append( " AND " );
            else
                first = false;

            stmt.from.append( stmt.getTableName( dataRecord ) )
                     .append( "." )
                     .append( srcDataField.getName() )
                     .append( " = " )
                     .append( stmt.getTableName( relRecord ) )
                     .append( "." )
                     .append(  relField.getFieldName() );
        }

        return true;
    }

    private boolean addLeftJoinKeys(SqlStatement stmt, View view, EntityDef entityDef )
    {
        DataRecord dataRecord = entityDef.getDataRecord();
        RelRecord  relRecord  = dataRecord.getRelRecord();

        if ( relRecord.getRelationshipType() == RelRecord.MANY_TO_MANY )
            return addMmLeftJoinKeys( stmt, view, entityDef );

        stmt.from.append( " LEFT JOIN\n" );

        // If the table name for entityDef is not already part of the SELECT
        // statement then add it to the FROM clause.
        stmt.addTableToFrom( dataRecord.getRecordName(), dataRecord, false );

        stmt.from.append( " ON " );

        // Add the rel fields.
        boolean first = true;
        for ( RelField relField : relRecord.getRelFields() )
        {
            if ( !first )
                stmt.from.append( " AND " );
            else
                first = false;

            DataField     srcDataField  = relField.getSrcDataField();
            AttributeDef srcAttributeDef = srcDataField.getAttributeDef();
            EntityDef    srcEntityDef = srcAttributeDef.getEntityDef();
            DataRecord    srcDataRecord = srcEntityDef.getDataRecord();
            DataField     relDataField  = relField.getRelDataField();
            AttributeDef relAttributeDef = relDataField.getAttributeDef();
            EntityDef    relEntityDef = relAttributeDef.getEntityDef();
            DataRecord    relDataRecord = relEntityDef.getDataRecord();

            stmt.from.append( stmt.getTableName( relDataRecord ) )
                     .append( "." )
                     .append( relDataField.getName() )
                     .append( " = " )
                     .append( stmt.getTableName( srcDataRecord ) )
                     .append( "." )
                     .append(  srcDataField.getName() );
        }


        QualEntity childQualEntity = qualMap.get( entityDef );
        if ( childQualEntity != null )
        {
            StringBuilder sb = buildQualString( stmt, view, entityDef, true );
            stmt.from.append( sb );
        }

        if ( entityDef.getAutoSeq() != null )
            stmt.appendOrdering( entityDef.getAutoSeq() );

        return true;
    }

    /**
     * Adds the foreign keys to the current select.
     * @param stmt
     * @param entityDef
     * @param rootEntity
     * @return True if all FKs are non-null and select should be executed,
     *         False if at least one FK is null.
     */
    private boolean addForeignKeys( SqlStatement stmt, View view, EntityDef entityDef, EntityDef rootEntityDef )
    {
        boolean rootEntity = ( rootEntityDef == entityDef );

        // If this is not the root entity, lets make sure the parent entity was added.
        if ( ! rootEntity )
        {
            assert entityDef.getParent() != null;

            // We don't care about the return code because it is not a problem if the parent
            // was already added.
            addForeignKeys( stmt, view, entityDef.getParent(), rootEntityDef );
        }

        DataRecord dataRecord = entityDef.getDataRecord();
        RelRecord  relRecord  = dataRecord.getRelRecord();

        // If we are dealing with a m-to-m relationship lets first add the correspondence
        // table to the query.
        if ( relRecord != null && relRecord.getRelationshipType().isManyToMany() )
        {
            boolean added = stmt.addTableToFrom( relRecord.getRecordName(), relRecord, true );
            if ( ! added )
                return true;  // The table was already added so we don't need to add it again.

            stmt.fromConjunctionNeeded = false;

            // Look for the rel fields that have a parent entity as the source and add them
            // to the join statement.
            for ( RelField relField : relRecord.getRelFields() )
            {
                DataField     srcDataField  = relField.getSrcDataField();
                AttributeDef srcAttributeDef = srcDataField.getAttributeDef();
                EntityDef    srcEntityDef = srcAttributeDef.getEntityDef();
                if ( srcEntityDef == entityDef )
                    continue;  // Source is not a parent.

                // If entityDef is the root then we need to add the qualification to the WHERE
                // clause because the root table is not being joined with any parent tables.
                if ( rootEntity )
                {
                    if ( stmt.conjunctionNeeded )
                        stmt.where.append(" AND ");

                    stmt.appendWhere( stmt.getTableName( relRecord ), ".", relField.getFieldName() );

                    // If we get here then we are building the foreign keys for the
                    // entity that we are loading.  In this case the key values from
                    // the parent entities have already been loaded so we only need
                    // to copy the attribute values from the parent entities.

                    // If all entity instances of the EntityDef we are loading are to
                    // be loaded at once, then instead of qualifying on a single key we
                    // want to create an IN clause with all the parent keys.
                    if ( selectAllInstances( entityDef ) )
                    {
                        if ( ! addAllParentFks( stmt, entityDef, srcDataField ) )
                            return false; // No non-null attrs found so don't bother loading.
                    }
                    else
                    {
                        if ( ! getAttributeValueEquality( stmt, stmt.where, srcDataField, view.cursor( srcEntityDef ) ) )
                            return false; // Attribute is null--don't bother loading it.
                    }

                    stmt.conjunctionNeeded = true;
                }
                else
                {
                    if ( stmt.fromConjunctionNeeded )
                        stmt.from.append(" AND ");
                    else
                        stmt.from.append(" ON ");

                    stmt.appendFrom( stmt.getTableName( relRecord ), ".", relField.getFieldName() );
                    stmt.appendFrom( " = ", stmt.getTableName( srcEntityDef.getDataRecord() ), ".", srcDataField.getName() );
                    stmt.fromConjunctionNeeded = true;
                }
            }
        }

        // If the table name for entityDef is not already part of the SELECT
        // statement then add it to the FROM clause.
        boolean added = stmt.addTableToFrom( entityDef.getDataRecord().getRecordName(), entityDef.getDataRecord(), true );
        if ( ! added )
            return true;  // The table was already added so we don't need to add it again.

        // The FROM conjunction needs to be reset each time we come in to addForeignKeys because each JOIN is separate.
        stmt.fromConjunctionNeeded = false;

        // Add the rel fields.
        for ( RelField relField : relRecord.getRelFields() )
        {
            DataField     srcDataField  = relField.getSrcDataField();
            AttributeDef srcAttributeDef = srcDataField.getAttributeDef();
            EntityDef    srcEntityDef = srcAttributeDef.getEntityDef();
            DataRecord    srcDataRecord = srcEntityDef.getDataRecord();
            DataField     relDataField  = relField.getRelDataField();

            if ( relRecord.getRelationshipType() == RelRecord.MANY_TO_MANY )
            {
                // The keys for the correspondence table has already been added, so we'll
                // skip keys where the source is not the entityDef.
                if ( srcEntityDef != entityDef )
                    continue;

                if ( stmt.fromConjunctionNeeded )
                    stmt.from.append(" AND ");
                else
                    stmt.from.append(" ON ");

                stmt.appendFrom( stmt.getTableName( dataRecord ), ".", srcDataField.getName() );
                stmt.appendFrom( " = " );
                stmt.appendFrom( stmt.getTableName( relRecord ), ".", relField.getFieldName() );
                stmt.fromConjunctionNeeded = true;
            } // if relationship = many-to-many
            else
            if ( relRecord.getRelationshipType() == RelRecord.ONE_TO_MANY )
            {
                AttributeDef relAttributeDef = relDataField.getAttributeDef();
                EntityDef    relEntityDef = relAttributeDef.getEntityDef();
                DataRecord    relDataRecord = relEntityDef.getDataRecord();

                if ( rootEntity )
                {
                    if ( stmt.conjunctionNeeded )
                        stmt.appendWhere( " AND " );

                    stmt.appendWhere( stmt.getTableName( relDataRecord ),
                                      ".", relDataField.getName() );

                    // If we get here then we are building the foreign keys for the
                    // entity that we are loading.  In this case the key values from
                    // the parent entities have already been loaded so we only need
                    // to copy the attribute values from the parent entities.

                    // If all entity instances of the EntityDef we are loading are to
                    // be loaded at once, then instead of qualifying on a single key we
                    // want to create an IN clause with all the parent keys.
                    if ( selectAllInstances( entityDef ) )
                    {
                        assert dataRecord.getRelRecord().getRelFields().size() == 1;
                        assert dataRecord.getRelRecord().getRelationshipType().isOneToMany();

                        if ( ! addAllParentFks( stmt, entityDef, srcDataField ) )
                            return false; // No non-null attrs found so don't bother loading.
                    }
                    else
                    {
                        // Add a single FK value.
                        if ( ! getAttributeValueEquality( stmt, stmt.where, srcDataField, view.cursor( srcEntityDef ) ) )
                            return false; // Attribute is null--don't bother loading it.
                    }

                    stmt.conjunctionNeeded = true;
                }
                else
                {
                    if ( stmt.fromConjunctionNeeded )
                 	   stmt.from.append(" AND ");
                    else
                 	   stmt.from.append(" ON ");

                    stmt.appendFrom( stmt.getTableName( relDataRecord ), ".", relDataField.getName() );

                    // If we get here then we are building the foreign keys for an
                    // entity/table that is being used to qualify the entity/table
                    // that we are loading.  This means that the foreign key values
                    // must come from a parent table that is part of the current
                    // select.
                    stmt.appendFrom( " = ",
                                      stmt.getTableName( srcDataRecord ),
                                      ".", srcDataField.getName() );
                    stmt.fromConjunctionNeeded = true;
                }
            } // if relationship = ONE_TO_MANY
            else
            {
                assert relRecord.getRelationshipType() == RelRecord.MANY_TO_ONE;

                AttributeDef relAttributeDef = relDataField.getAttributeDef();
                EntityDef    relEntityDef = relAttributeDef.getEntityDef();
                DataRecord    relDataRecord = relEntityDef.getDataRecord();


                if ( rootEntity ) // DGC 2011-11-14: Is this necessary?  Isn't rootEntity always false?
                {
                    if ( stmt.conjunctionNeeded )
                        stmt.appendWhere( " AND " );

                    stmt.appendWhere( stmt.getTableName( srcDataRecord ), ".", srcDataField.getName());

                    if ( ! getAttributeValueEquality( stmt, stmt.where, relDataField, view.cursor( relEntityDef ) ) )
                        return false; // Attribute is null--don't bother loading it.
                    stmt.conjunctionNeeded = true;
                }
                else
                {
                    if ( stmt.fromConjunctionNeeded )
                 	   stmt.from.append(" AND ");
                    else
                 	   stmt.from.append(" ON ");
                    stmt.appendFrom( stmt.getTableName( srcDataRecord ), ".", srcDataField.getName());

                    // TODO: We may be able to use an attribute value instead of a column.
                    // See logic in kzhsqlga.c.
                    stmt.appendFrom( " = ",
                                      stmt.getTableName( relDataRecord ),
                                      ".", relDataField.getName() );
                    stmt.fromConjunctionNeeded = true;
                }
            } // if relationship = MANY_TO_ONE
        } // for each relField...

        return true;
    }

    private boolean addAllParentFks( SqlStatement stmt, EntityDef entityDef, DataField srcDataField )
    {
        boolean foundNonNullValues = false;
        EntityDef parent = entityDef.getParent();
        assert loadedInstances.containsKey( parent );

        stmt.appendWhere( " IN ( " );
        for ( EntityInstance ei : loadedInstances.get( parent ).values() )
        {
            // Edge case: when loading entities with a limit we may end up dropping
            // some EI's.  Skip any entities that are flagged as hidden.
            if ( ei.isHidden() )
                continue;

            if ( ei.getAttribute( srcDataField.getAttributeDef() ).isNull() )
                continue;

            if ( foundNonNullValues )
                stmt.appendWhere( ", " );

            foundNonNullValues = true;
            getAttributeValue( stmt, stmt.where, srcDataField, ei, true );
        }

        stmt.appendWhere( " ) " );

        return foundNonNullValues;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.DbHandler#deleteEntity(com.quinsoft.zeidon.View, com.quinsoft.zeidon.EntityInstance, java.lang.Object)
     */
    @Override
    public int deleteEntity(View view, EntityInstance entityInstance)
    {
        EntityDef entityDef = entityInstance.getEntityDef();
        DataRecord dataRecord = entityDef.getDataRecord();

        SqlStatement stmt = initializeCommand( SqlCommand.DELETE, view );
        task.dblog().debug( "Delete entity %s, table name = %s", entityDef.getName(), dataRecord.getRecordName() );

//        stmt.appendCmd( "DELETE FROM ", dataRecord.getRecordName(), " " );
        stmt.appendCmd( "DELETE " );
        stmt.addTableToFrom( dataRecord.getRecordName(), dataRecord, false );
        stmt.buildWhere( stmt, entityInstance );
        executeStatement( view, entityDef, entityInstance, stmt );
        return 0;
    }

    @Override
    public int deleteAllChildren(View view, EntityInstance parent)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public int deleteRelationship(View view, EntityInstance entityInstance)
    {
        EntityDef entityDef = entityInstance.getEntityDef();
        DataRecord dataRecord = entityDef.getDataRecord();
        RelRecord relRecord = dataRecord.getRelRecord();

        // The only thing that needs to be done is to delete the correspondence
        // table if the relationship is many-to-many.
        if ( relRecord.getRelationshipType() != RelRecord.MANY_TO_MANY )
            return 0;

        SqlStatement stmt = initializeCommand( SqlCommand.DELETE, view );
        task.dblog().debug( "Deleting entity relationship %s, table name = %s",
                            entityDef.getName(), relRecord.getRecordName() );

        stmt.appendCmd( "DELETE FROM " );
        stmt.appendCmd( relRecord.getRecordName() );
        stmt.appendCmd( " WHERE " );
        boolean first = true;
        for ( RelField relField : relRecord.getRelFields() )
        {
            if ( first )
                first = false;
            else
                stmt.appendCmd( " AND " );

            stmt.appendCmd( relField.getFieldName() );
            stmt.appendCmd( " = " );

            // If the EntityDef of the relfield is NOT the EntityDef of the entityInstance
            // we need to find the source instance.
            DataField srcDataField = relField.getSrcDataField();
            EntityDef srcEntityDef = srcDataField.getAttributeDef().getEntityDef();
            if ( srcEntityDef == entityDef )
                getAttributeValue( stmt, stmt.sqlCmd, srcDataField, entityInstance );
            else
                getAttributeValue( stmt, stmt.sqlCmd, srcDataField, view.cursor( srcEntityDef ) );
         }

        //stmt.buildWhere( stmt, entityInstance );
        //stmt.assembleSql();
        executeStatement( view, entityDef, entityInstance, stmt );

        return 0;
    }

    @Override
    public int insertRelationship(View view, EntityInstance entityInstance)
    {
        EntityDef entityDef = entityInstance.getEntityDef();
        DataRecord dataRecord = entityDef.getDataRecord();
        RelRecord relRecord = dataRecord.getRelRecord();

        // Nothing needs to be done unless the relationship is many-to-many because
        // FK's are handled by the updates.
        if ( relRecord.getRelationshipType() != RelRecord.MANY_TO_MANY )
            return 0;

        SqlStatement stmt = initializeCommand( SqlCommand.INSERT, view );
        task.dblog().debug( "Inserting entity relationship %s, table name = %s",
                            entityDef.getName(), relRecord.getRecordName() );

        stmt.appendCmd( "INSERT INTO ", relRecord.getRecordName(), " ( " );
        boolean first = true;
        for ( RelField relField : relRecord.getRelFields() )
        {
            if ( first )
                first = false;
            else
                stmt.appendCmd( ", " );

            stmt.appendCmd( relField.getFieldName() );
        }

        // Add the auto seq if it exists.
        DataField autoSeq = dataRecord.getDataField( entityDef.getAutoSeq() );
        if ( autoSeq != null )
            stmt.appendCmd( ", ", autoSeq.getName() );

        stmt.appendCmd( "  ) VALUES ( " );
        first = true;
        for ( RelField relField : relRecord.getRelFields() )
        {
            if ( first )
                first = false;
            else
                stmt.appendCmd( ", " );

            // If the EntityDef of the relfield is NOT the EntityDef of the entityInstance
            // we need to find the source instance.
            DataField srcDataField = relField.getSrcDataField();
            EntityDef srcEntityDef = srcDataField.getAttributeDef().getEntityDef();
            if ( srcEntityDef == entityDef )
                getAttributeValue( stmt, stmt.sqlCmd, srcDataField, entityInstance );
            else
                getAttributeValue( stmt, stmt.sqlCmd, srcDataField, view.cursor( srcEntityDef ) );
        }

        if ( autoSeq != null )
        {
            stmt.appendCmd( ", " );
            getAttributeValue( stmt, stmt.sqlCmd, autoSeq, entityInstance );
        }

        stmt.appendCmd( " )" );

        executeStatement( view, entityDef, entityInstance, stmt );

        return 0;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.DbHandler#updateEntity(com.quinsoft.zeidon.View, com.quinsoft.zeidon.EntityInstance, java.lang.Object)
     */
    @Override
    public int updateEntity(View view, EntityInstance entityInstance)
    {
        EntityDef entityDef = entityInstance.getEntityDef();
        DataRecord dataRecord = entityDef.getDataRecord();

        SqlStatement stmt = initializeCommand( SqlCommand.UPDATE, view );
        task.dblog().debug( "Updating entity %s, table name = %s", entityDef.getName(), dataRecord.getRecordName() );

        boolean updateCorrespondenceTable = false;

        stmt.appendCmd( "UPDATE ", dataRecord.getRecordName(), "\nSET    " );  // Extra spaces after SET to match indentation.
        int updateCount = 0;
        for ( DataField dataField : dataRecord.dataFields() )
        {
            AttributeDef attributeDef = dataField.getAttributeDef();
            if ( ! attributeDef.isPersistent() )
                continue;

            if ( attributeDef.isKey() )
            {
                if ( entityInstance.getAttribute( attributeDef ).isUpdated() )
                    throw new ZeidonException( "Trying to update key %s", attributeDef.toString() );

                if ( entityInstance.getAttribute( attributeDef ).isNull() )
                    throw new ZeidonException( "Key %s is null for update.", attributeDef.toString() );
            }

            if ( ! entityInstance.getAttribute( attributeDef ).isUpdated() )
                continue;

            // If this is an autosequencing attribute then we need to check to see if the
            // relationship is m-to-m.  If it is then we need to update the correspondence
            // table instead of the main table.
            if ( attributeDef.isAutoSeq() && entityDef.getParent() != null )
            {
                RelRecord relRecord = dataRecord.getRelRecord();
                if ( relRecord.getRelationshipType().isManyToMany() )
                {
                    updateCorrespondenceTable = true;
                    continue;
                }
            }

            if ( updateCount > 0 )
                stmt.appendCmd( ",\n" );

            updateCount++;

            stmt.appendCmd( dataField.getName(), " = " );
            getAttributeValue( stmt, stmt.sqlCmd, dataField, entityInstance );
        }

        // If there were no attributes found to need updating then there's nothing to do.
        if ( updateCount > 0 )
        {
            stmt.buildWhere( stmt, entityInstance );
            executeStatement( view, entityDef, entityInstance, stmt );
        }

        // Update the correspondence table only if the entity wasn't created/excluded.
        // If it was c/i then the correspondence table was just created and doesn't
        // need to be updated.
        if ( updateCorrespondenceTable && ! entityInstance.isCreated() && ! entityInstance.isIncluded() )
        {
            throw new ZeidonException( "Updating correspondance table not supported yet" );
        }

        return 0;
    }

    /**
     * This object is used to keep track of the different parts of a SQL statement.
     * Since statements can be nested (e.g. subselect) there can be nested SqlStatements.
     *
     */
    public class SqlStatement
    {
        final SqlCommand commandType; // Insert, select, etc.
        int componentCount;     // Number of components in statement.
        int subLevel;           // Number of sub-levels in statement; usually 0.
        int activateLimit = 0;  // Max number of entities to activate.  If 0 then activate all.
        private final StringBuilder sqlCmd;   // The final results.
        private final StringBuilder from;     // Holds FROM clause.
        private final StringBuilder insertValues;  // Holds values during insert.
        private final StringBuilder columnList;  // Holds column list.
        private final StringBuilder where;    // Holds WHERE clause.
        private final StringBuilder ordering; // Holds "order by" clause.
        private       StringBuilder suffix;   // DB-specific db-handlers put stuff here.
        private final LinkedHashMap<DataField, Integer> columns; // List of datafields in the column list.
        private final Map<Object,String> tables; // List of tables in the statement.  Key = table name, value = alias

        private final List<Object> boundValues;

        /**
         * List of DataRecords involved in the SQL.  For each DataRecord, the list of DataFields.
         */
        final Map<DataRecord, List<DataField>>  dataRecords;

        final EntityDef selectRoot;  // For select commands, this is the entity we're loading.

        /**
         * The view we are loading/commiting.
         */
        final View targetView;

        boolean containsSubselect;
        boolean qualUsesChildEntity;

        /**
         * If true, then a conjunction (AND/OR) is needed before the next predicate
         * is added to the SELECT...WHERE clause.
         */
        boolean conjunctionNeeded = false;
        boolean fromConjunctionNeeded = false;

        SqlStatement parent;    // If current SqlStatement is a sub-select, this points to parent select.
        private String assembledCommand;

        SqlStatement( SqlCommand commandType, View view, EntityDef selectRoot )
        {
            this.commandType = commandType;
            sqlCmd = new StringBuilder();
            from =   new StringBuilder();
            where =  new StringBuilder();
            columnList =  new StringBuilder();
            ordering = new StringBuilder();
            columns = new LinkedHashMap<DataField, Integer>();
            tables  = new HashMap<Object,String>();
            dataRecords = new LinkedHashMap<DataRecord, List<DataField>>();
            boundValues = new ArrayList<Object>();
            this.selectRoot = selectRoot;
            targetView = view;

            if ( commandType == SqlCommand.INSERT )
                insertValues = new StringBuilder();
            else
                insertValues = null;
        }

        /**
         * @param dataRecord
         * @param dataField
         */
        public void addColumn( StringBuilder colName, DataRecord dataRecord, DataField dataField )
        {
            if ( columns.size() > 0 )
                columnList.append( ", " );

            columnList.append( colName );

            assert ! columns.containsKey( dataField ); // Make sure we haven't added this datafield already.
            columns.put( dataField, columns.size() + 1 );

            List<DataField> list = dataRecords.get( dataRecord );
            if ( list == null )
            {
                list = new ArrayList<DataField>();
                dataRecords.put( dataRecord, list );
            }

            assert ! list.contains( dataField );
            list.add( dataField );

            // Keep track of the column number so we can use it later for retrieval.

        }

        boolean whereIsEmpty()
        {
            return where.length() == 0;
        }

        void addBoundAttribute( StringBuilder buffer, Object value )
        {
            boundValues.add( value );
            buffer.append( " ? " );
        }

        void buildWhere(SqlStatement stmt, EntityInstance entityInstance)
        {
            EntityDef entityDef = entityInstance.getEntityDef();
            DataRecord dataRecord = entityDef.getDataRecord();

            for ( DataField dataField : dataRecord.dataFields() )
            {
                AttributeDef AttributeDef = dataField.getAttributeDef();
                if ( ! AttributeDef.isKey() )
                    continue;

                if ( conjunctionNeeded )
                    appendWhere( " AND " );

                appendWhere( getTableName( dataRecord ),
                             ".", dataField.getName() );

                if ( entityInstance.getAttribute( AttributeDef ).isNull() )
                    appendWhere(" IS NULL " );
                else
                {
                    appendWhere( " = " );
                    getAttributeValue( stmt, where, dataField, entityInstance );
                }

                // We just added a predicate, so a conjunction is needed
                // before the next predicate is added.
                conjunctionNeeded = true;
            }
        }

        void appendCmd(Object...objects)
        {
            for ( Object o : objects )
                sqlCmd.append( o );
        }

        void appendFrom(Object...objects)
        {
            for ( Object o : objects )
                from.append( o );
        }

        void appendWhere(Object...objects)
        {
            for ( Object o : objects )
                where.append( o );
        }

        void appendColumn(Object...objects)
        {
            for ( Object o : objects )
                columnList.append( o );
        }

        void appendInsertValues(Object...objects)
        {
            for ( Object o : objects )
                insertValues.append( o );
        }

        /**
         * Adds the default ordering for entityDef as defined in the LOD.
         *
         * @param entityDef
         */
        void appendOrdering( EntityDef entityDef )
        {
            final AttributeDef autoSeq = entityDef.getAutoSeq();
            if ( autoSeq != null )
                appendOrdering( autoSeq );

            if ( entityDef.getSequencingAttributes() != null )
            {
                for ( AttributeDef attributeDef : entityDef.getSequencingAttributes() )
                    appendOrdering( attributeDef );
            }

        }

        void appendOrdering( AttributeDef attributeDef )
        {
            appendOrdering( attributeDef.getEntityDef().getDataRecord().getDataField( attributeDef ), attributeDef.isAutoSeq() );

            if ( ! attributeDef.isSequencingAscending() )
                ordering.append( " DESC " );
        }

        void appendOrdering( AttributeDef attributeDef, boolean descending )
        {
            appendOrdering( attributeDef.getEntityDef().getDataRecord().getDataField( attributeDef ), false );

            if ( descending )
                ordering.append( " DESC " );
        }

        /**
         * Adds the column represented by dataField.  If the column is an autoseq
         * field then we may have to use the correspondance table.
         *
         * @param dataField
         * @param isAutoSeq
         */
        void appendOrdering( DataField dataField, boolean isAutoSeq )
        {
            if ( ordering.length() > 0 )
                ordering.append( ", " );

            DataRecord dataRecord = dataField.getAttributeDef().getEntityDef().getDataRecord();
            if ( isAutoSeq )
            {
                RelRecord relRecord = dataRecord.getRelRecord();
                if ( relRecord.getRecordName() != null )
                    ordering.append( getTableName( relRecord ) );
                else
                    ordering.append( getTableName( dataRecord ) );
            }
            else
                ordering.append( getTableName( dataRecord ) );

            ordering.append( "." )
                    .append( dataField.getName() );
        }

        public void appendSuffix( Object value )
        {
            if ( suffix == null )
                suffix = new StringBuilder();

            suffix.append( value );
        }

        /**
         * Returns the alias name for a table (or the table name if there is no alias).
         *
         * @param Object source The source of the table name, either a DataRecord or RelRecord.
         * @return
         */
        String getTableName( String tableName, Object source )
        {
            assert ( source instanceof DataRecord ) || ( source instanceof RelRecord );

            // Already exists?
            String name = tables.get( source );
            if ( name != null )
                return name;

            // Is an alias needed?
            String alias = null;
            if ( tables.containsValue( tableName ) )
            {
                // We've got a table listed twice but with different relationships.  We need to create
                // an alias name.
                for ( int i = 1; i < 40; i++ )
                {
                    if ( ! tables.containsValue( tableName + i ) )
                    {
                        alias = tableName + i;
                        break;
                    }
                }

                if ( alias == null )
                    throw new ZeidonException( "We ran out of alias names.  There's probably an internal error somewhere." );

                tables.put( source, alias );
            }
            else
                tables.put( source, tableName );

            return tables.get( source );
        }

        String getTableName( EntityDef entityDef )
        {
            DataRecord dataRecord = entityDef.getDataRecord();
            return getTableName( dataRecord.getRecordName(), dataRecord );
        }

        String getTableName( DataRecord dataRecord )
        {
            return getTableName( dataRecord.getRecordName(), dataRecord );
        }

        String getTableName( RelRecord relRecord )
        {
            return getTableName( relRecord.getRecordName(), relRecord );
        }

        /**
         * Adds the table name to the FROM buffer.  This routine is smart enough to figure out
         * if an alias name is to be created.
         *
         * Returns true if table was added, false if it wasn't (meaning it
         * was already in the statement).
         *
         * @param tableName The name of the table in the DB.
         * @param source The source object for the table name, either a DataRecord or RelRecord.
         * @param checkForComma If true, add a comma before the table name if there are multiple tables.
         */
        boolean addTableToFrom(String tableName, Object source, boolean checkForComma )
        {
            assert ! StringUtils.isBlank( tableName );
            assert ( source instanceof DataRecord ) || ( source instanceof RelRecord );

            if ( tables.containsKey( source ) )
                return false;

            if ( checkForComma && tables.size() > 0 )
            {
            	if ( activatingWithJoins() )
            	{
          	        from.append( " LEFT JOIN\n");
            	}
            	else
                    from.append( ",");
            }

            String alias = getTableName( tableName, source );
            from.append( " " ).append( tableName );
            if ( ! StringUtils.equals( tableName, alias ) )
                from.append( " " ).append( alias );

            return true;
        }

        /**
         * Append the SQL string in 'source' to 'str'.  Break up 'source' into multiple
         * lines if it is longer than maxLineLth.
         *
         * @param str
         * @param source
         * @param prettyPrint
         */
        private void addStringWithWrapping( StringBuilder str, StringBuilder source, boolean prettyPrint )
        {
            // If we're not pretty-printing just append and get out.
            if ( ! prettyPrint )
            {
                str.append( source );
                return;
            }

            int maxLineLth = 120;
            String indent = null;

            int sourceIdx = 0;
            int sourceLth = source.length();
            while ( sourceIdx <= sourceLth )
            {
                // See if there are any '\n' in current line.
                int idx = sourceIdx;
                while ( idx < sourceIdx + maxLineLth && idx < sourceLth && source.charAt( idx ) != '\n' )
                    idx++;

                // Did we find a new-line?
                if ( idx >= sourceLth || source.charAt( idx ) != '\n' )
                {
                    // No. Does the rest of the line need wrapping?
                    if ( sourceLth - sourceIdx <= maxLineLth )
                    {
                        // No.  Append the rest of source and call it a day.
                        str.append( source.substring( sourceIdx ) );
                        return;
                    }

                    // Find the last whitespace character so we can break up the line.
                    idx = sourceIdx + maxLineLth; // Start looking at the end of the line.
                    while ( idx > sourceIdx && source.charAt( idx ) != ' ' )
                        idx--;

                    // If idx = sourceIdx then we have a really long table+column name.  Who's
                    // writing these applications?  Find the first whitespace *after*  the long
                    // name.
                    if ( idx == sourceIdx )
                    {
                        idx = sourceIdx + maxLineLth + 1;
                        while ( idx < sourceLth && source.charAt( idx ) != ' ' )
                            idx++;
                    }
                }

                str.append( source.subSequence( sourceIdx, idx ) ).append( '\n' );
                idx++;
                while ( idx < sourceLth && source.charAt( idx ) == ' ' ) // Skip over any additional whitespaces.
                    idx++;
                sourceIdx = idx;

                if ( indent == null )
                {
                    // Indent is null, this means we are wrapping the first line.
                    indent = "       ";

                    // From now on we're going to insert 'indent' at the beginning of every
                    // line.  To make calculations easier we'll subtract the length of 'indent'
                    // from the max length and pretend there is no indent.
                    maxLineLth -= indent.length();
                }

                str.append( indent );
            }
        }

        String getAssembledCommand()
        {
            // Assemble the command string if it hasn't been done already.
            if ( assembledCommand == null )
                assembledCommand = assembleCommand( false );

            return assembledCommand;
        }

        @Override
        public String toString()
        {
            return assembleCommand( true );
        }

        private String assembleCommand( boolean prettyPrint )
        {
            StringBuilder str = new StringBuilder();
            addStringWithWrapping( str, sqlCmd, prettyPrint );
            addStringWithWrapping( str, columnList, prettyPrint );
            if ( from.length() > 0 )
            {
                str.append( "\nFROM " );
                addStringWithWrapping( str, from, prettyPrint );
            }

            if ( insertValues != null )
                addStringWithWrapping( str, insertValues, prettyPrint );

            if ( where.length() > 0 )
            {
                str.append( "\nWHERE " );
                addStringWithWrapping( str, where, prettyPrint );

            }

            if ( ordering.length() > 0 )
            {
                str.append( "\nORDER BY " );
                addStringWithWrapping( str, ordering, prettyPrint );
            }

            if ( suffix != null )
                str.append( "\n" ).append( suffix );

            str.append( ";" );

            return str.toString();
        }

        public List<Object> getBoundValues()
        {
            return boundValues;
        }

        public Map<DataField, Integer> getColumns()
        {
            return columns;
        }
    } // class SqlStatement

    /*
     * Check to see if we've created the cached data for this LodDef. If we have,
     * return it, otherwise create it and put it in the cache.
     */
    EntityDefSqlData getEntityDefData( EntityDef entityDef )
    {
        EntityDefSqlData data = entityDef.getCacheMap().get( EntityDefSqlData.class );
        if ( data != null )
            return data;

        data = new EntityDefSqlData( entityDef );
        data = entityDef.getCacheMap().put( EntityDefSqlData.class, data );
        return data;
    }

    /**
     * This class stores SQL-specific data in the EntityDef cachemap.
     *
     */
    static class EntityDefSqlData
    {
        private final EntityDef entityDef;

        /**
         * This is the list of children that can be loaded by joining
         * with the parent entityDef.
         */
        private final ImmutableList<EntityDef> joinedChildren;

        /**
         * @param entityDef
         */
        private EntityDefSqlData(EntityDef entityDef)
        {
            this.entityDef = entityDef;

            Builder<EntityDef> builder = ImmutableList.builder();
            addJoinedChildren( builder, this.entityDef );
            joinedChildren = builder.build();
        }

        private void addJoinedChildren( Builder<EntityDef> builder, EntityDef parent )
        {
            for ( EntityDef child : parent.getChildren() )
            {
                if ( isJoinable( child ) )
                {
                    builder.add( child );
                    addJoinedChildren( builder, child );
                }
            }
        }

        ImmutableList<EntityDef> getJoinedChildren()
        {
            return joinedChildren;
        }

        private boolean isJoinable( EntityDef def )
        {
            if ( def.getParent() == null )
                return false;

            //TODO: We can't handle recursive records yet.
            if ( def.isRecursive() )
                return false;

            // If there is an activate limit for this entity then we can't join it
            // with it's parent because we have to set the limit in the SQL call.
            if ( def.getActivateLimit() != null )
                return false;

            if ( def.getLazyLoadConfig().isLazyLoad() )
                return false;

            DataRecord dataRecord = def.getDataRecord();
            if ( dataRecord == null )
                return false;

            if ( def.isAutoloadFromParent() )
            {
                RelRecord relRecord = dataRecord.getRelRecord();

                // If the entity is flagged as AUTOLOAD FROM PARENT and the relationship
                // is many-to-one then we can load this entity by using the values from
                // its parent.  In that situation we don't want to join this entity.
                if ( relRecord.getRelationshipType().isManyToOne() )
                    return false;
            }

            return dataRecord.isJoinable();
        }

        boolean loadedByParent()
        {
            return isJoinable( entityDef );
        }
    }

    /**
     * Writes the SQL to the log if either log() or dblog() has DEBUG on.
     *
     * @param sql
     */
    protected void logSql( String sql )
    {
        if ( task.log().isDebugEnabled() )
            task.log().debug( "<SQL>\n%s\n</SQL>", sql );
        else
        if ( task.dblog().isDebugEnabled() )
            task.dblog().debug( "<SQL>\n%s\n</SQL>", sql );
    }

    protected void logSql( SqlStatement sql )
    {
        if ( task.log().isDebugEnabled() || task.dblog().isDebugEnabled() )
            logSql( sql.toString() );
    }

    public Integer getInsertCount()
    {
        if ( insertCount == null )
        {
            String value = getConfigValue( "InsertCount" );

            try
            {
                if ( StringUtils.isBlank( value ) )
                    insertCount = 0;
                else
                    insertCount = Integer.parseInt( value );
            }
            catch ( Exception e )
            {
                throw ZeidonException.prependMessage( e, "InsertCount = %s", value );
            }
        }

        return insertCount;
    }

    public boolean isBindAllValues()
    {
        if ( isBindAllValues == null )
        {
            String value = getConfigValue( "BindAllValues" );

            // If it's not specified then we'll look at whether dblog debug is enabled.  We figure
            // that if debug is on that the user would like to see the SQL statements with the values
            // embedded in the SQL.
            if ( StringUtils.isBlank( value ) )
                return task.dblog().isDebugEnabled() ? false  // Debug is enabled so don't bind all attributes.
                                                     : true;  // Debug is off so we'll bind everything.

            isBindAllValues = "TY1".contains( value.substring( 0, 1 ).toUpperCase() );  // Catches "true", "yes", "1".
        }

        return isBindAllValues;
    }

    /**
     * Checks the zeidon.ini to see if we should ignore joins.
     * @return
     */
    public boolean ignoreJoins()
    {
        if ( ignoreJoins == null )
        {
            String value = getConfigValue( "IgnoreJoins" );

            if ( StringUtils.isBlank( value ) )
                ignoreJoins = false;
            else
                ignoreJoins = "TY1".contains( value.substring( 0, 1 ).toUpperCase() );  // Catches "true", "yes", "1".
        }

        return ignoreJoins;
    }

    public String getPassword()
    {
        return getConfigValue( "Password" );
    }

    public String getUserName()
    {
        return getConfigValue( "Username" );
    }

    @Override
    public PessimisticLockingHandler getPessimisticLockingHandler( ActivateOptions activateOptions , View view  )
    {
        LockingLevel lockLevel = activateOptions.getLockingLevel();
        if ( ! lockLevel.isPessimisticLock() )
            return NOOP_PESSIMISTIC_LOCKING_HANDLER;

        if ( lockLevel == LockingLevel.PESSIMISTIC_WITHREAD && activateOptions.isReadOnly() )
            return NOOP_PESSIMISTIC_LOCKING_HANDLER;

        PessimisticLockingHandler lockingHandler = new PessimisticLockingViaDb( activateOptions, qualMap );
        view.addViewCleanupWork( lockingHandler );
        return lockingHandler;
    }
}
