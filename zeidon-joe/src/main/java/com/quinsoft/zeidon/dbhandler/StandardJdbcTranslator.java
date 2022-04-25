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

package com.quinsoft.zeidon.dbhandler;

import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.GeneratedKey;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.dbhandler.AbstractSqlHandler.SqlStatement;
import com.quinsoft.zeidon.domains.AbstractNumericDomain;
import com.quinsoft.zeidon.domains.BlobDomain;
import com.quinsoft.zeidon.domains.BooleanDomain;
import com.quinsoft.zeidon.domains.DateDomain;
import com.quinsoft.zeidon.domains.DateTimeDomain;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.domains.DomainDateTimeFormatter;
import com.quinsoft.zeidon.domains.GeneratedKeyDomain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.DataField;
import org.apache.commons.lang3.StringUtils;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

/**
 * The standard JDBC domain translator.
 */
public class StandardJdbcTranslator implements JdbcDomainTranslator
{
    protected static final int MAX_INLINE_STRING_LENGTH = 100;

    /**
     * Thread-safe formatter for converting to string.
     **/
    protected final DomainDateTimeFormatter dateFormatter;
    protected final DomainDateTimeFormatter dateTimeFormatter;

    private final Task        task;

    /**
     * If true, then we'll use '?' for all attribute values when building the SQL.
     * This protects a task from possible SQL injection.
     */
    final boolean bindAllValues;

    public StandardJdbcTranslator(Task task, JdbcHandler handler )
    {
        super();
        this.task = task;
        bindAllValues = handler.isBindAllValues();
        dateFormatter = handler.getDateFormatter();
        dateTimeFormatter = handler.getDateTimeFormatter();
    }

    protected Task getTask()
    {
        return task;
    }

    protected boolean appendString( SqlStatement stmt, StringBuilder buffer, AttributeDef attributeDef, Object value )
    {
        String str = value.toString();
        if ( str.length() > MAX_INLINE_STRING_LENGTH || bindAllValues)
        {
            if ( getTask().dblog().isTraceEnabled() )
                getTask().dblog().trace( "Bound string: length = %d, value = %s...", str.length(), StringUtils.substring( str, 0, 50 ) );

            DataField dataField = attributeDef.getEntityDef().getDataRecord().getDataField( attributeDef );
            stmt.addBoundAttribute( buffer, new BoundAttributeData( dataField, value ) );

            //stmt.addBoundAttribute( buffer, value );
        }
        else
        {
            buffer.append( "'" ).append( StringUtils.replace( str, "'", "''" ) ).append( "'" );
        }

        return true;
    }

