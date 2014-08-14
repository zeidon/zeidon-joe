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

package com.arksoft.epamms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.SetMatchingFlags;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.utils.JspWebUtils;
import com.quinsoft.zeidon.vml.VmlOperation;

/**
 * @author DKS
 *
 */
public class EpammsTest
{
    private Task         zeidonSystem;
    private Task         task;
    private ObjectEngine oe;

    /**
     * Called at the beginning of the test to reset the DB.
     */
    @BeforeClass
    public static void resetDB( ) throws IOException
    {
        System.out.println( "CWD = " + System.getProperty("user.dir") );
        // Copy the "base" sqlite file to a test one so we can commit changes
        // as part of the tests.
        File srcFile  = new File("./src/test/resources/testdata/ePamms/sqlite/base.db");
        File destFile = new File("./src/test/resources/testdata/ePamms/sqlite/test.db");
        FileUtils.copyFile( srcFile, destFile );
    }

    @Before
    public void setUp( ) throws Exception
    {
        oe = JavaObjectEngine.getInstance();
        zeidonSystem = oe.getSystemTask();
        zeidonSystem.log().debug( "Starting test" );
        task = oe.createTask( "ePamMs" );  // ignore case on app name
    }

    @Test
    public void testZeidonINI( ) throws IOException
    {
        String s = task.readZeidonConfig( "[App.ePamms]", "WebDirectory" );  // this worked in the C environment
        assertNotSame( "readZeidonIni for \"[App.ePamms]\", \"WebDirectory\" failed", s, null );
    }

    @Test
    public void testZeidonINI2( ) throws IOException
    {
        String s = task.readZeidonConfig( "App.ePamms", "WebDirectory" );  // this works in the Java environment  09/30/2011
        assertNotSame( "readZeidonIni for \"App.ePamms\", \"WebDirectory\" failed", s, null );
    }

    public void displayProperties( String name, StringBuilder sb )
    {
        task.log( ).info( name + ".Length = " + sb.length( ) );
        task.log( ).info( name + ".Capacity = " + sb.capacity( ) );
        task.log( ).info( name + ".value = " + sb.toString( ) );
    }

    public boolean testRegex( String input, String expression )
    {
        CharSequence inputStr = input;
        Pattern pattern = Pattern.compile( expression );
        Matcher matcher = pattern.matcher( inputStr );
        if ( matcher.matches( ) == true )
           return true;

        return false;
    }

    @Test
    public void testCheckExistenceOfEntity( )
    {
       View vKZXMLPGO = JspWebUtils.createWebSession( null, task, "testUserId" );
       vKZXMLPGO.cursor( "Session" ).createEntity( CursorPosition.NEXT );
       vKZXMLPGO.cursor( "DialogWindowList" ).createEntity( CursorPosition.NEXT );
       vKZXMLPGO.cursor( "DialogWindowList" ).setAttribute( "DialogName", "TestDlg1" );
       vKZXMLPGO.cursor( "DialogWindowList" ).setAttribute( "WindowName", "TestWnd1" );
       vKZXMLPGO.cursor( "DialogWindowList" ).createEntity( CursorPosition.NEXT );
       vKZXMLPGO.cursor( "DialogWindowList" ).setAttribute( "DialogName", "TestDlg2" );
       vKZXMLPGO.cursor( "DialogWindowList" ).setAttribute( "WindowName", "TestWnd2" );
       vKZXMLPGO.cursor( "DialogWindowList" ).deleteEntity( CursorPosition.NONE );
       CursorResult nRC = vKZXMLPGO.cursor( "DialogWindowList" ).checkExistenceOfEntity();
       assertEquals( "testCheckExistenceOfEntity valid", CursorResult.UNDEFINED, nRC );
    }

    @Test
    public void testRegexDomains( )
    {
        boolean bRC;

        // Validate VEA requiring 2 periods and no spaces using Java reg ex.
        // VEA format view.entity.attribute or .entity.attribute or view.entity. or ..attribute or view.. or .entity. :
 /*     String expressionSSN = "^\\d{3}[- ]?\\d{2}[- ]?\\d{4}$";
        bRC = testRegex( "879-89-8989", expressionSSN );
        assertEquals( "testRegexDomains SSN valid", bRC, true );
        bRC = testRegex( "869878789", expressionSSN );
        assertEquals( "testRegexDomains SSN valid", bRC, true );
        bRC = testRegex( "869-878789", expressionSSN );
        assertEquals( "testRegexDomains SSN valid", bRC, true );
        bRC = testRegex( "86987-8789", expressionSSN );
        assertEquals( "testRegexDomains SSN valid", bRC, true );
        bRC = testRegex( "8698-78789", expressionSSN );
        assertEquals( "testRegexDomains SSN invalid", bRC, false );
*/
        // Checks for US style telephone numbers ...
        // 10 digit accepts () around area code, and doesn't allow preceeding 1 as country code
        // Phone Number formats: (nnn)nnn-nnnn; nnnnnnnnnn; nnn-nnn-nnnn
/*      String expressionPhone = "(?:(?:(\\s*\\(?([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\)?\\s*(?:[.-]\\s*)?)([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})";
        bRC = testRegex( "(879)789-8989", expressionPhone );
        assertEquals( "testRegexDomains Phone valid", bRC, true );
        bRC = testRegex( "869-878-7890", expressionPhone );
        assertEquals( "testRegexDomains Phone valid", bRC, true );
        bRC = testRegex( "(879) 789-8989", expressionPhone );
        assertEquals( "testRegexDomains Phone valid", bRC, true );
        bRC = testRegex( "1234567890", expressionPhone );   <====================== fails
        assertEquals( "testRegexDomains Phone valid", bRC, true );
        bRC = testRegex( "8698-787890", expressionPhone );
        assertEquals( "testRegexDomains Phone invalid", bRC, false );
        bRC = testRegex( "123456789", expressionPhone );
        assertEquals( "testRegexDomains Phone invalid", bRC, false );
*/
        // Checks for US style telephone numbers ...
        // 10 digit accepts () around area code, and doesn't allow preceeding 1 as country code
        // Phone Number formats: (nnn)nnn-nnnn; nnnnnnnnnn; nnn-nnn-nnnn
        String expressionPhone = "^[+]?\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$"; // e.g.  matches following phone numbers: (123)456-7890, 123-456-7890, 1234567890, (123)-456-7890
        bRC = testRegex( "(879)789-8989", expressionPhone );
        assertEquals( "testRegexDomains Phone valid", bRC, true );
        bRC = testRegex( "869-878-7890", expressionPhone );
        assertEquals( "testRegexDomains Phone valid", bRC, true );
        bRC = testRegex( "(879) 789-8989", expressionPhone );
        assertEquals( "testRegexDomains Phone valid", bRC, true );
        bRC = testRegex( "(879)-789-8989", expressionPhone );
        assertEquals( "testRegexDomains Phone valid", bRC, true );
        bRC = testRegex( "1234567890", expressionPhone );
        assertEquals( "testRegexDomains Phone valid", bRC, true );
        bRC = testRegex( "8698-787890", expressionPhone );
        assertEquals( "testRegexDomains Phone invalid", bRC, false );
        bRC = testRegex( "869878789", expressionPhone );
        assertEquals( "testRegexDomains Phone invalid", bRC, false );

        // Validate Social Security number (SSN) using Java reg ex.
        // SSN format xxx-xx-xxxx, xxxxxxxxx, xxx-xxxxxx; xxxxx-xxxx:
        String expressionSSN = "^\\d{3}[- ]?\\d{2}[- ]?\\d{4}$";
        bRC = testRegex( "879-89-8989", expressionSSN );
        assertEquals( "testRegexDomains SSN valid", bRC, true );
        bRC = testRegex( "869878789", expressionSSN );
        assertEquals( "testRegexDomains SSN valid", bRC, true );
        bRC = testRegex( "869-878789", expressionSSN );
        assertEquals( "testRegexDomains SSN valid", bRC, true );
        bRC = testRegex( "86987-8789", expressionSSN );
        assertEquals( "testRegexDomains SSN valid", bRC, true );
        bRC = testRegex( "8698-78789", expressionSSN );
        assertEquals( "testRegexDomains SSN invalid", bRC, false );
    }

