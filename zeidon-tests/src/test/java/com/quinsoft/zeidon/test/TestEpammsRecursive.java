/**
 *
 */
package com.quinsoft.zeidon.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import static com.quinsoft.zeidon.vml.VmlOperation.zSINGLE;

// Just for temporary testing...
//import com.jacob.com.*;
//import com.jacob.activeX.*;


/**
 * @author DG
 *
 */
public class TestEpammsRecursive
{
	Task         ePammsDKS;
	Task         zeidonSystem;
	View         mFASrc;
	ObjectEngine oe;

   /**
    * Called at the beginning of the test to reset the DB.
    */
   @BeforeClass
   public static void resetDB( ) throws IOException
   {
      // Copy the "base" sqlite file to a test one so we can commit changes as part of the tests.
      File srcFile  = new File("./src/test/resources/testdata/ePamms/sqlite/base.db");
      File destFile = new File("./src/test/resources/testdata/ePamms/sqlite/test.db");
      FileUtils.copyFile( srcFile, destFile );
   }

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
      oe = JavaObjectEngine.getInstance();
      zeidonSystem = oe.getSystemTask();
      zeidonSystem.log().debug( "Starting test" );
      ePammsDKS = oe.createTask( "ePammsDKS" );
	}

   private String getTempDir()
   {
      return System.getProperty("java.io.tmpdir");
   }

   private void copyStorageDisposalStatementsRecursive( View mMasLC, EntityCursor ecMasLC, View mSubLC, EntityCursor ecSubLC )
   {
      EntityInstance eiSubLC = ecSubLC.createEntity();
      eiSubLC.getAttribute("Title").setValue( ecMasLC.getAttribute("Title").getString() );
      eiSubLC.getAttribute("Text").setValue( ecMasLC.getAttribute("Text").getString() );
      eiSubLC.getAttribute("ContainerVolume").setValue( ecMasLC.getAttribute("ContainerVolume").getString() );
      eiSubLC.getAttribute("ContainerType").setValue( ecMasLC.getAttribute("ContainerType").getString() );

      EntityCursor ecMasLCR = mMasLC.cursor( "M_StorageDisposalSubStatement" );
      if ( ecMasLCR.hasAny() ) {
         ecMasLCR.setToSubobject();
         ecMasLCR = mMasLC.cursor( "M_StorageDisposalStatement" );
         EntityCursor ecSubLCR = mSubLC.cursor( "S_StorageDisposalSubStatement" );
         ecSubLCR.setToSubobject();
         ecSubLCR = mSubLC.cursor( "S_StorageDisposalStatement" );
         
         CursorResult cr = ecMasLCR.setFirst();
         while ( cr == CursorResult.SET )
         {
            copyStorageDisposalStatementsRecursive( mMasLC, ecMasLCR, mSubLC, ecSubLCR );
            cr = ecMasLCR.setNext();
         }
      }
    }

   private View
   BuildSimpleStringQualification( View   vSubtask,
                                   String strEntityName,
                                   String strKeyAttributeName,
                                   String strKeyAttributeValue,
                                   String strComparator )
   {
      View view = vSubtask.activateEmptyObjectInstance( "KZDBHQUA", oe.getSystemTask().getApplication() );
      view.cursor( "EntitySpec" ).createEntity( CursorPosition.NEXT );
      view.cursor( "EntitySpec" ).getAttribute( "EntityName" ).setValue( strEntityName );
      view.cursor( "QualAttrib" ).createEntity( CursorPosition.NEXT );
      view.cursor( "QualAttrib" ).getAttribute( "EntityName" ).setValue( strEntityName );
      view.cursor( "QualAttrib" ).getAttribute( "AttributeName" ).setValue( strKeyAttributeName );
      view.cursor( "QualAttrib" ).getAttribute( "Value" ).setValue( strKeyAttributeValue );
      view.cursor( "QualAttrib" ).getAttribute( "Oper" ).setValue( strComparator );
      return( view );
   }

   private View
   BuildSimpleIntegerQualification( View   vSubtask,
                                    String strEntityName,
                                    String strKeyAttributeName,
                                    int    lKeyAttributeValue,
                                    String strComparator )
   {
      View view = vSubtask.activateEmptyObjectInstance( "KZDBHQUA", oe.getSystemTask().getApplication() );
      view.cursor( "EntitySpec" ).createEntity( CursorPosition.NEXT );
      view.cursor( "EntitySpec" ).getAttribute( "EntityName" ).setValue( strEntityName );
      view.cursor( "QualAttrib" ).createEntity( CursorPosition.NEXT );
      view.cursor( "QualAttrib" ).getAttribute( "EntityName" ).setValue( strEntityName );
      view.cursor( "QualAttrib" ).getAttribute( "AttributeName" ).setValue( strKeyAttributeName );
      view.cursor( "QualAttrib" ).getAttribute( "Value" ).setValue( lKeyAttributeValue );
      view.cursor( "QualAttrib" ).getAttribute( "Oper" ).setValue( strComparator );
      return( view );
   }
