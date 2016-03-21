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

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.AbstractOptionsConfiguration;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LockingLevel;

/**
 * JDBC handler for mysql that uses "lock table" to lock the genkey table.
 *
 * @author dg
 *
 */
public class MysqlJdbcHandler extends JdbcHandler
{
    /**
     * This is true if we're locking the root and we're using a single transaction.
     */
    private boolean lockingRoot = false;

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
    protected boolean isJoinable( EntityDef entityDef )
    {
        // If we're locking the root entity in a single transaction then we don't want to
        // join any children because the locking mechanism will lock all the joined tables.
        // TODO: Leave this out for now.  What's worse, locking all the joined tables or
        // skipping the joins all together?
//        if ( lockingRoot && ! activateOptions.isReadOnly() )
//        {
//            // We are going to use
//            if ( entityDef.getParent() == entityDef.getLodDef().getRoot() )
//            {
//                getTask().dblog().trace( "Entity %s is not joined with its parent because the parent is loaded with record-level locking", entityDef );
//                return false;
//            }
//        }

        return super.isJoinable( entityDef );
    }

    @Override
    public PessimisticLockingHandler getPessimisticLockingHandler( ActivateOptions activateOptions , View view  )
    {
        // If we're doing an activate in a single transaction we'll use record-level locking
        // as part of the transaction which is part of the select and doesn't need a separate
        // locking handler.
        if ( activateOptions.isSingleTransaction() )
        {
            lockingRoot  = true;
            return AbstractSqlHandler.NOOP_PESSIMISTIC_LOCKING_HANDLER;
        }

        return super.getPessimisticLockingHandler( activateOptions, view );
    }

    @Override
    protected int executeLoad(View view, EntityDef entityDef, SqlStatement stmt)
    {
        if ( entityDef.getParent() == null )
        {
            LockingLevel lockLevel = activateOptions.getLockingLevel();
            if ( lockLevel.isPessimisticLock() && activateOptions.isSingleTransaction() )
            {
                if ( lockLevel == LockingLevel.PESSIMISTIC_NOREAD || ! activateOptions.isReadOnly() )
                {
                    // Add "FOR UPDATE" to lock the root records.
                    stmt.appendSuffix( " FOR UPDATE " );
                }
            }
        }

        return super.executeLoad( view, entityDef, stmt );
    }

    @Override
    protected void initializeTransaction( )
    {
        // If the user has indicated she's doing multiple activates in a single transaction
        // then we'll assume she wants locking.  Set the isolation level for locking.
        if ( options.isSingleTransaction() )
        {
            // Using executeSql() could be dangerous because it results in a recursive
            // call to getTransaction().  As currently constructed, however, it works.
            executeSql( "SET TRANSACTION ISOLATION LEVEL REPEATABLE READ" );
        }
    }
}
