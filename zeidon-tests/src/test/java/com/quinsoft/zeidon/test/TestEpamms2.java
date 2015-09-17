/**
 *
 */
package com.quinsoft.zeidon.test;

import org.junit.Assert;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.vml.zVIEW;
import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.ArrayList;

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

	@Test
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


	private class VmlTester extends VmlObjectOperations
	{
		public VmlTester( View view )
		{
			super( view );
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
   }

}
