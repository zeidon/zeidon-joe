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
public class TestMMCRM
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
        zencas = oe.createTask( "mmcrm" );

		zeidonSystem = oe.getSystemTask();
	}

	@Test
	public void testTemporalSubobject()
	{
	    View         testview;
		testview = zencas.activateEmptyObjectInstance( "mOrganization" );
		TestMMCRMTester tester = new TestMMCRMTester( testview );
		tester.TEST_Error( testview );
        System.out.println("===== Finished TEST_Error ========");
	}

	private class TestMMCRMTester extends VmlObjectOperations
	{
		public TestMMCRMTester( View view )
		{
			super( view );
		}


		//:DIALOG OPERATION
		//:TEST_Error( VIEW ViewToWindow )
		//:   VIEW  mOrganization  BASED ON LOD mOrganization
		public int 
		TEST_Error( View     ViewToWindow )
		{
		   zVIEW    mOrganization = new zVIEW( );
		   //:VIEW  mOrganization2  BASED ON LOD mOrganization
		   zVIEW    mOrganization2 = new zVIEW( );
		   //:VIEW  mPayroll BASED ON LOD mPayroll
		   zVIEW    mPayroll = new zVIEW( );
		   //:VIEW  mPayroll2 BASED ON LOD mPayroll
		   zVIEW    mPayroll2 = new zVIEW( );
		   int      RESULT = 0;

		   // We don't really need to working sample...
		   // THIS SAMPLE IS WORKING
		   //:ACTIVATE mOrganization EMPTY
		   RESULT = ActivateEmptyObjectInstance( mOrganization, "mOrganization", ViewToWindow, zSINGLE );
		   RESULT = CreateEntity( mOrganization, "Organization", zPOS_AFTER );
		   RESULT = CreateEntity( mOrganization, "Service", zPOS_AFTER );
		   SetAttributeFromString( mOrganization, "Service", "ServiceName", "Test Item 1" );
		   RESULT = CreateEntity( mOrganization, "Service", zPOS_AFTER );
		   SetAttributeFromString( mOrganization, "Service", "ServiceName", "Test Item 2" );
		   RESULT = CreateEntity( mOrganization, "Service", zPOS_AFTER );
		   SetAttributeFromString( mOrganization, "Service", "ServiceName", "Test Item 3" );
		   SetNameForView( mOrganization, "mOrganization", null, zLEVEL_TASK );
		   //:  
		   //:ACTIVATE mPayroll EMPTY
		   RESULT = ActivateEmptyObjectInstance( mPayroll, "mPayroll", ViewToWindow, zSINGLE );
		   RESULT = CreateEntity( mPayroll, "Payroll", zPOS_AFTER );
		   SetNameForView( mPayroll, "mPayroll", null, zLEVEL_TASK );

		   RESULT = IncludeSubobjectFromSubobject( mPayroll, "Organization", mOrganization, "Organization", zPOS_AFTER );
		   

		   // THIS SAMPLE WITH TEMPORAL NOT WORKING
		   //:ACTIVATE mOrganization2 EMPTY
		   RESULT = ActivateEmptyObjectInstance( mOrganization2, "mOrganization", ViewToWindow, zSINGLE );
		   RESULT = CreateEntity( mOrganization2, "Organization", zPOS_AFTER );
		   RESULT = CreateEntity( mOrganization2, "Service", zPOS_AFTER );
		   SetAttributeFromString( mOrganization2, "Service", "ServiceName", "Test Item 1" );
		   RESULT = CreateEntity( mOrganization2, "Service", zPOS_AFTER );
		   SetAttributeFromString( mOrganization2, "Service", "ServiceName", "Test Item 2" );
		   RESULT = CreateEntity( mOrganization2, "Service", zPOS_AFTER );
		   SetAttributeFromString( mOrganization2, "Service", "ServiceName", "Test Item 3" );
		   SetNameForView( mOrganization2, "mOrganization2", null, zLEVEL_TASK );

		   CreateTemporalSubobjectVersion( mOrganization2, "Organization" );

		   //:ACTIVATE mPayroll2 EMPTY
		   RESULT = ActivateEmptyObjectInstance( mPayroll2, "mPayroll", ViewToWindow, zSINGLE );
		   RESULT = CreateEntity( mPayroll2, "Payroll", zPOS_AFTER );
		   SetNameForView( mPayroll2, "mPayroll2", null, zLEVEL_TASK );

		   RESULT = IncludeSubobjectFromSubobject( mPayroll2, "Organization", mOrganization2, "Organization", zPOS_AFTER );
		   return( 0 );
		} 

   }

}
