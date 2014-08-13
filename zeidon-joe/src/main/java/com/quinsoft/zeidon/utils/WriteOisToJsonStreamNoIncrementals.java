/**
 *
 */
package com.quinsoft.zeidon.utils;

import java.io.Writer;
import java.util.Collection;
import java.util.EnumSet;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.StreamWriter;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.WriteOiFlags;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * @author dgc
 *
 */
public class WriteOisToJsonStreamNoIncrementals implements StreamWriter
{
    private final static String VERSION = "1";

    private Collection<? extends View> viewList;
    private Writer writer;
    private EnumSet<WriteOiFlags> flags;

    private JsonGenerator jg;

    @Override
    public void writeToStream( SerializeOi options )
    {
        this.viewList = options.getViewList();
        this.writer = options.getWriter();
        if ( options.getFlags() == null )
            flags = EnumSet.noneOf( WriteOiFlags.class );
        else
            flags = options.getFlags();
        if ( flags.contains( WriteOiFlags.INCREMENTAL ) )
            throw new ZeidonException( "This JSON stream writer not intended for writing incremental." );

        JsonFactory jsonF = new JsonFactory();
        try
        {
            jg = jsonF.createGenerator( writer );
            jg.useDefaultPrettyPrinter(); // enable indentation just to make debug/testing easier
            jg.writeStartObject();

            jg.writeStringField( "version", VERSION );

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
        view = view.newView();  // To preserve cursor positions in the original view.
        ViewEntity lastViewEntity = null;

        ViewEntity rootViewEntity = view.getViewOd().getRoot();
        for ( EntityInstance ei:  view.cursor( rootViewEntity ).eachEntity() )
        {
            lastViewEntity = writeEntity( ei, lastViewEntity );
        }

        if ( lastViewEntity != null )
        {
            jg.writeEndObject();
            if ( lastViewEntity.getMaxCardinality() > 1 )
                jg.writeEndArray();
        }
    }

    private ViewEntity writeEntity( EntityInstance ei, ViewEntity lastViewEntity ) throws Exception
    {
        try
        {
            // See if we need to open or close an array field.
            final ViewEntity viewEntity = ei.getViewEntity();
            if ( lastViewEntity != viewEntity )
            {
                if ( lastViewEntity != null )
                {
                    jg.writeEndObject();

                    if ( lastViewEntity.getMaxCardinality() > 1 )
                        jg.writeEndArray();
                }

                lastViewEntity = viewEntity;

                if ( viewEntity.getMaxCardinality() > 1 )
                {
                    jg.writeArrayFieldStart( viewEntity.getName() );
                    jg.writeStartObject();
                }
                else
                    jg.writeObjectFieldStart( viewEntity.getName() );
            }

            for ( AttributeInstance attrib : ei.attributeList( false ) )
            {
                String value = attrib.getString();
                jg.writeStringField( attrib.getViewAttribute().getName(), value );
            }

            // Loop through the children and add them.
            ViewEntity lastChildViewEntity = null;
            for ( EntityInstance child : ei.getDirectChildren() )
                lastChildViewEntity = writeEntity( child, lastChildViewEntity );

            if ( lastChildViewEntity != null )
            {
                jg.writeEndObject();
                if ( lastChildViewEntity.getMaxCardinality() > 1 )
                    jg.writeEndArray();
            }

            return viewEntity;
        }

        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependEntityInstance( ei );
        }
    }
}
