/**
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
package com.quinsoft.zeidon.objectdefinition;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.CacheMap;
import com.quinsoft.zeidon.ObjectConstraintException;
import com.quinsoft.zeidon.ObjectConstraintType;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.UnknownEntityDefException;
import com.quinsoft.zeidon.UnknownLodDefException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.dbhandler.DbHandler;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.utils.CacheMapImpl;
import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileEntityHandler.NullEntityHandler;

/**
 * @author DG
 *
 */
public class LodDef implements PortableFileAttributeHandler
{
    private final Application  app;
    private String       name;
    private final String filename;
    private final Map<String, EntityDef> nameMap = new HashMap<String, EntityDef>();
    private List<EntityDef> entityList = new ArrayList<EntityDef>();
    private String       erDate;
    private boolean      hasGenKey;
    private String       genkeyHandler;
    private String       database;
    private boolean      activateConstraint;
    private boolean      commitConstraint;
    private String       constraintOper;
    private int          height = 0;
    private LockingLevel lockingLevel = LockingLevel.NONE;
    private SourceFileType sourceFileType = SourceFileType.VML;
    private String         sourceFileName;
    private boolean      hasLazyLoadEntities;
    private boolean      hasDuplicateInstances;

    /**
     * True if any entities in this LOD have DataRecords.
     */
    private boolean      hasPhysicalMappings = false;
    private String       libraryName;

    private CacheMap cacheMap;

    static private final Class<?>[] constructorArgTypes  = new Class<?>[] { View.class };

    public LodDef(Task task, Application app, String name) throws UnknownLodDefException
    {
        super();
        this.app = app;
        this.name = name;

        // Make sure the XOD exists.
        String xod = name + ".XOD";

        // We know that system apps XODs are stored as all lower-case.
        if ( app.isSystemApp() )
            xod = xod.toLowerCase();

        filename = app.getObjectDir() + "/" + xod;
        InputStream inputStream = JoeUtils.getInputStream( task, filename, getClass().getClassLoader() );
        if ( inputStream == null )
            throw new UnknownLodDefException(name, filename, app );

        loadLodDef( task, inputStream );
    }

    public Application getApplication()
    {
        return app;
    }

    public String getName()
    {
        return name;
    }

    /**
     * Returns the full filename of the XOD that defined this OD.
     * @return XOD file name
     */
    public String getFileName()
    {
        return filename;
    }

    public EntityDef getEntityDef( String entityName, boolean required, boolean ignoreCase )
    {
        // We allow the dbhandler to use a special string to indicate the
        // root.
        if ( StringUtils.equals( entityName, DbHandler.ROOT_ENTITY ) )
            return getEntityDef( 0 );  // Return the root LodDef.

        String searchName = entityName;
        if ( ignoreCase )
            searchName = searchName.toLowerCase();

        EntityDef entityDef = nameMap.get( searchName );
        if ( entityDef == null && required )
            throw new UnknownEntityDefException( this, entityName );

        return entityDef;
    }

    public EntityDef getEntityDef( String entityName, boolean required )
    {
        return getEntityDef( entityName, required, false );
    }

    public EntityDef getEntityDef( String entityName )
    {
        return getEntityDef( entityName, true, false );
    }

    public EntityDef getEntityDef( int index )
    {
        EntityDef entityDef = entityList.get( index );
        return entityDef;
    }

    private void loadLodDef( Task task, InputStream file )
    {
        try
        {
            PortableFileReader.ReadPortableFile( file, task.log(), new LodDefHandler( this ) );
            task.log().info( "LodDef %s loaded from: %s", this, filename );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( filename );
        }
    }

    private void addEntityDef( EntityDef entityDef )
    {
        nameMap.put( entityDef.getName(), entityDef );
        nameMap.put( entityDef.getName().toLowerCase(), entityDef );
        height = Math.max( height, entityDef.getDepth() );
        entityList.add( entityDef );
    }

