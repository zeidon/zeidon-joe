/**
 *
 */
package com.quinsoft.zeidon.test;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
public class TestEpamms2
{
	Task         ePamms;
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
      ePamms = oe.createTask( "ePammsDon" );

      zeidonSystem = oe.getSystemTask();
	}

	@Test
   public void ExecuteSubobjectTemporalEntity()
	{
	   View mMasLC = ePamms.activateEmptyObjectInstance( "mMasLC" );
		VmlTester tester = new VmlTester( mMasLC );
		tester.TEST_SubobjectTemporalEntity( mMasLC );
      System.out.println("===== Finished ExecuteSubobjectTemporalEntity ========");
	}

	@Test
	public void ExecuteJOE_TestSubobjectCreateView()
	{
	   View         testview;
		testview = ePamms.activateEmptyObjectInstance( "mSPLDef" );
		VmlTester tester = new VmlTester( testview );
		tester.ExecuteJOE_TestSubobjectCreateView( testview );
      System.out.println("===== Finished ExecuteJOE_TestSubobjectCreateView ========");
	}

	@Test
	public void ExecuteJOEIncludeError()
	{
	   View         testview;
		testview = ePamms.activateEmptyObjectInstance( "mSPLDef" );
		VmlTester tester = new VmlTester( testview );
		tester.TEST_IncludeError( testview );
      System.out.println("===== Finished ExecuteJOEIncludeError ========");
	}

    //@Test
	public void ExecuteJOEIncludeError2()
	{
	   View         testview;
		testview = ePamms.activateEmptyObjectInstance( "mSPLDef" );
		VmlTester tester = new VmlTester( testview );
		tester.TEST_IncludeError2( testview );
      System.out.println("===== Finished ExecuteJOEIncludeError2 ========");
	}

	@Test
	public void ExecuteJOEIncludeError3()
	{
	   View         testview;
		testview = ePamms.activateEmptyObjectInstance( "mSPLDef" );
		VmlTester tester = new VmlTester( testview );
		tester.TEST_IncludeError3( testview );
      System.out.println("===== Finished ExecuteJOEIncludeError3 ========");
	}

	@Test
	public void TEST_TwoViewsSetAttributeError()
	{
	   View         testview;
		testview = ePamms.activateEmptyObjectInstance( "mSPLDef" );
		VmlTester tester = new VmlTester( testview );
		tester.TEST_TwoViewsSetAttributeError( testview );
       System.out.println("===== Finished TEST_TwoViewsSetAttributeError ========");
	}

	// Test fails because mspldef1.por has unknown attributes.
