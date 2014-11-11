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

package com.quinsoft.zeidon.dbhandler;

import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.dbhandler.AbstractSqlHandler.SqlStatement;
import com.quinsoft.zeidon.domains.AbstractNumericDomain;
import com.quinsoft.zeidon.domains.BooleanDomain;
import com.quinsoft.zeidon.domains.DateDomain;
import com.quinsoft.zeidon.domains.DateTimeDomain;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.DataField;

/**
 * The standard JDBC domain translator.
 *
 * @author DG
 *
 */
public class StandardJdbcTranslator implements JdbcDomainTranslator
{
    protected static final int MAX_INLINE_STRING_LENGTH = 100;

    /**
     * Thread-safe formatter for converting to string.
     **/
    protected final DateTimeFormatter dateFormatter;
    protected final DateTimeFormatter dateTimeFormatter;

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

    protected boolean appendString( SqlStatement stmt, StringBuilder buffer, Object value )
    {
        String str = value.toString();
        if ( str.length() > MAX_INLINE_STRING_LENGTH || bindAllValues)
        {
            if ( getTask().dblog().isTraceEnabled() )
                getTask().dblog().trace( "Bound string: length = %d, value = %s...", str.length(), StringUtils.substring( str, 0, 50 ) );

            stmt.addBoundAttribute( buffer, value );
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
            stmt.addBoundAttribute( buffer, value );
            return true;
        }

        if ( value == null )
        {
            buffer.append( "null" );
            return true;
        }

        if ( domain instanceof DateTimeDomain )
        {
            // Convert the value (likely a string) to a date.
            Object v = domain.convertExternalValue( task, null, attributeDef, null, value );
            String str = dateTimeFormatter.print( (DateTime) v );
            return appendString( stmt, buffer, str );
        }

        if ( domain instanceof DateDomain )
        {
            // Convert the value (likely a string) to a date.
            Object v = domain.convertExternalValue( task, null, attributeDef, null, value );
            String str = dateFormatter.print( (DateTime) v );
            return appendString( stmt, buffer, str );
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

        String string = domain.convertToString( task, attributeDef, value );
        return appendString( stmt, buffer, string );
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

        if ( domain instanceof DateTimeDomain )
        {
            if ( dbValue instanceof CharSequence )
            {
                String date = dbValue.toString();
                try
                {
                    return dateTimeFormatter.parseDateTime( date );
                }
                catch ( IllegalArgumentException e )
                {
                    throw ZeidonException.prependMessage( e, "Invalid datetime format.  Got '%s' but expected format '%s'",
                                                          date, dateTimeFormatter );
                }
            }
        }

        if ( domain instanceof DateDomain )
        {
            if ( dbValue instanceof CharSequence )
            {
                String date = dbValue.toString();
                try
                {
                    return this.dateFormatter.parseDateTime( date );
                }
                catch ( IllegalArgumentException e )
                {
                    throw ZeidonException.prependMessage( e, "Invalid date format.  Got '%s' but expected format '%s'",
                                                          date, dateFormatter );
                }
            }
            else
            if ( dbValue instanceof Date )
            {
                return new DateTime( dbValue );
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
        final Object value = view.cursor( attributeDef.getEntityDef() ).getInternalAttributeValue( attributeDef );

        try
        {
            return bindAttributeValue( ps, value, idx );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependAttributeDef( attributeDef );
        }
    }

    @Override
    public String bindAttributeValue( PreparedStatement ps, Object value, int idx )
    {
        try
        {
            if ( value instanceof DateTime )
            {
                DateTime d = (DateTime) value;
                ps.setObject( idx, new Date( d.getMillis() ) );
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
            String className = value == null ? "<null>" : value.getClass().getCanonicalName();
            throw ZeidonException.wrapException( e ).appendMessage( "Col index: %d\nValue %s\nClass: %s", idx, value, className );
        }
    }
}
