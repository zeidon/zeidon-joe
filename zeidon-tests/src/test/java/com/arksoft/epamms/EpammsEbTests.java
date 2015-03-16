/**
    This file is part of the Zeidon Java Object Engine (Zeidon JOE).

    Zeidon JOE is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Zeidon JOE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2009-2015 QuinSoft
 */

package com.arksoft.epamms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.PessimisticLockingException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.LockingLevel;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.utils.JspWebUtils;
import com.quinsoft.zeidon.utils.QualificationBuilder;
import com.quinsoft.zeidon.vml.VmlDialog;

/**
 * @author DG
 *
 */
public class EpammsEbTests
{
   private Task         zeidonSystem;
   private Task         task;
   private ObjectEngine oe;

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

   @Before
   public void setUp() throws Exception
   {
      oe = JavaObjectEngine.getInstance();
      zeidonSystem = oe.getSystemTask( );
      zeidonSystem.log().debug( "Starting test" );
      task = oe.createTask( "epamms" );
   }

   @Test
   public void testPessimisticLocking()
   {

       View mMasProd = new QualificationBuilder( task )
                               .setLodDef( "mMasProd" )
                               .multipleRoots()
                               .withLocking( LockingLevel.PESSIMISTIC )
                               .activate();
       mMasProd.logObjectInstance();

       try
       {
           new QualificationBuilder( task )
                                   .setLodDef( "mMasProd" )
                                   .multipleRoots()
                                   .withLocking( LockingLevel.PESSIMISTIC )
                                   .activate();
       }
       catch ( PessimisticLockingException e )
       {
           task.log().debug( "Pessimistic lock worked.  Failed to load entity." );
       }

       new QualificationBuilder( task )
                       .setLodDef( "mMasProd" )
                       .multipleRoots()
                       .readOnly()
                       .activate();

       mMasProd.commit();  // This should drop pessimistic locks even though there are no changes.

       new QualificationBuilder( task )
                       .setLodDef( "mMasProd" )
                       .multipleRoots()
                       .withLocking( LockingLevel.PESSIMISTIC )
                       .activate();

       task.dropTask();
   }

   @Test
   public void testBasicStartup() throws IOException
   {
   // zVIEW    wWebXfer = new zVIEW( );
      int      RESULT;
   // int zSINGLE            = 0;
   // int zLEVEL_TASK        = 2;
   // int zPOS_AFTER         = 3;

      View vKZXMLPGO = JspWebUtils.createWebSession( null, task, "" );
      wStartUp_Dialog wStartUp = new wStartUp_Dialog( vKZXMLPGO );

      //:ACTIVATE wWebXfer EMPTY
   // RESULT = ActivateEmptyObjectInstance( wWebXfer, "wWebXfer", wStartUp, zSINGLE );
      View wWebXfer = vKZXMLPGO.activateEmptyObjectInstance( "wWebXfer", vKZXMLPGO.getApplication() );
      //:NAME VIEW wWebXfer "wWebXfer"
   // SetNameForView( wWebXfer, "wWebXfer", null, zLEVEL_TASK );
      wWebXfer.setName( "wWebXfer" );
      //:CREATE ENTITY wWebXfer.Root
   // RESULT = CreateEntity( wWebXfer, "Root", zPOS_AFTER );
      wWebXfer.cursor( "Root" ).createEntity( CursorPosition.NEXT );
   }

