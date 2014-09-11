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

package com.quinsoft.zeidon.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.utils.QualificationBuilder;

/**
 * A test of the SQL generator.  The generated SQL is compared with previous runs
 * to make sure nothing has changed.
 *
 * @author DG
 *
 */
public class SqlGeneratorTest
{
    private final static String CORRECT_SQL_DIR = "src/test/resources/testdata/sqltest/correct/";
    private final static String TMP_SQL_DIR     = "target/test-classes/resources/testdata/sqltest/tmp/";
    private ObjectEngine oe;

    private final Map<String,Task> tasks = new CaseInsensitiveMap<String, Task>();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        oe = JavaObjectEngine.getInstance();
        tasks.put( "zencas", oe.createTask( "ZENCAs" ) );
        tasks.put( "epamms", oe.createTask( "ePamms" ) );
    }

    private void writeSql( String command, String filename ) throws IOException
    {
        FileUtils.writeStringToFile( new File( filename ), command );
    }

    private String readSql( String filename ) throws IOException
    {
        List<String> list = FileUtils.readLines( new File( filename ) );
        String str = StringUtils.join( list, "\n" );
        str = StringUtils.remove( str, "\r" );
        return str;
    }

    private void callDiff( String oldSql, String newSql ) throws IOException
    {
        if ( SystemUtils.IS_OS_WINDOWS )
            Runtime.getRuntime().exec("windiff.exe " + oldSql + " " + newSql );
        else
            Runtime.getRuntime().exec("windiff " + oldSql + " " + newSql );
    }

    /**
     * Run an activate an compare the generator results with SQL stored in a file.
     *
     * @param lodDefName
     * @param filename
     * @param qualFile - Load a qualfication object from this file.
     * @return
     * @throws IOException
     */
    private boolean runActivate( String lodDefName, String filename, String qualFile, String taskName ) throws IOException
    {
        // Load the qual object from a file if the filename is supplied.
        View qualView = null;
        if ( !StringUtils.isBlank( qualFile ) )
        {
            QualificationBuilder qual = null;
            qual = new QualificationBuilder( tasks.get( taskName ) )
                             .loadFile( CORRECT_SQL_DIR + qualFile + ".qual" );
            qualView = qual.getView();
        }

        return runActivate( lodDefName, filename, qualView, taskName );
    }

    /**
     * Run an activate an compare the generator results with SQL stored in a file.
     *
     * @param lodDefName
     * @param filename
     * @param qualView
     * @return
     * @throws IOException
     */
    private boolean runActivate( String lodDefName, String filename, View qualView, String taskName ) throws IOException
    {
        Task task = tasks.get( taskName );
        ActivateOptions options = new ActivateOptions( task );
        options.setLodDef( task, lodDefName );
        options.overrideConfigValue( "JdbcConfigGroupName", "TestSql" );
        options.overrideConfigValue( "BindAllValues", "N" );
        TestSqlHandler testHandler = new TestSqlHandler( task, options );
        options.setDbHandler( testHandler );

        // Do the activate.
        View v = tasks.get( taskName ).activateObjectInstance( lodDefName, qualView, options );
        return doCompare( testHandler, v, filename );
    }

    private boolean doCompare( TestSqlHandler testHandler, View v, String filename ) throws IOException
    {
        // Get the sql commands.
        List<String> commands = testHandler.getSqlCommands();
        String sql = StringUtils.join( commands, "\n" );
        sql = StringUtils.remove( sql, "\r" );

        String oldFilename = CORRECT_SQL_DIR + filename + ".sql";
        File old = new File( oldFilename );
        // If the old file doesn't exist we'll write it and finish.
        if ( ! old.exists() )
        {
            writeSql( sql, oldFilename );
            return true;
        }

        // Write the sql to a temp directory so we can compare it if necessary.
        String newFilename = TMP_SQL_DIR + filename + ".sql";
        writeSql( sql, newFilename );

        // Load the old version of the sql so we can compare it to the new one.
        String oldSql = readSql( oldFilename );

        String diff = StringUtils.difference( oldSql, sql );
        boolean equals = StringUtils.isBlank( diff );
        if ( !equals )
            callDiff( oldFilename, newFilename );

        return equals;
    }

    private boolean runCommit( String lodDefName, String filename, String taskName ) throws IOException
    {
        Task task = tasks.get( taskName );

        // Load the OI from a file.
        String fullFilename = CORRECT_SQL_DIR + filename + ".oi";
        View view = task.activateOiFromFile( lodDefName, fullFilename, ActivateFlags.MULTIPLE );

        CommitOptions options = new CommitOptions( task );
        options.addView( view );
        options.overrideConfigValue( "JdbcConfigGroupName", "TestSql" );
        options.overrideConfigValue( "BindAllValues", "N" );
        TestSqlHandler testHandler = new TestSqlHandler( task, options );
        options.setDbHandler( testHandler );
        view.commit( options );

        return doCompare( testHandler, view, filename );
    }

    @Test
    public void testCommitEmploy() throws IOException
    {
//        assertTrue( runCommit( "mEmploy", "mEmploy", "zencas" ) );
    }

    @Test
    public void testDeleteEmploy() throws IOException
    {
//        assertTrue( runCommit( "mEmploy", "mEmploy-delete", "zencas" ) );
    }

    @Test
    public void testCommit1() throws IOException
    {
        assertTrue( runCommit( "mMasLC", "mMasLC", "epamms" ) );
    }

    @Test
    public void testActivateAdmDiv() throws IOException
    {
        assertTrue( runActivate( "mAdmDiv", "mAdmDiv", "mAdmDiv", "zencas" ) );
    }

    @Test
    public void testActivateWithEntityList() throws IOException
    {
        View mAdmDiv = tasks.get( "zencas" ).activateEmptyObjectInstance( "mAdmDiv" );
        for ( int i = 10; i < 13; i++ )
        {
            mAdmDiv.cursor( "AdministrativeDivision" ).createEntity( CreateEntityFlags.fIGNORE_PERMISSIONS )
                                                      .setAttribute( "ID", i );
        }

        View qual = QualificationBuilder.generateQualFromEntityList( mAdmDiv, "AdministrativeDivision", null );
        assertTrue( runActivate( "mAdmDiv", "mAdmDiv-KeyList", qual, "zencas" ) );
    }

    @Test
    public void testActivateWithRestricting() throws IOException
    {
//        assertTrue( runActivate( "mEmploy", "mEmploy-child", "mEmploy-child", "zencas" ) );
    }
}
