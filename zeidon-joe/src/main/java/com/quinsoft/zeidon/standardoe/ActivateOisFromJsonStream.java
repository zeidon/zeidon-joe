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
package com.quinsoft.zeidon.standardoe;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.StreamReader;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.DynamicAttributeDefConfiguration;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * Reads all the OIs from a given stream in JSON format.
 *
 * @author dgc
 *
 */
class ActivateOisFromJsonStream implements StreamReader
{
    private static final EnumSet<CreateEntityFlags> CREATE_FLAGS = EnumSet.of( CreateEntityFlags.fNO_SPAWNING,
                                                                               CreateEntityFlags.fIGNORE_MAX_CARDINALITY,
                                                                               CreateEntityFlags.fDONT_UPDATE_OI,
                                                                               CreateEntityFlags.fDONT_INITIALIZE_ATTRIBUTES,
                                                                               CreateEntityFlags.fIGNORE_PERMISSIONS );
    private static final EntityMeta DEFAULT_ENTITY_META = new EntityMeta();

    private Task                    task;
    private InputStream             stream;

    /**
     * Keep track of the options for this activate.
     */
    private DeserializeOi  options;

    /**
     * This keeps track of all the entities that are the sources for linked instances.
     * The key is the EntityKey.
     */
    private final Map<Object, EntityInstanceImpl> linkSources;

    private JsonParser                    jp;
    private Application                   application;
    private boolean                       incremental;
    private LodDef                        lodDef;
    private View                          view;
    private final List<View>              returnList;
    private String version;

    /**
     * Used to keep track of the instances that are flagged as selected in the input
     * stream.  Cursors will be set afterwards.
     */
    private List<EntityInstance> selectedInstances;

    /**
     * If true then mark the OI that is being read as readonly.
     */
    private boolean readOnlyOi;

    /**
     * If trure then mark the view as readonly.
     */
    private boolean readOnly;

    /**
     * A JSON stream will have a version.  Once the version is read from the stream a
     * subclass of JsonReader will be used to read a particular version.
     * @author dgc
     *
     */
    private interface JsonReader
    {
        void process() throws Exception;
    }

    ActivateOisFromJsonStream( )
    {
        returnList = new ArrayList<View>();
        linkSources = new HashMap<Object, EntityInstanceImpl>();
    }

    public List<View> read()
    {
        try
        {
            JsonFactory jsonFactory = new JsonFactory();
            jp = jsonFactory.createParser( stream );
            jp.configure( JsonParser.Feature.AUTO_CLOSE_SOURCE, false );

            // Read the START_OBJECT
            JsonToken token = jp.nextToken();
            if ( token != JsonToken.START_OBJECT )
                throw new ZeidonException( "OI JSON stream doesn't start with object." );

            token = jp.nextToken();
            if ( token != JsonToken.FIELD_NAME )
                throw new ZeidonException( "OI JSON missing OI field name." );

            String fieldName = jp.getCurrentName();
            if ( fieldName.equals( ".meta"  ) )
            {
                readFileMeta();

                JsonReader reader = getReaderForVersion();
                reader.process();
            }
            else
            {
                if ( StringUtils.equalsIgnoreCase( fieldName, "version" ) )
                {
                    token = jp.nextToken(); // Move to value.
                    version = jp.getValueAsString();
                    token = jp.nextToken(); // Move to next field name.
                    assert token == JsonToken.FIELD_NAME;
                    fieldName = jp.getCurrentName();
                }
                else
                if ( StringUtils.isBlank( options.getVersion() ) )
                {
                    throw new ZeidonException( "First field must be version" );
                }

                if ( lodDef == null )
                    throw new ZeidonException( "JSON stream appears to start with the root entity name (%s)" +
                                               " but the LodDef has not been specified.", fieldName );

                String rootName = lodDef.getRoot().getName();
                if ( ! fieldName.equals( rootName ) )
                    throw new ZeidonException( "The first field in the JSON stream must be the root entity name" +
                                               " (%s) or '.meta' but was %s.", rootName, fieldName );

                view = task.activateEmptyObjectInstance( lodDef );
                returnList.add( view );

                JsonReader reader = getSimpleReaderForVersion();
                reader.process();
            }

            jp.close();
        }
        catch ( Exception e )
        {
            ZeidonException ze = ZeidonException.wrapException( e );
            JsonLocation loc = jp.getCurrentLocation();
            JsonToken token = jp.getCurrentToken();
            ze.appendMessage( "Position line=%d col=%d, token=%s", loc.getLineNr(), loc.getColumnNr(),
                              token == null ? "No Token" : token.name() );
            throw ze;
        }

        return returnList;
    }

