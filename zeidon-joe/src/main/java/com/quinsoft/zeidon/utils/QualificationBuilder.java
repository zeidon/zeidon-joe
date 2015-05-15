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

package com.quinsoft.zeidon.utils;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.dbhandler.DbHandler;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LockingLevel;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * A builder for creating and manipulating Qualification objects.
 *
 * @author DG
 *
 */
public class QualificationBuilder
{
    public static final String QUAL_XOD_NAME = "kzdbhqua";
    public static final String ENTITYSPEC    = "EntitySpec";
    public static final String ENTITYNAME    = "EntityName";
    public static final String QUALATTRIB    = "QualAttrib";
    public static final String ATTRIBUTENAME = "AttributeName";
    public static final String OPER          = "Oper";
    public static final String VALUE         = "Value";
    public static final String KEYLIST       = "KeyList";

    private final Task        task;
    private       Task        cacheTask;
    private       Application application;
    private       View        qualView;
    private       int         entitySpecCount;
    private       String      cacheViewName;
    private final ActivateOptions activateOptions;

    /**
     * EntityInstance of the EntitySpec that qualifies the
     * root of the target LodDef.
     */
    private EntityInstance rootInstance;

    /**
     * When doing the activate we will synchronize using this object.  May be changed
     * by cachedAs().  Defaults to the QualificationBuilder.
     */
    private Object synch;

    /**
     * If non-null, then attempt to relink this instance with the root after the activate.
     */
    private EntityInstance sourceEntityInstance;

    /**
     * Creates an empty qualification object.
     * @param task
     */
    public QualificationBuilder( TaskQualification taskQual )
    {
        this.task = taskQual.getTask();
        cacheTask = task;
        application = task.getApplication();
        qualView = task.activateEmptyObjectInstance( QUAL_XOD_NAME, task.getSystemTask().getApplication() );
        activateOptions = new ActivateOptions( taskQual.getTask() );
        activateOptions.setQualificationObject( qualView );
        entitySpecCount = 0;
        synch = this;
    }

    /**
     * @deprecated  Use QualificationBuilder( task ).setLodDef(...) instead.
     */
    @Deprecated
    public QualificationBuilder( TaskQualification taskQual, String lodDefName )
    {
        this( taskQual );
        setLodDef( lodDefName );
    }

    public QualificationBuilder setApplication( Application application )
    {
        this.application = application;
        return this;
    }

    public QualificationBuilder setApplication( String application )
    {
        this.application = task.getApplication( application );
        return this;
    }

    public QualificationBuilder setLodDef( LodDef lodDef )
    {
        activateOptions.setLodDef( lodDef );
        return this;
    }

    public QualificationBuilder setLodDef( String lodDefName )
    {
        return setLodDef( application.getLodDef( task, lodDefName ) );
    }

    /**
     * Use setLodDef instead.
     */
    @Deprecated
    public QualificationBuilder setViewOd( String lodDefName )
    {
        return setLodDef( application.getLodDef( task, lodDefName ) );
    }

    public QualificationBuilder setLodDef( String applicationName, String lodDefName )
    {
        setApplication( applicationName );
        return setLodDef( application.getLodDef( task, lodDefName ) );
    }

    public LodDef getLodDef()
    {
        return activateOptions.getLodDef();
    }

    public QualificationBuilder loadFile( String filename )
    {
        LodDef qualLodDef = task.getSystemTask().getApplication().getLodDef( task, QUAL_XOD_NAME );
        qualView = task.deserializeOi().setLodDef( qualLodDef ).fromResource( filename ).activateFirst();
        activateOptions.setQualificationObject( qualView );
        return this;
    }

    /**
     * Loads the qualifcation from a serialized JSON string.
     * @param qualString
     * @return
     */
    public QualificationBuilder loadFromSerializedString( String qualString )
    {
        LodDef qualLodDef = task.getSystemTask().getApplication().getLodDef( task, QUAL_XOD_NAME );
        qualView = task.deserializeOi().setLodDef( qualLodDef ).asJson().fromString( qualString ).activateFirst();
        activateOptions.setQualificationObject( qualView );
        return this;
    }

    public QualificationBuilder singleRoot()
    {
        return setFlag( ActivateFlags.fSINGLE );
    }

    public QualificationBuilder rootOnlyMultiple()
    {
        rootOnly();
        multipleRoots();
        return this;
    }

