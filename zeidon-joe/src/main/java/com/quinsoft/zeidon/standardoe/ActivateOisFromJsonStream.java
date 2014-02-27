/**
 *
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
import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;
import com.quinsoft.zeidon.objectdefinition.ViewOd;

/**
 * Reads all the OIs from a given stream in JSON format.
 *
 * @author dgc
 *
 */
public class ActivateOisFromJsonStream
{
    private static final EnumSet<CreateEntityFlags> CREATE_FLAGS = EnumSet.of( CreateEntityFlags.fNO_SPAWNING,
                                                                               CreateEntityFlags.fIGNORE_MAX_CARDINALITY,
                                                                               CreateEntityFlags.fDONT_UPDATE_OI,
                                                                               CreateEntityFlags.fDONT_INITIALIZE_ATTRIBUTES,
                                                                               CreateEntityFlags.fIGNORE_PERMISSIONS );
    private final Task                    task;
    private final InputStream             stream;
    private final EnumSet<ActivateFlags>  control;

    /**
     * This keeps track of all the entities that are the sources for linked instances.
     * The key is the EntityKey.
     */
    private final Map<Object, EntityInstanceImpl> linkSources;

    private JsonParser                    jp;
    private Application                   application;
    private boolean                       incremental;
    private ViewOd                        viewOd;
    private View                          view;
    private List<View>                    returnList;

    public ActivateOisFromJsonStream( Task task, InputStream stream, EnumSet<ActivateFlags> control )
    {
        this.task = task;
        this.stream = stream;
        this.control = control;
        returnList = new ArrayList<View>();
        linkSources = new HashMap<Object, EntityInstanceImpl>();
    }

    public List<View> read()
    {
        try
        {
            JsonFactory jsonFactory = new JsonFactory();
            jp = jsonFactory.createJsonParser( stream );
            jp.configure( JsonParser.Feature.AUTO_CLOSE_SOURCE, false );

            // Read the START_OBJECT
            JsonToken token = jp.nextToken();
            if ( token != JsonToken.START_OBJECT )
                throw new ZeidonException( "OI JSON stream doesn't start with object." );

            token = jp.nextToken();
            if ( token != JsonToken.FIELD_NAME )
                throw new ZeidonException( "OI JSON missing OI field name." );

            token = jp.nextToken();
            if ( token != JsonToken.START_ARRAY )
                throw new ZeidonException( "OI JSON missing beginning of OI array." );

            while ( readOi() );

            jp.close();
            view.reset();
        }
        catch ( Exception e )
        {
            ZeidonException ze = ZeidonException.wrapException( e );
            JsonLocation loc = jp.getCurrentLocation();
            ze.appendMessage( "Position line=%d col=%d, token=%s", loc.getLineNr(), loc.getColumnNr(), jp
                    .getCurrentToken().name() );
            throw ze;
        }

        return returnList;
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
            readOiMeta();
        else
            throw new ZeidonException( ".oimeta object not specified in JSON stream" );

        fieldName = jp.getCurrentName();
        if ( !StringUtils.equals( fieldName, viewOd.getRoot().getName() ) )
            throw new ZeidonException( "First entity specified in OI (%s) is not the root (%s)", fieldName,
                                       viewOd.getRoot().getName() );

        readEntity( fieldName );

        token = jp.nextToken();
        if ( token != JsonToken.END_OBJECT )
            throw new ZeidonException( "OI JSON stream doesn't end with object." );

        return true;  // Keep looking for OIs in the stream.
}