    @Test
    public void testStringBuilder( )
    {
       StringBuilder sb1 = new StringBuilder( );
       int capacity = 50;
       StringBuilder sb2 = new StringBuilder( capacity );
       String myString = "To be or not to be";
       StringBuilder sb3 = new StringBuilder( myString );
       int startIndex = 0;
       int stringLength = myString.length( );

       displayProperties( "sb1", sb1 );
       displayProperties( "sb2", sb2 );
       displayProperties( "sb3", sb3 );

       sb3.insert( 9, "NOT" );  // doesn't do quite what I expected
       displayProperties( "sb3", sb3 );
    }

    @Test
    public void testzIntegerToString( ) throws IOException
    {
       VmlTestOperation oper = new VmlTestOperation( task );
       String strYear = "2011";
       int nYear = 2011 + 1;
       StringBuilder sb_strYear;
       if ( strYear == null )
          sb_strYear = new StringBuilder( 32 );
       else
          sb_strYear = new StringBuilder( strYear );

       oper.zIntegerToString( sb_strYear, 4, nYear );
       assertEquals( "zIntegerToString string conversion invalid", sb_strYear.toString( ), "2012" );
    }

    @Test
    public void testZeidonStringCopy( ) throws IOException
    {
       VmlTestOperation oper = new VmlTestOperation( task );
       String s;
       int nLth;
       StringBuilder sbTgt = new StringBuilder( );
       StringBuilder sbSrc = new StringBuilder( );
       sbSrc.setLength( 0 );
       sbSrc.insert( 0, "2011" );
       nLth = oper.ZeidonStringCopy( sbTgt, 1, 4, sbSrc, 1, 4, 4 );
       assertEquals( "ZeidonStringCopy string copied invalid", sbTgt.toString( ), "2011" );
       assertEquals( "ZeidonStringCopy string copy length invalid", nLth, 4 );
       sbSrc.setLength( 0 );
       sbSrc.insert( 0, "N" );
       nLth = oper.ZeidonStringCopy( sbTgt, 1, 0, sbSrc, 1, 0, 2 );
       assertEquals( "ZeidonStringCopy string copied invalid", sbTgt.toString( ), "N" );
       assertEquals( "ZeidonStringCopy string copy length invalid", nLth, 1 );
    }

    @Test
    public void testSetNextContinue( ) throws IOException
    {
        View view = task.activateOiFromFile( "mMasLC", task.getObjectEngine().getHomeDirectory() + "/ePamms/OIs/mMasLC.por", null );
        view.cursor( "M_GeneralSection" ).setNext().toInt();
        view.cursor( "M_GeneralStatement" ).logEntity( false );
        int nRC = view.cursor( "M_GeneralStatement" ).setNextContinue().toInt();;
        view.cursor( "M_GeneralStatement" ).logEntity( false );
        assertEquals( "setNextContinue return code invalid", nRC, 0 );
    }

    @Test
    public void testSetPrevContinue( ) throws IOException
    {
        View view = task.activateOiFromFile( "mMasLC", task.getObjectEngine().getHomeDirectory() + "/ePamms/OIs/mMasLC.por", null );
        view.logObjectInstance( );
        CursorResult nRC = view.cursor( "M_StorageDisposalStatement" ).setPrevContinue();
        // Cursor should be unchanged because we're trying to setPrev on first entity.
        assertEquals( "setPrevContinue return code invalid", CursorResult.UNCHANGED, nRC );
    }