    private JsonReader getReaderForVersion()
    {
        String v = getVersion();
        switch ( v )
        {
            case "1":
            case "1.0":
                return new JsonReaderVersion1();

            default:
                throw new ZeidonException("Unknown version %s", v );
        }
    }

    private JsonReader getSimpleReaderForVersion()
    {
        String v = getVersion();
        switch ( v )
        {
            case "1":
                return new SimpleJsonReaderVersion1();

            default:
                throw new ZeidonException("Unknown version %s", v );
        }
    }

    private void readFileMeta() throws Exception
    {
        jp.nextToken();
        while ( jp.nextToken() != JsonToken.END_OBJECT )
        {
            String fieldName = jp.getCurrentName();
            jp.nextToken(); // Move to value.
            switch ( fieldName )
            {
                case "version":
                    version = jp.getValueAsString();
                    task.log().debug( "JSON version: %s", version );
                    break;

                case "date":
                    break;

                default:
                    task.log().warn( "Unknown .oimeta fieldname %s", fieldName );
            }
        }

        jp.nextToken();

    }

    private String getVersion()
    {
        if ( ! StringUtils.isBlank( options.getVersion() ) )
            return options.getVersion();

        if ( StringUtils.isBlank( version ) )
            throw new ZeidonException( "Version is not specified in stream or Deserialization options" );

        return version;
    }

    private boolean readOi() throws Exception
    {
        JsonToken token = jp.nextToken();

        // If we find the end of the OI array then that's the end of OIs.
        if ( token == JsonToken.END_ARRAY )
            return false;  // No more OIs in the stream.

        if ( token != JsonToken.START_OBJECT )
            throw new ZeidonException( "OI JSON stream doesn't start with object." );

        token = jp.nextToken();

        String fieldName = jp.getCurrentName();
        if ( StringUtils.equals( fieldName, ".oimeta" ) )
            token = readOiMeta();
        else
            throw new ZeidonException( ".oimeta object not specified in JSON stream" );

        // If the token after reading the .oimeta is END_OBJECT then the OI is empty.
        if ( token != JsonToken.END_OBJECT )
        {
            fieldName = jp.getCurrentName();
            if ( !StringUtils.equals( fieldName, lodDef.getRoot().getName() ) )
                throw new ZeidonException( "First entity specified in OI (%s) is not the root (%s)", fieldName,
                                           lodDef.getRoot().getName() );

            readEntity( fieldName );
            token = jp.nextToken();
        }

        if ( selectedInstances.size() > 0 )
            setCursors();
        else
            view.reset();

        if ( token != JsonToken.END_OBJECT )
            throw new ZeidonException( "OI JSON stream doesn't end with object." );

        if ( readOnlyOi )
            ((InternalView) view).getViewImpl().getObjectInstance().setReadOnly( true );

        if ( readOnly )
            view.setReadOnly( true );

        return true;  // Keep looking for OIs in the stream.
    }

    /**
     * The view has been loaded from the stream and it was indicated that there are
     * cursor selections.  Reset them.
     */
    private void setCursors()
    {
        for ( EntityInstance ei : selectedInstances )
        {
            EntityDef entityDef = ei.getEntityDef();
            view.cursor( entityDef ).setCursor( ei );
        }
    }

