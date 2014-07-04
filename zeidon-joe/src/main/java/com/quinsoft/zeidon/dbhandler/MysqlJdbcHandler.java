/**
 *
 */
package com.quinsoft.zeidon.dbhandler;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.AbstractOptionsConfiguration;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * JDBC handler for mysql that uses "lock table" to lock the genkey table.
 *
 * @author dg
 *
 */
public class MysqlJdbcHandler extends JdbcHandler
{

    public MysqlJdbcHandler(Task task, AbstractOptionsConfiguration options )
    {
        super( task, options );
    }

    @Override
    protected void acquireGenkeyLock( View kzgkhwob, List<? extends View> viewList )
    {
        getTask().dblog().debug( "Attempting to acquire write lock on ZEIDONGENKEYTABLE" );
        executeSql( "LOCK TABLES ZEIDONGENKEYTABLE WRITE;" );
    }

    @Override
    protected void releaseGenkeyLock()
    {
        for ( int tries = 0; tries < 5; tries++ )
        {
            try
            {
                executeSql( "UNLOCK TABLES;" );
                return;
            }
            catch ( Exception e )
            {
                // Danger danger Will Robinson!  We couldn't delete the lock, which will
                // prevent anybody else from creating new entities.  Log a warning and try again.
                getTask().log().warn( "Exception attempting to execute UNLOCK TABLES: %s\n     %s",
                                      e.getMessage(), StringUtils.join( e.getStackTrace(), "\n     " ) );
            }

            try
            {
                Thread.sleep( tries * tries * 100 );
            }
            catch ( InterruptedException e2 )
            {
                // Ignore this error.  If we somehow get a crazy error here we still want
                // to try and unlock the tables.
            }
            getTask().log().warn( "Attempting to UNLOCK genkey tables again #%d.", tries );
        }

        // If we get here then we didn't acquire the lock.
        throw new GenkeyLockException("Unable to UNLOCK GENKEY lock.  See logs for possible explanation.");
    }

    @Override
    protected void addActivateLimit( ViewEntity viewEntity, SqlStatement stmt )
    {
        stmt.appendSuffix( "LIMIT " );
        stmt.appendSuffix( viewEntity.getActivateLimit() );
    }
}
