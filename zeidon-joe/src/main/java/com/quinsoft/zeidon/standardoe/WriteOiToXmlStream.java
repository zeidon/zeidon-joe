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
import java.io.Writer;
import java.util.EnumSet;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.StreamWriter;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.WriteOiFlags;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * @author DG
 *
 */
public class WriteOiToXmlStream implements StreamWriter
{
    private ViewImpl view;
    private Writer writer;
    private EnumSet<WriteOiFlags> control;
    private boolean incremental;

    private int currentIndent;
    private SerializeOi options;

    @Override
    public void writeToStream( SerializeOi options, Writer writer )
    {
        List<View> viewList = options.getViewList();
        if ( viewList.size() > 1 )
            throw new ZeidonException( "XML stream processing can only handle a single OI" );

        view = ((InternalView) viewList.get( 0 ) ).getViewImpl();
        this.options = options;
        this.writer = writer;
        control = options.getFlags();
        incremental = this.control.contains( WriteOiFlags.INCREMENTAL );
    }

    private void write( String string )
    {
        try
        {
            writer.write( string );
        }
        catch ( IOException e )
        {
            throw ZeidonException.wrapException( e )
                                 .appendMessage( "Attempting to write: %s", StringUtils.substring( string, 0, 100 ) );
        }
    }

    private void write( String format, Object...strings )
    {
        if ( strings == null || strings.length == 0 )
        {
            write( format );
        }
        else
        {
            write( String.format( format, strings ) );
        }
    }

    private void writeIndent()
    {
        if ( options.isCompressed() )
            return;

        for ( int i = 0; i < currentIndent; i++ )
            write( "  " );
    }

    private void startElement( final String elementName, final String...attributes )
    {
        startElement( elementName, null, false, attributes );
    }

    private void startElement( final String elementName, final String value, final boolean close, final String...attributes )
    {
        assert attributes.length % 2 == 0 : "Illegal number of attributes; should be an even number.";
        writeIndent();
        write( "<" );
        write( elementName );

        if ( attributes != null && attributes.length > 0 )
        {
            for ( int i = 0; i < attributes.length; i += 2 )
            {
                String esc = StringEscapeUtils.escapeXml( attributes[ i + 1 ] );
                write( " %s=\"%s\"", attributes[ i ], esc );
            }
        }

        if ( value != null )
        {
            String esc = StringEscapeUtils.escapeXml( value );
            write(">%s</%s>\n", esc, elementName );
        }
        else
        {
            if ( close )
                write( "/>\n" );
            else
                write( ">\n" );
        }
    }

    private void endElement( final String elementName )
    {
        writeIndent();
        write( "</%s>\n", elementName );
    }

    private void writeEntity( final EntityInstanceImpl ei )
    {
        final EntityDef entityDef = ei.getEntityDef();

        currentIndent = entityDef.getLevel();

        if ( incremental )
            startElement( entityDef.getName(),
                          "Created",  yesNo( ei.isCreated() ),
                          "Delete",   yesNo( ei.isDeleted() ),
                          "Updated",  yesNo( ei.isUpdated() ),
                          "Included", yesNo( ei.isIncluded() ),
                          "Excluded", yesNo( ei.isExcluded() ) );
        else
            startElement( entityDef.getName() );

        currentIndent++;
        String[] attrIncr = new String[] { "Updated", null };
        for ( AttributeDef attributeDef : ei.getNonNullAttributeList() )
        {
            AttributeValue attrib = ei.getInternalAttribute( attributeDef );
            String value = attrib.getString( view.getTask(), attributeDef );
            if ( incremental )
            {
                attrIncr[ 1 ] = yesNo( attrib.isUpdated() );
                startElement( attributeDef.getName(), value, true, attrIncr );
            }
            else
                startElement( attributeDef.getName(), value, true, (String[]) null );
        }

        // Loop through the children and add them.  If 'incremental' is true then
        // we want hidden entities.
        boolean first = true;
        for ( EntityInstanceImpl child : ei.getDirectChildren( incremental, false ) )
        {
            if ( first )
            {
                write("\n");
                first = false;
            }

            writeEntity( child );
        }

        currentIndent--;
        endElement( entityDef.getName() );
    }

    public void writeToStream()
    {
//        write( "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n" );
        startElement( "zOI", "zObjectName", view.getLodDef().getName(),
                             "zAppName", view.getApplication().getName(),
                             "zIncreFlags", yesNo( incremental ) );

        currentIndent = 0;

        for ( EntityInstanceImpl ei = view.getObjectInstance().getRootEntityInstance();
              ei != null;
              ei = ei.getNextTwin() )
        {
            if ( incremental || ! ei.isHidden() )
                writeEntity( ei );
        }

        endElement( "zOI" );
    }

    private String yesNo( boolean b )
    {
        return b ? "Y" : "N";
    }
}
