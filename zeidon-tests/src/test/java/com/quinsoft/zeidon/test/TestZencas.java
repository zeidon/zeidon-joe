/**
 *
 */
package com.quinsoft.zeidon.test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.Assert;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.SelectSet;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.SetMatchingFlags;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.utils.JsonUtils;
import com.quinsoft.zeidon.utils.JspWebUtils;
import com.quinsoft.zeidon.utils.QualificationBuilder;
import com.quinsoft.zeidon.utils.Timer;
import com.quinsoft.zeidon.vml.VmlOperation;
import com.quinsoft.zeidon.vml.zVIEW;
import com.quinsoft.zeidon.zeidonoperations.KZOEP1AA;
import com.quinsoft.zencas.ZGLOBAL1_Operation;
import com.quinsoft.zencas.ZGLOBAL2_Operation;
import com.quinsoft.zencas.lTrnscpt_Object;
import com.quinsoft.zencas.mStudenC_Object;

// Just for temporary testing...
//import com.jacob.com.*;
//import com.jacob.activeX.*;


/**
 * @author DG
 *
 */
public class TestZencas
{
	Task         zencas;
	Task         zeidonSystem;
	View         mFASrc;
	View         subtaskView;
	ObjectEngine oe;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
        oe = JavaObjectEngine.getInstance();
        zencas = oe.createTask( "ZENCAs" );
		zeidonSystem = oe.getSystemTask();
	}

	@Test
	public void testWritingXodAsJson() throws IOException
	{
	    Map<String, String> envs = System.getenv();
        String fileDbUrl = StrSubstitutor.replace( "jdbc:sqlite:${ZENCAS_SQLITE_DIR}/zencasa.sqlite", envs );
        View stud = new QualificationBuilder( zencas )
                        .setLodDef( "lStudDpt" )
                        .setOiSourceUrl( fileDbUrl )
                        .addAttribQual( "Status", "A" )
                        .addAttribQual( "AND" )
                        .addAttribQual( "MajorDepartment", "ID", "=", 3 )
                        .activate();

        Writer writer = null;
        try
        {
            writer = new FileWriter( getTempDir() + "/xod.json" );
            JsonUtils.writeXodToJsonStream( stud, writer );
        }
        finally
        {
            IOUtils.closeQuietly( writer );
        }
    }

	@Test
	public void testRecursion()
	{
        View tst = new QualificationBuilder( zencas )
                            .setLodDef( "TstRecur" )
                            .addAttribQual( "ID", 407 )
                            .activate();

        EntityCursor parent = tst.cursor( "FinancialApprovalRole" );
        EntityCursor child = tst.cursor( "FinancialApprovalRoleChild" );

        child.setToSubobject();
        CursorResult rc = parent.setFirst("ID", 77);
        Assert.assertEquals( "Recursive setFirst didn't set the cursor", CursorResult.SET, rc );
        Assert.assertEquals( "Bad child ID value", child.getAttribute( "ID" ).getInteger(), (Integer) 135 );
        child.setToSubobject();
        Assert.assertEquals( "Bad parent ID value", parent.getAttribute( "ID" ).getInteger(), (Integer) 135 );
        View tst2 = tst.newView();
        tst2.resetSubobjectTop();
        Assert.assertEquals( tst2.cursor( "FinancialApprovalRole" ).getAttribute( "ID" ).getInteger(), (Integer) 76 );
	}

	@Test
	public void testAttributeUpdated()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testAttributeUpdated( testview );
        System.out.println("===== Finished testAttributeUpdated ========");
	}

	/**
	 * Test password encryption.
	 */
    @Test
    public void testPassword()
    {
        String password = UUID.randomUUID().toString();

        // Activate a user and set the password.
        View mUser = new QualificationBuilder( zencas ).setLodDef( "mUser" ).addAttribQual( "ID", 490 ).activate();
        AttributeInstance attr1 = mUser.cursor( "User" ).getAttribute( "Password" );
        mUser.cursor( "User" ).getAttribute( "Password" ).setValue( password );
        System.out.println( "Hash = " + attr1.getString() );
        Assert.assertEquals( "Password doesn't match", 0, attr1.compare( password ) );
        Assert.assertEquals( "Password matches different string", 1, attr1.compare(  "abc" ) );

        // Commit the new password
        System.out.println( "Hash = " + attr1.getString() );
        mUser.commit();

        // Make sure the commit didn't change anything.
        Assert.assertEquals( "Password doesn't match", 0, attr1.compare( password ) );
        Assert.assertEquals( "Password matches different string", 1, attr1.compare(  "abc" ) );

        // Reload the user and verify the password.
        View mUser2 = new QualificationBuilder( zencas ).setLodDef( "mUser" ).addAttribQual( "ID", 490 ).activate();
        AttributeInstance attr2 = mUser2.cursor( "User" ).getAttribute( "Password" );
        Assert.assertEquals( "After commit: Password doesn't match", 0, attr2.compare( password ) );
        Assert.assertEquals( "After commit: Password matches different string", 1, attr2.compare( "abc" )  );
    }

	@Test
	// Some of the dynamic domains are dependent on the admin division.
	// That means there are more than one domain with the same name.
	// Make sure that the correct domain is retrieved.
	public void testActivateDynamicDomainAdminDivError()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testActivateDynamicDomainAdminDivError( testview );
        System.out.println("===== Finished testInclude2 ========");
	}

	@Test
	public void testClsIncludeError()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testClsIncludeError( testview );
        System.out.println("===== Finished testInclude2 ========");
	}

	@Test
	public void testActivateControls()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testActivateControls( testview );
        System.out.println("===== Finished testActivateControls ========");
	}
	@Test
	public void testCheckExistenceWithRecursiveEnt()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testCheckExistenceWithRecursiveEnt( testview );
        System.out.println("===== Finished testCheckExistenceWithRecursiveEnt ========");
	}

	@Test
	public void testZeidonStringCompare()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testZeidonStringCompare( testview );
        System.out.println("===== Finished testZeidonStringCompare ========");
	}

	@Test
	public void testRecursiveEntities()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testRecursiveEntities( testview );
        System.out.println("===== Finished testRecursiveEntities ========");
	}

	@Test
	public void testXpg()
	{
	    JspWebUtils.createWebSession( this, zencas, "123" );
	}

	@Test
	public void testJson() throws IOException
	{
        View stud = new QualificationBuilder( zencas )
                            .setLodDef( "lStudDpt" )
                            .singleRoot()
                            .addAttribQual( "Status", "A" )
                            .addAttribQual( "AND" )
                            .addAttribQual( "MajorDepartment", "ID", "=", 3 )
                            .activate();
        stud.relinkOis( (View[]) null );

        View person = new QualificationBuilder( zencas )
                            .setLodDef( "mPerson" )
                            .addAttribQual( "ID", 50 )
                            .activate();

        stud.cursor( "Student" ).getAttribute( "eMailAddress" ).setValue( "xxx@comcast.net" );

        SerializeOi options = new SerializeOi();
        options.withIncremental();
        new SerializeOi().withIncremental().addView( stud, person ).toFile( getTempDir() + "/stud.json" );

        List<View> viewList = new DeserializeOi( zencas )
                                        .asJson()
                                        .fromResource( getTempDir() + "/stud.json" )
                                        .activate();
        for ( View v : viewList )
            v.logObjectInstance();

        stud = new QualificationBuilder( zencas )
                            .setLodDef( "mCollege" )
                            .singleRoot()
                            .addAttribQual( "ID", 5 )
                            .activate();
        stud.cursor( "College" ).deleteEntity();
        stud.serializeOi().withIncremental().toFile( getTempDir() + "/mcollege.json" );

        viewList = new DeserializeOi( zencas )
                                    .asJson()
                                    .fromResource( getTempDir() + "/mcollege.json" )
                                    .activate();
        for ( View v : viewList )
        {
            v.logObjectInstance();
            v.log().info( "Entity count = %d", v.cursor( "College" ).getEntityCount() );
        }
	}

	@Test
	public void testSelectSet()
	{
        View stud = new QualificationBuilder( zencas )
                            .setLodDef( "lStudDpt" )
                            .addAttribQual( "Status", "A" )
                            .addAttribQual( "AND" )
                            .addAttribQual( "MajorDepartment", "ID", "=", 3 )
                            .activate();

        EntityCursor student = stud.cursor( "Student" );
        SelectSet selectSet = stud.getSelectSet( "test" );

        // Set some entities in the select set.
        student.setPosition( 7 );
        selectSet.select( student );
        student.setPosition( 2 );
        selectSet.select( student );
        student.setPosition( 8 );
        selectSet.select( student );

        int count = 0;
        Set<EntityInstance> set = selectSet.getSet();
        for ( EntityInstance ei : selectSet.eachEntity() )
        {
            count++;
            long p = ei.getPosition();
            Assert.assertTrue( "EI isn't in select set", set.contains( student.getEntityInstance() ) );
        }

        Assert.assertEquals( "We didn't get the right number of selected EIs", 3, count );

        // Now try with a child
        selectSet = stud.getSelectSet( "test2" );
        student.setPosition( 7 );
        EntityCursor track = stud.cursor( "StudentMajorDegreeTrack" );
        track.setPosition( 2 );
        selectSet.select( track );
        set = selectSet.getSet();
        for ( EntityInstance ei : selectSet.eachEntity() )
        {
            long p = ei.getPosition();
            Assert.assertTrue( "EI isn't in select set", set.contains( track.getEntityInstance() ) );
        }

        stud.logObjectInstance();

	}

	@Test
	public void testhasAny()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testhasAny( testview );
        System.out.println("===== Finished testhasAny ========");
	}

	@Test
	public void testInclude()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testInclude( testview );
        System.out.println("===== Finished testInclude ========");
	}

	@Test
	public void testInclude2()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testInclude2( testview );
        System.out.println("===== Finished testInclude2 ========");
	}

    @Test
    public void testInclude3()
    {
        View         testview;
        testview = zencas.deserializeOi()
                         .fromFile( zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/TestInclude3-mFAProfO.por" )
                         .setLodDef( "mFAProf" )
                         .activateFirst();

        int rc = testview.cursor( "FinAidAward" ).setFirst( "ID", 50224 ).toInt();
        View profn = zencas.activateEmptyObjectInstance( "mFAProf" );
        profn.cursor( "FinAidProfile" ).createEntity();
        profn.cursor( "FinAidProfile" ).setMatchingAttributesByName( testview.cursor( "FinAidProfile" ), SetMatchingFlags.DEFAULT);
        profn.cursor( "Person" ).includeSubobject( testview.cursor( "Person" ), CursorPosition.LAST );

        View view = zencas.deserializeOi()
                .fromFile( zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/AfterPersonInclude.por" )
                .setLodDef( "mFAProf" )
                .activateFirst();
        Assert.assertTrue( "Views don't match after include", view.equalsOi( profn ) );

        System.out.println("done");
	   	testview.drop();
   }

	@Test
	public void testDomainCompareAttrs()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testDomainCompareAttrs( testview );
        System.out.println("===== Finished testDomainCompareAttrs ========");
	}

	@Test
	public void testInclude1to1WithCommit()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testInclude1to1WithCommit( testview );
        System.out.println("===== Finished testInclude1to1WithCommit ========");
	}

	@Test
	public void testInclude0to1WithCommit()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testInclude0to1WithCommit( testview );
        System.out.println("===== Finished testInclude0to1WithCommit ========");
	}

	@Test
	public void testInclude0to1WithCommit2()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testInclude0to1WithCommit2( testview );
        System.out.println("===== Finished testInclude0to1WithCommit2 ========");
	}

	@Test
	public void testIncludeMtoMWithCommit()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testIncludeMtoMWithCommit( testview );
        System.out.println("===== Finished testIncludeMtoMWithCommit ========");
	}

	@Test
	public void testIncludeMtoMWithCommitJeff()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testIncludeMtoMWithCommitJeff( testview );
        System.out.println("===== Finished testIncludeMtoMWithCommitJeff ========");
	}

	@Test
	public void testActivateRecurObj()
	{
	    View         testview;
        // Turn off assertions for zeidon for this test.
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        loader.setPackageAssertionStatus( "com.quinsoft.zeidon", false );
        loader.getSystemClassLoader().setDefaultAssertionStatus(false);
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testActivateRecurObj( testview );
        System.out.println("===== Finished testActivateRecurObj ========");
	}

	@Test
	public void testActivateRecurObjSetFirst()
	{
	    View         testview;
        // Turn off assertions for zeidon for this test.
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        loader.setPackageAssertionStatus( "com.quinsoft.zeidon", false );
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testActivateRecurObjSetFirst( testview );
        System.out.println("===== Finished testActivateRecurObj ========");
	}

	@Test
	public void testDomainCompareIssue()
	{
	    View         testview;
        // Turn off assertions for zeidon for this test.
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        loader.setPackageAssertionStatus( "com.quinsoft.zeidon", false );
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testDomainCompareIssue( testview );
        System.out.println("===== Finished testDomainCompareIssue ========");
	}

    @Test
	public void
	testExcludeIncludeSaveError( )
	{
	    View         testview;
        // Turn off assertions for zeidon for this test.
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        loader.setPackageAssertionStatus( "com.quinsoft.zeidon", false );
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testExcludeIncludeSaveError( testview );
        System.out.println("===== Finished testExcludeIncludeSaveError ========");		
	}

//    @Test
    public void testNavigationTime()
    {
        // We're about to load a large portable file.  If assertions are on this takes a looong time.
        // Turn off assertions for zeidon for this test.
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        loader.setPackageAssertionStatus( "com.quinsoft.zeidon", false );

        View view;
        Timer timer;

        timer = new Timer();
        view = zencas.activateOiFromFile( "mGLJEnt", "./testdata/ZENCAs/large-mGLJent.por" );
        zencas.log().info( "Time activiting large OI #1 = " + timer.toString() );

        timer = new Timer();
        int count = 0;
        for ( CursorResult rc = view.cursor( "GLJournalEntry" ).setFirst();
              rc.isSet();
              rc = view.cursor( "GLJournalEntry" ).setNext() )
        {
            if ( view.cursor( "GLJournalEntry" ).compareAttribute( "TransactionDate", "20110101" ) >= 0 )
                count++;
        }

        zencas.log().info( "Count = %d, time = %s", count, timer );

        timer = new Timer();
        count = 0;
        for ( CursorResult rc = view.cursor( "GLJournalEntry" ).setFirst();
              rc.isSet();
              rc = view.cursor( "GLJournalEntry" ).setNext() )
        {
            if ( view.cursor( "GLJournalEntry" ).compareAttribute( "TransactionDate", "20110101" ) >= 0 )
                count++;
        }

        zencas.log().info( "Count2 = %d, time = %s", count, timer );

        timer = new Timer();
        count = 0;
        EntityCursor cursor = view.cursor( "GLJournalEntry" );
        for ( EntityInstance ei : cursor.eachEntity() )
        {
            if ( ei.compareAttribute( "TransactionDate", "20110101" ) >= 0 )
                count++;
        }
        zencas.log().info( "Count3 = %d, time = %s", count, timer );

        timer = new Timer();
        count = 0;
        for ( int rc = view.cursor( "GLJournalEntry" ).setFirst().toInt();
              rc >= 0;
              rc = view.cursor( "GLJournalEntry" ).setNext().toInt() )
        {
            if ( view.cursor( "GLJournalEntry" ).compareAttribute( "TransactionDate", "20110101" ) >= 0 )
            {
                view.cursor( "GLJournalEntry" ).dropEntity( CursorPosition.NONE );
                count++;
            }
        }

        System.gc();
        zencas.log().info( "Dropped = %d, time = %s", count, timer );

        timer = new Timer();
        view = zencas.activateOiFromFile( "mGLJEnt", "./testdata/ZENCAs/large-mGLJent.por" );
        zencas.log().info( "Time activiting large OI #2 = " + timer.toString() );

        timer = new Timer();
        count = 0;
        for ( int rc = view.cursor( "GLJournalEntry" ).setFirst().toInt();
              rc >= 0;
              rc = view.cursor( "GLJournalEntry" ).setNext().toInt() )
        {
            if ( view.cursor( "GLJournalEntry" ).compareAttribute( "TransactionDate", "20110101" ) >= 0 )
            {
                view.cursor( "GLJournalEntry" ).deleteEntity( CursorPosition.NONE );
                count++;
            }
        }

        zencas.log().info( "Deleted = %d, time = %s", count, timer );

        // Turn assertions back on.
        loader.clearAssertionStatus();
    }

	@Test
	public void testSQL()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testSQLWithDistinct( testview );
        System.out.println("===== Finished testSQLWithDistinct ========");
	}

	@Test
	public void testClsLstCActivateTime()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testClsLstCActivateTime( testview );
        System.out.println("===== Finished testClsLstCActivateTime ========");
	}

	@Test
	public void testmUserActivateTime()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testmUserActivateTime( testview );
        System.out.println("===== Finished testmUserActivateTime ========");
	}

	@Test
	public void testmUserActivateTimeAdmin()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testmUserActivateTimeAdmin( testview );
        System.out.println("===== Finished testmUserActivateTimeAdmin ========");
	}

//	@Test
	public void testActivateWithJoins()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		// This gives an error on the sql with joins.
		tester.testActivatemStudentWithJoins( testview );
        System.out.println("===== Finished testActivateWithJoins ========");
	}

	@Test
	public void testActivatelAdviseeWithRestricts()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		// This gives an error on the sql with joins.
		tester.testActivatelAdviseeWithRestricts( testview );
        System.out.println("===== Finished testActivatelAdviseeWithRestricts ========");
	}

	@Test
	public void testDerivedDate()
	{
	    View         testview;
        // I get an error on the derived attribute dLastAuditDateTime because GetVariableFromAttribute returns the
		// date in the format "08/19/2011 09:45:20.0 AM" which causes an error in StoreStringInRecord.
		// If I add the context "YYYYMMDDHHMMSSS" it works fine.
		//GetVariableFromAttribute( sb_szDate, mi_lTempInteger_2, 'S', 101, mStudenC, "SavedDegreeAudit", "CreatedDateTime", "", 0 );			//com.quinsoft.zeidon.InvalidAttributeValueException: Invalid value for attribute
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testDerivedDate( testview );
        System.out.println("===== Finished testDerivedDate ========");
	}

	@Test
	public void testImportPorError()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testImportPorError( testview );
        System.out.println("===== Finished testImportPorError ========");
	}
	@Test
	public void testVariousItems()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testVariousItems( testview );
        System.out.println("===== Finished testVariousItems ========");
	}
	@Test
	public void testSAProfIncludeSubobjects()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testSAProfIncludeSubobjects( testview );
        System.out.println("===== Finished testSAProfIncludeSubobjects ========");
	}
	@Test
	public void testActivatingDomains()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		// I am having a problem running MyENC with sqlite.  This is not a problem
		// when I run with access db.
		tester.testActivatingDomains( testview );
        System.out.println("===== Finished testActivatingDomains ========");
	}
	@Test
	public void testSQL2()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testSQL2( testview );
        System.out.println("===== Finished testSQL2 ========");
	}
	@Test
	public void testActivateMissingPerson()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testActivateMissingPerson( testview );
        System.out.println("===== Finished testActivateMissingPerson ========");
	}
	@Test
	public void testActivateMissingCollegeTerm()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testActivateMissingCollegeTerm( testview );
        System.out.println("===== Finished testActivateMissingCollegeTerm ========");
	}
	@Test
	public void testActivatemSAProf()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testActivatemSAProf( testview );
        System.out.println("===== Finished testActivatemSAProf ========");
	}

	@Test
	public void testMissingRequiredGroup()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testMissingRequiredGroup( testview );
        System.out.println("===== Finished testMissingRequiredGroup ========");
	}

	@Test
	public void testMissingClass()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testMissingClass( testview );
        System.out.println("===== Finished testMissingClass ========");
	}

	@Test
	public void testSetAttributeFromAttribute()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testSetAttributeFromAttribute( testview );
        System.out.println("===== Finished testSetAttributeFromAttribute ========");
	}

	@Test
	public void testSavingNewObject()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testSavingNewObject( testview );
        System.out.println("===== Finished testSavingNewObject ========");
	}

	@Test
	public void testDatesTimes()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testDatesTimes( testview );
        System.out.println("===== Finished testDatesTimes ========");
	}

	@Test
	public void testDateSort()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testDateSort( testview );
        System.out.println("===== Finished testDateSort ========");
	}

	@Test
	public void testSpawning1()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testSpawning1( testview );

		// Test for a memory leak by creating lots of views and make sure they
		// get cleaned up by the GC.
		testview = zencas.getViewByName( "mFAProf1" );

		/*
		for ( int i = 0; i < 100000; i++ )
		{
		    testview = testview.newView();
		    testview.setName( "mFAProf2" );
		}
        */
        System.out.println("===== Finished testSpawning1 ========");
	}

	@Test
	public void testHierarchicalCursor()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testHierarchicalCursor( testview );
        System.out.println("===== Finished testHierarchicalCursor ========");
	}

	@Test
	public void testActivateRootOnly()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.activateRootOnly( testview );
        System.out.println("===== Finished testActivateRootOnly ========");
	}

	@Test
	public void testYearErrorInmStudenC()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testYearErrorInmStudenC( testview );
        System.out.println("===== Finished testYearErrorInmStudenC ========");
	}

	@Test
	public void testDropEntityError()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testDropEntityError( testview );
        System.out.println("===== Finished testDropEntityError ========");
	}

	@Test
	public void testDropEntityCursorError()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testDropEntityCursorError( testview );
        System.out.println("===== Finished testDropEntityError2 ========");
	}

	@Test
	public void testDeleteDropEntityCursorPosError()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testDeleteDropEntityCursorPosError( testview );
        System.out.println("===== Finished testDeleteDropEntityCursorPosError ========");
	}

	@Test
	public void testActivatemFAProfNoData()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testActivatemFAProfNoData( testview );
        System.out.println("===== Finished testActivatemFAProfNoData ========");
	}

	@Test
	public void testAdminDivDomain()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testAdminDivDomain( testview );
        System.out.println("===== Finished testAdminDivDomain ========");
	}

	@Test
	public void testActivatemFAProfMissingYearSemester()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testActivatemFAProfMissingYearSemester( testview );
        System.out.println("===== Finished testActivatemFAProfMissingYearSemester ========");
	}

	@Test
	public void testActivatelTrnscptMissingMajorCollegeFields()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testActivatelTrnscptMissingMajorCollegeFields( testview );
        System.out.println("===== Finished testActivatelTrnscptMissingMajorCollegeFields ========");
	}

	@Test
	public void testCompareEmptyWithNullForStaticTableDomain()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testCompareEmptyWithNullForStaticTableDomain( testview );
        System.out.println("===== Finished testCompareEmptyWithNullForStaticTableDomain ========");
	}

	@Test
	public void testDerivedDerived()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testDerivedDerived( testview );
        System.out.println("===== Finished testDerivedDerived ========");
	}

