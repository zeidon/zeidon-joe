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

    Copyright 2009-2015 QuinSoft
 */
package com.quinsoft.zeidon.standardoe;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.ISODateTimeFormat;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Pagination;
import com.quinsoft.zeidon.SelectSet;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.StreamWriter;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.WriteOiFlags;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.BigDecimalDomain;
import com.quinsoft.zeidon.domains.BooleanDomain;
import com.quinsoft.zeidon.domains.DateTimeDomain;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.domains.DoubleDomain;
import com.quinsoft.zeidon.domains.IntegerDomain;
import com.quinsoft.zeidon.domains.LongDomain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * @author dgc
 *
 */
public class WriteOisToJsonStream implements StreamWriter
{
    private final static String VERSION = "1";

    private Collection<? extends View> viewList;
    private SerializeOi options;
    private EnumSet<WriteOiFlags> flags;
    private final Set<ObjectInstance> ois = new HashSet<ObjectInstance>();
    private JsonGenerator jg;
    private View currentView;
    private final LinkedHashMap<String, Object> linkedMap = new LinkedHashMap<>(5);

    @Override
    public void writeToStream( SerializeOi options, Writer writer )
    {
        this.viewList = options.getViewList();
        this.options = options;
        if ( options.getFlags() == null )
            flags = EnumSet.noneOf( WriteOiFlags.class );
        else
            flags = options.getFlags();
        if ( ! flags.contains( WriteOiFlags.INCREMENTAL ) )
            throw new ZeidonException( "This JSON stream writer intended for writing incremental." );

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
            if ( ! options.isCompressed() )
                jg.useDefaultPrettyPrinter(); // enable indentation just to make debug/testing easier

            jg.writeStartObject();

            // Write meta info for entire JSON object.
            jg.writeObjectFieldStart( ".meta" );
            jg.writeStringField( "version", VERSION );
            if ( options.isWriteDate() )
                jg.writeStringField( "datetime", new LocalDateTime().toString() );
            jg.writeEndObject();

            jg.writeArrayFieldStart( "OIs" );

            for ( View view : viewList )
            {
                currentView = view;
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
        SelectSet rootSelectSet = null;
        Map<Long, SelectSet> sets = options.getRootSelectSets();
        if ( sets != null )
            rootSelectSet = sets.get( view.getOiId() );

        writeOiMeta( view );

        EntityDef lastEntityDef = null;

        ViewImpl viewImpl = ((InternalView)view).getViewImpl();
        for ( EntityInstanceImpl ei = viewImpl.getObjectInstance().getRootEntityInstance();
              ei != null;
              ei = ei.getNextTwin() )
        {
            // If we have a root select set and the EI is not selected then skip it.
            if ( rootSelectSet != null && rootSelectSet.isSelected( ei ) )
                continue;

            lastEntityDef = writeEntity( ei, lastEntityDef );
        }

        if ( lastEntityDef != null )
            jg.writeEndArray();
    }

    private void writeOiMeta( View view ) throws Exception
    {
        ObjectInstance oi = ((InternalView) view).getViewImpl().getObjectInstance();
        jg.writeObjectFieldStart( ".oimeta" );
        jg.writeStringField( "application", view.getLodDef().getApplication().getName() );
        jg.writeStringField( "odName", view.getLodDef().getName() );
        jg.writeBooleanField( "incremental", true );
        if ( oi.isReadOnly() )
            jg.writeBooleanField( "readOnlyOi", true );
        else
            if ( view.isReadOnly() )
                jg.writeBooleanField( "readOnly", true );

        if ( oi.isLocked() )
            jg.writeBooleanField( "locked", true );

        Integer rootCount = view.getTotalRootCount();
        if ( rootCount != null )
            jg.writeNumberField( "totalRootCount", rootCount );

        writePagination( view );
        jg.writeEndObject();
    }

    private void writePagination( View view ) throws Exception
    {
        ActivateOptions options = view.getActivateOptions();
        Pagination paging = options.getPagingOptions();
        if ( paging == null )
            return;

        jg.writeObjectFieldStart( "pagination" );
        jg.writeNumberField( "pageSize", paging.getPageSize() );
        jg.writeNumberField( "currentPage", paging.getPageNumber() );

        Integer rootCount = view.getTotalRootCount();
        if ( rootCount != null )
        {
            jg.writeNumberField( "totalCount", rootCount );
            jg.writeNumberField( "totalPages", rootCount / paging.getPageSize() + 1 );
        }

        jg.writeEndObject();
    }

    private String camelCaseName( String name )
    {
        if ( ! options.isCamelCase() )
            return name;

        char[] nameChars = name.toCharArray();
        for ( int i = 0; i < nameChars.length; i++ )
        {
            if ( ! Character.isUpperCase( nameChars[ i ] ) )
                break;

            nameChars[ i ] = Character.toLowerCase( nameChars[ i ] );
        }

        return String.valueOf( nameChars );
    }

    private EntityDef writeEntity( EntityInstanceImpl ei, EntityDef lastEntityDef ) throws Exception
    {
        try
        {
            // See if we need to open or close an array field.
            final EntityDef entityDef = ei.getEntityDef();
            if ( lastEntityDef != entityDef )
            {
                if ( lastEntityDef != null )
                    jg.writeEndArray();

                lastEntityDef = entityDef;
                jg.writeArrayFieldStart( camelCaseName( entityDef.getName() ) );
            }

            jg.writeStartObject();
            boolean writePersistent = writeEntityMeta( ei );
            for ( AttributeDef attributeDef : entityDef.getAttributes() )
            {
                // If the attribute is not persistent and we're only writing persisten
                // then go to the next one.
                if ( attributeDef.isPersistent() && ! writePersistent )
                    continue;

                if ( attributeDef.isDerived() )
                    continue;

                AttributeInstanceImpl attrib = ei.getAttribute( attributeDef );
                if ( attrib.isNull() && ! attrib.isUpdated() )
                    continue;

                AttributeValue attribValue = ei.getInternalAttribute( attributeDef );

                // Check for integer, double, or boolean so that it gets written without quotes.
                // TODO: Do this more dynamically.
                Domain domain = attributeDef.getDomain();
                String jsonName = camelCaseName( attributeDef.getName() );
                if ( domain instanceof IntegerDomain )
                    jg.writeNumberField( jsonName, attrib.getInteger() );
                else
                if ( domain instanceof DoubleDomain )
                    jg.writeNumberField( jsonName, attrib.getDouble() );
                else
                if ( domain instanceof BooleanDomain )
                    jg.writeBooleanField( jsonName, attrib.getBoolean() );
                else
                if ( domain instanceof LongDomain )
                    jg.writeNumberField( jsonName, (Long) attrib.getValue() );
                else
                if ( domain instanceof BigDecimalDomain )
                    jg.writeNumberField( jsonName, (BigDecimal) attrib.getValue() );
                else
                if ( domain instanceof DateTimeDomain )
                    jg.writeStringField( jsonName, ISODateTimeFormat.dateTime().print( (DateTime) attrib.getValue() ) );
                else
                {
                    String value = attribValue.getString( ei.getTask(), attributeDef );
                    jg.writeStringField( jsonName, value );
                }

                if ( attributeDef.isPersistent() )
                    writeAttributeMeta( attribValue, attributeDef );
            }

            // Loop through the children and add them.
            EntityDef lastChildEntityDef = null;
            for ( EntityInstanceImpl child : ei.getDirectChildren( true, false ) )
            {
                lastChildEntityDef = writeEntity( child, lastChildEntityDef );
            }

            if ( lastChildEntityDef != null )
                jg.writeEndArray();

            jg.writeEndObject();

            return entityDef;
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependEntityInstance( ei );
        }
    }

    private void writeAttributeMeta( AttributeValue attrib, AttributeDef attributeDef )
                        throws JsonGenerationException, IOException
    {
        if ( ! attrib.isUpdated() )
            return;

        jg.writeObjectFieldStart( "." + camelCaseName( attributeDef.getName() ) );
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

        EntityDef entityDef = ei.getEntityDef();
        boolean selectedCursor = currentView.cursor( entityDef ).getEntityInstance() == ei;

        linkedMap.clear();

        String str = createIncrementalStr( ei );
        if ( ! StringUtils.isBlank( str ) )
            linkedMap.put( "incrementals", str );

        if ( selectedCursor && options.isWithCursors() )
            linkedMap.put( "selected", true );

        if ( ei.isIncomplete() )
            linkedMap.put( "incomplete", true );

        if ( ei.hasLoadedLazyChildren() )
        {
            StringBuilder sb = new StringBuilder();
            for ( EntityDef def : ei.getEntitiesLoadedLazily() )
                sb.append( "," ).append( def.getName() );
            sb.deleteCharAt( 0 );
            linkedMap.put( "lazyLoaded", sb.toString() );
        }

        if ( recordOwner != null )
        {
            if ( recordOwner == ei )
            {
                // TODO: validate that ei.entityDef has all the attributes in the shared
                // attribute hash.
                ei.setRecordOwner( true );
                linkedMap.put( "isLinkedSource", true );
                linkedMap.put( "entityKey", Long.toString( ei.getEntityKey() ) );
            }
            else
            {
                // Write the entity key of the record owner.
                linkedMap.put( "linkedSource", Long.toString( recordOwner.getEntityKey() ));
                writeAttributes = false;
            }
        }

        if ( linkedMap.size() == 0 )
            return writeAttributes;

        jg.writeObjectFieldStart( ".meta" );

        for ( String key : linkedMap.keySet() )
            jg.writeObjectField( key, linkedMap.get( key ) );

        jg.writeEndObject();

        return writeAttributes;
    }
}
