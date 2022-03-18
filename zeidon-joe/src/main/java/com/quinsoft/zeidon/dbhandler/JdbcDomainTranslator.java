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

import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.dbhandler.AbstractSqlHandler.SqlStatement;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.DataField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * This interface is used by the JdbcHandler to convert attribute values to values that
 * can be stored in a JDBC database.
 */
public interface JdbcDomainTranslator
{
    /**
     * Either appends a string representation of the attribute value to buffer or
     * adds the value to the list of bound attributes.
     * @param stmt TODO
     * @param buffer
     * @param domain
     * @param attributeDef TODO
     * @param value
     * @return
     */
    boolean appendSqlValue(SqlStatement stmt, StringBuilder buffer, Domain domain, AttributeDef attributeDef, Object value);

    /**
     * Converts a value from the DB to something the domain can handle.
     * @param dbValue TODO
     * @return
     */
    Object convertDbValue( Domain domain, Object dbValue ) throws SQLException;

    String bindAttributeValue( PreparedStatement ps, View view, DataField dataField, int idx );
    String bindAttributeValue( PreparedStatement ps, BoundAttributeData value, int idx );
}
