/**
 *
 */
package com.quinsoft.zeidon.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.vml.zVIEW;

// Just for temporary testing...
//import com.jacob.com.*;
//import com.jacob.activeX.*;


/**
 * @author DG
 *
 */
public class TestSWAU
{
	Task         zencas;
	Task         zeidonSystem;
	View         mFASrc;
	ObjectEngine oe;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
        oe = JavaObjectEngine.getInstance();
        zencas = oe.createTask( "SWAU" );

		zeidonSystem = oe.getSystemTask();
	}

	@Test
	public void testIntegrityRules()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mSAProf" );
		SwauVmlTester tester = new SwauVmlTester( testview );
		tester.testIntegrityRules( testview );
        System.out.println("===== Finished testIntegrityRules ========");
	}

	@Test
	public void testIntegrityRules2()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mSAProf" );
		SwauVmlTester tester = new SwauVmlTester( testview );
		tester.testIntegrityRules2( testview );
        System.out.println("===== Finished testIntegrityRules2 ========");
	}

	@Test
	public void testSAProfIncludeMealPlan()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mSAProf" );
		SwauVmlTester tester = new SwauVmlTester( testview );
		tester.testSAProfIncludeMealPlan( testview );
        System.out.println("===== Finished testSAProfIncludeMealPlan ========");
	}


	private class SwauVmlTester extends VmlObjectOperations
	{
		public SwauVmlTester( View view )
		{
			super( view );
		}

		public int
		testIntegrityRules( View ViewToWindow )
		{
			zVIEW    mSAProfT      = new zVIEW( );
			int RESULT=0;

			   // This is an object where the entity STUDENT (under the root) has been restricted. STUDENT
			   // is a 1 to 1 with root, but the commit should not cause an error.
		   	   ActivateOI_FromFile( mSAProfT, "mSAProf", ViewToWindow,
		                zeidonSystem.getObjectEngine().getHomeDirectory() + "/SWAU/mSAProfT.json", zSINGLE );
			   RESULT = CommitObjectInstance( mSAProfT );
			   DropObjectInstance( mSAProfT );

			   // This is an object where the entity STUDENT (under the root) exists.
			   // Excluding Student and then committing should cause an error.
		   	   ActivateOI_FromFile( mSAProfT, "mSAProf", ViewToWindow,
		                zeidonSystem.getObjectEngine().getHomeDirectory() + "/SWAU/mSAProfTWStud.json", zSINGLE );
			   //:EXCLUDE mSAProfT.Student
			   RESULT = ExcludeEntity( mSAProfT, "Student", zREPOS_AFTER );

			   try {
    			   RESULT = CommitObjectInstance( mSAProfT );
    		       Assert.fail( "Commit did not throw error with missing child");
				}
				catch (ZeidonException e) {
				    // Do nothing because this exception is expected.
				}			   //:COMMIT mSAProfT
			   DropObjectInstance( mSAProfT );
			return 0;
		}

		public int
		testIntegrityRules2( View ViewToWindow )
		{
			zVIEW    mPerson      = new zVIEW( );
		    String   szPermanent = null;
			int RESULT=0;
		    int      lTempInteger_1 = 0;
		    int      lTempInteger_2 = 0;

            // In this example, we exclude/include PrimaryAddress (which is under root Person),
		    // Doing that changes the displayed entity PrimaryForPerson (Person) under Address.
		    // Because PrimaryForPerson is not set as include/exclude in the object, the commit fails on
		    // this entity. If I change PrimaryForPerson to be inc/exc, then the commit seems to work.
		    ActivateOI_FromFile( mPerson, "mPerson", ViewToWindow,
		                zeidonSystem.getObjectEngine().getHomeDirectory() + "/SWAU/mPersonPrimaryAddress.json", zSINGLE );
		   	   SetNameForView( mPerson, "mPerson", null, zLEVEL_TASK );
		   	   
		       RESULT = SetCursorFirstEntity( mPerson, "Address", "" );
		       while ( RESULT > zCURSOR_UNCHANGED )
		       { 
		          //:IF mPerson.Address.Type = "Permanent"
		          if ( CompareAttributeToString( mPerson, "Address", "Type", "Permanent" ) == 0 )
		          { 
		             //:szPermanent = "Y" 
		              {StringBuilder sb_szPermanent;
		             if ( szPermanent == null )
		                sb_szPermanent = new StringBuilder( 32 );
		             else
		                sb_szPermanent = new StringBuilder( szPermanent );
		                         ZeidonStringCopy( sb_szPermanent, 1, 0, "Y", 1, 0, 2 );
		             szPermanent = sb_szPermanent.toString( );}
		             //:// Check if this address is their primary.
		             lTempInteger_1 = CheckExistenceOfEntity( mPerson, "PrimaryAddress" );
		             if ( lTempInteger_1 == 0 )
		             { 
		                //:// The permanent address and the primary address are not the same.
		                //:// Make the permanent address the primary address.
		                if ( CompareAttributeToAttribute( mPerson, "PrimaryAddress", "ID", mPerson, "Address", "ID" ) != 0 )
		                { 
		                   //:// Getting an error on PrimaryForPerson (cardinality) so relink before excluding.
		                   lTempInteger_2 = CheckExistenceOfEntity( mPerson, "PrimaryForPerson" );
		                   if ( lTempInteger_2 == 0 )
		                   { 
		                      //:RelinkInstanceToInstance( mPerson, "PrimaryForPerson", mPerson, "Person" )
		                      RelinkInstanceToInstance( mPerson, "PrimaryForPerson", mPerson, "Person" );
		                   } 

		                   RESULT = ExcludeEntity( mPerson, "PrimaryAddress", zREPOS_AFTER );
		                   RESULT = IncludeSubobjectFromSubobject( mPerson, "PrimaryAddress", mPerson, "Address", zPOS_AFTER );
		                   RESULT = CommitObjectInstance( mPerson );
		                } 
		             } 
		          } 

		          RESULT = SetCursorNextEntity( mPerson, "Address", "" );
		          //:END 
		       } 
		                   
			   DropObjectInstance( mPerson );
			return 0;
		}

		public int
		testSAProfIncludeMealPlan( View ViewToWindow )
		{
			zVIEW    mSAProf      = new zVIEW( );
			zVIEW    lSAMealPLST      = new zVIEW( );
		   int RESULT=0;

	   	   //ActivateOI_FromFile( mSAProf, "mSAProf", ViewToWindow,
		   //             zeidonSystem.getObjectEngine().getHomeDirectory() + "/SWAU/mSAProf_testerpl.por", zSINGLE );
	   	   ActivateOI_FromFile( mSAProf, "mSAProf", ViewToWindow,
		                zeidonSystem.getObjectEngine().getHomeDirectory() + "/SWAU/mSAProf.por", zSINGLE );
		   SetNameForView( mSAProf, "mSAProf", null, zLEVEL_TASK );
	   	   ActivateOI_FromFile( lSAMealPLST, "mSAMealP", ViewToWindow,
		                zeidonSystem.getObjectEngine().getHomeDirectory() + "/SWAU/lSAMealPLST.por", zSINGLE );
           SetNameForView( lSAMealPLST, "lSAMealPLST", null, zLEVEL_TASK );
/************************************/
/*
           RESULT = SetCursorFirstEntity( lSAMealPLST, "MealPlan", "" );
	       RESULT = SetCursorFirstEntity( mSAProf, "TermOfMealPlan", "" );

           RESULT = ExcludeEntity( mSAProf, "MealPlan", zREPOS_AFTER );
	       RESULT = IncludeSubobjectFromSubobject( mSAProf, "MealPlan", lSAMealPLST, "MealPlan", zPOS_AFTER );
	       RESULT = SetCursorFirstEntity( mSAProf, "TermOfMealPlan", "" );
           RESULT = ExcludeEntity( mSAProf, "MealPlan", zREPOS_AFTER );
	       RESULT = IncludeSubobjectFromSubobject( mSAProf, "MealPlan", lSAMealPLST, "MealPlan", zPOS_AFTER );
*/
/************************************/
	       CursorResult csrRC = null;
	       csrRC = lSAMealPLST.cursor( "MealPlan" ).setFirst(  );
	       csrRC = mSAProf.cursor( "TermOfMealPlan" ).setFirst(  );

	       mSAProf.cursor("MealPlan").excludeEntity( CursorPosition.NEXT );
	       mSAProf.cursor( "MealPlan" ).includeSubobject( lSAMealPLST.cursor( "MealPlan" ), CursorPosition.NEXT );
	       csrRC = mSAProf.cursor( "TermOfMealPlan" ).setFirst(  );
	       // If I put this here, then this works. The first MealPlan is hidden (excluded) which we shouldn't be positioned on.
	       mSAProf.cursor("MealPlan").setNext();

	       mSAProf.cursor("MealPlan").excludeEntity( CursorPosition.NEXT );
	       mSAProf.cursor( "MealPlan" ).includeSubobject( lSAMealPLST.cursor( "MealPlan" ), CursorPosition.NEXT );

	       return 0;
		}

   }
}