    private boolean readSimpleOi() throws Exception
    {
        JsonToken token = jp.getCurrentToken();

        // If we find the end of the OI array then that's the end of OIs.
        if ( token == JsonToken.END_ARRAY || token == JsonToken.END_OBJECT )
            return false;  // No more OIs in the stream.

        String fieldName = jp.getCurrentName();

        assert token == JsonToken.FIELD_NAME;
        assert lodDef.getRoot().getName().equals( fieldName );

        // If the token after reading the .oimeta is END_OBJECT then the OI is empty.
        if ( token != JsonToken.END_OBJECT )
        {
            // readEntity expects the current token to be the opening { or [.
            // Skip over the field name.
            token = jp.nextToken();
            readEntity( fieldName );
            token = jp.nextToken();
        }

        if ( token != JsonToken.END_OBJECT )
            throw new ZeidonException( "OI JSON stream doesn't end with object." );

        return true;  // Keep looking for OIs in the stream.
    }

    private void readEntity( String entityName ) throws Exception
    {
        // Keeps track of whether the entity list starts with a [ or not.  If there
        // is no [ then we are done reading entities of this type when we find the
        // end of the object.
        boolean entityArray = false;
        int twinCount = 0;

        JsonToken token = jp.getCurrentToken();
        if ( token == JsonToken.START_ARRAY )
        {
            token = jp.nextToken();
            entityArray = true;  // Entity list started with [
        }

        assert token == JsonToken.START_OBJECT;

        EntityDef entityDef = lodDef.getEntityDef( entityName );

        // Read tokens until we find the token that ends the current list of entities.
        while ( ( token = jp.nextToken() ) != null )
        {
            twinCount++;

            if ( token == JsonToken.END_ARRAY )
                break;

            if ( token == JsonToken.END_OBJECT )
            {
                // If we get here then this should indicate an empty OI.  Get the next
                // token, verify that it's an END_ARRAY, and return.
                token = jp.nextToken();
                assert token == JsonToken.END_ARRAY;
                break;
            }

            // If there are multiple twins then the token is START_OBJECT to
            // indicate a new EI.
            if ( token == JsonToken.START_OBJECT )
            {
                assert twinCount > 1; // Assert that we already created at least one EI.
                token = jp.nextToken();
            }

            assert token == JsonToken.FIELD_NAME;
            EntityInstanceImpl ei = (EntityInstanceImpl) view.cursor( entityDef ).createEntity( CursorPosition.LAST, CREATE_FLAGS );

            List<AttributeMeta> attributeMetas = new ArrayList<>();

            // Read tokens until we find the token that ends the current entity.
            EntityMeta entityMeta = DEFAULT_ENTITY_META;
            while ( ( token = jp.nextToken() ) != JsonToken.END_OBJECT )
            {
                String fieldName = jp.getCurrentName();
                if ( token != JsonToken.VALUE_STRING )
                    token = jp.nextToken();

                if ( StringUtils.equals( fieldName, ".meta" ) )
                {
                    entityMeta = readEntityMeta( ei );

                    // Now that we have everything we can perform some processing.
                    if ( entityMeta.isLinkedSource )
                        linkSources.put( entityMeta.entityKey, ei );
                    else
                    if ( entityMeta.linkedSource != null )
                        ei.linkInstances( linkSources.get( entityMeta.linkedSource ) );

                    continue;
                }

                if ( fieldName.startsWith( "." ) )
                {
                    AttributeMeta am = readAttributeMeta( ei, fieldName );
                    attributeMetas.add( am );
                    continue;
                }

                // Is this the start of an entity.
                if ( token == JsonToken.START_ARRAY || token == JsonToken.START_OBJECT )
                {
                    boolean recursiveChild = false;

                    // Validate that the entity name is valid.
                    EntityDef childEntity = lodDef.getEntityDef( fieldName );
                    if ( childEntity.getParent() != entityDef )
                    {
                        // Check to see the childEntity is a recursive child.
                        if ( entityDef.isRecursive() )
                        {
                            view.cursor( entityDef ).setToSubobject();
                            recursiveChild = true;
                        }
                        else
                            throw new ZeidonException( "Parse error: %s is not a child of %s", fieldName,
                                                       entityName );
                    }

                    readEntity( fieldName );

                    if ( recursiveChild )
                        view.resetSubobject();

                    continue;
                }

                if ( StringUtils.equals( jp.getText(), fieldName ) )
                    // If jp points to attr name, get next token.
                    token = jp.nextToken();

                // This better be an attribute
                // Try getting the attribute.  We won't throw an exception (yet) if there
                // is no attribute with a matching name.
                AttributeDef attributeDef = entityDef.getAttribute( fieldName, false );
                if ( attributeDef == null )
                {
                    // We didn't find an attribute with a name matching fieldName.  Do we allow
                    // dynamic attributes for this entity?
                    if ( options.getAllowableDynamicEntities() == null ||
                       ! options.getAllowableDynamicEntities().contains( entityDef.getName() ) )
                    {
                        entityDef.getAttribute( fieldName ); // This will throw the exception.
                    }

                    // We are allowing dynamic attributes.  Create one.
                    DynamicAttributeDefConfiguration config = new DynamicAttributeDefConfiguration();
                    config.setAttributeName( fieldName );
                    attributeDef = entityDef.createDynamicAttributeDef( config );
                }

                ei.setInternalAttributeValue( attributeDef, jp.getText(), false );
                if ( incremental )
                {
                    // Since incremental flags are set, assume the attribute hasn't been
                    // updated.  We'll be told later if it has.
                    AttributeValue attrib = ei.getInternalAttribute( attributeDef );
                    attrib.setUpdated( false );
                }
            } // while ( ( token = jp.nextToken() ) != JsonToken.END_OBJECT )...

            // Apply all the attribute metas to correctly set the attribute flags.
            for ( AttributeMeta am : attributeMetas )
                am.apply( ei );

            // Now that we've updated everything, set the flags.
            ei.setCreated( entityMeta.created );
            ei.setUpdated( entityMeta.updated );
            ei.setDeleted( entityMeta.deleted );
            ei.setIncluded( entityMeta.included );
            ei.setExcluded( entityMeta.excluded );
            if ( entityMeta.incomplete )
                ei.setIncomplete( null );
            if ( entityMeta.lazyLoaded != null )
            {
                String[] names = entityMeta.lazyLoaded.split( "," );
                for ( String name: names )
                    ei.getEntitiesLoadedLazily().add( lodDef.getEntityDef( name ) );
            }

            // If the entity list didn't start with a [ then there is only one entity
            // in the list of twins so exit.
            if ( entityArray == false )
                break;

        } // while ( ( token = jp.nextToken() ) != null )...
    }