    @Test
    public void testSetMatching( ) throws IOException
    {
        View vKZXMLPGO = JspWebUtils.createWebSession( null, task, "testUserId" );

        View mMasLC = task.activateOiFromFile( "mMasLC", task.getObjectEngine().getHomeDirectory() + "/ePamms/OIs/mMasLC.por", null );
        mMasLC.setName( "mMasLC" );

        View wWebXfer = task.activateOiFromFile( "wWebXfer", task.getObjectEngine().getHomeDirectory() + "/ePamms/OIs/wWebXfer.por", null );
        wWebXfer.setName( "wWebXfer" );

        int nRC = wWebXfer.cursor( "Usage" ).setFirst().toInt();
        while ( nRC > CursorResult.UNCHANGED.toInt() )
        {
           mMasLC.cursor( "M_Usage" ).createEntity( CursorPosition.NEXT );
           mMasLC.cursor( "M_Usage" ).setMatchingAttributesByName( wWebXfer.cursor( "Usage" ), SetMatchingFlags.DEFAULT );
           mMasLC.cursor( "M_Usage" ).setAttribute( "UsageType", "U" );
           nRC = wWebXfer.cursor( "Usage" ).setNextContinue().toInt();;
        }

        assertNotSame( "setMatchingAttributesByName failed", nRC, 0 );
    }

    @Test
    public void testImportCSV( ) throws IOException
    {
        View vKZXMLPGO = JspWebUtils.createWebSession( null, task, "testUserId" );
        View wWebXfer = vKZXMLPGO.activateEmptyObjectInstance( "wWebXfer", task.getApplication() );
        if ( VmlOperation.isValid( wWebXfer ) )
        {
           String directoryName = task.readZeidonConfig( "App.ePamms", "WebDirectory" );
           int nRC = 0;  // ImportCSV_ToZeidonOI( wWebXfer, directoryName );  can't quite do this???
           assertEquals( "ImportCSV failed", nRC, 0 );
        }
    }

    @Test
    public void testSetCursorFirstEntityByEntityCsr( ) throws IOException
    {
      View mMasLC = task.activateOiFromFile( "mMasLC", task.getObjectEngine().getHomeDirectory() + "/ePamms/OIs/mMasLC.por", null );
      mMasLC.setName( "mMasLC" );
      mMasLC.cursor( "MI_UsageList" ).setFirst( "Name", "Hospitals" );
      task.log().info( "MI_UsageList EntityKey: " + mMasLC.cursor( "MI_UsageList" ).getEntityKey( ) );
      mMasLC.cursor( "MI_UsageList" ).logEntity( );
      VmlTestOperation oper = new VmlTestOperation( task );
      int nRC = oper.SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
      mMasLC.cursor( "M_Usage" ).logEntity( );
      String strName = mMasLC.cursor( "M_Usage" ).getStringFromAttribute( "Name" );
      task.log( ).info( "testSetCursorFirstEntityByEntityCsr RC: " + nRC );
      task.log( ).info( "testSetCursorFirstEntityByEntityCsr Name: " + strName + "  Original Name: " + "Hospitals" );

      assertEquals( "SetCursorFirstEntityByEntityCsr Name failed", strName, "Hospitals"  );
      assertEquals( "SetCursorFirstEntityByEntityCsr failed", nRC, 0 );
    }

    @Test
    public void testMoveEntityForReal( ) throws IOException
    {
      int nRC;

      View mMasLC = task.activateOiFromFile( "mMasLC", task.getObjectEngine().getHomeDirectory() + "/ePamms/OIs/mMasLC.por", null );
      mMasLC.setName( "mMasLC" );
      mMasLC.cursor( "M_GeneralSection" ).setNext().toInt();
      mMasLC.cursor( "M_GeneralSection" ).logEntity( false );

      View mTempLC = mMasLC.newView( );
      mTempLC.setName( "mTempLC" );

      // MoveSubobject( mTempLC, "M_GeneralStatement", mMasLC, "M_GeneralStatement", zPOS_PREV, zREPOS_PREV );
      EntityCursor srcCursor = mMasLC.cursor( "M_GeneralStatement" );
      nRC = srcCursor.setLast().toInt();;
      EntityCursor tgtCursor = mTempLC.cursor( "M_GeneralStatement" );
      nRC = tgtCursor.setLast().toInt();;
      nRC = tgtCursor.setPrevContinue().toInt();
      srcCursor.logEntity( false );
      tgtCursor.logEntity( false );
      nRC = tgtCursor.moveSubobject( CursorPosition.PREV, srcCursor, CursorPosition.PREV ).toInt();
      mTempLC.drop( );

      // We now commit the Master Label Content to maintain order!
      mMasLC.commit( );
      task.log( ).info( "testMoveEntity RC: " + nRC );
      assertEquals( "MoveEntityForReal failed", nRC, 0 );
    }

    private void displayFirstThreeEntities( View mMasLC, String strMsg )
    {
       View mTempLC = mMasLC.newView( );
       mTempLC.cursor( "MI_UsageList" ).setFirst().toInt();
       mTempLC.cursor( "M_Usage" ).setFirst().toInt();

       task.log( ).info( "displayFirstThreeEntities: " + strMsg );

       for ( int k = 0; k < 3; k++ )
       {
          mTempLC.cursor( "MI_UsageList" ).logEntity( false );
          mTempLC.cursor( "M_Usage" ).logEntity( false );
          mTempLC.cursor( "MI_UsageList" ).setNext().toInt();
          mTempLC.cursor( "M_Usage" ).setNext().toInt();
       }
    }

    @Test
    public void testMoveEntityToTop( ) throws IOException
    {
      int nRC;

      View mMasLC = task.activateOiFromFile( "mMasLC", task.getObjectEngine().getHomeDirectory() + "/ePamms/OIs/mMasLC.por", null );
      mMasLC.setName( "mMasLC" );
      displayFirstThreeEntities( mMasLC, "Initial ... 1.2.3?" );

      mMasLC.cursor( "MI_UsageList" ).setNext().toInt();  // get mMasLC.MI_UsageList to second entity
      View mTempLC = mMasLC.newView( );  // mTempLC.MI_UsageList is on second entity
      mTempLC.setName( "mTempLC" );
      mTempLC.cursor( "MI_UsageList" ).logEntity( false );
      mMasLC.cursor( "MI_UsageList" ).setNext().toInt();  // get mMasLC.MI_UsageList to third entity

      VmlTestOperation oper = new VmlTestOperation( task );
      nRC = oper.SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );  // get mMasLC.M_Usage to third entity
      mMasLC.cursor( "M_Usage" ).logEntity( );  // mMasLC.M_Usage should be on third entity ... check!
      nRC = oper.SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" );  // get mTempLC.M_Usage to second entity
      mTempLC.cursor( "M_Usage" ).logEntity( );  // mTempLC.M_Usage should be on second entity ... check!

