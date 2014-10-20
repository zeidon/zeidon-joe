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

import java.sql.SQLException;

import org.joda.time.DateTime;

import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.dbhandler.AbstractSqlHandler.SqlStatement;
import com.quinsoft.zeidon.domains.BooleanDomain;
import com.quinsoft.zeidon.domains.DateDomain;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * JDBC translator for Sqlite.
 *
 * @author DG
 *
 */
public class SqliteJdbcTranslator extends StandardJdbcTranslator
{

    /**
     * @param task
     * @param config
     */
    public SqliteJdbcTranslator(Task task, JdbcHandler handler )
    {
        super( task, handler );
    }

    @Override
    public Object convertDbValue(Domain domain, Object dbValue) throws SQLException
    {
        if ( domain instanceof DateDomain )
        {
            // Sqlite stores dates as unix times.
            if ( dbValue instanceof Number )
            {
                long time = ((Number) dbValue).longValue();

                return new DateTime( time );
            }
        }
        return super.convertDbValue( domain, dbValue );
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

        // Sqlite stores booleans as integers.
        if ( domain instanceof BooleanDomain )
        {
            Object b = domain.convertExternalValue( getTask(), null, attributeDef, null, value );
            buffer.append( (Boolean) b ? "1" : "0" );
            return true;
        }

        return super.appendSqlValue( stmt, buffer, domain, attributeDef, value );
    }

}
