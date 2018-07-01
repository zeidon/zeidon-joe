/**
 *
 */
package com.quinsoft.zeidon.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.EnumSet;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.UnknownAttributeDefException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.WriteOiFlags;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.DynamicAttributeDefConfiguration;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
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
    View         mFASrc;
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

    private View createTestOI()
    {
        mFASrc = zencas.deserializeOi().fromFile( "target/test-classes/testdata/ZENCAs/ViewTest1.json" ).activateFirst();
        return mFASrc;
    }

    /**
     * Creates a test object that is used in tests.  No tests using this object should
     * care about the number of entities.
     *
     * @return
     */
    private View createTestOI2()
    {
        mFASrc = zencas.deserializeOi().fromFile( "target/test-classes/testdata/ZENCAs/ViewTest2.json" ).activateFirst();
        return mFASrc;
    }

//    @Test
//    public void testDynamicDomains()
//    {
//        View student = zencas.activateEmptyObjectInstance( "mPerson" );
//        student.cursor( "Person" ).createEntity();
//        student.cursor( "Prospect" ).createEntity()
//                                    .getAttribute( "AcceptanceStatus").setValue( "xx" ) ;
//    }

    @Test
    public void testCommitToFile()
    {
        createTestOI();
        String filename = mFASrc.getTempDirectory() + "mfasrc.por";
        String attrvalue = "This is line 1\r\nThis is line2";
        mFASrc.cursor( "FinAidSource" ).getAttribute( "SourceFootnote").setValue( attrvalue ) ;
        mFASrc.writeOiToFile( filename, EnumSet.of( WriteOiFlags.INCREMENTAL ) );
        View v2 = zencas.activateOiFromFile( "mFASrc", filename );
        String str = v2.cursor( "FinAidSource" ).getAttribute( "SourceFootnote" ).getString();
        assertEquals( "Multi-line attribute value fails comparison", attrvalue, str );
    }

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
    public void testCursorsWithinOi()
    {
        createTestOI();
        mFASrc.reset();
        int count = 0;
        for ( CursorResult rc = mFASrc.cursor( "Fund" ).setFirstWithinOi();
              rc.isSet();
              rc = mFASrc.cursor( "Fund" ).setNextContinue() )
        {
            count++;
        }
        assertEquals( "setFirst/NextWithinOi didn't find all entities", count, 4 );

        count = 0;
        for ( CursorResult rc = mFASrc.cursor( "Fund" ).setFirstWithinOi();
              rc.isSet();
              rc = mFASrc.cursor( "Fund" ).setNextWithinOi() )
        {
            count++;
        }

        assertEquals( "setFirst/NextWithinOi didn't find all entities", count, 4 );

        View view = zencas.activateOiFromFile( "mEmploy", "target/test-classes/testdata//ZENCAs/mEmploy2.txt", ActivateFlags.SINGLE );
        CursorResult rc = view.cursor( "HistDeptContractDistribution" ).setFirst( "Employee" );
        String id = view.cursor( "HistDeptContractDistribution" ).getAttribute( "ID" ).getString();
        assertEquals( "setFirst with scoping didn't find first instance.", "101", id );
        rc = view.cursor( "HistDeptContractDistribution" ).setFirstWithinOi();
        assertEquals( "setFirstWithinOi didn't find first instance.", CursorResult.SET, rc );

        boolean hasAny = mFASrc.cursor( "Fund" ).hasAny();
        assertTrue( "hasAny failed.", hasAny );

        view.cursor( "HistoricalContract" ).setFirst();
        hasAny = view.cursor( "EmployeePerson" ).hasAny();
        assertTrue( "hasAny failed.", hasAny );

        hasAny = view.cursor( "HistDeptContractDistribution" ).hasAnyWithinOi();
        assertTrue( "hasAnyWithinOi failed.", hasAny );

        hasAny = view.cursor( "HistDeptContractDistribution" ).hasAny( "Employee" );
        assertTrue( "hasAny failed.", hasAny );

        hasAny = view.cursor( "HistDeptContractDistribution" ).hasAny();
        assertTrue( "hasAny failed.", ! hasAny );
    }

    //@Test
    public void testDelete()
    {
        createTestOI();
        mFASrc.reset();
        EntityCursor schol = mFASrc.cursor( "Scholarship" );

        // Create a third entity at the end
        schol.setLast();
        schol.createEntity( CursorPosition.LAST, EnumSet.of( CreateEntityFlags.fIGNORE_MAX_CARDINALITY, CreateEntityFlags.fIGNORE_PERMISSIONS )).getAttribute( "ID" ).setValue(  2  ) ;

        // Position on the second entity and delete it.
        schol.setFirst();
        schol.setNext();
        CursorResult rc = schol.deleteEntity( CursorPosition.NONE );
        assertEquals( "Delete returned wrong return code", rc, CursorResult.UNCHANGED );
        rc = schol.setNext();
        assertEquals( "Delete didn't set cursor correctly", rc, CursorResult.SET );
        String id = schol.getAttribute( "ID" ).getString();
        assertEquals( "Delete didn't set cursor correctly", id, "2" );

        rc = schol.setNext();
        assertTrue( ! rc.isSet() );
        Integer i = schol.getAttribute( "ID" ).getInteger();
        assertEquals("Delete with repos none didn't work.", i, (Integer) 2 );

        rc = mFASrc.cursor( "FinAidSource" ).deleteEntity();
        assertTrue( ! rc.isSet() );

        // Recreate the OI so we can try more deletes.
        createTestOI();
        mFASrc.reset();
        schol = mFASrc.cursor( "Scholarship" );
        rc = schol.deleteEntity( CursorPosition.NEXT );
        assertEquals( rc, CursorResult.SET );

        // Recreate the OI so we can try more deletes.
        createTestOI();
        mFASrc.reset();
        schol = mFASrc.cursor( "Scholarship" );
        rc = schol.setLast();
        rc = schol.deleteEntity( CursorPosition.LAST );
        id = schol.getAttribute( "ID" ).getString();
        assertEquals( rc, CursorResult.SET );
        rc = schol.deleteEntity( CursorPosition.LAST );
        assertEquals( rc, CursorResult.UNCHANGED );
    }

    /**
     * Test method for {@link com.quinsoft.zeidon.View#getLodDef()}.
     */
    @Test
    public void testGetLodDef()
    {
        for ( Application app : oe.getApplicationList() )
        {
            oe.getSystemTask().log().debug(  "App '%s'  ObjDir = %s", app.getName(), app.getObjectDir() );
        }

        mFASrc = zencas.activateEmptyObjectInstance( "mFASrc" );
        mFASrc.getLodDef().displayLodDef( mFASrc );
        createTestOI();
        assertEquals( "Wrong LodDef name", mFASrc.getLodDef().getName(), "mFASrc" );
    }

    /**
     * Test method for {@link com.quinsoft.zeidon.View#eachEntity(java.lang.String)}.
     */
    @Test
    public void testEntities()
    {
        createTestOI();

        int entityCount = 0;
        for ( EntityInstance ei : mFASrc.getHierEntityList() )
        {
            assert ei != null;
            entityCount++;
        }
        assertEquals("Wrong number of entities via iterator", 7, entityCount);

        entityCount = 0;
        for ( CursorResult result = mFASrc.cursor( "FinAidSource" ).setFirst();
              result.isSet();
              result = mFASrc.cursor( "FinAidSource" ).setNext() )
        {
            entityCount++;
            for ( result = mFASrc.cursor( "Scholarship" ).setFirst();
                  result.isSet();
                  result = mFASrc.cursor( "Scholarship" ).setNext() )
            {
                entityCount++;
                mFASrc.log().debug( "Attribute value = %s", mFASrc.cursor( "Scholarship" ).getAttribute( "ID" ).getString() );
                for ( result = mFASrc.cursor( "Fund" ).setFirst();
                      result.isSet();
                      result = mFASrc.cursor( "Fund" ).setNext() )
                {
                    entityCount++;
                }
            }
        }
        assertEquals("Wrong number of entities via setFirst/Next", entityCount, 7);

        entityCount = 0;
        for ( CursorResult result = mFASrc.cursor( "Scholarship" ).setFirst( "FinAidSource" );
              result.isSet();
              result = mFASrc.cursor( "Scholarship" ).setNext( "FinAidSource" ) )
        {
            entityCount++;
            mFASrc.log().debug( "Attribute value = %s", mFASrc.cursor( "Scholarship" ).getAttribute( "ID" ).getString() );
            for ( result = mFASrc.cursor( "Fund" ).setFirst();
                  result.isSet();
                  result = mFASrc.cursor( "Fund" ).setNext() )
            {
                entityCount++;
            }
        }
        assertEquals("Wrong number of entities via setFirst/Next", entityCount, 6);

        // Verify that setFirst with arguments works.
        mFASrc.cursor( "FinAidSource" ).setFirst();
        assertEquals("Error in setFirst", mFASrc.cursor( "Scholarship" ).setFirst("ID", 1), CursorResult.SET);
        assertEquals("Error in setFirst", mFASrc.cursor( "Scholarship" ).getAttribute("ID").getInteger(), (Integer) 1);
        assertEquals("Error in setFirst", mFASrc.cursor( "Fund" ).setFirst("Name", "Fund1"), CursorResult.SET);
        assertEquals("Error in setFirst", mFASrc.cursor( "Fund" ).getAttribute("Name").getString(), "Fund1");

        for ( EntityInstance finAid : mFASrc.cursor( "FinAidSource" ).eachEntity() )
        {
            assert finAid != null;
            for ( EntityInstance scholarship : mFASrc.cursor( "Scholarship" ).eachEntity() )
            {
                mFASrc.log().debug( "Attribute value = %s", scholarship.getAttribute( "ID" ).getString() );
                for ( EntityInstance fund : mFASrc.cursor( "Fund" ).eachEntity() )
                {
                    mFASrc.log().debug( fund.getAttribute( "Name" ).getString() );
                }
            }
        }

        mFASrc.logObjectInstance();

        View view2 = zencas.activateEmptyObjectInstance( "mFASrc" );
        view2.cursor( "FinAidSource" ).createEntity().getAttribute( "ID" ).setValue(  99  ) ;
        view2.cursor( "Scholarship" ).includeSubobject( mFASrc.cursor( "Scholarship" ), CursorPosition.FIRST );
        view2.logObjectInstance();
    }

    @Test
    public void testDomains()
    {
        createTestOI();
        
        // Date domain
        mFASrc.cursor( "Fund" ).getAttribute( "FullyEndowedDate").setValue( "03/11/2020" ) ;
        String dateString = mFASrc.cursor( "Fund" ).getAttribute( "FullyEndowedDate" ).getString(null);
        assertTrue( "Date domain mismatch", dateString.equals( "03/11/2020" ) );

        // Static table domain.
        mFASrc.cursor( "FinAidSource" ).getAttribute( "AidType").setValue( "G" ) ;
        String value = mFASrc.cursor( "FinAidSource" ).getAttribute( "AidType" ).getString();
        assertEquals( "Table domain value failed", value, "G" );
        value = mFASrc.cursor( "FinAidSource" ).getAttribute( "AidType" ).getString( "" );
        assertEquals( "Table domain value failed", value, "Gift Aid" );

        // Email domain
        String email = "test@testing.com";
        mFASrc.cursor( "EmailPerson" ).createEntity().getAttribute( "eMailAddress").setValue( email ) ;
        String email2 = mFASrc.cursor( "EmailPerson" ).getAttribute( "eMailAddress" ).getString();
        assertEquals( email, email2 );

        // DoubleDomain
        mFASrc.cursor( "FinAidSource" ).getAttribute( "wTotalNotAcceptedActiveStudents").setValue( "12.0" ) ;
        try
        {
            mFASrc.cursor( "FinAidSource" ).getAttribute( "wTotalNotAcceptedActiveStudents").setValue( "12%" ) ;
            fail( "Didn't get expected Invalid attribute exception" );
        }
        catch ( InvalidAttributeValueException e )
        {
            // We expect this exception so just ignore it.
            System.out.println( "Got expected exception:\n" + e.toString() );
        }
    }

    @Test
    public void testCursors()
    {
        createTestOI();

        int count = 0;
        for ( CursorResult result = mFASrc.cursor( "Fund" ).setFirst("FinAidSource");
              result.isSet();
              result = mFASrc.cursor( "Fund" ).setNextContinue() )
        {
            count++;
            System.out.println( "Fund = " + mFASrc.cursor( "Fund" ).toString() );
        }

        assertEquals( "Scoping cursors failed", 4, count );

        //
        // Test setting cursor by entity key.
        //
        // Set cursor to last Fund
        mFASrc.cursor( "FinAidSource" ).setLast();
        mFASrc.cursor( "Scholarship" ).setLast();
        mFASrc.cursor( "Fund" ).setLast();

        // Get Fund.ID
        Integer id1 = mFASrc.cursor( "Fund" ).getAttribute( "ID" ).getInteger();
        long key = mFASrc.cursor( "Fund" ).getEntityKey();

        // Set cursor to be a new Fund.
        mFASrc.cursor( "Fund" ).setFirst();

        CursorResult rc = mFASrc.cursor( "Fund" ).setByEntityKey( key );
        assertEquals( "Setting cursor by entity key didn't change the parent.",
                       rc, CursorResult.SET );
        Integer id2 = mFASrc.cursor( "Fund" ).getAttribute( "ID" ).getInteger();
        assertEquals( "Setting cursor by entity key didn't change the cursor.", id1, id2 );

        rc = mFASrc.cursor( "Scholarship" ).setPosition( 1 );
        assertEquals( "setPosition didn't return correct value", rc, CursorResult.SET );
        assertEquals( "Set position didn't work", mFASrc.cursor( "Scholarship" ).getAttribute( "ID" ).getInteger(), (Object) 1 );
        mFASrc.cursor( "Scholarship" ).setPosition( 0 );
        assertEquals( "Set position didn't work", mFASrc.cursor( "Scholarship" ).getAttribute( "ID" ).getInteger(), (Object) 0 );
        assertEquals( "Set position didn't return null", mFASrc.cursor( "Scholarship" ).setPosition( 999 ), CursorResult.NULL );
    }

    @Test
    public void testActivateOiFromOi()
    {
        createTestOI();

        mFASrc.cursor( "Scholarship" ).setFirst();
        View temp = mFASrc.newView();
        temp.cursor( "Scholarship" ).setNext();
        System.out.println("===== source ========");
        mFASrc.logObjectInstance();
        temp = mFASrc.activateOiFromOi( ActivateFlags.fSINGLE );
        System.out.println("==== target =========");
        temp.logObjectInstance();
    }

    @Test
    public void testCopyCursors()
    {
        createTestOI();

        mFASrc.cursor( "Scholarship" ).setFirst();
        View newView = mFASrc.newView();
        String id = mFASrc.cursor( "Scholarship" ).getAttribute( "ID" ).getString();
        mFASrc.cursor( "Scholarship" ).setNext();
        newView.copyCursors( mFASrc );
        assertTrue( "copyCursors() didn't set cursors correctly", mFASrc.cursor( "Scholarship" ).getAttribute( "ID").compare( id )  != 0 );
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
    public void testSort()
    {
        createTestOI();
        EntityCursor cursor = mFASrc.cursor( "Scholarship" );
        cursor.orderEntities( "ID D" );

        System.out.println("===== descending ========");
        mFASrc.logObjectInstance();
        cursor.setFirst();
        EntityInstance e1 = cursor.getEntityInstance();
        cursor.setNext();
        EntityInstance e2 = cursor.getEntityInstance();
        assertTrue("Entity ordering failed", e2.getAttribute( "ID").compare( e1.getAttribute( "ID" ).getValue() ) == -1 );

        // Now reverse the order.
        cursor.orderEntities( "ID A" );

        System.out.println("===== ascending ========");
        mFASrc.logObjectInstance();
        cursor.setFirst();
        e1 = cursor.getEntityInstance();
        cursor.setNext();
        e2 = cursor.getEntityInstance();
        assertTrue("Entity ordering failed", e2.getAttribute( "ID").compare( e1.getAttribute( "ID" ).getValue() ) == 1 );
    }

    @Test
    public void testValues()
    {
        createTestOI();
        EntityCursor finAid = mFASrc.cursor( "FinAidSource" );

        finAid.getAttribute( "wTotalNotAcceptedActiveStudents").setValue( "1231234.56789" ) ;
        String str = finAid.getAttribute( "wTotalNotAcceptedActiveStudents" ).getString( null );
        assertEquals( "Default decimal context failed", str, "1,231,234.57" ); // Gets rounded up.
        str = finAid.getAttribute( "wTotalNotAcceptedActiveStudents" ).getString();
        assertEquals( "Default decimal context failed", str, "1231234.56789" );

        try
        {
            finAid.getAttribute( "wTotalNotAcceptedActiveStudents").setValue( "123a.00" ) ;
            fail( "Didn't get expected Invalid attribute exception" );
        }
        catch ( InvalidAttributeValueException e )
        {
            // We expect this exception so just ignore it.
            System.out.println( "Got expected exception:\n" + e.toString() );
        }

        System.out.println( str );
    }

    @Test
    public void testMoveSubobject()
    {
        View view = createTestOI2();
        View v2 = view.newView();
        EntityCursor sch  = view.cursor( "Scholarship" );
        EntityCursor sch2 = v2.cursor( "Scholarship" );
        sch.setPosition( 4 );
//        String s4 = sch.getAttribute( "ID" ).getString();
        sch2.setPosition( 6 );
        String s6 = sch2.getAttribute( "ID" ).getString();
        sch.moveSubobject( CursorPosition.NEXT, sch2, CursorPosition.NEXT );

        sch.setPosition( 4 );
//        String s4_2 = sch.getAttribute( "ID" ).getString();
        sch.setNext();
        String s5 = sch.getAttribute( "ID" ).getString();
        assertEquals( "Move entity didn't move source", s6, s5 );
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
    public void testDynamicAttributes()
    {
        createTestOI();
        try
        {
            AttributeInstance attr = mFASrc.cursor( "FinAidSource" ).getAttribute( "TestDyn" );
            assertTrue( false );
        }
        catch ( UnknownAttributeDefException e )
        {
            // If we get here everything is as expected.
        }

        DynamicAttributeDefConfiguration config = new DynamicAttributeDefConfiguration();
        config.setAttributeName( "TestDyn" );
        mFASrc.cursor( "FinAidSource" ).createDynamicAttributeDef( config  );
        AttributeInstance attr = mFASrc.cursor( "FinAidSource" ).getAttribute( "TestDyn" );
        attr.setValue( "This is a test" );

        attr = mFASrc.cursor( "FinAidSource" ).getAttribute( "TestDyn" );
        String value = attr.getString();
        assertTrue( "Unexpected dynamic attribute value",  "This is a test".equals( value ) );

        String filename = mFASrc.getTempDirectory() + "dynamictest.por";
        mFASrc.writeOiToFile( filename, null );

        try
        {
            View v = new DeserializeOi( mFASrc )
                                .fromFile( "target/test-classes/testdata/ZENCAs/lStudDpt-dynamictest.json" )
                                .asJson()
                                .activateFirst();

            assertTrue( "Didn't get expected Exception", false );
        }
        catch ( ZeidonException e )
        {
            mFASrc.log().debug( "Got expected Exception" );
        }

        View v = new DeserializeOi( mFASrc )
                            .fromFile( "target/test-classes/testdata/ZENCAs/lStudDpt-dynamictest.json" )
                            .allowDynamicAttributesFor( "Student" )
                            .activateFirst();

        value = v.cursor( "Student" ).getAttribute( "DynamicAttr" ).getString();
        assertTrue( "Unexpected dynamic attribute value",  "This is a test".equals( value ) );
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