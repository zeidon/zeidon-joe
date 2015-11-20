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

import java.sql.Connection;

import com.quinsoft.zeidon.DropTaskCleanup;
import com.quinsoft.zeidon.DropViewCleanup;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;

/**
 * Keeps track of a JDBC transaction/connection.
 *
 */
public class JdbcTransaction implements DropTaskCleanup, DropViewCleanup
{
    private final Connection connection;
    private boolean closed = false;

    /**
     *
     */
    public JdbcTransaction( Connection connection )
    {
        this.connection = connection;
    }

    public Connection getConnection()
    {
        return connection;
    }

    void close() throws Exception
    {
        if ( closed )
            throw new ZeidonException( "JDBC transaction already closed" );

        connection.close();
        closed = true;
    }

    boolean isClosed()
    {
        return closed;
    }

    /**
     * This is called when a task is dropped and will close the connection
     * if it hasn't been already.
     */
    @Override
    public void taskDropped( Task task )
    {
        if ( closed )
            return;

        try
        {
            task.log().warn( "Task has open JDBC connection.  Commit/rollback may not have been called.  Closing now." );
            close();
        }
        catch ( Exception e )
        {
            ZeidonException.wrapException( e );
        }
    }

    @Override
    public void viewDropped( View view )
    {
        if ( closed )
            return;
        
        try
        {
            view.dblog().debug( "Closing JDBC transaction" );
            close();
        }
        catch ( Exception e )
        {
            ZeidonException.wrapException( e );
        }
    }
}
