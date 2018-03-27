/**
 *
 */
package com.quinsoft.zeidon.test;

import org.junit.Assert;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
//import com.quinsoft.zeidon.test.TestSWAU.SwauVmlTester;
//import com.quinsoft.zeidon.test.TestPerygrene.VmlTester;
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
		perygreneVmlTester tester = new perygreneVmlTester( testview );
		tester.testActivateFile( testview );
        System.out.println("===== Finished testOrderEntity ========");
	}

	private class perygreneVmlTester extends VmlObjectOperations
	{
		public perygreneVmlTester( View view )
		{
			super( view );
		}

        public int
        testActivateFile( View ViewToWindow)
        {
        	zVIEW    mDrvShiftRoutes = new zVIEW( );
        	int      RESULT = 0;

        	 View tmpview = ViewToWindow.getTask().deserializeOi()
        	 .fromFile( "target/test-classes/testdata//perygrene/drvshiftbeforedeleting.por" )
        	 .setLodDef( "mDrvShiftRoutes" )
        	 //.setFlags(ActivateFlags.IGNORE_ENTITY_ERRORS)
        	 .activateFirst();
        	 mDrvShiftRoutes.setView(tmpview); 

        	 //:ActivateOI_FromFile( mDrvShiftRoutes, "mDrvShiftRoutes", ViewToWindow, "c:/temp/mDrvShiftRoutes.por", zSINGLE )
        	ActivateOI_FromFile( mDrvShiftRoutes, "mDrvShiftRoutes", ViewToWindow, "c:/temp/drvshiftbeforedeleting.por", zSINGLE );
			
        	return 0;
       }
   }
}