    public QualificationBuilder rootOnly()
    {
        getRootInstance().getAttribute( "RootOnly" ).setValue( "Y" );
        return setFlag( ActivateFlags.fROOT_ONLY );
    }

    public QualificationBuilder readOnly()
    {
        return setFlag( ActivateFlags.fREAD_ONLY );
    }

    public QualificationBuilder multipleRoots()
    {
        getRootInstance().getAttribute( "MultipleRoots" ).setValue( "Y" );
        return setFlag( ActivateFlags.fMULTIPLE );
    }

    public QualificationBuilder limitCountTo( int limit )
    {
        qualView.cursor( ENTITYSPEC ).getAttribute( "ActivateLimit" ).setValue( limit );
        return setFlag( ActivateFlags.fMULTIPLE );
    }

    public QualificationBuilder setAsynchronous()
    {
        return asynchronous();
    }

    public QualificationBuilder asynchronous()
    {
        /*  Why can't we do async?  Removing this for now.
        if ( sourceEntityInstance != null )
            throw new ZeidonException( "The qualification source entity is configured to be relinked after " +
            		                   "the activate is finished.  This is incompatible with asynchronous config." );
        */

        return setFlag( ActivateFlags.fASYNCHRONOUS );
    }

    public QualificationBuilder setFlag( ActivateFlags flag )
    {
        activateOptions.addActivateFlag( flag );
        return this;
    }

    /**
     * Gives this qualification a name.  This name may be used by the underlying dbhandler
     * for performing the activate.
     *
     * @param qualName
     * @return
     */
    public QualificationBuilder setQualificationName( String qualName )
    {
        activateOptions.setQualificationName( qualName );
        return this;
    }

    public QualificationBuilder setOiSourceUrl( String url )
    {
        activateOptions.setOiSourceUrl( url );
        return this;
    }

    /**
     * Cache this object in the System task using cacheName to name the view.
     *
     * @param cacheName
     * @return
     */
    public QualificationBuilder cachedAs( String cacheName )
    {
        return cachedAs( cacheName, task.getSystemTask(), cacheName );
    }

    /**
     * Cache this object in the specified task using cacheName to name the view.
     *
     * @param cacheName
     * @param taskQual
     * @return
     */
    public QualificationBuilder cachedAs( String cacheName, TaskQualification taskQual )
    {
        return cachedAs( cacheName, taskQual.getTask(), cacheName ); // We'll default sync object to view name.
    }

    public QualificationBuilder cachedAs( String cacheName, TaskQualification taskQual, Object syncObject )
    {
        this.cacheViewName = cacheName;
        this.synch = syncObject;
        cacheTask = taskQual.getTask();
        return this;
    }

    /**
     * @deprecated  Use forEntity() instead.
     */
    @Deprecated
    public QualificationBuilder entitySpec( String entityName )
    {
        return forEntity( entityName );
    }

    /**
     * Adds qualification to a non-root entity.  Mirrors the RESTRICTING clause
     * in VML.  Synonym for forEntity().
     *
     * @param entityName
     * @return
     */
    public QualificationBuilder restricting( String entityName )
    {
        return forEntity( entityName );
    }

    /**
     * @return The EntityInstance for the root EntitySpec.  Creates it if necessary.
     */
    private EntityInstance getRootInstance()
    {
        if ( rootInstance == null )
        {
            String rootName = getLodDef().getRoot().getName();

            if ( qualView.cursor( ENTITYSPEC ).setFirst( "EntityName", rootName ) != CursorResult.SET )
            {
                entitySpecCount++;
                qualView.cursor( ENTITYSPEC ).createEntity().getAttribute( "EntityName" ).setValue( rootName );
            }

            rootInstance = qualView.cursor( ENTITYSPEC ).getEntityInstance();
        }

        return rootInstance;
    }

    public QualificationBuilder forEntity( String entityName )
    {
        if ( getLodDef() == null )
            throw new ZeidonException( "Must specify LodDef before setting qualification" );

        if ( qualView.cursor( ENTITYSPEC ).setFirst( "EntityName", entityName ) != CursorResult.SET )
        {
            entitySpecCount++;
            qualView.cursor( ENTITYSPEC ).createEntity().getAttribute( "EntityName").setValue( entityName );

            // If this is the root then get the cursor.
            if ( rootInstance == null && getLodDef().getRoot().getName().equals( entityName ) )
                rootInstance = qualView.cursor( ENTITYSPEC ).getEntityInstance();
        }

        return this;
    }