    private AttributeMeta readAttributeMeta( EntityInstanceImpl ei, String fieldName ) throws JsonParseException, IOException
    {
        String attribName = fieldName.substring( 1 ); // Remove the ".".
        AttributeMeta meta = new AttributeMeta();
        meta.attributeDef = ei.getEntityDef().getAttribute( attribName );

        while ( jp.nextToken() != JsonToken.END_OBJECT )
        {
            fieldName = jp.getCurrentName();

            if ( fieldName.equals( "updated" ) )
                meta.updated = true;
            else
                task.log().warn( "Unknown entity meta value %s", fieldName );
        }

        return meta;
    }

    private class AttributeMeta
    {
        private AttributeDef attributeDef;
        private boolean updated = false;

        private void apply( EntityInstanceImpl ei )
        {
            if ( updated )
            {
                AttributeValue attrib = ei.getInternalAttribute( attributeDef );
                attrib.setUpdated( true );
            }
        }
    }

    private EntityMeta readEntityMeta(EntityInstanceImpl ei) throws Exception
    {
        EntityMeta meta = new EntityMeta();
        while ( jp.nextToken() != JsonToken.END_OBJECT )
        {
            String fieldName = jp.getCurrentName();

            switch ( fieldName )
            {
                case  "incrementals" :   readIncrementals( meta ); break;
                case  "isLinkedSource" : meta.isLinkedSource = true; break;
                case  "entityKey" :      meta.entityKey = jp.getText(); break;
                case  "linkedSource" :   meta.linkedSource = jp.getText(); break;
                case  "selected" :       selectedInstances.add( ei ); break;
                case  "incomplete" :     meta.incomplete = true; break;
                case  "lazyLoaded" :     meta.lazyLoaded = jp.getText(); break;

                default: task.log().warn( "Unknown entity meta value %s", fieldName );
            }
        }

        return meta;
    }

