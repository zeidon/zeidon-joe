/**
 *
 */
package com.quinsoft.zeidon.test;

import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
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
               //zeidonSystem.getObjectEngine().getHomeDirectory() + "/SWAU/mSAProfT.por", zSINGLE );
			   //omSAProf_fnLocalBuildQual_29( mSAProf, vTempViewVar_0 );
			   //RESULT = ActivateObjectInstance( mSAProfT, "mSAProf", mSAProf, vTempViewVar_0, zSINGLE );
			   //DropView( vTempViewVar_0 );
			   //:COMMIT mSAProfT
			   RESULT = CommitObjectInstance( mSAProfT );
			   DropObjectInstance( mSAProfT );

			   // This is an object where the entity STUDENT (under the root) exists.
			   // Excluding Student and then committing should cause an error.
		   	   ActivateOI_FromFile( mSAProfT, "mSAProf", ViewToWindow,
		                zeidonSystem.getObjectEngine().getHomeDirectory() + "/SWAU/mSAProfTWStud.json", zSINGLE );
			   //:EXCLUDE mSAProfT.Student
			   RESULT = ExcludeEntity( mSAProfT, "Student", zREPOS_AFTER );

			   //:COMMIT mSAProfT
			   RESULT = CommitObjectInstance( mSAProfT );
			   DropObjectInstance( mSAProfT );
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