    private void readEntity( String entityName ) throws Exception
    {
        JsonToken token = jp.getCurrentToken(); // Skip over entity name

        ViewEntity viewEntity = viewOd.getViewEntity( entityName );

        // Read tokens until we find the token that ends the current list of entities.
        while ( ( token = jp.nextToken() ) != JsonToken.END_ARRAY )
        {
            if ( token != JsonToken.START_OBJECT )
                throw new ZeidonException( "Entity stream doesn't start with array." );

            EntityInstanceImpl ei = (EntityInstanceImpl) view.cursor( viewEntity ).createEntity( CursorPosition.LAST, CREATE_FLAGS );
            token = jp.nextToken();

            // Read tokens until we find the token that ends the current entity.
            EntityMeta entityMeta = null;
            while ( ( token = jp.nextToken() ) != JsonToken.END_OBJECT )
            {
                String fieldName = jp.getCurrentName();
                if ( token != JsonToken.VALUE_STRING )
                    token = jp.nextToken();

                if ( StringUtils.equals( fieldName, ".meta" ) )
                {
                    entityMeta = readEntityMeta();

                    // Now that we have everything we can perform some processing.
                    if ( entityMeta.isLinkedSource )
                        linkSources.put( entityMeta.entityKey, ei );
                    else
                    if ( entityMeta.linkedSource != null )
                        ei.linkInstances( linkSources.get( entityMeta.linkedSource ) );

                    continue;
                }

                // Is this the start of an entity.
                if ( token == JsonToken.START_ARRAY )
                {
                    boolean recursiveChild = false;

                    // Validate that the entity name is valid.
                    ViewEntity childEntity = viewOd.getViewEntity( fieldName );
                    if ( childEntity.getParent() != viewEntity )
                    {
                        // Check to see the childEntity is a recursive child.
                        if ( childEntity.isRecursive() )
                        {
                            view.cursor( viewEntity ).setToSubobject();
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

                // This better be an attribute or attribute meta.
                if ( fieldName.startsWith( "." ) )
                    readAttributeMeta( ei, fieldName );
                else
                {
                    ViewAttribute viewAttribute = viewEntity.getAttribute( fieldName );
                    ei.setInternalAttributeValue( viewAttribute, jp.getText(), false );
                    if ( incremental )
                    {
                        // Since incremental flags are set, assume the attribute hasn't been
                        // updated.  We'll be told later if it has.
                        AttributeValue attrib = ei.getInternalAttribute( viewAttribute );
                        attrib.setUpdated( false );
                    }
                }
            }

            // Now that we've updated everyting, set the flags.
            if ( entityMeta != null )
            {
                ei.setCreated( entityMeta.created );
                ei.setUpdated( entityMeta.updated );
                ei.setDeleted( entityMeta.deleted );
                ei.setIncluded( entityMeta.included );
                ei.setExcluded( entityMeta.excluded );
            }
        }
    }

    private void readAttributeMeta( EntityInstanceImpl ei, String fieldName ) throws JsonParseException, IOException
    {
        String attribName = fieldName.substring( 1 ); // Remove the ".".
        ViewAttribute viewAttribute = ei.getViewEntity().getAttribute( attribName );
        AttributeValue attrib = ei.getInternalAttribute( viewAttribute );

        while ( jp.nextToken() != JsonToken.END_OBJECT )
        {
            fieldName = jp.getCurrentName();

            if ( fieldName.equals( "updated" ) )
                attrib.setUpdated( true );
            else
                throw new ZeidonException( "Unknown entity meta value %s", fieldName );
        }
    }

    private EntityMeta readEntityMeta() throws Exception
    {
        EntityMeta meta = new EntityMeta();
        while ( jp.nextToken() != JsonToken.END_OBJECT )
        {
            String fieldName = jp.getCurrentName();

            if ( fieldName.equals( "incrementals" ) )
                readIncrementals( meta );
            else
            if ( fieldName.equals( "isLinkedSource" ) )
                meta.isLinkedSource = true;
            else
            if ( fieldName.equals( "entityKey" ) )
                meta.entityKey = jp.getText();
            else
            if ( fieldName.equals( "linkedSource" ) )
                meta.linkedSource = jp.getText();
            else
                throw new ZeidonException( "Unknown entity meta value %s", fieldName );
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

    private void readOiMeta() throws Exception
    {
        String odName = null;
        jp.nextToken();
        while ( jp.nextToken() != JsonToken.END_OBJECT )
        {
            String fieldName = jp.getCurrentName();
            jp.nextToken(); // Move to value.
            if ( StringUtils.equals( fieldName, "application" ) )
                application = task.getApplication( jp.getValueAsString() );
            else if ( StringUtils.equals( fieldName, "odName" ) )
                odName = jp.getValueAsString(); // Save OD name for later.
            else if ( StringUtils.equals( fieldName, "incremental" ) )
                incremental = jp.getValueAsBoolean();
            else
                throw new ZeidonException( "Unknown .oimeta fieldname %s", fieldName );
        }

        if ( odName == null )
            throw new ZeidonException( "ViewOD not specified in JSON .oimeta" );

        // We don't load the ViewOD until now because it's valid JSON to reorder
        // the values
        // in the .oimeta object.
        viewOd = application.getViewOd( task, odName );
        view = task.activateEmptyObjectInstance( viewOd );
        returnList.add( view );
        jp.nextToken();
        jp.nextToken();
    }

    private static class EntityMeta
    {
        public String linkedSource;
        public String entityKey;
        public boolean isLinkedSource;
        boolean updated  = false;
        boolean created  = false;
        boolean deleted  = false;
        boolean included = false;
        boolean excluded = false;
    }
}