//	@Test
	public void testTimeFormatting()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testTimeFormatting( testview );
        System.out.println("===== Finished testTimeFormatting ========");
	}

	@Test
	public void testActivatesHost()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testActivatesHost( testview );
        System.out.println("===== Finished testActivatesHost ========");
	}

	@Test
	public void testUsingJacob()
	{

        //ActiveXComponent comp=new ActiveXComponent("LDAP://RootDSE");
        //ActiveXComponent comp=new ActiveXComponent("activeds");
        //ActiveXComponent comp=new ActiveXComponent("activeds.ADSI");
        //ActiveXComponent comp=new ActiveXComponent("activeds.ADsDSOObject");
        //ActiveXComponent comp=new ActiveXComponent("activeds.activeDirectory");
		//System.out.println("The Library been loaded, and an activeX component been created");


			        /*
			        int arg1=100;
			        int arg2=50;
			        //using the functions from the library:
			        int summation=Dispatch.call(comp, "sum",arg1,arg2).toInt();
			        System.out.println("Summation= "+ summation);

			        int subtraction= Dispatch.call(comp,"subtract",arg1,arg2).toInt();
			        System.out.println("Subtraction= "+ subtraction);

			        int multiplication=Dispatch.call(comp,"multi",arg1,arg2).toInt();
			        System.out.println("Multiplication= "+ multiplication);

			        double division=Dispatch.call(comp,"div",arg1,arg2).toDouble();
			        System.out.println("Division= "+ division);
			        */
			        /**The following code is abstract of using the get,
			         * when the library contains a function that return
			         * some kind of a struct, and then get its properties and values
			         **/
			//      Dispatch disp=Dispatch.call(comp,"sum",100,10).toDispatch();
			//      DataType Var=Dispatch.get(disp,"Property Name");

	}

	/**
	 * This tests the situation where child entities can be duplicated.  Given this subobject:
	 *   A
	 *   |
	 *   B
	 *
	 * There are many different instances of A but they all share the same B.  The Activate
	 * should correctly load all the B's.
	 */
//	@Test
	public void testDuplicateChildEntities()
	{
        Assert.assertTrue( "This is a place holder as a reminder to fix OI comparison test.  Look for 'mstudent_ac.por' in comments", false );
	}

	/**
	 * Validate that we can insert multiple twins in one SQL statement.
	 */