    @Override
    public void setAttribute(PortableFileReader reader)
    {
        switch ( reader.getAttributeName() )
        {
            case "NAME":
                if ( ! reader.getAttributeValue().equalsIgnoreCase( name ) )
                    throw new ZeidonException("Name of object from XOD ('%s') doesn't match OD name", reader.getAttributeValue());

                // Override the name used when this object was being created with the one from the .xod file.
                // We need to do this because names are case-insensitive and the user may have used a different
                // case than the one from the file.
                name = reader.getAttributeValue().intern();
                break;

            case "ER_DATE":
                erDate = reader.getAttributeValue().intern();
                break;

            case "DATABASE":
            case "DFT_DBNANME":
                database = reader.getAttributeValue().intern();
                break;

            case "DBH_Data":
                // We don't actually use DBH_Data in JOE but this code is here because we need to
                // read past the blob data.
                int lth = Integer.parseInt( reader.getAttributeValue() );
                byte[] buffer = new byte[ lth ];
                try
                {
                    reader.getStreamReader().read( buffer, lth );
                }
                catch ( Throwable e )
                {
                    throw ZeidonException.wrapException( e );
                }
                break;

            case "GKHANDLER":
                genkeyHandler = reader.getAttributeValue().intern();
                break;

            case "LOCK":
                Integer level = Integer.valueOf( reader.getAttributeValue() );
                lockingLevel = LockingLevel.lookup( level );
                break;

            case "OCEOPER":
                constraintOper = reader.getAttributeValue().intern();
                break;

            case "OCACT":
                activateConstraint = reader.getAttributeValue().startsWith( "Y" );
                break;

            case "OCCOM":
                commitConstraint = reader.getAttributeValue().startsWith( "Y" );
                break;

            case "OCSRCFILE":
                sourceFileName = reader.getAttributeValue();
                if ( ! sourceFileName.contains( "." ) )
                    sourceFileName = getApplication().getPackage() + "." + sourceFileName;
                break;

            case "OCSRCTYPE":
                sourceFileType = SourceFileType.parse( reader.getAttributeValue() );
                break;

            case "OPER_LIBNM":
                libraryName = reader.getAttributeValue().intern();
                break;
        }
    }

    /**
     * Returns the list of EntityDefs in hierarchical order.
     *
     * @return
     */
    public List<EntityDef> getEntityDefs()
    {
        return entityList;
    }

    public int getEntityCount()
    {
        return entityList.size();
    }

    @Override
    public String toString()
    {
        return getApplication().toString() + "." + name;
    }

    public String getErDate()
    {
        return erDate;
    }

    public String getDatabase()
    {
        return database;
    }

    public EntityDef getRoot()
    {
        return entityList.get( 0 );
    }

    /**
     * Traces the LodDef to the log
     * @param task
     */
    public void displayLodDef( TaskQualification task )
    {
        task.log().info( "Displaying LodDef for %s", getName() );
        for ( EntityDef entityDef : getEntityDefs() )
        {
            task.log().info( "%s %d, count = %d", entityDef.getName(),
                              entityDef.getDepth(), entityDef.getChildCount() );
            for ( AttributeDef attrib : entityDef.getAttributes() )
            {
                Domain domain = attrib.getDomain();
                String type = domain == null ? attrib.getType().toString() : domain.getName();
                task.log().info( "    Attrib: %s %d Domain:%s",
                                 attrib.getName(), attrib.getAttributeNumber(), type );
            }

            DataRecord dataRecord = entityDef.getDataRecord();
            if ( dataRecord == null )
                continue;

            task.log().info( "    DataRecord: %s type=%s", dataRecord.getRecordName(), dataRecord.getType() );
            RelRecord relRecord = dataRecord.getRelRecord();
            if ( relRecord == null )
                continue;

            task.log().info( "    RelRecord: %s  type=%s", relRecord.getRecordName(), relRecord.getRelationshipType() );
            for ( RelField relField : relRecord.getRelFields() )
            {
                task.log().info( "        RelField: %s, %d, %d", relField.getFieldName(),
                                 relField.getRelToken(), relField.getSrcToken() );
            }
        }
    }

