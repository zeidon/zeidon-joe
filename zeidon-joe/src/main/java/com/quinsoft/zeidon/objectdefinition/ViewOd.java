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

    Copyright 2009-2012 QuinSoft
 */
/**
 *
 */
package com.quinsoft.zeidon.objectdefinition;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.ObjectConstraintException;
import com.quinsoft.zeidon.ObjectConstraintType;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.UnknownViewEntityException;
import com.quinsoft.zeidon.UnknownViewOdException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.dbhandler.DbHandler;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.standardoe.ScalaHelper;
import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileEntityHandler.NullEntityHandler;

/**
 * @author DG
 *
 */
public class ViewOd implements PortableFileAttributeHandler
{
    private final Application  app;
    private String       name;
    private final String filename;
    private final Map<String, ViewEntity> nameMap = new HashMap<String, ViewEntity>();
    private List<ViewEntity> entityList = new ArrayList<ViewEntity>();
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

    static private final Class<?>[] constructorArgTypes  = new Class<?>[] { View.class };
    static private final Class<?>[] constructorArgTypes2 = new Class<?>[] { Task.class };

    public ViewOd(Task task, Application app, String name) throws UnknownViewOdException
    {
        super();
        this.app = app;
        this.name = name;

        // Make sure the XOD exists.
        String xod = name + ".XOD";

        // We know that system apps XODs are stored as all lower-case.
        if ( app.isSystemApp() )
            xod = xod.toLowerCase();

        filename = app.getObjectDir() + File.separator + xod;
        InputStream inputStream = JoeUtils.getInputStream( task, filename, getClass().getClassLoader() );
        if ( inputStream == null )
            throw new UnknownViewOdException(name, filename, app );

        loadViewOD( task, inputStream );
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

    public ViewEntity getViewEntity( String entityName, boolean required )
    {
        // We allow the dbhandler to use a special string to indicate the
        // root.
        if ( StringUtils.equals( entityName, DbHandler.ROOT_ENTITY ) )
            return getViewEntity( 0 );  // Return the root view entity.

        ViewEntity viewEntity = nameMap.get( entityName );
        if ( viewEntity == null && required )
            throw new UnknownViewEntityException( this, entityName );

        return viewEntity;
    }

    public ViewEntity getViewEntity( String entityName )
    {
        return getViewEntity( entityName, true );
    }

    public ViewEntity getViewEntity( int index )
    {
        ViewEntity viewEntity = entityList.get( index );
        return viewEntity;
    }

    private void loadViewOD( Task task, InputStream file )
    {
        try
        {
            PortableFileReader.ReadPortableFile( file, task.log(), new ViewOdHandler( this ) );
            task.log().info( "View OD %s loaded from: %s", this, filename );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( filename );
        }
    }

    private void addViewEntity( ViewEntity viewEntity )
    {
        nameMap.put( viewEntity.getName(), viewEntity );
        height = Math.max( height, viewEntity.getLevel() );
        entityList.add( viewEntity );
    }

    @Override
    public void setAttribute(PortableFileReader reader)
    {
        if ( reader.getAttributeName().equals( "NAME" ))
        {
            if ( ! reader.getAttributeValue().equalsIgnoreCase( name ) )
                throw new ZeidonException("Name of object from XOD ('%s') doesn't match OD name", reader.getAttributeValue());

            // Override the name used when this object was being created with the one from the .xod file.
            // We need to do this because names are case-insensitive and the user may have used a different
            // case than the one from the file.
            name = reader.getAttributeValue().intern();
        }
        else
        if ( reader.getAttributeName().equals( "ER_DATE" ))
        {
            erDate = reader.getAttributeValue().intern();
        }
        else
        if ( reader.getAttributeName().equals( "DATABASE" ) ||
             reader.getAttributeName().equals( "DFT_DBNAME" ) )
        {
            database = reader.getAttributeValue().intern();
        }
        else
        if ( reader.getAttributeName().equals( "DBH_Data" ))
        {
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
        }
        if ( reader.getAttributeName().equals( "GKHANDLER" ))
        {
            genkeyHandler = reader.getAttributeValue().intern();
        }
        else
        if ( reader.getAttributeName().equals( "LOCK" ))
        {
            Integer level = Integer.getInteger( reader.getAttributeValue() );
            lockingLevel = LockingLevel.lookup( level );
        }
        else
        if ( reader.getAttributeName().equals( "OCEOPER" ))
        {
            constraintOper = reader.getAttributeValue().intern();
        }
        else
        if ( reader.getAttributeName().equals( "OCACT" ))
        {
            activateConstraint = reader.getAttributeValue().startsWith( "Y" );
        }
        else
        if ( reader.getAttributeName().equals( "OCCOM" ))
        {
            commitConstraint = reader.getAttributeValue().startsWith( "Y" );
        }
        else
        if ( reader.getAttributeName().equals( "OCSRCTYPE" ))
        {
            sourceFileType = SourceFileType.parse( reader.getAttributeValue() );
        }
    }

    public List<ViewEntity> getViewEntitiesHier()
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

    public ViewEntity getRoot()
    {
        return entityList.get( 0 );
    }

    /**
     * Traces the ViewOd to the log
     * @param task
     */
    public void displayViewOD( TaskQualification task )
    {
        task.log().info( "Displaying View OD for %s", getName() );
        for ( ViewEntity viewEntity : getViewEntitiesHier() )
        {
            task.log().info( "%s %d, count = %d", viewEntity.getName(),
                              viewEntity.getLevel(), viewEntity.getChildCount() );
            for ( ViewAttribute attrib : viewEntity.getAttributes() )
            {
                Domain domain = attrib.getDomain();
                String type = domain == null ? attrib.getType().toString() : domain.getName();
                task.log().info( "    Attrib: %s %d Domain:%s",
                                 attrib.getName(), attrib.getAttributeNumber(), type );
            }

            DataRecord dataRecord = viewEntity.getDataRecord();
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
        String className = getApplication().getPackage() + "." + getName() + "_Object";
        try
        {
            ClassLoader classLoader = oe.getClassLoader( className );
            Class<?> operationsClass;
            operationsClass = classLoader.loadClass( className );
            try
            {
                // First try with View as the constructor args.
                Constructor<?> constructor = operationsClass.getConstructor( constructorArgTypes );
                return constructor.newInstance( view );
            }
            catch ( NoSuchMethodException e )
            {
                try
                {
                    // Maybe with a task constructor?
                    Constructor<?> constructor = operationsClass.getConstructor( constructorArgTypes2 );
                    return constructor.newInstance( view.getTask() );
                }
                catch ( NoSuchMethodException e2 )
                {
                    // Now try with an empty constructor.
                    Constructor<?> constructor = operationsClass.getConstructor();
                    return constructor.newInstance();
                }
            }
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e )
                                 .prependViewOd( ViewOd.this )
                                 .appendMessage( "Class name = %s", className )
                                 .appendMessage( "See inner exception for more info." );
        }

    }

    static private final Class<?>[] ARGUMENT_TYPES1 = new Class<?>[] { View.class, Integer.class, Integer.class };
    static private final Class<?>[] ARGUMENT_TYPES2 = new Class<?>[] { View.class, ObjectConstraintType.class };

    private int executeConstraint( View view, ObjectConstraintType type )
    {
        if ( getSourceFileType() == SourceFileType.SCALA )
            return executeScalaConstraint( view, type );

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
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e )
                                 .appendMessage( "Class = %s", object.getClass().getCanonicalName() )
                                 .appendMessage( "Method name = %s", getConstraintOper() );
        }
    }

    @SuppressWarnings("unchecked")
    private int executeScalaConstraint( View view, ObjectConstraintType type )
    {
        String className = "com.quinsoft.zeidon.scala.ScalaHelperImpl";
        ObjectEngine oe = view.getObjectEngine();
        ClassLoader classLoader = oe.getClassLoader( className );
        Class<ScalaHelper> operationsClass;
        try
        {
            operationsClass = (Class<ScalaHelper>) classLoader.loadClass( className );
        }
        catch ( ClassNotFoundException e )
        {
            throw new ZeidonException("Couldn't load %s.  Do you have zeidon-scala in your classpath?", className );
        }

        try
        {
            ScalaHelper instance = operationsClass.newInstance();
            return instance.executeObjectConstraint( view, type, classLoader );
        }
        catch ( Exception e )
        {
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
            throw ZeidonException.prependMessage( e, "Error calling Activate constraint for %s", this );
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

    private class ViewOdHandler extends NullEntityHandler
    {
        private final ViewOd viewOd;
        private ViewEntity currentViewEntity = null;
        private final ArrayList<ViewEntity> parentStack = new ArrayList<ViewEntity>();

        ViewOdHandler(ViewOd viewOd)
        {
            super();
            this.viewOd = viewOd;
            parentStack.add( null );
        }

        @Override
		public PortableFileAttributeHandler createEntity( PortableFileReader reader, int level, long flags )
        {
            if ( reader.getAttributeName().equals( "ATTRIB" ))
            {
                ViewAttribute attrib = new ViewAttribute(currentViewEntity);
                return attrib;
            }
            else
            if ( reader.getAttributeName().equals( "CHILDENTITY" ) ||
                 reader.getAttributeName().equals( "ENTITY" ))
            {
                // Subtract one from level to take into account that the level 1 entity
                // is the object name.
                level--;

                if ( currentViewEntity != null )
                    addViewEntity( currentViewEntity );

                ViewEntity viewEntity = new ViewEntity( viewOd, level );

                if ( level >= parentStack.size() )
                    parentStack.add( viewEntity );
                else
                    parentStack.set( level, viewEntity );
                if ( level > 1 )
                    viewEntity.setParent( parentStack.get( level - 1 ) );

                if ( currentViewEntity != null )
                    currentViewEntity.setNextHier( viewEntity );
                viewEntity.setPrevHier( currentViewEntity );
                currentViewEntity = viewEntity;
                return viewEntity;
            }
            else
            if ( reader.getAttributeName().equals( "DATAFIELD" ))
            {
                DataField dataField = new DataField();
                currentViewEntity.getDataRecord().addDataField( dataField );
                return dataField;
            }
            else
            if ( reader.getAttributeName().equals( "RELFIELD" ))
            {
                RelField relField = new RelField();
                currentViewEntity.getDataRecord().getRelRecord().addRelField( relField );
                return relField;
            }
            else
            if ( reader.getAttributeName().equals( "RELRECORD" ))
            {
                RelRecord relRecord = new RelRecord( currentViewEntity.getDataRecord() );
                currentViewEntity.getDataRecord().setRelRecord( relRecord );
                return relRecord;
            }
            else
            if ( reader.getAttributeName().equals( "DATARECORD" ))
            {
                DataRecord dataRecord = new DataRecord( currentViewEntity );
                currentViewEntity.setDataRecord( dataRecord );
                return dataRecord;
            }
            else
            if ( reader.getAttributeName().equals( "OBJECT" ))
            {
                return viewOd;
            }
            else
                throw new ZeidonException( "Unknown entity '%s'", reader.getAttributeValue());
        }

        @Override
        public void endEntity(PortableFileReader reader, PortableFileAttributeHandler handler, int level)
        {
            if ( handler instanceof ViewEntity )
            {
                ViewEntity viewEntity = (ViewEntity) handler;

                // Count up the number of persistent and work attributes.
                int persistentCount = 0;
                int workCount = 0;
                for ( ViewAttribute viewAttribute : viewEntity.getAttributes() )
                {
                    if ( viewAttribute.isPersistent() )
                        persistentCount++;
                    else
                        workCount++;
                }

                viewEntity.setPersistentAttributeCount( persistentCount );
                viewEntity.setWorkAttributeCount( workCount );
            }
            else
            if ( handler instanceof ViewAttribute )
            {
                ViewAttribute attrib = (ViewAttribute) handler;
                currentViewEntity.addViewAttribute( attrib );
            }
        }

        @Override
        public void endFile()
        {
            addViewEntity( currentViewEntity );  // Add the last view entity.

            // Set the sibling pointers for all the view entities.
            entityList.get( 0 ).setSiblingsForChildren();
            entityList = Collections.unmodifiableList( entityList );
            Map<Integer, ViewAttribute> attribMap = new HashMap<Integer, ViewAttribute>();

            // Find the ViewAttrib for each of the DataFields.
            for ( ViewEntity ve : entityList )
            {
                for ( ViewAttribute viewAttrib : ve.getAttributes() )
                {
                    attribMap.put( viewAttrib.getToken(), viewAttrib );
                }

                DataRecord dataRecord = ve.getDataRecord();
                if ( dataRecord != null )
                {
                    for ( DataField dataField : dataRecord.dataFields() )
                    {
                        ViewAttribute viewAttrib = attribMap.get( dataField.getToken() );
                        assert viewAttrib != null : "Can't find matching XVA Token for DataField";
                        dataField.setViewAttribute( viewAttrib );
                    }
                    dataRecord.setFields( ve );
                }
            }
        }
    } // class ViewOdHandler

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
}
