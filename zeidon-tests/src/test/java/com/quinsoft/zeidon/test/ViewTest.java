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
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.UnknownAttributeDefException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.WriteOiFlags;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.DynamicAttributeDefConfiguration;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.utils.ZeidonInputStream;

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
        mFASrc = zencas.activateEmptyObjectInstance( "mFASrc" );
        mFASrc.cursor( "FinAidSource" ).createEntity();
        for ( int i = 0; i < 2; i++ )
        {
            mFASrc.cursor( "Scholarship" ).createEntity(CursorPosition.NEXT,
                                                        EnumSet.of( CreateEntityFlags.fIGNORE_MAX_CARDINALITY, CreateEntityFlags.fIGNORE_PERMISSIONS) );
            mFASrc.cursor( "Scholarship" ).setAttribute( "ID" ,  i  );
            mFASrc.cursor( "Scholarship" ).setAttribute( "Name" ,  "ScholarshipName" + i  );
            for ( int j = 1; j < 3; j++ )
            {
                mFASrc.cursor( "Fund" ).createEntity( CursorPosition.NEXT, EnumSet.of( CreateEntityFlags.fIGNORE_MAX_CARDINALITY, CreateEntityFlags.fIGNORE_PERMISSIONS ) )
                                       .setAttribute( "Name" ,  "Fund" + Integer.toString( j  ) )
                                       .setAttribute( "ID" ,  (100 * j) + i  );
            }
        }
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
        mFASrc = zencas.activateEmptyObjectInstance( "mFASrc" );
        mFASrc.cursor( "FinAidSource" ).createEntity();
        for ( int i = 0; i < 10; i++ )
        {
            mFASrc.cursor( "Scholarship" ).createEntity(CursorPosition.NEXT, EnumSet.of( CreateEntityFlags.fIGNORE_MAX_CARDINALITY, CreateEntityFlags.fIGNORE_PERMISSIONS) );
            mFASrc.cursor( "Scholarship" ).setAttribute( "ID" ,  i  );
            mFASrc.cursor( "Scholarship" ).setAttribute( "Name" ,  "ScholarshipName" + i  );
            for ( int j = 1; j < 4; j++ )
            {
                mFASrc.cursor( "Fund" ).createEntity(CursorPosition.NEXT, EnumSet.of( CreateEntityFlags.fIGNORE_MAX_CARDINALITY, CreateEntityFlags.fIGNORE_PERMISSIONS ) )
                                       .setAttribute( "Name" ,  "Fund" + Integer.toString( j  ) )
                                       .setAttribute( "ID" ,  (100 * j) + i  );
            }
        }
        return mFASrc;
    }

