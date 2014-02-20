/**
 *
 */
package com.hfi.cheetah;

import java.util.List;

import com.quinsoft.zeidon.AbstractOptionsConfiguration;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.dbhandler.MysqlJdbcHandler;

/**
 * MySql dbhandler specifically for Cheetah app.  The purpose of this class is to:
 *
 * - Override standard MySql genkey locking and use the old style locking.
 *
 * @author dgc
 *
 */
public class CheetahMySqlDbHandler extends MysqlJdbcHandler
{
    public CheetahMySqlDbHandler(Task task, AbstractOptionsConfiguration options )
    {
        super( task, options );
    }

    @Override
    protected void acquireGenkeyLock( View kzgkhwob, List<? extends View> viewList )
    {
        writeGenkeyLockRow( kzgkhwob, viewList );
    }

    @Override
    protected void releaseGenkeyLock()
    {
        deleteGenkeyLockRow();
    }
}
