/**
 *
 */
package com.hfi.cheetah;

import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.utils.QualificationBuilder;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.vml.zVIEW;



/**
 * @author DG
 *
 */
public class TestCheetah
{
	Task         cheetah;
	Task         zeidonSystem;
	View         mFASrc;
	ObjectEngine oe;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
        oe = ZeidonObjectEngineConfiguration.getObjectEngine();
        cheetah = oe.createTask( "Cheetah" );

		zeidonSystem = oe.getSystemTask();
	}

	@Test
	public void testActivate()
	{
	    new QualificationBuilder( cheetah )
	            .setViewOd( "mVerItem" )
//	            .restricting( "VerificationItem" )
	            .addAttribQual( "BusinessUnit", "BusinessUnitID", "=", 10 )
	            .activate();
	}

//	@Test
	public void testInclude()
	{
	    View         testview;
		testview = cheetah.activateEmptyObjectInstance( "mUser" );
		TestVml tester = new TestVml( testview );
		tester.TEST_Zeidon( testview );
        System.out.println("===== Finished TEST_Zeidon ========");
	}

	private class TestVml extends VmlObjectOperations
	{
		public TestVml( View view )
		{
			super( view );
		}

		public int
		TEST_Zeidon( View     ViewToWindow )
		{
		   zVIEW    mUser = new zVIEW( );
		   //:VIEW lPersonSelectLST BASED ON LOD mPerson
		   zVIEW    lPersonSelectLST = new zVIEW( );
		   int      RESULT = 0;


		   //:// load 2 views. mUser the TARGET and lPersonLST the source.
		   //:// the point of this is to include person under User and commit it. That will work
		   //:// then Exclude person from user. incldue a different person under user and commit. That will fail
		   //:ActivateOI_FromFile( mUser, "mUser", ViewToWindow, "./mUser.por", zSINGLE )
		   ActivateOI_FromFile( mUser, "mUser", ViewToWindow, "./src/test/resources/testdata/Cheetah/ois/mUser.por", zSINGLE );
		   //:NAME VIEW  mUser  "mUser"
		   SetNameForView( mUser, "mUser", null, zLEVEL_TASK );
		   //:ActivateOI_FromFile( lPersonSelectLST, "mPerson", ViewToWindow, "./lPersonSelectLST.por", zMULTIPLE )
		   ActivateOI_FromFile( lPersonSelectLST, "mPerson", ViewToWindow, "./src/test/resources/testdata/Cheetah/ois/lPersonSelectLST.por", zMULTIPLE );
		   //:NAME VIEW  lPersonSelectLST  "lPersonSelectLST"
		   SetNameForView( lPersonSelectLST, "lPersonSelectLST", null, zLEVEL_TASK );

		   //:SET CURSOR FIRST lPersonSelectLST.Person
		   RESULT = lPersonSelectLST.cursor( "Person" ).setFirst().toInt();
		   //:INCLUDE mUser.Person  FROM  lPersonSelectLST.Person
		   RESULT = IncludeSubobjectFromSubobject( mUser, "Person", lPersonSelectLST, "Person", zPOS_AFTER );
           CommitObjectInstance( mUser );
		   //://   COMMIT mUser

		   //:EXCLUDE mUser.Person
		   RESULT = ExcludeEntity( mUser, "Person", zREPOS_AFTER );
		   //:SET CURSOR NEXT lPersonSelectLST.Person
		   RESULT = lPersonSelectLST.cursor( "Person" ).setNext().toInt();
		   //:INCLUDE mUser.Person  FROM  lPersonSelectLST.Person
		   RESULT = IncludeSubobjectFromSubobject( mUser, "Person", lPersonSelectLST, "Person", zPOS_AFTER );
		   CommitObjectInstance( mUser );
		   return( 0 );
		// //   COMMIT mUser
		// END
		}
	}
}