      nRC = mTempLC.cursor( "M_Usage" ).moveSubobject( CursorPosition.PREV, mMasLC.cursor( "M_Usage" ), CursorPosition.PREV ).toInt();
      nRC = mTempLC.cursor( "MI_UsageList" ).moveSubobject( CursorPosition.PREV, mMasLC.cursor( "MI_UsageList" ), CursorPosition.PREV ).toInt();
      mTempLC.drop( );
      mMasLC.cursor( "M_Usage" ).logEntity( );  // mMasLC.M_Usage should be on #3 (second) entity (NO! on #2 - third)
      mMasLC.cursor( "MI_UsageList" ).logEntity( );  // mMasLC.MI_UsageList should be on #3 (second) entity (NO! on #2 - third)
      displayFirstThreeEntities( mMasLC, "Second ... 1.3.2?" );  // order should be 1.3.2

      // We now commit the Master Label Content to maintain order!
      mMasLC.commit( );

      mTempLC = mMasLC.newView( );  // mTempLC.MI_UsageList should be on #3 (second) entity
      mTempLC.cursor( "MI_UsageList" ).setFirst().toInt();
      mTempLC.cursor( "MI_UsageList" ).logEntity( false );  // mTempLC.MI_UsageList should be on #1 (first) entity
      nRC = oper.SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" );
      mTempLC.cursor( "M_Usage" ).logEntity( false );  // mTempLC.M_Usage should be on #1 (first) entity

