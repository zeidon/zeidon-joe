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
		      int      RESULT = 0;
		      int      iCnt1 = 0;
		      int      iCnt2 = 0;
		      
		      /*
		       * There is a problem when deleting the first StudentAccountTransApplied (id 3590) in mSAProf. This entity is linked under "BillingPeriod" as well.
		       * On the mSAProf.commit we run cleanupOI
		       * This seems to incorrectly set a prevHierInstance for the second StudentAccountTransApplied (id 3587).
		       * It actually seems to set the prevHierInstance correctly for StudentAccountTransApplied (id 3587) and it's linked entity "PeriodTransApplied" under "BillingPeriod"
		       * but then when looping through the linked entities in cleanupOI we set the prevHierInstance again for StudentAccountTransApplied, this time incorrectly.
		       * If you set a breakpoint in EntityInstanceImpl line 497 (set it before you do the mSAProf.commit) you will see what I mean.
		       * Or you can set the breakpoint in AbstractCommitToDb line 104 (linked.dropEntity()).
		       * 
		       * I can't tell if the problem originates with the .deleteEntity or if the problem is in cleanupOI. I just know on the third time we get to linked.dropEntity
		       * I don't think we should get there (because that third time, when I do a link.parent() the parent is the StudentAccountTransApplied that was
		       * deleted (3590), it's like looping through getAllLinkedInstances is incorrect.
		       * 
		       *  The reason I noticed the incorrect prevHierInstance is because when we call mSAProf.cursor("StudentAccountTransApplied").orderEntities( "TransactionDate D" );
		       *  We fail on assert, line 938 in EntityInstanceImpl - assert assertNoTwin( parent, prevInstance ) : "Internal error: calling code hasn't found a twin";
		       *  
		       *  In the test code here I have tried looping through mSAProf2.getHierEntityList(). This always seems correct.
		       */
		      
		      

              // Creating wXferO and sHost simply to keep from getting errors when looking at mSAProf in the browser.
			  RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
			  RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
			  SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );

			  RESULT = ActivateObjectInstance( sHost, "sHost", ViewToWindow, 0, zSINGLE );
			  SetNameForView( sHost, "sHost", null, zLEVEL_TASK );
			  if ( sHost.cursor("Host").checkExistenceOfEntity().toInt() != 0 )
				  sHost.cursor("Host").createEntity();

		      nRC = ActivateOI_FromFile( mSAProf, "mSAProf", ViewToWindow, "target/test-classes/testdata//IBOE/mSAProf.por", zMULTIPLE );
		      SetNameForView( mSAProf, "mSAProf", null, zLEVEL_TASK );
		      
		      //mSAProf.cursor("StudentAccountTransApplied").orderEntities( "TransactionDate D" );
		      CreateViewFromView( mSAProf2, mSAProf );		      
		      SetNameForView( mSAProf2, "mSAProf2", null, zLEVEL_TASK );
		      
		      RESULT = SetCursorFirstEntity( mSAProf, "StudentAccountTransApplied", "" );
              /*
              ViewToWindow.log().info("before delete");
              for (EntityInstance ei : mSAProf2.getHierEntityList()) {
            	  ViewToWindow.log().info(ei.getEntityDef().getName() + " " + ei.getKeyString()); 
              } */
              
		      mSAProf.cursor("StudentAccountTransApplied").deleteEntity( CursorPosition.NEXT );
		      // EntityInstanceImpl prevHier = firstInstance.getPrevHier();
              /*
              ViewToWindow.log().info("after delete/before commit");
              for (EntityInstance ei : mSAProf2.getHierEntityList()) {
            	  ViewToWindow.log().info(ei.getEntityDef().getName() + " " + ei.getKeyString()); 
              } */
		      mSAProf.commit();
		      /*
              ViewToWindow.log().info("after commit");
              for (EntityInstance ei : mSAProf2.getHierEntityList()) {
                	  ViewToWindow.log().info(ei.getEntityDef().getName() + " " + ei.getKeyString()); 
              } */
		      mSAProf.cursor("StudentAccountTransApplied").orderEntities( "TransactionDate D" );
		      
		      // This was just for testing.
		      iCnt1 = 0;
              for ( EntityInstance ei : mSAProf.cursor( "StudentAccountProfile" ).getChildren( "StudentAccountTransApplied"))
              {
            	  iCnt1++;
              }
		      return( 0 );
		   } 

   }

}
