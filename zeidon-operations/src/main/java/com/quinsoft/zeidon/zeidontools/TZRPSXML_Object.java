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

    Copyright 2009-2010 QuinSoft
**/

package com.quinsoft.zeidon.zeidontools;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.quinsoft.zeidon.zeidonoperations.KZOEP1AA;

/**
   @author QuinSoft
**/

public class TZRPSXML_Object extends VmlObjectOperations
{
   private final KZOEP1AA m_KZOEP1AA;
   public TZRPSXML_Object( View view )
   {
      super( view );
      m_KZOEP1AA = new KZOEP1AA( view );
   }


//:TRANSFORMATION OPERATION
//:GenerateXSLT_XML( VIEW vSourceOI,
//:                  STRING ( 32 )  szReportName,
//:                  STRING ( 500 ) szXMLFileName,
//:                  STRING ( 32 )  szTopEntityName )

//:   VIEW vReportDef     BASED ON LOD TZRPSRCO
public int 
oTZRPSRCO_GenerateXSLT_XML( View     vSourceOI,
                            String   szReportName,
                            String   szXMLFileName,
                            String   szTopEntityName )
{
   zVIEW    vReportDef = new zVIEW( );
   //:VIEW vReportLODRecurs BASED ON LOD TZRPSRCO
   zVIEW    vReportLODRecurs = new zVIEW( );
   //:VIEW vReportDefRoot BASED ON LOD TZRPSRCO
   zVIEW    vReportDefRoot = new zVIEW( );
   //:VIEW vSourceOIHier
   zVIEW    vSourceOIHier = new zVIEW( );
   //:VIEW vSourceOIDebug
   zVIEW    vSourceOIDebug = new zVIEW( );
   //:VIEW vLOD           BASED ON LOD TZZOLODO
   zVIEW    vLOD = new zVIEW( );
   //:VIEW vTaskLPLR  BASED ON LOD TZCMLPLO
   zVIEW    vTaskLPLR = new zVIEW( );
   //:STRING ( 200 )  szMsg
   String   szMsg = null;
   //:STRING ( 200 )  szFileName
   String   szFileName = null;
   //:STRING ( 32 )   szReturnedEntityName
   String   szReturnedEntityName = null;
   //:STRING ( 32 )   szLastReturnedEntityName
   String   szLastReturnedEntityName = null;
   //:STRING ( 32 )   szParentEntityName
   String   szParentEntityName = null;
   //:STRING ( 32 )   szDrivingViewName
   String   szDrivingViewName = null;
   //:STRING ( 50 )   szIndentationValue
   String   szIndentationValue = null;
   //:STRING ( 50 )   szIndentationSubValue
   String   szIndentationSubValue = null;
   //:STRING ( 1 )    szInNodeFlag
   String   szInNodeFlag = null;
   //:STRING ( 5000 ) szOutputLine
   String   szOutputLine = null;
   //:INTEGER         lFileHandle
   int      lFileHandle = 0;
   //:INTEGER         Indentation
   int      Indentation = 0;
   //:SHORT           ReturnedHierLevel
   int      ReturnedHierLevel = 0;
   //:SHORT           LastHierLevel
   int      LastHierLevel = 0;
   //:SHORT           nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;


   //:// Build an XSLT XML FO object for the passed in application object and Report Definition.

   //:// First we need to activate the report def...
   //:SysReadZeidonIni( -1, "[WorkStation]", "ResourcePath", szFileName )
   {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
   m_KZOEP1AA.SysReadZeidonIni( -1, "[WorkStation]", "ResourcePath", sb_szFileName );
   szFileName = sb_szFileName.toString( );}
   //://szFileName = "c:\lplr\zencas\bin\"
   //:szFileName = szFileName + szReportName + ".xrp"
    {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
   ZeidonStringConcat( sb_szFileName, 1, 0, szReportName, 1, 0, 201 );
   szFileName = sb_szFileName.toString( );}
    {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
   ZeidonStringConcat( sb_szFileName, 1, 0, ".xrp", 1, 0, 201 );
   szFileName = sb_szFileName.toString( );}
   //:SfActivateSysOI_FromFile( vReportDef, "TZRPSRCO", vSourceOI, szFileName, zSINGLE )
   SfActivateSysOI_FromFile( vReportDef, "TZRPSRCO", vSourceOI, szFileName, zSINGLE );

   //:// Get the Name of the Driving object, as we will need that in processing mapping.
   //:IF vReportDef.DrivingViewObjRef EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( vReportDef, "DrivingViewObjRef" );
   if ( lTempInteger_0 == 0 )
   { 
      //:szDrivingViewName = vReportDef.DrivingViewObjRef.Name
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szDrivingViewName;
      if ( szDrivingViewName == null )
         sb_szDrivingViewName = new StringBuilder( 32 );
      else
         sb_szDrivingViewName = new StringBuilder( szDrivingViewName );
      GetVariableFromAttribute( sb_szDrivingViewName, mi_lTempInteger_1, 'S', 33, vReportDef, "DrivingViewObjRef", "Name", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szDrivingViewName = sb_szDrivingViewName.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szMsg = "The report must have a 'Driving View' set in the report details."
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
      ZeidonStringCopy( sb_szMsg, 1, 0, "The report must have a 'Driving View' set in the report details.", 1, 0, 201 );
      szMsg = sb_szMsg.toString( );}
      //:MessageSend( vReportDef, "", "Generate XSLT",
      //:             szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
      MessageSend( vReportDef, "", "Generate XSLT", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Open XML output file.
   //:SysReadZeidonIni( -1, "[Workstation]", "XSLTDirectory", szFileName )
   {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
   m_KZOEP1AA.SysReadZeidonIni( -1, "[Workstation]", "XSLTDirectory", sb_szFileName );
   szFileName = sb_szFileName.toString( );}
   //:szFileName = szFileName + szXMLFileName + ".xml"
    {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
   ZeidonStringConcat( sb_szFileName, 1, 0, szXMLFileName, 1, 0, 201 );
   szFileName = sb_szFileName.toString( );}
    {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
   ZeidonStringConcat( sb_szFileName, 1, 0, ".xml", 1, 0, 201 );
   szFileName = sb_szFileName.toString( );}
   //:lFileHandle = SysOpenFile( vReportDef, szFileName, COREFILE_WRITE )
   try
   {
       lFileHandle = m_KZOEP1AA.SysOpenFile( vReportDef, szFileName, COREFILE_WRITE );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:IF lFileHandle < 0
   if ( lFileHandle < 0 )
   { 
      //:szMsg = "Cannot open XSLT Output File, " + szFileName
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
      ZeidonStringCopy( sb_szMsg, 1, 0, "Cannot open XSLT Output File, ", 1, 0, 201 );
      szMsg = sb_szMsg.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
      ZeidonStringConcat( sb_szMsg, 1, 0, szFileName, 1, 0, 201 );
      szMsg = sb_szMsg.toString( );}
      //:MessageSend( vReportDef, "", "Generate XSLT",
      //:             szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
      MessageSend( vReportDef, "", "Generate XSLT", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Navigate the vSourceOI object/subobject hierarchically and create an XML node for each entity that has a corresponding
   //:// GroupSet entity in the Report Def.

   //:// Generate basic header.
   //:szOutputLine = "<?xml version=" + QUOTES + "1.0" + QUOTES + " encoding=" + QUOTES + "iso-8859-1" + QUOTES + "?>"
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringCopy( sb_szOutputLine, 1, 0, "<?xml version=", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringConcat( sb_szOutputLine, 1, 0, QUOTES, 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringConcat( sb_szOutputLine, 1, 0, "1.0", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringConcat( sb_szOutputLine, 1, 0, QUOTES, 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringConcat( sb_szOutputLine, 1, 0, " encoding=", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringConcat( sb_szOutputLine, 1, 0, QUOTES, 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringConcat( sb_szOutputLine, 1, 0, "iso-8859-1", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringConcat( sb_szOutputLine, 1, 0, QUOTES, 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringConcat( sb_szOutputLine, 1, 0, "?>", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
   oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );
   //:szOutputLine = "<zOI>"
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringCopy( sb_szOutputLine, 1, 0, "<zOI>", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
   oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );

   //:// Process the page header. 
   //:SET CURSOR FIRST vReportDef.GroupSet WHERE vReportDef.GroupSet.Type = "PH"
   RESULT = SetCursorFirstEntityByString( vReportDef, "GroupSet", "Type", "PH", "" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:szOutputLine = "<PageHeader>"
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringCopy( sb_szOutputLine, 1, 0, "<PageHeader>", 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
      //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
      oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );
      //:XML_PageHeaderFooter( vReportDef, vSourceOI, szOutputLine, lFileHandle, szIndentationValue )
      oTZRPSRCO_XML_PageHeaderFooter( vReportDef, vSourceOI, szOutputLine, lFileHandle, szIndentationValue );
      //:szOutputLine = "</PageHeader>"
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringCopy( sb_szOutputLine, 1, 0, "</PageHeader>", 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
      //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
      oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );
   } 

   //:END

   //:szOutputLine = "   <GR_" + szTopEntityName + ">"
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringCopy( sb_szOutputLine, 1, 0, "   <GR_", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringConcat( sb_szOutputLine, 1, 0, szTopEntityName, 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringConcat( sb_szOutputLine, 1, 0, ">", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
   oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );

   //:// Process Top Entity Header, if it exists.
   //:CreateViewFromView( vReportLODRecurs, vReportDef )
   CreateViewFromView( vReportLODRecurs, vReportDef );

   //:GenerateXML_LODRecurs( vReportDef, vReportLODRecurs, vSourceOI, szOutputLine, lFileHandle, szIndentationValue )
   oTZRPSRCO_GenerateXML_LODRecurs( vReportDef, vReportLODRecurs, vSourceOI, szOutputLine, lFileHandle, szIndentationValue );


   //:szOutputLine = "   </GR_" + szTopEntityName + ">"
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringCopy( sb_szOutputLine, 1, 0, "   </GR_", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringConcat( sb_szOutputLine, 1, 0, szTopEntityName, 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringConcat( sb_szOutputLine, 1, 0, ">", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
   oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );

   //:// Process the page footer. 
   //:SET CURSOR FIRST vReportDef.GroupSet WHERE vReportDef.GroupSet.Type = "PF"
   RESULT = SetCursorFirstEntityByString( vReportDef, "GroupSet", "Type", "PF", "" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:szOutputLine = "<PageFooter>"
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringCopy( sb_szOutputLine, 1, 0, "<PageFooter>", 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
      //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
      oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );
      //:XML_PageHeaderFooter( vReportDef, vSourceOI, szOutputLine, lFileHandle, szIndentationValue )
      oTZRPSRCO_XML_PageHeaderFooter( vReportDef, vSourceOI, szOutputLine, lFileHandle, szIndentationValue );
      //:szOutputLine = "</PageFooter>"
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringCopy( sb_szOutputLine, 1, 0, "</PageFooter>", 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
      //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
      oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );
   } 

   //:END

   //:// Terminate and close the XML file.
   //:szOutputLine = "</zOI>"
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
   ZeidonStringCopy( sb_szOutputLine, 1, 0, "</zOI>", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
   oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );
   //:SysCloseFile( vReportDef, lFileHandle, 0 )
   try
   {
       m_KZOEP1AA.SysCloseFile( vReportDef, lFileHandle, null );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:XML_PageHeaderFooter( VIEW vReportDef BASED ON LOD TZRPSRCO,
//:                      VIEW vSourceOI,
//:                      STRING ( 5000 ) szOutputLine,
//:                      INTEGER         lFileHandle,
//:                      STRING ( 50 )   szIndentationValue )

//:   STRING ( 32 ) szEntityName
private int 
oTZRPSRCO_XML_PageHeaderFooter( View     vReportDef,
                                View     vSourceOI,
                                String   szOutputLine,
                                int      lFileHandle,
                                String   szIndentationValue )
{
   String   szEntityName = null;
   //:STRING ( 50 ) szIndentationSubValue
   String   szIndentationSubValue = null;
   int      lTempInteger_0 = 0;

   //:IF vReportDef.Control EXISTS 
   lTempInteger_0 = CheckExistenceOfEntity( vReportDef, "Control" );
   if ( lTempInteger_0 == 0 )
   { 
      //:szIndentationSubValue = szIndentationValue + "      "
       {StringBuilder sb_szIndentationSubValue;
      if ( szIndentationSubValue == null )
         sb_szIndentationSubValue = new StringBuilder( 32 );
      else
         sb_szIndentationSubValue = new StringBuilder( szIndentationSubValue );
      ZeidonStringCopy( sb_szIndentationSubValue, 1, 0, szIndentationValue, 1, 0, 51 );
      szIndentationSubValue = sb_szIndentationSubValue.toString( );}
       {StringBuilder sb_szIndentationSubValue;
      if ( szIndentationSubValue == null )
         sb_szIndentationSubValue = new StringBuilder( 32 );
      else
         sb_szIndentationSubValue = new StringBuilder( szIndentationSubValue );
      ZeidonStringConcat( sb_szIndentationSubValue, 1, 0, "      ", 1, 0, 51 );
      szIndentationSubValue = sb_szIndentationSubValue.toString( );}
      //:GenerateXML_CtlRecurs( vReportDef, vSourceOI, szEntityName, szOutputLine, lFileHandle, szIndentationSubValue )
      oTZRPSRCO_GenerateXML_CtlRecurs( vReportDef, vSourceOI, szEntityName, szOutputLine, lFileHandle, szIndentationSubValue );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateXML_LODRecurs( VIEW vReportDef BASED ON LOD TZRPSRCO,
//:                       VIEW vReportDefRecurs BASED ON LOD TZRPSRCO,
//:                       VIEW vSourceOI,
//:                       STRING ( 5000 ) szOutputLine,
//:                       INTEGER         lFileHandle,
//:                       STRING ( 50 )   szIndentationValue )

//:   STRING ( 32 ) szEntityName
public int 
oTZRPSRCO_GenerateXML_LODRecurs( View     vReportDef,
                                 View     vReportDefRecurs,
                                 View     vSourceOI,
                                 String   szOutputLine,
                                 int      lFileHandle,
                                 String   szIndentationValue )
{
   String   szEntityName = null;
   //:STRING ( 32 ) szDrivingViewName
   String   szDrivingViewName = null;
   //:STRING ( 50 ) szIndentationSubValue
   String   szIndentationSubValue = null;
   //:STRING ( 10 ) szCnt
   String   szCnt = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:szDrivingViewName = vReportDef.DrivingViewObjRef.Name
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szDrivingViewName;
   if ( szDrivingViewName == null )
      sb_szDrivingViewName = new StringBuilder( 32 );
   else
      sb_szDrivingViewName = new StringBuilder( szDrivingViewName );
   GetVariableFromAttribute( sb_szDrivingViewName, mi_lTempInteger_0, 'S', 33, vReportDef, "DrivingViewObjRef", "Name", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szDrivingViewName = sb_szDrivingViewName.toString( );}

   //:// Build one level of the PartialReportEntity subobject, matching the structure of the Driving LOD and
   //:// setting the ReportDisplayFlag entity for any entity with a corresponding GroupSet entity in the report, or having
   //:// a PartialReportEntityChild entity with a corresponding GroupSet entity. The flag is set to "D" if the entity has
   //:// a corresponding GroupSet entity and to a "C" if it has a child with a corresponding GroupSet entity.

   //:FOR EACH vReportDefRecurs.PartialReportEntity 
   RESULT = SetCursorFirstEntity( vReportDefRecurs, "PartialReportEntity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:szEntityName = vReportDefRecurs.PartialReportEntity.Name 
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
      GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_1, 'S', 33, vReportDefRecurs, "PartialReportEntity", "Name", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szEntityName = sb_szEntityName.toString( );}
      //: 
      //:// If ReportDisplayFlag = "D" then this is an entity that is displayed on the report (there is a GroupSet).  
      //:// Otherwise, it's a parent entity where one of it's children is displayed on the report.                    
      //:IF vReportDefRecurs.PartialReportEntity.ReportDisplayFlag = "D"
      if ( CompareAttributeToString( vReportDefRecurs, "PartialReportEntity", "ReportDisplayFlag", "D" ) == 0 )
      { 

         //:SET CURSOR FIRST vReportDef.GroupSet WHERE vReportDef.GroupSet.Tag = szEntityName 
         RESULT = SetCursorFirstEntityByString( vReportDef, "GroupSet", "Tag", szEntityName, "" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
         } 

         //:   // There is an error because the report should have a GroupSet for this.          
         //:END

         //:nRC = SetCursorFirstEntity( vSourceOI, szEntityName, "" )
         nRC = SetCursorFirstEntity( vSourceOI, szEntityName, "" );

         //:// Process group header
         //:SET CURSOR FIRST vReportDef.Group WHERE vReportDef.Group.Type = "gh"
         RESULT = SetCursorFirstEntityByString( vReportDef, "Group", "Type", "gh", "" );
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:szOutputLine = szIndentationValue + "   <" + szEntityName + "Header>"
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringCopy( sb_szOutputLine, 1, 0, szIndentationValue, 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, "   <", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, szEntityName, 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, "Header>", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
            //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
            oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );

            //:szIndentationSubValue = szIndentationValue + "      "
             {StringBuilder sb_szIndentationSubValue;
            if ( szIndentationSubValue == null )
               sb_szIndentationSubValue = new StringBuilder( 32 );
            else
               sb_szIndentationSubValue = new StringBuilder( szIndentationSubValue );
            ZeidonStringCopy( sb_szIndentationSubValue, 1, 0, szIndentationValue, 1, 0, 51 );
            szIndentationSubValue = sb_szIndentationSubValue.toString( );}
             {StringBuilder sb_szIndentationSubValue;
            if ( szIndentationSubValue == null )
               sb_szIndentationSubValue = new StringBuilder( 32 );
            else
               sb_szIndentationSubValue = new StringBuilder( szIndentationSubValue );
            ZeidonStringConcat( sb_szIndentationSubValue, 1, 0, "      ", 1, 0, 51 );
            szIndentationSubValue = sb_szIndentationSubValue.toString( );}
            //:GenerateXML_CtlRecurs( vReportDef, vSourceOI, szDrivingViewName, szOutputLine, lFileHandle, szIndentationSubValue )
            oTZRPSRCO_GenerateXML_CtlRecurs( vReportDef, vSourceOI, szDrivingViewName, szOutputLine, lFileHandle, szIndentationSubValue );

            //:szOutputLine = szIndentationValue + "   </" + szEntityName + "Header>"
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringCopy( sb_szOutputLine, 1, 0, szIndentationValue, 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, "   </", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, szEntityName, 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, "Header>", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
            //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
            oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );
         } 

         //:END

         //:// Go to generate an attribute entry for each attribute with mapping in the Detail Group of the GroupSet.
         //:SET CURSOR FIRST vReportDef.Group WHERE vReportDef.Group.Type = "ga"
         RESULT = SetCursorFirstEntityByString( vReportDef, "Group", "Type", "ga", "" );
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:         
            //:// Loop through vSourceOI for each entity
            //:LOOP WHILE nRC >= zCURSOR_SET                     
            while ( nRC >= zCURSOR_SET )
            { 

               //:szOutputLine = szIndentationValue + "   <" + szEntityName + ">"
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
               ZeidonStringCopy( sb_szOutputLine, 1, 0, szIndentationValue, 1, 0, 5001 );
               szOutputLine = sb_szOutputLine.toString( );}
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
               ZeidonStringConcat( sb_szOutputLine, 1, 0, "   <", 1, 0, 5001 );
               szOutputLine = sb_szOutputLine.toString( );}
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
               ZeidonStringConcat( sb_szOutputLine, 1, 0, szEntityName, 1, 0, 5001 );
               szOutputLine = sb_szOutputLine.toString( );}
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
               ZeidonStringConcat( sb_szOutputLine, 1, 0, ">", 1, 0, 5001 );
               szOutputLine = sb_szOutputLine.toString( );}
               //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
               oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );
               //:szIndentationSubValue = szIndentationValue + "   "
                {StringBuilder sb_szIndentationSubValue;
               if ( szIndentationSubValue == null )
                  sb_szIndentationSubValue = new StringBuilder( 32 );
               else
                  sb_szIndentationSubValue = new StringBuilder( szIndentationSubValue );
               ZeidonStringCopy( sb_szIndentationSubValue, 1, 0, szIndentationValue, 1, 0, 51 );
               szIndentationSubValue = sb_szIndentationSubValue.toString( );}
                {StringBuilder sb_szIndentationSubValue;
               if ( szIndentationSubValue == null )
                  sb_szIndentationSubValue = new StringBuilder( 32 );
               else
                  sb_szIndentationSubValue = new StringBuilder( szIndentationSubValue );
               ZeidonStringConcat( sb_szIndentationSubValue, 1, 0, "   ", 1, 0, 51 );
               szIndentationSubValue = sb_szIndentationSubValue.toString( );}

               //:GenerateXML_CtlRecurs( vReportDef, vSourceOI, szDrivingViewName, szOutputLine, lFileHandle, szIndentationSubValue )
               oTZRPSRCO_GenerateXML_CtlRecurs( vReportDef, vSourceOI, szDrivingViewName, szOutputLine, lFileHandle, szIndentationSubValue );

               //:// Check to see if there are child entities.
               //:IF vReportDefRecurs.PartialReportEntityChild EXISTS
               lTempInteger_2 = CheckExistenceOfEntity( vReportDefRecurs, "PartialReportEntityChild" );
               if ( lTempInteger_2 == 0 )
               { 
                  //:SetViewToSubobject( vReportDefRecurs, "PartialReportEntityChild" )
                  SetViewToSubobject( vReportDefRecurs, "PartialReportEntityChild" );
                  //:szIndentationSubValue = szIndentationValue + "      "
                   {StringBuilder sb_szIndentationSubValue;
                  if ( szIndentationSubValue == null )
                     sb_szIndentationSubValue = new StringBuilder( 32 );
                  else
                     sb_szIndentationSubValue = new StringBuilder( szIndentationSubValue );
                  ZeidonStringCopy( sb_szIndentationSubValue, 1, 0, szIndentationValue, 1, 0, 51 );
                  szIndentationSubValue = sb_szIndentationSubValue.toString( );}
                   {StringBuilder sb_szIndentationSubValue;
                  if ( szIndentationSubValue == null )
                     sb_szIndentationSubValue = new StringBuilder( 32 );
                  else
                     sb_szIndentationSubValue = new StringBuilder( szIndentationSubValue );
                  ZeidonStringConcat( sb_szIndentationSubValue, 1, 0, "      ", 1, 0, 51 );
                  szIndentationSubValue = sb_szIndentationSubValue.toString( );}
                  //:nRC = GenerateXML_LODRecurs( vReportDef,
                  //:                             vReportDefRecurs, vSourceOI, szOutputLine, lFileHandle, szIndentationSubValue )
                  oTZRPSRCO_GenerateXML_LODRecurs( vReportDef, vReportDefRecurs, vSourceOI, szOutputLine, lFileHandle, szIndentationSubValue );
                  //:ResetViewFromSubobject( vReportDefRecurs )
                  ResetViewFromSubobject( vReportDefRecurs );

                  //:// We need to reset on the report GroupSet/Group because it has been changed when we go down levels.
                  //:SET CURSOR FIRST vReportDef.GroupSet WHERE vReportDef.GroupSet.Tag = szEntityName
                  SetCursorFirstEntityByString( vReportDef, "GroupSet", "Tag", szEntityName, "" );
                  //:SET CURSOR FIRST vReportDef.Group WHERE vReportDef.Group.Type = "ga"
                  SetCursorFirstEntityByString( vReportDef, "Group", "Type", "ga", "" );
               } 

               //:END

               //:// End of checking for child entities...               
               //:szOutputLine = szIndentationValue + "   </" + szEntityName + ">"
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
               ZeidonStringCopy( sb_szOutputLine, 1, 0, szIndentationValue, 1, 0, 5001 );
               szOutputLine = sb_szOutputLine.toString( );}
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
               ZeidonStringConcat( sb_szOutputLine, 1, 0, "   </", 1, 0, 5001 );
               szOutputLine = sb_szOutputLine.toString( );}
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
               ZeidonStringConcat( sb_szOutputLine, 1, 0, szEntityName, 1, 0, 5001 );
               szOutputLine = sb_szOutputLine.toString( );}
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
               ZeidonStringConcat( sb_szOutputLine, 1, 0, ">", 1, 0, 5001 );
               szOutputLine = sb_szOutputLine.toString( );}
               //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
               oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );

               //:nRC = SetCursorNextEntity( vSourceOI, szEntityName, "" )
               nRC = SetCursorNextEntity( vSourceOI, szEntityName, "" );
            } 

            //:END
         } 

         //:END

         //:// Process group footer.
         //:SET CURSOR FIRST vReportDef.Group WHERE vReportDef.Group.Type = "gf"
         RESULT = SetCursorFirstEntityByString( vReportDef, "Group", "Type", "gf", "" );
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:szOutputLine = szIndentationValue + "   <" + szEntityName + "Footer>"
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringCopy( sb_szOutputLine, 1, 0, szIndentationValue, 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, "   <", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, szEntityName, 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, "Footer>", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
            //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
            oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );

            //:szIndentationSubValue = szIndentationValue + "      "
             {StringBuilder sb_szIndentationSubValue;
            if ( szIndentationSubValue == null )
               sb_szIndentationSubValue = new StringBuilder( 32 );
            else
               sb_szIndentationSubValue = new StringBuilder( szIndentationSubValue );
            ZeidonStringCopy( sb_szIndentationSubValue, 1, 0, szIndentationValue, 1, 0, 51 );
            szIndentationSubValue = sb_szIndentationSubValue.toString( );}
             {StringBuilder sb_szIndentationSubValue;
            if ( szIndentationSubValue == null )
               sb_szIndentationSubValue = new StringBuilder( 32 );
            else
               sb_szIndentationSubValue = new StringBuilder( szIndentationSubValue );
            ZeidonStringConcat( sb_szIndentationSubValue, 1, 0, "      ", 1, 0, 51 );
            szIndentationSubValue = sb_szIndentationSubValue.toString( );}
            //:GenerateXML_CtlRecurs( vReportDef, vSourceOI, szEntityName, szOutputLine, lFileHandle, szIndentationSubValue )
            oTZRPSRCO_GenerateXML_CtlRecurs( vReportDef, vSourceOI, szEntityName, szOutputLine, lFileHandle, szIndentationSubValue );
            //:szOutputLine = szIndentationValue + "   </" + szEntityName + "Footer>"
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringCopy( sb_szOutputLine, 1, 0, szIndentationValue, 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, "   </", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, szEntityName, 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, "Footer>", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
            //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
            oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );
         } 

         //:END

         //:ELSE
      } 
      else
      { 
         //:// Maybe the parent isn't being displayed but a child might be, so we need to check...
         //:      
         //:// Loop through vSourceOI for each entity
         //:nRC = SetCursorFirstEntity( vSourceOI, szEntityName, "" )
         nRC = SetCursorFirstEntity( vSourceOI, szEntityName, "" );
         //:LOOP WHILE nRC >= zCURSOR_SET                     
         while ( nRC >= zCURSOR_SET )
         { 
            //:IF vReportDefRecurs.PartialReportEntityChild EXISTS
            lTempInteger_3 = CheckExistenceOfEntity( vReportDefRecurs, "PartialReportEntityChild" );
            if ( lTempInteger_3 == 0 )
            { 

               //:SetViewToSubobject( vReportDefRecurs, "PartialReportEntityChild" )
               SetViewToSubobject( vReportDefRecurs, "PartialReportEntityChild" );
               //:szIndentationSubValue = szIndentationValue + "      "
                {StringBuilder sb_szIndentationSubValue;
               if ( szIndentationSubValue == null )
                  sb_szIndentationSubValue = new StringBuilder( 32 );
               else
                  sb_szIndentationSubValue = new StringBuilder( szIndentationSubValue );
               ZeidonStringCopy( sb_szIndentationSubValue, 1, 0, szIndentationValue, 1, 0, 51 );
               szIndentationSubValue = sb_szIndentationSubValue.toString( );}
                {StringBuilder sb_szIndentationSubValue;
               if ( szIndentationSubValue == null )
                  sb_szIndentationSubValue = new StringBuilder( 32 );
               else
                  sb_szIndentationSubValue = new StringBuilder( szIndentationSubValue );
               ZeidonStringConcat( sb_szIndentationSubValue, 1, 0, "      ", 1, 0, 51 );
               szIndentationSubValue = sb_szIndentationSubValue.toString( );}
               //:nRC = GenerateXML_LODRecurs( vReportDef,
               //:                             vReportDefRecurs, vSourceOI, szOutputLine, lFileHandle, szIndentationSubValue )
               oTZRPSRCO_GenerateXML_LODRecurs( vReportDef, vReportDefRecurs, vSourceOI, szOutputLine, lFileHandle, szIndentationSubValue );
               //:ResetViewFromSubobject( vReportDefRecurs )
               ResetViewFromSubobject( vReportDefRecurs );
            } 

            //:   
            //:END
            //:nRC = SetCursorNextEntity( vSourceOI, szEntityName, "" )
            nRC = SetCursorNextEntity( vSourceOI, szEntityName, "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( vReportDefRecurs, "PartialReportEntity", "" );
      //:END
   } 

   //:   
   //:END  // FOR EACH vReportDefRecurs.PartialReportEntity 
   return( 0 );
// END
} 


//:LOCAL OPERATION
private int 
oTZRPSRCO_GenerateXMLLine( View     ReportObject,
                           int      lFileHandle,
                           String   szOutputLine )
{

   //:GenerateXMLLine( VIEW ReportObject BASED ON LOD TZRPSRCO,
   //:           INTEGER lFileHandle,
   //:           STRING ( 5000 ) szOutputLine )

   //:// This is just a SysWriteLine with an options TraceLineS statement.
   //://TraceLineS( "*** Line: ", szOutputLine )
   //:SysWriteLine( ReportObject, lFileHandle, szOutputLine )
   try
   {
       m_KZOEP1AA.SysWriteLine( ReportObject, lFileHandle, szOutputLine );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:GenerateXML_CtlRecurs( VIEW vReportDef BASED ON LOD TZRPSRCO,
//:                       VIEW vSourceOI,
//:                       STRING ( 32 )   szDrivingObjectViewName,
//:                       STRING ( 5000 ) szOutputLine,
//:                       INTEGER         lFileHandle,
//:                       STRING ( 50 )   szIndentationValue )

//:   VIEW vMappingOI
private int 
oTZRPSRCO_GenerateXML_CtlRecurs( View     vReportDef,
                                 View     vSourceOI,
                                 String   szDrivingObjectViewName,
                                 String   szOutputLine,
                                 int      lFileHandle,
                                 String   szIndentationValue )
{
   zVIEW    vMappingOI = new zVIEW( );
   //:STRING ( 32 )   szEntityName
   String   szEntityName = null;
   //:STRING ( 32 )   szAttributeName
   String   szAttributeName = null;
   //:STRING ( 32 )   szMappingName
   String   szMappingName = null;
   //:STRING ( 5000 ) szReturnedAttributeValue
   String   szReturnedAttributeValue = null;
   //:STRING ( 5000 ) szConvertedAttributeValue
   String   szConvertedAttributeValue = null;
   //:STRING ( 200 )  szMsg
   String   szMsg = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;


   //:// Generate an attribute entry for each attribute with mapping in the GroupSet. Note that these are all at the same
   //:// hierarchical level since we're assuming no tables within a GroupSet. However, the controls in the GroupSet may well be
   //:// organized hierarchically and must thus be processed recursively.

   //:// Sort the Controls in position order so they will be in the same order as in the XSLT. (This probably isn't
   //:// necessary, but it will simply debugging.)
   //:OrderEntityForView( vReportDef, "Control", "PSDLG_Y A PSDLG_X A" )
   OrderEntityForView( vReportDef, "Control", "PSDLG_Y A PSDLG_X A" );

   //:// Process each Control. The processing rule is simple:
   //:// If the control has mapping, build an XML node.
   //:// If the control doesn't have mapping, ignore it.
   //:// The only issue is that we must step down a level if the Control has a subcontrol.

   //:FOR EACH vReportDef.Control
   RESULT = SetCursorFirstEntity( vReportDef, "Control", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF vReportDef.CtrlCtrl EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( vReportDef, "CtrlCtrl" );
      if ( lTempInteger_0 == 0 )
      { 
         //:SetViewToSubobject( vReportDef, "CtrlCtrl" )
         SetViewToSubobject( vReportDef, "CtrlCtrl" );
         //:GenerateXML_CtlRecurs( vReportDef, vSourceOI, szDrivingObjectViewName, szOutputLine, lFileHandle, szIndentationValue )
         oTZRPSRCO_GenerateXML_CtlRecurs( vReportDef, vSourceOI, szDrivingObjectViewName, szOutputLine, lFileHandle, szIndentationValue );
         //:ResetViewFromSubobject( vReportDef )
         ResetViewFromSubobject( vReportDef );
         //:ELSE
      } 
      else
      { 

         //:IF vReportDef.CtrlMapER_Attribute EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( vReportDef, "CtrlMapER_Attribute" );
         if ( lTempInteger_1 == 0 )
         { 
            //:// Format XML Line for Attribute.
            //:szReturnedAttributeValue = ""
             {StringBuilder sb_szReturnedAttributeValue;
            if ( szReturnedAttributeValue == null )
               sb_szReturnedAttributeValue = new StringBuilder( 32 );
            else
               sb_szReturnedAttributeValue = new StringBuilder( szReturnedAttributeValue );
            ZeidonStringCopy( sb_szReturnedAttributeValue, 1, 0, "", 1, 0, 5001 );
            szReturnedAttributeValue = sb_szReturnedAttributeValue.toString( );}
            //:szConvertedAttributeValue = ""
             {StringBuilder sb_szConvertedAttributeValue;
            if ( szConvertedAttributeValue == null )
               sb_szConvertedAttributeValue = new StringBuilder( 32 );
            else
               sb_szConvertedAttributeValue = new StringBuilder( szConvertedAttributeValue );
            ZeidonStringCopy( sb_szConvertedAttributeValue, 1, 0, "", 1, 0, 5001 );
            szConvertedAttributeValue = sb_szConvertedAttributeValue.toString( );}
            //:szAttributeName = vReportDef.CtrlMapER_Attribute.Name
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szAttributeName;
            if ( szAttributeName == null )
               sb_szAttributeName = new StringBuilder( 32 );
            else
               sb_szAttributeName = new StringBuilder( szAttributeName );
            GetVariableFromAttribute( sb_szAttributeName, mi_lTempInteger_2, 'S', 33, vReportDef, "CtrlMapER_Attribute", "Name", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szAttributeName = sb_szAttributeName.toString( );}
            //:szEntityName    = vReportDef.CtrlMapRelatedEntity.Name
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szEntityName;
            if ( szEntityName == null )
               sb_szEntityName = new StringBuilder( 32 );
            else
               sb_szEntityName = new StringBuilder( szEntityName );
            GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_3, 'S', 33, vReportDef, "CtrlMapRelatedEntity", "Name", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szEntityName = sb_szEntityName.toString( );}
            //:IF vReportDef.CtrlMapView.Name = szDrivingObjectViewName   // Check if mapping if from Driving Object View.
            if ( CompareAttributeToString( vReportDef, "CtrlMapView", "Name", szDrivingObjectViewName ) == 0 )
            { 
               //:GetStringFromAttributeByContext( szReturnedAttributeValue, vSourceOI, szEntityName, szAttributeName, "", 5000 )
               {StringBuilder sb_szReturnedAttributeValue;
               if ( szReturnedAttributeValue == null )
                  sb_szReturnedAttributeValue = new StringBuilder( 32 );
               else
                  sb_szReturnedAttributeValue = new StringBuilder( szReturnedAttributeValue );
               GetStringFromAttributeByContext( sb_szReturnedAttributeValue, vSourceOI, szEntityName, szAttributeName, "", 5000 );
               szReturnedAttributeValue = sb_szReturnedAttributeValue.toString( );}
               //:ELSE
            } 
            else
            { 
               //:// Mapping is from separate view.
               //:szMappingName = vReportDef.CtrlMapView.Name
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szMappingName;
               if ( szMappingName == null )
                  sb_szMappingName = new StringBuilder( 32 );
               else
                  sb_szMappingName = new StringBuilder( szMappingName );
               GetVariableFromAttribute( sb_szMappingName, mi_lTempInteger_4, 'S', 33, vReportDef, "CtrlMapView", "Name", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szMappingName = sb_szMappingName.toString( );}
               //:GET VIEW vMappingOI NAMED szMappingName
               GetViewByName( vMappingOI, szMappingName, vReportDef, zLEVEL_TASK );
               //:GetStringFromAttributeByContext( szReturnedAttributeValue, vMappingOI, szEntityName, szAttributeName, "", 5000 )
               {StringBuilder sb_szReturnedAttributeValue;
               if ( szReturnedAttributeValue == null )
                  sb_szReturnedAttributeValue = new StringBuilder( 32 );
               else
                  sb_szReturnedAttributeValue = new StringBuilder( szReturnedAttributeValue );
               GetStringFromAttributeByContext( sb_szReturnedAttributeValue, vMappingOI, szEntityName, szAttributeName, "", 5000 );
               szReturnedAttributeValue = sb_szReturnedAttributeValue.toString( );}
            } 

            //:END

            //:ConvertXML_SpecialCharacters( vReportDef, szConvertedAttributeValue, szReturnedAttributeValue, 5000 )
            {StringBuilder sb_szReturnedAttributeValue;
            if ( szReturnedAttributeValue == null )
               sb_szReturnedAttributeValue = new StringBuilder( 32 );
            else
               sb_szReturnedAttributeValue = new StringBuilder( szReturnedAttributeValue );
            StringBuilder sb_szConvertedAttributeValue;
            if ( szConvertedAttributeValue == null )
               sb_szConvertedAttributeValue = new StringBuilder( 32 );
            else
               sb_szConvertedAttributeValue = new StringBuilder( szConvertedAttributeValue );
            ConvertXML_SpecialCharacters( vReportDef, sb_szConvertedAttributeValue, sb_szReturnedAttributeValue, 5000 );
            szReturnedAttributeValue = sb_szReturnedAttributeValue.toString( );
            szConvertedAttributeValue = sb_szConvertedAttributeValue.toString( );}

            //:szOutputLine = szIndentationValue + "  " + "<" + szEntityName + "." + szAttributeName + ">" + szConvertedAttributeValue
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringCopy( sb_szOutputLine, 1, 0, szIndentationValue, 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, "  ", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, "<", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, szEntityName, 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, ".", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, szAttributeName, 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, ">", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, szConvertedAttributeValue, 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
            //:szOutputLine = szOutputLine + "</" + szEntityName + "." + szAttributeName + ">"
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, "</", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, szEntityName, 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, ".", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, szAttributeName, 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, ">", 1, 0, 5001 );
            szOutputLine = sb_szOutputLine.toString( );}
            //:GenerateXMLLine( vReportDef, lFileHandle, szOutputLine )
            oTZRPSRCO_GenerateXMLLine( vReportDef, lFileHandle, szOutputLine );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( vReportDef, "Control", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 

}