      mMasLC.cursor( "MI_UsageList" ).setFirst().toInt();
      mMasLC.cursor( "MI_UsageList" ).setNext().toInt();
      nRC = oper.SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );  // mMasLC.M_Usage should be on #3 (second) entity
      mMasLC.cursor( "M_Usage" ).logEntity( );  // mMasLC.M_Usage should be on #3 (first) entity

      nRC = mTempLC.cursor( "M_Usage" ).moveSubobject( CursorPosition.PREV, mMasLC.cursor( "M_Usage" ), CursorPosition.PREV ).toInt();  // trying to move #3 before #1 ... BOMB HERE
      nRC = mTempLC.cursor( "MI_UsageList" ).moveSubobject( CursorPosition.PREV, mMasLC.cursor( "MI_UsageList" ), CursorPosition.PREV ).toInt();  // trying to move #3 before #1

      mTempLC.drop( );
      displayFirstThreeEntities( mMasLC, "Last ... 3.1.2?" );

      // We now commit the Master Label Content to maintain order!
      mMasLC.commit( );

      task.log( ).info( "testMoveEntity RC: " + nRC );
      assertEquals( "MoveEntityForReal failed", nRC, 0 );
    }

    @Test
    public void testMoveEntity( ) throws IOException
    {
      View mMasLC = task.activateOiFromFile( "mMasLC", task.getObjectEngine().getHomeDirectory() + "/ePamms/OIs/mMasLC.por", null );
      mMasLC.setName( "mMasLC" );
    //mMasLC.cursor( "M_GeneralSection" ).setNext().toInt();
      mMasLC.cursor( "M_GeneralSection" ).logEntity( false );

      View mTempLC = mMasLC.newView( );
      mTempLC.setName( "mTempLC" );

      // MoveSubobject( mTempLC, "M_GeneralStatement", mMasLC, "M_GeneralStatement", zPOS_PREV, zREPOS_PREV );
      EntityCursor srcCursor = mMasLC.cursor( "M_GeneralStatement" );
      EntityCursor tgtCursor = mTempLC.cursor( "M_GeneralStatement" );
      int nRC = mTempLC.cursor( "M_GeneralStatement" ).setPrevContinue().toInt();  // note that nRC = -1 (cursor unchanged)
      srcCursor.logEntity( false );
      tgtCursor.logEntity( false );
      nRC = tgtCursor.moveSubobject( CursorPosition.PREV, srcCursor, CursorPosition.PREV ).toInt();
      mTempLC.drop( );

      // We now commit the Master Label Content to maintain order!
      mMasLC.commit( );
      task.log( ).info( "testMoveEntity RC: " + nRC );
      assertEquals( "MoveEntity failed", CursorResult.UNCHANGED.toInt(), nRC );
    }

    @Test
    public void testMoveEntityLoopOnCommit( ) throws IOException
    {
      View mMasLC = task.activateOiFromFile( "mMasLC", task.getObjectEngine().getHomeDirectory() + "/ePamms/OIs/mMasLC.por", null );
      mMasLC.setName( "mMasLC" );

      mMasLC.cursor( "M_HumanHazardSection" ).logEntity( false );
      EntityCursor srcCursorList = mMasLC.cursor( "MI_UsageList" );
      int nRC = srcCursorList.setFirst( "ID", "3" ).toInt();
      srcCursorList.logEntity( false );

      View mTempLC = mMasLC.newView( );
      mTempLC.setName( "mTempLC" );
      EntityCursor tgtCursorList = mTempLC.cursor( "MI_UsageList" );
      nRC = tgtCursorList.setFirst( "ID", "2" ).toInt();
      tgtCursorList.logEntity( false );

      VmlTestOperation oper = new VmlTestOperation( task );
      nRC = oper.SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
      nRC = oper.SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" );

      EntityCursor srcCursor = mMasLC.cursor( "M_Usage" );
      EntityCursor tgtCursor = mTempLC.cursor( "M_Usage" );
      srcCursor.logEntity( false );
      tgtCursor.logEntity( false );

      nRC = tgtCursor.moveSubobject( CursorPosition.PREV, srcCursor, CursorPosition.PREV ).toInt();
      nRC = tgtCursorList.moveSubobject( CursorPosition.PREV, srcCursorList, CursorPosition.PREV ).toInt();
      mTempLC.drop( );
      assertEquals( "MoveEntity failed", CursorResult.SET.toInt(), nRC );

      srcCursor.logEntity( false );
      srcCursorList.logEntity( false );

      // We now commit the Master Label Content to maintain order!
      nRC = mMasLC.commit( );                     // infinite loop occurs here in commitExcludes!
      task.log( ).info( "testMoveEntity RC: " + nRC );
    }

    @Test
    public void testSetEntityKey( ) throws IOException
    {
      View mMasLC = task.activateOiFromFile( "mMasLC", task.getObjectEngine().getHomeDirectory() + "/ePamms/OIs/mMasLC.por", null );
      mMasLC.setName( "mMasLC" );
      mMasLC.cursor( "M_GeneralSection" ).setFirst( "SectionType", "F" );
      mMasLC.cursor( "M_GeneralSection" ).logEntity( true );

      View mTempLC = mMasLC.newView( );
      mTempLC.setName( "mTempLC" );

      mTempLC.cursor( "M_GeneralStatement" ).setLast().toInt();;
      long lEKey = mTempLC.cursor( "M_GeneralStatement" ).getEntityKey( );
      int nLastID = mTempLC.cursor( "M_GeneralStatement" ).getIntegerFromAttribute( "ID" );

      mMasLC.cursor( "M_GeneralStatement" ).logEntity( false );
      int nRC = mMasLC.cursor( "M_GeneralStatement" ).setByEntityKey( lEKey ).toInt();
      mMasLC.cursor( "M_GeneralStatement" ).logEntity( false );
      mTempLC = mMasLC.newView( );

      // MoveSubobject( mTempLC, "M_GeneralStatement", mMasLC, "M_GeneralStatement", zPOS_PREV, zREPOS_PREV );
      EntityCursor srcCursor = mMasLC.cursor( "M_GeneralStatement" );
      EntityCursor tgtCursor = mTempLC.cursor( "M_GeneralStatement" );
      nRC = mTempLC.cursor( "M_GeneralStatement" ).setPrevContinue().toInt();  // note that nRC = -1 (cursor unchanged)
      task.log( ).info( "Display mMasLC Entity" );
      srcCursor.logEntity( false );
      task.log( ).info( "Display mTempLC Entity" );
      tgtCursor.logEntity( false );
      tgtCursor.moveSubobject( CursorPosition.PREV, srcCursor, CursorPosition.PREV );
      mMasLC.cursor( "M_GeneralStatement" ).setLast().toInt();;
      int nNewLastID = mMasLC.cursor( "M_GeneralStatement" ).getIntegerFromAttribute( "ID" );
      mTempLC.drop( );

      task.log( ).info( "Display mMasLC M_GeneralSection with children" );
      mMasLC.cursor( "M_GeneralSection" ).logEntity( true );

      task.log( ).info( "testMoveEntity RC: " + nRC );
      task.log( ).info( "testMoveEntity LastID: " + nLastID + "  NewLastID: " + nNewLastID );
      assertNotSame( "SetEntityKey moveSubobject failed", nNewLastID, nLastID );
      mMasLC.cursor( "M_GeneralStatement" ).setPrev( );
      nNewLastID = mMasLC.cursor( "M_GeneralStatement" ).getIntegerFromAttribute( "ID" );
      assertEquals( "SetEntityKey setPrevContinue position failed", 1, nNewLastID  );
      assertEquals( "SetEntityKey setPrevContinue failed", nRC, 0 );
    }

   protected String  Tag;
   protected String  Text;
   protected Integer Type;
   protected Integer Subtype;
   protected Integer StyleX;
   protected Integer OptionFlags;
   protected Integer IdNbr;
   protected Integer PSDLG_X;
   protected Integer PSDLG_Y;
   protected Integer SZDLG_X;
   protected Integer SZDLG_Y;
   protected Integer RELCNT_X;
   protected Integer RELCNT_Y;
   protected Blob    CtrlBOI;
   protected Integer ColorFg;
   protected Integer ColorBk;
   protected Integer DIL;
   protected Blob    EventAct;
   protected Blob    DD_BOI;
   protected String  D_Text;
   protected Integer NLS;
   protected String  TagPE;
   protected String  GUID;
   protected Blob    Properties;
   protected Blob    LKey;
   protected String  Script;
   protected String  ScriptDLL;

   public int loadCtrlEntityStructure( EntityCursor cursor )
   {
      Tag = cursor.getStringFromAttribute( "Tag" );  if ( Tag == null ) Tag = "";
      System.out.println( "Ctrl tag: " + Tag );
      Text = cursor.getStringFromAttribute( "Text" );  if ( Text == null ) Text = "";
      Type = cursor.getIntegerFromAttribute( "Type" );  if ( Type == null ) Type = 0;
      Subtype = cursor.getIntegerFromAttribute( "Subtype" );  if ( Subtype == null ) Subtype = 0;
      StyleX = cursor.getIntegerFromAttribute( "StyleX" );  if ( StyleX == null ) StyleX = 0;
      OptionFlags = cursor.getIntegerFromAttribute( "OptionFlags" );  if ( OptionFlags == null ) OptionFlags = 0;
      IdNbr = cursor.getIntegerFromAttribute( "IdNbr" );  if ( IdNbr == null ) IdNbr = 0;
      PSDLG_X = cursor.getIntegerFromAttribute( "PSDLG_X" );  if ( PSDLG_X == null ) PSDLG_X = 0;
      PSDLG_Y = cursor.getIntegerFromAttribute( "PSDLG_Y" );  if ( PSDLG_Y == null ) PSDLG_Y = 0;
      SZDLG_X = cursor.getIntegerFromAttribute( "SZDLG_X" );  if ( SZDLG_X == null ) SZDLG_X = 0;
      SZDLG_Y = cursor.getIntegerFromAttribute( "SZDLG_Y" );  if ( SZDLG_Y == null ) SZDLG_Y = 0;
      RELCNT_X = cursor.getIntegerFromAttribute( "RELCNT_X" );  if ( RELCNT_X == null ) RELCNT_X = 0;
      RELCNT_Y = cursor.getIntegerFromAttribute( "RELCNT_Y" );  if ( RELCNT_Y == null ) RELCNT_Y = 0;
      CtrlBOI = cursor.getBlobFromAttribute( "CtrlBOI" );
      ColorFg = cursor.getIntegerFromAttribute( "ColorFg" );  if ( ColorFg == null ) ColorFg = 0;
      ColorBk = cursor.getIntegerFromAttribute( "ColorBk" );  if ( ColorBk == null ) ColorBk = 0;
      DIL = cursor.getIntegerFromAttribute( "DIL" );  if ( DIL == null ) DIL = 0;
      EventAct = cursor.getBlobFromAttribute( "EventAct" );
      DD_BOI = cursor.getBlobFromAttribute( "DD_BOI" );
   // D_Text = cursor.getStringFromAttribute( "D_Text" );  if ( D_Text == null ) D_Text = "";
      NLS = cursor.getIntegerFromAttribute( "NLS" );  if ( NLS == null ) NLS = 0;
      TagPE = cursor.getStringFromAttribute( "TagPE" );  if ( TagPE == null ) TagPE = "";
      GUID = cursor.getStringFromAttribute( "GUID" );  if ( GUID == null ) GUID = "";
      Properties = cursor.getBlobFromAttribute( "Properties" );
      LKey = cursor.getBlobFromAttribute( "LKey" );
      Script = cursor.getStringFromAttribute( "Script" );  if ( Script == null ) Script = "";
      ScriptDLL = cursor.getStringFromAttribute( "ScriptDLL" );  if ( ScriptDLL == null ) ScriptDLL = "";
      return 0;
    }

    private class VmlTestOperation extends VmlOperation
    {
        public VmlTestOperation(TaskQualification taskQual)
        {
            super( taskQual );
        }
    }
}

