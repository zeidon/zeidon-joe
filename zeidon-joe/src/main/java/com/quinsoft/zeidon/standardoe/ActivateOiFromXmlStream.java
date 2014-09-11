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

import java.io.InputStream;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.StreamReader;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * @author dgc
 *
 */
class ActivateOiFromXmlStream implements StreamReader
{
    private static final EnumSet<CreateEntityFlags> CREATE_FLAGS = EnumSet.of( CreateEntityFlags.fNO_SPAWNING,
                                                                               CreateEntityFlags.fIGNORE_MAX_CARDINALITY,
                                                                               CreateEntityFlags.fDONT_UPDATE_OI,
                                                                               CreateEntityFlags.fDONT_INITIALIZE_ATTRIBUTES,
                                                                               CreateEntityFlags.fIGNORE_PERMISSIONS );

    private Task        task;
    private InputStream inputStream;
    private boolean     ignoreInvalidEntityNames;
    private boolean     ignoreInvalidAttributeNames;

    private Application              application;
    private LodDef                   lodDef;
    private ViewImpl                 view;
    private EnumSet<ActivateFlags>   control;
    private boolean                  incremental = false;
    private Stack<Attributes>        entityAttributes = new Stack<Attributes>();
    private Stack<Attributes>        attributeAttributes = new Stack<Attributes>();
    private Stack<EntityDef>        currentEntityStack = new Stack<EntityDef>();
    private EntityDef               currentEntityDef;
    private StringBuilder            characterBuffer;

    ViewImpl read()
    {
        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new SaxParserHandler();
            saxParser.parse( inputStream, handler );

            // If user wanted just one root remove others if we have more than one.
            // We don't want to abort the loading of entities in the middle of
            // the stream because that could throw off XML processing.
            EntityCursorImpl rootCursor = view.getViewCursor().getEntityCursor( lodDef.getRoot() );
            if ( control.contains( ActivateFlags.fSINGLE ) && rootCursor.getEntityCount() > 1 )
            {
                rootCursor.setFirst();
                while ( rootCursor.setNext().isSet() )
                    rootCursor.dropEntity();
                rootCursor.setFirst();
            }

            view.reset();
            return view;
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    private boolean isYes( String str )
    {
        if ( StringUtils.isBlank( str ) )
            return false;

        switch ( str.toUpperCase().charAt( 0 ) )
        {
            case 'Y':
            case '1':
            case 'T':
                return true;

            default:
                return false;
        }
    }

    private void createOi( String qName, Attributes attributes )
    {
        String appName = attributes.getValue( "zAppName" );
        if ( StringUtils.isBlank( appName ) )
            throw new ZeidonException("zOI element does not specify zAppName" );

        application = task.getApplication( appName );
        String odName = attributes.getValue( "zObjectName" );
        if ( StringUtils.isBlank( odName ) )
            throw new ZeidonException("zOI element does not specify zObjectName" );

        lodDef = application.getLodDef( task, odName );
        view = (ViewImpl) task.activateEmptyObjectInstance( lodDef );

        String increFlags = attributes.getValue( "zIncreFlags" );
        if ( ! StringUtils.isBlank( increFlags ) )
            incremental = isYes( increFlags );
    }


    private void createEntity( String entityName, Attributes attributes )
    {
        currentEntityDef = lodDef.getEntityDef( entityName );
        currentEntityStack.push( currentEntityDef );
        EntityCursorImpl cursor = view.cursor( currentEntityDef );
        cursor.createEntity( CursorPosition.LAST, CREATE_FLAGS );

        // If we're setting incremental flags, save them for later.
        if ( incremental )
            entityAttributes.push( attributes );
    }

    private void setAttribute( String attributeName, Attributes attributes )
    {
        characterBuffer = new StringBuilder();

        if ( incremental )
            attributeAttributes.push( attributes );
    }

    private class SaxParserHandler extends DefaultHandler
    {
        @Override
        public void startElement( String uri,
                                  String localName,
                                  String qName,
                                  Attributes attributes ) throws SAXException
        {
            if ( StringUtils.equalsIgnoreCase( qName, "zOI" ) )
            {
                createOi( qName, attributes );
                return;
            }

            // If we get here then we better have a view.
            if ( view == null )
                throw new ZeidonException( "XML stream does not specify zOI element" );

            // Is the element name an entity name?
            if ( lodDef.getEntityDef( qName, false ) != null )
            {
                createEntity( qName, attributes );
                return;
            }

            if ( currentEntityDef.getAttribute( qName, false ) != null )
            {
                setAttribute( qName, attributes );
                return;
            }

            // If we get here then we don't know what we have.  If user has specified
            // that we're to ignore entity or attribute errors then we'll just assume that
            // this element is an old entity/attribute name and we can ignore it.
            if ( ignoreInvalidAttributeNames || ignoreInvalidEntityNames )
                return;

            throw new ZeidonException( "Unknown XML element: %s", qName );
        }

        @Override
        public void endElement( String uri, String localName, String qName ) throws SAXException
        {
            // Is the element an attribute name?
            AttributeDef attributeDef = currentEntityDef.getAttribute( qName, false );
            if ( attributeDef != null )
            {
                EntityInstanceImpl ei = view.cursor( attributeDef.getEntityDef() ).getEntityInstance();
                ei.setInternalAttributeValue( attributeDef, characterBuffer.toString(), false );
                characterBuffer = null;

                if ( incremental )
                {
                    Attributes attributes = attributeAttributes.pop();
                    ei.setAttributeUpdated( attributeDef, isYes( attributes.getValue( "Updated" ) ) );
                }

                return;
            }

            // Is the element name an entity name?
            if ( lodDef.getEntityDef( qName, false ) != null )
            {
                assert qName.equals( currentEntityDef.getName() ) : "Mismatching entity names in XML";

                if ( incremental )
                {
                    EntityInstanceImpl ei = view.cursor( qName ).getEntityInstance();
                    Attributes attributes = entityAttributes.pop();

                    ei.setUpdated( isYes( attributes.getValue( "Updated" ) ) );
                    ei.setCreated( isYes( attributes.getValue( "Created" ) ) );
                    ei.setIncluded( isYes( attributes.getValue( "Included" ) ) );
                    ei.setExcluded( isYes( attributes.getValue( "Excluded" ) ) );
                    ei.setDeleted( isYes( attributes.getValue( "Deleted" ) ) );
                }

                // The top of the stack equals currentEntityDef.  Pop it off the stack and
                // set currentEntityDef to the next item in the stack.
                currentEntityStack.pop();
                if ( currentEntityDef.getParent() != null ) // Is it root?
                    currentEntityDef = currentEntityStack.peek();
                return;
            }

            if ( StringUtils.equalsIgnoreCase( qName, "zOI" ) )
            {
                // TODO: Stop parsing.
                return;
            }
        } // endElement

        @Override
        public void characters( char[] ch, int start, int length ) throws SAXException
        {
            if ( characterBuffer == null )
                return;  // We don't need to capture the characters.

            characterBuffer.append( ch, start, length );
        }
    } // class SaxParserHandler

    @Override
    public List<View> readFromStream( DeserializeOi options )
    {
        this.task = options.getTask();
        control = options.getFlags();
        this.inputStream = options.getInputStream();;
        ignoreInvalidEntityNames = control.contains( ActivateFlags.fIGNORE_ENTITY_ERRORS );
        ignoreInvalidAttributeNames = control.contains( ActivateFlags.fIGNORE_ATTRIB_ERRORS );
        read();
        return Arrays.asList( (View) view );
    }
}