//    @Test
//    public void testDynamicDomains()
//    {
//        View student = zencas.activateEmptyObjectInstance( "mPerson" );
//        student.cursor( "Person" ).createEntity();
//        student.cursor( "Prospect" ).createEntity()
//                                    .setAttribute( "AcceptanceStatus", "xx" );
//    }

    String jsonLabel = "{ \".oimeta\" : { \"application\" : \"epamms\", \"odName\" : \"mLLD\", \"incremental\" : \"true\" }," +
    	"\"LLD\" : [ { \".meta\" : { \"created\" : \"true\" }, \"Name\" : \"Drop area ...\" , " +
    	"\"Panel\" : [ { \".meta\" : { \"created\" : \"true\" }, \"Order\" : \"1\" , \"Tag\" : \"panel1\" , \"Top\" : \"0px\", \"Left\" : \"0px\", \"Height\" : \"600px\", \"Width\" : \"650px\", \"Level\" : \"0\" , " +
    	"\"Block\" : [ { \".meta\" : { \"created\" : \"true\" } , \"Top\" : \"143px\", \"Left\" : \"179px\", \"Height\" : \"317px\", \"Width\" : \"311px\", \"Tag\" : \"Tag100\" , \"Level\" : \"1\" , " +
    	"\"Block\" : [ { \".meta\" : { \"created\" : \"true\" } , \"Top\" : \"100px\", \"Left\" : \"98px\", \"Height\" : \"100px\", \"Width\" : \"100px\", \"Tag\" : \"Tag101\" , \"Level\" : \"2\"  } ] } ]}, " +
    	"{ \".meta\" : { \"created\" : \"true\" }, \"Order\" : \"2\" , \"Tag\" : \"panel2\" , \"Top\" : \"0px\", \"Left\" : \"0px\", \"Height\" : \"600px\", \"Width\" : \"650px\", \"Level\" : \"0\" }, " +
    	"{ \".meta\" : { \"created\" : \"true\" }, \"Order\" : \"9\" , \"Tag\" : \"panel9\" , \"Top\" : \"0px\", \"Left\" : \"0px\", \"Height\" : \"600px\", \"Width\" : \"650px\", \"Level\" : \"0\"  } ] } ]}";
    @Test
    public void testJson() throws Exception
    {
        View v = zencas.activateOiFromFile( "mStudent", zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mstudent_ac.por" );
        String filename = v.getTempDirectory() + "mstudent_ac.json";
        v.serializeOi().asJson().withIncremental().toFile( filename );

        View v2 = new DeserializeOi( zencas )
                            .asJson()
                            .fromResource( filename )
                            .activateFirst();
        filename = v.getTempDirectory() + "mstudent_ac.por";
        v2.writeOiToFile( filename, null );

        filename = zeidonSystem.getObjectEngine().getHomeDirectory() + "/ePamms/OIs/mlld.json";
        try {
            Task epamms = oe.createTask( "ePamms" );
            View v3 = new DeserializeOi( epamms )
                                    .asJson()
                                    .fromResource( filename )
                                    .activateFirst();
            v3.logObjectInstance();
            EntityCursor block = v3.cursor( "Block" );
            CursorResult r = block.setFirst();
            assertEquals( "Invalid OI from Json - Block not found", r, CursorResult.SET );
            assertEquals( "Unexpected attribute value", block.getAttribute( "Tag" ).getString(), "Tag100" );
            r = block.setNext();
            assertEquals( "Invalid OI from Json - Block should be child, not twin", r, CursorResult.SET );
            r = block.setNext();
            assertEquals( "Invalid OI from Json - Block should be child, not twin", r, CursorResult.UNCHANGED );
         } catch( ZeidonException ze ) {
            assertEquals( "Error processing Json Label: " + ze.getMessage(), 0, 1 );
         }

    }

    @Test
    public void testCommitToFile()
    {
        createTestOI();
        String filename = mFASrc.getTempDirectory() + "mfasrc.por";
        String attrvalue = "This is line 1\r\nThis is line2";
        mFASrc.cursor( "FinAidSource" ).setAttribute( "SourceFootnote", attrvalue );
        mFASrc.writeOiToFile( filename, EnumSet.of( WriteOiFlags.INCREMENTAL ) );
        View v2 = zencas.activateOiFromFile( "mFASrc", filename );
        String str = v2.cursor( "FinAidSource" ).getStringFromAttribute( "SourceFootnote" );
        assertEquals( "Multi-line attribute value fails comparison", attrvalue, str );
    }

    @Test
    public void testSubobject() throws IOException
    {
        String oldfile = zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mRptStrD.XWD";
        String newfile = zeidonSystem.getTempDirectory() + "testxwd.XWD";
        LodDef xwdOD = zeidonSystem.getApplication( "ZeidonTools" ).getLodDef( zencas, "kzwdlgxo" );
        View xwd;

        xwd = zeidonSystem.activateEmptyObjectInstance( xwdOD );
        xwd.cursor( "Dlg" ).createEntity();
        xwd.cursor( "Wnd" ).createEntity();
        xwd.cursor( "Ctrl" ).createEntity().setAttribute( "Tag", "Ctrl1" );
        EntityCursor ctrl  = xwd.cursor("Ctrl" );
        EntityCursor cctrl = xwd.cursor("CtrlCtrl" );
        cctrl.createEntity().setAttribute( "Tag", "Ctrl2" );
        cctrl.setToSubobject();
        cctrl.createEntity().setAttribute( "Tag", "Ctrl3" );
        cctrl.setToSubobject();
        cctrl.createEntity().setAttribute( "Tag", "Ctrl4" );
        assertEquals( "Subobject Ctrl has wrong value ",     ctrl.getStringFromAttribute( "Tag" ), "Ctrl3" );
        assertEquals( "Subobject CtrlCtrl has wrong value ", cctrl.getStringFromAttribute( "Tag" ), "Ctrl4" );
        xwd.logObjectInstance();
        xwd.cursor( "CtrlCtrl" ).resetSubobjectToParent();
        assertEquals( "Subobject Ctrl has wrong value ",     ctrl.getStringFromAttribute( "Tag" ), "Ctrl2" );
        assertEquals( "Subobject CtrlCtrl has wrong value ", cctrl.getStringFromAttribute( "Tag" ), "Ctrl3" );
        xwd.cursor( "CtrlCtrl" ).resetSubobjectToParent();
        assertEquals( "Subobject Ctrl has wrong value ",     ctrl.getStringFromAttribute( "Tag" ), "Ctrl1" );
        assertEquals( "Subobject CtrlCtrl has wrong value ", cctrl.getStringFromAttribute( "Tag" ), "Ctrl2" );

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
        View xwd = zencas.activateOiFromFile( xwdOD, zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mRptStrD.XWD", null );
        EntityCursor ctrl = xwd.cursor( "Ctrl" );
        EntityCursor ctrlMap = xwd.cursor( "CtrlMap" );

        // Get current value.
        Integer originalY = ctrl.getIntegerFromAttribute( "PSDLG_Y" );

        //
        // Create and cancel
        //

        // Create temporal subobject and verify that the attr values is still the same.
        ctrl.createTemporalSubobjectVersion();
        Integer newY = ctrl.getIntegerFromAttribute( "PSDLG_Y" );
        assertEquals( "CreateTemporal isn't working", newY, originalY );
        asserhttp://www.google.com/tTrue( "Tag should still be null", ctrlMap.isAttributeNull( "Tag" ) );

        // Add one to the value and verify that it got set correctly.
        ctrl.setAttribute( "PSDLG_Y", originalY + 1 );
        newY = ctrl.getIntegerFromAttribute( "PSDLG_Y" );
        assertEquals( "Versioned instance values aren't working", (Integer) ( newY - 1 ), originalY );

        ctrlMap.setAttribute( "Tag", "TestTag" );
        assertEquals( "CtrlMap.Tag wasn't set correctly", ctrlMap.getStringFromAttribute( "Tag" ), "TestTag" );

        // Cancel the subobject and verify that we have the old value.
        ctrl.cancelSubobject();
        newY = ctrl.getIntegerFromAttribute( "PSDLG_Y" );
        assertEquals( "Cancel version isn't working", newY, originalY );
        assertTrue( "Tag should still be null", ctrlMap.isAttributeNull( "Tag" ) );

        //
        // Create and accept
        //

        // Create temporal subobject (again) and verify that the attr values is still the same.
        ctrl.createTemporalSubobjectVersion();
        newY = ctrl.getIntegerFromAttribute( "PSDLG_Y" );
        assertEquals( "Create Temporal isn't working", newY, originalY );
        assertTrue( "Tag should still be null", ctrlMap.isAttributeNull( "Tag" ) );

        // Add one to the value and verify that it got set correctly.
        ctrl.setAttribute( "PSDLG_Y", originalY + 1 );
        newY = ctrl.getIntegerFromAttribute( "PSDLG_Y" );
        assertEquals( "Versioned instance values aren't working", (Integer) ( newY - 1 ), originalY );

        ctrlMap.setAttribute( "Tag", "TestTag2" );
        assertEquals( "CtrlMap.Tag wasn't set correctly", ctrlMap.getStringFromAttribute( "Tag" ), "TestTag2" );

        ctrl.acceptSubobject();
        newY = ctrl.getIntegerFromAttribute( "PSDLG_Y" );
        assertEquals( "Versioned instance values aren't working", (Integer) ( newY - 1 ), originalY );
        assertEquals( "CtrlMap.Tag wasn't set correctly", ctrlMap.getStringFromAttribute( "Tag" ), "TestTag2" );

        //
        // Test with linked entities.
        //

        // Link them.
        xwd = zencas.activateOiFromFile( xwdOD, zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mRptStrD.XWD", null );
        View xwd2 = xwd.newView();
        xwd.cursor( "Ctrl" ).setFirst();
        xwd2.cursor( "Ctrl" ).setFirst();
        xwd2.cursor( "Ctrl" ).setNext();
        xwd2.cursor( "Ctrl" ).setAttributeFromAttribute( "Tag", xwd, "Ctrl", "Tag" );
        xwd2.cursor( "Ctrl" ).linkInstances( xwd.cursor( "Ctrl" ) );

        // Verify they have the same attribute value.
        Integer y  = xwd.cursor( "Ctrl" ).getIntegerFromAttribute( "PSDLG_Y" );
        Integer y2 = xwd2.cursor( "Ctrl" ).getIntegerFromAttribute( "PSDLG_Y" );
        assertEquals("linkInstances didn't appear to work", y, y2 );

        // Change the value for one entity and make sure they're still the same.
        xwd.cursor( "Ctrl" ).setAttribute( "PSDLG_Y", y + 1 );
        y  = xwd.cursor( "Ctrl" ).getIntegerFromAttribute( "PSDLG_Y" );
        y2 = xwd2.cursor( "Ctrl" ).getIntegerFromAttribute( "PSDLG_Y" );
        assertEquals("linkInstances didn't appear to work", y, y2 );

        // Create temporal for one of the linked instances.  Make sure both don't get changed.
        xwd.cursor( "Ctrl" ).createTemporalSubobjectVersion();
        xwd.cursor( "Ctrl" ).setAttribute( "PSDLG_Y", y + 1 );
        y  = xwd.cursor( "Ctrl" ).getIntegerFromAttribute( "PSDLG_Y" );
        y2 = xwd2.cursor( "Ctrl" ).getIntegerFromAttribute( "PSDLG_Y" );
        assertTrue("Changing a temporal value changes linked values.", y != y2 );

        // Accept the subobject and make sure the attributes are now the same.
        xwd.cursor( "Ctrl" ).acceptSubobject();
        y2 = xwd2.cursor( "Ctrl" ).getIntegerFromAttribute( "PSDLG_Y" );
        assertEquals("linkInstances didn't appear to work", y, y2 );
        newY  = xwd.cursor( "Ctrl" ).getIntegerFromAttribute( "PSDLG_Y" );
        assertEquals("AcceptSubobject changed a value", y, newY );

        // Change the value one last time and make sure they're still the same.
        xwd.cursor( "Ctrl" ).setAttribute( "PSDLG_Y", newY + 1 );
        y  = xwd.cursor( "Ctrl" ).getIntegerFromAttribute( "PSDLG_Y" );
        y2 = xwd2.cursor( "Ctrl" ).getIntegerFromAttribute( "PSDLG_Y" );
        assertEquals("Linked, accepted instances don't appear to be linked.", y, y2 );

        //
        // Temporal entities.
        //
        EntityCursor wnd = xwd.cursor( "Wnd" );
        wnd.createTemporalEntity( CursorPosition.LAST );
        wnd.setAttribute( "Caption", "testvalue" );
        wnd.cancelTemporalEntity();

        wnd.createTemporalEntity( CursorPosition.LAST );
        wnd.setAttribute( "Caption", "testvalue" );
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

        View view = zencas.activateOiFromFile( "mEmploy", zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mEmploy2.txt", ActivateFlags.SINGLE );
        CursorResult rc = view.cursor( "HistDeptContractDistribution" ).setFirst( "Employee" );
        String id = view.cursor( "HistDeptContractDistribution" ).getStringFromAttribute( "ID" );
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
        schol.createEntity( CursorPosition.LAST, EnumSet.of( CreateEntityFlags.fIGNORE_MAX_CARDINALITY, CreateEntityFlags.fIGNORE_PERMISSIONS )).setAttribute( "ID" ,  2  );

        // Position on the second entity and delete it.
        schol.setFirst();
        schol.setNext();
        CursorResult rc = schol.deleteEntity( CursorPosition.NONE );
        assertEquals( "Delete returned wrong return code", rc, CursorResult.UNCHANGED );
        rc = schol.setNext();
        assertEquals( "Delete didn't set cursor correctly", rc, CursorResult.SET );
        String id = schol.getStringFromAttribute( "ID" );
        assertEquals( "Delete didn't set cursor correctly", id, "2" );

        rc = schol.setNext();
        assertTrue( ! rc.isSet() );
        Integer i = schol.getIntegerFromAttribute( "ID" );
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
        id = schol.getStringFromAttribute( "ID" );
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
                mFASrc.log().debug( "Attribute value = %s", mFASrc.cursor( "Scholarship" ).getStringFromAttribute( "ID" ) );
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
            mFASrc.log().debug( "Attribute value = %s", mFASrc.cursor( "Scholarship" ).getStringFromAttribute( "ID" ) );
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
        assertEquals("Error in setFirst", mFASrc.cursor( "Scholarship" ).getIntegerFromAttribute("ID"), (Integer) 1);
        assertEquals("Error in setFirst", mFASrc.cursor( "Fund" ).setFirst("Name", "Fund1"), CursorResult.SET);
        assertEquals("Error in setFirst", mFASrc.cursor( "Fund" ).getStringFromAttribute("Name"), "Fund1");

        for ( EntityInstance finAid : mFASrc.cursor( "FinAidSource" ).eachEntity() )
        {
            assert finAid != null;
            for ( EntityInstance scholarship : mFASrc.cursor( "Scholarship" ).eachEntity() )
            {
                mFASrc.log().debug( "Attribute value = %s", scholarship.getStringFromAttribute( "ID" ) );
                for ( EntityInstance fund : mFASrc.cursor( "Fund" ).eachEntity() )
                {
                    mFASrc.log().debug( fund.getStringFromAttribute( "Name" ) );
                }
            }
        }

        mFASrc.logObjectInstance();

        View view2 = zencas.activateEmptyObjectInstance( "mFASrc" );
        view2.cursor( "FinAidSource" ).createEntity().setAttribute( "ID" ,  99  );
        view2.cursor( "Scholarship" ).includeSubobject( mFASrc.cursor( "Scholarship" ), CursorPosition.FIRST );
        view2.logObjectInstance();
    }

    @Test
    public void testDomains()
    {
        createTestOI();

        // Date domain
        mFASrc.cursor( "Fund" ).setAttribute( "FullyEndowedDate", "03/11/2020" );
        String dateString = mFASrc.cursor( "Fund" ).getStringFromAttribute( "FullyEndowedDate", null );
        assertTrue( "Date domain mismatch", dateString.equals( "03/11/2020" ) );

        // Static table domain.
        mFASrc.cursor( "FinAidSource" ).setAttribute( "AidType", "G" );
        String value = mFASrc.cursor( "FinAidSource" ).getStringFromAttribute( "AidType" );
        assertEquals( "Table domain value failed", value, "G" );
        value = mFASrc.cursor( "FinAidSource" ).getStringFromAttribute( "AidType", "" );
        assertEquals( "Table domain value failed", value, "Gift Aid" );

        // Email domain
        String email = "test@testing.com";
        mFASrc.cursor( "EmailPerson" ).createEntity().setAttribute( "eMailAddress", email );
        String email2 = mFASrc.cursor( "EmailPerson" ).getStringFromAttribute( "eMailAddress" );
        assertEquals( email, email2 );

        // DoubleDomain
        mFASrc.cursor( "FinAidSource" ).setAttribute( "wTotalNotAcceptedActiveStudents", "12.0" );
        try
        {
            mFASrc.cursor( "FinAidSource" ).setAttribute( "wTotalNotAcceptedActiveStudents", "12%" );
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
        Integer id1 = mFASrc.cursor( "Fund" ).getIntegerFromAttribute( "ID" );
        long key = mFASrc.cursor( "Fund" ).getEntityKey();

        // Set cursor to be a new Fund.
        mFASrc.cursor( "Fund" ).setFirst();

        CursorResult rc = mFASrc.cursor( "Fund" ).setByEntityKey( key );
        assertEquals( "Setting cursor by entity key didn't change the parent.",
                       rc, CursorResult.SET );
        Integer id2 = mFASrc.cursor( "Fund" ).getIntegerFromAttribute( "ID" );
        assertEquals( "Setting cursor by entity key didn't change the cursor.", id1, id2 );

        rc = mFASrc.cursor( "Scholarship" ).setPosition( 1 );
        assertEquals( "setPosition didn't return correct value", rc, CursorResult.SET );
        assertEquals( "Set position didn't work", mFASrc.cursor( "Scholarship" ).getIntegerFromAttribute( "ID" ), (Object) 1 );
        mFASrc.cursor( "Scholarship" ).setPosition( 0 );
        assertEquals( "Set position didn't work", mFASrc.cursor( "Scholarship" ).getIntegerFromAttribute( "ID" ), (Object) 0 );
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
        String id = mFASrc.cursor( "Scholarship" ).getStringFromAttribute( "ID" );
        mFASrc.cursor( "Scholarship" ).setNext();
        newView.copyCursors( mFASrc );
        assertTrue( "copyCursors() didn't set cursors correctly", mFASrc.cursor( "Scholarship" ).compareAttribute( "ID", id ) != 0 );
    }

    @Test
    public void testActivateFromFile()
    {
        View view = zeidonTools.activateEmptyObjectInstance( "kzwdlgxo" );
        view.logObjectInstance();

        view = zeidonTools.deserializeOi()
                          .fromFile( zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mRptStrD.XWD" )
                          .setApplication( zeidonTools.getApplication() )
                          .activateFirst();
        view.logObjectInstance();

        // Activate a multiple-root object with SINGLE and make sure there's only one root.
        view = zencas.activateOiFromFile( "mEmploy", zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/multipleRoots.txt", ActivateFlags.SINGLE );
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
        assertTrue("Entity ordering failed", e2.compareAttribute( "ID", e1, "ID" ) == -1 );

        // Now reverse the order.
        cursor.orderEntities( "ID A" );

        System.out.println("===== ascending ========");
        mFASrc.logObjectInstance();
        cursor.setFirst();
        e1 = cursor.getEntityInstance();
        cursor.setNext();
        e2 = cursor.getEntityInstance();
        assertTrue("Entity ordering failed", e2.compareAttribute( "ID", e1, "ID" ) == 1 );
    }

    @Test
    public void testValues()
    {
        createTestOI();
        EntityCursor finAid = mFASrc.cursor( "FinAidSource" );

        finAid.setAttribute( "wTotalNotAcceptedActiveStudents", "1231234.56789" );
        String str = finAid.getStringFromAttribute( "wTotalNotAcceptedActiveStudents", null );
        assertEquals( "Default decimal context failed", str, "1,231,234.57" ); // Gets rounded up.
        str = finAid.getStringFromAttribute( "wTotalNotAcceptedActiveStudents" );
        assertEquals( "Default decimal context failed", str, "1231234.56789" );

        try
        {
            finAid.setAttribute( "wTotalNotAcceptedActiveStudents", "123a.00" );
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
//        String s4 = sch.getStringFromAttribute( "ID" );
        sch2.setPosition( 6 );
        String s6 = sch2.getStringFromAttribute( "ID" );
        sch.moveSubobject( CursorPosition.NEXT, sch2, CursorPosition.NEXT );

        sch.setPosition( 4 );
//        String s4_2 = sch.getStringFromAttribute( "ID" );
        sch.setNext();
        String s5 = sch.getStringFromAttribute( "ID" );
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
//        view.cursor( "Session" ).createEntity().setAttribute( "UserID", "Test user id" );
        View wXferO = zencas.activateEmptyObjectInstance( "wXferO" );
        wXferO.cursor( "Root" ).createEntity();
        View mYearLST = zencas.activateEmptyObjectInstance( "mYear" );
        String   szTempString_0 = null;
        int      lTempInteger_0 = 0;
        CursorResult  RESULT;

        mYearLST = zencas.activateOiFromFile( "mYear", zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mYearLST.por", ActivateFlags.IGNORE_ATTRIB_ERRORS );
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
        mStudenC = zencas.activateOiFromFile( "mStudenC", zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mstudencsqlOI.por", ActivateFlags.IGNORE_ATTRIB_ERRORS );
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
        mStudenC = zencas.activateOiFromFile( "mStudenC", zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mStudenCWithin.por", ActivateFlags.IGNORE_ATTRIB_ERRORS );
        mStudenC.log( ).info("*** Show mStudenC data ***");
        CursorResult RESULT = mStudenC.cursor( "RegistrationClass" ).setFirst( "Student" );
        //RESULT = mStudenC.cursor( "Registration" ).setFirst().toInt();
        while ( RESULT.isSet() )
        {
     	   //mStudenC.log( ).info(mStudenC.cursor( "Registration" ).getStringFromAttribute( "ID" ));
           System.out.println( mStudenC.cursor( "Registration" ).getStringFromAttribute( "ID" )  );
           System.out.println( mStudenC.cursor( "RegistrationClass" ).getStringFromAttribute( "ID" )  );

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
        String filename = zeidonSystem.getObjectEngine().getHomeDirectory() + "ZENCAs/ThisFileDoesntExist|" +
                          zeidonSystem.getObjectEngine().getHomeDirectory() + "ZENCAs/mStudenCWithin.por";
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
                                .fromFile( zeidonSystem.getObjectEngine().getHomeDirectory() + "ZENCAs/lStudDpt-dynamictest.json" )
                                .asJson()
                                .activateFirst();

            assertTrue( "Didn't get expected Exception", false );
        }
        catch ( ZeidonException e )
        {
            mFASrc.log().debug( "Got expected Exception" );
        }

        View v = new DeserializeOi( mFASrc )
                            .fromFile( zeidonSystem.getObjectEngine().getHomeDirectory() + "ZENCAs/lStudDpt-dynamictest.json" )
                            .allowDynamicAttributesFor( "Student" )
                            .activateFirst();

        value = v.cursor( "Student" ).getAttribute( "DynamicAttr" ).getString();
        assertTrue( "Unexpected dynamic attribute value",  "This is a test".equals( value ) );
    }
}