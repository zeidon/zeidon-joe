/**
 *
 */
package com.quinsoft.zeidon.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.utils.QualificationBuilder;
import com.quinsoft.zeidon.utils.ZeidonInputStream;
import com.quinsoft.zencas.scalasamples.SampleActivates;
import com.quinsoft.zencas.scalasamples.SampleAttributeCode;
import com.quinsoft.zencas.scalasamples.SampleCursorManipulation;
import com.quinsoft.zencas.scalasamples.SampleViewManipulations;

/**
 * @author DG
 *
 */
public class ViewTest
{
    Task         zencas;
    Task         zeidonSystem;
    ObjectEngine oe;
    private Task zeidonTools;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        System.out.println( "CWD = " + System.getProperty("user.dir") );
        oe = JavaObjectEngine.getInstance();
        zencas = oe.createTask( "ZENCAs" );
        zeidonTools = oe.createTask( "ZeidonTools" );
        zeidonSystem = oe.getSystemTask();
    }

//    @Test
//    public void testDynamicDomains()
//    {
//        View student = zencas.activateEmptyObjectInstance( "mPerson" );
//        student.cursor( "Person" ).createEntity();
//        student.cursor( "Prospect" ).createEntity()
//                                    .getAttribute( "AcceptanceStatus").setValue( "xx" ) ;
//    }

    private void runJsonTest( String json, boolean rootOnly )
    {
        QualificationBuilder qb = new QualificationBuilder( zencas );
        qb.setLodDef( "mPerson" );
        qb.loadFromJsonString( json );
        if ( rootOnly )
            qb.rootOnly();
        qb.activate();
    }

    private void runJsonTest( String json )
    {
        runJsonTest( json, true );
    }

    @Test
    public void testQualBuilderFromJson() throws IOException
    {
        String json;

        json = "{\r\n" +
                "    \"$or\": [ { \"FirstName\": \"Bob\" }, { \"$and\" : [ { \"LastName\": \"Smith\" }, { \"MaidenName\": { \"$neq\": \"Smith\" } } ] } ]\r\n" +
                "}";

        runJsonTest( json );

        json = "{\r\n" +
                "    \"ID\": [10, 11, 12],\r\n" +
                "    \"MaidenName\": \"Alice\",\r\n" +
                "    \"$or\": [ { \"FirstName\": \"Bob\" }, { \"LastName\": \"Smith\" } ],\r\n" +
                "    \"DateOfBirth\": { \"$gt\": \"01/01/2001\", \"$lt\": \"01/01/2010\" }\r\n" +
                "}";

        runJsonTest( json );

        json = "{\r\n" +
                "    \"MaidenName\": { \"$neq\": \"Smith\" }\r\n" +
                "}";

        runJsonTest( json );

        json = "{\r\n" +
                "    \"MaidenName\": { \"$neq\": \"Smith\" },\r\n" +
                "    \"ID\": [10, 11, 12],\r\n" +
                "    \"Address\": { \"Description\": { \"$neq\": \"\" } }\r\n" +
                "}";

        runJsonTest( json );

        json = "{\r\n" +
                "    \"ID\": [10, 11, 12],\r\n" +
                "    \"restricting\": {\r\n" +
                "        \"Address\": {\r\n" +
                "            \"Description\": { \"<>\": \"\" }\r\n" +
                "        }\r\n" +
                "    }\r\n" +
                "}";

        runJsonTest( json, false );

        System.out.println( "here" );
    }

    @Test
    public void testSubobject() throws IOException
    {
        String oldfile = "target/test-classes/testdata//ZENCAs/mRptStrD.XWD";
        String newfile = zeidonSystem.getTempDirectory() + "testxwd.XWD";
        LodDef xwdOD = zeidonSystem.getApplication( "ZeidonTools" ).getLodDef( zencas, "kzwdlgxo" );
        View xwd;

        xwd = zeidonSystem.activateEmptyObjectInstance( xwdOD );
        xwd.cursor( "Dlg" ).createEntity();
        xwd.cursor( "Wnd" ).createEntity();
        xwd.cursor( "Ctrl" ).createEntity().getAttribute( "Tag").setValue( "Ctrl1" ) ;
        EntityCursor ctrl  = xwd.cursor("Ctrl" );
        EntityCursor cctrl = xwd.cursor("CtrlCtrl" );
        cctrl.createEntity().getAttribute( "Tag").setValue( "Ctrl2" ) ;
        cctrl.setToSubobject();
        cctrl.createEntity().getAttribute( "Tag").setValue( "Ctrl3" ) ;
        cctrl.setToSubobject();
        cctrl.createEntity().getAttribute( "Tag").setValue( "Ctrl4" ) ;
        assertEquals( "Subobject Ctrl has wrong value ",     ctrl.getAttribute( "Tag" ).getString(), "Ctrl3" );
        assertEquals( "Subobject CtrlCtrl has wrong value ", cctrl.getAttribute( "Tag" ).getString(), "Ctrl4" );
        xwd.logObjectInstance();
        xwd.cursor( "CtrlCtrl" ).resetSubobjectToParent();
        assertEquals( "Subobject Ctrl has wrong value ",     ctrl.getAttribute( "Tag" ).getString(), "Ctrl2" );
        assertEquals( "Subobject CtrlCtrl has wrong value ", cctrl.getAttribute( "Tag" ).getString(), "Ctrl3" );
        xwd.cursor( "CtrlCtrl" ).resetSubobjectToParent();
        assertEquals( "Subobject Ctrl has wrong value ",     ctrl.getAttribute( "Tag" ).getString(), "Ctrl1" );
        assertEquals( "Subobject CtrlCtrl has wrong value ", cctrl.getAttribute( "Tag" ).getString(), "Ctrl2" );

        xwd = zencas.activateOiFromFile( xwdOD, oldfile, null );
        xwd.logObjectInstance();
        xwd.writeOiToFile( newfile, null );

//        String f1 = FileUtils.readFileToString( new File( oldfile ) );
//        String f2 = FileUtils.readFileToString( new File( newfile ) );
//        String diff = StringUtils.difference( f1, f2 );
//        boolean equals = StringUtils.isBlank( diff );
//        if ( !equals )
//            Runtime.getRuntime().exec("windiff " + oldfile + " " + newfile );
    }

    @Test
    public void testVersioning()
    {
        LodDef xwdOD = zeidonSystem.getApplication( "ZeidonTools" ).getLodDef( zencas, "kzwdlgxo" );
        View xwd = zencas.activateOiFromFile( xwdOD, "target/test-classes/testdata//ZENCAs/mRptStrD.XWD", null );
        EntityCursor ctrl = xwd.cursor( "Ctrl" );
        EntityCursor ctrlMap = xwd.cursor( "CtrlMap" );

        // Get current value.
        Integer originalY = ctrl.getAttribute( "PSDLG_Y" ).getInteger();

        //
        // Create and cancel
        //

        // Create temporal subobject and verify that the attr values is still the same.
        ctrl.createTemporalSubobjectVersion();
        Integer newY = ctrl.getAttribute( "PSDLG_Y" ).getInteger();
        assertEquals( "CreateTemporal isn't working", newY, originalY );
        asserhttp://www.google.com/tTrue( "Tag should still be null", ctrlMap.getAttribute( "Tag" ).isNull() );

        // Add one to the value and verify that it got set correctly.
        ctrl.getAttribute( "PSDLG_Y").setValue( originalY + 1 ) ;
        newY = ctrl.getAttribute( "PSDLG_Y" ).getInteger();
        assertEquals( "Versioned instance values aren't working", (Integer) ( newY - 1 ), originalY );

        ctrlMap.getAttribute( "Tag").setValue( "TestTag" ) ;
        assertEquals( "CtrlMap.Tag wasn't set correctly", ctrlMap.getAttribute( "Tag" ).getString(), "TestTag" );

        // Cancel the subobject and verify that we have the old value.
        ctrl.cancelSubobject();
        newY = ctrl.getAttribute( "PSDLG_Y" ).getInteger();
        assertEquals( "Cancel version isn't working", newY, originalY );
        assertTrue( "Tag should still be null", ctrlMap.getAttribute( "Tag" ).isNull() );

        //
        // Create and accept
        //

        // Create temporal subobject (again) and verify that the attr values is still the same.
        ctrl.createTemporalSubobjectVersion();
        newY = ctrl.getAttribute( "PSDLG_Y" ).getInteger();
        assertEquals( "Create Temporal isn't working", newY, originalY );
        assertTrue( "Tag should still be null", ctrlMap.getAttribute( "Tag" ).isNull() );

        // Add one to the value and verify that it got set correctly.
        ctrl.getAttribute( "PSDLG_Y").setValue( originalY + 1 ) ;
        newY = ctrl.getAttribute( "PSDLG_Y" ).getInteger();
        assertEquals( "Versioned instance values aren't working", (Integer) ( newY - 1 ), originalY );

        ctrlMap.getAttribute( "Tag").setValue( "TestTag2" ) ;
        assertEquals( "CtrlMap.Tag wasn't set correctly", ctrlMap.getAttribute( "Tag" ).getString(), "TestTag2" );

        ctrl.acceptSubobject();
        newY = ctrl.getAttribute( "PSDLG_Y" ).getInteger();
        assertEquals( "Versioned instance values aren't working", (Integer) ( newY - 1 ), originalY );
        assertEquals( "CtrlMap.Tag wasn't set correctly", ctrlMap.getAttribute( "Tag" ).getString(), "TestTag2" );

        //
        // Test with linked entities.
        //

        // Link them.
        xwd = zencas.activateOiFromFile( xwdOD, "target/test-classes/testdata//ZENCAs/mRptStrD.XWD", null );
        View xwd2 = xwd.newView();
        xwd.cursor( "Ctrl" ).setFirst();
        xwd2.cursor( "Ctrl" ).setFirst();
        xwd2.cursor( "Ctrl" ).setNext();
        xwd2.cursor( "Ctrl" ).getAttribute( "Tag").setValue( xwd.cursor(  "Ctrl" ).getAttribute(  "Tag" ).getValue() )  ;
        xwd2.cursor( "Ctrl" ).linkInstances( xwd.cursor( "Ctrl" ) );

        // Verify they have the same attribute value.
        Integer y  = xwd.cursor( "Ctrl" ).getAttribute( "PSDLG_Y" ).getInteger();
        Integer y2 = xwd2.cursor( "Ctrl" ).getAttribute( "PSDLG_Y" ).getInteger();
        assertEquals("linkInstances didn't appear to work", y, y2 );

        // Change the value for one entity and make sure they're still the same.
        xwd.cursor( "Ctrl" ).getAttribute( "PSDLG_Y").setValue( y + 1 ) ;
        y  = xwd.cursor( "Ctrl" ).getAttribute( "PSDLG_Y" ).getInteger();
        y2 = xwd2.cursor( "Ctrl" ).getAttribute( "PSDLG_Y" ).getInteger();
        assertEquals("linkInstances didn't appear to work", y, y2 );

        // Create temporal for one of the linked instances.  Make sure both don't get changed.
        xwd.cursor( "Ctrl" ).createTemporalSubobjectVersion();
        xwd.cursor( "Ctrl" ).getAttribute( "PSDLG_Y").setValue( y + 1 ) ;
        y  = xwd.cursor( "Ctrl" ).getAttribute( "PSDLG_Y" ).getInteger();
        y2 = xwd2.cursor( "Ctrl" ).getAttribute( "PSDLG_Y" ).getInteger();
        assertTrue("Changing a temporal value changes linked values.", y != y2 );

        // Accept the subobject and make sure the attributes are now the same.
        xwd.cursor( "Ctrl" ).acceptSubobject();
        y2 = xwd2.cursor( "Ctrl" ).getAttribute( "PSDLG_Y" ).getInteger();
        assertEquals("linkInstances didn't appear to work", y, y2 );
        newY  = xwd.cursor( "Ctrl" ).getAttribute( "PSDLG_Y" ).getInteger();
        assertEquals("AcceptSubobject changed a value", y, newY );

        // Change the value one last time and make sure they're still the same.
        xwd.cursor( "Ctrl" ).getAttribute( "PSDLG_Y").setValue( newY + 1 ) ;
        y  = xwd.cursor( "Ctrl" ).getAttribute( "PSDLG_Y" ).getInteger();
        y2 = xwd2.cursor( "Ctrl" ).getAttribute( "PSDLG_Y" ).getInteger();
        assertEquals("Linked, accepted instances don't appear to be linked.", y, y2 );

        //
        // Temporal entities.
        //
        EntityCursor wnd = xwd.cursor( "Wnd" );
        wnd.createTemporalEntity( CursorPosition.LAST );
        wnd.getAttribute( "Caption").setValue( "testvalue" ) ;
        wnd.cancelTemporalEntity();

        wnd.createTemporalEntity( CursorPosition.LAST );
        wnd.getAttribute( "Caption").setValue( "testvalue" ) ;
        wnd.acceptTemporalEntity();
    }


    @Test
    public void testActivateFromFile()
    {
        View view = zeidonTools.activateEmptyObjectInstance( "kzwdlgxo" );
        view.logObjectInstance();

        view = zeidonTools.deserializeOi()
                          .fromFile( "target/test-classes/testdata//ZENCAs/mRptStrD.XWD" )
                          .setApplication( zeidonTools.getApplication() )
                          .activateFirst();
        view.logObjectInstance();

        // Activate a multiple-root object with SINGLE and make sure there's only one root.
        view = zencas.activateOiFromFile( "mEmploy", "target/test-classes/testdata//ZENCAs/multipleRoots.txt", ActivateFlags.SINGLE );
        assertEquals( view.cursor( "Employee" ).getEntityCount(), 1 );

        // Activate a file with unknown entities/attributes.  Shouldn't throw an exception.
//        view = zencas.activateOiFromFile( "mEmploy", "./testdata/ZENCAs/mEmploy-unknown-elements.txt", ActivateFlags.IGNORE_ERRORS );
    }


    @Test
    public void testReportedBugs()
    {
        // Reported by Kelly.  Activating wXferO caused a a problem because it had entities without
        // datafields that were parents of entities *with* datafields.
        @SuppressWarnings("unused")
        //View view = zencas.activateEmptyObjectInstance( "wXferO" );

//        view = zencas.activateEmptyObjectInstance( "KZXMLPGO", ObjectEngine.ZEIDON_SYSTEM_APP_NAME );
//        view.cursor( "Session" ).createEntity().getAttribute( "UserID").setValue( "Test user id" ) ;
        View wXferO = zencas.activateEmptyObjectInstance( "wXferO" );
        wXferO.cursor( "Root" ).createEntity();
        View mYearLST = zencas.activateEmptyObjectInstance( "mYear" );
        String   szTempString_0 = null;
        int      lTempInteger_0 = 0;
        CursorResult  RESULT;

        mYearLST = zencas.activateOiFromFile( "mYear", "target/test-classes/testdata//ZENCAs/mYearLST.por", ActivateFlags.IGNORE_ATTRIB_ERRORS );
        RESULT = mYearLST.cursor( "CollegeYear" ).setFirst( "ID", 38 );
        wXferO.cursor( "StudentScheduleCollegeYear" ).includeSubobject( mYearLST.cursor( "CollegeYear" ), CursorPosition.NEXT );

        RESULT = wXferO.cursor( "StudentScheduleCollegeYear" ).excludeEntity();

        RESULT = mYearLST.cursor( "CollegeYear" ).setFirst( "ID", 36 );
        wXferO.cursor( "StudentScheduleCollegeYear" ).includeSubobject( mYearLST.cursor( "CollegeYear" ), CursorPosition.NEXT );

        RESULT = wXferO.cursor( "StudentScheduleCollegeYear" ).excludeEntity();

        RESULT = mYearLST.cursor( "CollegeYear" ).setFirst( "ID", 38 );
        wXferO.cursor( "StudentScheduleCollegeYear" ).includeSubobject( mYearLST.cursor( "CollegeYear" ), CursorPosition.NEXT );

        // At this point in my code, I wouldn't see either StudentScheduleCollegeYear.
        assertTrue( "Include/Exclude for Derived not working properly",
        		wXferO.cursor( "StudentScheduleCollegeYear" ).checkExistenceOfEntity() == CursorResult.SET );

    }

    @Test
    public void testReportedBugs2()
    {
        CursorResult RESULT;

        // Reported by Kelly.  Because there was an issue with linking Hier entities in the
    	// insertInstance function, we were hanging when making the second .setFirst() call after
    	// an orderEntities call.mstudencsqlOI.por
        View mStudenC = zencas.activateEmptyObjectInstance( "mStudenC");
        // The following por file didn't work because of the finalexamstarttime and finalexamendtime
        // For some reason in export of the por file the time only has the date part. 20090808.
        // The second por file doesn't work either.  This time the value is 19000101113000000.
        //mStudenC = zencas.activateOiFromFile( "mStudenC", "./testdata/ZENCAs/mStudenCOI.por", null );
        mStudenC = zencas.activateOiFromFile( "mStudenC", "target/test-classes/testdata//ZENCAs/mstudencsqlOI.por", ActivateFlags.IGNORE_ATTRIB_ERRORS );
        mStudenC.cursor( "DisplayAcademicStanding" ).orderEntities( "DisplayAcademicStandingTerm.YearSemester D" );
        RESULT = mStudenC.cursor( "DisplayAcademicStanding" ).setFirst();
        RESULT = mStudenC.cursor( "HS_RequiredGroup" ).setFirst();
        assertEquals( "setFirst() error", CursorResult.NULL, RESULT );
        System.out.println( "No Hanging issue after ordering mStudenC.\n"  );
    }

    @Test
    public void testReportedBugs3() throws IOException
    {
        // Reported by Kelly.
    	// Looping through entities using WITHIN doesn't seem to be working, at least in the case
    	// of RegistrationClass under Registration in mStudenC.  Seems to loop through the
    	// appropriate times, but when I try and display data, I am always on the same entity.
        View mStudenC = zencas.activateEmptyObjectInstance( "mStudenC");
        // The following por file didn't work because of the finalexamstarttime and finalexamendtime
        // For some reason in export of the por file the time only has the date part. 20090808.
        // The second por file doesn't work either.  This time the value is 19000101113000000.
        //mStudenC = zencas.activateOiFromFile( "mStudenC", "./testdata/ZENCAs/mStudenCOI.por", null );
        mStudenC = zencas.activateOiFromFile( "mStudenC", "target/test-classes/testdata//ZENCAs/mStudenCWithin.por", ActivateFlags.IGNORE_ATTRIB_ERRORS );
        mStudenC.log( ).info("*** Show mStudenC data ***");
        CursorResult RESULT = mStudenC.cursor( "RegistrationClass" ).setFirst( "Student" );
        //RESULT = mStudenC.cursor( "Registration" ).setFirst().toInt();
        while ( RESULT.isSet() )
        {
     	   //mStudenC.log( ).info(mStudenC.cursor( "Registration" ).getAttribute( "ID" ).getString());
           System.out.println( mStudenC.cursor( "Registration" ).getAttribute( "ID" ).getString()  );
           System.out.println( mStudenC.cursor( "RegistrationClass" ).getAttribute( "ID" ).getString()  );

           RESULT = mStudenC.cursor( "RegistrationClass" ).setNextContinue();
        }

        System.out.println( "Done.\n"  );
    }

    /**
     * Tests loading an inputstream from a resourceName that is a list of resources separated by "|".
     */
    @Test
    public void testLoadingResourceList()
    {
        String filename = "target/test-classes/testdata/ZENCAs/ThisFileDoesntExist|" +
                          "target/test-classes/testdata/ZENCAs/mStudenCWithin.por";
        filename = StrSubstitutor.replaceSystemProperties( filename );
        ZeidonInputStream stream = JoeUtils.getInputStream( filename );
        String desc = stream.getDescription();
        assertTrue( "Somehow didn't find the correct stream", desc.endsWith( ".por" ));
    }

    @Test
    public void testScalaSamples()
    {
        SampleActivates sampleActivates = new SampleActivates( zencas );
        com.quinsoft.zeidon.scala.View mUser = sampleActivates.runAll();

        SampleCursorManipulation sampleCursor = new SampleCursorManipulation( zencas );
        sampleCursor.runAll( mUser );

        SampleViewManipulations sampleView = new SampleViewManipulations( zencas );
        sampleView.runAll( mUser );

        SampleAttributeCode sampleCode = new SampleAttributeCode( zencas );
        sampleCode.runAll( mUser );
    }
}