/**
 *
 */
package com.quinsoft.zeidon.test;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Pagination;
import com.quinsoft.zeidon.SelectSet;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.SetMatchingFlags;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.utils.JoeUtils;
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
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assume.assumeThat;

/**
 * @author DG
 *
 */
public class TestZencas
{
<<<<<<< HEAD
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
	    JavaObjectEngine.resetInstance();
        oe = JavaObjectEngine.getInstance();
        zencas = oe.createTask( "ZENCAs" );
		zeidonSystem = oe.getSystemTask();
	}

	@Test
	public void testWritingXodAsJson() throws IOException
	{
	    Map<String, String> envs = System.getenv();
=======
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
        JavaObjectEngine.resetInstance();
        oe = JavaObjectEngine.getInstance();
        zencas = oe.createTask( "ZENCAs" );
        zeidonSystem = oe.getSystemTask();
    }

    @Test
    public void testWritingXodAsJson() throws IOException
    {
        Map<String, String> envs = System.getenv();
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
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
    public void testRollingPagination() throws IOException
    {
        View studFull = new QualificationBuilder( zencas )
                            .setLodDef( "lStudDpt" )
                            .addActivateOrdering( "Student", "LS_AdvisorName", true )
                            .addActivateOrdering( "Student", "ID", false )
                            .limitCountTo( 100 )
                            .setSingleTransaction()
                            .activate();
        studFull.cursor( "Student" ).setFirst();

        View stud = new QualificationBuilder( zencas )
                            .setLodDef( "lStudDpt" )
                            .setPagination( new Pagination().setRollingPagination( true ).setPageSize( 10 ) )
                            .addActivateOrdering( "Student", "LS_AdvisorName", true )
                            .setSingleTransaction()
                            .activate();

        int count = 0;
        for ( EntityInstance ei : stud.cursor( "Student" ).eachEntity() )
        {
            stud.log().info( "Key = %s", ei.getAttribute( "ID" ) );
            stud.log().info( "Key = %s", stud.cursor( "Student" ).getAttribute( "ID" ) );
            stud.log().info( "Key = %s", studFull.cursor( "Student" ).getAttribute( "ID" ) );
            stud.log().info( "%d -----------", count++ );

            // Verify that we're loading everything by comparing the IDs with studFull.
            Assert.assertEquals( studFull.cursor( "Student" ).getAttribute( "ID" ).getInteger(),
                                 stud    .cursor( "Student" ).getAttribute( "ID" ).getInteger() );

            if ( count > 55 )
                break;

            studFull.cursor( "Student" ).setNext();
        }

        studFull.drop();
    }


    @Test
    public void testActivatingEmptyString() throws IOException
    {
        View studFull = new QualificationBuilder( zencas )
                            .setLodDef( "lStudDpt" )
                            .addAttribQual( "eMailAddress", "" )
                            .limitCountTo( 100 )
                            .activate();
        studFull.cursor( "Student" ).setFirst();
        Assert.assertEquals( studFull.cursor( "Student" ).getEntityCount(), 0 );

        studFull = new QualificationBuilder( zencas )
                .setLodDef( "lStudDpt" )
                .addAttribQual( "Student", "eMailAddress", "!=", "" )
                .addAttribQual( "AND" )
                .addAttribQual( "Student", "ID", "<=", 10 )
                .limitCountTo( 20 )
                .activate();
        studFull.cursor( "Student" ).setFirst();
        Assert.assertEquals( studFull.cursor( "Student" ).getEntityCount(), 10 );
    }

<<<<<<< HEAD
	@Test
	public void testRecursion()
	{
=======
    @Test
    public void testRecursion()
    {
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
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
<<<<<<< HEAD
	}

	@Test
	public void testBlobs()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testBlobs( testview );
        System.out.println("===== Finished testBlobs ========");
	}

	@Test
	public void testRestrictWithParentJoin()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testRestrictWithParentJoin( testview );
        System.out.println("===== Finished testRestrictWithParentJoin ========");
	}

	@Test
	public void testQualKeyList()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testQualKeyList( testview );
        System.out.println("===== Finished testQualKeyList ========");
	}

	@Test
	public void testSetMatching()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testSetMatching( testview );
        System.out.println("===== Finished testSetMatching ========");
	}

	@Test
	public void CreateTemporalDerivedEntityWorkAttributeIssue()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.CreateTemporalDerivedEntityWorkAttributeIssue( testview );
        System.out.println("===== Finished CreateTemporalDerivedEntityWorkAttributeIssue ========");
	}

	@Test
	public void testAttributeReadOnlyError()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testAttributeReadOnlyError( testview );
        System.out.println("===== Finished testAttributeReadOnlyError ========");
	}

	@Test
	public void testAcceptSubobjectNoUpdate()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testAcceptSubobjectNoUpdate( testview );
        System.out.println("===== Finished testAcceptSubobjectNoUpdate ========");
	}

	@Test
	public void testDateTimeCompare()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testDateTimeCompare( testview );
        System.out.println("===== Finished testDateTimeCompare ========");
	}

	@Test
	public void testRelinking()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testRelinking( testview );
        System.out.println("===== Finished testRelinking ========");
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


	@Test
	public void testDropDynamicDomain()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testDropDynamicDomain( testview );
        System.out.println("===== Finished testDropDynamicDomain ========");
	}

	@Test
	public void testLODDisplayRoot()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testLODDisplayRoot( testview );
        System.out.println("===== Finished testLODDisplayRoot ========");
	}

	@Test
	public void testDeletePersonRoot()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testDeletePersonRoot( testview );
        System.out.println("===== Finished testDeletePersonRoot ========");
	}

	@Test
	public void testUpdateNonLatinCharacters()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testUpdateNonLatinCharacters( testview );
        System.out.println("===== Finished testUpdateNonLatinCharacters ========");
	}

	@Test
	public void testDerivedAttrCompare()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testDerivedAttrCompare( testview );
        System.out.println("===== Finished testDerivedAttrCompare ========");
	}

	/**
	 * Test password encryption.
	 */
=======
    }

    @Test
    public void testBlobs()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testBlobs( testview );
        System.out.println("===== Finished testBlobs ========");
    }

    @Test
    public void testRestrictWithParentJoin()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testRestrictWithParentJoin( testview );
        System.out.println("===== Finished testRestrictWithParentJoin ========");
    }

    @Test
    public void testQualKeyList()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testQualKeyList( testview );
        System.out.println("===== Finished testQualKeyList ========");
    }

    @Test
    public void testSetMatching()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testSetMatching( testview );
        System.out.println("===== Finished testSetMatching ========");
    }

    @Test
    public void CreateTemporalDerivedEntityWorkAttributeIssue()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.CreateTemporalDerivedEntityWorkAttributeIssue( testview );
        System.out.println("===== Finished CreateTemporalDerivedEntityWorkAttributeIssue ========");
    }

    @Test
    public void testAttributeReadOnlyError()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testAttributeReadOnlyError( testview );
        System.out.println("===== Finished testAttributeReadOnlyError ========");
    }

    @Test
    public void testAcceptSubobjectNoUpdate()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testAcceptSubobjectNoUpdate( testview );
        System.out.println("===== Finished testAcceptSubobjectNoUpdate ========");
    }

    @Test
    public void testDateTimeCompare()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testDateTimeCompare( testview );
        System.out.println("===== Finished testDateTimeCompare ========");
    }

    @Test
    public void testRelinking()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testRelinking( testview );
        System.out.println("===== Finished testRelinking ========");
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


    @Test
    public void testDropDynamicDomain()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testDropDynamicDomain( testview );
        System.out.println("===== Finished testDropDynamicDomain ========");
    }

    @Test
    public void testLODDisplayRoot()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testLODDisplayRoot( testview );
        System.out.println("===== Finished testLODDisplayRoot ========");
    }

    @Test
    public void testDeletePersonRoot()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testDeletePersonRoot( testview );
        System.out.println("===== Finished testDeletePersonRoot ========");
    }

    @Test
    public void testUpdateNonLatinCharacters()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testUpdateNonLatinCharacters( testview );
        System.out.println("===== Finished testUpdateNonLatinCharacters ========");
    }

    @Test
    public void testDerivedAttrCompare()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testDerivedAttrCompare( testview );
        System.out.println("===== Finished testDerivedAttrCompare ========");
    }

    /**
     * Test password encryption.
     */
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
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

<<<<<<< HEAD
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
	public void testLongStringWBindFalse()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testLongStringWBindFalse( testview );
        System.out.println("===== Finished testLongStringWBindFalse ========");
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
	public void testAcceptSubobject()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testAcceptSubobject( testview );
        System.out.println("===== Finished testAcceptSubobject ========");
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
	public void TEST_TemporalSaveIssuemSAProf()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.TEST_TemporalSaveIssuemSAProf( testview );
        System.out.println("===== Finished TEST_TemporalSaveIssuemSAProf ========");
	}

	@Test
	public void testXpg()
	{
	    JspWebUtils.createWebSession( this, zencas, "123" );
	}

	@Test
	public void testJson() throws IOException
	{
=======
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
    public void testLongStringWBindFalse()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testLongStringWBindFalse( testview );
        System.out.println("===== Finished testLongStringWBindFalse ========");
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
    public void testAcceptSubobject()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testAcceptSubobject( testview );
        System.out.println("===== Finished testAcceptSubobject ========");
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
    public void TEST_TemporalSaveIssuemSAProf()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.TEST_TemporalSaveIssuemSAProf( testview );
        System.out.println("===== Finished TEST_TemporalSaveIssuemSAProf ========");
    }

    @Test
    public void testXpg()
    {
        JspWebUtils.createWebSession( this, zencas, "123" );
    }

    @Test
    public void testJson() throws IOException
    {
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
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

        SerializeOi options = new SerializeOi();
        options.withIncremental();
        new SerializeOi().withIncremental().useCamelCase().addView( stud, person ).toTempDir( "stud.json" );

        List<View> viewList = new DeserializeOi( zencas )
                                        .asJson()
                                        .fromTempDir( "stud.json" )
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
<<<<<<< HEAD
	}

	@Test
	public void testSelectSet()
	{
=======
    }

    @Test
    public void testSelectSet()
    {
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
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
        Set<EntityInstance> set = selectSet;
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
        set = selectSet;
        for ( EntityInstance ei : selectSet.eachEntity() )
        {
            long p = ei.getPosition();
            Assert.assertTrue( "EI isn't in select set", set.contains( track.getEntityInstance() ) );
        }

        stud.logObjectInstance();

<<<<<<< HEAD
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
	public void testIncludePersonCommit()
	{
	    assumeThat( "Run all failing tests", JoeUtils.getEnvProperty( "runAllTests" ), is( "true" ) );
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testIncludePersonCommit( testview );
        System.out.println("===== Finished testInclude ========");
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
=======
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
    public void testIncludePersonCommit()
    {
        assumeThat( "Run all failing tests", JoeUtils.getEnvProperty( "runAllTests" ), is( "true" ) );
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testIncludePersonCommit( testview );
        System.out.println("===== Finished testInclude ========");
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
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b

    @Test
    public void testInclude3()
    {
        View         testview;
        testview = zencas.deserializeOi()
                         .fromFile( "target/test-classes/testdata//ZENCAs/TestInclude3-mFAProfO.por" )
                         .setLodDef( "mFAProf" )
                         .activateFirst();

        int rc = testview.cursor( "FinAidAward" ).setFirst( "ID", 50224 ).toInt();
        View profn = zencas.activateEmptyObjectInstance( "mFAProf" );
        profn.cursor( "FinAidProfile" ).createEntity();
        profn.cursor( "FinAidProfile" ).setMatchingAttributesByName( testview.cursor( "FinAidProfile" ), SetMatchingFlags.DEFAULT);
        profn.cursor( "Person" ).includeSubobject( testview.cursor( "Person" ), CursorPosition.LAST );

        View view = zencas.deserializeOi()
                .fromFile( "target/test-classes/testdata//ZENCAs/AfterPersonInclude.por" )
                .setLodDef( "mFAProf" )
                .activateFirst();
        Assert.assertTrue( "Views don't match after include", view.equalsOi( profn ) );

        System.out.println("done");
<<<<<<< HEAD
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

//	@Test
	public void testzGetNextEntityAttributeName()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testzGetNextEntityAttributeName( testview );
        System.out.println("===== Finished testzGetNextEntityAttributeName ========");
	}

	@Test
	public void testIncludeCommit()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testIncludeCommit( testview );
        System.out.println("===== Finished testIncludeCommit ========");
	}

	@Test
	public void testLocking()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testLocking( testview );
        System.out.println("===== Finished testIncludeCommit ========");
	}

	@Test
	public void testIncludeCommitSubentityError()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testIncludeCommitSubentityError( testview );
        System.out.println("===== Finished testIncludeCommitSubentityError ========");
	}

	@Test
	public void mPersonProspectSaveAttribute()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.mPersonProspectSaveAttribute( testview );
        System.out.println("===== Finished mPersonProspectSaveAttribute ========");
	}

	@Test
	public void IncludeExcludeMaxCardinalityIssue()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.IncludeExcludeMaxCardinalityIssue( testview );
        System.out.println("===== Finished IncludeExcludeMaxCardinalityIssue ========");
	}

	@Test
	public void testExcludeSubentityError()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testExcludeSubentityError( testview );
        System.out.println("===== Finished testExcludeSubentityError ========");
	}

	/**
	 * Verify that if we try to update an attribute that is hidden we won't allow it unless
	 * it is linked to an entity that has the attribute visible.
	 */
=======
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

//    @Test
    public void testzGetNextEntityAttributeName()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testzGetNextEntityAttributeName( testview );
        System.out.println("===== Finished testzGetNextEntityAttributeName ========");
    }

    @Test
    public void testIncludeCommit()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testIncludeCommit( testview );
        System.out.println("===== Finished testIncludeCommit ========");
    }

    @Test
    public void testLocking()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testLocking( testview );
        System.out.println("===== Finished testIncludeCommit ========");
    }

    @Test
    public void testIncludeCommitSubentityError()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testIncludeCommitSubentityError( testview );
        System.out.println("===== Finished testIncludeCommitSubentityError ========");
    }

    @Test
    public void mPersonProspectSaveAttribute()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.mPersonProspectSaveAttribute( testview );
        System.out.println("===== Finished mPersonProspectSaveAttribute ========");
    }

    @Test
    public void IncludeExcludeMaxCardinalityIssue()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.IncludeExcludeMaxCardinalityIssue( testview );
        System.out.println("===== Finished IncludeExcludeMaxCardinalityIssue ========");
    }

    @Test
    public void testExcludeSubentityError()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testExcludeSubentityError( testview );
        System.out.println("===== Finished testExcludeSubentityError ========");
    }

    /**
     * Verify that if we try to update an attribute that is hidden we won't allow it unless
     * it is linked to an entity that has the attribute visible.
     */
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
    @Test
    public void testUpdatingHiddenAttribute()
    {
        View view = new DeserializeOi( zencas )
                .asJson()
                .fromFile( "target/test-classes/testdata/ZENCAs/mFASrc-update-hidden.json" )
                .activateFirst();

        try {
            view.commit();
            Assert.fail("Expected an ZeidonException to be thrown");
        } catch (ZeidonException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.containsString("Attribute is defined as read-only"));
        }

    }

<<<<<<< HEAD
	@Test
	public void testActivateRecurObj()
	{
	    View         testview;
=======
    @Test
    public void testActivateRecurObj()
    {
        View         testview;
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
        // Turn off assertions for zeidon for this test.
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        loader.setPackageAssertionStatus( "com.quinsoft.zeidon", false );
        loader.getSystemClassLoader().setDefaultAssertionStatus(false);
<<<<<<< HEAD
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testActivateRecurObj( testview );
        System.out.println("===== Finished testActivateRecurObj ========");
	}

	@Test
	public void testAutoloadFromParent()
	{
	    View         testview;
=======
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testActivateRecurObj( testview );
        System.out.println("===== Finished testActivateRecurObj ========");
    }

    @Test
    public void testAutoloadFromParent()
    {
        View         testview;
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
        // Turn off assertions for zeidon for this test.
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        loader.setPackageAssertionStatus( "com.quinsoft.zeidon", false );
        loader.getSystemClassLoader().setDefaultAssertionStatus(false);
<<<<<<< HEAD
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testAutoloadFromParent( testview );
        System.out.println("===== Finished testAutoloadFromParent ========");
	}

	@Test
	public void testActivateORStmnt()
	{
	    View         testview;
=======
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testAutoloadFromParent( testview );
        System.out.println("===== Finished testAutoloadFromParent ========");
    }

    @Test
    public void testActivateORStmnt()
    {
        View         testview;
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
        // Turn off assertions for zeidon for this test.
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        loader.setPackageAssertionStatus( "com.quinsoft.zeidon", false );
        loader.getSystemClassLoader().setDefaultAssertionStatus(false);
<<<<<<< HEAD
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testActivateORStmnt( testview );
        System.out.println("===== Finished testActivateORStmnt ========");
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
	public void testNullTableDomain()
	{
	    View         testview;
        // Turn off assertions for zeidon for this test.
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        loader.setPackageAssertionStatus( "com.quinsoft.zeidon", false );
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testNullTableDomain( testview );
        System.out.println("===== Finished testNullTableDomain ========");
	}

//    @Test
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
=======
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testActivateORStmnt( testview );
        System.out.println("===== Finished testActivateORStmnt ========");
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
    public void testNullTableDomain()
    {
        View         testview;
        // Turn off assertions for zeidon for this test.
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        loader.setPackageAssertionStatus( "com.quinsoft.zeidon", false );
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testNullTableDomain( testview );
        System.out.println("===== Finished testNullTableDomain ========");
    }

//    @Test
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
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b

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
            if ( view.cursor( "GLJournalEntry" ).getAttribute( "TransactionDate").compare( "20110101" )  >= 0 )
                count++;
        }

        zencas.log().info( "Count = %d, time = %s", count, timer );

        timer = new Timer();
        count = 0;
        for ( CursorResult rc = view.cursor( "GLJournalEntry" ).setFirst();
              rc.isSet();
              rc = view.cursor( "GLJournalEntry" ).setNext() )
        {
            if ( view.cursor( "GLJournalEntry" ).getAttribute( "TransactionDate").compare( "20110101" )  >= 0 )
                count++;
        }

        zencas.log().info( "Count2 = %d, time = %s", count, timer );

        timer = new Timer();
        count = 0;
        EntityCursor cursor = view.cursor( "GLJournalEntry" );
        for ( EntityInstance ei : cursor.eachEntity() )
        {
            if ( ei.getAttribute( "TransactionDate").compare( "20110101" )  >= 0 )
                count++;
        }
        zencas.log().info( "Count3 = %d, time = %s", count, timer );

        timer = new Timer();
        count = 0;
        for ( int rc = view.cursor( "GLJournalEntry" ).setFirst().toInt();
              rc >= 0;
              rc = view.cursor( "GLJournalEntry" ).setNext().toInt() )
        {
            if ( view.cursor( "GLJournalEntry" ).getAttribute( "TransactionDate").compare( "20110101" )  >= 0 )
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
            if ( view.cursor( "GLJournalEntry" ).getAttribute( "TransactionDate").compare( "20110101" )  >= 0 )
            {
                view.cursor( "GLJournalEntry" ).deleteEntity( CursorPosition.NONE );
                count++;
            }
        }

        zencas.log().info( "Deleted = %d, time = %s", count, timer );

        // Turn assertions back on.
        loader.clearAssertionStatus();
    }

<<<<<<< HEAD
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

	//@Test
	public void testCODXMLImport()
	{
        View         mapping;
        mapping = zeidonSystem.activateEmptyObjectInstance( "SerializationMapping" );

	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		// I am having a problem running MyENC with sqlite.  This is not a problem
		// when I run with access db.
		tester.testCODXMLImport( testview );
        System.out.println("===== Finished testCODXMLImport ========");
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

	@Test
	public void mUser_ActivateUserLST()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.mUser_ActivateUserLST( testview );
        System.out.println("===== Finished mUser_ActivateUserLST ========");
	}

	@Test
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

//	@Test
	public void testAutoLoadFromParent()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testAutoLoadFromParent( testview );
        System.out.println("===== Finished testAutoLoadFromParent ========");
	}
	@Test
	public void mFAProfPermissionIssueTemporal()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.mFAProfPermissionIssueTemporal( testview );
        System.out.println("===== Finished mFAProfPermissionIssueTemporal ========");
	}

	@Test
	public void mFAProfPermissionIssueTemporal2()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.mFAProfPermissionIssueTemporal2( testview );
        System.out.println("===== Finished mFAProfPermissionIssueTemporal2 ========");
	}

	@Test
	public void mFAProfTemporalIssue3()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.mFAProfTemporalIssue3( testview );
        System.out.println("===== Finished mFAProfTemporalIssue3 ========");
	}

	@Test
	public void mFAProfTemporalPerProfileFinAidAwardPeriodPathTest()
	{
	    assumeThat( "Run all failing tests", JoeUtils.getEnvProperty( "runAllTests" ), is( "true" ) );
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.mFAProfTemporalPerProfileFinAidAwardPeriodPathTest( testview );
        System.out.println("===== Finished mFAProfTemporalPerProfileFinAidAwardPeriodPathTest ========");
	}

	@Test
	public void mFAProfTemporalFinAidAward()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.mFAProfTemporalFinAidAward( testview );
        System.out.println("===== Finished mFAProfTemporalFinAidAward ========");
	}

	@Test
	public void mFAProfTemporalLinkIssue()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.mFAProfTemporalLinkIssue( testview );
        System.out.println("===== Finished mFAProfTemporalLinkIssue ========");
	}
	
	@Test
	public void mFAProfCreateTemporalCODLinkIssue()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.mFAProfCreateTemporalCODLinkIssue( testview );
        System.out.println("===== Finished mFAProfCreateTemporalCODLinkIssue ========");
	}

	@Test
	public void testExclInclOrderEntities()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mFASrc" );
		VmlTester tester = new VmlTester( testview );
		tester.testExclInclOrderEntities( testview );
        System.out.println("===== Finished testExclInclOrderEntities ========");
	}

	@Test
	public void testUpdateForeignKeys()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStudent" );
		VmlTester tester = new VmlTester( testview );
		tester.testUpdateForeignKeys( testview );
        System.out.println("===== Finished testUpdateForeignKeys ========");
	}

	@Test
	public void testUsingJacob()
	{
=======
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

//    @Test
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
        //GetVariableFromAttribute( sb_szDate, mi_lTempInteger_2, 'S', 101, mStudenC, "SavedDegreeAudit", "CreatedDateTime", "", 0 );            //com.quinsoft.zeidon.InvalidAttributeValueException: Invalid value for attribute
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

    //@Test
    public void testCODXMLImport()
    {
        View         mapping;
        mapping = zeidonSystem.activateEmptyObjectInstance( "SerializationMapping" );

        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mStudent" );
        VmlTester tester = new VmlTester( testview );
        // I am having a problem running MyENC with sqlite.  This is not a problem
        // when I run with access db.
        tester.testCODXMLImport( testview );
        System.out.println("===== Finished testCODXMLImport ========");
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

    @Test
    public void mUser_ActivateUserLST()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.mUser_ActivateUserLST( testview );
        System.out.println("===== Finished mUser_ActivateUserLST ========");
    }

    @Test
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

//    @Test
    public void testAutoLoadFromParent()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testAutoLoadFromParent( testview );
        System.out.println("===== Finished testAutoLoadFromParent ========");
    }
    @Test
    public void mFAProfPermissionIssueTemporal()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.mFAProfPermissionIssueTemporal( testview );
        System.out.println("===== Finished mFAProfPermissionIssueTemporal ========");
    }

    @Test
    public void mFAProfPermissionIssueTemporal2()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.mFAProfPermissionIssueTemporal2( testview );
        System.out.println("===== Finished mFAProfPermissionIssueTemporal2 ========");
    }

    @Test
    public void mFAProfTemporalIssue3()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.mFAProfTemporalIssue3( testview );
        System.out.println("===== Finished mFAProfTemporalIssue3 ========");
    }

    @Test
    public void mFAProfTemporalPerProfileFinAidAwardPeriodPathTest()
    {
        assumeThat( "Run all failing tests", JoeUtils.getEnvProperty( "runAllTests" ), is( "true" ) );
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.mFAProfTemporalPerProfileFinAidAwardPeriodPathTest( testview );
        System.out.println("===== Finished mFAProfTemporalPerProfileFinAidAwardPeriodPathTest ========");
    }

    @Test
    public void mFAProfTemporalFinAidAward()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.mFAProfTemporalFinAidAward( testview );
        System.out.println("===== Finished mFAProfTemporalFinAidAward ========");
    }

    @Test
    public void mFAProfTemporalLinkIssue()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.mFAProfTemporalLinkIssue( testview );
        System.out.println("===== Finished mFAProfTemporalLinkIssue ========");
    }

    //@Test
    public void mFAProfCreateTemporalCODLinkIssue()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.mFAProfCreateTemporalCODLinkIssue( testview );
        System.out.println("===== Finished mFAProfCreateTemporalCODLinkIssue ========");
    }

    @Test
    public void testExclInclOrderEntities()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mFASrc" );
        VmlTester tester = new VmlTester( testview );
        tester.testExclInclOrderEntities( testview );
        System.out.println("===== Finished testExclInclOrderEntities ========");
    }

    @Test
    public void testUpdateForeignKeys()
    {
        View         testview;
        testview = zencas.activateEmptyObjectInstance( "mStudent" );
        VmlTester tester = new VmlTester( testview );
        tester.testUpdateForeignKeys( testview );
        System.out.println("===== Finished testUpdateForeignKeys ========");
    }

    @Test
    public void testUsingJacob()
    {
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b

        //ActiveXComponent comp=new ActiveXComponent("LDAP://RootDSE");
        //ActiveXComponent comp=new ActiveXComponent("activeds");
        //ActiveXComponent comp=new ActiveXComponent("activeds.ADSI");
        //ActiveXComponent comp=new ActiveXComponent("activeds.ADsDSOObject");
        //ActiveXComponent comp=new ActiveXComponent("activeds.activeDirectory");
<<<<<<< HEAD
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
=======
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
//    @Test
    public void testDuplicateChildEntities()
    {
        Assert.assertTrue( "This is a place holder as a reminder to fix OI comparison test.  Look for 'mstudent_ac.por' in comments", false );
    }

    /**
     * Validate that we can insert multiple twins in one SQL statement.
     */
//    @Test
    public void testCreateMultipleTwins()
    {
        View view = new QualificationBuilder( zencas ).setLodDef( "mCRStdP" ).addAttribQual( "ID", 580 ).activate();
        view.cursor( "ClassRoomSession" ).createEntity( CursorPosition.LAST );
        view.cursor( "ClassRoomSession" ).createEntity( CursorPosition.LAST );
        view.commit();
        view.logObjectInstance();
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
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

<<<<<<< HEAD
	private class VmlTester extends VmlOperation
	{
		public VmlTester( TaskQualification qual )
		{
			super( qual );
		}

		public int
		mPersonProspectSaveAttribute( View ViewToWindow )
		{
		   zVIEW    mPerson = new zVIEW( );
		   zVIEW    mProspct = new zVIEW( );
		   zVIEW    mFASrc = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int      ViewCluster = 0;
		   int RESULT=0;

		   // KJS 02/13/19 - We have two objects Prospect and Person, I create mProspct.Prospect and
		   // include mPerson.Person into it. If I save mPerson first, it does the "INSERT INTO Prospect" but we
		   // lose some of the attributes of Prospect because mPerson.Prospect does not contain all of the attributes of
		   // Prospect. While in this situation, it is more proper to save mProspct first (which solves the problem),
		   // we think that it should work correctly when saving mPerson first.

		   RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		   RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		   SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		   fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );

		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
		   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );
		   if ( CheckExistenceOfEntity( mPerson, "Prospect") == 0 )
		   {
			   DeleteEntity( mPerson, "Prospect", zREPOS_NONE );
			   mPerson.commit();
		   }
		   RESULT = ActivateEmptyObjectInstance( mProspct, "mProspct", ViewToWindow, zSINGLE );
		   RESULT = CreateEntity( mProspct, "Prospect", zPOS_AFTER );
		   mProspct.cursor("Prospect").getAttribute("Type").setValue("5");
		   mProspct.cursor("Prospect").getAttribute("AcceptanceStatus").setValue("A");
		   mProspct.cursor("Prospect").getAttribute("PriorityStatus").setValue("V");
		   mProspct.cursor("Prospect").getAttribute("Status").setValue("S");
		   mProspct.cursor("Person").includeSubobject(mPerson.cursor( "Person" ), CursorPosition.NEXT);
		   if ( mPerson.cursor( "Person" ).getAttribute("Gender").getString().equals("M") )
			   mPerson.cursor("Person").getAttribute("Gender").setValue("F");
		   else
			   mPerson.cursor("Person").getAttribute("Gender").setValue("M");
		   {MutableInt mi_ViewCluster = new MutableInt( ViewCluster );
	       CreateViewCluster( ViewToWindow, mi_ViewCluster );
	       ViewCluster = mi_ViewCluster.intValue( );}
	       AddToViewCluster( ViewCluster, mPerson, 0 );
	       AddToViewCluster( ViewCluster, mProspct, 0 );
	       {MutableInt mi_Ignore = new MutableInt( 0 );
	       RESULT = CommitMultipleObjectInstances( ViewCluster, mi_Ignore );}
	       int iPropectID = mProspct.cursor("Prospect").getAttribute("ID").getInteger();
		   DropView( mProspct );
		   mProspct.setView(new QualificationBuilder( zencas )
                    .setLodDef( "mProspct" )
                    .addAttribQual( "ID", iPropectID )
                    .activate());
		   // After the save of first mPerson, then mProspct, we have lost the AcceptanceStatus and
		   // ProspectPriority because they are hidden in mPerson.Prospect.
		   if ( mProspct.cursor("Prospect").getAttribute("AcceptanceStatus").getString().equals("") &&
				mProspct.cursor("Prospect").getAttribute("PriorityStatus").getString().equals(""))
	  	        Assert.assertTrue( "Error when saving mPerson before mProspct, some attributes are null when they should have values!! ", false );

		   return 0;
		}
		public void
		IncludeExcludeMaxCardinalityIssue( View ViewToWindow )
		{
		   zVIEW    mPerson = new zVIEW( );
		   zVIEW    mPersonTST = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int RESULT=0;

		   // Two objects that have some same entities.
		   // Relink Person between two
		   // Exclude/Include in one object
		   // Try to commit second object and get a max cardinality error.
		   // It's like the "include" happens in both objects but the "exclude" is only in one object.

		   // Set up person, make sure they have values...
		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
		   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );
		   if ( mPerson.cursor("Address").checkExistenceOfEntity().isEmpty() || !mPerson.cursor("Address").setFirst("StateProvince", "MA").isSet() )
		   {
			   mPerson.cursor("Address").getAttribute("Line1").setValue("1 Main St");
			   mPerson.cursor("Address").getAttribute("City").setValue("Quincy");
			   mPerson.cursor("Address").getAttribute("StateProvince").setValue("MA");
			   mPerson.cursor("Address").getAttribute("PostalCode").setValue("02170");
		   }
		    if ( !mPerson.cursor("Address").setFirst("StateProvince", "OK").isSet() )
		    {
			   mPerson.cursor("Address").getAttribute("Line1").setValue("1 Main St");
			   mPerson.cursor("Address").getAttribute("City").setValue("Oklahoma City");
			   mPerson.cursor("Address").getAttribute("StateProvince").setValue("OK");
			   mPerson.cursor("Address").getAttribute("PostalCode").setValue("73132");
		    }
			mPerson.cursor("Address").setFirst("StateProvince", "MA");
			if ( mPerson.cursor("PrimaryAddress").checkExistenceOfEntity().isEmpty() )
			{
			   mPerson.cursor("PrimaryAddress").includeSubobject(mPerson.cursor("Address"));
			}
			else
			{
			   mPerson.cursor("PrimaryAddress").excludeEntity();
			   mPerson.cursor("PrimaryAddress").includeSubobject(mPerson.cursor("Address"));
			}
			mPerson.commit();
			mPerson.drop();

			o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
			RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
			DropView( vTempViewVar_0 );
			SetNameForView( mPerson, "mPerson", null, zLEVEL_TASK );
			int AddressID = mPerson.cursor("PrimaryAddress").getAttribute("ID").getInteger();

		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
		   RESULT = ActivateObjectInstance( mPersonTST, "mPerson2", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );
		   SetNameForView( mPersonTST, "mPersonTST", null, zLEVEL_TASK );
		   RelinkInstanceToInstance( mPersonTST, "Person", mPerson, "Person" );
		   RelinkInstanceToInstance( mPersonTST, "PrimaryAddress", mPerson, "PrimaryAddress" );

		   // Exclude Primary address
		   mPerson.cursor("PrimaryAddress").excludeEntity();
		   mPerson.cursor("Address").setFirst("StateProvince", "OK");
		   // Include new Primary Address
		   mPerson.cursor("PrimaryAddress").includeSubobject(mPerson.cursor("Address"));
		   // Save on other object causes a max cardinality because it looks like it has two PrimaryAddress.
		   mPersonTST.commit();

		}

		public int
		mFAProfPermissionIssue( View ViewToWindow )
		{
		   zVIEW    mPerson = new zVIEW( );
		   zVIEW    mFAProf = new zVIEW( );
		   zVIEW    mFASrc = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int RESULT=0;

		   // KJS 07/02/18 - I have two tests that are similar, this one does NOT use temporal entity.
		   // The other test mFAProfPermissionIssueTemporal uses CreateTemporal...
		   // I had been getting an assert error when committing.

		   // ALSO... a different error... When I do a delete of FinAidAward and then do a commit, I get a permission error
		   // because a subentity is marked as "excluded" instead of "deleted". See comment before the last commit.

		   // KJS 04/04/19 - We get a permission error on mFAProf if we update mPerson.Prospect and include mPerson into mFAProf.
		   // This is because mFAProf.Prospect, is "display only", no update permission. We think we should ignore the update on mFAProf.Prospect.


		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
		   //RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
		   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );

		   if (mPerson.cursor("Prospect").checkExistenceOfEntity().toInt() != 0)
			   mPerson.cursor("Prospect").createEntity();

		   // We get a permission error on mFAProf if we update mPerson.Prospect and include mPerson into mFAProf.
		   // We should ignore the update on mFAProf.Prospect.
		   mPerson.cursor("Prospect").getAttribute("ExpectedEntryYear").setValue("2019");


	    	//ActivateOI_FromFile( mFASrc, "mFASrc", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFASrc.json", zSINGLE );//src/test/resources/testdata/ZENCAs
		   o_fnLocalBuildQualmFASrc( ViewToWindow, vTempViewVar_0, 348 );
		   //RESULT = ActivateObjectInstance( mFASrc, "mFASrc", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
		   RESULT = ActivateObjectInstance( mFASrc, "mFASrc", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
		   DropView( vTempViewVar_0 );
	       SetNameForView( mFASrc, "mFASrc", null, zLEVEL_TASK );

		    RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", ViewToWindow, zSINGLE );
		    SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
		    RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
			RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );

			CreateEntity( mFAProf, "FinAidAward", zPOS_AFTER );
		    mFAProf.cursor("FinAidAward").getAttribute("AwardType").setValue("G");
		    mFAProf.cursor("FinAidAward").getAttribute("AwardStatus").setValue("A");
		    //mFAProf.cursor("FinAidAward").getAttribute("Amount").setValue("1000");
			RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );

		    //FinAidAwardDisbursement
			RESULT = CreateEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );

			RESULT = CreateEntity( mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("PeriodDesignator").setValue("2016-2017 Fall");  //2016-2017 Fall
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("BeginDate").setValue("20160804");
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("EndDate").setValue("20170515");
		    RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod", mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );

			//CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement" );
		    mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue("1000");
		    //AcceptSubobject( mFAProf, "FinAidAwardDisbursement" );

		    mFAProf.cursor("FinAidAward").getAttribute("Amount").setValue("1000");
	   	    RESULT = CommitObjectInstance( mFAProf );

	   	    // There is an error that when I delete FinAidAward, the entity FinAidAwardDisbursement (which has permission Delete and
	   	    // parent delete behavior is "delete") is marked as "excluded" and so we get a permission error on FinAidAwardDisbursement when
	   	    // trying to commit.
	   	    RESULT = DeleteEntity( mFAProf, "FinAidAward", zREPOS_NONE );
	   	    RESULT = CommitObjectInstance( mFAProf );
		   return 0;
		}

		public int
		mFAProfPermissionIssueTemporal( View ViewToWindow )
		{
		   zVIEW    mPerson = new zVIEW( );
		   zVIEW    mFAProf = new zVIEW( );
		   zVIEW    mFASrc = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int RESULT=0;

		   // KJS 07/02/18 - This test uses CreateTemporalEntity. Depending on when I call the CreateTemporal... I get
		   // permission errors on the commit of mFAProf. Please see comments further down.

		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
		   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );


	    	//ActivateOI_FromFile( mFASrc, "mFASrc", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFASrc.json", zSINGLE );//src/test/resources/testdata/ZENCAs
		   o_fnLocalBuildQualmFASrc( ViewToWindow, vTempViewVar_0, 348 );
		   RESULT = ActivateObjectInstance( mFASrc, "mFASrc", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
		   DropView( vTempViewVar_0 );
	       SetNameForView( mFASrc, "mFASrc", null, zLEVEL_TASK );
		   //xxxx


		    RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", ViewToWindow, zSINGLE );
		    SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
		    RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
			RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );

			CreateEntity( mFAProf, "FinAidAward", zPOS_AFTER );
		    mFAProf.cursor("FinAidAward").getAttribute("AwardType").setValue("G");
		    mFAProf.cursor("FinAidAward").getAttribute("AwardStatus").setValue("A");
			RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );

			RESULT = CreateEntity( mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("PeriodDesignator").setValue("2016-2017 Fall");  //2016-2017 Fall
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("BeginDate").setValue("20160804");
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("EndDate").setValue("20170515");

		    //FinAidAwardDisbursement
			RESULT = CreateEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
			// *********** READ COMMENT ********
            // If I uncomment this CreateTemporal... and comment out the next CreateTemporal...
			// we DO NOT get any permission error on commit.
			//CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement" );

		    RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod", mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
  	        //Assert.assertTrue( "Error linked entities FinAidAwardDisbursement/PerPeriodFinAidAwardDisbursement ", mFAProf.cursor("FinAidAwardDisbursement").isLinked( mFAProf.cursor("PerPeriodFinAidAwardDisbursement")) );

			CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement" );
		    mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue("1000");
		    AcceptSubobject( mFAProf, "FinAidAwardDisbursement" );
  	        Assert.assertTrue( "Error linked entities FinAidAwardDisbursement/PerPeriodFinAidAwardDisbursement ", mFAProf.cursor("FinAidAwardDisbursement").isLinked( mFAProf.cursor("PerPeriodFinAidAwardDisbursement")) );

		    mFAProf.cursor("FinAidAward").getAttribute("Amount").setValue("1000");
	   	    RESULT = CommitObjectInstance( mFAProf );

		   return 0;
		}


		public int
		mFAProfPermissionIssueTemporal2( View ViewToWindow )
		{
		   zVIEW    mPerson = new zVIEW( );
		   zVIEW    mFAProf = new zVIEW( );
		   zVIEW    mFASrc = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int RESULT=0;

		   // KJS 07/02/18 - This test uses CreateTemporalEntity. Depending on when I call the CreateTemporal... I get
		   // permission errors on the commit of mFAProf. Please see comments further down.

		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
		   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );


	    	//ActivateOI_FromFile( mFASrc, "mFASrc", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFASrc.json", zSINGLE );//src/test/resources/testdata/ZENCAs
		   o_fnLocalBuildQualmFASrc( ViewToWindow, vTempViewVar_0, 348 );
		   RESULT = ActivateObjectInstance( mFASrc, "mFASrc", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
		   DropView( vTempViewVar_0 );
	       SetNameForView( mFASrc, "mFASrc", null, zLEVEL_TASK );
		   //xxxx

/*
	       CreateTemporalEntity( mFAProf, "FinAidAward", zPOS_AFTER )
	       INCLUDE mFAProf.FinAidSource FROM lFASrc.FinAidSource

	       SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod
	       LOOP WHILE nDisb > 0
	          CREATE ENTITY  mFAProf.FinAidAwardDisbursement
	          // KJS 06/29/18 - *** THIS IS FOR TESTING ONLY. I get an error when we do the
	          // CommitMultipleOI's when saving the mFAStu (mFAProf) because of permission errors
	          // that I think are not correct and need to be fixed in the joe. For now, I want to try
	          // and see if this COMMIT makes it work. I have notes elsewhere on what the issue is...
	          //COMMIT mFAProf
	          INCLUDE mFAProf.FinAidAwardPeriod FROM mFAProf.PerProfileFinAidAwardPeriod
	          mFAProf.FinAidAwardDisbursement.AmountExpected =  dAmountDiv

	    CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement" )
	    AcceptSubobject( mFAProf, "FinAidAwardDisbursement" )

	       mFAProf.FinAidAwardDisbursement.OriginalAmountExpected = mFAProf.FinAidAwardDisbursement.AmountExpected

	       mFAProf.FinAidAward.LastModifiedBy = mUser.User.dFullNameLFM
	       SetAttributeFromCurrentDateTime( mFAProf, "FinAidAward", "ModifiedDateTime" )

	    nRC = AcceptSubobject( mFAProf, "FinAidAward"  )
*/

		    RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", ViewToWindow, zSINGLE );
		    SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
		    RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
			RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );

			RESULT = CreateEntity( mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("PeriodDesignator").setValue("2016-2017 Fall");  //2016-2017 Fall
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("BeginDate").setValue("20160804");
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("EndDate").setValue("20170515");

			CreateTemporalEntity( mFAProf, "FinAidAward", zPOS_AFTER );
		    mFAProf.cursor("FinAidAward").getAttribute("AwardType").setValue("G");
		    mFAProf.cursor("FinAidAward").getAttribute("AwardStatus").setValue("A");
			RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );

		    //FinAidAwardDisbursement
			RESULT = CreateEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
		    RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod",   mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );

		    boolean areLinked;
            //boolean areLinked = mFAProf.cursor("FinAidAwardDisbursement").isLinked( mFAProf.cursor("PerPeriodFinAidAwardDisbursement"));
			CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement" );
		    mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue("1000");
		    // After doing the AcceptSubobject, we lose the links to the linked entities.
		    AcceptSubobject( mFAProf, "FinAidAwardDisbursement" );

		    mFAProf.cursor("FinAidAwardDisbursement").getAttribute("OriginalAmountExpected").setValue("1000");

		    mFAProf.cursor("FinAidAward").getAttribute("Amount").setValue("1000");
		    mFAProf.cursor("FinAidAward").getAttribute("LastModifiedBy").setValue("KJS");
		    AcceptSubobject( mFAProf, "FinAidAward" );
=======
    private class VmlTester extends VmlOperation
    {
        public VmlTester( TaskQualification qual )
        {
            super( qual );
        }

        public int
        mPersonProspectSaveAttribute( View ViewToWindow )
        {
           zVIEW    mPerson = new zVIEW( );
           zVIEW    mProspct = new zVIEW( );
           zVIEW    mFASrc = new zVIEW( );
           zVIEW    lTermLST = new zVIEW( );
           zVIEW    wXferO = new zVIEW( );
           zVIEW    vTempViewVar_0 = new zVIEW( );
           int      ViewCluster = 0;
           int RESULT=0;

           // KJS 02/13/19 - We have two objects Prospect and Person, I create mProspct.Prospect and
           // include mPerson.Person into it. If I save mPerson first, it does the "INSERT INTO Prospect" but we
           // lose some of the attributes of Prospect because mPerson.Prospect does not contain all of the attributes of
           // Prospect. While in this situation, it is more proper to save mProspct first (which solves the problem),
           // we think that it should work correctly when saving mPerson first.

           RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
           RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
           SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
           fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );

           o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
           RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );
           if ( CheckExistenceOfEntity( mPerson, "Prospect") == 0 )
           {
               DeleteEntity( mPerson, "Prospect", zREPOS_NONE );
               mPerson.commit();
           }
           RESULT = ActivateEmptyObjectInstance( mProspct, "mProspct", ViewToWindow, zSINGLE );
           RESULT = CreateEntity( mProspct, "Prospect", zPOS_AFTER );
           mProspct.cursor("Prospect").getAttribute("Type").setValue("5");
           mProspct.cursor("Prospect").getAttribute("AcceptanceStatus").setValue("A");
           mProspct.cursor("Prospect").getAttribute("PriorityStatus").setValue("V");
           mProspct.cursor("Prospect").getAttribute("Status").setValue("S");
           mProspct.cursor("Person").includeSubobject(mPerson.cursor( "Person" ), CursorPosition.NEXT);
           if ( mPerson.cursor( "Person" ).getAttribute("Gender").getString().equals("M") )
               mPerson.cursor("Person").getAttribute("Gender").setValue("F");
           else
               mPerson.cursor("Person").getAttribute("Gender").setValue("M");
           {MutableInt mi_ViewCluster = new MutableInt( ViewCluster );
           CreateViewCluster( ViewToWindow, mi_ViewCluster );
           ViewCluster = mi_ViewCluster.intValue( );}
           AddToViewCluster( ViewCluster, mPerson, 0 );
           AddToViewCluster( ViewCluster, mProspct, 0 );
           {MutableInt mi_Ignore = new MutableInt( 0 );
           RESULT = CommitMultipleObjectInstances( ViewCluster, mi_Ignore );}
           int iPropectID = mProspct.cursor("Prospect").getAttribute("ID").getInteger();
           DropView( mProspct );
           mProspct.setView(new QualificationBuilder( zencas )
                    .setLodDef( "mProspct" )
                    .addAttribQual( "ID", iPropectID )
                    .activate());
           // After the save of first mPerson, then mProspct, we have lost the AcceptanceStatus and
           // ProspectPriority because they are hidden in mPerson.Prospect.
           if ( mProspct.cursor("Prospect").getAttribute("AcceptanceStatus").getString().equals("") &&
                mProspct.cursor("Prospect").getAttribute("PriorityStatus").getString().equals(""))
                  Assert.assertTrue( "Error when saving mPerson before mProspct, some attributes are null when they should have values!! ", false );

           return 0;
        }
        public void
        IncludeExcludeMaxCardinalityIssue( View ViewToWindow )
        {
           zVIEW    mPerson = new zVIEW( );
           zVIEW    mPersonTST = new zVIEW( );
           zVIEW    vTempViewVar_0 = new zVIEW( );
           int RESULT=0;

           // Two objects that have some same entities.
           // Relink Person between two
           // Exclude/Include in one object
           // Try to commit second object and get a max cardinality error.
           // It's like the "include" happens in both objects but the "exclude" is only in one object.

           // Set up person, make sure they have values...
           o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
           RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );
           if ( mPerson.cursor("Address").checkExistenceOfEntity().isEmpty() || !mPerson.cursor("Address").setFirst("StateProvince", "MA").isSet() )
           {
               mPerson.cursor("Address").getAttribute("Line1").setValue("1 Main St");
               mPerson.cursor("Address").getAttribute("City").setValue("Quincy");
               mPerson.cursor("Address").getAttribute("StateProvince").setValue("MA");
               mPerson.cursor("Address").getAttribute("PostalCode").setValue("02170");
           }
            if ( !mPerson.cursor("Address").setFirst("StateProvince", "OK").isSet() )
            {
               mPerson.cursor("Address").getAttribute("Line1").setValue("1 Main St");
               mPerson.cursor("Address").getAttribute("City").setValue("Oklahoma City");
               mPerson.cursor("Address").getAttribute("StateProvince").setValue("OK");
               mPerson.cursor("Address").getAttribute("PostalCode").setValue("73132");
            }
            mPerson.cursor("Address").setFirst("StateProvince", "MA");
            if ( mPerson.cursor("PrimaryAddress").checkExistenceOfEntity().isEmpty() )
            {
               mPerson.cursor("PrimaryAddress").includeSubobject(mPerson.cursor("Address"));
            }
            else
            {
               mPerson.cursor("PrimaryAddress").excludeEntity();
               mPerson.cursor("PrimaryAddress").includeSubobject(mPerson.cursor("Address"));
            }
            mPerson.commit();
            mPerson.drop();

            o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
            RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
            DropView( vTempViewVar_0 );
            SetNameForView( mPerson, "mPerson", null, zLEVEL_TASK );
            int AddressID = mPerson.cursor("PrimaryAddress").getAttribute("ID").getInteger();

           o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
           RESULT = ActivateObjectInstance( mPersonTST, "mPerson2", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );
           SetNameForView( mPersonTST, "mPersonTST", null, zLEVEL_TASK );
           RelinkInstanceToInstance( mPersonTST, "Person", mPerson, "Person" );
           RelinkInstanceToInstance( mPersonTST, "PrimaryAddress", mPerson, "PrimaryAddress" );

           // Exclude Primary address
           mPerson.cursor("PrimaryAddress").excludeEntity();
           mPerson.cursor("Address").setFirst("StateProvince", "OK");
           // Include new Primary Address
           mPerson.cursor("PrimaryAddress").includeSubobject(mPerson.cursor("Address"));
           // Save on other object causes a max cardinality because it looks like it has two PrimaryAddress.
           mPersonTST.commit();

        }

        public int
        mFAProfPermissionIssue( View ViewToWindow )
        {
           zVIEW    mPerson = new zVIEW( );
           zVIEW    mFAProf = new zVIEW( );
           zVIEW    mFASrc = new zVIEW( );
           zVIEW    lTermLST = new zVIEW( );
           zVIEW    wXferO = new zVIEW( );
           zVIEW    vTempViewVar_0 = new zVIEW( );
           int RESULT=0;

           // KJS 07/02/18 - I have two tests that are similar, this one does NOT use temporal entity.
           // The other test mFAProfPermissionIssueTemporal uses CreateTemporal...
           // I had been getting an assert error when committing.

           // ALSO... a different error... When I do a delete of FinAidAward and then do a commit, I get a permission error
           // because a subentity is marked as "excluded" instead of "deleted". See comment before the last commit.

           // KJS 04/04/19 - We get a permission error on mFAProf if we update mPerson.Prospect and include mPerson into mFAProf.
           // This is because mFAProf.Prospect, is "display only", no update permission. We think we should ignore the update on mFAProf.Prospect.


            RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
            RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
            SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
            fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
            RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            DropView( vTempViewVar_0 );
            SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
            OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

           o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
           //RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
           RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );

           if (mPerson.cursor("Prospect").checkExistenceOfEntity().toInt() != 0)
               mPerson.cursor("Prospect").createEntity();

           // We get a permission error on mFAProf if we update mPerson.Prospect and include mPerson into mFAProf.
           // We should ignore the update on mFAProf.Prospect.
           mPerson.cursor("Prospect").getAttribute("ExpectedEntryYear").setValue("2019");


            //ActivateOI_FromFile( mFASrc, "mFASrc", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFASrc.json", zSINGLE );//src/test/resources/testdata/ZENCAs
           o_fnLocalBuildQualmFASrc( ViewToWindow, vTempViewVar_0, 348 );
           //RESULT = ActivateObjectInstance( mFASrc, "mFASrc", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
           RESULT = ActivateObjectInstance( mFASrc, "mFASrc", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
           DropView( vTempViewVar_0 );
           SetNameForView( mFASrc, "mFASrc", null, zLEVEL_TASK );

            RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", ViewToWindow, zSINGLE );
            SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
            RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );

            CreateEntity( mFAProf, "FinAidAward", zPOS_AFTER );
            mFAProf.cursor("FinAidAward").getAttribute("AwardType").setValue("G");
            mFAProf.cursor("FinAidAward").getAttribute("AwardStatus").setValue("A");
            //mFAProf.cursor("FinAidAward").getAttribute("Amount").setValue("1000");
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );

            //FinAidAwardDisbursement
            RESULT = CreateEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );

            RESULT = CreateEntity( mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("PeriodDesignator").setValue("2016-2017 Fall");  //2016-2017 Fall
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("BeginDate").setValue("20160804");
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("EndDate").setValue("20170515");
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod", mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );

            //CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement" );
            mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue("1000");
            //AcceptSubobject( mFAProf, "FinAidAwardDisbursement" );

            mFAProf.cursor("FinAidAward").getAttribute("Amount").setValue("1000");
               RESULT = CommitObjectInstance( mFAProf );

               // There is an error that when I delete FinAidAward, the entity FinAidAwardDisbursement (which has permission Delete and
               // parent delete behavior is "delete") is marked as "excluded" and so we get a permission error on FinAidAwardDisbursement when
               // trying to commit.
               RESULT = DeleteEntity( mFAProf, "FinAidAward", zREPOS_NONE );
               RESULT = CommitObjectInstance( mFAProf );
           return 0;
        }

        public int
        mFAProfPermissionIssueTemporal( View ViewToWindow )
        {
           zVIEW    mPerson = new zVIEW( );
           zVIEW    mFAProf = new zVIEW( );
           zVIEW    mFASrc = new zVIEW( );
           zVIEW    lTermLST = new zVIEW( );
           zVIEW    wXferO = new zVIEW( );
           zVIEW    vTempViewVar_0 = new zVIEW( );
           int RESULT=0;

           // KJS 07/02/18 - This test uses CreateTemporalEntity. Depending on when I call the CreateTemporal... I get
           // permission errors on the commit of mFAProf. Please see comments further down.

            RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
            RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
            SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
            fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
            RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            DropView( vTempViewVar_0 );
            SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
            OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

           o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
           RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );


            //ActivateOI_FromFile( mFASrc, "mFASrc", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFASrc.json", zSINGLE );//src/test/resources/testdata/ZENCAs
           o_fnLocalBuildQualmFASrc( ViewToWindow, vTempViewVar_0, 348 );
           RESULT = ActivateObjectInstance( mFASrc, "mFASrc", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
           DropView( vTempViewVar_0 );
           SetNameForView( mFASrc, "mFASrc", null, zLEVEL_TASK );
           //xxxx


            RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", ViewToWindow, zSINGLE );
            SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
            RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );

            CreateEntity( mFAProf, "FinAidAward", zPOS_AFTER );
            mFAProf.cursor("FinAidAward").getAttribute("AwardType").setValue("G");
            mFAProf.cursor("FinAidAward").getAttribute("AwardStatus").setValue("A");
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );

            RESULT = CreateEntity( mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("PeriodDesignator").setValue("2016-2017 Fall");  //2016-2017 Fall
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("BeginDate").setValue("20160804");
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("EndDate").setValue("20170515");

            //FinAidAwardDisbursement
            RESULT = CreateEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
            // *********** READ COMMENT ********
            // If I uncomment this CreateTemporal... and comment out the next CreateTemporal...
            // we DO NOT get any permission error on commit.
            //CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement" );

            RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod", mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
              //Assert.assertTrue( "Error linked entities FinAidAwardDisbursement/PerPeriodFinAidAwardDisbursement ", mFAProf.cursor("FinAidAwardDisbursement").isLinked( mFAProf.cursor("PerPeriodFinAidAwardDisbursement")) );

            CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement" );
            mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue("1000");
            AcceptSubobject( mFAProf, "FinAidAwardDisbursement" );
              Assert.assertTrue( "Error linked entities FinAidAwardDisbursement/PerPeriodFinAidAwardDisbursement ", mFAProf.cursor("FinAidAwardDisbursement").isLinked( mFAProf.cursor("PerPeriodFinAidAwardDisbursement")) );

            mFAProf.cursor("FinAidAward").getAttribute("Amount").setValue("1000");
               RESULT = CommitObjectInstance( mFAProf );

           return 0;
        }


        public int
        mFAProfPermissionIssueTemporal2( View ViewToWindow )
        {
           zVIEW    mPerson = new zVIEW( );
           zVIEW    mFAProf = new zVIEW( );
           zVIEW    mFASrc = new zVIEW( );
           zVIEW    lTermLST = new zVIEW( );
           zVIEW    wXferO = new zVIEW( );
           zVIEW    vTempViewVar_0 = new zVIEW( );
           int RESULT=0;

           // KJS 07/02/18 - This test uses CreateTemporalEntity. Depending on when I call the CreateTemporal... I get
           // permission errors on the commit of mFAProf. Please see comments further down.

            RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
            RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
            SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
            fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
            RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            DropView( vTempViewVar_0 );
            SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
            OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

           o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
           RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );


            //ActivateOI_FromFile( mFASrc, "mFASrc", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFASrc.json", zSINGLE );//src/test/resources/testdata/ZENCAs
           o_fnLocalBuildQualmFASrc( ViewToWindow, vTempViewVar_0, 348 );
           RESULT = ActivateObjectInstance( mFASrc, "mFASrc", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
           DropView( vTempViewVar_0 );
           SetNameForView( mFASrc, "mFASrc", null, zLEVEL_TASK );
           //xxxx

/*
           CreateTemporalEntity( mFAProf, "FinAidAward", zPOS_AFTER )
           INCLUDE mFAProf.FinAidSource FROM lFASrc.FinAidSource

           SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod
           LOOP WHILE nDisb > 0
              CREATE ENTITY  mFAProf.FinAidAwardDisbursement
              // KJS 06/29/18 - *** THIS IS FOR TESTING ONLY. I get an error when we do the
              // CommitMultipleOI's when saving the mFAStu (mFAProf) because of permission errors
              // that I think are not correct and need to be fixed in the joe. For now, I want to try
              // and see if this COMMIT makes it work. I have notes elsewhere on what the issue is...
              //COMMIT mFAProf
              INCLUDE mFAProf.FinAidAwardPeriod FROM mFAProf.PerProfileFinAidAwardPeriod
              mFAProf.FinAidAwardDisbursement.AmountExpected =  dAmountDiv

        CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement" )
        AcceptSubobject( mFAProf, "FinAidAwardDisbursement" )

           mFAProf.FinAidAwardDisbursement.OriginalAmountExpected = mFAProf.FinAidAwardDisbursement.AmountExpected

           mFAProf.FinAidAward.LastModifiedBy = mUser.User.dFullNameLFM
           SetAttributeFromCurrentDateTime( mFAProf, "FinAidAward", "ModifiedDateTime" )

        nRC = AcceptSubobject( mFAProf, "FinAidAward"  )
*/

            RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", ViewToWindow, zSINGLE );
            SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
            RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );

            RESULT = CreateEntity( mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("PeriodDesignator").setValue("2016-2017 Fall");  //2016-2017 Fall
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("BeginDate").setValue("20160804");
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("EndDate").setValue("20170515");

            CreateTemporalEntity( mFAProf, "FinAidAward", zPOS_AFTER );
            mFAProf.cursor("FinAidAward").getAttribute("AwardType").setValue("G");
            mFAProf.cursor("FinAidAward").getAttribute("AwardStatus").setValue("A");
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );

            //FinAidAwardDisbursement
            RESULT = CreateEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod",   mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );

            boolean areLinked;
            //boolean areLinked = mFAProf.cursor("FinAidAwardDisbursement").isLinked( mFAProf.cursor("PerPeriodFinAidAwardDisbursement"));
            CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement" );
            mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue("1000");
            // After doing the AcceptSubobject, we lose the links to the linked entities.
            AcceptSubobject( mFAProf, "FinAidAwardDisbursement" );

            mFAProf.cursor("FinAidAwardDisbursement").getAttribute("OriginalAmountExpected").setValue("1000");

            mFAProf.cursor("FinAidAward").getAttribute("Amount").setValue("1000");
            mFAProf.cursor("FinAidAward").getAttribute("LastModifiedBy").setValue("KJS");
            AcceptSubobject( mFAProf, "FinAidAward" );
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b

            areLinked = mFAProf.cursor("FinAidAwardDisbursement").isLinked( mFAProf.cursor("PerPeriodFinAidAwardDisbursement"));
            Assert.assertTrue( "Error linked entities FinAidAwardDisbursement/PerPeriodFinAidAwardDisbursement ", mFAProf.cursor("FinAidAwardDisbursement").isLinked( mFAProf.cursor("PerPeriodFinAidAwardDisbursement")) );

<<<<<<< HEAD
            //	   	    RESULT = CommitObjectInstance( mFAProf );

	   	    // We are getting an error when saving because we set an attribute in FinAidAwardDisbursement
	   	    // that is not in the linked attribute PerPeriodFinAidAwardDisbursement.
	   	    // Thought it might have to do with temporal entities, but I still get error even when taking
	   	    // it out.
			//CreateTemporalSubobjectVersion( mFAProf, "FinAidAward" );
			//CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement" );
		    mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue("1000");
		    mFAProf.cursor("FinAidAwardDisbursement").getAttribute("OriginalAmountExpected").setValue("1000");
		    //AcceptSubobject( mFAProf, "FinAidAwardDisbursement" );
		    //AcceptSubobject( mFAProf, "FinAidAward" );
	   	    //RESULT = CommitObjectInstance( mFAProf );

		   return 0;
		}

		public int
		mFAProfTemporalIssue3( View ViewToWindow )
		{
		   zVIEW    mPerson = new zVIEW( );
		   zVIEW    mFAProf = new zVIEW( );
		   zVIEW    mFASrc = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int RESULT=0;

		   // KJS 09/18/19 - Getting the following error:
		   // com.quinsoft.zeidon.TemporalEntityException: Entity has children that are unaccepted version roots
		   // EntityDef  = ZENCAs.mFAProf.FinAidAward
		   // If we
		   // 1. createTemporalEntity on root FinAidAward then
		   // 2. createTemporalEntity on child FinAidAwardDisbursement
		   // 3. acceptSubobject( FinAidAwardDisbursement )
		   // 4. acceptSubobject( FinAidAward )
		   // Works without error
		   // If we
		   // 1. createTemporalSubobjectVersion on root FinAidAward then
		   // 2. createTemporalEntity on child FinAidAwardDisbursement
		   // 3. acceptSubobject( FinAidAwardDisbursement )
		   // 4. acceptSubobject( FinAidAward )
		   // Receive Error
		   //
		   // In the first instance of acceptSubobject( FinAidAwardDisbursement ) (this is after we did a createTemporal on FinAidAward), we set all
		   // child entities to parent version status UNACCEPTED_ENTITY
		   // In the second instance of acceptSubobject( FinAidAwardDisbursement ), we set all child entities to parent version status UNACCEPTED_ROOT, which
		   // then throws the exception when doing acceptSubobject( FinAidAward ).


		    // Set up code.
		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
		   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );


		   o_fnLocalBuildQualmFASrc( ViewToWindow, vTempViewVar_0, 348 );
		   RESULT = ActivateObjectInstance( mFASrc, "mFASrc", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
		   DropView( vTempViewVar_0 );
	       SetNameForView( mFASrc, "mFASrc", null, zLEVEL_TASK );
		   //xxxx

		    RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", ViewToWindow, zSINGLE );
		    SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
		    RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
			RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );

			RESULT = CreateEntity( mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("PeriodDesignator").setValue("2016-2017 Fall");  //2016-2017 Fall
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("BeginDate").setValue("20160804");
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("EndDate").setValue("20170515");
		    // End of set up code...


		    // This works
			CreateTemporalEntity( mFAProf, "FinAidAward", zPOS_AFTER );
		    mFAProf.cursor("FinAidAward").getAttribute("AwardType").setValue("G");
		    mFAProf.cursor("FinAidAward").getAttribute("AwardStatus").setValue("A");
			RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );

			CreateTemporalEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
		    RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod",   mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
		    mFAProf.cursor("FinAidAwardDisbursement").acceptSubobject();
		    mFAProf.cursor("FinAidAward").acceptSubobject();

		    // This does not work.
		    CreateTemporalSubobjectVersion( mFAProf, "FinAidAward" );
			CreateTemporalEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
		    RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod",   mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
		    mFAProf.cursor("FinAidAwardDisbursement").acceptSubobject();
		    mFAProf.cursor("FinAidAward").acceptSubobject();

		   return 0;
		}

		public int
		mFAProfTemporalPerProfileFinAidAwardPeriodPathTest( View ViewToWindow )
		{
		   zVIEW    mPerson = new zVIEW( );
		   zVIEW    mFAProf = new zVIEW( );
		   zVIEW    mFASrc = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int RESULT=0;

		   // KJS 09/18/19 - at the moment this gives the same error as the test mFAProfTemporalIssue3,
		   // but when that test gets fixed, I want to make sure that the values in PerPeriodFinAidAwardDisbursement, down the
		   // PerProfileFinAidAwardPeriod path show the same values as the original FinAidAwardDisbursement entity under FinAidAward.
		   // The PerProfileFinAidAwardPeriod path is the "included" path.

		    // Set up code.
		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
		   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );


		   o_fnLocalBuildQualmFASrc( ViewToWindow, vTempViewVar_0, 348 );
		   RESULT = ActivateObjectInstance( mFASrc, "mFASrc", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
		   DropView( vTempViewVar_0 );
	       SetNameForView( mFASrc, "mFASrc", null, zLEVEL_TASK );

		    RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", ViewToWindow, zSINGLE );
		    SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
		    RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
			RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );

			RESULT = CreateEntity( mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("PeriodDesignator").setValue("2016-2017 Fall");  //2016-2017 Fall
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("BeginDate").setValue("20160804");
		    mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("EndDate").setValue("20170515");
		    // End of set up code...

		    // This works
			CreateTemporalEntity( mFAProf, "FinAidAward", zPOS_AFTER );
		    mFAProf.cursor("FinAidAward").getAttribute("AwardType").setValue("G");
		    mFAProf.cursor("FinAidAward").getAttribute("AwardStatus").setValue("A");
			RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );

			CreateTemporalEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
		    RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod",   mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
		    mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 500) ;
		    mFAProf.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 500) ;
		    mFAProf.cursor("FinAidAwardDisbursement").acceptSubobject();
		    mFAProf.cursor("FinAidAward").acceptSubobject();

	        RESULT= mFAProf.cursor("FinAidAwardDisbursement").setFirst( "Amount", 500 ).toInt();
	        RESULT= mFAProf.cursor("PerPeriodFinAidAwardDisbursement").setFirst( "Amount", 500 ).toInt();
 		    Assert.assertEquals("PerPeriodFinAidAwardDisbursement entity doesn't exist for amount 500.", 0, RESULT, 0.0);

 		    // Another test
		    CreateTemporalSubobjectVersion( mFAProf, "FinAidAward" );
		    CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement");
		    mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 300) ;
		    mFAProf.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 300) ;
		    mFAProf.cursor("FinAidAwardDisbursement").acceptSubobject();
		    mFAProf.cursor("FinAidAward").acceptSubobject();
	        RESULT= mFAProf.cursor("FinAidAwardDisbursement").setFirst( "Amount", 300 ).toInt();
	        RESULT= mFAProf.cursor("PerPeriodFinAidAwardDisbursement").setFirst( "Amount", 300 ).toInt();
 		    Assert.assertEquals("PerPeriodFinAidAwardDisbursement entity doesn't exist for amount 300.", 0, RESULT, 0.0);

 		    // This does not work.
		    CreateTemporalSubobjectVersion( mFAProf, "FinAidAward" );
			CreateTemporalEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
		    RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod",   mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
		    mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 100) ;
		    mFAProf.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 100) ;
		    mFAProf.cursor("FinAidAwardDisbursement").acceptSubobject();
		    mFAProf.cursor("FinAidAward").acceptSubobject();

	        RESULT= mFAProf.cursor("FinAidAwardDisbursement").setFirst( "Amount", 100 ).toInt();
	        RESULT= mFAProf.cursor("PerPeriodFinAidAwardDisbursement").setFirst( "Amount", 100 ).toInt();
 		    Assert.assertEquals("PerPeriodFinAidAwardDisbursement entity doesn't exist for amount 100.", 0, RESULT, 0.0);

		   return 0;
		}


		public int
		mFAProfTemporalFinAidAward( View ViewToWindow )
		{
		   zVIEW    mPerson = new zVIEW( );
		   zVIEW    mFAProf = new zVIEW( );
		   zVIEW    mFASrc = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int RESULT=0;

		   // KJS 03/23/21
		   // Create Temporal Subobject for FinAidAward
		   // Include FinAidSource
		   // Add FinAidAwardDisbursements
		   // Accept FinAidAward
		   // FinAidSource is missing all values (and mFASrc doesn't look right as well).

		    // Set up code.
		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
		   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );


		   o_fnLocalBuildQualmFASrc( ViewToWindow, vTempViewVar_0, 348 );
		   RESULT = ActivateObjectInstance( mFASrc, "mFASrc", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
		   DropView( vTempViewVar_0 );
	       SetNameForView( mFASrc, "mFASrc", null, zLEVEL_TASK );

		    RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", ViewToWindow, zSINGLE );
		    SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
		    RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
			RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );

			CreateTemporalEntity( mFAProf, "FinAidAward", zPOS_AFTER );
		    mFAProf.cursor("FinAidAward").getAttribute("AwardType").setValue("G");
		    mFAProf.cursor("FinAidAward").getAttribute("AwardStatus").setValue("A");
			RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );

		    RESULT = CreateEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
		    mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 500) ;
		    mFAProf.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 500) ;
		    mFAProf.cursor("FinAidAward").acceptSubobject();
	        Assert.assertTrue( "FinAidSource entity exists but is empty after acceptSubobject", mFAProf.cursor("FinAidSource").getAttribute("ID").getString().length() > 0 );

		    return 0;

		}

		public int
		mFAProfTemporalLinkIssue( View ViewToWindow )
		{
		   zVIEW    mPerson = new zVIEW( );
		   zVIEW    mFAProf = new zVIEW( );
		   zVIEW    lFACOAYr = new zVIEW( );
		   zVIEW    mFACOAYr = new zVIEW( );
		   zVIEW    mYear = new zVIEW( );
		   zVIEW    lFANdProLST = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   zVIEW    vTempViewVar_1 = new zVIEW( );
		   int RESULT=0;
		   int nRC=0;

		   // KJS 05/13/19 - This test creates a TemporalSubobject for the root entity FinAidProfile. Then we create/delete/create the
		   // sub entity FinAidCOAItemAssigned. After the delete/create, we try to do a create TemporalSubobject on FinAidCOAItemAssigned and we
		   // receive a TemporalEntityException: Attempting to create a temporal subobject for an entity that has a child entity linked to another
		   // temporal entity.
		   // We do not get an error unless we've created the temporal on the root FinAidProfile.

		   RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		   RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		   SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );

		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
		   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );


		   RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", ViewToWindow, zSINGLE );
		   SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
		   RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
		   RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );
	   	   RESULT = CommitObjectInstance( mFAProf );

	   	   CreateTemporalSubobjectVersion( mFAProf, "FinAidProfile" );

	   	   //:ACTIVATE  mYear  WHERE mYear.CollegeYear.ID = 44
	   	   o_fnLocalBuildQualmYear( ViewToWindow, vTempViewVar_1 );
	   	   RESULT = ActivateObjectInstance( mYear, "mYear", ViewToWindow, vTempViewVar_1, zSINGLE );
	   	   DropView( vTempViewVar_1 );
	   	   SetNameForView( mYear, "mYear", null, zLEVEL_TASK );

	   	   // This next piece of code is only to make sure that we have some FInAidCOAItemForYear entitites exist so we can
	   	   // use them for creating mFAProf.FinAidCOAItemAssigned

	   	   //:ACTIVATE  lFANdProLST Multiple WHERE lFANdProLST.AdministrativeDivision.ID = 1 AND lFANdProLST.FinAidCOA.ID = 2
	   	   //:         RESTRICTING lFANdProLST.FinAidCOAItemForYear TO lFANdProLST.CollegeYear.ID = 44
	   	   o_fnLocalBuildQuallFANdProLST2( ViewToWindow, vTempViewVar_0 );
	   	   RESULT = ActivateObjectInstance( lFANdProLST, "mFANdPro", ViewToWindow, vTempViewVar_0, zMULTIPLE );
	   	   DropView( vTempViewVar_0 );
	   	   SetNameForView( lFANdProLST, "lFANdProLST", null, zLEVEL_TASK );
	   	   RESULT = SetCursorFirstEntity( lFANdProLST, "FinAidCOAItem", "" );
	   	   while ( RESULT > zCURSOR_UNCHANGED )
	   	   {
	   	      if ( CheckExistenceOfEntity( lFANdProLST, "FinAidCOAItemForYear" ) != 0 )
	   	      {
	   	         RESULT = ActivateEmptyObjectInstance( mFACOAYr, "mFACOAYr", ViewToWindow, zSINGLE );
	   	         SetNameForView( mFACOAYr, "mFACOAYr", null, zLEVEL_TASK );
	   	         RESULT = CreateEntity( mFACOAYr, "FinAidCOAItemForYear", zPOS_AFTER );
	   	         SetAttributeFromInteger( mFACOAYr, "FinAidCOAItemForYear", "RevenueAmount", 1500 );
	   	         SetAttributeFromInteger( mFACOAYr, "FinAidCOAItemForYear", "FirstTermRevenueAmount", 500 );
	   	         SetAttributeFromInteger( mFACOAYr, "FinAidCOAItemForYear", "SecondTermRevenueAmount", 500 );
	   	         SetAttributeFromInteger( mFACOAYr, "FinAidCOAItemForYear", "ThirdTermRevenueAmount", 500 );
	   	         RESULT = IncludeSubobjectFromSubobject( mFACOAYr, "FinAidCOAItem", lFANdProLST, "FinAidCOAItem", zPOS_AFTER );
	   	         RESULT = IncludeSubobjectFromSubobject( mFACOAYr, "CollegeYear", mYear, "CollegeYear", zPOS_AFTER );
	   	         RESULT = CommitObjectInstance( mFACOAYr );
	   	         DropObjectInstance( mFACOAYr );
	   	      }

	   	      RESULT = SetCursorNextEntity( lFANdProLST, "FinAidCOAItem", "" );
	   	   }

 	       DropObjectInstance( lFANdProLST );
 	       // END of making sure FinAidCOAItemForYear exists

 	       // CREATE FinAidCOAItemAssigned
	   	   o_fnLocalBuildQuallFANdProLST2( ViewToWindow, vTempViewVar_0 );
	   	   RESULT = ActivateObjectInstance( lFANdProLST, "mFANdPro", ViewToWindow, vTempViewVar_0, zMULTIPLE );
	   	   DropView( vTempViewVar_0 );
	   	   SetNameForView( lFANdProLST, "lFANdProLST", null, zLEVEL_TASK );
	   	   RESULT = SetCursorFirstEntity( lFANdProLST, "FinAidCOAItem", "" );
	   	   while ( RESULT > zCURSOR_UNCHANGED )
	   	   {
	   	      RESULT = CreateEntity( mFAProf, "FinAidCOAItemAssigned", zPOS_AFTER );
	   	      RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidCOAItemForYear", lFANdProLST, "FinAidCOAItemForYear", zPOS_AFTER );
	   	      SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "RevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "RevenueAmount" );
	   	      SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "FirstTermRevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "FirstTermRevenueAmount" );
	   	      SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "SecondTermRevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "SecondTermRevenueAmount" );
	   	      SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "ThirdTermRevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "ThirdTermRevenueAmount" );
	   	      RESULT = SetCursorNextEntity( lFANdProLST, "FinAidCOAItem", "" );
	   	   }

 	       // DELETE FinAidCOAItemAssigned
	   	   RESULT = SetCursorFirstEntity( mFAProf, "FinAidCOAItemAssigned", "" );
	   	   while ( RESULT > zCURSOR_UNCHANGED )
	   	   {
   	          RESULT = DeleteEntity( mFAProf, "FinAidCOAItemAssigned", zREPOS_NONE );
	   	      RESULT = SetCursorNextEntity( mFAProf, "FinAidCOAItemAssigned", "" );
	   	   }

 	       // CREATE FinAidCOAItemAssigned again
	   	   RESULT = SetCursorFirstEntity( lFANdProLST, "FinAidCOAItem", "" );
	   	   while ( RESULT > zCURSOR_UNCHANGED )
	   	   {
	   	      RESULT = CreateEntity( mFAProf, "FinAidCOAItemAssigned", zPOS_AFTER );
	   	      RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidCOAItemForYear", lFANdProLST, "FinAidCOAItemForYear", zPOS_AFTER );
	   	      SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "RevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "RevenueAmount" );
	   	      SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "FirstTermRevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "FirstTermRevenueAmount" );
	   	      SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "SecondTermRevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "SecondTermRevenueAmount" );
	   	      SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "ThirdTermRevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "ThirdTermRevenueAmount" );
	   	      RESULT = SetCursorNextEntity( lFANdProLST, "FinAidCOAItem", "" );
	   	   }

	   	   // We receive a TemporalEntityExcption on this.
	   	   // We only get this when we've done a CreateTemporalSubobjectVersion( mFAProf, "FinAidProfile" )
			CreateTemporalSubobjectVersion( mFAProf, "FinAidCOAItemAssigned" );

		   return 0;
		}

		public int
		mFAProfCreateTemporalCODLinkIssue( View ViewToWindow )
		{
		   zVIEW    mFAProf = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );

		   // KJS 10/06/21 
		   // One the create temporal of "COD_Disbursement", we get the following error:
		   // TemporalEntityException: Attempting to create a temporal subobject for an entity that has a child entity linked to another temporal entity.
		   // If we take out either the create temporal of "FinAidAward" or "FinAidAwardDisbursement", it works without error.
		   
		   o_fnLocalBuildmFAProf2( ViewToWindow, vTempViewVar_0, 23496 ); //348  23496
		   ActivateObjectInstance( mFAProf, "mFAProf", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );
	           SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
	       
	       // If COD_Disbursement doesn't exist, create one and save for our test purposes.
	       if ( !mFAProf.cursor("COD_Disbursement").checkExistenceOfEntity().isSet() )
	       {
	    	   mFAProf.cursor("COD_Disbursement").createEntity();
	    	   mFAProf.cursor("COD_Disbursement").getAttribute("SequenceNumber").setValue("1");
	    	   mFAProf.cursor("COD_Disbursement").getAttribute("COD_ResponseCode").setValue("A");
	    	   mFAProf.commit();
	       }
		    CreateTemporalSubobjectVersion( mFAProf, "FinAidAward" );
		    CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement");
		    CreateTemporalSubobjectVersion( mFAProf, "COD_Disbursement");
	       
		   return 0;
		}


		public int
		CreateTemporalDerivedEntityWorkAttributeIssue( View ViewToWindow )
		{
		   zVIEW    mPerson = new zVIEW( );
		   zVIEW    mFAProf = new zVIEW( );
		   zVIEW    mFASrc = new zVIEW( );
		   zVIEW    mClass = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int RESULT=0;

		   // KJS 09/16/19
		   // Issue: After a CreateTemporalSubobjectVersion, derived entity work attributes are blank.
		   // We create derived entity GradeEnrollment from entity Enrollment and set a work attribute (GradeEnrollment.wEnteredGrade).
		   // After CreateTemporalSubobjectVersion on the root entity Class the work attribute in GradeEnrollment is blank.
		   // Work attributes in the original entity Enrollment are fine.
		   // ER attributes in the derived entity GradeEnrollment are fine.

		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

			int testClass = 2560;

	       o_fnLocalBuildmClass( ViewToWindow, vTempViewVar_0, testClass );

	       RESULT = ActivateObjectInstance( mClass, "mClass", ViewToWindow, vTempViewVar_0, zSINGLE );
	       DropView( vTempViewVar_0 );
		   SetNameForView( mClass, "mClass", null, zLEVEL_TASK );

		   RESULT = SetCursorFirstEntity( mClass, "GradeEnrollment", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      RESULT = ExcludeEntity( mClass, "GradeEnrollment", zREPOS_NONE );
		      RESULT = SetCursorNextEntity( mClass, "GradeEnrollment", "" );
		   }

		   RESULT = SetCursorFirstEntity( mClass, "Enrollment", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      SetAttributeFromAttribute( mClass, "Enrollment", "wOriginalFinalGrade", mClass, "Enrollment", "FinalGrade" );
		      if ( CompareAttributeToString( mClass, "Enrollment", "Status", "T" ) == 0 || CompareAttributeToString( mClass, "Enrollment", "Status", "C" ) == 0 )
		      {
		    	  RESULT = IncludeSubobjectFromSubobject( mClass, "GradeEnrollment", mClass, "Enrollment", zPOS_AFTER );
		    	  SetAttributeFromAttribute( mClass, "GradeEnrollment", "wEnteredGrade", mClass, "Enrollment", "FinalGrade" );
		      }

		      RESULT = SetCursorNextEntity( mClass, "Enrollment", "" );
		   }
		   SetAttributeFromString( mClass, "Class", "wEnterGradesType", "F" );
		   RESULT = SetCursorFirstEntity( mClass, "GradeEnrollment", "" );
		   String grade = mClass.cursor("GradeEnrollment").getAttribute("wEnteredGrade").getString();
           Assert.assertTrue( "Before ", ! grade.equals("") );
		   CreateTemporalSubobjectVersion( mClass, "Class" );
           Assert.assertTrue( "After ", !mClass.cursor("GradeEnrollment").getAttribute("wEnteredGrade").getString().equals("") );

		   return 0;
		}
=======
            //               RESULT = CommitObjectInstance( mFAProf );

               // We are getting an error when saving because we set an attribute in FinAidAwardDisbursement
               // that is not in the linked attribute PerPeriodFinAidAwardDisbursement.
               // Thought it might have to do with temporal entities, but I still get error even when taking
               // it out.
            //CreateTemporalSubobjectVersion( mFAProf, "FinAidAward" );
            //CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement" );
            mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue("1000");
            mFAProf.cursor("FinAidAwardDisbursement").getAttribute("OriginalAmountExpected").setValue("1000");
            //AcceptSubobject( mFAProf, "FinAidAwardDisbursement" );
            //AcceptSubobject( mFAProf, "FinAidAward" );
               //RESULT = CommitObjectInstance( mFAProf );

           return 0;
        }

        public int
        mFAProfTemporalIssue3( View ViewToWindow )
        {
           zVIEW    mPerson = new zVIEW( );
           zVIEW    mFAProf = new zVIEW( );
           zVIEW    mFASrc = new zVIEW( );
           zVIEW    lTermLST = new zVIEW( );
           zVIEW    wXferO = new zVIEW( );
           zVIEW    vTempViewVar_0 = new zVIEW( );
           int RESULT=0;

           // KJS 09/18/19 - Getting the following error:
           // com.quinsoft.zeidon.TemporalEntityException: Entity has children that are unaccepted version roots
           // EntityDef  = ZENCAs.mFAProf.FinAidAward
           // If we
           // 1. createTemporalEntity on root FinAidAward then
           // 2. createTemporalEntity on child FinAidAwardDisbursement
           // 3. acceptSubobject( FinAidAwardDisbursement )
           // 4. acceptSubobject( FinAidAward )
           // Works without error
           // If we
           // 1. createTemporalSubobjectVersion on root FinAidAward then
           // 2. createTemporalEntity on child FinAidAwardDisbursement
           // 3. acceptSubobject( FinAidAwardDisbursement )
           // 4. acceptSubobject( FinAidAward )
           // Receive Error
           //
           // In the first instance of acceptSubobject( FinAidAwardDisbursement ) (this is after we did a createTemporal on FinAidAward), we set all
           // child entities to parent version status UNACCEPTED_ENTITY
           // In the second instance of acceptSubobject( FinAidAwardDisbursement ), we set all child entities to parent version status UNACCEPTED_ROOT, which
           // then throws the exception when doing acceptSubobject( FinAidAward ).


            // Set up code.
            RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
            RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
            SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
            fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
            RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            DropView( vTempViewVar_0 );
            SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
            OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

           o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
           RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );


           o_fnLocalBuildQualmFASrc( ViewToWindow, vTempViewVar_0, 348 );
           RESULT = ActivateObjectInstance( mFASrc, "mFASrc", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
           DropView( vTempViewVar_0 );
           SetNameForView( mFASrc, "mFASrc", null, zLEVEL_TASK );
           //xxxx

            RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", ViewToWindow, zSINGLE );
            SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
            RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );

            RESULT = CreateEntity( mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("PeriodDesignator").setValue("2016-2017 Fall");  //2016-2017 Fall
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("BeginDate").setValue("20160804");
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("EndDate").setValue("20170515");
            // End of set up code...


            // This works
            CreateTemporalEntity( mFAProf, "FinAidAward", zPOS_AFTER );
            mFAProf.cursor("FinAidAward").getAttribute("AwardType").setValue("G");
            mFAProf.cursor("FinAidAward").getAttribute("AwardStatus").setValue("A");
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );

            CreateTemporalEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod",   mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
            mFAProf.cursor("FinAidAwardDisbursement").acceptSubobject();
            mFAProf.cursor("FinAidAward").acceptSubobject();

            // This does not work.
            CreateTemporalSubobjectVersion( mFAProf, "FinAidAward" );
            CreateTemporalEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod",   mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
            mFAProf.cursor("FinAidAwardDisbursement").acceptSubobject();
            mFAProf.cursor("FinAidAward").acceptSubobject();

           return 0;
        }

        public int
        mFAProfTemporalPerProfileFinAidAwardPeriodPathTest( View ViewToWindow )
        {
           zVIEW    mPerson = new zVIEW( );
           zVIEW    mFAProf = new zVIEW( );
           zVIEW    mFASrc = new zVIEW( );
           zVIEW    lTermLST = new zVIEW( );
           zVIEW    wXferO = new zVIEW( );
           zVIEW    vTempViewVar_0 = new zVIEW( );
           int RESULT=0;

           // KJS 09/18/19 - at the moment this gives the same error as the test mFAProfTemporalIssue3,
           // but when that test gets fixed, I want to make sure that the values in PerPeriodFinAidAwardDisbursement, down the
           // PerProfileFinAidAwardPeriod path show the same values as the original FinAidAwardDisbursement entity under FinAidAward.
           // The PerProfileFinAidAwardPeriod path is the "included" path.

            // Set up code.
            RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
            RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
            SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
            fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
            RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            DropView( vTempViewVar_0 );
            SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
            OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

           o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
           RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );


           o_fnLocalBuildQualmFASrc( ViewToWindow, vTempViewVar_0, 348 );
           RESULT = ActivateObjectInstance( mFASrc, "mFASrc", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
           DropView( vTempViewVar_0 );
           SetNameForView( mFASrc, "mFASrc", null, zLEVEL_TASK );

            RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", ViewToWindow, zSINGLE );
            SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
            RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );

            RESULT = CreateEntity( mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("PeriodDesignator").setValue("2016-2017 Fall");  //2016-2017 Fall
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("BeginDate").setValue("20160804");
            mFAProf.cursor("PerProfileFinAidAwardPeriod").getAttribute("EndDate").setValue("20170515");
            // End of set up code...

            // This works
            CreateTemporalEntity( mFAProf, "FinAidAward", zPOS_AFTER );
            mFAProf.cursor("FinAidAward").getAttribute("AwardType").setValue("G");
            mFAProf.cursor("FinAidAward").getAttribute("AwardStatus").setValue("A");
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );

            CreateTemporalEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod",   mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
            mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 500) ;
            mFAProf.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 500) ;
            mFAProf.cursor("FinAidAwardDisbursement").acceptSubobject();
            mFAProf.cursor("FinAidAward").acceptSubobject();

            RESULT= mFAProf.cursor("FinAidAwardDisbursement").setFirst( "Amount", 500 ).toInt();
            RESULT= mFAProf.cursor("PerPeriodFinAidAwardDisbursement").setFirst( "Amount", 500 ).toInt();
             Assert.assertEquals("PerPeriodFinAidAwardDisbursement entity doesn't exist for amount 500.", 0, RESULT, 0.0);

             // Another test
            CreateTemporalSubobjectVersion( mFAProf, "FinAidAward" );
            CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement");
            mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 300) ;
            mFAProf.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 300) ;
            mFAProf.cursor("FinAidAwardDisbursement").acceptSubobject();
            mFAProf.cursor("FinAidAward").acceptSubobject();
            RESULT= mFAProf.cursor("FinAidAwardDisbursement").setFirst( "Amount", 300 ).toInt();
            RESULT= mFAProf.cursor("PerPeriodFinAidAwardDisbursement").setFirst( "Amount", 300 ).toInt();
             Assert.assertEquals("PerPeriodFinAidAwardDisbursement entity doesn't exist for amount 300.", 0, RESULT, 0.0);

             // This does not work.
            CreateTemporalSubobjectVersion( mFAProf, "FinAidAward" );
            CreateTemporalEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod",   mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
            mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 100) ;
            mFAProf.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 100) ;
            mFAProf.cursor("FinAidAwardDisbursement").acceptSubobject();
            mFAProf.cursor("FinAidAward").acceptSubobject();

            RESULT= mFAProf.cursor("FinAidAwardDisbursement").setFirst( "Amount", 100 ).toInt();
            RESULT= mFAProf.cursor("PerPeriodFinAidAwardDisbursement").setFirst( "Amount", 100 ).toInt();
             Assert.assertEquals("PerPeriodFinAidAwardDisbursement entity doesn't exist for amount 100.", 0, RESULT, 0.0);

           return 0;
        }


        public int
        mFAProfTemporalFinAidAward( View ViewToWindow )
        {
           zVIEW    mPerson = new zVIEW( );
           zVIEW    mFAProf = new zVIEW( );
           zVIEW    mFASrc = new zVIEW( );
           zVIEW    lTermLST = new zVIEW( );
           zVIEW    wXferO = new zVIEW( );
           zVIEW    vTempViewVar_0 = new zVIEW( );
           int RESULT=0;

           // KJS 03/23/21
           // Create Temporal Subobject for FinAidAward
           // Include FinAidSource
           // Add FinAidAwardDisbursements
           // Accept FinAidAward
           // FinAidSource is missing all values (and mFASrc doesn't look right as well).

            // Set up code.
            RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
            RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
            SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
            fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
            RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            DropView( vTempViewVar_0 );
            SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
            OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

           o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
           RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );


           o_fnLocalBuildQualmFASrc( ViewToWindow, vTempViewVar_0, 348 );
           RESULT = ActivateObjectInstance( mFASrc, "mFASrc", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
           DropView( vTempViewVar_0 );
           SetNameForView( mFASrc, "mFASrc", null, zLEVEL_TASK );

            RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", ViewToWindow, zSINGLE );
            SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
            RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );

            CreateTemporalEntity( mFAProf, "FinAidAward", zPOS_AFTER );
            mFAProf.cursor("FinAidAward").getAttribute("AwardType").setValue("G");
            mFAProf.cursor("FinAidAward").getAttribute("AwardStatus").setValue("A");
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );

            RESULT = CreateEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
            mFAProf.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 500) ;
            mFAProf.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 500) ;
            mFAProf.cursor("FinAidAward").acceptSubobject();
            Assert.assertTrue( "FinAidSource entity exists but is empty after acceptSubobject", mFAProf.cursor("FinAidSource").getAttribute("ID").getString().length() > 0 );

            return 0;

        }

        public int
        mFAProfTemporalLinkIssue( View ViewToWindow )
        {
           zVIEW    mPerson = new zVIEW( );
           zVIEW    mFAProf = new zVIEW( );
           zVIEW    lFACOAYr = new zVIEW( );
           zVIEW    mFACOAYr = new zVIEW( );
           zVIEW    mYear = new zVIEW( );
           zVIEW    lFANdProLST = new zVIEW( );
           zVIEW    lTermLST = new zVIEW( );
           zVIEW    wXferO = new zVIEW( );
           zVIEW    vTempViewVar_0 = new zVIEW( );
           zVIEW    vTempViewVar_1 = new zVIEW( );
           int RESULT=0;
           int nRC=0;

           // KJS 05/13/19 - This test creates a TemporalSubobject for the root entity FinAidProfile. Then we create/delete/create the
           // sub entity FinAidCOAItemAssigned. After the delete/create, we try to do a create TemporalSubobject on FinAidCOAItemAssigned and we
           // receive a TemporalEntityException: Attempting to create a temporal subobject for an entity that has a child entity linked to another
           // temporal entity.
           // We do not get an error unless we've created the temporal on the root FinAidProfile.

           RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
           RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
           SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );

           o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
           RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );


           RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", ViewToWindow, zSINGLE );
           SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
           RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
           RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );
            RESULT = CommitObjectInstance( mFAProf );

            CreateTemporalSubobjectVersion( mFAProf, "FinAidProfile" );

            //:ACTIVATE  mYear  WHERE mYear.CollegeYear.ID = 44
            o_fnLocalBuildQualmYear( ViewToWindow, vTempViewVar_1 );
            RESULT = ActivateObjectInstance( mYear, "mYear", ViewToWindow, vTempViewVar_1, zSINGLE );
            DropView( vTempViewVar_1 );
            SetNameForView( mYear, "mYear", null, zLEVEL_TASK );

            // This next piece of code is only to make sure that we have some FInAidCOAItemForYear entitites exist so we can
            // use them for creating mFAProf.FinAidCOAItemAssigned

            //:ACTIVATE  lFANdProLST Multiple WHERE lFANdProLST.AdministrativeDivision.ID = 1 AND lFANdProLST.FinAidCOA.ID = 2
            //:         RESTRICTING lFANdProLST.FinAidCOAItemForYear TO lFANdProLST.CollegeYear.ID = 44
            o_fnLocalBuildQuallFANdProLST2( ViewToWindow, vTempViewVar_0 );
            RESULT = ActivateObjectInstance( lFANdProLST, "mFANdPro", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            DropView( vTempViewVar_0 );
            SetNameForView( lFANdProLST, "lFANdProLST", null, zLEVEL_TASK );
            RESULT = SetCursorFirstEntity( lFANdProLST, "FinAidCOAItem", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            {
                if ( CheckExistenceOfEntity( lFANdProLST, "FinAidCOAItemForYear" ) != 0 )
                {
                RESULT = ActivateEmptyObjectInstance( mFACOAYr, "mFACOAYr", ViewToWindow, zSINGLE );
                SetNameForView( mFACOAYr, "mFACOAYr", null, zLEVEL_TASK );
                RESULT = CreateEntity( mFACOAYr, "FinAidCOAItemForYear", zPOS_AFTER );
                SetAttributeFromInteger( mFACOAYr, "FinAidCOAItemForYear", "RevenueAmount", 1500 );
                SetAttributeFromInteger( mFACOAYr, "FinAidCOAItemForYear", "FirstTermRevenueAmount", 500 );
                SetAttributeFromInteger( mFACOAYr, "FinAidCOAItemForYear", "SecondTermRevenueAmount", 500 );
                SetAttributeFromInteger( mFACOAYr, "FinAidCOAItemForYear", "ThirdTermRevenueAmount", 500 );
                RESULT = IncludeSubobjectFromSubobject( mFACOAYr, "FinAidCOAItem", lFANdProLST, "FinAidCOAItem", zPOS_AFTER );
                RESULT = IncludeSubobjectFromSubobject( mFACOAYr, "CollegeYear", mYear, "CollegeYear", zPOS_AFTER );
                RESULT = CommitObjectInstance( mFACOAYr );
                DropObjectInstance( mFACOAYr );
                }

                RESULT = SetCursorNextEntity( lFANdProLST, "FinAidCOAItem", "" );
            }

            DropObjectInstance( lFANdProLST );
            // END of making sure FinAidCOAItemForYear exists

            // CREATE FinAidCOAItemAssigned
            o_fnLocalBuildQuallFANdProLST2( ViewToWindow, vTempViewVar_0 );
            RESULT = ActivateObjectInstance( lFANdProLST, "mFANdPro", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            DropView( vTempViewVar_0 );
            SetNameForView( lFANdProLST, "lFANdProLST", null, zLEVEL_TASK );
            RESULT = SetCursorFirstEntity( lFANdProLST, "FinAidCOAItem", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            {
                RESULT = CreateEntity( mFAProf, "FinAidCOAItemAssigned", zPOS_AFTER );
                RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidCOAItemForYear", lFANdProLST, "FinAidCOAItemForYear", zPOS_AFTER );
                SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "RevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "RevenueAmount" );
                SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "FirstTermRevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "FirstTermRevenueAmount" );
                SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "SecondTermRevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "SecondTermRevenueAmount" );
                SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "ThirdTermRevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "ThirdTermRevenueAmount" );
                RESULT = SetCursorNextEntity( lFANdProLST, "FinAidCOAItem", "" );
            }

            // DELETE FinAidCOAItemAssigned
              RESULT = SetCursorFirstEntity( mFAProf, "FinAidCOAItemAssigned", "" );
              while ( RESULT > zCURSOR_UNCHANGED )
              {
                 RESULT = DeleteEntity( mFAProf, "FinAidCOAItemAssigned", zREPOS_NONE );
                 RESULT = SetCursorNextEntity( mFAProf, "FinAidCOAItemAssigned", "" );
              }

            // CREATE FinAidCOAItemAssigned again
              RESULT = SetCursorFirstEntity( lFANdProLST, "FinAidCOAItem", "" );
              while ( RESULT > zCURSOR_UNCHANGED )
              {
                 RESULT = CreateEntity( mFAProf, "FinAidCOAItemAssigned", zPOS_AFTER );
                 RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidCOAItemForYear", lFANdProLST, "FinAidCOAItemForYear", zPOS_AFTER );
                 SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "RevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "RevenueAmount" );
                 SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "FirstTermRevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "FirstTermRevenueAmount" );
                 SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "SecondTermRevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "SecondTermRevenueAmount" );
                 SetAttributeFromAttribute( mFAProf, "FinAidCOAItemAssigned", "ThirdTermRevenueAmount", lFANdProLST, "FinAidCOAItemForYear", "ThirdTermRevenueAmount" );
                 RESULT = SetCursorNextEntity( lFANdProLST, "FinAidCOAItem", "" );
              }

              // We receive a TemporalEntityExcption on this.
              // We only get this when we've done a CreateTemporalSubobjectVersion( mFAProf, "FinAidProfile" )
            CreateTemporalSubobjectVersion( mFAProf, "FinAidCOAItemAssigned" );

           return 0;
        }

        public int
        mFAProfCreateTemporalCODLinkIssue( View ViewToWindow )
        {
           zVIEW    mFAProf = new zVIEW( );
           zVIEW    vTempViewVar_0 = new zVIEW( );

           // KJS 10/06/21
           // One the create temporal of "COD_Disbursement", we get the following error:
           // TemporalEntityException: Attempting to create a temporal subobject for an entity that has a child entity linked to another temporal entity.
           // If we take out either the create temporal of "FinAidAward" or "FinAidAwardDisbursement", it works without error.

           o_fnLocalBuildmFAProf2( ViewToWindow, vTempViewVar_0, 23496 ); //348  23496
           ActivateObjectInstance( mFAProf, "mFAProf", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );
           SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );

           // If COD_Disbursement doesn't exist, create one and save for our test purposes.
           if ( !mFAProf.cursor("COD_Disbursement").checkExistenceOfEntity().isSet() )
           {
               mFAProf.cursor("COD_Disbursement").createEntity();
               mFAProf.cursor("COD_Disbursement").getAttribute("SequenceNumber").setValue("1");
               mFAProf.cursor("COD_Disbursement").getAttribute("COD_ResponseCode").setValue("A");
               mFAProf.commit();
           }
            CreateTemporalSubobjectVersion( mFAProf, "FinAidAward" );
            CreateTemporalSubobjectVersion( mFAProf, "FinAidAwardDisbursement");
            CreateTemporalSubobjectVersion( mFAProf, "COD_Disbursement");

           return 0;
        }


        public int
        CreateTemporalDerivedEntityWorkAttributeIssue( View ViewToWindow )
        {
           zVIEW    mPerson = new zVIEW( );
           zVIEW    mFAProf = new zVIEW( );
           zVIEW    mFASrc = new zVIEW( );
           zVIEW    mClass = new zVIEW( );
           zVIEW    lTermLST = new zVIEW( );
           zVIEW    wXferO = new zVIEW( );
           zVIEW    vTempViewVar_0 = new zVIEW( );
           int RESULT=0;

           // KJS 09/16/19
           // Issue: After a CreateTemporalSubobjectVersion, derived entity work attributes are blank.
           // We create derived entity GradeEnrollment from entity Enrollment and set a work attribute (GradeEnrollment.wEnteredGrade).
           // After CreateTemporalSubobjectVersion on the root entity Class the work attribute in GradeEnrollment is blank.
           // Work attributes in the original entity Enrollment are fine.
           // ER attributes in the derived entity GradeEnrollment are fine.

            RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
            RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
            SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
            fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
            RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            DropView( vTempViewVar_0 );
            SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
            OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );

            int testClass = 2560;

           o_fnLocalBuildmClass( ViewToWindow, vTempViewVar_0, testClass );

           RESULT = ActivateObjectInstance( mClass, "mClass", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );
           SetNameForView( mClass, "mClass", null, zLEVEL_TASK );

           RESULT = SetCursorFirstEntity( mClass, "GradeEnrollment", "" );
           while ( RESULT > zCURSOR_UNCHANGED )
           {
              RESULT = ExcludeEntity( mClass, "GradeEnrollment", zREPOS_NONE );
              RESULT = SetCursorNextEntity( mClass, "GradeEnrollment", "" );
           }

           RESULT = SetCursorFirstEntity( mClass, "Enrollment", "" );
           while ( RESULT > zCURSOR_UNCHANGED )
           {
              SetAttributeFromAttribute( mClass, "Enrollment", "wOriginalFinalGrade", mClass, "Enrollment", "FinalGrade" );
              if ( CompareAttributeToString( mClass, "Enrollment", "Status", "T" ) == 0 || CompareAttributeToString( mClass, "Enrollment", "Status", "C" ) == 0 )
              {
                  RESULT = IncludeSubobjectFromSubobject( mClass, "GradeEnrollment", mClass, "Enrollment", zPOS_AFTER );
                  SetAttributeFromAttribute( mClass, "GradeEnrollment", "wEnteredGrade", mClass, "Enrollment", "FinalGrade" );
              }

              RESULT = SetCursorNextEntity( mClass, "Enrollment", "" );
           }
           SetAttributeFromString( mClass, "Class", "wEnterGradesType", "F" );
           RESULT = SetCursorFirstEntity( mClass, "GradeEnrollment", "" );
           String grade = mClass.cursor("GradeEnrollment").getAttribute("wEnteredGrade").getString();
           Assert.assertTrue( "Before ", ! grade.equals("") );
           CreateTemporalSubobjectVersion( mClass, "Class" );
           Assert.assertTrue( "After ", !mClass.cursor("GradeEnrollment").getAttribute("wEnteredGrade").getString().equals("") );

           return 0;
        }
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b


private int
o_fnLocalBuildQualmYear( View     vSubtask,
                       zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeYear" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "44" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
}

private int
o_fnLocalBuildQuallFANdProLST2( View     vSubtask,
                       zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidCOA" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "1" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidCOA" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "2" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidCOAItemForYear" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "44" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
}
private int
o_fnLocalBuildQuallFANdProLST1( View     vSubtask,
                       zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidCOA" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "1" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidCOA" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "2" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
}

private int
o_fnLocalBuildQuallFANdProLST( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_2,
                       int      lTempInteger_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidCOA" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidCOAItemForYear" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
}

<<<<<<< HEAD
		public int
		activateRootOnly( View ViewToWindow )
		{
		   zVIEW    mPerson = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int RESULT=0;
           StringBuilder sb_Country = new StringBuilder( );

           /*
		   // KJS 05/0815 - Just wanting to look at domain context... this is not part of this test.
		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
		   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );

           GetStringFromAttributeByContext( sb_Country, mPerson, "Address", "Country", "OnlineAppCountry", 50 );
		   */


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
		testNullTableDomain( View ViewToWindow )
		{
		   zVIEW    mStudent = new zVIEW( );
		   zVIEW    lTermLST = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int RESULT=0;
=======
        public int
        activateRootOnly( View ViewToWindow )
        {
           zVIEW    mPerson = new zVIEW( );
           zVIEW    vTempViewVar_0 = new zVIEW( );
           int RESULT=0;
           StringBuilder sb_Country = new StringBuilder( );

           /*
           // KJS 05/0815 - Just wanting to look at domain context... this is not part of this test.
           o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
           RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );

           GetStringFromAttributeByContext( sb_Country, mPerson, "Address", "Country", "OnlineAppCountry", 50 );
           */


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
        testNullTableDomain( View ViewToWindow )
        {
           zVIEW    mStudent = new zVIEW( );
           zVIEW    lTermLST = new zVIEW( );
           zVIEW    wXferO = new zVIEW( );
           zVIEW    vTempViewVar_0 = new zVIEW( );
           int RESULT=0;
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
           StringBuilder sb_Country = new StringBuilder( );

           /*
            * KJS 07/18/17
            * When we create a WHERE clause with a table domain like YNField = "", if the
            * table domain excepts a blank value, then the where clause is created with only a
            * "YNField = ''. Because our values are stored in the database as NULL, then we do
            * not retrieve any database values. We need to where clause to be
            * "YNField IS NULL OR YNField = ''"
            * I currently think that in isNullAndEmptyString() "QualAttrib.java" we need the
            * following line:
            *        if ( ! ( domain instanceof StringDomain || domain instanceof TableDomain ) )
            * so that we do not just return the "false" value. Which so far in my testing has
            * not caused issue...
            * But DG changed SystemApplication.nullStringEqualsEmptyString to true and now
            * this works correctly. So I took my change out and put his in.
<<<<<<< HEAD
		   */

            // These are views that need to get created in order to be able to activate
            // mStudent.
		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
		    fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
			RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			DropView( vTempViewVar_0 );
			SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
			OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );
			RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst( "CurrentTermFlag", "Y" ).toInt();

			// Activate mStudent where student.id in (7,8,11) and where student.PhiDeltaLambdaFlag = ""
			// Changed to only activate student.id = 7
			// This is to make sure that we have "PhiDeltaLamdaFlag IS NULL" as opposed to only "PhiDeltaLamdaFlag = ''"
		    o_BuildQualmStudent( ViewToWindow, vTempViewVar_0 );
		    RESULT = ActivateObjectInstance( mStudent, "mStudent", ViewToWindow, vTempViewVar_0, zMULTIPLE );
		    DropView( vTempViewVar_0 );
		    RESULT = CheckExistenceOfEntity( mStudent, "Student");
		    // Check if we activated any Student entities.
 		    Assert.assertEquals("Activate mStudent should have activated 1 entity but is returning none.", CursorResult.SET.toInt(), RESULT );
		    DropView( mStudent );
			// Activate mStudent where student.id in ( 8, 9 ) and where student.GeneralNote = ""
		    // This is to make sure that we have "(GeneralNote IS NULL OR GeneralNote = '')"
		    o_BuildQualmStudent2( ViewToWindow, vTempViewVar_0 );
		    RESULT = ActivateObjectInstance( mStudent, "mStudent", ViewToWindow, vTempViewVar_0, zMULTIPLE );
		    DropView( vTempViewVar_0 );
		    RESULT = CheckExistenceOfEntity( mStudent, "Student");
		    // Check if we activated any Student entities.
 		    Assert.assertEquals("Activate mStudent should have activated 2 entities but is returning none.", CursorResult.SET.toInt(), RESULT );
		    RESULT = SetCursorNextEntity( mStudent, "Student", "" );
 		    Assert.assertEquals("Activate mStudent should have activated 2 entities but is returning only one.", CursorResult.SET.toInt(), RESULT );
 		    DropView( lTermLST );
		   	//:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = 175
		   	o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, 175 );
	   	    RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zSINGLE );
	   	    DropView( vTempViewVar_0 );
	   	    SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" );
	   	    RESULT = CommitObjectInstance( lTermLST );
	   	    SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "" );
	   	    RESULT = CommitObjectInstance( lTermLST );
	   	    // We want to make sure that when we update a table list domain to "" that it updates the db with null not ''.
	   	    // The activate for lTermLST should activate CurrentForStudentAccounts to "CurrentForStudentAccounts IS NULL" and
	   	    // so if the setting of "" is incorrect, then we wouldn't retrieve a value.
	   	    o_fnLocalBuildlTermWithNullValue( ViewToWindow, vTempViewVar_0, 175 );
	   	    RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zSINGLE );
	   	    DropView( vTempViewVar_0 );
		    RESULT = CheckExistenceOfEntity( lTermLST, "CollegeTerm");
		    // Check if we activated any Student entities.
 		    Assert.assertEquals("Activate lTermLST should have activated 1 entity but is returning none.", CursorResult.SET.toInt(), RESULT );

 		    return 0;
		}

		public int
		testSAProfIncludeSubobjects( View ViewToWindow )
		{
		   zVIEW    mSAProfEList = new zVIEW( );
		   zVIEW    mSAProf      = new zVIEW( );
		   int RESULT=0;

	   	   ActivateOI_FromFile( mSAProf, "mSAProf", ViewToWindow, "target/test-classes/testdata//ZENCAs/mSAProfIncludeErr.por", zSINGLE );
		   ActivateEmptyObjectInstance( mSAProfEList, "mSAProfE", ViewToWindow, zMULTIPLE );
		   //:NAME VIEW mSAProfEList "mSAProfEList"
		   SetNameForView( mSAProfEList, "mSAProfEList", null, zLEVEL_TASK );
		   //:INCLUDE mSAProfEList.StudentAccountProfile FROM mSAProf.StudentAccountProfile
		   RESULT = IncludeSubobjectFromSubobject( mSAProfEList, "StudentAccountProfile", mSAProf, "StudentAccountProfile", zPOS_AFTER );

		   return 0;
		}

		public int
		testLODDisplayRoot( View ViewToWindow )
		{
			zVIEW    DOMAINTL = new zVIEW( );
			int RESULT=0;

			// At IBOE when we do this activate the admin divs are not correct, and it seems like it's because Domain is display only
			// and AdminDiv is not. However... here in this test it works just fine. Will try adding this test to 1.3.8.3.

		    RESULT = ActivateObjectInstance( DOMAINTL, "DOMAINTL", ViewToWindow, 0, zMULTIPLE );


			return 0;
		}

		//:DIALOG OPERATION
		//:Test_Time( VIEW ViewToWindow )
		//:   VIEW mDrvRoute BASED ON LOD mDrvRoute
		public int
		testDateTimeCompare( View     ViewToWindow )
		{
		   zVIEW    mDrvRoute = new zVIEW( );
		   //:VIEW wXferO    REGISTERED AS wXferO
		   zVIEW    wXferO = new zVIEW( );
		   int      RESULT = 0;
		   //:STRING ( 30 ) szStartDateTime
		   String   szStartDateTime = null;
		   //:STRING ( 30 ) szEndDateTime
		   String   szEndDateTime = null;

		   // Comparing date/time does not appear to be working unless we get the dates as strings first.
		    RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		    //:CREATE ENTITY wXferO.Root
		    RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		    //:NAME VIEW wXferO "wXferO"
		    SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );

		   //:// 4/21 to 6/16

		   //:SetAttrFromStrByContext( wXferO, "Root", "WorkDateTime",  "201608040100000", "YYYYMMDDHHMMSSS" )
		   {
		    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
		    m_ZGLOBAL1_Operation.SetAttrFromStrByContext( wXferO, "Root", "WorkDateTime", "201608040100000", "YYYYMMDDHHMMSSS" );
		    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
		   }
		   //:SetAttrFromStrByContext( wXferO, "Root", "WorkDateTime2", "201608042230000", "YYYYMMDDHHMMSSS" )
		   {
		    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
		    m_ZGLOBAL1_Operation.SetAttrFromStrByContext( wXferO, "Root", "WorkDateTime2", "201608042230000", "YYYYMMDDHHMMSSS" );
		    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
		   }
		   //:IF wXferO.Root.WorkDateTime > wXferO.Root.WorkDateTime2
		   if ( CompareAttributeToAttribute( wXferO, "Root", "WorkDateTime", wXferO, "Root", "WorkDateTime2" ) > 0 )
		   {
		      MessageSend( ViewToWindow, "", "Save", "End Date/Time is not later than Start Date/Time.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
		      //:RETURN 2
		      if(8==8)return( 2 );
		   }

		   {
			    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
			    m_ZGLOBAL1_Operation.SetAttrFromStrByContext( wXferO, "Root", "WorkDateTime", "201608040100000", "YYYYMMDDHHMMSSS" );
			    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
		   }
			   //:SetAttrFromStrByContext( wXferO, "Root", "WorkDateTime2", "201608042230000", "YYYYMMDDHHMMSSS" )
			   {
			    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
			    m_ZGLOBAL1_Operation.SetAttrFromStrByContext( wXferO, "Root", "WorkDateTime2", "201608040100000", "YYYYMMDDHHMMSSS" );
			    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
			   }


			   if ( CompareAttributeToAttribute( wXferO, "Root", "WorkDateTime", wXferO, "Root", "WorkDateTime2" ) > 0 )
			   {
			      MessageSend( ViewToWindow, "", "Save", "End Date/Time is not later than Start Date/Time.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
			      //:RETURN 2
			      if(8==8)return( 2 );
			   }

		   return( 0 );
		}

		public int
		testRelinking( View ViewToWindow )
		{
			   zVIEW    mSAAdmin = new zVIEW( );
			   int      RESULT = 0;
			   //:VIEW mSAAdmin2   BASED ON LOD  mSAAdmin
			   zVIEW    mSAAdmin2 = new zVIEW( );
			   //:VIEW lSATermLST  REGISTERED AS lSATermLST
			   zVIEW    lSATermLST = new zVIEW( );
			   //:VIEW lSATermLST2 BASED ON LOD  lTermLST
			   zVIEW    lSATermLST2 = new zVIEW( );
			   //:VIEW lTermLST    BASED ON LOD  lTermLST
			   zVIEW    lTermLST = new zVIEW( );
			   //:INTEGER TermID
			   int      TermID = 0;
			   String   szTempString_0 = null;
			   int      lTempInteger_0 = 0;
			   zVIEW    vTempViewVar_0 = new zVIEW( );
			   int      lTempInteger_1 = 0;
			   String   szTempString_1 = null;
			   String   szTempString_2 = null;
			   String   szTempString_3 = null;
			   String   szTempString_4 = null;
			   int      lTempInteger_2 = 0;
			   zVIEW    vTempViewVar_1 = new zVIEW( );
			   zVIEW    vTempViewVar_2 = new zVIEW( );
			   int      lTempInteger_3 = 0;

			   // After doing a couple of relinks, it appears that the relink fails (or relinks the opposite of how it's supposed to).

		   	   ActivateOI_FromFile( mSAAdmin, "mSAAdmin", ViewToWindow, "target/test-classes/testdata//ZENCAs/mSAAdmin.por", zSINGLE );
		   	   ActivateOI_FromFile( lSATermLST, "lTermLST", ViewToWindow, "target/test-classes/testdata//ZENCAs/lSATermsLST.por", zMULTIPLE );
		   	   RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", 175, "" );
			   //RESULT = GetViewByName( mSAAdmin, "mSAAdmin", ViewToWindow, zLEVEL_TASK );
			   //RESULT = GetViewByName( lSATermLST, "lSATermLST", ViewToWindow, zLEVEL_TASK );


		   	   //:// Set the selected College Term as "Current" after making sure all others are NOT Current.
		   	   //:TermID = mSAAdmin.CollegeTerm.ID
		   	   {MutableInt mi_TermID = new MutableInt( TermID );
		   	       GetIntegerFromAttribute( mi_TermID, mSAAdmin, "CollegeTerm", "ID" );
		   	   TermID = mi_TermID.intValue( );}

		   	   //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = 175
		   	o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, 175 );
		   	   RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zSINGLE );
		   	   DropView( vTempViewVar_0 );
		   	   //:lTermLST.CollegeTerm.CurrentForStudentAccounts = ""
		   	   SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "" );
		   	   //:COMMIT lTermLST
		   	   RESULT = CommitObjectInstance( lTermLST );
		   	   //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = lTermLST.CollegeTerm.ID
		   	   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
		   	       GetIntegerFromAttribute( mi_lTempInteger_0, lTermLST, "CollegeTerm", "ID" );
		   	   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
		   	   RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", lTempInteger_0, "" );

		   	   // Relink the the instances.
		   	   RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
               szTempString_1 = lTermLST.cursor( "CollegeTerm" ).getAttribute("CurrentForStudentAccounts").getString();
		   	   //:DropObjectInstance( lTermLST )
		   	   DropObjectInstance( lTermLST );
		   	   //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = 176
		   	   RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", 176, "" );
		   	   //:// Then set Term on which we're currently positioned to Current.
		   	   //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = mSAAdmin.CollegeTerm.ID
		   	   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
		   	       GetIntegerFromAttribute( mi_lTempInteger_1, mSAAdmin, "CollegeTerm", "ID" );
		   	   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
		   	o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_1, lTempInteger_1 );
		   	   RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_1, zSINGLE );
		   	   DropView( vTempViewVar_1 );
		   	   //:lTermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
		   	   SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" );
		   	   //:COMMIT lTermLST
		   	   RESULT = CommitObjectInstance( lTermLST );
		   	   //:RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" )
		   	   RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
		   	   //:DropObjectInstance( lTermLST )
		   	   DropObjectInstance( lTermLST );

		   	   //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = 176
		   	o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_2, 176 );
		   	   RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_2, zSINGLE );
		   	   DropView( vTempViewVar_2 );
		   	   //:lTermLST.CollegeTerm.CurrentForStudentAccounts = ""
		   	   SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "" );
		   	   //:COMMIT lTermLST
		   	   RESULT = CommitObjectInstance( lTermLST );
		   	   //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = lTermLST.CollegeTerm.ID
		   	   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
		   	       GetIntegerFromAttribute( mi_lTempInteger_2, lTermLST, "CollegeTerm", "ID" );
		   	   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
		   	   RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", lTempInteger_2, "" );
		   	   //:RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" )
=======
           */

            // These are views that need to get created in order to be able to activate
            // mStudent.
            RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
            RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
            SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
            fnLocalBuildlTermLST( ViewToWindow, vTempViewVar_0 );
            RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            DropView( vTempViewVar_0 );
            SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
            OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D CollegeTerm.Semester D" );
            RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst( "CurrentTermFlag", "Y" ).toInt();

            // Activate mStudent where student.id in (7,8,11) and where student.PhiDeltaLambdaFlag = ""
            // Changed to only activate student.id = 7
            // This is to make sure that we have "PhiDeltaLamdaFlag IS NULL" as opposed to only "PhiDeltaLamdaFlag = ''"
            o_BuildQualmStudent( ViewToWindow, vTempViewVar_0 );
            RESULT = ActivateObjectInstance( mStudent, "mStudent", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            DropView( vTempViewVar_0 );
            RESULT = CheckExistenceOfEntity( mStudent, "Student");
            // Check if we activated any Student entities.
             Assert.assertEquals("Activate mStudent should have activated 1 entity but is returning none.", CursorResult.SET.toInt(), RESULT );
            DropView( mStudent );
            // Activate mStudent where student.id in ( 8, 9 ) and where student.GeneralNote = ""
            // This is to make sure that we have "(GeneralNote IS NULL OR GeneralNote = '')"
            o_BuildQualmStudent2( ViewToWindow, vTempViewVar_0 );
            RESULT = ActivateObjectInstance( mStudent, "mStudent", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            DropView( vTempViewVar_0 );
            RESULT = CheckExistenceOfEntity( mStudent, "Student");
            // Check if we activated any Student entities.
             Assert.assertEquals("Activate mStudent should have activated 2 entities but is returning none.", CursorResult.SET.toInt(), RESULT );
            RESULT = SetCursorNextEntity( mStudent, "Student", "" );
             Assert.assertEquals("Activate mStudent should have activated 2 entities but is returning only one.", CursorResult.SET.toInt(), RESULT );
             DropView( lTermLST );
               //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = 175
               o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, 175 );
               RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zSINGLE );
               DropView( vTempViewVar_0 );
               SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" );
               RESULT = CommitObjectInstance( lTermLST );
               SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "" );
               RESULT = CommitObjectInstance( lTermLST );
               // We want to make sure that when we update a table list domain to "" that it updates the db with null not ''.
               // The activate for lTermLST should activate CurrentForStudentAccounts to "CurrentForStudentAccounts IS NULL" and
               // so if the setting of "" is incorrect, then we wouldn't retrieve a value.
               o_fnLocalBuildlTermWithNullValue( ViewToWindow, vTempViewVar_0, 175 );
               RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zSINGLE );
               DropView( vTempViewVar_0 );
            RESULT = CheckExistenceOfEntity( lTermLST, "CollegeTerm");
            // Check if we activated any Student entities.
             Assert.assertEquals("Activate lTermLST should have activated 1 entity but is returning none.", CursorResult.SET.toInt(), RESULT );

             return 0;
        }

        public int
        testSAProfIncludeSubobjects( View ViewToWindow )
        {
           zVIEW    mSAProfEList = new zVIEW( );
           zVIEW    mSAProf      = new zVIEW( );
           int RESULT=0;

              ActivateOI_FromFile( mSAProf, "mSAProf", ViewToWindow, "target/test-classes/testdata//ZENCAs/mSAProfIncludeErr.por", zSINGLE );
           ActivateEmptyObjectInstance( mSAProfEList, "mSAProfE", ViewToWindow, zMULTIPLE );
           //:NAME VIEW mSAProfEList "mSAProfEList"
           SetNameForView( mSAProfEList, "mSAProfEList", null, zLEVEL_TASK );
           //:INCLUDE mSAProfEList.StudentAccountProfile FROM mSAProf.StudentAccountProfile
           RESULT = IncludeSubobjectFromSubobject( mSAProfEList, "StudentAccountProfile", mSAProf, "StudentAccountProfile", zPOS_AFTER );

           return 0;
        }

        public int
        testLODDisplayRoot( View ViewToWindow )
        {
            zVIEW    DOMAINTL = new zVIEW( );
            int RESULT=0;

            // At IBOE when we do this activate the admin divs are not correct, and it seems like it's because Domain is display only
            // and AdminDiv is not. However... here in this test it works just fine. Will try adding this test to 1.3.8.3.

            RESULT = ActivateObjectInstance( DOMAINTL, "DOMAINTL", ViewToWindow, 0, zMULTIPLE );


            return 0;
        }

        //:DIALOG OPERATION
        //:Test_Time( VIEW ViewToWindow )
        //:   VIEW mDrvRoute BASED ON LOD mDrvRoute
        public int
        testDateTimeCompare( View     ViewToWindow )
        {
           zVIEW    mDrvRoute = new zVIEW( );
           //:VIEW wXferO    REGISTERED AS wXferO
           zVIEW    wXferO = new zVIEW( );
           int      RESULT = 0;
           //:STRING ( 30 ) szStartDateTime
           String   szStartDateTime = null;
           //:STRING ( 30 ) szEndDateTime
           String   szEndDateTime = null;

           // Comparing date/time does not appear to be working unless we get the dates as strings first.
            RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
            //:CREATE ENTITY wXferO.Root
            RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
            //:NAME VIEW wXferO "wXferO"
            SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );

           //:// 4/21 to 6/16

           //:SetAttrFromStrByContext( wXferO, "Root", "WorkDateTime",  "201608040100000", "YYYYMMDDHHMMSSS" )
           {
            ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
            m_ZGLOBAL1_Operation.SetAttrFromStrByContext( wXferO, "Root", "WorkDateTime", "201608040100000", "YYYYMMDDHHMMSSS" );
            // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
           }
           //:SetAttrFromStrByContext( wXferO, "Root", "WorkDateTime2", "201608042230000", "YYYYMMDDHHMMSSS" )
           {
            ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
            m_ZGLOBAL1_Operation.SetAttrFromStrByContext( wXferO, "Root", "WorkDateTime2", "201608042230000", "YYYYMMDDHHMMSSS" );
            // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
           }
           //:IF wXferO.Root.WorkDateTime > wXferO.Root.WorkDateTime2
           if ( CompareAttributeToAttribute( wXferO, "Root", "WorkDateTime", wXferO, "Root", "WorkDateTime2" ) > 0 )
           {
              MessageSend( ViewToWindow, "", "Save", "End Date/Time is not later than Start Date/Time.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
              //:RETURN 2
              if(8==8)return( 2 );
           }

           {
                ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
                m_ZGLOBAL1_Operation.SetAttrFromStrByContext( wXferO, "Root", "WorkDateTime", "201608040100000", "YYYYMMDDHHMMSSS" );
                // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
           }
               //:SetAttrFromStrByContext( wXferO, "Root", "WorkDateTime2", "201608042230000", "YYYYMMDDHHMMSSS" )
               {
                ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
                m_ZGLOBAL1_Operation.SetAttrFromStrByContext( wXferO, "Root", "WorkDateTime2", "201608040100000", "YYYYMMDDHHMMSSS" );
                // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
               }


               if ( CompareAttributeToAttribute( wXferO, "Root", "WorkDateTime", wXferO, "Root", "WorkDateTime2" ) > 0 )
               {
                  MessageSend( ViewToWindow, "", "Save", "End Date/Time is not later than Start Date/Time.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:RETURN 2
                  if(8==8)return( 2 );
               }

           return( 0 );
        }

        public int
        testRelinking( View ViewToWindow )
        {
               zVIEW    mSAAdmin = new zVIEW( );
               int      RESULT = 0;
               //:VIEW mSAAdmin2   BASED ON LOD  mSAAdmin
               zVIEW    mSAAdmin2 = new zVIEW( );
               //:VIEW lSATermLST  REGISTERED AS lSATermLST
               zVIEW    lSATermLST = new zVIEW( );
               //:VIEW lSATermLST2 BASED ON LOD  lTermLST
               zVIEW    lSATermLST2 = new zVIEW( );
               //:VIEW lTermLST    BASED ON LOD  lTermLST
               zVIEW    lTermLST = new zVIEW( );
               //:INTEGER TermID
               int      TermID = 0;
               String   szTempString_0 = null;
               int      lTempInteger_0 = 0;
               zVIEW    vTempViewVar_0 = new zVIEW( );
               int      lTempInteger_1 = 0;
               String   szTempString_1 = null;
               String   szTempString_2 = null;
               String   szTempString_3 = null;
               String   szTempString_4 = null;
               int      lTempInteger_2 = 0;
               zVIEW    vTempViewVar_1 = new zVIEW( );
               zVIEW    vTempViewVar_2 = new zVIEW( );
               int      lTempInteger_3 = 0;

               // After doing a couple of relinks, it appears that the relink fails (or relinks the opposite of how it's supposed to).

                  ActivateOI_FromFile( mSAAdmin, "mSAAdmin", ViewToWindow, "target/test-classes/testdata//ZENCAs/mSAAdmin.por", zSINGLE );
                  ActivateOI_FromFile( lSATermLST, "lTermLST", ViewToWindow, "target/test-classes/testdata//ZENCAs/lSATermsLST.por", zMULTIPLE );
                  RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", 175, "" );
               //RESULT = GetViewByName( mSAAdmin, "mSAAdmin", ViewToWindow, zLEVEL_TASK );
               //RESULT = GetViewByName( lSATermLST, "lSATermLST", ViewToWindow, zLEVEL_TASK );


                  //:// Set the selected College Term as "Current" after making sure all others are NOT Current.
                  //:TermID = mSAAdmin.CollegeTerm.ID
                  {MutableInt mi_TermID = new MutableInt( TermID );
                      GetIntegerFromAttribute( mi_TermID, mSAAdmin, "CollegeTerm", "ID" );
                  TermID = mi_TermID.intValue( );}

                  //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = 175
               o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, 175 );
                  RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zSINGLE );
                  DropView( vTempViewVar_0 );
                  //:lTermLST.CollegeTerm.CurrentForStudentAccounts = ""
                  SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "" );
                  //:COMMIT lTermLST
                  RESULT = CommitObjectInstance( lTermLST );
                  //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = lTermLST.CollegeTerm.ID
                  {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                      GetIntegerFromAttribute( mi_lTempInteger_0, lTermLST, "CollegeTerm", "ID" );
                  lTempInteger_0 = mi_lTempInteger_0.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", lTempInteger_0, "" );

                  // Relink the the instances.
                  RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
               szTempString_1 = lTermLST.cursor( "CollegeTerm" ).getAttribute("CurrentForStudentAccounts").getString();
                  //:DropObjectInstance( lTermLST )
                  DropObjectInstance( lTermLST );
                  //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = 176
                  RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", 176, "" );
                  //:// Then set Term on which we're currently positioned to Current.
                  //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = mSAAdmin.CollegeTerm.ID
                  {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                      GetIntegerFromAttribute( mi_lTempInteger_1, mSAAdmin, "CollegeTerm", "ID" );
                  lTempInteger_1 = mi_lTempInteger_1.intValue( );}
               o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_1, lTempInteger_1 );
                  RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_1, zSINGLE );
                  DropView( vTempViewVar_1 );
                  //:lTermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
                  SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" );
                  //:COMMIT lTermLST
                  RESULT = CommitObjectInstance( lTermLST );
                  //:RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" )
                  RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
                  //:DropObjectInstance( lTermLST )
                  DropObjectInstance( lTermLST );

                  //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = 176
               o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_2, 176 );
                  RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_2, zSINGLE );
                  DropView( vTempViewVar_2 );
                  //:lTermLST.CollegeTerm.CurrentForStudentAccounts = ""
                  SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "" );
                  //:COMMIT lTermLST
                  RESULT = CommitObjectInstance( lTermLST );
                  //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = lTermLST.CollegeTerm.ID
                  {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                      GetIntegerFromAttribute( mi_lTempInteger_2, lTermLST, "CollegeTerm", "ID" );
                  lTempInteger_2 = mi_lTempInteger_2.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", lTempInteger_2, "" );
                  //:RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" )
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b

               szTempString_1 = lTermLST.cursor( "CollegeTerm" ).getAttribute("CurrentForStudentAccounts").getString();
               szTempString_2 = mSAAdmin.cursor( "CollegeTerm" ).getAttribute("CurrentForStudentAccounts").getString();

<<<<<<< HEAD
		   	   RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
		   	   // ERROR OCCURS HERE?????
		   	   // lTermLST and mSAAdmin CollegeTerm.CurrentForStudentAccounts should be "" not "Y"
		   	szTempString_1 = lTermLST.cursor( "CollegeTerm" ).getAttribute("CurrentForStudentAccounts").getString();
	 		   //Assert.assertEquals("HELP", "",szTempString_1 );
		   	if (szTempString_1.equals("Y"))
	 	      Assert.assertTrue( "lTermLST/mSAAdmin CollegeTerm.CurrentForStudentAccounts should be blank not Y. ", 1 == 0 );

		   	   //:DropObjectInstance( lTermLST )
		   	   DropObjectInstance( lTermLST );

		   	   /*
		   	   //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = 175
		   	   RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", 175, "" );
		   	   //:// Then set Term on which we're currently positioned to Current.
		   	   //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = mSAAdmin.CollegeTerm.ID
		   	   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
		   	       GetIntegerFromAttribute( mi_lTempInteger_3, mSAAdmin, "CollegeTerm", "ID" );
		   	   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
		   	o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_2, lTempInteger_3 );
		   	   RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_2, zSINGLE );
		   	   DropView( vTempViewVar_2 );
		   	   //:lTermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
		   	   SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" );
		   	   //:COMMIT lTermLST
		   	   RESULT = CommitObjectInstance( lTermLST );
		   	   //:RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" )
		   	   RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
		   	   //:DropObjectInstance( lTermLST )
		   	   DropObjectInstance( lTermLST );
		   	   */
		   	   return( 0 );
		}


		public int
		testRelinkingOrig( View ViewToWindow )
		{
			   zVIEW    mSAAdmin = new zVIEW( );
			   int      RESULT = 0;
			   //:VIEW mSAAdmin2   BASED ON LOD  mSAAdmin
			   zVIEW    mSAAdmin2 = new zVIEW( );
			   //:VIEW lSATermLST  REGISTERED AS lSATermLST
			   zVIEW    lSATermLST = new zVIEW( );
			   //:VIEW lSATermLST2 BASED ON LOD  lTermLST
			   zVIEW    lSATermLST2 = new zVIEW( );
			   //:VIEW lTermLST    BASED ON LOD  lTermLST
			   zVIEW    lTermLST = new zVIEW( );
			   //:INTEGER TermID
			   int      TermID = 0;
			   String   szTempString_0 = null;
			   int      lTempInteger_0 = 0;
			   zVIEW    vTempViewVar_0 = new zVIEW( );
			   int      lTempInteger_1 = 0;
			   String   szTempString_1 = null;
			   String   szTempString_2 = null;
			   String   szTempString_3 = null;
			   String   szTempString_4 = null;
			   int      lTempInteger_2 = 0;
			   zVIEW    vTempViewVar_1 = new zVIEW( );
			   int      lTempInteger_3 = 0;

		   	   ActivateOI_FromFile( mSAAdmin, "mSAAdmin", ViewToWindow, "target/test-classes/testdata//ZENCAs/mSAAdmin.por", zSINGLE );
		   	   ActivateOI_FromFile( lSATermLST, "lTermLST", ViewToWindow, "target/test-classes/testdata//ZENCAs/lSATermsLST.por", zMULTIPLE );
		   	   RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", 175, "" );
			   //RESULT = GetViewByName( mSAAdmin, "mSAAdmin", ViewToWindow, zLEVEL_TASK );
			   //RESULT = GetViewByName( lSATermLST, "lSATermLST", ViewToWindow, zLEVEL_TASK );

			   //:// Set the selected College Term as "Current" after making sure all others are NOT Current.
			   //:TermID = mSAAdmin.CollegeTerm.ID
			   {MutableInt mi_TermID = new MutableInt( TermID );
			       GetIntegerFromAttribute( mi_TermID, mSAAdmin, "CollegeTerm", "ID" );
			   TermID = mi_TermID.intValue( );}

			   //:// First make sure others are NOT Current.
			   //:FOR EACH lSATermLST.CollegeTerm
			   RESULT = SetCursorFirstEntity( lSATermLST, "CollegeTerm", "" );
			   while ( RESULT > zCURSOR_UNCHANGED )
			   {
			      //:TraceLineS("In loop.  Pointer on: ", lSATermLST.CollegeTerm.YearSemester)
			      {StringBuilder sb_szTempString_0;
			      if ( szTempString_0 == null )
			         sb_szTempString_0 = new StringBuilder( 32 );
			      else
			         sb_szTempString_0 = new StringBuilder( szTempString_0 );
			             GetStringFromAttribute( sb_szTempString_0, lSATermLST, "CollegeTerm", "YearSemester" );
			      szTempString_0 = sb_szTempString_0.toString( );}
			      TraceLineS( "In loop.  Pointer on: ", szTempString_0 );
			      //:IF lSATermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
			      if ( CompareAttributeToString( lSATermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" ) == 0 )
			      {
			         //:TraceLineS("In loop.  This one is marked as current ", "")
			         TraceLineS( "In loop.  This one is marked as current ", "" );
			         //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = lSATermLST.CollegeTerm.ID
			         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
			                   GetIntegerFromAttribute( mi_lTempInteger_0, lSATermLST, "CollegeTerm", "ID" );
			         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
			         o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
			         RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zSINGLE );
			         DropView( vTempViewVar_0 );
			         if (RESULT >= 0)
			         {
			         //:lTermLST.CollegeTerm.CurrentForStudentAccounts = ""
			         SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "" );

			         //:COMMIT lTermLST
			         RESULT = CommitObjectInstance( lTermLST );

			         //:// Relink the changed values into the current Annual Admin object, if the CollegeTerm is there.
			         //://CreateViewFromView( mSAAdmin2, mSAAdmin )

			         //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = lTermLST.CollegeTerm.ID
			         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
			                   GetIntegerFromAttribute( mi_lTempInteger_1, lTermLST, "CollegeTerm", "ID" );
			         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
			         RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", lTempInteger_1, "" );
			         //:IF RESULT >= zCURSOR_SET
			         if ( RESULT >= zCURSOR_SET )
			         {
			            //:TraceLineS("In relink.  Pointer on: ", lTermLST.CollegeTerm.YearSemester)
			            {StringBuilder sb_szTempString_1;
			            if ( szTempString_1 == null )
			               sb_szTempString_1 = new StringBuilder( 32 );
			            else
			               sb_szTempString_1 = new StringBuilder( szTempString_1 );
			                         GetStringFromAttribute( sb_szTempString_1, lTermLST, "CollegeTerm", "YearSemester" );
			            szTempString_1 = sb_szTempString_1.toString( );}
			            TraceLineS( "In relink.  Pointer on: ", szTempString_1 );
			            //:TraceLineS("In relink.  Current: ", lTermLST.CollegeTerm.CurrentForStudentAccounts)
			            {StringBuilder sb_szTempString_2;
			            if ( szTempString_2 == null )
			               sb_szTempString_2 = new StringBuilder( 32 );
			            else
			               sb_szTempString_2 = new StringBuilder( szTempString_2 );
			                         GetStringFromAttribute( sb_szTempString_2, lTermLST, "CollegeTerm", "CurrentForStudentAccounts" );
			            szTempString_2 = sb_szTempString_2.toString( );}
			            TraceLineS( "In relink.  Current: ", szTempString_2 );
			            //:RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" )
			            RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
			            //: TraceLineS("In relink.  After Value: ", mSAAdmin.CollegeTerm.YearSemester)
			            {StringBuilder sb_szTempString_3;
			            if ( szTempString_3 == null )
			               sb_szTempString_3 = new StringBuilder( 32 );
			            else
			               sb_szTempString_3 = new StringBuilder( szTempString_3 );
			                         GetStringFromAttribute( sb_szTempString_3, mSAAdmin, "CollegeTerm", "YearSemester" );
			            szTempString_3 = sb_szTempString_3.toString( );}
			            TraceLineS( "In relink.  After Value: ", szTempString_3 );
			            //: TraceLineS("In relink.  After Value current: ", mSAAdmin.CollegeTerm.CurrentForStudentAccounts)
			            {StringBuilder sb_szTempString_4;
			            if ( szTempString_4 == null )
			               sb_szTempString_4 = new StringBuilder( 32 );
			            else
			               sb_szTempString_4 = new StringBuilder( szTempString_4 );
			                         GetStringFromAttribute( sb_szTempString_4, mSAAdmin, "CollegeTerm", "CurrentForStudentAccounts" );
			            szTempString_4 = sb_szTempString_4.toString( );}
			            TraceLineS( "In relink.  After Value current: ", szTempString_4 );
			         }

			         //:END
			         //://DropView( mSAAdmin2 )
			         //:DropObjectInstance( lTermLST )
			         DropObjectInstance( lTermLST );
			         }
			      }

			      RESULT = SetCursorNextEntity( lSATermLST, "CollegeTerm", "" );
			      //:END
			   }

			   //:END
			   //:COMMIT lSATermLST
			   RESULT = CommitObjectInstance( lSATermLST );
			   //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = TermID
			   RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", TermID, "" );
			   //:// Then set Term on which we're currently positioned to Current.
			   //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = mSAAdmin.CollegeTerm.ID
			   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
			       GetIntegerFromAttribute( mi_lTempInteger_2, mSAAdmin, "CollegeTerm", "ID" );
			   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
			   o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_1, lTempInteger_2 );
			   RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_1, zSINGLE );
			   DropView( vTempViewVar_1 );
			   //:lTermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
			   SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" );
			   //:SET CURSOR FIRST lSATermLST.CollegeTerm WHERE lSATermLST.CollegeTerm.ID = mSAAdmin.CollegeTerm.ID
			   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
			       GetIntegerFromAttribute( mi_lTempInteger_3, mSAAdmin, "CollegeTerm", "ID" );
			   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
			   RESULT = SetCursorFirstEntityByInteger( lSATermLST, "CollegeTerm", "ID", lTempInteger_3, "" );
			   //:lSATermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
			   SetAttributeFromString( lSATermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" );
			   //:COMMIT lTermLST
			   RESULT = CommitObjectInstance( lTermLST );
			   //:RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" )
			   RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
			   //:DropObjectInstance( lTermLST )
			   DropObjectInstance( lTermLST );

/******************************************************************************/
			   // Now do the whole thing over for a different term.
		   	   RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", 176, "" );
			   {MutableInt mi_TermID = new MutableInt( TermID );
		       GetIntegerFromAttribute( mi_TermID, mSAAdmin, "CollegeTerm", "ID" );

		   TermID = mi_TermID.intValue( );}

		   //:// First make sure others are NOT Current.
		   //:FOR EACH lSATermLST.CollegeTerm
			   //:// First make sure others are NOT Current.
			   //:FOR EACH lSATermLST.CollegeTerm
			   RESULT = SetCursorFirstEntity( lSATermLST, "CollegeTerm", "" );
			   while ( RESULT > zCURSOR_UNCHANGED )
			   {
			      //:TraceLineS("In loop.  Pointer on: ", lSATermLST.CollegeTerm.YearSemester)
			      {StringBuilder sb_szTempString_0;
			      if ( szTempString_0 == null )
			         sb_szTempString_0 = new StringBuilder( 32 );
			      else
			         sb_szTempString_0 = new StringBuilder( szTempString_0 );
			             GetStringFromAttribute( sb_szTempString_0, lSATermLST, "CollegeTerm", "YearSemester" );
			      szTempString_0 = sb_szTempString_0.toString( );}
			      TraceLineS( "In loop.  Pointer on: ", szTempString_0 );
			      //:IF lSATermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
			      if ( CompareAttributeToString( lSATermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" ) == 0 )
			      {
			         //:TraceLineS("In loop.  This one is marked as current ", "")
			         TraceLineS( "In loop.  This one is marked as current ", "" );
			         //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = lSATermLST.CollegeTerm.ID
			         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
			                   GetIntegerFromAttribute( mi_lTempInteger_0, lSATermLST, "CollegeTerm", "ID" );
			         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
			         o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
			         RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zSINGLE );
			         DropView( vTempViewVar_0 );
			         if (RESULT >= 0 )
			         {
			         //:lTermLST.CollegeTerm.CurrentForStudentAccounts = ""
			         SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "" );

			         //:COMMIT lTermLST
			         RESULT = CommitObjectInstance( lTermLST );

			         //:// Relink the changed values into the current Annual Admin object, if the CollegeTerm is there.
			         //://CreateViewFromView( mSAAdmin2, mSAAdmin )

			         //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = lTermLST.CollegeTerm.ID
			         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
			                   GetIntegerFromAttribute( mi_lTempInteger_1, lTermLST, "CollegeTerm", "ID" );
			         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
			         RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", lTempInteger_1, "" );
			         //:IF RESULT >= zCURSOR_SET
			         if ( RESULT >= zCURSOR_SET )
			         {
			            //:TraceLineS("In relink.  Pointer on: ", lTermLST.CollegeTerm.YearSemester)
			            {StringBuilder sb_szTempString_1;
			            if ( szTempString_1 == null )
			               sb_szTempString_1 = new StringBuilder( 32 );
			            else
			               sb_szTempString_1 = new StringBuilder( szTempString_1 );
			                         GetStringFromAttribute( sb_szTempString_1, lTermLST, "CollegeTerm", "YearSemester" );
			            szTempString_1 = sb_szTempString_1.toString( );}
			            TraceLineS( "In relink.  Pointer on: ", szTempString_1 );
			            //:TraceLineS("In relink.  Current: ", lTermLST.CollegeTerm.CurrentForStudentAccounts)
			            {StringBuilder sb_szTempString_2;
			            if ( szTempString_2 == null )
			               sb_szTempString_2 = new StringBuilder( 32 );
			            else
			               sb_szTempString_2 = new StringBuilder( szTempString_2 );
			                         GetStringFromAttribute( sb_szTempString_2, lTermLST, "CollegeTerm", "CurrentForStudentAccounts" );
			            szTempString_2 = sb_szTempString_2.toString( );}
			            TraceLineS( "In relink.  Current: ", szTempString_2 );
			            //:RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" )
			            RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
			            //: TraceLineS("In relink.  After Value: ", mSAAdmin.CollegeTerm.YearSemester)
			            {StringBuilder sb_szTempString_3;
			            if ( szTempString_3 == null )
			               sb_szTempString_3 = new StringBuilder( 32 );
			            else
			               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                        //GetStringFromAttribute( sb_szTempString_3, mSAAdmin, "CollegeTerm", "YearSemester" );
                        GetStringFromAttribute( sb_szTempString_3, lTermLST, "CollegeTerm", "CurrentForStudentAccounts" );
                        //!!!!!!!!!!!!!
			            szTempString_3 = sb_szTempString_3.toString( );}
			            if (szTempString_3.equals("Y"))
			            	Assert.assertEquals("This should be blank, not 'Y'!!", szTempString_3, "" );
			            //: TraceLineS("In relink.  After Value current: ", mSAAdmin.CollegeTerm.CurrentForStudentAccounts)
			            {StringBuilder sb_szTempString_4;
			            if ( szTempString_4 == null )
			               sb_szTempString_4 = new StringBuilder( 32 );
			            else
			               sb_szTempString_4 = new StringBuilder( szTempString_4 );
			                         GetStringFromAttribute( sb_szTempString_4, mSAAdmin, "CollegeTerm", "CurrentForStudentAccounts" );
			            szTempString_4 = sb_szTempString_4.toString( );}
			            TraceLineS( "In relink.  After Value current: ", szTempString_4 );
			         }

			         //:END
			         //://DropView( mSAAdmin2 )
			         //:DropObjectInstance( lTermLST )
			         DropObjectInstance( lTermLST );
			         }
			      }

			      RESULT = SetCursorNextEntity( lSATermLST, "CollegeTerm", "" );
			      //:END
			   }

			   //:END
			   //:COMMIT lSATermLST
			   RESULT = CommitObjectInstance( lSATermLST );
			   //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = TermID
			   RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", TermID, "" );
			   //:// Then set Term on which we're currently positioned to Current.
			   //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = mSAAdmin.CollegeTerm.ID
			   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
			       GetIntegerFromAttribute( mi_lTempInteger_2, mSAAdmin, "CollegeTerm", "ID" );
			   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
			   o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_1, lTempInteger_2 );
			   RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_1, zSINGLE );
			   DropView( vTempViewVar_1 );
			   //:lTermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
			   SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" );
			   //:SET CURSOR FIRST lSATermLST.CollegeTerm WHERE lSATermLST.CollegeTerm.ID = mSAAdmin.CollegeTerm.ID
			   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
			       GetIntegerFromAttribute( mi_lTempInteger_3, mSAAdmin, "CollegeTerm", "ID" );
			   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
			   RESULT = SetCursorFirstEntityByInteger( lSATermLST, "CollegeTerm", "ID", lTempInteger_3, "" );
			   //:lSATermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
			   SetAttributeFromString( lSATermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" );
			   //:COMMIT lTermLST
			   RESULT = CommitObjectInstance( lTermLST );
			   //:RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" )
			   RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
			   //:DropObjectInstance( lTermLST )
			   DropObjectInstance( lTermLST );
			   return( 0 );
		}

		private int
		o_fnLocalBuildQual_3( View     vSubtask,
		                      zVIEW    vQualObject,
		                      int      lTempInteger_0 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeTerm" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		private int
		o_fnLocalBuildlTermWithNullValue( View     vSubtask,
		                      zVIEW    vQualObject,
		                      int      lTempInteger_0 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeTerm" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "CurrentForStudentAccounts" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		public int
		testExclInclOrderEntities( View ViewToWindow )
		{
		   zVIEW    mConListTest = new zVIEW( );
		   zVIEW    mConListTest2 = new zVIEW( );
		   zVIEW    mSAProf      = new zVIEW( );
		   int      RESULT = 0;

		   // KJS - After we exclude and include the entity Class in mConList, when we try do do the OrderEntityForView on a
		   // derived attribute, in the derived attribute code, we receive the following error:
		   // com.quinsoft.zeidon.NullCursorException: Cursor for entity is null
		   // EntityDef  = ZENCAs.mConList.ClassCourse
		   // I loop through the entities, also do other order entities, which are all ok. Only the ordering of dName causes issue.

		   ActivateOI_FromFile( mConListTest, "mConList", ViewToWindow, "target/test-classes/testdata//ZENCAs/mConListL.por", zSINGLE );
		   ActivateOI_FromFile( mConListTest2, "mConList", ViewToWindow, "target/test-classes/testdata//ZENCAs/mConListL.por", zSINGLE );
		   //:NAME VIEW mSAProfEList "mSAProfEList"
		   SetNameForView( mConListTest, "mConListTest", null, zLEVEL_TASK );
		   SetNameForView( mConListTest2, "mConListTest2", null, zLEVEL_TASK );
		   RESULT= mConListTest.cursor("Class").setFirst( "ID", 3601 ).toInt();
		   RESULT= mConListTest2.cursor("Class").setFirst( "ID", 3601 ).toInt();
		   OrderEntityForView( mConListTest, "Class", "dName A" ); //CourseTitle
		   RESULT = ExcludeEntity( mConListTest, "Class", zREPOS_AFTER );
		   RESULT = IncludeSubobjectFromSubobject( mConListTest, "Class", mConListTest2, "Class", zPOS_AFTER );
		   RESULT = mConListTest.cursor("Class").setFirst().toInt();
      		while ( RESULT > zCURSOR_UNCHANGED )
      		{
      			String str1 =  mConListTest2.cursor("ClassCourse").getAttribute("Title").getString();
      			String str2 =  mConListTest2.cursor("Class").getAttribute("dName").getString();
		        RESULT = mConListTest.cursor("Class").setNext().toInt();
      		}
 		   OrderEntityForView( mConListTest, "Class", "ClassCourse.Title A" );
		   OrderEntityForView( mConListTest, "Class", "CourseTitle A" );
		   // All of above code seems fine, but we crash in the derived attribute code of dName.
		   OrderEntityForView( mConListTest, "Class", "dName A" );
			return 0;
		}

		public int
		testSpawning1( View ViewToWindow )
		{
		   zVIEW    mFAProf1      = new zVIEW( );
		   zVIEW    mFAProf2      = new zVIEW( );
		   zVIEW    mClass      = new zVIEW( );
		   zVIEW   vTempViewVar_0 = new zVIEW();
		   int RESULT=0;
		   double dAmount=0;

		   ActivateOI_FromFile( mFAProf1, "mFAProf", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFAProfO.por", zSINGLE );
		   SetNameForView( mFAProf1, "mFAProf1", null, zLEVEL_TASK );
		   mFAProf1.cursor("FinAidAward").setFirst().toInt();
		   ActivateOI_FromFile( mFAProf2, "mFAProf", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFAProfO.por", zSINGLE );
		   SetNameForView( mFAProf2, "mFAProf2", null, zLEVEL_TASK );
		   mFAProf2.cursor("FinAidAward").setFirst().toInt();

		   RelinkInstanceToInstance( mFAProf1, "FinAidAward", mFAProf2, "FinAidAward");

	       mFAProf1.cursor("FinAidAwardDisbursement").createEntity();
	       mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 123) ;
	       mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 321) ;

	       // Check spawning after createEntity
	       RESULT= mFAProf2.cursor("FinAidAwardDisbursement").setFirst( "Amount", 123 ).toInt();
 		   dAmount = mFAProf2.cursor("FinAidAwardDisbursement").getAttribute("Amount").getDouble();
 		   Assert.assertEquals("mFAProf2.FinAidAwardDisbursement not correctly spawned after createEntity.", 123.0, dAmount, 0.1);
 		   dAmount = mFAProf2.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").getDouble();
 		   Assert.assertEquals("mFAProf2.FinAidAwardDisbursement not correctly spawned after createEntity.", 321.0, dAmount, 0.1);

		   RESULT = IncludeSubobjectFromSubobject( mFAProf1, "FinAidAwardPeriod", mFAProf1, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
		   RESULT = CheckExistenceOfEntity( mFAProf2, "FinAidAwardPeriod");
	       // Check spawning after Include entity
 		   Assert.assertEquals("mFAProf2.FinAidAwardPeriod not correctly spawned after include.", 0, RESULT );


 		   mFAProf1.cursor("FinAidAwardDisbursement").createTemporalEntity();
	       mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 234) ;
	       mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 432) ;
	       mFAProf1.cursor("FinAidAwardDisbursement").acceptTemporalEntity();

	       // Check spawning after createTemporalEntity and acceptTemporalEntity
           RESULT= mFAProf2.cursor("FinAidAwardDisbursement").setCursor( mFAProf1.cursor("FinAidAwardDisbursement") ).toInt();
 		   dAmount = mFAProf2.cursor("FinAidAwardDisbursement").getAttribute("Amount").getDouble();
 		   Assert.assertEquals("mFAProf2.FinAidAwardDisbursement not correctly spawned after createEntity.", 234.0, dAmount, 0.1);
 		   // I am assuming in above assert that when we create an entity and accept in mFAProf1 we would be on
 		   // positioned on that entity in mFAProf2.  In case that wouldn't be the case, I have the below code that
 		   // would be used instead.
	       RESULT = mFAProf2.cursor("FinAidAwardDisbursement").setFirst("Amount", 234).toInt();
 		   Assert.assertEquals("FinAidAwardDisbursement not correctly spawned after createTemporal.", 0, RESULT);


           // KJS Adding new test because using createTemporalEntity, after doing an include then cancelSubobject, the
 		   // link on the included entity was not broken. At perygrene, we get an error when doing the last createTemporalEntity but
 		   // of course here I'm not getting it...
 		   mFAProf2.drop();
	       o_fnLocalBuildmClass( ViewToWindow, vTempViewVar_0, 31967 );

	       RESULT = ActivateObjectInstance( mClass, "mClass", ViewToWindow, vTempViewVar_0, zSINGLE );
	       DropView( vTempViewVar_0 );
		   SetNameForView( mClass, "mClass", null, zLEVEL_TASK );

	       mFAProf1.cursor("FinAidAwardDisbursement").createEntity();
	       mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 123) ;
	       mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 321) ;
		   RESULT = IncludeSubobjectFromSubobject( mFAProf1, "FADisbursementClass", mClass, "Class", zPOS_AFTER );

	       mFAProf1.cursor("FinAidAwardDisbursement").createTemporalEntity();
	       mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 234) ;
	       mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 432) ;
		   RESULT = IncludeSubobjectFromSubobject( mFAProf1, "FADisbursementClass", mClass, "Class", zPOS_AFTER );
	       mFAProf1.cursor("FinAidAwardDisbursement").cancelSubobject();
	       // Aaaarrrrgggghhhh! At perygrene, we have a case where doing the following creates an error. Why do we not get that here???
	       mFAProf1.cursor("FinAidAwardDisbursement").createTemporalSubobjectVersion();

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
              szYearSemester = mFAProf.cursor( "CollegeTerm" ).getAttribute("YearSemester").getString();
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
              szType = lTrnscpt.cursor( "MajorCollege" ).getAttribute("Type").getString();
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
			   szTime = mCRStdPLST.cursor("ClassRoomSession").getAttribute("StartTime").getString();
			   szTime = mCRStdPLST.cursor("ClassRoomSession").getAttribute("StartTime").getString("HH:MM:SS.S");

		       RESULT = mCRStdPLST.cursor( "ClassRoomStandardSchedule" ).setFirst( "ID","381").toInt();
		       RESULT = mCRStdPLST.cursor( "ClassRoomSession" ).setFirst( "ID","1021","ClassRoomStandardSchedule").toInt();
		       RESULT = mCRStdPLST2.cursor( "ClassRoomStandardSchedule" ).setFirst( "ID","396").toInt();
		       RESULT = mCRStdPLST2.cursor( "ClassRoomSession" ).setFirst( "ID","2069", "ClassRoomStandardSchedule").toInt();
			   RESULT = CompareAttributeToAttribute( mCRStdPLST2, "ClassRoomSession", "StartTime", mCRStdPLST, "ClassRoomSession", "StartTime" );
	   		   Assert.assertEquals("Error comparing times do not equal ", 0, RESULT);
	   		   // Set StartTime
	   		   mCRStdPLST2.cursor("ClassRoomSession").getAttribute("StartTime").setValue("08:05 AM", "HH:MM AM");
			   szTime = mCRStdPLST2.cursor("ClassRoomSession").getAttribute("StartTime").getString("HH:MM AM");
	   		   mCRStdPLST2.cursor("ClassRoomSession").getAttribute("StartTime").setValue("01:45 PM", "HH:MM AM");
			   mCRStdPLST2.commit();
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
=======
                  RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
                  // ERROR OCCURS HERE?????
                  // lTermLST and mSAAdmin CollegeTerm.CurrentForStudentAccounts should be "" not "Y"
               szTempString_1 = lTermLST.cursor( "CollegeTerm" ).getAttribute("CurrentForStudentAccounts").getString();
                //Assert.assertEquals("HELP", "",szTempString_1 );
               if (szTempString_1.equals("Y"))
               Assert.assertTrue( "lTermLST/mSAAdmin CollegeTerm.CurrentForStudentAccounts should be blank not Y. ", 1 == 0 );

                  //:DropObjectInstance( lTermLST )
                  DropObjectInstance( lTermLST );

                  /*
                  //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = 175
                  RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", 175, "" );
                  //:// Then set Term on which we're currently positioned to Current.
                  //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = mSAAdmin.CollegeTerm.ID
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                      GetIntegerFromAttribute( mi_lTempInteger_3, mSAAdmin, "CollegeTerm", "ID" );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );}
               o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_2, lTempInteger_3 );
                  RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_2, zSINGLE );
                  DropView( vTempViewVar_2 );
                  //:lTermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
                  SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" );
                  //:COMMIT lTermLST
                  RESULT = CommitObjectInstance( lTermLST );
                  //:RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" )
                  RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
                  //:DropObjectInstance( lTermLST )
                  DropObjectInstance( lTermLST );
                  */
                  return( 0 );
        }


        public int
        testRelinkingOrig( View ViewToWindow )
        {
               zVIEW    mSAAdmin = new zVIEW( );
               int      RESULT = 0;
               //:VIEW mSAAdmin2   BASED ON LOD  mSAAdmin
               zVIEW    mSAAdmin2 = new zVIEW( );
               //:VIEW lSATermLST  REGISTERED AS lSATermLST
               zVIEW    lSATermLST = new zVIEW( );
               //:VIEW lSATermLST2 BASED ON LOD  lTermLST
               zVIEW    lSATermLST2 = new zVIEW( );
               //:VIEW lTermLST    BASED ON LOD  lTermLST
               zVIEW    lTermLST = new zVIEW( );
               //:INTEGER TermID
               int      TermID = 0;
               String   szTempString_0 = null;
               int      lTempInteger_0 = 0;
               zVIEW    vTempViewVar_0 = new zVIEW( );
               int      lTempInteger_1 = 0;
               String   szTempString_1 = null;
               String   szTempString_2 = null;
               String   szTempString_3 = null;
               String   szTempString_4 = null;
               int      lTempInteger_2 = 0;
               zVIEW    vTempViewVar_1 = new zVIEW( );
               int      lTempInteger_3 = 0;

                  ActivateOI_FromFile( mSAAdmin, "mSAAdmin", ViewToWindow, "target/test-classes/testdata//ZENCAs/mSAAdmin.por", zSINGLE );
                  ActivateOI_FromFile( lSATermLST, "lTermLST", ViewToWindow, "target/test-classes/testdata//ZENCAs/lSATermsLST.por", zMULTIPLE );
                  RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", 175, "" );
               //RESULT = GetViewByName( mSAAdmin, "mSAAdmin", ViewToWindow, zLEVEL_TASK );
               //RESULT = GetViewByName( lSATermLST, "lSATermLST", ViewToWindow, zLEVEL_TASK );

               //:// Set the selected College Term as "Current" after making sure all others are NOT Current.
               //:TermID = mSAAdmin.CollegeTerm.ID
               {MutableInt mi_TermID = new MutableInt( TermID );
                   GetIntegerFromAttribute( mi_TermID, mSAAdmin, "CollegeTerm", "ID" );
               TermID = mi_TermID.intValue( );}

               //:// First make sure others are NOT Current.
               //:FOR EACH lSATermLST.CollegeTerm
               RESULT = SetCursorFirstEntity( lSATermLST, "CollegeTerm", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               {
                  //:TraceLineS("In loop.  Pointer on: ", lSATermLST.CollegeTerm.YearSemester)
                  {StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, lSATermLST, "CollegeTerm", "YearSemester" );
                  szTempString_0 = sb_szTempString_0.toString( );}
                  TraceLineS( "In loop.  Pointer on: ", szTempString_0 );
                  //:IF lSATermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
                  if ( CompareAttributeToString( lSATermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" ) == 0 )
                  {
                     //:TraceLineS("In loop.  This one is marked as current ", "")
                     TraceLineS( "In loop.  This one is marked as current ", "" );
                     //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = lSATermLST.CollegeTerm.ID
                     {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                               GetIntegerFromAttribute( mi_lTempInteger_0, lSATermLST, "CollegeTerm", "ID" );
                     lTempInteger_0 = mi_lTempInteger_0.intValue( );}
                     o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
                     RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zSINGLE );
                     DropView( vTempViewVar_0 );
                     if (RESULT >= 0)
                     {
                     //:lTermLST.CollegeTerm.CurrentForStudentAccounts = ""
                     SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "" );

                     //:COMMIT lTermLST
                     RESULT = CommitObjectInstance( lTermLST );

                     //:// Relink the changed values into the current Annual Admin object, if the CollegeTerm is there.
                     //://CreateViewFromView( mSAAdmin2, mSAAdmin )

                     //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = lTermLST.CollegeTerm.ID
                     {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                               GetIntegerFromAttribute( mi_lTempInteger_1, lTermLST, "CollegeTerm", "ID" );
                     lTempInteger_1 = mi_lTempInteger_1.intValue( );}
                     RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", lTempInteger_1, "" );
                     //:IF RESULT >= zCURSOR_SET
                     if ( RESULT >= zCURSOR_SET )
                     {
                        //:TraceLineS("In relink.  Pointer on: ", lTermLST.CollegeTerm.YearSemester)
                        {StringBuilder sb_szTempString_1;
                        if ( szTempString_1 == null )
                           sb_szTempString_1 = new StringBuilder( 32 );
                        else
                           sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetStringFromAttribute( sb_szTempString_1, lTermLST, "CollegeTerm", "YearSemester" );
                        szTempString_1 = sb_szTempString_1.toString( );}
                        TraceLineS( "In relink.  Pointer on: ", szTempString_1 );
                        //:TraceLineS("In relink.  Current: ", lTermLST.CollegeTerm.CurrentForStudentAccounts)
                        {StringBuilder sb_szTempString_2;
                        if ( szTempString_2 == null )
                           sb_szTempString_2 = new StringBuilder( 32 );
                        else
                           sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                     GetStringFromAttribute( sb_szTempString_2, lTermLST, "CollegeTerm", "CurrentForStudentAccounts" );
                        szTempString_2 = sb_szTempString_2.toString( );}
                        TraceLineS( "In relink.  Current: ", szTempString_2 );
                        //:RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" )
                        RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
                        //: TraceLineS("In relink.  After Value: ", mSAAdmin.CollegeTerm.YearSemester)
                        {StringBuilder sb_szTempString_3;
                        if ( szTempString_3 == null )
                           sb_szTempString_3 = new StringBuilder( 32 );
                        else
                           sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                     GetStringFromAttribute( sb_szTempString_3, mSAAdmin, "CollegeTerm", "YearSemester" );
                        szTempString_3 = sb_szTempString_3.toString( );}
                        TraceLineS( "In relink.  After Value: ", szTempString_3 );
                        //: TraceLineS("In relink.  After Value current: ", mSAAdmin.CollegeTerm.CurrentForStudentAccounts)
                        {StringBuilder sb_szTempString_4;
                        if ( szTempString_4 == null )
                           sb_szTempString_4 = new StringBuilder( 32 );
                        else
                           sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                     GetStringFromAttribute( sb_szTempString_4, mSAAdmin, "CollegeTerm", "CurrentForStudentAccounts" );
                        szTempString_4 = sb_szTempString_4.toString( );}
                        TraceLineS( "In relink.  After Value current: ", szTempString_4 );
                     }

                     //:END
                     //://DropView( mSAAdmin2 )
                     //:DropObjectInstance( lTermLST )
                     DropObjectInstance( lTermLST );
                     }
                  }

                  RESULT = SetCursorNextEntity( lSATermLST, "CollegeTerm", "" );
                  //:END
               }

               //:END
               //:COMMIT lSATermLST
               RESULT = CommitObjectInstance( lSATermLST );
               //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = TermID
               RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", TermID, "" );
               //:// Then set Term on which we're currently positioned to Current.
               //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = mSAAdmin.CollegeTerm.ID
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                   GetIntegerFromAttribute( mi_lTempInteger_2, mSAAdmin, "CollegeTerm", "ID" );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );}
               o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_1, lTempInteger_2 );
               RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_1, zSINGLE );
               DropView( vTempViewVar_1 );
               //:lTermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
               SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" );
               //:SET CURSOR FIRST lSATermLST.CollegeTerm WHERE lSATermLST.CollegeTerm.ID = mSAAdmin.CollegeTerm.ID
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                   GetIntegerFromAttribute( mi_lTempInteger_3, mSAAdmin, "CollegeTerm", "ID" );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( lSATermLST, "CollegeTerm", "ID", lTempInteger_3, "" );
               //:lSATermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
               SetAttributeFromString( lSATermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" );
               //:COMMIT lTermLST
               RESULT = CommitObjectInstance( lTermLST );
               //:RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" )
               RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
               //:DropObjectInstance( lTermLST )
               DropObjectInstance( lTermLST );

/******************************************************************************/
               // Now do the whole thing over for a different term.
                  RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", 176, "" );
               {MutableInt mi_TermID = new MutableInt( TermID );
               GetIntegerFromAttribute( mi_TermID, mSAAdmin, "CollegeTerm", "ID" );

           TermID = mi_TermID.intValue( );}

           //:// First make sure others are NOT Current.
           //:FOR EACH lSATermLST.CollegeTerm
               //:// First make sure others are NOT Current.
               //:FOR EACH lSATermLST.CollegeTerm
               RESULT = SetCursorFirstEntity( lSATermLST, "CollegeTerm", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               {
                  //:TraceLineS("In loop.  Pointer on: ", lSATermLST.CollegeTerm.YearSemester)
                  {StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, lSATermLST, "CollegeTerm", "YearSemester" );
                  szTempString_0 = sb_szTempString_0.toString( );}
                  TraceLineS( "In loop.  Pointer on: ", szTempString_0 );
                  //:IF lSATermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
                  if ( CompareAttributeToString( lSATermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" ) == 0 )
                  {
                     //:TraceLineS("In loop.  This one is marked as current ", "")
                     TraceLineS( "In loop.  This one is marked as current ", "" );
                     //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = lSATermLST.CollegeTerm.ID
                     {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                               GetIntegerFromAttribute( mi_lTempInteger_0, lSATermLST, "CollegeTerm", "ID" );
                     lTempInteger_0 = mi_lTempInteger_0.intValue( );}
                     o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
                     RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_0, zSINGLE );
                     DropView( vTempViewVar_0 );
                     if (RESULT >= 0 )
                     {
                     //:lTermLST.CollegeTerm.CurrentForStudentAccounts = ""
                     SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "" );

                     //:COMMIT lTermLST
                     RESULT = CommitObjectInstance( lTermLST );

                     //:// Relink the changed values into the current Annual Admin object, if the CollegeTerm is there.
                     //://CreateViewFromView( mSAAdmin2, mSAAdmin )

                     //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = lTermLST.CollegeTerm.ID
                     {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                               GetIntegerFromAttribute( mi_lTempInteger_1, lTermLST, "CollegeTerm", "ID" );
                     lTempInteger_1 = mi_lTempInteger_1.intValue( );}
                     RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", lTempInteger_1, "" );
                     //:IF RESULT >= zCURSOR_SET
                     if ( RESULT >= zCURSOR_SET )
                     {
                        //:TraceLineS("In relink.  Pointer on: ", lTermLST.CollegeTerm.YearSemester)
                        {StringBuilder sb_szTempString_1;
                        if ( szTempString_1 == null )
                           sb_szTempString_1 = new StringBuilder( 32 );
                        else
                           sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetStringFromAttribute( sb_szTempString_1, lTermLST, "CollegeTerm", "YearSemester" );
                        szTempString_1 = sb_szTempString_1.toString( );}
                        TraceLineS( "In relink.  Pointer on: ", szTempString_1 );
                        //:TraceLineS("In relink.  Current: ", lTermLST.CollegeTerm.CurrentForStudentAccounts)
                        {StringBuilder sb_szTempString_2;
                        if ( szTempString_2 == null )
                           sb_szTempString_2 = new StringBuilder( 32 );
                        else
                           sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                     GetStringFromAttribute( sb_szTempString_2, lTermLST, "CollegeTerm", "CurrentForStudentAccounts" );
                        szTempString_2 = sb_szTempString_2.toString( );}
                        TraceLineS( "In relink.  Current: ", szTempString_2 );
                        //:RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" )
                        RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
                        //: TraceLineS("In relink.  After Value: ", mSAAdmin.CollegeTerm.YearSemester)
                        {StringBuilder sb_szTempString_3;
                        if ( szTempString_3 == null )
                           sb_szTempString_3 = new StringBuilder( 32 );
                        else
                           sb_szTempString_3 = new StringBuilder( szTempString_3 );
                        //GetStringFromAttribute( sb_szTempString_3, mSAAdmin, "CollegeTerm", "YearSemester" );
                        GetStringFromAttribute( sb_szTempString_3, lTermLST, "CollegeTerm", "CurrentForStudentAccounts" );
                        //!!!!!!!!!!!!!
                        szTempString_3 = sb_szTempString_3.toString( );}
                        if (szTempString_3.equals("Y"))
                            Assert.assertEquals("This should be blank, not 'Y'!!", szTempString_3, "" );
                        //: TraceLineS("In relink.  After Value current: ", mSAAdmin.CollegeTerm.CurrentForStudentAccounts)
                        {StringBuilder sb_szTempString_4;
                        if ( szTempString_4 == null )
                           sb_szTempString_4 = new StringBuilder( 32 );
                        else
                           sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                     GetStringFromAttribute( sb_szTempString_4, mSAAdmin, "CollegeTerm", "CurrentForStudentAccounts" );
                        szTempString_4 = sb_szTempString_4.toString( );}
                        TraceLineS( "In relink.  After Value current: ", szTempString_4 );
                     }

                     //:END
                     //://DropView( mSAAdmin2 )
                     //:DropObjectInstance( lTermLST )
                     DropObjectInstance( lTermLST );
                     }
                  }

                  RESULT = SetCursorNextEntity( lSATermLST, "CollegeTerm", "" );
                  //:END
               }

               //:END
               //:COMMIT lSATermLST
               RESULT = CommitObjectInstance( lSATermLST );
               //:SET CURSOR FIRST mSAAdmin.CollegeTerm WHERE mSAAdmin.CollegeTerm.ID = TermID
               RESULT = SetCursorFirstEntityByInteger( mSAAdmin, "CollegeTerm", "ID", TermID, "" );
               //:// Then set Term on which we're currently positioned to Current.
               //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = mSAAdmin.CollegeTerm.ID
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                   GetIntegerFromAttribute( mi_lTempInteger_2, mSAAdmin, "CollegeTerm", "ID" );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );}
               o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_1, lTempInteger_2 );
               RESULT = ActivateObjectInstance( lTermLST, "lTermLST", ViewToWindow, vTempViewVar_1, zSINGLE );
               DropView( vTempViewVar_1 );
               //:lTermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
               SetAttributeFromString( lTermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" );
               //:SET CURSOR FIRST lSATermLST.CollegeTerm WHERE lSATermLST.CollegeTerm.ID = mSAAdmin.CollegeTerm.ID
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                   GetIntegerFromAttribute( mi_lTempInteger_3, mSAAdmin, "CollegeTerm", "ID" );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( lSATermLST, "CollegeTerm", "ID", lTempInteger_3, "" );
               //:lSATermLST.CollegeTerm.CurrentForStudentAccounts = "Y"
               SetAttributeFromString( lSATermLST, "CollegeTerm", "CurrentForStudentAccounts", "Y" );
               //:COMMIT lTermLST
               RESULT = CommitObjectInstance( lTermLST );
               //:RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" )
               RelinkInstanceToInstance( mSAAdmin, "CollegeTerm", lTermLST, "CollegeTerm" );
               //:DropObjectInstance( lTermLST )
               DropObjectInstance( lTermLST );
               return( 0 );
        }

        private int
        o_fnLocalBuildQual_3( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0 )
        {
           int      RESULT = 0;

           RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
           CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
           SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeTerm" );
           CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
           SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
           SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
           SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
           SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
           return( 0 );
        }

        private int
        o_fnLocalBuildlTermWithNullValue( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0 )
        {
           int      RESULT = 0;

           RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
           CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
           SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeTerm" );
           CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
           SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
           SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
           SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
           SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
           CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
           SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
           CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
           SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
           SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "CurrentForStudentAccounts" );
           SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
           SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
           return( 0 );
        }

        public int
        testExclInclOrderEntities( View ViewToWindow )
        {
           zVIEW    mConListTest = new zVIEW( );
           zVIEW    mConListTest2 = new zVIEW( );
           zVIEW    mSAProf      = new zVIEW( );
           int      RESULT = 0;

           // KJS - After we exclude and include the entity Class in mConList, when we try do do the OrderEntityForView on a
           // derived attribute, in the derived attribute code, we receive the following error:
           // com.quinsoft.zeidon.NullCursorException: Cursor for entity is null
           // EntityDef  = ZENCAs.mConList.ClassCourse
           // I loop through the entities, also do other order entities, which are all ok. Only the ordering of dName causes issue.

           ActivateOI_FromFile( mConListTest, "mConList", ViewToWindow, "target/test-classes/testdata//ZENCAs/mConListL.por", zSINGLE );
           ActivateOI_FromFile( mConListTest2, "mConList", ViewToWindow, "target/test-classes/testdata//ZENCAs/mConListL.por", zSINGLE );
           //:NAME VIEW mSAProfEList "mSAProfEList"
           SetNameForView( mConListTest, "mConListTest", null, zLEVEL_TASK );
           SetNameForView( mConListTest2, "mConListTest2", null, zLEVEL_TASK );
           RESULT= mConListTest.cursor("Class").setFirst( "ID", 3601 ).toInt();
           RESULT= mConListTest2.cursor("Class").setFirst( "ID", 3601 ).toInt();
           OrderEntityForView( mConListTest, "Class", "dName A" ); //CourseTitle
           RESULT = ExcludeEntity( mConListTest, "Class", zREPOS_AFTER );
           RESULT = IncludeSubobjectFromSubobject( mConListTest, "Class", mConListTest2, "Class", zPOS_AFTER );
           RESULT = mConListTest.cursor("Class").setFirst().toInt();
              while ( RESULT > zCURSOR_UNCHANGED )
              {
                  String str1 =  mConListTest2.cursor("ClassCourse").getAttribute("Title").getString();
                  String str2 =  mConListTest2.cursor("Class").getAttribute("dName").getString();
                RESULT = mConListTest.cursor("Class").setNext().toInt();
              }
            OrderEntityForView( mConListTest, "Class", "ClassCourse.Title A" );
           OrderEntityForView( mConListTest, "Class", "CourseTitle A" );
           // All of above code seems fine, but we crash in the derived attribute code of dName.
           OrderEntityForView( mConListTest, "Class", "dName A" );
            return 0;
        }

        public int
        testSpawning1( View ViewToWindow )
        {
           zVIEW    mFAProf1      = new zVIEW( );
           zVIEW    mFAProf2      = new zVIEW( );
           zVIEW    mClass      = new zVIEW( );
           zVIEW   vTempViewVar_0 = new zVIEW();
           int RESULT=0;
           double dAmount=0;

           ActivateOI_FromFile( mFAProf1, "mFAProf", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFAProfO.por", zSINGLE );
           SetNameForView( mFAProf1, "mFAProf1", null, zLEVEL_TASK );
           mFAProf1.cursor("FinAidAward").setFirst().toInt();
           ActivateOI_FromFile( mFAProf2, "mFAProf", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFAProfO.por", zSINGLE );
           SetNameForView( mFAProf2, "mFAProf2", null, zLEVEL_TASK );
           mFAProf2.cursor("FinAidAward").setFirst().toInt();

           RelinkInstanceToInstance( mFAProf1, "FinAidAward", mFAProf2, "FinAidAward");

           mFAProf1.cursor("FinAidAwardDisbursement").createEntity();
           mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 123) ;
           mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 321) ;

           // Check spawning after createEntity
           RESULT= mFAProf2.cursor("FinAidAwardDisbursement").setFirst( "Amount", 123 ).toInt();
            dAmount = mFAProf2.cursor("FinAidAwardDisbursement").getAttribute("Amount").getDouble();
            Assert.assertEquals("mFAProf2.FinAidAwardDisbursement not correctly spawned after createEntity.", 123.0, dAmount, 0.1);
            dAmount = mFAProf2.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").getDouble();
            Assert.assertEquals("mFAProf2.FinAidAwardDisbursement not correctly spawned after createEntity.", 321.0, dAmount, 0.1);

           RESULT = IncludeSubobjectFromSubobject( mFAProf1, "FinAidAwardPeriod", mFAProf1, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
           RESULT = CheckExistenceOfEntity( mFAProf2, "FinAidAwardPeriod");
           // Check spawning after Include entity
            Assert.assertEquals("mFAProf2.FinAidAwardPeriod not correctly spawned after include.", 0, RESULT );


            mFAProf1.cursor("FinAidAwardDisbursement").createTemporalEntity();
           mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 234) ;
           mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 432) ;
           mFAProf1.cursor("FinAidAwardDisbursement").acceptTemporalEntity();

           // Check spawning after createTemporalEntity and acceptTemporalEntity
           RESULT= mFAProf2.cursor("FinAidAwardDisbursement").setCursor( mFAProf1.cursor("FinAidAwardDisbursement") ).toInt();
            dAmount = mFAProf2.cursor("FinAidAwardDisbursement").getAttribute("Amount").getDouble();
            Assert.assertEquals("mFAProf2.FinAidAwardDisbursement not correctly spawned after createEntity.", 234.0, dAmount, 0.1);
            // I am assuming in above assert that when we create an entity and accept in mFAProf1 we would be on
            // positioned on that entity in mFAProf2.  In case that wouldn't be the case, I have the below code that
            // would be used instead.
           RESULT = mFAProf2.cursor("FinAidAwardDisbursement").setFirst("Amount", 234).toInt();
            Assert.assertEquals("FinAidAwardDisbursement not correctly spawned after createTemporal.", 0, RESULT);


           // KJS Adding new test because using createTemporalEntity, after doing an include then cancelSubobject, the
            // link on the included entity was not broken. At perygrene, we get an error when doing the last createTemporalEntity but
            // of course here I'm not getting it...
            mFAProf2.drop();
           o_fnLocalBuildmClass( ViewToWindow, vTempViewVar_0, 31967 );

           RESULT = ActivateObjectInstance( mClass, "mClass", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );
           SetNameForView( mClass, "mClass", null, zLEVEL_TASK );

           mFAProf1.cursor("FinAidAwardDisbursement").createEntity();
           mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 123) ;
           mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 321) ;
           RESULT = IncludeSubobjectFromSubobject( mFAProf1, "FADisbursementClass", mClass, "Class", zPOS_AFTER );

           mFAProf1.cursor("FinAidAwardDisbursement").createTemporalEntity();
           mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("Amount").setValue( 234) ;
           mFAProf1.cursor("FinAidAwardDisbursement").getAttribute("AmountExpected").setValue( 432) ;
           RESULT = IncludeSubobjectFromSubobject( mFAProf1, "FADisbursementClass", mClass, "Class", zPOS_AFTER );
           mFAProf1.cursor("FinAidAwardDisbursement").cancelSubobject();
           // Aaaarrrrgggghhhh! At perygrene, we have a case where doing the following creates an error. Why do we not get that here???
           mFAProf1.cursor("FinAidAwardDisbursement").createTemporalSubobjectVersion();

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
              szYearSemester = mFAProf.cursor( "CollegeTerm" ).getAttribute("YearSemester").getString();
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
              szType = lTrnscpt.cursor( "MajorCollege" ).getAttribute("Type").getString();
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
               szTime = mCRStdPLST.cursor("ClassRoomSession").getAttribute("StartTime").getString();
               szTime = mCRStdPLST.cursor("ClassRoomSession").getAttribute("StartTime").getString("HH:MM:SS.S");

               RESULT = mCRStdPLST.cursor( "ClassRoomStandardSchedule" ).setFirst( "ID","381").toInt();
               RESULT = mCRStdPLST.cursor( "ClassRoomSession" ).setFirst( "ID","1021","ClassRoomStandardSchedule").toInt();
               RESULT = mCRStdPLST2.cursor( "ClassRoomStandardSchedule" ).setFirst( "ID","396").toInt();
               RESULT = mCRStdPLST2.cursor( "ClassRoomSession" ).setFirst( "ID","2069", "ClassRoomStandardSchedule").toInt();
               RESULT = CompareAttributeToAttribute( mCRStdPLST2, "ClassRoomSession", "StartTime", mCRStdPLST, "ClassRoomSession", "StartTime" );
                  Assert.assertEquals("Error comparing times do not equal ", 0, RESULT);
                  // Set StartTime
                  mCRStdPLST2.cursor("ClassRoomSession").getAttribute("StartTime").setValue("08:05 AM", "HH:MM AM");
               szTime = mCRStdPLST2.cursor("ClassRoomSession").getAttribute("StartTime").getString("HH:MM AM");
                  mCRStdPLST2.cursor("ClassRoomSession").getAttribute("StartTime").setValue("01:45 PM", "HH:MM AM");
               mCRStdPLST2.commit();
            return 0;
        }

        public int
        testDropEntityError( View     ViewToWindow )
        {
               zVIEW    mFAProf = new zVIEW( );
               zVIEW    vTempViewVar_0 = new zVIEW( );
               int         RESULT=0;
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
               int         RESULT=0;
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
               int         RESULT=0;
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

//        @Test
        public int
        testClsLstCActivateTime( View ViewToWindow )
        {
            zVIEW    lClsLstC = new zVIEW( );
            zVIEW    vTempViewVar_0 = new zVIEW( );
            int RESULT=0;

            o_BuildlClsLst2( ViewToWindow, vTempViewVar_0, 162 );
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
            RESULT = ActivateObjectInstance( lClsLstC, "lClsLstC", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            //RESULT = ActivateObjectInstance( lClsLstC, "lClsLstC", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            DropView( vTempViewVar_0 );
            //:NAME VIEW lClsLstC "lClsLstC"
            SetNameForView( lClsLstC, "lClsLstC", null, zLEVEL_TASK );
<<<<<<< HEAD
			DropView( lClsLstC );
			return 0;
		}


		public int
		testAcceptSubobject( View ViewToWindow )
		{

			zVIEW    mUser = new zVIEW( );
			//zVIEW    mPerson = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

	        View mPerson = new QualificationBuilder( zencas )
=======
            DropView( lClsLstC );
            return 0;
        }


        public int
        testAcceptSubobject( View ViewToWindow )
        {

            zVIEW    mUser = new zVIEW( );
            //zVIEW    mPerson = new zVIEW( );
            zVIEW    vTempViewVar_0 = new zVIEW( );
            int RESULT=0;

            View mPerson = new QualificationBuilder( zencas )
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
            .setLodDef( "mPerson" )
            .addAttribQual( "ID", 50 )
            .activate();

<<<<<<< HEAD
	        // After a createEntity, the acceptSubobject should not return an error.
	        mPerson.cursor("Address").createEntity();
	        mPerson.cursor("Address").acceptSubobject();
	        //mPerson.cursor("Address").create
			return 0;
		}

		public int
		testDropDynamicDomain( View ViewToWindow )
		{

			zVIEW    mUser = new zVIEW( );
			//zVIEW    mPerson = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			zVIEW    domainT = new zVIEW( );
			int RESULT=0;
			String szDomainName="_DM_Ethnicity_Ethnicity";

	        View mPerson = new QualificationBuilder( zencas )
=======
            // After a createEntity, the acceptSubobject should not return an error.
            mPerson.cursor("Address").createEntity();
            mPerson.cursor("Address").acceptSubobject();
            //mPerson.cursor("Address").create
            return 0;
        }

        public int
        testDropDynamicDomain( View ViewToWindow )
        {

            zVIEW    mUser = new zVIEW( );
            //zVIEW    mPerson = new zVIEW( );
            zVIEW    vTempViewVar_0 = new zVIEW( );
            zVIEW    domainT = new zVIEW( );
            int RESULT=0;
            String szDomainName="_DM_Ethnicity_Ethnicity";

            View mPerson = new QualificationBuilder( zencas )
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
            .setLodDef( "mPerson" )
            .addAttribQual( "ID", 50 )
            .activate();

<<<<<<< HEAD
	        Application app = ViewToWindow.getApplication();
	        app.getViewByName( szDomainName ).drop();
	        View domainView = app.getViewByName( szDomainName );
	        if ( domainView != null )
	           domainView.drop();
	        domainView = app.getViewByName( szDomainName );
	        RESULT = GetViewByName( domainT, szDomainName, ViewToWindow, zLEVEL_APPLICATION );
		    Assert.assertEquals( -1, RESULT);
			return 0;
		}

		public int
		testBlobs( View ViewToWindow )
		{

			zVIEW    mUser = new zVIEW( );
			zVIEW    mPerson = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;


	        o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
	        RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
	        DropView( vTempViewVar_0 );
	        SetNameForView( mUser, "mUser", null, zLEVEL_TASK );

    		o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
	 		RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
	 		DropView( vTempViewVar_0 );

	 		mPerson.cursor("ApplicationSibling").createEntity();
	 		mPerson.cursor("ApplicationSibling").getAttribute("FirstName1").setValue("TestFirst");

	 		//srcView.serializeOi().withIncremental().toString()
	 		//mPerson.serializeOi().withIncremental().toWriter(writer)

	 		SetBlobFromOI( mUser, "User", "ProspectInitialApplicationPerson", mPerson.getView(), 0 ) ;
		 	DropView( mPerson);
		 	RESULT = CommitObjectInstance( mUser );
	 		DropView( mUser );

			o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
			RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
			DropView( vTempViewVar_0 );
			SetNameForView( mUser, "mUser", null, zLEVEL_TASK );

		 	StringBuilder szReturn  = new StringBuilder();

		 	SetOI_FromBlob( mPerson, "mPerson", ViewToWindow,
	                      	mUser,  "User", "ProspectInitialApplicationPerson", zIGNORE_ERRORS );

	 		mPerson.cursor("FinAidProfile").setNext();
	 		mPerson.cursor("Address").setNext();

			DropView( mUser );
            DropView( mPerson);
			return 0;

		}

		public int
		testAcceptSubobjectNoUpdate( View ViewToWindow )
		{
			zVIEW    mUser = new zVIEW( );
			zVIEW    mClass = new zVIEW( );
			zVIEW    mPerson = new zVIEW( );
			zVIEW    vTempViewVar_1 = new zVIEW( );
			int RESULT=0;

			// In this test we update FinalGrade attribute from included entities, but after the AcceptSubobject FinalGrade appears empty.

		       o_fnLocalBuildmClass( ViewToWindow, vTempViewVar_1, 31967 );
			   RESULT = ActivateObjectInstance( mClass, "mClass", ViewToWindow, vTempViewVar_1, zSINGLE );
			   DropView( vTempViewVar_1 );
			   SetNameForView( mClass, "mClass", null, zLEVEL_TASK );

               // This is set up... so that we know the FinalGrade starts out as null
			   RESULT = SetCursorFirstEntity( mClass, "Enrollment", "" );
			   while ( RESULT > zCURSOR_UNCHANGED )
			   {
				  SetAttributeFromString( mClass, "Enrollment", "FinalGrade", "" );
			      RESULT = SetCursorNextEntity( mClass, "Enrollment", "" );
			   }
			   mClass.commit();

			   // Exclude all GradeEnrollments if any exist
			   RESULT = SetCursorFirstEntity( mClass, "GradeEnrollment", "" );
			   while ( RESULT > zCURSOR_UNCHANGED )
			   {
			      RESULT = ExcludeEntity( mClass, "GradeEnrollment", zREPOS_NONE );
			      RESULT = SetCursorNextEntity( mClass, "GradeEnrollment", "" );
			   }

			   // Now include GradeEnrollment for all Enrollment entries that are not dropped (taking now or completed).
			   RESULT = SetCursorFirstEntity( mClass, "Enrollment", "" );
			   while ( RESULT > zCURSOR_UNCHANGED )
			   {
			      if ( CompareAttributeToString( mClass, "Enrollment", "Status", "T" ) == 0 || CompareAttributeToString( mClass, "Enrollment", "Status", "C" ) == 0 )
			      {
			         RESULT = IncludeSubobjectFromSubobject( mClass, "GradeEnrollment", mClass, "Enrollment", zPOS_AFTER );
			         SetAttributeFromAttribute( mClass, "GradeEnrollment", "wEnteredGrade", mClass, "Enrollment", "FinalGrade" );
			      }

			      RESULT = SetCursorNextEntity( mClass, "Enrollment", "" );
			   }

			   SetAttributeFromString( mClass, "Class", "wEnterGradesType", "F" );
			   RESULT = SetCursorFirstEntity( mClass, "GradeEnrollment", "" );

			   CreateTemporalSubobjectVersion( mClass, "Class" );

			   // Set all included GradeEnrollment entries to a grade of "C"
			   RESULT = SetCursorFirstEntity( mClass, "GradeEnrollment", "" );
			   while ( RESULT > zCURSOR_UNCHANGED )
			   {
			      SetAttributeFromString( mClass, "GradeEnrollment", "wEnteredGrade", "C" );
			      RESULT = SetCursorNextEntity( mClass, "GradeEnrollment", "" );
			   }

			   // Now update all Enrollment entries where FinalGrade = GradeEnrollment.wEnteredGrade.
			   RESULT = SetCursorFirstEntity( mClass, "GradeEnrollment", "" );
			   while ( RESULT > zCURSOR_UNCHANGED )
			   {
			      RESULT = SetCursorFirstEntityByInteger( mClass, "Enrollment", "ID", mClass.cursor("GradeEnrollment").getAttribute("ID").getInteger(), "" );
			      if ( RESULT >= zCURSOR_SET )
			      {
			         SetAttributeFromAttribute( mClass, "Enrollment", "FinalGrade", mClass, "GradeEnrollment", "wEnteredGrade" );
			      }

			      RESULT = SetCursorNextEntity( mClass, "GradeEnrollment", "" );
			   }

			   // After AcceptSubobject the Enrollment.FinalGrades are empty.
			   AcceptSubobject( mClass, "Class" );

			   RESULT = SetCursorFirstEntity( mClass, "Enrollment", "" );
			   String szGrade = mClass.cursor("Enrollment").getAttribute( "FinalGrade" ).getString();
   			   Assert.assertEquals("Class should be 'C'.", "C", szGrade);

			return 0;
		}

		public int
		testAttributeReadOnlyError( View ViewToWindow )
		{
			zVIEW    mUser = new zVIEW( );
			zVIEW    mBatch = new zVIEW( );
			zVIEW    mPerson = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

			// In this test we include mBatch.OnlineCreatingUser from mUser.User.
			// Then we update mUser.User. When we commit mBatch we get:
			// Entity instance in view: 1081 ZENCAs.mBatch  entity: OnlineCreatingUser  does not have update authority:
			// It is true that OnlineCreatingUser is marked as incl/excl but not marked for "update". Should it be??
			// We are not updating OnlineCreatingUser, we are updating the original mUser.User.
		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
		   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
		   DropView( vTempViewVar_0 );

	        o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
	        RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
	        DropView( vTempViewVar_0 );
	        SetNameForView( mUser, "mUser", null, zLEVEL_TASK );


		   RESULT = ActivateEmptyObjectInstance( mBatch, "mBatch", ViewToWindow, zSINGLE );
		   RESULT = CreateEntity( mBatch, "DataEntryBatch", zPOS_AFTER );
		   SetAttributeFromString( mBatch, "DataEntryBatch", "Name", "WebOnlineApp" );
		   SetAttributeFromString( mBatch, "DataEntryBatch", "Type", "P" );
		   SetAttributeFromString( mBatch, "DataEntryBatch", "OnlineOrManualEntryType", "O" );

		   SetNameForView( mBatch, "mBatch", null, zLEVEL_TASK );

		   SetBlobFromOI( mUser, "User", "ProspectInitialApplicationPerson", mPerson, 0 );
		   RESULT = CommitObjectInstance( mUser );

		   RESULT = CreateEntity( mBatch, "BatchItem", zPOS_AFTER );
		   SetAttributeFromString( mBatch, "BatchItem", "InquiryOrApplicationType", "A" );
		   RESULT = IncludeSubobjectFromSubobject( mBatch, "OnlineCreatingUser", mUser, "User", zPOS_AFTER );

		   SetBlobFromOI( mBatch, "BatchItem", "BlobOI", mPerson, 0 );

		   SetAttributeFromString( mBatch, "BatchItem", "wCopyMergeStatus", "" );
		   SetAttributeFromString( mBatch, "BatchItem", "wPotentialDuplicateFlag", "" );
		   // Update mUser.User attribute
		   SetAttributeFromString( mUser, "User", "ProspectInitialApplicationPerson", "" );

		   // We get an error:
		   // Entity instance in view: 1081 ZENCAs.mBatch  entity: OnlineCreatingUser  does not have update authority:
		   // It is true that OnlineCreatingUser is marked as incl/excl but not marked for "update". Should it be??
		   RESULT = CommitObjectInstance( mBatch );
		   RESULT = CommitObjectInstance( mUser );
			return 0;
		}


		public int
		testzGetNextEntityAttributeName( View ViewToWindow )
		{
			   zVIEW    fPerson= new zVIEW( );
			   //:VIEW vWindow
			   zVIEW    vWindow = new zVIEW( );
			   //:INTEGER nResult
			   int      nResult = 0;
			   //:INTEGER nRC
			   int      nRC = 0;
			   //:INTEGER nExists
			   int      nExists = 0;
			   //:INTEGER nRCE
			   int      nRCE = 0;
			   //:INTEGER nRCA
			   int      nRCA = 0;
			   int      RESULT = 0;
			   String   szString = null;
			   String   szFindString = null;
			   String   szAddToString = null;
			   String   szEntityName = null;
			   String   szAttributeName = null;

			   //:// activate and create the find object for the screen
			   //:ACTIVATE fPerson EMPTY
			   RESULT = ActivateEmptyObjectInstance( fPerson, "fPerson", ViewToWindow, zSINGLE );
			   //:NAME VIEW fPerson  "fPerson"
			   SetNameForView( fPerson, "fPerson", null, zLEVEL_TASK );
			   //:CREATE ENTITY fPerson.Person
			   RESULT = CreateEntity( fPerson, "Person", zPOS_AFTER );
			   //:CREATE ENTITY fPerson.Address
			   RESULT = CreateEntity( fPerson, "Address", zPOS_AFTER );
			   //:CREATE ENTITY fPerson.PrimaryAddress
			   RESULT = CreateEntity( fPerson, "PrimaryAddress", zPOS_AFTER );
			   //:CREATE ENTITY fPerson.Demographics
			   RESULT = CreateEntity( fPerson, "Demographics", zPOS_AFTER );
			   //:CREATE ENTITY fPerson.HomeChurch
			   RESULT = CreateEntity( fPerson, "HomeChurch", zPOS_AFTER );
			   //:CREATE ENTITY fPerson.HomeChurchAddress
			   RESULT = CreateEntity( fPerson, "HomeChurchAddress", zPOS_AFTER );
			   //:CREATE ENTITY fPerson.DescendantRole
			   RESULT = CreateEntity( fPerson, "DescendantRole", zPOS_AFTER );
			   //:CREATE ENTITY fPerson.DescendantPerson
			   RESULT = CreateEntity( fPerson, "DescendantPerson", zPOS_AFTER );
			   //:CREATE ENTITY fPerson.ParentRole
			   RESULT = CreateEntity( fPerson, "ParentRole", zPOS_AFTER );
			   //:CREATE ENTITY fPerson.ParentPerson
			   RESULT = CreateEntity( fPerson, "ParentPerson", zPOS_AFTER );
			   //:CREATE ENTITY fPerson.PriorEducation
			   RESULT = CreateEntity( fPerson, "PriorEducation", zPOS_AFTER );
			   //:CREATE ENTITY fPerson.School
			   RESULT = CreateEntity( fPerson, "School", zPOS_AFTER );
			   //:CREATE ENTITY fPerson.Staff
			   RESULT = CreateEntity( fPerson, "Staff", zPOS_AFTER );
			   //:CREATE ENTITY fPerson.Category
			   RESULT = CreateEntity( fPerson, "Category", zPOS_AFTER );

			   // loop through each entity and attribute to find what ones were not null, the add a
			   //    qual attribute for each one.
			   {StringBuilder sb_szEntityName;
			   if ( szEntityName == null )
			      sb_szEntityName = new StringBuilder( 32 );
			   else
			      sb_szEntityName = new StringBuilder( szEntityName );
			       nRCE = zGetFirstEntityNameForView( fPerson, sb_szEntityName );
			   szEntityName = sb_szEntityName.toString( );}
			   //:szFindString = ""
			    {StringBuilder sb_szFindString;
			   if ( szFindString == null )
			      sb_szFindString = new StringBuilder( 32 );
			   else
			      sb_szFindString = new StringBuilder( szFindString );
			      ZeidonStringCopy( sb_szFindString, 1, 0, "", 1, 0, 2049 );
			   szFindString = sb_szFindString.toString( );}
			   //:LOOP WHILE nRCE >= 0
			   while ( nRCE >= 0 )
			   {
			      nExists = CheckExistenceOfEntity( fPerson, szEntityName );
			      if ( nExists >= zCURSOR_SET )
			      {
			         //:// get the attributes
			         {StringBuilder sb_szAttributeName;
			         if ( szAttributeName == null )
			            sb_szAttributeName = new StringBuilder( 32 );
			         else
			            sb_szAttributeName = new StringBuilder( szAttributeName );
			                   nRCA = zGetFirstAttributeNameForEntity( fPerson, szEntityName, sb_szAttributeName );
			         szAttributeName = sb_szAttributeName.toString( );}
			         while ( nRCA >= 0 )
			         {
			            {StringBuilder sb_szString;
			            if ( szString == null )
			               sb_szString = new StringBuilder( 32 );
			            else
			               sb_szString = new StringBuilder( szString );
			                         GetStringFromAttribute( sb_szString, fPerson, szEntityName, szAttributeName );
			            szString = sb_szString.toString( );}
			            //:// if a value was entered...qualify on it.
			            //:IF szString != ""
			            if ( ZeidonStringCompare( szString, 1, 0, "", 1, 0, 1025 ) != 0 )
			            {
			               //:szAddToString = szEntityName + "." + szAttributeName + " like %" + szString + "%"
			                {StringBuilder sb_szAddToString;
			               if ( szAddToString == null )
			                  sb_szAddToString = new StringBuilder( 32 );
			               else
			                  sb_szAddToString = new StringBuilder( szAddToString );
			                              ZeidonStringCopy( sb_szAddToString, 1, 0, szEntityName, 1, 0, 1025 );
			               szAddToString = sb_szAddToString.toString( );}
			                {StringBuilder sb_szAddToString;
			               if ( szAddToString == null )
			                  sb_szAddToString = new StringBuilder( 32 );
			               else
			                  sb_szAddToString = new StringBuilder( szAddToString );
			                              ZeidonStringConcat( sb_szAddToString, 1, 0, ".", 1, 0, 1025 );
			               szAddToString = sb_szAddToString.toString( );}
			            }

			            //:END
			            //:nRCA = zGetNextAttributeNameForEntity( fPerson, szEntityName, szAttributeName )
			            {StringBuilder sb_szAttributeName;
			            if ( szAttributeName == null )
			               sb_szAttributeName = new StringBuilder( 32 );
			            else
			               sb_szAttributeName = new StringBuilder( szAttributeName );
			                         nRCA = zGetNextAttributeNameForEntity( fPerson, szEntityName, sb_szAttributeName );
			            szAttributeName = sb_szAttributeName.toString( );}
			         }

			      }

			      //:nRCE = zGetNextEntityNameForView( fPerson, szEntityName )
			      {StringBuilder sb_szEntityName;
			      if ( szEntityName == null )
			         sb_szEntityName = new StringBuilder( 32 );
			      else
			         sb_szEntityName = new StringBuilder( szEntityName );
			             nRCE = zGetNextEntityNameForView( fPerson, sb_szEntityName );
			      szEntityName = sb_szEntityName.toString( );}
			   }

			   return( 0 );
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
		    		"target/test-classes/testdata/ZENCAs/rAdvTst.xrp", zSINGLE );

		    RESULT = CheckExistenceOfEntity( vReportDef, "DrivingViewObjRef" );

			DropView( vReportDef );
			return ( 0 );
		}

		public void testZeidonStringCompare( View     ViewToWindow )
		{
			int nRC = 0;

			// Also going to test zSearchAndReplace
			String szLocation = "NY:AMHERSTNY";
=======
            Application app = ViewToWindow.getApplication();
            app.getViewByName( szDomainName ).drop();
            View domainView = app.getViewByName( szDomainName );
            if ( domainView != null )
               domainView.drop();
            domainView = app.getViewByName( szDomainName );
            RESULT = GetViewByName( domainT, szDomainName, ViewToWindow, zLEVEL_APPLICATION );
            Assert.assertEquals( -1, RESULT);
            return 0;
        }

        public int
        testBlobs( View ViewToWindow )
        {

            zVIEW    mUser = new zVIEW( );
            zVIEW    mPerson = new zVIEW( );
            zVIEW    vTempViewVar_0 = new zVIEW( );
            int RESULT=0;


            o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
            RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
            DropView( vTempViewVar_0 );
            SetNameForView( mUser, "mUser", null, zLEVEL_TASK );

            o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
             RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
             DropView( vTempViewVar_0 );

             mPerson.cursor("ApplicationSibling").createEntity();
             mPerson.cursor("ApplicationSibling").getAttribute("FirstName1").setValue("TestFirst");

             //srcView.serializeOi().withIncremental().toString()
             //mPerson.serializeOi().withIncremental().toWriter(writer)

             SetBlobFromOI( mUser, "User", "ProspectInitialApplicationPerson", mPerson.getView(), 0 ) ;
             DropView( mPerson);
             RESULT = CommitObjectInstance( mUser );
             DropView( mUser );

            o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
            RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
            DropView( vTempViewVar_0 );
            SetNameForView( mUser, "mUser", null, zLEVEL_TASK );

             StringBuilder szReturn  = new StringBuilder();

             SetOI_FromBlob( mPerson, "mPerson", ViewToWindow,
                              mUser,  "User", "ProspectInitialApplicationPerson", zIGNORE_ERRORS );

             mPerson.cursor("FinAidProfile").setNext();
             mPerson.cursor("Address").setNext();

            DropView( mUser );
            DropView( mPerson);
            return 0;

        }

        public int
        testAcceptSubobjectNoUpdate( View ViewToWindow )
        {
            zVIEW    mUser = new zVIEW( );
            zVIEW    mClass = new zVIEW( );
            zVIEW    mPerson = new zVIEW( );
            zVIEW    vTempViewVar_1 = new zVIEW( );
            int RESULT=0;

            // In this test we update FinalGrade attribute from included entities, but after the AcceptSubobject FinalGrade appears empty.

               o_fnLocalBuildmClass( ViewToWindow, vTempViewVar_1, 31967 );
               RESULT = ActivateObjectInstance( mClass, "mClass", ViewToWindow, vTempViewVar_1, zSINGLE );
               DropView( vTempViewVar_1 );
               SetNameForView( mClass, "mClass", null, zLEVEL_TASK );

               // This is set up... so that we know the FinalGrade starts out as null
               RESULT = SetCursorFirstEntity( mClass, "Enrollment", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               {
                  SetAttributeFromString( mClass, "Enrollment", "FinalGrade", "" );
                  RESULT = SetCursorNextEntity( mClass, "Enrollment", "" );
               }
               mClass.commit();

               // Exclude all GradeEnrollments if any exist
               RESULT = SetCursorFirstEntity( mClass, "GradeEnrollment", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               {
                  RESULT = ExcludeEntity( mClass, "GradeEnrollment", zREPOS_NONE );
                  RESULT = SetCursorNextEntity( mClass, "GradeEnrollment", "" );
               }

               // Now include GradeEnrollment for all Enrollment entries that are not dropped (taking now or completed).
               RESULT = SetCursorFirstEntity( mClass, "Enrollment", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               {
                  if ( CompareAttributeToString( mClass, "Enrollment", "Status", "T" ) == 0 || CompareAttributeToString( mClass, "Enrollment", "Status", "C" ) == 0 )
                  {
                     RESULT = IncludeSubobjectFromSubobject( mClass, "GradeEnrollment", mClass, "Enrollment", zPOS_AFTER );
                     SetAttributeFromAttribute( mClass, "GradeEnrollment", "wEnteredGrade", mClass, "Enrollment", "FinalGrade" );
                  }

                  RESULT = SetCursorNextEntity( mClass, "Enrollment", "" );
               }

               SetAttributeFromString( mClass, "Class", "wEnterGradesType", "F" );
               RESULT = SetCursorFirstEntity( mClass, "GradeEnrollment", "" );

               CreateTemporalSubobjectVersion( mClass, "Class" );

               // Set all included GradeEnrollment entries to a grade of "C"
               RESULT = SetCursorFirstEntity( mClass, "GradeEnrollment", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               {
                  SetAttributeFromString( mClass, "GradeEnrollment", "wEnteredGrade", "C" );
                  RESULT = SetCursorNextEntity( mClass, "GradeEnrollment", "" );
               }

               // Now update all Enrollment entries where FinalGrade = GradeEnrollment.wEnteredGrade.
               RESULT = SetCursorFirstEntity( mClass, "GradeEnrollment", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               {
                  RESULT = SetCursorFirstEntityByInteger( mClass, "Enrollment", "ID", mClass.cursor("GradeEnrollment").getAttribute("ID").getInteger(), "" );
                  if ( RESULT >= zCURSOR_SET )
                  {
                     SetAttributeFromAttribute( mClass, "Enrollment", "FinalGrade", mClass, "GradeEnrollment", "wEnteredGrade" );
                  }

                  RESULT = SetCursorNextEntity( mClass, "GradeEnrollment", "" );
               }

               // After AcceptSubobject the Enrollment.FinalGrades are empty.
               AcceptSubobject( mClass, "Class" );

               RESULT = SetCursorFirstEntity( mClass, "Enrollment", "" );
               String szGrade = mClass.cursor("Enrollment").getAttribute( "FinalGrade" ).getString();
                  Assert.assertEquals("Class should be 'C'.", "C", szGrade);

            return 0;
        }

        public int
        testAttributeReadOnlyError( View ViewToWindow )
        {
            zVIEW    mUser = new zVIEW( );
            zVIEW    mBatch = new zVIEW( );
            zVIEW    mPerson = new zVIEW( );
            zVIEW    vTempViewVar_0 = new zVIEW( );
            int RESULT=0;

            // In this test we include mBatch.OnlineCreatingUser from mUser.User.
            // Then we update mUser.User. When we commit mBatch we get:
            // Entity instance in view: 1081 ZENCAs.mBatch  entity: OnlineCreatingUser  does not have update authority:
            // It is true that OnlineCreatingUser is marked as incl/excl but not marked for "update". Should it be??
            // We are not updating OnlineCreatingUser, we are updating the original mUser.User.
           o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
           RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );

            o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
            RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
            DropView( vTempViewVar_0 );
            SetNameForView( mUser, "mUser", null, zLEVEL_TASK );


           RESULT = ActivateEmptyObjectInstance( mBatch, "mBatch", ViewToWindow, zSINGLE );
           RESULT = CreateEntity( mBatch, "DataEntryBatch", zPOS_AFTER );
           SetAttributeFromString( mBatch, "DataEntryBatch", "Name", "WebOnlineApp" );
           SetAttributeFromString( mBatch, "DataEntryBatch", "Type", "P" );
           SetAttributeFromString( mBatch, "DataEntryBatch", "OnlineOrManualEntryType", "O" );

           SetNameForView( mBatch, "mBatch", null, zLEVEL_TASK );

           SetBlobFromOI( mUser, "User", "ProspectInitialApplicationPerson", mPerson, 0 );
           RESULT = CommitObjectInstance( mUser );

           RESULT = CreateEntity( mBatch, "BatchItem", zPOS_AFTER );
           SetAttributeFromString( mBatch, "BatchItem", "InquiryOrApplicationType", "A" );
           RESULT = IncludeSubobjectFromSubobject( mBatch, "OnlineCreatingUser", mUser, "User", zPOS_AFTER );

           SetBlobFromOI( mBatch, "BatchItem", "BlobOI", mPerson, 0 );

           SetAttributeFromString( mBatch, "BatchItem", "wCopyMergeStatus", "" );
           SetAttributeFromString( mBatch, "BatchItem", "wPotentialDuplicateFlag", "" );
           // Update mUser.User attribute
           SetAttributeFromString( mUser, "User", "ProspectInitialApplicationPerson", "" );

           // We get an error:
           // Entity instance in view: 1081 ZENCAs.mBatch  entity: OnlineCreatingUser  does not have update authority:
           // It is true that OnlineCreatingUser is marked as incl/excl but not marked for "update". Should it be??
           RESULT = CommitObjectInstance( mBatch );
           RESULT = CommitObjectInstance( mUser );
            return 0;
        }


        public int
        testzGetNextEntityAttributeName( View ViewToWindow )
        {
               zVIEW    fPerson= new zVIEW( );
               //:VIEW vWindow
               zVIEW    vWindow = new zVIEW( );
               //:INTEGER nResult
               int      nResult = 0;
               //:INTEGER nRC
               int      nRC = 0;
               //:INTEGER nExists
               int      nExists = 0;
               //:INTEGER nRCE
               int      nRCE = 0;
               //:INTEGER nRCA
               int      nRCA = 0;
               int      RESULT = 0;
               String   szString = null;
               String   szFindString = null;
               String   szAddToString = null;
               String   szEntityName = null;
               String   szAttributeName = null;

               //:// activate and create the find object for the screen
               //:ACTIVATE fPerson EMPTY
               RESULT = ActivateEmptyObjectInstance( fPerson, "fPerson", ViewToWindow, zSINGLE );
               //:NAME VIEW fPerson  "fPerson"
               SetNameForView( fPerson, "fPerson", null, zLEVEL_TASK );
               //:CREATE ENTITY fPerson.Person
               RESULT = CreateEntity( fPerson, "Person", zPOS_AFTER );
               //:CREATE ENTITY fPerson.Address
               RESULT = CreateEntity( fPerson, "Address", zPOS_AFTER );
               //:CREATE ENTITY fPerson.PrimaryAddress
               RESULT = CreateEntity( fPerson, "PrimaryAddress", zPOS_AFTER );
               //:CREATE ENTITY fPerson.Demographics
               RESULT = CreateEntity( fPerson, "Demographics", zPOS_AFTER );
               //:CREATE ENTITY fPerson.HomeChurch
               RESULT = CreateEntity( fPerson, "HomeChurch", zPOS_AFTER );
               //:CREATE ENTITY fPerson.HomeChurchAddress
               RESULT = CreateEntity( fPerson, "HomeChurchAddress", zPOS_AFTER );
               //:CREATE ENTITY fPerson.DescendantRole
               RESULT = CreateEntity( fPerson, "DescendantRole", zPOS_AFTER );
               //:CREATE ENTITY fPerson.DescendantPerson
               RESULT = CreateEntity( fPerson, "DescendantPerson", zPOS_AFTER );
               //:CREATE ENTITY fPerson.ParentRole
               RESULT = CreateEntity( fPerson, "ParentRole", zPOS_AFTER );
               //:CREATE ENTITY fPerson.ParentPerson
               RESULT = CreateEntity( fPerson, "ParentPerson", zPOS_AFTER );
               //:CREATE ENTITY fPerson.PriorEducation
               RESULT = CreateEntity( fPerson, "PriorEducation", zPOS_AFTER );
               //:CREATE ENTITY fPerson.School
               RESULT = CreateEntity( fPerson, "School", zPOS_AFTER );
               //:CREATE ENTITY fPerson.Staff
               RESULT = CreateEntity( fPerson, "Staff", zPOS_AFTER );
               //:CREATE ENTITY fPerson.Category
               RESULT = CreateEntity( fPerson, "Category", zPOS_AFTER );

               // loop through each entity and attribute to find what ones were not null, the add a
               //    qual attribute for each one.
               {StringBuilder sb_szEntityName;
               if ( szEntityName == null )
                  sb_szEntityName = new StringBuilder( 32 );
               else
                  sb_szEntityName = new StringBuilder( szEntityName );
                   nRCE = zGetFirstEntityNameForView( fPerson, sb_szEntityName );
               szEntityName = sb_szEntityName.toString( );}
               //:szFindString = ""
                {StringBuilder sb_szFindString;
               if ( szFindString == null )
                  sb_szFindString = new StringBuilder( 32 );
               else
                  sb_szFindString = new StringBuilder( szFindString );
                  ZeidonStringCopy( sb_szFindString, 1, 0, "", 1, 0, 2049 );
               szFindString = sb_szFindString.toString( );}
               //:LOOP WHILE nRCE >= 0
               while ( nRCE >= 0 )
               {
                  nExists = CheckExistenceOfEntity( fPerson, szEntityName );
                  if ( nExists >= zCURSOR_SET )
                  {
                     //:// get the attributes
                     {StringBuilder sb_szAttributeName;
                     if ( szAttributeName == null )
                        sb_szAttributeName = new StringBuilder( 32 );
                     else
                        sb_szAttributeName = new StringBuilder( szAttributeName );
                               nRCA = zGetFirstAttributeNameForEntity( fPerson, szEntityName, sb_szAttributeName );
                     szAttributeName = sb_szAttributeName.toString( );}
                     while ( nRCA >= 0 )
                     {
                        {StringBuilder sb_szString;
                        if ( szString == null )
                           sb_szString = new StringBuilder( 32 );
                        else
                           sb_szString = new StringBuilder( szString );
                                     GetStringFromAttribute( sb_szString, fPerson, szEntityName, szAttributeName );
                        szString = sb_szString.toString( );}
                        //:// if a value was entered...qualify on it.
                        //:IF szString != ""
                        if ( ZeidonStringCompare( szString, 1, 0, "", 1, 0, 1025 ) != 0 )
                        {
                           //:szAddToString = szEntityName + "." + szAttributeName + " like %" + szString + "%"
                            {StringBuilder sb_szAddToString;
                           if ( szAddToString == null )
                              sb_szAddToString = new StringBuilder( 32 );
                           else
                              sb_szAddToString = new StringBuilder( szAddToString );
                                          ZeidonStringCopy( sb_szAddToString, 1, 0, szEntityName, 1, 0, 1025 );
                           szAddToString = sb_szAddToString.toString( );}
                            {StringBuilder sb_szAddToString;
                           if ( szAddToString == null )
                              sb_szAddToString = new StringBuilder( 32 );
                           else
                              sb_szAddToString = new StringBuilder( szAddToString );
                                          ZeidonStringConcat( sb_szAddToString, 1, 0, ".", 1, 0, 1025 );
                           szAddToString = sb_szAddToString.toString( );}
                        }

                        //:END
                        //:nRCA = zGetNextAttributeNameForEntity( fPerson, szEntityName, szAttributeName )
                        {StringBuilder sb_szAttributeName;
                        if ( szAttributeName == null )
                           sb_szAttributeName = new StringBuilder( 32 );
                        else
                           sb_szAttributeName = new StringBuilder( szAttributeName );
                                     nRCA = zGetNextAttributeNameForEntity( fPerson, szEntityName, sb_szAttributeName );
                        szAttributeName = sb_szAttributeName.toString( );}
                     }

                  }

                  //:nRCE = zGetNextEntityNameForView( fPerson, szEntityName )
                  {StringBuilder sb_szEntityName;
                  if ( szEntityName == null )
                     sb_szEntityName = new StringBuilder( 32 );
                  else
                     sb_szEntityName = new StringBuilder( szEntityName );
                         nRCE = zGetNextEntityNameForView( fPerson, sb_szEntityName );
                  szEntityName = sb_szEntityName.toString( );}
               }

               return( 0 );
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
                    "target/test-classes/testdata/ZENCAs/rAdvTst.xrp", zSINGLE );

            RESULT = CheckExistenceOfEntity( vReportDef, "DrivingViewObjRef" );

            DropView( vReportDef );
            return ( 0 );
        }

        public void testZeidonStringCompare( View     ViewToWindow )
        {
            int nRC = 0;

            // Also going to test zSearchAndReplace
            String szLocation = "NY:AMHERSTNY";
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
            {StringBuilder sb_szLocation;
            if ( szLocation == null )
               sb_szLocation = new StringBuilder( 32 );
            else
               sb_szLocation = new StringBuilder( szLocation );
            zSearchAndReplace( sb_szLocation, 256, "NY:", "NY: " );
            szLocation = sb_szLocation.toString( );}

            if (!szLocation.equals("NY: AMHERSTNY"))
<<<<<<< HEAD
            	Assert.assertEquals("zSearchAndReplace not working!", 1, 0);
=======
                Assert.assertEquals("zSearchAndReplace not working!", 1, 0);
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b

            szLocation = "111-11-1111";
            {StringBuilder sb_szLocation;
            if ( szLocation == null )
               sb_szLocation = new StringBuilder( 32 );
            else
               sb_szLocation = new StringBuilder( szLocation );
            zSearchAndReplace( sb_szLocation,25, "-", "" );
            szLocation = sb_szLocation.toString( );}
            if (!szLocation.equals("111111111"))
<<<<<<< HEAD
            	Assert.assertEquals("zSearchAndReplace not working!", 1, 0);
=======
                Assert.assertEquals("zSearchAndReplace not working!", 1, 0);
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b

            szLocation = "NY:AMHERSTNYNY:AMHERSTNY";
            {StringBuilder sb_szLocation;
            if ( szLocation == null )
               sb_szLocation = new StringBuilder( 32 );
            else
               sb_szLocation = new StringBuilder( szLocation );
            zSearchAndReplace( sb_szLocation,25, "NY:", "NY: " );
            szLocation = sb_szLocation.toString( );}
            if (!szLocation.equals("NY: AMHERSTNYNY: AMHERSTNY"))
<<<<<<< HEAD
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
                throw ZeidonException.wrapException( e );
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
		    		"target/test-classes/testdata/ZENCAs/rAdvTst2.xrp", zSINGLE );

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

		    mStudent.cursor( "Student" ).getAttribute( "RoommateQuestionaireReceivedDate").setValue( "" ) ;
	        View v = zencas.activateOiFromFile( "mStudent", "target/test-classes/testdata//ZENCAs/mstudent_ac.por" );
	        boolean c = mStudent.equalsOi( v );
	        Assert.assertTrue( "OIs differ", c );

		    System.out.println(RESULT);
		    DropView( vTempViewVar_0 );
			DropView( mStudent );
			return 0;
		}


		public int
		testAutoloadFromParent( View     ViewToWindow )
		{
			zVIEW    mTstAutos = new zVIEW( );
			zVIEW    wXferO = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

			// When an entity is marked as AUTOLOADFROMPARENT in xod, if the parent entity fk_id is null, then we should not create an
			// entity for this child.
			// We have Student->Person where Person is AUTOLOAD... Student.fk_id_person2 is null so Person should not exists but
			// currently it does although attribute ID in Person is null.

			o_fnLocalBuildmTstAuto( ViewToWindow, vTempViewVar_0, 16406 );
		    RESULT = ActivateObjectInstance( mTstAutos, "mTstAutos", ViewToWindow, vTempViewVar_0, zMULTIPLE );
		    Assert.assertFalse( "Person exists but should not because parent key is null.", mTstAutos.cursor("Person").checkExistenceOfEntity().isSet());

		    DropView( vTempViewVar_0 );
			DropView( mTstAutos );
			return 0;
		}

		public int
		testActivateORStmnt( View     ViewToWindow )
		{
			   zVIEW    mTstOR = new zVIEW( );
			   zVIEW    vTempViewVar_0 = new zVIEW( );
			   int      RESULT = 0;

			   /*
			    *  We have an activate statement with an OR.
   				ACTIVATE mTstOR Multiple WHERE mTstOR.AdministrativeDivision.ID = 2 OR mTstOR.DegreeTrack.ID = 1212

   				Because of the OR, we believe that the join from STUDENT to STUDENTMAJORDEGREETRACK should be a
   				LEFT JOIN but the sql is created as JOIN.

			    SELECT  STUDENT.ID, STUDENT.FKIDADMINISTRATIVE, STUDENTMAJORDEGREETRACK.FK_ID_DEGREETRACK
			    FROM  STUDENT JOIN
			          STUDENTMAJORDEGREETRACK ON STUDENTMAJORDEGREETRACK.FKHISTID_STUDENT = STUDENT.ID
			    WHERE STUDENT.FKIDADMINISTRATIVE = 2 OR STUDENTMAJORDEGREETRACK.FK_ID_DEGREETRACK = 1212

			   */
			   o_fnLocalBuildmTstOR( ViewToWindow, vTempViewVar_0 );
			   RESULT = ActivateObjectInstance( mTstOR, "mTstOR", ViewToWindow, vTempViewVar_0, zMULTIPLE );
			   DropView( vTempViewVar_0 );
			   //:NAME VIEW mTstOR "mTstOR"
			   SetNameForView( mTstOR, "mTstOR", null, zLEVEL_TASK );
			   CursorResult rc  = mTstOR.cursor("Student").setFirst("ID", 28);
		       Assert.assertEquals( "ResultSet is missing a student we expect to return", CursorResult.SET, rc );

			   return( 0 );
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
		testRestrictWithParentJoin( View     ViewToWindow )
		{
			zVIEW    mTSTs = new zVIEW( );
			zVIEW    mTSTNoJoin = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;
			boolean bRC;

			// I have two objects. One is using parent join, the other is not.
			// The object "with join" is not doing RESTRICT correctly. We restrict on DegreeTrack but parent entity
			// "StudentMajorDegreeTrack" is empty/missing.
			// On sqlite db, need to run the following: update degreetrack set exceptionflag = 'Y' where id = 1264

		   // Activate object that has no parent join. Restrict DegreeTrack on DegreeTrack.ExchangeFlag = "Y".
		   //:ACTIVATE mTSTs2 MULTIPLE WHERE  mTSTs2.DegreeTrack.ExchangeFlag = "Y"
		   //:    RESTRICTING mTSTs2.DegreeTrack TO mTSTs2.DegreeTrack.ExchangeFlag = "Y"
		   o_fnLocalBuildmTSTs( ViewToWindow, vTempViewVar_0 );
		   RESULT = ActivateObjectInstance( mTSTNoJoin, "mTSTNoJoin", ViewToWindow, vTempViewVar_0, zMULTIPLE );
		   DropView( vTempViewVar_0 );
		   SetNameForView( mTSTNoJoin, "mTSTNoJoin", null, zLEVEL_TASK );
           int rc = mTSTNoJoin.cursor( "DegreeTrack" ).setFirst( "ID", 1264, "Person" ).toInt();
 		   Assert.assertEquals("Object without parent join but has RESTRICT. DegreeTrack should exist but does not.", true, rc >= 0);

		   // Activate object that has parent join. Restrict DegreeTrack on DegreeTrack.ExchangeFlag = "Y".
		   //:ACTIVATE mTSTs MULTIPLE WHERE  mTSTs.DegreeTrack.ExchangeFlag = "Y"
		   //:    RESTRICTING mTSTs.DegreeTrack TO mTSTs.DegreeTrack.ExchangeFlag = "Y"
		   o_fnLocalBuildmTSTs( ViewToWindow, vTempViewVar_0 );
		   RESULT = ActivateObjectInstance( mTSTs, "mTSTs", ViewToWindow, vTempViewVar_0, zMULTIPLE );
		   DropView( vTempViewVar_0 );
		   //:NAME VIEW mTSTs "mTSTs"
		   SetNameForView( mTSTs, "mTSTs", null, zLEVEL_TASK );
           rc = mTSTs.cursor( "DegreeTrack" ).setFirst( "ID", 1264, "Person" ).toInt();
 		   Assert.assertEquals("Object with Parent Join and RESTRICT. DegreeTrack should exist but does not.", true, rc >= 0);

			DropView( mTSTs );
			DropView( mTSTNoJoin );

			return 0;
		}

		private int
		o_fnLocalBuildmTSTs( View     vSubtask,
		                      zVIEW    vQualObject )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "DegreeTrack" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ExceptionFlag" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "DegreeTrack" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "DegreeTrack" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ExceptionFlag" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		public int
		testQualKeyList( View ViewToWindow )
		{
			zVIEW    wXferO = new zVIEW( );
			zVIEW    vQualObject = new zVIEW( );
			zVIEW    lTrnscpt = new zVIEW( );
			int RESULT = 0;

		   // Was having trouble with they keylist and the sql statement that was created: had = ( ?, ? ), not in ( ?, ? ).
		   // want to make sure this works. Shouldn't need an Assert. If the ActivateObjectInstance works
		   // then we will assume all is well.
		   RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
		   RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
		   RESULT = CreateEntity( wXferO, "ActivateID_List", zPOS_AFTER );
		   SetAttributeFromInteger( wXferO, "ActivateID_List", "ID", 16406 );
		   RESULT = CreateEntity( wXferO, "ActivateID_List", zPOS_AFTER );
		   SetAttributeFromInteger( wXferO, "ActivateID_List", "ID", 14901 );

		   GenerateQualFromEntityList( vQualObject, wXferO, "ActivateID_List", "", 0 );
		   ActivateObjectInstance( lTrnscpt, "lTrnscpt", ViewToWindow, vQualObject, zMULTIPLE );
		   DropObjectInstance( lTrnscpt);

		   return 0;
		}

		public int
		testSetMatching( View ViewToWindow )
		{
			zVIEW    mPerson = new zVIEW( );
			zVIEW    mPerson2 = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;
			boolean bRC;

		   // Was having trouble with they keylist and the sql statement that was created: had = ( ?, ? ), not in ( ?, ? ).
		   // want to make sure this works. Shouldn't need an Assert. If the ActivateObjectInstance works
		   // then we will assume all is well.
		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
		   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 151289 );
		   RESULT = ActivateObjectInstance( mPerson2, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );

		   if (mPerson2.cursor("Demographics").checkExistenceOfEntity().isEmpty())
		   {
			   mPerson2.cursor("Demographics").createEntity();
			   SetMatchingAttributesByName( mPerson2, "Demographics", mPerson, "Demographics", zSET_NULL | zSET_NOTNULL );
	 		   Assert.assertEquals("After setMatchingAttributesByName, Demographics.ID should still be NULL.", true, mPerson2.cursor("Demographics").getAttribute("ID").isNull());
		   }
		   DropObjectInstance(mPerson);
		   DropObjectInstance(mPerson2);
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

		    if ( mStudent.cursor("Student").getAttribute("Status").getString().equals("I") )
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
		testLongStringWBindFalse( View     ViewToWindow )
		{
			View    mPerson = new zVIEW( );
			zVIEW   vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

			// The following activate does not work properly when BindAllValues=true.  AdviseeStudentTrack is
			// null.  If BindAllValues=false then it works correctly.
			// Is there a way to set BindAllValues here so it doesn't matter what zeidon.ini is set to?

			 o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );

	         ActivateOptions options = new ActivateOptions( ViewToWindow.getTask() );
	         options.overrideConfigValue( "BindAllValues", "false" );
	         options.setActivateFlags( ActivateFlags.MULTIPLE );
	         mPerson = ViewToWindow.activateObjectInstance( "mPerson", vTempViewVar_0, options );
	         DropView( vTempViewVar_0 );
	         mPerson.cursor("Person").getAttribute("EmergencyContactNote").setValue("");
	         mPerson.commit();

	         mPerson.cursor("Person").getAttribute("EmergencyContactNote").setValue("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
	         mPerson.commit();
   			 //Assert.assertEquals("AdviseeStudentTrack was not correctly activated when BindAllValues=true.", 0, RESULT);
 			 DropView( mPerson );

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
		    String dateStr = wXferO.cursor("Root").getAttribute("dCurrentDate").getString();
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
	        szYear = mStudenC.cursor("RegistrationClassCollegeYear").getAttribute("Year").getString();
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
		      iRequiredGroup = mMinorTrack.cursor( "RequiredGroup" ).getAttribute("ID").getInteger();
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
		      iRequiredGroup = lClsLstC.cursor( "Course" ).getAttribute("ID").getInteger();
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
		    wWebXfer.cursor("Work").getAttribute( "FromDate").setValue( wXferO.cursor(  "Root" ).getAttribute(  "dCurrentDate" ).getValue() )  ;

		    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wWebXfer );
		    m_ZGLOBAL1_Operation.AddMonthsToDate( wWebXfer, "Work", "FromDate", -6 );
		     //iRequiredGroup = lClsLstC.cursor( "Course" ).getAttribute("ID").getInteger();
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

	         szDate = mUser.cursor("User").getAttribute("LastLoginDateTime").getString();
	         Assert.assertEquals("Error with datetime string length", 14, szDate.length());
	         szDate = mUser.cursor("User").getAttribute("OnlineProspectInitialCreatedDate").getString();
	         Assert.assertEquals("Error with datetime string length", 8, szDate.length());

	         mUser.cursor("User").getAttribute("OnlineProspectInitialCreatedDate").setValue("11/11/2021", "DD/MM/YYYY");
	         mUser.cursor("User").getAttribute("LastLoginDateTime").setValue("11/11/2021", "DD/MM/YYYY");

	         RESULT = CommitObjectInstance( mUser );

	         testTimeFormatting( ViewToWindow );

	         return ( 0 );
		}

		public int
		testDerivedAttrCompare( View     ViewToWindow )
		{
			zVIEW    mUser = new zVIEW( );
			zVIEW    mUser2 = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			String szRoles;
			int RESULT=0;

			 o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
	         RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zSINGLE );
	         DropView( vTempViewVar_0 );

	         CreateViewFromView( mUser2, mUser );

	         EntityCursor cursor = mUser.cursor( "Person" );
	         //AttributeInstance attrib = cursor.getAttribute( "dPersonRoles" );
	         //szRoles = attrib.getString();

	         //RESULT = CompareAttributeToAttribute(mUser2, "Person", "dPersonRoles", mUser, "Person", "dPersonRoles");

	         // When we call getAttribute(), executeDerivedOperation is not being executed.
	         // When we do the attrib.getString() below, then it calls the derived attribute and the compare will work.
	         Assert.assertTrue( "Error comparing Derived Attribute, executeDerivedOperation not being executed.", cursor.getAttribute( "dPersonRoles" ).compare( "Instructor, Donor, Alumni, Employee" ) == 0 );
	         AttributeInstance attrib2 = cursor.getAttribute( "dPersonRoles" );
	         szRoles = attrib2.getString();

	         Assert.assertTrue( "Error comparing Derived Attribute, executeDerivedOperation not being executed.", cursor.getAttribute( "dPersonRoles" ).compare( "Instructor, Donor, Alumni, Employee" ) == 0 );

	         return 0;
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

	         wConList.cursor("ContactList").getAttribute("ListName").setValue( "KellysTest") ;
	         wConList.cursor("ContactList").getAttribute("Type").setValue( "W") ;
	         wConList.cursor("ContactList").getAttribute("Note").setValue( "This is a test") ;
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
	         iConListID = wConList.cursor("ContactList").getAttribute("ID").getInteger();
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
		testAutoLoadFromParent( View ViewToWindow )
		{

			zVIEW    mTstClss = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;

			// When the xod had autoloadforparent=Y but the foreign key in the parent was null, the child was still being created.
			// It's fixed but I am still adding this.

			o_fnLocalBuildQualTstClass( ViewToWindow, vTempViewVar_0, 776 );
			RESULT = ActivateObjectInstance( mTstClss, "mTstClss", ViewToWindow, vTempViewVar_0, zSINGLE );
			Assert.assertEquals("Child entity faculty should be null.", mTstClss.cursor("Faculty").checkExistenceOfEntity(), CursorResult.NULL);

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

	   		ActivateOI_FromFile( mStudent, "mStudent", ViewToWindow, "target/test-classes/testdata//ZENCAs/mStudentDerivedDate.por", zSINGLE );
	   		ActivateOI_FromFile( mStudenC, "mStudenC", ViewToWindow, "target/test-classes/testdata//ZENCAs/mStudenCDerivedDate.por", zSINGLE );
			SetNameForView( mStudenC, "mStudenC", null, zLEVEL_TASK );
            // I get an error on the derived attribute dLastAuditDateTime because GetVariableFromAttribute returns the
			// date in the format "08/19/2011 09:45:20.0 AM" which causes an error in StoreStringInRecord.
			// If I add the context "YYYYMMDDHHMMSSS" it works fine.
			//GetVariableFromAttribute( sb_szDate, mi_lTempInteger_2, 'S', 101, mStudenC, "SavedDegreeAudit", "CreatedDateTime", "", 0 );			//com.quinsoft.zeidon.InvalidAttributeValueException: Invalid value for attribute
	        strGridEditCtl1 = mStudent.cursor( "MajorDegreeTrack" ).getAttribute( "dLastAuditDateTime" ).getString("");
 	        System.out.println(strGridEditCtl1);
	         DropObjectInstance( mStudent );
	         DropObjectInstance( mStudenC );
			return 0;
		}


		public int
		testDeletePersonRoot( View     ViewToWindow )
		{
			zVIEW    mPerson = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;
			   //:
			   //: This test creates a Person with Address.
			   //: Include Address into mPerson.PrimaryAddress which then creates the display entity "PrimaryForPerson" under Address.
			   //: When we try to delete mPerson.Person we get the following error:
			   //:
			   //: com.quinsoft.zeidon.ZeidonException: Entity is not flagged for delete.
			   //: EntityDef  = ZENCAs.mPerson.PrimaryForPerson
			   //:
			   //: But I don't think PrimaryForPerson should need to have "delete flagged".

			   o_fnLocalBuildQual_Humpty( ViewToWindow, vTempViewVar_0 );
			   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
			   DropView( vTempViewVar_0 );
			   // Create temporary person if it doeson't exist.
			   if ( RESULT < 0 )
			   {
				   RESULT = ActivateEmptyObjectInstance( mPerson, "mPerson", ViewToWindow, zSINGLE );
				   //: CREATE ENTITY mPerson.Person
				   RESULT = CreateEntity( mPerson, "Person", zPOS_AFTER );
				   //: mPerson.Person.LastName = "Dumpty"
				   SetAttributeFromString( mPerson, "Person", "LastName", "Dumpty" );
				   //: mPerson.Person.FirstName = "Humpty"
				   SetAttributeFromString( mPerson, "Person", "FirstName", "Humpty" );
				   RESULT = CreateEntity( mPerson, "Address", zPOS_AFTER );
				   //: mPerson.Address.Line1 = "1111"
				   SetAttributeFromString( mPerson, "Address", "Line1", "1111" );
				   //: mPerson.Address.City = "xxxxxxx"
				   SetAttributeFromString( mPerson, "Address", "City", "xxxxxxx" );
				   //: INCLUDE mPerson.PrimaryAddress FROM mPerson.Address
				   RESULT = IncludeSubobjectFromSubobject( mPerson, "PrimaryAddress", mPerson, "Address", zPOS_AFTER );
				   //: COMMIT mPerson
				   RESULT = CommitObjectInstance( mPerson );
				   //: DropObjectInstance( mPerson )
				   DropObjectInstance( mPerson );
			   }

			   //: ACTIVATE mPerson WHERE mPerson.Person.LastName = "Dumpty" AND
			   //:          mPerson.Person.FirstName = "Humpty"
			   o_fnLocalBuildQual_Humpty( ViewToWindow, vTempViewVar_0 );
			   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
			   DropView( vTempViewVar_0 );
			   //: DELETE ENTITY mPerson.Person
			   RESULT = DeleteEntity( mPerson, "Person", zPOS_NEXT );

			   return 0;
		}


		public int
		testUpdateNonLatinCharacters( View     ViewToWindow )
		{
			zVIEW    mPerson = new zVIEW( );
			zVIEW    vTempViewVar_0 = new zVIEW( );
			int RESULT=0;
			   //:
			   //: This test tries to set Address.city to the Cyrillic characters 'Германия'.
			   //:
			   //: When we do and commit the object, the sql code generated (this is for SqlServer) gets set to:
			   //: UPDATE ADDRESS
			   //:    SET    MODIFIEDDATETIME = '2016-06-13 09:48:16',
			   //:    CITY = '????????'
			   //:  WHERE ADDRESS.ID = 235512;

			   //: If we do the update in sqlserver, we need to put an "N" in front like the following,
			   //: so would we need to do something similar in our code?
			   //: UPDATE ADDRESS
		   	   //: SET CITY = N'Германия'
			   //:
			   //: If the address is already set to 'Германия' in the database, when we activate, the city looks
			   //: corect. Also, when we do the SetAttributeFromString( mPerson, "Address", "City", "Германия" );
			   //: the object looks correct, it's when we do the update.

			   o_fnLocalBuildQual_Humpty( ViewToWindow, vTempViewVar_0 );
			   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
			   //DropView( vTempViewVar_0 );
			   // Create temporary person if it doeson't exist.
			   if ( RESULT < 0 )
			   {
				   RESULT = ActivateEmptyObjectInstance( mPerson, "mPerson", ViewToWindow, zSINGLE );
				   //: CREATE ENTITY mPerson.Person
				   RESULT = CreateEntity( mPerson, "Person", zPOS_AFTER );
				   //: mPerson.Person.LastName = "Dumpty"
				   SetAttributeFromString( mPerson, "Person", "LastName", "Dumpty" );
				   //: mPerson.Person.FirstName = "Humpty"
				   SetAttributeFromString( mPerson, "Person", "FirstName", "Humpty" );
				   RESULT = CreateEntity( mPerson, "Address", zPOS_AFTER );
				   //: mPerson.Address.Line1 = "1111"
				   SetAttributeFromString( mPerson, "Address", "Line1", "1111" );
				   //: mPerson.Address.City = "xxxxxxx"
				   SetAttributeFromString( mPerson, "Address", "City", "xxxxxxx" );
				   //: INCLUDE mPerson.PrimaryAddress FROM mPerson.Address
				   RESULT = IncludeSubobjectFromSubobject( mPerson, "PrimaryAddress", mPerson, "Address", zPOS_AFTER );
				   //: COMMIT mPerson
				   RESULT = CommitObjectInstance( mPerson );
				   //: DropObjectInstance( mPerson )
				   DropObjectInstance( mPerson );
			   }

			   SetAttributeFromString( mPerson, "Address", "City", "xxxxxxx" );
			   RESULT = CommitObjectInstance( mPerson );

			   SetAttributeFromString( mPerson, "Address", "City", "Германия" );
			   RESULT = CommitObjectInstance( mPerson );
			   DropObjectInstance( mPerson );

			   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
		       Assert.assertEquals( mPerson.cursor( "Address" ).getAttribute( "City" ).getString(), "Германия" );

			   return 0;
		}
=======
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
                throw ZeidonException.wrapException( e );
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
                    "target/test-classes/testdata/ZENCAs/rAdvTst2.xrp", zSINGLE );

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

            mStudent.cursor( "Student" ).getAttribute( "RoommateQuestionaireReceivedDate").setValue( "" ) ;
            View v = zencas.activateOiFromFile( "mStudent", "target/test-classes/testdata//ZENCAs/mstudent_ac.por" );
            boolean c = mStudent.equalsOi( v );
            Assert.assertTrue( "OIs differ", c );

            System.out.println(RESULT);
            DropView( vTempViewVar_0 );
            DropView( mStudent );
            return 0;
        }


        public int
        testAutoloadFromParent( View     ViewToWindow )
        {
            zVIEW    mTstAutos = new zVIEW( );
            zVIEW    wXferO = new zVIEW( );
            zVIEW    vTempViewVar_0 = new zVIEW( );
            int RESULT=0;

            // When an entity is marked as AUTOLOADFROMPARENT in xod, if the parent entity fk_id is null, then we should not create an
            // entity for this child.
            // We have Student->Person where Person is AUTOLOAD... Student.fk_id_person2 is null so Person should not exists but
            // currently it does although attribute ID in Person is null.

            o_fnLocalBuildmTstAuto( ViewToWindow, vTempViewVar_0, 16406 );
            RESULT = ActivateObjectInstance( mTstAutos, "mTstAutos", ViewToWindow, vTempViewVar_0, zMULTIPLE );
            Assert.assertFalse( "Person exists but should not because parent key is null.", mTstAutos.cursor("Person").checkExistenceOfEntity().isSet());

            DropView( vTempViewVar_0 );
            DropView( mTstAutos );
            return 0;
        }

        public int
        testActivateORStmnt( View     ViewToWindow )
        {
               zVIEW    mTstOR = new zVIEW( );
               zVIEW    vTempViewVar_0 = new zVIEW( );
               int      RESULT = 0;

               /*
                *  We have an activate statement with an OR.
                   ACTIVATE mTstOR Multiple WHERE mTstOR.AdministrativeDivision.ID = 2 OR mTstOR.DegreeTrack.ID = 1212

                   Because of the OR, we believe that the join from STUDENT to STUDENTMAJORDEGREETRACK should be a
                   LEFT JOIN but the sql is created as JOIN.

                SELECT  STUDENT.ID, STUDENT.FKIDADMINISTRATIVE, STUDENTMAJORDEGREETRACK.FK_ID_DEGREETRACK
                FROM  STUDENT JOIN
                      STUDENTMAJORDEGREETRACK ON STUDENTMAJORDEGREETRACK.FKHISTID_STUDENT = STUDENT.ID
                WHERE STUDENT.FKIDADMINISTRATIVE = 2 OR STUDENTMAJORDEGREETRACK.FK_ID_DEGREETRACK = 1212

               */
               o_fnLocalBuildmTstOR( ViewToWindow, vTempViewVar_0 );
               RESULT = ActivateObjectInstance( mTstOR, "mTstOR", ViewToWindow, vTempViewVar_0, zMULTIPLE );
               DropView( vTempViewVar_0 );
               //:NAME VIEW mTstOR "mTstOR"
               SetNameForView( mTstOR, "mTstOR", null, zLEVEL_TASK );
               CursorResult rc  = mTstOR.cursor("Student").setFirst("ID", 28);
               Assert.assertEquals( "ResultSet is missing a student we expect to return", CursorResult.SET, rc );

               return( 0 );
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
        testRestrictWithParentJoin( View     ViewToWindow )
        {
            zVIEW    mTSTs = new zVIEW( );
            zVIEW    mTSTNoJoin = new zVIEW( );
            zVIEW    vTempViewVar_0 = new zVIEW( );
            int RESULT=0;
            boolean bRC;

            // I have two objects. One is using parent join, the other is not.
            // The object "with join" is not doing RESTRICT correctly. We restrict on DegreeTrack but parent entity
            // "StudentMajorDegreeTrack" is empty/missing.
            // On sqlite db, need to run the following: update degreetrack set exceptionflag = 'Y' where id = 1264

           // Activate object that has no parent join. Restrict DegreeTrack on DegreeTrack.ExchangeFlag = "Y".
           //:ACTIVATE mTSTs2 MULTIPLE WHERE  mTSTs2.DegreeTrack.ExchangeFlag = "Y"
           //:    RESTRICTING mTSTs2.DegreeTrack TO mTSTs2.DegreeTrack.ExchangeFlag = "Y"
           o_fnLocalBuildmTSTs( ViewToWindow, vTempViewVar_0 );
           RESULT = ActivateObjectInstance( mTSTNoJoin, "mTSTNoJoin", ViewToWindow, vTempViewVar_0, zMULTIPLE );
           DropView( vTempViewVar_0 );
           SetNameForView( mTSTNoJoin, "mTSTNoJoin", null, zLEVEL_TASK );
           int rc = mTSTNoJoin.cursor( "DegreeTrack" ).setFirst( "ID", 1264, "Person" ).toInt();
            Assert.assertEquals("Object without parent join but has RESTRICT. DegreeTrack should exist but does not.", true, rc >= 0);

           // Activate object that has parent join. Restrict DegreeTrack on DegreeTrack.ExchangeFlag = "Y".
           //:ACTIVATE mTSTs MULTIPLE WHERE  mTSTs.DegreeTrack.ExchangeFlag = "Y"
           //:    RESTRICTING mTSTs.DegreeTrack TO mTSTs.DegreeTrack.ExchangeFlag = "Y"
           o_fnLocalBuildmTSTs( ViewToWindow, vTempViewVar_0 );
           RESULT = ActivateObjectInstance( mTSTs, "mTSTs", ViewToWindow, vTempViewVar_0, zMULTIPLE );
           DropView( vTempViewVar_0 );
           //:NAME VIEW mTSTs "mTSTs"
           SetNameForView( mTSTs, "mTSTs", null, zLEVEL_TASK );
           rc = mTSTs.cursor( "DegreeTrack" ).setFirst( "ID", 1264, "Person" ).toInt();
            Assert.assertEquals("Object with Parent Join and RESTRICT. DegreeTrack should exist but does not.", true, rc >= 0);

            DropView( mTSTs );
            DropView( mTSTNoJoin );

            return 0;
        }

        private int
        o_fnLocalBuildmTSTs( View     vSubtask,
                              zVIEW    vQualObject )
        {
           int      RESULT = 0;

           RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
           CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
           SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
           CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
           SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "DegreeTrack" );
           SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ExceptionFlag" );
           SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
           SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
           CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
           SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "DegreeTrack" );
           CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
           SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "DegreeTrack" );
           SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ExceptionFlag" );
           SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
           SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
           return( 0 );
        }

        public int
        testQualKeyList( View ViewToWindow )
        {
            zVIEW    wXferO = new zVIEW( );
            zVIEW    vQualObject = new zVIEW( );
            zVIEW    lTrnscpt = new zVIEW( );
            int RESULT = 0;

           // Was having trouble with they keylist and the sql statement that was created: had = ( ?, ? ), not in ( ?, ? ).
           // want to make sure this works. Shouldn't need an Assert. If the ActivateObjectInstance works
           // then we will assume all is well.
           RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
           RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
           RESULT = CreateEntity( wXferO, "ActivateID_List", zPOS_AFTER );
           SetAttributeFromInteger( wXferO, "ActivateID_List", "ID", 16406 );
           RESULT = CreateEntity( wXferO, "ActivateID_List", zPOS_AFTER );
           SetAttributeFromInteger( wXferO, "ActivateID_List", "ID", 14901 );

           GenerateQualFromEntityList( vQualObject, wXferO, "ActivateID_List", "", 0 );
           ActivateObjectInstance( lTrnscpt, "lTrnscpt", ViewToWindow, vQualObject, zMULTIPLE );
           DropObjectInstance( lTrnscpt);

           return 0;
        }

        public int
        testSetMatching( View ViewToWindow )
        {
            zVIEW    mPerson = new zVIEW( );
            zVIEW    mPerson2 = new zVIEW( );
            zVIEW    vTempViewVar_0 = new zVIEW( );
            int RESULT=0;
            boolean bRC;

           // Was having trouble with they keylist and the sql statement that was created: had = ( ?, ? ), not in ( ?, ? ).
           // want to make sure this works. Shouldn't need an Assert. If the ActivateObjectInstance works
           // then we will assume all is well.
           o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
           RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
           o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 151289 );
           RESULT = ActivateObjectInstance( mPerson2, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );

           if (mPerson2.cursor("Demographics").checkExistenceOfEntity().isEmpty())
           {
               mPerson2.cursor("Demographics").createEntity();
               SetMatchingAttributesByName( mPerson2, "Demographics", mPerson, "Demographics", zSET_NULL | zSET_NOTNULL );
                Assert.assertEquals("After setMatchingAttributesByName, Demographics.ID should still be NULL.", true, mPerson2.cursor("Demographics").getAttribute("ID").isNull());
           }
           DropObjectInstance(mPerson);
           DropObjectInstance(mPerson2);
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

            if ( mStudent.cursor("Student").getAttribute("Status").getString().equals("I") )
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

//             lAdvisee.writeOiToFile( "/tmp/f1.por", 0 );
             RESULT = lAdvisee.cursor("AdviseeStudentTrack").checkExistenceOfEntity().toInt();
                Assert.assertEquals("AdviseeStudentTrack was not correctly activated when BindAllValues=true.", 0, RESULT);
             DropView( lAdvisee );

            return 0;
        }

        public int
        testLongStringWBindFalse( View     ViewToWindow )
        {
            View    mPerson = new zVIEW( );
            zVIEW   vTempViewVar_0 = new zVIEW( );
            int RESULT=0;

            // The following activate does not work properly when BindAllValues=true.  AdviseeStudentTrack is
            // null.  If BindAllValues=false then it works correctly.
            // Is there a way to set BindAllValues here so it doesn't matter what zeidon.ini is set to?

             o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );

             ActivateOptions options = new ActivateOptions( ViewToWindow.getTask() );
             options.overrideConfigValue( "BindAllValues", "false" );
             options.setActivateFlags( ActivateFlags.MULTIPLE );
             mPerson = ViewToWindow.activateObjectInstance( "mPerson", vTempViewVar_0, options );
             DropView( vTempViewVar_0 );
             mPerson.cursor("Person").getAttribute("EmergencyContactNote").setValue("");
             mPerson.commit();

             mPerson.cursor("Person").getAttribute("EmergencyContactNote").setValue("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
             mPerson.commit();
                //Assert.assertEquals("AdviseeStudentTrack was not correctly activated when BindAllValues=true.", 0, RESULT);
              DropView( mPerson );

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
            String dateStr = wXferO.cursor("Root").getAttribute("dCurrentDate").getString();
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
            szYear = mStudenC.cursor("RegistrationClassCollegeYear").getAttribute("Year").getString();
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
              iRequiredGroup = mMinorTrack.cursor( "RequiredGroup" ).getAttribute("ID").getInteger();
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
              iRequiredGroup = lClsLstC.cursor( "Course" ).getAttribute("ID").getInteger();
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
            wWebXfer.cursor("Work").getAttribute( "FromDate").setValue( wXferO.cursor(  "Root" ).getAttribute(  "dCurrentDate" ).getValue() )  ;

            ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wWebXfer );
            m_ZGLOBAL1_Operation.AddMonthsToDate( wWebXfer, "Work", "FromDate", -6 );
             //iRequiredGroup = lClsLstC.cursor( "Course" ).getAttribute("ID").getInteger();
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

             szDate = mUser.cursor("User").getAttribute("LastLoginDateTime").getString();
             Assert.assertEquals("Error with datetime string length", 14, szDate.length());
             szDate = mUser.cursor("User").getAttribute("OnlineProspectInitialCreatedDate").getString();
             Assert.assertEquals("Error with datetime string length", 8, szDate.length());

             mUser.cursor("User").getAttribute("OnlineProspectInitialCreatedDate").setValue("11/11/2021", "DD/MM/YYYY");
             mUser.cursor("User").getAttribute("LastLoginDateTime").setValue("11/11/2021", "DD/MM/YYYY");

             RESULT = CommitObjectInstance( mUser );

             testTimeFormatting( ViewToWindow );

             return ( 0 );
        }

        public int
        testDerivedAttrCompare( View     ViewToWindow )
        {
            zVIEW    mUser = new zVIEW( );
            zVIEW    mUser2 = new zVIEW( );
            zVIEW    vTempViewVar_0 = new zVIEW( );
            String szRoles;
            int RESULT=0;

             o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
             RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zSINGLE );
             DropView( vTempViewVar_0 );

             CreateViewFromView( mUser2, mUser );

             EntityCursor cursor = mUser.cursor( "Person" );
             //AttributeInstance attrib = cursor.getAttribute( "dPersonRoles" );
             //szRoles = attrib.getString();

             //RESULT = CompareAttributeToAttribute(mUser2, "Person", "dPersonRoles", mUser, "Person", "dPersonRoles");

             // When we call getAttribute(), executeDerivedOperation is not being executed.
             // When we do the attrib.getString() below, then it calls the derived attribute and the compare will work.
             Assert.assertTrue( "Error comparing Derived Attribute, executeDerivedOperation not being executed.", cursor.getAttribute( "dPersonRoles" ).compare( "Instructor, Donor, Alumni, Employee" ) == 0 );
             AttributeInstance attrib2 = cursor.getAttribute( "dPersonRoles" );
             szRoles = attrib2.getString();

             Assert.assertTrue( "Error comparing Derived Attribute, executeDerivedOperation not being executed.", cursor.getAttribute( "dPersonRoles" ).compare( "Instructor, Donor, Alumni, Employee" ) == 0 );

             return 0;
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

             wConList.cursor("ContactList").getAttribute("ListName").setValue( "KellysTest") ;
             wConList.cursor("ContactList").getAttribute("Type").setValue( "W") ;
             wConList.cursor("ContactList").getAttribute("Note").setValue( "This is a test") ;
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

//             RESULT = CommitObjectInstance( wConList );
             iConListID = wConList.cursor("ContactList").getAttribute("ID").getInteger();
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
        testAutoLoadFromParent( View ViewToWindow )
        {

            zVIEW    mTstClss = new zVIEW( );
            zVIEW    vTempViewVar_0 = new zVIEW( );
            int RESULT=0;

            // When the xod had autoloadforparent=Y but the foreign key in the parent was null, the child was still being created.
            // It's fixed but I am still adding this.

            o_fnLocalBuildQualTstClass( ViewToWindow, vTempViewVar_0, 776 );
            RESULT = ActivateObjectInstance( mTstClss, "mTstClss", ViewToWindow, vTempViewVar_0, zSINGLE );
            Assert.assertEquals("Child entity faculty should be null.", mTstClss.cursor("Faculty").checkExistenceOfEntity(), CursorResult.NULL);

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

               ActivateOI_FromFile( mStudent, "mStudent", ViewToWindow, "target/test-classes/testdata//ZENCAs/mStudentDerivedDate.por", zSINGLE );
               ActivateOI_FromFile( mStudenC, "mStudenC", ViewToWindow, "target/test-classes/testdata//ZENCAs/mStudenCDerivedDate.por", zSINGLE );
            SetNameForView( mStudenC, "mStudenC", null, zLEVEL_TASK );
            // I get an error on the derived attribute dLastAuditDateTime because GetVariableFromAttribute returns the
            // date in the format "08/19/2011 09:45:20.0 AM" which causes an error in StoreStringInRecord.
            // If I add the context "YYYYMMDDHHMMSSS" it works fine.
            //GetVariableFromAttribute( sb_szDate, mi_lTempInteger_2, 'S', 101, mStudenC, "SavedDegreeAudit", "CreatedDateTime", "", 0 );            //com.quinsoft.zeidon.InvalidAttributeValueException: Invalid value for attribute
            strGridEditCtl1 = mStudent.cursor( "MajorDegreeTrack" ).getAttribute( "dLastAuditDateTime" ).getString("");
             System.out.println(strGridEditCtl1);
             DropObjectInstance( mStudent );
             DropObjectInstance( mStudenC );
            return 0;
        }


        public int
        testDeletePersonRoot( View     ViewToWindow )
        {
            zVIEW    mPerson = new zVIEW( );
            zVIEW    vTempViewVar_0 = new zVIEW( );
            int RESULT=0;
               //:
               //: This test creates a Person with Address.
               //: Include Address into mPerson.PrimaryAddress which then creates the display entity "PrimaryForPerson" under Address.
               //: When we try to delete mPerson.Person we get the following error:
               //:
               //: com.quinsoft.zeidon.ZeidonException: Entity is not flagged for delete.
               //: EntityDef  = ZENCAs.mPerson.PrimaryForPerson
               //:
               //: But I don't think PrimaryForPerson should need to have "delete flagged".

               o_fnLocalBuildQual_Humpty( ViewToWindow, vTempViewVar_0 );
               RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
               DropView( vTempViewVar_0 );
               // Create temporary person if it doeson't exist.
               if ( RESULT < 0 )
               {
                   RESULT = ActivateEmptyObjectInstance( mPerson, "mPerson", ViewToWindow, zSINGLE );
                   //: CREATE ENTITY mPerson.Person
                   RESULT = CreateEntity( mPerson, "Person", zPOS_AFTER );
                   //: mPerson.Person.LastName = "Dumpty"
                   SetAttributeFromString( mPerson, "Person", "LastName", "Dumpty" );
                   //: mPerson.Person.FirstName = "Humpty"
                   SetAttributeFromString( mPerson, "Person", "FirstName", "Humpty" );
                   RESULT = CreateEntity( mPerson, "Address", zPOS_AFTER );
                   //: mPerson.Address.Line1 = "1111"
                   SetAttributeFromString( mPerson, "Address", "Line1", "1111" );
                   //: mPerson.Address.City = "xxxxxxx"
                   SetAttributeFromString( mPerson, "Address", "City", "xxxxxxx" );
                   //: INCLUDE mPerson.PrimaryAddress FROM mPerson.Address
                   RESULT = IncludeSubobjectFromSubobject( mPerson, "PrimaryAddress", mPerson, "Address", zPOS_AFTER );
                   //: COMMIT mPerson
                   RESULT = CommitObjectInstance( mPerson );
                   //: DropObjectInstance( mPerson )
                   DropObjectInstance( mPerson );
               }

               //: ACTIVATE mPerson WHERE mPerson.Person.LastName = "Dumpty" AND
               //:          mPerson.Person.FirstName = "Humpty"
               o_fnLocalBuildQual_Humpty( ViewToWindow, vTempViewVar_0 );
               RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
               DropView( vTempViewVar_0 );
               //: DELETE ENTITY mPerson.Person
               RESULT = DeleteEntity( mPerson, "Person", zPOS_NEXT );

               return 0;
        }


        public int
        testUpdateNonLatinCharacters( View     ViewToWindow )
        {
            zVIEW    mPerson = new zVIEW( );
            zVIEW    vTempViewVar_0 = new zVIEW( );
            int RESULT=0;
               //:
               //: This test tries to set Address.city to the Cyrillic characters 'Германия'.
               //:
               //: When we do and commit the object, the sql code generated (this is for SqlServer) gets set to:
               //: UPDATE ADDRESS
               //:    SET    MODIFIEDDATETIME = '2016-06-13 09:48:16',
               //:    CITY = '????????'
               //:  WHERE ADDRESS.ID = 235512;

               //: If we do the update in sqlserver, we need to put an "N" in front like the following,
               //: so would we need to do something similar in our code?
               //: UPDATE ADDRESS
                  //: SET CITY = N'Германия'
               //:
               //: If the address is already set to 'Германия' in the database, when we activate, the city looks
               //: corect. Also, when we do the SetAttributeFromString( mPerson, "Address", "City", "Германия" );
               //: the object looks correct, it's when we do the update.

               o_fnLocalBuildQual_Humpty( ViewToWindow, vTempViewVar_0 );
               RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
               //DropView( vTempViewVar_0 );
               // Create temporary person if it doeson't exist.
               if ( RESULT < 0 )
               {
                   RESULT = ActivateEmptyObjectInstance( mPerson, "mPerson", ViewToWindow, zSINGLE );
                   //: CREATE ENTITY mPerson.Person
                   RESULT = CreateEntity( mPerson, "Person", zPOS_AFTER );
                   //: mPerson.Person.LastName = "Dumpty"
                   SetAttributeFromString( mPerson, "Person", "LastName", "Dumpty" );
                   //: mPerson.Person.FirstName = "Humpty"
                   SetAttributeFromString( mPerson, "Person", "FirstName", "Humpty" );
                   RESULT = CreateEntity( mPerson, "Address", zPOS_AFTER );
                   //: mPerson.Address.Line1 = "1111"
                   SetAttributeFromString( mPerson, "Address", "Line1", "1111" );
                   //: mPerson.Address.City = "xxxxxxx"
                   SetAttributeFromString( mPerson, "Address", "City", "xxxxxxx" );
                   //: INCLUDE mPerson.PrimaryAddress FROM mPerson.Address
                   RESULT = IncludeSubobjectFromSubobject( mPerson, "PrimaryAddress", mPerson, "Address", zPOS_AFTER );
                   //: COMMIT mPerson
                   RESULT = CommitObjectInstance( mPerson );
                   //: DropObjectInstance( mPerson )
                   DropObjectInstance( mPerson );
               }

               SetAttributeFromString( mPerson, "Address", "City", "xxxxxxx" );
               RESULT = CommitObjectInstance( mPerson );

               SetAttributeFromString( mPerson, "Address", "City", "Германия" );
               RESULT = CommitObjectInstance( mPerson );
               DropObjectInstance( mPerson );

               RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
               Assert.assertEquals( mPerson.cursor( "Address" ).getAttribute( "City" ).getString(), "Германия" );

               return 0;
        }
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b


private int
o_fnLocalBuildQual_Humpty( View     vSubtask,
                      zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "LastName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Dumpty" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "FirstName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Humpty" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
}


<<<<<<< HEAD
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
		   ActivateOI_FromFile( mFAProf, "mFAProf", ViewToWindow, "target/test-classes/testdata/ZENCAs/mFAProfO.por", zSINGLE );
		   //:NAME VIEW mFAProf "mFAProf"
		   SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );

		   // Check contexts
=======
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
           ActivateOI_FromFile( mFAProf, "mFAProf", ViewToWindow, "target/test-classes/testdata/ZENCAs/mFAProfO.por", zSINGLE );
           //:NAME VIEW mFAProf "mFAProf"
           SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );

           // Check contexts
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
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

<<<<<<< HEAD
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
           Assert.assertEquals("Invalid Set Cursor on Previous <=", CompareAttributeToInteger( mFAProf, "FinAidAward", "Amount", 7000 ), 0);
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

   			   ActivateOI_FromFile( mFAProf, "mFAProf", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFAProfT.por", zSINGLE );
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
 		   mPerson.cursor("Person").getAttribute("Gender").setValue( "F", "") ;
	       strText = mPerson.cursor( "Person" ).getAttribute( "HomePhone" ).getString( "" );

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

   		public int
   		testIncludeCommit( View     ViewToWindow )
   		{
   		   zVIEW    mStudent = new zVIEW( );
   		   zVIEW    lCategry = new zVIEW( );
   		   zVIEW    mClass = new zVIEW( );
   		   zVIEW    mCourse = new zVIEW( );
   		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   zVIEW    mUser = new zVIEW( );
		   zVIEW    wXferO = new zVIEW( );
		   int      lTempInteger_0 = 0;
   		   int      RESULT = 0;

   		   // There is an error on commit that we get after doing an include.
   		   // I set up this test case to show that error but at the moment I am not getting
   		   // an error here. I will keep this for now because I think I will make changes
   		   // to recreate the error.

	        o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
	        RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
	        DropView( vTempViewVar_0 );
	        SetNameForView( mUser, "mUser", null, zLEVEL_TASK );
	        SetNameForView( mUser, "mUser", null, zLEVEL_APPLICATION );


	       o_fnLocalBuildmClass( ViewToWindow, vTempViewVar_0, 31967 );
	       RESULT = ActivateObjectInstance( mClass, "mClass", ViewToWindow, vTempViewVar_0, zSINGLE );
	       DropView( vTempViewVar_0 );
		   SetNameForView( mClass, "mClass", null, zLEVEL_TASK );

	       o_fnLocalBuildmCourse( ViewToWindow, vTempViewVar_0, 657 );
	       RESULT = ActivateObjectInstance( mCourse, "mCourse", ViewToWindow, vTempViewVar_0, zSINGLE );
	       DropView( vTempViewVar_0 );
		   SetNameForView( mCourse, "mCourse", null, zLEVEL_TASK );

   	       RESULT = ExcludeEntity( mClass, "Course", zREPOS_AFTER );
   		   RESULT = IncludeSubobjectFromSubobject( mClass, "Course", mCourse, "Course", zPOS_AFTER );

		   SetAttributeFromString( mCourse, "Course", "Note", "This is a course test" );
   		   RESULT = CommitObjectInstance( mCourse );

		   SetAttributeFromString( mClass, "Class", "Note", "This is a class test" );
		   SetAttributeFromString( mClass, "Course", "Description", "This is a course 2 test" );
   		   RESULT = CommitObjectInstance( mClass );

   		   TraceLineS(" *** AFTER commit mClass ", "");
  		   return( 0 );
   		}

   		public int
   		testIncludeCommitSubentityError( View     ViewToWindow )
   		{
   		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   zVIEW    mUser = new zVIEW( );
		   int      lTempInteger_0 = 0;
   		   int      RESULT = 0;
   		   zVIEW    mFAAdmin = new zVIEW( );
   		   zVIEW    mYearLST = new zVIEW( );
   		   int      lTempInteger_1 = 0;

   		   // mFAAdmin has the includable entity DisbCollegeTerm and under that is DisbCollegeYear.
   		   // DisbCollegeYear is marked as "display" not "includable".
   		   // Because of this, we get an error on Commit of mFAAdmin.

   		   RESULT = ActivateEmptyObjectInstance( mFAAdmin, "mFAAdmin", ViewToWindow, zSINGLE );
   		   RESULT = CreateEntity( mFAAdmin, "FinAidAdmin", zPOS_AFTER );
   		   SetNameForView( mFAAdmin, "mFAAdmin", null, zLEVEL_TASK );

   		   RESULT = ActivateObjectInstance( mYearLST, "mYear", ViewToWindow, 0, zMULTIPLE );
   		   SetNameForView( mYearLST, "mYearLST", null, zLEVEL_TASK );
   		   mYearLST.cursor("CollegeYear").setFirst("ID", 38);
  		   RESULT = IncludeSubobjectFromSubobject( mFAAdmin, "CollegeYear", mYearLST, "CollegeYear", zPOS_AFTER );

	        o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
	        RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
	        DropView( vTempViewVar_0 );
	        SetNameForView( mUser, "mUser", null, zLEVEL_TASK );

	        RESULT = CreateEntity( mFAAdmin, "FinAidAwardPeriod", zPOS_AFTER );
	        SetAttributeFromAttribute( mFAAdmin, "FinAidAwardPeriod", "PeriodDesignator", mFAAdmin, "CollegeYear", "Year" );
	        SetAttributeFromAttribute( mFAAdmin, "FinAidAwardPeriod", "BeginDate", mFAAdmin, "CollegeYear", "BeginDate" );
            SetAttributeFromAttribute( mFAAdmin, "FinAidAwardPeriod", "EndDate", mFAAdmin, "CollegeYear", "EndDate" );
            RESULT = SetCursorFirstEntity( mFAAdmin, "CollegeTerm", "FinAidAdmin" );
            RESULT = IncludeSubobjectFromSubobject( mFAAdmin, "DisbCollegeTerm", mFAAdmin, "CollegeTerm", zPOS_AFTER );
    		RESULT = CommitObjectInstance( mFAAdmin );

  		   return( 0 );
   		}

   		public int
   		testExcludeSubentityError( View     ViewToWindow )
   		{
   		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int      lTempInteger_0 = 0;
   		   int      RESULT = 0;
   		   zVIEW    mFASrc = new zVIEW( );
   		   int      lTempInteger_1 = 0;

   		   // mFAAdmin has the includable entity DisbCollegeTerm and under that is DisbCollegeYear.
   		   // DisbCollegeYear is marked as "display" not "includable".
   		   // Because of this, we get an error on Commit of mFAAdmin.
    	   ActivateOI_FromFile( mFASrc, "mFASrc", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFASrc.json", zSINGLE );//src/test/resources/testdata/ZENCAs
    	   SetNameForView( mFASrc, "mFASrc", null, zLEVEL_TASK );

    	   RESULT = ExcludeEntity( mFASrc, "Scholarship", zREPOS_AFTER );
    	   // We excluded Scholarship which has a subentity of Fund. Fund should not exist but it does
           Assert.assertEquals( mFASrc.cursor("Fund").checkExistenceOfEntity().toInt(), -3 );

  		   return( 0 );
   		}


   		public int
   		testCODXMLImport( View     ViewToWindow )
   		{
   		   zVIEW    vTempViewVar_0 = new zVIEW( );
		   int      lTempInteger_0 = 0;
   		   int      RESULT = 0;
   		   zVIEW    xCOD2 = new zVIEW( );
   		   int      lTempInteger_1 = 0;

   		   // mFAAdmin has the includable entity DisbCollegeTerm and under that is DisbCollegeYear.
   		   // DisbCollegeYear is marked as "display" not "includable".
   		   // Because of this, we get an error on Commit of mFAAdmin.
    	   ActivateOI_FromFile( xCOD2, "xCOD2", ViewToWindow, "target/test-classes/testdata//ZENCAs/CODImport.xml", zSINGLE );//src/test/resources/testdata/ZENCAs
     	   SetNameForView( xCOD2, "xCOD2", null, zLEVEL_TASK );
           //Assert.assertEquals( mFASrc.cursor("Fund").checkExistenceOfEntity().toInt(), -3 );

  		   return( 0 );
   		}


   		public int
   		testLocking( View     ViewToWindow )
   		{
  		   zVIEW    mPersonTST = new zVIEW( );
 		   zVIEW    mPersonTST2 = new zVIEW( );
 		   zVIEW    vTempViewVar_0 = new zVIEW( );
 		   int RESULT=0;
=======
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
//           if ( CompareAttributeToDecimal( mFAProf, "FinAidAwardDisbursement", "Amount", (double) 383.00 ) != 0 )
//           {
//              //:IssueError( ViewToWindow,0,0, "Invalid Set Cursor Subobject on 383.00" )
//              IssueError( ViewToWindow, 0, 0, "Invalid Set Cursor Subobject on 383.00" );
//           }

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
//           if ( CompareAttributeToInteger( mFAProf, "FinAidAwardDisbursement", "ID", 88634 ) != 0 )
//           {
//              //:IssueError( ViewToWindow,0,0, "Invalid Set Cursor Subobject on DisbursementDate and AppliedToAccount" )
//              IssueError( ViewToWindow, 0, 0, "Invalid Set Cursor Subobject on DisbursementDate and AppliedToAccount" );
//           }

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
//           if ( CompareAttributeToInteger( mFAProf, "FinAidAwardDisbursement", "ID", 88634 ) != 0 )
//           {
//              //:IssueError( ViewToWindow,0,0, "Invalid Set Cursor Subobject on DisbursementDate and AwardType" )
//              IssueError( ViewToWindow, 0, 0, "Invalid Set Cursor Subobject on DisbursementDate and AwardType" );
//           }

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
//           if ( CompareAttributeToInteger( mFAProf, "FinAidAward", "ID", 44963 ) != 0 )
//           {
//              //:IssueError( ViewToWindow,0,0, "Invalid Set Cursor on >= and <=" )
//              IssueError( ViewToWindow, 0, 0, "Invalid Set Cursor on >= and <=" );
//           }

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
           Assert.assertEquals("Invalid Set Cursor on Previous <=", CompareAttributeToInteger( mFAProf, "FinAidAward", "Amount", 7000 ), 0);
//           if ( CompareAttributeToInteger( mFAProf, "FinAidAward", "ID", 44965 ) != 0 )
//           {
//              //:IssueError( ViewToWindow,0,0, "Invalid Set Cursor on Previous <=" )
//              IssueError( ViewToWindow, 0, 0, "Invalid Set Cursor on Previous <=" );
//           }

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
//           if ( ZeidonStringCompare( szContextDate8, 1, 0, "20100802", 1, 0, 9 ) != 0 )
//           {
//              //:IssueError( ViewToWindow,0,0, "Invalid AddDaysToDate Value" )
//              IssueError( ViewToWindow, 0, 0, "Invalid AddDaysToDate Value" );
//           }

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
//           if ( DaysInMonth != 31 )
//           {
//              //:IssueError( ViewToWindosheen girlw,0,0, "Invalid DaysInMonth Value" )
//              IssueError( ViewToWindow, 0, 0, "Invalid DaysInMonth Value" );
//           }

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
//           if ( ZeidonStringCompare( szTestString, 1, 0, "Bill Jones", 1, 0, 21 ) != 0 )
//           {
//              //:IssueError( ViewToWindow,0,0, "Invalid Trimming of blank characters" )
//              IssueError( ViewToWindow, 0, 0, "Invalid Trimming of blank characters" );
//           }

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
//           if ( nRC < 0 )
//           {
//              //:IssueError( ViewToWindow,0,0, "Invalid FindStringInAttribute Value" )
//              IssueError( ViewToWindow, 0, 0, "Invalid FindStringInAttribute Value" );
//           }
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

                  ActivateOI_FromFile( mFAProf, "mFAProf", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFAProfT.por", zSINGLE );
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
            mPerson.cursor("Person").getAttribute("Gender").setValue( "F", "") ;
           strText = mPerson.cursor( "Person" ).getAttribute( "HomePhone" ).getString( "" );

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

           public int
           testIncludeCommit( View     ViewToWindow )
           {
              zVIEW    mStudent = new zVIEW( );
              zVIEW    lCategry = new zVIEW( );
              zVIEW    mClass = new zVIEW( );
              zVIEW    mCourse = new zVIEW( );
              zVIEW    vTempViewVar_0 = new zVIEW( );
           zVIEW    mUser = new zVIEW( );
           zVIEW    wXferO = new zVIEW( );
           int      lTempInteger_0 = 0;
              int      RESULT = 0;

              // There is an error on commit that we get after doing an include.
              // I set up this test case to show that error but at the moment I am not getting
              // an error here. I will keep this for now because I think I will make changes
              // to recreate the error.

            o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
            RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
            DropView( vTempViewVar_0 );
            SetNameForView( mUser, "mUser", null, zLEVEL_TASK );
            SetNameForView( mUser, "mUser", null, zLEVEL_APPLICATION );


           o_fnLocalBuildmClass( ViewToWindow, vTempViewVar_0, 31967 );
           RESULT = ActivateObjectInstance( mClass, "mClass", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );
           SetNameForView( mClass, "mClass", null, zLEVEL_TASK );

           o_fnLocalBuildmCourse( ViewToWindow, vTempViewVar_0, 657 );
           RESULT = ActivateObjectInstance( mCourse, "mCourse", ViewToWindow, vTempViewVar_0, zSINGLE );
           DropView( vTempViewVar_0 );
           SetNameForView( mCourse, "mCourse", null, zLEVEL_TASK );

              RESULT = ExcludeEntity( mClass, "Course", zREPOS_AFTER );
              RESULT = IncludeSubobjectFromSubobject( mClass, "Course", mCourse, "Course", zPOS_AFTER );

           SetAttributeFromString( mCourse, "Course", "Note", "This is a course test" );
              RESULT = CommitObjectInstance( mCourse );

           SetAttributeFromString( mClass, "Class", "Note", "This is a class test" );
           SetAttributeFromString( mClass, "Course", "Description", "This is a course 2 test" );
              RESULT = CommitObjectInstance( mClass );

              TraceLineS(" *** AFTER commit mClass ", "");
             return( 0 );
           }

           public int
           testIncludeCommitSubentityError( View     ViewToWindow )
           {
              zVIEW    vTempViewVar_0 = new zVIEW( );
           zVIEW    mUser = new zVIEW( );
           int      lTempInteger_0 = 0;
              int      RESULT = 0;
              zVIEW    mFAAdmin = new zVIEW( );
              zVIEW    mYearLST = new zVIEW( );
              int      lTempInteger_1 = 0;

              // mFAAdmin has the includable entity DisbCollegeTerm and under that is DisbCollegeYear.
              // DisbCollegeYear is marked as "display" not "includable".
              // Because of this, we get an error on Commit of mFAAdmin.

              RESULT = ActivateEmptyObjectInstance( mFAAdmin, "mFAAdmin", ViewToWindow, zSINGLE );
              RESULT = CreateEntity( mFAAdmin, "FinAidAdmin", zPOS_AFTER );
              SetNameForView( mFAAdmin, "mFAAdmin", null, zLEVEL_TASK );

              RESULT = ActivateObjectInstance( mYearLST, "mYear", ViewToWindow, 0, zMULTIPLE );
              SetNameForView( mYearLST, "mYearLST", null, zLEVEL_TASK );
              mYearLST.cursor("CollegeYear").setFirst("ID", 38);
             RESULT = IncludeSubobjectFromSubobject( mFAAdmin, "CollegeYear", mYearLST, "CollegeYear", zPOS_AFTER );

            o_fnLocalBuildmUser( ViewToWindow, vTempViewVar_0, "halll" );
            RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY );
            DropView( vTempViewVar_0 );
            SetNameForView( mUser, "mUser", null, zLEVEL_TASK );

            RESULT = CreateEntity( mFAAdmin, "FinAidAwardPeriod", zPOS_AFTER );
            SetAttributeFromAttribute( mFAAdmin, "FinAidAwardPeriod", "PeriodDesignator", mFAAdmin, "CollegeYear", "Year" );
            SetAttributeFromAttribute( mFAAdmin, "FinAidAwardPeriod", "BeginDate", mFAAdmin, "CollegeYear", "BeginDate" );
            SetAttributeFromAttribute( mFAAdmin, "FinAidAwardPeriod", "EndDate", mFAAdmin, "CollegeYear", "EndDate" );
            RESULT = SetCursorFirstEntity( mFAAdmin, "CollegeTerm", "FinAidAdmin" );
            RESULT = IncludeSubobjectFromSubobject( mFAAdmin, "DisbCollegeTerm", mFAAdmin, "CollegeTerm", zPOS_AFTER );
            RESULT = CommitObjectInstance( mFAAdmin );

             return( 0 );
           }

           public int
           testExcludeSubentityError( View     ViewToWindow )
           {
              zVIEW    vTempViewVar_0 = new zVIEW( );
           int      lTempInteger_0 = 0;
              int      RESULT = 0;
              zVIEW    mFASrc = new zVIEW( );
              int      lTempInteger_1 = 0;

              // mFAAdmin has the includable entity DisbCollegeTerm and under that is DisbCollegeYear.
              // DisbCollegeYear is marked as "display" not "includable".
              // Because of this, we get an error on Commit of mFAAdmin.
           ActivateOI_FromFile( mFASrc, "mFASrc", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFASrc.json", zSINGLE );//src/test/resources/testdata/ZENCAs
           SetNameForView( mFASrc, "mFASrc", null, zLEVEL_TASK );

           RESULT = ExcludeEntity( mFASrc, "Scholarship", zREPOS_AFTER );
           // We excluded Scholarship which has a subentity of Fund. Fund should not exist but it does
           Assert.assertEquals( mFASrc.cursor("Fund").checkExistenceOfEntity().toInt(), -3 );

             return( 0 );
           }


           public int
           testCODXMLImport( View     ViewToWindow )
           {
              zVIEW    vTempViewVar_0 = new zVIEW( );
           int      lTempInteger_0 = 0;
              int      RESULT = 0;
              zVIEW    xCOD2 = new zVIEW( );
              int      lTempInteger_1 = 0;

              // mFAAdmin has the includable entity DisbCollegeTerm and under that is DisbCollegeYear.
              // DisbCollegeYear is marked as "display" not "includable".
              // Because of this, we get an error on Commit of mFAAdmin.
           ActivateOI_FromFile( xCOD2, "xCOD2", ViewToWindow, "target/test-classes/testdata//ZENCAs/CODImport.xml", zSINGLE );//src/test/resources/testdata/ZENCAs
            SetNameForView( xCOD2, "xCOD2", null, zLEVEL_TASK );
           //Assert.assertEquals( mFASrc.cursor("Fund").checkExistenceOfEntity().toInt(), -3 );

             return( 0 );
           }


           public int
           testLocking( View     ViewToWindow )
           {
             zVIEW    mPersonTST = new zVIEW( );
            zVIEW    mPersonTST2 = new zVIEW( );
            zVIEW    vTempViewVar_0 = new zVIEW( );
            int RESULT=0;
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
            StringBuilder sb_Country = new StringBuilder( );
            zVIEW    ZPLOCKO = new zVIEW( );

            RESULT = ActivateObjectInstance( ZPLOCKO, "ZPLOCKO", ViewToWindow, 0, zMULTIPLE );
            //:NAME VIEW ZPLOCKO "ZPLOCKO"
            SetNameForView( ZPLOCKO, "ZPLOCKO", null, zLEVEL_TASK );
<<<<<<< HEAD
			RESULT = ZPLOCKO.cursor( "ZeidonLock" ).setFirst().toInt();
			while ( RESULT > zCURSOR_UNCHANGED )
			{
				ZPLOCKO.cursor("ZeidonLock").deleteEntity();
				RESULT = ZPLOCKO.cursor( "ZeidonLock" ).setNext().toInt();
			}
			ZPLOCKO.commit();

 		   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
 		   RESULT = ActivateObjectInstance( mPersonTST, "mPersonTST", ViewToWindow, vTempViewVar_0, zSINGLE_FOR_UPDATE );
 		   if ( ActivateObjectInstance( mPersonTST2, "mPersonTST", ViewToWindow, vTempViewVar_0, zSINGLE_FOR_UPDATE ) != zLOCK_ERROR )
 			  Assert.assertTrue("Should receive a zLOCK_ERROR.", false);
 		   DropView( vTempViewVar_0 );
   		   return( 0 );
   		}

		//:   VIEW mFAProfO BASED ON LOD mFAProf
		public int
		testIncludePersonCommit( View     ViewToWindow )
		{
			   zVIEW    mPers2 = new zVIEW( );
			   zVIEW    mPers1 = new zVIEW( );
			   zVIEW    vTempViewVar_0 = new zVIEW( );
			   int      RESULT = 0;
			   int      ID1 = 0;
			   int      ID2 = 0;

			   // Activate mPers1
			   // Activate mPers2 (where Person.ID is not mPers1.Person.ID)
			   // Include mPers1.MailingPerson from mPers2.Person.
			   // Include mPers2.MailingPerson from mPers1.Person.
			   // Commit mPers1/mPers2 and re-activate both views.
			   // ERROR - mPers2.MailingPerson does not exist after activate.

			   // ACTIVATE  mPers1
			   RESULT = ActivateObjectInstance( mPers1, "mPersTst", ViewToWindow, 0, zSINGLE );
			   SetNameForView( mPers1, "mPers1", null, zLEVEL_TASK );
			   if ( mPers1.cursor("MailingPerson").checkExistenceOfEntity().isSet())
			   {
				   // If MailingPerson exists exclude and save.
				   mPers1.cursor("MailingPerson").excludeEntity();
				   mPers1.commit();
			   }
			   // ACTIVATE  mPers2  WHERE mPers2.Person.ID != mPers1.Person.ID
			   ID1 = mPers1.cursor("Person").getAttribute("ID").getInteger();
			   o_BuildPersonNoID( ViewToWindow, vTempViewVar_0, mPers1.cursor("Person").getAttribute("ID").getInteger() );
			   RESULT = ActivateObjectInstance( mPers2, "mPersTst", ViewToWindow, vTempViewVar_0, zSINGLE );
			   DropView( vTempViewVar_0 );
			   ID2 = mPers2.cursor("Person").getAttribute("ID").getInteger();
			   SetNameForView( mPers2, "mPers2", null, zLEVEL_TASK );
			   if ( mPers2.cursor("MailingPerson").checkExistenceOfEntity().isSet())
			   {
				   // If MailingPerson exists exclude and save.
				   mPers2.cursor("MailingPerson").excludeEntity();
				   mPers2.commit();
			   }
			   // Include MailingPerson from each other.
			   RESULT = IncludeSubobjectFromSubobject( mPers1, "MailingPerson", mPers2, "Person", zPOS_AFTER );
			   RESULT = IncludeSubobjectFromSubobject( mPers2, "MailingPerson", mPers1, "Person", zPOS_AFTER );
			   mPers1.commit();
			   mPers2.commit();
			   DropView( mPers1 );
			   DropView( mPers2 );

			   // Activate mPers1/mPers2 again.
			   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, ID1 );
			   RESULT = ActivateObjectInstance( mPers1, "mPersTst", ViewToWindow, vTempViewVar_0, zSINGLE );
			   DropView( vTempViewVar_0 );
			   SetNameForView( mPers1, "mPers1", null, zLEVEL_TASK );
			   o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, ID2 );
			   RESULT = ActivateObjectInstance( mPers2, "mPersTst", ViewToWindow, vTempViewVar_0, zSINGLE );
			   DropView( vTempViewVar_0 );
			   SetNameForView( mPers2, "mPers2", null, zLEVEL_TASK );

			   // Check if MailingPerson exists for each view.
			   if ( !mPers1.cursor("MailingPerson").checkExistenceOfEntity().isSet())
		 			  Assert.assertTrue("mPers1 MailingPerson should exist after commit but does not.", false);

			   if ( !mPers2.cursor("MailingPerson").checkExistenceOfEntity().isSet())
		 			  Assert.assertTrue("mPers2 MailingPerson should exist after commit but does not.", false);

			   // Exclude MailingPerson for both views for cleanup for next time... even though we do a check above.
			   if ( mPers1.cursor("MailingPerson").checkExistenceOfEntity().isSet())
			   {
				   mPers1.cursor("MailingPerson").excludeEntity();
				   mPers1.commit();
			   }
			   if ( mPers2.cursor("MailingPerson").checkExistenceOfEntity().isSet())
			   {
				   mPers2.cursor("MailingPerson").excludeEntity();
				   mPers2.commit();
			   }

			   return 0;
		}

		private int
		o_BuildPersonNoID( View     vSubtask,
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
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "!=" );
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
			ActivateOI_FromFile( mFAProfO, "mFAProf", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFAProf.por", zSINGLE );

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
=======
            RESULT = ZPLOCKO.cursor( "ZeidonLock" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            {
                ZPLOCKO.cursor("ZeidonLock").deleteEntity();
                RESULT = ZPLOCKO.cursor( "ZeidonLock" ).setNext().toInt();
            }
            ZPLOCKO.commit();

            o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, 18808 );
            RESULT = ActivateObjectInstance( mPersonTST, "mPersonTST", ViewToWindow, vTempViewVar_0, zSINGLE_FOR_UPDATE );
            if ( ActivateObjectInstance( mPersonTST2, "mPersonTST", ViewToWindow, vTempViewVar_0, zSINGLE_FOR_UPDATE ) != zLOCK_ERROR )
               Assert.assertTrue("Should receive a zLOCK_ERROR.", false);
            DropView( vTempViewVar_0 );
              return( 0 );
           }

        //:   VIEW mFAProfO BASED ON LOD mFAProf
        public int
        testIncludePersonCommit( View     ViewToWindow )
        {
               zVIEW    mPers2 = new zVIEW( );
               zVIEW    mPers1 = new zVIEW( );
               zVIEW    vTempViewVar_0 = new zVIEW( );
               int      RESULT = 0;
               int      ID1 = 0;
               int      ID2 = 0;

               // Activate mPers1
               // Activate mPers2 (where Person.ID is not mPers1.Person.ID)
               // Include mPers1.MailingPerson from mPers2.Person.
               // Include mPers2.MailingPerson from mPers1.Person.
               // Commit mPers1/mPers2 and re-activate both views.
               // ERROR - mPers2.MailingPerson does not exist after activate.

               // ACTIVATE  mPers1
               RESULT = ActivateObjectInstance( mPers1, "mPersTst", ViewToWindow, 0, zSINGLE );
               SetNameForView( mPers1, "mPers1", null, zLEVEL_TASK );
               if ( mPers1.cursor("MailingPerson").checkExistenceOfEntity().isSet())
               {
                   // If MailingPerson exists exclude and save.
                   mPers1.cursor("MailingPerson").excludeEntity();
                   mPers1.commit();
               }
               // ACTIVATE  mPers2  WHERE mPers2.Person.ID != mPers1.Person.ID
               ID1 = mPers1.cursor("Person").getAttribute("ID").getInteger();
               o_BuildPersonNoID( ViewToWindow, vTempViewVar_0, mPers1.cursor("Person").getAttribute("ID").getInteger() );
               RESULT = ActivateObjectInstance( mPers2, "mPersTst", ViewToWindow, vTempViewVar_0, zSINGLE );
               DropView( vTempViewVar_0 );
               ID2 = mPers2.cursor("Person").getAttribute("ID").getInteger();
               SetNameForView( mPers2, "mPers2", null, zLEVEL_TASK );
               if ( mPers2.cursor("MailingPerson").checkExistenceOfEntity().isSet())
               {
                   // If MailingPerson exists exclude and save.
                   mPers2.cursor("MailingPerson").excludeEntity();
                   mPers2.commit();
               }
               // Include MailingPerson from each other.
               RESULT = IncludeSubobjectFromSubobject( mPers1, "MailingPerson", mPers2, "Person", zPOS_AFTER );
               RESULT = IncludeSubobjectFromSubobject( mPers2, "MailingPerson", mPers1, "Person", zPOS_AFTER );
               mPers1.commit();
               mPers2.commit();
               DropView( mPers1 );
               DropView( mPers2 );

               // Activate mPers1/mPers2 again.
               o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, ID1 );
               RESULT = ActivateObjectInstance( mPers1, "mPersTst", ViewToWindow, vTempViewVar_0, zSINGLE );
               DropView( vTempViewVar_0 );
               SetNameForView( mPers1, "mPers1", null, zLEVEL_TASK );
               o_fnLocalBuildQualmPerson( ViewToWindow, vTempViewVar_0, ID2 );
               RESULT = ActivateObjectInstance( mPers2, "mPersTst", ViewToWindow, vTempViewVar_0, zSINGLE );
               DropView( vTempViewVar_0 );
               SetNameForView( mPers2, "mPers2", null, zLEVEL_TASK );

               // Check if MailingPerson exists for each view.
               if ( !mPers1.cursor("MailingPerson").checkExistenceOfEntity().isSet())
                       Assert.assertTrue("mPers1 MailingPerson should exist after commit but does not.", false);

               if ( !mPers2.cursor("MailingPerson").checkExistenceOfEntity().isSet())
                       Assert.assertTrue("mPers2 MailingPerson should exist after commit but does not.", false);

               // Exclude MailingPerson for both views for cleanup for next time... even though we do a check above.
               if ( mPers1.cursor("MailingPerson").checkExistenceOfEntity().isSet())
               {
                   mPers1.cursor("MailingPerson").excludeEntity();
                   mPers1.commit();
               }
               if ( mPers2.cursor("MailingPerson").checkExistenceOfEntity().isSet())
               {
                   mPers2.cursor("MailingPerson").excludeEntity();
                   mPers2.commit();
               }

               return 0;
        }

        private int
        o_BuildPersonNoID( View     vSubtask,
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
           SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "!=" );
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
            ActivateOI_FromFile( mFAProfO, "mFAProf", ViewToWindow, "target/test-classes/testdata//ZENCAs/mFAProf.por", zSINGLE );

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
           int        iLevel1 = 0;
           int        iLevel2 = 0;
           int        lTempInteger_1 = 0;

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
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b

           RESULT = GetRecursEnts( TstRecur );

           /*
<<<<<<< HEAD
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
			   iLevel1++;
			   iLevel2 = 0;
=======
           while ( RESULT > zCURSOR_UNCHANGED )
           {
               iLevel1++;
               iLevel2 = 0;
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
               lTempInteger_1 = CheckExistenceOfEntity( TstRecur, "FinancialApprovalRoleChild" );
               if ( lTempInteger_1 == 0 )
               {

                  //:SetViewToSubobject( vReportDefRecurs, "PartialReportEntityChild" )
                  SetViewToSubobject( TstRecur, "FinancialApprovalRoleChild" );
<<<<<<< HEAD
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
				if ( lClsLstC.cursor("Class").getAttribute( "wCourseID").compare( "" )  == 0 )
				{
					lClsLstC.cursor("Class").getAttribute("wCourseID").setValue( lClsLstC.cursor(  "Course" ).getAttribute(  "ID" ).getValue() )  ;
					lClsLstC.cursor("Class").getAttribute("wCourseNumber").setValue( lClsLstC.cursor(  "Course" ).getAttribute(  "Number" ).getValue() )  ;
				}

				if ( lClsLstC.cursor("Class").getAttribute( "wCrossListedFlag").compare( "Y" )  != 0 )
				{
					RESULT = lClsLstC.cursor( "CrossListedCourse" ).setFirst().toInt();
					while ( RESULT > CursorResult.UNCHANGED.toInt() )
					{
						RESULT = lClsLstCT.cursor( "Class" ).setFirst().toInt();
						if ( RESULT > CursorResult.UNCHANGED.toInt() )
						{
							// !!! compareAttribute will give an error when one of the entities is null so I wasn't
							// able to use this when I ran this test on MyENC.  I had to use the CompareAttributeToA...
							while ( RESULT > CursorResult.UNCHANGED.toInt() &&
							        ( lClsLstC.cursor("Course").getAttribute( "ID").compare( lClsLstCT.cursor("CrossListedCourse").getAttribute( "ID" ).getValue() )  != 0 ) )
								while ( RESULT > CursorResult.UNCHANGED.toInt() && ( CompareAttributeToAttribute( lClsLstCT, "Course", "ID", lClsLstC, "CrossListedCourse", "ID" ) != 0 ) )
								{
									RESULT = lClsLstCT.cursor( "Class" ).setNext().toInt();
								}

						}
						if ( RESULT < CursorResult.UNCHANGED.toInt() )
						{
							lClsLstCT.cursor( "Class" ).includeSubobject( lClsLstC.cursor( "Class" ), CursorPosition.NEXT );
							szTempString_0 = lClsLstC.cursor( "CrossListedCourse" ).getAttribute( "Number" ).getString();
							szTempString_0 = lClsLstCT.cursor( "Class" ).getAttribute( "Section" ).getString();
							szTempString_0 = szTempString_0 + szTempString_1;
							lClsLstCT.cursor("Class").getAttribute("wClassNumberTopicSection").setValue( szTempString_0) ;
							lClsLstCT.cursor("Class").getAttribute("wCrossListedFlag").setValue( "Y") ;
							lClsLstCT.cursor( "CorrespondingCrossListedCourse" ).includeSubobject( lClsLstC.cursor( "CrossListedCourse" ), CursorPosition.NEXT );
							lClsLstCT.cursor("Class").getAttribute("wCourseID").setValue( lClsLstC.cursor(  "CrossListedCourse" ).getAttribute(  "ID" ).getValue() )  ;
							lClsLstCT.cursor("Class").getAttribute("wCourseNumber").setValue( lClsLstC.cursor(  "CrossListedCourse" ).getAttribute(  "Number" ).getValue() )  ;
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
				TermslClsLstC.cursor("Class").getAttribute( "wClassNumberTopicSection").setValue( lClsLstC.cursor(  "Class" ).getAttribute(  "wClassNumberTopicSection" ).getValue() )  ;
				TermslClsLstC.cursor("Class").getAttribute( "wCrossListedFlag").setValue( lClsLstC.cursor(  "Class" ).getAttribute(  "wCrossListedFlag" ).getValue() )  ;
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
		o_fnLocalBuildmTstAuto( View     vSubtask,
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
=======
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
           int        iLevel1 = 0;
           int        iLevel2 = 0;
           int        lTempInteger_1 = 0;

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
                if ( lClsLstC.cursor("Class").getAttribute( "wCourseID").compare( "" )  == 0 )
                {
                    lClsLstC.cursor("Class").getAttribute("wCourseID").setValue( lClsLstC.cursor(  "Course" ).getAttribute(  "ID" ).getValue() )  ;
                    lClsLstC.cursor("Class").getAttribute("wCourseNumber").setValue( lClsLstC.cursor(  "Course" ).getAttribute(  "Number" ).getValue() )  ;
                }

                if ( lClsLstC.cursor("Class").getAttribute( "wCrossListedFlag").compare( "Y" )  != 0 )
                {
                    RESULT = lClsLstC.cursor( "CrossListedCourse" ).setFirst().toInt();
                    while ( RESULT > CursorResult.UNCHANGED.toInt() )
                    {
                        RESULT = lClsLstCT.cursor( "Class" ).setFirst().toInt();
                        if ( RESULT > CursorResult.UNCHANGED.toInt() )
                        {
                            // !!! compareAttribute will give an error when one of the entities is null so I wasn't
                            // able to use this when I ran this test on MyENC.  I had to use the CompareAttributeToA...
                            while ( RESULT > CursorResult.UNCHANGED.toInt() &&
                                    ( lClsLstC.cursor("Course").getAttribute( "ID").compare( lClsLstCT.cursor("CrossListedCourse").getAttribute( "ID" ).getValue() )  != 0 ) )
                                while ( RESULT > CursorResult.UNCHANGED.toInt() && ( CompareAttributeToAttribute( lClsLstCT, "Course", "ID", lClsLstC, "CrossListedCourse", "ID" ) != 0 ) )
                                {
                                    RESULT = lClsLstCT.cursor( "Class" ).setNext().toInt();
                                }

                        }
                        if ( RESULT < CursorResult.UNCHANGED.toInt() )
                        {
                            lClsLstCT.cursor( "Class" ).includeSubobject( lClsLstC.cursor( "Class" ), CursorPosition.NEXT );
                            szTempString_0 = lClsLstC.cursor( "CrossListedCourse" ).getAttribute( "Number" ).getString();
                            szTempString_0 = lClsLstCT.cursor( "Class" ).getAttribute( "Section" ).getString();
                            szTempString_0 = szTempString_0 + szTempString_1;
                            lClsLstCT.cursor("Class").getAttribute("wClassNumberTopicSection").setValue( szTempString_0) ;
                            lClsLstCT.cursor("Class").getAttribute("wCrossListedFlag").setValue( "Y") ;
                            lClsLstCT.cursor( "CorrespondingCrossListedCourse" ).includeSubobject( lClsLstC.cursor( "CrossListedCourse" ), CursorPosition.NEXT );
                            lClsLstCT.cursor("Class").getAttribute("wCourseID").setValue( lClsLstC.cursor(  "CrossListedCourse" ).getAttribute(  "ID" ).getValue() )  ;
                            lClsLstCT.cursor("Class").getAttribute("wCourseNumber").setValue( lClsLstC.cursor(  "CrossListedCourse" ).getAttribute(  "Number" ).getValue() )  ;
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
                TermslClsLstC.cursor("Class").getAttribute( "wClassNumberTopicSection").setValue( lClsLstC.cursor(  "Class" ).getAttribute(  "wClassNumberTopicSection" ).getValue() )  ;
                TermslClsLstC.cursor("Class").getAttribute( "wCrossListedFlag").setValue( lClsLstC.cursor(  "Class" ).getAttribute(  "wCrossListedFlag" ).getValue() )  ;
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
        o_fnLocalBuildmTstAuto( View     vSubtask,
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
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b

private int
o_fnLocalBuildmTstOR( View     vSubtask,
                      zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "2" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "DegreeTrack" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "1212" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );

   /////////////////////////
   /*
   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "2" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "166" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   */
   return( 0 );
}

<<<<<<< HEAD
		private int
		o_fnLocalBuildQualTstClass(
				View     vSubtask,
                zVIEW    vQualObject,
                int      lTempInteger_0)
		{
			   int      RESULT = 0;

			   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
			   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Class" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Class" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
			   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
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
		o_fnLocalBuildmCourse( View     vSubtask,
		                      zVIEW    vQualObject,
		                      int      lTempInteger_1 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Course" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Course" );
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
		o_fnLocalBuildQualmFASrc( View     vSubtask,
		                       zVIEW    vQualObject,
		                       int      lTempInteger_0 )
		{
		   int      RESULT = 0;

		   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
		   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidSource" );
		   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidSource" );
		   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
		   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
		   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		   return( 0 );
		}

		private int
		o_BuildQualmStudent( View     vSubtask,
		                     zVIEW    vQualObject )
		{
			int      RESULT = 0;

			   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
			   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
			   //CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   //SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "7" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
			   /*
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "8" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "11" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
			   */
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "PhiDeltaLambdaFlag" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
			   return( 0 );
		}
		private int
		o_BuildQualmStudent2( View     vSubtask,
		                      zVIEW    vQualObject )
		{
			int      RESULT = 0;

			   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
			   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "8" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );

			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "9" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );

			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
			   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
			   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "GeneralNote" );
			   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
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
		o_fnLocalBuildmFAProf2( View     vSubtask,
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
=======
        private int
        o_fnLocalBuildQualTstClass(
                View     vSubtask,
                zVIEW    vQualObject,
                int      lTempInteger_0)
        {
               int      RESULT = 0;

               RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
               CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Class" );
               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Class" );
               SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
               SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
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
        o_fnLocalBuildmCourse( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_1 )
        {
           int      RESULT = 0;

           RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
           CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
           SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Course" );
           CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
           SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Course" );
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
        o_fnLocalBuildQualmFASrc( View     vSubtask,
                               zVIEW    vQualObject,
                               int      lTempInteger_0 )
        {
           int      RESULT = 0;

           RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
           CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
           SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidSource" );
           CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
           SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidSource" );
           SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
           SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
           SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
           return( 0 );
        }

        private int
        o_BuildQualmStudent( View     vSubtask,
                             zVIEW    vQualObject )
        {
            int      RESULT = 0;

               RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
               CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
               //CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               //SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
               SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
               SetAttributeFromString( vQualObject, "QualAttrib", "Value", "7" );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
               /*
               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
               SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
               SetAttributeFromString( vQualObject, "QualAttrib", "Value", "8" );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
               SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
               SetAttributeFromString( vQualObject, "QualAttrib", "Value", "11" );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
               */
               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
               SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "PhiDeltaLambdaFlag" );
               SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
               return( 0 );
        }
        private int
        o_BuildQualmStudent2( View     vSubtask,
                              zVIEW    vQualObject )
        {
            int      RESULT = 0;

               RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
               CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
               SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
               SetAttributeFromString( vQualObject, "QualAttrib", "Value", "8" );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );

               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
               SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
               SetAttributeFromString( vQualObject, "QualAttrib", "Value", "9" );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );

               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
               CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
               SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "GeneralNote" );
               SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
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
        o_fnLocalBuildmFAProf2( View     vSubtask,
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
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b


public int
mUser_ActivateUserLST(  View     ViewToSubtask )
{
   zVIEW    mUser = new zVIEW( );
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   // kkk just a test for IssueError.
   //IssueError( ViewToSubtask, 0, 0, "Testing IssueError" );

   // In this test I have two exact activate statements except one is MULTIPLE, the other is ROOTONLYMULTIPLE and
   // both contain a mUser.UserGroup EXISTS (if I don't have the EXISTS, then there is no error).
   // In the MULTIPLE activate everything seems fine but in the ROOTONLYMULTIPLE activate, we do not have
   // the JOIN as part of the select statement (see below statement). This works with sqlite but does
   // not work with sqlserver where we get an error:
   // Msg 156, Level 15, State 1, Line 9
   // Incorrect syntax near the keyword 'ON'.
   // I tried commenting out the following lines in AbstractSqlHandler.activatingWithJoins()
   //if ( activateFlags.contains( ActivateFlags.fROOT_ONLY ) )
   //    return false;
   // Which does give me a correct sql statement here but then I get asserts errors in
   // testActivateRootOnly, because the activate isn't ROOT ONLY (but in mUser_Activate... it is root only... odd)


   /*
   ACTIVATE mUser MULTIPLE
            WHERE ( mUser.User.Status = "A" ) AND
            (mUser.User.UserName = "ddennis" OR mUser.User.UserName = "testerpl" OR mUser.User.UserName = "monicak")
             AND mUser.UserGroup EXISTS

SELECT z_USER.ID, z_USER.USERNAME, z_USER.PASSWORD, z_USER.JAVAPASSWORD, z_USER.STATUS, z_USER.AUTHORIZATIONLEVEL,
       z_USER.EMAILADDRESS, z_USER.EMAILPASSWORD, z_USER.EMAILUSERNAME, z_USER.LOGINAUTHENTICTNTY,
       z_USER.MODIFIEDDATETIME, z_USER.OVERRIDESTATUS, z_USER.ONLNPRSPCTINTLCRTD, z_USER.SECURITYANSWER1,
       z_USER.SECURITYANSWER2, z_USER.SECURITYANSWER3, z_USER.SECURITYQUESTION1, z_USER.SECURITYQUESTION2,
       z_USER.SECURITYQUESTION3, z_USER.SMTPSERVER, z_USER.ACTIVE, z_USER.WEBBROADCASTMSSGFL, z_USER.CREATEDBY,
       z_USER.CREATEDDATETIME, z_USER.LASTMODIFIEDBY, z_USER.MERGEOUTPUTFILENAM, z_USER.LASTLOGINDATETIME,
       z_USER.FKIDADMINISTRATIVE, z_USER.FK_ID_PERSON, z_USER.PRSPCTINTLAPLCTNPR, z_USER.z_NOTE
FROM  z_USER JOIN
       MM_USERGROUP_CONTANSMMBR_Z_USE ON MM_USERGROUP_CONTANSMMBR_Z_USE.FK_ID_Z_USER = z_USER.ID JOIN
       USERGROUP ON USERGROUP.ID = MM_USERGROUP_CONTANSMMBR_Z_USE.FK_ID_USERGROUP
WHERE  ( z_USER.STATUS = 'A' )  AND  ( z_USER.USERNAME = 'halll' OR z_USER.USERNAME = 'hardeem' OR z_USER.USERNAME =
       'murphyr' )  AND USERGROUP.ID  IS NOT NULL ;

   ACTIVATE mUser RootOnlyMultiple
            WHERE ( mUser.User.Status = "A" ) AND
            (mUser.User.UserName = "ddennis" OR mUser.User.UserName = "testerpl" OR mUser.User.UserName = "monicak")
             AND mUser.UserGroup EXISTS

SELECT z_USER.ID, z_USER.USERNAME, z_USER.PASSWORD, z_USER.JAVAPASSWORD, z_USER.STATUS, z_USER.AUTHORIZATIONLEVEL,
       z_USER.EMAILADDRESS, z_USER.EMAILPASSWORD, z_USER.EMAILUSERNAME, z_USER.LOGINAUTHENTICTNTY,
       z_USER.MODIFIEDDATETIME, z_USER.OVERRIDESTATUS, z_USER.ONLNPRSPCTINTLCRTD, z_USER.SECURITYANSWER1,
       z_USER.SECURITYANSWER2, z_USER.SECURITYANSWER3, z_USER.SECURITYQUESTION1, z_USER.SECURITYQUESTION2,
       z_USER.SECURITYQUESTION3, z_USER.SMTPSERVER, z_USER.ACTIVE, z_USER.WEBBROADCASTMSSGFL, z_USER.CREATEDBY,
       z_USER.CREATEDDATETIME, z_USER.LASTMODIFIEDBY, z_USER.MERGEOUTPUTFILENAM, z_USER.LASTLOGINDATETIME,
       z_USER.FKIDADMINISTRATIVE, z_USER.FK_ID_PERSON, z_USER.PRSPCTINTLAPLCTNPR, z_USER.z_NOTE
FROM  z_USER, MM_USERGROUP_CONTANSMMBR_Z_USE ON MM_USERGROUP_CONTANSMMBR_Z_USE.FK_ID_Z_USER = z_USER.ID, USERGROUP ON
       USERGROUP.ID = MM_USERGROUP_CONTANSMMBR_Z_USE.FK_ID_USERGROUP
WHERE  ( z_USER.STATUS = 'A' )  AND  ( z_USER.USERNAME = 'halll' OR z_USER.USERNAME = 'hardeem' OR z_USER.USERNAME =
       'murphyr' )  AND USERGROUP.ID  IS NOT NULL ;


    */
   omUser_fnLocalBuildQualActivateUserLST( ViewToSubtask, vTempViewVar_0 );
   RESULT = ActivateObjectInstance( mUser, "mUser", ViewToSubtask, vTempViewVar_0, zMULTIPLE );

   DropView( mUser );
   RESULT = ActivateObjectInstance( mUser, "mUser", ViewToSubtask, vTempViewVar_0, zACTIVATE_ROOTONLY_MULTIPLE );
   DropView( vTempViewVar_0 );

   //://NAME VIEW  mUser  "mUserLST"
   //:SetNameForView( mUser, "mUserLST", ViewToSubtask, zLEVEL_APPLICATION )
   SetNameForView( mUser, "mUserLST", ViewToSubtask, zLEVEL_APPLICATION );
   return( 0 );
// END
}


private int
omUser_fnLocalBuildQualActivateUserLST( View     vSubtask,
                           zVIEW    vQualObject )
{
<<<<<<< HEAD
	   int      RESULT = 0;

	   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
	   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "User" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Status" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "A" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "UserName" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "halll" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "UserName" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "hardeem" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "UserName" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "murphyr" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "UserGroup" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
	   return( 0 );
=======
       int      RESULT = 0;

       RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
       CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "User" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
       SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Status" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Value", "A" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
       SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "UserName" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Value", "halll" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
       SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "UserName" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Value", "hardeem" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
       SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "UserName" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Value", "murphyr" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "UserGroup" );
       SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
       return( 0 );
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b
}


public int
TEST_TemporalSaveIssuemSAProf( View     ViewToWindow )
{
   zVIEW    mSAChrgILST = new zVIEW( );
   //:VIEW mSAProfS    BASED ON LOD mSAProf
   zVIEW    mSAProfS = new zVIEW( );
   //:VIEW mSAProf     BASED ON LOD mSAProf
   zVIEW    mSAProf = new zVIEW( );
   //:VIEW mTermLST    BASED ON LOD mTerm
   zVIEW    mTermLST = new zVIEW( );
   //:VIEW mTerm       BASED ON LOD mTerm
   zVIEW    mTerm = new zVIEW( );
   //:INTEGER SAProfID
   int      SAProfID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_3 = new zVIEW( );


 // After the commit, even though mSAProf.BillingPeriod is created, mSAProf.AppliedBillingPeriod is missing AND
 // BillingPeriod is NOT created in the database.
 // Also, interestingly... at IBOE, when I run this code, it creates the BillingPeriod correctly but the foreign key in StudentAccountTransApplied is missing.
 // It's the opposite of what happens here. I don't suppose that matters.
 // If we do a CreateEntity (not temporal) all is fine.

 // Assuming that this issue is related to mFAProfTemporalPerProfileFinAidAwardPeriodPathTest test since both of these object are complicated and have
 // specific entities down two paths in the object.

   //:// Code to test commit of new TransApplied entries when Billing Period has been added.
   //:// We will use any mSAProf example with SA Transactions as a base to create the new entry.

   //:// Activate list of College Terms for adding BillingPeriod and position on last, for which the mSAprof is probably missing..
   //:ACTIVATE mTermLST Multiple
   //:     RESTRICTING mTermLST.Class TO mTermLST.Class.ID = 0
   RESULT = ActivateObjectInstance( mTermLST, "lTermLST", ViewToWindow, 0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   SetNameForView( mTermLST, "lTermLST", null, zLEVEL_TASK );
   OrderEntityForView( mTermLST, "CollegeTerm", "YearSemester D" );
   RESULT = SetCursorFirstEntity( mTermLST, "CollegeTerm", "" );

   //:// Activate any mSAProf as our base.
   //:ACTIVATE mSAProfS WHERE mSAProfS.StudentAccountTransApplied EXISTS
   //:      RESTRICTING mSAProfS.BillingPeriod TO mSAProfS.PeriodCollegeTerm.ID = mTermLST.CollegeTerm.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mTermLST, "CollegeTerm", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_3x( ViewToWindow, vTempViewVar_1, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSAProfS, "mSAProf", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   SetNameForView( mSAProfS, "mSAProfS", null, zLEVEL_TASK );

   //:// Delete any current Billing Term entry for the College Term on which we're positioned.
   //:IF mSAProfS.BillingPeriod EXISTS
   lTempInteger_1 = CheckExistenceOfEntity( mSAProfS, "BillingPeriod" );
   if ( lTempInteger_1 == 0 )
   {
      //:// There are entries for the Term, so delete them all.
      //:FOR EACH mSAProfS.StudentAccountTransApplied
      RESULT = SetCursorFirstEntity( mSAProfS, "StudentAccountTransApplied", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      {
         //:IF mSAProfS.AppliedBillingPeriod.ID = mSAProfS.BillingPeriod.ID
         if ( CompareAttributeToAttribute( mSAProfS, "AppliedBillingPeriod", "ID", mSAProfS, "BillingPeriod", "ID" ) == 0 )
         {
            RESULT = DeleteEntity( mSAProfS, "StudentAccountTransApplied", zREPOS_NONE );
         }

         RESULT = SetCursorNextEntity( mSAProfS, "StudentAccountTransApplied", "" );
      }

      RESULT = CommitObjectInstance( mSAProfS );
      RESULT = DeleteEntity( mSAProfS, "BillingPeriod", zPOS_NEXT );
      RESULT = CommitObjectInstance( mSAProfS );
      {MutableInt mi_SAProfID = new MutableInt( SAProfID );
             GetIntegerFromAttribute( mi_SAProfID, mSAProfS, "StudentAccountProfile", "ID" );
      SAProfID = mi_SAProfID.intValue( );}
      DropObjectInstance( mSAProfS );

      //:// Get a fresh start.
      //:ACTIVATE mSAProfS WHERE mSAProfS.StudentAccountTransApplied EXISTS
      o_fnLocalBuildQual_4( ViewToWindow, vTempViewVar_2 );
      RESULT = ActivateObjectInstance( mSAProfS, "mSAProf", ViewToWindow, vTempViewVar_2, zSINGLE );
      DropView( vTempViewVar_2 );
      SetNameForView( mSAProfS, "mSAProfS", null, zLEVEL_TASK );
   }

   //:END

   //:// Position on a StudentAccountTransApplied for the source of the create transaction.
   CreateViewFromView( mSAProf, mSAProfS );
   SetNameForView( mSAProf, "mSAProfCopy", null, zLEVEL_TASK );
   RESULT = SetCursorFirstEntity( mSAProf, "StudentAccountTransApplied", "" );
   RESULT = SetCursorLastEntity( mSAProfS, "StudentAccountTransApplied", "" );

   //:// Add a BillingPeriod for CollegeTerm and add two StudentAccountTransApplied entries.
   RESULT = CreateEntity( mSAProfS, "BillingPeriod", zPOS_AFTER );
   RESULT = IncludeSubobjectFromSubobject( mSAProfS, "PeriodCollegeTerm", mTermLST, "CollegeTerm", zPOS_AFTER );
   //:mSAProfS.BillingPeriod.PeriodDesignator = mTermLST.CollegeTerm.YearSemester
   SetAttributeFromAttribute( mSAProfS, "BillingPeriod", "PeriodDesignator", mTermLST, "CollegeTerm", "YearSemester" );
   //:mSAProfS.BillingPeriod.BeginDate        = mSAProf.PeriodCollegeTerm.CourseStartDate
   SetAttributeFromAttribute( mSAProfS, "BillingPeriod", "BeginDate", mSAProf, "PeriodCollegeTerm", "CourseStartDate" );
   //:mSAProfS.BillingPeriod.EndDate          = mSAProf.PeriodCollegeTerm.CourseEndDate
   SetAttributeFromAttribute( mSAProfS, "BillingPeriod", "EndDate", mSAProf, "PeriodCollegeTerm", "CourseEndDate" );

   CreateTemporalEntity( mSAProfS, "StudentAccountTransApplied", zPOS_AFTER );
   //CreateEntity( mSAProfS, "StudentAccountTransApplied", zPOS_AFTER );
   RESULT = IncludeSubobjectFromSubobject( mSAProfS, "SATransactionCode", mSAProf, "SATransactionCode", zPOS_AFTER );
   RESULT = IncludeSubobjectFromSubobject( mSAProfS, "DebitGLChartEntry", mSAProf, "DebitGLChartEntry", zPOS_AFTER );
   RESULT = IncludeSubobjectFromSubobject( mSAProfS, "CreditGLChartEntry", mSAProf, "CreditGLChartEntry", zPOS_AFTER );
   RESULT = IncludeSubobjectFromSubobject( mSAProfS, "AppliedBillingPeriod", mSAProfS, "BillingPeriod", zPOS_AFTER );
   SetAttributeFromAttribute( mSAProfS, "StudentAccountTransApplied", "Description", mSAProf, "StudentAccountTransApplied", "Description" );
   SetAttributeFromAttribute( mSAProfS, "StudentAccountTransApplied", "Instrument", mSAProf, "StudentAccountTransApplied", "Instrument" );
   SetAttributeFromAttribute( mSAProfS, "StudentAccountTransApplied", "DateEntered", mSAProf, "StudentAccountTransApplied", "DateEntered" );
   SetAttributeFromAttribute( mSAProfS, "StudentAccountTransApplied", "Amount", mSAProf, "StudentAccountTransApplied", "Amount" );
   SetAttributeFromAttribute( mSAProfS, "StudentAccountTransApplied", "Source", mSAProf, "StudentAccountTransApplied", "Source" );
   AcceptSubobject( mSAProfS, "StudentAccountTransApplied" );

   DropView( mSAProf );

   RESULT = CommitObjectInstance( mSAProfS );

   // After the commit, even though mSAProf.BillingPeriod is created, mSAProf.AppliedBillingPeriod is missing AND
   // BillingPeriod is NOT created in the database.
   // Also, interestingly... at IBOE, when I run this code, it creates the BillingPeriod correctly but the foreign key in StudentAccountTransApplied is missing.
   // It's the opposite of what happens here. I don't suppose that matters.
   // If we do a CreateEntity (not temporal) all is fine.
   Assert.assertTrue( "ERROR! After commit AppliedBillingPeriod.ID is missing.", mSAProfS.cursor("AppliedBillingPeriod").getAttribute("ID").getValue() != null );
   Assert.assertTrue( "ERROR! After commit BillingPeriod.ID is missing.", mSAProfS.cursor("BillingPeriod").getAttribute("ID").getValue() != null );

   //:ACTIVATE mSAProf WHERE mSAProf.StudentAccountProfile.ID = mSAProfS.StudentAccountProfile.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mSAProfS, "StudentAccountProfile", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   o_fnLocalBuildQual_5( ViewToWindow, vTempViewVar_3, lTempInteger_2 );
   RESULT = ActivateObjectInstance( mSAProf, "mSAProf", ViewToWindow, vTempViewVar_3, zSINGLE );
   DropView( vTempViewVar_3 );
   SetNameForView( mSAProf, "mSAProfVerify", null, zLEVEL_TASK );
   return( 0 );
}

<<<<<<< HEAD
	private int
	o_fnLocalBuildQual_5( View     vSubtask,
	                      zVIEW    vQualObject,
	                      int      lTempInteger_2 )
	{
	   int      RESULT = 0;

	   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
	   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountProfile" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
	   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
	   return( 0 );
	}

	private int
	o_fnLocalBuildQual_4( View     vSubtask,
	                      zVIEW    vQualObject )
	{
	   int      RESULT = 0;

	   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
	   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountTransApplied" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
	   return( 0 );
	}

	private int
	o_fnLocalBuildQual_3x( View     vSubtask,
	                      zVIEW    vQualObject,
	                      int      lTempInteger_0 )
	{
	   int      RESULT = 0;

	   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
	   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountTransApplied" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
	   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "BillingPeriod" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PeriodCollegeTerm" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
	   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
	   return( 0 );
	}

	private int
	o_fnLocalBuildQual_2( View     vSubtask,
	                      zVIEW    vQualObject )
	{
	   int      RESULT = 0;

	   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
	   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Class" );
	   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
	   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Class" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
	   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
	   return( 0 );
	}

	public int testUpdateForeignKeys( View     ViewToWindow  )
	{
		   zVIEW    mFAReq = new zVIEW( );
		   int      RESULT = 0;
		   zVIEW    mAdmDiv = new zVIEW( );
		   zVIEW    mFATrk = new zVIEW( );
		   zVIEW    vTempViewVar_0 = new zVIEW( );
	
		   // KJS 10/12/21
		   // We have an entity FinAidTrackRequirement. The key for this is not a generated key, it is the two foreign keys:
		   // FKIDFINAIDREQUIREM
		   // FK_ID_FINAIDTRACK
		   // When I try to exclude/include the related entity FinAidRequirement, I get an error on save
		   // because FKIDFINAIDREQUIREM is part of the key.
		   // Seems like this should be allowed (it is in C world).

		    o_fnLocalBuildmAdmDiv( ViewToWindow, vTempViewVar_0, 1 );
		    RESULT = ActivateObjectInstance( mAdmDiv, "mAdmDiv", ViewToWindow, vTempViewVar_0, zSINGLE );
	   		DropView( vTempViewVar_0 );
		   
		   //:ACTIVATE  mFATrk EMPTY 
		   RESULT = ActivateEmptyObjectInstance( mFATrk, "mFATrk", ViewToWindow, zSINGLE );
		   RESULT = CreateEntity( mFATrk, "FinAidTrack", zPOS_AFTER );
		   SetAttributeFromString( mFATrk, "FinAidTrack", "Name", "TESTTRACK" );
		   RESULT = CreateEntity( mFATrk, "FinAidTrackRequirement", zPOS_AFTER );
		   SetAttributeFromInteger( mFATrk, "FinAidTrackRequirement", "SequenceNumber", 1 );
		   //:ACTIVATE  mFAReq MULTIPLE
		   RESULT = ActivateObjectInstance( mFAReq, "mFAReq", ViewToWindow, 0, zACTIVATE_ROOTONLY_MULTIPLE );
		   RESULT = IncludeSubobjectFromSubobject( mFATrk, "FinAidRequirement", mFAReq, "FinAidRequirement", zPOS_AFTER );
		   RESULT = IncludeSubobjectFromSubobject( mFATrk, "AdministrativeDivision", mAdmDiv, "AdministrativeDivision", zPOS_AFTER );
		   RESULT = CommitObjectInstance( mFATrk );
	
		   RESULT = ExcludeEntity( mFATrk, "FinAidRequirement", zREPOS_AFTER );
		   RESULT = SetCursorNextEntity( mFAReq, "FinAidRequirement", "" );
		   RESULT = IncludeSubobjectFromSubobject( mFATrk, "FinAidRequirement", mFAReq, "FinAidRequirement", zPOS_AFTER );
		   RESULT = CommitObjectInstance( mFATrk );
		   return( 0 );
	}
=======
    private int
    o_fnLocalBuildQual_5( View     vSubtask,
                          zVIEW    vQualObject,
                          int      lTempInteger_2 )
    {
       int      RESULT = 0;

       RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
       CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountProfile" );
       SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
       SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
       return( 0 );
    }

    private int
    o_fnLocalBuildQual_4( View     vSubtask,
                          zVIEW    vQualObject )
    {
       int      RESULT = 0;

       RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
       CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountTransApplied" );
       SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
       return( 0 );
    }

    private int
    o_fnLocalBuildQual_3x( View     vSubtask,
                          zVIEW    vQualObject,
                          int      lTempInteger_0 )
    {
       int      RESULT = 0;

       RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
       CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountTransApplied" );
       SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
       CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "BillingPeriod" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PeriodCollegeTerm" );
       SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
       SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
       return( 0 );
    }

    private int
    o_fnLocalBuildQual_2( View     vSubtask,
                          zVIEW    vQualObject )
    {
       int      RESULT = 0;

       RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
       CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Class" );
       CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
       SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Class" );
       SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
       SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
       return( 0 );
    }

    public int testUpdateForeignKeys( View     ViewToWindow  )
    {
           zVIEW    mFAReq = new zVIEW( );
           int      RESULT = 0;
           zVIEW    mAdmDiv = new zVIEW( );
           zVIEW    mFATrk = new zVIEW( );
           zVIEW    vTempViewVar_0 = new zVIEW( );

           // KJS 10/12/21
           // We have an entity FinAidTrackRequirement. The key for this is not a generated key, it is the two foreign keys:
           // FKIDFINAIDREQUIREM
           // FK_ID_FINAIDTRACK
           // When I try to exclude/include the related entity FinAidRequirement, I get an error on save
           // because FKIDFINAIDREQUIREM is part of the key.
           // Seems like this should be allowed (it is in C world).

            o_fnLocalBuildmAdmDiv( ViewToWindow, vTempViewVar_0, 1 );
            RESULT = ActivateObjectInstance( mAdmDiv, "mAdmDiv", ViewToWindow, vTempViewVar_0, zSINGLE );
               DropView( vTempViewVar_0 );

           //:ACTIVATE  mFATrk EMPTY
           RESULT = ActivateEmptyObjectInstance( mFATrk, "mFATrk", ViewToWindow, zSINGLE );
           RESULT = CreateEntity( mFATrk, "FinAidTrack", zPOS_AFTER );
           SetAttributeFromString( mFATrk, "FinAidTrack", "Name", "TESTTRACK" );
           RESULT = CreateEntity( mFATrk, "FinAidTrackRequirement", zPOS_AFTER );
           SetAttributeFromInteger( mFATrk, "FinAidTrackRequirement", "SequenceNumber", 1 );
           //:ACTIVATE  mFAReq MULTIPLE
           RESULT = ActivateObjectInstance( mFAReq, "mFAReq", ViewToWindow, 0, zACTIVATE_ROOTONLY_MULTIPLE );
           RESULT = IncludeSubobjectFromSubobject( mFATrk, "FinAidRequirement", mFAReq, "FinAidRequirement", zPOS_AFTER );
           RESULT = IncludeSubobjectFromSubobject( mFATrk, "AdministrativeDivision", mAdmDiv, "AdministrativeDivision", zPOS_AFTER );
           RESULT = CommitObjectInstance( mFATrk );

           RESULT = ExcludeEntity( mFATrk, "FinAidRequirement", zREPOS_AFTER );
           RESULT = SetCursorNextEntity( mFAReq, "FinAidRequirement", "" );
           RESULT = IncludeSubobjectFromSubobject( mFATrk, "FinAidRequirement", mFAReq, "FinAidRequirement", zPOS_AFTER );
           RESULT = CommitObjectInstance( mFATrk );
           return( 0 );
    }
>>>>>>> ad8fc523e50c54cbf52ee9d6f5db2591c5d2205b

   }
}