   private static class TextGrabber
   {
      public static String getClipboard()
      {
         // get the system clipboard
         Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

         // get the contents on the clipboard in a transferable object
         Transferable clipboardContents = systemClipboard.getContents(null);

         // dump out all available flavors
         DataFlavor[] flavors = clipboardContents.getTransferDataFlavors();
         for (int i = 0; i < flavors.length; i++)
         {
            System.out.println(flavors[i]);
         }

         try
         {
            // If the clipboard contains part of a Word-doc or an Excel-Sheet,
            // you'll get dozens of different flavors here, for example:
            // DataFlavor[mimetype=text/rtf;representationclass=java.io.InputStream]
            // DataFlavor[mimetype=text/rtf;representationclass=[B]
            // DataFlavor[mimetype=text/plain;representationclass=[B;charset=Cp1252]

            // get the contents in one of the flavors (RTF InputStream)
            DataFlavor flavor = new DataFlavor("text/plain;class=java.io.InputStream;charset=US-ASCII");
            try
            {
               InputStream cbis = (InputStream) clipboardContents.getTransferData(flavor);

               // Create the byte array to hold the data
               byte[] bytes = new byte[32000];

               // ... read the bytes from the stream...
               System.out.println(cbis.toString());
            }
            catch (UnsupportedFlavorException ex)
            {
               ex.printStackTrace();
            }
            catch (IOException ex)
            {
               ex.printStackTrace();
            }
         }
         catch (ClassNotFoundException ex)
         {
            ex.printStackTrace();
         }

         // check if clipboard is empty
         if (clipboardContents == null)
         {
            return ("Clipboard is empty!!!");
         }
         else
         {
            try
            {
               // see if DataFlavor of DataFlavor.stringFlavor is supported
               if (clipboardContents.isDataFlavorSupported(DataFlavor.stringFlavor))
               {
                  // return text content
                  String returnText = (String) clipboardContents.getTransferData(DataFlavor.stringFlavor);
                  return returnText;
               }
            }
            catch (UnsupportedFlavorException ufe)
            {
               ufe.printStackTrace();
            }
            catch (IOException ioe)
            {
               ioe.printStackTrace();
            }

            return null;
         }
      }
   }

   @Test
   public void testSanitizeClipboard() throws IOException
   {
      String grabbed = TextGrabber.getClipboard( );
      System.out.println( "Copying text from system clipboard 1." );
      System.out.println( grabbed );

      // The % in the following text causes problems in TaskLogger.java.
      task.log( ).debug( "TextGrabber: %s", grabbed );
      task.log( ).info( "TextGrabber: %s", grabbed );
      task.log( ).warn( "TextGrabber: %s", grabbed );
      task.log( ).trace( "TextGrabber: %s", grabbed );
      task.log( ).error( "TextGrabber: %s", grabbed );

      task.log( ).debug( "TextGrabber: %s" + grabbed );
      task.log( ).info( "TextGrabber: %s" + grabbed );
      task.log( ).warn( "TextGrabber: %s" + grabbed );
      task.log( ).trace( "TextGrabber: %s" + grabbed );
      task.log( ).error( "TextGrabber: %s" + grabbed );

      View vKZXMLPGO = JspWebUtils.createWebSession( null, task, "testUserId" );
      wMLC_Dialog wMLC = new wMLC_Dialog( vKZXMLPGO );

      String str = wMLC.SysGetClipboardText( );
      System.out.println( "Copying text from system clipboard 2." );
      System.out.println( str );

      int nLth1 = str.length();
      grabbed = wMLC.SanitizeHTML( str, "", "" );
      int nLth2 = grabbed.length();
      if ( nLth1 > 0 && str.equals( grabbed ) == false )
         assertNotSame( "testSanitizeClipboard failed", nLth1, nLth2 );
      else
         assertEquals( "testSanitizeClipboard zero length failed", nLth1, nLth2 );
   }

