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

    Copyright 2009-2014 QuinSoft
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
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.GenKeyHandler;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.DataField;
import com.quinsoft.zeidon.objectdefinition.DataRecord;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.objectdefinition.RelField;
import com.quinsoft.zeidon.objectdefinition.RelRecord;
import com.quinsoft.zeidon.objectdefinition.RelRecord.RelationshipType;
import com.quinsoft.zeidon.standardoe.OiRelinker;

/**
 * @author DG
 *
 */
public abstract class AbstractSqlHandler implements DbHandler, GenKeyHandler
{
    private static final long COL_KEYS_ONLY = 0x00000001;
    private static final long COL_NO_HIDDEN = 0x00000002;
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

    protected AbstractSqlHandler( Task task, AbstractOptionsConfiguration options )
    {
        this.task = task;
        this.application = options.getApplication();
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
            stmt.appendCmd( "  ) VALUES " );

            EntityInstance entityInstance = null;
            for ( int count = 0; count < maxInserts && idx < numInserts; count++ )
            {
                if ( count == 0 ) // First row?
                    stmt.appendCmd( "\n ( " );
                else
                    stmt.appendCmd( " ),\n ( " );

                entityInstance = entityInstances.get( idx );
                addColumnValueList( stmt, entityInstance );

                idx++;
            }

            stmt.appendCmd( " )" );
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
            AttributeDef AttributeDef = dataField.getAttributeDef();
            if ( useDbGenerateKeys() && AttributeDef.isGenKey() && entityInstance.getAttribute( AttributeDef ).isNull() )
                continue;

            if ( firstColumn )
                firstColumn = false;
            else
                stmt.appendCmd( ", " );

            getAttributeValue( stmt, stmt.sqlCmd, dataField, entityInstance );
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
     * Add the attribute value to the buffer.
     * @param stmt TODO
     * @param buffer
     * @param dataField
     * @param entityInstance
     */
    void getAttributeValue(SqlStatement stmt, StringBuilder buffer, DataField dataField, EntityInstance entityInstance, boolean ignoreBind )
    {
        if ( ignoreBind == false && isBindAllValues() )
        {
            // There can be multiple INSERT statements for a single SQL command.  We need to bind the attribute
            // value instead of the data field.
            if ( stmt.commandType == SqlCommand.INSERT )
            {
                Object value = entityInstance.getInternalAttributeValue( dataField.getAttributeDef() );
                stmt.addBoundAttribute( buffer, value );
                return;
            }

            stmt.addBoundAttribute( buffer, dataField );
            return;
        }

        AttributeDef attributeDef = dataField.getAttributeDef();
        Object value = entityInstance.getInternalAttributeValue( attributeDef );
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
            if ( entityInstance.isAttributeNull( dataField.getAttributeDef() ) )
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
            AttributeDef AttributeDef = dataField.getAttributeDef();
            if ( ( control & COL_KEYS_ONLY ) != 0 && AttributeDef.isKey() )
                continue;

            // If nControl indicates that we don't want hidden attributes, then
            // don't use hidden attributes UNLESS THEY ARE KEYS.  Keys, even if
            // they are hidden, should be included.  Same thing with auto sequencing
            // attributes.
            if ( ( control & COL_NO_HIDDEN ) == 0 &&
                 ( AttributeDef.isHidden() && ! AttributeDef.isKey() &&
                                            ! AttributeDef.isForeignKey() &&
                                            ! AttributeDef.isAutoSeq() ))
            {
                continue;
            }

            // If the attribute is an Auto Seq attribute and the relationship
            // is many-to-many then the attribute is stored in the corresponding
            // table.  If the command type is also INSERT then the attribute is
            // not to be included in this list.
            if ( AttributeDef.isAutoSeq() &&
                 relRecord != null && relRecord.getRelationshipType() == RelRecord.MANY_TO_MANY &&
                 stmt.commandType == SqlCommand.INSERT )
            {
                continue;
            }

            // Skip the attribute if it wasn't updated.
            if ( entityInstance != null && stmt.commandType != SqlCommand.INSERT)
            {
                if ( ! entityInstance.isAttributeUpdated( AttributeDef ) )
                    continue;
            }

            if ( stmt.commandType == SqlCommand.INSERT && useDbGenerateKeys() )
            {
                // If the DB is generating the keys then don't add generated keys to
                // the list of columns UNLESS the key is not null.  If the key is not
                // null then someone must have set it on purpose and we'll use that value.
                if ( AttributeDef.isGenKey() && entityInstance.getAttribute( AttributeDef ).isNull() )
                    continue;
            }

            // TODO: Add dbOper code?

            StringBuilder colName = new StringBuilder();

            // Add table name.
            if ( ( control & COL_FULL_QUAL ) != 0 )
            {
                String tableName;
                if ( AttributeDef.isAutoSeq() &&
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
            if ( stmt.columns.size() > 0 )
                stmt.appendCmd( ", " );

            stmt.appendCmd( colName );
            stmt.addColumn( dataRecord, dataField );
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

            stmt.appendCmd( colName );
            stmt.addColumn( dataRecord, relField.getSrcDataField() );
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
            if ( entitySpec.isAttributeNull( "EntityName" ) )
                throw new ZeidonException("Qualification view is missing entity name in EntitySpec" );

            String entityName = entitySpec.getStringFromAttribute( "EntityName" );
            if ( StringUtils.isBlank( entityName ) )
                throw new ZeidonException("Qualification view is missing entity name in EntitySpec" );

            EntityDef entityDef = lodDef.getEntityDef( entityName );
            QualEntity qualEntity = new QualEntity( entitySpec, entityDef );
            qualMap.put( entityDef, qualEntity );

            int parenCount = 0;

            for ( EntityInstance qualAttribInstance : entitySpec.getChildren( "QualAttrib" ) )
            {
                //
                // Verify Oper
                //
                if ( qualAttribInstance.isAttributeNull( "Oper" ) )
                    throw new ZeidonException( "QualAttrib for " + entityName + " is missing Oper" );

                QualAttrib qualAttrib = new QualAttrib( qualAttribInstance.getStringFromAttribute( "Oper" ) );
                if ( qualAttrib.oper.equals( "EXCLUDE" ) )
                {
                    qualEntity.exclude = true;
                    continue;
                }

                if ( qualEntity.exclude )
                    throw new ZeidonException( "Entity '%s' has EXCLUDE but has additional qualifcation",
                                               qualEntity.entityDef.getName() );

                parenCount += CharSetUtils.count( qualAttrib.oper, "(" );
                parenCount -= CharSetUtils.count( qualAttrib.oper, ")" );

                //
                // Verify EntityName
                //
                if ( ! qualAttribInstance.isAttributeNull( "EntityName"  ) )
                {
                    String qualEntityName = qualAttribInstance.getStringFromAttribute( "EntityName" );
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
                        qualAttrib.oper = "=";

                    qualAttrib.attributeDef = qualAttrib.entityDef.getKeys().get( 0 );
                }

                //
                // Verify AttribName
                //
                if ( ! qualAttribInstance.isAttributeNull( "AttributeName"  ) || qualAttrib.attributeDef != null )
                {
                    if ( qualAttrib.attributeDef == null )
                    {
                        String attribName = qualAttribInstance.getStringFromAttribute( "AttributeName" );
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
                        // Find the rel field for the qualifying attribute.
                        for ( RelField relField : relRecord.getRelFields() )
                        {
                            // Change the column we are qualifying on.
                            DataField dataField = relField.getRelDataField();
                            qualAttrib.attributeDef = dataField.getAttributeDef();
                            qualAttrib.entityDef = qualAttrib.attributeDef.getEntityDef();

                            dataRecord = qualAttrib.entityDef.getDataRecord();
                            relRecord = dataRecord.getRelRecord();
                        }
                    }
                }

                //
                // Verify Value
                //
                if ( ! qualAttribInstance.isAttributeNull( "Value"  ) )
                {
                    if ( qualAttrib.attributeDef == null )
                        throw new ZeidonException("QualAttrib with value requires Entity.Attrib");

                    // Get the string value from the qualification object, convert it to the
                    // domain's internal value, and then to an a string representation of the
                    // internal value.
                    String value = qualAttribInstance.getAttribute( "Value" ).getString();
                    Domain domain = qualAttrib.attributeDef.getDomain();
                    qualAttrib.value = domain.convertExternalValue( task, null, qualAttrib.attributeDef, null, value );
                }

                //
                // Verify KeyList
                //
                StringBuilder keyList = null;

                for ( EntityInstance kl : qualAttribInstance.getChildren( "KeyList" ) )
                {
                    if ( keyList == null )
                        keyList = new StringBuilder();
                    else
                        keyList.append( ", " );

                    keyList.append( kl.getStringFromAttribute( "IntegerValue" ) );
                }

                if ( keyList != null && ! StringUtils.isBlank( keyList.toString() ) )
                    qualAttrib.keyList = keyList.toString();

                //
                // Verify SourceViewName
                //
                if ( ! qualAttribInstance.isAttributeNull( "SourceViewName"  ) )
                    throw new ZeidonException( "SourceViewName is currently unsupported for Activate Qualification" );

                if ( ! qualAttribInstance.isAttributeNull( "SourceViewID"  ) )
                    throw new ZeidonException( "SourceViewID is not supported by Java Object Engine" );

                //
                // Verify SourceEntityName
                //
                if ( ! qualAttribInstance.isAttributeNull( "SourceEntityName"  ) )
                    throw new ZeidonException( "SourceEntityName is currently unsupported for Activate Qualification" );

                if ( ! qualAttribInstance.isAttributeNull( "SourceAttributeName"  ) )
                    throw new ZeidonException( "SourceAttributeName is currently unsupported for Activate Qualification" );

                // =================================================================
                // ===
                // ===  Validate Qualification attributes.
                // ===
                // =================================================================

                // TODO: Add the rest of the checks in fnSqlRetrieveQualAttrib.

                qualEntity.addQualAttrib( qualAttrib );
            } // for each QualAttrib...

            assert parenCount == 0;

        } // for each EntitySpec...
    } // method loadQualificationObject

    @Override
    public int beginActivate( View view, View qual, EnumSet<ActivateFlags> control )
    {
        this.qual = qual;
        this.activateFlags = control;
        activateOptions = (ActivateOptions) options;
        loadQualificationObject( view.getLodDef() );

        loadedViewEntities = new HashSet<>();

        determineEntitiesThatCanBeLoadedInOneSelect( view );

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

        for ( EntityDef entityDef : view.getLodDef().getViewEntitiesHier() )
        {
            EntityDef parent = entityDef.getParent();
            if ( parent == null )
                continue;

            if ( entityCanBeLoadedAtOnce( entityDef ) )
            {
                loadedInstances.put( parent, new HashMap<Object, EntityInstance>() );
                loadInOneSelect.add( entityDef );
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
        if ( loadInOneSelect.contains( entityDef ) )
        {
            EntityDef parent = entityDef.getParent();
            if ( loadedInstances.containsKey( parent ) )
                return true;
        }

        return false;
    }

    protected boolean activatingWithJoins()
    {
        if ( activateFlags.contains( ActivateFlags.fIGNORE_JOINS ) )
            return false;

        if ( activateFlags.contains( ActivateFlags.fROOT_ONLY ) )
            return false;

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

        List<EntityDef> jc = joinableChildren.get( entityDef );
        if ( jc != null )
            return jc;

        // We need to keep the set of
        jc = new ArrayList<EntityDef>();

        // Keep track of entityDefs that are not joinable.  None of their
        // children are joinable either.
        ArrayList<EntityDef> notJoinable = new ArrayList<EntityDef>();

        // Get the list of all children that can be joinable.
        for ( EntityDef child : getEntityDefData( entityDef ).getJoinedChildren() )
        {
            boolean ignoreBecauseOfParent = false;
            for ( EntityDef parent = child.getParent(); parent != null; parent = parent.getParent() )
            {
                if ( notJoinable.contains( parent ) )
                {
                    ignoreBecauseOfParent = true;
                    break;
                }
            }

            if ( ignoreBecauseOfParent == false && isJoinable( child ) )
                jc.add( child );
            else
                notJoinable.add( child );
        }

        joinableChildren.put( entityDef, Collections.unmodifiableList( jc ) );
        return jc;
    }

    protected boolean isJoinable( EntityDef entityDef )
    {
        if ( ! activatingWithJoins() )
            return false;

        QualEntity qualEntity = qualMap.get( entityDef );
        if ( qualEntity != null )
            return false;

        //TODO: Add check to see if child is qualified.  If it is then it's not joinable.

        return getEntityDefData( entityDef ).isJoinable( entityDef );
    }

    /**
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
            // If the table is qualified then we're going to add more stuff later
            // so let's add an opening paren.
            if ( qualEntity != null )
                stmt.appendWhere( "(" );

            // Pass true to indicate that entityDef is the root of this SELECT.
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
            addQualification( stmt, view, entityDef );

            // If the parent is non-null then we added foreign keys above.  Add
            // the closing paren.
            if ( entityDef.getParent() != null )
                stmt.appendWhere( ")" );
        }

        // TODO: Add ORDER BY if we have an activate limit.

        stmt.appendOrdering( entityDef );
        for ( EntityDef child : joinedChildren )
            stmt.appendOrdering( child );

        if ( qualEntity != null && qualEntity.activateLimit != null )
            addActivateLimit( qualEntity.activateLimit, stmt );
        else
        if ( entityDef.getActivateLimit() != null )
            addActivateLimit( entityDef.getActivateLimit(), stmt );

        return null;
    }

    private boolean entityCanBeLoadedAtOnce( EntityDef entityDef )
    {
        // If we're activating as part of a lazy load then we shouldn't be loading all
        // instances because lazy load indicates we want partial loading.
        if ( activateOptions.isPerformingLazyLoad() )
            return false;

        if ( activateOptions.getActivateFlags().contains( ActivateFlags.fIGNORE_LOAD_OPTIMIZATION ) )
            return false;

        // Can't load children of recursive entities.
        for ( EntityDef ve = entityDef; ve != null; ve = ve.getParent() )
        {
            if ( ve.isRecursive() )
                return false;
        }

        // If this is being joined with its parent then we don't need to load the
        // child in a separate SELECT.
        if ( isJoinable( entityDef ) )
            return false;

        DataRecord childRecord = entityDef.getDataRecord();
        if ( childRecord == null )
            return false;

        RelRecord relRecord = childRecord.getRelRecord();
        if ( relRecord == null )
            return false;

        RelationshipType relType = relRecord.getRelationshipType();
        if ( relType == null )
            return false;

        if ( relType.isManyToOne() )
            return false;

        // We can only handle it if there is a single key between child
        // and parent.
        if ( relType.isOneToMany() && relRecord.getRelFields().size() > 1 )
            return false;

        if ( relType.isManyToMany() && relRecord.getRelFields().size() > 2 )
            return false;

        if ( entityDef.getParent() != null && qualMap.containsKey( entityDef ) )
            return false;

        return true;
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

        // If this entity was already loaded through a join with its parent then there's nothing to do.
        if ( isJoinable( entityDef ) )
        {
            assert assertNotNullKey( view, entityDef ) : "Activated entity has null key";
            task.dblog().trace( "Loaded via parent join" );
            return DbHandler.LOAD_DONE;
        }

        // If we've already loaded this entity and the entity can be loaded all at once then
        // we've loaded all the instances and we're done.
        if ( loadedViewEntities.contains( entityDef ) && selectAllInstances( entityDef ) )
            return DbHandler.LOAD_DONE;

        loadedViewEntities.add( entityDef );

        // TODO: Implement autoload from parent.
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
        if ( parent != null && entityCanBeLoadedAtOnce( entityDef ) )
            parentEi = view.cursor( parent ).getEntityInstance();

        executeLoad( view, entityDef, stmt );

        if ( parentEi != null )
            view.cursor( parent ).setCursor( parentEi );

        assert assertNotNullKey( view, entityDef ) : "Activated entity has null key";

        return DbHandler.LOAD_DONE;
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
            return false;

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

    private void addQualification(SqlStatement stmt, View view, EntityDef entityDef)
    {
        QualEntity qualEntity = qualMap.get( entityDef );

        // Go through each of the QualAttrib's looking for tables that are not
        // already part of the select.
        for ( QualAttrib qualAttrib : qualEntity.qualAttribs )
        {
            // Add the table to the SELECT statement only if the QualAttrib has
            // a lpEntityDef and a lpAttributeDef.
            if ( qualAttrib.entityDef == null || qualAttrib.attributeDef == null )
                continue;

            addForeignKeys( stmt, view, qualAttrib.entityDef, entityDef );
        }

        if ( stmt.conjunctionNeeded )
        {
            stmt.appendWhere( " AND \n" );

            // KJS 09/12/13 - Ran into problems when we had a RESTRICTING with several "OR" operators. The SQL was "... AND xxx OR xxx OR xxx "
            // as opposed to "... AND (xxx OR xxx OR xxx )".
            stmt.appendWhere( "(" );
        }

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
                stmt.appendWhere( " ", qualAttrib.oper, " " );
                continue;
            }

            // QualAttrib has an entity name, so this is an expression.  Add
            // the expression to the WHERE clause.

            // TODO : add code for subselect commands (like "EXISTS")

            DataRecord dataRecord = qualAttrib.entityDef.getDataRecord();
            DataField  dataField = dataRecord.getDataField( qualAttrib.attributeDef );

            String col = dataField.getName();
            stmt.appendWhere( stmt.getTableName( dataRecord ), ".", col, " " );

            if ( qualAttrib.keyList != null )
            {
                stmt.appendWhere( " IN ( ", qualAttrib.keyList, " ) " );
                continue;
            }

            boolean isNull = false;
            if ( qualAttrib.value == null || StringUtils.isBlank( qualAttrib.value.toString() ) )
                isNull = true;

            if ( isNull )
            {
                // Value is NULL.  Create appropriate SQL.
                if ( qualAttrib.oper.equals( "<>" ) || qualAttrib.oper.equals( "!=" ) )
                    stmt.appendWhere( " IS NOT NULL " );
                else
                    stmt.appendWhere( " IS NULL " );
            }
            else
            {
                Domain domain = dataField.getAttributeDef().getDomain();
                StringBuilder buffer = new StringBuilder();
                getSqlValue( stmt, domain, qualAttrib.attributeDef, buffer, qualAttrib.value );
                stmt.appendWhere( qualAttrib.oper, " ", buffer.toString() );
            }
        }

        if ( stmt.conjunctionNeeded )
           stmt.appendWhere( ")" );

        stmt.conjunctionNeeded = true;
    }

    protected abstract int executeLoad(View view, EntityDef entityDef, SqlStatement stmt);
    protected abstract int executeStatement(View view, EntityDef entityDef, SqlStatement stmt);
    protected abstract void addActivateLimit( int limit, SqlStatement stmt );

    protected int executeStatement(View view, EntityDef entityDef, EntityInstance entityInstance, SqlStatement stmt)
    {
        try
        {
            return executeStatement( view, entityDef, stmt );
        }
        catch ( Throwable t )
        {
            task.dblog().error( "Entity that caused the error:", t, (Object[]) null );
            entityInstance.logEntity( false );
            throw ZeidonException.wrapException( t );
        }
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
    public Map<Integer, Integer> setGenKeys(View kzgkhwob, List<? extends View> viewList )
    {
        // This map will keep track of the next assignable genkey.  The map key is
        // ER entity token.
        Map<Integer, Integer> genkeyValues = new HashMap<Integer, Integer>();

        acquireGenkeyLock( kzgkhwob, viewList );

        try
        {
            // Get the current genkey values from the DB.
            View genkeys = task.activateObjectInstance( "GENKEYWO", null, EnumSet.of( ActivateFlags.fMULTIPLE ) );

            // Add the counts from kzgkhwob to the values from the DB.
            EntityCursor cursor = genkeys.cursor( "ZeidonGenkeyTable" );
            for ( EntityInstance genkey : kzgkhwob.cursor( "Genkey" ).eachEntity() )
            {
                String tableName = genkey.getStringFromAttribute( "TableName" );
                CursorResult rc = cursor.setFirst( "TableName", tableName );
                if ( ! rc.isSet() )
                {
                    // Genkey entry doesn't exist, so add it.
                    cursor.createEntity().setAttribute( "TableName", tableName  )
                                         .setAttribute( "CurrentGenkey", 0  );
                }

                genkeyValues.put( kzgkhwob.cursor( "Genkey" ).getIntegerFromAttribute( "EntityID" ),
                                  cursor.getIntegerFromAttribute( "CurrentGenkey" ) + 1 );
                Integer count = genkey.getIntegerFromAttribute( "EntityCount" );
                Integer c = cursor.getIntegerFromAttribute( "CurrentGenkey" );
                cursor.setAttribute( "CurrentGenkey", count + c );
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
        //stmt.addTableToFrom( relRecord.getRecordName(), relRecord, false );
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

            if ( ! entityInstance.isAttributeUpdated( attributeDef ) )
                continue;

            if ( attributeDef.isKey() )
               throw new ZeidonException( "Trying to update key %s", attributeDef.toString() );

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
        private final StringBuilder sqlCmd;   // The final results.
        private final StringBuilder from;     // Holds FROM clause.
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
            ordering = new StringBuilder();
            columns = new LinkedHashMap<DataField, Integer>();
            tables  = new HashMap<Object,String>();
            dataRecords = new LinkedHashMap<DataRecord, List<DataField>>();
            boundValues = new ArrayList<Object>();
            this.selectRoot = selectRoot;
            targetView = view;
        }

        /**
         * @param dataRecord
         * @param dataField
         */
        public void addColumn( DataRecord dataRecord, DataField dataField )
        {
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

                if ( entityInstance.isAttributeNull( AttributeDef ) )
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
        }

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

            if ( ! dataField.getAttributeDef().isSequencingAscending() )
                ordering.append( " DESC " );
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
                for ( int i = 1; i < 20; i++ )
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
                    from.append( " JOIN\n");
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
            if ( from.length() > 0 )
            {
                str.append( "\nFROM " );
                addStringWithWrapping( str, from, prettyPrint );
            }

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

    private static class QualEntity
    {
        private boolean                usesChildQualification;
        private boolean                exclude;
        private boolean                childQualIsManyToMany;
        private final Integer          activateLimit;
        private final EntityInstance   qualEntityInstance;
        private final EntityDef       entityDef;
        private final List<QualAttrib> qualAttribs;

        QualEntity(EntityInstance qualEntityInstance, EntityDef entityDef)
        {
            super();
            this.qualEntityInstance = qualEntityInstance;
            this.entityDef = entityDef;
            qualAttribs = new ArrayList<QualAttrib>();
            AttributeInstance limitAttr = qualEntityInstance.getAttribute( "ActivateLimit" );
            if ( limitAttr.isNull() )
                activateLimit = null;
            else
                activateLimit = limitAttr.getInteger();
        }

        void addQualAttrib( QualAttrib qualAttrib )
        {
            qualAttribs.add( qualAttrib );

            if ( qualAttrib.entityDef != null && qualAttrib.entityDef != entityDef )
            {
                usesChildQualification = true;

                // Check to see if there is a one-to-many or m-to-m relationship because if there
                // is we'll need to use "distinct" as part of the SQL.
                for ( EntityDef search = qualAttrib.entityDef; search != entityDef; search = search.getParent() )
                {
                    DataRecord dataRecord = search.getDataRecord();
                    if ( dataRecord == null )
                        throw new ZeidonException( "Trying to qualify %s by using the derived entity %s", entityDef, qualAttrib.entityDef );

                    RelRecord relRecord = dataRecord.getRelRecord();
                    if ( relRecord.getRelationshipType() != RelRecord.CHILD_IS_SOURCE )
                    {
                        // The child qual is either many-to-many or one-to-many.
                        childQualIsManyToMany = true;
                        break;
                    }
                }
            }
        }

        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            if ( entityDef != null )
                sb.append("EntityDef = ").append( entityDef.getName() ).append( " " );

            if ( qualAttribs.size() > 0 )
                sb.append( StringUtils.join( qualAttribs, ", " ) );

            return sb.toString();
        }
    }

    private static class QualAttrib
    {
        public Object value;
        String        oper;
        EntityDef    entityDef;
        String        keyList;
        AttributeDef attributeDef;

        private QualAttrib(String oper)
        {
            super();

            if ( oper.trim().toUpperCase().equals("!=" ) )
                this.oper = "<>";
            else
                this.oper = oper.trim().toUpperCase();
        }

        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            if ( entityDef != null )
                sb.append( entityDef.getName() ).append( "." ).append( attributeDef.getName() ).append( " "  );
            sb.append( oper ).append( " " );
            if ( value != null )
                sb.append( value );
            return sb.toString();
        }
    }

    /*
     * Check to see if we've created the cached data for this LodDef. If we have,
     * return it, otherwise create it and put it in the cache.
     */
    EntityDefSqlData getEntityDefData( EntityDef entityDef )
    {
        EntityDefSqlData data = entityDef.getCacheMap( EntityDefSqlData.class );
        if ( data != null )
            return data;

        data = new EntityDefSqlData( entityDef );
        data = entityDef.putCacheMap( EntityDefSqlData.class, data );
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

        private boolean isJoinable( EntityDef ve )
        {
            if ( ve.getParent() == null )
                return false;

            //TODO: We can't handle recursive records yet.
            if ( ve.isRecursive() )
                return false;

            if ( ve.getLazyLoadConfig().isLazyLoad() )
                return false;

            DataRecord dataRecord = ve.getDataRecord();
            if ( dataRecord == null )
                return false;

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
}
