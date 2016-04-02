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
/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.StreamReader;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.InternalType;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.utils.BufferedBinaryStreamReader;
import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileEntityHandler;

/**
 * Activates an OI from a POR stream.
 *
 * @author DG
 *
 */
class ActivateOiFromPorStream implements PortableFileEntityHandler, StreamReader
{
    private static final EnumSet<CreateEntityFlags> CREATE_FLAGS = EnumSet.of( CreateEntityFlags.fNO_SPAWNING,
                                                                               CreateEntityFlags.fIGNORE_MAX_CARDINALITY,
                                                                               CreateEntityFlags.fDONT_UPDATE_OI,
                                                                               CreateEntityFlags.fDONT_INITIALIZE_ATTRIBUTES,
                                                                               CreateEntityFlags.fIGNORE_PERMISSIONS );

    private Task    task;
    private Application application;
    private LodDef            lodDef;
    private ViewImpl          view;
    private Set<ActivateFlags>       control;
    private List<EntityInstanceImpl> entities;
    private EntityInstanceImpl       lastInstance;
    private BufferedBinaryStreamReader streamReader;
    private InputStream        inputStream;
    private boolean            ignoreInvalidEntityNames;
    private boolean            ignoreInvalidAttributeNames;

    ActivateOiFromPorStream()
    {
        super();
    }

    ViewImpl read()
    {
        PortableFileReader reader = new PortableFileReader( task.log(), inputStream, this );
        if ( streamReader != null )
            reader.setStreamReader( streamReader );

        reader.readEntities();

        // If user wanted just one root remove others if we have more than one.
        // We don't want to abort the loading of entities in the middle of the stream
        // because that could throw off the link cards.
        EntityCursorImpl rootCursor = view.getViewCursor().getEntityCursor( lodDef.getRoot() );
        if ( control.contains( ActivateFlags.fSINGLE ) && rootCursor.getEntityCount() > 1  )
        {
            rootCursor.setFirst();
            while ( rootCursor.setNext().isSet() )
                rootCursor.dropEntity();
            rootCursor.setFirst();
        }

        view.reset();
        return view;
    }

    void setLodDef( LodDef lodDef )
    {
        this.lodDef = lodDef;
    }

    @Override
    public PortableFileAttributeHandler createEntity(PortableFileReader reader,
                                                     int level,
                                                     long flags)
    {
        String entityName = reader.getAttributeName();
        EntityDef entityDef = lodDef.getEntityDef( entityName, ! ignoreInvalidEntityNames );

        // If the entityDef is null then the entity name doesn't exist in the LodDef and
        // the user wants us to ignore unknown entities.
        if ( entityDef == null )
        {
            entities.add( null );  // Needed so that link indexes in stream still point to valid entities.
            return new PortableFileAttributeHandler.NullAttributeHandler();
        }

        int entityDefLevel = entityDef.getDepth() + view.getViewCursor().getRecursiveDiff();
        if ( entityDefLevel < level )
        {
            // If the entityDef level is < level than the last entity we created must
            // be the parent of the one we're about to create and we need to set the
            // cursor to the subobject.
            view.cursor( lastInstance.getEntityDef() ).setToSubobject();
        }
        else
        {
            while ( entityDefLevel > level )
            {
                view.resetSubobject();
                entityDefLevel = entityDef.getDepth() + view.getViewCursor().getRecursiveDiff();
            }
        }

        // Create the new instance and add it to the list of entities.
        EntityCursorImpl cursor = view.cursor( entityDef );
        lastInstance = cursor.createEntity( CursorPosition.LAST, CREATE_FLAGS );
        entities.add( lastInstance );

        return new AttributeSetter( lastInstance );
    }

    private class AttributeSetter implements PortableFileAttributeHandler
    {
        // Flags used when reading from a stream.
        final static long FLAGS_ACTIVATED = 0x00000001;
        final static long FLAGS_UPDATED   = 0x00000002;

        private final EntityInstanceImpl entityInstance;
        private final EntityDef         entityDef;

