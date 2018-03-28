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
	public void testCursorLinks()
	{
	    View         testview;
		testview = perygrene.activateEmptyObjectInstance( "mDrvShiftRoutes" );
		PerygreneVmlTester tester = new PerygreneVmlTester( testview );
		tester.testCursorLinks( testview );
        System.out.println("===== Finished testCursorLinks ========");
	}

	private class PerygreneVmlTester extends VmlObjectOperations
	{
		public PerygreneVmlTester( View view )
		{
			super( view );
		}
 
        public int
        testCursorLinks( View     ViewToWindow)
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

     }
}
