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

import java.util.EnumSet;

import com.quinsoft.zeidon.AbstractOptionsConfiguration;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * @author dgc
 *
 */
public class TestSqlHandler extends JdbcHandler
{
    private static final EnumSet<CreateEntityFlags> CREATE_FLAGS = EnumSet.of( CreateEntityFlags.fNO_SPAWNING,
                                                                               CreateEntityFlags.fIGNORE_MAX_CARDINALITY,
                                                                               CreateEntityFlags.fDONT_UPDATE_OI,
                                                                               CreateEntityFlags.fDONT_INITIALIZE_ATTRIBUTES,
                                                                               CreateEntityFlags.fDBHANDLER,
                                                                               CreateEntityFlags.fIGNORE_PERMISSIONS );

    // We'll start keys at a number that stands out.
    private int generatedKey = 99_999_000;

    // We'll start keys at a number that stands out.
    private int generatedFKey = 77_777_000;

    /**
     * @param task
     * @param options
     */
    public TestSqlHandler( Task task, AbstractOptionsConfiguration options )
    {
        super( task, options );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.DbHandler#beginTransaction()
     */
    @Override
    public boolean beginTransaction()
    {
        getTask().dblog().debug( "TestSql: Begin Transaction" );
        return true;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.DbHandler#endTransaction(boolean)
     */
    @Override
    public void endTransaction( boolean commit )
    {
        getTask().dblog().debug( "TestSql: End Transaction" );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.AbstractSqlHandler#executeLoad(com.quinsoft.zeidon.View, com.quinsoft.zeidon.objectdefinition.EntityDef, com.quinsoft.zeidon.dbhandler.AbstractSqlHandler.SqlStatement)
     */
    @Override
    protected int executeLoad( View view, EntityDef entityDef, SqlStatement stmt )
    {
        logSql( stmt );
        EntityCursor cursor = view.cursor( entityDef );
        cursor.createEntity( CREATE_FLAGS );

        for ( AttributeDef attributeDef : entityDef.getAttributes() )
        {
            if ( attributeDef.isKey() )
            {
                cursor.getAttribute( attributeDef ).setInternalValue( generatedKey++, false );
                continue;
            }

            if ( attributeDef.isForeignKey() )
            {
                cursor.getAttribute( attributeDef ).setInternalValue( generatedFKey++, false );
                continue;
            }

            Domain domain = attributeDef.getDomain();
            Object value = domain.generateRandomTestValue( getTask(), attributeDef, cursor.getEntityInstance() );
            cursor.getAttribute( attributeDef ).setInternalValue( value, false );
        }

        return 0;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.AbstractSqlHandler#executeStatement(com.quinsoft.zeidon.View, com.quinsoft.zeidon.objectdefinition.EntityDef, com.quinsoft.zeidon.dbhandler.AbstractSqlHandler.SqlStatement)
     */
    @Override
    protected int executeStatement( View view, EntityDef entityDef, SqlStatement stmt )
    {
        logSql( stmt );
        return 0;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.AbstractSqlHandler#addActivateLimit(int, com.quinsoft.zeidon.dbhandler.AbstractSqlHandler.SqlStatement)
     */
    @Override
    protected void addActivateLimit( int limit, SqlStatement stmt )
    {
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.AbstractSqlHandler#executeSql(java.lang.String)
     */
    @Override
    protected int executeSql( String sql )
    {
        logSql( sql );
        return 0;
    }
}
