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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.EntityCursor.CursorStatus;
import com.quinsoft.zeidon.SelectSet;
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
public class WriteOisToXmlStream implements StreamWriter
{
    private ViewImpl currentView;
    private Writer writer;
    private EnumSet<WriteOiFlags> control;
    private boolean incremental;
    private final Set<ObjectInstance> ois = new HashSet<ObjectInstance>();

    private int currentIndent;
    private SerializeOi options;

    @Override
    public void writeToStream( SerializeOi options, Writer writer )
    {
        List<View> viewList = options.getViewList();

        // Create a set of all the OIs and turn off the record owner flag.  The record owner
        // flag will be used to determine if a linked EI has been written to the stream.
        for ( View view : viewList )
        {
            ObjectInstance oi = ((InternalView) view).getViewImpl().getObjectInstance();
            ois.add( oi );
            for ( EntityInstanceImpl ei = oi.getRootEntityInstance(); ei != null; ei = ei.getNextTwin() )
                ei.setRecordOwner( false );
        }

        currentView = ((InternalView) viewList.get( 0 ) ).getViewImpl();
        this.options = options;
        this.writer = writer;
        control = options.getFlags();
        incremental = this.control.contains( WriteOiFlags.INCREMENTAL );

        if ( viewList.size() > 1 )
        {
            startElement( "zOIs" );
            currentIndent++;
        }

        for ( View view : viewList )
        {
            currentView = ((InternalView) view ).getViewImpl();
            writeViewToStream();
        }

        if ( viewList.size() > 1 )
        {
            currentIndent--;
            endElement( "zOIs" );
        }
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
                // Don't bother printing if it's empty.
                if ( ! StringUtils.isBlank( esc ) )
                    write( " %s=\"%s\"", attributes[ i ], esc );
            }
        }

        if ( value != null )
        {
            String esc = StringEscapeUtils.escapeXml( value );
            write(">%s</%s>", esc, elementName );
            if ( ! options.isCompressed() )
                write( "\n" );
        }
        else
        {
            if ( close )
                write( "/>" );
            else
                write( ">" );

            if ( ! options.isCompressed() )
                write( "\n" );
        }
    }

    private void endElement( final String elementName )
    {
        writeIndent();
        write( "</%s>", elementName );
        if ( ! options.isCompressed() )
            write( "\n" );
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

    private void writeEntity( final EntityInstanceImpl ei )
    {
        final EntityDef entityDef = ei.getEntityDef();

        currentIndent = entityDef.getDepth();

        boolean writeAttributes = true;
        if ( incremental )
        {
            EntityCursorImpl cursor = currentView.cursor( entityDef );

            // Check to see if the current ei is the selected EI.  We first check the status because
            // calling getEntityInstance() could potentially trigger a lazy-load.
            boolean selected = cursor.getStatus() == CursorStatus.SET &&
                               cursor.getEntityInstance() == ei;

            EntityInstanceImpl recordOwner = findLinkedRecordOwner( ei );
            String entityKey = null;
            String isLinkedSource = null;
            if ( recordOwner != null )
            {
                if ( recordOwner == ei )
                {
                    // TODO: validate that ei.entityDef has all the attributes in the shared
                    // attribute hash.
                    ei.setRecordOwner( true );
                    isLinkedSource = "Y";
                    entityKey = Long.toString( ei.getEntityKey() );
                }
                else
                {
                    // Write the entity key of the record owner.
                    entityKey = Long.toString( ei.getEntityKey() );
                    writeAttributes = false;
                }
            }

            StringBuilder lazyLoaded = new StringBuilder();
            if ( ei.hasLoadedLazyChildren() )
            {
                for ( EntityDef def : ei.getEntitiesLoadedLazily() )
                    lazyLoaded.append( "," ).append( def.getName() );
                lazyLoaded.deleteCharAt( 0 );
            }

            startElement( entityDef.getName(),
                          "created",    yesNull( ei.isCreated() ),
                          "delete",     yesNull( ei.isDeleted() ),
                          "updated",    yesNull( ei.isUpdated() ),
                          "included",   yesNull( ei.isIncluded() ),
                          "excluded",   yesNull( ei.isExcluded() ),
                          "incomplete", yesNull( ei.isIncomplete() ),
                          "selected",   yesNull( selected ),
                          "readonly",   yesNull( currentView.isReadOnly() ),
                          "isLinkedSource", isLinkedSource,
                          "entityKey",  entityKey,
                          "lazyLoaded", lazyLoaded.toString() );
        }
        else
            startElement( entityDef.getName() );

        currentIndent++;
        String[] attrIncr = new String[] { "updated", null };
        if ( writeAttributes )
        {
            for ( AttributeDef attributeDef : ei.getNonNullAttributeList() )
            {
                AttributeValue attrib = ei.getInternalAttribute( attributeDef );
                String value = attrib.getString( currentView.getTask(), attributeDef );
                if ( incremental )
                {
                    attrIncr[ 1 ] = yesNull( attrib.isUpdated() );
                    startElement( attributeDef.getName(), value, true, attrIncr );
                }
                else
                    startElement( attributeDef.getName(), value, true, (String[]) null );
            }
        }

        // Loop through the children and add them.  If 'incremental' is true then
        // we want hidden entities.
        boolean first = true;
        for ( EntityInstanceImpl child : ei.getDirectChildren( incremental, false ) )
        {
            if ( first )
            {
                if ( ! options.isCompressed() )
                    write( "\n" );
                first = false;
            }

            writeEntity( child );
        }

        currentIndent--;
        endElement( entityDef.getName() );
    }

    private void writeViewToStream()
    {
        SelectSet rootSelectSet = null;
        Map<Long, SelectSet> sets = options.getRootSelectSets();
        if ( sets != null )
            rootSelectSet = sets.get( currentView.getOiId() );

//        write( "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n" );
        startElement( "zOI", "objectName", currentView.getLodDef().getName(),
                             "appName", currentView.getApplication().getName(),
                             "increFlags", yesNo( incremental ) );

        currentIndent++;

        for ( EntityInstanceImpl ei = currentView.getObjectInstance().getRootEntityInstance();
              ei != null;
              ei = ei.getNextTwin() )
        {
            // If we have a root select set and the EI is not selected then skip it.
            if ( rootSelectSet != null && rootSelectSet.isSelected( ei ) )
                continue;

            if ( incremental || ! ei.isHidden() )
                writeEntity( ei );
        }

        currentIndent--;
        endElement( "zOI" );
    }

    private String yesNull( boolean b )
    {
        return b ? "Y" : "";
    }

    private String yesNo( boolean b )
    {
        return b ? "Y" : "N";
    }
}