/*
	@Test
	public void testMoveEntityToNewParent() throws IOException
	{
      View vSubtask = ePammsDKS.activateEmptyObjectInstance( "mMasLC" );
      View qual = BuildSimpleIntegerQualification( vSubtask, "MasterLabelContent", "ID", 17, "=" );

      View mMasLC = vSubtask.activateObjectInstance( "mMasLC", qual, ActivateFlags.SINGLE );
      qual.drop();
      System.out.println("===== Started mMasLC log ... testMoveEntityToNewParent ========");
      mMasLC.logObjectInstance();
      System.out.println("===== Finished mMasLC log ... testMoveEntityToNewParent ========");

      EntityCursor ec = mMasLC.cursor( "M_DirectionsForUseCategory" );
      CursorResult cr = ec.setFirst();
      EntityCursor ec1 = mMasLC.cursor( "M_DirectionsForUseSection" );
      cr = ec1.setFirst( "Name", "General" );
      View mMasLC2 = mMasLC.newView();
      EntityCursor ec2 = mMasLC2.cursor( "M_DirectionsForUseCategory" );
      cr = ec2.setFirst( "Name", "FOGGING" );
      ec2 = mMasLC2.cursor( "M_DirectionsForUseSection" );

      Assert.assertTrue( "FOGGING DirectionsForUseSection NotFound!", cr == CursorResult.SET );
      ec2.moveSubobject( CursorPosition.FIRST, ec1, CursorPosition.NEXT );
      mMasLC.logObjectInstance();
      mMasLC.commit();
   }
*/
   @Test
	public void testMoveEntityToNewParent() throws IOException
	{
      View mLLD = ePammsDKS.activateEmptyObjectInstance( "mLLD" );

      System.out.println("===== Started mLLD log ... testMoveEntityToNewParent ========");

      EntityCursor ec = mLLD.cursor( "LLD" );
      ec.createEntity( CursorPosition.LAST );
      ec.getAttribute( "Tag" ).setValue( "LLD_Tag" );
      ec.getAttribute( "Name" ).setValue( "LLD_Name" );

      ec = mLLD.cursor( "LLD_Page" );
      ec.createEntity( CursorPosition.LAST );
      ec.getAttribute( "Tag" ).setValue( "PageTag" );
      ec.getAttribute( "Name" ).setValue( "PageName" );

      ec = mLLD.cursor( "LLD_Panel" );
      ec.createEntity( CursorPosition.LAST );
      ec.getAttribute( "Tag" ).setValue( "PanelTag" );
      ec.getAttribute( "Name" ).setValue( "PanelName" );

      ec = mLLD.cursor( "LLD_Block" );
      ec.createEntity( CursorPosition.LAST );
      ec.getAttribute( "Tag" ).setValue( "BlockTag" );
      ec.getAttribute( "Name" ).setValue( "BlockName" );

      ec = mLLD.cursor( "LLD_SubBlock" );
      ec.createEntity( CursorPosition.LAST );
      ec.getAttribute( "Tag" ).setValue( "SubBlockTag" );
      ec.getAttribute( "Name" ).setValue( "SubBlockName" );

      ec.setToSubobject();
      ec = mLLD.getCursor( "LLD_Block" );

      ec = mLLD.cursor( "LLD_SpecialSectionAttribute" );
      ec.createEntity( CursorPosition.LAST );
      ec.getAttribute( "Name" ).setValue( "SpecialSectionAttributeName" );

      ec = mLLD.cursor( "LLD_SpecialSectionAttrBlock" );
      ec.createEntity( CursorPosition.LAST );
      ec.getAttribute( "Tag" ).setValue( "SpecialSectionAttrBlockTag" );
      ec.getAttribute( "Name" ).setValue( "SpecialSectionAttrBlockName" );

      ec.resetSubobjectToParent();
      
      View v = mLLD.newView();
      System.out.println("===== Begin mLLD log ... testMoveEntityToNewParent ========");
      v.logObjectInstance();
      EntityCursor ecp = v.getCursor( "LLD_Panel" );  // this is the new parent entity
      CursorResult cr = ecp.setFirst();
      System.out.println("===== Logging ecp entity LLD_Panel ========");
      ecp.logEntity();
      Assert.assertTrue( "Panel not found!", cr == CursorResult.SET );
      ecp = v.getCursor( "LLD_Block" );  // this is the new sibling entity
      ec = mLLD.getCursor( "LLD_SubBlock" );
      ec.setToSubobject();
      ec = mLLD.getCursor( "LLD_Block" );
      System.out.println("===== Logging ec entity LLD_Block ========");
      ec.logEntity();
      ecp.moveSubobject( CursorPosition.LAST, ec, CursorPosition.NONE );
      mLLD.copyCursors( v );  // we want position on the moved entity
      System.out.println( "After Moving SubBlock To target entity: LLD_Block" );
      v.logObjectInstance();
      v.drop();
      ec = mLLD.getCursor( "LLD_SubBlock" );
      cr = ec.setFirst();
      Assert.assertTrue( "SubBlock should not be found!", cr != CursorResult.SET );
      ec = mLLD.getCursor( "LLD_SpecialSectionAttribute" );
      cr = ec.setFirst();
      Assert.assertTrue( "SpecialSectionAttribute should be found!", cr == CursorResult.SET );
   }

   private void displaySPLD( View mSPLDef ) {
      EntityCursor ec;
      View t = mSPLDef.newView();
      t.resetSubobjectTop();
      ec = t.getCursor( "SPLD_LLD" );
      ec.logEntity( true );
      t.drop();
   }

   public void testMoveSubobjectToNewParent1() throws IOException
	{
      View mSPLDef = ePammsDKS.deserializeOi()
                        .fromFile( "target/test-classes/testdata/epammsDKS/mSPLDef.json" )
                        .setLodDef( "mSPLDef" )
                        .setFlags( zSINGLE )
                        .setApplication(ePammsDKS.getApplication() )
                        .activateFirst();

      System.out.println("===== Started mSPLDef log ... testMoveSubobjectToNewParent1 ========");
      displaySPLD( mSPLDef );
      EntityCursor ec = mSPLDef.cursor( "SPLD_LLD" );
      CursorResult cr = ec.setFirst();
      Assert.assertTrue( "SPLD_LLD should be found!", cr == CursorResult.SET );
      System.out.println("===== Logging before move SPLD_LLD ========");
      ec.logEntity( true );

      ec = mSPLDef.cursor( "LLD_Page" );
      cr = ec.setFirst();
      Assert.assertTrue( "LLD_Page should be found!", cr == CursorResult.SET );

      ec = mSPLDef.cursor( "LLD_Panel" );
      cr = ec.setFirst();
      Assert.assertTrue( "LLD_Panel should be found!", cr == CursorResult.SET );

      ec = mSPLDef.cursor( "LLD_Block" );
      cr = ec.setFirst();
      Assert.assertTrue( "LLD_Block should be found!", cr == CursorResult.SET );

      ec = mSPLDef.cursor( "LLD_Block" );
      cr = ec.setNext();
      Assert.assertTrue( "Second LLD_Block should be found!", cr == CursorResult.SET );

      ec = mSPLDef.cursor( "LLD_Block" );
      cr = ec.setNext();
      Assert.assertTrue( "Third LLD_Block should be found!", cr == CursorResult.SET );

      ec = mSPLDef.cursor( "LLD_SubBlock" );
      cr = ec.setFirst();
      Assert.assertTrue( "LLD_SubBlock should be found!", cr == CursorResult.SET );

      View v = mSPLDef.newView();
      v.copyCursors( mSPLDef );
      System.out.println("===== Begin mSPLDef log ... testMoveSubobjectToNewParent1 ========");
   // v.logObjectInstance();

      EntityCursor ecp = v.getCursor( "LLD_Panel" );  // this is the new parent entity
      cr = ecp.setFirst();
      System.out.println("===== Logging ecp entity LLD_Panel ========");
      ecp.logEntity();
      Assert.assertTrue( "Panel not found!", cr == CursorResult.SET );
      ecp = v.getCursor( "LLD_Block" );  // this is the new sibling entity

      ec = mSPLDef.getCursor( "LLD_SubBlock" );
      ec.setToSubobject();
      ec = mSPLDef.getCursor( "LLD_Block" );
      System.out.println("===== Logging ec entity LLD_Block ========");
      ec.logEntity();
      ecp.moveSubobject( CursorPosition.LAST, ec, CursorPosition.NONE );
      mSPLDef.copyCursors( v );  // we want position on the moved entity
      System.out.println( "After Moving SubBlock To target entity: LLD_Block" );
      v.resetSubobjectTop();
      System.out.println("===== Logging after move SPLD_LLD ========");
      ecp = v.getCursor( "SPLD_LLD" );
      ecp.logEntity( true );
      v.drop();
      ec = mSPLDef.getCursor( "LLD_SubBlock" );
      cr = ec.setFirst();
      Assert.assertTrue( "SubBlock should not be found!", cr != CursorResult.SET );
      ec = mSPLDef.getCursor( "LLD_SpecialSectionAttribute" );
      cr = ec.setFirst();
      Assert.assertTrue( "SpecialSectionAttribute should be found!", cr == CursorResult.SET );
   }

   @Test
	public void testMoveSubobjectToNewParent2() throws IOException
	{
      View mSPLDef = ePammsDKS.deserializeOi()
                        .fromFile( "target/test-classes/testdata/epammsDKS/mSPLDef.json" )
                        .setLodDef( "mSPLDef" )
                        .setFlags( zSINGLE )
                        .setApplication(ePammsDKS.getApplication() )
                        .activateFirst();

      System.out.println("===== Started mSPLDef log ... testMoveSubobjectToNewParent2 ========");
   // displaySPLD( mSPLDef );
      EntityCursor ec = mSPLDef.cursor( "SPLD_LLD" );
      CursorResult cr = ec.setFirst();
      Assert.assertTrue( "SPLD_LLD should be found!", cr == CursorResult.SET );
      System.out.println("===== Logging before move SPLD_LLD ========");
      ec.logEntity( true );

      ec = mSPLDef.cursor( "LLD_Page" );
      cr = ec.setFirst();
      Assert.assertTrue( "LLD_Page should be found!", cr == CursorResult.SET );

      ec = mSPLDef.cursor( "LLD_Panel" );
      cr = ec.setFirst();
      Assert.assertTrue( "LLD_Panel should be found!", cr == CursorResult.SET );

      ec = mSPLDef.cursor( "LLD_Block" );
      cr = ec.setFirst();
      Assert.assertTrue( "LLD_Block should be found!", cr == CursorResult.SET );

      ec = mSPLDef.cursor( "LLD_Block" );
      cr = ec.setNext();
      Assert.assertTrue( "Second LLD_Block should be found!", cr == CursorResult.SET );

      ec = mSPLDef.cursor( "LLD_Block" );
      cr = ec.setNext();
      Assert.assertTrue( "Third LLD_Block should be found!", cr == CursorResult.SET );

      ec = mSPLDef.cursor( "LLD_Block" );
      cr = ec.setFirstWithinOi( "ID", 2782 );
      Assert.assertTrue( "LLD_Block (ID: 2782) should be found!", cr == CursorResult.SET );
      System.out.println("===== mSPLDef log entity (ID: 2782) testMoveSubobjectToNewParent2 ========");
      ec.logEntity();

      View v = mSPLDef.newView();
      v.copyCursors( mSPLDef );
      EntityCursor ecp = v.getCursor( "LLD_Block" );  // this is the new parent entity
      cr = ecp.setFirstWithinOi( "ID", 2781 );
      Assert.assertTrue( "LLD_Block (ID: 2781) should be found!", cr == CursorResult.SET );
      System.out.println("===== mSPLDef log entity (ID: 2781) testMoveSubobjectToNewParent2 ========");
      ecp.logEntity();

      System.out.println("===== Begin mSPLDef move log ... testMoveSubobjectToNewParent2 ========");
   // v.logObjectInstance();
      ecp = v.getCursor( "LLD_SubBlock" );
      ecp.setToSubobject();
      ecp = v.getCursor( "LLD_Block" );
      ecp.moveSubobject( CursorPosition.LAST, ec, CursorPosition.NONE );
   // mSPLDef.copyCursors( v );  // we want position on the moved entity
      v.drop();
      mSPLDef.resetSubobjectTop();
      System.out.println( "After Moving SubBlock To target entity: LLD_Block" );
      System.out.println("===== Logging after move SPLD_LLD ========");
      ecp = mSPLDef.getCursor( "SPLD_LLD" );
      ecp.logEntity( true );
      ecp = mSPLDef.getCursor( "LLD_Block" );
      cr = ec.setNext();
      Assert.assertTrue( "Second LLD_Block should be found!", cr == CursorResult.SET );
      ecp = mSPLDef.getCursor( "LLD_SubBlock" );
      cr = ec.setFirst();
      Assert.assertTrue( "SubBlock should now be found!", cr == CursorResult.SET );
      ec = mSPLDef.getCursor( "LLD_SpecialSectionAttribute" );
      cr = ec.setFirst();
      Assert.assertTrue( "SpecialSectionAttribute should be found!", cr == CursorResult.SET );
   }

	@Test
	public void testRecursiveCreate() throws IOException
	{
      View mMasLC = ePammsDKS.activateEmptyObjectInstance( "mMasLC" );
      View mSubLC = ePammsDKS.activateEmptyObjectInstance( "mSubLC" );

      System.out.println("===== Started mMasLC log ... testRecursiveCreate ========");
      EntityCursor ecMasLC = mMasLC.cursor( "MasterLabelContent" );
      EntityInstance eiMasLC = ecMasLC.createEntity();
      eiMasLC.getAttribute("Title").setValue( "MAQUAT® 710-M" );
      ecMasLC = mMasLC.cursor( "M_StorageDisposalSection" );
      eiMasLC = ecMasLC.createEntity();
      eiMasLC.getAttribute("Name").setValue( "CONTAINER HANDLING" );
      eiMasLC.getAttribute("Title").setValue( "CONTAINER HANDLING:" );
      ecMasLC = mMasLC.cursor( "M_StorageDisposalStatement" );
      eiMasLC = ecMasLC.createEntity();
      eiMasLC.getAttribute("Title").setValue( "{{ResidentialUse}}" );
      eiMasLC.getAttribute("Text").setValue( "Non-refillable container. Do not reuse or refill this container. Offer for recycling if available or place in trash. " );
      eiMasLC.getAttribute("ContainerVolume").setValue( "<=5" );
      eiMasLC.getAttribute("ContainerType").setValue( "Sealed" );
   // ecMasLC = mMasLC.cursor( "M_StorageDisposalStatement" );
      eiMasLC = ecMasLC.createEntity();
      eiMasLC.getAttribute("Title").setValue( "{{CommercialUse}}" );
      eiMasLC.getAttribute("ContainerVolume").setValue( "<=5" );
      eiMasLC.getAttribute("ContainerType").setValue( "Non-refillable" );
      ecMasLC = mMasLC.cursor( "M_StorageDisposalSubStatement" );
      eiMasLC = ecMasLC.createEntity();
      eiMasLC.getAttribute("Title").setValue( "{{ResidentialUse}}" );
      eiMasLC.getAttribute("Text").setValue( "Sealed container. Do not reuse or refill this container. Offer for recycling if available or place in trash. " );
      eiMasLC.getAttribute("ContainerVolume").setValue( ">5" );
      eiMasLC.getAttribute("ContainerType").setValue( "Sealed" );
      ecMasLC = mMasLC.cursor( "M_StorageDisposalStatement" );
      mMasLC.logObjectInstance();
      System.out.println("===== Finished mMasLC log ... testRecursiveCreate ========");


      System.out.println("===== Started mSubLC log ... testRecursiveCreate ========");
      EntityCursor ecSubLC = mSubLC.cursor( "SubregLabelContent" );
      EntityInstance eiSubLC = ecSubLC.createEntity();
      eiSubLC.getAttribute("Description").setValue( "KennelSol MAQUAT® 710-M" );
      ecMasLC = mMasLC.cursor( "M_StorageDisposalSection" );
      ecSubLC = mSubLC.cursor( "S_StorageDisposalSection" );
      eiSubLC = ecSubLC.createEntity();
      eiSubLC.getAttribute("Name").setValue( ecMasLC.getAttribute("Name").getString() );
      eiSubLC.getAttribute("Title").setValue( ecMasLC.getAttribute("Title").getString() );

      ecMasLC = mMasLC.cursor( "M_StorageDisposalStatement" );
      ecSubLC = mSubLC.cursor( "S_StorageDisposalStatement" );
      CursorResult cr = ecMasLC.setFirst();
      while ( cr == CursorResult.SET )
      {
         copyStorageDisposalStatementsRecursive( mMasLC, ecMasLC, mSubLC, ecSubLC );
         cr = ecMasLC.setNext();
      }
      
      mSubLC.logObjectInstance();
      System.out.println("===== Finished mSubLC log ... testRecursiveCreate ========");
   }

      
      /*
      eiSubLC = ecSubLC.createEntity();
      eiSubLC.getAttribute("Title").setValue( "{{CommercialUse}}" );
      eiSubLC.getAttribute("ContainerVolume").setValue( "<=5" );
      eiSubLC.getAttribute("ContainerType").setValue( "Non-refillable" );
      ecSubLC = mSubLC.cursor( "M_StorageDisposalSubStatement" );
      eiSubLC = ecSubLC.createEntity();
      eiSubLC.getAttribute("Title").setValue( "{{ResidentialUse}}" );
      eiSubLC.getAttribute("Text").setValue( "Non-refillable container. Do not reuse or refill this container. Offer for recycling if available or place in trash. " );
      eiSubLC.getAttribute("ContainerVolume").setValue( "<=5" );
      eiSubLC.getAttribute("ContainerType").setValue( "Sealed" );
      ecSubLC = mSubLC.cursor( "M_StorageDisposalStatement" );
      
      
      ecMasLC = mMasLC.cursor( "MasterLabelContent" );
      CursorResult crMasLC = ecMasLC.setFirst();
      EntityCursor ecSubLC = mMasLC.cursor( "M_DirectionsForUseSection" );
      crMasLC = ecSubLC.setFirst( "Name", "General" );
      View mMasLC2 = mMasLC.newView();
      EntityCursor ec2 = mMasLC2.cursor( "M_DirectionsForUseCategory" );
      crMasLC = ec2.setFirst( "Name", "FOGGING" );
      ec2 = mMasLC2.cursor( "M_DirectionsForUseSection" );

      Assert.assertTrue( "FOGGING DirectionsForUseSection NotFound!", crMasLC == CursorResult.SET );
      ec2.moveSubobject(CursorPosition.FIRST, ecSubLC, CursorPosition.NEXT );
      mMasLC.logObjectInstance();
      mMasLC.commit();
      */
      
	@Test
   public void ExecuteMoveEntity()
	{
	   View mMasLC = ePammsDKS.activateEmptyObjectInstance( "mMasLC" );
		VmlTester tester = new VmlTester( mMasLC );
		tester.TEST_MoveEntity( mMasLC );
      System.out.println("===== Finished ExecuteMoveEntity ========");
	}

	@Test
   public void ExecuteAcceptSubobjectTemporalEntity()
	{
	   View mMasLC = ePammsDKS.activateEmptyObjectInstance( "mMasLC" );
		VmlTester tester = new VmlTester( mMasLC );
		tester.TEST_AcceptSubobjectTemporalEntity( mMasLC );
      System.out.println("===== Finished ExecuteSubobjectTemporalEntity ========");
	}

	@Test
   public void ExecuteAcceptSubobjectTemporalEntityFromFile()
	{
	   View mMasLC = ePammsDKS.activateEmptyObjectInstance( "mMasLC" );
		VmlTester tester = new VmlTester( mMasLC );
		tester.TEST_AcceptSubobjectTemporalEntityFromFile( mMasLC );
      System.out.println("===== Finished ExecuteSubobjectTemporalEntity ========");
	}

	private class VmlTester extends VmlObjectOperations
	{
		public VmlTester( View view )
		{
			super( view );
		}

      private String
      EliminateBlanksCamelCase( String s )
      {
         if ( s.length() < 2 )
            return s;

         if ( s.charAt( 0 ) == '(' && s.charAt( s.length() - 1 ) == ')' )
            s = s.substring( 1, s.length() - 1 );

         String[] sub = s.split( " " );
         StringBuilder sb = new StringBuilder();
         int k = 0;
         while ( k < sub.length ) {
            if ( sub[ k ].length() > 0 && (sub.length == 1 || (sub[ k ].compareToIgnoreCase( "or" ) != 0 && sub[ k ].compareToIgnoreCase( "and" ) != 0)) ) {
               sb.append( sub[ k ].substring( 0, 1 ).toUpperCase() ).append( sub[ k ].substring( 1 ) );
            }
            k++;
         }
         k = sb.indexOf( "PpmActive" );
         if ( k >= 0 ) {
            sb.replace( k, k + 9, "PPM" );
         }
         return sb.toString();
      }

      private int
      ParseStatementForKeywords( View     mMasLC,
                                 String   statementEntity,
                                 String   keywordEntity,
                                 String   keywordTextEntity,
                                 String   keywordSemaphore )
      {
         StringBuilder sbTarget = new StringBuilder();
         String   szKeyword;
         String   szKeywordValue;
         String   szBraceEnclosedKeyword;
         int      openSemaphorePos = 0;
         int      closeSemaphorePos = 0;

         if ( keywordSemaphore.length() < 2 )
            keywordSemaphore = "{}";

         char openSemaphore = keywordSemaphore.charAt( 0 );
         char closeSemaphore = keywordSemaphore.charAt( 1 );

      // 0.75 oz. of this product per 4 gal. of water {(0.19 oz. per gal. of water)} {(150 ppm active quat)}{(or equivalent use dilution)}
      // 1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123  << lth = 133
      //          1         2         3         4         5         6         7         8         9        10        11        12        13
         String szOrigStatement = mMasLC.cursor( statementEntity ).getAttribute( "Text" ).getValue().toString();
         sbTarget.setLength( 0 );

         // Parse the semaphores out of the string.
         openSemaphorePos = szOrigStatement.indexOf( openSemaphore, openSemaphorePos );
         while ( openSemaphorePos >= 0 ) {
            // Copy static text up to the semaphore to the target.
            sbTarget.append( szOrigStatement.substring( closeSemaphorePos, openSemaphorePos ) );

            openSemaphorePos++;
            closeSemaphorePos = szOrigStatement.indexOf( closeSemaphore, openSemaphorePos );
            if ( closeSemaphorePos >= 0 ) {
               szKeywordValue = szOrigStatement.substring( openSemaphorePos, closeSemaphorePos );
               szKeyword = EliminateBlanksCamelCase( szKeywordValue );
               szBraceEnclosedKeyword = "{{" + szKeyword + "}}";
               sbTarget.append( szBraceEnclosedKeyword );
               mMasLC.cursor( keywordEntity ).createEntity();
               mMasLC.cursor( keywordEntity ).getAttribute( "Name" ).setValue( szKeyword );
               mMasLC.cursor( keywordEntity ).getAttribute( "Type" ).setValue( "A" );  // All optional
               mMasLC.cursor( keywordTextEntity ).createEntity();
               mMasLC.cursor( keywordTextEntity ).getAttribute( "Text" ).setValue( szKeywordValue );
               closeSemaphorePos++;
               openSemaphorePos = szOrigStatement.indexOf( openSemaphore, closeSemaphorePos );
            } else {
               closeSemaphorePos = openSemaphorePos;
               openSemaphorePos = -1;
            }
         }

         sbTarget.append( szOrigStatement.substring( closeSemaphorePos ) ); // append remaining static text in the original source string
         mMasLC.cursor( statementEntity ).getAttribute( "Text" ).setValue( sbTarget.toString() );
         return( 0 );
      }


      public int
      TEST_AcceptSubobjectTemporalEntityFromFile( View mMasLC )
      {
         mMasLC = ePammsDKS.deserializeOi()
                        .fromFile( "target/test-classes/testdata/epammsDKS/mMasLC.json" )
                        .setLodDef( "mMasLC" )
                        .setFlags( zSINGLE )
                        .setApplication(ePammsDKS.getApplication() )
                        .activateFirst();
         CursorResult cr = mMasLC.cursor( "M_DirectionsForUseSection" ).setFirst( "Name", "SANITIZING" );
         Assert.assertTrue( "SANITIZING DirectionsForUseSection NotFound!", cr == CursorResult.SET );
         mMasLC.cursor( "M_DirectionsForUseStatement" ).createTemporalSubobjectVersion();

         mMasLC.cursor( "M_DirectionsForUseSubStatement" ).setToSubobject();
         mMasLC.cursor( "M_DirectionsForUseStatement" ).createTemporalEntity();
         mMasLC.cursor( "M_DirectionsForUseStatement" ).getAttribute("Text").setValue("0.75 oz. of this product per 4 gal. of water {(0.19 oz. per gal. of water)} {(150 ppm active quat)}{(or equivalent use dilution)}");
         mMasLC.cursor( "M_DirectionsForUseStatement" ).acceptSubobject();
         mMasLC.cursor( "M_DirectionsForUseStatement" ).resetSubobjectToParent();
         mMasLC.cursor( "M_DirectionsForUseSubStatement" ).setToSubobject();
         mMasLC.cursor( "M_DirectionsForUseStatement" ).createTemporalSubobjectVersion();
         ParseStatementForKeywords( mMasLC, "M_DirectionsForUseStatement", "M_InsertTextKeywordDU", "M_InsertTextDU", "{}" );
         /*
         mMasLC.cursor( "M_DirectionsForUseStatement" ).getAttribute("Text").setValue("0.75 oz. of this product per 4 gal. of water {{0.19Oz.PerGal.OfWater}} {{150PpmActiveQuat}}{{OrEquivalentUseDilution}}");

         mMasLC.cursor( "M_InsertTextKeywordDU" ).createEntity();
         mMasLC.cursor( "M_InsertTextKeywordDU" ).getAttribute("Name").setValue("0.19Oz.PerGal.OfWater");
         mMasLC.cursor( "M_InsertTextKeywordDU" ).getAttribute("Type").setValue("A");
         mMasLC.cursor( "M_InsertTextDU" ).createEntity();
         mMasLC.cursor( "M_InsertTextDU" ).getAttribute("Text").setValue("(0.19 oz. per gal. of water)");

         mMasLC.cursor( "M_InsertTextKeywordDU" ).createEntity();
         mMasLC.cursor( "M_InsertTextKeywordDU" ).getAttribute("Name").setValue("150PpmActiveQuat");
         mMasLC.cursor( "M_InsertTextKeywordDU" ).getAttribute("Type").setValue("A");
         mMasLC.cursor( "M_InsertTextDU" ).createEntity();
         mMasLC.cursor( "M_InsertTextDU" ).getAttribute("Text").setValue("(150 ppm active quat)");

         mMasLC.cursor( "M_InsertTextKeywordDU" ).createEntity();
         mMasLC.cursor( "M_InsertTextKeywordDU" ).getAttribute("Name").setValue("OrEquivalentUseDilution");
         mMasLC.cursor( "M_InsertTextKeywordDU" ).getAttribute("Type").setValue("A");
         mMasLC.cursor( "M_InsertTextDU" ).createEntity();
         mMasLC.cursor( "M_InsertTextDU" ).getAttribute("Text").setValue("(or equivalent use dilution)");
         */

         mMasLC.cursor( "M_DirectionsForUseStatement" ).acceptSubobject();
         mMasLC.cursor( "M_DirectionsForUseStatement" ).resetSubobjectToParent();
         return 0;
      }

      public int
      TEST_MoveEntity( View mMasLC )
      {
         mMasLC = ePammsDKS.deserializeOi()
                        .fromFile( "target/test-classes/testdata/epammsDKS/mMasLC.json" )
                        .setLodDef( "mMasLC" )
                        .setFlags( zSINGLE )
                        .setApplication(ePammsDKS.getApplication() )
                        .activateFirst();
         EntityCursor ec = mMasLC.cursor( "M_DirectionsForUseCategory" );
         CursorResult cr = ec.setFirst();
         EntityCursor ec1 = mMasLC.cursor( "M_DirectionsForUseSection" );
         cr = ec1.setFirst( "Name", "General" );
         View mMasLC2 = mMasLC.newView();
         EntityCursor ec2 = mMasLC2.cursor( "M_DirectionsForUseCategory" );
         cr = ec2.setFirst( "Name", "FOGGING" );
         ec2 = mMasLC2.cursor( "M_DirectionsForUseSection" );

         Assert.assertTrue( "FOGGING DirectionsForUseSection NotFound!", cr == CursorResult.SET );
         ec2.moveSubobject( CursorPosition.FIRST, ec1, CursorPosition.NEXT );
         mMasLC.logObjectInstance();
         return 0;
      }

      public int
		TEST_AcceptSubobjectTemporalEntity( View mMasLC )
		{
         mMasLC.cursor( "MasterLabelContent" ).createEntity();
         mMasLC.cursor( "MasterLabelContent" ).getAttribute("Version").setValue("1");
         mMasLC.cursor( "MasterLabelContent" ).getAttribute("RevisionDate").setValue("20040404");
         mMasLC.cursor( "MasterLabelContent" ).createTemporalSubobjectVersion();
         mMasLC.cursor( "MasterSubLabelContent" ).setToSubobject();
         mMasLC.cursor( "MasterLabelContent" ).createTemporalEntity();
         mMasLC.cursor( "MasterLabelContent" ).getAttribute("Version").setValue("2");
         mMasLC.cursor( "MasterLabelContent" ).getAttribute("RevisionDate").setValue("20050505");
         mMasLC.cursor( "MasterLabelContent" ).acceptSubobject();
         mMasLC.cursor( "MasterLabelContent" ).resetSubobjectToParent();
         mMasLC.cursor( "MasterSubLabelContent" ).setToSubobject();
         mMasLC.cursor( "MasterLabelContent" ).createTemporalEntity();
         mMasLC.cursor( "MasterLabelContent" ).cancelSubobject();
         mMasLC.cursor( "MasterLabelContent" ).resetSubobjectToParent();
         mMasLC.cursor( "MasterLabelContent" ).acceptSubobject();
         mMasLC.cursor( "M_DirectionsForUseCategory" ).createEntity();
         mMasLC.cursor( "M_DirectionsForUseSection" ).createEntity();
         mMasLC.cursor( "M_DirectionsForUseStatement" ).createEntity();
         mMasLC.cursor( "M_DirectionsForUseSubStatement" ).setToSubobject();
         mMasLC.cursor( "M_DirectionsForUseStatement" ).createTemporalEntity();
         mMasLC.cursor( "M_DirectionsForUseStatement" ).getAttribute("Text").setValue("0.75 oz. of this product per 4 gal. of water {(0.19 oz. per gal. of water)} {(150 ppm active quat)}{(or equivalent use dilution)}");
         mMasLC.cursor( "M_DirectionsForUseStatement" ).acceptSubobject();
         mMasLC.cursor( "M_DirectionsForUseStatement" ).resetSubobjectToParent();
         mMasLC.cursor( "M_DirectionsForUseSubStatement" ).setToSubobject();
         mMasLC.cursor( "M_DirectionsForUseStatement" ).createTemporalSubobjectVersion();
         mMasLC.cursor( "M_DirectionsForUseStatement" ).getAttribute("Text").setValue("0.75 oz. of this product per 4 gal. of water {{0.19Oz.PerGal.OfWater}} {{150PpmActiveQuat}}{{OrEquivalentUseDilution}}");

         mMasLC.cursor( "M_InsertTextKeywordDU" ).createEntity();
         mMasLC.cursor( "M_InsertTextKeywordDU" ).getAttribute("Name").setValue("0.19Oz.PerGal.OfWater");
         mMasLC.cursor( "M_InsertTextKeywordDU" ).getAttribute("Type").setValue("A");
         mMasLC.cursor( "M_InsertTextDU" ).createEntity();
         mMasLC.cursor( "M_InsertTextDU" ).getAttribute("Text").setValue("(0.19 oz. per gal. of water)");

         mMasLC.cursor( "M_InsertTextKeywordDU" ).createEntity();
         mMasLC.cursor( "M_InsertTextKeywordDU" ).getAttribute("Name").setValue("150PpmActiveQuat");
         mMasLC.cursor( "M_InsertTextKeywordDU" ).getAttribute("Type").setValue("A");
         mMasLC.cursor( "M_InsertTextDU" ).createEntity();
         mMasLC.cursor( "M_InsertTextDU" ).getAttribute("Text").setValue("(150 ppm active quat)");

         mMasLC.cursor( "M_InsertTextKeywordDU" ).createEntity();
         mMasLC.cursor( "M_InsertTextKeywordDU" ).getAttribute("Name").setValue("OrEquivalentUseDilution");
         mMasLC.cursor( "M_InsertTextKeywordDU" ).getAttribute("Type").setValue("A");
         mMasLC.cursor( "M_InsertTextDU" ).createEntity();
         mMasLC.cursor( "M_InsertTextDU" ).getAttribute("Text").setValue("(or equivalent use dilution)");

         mMasLC.cursor( "M_DirectionsForUseStatement" ).acceptSubobject();
         mMasLC.cursor( "M_DirectionsForUseStatement" ).resetSubobjectToParent();
         return 0;
		}

   }

}