   @Test
   public void testMarketingLoop() throws IOException
   {
      View vKZXMLPGO = JspWebUtils.createWebSession( null, task, "testUserId" );

      View qualView = vKZXMLPGO.activateEmptyObjectInstance( "KZDBHQUA", vKZXMLPGO.getSystemTask().getApplication() );
      qualView.cursor( "EntitySpec" ).createEntity( CursorPosition.NEXT );
      qualView.cursor( "EntitySpec" ).setAttribute( "EntityName", "MasterProduct" );
      qualView.cursor( "QualAttrib" ).createEntity( CursorPosition.NEXT );
      qualView.cursor( "QualAttrib" ).setAttribute( "EntityName", "MasterProduct" );
      qualView.cursor( "QualAttrib" ).setAttribute( "AttributeName", "ID" );
      qualView.cursor( "QualAttrib" ).setAttribute( "Value", 1 );  // assuming ID = 1
      qualView.cursor( "QualAttrib" ).setAttribute( "Oper", "=" );

      wMLC_Dialog wMLC = new wMLC_Dialog( vKZXMLPGO );
      View wWebXfer = vKZXMLPGO.activateEmptyObjectInstance( "wWebXfer", task.getApplication() );
      wWebXfer.cursor( "Root" ).createEntity( );
      wWebXfer.setName( "wWebXfer" );

      // ZeidonAction: wMLCListMasterProducts.UpdateMasterProduct
      // ZeidonOperation: wMLC.UpdateMasterProduct called from wMLCListMasterProducts
      // ZeidonOperation: wMLC.InitMasterProductForUpdate called from wMLCUpdateMasterProduct
      // ZeidonAction: wMLCUpdateMasterProduct.UpdateMasterLabelContent
      // ZeidonOperation: wMLC.UpdateMasterLabelContent called from wMLCUpdateMasterProduct
      View mMasProd = vKZXMLPGO.activateObjectInstance( "mMasProd", qualView, ActivateFlags.SINGLE );
      //:NAME VIEW mMasProd "mMasProd"
   // mMasLC.displayObjectInstance();
      mMasProd.setName( "mMasProd" );
      qualView.drop( );

      // ZeidonOperation: wMLC.InitMasterLabelContentForUpdate called from wMLCVersionData
      // ZeidonAction: wMLCVersionData.smMarketing
      // ZeidonOperation: wMLC.EditMarketingSect called from wMLCVersionData
      // ZeidonOperation: wMLC.InitMarketingContent called from wMLCMarketing
      wMLC.InitMasterLabelContentForUpdate( vKZXMLPGO );
      wMLC.EditMarketingSect( vKZXMLPGO );
      wMLC.InitMarketingContent( vKZXMLPGO );

      // ZeidonAction: wMLCMarketing.UpdateMarketingSection
      // ZeidonOperation: wMLC.SelectMarketingSectForUpdate called from wMLCMarketing
      // ZeidonOperation: wMLC.InitMarketingSect called from wMLCMarketingSection
      // ZeidonAction: wMLCMarketingSection.UpdateMarketingStmt
      // ZeidonOperation: wMLC.SelectMarketingStmtForUpdate called from wMLCMarketingSection
      // ZeidonOperation: wMLC.InitMarketingStmtForUpdate called from wMLCMarketingStatement
      wMLC.SelectMarketingSectForUpdate( vKZXMLPGO );
      wMLC.InitMarketingSect( vKZXMLPGO );
      wMLC.SelectMarketingStmtForUpdate( vKZXMLPGO );
      wMLC.InitMarketingStmtForUpdate( vKZXMLPGO );

      View mMasLC = vKZXMLPGO.getViewByName( "mMasLC" );
      EntityCursor srcCursor = mMasLC.cursor( "M_MarketingStatement" );
      int nRC = srcCursor.setFirst().toInt();
      String str = srcCursor.getStringFromAttribute( "Text" );
      if ( str.endsWith( "Updated" ) )
         str = str.replaceAll( "Updated", "" );
      else
         str += "Updated";

      srcCursor.setAttribute( "Text", str );
      srcCursor.logEntity( false );
      str = srcCursor.getStringFromAttribute( "Text" );

      // ZeidonAction: wMLCMarketingStatement.smAcceptMarketingStmt
      // ZeidonOperation: wMLC.AcceptMarketingStmt called from wMLCMarketingStatement

      wMLC.AcceptMarketingStmt( vKZXMLPGO );
      srcCursor.logEntity( false );

      // ZeidonOperation: wMLC.InitMarketingSect called from wMLCMarketingSection
      // ZeidonAction: wMLCMarketingSection.smAcceptMarketingSection
      // ZeidonOperation: wMLC.AcceptMarketingSect called from wMLCMarketingSection
      wMLC.InitMarketingSect( vKZXMLPGO );
      wMLC.AcceptMarketingSect( vKZXMLPGO );
      assertEquals( "testMarketingLoop failed", nRC, 0 );
   }


