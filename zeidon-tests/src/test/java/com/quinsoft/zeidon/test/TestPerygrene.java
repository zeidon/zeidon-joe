/**
 *
 */
package com.quinsoft.zeidon.test;

import org.junit.Assert;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
//import com.quinsoft.zeidon.test.TestSWAU.SwauVmlTester;
//import com.quinsoft.zeidon.test.TestCheetah2.VmlTester;
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
	public void testActivateFile()
	{
	    View         testview;
		testview = perygrene.activateEmptyObjectInstance( "mDrvShiftRoutes" );
		PerygreneVmlTester tester = new PerygreneVmlTester( testview );
		tester.testActivateFile( testview );
        System.out.println("===== Finished testActivateFile ========");
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

	private class PerygreneVmlTester extends VmlObjectOperations
	{
		public PerygreneVmlTester( View view )
		{
			super( view );
		}

        public int
        testActivateFile( View     ViewToWindow)
        {
        	   zVIEW    mDrvShiftRoutes = new zVIEW( );
        	   int      RESULT = 0;

        	 View tmpview = ViewToWindow.getTask().deserializeOi()
        	 .fromFile( "target/test-classes/testdata//Perygrene/drvshiftbeforedeleting.por" )
        	 .setLodDef( "mDrvShiftRoutes" )
        	 //.setFlags(ActivateFlags.IGNORE_ENTITY_ERRORS)
        	 .activateFirst();
        	 mDrvShiftRoutes.setView(tmpview); 


        	   return 0;
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
                	 .fromFile( "target/test-classes/testdata//Perygrene/drvshiftbeforedeleting.por" )
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

    	   ActivateOI_FromFile( mDrvShiftRoutes, "mDrvShiftRoutes", ViewToWindow, "target/test-classes/testdata//Perygrene/mDrvShiftRoutes.por", zSINGLE );
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

    	   mDrvShiftRoutes.serializeOi().toFile( "target/test-classes/testdata//Perygrene/test2.por" );
    	   ActivateOI_FromFile( mDrvShiftRoutes2, "mDrvShiftRoutes", ViewToWindow, "target/test-classes/testdata//Perygrene/test2.por", zSINGLE );
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

        	   ActivateOI_FromFile( mDrvShiftRoutes, "mDrvShiftRoutes", ViewToWindow, "target/test-classes/testdata//Perygrene/mDrvShiftRoutes.por", zSINGLE );
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
        	   //mDrvShiftRoutes.serializeOi().withIncremental().toFile( "target/test-classes/testdata//Perygrene/test3.por" );
        	   mDrvShiftRoutes.serializeOi().toFile( "target/test-classes/testdata//Perygrene/test3.por" );
        	   ActivateOI_FromFile( mDrvShiftRoutes2, "mDrvShiftRoutes", ViewToWindow, "target/test-classes/testdata//Perygrene/test3.por", zSINGLE );
        	   SetNameForView( mDrvShiftRoutes2, "RoutesTEST2", null, zLEVEL_TASK );
        	   
        	   mDrvShiftRoutes2.cursor("DeliveryRoute").setFirst();
    		   RESULT = CheckExistenceOfEntity( mDrvShiftRoutes2, "DeliveryLeg");
     		   Assert.assertEquals("DeliveryLeg should exist but does not.", CursorResult.SET.toInt(), RESULT );
        	   

        	   return 0;
       }

     }
}
