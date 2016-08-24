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
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.vml.VmlObjectOperations;

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