    private void validateEntity()
    {
        // If we haven't created an entity spec for this qual OI then we'll automatically create
        // one for the root entity.
        if ( entitySpecCount == 0 )
            forEntity( getLodDef().getRoot().getName() );

    }

    /**
     * Excludes an entity type and its children from the activation.
     *
     * @param oper
     * @return
     */
    public QualificationBuilder excludeEntity( String entityName )
    {
        // Save the current entitySpec.  We'll reset current entitySpec to this
        // after adding the exclude.
        String currentEntity = qualView.cursor( ENTITYSPEC ).getAttribute( "EntityName" ).getString();

        restricting( entityName ).addAttribQual( "EXCLUDE" );

        // Since there should never be any other qualification for something that is exluded
        // we'll reset the current entitySpec back to the original.
        qualView.cursor( ENTITYSPEC ).setFirst( "EntityName", currentEntity );

        return this;
    }

    /**
     * @deprecated  Use addAttribQual() instead.
     */
    @Deprecated
    public QualificationBuilder newAttribSpec( String oper )
    {
        return addAttribQual( oper );
    }

    public QualificationBuilder addAttribQualEntityExists( String entityName )
    {
        validateEntity();
        qualView.cursor( QUALATTRIB ).createEntity()
                                     .setAttribute( ENTITYNAME, entityName )
                                     .setAttribute( OPER, "EXISTS" );
        return this;
    }

    public QualificationBuilder addAttribQual( String oper )
    {
        validateEntity();
        qualView.cursor( QUALATTRIB ).createEntity().setAttribute( OPER, oper );
        return this;
    }

    public QualificationBuilder addAttribQual( String attribName, Object attribValue )
    {
        validateEntity();
        return addAttribQual( qualView.cursor( ENTITYSPEC ).getAttribute( ENTITYNAME ).getString(), attribName, "=", attribValue );
    }

    @Deprecated // use addAttribQual instead.
    public QualificationBuilder newAttribSpec( String attribName, String attribValue )
    {
        return addAttribQual( qualView.cursor( ENTITYSPEC ).getAttribute( ENTITYNAME ).getString(), attribName, "=", attribValue );
    }

    /**
     * @deprecated  Use forEntity() instead.
     */
    @Deprecated
    public QualificationBuilder newAttribSpec( String attribName, String oper, String attribValue )
    {
        return addAttribQual( qualView.cursor( ENTITYSPEC ).getAttribute( ENTITYNAME ).getString(), attribName, oper, attribValue );
    }

    public QualificationBuilder newEntityKey( Integer key )
    {
        qualView.cursor( KEYLIST ).createEntity()
                                  .setAttribute( "IntegerValue", key );
        return this;
    }

    public QualificationBuilder newEntityKey( String key )
    {
        qualView.cursor( KEYLIST ).createEntity()
                                  .setAttribute( "StringValue", key );
        return this;
    }

    /**
     * @deprecated  Use addAttribQual() instead.
     */
    @Deprecated
    public QualificationBuilder newAttribSpec( String entityName, String attribName, String oper, String attribValue )
    {
        return addAttribQual( entityName, attribName, oper, attribValue );
    }

    public QualificationBuilder addAttribQual( String entityName, String attribName, String oper, Object attribValue )
    {
        validateEntity();
        qualView.cursor( QUALATTRIB ).createEntity()
                                     .setAttribute( ENTITYNAME, entityName )
                                     .setAttribute( ATTRIBUTENAME, attribName )
                                     .setAttribute( OPER, oper )
                                     .setAttribute( VALUE, attribValue == null ? null : attribValue.toString() );

        return this;
    }

    /**
     * Adds qualification to activate the source entity instance.
     *
     * @param source
     * @return
     */
    public QualificationBuilder fromEntityKeys( EntityInstance source )
    {
        return fromEntityKeys( source, true );
    }

    /**
     * Use the keys from 'source' to qualify the entity.
     *
     * @param source
     * @param linkEntities if true and the target entity is the root then the entities will
     * be relinked after the activate.  This is used to activate a list with rootonly-multiple
     * and then activating one of the roots.  The root will be be current if the OI is updated.
     *
     * @return
     */
    public QualificationBuilder fromEntityKeys( EntityInstance source, boolean linkEntities )
    {
        EntityDef entityDef = source.getEntityDef();

        if ( entityDef.getKeys().size() > 1 )
            addAttribQual( "(" );

        boolean firstKey = true;
        for ( AttributeDef key : entityDef.getKeys() )
        {
            if ( firstKey )
                firstKey = false;
            else
                addAttribQual( " AND " );

            addAttribQual( key.getName(), source.getAttribute( key ).getString() );
        }

        if ( entityDef.getKeys().size() > 1 )
            addAttribQual( ")" );

        if ( linkEntities == true )
        {
            if ( activateOptions.getActivateFlags().contains( ActivateFlags.fASYNCHRONOUS ) )
                throw new ZeidonException( "This activate is configured to be asynchronous.  This is incompatable " +
                		                   "with a linked source instance.  Use fromEntityKeys(..., false)." );

            sourceEntityInstance = source;
        }

        return this;
    }

