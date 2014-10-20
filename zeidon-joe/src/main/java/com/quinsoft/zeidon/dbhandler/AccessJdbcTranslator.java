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



import org.joda.time.DateTime;

import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.dbhandler.AbstractSqlHandler.SqlStatement;
import com.quinsoft.zeidon.domains.DateDomain;
import com.quinsoft.zeidon.domains.DateTimeDomain;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * JDBC translator for MS Access DBs.  This extends the standard JDBC translator
 * and overrides the few methods as needed.
 *
 * @author DG
 *
 */
public class AccessJdbcTranslator extends StandardJdbcTranslator
{
    /**
     * @param task
     * @param application TODO
     */
    public AccessJdbcTranslator(Task task, JdbcHandler handler )
    {
        super( task, handler );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.JdbcDomainTranslator#getAttributeValue(java.lang.StringBuilder, com.quinsoft.zeidon.objectdefinition.DataField, com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public boolean appendSqlValue(SqlStatement stmt, StringBuilder buffer, Domain domain, AttributeDef attributeDef, Object value)
    {
        // If we're binding all the values we'll let the parent class handle it.
        if ( bindAllValues )
            return super.appendSqlValue( stmt, buffer, domain, attributeDef, value );

        if ( value == null )
            return super.appendSqlValue( stmt, buffer, domain, attributeDef, null );

        /*
         * Access dates are denoted in JDBC as date strings surrounded by '#'.
         * E.g. #2001-04-30#
         */

        if ( domain instanceof DateTimeDomain )
        {
            // Convert the value (likely a string) to a date.
            Object v = domain.convertExternalValue( getTask(), null, attributeDef, null, value );
            String str = dateTimeFormatter.print( (DateTime) v );
            buffer.append( "#" ).append( str ).append( "#" );
            return true;
        }

        if ( domain instanceof DateDomain )
        {
            // Convert the value (likely a string) to a date.
            Object v = domain.convertExternalValue( getTask(), null, attributeDef, null, value );
            String str = dateFormatter.print( (DateTime) v );
            buffer.append( "#" ).append( str ).append( "#" );
            return true;
        }

        return super.appendSqlValue( stmt, buffer, domain, attributeDef, value );
    }
}