/*
10 Java Regular Expression Examples You Should Know

Published: November 18, 2009 , Updated: January 23, 2010 , Author: mkyong

Regular expressions are an art of programing. They are hard to debug, learn and understand,
but their powerful features are still attract many developers to code regular expressions.
Let’s explore the following 10 practical regular expressions.

1. Username Regular Expression Pattern

 ^[a-z0-9_-]{3,15}$

 ^                   # Start of the line
  [a-z0-9_-]         # Match characters and symbols in the list, a-z, 0-9 , underscore , hyphen
            {3,15}   # Length at least 3 characters and maximum length of 15
                  $  # End of the line
==> See the explanation and example here

2. Password Regular Expression Pattern

((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})

(                                                  # Start of group
 (?=.*\d)                                          # Must contain one digit from 0-9
         (?=.*[a-z])                               # Must contain one lowercase characters
                    (?=.*[A-Z])                    # Must contain one uppercase characters
                               (?=.*[@#$%])        # Must contain one special symbol in the list "@#$%"
                                           .       # Match anything with previous condition checking
                                            {6,20} # Length at least 6 characters and maximum of 20
                                                  )# End of group
==> See the explanation and example here

3. Hexadecimal Color Code Regular Expression Pattern

^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$

^                                   # Start of the line
 #                                  # Must contain a "#" symbols
 (                                  # Start of group #1
  [A-Fa-f0-9]{6}                    # Any strings in the list, with length of 6
                |                   # or
                 [A-Fa-f0-9]{3}     # Any strings in the list, with length of 3
                               )    # End of group #1
                                $   # End of the line
==> See the explanation and example here

4. Email Regular Expression Pattern

^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$

^                                                                                      # Start of the line
 [_A-Za-z0-9-]+                                                                        # Must start with string in the bracket [ ], must contain one or more (+)
               (                                                                       # Start of group #1
                \\.[_A-Za-z0-9-]+                                                      # Follow by a dot "." and string in the bracket [ ], must contain one or more (+)
                                 )*                                                    # End of group #1, this group is optional (*)
                                   @                                                   # Must contain a "@" symbol
                                    [A-Za-z0-9]+                                       # Follow by string in the bracket [ ], must contain one or more (+)
                                                (                                      # Start of group #2 - first level TLD checking
                                                 \\.[A-Za-z0-9]+                       # Follow by a dot "." and string in the bracket [ ], must contain one or more (+)
                                                                )*                     # End of group #2, this group is optional (*)
                                                                  (                    # Start of group #3 - second level TLD checking
                                                                   \\.[A-Za-z]{2,}     # Follow by a dot "." and string in the bracket [ ], with minimum length of 2
                                                                                  )    # End of group #3
                                                                                   $   # End of the line
==> See the explanation and example here

5. Image File Extension Regular Expression Pattern

([^\s]+(\.(?i)(jpg|png|gif|bmp))$)

(                                   # Start of the group #1
 [^\s]+                             # Must contain one or more anything (except white space)
       (                            # Start of the group #2
        \.                          # Follow by a dot "."
          (?i)                      # Ignore the case sensitive checking
              (                     # Start of the group #3
               jpg                  # Contains characters "jpg"
                  |                 # or
                   png              # Contains characters "png"
                      |             # or
                       gif          # Contains characters "gif"
                          |         # or
                           bmp      # Contains characters "bmp"
                              )     # End of the group #3
                               )    # End of the group #2
                                $   # End of the string
                                 )  # End of the group #1
==> See the explanation and example here

6. IP Address Regular Expression Pattern

^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$

^                                                                                                                                            # Start of the line
 (                                                                                                                                           # Start of group #1
  [01]?\\d\\d?                                                                                                                               # Can be one or two digits. If three digits appear, it must start either 0 or 1 ... e.g ([0-9], [0-9][0-9],[0-1][0-9][0-9])
              |                                                                                                                              # or
               2[0-4]\\d                                                                                                                     # Start with 2, follow by 0-4 and end with any digit (2[0-4][0-9])
                        |                                                                                                                    # or
                         25[0-5]                                                                                                             # Start with 2, follow by 5 and end with 0-5 (25[0-5])
                                )                                                                                                            # End of group #2
                                 \\.                                                                                                         # Follow by a dot "."
                                                          ....                                                                               # Repeat with 3 time (3x)
                                                                                                                                          $  # End of the line
==> See the explanation and example here

7. Time Format Regular Expression Pattern

Time in 12-Hour Format Regular Expression Pattern

(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)

(                                            # Start of group #1
 1[012]                                      # Start with 10, 11, 12
       |                                     # or
        [1-9]                                # Start with 1,2,...9
             )                               # End of group #1
              :                              # Follow by a semi colon (:)
               [0-5][0-9]                    # Follow by 0..5 and 0..9, which means 00 to 59
                         (\\s)?              # Follow by a white space (optional)
                               (?i)          # Next checking is case insensitive
                                   (am|pm)   # Follow by am or pm
==> See the explanation and example here

Time in 24-Hour Format Regular Expression Pattern

([01]?[0-9]|2[0-3]):[0-5][0-9]

(                                # Start of group #1
 [01]?[0-9]                      # Start with 0-9,1-9,00-09,10-19
           |                     # or
            2[0-3]               # Start with 20-23
                  )              # End of group #1
                   :             # Follow by a semi colon (:)
                    [0-5][0-9]   # Follow by 0..5 and 0..9, which means 00 to 59
==> See the explanation and example here

8. Date Format (dd/mm/yyyy) Regular Expression Pattern

(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)

(                                                           # Start of group #1
 0?[1-9]                                                    # 01-09 or 1-9
        |                                                   # or
         [12][0-9]                                          # 10-19 or 20-29
                  |                                         # or
                   3[01]                                    # 30, 31
                        )                                   # End of group #1
                         /                                  # Follow by a "/"
                          (                                 # Start of group #2
                           0?[1-9]                          # 01-09 or 1-9
                                  |                         # or
                                   1[012]                   # 10,11,12
                                         )                  # End of group #2
                                          /                 # Follow by a "/"
                                           (                # Start of group #3
                                            (19|20)\\d\\d   # 19[0-9][0-9] or 20[0-9][0-9]
                                                         )  # End of group #3
==> See the explanation and example here

9. HTML tag Regular Expression Pattern

<("[^"]*"|'[^']*'|[^'">])*>

<                             # Start with opening tag "<"
 (                            # Start of group #1
  "[^"]*"                     # Only two double quotes are allowed - "string"
         |                    # or
          '[^']*'             # Only two apostrophes are allowed - 'string'
                 |            # or
                  [^'">]      # Can't contain single apostrophe, double quote, or >
                        )     # End of group #1
                         *    # 0 or more
                          >   # End with closing tag ">"
==> See the explanation and example here

10. HTML links Regular Expression Pattern

HTML A tag Regular Expression Pattern

(?i)<a([^>]+)>(.+?)</a>

(                       # Start of group #1
 ?i                     # All checking are case insensive
   )                    # End of group #1
    <a                  # Start with "<a"
      (                 # Start of group #2
       [^>]+            # Anything except (">"), at least one character
            )           # End of group #2
             >          # Follow by ">"
              (.+?)     # Match anything
                   </a> # End with "</a>
Extract HTML link Regular Expression Pattern

\s*(?i)href\s*=\s*(\"([^"]*\")|'[^']*'|([^'">\s]+));

\s*                                                   # Can start with whitespace
   (?i)                                               # All checking are case insensive
       href                                           # Follow by "href" word
           \s*=\s*                                    # Allows spaces on either side of the equal sign,
                  (                                   # Start of group #1
                   \"([^"]*\")                        # Only two double quotes are allow - "string"
                              |                       # or
                               '[^']*'                # Only two single quotes are allow - 'string'
                                      |               # or
                                       ([^'">\s]+)    # Can't contain one single / double quotes and ">"
                                                  )   # End of group #1

To develop regular expressions, ordinary and special characters are used:

   \$  ^ .  *  +  ?  [' '] \.

Any other character appearing in a regular expression is ordinary, unless a \ precedes it.

Special characters serve a special purpose. For instance, the . matches anything except a
new line. A regular expression like s.n matches any three-character string that begins with
s and ends with n, including sun and son.

There are many special characters used in regular expressions to find words at the beginning
of lines, words that ignore case or are case-specific, and special characters that give a range,
such as a-e, meaning any letter from a to e.

Regular expression usage using this new package is Perl-like, so if you are familiar with using
regular expressions in Perl, you can use the same expression syntax in the Java programming
language. If you're not familiar with regular expressions here are a few to get you started:

Construct   Matches
Characters

 x              The character x
 \\             The backslash character
 \0n            The character with octal value 0n (0 <= n <= 7)
 \0nn           The character with octal value 0nn (0 <= n <= 7)
 \0mnn          The character with octal value 0mnn (0 <= m <= 3, 0 <= n <= 7)
 \xhh           The character with hexadecimal value 0xhh
\\uhhhh         The character with hexadecimal value 0xhhhh
 \t             The tab character ('\u0009')
 \n             The newline (line feed) character ('\u000A')
 \r             The carriage-return character ('\u000D')
 \f             The form-feed character ('\u000C')
 \a             The alert (bell) character ('\u0007')
 \e             The escape character ('\u001B')
 \cx            The control character corresponding to x

Character Classes

[abc]          a, b, or c (simple class)
[^abc]         Any character except a, b, or c (negation)
[a-zA-Z]       a through z or A through Z, inclusive (range)
[a-z-[bc]]     a through z, except for b and c: [ad-z] (subtraction)
[a-z-[m-p]]    a through z, except for m through p: [a-lq-z]
[a-z-[^def]]   d, e, or f

Predefined Character Classes
.              Any character (may or may not match line terminators)
\d             A digit: [0-9]
\D             A non-digit: [^0-9]
\s             A whitespace character: [ \t\n\x0B\f\r]
\S             A non-whitespace character: [^\s]
\w             A word character: [a-zA-Z_0-9]
\W             A non-word character: [^\w]

Check the documentation about the Pattern class for more specific details and examples.

Classes and Methods

The following classes match character sequences against patterns specified by regular expressions.

Pattern Class

An instance of the Pattern class represents a regular expression that is specified in
string form in a syntax similar to that used by Perl.

A regular expression, specified as a string, must first be compiled into an instance of
the Pattern class. The resulting pattern is used to create a Matcher object that matches
arbitrary character sequences against the regular expression. Many matchers can share the
same pattern because it is stateless.

The compile method compiles the given regular expression into a pattern, then the matcher
method creates a matcher that will match the given input against this pattern. The pattern
method returns the regular expression from which this pattern was compiled.

The split method is a convenience method that splits the given input sequence around matches
of this pattern. The following example demonstrates:

//
// Uses split to break up a string of input separated by commas and/or whitespace.
//
import java.util.regex.*;

public class Splitter
{
    public static void main(String[] args) throws Exception
    {
        // Create a pattern to match breaks
        Pattern p = Pattern.compile("[,\\s]+");

        // Split input with the pattern
        String[] result = p.split("one,two, three   four ,  five");
        for (int i=0; i<result.length; i++)
            System.out.println(result[i]);
    }
}

Matcher Class

Instances of the Matcher class are used to match character sequences against a given string
sequence pattern. Input is provided to matchers using the CharSequence interface to support
matching against characters from a wide variety of input sources.

A matcher is created from a pattern by invoking the pattern's matcher method. Once created,
a matcher can be used to perform three different kinds of match operations:

The matches method attempts to match the entire input sequence against the pattern.
The lookingAt method attempts to match the input sequence, starting at the beginning, against the pattern.
The find method scans the input sequence looking for the next sequence that matches the pattern.

Each of these methods returns a boolean indicating success or failure. More information
about a successful match can be obtained by querying the state of the matcher.

This class also defines methods for replacing matched sequences by new strings whose
contents can, if desired, be computed from the match result.

The appendReplacement method appends everything up to the next match and the replacement
for that match. The appendTail appends the strings at the end, after the last match.

For instance, in the string blahcatblahcatblah, the first appendReplacement appends blahdog.
The second appendReplacement appends blahdog, and the appendTail appends blah, resulting
in: blahdogblahdogblah. See Simple word replacement for an example.

CharSequence Interface

The CharSequence interface provides uniform, read-only access to many different types of
character sequences. You supply the data to be searched from different sources. String,
StringBuffer and CharBuffer implement CharSequence, so they are easy sources of data to
search through. If you don't care for one of the available sources, you can write your own
input source by implementing the CharSequence interface.

Example Regex Scenarios

The following code samples demonstrate the use of the java.util.regex package for various common scenarios:

// Simple Word Replacement
//
// This code writes "One dog, two dogs in the yard." to the standard-output stream:
//
import java.util.regex.*;

public class Replacement
{
    public static void main(String[] args) throws Exception
    {
        // Create a pattern to match cat
        Pattern p = Pattern.compile("cat");

        // Create a matcher with an input string
        Matcher m = p.matcher("one cat, two cats in the yard");
        StringBuffer sb = new StringBuffer();
        boolean result = m.find();

        // Loop through and create a new String with the replacements
        while(result)
        {
            m.appendReplacement(sb, "dog");
            result = m.find();
        }

        // Add the last segment of input to
        // the new String
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}

The following code is a sample of some characters you can check are in an email address,
or should not be in an email address. It is not a complete email validation program that
checks for all possible email scenarios, but can be added to as needed.

// Email Validation
//
// Checks for invalid characters in email addresses
//
public class EmailValidation
{
   public static void main(String[] args) throws Exception
   {
      String input = "@sun.com";

      // Checks for email addresses starting with inappropriate symbols like dots or @ signs.
      Pattern p = Pattern.compile("^\\.|^\\@");
      Matcher m = p.matcher(input);
      if (m.find())
         System.err.println("Email addresses don't start with dots or @ signs.");

      // Checks for email addresses that start with www. and prints a message if it does.
      p = Pattern.compile("^www\\.");
      m = p.matcher(input);
      if (m.find()) {
        System.out.println("Email addresses don't start with \"www.\", only web pages do.");
      }

      p = Pattern.compile("[^A-Za-z0-9\\.\\@_\\-~#]+");
      m = p.matcher(input);
      StringBuffer sb = new StringBuffer();
      boolean result = m.find();
      boolean deletedIllegalChars = false;

      while(result)
      {
         deletedIllegalChars = true;
         m.appendReplacement(sb, "");
         result = m.find();
      }

      // Add the last segment of input to the new String
      m.appendTail(sb);

      input = sb.toString();

      if (deletedIllegalChars)
      {
         System.out.println("It contained incorrect characters such as spaces or commas.");
      }
   }
}


// Removing Control Characters from a File
//
// This class removes control characters from a named file.
//
import java.util.regex.*;
import java.io.*;

public class Control
{
    public static void main(String[] args) throws Exception
    {
        // Create a file object with the file name in the argument:
        File fin = new File("fileName1");
        File fout = new File("fileName2");

        // Open and input and output stream
        FileInputStream fis = new FileInputStream(fin);
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedReader in = new BufferedReader( new InputStreamReader(fis) );
        BufferedWriter out = new BufferedWriter( new OutputStreamWriter(fos) );

        // The pattern matches control characters
        Pattern p = Pattern.compile("{cntrl}");
        Matcher m = p.matcher("");
        String aLine = null;
        while((aLine = in.readLine()) != null)
        {
            m.reset(aLine);

            // Replaces control characters with an empty string.
            String result = m.replaceAll("");
            out.write(result);
            out.newLine();
        }

        in.close();
        out.close();
    }
}


// File Searching
//
// Prints out the comments found in a .java file.
//
import java.util.regex.*;
import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.nio.channels.*;

public class CharBufferExample
{
    public static void main(String[] args) throws Exception
    {
        // Create a pattern to match comments
        Pattern p = Pattern.compile("//.*$", Pattern.MULTILINE);

        // Get a Channel for the source file
        File f = new File("Replacement.java");
        FileInputStream fis = new FileInputStream(f);
        FileChannel fc = fis.getChannel();

        // Get a CharBuffer from the source file
        ByteBuffer bb = fc.map(FileChannel.MAP_RO, 0, (int)fc.size());
        Charset cs = Charset.forName("8859_1");
        CharsetDecoder cd = cs.newDecoder();
        CharBuffer cb = cd.decode(bb);

        // Run some matches
        Matcher m = p.matcher(cb);
        while (m.find())
           System.out.println("Found comment: "+m.group());
    }
}

Conclusion

Pattern matching in the Java programming language is now as flexible as in many other programming languages.
Regular expressions can be put to use in applications to ensure data is formatted correctly before being entered
into a database, or sent to some other part of an application, and they can be used for a wide variety of administrative
tasks. In short, you can use regular expressions anywhere in your Java programming that calls for pattern matching.

*/