//	@Test
    public void testCreateMultipleTwins()
    {
	    View view = new QualificationBuilder( zencas ).setLodDef( "mCRStdP" ).addAttribQual( "ID", 580 ).activate();
	    view.cursor( "ClassRoomSession" ).createEntity( CursorPosition.LAST );
        view.cursor( "ClassRoomSession" ).createEntity( CursorPosition.LAST );
        view.commit();
	    view.logObjectInstance();
    }

    public void
    testOutOfMemory()
    {
        VmlTester tester = new VmlTester( zencas );
        tester.testOutOfMemory();
    }

    private String getTempDir()
    {
        return System.getProperty("java.io.tmpdir");
    }

	private class VmlTester extends VmlOperation
	{
		public VmlTester( TaskQualification qual )
		{
			super( qual );
		}

		public int
		activateRootOnly( View ViewToWindow )
		{
		   zVIEW    mPerson = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int RESULT=0;

		   // Activating mPerson as ROOTONLY, does eliminate some child entities like FinAidProfile, but there are several entities
		   // that were not eliminated.
		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
		   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
		   DropView( vTempViewVar_0 );
		   RESULT = CheckExistenceOfEntity( mPerson, "Employee");
 		   Assert.assertEquals("Activate mPerson RootOnly but Employee entity exists.", CursorResult.NULL.toInt(), RESULT );
		   RESULT = CheckExistenceOfEntity( mPerson, "Student");
 		   Assert.assertEquals("Activate mPerson RootOnly but Student entity exists.", CursorResult.NULL.toInt(), RESULT );
		   RESULT = CheckExistenceOfEntity( mPerson, "Demographics");
 		   Assert.assertEquals("Activate mPerson RootOnly but Demographics entity exists.", CursorResult.NULL.toInt(), RESULT );
		   RESULT = CheckExistenceOfEntity( mPerson, "HomeChurch");
 		   Assert.assertEquals("Activate mPerson RootOnly but HomeChurch entity exists.", CursorResult.NULL.toInt(), RESULT );
		   RESULT = CheckExistenceOfEntity( mPerson, "PrimaryAddress");
 		   Assert.assertEquals("Activate mPerson RootOnly but PrimaryAddress entity exists.", CursorResult.NULL.toInt(), RESULT );

		   return 0;
		}

		public int
		testSAProfIncludeSubobjects( View ViewToWindow )
		{
		   zVIEW    mSAProfEList = new zVIEW( );
		   zVIEW    mSAProf      = new zVIEW( );
		   int RESULT=0;

	   	   ActivateOI_FromFile( mSAProf, "mSAProf", ViewToWindow, zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mSAProfIncludeErr.por", zSINGLE );
		   ActivateEmptyObjectInstance( mSAProfEList, "mSAProfE", ViewToWindow, zMULTIPLE );
		   //:NAME VIEW mSAProfEList "mSAProfEList"
		   SetNameForView( mSAProfEList, "mSAProfEList", null, zLEVEL_TASK );
		   //:INCLUDE mSAProfEList.StudentAccountProfile FROM mSAProf.StudentAccountProfile
		   RESULT = IncludeSubobjectFromSubobject( mSAProfEList, "StudentAccountProfile", mSAProf, "StudentAccountProfile", zPOS_AFTER );

		   return 0;
		}

		public int
		testSpawning1( View ViewToWindow )
		{
		   zVIEW    mFAProf1      = new zVIEW( );
		   zVIEW    mFAProf2      = new zVIEW( );
		   int RESULT=0;
		   double dAmount=0;

		   ActivateOI_FromFile( mFAProf1, "mFAProf", ViewToWindow, zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mFAProfO.por", zSINGLE );
		   SetNameForView( mFAProf1, "mFAProf1", null, zLEVEL_TASK );
		   mFAProf1.cursor("FinAidAward").setFirst().toInt();
		   ActivateOI_FromFile( mFAProf2, "mFAProf", ViewToWindow, zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mFAProfO.por", zSINGLE );
		   SetNameForView( mFAProf2, "mFAProf2", null, zLEVEL_TASK );
		   mFAProf2.cursor("FinAidAward").setFirst().toInt();

		   RelinkInstanceToInstance( mFAProf1, "FinAidAward", mFAProf2, "FinAidAward");

	       mFAProf1.cursor("FinAidAwardDisbursement").createEntity();
	       mFAProf1.cursor("FinAidAwardDisbursement").setAttribute("Amount", 123);
	       mFAProf1.cursor("FinAidAwardDisbursement").setAttribute("AmountExpected", 321);

	       // Check spawning after createEntity
	       RESULT= mFAProf2.cursor("FinAidAwardDisbursement").setFirst( "Amount", 123 ).toInt();
 		   dAmount = mFAProf2.cursor("FinAidAwardDisbursement").getDoubleFromAttribute("Amount");
 		   Assert.assertEquals("mFAProf2.FinAidAwardDisbursement not correctly spawned after createEntity.", 123.0, dAmount, 0.1);
 		   dAmount = mFAProf2.cursor("FinAidAwardDisbursement").getDoubleFromAttribute("AmountExpected");
 		   Assert.assertEquals("mFAProf2.FinAidAwardDisbursement not correctly spawned after createEntity.", 321.0, dAmount, 0.1);

		   RESULT = IncludeSubobjectFromSubobject( mFAProf1, "FinAidAwardPeriod", mFAProf1, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
		   RESULT = CheckExistenceOfEntity( mFAProf2, "FinAidAwardPeriod");
	       // Check spawning after Include entity
 		   Assert.assertEquals("mFAProf2.FinAidAwardPeriod not correctly spawned after include.", 0, RESULT );

 		   mFAProf1.cursor("FinAidAwardDisbursement").createTemporalEntity();
	       mFAProf1.cursor("FinAidAwardDisbursement").setAttribute("Amount", 234);
	       mFAProf1.cursor("FinAidAwardDisbursement").setAttribute("AmountExpected", 432);
	       mFAProf1.cursor("FinAidAwardDisbursement").acceptTemporalEntity();

	       // Check spawning after createTemporalEntity and acceptTemporalEntity
           RESULT= mFAProf2.cursor("FinAidAwardDisbursement").setCursor( mFAProf1.cursor("FinAidAwardDisbursement") ).toInt();
 		   dAmount = mFAProf2.cursor("FinAidAwardDisbursement").getDoubleFromAttribute("Amount");
 		   Assert.assertEquals("mFAProf2.FinAidAwardDisbursement not correctly spawned after createEntity.", 234.0, dAmount, 0.1);
 		   // I am assuming in above assert that when we create an entity and accept in mFAProf1 we would be on
 		   // positioned on that entity in mFAProf2.  In case that wouldn't be the case, I have the below code that
 		   // would be used instead.
	       RESULT = mFAProf2.cursor("FinAidAwardDisbursement").setFirst("Amount", 234).toInt();
 		   Assert.assertEquals("FinAidAwardDisbursement not correctly spawned after createTemporal.", 0, RESULT);

		   return 0;
		}

		public int
		testSQL2( View ViewToWindow )
		{
			zVIEW    lStuClAt = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int      RESULT = 0;

			o_fnLocalBuildlStuClAt( ViewToWindow, vTempViewVar_0, 16406, "2009-2010 January" );
			RESULT = ActivateObjectInstance( lStuClAt, "lStuClAt", ViewToWindow, vTempViewVar_0, zSINGLE );
			DropView( vTempViewVar_0 );

	         return 0;
		}

		public int
		testActivatingDomains( View ViewToWindow )
		{
			   zVIEW    DOMAINT = new zVIEW( );
			   //:VIEW DOMAINTN  BASED ON LOD  DOMAINT
			   zVIEW    DOMAINTN = new zVIEW( );
			   //:VIEW DOMAINTNC BASED ON LOD DOMAINT
			   zVIEW    DOMAINTNC = new zVIEW( );
			   //:STRING ( 50 ) szName
			   String   szName = null;
			   zVIEW    vTempViewVar_0 = new zVIEW( );
			   int      RESULT = 0;
			   String   szTempString_0 = null;
			   int      lTempInteger_0 = 0;
			   String   szTempString_1 = null;
			   int      lTempInteger_1 = 0;

			   //:ACTIVATE DOMAINT MULTIPLE
			   //:   WHERE DOMAINT.Domain.Name = szDomainName
			   o_fnLocalBuildDOMAINT( ViewToWindow, vTempViewVar_0, "Country" );
			   RESULT = ActivateObjectInstance( DOMAINT, "DOMAINO", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			   DropView( vTempViewVar_0 );
			   //:
			   //:// Create an entry for this dynamic Domain.
			   //:CreateViewFromView( DOMAINTN, DOMAINT )
			   CreateViewFromView( DOMAINTN, DOMAINT );
			   //:szName = "X_" + DOMAINT.Domain.Name
			   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
			   StringBuilder sb_szTempString_0;
			   if ( szTempString_0 == null )
			      sb_szTempString_0 = new StringBuilder( 32 );
			   else
			      sb_szTempString_0 = new StringBuilder( szTempString_0 );
			       GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 33, DOMAINT, "Domain", "Name", "", 0 );
			   lTempInteger_0 = mi_lTempInteger_0.intValue( );
			   szTempString_0 = sb_szTempString_0.toString( );}
			    {StringBuilder sb_szName;
			   if ( szName == null )
			      sb_szName = new StringBuilder( 32 );
			   else
			      sb_szName = new StringBuilder( szName );
			      ZeidonStringCopy( sb_szName, 1, 0, "X_", 1, 0, 51 );
			   szName = sb_szName.toString( );}
			    {StringBuilder sb_szName;
			   if ( szName == null )
			      sb_szName = new StringBuilder( 32 );
			   else
			      sb_szName = new StringBuilder( szName );
			      ZeidonStringConcat( sb_szName, 1, 0, szTempString_0, 1, 0, 51 );
			   szName = sb_szName.toString( );}
			   //:NAME VIEW DOMAINTN szName
			   SetNameForView( DOMAINTN, szName, null, zLEVEL_TASK );

			   //:// Create an entry for each Domain Context.
			   //:FOR EACH DOMAINTN.Context
			   RESULT = DOMAINTN.cursor( "Context" ).setFirst().toInt();
			   while ( RESULT > zCURSOR_UNCHANGED )
			   {
			      //:CREATE ENTITY DOMAINT.Domain
			      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
			      //:DOMAINT.Domain.Name = DOMAINTN.Context.Name
			      SetAttributeFromAttribute( DOMAINT, "Domain", "Name", DOMAINTN, "Context", "Name" );
			      //:FOR EACH DOMAINTN.ContextDomainValue
			      RESULT = DOMAINTN.cursor( "ContextDomainValue" ).setFirst().toInt();
			      while ( RESULT > zCURSOR_UNCHANGED )
			      {
			         //:CREATE ENTITY DOMAINT.DomainValue
			         RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
			         //:DOMAINT.DomainValue.InternalStringValue = DOMAINTN.ContextDomainValue.InternalStringValue
			         SetAttributeFromAttribute( DOMAINT, "DomainValue", "InternalStringValue", DOMAINTN, "ContextDomainValue", "InternalStringValue" );
			         //:DOMAINT.DomainValue.ExternalDescription = DOMAINTN.ContextDomainValue.ExternalDescription
			         SetAttributeFromAttribute( DOMAINT, "DomainValue", "ExternalDescription", DOMAINTN, "ContextDomainValue", "ExternalDescription" );
			         RESULT = DOMAINTN.cursor( "ContextDomainValue" ).setNextContinue().toInt();;
			      }

			      //:END
			      //:CreateViewFromView( DOMAINTNC, DOMAINT )
			      CreateViewFromView( DOMAINTNC, DOMAINT );
			      //:szName = "X_" + DOMAINTNC.Domain.Name
			      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
			      StringBuilder sb_szTempString_1;
			      if ( szTempString_1 == null )
			         sb_szTempString_1 = new StringBuilder( 32 );
			      else
			         sb_szTempString_1 = new StringBuilder( szTempString_1 );
			             GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 33, DOMAINTNC, "Domain", "Name", "", 0 );
			      lTempInteger_1 = mi_lTempInteger_1.intValue( );
			      szTempString_1 = sb_szTempString_1.toString( );}
			       {StringBuilder sb_szName;
			      if ( szName == null )
			         sb_szName = new StringBuilder( 32 );
			      else
			         sb_szName = new StringBuilder( szName );
			            ZeidonStringCopy( sb_szName, 1, 0, "X_", 1, 0, 51 );
			      szName = sb_szName.toString( );}
			       {StringBuilder sb_szName;
			      if ( szName == null )
			         sb_szName = new StringBuilder( 32 );
			      else
			         sb_szName = new StringBuilder( szName );
			            ZeidonStringConcat( sb_szName, 1, 0, szTempString_1, 1, 0, 51 );
			      szName = sb_szName.toString( );}
			      //:NAME VIEW DOMAINTNC szName
			      SetNameForView( DOMAINTNC, szName, null, zLEVEL_TASK );
			      RESULT = DOMAINTN.cursor( "Context" ).setNextContinue().toInt();;
			   }

		   return 0;
		}

		public int
		testSQLWithDistinct( View     ViewToWindow )
		{
			   zVIEW    mFAProf = new zVIEW( );
			   zVIEW    vTempViewVar_0 = new zVIEW( );
			   int RESULT=0;

		      //:ACTIVATE mFAProf WHERE mFAProf.Person.ID = mSAProfEList.Person.ID
		      //:                   AND mFAProf.CollegeYear.ID = wStudent.CollegeTermYear.ID
		      //:        RESTRICTING mFAProf.Person                      TO mFAProf.Person.ID = 0
		      //:        RESTRICTING mFAProf.FinAidTrack                 TO mFAProf.FinAidTrack.ID = 0
		      //:        RESTRICTING mFAProf.FinAidRequirementMet        TO mFAProf.FinAidRequirementMet.ID = 0
		      //:        RESTRICTING mFAProf.FinAidCOAItemAssigned       TO mFAProf.FinAidCOAItemAssigned.ID = 0
		      //:        RESTRICTING mFAProf.PerProfileFinAidAwardPeriod TO mFAProf.PerProfileFinAidAwardPeriod.ID = 0
		      //:        RESTRICTING mFAProf.OtherFAISIR                 TO mFAProf.OtherFAISIR.ID = 0
		      //:        RESTRICTING mFAProf.FAISIR                      TO mFAProf.FAISIR.ID = 0
		      //:        RESTRICTING mFAProf.WorkStudyAllocation         TO mFAProf.WorkStudyAllocation.ID = 0
		      //:        RESTRICTING mFAProf.CurrentPerson               TO mFAProf.CurrentPerson.ID = 0
		      omSAProfE_fnLocalBuildQual_0( ViewToWindow, vTempViewVar_0, 18685, 26, 169, 169 );
		      RESULT = ActivateObjectInstance( mFAProf, "mFAProf", ViewToWindow, vTempViewVar_0, zSINGLE );
 	          System.out.println(RESULT);
		      DropView( vTempViewVar_0 );
		      DropView( mFAProf );
			return 0;
		}

		public int
		testActivatemFAProfNoData( View     ViewToWindow )
		{
			   zVIEW    mFAProf = new zVIEW( );
			   zVIEW    vTempViewVar_0 = new zVIEW( );
			   int RESULT=0;

			   // This test is activating an mFAProf instance that we know we do not have data for.
			   // I believe the object should come back empty but we are getting a NullCursorException
			   // for the root entity FinAidProfile.

		      //:ACTIVATE mFAProf WHERE mFAProf.Person.ID = mSAProfEList.Person.ID
		      //:                   AND mFAProf.CollegeYear.ID = wStudent.CollegeTermYear.ID
		      //:        RESTRICTING mFAProf.Person                      TO mFAProf.Person.ID = 0
		      //:        RESTRICTING mFAProf.FinAidTrack                 TO mFAProf.FinAidTrack.ID = 0
		      //:        RESTRICTING mFAProf.FinAidRequirementMet        TO mFAProf.FinAidRequirementMet.ID = 0
		      //:        RESTRICTING mFAProf.FinAidCOAItemAssigned       TO mFAProf.FinAidCOAItemAssigned.ID = 0
		      //:        RESTRICTING mFAProf.PerProfileFinAidAwardPeriod TO mFAProf.PerProfileFinAidAwardPeriod.ID = 0
		      //:        RESTRICTING mFAProf.OtherFAISIR                 TO mFAProf.OtherFAISIR.ID = 0
		      //:        RESTRICTING mFAProf.FAISIR                      TO mFAProf.FAISIR.ID = 0
		      //:        RESTRICTING mFAProf.WorkStudyAllocation         TO mFAProf.WorkStudyAllocation.ID = 0
		      //:        RESTRICTING mFAProf.CurrentPerson               TO mFAProf.CurrentPerson.ID = 0
		      omSAProfE_fnLocalBuildQual_0( ViewToWindow, vTempViewVar_0, 18808, 41, 170, 170 );
		      RESULT = ActivateObjectInstance( mFAProf, "mFAProf", ViewToWindow, vTempViewVar_0, zSINGLE );
 	          System.out.println(RESULT);
		      DropView( vTempViewVar_0 );
			return 0;
		}


		public int
		testAdminDivDomain( View     ViewToWindow )
		{
			   zVIEW    TstPrspt = new zVIEW( );
			   zVIEW    vQualObject = new zVIEW( );
			   int RESULT=0;

			   //:ACTIVATE  TstPrspt  WHERE TstPrspt.Prospect.ID = 16265
			   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", ViewToWindow, zMULTIPLE );
			   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Prospect" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Prospect" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "16265" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );

			   RESULT = ActivateObjectInstance( TstPrspt, "TstPrspt", ViewToWindow, vQualObject, zSINGLE );
			   DropView( vQualObject );
			return 0;
		}


		public int
		testActivatemFAProfMissingYearSemester( View     ViewToWindow )
		{
			   zVIEW    mFAProf = new zVIEW( );
			   zVIEW    vTempViewVar_0 = new zVIEW( );
			   int RESULT=0;
			   String   szYearSemester;

			   // When I activate mFAProf the entity CollegeTerm under PerProfileFinAidAwardPeriod
			   // only has the fields ID and Semester populated.  The fields YearSemester, CourseStartDate etc.
			   // are blank. Not sure why this is.  I know in the objectcontraint code for mFAProf we do a
			   // relink on PerProfileFinAidAwardPeriod, but the fields are the same before and after the
			   // relink.

              //:ACTIVATE mFAProf
		      //:WHERE mFAProf.FinAidProfile.ID = mSAStu.FinAidProfile.ID
			  o_fnLocalmFAProf( ViewToWindow, vTempViewVar_0, 17657 );
		      RESULT = ActivateObjectInstance( mFAProf, "mFAProf", ViewToWindow, vTempViewVar_0, zSINGLE );

		      // NEW TEST
		      /*
		      RESULT = SetCursorFirstEntityByInteger( mFAProf, "PerProfileFinAidAwardPeriod", "ID", 30830, "" );
		      //:IF RESULT >= zCURSOR_SET
		      if ( RESULT >= zCURSOR_SET )
		      {
		         RESULT = SetCursorFirstEntityByInteger( mFAProf, "PerPeriodFinAidAwardDisbursement", "ID", 71497, "" );
		      }
		      */
		      // END NEW TEST
              RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
              szYearSemester = mFAProf.cursor( "CollegeTerm" ).getStringFromAttribute("YearSemester");
	   		  Assert.assertEquals("CollegeTerm 160 has some missing attributes like YearSemester ", "2009-2010 Fall", szYearSemester);
		      DropView( vTempViewVar_0 );
			return 0;
		}

		public int
		testActivatelTrnscptMissingMajorCollegeFields( View     ViewToWindow )
		{
			   zVIEW    lTrnscpt = new zVIEW( );
			   zVIEW    vTempViewVar_0 = new zVIEW( );
			   int RESULT=0;
			   String   szType;

			   // When I activate lTrnscpt the entity MajorCollege under StudentMajorDegreeTrack
			   // is missing the field Type.  It is null when it should be "U".

			   //:ACTIVATE lTrnscpt WHERE lTrnscpt.Student.ID = mStudenC.Student.ID
		      o_fnLocalBuildlTrnscpt( ViewToWindow, vTempViewVar_0, 16406 );
			  RESULT = ActivateObjectInstance( lTrnscpt, "lTrnscpt", ViewToWindow, vTempViewVar_0, zSINGLE );
			  DropView( vTempViewVar_0 );
              RESULT = lTrnscpt.cursor( "StudentMajorDegreeTrack" ).setFirst().toInt();
              szType = lTrnscpt.cursor( "MajorCollege" ).getStringFromAttribute("Type");
	   		  Assert.assertEquals("MajorCollege 1 has missing attribute Type ", "U", szType);
			return 0;
		}

		public int
		testCompareEmptyWithNullForStaticTableDomain( View     ViewToWindow )
		{
			   zVIEW    lTrnscpt = new zVIEW( );
			   zVIEW    vTempViewVar_0 = new zVIEW( );
			   int RESULT=0;
			   String   szType;

			   // I am comparing a null to a "".  For the staticTableDomain, this is not working, it
			   // goes into AbstractDomain where isNull is always comparing the value to null.  This
			   // does not take emptyString into consideration.

			   //:ACTIVATE lTrnscpt WHERE lTrnscpt.Student.ID = mStudenC.Student.ID
		      o_fnLocalBuildlTrnscpt( ViewToWindow, vTempViewVar_0, 16406 );
			  RESULT = ActivateObjectInstance( lTrnscpt, "lTrnscpt", ViewToWindow, vTempViewVar_0, zSINGLE );
			  DropView( vTempViewVar_0 );
		      RESULT = lTrnscpt.cursor( "Registration" ).setFirst().toInt();
		      //:IF lTrnscpt.Registration.GradUndergradOverrideFlag != ""
		      RESULT = CompareAttributeToString( lTrnscpt, "Registration", "GradUndergradOverrideFlag", "" );
		      Assert.assertTrue( "Error comparing null and empty on a StaticTableDomain ", RESULT == 0 );
			return 0;
		}

		public int
		testTimeFormatting( View     ViewToWindow )
		{
			   zVIEW    mCRStdPLST = new zVIEW( );
			   zVIEW    mCRStdPLST2 = new zVIEW( );
			   zVIEW    vTempViewVar_0 = new zVIEW( );
			   int RESULT=0;
			   String   szTime;

			   RESULT = ActivateObjectInstance( mCRStdPLST, "mCRStdP", ViewToWindow, 0, zMULTIPLE );
			   //:NAME VIEW mCRStdPLST "mCRStdPLST"
			   SetNameForView( mCRStdPLST, "mCRStdPLST", null, zLEVEL_TASK );
			   CreateViewFromView( mCRStdPLST2, mCRStdPLST );
			   SetNameForView( mCRStdPLST2, "mCRStdPLST2", null, zLEVEL_TASK );

			   RESULT = mCRStdPLST.cursor( "ClassRoomStandardSchedule" ).setFirst().toInt();
			   szTime = mCRStdPLST.cursor("ClassRoomSession").getStringFromAttribute("StartTime");
			   szTime = mCRStdPLST.cursor("ClassRoomSession").getStringFromAttribute("StartTime","HH:MM:SS.S");

		       RESULT = mCRStdPLST.cursor( "ClassRoomStandardSchedule" ).setFirst( "ID","381").toInt();
		       RESULT = mCRStdPLST.cursor( "ClassRoomSession" ).setFirst( "ID","1021","ClassRoomStandardSchedule").toInt();
		       RESULT = mCRStdPLST2.cursor( "ClassRoomStandardSchedule" ).setFirst( "ID","396").toInt();
		       RESULT = mCRStdPLST2.cursor( "ClassRoomSession" ).setFirst( "ID","2069", "ClassRoomStandardSchedule").toInt();
			   RESULT = CompareAttributeToAttribute( mCRStdPLST2, "ClassRoomSession", "StartTime", mCRStdPLST, "ClassRoomSession", "StartTime" );
	   		   Assert.assertEquals("Error comparing times do not equal ", 0, RESULT);
			return 0;
		}

		public int
		testDropEntityError( View     ViewToWindow )
		{
			   zVIEW    mFAProf = new zVIEW( );
			   zVIEW    vTempViewVar_0 = new zVIEW( );
			   int 		RESULT=0;
			   int      lTempInteger_11 = 0;
			   int iCount1 = 0;
			   int iCount2 = 0;

		         //:ACTIVATE mFAProf
		         //:   WHERE mFAProf.FinAidProfile.ID = mSAStu.FinAidProfile.ID
		         //:   RESTRICTING mFAProf.Person TO mFAProf.Person.ID = 0
		         o_fnLocalBuildmFAProf( ViewToWindow, vTempViewVar_0, 17657 );
		         RESULT = ActivateObjectInstance( mFAProf, "mFAProf", ViewToWindow, vTempViewVar_0, zSINGLE );


		         // When we do a dropEntity for the last FinAidAward entity (ID=41810), then the child entity
		         // FinAidAwardPeriod is missing for the FinAidAward entity that preceded the last (ID=38770).
		         // Please note that under FinAidAward are two FinAidAwardDisbursements.  It's the
		         // FinAidAwardPeriod under the second FinAidAwardDisbursement that is missing.  This would be the
		         // last child entity preceding the deleted FinAidAward that is dropped.
		         // If I do a dropEntity for a FinAidAward that is not the last there does not seem
		         // to be a problem.
		         RESULT = mFAProf.cursor( "FinAidAward" ).setFirst("ID", 41810 ).toInt();
                 DropEntity( mFAProf, "FinAidAward", zREPOS_NONE );
		         RESULT = mFAProf.cursor("FinAidAward").checkExistenceOfEntity().toInt();

   		         RESULT = mFAProf.cursor("FinAidAward").setFirst("ID", 39870).toInt();
		         RESULT = mFAProf.cursor("FinAidAwardDisbursement").setFirst("ID", 78249).toInt();
		         RESULT = mFAProf.cursor("FinAidAwardPeriod").checkExistenceOfEntity().toInt();
	   			 Assert.assertEquals("FinAidAwardPeriod ID=30830 does not exist for FinAidAwardDisbursement 78249. ", 0, RESULT);


			return 0;
		}

		public int
		testDropEntityCursorError( View     ViewToWindow )
		{
			   int 		RESULT=0;
			   int iCount1 = 0;
			   int iCount2 = 0;


	 			zVIEW    wWebXfer = new zVIEW( );

			    RESULT = ActivateEmptyObjectInstance( wWebXfer, "wWebXfer", ViewToWindow, zSINGLE );
			    RESULT = CreateEntity( wWebXfer, "Work", zPOS_AFTER );
			    RESULT = CreateEntity( wWebXfer, "PersonRole", zPOS_AFTER );
			    SetAttributeFromString( wWebXfer, "PersonRole", "Type", "Faculty" );
    	        SetAttributeFromString( wWebXfer, "PersonRole", "WindowName", "Faculty" );
			    RESULT = CreateEntity( wWebXfer, "PersonRole", zPOS_AFTER );
			    SetAttributeFromString( wWebXfer, "PersonRole", "Type", "Student" );
    	        SetAttributeFromString( wWebXfer, "PersonRole", "WindowName", "Student" );
			    RESULT = CreateEntity( wWebXfer, "PersonRole", zPOS_AFTER );
			    SetAttributeFromString( wWebXfer, "PersonRole", "Type", "Faculty" );
    	        SetAttributeFromString( wWebXfer, "PersonRole", "WindowName", "Faculty" );
			    SetNameForView( wWebXfer, "wWebXfer", null, zLEVEL_TASK );

   		        DropEntity( wWebXfer, "PersonRole", zREPOS_NONE);

      		    RESULT = SetCursorFirstEntity( wWebXfer, "PersonRole", "" );
     			Assert.assertEquals("SetCursorFirstEntity doesn't set correctly. ", 0, RESULT);
      		    while ( RESULT > zCURSOR_UNCHANGED )
      		    {
       		       iCount1++;
      		       DropEntity( wWebXfer, "PersonRole", zREPOS_NONE);
      		 	   RESULT = SetCursorNextEntity( wWebXfer, "PersonRole", "" );
      		    }
     		    Assert.assertEquals("SetCursorNextEntity doesn't set correctly. ", 2, iCount1);

      		    //RESULT = SetCursorFirstEntity( wWebXfer, "PersonRole", "" );
     			//Assert.assertEquals("SetCursorFirstEntity doesn't set correctly. ", 0, RESULT);
			return 0;
		}

		public int
		testDeleteDropEntityCursorPosError( View     ViewToWindow )
		{
			   int 		RESULT=0;
			   int iCount1 = 0;
			   int iCount2 = 0;


	 			zVIEW    wWebXfer = new zVIEW( );

			    RESULT = ActivateEmptyObjectInstance( wWebXfer, "wWebXfer", ViewToWindow, zSINGLE );
			    RESULT = CreateEntity( wWebXfer, "Work", zPOS_AFTER );
			    RESULT = CreateEntity( wWebXfer, "PersonRole", zPOS_AFTER );
			    SetAttributeFromString( wWebXfer, "PersonRole", "Type", "Faculty" );
    	        SetAttributeFromString( wWebXfer, "PersonRole", "WindowName", "Faculty1" );
			    RESULT = CreateEntity( wWebXfer, "PersonRole", zPOS_AFTER );
			    SetAttributeFromString( wWebXfer, "PersonRole", "Type", "Student" );
    	        SetAttributeFromString( wWebXfer, "PersonRole", "WindowName", "Student" );
			    RESULT = CreateEntity( wWebXfer, "PersonRole", zPOS_AFTER );
			    SetAttributeFromString( wWebXfer, "PersonRole", "Type", "Faculty" );
    	        SetAttributeFromString( wWebXfer, "PersonRole", "WindowName", "Faculty2" );
			    RESULT = CreateEntity( wWebXfer, "PersonRole", zPOS_AFTER );
			    SetAttributeFromString( wWebXfer, "PersonRole", "Type", "Faculty" );
    	        SetAttributeFromString( wWebXfer, "PersonRole", "WindowName", "Faculty3" );
			    SetNameForView( wWebXfer, "wWebXfer", null, zLEVEL_TASK );

   		        //DropEntity( wWebXfer, "PersonRole", zREPOS_NONE); kkk
   		        RESULT = DeleteEntity( wWebXfer, "PersonRole", zPOS_NEXT );
    		    Assert.assertEquals("deleteEntity with pos NEXT should position on an existing entity if one exists. ", 0, CheckExistenceOfEntity( wWebXfer, "PersonRole" ));
   		        RESULT = DropEntity( wWebXfer, "PersonRole", zPOS_NEXT );
    		    Assert.assertEquals("dropEntity with pos NEXT should position on an existing entity if one exists. ", 0, CheckExistenceOfEntity( wWebXfer, "PersonRole" ));
    		    SetCursorFirstEntity( wWebXfer, "PersonRole", "");
   		        RESULT = DeleteEntity( wWebXfer, "PersonRole", zPOS_PREV );
    		    Assert.assertEquals("dropEntity with pos PREV should position on an existing entity if one exists. ", 0, CheckExistenceOfEntity( wWebXfer, "PersonRole" ));

 			return 0;
		}

//		@Test
		public int
		testClsLstCActivateTime( View ViewToWindow )
		{
			zVIEW    lClsLstC = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

			o_BuildlClsLst2( ViewToWindow, vTempViewVar_0, 162 );
            RESULT = ActivateObjectInstance( lClsLstC, "lClsLstC", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            //RESULT = ActivateObjectInstance( lClsLstC, "lClsLstC", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            DropView( vTempViewVar_0 );
            //:NAME VIEW lClsLstC "lClsLstC"
            SetNameForView( lClsLstC, "lClsLstC", null, zLEVEL_TASK );
			DropView( lClsLstC );
			return 0;
		}

		public int
		testmUserActivateTime( View ViewToWindow )
		{

			zVIEW    mUser = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

	         o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
	         RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
	         DropView( vTempViewVar_0 );
	         SetNameForView( mUser, "mUser", null, zLEVEL_TASK );
			 DropView( mUser );
			 return 0;

		}

		public int
		testmUserActivateTimeAdmin( View ViewToWindow )
		{

			zVIEW    mUser = new zVIEW( );
			zVIEW    mUserT = new zVIEW( );
			zVIEW    vTempViewVar_1 = new zVIEW( );
			int RESULT=0;

	         //:ACTIVATE mUserT WHERE mUserT.User.UserName = "Admin"
	         o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_1, "halll" );
	         TraceLineS("***  Before mUser Normal ", "");
	         RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_1, zSINGLE );
	         TraceLineS("***  After mUser Normal ", "");
	         DropView( vTempViewVar_1 );
	         SetNameForView( mUser, "mUser", null, zLEVEL_TASK );

		     o_fnLocalBuildmUserAdmin( ViewToWindow, vTempViewVar_1 );

	         TraceLineS("***  Before mUserT ", "");
	         RESULT = ActivateObjectInstance( mUserT, "mUser", ViewToWindow, vTempViewVar_1, zSINGLE );
	         SetNameForView( mUserT, "mUserT", null, zLEVEL_TASK );
	         TraceLineS("***  After mUserT ", "");
			 DropView( mUser );
			 DropView( mUserT );
	         return 0;

		}

		public int
		testClsIncludeError( View ViewToWindow )
		{
			zVIEW    TermslClsLstC = new zVIEW( );
			zVIEW    lTermLST = new zVIEW( );
			zVIEW    lClsLstC = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

			// We activate an empty lClsLstC (TermslClsLstC), then activate lClsLstC for the collegeterm 162.
			// Then we loop through the classes in lClsLstC and include them into TermsLClsLstC.  After the first
			// include of Class, the child entity Course is missing.  All subsequent includes seem to work fine.

		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );

		      ActivateEmptyObjectInstance( TermslClsLstC, "lClsLstC", ViewToWindow, zMULTIPLE );
		      //:NAME VIEW TermslClsLstC "TermslClsLstC"
		      SetNameForView( TermslClsLstC, "TermslClsLstC", null, zLEVEL_TASK );

		      //:FOR EACH lTermLST.CollegeTerm
		      RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst("ID",162 ).toInt();
		      if ( RESULT > zCURSOR_UNCHANGED )
		      {
		         //:// We want to load this term for class registration if the term is marked "OpenForFaculty" or if the current
		         //:// date is between the course start date and the add/drop deadline date (this means the term is open for
		         //:// add/drops.
		         //:IF lTermLST.CollegeTerm.OpenForFacultyFlag = "Y" //OR
		         if ( CompareAttributeToString( lTermLST, "CollegeTerm", "OpenForFacultyFlag", "Y" ) == 0 )
		         {

		            //:ACTIVATE lClsLstC Multiple WHERE lClsLstC.CollegeTerm.ID = lTermLST.CollegeTerm.ID
		            //:                                 AND lClsLstC.Class.Status != "C"
		        	o_fnLocalBuildlClsLst( lTermLST, vTempViewVar_0, 162 );
		            RESULT = ActivateObjectInstance( lClsLstC, "lClsLstC", lTermLST, vTempViewVar_0, zMULTIPLE );
		            DropView( vTempViewVar_0 );
		            //:NAME VIEW lClsLstC "lClsLstC"
		            SetNameForView( lClsLstC, "lClsLstC", null, zLEVEL_TASK );
		            //:FOR EACH lClsLstC.Class
		            RESULT = lClsLstC.cursor( "Class" ).setFirst().toInt();
		            while ( RESULT > zCURSOR_UNCHANGED )
		            {
		               //:INCLUDE TermslClsLstC.Class FROM lClsLstC.Class
		               RESULT = IncludeSubobjectFromSubobject( TermslClsLstC, "Class", lClsLstC, "Class", zPOS_AFTER );
		  	           RESULT = TermslClsLstC.cursor("Course").checkExistenceOfEntity().toInt();
		   			   Assert.assertEquals("Course after include of Class does not exist.", 0, RESULT);

		               RESULT = lClsLstC.cursor( "Class" ).setNextContinue().toInt();;
		               //:END
		            }

		            //:END
		            //:DropObjectInstance( lClsLstC )
		            DropObjectInstance( lClsLstC );
		         }

		         RESULT = lTermLST.cursor( "CollegeTerm" ).setNextContinue().toInt();;
		         //:END
		      }
				DropView( lTermLST );

		      return 0;

		}

		public int
		testActivatesHost( View     ViewToWindow )
		{
			zVIEW    sHost = new zVIEW( );
			int RESULT=0;

		    RESULT = ActivateObjectInstance( sHost, "sHost", ViewToWindow, 0, zSINGLE );
		    //:NAME VIEW sHost "sHost"
		    SetNameForView( sHost, "sHost", null, zLEVEL_TASK );
			DropView( sHost );

			return ( 0 );
		}

		public int
		testCheckExistenceWithRecursiveEnt( View     ViewToWindow )
		{
			zVIEW    vReportDef = new zVIEW( );
			int RESULT=0;

		    SfActivateSysOI_FromFile( vReportDef, "TZRPSRCO", ViewToWindow,
		    		zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/rAdvTst.xrp", zSINGLE );

		    RESULT = CheckExistenceOfEntity( vReportDef, "DrivingViewObjRef" );

			DropView( vReportDef );
			return ( 0 );
		}

		public void testZeidonStringCompare( View     ViewToWindow )
		{
			int nRC = 0;

			// Also going to test zSearchAndReplace
			String szLocation = "NY:AMHERSTNY";
            {StringBuilder sb_szLocation;
            if ( szLocation == null )
               sb_szLocation = new StringBuilder( 32 );
            else
               sb_szLocation = new StringBuilder( szLocation );
            zSearchAndReplace( sb_szLocation, 256, "NY:", "NY: " );
            szLocation = sb_szLocation.toString( );}

            if (!szLocation.equals("NY: AMHERSTNY"))
            	Assert.assertEquals("zSearchAndReplace not working!", 1, 0);

            szLocation = "111-11-1111";
            {StringBuilder sb_szLocation;
            if ( szLocation == null )
               sb_szLocation = new StringBuilder( 32 );
            else
               sb_szLocation = new StringBuilder( szLocation );
            zSearchAndReplace( sb_szLocation,25, "-", "" );
            szLocation = sb_szLocation.toString( );}
            if (!szLocation.equals("111111111"))
            	Assert.assertEquals("zSearchAndReplace not working!", 1, 0);

            szLocation = "NY:AMHERSTNYNY:AMHERSTNY";
            {StringBuilder sb_szLocation;
            if ( szLocation == null )
               sb_szLocation = new StringBuilder( 32 );
            else
               sb_szLocation = new StringBuilder( szLocation );
            zSearchAndReplace( sb_szLocation,25, "NY:", "NY: " );
            szLocation = sb_szLocation.toString( );}
            if (!szLocation.equals("NY: AMHERSTNYNY: AMHERSTNY"))
            	Assert.assertEquals("zSearchAndReplace not working!", 1, 0);

            szLocation = "NY:NY:NY:XXX";
            {StringBuilder sb_szLocation;
            if ( szLocation == null )
               sb_szLocation = new StringBuilder( 32 );
            else
               sb_szLocation = new StringBuilder( szLocation );
            zSearchAndReplace( sb_szLocation,25, "NY:", "NY: " );
            szLocation = sb_szLocation.toString( );}
            if (!szLocation.equals("NY: NY: NY: XXX"))
            	Assert.assertEquals("zSearchAndReplace not working!", 1, 0);


	        byte[] buffer2=new byte[]{97, 0, 0, 0, 0, 0, 0, 0, 0};
	        //String str1 = new String(buffer2);
	        int size = 0;
			try {
	        while (size < buffer2.length)
	        {
	            if (buffer2[size] == 0)
	            {
	                break;
	            }
	            size++;
	        }
	        String str1;
				str1 = new String(buffer2, 0, size, "UTF-8");
	        //String str1 = "a";
	        long l1 = Long.parseLong(str1, 16);

	        long l = 10;
	        String s = Long.toString(l, 16);
	        //Long.parseLong(String s, 16)
	        System.out.println(s);
	        long l2 = Long.parseLong(s, 16);

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// I had code that was comparing two dates and it wasn't working correctly.
			// Made a change to zstrcmp.

			   //:IF "aaaa" <= "bbbb"
			   if ( ZeidonStringCompare( "aaaa", 1, 0, "bbbb", 1, 0, 5 ) >= 0 )
			   {
			      TraceLineS( "**** TRUE ****", "" );
			   }
			   else
			   {
				   Assert.assertEquals("'aaaa' >= 'bbbb' should be true! ", 1, 0);
			   }

			   //:END
			   //:IF "bbbb" <= "aaaa"
			   if ( ZeidonStringCompare( "bbbb", 1, 0, "aaaa", 1, 0, 5 ) >= 0 )
			   {
        		  Assert.assertEquals("'bbbb' <= 'aaaa' is not true! ", 1, 0);
			   }

			   //:END
			   //:IF "bbbb" >= "aaaa"
			   if ( ZeidonStringCompare( "bbbb", 1, 0, "aaaa", 1, 0, 5 ) <= 0 )
			   {
			      TraceLineS( "**** TRUE ****", "" );
			   }
			   else
			   {
				   Assert.assertEquals("'bbbb' >= 'aaaa' should be true! ", 1, 0);
			   }

			   //:END
			   //:IF "aaaa" >= "bbbb"
			   if ( ZeidonStringCompare( "aaaa", 1, 0, "bbbb", 1, 0, 5 ) <= 0 )
			   {
        		  Assert.assertEquals("'aaaa' >= 'bbbb' is not true! ", 1, 0);
			   }

			   //:IF "20130107" <= "20131216"
			   if ( ZeidonStringCompare( "20130107", 1, 0, "20131216", 1, 0, 9 ) >= 0 )
			   {
				   TraceLineS( "**** TRUE ****", "" );
			   }
			   else
			   {
				   Assert.assertEquals("'20130107' >= '20131216' should be true! ", 1, 0);
			   }

			   //:IF "20130107" >= "20131216"
			   if ( ZeidonStringCompare( "20130107", 1, 0, "20131216", 1, 0, 9 ) <= 0 )
			   {
        		  Assert.assertEquals("'20130107' >= '20131216' is not true! ", 1, 0);
			   }

		}

		public int
		testRecursiveEntities( View     ViewToWindow )
		{
			zVIEW    vReportDef = new zVIEW( );
			zVIEW    vReportRecurs = new zVIEW( );
			int RESULT=0;

	        ClassLoader loader = ClassLoader.getSystemClassLoader();
	        loader.setPackageAssertionStatus( "com.quinsoft.zeidon", true );

		    SfActivateSysOI_FromFile( vReportDef, "TZRPSRCO", ViewToWindow,
		    		zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/rAdvTst2.xrp", zSINGLE );

		    CreateViewFromView( vReportRecurs, vReportDef );

		    //reportRecursiveOper( vReportDef, vReportRecurs);
		    reportLODRecurs( vReportDef, vReportRecurs);

	        loader.clearAssertionStatus();
			DropView( vReportDef );
			DropView( vReportRecurs );

			return ( 0 );
		}

		public int
		reportLODRecurs(View vReportDef, View vReportDefRecurs )
		{
			   int RESULT = 0;
			   int lTempInteger_1 = 0;
			   int nRC = 0;


			   //:// Build one level of the PartialReportEntity subobject, matching the structure of the Driving LOD and
			   //:// setting the ReportDisplayFlag entity for any entity with a corresponding GroupSet entity in the report, or having
			   //:// a PartialReportEntityChild entity with a corresponding GroupSet entity. The flag is set to "D" if the entity has
			   //:// a corresponding GroupSet entity and to a "C" if it has a child with a corresponding GroupSet entity.

			   //:FOR EACH vReportDefRecurs.PartialReportEntity
			   RESULT = SetCursorFirstEntity( vReportDefRecurs, "PartialReportEntity", "" );
			   while ( RESULT > zCURSOR_UNCHANGED )
			   {
	               lTempInteger_1 = CheckExistenceOfEntity( vReportDefRecurs, "PartialReportEntityChild" );
	               if ( lTempInteger_1 == 0 )
	               {

	                  //:SetViewToSubobject( vReportDefRecurs, "PartialReportEntityChild" )
	                  SetViewToSubobject( vReportDefRecurs, "PartialReportEntityChild" );
	                  //:szIndentationSubValue = szIndentationValue + "      "
	                  nRC = reportLODRecurs( vReportDef, vReportDefRecurs );
	                  //:ResetViewFromSubobject( vReportDefRecurs )
	                  ResetViewFromSubobject( vReportDefRecurs );
	               }

			       RESULT = SetCursorNextEntity( vReportDefRecurs, "PartialReportEntity", "" );

			      //:END
			   }

		   return( 0 );
		}


		public int
		testActivatemStudentWithJoins( View     ViewToWindow )
		{
			zVIEW    mStudent = new zVIEW( );
			zVIEW    lTermLST = new zVIEW( );
			zVIEW    wXferO = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    //:CREATE ENTITY wXferO.Root
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    //:NAME VIEW wXferO "wXferO"
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			//:OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" )
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );
			//:SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.CurrentTermFlag = "Y"
			RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst( "CurrentTermFlag", "Y" ).toInt();
			//:ACTIVATE mStudent WHERE mStudent.Student.ID = mUser.Student.ID
		    o_fnLocalBuildmStudent( ViewToWindow, vTempViewVar_0, 16406 );
		    RESULT = ActivateObjectInstance( mStudent, "mStudent", ViewToWindow, vTempViewVar_0, zSINGLE );

		    mStudent.cursor( "Student" ).setAttribute( "RoommateQuestionaireReceivedDate", "" );
	        View v = zencas.activateOiFromFile( "mStudent", zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mstudent_ac.por" );
	        boolean c = mStudent.equalsOi( v );
	        Assert.assertTrue( "OIs differ", c );

		    System.out.println(RESULT);
		    DropView( vTempViewVar_0 );
			DropView( mStudent );
			return 0;
		}


		public int
		testhasAny( View     ViewToWindow )
		{
			zVIEW    mStudent = new zVIEW( );
			zVIEW    lTermLST = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;
			boolean bRC;

		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			//:OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" )
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

			// Multiple Root entity
    	    EntityCursor cursor = lTermLST.cursor( "CollegeTerm" );
		    bRC = cursor.hasAny();
 			Assert.assertEquals("hasAny() returns false for multiple root entity when it should be true. ", true, bRC);
    	    cursor = lTermLST.cursor( "CollegeYear" );
            CursorResult rc = cursor.setFirst();
		    bRC = cursor.hasAny();
 			Assert.assertEquals("hasAny() returns false for sub entity (CollegeYear) when it should be true. ", true, bRC);
 			// Position on last CollegeYear and see if hasAny comes back true.
 			cursor.setLast();
		    bRC = cursor.hasAny();
 			Assert.assertEquals("hasAny() returns false for last sub entity (CollegeYear) when it should be true. ", true, bRC);

			//:ACTIVATE mStudent WHERE mStudent.Student.ID = mUser.Student.ID
		    o_fnLocalBuildmStudent( ViewToWindow, vTempViewVar_0, 16406 );
		    RESULT = ActivateObjectInstance( mStudent, "mStudent", ViewToWindow, vTempViewVar_0, zSINGLE );

		    // Single Root entity
    	    cursor = mStudent.cursor( "Student" );
		    bRC = cursor.hasAny();
 			Assert.assertEquals("hasAny() returns false for single root entity when it should be true. ", true, bRC);
    	    cursor = mStudent.cursor( "Person" );
		    bRC = cursor.hasAny();
 			Assert.assertEquals("hasAny() returns false for sub entity (Person) when it should be true. ", true, bRC);
			DropView( mStudent );
			DropView( lTermLST );

			return 0;
		}

		public int
		testActivateDynamicDomainAdminDivError( View     ViewToWindow )
		{
			zVIEW    mStudent = new zVIEW( );
			zVIEW    lTermLST = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );

			//:// Activate Student data.
			//:ACTIVATE mStudent WHERE mStudent.Student.ID = lAdvisee.Advisee.ID
			//:            RESTRICTING mStudent.ContactActivity TO
			//:                        mStudent.ContactActivity.ID = 0
			o_fnLocalBuildQual_50( ViewToWindow, vTempViewVar_0, 15541 );
			RESULT = ActivateObjectInstance( mStudent, "mStudent", ViewToWindow, vTempViewVar_0, zSINGLE );
			mStudent.log().info( "Entity count = %d", mStudent.getEntityCount( true ) );
			DropView( vTempViewVar_0 );
			DropView( mStudent );
			DropView( lTermLST );

			return 0;
		}


		public int
		testAttributeUpdated( View     ViewToWindow )
		{
			zVIEW    mStudent = new zVIEW( );
			zVIEW    lTermLST = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;
			boolean bRC;

		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			//:OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" )
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

			//:ACTIVATE mStudent WHERE mStudent.Student.ID = mUser.Student.ID
		    o_fnLocalBuildmStudent( ViewToWindow, vTempViewVar_0, 16406 );
		    RESULT = ActivateObjectInstance( mStudent, "mStudent", ViewToWindow, vTempViewVar_0, zSINGLE );

			RESULT = AttributeUpdated( mStudent, "Student", "Status");
 			Assert.assertEquals("AttributeUpdated returns true when it should be false. ", 0, RESULT);

		    if ( mStudent.cursor("Student").getStringFromAttribute("Status").equals("I") )
		    {
				SetAttributeFromString( mStudent, "Student", "Status", "I");
				RESULT = AttributeUpdated( mStudent, "Student", "Status");
	 			Assert.assertEquals("AttributeUpdated returns true when it should be false. ", 0, RESULT);

				SetAttributeFromString( mStudent, "Student", "Status", "A");
				RESULT = AttributeUpdated( mStudent, "Student", "Status");
				Assert.assertEquals("AttributeUpdated returns false when it should be true. ", 1, RESULT);
		    }
		    else
		    {
				SetAttributeFromString( mStudent, "Student", "Status", "A");
				RESULT = AttributeUpdated( mStudent, "Student", "Status");
	 			Assert.assertEquals("AttributeUpdated returns true when it should be false. ", 0, RESULT);

				SetAttributeFromString( mStudent, "Student", "Status", "I");
				RESULT = AttributeUpdated( mStudent, "Student", "Status");
				Assert.assertEquals("AttributeUpdated returns false when it should be true. ", 1, RESULT);
		    }

			RESULT = AttributeUpdated( mStudent, "Student", "LegacyTransferEarnedCredits");
			Assert.assertEquals("AttributeUpdated returns false when it should be true. ", 0, RESULT);

		    SetAttributeFromDecimal( mStudent, "Student", "LegacyTransferEarnedCredits", 103.3 );
			RESULT = AttributeUpdated( mStudent, "Student", "LegacyTransferEarnedCredits");
			Assert.assertEquals("AttributeUpdated returns false when it should be true. ", 1, RESULT);
			DropView( mStudent );
			DropView( lTermLST );
			return 0;
		}


		public int
		testActivatelAdviseeWithRestricts( View     ViewToWindow )
		{
			View     lAdvisee;
			zVIEW    lTermLST = new zVIEW( );
			zVIEW    wXferO = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

			// The following activate does not work properly when BindAllValues=true.  AdviseeStudentTrack is
			// null.  If BindAllValues=false then it works correctly.
			// Is there a way to set BindAllValues here so it doesn't matter what zeidon.ini is set to?

	         //:// Current list is Inactive, drop that OI and get original view of Active Advisees.
	         //:ACTIVATE lAdvisee Multiple
	         //:   WHERE lAdvisee.Faculty.ID = FacultyID
	         //:     AND (lAdvisee.Advisee.Status = "A" OR lAdvisee.Advisee.Status = "D")
	         //:      RESTRICTING lAdvisee.Registration TO lAdvisee.Registration.Status = "T"
	         //:      RESTRICTING lAdvisee.AdviseeStudentTrack TO lAdvisee.Faculty.ID = FacultyID
	         o_fnLocalBuildlAdvisee( ViewToWindow, vTempViewVar_0, 29 );

	         ActivateOptions options = new ActivateOptions( ViewToWindow.getTask() );
	         options.overrideConfigValue( "BindAllValues", "true" );
	         options.setActivateFlags( ActivateFlags.MULTIPLE );
	         lAdvisee = ViewToWindow.activateObjectInstance( "lAdvisee", vTempViewVar_0, options );
	         DropView( vTempViewVar_0 );

//	         lAdvisee.writeOiToFile( "/tmp/f1.por", 0 );
	         RESULT = lAdvisee.cursor("AdviseeStudentTrack").checkExistenceOfEntity().toInt();
   			 Assert.assertEquals("AdviseeStudentTrack was not correctly activated when BindAllValues=true.", 0, RESULT);
 			DropView( lAdvisee );

			return 0;
		}


		public int
		testHierarchicalCursor( View     ViewToWindow )
		{
			zVIEW    lAdvisee = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;
			int RESULT2=0;

			// The following activate does not work properly when BindAllValues=true.  AdviseeStudentTrack is
			// null.  If BindAllValues=false then it works correctly.
			// Is there a way to set BindAllValues here so it doesn't matter what zeidon.ini is set to?

	         //:// Current list is Inactive, drop that OI and get original view of Active Advisees.
	         //:ACTIVATE lAdvisee Multiple
	         //:   WHERE lAdvisee.Faculty.ID = FacultyID
	         //:     AND (lAdvisee.Advisee.Status = "A" OR lAdvisee.Advisee.Status = "D")
	         //:      RESTRICTING lAdvisee.Registration TO lAdvisee.Registration.Status = "T"
	         //:      RESTRICTING lAdvisee.AdviseeStudentTrack TO lAdvisee.Faculty.ID = FacultyID
	         o_fnLocalBuildlAdvisee( ViewToWindow, vTempViewVar_0, 29 );
	         RESULT = ActivateObjectInstance( lAdvisee, "lAdvisee", ViewToWindow, vTempViewVar_0, zMULTIPLE );
	         DropView( vTempViewVar_0 );

             DefineHierarchicalCursor( lAdvisee, "Advisee" );

	         StringBuilder sbReturnedEntityName = new StringBuilder( 32 );;
	         MutableInt mi_ReturnedHierLevel = new MutableInt( 0 );
	         RESULT = SetCursorNextEntityHierarchical( mi_ReturnedHierLevel, sbReturnedEntityName, lAdvisee );

	         // Next heirarchical cursor entityName should be AdviseeStudentTrack or AdviseePerson.  If we are using BindAllValues=true since
	         // there is currently an error, so AdviseeStudentTrack will never exist but AdviseePerson does.
	         // AdviseeStudentTrack should be the next hierarchical cursor.
	         if ( !sbReturnedEntityName.toString().equals("AdviseeStudentTrack") && !sbReturnedEntityName.toString().equals("AdviseePerson") )
   			    Assert.assertEquals("SetCursorNextHierarchical did not set correctly to next hierarchical cursor.", 0, -1);

				DropView( lAdvisee );
			return 0;
		}

		public int
		testDerivedDerived( View     ViewToWindow )
		{
			zVIEW    lTrnscpt = new zVIEW( );
			zVIEW    lTermLST = new zVIEW( );
			zVIEW    wXferO = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    //:CREATE ENTITY wXferO.Root
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    //:NAME VIEW wXferO "wXferO"
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    String dateStr = wXferO.cursor("Root").getStringFromAttribute("dCurrentDate");
			DropView( wXferO );
			return 0;
		}

		public int
		testActivateMissingPerson( View     ViewToWindow )
		{
			zVIEW    lTrnscpt = new zVIEW( );
			zVIEW    lTermLST = new zVIEW( );
			zVIEW    wXferO = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    //:CREATE ENTITY wXferO.Root
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    //:NAME VIEW wXferO "wXferO"
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			//:OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" )
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );
			//:SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.CurrentTermFlag = "Y"
			RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst( "CurrentTermFlag", "Y" ).toInt();
			//:ACTIVATE mStudent WHERE mStudent.Student.ID = mUser.Student.ID
			o_fnLocalBuildlTrnscpt( ViewToWindow, vTempViewVar_0, 16406 );
			RESULT = ActivateObjectInstance( lTrnscpt, "lTrnscpt", ViewToWindow, vTempViewVar_0, zSINGLE );

	        lTrnscpt_Object m_lTrnscpt_Object = new lTrnscpt_Object( lTrnscpt );
	        m_lTrnscpt_Object.olTrnscpt_BuildTranscript( lTrnscpt, "U" );

		    System.out.println(RESULT);
		    DropView( vTempViewVar_0 );
			DropView( lTrnscpt );
			return 0;
		}

		public int
		testActivateMissingCollegeTerm( View     ViewToWindow )
		{
			zVIEW    mStudenC = new zVIEW( );
			zVIEW    lTrnscpt = new zVIEW( );
			zVIEW    lTermLST = new zVIEW( );
			zVIEW    wXferO = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    //:CREATE ENTITY wXferO.Root
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    //:NAME VIEW wXferO "wXferO"
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			//:OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" )
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );
			//:SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.CurrentTermFlag = "Y"
			RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst( "CurrentTermFlag", "Y" ).toInt();
			//:ACTIVATE mStudent WHERE mStudent.Student.ID = mUser.Student.ID
			o_fnLocalBuildlTrnscpt( ViewToWindow, vTempViewVar_0, 16406 );
			RESULT = ActivateObjectInstance( lTrnscpt, "lTrnscpt", ViewToWindow, vTempViewVar_0, zSINGLE );

	        lTrnscpt_Object m_lTrnscpt_Object = new lTrnscpt_Object( lTrnscpt );
	        m_lTrnscpt_Object.olTrnscpt_BuildTranscript( lTrnscpt, "U" );

	        o_fnLocalBuildmStudenC( ViewToWindow, vTempViewVar_0, 16406 );
	        RESULT = ActivateObjectInstance( mStudenC, "mStudenC", ViewToWindow, vTempViewVar_0, zSINGLE );
	        DropView( vTempViewVar_0 );
	        SetNameForView( mStudenC, "mStudenC", null, zLEVEL_TASK );

	        mStudenC_Object m_mStudenC_Object = new mStudenC_Object( mStudenC );
	        m_mStudenC_Object.omStudenC_BuildStuClassHistory( mStudenC, lTrnscpt, "U" );

		    System.out.println(RESULT);
		    DropView( vTempViewVar_0 );
			DropView( mStudenC );
			return 0;
		}


		public int
		testYearErrorInmStudenC( View     ViewToWindow )
		{
			zVIEW    mStudenC = new zVIEW( );
			zVIEW    lTrnscpt = new zVIEW( );
			zVIEW    lTermLST = new zVIEW( );
			zVIEW    wXferO = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			String   szYear="";
			int RESULT=0;

		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    //:CREATE ENTITY wXferO.Root
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    //:NAME VIEW wXferO "wXferO"
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			//:OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" )
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );
			//:SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.CurrentTermFlag = "Y"
			RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst( "CurrentTermFlag", "Y" ).toInt();

	        o_fnLocalBuildmStudenC( ViewToWindow, vTempViewVar_0, 16406 );
	        RESULT = ActivateObjectInstance( mStudenC, "mStudenC", ViewToWindow, vTempViewVar_0, zSINGLE );
	        DropView( vTempViewVar_0 );
	        SetNameForView( mStudenC, "mStudenC", null, zLEVEL_TASK );
			RESULT = mStudenC.cursor( "Registration" ).setFirst().toInt();

	        RESULT = mStudenC.cursor("RegistrationClassCollegeYear").checkExistenceOfEntity().toInt();
   		    Assert.assertEquals("RegistrationClassCollegeYear does not exists but it should...", 0, RESULT);
	        szYear = mStudenC.cursor("RegistrationClassCollegeYear").getStringFromAttribute("Year");
   		    Assert.assertEquals("Attributes in RegistrationClassCollegeYear are blank.", "2007-2008", szYear);
			DropView( mStudenC );
			DropView( lTermLST );
			DropView( wXferO );

			return 0;
		}


		public int
		testActivatemSAProf( View     ViewToWindow )
		{
			zVIEW    mSAProf = new zVIEW( );
			zVIEW    lTermLST = new zVIEW( );
			zVIEW    wXferO = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			//:OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" )
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );
			//:SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.CurrentTermFlag = "Y"
			RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst( "CurrentForStudentAccounts", "Y" ).toInt();
	         //:ACTIVATE mSAProf MULTIPLE //temp
	         //:   WHERE mSAProf.Student.ID = mSAStu.Student.ID
	         //:     AND mSAProf.ProfileAdministrativeDivision.ID = mSAStu.AdministrativeDivision.ID
	         //:   RESTRICTING mSAProf.Student TO mSAProf.Student.ID = 0
	         //:   RESTRICTING mSAProf.BillingPeriod TO mSAProf.BillingPeriod.ID = 0
	         //:   RESTRICTING mSAProf.SASubAccount TO mSAProf.SASubAccount.ID = 0
	         //:   RESTRICTING mSAProf.StudentAccountHistoricalProfile TO mSAProf.StudentAccountHistoricalProfile.ID = 0
	         o_fnLocalBuildmSAProf( ViewToWindow, vTempViewVar_0, 16406, 1 );
	         RESULT = ActivateObjectInstance( mSAProf, "mSAProf", ViewToWindow, vTempViewVar_0, zMULTIPLE );
     		 SetNameForView( mSAProf, "mSAProf", null, zLEVEL_TASK );
	         DropView( vTempViewVar_0 );
				DropView( mSAProf );
				DropView( lTermLST );

	         return 0;
		}


		public int
		testMultipleRootCommit( View     ViewToWindow )
		{
			zVIEW    mSAProf = new zVIEW( );
			zVIEW    lTermLST = new zVIEW( );
			zVIEW    wXferO = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			//:OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" )
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );
			//:SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.CurrentTermFlag = "Y"
			RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst( "CurrentForStudentAccounts", "Y" ).toInt();
	         //:ACTIVATE mSAProf MULTIPLE //temp
	         //:   WHERE mSAProf.Student.ID = mSAStu.Student.ID
	         //:     AND mSAProf.ProfileAdministrativeDivision.ID = mSAStu.AdministrativeDivision.ID
	         //:   RESTRICTING mSAProf.Student TO mSAProf.Student.ID = 0
	         //:   RESTRICTING mSAProf.BillingPeriod TO mSAProf.BillingPeriod.ID = 0
	         //:   RESTRICTING mSAProf.SASubAccount TO mSAProf.SASubAccount.ID = 0
	         //:   RESTRICTING mSAProf.StudentAccountHistoricalProfile TO mSAProf.StudentAccountHistoricalProfile.ID = 0
	         o_fnLocalBuildmSAProf( ViewToWindow, vTempViewVar_0, 16406, 1 );
	         RESULT = ActivateObjectInstance( mSAProf, "mSAProf", ViewToWindow, vTempViewVar_0, zMULTIPLE );
	         DropView( vTempViewVar_0 );
				DropView( mSAProf );
				DropView( lTermLST );

	         return 0;
		}

		public int
		testMissingRequiredGroup( View     ViewToWindow )
		{
			zVIEW    mMinorTrack = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;
			int iRequiredGroup=0;

			// When calling the activate for mDegTrk, the omDegTrk_ObjectConstraints operation is called.
			// When the OrderEntityForView on line 6273 is called, the entity RequiredGroup is no longer under
			// DegreeTrackRequiredGroup.  Not sure if this is because there is only one DegreeTrackRequiredGroup
			// because if I activate mDegTrk with degree track 1103 (where there are several DegreeTrackRequiredGroups),
			// the OrderEntityForView seems to work correctly.

			  omDegTrk_fnLocalBuildmDegTrk( ViewToWindow, vTempViewVar_0, 1479 );
			  //omDegTrk_fnLocalBuildmDegTrk( ViewToWindow, vTempViewVar_0, 1103 );
		      RESULT = ActivateObjectInstance( mMinorTrack, "mDegTrk", ViewToWindow, vTempViewVar_0, zSINGLE );
		      DropView( vTempViewVar_0 );
		      //:FOR EACH mMinorTrack.DegreeTrackRequiredGroup
		      RESULT = mMinorTrack.cursor( "DegreeTrackRequiredGroup" ).setFirst().toInt();
		      iRequiredGroup = mMinorTrack.cursor( "RequiredGroup" ).getIntegerFromAttribute("ID");
				DropView( mMinorTrack );

	         return 0;
		}

		public int
		testActivateControls( View ViewToWindow )
		{
			zVIEW    sHost = new zVIEW( );
			int      RESULT = 0;

			//:// ACTIVATE sHost
			//:ActivateObjectInstance( sHost, "sHost", zAnyView, 0,
			//:                        zSINGLE + zLEVEL_APPLICATION )
			ActivateObjectInstance( sHost, "sHost", ViewToWindow, null, zSINGLE + zLEVEL_APPLICATION );
			return ( 0 );
		}

		public int
		testMissingClass( View     ViewToWindow )
		{
			zVIEW    lClsLstC = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;
			int iRequiredGroup=0;

		 	  o_fnLocalBuildlClsLst( ViewToWindow, vTempViewVar_0, 29, 152 );
		      RESULT = ActivateObjectInstance( lClsLstC, "lClsLstC", ViewToWindow, vTempViewVar_0, zMULTIPLE );
		      DropView( vTempViewVar_0 );
		      //:NAME VIEW lClsLstC "lClsLstC"
		      SetNameForView( lClsLstC, "lClsLstC", null, zLEVEL_TASK );
		      //:FOR EACH mMinorTrack.DegreeTrackRequiredGroup
		      RESULT = lClsLstC.cursor( "Class" ).setFirst().toInt();

		      // When there was only one "Class", the entity "Course" was missing.  If there was
		      // more than one "Class", then "Course was there.
		      iRequiredGroup = lClsLstC.cursor( "Course" ).getIntegerFromAttribute("ID");
				DropView( lClsLstC );

	         return 0;
		}

		public int
		testSetAttributeFromAttribute( View     ViewToWindow )
		{
			zVIEW    wXferO = new zVIEW( );
			zVIEW    wWebXfer = new zVIEW( );
			int RESULT=0;

		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );

		    RESULT = ActivateEmptyObjectInstance( wWebXfer, "wWebXfer", ViewToWindow, zSINGLE );
		    RESULT = CreateEntity( wWebXfer, "Work", zPOS_AFTER );
		    SetNameForView( wWebXfer, "wWebXfer", null, zLEVEL_TASK );

		    // If we were to do a getStringFromAttribute, the derived function would get called but
		    // setAttributeFromAttribute doesn't call it.  Following should be calling
		    // wXferO_Object.owXferO_dCurrentDate
		    //So AddMonthsToDate fails.
		    wWebXfer.cursor("Work").setAttributeFromAttribute( "FromDate", wXferO, "Root", "dCurrentDate" );

		    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wWebXfer );
		    m_ZGLOBAL1_Operation.AddMonthsToDate( wWebXfer, "Work", "FromDate", -6 );
		     //iRequiredGroup = lClsLstC.cursor( "Course" ).getIntegerFromAttribute("ID");
			DropView( wXferO );
			DropView( wWebXfer );

	         return 0;
		}

		public int
		testDateSort( View     ViewToWindow )
		{
		    zVIEW    wDateSrt = new zVIEW( );
		    String szSort = "";

		    ActivateOI_FromFile( wDateSrt, "wDateSrt", ViewToWindow, "./src/test/resources/testdata/ZENCAs/wDateSrt1.por", zMULTIPLE );
			SetNameForView( wDateSrt, "wDateSrt", null, zLEVEL_TASK );

			szSort = "wDateSrt.Date2 D, wDateSrt.Date1 D";
			OrderEntityForView( wDateSrt, "wDateSrt", szSort );

		    return ( 0 );
		}

		public int
		testDatesTimes( View     ViewToWindow )
		{
			zVIEW    mUser = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;
			String szDate = "";

	         o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
	         RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
	         DropView( vTempViewVar_0 );

	         {StringBuilder sb_szDate;
	         if ( szDate == null )
	            sb_szDate = new StringBuilder( 32 );
	         else
	            sb_szDate = new StringBuilder( szDate );
	         new KZOEP1AA( task ).SysGetDateTime( sb_szDate );
	         //m_KZOEP1AA.SysGetDateTime( sb_szDate );
	         szDate = sb_szDate.toString( );}
	         //:mUser.User.LastLoginDateTime = szDate
	         SetAttributeFromString( mUser, "User", "LastLoginDateTime", szDate );
	         SetAttributeFromString( mUser, "User", "OnlineProspectInitialCreatedDate", szDate );

	         szDate = mUser.cursor("User").getStringFromAttribute("LastLoginDateTime");
	         szDate = mUser.cursor("User").getStringFromAttribute("OnlineProspectInitialCreatedDate");

	         //:COMMIT mUser
	         //RESULT = CommitObjectInstance( mUser );
	         DropView( mUser );

	         return ( 0 );
		}

		public int
		testSavingNewObject( View     ViewToWindow )
		{
			zVIEW    wConList = new zVIEW( );
			zVIEW    mClass = new zVIEW( );
			zVIEW    mUser = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int i=0;
			int iConListID=0;
			int RESULT=0;

			// This test tries to insert null values into the db for example:
			//INSERT INTO MM_PERSON_FOR_CONTACTLIST ( FK_ID_CONTACTLIST, FK_ID_PERSON  ) VALUES ( null, 122337 )

			// Currently this isn't giving an error, I assume I need to add an index to the database but I assume
			// there should be a value for FK_ID_CONTACTLIST.

		    RESULT = ActivateEmptyObjectInstance( wConList, "wConList", ViewToWindow, zSINGLE );
		    RESULT = CreateEntity( wConList, "ContactList", zPOS_AFTER );

	         o_fnLocalBuildmClass( ViewToWindow, vTempViewVar_0, 31967 );

	         RESULT = ActivateObjectInstance( mClass, "mClass", ViewToWindow, vTempViewVar_0, zSINGLE );
	         DropView( vTempViewVar_0 );

	         o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
	         RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zSINGLE );
	         DropView( vTempViewVar_0 );

	         wConList.cursor("ContactList").setAttribute("ListName", "KellysTest");
	         wConList.cursor("ContactList").setAttribute("Type", "W");
	         wConList.cursor("ContactList").setAttribute("Note", "This is a test");
	         //:INCLUDE wConListT.User FROM mUser.User
	         RESULT = IncludeSubobjectFromSubobject( wConList, "User", mUser, "User", zPOS_AFTER );
	         //RESULT = IncludeSubobjectFromSubobject( wConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );


	         RESULT = mClass.cursor( "Enrollment" ).setFirst().toInt();
	         while ( RESULT > zCURSOR_UNCHANGED )
	         {
	            //:IF lPersDrL.Person.wSelectedFlag = "Y"
	            if ( CheckExistenceOfEntity( mClass, "EnrolledStudentPerson" ) == 0 && i < 10 )
	            {
	            	i = i + 1;
	               //:INCLUDE wConListT.Person FROM lPersDrL.Person
	               RESULT = IncludeSubobjectFromSubobject( wConList, "Person", mClass, "EnrolledStudentPerson", zPOS_AFTER );
	            }

	            RESULT = mClass.cursor( "Enrollment" ).setNextContinue().toInt();;
	         }

	         MutableInt viewCluster = new MutableInt();
	         CreateViewCluster( wConList, viewCluster );
	         AddToViewCluster( viewCluster, wConList, 0 );
	         RESULT = CommitMultipleObjectInstances( viewCluster, new MutableInt() );
	         DropViewCluster( viewCluster );

