/**
 *
 */
package com.quinsoft.zeidon.test;

import junit.framework.Assert;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.EntityInstance;
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
public class TestEpamms
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
	public void ExecuteJOE_Test1()
	{
	    View         testview;
		testview = ePamms.activateEmptyObjectInstance( "mSPLDef" );
		VmlTester tester = new VmlTester( testview );
		tester.ExecuteJOE_Test1( testview );
        System.out.println("===== Finished ExecuteJOE_Test1 ========");
	}

	@Test
	public void ExecuteJOE_Test2()
	{
	    View         testview;
		testview = ePamms.activateEmptyObjectInstance( "mSPLDef" );
		VmlTester tester = new VmlTester( testview );
		tester.ExecuteJOE_Test2( testview );
        System.out.println("===== Finished ExecuteJOE_Test2 ========");
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
	public void ExecuteJOE_TestTemporalError()
	{
	    View         testview;
		testview = ePamms.activateEmptyObjectInstance( "mSPLDef" );
		VmlTester tester = new VmlTester( testview );
		tester.ExecuteJOE_TemporalDeleteError( testview );
        System.out.println("===== Finished ExecuteJOE_TemporalDeleteError ========");
	}


	private class VmlTester extends VmlObjectOperations
	{
		public VmlTester( View view )
		{
			super( view );
		}


		public int
		ExecuteJOE_Test1( View     ViewToWindow )
		{
		   zVIEW    mSPLDef = new zVIEW( );
		   //:VIEW mSPLDef2 BASED ON LOD mSPLDef
		   zVIEW    mSPLDef2 = new zVIEW( );
		   int      RESULT = 0;
		   int      lTempInteger_0 = 0;


		   //:// Execute Tests to Check for JOE Bugs.

		   //:// TEST 1
		   //:// Recursive Subobject Test.
		   //:// The current error is that the basic SetViewToSubobject function did not change the view to the subobject.

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

		   //:IF mSPLDef.LLD_Block EXISTS
           Assert.assertEquals( "SetViewToSubobject LLD_SubBlock didn't work.", -3, CheckExistenceOfEntity( mSPLDef, "LLD_Block" ) );
           /*
		   lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "LLD_Block" );
		   if ( lTempInteger_0 == 0 )
           */
		   //:END

		   //:CREATE ENTITY mSPLDef.LLD_Block
		   RESULT = CreateEntity( mSPLDef, "LLD_Block", zPOS_AFTER );
		   //:mSPLDef.LLD_Block.Name = "Block Level 2"
		   SetAttributeFromString( mSPLDef, "LLD_Block", "Name", "Block Level 2" );
		   //:CREATE ENTITY mSPLDef.LLD_SpecialSectionAttribute
		   RESULT = CreateEntity( mSPLDef, "LLD_SpecialSectionAttribute", zPOS_AFTER );
		   //:mSPLDef.LLD_SpecialSectionAttribute.Name = "Spec Attribute 2"
		   SetAttributeFromString( mSPLDef, "LLD_SpecialSectionAttribute", "Name", "Spec Attribute 2" );

		   //:ResetViewFromSubobject( mSPLDef )
		   ResetViewFromSubobject( mSPLDef );

           Assert.assertEquals( "No match on Block Level 1", 0, CompareAttributeToString( mSPLDef, "LLD_Block", "Name", "Block Level 1" ) );
		   //:IF mSPLDef.LLD_SpecialSectionAttribute.Name != "Spec Attribute 1"
           Assert.assertEquals( "No match on Spec Attribute 1", 0, CompareAttributeToString( mSPLDef, "LLD_SpecialSectionAttribute", "Name", "Spec Attribute 1" ) );

           //:SetViewToSubobject( mSPLDef, "LLD_SubBlock" )
		   SetViewToSubobject( mSPLDef, "LLD_SubBlock" );
		   //:IF mSPLDef.LLD_Block.Name != "Block Level 2"
           Assert.assertEquals( "No match on Block Level 2", 0, CompareAttributeToString( mSPLDef, "LLD_Block", "Name", "Block Level 2" ) );
		   //:IF mSPLDef.LLD_SpecialSectionAttribute.Name != "Spec Attribute 2"
           Assert.assertEquals( "No match on Spec Attribute 2", 0, CompareAttributeToString( mSPLDef, "LLD_SpecialSectionAttribute", "Name", "Spec Attribute 2" ) );

           //:ResetViewFromSubobject( mSPLDef )
		   ResetViewFromSubobject( mSPLDef );

		   //:// Now try the subobject from a different view.
		   //:CreateViewFromView( mSPLDef2, mSPLDef )
		   CreateViewFromView( mSPLDef2, mSPLDef );

		   //:SetViewToSubobject( mSPLDef2, "LLD_SubBlock" )
		   SetViewToSubobject( mSPLDef2, "LLD_SubBlock" );
           Assert.assertEquals( "No match on Block Level 2", 0, CompareAttributeToString( mSPLDef2, "LLD_Block", "Name", "Block Level 2" ) );
		   //:IF mSPLDef2.LLD_SpecialSectionAttribute.Name != "Spec Attribute 2"
           Assert.assertEquals( "No match on Spec Attribute 2", 0, CompareAttributeToString( mSPLDef2, "LLD_SpecialSectionAttribute", "Name", "Spec Attribute 2" ) );

		   //:ResetViewFromSubobject( mSPLDef2 )
		   ResetViewFromSubobject( mSPLDef2 );

		   //:DropView( mSPLDef2 )
		   DropView( mSPLDef2 );

		   //:TraceLineS( "*** JOE Test 1 successfully completed", "" )
		   TraceLineS( "*** JOE Test 1 successfully completed", "" );
		   return( 0 );
		//
//		    // Recursive code that didn't quite work.
//		    /*FOR EACH mSPLDef.BlockSubBlockComponent
//		       IF mSPLDef.BlockSubBlockComponent.Type = "Block"
//		          CREATE ENTITY mSPLDef.LLD_Block
//		          SetMatchingAttributesByName( mSPLDef, "LLD_Block", mSPLDef, "BlockSubBlockComponent", zSET_NULL )
//		       ELSE
//		          IF mSPLDef.BlockSubBlockComponent.Type = "SubBlock"
//		             CREATE ENTITY mSPLDef.LLD_SubBlock
//		             CreateViewFromView( mSPLDef2, mSPLDef )
//		             SetViewToSubobject( mSPLDef2, "LLD_SubBlock" )
//		             SetMatchingAttributesByName( mSPLDef2, "LLD_Block", mSPLDef, "BlockSubBlockComponent", zSET_NULL )
//		             ResetView( mSPLDef2 )
//		             DropView( mSPLDef2 )
//		          ELSE
//		             TraceLineS( "#### No Match on Block Type", "" )
//		          END
//		       END
//		    END*/
		// END
		}

		public int
		ExecuteJOE_TestSubobjectCreateView( View     ViewToWindow )
		{
		   zVIEW    mSPLDef = new zVIEW( );
		   //:VIEW mSPLDef2 BASED ON LOD mSPLDef
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

		//:   VIEW mSPLDef  BASED ON LOD mSPLDef
		public int
		ExecuteJOE_Test2( View     ViewToWindow )
		{
		   zVIEW    mSPLDef = new zVIEW( );
		   //:VIEW mSPLDef2 BASED ON LOD mSPLDef
		   zVIEW    mSPLDef2 = new zVIEW( );
		   int      RESULT = 0;
		   int      lTempInteger_0 = 0;


		   //:// Execute Tests to Check for JOE Bugs.

		   //:// TEST 2
		   //:// Test of Entity created in one view is not showing second view.
		   //:// This only happens when the object is activated from the database and changed.

		   //:// Activate the basic object.
		   //:ActivateOI_FromFile( mSPLDef, "mSPLDef", ViewToWindow, "c:\temp\JOE_Test2.por", zSINGLE )
		   ActivateOI_FromFile( mSPLDef, "mSPLDef", ViewToWindow, zeidonSystem.getObjectEngine().getHomeDirectory() + "/ePammsDon/JOE_Test2.por", zSINGLE );
		   //:NAME VIEW mSPLDef "mSPLDef"
		   SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );

		   //:// First, do a simple create and see if entity is seen in other view.

		   //:SET CURSOR LAST mSPLDef.LLD_Panel
		   RESULT = SetCursorLastEntity( mSPLDef, "LLD_Panel", "" );
		   //:CreateViewFromView( mSPLDef2, mSPLDef )
		   CreateViewFromView( mSPLDef2, mSPLDef );
		   //:NAME VIEW mSPLDef2 "mSPLDef2"
		   SetNameForView( mSPLDef2, "mSPLDef2", null, zLEVEL_TASK );

		   //:CREATE ENTITY mSPLDef.ContinuationStatement
		   RESULT = CreateEntity( mSPLDef, "ContinuationStatement", zPOS_AFTER );
		   //:mSPLDef.ContinuationStatement.Title = "Title 1"
		   SetAttributeFromString( mSPLDef, "ContinuationStatement", "Title", "Title 1" );
		   //:mSPLDef.ContinuationStatement.Text  = "Text 1"
		   SetAttributeFromString( mSPLDef, "ContinuationStatement", "Text", "Text 1" );

		   //:IF mSPLDef2.ContinuationStatement DOES NOT EXIST
		   lTempInteger_0 = CheckExistenceOfEntity( mSPLDef2, "ContinuationStatement" );
           Assert.assertEquals( "ContinuationStatement doesn't exist 1", 0, CheckExistenceOfEntity( mSPLDef2, "ContinuationStatement" ) );

		   //:// Next, do the same test except that the entity is first deleted from the primary view.

		   //:DELETE ENTITY mSPLDef.ContinuationStatement NONE
		   RESULT = DeleteEntity( mSPLDef, "ContinuationStatement", zREPOS_NONE );

		   //:CREATE ENTITY mSPLDef.ContinuationStatement
		   RESULT = CreateEntity( mSPLDef, "ContinuationStatement", zPOS_AFTER );
		   //:mSPLDef.ContinuationStatement.Title = "Title 1"
		   SetAttributeFromString( mSPLDef, "ContinuationStatement", "Title", "Title 1" );
		   //:mSPLDef.ContinuationStatement.Text  = "Text 1"
		   SetAttributeFromString( mSPLDef, "ContinuationStatement", "Text", "Text 1" );

		   RESULT = SetCursorFirstEntity( mSPLDef2, "ContinuationStatement", "" );
           Assert.assertEquals( "ContinuationStatement doesn't exist 2", 0, RESULT );
           /*
		   if ( RESULT < zCURSOR_SET )
           */

		   //:TraceLineS( "*** JOE Test 2 successfully completed", "" )
		   TraceLineS( "*** JOE Test 2 successfully completed", "" );
		   return( 0 );
		// END
		}

		//:   VIEW mSPLDef  BASED ON LOD mSPLDef
		public int
		ExecuteJOE_TemporalDeleteError( View     ViewToWindow )
		{
		   zVIEW    mSPLDef = new zVIEW( );
		   //:VIEW mSPLDef2 BASED ON LOD mSPLDef
		   zVIEW    mSPLDef2 = new zVIEW( );
		   int      RESULT = 0;
		   int      lTempInteger_0 = 0;
           int      SaveID = 0;

           // A temporal subobject is created for LLD_Page. Then a subentity LLD_Panel is deleted.
           // After the CancelSubobject LLD_Page, the LLD_Panel should exist again, but it does not.

		   //:// Activate the basic object.
		   //:ActivateOI_FromFile( mSPLDef, "mSPLDef", ViewToWindow, "c:\temp\JOE_Test2.por", zSINGLE )
		   ActivateOI_FromFile( mSPLDef, "mSPLDef", ViewToWindow, zeidonSystem.getObjectEngine().getHomeDirectory() + "/ePammsDon/JOE_Test2.por", zSINGLE );
		   //:NAME VIEW mSPLDef "mSPLDef"
		   SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );

           // Dad's new test. After deleting the entity from a Temporal view, if the
           // parent TemporalSubobject gets cancelled, then the deleted entity should be back.
           //:// First CancelSubobject test after delete of a subentity.

           CreateTemporalSubobjectVersion( mSPLDef, "LLD_Page" );

           RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Panel", "" );

           //:SaveID = mSPLDef.LLD_Panel.ID
           {MutableInt mi_SaveID = new MutableInt( SaveID );
               GetIntegerFromAttribute( mi_SaveID, mSPLDef, "LLD_Panel", "ID" );
           SaveID = mi_SaveID.intValue( );}

           //:DELETE ENTITY mSPLDef.LLD_Panel
           EntityInstance ei = mSPLDef.cursor( "LLD_Panel" ).getEntityInstance();
           EntityInstance page = mSPLDef.cursor( "LLD_Page" ).getEntityInstance();
           RESULT = DeleteEntity( mSPLDef, "LLD_Panel", zPOS_NEXT );

           //:CancelSubobject( mSPLDef, "LLD_Page" )
           CancelSubobject( mSPLDef, "LLD_Page" );

           //:SET CURSOR FIRST mSPLDef.LLD_Panel WHERE mSPLDef.LLD_Panel.ID = SaveID
           RESULT = SetCursorFirstEntityByInteger( mSPLDef, "LLD_Panel", "ID", SaveID, "" );
           Assert.assertEquals( "Can't find LLD_Panel but it should exist after CancelSubobject.", 0, RESULT );

           //:TraceLineS( "$$$$ Cancel of deleted Panel worked", "" )
           TraceLineS( "$$$$ Cancel of deleted Panel worked", "" );

           return( 0 );
 		}
   }
}
