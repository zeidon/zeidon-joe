/**
 *
 */
package com.quinsoft.zeidon.test;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
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
public class TestCheetah2
{
	Task         cheetah;
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
        cheetah = oe.createTask( "Cheetah" );

		zeidonSystem = oe.getSystemTask();
	}

	@Test
	public void testOrderEntity()
	{
	    View         testview;
		testview = cheetah.activateEmptyObjectInstance( "mUser" );
		VmlTester tester = new VmlTester( testview );
		tester.testOrderEntity( testview );
        System.out.println("===== Finished testOrderEntity ========");
	}

	@Test
	public void testOrderEntity2()
	{
	    View         testview;
		testview = cheetah.activateEmptyObjectInstance( "mUser" );
		VmlTester tester = new VmlTester( testview );
		tester.testOrderEntity2( testview );
        System.out.println("===== Finished testOrderEntity2 ========");
	}

	private class VmlTester extends VmlObjectOperations
	{
		public VmlTester( View view )
		{
			super( view );
		}

        public int
        testOrderEntity2( View     ViewToWindow)
        {
		    zVIEW    mRasta = new zVIEW( );
		    zVIEW    mRastaFromFile = new zVIEW( );
		    zVIEW    vQualObject = new zVIEW( );
		    int RESULT = 0;
		    String szSort = "";

		    RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", ViewToWindow, zMULTIPLE );
		    CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
		    SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Referral" );
		    CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
		    SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SWEmployee" );
		    SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "EmployeeID" );
		    SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", 1202 );
		    SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
		    
        	RESULT = ActivateObjectInstance( mRasta, "mRasta", ViewToWindow, vQualObject, zACTIVATE_ROOTONLY_MULTIPLE );
        	DropView( vQualObject );
			SetNameForView( mRasta, "mRasta", null, zLEVEL_TASK );

			ActivateOI_FromFile( mRastaFromFile, "mRasta", ViewToWindow, "./src/test/resources/testdata/Cheetah/ois/lRastaRootOnly.por", zMULTIPLE );
			SetNameForView( mRastaFromFile, "mRastaFromFile", null, zLEVEL_TASK );
       	
        	
			szSort = "Referral.StatusDate D, Referral.ReferralDate D";
			OrderEntityForView( mRasta, "Referral", szSort );
			OrderEntityForView( mRastaFromFile, "Referral", szSort );
        	
        	DropView( mRasta );
        	DropView( mRastaFromFile );

			return 0;
       }
		public int
		testOrderEntity( View     ViewToWindow )
		{
		    zVIEW    lRastaRed45 = new zVIEW( );
		    zVIEW    mRasta = new zVIEW( );
		    zVIEW    wDateSrt = new zVIEW( );
		    String szSort = "";
		    int RESULT = 0;
		    int nPersonID = 0;
		    int count = 0;
		    
		    RESULT = ActivateEmptyObjectInstance( wDateSrt, "wDateSrt", ViewToWindow, zMULTIPLE );
			SetNameForView( wDateSrt, "wDateSrt", null, zLEVEL_TASK );
		    RESULT = ActivateEmptyObjectInstance( mRasta, "mRasta", ViewToWindow, zMULTIPLE );
			SetNameForView( mRasta, "mRasta", null, zLEVEL_TASK );
			ActivateOI_FromFile( lRastaRed45, "mRasta", ViewToWindow, "./src/test/resources/testdata/Cheetah/ois/lRastaRed45_2.por", zMULTIPLE );
			SetNameForView( lRastaRed45, "lRastaRed45", null, zLEVEL_TASK );
			   //:FOR EACH lRastaRed45.Referral 
			   RESULT = SetCursorFirstEntity( lRastaRed45, "Referral", "" );
			   while ( RESULT > zCURSOR_UNCHANGED )
			   { 
			      //:nPersonID = lRastaRed45.Person.PersonID
			      {MutableInt mi_nPersonID = new MutableInt( nPersonID );
			             GetIntegerFromAttribute( mi_nPersonID, lRastaRed45, "Person", "PersonID" );
			      nPersonID = mi_nPersonID.intValue( );}

			      if ( nPersonID == 468419 || nPersonID == 467889 || nPersonID == 467960 || 
			    	   nPersonID == 463967 || nPersonID == 466619 || nPersonID == 271939 )
			      { 
					 RESULT = IncludeSubobjectFromSubobject( mRasta, "Referral", lRastaRed45, "Referral", zPOS_AFTER );
                     
			         RESULT = CreateEntity( wDateSrt, "wDateSrt", zPOS_AFTER );
		             SetAttributeFromAttribute( wDateSrt, "wDateSrt", "Date1", lRastaRed45, "Referral", "ReferralDate" );
		             SetAttributeFromAttribute( wDateSrt, "wDateSrt", "Date2", lRastaRed45, "Referral", "StatusDate" );
   		  		     SetAttributeFromInteger( wDateSrt, "wDateSrt", "OrderNbr", 1 );
			      } 

			      //:IF nPersonID != 468419 AND 
			      //:   nPersonID != 467889 AND
			      //:   nPersonID != 467960 AND
			      //:   nPersonID != 463967 AND
			      //:   nPersonID != 466619 AND
			      //:   nPersonID != 271939 
			      /*
			      if ( nPersonID != 468419 && nPersonID != 467889 && nPersonID != 467960 && nPersonID != 463967 && nPersonID != 466619 && nPersonID != 271939 )
			      { 

			         //:DELETE ENTITY lRastaRed45.Referral   
			         RESULT = DeleteEntity( lRastaRed45, "Referral", zPOS_NONE );
			      } 
*/
			      RESULT = SetCursorNextEntity( lRastaRed45, "Referral", "" );
			      //:END 
			   } 
			   
			//	szSort = "Referral.StatusDate D, Person.LastName";
			szSort = "Referral.StatusDate D, Referral.ReferralDate D";
			OrderEntityForView( lRastaRed45, "Referral", szSort );
			OrderEntityForView( mRasta, "Referral", szSort );
			szSort = "wDateSrt.Date2 D, wDateSrt.Date1 D";
			OrderEntityForView( wDateSrt, "wDateSrt", szSort );
			
			return 0;
		}		
   }
}