   @Test
   public void testAddDirectionsForUse() throws IOException
   {
      View vKZXMLPGO = JspWebUtils.createWebSession( null, task, "testUserId" );

      // ZeidonOperation: wMLC.UpdateMasterProduct called from wMLCListMasterProducts
      // ZeidonOperation: wMLC.InitMasterProductForUpdate called from wMLCUpdateMasterProduct
      View qualView = vKZXMLPGO.activateEmptyObjectInstance( "KZDBHQUA", vKZXMLPGO.getSystemTask().getApplication() );
      qualView.cursor( "EntitySpec" ).createEntity( CursorPosition.NEXT );
      qualView.cursor( "EntitySpec" ).setAttribute( "EntityName", "MasterProduct" );
      qualView.cursor( "QualAttrib" ).createEntity( CursorPosition.NEXT );
      qualView.cursor( "QualAttrib" ).setAttribute( "EntityName", "MasterProduct" );
      qualView.cursor( "QualAttrib" ).setAttribute( "AttributeName", "ID" );
      qualView.cursor( "QualAttrib" ).setAttribute( "Value", 4 );  // assuming ID = 4
      qualView.cursor( "QualAttrib" ).setAttribute( "Oper", "=" );

      View wWebXfer = vKZXMLPGO.activateEmptyObjectInstance( "wWebXfer", task.getApplication() );
      wWebXfer.cursor( "Root" ).createEntity( );
      wWebXfer.setName( "wWebXfer" );

      // ZeidonAction: wMLCUpdateMasterProduct.UpdateMasterLabelContent
      // ZeidonOperation: wMLC.UpdateMasterLabelContent called from wMLCUpdateMasterProduct
      View mMasProd = vKZXMLPGO.activateObjectInstance( "mMasProd", qualView, ActivateFlags.SINGLE );
      //:NAME VIEW mMasProd "mMasProd"
   // mMasLC.displayObjectInstance();
      mMasProd.setName( "mMasProd" );
      qualView.drop( );
   // mMasProd.writeOiToFile( "c:\\temp\\mMasProd.OI", View.CONTROL_INCREMENTAL );

      wMLC_Dialog wMLC = new wMLC_Dialog( vKZXMLPGO );
      wMLC.InitMasterLabelContentForUpdate( vKZXMLPGO );
      wMLC.EditDirectionsUseSect( vKZXMLPGO );
      wMLC.InitDirectionsUseContent( vKZXMLPGO );
      wMLC.AddNewDirectionsUseSect( vKZXMLPGO );
      wMLC.InitDirectionsUseSect( vKZXMLPGO );
      wMLC.AcceptDirectionsUseSect( vKZXMLPGO );
   }