//	@Test
	public void TEST_ActivateOI_FromOIRecursive()
	{
	   View         testview;
		testview = ePamms.activateEmptyObjectInstance( "mSPLDef" );
		VmlTester tester = new VmlTester( testview );
		tester.TEST_ActivateOI_FromOIRecursive( testview );
       System.out.println("===== Finished TEST_ActivateOI_FromOIRecursive ========");
	}

	private class VmlTester extends VmlObjectOperations
	{
		public VmlTester( View view )
		{
			super( view );
		}

      public int 
		TEST_SubobjectTemporalEntity( View mMasLC )
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
         mMasLC.cursor( "M_StorageDisposalSection" ).createEntity();
         mMasLC.cursor( "M_StorageDisposalStatement" ).createEntity();
         mMasLC.cursor( "M_StorageDisposalSubStatement" ).setToSubobject();
         mMasLC.cursor( "M_StorageDisposalStatement" ).createTemporalEntity();
         mMasLC.cursor( "M_StorageDisposalStatement" ).cancelSubobject();         
		   return( 0 );
		}

		public int
		ExecuteJOE_TestSubobjectCreateView( View     ViewToWindow )
		{
		   zVIEW    mSPLDef = new zVIEW( );
		   zVIEW    mSPLDef2 = new zVIEW( );
		   int      RESULT = 0;
		   int      lTempInteger_0 = 0;


		   //:// Execute Tests to Check for JOE Bugs.

		   // Recursive Subobject Test.
		   // After creating the subobject entity, we get an error when calling the a newView() from the subobject view.
		   // The error occurs in EntityCursorImpl line 159 and I have to say I am confused as to why in EntityCursorImpl the
		   // retrieved parentInstance isn't the same in both the cases of newView() in this code.

		   //:// Create subobject with one level of recursive subobject.
		   //:ACTIVATE mSPLDef EMPTY
		   RESULT = ActivateEmptyObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, zSINGLE );
		   //:NAME VIEW mSPLDef "mSPLDef"
		   SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );
		   //:CREATE ENTITY mSPLDef.SubregPhysicalLabelDef
		   RESULT = CreateEntity( mSPLDef, "SubregPhysicalLabelDef", zPOS_AFTER );
		   //:CREATE ENTITY mSPLDef.SPLD_LLD
		   RESULT = CreateEntity( mSPLDef, "SPLD_LLD", zPOS_AFTER );
		   //:mSPLDef.SPLD_LLD.Name = "Test"
		   SetAttributeFromString( mSPLDef, "SPLD_LLD", "Name", "Test" );
		   //:CREATE ENTITY mSPLDef.LLD_Page
		   RESULT = CreateEntity( mSPLDef, "LLD_Page", zPOS_AFTER );
		   //:mSPLDef.LLD_Page.Width = 10
		   SetAttributeFromInteger( mSPLDef, "LLD_Page", "Width", 10 );
		   //:CREATE ENTITY mSPLDef.LLD_Panel
		   RESULT = CreateEntity( mSPLDef, "LLD_Panel", zPOS_AFTER );
		   //:mSPLDef.LLD_Panel.Width = 11
		   SetAttributeFromInteger( mSPLDef, "LLD_Panel", "Width", 11 );

		   //:CREATE ENTITY mSPLDef.LLD_Block
		   RESULT = CreateEntity( mSPLDef, "LLD_Block", zPOS_AFTER );
		   //:mSPLDef.LLD_Block.Name = "Block Level 1"
		   SetAttributeFromString( mSPLDef, "LLD_Block", "Name", "Block Level 1" );
		   //:CREATE ENTITY mSPLDef.LLD_SpecialSectionAttribute
		   RESULT = CreateEntity( mSPLDef, "LLD_SpecialSectionAttribute", zPOS_AFTER );
		   //:mSPLDef.LLD_SpecialSectionAttribute.Name = "Spec Attribute 1"
		   SetAttributeFromString( mSPLDef, "LLD_SpecialSectionAttribute", "Name", "Spec Attribute 1" );

		   //:SetViewToSubobject( mSPLDef, "LLD_SubBlock" )
		   SetViewToSubobject( mSPLDef, "LLD_SubBlock" );

		   //zVIEW vGrid2 = new zVIEW( );
		   //CreateViewFromView( vGrid2, mSPLDef );
		   View vGrid2;
		   vGrid2 = mSPLDef.newView( );

		   DropView( vGrid2 );

		   RESULT = CreateEntity( mSPLDef, "LLD_Block", zPOS_AFTER );
		   //:mSPLDef.LLD_Block.Name = "Block Level 2"
		   SetAttributeFromString( mSPLDef, "LLD_Block", "Name", "Block Level 2" );
		   //:CREATE ENTITY mSPLDef.LLD_SpecialSectionAttribute
		   RESULT = CreateEntity( mSPLDef, "LLD_SpecialSectionAttribute", zPOS_AFTER );
		   //:mSPLDef.LLD_SpecialSectionAttribute.Name = "Spec Attribute 2"
		   SetAttributeFromString( mSPLDef, "LLD_SpecialSectionAttribute", "Name", "Spec Attribute 2" );

		   /*
		   ResetViewFromSubobject( mSPLDef );
		   SetViewToSubobject( mSPLDef, "LLD_SubBlock" );
		   ResetViewFromSubobject( mSPLDef );
		   SetViewToSubobject( mSPLDef, "LLD_SubBlock" );
		   */
		   vGrid2 = mSPLDef.newView( );
		   //CreateViewFromView( mSPLDef2, mSPLDef );
		   String str = GetStringFromAttribute( vGrid2, "LLD_SpecialSectionAttribute", "Name" );
		   DropView( vGrid2 );

		   return( 0 );
		}

		public int
		TEST_IncludeError( View     ViewToWindow )
		{
		   zVIEW    mMasLC = new zVIEW( );
		   //:VIEW mMasLC2 BASED ON LOD mMasLC
		   zVIEW    mMasLC2 = new zVIEW( );
		   //:INTEGER Count
		   int      Count = 0;
		   int      RESULT = 0;


		   ActivateOI_FromFile( mMasLC, "mMasLC", ViewToWindow, "target/test-classes/testdata/ePammsDon/mMasLCinclude.por", zSINGLE );
		   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );

		   // Here's the issue:
		   // Create mMasLC2 from mMasLC.
		   // Exclude/Include entities in mMasLC2.
		   // The included entities look good in mMasLC.
		   // Drop mMasLC2, Create mMasLC2 from mMasLC.
		   // Exclude/Include entities in mMasLC2.
		   // The included entities do not exist in mMasLC.

		   //:// EXECUTION 1
		   //:// First remove existing entries.
		   CreateViewFromView( mMasLC2, mMasLC );
		   //:FOR EACH mMasLC2.M_UsageNonGroupUsage
		   RESULT = SetCursorFirstEntity( mMasLC2, "M_UsageNonGroupUsage", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      RESULT = ExcludeEntity( mMasLC2, "M_UsageNonGroupUsage", zREPOS_NONE );
		      RESULT = SetCursorNextEntity( mMasLC2, "M_UsageNonGroupUsage", "" );
		   }

		   // Include M_UsageNonGroupUsage from M_Usage.
		   //:FOR EACH mMasLC2.M_Usage
		   RESULT = SetCursorFirstEntity( mMasLC2, "M_Usage", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      RESULT = IncludeSubobjectFromSubobject( mMasLC2, "M_UsageNonGroupUsage", mMasLC2, "M_Usage", zPOS_AFTER );
		      RESULT = SetCursorNextEntity( mMasLC2, "M_Usage", "" );
		   }

		   DropView( mMasLC2 );
		   Count = 0;

		   //:FOR EACH mMasLC.M_UsageNonGroupUsage
		   RESULT = SetCursorFirstEntity( mMasLC, "M_UsageNonGroupUsage", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      Count = Count + 1;
		      RESULT = SetCursorNextEntity( mMasLC, "M_UsageNonGroupUsage", "" );
		   }

		   TraceLineI( "***** Count 1: ", Count );

		   // EXECUTION 2
		   // Create mMasLC2
		   // First remove existing entries.
		   CreateViewFromView( mMasLC2, mMasLC );
		   //:FOR EACH mMasLC2.M_UsageNonGroupUsage
		   RESULT = SetCursorFirstEntity( mMasLC2, "M_UsageNonGroupUsage", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      RESULT = ExcludeEntity( mMasLC2, "M_UsageNonGroupUsage", zREPOS_NONE );
		      RESULT = SetCursorNextEntity( mMasLC2, "M_UsageNonGroupUsage", "" );
		   }

		   // Include M_UsageNonGroupUsage from M_Usage.
		   //:FOR EACH mMasLC2.M_Usage
		   RESULT = SetCursorFirstEntity( mMasLC2, "M_Usage", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      RESULT = IncludeSubobjectFromSubobject( mMasLC2, "M_UsageNonGroupUsage", mMasLC2, "M_Usage", zPOS_AFTER );
		      RESULT = SetCursorNextEntity( mMasLC2, "M_Usage", "" );
		   }

		   DropView( mMasLC2 );
		   Count = 0;
		   //:FOR EACH mMasLC.M_UsageNonGroupUsage
		   RESULT = SetCursorFirstEntity( mMasLC, "M_UsageNonGroupUsage", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      //:Count = Count + 1
		      Count = Count + 1;
		      RESULT = SetCursorNextEntity( mMasLC, "M_UsageNonGroupUsage", "" );
		   }

		   TraceLineI( "***** Count 2: ", Count );
           Assert.assertTrue( "After includes, M_UsageNonGroupUsage should have 85 entitites, it does not!", Count == 85 );

		   return( 0 );
		}


		public int
		TEST_IncludeError3( View     ViewToWindow )
		{
		   zVIEW    mMasLC = new zVIEW( );
		   int      Count = 0;
		   int      RESULT = 0;


		   ActivateOI_FromFile( mMasLC, "mMasLC", ViewToWindow, "target/test-classes/testdata/ePammsDon/mMasLCinclude.por", zSINGLE );
		   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );

		   // Exclude/Include entities and then Exclude/Include again.
		   // This test has been working, it's TEST_IncludeError (using a created view) that
		   // doesn't but I wanted to put this in here as a reference.

		   // EXECUTION 1
		   // First remove existing entries.
		   //:FOR EACH mMasLC.M_UsageNonGroupUsage
		   RESULT = SetCursorFirstEntity( mMasLC, "M_UsageNonGroupUsage", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      RESULT = ExcludeEntity( mMasLC, "M_UsageNonGroupUsage", zREPOS_NONE );
		      RESULT = SetCursorNextEntity( mMasLC, "M_UsageNonGroupUsage", "" );
		   }

		   //:// Add any Usage entry that's not tied to a Group.
		   //:FOR EACH mMasLC.M_Usage
		   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      RESULT = IncludeSubobjectFromSubobject( mMasLC, "M_UsageNonGroupUsage", mMasLC, "M_Usage", zPOS_AFTER );
		      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
		   }

		   Count = 0;

		   //:FOR EACH mMasLC.M_UsageNonGroupUsage
		   RESULT = SetCursorFirstEntity( mMasLC, "M_UsageNonGroupUsage", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      Count = Count + 1;
		      RESULT = SetCursorNextEntity( mMasLC, "M_UsageNonGroupUsage", "" );
		   }

		   TraceLineI( "***** Count 1: ", Count );

		   //:// EXECUTION 2
		   //:// First remove existing entries.
		   //:FOR EACH mMasLC.M_UsageNonGroupUsage
		   RESULT = SetCursorFirstEntity( mMasLC, "M_UsageNonGroupUsage", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      RESULT = ExcludeEntity( mMasLC, "M_UsageNonGroupUsage", zREPOS_NONE );
		      RESULT = SetCursorNextEntity( mMasLC, "M_UsageNonGroupUsage", "" );
		   }

		   //:// Add any Usage entry that's not tied to a Group.
		   //:FOR EACH mMasLC.M_Usage
		   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      RESULT = IncludeSubobjectFromSubobject( mMasLC, "M_UsageNonGroupUsage", mMasLC, "M_Usage", zPOS_AFTER );
		      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
		   }

		   Count = 0;
		   //:FOR EACH mMasLC.M_UsageNonGroupUsage
		   RESULT = SetCursorFirstEntity( mMasLC, "M_UsageNonGroupUsage", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      //:Count = Count + 1
		      Count = Count + 1;
		      RESULT = SetCursorNextEntity( mMasLC, "M_UsageNonGroupUsage", "" );
		   }

		   TraceLineI( "***** Count 2: ", Count );
           Assert.assertTrue( "After includes, M_UsageNonGroupUsage should have 85 entitites, it does not!", Count == 85 );
		   return( 0 );
		}

		public int
		TEST_IncludeError2( View     ViewToWindow )
		{
		   zVIEW    mMasLC = new zVIEW( );
		   int      RESULT = 0;


		   // DELETE ENTITY WITH INCLUDED INSTANCE IN A DERIVED PATH.
		   // Error "Entity is not flagged for delete".

		   ActivateOI_FromFile( mMasLC, "mMasLC", ViewToWindow, "target/test-classes/testdata/ePammsDon/mMasLCinclude.por", zSINGLE );
		   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );

		   //:FOR EACH mMasLC.M_UsageNonGroupUsage
		   RESULT = SetCursorFirstEntity( mMasLC, "M_UsageNonGroupUsage", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      RESULT = ExcludeEntity( mMasLC, "M_UsageNonGroupUsage", zREPOS_NONE );
		      RESULT = SetCursorNextEntity( mMasLC, "M_UsageNonGroupUsage", "" );
		   }

		   //:// Add any Usage entry that's not tied to a Group.
		   //:FOR EACH mMasLC.M_Usage
		   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
		   while ( RESULT > zCURSOR_UNCHANGED )
		   {
		      RESULT = IncludeSubobjectFromSubobject( mMasLC, "M_UsageNonGroupUsage", mMasLC, "M_Usage", zPOS_AFTER );
		      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
		   }

		   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
		   RESULT = DeleteEntity( mMasLC, "M_Usage", zPOS_NEXT );
		   return( 0 );
		}


		//:DIALOG OPERATION
		//:TEST_IncludeError( VIEW ViewToWindow )

		//:   VIEW mSPLDef1 BASED ON LOD mSPLDef
		public int
		TEST_TwoViewsSetAttributeError( View     ViewToWindow )
		{
		   zVIEW    mSPLDef1 = new zVIEW( );
		   //:VIEW mSPLDef2 BASED ON LOD mSPLDef
		   zVIEW    mSPLDef2 = new zVIEW( );
		   //:STRING ( 50 ) szText
		   String   szText = null;
		   //:STRING ( 50 ) szText1
		   String   szText1 = null;
		   //:STRING ( 50 ) szText2
		   String   szText2 = null;
		   int      RESULT = 0;
		   int      lTempInteger_0 = 0;
		   int      lTempInteger_1 = 0;
		   int      lTempInteger_2 = 0;
		   int      lTempInteger_3 = 0;


		   //:// CREATING ENTITY IN ONE VIEW NOT VISIBLE IN ANOTHER

		   //:// It seems that there are two errors:
		   //:// 1. It seems that we should get an error on get attribute after line with null???????
		   // KJS 01/29/15 - For above issue, we made a change to GetVariableFromAttribute. If the entity is
		   // null and flag is not zACCEPT_NULL_ENTITY we allow thrown nullexception.
		   //:// 2. After the set cursor below, we should get the value in view mSPLDef1 created in view mSPLDef2.

		   //:// Browser is more inaccurate as it shows first view still having the first value.

		   //:ACTIVATE mSPLDef1 EMPTY
		   RESULT = ActivateEmptyObjectInstance( mSPLDef1, "mSPLDef", ViewToWindow, zSINGLE );
		   //:NAME VIEW mSPLDef1 "mSPLDef1"
		   SetNameForView( mSPLDef1, "mSPLDef1", null, zLEVEL_TASK );

		   //:CREATE ENTITY mSPLDef1.SubregPhysicalLabelDef
		   RESULT = CreateEntity( mSPLDef1, "SubregPhysicalLabelDef", zPOS_AFTER );
		   //:mSPLDef1.SubregPhysicalLabelDef.Name = "Test"
		   SetAttributeFromString( mSPLDef1, "SubregPhysicalLabelDef", "Name", "Test" );
		   //:CREATE ENTITY mSPLDef1.SPLD_LLD
		   RESULT = CreateEntity( mSPLDef1, "SPLD_LLD", zPOS_AFTER );
		   //:CREATE ENTITY mSPLDef1.LLD_Page
		   RESULT = CreateEntity( mSPLDef1, "LLD_Page", zPOS_AFTER );
		   //:CREATE ENTITY mSPLDef1.LLD_Panel
		   RESULT = CreateEntity( mSPLDef1, "LLD_Panel", zPOS_AFTER );
		   //:CREATE ENTITY mSPLDef1.LLD_Block
		   RESULT = CreateEntity( mSPLDef1, "LLD_Block", zPOS_AFTER );

		   //:CreateViewFromView( mSPLDef2, mSPLDef1 )
		   CreateViewFromView( mSPLDef2, mSPLDef1 );
		   //:NAME VIEW mSPLDef2 "mSPLDef2"
		   SetNameForView( mSPLDef2, "mSPLDef2", null, zLEVEL_TASK );
		   //:CREATE ENTITY mSPLDef1.ContinuationStatement
		   RESULT = CreateEntity( mSPLDef1, "ContinuationStatement", zPOS_AFTER );
		   //:mSPLDef1.ContinuationStatement.Text  = "Text 1"
		   SetAttributeFromString( mSPLDef1, "ContinuationStatement", "Text", "Text 1" );
		   //:szText = mSPLDef1.ContinuationStatement.Text
		   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
		   StringBuilder sb_szText;
		   if ( szText == null )
		      sb_szText = new StringBuilder( 32 );
		   else
		      sb_szText = new StringBuilder( szText );
		       GetVariableFromAttribute( sb_szText, mi_lTempInteger_0, 'S', 51, mSPLDef1, "ContinuationStatement", "Text", "", 0 );
		   lTempInteger_0 = mi_lTempInteger_0.intValue( );
		   szText = sb_szText.toString( );}
		   //:TraceLineS( "**** Continuation Text Before: ", szText )
		   TraceLineS( "**** Continuation Text Before: ", szText );
		   //:DELETE ENTITY mSPLDef1.ContinuationStatement
		   RESULT = DeleteEntity( mSPLDef1, "ContinuationStatement", zPOS_NEXT );

		   //:// Should there be an error on next statement since cursor is null???????
		   //:szText = mSPLDef1.ContinuationStatement.Text
		   try
		   {
			   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
			   StringBuilder sb_szText;
			   if ( szText == null )
			      sb_szText = new StringBuilder( 32 );
			   else
			      sb_szText = new StringBuilder( szText );
			       GetVariableFromAttribute( sb_szText, mi_lTempInteger_1, 'S', 51, mSPLDef1, "ContinuationStatement", "Text", "", 0 );
			   lTempInteger_1 = mi_lTempInteger_1.intValue( );
			   szText = sb_szText.toString( );}
			   //:TraceLineS( "**** Continuation Text After: ", szText )
	           Assert.assertTrue( "Error! We think we should crash with GetVariableFromAttribute and Null Entity!", 1 == 0 );
		   }
		   catch( Exception e)
		   {
		      TraceLineS("**** Error thrown for GetVariableFromAttribute with Null Entity, which is correct.", "");
		   }
		   TraceLineS( "**** Continuation Text After: ", szText );

		   //:CREATE ENTITY mSPLDef2.ContinuationStatement
		   RESULT = CreateEntity( mSPLDef2, "ContinuationStatement", zPOS_AFTER );
		   //:mSPLDef2.ContinuationStatement.Text  = "Text 2"
		   SetAttributeFromString( mSPLDef2, "ContinuationStatement", "Text", "Text 2" );

		   //:// This Set Cursor should position on the entity just created.
		   //:SET CURSOR FIRST mSPLDef1.ContinuationStatement
		   RESULT = SetCursorFirstEntity( mSPLDef1, "ContinuationStatement", "" );
		   //:szText1 = mSPLDef1.ContinuationStatement.Text
		   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
		   StringBuilder sb_szText1;
		   if ( szText1 == null )
		      sb_szText1 = new StringBuilder( 32 );
		   else
		      sb_szText1 = new StringBuilder( szText1 );
		       GetVariableFromAttribute( sb_szText1, mi_lTempInteger_2, 'S', 51, mSPLDef1, "ContinuationStatement", "Text", "", 0 );
		   lTempInteger_2 = mi_lTempInteger_2.intValue( );
		   szText1 = sb_szText1.toString( );}
		   //:TraceLineS( "**** After Set Text 1: ", szText1 )
		   TraceLineS( "**** After Set Text 1: ", szText1 );

		   //:szText2 = mSPLDef2.ContinuationStatement.Text
		   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
		   StringBuilder sb_szText2;
		   if ( szText2 == null )
		      sb_szText2 = new StringBuilder( 32 );
		   else
		      sb_szText2 = new StringBuilder( szText2 );
		       GetVariableFromAttribute( sb_szText2, mi_lTempInteger_3, 'S', 51, mSPLDef2, "ContinuationStatement", "Text", "", 0 );
		   lTempInteger_3 = mi_lTempInteger_3.intValue( );
		   szText2 = sb_szText2.toString( );}
		   //:TraceLineS( "**** Continuation Text 2: ", szText2 )
		   TraceLineS( "**** Continuation Text 2: ", szText2 );

		   //:IF szText1 = szText2
		   if ( ZeidonStringCompare( szText1, 1, 0, szText2, 1, 0, 51 ) == 0 )
		   {
		      //:TraceLineS( "**** TEXT VALUES MATCH", "" )
		      TraceLineS( "**** TEXT VALUES MATCH", "" );
		      //:ELSE
		   }
		   else
		   {
		      //:MessageSend( ViewToWindow, "", "View Error",
		      //:             "Attribute shows differently for two views.",
		      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
		      MessageSend( ViewToWindow, "", "View Error", "Attribute shows differently for two views.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
		      //:TraceLineS( "**** NO MATCH ON TEXT", "" )
		      TraceLineS( "**** NO MATCH ON TEXT", "" );
		      //:RETURN 2
		      if(8==8)return( 2 );
		   }

		   //:END
		   return( 0 );
		//
		// END
		}

		//:DIALOG OPERATION
		//:TEST_IncludeError( VIEW ViewToWindow )

		//:   VIEW mSPLDef1 BASED ON LOD mSPLDef
		public int
		TEST_ActivateOI_FromOIRecursive( View     ViewToWindow )
		{
		   zVIEW    mSPLDef1 = new zVIEW( );
		   //:VIEW mSPLDef2 BASED ON LOD mSPLDef
		   zVIEW    mSPLDef2 = new zVIEW( );
		   //:STRING ( 50 ) szText
		   String   szText = null;
		   //:STRING ( 50 ) szText1
		   String   szText1 = null;
		   //:STRING ( 50 ) szText2
		   String   szText2 = null;
		   int      RESULT = 0;
		   int      lTempInteger_0 = 0;
		   int      lTempInteger_1 = 0;


		   //:// ActivateOI_FromOI not working correctly for recursive subobject.

		   //:// Subentities down a recursive path are not being copied during the function.

		   //:ActivateOI_FromFile( mSPLDef1, "mSPLDef", ViewToWindow, "c:\lplr\epamms\temp\mspldef1.por", zSINGLE )
		   ActivateOI_FromFile( mSPLDef1, "mSPLDef", ViewToWindow, "target/test-classes/testdata/ePammsDon/mspldef1.por", zSINGLE );
		   //:NAME VIEW mSPLDef1 "mSPLDef1"
		   SetNameForView( mSPLDef1, "mSPLDef1", null, zLEVEL_TASK );
		   //:ActivateOI_FromOI( mSPLDef2, mSPLDef1, zSINGLE )
		   ActivateOI_FromOI( mSPLDef2, mSPLDef1, zSINGLE );
		   //:NAME VIEW mSPLDef2 "mSPLDef2"
		   SetNameForView( mSPLDef2, "mSPLDef2", null, zLEVEL_TASK );

		   //:SET CURSOR FIRST mSPLDef1.LLD_Panel
		   RESULT = SetCursorFirstEntity( mSPLDef1, "LLD_Panel", "" );
		   //:SET CURSOR FIRST mSPLDef2.LLD_Panel
		   RESULT = SetCursorFirstEntity( mSPLDef2, "LLD_Panel", "" );
		   //:SetViewToSubobject( mSPLDef1, "LLD_SubBlock" )
		   SetViewToSubobject( mSPLDef1, "LLD_SubBlock" );
		   //:SetViewToSubobject( mSPLDef2, "LLD_SubBlock" )
		   SetViewToSubobject( mSPLDef2, "LLD_SubBlock" );

		   //:IF mSPLDef1.LLD_SpecialSectionAttribute DOES NOT EXIST
		   lTempInteger_0 = CheckExistenceOfEntity( mSPLDef1, "LLD_SpecialSectionAttribute" );
		   if ( lTempInteger_0 != 0 )
		   {
		      //:MessageSend( ViewToWindow, "", "Subobject Error",
		      //:             "Test Case Error: LLD_SpecialSectionAttribute doesn't exist for mSPLDef1.",
		      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
		      MessageSend( ViewToWindow, "", "Subobject Error", "Test Case Error: LLD_SpecialSectionAttribute doesn't exist for mSPLDef1.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
		      //:RETURN 2
		      if(8==8)return( 2 );
		   }

		   //:END

		   //:// Check if recursive subobject entity was copied.
		   //:IF mSPLDef2.LLD_SpecialSectionAttrBlock DOES NOT EXIST
		   lTempInteger_1 = CheckExistenceOfEntity( mSPLDef2, "LLD_SpecialSectionAttrBlock" );
		   if ( lTempInteger_1 != 0 )
		   {
		      //:MessageSend( ViewToWindow, "", "Subobject Error",
		      //:             "Entity LLD_SpecialSectionAttribute doesn't exist for mSPLDef1.",
		      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
		      MessageSend( ViewToWindow, "", "Subobject Error", "Entity LLD_SpecialSectionAttribute doesn't exist for mSPLDef1.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
		      //:RETURN 2
		      if(8==8)return( 2 );
		   }

		   //:END

		   //:// Check if recursive subobject entity attribute was copied.
		   //:IF mSPLDef2.LLD_SpecialSectionAttrBlock.MarginTop != mSPLDef1.LLD_SpecialSectionAttrBlock.MarginTop
		   if ( CompareAttributeToAttribute( mSPLDef2, "LLD_SpecialSectionAttrBlock", "MarginTop", mSPLDef1, "LLD_SpecialSectionAttrBlock", "MarginTop" ) != 0 )
		   {
		      //:MessageSend( ViewToWindow, "", "Subobject Error",
		      //:             "Entity LLD_SpecialSectionAttribute doesn't exist for mSPLDef1.",
		      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
		      MessageSend( ViewToWindow, "", "Subobject Error", "Entity LLD_SpecialSectionAttribute doesn't exist for mSPLDef1.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
		      //:RETURN 2
		      if(8==8)return( 2 );
		   }

		   //:END
		   //:TraceLineS( "*** ActivateOI_FromOI recursive test works correctly.", "" )
		   TraceLineS( "*** ActivateOI_FromOI recursive test works correctly.", "" );
		   return( 0 );
//
		// END
		}

   }

}