//	         RESULT = CommitObjectInstance( wConList );
	         iConListID = wConList.cursor("ContactList").getIntegerFromAttribute("ID");
	         //:DropObjectInstance( wConListT )
	         DropObjectInstance( wConList );

	         // Now delete some of the person entries in contact list.
	         o_fnLocalBuildwConList( ViewToWindow, vTempViewVar_0, iConListID );
	         RESULT = ActivateObjectInstance( wConList, "wConList", ViewToWindow, vTempViewVar_0, zSINGLE );
	         DropView( vTempViewVar_0 );


	         RESULT = wConList.cursor( "Person" ).setFirst().toInt();
	         i = 0;
	         while ( RESULT > zCURSOR_UNCHANGED )
	         {
	            //:IF wConList.Person.wSelectedFlag = "Y"
	            if ( i < 4 )
	            {
	            	i = i + 1;
	                  RESULT = ExcludeEntity( wConList, "Person", zREPOS_NONE );
	            }

	            RESULT = wConList.cursor( "Person" ).setNextContinue().toInt();;
	         }
	         RESULT = CommitObjectInstance( wConList );

	         // Now delete the contact list.
	         RESULT = DeleteEntity( wConList, "ContactList", zPOS_NEXT );
	         RESULT = CommitObjectInstance( wConList );

	         DropObjectInstance( wConList );

	         return 0;
		}


		public int
		testExcludeIncludeSaveError( View     ViewToWindow )
		{
			zVIEW    wConList = new zVIEW( );
			zVIEW    mClass = new zVIEW( );
			zVIEW    mUser = new zVIEW( );
			zVIEW    vQualObject = new zVIEW( );
			zVIEW    mSAProf = new zVIEW( );
			zVIEW    mPlan = new zVIEW( );
			zVIEW    mSAPPlan = new zVIEW();
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int i=0;
			int iConListID=0;
			int RESULT=0;
			
			   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", ViewToWindow, zMULTIPLE );
			   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
			   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", 16406 );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
			
				
			   RESULT = ActivateObjectInstance( mSAProf, "mSAProf", ViewToWindow, vQualObject, zSINGLE );
			   DropView( vQualObject );

			   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", ViewToWindow, zMULTIPLE );
			   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PaymentPlan" );
			   RESULT = ActivateObjectInstance( mSAPPlan, "mSAPPlan", ViewToWindow, vQualObject, zMULTIPLE );
			   
			   if ( CheckExistenceOfEntity( mSAProf, "PaymentPlan") == 0 )
		            RESULT = ExcludeEntity( mSAProf, "PaymentPlan", zREPOS_AFTER );
				   
		         RESULT = IncludeSubobjectFromSubobject( mSAProf, "PaymentPlan", mSAPPlan, "PaymentPlan", zPOS_AFTER );
		         RESULT = CommitObjectInstance( mSAProf );
		         
		            
		            RESULT = ExcludeEntity( mSAProf, "PaymentPlan", zREPOS_AFTER );
		            RESULT = IncludeSubobjectFromSubobject( mSAProf, "PaymentPlan", mSAPPlan, "PaymentPlan", zPOS_AFTER );

		            RESULT = CommitObjectInstance( mSAProf );
		            RESULT = CommitObjectInstance( mSAProf );
		            
		            // After the second commit, although the browser shows a valid PaymentPlan entity, the database FK_ID_PaymentPlan
		            // has been set to null because the first ExcludedEntity is still set as EX and so the code thinks it needs
		            // to do an update.
					   DropView( mSAProf );
		         
					   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", ViewToWindow, zMULTIPLE );
					   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
					   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
					   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
					   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
					   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
					   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", 16406 );
					   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
											
					   RESULT = ActivateObjectInstance( mSAProf, "mSAProf", ViewToWindow, vQualObject, zSINGLE );
					   DropView( vQualObject );
					   
			            // After the second commit, although the browser shows a valid PaymentPlan entity, the database FK_ID_PaymentPlan
			            // has been set to null because the first ExcludedEntity is still set as EX and so the code thinks it needs
			            // to do an update.
					   Assert.assertEquals("No PAYMENTPLAN", 0, CheckExistenceOfEntity( mSAProf, "PaymentPlan"));		            

	         return 0;
		}
		
		public int
		testImportPorError( View     ViewToWindow )
		{
			zVIEW    mStudenC = new zVIEW( );
		    String   strGridEditCtl1 = null;
			int RESULT=0;

	   		//ActivateOI_FromFile( mStudenC, "mStudenC", ViewToWindow, "./testdata/ZENCAs/mStudenCDerivedDate.por", zSINGLE );
			return 0;
		}


		public int
		testDerivedDate( View     ViewToWindow )
		{
			zVIEW    mStudent = new zVIEW( );
			zVIEW    mStudenC = new zVIEW( );
		    String   strGridEditCtl1 = null;
			int RESULT=0;

	   		ActivateOI_FromFile( mStudent, "mStudent", ViewToWindow, zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mStudentDerivedDate.por", zSINGLE );
	   		ActivateOI_FromFile( mStudenC, "mStudenC", ViewToWindow, zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mStudenCDerivedDate.por", zSINGLE );
			SetNameForView( mStudenC, "mStudenC", null, zLEVEL_TASK );
            // I get an error on the derived attribute dLastAuditDateTime because GetVariableFromAttribute returns the
			// date in the format "08/19/2011 09:45:20.0 AM" which causes an error in StoreStringInRecord.
			// If I add the context "YYYYMMDDHHMMSSS" it works fine.
			//GetVariableFromAttribute( sb_szDate, mi_lTempInteger_2, 'S', 101, mStudenC, "SavedDegreeAudit", "CreatedDateTime", "", 0 );			//com.quinsoft.zeidon.InvalidAttributeValueException: Invalid value for attribute
	        strGridEditCtl1 = mStudent.cursor( "MajorDegreeTrack" ).getStringFromAttribute( "dLastAuditDateTime", "" );
 	        System.out.println(strGridEditCtl1);
	         DropObjectInstance( mStudent );
	         DropObjectInstance( mStudenC );
			return 0;
		}

		public int
		testVariousItems( View ViewToWindow )
		{
		   zVIEW    mFAProf = new zVIEW( );
		   //:STRING ( 20 )  szTestString
		   String   szTestString = null;
		   //:STRING ( 20 )  szContextDate
		   String   szContextDate = null;
		   //:STRING ( 8 )   szContextDate8
		   String   szContextDate8 = null;
		   //:STRING ( 200 ) szRevenue
		   String   szRevenue = null;
		   String   ClassCollegeTermSemester = null;
		   //:INTEGER DaysInMonth
		   int      DaysInMonth = 0;
		   //:SHORT   nRC
		   int      nRC = 0;
		   int      RESULT = 0;
		   int      lTempInteger_0 = 0;
		   int      lTempInteger_1 = 0;

	       //Assert.assertTrue("Wrong attribute value.", true);
   		   //Assert.assertEquals("PerProSATransactionCode was not correctly included.", CheckExistenceOfEntity( mFAProf, "PerProSATransactionCode" ), 0);


		   //:ActivateOI_FromFile( mFAProf, "mFAProf", ViewToWindow, "c:\temp\mFAProfO.por", zSINGLE )
		   ActivateOI_FromFile( mFAProf, "mFAProf", ViewToWindow, zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mFAProfO.por", zSINGLE );
		   //:NAME VIEW mFAProf "mFAProf"
		   SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );

		   // Check contexts
           {MutableInt mi_lTempInteger_23 = new MutableInt( lTempInteger_1 );
           StringBuilder sb_ClassCollegeTermSemester;
           sb_ClassCollegeTermSemester = new StringBuilder( 32 );
           GetVariableFromAttribute( sb_ClassCollegeTermSemester, mi_lTempInteger_23, 'S', 3, mFAProf, "EnrolledClassCollegeTerm", "Semester", "", 0 );
           lTempInteger_1 = mi_lTempInteger_23.intValue( );
           ClassCollegeTermSemester = sb_ClassCollegeTermSemester.toString( );}
           System.out.println(ClassCollegeTermSemester);
           Assert.assertTrue("Invalid CollegeSemester using no context", ClassCollegeTermSemester.equals("01"));

           {StringBuilder sb_ClassCollegeTermSemester;
           if ( ClassCollegeTermSemester == null )
              sb_ClassCollegeTermSemester = new StringBuilder( 32 );
           else
              sb_ClassCollegeTermSemester = new StringBuilder( ClassCollegeTermSemester );
               GetStringFromAttributeByContext( sb_ClassCollegeTermSemester, mFAProf, "EnrolledClassCollegeTerm", "Semester", "CollegeSemester", 50 );
           ClassCollegeTermSemester = sb_ClassCollegeTermSemester.toString( );}
           Assert.assertTrue("Invalid CollegeSemester using CollegeSemester context", ClassCollegeTermSemester.equals("Fall"));

		   //:// Check Date Formatting.

		   //:GetStringFromAttributeByContext( szContextDate, mFAProf, "FinAidAward", "DateAccepted", "MM/DD/YYYY", 20 )
		   {StringBuilder sb_szContextDate;
		   if ( szContextDate == null )
		      sb_szContextDate = new StringBuilder( 32 );
		   else
		      sb_szContextDate = new StringBuilder( szContextDate );
		       GetStringFromAttributeByContext( sb_szContextDate, mFAProf, "FinAidAward", "DateAccepted", "MM/DD/YYYY", 20 );
		   szContextDate = sb_szContextDate.toString( );}
		   //:TraceLineS( "*** Date: ", szContextDate )
		   TraceLineS( "*** Date: ", szContextDate );
		   //:IF szContextDate != "10/28/2010"
           Assert.assertEquals("Invalid MM/DD/YYYY Date Format", ZeidonStringCompare( szContextDate, 1, 0, "10/28/2010", 1, 0, 21 ), 0);

		   //:END

		   //:GetStringFromAttributeByContext( szContextDate, mFAProf, "FinAidAward", "DateAccepted", "DD-MM-YYYY", 20 )
		   {StringBuilder sb_szContextDate;
		   if ( szContextDate == null )
		      sb_szContextDate = new StringBuilder( 32 );
		   else
		      sb_szContextDate = new StringBuilder( szContextDate );
		       GetStringFromAttributeByContext( sb_szContextDate, mFAProf, "FinAidAward", "DateAccepted", "DD-MM-YYYY", 20 );
		   szContextDate = sb_szContextDate.toString( );}
		   //:TraceLineS( "*** Date: ", szContextDate )
		   TraceLineS( "*** Date: ", szContextDate );
		   //:IF szContextDate != "28-10-2010"
           Assert.assertEquals("Invalid DD-MM-YYYY Date Format", ZeidonStringCompare( szContextDate, 1, 0, "28-10-2010", 1, 0, 21 ), 0);

		   //:GetStringFromAttributeByContext( szContextDate, mFAProf, "FinAidAward", "DateAccepted", "MonthDDYYYY", 20 )
		   {StringBuilder sb_szContextDate;
		   if ( szContextDate == null )
		      sb_szContextDate = new StringBuilder( 32 );
		   else
		      sb_szContextDate = new StringBuilder( szContextDate );
		       GetStringFromAttributeByContext( sb_szContextDate, mFAProf, "FinAidAward", "DateAccepted", "MonthDDYYYY", 20 );
		   szContextDate = sb_szContextDate.toString( );}
		   //:TraceLineS( "*** Date: ", szContextDate )
		   TraceLineS( "*** Date: ", szContextDate );
           Assert.assertEquals("Invalid MonthDDYYYY Date Format", ZeidonStringCompare( szContextDate, 1, 0, "October 28, 2010", 1, 0, 21 ), 0);

		   //:// Check Revenue Formatting.
		   //:SET CURSOR FIRST mFAProf.FinAidAward WHERE mFAProf.FinAidAward.Amount = 3930
		   RESULT = mFAProf.cursor( "FinAidAward" ).setFirst( "Amount", "3930" ).toInt();

		   // ZeidonStringCompare was comparing these as equal.  Need to make changes.
		   RESULT = ZeidonStringCompare( "3,930", 1, 0, "3,930.00", 1, 0, 201 );
           Assert.assertEquals("ZeidonStringCompare not working", RESULT, 1);

		   //:GetStringFromAttributeByContext( szRevenue, mFAProf, "FinAidAward", "Amount", "Revenue", 20 )
		   {StringBuilder sb_szRevenue;
		   if ( szRevenue == null )
		      sb_szRevenue = new StringBuilder( 32 );
		   else
		      sb_szRevenue = new StringBuilder( szRevenue );
		       GetStringFromAttributeByContext( sb_szRevenue, mFAProf, "FinAidAward", "Amount", "Revenue", 20 );
		   szRevenue = sb_szRevenue.toString( );}
		   //:TraceLineS( "*** Revenue: ", szRevenue )
		   TraceLineS( "*** Revenue: ", szRevenue );
		   //:IF szRevenue != "3,930.00"
           Assert.assertEquals("Invalid Revenue Format", ZeidonStringCompare( szRevenue, 1, 0, "3,930.00", 1, 0, 201 ), 0);

		   //:GetStringFromAttributeByContext( szRevenue, mFAProf, "FinAidAward", "Amount", "RevenueWithDollarSign", 20 )
		   {StringBuilder sb_szRevenue;
		   if ( szRevenue == null )
		      sb_szRevenue = new StringBuilder( 32 );
		   else
		      sb_szRevenue = new StringBuilder( szRevenue );
		       GetStringFromAttributeByContext( sb_szRevenue, mFAProf, "FinAidAward", "Amount", "RevenueWithDollarSign", 20 );
		   szRevenue = sb_szRevenue.toString( );}
		   //:TraceLineS( "*** Revenue: ", szRevenue )
		   TraceLineS( "*** Revenue: ", szRevenue );
		   //:IF szRevenue != "$3,930.00"
           Assert.assertEquals("Invalid RevenueWithDollarSign Format", ZeidonStringCompare( szRevenue, 1, 0, "$3,930.00", 1, 0, 201 ), 0);

		   //:GetStringFromAttributeByContext( szRevenue, mFAProf, "FinAidAward", "Amount", "RevenueText", 200 )
		   {StringBuilder sb_szRevenue;
		   if ( szRevenue == null )
		      sb_szRevenue = new StringBuilder( 32 );
		   else
		      sb_szRevenue = new StringBuilder( szRevenue );
		       GetStringFromAttributeByContext( sb_szRevenue, mFAProf, "FinAidAward", "Amount", "RevenueText", 200 );
		   szRevenue = sb_szRevenue.toString( );}
		   //:TraceLineS( "*** Revenue: ", szRevenue )
		   TraceLineS( "*** Revenue: ", szRevenue );
		   //:IF szRevenue != "Three Thousand Nine Hundred Thirty and 00/ 100 Dollars"
           Assert.assertEquals("Invalid RevenueText Format", ZeidonStringCompare( szRevenue, 1, 0, "Three Thousand Nine Hundred Thirty and 00 / 100 Dollars", 1, 0, 201 ), 0);

		   {StringBuilder sb_szRevenue = new StringBuilder();
	       GetStringFromAttributeByContext( sb_szRevenue, mFAProf, "FinAidAward", "Amount", "RevenueWithDollarSignCR", 200 );
	       szRevenue = sb_szRevenue.toString( );
           Assert.assertEquals("Invalid RevenueWithDollarSignCR Format for Positive", ZeidonStringCompare( szRevenue, 1, 0, "$3,930.00", 1, 0, 201 ), 0);
	       SetAttributeFromDecimal(mFAProf.getView(), "FinAidAward", "Amount", -3930);
	       GetStringFromAttributeByContext( sb_szRevenue, mFAProf, "FinAidAward", "Amount", "RevenueWithDollarSignCR", 200 );
	       szRevenue = sb_szRevenue.toString( );
           Assert.assertEquals("Invalid RevenueWithDollarSignCR Format for Negative", ZeidonStringCompare( szRevenue, 1, 0, "$3,930.00CR", 1, 0, 201 ), 0);
		   }
		   //:// Check Set Cursor on Subobject Value

		   //:SET CURSOR FIRST mFAProf.FinAidAwardDisbursement WITHIN mFAProf.FinAidProfile
		   //:           WHERE mFAProf.FinAidAwardDisbursement.Amount = 383.00
		   RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setFirst( "Amount", "383.00", "FinAidProfile" ).toInt();
		   //:IF mFAProf.FinAidAwardDisbursement.Amount != 383.00
           Assert.assertEquals("Invalid Set Cursor Subobject on 383.00", CompareAttributeToDecimal( mFAProf, "FinAidAwardDisbursement", "Amount", 383.00 ), 0);
//		   if ( CompareAttributeToDecimal( mFAProf, "FinAidAwardDisbursement", "Amount", (double) 383.00 ) != 0 )
//		   {
//		      //:IssueError( ViewToWindow,0,0, "Invalid Set Cursor Subobject on 383.00" )
//		      IssueError( ViewToWindow, 0, 0, "Invalid Set Cursor Subobject on 383.00" );
//		   }

		   //:END

		   //:SET CURSOR FIRST mFAProf.FinAidAward
		   RESULT = mFAProf.cursor( "FinAidAward" ).setFirst().toInt();
		   //:SET CURSOR FIRST mFAProf.FinAidAwardDisbursement WITHIN mFAProf.FinAidProfile
		   //:           WHERE mFAProf.FinAidAwardDisbursement.DisbursementDate = "20100824"
		   //:             AND mFAProf.FinAidAwardDisbursement.AppliedToAccount = "Y"
		   RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setFirst( "FinAidProfile" ).toInt();
		   if ( RESULT > zCURSOR_UNCHANGED )
		   {
		      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mFAProf, "FinAidAwardDisbursement", "DisbursementDate", "20100824" ) != 0 || CompareAttributeToString( mFAProf, "FinAidAwardDisbursement", "AppliedToAccount", "Y" ) != 0 ) )
		      {
		         RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setNextContinue().toInt();;
		      }

		   }

		   //:IF mFAProf.FinAidAwardDisbursement.ID != 88634
           Assert.assertEquals("Invalid Set Cursor Subobject on DisbursementDate and AppliedToAccount", CompareAttributeToInteger( mFAProf, "FinAidAwardDisbursement", "ID", 88634 ), 0);
