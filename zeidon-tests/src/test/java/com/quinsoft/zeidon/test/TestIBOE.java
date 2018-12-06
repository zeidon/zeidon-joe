/**
 *
 */
package com.quinsoft.zeidon.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityInstance;
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
		      zVIEW    mSAProf2 = new zVIEW( );
		      //:VIEW dSATrans BASED ON LOD  dSATrans
		      zVIEW    dSATrans = new zVIEW( );
			  zVIEW    wXferO = new zVIEW( );
			  zVIEW    sHost = new zVIEW( );
		      int      nRC = 0;
		      //:STRING ( 256 ) szFileName
		      String   szFileName = null;
		      int      RESULT = 0;
		      int      iCnt1 = 0;
		      int      iCnt2 = 0;

              // Creating wXferO and sHost simply to keep from getting errors when looking at mSAProf in the browser.
			  RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
			  RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
			  SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );

			  RESULT = ActivateObjectInstance( sHost, "sHost", ViewToWindow, 0, zSINGLE );
			  SetNameForView( sHost, "sHost", null, zLEVEL_TASK );
			  if ( sHost.cursor("Host").checkExistenceOfEntity().toInt() != 0 )
				  sHost.cursor("Host").createEntity();

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
		      SetNameForView( mSAProf, "mSAProf", null, zLEVEL_TASK );
		      mSAProf.cursor("StudentAccountTransApplied").orderEntities( "TransactionDate D" );
		      CreateViewFromView( mSAProf2, mSAProf );		      
		      SetNameForView( mSAProf2, "mSAProf2", null, zLEVEL_TASK );
		      
		      RESULT = SetCursorFirstEntity( mSAProf, "StudentAccountTransApplied", "" );
              ViewToWindow.log().info("before delete");
              /*
              for (EntityInstance ei : mSAProf2.getHierEntityList()) {
            	  ViewToWindow.log().info(ei.getEntityDef().getName() + " " + ei.getKeyString()); 
              } */
              
              ViewToWindow.log().info("after delete/before commit");
		      //RESULT = SetCursorLastEntity( mSAProf, "StudentAccountTransApplied", "" );
		      //RESULT = DeleteEntity( mSAProf, "StudentAccountTransApplied", zPOS_NEXT );
		      mSAProf.cursor("StudentAccountTransApplied").deleteEntity( CursorPosition.NEXT );
		      /*
              for (EntityInstance ei : mSAProf2.getHierEntityList()) {
            	  ViewToWindow.log().info(ei.getEntityDef().getName() + " " + ei.getKeyString()); 
              } */
              ViewToWindow.log().info("after commit");
		      // CHECK SQL AND SEE IF WE GET TWO DELETE
		      //:COMMIT mSAProf
		      RESULT = CommitObjectInstance( mSAProf );
		      /*
              for (EntityInstance ei : mSAProf2.getHierEntityList()) {
                	  ViewToWindow.log().info(ei.getEntityDef().getName() + " " + ei.getKeyString()); 
              } */
		      mSAProf.cursor("StudentAccountTransApplied").orderEntities( "TransactionDate D" );
		      //OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D" );
		      iCnt1 = 0;
              for ( EntityInstance ei : mSAProf.cursor( "StudentAccountProfile" ).getChildren( "StudentAccountTransApplied"))
              {
            	  iCnt1++;
              }
		      return( 0 );
		   // END
		   } 

   }

}
