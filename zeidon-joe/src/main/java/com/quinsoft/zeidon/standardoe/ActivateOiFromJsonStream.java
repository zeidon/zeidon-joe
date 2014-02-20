/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import java.io.InputStream;
import java.util.EnumSet;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;
import com.quinsoft.zeidon.objectdefinition.ViewOd;

/**
 * @author dgc
 *
 */
class ActivateOiFromJsonStream
{
    private static final EnumSet<CreateEntityFlags> CREATE_FLAGS = EnumSet.of( CreateEntityFlags.fNO_SPAWNING,
                                                                               CreateEntityFlags.fIGNORE_MAX_CARDINALITY,
                                                                               CreateEntityFlags.fDONT_UPDATE_OI,
                                                                               CreateEntityFlags.fDONT_INITIALIZE_ATTRIBUTES,
                                                                               CreateEntityFlags.fIGNORE_PERMISSIONS );
    private final TaskImpl task;
    private final InputStream stream;
    private final EnumSet<ActivateFlags> control;

    private JsonParser  jp;
    private Application application;
    private boolean     incremental;
    private ViewOd      viewOd;
    private ViewImpl    view;

    ActivateOiFromJsonStream( TaskImpl task,
                              InputStream stream,
                              EnumSet<ActivateFlags> control )
    {
        this.task = task;
        this.stream = stream;
        this.control = control;
    }

    ViewImpl read()
    {
        try
        {
            JsonFactory jsonFactory = new JsonFactory();
            jp = jsonFactory.createJsonParser( stream );
            readOi();
            jp.close();
            view.reset();
        }
        catch ( Exception e )
        {
            ZeidonException ze = ZeidonException.wrapException( e );
            JsonLocation loc = jp.getCurrentLocation();
            ze.appendMessage( "Position line=%d col=%d, token=%s",
                              loc.getLineNr(), loc.getColumnNr(), jp.getCurrentToken().name() );
            throw ze;
        }
        finally
        {
            IOUtils.closeQuietly( jp );
        }

        return view;
    }

    private void readOi() throws Exception
    {
        JsonToken token = jp.nextToken();
        if ( token != JsonToken.START_OBJECT )
            throw new ZeidonException( "OI JSON stream doesn't start with object." );

        token = jp.nextToken();

        String fieldName = jp.getCurrentName();
        if ( StringUtils.equals( fieldName, ".oimeta" ) )
            readOiMeta();
        else
            throw new ZeidonException( ".oimeta object not specified in JSON stream" );

        fieldName = jp.getCurrentName();
        if ( ! StringUtils.equals( fieldName, viewOd.getRoot().getName() ) )
            throw new ZeidonException( "First entity specified in OI (%s) is not the root (%s)",
                                       fieldName, viewOd.getRoot().getName() );

        readEntity( fieldName );
    }

    private void readEntity( String entityName ) throws Exception
    {
        JsonToken token = jp.getCurrentToken(); // Skip over entity name

        ViewEntity viewEntity = viewOd.getViewEntity( entityName );

        while ( ( token = jp.nextToken() ) != JsonToken.END_ARRAY )
        {
            if ( token != JsonToken.START_OBJECT )
                throw new ZeidonException( "Entity stream doesn't start with array." );

            EntityInstanceImpl ei = view.cursor( viewEntity ).createEntity( CursorPosition.LAST, CREATE_FLAGS );
            token = jp.nextToken();

            EntityMeta entityMeta = null;
            while ( ( token = jp.nextToken() ) != JsonToken.END_OBJECT )
            {
                String fieldName = jp.getCurrentName();
                if ( token != JsonToken.VALUE_STRING )
                    token = jp.nextToken();
                if ( StringUtils.equals( fieldName, ".meta" ) )
                {
                    entityMeta = readEntityMeta();
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
                            throw new ZeidonException( "Parse error: %s is not a child of %s", fieldName, entityName );
                    }

                    readEntity( fieldName );

                    if ( recursiveChild )
                        view.resetSubobject();

                    continue;
                }

                // This better be an attribute.
                if ( StringUtils.equals( jp.getText(), fieldName ) )  // If jp points to attr name, get next token.
                    token = jp.nextToken();

                ViewAttribute viewAttribute = viewEntity.getAttribute( fieldName );
                ei.setInternalAttributeValue( viewAttribute, jp.getText(), false );
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

    private EntityMeta readEntityMeta() throws Exception
    {
        EntityMeta meta = new EntityMeta();
        jp.nextToken();
        while ( jp.nextToken() != JsonToken.END_OBJECT )
        {
            String fieldName = jp.getCurrentName();
            jp.nextToken();

            if ( fieldName.equals( "updated" ) )
                meta.updated = true;
            else
            if ( fieldName.equals( "created" ) )
                meta.created = true;
            else
            if ( fieldName.equals( "deleted" ) )
                meta.deleted = true;
            else
            if ( fieldName.equals( "included" ) )
                meta.included = true;
            else
            if ( fieldName.equals( "excluded" ) )
                meta.excluded = true;
            else
                throw new ZeidonException( "Unknown entity meta value %s", fieldName );
        }

        return meta;
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
            else
            if ( StringUtils.equals( fieldName, "odName" ) )
                odName = jp.getValueAsString();  // Save OD name for later.
            else
            if ( StringUtils.equals( fieldName, "incremental" ) )
                incremental = jp.getValueAsBoolean();
            else
                throw new ZeidonException( "Unknown .oimeta fieldname %s", fieldName );
        }

        if ( odName == null )
            throw new ZeidonException( "ViewOD not specified in JSON .oimeta" );

        // We don't load the ViewOD until now because it's valid JSON to reorder the values
        // in the .oimeta object.
        viewOd = application.getViewOd( task, odName );
        view = task.activateEmptyObjectInstance( viewOd );
        jp.nextToken();
        jp.nextToken();
    }

    private static class EntityMeta
    {
        boolean updated = false;
        boolean created = false;
        boolean deleted = false;
        boolean included = false;
        boolean excluded = false;
    }
}