//		   if ( CompareAttributeToInteger( mFAProf, "FinAidAwardDisbursement", "ID", 88634 ) != 0 )
//		   {
//		      //:IssueError( ViewToWindow,0,0, "Invalid Set Cursor Subobject on DisbursementDate and AppliedToAccount" )
//		      IssueError( ViewToWindow, 0, 0, "Invalid Set Cursor Subobject on DisbursementDate and AppliedToAccount" );
//		   }

		   //:END

		   //:SET CURSOR FIRST mFAProf.FinAidAward
		   RESULT = mFAProf.cursor( "FinAidAward" ).setFirst().toInt();
		   //:SET CURSOR FIRST mFAProf.FinAidAwardDisbursement WITHIN mFAProf.FinAidProfile
		   //:           WHERE mFAProf.FinAidAwardDisbursement.DisbursementDate = "20100824"
		   //:             AND mFAProf.FinAidAward.AwardType = "G"
		   RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setFirst( "FinAidProfile" ).toInt();
		   if ( RESULT > zCURSOR_UNCHANGED )
		   {
		      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mFAProf, "FinAidAwardDisbursement", "DisbursementDate", "20100824" ) != 0 || CompareAttributeToString( mFAProf, "FinAidAward", "AwardType", "G" ) != 0 ) )
		      {
		         RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setNextContinue().toInt();;
		      }

		   }

		   //:IF mFAProf.FinAidAwardDisbursement.ID != 88634
           Assert.assertEquals("Invalid Set Cursor Subobject on DisbursementDate and AwardType", CompareAttributeToInteger( mFAProf, "FinAidAwardDisbursement", "ID", 88634 ), 0);