    private void readIncrementals( EntityMeta meta ) throws JsonParseException, IOException
    {
        String increStr = jp.getText().toLowerCase();

        meta.updated  = increStr.contains( "u" );
        meta.created  = increStr.contains( "c" );
        meta.deleted  = increStr.contains( "d" );
        meta.included = increStr.contains( "i" );
        meta.excluded = increStr.contains( "x" );
    }

    private JsonToken readOiMeta() throws Exception
    {
        String odName = null;
        readOnlyOi = false;
        readOnly   = false;

        jp.nextToken();
        while ( jp.nextToken() != JsonToken.END_OBJECT )
        {
            String fieldName = jp.getCurrentName();
            jp.nextToken(); // Move to value.
            switch ( fieldName )
            {
                case "application": application = task.getApplication( jp.getValueAsString() ); break;
                case "odName":      odName = jp.getValueAsString(); break; // Save OD name for later.
                case "incremental": incremental = jp.getValueAsBoolean(); break;
                case "readOnlyOi":  readOnlyOi = true; break;
                case "readOnly":    readOnly = true; break;

                default: task.log().warn( "Unknown .oimeta fieldname %s", fieldName ); break;
            }
        }

        if ( odName == null )
            throw new ZeidonException( "LodDef not specified in JSON .oimeta" );

        // We don't load the LodDef until now because it's valid JSON to reorder
        // the values in the .oimeta object.
        lodDef = application.getLodDef( task, odName );
        view = task.activateEmptyObjectInstance( lodDef );
        returnList.add( view );
        JsonToken token = jp.nextToken();

        // Create a list to keep track of selected instances.
        selectedInstances = new ArrayList<>();

        // If the next token is FIELD_NAME then OI data is next so get the next token.
        // If it's not the the OI is EMPTY and token should be END_OBJECT.
        if ( token == JsonToken.FIELD_NAME )
            token = jp.nextToken();
        else
            assert token == JsonToken.END_OBJECT;

        return token;
    }

    private static class EntityMeta
    {
        private String lazyLoaded = null;
        private String linkedSource;
        private String entityKey;
        private boolean isLinkedSource;
        private boolean updated  = false;
        private boolean created  = false;
        private boolean deleted  = false;
        private boolean included = false;
        private boolean excluded = false;
        private boolean incomplete = false;
    }

    @Override
    public List<View> readFromStream( DeserializeOi options )
    {
        this.task = options.getTask();
        this.stream = options.getInputStream();
        this.options = options;
        lodDef = options.getLodDef();
        return read();
    }

    private class JsonReaderVersion1 implements JsonReader
    {
        @Override
        public void process() throws Exception
        {
            JsonToken token = jp.nextToken();
            if ( token != JsonToken.START_ARRAY )
                throw new ZeidonException( "OI JSON missing beginning of OI array." );

            while ( readOi() );
        }
    }


    private class SimpleJsonReaderVersion1 implements JsonReader
    {
        @Override
        public void process() throws Exception
        {
            while ( readSimpleOi() );
        }
    }

}
