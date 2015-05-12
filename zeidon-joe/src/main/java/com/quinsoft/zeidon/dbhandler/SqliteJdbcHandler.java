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

import com.quinsoft.zeidon.AbstractOptionsConfiguration;
import com.quinsoft.zeidon.Task;

/**
 * JDBC handler for mysql that uses "lock table" to lock the genkey table.
 *
 * @author dg
 *
 */
public class SqliteJdbcHandler extends JdbcHandler
{

    public SqliteJdbcHandler(Task task, AbstractOptionsConfiguration options )
    {
        super( task, options );
    }

    /**
     * Indicate that we always want to add the value of the generated key to the
     * insert statement.  This should result in a value of NULL.  Sqlite requires
     * genkeys (i.e. columns that are specified as INTEGER PRIMARY KEY) be set
     * to NULL for insert statements.
     */
    @Override
    protected boolean addGeneratedKeyForInsert()
    {
        return true;
    }

    /**
     * Default insert count to 1 if it isn't specified.
     */
    @Override
    public Integer getInsertCount()
    {
        Integer ic = super.getInsertCount();
        if ( ic == null || ic == 0 )
            return 1;

        return ic;
    }
}