   @Test
   public void testFop() throws IOException
   {
      View vKZXMLPGO = JspWebUtils.createWebSession( null, task, "testUserId" );

      /* String strHTML_FileName = VmlOperation.SysMakeWebFileName( task, mSPLDef.getApplication( ), 2 ) + ".html"; */
      String strPDF_FileName = "c:\\temp\\dks.pdf";
      String strXML_FileName = "c:\\temp\\dks.xml";
      String strXSLT_FileName = "c:\\temp\\dks.xslt";
      VmlDialog.TransformXslXmlToPDF( task, strXSLT_FileName, strXML_FileName, strPDF_FileName );
      task.log( ).debug( "HTML FileName: %s", strPDF_FileName );
   }

/*
   @Test
   public void testCancelSubobject() throws IOException
   {
      View vKZXMLPGO = JspWebUtils.createWebSession( null, task, "testUserId" );

      View qualView = vKZXMLPGO.activateEmptyObjectInstance( "KZDBHQUA", vKZXMLPGO.getSystemTask().getApplication() );
      qualView.cursor( "EntitySpec" ).createEntity( CursorPosition.NEXT );
      qualView.cursor( "EntitySpec" ).setAttribute( "EntityName", "MasterProduct" );
      qualView.cursor( "QualAttrib" ).createEntity( CursorPosition.NEXT );
      qualView.cursor( "QualAttrib" ).setAttribute( "EntityName", "MasterProduct" );
      qualView.cursor( "QualAttrib" ).setAttribute( "AttributeName", "ID" );
      qualView.cursor( "QualAttrib" ).setAttribute( "Value", 1 );  // assuming ID = 1
      qualView.cursor( "QualAttrib" ).setAttribute( "Oper", "=" );

      wMLC_Dialog wMLC = new wMLC_Dialog( vKZXMLPGO );
      View wWebXfer = vKZXMLPGO.activateEmptyObjectInstance( "wWebXfer", task.getApplication() );
      wWebXfer.cursor( "Root" ).createEntity( );
      wWebXfer.setName( "wWebXfer" );

      View mMasProd = vKZXMLPGO.activateObjectInstance( "mMasProd", qualView, ActivateFlags.SINGLE );
      //:NAME VIEW mMasProd "mMasProd"
   // mMasLC.displayObjectInstance();
      mMasProd.setName( "mMasProd" );
      qualView.drop( );

      wMLC.InitMasterLabelContentForUpdate( vKZXMLPGO );
      wMLC.EditMarketingSect( vKZXMLPGO );
      wMLC.AddNewMarketingSect( vKZXMLPGO );
      wMLC.InitMarketingSect( vKZXMLPGO );
      wMLC.CancelMarketingSect( vKZXMLPGO );
   }

   @Test
   public void testAcceptSubobject() throws IOException
   {
      View vKZXMLPGO = JspWebUtils.createWebSession( null, task, "testUserId" );

      View qualView = vKZXMLPGO.activateEmptyObjectInstance( "KZDBHQUA", vKZXMLPGO.getSystemTask().getApplication() );
      qualView.cursor( "EntitySpec" ).createEntity( CursorPosition.NEXT );
      qualView.cursor( "EntitySpec" ).setAttribute( "EntityName", "MasterProduct" );
      qualView.cursor( "QualAttrib" ).createEntity( CursorPosition.NEXT );
      qualView.cursor( "QualAttrib" ).setAttribute( "EntityName", "MasterProduct" );
      qualView.cursor( "QualAttrib" ).setAttribute( "AttributeName", "ID" );
      qualView.cursor( "QualAttrib" ).setAttribute( "Value", 1 );  // assuming ID = 1
      qualView.cursor( "QualAttrib" ).setAttribute( "Oper", "=" );

      wMLC_Dialog wMLC = new wMLC_Dialog( vKZXMLPGO );
      View wWebXfer = vKZXMLPGO.activateEmptyObjectInstance( "wWebXfer", task.getApplication() );
      wWebXfer.cursor( "Root" ).createEntity( );
      wWebXfer.setName( "wWebXfer" );

      View mMasProd = vKZXMLPGO.activateObjectInstance( "mMasProd", qualView, ActivateFlags.SINGLE );
      //:NAME VIEW mMasProd "mMasProd"
   // mMasLC.displayObjectInstance();
      mMasProd.setName( "mMasProd" );
      qualView.drop( );

      wMLC.InitMasterLabelContentForUpdate( vKZXMLPGO );
      wMLC.EditMarketingSect( vKZXMLPGO );
      wMLC.InitMarketingContent( vKZXMLPGO );
      wMLC.SelectMarketingSectForUpdate( vKZXMLPGO );
      wMLC.InitMarketingSect( vKZXMLPGO );
      View mMasLC = vKZXMLPGO.getViewByName( "mMasLC" );
      mMasLC.cursor( "M_MarketingSection" ).setAttribute( "Title", "Title", "" );
      mMasLC.cursor( "M_MarketingSection" ).setAttribute( "Subtitle", "Subtitle", "" );
      mMasLC.cursor( "M_MarketingSection" ).setAttribute( "ReviewerNote", "Reviewer Note", "" );
      wMLC.AcceptMarketingSect( vKZXMLPGO );
   }

   @Test
   public void testAutoSeq() throws IOException
   {
      View vKZXMLPGO = JspWebUtils.createWebSession( null, task, "testUserId" );

      View qualView = vKZXMLPGO.activateEmptyObjectInstance( "KZDBHQUA", vKZXMLPGO.getSystemTask().getApplication() );
      qualView.cursor( "EntitySpec" ).createEntity( CursorPosition.NEXT );
      qualView.cursor( "EntitySpec" ).setAttribute( "EntityName", "MasterProduct" );
      qualView.cursor( "QualAttrib" ).createEntity( CursorPosition.NEXT );
      qualView.cursor( "QualAttrib" ).setAttribute( "EntityName", "MasterProduct" );
      qualView.cursor( "QualAttrib" ).setAttribute( "AttributeName", "ID" );
      qualView.cursor( "QualAttrib" ).setAttribute( "Value", 1 );  // assuming ID = 1
      qualView.cursor( "QualAttrib" ).setAttribute( "Oper", "=" );

      wMLC_Dialog wMLC = new wMLC_Dialog( vKZXMLPGO );
      View wWebXfer = vKZXMLPGO.activateEmptyObjectInstance( "wWebXfer", task.getApplication() );
      wWebXfer.cursor( "Root" ).createEntity( );
      wWebXfer.setName( "wWebXfer" );

      View mMasProd = vKZXMLPGO.activateObjectInstance( "mMasProd", qualView, ActivateFlags.SINGLE );
      //:NAME VIEW mMasProd "mMasProd"
   // mMasLC.displayObjectInstance();
      mMasProd.setName( "mMasProd" );
      qualView.drop( );

      wMLC.InitMasterLabelContentForUpdate( vKZXMLPGO );
      wMLC.EditFirstAidSect( vKZXMLPGO );
      wMLC.InitFirstAidSect( vKZXMLPGO );
      View mMasLC = vKZXMLPGO.getViewByName( "mMasLC" );

      wMLC.SelectMarketingSectForUpdate( vKZXMLPGO );
      wMLC.InitMarketingSect( vKZXMLPGO );

      View mTempLC = mMasLC.newView( );
      mTempLC.setName( "mTempLC" );

      EntityCursor srcCursor = mMasLC.cursor( "M_GeneralStatement" );
      int nRC = srcCursor.setLast().toInt();;
      EntityCursor tgtCursor = mTempLC.cursor( "M_GeneralStatement" );
      nRC = tgtCursor.setLast().toInt();;
      nRC = tgtCursor.setPrevContinue( );
      srcCursor.displayEntity( false );
      tgtCursor.displayEntity( false );
      nRC = tgtCursor.moveSubobject( CursorPosition.PREV, srcCursor, CursorPosition.PREV );
      mTempLC.drop( );
      nRC = srcCursor.setLast().toInt();;
      int nID1 = srcCursor.getIntegerFromAttribute( "ID" );
      srcCursor.displayEntity( false );

      wMLC.AcceptMarketingSect( vKZXMLPGO );
      mMasLC.commit( );

      wMLC.InitMasterLabelContentForUpdate( vKZXMLPGO );
      wMLC.EditFirstAidSect( vKZXMLPGO );
      wMLC.InitFirstAidSect( vKZXMLPGO );
      mMasLC = vKZXMLPGO.getViewByName( "mMasLC" );

      srcCursor = mMasLC.cursor( "M_GeneralStatement" );
      nRC = srcCursor.setLast().toInt();;
      int nID2 = srcCursor.getIntegerFromAttribute( "ID" );
      srcCursor.displayEntity( false );
      assertEquals( "testAutoSeq failed", nID1, nID2 );
   }

   @Test
   public void testBug1() throws IOException
   {
      View view = task.activateOiFromFile( "mMasLC", "./testdata/ePamms/OIs/TestInsertBug1.por", null );

      view.displayObjectInstance();
      EntityCursor cursor = view.cursor( "M_StorageDisposalStatement" );
      cursor.createTemporalSubobjectVersion();
      cursor.setAttribute( "Title", "this is a another test" );
      cursor.acceptSubobject();
      view.commit();
//    view.displayObjectInstance();
   }

   private EntityDef findEntityDef( EntityDef parentEntityDef, String entityName )
   {
      EntityDef ve;

      if ( parentEntityDef.getName().equals( entityName ) )
         return parentEntityDef;

      for ( EntityDef childEntityDef : parentEntityDef.getChildren() )
      {
         if ( childEntityDef.getName( ).equals( entityName ) )
            return childEntityDef;

         ve = findEntityDef( childEntityDef, entityName );
         if ( ve != null )
            return ve;
      }

      return null;
   }

// @Test // this is a destructive test, so only run it when necessary to see if deletes are working properly
   public void testXOD() throws IOException
   {
      View vKZXMLPGO = JspWebUtils.createWebSession( null, task, "testUserId" );

      View qualView = vKZXMLPGO.activateEmptyObjectInstance( "KZDBHQUA", vKZXMLPGO.getSystemTask().getApplication() );
      qualView.cursor( "EntitySpec" ).createEntity( CursorPosition.NEXT );
      qualView.cursor( "EntitySpec" ).setAttribute( "EntityName", "MasterLabelContent" );
      qualView.cursor( "QualAttrib" ).createEntity( CursorPosition.NEXT );
      qualView.cursor( "QualAttrib" ).setAttribute( "EntityName", "MasterLabelContent" );
      qualView.cursor( "QualAttrib" ).setAttribute( "AttributeName", "ID" );
      qualView.cursor( "QualAttrib" ).setAttribute( "Value", 1 );  // assuming ID = 1
      qualView.cursor( "QualAttrib" ).setAttribute( "Oper", "=" );

      View mMasLC = vKZXMLPGO.activateEmptyObjectInstance( "mMasLC", task.getApplication() );
      mMasLC.getLodDef().displayLodDef( task );
      EntityDef entityDef = findEntityDef( mMasLC.getLodDef().getRoot(), "M_Usage" );
      if ( entityDef != null )
      {
         DataRecord dataRecord = entityDef.getDataRecord();
         if ( dataRecord != null )
         {
            task.log().info( "    DataRecord: %s type=%s  count %d", dataRecord.getRecordName(), dataRecord.getType(), dataRecord.dataFields().size() );
            for ( DataField df : dataRecord.dataFields( ) )
            {
               task.log().info( "Field: " + df.getName() );
               assertNotSame( "testXOD with M_Usage.TYPE failed", df.getName(), "TYPE" );
            }
         }
      }

      //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = mMasProd.MasterLabelContent.ID
      // ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      mMasLC = vKZXMLPGO.activateObjectInstance( "mMasLC", qualView, ActivateFlags.SINGLE );
      //:NAME VIEW mMasLC "mMasLC"
   // mMasLC.displayObjectInstance();
      mMasLC.setName( "mMasLC" );
      int nCnt = 0;
      int nRC = mMasLC.cursor( "M_Usage" ).setFirst();
      EntityInstance ei = mMasLC.cursor( "M_Usage" ).getEntityInstance();
      while ( nRC == CursorResult.SET )
      {
         nCnt++;
         mMasLC.cursor( "M_Usage" ).deleteEntity( );
         nRC = mMasLC.cursor( "M_Usage" ).setFirst().toInt();
      }

      task.log().info( "M_Usage entities deleted: " + nCnt );
      assertNotSame( "testXOD with M_Usage .. no entities to delete ... get a new MySQL database", nCnt, 0 );
      mMasLC.commit( );

      mMasLC = vKZXMLPGO.activateObjectInstance( "mMasLC", qualView, ActivateFlags.SINGLE );
      nRC = mMasLC.cursor( "M_Usage" ).setFirst();

      qualView.drop( );
      assertNotSame( "testXOD with M_Usage delete failed", nRC, CursorResult.SET );
   }
*/
}