    /**
     * Activate using the specified locking level.
     *
     * @param lockingLevel
     * @return
     */
    public QualificationBuilder withLocking( LockingLevel lockingLevel )
    {
        activateOptions.setLockingLevel( lockingLevel );
        return this;
    }

    /**
     * Activate with the default locking level as specified in the LOD.
     *
     * @return
     */
    public QualificationBuilder withLocking()
    {
        return withLocking( getLodDef().getLockingLevel() );
    }

    public View getView()
    {
        return qualView;
    }

    public View activate()
    {
        View view = null;

        // In case we're loading a cached object, synchronize using 'synch'.  If we're not
        // loading a cached object then sync = 'this' so it's basically a no-op.  If we're loading
        // a cached object then synch is required to be an object that will prevent multiple
        // threads from loading the same cached object at the same time.  Usually the viewName
        // is good enough.
        synchronized ( synch )
        {
            // Are we dealing with a cached view?
            if ( StringUtils.isBlank( cacheViewName ) )
                // No.  Make sure cacheTask and task are the same.
                assert cacheTask == task : "Unequal tasks for non-cached activate";
            else
                // Yes.  Attempt to get the view by name in the cacheTask.
                view = cacheTask.getViewByName( cacheViewName );

            if ( view == null )
            {
                view = cacheTask.activateObjectInstance( activateOptions );

                // Are we dealing with a cached view?
                if ( ! StringUtils.isBlank( cacheViewName ) )
                    // Yes.  Name the view for later.
                    cacheTask.setNameForView( cacheViewName, view );
            }

            // If the source instance is specified and is the same type as the root, then relink.
            if ( sourceEntityInstance != null )
            {
                EntityDef root = getLodDef().getRoot();
                if ( root == sourceEntityInstance.getEntityDef() && view.cursor( root ).getEntityCount() == 1 )
                    view.cursor( root ).linkInstances( sourceEntityInstance );

                // Null out the value so that it can be dropped by the GC if it's not
                // longer needed.
                sourceEntityInstance = null;
            }

            qualView.setInternal( true ); // So it doesn't show up in the browser.
            return view;
        }
    }

    /**
     * @deprecated  Use setLodDef(...).activate() instead.
     */
    @Deprecated
    public View activate( String lodDefName )
    {
        setLodDef( lodDefName );
        return activate();
    }

    public static View generateQualFromSingleRootValue( TaskQualification task,
                                                        String            lodDefName,
                                                        String            attributeName,
                                                        Object            value )
    {
        Application app = task.getApplication();
        LodDef lodDef = app.getLodDef( task, lodDefName );

        QualificationBuilder qual = new QualificationBuilder( task ).setLodDef( lodDefName );
        qual.forEntity( lodDef.getRoot().getName() ).addAttribQual( attributeName, value.toString() );

        return qual.getView();
    }

    public static View generateQualFromEntityList( View sourceView, String entityName, String scopingEntity )
    {
        EntityCursor cursor = sourceView.cursor( entityName );
        EntityDef entityDef = cursor.getEntityDef();

        // Find the key.
        AttributeDef key = null;
        for ( AttributeDef attrib : entityDef.getKeys() )
        {
            key = attrib;
        }

        QualificationBuilder qual = new QualificationBuilder( sourceView )
                                            .setLodDef( sourceView.getLodDef() )
                                            .forEntity( DbHandler.ROOT_ENTITY )
                                            .addAttribQual( key.getName(), null );
        for ( EntityInstance ei : cursor.eachEntity() )
            qual.newEntityKey( ei.getAttribute( key ).getInteger() );

        return qual.getView();
    }

    public static View activateFromFile( Task task, String filename )
    {
        LodDef qua = task.getSystemTask().getApplication().getLodDef( task, "kzdbhqua" );
        return task.activateOiFromFile( qua, filename, null );
    }
}