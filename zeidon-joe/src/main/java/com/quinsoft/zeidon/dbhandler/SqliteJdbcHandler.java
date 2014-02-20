/**
 *
 */
package com.quinsoft.zeidon.dbhandler;

import com.quinsoft.zeidon.AbstractOptionsConfiguration;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

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

    @Override
    void addActivateLimit( ViewEntity viewEntity, SqlStatement stmt )
    {
        stmt.appendSuffix( "LIMIT " );
        stmt.appendSuffix( viewEntity.getActivateLimit() );
    }
}
