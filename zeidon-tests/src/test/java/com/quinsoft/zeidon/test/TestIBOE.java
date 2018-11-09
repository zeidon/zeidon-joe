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
public class TestIBOE
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
        zencas = oe.createTask( "IBOE" );

		zeidonSystem = oe.getSystemTask();
	}

	@Test
	public void testIntegrityRules()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mSAProf" );
		IBOEVmlTester tester = new IBOEVmlTester( testview );
		tester.TEST_SAVE_Student( testview );
        System.out.println("===== Finished TEST_SAVE_Student ========");
	}



	private class IBOEVmlTester extends VmlObjectOperations
	{
		public IBOEVmlTester( View view )
		{
			super( view );
		}


		   public int 
		   TEST_SAVE_Student( View     ViewToWindow )
		   {
		      zVIEW    mSAStu = new zVIEW( );
		      //:VIEW mSAProf  BASED ON LOD mSAProf  
		      zVIEW    mSAProf = new zVIEW( );
		      //:VIEW dSATrans BASED ON LOD  dSATrans
		      zVIEW    dSATrans = new zVIEW( );
		      //:SHORT nRC
		      int      nRC = 0;
		      //:STRING ( 256 ) szFileName
		      String   szFileName = null;
		      int      RESULT = 0;


		      //:szFileName = "c:\temp\mSAProf.por"
		       {StringBuilder sb_szFileName;
		      if ( szFileName == null )
		         sb_szFileName = new StringBuilder( 32 );
		      else
		         sb_szFileName = new StringBuilder( szFileName );
		         ZeidonStringCopy( sb_szFileName, 1, 0, "target/test-classes/testdata//IBOE/mSAProf.por", 1, 0, 257 );
		      szFileName = sb_szFileName.toString( );}
		      //:nRC = ActivateOI_FromFile( mSAProf, "mSAProf", ViewToWindow, szFileName, zMULTIPLE )
		      nRC = ActivateOI_FromFile( mSAProf, "mSAProf", ViewToWindow, szFileName, zMULTIPLE );
		      //:NAME VIEW mSAProf "mSAProfTEST"
		      SetNameForView( mSAProf, "mSAProfTEST", null, zLEVEL_TASK );

		      //:SET CURSOR FIRST mSAProf.StudentAccountTransApplied 
		      RESULT = SetCursorFirstEntity( mSAProf, "StudentAccountTransApplied", "" );
		      //:/*
		      //:SET CURSOR FIRST mSAProf.PeriodTransApplied
		      //:               WHERE mSAProf.PeriodTransApplied.ID = mSAProf.StudentAccountTransApplied.ID 
		      //:IF RESULT >= zCURSOR_SET 
		      //:         DropEntity( mSAProf, "PeriodTransApplied", zREPOS_NONE )
		      //:END */
		      //:DELETE ENTITY  mSAProf.StudentAccountTransApplied
		      RESULT = DeleteEntity( mSAProf, "StudentAccountTransApplied", zPOS_NEXT );

		      //:COMMIT mSAProf
		      RESULT = CommitObjectInstance( mSAProf );
		      return( 0 );
		   // END
		   } 

   }

}