    void setHasGenKey(boolean hasGenKey)
    {
        this.hasGenKey = hasGenKey;
    }

    public boolean hasGenKey()
    {
        return hasGenKey;
    }

    public boolean hasActivateConstraint()
    {
        return activateConstraint;
    }

    public boolean hasCommitConstraint()
    {
        return commitConstraint;
    }

    private Object getConstraintObject( View view )
    {
        ObjectEngine oe = view.getObjectEngine();
        String className = getSourceFileName();
        if ( StringUtils.isBlank( className ) )
            className = getApplication().getPackage() + "." + getName() + "_Object";

        try
        {
            ClassLoader classLoader = oe.getClassLoader( className );
            Class<?> operationsClass;
            operationsClass = classLoader.loadClass( className );
            Constructor<?> constructor = operationsClass.getConstructor( constructorArgTypes );
            return constructor.newInstance( view );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e )
                                 .prependLodDef( LodDef.this )
                                 .appendMessage( "Class name = %s", className )
                                 .appendMessage( "See inner exception for more info." );
        }
    }

    static private final Class<?>[] ARGUMENT_TYPES1 = new Class<?>[] { View.class, Integer.class, Integer.class };
    static private final Class<?>[] ARGUMENT_TYPES2 = new Class<?>[] { View.class, ObjectConstraintType.class };

    private int executeConstraint( View view, ObjectConstraintType type )
    {
        switch ( sourceFileType )
        {
            case VML:
                return executeVmlConstraint( view, type );

            case SCALA:
                return executeScalaConstraint( view, type );

            case JAVA:
            default:
                throw new ZeidonException( "Unsupported Entity Constraint SourceFileType: %s", type );
        }
    }

    private int executeVmlConstraint( View view, ObjectConstraintType type )
    {
        Object object = getConstraintObject( view );

        try
        {
            Method method;
            try
            {
                method = object.getClass().getMethod( getConstraintOper(), ARGUMENT_TYPES1 );
                Integer rc = (Integer) method.invoke( object, view, type.getVmlValue(), 0 );
                return rc;
            }
            catch ( NoSuchMethodException e1 )
            {
                method = object.getClass().getMethod( getConstraintOper(), ARGUMENT_TYPES2 );
                Integer rc = (Integer) method.invoke( object, view, type );
                return rc;
            }
        }
        catch ( InvocationTargetException e )
        {
            Throwable target = e.getTargetException();
            if ( target instanceof ZeidonException )
                throw (ZeidonException) target;

            target = e.getCause();
            if ( target instanceof ZeidonException )
                throw (ZeidonException) target;

            throw ZeidonException.wrapException( e )
                                 .appendMessage( "Class = %s", object.getClass().getCanonicalName() )
                                 .appendMessage( "Method name = %s", getConstraintOper() );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e )
                                 .appendMessage( "Class = %s", object.getClass().getCanonicalName() )
                                 .appendMessage( "Method name = %s", getConstraintOper() );
        }
    }

    private int executeScalaConstraint( View view, ObjectConstraintType type )
    {
        try
        {
            return view.getTask().getScalaHelper().executeObjectConstraint( view, type );
        }
        catch ( Exception e )
        {
            if ( e instanceof InvocationTargetException )
                throw ZeidonException.wrapException( ((InvocationTargetException) e).getTargetException() );
            else
                throw ZeidonException.wrapException( e );
        }
    }

    /**
     * Executes the activate constraint on the view.
     *
     * @param view
     * @return
     */
    public int executeActivateConstraint( View view ) throws ObjectConstraintException
    {
        if ( ! hasActivateConstraint() )
            return 0;

        try
        {
            return executeConstraint( view, ObjectConstraintType.ACTIVATE );
        }
        catch ( Exception e )
        {
            if ( e instanceof InvocationTargetException )
                throw ZeidonException.wrapException( ((InvocationTargetException) e).getTargetException() );
            else
                throw ZeidonException.wrapException( e );
        }
    }

    /**
     * Executes the commit constraint on the view.
     *
     * @param view
     * @return
     */
    public int executeCommitConstraint( View view ) throws ObjectConstraintException
    {
        if ( ! hasCommitConstraint() )
            return 0;

        try
        {
            return executeConstraint( view, ObjectConstraintType.COMMIT );
        }
        catch ( Exception e )
        {
            throw ZeidonException.prependMessage( e, "Error calling Commit constraint for %s", this );
        }
    }

    public String getConstraintOper()
    {
        return constraintOper;
    }

    private class LodDefHandler extends NullEntityHandler
    {
        private final LodDef lodDef;
        private EntityDef currentEntityDef = null;
        private final ArrayList<EntityDef> parentStack = new ArrayList<EntityDef>();

        LodDefHandler(LodDef lodDef)
        {
            super();
            this.lodDef = lodDef;
            parentStack.add( null );
        }

        @Override
		public PortableFileAttributeHandler createEntity( PortableFileReader reader, int level, long flags )
        {
            switch ( reader.getAttributeName() )
            {
                case "ATTRIB":
                {
                    AttributeDef attrib = new AttributeDef(currentEntityDef);
                    return attrib;
                }

                case "CHILDENTITY":
                case "ENTITY":
                {
                    // Subtract one from level to take into account that the level 1 entity
                    // is the object name.
                    level--;

                    if ( currentEntityDef != null )
                        addEntityDef( currentEntityDef );

                    EntityDef entityDef = new EntityDef( lodDef, level );

                    if ( level >= parentStack.size() )
                        parentStack.add( entityDef );
                    else
                        parentStack.set( level, entityDef );
                    if ( level > 1 )
                        entityDef.setParent( parentStack.get( level - 1 ) );

                    if ( currentEntityDef != null )
                        currentEntityDef.setNextHier( entityDef );
                    entityDef.setPrevHier( currentEntityDef );
                    currentEntityDef = entityDef;
                    return entityDef;
                }

                case "DATAFIELD":
                {
                    DataField dataField = new DataField();
                    currentEntityDef.getDataRecord().addDataField( dataField );
                    return dataField;
                }

                case "RELFIELD":
                {
                    RelField relField = new RelField();
                    return relField;
                }

                case "RELRECORD":
                {
                    RelRecord relRecord = new RelRecord( currentEntityDef.getDataRecord() );
                    currentEntityDef.getDataRecord().setRelRecord( relRecord );
                    return relRecord;
                }

                case "DATARECORD":
                {
                    DataRecord dataRecord = new DataRecord( currentEntityDef );
                    currentEntityDef.setDataRecord( dataRecord );
                    return dataRecord;
                }

                case "OBJECT":
                {
                    return lodDef;
                }

                default:
                    throw new ZeidonException( "Unknown entity '%s'", reader.getAttributeValue());
            }
        }

        @Override
        public void endEntity(PortableFileReader reader, PortableFileAttributeHandler handler, int level)
        {
            if ( handler instanceof EntityDef )
            {
                EntityDef entityDef = (EntityDef) handler;

                // Count up the number of persistent and work attributes.
                int persistentCount = 0;
                int workCount = 0;
                for ( AttributeDef attributeDef : entityDef.getAttributes() )
                {
                    if ( attributeDef.isPersistent() )
                        persistentCount++;
                    else
                        workCount++;
                }

                entityDef.setPersistentAttributeCount( persistentCount );
                entityDef.setWorkAttributeCount( workCount );
            }
            else
            if ( handler instanceof AttributeDef )
            {
                AttributeDef attrib = (AttributeDef) handler;
                attrib.finishAttributeLoading();
                currentEntityDef.addAttributeDef( attrib );
            }
            else
            if ( handler instanceof RelField )
            {
                RelField relField = (RelField) handler;
                currentEntityDef.getDataRecord().getRelRecord().addRelField( relField );
            }
        }

        @Override
        public void endFile()
        {
            addEntityDef( currentEntityDef );  // Add the last LodDef.

            // Set the sibling pointers for all the view entities.
            entityList.get( 0 ).setSiblingsForChildren();
            entityList = Collections.unmodifiableList( entityList );
            Map<Integer, AttributeDef> attribMap = new HashMap<Integer, AttributeDef>();

            // Find the AttributeDef for each of the DataFields.
            for ( EntityDef entityDef : entityList )
            {
                for ( AttributeDef AttributeDef : entityDef.getAttributes() )
                    attribMap.put( AttributeDef.getXvaAttrToken(), AttributeDef );

                DataRecord dataRecord = entityDef.getDataRecord();
                if ( dataRecord != null )
                {
                    for ( DataField dataField : dataRecord.dataFields() )
                    {
                        AttributeDef AttributeDef = attribMap.get( dataField.getToken() );
                        assert AttributeDef != null : "Can't find matching XVA Token for DataField";
                        dataField.setAttributeDef( AttributeDef );
                    }
                    dataRecord.setFields( entityDef );
                }

                entityDef.validateEntityDef();
            }

            if ( hasDuplicateInstances() )
                computeDuplicateInstances();
        }
    } // class LodDefHandler

    /**
     * This LodDef has entities flagged as duplicate instances.  Find all the entityDefs
     * flagged as duplicate instances, then make sure they all activate the total sum
     * of attributes.
     */
    private void computeDuplicateInstances()
    {
        Map<String, List<EntityDef>> duplicateMap = new HashMap<>();

        for ( EntityDef entityDef : entityList )
        {
            if ( ! entityDef.isDuplicateEntity() )
                continue;

            List<EntityDef> list = duplicateMap.get( entityDef.getErEntityToken() );
            if ( list == null )
            {
                list = new ArrayList<>();
                duplicateMap.put( entityDef.getErEntityToken(), list );
            }

            list.add( entityDef );
        }

        assert duplicateMap.size() > 0;

        for ( List<EntityDef> list : duplicateMap.values() )
        {
            // Get the set of attributes that are to be activated by any entity.
            Set<String> activatedAttributes = new HashSet<>();
            for ( EntityDef entityDef : list )
            {
                for ( AttributeDef attributeDef : entityDef.getAttributes() )
                {
                    if ( attributeDef.isActivate() )
                        activatedAttributes.add( attributeDef.getErAttributeToken() );
                }
            }

            // Now go through and make sure all the attributes have the activate
            // flag set for all entities.
            for ( EntityDef entityDef : list )
            {
                for ( String erAttributetoken : activatedAttributes )
                    entityDef.getAttributeByErToken( erAttributetoken ).setActivate( true );
            }
        }
    }

    public int getHeight()
    {
        return height;
    }

    /**
     * @return the lockingLevel
     */
    public LockingLevel getLockingLevel()
    {
        return lockingLevel;
    }

    public String getGenkeyHandler()
    {
        return genkeyHandler;
    }

    public SourceFileType getSourceFileType()
    {
        return sourceFileType;
    }

    public String getSourceFileName()
    {
        return sourceFileName;
    }

    /**
     * True if the LOD has DataRecords.
     *
     * @return
     */
    public boolean hasPhysicalMappings()
    {
        return hasPhysicalMappings;
    }

    void setHasPhysicalMappings( boolean hasPhysicalMappings )
    {
        this.hasPhysicalMappings = hasPhysicalMappings;
    }

    public String getLibraryName()
    {
        return libraryName;
    }

    /**
     * Returns true if this LOD has entities that are lazy-loaded.
     *
     * @return
     */
    public boolean hasLazyLoadEntities()
    {
        return hasLazyLoadEntities;
    }

    void setHasLazyLoadEntities( boolean hasLazyLoadEntities )
    {
        this.hasLazyLoadEntities = hasLazyLoadEntities;
    }

    boolean hasDuplicateInstances()
    {
        return hasDuplicateInstances;
    }

    void setHasDuplicateInstances( boolean hasDuplicateInstances )
    {
        this.hasDuplicateInstances = hasDuplicateInstances;
    }

    public synchronized CacheMap getCacheMap()
    {
        if ( cacheMap == null )
            cacheMap = new CacheMapImpl();

        return cacheMap;
    }
}
