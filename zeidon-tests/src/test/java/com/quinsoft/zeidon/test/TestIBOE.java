/**
 *
 */
package com.quinsoft.zeidon.test;

import org.junit.Before;
import org.junit.Test;
import org.apache.commons.lang3.mutable.MutableInt;
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

	@Test
	public void testCancelSubobject()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mStuEnrM" );
		IBOEVmlTester tester = new IBOEVmlTester( testview );
		tester.testCancelSubobject( testview );
        System.out.println("===== Finished testCancelSubobject ========");
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

		   public int
		   TEST_TemporalSaveIssue( View     ViewToWindow )
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

		      // This test doesn't work here because our sqlite database has no data in it. This same test is in TestZencas. I've only kept it here because
		      // we get a different problem at IBOE than in TestZencas. Has the same idea but maybe someday we'd want to look at it here?? Probably not...


		      //:// Code to test commit of new TransApplied entries when Billing Period has been added.
		      //:// We will use any mSAProf example with SA Transactions as a base to create the new entry.

		      //:// Activate list of College Terms for adding BillingPeriod and position on last, for which the mSAprof is probably missing..
		      //:ACTIVATE mTermLST Multiple
		      //:     RESTRICTING mTermLST.Class TO mTermLST.Class.ID = 0
		      RESULT = ActivateObjectInstance( mTermLST, "lTermLST", ViewToWindow, 0, zMULTIPLE );
		      DropView( vTempViewVar_0 );
		      //:NAME VIEW mTermLST "mTermLST"
		      SetNameForView( mTermLST, "mTermLST", null, zLEVEL_TASK );
		      //:OrderEntityForView( mTermLST, "CollegeTerm", "YearSemester D" )
		      OrderEntityForView( mTermLST, "CollegeTerm", "YearSemester D" );
		      //:SET CURSOR FIRST mTermLST.CollegeTerm
		      RESULT = SetCursorFirstEntity( mTermLST, "CollegeTerm", "" );

		      //:// Activate any mSAProf as our base.
		      //:ACTIVATE mSAProfS WHERE mSAProfS.StudentAccountTransApplied EXISTS
		      //:      RESTRICTING mSAProfS.BillingPeriod TO mSAProfS.PeriodCollegeTerm.ID = mTermLST.CollegeTerm.ID
		      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
		          GetIntegerFromAttribute( mi_lTempInteger_0, mTermLST, "CollegeTerm", "ID" );
		      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
		      o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_1, lTempInteger_0 );
		      RESULT = ActivateObjectInstance( mSAProfS, "mSAProf", ViewToWindow, vTempViewVar_1, zSINGLE );
		      DropView( vTempViewVar_1 );
		      //:NAME VIEW mSAProfS "mSAProfS"
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
		               //:DELETE ENTITY mSAProfS.StudentAccountTransApplied NONE
		               RESULT = DeleteEntity( mSAProfS, "StudentAccountTransApplied", zREPOS_NONE );
		            }

		            RESULT = SetCursorNextEntity( mSAProfS, "StudentAccountTransApplied", "" );
		            //:END
		         }

		         //:END
		         //:COMMIT mSAProfS
		         RESULT = CommitObjectInstance( mSAProfS );
		         //:DELETE ENTITY mSAProfS.BillingPeriod
		         RESULT = DeleteEntity( mSAProfS, "BillingPeriod", zPOS_NEXT );
		         //:COMMIT mSAProfS
		         RESULT = CommitObjectInstance( mSAProfS );
		         //:SAProfID = mSAProfS.StudentAccountProfile.ID
		         {MutableInt mi_SAProfID = new MutableInt( SAProfID );
		                GetIntegerFromAttribute( mi_SAProfID, mSAProfS, "StudentAccountProfile", "ID" );
		         SAProfID = mi_SAProfID.intValue( );}
		         //:DropObjectInstance( mSAProfS )
		         DropObjectInstance( mSAProfS );

		         //:// Get a fresh start.
		         //:ACTIVATE mSAProfS WHERE mSAProfS.StudentAccountTransApplied EXISTS
		         o_fnLocalBuildQual_4( ViewToWindow, vTempViewVar_2 );
		         RESULT = ActivateObjectInstance( mSAProfS, "mSAProf", ViewToWindow, vTempViewVar_2, zSINGLE );
		         DropView( vTempViewVar_2 );
		         //:NAME VIEW mSAProfS "mSAProfS"
		         SetNameForView( mSAProfS, "mSAProfS", null, zLEVEL_TASK );
		      }

		      //:END

		      //:// Position on a StudentAccountTransApplied for the source of the create transaction.
		      //:CreateViewFromView( mSAProf, mSAProfS )
		      CreateViewFromView( mSAProf, mSAProfS );
		      //:NAME VIEW mSAProf "mSAProfCopy"
		      SetNameForView( mSAProf, "mSAProfCopy", null, zLEVEL_TASK );
		      //:SET CURSOR FIRST mSAProf.StudentAccountTransApplied
		      RESULT = SetCursorFirstEntity( mSAProf, "StudentAccountTransApplied", "" );
		      //:SET CURSOR LAST  mSAProfS.StudentAccountTransApplied
		      RESULT = SetCursorLastEntity( mSAProfS, "StudentAccountTransApplied", "" );

		      //:// Add a BillingPeriod for CollegeTerm and add two StudentAccountTransApplied entries.
		      //:CREATE ENTITY mSAProfS.BillingPeriod
		      RESULT = CreateEntity( mSAProfS, "BillingPeriod", zPOS_AFTER );
		      //:INCLUDE mSAProfS.PeriodCollegeTerm FROM mTermLST.CollegeTerm
		      RESULT = IncludeSubobjectFromSubobject( mSAProfS, "PeriodCollegeTerm", mTermLST, "CollegeTerm", zPOS_AFTER );
		      //:mSAProfS.BillingPeriod.PeriodDesignator = mTermLST.CollegeTerm.YearSemester
		      SetAttributeFromAttribute( mSAProfS, "BillingPeriod", "PeriodDesignator", mTermLST, "CollegeTerm", "YearSemester" );
		      //:mSAProfS.BillingPeriod.BeginDate        = mSAProf.PeriodCollegeTerm.CourseStartDate
		      SetAttributeFromAttribute( mSAProfS, "BillingPeriod", "BeginDate", mSAProf, "PeriodCollegeTerm", "CourseStartDate" );
		      //:mSAProfS.BillingPeriod.EndDate          = mSAProf.PeriodCollegeTerm.CourseEndDate
		      SetAttributeFromAttribute( mSAProfS, "BillingPeriod", "EndDate", mSAProf, "PeriodCollegeTerm", "CourseEndDate" );

		      //:CreateTemporalEntity( mSAProfS, "StudentAccountTransApplied", zPOS_AFTER )
		      CreateTemporalEntity( mSAProfS, "StudentAccountTransApplied", zPOS_AFTER );
		      //:INCLUDE mSAProfS.SATransactionCode    FROM mSAProf.SATransactionCode
		      RESULT = IncludeSubobjectFromSubobject( mSAProfS, "SATransactionCode", mSAProf, "SATransactionCode", zPOS_AFTER );
		      //:INCLUDE mSAProfS.DebitGLChartEntry    FROM mSAProf.DebitGLChartEntry
		      RESULT = IncludeSubobjectFromSubobject( mSAProfS, "DebitGLChartEntry", mSAProf, "DebitGLChartEntry", zPOS_AFTER );
		      //:INCLUDE mSAProfS.CreditGLChartEntry   FROM mSAProf.CreditGLChartEntry
		      RESULT = IncludeSubobjectFromSubobject( mSAProfS, "CreditGLChartEntry", mSAProf, "CreditGLChartEntry", zPOS_AFTER );
		      //:INCLUDE mSAProfS.AppliedBillingPeriod FROM mSAProfS.BillingPeriod
		      RESULT = IncludeSubobjectFromSubobject( mSAProfS, "AppliedBillingPeriod", mSAProfS, "BillingPeriod", zPOS_AFTER );
		      //:mSAProfS.StudentAccountTransApplied.Description = mSAProf.StudentAccountTransApplied.Description
		      SetAttributeFromAttribute( mSAProfS, "StudentAccountTransApplied", "Description", mSAProf, "StudentAccountTransApplied", "Description" );
		      //:mSAProfS.StudentAccountTransApplied.Instrument  = mSAProf.StudentAccountTransApplied.Instrument
		      SetAttributeFromAttribute( mSAProfS, "StudentAccountTransApplied", "Instrument", mSAProf, "StudentAccountTransApplied", "Instrument" );
		      //:mSAProfS.StudentAccountTransApplied.DateEntered = mSAProf.StudentAccountTransApplied.DateEntered
		      SetAttributeFromAttribute( mSAProfS, "StudentAccountTransApplied", "DateEntered", mSAProf, "StudentAccountTransApplied", "DateEntered" );
		      //:mSAProfS.StudentAccountTransApplied.Amount      = mSAProf.StudentAccountTransApplied.Amount
		      SetAttributeFromAttribute( mSAProfS, "StudentAccountTransApplied", "Amount", mSAProf, "StudentAccountTransApplied", "Amount" );
		      //:mSAProfS.StudentAccountTransApplied.Source      = mSAProf.StudentAccountTransApplied.Source
		      SetAttributeFromAttribute( mSAProfS, "StudentAccountTransApplied", "Source", mSAProf, "StudentAccountTransApplied", "Source" );
		      //:AcceptSubobject( mSAProfS, "StudentAccountTransApplied" )
		      AcceptSubobject( mSAProfS, "StudentAccountTransApplied" );

		      //:DropView( mSAProf )
		      DropView( mSAProf );

		      //:COMMIT mSAProfS
		      RESULT = CommitObjectInstance( mSAProfS );

		      //:ACTIVATE mSAProf WHERE mSAProf.StudentAccountProfile.ID = mSAProfS.StudentAccountProfile.ID
		      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
		          GetIntegerFromAttribute( mi_lTempInteger_2, mSAProfS, "StudentAccountProfile", "ID" );
		      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
		      o_fnLocalBuildQual_5( ViewToWindow, vTempViewVar_3, lTempInteger_2 );
		      RESULT = ActivateObjectInstance( mSAProf, "mSAProf", ViewToWindow, vTempViewVar_3, zSINGLE );
		      DropView( vTempViewVar_3 );
		      //:NAME VIEW mSAProf "mSAProfVerify"
		      SetNameForView( mSAProf, "mSAProfVerify", null, zLEVEL_TASK );
		      return( 0 );
		   // END
		   }


		   public int
		   testCancelSubobject( View     ViewToWindow )
		   {
		      zVIEW    mStuEnrM = new zVIEW( );
		      int      nRC = 0;

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


		      nRC = ActivateOI_FromFile( mStuEnrM, "mStuEnrM", ViewToWindow, "target/test-classes/testdata/IBOE/mStuEnrM.por", zMULTIPLE );
		      SetNameForView( mStuEnrM, "mStuEnrM", null, zLEVEL_TASK );
		      
	          CreateTemporalEntity( mStuEnrM, "EnrollmentModification", zPOS_AFTER );
	          CancelSubobject( mStuEnrM, "EnrollmentModification" );
	          
		      return( 0 );
		   }

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
			o_fnLocalBuildQual_3( View     vSubtask,
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
   }

}