//		   if ( CompareAttributeToInteger( mFAProf, "FinAidAwardDisbursement", "ID", 88634 ) != 0 )
//		   {
//		      //:IssueError( ViewToWindow,0,0, "Invalid Set Cursor Subobject on DisbursementDate and AwardType" )
//		      IssueError( ViewToWindow, 0, 0, "Invalid Set Cursor Subobject on DisbursementDate and AwardType" );
//		   }

		   //:SET CURSOR FIRST mFAProf.FinAidAward
		   RESULT = mFAProf.cursor( "FinAidAward" ).setFirst().toInt();
		   //:SET CURSOR FIRST mFAProf.FinAidAward WHERE mFAProf.FinAidAward.OriginalAmountOffered >= 6000
		   //:                                       AND mFAProf.FinAidAward.Amount <= 4000
		   RESULT = mFAProf.cursor( "FinAidAward" ).setFirst().toInt();
		   if ( RESULT > zCURSOR_UNCHANGED )
		   {
		      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mFAProf, "FinAidAward", "OriginalAmountOffered", 6000 ) < 0 || CompareAttributeToInteger( mFAProf, "FinAidAward", "Amount", 4000 ) > 0 ) )
		      {
		         RESULT = mFAProf.cursor( "FinAidAward" ).setNext().toInt();
		      }

		   }

		   //:IF mFAProf.FinAidAward.ID != 44963
           Assert.assertEquals("Invalid Set Cursor on >= and <=", CompareAttributeToInteger( mFAProf, "FinAidAward", "ID", 44963 ), 0);
//		   if ( CompareAttributeToInteger( mFAProf, "FinAidAward", "ID", 44963 ) != 0 )
//		   {
//		      //:IssueError( ViewToWindow,0,0, "Invalid Set Cursor on >= and <=" )
//		      IssueError( ViewToWindow, 0, 0, "Invalid Set Cursor on >= and <=" );
//		   }

		   //:END

		   //:SET CURSOR LAST mFAProf.FinAidAward
		   RESULT = mFAProf.cursor( "FinAidAward" ).setLast().toInt();;
		   //:SET CURSOR PREVIOUS mFAProf.FinAidAward WHERE mFAProf.FinAidAward.Amount >= 6500
		   RESULT = mFAProf.cursor( "FinAidAward" ).setPrev( ).toInt();
		   if ( RESULT > zCURSOR_UNCHANGED )
		   {
		      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mFAProf, "FinAidAward", "Amount", 6500 ) < 0 ) )
		      {
		         RESULT = mFAProf.cursor( "FinAidAward" ).setPrev( ).toInt();
		      }

		   }

		   //:IF mFAProf.FinAidAward.ID != 44965
           Assert.assertEquals("Invalid Set Cursor on Previous <=", CompareAttributeToInteger( mFAProf, "FinAidAward", "ID", 44965 ), 0);
//		   if ( CompareAttributeToInteger( mFAProf, "FinAidAward", "ID", 44965 ) != 0 )
//		   {
//		      //:IssueError( ViewToWindow,0,0, "Invalid Set Cursor on Previous <=" )
//		      IssueError( ViewToWindow, 0, 0, "Invalid Set Cursor on Previous <=" );
//		   }

		   //:END

		   //:// Check Operation AddDaysToDate.
		   //:AddDaysToDate( mFAProf, "FinAidProfile", "ProfileYearBegin", 32 )
		   {
		    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mFAProf );
		    m_ZGLOBAL1_Operation.AddDaysToDate( mFAProf, "FinAidProfile", "ProfileYearBegin", 32 );
		    m_ZGLOBAL1_Operation = null;  // permit gc
		   }
		   //:szContextDate8 = mFAProf.FinAidProfile.ProfileYearBegin
		   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
		   StringBuilder sb_szContextDate8;
		   if ( szContextDate8 == null )
		      sb_szContextDate8 = new StringBuilder( 32 );
		   else
		      sb_szContextDate8 = new StringBuilder( szContextDate8 );
		       GetVariableFromAttribute( sb_szContextDate8, mi_lTempInteger_0, 'S', 9, mFAProf, "FinAidProfile", "ProfileYearBegin", "", 0 );
		   lTempInteger_0 = mi_lTempInteger_0.intValue( );
		   szContextDate8 = sb_szContextDate8.toString( );}
		   //:TraceLineS( "*** AddDaysToDate Date: ", szContextDate8 )
		   TraceLineS( "*** AddDaysToDate Date: ", szContextDate8 );
		   //:IF szContextDate8 != "20100802"
           Assert.assertEquals("Invalid AddDaysToDate Value", ZeidonStringCompare( szContextDate8, 1, 0, "20100802", 1, 0, 9 ), 0);
//		   if ( ZeidonStringCompare( szContextDate8, 1, 0, "20100802", 1, 0, 9 ) != 0 )
//		   {
//		      //:IssueError( ViewToWindow,0,0, "Invalid AddDaysToDate Value" )
//		      IssueError( ViewToWindow, 0, 0, "Invalid AddDaysToDate Value" );
//		   }

		   //:END
		   //:mFAProf.FinAidProfile.ProfileYearBegin = "20100701"    // Set the value back in case it is used elsewhere.
		   SetAttributeFromString( mFAProf, "FinAidProfile", "ProfileYearBegin", "20100701" );


		   //:// Check Operation GetDaysInMonth
		   //:DaysInMonth = GetDaysInMonth( "20100701" )
		   {
		    ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( ViewToWindow );
		    DaysInMonth = m_ZGLOBAL2_Operation.GetDaysInMonth( "20100701" );
		    m_ZGLOBAL2_Operation = null;  // permit gc
		   }
		   //:TraceLineI( "*** DaysInMonth: ", DaysInMonth )
		   TraceLineI( "*** DaysInMonth: ", DaysInMonth );
		   //:IF DaysInMonth != 31
           Assert.assertEquals("Invalid DaysInMonth Value", DaysInMonth, 31);
//		   if ( DaysInMonth != 31 )
//		   {
//		      //:IssueError( ViewToWindosheen girlw,0,0, "Invalid DaysInMonth Value" )
//		      IssueError( ViewToWindow, 0, 0, "Invalid DaysInMonth Value" );
//		   }

		   //:END


		   //:// Check Relink Operation
		   //:SET CURSOR FIRST mFAProf.FinAidAward
		   RESULT = mFAProf.cursor( "FinAidAward" ).setFirst().toInt();
		   //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod WHERE mFAProf.PerProfileFinAidAwardPeriod.ID = mFAProf.FinAidAwardPeriod.ID
		   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
		       GetIntegerFromAttribute( mi_lTempInteger_1, mFAProf, "FinAidAwardPeriod", "ID" );
		   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
		   RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst( "ID", lTempInteger_1 ).toInt();
		   //:RelinkInstanceToInstance( mFAProf, "FinAidAwardPeriod", mFAProf, "PerProfileFinAidAwardPeriod" )
		   RelinkInstanceToInstance( mFAProf, "FinAidAwardPeriod", mFAProf, "PerProfileFinAidAwardPeriod" );
		   //:mFAProf.PerProfileFinAidAwardPeriod.PeriodDesignator = "201020113"
		   SetAttributeFromString( mFAProf, "PerProfileFinAidAwardPeriod", "PeriodDesignator", "201020113" );
		   //:IF mFAProf.FinAidAwardPeriod.PeriodDesignator != "201020113"
		   if ( CompareAttributeToString( mFAProf, "FinAidAwardPeriod", "PeriodDesignator", "201020113" ) != 0 )
		   {
		      //:IssueError( ViewToWindow,0,0, "Invalid Relink on PerProfileFinAidAwardPeriod" )
		      IssueError( ViewToWindow, 0, 0, "Invalid Relink on PerProfileFinAidAwardPeriod" );
		   }

		   //:END
		   //:mFAProf.PerProfileFinAidAwardPeriod.PeriodDesignator = "201020111"   // Set the value back in case it is used elsewhere.
		   SetAttributeFromString( mFAProf, "PerProfileFinAidAwardPeriod", "PeriodDesignator", "201020111" );


		   //:// Check Trimming blanks at end of string.
		   //:szTestString = "Bill Jones   "
		    {StringBuilder sb_szTestString;
		   if ( szTestString == null )
		      sb_szTestString = new StringBuilder( 32 );
		   else
		      sb_szTestString = new StringBuilder( szTestString );
		      ZeidonStringCopy( sb_szTestString, 1, 0, "Bill Jones   ", 1, 0, 21 );
		   szTestString = sb_szTestString.toString( );}
		   //:zTrim( szTestString )
		   {
		    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( ViewToWindow );
		    {StringBuilder sb_szTestString;
		   if ( szTestString == null )
		      sb_szTestString = new StringBuilder( 32 );
		   else
		      sb_szTestString = new StringBuilder( szTestString );
		       m_ZGLOBAL1_Operation.zTrim( sb_szTestString );
		   szTestString = sb_szTestString.toString( );}
		    m_ZGLOBAL1_Operation = null;  // permit gc
		   }
		   //:IF szTestString != "Bill Jones"
           Assert.assertEquals("Invalid Trimming of blank characters", ZeidonStringCompare( szTestString, 1, 0, "Bill Jones", 1, 0, 21 ), 0);
//		   if ( ZeidonStringCompare( szTestString, 1, 0, "Bill Jones", 1, 0, 21 ) != 0 )
//		   {
//		      //:IssueError( ViewToWindow,0,0, "Invalid Trimming of blank characters" )
//		      IssueError( ViewToWindow, 0, 0, "Invalid Trimming of blank characters" );
//		   }

		   //:END


		   //:// Check FindStringInAttribute
		   //:SET CURSOR FIRST mFAProf.FinAidAward
		   RESULT = mFAProf.cursor( "FinAidAward" ).setFirst().toInt();
		   //:nRC = FindStringInAttribute( " From MyENC", mFAProf, "DisplaySwappedEntries", "SwappedSourceDescription" )
		   {
		    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mFAProf );
		    nRC = m_ZGLOBAL1_Operation.FindStringInAttribute( " From MyENC", mFAProf, "DisplaySwappedEntries", "SwappedSourceDescription" );
		    m_ZGLOBAL1_Operation = null;  // permit gc
		   }
		   //:IF nRC < 0
	       Assert.assertTrue("Invalid FindStringInAttribute Value", nRC >= 0);
