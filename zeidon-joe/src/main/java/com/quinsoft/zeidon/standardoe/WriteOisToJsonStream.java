/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.WriteOiFlags;
import com.quinsoft.zeidon.WriteToStream;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * @author dgc
 *
 */
public class WriteOisToJsonStream
{
    private final static String VERSION = "1";

    private final Collection<? extends View> viewList;
    private final Writer writer;
    private final WriteToStream options;
    private final EnumSet<WriteOiFlags> flags;
    private final boolean incremental;
    private final Set<ObjectInstance> ois = new HashSet<ObjectInstance>();

    private JsonGenerator jg;

    public WriteOisToJsonStream( Collection<? extends View> viewList, Writer writer, WriteToStream options )
    {
        this.viewList = viewList;
        this.writer = writer;
        this.options = options;
        if ( options.getFlags() == null )
            flags = EnumSet.noneOf( WriteOiFlags.class );
        else
            flags = this.options.getFlags();
        incremental = this.flags.contains( WriteOiFlags.INCREMENTAL );
    }

    public WriteOisToJsonStream(View view, Writer writer, WriteToStream options )
    {
        this( Arrays.asList( view ), writer, options );
    }

    /**
     * Returns a JSON string that serializes the collection of views.
     *
     * @param viewList
     * @param options
     * @return
     */
    public static String writeOisToJsonString( Collection<? extends View> viewList, WriteToStream options )
    {
        StringWriter writer = new StringWriter();
        WriteOisToJsonStream jsonBuilder = new WriteOisToJsonStream( viewList, writer, options );
        jsonBuilder.writeToStream();
        return writer.toString();
    }

    public void writeToStream()
    {
        // Create a set of all the OIs and turn off the record owner flag.  The record owner
        // flag will be used to determine if a linked EI has been written to the stream.
        for ( View view : viewList )
        {
            ObjectInstance oi = ((InternalView) view).getViewImpl().getObjectInstance();
            ois.add( oi );
            for ( EntityInstanceImpl ei = oi.getRootEntityInstance(); ei != null; ei = ei.getNextTwin() )
                ei.setRecordOwner( false );
        }

        JsonFactory jsonF = new JsonFactory();
        try
        {
            jg = jsonF.createGenerator( writer );
            jg.useDefaultPrettyPrinter(); // enable indentation just to make debug/testing easier
            jg.writeStartObject();

            // Write meta info for entire JSON object.
            jg.writeObjectFieldStart( ".meta" );
            jg.writeStringField( "version", VERSION );
            jg.writeStringField( "date", new LocalDateTime().toString() );
            jg.writeEndObject();

            jg.writeArrayFieldStart( "OIs" );

            for ( View view : viewList )
            {
                jg.writeStartObject();
                writeOi( view );
                jg.writeEndObject();
            }
            jg.writeEndArray();
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

        // If lastViewEntity is null then the OI is empty so write a start array
        // to indicate it's empty.
        if ( lastViewEntity == null )
        {
//            jg.writeStartObject();
//            jg.writeEndObject();
        }
        else
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

            jg.writeStartObject();
            boolean writePersistent = writeEntityMeta( ei );
            for ( ViewAttribute viewAttribute : ei.getNonNullAttributeList() )
            {
                AttributeValue attrib = ei.getInternalAttribute( viewAttribute );
                if ( writePersistent || ! viewAttribute.isPersistent() )
                {
                    String value = attrib.getString( ei.getTask(), viewAttribute );
                    jg.writeStringField( viewAttribute.getName(), value );
                    if ( incremental && viewAttribute.isPersistent() )
                        writeAttributeMeta( attrib, viewAttribute );
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

    private void writeAttributeMeta( AttributeValue attrib, ViewAttribute viewAttribute )
                        throws JsonGenerationException, IOException
    {
        if ( ! attrib.isUpdated() )
            return;

        jg.writeObjectFieldStart( "." + viewAttribute.getName() );
        jg.writeStringField( "updated", "true" );
        jg.writeEndObject();
    }

    private String createIncrementalStr( EntityInstanceImpl ei )
    {
        String str = "";

        if ( ei.isUpdated() )
            str += 'U';

        if ( ei.isCreated() )
            str += 'C';

        if ( ei.isDeleted() )
            str += 'D';

        if ( ei.isIncluded() )
            str += 'I';

        if ( ei.isExcluded() )
            str += 'X';

        return str;
    }

    /**
     * If this EI is linked with another EI in the OI set then this returns the record owner.
     * If no record owner, returns EI.
     *
     * @param ei
     * @return
     */
    private EntityInstanceImpl findLinkedRecordOwner( EntityInstanceImpl ei )
    {
        // Keep track of whether we found another EI linked with this one.
        boolean foundLinked = false;

        // Run through the list of the other linked instances.
        for ( EntityInstanceImpl linked : ei.getLinkedInstances() )
        {
            if ( ois.contains( linked.getObjectInstance() ) )
            {
                foundLinked = true;
                if ( linked.isRecordOwner() )
                    return linked;
            }
        }

        // If we get here then we didn't find a record owner.  if foundLinked is true
        // then we did find a linked EI.
        if ( foundLinked )
            return ei;

        // Didn't find any EI's that are linked with ei.
        return null;
    }

    /**
     * Returns true if we should also write the persistent attributes of the EI.  We
     * don't write the persistent attributes if the EI is linked and it's not the
     * record owner.
     *
     * @param ei
     * @return
     * @throws Exception
     */
    private boolean writeEntityMeta( EntityInstanceImpl ei ) throws Exception
    {
        boolean writeAttributes = true;
        EntityInstanceImpl recordOwner = findLinkedRecordOwner( ei );

        if ( ! incremental && recordOwner == null )
            return writeAttributes;  // Nothing to do if we're not writing incrementals.

        String str = createIncrementalStr( ei );
        if ( StringUtils.isBlank( str ) && recordOwner == null )
            return writeAttributes;

        jg.writeObjectFieldStart( ".meta" );

        if ( ! StringUtils.isBlank( str )  )
            jg.writeStringField( "incrementals", str );

        if ( recordOwner != null )
        {
            if ( recordOwner == ei )
            {
                // TODO: validate that ei.viewEntity has all the attributes in the shared
                // attribute hash.
                ei.setRecordOwner( true );
                jg.writeStringField( "isLinkedSource", "true" );
                jg.writeStringField( "entityKey", Long.toString( ei.getEntityKey() ));
            }
            else
            {
                // Write the entity key of the record owner.
                jg.writeStringField( "linkedSource", Long.toString( recordOwner.getEntityKey() ));
                writeAttributes = false;
            }
        }

        jg.writeEndObject();
        return writeAttributes;
    }
}