        public AttributeSetter(EntityInstanceImpl entityInstance)
        {
            this.entityInstance = entityInstance;
            entityDef = entityInstance.getEntityDef();
        }

        @Override
        public void setAttribute(PortableFileReader reader)
        {
            AttributeDef attributeDef = entityDef.getAttribute( reader.getAttributeName(), ! ignoreInvalidAttributeNames );
            if ( attributeDef != null )
            {
                if ( attributeDef.getType() == InternalType.BLOB )
                {
                    int length = Integer.parseInt( reader.getAttributeValue() );
                    byte[] buffer = new byte[ length ];
                    int read;
                    try
                    {
                        read = reader.getStreamReader().read( buffer, length );
                    }
                    catch ( IOException e )
                    {
                        throw ZeidonException.wrapException( e );
                    }
                    assert read == length;
                    entityInstance.getAttribute( attributeDef).setInternalValue( new Blob( buffer ), true ) ;
                }
                else
                    entityInstance.getAttribute( attributeDef).setInternalValue( reader.getAttributeValue(), true ) ;

                if ( reader.isIncremental() )
                {
                    AttributeValue attrib = entityInstance.getInternalAttribute( attributeDef );
                    attrib.setUpdated( ( reader.getAttributeFlags() & FLAGS_UPDATED ) != 0 );
                }
            }
        }
    }

    @Override
    public void endEntity(PortableFileReader reader,
                          PortableFileAttributeHandler handler,
                          int currentLevel)
    {
    }

    @Override
    public void endFile()
    {

    }

    @Override
    public void link(PortableFileReader reader, int target, int source)
    {
        EntityInstanceImpl targetInstance = entities.get( target );
        EntityInstanceImpl sourceInstance = entities.get( source );
        reader.getLogger().trace( "Linking %s (%d) with %s (%d)",
                                  targetInstance.getEntityDef().getName(), target,
                                  sourceInstance.getEntityDef().getName(), source );
        targetInstance.linkInternalInstances( sourceInstance );
    }

    @Override
    public void endEntityAttributes(PortableFileReader reader, String entityName, int currentLevel)
    {
        if ( reader.isIncremental() )
        {
            assert lastInstance.getEntityDef().getName().equals( entityName );
            lastInstance.setIncrementalFlags( reader.getEntityFlags() );
        }
    }

    @Override
    public void startFile(PortableFileReader reader, String lodDefName)
    {
        // LodDef may have already been given to us.
        if ( lodDef == null )
            lodDef = application.getLodDef( task, lodDefName );

        if ( view == null )
            view = ((InternalView) task.activateEmptyObjectInstance( lodDef )).getViewImpl();
        else
        if ( view.getLodDef() != lodDef )
            throw new ZeidonException( "LodDef of empty view (%s) does not match LodDef from OI stream (%s)",
                                       view.getLodDef().getName(), lodDef.getName() );
    }

    /**
     * This sets the initial empty view.  If not set then it will be created automatically.
     * Setting this allows a caller to create and access the view before processing starts.
     *
     * @param view
     */
    void setEmptyView( ViewImpl view )
    {
        this.view = view;
    }

    LodDef getLodDef()
    {
        return lodDef;
    }

    /**
     * Explicitly set the stream reader.  This allows a client to read multiple OIs
     * from a single stream because this won't be automatically closed.
     *
     * @param reader
     */
    public void setStreamReader( BufferedBinaryStreamReader reader )
    {
        this.streamReader = reader;
    }

    @Override
    public List<View> readFromStream( DeserializeOi options )
    {
        task = options.getTask();
        application = options.getApplication();
        control = options.getFlags();
        entities = new ArrayList<EntityInstanceImpl>();
        inputStream = options.getInputStream();
        lodDef = options.getLodDef();
        ignoreInvalidEntityNames = control.contains( ActivateFlags.fIGNORE_ENTITY_ERRORS );
        ignoreInvalidAttributeNames = control.contains( ActivateFlags.fIGNORE_ATTRIB_ERRORS );

        read();
        return Arrays.asList( (View) view );
    }
}