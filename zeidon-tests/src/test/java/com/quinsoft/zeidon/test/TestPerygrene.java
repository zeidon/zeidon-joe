/**
 *
 */
package com.quinsoft.zeidon.test;

import org.junit.Assert;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
//import com.quinsoft.zeidon.test.TestSWAU.SwauVmlTester;
//import com.quinsoft.zeidon.test.TestCheetah2.VmlTester;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.vml.zVIEW;

// Just for temporary testing...
//import com.jacob.com.*;
//import com.jacob.activeX.*;


/**
 * @author DG
 *
 */
public class TestPerygrene
{
	Task         perygrene;
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
        perygrene = oe.createTask( "perygrene" );

		zeidonSystem = oe.getSystemTask();
	}

	@Test
	public void testCursorLinks()
	{
	    View         testview;
		testview = perygrene.activateEmptyObjectInstance( "mDrvShiftRoutes" );
		PerygreneVmlTester tester = new PerygreneVmlTester( testview );
		tester.testCursorLinks( testview );
        System.out.println("===== Finished testCursorLinks ========");
	}


	@Test
	public void testCursorLinks2()
	{
	    View         testview;
		testview = perygrene.activateEmptyObjectInstance( "mDrvShiftRoutes" );
		PerygreneVmlTester tester = new PerygreneVmlTester( testview );
		tester.testCursorLinks2( testview );
        System.out.println("===== Finished testCursorLinks2 ========");
	}

	@Test
	public void testCursorLinks3()
	{
	    View         testview;
		testview = perygrene.activateEmptyObjectInstance( "mDrvShiftRoutes" );
		PerygreneVmlTester tester = new PerygreneVmlTester( testview );
		tester.testCursorLinks3( testview );
        System.out.println("===== Finished testCursorLinks3 ========");
	}

	@Test
	public void testActivateOIFromOI()
	{
	    View         testview;
		testview = perygrene.activateEmptyObjectInstance( "mDrvShiftRoutes" );
		PerygreneVmlTester tester = new PerygreneVmlTester( testview );
		tester.testActivateOIFromOI( testview );
        System.out.println("===== Finished testActivateOIFromOI ========");
	}

	@Test
	public void testRecursive()
	{
	    View         testview;
		testview = perygrene.activateEmptyObjectInstance( "qDrvShift" );
		testview.setName("qDrvShift");
		PerygreneVmlTester tester = new PerygreneVmlTester( testview );
		tester.testRecursive( testview );
        System.out.println("===== Finished testRecursive ========");
	}
	@Test
	public void testRecursive2()
	{
	    View         testview;
		testview = perygrene.activateEmptyObjectInstance( "qDrvShift" );
		testview.setName("qDrvShift");
		PerygreneVmlTester tester = new PerygreneVmlTester( testview );
		tester.Test_Code2( testview );
        System.out.println("===== Finished testRecursive ========");
	}

	@Test
	public void SetCursorFirstHierError()
	{
	    View         testview;
		testview = perygrene.activateEmptyObjectInstance( "qDrvShift" );
		testview.setName("qDrvShift");
		PerygreneVmlTester tester = new PerygreneVmlTester( testview );
		tester.SetCursorFirstHierError( testview );
        System.out.println("===== Finished SetCursorFirstHierError ========");
	}

	private class PerygreneVmlTester extends VmlObjectOperations
	{
		public PerygreneVmlTester( View view )
		{
			super( view );
		}

		public int 
		SetCursorFirstHierError( View     ViewToWindow )
		{
		   zVIEW    vLOD = new zVIEW( );
		   //:INTEGER nRC
		   int      nRC = 0;
		   //:STRING ( 32 ) szEntityName
		   String   szEntityName = null;
		   int      RESULT = 0;
		   int      lTempInteger_0 = 0;
		   int      lTempInteger_1 = 0;

		   // Don is trying to do a set cursor first on recursive object. It seems to be positioning on the entity furthest down the chain "DriverPerson", as
		   // opposed to the top entity "DriverShift".
		   // FYI... I activate qDrvShift.LOD (so it's in the browser), This way we can view it's structure. 

		   // Activate the Query LOD (Query View).
    	   nRC = SfActivateSysOI_FromFile( vLOD, "TZZOLODO", ViewToWindow, "target/test-classes/testdata/perygrene/qDrvShift.LOD", zSINGLE );
		   if ( nRC < 0 )
		      return( 2 );

		   SetNameForView( vLOD, "vLOD_Test", null, zLEVEL_TASK );

		   RESULT = SetCursorFirstEntity( vLOD, "LOD", "" );
		   //:SET CURSOR FIRST vLOD.LOD_Attribute WHERE vLOD.LOD_Attribute.ZKey = 220121248   // Positions Correctly
		   RESULT = SetCursorFirstEntityByInteger( vLOD, "LOD_Attribute", "ZKey", 220121248, "" );
		   szEntityName = vLOD.cursor("LOD_Entity").getAttribute("Name").getString();
		   if ( !szEntityName.equals("DriverShift") )
		   { 
	 		   Assert.assertEquals("After SetCursorFirst, LOD_Entity should be 'DriverShift'.", szEntityName.equals("DriverShift"), true );
		   } 

		   //SET CURSOR FIRST vLOD.ER_AttributeRec WITHIN vLOD.LOD_EntityParent
		   //           WHERE vLOD.ER_AttributeRec.ZKey =  110000176                     // Positions in Error
		   //RESULT = SetCursorFirstEntityByInteger( vLOD, "ER_AttributeRec", "ZKey", 110000176, "LOD_EntityParent" );

		   //SET CURSOR FIRST vLOD.ER_AttributeRec WITHIN vLOD.LOD_EntityParent
		   //           WHERE vLOD.ER_AttributeRec.Name = "ID"                      // Positions in Error
		   RESULT = SetCursorFirstEntityByString( vLOD, "ER_AttributeRec", "Name", "ID", "LOD_EntityParent" );
		   szEntityName = vLOD.cursor("LOD_EntityParent").getAttribute("Name").getString();
		   if (  !szEntityName.equals("DriverShift") )
		   { 
	 		   Assert.assertEquals("After SetCursorFirst, LOD_EntityParent should be 'DriverShift'.", szEntityName.equals("DriverShift"), true );
		   } 

		   return( 0 );
		} 		
		
		public int 
		Test_Code2( View     ViewToWindow )
		{
		   zVIEW    zqFrame = new zVIEW( );
		   int      RESULT = 0;


		   //:ACTIVATE zqFrame EMPTY 
		   RESULT = ActivateEmptyObjectInstance( zqFrame, "zqFrame", ViewToWindow, zSINGLE );
		   //:NAME VIEW zqFrame "zqFrame" 
		   SetNameForView( zqFrame, "zqFrame", null, zLEVEL_TASK );
		   //:CREATE ENTITY zqFrame.zqFrame 
		   RESULT = CreateEntity( zqFrame, "zqFrame", zPOS_AFTER );
		   //:CREATE ENTITY zqFrame.ParentEntity 
		   RESULT = CreateEntity( zqFrame, "ParentEntity", zPOS_AFTER );
		   //:zqFrame.ParentEntity.EntityName = "DeliveryRoute"
		   SetAttributeFromString( zqFrame, "ParentEntity", "EntityName", "DeliveryRoute" );
		   //:SetViewToSubobject( zqFrame, "ChildEntity" )
		   SetViewToSubobject( zqFrame, "ChildEntity" );
		   //:CREATE ENTITY zqFrame.ParentEntity 
		   RESULT = CreateEntity( zqFrame, "ParentEntity", zPOS_AFTER );
		   //:zqFrame.ParentEntity.EntityName = "LoadRequest"
		   SetAttributeFromString( zqFrame, "ParentEntity", "EntityName", "LoadRequest" );
		   //:SetViewToSubobject( zqFrame, "ChildEntity" )
		   SetViewToSubobject( zqFrame, "ChildEntity" );
		   //:CREATE ENTITY zqFrame.ParentEntity 
		   RESULT = CreateEntity( zqFrame, "ParentEntity", zPOS_AFTER );
		   //:zqFrame.ParentEntity.EntityName = "Order"
		   SetAttributeFromString( zqFrame, "ParentEntity", "EntityName", "Order" );
		   //:TraceLineS( "@@@@ Before Reset 1", "" )
		   TraceLineS( "@@@@ Before Reset 1", "" );
		   //:ResetViewFromSubobject( zqFrame )
		   ResetViewFromSubobject( zqFrame );
		   //:TraceLineS( "@@@@ Before Reset 2", "" )
		   TraceLineS( "@@@@ Before Reset 2", "" );
		   //:ResetViewFromSubobject( zqFrame )
		   ResetViewFromSubobject( zqFrame );
		   //:TraceLineS( "@@@@ After Reset 2", "" )
		   TraceLineS( "@@@@ After Reset 2", "" );
		   return( 0 );
		// END
		} 
		
        public int
        testCursorLinks( View     ViewToWindow)
        {
        	   zVIEW    mDrvShiftRoutes = new zVIEW( );
        	   int      RESULT = 0;
        	   //:VIEW mPreviousLeg    BASED ON LOD mDrvShiftRoutes
        	   zVIEW    mPreviousLeg = new zVIEW( );
        	   //:VIEW mNextRoute      BASED ON LOD mDrvShiftRoutes
        	   zVIEW    mNextRoute = new zVIEW( );
        	   //:INTEGER RouteCount
        	   int      RouteCount = 0;
        	   //:INTEGER FuelStopCount
        	   int      FuelStopCount = 0;
        	   int      lTempInteger_0 = 0;
        	   int      lTempInteger_1 = 0;
        	   int      lTempInteger_2 = 0;
        	   
        	   // The error seemed to be when we have two parallel entities (DeliveryLeg, FuelStop). 
        	   // The second entity FuelStop, we delete the first instance of. 
        	   // If I do this and output the object to a file, we see the correct DeliveryLeg, FuelStops 
        	   // under DeliveryRoute.
        	   // When we delete all DeliveryLegs and then create new ones, if we output the object to
        	   // a file, the DeliveryLegs do not get output.
        	   // In our "real world" case, the browser looks fine (but not the output) until we do a 
        	   // commit on mDrvShiftRoutes, then it appears that the DeliveryLegs are missing.

          	 View tmpview = ViewToWindow.getTask().deserializeOi()
                	 .fromFile( "target/test-classes/testdata//perygrene/drvshiftbeforedeleting.por" )
                	 .setLodDef( "mDrvShiftRoutes" )
                	 //.setFlags(ActivateFlags.IGNORE_ENTITY_ERRORS)
                	 .activateFirst();
                	 mDrvShiftRoutes.setView(tmpview); 
                	 
         /********************* Actual Code From Perygrene "ResetLegs" *********/


                	   //:// We will create all Delivery Legs anew by deleting the current Legs and recreating them from the Fuel Stops under each Route.
                	   //:// The From address in a Leg will be determined as follows:
                	   //:// 1. For the first Leg of the Shift, the From Address will be the Home Base from the Carrier. (Note it is assumed that lCarrierDefault 
                	   //://    is positioned on the correct Home Base Address.
                	   //:// 2. Otherwise, the From Address will always be the To Address of the previous Leg.
                	   //:// The To address in a Leg will be determined as follows.
                	   //:// 1. A final Delivery Leg for the Shift will be created from the final Fuel Stop back to Home Base.
                	   //:// 2. If the Leg is being created for a Fuel Stop, the To Address will always be to the Fuel Stop Terminal or Delivery Location Address.
                	   //:// 3. After the last Fuel Stop for a Route, if the next Route is not for the same Vehicle, then a final Leg will be created back to Home Base.

                	   //:// First delete all Legs.
                	   //:FOR EACH mDrvShiftRoutes.DeliveryRoute 
                	   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
                	   while ( RESULT > zCURSOR_UNCHANGED )
                	   { 
                	      //:FOR EACH mDrvShiftRoutes.DeliveryLeg 
                	      RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryLeg", "" );
                	      while ( RESULT > zCURSOR_UNCHANGED )
                	      { 
                	         //:DELETE ENTITY mDrvShiftRoutes.DeliveryLeg NONE 
                	         RESULT = DeleteEntity( mDrvShiftRoutes, "DeliveryLeg", zREPOS_NONE );
                	         RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryLeg", "" );
                	      } 

                	      RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
                	      //:END
                	   } 

                	   //:END

                	   //:// Now create them anew.
                	   //:RouteCount = 0
                	   RouteCount = 0;
                	   //:mPreviousLeg = mDrvShiftRoutes    // Just initialize mPreviousLeg
                	   SetViewFromView( mPreviousLeg, mDrvShiftRoutes );
                	   //:NAME VIEW mPreviousLeg "mPreviousLeg"
                	   SetNameForView( mPreviousLeg, "mPreviousLeg", null, zLEVEL_TASK );
                	   //:FOR EACH mDrvShiftRoutes.DeliveryRoute 
                	   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
                	   while ( RESULT > zCURSOR_UNCHANGED )
                	   { 
                		   //:RouteCount = RouteCount + 1
                	      RouteCount = RouteCount + 1;

                	      //:FuelStopCount = 0
                	      FuelStopCount = 0;
                	      //:// Create Leg to each Fuel Stop.
                	      //:FOR EACH mDrvShiftRoutes.FuelStop 
                	      RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "FuelStop", "" );
                	      while ( RESULT > zCURSOR_UNCHANGED )
                	      { 
                	         //:FuelStopCount = FuelStopCount + 1
                	         FuelStopCount = FuelStopCount + 1;
                	         //:IF RouteCount = 1 AND FuelStopCount = 1
                	         if ( RouteCount == 1 && FuelStopCount == 1 )
                	         { 
                	            //:// This is the very first Fuel Stop for the Shift, so the From Address is Home Base.
                	            //:CREATE ENTITY mDrvShiftRoutes.DeliveryLeg 
                	            RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
                	         } 
                	         else
                	         { 
                	            //:// This is regular Leg, so the From Address is the To Address of the previous Leg.
                	            //:CREATE ENTITY mDrvShiftRoutes.DeliveryLeg 
                	            RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
                	         } 

                	         //:END
                	         //:// The To Address is always from the Fuel Stop.
                	         //:IF mDrvShiftRoutes.FuelStopTerminal EXISTS
                	         lTempInteger_0 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminal" );
                	         if ( lTempInteger_0 == 0 )
                	         { 
                	            //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopTerminalAddress 
                	            // kjsRESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mDrvShiftRoutes, "FuelStopTerminalAddress", zPOS_AFTER );
                	            //:ELSE
                	         } 
                	         else
                	         { 
                	            //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopDeliveryLocationAddress 
                	            // kjs RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
                	         } 

                	         //:END 

                	         //:DropView( mPreviousLeg )
                	         DropView( mPreviousLeg );
                	         //:mPreviousLeg = mDrvShiftRoutes    // Save position for From Address of next Leg.
                	         SetViewFromView( mPreviousLeg, mDrvShiftRoutes );
                	         //:NAME VIEW mPreviousLeg "mPreviousLeg"
                	         SetNameForView( mPreviousLeg, "mPreviousLeg", null, zLEVEL_TASK );
                	         RESULT = SetCursorNextEntity( mDrvShiftRoutes, "FuelStop", "" );
                	      } 

                	      //:END

                	      //:// Check next Route to see if we need to add a Leg back to Home Base.
                	      //:CreateViewFromView( mNextRoute, mDrvShiftRoutes )
                	      CreateViewFromView( mNextRoute, mDrvShiftRoutes );
                	      //:NAME VIEW mNextRoute "mNextRoute"
                	      SetNameForView( mNextRoute, "mNextRoute", null, zLEVEL_TASK );
                	      //:SET CURSOR NEXT mNextRoute.DeliveryRoute 
                	      RESULT = SetCursorNextEntity( mNextRoute, "DeliveryRoute", "" );
                	      //:IF mDrvShiftRoutes.RouteVehiclePowerUnit.ID != mNextRoute.RouteVehiclePowerUnit.ID 
                	      if ( CompareAttributeToAttribute( mDrvShiftRoutes, "RouteVehiclePowerUnit", "ID", mNextRoute, "RouteVehiclePowerUnit", "ID" ) != 0 )
                	      { 
                	         //:// Add Leg back to Home Base since the next Route is for a different Vehicle.
                	         //:CREATE ENTITY mDrvShiftRoutes.DeliveryLeg 
                	         RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
                	         //:IF mDrvShiftRoutes.FuelStopTerminal EXISTS
                	         lTempInteger_1 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminal" );
                	         if ( lTempInteger_1 == 0 )
                	         { 
                	            //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopTerminalAddress 
                	            // kjs RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mDrvShiftRoutes, "FuelStopTerminalAddress", zPOS_AFTER );
                	            //:ELSE
                	         } 
                	         else
                	         { 
                	            //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopDeliveryLocationAddress 
                	            // kjsRESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
                	         } 

                 	      } 

                	      //:END 
                	      //:DropView( mNextRoute )
                	      DropView( mNextRoute );

                	      //:DropView( mPreviousLeg )
                	      DropView( mPreviousLeg );
                	      //:mPreviousLeg = mDrvShiftRoutes    // Save position for From Address of next Leg.
                	      SetViewFromView( mPreviousLeg, mDrvShiftRoutes );
                	      //:NAME VIEW mPreviousLeg "mPreviousLeg"
                	      SetNameForView( mPreviousLeg, "mPreviousLeg", null, zLEVEL_TASK );
                	      RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
                	   } 

                	   //:   
                	   //:END

                	   //:// Add the last Fuel Stop back to Home Base.
                	   //:CREATE ENTITY mDrvShiftRoutes.DeliveryLeg 
                	   RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
                	   //:IF mDrvShiftRoutes.FuelStopTerminal EXISTS
                	   lTempInteger_2 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminal" );
                	   if ( lTempInteger_2 == 0 )
                	   { 
                	      //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopTerminalAddress 
                	      // kjs RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mDrvShiftRoutes, "FuelStopTerminalAddress", zPOS_AFTER );
                	      //:ELSE
                	   } 
                	   else
                	   { 
                	      //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopDeliveryLocationAddress 
                	      // kjs RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
                	   } 

                	   //:END 
                	   //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM lCarrierDefault.VehicleHomeBaseAddress 
                	   // kjsRESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", lCarrierDefault, "VehicleHomeBaseAddress", zPOS_AFTER );
                	   //:DropView( mPreviousLeg )
                	   DropView( mPreviousLeg );

         /********************* END OF ACTUAL CODE ******************************/


        	   return 0;
       }

        public int
        testCursorLinks2( View     ViewToWindow)
        {
     	   zVIEW    mDrvShiftRoutes = new zVIEW( );
    	   zVIEW    mDrvShiftRoutes2 = new zVIEW( );
           int      RESULT = 0;
    	   
    	   // The error seems to be when we have two parallel entities (DeliveryLeg, FuelStop). 
    	   // The second entity: FuelStop, we delete the first instance of. 
    	   // If I do this and output the object to a file, we see the correct DeliveryLeg, FuelStops 
    	   // under DeliveryRoute.
    	   // When we delete all DeliveryLegs under DeliveryRoute and then create new ones, 
    	   // if we output the object to a file, the DeliveryLegs (for the DeliveryRoute we deleted the first FuelStop) 
    	   // do not get output. In our "real world" case, the browser looks fine (but not the output) until we do a 
    	   // commit on mDrvShiftRoutes, then it appears that the DeliveryLegs are missing.

    	   ActivateOI_FromFile( mDrvShiftRoutes, "mDrvShiftRoutes", ViewToWindow, "target/test-classes/testdata//perygrene/mDrvShiftRoutes.json", zSINGLE );
    	   SetNameForView( mDrvShiftRoutes, "RoutesTEST", null, zLEVEL_TASK );
    	   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
    	   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "FuelStop", "" );
    	   // Delete the first FuelStop under DeliveryRoute.
    	   RESULT = DeleteEntity( mDrvShiftRoutes, "FuelStop", zPOS_NEXT );
    	   
    	   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryLeg", "" );
    	   while ( RESULT > zCURSOR_UNCHANGED )
    	   { 
    	      RESULT = DeleteEntity( mDrvShiftRoutes, "DeliveryLeg", zREPOS_NONE );
    	      RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryLeg", "" );
    	   } 
        	   
    	   // Create a new DeliveryLeg. 
    	   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
    	   RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
    	   SetAttributeFromString( mDrvShiftRoutes, "DeliveryLeg", "Type", "T-D" );
    	   SetAttributeFromInteger( mDrvShiftRoutes, "DeliveryLeg", "DistanceInMiles", 18 );

    	   mDrvShiftRoutes.serializeOi().toFile( "target/test-classes/testdata//perygrene/test2.json" );
    	   ActivateOI_FromFile( mDrvShiftRoutes2, "mDrvShiftRoutes", ViewToWindow, "target/test-classes/testdata//perygrene/test2.json", zSINGLE );
    	   SetNameForView( mDrvShiftRoutes2, "RoutesTEST2", null, zLEVEL_TASK );
    	   
    	   mDrvShiftRoutes2.cursor("DeliveryRoute").setFirst();
		   RESULT = CheckExistenceOfEntity( mDrvShiftRoutes2, "DeliveryLeg");
 		   Assert.assertEquals("DeliveryLeg should exist but does not.", CursorResult.SET.toInt(), RESULT );

 		   return 0;
       }

        public int
        testCursorLinks3( View     ViewToWindow)
        {
     	   zVIEW    mDrvShiftRoutes = new zVIEW( );
    	   zVIEW    mDrvShiftRoutes2 = new zVIEW( );
        	   int      RESULT = 0;
        	   zVIEW    mPreviousLeg = new zVIEW( );
        	   //:VIEW mNextRoute      BASED ON LOD mDrvShiftRoutes
        	   zVIEW    mNextRoute = new zVIEW( );
        	   //:INTEGER RouteCount
        	   int      RouteCount = 0;
        	   //:INTEGER FuelStopCount
        	   int      FuelStopCount = 0;
        	   int      lTempInteger_0 = 0;
        	   int      lTempInteger_1 = 0;
        	   int      lTempInteger_2 = 0;
        	   
        	   // I am trying to recreate the error we get at perygrene and the browser.
        	   // Since I couldn't activate the file that had incrementals, I am trying to
        	   // start at the beginning where mDrvShiftRoutes is first activated, and then
        	   // try to do the same steps to create a problem.
        	   // Of course, this seems to work properly so I have no idea at the moment
        	   // why this is different from the real test.
        	   
        	   // The error seemed to be when we have two parallel entities (DeliveryLeg, FuelStop). 
        	   // The second entity FuelStop, we delete the first instance of. 
        	   // If I do this and output the object to a file, we see the correct DeliveryLeg, FuelStops 
        	   // under DeliveryRoute.
        	   // When we delete all DeliveryLegs and then create new ones, if we output the object to
        	   // a file, the DeliveryLegs do not get output.
        	   // In our "real world" case, the browser looks fine (but not the output) until we do a 
        	   // commit on mDrvShiftRoutes, then it appears that the DeliveryLegs are missing.

        	   ActivateOI_FromFile( mDrvShiftRoutes, "mDrvShiftRoutes", ViewToWindow, "target/test-classes/testdata//perygrene/mDrvShiftRoutes.por", zSINGLE );
        	   SetNameForView( mDrvShiftRoutes, "RoutesTEST", null, zLEVEL_TASK );
        	   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
        	   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "FuelStop", "" );
        	   // Delete the first FuelStop under DeliveryRoute.
        	   RESULT = DeleteEntity( mDrvShiftRoutes, "FuelStop", zPOS_NEXT );

        	   RESULT = SetCursorLastEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
        	   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "FuelStop", "" );
        	   // Delete the first FuelStop under DeliveryRoute.
        	   RESULT = DeleteEntity( mDrvShiftRoutes, "FuelStop", zPOS_NEXT );
        	   
               /********************* Actual Code From Perygrene "ResetLegs" *********/


        	   //:// We will create all Delivery Legs anew by deleting the current Legs and recreating them from the Fuel Stops under each Route.
        	   //:// The From address in a Leg will be determined as follows:
        	   //:// 1. For the first Leg of the Shift, the From Address will be the Home Base from the Carrier. (Note it is assumed that lCarrierDefault 
        	   //://    is positioned on the correct Home Base Address.
        	   //:// 2. Otherwise, the From Address will always be the To Address of the previous Leg.
        	   //:// The To address in a Leg will be determined as follows.
        	   //:// 1. A final Delivery Leg for the Shift will be created from the final Fuel Stop back to Home Base.
        	   //:// 2. If the Leg is being created for a Fuel Stop, the To Address will always be to the Fuel Stop Terminal or Delivery Location Address.
        	   //:// 3. After the last Fuel Stop for a Route, if the next Route is not for the same Vehicle, then a final Leg will be created back to Home Base.

        	   //:// First delete all Legs.
        	   //:FOR EACH mDrvShiftRoutes.DeliveryRoute 
        	   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
        	   while ( RESULT > zCURSOR_UNCHANGED )
        	   { 
        	      //:FOR EACH mDrvShiftRoutes.DeliveryLeg 
        	      RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryLeg", "" );
        	      while ( RESULT > zCURSOR_UNCHANGED )
        	      { 
        	         //:DELETE ENTITY mDrvShiftRoutes.DeliveryLeg NONE 
        	         RESULT = DeleteEntity( mDrvShiftRoutes, "DeliveryLeg", zREPOS_NONE );
        	         RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryLeg", "" );
        	      } 

        	      RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
        	      //:END
        	   } 

        	   //:END

        	   //:// Now create them anew.
        	   //:RouteCount = 0
        	   RouteCount = 0;
        	   //:mPreviousLeg = mDrvShiftRoutes    // Just initialize mPreviousLeg
        	   SetViewFromView( mPreviousLeg, mDrvShiftRoutes );
        	   //:NAME VIEW mPreviousLeg "mPreviousLeg"
        	   SetNameForView( mPreviousLeg, "mPreviousLeg", null, zLEVEL_TASK );
        	   //:FOR EACH mDrvShiftRoutes.DeliveryRoute 
        	   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
        	   while ( RESULT > zCURSOR_UNCHANGED )
        	   { 
        		   //:RouteCount = RouteCount + 1
        	      RouteCount = RouteCount + 1;

        	      //:FuelStopCount = 0
        	      FuelStopCount = 0;
        	      //:// Create Leg to each Fuel Stop.
        	      //:FOR EACH mDrvShiftRoutes.FuelStop 
        	      RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "FuelStop", "" );
        	      while ( RESULT > zCURSOR_UNCHANGED )
        	      { 
        	         //:FuelStopCount = FuelStopCount + 1
        	         FuelStopCount = FuelStopCount + 1;
        	         //:IF RouteCount = 1 AND FuelStopCount = 1
        	         if ( RouteCount == 1 && FuelStopCount == 1 )
        	         { 
        	            //:// This is the very first Fuel Stop for the Shift, so the From Address is Home Base.
        	            //:CREATE ENTITY mDrvShiftRoutes.DeliveryLeg 
        	            RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
        	         } 
        	         else
        	         { 
        	            //:// This is regular Leg, so the From Address is the To Address of the previous Leg.
        	            //:CREATE ENTITY mDrvShiftRoutes.DeliveryLeg 
        	            RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
        	         } 

        	         //:END
        	         //:// The To Address is always from the Fuel Stop.
        	         //:IF mDrvShiftRoutes.FuelStopTerminal EXISTS
        	         lTempInteger_0 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminal" );
        	         if ( lTempInteger_0 == 0 )
        	         { 
        	            //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopTerminalAddress 
        	            // kjsRESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mDrvShiftRoutes, "FuelStopTerminalAddress", zPOS_AFTER );
        	            //:ELSE
        	         } 
        	         else
        	         { 
        	            //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopDeliveryLocationAddress 
        	            // kjs RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
        	         } 

        	         //:END 

        	         //:DropView( mPreviousLeg )
        	         DropView( mPreviousLeg );
        	         //:mPreviousLeg = mDrvShiftRoutes    // Save position for From Address of next Leg.
        	         SetViewFromView( mPreviousLeg, mDrvShiftRoutes );
        	         //:NAME VIEW mPreviousLeg "mPreviousLeg"
        	         SetNameForView( mPreviousLeg, "mPreviousLeg", null, zLEVEL_TASK );
        	         RESULT = SetCursorNextEntity( mDrvShiftRoutes, "FuelStop", "" );
        	      } 

        	      //:END

        	      //:// Check next Route to see if we need to add a Leg back to Home Base.
        	      //:CreateViewFromView( mNextRoute, mDrvShiftRoutes )
        	      CreateViewFromView( mNextRoute, mDrvShiftRoutes );
        	      //:NAME VIEW mNextRoute "mNextRoute"
        	      SetNameForView( mNextRoute, "mNextRoute", null, zLEVEL_TASK );
        	      //:SET CURSOR NEXT mNextRoute.DeliveryRoute 
        	      RESULT = SetCursorNextEntity( mNextRoute, "DeliveryRoute", "" );
        	      //:IF mDrvShiftRoutes.RouteVehiclePowerUnit.ID != mNextRoute.RouteVehiclePowerUnit.ID 
        	      if ( CompareAttributeToAttribute( mDrvShiftRoutes, "RouteVehiclePowerUnit", "ID", mNextRoute, "RouteVehiclePowerUnit", "ID" ) != 0 )
        	      { 
        	         //:// Add Leg back to Home Base since the next Route is for a different Vehicle.
        	         //:CREATE ENTITY mDrvShiftRoutes.DeliveryLeg 
        	         RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
        	         //:IF mDrvShiftRoutes.FuelStopTerminal EXISTS
        	         lTempInteger_1 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminal" );
        	         if ( lTempInteger_1 == 0 )
        	         { 
        	            //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopTerminalAddress 
        	            // kjs RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mDrvShiftRoutes, "FuelStopTerminalAddress", zPOS_AFTER );
        	            //:ELSE
        	         } 
        	         else
        	         { 
        	            //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopDeliveryLocationAddress 
        	            // kjsRESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
        	         } 

         	      } 

        	      //:END 
        	      //:DropView( mNextRoute )
        	      DropView( mNextRoute );

        	      //:DropView( mPreviousLeg )
        	      DropView( mPreviousLeg );
        	      //:mPreviousLeg = mDrvShiftRoutes    // Save position for From Address of next Leg.
        	      SetViewFromView( mPreviousLeg, mDrvShiftRoutes );
        	      //:NAME VIEW mPreviousLeg "mPreviousLeg"
        	      SetNameForView( mPreviousLeg, "mPreviousLeg", null, zLEVEL_TASK );
        	      RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
        	   } 

        	   //:   
        	   //:END

        	   //:// Add the last Fuel Stop back to Home Base.
        	   //:CREATE ENTITY mDrvShiftRoutes.DeliveryLeg 
        	   RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
        	   //:IF mDrvShiftRoutes.FuelStopTerminal EXISTS
        	   lTempInteger_2 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminal" );
        	   if ( lTempInteger_2 == 0 )
        	   { 
        	      //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopTerminalAddress 
        	      // kjs RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mDrvShiftRoutes, "FuelStopTerminalAddress", zPOS_AFTER );
        	      //:ELSE
        	   } 
        	   else
        	   { 
        	      //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopDeliveryLocationAddress 
        	      // kjs RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
        	   } 

        	   //:END 
        	   //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM lCarrierDefault.VehicleHomeBaseAddress 
        	   // kjsRESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", lCarrierDefault, "VehicleHomeBaseAddress", zPOS_AFTER );
        	   //:DropView( mPreviousLeg )
        	   DropView( mPreviousLeg );

 /********************* END OF ACTUAL CODE ******************************/
        	   //mDrvShiftRoutes.serializeOi().withIncremental().toFile( "target/test-classes/testdata//perygrene/test3.por" );
        	   mDrvShiftRoutes.serializeOi().toFile( "target/test-classes/testdata//perygrene/test3.json" );
        	   ActivateOI_FromFile( mDrvShiftRoutes2, "mDrvShiftRoutes", ViewToWindow, "target/test-classes/testdata//perygrene/test3.json", zSINGLE );
        	   SetNameForView( mDrvShiftRoutes2, "RoutesTEST2", null, zLEVEL_TASK );
        	   
        	   mDrvShiftRoutes2.cursor("DeliveryRoute").setFirst();
    		   RESULT = CheckExistenceOfEntity( mDrvShiftRoutes2, "DeliveryLeg");
     		   Assert.assertEquals("DeliveryLeg should exist but does not.", CursorResult.SET.toInt(), RESULT );
        	   

        	   return 0;
       }

        public int
        testActivateOIFromOI( View     ViewToWindow)
        {
     	   zVIEW    mFgtBill = new zVIEW( );
    	   zVIEW    mFgtBillTmp = new zVIEW( );
    	   zVIEW    mBillContract = new zVIEW( );
           int      RESULT = 0;
    	   int      lTempInteger_0 = 0;
    	   
    	   // In the following example... if we do a createEntity on FreightBillLineItem then do an  
    	   // include/exclude/include for child BillingContractTransaction, the excluded entity
    	   // is cleaned up and is not marked as hidden.
    	   // When instead of createEntity, we do a createTemporalEntity instead of createEntity, 
    	   // the hidden entity still exists and causes an error in the activateOiFromOi.

    	   ActivateOI_FromFile( mFgtBill, "mFgtBill", ViewToWindow, "target/test-classes/testdata//perygrene/mFgtBill.json", zSINGLE );
    	   ActivateOI_FromFile( mBillContract, "mBillContract", ViewToWindow, "target/test-classes/testdata//perygrene/mBillContract.json", zSINGLE );
    	   //
    	   RESULT = SetCursorLastEntity( mFgtBill, "FreightBillLineItem", "" );
    	   //mFgtBill.cursor("FreightBillLineItem").createEntity();
    	   mFgtBill.cursor("FreightBillLineItem").createEntity(CursorPosition.NEXT);
    	   
    	   RESULT = SetCursorFirstEntity( mBillContract, "BillingContractTransaction", "" );
    	   RESULT = IncludeSubobjectFromSubobject( mFgtBill, "BillingContractTransaction", mBillContract, "BillingContractTransaction", zPOS_AFTER );
    	   SetAttributeFromAttribute( mFgtBill, "FreightBillLineItem", "Description", mFgtBill, "BillingTransaction", "Name" );
           mBillContract.cursor( "BillingContractTransaction" ).setNext();
    	   RESULT = ExcludeEntity( mFgtBill, "BillingContractTransaction", zREPOS_AFTER );
    	   RESULT = IncludeSubobjectFromSubobject( mFgtBill, "BillingContractTransaction", mBillContract, "BillingContractTransaction", zPOS_AFTER );
           // Works correctly
    	   ActivateOI_FromOI( mFgtBillTmp, mFgtBill, zSINGLE );
           mFgtBillTmp.drop();
    	   
    	   RESULT = SetCursorLastEntity( mFgtBill, "FreightBillLineItem", "" );
    	   //CreateTemporalEntity( mFgtBill, "FreightBillLineItem", zPOS_AFTER );
           mFgtBill.cursor("FreightBillLineItem").createTemporalEntity( CursorPosition.NEXT );
    	   
    	   RESULT = SetCursorFirstEntity( mBillContract, "BillingContractTransaction", "" );
    	   RESULT = IncludeSubobjectFromSubobject( mFgtBill, "BillingContractTransaction", mBillContract, "BillingContractTransaction", zPOS_AFTER );
    	   SetAttributeFromAttribute( mFgtBill, "FreightBillLineItem", "Description", mFgtBill, "BillingTransaction", "Name" );
           mBillContract.cursor( "BillingContractTransaction" ).setNext();
    	   RESULT = ExcludeEntity( mFgtBill, "BillingContractTransaction", zREPOS_AFTER );
    	   RESULT = IncludeSubobjectFromSubobject( mFgtBill, "BillingContractTransaction", mBillContract, "BillingContractTransaction", zPOS_AFTER );
    	   //AcceptSubobject( mFgtBill, "FreightBillLineItem" );
    	   mFgtBill.cursor("FreightBillLineItem").acceptSubobject();
           // Doesn't work because the AcceptSubobject doesn't clean up the exclude, marked as hidden???
    	   ActivateOI_FromOI( mFgtBillTmp, mFgtBill, zSINGLE );
    	   
 		   return 0;
       }

        public int
        testRecursive( View     ViewToWindow)
        {
        	   zVIEW    zqFrame = new zVIEW( );
        	   //:VIEW zqFrameHier BASED ON LOD  zqFrame
        	   zVIEW    zqFrameHier = new zVIEW( );
        	   //:VIEW vLOD
        	   zVIEW    vLOD = new zVIEW( );
        	   //:STRING ( 400 ) szFileName
        	   String   szFileName = null;
        	   //:STRING ( 32 )  szEntityType
        	   String   szEntityType = null;
        	   //:STRING ( 32 )  szEntityName
        	   String   szEntityName = null;
        	   //:SHORT          nRC
        	   int      nRC = 0;
        	   //:INTEGER HierarchicalLevel
        	   int      HierarchicalLevel = 0;
        	   int      RESULT = 0;
        	   String   szTempString_0 = null;
        	   String   szTempString_1 = null;
        	   String   szTempString_2 = null;
        	   String   szTempString_3 = null;
        	   String   szTempString_4 = null;
        	   String   szTempString_5 = null;
        	   String   szTempString_6 = null;
        	   String   szTempString_7 = null;
        	   String   szTempString_8 = null;
        	   int      lTempInteger_0 = 0;
        	   String   szTempString_9 = null;
        	   String   szTempString_10 = null;
        	   int      lTempInteger_1 = 0;
        	   String   szTempString_11 = null;
        	   String   szTempString_12 = null;
        	   String   szTempString_13 = null;
        	   int      lTempInteger_2 = 0;
        	   String   szTempString_14 = null;
        	   String   szTempString_15 = null;
        	   int      lTempInteger_3 = 0;
        	   String   szTempString_16 = null;
        	   String   szTempString_17 = null;
        	   String   szTempString_18 = null;
        	   String   szTempString_19 = null;
        	   int      lTempInteger_4 = 0;
        	   String   szTempString_20 = null;

/*
 * I think dad said there might be a couple of errors he encountered. But the one I first encountered is that he is  
 * creating an object recursively (zqFrame) based on a LOD loaded in tzzolodo.xod.
 * After setViewSubobject a couple of times, we then resetfromsubobject and zqFrame does no reset properly. 
 * zqFrame resets to the wrong parent (Driver instead of DriverPerson). 
 * Whereas the tzzolodo object resets correctly. I'm wondering if it has to do with the fact that in zqFrame,  
 * there are no keys because we are creating and haven't committed...  ??

   FYI... I activate qDrvShift.LOD (so it's in the browser), this is the object that gets loaded into tzzolodo. 
   This way we can view it's structure to see how zqFrame should be being built.
   
 */
        	   //:// Activate the LOD.
        	   nRC = SfActivateSysOI_FromFile( vLOD, "TZZOLODO", ViewToWindow, "target/test-classes/testdata/perygrene/qDrvShift.LOD", zSINGLE );
        	   SetNameForView( vLOD, "qDrvShiftTest", null, zLEVEL_TASK );

        	   RESULT = ActivateEmptyObjectInstance( zqFrame, "zqFrame", ViewToWindow, zSINGLE );
        	   RESULT = CreateEntity( zqFrame, "zqFrame", zPOS_AFTER );
        	   SetNameForView( zqFrame, "zqFrame", null, zLEVEL_TASK );
        	   SetAttributeFromAttribute( zqFrame, "zqFrame", "QueryObjectName", vLOD, "LOD", "Name" );
        	   SetAttributeFromAttribute( zqFrame, "zqFrame", "QueryObjectRootEntityName", vLOD, "LOD_EntityParent", "Name" );
        	   TraceLineS( "Building zqFrame subobject ", "" );
        	   TraceLineS( " ", "" );
        	   //:TraceLineS( "Top LOD Entity: ", vLOD.LOD_EntityParent.Name )
        	   TraceLineS( "Top LOD Entity: ", vLOD.cursor("LOD_EntityParent").getAttribute("Name" ).getString() );

        	   //:// Create recursive ParentEntity subobject structure.
        	   nRC = o_TestCodeRecurs1( vLOD, zqFrame, 1 );
        	   TraceLineS( " ", "" );

        	   //:TraceLineS( "Top Parent Entity 1: ", zqFrame.ParentEntity.EntityName )
        	   TraceLineS( "Top Parent Entity 1: ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString());
        	   ResetViewFromSubobjectTop( zqFrame );
        	   TraceLineS( "Top Parent Entity 2: ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
        	   RESULT = SetCursorFirstEntity( zqFrame, "ParentEntity", "" );
        	   //:TraceLineS( "Top Parent Entity 3: ", zqFrame.ParentEntity.EntityName ) 
        	   TraceLineS( "Top Parent Entity 3: ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );

        	   CreateViewFromView( zqFrameHier, zqFrame );
        	   SetNameForView( zqFrameHier, "zqFrameHier", null, zLEVEL_TASK );
        	   DefineHierarchicalCursor( zqFrameHier, "ParentEntity" );

        	   //:// Process zqFrame hierarchically.
        	   //:TraceLineS( " ", "" )
        	   TraceLineS( " ", "" );
        	   //:TraceLineS( "Process zqFrame Hierarchically ", "" )
        	   TraceLineS( "Process zqFrame Hierarchically ", "" );
        	   //:TraceLineS( "Root Entity: ", zqFrameHier.ParentEntity.EntityName )
        	   TraceLineS( "Root Entity: ", zqFrameHier.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
        	   //:nRC = SetCursorNextEntityHierarchical( HierarchicalLevel, szEntityType, zqFrameHier ) 
        	   {StringBuilder sb_szEntityType;
        	   if ( szEntityType == null )
        	      sb_szEntityType = new StringBuilder( 32 );
        	   else
        	      sb_szEntityType = new StringBuilder( szEntityType );
        	   MutableInt mi_HierarchicalLevel = new MutableInt( HierarchicalLevel );
        	       nRC = SetCursorNextEntityHierarchical( mi_HierarchicalLevel, sb_szEntityType, zqFrameHier );
        	   szEntityType = sb_szEntityType.toString( );
        	   HierarchicalLevel = mi_HierarchicalLevel.intValue( );}
        	   
        	   //:LOOP WHILE nRC >= 0 
        	   while ( nRC >= 0 )
        	   { 
        	      //:TraceLineS( "...", "" )
        	      TraceLineS( "...", "" );
        	      //:TraceLineS( "Type: ", szEntityType )
        	      TraceLineS( "Type: ", szEntityType );
        	      //:GetStringFromAttribute( szEntityName, zqFrameHier, szEntityType, "EntityName" )
        	      szEntityName = zqFrameHier.cursor(szEntityType).getAttribute("EntityName" ).getString();
        	      TraceLineS( "Entity: ", szEntityName );
        	      TraceLineI( "Level: ", HierarchicalLevel );
        	      //:TraceLineS( "Parent Entity: ", zqFrameHier.ParentEntity.EntityName )
        	      TraceLineS( "Parent Entity: ", zqFrameHier.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
        	      //:IF szEntityType = "ChildEntity"
        	      if ( ZeidonStringCompare( szEntityType, 1, 0, "ChildEntity", 1, 0, 33 ) == 0 )
        	      { 
        	         //:TraceLineS( "Child Entity: ", zqFrameHier.ChildEntity.EntityName )
        	         TraceLineS( "Child Entity: ", zqFrameHier.cursor("ChildEntity").getAttribute("EntityName" ).getString() );
        	      } 
       	      
        	      //:nRC = SetCursorNextEntityHierarchical( HierarchicalLevel, szEntityName, zqFrameHier )   
        	      {StringBuilder sb_szEntityName;
        	      if ( szEntityName == null )
        	         sb_szEntityName = new StringBuilder( 32 );
        	      else
        	         sb_szEntityName = new StringBuilder( szEntityName );
        	      MutableInt mi_HierarchicalLevel = new MutableInt( HierarchicalLevel );
        	             nRC = SetCursorNextEntityHierarchical( mi_HierarchicalLevel, sb_szEntityName, zqFrameHier );
        	      szEntityType = sb_szEntityName.toString( );
        	      HierarchicalLevel = mi_HierarchicalLevel.intValue( );}
        	   } 

        	   

        	   //:// Test Find recursive Entity by Name.
        	   //:// The TestCodeRecurs2 operation crashes by going into an eternal loop.
        	   //://TestCodeRecurs2( zqFrame, "DeliveryLeg" )


        	   //:// Test of Recursive Error.

        	   TraceLineS( "... ", "" );
        	   TraceLineS( "... ", "" );
        	   TraceLineS( "Recursive Error Test ", "" );
        	   TraceLineS( "... ", "" );

        	   //:// Step down from DriverShift to Driver.
        	   //:TraceLineS( "Parent DriverShift: ", zqFrame.ParentEntity.EntityName )
        	   TraceLineS( "Parent DriverShift: ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
        	   //:TraceLineS( "Child Driver: ", zqFrame.ChildEntity.EntityName )
        	   TraceLineS( "Child Driver: ", zqFrame.cursor("ChildEntity").getAttribute("EntityName" ).getString() );
        	   //:IF zqFrame.ChildEntity EXISTS
        	   if ( CheckExistenceOfEntity( zqFrame, "ChildEntity" ) == 0 )
        	   { 
        	      TraceLineS( "ChildEntity Exists: ", "(Correct)" );
        	   } 
        	   else
        	   { 
        	      TraceLineS( "No ChildEntity  ", "(Incorrect)" );
                  Assert.assertEquals(  CheckExistenceOfEntity( zqFrame, "ChildEntity" ), 0 );
        	   } 

        	   
        	   SetViewToSubobject( zqFrame, "ChildEntity" );
        	   TraceLineS( "(SetViewToSubobject) ", "" );
        	   //:TraceLineS( "New Parent Driver: ", zqFrame.ParentEntity.EntityName )
        	   TraceLineS( "New Parent Driver: ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
        	   //:TraceLineS( "New Child DriverPerson: ", zqFrame.ChildEntity.EntityName )
        	   //:IF zqFrame.ChildEntity EXISTS
        	   if ( CheckExistenceOfEntity( zqFrame, "ChildEntity" ) == 0 )
        	   { 
        	      //:TraceLineS( "ChildEntity Exists: ", zqFrame.ChildEntity.EntityName )
        	      TraceLineS( "ChildEntity Exists: ", zqFrame.cursor("ChildEntity").getAttribute("EntityName" ).getString() );
        	   } 
        	   else
        	   { 
        	      TraceLineS( "No ChildEntity (should be DriverPerson)  ", "(Incorrect)" );
        	      // When we get here, it looks like there are two ParentEntity(s) Driver/DriverPerson but DriverPerson should be the child.
                  Assert.assertEquals(  CheckExistenceOfEntity( zqFrame, "ChildEntity" ), 0 );
        	   } 
        	   
        	   TraceLineS( "... ", "" );

        	   //:// Step down from Driver to DriverPerson
        	   TraceLineS( "Child DriverPerson: ", zqFrame.cursor("ChildEntity").getAttribute("EntityName" ).getString());
        	   SetViewToSubobject( zqFrame, "ChildEntity" );
        	   TraceLineS( "(SetViewToSubobject) ", "" );
        	   //:IF zqFrame.ParentEntity EXISTS
        	   if ( CheckExistenceOfEntity( zqFrame, "ParentEntity" ) == 0 )
        	   { 
            	  TraceLineS( "Parent DriverPerson: ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
        	      TraceLineS( "ParentEntity Exists: ", "(Correct)" );
        	   } 
        	   else
        	   { 
             	   TraceLineS( "Parent Driver: ", "" );
        	      TraceLineS( "No ParentEntity  ", "(Incorrect)" );
                  Assert.assertEquals(  CheckExistenceOfEntity( zqFrame, "ParentEntity" ), 0 );
        	   } 

        	   
        	   //:TraceLineS( "New Parent DriverPerson: ", zqFrame.ParentEntity.EntityName )
        	   TraceLineS( "New Parent DriverPerson: ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
        	   //:TraceLineS( "New Child Null: ", zqFrame.ChildEntity.EntityName )
        	   //:IF zqFrame.ChildEntity EXISTS
        	   if ( CheckExistenceOfEntity( zqFrame, "ChildEntity" ) == 0 )
        	   { 
            	   TraceLineS( "New Child Null: ", zqFrame.cursor("ChildEntity").getAttribute("EntityName" ).getString() );
        	      //:TraceLineS( "ChildEntity Exists: ", zqFrame.ChildEntity.EntityName )
        	      TraceLineS( "ChildEntity Exists: ", zqFrame.cursor("ChildEntity").getAttribute("EntityName" ).getString() );
        	   } 
        	   else
        	   { 
            	   TraceLineS( "New Child Null: ", "" );
        	      TraceLineS( "No ChildEntity  ", "" );
        	   } 

        	   //:// Check SET CURSOR NEXT on DriverPerson/Carrier.
        	   TraceLineS( "... ", "" );
        	   TraceLineS( "(SET CURSOR NEXT DriverPerson) ", "" );
        	   RESULT = SetCursorNextEntity( zqFrame, "ParentEntity", "" );
        	   if ( RESULT >= zCURSOR_SET )
        	   { 
        	      TraceLineS( "Next Parent Exists ", "(Correct)" );
        	      TraceLineS( "Parent Carrier: ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
        	   } 
        	   else
        	   { 
        	      TraceLineS( "Next Parent NOT exist", "(Incorrect)" );
                  Assert.assertTrue( "Next Parent does NOT exist", RESULT >= zCURSOR_SET );
        	   } 

        	   TraceLineS( "... ", "" );

        	   //:// Step down from DriverPerson to Null.
        	   //:TraceLineS( "Parent DriverPerson: ", zqFrame.ParentEntity.EntityName )
        	   //:TraceLineS( "Child Null: ", zqFrame.ChildEntity.EntityName )
        	   RESULT = SetCursorFirstEntity( zqFrame, "ParentEntity", "" );
        	   TraceLineS( "Parent DriverPerson: ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
        	   TraceLineS( "Child Null: ", "" );
        	   SetViewToSubobject( zqFrame, "ChildEntity" );
        	   TraceLineS( "(SetViewToSubobject) ", "" );
        	   //:IF zqFrame.ParentEntity EXISTS
        	   if ( CheckExistenceOfEntity( zqFrame, "ParentEntity" ) == 0 )
        	   { 
            	  TraceLineS( "Parent DriverPerson: ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
        	      TraceLineS( "ParentEntity Exists: ", "" );
        	   } 
        	   else
        	   { 
            	  TraceLineS( "Parent DriverPerson: ", "" );
        	      TraceLineS( "No ParentEntity  ", "(Correct)" );
        	   } 

        	   //:// Check SET CURSOR NEXT on DriverPerson again after Reset.
        	   TraceLineS( "... ", "" );
        	   ResetViewFromSubobject( zqFrame );
        	   TraceLineS( "(ResetViewFromSubobject) ", "" );
        	   TraceLineS( "(SET CURSOR NEXT after reset) ", "" );
        	   RESULT = SetCursorNextEntity( zqFrame, "ParentEntity", "" );
        	   if ( RESULT >= zCURSOR_SET )
        	   { 
        	      TraceLineS( "Next Parent Exists ", "(Correct)" );
        	      //:TraceLineS( "Parent Carrier: ", zqFrame.ParentEntity.EntityName )
        	      TraceLineS( "Parent Carrier: ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
        	   } 
        	   else
        	   { 
        	      TraceLineS( "Next Parent does NOT exist ", "(Incorrect)" );
                  Assert.assertTrue( "Next Parent does NOT exist", RESULT >= zCURSOR_SET );
        	   } 
        	   
        	   ResetViewFromSubobject( zqFrame );
        	   TraceLineS( "(ResetViewFromSubobject) ", " now on Driver" );
        	   TraceLineS( "(SET CURSOR NEXT after reset) ", "" );
        	   RESULT = SetCursorNextEntity( zqFrame, "ParentEntity", "" );
        	   if ( RESULT >= zCURSOR_SET )
        	   { 
        	      TraceLineS( "Next Parent DeliveryRoute Exists (Correct) ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
        	   } 
        	   else
        	   { 
        	      TraceLineS( "Next Parent does NOT exist ", "(Incorrect)" );
                  Assert.assertTrue( "Next Parent Existx", RESULT >= zCURSOR_SET );
        	   } 
        	   
        	   SetViewToSubobject( zqFrame, "ChildEntity" );
        	   TraceLineS( "(SetViewToSubobject) ", "" );
        	   //:IF zqFrame.ParentEntity EXISTS
        	   if ( CheckExistenceOfEntity( zqFrame, "ParentEntity" ) == 0 )
        	   { 
            	  TraceLineS( "Parent DeliveryLeg: ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
        	      TraceLineS( "ParentEntity Exists: ", "" );
        	   } 
        	   else
        	   { 
        	      TraceLineS( "No ParentEntity DeliveryLeg  ", "(Incorrect)" );
        	   } 
        	   SetViewToSubobject( zqFrame, "ChildEntity" );
        	   TraceLineS( "(SetViewToSubobject) now should be null", "" );
        	   if ( CheckExistenceOfEntity( zqFrame, "ParentEntity" ) == 0 )
        	   { 
         	      TraceLineS( "ParentEntity Exists  ", "(Incorrect)" );
        	   } 
        	   else
        	   { 
             	  TraceLineS( "No ParentEntity Should exist (Correct) ", "");
        	   } 

        	   ResetViewFromSubobject( zqFrame );
        	   TraceLineS( "(ResetViewFromSubobject) now on DeliveryLeg: ",  zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );

        	   ResetViewFromSubobject( zqFrame );
        	   TraceLineS( "(ResetViewFromSubobject) now on DeliveryRoute: ",  zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );

        	   ResetViewFromSubobject( zqFrame );
        	   TraceLineS( "(ResetViewFromSubobject) now on DriverShift: ",  zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );

 		   return 0;
        }
        
        private int 
        o_TestCodeRecurs1( View     vLOD,
                           View     zqFrame,
                           int      HierArchicalLevel )
        {
           String   szRecursiveEntityName = null;
           //:INTEGER NewHierArchicalLevel
           int      NewHierArchicalLevel = 0;
           //:INTEGER nRC
           int      nRC = 0;
           int      RESULT = 0;
           int      lTempInteger_0 = 0;
           String   szTempString_0 = null;
           String   szTempString_1 = null;
           String   szTempString_2 = null;

           //:// Create zqFrame.ParentEntity subobject under parent

           RESULT = CreateEntity( zqFrame, "ParentEntity", zPOS_AFTER );
           szRecursiveEntityName = vLOD.cursor("LOD_EntityParent").getAttribute("Name" ).getString();
           //:zqFrame.ParentEntity.EntityName = szRecursiveEntityName
           SetAttributeFromString( zqFrame, "ParentEntity", "EntityName", szRecursiveEntityName );
           TraceLineS( "... ", "" );
           //:TraceLineS( "Entity Created: ", zqFrame.ParentEntity.EntityName )
           TraceLineS( "Entity Created: ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
           TraceLineI( "Hier Level: ", HierArchicalLevel );
           TraceLineS( "... ", "" );

           //:// Create rest of recursive structure.
           //:// If the following IF statement is added so that we don't do a SetView.. / ResetView.. on a null LOD_EntityChild
           //:// entity, then the next zqFrame Reset crashes. (The vLOD reset is fine.)
           //://IF vLOD.LOD_EntityChild EXISTS
           SetViewToSubobject( vLOD, "LOD_EntityChild" );
           SetViewToSubobject( zqFrame, "ChildEntity" );
           TraceLineS( "(After SetViewToSubobject)", "" );
           NewHierArchicalLevel = HierArchicalLevel + 1;
           //:FOR EACH vLOD.LOD_EntityParent
           RESULT = SetCursorFirstEntity( vLOD, "LOD_EntityParent", "" );
           while ( RESULT > zCURSOR_UNCHANGED )
           { 
              nRC = o_TestCodeRecurs1( vLOD, zqFrame, NewHierArchicalLevel );
              RESULT = SetCursorNextEntity( vLOD, "LOD_EntityParent", "" );
           } 

           
           vLOD.resetSubobject();
           zqFrame.resetSubobject();

           TraceLineS( "(After ResetViewFromSubobject)", "" );
           

           //:TraceLineS( "Return vLOD Parent: ", vLOD.LOD_EntityParent.Name )
           TraceLineS( "Return vLOD Parent: ", vLOD.cursor("LOD_EntityParent").getAttribute("Name" ).getString() );
           //:TraceLineS( "Return zqFrame Parent: ", zqFrame.ParentEntity.EntityName )
           TraceLineS( "Return zqFrame Parent: ", zqFrame.cursor("ParentEntity").getAttribute("EntityName" ).getString() );
           //:IF vLOD.LOD_EntityParent.Name != zqFrame.ParentEntity.EntityName
           if ( CompareAttributeToAttribute( vLOD, "LOD_EntityParent", "Name", zqFrame, "ParentEntity", "EntityName" ) != 0 )
           { 
        	   // At this point vLOD has reset back to "DriverPerson" but zqFrame has set back ot the parent of "DriverPerson" which is "Driver"
        	   // Is this because zqFrame doesn't have any keys??
               TraceLineS( "(Error: No match on reset Parents)", "" );
               Assert.assertEquals( vLOD.cursor( "LOD_EntityParent" ).getAttribute( "Name" ).getString(),
		                            zqFrame.cursor( "ParentEntity" ).getAttribute( "EntityName" ).getString() );
           } 
           
           return( 0 );
        } 

     }
}