    protected boolean appendNumeric(SqlStatement stmt, StringBuilder buffer, Object value )
    {
        if ( bindAllValues )
            stmt.addBoundAttribute( buffer, value );
        else
            buffer.append( value.toString() );

        return true;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.JdbcDomainTranslator#getAttributeValue(java.lang.StringBuilder, com.quinsoft.zeidon.objectdefinition.DataField, com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public boolean appendSqlValue(SqlStatement stmt, StringBuilder buffer, Domain domain, AttributeDef attributeDef, Object value)
    {
        if ( bindAllValues )
        {
            Object dbValue = domain.convertValueForDb( task, attributeDef, value );
            DataField dataField = attributeDef.getEntityDef().getDataRecord().getDataField( attributeDef );
            stmt.addBoundAttribute( buffer, new BoundAttributeData( dataField, dbValue ) );
            return true;
        }

        if ( value == null )
        {
            buffer.append( "null" );
            return true;
        }

        if ( domain instanceof GeneratedKeyDomain )
        {
            // The default for DBs is that the key is an integer so copy the value of the
            // attribute directly to the buffer (i.e. without quotes).
            buffer.append( ((GeneratedKey) value).getString() );
            return true;
        }

        if ( domain instanceof DateTimeDomain )
        {
            // Convert the value (likely a string) to a date.
            Object v = domain.convertExternalValue( task, null, attributeDef, null, value );
            String str = dateTimeFormatter.format( (ZonedDateTime) v );
            return appendString( stmt, buffer, attributeDef, str );
        }

        if ( domain instanceof DateDomain )
        {
            // Convert the value (likely a string) to a date.
            Object v = domain.convertExternalValue( task, null, attributeDef, null, value );
            String str = dateFormatter.format( (ZonedDateTime) v );
            return appendString( stmt, buffer, attributeDef, str );
        }

        if ( domain instanceof AbstractNumericDomain )
        {
            return appendNumeric( stmt, buffer, value );
        }

        if ( domain instanceof BooleanDomain )
        {
            Object b = domain.convertExternalValue( task, null, attributeDef, null, value );
            buffer.append( (Boolean) b ? "true" : "false" );
            return true;
        }

        if ( domain instanceof BlobDomain )
        {
            String s = domain.convertToString( task, attributeDef, value );
            return appendString( stmt, buffer, attributeDef, s );
        }

//        String string = domain.convertToString( task, attributeDef, value );
        return appendString( stmt, buffer, attributeDef, value.toString() );
    }

    /**
     * Takes a value loaded from the DB and potentially converts it.
     */
    @Override
    public Object convertDbValue(Domain domain, Object dbValue) throws SQLException
    {
        if ( dbValue instanceof Clob )
        {
            Clob clob = (Clob) dbValue;
            return clob.getSubString( 1L, (int) clob.length() );
        }

        if ( domain instanceof DateDomain )
        {
            if ( dbValue instanceof CharSequence )
            {
                String date = dbValue.toString();
                return dateTimeFormatter.parse( date );
            }
            else
            if ( dbValue instanceof java.sql.Date )
            {
                return ( (java.sql.Date) dbValue).toLocalDate().atStartOfDay( ZoneId.systemDefault() ) ;
            }
            else
            if ( dbValue instanceof java.util.Date )
            {
                return ZonedDateTime.ofInstant( ( (java.util.Date) dbValue).toInstant(),
                                                ZoneId.systemDefault() );
            }
        }

        if ( domain instanceof DateTimeDomain )
        {
            if ( dbValue instanceof CharSequence )
            {
                String date = dbValue.toString();
                ZonedDateTime dt = dateTimeFormatter.parse( date );
                return dt;
            }
        }

        return dbValue;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.JdbcDomainTranslator#bindAttributeValue(java.sql.PreparedStatement, com.quinsoft.zeidon.View, com.quinsoft.zeidon.objectdefinition.DataField, int)
     */
    @Override
    public String bindAttributeValue( PreparedStatement ps, View view, DataField dataField, int idx )
    {
        final AttributeDef attributeDef = dataField.getAttributeDef();
        Domain domain = attributeDef.getDomain();
        Object value;
        if ( domain instanceof BlobDomain )
        {
            value = view.cursor( attributeDef.getEntityDef() ).getAttribute( attributeDef ).getBlob();
            try
            {
                if ( value == null )
                {
                    ps.setObject( idx, value, Types.LONGVARBINARY, -1 );
                    return "<null>";
                }
            }
            catch ( SQLException e )
            {
                throw ZeidonException.wrapException( e ).prependAttributeDef( attributeDef );
            }
        }
        else
        {
        	value = view.cursor( attributeDef.getEntityDef() ).getAttribute( attributeDef ).getValue();
        	value = domain.convertValueForDb( getTask(), attributeDef, value );
        }

        value = AbstractSqlHandler.convertEmptyStringValue( value, attributeDef );

        try
        {
            return bindAttributeValue( ps, new BoundAttributeData( dataField, value ), idx );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependAttributeDef( attributeDef );
        }
    }

    @Override
    public String bindAttributeValue( PreparedStatement ps, BoundAttributeData data, int idx )
    {
        if ( data.value instanceof Collection )
            return bindCollectionValue( ps, data, idx );

        final AttributeDef attributeDef = data.dataField.getAttributeDef();
        Domain domain = attributeDef.getDomain();
        Object value = data.value;
        try
        {
            if ( domain instanceof BlobDomain )
            {
                if ( value == null )
                {
                    ps.setObject( idx, value, Types.LONGVARBINARY, -1 );
                    return "<null>";
                }
                Blob blob = (Blob) value;
                ps.setObject( idx, blob.getBytes() ); // If blob is varbinary
            }
            else if ( value instanceof ZonedDateTime )
            {
                ZonedDateTime d = (ZonedDateTime) value;
                java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf( d.toLocalDateTime() );
                ps.setTimestamp( idx, timestamp );
            }
            else if ( value instanceof GeneratedKey )
            {
                GeneratedKey k = (GeneratedKey) value;
                ps.setObject( idx, k.getNativeValue() );
            }
            else
            {
                ps.setObject( idx, value );
            }

            if ( value == null )
                return "<null>";

            return value.toString();
        }
        catch ( Exception e )
        {
            String className = value == null ? "<null>"
                    : value.getClass().getCanonicalName();
            throw ZeidonException.wrapException( e ).appendMessage( "Col index: %d\nValue %s\nClass: %s", idx,
                                                                    value, className );
        }
    }

    protected String getArrayType( DataField dataField, Collection<?> collection )
    {
        Object value = collection.iterator().next();

        if ( value instanceof Integer )
            return "int";

        if ( value instanceof UUID )
            return "UUID";

        return "text";
    }

    protected String bindCollectionValue( PreparedStatement ps, BoundAttributeData data, int idx )
    {
        Collection<?> collection = (Collection<?>) data.value;
        try
        {
            Object[] array = collection.toArray( new Object[ collection.size() ] );
            String arrayType = getArrayType( data.dataField, collection );
            ps.setArray( idx, ps.getConnection().createArrayOf( arrayType, array ) );
            return collection.toString();
        }
        catch ( SQLException e )
        {
            throw ZeidonException.wrapException( e );
        }
    }
}
