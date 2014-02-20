/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import java.io.Writer;
import java.util.EnumSet;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.quinsoft.zeidon.WriteOiFlags;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * @author dgc
 *
 */
class WriteOiToJsonStream
{
    private final static EnumSet<WriteOiFlags> defaultFlags = EnumSet.noneOf( WriteOiFlags.class );

    private final ViewImpl view;
    private final Writer writer;
    private final EnumSet<WriteOiFlags> control;
    private final boolean incremental;

    private JsonGenerator jg;

    private boolean entityMetaStarted;

    WriteOiToJsonStream(ViewImpl view, Writer writer, EnumSet<WriteOiFlags> control )
    {
        this.view = view;
        this.writer = writer;
        this.control = control == null ? defaultFlags : control;
        incremental = this.control.contains( WriteOiFlags.fINCREMENTAL );
    }

    void writeToStream()
    {
        JsonFactory jsonF = new JsonFactory();
        try
        {
            jg = jsonF.createJsonGenerator( writer );
            jg.useDefaultPrettyPrinter(); // enable indentation just to make debug/testing easier

            jg.writeStartObject();
            writeOi();
            jg.writeEndObject();
            jg.close();
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    private void writeOi() throws Exception
    {
        writeOiMeta();

        ViewEntity lastViewEntity = null;

        for ( EntityInstanceImpl ei = view.getObjectInstance().getRootEntityInstance();
              ei != null;
              ei = ei.getNextTwin() )
        {
            if ( incremental || !ei.isHidden() )
                lastViewEntity = writeEntity( ei, lastViewEntity );
        }

        if ( lastViewEntity != null )
            jg.writeEndArray();
    }

    private void writeOiMeta() throws Exception
    {
        jg.writeObjectFieldStart( ".oimeta" );
        jg.writeStringField( "application", view.getViewOd().getApplication().getName() );
        jg.writeStringField( "odName", view.getViewOd().getName() );
        jg.writeBooleanField( "incremental", incremental );
        jg.writeEndObject();
    }

    private ViewEntity writeEntity( EntityInstanceImpl ei, ViewEntity lastViewEntity ) throws Exception
    {
        try
        {
            // See if we need to open or close an array field.
            final ViewEntity viewEntity = ei.getViewEntity();
            if ( lastViewEntity != viewEntity )
            {
                if ( lastViewEntity != null )
                    jg.writeEndArray();

                lastViewEntity = viewEntity;
                jg.writeArrayFieldStart( viewEntity.getName() );
            }

            jg.writeStartObject(); // TODO: Write meta/incremental information.

            writeEntityMeta( ei );

            String[] attrIncr = new String[] { "Updated", null };
            for ( ViewAttribute viewAttribute : ei.getNonNullAttributeList() )
            {
                AttributeValue attrib = ei.getInternalAttribute( viewAttribute );
                String value = attrib.getString( view.getTask(), viewAttribute );
                jg.writeStringField( viewAttribute.getName(), value );
                if ( incremental )
                {
                }
            }

            // Loop through the children and add them.  If 'incremental' is true then
            // we want hidden entities.
            ViewEntity lastChildViewEntity = null;
            for ( EntityInstanceImpl child : ei.getDirectChildren( incremental ) )
            {
                lastChildViewEntity = writeEntity( child, lastChildViewEntity );
            }

            if ( lastChildViewEntity != null )
                jg.writeEndArray();

            jg.writeEndObject();

            return viewEntity;
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependEntityInstance( ei );
        }
    }

    private void writeEntityMeta( EntityInstanceImpl ei ) throws Exception
    {
        if ( ! incremental )
            return;  // Nothing to do if we're not writing incrementals.

        // Keep track of whether we started a .meta tag.  We'll only create one if we need to.
        entityMetaStarted = false;

        writeEntityFlag( ei, "updated", ei.isUpdated() );
        writeEntityFlag( ei, "created", ei.isCreated() );
        writeEntityFlag( ei, "deleted", ei.isDeleted() );
        writeEntityFlag( ei, "included", ei.isIncluded() );
        writeEntityFlag( ei, "excluded", ei.isExcluded() );

        if ( entityMetaStarted )
            jg.writeEndObject();
    }

    private void writeEntityFlag( EntityInstanceImpl ei, String flagName, boolean value ) throws Exception
    {
        if ( ! value )
            return;

        if ( ! entityMetaStarted )
        {
            jg.writeObjectFieldStart( ".meta" );
            entityMetaStarted = true;
        }

        jg.writeBooleanField( flagName, value );
    }
}
