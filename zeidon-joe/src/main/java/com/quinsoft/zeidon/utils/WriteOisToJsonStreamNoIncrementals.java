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
package com.quinsoft.zeidon.utils;

import java.io.Writer;
import java.util.Collection;
import java.util.EnumSet;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.StreamWriter;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.WriteOiFlags;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * Serializes an OI by writing it as a JSON stream.  This does not write any Zeidon
 * information (e.g. incremental flags).  Zeidon can retrieve an OI written with this
 * object but it won't have any linking or incremental flags.
 *
 * @author dgc
 *
 */
public class WriteOisToJsonStreamNoIncrementals implements StreamWriter
{
    private final static String VERSION = "1";

    private Collection<? extends View> viewList;
    private EnumSet<WriteOiFlags> flags;

    private JsonGenerator jg;

    @Override
    public void writeToStream( SerializeOi options, Writer writer )
    {
        this.viewList = options.getViewList();
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

        EntityDef rootEntityDef = view.getLodDef().getRoot();
        jg.writeArrayFieldStart( rootEntityDef.getName() );
        if ( ! view.isEmpty() )
        {
            jg.writeStartObject();

            for ( EntityInstance ei:  view.cursor( rootEntityDef ).eachEntity() )
            {
                if ( ei.hasPrevTwin() )
                    jg.writeStartObject();

                writeEntity( ei );

                if ( ei.hasNextTwin() )
                    jg.writeEndObject();
            }

            jg.writeEndObject();
        }
        jg.writeEndArray();
    }

    private EntityDef writeEntity( EntityInstance ei ) throws Exception
    {
        try
        {
            // See if we need to open or close an array field.
            final EntityDef entityDef = ei.getEntityDef();

            for ( AttributeInstance attrib : ei.getAttributes( false ) )
            {
                if ( attrib.getAttributeDef().isHidden() )
                    continue;

                String value = attrib.getString( null );

                // getAttributes will include null attributes if they have been updated
                // (i.e. explicitly set to null).  Since we aren't writing incrementals
                // we don't want those so check for null.
                if ( ! StringUtils.isBlank( value ) )
                    jg.writeStringField( attrib.getAttributeDef().getName(), value );
            }

            // Loop through the children and add them.
            for ( EntityInstance child : ei.getDirectChildren( false, false ) )
            {
                EntityDef childEntityDef = child.getEntityDef();
                if ( ! child.hasPrevTwin() )
                {
                    if ( childEntityDef.getMaxCardinality() > 1 )
                    {
                        jg.writeArrayFieldStart( childEntityDef.getName() );
                        jg.writeStartObject();
                    }
                    else
                        jg.writeObjectFieldStart( childEntityDef.getName() );
                }
                else
                    jg.writeStartObject();

                writeEntity( child );

                jg.writeEndObject();
                if ( ! child.hasNextTwin() )
                {
                    if ( childEntityDef.getMaxCardinality() > 1 )
                        jg.writeEndArray();
                }
            }

            return entityDef;
        }

        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependEntityInstance( ei );
        }
    }
}
