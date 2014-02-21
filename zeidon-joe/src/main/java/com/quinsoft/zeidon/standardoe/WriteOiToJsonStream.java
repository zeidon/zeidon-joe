/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.WriteOiFlags;
import com.quinsoft.zeidon.WriteOiOptions;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * @author dgc
 *
 */
public class WriteOiToJsonStream
{
    private final Collection<View> viewList;
    private final Writer writer;
    private final WriteOiOptions options;
    private final EnumSet<WriteOiFlags> flags;
    private final boolean incremental;

    private JsonGenerator jg;

    private boolean entityMetaStarted;

    public WriteOiToJsonStream( Collection<View> viewList, Writer writer, WriteOiOptions options )
    {
        this.viewList = viewList;
        this.writer = writer;
        this.options = options;
        this.flags = options.getFlags();
        incremental = this.flags.contains( WriteOiFlags.fINCREMENTAL );
    }

    public WriteOiToJsonStream(View view, Writer writer, WriteOiOptions options )
    {
        this( viewToCollection( view ), writer, options );
    }

    public void writeToStream()
    {
        JsonFactory jsonF = new JsonFactory();
        try
        {
            jg = jsonF.createJsonGenerator( writer );
            jg.useDefaultPrettyPrinter(); // enable indentation just to make debug/testing easier

            jg.writeStartObject();
            for ( View view : viewList )
                writeOi( view );
            
            jg.writeEndObject();
            jg.close();
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    private void writeOi( View view ) throws Exception
    {
        writeOiMeta( view );

        ViewEntity lastViewEntity = null;

        ViewImpl viewImpl = ((InternalView)view).getViewImpl();
        for ( EntityInstanceImpl ei = viewImpl.getObjectInstance().getRootEntityInstance();
              ei != null;
              ei = ei.getNextTwin() )
        {
            if ( incremental || !ei.isHidden() )
                lastViewEntity = writeEntity( ei, lastViewEntity );
        }

        if ( lastViewEntity != null )
            jg.writeEndArray();
    }

    private void writeOiMeta( View view ) throws Exception
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
                String value = attrib.getString( ei.getTask(), viewAttribute );
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
    
    /**
     * Convenience method to turn a single view into a collection.
     * 
     * @param view
     * @return
     */
    private static Collection<View> viewToCollection( View view )
    {
        ArrayList<View> list = new ArrayList<View>();
        list.add( view );
        return list;
    }
}