//		   if ( nRC < 0 )
//		   {
//		      //:IssueError( ViewToWindow,0,0, "Invalid FindStringInAttribute Value" )
//		      IssueError( ViewToWindow, 0, 0, "Invalid FindStringInAttribute Value" );
//		   }
	         DropObjectInstance( mFAProf );

		   //:END
		   return( 0 );

		}
   		public int
   		testInclude2( View     ViewToWindow )
   		{
   			   zVIEW    mFAProf = new zVIEW( );
   			   int RESULT;
   			   int lTempInteger_0;

   			   ActivateOI_FromFile( mFAProf, "mFAProf", ViewToWindow, zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mFAProfT.por", zSINGLE );
   			   SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
   			   RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod", mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
   			   RESULT = CheckExistenceOfEntity( mFAProf, "PerProSATransactionCode" );
   			   Assert.assertEquals("PerProSATransactionCode was not correctly included.", 0, RESULT);
   			   DropView( mFAProf );
   			   return RESULT;
   		}
  		public int
  		testDomainCompareAttrs( View     ViewToWindow )
   		{
  		   zVIEW    wXferO = new zVIEW( );
 		   zVIEW    mPerson = new zVIEW( );
 		   zVIEW    vTempViewVar_0 = new zVIEW( );
   		   int      RESULT = 0;
   		   String   strText = null;


 		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
 		    //:CREATE ENTITY wXferO.Root
 		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
 		    //:NAME VIEW wXferO "wXferO"
 		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );

 		   // Activating mPerson as ROOTONLY, does eliminate some child entities like FinAidProfile, but there are several entities
 		   // that were not eliminated.
 		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
 		   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
 		   DropView( vTempViewVar_0 );
 		   mPerson.cursor("Person").setAttribute("Gender", "F", "");
	       strText = mPerson.cursor( "Person" ).getStringFromAttribute( "HomePhone", "" );

  		   //SetAttributeFromString( wXferO, "Root", "ActionFlag", "Yes" );
  		   SetAttributeFromString( wXferO, "Root", "ActionFlag", "Y" );

 	       if ( CompareAttributeToString( wXferO, "Root", "WorkDate", "20130613" ) == 0 )
 	    	   RESULT = 0;
 	       else
 	    	   RESULT = -1;

	       if ( CompareAttributeToString( wXferO, "Root", "ActionFlag", "Yes" ) == 0 )
 	    	   RESULT = 0;
 	       else
 	    	   RESULT = -1;

	       if ( CompareAttributeToString( wXferO, "Root", "ActionFlag", "Y" ) == 0 )
 	    	   RESULT = 0;
 	       else
 	    	   RESULT = -1;

	       if ( CompareAttributeToString( wXferO, "Root", "ActionFlag", "N" ) == 0 )
 	    	   RESULT = 0;
 	       else
 	    	   RESULT = -1;

	       SetAttributeFromString( wXferO, "Root", "WorkDate", "20130610" );
	       SetAttributeFromString( wXferO, "Root", "WorkDate2", "20130613" );

		   //Assert.assertEquals("No match on Person Name.", CompareAttributeToAttribute( mFAProfN, "Person", "ID", mFAProfO, "Person", "ID" ), 0 );
	       if ( CompareAttributeToAttribute( wXferO, "Root", "WorkDate", wXferO, "Root", "WorkDate2" ) == 0 )
 	    	   RESULT = 0;
 	       else
 	    	   RESULT = -1;

 	       if ( CompareAttributeToString( wXferO, "Root", "WorkDate2", "20130613" ) == 0 )
 	    	   RESULT = 0;
 	       else
 	    	   RESULT = -1;
 	       if ( CompareAttributeToString( wXferO, "Root", "WorkDate2", "String1" ) == 0 )
 	    	   RESULT = 0;
 	       else
 	    	   RESULT = -1;

	       SetAttributeFromString( wXferO, "Root", "WorkDate2", "20130610" );
	       if ( CompareAttributeToAttribute( wXferO, "Root", "WorkDate", wXferO, "Root", "WorkDate2" ) == 0 )
 	    	   RESULT = 0;
 	       else
 	    	   RESULT = -1;

	       // We get an error here... which is correct.
	       try
	       {
	          //SetAttributeFromString( wXferO, "Root", "WorkDate2", "String" );
		      Assert.assertEquals("This should not work, setting date to string.", SetAttributeFromString( wXferO, "Root", "WorkDate2", "String" ), 0 );
	       }
	       catch( Exception e)
	       {
              //The above SetAttributeFromString should not work. So the fact that we get here is correct. Don't give an error.
	       }

   			return RESULT;
   		}

   		public int
   		testInclude1to1WithCommit( View     ViewToWindow )
   		{
   		   zVIEW    mStudent = new zVIEW( );
   		   int      RESULT = 0;
   		   zVIEW    mAdmDiv = new zVIEW( );
   		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );

		   // Currently, we are getting an error when doing an exclude then include, then
		   // commit on a 0 to m or 1 to m entity. I have tried a m to m and this seems to
		   // work fine.
		   // This example loads mStudent then excludes AdministrativeDivision, then includes
		   // it back in and tries to commit. It is trying to set the foreign key in "STUDENT" to
		   // null.

		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    //:CREATE ENTITY wXferO.Root
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    //:NAME VIEW wXferO "wXferO"
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			//:OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" )
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );
			//:SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.CurrentTermFlag = "Y"
			RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst( "CurrentTermFlag", "Y" ).toInt();

			//:ACTIVATE mStudent WHERE mStudent.Student.ID = 16404 //hardeem
		    o_fnLocalBuildmStudent( ViewToWindow, vTempViewVar_0, 16406 );
		    RESULT = ActivateObjectInstance( mStudent, "mStudent", ViewToWindow, vTempViewVar_0, zSINGLE );
	   		DropView( vTempViewVar_0 );
		    // ACTIVATE mAdmDiv
		    o_fnLocalBuildmAdmDiv( ViewToWindow, vTempViewVar_0, 1 );
		    RESULT = ActivateObjectInstance( mAdmDiv, "mAdmDiv", ViewToWindow, vTempViewVar_0, zSINGLE );
	   		DropView( vTempViewVar_0 );

   		   TraceLineS(" *** RIGHT before Exclude AdminDiv ", "");
   		   RESULT = ExcludeEntity( mStudent, "AdministrativeDivision", zREPOS_NONE );
   		   //RESULT = ExcludeEntity( mStudent, "AdministrativeDivision", zREPOS_AFTER );
   		   TraceLineS(" *** AFTER Exclude  before Include AdminDiv ", "");
   		  RESULT = IncludeSubobjectFromSubobject( mStudent, "AdministrativeDivision", mAdmDiv, "AdministrativeDivision", zPOS_AFTER );
   		  TraceLineS(" *** AFTER include AdminDiv ", "");

   		   //:COMMIT mStudent
   		   RESULT = CommitObjectInstance( mStudent );
	   	   DropView( vTempViewVar_0 );
	   	   DropView( mAdmDiv );
	   	   DropView( mStudent );
	   	   DropView( lTermLST );
	   	   DropView( wXferO );
   		   return( 0 );
   		}

   		public int
   		testInclude0to1WithCommit( View     ViewToWindow )
   		{
   		   zVIEW    mStudent = new zVIEW( );
   		   zVIEW    lCohort = new zVIEW( );
  		   int      RESULT = 0;
   		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );

		   // Currently, we are getting an error when doing an exclude then include, then
		   // commit on a 0 to m or 1 to m entity. I have tried a m to m and this seems to
		   // work fine.
		   // This example loads mStudent then excludes AdministrativeDivision, then includes
		   // it back in and tries to commit. It is trying to set the foreign key in "STUDENT" to
		   // null.

		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    //:CREATE ENTITY wXferO.Root
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    //:NAME VIEW wXferO "wXferO"
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			//:OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" )
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );
			//:SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.CurrentTermFlag = "Y"
			RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst( "CurrentTermFlag", "Y" ).toInt();

			//:ACTIVATE mStudent WHERE mStudent.Student.ID = 14136 //johnsonl
		    //o_fnLocalBuildmStudent( ViewToWindow, vTempViewVar_0, 16406 );
		    o_fnLocalBuildmStudent( ViewToWindow, vTempViewVar_0, 14229 );
		    RESULT = ActivateObjectInstance( mStudent, "mStudent", ViewToWindow, vTempViewVar_0, zSINGLE );
	   		DropView( vTempViewVar_0 );
		    SetNameForView( mStudent, "mStudent", null, zLEVEL_TASK );

			   RESULT = ActivateObjectInstance( lCohort, "lCohort", ViewToWindow, 0, zMULTIPLE );
			    SetNameForView( lCohort, "lCohort", null, zLEVEL_TASK );

			   //:   // Request is to create Cohort.
			   //:   IF mStudent.Cohort EXISTS
			   RESULT = CheckExistenceOfEntity( mStudent, "Cohort" );
			   if ( RESULT == 0 )
			   {
			      //:   EXCLUDE mStudent.Cohort
			      RESULT = ExcludeEntity( mStudent, "Cohort", zREPOS_AFTER );
			   }

			   //:   END
			   //:   SET CURSOR FIRST lCohort.Cohort WHERE lCohort.Cohort.ID = 809
			   RESULT = SetCursorFirstEntityByInteger( lCohort, "Cohort", "ID", 809, "" );
			   //:   INCLUDE mStudent.Cohort FROM lCohort.Cohort
			   RESULT = IncludeSubobjectFromSubobject( mStudent, "Cohort", lCohort, "Cohort", zPOS_AFTER );
			   RESULT = CommitObjectInstance( mStudent );

     	       RESULT = ExcludeEntity( mStudent, "Cohort", zREPOS_AFTER );
			   RESULT = IncludeSubobjectFromSubobject( mStudent, "Cohort", lCohort, "Cohort", zPOS_AFTER );
			   //:
			   //:   COMMIT mStudent
			   RESULT = CommitObjectInstance( mStudent );
			   RESULT = CommitObjectInstance( mStudent );
	   	   DropView( vTempViewVar_0 );
	   	   DropView( lCohort );
	   	   DropView( mStudent );
	   	   DropView( lTermLST );
	   	   DropView( wXferO );
   		   return( 0 );
   		}

   		public int
   		testInclude0to1WithCommit2( View     ViewToWindow )
   		{
   		   zVIEW    mStudent = new zVIEW( );
   		   zVIEW    lCohort = new zVIEW( );
  		   int      RESULT = 0;
   		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );

		   // I exclude mStudent.Cohort (which is id 26) and include Cohort id=809.
		   // When I look at the object browser, you see 2 entities for mStudent.Cohort although
		   // I can not position on the excluded Cohort. But when we save to the database,
		   // the original cohort (id=26) gets saved not the new included cohort (id=809).

		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    //:CREATE ENTITY wXferO.Root
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    //:NAME VIEW wXferO "wXferO"
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			//:OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" )
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );
			//:SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.CurrentTermFlag = "Y"
			RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst( "CurrentTermFlag", "Y" ).toInt();

			//:ACTIVATE mStudent WHERE mStudent.Student.ID = 14136 //johnsonl
		    //o_fnLocalBuildmStudent( ViewToWindow, vTempViewVar_0, 16406 );
		    o_fnLocalBuildmStudent( ViewToWindow, vTempViewVar_0, 14229 );
		    RESULT = ActivateObjectInstance( mStudent, "mStudent", ViewToWindow, vTempViewVar_0, zSINGLE );
	   		DropView( vTempViewVar_0 );
		    SetNameForView( mStudent, "mStudent", null, zLEVEL_TASK );

			RESULT = ActivateObjectInstance( lCohort, "lCohort", ViewToWindow, 0, zMULTIPLE );
			SetNameForView( lCohort, "lCohort", null, zLEVEL_TASK );

			   //:   // Request is to create Cohort.
			   //:   IF mStudent.Cohort EXISTS
			   RESULT = CheckExistenceOfEntity( mStudent, "Cohort" );
			   if ( RESULT == 0 )
			   {
			      //:   EXCLUDE mStudent.Cohort
			      RESULT = ExcludeEntity( mStudent, "Cohort", zREPOS_AFTER );
			   }

			   //:   END
			   //:   SET CURSOR FIRST lCohort.Cohort WHERE lCohort.Cohort.ID = 809
			   RESULT = SetCursorFirstEntityByInteger( lCohort, "Cohort", "ID", 809, "" );
			   //:   INCLUDE mStudent.Cohort FROM lCohort.Cohort
			   RESULT = IncludeSubobjectFromSubobject( mStudent, "Cohort", lCohort, "Cohort", zPOS_AFTER );
			   RESULT = CommitObjectInstance( mStudent );
		   	   DropView( mStudent );

			   o_fnLocalBuildmStudent( ViewToWindow, vTempViewVar_0, 14229 );
			   RESULT = ActivateObjectInstance( mStudent, "mStudent", ViewToWindow, vTempViewVar_0, zSINGLE );
		   	   DropView( vTempViewVar_0 );
			   SetNameForView( mStudent, "mStudent", null, zLEVEL_TASK );
		   	   //kkk
		   	   int lTempInteger_0 = 0;
		   	   MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
	   	       GetIntegerFromAttribute( mi_lTempInteger_0, mStudent, "Cohort", "ID" );
	   	       lTempInteger_0 = mi_lTempInteger_0.intValue( );

	   	       if (lTempInteger_0 == 26 )
					//:IssueError( ViewToWindow,0,0, "Person was not correctly included." )
					Assert.assertEquals("Cohort include/save was not correct!", lTempInteger_0, 809);
	   	    	 //TraceLineS(" ***ERROR ", "");



     	       RESULT = ExcludeEntity( mStudent, "Cohort", zREPOS_AFTER );
			   RESULT = IncludeSubobjectFromSubobject( mStudent, "Cohort", lCohort, "Cohort", zPOS_AFTER );
			   //:
			   //:   COMMIT mStudent
			   RESULT = CommitObjectInstance( mStudent );
	   	   DropView( vTempViewVar_0 );
	   	   DropView( lCohort );
	   	   DropView( mStudent );
	   	   DropView( lTermLST );
	   	   DropView( wXferO );
   		   return( 0 );
   		}

   		public int
   		testIncludeMtoMWithCommit( View     ViewToWindow )
   		{
   		   zVIEW    mStudent = new zVIEW( );
   		   zVIEW    lCategry = new zVIEW( );
   		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );
		   int      lTempInteger_0 = 0;
   		   int      RESULT = 0;

		   // This example loads mStudent then includes/excludes CATEGORY from lCategry. Then commits.

		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    //:CREATE ENTITY wXferO.Root
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    //:NAME VIEW wXferO "wXferO"
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			//:OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" )
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );
			//:SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.CurrentTermFlag = "Y"
			RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst( "CurrentTermFlag", "Y" ).toInt();

			//:ACTIVATE mStudent WHERE mStudent.Student.ID = 16404 //hardeem
		    o_fnLocalBuildmStudent( ViewToWindow, vTempViewVar_0, 16406 );
		    RESULT = ActivateObjectInstance( mStudent, "mStudent", ViewToWindow, vTempViewVar_0, zSINGLE );
	   		DropView( vTempViewVar_0 );
		    // ACTIVATE mAdmDiv
	   	   o_fnLocalBuildlCategory( ViewToWindow, vTempViewVar_0 );
	   	   RESULT = ActivateObjectInstance( lCategry, "lCategry", ViewToWindow, vTempViewVar_0, zMULTIPLE );
	   	   DropView( vTempViewVar_0 );
	   	   //:NAME VIEW lCategry "lCategry"
	   	   SetNameForView( lCategry, "lCategry", null, zLEVEL_TASK );
	   	   //:
	   	   //:SET CURSOR FIRST mStudent.Category where mStudent.Category.ID = lCategry.Category.ID
	   	   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
	   	       GetIntegerFromAttribute( mi_lTempInteger_0, lCategry, "Category", "ID" );
	   	   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
	   	   RESULT = SetCursorFirstEntityByInteger( mStudent, "Category", "ID", lTempInteger_0, "" );
	   	   //:IF RESULT >= zCURSOR_SET
	   	   if ( RESULT >= zCURSOR_SET )
	   	   {
	   	      //:EXCLUDE mStudent.Category
	   	      RESULT = ExcludeEntity( mStudent, "Category", zREPOS_AFTER );
	   	   }

	   	   //:END
	   	   //:INCLUDE mStudent.Category FROM lCategry.Category
	   	   //RESULT = IncludeSubobjectFromSubobject( mStudent, "Category", lCategry, "Category", zPOS_AFTER );

   		   //:COMMIT mStudent
   		   RESULT = CommitObjectInstance( mStudent );
   		   TraceLineS(" *** AFTER commit mStudent ", "");

   		   //RESULT = ExcludeEntity( mStudent, "Category", zREPOS_AFTER );
   		   RESULT = IncludeSubobjectFromSubobject( mStudent, "Category", lCategry, "Category", zPOS_AFTER );

   		   //:COMMIT mStudent
   		   RESULT = CommitObjectInstance( mStudent );
	   	   DropView( mStudent );
	   	   DropView( lCategry );
	   	   DropView( vTempViewVar_0 );
	   	   DropView( lTermLST );
	   	   DropView( wXferO );
  		   return( 0 );
   		}

   		public int
   		testIncludeMtoMWithCommitJeff( View     ViewToWindow )
   		{
   		   zVIEW    mStudent = new zVIEW( );
   		   zVIEW    lCategry = new zVIEW( );
   		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );
		   int      lTempInteger_0 = 0;
   		   int      RESULT = 0;

		   // This example loads mStudent then includes/excludes CATEGORY from lCategry. Then commits.

		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    //:CREATE ENTITY wXferO.Root
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    //:NAME VIEW wXferO "wXferO"
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			//:NAME VIEW lTermLST "lTermLST"
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			//:OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" )
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );
			//:SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.CurrentTermFlag = "Y"
			RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst( "CurrentTermFlag", "Y" ).toInt();

			//:ACTIVATE mStudent WHERE mStudent.Student.ID = 16404 //hardeem
		    o_fnLocalBuildmStudent( ViewToWindow, vTempViewVar_0, 16406 );
		    RESULT = ActivateObjectInstance( mStudent, "mStudent", ViewToWindow, vTempViewVar_0, zSINGLE );
	   		DropView( vTempViewVar_0 );
		    // ACTIVATE mAdmDiv
	   	   o_fnLocalBuildlCategory( ViewToWindow, vTempViewVar_0 );
	   	   RESULT = ActivateObjectInstance( lCategry, "lCategry", ViewToWindow, vTempViewVar_0, zMULTIPLE );
	   	   DropView( vTempViewVar_0 );
	   	   //:NAME VIEW lCategry "lCategry"
	   	   SetNameForView( lCategry, "lCategry", null, zLEVEL_TASK );
	   	   //:
	   	   //:SET CURSOR FIRST mStudent.Category where mStudent.Category.ID = lCategry.Category.ID
	   	   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
	   	       GetIntegerFromAttribute( mi_lTempInteger_0, lCategry, "Category", "ID" );
	   	   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
	   	   RESULT = SetCursorFirstEntityByInteger( mStudent, "Category", "ID", lTempInteger_0, "" );
	   	   //:IF RESULT >= zCURSOR_SET
	   	   if ( RESULT >= zCURSOR_SET )
	   	   {
	   	      //:EXCLUDE mStudent.Category
	   	      RESULT = ExcludeEntity( mStudent, "Category", zREPOS_AFTER );
	   	   }

	   	   //:END
	   	   //:INCLUDE mStudent.Category FROM lCategry.Category
	   	   //RESULT = IncludeSubobjectFromSubobject( mStudent, "Category", lCategry, "Category", zPOS_AFTER );

   		   //:COMMIT mStudent
   		   //RESULT = CommitObjectInstance( mStudent );
   		   TraceLineS(" *** AFTER commit mStudent ", "");

   		   //RESULT = ExcludeEntity( mStudent, "Category", zREPOS_AFTER );
   		   RESULT = IncludeSubobjectFromSubobject( mStudent, "Category", lCategry, "Category", zPOS_AFTER );

   		   //:COMMIT mStudent
   		   RESULT = CommitObjectInstance( mStudent );
   		   RESULT = CommitObjectInstance( mStudent );
	   	   DropView( mStudent );
	   	   DropView( lCategry );
	   	   DropView( vTempViewVar_0 );
	   	   DropView( lTermLST );
	   	   DropView( wXferO );
  		   return( 0 );
   		}

		//:   VIEW mFAProfO BASED ON LOD mFAProf
		public int
		testInclude( View     ViewToWindow )
		{
			zVIEW    mFAProfO = new zVIEW( );
			//:VIEW mFAProfN BASED ON LOD mFAProf
			zVIEW    mFAProfN = new zVIEW( );
			//:INTEGER Count
			int      Count = 0;
			int      RESULT = 0;
			int      lTempInteger_0 = 0;
			String   szTempString_0 = null;
			int      lTempInteger_1 = 0;
			int      lTempInteger_2 = 0;
			double  dTempDecimal_0 = 0.0;
			int      lTempInteger_3 = 0;

	        Assert.assertTrue("Wrong attribute value.", true);

			//:// Zeidon Include Spawn Test

			//:// Activate an existing mFAProf instance and duplicate it as a new mFAProf instance, checking
			//:// to make sure that the FinAidAward subobject spawns correctly as a PerProfileFinAidAwardPeriod subobject.
			//:// Then commit the created mFAProf object and reactivate it, checking the structure just read with the one written.
			//:// Finally, delete the created mFAProf object so that the test can be rerun.

			//://ACTIVATE mFAProfO WHERE mFAProfO.FinAidProfile.ID = 24297

			//:ActivateOI_FromFile( mFAProfO, "mFAProf", ViewToWindow, "c:\temp\mFAProf.por", zSINGLE )
			ActivateOI_FromFile( mFAProfO, "mFAProf", ViewToWindow, zeidonSystem.getObjectEngine().getHomeDirectory() + "/ZENCAs/mFAProf.por", zSINGLE );

			//:NAME VIEW mFAProfO "mFAProfO"
			SetNameForView( mFAProfO, "mFAProfO", null, zLEVEL_TASK );
			//:SET CURSOR FIRST mFAProfO.FinAidAward WHERE mFAProfO.FinAidAward.ID = 50224
			RESULT = mFAProfO.cursor( "FinAidAward" ).setFirst( "ID", "50224" ).toInt();

			//:ACTIVATE mFAProfN EMPTY
			RESULT = ActivateEmptyObjectInstance( mFAProfN, "mFAProf", ViewToWindow, zSINGLE );
			//:NAME VIEW mFAProfN "mFAProfN"
			SetNameForView( mFAProfN, "mFAProfN", null, zLEVEL_TASK );
			//:CREATE ENTITY mFAProfN.FinAidProfile
			RESULT = CreateEntity( mFAProfN, "FinAidProfile", zPOS_AFTER );
			//:SetMatchingAttributesByName( mFAProfN, "FinAidProfile", mFAProfO, "FinAidProfile", zSET_NULL )
			SetMatchingAttributesByName( mFAProfN, "FinAidProfile", mFAProfO, "FinAidProfile", zSET_NULL );

			//:INCLUDE mFAProfN.Person FROM mFAProfO.Person
			RESULT = IncludeSubobjectFromSubobject( mFAProfN, "Person", mFAProfO, "Person", zPOS_AFTER );

			//:// Check that the Person Name is set correctly and that there are 46 Enrolled entries.
			//:IF mFAProfN.Person DOES NOT EXIST
			lTempInteger_0 = CheckExistenceOfEntity( mFAProfN, "Person" );
			if ( lTempInteger_0 != 0 )
			{
				//:IssueError( ViewToWindow,0,0, "Person was not correctly included." )
				Assert.assertEquals("Person was not correctly included.", CheckExistenceOfEntity( mFAProfN, "Person" ), 0);
				//:ELSE
			}
			else
			{
				//:IF mFAProfN.Person.dFullNameLFM != mFAProfO.Person.dFullNameLFM
				Assert.assertEquals("No match on Person Name.", CompareAttributeToAttribute( mFAProfN, "Person", "ID", mFAProfO, "Person", "ID" ), 0 );

				Count = 0;
				//:FOR EACH mFAProfN.Enrolled
				RESULT = mFAProfN.cursor( "Enrolled" ).setFirst().toInt();
				while ( RESULT > zCURSOR_UNCHANGED )
				{
					//:Count = Count + 1
					Count = Count + 1;
					RESULT = mFAProfN.cursor( "Enrolled" ).setNext().toInt();
				}

			    Assert.assertEquals("Count of Enrolled entries is not 63.", Count, 63 );

			}

			//:END

			//:// Duplicate Award Periods.
			//:FOR EACH mFAProfO.PerProfileFinAidAwardPeriod
			RESULT = mFAProfO.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
			while ( RESULT > zCURSOR_UNCHANGED )
			{
				//:CREATE ENTITY mFAProfN.PerProfileFinAidAwardPeriod
				RESULT = CreateEntity( mFAProfN, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
				//:SetMatchingAttributesByName( mFAProfN, "PerProfileFinAidAwardPeriod", mFAProfO, "PerProfileFinAidAwardPeriod", zSET_NULL )
				SetMatchingAttributesByName( mFAProfN, "PerProfileFinAidAwardPeriod", mFAProfO, "PerProfileFinAidAwardPeriod", zSET_NULL );
				//:INCLUDE mFAProfN.CollegeTerm FROM mFAProfO.CollegeTerm
				RESULT = IncludeSubobjectFromSubobject( mFAProfN, "CollegeTerm", mFAProfO, "CollegeTerm", zPOS_AFTER );
				RESULT = mFAProfO.cursor( "PerProfileFinAidAwardPeriod" ).setNext().toInt();
			}

			//:END

			//:// Create Fin Aid Award Subobject.
			//:CREATE ENTITY mFAProfN.FinAidAward
			RESULT = CreateEntity( mFAProfN, "FinAidAward", zPOS_AFTER );
			//:SetMatchingAttributesByName( mFAProfN, "FinAidAward", mFAProfO, "FinAidAward", zSET_NULL )
			SetMatchingAttributesByName( mFAProfN, "FinAidAward", mFAProfO, "FinAidAward", zSET_NULL );
			//:INCLUDE mFAProfN.FinAidSource FROM mFAProfO.FinAidSource
			RESULT = IncludeSubobjectFromSubobject( mFAProfN, "FinAidSource", mFAProfO, "FinAidSource", zPOS_AFTER );

			//:FOR EACH mFAProfO.FinAidAwardDisbursement
			RESULT = mFAProfO.cursor( "FinAidAwardDisbursement" ).setFirst().toInt();
			while ( RESULT > zCURSOR_UNCHANGED )
			{
				//:CREATE ENTITY mFAProfN.FinAidAwardDisbursement
				RESULT = CreateEntity( mFAProfN, "FinAidAwardDisbursement", zPOS_AFTER );
				//:SetMatchingAttributesByName( mFAProfN, "FinAidAwardDisbursement", mFAProfO, "FinAidAwardDisbursement", zSET_NULL )
				SetMatchingAttributesByName( mFAProfN, "FinAidAwardDisbursement", mFAProfO, "FinAidAwardDisbursement", zSET_NULL );
				//:SET CURSOR FIRST mFAProfN.PerProfileFinAidAwardPeriod
				//:           WHERE mFAProfN.PerProfileFinAidAwardPeriod.PeriodDesignator = mFAProfO.FinAidAwardPeriod.PeriodDesignator
				{StringBuilder sb_szTempString_0;
				if ( szTempString_0 == null )
					sb_szTempString_0 = new StringBuilder( 32 );
				else
					sb_szTempString_0 = new StringBuilder( szTempString_0 );
				GetStringFromAttribute( sb_szTempString_0, mFAProfO, "FinAidAwardPeriod", "PeriodDesignator" );
				szTempString_0 = sb_szTempString_0.toString( );}
				RESULT = mFAProfN.cursor( "PerProfileFinAidAwardPeriod" ).setFirst( "PeriodDesignator", szTempString_0 ).toInt();
				//:INCLUDE mFAProfN.FinAidAwardPeriod FROM mFAProfN.PerProfileFinAidAwardPeriod
				RESULT = IncludeSubobjectFromSubobject( mFAProfN, "FinAidAwardPeriod", mFAProfN, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
				//:INCLUDE mFAProfN.FinAidAwardTransApplied FROM mFAProfO.FinAidAwardTransApplied
				RESULT = IncludeSubobjectFromSubobject( mFAProfN, "FinAidAwardTransApplied", mFAProfO, "FinAidAwardTransApplied", zPOS_AFTER );
				//:IF mFAProfO.SwappedFromDisbursement EXISTS
				lTempInteger_1 = CheckExistenceOfEntity( mFAProfO, "SwappedFromDisbursement" );
				if ( lTempInteger_1 == 0 )
				{
					//:INCLUDE mFAProfN.SwappedFromDisbursement FROM mFAProfO.SwappedFromDisbursement
					RESULT = IncludeSubobjectFromSubobject( mFAProfN, "SwappedFromDisbursement", mFAProfO, "SwappedFromDisbursement", zPOS_AFTER );
				}

				RESULT = mFAProfO.cursor( "FinAidAwardDisbursement" ).setNext().toInt();
				//:END
			}

			//:END

			//:// Check that Period subobject was spawned correctly.
			//:FOR EACH mFAProfO.FinAidAwardDisbursement
			RESULT = mFAProfO.cursor( "FinAidAwardDisbursement" ).setFirst().toInt();
			while ( RESULT > zCURSOR_UNCHANGED )
			{
				//:SET CURSOR FIRST mFAProfN.PerProfileFinAidAwardPeriod
				//:           WHERE mFAProfN.PerProfileFinAidAwardPeriod.PeriodDesignator = mFAProfN.FinAidAwardPeriod.PeriodDesignator
				{StringBuilder sb_szTempString_0;
				if ( szTempString_0 == null )
					sb_szTempString_0 = new StringBuilder( 32 );
				else
					sb_szTempString_0 = new StringBuilder( szTempString_0 );
				GetStringFromAttribute( sb_szTempString_0, mFAProfN, "FinAidAwardPeriod", "PeriodDesignator" );
				szTempString_0 = sb_szTempString_0.toString( );}
				RESULT = mFAProfN.cursor( "PerProfileFinAidAwardPeriod" ).setFirst( "PeriodDesignator", szTempString_0 ).toInt();
				//:IF mFAProfN.PerProSATransactionCode DOES NOT EXIST
				lTempInteger_2 = CheckExistenceOfEntity( mFAProfN, "PerProSATransactionCode" );
				if ( lTempInteger_2 != 0 )
				{
					//:IssueError( ViewToWindow,0,0, "PerProSATransactionCode was not correctly included." )
					Assert.assertEquals("PerProSATransactionCode was not correctly included.", CheckExistenceOfEntity( mFAProfN, "PerProSATransactionCode" ), 0);
					//:ELSE
				}
				else
				{
					//:SET CURSOR FIRST mFAProfN.PerPeriodFinAidAwardDisbursement
					//:           WHERE mFAProfN.PerPeriodFinAidAwardDisbursement.Amount               = mFAProfN.FinAidAwardDisbursement.Amount
					{MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
					GetDecimalFromAttribute( md_dTempDecimal_0, mFAProfN, "FinAidAwardDisbursement", "Amount" );
					dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
					RESULT = mFAProfN.cursor( "PerPeriodFinAidAwardDisbursement" ).setFirst( "Amount", dTempDecimal_0 ).toInt();
					//:                 mFAProfN.PerPeriodFinAidAwardDisbursement.AppliedToAccountDate = mFAProfN.FinAidAwardDisbursement.AppliedToAccountDate
					SetAttributeFromAttribute( mFAProfN, "PerPeriodFinAidAwardDisbursement", "AppliedToAccountDate", mFAProfN, "FinAidAwardDisbursement", "AppliedToAccountDate" );
					if ( RESULT < zCURSOR_SET )
					{
						//:IssueError( ViewToWindow,0,0, "No attribute match on FinAidAwardDisbursement ID." )
						Assert.assertTrue( "No attribute match on FinAidAwardDisbursement ID.", RESULT >= zCURSOR_SET );
						//:ELSE
					}
					else
					{
						//:IF mFAProfN.FinAidSource.Name != mFAProfN.DisbFinAidSource.Name
						Assert.assertEquals("No match on DisbFinAidSource Name.", CompareAttributeToAttribute( mFAProfN, "FinAidSource", "Name", mFAProfN, "DisbFinAidSource", "Name" ), 0 );
						//:END
						Assert.assertEquals("No match on PerProSATransactionCode ID.", CompareAttributeToAttribute( mFAProfN, "SATransactionCode", "ID", mFAProfN, "PerProSATransactionCode", "ID" ), 0 );

						//:END
						//:IF mFAProfN.SwappedFromDisbursement EXISTS
						lTempInteger_3 = CheckExistenceOfEntity( mFAProfN, "SwappedFromDisbursement" );
						if ( lTempInteger_3 == 0 )
						{
							Assert.assertEquals("No match on PeriodSwappedFromDisbursement ID.", CompareAttributeToAttribute( mFAProfN, "PeriodSwappedFromDisbursement", "ID", mFAProfN, "SwappedFromDisbursement", "ID" ), 0 );
						}

						//:END
					}

					//:END
				}

				RESULT = mFAProfO.cursor( "FinAidAwardDisbursement" ).setNext().toInt();
				//:END
			}
			DropView( mFAProfO );
			DropView( mFAProfN );
			//:END
			return( 0 );
			//
			// END
		}

		public void testActivateRecurObj( View     ViewToWindow )
		{
		   zVIEW    TstRecur = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int      RESULT = 0;
		   int		iLevel1 = 0;
		   int		iLevel2 = 0;
		   int		lTempInteger_1 = 0;

           // This is to confirm that we load a recursive object correctly.
		   // If sqlite is updated correctly, we should have 3 FinancialApprovalRole(s)
		   // The first FinancialApprovalRole has 2 children, the second has 25 and the
		   // third has none.

	        // Turn off assertions for zeidon for this test.
	        //ClassLoader loader = ClassLoader.getSystemClassLoader();
	        //loader.setPackageAssertionStatus( "com.quinsoft.zeidon", false );
	        //loader.setPackageAssertionStatus( "com.quinsoft.zeidon.test", false );
		   //com.quinsoft.zeidon.test

		   //:ACTIVATE  TstRecur WHERE TstRecur.User.UserName = "woostert"
		   o_fnLocalBuildTstRecur( ViewToWindow, vTempViewVar_0 );
		   RESULT = ActivateObjectInstance( TstRecur, "TstRecur", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );
		   //:NAME VIEW TstRecur "TstRecur"
		   SetNameForView( TstRecur, "TstRecur", null, zLEVEL_TASK );

		   RESULT = SetCursorFirstEntity( TstRecur, "FinancialApprovalRole", "" );

           RESULT = GetRecursEnts( TstRecur );

           /*
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
			   iLevel1++;
			   iLevel2 = 0;
               lTempInteger_1 = CheckExistenceOfEntity( TstRecur, "FinancialApprovalRoleChild" );
               if ( lTempInteger_1 == 0 )
               {

                  //:SetViewToSubobject( vReportDefRecurs, "PartialReportEntityChild" )
                  SetViewToSubobject( TstRecur, "FinancialApprovalRoleChild" );
	       		  RESULT = SetCursorFirstEntity( TstRecur, "FinancialApprovalRole", "" );
	    		  while ( RESULT > zCURSOR_UNCHANGED )
	    		  {
	                 iLevel2++;
   	  		         RESULT = SetCursorNextEntity( TstRecur, "FinancialApprovalRole", "" );
	    		  }
	              ResetViewFromSubobject( TstRecur );
	              if ( iLevel1 == 1  )
						Assert.assertEquals("Recursive object level2 should be 2 ", 2, iLevel2 );
                  if ( iLevel1 == 2 )
						Assert.assertEquals("Recursive object level2 should be 25 ", 25, iLevel2 );
                  if ( iLevel1 == 3  )
						Assert.assertEquals("Recursive object level2 should be 0 ", 0, iLevel2);
	           }

		       RESULT = SetCursorNextEntity( TstRecur, "FinancialApprovalRole", "" );

		      //:END
		   }


		   Assert.assertEquals("Recursive object level1 should be 3 ", 3, iLevel1 );
		   */
		   DropView( TstRecur );


		}

		public void testDomainCompareIssue( View ViewToWindow )
		{
			   zVIEW    mCRStdPLST = new zVIEW( );
			   zVIEW    mFaculty = new zVIEW( );
			   int      RESULT = 0;
			   int      lTempInteger_0 = 0;
			   int      lTempInteger_1 = 0;
			   int      lTempInteger_2 = 0;

			   RESULT = ActivateObjectInstance( mFaculty, "mFaculty", ViewToWindow, 0, zMULTIPLE );
			   SetNameForView( mFaculty, "mFaculty", null, zLEVEL_TASK );

		       OrderEntityForView( mFaculty, "Faculty", "Status  A" );

			   // Getting an error on the OrderEntityForView in AltStaticTableDomain.java because we
			   // end up comparing an integer to a string (which is an integer).
			   // I can also get this error in AbstractDomain.java line 347 if I'm not using AltStatic...
			   // If I add "Object newValue = attributeValue.convertInternalValue( getTask(), attributeDef, value );"
			   // in AttributeInstanceImpl.java setInternalValue(), then I don't get an error because then I am
			   // comparing integer and integer.
			   RESULT = ActivateObjectInstance( mCRStdPLST, "mCRStdP", ViewToWindow, 0, zMULTIPLE );
			   SetNameForView( mCRStdPLST, "mCRStdPLST", null, zLEVEL_TASK );
			   RESULT = SetCursorFirstEntity( mCRStdPLST, "ClassRoomStandardSchedule", "" );
			   while ( RESULT > zCURSOR_UNCHANGED )
			   {
			      lTempInteger_0 = CheckExistenceOfEntity( mCRStdPLST, "ClassRoomSession" );
			      if ( lTempInteger_0 == 0 )
			      {
			         OrderEntityForView( mCRStdPLST, "ClassRoomSession", "MeetingDay A StartTime  A" );
			      }

			      RESULT = SetCursorNextEntity( mCRStdPLST, "ClassRoomStandardSchedule", "" );
			   }
		}

		public void testActivateRecurObjSetFirst( View     ViewToWindow )
		{
		   zVIEW    TstRecur = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int      RESULT = 0;
		   int      nRC = 0;
		   int		iLevel1 = 0;
		   int		iLevel2 = 0;
		   int		lTempInteger_1 = 0;

		   // Wondering why on the second SetCursorFirstEntityByInteger for "FinancialApprovalRoleChild"
		   // we get a RETURN of -3. In SetCursorFirstEntityByInteger, the line for:
		   //      EntityCursor cursor = view.cursor( entityName );
           // The currentIterator is an EmptyIterator. Why is that?

		   //:ACTIVATE  TstRecur WHERE TstRecur.User.UserName = "woostert"
		   o_fnLocalBuildTstRecur( ViewToWindow, vTempViewVar_0 );
		   RESULT = ActivateObjectInstance( TstRecur, "TstRecur", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );
		   //:NAME VIEW TstRecur "TstRecur"
		   SetNameForView( TstRecur, "TstRecur", null, zLEVEL_TASK );

		   RESULT = SetCursorFirstEntityByInteger( TstRecur, "FinancialApprovalRole", "ID", 103, "" );
		   //:IF RESULT >= zCURSOR_SET
		   if ( RESULT >= zCURSOR_SET )
		   {
		      //:SET CURSOR FIRST TstRecur.FinancialApprovalRoleChild WHERE TstRecur.FinancialApprovalRoleChild.ID = 109
		      RESULT = SetCursorFirstEntityByInteger( TstRecur, "FinancialApprovalRoleChild", "ID", 109, "" );

		      if ( RESULT == -3 )
		      {
		    	   Assert.assertEquals("We should have a recursive child here but we don't ", 0, 1);
		      }
		      //:TraceLineI("*** Set cursor first RESULT *** ", RESULT)
		      TraceLineI( "*** Set cursor first RESULT *** ", RESULT );
		      //:IF RESULT >= zCURSOR_SET
		      if ( RESULT >= zCURSOR_SET )
		      {
		         //:TraceLineS("*** Yes FinancialApprovalRoleChild 109 *** ", "")
		         TraceLineS( "*** Yes FinancialApprovalRoleChild 109 *** ", "" );
		         //:nRC = SetViewToSubobject( TstRecur, "FinancialApprovalRoleChild")
		         nRC = SetViewToSubobject( TstRecur, "FinancialApprovalRoleChild" );
		         //:ELSE
		      }
		      else
		      {
		         //:TraceLineS("*** NO!! FinancialApprovalRoleChild 109 *** ", "")
		         TraceLineS( "*** NO!! FinancialApprovalRoleChild 109 *** ", "" );
		      }
		   }
		   DropView( TstRecur );

		}

		public int GetRecursEnts( View TstRecur)
		{
			   int RESULT = 0;
			   int lTempInteger_1 = 0;
			   int iID = 0;
			   MutableInt miID = new MutableInt( );

			   //:// Build one level of the PartialReportEntity subobject, matching the structure of the Driving LOD and
			   //:// setting the ReportDisplayFlag entity for any entity with a corresponding GroupSet entity in the report, or having
			   //:// a PartialReportEntityChild entity with a corresponding GroupSet entity. The flag is set to "D" if the entity has
			   //:// a corresponding GroupSet entity and to a "C" if it has a child with a corresponding GroupSet entity.

			   //:FOR EACH vReportDefRecurs.PartialReportEntity
			   RESULT = SetCursorFirstEntity( TstRecur, "FinancialApprovalRole", "" );
			   while ( RESULT > zCURSOR_UNCHANGED )
			   {
	               lTempInteger_1 = CheckExistenceOfEntity( TstRecur, "FinancialApprovalRoleChild" );
			       GetIntegerFromAttribute( miID, TstRecur, "FinancialApprovalRole", "ID" );
			       if ( miID.intValue( ) == 109 && lTempInteger_1 != 0 )
			       {
			    	   Assert.assertEquals("We should have a recursive child here but we don't ", 0, 1);
			       }
	               if ( lTempInteger_1 == 0 )
	               {

	                  SetViewToSubobject( TstRecur, "FinancialApprovalRoleChild" );

	                  RESULT = GetRecursEnts( TstRecur );

	                  ResetViewFromSubobject( TstRecur );
	               }

			       RESULT = SetCursorNextEntity( TstRecur, "FinancialApprovalRole", "" );

			      //:END
			   }

		   return( 0 );

		}

		public void testOutOfMemory()
		{

			int nCnt = 0;
			int RESULT;
			String   szTempString_0 = null;
			String   szTempString_1 = null;
			int      lTempInteger_4 = 0;

			//START OF TEST
			//This test scenario will give me an OutOfMemoryError only if I run it twice.
			//There is a drop statement at the end that if I add in, I never get the OutOfMemory problem.
			//I believe in zeidon, if you activate an object that is already created, it will clean
			//up the old and create the new.  I'm wondering if this is not happening in JOE?
			View TermslClsLstC = zencas.activateEmptyObjectInstance( "lClsLstC");
			View lClsLstC      = zencas.activateEmptyObjectInstance( "lClsLstC");
			lClsLstC = zencas.activateOiFromFile( "lClsLstC", "./testdata/ZENCAs/lClsLstC.por", ActivateFlags.IGNORE_ATTRIB_ERRORS );
			lClsLstC.setName( "lClsLstC" );

			zVIEW    lClsLstCT = new zVIEW( );
			//String   szTempString_0 = null;
			//String   szTempString_1 = null;

			//protected int CreateViewFromView( zVIEW tgtView, View srcView )
			View newView = lClsLstC.newView( );
			lClsLstCT.setView( newView );

			lClsLstCT.setName( "lClsLstCT" );
			RESULT = lClsLstC.cursor( "Class" ).setFirst().toInt();
			while ( RESULT > CursorResult.UNCHANGED.toInt() )
			{
				if ( lClsLstC.cursor("Class").compareAttribute( "wCourseID", "" ) == 0 )
				{
					lClsLstC.cursor("Class").setAttributeFromAttribute("wCourseID", lClsLstC, "Course", "ID" );
					lClsLstC.cursor("Class").setAttributeFromAttribute("wCourseNumber", lClsLstC, "Course", "Number" );
				}

				if ( lClsLstC.cursor("Class").compareAttribute( "wCrossListedFlag", "Y" ) != 0 )
				{
					RESULT = lClsLstC.cursor( "CrossListedCourse" ).setFirst().toInt();
					while ( RESULT > CursorResult.UNCHANGED.toInt() )
					{
						RESULT = lClsLstCT.cursor( "Class" ).setFirst().toInt();
						if ( RESULT > CursorResult.UNCHANGED.toInt() )
						{
							// !!! compareAttribute will give an error when one of the entities is null so I wasn't
							// able to use this when I ran this test on MyENC.  I had to use the CompareAttributeToA...
							while ( RESULT > CursorResult.UNCHANGED.toInt() && ( lClsLstC.cursor("Course").compareAttribute( "ID", lClsLstCT.cursor("CrossListedCourse"), "ID" ) != 0 ) )
								while ( RESULT > CursorResult.UNCHANGED.toInt() && ( CompareAttributeToAttribute( lClsLstCT, "Course", "ID", lClsLstC, "CrossListedCourse", "ID" ) != 0 ) )
								{
									RESULT = lClsLstCT.cursor( "Class" ).setNext().toInt();
								}

						}
						if ( RESULT < CursorResult.UNCHANGED.toInt() )
						{
							lClsLstCT.cursor( "Class" ).includeSubobject( lClsLstC.cursor( "Class" ), CursorPosition.NEXT );
							szTempString_0 = lClsLstC.cursor( "CrossListedCourse" ).getStringFromAttribute( "Number" );
							szTempString_0 = lClsLstCT.cursor( "Class" ).getStringFromAttribute( "Section" );
							szTempString_0 = szTempString_0 + szTempString_1;
							lClsLstCT.cursor("Class").setAttribute("wClassNumberTopicSection", szTempString_0);
							lClsLstCT.cursor("Class").setAttribute("wCrossListedFlag", "Y");
							lClsLstCT.cursor( "CorrespondingCrossListedCourse" ).includeSubobject( lClsLstC.cursor( "CrossListedCourse" ), CursorPosition.NEXT );
							lClsLstCT.cursor("Class").setAttributeFromAttribute("wCourseID", lClsLstC, "CrossListedCourse", "ID" );
							lClsLstCT.cursor("Class").setAttributeFromAttribute("wCourseNumber", lClsLstC, "CrossListedCourse", "Number" );
						}

						RESULT = lClsLstC.cursor( "CrossListedCourse" ).setNext().toInt();
					}
				}

				RESULT = lClsLstC.cursor( "Class" ).setNext().toInt();
			}

			lClsLstCT.drop();


			RESULT = lClsLstC.cursor( "Class" ).setFirst().toInt();
			nCnt = 0;
			while ( RESULT > CursorResult.UNCHANGED.toInt() )
			{
				nCnt = nCnt + 1;
				//RESULT = IncludeSubobjectFromSubobject( TermslClsLstC, "Class", lClsLstC, "Class", zPOS_AFTER );
				TermslClsLstC.cursor( "Class" ).includeSubobject( lClsLstC.cursor( "Class" ), CursorPosition.NEXT );
				TermslClsLstC.cursor("Class").setAttributeFromAttribute( "wClassNumberTopicSection", lClsLstC, "Class", "wClassNumberTopicSection" );
				TermslClsLstC.cursor("Class").setAttributeFromAttribute( "wCrossListedFlag", lClsLstC, "Class", "wCrossListedFlag" );
				//SetAttributeFromAttribute( TermslClsLstC, "Class", "wClassNumberTopicSection", lClsLstC, "Class", "wClassNumberTopicSection" );
				//SetAttributeFromAttribute( TermslClsLstC, "Class", "wCrossListedFlag", lClsLstC, "Class", "wCrossListedFlag" );
				//lTempInteger_4 = CheckExistenceOfEntity( lClsLstC, "CorrespondingCrossListedCourse" );
				lTempInteger_4 = lClsLstC.cursor("CorrespondingCrossListedCourse").checkExistenceOfEntity().toInt();
				if ( lTempInteger_4 == 0 )
				{
					//:INCLUDE TermslClsLstC.CorrespondingCrossListedCourse FROM lClsLstC.CorrespondingCrossListedCourse
					//RESULT = IncludeSubobjectFromSubobject( TermslClsLstC, "CorrespondingCrossListedCourse", lClsLstC, "CorrespondingCrossListedCourse", zPOS_AFTER );
					TermslClsLstC.cursor( "CorrespondingCrossListedCourse" ).includeSubobject( lClsLstC.cursor( "CorrespondingCrossListedCourse" ), CursorPosition.NEXT );
				}

				RESULT = lClsLstC.cursor( "Class" ).setNext().toInt();
			}
			// IF I PUT THE FOLLOWING drop statement in, then i can run this as many times as i want.
			// if i take it out, then second time i get heap space.
			//lClsLstC.drop();
			//END OF TEST */

			DropView( lClsLstC );
			DropView( TermslClsLstC );


		}
		private int
		omSAProfE_fnLocalBuildQual_0( View     vSubtask,
		                              zVIEW    vQualObject,
		                              int      lTempInteger_15,
		                              int      lTempInteger_16,
		                              int      lTempInteger_17,
		                              int      lTempInteger_18 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidProfile" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_15 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_16 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidAward" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidAwardPeriodTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_17 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidAwardDisbursement" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidAwardPeriodTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_18 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidTrack" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidTrack" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidRequirementMet" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidRequirementMet" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidCOAItemAssigned" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidCOAItemAssigned" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PerProfileFinAidAwardPeriod" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PerProfileFinAidAwardPeriod" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "OtherFAISIR" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "OtherFAISIR" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FAISIR" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FAISIR" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "WorkStudyAllocation" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "WorkStudyAllocation" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CurrentPerson" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CurrentPerson" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}
		private int
		o_fnLocalBuildmStudent( View     vSubtask,
		                       zVIEW    vQualObject,
		                       int      lTempInteger_0 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}
		private int
		fnLocalBuildlTermLST( View     vSubtask,
		                       zVIEW    vQualObject )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeTerm" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Year" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "2000-2001" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ">=" );
		   return( 0 );
		}
		private int
		o_fnLocalBuildDOMAINT( View     vSubtask,
		                       zVIEW    vQualObject,
		                       String   szDomainName )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}
		private int
		o_fnLocalBuildlStuClAt( View     vSubtask,
		                      zVIEW    vQualObject,
		                      int      lTempInteger_3,
		                      String   szTempString_2 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "YearSemester" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_2.toString( ) );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		private int
		o_fnLocalBuildlTrnscpt( View     vSubtask,
		                      zVIEW    vQualObject,
		                      int      lTempInteger_0 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}
		private int
		o_fnLocalBuildmStudenC( View     vSubtask,
		                       zVIEW    vQualObject,
		                       int      lTempInteger_2 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}
		private int
		o_fnLocalBuildmSAProf( View     vSubtask,
		                       zVIEW    vQualObject,
		                       int      lTempInteger_6,
		                       int      lTempInteger_7 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_6 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ProfileAdministrativeDivision" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_7 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "BillingPeriod" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "BillingPeriod" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SASubAccount" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SASubAccount" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountHistoricalProfile" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountHistoricalProfile" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		private int
		omDegTrk_fnLocalBuildmDegTrk( View     vSubtask,
		                             zVIEW    vQualObject,
		                             int      lTempInteger_2 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "DegreeTrack" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "DegreeTrack" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		private int
		o_fnLocalBuildlClsLst( View     vSubtask,
		                       zVIEW    vQualObject,
		                       int      FacultyID,
		                       int      lTempInteger_1 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Class" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Instructor" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", FacultyID );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		private int
		o_fnLocalBuildmClass( View     vSubtask,
		                      zVIEW    vQualObject,
		                      int      lTempInteger_1 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Class" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Class" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		private int
		o_fnLocalBuildmUser( View     vSubtask,
		                       zVIEW    vQualObject,
		                       String   szTempString_0 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "User" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "UserName" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}


		private int
		o_fnLocalBuildmUserAdmin( View     vSubtask,
		                      zVIEW    vQualObject )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "User" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "UserName" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Admin" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		private int
		o_fnLocalBuildwConList( View     vSubtask,
		                      zVIEW    vQualObject,
		                      int      lTempInteger_0 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		private int
		o_fnLocalBuildQual_50( View     vSubtask,
		                       zVIEW    vQualObject,
		                       int      lTempInteger_0 )
		{
			   int      RESULT = 0;

			   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
			   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
			   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
			   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactActivity" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactActivity" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
			   return( 0 );
		}
		private int
		o_fnLocalBuildlAdvisee( View     vSubtask,
		                       zVIEW    vQualObject,
		                       int      FacultyID )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Advisee" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Faculty" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", FacultyID );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Advisee" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Status" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "A" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Advisee" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Status" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "D" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Registration" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Registration" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Status" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "T" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "AdviseeStudentTrack" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Faculty" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", FacultyID );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		private int
		o_fnLocalBuildQualmPerson( View     vSubtask,
		                       zVIEW    vQualObject,
		                       int      lTempInteger_0 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		private int
		o_fnLocalBuildmFAProf( View     vSubtask,
		                       zVIEW    vQualObject,
		                       int      lTempInteger_10 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidProfile" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidProfile" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_10 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		private int
		o_fnLocalBuildlClsLst( View     vSubtask,
		                              zVIEW    vQualObject,
		                              int      lTempInteger_3 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Class" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Class" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Status" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "C" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "!=" );
		   return( 0 );
		}

		private int
		o_BuildlClsLst2( View     vSubtask,
		                              zVIEW    vQualObject,
		                              int      lTempInteger_3 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Class" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", 161 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", 162 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", 163 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", 160 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   //*************************************
		   //
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", 152 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", 153 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", 154 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", 155 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   //
		   return( 0 );
		}

		private int
		o_fnLocalmFAProf( View     vSubtask,
		                       zVIEW    vQualObject,
		                       int      lTempInteger_10 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidProfile" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidProfile" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_10 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}
		private int
		o_fnLocalBuildmAdmDiv( View     vSubtask,
		                       zVIEW    vQualObject,
		                       int      AdminDivID )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "AdministrativeDivision" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", AdminDivID );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidRequirement" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidRequirement" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		private int
		o_fnLocalBuildlCategory( View     vSubtask,
		                         zVIEW    vQualObject )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Category" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "1" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Category" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "FunctionalArea" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Admissions" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		private int
		o_fnLocalBuildTstRecur( View     vSubtask,
		                       zVIEW    vQualObject )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "User" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "UserName" );
		   //SetAttributeFromString( vQualObject, "QualAttrib", "Value", "burtc" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "woostert" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}
   }
}
