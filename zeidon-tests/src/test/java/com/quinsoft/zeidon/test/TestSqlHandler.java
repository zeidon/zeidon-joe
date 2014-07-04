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

    Copyright 2009-2012 QuinSoft
 */

package com.quinsoft.zeidon.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.AbstractOptionsConfiguration;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.dbhandler.JdbcHandler;
import com.quinsoft.zeidon.dbhandler.StandardJdbcTranslator;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * Test dbhandler that just stores the generated SQL to a list so that the
 * SQL can be compared for differences.
 *
 * For activates, this will generate dummy data.
 *
 * @author DG
 *
 */
public class TestSqlHandler extends JdbcHandler
{
    private final List<String> sqlCommands = new ArrayList<String>();
    private final StandardJdbcTranslator translator;

    private int   currentGenkey = 0;

    /**
     * @param task
     * @param view
     * @param testSqlGeneration
     */
    public TestSqlHandler(Task task, AbstractOptionsConfiguration options )
    {
        super( task, options );
        translator = new StandardJdbcTranslator( task, this );

    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.AbstractSqlHandler#executeLoad(com.quinsoft.zeidon.objectdefinition.ViewEntity, com.quinsoft.zeidon.dbhandler.AbstractSqlHandler.SqlStatement)
     */
    @Override
    protected int executeLoad(View view, ViewEntity viewEntity, SqlStatement stmt)
    {
        String sql = StringUtils.remove(  stmt.toString(), '\r' );
        task.dblog().debug( "<SQL>\n%s\n</SQL>", sql );
        sqlCommands.add( sql );

//        DataRecord dataRecord = viewEntity.getDataRecord();
        EntityCursor csr = view.cursor( viewEntity );
        int max = 3;
        if ( viewEntity.getParent() != null )
            max = Math.min(  max, viewEntity.getMaxCardinality() );
        for ( int i = 0; i < max; i++ )
        {
            csr.createEntity( CreateEntityFlags.fIGNORE_PERMISSIONS );
            for ( ViewAttribute key : viewEntity.getAttributes() )
            {
                if ( key.isKey() || key.isForeignKey() )
                {
                    csr.setInternalAttributeValue( key, currentGenkey++, false );
                    continue;
                }
            }
        }

        return 0;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.AbstractSqlHandler#executeSql(java.lang.String)
     */
    @Override
    protected int executeSql(String sql)
    {
        sqlCommands.add( sql );
        return 0;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.AbstractSqlHandler#executeStatement(com.quinsoft.zeidon.objectdefinition.ViewEntity, com.quinsoft.zeidon.dbhandler.AbstractSqlHandler.SqlStatement)
     */
    @Override
    protected int executeStatement(View view, ViewEntity viewEntity, SqlStatement stmt)
    {
        String sql = stmt.toString();
        task.dblog().debug( "<SQL>\n%s\n</SQL>", sql );
        sqlCommands.add( sql );
        return 0;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.AbstractSqlHandler#getSqlValue(com.quinsoft.zeidon.domains.Domain, java.lang.StringBuilder, java.lang.Object)
     */
    @Override
    protected void getSqlValue(SqlStatement stmt, Domain domain, ViewAttribute viewAttribute, StringBuilder buffer, Object value)
    {
        if ( translator.appendSqlValue( stmt, buffer, domain, viewAttribute, value ) )
            return;

        throw new ZeidonException("JdbcDomainTranslator did not correctly translate an attribute value" );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.DbHandler#beginTransaction()
     */
    @Override
    public void beginTransaction()
    {
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.dbhandler.DbHandler#endTransaction(boolean)
     */
    @Override
    public void endTransaction(boolean commit)
    {
    }

    public List<String> getSqlCommands()
    {
        return sqlCommands;
    }
}
