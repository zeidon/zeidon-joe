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

package com.quinsoft.epamms;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.quinsoft.epamms.ZGlobal1_Operation;
import com.quinsoft.epamms.ZGlobalV_Operation;

import com.quinsoft.zeidon.zeidonoperations.KZOEP1AA;
import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;

/**
   @author QuinSoft
**/

public class mSPLDef_Object extends VmlObjectOperations
{
   private final KZOEP1AA m_KZOEP1AA;
   private final ZDRVROPR m_ZDRVROPR;
   public mSPLDef_Object( View view )
   {
      super( view );
      m_KZOEP1AA = new KZOEP1AA( view );
      m_ZDRVROPR = new ZDRVROPR( view );
   }


//:TRANSFORMATION OPERATION
//:GeneratePDF_Label( VIEW mSPLDef BASED ON LOD mSPLDef )

//:   VIEW mSPLDefPDF BASED ON LOD mSPLDef
public int 
omSPLDef_GeneratePDF_Label( View     mSPLDef )
{
   zVIEW    mSPLDefPDF = new zVIEW( );
   //:STRING ( 32000 ) szWriteBuffer
   String   szWriteBuffer = null;
   //:INTEGER lFile
   int      lFile = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:STRING ( 50 ) szLeadingBlanks
   String   szLeadingBlanks = null;
   //:STRING ( 10 ) szSize
   String   szSize = null;
   //:STRING ( 10 ) szTop
   String   szTop = null;
   //:STRING ( 10 ) szHeight
   String   szHeight = null;
   //:STRING ( 10 ) szWidth
   String   szWidth = null;
   //:STRING ( 10 ) szPageHeight
   String   szPageHeight = null;
   //:STRING ( 10 ) szPageWidth
   String   szPageWidth = null;
   //:STRING ( 10 ) szLeft
   String   szLeft = null;
   //:STRING ( 32 ) szLastSectionType
   String   szLastSectionType = null;
   //:STRING ( 32 ) szVoid
   String   szVoid = null;
   //:STRING ( 2 )  szCount
   String   szCount = null;
   //:STRING ( 40 ) szPanelDottedBorder
   String   szPanelDottedBorder = null;
   //:STRING ( 2 )  szSequenceNumber
   String   szSequenceNumber = null;
   //:STRING ( 50 ) szSectionName
   String   szSectionName = null;
   //:INTEGER       lCount
   int      lCount = 0;
   //:SHORT         nRC
   int      nRC = 0;
   //:DECIMAL       InterPanelSpace
   double  InterPanelSpace = 0.0;
   //:DECIMAL       Left
   double  Left = 0.0;
   //:DECIMAL       Temp
   double  Temp = 0.0;
   //:STRING ( 32 ) szLPLR_Name
   String   szLPLR_Name = null;
   //:STRING ( 64 ) szSystemIniApplName
   String   szSystemIniApplName = null;
   //:STRING ( 64 ) szLabelName
   String   szLabelName = null;
   //:STRING ( 256 ) szXslDirectory
   String   szXslDirectory = null;
   //:STRING ( 256 ) szXmlName
   String   szXmlName = null;
   //:STRING ( 256 ) szXslName
   String   szXslName = null;
   //:STRING ( 30 ) szDateTime
   String   szDateTime = null;
   //:STRING ( 30 ) szDateTimeDisplay
   String   szDateTimeDisplay = null;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   double  dTempDecimal_4 = 0.0;


   //:// Generate a PDF Label from the SPLD.
   //:SysGetDateTime( szDateTime )
    {StringBuilder sb_szDateTime;
   if ( szDateTime == null )
      sb_szDateTime = new StringBuilder( 32 );
   else
      sb_szDateTime = new StringBuilder( szDateTime );
      m_KZOEP1AA.SysGetDateTime( sb_szDateTime );
   szDateTime = sb_szDateTime.toString( );}
   //:mSPLDef.SubregPhysicalLabelDef.wDateTime = szDateTime
   SetAttributeFromString( mSPLDef, "SubregPhysicalLabelDef", "wDateTime", szDateTime );

   //:// Delete any existing DisplaySection entries.
   //:FOR EACH mSPLDef.DisplaySection 
   RESULT = SetCursorFirstEntity( mSPLDef, "DisplaySection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mSPLDef.DisplaySection NONE 
      RESULT = DeleteEntity( mSPLDef, "DisplaySection", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "DisplaySection", "" );
   } 

   //:END

   //:// Open the File
   //:// SfGetApplicationForSubtask( szLPLR_Name, mSPLDef )
   //:// szSystemIniApplName = "[App." + szLPLR_Name + "]"
   //:// SysReadZeidonIni( -1, szSystemIniApplName, "WebDirectory", szXslDirectory )
   //:// SysConvertEnvironmentString( szXslDirectory, szXslDirectory )
   //:// szXslDirectory = "C:/Program Files/Apache Group/tomcat 7.0/webapps/ROOT/epamms/"

   //:SysGetEnvVar( szXslName, "CATALINA_HOME", 256 ) // borrow szXslName for a couple of lines
   {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
       m_KZOEP1AA.SysGetEnvVar( sb_szXslName, "CATALINA_HOME", 256 );
   szXslName = sb_szXslName.toString( );}
   //:// SysAppendcDirSep( szXslName )
   //:SysConvertEnvironmentString( szXslDirectory, szXslName )
   {StringBuilder sb_szXslDirectory;
   if ( szXslDirectory == null )
      sb_szXslDirectory = new StringBuilder( 32 );
   else
      sb_szXslDirectory = new StringBuilder( szXslDirectory );
       m_KZOEP1AA.SysConvertEnvironmentString( sb_szXslDirectory, szXslName );
   szXslDirectory = sb_szXslDirectory.toString( );}
   //:szXslDirectory = szXslDirectory + "/webapps/ROOT/" + mSPLDef.SubregOrganization.LoginName + "/"
    {StringBuilder sb_szXslDirectory;
   if ( szXslDirectory == null )
      sb_szXslDirectory = new StringBuilder( 32 );
   else
      sb_szXslDirectory = new StringBuilder( szXslDirectory );
      ZeidonStringConcat( sb_szXslDirectory, 1, 0, "/webapps/ROOT/", 1, 0, 257 );
   szXslDirectory = sb_szXslDirectory.toString( );}
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 129, mSPLDef, "SubregOrganization", "LoginName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szXslDirectory;
   if ( szXslDirectory == null )
      sb_szXslDirectory = new StringBuilder( 32 );
   else
      sb_szXslDirectory = new StringBuilder( szXslDirectory );
      ZeidonStringConcat( sb_szXslDirectory, 1, 0, szTempString_0, 1, 0, 257 );
   szXslDirectory = sb_szXslDirectory.toString( );}
    {StringBuilder sb_szXslDirectory;
   if ( szXslDirectory == null )
      sb_szXslDirectory = new StringBuilder( 32 );
   else
      sb_szXslDirectory = new StringBuilder( szXslDirectory );
      ZeidonStringConcat( sb_szXslDirectory, 1, 0, "/", 1, 0, 257 );
   szXslDirectory = sb_szXslDirectory.toString( );}

   //:szLabelName = mSPLDef.SubregProduct.Name
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szLabelName;
   if ( szLabelName == null )
      sb_szLabelName = new StringBuilder( 32 );
   else
      sb_szLabelName = new StringBuilder( szLabelName );
       GetVariableFromAttribute( sb_szLabelName, mi_lTempInteger_1, 'S', 65, mSPLDef, "SubregProduct", "Name", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szLabelName = sb_szLabelName.toString( );}

   //:szXmlName = szXslDirectory + "xml/"
    {StringBuilder sb_szXmlName;
   if ( szXmlName == null )
      sb_szXmlName = new StringBuilder( 32 );
   else
      sb_szXmlName = new StringBuilder( szXmlName );
      ZeidonStringCopy( sb_szXmlName, 1, 0, szXslDirectory, 1, 0, 257 );
   szXmlName = sb_szXmlName.toString( );}
    {StringBuilder sb_szXmlName;
   if ( szXmlName == null )
      sb_szXmlName = new StringBuilder( 32 );
   else
      sb_szXmlName = new StringBuilder( szXmlName );
      ZeidonStringConcat( sb_szXmlName, 1, 0, "xml/", 1, 0, 257 );
   szXmlName = sb_szXmlName.toString( );}
   //:szXslName = szXslDirectory + "xsl/"
    {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
      ZeidonStringCopy( sb_szXslName, 1, 0, szXslDirectory, 1, 0, 257 );
   szXslName = sb_szXslName.toString( );}
    {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
      ZeidonStringConcat( sb_szXslName, 1, 0, "xsl/", 1, 0, 257 );
   szXslName = sb_szXslName.toString( );}
   //:SysValidDirOrFile( szXmlName, 1, 1, 256 )
   SysValidDirOrFile( szXmlName, 1, 1, 256 );
   //:SysValidDirOrFile( szXslName, 1, 1, 256 )
   SysValidDirOrFile( szXslName, 1, 1, 256 );
   //:szXmlName = szXmlName + szLabelName + ".xml"
    {StringBuilder sb_szXmlName;
   if ( szXmlName == null )
      sb_szXmlName = new StringBuilder( 32 );
   else
      sb_szXmlName = new StringBuilder( szXmlName );
      ZeidonStringConcat( sb_szXmlName, 1, 0, szLabelName, 1, 0, 257 );
   szXmlName = sb_szXmlName.toString( );}
    {StringBuilder sb_szXmlName;
   if ( szXmlName == null )
      sb_szXmlName = new StringBuilder( 32 );
   else
      sb_szXmlName = new StringBuilder( szXmlName );
      ZeidonStringConcat( sb_szXmlName, 1, 0, ".xml", 1, 0, 257 );
   szXmlName = sb_szXmlName.toString( );}
   //:szXslName = szXslName + szLabelName + ".xsl"
    {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
      ZeidonStringConcat( sb_szXslName, 1, 0, szLabelName, 1, 0, 257 );
   szXslName = sb_szXslName.toString( );}
    {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
      ZeidonStringConcat( sb_szXslName, 1, 0, ".xsl", 1, 0, 257 );
   szXslName = sb_szXslName.toString( );}
   //:TraceLineS( "Output Xsl Filename: ", szXslName )
   TraceLineS( "Output Xsl Filename: ", szXslName );
   //:lFile = SysOpenFile( mSPLDef, szXslName, COREFILE_WRITE )
   try
   {
       lFile = m_KZOEP1AA.SysOpenFile( mSPLDef, szXslName, COREFILE_WRITE );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:IF lFile < 0
   if ( lFile < 0 )
   { 
      //:MessageSend( mSPLDef, "", "Generate Label",
      //:             "Error opening output file.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mSPLDef, "", "Generate Label", "Error opening output file.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:TraceLineS( "##### After open: ", szXslName )
   TraceLineS( "##### After open: ", szXslName );

   //:// Put out XSL header data.
   //:szWriteBuffer = "<?xml version=@1.0@ encoding='iso-8859-1'?>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<?xml version=@1.0@ encoding='iso-8859-1'?>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "@", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "@", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:GetStringFromAttributeByContext( szDateTimeDisplay, mSPLDef, "SubregPhysicalLabelDef", "wDateTime", "YYYY/MM/DD HH:MM:SS.S AM", 30 )
   {StringBuilder sb_szDateTimeDisplay;
   if ( szDateTimeDisplay == null )
      sb_szDateTimeDisplay = new StringBuilder( 32 );
   else
      sb_szDateTimeDisplay = new StringBuilder( szDateTimeDisplay );
       GetStringFromAttributeByContext( sb_szDateTimeDisplay, mSPLDef, "SubregPhysicalLabelDef", "wDateTime", "YYYY/MM/DD HH:MM:SS.S AM", 30 );
   szDateTimeDisplay = sb_szDateTimeDisplay.toString( );}
   //:szWriteBuffer = "<!-- Output created by ePamms   " + szDateTimeDisplay + " -->"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<!-- Output created by ePamms   ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szDateTimeDisplay, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " -->", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = "<xsl:stylesheet version=^1.0^"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<xsl:stylesheet version=^1.0^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "   xmlns:xsl=^http://www.w3.org/1999/XSL/Transform^"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   xmlns:xsl=^http://www.w3.org/1999/XSL/Transform^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "   xmlns:fo=^http://www.w3.org/1999/XSL/Format^"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   xmlns:fo=^http://www.w3.org/1999/XSL/Format^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "   xmlns:fox=^http://xml.apache.org/fop/extensions^"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   xmlns:fox=^http://xml.apache.org/fop/extensions^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "   xmlns:exslt=^http://exslt.org/common^"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   xmlns:exslt=^http://exslt.org/common^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "   xmlns:exsl=^http://exslt.org/common^"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   xmlns:exsl=^http://exslt.org/common^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "   extension-element-prefixes=^exsl^"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   extension-element-prefixes=^exsl^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "   xmlns:msxsl=^urn:schemas-microsoft-com:xslt^"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   xmlns:msxsl=^urn:schemas-microsoft-com:xslt^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "   exclude-result-prefixes=^msxsl^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   exclude-result-prefixes=^msxsl^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = ""
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "   <xsl:output method=^xml^ indent=^yes^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   <xsl:output method=^xml^ indent=^yes^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Beginning of XSL Label Definition
   //:szWriteBuffer = "   <xsl:template match=^/zOI^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   <xsl:template match=^/zOI^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "      <fo:root>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "      <fo:root>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "         <fo:layout-master-set>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "         <fo:layout-master-set>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Page
   //:Temp = mSPLDef.LLD_Page.Height + 2.0  // add 1-inch border
   {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
       GetDecimalFromAttribute( md_dTempDecimal_0, mSPLDef, "LLD_Page", "Height" );
   dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
   Temp = dTempDecimal_0 + 2.0;
   //:szPageHeight = Temp
    {StringBuilder sb_szPageHeight;
   if ( szPageHeight == null )
      sb_szPageHeight = new StringBuilder( 32 );
   else
      sb_szPageHeight = new StringBuilder( szPageHeight );
      ZeidonStringConvertFromNumber( sb_szPageHeight, 1, 0, 10, 0, Temp, "D" );
   szPageHeight = sb_szPageHeight.toString( );}
   //:Temp = mSPLDef.LLD_Page.Width + 2.0 // add 1-inch border
   {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
       GetDecimalFromAttribute( md_dTempDecimal_1, mSPLDef, "LLD_Page", "Width" );
   dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
   Temp = dTempDecimal_1 + 2.0;
   //:szPageWidth  = Temp
    {StringBuilder sb_szPageWidth;
   if ( szPageWidth == null )
      sb_szPageWidth = new StringBuilder( 32 );
   else
      sb_szPageWidth = new StringBuilder( szPageWidth );
      ZeidonStringConvertFromNumber( sb_szPageWidth, 1, 0, 10, 0, Temp, "D" );
   szPageWidth = sb_szPageWidth.toString( );}
   //:szWriteBuffer = "            <fo:simple-page-master master-name=^p1^ page-width=^" + szPageWidth + "in^ page-height=^" + szPageHeight + "in^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <fo:simple-page-master master-name=^p1^ page-width=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szPageWidth, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^ page-height=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szPageHeight, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = "               <fo:region-body region-name=^xsl-region-body^ margin=^0.2in^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <fo:region-body region-name=^xsl-region-body^ margin=^0.2in^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "            </fo:simple-page-master>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            </fo:simple-page-master>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "         </fo:layout-master-set>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "         </fo:layout-master-set>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "         <fo:page-sequence master-reference=^p1^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "         <fo:page-sequence master-reference=^p1^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "            <fo:flow flow-name=^xsl-region-body^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <fo:flow flow-name=^xsl-region-body^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Generate unique XML name for each Marketing Section, which contains a sequence number suffix.
   //:lCount = 0
   lCount = 0;
   //:FOR EACH mSPLDef.SPLD_MarketingSection 
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:lCount = lCount + 1
      lCount = lCount + 1;
      //:szSequenceNumber = lCount
       {StringBuilder sb_szSequenceNumber;
      if ( szSequenceNumber == null )
         sb_szSequenceNumber = new StringBuilder( 32 );
      else
         sb_szSequenceNumber = new StringBuilder( szSequenceNumber );
            ZeidonStringConvertFromNumber( sb_szSequenceNumber, 1, 0, 2, lCount, (double) 0.0, "I" );
      szSequenceNumber = sb_szSequenceNumber.toString( );}
      //:szSectionName = "SPLD_MarketingSection" + szSequenceNumber
       {StringBuilder sb_szSectionName;
      if ( szSectionName == null )
         sb_szSectionName = new StringBuilder( 32 );
      else
         sb_szSectionName = new StringBuilder( szSectionName );
            ZeidonStringCopy( sb_szSectionName, 1, 0, "SPLD_MarketingSection", 1, 0, 51 );
      szSectionName = sb_szSectionName.toString( );}
       {StringBuilder sb_szSectionName;
      if ( szSectionName == null )
         sb_szSectionName = new StringBuilder( 32 );
      else
         sb_szSectionName = new StringBuilder( szSectionName );
            ZeidonStringConcat( sb_szSectionName, 1, 0, szSequenceNumber, 1, 0, 51 );
      szSectionName = sb_szSectionName.toString( );}
      //:mSPLDef.SPLD_MarketingSection.wXML_MarketingName = szSectionName
      SetAttributeFromString( mSPLDef, "SPLD_MarketingSection", "wXML_MarketingName", szSectionName );
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingSection", "" );
   } 

   //:END
   //:mSPLDef.SubregPhysicalLabelDef.wLastDisplaySuffixCount = 0
   SetAttributeFromInteger( mSPLDef, "SubregPhysicalLabelDef", "wLastDisplaySuffixCount", 0 );

   //:// Generate printer location icons.
   //:FormatPrintIcons( mSPLDef, lFile, szWriteBuffer )
   omSPLDef_FormatPrintIcons( mSPLDef, lFile, szWriteBuffer );

   //:// Compute the Top Position values for each Block and SubBlock.
   //:ComputeTopPositions( mSPLDef )
   omSPLDef_ComputeTopPositions( mSPLDef );

   //:// Loop through each PANEL, creating a Panel with Block containers.
   //:lCount          = 0
   lCount = 0;
   //:Left            = 1.0
   Left = 1.0;
   //:InterPanelSpace = 0.75
   InterPanelSpace = 0.75;
   //:IF mSPLDef.SubregPhysicalLabelDef.wFormatWithDottedBorders = "Y"
   if ( CompareAttributeToString( mSPLDef, "SubregPhysicalLabelDef", "wFormatWithDottedBorders", "Y" ) == 0 )
   { 
      //:szPanelDottedBorder = " border=^1.0pt dotted green^"
       {StringBuilder sb_szPanelDottedBorder;
      if ( szPanelDottedBorder == null )
         sb_szPanelDottedBorder = new StringBuilder( 32 );
      else
         sb_szPanelDottedBorder = new StringBuilder( szPanelDottedBorder );
            ZeidonStringCopy( sb_szPanelDottedBorder, 1, 0, " border=^1.0pt dotted green^", 1, 0, 41 );
      szPanelDottedBorder = sb_szPanelDottedBorder.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szPanelDottedBorder = ""
       {StringBuilder sb_szPanelDottedBorder;
      if ( szPanelDottedBorder == null )
         sb_szPanelDottedBorder = new StringBuilder( 32 );
      else
         sb_szPanelDottedBorder = new StringBuilder( szPanelDottedBorder );
            ZeidonStringCopy( sb_szPanelDottedBorder, 1, 0, "", 1, 0, 41 );
      szPanelDottedBorder = sb_szPanelDottedBorder.toString( );}
   } 

   //:END
   //:FOR EACH mSPLDef.LLD_Panel 
   RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Panel", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:lCount = lCount + 1
      lCount = lCount + 1;
      //:szCount = lCount
       {StringBuilder sb_szCount;
      if ( szCount == null )
         sb_szCount = new StringBuilder( 32 );
      else
         sb_szCount = new StringBuilder( szCount );
            ZeidonStringConvertFromNumber( sb_szCount, 1, 0, 2, lCount, (double) 0.0, "I" );
      szCount = sb_szCount.toString( );}
      //:szLeft = Left
       {StringBuilder sb_szLeft;
      if ( szLeft == null )
         sb_szLeft = new StringBuilder( 32 );
      else
         sb_szLeft = new StringBuilder( szLeft );
            ZeidonStringConvertFromNumber( sb_szLeft, 1, 0, 10, 0, Left, "D" );
      szLeft = sb_szLeft.toString( );}

      //:// Panel Container
      //://szTop        = "1.0"
      //:Temp = mSPLDef.LLD_Panel.Top + 1.0
      {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
             GetDecimalFromAttribute( md_dTempDecimal_2, mSPLDef, "LLD_Panel", "Top" );
      dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
      Temp = dTempDecimal_2 + 1.0;
      //:szTop = Temp 
       {StringBuilder sb_szTop;
      if ( szTop == null )
         sb_szTop = new StringBuilder( 32 );
      else
         sb_szTop = new StringBuilder( szTop );
            ZeidonStringConvertFromNumber( sb_szTop, 1, 0, 10, 0, Temp, "D" );
      szTop = sb_szTop.toString( );}
      //:Temp = mSPLDef.LLD_Panel.Left + 1.0
      {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
             GetDecimalFromAttribute( md_dTempDecimal_3, mSPLDef, "LLD_Panel", "Left" );
      dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
      Temp = dTempDecimal_3 + 1.0;
      //:szLeft = Temp
       {StringBuilder sb_szLeft;
      if ( szLeft == null )
         sb_szLeft = new StringBuilder( 32 );
      else
         sb_szLeft = new StringBuilder( szLeft );
            ZeidonStringConvertFromNumber( sb_szLeft, 1, 0, 10, 0, Temp, "D" );
      szLeft = sb_szLeft.toString( );}
      //:szHeight     = mSPLDef.LLD_Panel.Height
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szHeight;
      if ( szHeight == null )
         sb_szHeight = new StringBuilder( 32 );
      else
         sb_szHeight = new StringBuilder( szHeight );
             GetVariableFromAttribute( sb_szHeight, mi_lTempInteger_2, 'S', 11, mSPLDef, "LLD_Panel", "Height", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szHeight = sb_szHeight.toString( );}
      //:szWidth      = mSPLDef.LLD_Panel.Width
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szWidth;
      if ( szWidth == null )
         sb_szWidth = new StringBuilder( 32 );
      else
         sb_szWidth = new StringBuilder( szWidth );
             GetVariableFromAttribute( sb_szWidth, mi_lTempInteger_3, 'S', 11, mSPLDef, "LLD_Panel", "Width", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szWidth = sb_szWidth.toString( );}
      //:szWriteBuffer = "               <fo:block-container position=^absolute^ top=^" + szTop + "in^ left=^" + szLeft + 
      //:                "in^ height=^" + szHeight + "in^ width=^" + szWidth + "in^" + szPanelDottedBorder + ">"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <fo:block-container position=^absolute^ top=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTop, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^ left=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szLeft, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^ height=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szHeight, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^ width=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szWidth, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szPanelDottedBorder, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, ">", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }
      //:szWriteBuffer = ""
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:// Panel Number
      //:szWriteBuffer = "                  <!-- Panel Number " + szCount + " -->"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <!-- Panel Number ", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szCount, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " -->", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }
      //:szWriteBuffer = "                  <fo:block-container position=^absolute^ top=^-0.2in^ left=^-0.2in^>" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <fo:block-container position=^absolute^ top=^-0.2in^ left=^-0.2in^>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }
      //:szWriteBuffer = "                     <fo:block text-align=^left^>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                     <fo:block text-align=^left^>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }
      //:szWriteBuffer = "                        " + szCount
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                        ", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szCount, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }
      //:szWriteBuffer = "                     </fo:block>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                     </fo:block>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }
      //:szWriteBuffer = "                  </fo:block-container>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  </fo:block-container>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }
      //:szWriteBuffer = ""
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:// Process each Block within the Panel.
      //:szLeadingBlanks = "               "
       {StringBuilder sb_szLeadingBlanks;
      if ( szLeadingBlanks == null )
         sb_szLeadingBlanks = new StringBuilder( 32 );
      else
         sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
            ZeidonStringCopy( sb_szLeadingBlanks, 1, 0, "               ", 1, 0, 51 );
      szLeadingBlanks = sb_szLeadingBlanks.toString( );}
      //:CreateViewFromView( mSPLDefPDF, mSPLDef )
      CreateViewFromView( mSPLDefPDF, mSPLDef );
      //:NAME VIEW mSPLDefPDF "mSPLDefPDF"
      SetNameForView( mSPLDefPDF, "mSPLDefPDF", null, zLEVEL_TASK );
      //://NAME VIEW mSPLDef "mSPLDef"
      //:nRC = ProcessPDF_Blocks( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer )
      nRC = omSPLDef_ProcessPDF_Blocks( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer );
      //:DropView( mSPLDefPDF )
      DropView( mSPLDefPDF );
      //:IF nRC = 2
      if ( nRC == 2 )
      { 
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END

      //:// Close Panel Container.
      //:szWriteBuffer = "               </fo:block-container>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               </fo:block-container>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:// Increment Left position for next Panel.
      //:Left = Left + InterPanelSpace + mSPLDef.LLD_Panel.Width
      {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
             GetDecimalFromAttribute( md_dTempDecimal_4, mSPLDef, "LLD_Panel", "Width" );
      dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
      Left = Left + InterPanelSpace + dTempDecimal_4;
      RESULT = SetCursorNextEntity( mSPLDef, "LLD_Panel", "" );
   } 

   //:   
   //:END

   //:// Close XSL body.
   //:szWriteBuffer = "            </fo:flow>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            </fo:flow>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "         </fo:page-sequence>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "         </fo:page-sequence>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "      </fo:root>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "      </fo:root>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "   </xsl:template>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   </xsl:template>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = ""
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Sub Template.
   //:szWriteBuffer = "   <xsl:template match=^sub^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   <xsl:template match=^sub^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "      <fo:inline vertical-align=^sub^ font-size=^75%^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "      <fo:inline vertical-align=^sub^ font-size=^75%^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "         <xsl:apply-templates select=^*||text()^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "         <xsl:apply-templates select=^*|text()^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "      </fo:inline>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "      </fo:inline>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "   </xsl:template>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   </xsl:template>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Bold Template.
   //:szWriteBuffer = "   <xsl:template match=^bold^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   <xsl:template match=^bold^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "      <fo:inline font-weight=^bold^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "      <fo:inline font-weight=^bold^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "         <xsl:apply-templates select=^*||text()^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "         <xsl:apply-templates select=^*|text()^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "      </fo:inline>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "      </fo:inline>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "   </xsl:template>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   </xsl:template>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Conclude XSL.
   //:szWriteBuffer = ""
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "</xsl:stylesheet>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "</xsl:stylesheet>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }


   //:// Close the XSL file.
   //:SysCloseFile( mSPLDef, lFile, 0 )
   try
   {
       m_KZOEP1AA.SysCloseFile( mSPLDef, lFile, null );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Generate XML. We do this at the end because the process above built data (e.g.  DisplayText attributes) in the mSPLDef object instance.
   //:// szWriteBuffer = "c:\lplr\epamms\xsl\TestLabel.xml"
   //:// CommitOI_ToXML_File( mSPLDef, szWriteBuffer, 0 )
   //:TraceLineS( "Output Xml Filename: ", szXmlName )
   TraceLineS( "Output Xml Filename: ", szXmlName );
   //:GenerateXML_File( mSPLDef, "SubregPhysicalLabelDef", szXmlName )
   omSPLDef_GenerateXML_File( mSPLDef, "SubregPhysicalLabelDef", szXmlName );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:FormatPrintIcons( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                  INTEGER lFile,
//:                  STRING ( 32000 ) szWriteBuffer )

//:   DECIMAL PageHeight
public int 
omSPLDef_FormatPrintIcons( View     mSPLDef,
                           int      lFile,
                           String   szWriteBuffer )
{
   double  PageHeight = 0.0;
   //:DECIMAL PageWidth
   double  PageWidth = 0.0;
   //:DECIMAL TopMargin
   double  TopMargin = 0.0;
   //:DECIMAL LeftMargin
   double  LeftMargin = 0.0;

   //:DECIMAL IconTopLeftTop
   double  IconTopLeftTop = 0.0;
   //:DECIMAL IconBottomLeftTop
   double  IconBottomLeftTop = 0.0;
   //:DECIMAL IconTopRightTop
   double  IconTopRightTop = 0.0;
   //:DECIMAL IconBottomRightTop
   double  IconBottomRightTop = 0.0;
   //:DECIMAL IconCenterTopTop
   double  IconCenterTopTop = 0.0;
   //:DECIMAL IconCenterLeftTop
   double  IconCenterLeftTop = 0.0;
   //:DECIMAL IconCenterRightTop
   double  IconCenterRightTop = 0.0;
   //:DECIMAL IconCenterBottomTop
   double  IconCenterBottomTop = 0.0;

   //:DECIMAL IconTopLeftLeft
   double  IconTopLeftLeft = 0.0;
   //:DECIMAL IconBottomLeftLeft
   double  IconBottomLeftLeft = 0.0;
   //:DECIMAL IconTopRightLeft
   double  IconTopRightLeft = 0.0;
   //:DECIMAL IconBottomRightLeft
   double  IconBottomRightLeft = 0.0;
   //:DECIMAL IconCenterTopLeft
   double  IconCenterTopLeft = 0.0;
   //:DECIMAL IconCenterLeftLeft
   double  IconCenterLeftLeft = 0.0;
   //:DECIMAL IconCenterRightLeft
   double  IconCenterRightLeft = 0.0;
   //:DECIMAL IconCenterBottomLeft
   double  IconCenterBottomLeft = 0.0;

   //:STRING ( 10 ) szTop
   String   szTop = null;
   //:STRING ( 10 ) szLeft
   String   szLeft = null;
   //:STRING ( 30 ) szDateTime
   String   szDateTime = null;
   //:STRING ( 30 ) szDateTimeDisplay
   String   szDateTimeDisplay = null;
   //:STRING ( 90 ) szProductIdentifier
   String   szProductIdentifier = null;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;


   //:// Format the printer icons based on the Page Height and Width.

   //:// There are 4 corner icons and 4 Center line icons, all of which identify the boundaries of the panel images for the page. 
   //:// The Panel area will be positioned 1 inch from the top and 1 inch from the left and 1 inch from the right and one inch from
   //:// the bottom. The 4 center line icons will be in the middle of those corner icons.
   //://
   //:// The 4 corner icons will be positioned:
   //://       TopLeft     Top:  0.2 
   //://                   Left: 0.2
   //://       BottomLeft  Top:  PageHeight - 1.2
   //://                   Left: 0.2
   //://       TopRight    Top:  0.2
   //://                   Left: PageWidth - 1.2
   //://       BottomRight Top:  PageHeight - 1.2
   //://                   Left: PageWidth - 1.2
   //://
   //:// The 4 mid-point icons will be positioned:
   //://       CenterTop    Top:  0.2
   //://                    Left: (PageWidth - 2) / 2 + 0.2
   //://       CenterLeft   Top:  (PageHeight - 2) / 2 + 0.2
   //://                    Left: 0.2
   //://       CenterRight  Top:  (PageHeight - 2) / 2 + 0.2
   //://                    Left: PageWidth - 1.2
   //://       CenterBottom Top:  PageHeight - 1.2
   //://                    Left: (PageWidth - 2) / 2 + 0.2
   //:// 
   //:// In addition, a PDF identifier showing company and date/time is displayed in upper left.

   //:SET CURSOR FIRST mSPLDef.LLD_Panel 
   RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Panel", "" );

   //:// Compute Top and Left icon margin values.

   //:TopMargin       = 1
   TopMargin = 1;
   //:LeftMargin      = 1
   LeftMargin = 1;
   //:PageWidth      = mSPLDef.LLD_Page.Width + 2
   {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
       GetDecimalFromAttribute( md_dTempDecimal_0, mSPLDef, "LLD_Page", "Width" );
   dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
   PageWidth = dTempDecimal_0 + 2;
   //:PageHeight     = mSPLDef.LLD_Page.Height + 2
   {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
       GetDecimalFromAttribute( md_dTempDecimal_1, mSPLDef, "LLD_Page", "Height" );
   dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
   PageHeight = dTempDecimal_1 + 2;

   //:IconTopLeftTop         = 0.2
   IconTopLeftTop = 0.2;
   //:IconTopLeftLeft        = 0.2
   IconTopLeftLeft = 0.2;

   //:IconBottomLeftTop      = PageHeight - 0.8
   IconBottomLeftTop = PageHeight - 0.8;
   //:IconBottomLeftLeft     = 0.2
   IconBottomLeftLeft = 0.2;

   //:IconTopRightTop        = 0.2
   IconTopRightTop = 0.2;
   //:IconTopRightLeft       = PageWidth - 0.8
   IconTopRightLeft = PageWidth - 0.8;

   //:IconBottomRightTop     = PageHeight - 0.8
   IconBottomRightTop = PageHeight - 0.8;
   //:IconBottomRightLeft    = PageWidth - 0.8
   IconBottomRightLeft = PageWidth - 0.8;

   //:IconCenterTopTop       = 0.2
   IconCenterTopTop = 0.2;
   //:IconCenterTopLeft      = (PageWidth - 2) / 2 + 0.2
   IconCenterTopLeft = ( PageWidth - 2 ) / 2 + 0.2;

   //:IconCenterLeftTop      = (PageHeight - 2) / 2 + 0.2
   IconCenterLeftTop = ( PageHeight - 2 ) / 2 + 0.2;
   //:IconCenterLeftLeft     = 0.2
   IconCenterLeftLeft = 0.2;

   //:IconCenterRightTop     = (PageHeight - 2) / 2 + 0.2
   IconCenterRightTop = ( PageHeight - 2 ) / 2 + 0.2;
   //:IconCenterRightLeft    = PageWidth - 0.8
   IconCenterRightLeft = PageWidth - 0.8;

   //:IconCenterBottomTop    = PageHeight - 0.8
   IconCenterBottomTop = PageHeight - 0.8;
   //:IconCenterBottomLeft   = (PageWidth - 2) / 2 + 0.2
   IconCenterBottomLeft = ( PageWidth - 2 ) / 2 + 0.2;

   //:// Generate Icons

   //:szWriteBuffer = "               <!-- Printer Location Icon Generation -->"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <!-- Printer Location Icon Generation -->", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Icon Top Left
   //:szTop  = IconTopLeftTop
    {StringBuilder sb_szTop;
   if ( szTop == null )
      sb_szTop = new StringBuilder( 32 );
   else
      sb_szTop = new StringBuilder( szTop );
      ZeidonStringConvertFromNumber( sb_szTop, 1, 0, 10, 0, IconTopLeftTop, "D" );
   szTop = sb_szTop.toString( );}
   //:szLeft = IconTopLeftLeft
    {StringBuilder sb_szLeft;
   if ( szLeft == null )
      sb_szLeft = new StringBuilder( 32 );
   else
      sb_szLeft = new StringBuilder( szLeft );
      ZeidonStringConvertFromNumber( sb_szLeft, 1, 0, 10, 0, IconTopLeftLeft, "D" );
   szLeft = sb_szLeft.toString( );}
   //:szWriteBuffer = "               <fo:block-container position=^absolute^ top=^" + szTop + "in^ left=^" + szLeft + "in^>" 
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <fo:block-container position=^absolute^ top=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTop, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^ left=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szLeft, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  <fo:block text-align=^left^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <fo:block text-align=^left^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/TopLeft.png^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/TopLeft.png^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  </fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  </fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "               </fo:block-container>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               </fo:block-container>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Icon Top Right
   //:szTop  = IconTopRightTop
    {StringBuilder sb_szTop;
   if ( szTop == null )
      sb_szTop = new StringBuilder( 32 );
   else
      sb_szTop = new StringBuilder( szTop );
      ZeidonStringConvertFromNumber( sb_szTop, 1, 0, 10, 0, IconTopRightTop, "D" );
   szTop = sb_szTop.toString( );}
   //:szLeft = IconTopRightLeft
    {StringBuilder sb_szLeft;
   if ( szLeft == null )
      sb_szLeft = new StringBuilder( 32 );
   else
      sb_szLeft = new StringBuilder( szLeft );
      ZeidonStringConvertFromNumber( sb_szLeft, 1, 0, 10, 0, IconTopRightLeft, "D" );
   szLeft = sb_szLeft.toString( );}
   //:szWriteBuffer = "               <fo:block-container position=^absolute^ top=^" + szTop + "in^ left=^" + szLeft + "in^>" 
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <fo:block-container position=^absolute^ top=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTop, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^ left=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szLeft, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  <fo:block text-align=^left^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <fo:block text-align=^left^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/TopRight.png^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/TopRight.png^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  </fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  </fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "               </fo:block-container>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               </fo:block-container>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Icon Bottom Left
   //:szTop  = IconBottomLeftTop
    {StringBuilder sb_szTop;
   if ( szTop == null )
      sb_szTop = new StringBuilder( 32 );
   else
      sb_szTop = new StringBuilder( szTop );
      ZeidonStringConvertFromNumber( sb_szTop, 1, 0, 10, 0, IconBottomLeftTop, "D" );
   szTop = sb_szTop.toString( );}
   //:szLeft = IconBottomLeftLeft
    {StringBuilder sb_szLeft;
   if ( szLeft == null )
      sb_szLeft = new StringBuilder( 32 );
   else
      sb_szLeft = new StringBuilder( szLeft );
      ZeidonStringConvertFromNumber( sb_szLeft, 1, 0, 10, 0, IconBottomLeftLeft, "D" );
   szLeft = sb_szLeft.toString( );}
   //:szWriteBuffer = "               <fo:block-container position=^absolute^ top=^" + szTop + "in^ left=^" + szLeft + "in^>" 
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <fo:block-container position=^absolute^ top=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTop, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^ left=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szLeft, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  <fo:block text-align=^left^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <fo:block text-align=^left^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/BottomLeft.png^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/BottomLeft.png^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  </fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  </fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "               </fo:block-container>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               </fo:block-container>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Icon Bottom Right
   //:szTop  = IconBottomRightTop
    {StringBuilder sb_szTop;
   if ( szTop == null )
      sb_szTop = new StringBuilder( 32 );
   else
      sb_szTop = new StringBuilder( szTop );
      ZeidonStringConvertFromNumber( sb_szTop, 1, 0, 10, 0, IconBottomRightTop, "D" );
   szTop = sb_szTop.toString( );}
   //:szLeft = IconBottomRightLeft
    {StringBuilder sb_szLeft;
   if ( szLeft == null )
      sb_szLeft = new StringBuilder( 32 );
   else
      sb_szLeft = new StringBuilder( szLeft );
      ZeidonStringConvertFromNumber( sb_szLeft, 1, 0, 10, 0, IconBottomRightLeft, "D" );
   szLeft = sb_szLeft.toString( );}
   //:szWriteBuffer = "               <fo:block-container position=^absolute^ top=^" + szTop + "in^ left=^" + szLeft + "in^>" 
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <fo:block-container position=^absolute^ top=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTop, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^ left=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szLeft, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  <fo:block text-align=^left^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <fo:block text-align=^left^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/BottomRight.png^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/BottomRight.png^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  </fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  </fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "               </fo:block-container>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               </fo:block-container>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Icon Top Center
   //:szTop  = IconCenterTopTop
    {StringBuilder sb_szTop;
   if ( szTop == null )
      sb_szTop = new StringBuilder( 32 );
   else
      sb_szTop = new StringBuilder( szTop );
      ZeidonStringConvertFromNumber( sb_szTop, 1, 0, 10, 0, IconCenterTopTop, "D" );
   szTop = sb_szTop.toString( );}
   //:szLeft = IconCenterTopLeft
    {StringBuilder sb_szLeft;
   if ( szLeft == null )
      sb_szLeft = new StringBuilder( 32 );
   else
      sb_szLeft = new StringBuilder( szLeft );
      ZeidonStringConvertFromNumber( sb_szLeft, 1, 0, 10, 0, IconCenterTopLeft, "D" );
   szLeft = sb_szLeft.toString( );}
   //:szWriteBuffer = "               <fo:block-container position=^absolute^ top=^" + szTop + "in^ left=^" + szLeft + "in^>" 
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <fo:block-container position=^absolute^ top=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTop, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^ left=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szLeft, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  <fo:block text-align=^left^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <fo:block text-align=^left^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/CenterH.png^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/CenterH.png^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  </fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  </fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "               </fo:block-container>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               </fo:block-container>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Icon Bottom Center
   //:szTop  = IconCenterBottomTop
    {StringBuilder sb_szTop;
   if ( szTop == null )
      sb_szTop = new StringBuilder( 32 );
   else
      sb_szTop = new StringBuilder( szTop );
      ZeidonStringConvertFromNumber( sb_szTop, 1, 0, 10, 0, IconCenterBottomTop, "D" );
   szTop = sb_szTop.toString( );}
   //:szLeft = IconCenterBottomLeft
    {StringBuilder sb_szLeft;
   if ( szLeft == null )
      sb_szLeft = new StringBuilder( 32 );
   else
      sb_szLeft = new StringBuilder( szLeft );
      ZeidonStringConvertFromNumber( sb_szLeft, 1, 0, 10, 0, IconCenterBottomLeft, "D" );
   szLeft = sb_szLeft.toString( );}
   //:szWriteBuffer = "               <fo:block-container position=^absolute^ top=^" + szTop + "in^ left=^" + szLeft + "in^>" 
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <fo:block-container position=^absolute^ top=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTop, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^ left=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szLeft, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  <fo:block text-align=^left^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <fo:block text-align=^left^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/CenterH.png^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/CenterH.png^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  </fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  </fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "               </fo:block-container>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               </fo:block-container>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Icon Left Center
   //:szTop  = IconCenterLeftTop
    {StringBuilder sb_szTop;
   if ( szTop == null )
      sb_szTop = new StringBuilder( 32 );
   else
      sb_szTop = new StringBuilder( szTop );
      ZeidonStringConvertFromNumber( sb_szTop, 1, 0, 10, 0, IconCenterLeftTop, "D" );
   szTop = sb_szTop.toString( );}
   //:szLeft = IconCenterLeftLeft
    {StringBuilder sb_szLeft;
   if ( szLeft == null )
      sb_szLeft = new StringBuilder( 32 );
   else
      sb_szLeft = new StringBuilder( szLeft );
      ZeidonStringConvertFromNumber( sb_szLeft, 1, 0, 10, 0, IconCenterLeftLeft, "D" );
   szLeft = sb_szLeft.toString( );}
   //:szWriteBuffer = "               <fo:block-container position=^absolute^ top=^" + szTop + "in^ left=^" + szLeft + "in^>" 
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <fo:block-container position=^absolute^ top=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTop, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^ left=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szLeft, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  <fo:block text-align=^left^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <fo:block text-align=^left^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/CenterV.png^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/CenterV.png^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  </fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  </fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "               </fo:block-container>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               </fo:block-container>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Icon Right Center
   //:szTop  = IconCenterRightTop
    {StringBuilder sb_szTop;
   if ( szTop == null )
      sb_szTop = new StringBuilder( 32 );
   else
      sb_szTop = new StringBuilder( szTop );
      ZeidonStringConvertFromNumber( sb_szTop, 1, 0, 10, 0, IconCenterRightTop, "D" );
   szTop = sb_szTop.toString( );}
   //:szLeft = IconCenterRightLeft
    {StringBuilder sb_szLeft;
   if ( szLeft == null )
      sb_szLeft = new StringBuilder( 32 );
   else
      sb_szLeft = new StringBuilder( szLeft );
      ZeidonStringConvertFromNumber( sb_szLeft, 1, 0, 10, 0, IconCenterRightLeft, "D" );
   szLeft = sb_szLeft.toString( );}
   //:szWriteBuffer = "               <fo:block-container position=^absolute^ top=^" + szTop + "in^ left=^" + szLeft + "in^>" 
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <fo:block-container position=^absolute^ top=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTop, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^ left=^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szLeft, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  <fo:block text-align=^left^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <fo:block text-align=^left^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/CenterV.png^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                     <fo:external-graphic src=^c:/lplr/epamms/xsl/images/CenterV.png^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  </fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  </fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "               </fo:block-container>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               </fo:block-container>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Company and Date/Time Identifier

   //:GetStringFromAttributeByContext( szDateTimeDisplay, mSPLDef, "SubregPhysicalLabelDef", "wDateTime", "YYYY/MM/DD HH:MM:SS.S AM", 30 )
   {StringBuilder sb_szDateTimeDisplay;
   if ( szDateTimeDisplay == null )
      sb_szDateTimeDisplay = new StringBuilder( 32 );
   else
      sb_szDateTimeDisplay = new StringBuilder( szDateTimeDisplay );
       GetStringFromAttributeByContext( sb_szDateTimeDisplay, mSPLDef, "SubregPhysicalLabelDef", "wDateTime", "YYYY/MM/DD HH:MM:SS.S AM", 30 );
   szDateTimeDisplay = sb_szDateTimeDisplay.toString( );}
   //://szDateTimeDisplay = "01/01/2000 00:00:00.0 AM"
   //:szProductIdentifier = mSPLDef.SubregOrganization.Name + "   " + szDateTimeDisplay
   {StringBuilder sb_szProductIdentifier;
   if ( szProductIdentifier == null )
      sb_szProductIdentifier = new StringBuilder( 32 );
   else
      sb_szProductIdentifier = new StringBuilder( szProductIdentifier );
       GetStringFromAttribute( sb_szProductIdentifier, mSPLDef, "SubregOrganization", "Name" );
   szProductIdentifier = sb_szProductIdentifier.toString( );}
    {StringBuilder sb_szProductIdentifier;
   if ( szProductIdentifier == null )
      sb_szProductIdentifier = new StringBuilder( 32 );
   else
      sb_szProductIdentifier = new StringBuilder( szProductIdentifier );
      ZeidonStringConcat( sb_szProductIdentifier, 1, 0, "   ", 1, 0, 91 );
   szProductIdentifier = sb_szProductIdentifier.toString( );}
    {StringBuilder sb_szProductIdentifier;
   if ( szProductIdentifier == null )
      sb_szProductIdentifier = new StringBuilder( 32 );
   else
      sb_szProductIdentifier = new StringBuilder( szProductIdentifier );
      ZeidonStringConcat( sb_szProductIdentifier, 1, 0, szDateTimeDisplay, 1, 0, 91 );
   szProductIdentifier = sb_szProductIdentifier.toString( );}
   //:szWriteBuffer = "               <fo:block-container position=^absolute^ top=^0.4in^ left=^1.0in^>" 
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <fo:block-container position=^absolute^ top=^0.4in^ left=^1.0in^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  <fo:block font-size=^7pt^ letter-spacing=^.2em^ text-align=^left^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <fo:block font-size=^7pt^ letter-spacing=^.2em^ text-align=^left^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                     " + szProductIdentifier
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                     ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szProductIdentifier, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "                  </fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  </fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = "               </fo:block-container>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               </fo:block-container>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:FormatContinueBlock( VIEW mSPLDefPDF BASED ON LOD mSPLDef,
//:                     VIEW mSPLDef      BASED ON LOD mSPLDef,
//:                     INTEGER lFile,
//:                     STRING ( 50 )    szPassedBlanks,
//:                     STRING ( 32000 ) szWriteBuffer,
//:                     STRING ( 1 )     szContinueType )

//:   //VIEW mSPLDefPDFPrev BASED ON LOD mSPLDef
//:   STRING ( 32000 )  szStatementText
public int 
omSPLDef_FormatContinueBlock( View     mSPLDefPDF,
                              View     mSPLDef,
                              int      lFile,
                              String   szPassedBlanks,
                              String   szWriteBuffer,
                              String   szContinueType )
{
   String   szStatementText = null;
   //:STRING ( 32000 )  szTemporaryText
   String   szTemporaryText = null;
   //:STRING ( 256 )    szStatementTitle
   String   szStatementTitle = null;
   //:STRING ( 10 )     szSeparatorCharacters
   String   szSeparatorCharacters = null;
   //:STRING ( 2 )      szTitleFormat
   String   szTitleFormat = null;
   //:STRING ( 2 )      szStatementFormat
   String   szStatementFormat = null;
   //:STRING ( 50 )     szLeadingBlanks
   String   szLeadingBlanks = null;
   //:STRING ( 50 )     szDisplayStatementName
   String   szDisplayStatementName = null;
   //:STRING ( 50 )     szDisplaySectionName
   String   szDisplaySectionName = null;
   //:STRING ( 3 )      szDisplaySectionSuffix
   String   szDisplaySectionSuffix = null;
   //:SHORT             nRC
   int      nRC = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      RESULT = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;


   //:// Process Storage & Disposal, Directions for Use or Marketing Continuation Statements from previous Panel.

   //:szLeadingBlanks = szPassedBlanks
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringCopy( sb_szLeadingBlanks, 1, 0, szPassedBlanks, 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}

   //:// Create Block Container. Top is overridden to top of Panel.
   //:TraceLineS( "$$$$ Begin of FormatContinueBlock", "" )
   TraceLineS( "$$$$ Begin of FormatContinueBlock", "" );

   //:FormatBlockContainer( mSPLDefPDF, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, "" )
   omSPLDef_FormatBlockContainer( mSPLDefPDF, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, "" );

   //:// If the continuation is to another Panel, put out Continuation text.
   //:IF szContinueType = "P"
   if ( ZeidonStringCompare( szContinueType, 1, 0, "P", 1, 0, 2 ) == 0 )
   { 
      //:// Add the Continuation verbage to the current Panel.
      //:szWriteBuffer = szLeadingBlanks + "   <fo:block margin-bottom=^.05in^>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <fo:block margin-bottom=^.05in^>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:IF mSPLDefPDF.LLD_Block.LLD_SectionType = "DirectionsForUse"
      if ( CompareAttributeToString( mSPLDefPDF, "LLD_Block", "LLD_SectionType", "DirectionsForUse" ) == 0 )
      { 
         //:szWriteBuffer = szLeadingBlanks + "      " + mSPLDefPDF.SPLD_LLD.ContNextPageTextDirForUse 
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      ", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 32001, mSPLDefPDF, "SPLD_LLD", "ContNextPageTextDirForUse", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTempString_0, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szWriteBuffer = szLeadingBlanks + "      " + mSPLDefPDF.SPLD_LLD.ContNextPageTextMarketing 
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      ", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 32001, mSPLDefPDF, "SPLD_LLD", "ContNextPageTextMarketing", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTempString_1, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:szWriteBuffer = szLeadingBlanks + "   </fo:block>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   </fo:block>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }
   } 

   //:END

   //:szStatementFormat     = "SP"
    {StringBuilder sb_szStatementFormat;
   if ( szStatementFormat == null )
      sb_szStatementFormat = new StringBuilder( 32 );
   else
      sb_szStatementFormat = new StringBuilder( szStatementFormat );
      ZeidonStringCopy( sb_szStatementFormat, 1, 0, "SP", 1, 0, 3 );
   szStatementFormat = sb_szStatementFormat.toString( );}
   //:szSeparatorCharacters = ", "
    {StringBuilder sb_szSeparatorCharacters;
   if ( szSeparatorCharacters == null )
      sb_szSeparatorCharacters = new StringBuilder( 32 );
   else
      sb_szSeparatorCharacters = new StringBuilder( szSeparatorCharacters );
      ZeidonStringCopy( sb_szSeparatorCharacters, 1, 0, ", ", 1, 0, 11 );
   szSeparatorCharacters = sb_szSeparatorCharacters.toString( );}

   //:// Increment Display Section Suffix and Initialize DisplaySection entry.
   //:mSPLDef.SubregPhysicalLabelDef.wLastDisplaySuffixCount = mSPLDef.SubregPhysicalLabelDef.wLastDisplaySuffixCount + 1
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mSPLDef, "SubregPhysicalLabelDef", "wLastDisplaySuffixCount" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   lTempInteger_3 = lTempInteger_2 + 1;
   SetAttributeFromInteger( mSPLDef, "SubregPhysicalLabelDef", "wLastDisplaySuffixCount", lTempInteger_3 );
   //:szDisplaySectionSuffix = mSPLDef.SubregPhysicalLabelDef.wLastDisplaySuffixCount 
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
   StringBuilder sb_szDisplaySectionSuffix;
   if ( szDisplaySectionSuffix == null )
      sb_szDisplaySectionSuffix = new StringBuilder( 32 );
   else
      sb_szDisplaySectionSuffix = new StringBuilder( szDisplaySectionSuffix );
       GetVariableFromAttribute( sb_szDisplaySectionSuffix, mi_lTempInteger_4, 'S', 4, mSPLDef, "SubregPhysicalLabelDef", "wLastDisplaySuffixCount", "", 0 );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );
   szDisplaySectionSuffix = sb_szDisplaySectionSuffix.toString( );}
   //:szDisplaySectionName = "DisplaySection" + szDisplaySectionSuffix
    {StringBuilder sb_szDisplaySectionName;
   if ( szDisplaySectionName == null )
      sb_szDisplaySectionName = new StringBuilder( 32 );
   else
      sb_szDisplaySectionName = new StringBuilder( szDisplaySectionName );
      ZeidonStringCopy( sb_szDisplaySectionName, 1, 0, "DisplaySection", 1, 0, 51 );
   szDisplaySectionName = sb_szDisplaySectionName.toString( );}
    {StringBuilder sb_szDisplaySectionName;
   if ( szDisplaySectionName == null )
      sb_szDisplaySectionName = new StringBuilder( 32 );
   else
      sb_szDisplaySectionName = new StringBuilder( szDisplaySectionName );
      ZeidonStringConcat( sb_szDisplaySectionName, 1, 0, szDisplaySectionSuffix, 1, 0, 51 );
   szDisplaySectionName = sb_szDisplaySectionName.toString( );}
   //:CREATE ENTITY mSPLDef.DisplaySection 
   RESULT = CreateEntity( mSPLDef, "DisplaySection", zPOS_AFTER );
   //:mSPLDef.DisplaySection.Type = "SPLD_ContinuationSection"
   SetAttributeFromString( mSPLDef, "DisplaySection", "Type", "SPLD_ContinuationSection" );
   //:mSPLDef.DisplaySection.XML_SectionName = szDisplaySectionName
   SetAttributeFromString( mSPLDef, "DisplaySection", "XML_SectionName", szDisplaySectionName );

   //:// If this is a continuation to the next Panel, we need to use the last block on the previous Panel for formatting, so create that view.
   //:/*CreateViewFromView( mSPLDefPDFPrev, mSPLDefPDF )
   //:NAME VIEW mSPLDefPDFPrev "mSPLDefPDFPrev"
   //:IF szContinueType = "P"
   //:   SET CURSOR PREVIOUS mSPLDefPDFPrev.LLD_Panel 
   //:   SET CURSOR LAST mSPLDefPDFPrev.LLD_Block
   //:END*/

   //:// Format each Statement, with Title, if requested.
   //:FOR EACH mSPLDef.ContinuationStatement 
   RESULT = SetCursorFirstEntity( mSPLDef, "ContinuationStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Create the Display Statement entry, unless this Statement is a full continuation on next page.
      //:mSPLDef.SubregPhysicalLabelDef.wLastDisplaySuffixCount = mSPLDef.SubregPhysicalLabelDef.wLastDisplaySuffixCount + 1
      {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
             GetIntegerFromAttribute( mi_lTempInteger_5, mSPLDef, "SubregPhysicalLabelDef", "wLastDisplaySuffixCount" );
      lTempInteger_5 = mi_lTempInteger_5.intValue( );}
      lTempInteger_6 = lTempInteger_5 + 1;
      SetAttributeFromInteger( mSPLDef, "SubregPhysicalLabelDef", "wLastDisplaySuffixCount", lTempInteger_6 );
      //:szDisplaySectionSuffix = mSPLDef.SubregPhysicalLabelDef.wLastDisplaySuffixCount 
      {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
      StringBuilder sb_szDisplaySectionSuffix;
      if ( szDisplaySectionSuffix == null )
         sb_szDisplaySectionSuffix = new StringBuilder( 32 );
      else
         sb_szDisplaySectionSuffix = new StringBuilder( szDisplaySectionSuffix );
             GetVariableFromAttribute( sb_szDisplaySectionSuffix, mi_lTempInteger_7, 'S', 4, mSPLDef, "SubregPhysicalLabelDef", "wLastDisplaySuffixCount", "", 0 );
      lTempInteger_7 = mi_lTempInteger_7.intValue( );
      szDisplaySectionSuffix = sb_szDisplaySectionSuffix.toString( );}
      //:szDisplayStatementName = "DisplayStatement" + szDisplaySectionSuffix
       {StringBuilder sb_szDisplayStatementName;
      if ( szDisplayStatementName == null )
         sb_szDisplayStatementName = new StringBuilder( 32 );
      else
         sb_szDisplayStatementName = new StringBuilder( szDisplayStatementName );
            ZeidonStringCopy( sb_szDisplayStatementName, 1, 0, "DisplayStatement", 1, 0, 51 );
      szDisplayStatementName = sb_szDisplayStatementName.toString( );}
       {StringBuilder sb_szDisplayStatementName;
      if ( szDisplayStatementName == null )
         sb_szDisplayStatementName = new StringBuilder( 32 );
      else
         sb_szDisplayStatementName = new StringBuilder( szDisplayStatementName );
            ZeidonStringConcat( sb_szDisplayStatementName, 1, 0, szDisplaySectionSuffix, 1, 0, 51 );
      szDisplayStatementName = sb_szDisplayStatementName.toString( );}
      //:CREATE ENTITY mSPLDef.DisplayStatement
      RESULT = CreateEntity( mSPLDef, "DisplayStatement", zPOS_AFTER );
      //:mSPLDef.DisplayStatement.XML_StatementName = szDisplayStatementName
      SetAttributeFromString( mSPLDef, "DisplayStatement", "XML_StatementName", szDisplayStatementName );

      //:szStatementText  = mSPLDef.ContinuationStatement.Text 
      {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
      StringBuilder sb_szStatementText;
      if ( szStatementText == null )
         sb_szStatementText = new StringBuilder( 32 );
      else
         sb_szStatementText = new StringBuilder( szStatementText );
             GetVariableFromAttribute( sb_szStatementText, mi_lTempInteger_8, 'S', 32001, mSPLDef, "ContinuationStatement", "Text", "", 0 );
      lTempInteger_8 = mi_lTempInteger_8.intValue( );
      szStatementText = sb_szStatementText.toString( );}
      //:szStatementTitle = mSPLDef.ContinuationStatement.Title 
      {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
      StringBuilder sb_szStatementTitle;
      if ( szStatementTitle == null )
         sb_szStatementTitle = new StringBuilder( 32 );
      else
         sb_szStatementTitle = new StringBuilder( szStatementTitle );
             GetVariableFromAttribute( sb_szStatementTitle, mi_lTempInteger_9, 'S', 257, mSPLDef, "ContinuationStatement", "Title", "", 0 );
      lTempInteger_9 = mi_lTempInteger_9.intValue( );
      szStatementTitle = sb_szStatementTitle.toString( );}
      //:IF szTitleFormat = "PU"
      if ( ZeidonStringCompare( szTitleFormat, 1, 0, "PU", 1, 0, 3 ) == 0 )
      { 



         //:ELSE
      } 
      else
      { 
         //:   
         //:IF szStatementFormat = "SN"
         if ( ZeidonStringCompare( szStatementFormat, 1, 0, "SN", 1, 0, 3 ) == 0 )
         { 
         } 


         //:   // This will be skipped for now.
         //:   // SN - Separate Numbered Paragraph
         //:   // Process each Statement within the Section, indenting any text that follows a number.
         //:   // We will do this by determining if the first character in the text is a number.
         //:   // If it is not, we'll simply format as for SP above.
         //:   // If it is, we'll find the first character after any spaces following the number and indent that text, after the number.


         //:END
      } 

      //:END

      //:// Combine Title in text if specified.
      //:IF szStatementTitle != "" AND szTitleFormat = "CT"
      if ( ZeidonStringCompare( szStatementTitle, 1, 0, "", 1, 0, 257 ) != 0 && ZeidonStringCompare( szTitleFormat, 1, 0, "CT", 1, 0, 3 ) == 0 )
      { 
         //:// Title is combined with Text.
         //:szTemporaryText = szStatementText
          {StringBuilder sb_szTemporaryText;
         if ( szTemporaryText == null )
            sb_szTemporaryText = new StringBuilder( 32 );
         else
            sb_szTemporaryText = new StringBuilder( szTemporaryText );
                  ZeidonStringCopy( sb_szTemporaryText, 1, 0, szStatementText, 1, 0, 32001 );
         szTemporaryText = sb_szTemporaryText.toString( );}
         //:szStatementText = szStatementTitle + " " + szTemporaryText
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                  ZeidonStringCopy( sb_szStatementText, 1, 0, szStatementTitle, 1, 0, 32001 );
         szStatementText = sb_szStatementText.toString( );}
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                  ZeidonStringConcat( sb_szStatementText, 1, 0, " ", 1, 0, 32001 );
         szStatementText = sb_szStatementText.toString( );}
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                  ZeidonStringConcat( sb_szStatementText, 1, 0, szTemporaryText, 1, 0, 32001 );
         szStatementText = sb_szStatementText.toString( );}
         //:ELSE
      } 
      else
      { 
         //:// Format Title, if it exists.
         //:IF szStatementTitle != "" 
         if ( ZeidonStringCompare( szStatementTitle, 1, 0, "", 1, 0, 257 ) != 0 )
         { 
            //:szWriteBuffer = szLeadingBlanks + "   <fo:block "
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <fo:block ", 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:AddFormatToSpecialText( mSPLDefPDF, "Title", szWriteBuffer )
            {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                         omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Title", sb_szWriteBuffer );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
            try
            {
                {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            };
            }
            catch ( Exception e )
            {
               throw ZeidonException.wrapException( e );
            }

            //://szWriteBuffer = szLeadingBlanks + "      " + szStatementTitle
            //:szWriteBuffer = szLeadingBlanks + "      <xsl:apply-templates select=^SubregPhysicalLabelDef/" 
            //:                                + szDisplaySectionName + "/" + szDisplayStatementName + "/Title^/>"
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      <xsl:apply-templates select=^SubregPhysicalLabelDef/", 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szDisplaySectionName, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "/", 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szDisplayStatementName, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "/Title^/>", 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
            try
            {
                {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            };
            }
            catch ( Exception e )
            {
               throw ZeidonException.wrapException( e );
            }

            //:szWriteBuffer = szLeadingBlanks + "   </fo:block>"
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   </fo:block>", 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
            try
            {
                {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            };
            }
            catch ( Exception e )
            {
               throw ZeidonException.wrapException( e );
            }

            //:mSPLDef.DisplayStatement.Title = szStatementTitle
            SetAttributeFromString( mSPLDef, "DisplayStatement", "Title", szStatementTitle );
         } 

         //:END
      } 

      //:END
      //:// Format Statement Text
      //:szWriteBuffer = szLeadingBlanks + "   <fo:block "
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <fo:block ", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:AddFormatToSpecialText( mSPLDefPDF, "Text", szWriteBuffer )
      {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
             omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Text", sb_szWriteBuffer );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //://szWriteBuffer = szLeadingBlanks + "      " + szStatementText
      //:szWriteBuffer = szLeadingBlanks + "      <xsl:apply-templates select=^SubregPhysicalLabelDef/" 
      //:                                      + szDisplaySectionName + "/" + szDisplayStatementName + "/Text^/>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      <xsl:apply-templates select=^SubregPhysicalLabelDef/", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szDisplaySectionName, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "/", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szDisplayStatementName, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "/Text^/>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:szWriteBuffer = szLeadingBlanks + "   </fo:block>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   </fo:block>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:mSPLDef.DisplayStatement.Text = szStatementText
      SetAttributeFromString( mSPLDef, "DisplayStatement", "Text", szStatementText );
      RESULT = SetCursorNextEntity( mSPLDef, "ContinuationStatement", "" );
   } 

   //:      
   //:END
   //://DropView( mSPLDefPDFPrev )

   //:// Process Termination.
   //:szWriteBuffer = szLeadingBlanks + "</fo:block-container>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block-container>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Remove any existing Continuation Statements.
   //:FOR EACH mSPLDef.ContinuationStatement 
   RESULT = SetCursorFirstEntity( mSPLDef, "ContinuationStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mSPLDef.ContinuationStatement NONE 
      RESULT = DeleteEntity( mSPLDef, "ContinuationStatement", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "ContinuationStatement", "" );
   } 

   //:END
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:ProcessPDF_Blocks( VIEW mSPLDef    BASED ON LOD mSPLDef,
//:                   VIEW mSPLDefPDF BASED ON LOD mSPLDef,
//:                   INTEGER lFile,
//:                   STRING ( 50 )    szPassedBlanks,
//:                   STRING ( 32000 ) szWriteBuffer )

//:   VIEW mSPLDefTopBlock BASED ON LOD mSPLDef
public int 
omSPLDef_ProcessPDF_Blocks( View     mSPLDef,
                            View     mSPLDefPDF,
                            int      lFile,
                            String   szPassedBlanks,
                            String   szWriteBuffer )
{
   zVIEW    mSPLDefTopBlock = new zVIEW( );
   //:VIEW mSPLDef2        BASED ON LOD mSPLDef
   zVIEW    mSPLDef2 = new zVIEW( );
   //:STRING ( 50 ) szLeadingBlanks
   String   szLeadingBlanks = null;
   //:STRING ( 50 ) szBlockBlanks
   String   szBlockBlanks = null;
   //:STRING ( 10 ) szHeight
   String   szHeight = null;
   //:STRING ( 10 ) szWidth
   String   szWidth = null;
   //:STRING ( 10 ) szTop
   String   szTop = null;
   //:STRING ( 50 ) szSectionType
   String   szSectionType = null;
   //:STRING ( 50 ) szLeft
   String   szLeft = null;
   //:STRING ( 90 ) szTitle
   String   szTitle = null;
   //:STRING ( 90 ) szMsg
   String   szMsg = null;
   //:SHORT         nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_7 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_8 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_9 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_10 = 0;


   //:// Process each LLD_Block Container and subobject data.

   //:// Note that mSPLDefPDF is pointing to the LLD Panel and Block substructure, which recursively steps
   //:// to a subblock, while mSPLDef points to the rest of the structure at the root level.
   //:// mSPLDefTopBlock will be pointing to the top Block structure, which will be the same as mSPLDefPDF unless
   //:// we've stepped down a level to SubBlock.


   //:szLeadingBlanks = szPassedBlanks + "   "
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringCopy( sb_szLeadingBlanks, 1, 0, szPassedBlanks, 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringConcat( sb_szLeadingBlanks, 1, 0, "   ", 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}

   //:// If the first LLD_Block is a Continuation LLD_Block from the previous Panel, process it first.
   //:IF mSPLDefPDF.LLD_Block.ContinuationBlockFlag = "Y" AND mSPLDefPDF.ContinuationStatement EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSPLDefPDF, "ContinuationStatement" );
   if ( CompareAttributeToString( mSPLDefPDF, "LLD_Block", "ContinuationBlockFlag", "Y" ) == 0 && lTempInteger_0 == 0 )
   { 
      //:FormatContinueBlock( mSPLDefPDF, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, "P" )
      omSPLDef_FormatContinueBlock( mSPLDefPDF, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, "P" );
   } 

   //:END

   //:FOR EACH mSPLDefPDF.LLD_Block 
   RESULT = SetCursorFirstEntity( mSPLDefPDF, "LLD_Block", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:IF mSPLDefPDF.LLD_Block.ContinuationBlockFlag = "Y" 
      if ( CompareAttributeToString( mSPLDefPDF, "LLD_Block", "ContinuationBlockFlag", "Y" ) == 0 )
      { 

         //:// This is a continuation from the last Block, so format if there is continuation data.
         //:// Note that mSPLDef is pointing to the Panel entity that holds the continuation statements.
         //:SET CURSOR FIRST mSPLDef.ContinuationStatement 
         RESULT = SetCursorFirstEntity( mSPLDef, "ContinuationStatement", "" );
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:FormatContinueBlock( mSPLDefPDF, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, "B" )
            omSPLDef_FormatContinueBlock( mSPLDefPDF, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, "B" );

            //:// Remove continuation entries.
            //:FOR EACH mSPLDef.ContinuationStatement 
            RESULT = SetCursorFirstEntity( mSPLDef, "ContinuationStatement", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:DELETE ENTITY mSPLDef.ContinuationStatement NONE 
               RESULT = DeleteEntity( mSPLDef, "ContinuationStatement", zREPOS_NONE );
               RESULT = SetCursorNextEntity( mSPLDef, "ContinuationStatement", "" );
            } 

            //:END
         } 

         //:END

         //:ELSE
      } 
      else
      { 

         //:// Create LLD_Block Container.
         //:FormatBlockContainer( mSPLDefPDF, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, "" )
         omSPLDef_FormatBlockContainer( mSPLDefPDF, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, "" );

         //:// Processing depends on which kind of LLD_Block this is.
         //:// If it is just a Container, then format the LLD_Block and process each LLD_SubBlock.
         //:// Otherwise, go to the proper suboperation for processing the type of LLD_Block.
         //:IF mSPLDefPDF.LLD_SubBlock EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mSPLDefPDF, "LLD_SubBlock" );
         if ( lTempInteger_1 == 0 )
         { 
            //:// Process each LLD_SubBlock as regular Block, after stepping into subobject.

            //:// Create a view to the top Block for debug purposes.
            //:CreateViewFromView( mSPLDefTopBlock, mSPLDefPDF )
            CreateViewFromView( mSPLDefTopBlock, mSPLDefPDF );

            //:SetViewToSubobject( mSPLDefPDF, "LLD_SubBlock" )
            SetViewToSubobject( mSPLDefPDF, "LLD_SubBlock" );
            //:nRC = ProcessPDF_Blocks( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer )
            nRC = omSPLDef_ProcessPDF_Blocks( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer );
            //:IF nRC = 2
            if ( nRC == 2 )
            { 
               //:RETURN 2
               if(8==8)return( 2 );
            } 

            //:END
            //:ResetViewFromSubobject( mSPLDefPDF )
            ResetViewFromSubobject( mSPLDefPDF );

            //:GET VIEW mSPLDefTopBlock NAMED "mSPLDefTopBlock"
            RESULT = GetViewByName( mSPLDefTopBlock, "mSPLDefTopBlock", mSPLDef, zLEVEL_TASK );
            //:IF RESULT >= 0
            if ( RESULT >= 0 )
            { 
               //:DropView( mSPLDefTopBlock )
               DropView( mSPLDefTopBlock );
            } 

            //:END
            //:// Temporary code to process LLD_Block without recursive subobject LLD_SubBlock.
            //://OrderEntityForView( mSPLDefPDF, "LLD_SubBlock", "Left A wComputedTopPosition A" )
            //:/*ACTIVATE mSPLDefPDF2 EMPTY 
            //:NAME VIEW mSPLDefPDF2 "mSPLDefPDF2"
            //:CREATE ENTITY mSPLDefPDF2.SubregPhysicalLabelDef 
            //:CREATE ENTITY mSPLDefPDF2.SPLD_LLD 
            //:CREATE ENTITY mSPLDefPDF2.LLD_Page
            //:CREATE ENTITY mSPLDefPDF2.LLD_Panel 
            //:FOR EACH mSPLDefPDF.LLD_SubBlock 
            //:   CREATE ENTITY mSPLDefPDF2.LLD_Block 
            //:   SetMatchingAttributesByName( mSPLDefPDF2, "LLD_Block", mSPLDefPDF, "LLD_SubBlock", zSET_ALL ) 
            //:   FOR EACH mSPLDefPDF.LLD_SpecialSectionAttributeSub 
            //:      CREATE ENTITY mSPLDefPDF2.LLD_SpecialSectionAttribute 
            //:      mSPLDefPDF2.LLD_SpecialSectionAttribute.Name = mSPLDefPDF.LLD_SpecialSectionAttributeSub.Name 
            //:      CREATE ENTITY mSPLDefPDF2.LLD_SpecialSectionAttrBlock 
            //:      SetMatchingAttributesByName( mSPLDefPDF2, "LLD_SpecialSectionAttrBlock", mSPLDefPDF, "LLD_SpecialSectionAttrSubBlock", zSET_ALL ) 
            //:   END
            //:END
            //:ProcessPDF_Blocks( mSPLDef, mSPLDefPDF2, lFile, szLeadingBlanks, szWriteBuffer )
            //:DropObjectInstance( mSPLDefPDF2 )*/
            //:ELSE
         } 
         else
         { 
            //:// Determine what kind of LLD_Block it is and go to process accordingly.

            //:szSectionType = mSPLDefPDF.LLD_Block.LLD_SectionType 
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szSectionType;
            if ( szSectionType == null )
               sb_szSectionType = new StringBuilder( 32 );
            else
               sb_szSectionType = new StringBuilder( szSectionType );
                         GetVariableFromAttribute( sb_szSectionType, mi_lTempInteger_2, 'S', 51, mSPLDefPDF, "LLD_Block", "LLD_SectionType", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szSectionType = sb_szSectionType.toString( );}

            //:// IMAGE
            //:IF szSectionType = "Graphic"
            if ( ZeidonStringCompare( szSectionType, 1, 0, "Graphic", 1, 0, 51 ) == 0 )
            { 
               //:szHeight = mSPLDefPDF.LLD_Block.Height 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szHeight;
               if ( szHeight == null )
                  sb_szHeight = new StringBuilder( 32 );
               else
                  sb_szHeight = new StringBuilder( szHeight );
                               GetVariableFromAttribute( sb_szHeight, mi_lTempInteger_3, 'S', 11, mSPLDefPDF, "LLD_Block", "Height", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szHeight = sb_szHeight.toString( );}
               //:szWidth  = mSPLDefPDF.LLD_Block.Width 
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szWidth;
               if ( szWidth == null )
                  sb_szWidth = new StringBuilder( 32 );
               else
                  sb_szWidth = new StringBuilder( szWidth );
                               GetVariableFromAttribute( sb_szWidth, mi_lTempInteger_4, 'S', 11, mSPLDefPDF, "LLD_Block", "Width", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szWidth = sb_szWidth.toString( );}
               //:szTop    = mSPLDefPDF.LLD_Block.Top 
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szTop;
               if ( szTop == null )
                  sb_szTop = new StringBuilder( 32 );
               else
                  sb_szTop = new StringBuilder( szTop );
                               GetVariableFromAttribute( sb_szTop, mi_lTempInteger_5, 'S', 11, mSPLDefPDF, "LLD_Block", "Top", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szTop = sb_szTop.toString( );}
               //:szLeft   = mSPLDefPDF.LLD_Block.Left 
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
               StringBuilder sb_szLeft;
               if ( szLeft == null )
                  sb_szLeft = new StringBuilder( 32 );
               else
                  sb_szLeft = new StringBuilder( szLeft );
                               GetVariableFromAttribute( sb_szLeft, mi_lTempInteger_6, 'S', 51, mSPLDefPDF, "LLD_Block", "Left", "", 0 );
               lTempInteger_6 = mi_lTempInteger_6.intValue( );
               szLeft = sb_szLeft.toString( );}

               //:szWriteBuffer = "                     <fo:block text-align=^left^>"
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                     <fo:block text-align=^left^>", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
               try
               {
                   {
                ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
               };
               }
               catch ( Exception e )
               {
                  throw ZeidonException.wrapException( e );
               }
               //:szWriteBuffer = "                        <fo:external-graphic src=^c:/lplr/epamms/xsl/images/" + mSPLDefPDF.LLD_Block.ImageName +
               //:                "^ height=^" + szHeight + "in^ width=^" + szWidth + "in^ content-height=^scale-to-fit^ content-width=^scale-to-fit^/>"
               {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_7, 'S', 255, mSPLDefPDF, "LLD_Block", "ImageName", "", 0 );
               lTempInteger_7 = mi_lTempInteger_7.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                        <fo:external-graphic src=^c:/lplr/epamms/xsl/images/", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTempString_0, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^ height=^", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szHeight, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^ width=^", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szWidth, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^ content-height=^scale-to-fit^ content-width=^scale-to-fit^/>", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
               try
               {
                   {
                ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
               };
               }
               catch ( Exception e )
               {
                  throw ZeidonException.wrapException( e );
               }

               //:szWriteBuffer = "                     </fo:block>"
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                     </fo:block>", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
               try
               {
                   {
                ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
               };
               }
               catch ( Exception e )
               {
                  throw ZeidonException.wrapException( e );
               }

               //:ELSE
            } 
            else
            { 

               //:// DIRECTIONS FOR USE
               //:IF szSectionType = "DirectionsForUse"
               if ( ZeidonStringCompare( szSectionType, 1, 0, "DirectionsForUse", 1, 0, 51 ) == 0 )
               { 
                  //:// TraceLineS( "Major Block: ", szSectionType )

                  //:// Check if Title should be converted to upper case.
                  //:szTitle = "Directions for Use"
                   {StringBuilder sb_szTitle;
                  if ( szTitle == null )
                     sb_szTitle = new StringBuilder( 32 );
                  else
                     sb_szTitle = new StringBuilder( szTitle );
                                    ZeidonStringCopy( sb_szTitle, 1, 0, "Directions for Use", 1, 0, 91 );
                  szTitle = sb_szTitle.toString( );}
                  //:SET CURSOR FIRST mSPLDefPDF.LLD_SpecialSectionAttribute WHERE mSPLDefPDF.LLD_SpecialSectionAttribute.Name = "Header"
                  RESULT = SetCursorFirstEntityByString( mSPLDefPDF, "LLD_SpecialSectionAttribute", "Name", "Header", "" );
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:szTitle = "DIRECTIONS FOR USE"
                      {StringBuilder sb_szTitle;
                     if ( szTitle == null )
                        sb_szTitle = new StringBuilder( 32 );
                     else
                        sb_szTitle = new StringBuilder( szTitle );
                                          ZeidonStringCopy( sb_szTitle, 1, 0, "DIRECTIONS FOR USE", 1, 0, 91 );
                     szTitle = sb_szTitle.toString( );}
                  } 

                  //:END 

                  //:// Set up Directions for Use Title.
                  //:szWriteBuffer = szLeadingBlanks + "   <fo:block "
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
                  szWriteBuffer = sb_szWriteBuffer.toString( );}
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <fo:block ", 1, 0, 32001 );
                  szWriteBuffer = sb_szWriteBuffer.toString( );}
                  //:AddFormatToSpecialText( mSPLDefPDF, "Header", szWriteBuffer )
                  {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                     omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Header", sb_szWriteBuffer );
                  szWriteBuffer = sb_szWriteBuffer.toString( );}
                  //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                  try
                  {
                      {
                   ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                   m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                   // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                  };
                  }
                  catch ( Exception e )
                  {
                     throw ZeidonException.wrapException( e );
                  }

                  //:szWriteBuffer = szLeadingBlanks + "      " + szTitle
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
                  szWriteBuffer = sb_szWriteBuffer.toString( );}
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      ", 1, 0, 32001 );
                  szWriteBuffer = sb_szWriteBuffer.toString( );}
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTitle, 1, 0, 32001 );
                  szWriteBuffer = sb_szWriteBuffer.toString( );}
                  //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                  try
                  {
                      {
                   ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                   m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                   // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                  };
                  }
                  catch ( Exception e )
                  {
                     throw ZeidonException.wrapException( e );
                  }

                  //:szWriteBuffer = szLeadingBlanks + "   </fo:block>"
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
                  szWriteBuffer = sb_szWriteBuffer.toString( );}
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   </fo:block>", 1, 0, 32001 );
                  szWriteBuffer = sb_szWriteBuffer.toString( );}
                  //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                  try
                  {
                      {
                   ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                   m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                   // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                  };
                  }
                  catch ( Exception e )
                  {
                     throw ZeidonException.wrapException( e );
                  }

                  //:// For Directions For Use, we will process all Sections.
                  //:FOR EACH mSPLDef.SPLD_DirectionsForUseSection  
                  RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_DirectionsForUseSection", "" );
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //:GeneratePDF_DFU( mSPLDef, mSPLDefPDF, lFile,
                     //:               "SPLD_DirectionsForUseSection",
                     //:               "SPLD_DirectionsForUseStatement",
                     //:               "SPLD_DirectionsUsage",
                     //:               "SPLD_DirectionsUsageOrdering",
                     //:               szLeadingBlanks,
                     //:               szWriteBuffer )
                     omSPLDef_GeneratePDF_DFU( mSPLDef, mSPLDefPDF, lFile, "SPLD_DirectionsForUseSection", "SPLD_DirectionsForUseStatement", "SPLD_DirectionsUsage", "SPLD_DirectionsUsageOrdering", szLeadingBlanks, szWriteBuffer );
                     RESULT = SetCursorNextEntity( mSPLDef, "SPLD_DirectionsForUseSection", "" );
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 

                  //:// MARKETING
                  //:IF szSectionType = "Marketing"
                  if ( ZeidonStringCompare( szSectionType, 1, 0, "Marketing", 1, 0, 51 ) == 0 )
                  { 
                     //:// TraceLineS( "Major Block: ", szSectionType )

                     //:// For Marketing, we will process a Section if it's Name matches the name in the Block.
                     //:SET CURSOR FIRST mSPLDef.SPLD_MarketingSection WHERE mSPLDef.SPLD_MarketingSection.Name = mSPLDefPDF.LLD_Block.Name 
                     {StringBuilder sb_szTempString_1;
                     if ( szTempString_1 == null )
                        sb_szTempString_1 = new StringBuilder( 32 );
                     else
                        sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                           GetStringFromAttribute( sb_szTempString_1, mSPLDefPDF, "LLD_Block", "Name" );
                     szTempString_1 = sb_szTempString_1.toString( );}
                     RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_MarketingSection", "Name", szTempString_1, "" );
                     //:IF RESULT >= zCURSOR_SET 
                     if ( RESULT >= zCURSOR_SET )
                     { 
                        //:// If there is a Marketing "Header" entry, add it.
                        //:SET CURSOR FIRST mSPLDefPDF.LLD_SpecialSectionAttribute WHERE mSPLDefPDF.LLD_SpecialSectionAttribute.Name = "Header"
                        RESULT = SetCursorFirstEntityByString( mSPLDefPDF, "LLD_SpecialSectionAttribute", "Name", "Header", "" );
                        //:IF RESULT >= zCURSOR_SET
                        if ( RESULT >= zCURSOR_SET )
                        { 
                           //:szWriteBuffer = szLeadingBlanks + "   <fo:block "
                            {StringBuilder sb_szWriteBuffer;
                           if ( szWriteBuffer == null )
                              sb_szWriteBuffer = new StringBuilder( 32 );
                           else
                              sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
                           szWriteBuffer = sb_szWriteBuffer.toString( );}
                            {StringBuilder sb_szWriteBuffer;
                           if ( szWriteBuffer == null )
                              sb_szWriteBuffer = new StringBuilder( 32 );
                           else
                              sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <fo:block ", 1, 0, 32001 );
                           szWriteBuffer = sb_szWriteBuffer.toString( );}
                           //:AddFormatToSpecialText( mSPLDefPDF, "Header", szWriteBuffer )
                           {StringBuilder sb_szWriteBuffer;
                           if ( szWriteBuffer == null )
                              sb_szWriteBuffer = new StringBuilder( 32 );
                           else
                              sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                       omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Header", sb_szWriteBuffer );
                           szWriteBuffer = sb_szWriteBuffer.toString( );}
                           //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                           try
                           {
                               {
                            ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                            m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                            // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                           };
                           }
                           catch ( Exception e )
                           {
                              throw ZeidonException.wrapException( e );
                           }

                           //:szWriteBuffer = szLeadingBlanks + "      Marketing"
                            {StringBuilder sb_szWriteBuffer;
                           if ( szWriteBuffer == null )
                              sb_szWriteBuffer = new StringBuilder( 32 );
                           else
                              sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
                           szWriteBuffer = sb_szWriteBuffer.toString( );}
                            {StringBuilder sb_szWriteBuffer;
                           if ( szWriteBuffer == null )
                              sb_szWriteBuffer = new StringBuilder( 32 );
                           else
                              sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      Marketing", 1, 0, 32001 );
                           szWriteBuffer = sb_szWriteBuffer.toString( );}
                           //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                           try
                           {
                               {
                            ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                            m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                            // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                           };
                           }
                           catch ( Exception e )
                           {
                              throw ZeidonException.wrapException( e );
                           }

                           //:szWriteBuffer = szLeadingBlanks + "   </fo:block>"
                            {StringBuilder sb_szWriteBuffer;
                           if ( szWriteBuffer == null )
                              sb_szWriteBuffer = new StringBuilder( 32 );
                           else
                              sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
                           szWriteBuffer = sb_szWriteBuffer.toString( );}
                            {StringBuilder sb_szWriteBuffer;
                           if ( szWriteBuffer == null )
                              sb_szWriteBuffer = new StringBuilder( 32 );
                           else
                              sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   </fo:block>", 1, 0, 32001 );
                           szWriteBuffer = sb_szWriteBuffer.toString( );}
                           //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                           try
                           {
                               {
                            ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                            m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                            // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                           };
                           }
                           catch ( Exception e )
                           {
                              throw ZeidonException.wrapException( e );
                           }
                        } 

                        //:END

                        //:// Don't execute if the only Statement is null. This could occur if the block contains a Claims List, without
                        //:// any marketing statement preceding it.
                        //:IF mSPLDef.SPLD_MarketingStatement.Text != "" 
                        if ( CompareAttributeToString( mSPLDef, "SPLD_MarketingStatement", "Text", "" ) != 0 )
                        { 
                           //:GeneratePDF_DFU( mSPLDef, mSPLDefPDF, lFile,
                           //:            "SPLD_MarketingSection",
                           //:            "SPLD_MarketingStatement",
                           //:            "SPLD_MarketingUsage",
                           //:            "SPLD_MarketingUsageOrdering",
                           //:            szLeadingBlanks,
                           //:            szWriteBuffer )
                           omSPLDef_GeneratePDF_DFU( mSPLDef, mSPLDefPDF, lFile, "SPLD_MarketingSection", "SPLD_MarketingStatement", "SPLD_MarketingUsage", "SPLD_MarketingUsageOrdering", szLeadingBlanks, szWriteBuffer );
                        } 

                        //:END
                        //:ELSE
                     } 
                     else
                     { 
                        //:IF mSPLDefPDF.LLD_Block.Name = ""
                        if ( CompareAttributeToString( mSPLDefPDF, "LLD_Block", "Name", "" ) == 0 )
                        { 
                           //:szMsg = "Marketing Section Name for Block is null." 
                            {StringBuilder sb_szMsg;
                           if ( szMsg == null )
                              sb_szMsg = new StringBuilder( 32 );
                           else
                              sb_szMsg = new StringBuilder( szMsg );
                                                      ZeidonStringCopy( sb_szMsg, 1, 0, "Marketing Section Name for Block is null.", 1, 0, 91 );
                           szMsg = sb_szMsg.toString( );}
                           //:ELSE
                        } 
                        else
                        { 
                           //:szMsg = "No match on Marketing Section Name: " + mSPLDefPDF.LLD_Block.Name
                           {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                           StringBuilder sb_szTempString_1;
                           if ( szTempString_1 == null )
                              sb_szTempString_1 = new StringBuilder( 32 );
                           else
                              sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                                       GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_8, 'S', 129, mSPLDefPDF, "LLD_Block", "Name", "", 0 );
                           lTempInteger_8 = mi_lTempInteger_8.intValue( );
                           szTempString_1 = sb_szTempString_1.toString( );}
                            {StringBuilder sb_szMsg;
                           if ( szMsg == null )
                              sb_szMsg = new StringBuilder( 32 );
                           else
                              sb_szMsg = new StringBuilder( szMsg );
                                                      ZeidonStringCopy( sb_szMsg, 1, 0, "No match on Marketing Section Name: ", 1, 0, 91 );
                           szMsg = sb_szMsg.toString( );}
                            {StringBuilder sb_szMsg;
                           if ( szMsg == null )
                              sb_szMsg = new StringBuilder( 32 );
                           else
                              sb_szMsg = new StringBuilder( szMsg );
                                                      ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_1, 1, 0, 91 );
                           szMsg = sb_szMsg.toString( );}
                        } 

                        //:END
                        //://CreateViewFromView( mSPLDefPDF2, mSPLDefPDF )
                        //://NAME VIEW mSPLDefPDF2 "mSPLDefPDFBlock"
                        //:MessageSend( mSPLDef, "", "Generate Label", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                        MessageSend( mSPLDef, "", "Generate Label", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                        //://DropView( mSPLDefPDF2 )
                        //:RETURN 2
                        if(8==8)return( 2 );
                     } 

                     //:END
                     //:ELSE
                  } 
                  else
                  { 

                     //:// GENERAL (ENVIRONMENTAL/PHYSICAL HAZARD, FIRST AID or PRECAUTIONARY)
                     //:/*IF szSectionType = "OtherHazard" OR
                     //:szSectionType = "FirstAid" OR
                     //:szSectionType = "Precautionary"

                     //:// We can get by using the same operation because all 3 Section Types are handled the same way and the child entity,
                     //:// SPLDT_GeneralSection, is pointing to the instance of the correct Type.
                     //:// TraceLineS( "Major Block: ", szSectionType )
                     //://FOR EACH mSPLDef.SPLDT_GeneralSection
                     //:GeneratePDF_General( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer )
                     //://END
                     //:ELSE*/

                     //:// STORAGE AND DISPOSAL
                     //:IF szSectionType = "StorageDisposal" 
                     if ( ZeidonStringCompare( szSectionType, 1, 0, "StorageDisposal", 1, 0, 51 ) == 0 )
                     { 

                        //:// TraceLineS( "Major Block: ", szSectionType )
                        //:GeneratePDF_DFU( mSPLDef, mSPLDefPDF, lFile,
                        //:        "SPLD_StorageDisposalSection",
                        //:        "SPLD_StorageDisposalStatement",
                        //:        "",
                        //:        "",
                        //:        szLeadingBlanks,
                        //:        szWriteBuffer )
                        omSPLDef_GeneratePDF_DFU( mSPLDef, mSPLDefPDF, lFile, "SPLD_StorageDisposalSection", "SPLD_StorageDisposalStatement", "", "", szLeadingBlanks, szWriteBuffer );

                        //:ELSE
                     } 
                     else
                     { 

                        //:// PRECAUTIONARY
                        //:IF szSectionType = "Precautionary" 
                        if ( ZeidonStringCompare( szSectionType, 1, 0, "Precautionary", 1, 0, 51 ) == 0 )
                        { 

                           //:// TraceLineS( "Major Block: ", szSectionType )
                           //:SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.SectionType = "P"  // SectionType of P is Precautionary 
                           RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_GeneralSection", "SectionType", "P", "" );
                           //:GeneratePDF_DFU( mSPLDef, mSPLDefPDF, lFile,
                           //:     "SPLD_GeneralSection",
                           //:     "SPLD_GeneralStatement",
                           //:     "",
                           //:     "",
                           //:     szLeadingBlanks,
                           //:     szWriteBuffer )
                           omSPLDef_GeneratePDF_DFU( mSPLDef, mSPLDefPDF, lFile, "SPLD_GeneralSection", "SPLD_GeneralStatement", "", "", szLeadingBlanks, szWriteBuffer );

                           //:ELSE
                        } 
                        else
                        { 

                           //:// FIRST AID
                           //:IF szSectionType = "FirstAid" 
                           if ( ZeidonStringCompare( szSectionType, 1, 0, "FirstAid", 1, 0, 51 ) == 0 )
                           { 

                              //:// TraceLineS( "Major Block: ", szSectionType )

                              //:// Check if Title should be converted to upper case.
                              //:szTitle = "First Aid"
                               {StringBuilder sb_szTitle;
                              if ( szTitle == null )
                                 sb_szTitle = new StringBuilder( 32 );
                              else
                                 sb_szTitle = new StringBuilder( szTitle );
                                                            ZeidonStringCopy( sb_szTitle, 1, 0, "First Aid", 1, 0, 91 );
                              szTitle = sb_szTitle.toString( );}
                              //:SET CURSOR FIRST mSPLDefPDF.LLD_SpecialSectionAttribute WHERE mSPLDefPDF.LLD_SpecialSectionAttribute.Name = "Header"
                              RESULT = SetCursorFirstEntityByString( mSPLDefPDF, "LLD_SpecialSectionAttribute", "Name", "Header", "" );
                              //:IF RESULT >= zCURSOR_SET
                              if ( RESULT >= zCURSOR_SET )
                              { 
                                 //:szTitle = "FIRST AID"
                                  {StringBuilder sb_szTitle;
                                 if ( szTitle == null )
                                    sb_szTitle = new StringBuilder( 32 );
                                 else
                                    sb_szTitle = new StringBuilder( szTitle );
                                                                  ZeidonStringCopy( sb_szTitle, 1, 0, "FIRST AID", 1, 0, 91 );
                                 szTitle = sb_szTitle.toString( );}
                              } 

                              //:END 

                              //:// Set up First Aid Title.
                              //:szWriteBuffer = szLeadingBlanks + "   <fo:block "
                               {StringBuilder sb_szWriteBuffer;
                              if ( szWriteBuffer == null )
                                 sb_szWriteBuffer = new StringBuilder( 32 );
                              else
                                 sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
                              szWriteBuffer = sb_szWriteBuffer.toString( );}
                               {StringBuilder sb_szWriteBuffer;
                              if ( szWriteBuffer == null )
                                 sb_szWriteBuffer = new StringBuilder( 32 );
                              else
                                 sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <fo:block ", 1, 0, 32001 );
                              szWriteBuffer = sb_szWriteBuffer.toString( );}
                              //:AddFormatToSpecialText( mSPLDefPDF, "Header", szWriteBuffer )
                              {StringBuilder sb_szWriteBuffer;
                              if ( szWriteBuffer == null )
                                 sb_szWriteBuffer = new StringBuilder( 32 );
                              else
                                 sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                             omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Header", sb_szWriteBuffer );
                              szWriteBuffer = sb_szWriteBuffer.toString( );}
                              //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                              try
                              {
                                  {
                               ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                               m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                               // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                              };
                              }
                              catch ( Exception e )
                              {
                                 throw ZeidonException.wrapException( e );
                              }

                              //:szWriteBuffer = szLeadingBlanks + "      " + szTitle
                               {StringBuilder sb_szWriteBuffer;
                              if ( szWriteBuffer == null )
                                 sb_szWriteBuffer = new StringBuilder( 32 );
                              else
                                 sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
                              szWriteBuffer = sb_szWriteBuffer.toString( );}
                               {StringBuilder sb_szWriteBuffer;
                              if ( szWriteBuffer == null )
                                 sb_szWriteBuffer = new StringBuilder( 32 );
                              else
                                 sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      ", 1, 0, 32001 );
                              szWriteBuffer = sb_szWriteBuffer.toString( );}
                               {StringBuilder sb_szWriteBuffer;
                              if ( szWriteBuffer == null )
                                 sb_szWriteBuffer = new StringBuilder( 32 );
                              else
                                 sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTitle, 1, 0, 32001 );
                              szWriteBuffer = sb_szWriteBuffer.toString( );}
                              //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                              try
                              {
                                  {
                               ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                               m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                               // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                              };
                              }
                              catch ( Exception e )
                              {
                                 throw ZeidonException.wrapException( e );
                              }

                              //:szWriteBuffer = szLeadingBlanks + "   </fo:block>"
                               {StringBuilder sb_szWriteBuffer;
                              if ( szWriteBuffer == null )
                                 sb_szWriteBuffer = new StringBuilder( 32 );
                              else
                                 sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
                              szWriteBuffer = sb_szWriteBuffer.toString( );}
                               {StringBuilder sb_szWriteBuffer;
                              if ( szWriteBuffer == null )
                                 sb_szWriteBuffer = new StringBuilder( 32 );
                              else
                                 sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   </fo:block>", 1, 0, 32001 );
                              szWriteBuffer = sb_szWriteBuffer.toString( );}
                              //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                              try
                              {
                                  {
                               ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                               m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                               // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                              };
                              }
                              catch ( Exception e )
                              {
                                 throw ZeidonException.wrapException( e );
                              }

                              //:SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.SectionType = "F"  // SectionType of F is First Aid 
                              RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_GeneralSection", "SectionType", "F", "" );
                              //:GeneratePDF_DFU( mSPLDef, mSPLDefPDF, lFile,
                              //:  "SPLD_GeneralSection",
                              //:  "SPLD_GeneralStatement",
                              //:  "",
                              //:  "",
                              //:  szLeadingBlanks,
                              //:  szWriteBuffer )
                              omSPLDef_GeneratePDF_DFU( mSPLDef, mSPLDefPDF, lFile, "SPLD_GeneralSection", "SPLD_GeneralStatement", "", "", szLeadingBlanks, szWriteBuffer );

                              //:ELSE
                           } 
                           else
                           { 

                              //:// PHYSICAL HAZARD
                              //:IF szSectionType = "PhysicalHazard" 
                              if ( ZeidonStringCompare( szSectionType, 1, 0, "PhysicalHazard", 1, 0, 51 ) == 0 )
                              { 

                                 //:// TraceLineS( "Major Block: ", szSectionType )
                                 //:SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.SectionType = "E"  // SectionType of E is Environmental Hazard 
                                 RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_GeneralSection", "SectionType", "E", "" );
                                 //:GeneratePDF_DFU( mSPLDef, mSPLDefPDF, lFile,
                                 //:"SPLD_GeneralSection",
                                 //:"SPLD_GeneralStatement",
                                 //:"",
                                 //:"",
                                 //:szLeadingBlanks,
                                 //:szWriteBuffer )
                                 omSPLDef_GeneratePDF_DFU( mSPLDef, mSPLDefPDF, lFile, "SPLD_GeneralSection", "SPLD_GeneralStatement", "", "", szLeadingBlanks, szWriteBuffer );

                                 //:ELSE
                              } 
                              else
                              { 

                                 //:// HUMAN HAZARD
                                 //:IF szSectionType = "HumanHazard" 
                                 if ( ZeidonStringCompare( szSectionType, 1, 0, "HumanHazard", 1, 0, 51 ) == 0 )
                                 { 

                                    //:// Generate the single Hazards entry.
                                    //:// TraceLineS( "Major Block: ", szSectionType )
                                    //:GeneratePDF_Hazards( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer )
                                    omSPLDef_GeneratePDF_Hazards( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer );

                                    //:ELSE
                                 } 
                                 else
                                 { 

                                    //:// INGREDIENTS
                                    //:IF szSectionType = "Ingredients" 
                                    if ( ZeidonStringCompare( szSectionType, 1, 0, "Ingredients", 1, 0, 51 ) == 0 )
                                    { 

                                       //:// Go to generate Active Ingredients.
                                       //:// TraceLineS( "Major Block: ", szSectionType )
                                       //:GeneratePDF_Ingred( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer )
                                       omSPLDef_GeneratePDF_Ingred( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer );

                                       //:ELSE
                                    } 
                                    else
                                    { 

                                       //:// NET CONTENTS
                                       //:IF szSectionType = "NetContents" 
                                       if ( ZeidonStringCompare( szSectionType, 1, 0, "NetContents", 1, 0, 51 ) == 0 )
                                       { 

                                          //:// Go to generate Net Contents.
                                          //:// TraceLineS( "Major Block: ", szSectionType )
                                          //:GeneratePDF_Content( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer )
                                          omSPLDef_GeneratePDF_Content( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer );

                                          //:ELSE
                                       } 
                                       else
                                       { 

                                          //:// EPA REGISTRATION AND ESTABLISHMENT NUMBERS
                                          //:IF szSectionType = "EPA_RegAndEstNbr" 
                                          if ( ZeidonStringCompare( szSectionType, 1, 0, "EPA_RegAndEstNbr", 1, 0, 51 ) == 0 )
                                          { 

                                             //:// Go to generate EPA Reg and Est Numbers.
                                             //:// TraceLineS( "Major Block: ", szSectionType )
                                             //:GeneratePDF_EPA_Reg( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer )
                                             omSPLDef_GeneratePDF_EPA_Reg( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer );

                                             //:ELSE
                                          } 
                                          else
                                          { 

                                             //:// PRODUCT NAME
                                             //:IF szSectionType = "Product Name" 
                                             if ( ZeidonStringCompare( szSectionType, 1, 0, "Product Name", 1, 0, 51 ) == 0 )
                                             { 

                                                //:// TraceLineS( "Major Block: ", szSectionType )
                                                //:szBlockBlanks = szLeadingBlanks + "   "
                                                 {StringBuilder sb_szBlockBlanks;
                                                if ( szBlockBlanks == null )
                                                   sb_szBlockBlanks = new StringBuilder( 32 );
                                                else
                                                   sb_szBlockBlanks = new StringBuilder( szBlockBlanks );
                                                                                                ZeidonStringCopy( sb_szBlockBlanks, 1, 0, szLeadingBlanks, 1, 0, 51 );
                                                szBlockBlanks = sb_szBlockBlanks.toString( );}
                                                 {StringBuilder sb_szBlockBlanks;
                                                if ( szBlockBlanks == null )
                                                   sb_szBlockBlanks = new StringBuilder( 32 );
                                                else
                                                   sb_szBlockBlanks = new StringBuilder( szBlockBlanks );
                                                                                                ZeidonStringConcat( sb_szBlockBlanks, 1, 0, "   ", 1, 0, 51 );
                                                szBlockBlanks = sb_szBlockBlanks.toString( );}
                                                //:szWriteBuffer = szBlockBlanks + "<fo:block "
                                                 {StringBuilder sb_szWriteBuffer;
                                                if ( szWriteBuffer == null )
                                                   sb_szWriteBuffer = new StringBuilder( 32 );
                                                else
                                                   sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szBlockBlanks, 1, 0, 32001 );
                                                szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                 {StringBuilder sb_szWriteBuffer;
                                                if ( szWriteBuffer == null )
                                                   sb_szWriteBuffer = new StringBuilder( 32 );
                                                else
                                                   sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<fo:block ", 1, 0, 32001 );
                                                szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                //:AddFormatToSpecialText( mSPLDefPDF, "Text", szWriteBuffer )
                                                {StringBuilder sb_szWriteBuffer;
                                                if ( szWriteBuffer == null )
                                                   sb_szWriteBuffer = new StringBuilder( 32 );
                                                else
                                                   sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                 omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Text", sb_szWriteBuffer );
                                                szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                                                try
                                                {
                                                    {
                                                 ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                                                 m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                                                 // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                                                };
                                                }
                                                catch ( Exception e )
                                                {
                                                   throw ZeidonException.wrapException( e );
                                                }

                                                //:szWriteBuffer = szBlockBlanks + "   <xsl:apply-templates select=^SubregPhysicalLabelDef/ProductName^/>"
                                                 {StringBuilder sb_szWriteBuffer;
                                                if ( szWriteBuffer == null )
                                                   sb_szWriteBuffer = new StringBuilder( 32 );
                                                else
                                                   sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szBlockBlanks, 1, 0, 32001 );
                                                szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                 {StringBuilder sb_szWriteBuffer;
                                                if ( szWriteBuffer == null )
                                                   sb_szWriteBuffer = new StringBuilder( 32 );
                                                else
                                                   sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <xsl:apply-templates select=^SubregPhysicalLabelDef/ProductName^/>", 1, 0, 32001 );
                                                szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                                                try
                                                {
                                                    {
                                                 ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                                                 m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                                                 // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                                                };
                                                }
                                                catch ( Exception e )
                                                {
                                                   throw ZeidonException.wrapException( e );
                                                }

                                                //:szWriteBuffer = szBlockBlanks + "</fo:block>"
                                                 {StringBuilder sb_szWriteBuffer;
                                                if ( szWriteBuffer == null )
                                                   sb_szWriteBuffer = new StringBuilder( 32 );
                                                else
                                                   sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szBlockBlanks, 1, 0, 32001 );
                                                szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                 {StringBuilder sb_szWriteBuffer;
                                                if ( szWriteBuffer == null )
                                                   sb_szWriteBuffer = new StringBuilder( 32 );
                                                else
                                                   sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block>", 1, 0, 32001 );
                                                szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                                                try
                                                {
                                                    {
                                                 ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                                                 m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                                                 // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                                                };
                                                }
                                                catch ( Exception e )
                                                {
                                                   throw ZeidonException.wrapException( e );
                                                }

                                                //:ELSE
                                             } 
                                             else
                                             { 

                                                //:// PRODUCT DESCRIPTION
                                                //:IF szSectionType = "Product Description" 
                                                if ( ZeidonStringCompare( szSectionType, 1, 0, "Product Description", 1, 0, 51 ) == 0 )
                                                { 

                                                   //:// TraceLineS( "Major Block: ", szSectionType )
                                                   //:szBlockBlanks = szLeadingBlanks + "   "
                                                    {StringBuilder sb_szBlockBlanks;
                                                   if ( szBlockBlanks == null )
                                                      sb_szBlockBlanks = new StringBuilder( 32 );
                                                   else
                                                      sb_szBlockBlanks = new StringBuilder( szBlockBlanks );
                                                                                                      ZeidonStringCopy( sb_szBlockBlanks, 1, 0, szLeadingBlanks, 1, 0, 51 );
                                                   szBlockBlanks = sb_szBlockBlanks.toString( );}
                                                    {StringBuilder sb_szBlockBlanks;
                                                   if ( szBlockBlanks == null )
                                                      sb_szBlockBlanks = new StringBuilder( 32 );
                                                   else
                                                      sb_szBlockBlanks = new StringBuilder( szBlockBlanks );
                                                                                                      ZeidonStringConcat( sb_szBlockBlanks, 1, 0, "   ", 1, 0, 51 );
                                                   szBlockBlanks = sb_szBlockBlanks.toString( );}
                                                   //:szWriteBuffer = szBlockBlanks + "<fo:block>"
                                                    {StringBuilder sb_szWriteBuffer;
                                                   if ( szWriteBuffer == null )
                                                      sb_szWriteBuffer = new StringBuilder( 32 );
                                                   else
                                                      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szBlockBlanks, 1, 0, 32001 );
                                                   szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                    {StringBuilder sb_szWriteBuffer;
                                                   if ( szWriteBuffer == null )
                                                      sb_szWriteBuffer = new StringBuilder( 32 );
                                                   else
                                                      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<fo:block>", 1, 0, 32001 );
                                                   szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                                                   try
                                                   {
                                                       {
                                                    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                                                    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                                                    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                                                   };
                                                   }
                                                   catch ( Exception e )
                                                   {
                                                      throw ZeidonException.wrapException( e );
                                                   }

                                                   //:szWriteBuffer = szBlockBlanks + "   <xsl:apply-templates select=^SubregPhysicalLabelDef/SubregLabelContent/SubregProduct/Description^/>"
                                                    {StringBuilder sb_szWriteBuffer;
                                                   if ( szWriteBuffer == null )
                                                      sb_szWriteBuffer = new StringBuilder( 32 );
                                                   else
                                                      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szBlockBlanks, 1, 0, 32001 );
                                                   szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                    {StringBuilder sb_szWriteBuffer;
                                                   if ( szWriteBuffer == null )
                                                      sb_szWriteBuffer = new StringBuilder( 32 );
                                                   else
                                                      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <xsl:apply-templates select=^SubregPhysicalLabelDef/SubregLabelContent/SubregProduct/Description^/>", 1, 0, 32001 );
                                                   szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                                                   try
                                                   {
                                                       {
                                                    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                                                    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                                                    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                                                   };
                                                   }
                                                   catch ( Exception e )
                                                   {
                                                      throw ZeidonException.wrapException( e );
                                                   }

                                                   //:szWriteBuffer = szBlockBlanks + "</fo:block>"
                                                    {StringBuilder sb_szWriteBuffer;
                                                   if ( szWriteBuffer == null )
                                                      sb_szWriteBuffer = new StringBuilder( 32 );
                                                   else
                                                      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szBlockBlanks, 1, 0, 32001 );
                                                   szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                    {StringBuilder sb_szWriteBuffer;
                                                   if ( szWriteBuffer == null )
                                                      sb_szWriteBuffer = new StringBuilder( 32 );
                                                   else
                                                      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block>", 1, 0, 32001 );
                                                   szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                                                   try
                                                   {
                                                       {
                                                    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                                                    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                                                    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                                                   };
                                                   }
                                                   catch ( Exception e )
                                                   {
                                                      throw ZeidonException.wrapException( e );
                                                   }
                                                   //:ELSE
                                                } 
                                                else
                                                { 
                                                   //:// It is an error because there is no processing for the Block.

                                                   //:szMsg = "Error:  The Block is empty for Panel, " + mSPLDef.LLD_Panel.Tag + ", Block " + mSPLDefPDF.LLD_Block.Tag + "."
                                                   {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
                                                   StringBuilder sb_szTempString_2;
                                                   if ( szTempString_2 == null )
                                                      sb_szTempString_2 = new StringBuilder( 32 );
                                                   else
                                                      sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                                                                                       GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_9, 'S', 65, mSPLDef, "LLD_Panel", "Tag", "", 0 );
                                                   lTempInteger_9 = mi_lTempInteger_9.intValue( );
                                                   szTempString_2 = sb_szTempString_2.toString( );}
                                                    {StringBuilder sb_szMsg;
                                                   if ( szMsg == null )
                                                      sb_szMsg = new StringBuilder( 32 );
                                                   else
                                                      sb_szMsg = new StringBuilder( szMsg );
                                                                                                      ZeidonStringCopy( sb_szMsg, 1, 0, "Error:  The Block is empty for Panel, ", 1, 0, 91 );
                                                   szMsg = sb_szMsg.toString( );}
                                                    {StringBuilder sb_szMsg;
                                                   if ( szMsg == null )
                                                      sb_szMsg = new StringBuilder( 32 );
                                                   else
                                                      sb_szMsg = new StringBuilder( szMsg );
                                                                                                      ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_2, 1, 0, 91 );
                                                   szMsg = sb_szMsg.toString( );}
                                                    {StringBuilder sb_szMsg;
                                                   if ( szMsg == null )
                                                      sb_szMsg = new StringBuilder( 32 );
                                                   else
                                                      sb_szMsg = new StringBuilder( szMsg );
                                                                                                      ZeidonStringConcat( sb_szMsg, 1, 0, ", Block ", 1, 0, 91 );
                                                   szMsg = sb_szMsg.toString( );}
                                                   {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
                                                   StringBuilder sb_szTempString_3;
                                                   if ( szTempString_3 == null )
                                                      sb_szTempString_3 = new StringBuilder( 32 );
                                                   else
                                                      sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                                                                                       GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_10, 'S', 65, mSPLDefPDF, "LLD_Block", "Tag", "", 0 );
                                                   lTempInteger_10 = mi_lTempInteger_10.intValue( );
                                                   szTempString_3 = sb_szTempString_3.toString( );}
                                                    {StringBuilder sb_szMsg;
                                                   if ( szMsg == null )
                                                      sb_szMsg = new StringBuilder( 32 );
                                                   else
                                                      sb_szMsg = new StringBuilder( szMsg );
                                                                                                      ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_3, 1, 0, 91 );
                                                   szMsg = sb_szMsg.toString( );}
                                                    {StringBuilder sb_szMsg;
                                                   if ( szMsg == null )
                                                      sb_szMsg = new StringBuilder( 32 );
                                                   else
                                                      sb_szMsg = new StringBuilder( szMsg );
                                                                                                      ZeidonStringConcat( sb_szMsg, 1, 0, ".", 1, 0, 91 );
                                                   szMsg = sb_szMsg.toString( );}
                                                   //:TraceLineS( szMsg, "" )
                                                   TraceLineS( szMsg, "" );

                                                   //:szTitle = "Error"
                                                    {StringBuilder sb_szTitle;
                                                   if ( szTitle == null )
                                                      sb_szTitle = new StringBuilder( 32 );
                                                   else
                                                      sb_szTitle = new StringBuilder( szTitle );
                                                                                                      ZeidonStringCopy( sb_szTitle, 1, 0, "Error", 1, 0, 91 );
                                                   szTitle = sb_szTitle.toString( );}

                                                   //:// Set up Error Title.
                                                   //:szWriteBuffer = szLeadingBlanks + "   <fo:block>"
                                                    {StringBuilder sb_szWriteBuffer;
                                                   if ( szWriteBuffer == null )
                                                      sb_szWriteBuffer = new StringBuilder( 32 );
                                                   else
                                                      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
                                                   szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                    {StringBuilder sb_szWriteBuffer;
                                                   if ( szWriteBuffer == null )
                                                      sb_szWriteBuffer = new StringBuilder( 32 );
                                                   else
                                                      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <fo:block>", 1, 0, 32001 );
                                                   szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                                                   try
                                                   {
                                                       {
                                                    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                                                    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                                                    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                                                   };
                                                   }
                                                   catch ( Exception e )
                                                   {
                                                      throw ZeidonException.wrapException( e );
                                                   }

                                                   //:szWriteBuffer = szLeadingBlanks + "      " + szTitle
                                                    {StringBuilder sb_szWriteBuffer;
                                                   if ( szWriteBuffer == null )
                                                      sb_szWriteBuffer = new StringBuilder( 32 );
                                                   else
                                                      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
                                                   szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                    {StringBuilder sb_szWriteBuffer;
                                                   if ( szWriteBuffer == null )
                                                      sb_szWriteBuffer = new StringBuilder( 32 );
                                                   else
                                                      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      ", 1, 0, 32001 );
                                                   szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                    {StringBuilder sb_szWriteBuffer;
                                                   if ( szWriteBuffer == null )
                                                      sb_szWriteBuffer = new StringBuilder( 32 );
                                                   else
                                                      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTitle, 1, 0, 32001 );
                                                   szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                                                   try
                                                   {
                                                       {
                                                    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                                                    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                                                    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                                                   };
                                                   }
                                                   catch ( Exception e )
                                                   {
                                                      throw ZeidonException.wrapException( e );
                                                   }

                                                   //:szWriteBuffer = szLeadingBlanks + "   </fo:block>"
                                                    {StringBuilder sb_szWriteBuffer;
                                                   if ( szWriteBuffer == null )
                                                      sb_szWriteBuffer = new StringBuilder( 32 );
                                                   else
                                                      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
                                                   szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                    {StringBuilder sb_szWriteBuffer;
                                                   if ( szWriteBuffer == null )
                                                      sb_szWriteBuffer = new StringBuilder( 32 );
                                                   else
                                                      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                                                      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   </fo:block>", 1, 0, 32001 );
                                                   szWriteBuffer = sb_szWriteBuffer.toString( );}
                                                   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
                                                   try
                                                   {
                                                       {
                                                    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                                                    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                                                    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
                                                   };
                                                   }
                                                   catch ( Exception e )
                                                   {
                                                      throw ZeidonException.wrapException( e );
                                                   }
                                                } 


                                                //:// MessageSend( mSPLDef, "", "Generate Label", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                                                //:// RETURN 2
                                                //:END
                                             } 

                                             //:END
                                          } 

                                          //:END
                                       } 

                                       //:END
                                    } 

                                    //:END
                                 } 

                                 //:END
                              } 

                              //:END
                           } 

                           //:END
                        } 

                        //:END
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            //:END

            //:// Add Column list for Marketing Section, if requested.
            //:IF szSectionType = "Marketing" AND mSPLDefPDF.LLD_Block.LLD_ColumnListType != ""
            if ( ZeidonStringCompare( szSectionType, 1, 0, "Marketing", 1, 0, 51 ) == 0 && CompareAttributeToString( mSPLDefPDF, "LLD_Block", "LLD_ColumnListType", "" ) != 0 )
            { 
               //:IF mSPLDefPDF.LLD_Block.LLD_ColumnListType = "C3"
               if ( CompareAttributeToString( mSPLDefPDF, "LLD_Block", "LLD_ColumnListType", "C3" ) == 0 )
               { 
                  //:// Request is 3 Column Claim List.
                  //:TraceLineS( "Marketing 3-Column List ", "" )
                  TraceLineS( "Marketing 3-Column List ", "" );
                  //:GeneratePDF_ClmList( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer, 3 )
                  omSPLDef_GeneratePDF_ClmList( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer, 3 );
                  //:ELSE
               } 
               else
               { 
                  //:// Default is 2 Column Claim List.
                  //:TraceLineS( "Marketing 2-Column List ", "" )
                  TraceLineS( "Marketing 2-Column List ", "" );
                  //:GeneratePDF_ClmList( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer, 2 )
                  omSPLDef_GeneratePDF_ClmList( mSPLDef, mSPLDefPDF, lFile, szLeadingBlanks, szWriteBuffer, 2 );
               } 

               //:END
            } 

            //:END
         } 

         //:   
         //:END

         //:// Process Termination.
         //:szWriteBuffer = szLeadingBlanks + "</fo:block-container>"
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block-container>", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
         //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
         try
         {
             {
          ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
          m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
          // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
         };
         }
         catch ( Exception e )
         {
            throw ZeidonException.wrapException( e );
         }
      } 

      RESULT = SetCursorNextEntity( mSPLDefPDF, "LLD_Block", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GeneratePDF_DFU( VIEW mSPLDef      BASED ON LOD mSPLDef,
//:                 VIEW mSPLDefPDF BASED ON LOD mSPLDef,
//:                 INTEGER lFile,
//:                 STRING ( 32 ) szSPLD_SectionName,
//:                 STRING ( 32 ) szStatementName,
//:                 STRING ( 32 ) szUsageTypeEntity,
//:                 STRING ( 32 ) szLoopingEntity,
//:                 STRING ( 50 ) szPassedBlanks,
//:                 STRING ( 32000 ) szWriteBuffer )

//:   VIEW mSPLDefPanelLevel BASED ON LOD mSPLDef
public int 
omSPLDef_GeneratePDF_DFU( View     mSPLDef,
                          View     mSPLDefPDF,
                          int      lFile,
                          String   szSPLD_SectionName,
                          String   szStatementName,
                          String   szUsageTypeEntity,
                          String   szLoopingEntity,
                          String   szPassedBlanks,
                          String   szWriteBuffer )
{
   zVIEW    mSPLDefPanelLevel = new zVIEW( );
   //:STRING ( 32000 )  szStatementText
   String   szStatementText = null;
   //:STRING ( 32000 )  szTemporaryText
   String   szTemporaryText = null;
   //:STRING ( 256 )    szStatementTitle
   String   szStatementTitle = null;
   //:STRING ( 256 )    szTemporaryTitle
   String   szTemporaryTitle = null;
   //:STRING ( 50 )     szSectionTitle
   String   szSectionTitle = null;
   //:STRING ( 50 )     szSectionName
   String   szSectionName = null;
   //:STRING ( 50 )     szSectionType
   String   szSectionType = null;
   //:STRING ( 10 )     szSeparatorCharacters
   String   szSeparatorCharacters = null;
   //:STRING ( 10 )     szNumberedText
   String   szNumberedText = null;
   //:STRING ( 32 )     szLineHeight
   String   szLineHeight = null;
   //:STRING ( 10 )     szStatementLeading
   String   szStatementLeading = null;
   //:STRING ( 2 )      szSectionTitlePosition
   String   szSectionTitlePosition = null;
   //:STRING ( 2 )      szStatementTitlePosition
   String   szStatementTitlePosition = null;
   //:STRING ( 2 )      szStatementFormat
   String   szStatementFormat = null;
   //:STRING ( 1 )      szFoundFirstNumberedEntryFlag
   String   szFoundFirstNumberedEntryFlag = null;
   //:STRING ( 50 )     szLeadingBlanks
   String   szLeadingBlanks = null;
   //:STRING ( 3 )      szDisplaySectionSuffix
   String   szDisplaySectionSuffix = null;
   //:STRING ( 50 )     szDisplaySectionName
   String   szDisplaySectionName = null;
   //:STRING ( 50 )     szDisplayStatementName
   String   szDisplayStatementName = null;
   //:STRING ( 1 )      szContinuationFlag
   String   szContinuationFlag = null;
   //:STRING ( 1 )      szBlockContinuationType
   String   szBlockContinuationType = null;
   //:STRING ( 1 )      szConvertToCapsFlag
   String   szConvertToCapsFlag = null;
   //:INTEGER           lCnt
   int      lCnt = 0;
   //:SHORT             nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      RESULT = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_11 = 0;


   //:// Generate PDF for a "Directions of Use" or "Marketing" Section.

   //:szLeadingBlanks = szPassedBlanks + "   "
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringCopy( sb_szLeadingBlanks, 1, 0, szPassedBlanks, 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringConcat( sb_szLeadingBlanks, 1, 0, "   ", 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}

   //:// We need to decide where the Statement Format type is being specified. It was stored in the TemplateSection.StatementFormat
   //:// attribute, but the TemplateSection entity is being eliminated. Maybe it should just go with the Block.
   //:// The same is true for szSeparatorCharacters = mSPLDef.SPLD_TemplateSection.UsageSeparatorCharacters
   //:szStatementFormat     = "SP"
    {StringBuilder sb_szStatementFormat;
   if ( szStatementFormat == null )
      sb_szStatementFormat = new StringBuilder( 32 );
   else
      sb_szStatementFormat = new StringBuilder( szStatementFormat );
      ZeidonStringCopy( sb_szStatementFormat, 1, 0, "SP", 1, 0, 3 );
   szStatementFormat = sb_szStatementFormat.toString( );}
   //:szSeparatorCharacters = ", "
    {StringBuilder sb_szSeparatorCharacters;
   if ( szSeparatorCharacters == null )
      sb_szSeparatorCharacters = new StringBuilder( 32 );
   else
      sb_szSeparatorCharacters = new StringBuilder( szSeparatorCharacters );
      ZeidonStringCopy( sb_szSeparatorCharacters, 1, 0, ", ", 1, 0, 11 );
   szSeparatorCharacters = sb_szSeparatorCharacters.toString( );}

   //:// If this is block for a Marketing Section, we need to position on the corresponding Market Section by Name.
   //:// Otherwise, we are positioned on the correct Section already.
   //:// We also need to initialize the XML for the Marketing Section.
   //:szSectionType = mSPLDefPDF.LLD_Block.LLD_SectionType 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szSectionType;
   if ( szSectionType == null )
      sb_szSectionType = new StringBuilder( 32 );
   else
      sb_szSectionType = new StringBuilder( szSectionType );
       GetVariableFromAttribute( sb_szSectionType, mi_lTempInteger_0, 'S', 51, mSPLDefPDF, "LLD_Block", "LLD_SectionType", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szSectionType = sb_szSectionType.toString( );}
   //:IF szSectionType = "Marketing" 
   if ( ZeidonStringCompare( szSectionType, 1, 0, "Marketing", 1, 0, 51 ) == 0 )
   { 
      //:szSectionName = mSPLDefPDF.LLD_Block.Name 
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szSectionName;
      if ( szSectionName == null )
         sb_szSectionName = new StringBuilder( 32 );
      else
         sb_szSectionName = new StringBuilder( szSectionName );
             GetVariableFromAttribute( sb_szSectionName, mi_lTempInteger_1, 'S', 51, mSPLDefPDF, "LLD_Block", "Name", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szSectionName = sb_szSectionName.toString( );}
      //:SetCursorFirstEntityByString( mSPLDef, szSPLD_SectionName, "Name", szSectionName, "" )
      SetCursorFirstEntityByString( mSPLDef, szSPLD_SectionName, "Name", szSectionName, "" );
   } 

   //:END

   //:// Increment Display Section Suffix and Initialize DisplaySection entry.
   //:mSPLDef.SubregPhysicalLabelDef.wLastDisplaySuffixCount = mSPLDef.SubregPhysicalLabelDef.wLastDisplaySuffixCount + 1
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mSPLDef, "SubregPhysicalLabelDef", "wLastDisplaySuffixCount" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   lTempInteger_3 = lTempInteger_2 + 1;
   SetAttributeFromInteger( mSPLDef, "SubregPhysicalLabelDef", "wLastDisplaySuffixCount", lTempInteger_3 );
   //:szDisplaySectionSuffix = mSPLDef.SubregPhysicalLabelDef.wLastDisplaySuffixCount 
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
   StringBuilder sb_szDisplaySectionSuffix;
   if ( szDisplaySectionSuffix == null )
      sb_szDisplaySectionSuffix = new StringBuilder( 32 );
   else
      sb_szDisplaySectionSuffix = new StringBuilder( szDisplaySectionSuffix );
       GetVariableFromAttribute( sb_szDisplaySectionSuffix, mi_lTempInteger_4, 'S', 4, mSPLDef, "SubregPhysicalLabelDef", "wLastDisplaySuffixCount", "", 0 );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );
   szDisplaySectionSuffix = sb_szDisplaySectionSuffix.toString( );}
   //:szDisplaySectionName = "DisplaySection" + szDisplaySectionSuffix
    {StringBuilder sb_szDisplaySectionName;
   if ( szDisplaySectionName == null )
      sb_szDisplaySectionName = new StringBuilder( 32 );
   else
      sb_szDisplaySectionName = new StringBuilder( szDisplaySectionName );
      ZeidonStringCopy( sb_szDisplaySectionName, 1, 0, "DisplaySection", 1, 0, 51 );
   szDisplaySectionName = sb_szDisplaySectionName.toString( );}
    {StringBuilder sb_szDisplaySectionName;
   if ( szDisplaySectionName == null )
      sb_szDisplaySectionName = new StringBuilder( 32 );
   else
      sb_szDisplaySectionName = new StringBuilder( szDisplaySectionName );
      ZeidonStringConcat( sb_szDisplaySectionName, 1, 0, szDisplaySectionSuffix, 1, 0, 51 );
   szDisplaySectionName = sb_szDisplaySectionName.toString( );}
   //:CREATE ENTITY mSPLDef.DisplaySection 
   RESULT = CreateEntity( mSPLDef, "DisplaySection", zPOS_AFTER );
   //:mSPLDef.DisplaySection.Type = szSPLD_SectionName
   SetAttributeFromString( mSPLDef, "DisplaySection", "Type", szSPLD_SectionName );
   //:mSPLDef.DisplaySection.XML_SectionName = szDisplaySectionName
   SetAttributeFromString( mSPLDef, "DisplaySection", "XML_SectionName", szDisplaySectionName );

   //:szSectionTitlePosition = mSPLDefPDF.LLD_Block.TitlePosition
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
   StringBuilder sb_szSectionTitlePosition;
   if ( szSectionTitlePosition == null )
      sb_szSectionTitlePosition = new StringBuilder( 32 );
   else
      sb_szSectionTitlePosition = new StringBuilder( szSectionTitlePosition );
       GetVariableFromAttribute( sb_szSectionTitlePosition, mi_lTempInteger_5, 'S', 3, mSPLDefPDF, "LLD_Block", "TitlePosition", "", 0 );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );
   szSectionTitlePosition = sb_szSectionTitlePosition.toString( );}
   //:IF szSectionTitlePosition = ""
   if ( ZeidonStringCompare( szSectionTitlePosition, 1, 0, "", 1, 0, 3 ) == 0 )
   { 
      //:szSectionTitlePosition = "SL"    // Default will put the Title on a separate line.   
       {StringBuilder sb_szSectionTitlePosition;
      if ( szSectionTitlePosition == null )
         sb_szSectionTitlePosition = new StringBuilder( 32 );
      else
         sb_szSectionTitlePosition = new StringBuilder( szSectionTitlePosition );
            ZeidonStringCopy( sb_szSectionTitlePosition, 1, 0, "SL", 1, 0, 3 );
      szSectionTitlePosition = sb_szSectionTitlePosition.toString( );}
   } 

   //:END

   //:// Format Section Title, if there is a Title.   *** We need to something different with Section Title.
   //:GetStringFromAttribute( szSectionTitle, mSPLDef, szSPLD_SectionName, "Title" )
   {StringBuilder sb_szSectionTitle;
   if ( szSectionTitle == null )
      sb_szSectionTitle = new StringBuilder( 32 );
   else
      sb_szSectionTitle = new StringBuilder( szSectionTitle );
       GetStringFromAttribute( sb_szSectionTitle, mSPLDef, szSPLD_SectionName, "Title" );
   szSectionTitle = sb_szSectionTitle.toString( );}
   //:IF szSectionTitle != ""
   if ( ZeidonStringCompare( szSectionTitle, 1, 0, "", 1, 0, 51 ) != 0 )
   { 
      //:szWriteBuffer = szLeadingBlanks + "<fo:block "
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<fo:block ", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:AddFormatToSpecialText( mSPLDefPDF, "Title", szWriteBuffer )
      {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
             omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Title", sb_szWriteBuffer );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:szWriteBuffer = szLeadingBlanks + "      <xsl:apply-templates select=^SubregPhysicalLabelDef/" + szDisplaySectionName + "/Title^/>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      <xsl:apply-templates select=^SubregPhysicalLabelDef/", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szDisplaySectionName, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "/Title^/>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:szWriteBuffer = szLeadingBlanks + "</fo:block>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }
   } 

   //:END

   //:// Format each Statement, including Title, if requested.
   //:// Get Title option from the LLD_SpecialSectionAttrBlock entity for Title option.
   //:szStatementTitlePosition = "SL"
    {StringBuilder sb_szStatementTitlePosition;
   if ( szStatementTitlePosition == null )
      sb_szStatementTitlePosition = new StringBuilder( 32 );
   else
      sb_szStatementTitlePosition = new StringBuilder( szStatementTitlePosition );
      ZeidonStringCopy( sb_szStatementTitlePosition, 1, 0, "SL", 1, 0, 3 );
   szStatementTitlePosition = sb_szStatementTitlePosition.toString( );}
   //:szConvertToCapsFlag = ""
    {StringBuilder sb_szConvertToCapsFlag;
   if ( szConvertToCapsFlag == null )
      sb_szConvertToCapsFlag = new StringBuilder( 32 );
   else
      sb_szConvertToCapsFlag = new StringBuilder( szConvertToCapsFlag );
      ZeidonStringCopy( sb_szConvertToCapsFlag, 1, 0, "", 1, 0, 2 );
   szConvertToCapsFlag = sb_szConvertToCapsFlag.toString( );}
   //:SET CURSOR FIRST mSPLDefPDF.LLD_SpecialSectionAttribute WHERE mSPLDefPDF.LLD_SpecialSectionAttribute.Name = "Title"
   RESULT = SetCursorFirstEntityByString( mSPLDefPDF, "LLD_SpecialSectionAttribute", "Name", "Title", "" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:szStatementTitlePosition = mSPLDefPDF.LLD_SpecialSectionAttrBlock.TitlePosition 
      {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
      StringBuilder sb_szStatementTitlePosition;
      if ( szStatementTitlePosition == null )
         sb_szStatementTitlePosition = new StringBuilder( 32 );
      else
         sb_szStatementTitlePosition = new StringBuilder( szStatementTitlePosition );
             GetVariableFromAttribute( sb_szStatementTitlePosition, mi_lTempInteger_6, 'S', 3, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "TitlePosition", "", 0 );
      lTempInteger_6 = mi_lTempInteger_6.intValue( );
      szStatementTitlePosition = sb_szStatementTitlePosition.toString( );}
      //:IF szStatementTitlePosition = ""
      if ( ZeidonStringCompare( szStatementTitlePosition, 1, 0, "", 1, 0, 3 ) == 0 )
      { 
         //:szStatementTitlePosition = "SL"
          {StringBuilder sb_szStatementTitlePosition;
         if ( szStatementTitlePosition == null )
            sb_szStatementTitlePosition = new StringBuilder( 32 );
         else
            sb_szStatementTitlePosition = new StringBuilder( szStatementTitlePosition );
                  ZeidonStringCopy( sb_szStatementTitlePosition, 1, 0, "SL", 1, 0, 3 );
         szStatementTitlePosition = sb_szStatementTitlePosition.toString( );}
      } 

      //:END
      //:szConvertToCapsFlag = mSPLDefPDF.LLD_SpecialSectionAttrBlock.CapitalizeTitleTextFlag 
      {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
      StringBuilder sb_szConvertToCapsFlag;
      if ( szConvertToCapsFlag == null )
         sb_szConvertToCapsFlag = new StringBuilder( 32 );
      else
         sb_szConvertToCapsFlag = new StringBuilder( szConvertToCapsFlag );
             GetVariableFromAttribute( sb_szConvertToCapsFlag, mi_lTempInteger_7, 'S', 2, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "CapitalizeTitleTextFlag", "", 0 );
      lTempInteger_7 = mi_lTempInteger_7.intValue( );
      szConvertToCapsFlag = sb_szConvertToCapsFlag.toString( );}
   } 

   //:END

   //:nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" )
   nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" );
   //:LOOP WHILE nRC >= zCURSOR_SET
   while ( nRC >= zCURSOR_SET )
   { 

      //:// Get Continuation Flag used in logic below.
      //:GetStringFromAttribute( szContinuationFlag, mSPLDef, szStatementName, "ContinuationBreakFlag" )
      {StringBuilder sb_szContinuationFlag;
      if ( szContinuationFlag == null )
         sb_szContinuationFlag = new StringBuilder( 32 );
      else
         sb_szContinuationFlag = new StringBuilder( szContinuationFlag );
             GetStringFromAttribute( sb_szContinuationFlag, mSPLDef, szStatementName, "ContinuationBreakFlag" );
      szContinuationFlag = sb_szContinuationFlag.toString( );}

      //:// Create the Display Statement entry, unless this Statement is a full continuation on next page.
      //:IF szContinuationFlag = "" OR szContinuationFlag = "M"
      if ( ZeidonStringCompare( szContinuationFlag, 1, 0, "", 1, 0, 2 ) == 0 || ZeidonStringCompare( szContinuationFlag, 1, 0, "M", 1, 0, 2 ) == 0 )
      { 
         //:mSPLDef.SubregPhysicalLabelDef.wLastDisplaySuffixCount = mSPLDef.SubregPhysicalLabelDef.wLastDisplaySuffixCount + 1
         {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                   GetIntegerFromAttribute( mi_lTempInteger_8, mSPLDef, "SubregPhysicalLabelDef", "wLastDisplaySuffixCount" );
         lTempInteger_8 = mi_lTempInteger_8.intValue( );}
         lTempInteger_9 = lTempInteger_8 + 1;
         SetAttributeFromInteger( mSPLDef, "SubregPhysicalLabelDef", "wLastDisplaySuffixCount", lTempInteger_9 );
         //:szDisplaySectionSuffix = mSPLDef.SubregPhysicalLabelDef.wLastDisplaySuffixCount 
         {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
         StringBuilder sb_szDisplaySectionSuffix;
         if ( szDisplaySectionSuffix == null )
            sb_szDisplaySectionSuffix = new StringBuilder( 32 );
         else
            sb_szDisplaySectionSuffix = new StringBuilder( szDisplaySectionSuffix );
                   GetVariableFromAttribute( sb_szDisplaySectionSuffix, mi_lTempInteger_10, 'S', 4, mSPLDef, "SubregPhysicalLabelDef", "wLastDisplaySuffixCount", "", 0 );
         lTempInteger_10 = mi_lTempInteger_10.intValue( );
         szDisplaySectionSuffix = sb_szDisplaySectionSuffix.toString( );}
         //:szDisplayStatementName = "DisplayStatement" + szDisplaySectionSuffix
          {StringBuilder sb_szDisplayStatementName;
         if ( szDisplayStatementName == null )
            sb_szDisplayStatementName = new StringBuilder( 32 );
         else
            sb_szDisplayStatementName = new StringBuilder( szDisplayStatementName );
                  ZeidonStringCopy( sb_szDisplayStatementName, 1, 0, "DisplayStatement", 1, 0, 51 );
         szDisplayStatementName = sb_szDisplayStatementName.toString( );}
          {StringBuilder sb_szDisplayStatementName;
         if ( szDisplayStatementName == null )
            sb_szDisplayStatementName = new StringBuilder( 32 );
         else
            sb_szDisplayStatementName = new StringBuilder( szDisplayStatementName );
                  ZeidonStringConcat( sb_szDisplayStatementName, 1, 0, szDisplaySectionSuffix, 1, 0, 51 );
         szDisplayStatementName = sb_szDisplayStatementName.toString( );}
         //:CREATE ENTITY mSPLDef.DisplayStatement
         RESULT = CreateEntity( mSPLDef, "DisplayStatement", zPOS_AFTER );
         //:mSPLDef.DisplayStatement.XML_StatementName = szDisplayStatementName
         SetAttributeFromString( mSPLDef, "DisplayStatement", "XML_StatementName", szDisplayStatementName );
      } 

      //:END

      //:IF szContinuationFlag = "M"     // M indicates this Statement is to be broken up into two pieces on separate panels.
      if ( ZeidonStringCompare( szContinuationFlag, 1, 0, "M", 1, 0, 2 ) == 0 )
      { 
         //:// If this statement is to be split for continuation, get text from ContinuationLeadingText.
         //:GetStringFromAttribute( szStatementText, mSPLDef, szStatementName, "ContinuationLeadingText" )
         {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                   GetStringFromAttribute( sb_szStatementText, mSPLDef, szStatementName, "ContinuationLeadingText" );
         szStatementText = sb_szStatementText.toString( );}
         //:ELSE
      } 
      else
      { 
         //:// It's not a continuation, so get all of the Text for the Statement.
         //:GetStringFromAttribute( szStatementText, mSPLDef, szStatementName, "Text" )
         {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                   GetStringFromAttribute( sb_szStatementText, mSPLDef, szStatementName, "Text" );
         szStatementText = sb_szStatementText.toString( );}
      } 

      //:END
      //:GetStringFromAttribute( szStatementTitle, mSPLDef, szStatementName, "Title" )
      {StringBuilder sb_szStatementTitle;
      if ( szStatementTitle == null )
         sb_szStatementTitle = new StringBuilder( 32 );
      else
         sb_szStatementTitle = new StringBuilder( szStatementTitle );
             GetStringFromAttribute( sb_szStatementTitle, mSPLDef, szStatementName, "Title" );
      szStatementTitle = sb_szStatementTitle.toString( );}

      //:// Convert Title to upper-case, if requested.
      //:IF szConvertToCapsFlag = "Y"
      if ( ZeidonStringCompare( szConvertToCapsFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:szTemporaryTitle = szStatementTitle
          {StringBuilder sb_szTemporaryTitle;
         if ( szTemporaryTitle == null )
            sb_szTemporaryTitle = new StringBuilder( 32 );
         else
            sb_szTemporaryTitle = new StringBuilder( szTemporaryTitle );
                  ZeidonStringCopy( sb_szTemporaryTitle, 1, 0, szStatementTitle, 1, 0, 257 );
         szTemporaryTitle = sb_szTemporaryTitle.toString( );}
         //:zToUpper( szTemporaryTitle, szStatementTitle )
         {StringBuilder sb_szStatementTitle;
         if ( szStatementTitle == null )
            sb_szStatementTitle = new StringBuilder( 32 );
         else
            sb_szStatementTitle = new StringBuilder( szStatementTitle );
                   zToUpper( szTemporaryTitle, sb_szStatementTitle );
         szStatementTitle = sb_szStatementTitle.toString( );}
      } 

      //:END

      //:// Combine Title in text if specified.
      //:IF szStatementTitle != "" AND szStatementTitlePosition = "CF"
      if ( ZeidonStringCompare( szStatementTitle, 1, 0, "", 1, 0, 257 ) != 0 && ZeidonStringCompare( szStatementTitlePosition, 1, 0, "CF", 1, 0, 3 ) == 0 )
      { 
         //:// Title is combined with Text.
         //:szTemporaryText = szStatementText
          {StringBuilder sb_szTemporaryText;
         if ( szTemporaryText == null )
            sb_szTemporaryText = new StringBuilder( 32 );
         else
            sb_szTemporaryText = new StringBuilder( szTemporaryText );
                  ZeidonStringCopy( sb_szTemporaryText, 1, 0, szStatementText, 1, 0, 32001 );
         szTemporaryText = sb_szTemporaryText.toString( );}
         //:szStatementText = szStatementTitle + " " + szTemporaryText
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                  ZeidonStringCopy( sb_szStatementText, 1, 0, szStatementTitle, 1, 0, 32001 );
         szStatementText = sb_szStatementText.toString( );}
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                  ZeidonStringConcat( sb_szStatementText, 1, 0, " ", 1, 0, 32001 );
         szStatementText = sb_szStatementText.toString( );}
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                  ZeidonStringConcat( sb_szStatementText, 1, 0, szTemporaryText, 1, 0, 32001 );
         szStatementText = sb_szStatementText.toString( );}
         //:ELSE
      } 
      else
      { 
         //:// Format Title, if it exists and is requested.
         //:IF szStatementTitle != "" AND szStatementTitlePosition = "SL" 
         if ( ZeidonStringCompare( szStatementTitle, 1, 0, "", 1, 0, 257 ) != 0 && ZeidonStringCompare( szStatementTitlePosition, 1, 0, "SL", 1, 0, 3 ) == 0 )
         { 
            //:szWriteBuffer = szLeadingBlanks + "   <fo:block "
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <fo:block ", 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:AddFormatToSpecialText( mSPLDefPDF, "Title", szWriteBuffer )
            {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                         omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Title", sb_szWriteBuffer );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
            try
            {
                {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            };
            }
            catch ( Exception e )
            {
               throw ZeidonException.wrapException( e );
            }

            //://szWriteBuffer = szLeadingBlanks + "      " + szStatementTitle
            //:szWriteBuffer = szLeadingBlanks + "      <xsl:apply-templates select=^SubregPhysicalLabelDef/" 
            //:                                + szDisplaySectionName + "/" + szDisplayStatementName + "/Title^/>"
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      <xsl:apply-templates select=^SubregPhysicalLabelDef/", 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szDisplaySectionName, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "/", 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szDisplayStatementName, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "/Title^/>", 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
            try
            {
                {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            };
            }
            catch ( Exception e )
            {
               throw ZeidonException.wrapException( e );
            }

            //:szWriteBuffer = szLeadingBlanks + "   </fo:block>"
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   </fo:block>", 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
            try
            {
                {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            };
            }
            catch ( Exception e )
            {
               throw ZeidonException.wrapException( e );
            }

            //:mSPLDef.DisplayStatement.Title = szStatementTitle
            SetAttributeFromString( mSPLDef, "DisplayStatement", "Title", szStatementTitle );
         } 

         //:END
      } 

      //:END

      //:// Process any mapping data for the following key words inserted in any text.
      //://    {{Usage}}
      //://    {{Claim}}
      //://    {{Surface}}
      //://    {{Area Of Use}}
      //://    {{Application Type}}
      //://    {{Product Name}}
      //://    {{Master Product}}
      //://    {{Primary Registrant}}
      //://    {{Subregistrant}}
      //://
      //:IF szUsageTypeEntity != ""
      if ( ZeidonStringCompare( szUsageTypeEntity, 1, 0, "", 1, 0, 33 ) != 0 )
      { 
         //:// Storage and Disposal has no keyword mapping.
         //:InsertMappingWordsIntoString( mSPLDef, szStatementText, szUsageTypeEntity, szLoopingEntity, szSeparatorCharacters )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( mSPLDef );
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                   m_ZGlobalV_Operation.InsertMappingWordsIntoString( mSPLDef, sb_szStatementText, szUsageTypeEntity, szLoopingEntity, szSeparatorCharacters );
         szStatementText = sb_szStatementText.toString( );}
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
         //:SetAttributeFromString( mSPLDef, szStatementName, "DisplayText", szStatementText )
         SetAttributeFromString( mSPLDef, szStatementName, "DisplayText", szStatementText );
      } 

      //:END

      //:// If Statement is flagged as Continuation, process as:
      //:// If the Statement has Continuation Text, format the regular text here, and set up the Continuation subobject for continuation
      //:// on the next Panel for all remaining Statement entities.
      //:// If the Statement does NOT have Continuation Text, format this and all remaining Statement entities on next Panel.

      //:IF szContinuationFlag = ""
      if ( ZeidonStringCompare( szContinuationFlag, 1, 0, "", 1, 0, 2 ) == 0 )
      { 

         //:// Process regular Statement Text.

         //:szWriteBuffer = szLeadingBlanks + "<fo:block "
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<fo:block ", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
         //:AddFormatToSpecialText( mSPLDefPDF, "Text", szWriteBuffer )
         {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                   omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Text", sb_szWriteBuffer );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
         //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
         try
         {
             {
          ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
          m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
          // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
         };
         }
         catch ( Exception e )
         {
            throw ZeidonException.wrapException( e );
         }

         //://szWriteBuffer = szLeadingBlanks + "      " + szStatementText
         //:szWriteBuffer = szLeadingBlanks + "   <xsl:apply-templates select=^SubregPhysicalLabelDef/" 
         //:                                   + szDisplaySectionName + "/" + szDisplayStatementName + "/Text^/>"
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <xsl:apply-templates select=^SubregPhysicalLabelDef/", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szDisplaySectionName, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "/", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szDisplayStatementName, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "/Text^/>", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
         //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
         try
         {
             {
          ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
          m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
          // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
         };
         }
         catch ( Exception e )
         {
            throw ZeidonException.wrapException( e );
         }

         //:szWriteBuffer = szLeadingBlanks + "</fo:block>"
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block>", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
         //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
         try
         {
             {
          ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
          m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
          // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
         };
         }
         catch ( Exception e )
         {
            throw ZeidonException.wrapException( e );
         }

         //:mSPLDef.DisplayStatement.Text = szStatementText
         SetAttributeFromString( mSPLDef, "DisplayStatement", "Text", szStatementText );

         //:ELSE
      } 
      else
      { 

         //:// Process Continuation.
         //:// Skip if a prior statement was a continuation.
         //:IF szBlockContinuationType = ""
         if ( ZeidonStringCompare( szBlockContinuationType, 1, 0, "", 1, 0, 2 ) == 0 )
         { 

            //:// If this is a partial text continuation, format the first part of the text on the current Block.
            //:IF szContinuationFlag = "M" 
            if ( ZeidonStringCompare( szContinuationFlag, 1, 0, "M", 1, 0, 2 ) == 0 )
            { 
               //:szWriteBuffer = szLeadingBlanks + "   <fo:block "
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <fo:block ", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:AddFormatToSpecialText( mSPLDefPDF, "Text", szWriteBuffer )
               {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                               omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Text", sb_szWriteBuffer );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
               try
               {
                   {
                ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
               };
               }
               catch ( Exception e )
               {
                  throw ZeidonException.wrapException( e );
               }

               //:szWriteBuffer = szLeadingBlanks + "      <xsl:apply-templates select=^SubregPhysicalLabelDef/" 
               //:                                + szDisplaySectionName + "/" + szDisplayStatementName + "/Text^/>"
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      <xsl:apply-templates select=^SubregPhysicalLabelDef/", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szDisplaySectionName, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "/", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szDisplayStatementName, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "/Text^/>", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
               try
               {
                   {
                ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
               };
               }
               catch ( Exception e )
               {
                  throw ZeidonException.wrapException( e );
               }

               //:szWriteBuffer = szLeadingBlanks + "   </fo:block>"
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   </fo:block>", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
               try
               {
                   {
                ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
               };
               }
               catch ( Exception e )
               {
                  throw ZeidonException.wrapException( e );
               }

               //:mSPLDef.DisplayStatement.Text = szStatementText
               SetAttributeFromString( mSPLDef, "DisplayStatement", "Text", szStatementText );
            } 

            //:END

            //:// When this code was written there was a core error where an entity created under view mSPLDefPanelLevel couldn't
            //:// be seen under view mSPLDefPDF. Thus the reason for the somewhat awkward code below where mSPLDefPanelLevel
            //:// is the same view as mSPLDefPDF for the case where the continuation is to the next Block.

            //:// First look to see if the next Block is a Continuation Block and set flag.
            //:SET CURSOR NEXT mSPLDefPDF.LLD_Block    
            RESULT = SetCursorNextEntity( mSPLDefPDF, "LLD_Block", "" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:IF mSPLDefPDF.LLD_Block.ContinuationBlockFlag = "Y" 
               if ( CompareAttributeToString( mSPLDefPDF, "LLD_Block", "ContinuationBlockFlag", "Y" ) == 0 )
               { 
                  //:szBlockContinuationType = "B"
                   {StringBuilder sb_szBlockContinuationType;
                  if ( szBlockContinuationType == null )
                     sb_szBlockContinuationType = new StringBuilder( 32 );
                  else
                     sb_szBlockContinuationType = new StringBuilder( szBlockContinuationType );
                                    ZeidonStringCopy( sb_szBlockContinuationType, 1, 0, "B", 1, 0, 2 );
                  szBlockContinuationType = sb_szBlockContinuationType.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szBlockContinuationType = "P"
                   {StringBuilder sb_szBlockContinuationType;
                  if ( szBlockContinuationType == null )
                     sb_szBlockContinuationType = new StringBuilder( 32 );
                  else
                     sb_szBlockContinuationType = new StringBuilder( szBlockContinuationType );
                                    ZeidonStringCopy( sb_szBlockContinuationType, 1, 0, "P", 1, 0, 2 );
                  szBlockContinuationType = sb_szBlockContinuationType.toString( );}
                  //:SET CURSOR PREVIOUS mSPLDefPDF.LLD_Block     // We need to undo the set cursor next above.
                  RESULT = SetCursorPrevEntity( mSPLDefPDF, "LLD_Block", "" );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:szBlockContinuationType = "P"
                {StringBuilder sb_szBlockContinuationType;
               if ( szBlockContinuationType == null )
                  sb_szBlockContinuationType = new StringBuilder( 32 );
               else
                  sb_szBlockContinuationType = new StringBuilder( szBlockContinuationType );
                              ZeidonStringCopy( sb_szBlockContinuationType, 1, 0, "P", 1, 0, 2 );
               szBlockContinuationType = sb_szBlockContinuationType.toString( );}
            } 

            //:END

            //:// Create Panel view that will hold Continuation statements and position on next Panel, if this
            //:// is a Panel continuation, rather than a Block continuation.
            //:CreateViewFromView( mSPLDefPanelLevel, mSPLDef )
            CreateViewFromView( mSPLDefPanelLevel, mSPLDef );
            //:NAME VIEW mSPLDefPanelLevel "mSPLDefPanelLevel"
            SetNameForView( mSPLDefPanelLevel, "mSPLDefPanelLevel", null, zLEVEL_TASK );
            //:IF szBlockContinuationType = "P"
            if ( ZeidonStringCompare( szBlockContinuationType, 1, 0, "P", 1, 0, 2 ) == 0 )
            { 
               //:SET CURSOR NEXT mSPLDefPanelLevel.LLD_Panel
               RESULT = SetCursorNextEntity( mSPLDefPanelLevel, "LLD_Panel", "" );
            } 

            //:END
            //:// Remove any existing Continuation Statements.
            //:FOR EACH mSPLDefPanelLevel.ContinuationStatement 
            RESULT = SetCursorFirstEntity( mSPLDefPanelLevel, "ContinuationStatement", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:DELETE ENTITY mSPLDefPanelLevel.ContinuationStatement NONE 
               RESULT = DeleteEntity( mSPLDefPanelLevel, "ContinuationStatement", zREPOS_NONE );
               RESULT = SetCursorNextEntity( mSPLDefPanelLevel, "ContinuationStatement", "" );
            } 

            //:END

            //:IF szBlockContinuationType = "B"
            if ( ZeidonStringCompare( szBlockContinuationType, 1, 0, "B", 1, 0, 2 ) == 0 )
            { 

               //:// Block continuation

               //:IF szContinuationFlag = "M"    
               if ( ZeidonStringCompare( szContinuationFlag, 1, 0, "M", 1, 0, 2 ) == 0 )
               { 
                  //:// Continuation is in two pieces, so next text is ContinuationText.
                  //:GetStringFromAttribute( szTemporaryText, mSPLDef, szStatementName, "ContinuationText" )
                  {StringBuilder sb_szTemporaryText;
                  if ( szTemporaryText == null )
                     sb_szTemporaryText = new StringBuilder( 32 );
                  else
                     sb_szTemporaryText = new StringBuilder( szTemporaryText );
                                     GetStringFromAttribute( sb_szTemporaryText, mSPLDef, szStatementName, "ContinuationText" );
                  szTemporaryText = sb_szTemporaryText.toString( );}
                  //:CREATE ENTITY mSPLDefPanelLevel.ContinuationStatement 
                  RESULT = CreateEntity( mSPLDefPanelLevel, "ContinuationStatement", zPOS_AFTER );
                  //:mSPLDefPanelLevel.ContinuationStatement.Text = szTemporaryText
                  SetAttributeFromString( mSPLDefPanelLevel, "ContinuationStatement", "Text", szTemporaryText );
                  //:ELSE
               } 
               else
               { 
                  //:// Initialize Continuation entry with Regular Text.
                  //:CREATE ENTITY mSPLDefPanelLevel.ContinuationStatement 
                  RESULT = CreateEntity( mSPLDefPanelLevel, "ContinuationStatement", zPOS_AFTER );
                  //:IF szStatementTitle != "" AND szStatementTitlePosition = "SL" 
                  if ( ZeidonStringCompare( szStatementTitle, 1, 0, "", 1, 0, 257 ) != 0 && ZeidonStringCompare( szStatementTitlePosition, 1, 0, "SL", 1, 0, 3 ) == 0 )
                  { 
                     //:mSPLDefPanelLevel.ContinuationStatement.Title = szStatementTitle
                     SetAttributeFromString( mSPLDefPanelLevel, "ContinuationStatement", "Title", szStatementTitle );
                  } 

                  //:END
                  //:mSPLDefPanelLevel.ContinuationStatement.Text = szStatementText
                  SetAttributeFromString( mSPLDefPanelLevel, "ContinuationStatement", "Text", szStatementText );
               } 

               //:END

               //:// Add any remaining Statement entries to continuation, that will be generated for the next Panel.
               //:// This will position us on the last Statement under the Section so that we will exit this operation without generating
               //:// another Statement for this Panel.
               //:nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" )
               nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" );
               //:LOOP WHILE nRC >= zCURSOR_SET
               while ( nRC >= zCURSOR_SET )
               { 
                  //:GetStringFromAttribute( szStatementText, mSPLDef, szStatementName, "Text" )
                  {StringBuilder sb_szStatementText;
                  if ( szStatementText == null )
                     sb_szStatementText = new StringBuilder( 32 );
                  else
                     sb_szStatementText = new StringBuilder( szStatementText );
                                     GetStringFromAttribute( sb_szStatementText, mSPLDef, szStatementName, "Text" );
                  szStatementText = sb_szStatementText.toString( );}
                  //:GetStringFromAttribute( szStatementTitle, mSPLDef, szStatementName, "Title" )
                  {StringBuilder sb_szStatementTitle;
                  if ( szStatementTitle == null )
                     sb_szStatementTitle = new StringBuilder( 32 );
                  else
                     sb_szStatementTitle = new StringBuilder( szStatementTitle );
                                     GetStringFromAttribute( sb_szStatementTitle, mSPLDef, szStatementName, "Title" );
                  szStatementTitle = sb_szStatementTitle.toString( );}
                  //:CREATE ENTITY mSPLDefPanelLevel.ContinuationStatement 
                  RESULT = CreateEntity( mSPLDefPanelLevel, "ContinuationStatement", zPOS_AFTER );
                  //:IF szStatementTitle != "" AND szStatementTitlePosition = "SL" 
                  if ( ZeidonStringCompare( szStatementTitle, 1, 0, "", 1, 0, 257 ) != 0 && ZeidonStringCompare( szStatementTitlePosition, 1, 0, "SL", 1, 0, 3 ) == 0 )
                  { 
                     //:mSPLDefPanelLevel.ContinuationStatement.Title = szStatementTitle
                     SetAttributeFromString( mSPLDefPanelLevel, "ContinuationStatement", "Title", szStatementTitle );
                  } 

                  //:END
                  //:mSPLDefPanelLevel.ContinuationStatement.Text = szStatementText
                  SetAttributeFromString( mSPLDefPanelLevel, "ContinuationStatement", "Text", szStatementText );

                  //:nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" )
                  nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" );
               } 

               //:END
               //:SET CURSOR PREVIOUS mSPLDefPDF.LLD_Block     // We need to undo the set cursor next above.
               RESULT = SetCursorPrevEntity( mSPLDefPDF, "LLD_Block", "" );
               //:ELSE
            } 
            else
            { 

               //:// Panel continuation

               //:IF szContinuationFlag = "M"    
               if ( ZeidonStringCompare( szContinuationFlag, 1, 0, "M", 1, 0, 2 ) == 0 )
               { 
                  //:// Continuation is in two pieces, so next text is ContinuationText.
                  //:GetStringFromAttribute( szTemporaryText, mSPLDef, szStatementName, "ContinuationText" )
                  {StringBuilder sb_szTemporaryText;
                  if ( szTemporaryText == null )
                     sb_szTemporaryText = new StringBuilder( 32 );
                  else
                     sb_szTemporaryText = new StringBuilder( szTemporaryText );
                                     GetStringFromAttribute( sb_szTemporaryText, mSPLDef, szStatementName, "ContinuationText" );
                  szTemporaryText = sb_szTemporaryText.toString( );}
                  //:CREATE ENTITY mSPLDefPanelLevel.ContinuationStatement 
                  RESULT = CreateEntity( mSPLDefPanelLevel, "ContinuationStatement", zPOS_AFTER );
                  //:mSPLDefPanelLevel.ContinuationStatement.Text = szTemporaryText
                  SetAttributeFromString( mSPLDefPanelLevel, "ContinuationStatement", "Text", szTemporaryText );
                  //:ELSE
               } 
               else
               { 
                  //:// Initialize Continuation entry with Regular Text.
                  //:CREATE ENTITY mSPLDefPanelLevel.ContinuationStatement 
                  RESULT = CreateEntity( mSPLDefPanelLevel, "ContinuationStatement", zPOS_AFTER );
                  //:IF szStatementTitle != "" AND szStatementTitlePosition = "SL" 
                  if ( ZeidonStringCompare( szStatementTitle, 1, 0, "", 1, 0, 257 ) != 0 && ZeidonStringCompare( szStatementTitlePosition, 1, 0, "SL", 1, 0, 3 ) == 0 )
                  { 
                     //:mSPLDefPanelLevel.ContinuationStatement.Title = szStatementTitle
                     SetAttributeFromString( mSPLDefPanelLevel, "ContinuationStatement", "Title", szStatementTitle );
                  } 

                  //:END
                  //:mSPLDefPanelLevel.ContinuationStatement.Text = szStatementText
                  SetAttributeFromString( mSPLDefPanelLevel, "ContinuationStatement", "Text", szStatementText );
               } 

               //:END

               //:// Copy BlockSpecialAttributeBlock entries.
               //:// I decided not to do the following but use the formatting characteristics specified on the Coninuation Block.
               //:/*FOR EACH mSPLDefPanelLevel.LLD_SpecialSectionAttribute
               //:   DELETE ENTITY mSPLDefPanelLevel.LLD_SpecialSectionAttribute NONE 
               //:END
               //:FOR EACH mSPLDefPDF.LLD_SpecialSectionAttribute 
               //:   CREATE ENTITY mSPLDefPanelLevel.LLD_SpecialSectionAttribute 
               //:   mSPLDefPanelLevel.LLD_SpecialSectionAttribute.Name = mSPLDefPDF.LLD_SpecialSectionAttribute.Name 
               //:   CREATE ENTITY mSPLDefPanelLevel.LLD_SpecialSectionAttrBlock 
               //:   SetMatchingAttributesByName( mSPLDefPanelLevel, "LLD_SpecialSectionAttrBlock", mSPLDefPDF, "LLD_SpecialSectionAttrBlock", zSET_NULL )     
               //:END*/

               //:// Add any remaining Statement entries to continuation, that will be generated for the next Panel.
               //:// This will position us on the last Statement under the Section so that we will exit this operation without generating
               //:// another Statement for this Panel.
               //:nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" )
               nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" );
               //:LOOP WHILE nRC >= zCURSOR_SET
               while ( nRC >= zCURSOR_SET )
               { 
                  //:GetStringFromAttribute( szStatementText, mSPLDef, szStatementName, "Text" )
                  {StringBuilder sb_szStatementText;
                  if ( szStatementText == null )
                     sb_szStatementText = new StringBuilder( 32 );
                  else
                     sb_szStatementText = new StringBuilder( szStatementText );
                                     GetStringFromAttribute( sb_szStatementText, mSPLDef, szStatementName, "Text" );
                  szStatementText = sb_szStatementText.toString( );}
                  //:GetStringFromAttribute( szStatementTitle, mSPLDef, szStatementName, "Title" )
                  {StringBuilder sb_szStatementTitle;
                  if ( szStatementTitle == null )
                     sb_szStatementTitle = new StringBuilder( 32 );
                  else
                     sb_szStatementTitle = new StringBuilder( szStatementTitle );
                                     GetStringFromAttribute( sb_szStatementTitle, mSPLDef, szStatementName, "Title" );
                  szStatementTitle = sb_szStatementTitle.toString( );}
                  //:CREATE ENTITY mSPLDefPanelLevel.ContinuationStatement 
                  RESULT = CreateEntity( mSPLDefPanelLevel, "ContinuationStatement", zPOS_AFTER );
                  //:IF szStatementTitle != "" AND szStatementTitlePosition = "SL" 
                  if ( ZeidonStringCompare( szStatementTitle, 1, 0, "", 1, 0, 257 ) != 0 && ZeidonStringCompare( szStatementTitlePosition, 1, 0, "SL", 1, 0, 3 ) == 0 )
                  { 
                     //:mSPLDefPanelLevel.ContinuationStatement.Title = szStatementTitle
                     SetAttributeFromString( mSPLDefPanelLevel, "ContinuationStatement", "Title", szStatementTitle );
                  } 

                  //:END
                  //:mSPLDefPanelLevel.ContinuationStatement.Text = szStatementText
                  SetAttributeFromString( mSPLDefPanelLevel, "ContinuationStatement", "Text", szStatementText );

                  //:nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" )
                  nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" );
               } 

               //:END

               //:// Since this is a Panel continuation, add the Continuation verbage to the current Panel.
               //:szWriteBuffer = szLeadingBlanks + "   <fo:block margin-top=^.05in^>"
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <fo:block margin-top=^.05in^>", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
               try
               {
                   {
                ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
               };
               }
               catch ( Exception e )
               {
                  throw ZeidonException.wrapException( e );
               }

               //:szWriteBuffer = szLeadingBlanks + "      " + mSPLDef.SPLD_LLD.ContinuationPreviousPageText 
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      ", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_11, 'S', 32001, mSPLDef, "SPLD_LLD", "ContinuationPreviousPageText", "", 0 );
               lTempInteger_11 = mi_lTempInteger_11.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTempString_0, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
               try
               {
                   {
                ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
               };
               }
               catch ( Exception e )
               {
                  throw ZeidonException.wrapException( e );
               }

               //:szWriteBuffer = szLeadingBlanks + "   </fo:block>"
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   </fo:block>", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
               try
               {
                   {
                ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
                // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
               };
               }
               catch ( Exception e )
               {
                  throw ZeidonException.wrapException( e );
               }
            } 

            //:END

            //:DropView( mSPLDefPanelLevel )
            DropView( mSPLDefPanelLevel );
         } 

         //:END
      } 

      //:   
      //:END

      //:nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" )
      nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GeneratePDF_ClmList( VIEW mSPLDef      BASED ON LOD mSPLDef,
//:                     VIEW mSPLDefPDF BASED ON LOD mSPLDef,
//:                     INTEGER lFile,
//:                     STRING ( 256 )   szPassedBlanks,
//:                     STRING ( 32000 ) szWriteBuffer,
//:                     INTEGER NumberColumns )

//:   VIEW mSPLDef2 BASED ON LOD mSPLDef
public int 
omSPLDef_GeneratePDF_ClmList( View     mSPLDef,
                              View     mSPLDefPDF,
                              int      lFile,
                              String   szPassedBlanks,
                              String   szWriteBuffer,
                              int      NumberColumns )
{
   zVIEW    mSPLDef2 = new zVIEW( );
   //:STRING ( 32000 )  szStatementText
   String   szStatementText = null;
   //:STRING ( 32000 )  szTemporaryText
   String   szTemporaryText = null;
   //:STRING ( 50 )     szLeadingBlanks
   String   szLeadingBlanks = null;
   //:STRING ( 50 )     szBreakName1
   String   szBreakName1 = null;
   //:STRING ( 50 )     szBreakName2
   String   szBreakName2 = null;
   //:STRING ( 10 )     szMarginRight
   String   szMarginRight = null;
   //:STRING ( 10 )     szMarginLeft
   String   szMarginLeft = null;
   //:STRING ( 100 )    szSectionTitle
   String   szSectionTitle = null;
   //:STRING ( 100 )    szStatementTitle
   String   szStatementTitle = null;
   //:STRING ( 10 )     szTitleFormat
   String   szTitleFormat = null;
   //:STRING ( 50 )     szSectionName
   String   szSectionName = null;
   //:STRING ( 10 )     szColumnTop
   String   szColumnTop = null;
   //:STRING ( 100 )    szCombinedName
   String   szCombinedName = null;
   //:STRING ( 100 )    szFootnoteText
   String   szFootnoteText = null;
   //:STRING ( 3 )      szFootnoteNumber
   String   szFootnoteNumber = null;
   //:SHORT             nRC
   int      nRC = 0;
   //:INTEGER           CurrentColumnNumber
   int      CurrentColumnNumber = 0;
   //:INTEGER           ItemCount
   int      ItemCount = 0;
   //:INTEGER           FootnoteCount
   int      FootnoteCount = 0;
   //:INTEGER           Column1Count
   int      Column1Count = 0;
   //:INTEGER           Column2Count
   int      Column2Count = 0;
   //:INTEGER           ColumnTotal
   int      ColumnTotal = 0;
   //:DECIMAL           ContainingBlockWidth
   double  ContainingBlockWidth = 0.0;
   //:DECIMAL           ColumnWidth
   double  ColumnWidth = 0.0;
   //:DECIMAL           ColumnHeight
   double  ColumnHeight = 0.0;
   //:DECIMAL           OriginalTopMargin
   double  OriginalTopMargin = 0.0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   int      lTempInteger_13 = 0;
   int      lTempInteger_14 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   int      lTempInteger_15 = 0;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;
   double  dTempDecimal_4 = 0.0;
   double  dTempDecimal_5 = 0.0;
   int      lTempInteger_16 = 0;


   //:// Generate PDF for a "Directory of Use" or "Marketing" Section.

   //:szLeadingBlanks = szPassedBlanks + "   "
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringCopy( sb_szLeadingBlanks, 1, 0, szPassedBlanks, 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringConcat( sb_szLeadingBlanks, 1, 0, "   ", 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}

   //:// Column List of Claim Usage Statements
   //:// The dependent Claim Usage statements are to be listed in 2 or 3 columns, after any regular Statements
   //:// and organized by Claims Classifications.

   //:// Position on the correct Marketing Section.
   //:SET CURSOR FIRST mSPLDef.SPLD_MarketingSection WHERE mSPLDef.SPLD_MarketingSection.Name = mSPLDefPDF.LLD_Block.Name    
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, mSPLDefPDF, "LLD_Block", "Name" );
   szTempString_0 = sb_szTempString_0.toString( );}
   RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_MarketingSection", "Name", szTempString_0, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:IssueError( mSPLDef,0,0, "No match on MarketingSection for Column" )
      IssueError( mSPLDef, 0, 0, "No match on MarketingSection for Column" );
   } 

   //:END

   //:// Build the list of unique footnotes.
   //:FOR EACH mSPLDef.M_UsageFootnote 
   RESULT = SetCursorFirstEntity( mSPLDef, "M_UsageFootnote", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:EXCLUDE mSPLDef.M_UsageFootnote NONE
      RESULT = ExcludeEntity( mSPLDef, "M_UsageFootnote", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "M_UsageFootnote", "" );
   } 

   //:END
   //:ItemCount = 0
   ItemCount = 0;
   //:FOR EACH mSPLDef.SPLD_MarketingStatement 
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH mSPLDef.SPLD_MarketingUsageOrdering WITHIN mSPLDef.SPLD_MarketingSection
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "SPLD_MarketingSection" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSPLDef.SPLD_MarketingUsage.UsageType = "C"
         if ( CompareAttributeToString( mSPLDef, "SPLD_MarketingUsage", "UsageType", "C" ) == 0 )
         { 
            //:ItemCount = ItemCount + 1
            ItemCount = ItemCount + 1;
            //:SET CURSOR FIRST mSPLDef.SPLD_Usage 
            //:           WHERE mSPLDef.SPLD_Usage.ID = mSPLDef.SPLD_MarketingUsage.ID 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mSPLDef, "SPLD_MarketingUsage", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_Usage", "ID", lTempInteger_0, "" );
            //:IF RESULT >=zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:IF mSPLDef.M_UsageFootnoteUsed EXISTS 
               lTempInteger_1 = CheckExistenceOfEntity( mSPLDef, "M_UsageFootnoteUsed" );
               if ( lTempInteger_1 == 0 )
               { 
                  //:SET CURSOR FIRST mSPLDef.M_UsageFootnote 
                  //:           WHERE mSPLDef.M_UsageFootnote.ID = mSPLDef.M_UsageFootnoteUsed.ID 
                  {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                                     GetIntegerFromAttribute( mi_lTempInteger_2, mSPLDef, "M_UsageFootnoteUsed", "ID" );
                  lTempInteger_2 = mi_lTempInteger_2.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mSPLDef, "M_UsageFootnote", "ID", lTempInteger_2, "" );
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:CREATE ENTITY mSPLDef.M_UsageFootnote 
                     RESULT = CreateEntity( mSPLDef, "M_UsageFootnote", zPOS_AFTER );
                     //:mSPLDef.M_UsageFootnote.ID   = mSPLDef.M_UsageFootnoteUsed.ID 
                     SetAttributeFromAttribute( mSPLDef, "M_UsageFootnote", "ID", mSPLDef, "M_UsageFootnoteUsed", "ID" );
                     //:mSPLDef.M_UsageFootnote.Text = mSPLDef.M_UsageFootnoteUsed.Text 
                     SetAttributeFromAttribute( mSPLDef, "M_UsageFootnote", "Text", mSPLDef, "M_UsageFootnoteUsed", "Text" );
                  } 

                  //:END 
               } 

               //:END
            } 

            //:END
         } 

         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "SPLD_MarketingSection" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingStatement", "" );
      //:END
   } 

   //:END
   //:IF ItemCount = 0
   if ( ItemCount == 0 )
   { 
      //:// There are no Claims entries, so give error and exit.
      //:MessageSend( mSPLDef, "", "Generate Label",
      //:             "The Marketing Section for Column list has no Claim entries.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mSPLDef, "", "Generate Label", "The Marketing Section for Column list has no Claim entries.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:FootnoteCount = 0
   FootnoteCount = 0;
   //:FOR EACH mSPLDef.M_UsageFootnote 
   RESULT = SetCursorFirstEntity( mSPLDef, "M_UsageFootnote", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FootnoteCount = FootnoteCount + 1
      FootnoteCount = FootnoteCount + 1;
      //:ItemCount     = ItemCount + 1  // Also add to total item count.
      ItemCount = ItemCount + 1;
      //:mSPLDef.M_UsageFootnote.wFootNoteRelativeNumber = FootnoteCount
      SetAttributeFromInteger( mSPLDef, "M_UsageFootnote", "wFootNoteRelativeNumber", FootnoteCount );
      RESULT = SetCursorNextEntity( mSPLDef, "M_UsageFootnote", "" );
   } 

   //:END

   //:// Set the work attribute with combination Usage Name and Footnote designator.
   //:FOR EACH mSPLDef.SPLD_MarketingStatement 
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH mSPLDef.SPLD_MarketingUsageOrdering WITHIN mSPLDef.SPLD_MarketingSection
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "SPLD_MarketingSection" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:szCombinedName = mSPLDef.SPLD_MarketingUsage.Name 
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szCombinedName;
         if ( szCombinedName == null )
            sb_szCombinedName = new StringBuilder( 32 );
         else
            sb_szCombinedName = new StringBuilder( szCombinedName );
                   GetVariableFromAttribute( sb_szCombinedName, mi_lTempInteger_3, 'S', 101, mSPLDef, "SPLD_MarketingUsage", "Name", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         szCombinedName = sb_szCombinedName.toString( );}
         //:SET CURSOR FIRST mSPLDef.SPLD_Usage 
         //:           WHERE mSPLDef.SPLD_Usage.ID = mSPLDef.SPLD_MarketingUsage.ID 
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                   GetIntegerFromAttribute( mi_lTempInteger_4, mSPLDef, "SPLD_MarketingUsage", "ID" );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_Usage", "ID", lTempInteger_4, "" );
         //:IF RESULT >=zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:IF mSPLDef.M_UsageFootnoteUsed EXISTS 
            lTempInteger_5 = CheckExistenceOfEntity( mSPLDef, "M_UsageFootnoteUsed" );
            if ( lTempInteger_5 == 0 )
            { 
               //:SET CURSOR FIRST mSPLDef.M_UsageFootnote 
               //:           WHERE mSPLDef.M_UsageFootnote.ID = mSPLDef.M_UsageFootnoteUsed.ID 
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                               GetIntegerFromAttribute( mi_lTempInteger_6, mSPLDef, "M_UsageFootnoteUsed", "ID" );
               lTempInteger_6 = mi_lTempInteger_6.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mSPLDef, "M_UsageFootnote", "ID", lTempInteger_6, "" );
               //:szFootnoteNumber = mSPLDef.M_UsageFootnote.wFootNoteRelativeNumber 
               {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
               StringBuilder sb_szFootnoteNumber;
               if ( szFootnoteNumber == null )
                  sb_szFootnoteNumber = new StringBuilder( 32 );
               else
                  sb_szFootnoteNumber = new StringBuilder( szFootnoteNumber );
                               GetVariableFromAttribute( sb_szFootnoteNumber, mi_lTempInteger_7, 'S', 4, mSPLDef, "M_UsageFootnote", "wFootNoteRelativeNumber", "", 0 );
               lTempInteger_7 = mi_lTempInteger_7.intValue( );
               szFootnoteNumber = sb_szFootnoteNumber.toString( );}
               //:szCombinedName = szCombinedName + "<sup> " + szFootnoteNumber + "</sup>"
                {StringBuilder sb_szCombinedName;
               if ( szCombinedName == null )
                  sb_szCombinedName = new StringBuilder( 32 );
               else
                  sb_szCombinedName = new StringBuilder( szCombinedName );
                              ZeidonStringConcat( sb_szCombinedName, 1, 0, "<sup> ", 1, 0, 101 );
               szCombinedName = sb_szCombinedName.toString( );}
                {StringBuilder sb_szCombinedName;
               if ( szCombinedName == null )
                  sb_szCombinedName = new StringBuilder( 32 );
               else
                  sb_szCombinedName = new StringBuilder( szCombinedName );
                              ZeidonStringConcat( sb_szCombinedName, 1, 0, szFootnoteNumber, 1, 0, 101 );
               szCombinedName = sb_szCombinedName.toString( );}
                {StringBuilder sb_szCombinedName;
               if ( szCombinedName == null )
                  sb_szCombinedName = new StringBuilder( 32 );
               else
                  sb_szCombinedName = new StringBuilder( szCombinedName );
                              ZeidonStringConcat( sb_szCombinedName, 1, 0, "</sup>", 1, 0, 101 );
               szCombinedName = sb_szCombinedName.toString( );}
            } 

            //:END
            //:mSPLDef.SPLD_MarketingUsage.wNameWithFootnoteDesignator = szCombinedName
            SetAttributeFromString( mSPLDef, "SPLD_MarketingUsage", "wNameWithFootnoteDesignator", szCombinedName );
         } 

         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "SPLD_MarketingSection" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingStatement", "" );
      //:END
   } 

   //:END

   //:// Build the two or three columns subobjects from the SPLD_MarketingUsageOrdering entities.
   //:// If specific breaks have been specified, break there. If not, put the same number of entries in each column.
   //:IF mSPLDefPDF.LLD_Block.UsageColumn1BreakName = ""
   if ( CompareAttributeToString( mSPLDefPDF, "LLD_Block", "UsageColumn1BreakName", "" ) == 0 )
   { 
      //:Column1Count = ItemCount / NumberColumns
      Column1Count = ItemCount / NumberColumns;
      //:ColumnTotal = Column1Count * NumberColumns
      ColumnTotal = Column1Count * NumberColumns;
      //:IF ColumnTotal < ItemCount
      if ( ColumnTotal < ItemCount )
      { 
         //:// Since column aren't going to be equal, add one to first and second columns.
         //:Column1Count = Column1Count + 1
         Column1Count = Column1Count + 1;
      } 

      //:END
      //:Column2Count = Column1Count * 2    // The last count of the second column is twice the last count of the first.
      Column2Count = Column1Count * 2;
      //:ItemCount = 0
      ItemCount = 0;
      //:FOR EACH mSPLDef.SPLD_MarketingUsageOrdering WITHIN mSPLDef.SPLD_MarketingSection 
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "SPLD_MarketingSection" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSPLDef.SPLD_MarketingUsage.UsageType = "C"
         if ( CompareAttributeToString( mSPLDef, "SPLD_MarketingUsage", "UsageType", "C" ) == 0 )
         { 
            //:ItemCount = ItemCount + 1
            ItemCount = ItemCount + 1;
            //:IF ItemCount <= Column1Count
            if ( ItemCount <= Column1Count )
            { 
               //:CREATE ENTITY mSPLDef.DisplayUsageColumn1 
               RESULT = CreateEntity( mSPLDef, "DisplayUsageColumn1", zPOS_AFTER );
               //:mSPLDef.DisplayUsageColumn1.Name = mSPLDef.SPLD_MarketingUsage.wNameWithFootnoteDesignator 
               SetAttributeFromAttribute( mSPLDef, "DisplayUsageColumn1", "Name", mSPLDef, "SPLD_MarketingUsage", "wNameWithFootnoteDesignator" );
               //:ELSE
            } 
            else
            { 
               //:IF ItemCount > Column1Count AND ItemCount <= Column2Count
               if ( ItemCount > Column1Count && ItemCount <= Column2Count )
               { 
                  //:CREATE ENTITY mSPLDef.DisplayUsageColumn2 
                  RESULT = CreateEntity( mSPLDef, "DisplayUsageColumn2", zPOS_AFTER );
                  //:mSPLDef.DisplayUsageColumn2.Name = mSPLDef.SPLD_MarketingUsage.wNameWithFootnoteDesignator 
                  SetAttributeFromAttribute( mSPLDef, "DisplayUsageColumn2", "Name", mSPLDef, "SPLD_MarketingUsage", "wNameWithFootnoteDesignator" );
                  //:ELSE
               } 
               else
               { 
                  //:CREATE ENTITY mSPLDef.DisplayUsageColumn3  
                  RESULT = CreateEntity( mSPLDef, "DisplayUsageColumn3", zPOS_AFTER );
                  //:mSPLDef.DisplayUsageColumn3.Name = mSPLDef.SPLD_MarketingUsage.wNameWithFootnoteDesignator 
                  SetAttributeFromAttribute( mSPLDef, "DisplayUsageColumn3", "Name", mSPLDef, "SPLD_MarketingUsage", "wNameWithFootnoteDesignator" );
               } 

               //:END 
            } 

            //:END
         } 

         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "SPLD_MarketingSection" );
         //:END
      } 

      //:END

      //:// Set CurrentColumnNumber for any footnote processing below.
      //:IF ItemCount <= Column1Count
      if ( ItemCount <= Column1Count )
      { 
         //:CurrentColumnNumber = 1
         CurrentColumnNumber = 1;
         //:ELSE
      } 
      else
      { 
         //:IF ItemCount <= Column2Count
         if ( ItemCount <= Column2Count )
         { 
            //:CurrentColumnNumber = 2
            CurrentColumnNumber = 2;
            //:ELSE
         } 
         else
         { 
            //:CurrentColumnNumber = 3
            CurrentColumnNumber = 3;
         } 

         //:END
      } 

      //:END

      //:ELSE
   } 
   else
   { 
      //:// Specific Breaks have been specified.
      //:szBreakName1 = mSPLDefPDF.LLD_Block.UsageColumn1BreakName 
      {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
      StringBuilder sb_szBreakName1;
      if ( szBreakName1 == null )
         sb_szBreakName1 = new StringBuilder( 32 );
      else
         sb_szBreakName1 = new StringBuilder( szBreakName1 );
             GetVariableFromAttribute( sb_szBreakName1, mi_lTempInteger_8, 'S', 51, mSPLDefPDF, "LLD_Block", "UsageColumn1BreakName", "", 0 );
      lTempInteger_8 = mi_lTempInteger_8.intValue( );
      szBreakName1 = sb_szBreakName1.toString( );}
      //:szBreakName2 = mSPLDefPDF.LLD_Block.UsageColumn2BreakName 
      {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
      StringBuilder sb_szBreakName2;
      if ( szBreakName2 == null )
         sb_szBreakName2 = new StringBuilder( 32 );
      else
         sb_szBreakName2 = new StringBuilder( szBreakName2 );
             GetVariableFromAttribute( sb_szBreakName2, mi_lTempInteger_9, 'S', 51, mSPLDefPDF, "LLD_Block", "UsageColumn2BreakName", "", 0 );
      lTempInteger_9 = mi_lTempInteger_9.intValue( );
      szBreakName2 = sb_szBreakName2.toString( );}
      //:CurrentColumnNumber = 1
      CurrentColumnNumber = 1;
      //:FOR EACH mSPLDef.SPLD_MarketingUsageOrdering WITHIN mSPLDef.SPLD_MarketingSection 
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "SPLD_MarketingSection" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSPLDef.SPLD_MarketingUsage.UsageType = "C"
         if ( CompareAttributeToString( mSPLDef, "SPLD_MarketingUsage", "UsageType", "C" ) == 0 )
         { 
            //:IF mSPLDef.SPLD_MarketingUsage.Name = szBreakName1
            if ( CompareAttributeToString( mSPLDef, "SPLD_MarketingUsage", "Name", szBreakName1 ) == 0 )
            { 
               //:CurrentColumnNumber = 2
               CurrentColumnNumber = 2;
               //:ELSE
            } 
            else
            { 
               //:IF mSPLDef.SPLD_MarketingUsage.Name = szBreakName2
               if ( CompareAttributeToString( mSPLDef, "SPLD_MarketingUsage", "Name", szBreakName2 ) == 0 )
               { 
                  //:CurrentColumnNumber = 3
                  CurrentColumnNumber = 3;
               } 

               //:END
            } 

            //:END
            //:IF CurrentColumnNumber = 1
            if ( CurrentColumnNumber == 1 )
            { 
               //:CREATE ENTITY mSPLDef.DisplayUsageColumn1 
               RESULT = CreateEntity( mSPLDef, "DisplayUsageColumn1", zPOS_AFTER );
               //:mSPLDef.DisplayUsageColumn1.Name = mSPLDef.SPLD_MarketingUsage.wNameWithFootnoteDesignator 
               SetAttributeFromAttribute( mSPLDef, "DisplayUsageColumn1", "Name", mSPLDef, "SPLD_MarketingUsage", "wNameWithFootnoteDesignator" );
               //:ELSE
            } 
            else
            { 
               //:IF CurrentColumnNumber = 2
               if ( CurrentColumnNumber == 2 )
               { 
                  //:CREATE ENTITY mSPLDef.DisplayUsageColumn2 
                  RESULT = CreateEntity( mSPLDef, "DisplayUsageColumn2", zPOS_AFTER );
                  //:mSPLDef.DisplayUsageColumn2.Name = mSPLDef.SPLD_MarketingUsage.wNameWithFootnoteDesignator 
                  SetAttributeFromAttribute( mSPLDef, "DisplayUsageColumn2", "Name", mSPLDef, "SPLD_MarketingUsage", "wNameWithFootnoteDesignator" );
                  //:ELSE
               } 
               else
               { 
                  //:CREATE ENTITY mSPLDef.DisplayUsageColumn3  
                  RESULT = CreateEntity( mSPLDef, "DisplayUsageColumn3", zPOS_AFTER );
                  //:mSPLDef.DisplayUsageColumn3.Name = mSPLDef.SPLD_MarketingUsage.wNameWithFootnoteDesignator 
                  SetAttributeFromAttribute( mSPLDef, "DisplayUsageColumn3", "Name", mSPLDef, "SPLD_MarketingUsage", "wNameWithFootnoteDesignator" );
               } 

               //:END
            } 

            //:END
         } 

         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "SPLD_MarketingSection" );
         //:END
      } 

      //:END
   } 

   //:END

   //:// Add any Footnote entries to the last Column.
   //:FOR EACH mSPLDef.M_UsageFootnote 
   RESULT = SetCursorFirstEntity( mSPLDef, "M_UsageFootnote", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szFootnoteNumber = mSPLDef.M_UsageFootnote.wFootNoteRelativeNumber 
      {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
      StringBuilder sb_szFootnoteNumber;
      if ( szFootnoteNumber == null )
         sb_szFootnoteNumber = new StringBuilder( 32 );
      else
         sb_szFootnoteNumber = new StringBuilder( szFootnoteNumber );
             GetVariableFromAttribute( sb_szFootnoteNumber, mi_lTempInteger_10, 'S', 4, mSPLDef, "M_UsageFootnote", "wFootNoteRelativeNumber", "", 0 );
      lTempInteger_10 = mi_lTempInteger_10.intValue( );
      szFootnoteNumber = sb_szFootnoteNumber.toString( );}
      //:szFootnoteText = "<sup> " + szFootnoteNumber + "</sup>" + mSPLDef.M_UsageFootnote.Text 
       {StringBuilder sb_szFootnoteText;
      if ( szFootnoteText == null )
         sb_szFootnoteText = new StringBuilder( 32 );
      else
         sb_szFootnoteText = new StringBuilder( szFootnoteText );
            ZeidonStringCopy( sb_szFootnoteText, 1, 0, "<sup> ", 1, 0, 101 );
      szFootnoteText = sb_szFootnoteText.toString( );}
       {StringBuilder sb_szFootnoteText;
      if ( szFootnoteText == null )
         sb_szFootnoteText = new StringBuilder( 32 );
      else
         sb_szFootnoteText = new StringBuilder( szFootnoteText );
            ZeidonStringConcat( sb_szFootnoteText, 1, 0, szFootnoteNumber, 1, 0, 101 );
      szFootnoteText = sb_szFootnoteText.toString( );}
       {StringBuilder sb_szFootnoteText;
      if ( szFootnoteText == null )
         sb_szFootnoteText = new StringBuilder( 32 );
      else
         sb_szFootnoteText = new StringBuilder( szFootnoteText );
            ZeidonStringConcat( sb_szFootnoteText, 1, 0, "</sup>", 1, 0, 101 );
      szFootnoteText = sb_szFootnoteText.toString( );}
      {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
      StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
             GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_11, 'S', 255, mSPLDef, "M_UsageFootnote", "Text", "", 0 );
      lTempInteger_11 = mi_lTempInteger_11.intValue( );
      szTempString_1 = sb_szTempString_1.toString( );}
       {StringBuilder sb_szFootnoteText;
      if ( szFootnoteText == null )
         sb_szFootnoteText = new StringBuilder( 32 );
      else
         sb_szFootnoteText = new StringBuilder( szFootnoteText );
            ZeidonStringConcat( sb_szFootnoteText, 1, 0, szTempString_1, 1, 0, 101 );
      szFootnoteText = sb_szFootnoteText.toString( );}
      //:IF CurrentColumnNumber = 1
      if ( CurrentColumnNumber == 1 )
      { 
         //:CREATE ENTITY mSPLDef.DisplayUsageColumn1 
         RESULT = CreateEntity( mSPLDef, "DisplayUsageColumn1", zPOS_AFTER );
         //:mSPLDef.DisplayUsageColumn1.Name = szFootnoteText 
         SetAttributeFromString( mSPLDef, "DisplayUsageColumn1", "Name", szFootnoteText );
         //:ELSE
      } 
      else
      { 
         //:IF CurrentColumnNumber = 2
         if ( CurrentColumnNumber == 2 )
         { 
            //:CREATE ENTITY mSPLDef.DisplayUsageColumn2 
            RESULT = CreateEntity( mSPLDef, "DisplayUsageColumn2", zPOS_AFTER );
            //:mSPLDef.DisplayUsageColumn2.Name = szFootnoteText
            SetAttributeFromString( mSPLDef, "DisplayUsageColumn2", "Name", szFootnoteText );
            //:ELSE
         } 
         else
         { 
            //:CREATE ENTITY mSPLDef.DisplayUsageColumn3  
            RESULT = CreateEntity( mSPLDef, "DisplayUsageColumn3", zPOS_AFTER );
            //:mSPLDef.DisplayUsageColumn3.Name = szFootnoteText
            SetAttributeFromString( mSPLDef, "DisplayUsageColumn3", "Name", szFootnoteText );
         } 

         //:END 
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "M_UsageFootnote", "" );
      //:END
   } 

   //:END

   //:// Build the two or three Blocks listing the entries in each of the three work subobjects.
   //:// If there is a Column definition entry, we will get the Top, Width and LeftMargin values from there.
   //:// If not, the width of each Block (column) will be 1/2 or 1/3 of the width of the source Block, minus the left margin,
   //:// which is from the main block.
   //:// We will create a temporary Block, initialized from the source Block, with the modified values for
   //:// each of the three columns.

   //:ACTIVATE mSPLDef2 EMPTY 
   RESULT = ActivateEmptyObjectInstance( mSPLDef2, "mSPLDef", mSPLDef, zSINGLE );
   //:NAME VIEW mSPLDef2 "mSPLDef2"
   SetNameForView( mSPLDef2, "mSPLDef2", null, zLEVEL_TASK );
   //:CREATE ENTITY mSPLDef2.SubregPhysicalLabelDef 
   RESULT = CreateEntity( mSPLDef2, "SubregPhysicalLabelDef", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef2.SPLD_LLD 
   RESULT = CreateEntity( mSPLDef2, "SPLD_LLD", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef2.LLD_Page 
   RESULT = CreateEntity( mSPLDef2, "LLD_Page", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef2.LLD_Panel   
   RESULT = CreateEntity( mSPLDef2, "LLD_Panel", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef2.LLD_Block 
   RESULT = CreateEntity( mSPLDef2, "LLD_Block", zPOS_AFTER );
   //:SetMatchingAttributesByName( mSPLDef2, "LLD_Block", mSPLDefPDF, "LLD_Block", zSET_ALL ) 
   SetMatchingAttributesByName( mSPLDef2, "LLD_Block", mSPLDefPDF, "LLD_Block", zSET_ALL );

   //:ContainingBlockWidth = mSPLDefPDF.LLD_Block.Width
   {MutableDouble md_ContainingBlockWidth = new MutableDouble( ContainingBlockWidth );
       GetDecimalFromAttribute( md_ContainingBlockWidth, mSPLDefPDF, "LLD_Block", "Width" );
   ContainingBlockWidth = md_ContainingBlockWidth.doubleValue( );}
   //:ColumnWidth = ContainingBlockWidth / NumberColumns
   ColumnWidth = ContainingBlockWidth / NumberColumns;
   //:mSPLDef2.LLD_Block.Width = ColumnWidth
   SetAttributeFromDecimal( mSPLDef2, "LLD_Block", "Width", ColumnWidth );
   //:mSPLDef2.LLD_Block.Left  = 0
   SetAttributeFromInteger( mSPLDef2, "LLD_Block", "Left", 0 );

   //:// The top will be whatever is in the Block, plus any top margin for the Column List LLD_SpecialSectionAttribute.
   //: 
   //:SET CURSOR FIRST mSPLDefPDF.LLD_SpecialSectionAttribute WHERE mSPLDefPDF.LLD_SpecialSectionAttribute.Name = "Column List"
   RESULT = SetCursorFirstEntityByString( mSPLDefPDF, "LLD_SpecialSectionAttribute", "Name", "Column List", "" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:mSPLDef2.LLD_Block.Top = mSPLDefPDF.LLD_SpecialSectionAttrBlock.MarginTop 
      SetAttributeFromAttribute( mSPLDef2, "LLD_Block", "Top", mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "MarginTop" );
      //:szColumnTop = mSPLDef2.LLD_Block.Top
      {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
      StringBuilder sb_szColumnTop;
      if ( szColumnTop == null )
         sb_szColumnTop = new StringBuilder( 32 );
      else
         sb_szColumnTop = new StringBuilder( szColumnTop );
             GetVariableFromAttribute( sb_szColumnTop, mi_lTempInteger_12, 'S', 11, mSPLDef2, "LLD_Block", "Top", "", 0 );
      lTempInteger_12 = mi_lTempInteger_12.intValue( );
      szColumnTop = sb_szColumnTop.toString( );}
      //:// The MarginTop of LLD_SpecialSectionAttrBlock is used only for specifying the BlockContainer holding the individual entries
      //:// within a column, but not for each entry within a column. Thus we need set two different values, the Original TopMargin
      //:// value for the BlockContainer and null for each row. Because we're modifying the TopMargin value in the object, we need
      //:// to reset it in the end.
      //:// We will create mSPLDef2 for generating the BlockContainer for the column and use mSPLDefPDF for each row.
      //:OriginalTopMargin = mSPLDefPDF.LLD_SpecialSectionAttrBlock.MarginTop
      {MutableDouble md_OriginalTopMargin = new MutableDouble( OriginalTopMargin );
             GetDecimalFromAttribute( md_OriginalTopMargin, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "MarginTop" );
      OriginalTopMargin = md_OriginalTopMargin.doubleValue( );}
      //:szMarginLeft       = mSPLDefPDF.LLD_SpecialSectionAttrBlock.MarginLeft 
      {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
      StringBuilder sb_szMarginLeft;
      if ( szMarginLeft == null )
         sb_szMarginLeft = new StringBuilder( 32 );
      else
         sb_szMarginLeft = new StringBuilder( szMarginLeft );
             GetVariableFromAttribute( sb_szMarginLeft, mi_lTempInteger_13, 'S', 11, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "MarginLeft", "", 0 );
      lTempInteger_13 = mi_lTempInteger_13.intValue( );
      szMarginLeft = sb_szMarginLeft.toString( );}
      //:szMarginRight      = mSPLDefPDF.LLD_SpecialSectionAttrBlock.MarginRight
      {MutableInt mi_lTempInteger_14 = new MutableInt( lTempInteger_14 );
      StringBuilder sb_szMarginRight;
      if ( szMarginRight == null )
         sb_szMarginRight = new StringBuilder( 32 );
      else
         sb_szMarginRight = new StringBuilder( szMarginRight );
             GetVariableFromAttribute( sb_szMarginRight, mi_lTempInteger_14, 'S', 11, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "MarginRight", "", 0 );
      lTempInteger_14 = mi_lTempInteger_14.intValue( );
      szMarginRight = sb_szMarginRight.toString( );}

      //:// Create a Block LLD_SpecialSectionAttribute entry containing the characteristics of the Column List entry, except for dropping
      //:// the top margin.
      //:CREATE ENTITY mSPLDef2.LLD_SpecialSectionAttribute 
      RESULT = CreateEntity( mSPLDef2, "LLD_SpecialSectionAttribute", zPOS_AFTER );
      //:mSPLDef2.LLD_SpecialSectionAttribute.Name = "Block" 
      SetAttributeFromString( mSPLDef2, "LLD_SpecialSectionAttribute", "Name", "Block" );
      //:CREATE ENTITY mSPLDef2.LLD_SpecialSectionAttrBlock 
      RESULT = CreateEntity( mSPLDef2, "LLD_SpecialSectionAttrBlock", zPOS_AFTER );
      //:SetMatchingAttributesByName( mSPLDef2, "LLD_SpecialSectionAttrBlock", mSPLDefPDF, "LLD_SpecialSectionAttrBlock", zSET_ALL )
      SetMatchingAttributesByName( mSPLDef2, "LLD_SpecialSectionAttrBlock", mSPLDefPDF, "LLD_SpecialSectionAttrBlock", zSET_ALL );
      //:mSPLDefPDF.LLD_SpecialSectionAttrBlock.MarginTop = ""     // Now set MarginTop for each row.
      SetAttributeFromString( mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "MarginTop", "" );
      //:ELSE
   } 
   else
   { 

      //:mSPLDef2.LLD_Block.Top = mSPLDef2.LLD_Block.Top
      SetAttributeFromAttribute( mSPLDef2, "LLD_Block", "Top", mSPLDef2, "LLD_Block", "Top" );
      //:szMarginLeft  = ".01"
       {StringBuilder sb_szMarginLeft;
      if ( szMarginLeft == null )
         sb_szMarginLeft = new StringBuilder( 32 );
      else
         sb_szMarginLeft = new StringBuilder( szMarginLeft );
            ZeidonStringCopy( sb_szMarginLeft, 1, 0, ".01", 1, 0, 11 );
      szMarginLeft = sb_szMarginLeft.toString( );}
      //:szMarginRight = ".01"
       {StringBuilder sb_szMarginRight;
      if ( szMarginRight == null )
         sb_szMarginRight = new StringBuilder( 32 );
      else
         sb_szMarginRight = new StringBuilder( szMarginRight );
            ZeidonStringCopy( sb_szMarginRight, 1, 0, ".01", 1, 0, 11 );
      szMarginRight = sb_szMarginRight.toString( );}
   } 

   //:END
   //:ColumnHeight = mSPLDefPDF.LLD_Block.Height - mSPLDef2.LLD_Block.Top
   {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
       GetDecimalFromAttribute( md_dTempDecimal_0, mSPLDefPDF, "LLD_Block", "Height" );
   dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
   {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
       GetDecimalFromAttribute( md_dTempDecimal_1, mSPLDef2, "LLD_Block", "Top" );
   dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
   ColumnHeight = dTempDecimal_0 - dTempDecimal_1;
   //:mSPLDef2.LLD_Block.Height = ColumnHeight
   SetAttributeFromDecimal( mSPLDef2, "LLD_Block", "Height", ColumnHeight );

   //:szSectionName = mSPLDef.DisplaySection.XML_SectionName 
   {MutableInt mi_lTempInteger_15 = new MutableInt( lTempInteger_15 );
   StringBuilder sb_szSectionName;
   if ( szSectionName == null )
      sb_szSectionName = new StringBuilder( 32 );
   else
      sb_szSectionName = new StringBuilder( szSectionName );
       GetVariableFromAttribute( sb_szSectionName, mi_lTempInteger_15, 'S', 51, mSPLDef, "DisplaySection", "XML_SectionName", "", 0 );
   lTempInteger_15 = mi_lTempInteger_15.intValue( );
   szSectionName = sb_szSectionName.toString( );}

   //:// Generate column 1 Block.
   //:FormatBlockContainer( mSPLDef2, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, szColumnTop )
   omSPLDef_FormatBlockContainer( mSPLDef2, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, szColumnTop );

   //:szWriteBuffer = szLeadingBlanks + "   <xsl:for-each select=^SubregPhysicalLabelDef/" + szSectionName + "/DisplayUsageColumn1^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <xsl:for-each select=^SubregPhysicalLabelDef/", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSectionName, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "/DisplayUsageColumn1^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "      <fo:block "
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      <fo:block ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:AddFormatToSpecialText( mSPLDefPDF, "Column List", szWriteBuffer )
   {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
       omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Column List", sb_szWriteBuffer );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "         <xsl:apply-templates select=^Name^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "         <xsl:apply-templates select=^Name^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = szLeadingBlanks + "      </fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      </fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "   </xsl:for-each>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   </xsl:for-each>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "</fo:block-container>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block-container>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Generate column 2 Block.
   //:mSPLDef2.LLD_Block.Left = mSPLDef2.LLD_Block.Left + ColumnWidth
   {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
       GetDecimalFromAttribute( md_dTempDecimal_2, mSPLDef2, "LLD_Block", "Left" );
   dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
   dTempDecimal_3 = dTempDecimal_2 + ColumnWidth;
   SetAttributeFromDecimal( mSPLDef2, "LLD_Block", "Left", dTempDecimal_3 );
   //:FormatBlockContainer( mSPLDef2, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, szColumnTop )
   omSPLDef_FormatBlockContainer( mSPLDef2, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, szColumnTop );

   //:szWriteBuffer = szLeadingBlanks + "   <xsl:for-each select=^SubregPhysicalLabelDef/" + szSectionName + "/DisplayUsageColumn2^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <xsl:for-each select=^SubregPhysicalLabelDef/", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSectionName, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "/DisplayUsageColumn2^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "      <fo:block "
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      <fo:block ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:AddFormatToSpecialText( mSPLDefPDF, "Column List", szWriteBuffer )
   {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
       omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Column List", sb_szWriteBuffer );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "         <xsl:apply-templates select=^Name^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "         <xsl:apply-templates select=^Name^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:szWriteBuffer = szLeadingBlanks + "      </fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      </fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "   </xsl:for-each>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   </xsl:for-each>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "</fo:block-container>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block-container>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Generate column 3 Block.
   //:IF NumberColumns = 3
   if ( NumberColumns == 3 )
   { 
      //:mSPLDef2.LLD_Block.Left = mSPLDef2.LLD_Block.Left + ColumnWidth
      {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
             GetDecimalFromAttribute( md_dTempDecimal_4, mSPLDef2, "LLD_Block", "Left" );
      dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
      dTempDecimal_5 = dTempDecimal_4 + ColumnWidth;
      SetAttributeFromDecimal( mSPLDef2, "LLD_Block", "Left", dTempDecimal_5 );
      //:FormatBlockContainer( mSPLDef2, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, szColumnTop )
      omSPLDef_FormatBlockContainer( mSPLDef2, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, szColumnTop );

      //:szWriteBuffer = szLeadingBlanks + "   <xsl:for-each select=^SubregPhysicalLabelDef/" + szSectionName + "/DisplayUsageColumn3^>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <xsl:for-each select=^SubregPhysicalLabelDef/", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSectionName, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "/DisplayUsageColumn3^>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:szWriteBuffer = szLeadingBlanks + "      <fo:block "
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      <fo:block ", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:AddFormatToSpecialText( mSPLDefPDF, "Column List", szWriteBuffer )
      {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
             omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Column List", sb_szWriteBuffer );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:szWriteBuffer = szLeadingBlanks + "         <xsl:apply-templates select=^Name^/>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "         <xsl:apply-templates select=^Name^/>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }
      //:szWriteBuffer = szLeadingBlanks + "      </fo:block>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      </fo:block>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:szWriteBuffer = szLeadingBlanks + "   </xsl:for-each>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   </xsl:for-each>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:szWriteBuffer = szLeadingBlanks + "</fo:block-container>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block-container>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }
   } 

   //:END

   //://DELETE ENTITY mSPLDef2.LLD_Block NONE
   //:DropObjectInstance( mSPLDef2 )
   DropObjectInstance( mSPLDef2 );

   //:// Build the work object containing each Claim under the appropriate Classification.
   //:// Type: C - Claim;  S - Surface;  T - Application Type;  U - Area of Use.
   //:// ClaimsClassification: Bacteria; Protozoa; Viruses; Fungi.
   //:// First delete any existing entries.
   //:FOR EACH mSPLDef.SPLDI_ClaimsClassification 
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_ClaimsClassification", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mSPLDef.SPLDI_ClaimsClassification NONE 
      RESULT = DeleteEntity( mSPLDef, "SPLDI_ClaimsClassification", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "SPLDI_ClaimsClassification", "" );
   } 

   //:END
   //:FOR EACH mSPLDef.SPLD_MarketingUsageOrdering WITHIN mSPLDef.SPLD_MarketingSection 
   //:          WHERE mSPLDef.SPLD_MarketingUsage.UsageType = "C" // just looking for Type=Claim
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "SPLD_MarketingSection" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( mSPLDef, "SPLD_MarketingUsage", "UsageType", "C" ) == 0 )
      { 
         //:SET CURSOR FIRST mSPLDef.SPLDI_ClaimsClassification
         //:        WHERE mSPLDef.SPLDI_ClaimsClassification.ClaimsClassification = mSPLDef.SPLD_MarketingUsage.ClaimsClassification
         {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetStringFromAttribute( sb_szTempString_0, mSPLDef, "SPLD_MarketingUsage", "ClaimsClassification" );
         szTempString_0 = sb_szTempString_0.toString( );}
         RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLDI_ClaimsClassification", "ClaimsClassification", szTempString_0, "" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:// Did not find the entity of the proper ClaimsClassification, so create one.
            //:CREATE ENTITY mSPLDef.SPLDI_ClaimsClassification
            RESULT = CreateEntity( mSPLDef, "SPLDI_ClaimsClassification", zPOS_AFTER );
            //:mSPLDef.SPLDI_ClaimsClassification.ClaimsClassification = mSPLDef.SPLD_MarketingUsage.ClaimsClassification
            SetAttributeFromAttribute( mSPLDef, "SPLDI_ClaimsClassification", "ClaimsClassification", mSPLDef, "SPLD_MarketingUsage", "ClaimsClassification" );
            //:INCLUDE mSPLDef.SPLDI_ClaimsUsage FROM mSPLDef.SPLD_MarketingUsage
            RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_ClaimsUsage", mSPLDef, "SPLD_MarketingUsage", zPOS_AFTER );
            //:IF RESULT < 0
            if ( RESULT < 0 )
            { 
               //:TraceLineS( "GeneratePDF_ClmList Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ======== CAN'T HAPPEN ===========================" )
               TraceLineS( "GeneratePDF_ClmList Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ======== CAN'T HAPPEN ===========================" );
               //:DisplayEntityInstance( mSPLDef, "SPLDI_ClaimsUsage" )
               DisplayEntityInstance( mSPLDef, "SPLDI_ClaimsUsage" );
               //:DisplayEntityInstance( mSPLDef, "SPLD_MarketingUsage" )
               DisplayEntityInstance( mSPLDef, "SPLD_MarketingUsage" );
               //:IssueError( mSPLDef, 0, 0, "Include Error" )
               IssueError( mSPLDef, 0, 0, "Include Error" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:// Check to see if the SPLD_MarketingUsage has already been included under the current ClaimsClassification.
            //:SET CURSOR FIRST mSPLDef.SPLDI_ClaimsUsage WHERE mSPLDef.SPLDI_ClaimsUsage.ID = mSPLDef.SPLD_MarketingUsage.ID
            {MutableInt mi_lTempInteger_16 = new MutableInt( lTempInteger_16 );
                         GetIntegerFromAttribute( mi_lTempInteger_16, mSPLDef, "SPLD_MarketingUsage", "ID" );
            lTempInteger_16 = mi_lTempInteger_16.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLDI_ClaimsUsage", "ID", lTempInteger_16, "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:// Not already included so set cursor to the last included to include the entity at the end.
               //:SET CURSOR LAST mSPLDef.SPLDI_ClaimsUsage
               RESULT = SetCursorLastEntity( mSPLDef, "SPLDI_ClaimsUsage", "" );
               //:INCLUDE mSPLDef.SPLDI_ClaimsUsage FROM mSPLDef.SPLD_MarketingUsage
               RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_ClaimsUsage", mSPLDef, "SPLD_MarketingUsage", zPOS_AFTER );
               //:IF RESULT < 0
               if ( RESULT < 0 )
               { 
                  //:TraceLineS( "GeneratePDF_ClmList Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ===========CAN'T HAPPEN EITHER ========================" )
                  TraceLineS( "GeneratePDF_ClmList Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ===========CAN'T HAPPEN EITHER ========================" );
                  //:DisplayEntityInstance( mSPLDef, "SPLDI_ClaimsUsage" )
                  DisplayEntityInstance( mSPLDef, "SPLDI_ClaimsUsage" );
                  //:DisplayEntityInstance( mSPLDef, "SPLD_MarketingUsage" )
                  DisplayEntityInstance( mSPLDef, "SPLD_MarketingUsage" );
                  //:IssueError( mSPLDef, 0, 0, "Include Error" )
                  IssueError( mSPLDef, 0, 0, "Include Error" );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:TraceLineS( "GeneratePDF_ClmList Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ====== ALREADY INCLUDED ... COULD HAPPEN =============================" )
               TraceLineS( "GeneratePDF_ClmList Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ====== ALREADY INCLUDED ... COULD HAPPEN =============================" );
               //:// DisplayEntityInstance( mSPLDef, "SPLDI_ClaimsUsage" )
               //:DisplayEntityInstance( mSPLDef, "SPLD_MarketingUsage" )
               DisplayEntityInstance( mSPLDef, "SPLD_MarketingUsage" );
            } 

            //:END
         } 

      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "SPLD_MarketingSection" );
      //:END
   } 

   //:END

   //:// Reset LLD_SpecialSectionAttrBlock.MarginTop back to its original value.
   //:mSPLDefPDF.LLD_SpecialSectionAttrBlock.MarginTop = OriginalTopMargin
   SetAttributeFromDecimal( mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "MarginTop", OriginalTopMargin );
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:GeneratePDF_Ingred( VIEW mSPLDef      BASED ON LOD mSPLDef,
//:                    VIEW mSPLDefPDF BASED ON LOD mSPLDef,
//:                    INTEGER lFile,
//:                    STRING ( 50 )    szPassedBlanks,
//:                    STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 50 )    szLeadingBlanks
public int 
omSPLDef_GeneratePDF_Ingred( View     mSPLDef,
                             View     mSPLDefPDF,
                             int      lFile,
                             String   szPassedBlanks,
                             String   szWriteBuffer )
{
   String   szLeadingBlanks = null;
   //:STRING ( 512 )   szIngredientsText
   String   szIngredientsText = null;
   //:STRING ( 256 )   szPeriodFiller
   String   szPeriodFiller = null;
   //:STRING ( 16 )    szPercent
   String   szPercent = null;
   int      RESULT = 0;

   //:// Generate XSL Statements for a "Active Ingredients" Section.

   //:szLeadingBlanks = szPassedBlanks + "   "
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringCopy( sb_szLeadingBlanks, 1, 0, szPassedBlanks, 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringConcat( sb_szLeadingBlanks, 1, 0, "   ", 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}
   //:SET CURSOR FIRST mSPLDef.SPLD_IngredientsSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_IngredientsSection", "" );
   //:szWriteBuffer = szLeadingBlanks + "<!-- Incredients Section using Leader -->"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<!-- Incredients Section using Leader -->", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //://FormatBlockContainer( mSPLDef, mSPLDef, lFile, szLeadingBlanks, szWriteBuffer, "" )

   //:// Title
   //:szWriteBuffer = szLeadingBlanks + "<fo:block "
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<fo:block ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:AddFormatToSpecialText( mSPLDefPDF, "Title", szWriteBuffer )
   {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
       omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Title", sb_szWriteBuffer );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "   <xsl:apply-templates select=^SubregPhysicalLabelDef/SPLD_IngredientsSection/ActiveTitle^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <xsl:apply-templates select=^SubregPhysicalLabelDef/SPLD_IngredientsSection/ActiveTitle^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "</fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Ingredients Percent
   //:// Use XSL Leader for formatting.
   //:szWriteBuffer = szLeadingBlanks + "<xsl:for-each select=^SubregPhysicalLabelDef/SPLD_IngredientsSection/SPLD_IngredientsStatement^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<xsl:for-each select=^SubregPhysicalLabelDef/SPLD_IngredientsSection/SPLD_IngredientsStatement^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "   <fo:block text-align-last=^justify^ "
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <fo:block text-align-last=^justify^ ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:AddFormatToSpecialText( mSPLDefPDF, "Ingredients Items", szWriteBuffer )
   {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
       omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Ingredients Items", sb_szWriteBuffer );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "      <xsl:apply-templates select=^ChemicalName^/>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      <xsl:apply-templates select=^ChemicalName^/>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "      <fo:leader leader-pattern=^dots^ />"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      <fo:leader leader-pattern=^dots^ />", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "      <xsl:apply-templates select=^Percent^/>%"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "      <xsl:apply-templates select=^Percent^/>%", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "   </fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   </fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "</xsl:for-each>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</xsl:for-each>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Inert Percent
   //:IF mSPLDef.SPLD_IngredientsSection.GeneralInactivePercent > 0 
   if ( CompareAttributeToInteger( mSPLDef, "SPLD_IngredientsSection", "GeneralInactivePercent", 0 ) > 0 )
   { 
      //:szWriteBuffer = szLeadingBlanks + "<fo:block text-align-last=^justify^ "
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<fo:block text-align-last=^justify^ ", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:AddFormatToSpecialText( mSPLDefPDF, "Ingredients Inert", szWriteBuffer )
      {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
             omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Ingredients Inert", sb_szWriteBuffer );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:szWriteBuffer = szLeadingBlanks + "   <xsl:apply-templates select=^SubregPhysicalLabelDef/SPLD_IngredientsSection/InertTitle^/>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <xsl:apply-templates select=^SubregPhysicalLabelDef/SPLD_IngredientsSection/InertTitle^/>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:szWriteBuffer = szLeadingBlanks + "   <fo:leader leader-pattern=^dots^ />"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <fo:leader leader-pattern=^dots^ />", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:szWriteBuffer = szLeadingBlanks + "   <xsl:apply-templates select=^SubregPhysicalLabelDef/SPLD_IngredientsSection/GeneralInactivePercent^/>%"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <xsl:apply-templates select=^SubregPhysicalLabelDef/SPLD_IngredientsSection/GeneralInactivePercent^/>%", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }

      //:szWriteBuffer = szLeadingBlanks + "</fo:block>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
      try
      {
          {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }
   } 

   //:END

   //:// Total
   //:szWriteBuffer = szLeadingBlanks + "<fo:block text-align-last=^justify^ "
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<fo:block text-align-last=^justify^ ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:AddFormatToSpecialText( mSPLDefPDF, "Ingredients Total", szWriteBuffer )
   {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
       omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Ingredients Total", sb_szWriteBuffer );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "   TOTAL"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   TOTAL", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "   <fo:leader leader-pattern=^dots^ />100%"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   <fo:leader leader-pattern=^dots^ />100%", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "</fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   return( 0 );
//    
//    // Close opening Block.
//    //szWriteBuffer = szLeadingBlanks + "</fo:block-container>"
//    //WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
// END
} 


//:TRANSFORMATION OPERATION
//:GeneratePDF_Content( VIEW mSPLDef      BASED ON LOD mSPLDef,
//:                     VIEW mSPLDefPDF BASED ON LOD mSPLDef,
//:                     INTEGER lFile,
//:                     STRING ( 50 )    szPassedBlanks,
//:                     STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 50 )    szLeadingBlanks
public int 
omSPLDef_GeneratePDF_Content( View     mSPLDef,
                              View     mSPLDefPDF,
                              int      lFile,
                              String   szPassedBlanks,
                              String   szWriteBuffer )
{
   String   szLeadingBlanks = null;
   //:STRING ( 512 )   szNetContentsText
   String   szNetContentsText = null;

   //:// Generate PDF for "NetContents".

   //:szLeadingBlanks = szPassedBlanks + "   "
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringCopy( sb_szLeadingBlanks, 1, 0, szPassedBlanks, 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringConcat( sb_szLeadingBlanks, 1, 0, "   ", 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}

   //://szNetContentsText = mSPLDef.SPLD_TemplateSection.TitleOverride
   //:IF szNetContentsText = ""
   if ( ZeidonStringCompare( szNetContentsText, 1, 0, "", 1, 0, 513 ) == 0 )
   { 
      //:szNetContentsText = "Net Contents: One Gallon (3.784 litres)"
       {StringBuilder sb_szNetContentsText;
      if ( szNetContentsText == null )
         sb_szNetContentsText = new StringBuilder( 32 );
      else
         sb_szNetContentsText = new StringBuilder( szNetContentsText );
            ZeidonStringCopy( sb_szNetContentsText, 1, 0, "Net Contents: One Gallon (3.784 litres)", 1, 0, 513 );
      szNetContentsText = sb_szNetContentsText.toString( );}
   } 

   //:END

   //:szWriteBuffer = szLeadingBlanks + "<fo:block "
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<fo:block ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:AddFormatToSpecialText( mSPLDefPDF, "Net Contents", szWriteBuffer )
   {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
       omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Net Contents", sb_szWriteBuffer );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "   " + szNetContentsText
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szNetContentsText, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "</fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GeneratePDF_Hazards( VIEW mSPLDef      BASED ON LOD mSPLDef,
//:                     VIEW mSPLDefPDF BASED ON LOD mSPLDef,
//:                     INTEGER lFile,
//:                     STRING ( 50 )    szPassedBlanks,
//:                     STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 50 )   szLeadingBlanks
public int 
omSPLDef_GeneratePDF_Hazards( View     mSPLDef,
                              View     mSPLDefPDF,
                              int      lFile,
                              String   szPassedBlanks,
                              String   szWriteBuffer )
{
   String   szLeadingBlanks = null;
   //:STRING ( 90 )   szChildHazardWarning
   String   szChildHazardWarning = null;
   //:STRING ( 90 )   szEPA_SignalWord
   String   szEPA_SignalWord = null;
   //:STRING ( 1000 ) szStatementText
   String   szStatementText = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   //:// Generate PDF for each Hazards key word.

   //:szLeadingBlanks = szPassedBlanks + "   "
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringCopy( sb_szLeadingBlanks, 1, 0, szPassedBlanks, 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringConcat( sb_szLeadingBlanks, 1, 0, "   ", 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}
   //:SET CURSOR FIRST mSPLDef.SPLD_HumanHazardSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_HumanHazardSection", "" );
   //:GetStringFromAttributeByContext( szChildHazardWarning, mSPLDef, "SPLD_HumanHazardSection", "EPA_ChildHazardWarning", "", 90 )
   {StringBuilder sb_szChildHazardWarning;
   if ( szChildHazardWarning == null )
      sb_szChildHazardWarning = new StringBuilder( 32 );
   else
      sb_szChildHazardWarning = new StringBuilder( szChildHazardWarning );
       GetStringFromAttributeByContext( sb_szChildHazardWarning, mSPLDef, "SPLD_HumanHazardSection", "EPA_ChildHazardWarning", "", 90 );
   szChildHazardWarning = sb_szChildHazardWarning.toString( );}
   //:szEPA_SignalWord     = mSPLDef.SPLD_HumanHazardSection.EPA_SignalWord 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEPA_SignalWord;
   if ( szEPA_SignalWord == null )
      sb_szEPA_SignalWord = new StringBuilder( 32 );
   else
      sb_szEPA_SignalWord = new StringBuilder( szEPA_SignalWord );
       GetVariableFromAttribute( sb_szEPA_SignalWord, mi_lTempInteger_0, 'S', 91, mSPLDef, "SPLD_HumanHazardSection", "EPA_SignalWord", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEPA_SignalWord = sb_szEPA_SignalWord.toString( );}
   //:szStatementText      = mSPLDef.SPLD_HumanHazardSection.dSelectedStatement 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szStatementText;
   if ( szStatementText == null )
      sb_szStatementText = new StringBuilder( 32 );
   else
      sb_szStatementText = new StringBuilder( szStatementText );
       GetVariableFromAttribute( sb_szStatementText, mi_lTempInteger_1, 'S', 1001, mSPLDef, "SPLD_HumanHazardSection", "dSelectedStatement", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szStatementText = sb_szStatementText.toString( );}

   //:// Child Hazard Warning
   //:szWriteBuffer = szLeadingBlanks + "<fo:block "
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<fo:block ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:AddFormatToSpecialText( mSPLDefPDF, "Hazards Warning", szWriteBuffer )
   {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
       omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Hazards Warning", sb_szWriteBuffer );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "   " + szChildHazardWarning
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szChildHazardWarning, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "</fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// EPA Signal Word
   //:szWriteBuffer = szLeadingBlanks + "<fo:block "
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<fo:block ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:AddFormatToSpecialText( mSPLDefPDF, "Hazards Signal Word", szWriteBuffer )
   {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
       omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Hazards Signal Word", sb_szWriteBuffer );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "   " + szEPA_SignalWord
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szEPA_SignalWord, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "</fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Precautionary Statement
   //:szWriteBuffer = szLeadingBlanks + "<fo:block "
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<fo:block ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:AddFormatToSpecialText( mSPLDefPDF, "Hazards Precautionary", szWriteBuffer )
   {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
       omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "Hazards Precautionary", sb_szWriteBuffer );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "   " + szStatementText
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "</fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:GeneratePDF_General( VIEW mSPLDef      BASED ON LOD mSPLDef,
//:                     VIEW mSPLDefPDF BASED ON LOD mSPLDef,
//:                     INTEGER lFile,
//:                     STRING ( 50 )    szPassedBlanks,
//:                     STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 50 )     szLeadingBlanks
public int 
omSPLDef_GeneratePDF_General( View     mSPLDef,
                              View     mSPLDefPDF,
                              int      lFile,
                              String   szPassedBlanks,
                              String   szWriteBuffer )
{
   String   szLeadingBlanks = null;
   //:STRING ( 32000 )  szStatementText
   String   szStatementText = null;
   //:STRING ( 256 )    szCombinedTitle
   String   szCombinedTitle = null;
   //:STRING ( 32 )     szLineHeight
   String   szLineHeight = null;
   //:STRING ( 10 )     szStatementLeading
   String   szStatementLeading = null;


   //:// Generate PDF for a "General" Section, which can be Precautionary, Environmental/Physical Hazard or First Aid.

   //:szLeadingBlanks = szPassedBlanks + "   "
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringCopy( sb_szLeadingBlanks, 1, 0, szPassedBlanks, 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringConcat( sb_szLeadingBlanks, 1, 0, "   ", 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}
   //:szLineHeight = ""
    {StringBuilder sb_szLineHeight;
   if ( szLineHeight == null )
      sb_szLineHeight = new StringBuilder( 32 );
   else
      sb_szLineHeight = new StringBuilder( szLineHeight );
      ZeidonStringCopy( sb_szLineHeight, 1, 0, "", 1, 0, 33 );
   szLineHeight = sb_szLineHeight.toString( );}
   return( 0 );
//    /*szStatementLeading = mSPLDef.SPLD_TemplateSection.dStatementLeading
//    IF szStatementLeading != ""
//       szLineHeight = "line-height:" + szStatementLeading +";"
//    ELSE
//       szStatementLeading = mSPLDef.SPLD_TemplatePanel.dStatementLeadingDefault
//       IF szStatementLeading != ""
//          szLineHeight = "line-height:" + szStatementLeading +";"
//       END
//    END
//    //SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.ID = mSPLDef.SPLDT_GeneralSection.ID
// // DisplayEntityInstance( mSPLDef, "SPLD_GeneralSection" )
//    GeneratePDF_Title( mSPLDef, lFile, "SPLD_GeneralSection", szCombinedTitle, szWriteBuffer )
//    FOR EACH mSPLDef.SPLD_GeneralStatement
//       szStatementText = szCombinedTitle + mSPLDef.SPLD_GeneralStatement.Text
//       szWriteBuffer = szCombinedTitle + szStatementText
//       IF szLineHeight != ""
//          AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
//       ELSE
//          AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
//       END
//       WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
//       szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
//    END*/
// END
} 


//:TRANSFORMATION OPERATION
//:GeneratePDF_StorDisp( VIEW mSPLDef      BASED ON LOD mSPLDef,
//:                      VIEW mSPLDefPDF BASED ON LOD mSPLDef,
//:                      INTEGER lFile,
//:                      STRING ( 32 )    szSectionType,
//:                      STRING ( 50 )    szPassedBlanks,
//:                      STRING ( 32000 ) szWriteBuffer )
public int 
omSPLDef_GeneratePDF_StorDisp( View     mSPLDef,
                               View     mSPLDefPDF,
                               int      lFile,
                               String   szSectionType,
                               String   szPassedBlanks,
                               String   szWriteBuffer )
{

   return( 0 );
//    // Right now there appears to be no reason why the Storage and Disposal section can't reuse the same code
//    // as the Directions for Use and Marketing sections.
//    
// END
} 


//:TRANSFORMATION OPERATION
//:GeneratePDF_EPA_Reg( VIEW mSPLDef      BASED ON LOD mSPLDef,
//:                     VIEW mSPLDefPDF BASED ON LOD mSPLDef,
//:                     INTEGER lFile,
//:                     STRING ( 50 )    szPassedBlanks,
//:                     STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 50 )    szLeadingBlanks
public int 
omSPLDef_GeneratePDF_EPA_Reg( View     mSPLDef,
                              View     mSPLDefPDF,
                              int      lFile,
                              String   szPassedBlanks,
                              String   szWriteBuffer )
{
   String   szLeadingBlanks = null;
   //:STRING ( 32000 ) szStatementText
   String   szStatementText = null;
   //:STRING ( 20 )    szEPA_RegNbr
   String   szEPA_RegNbr = null;
   //:STRING ( 20 )    szEPA_EstNbr
   String   szEPA_EstNbr = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   //:// Generate PDF for EPA Reg. No. and EPA Est. No..

   //:szLeadingBlanks = szPassedBlanks + "   "
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringCopy( sb_szLeadingBlanks, 1, 0, szPassedBlanks, 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}
    {StringBuilder sb_szLeadingBlanks;
   if ( szLeadingBlanks == null )
      sb_szLeadingBlanks = new StringBuilder( 32 );
   else
      sb_szLeadingBlanks = new StringBuilder( szLeadingBlanks );
      ZeidonStringConcat( sb_szLeadingBlanks, 1, 0, "   ", 1, 0, 51 );
   szLeadingBlanks = sb_szLeadingBlanks.toString( );}
   //:SET CURSOR FIRST mSPLDef.SPLD_HumanHazardSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_HumanHazardSection", "" );
   //:szEPA_RegNbr = mSPLDef.SubregProduct.EPA_RegistrationNumber
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEPA_RegNbr;
   if ( szEPA_RegNbr == null )
      sb_szEPA_RegNbr = new StringBuilder( 32 );
   else
      sb_szEPA_RegNbr = new StringBuilder( szEPA_RegNbr );
       GetVariableFromAttribute( sb_szEPA_RegNbr, mi_lTempInteger_0, 'S', 21, mSPLDef, "SubregProduct", "EPA_RegistrationNumber", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEPA_RegNbr = sb_szEPA_RegNbr.toString( );}
   //:IF szEPA_RegNbr = ""
   if ( ZeidonStringCompare( szEPA_RegNbr, 1, 0, "", 1, 0, 21 ) == 0 )
   { 
      //:szEPA_RegNbr = "*****"
       {StringBuilder sb_szEPA_RegNbr;
      if ( szEPA_RegNbr == null )
         sb_szEPA_RegNbr = new StringBuilder( 32 );
      else
         sb_szEPA_RegNbr = new StringBuilder( szEPA_RegNbr );
            ZeidonStringCopy( sb_szEPA_RegNbr, 1, 0, "*****", 1, 0, 21 );
      szEPA_RegNbr = sb_szEPA_RegNbr.toString( );}
   } 

   //:END
   //:szEPA_EstNbr = mSPLDef.SubregProduct.EPA_EstablishmentNumber
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szEPA_EstNbr;
   if ( szEPA_EstNbr == null )
      sb_szEPA_EstNbr = new StringBuilder( 32 );
   else
      sb_szEPA_EstNbr = new StringBuilder( szEPA_EstNbr );
       GetVariableFromAttribute( sb_szEPA_EstNbr, mi_lTempInteger_1, 'S', 21, mSPLDef, "SubregProduct", "EPA_EstablishmentNumber", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szEPA_EstNbr = sb_szEPA_EstNbr.toString( );}
   //:IF szEPA_EstNbr = ""
   if ( ZeidonStringCompare( szEPA_EstNbr, 1, 0, "", 1, 0, 21 ) == 0 )
   { 
      //:szEPA_EstNbr = "*****"
       {StringBuilder sb_szEPA_EstNbr;
      if ( szEPA_EstNbr == null )
         sb_szEPA_EstNbr = new StringBuilder( 32 );
      else
         sb_szEPA_EstNbr = new StringBuilder( szEPA_EstNbr );
            ZeidonStringCopy( sb_szEPA_EstNbr, 1, 0, "*****", 1, 0, 21 );
      szEPA_EstNbr = sb_szEPA_EstNbr.toString( );}
   } 

   //:END
   //:szStatementText = "EPA Reg. No. " + szEPA_RegNbr + "   EPA Est. No. " + szEPA_EstNbr
    {StringBuilder sb_szStatementText;
   if ( szStatementText == null )
      sb_szStatementText = new StringBuilder( 32 );
   else
      sb_szStatementText = new StringBuilder( szStatementText );
      ZeidonStringCopy( sb_szStatementText, 1, 0, "EPA Reg. No. ", 1, 0, 32001 );
   szStatementText = sb_szStatementText.toString( );}
    {StringBuilder sb_szStatementText;
   if ( szStatementText == null )
      sb_szStatementText = new StringBuilder( 32 );
   else
      sb_szStatementText = new StringBuilder( szStatementText );
      ZeidonStringConcat( sb_szStatementText, 1, 0, szEPA_RegNbr, 1, 0, 32001 );
   szStatementText = sb_szStatementText.toString( );}
    {StringBuilder sb_szStatementText;
   if ( szStatementText == null )
      sb_szStatementText = new StringBuilder( 32 );
   else
      sb_szStatementText = new StringBuilder( szStatementText );
      ZeidonStringConcat( sb_szStatementText, 1, 0, "   EPA Est. No. ", 1, 0, 32001 );
   szStatementText = sb_szStatementText.toString( );}
    {StringBuilder sb_szStatementText;
   if ( szStatementText == null )
      sb_szStatementText = new StringBuilder( 32 );
   else
      sb_szStatementText = new StringBuilder( szStatementText );
      ZeidonStringConcat( sb_szStatementText, 1, 0, szEPA_EstNbr, 1, 0, 32001 );
   szStatementText = sb_szStatementText.toString( );}

   //:szWriteBuffer = szLeadingBlanks + "<fo:block "
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<fo:block ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:AddFormatToSpecialText( mSPLDefPDF, "EPA Reg / Est No.", szWriteBuffer )
   {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
       omSPLDef_AddFormatToSpecialText( mSPLDefPDF, "EPA Reg / Est No.", sb_szWriteBuffer );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "   " + szStatementText
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "   ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:szWriteBuffer = szLeadingBlanks + "</fo:block>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</fo:block>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dFullNameLFM( VIEW mSPLDef BASED ON LOD mSPLDef,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )
public int 
omSPLDef_dFullNameLFM( View     mSPLDef,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         break ;
      //:  //PersonName_LastFirstMiddle( mSPLDef, InternalEntityStructure,
      //:  //                            InternalAttribStructure, GetOrSetFlag )

      //:  /* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:     /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
public int 
omSPLDef_dFullNameFML( View     mSPLDef,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{

   //:dFullNameFML( VIEW mSPLDef BASED ON LOD mSPLDef,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_FirstMiddleLast( mSPLDef, InternalEntityStructure,
         //:                           InternalAttribStructure, GetOrSetFlag )
         {
          ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
          m_ZGlobal1_Operation.PersonName_FirstMiddleLast( mSPLDef, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
          // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
         }
         break ;
      //:                              
      //:  /* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:     /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dPrimRegNameID( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   STRING ( 1000 ) szString
public int 
omSPLDef_dPrimRegNameID( View     mSPLDef,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   String   szString = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mSPLDef.Organization EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "Organization" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSPLDef.Organization.Name
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 1001, mSPLDef, "Organization", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
            //:IF mSPLDef.PrimaryRegistrant.EPA_CompanyNumber != ""
            if ( CompareAttributeToString( mSPLDef, "PrimaryRegistrant", "EPA_CompanyNumber", "" ) != 0 )
            { 
               //:szString = szString + " (" +
               //:        mSPLDef.PrimaryRegistrant.EPA_CompanyNumber + ")"
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, " (", 1, 0, 1001 );
               szString = sb_szString.toString( );}
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 7, mSPLDef, "PrimaryRegistrant", "EPA_CompanyNumber", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 1001 );
               szString = sb_szString.toString( );}
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, ")", 1, 0, 1001 );
               szString = sb_szString.toString( );}
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:szString = ""
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringCopy( sb_szString, 1, 0, "", 1, 0, 1001 );
            szString = sb_szString.toString( );}
         } 

         //:END
         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef,
         //:                   InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szString );
         //:RETURN 0
         if(8==8)return( 0 );

         //:/* end zDERIVED_GET */
         //:OF   zDERIVED_SET:
         case zDERIVED_SET :
            break ;
      } 


      //:  /* end zDERIVED_SET */
      //:END  /* case */
      return( 0 );
   } 


   //:DERIVED ATTRIBUTE OPERATION
   //:dSubregNameID( VIEW mSPLDef BASED ON LOD mSPLDef,
   //:            STRING ( 32 ) InternalEntityStructure,
   //:            STRING ( 32 ) InternalAttribStructure,
   //:            SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
omSPLDef_dSubregNameID( View     mSPLDef,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   String   szString = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mSPLDef.SubregOrganization EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "SubregOrganization" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSPLDef.SubregOrganization.Name
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 1001, mSPLDef, "SubregOrganization", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
            //:IF mSPLDef.Subregistrant.EPA_CompanyNumber != ""
            if ( CompareAttributeToString( mSPLDef, "Subregistrant", "EPA_CompanyNumber", "" ) != 0 )
            { 
               //:szString = szString + " (" +
               //:        mSPLDef.Subregistrant.EPA_CompanyNumber + ")"
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, " (", 1, 0, 1001 );
               szString = sb_szString.toString( );}
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 7, mSPLDef, "Subregistrant", "EPA_CompanyNumber", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 1001 );
               szString = sb_szString.toString( );}
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, ")", 1, 0, 1001 );
               szString = sb_szString.toString( );}
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:szString = ""
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringCopy( sb_szString, 1, 0, "", 1, 0, 1001 );
            szString = sb_szString.toString( );}
         } 

         //:END
         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef,
         //:                   InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szString );
         //:RETURN 0
         if(8==8)return( 0 );

         //:/* end zDERIVED_GET */
         //:OF   zDERIVED_SET:
         case zDERIVED_SET :
            break ;
      } 


      //:  /* end zDERIVED_SET */
      //:END  /* case */
      return( 0 );
   } 


   //:DERIVED ATTRIBUTE OPERATION
   //:dIngredientName( VIEW mSPLDef BASED ON LOD mSPLDef,
   //:              STRING ( 32 ) InternalEntityStructure,
   //:              STRING ( 32 ) InternalAttribStructure,
   //:              SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
omSPLDef_dIngredientName( View     mSPLDef,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   String   szString = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mSPLDef.S_IngredientsStatement EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "S_IngredientsStatement" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSPLDef.SPLD_IngredientsStatement.CommonName
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 1001, mSPLDef, "SPLD_IngredientsStatement", "CommonName", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
            //:IF szString = ""
            if ( ZeidonStringCompare( szString, 1, 0, "", 1, 0, 1001 ) == 0 )
            { 
               //:szString = mSPLDef.SPLD_IngredientsStatement.ChemicalName
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                               GetVariableFromAttribute( sb_szString, mi_lTempInteger_2, 'S', 1001, mSPLDef, "SPLD_IngredientsStatement", "ChemicalName", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szString = sb_szString.toString( );}
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:szString = ""
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringCopy( sb_szString, 1, 0, "", 1, 0, 1001 );
            szString = sb_szString.toString( );}
         } 

         //:END
         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef,
         //:                   InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szString );
         //:RETURN 0
         if(8==8)return( 0 );

         //:/* end zDERIVED_GET */
         //:OF   zDERIVED_SET:
         case zDERIVED_SET :
            break ;
      } 


      //:  /* end zDERIVED_SET */
      //:END  /* case */
      return( 0 );
   } 


   //:TRANSFORMATION OPERATION
   //:BuildSPLD_FromSLC( VIEW NewSPLD   BASED ON LOD mSPLDef,
   //:                VIEW SourceSLC BASED ON LOD mSubLC )

   //:VIEW mSPLDef2 BASED ON LOD mSPLDef
public int 
omSPLDef_BuildSPLD_FromSLC( View     NewSPLD,
                            View     SourceSLC )
{
   zVIEW    mSPLDef2 = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      RESULT = 0;


   //:// Build a new SPLD from the selected Subregistrant Label Content entry.

   //:// Tie back to SLC, if necessary.
   //:IF NewSPLD.SubregLabelContent DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( NewSPLD, "SubregLabelContent" );
   if ( lTempInteger_0 != 0 )
   { 
      //:INCLUDE NewSPLD.SubregLabelContent FROM SourceSLC.SubregLabelContent
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "SubregLabelContent", SourceSLC, "SubregLabelContent", zPOS_AFTER );
   } 

   //:END

   //:// Delete any current component entries.
   //:FOR EACH NewSPLD.SPLD_GeneralSection 
   RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY NewSPLD.SPLD_GeneralSection NONE 
      RESULT = DeleteEntity( NewSPLD, "SPLD_GeneralSection", zREPOS_NONE );
      RESULT = SetCursorNextEntity( NewSPLD, "SPLD_GeneralSection", "" );
   } 

   //:END
   //:FOR EACH NewSPLD.SPLD_IngredientsSection 
   RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_IngredientsSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY NewSPLD.SPLD_IngredientsSection NONE 
      RESULT = DeleteEntity( NewSPLD, "SPLD_IngredientsSection", zREPOS_NONE );
      RESULT = SetCursorNextEntity( NewSPLD, "SPLD_IngredientsSection", "" );
   } 

   //:END
   //:FOR EACH NewSPLD.SPLD_StorageDisposalSection 
   RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_StorageDisposalSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY NewSPLD.SPLD_StorageDisposalSection NONE 
      RESULT = DeleteEntity( NewSPLD, "SPLD_StorageDisposalSection", zREPOS_NONE );
      RESULT = SetCursorNextEntity( NewSPLD, "SPLD_StorageDisposalSection", "" );
   } 

   //:END
   //:FOR EACH NewSPLD.SPLD_DirectionsForUseSection 
   RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY NewSPLD.SPLD_DirectionsForUseSection NONE 
      RESULT = DeleteEntity( NewSPLD, "SPLD_DirectionsForUseSection", zREPOS_NONE );
      RESULT = SetCursorNextEntity( NewSPLD, "SPLD_DirectionsForUseSection", "" );
   } 

   //:END
   //:FOR EACH NewSPLD.SPLD_MarketingSection 
   RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY NewSPLD.SPLD_MarketingSection NONE 
      RESULT = DeleteEntity( NewSPLD, "SPLD_MarketingSection", zREPOS_NONE );
      RESULT = SetCursorNextEntity( NewSPLD, "SPLD_MarketingSection", "" );
   } 

   //:END
   //:FOR EACH NewSPLD.SPLD_HumanHazardSection 
   RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_HumanHazardSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY NewSPLD.SPLD_HumanHazardSection NONE 
      RESULT = DeleteEntity( NewSPLD, "SPLD_HumanHazardSection", zREPOS_NONE );
      RESULT = SetCursorNextEntity( NewSPLD, "SPLD_HumanHazardSection", "" );
   } 

   //:END
   //:FOR EACH NewSPLD.SPLD_Usage 
   RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY NewSPLD.SPLD_Usage NONE 
      RESULT = DeleteEntity( NewSPLD, "SPLD_Usage", zREPOS_NONE );
      RESULT = SetCursorNextEntity( NewSPLD, "SPLD_Usage", "" );
   } 

   //:END

   //:// General Section ... Precautionary, First Aid, Other Hazard
   //:FOR EACH SourceSLC.S_GeneralSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_GeneralSection
      RESULT = CreateEntity( NewSPLD, "SPLD_GeneralSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralSection", SourceSLC, "S_GeneralSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralSection", SourceSLC, "S_GeneralSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_GeneralSection FROM SourceSLC.S_GeneralSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_GeneralSection", SourceSLC, "S_GeneralSection", zPOS_AFTER );
      //:FOR EACH SourceSLC.S_GeneralStatement
      RESULT = SetCursorFirstEntity( SourceSLC, "S_GeneralStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_GeneralStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_GeneralStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralStatement", SourceSLC, "S_GeneralStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralStatement", SourceSLC, "S_GeneralStatement", zSET_NULL );
         //:INCLUDE NewSPLD.S_GeneralStatement FROM SourceSLC.S_GeneralStatement
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_GeneralStatement", SourceSLC, "S_GeneralStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceSLC, "S_GeneralStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_GeneralSection", "" );
      //:END
   } 

   //:END

   //:// Ingredients Section
   //:FOR EACH SourceSLC.S_IngredientsSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_IngredientsSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_IngredientsSection
      RESULT = CreateEntity( NewSPLD, "SPLD_IngredientsSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsSection", SourceSLC, "S_IngredientsSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsSection", SourceSLC, "S_IngredientsSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_IngredientsSection FROM SourceSLC.S_IngredientsSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_IngredientsSection", SourceSLC, "S_IngredientsSection", zPOS_AFTER );
      //:FOR EACH SourceSLC.S_IngredientsStatement
      RESULT = SetCursorFirstEntity( SourceSLC, "S_IngredientsStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_IngredientsStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_IngredientsStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsStatement", SourceSLC, "S_IngredientsStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsStatement", SourceSLC, "S_IngredientsStatement", zSET_NULL );
         //:INCLUDE NewSPLD.S_IngredientsStatement FROM SourceSLC.S_IngredientsStatement
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_IngredientsStatement", SourceSLC, "S_IngredientsStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceSLC, "S_IngredientsStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_IngredientsSection", "" );
      //:END
   } 

   //:END

   //:// StorageDisposal Section
   //:FOR EACH SourceSLC.S_StorageDisposalSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_StorageDisposalSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_StorageDisposalSection
      RESULT = CreateEntity( NewSPLD, "SPLD_StorageDisposalSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalSection", SourceSLC, "S_StorageDisposalSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalSection", SourceSLC, "S_StorageDisposalSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_StorageDisposalSection FROM SourceSLC.S_StorageDisposalSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_StorageDisposalSection", SourceSLC, "S_StorageDisposalSection", zPOS_AFTER );
      //:FOR EACH SourceSLC.S_StorageDisposalStatement
      RESULT = SetCursorFirstEntity( SourceSLC, "S_StorageDisposalStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_StorageDisposalStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_StorageDisposalStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalStatement", SourceSLC, "S_StorageDisposalStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalStatement", SourceSLC, "S_StorageDisposalStatement", zSET_NULL );
         //:INCLUDE NewSPLD.S_StorageDisposalStatement FROM SourceSLC.S_StorageDisposalStatement
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_StorageDisposalStatement", SourceSLC, "S_StorageDisposalStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceSLC, "S_StorageDisposalStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_StorageDisposalSection", "" );
      //:END
   } 

   //:END

   //:// Delete any Marketing Sections in SPLD not in SLC.
   //:/*FOR EACH NewSPLD.SPLD_MarketingSection 
   //:   IF NewSPLD.S_MarketingSection DOES NOT EXIST
   //:      DELETE ENTITY NewSPLD.SPLD_MarketingSection NONE 
   //:   END
   //:END*/

   //:// HumanHazard Section
   //:FOR EACH SourceSLC.S_HumanHazardSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_HumanHazardSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_HumanHazardSection
      RESULT = CreateEntity( NewSPLD, "SPLD_HumanHazardSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_HumanHazardSection", SourceSLC, "S_HumanHazardSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_HumanHazardSection", SourceSLC, "S_HumanHazardSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_HumanHazardSection FROM SourceSLC.S_HumanHazardSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_HumanHazardSection", SourceSLC, "S_HumanHazardSection", zPOS_AFTER );
      RESULT = SetCursorNextEntity( SourceSLC, "S_HumanHazardSection", "" );
   } 

   //:END

   //:// Build Directions for Use Entries (without Usage entries).
   //:FOR EACH SourceSLC.S_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_DirectionsForUseSection
      RESULT = CreateEntity( NewSPLD, "SPLD_DirectionsForUseSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseSection", SourceSLC, "S_DirectionsForUseSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseSection", SourceSLC, "S_DirectionsForUseSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_DirectionsForUseSection FROM SourceSLC.S_DirectionsForUseSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_DirectionsForUseSection", SourceSLC, "S_DirectionsForUseSection", zPOS_AFTER );
      //:FOR EACH SourceSLC.S_DirectionsForUseStatement
      RESULT = SetCursorFirstEntity( SourceSLC, "S_DirectionsForUseStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_DirectionsForUseStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_DirectionsForUseStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseStatement", SourceSLC, "S_DirectionsForUseStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseStatement", SourceSLC, "S_DirectionsForUseStatement", zSET_NULL );
         //:INCLUDE NewSPLD.S_DirectionsForUseStatement FROM SourceSLC.S_DirectionsForUseStatement 
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_DirectionsForUseStatement", SourceSLC, "S_DirectionsForUseStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceSLC, "S_DirectionsForUseStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// Build Marketing Entries (without Usage entries).
   //:FOR EACH SourceSLC.S_MarketingSection 
   RESULT = SetCursorFirstEntity( SourceSLC, "S_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_MarketingSection
      RESULT = CreateEntity( NewSPLD, "SPLD_MarketingSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingSection", SourceSLC, "S_MarketingSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingSection", SourceSLC, "S_MarketingSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_MarketingSection FROM SourceSLC.S_MarketingSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_MarketingSection", SourceSLC, "S_MarketingSection", zPOS_AFTER );
      //:FOR EACH SourceSLC.S_MarketingStatement
      RESULT = SetCursorFirstEntity( SourceSLC, "S_MarketingStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_MarketingStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_MarketingStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingStatement", SourceSLC, "S_MarketingStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingStatement", SourceSLC, "S_MarketingStatement", zSET_NULL );
         //:INCLUDE NewSPLD.S_MarketingStatement FROM SourceSLC.S_MarketingStatement 
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_MarketingStatement", SourceSLC, "S_MarketingStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceSLC, "S_MarketingStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_MarketingSection", "" );
      //:END
   } 

   //:END

   //:// Go to build Directions for Use and Marketing entries.
   //:BuildUsageEntriesFrSLC( NewSPLD, SourceSLC )
   omSPLDef_BuildUsageEntriesFrSLC( NewSPLD, SourceSLC );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:RebuildSPLD_FromSLC( VIEW NewSPLD   BASED ON LOD mSPLDef,
//:                     VIEW SourceSLC BASED ON LOD mSubLC )

//:   VIEW mSPLDef2 BASED ON LOD mSPLDef
public int 
omSPLDef_RebuildSPLD_FromSLC( View     NewSPLD,
                              View     SourceSLC )
{
   zVIEW    mSPLDef2 = new zVIEW( );
   int      RESULT = 0;


   //:// Correct Directions and Marketing entries by rebuilding them.

   //:FOR EACH NewSPLD.SPLD_DirectionsForUseSection 
   RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY NewSPLD.SPLD_DirectionsForUseSection NONE 
      RESULT = DeleteEntity( NewSPLD, "SPLD_DirectionsForUseSection", zREPOS_NONE );
      RESULT = SetCursorNextEntity( NewSPLD, "SPLD_DirectionsForUseSection", "" );
   } 

   //:END
   //:FOR EACH NewSPLD.SPLD_MarketingSection 
   RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY NewSPLD.SPLD_MarketingSection NONE 
      RESULT = DeleteEntity( NewSPLD, "SPLD_MarketingSection", zREPOS_NONE );
      RESULT = SetCursorNextEntity( NewSPLD, "SPLD_MarketingSection", "" );
   } 

   //:END

   //:// Build Directions for Use Entries (without Usage entries).
   //:FOR EACH SourceSLC.S_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_DirectionsForUseSection
      RESULT = CreateEntity( NewSPLD, "SPLD_DirectionsForUseSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseSection", SourceSLC, "S_DirectionsForUseSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseSection", SourceSLC, "S_DirectionsForUseSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_DirectionsForUseSection FROM SourceSLC.S_DirectionsForUseSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_DirectionsForUseSection", SourceSLC, "S_DirectionsForUseSection", zPOS_AFTER );
      //:FOR EACH SourceSLC.S_DirectionsForUseStatement
      RESULT = SetCursorFirstEntity( SourceSLC, "S_DirectionsForUseStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_DirectionsForUseStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_DirectionsForUseStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseStatement", SourceSLC, "S_DirectionsForUseStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseStatement", SourceSLC, "S_DirectionsForUseStatement", zSET_NULL );
         RESULT = SetCursorNextEntity( SourceSLC, "S_DirectionsForUseStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// Build Marketing Entries (without Usage entries).
   //:FOR EACH SourceSLC.S_MarketingSection 
   RESULT = SetCursorFirstEntity( SourceSLC, "S_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_MarketingSection
      RESULT = CreateEntity( NewSPLD, "SPLD_MarketingSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingSection", SourceSLC, "S_MarketingSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingSection", SourceSLC, "S_MarketingSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_MarketingSection FROM SourceSLC.S_MarketingSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_MarketingSection", SourceSLC, "S_MarketingSection", zPOS_AFTER );
      //:FOR EACH SourceSLC.S_MarketingStatement
      RESULT = SetCursorFirstEntity( SourceSLC, "S_MarketingStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_MarketingStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_MarketingStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingStatement", SourceSLC, "S_MarketingStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingStatement", SourceSLC, "S_MarketingStatement", zSET_NULL );
         RESULT = SetCursorNextEntity( SourceSLC, "S_MarketingStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_MarketingSection", "" );
      //:END
   } 

   //:END

   //:// Go to build Directions for Use and Marketing entries.
   //:BuildUsageEntriesFrSLC( NewSPLD, SourceSLC )
   omSPLDef_BuildUsageEntriesFrSLC( NewSPLD, SourceSLC );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildCompositeEntries( VIEW mSPLDef BASED ON LOD mSPLDef )

//:   STRING ( 100 )   szCompositeLocation
public int 
omSPLDef_BuildCompositeEntries( View     mSPLDef )
{
   String   szCompositeLocation = null;
   //:STRING ( 4000 )  szDisplayValue
   String   szDisplayValue = null;
   //:STRING ( 4000 )  szOriginalDisplayValue
   String   szOriginalDisplayValue = null;
   //:STRING ( 20 )    szUsageType
   String   szUsageType = null;
   //:INTEGER          MaxDisplayLength
   int      MaxDisplayLength = 0;
   //:INTEGER          OriginalStringLength
   int      OriginalStringLength = 0;
   //:INTEGER          Count
   int      Count = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_2 = null;
   String   szTempString_3 = null;
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   String   szTempString_6 = null;
   String   szTempString_7 = null;
   String   szTempString_8 = null;
   int      lTempInteger_6 = 0;
   String   szTempString_9 = null;
   int      lTempInteger_7 = 0;
   String   szTempString_10 = null;
   int      lTempInteger_8 = 0;
   String   szTempString_11 = null;
   int      lTempInteger_9 = 0;
   String   szTempString_12 = null;
   int      lTempInteger_10 = 0;


   //:// Build the flat display of all components subobject.
   //:MaxDisplayLength = 100
   MaxDisplayLength = 100;

   //:// First clear any current entries.
   //:FOR EACH mSPLDef.CompositeComponentList
   RESULT = SetCursorFirstEntity( mSPLDef, "CompositeComponentList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mSPLDef.CompositeComponentList NONE 
      RESULT = DeleteEntity( mSPLDef, "CompositeComponentList", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "CompositeComponentList", "" );
   } 

   //:END

   //:// General Section and Statements.
   //:FOR EACH mSPLDef.SPLD_GeneralSection 
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSPLDef.CompositeComponentList 
      RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
      //:mSPLDef.CompositeComponentList.Type               = "SPLD_GeneralSection"
      SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_GeneralSection" );
      //:mSPLDef.CompositeComponentList.SelectLevel        = 1
      SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 1 );
      //:mSPLDef.CompositeComponentList.DisplayType        = "General"
      SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "General" );
      //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_GeneralSection.ID 
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_GeneralSection", "ID" );
      //:mSPLDef.CompositeComponentList.DisplayValue       = mSPLDef.SPLD_GeneralSection.Title
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayValue", mSPLDef, "SPLD_GeneralSection", "Title" );
      //:mSPLDef.CompositeComponentList.Value              = mSPLDef.CompositeComponentList.DisplayValue
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "CompositeComponentList", "DisplayValue" );
      //:mSPLDef.CompositeComponentList.DisplayTypeIndent  = mSPLDef.CompositeComponentList.DisplayType
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", mSPLDef, "CompositeComponentList", "DisplayType" );

      //:FOR EACH mSPLDef.SPLD_GeneralStatement 
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_GeneralStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSPLDef.CompositeComponentList 
         RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
         //:mSPLDef.CompositeComponentList.Type               = "SPLD_GeneralStatement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_GeneralStatement" );
         //:mSPLDef.CompositeComponentList.SelectLevel        = 2
         SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 2 );
         //:mSPLDef.CompositeComponentList.DisplayType        = "Statement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "Statement" );
         //:mSPLDef.CompositeComponentList.DisplayTypeIndent  = "...Statement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", "...Statement" );
         //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_GeneralStatement.ID 
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_GeneralStatement", "ID" );
         //:mSPLDef.CompositeComponentList.Value              = mSPLDef.SPLD_GeneralStatement.Text
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "SPLD_GeneralStatement", "Text" );
         //:SetMatchingAttributesByName( mSPLDef, "CompositeComponentList",
         //:                             mSPLDef, "SPLD_GeneralStatement", zSET_NULL )
         SetMatchingAttributesByName( mSPLDef, "CompositeComponentList", mSPLDef, "SPLD_GeneralStatement", zSET_NULL );

         //:szOriginalDisplayValue = mSPLDef.SPLD_GeneralStatement.Text
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szOriginalDisplayValue;
         if ( szOriginalDisplayValue == null )
            sb_szOriginalDisplayValue = new StringBuilder( 32 );
         else
            sb_szOriginalDisplayValue = new StringBuilder( szOriginalDisplayValue );
                   GetVariableFromAttribute( sb_szOriginalDisplayValue, mi_lTempInteger_0, 'S', 4001, mSPLDef, "SPLD_GeneralStatement", "Text", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szOriginalDisplayValue = sb_szOriginalDisplayValue.toString( );}
         //:GetAttributeLength( OriginalStringLength, mSPLDef, "SPLD_GeneralStatement", "Text" )
         {MutableInt mi_OriginalStringLength = new MutableInt( OriginalStringLength );
                   GetAttributeLength( mi_OriginalStringLength, mSPLDef, "SPLD_GeneralStatement", "Text" );
         OriginalStringLength = mi_OriginalStringLength.intValue( );}
         //:IF MaxDisplayLength < OriginalStringLength
         if ( MaxDisplayLength < OriginalStringLength )
         { 
            //:szDisplayValue = szOriginalDisplayValue[1:100] + "....."
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringCopy( sb_szDisplayValue, 1, 0, szOriginalDisplayValue, 1, 100, 4001 );
            szDisplayValue = sb_szDisplayValue.toString( );}
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringConcat( sb_szDisplayValue, 1, 0, ".....", 1, 0, 4001 );
            szDisplayValue = sb_szDisplayValue.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szDisplayValue = szOriginalDisplayValue    
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringCopy( sb_szDisplayValue, 1, 0, szOriginalDisplayValue, 1, 0, 4001 );
            szDisplayValue = sb_szDisplayValue.toString( );}
         } 

         //:END
         //:mSPLDef.CompositeComponentList.DisplayValue   = szDisplayValue
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayValue", szDisplayValue );
         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_GeneralStatement", "" );
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_GeneralSection", "" );
      //:END
   } 

   //:END

   //:// Ingredients Section and Statements.
   //:FOR EACH mSPLDef.SPLD_IngredientsSection 
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_IngredientsSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSPLDef.CompositeComponentList 
      RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
      //:mSPLDef.CompositeComponentList.Type               = "SPLD_IngredientsSection"
      SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_IngredientsSection" );
      //:mSPLDef.CompositeComponentList.SelectLevel        = 1
      SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 1 );
      //:mSPLDef.CompositeComponentList.DisplayType        = "Ingredients"
      SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "Ingredients" );
      //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_IngredientsSection.ID 
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_IngredientsSection", "ID" );
      //:mSPLDef.CompositeComponentList.DisplayValue       = mSPLDef.SPLD_IngredientsSection.ActiveTitle 
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayValue", mSPLDef, "SPLD_IngredientsSection", "ActiveTitle" );
      //:mSPLDef.CompositeComponentList.Value              = mSPLDef.CompositeComponentList.DisplayValue
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "CompositeComponentList", "DisplayValue" );
      //:mSPLDef.CompositeComponentList.DisplayTypeIndent  = mSPLDef.CompositeComponentList.DisplayType
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", mSPLDef, "CompositeComponentList", "DisplayType" );

      //:FOR EACH mSPLDef.SPLD_IngredientsStatement 
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_IngredientsStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSPLDef.CompositeComponentList 
         RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
         //:mSPLDef.CompositeComponentList.Type               = "SPLD_IngredientsStatement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_IngredientsStatement" );
         //:mSPLDef.CompositeComponentList.SelectLevel        = 2
         SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 2 );
         //:mSPLDef.CompositeComponentList.DisplayType        = "Statement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "Statement" );
         //:mSPLDef.CompositeComponentList.DisplayTypeIndent  = "...Statement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", "...Statement" );
         //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_IngredientsStatement.ID 
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_IngredientsStatement", "ID" );
         //:mSPLDef.CompositeComponentList.DisplayValue       = mSPLDef.SPLD_IngredientsStatement.ChemicalName 
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayValue", mSPLDef, "SPLD_IngredientsStatement", "ChemicalName" );
         //:mSPLDef.CompositeComponentList.Value              = mSPLDef.SPLD_IngredientsStatement.ChemicalName
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "SPLD_IngredientsStatement", "ChemicalName" );
         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_IngredientsStatement", "" );
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_IngredientsSection", "" );
      //:END
   } 

   //:END

   //:// StorageDisposal Section and Statements.
   //:FOR EACH mSPLDef.SPLD_StorageDisposalSection 
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_StorageDisposalSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSPLDef.CompositeComponentList 
      RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
      //:mSPLDef.CompositeComponentList.Type               = "SPLD_StorageDisposalSection"
      SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_StorageDisposalSection" );
      //:mSPLDef.CompositeComponentList.SelectLevel        = 1
      SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 1 );
      //:mSPLDef.CompositeComponentList.DisplayType        = "StorageDisposal"
      SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "StorageDisposal" );
      //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_StorageDisposalSection.ID 
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_StorageDisposalSection", "ID" );
      //:mSPLDef.CompositeComponentList.DisplayValue       = mSPLDef.SPLD_StorageDisposalSection.Title 
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayValue", mSPLDef, "SPLD_StorageDisposalSection", "Title" );
      //:mSPLDef.CompositeComponentList.Value              = mSPLDef.CompositeComponentList.DisplayValue
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "CompositeComponentList", "DisplayValue" );
      //:mSPLDef.CompositeComponentList.DisplayTypeIndent  = mSPLDef.CompositeComponentList.DisplayType
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", mSPLDef, "CompositeComponentList", "DisplayType" );

      //:FOR EACH mSPLDef.SPLD_StorageDisposalStatement 
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_StorageDisposalStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSPLDef.SPLD_StorageDisposalStatement.Title != ""
         if ( CompareAttributeToString( mSPLDef, "SPLD_StorageDisposalStatement", "Title", "" ) != 0 )
         { 
            //:CREATE ENTITY mSPLDef.CompositeComponentList 
            RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
            //:mSPLDef.CompositeComponentList.Type               = "SPLD_StorageDisposalTitle"
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_StorageDisposalTitle" );
            //:mSPLDef.CompositeComponentList.SelectLevel        = 2
            SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 2 );
            //:mSPLDef.CompositeComponentList.DisplayType        = "Title"
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "Title" );
            //:mSPLDef.CompositeComponentList.DisplayTypeIndent  = "...Stmt Title"
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", "...Stmt Title" );
            //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_StorageDisposalStatement.ID  
            SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_StorageDisposalStatement", "ID" );
            //:mSPLDef.CompositeComponentList.Value              = mSPLDef.SPLD_StorageDisposalStatement.Title
            SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "SPLD_StorageDisposalStatement", "Title" );
         } 

         //:END

         //:CREATE ENTITY mSPLDef.CompositeComponentList 
         RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
         //:mSPLDef.CompositeComponentList.Type               = "SPLD_StorageDisposalStatement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_StorageDisposalStatement" );
         //:mSPLDef.CompositeComponentList.SelectLevel        = 2
         SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 2 );
         //:mSPLDef.CompositeComponentList.DisplayType        = "Statement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "Statement" );
         //:mSPLDef.CompositeComponentList.DisplayTypeIndent  = "...Statement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", "...Statement" );
         //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_StorageDisposalStatement.ID  
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_StorageDisposalStatement", "ID" );
         //:mSPLDef.CompositeComponentList.Value              = mSPLDef.SPLD_StorageDisposalStatement.Text
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "SPLD_StorageDisposalStatement", "Text" );

         //:szOriginalDisplayValue = mSPLDef.SPLD_StorageDisposalStatement.Text
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szOriginalDisplayValue;
         if ( szOriginalDisplayValue == null )
            sb_szOriginalDisplayValue = new StringBuilder( 32 );
         else
            sb_szOriginalDisplayValue = new StringBuilder( szOriginalDisplayValue );
                   GetVariableFromAttribute( sb_szOriginalDisplayValue, mi_lTempInteger_1, 'S', 4001, mSPLDef, "SPLD_StorageDisposalStatement", "Text", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szOriginalDisplayValue = sb_szOriginalDisplayValue.toString( );}
         //:GetAttributeLength( OriginalStringLength, mSPLDef, "SPLD_StorageDisposalStatement", "Text" )
         {MutableInt mi_OriginalStringLength = new MutableInt( OriginalStringLength );
                   GetAttributeLength( mi_OriginalStringLength, mSPLDef, "SPLD_StorageDisposalStatement", "Text" );
         OriginalStringLength = mi_OriginalStringLength.intValue( );}
         //:IF MaxDisplayLength < OriginalStringLength
         if ( MaxDisplayLength < OriginalStringLength )
         { 
            //:szDisplayValue = szOriginalDisplayValue[1:100] + "....."
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringCopy( sb_szDisplayValue, 1, 0, szOriginalDisplayValue, 1, 100, 4001 );
            szDisplayValue = sb_szDisplayValue.toString( );}
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringConcat( sb_szDisplayValue, 1, 0, ".....", 1, 0, 4001 );
            szDisplayValue = sb_szDisplayValue.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szDisplayValue = szOriginalDisplayValue    
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringCopy( sb_szDisplayValue, 1, 0, szOriginalDisplayValue, 1, 0, 4001 );
            szDisplayValue = sb_szDisplayValue.toString( );}
         } 

         //:END
         //:mSPLDef.CompositeComponentList.DisplayValue   = szDisplayValue
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayValue", szDisplayValue );
         //:SetMatchingAttributesByName( mSPLDef, "CompositeComponentList",
         //:                             mSPLDef, "SPLD_StorageDisposalStatement", zSET_NULL )
         SetMatchingAttributesByName( mSPLDef, "CompositeComponentList", mSPLDef, "SPLD_StorageDisposalStatement", zSET_NULL );
         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_StorageDisposalStatement", "" );
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_StorageDisposalSection", "" );
      //:END
   } 

   //:END

   //:// DirectionsForUse Section and Statements.
   //:FOR EACH mSPLDef.SPLD_DirectionsForUseSection 
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSPLDef.CompositeComponentList 
      RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
      //:mSPLDef.CompositeComponentList.Type               = "SPLD_DirectionsForUseSection"
      SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_DirectionsForUseSection" );
      //:mSPLDef.CompositeComponentList.SelectLevel        = 1
      SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 1 );
      //:mSPLDef.CompositeComponentList.DisplayType        = "DirectionsForUse"
      SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "DirectionsForUse" );
      //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_DirectionsForUseSection.ID
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_DirectionsForUseSection", "ID" );
      //:IF mSPLDef.SPLD_DirectionsForUseSection.Title != ""
      if ( CompareAttributeToString( mSPLDef, "SPLD_DirectionsForUseSection", "Title", "" ) != 0 )
      { 
         //:IF mSPLDef.SPLD_DirectionsForUseSection.Name = ""
         if ( CompareAttributeToString( mSPLDef, "SPLD_DirectionsForUseSection", "Name", "" ) == 0 )
         { 
            //:mSPLDef.CompositeComponentList.DisplayValue = mSPLDef.SPLD_DirectionsForUseSection.Title
            SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayValue", mSPLDef, "SPLD_DirectionsForUseSection", "Title" );
            //:ELSE
         } 
         else
         { 
            //:mSPLDef.CompositeComponentList.DisplayValue = mSPLDef.SPLD_DirectionsForUseSection.Name + " - " +
            //:                                             mSPLDef.SPLD_DirectionsForUseSection.Title 
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, mSPLDef, "SPLD_DirectionsForUseSection", "Name" );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringConcat( sb_szTempString_0, 1, 0, " - ", 1, 0, 32001 );
            szTempString_0 = sb_szTempString_0.toString( );}
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 255, mSPLDef, "SPLD_DirectionsForUseSection", "Title", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 32001 );
            szTempString_0 = sb_szTempString_0.toString( );}
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayValue", szTempString_0 );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:mSPLDef.CompositeComponentList.DisplayValue = mSPLDef.SPLD_DirectionsForUseSection.Name 
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayValue", mSPLDef, "SPLD_DirectionsForUseSection", "Name" );
      } 

      //:END
      //:mSPLDef.CompositeComponentList.Value              = mSPLDef.CompositeComponentList.DisplayValue
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "CompositeComponentList", "DisplayValue" );
      //:mSPLDef.CompositeComponentList.DisplayTypeIndent  = mSPLDef.CompositeComponentList.DisplayType
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", mSPLDef, "CompositeComponentList", "DisplayType" );

      //:FOR EACH mSPLDef.SPLD_DirectionsForUseStatement 
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_DirectionsForUseStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSPLDef.SPLD_DirectionsForUseStatement.Title != ""
         if ( CompareAttributeToString( mSPLDef, "SPLD_DirectionsForUseStatement", "Title", "" ) != 0 )
         { 
            //:CREATE ENTITY mSPLDef.CompositeComponentList 
            RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
            //:mSPLDef.CompositeComponentList.Type               = "SPLD_DirectionsForUseTitle"
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_DirectionsForUseTitle" );
            //:mSPLDef.CompositeComponentList.SelectLevel        = 2
            SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 2 );
            //:mSPLDef.CompositeComponentList.DisplayType        = "Title"
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "Title" );
            //:mSPLDef.CompositeComponentList.DisplayTypeIndent  = "...Stmt Title"
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", "...Stmt Title" );
            //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_DirectionsForUseStatement.ID  
            SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_DirectionsForUseStatement", "ID" );
            //:mSPLDef.CompositeComponentList.Value              = mSPLDef.SPLD_DirectionsForUseStatement.Title
            SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "SPLD_DirectionsForUseStatement", "Title" );
         } 

         //:END

         //:CREATE ENTITY mSPLDef.CompositeComponentList 
         RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
         //:mSPLDef.CompositeComponentList.Type               = "SPLD_DirectionsForUseStatement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_DirectionsForUseStatement" );
         //:mSPLDef.CompositeComponentList.SelectLevel        = 2
         SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 2 );
         //:mSPLDef.CompositeComponentList.DisplayType        = "Statement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "Statement" );
         //:mSPLDef.CompositeComponentList.DisplayTypeIndent  = "...Statement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", "...Statement" );
         //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_DirectionsForUseStatement.ID 
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_DirectionsForUseStatement", "ID" );
         //:mSPLDef.CompositeComponentList.Value              = mSPLDef.SPLD_DirectionsForUseStatement.Text
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "SPLD_DirectionsForUseStatement", "Text" );

         //:szOriginalDisplayValue = mSPLDef.SPLD_DirectionsForUseStatement.Text
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szOriginalDisplayValue;
         if ( szOriginalDisplayValue == null )
            sb_szOriginalDisplayValue = new StringBuilder( 32 );
         else
            sb_szOriginalDisplayValue = new StringBuilder( szOriginalDisplayValue );
                   GetVariableFromAttribute( sb_szOriginalDisplayValue, mi_lTempInteger_3, 'S', 4001, mSPLDef, "SPLD_DirectionsForUseStatement", "Text", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         szOriginalDisplayValue = sb_szOriginalDisplayValue.toString( );}
         //:GetAttributeLength( OriginalStringLength, mSPLDef, "SPLD_DirectionsForUseStatement", "Text" )
         {MutableInt mi_OriginalStringLength = new MutableInt( OriginalStringLength );
                   GetAttributeLength( mi_OriginalStringLength, mSPLDef, "SPLD_DirectionsForUseStatement", "Text" );
         OriginalStringLength = mi_OriginalStringLength.intValue( );}
         //:IF MaxDisplayLength < OriginalStringLength
         if ( MaxDisplayLength < OriginalStringLength )
         { 
            //:szDisplayValue = szOriginalDisplayValue[1:100] + "....."
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringCopy( sb_szDisplayValue, 1, 0, szOriginalDisplayValue, 1, 100, 4001 );
            szDisplayValue = sb_szDisplayValue.toString( );}
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringConcat( sb_szDisplayValue, 1, 0, ".....", 1, 0, 4001 );
            szDisplayValue = sb_szDisplayValue.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szDisplayValue = szOriginalDisplayValue    
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringCopy( sb_szDisplayValue, 1, 0, szOriginalDisplayValue, 1, 0, 4001 );
            szDisplayValue = sb_szDisplayValue.toString( );}
         } 

         //:END
         //:mSPLDef.CompositeComponentList.DisplayValue   = szDisplayValue
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayValue", szDisplayValue );
         //:SetMatchingAttributesByName( mSPLDef, "CompositeComponentList",
         //:                             mSPLDef, "SPLD_DirectionsForUseStatement", zSET_NULL )
         SetMatchingAttributesByName( mSPLDef, "CompositeComponentList", mSPLDef, "SPLD_DirectionsForUseStatement", zSET_NULL );

         //:// Add any Usage entries.
         //:// First sort by Usage Type, but keeping the entries in their original order otherwise.
         //:Count = 0
         Count = 0;
         //:FOR EACH mSPLDef.SPLD_DirectionsUsageOrdering 
         RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_DirectionsUsageOrdering", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:Count = Count + 1
            Count = Count + 1;
            //:mSPLDef.SPLD_DirectionsUsageOrdering.wSortOrder = Count
            SetAttributeFromInteger( mSPLDef, "SPLD_DirectionsUsageOrdering", "wSortOrder", Count );
            RESULT = SetCursorNextEntity( mSPLDef, "SPLD_DirectionsUsageOrdering", "" );
         } 

         //:END
         //:OrderEntityForView( mSPLDef, "SPLD_DirectionsUsageOrdering", "SPLD_DirectionsUsage.UsageType A wSortOrder A" )
         OrderEntityForView( mSPLDef, "SPLD_DirectionsUsageOrdering", "SPLD_DirectionsUsage.UsageType A wSortOrder A" );
         //:FOR EACH mSPLDef.SPLD_DirectionsUsageOrdering 
         RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_DirectionsUsageOrdering", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY mSPLDef.CompositeComponentList 
            RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
            //:GetStringFromAttributeByContext( szUsageType, mSPLDef, "SPLD_DirectionsUsage", "UsageType", "FullUsageType", 20 )
            {StringBuilder sb_szUsageType;
            if ( szUsageType == null )
               sb_szUsageType = new StringBuilder( 32 );
            else
               sb_szUsageType = new StringBuilder( szUsageType );
                         GetStringFromAttributeByContext( sb_szUsageType, mSPLDef, "SPLD_DirectionsUsage", "UsageType", "FullUsageType", 20 );
            szUsageType = sb_szUsageType.toString( );}
            //:mSPLDef.CompositeComponentList.Type              = "SPLD_DirectionsUsageOrdering"
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_DirectionsUsageOrdering" );
            //:mSPLDef.CompositeComponentList.SelectLevel       = 3
            SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 3 );
            //:mSPLDef.CompositeComponentList.DisplayType       = "DirUseUsage"
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "DirUseUsage" );
            //:mSPLDef.CompositeComponentList.DisplayTypeIndent = "...DU " + szUsageType
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringCopy( sb_szTempString_2, 1, 0, "...DU ", 1, 0, 255 );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringConcat( sb_szTempString_2, 1, 0, szUsageType, 1, 0, 255 );
            szTempString_2 = sb_szTempString_2.toString( );}
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", szTempString_2 );
            //:mSPLDef.CompositeComponentList.Value             = mSPLDef.SPLD_DirectionsUsage.Name 
            SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "SPLD_DirectionsUsage", "Name" );
            //:mSPLDef.CompositeComponentList.DisplayValue      = mSPLDef.SPLD_DirectionsUsage.Name 
            SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayValue", mSPLDef, "SPLD_DirectionsUsage", "Name" );
            //:SET CURSOR FIRST mSPLDef.SPLD_Usage WHERE mSPLDef.SPLD_Usage.UsageType            = mSPLDef.SPLD_DirectionsUsage.UsageType 
            //:                                  AND mSPLDef.SPLD_Usage.ClaimsClassification = mSPLDef.SPLD_DirectionsUsage.ClaimsClassification 
            //:                                  AND mSPLDef.SPLD_Usage.Name                 = mSPLDef.SPLD_DirectionsUsage.Name 
            RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSPLDef, "SPLD_Usage", "UsageType", mSPLDef, "SPLD_DirectionsUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( mSPLDef, "SPLD_Usage", "ClaimsClassification", mSPLDef, "SPLD_DirectionsUsage", "ClaimsClassification" ) != 0 ||
                       CompareAttributeToAttribute( mSPLDef, "SPLD_Usage", "Name", mSPLDef, "SPLD_DirectionsUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( mSPLDef, "SPLD_Usage", "" );
               } 

            } 

            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_Usage.ID 
               SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_Usage", "ID" );
               //://TraceLineI( "#### Valid Usage ID ", mSPLDef.SPLD_Usage.ID )
               //:ELSE
            } 
            else
            { 
            } 

            RESULT = SetCursorNextEntity( mSPLDef, "SPLD_DirectionsUsageOrdering", "" );
            //:   //TraceLineS( "#### Invalid Usage ID ", mSPLDef.SPLD_DirectionsUsage.Name )
            //:END
         } 

         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_DirectionsForUseStatement", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// Marketing Section and Statements.
   //:FOR EACH mSPLDef.SPLD_MarketingSection 
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSPLDef.CompositeComponentList 
      RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
      //:mSPLDef.CompositeComponentList.Type               = "SPLD_MarketingSection"
      SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_MarketingSection" );
      //:mSPLDef.CompositeComponentList.SelectLevel        = 1
      SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 1 );
      //:mSPLDef.CompositeComponentList.DisplayType        = "Marketing"
      SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "Marketing" );
      //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_MarketingSection.ID 
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_MarketingSection", "ID" );
      //:mSPLDef.CompositeComponentList.Name               = mSPLDef.SPLD_MarketingSection.Name
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Name", mSPLDef, "SPLD_MarketingSection", "Name" );
      //:TraceLineS( "SPLD BuildCompositeEntries Name: ", mSPLDef.SPLD_MarketingSection.Name )
      {StringBuilder sb_szTempString_3;
      if ( szTempString_3 == null )
         sb_szTempString_3 = new StringBuilder( 32 );
      else
         sb_szTempString_3 = new StringBuilder( szTempString_3 );
             GetStringFromAttribute( sb_szTempString_3, mSPLDef, "SPLD_MarketingSection", "Name" );
      szTempString_3 = sb_szTempString_3.toString( );}
      TraceLineS( "SPLD BuildCompositeEntries Name: ", szTempString_3 );
      //:IF mSPLDef.SPLD_MarketingSection.Title != ""
      if ( CompareAttributeToString( mSPLDef, "SPLD_MarketingSection", "Title", "" ) != 0 )
      { 
         //:IF mSPLDef.SPLD_MarketingSection.Name = ""
         if ( CompareAttributeToString( mSPLDef, "SPLD_MarketingSection", "Name", "" ) == 0 )
         { 
            //:mSPLDef.CompositeComponentList.DisplayValue = mSPLDef.SPLD_MarketingSection.Title
            SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayValue", mSPLDef, "SPLD_MarketingSection", "Title" );
            //:ELSE
         } 
         else
         { 
            //:mSPLDef.CompositeComponentList.DisplayValue = mSPLDef.SPLD_MarketingSection.Name + " - " +
            //:                                             mSPLDef.SPLD_MarketingSection.Title 
            {StringBuilder sb_szTempString_4;
            if ( szTempString_4 == null )
               sb_szTempString_4 = new StringBuilder( 32 );
            else
               sb_szTempString_4 = new StringBuilder( szTempString_4 );
                         GetStringFromAttribute( sb_szTempString_4, mSPLDef, "SPLD_MarketingSection", "Name" );
            szTempString_4 = sb_szTempString_4.toString( );}
             {StringBuilder sb_szTempString_4;
            if ( szTempString_4 == null )
               sb_szTempString_4 = new StringBuilder( 32 );
            else
               sb_szTempString_4 = new StringBuilder( szTempString_4 );
                        ZeidonStringConcat( sb_szTempString_4, 1, 0, " - ", 1, 0, 32001 );
            szTempString_4 = sb_szTempString_4.toString( );}
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szTempString_5;
            if ( szTempString_5 == null )
               sb_szTempString_5 = new StringBuilder( 32 );
            else
               sb_szTempString_5 = new StringBuilder( szTempString_5 );
                         GetVariableFromAttribute( sb_szTempString_5, mi_lTempInteger_4, 'S', 255, mSPLDef, "SPLD_MarketingSection", "Title", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szTempString_5 = sb_szTempString_5.toString( );}
             {StringBuilder sb_szTempString_4;
            if ( szTempString_4 == null )
               sb_szTempString_4 = new StringBuilder( 32 );
            else
               sb_szTempString_4 = new StringBuilder( szTempString_4 );
                        ZeidonStringConcat( sb_szTempString_4, 1, 0, szTempString_5, 1, 0, 32001 );
            szTempString_4 = sb_szTempString_4.toString( );}
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayValue", szTempString_4 );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:mSPLDef.CompositeComponentList.DisplayValue = mSPLDef.SPLD_MarketingSection.Name 
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayValue", mSPLDef, "SPLD_MarketingSection", "Name" );
      } 

      //:END
      //:mSPLDef.CompositeComponentList.Value              = mSPLDef.CompositeComponentList.DisplayValue
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "CompositeComponentList", "DisplayValue" );
      //:mSPLDef.CompositeComponentList.DisplayTypeIndent  = mSPLDef.CompositeComponentList.DisplayType
      SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", mSPLDef, "CompositeComponentList", "DisplayType" );

      //:FOR EACH mSPLDef.SPLD_MarketingStatement 
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSPLDef.SPLD_MarketingStatement.Title != ""
         if ( CompareAttributeToString( mSPLDef, "SPLD_MarketingStatement", "Title", "" ) != 0 )
         { 
            //:CREATE ENTITY mSPLDef.CompositeComponentList 
            RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
            //:mSPLDef.CompositeComponentList.Type               = "SPLD_MarketingTitle"
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_MarketingTitle" );
            //:mSPLDef.CompositeComponentList.SelectLevel        = 2
            SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 2 );
            //:mSPLDef.CompositeComponentList.DisplayType        = "Title"
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "Title" );
            //:mSPLDef.CompositeComponentList.DisplayTypeIndent  = "...Stmt Title"
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", "...Stmt Title" );
            //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_MarketingStatement.ID  
            SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_MarketingStatement", "ID" );
            //:mSPLDef.CompositeComponentList.Value              = mSPLDef.SPLD_MarketingStatement.Title
            SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "SPLD_MarketingStatement", "Title" );
         } 

         //:END

         //:CREATE ENTITY mSPLDef.CompositeComponentList 
         RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
         //:mSPLDef.CompositeComponentList.Type               = "SPLD_MarketingStatement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_MarketingStatement" );
         //:mSPLDef.CompositeComponentList.SelectLevel        = 2
         SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 2 );
         //:mSPLDef.CompositeComponentList.DisplayType        = "Statement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "Statement" );
         //:mSPLDef.CompositeComponentList.DisplayTypeIndent  = "...Statement"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", "...Statement" );
         //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_MarketingStatement.ID 
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_MarketingStatement", "ID" );
         //:mSPLDef.CompositeComponentList.Value              = mSPLDef.SPLD_MarketingStatement.Text
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "SPLD_MarketingStatement", "Text" );

         //:szOriginalDisplayValue = mSPLDef.SPLD_MarketingStatement.Text 
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
         StringBuilder sb_szOriginalDisplayValue;
         if ( szOriginalDisplayValue == null )
            sb_szOriginalDisplayValue = new StringBuilder( 32 );
         else
            sb_szOriginalDisplayValue = new StringBuilder( szOriginalDisplayValue );
                   GetVariableFromAttribute( sb_szOriginalDisplayValue, mi_lTempInteger_5, 'S', 4001, mSPLDef, "SPLD_MarketingStatement", "Text", "", 0 );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );
         szOriginalDisplayValue = sb_szOriginalDisplayValue.toString( );}
         //:GetAttributeLength( OriginalStringLength, mSPLDef, "SPLD_MarketingStatement", "Text" )
         {MutableInt mi_OriginalStringLength = new MutableInt( OriginalStringLength );
                   GetAttributeLength( mi_OriginalStringLength, mSPLDef, "SPLD_MarketingStatement", "Text" );
         OriginalStringLength = mi_OriginalStringLength.intValue( );}
         //:IF MaxDisplayLength < OriginalStringLength
         if ( MaxDisplayLength < OriginalStringLength )
         { 
            //:szDisplayValue = szOriginalDisplayValue[1:100] + "....."
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringCopy( sb_szDisplayValue, 1, 0, szOriginalDisplayValue, 1, 100, 4001 );
            szDisplayValue = sb_szDisplayValue.toString( );}
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringConcat( sb_szDisplayValue, 1, 0, ".....", 1, 0, 4001 );
            szDisplayValue = sb_szDisplayValue.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szDisplayValue = szOriginalDisplayValue    
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringCopy( sb_szDisplayValue, 1, 0, szOriginalDisplayValue, 1, 0, 4001 );
            szDisplayValue = sb_szDisplayValue.toString( );}
         } 

         //:END
         //:mSPLDef.CompositeComponentList.DisplayValue = szDisplayValue
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayValue", szDisplayValue );
         //:SetMatchingAttributesByName( mSPLDef, "CompositeComponentList",
         //:                             mSPLDef, "SPLD_MarketingStatement", zSET_NULL )
         SetMatchingAttributesByName( mSPLDef, "CompositeComponentList", mSPLDef, "SPLD_MarketingStatement", zSET_NULL );
         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingStatement", "" );
      } 

      //:END

      //:// Add any Usage entries.
      //:// First sort by Usage Type, but keeping the entries in their original order otherwise.
      //:Count = 0
      Count = 0;
      //:FOR EACH mSPLDef.SPLD_MarketingUsageOrdering 
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:Count = Count + 1
         Count = Count + 1;
         //:mSPLDef.SPLD_MarketingUsageOrdering.wSortOrder = Count
         SetAttributeFromInteger( mSPLDef, "SPLD_MarketingUsageOrdering", "wSortOrder", Count );
         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "" );
      } 

      //:END
      //:OrderEntityForView( mSPLDef, "SPLD_MarketingUsageOrdering", "SPLD_MarketingUsage.UsageType A wSortOrder A" )
      OrderEntityForView( mSPLDef, "SPLD_MarketingUsageOrdering", "SPLD_MarketingUsage.UsageType A wSortOrder A" );
      //:FOR EACH mSPLDef.SPLD_MarketingUsageOrdering 
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSPLDef.CompositeComponentList 
         RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
         //:GetStringFromAttributeByContext( szUsageType, mSPLDef, "SPLD_MarketingUsage", "UsageType", "FullUsageType", 20 )
         {StringBuilder sb_szUsageType;
         if ( szUsageType == null )
            sb_szUsageType = new StringBuilder( 32 );
         else
            sb_szUsageType = new StringBuilder( szUsageType );
                   GetStringFromAttributeByContext( sb_szUsageType, mSPLDef, "SPLD_MarketingUsage", "UsageType", "FullUsageType", 20 );
         szUsageType = sb_szUsageType.toString( );}
         //:mSPLDef.CompositeComponentList.Type              = "SPLD_MarketingUsageOrdering"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_MarketingUsageOrdering" );
         //:mSPLDef.CompositeComponentList.SelectLevel       = 3
         SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 3 );
         //:mSPLDef.CompositeComponentList.DisplayType       = "MarketingUsage"
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayType", "MarketingUsage" );
         //:mSPLDef.CompositeComponentList.DisplayTypeIndent = "...Mktg " + szUsageType
          {StringBuilder sb_szTempString_6;
         if ( szTempString_6 == null )
            sb_szTempString_6 = new StringBuilder( 32 );
         else
            sb_szTempString_6 = new StringBuilder( szTempString_6 );
                  ZeidonStringCopy( sb_szTempString_6, 1, 0, "...Mktg ", 1, 0, 255 );
         szTempString_6 = sb_szTempString_6.toString( );}
          {StringBuilder sb_szTempString_6;
         if ( szTempString_6 == null )
            sb_szTempString_6 = new StringBuilder( 32 );
         else
            sb_szTempString_6 = new StringBuilder( szTempString_6 );
                  ZeidonStringConcat( sb_szTempString_6, 1, 0, szUsageType, 1, 0, 255 );
         szTempString_6 = sb_szTempString_6.toString( );}
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", szTempString_6 );
         //:mSPLDef.CompositeComponentList.Value             = mSPLDef.SPLD_MarketingUsage.Name 
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "Value", mSPLDef, "SPLD_MarketingUsage", "Name" );
         //:mSPLDef.CompositeComponentList.DisplayValue      = mSPLDef.SPLD_MarketingUsage.Name 
         SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayValue", mSPLDef, "SPLD_MarketingUsage", "Name" );
         //:SET CURSOR FIRST mSPLDef.SPLD_Usage WHERE mSPLDef.SPLD_Usage.UsageType            = mSPLDef.SPLD_MarketingUsage.UsageType 
         //:                                  AND mSPLDef.SPLD_Usage.ClaimsClassification = mSPLDef.SPLD_MarketingUsage.ClaimsClassification 
         //:                                  AND mSPLDef.SPLD_Usage.Name                 = mSPLDef.SPLD_MarketingUsage.Name 
         RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_Usage", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSPLDef, "SPLD_Usage", "UsageType", mSPLDef, "SPLD_MarketingUsage", "UsageType" ) != 0 ||
                    CompareAttributeToAttribute( mSPLDef, "SPLD_Usage", "ClaimsClassification", mSPLDef, "SPLD_MarketingUsage", "ClaimsClassification" ) != 0 ||
                    CompareAttributeToAttribute( mSPLDef, "SPLD_Usage", "Name", mSPLDef, "SPLD_MarketingUsage", "Name" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( mSPLDef, "SPLD_Usage", "" );
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_Usage.ID
            SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_Usage", "ID" );
            //:ELSE
         } 
         else
         { 
            //:TraceLineS( "#### Invalid Usage ID ", mSPLDef.SPLD_MarketingUsage.Name )
            {StringBuilder sb_szTempString_7;
            if ( szTempString_7 == null )
               sb_szTempString_7 = new StringBuilder( 32 );
            else
               sb_szTempString_7 = new StringBuilder( szTempString_7 );
                         GetStringFromAttribute( sb_szTempString_7, mSPLDef, "SPLD_MarketingUsage", "Name" );
            szTempString_7 = sb_szTempString_7.toString( );}
            TraceLineS( "#### Invalid Usage ID ", szTempString_7 );
         } 

         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingSection", "" );
      //:END
   } 

   //:END

   //:// Hazards Section
   //:CREATE ENTITY mSPLDef.CompositeComponentList 
   RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
   //:mSPLDef.CompositeComponentList.Type               = "SPLD_HumanHazardSection"
   SetAttributeFromString( mSPLDef, "CompositeComponentList", "Type", "SPLD_HumanHazardSection" );
   //:mSPLDef.CompositeComponentList.SelectLevel        = 1
   SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 1 );
   //:mSPLDef.CompositeComponentList.DisplayTypeIndent  = "Hazards"
   SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", "Hazards" );
   //:mSPLDef.CompositeComponentList.OriginalTypeID     = mSPLDef.SPLD_HumanHazardSection.ID 
   SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "OriginalTypeID", mSPLDef, "SPLD_HumanHazardSection", "ID" );
   //:mSPLDef.CompositeComponentList.DisplayValue       = ""
   SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayValue", "" );

   //:CREATE ENTITY mSPLDef.CompositeComponentList 
   RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
   //:mSPLDef.CompositeComponentList.SelectLevel       = 2
   SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 2 );
   //:mSPLDef.CompositeComponentList.DisplayTypeIndent = "...Signal Word"
   SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", "...Signal Word" );
   //:mSPLDef.CompositeComponentList.DisplayValue      = mSPLDef.SPLD_HumanHazardSection.EPA_SignalWord 
   SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayValue", mSPLDef, "SPLD_HumanHazardSection", "EPA_SignalWord" );

   //:CREATE ENTITY mSPLDef.CompositeComponentList 
   RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
   //:mSPLDef.CompositeComponentList.SelectLevel       = 2
   SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 2 );
   //:mSPLDef.CompositeComponentList.DisplayTypeIndent = "...Child Warning"
   SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", "...Child Warning" );
   //:mSPLDef.CompositeComponentList.DisplayValue      = mSPLDef.SPLD_HumanHazardSection.EPA_ChildHazardWarning 
   SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayValue", mSPLDef, "SPLD_HumanHazardSection", "EPA_ChildHazardWarning" );

   //:CREATE ENTITY mSPLDef.CompositeComponentList 
   RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
   //:mSPLDef.CompositeComponentList.SelectLevel       = 2
   SetAttributeFromInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 2 );
   //:mSPLDef.CompositeComponentList.DisplayTypeIndent = "...Precautionary Stmt"
   SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", "...Precautionary Stmt" );
   //:mSPLDef.CompositeComponentList.DisplayValue      = mSPLDef.SPLD_HumanHazardSection.PrecautionaryStatement 
   SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "DisplayValue", mSPLDef, "SPLD_HumanHazardSection", "PrecautionaryStatement" );

   //:CREATE ENTITY mSPLDef.CompositeComponentList 
   RESULT = CreateEntity( mSPLDef, "CompositeComponentList", zPOS_AFTER );
   //:mSPLDef.CompositeComponentList.DisplayTypeIndent = "Location"
   SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayTypeIndent", "Location" );
   //:szCompositeLocation = "..." + mSPLDef.SPLD_HumanHazardSection.PanelLoc1 
   {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
   StringBuilder sb_szTempString_8;
   if ( szTempString_8 == null )
      sb_szTempString_8 = new StringBuilder( 32 );
   else
      sb_szTempString_8 = new StringBuilder( szTempString_8 );
       GetVariableFromAttribute( sb_szTempString_8, mi_lTempInteger_6, 'S', 255, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc1", "", 0 );
   lTempInteger_6 = mi_lTempInteger_6.intValue( );
   szTempString_8 = sb_szTempString_8.toString( );}
    {StringBuilder sb_szCompositeLocation;
   if ( szCompositeLocation == null )
      sb_szCompositeLocation = new StringBuilder( 32 );
   else
      sb_szCompositeLocation = new StringBuilder( szCompositeLocation );
      ZeidonStringCopy( sb_szCompositeLocation, 1, 0, "...", 1, 0, 101 );
   szCompositeLocation = sb_szCompositeLocation.toString( );}
    {StringBuilder sb_szCompositeLocation;
   if ( szCompositeLocation == null )
      sb_szCompositeLocation = new StringBuilder( 32 );
   else
      sb_szCompositeLocation = new StringBuilder( szCompositeLocation );
      ZeidonStringConcat( sb_szCompositeLocation, 1, 0, szTempString_8, 1, 0, 101 );
   szCompositeLocation = sb_szCompositeLocation.toString( );}
   //:IF mSPLDef.SPLD_HumanHazardSection.PanelLoc2 != ""
   if ( CompareAttributeToString( mSPLDef, "SPLD_HumanHazardSection", "PanelLoc2", "" ) != 0 )
   { 
      //:szCompositeLocation = szCompositeLocation + " / " + mSPLDef.SPLD_HumanHazardSection.PanelLoc2 
       {StringBuilder sb_szCompositeLocation;
      if ( szCompositeLocation == null )
         sb_szCompositeLocation = new StringBuilder( 32 );
      else
         sb_szCompositeLocation = new StringBuilder( szCompositeLocation );
            ZeidonStringConcat( sb_szCompositeLocation, 1, 0, " / ", 1, 0, 101 );
      szCompositeLocation = sb_szCompositeLocation.toString( );}
      {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
      StringBuilder sb_szTempString_9;
      if ( szTempString_9 == null )
         sb_szTempString_9 = new StringBuilder( 32 );
      else
         sb_szTempString_9 = new StringBuilder( szTempString_9 );
             GetVariableFromAttribute( sb_szTempString_9, mi_lTempInteger_7, 'S', 255, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc2", "", 0 );
      lTempInteger_7 = mi_lTempInteger_7.intValue( );
      szTempString_9 = sb_szTempString_9.toString( );}
       {StringBuilder sb_szCompositeLocation;
      if ( szCompositeLocation == null )
         sb_szCompositeLocation = new StringBuilder( 32 );
      else
         sb_szCompositeLocation = new StringBuilder( szCompositeLocation );
            ZeidonStringConcat( sb_szCompositeLocation, 1, 0, szTempString_9, 1, 0, 101 );
      szCompositeLocation = sb_szCompositeLocation.toString( );}
   } 

   //:END
   //:IF mSPLDef.SPLD_HumanHazardSection.PanelLoc3 != ""
   if ( CompareAttributeToString( mSPLDef, "SPLD_HumanHazardSection", "PanelLoc3", "" ) != 0 )
   { 
      //:szCompositeLocation = szCompositeLocation + " / " + mSPLDef.SPLD_HumanHazardSection.PanelLoc3
       {StringBuilder sb_szCompositeLocation;
      if ( szCompositeLocation == null )
         sb_szCompositeLocation = new StringBuilder( 32 );
      else
         sb_szCompositeLocation = new StringBuilder( szCompositeLocation );
            ZeidonStringConcat( sb_szCompositeLocation, 1, 0, " / ", 1, 0, 101 );
      szCompositeLocation = sb_szCompositeLocation.toString( );}
      {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
      StringBuilder sb_szTempString_10;
      if ( szTempString_10 == null )
         sb_szTempString_10 = new StringBuilder( 32 );
      else
         sb_szTempString_10 = new StringBuilder( szTempString_10 );
             GetVariableFromAttribute( sb_szTempString_10, mi_lTempInteger_8, 'S', 255, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc3", "", 0 );
      lTempInteger_8 = mi_lTempInteger_8.intValue( );
      szTempString_10 = sb_szTempString_10.toString( );}
       {StringBuilder sb_szCompositeLocation;
      if ( szCompositeLocation == null )
         sb_szCompositeLocation = new StringBuilder( 32 );
      else
         sb_szCompositeLocation = new StringBuilder( szCompositeLocation );
            ZeidonStringConcat( sb_szCompositeLocation, 1, 0, szTempString_10, 1, 0, 101 );
      szCompositeLocation = sb_szCompositeLocation.toString( );}
   } 

   //:END
   //:IF mSPLDef.SPLD_HumanHazardSection.PanelLoc4 != ""
   if ( CompareAttributeToString( mSPLDef, "SPLD_HumanHazardSection", "PanelLoc4", "" ) != 0 )
   { 
      //:szCompositeLocation = szCompositeLocation + " / " + mSPLDef.SPLD_HumanHazardSection.PanelLoc4 
       {StringBuilder sb_szCompositeLocation;
      if ( szCompositeLocation == null )
         sb_szCompositeLocation = new StringBuilder( 32 );
      else
         sb_szCompositeLocation = new StringBuilder( szCompositeLocation );
            ZeidonStringConcat( sb_szCompositeLocation, 1, 0, " / ", 1, 0, 101 );
      szCompositeLocation = sb_szCompositeLocation.toString( );}
      {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
      StringBuilder sb_szTempString_11;
      if ( szTempString_11 == null )
         sb_szTempString_11 = new StringBuilder( 32 );
      else
         sb_szTempString_11 = new StringBuilder( szTempString_11 );
             GetVariableFromAttribute( sb_szTempString_11, mi_lTempInteger_9, 'S', 255, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc4", "", 0 );
      lTempInteger_9 = mi_lTempInteger_9.intValue( );
      szTempString_11 = sb_szTempString_11.toString( );}
       {StringBuilder sb_szCompositeLocation;
      if ( szCompositeLocation == null )
         sb_szCompositeLocation = new StringBuilder( 32 );
      else
         sb_szCompositeLocation = new StringBuilder( szCompositeLocation );
            ZeidonStringConcat( sb_szCompositeLocation, 1, 0, szTempString_11, 1, 0, 101 );
      szCompositeLocation = sb_szCompositeLocation.toString( );}
   } 

   //:END
   //:IF mSPLDef.SPLD_HumanHazardSection.PanelLoc5 != ""
   if ( CompareAttributeToString( mSPLDef, "SPLD_HumanHazardSection", "PanelLoc5", "" ) != 0 )
   { 
      //:szCompositeLocation = szCompositeLocation + " / " + mSPLDef.SPLD_HumanHazardSection.PanelLoc5 
       {StringBuilder sb_szCompositeLocation;
      if ( szCompositeLocation == null )
         sb_szCompositeLocation = new StringBuilder( 32 );
      else
         sb_szCompositeLocation = new StringBuilder( szCompositeLocation );
            ZeidonStringConcat( sb_szCompositeLocation, 1, 0, " / ", 1, 0, 101 );
      szCompositeLocation = sb_szCompositeLocation.toString( );}
      {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
      StringBuilder sb_szTempString_12;
      if ( szTempString_12 == null )
         sb_szTempString_12 = new StringBuilder( 32 );
      else
         sb_szTempString_12 = new StringBuilder( szTempString_12 );
             GetVariableFromAttribute( sb_szTempString_12, mi_lTempInteger_10, 'S', 255, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc5", "", 0 );
      lTempInteger_10 = mi_lTempInteger_10.intValue( );
      szTempString_12 = sb_szTempString_12.toString( );}
       {StringBuilder sb_szCompositeLocation;
      if ( szCompositeLocation == null )
         sb_szCompositeLocation = new StringBuilder( 32 );
      else
         sb_szCompositeLocation = new StringBuilder( szCompositeLocation );
            ZeidonStringConcat( sb_szCompositeLocation, 1, 0, szTempString_12, 1, 0, 101 );
      szCompositeLocation = sb_szCompositeLocation.toString( );}
   } 

   //:END
   //:mSPLDef.CompositeComponentList.DisplayValue   = szCompositeLocation
   SetAttributeFromString( mSPLDef, "CompositeComponentList", "DisplayValue", szCompositeLocation );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dPanelName( VIEW mSPLDef BASED ON LOD mSPLDef,
//:            STRING ( 32 ) InternalEntityStructure,
//:            STRING ( 32 ) InternalAttribStructure,
//:            SHORT GetOrSetFlag )

//:   STRING ( 16 ) szPanelName
public int 
omSPLDef_dPanelName( View     mSPLDef,
                     String InternalEntityStructure,
                     String InternalAttribStructure,
                     Integer   GetOrSetFlag )
{
   String   szPanelName = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         break ;

      //:// Currently the Panel Name is just the "panel" + Panel Number.
      //:/*szPanelName = "Panel"
      //://szPanelName = szPanelName + mSPLDef.SPLD_TemplatePanel.wSequentialPanelNumber
      //:// Store the calculated value in the object.
      //:StoreStringInRecord( mSPLDef,
      //:                  InternalEntityStructure, InternalAttribStructure, szPanelName )*/

      //:  /* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:     /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dSectionIdentifier( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   STRING ( 16 ) szSectionIdentifier
public int 
omSPLDef_dSectionIdentifier( View     mSPLDef,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szSectionIdentifier = null;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// This is the Section Type, unless the Section Type is Marketing, in which case the Section Name is added on.
         //:IF mSPLDef.LLD_Block.LLD_SectionType = "Marketing"
         if ( CompareAttributeToString( mSPLDef, "LLD_Block", "LLD_SectionType", "Marketing" ) == 0 )
         { 
            //:szSectionIdentifier = "Marketing - " + mSPLDef.LLD_Block.Name 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 129, mSPLDef, "LLD_Block", "Name", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szSectionIdentifier;
            if ( szSectionIdentifier == null )
               sb_szSectionIdentifier = new StringBuilder( 32 );
            else
               sb_szSectionIdentifier = new StringBuilder( szSectionIdentifier );
                        ZeidonStringCopy( sb_szSectionIdentifier, 1, 0, "Marketing - ", 1, 0, 17 );
            szSectionIdentifier = sb_szSectionIdentifier.toString( );}
             {StringBuilder sb_szSectionIdentifier;
            if ( szSectionIdentifier == null )
               sb_szSectionIdentifier = new StringBuilder( 32 );
            else
               sb_szSectionIdentifier = new StringBuilder( szSectionIdentifier );
                        ZeidonStringConcat( sb_szSectionIdentifier, 1, 0, szTempString_0, 1, 0, 17 );
            szSectionIdentifier = sb_szSectionIdentifier.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szSectionIdentifier = mSPLDef.LLD_Block.LLD_SectionType 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szSectionIdentifier;
            if ( szSectionIdentifier == null )
               sb_szSectionIdentifier = new StringBuilder( 32 );
            else
               sb_szSectionIdentifier = new StringBuilder( szSectionIdentifier );
                         GetVariableFromAttribute( sb_szSectionIdentifier, mi_lTempInteger_1, 'S', 17, mSPLDef, "LLD_Block", "LLD_SectionType", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szSectionIdentifier = sb_szSectionIdentifier.toString( );}
         } 

         //:END
         //:StoreStringInRecord( mSPLDef,
         //:               InternalEntityStructure, InternalAttribStructure, szSectionIdentifier )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szSectionIdentifier );
         break ;

      //:  /* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:     /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dMasterProductNameNbr( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   STRING ( 1000 ) szString
public int 
omSPLDef_dMasterProductNameNbr( View     mSPLDef,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   String   szString = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mSPLDef.MasterProduct  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "MasterProduct" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSPLDef.MasterProduct.Name + " (" +
            //:        mSPLDef.PrimaryRegistrant.EPA_CompanyNumber + "-" +
            //:        mSPLDef.MasterProduct.Number + ")"
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttribute( sb_szString, mSPLDef, "MasterProduct", "Name" );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, " (", 1, 0, 1001 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 7, mSPLDef, "PrimaryRegistrant", "EPA_CompanyNumber", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 1001 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, "-", 1, 0, 1001 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 6, mSPLDef, "MasterProduct", "Number", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_1, 1, 0, 1001 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, ")", 1, 0, 1001 );
            szString = sb_szString.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szString = ""
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringCopy( sb_szString, 1, 0, "", 1, 0, 1001 );
            szString = sb_szString.toString( );}
         } 

         //:END
         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef,
         //:                   InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szString );
         //:RETURN 0
         if(8==8)return( 0 );

         //:/* end zDERIVED_GET */
         //:OF   zDERIVED_SET:
         case zDERIVED_SET :
            break ;
      } 


      //:  /* end zDERIVED_SET */
      //:END  /* case */
      return( 0 );
   } 


   //:DERIVED ATTRIBUTE OPERATION
   //:dSubregProductNameNbr( VIEW mSPLDef BASED ON LOD mSPLDef,
   //:                    STRING ( 32 ) InternalEntityStructure,
   //:                    STRING ( 32 ) InternalAttribStructure,
   //:                    SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
omSPLDef_dSubregProductNameNbr( View     mSPLDef,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   String   szString = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:szString = mSPLDef.SubregProduct.Name
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetVariableFromAttribute( sb_szString, mi_lTempInteger_0, 'S', 1001, mSPLDef, "SubregProduct", "Name", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szString = sb_szString.toString( );}
         //:IF mSPLDef.SubregProduct.Number != 0
         if ( CompareAttributeToInteger( mSPLDef, "SubregProduct", "Number", 0 ) != 0 )
         { 
            //:szString = szString + " (" + mSPLDef.SubregProduct.Number + ")"
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, " (", 1, 0, 1001 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 6, mSPLDef, "SubregProduct", "Number", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 1001 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, ")", 1, 0, 1001 );
            szString = sb_szString.toString( );}
         } 

         //:END
         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef,
         //:                   InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szString );
         //:RETURN 0
         if(8==8)return( 0 );

         //:/* end zDERIVED_GET */
         //:OF   zDERIVED_SET:
         case zDERIVED_SET :
            break ;
      } 


      //:  /* end zDERIVED_SET */
      //:END  /* case */
      return( 0 );
   } 


   //:DERIVED ATTRIBUTE OPERATION
   //:dDisplayPathogenName( VIEW mSPLDef BASED ON LOD mSPLDef,
   //:                   STRING ( 32 ) InternalEntityStructure,
   //:                   STRING ( 32 ) InternalAttribStructure,
   //:                   SHORT GetOrSetFlag )

   //:VIEW mSPLDef2 BASED ON LOD mSPLDef
public int 
omSPLDef_dDisplayPathogenName( View     mSPLDef,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   zVIEW    mSPLDef2 = new zVIEW( );
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:STRING ( 100 ) szUsageType
   String   szUsageType = null;
   //:STRING ( 100 ) szClassification
   String   szClassification = null;
   //:STRING ( 100 ) szCombinedName
   String   szCombinedName = null;
   //:STRING ( 3 )   szFootnoteNumber
   String   szFootnoteNumber = null;
   //:INTEGER        Count
   int      Count = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Combine the Footnote Number as a subscript to the Claim Name, if it exists.
         //:szCombinedName = mSPLDef.M_Usage.Name 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szCombinedName;
         if ( szCombinedName == null )
            sb_szCombinedName = new StringBuilder( 32 );
         else
            sb_szCombinedName = new StringBuilder( szCombinedName );
                   GetVariableFromAttribute( sb_szCombinedName, mi_lTempInteger_0, 'S', 101, mSPLDef, "M_Usage", "Name", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szCombinedName = sb_szCombinedName.toString( );}

         //:// Set Footnote Number, if footnote exists.
         //:IF mSPLDef.M_UsageFootnoteUsed EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mSPLDef, "M_UsageFootnoteUsed" );
         if ( lTempInteger_1 == 0 )
         { 
            //:SET CURSOR FIRST mSPLDef.M_UsageFootnote WHERE mSPLDef.M_UsageFootnote.ID = mSPLDef.M_UsageFootnoteUsed.ID 
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, mSPLDef, "M_UsageFootnoteUsed", "ID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mSPLDef, "M_UsageFootnote", "ID", lTempInteger_2, "" );
            //:IF mSPLDef.M_UsageFootnote.wFootNoteRelativeNumber = ""
            if ( CompareAttributeToString( mSPLDef, "M_UsageFootnote", "wFootNoteRelativeNumber", "" ) == 0 )
            { 
               //:// Relative numbers haven't be set, so set them here.
               //:CreateViewFromView( mSPLDef2, mSPLDef )
               CreateViewFromView( mSPLDef2, mSPLDef );
               //:Count = 0
               Count = 0;
               //:FOR EACH mSPLDef2.M_UsageFootnote 
               RESULT = SetCursorFirstEntity( mSPLDef2, "M_UsageFootnote", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:Count = Count + 1
                  Count = Count + 1;
                  //:mSPLDef2.M_UsageFootnote.wFootNoteRelativeNumber = Count
                  SetAttributeFromInteger( mSPLDef2, "M_UsageFootnote", "wFootNoteRelativeNumber", Count );
                  RESULT = SetCursorNextEntity( mSPLDef2, "M_UsageFootnote", "" );
               } 

               //:END
               //:DropView( mSPLDef2 )
               DropView( mSPLDef2 );
            } 

            //:END 
            //:szFootnoteNumber = mSPLDef.M_UsageFootnote.wFootNoteRelativeNumber 
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szFootnoteNumber;
            if ( szFootnoteNumber == null )
               sb_szFootnoteNumber = new StringBuilder( 32 );
            else
               sb_szFootnoteNumber = new StringBuilder( szFootnoteNumber );
                         GetVariableFromAttribute( sb_szFootnoteNumber, mi_lTempInteger_3, 'S', 4, mSPLDef, "M_UsageFootnote", "wFootNoteRelativeNumber", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szFootnoteNumber = sb_szFootnoteNumber.toString( );}
            //:szCombinedName = szCombinedName + "<sup> " + szFootnoteNumber + "</sup>"
             {StringBuilder sb_szCombinedName;
            if ( szCombinedName == null )
               sb_szCombinedName = new StringBuilder( 32 );
            else
               sb_szCombinedName = new StringBuilder( szCombinedName );
                        ZeidonStringConcat( sb_szCombinedName, 1, 0, "<sup> ", 1, 0, 101 );
            szCombinedName = sb_szCombinedName.toString( );}
             {StringBuilder sb_szCombinedName;
            if ( szCombinedName == null )
               sb_szCombinedName = new StringBuilder( 32 );
            else
               sb_szCombinedName = new StringBuilder( szCombinedName );
                        ZeidonStringConcat( sb_szCombinedName, 1, 0, szFootnoteNumber, 1, 0, 101 );
            szCombinedName = sb_szCombinedName.toString( );}
             {StringBuilder sb_szCombinedName;
            if ( szCombinedName == null )
               sb_szCombinedName = new StringBuilder( 32 );
            else
               sb_szCombinedName = new StringBuilder( szCombinedName );
                        ZeidonStringConcat( sb_szCombinedName, 1, 0, "</sup>", 1, 0, 101 );
            szCombinedName = sb_szCombinedName.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef,
         //:                  InternalEntityStructure,
         //:                  InternalAttribStructure, szCombinedName )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szCombinedName );
         break ;
      //: 
      //:  /* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:     /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omSPLDef_BuildSPLD_FromSPLD( View     NewSPLD,
                             View     SourceSPLD,
                             View     ParentSLC )
{
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   //:BuildSPLD_FromSPLD( VIEW NewSPLD    BASED ON LOD mSPLDef,
   //:                 VIEW SourceSPLD BASED ON LOD mSPLDef,
   //:                 VIEW ParentSLC  BASED ON LOD mSubLC )

   //:// Build an new SPLD from a previous SPLD.
   //:// Most of the component construction is the same as that in BuildSPLD_FromSLC, or nearly
   //:// the same. Only the Marketing Section is completely driven from the source SPLD. These are
   //:// explained for each section below.

   //:// Set root attributes and tie back to Parent SLC.
   //:SetMatchingAttributesByName( NewSPLD, "SubregPhysicalLabelDef", SourceSPLD, "SubregPhysicalLabelDef", zSET_NULL )
   SetMatchingAttributesByName( NewSPLD, "SubregPhysicalLabelDef", SourceSPLD, "SubregPhysicalLabelDef", zSET_NULL );
   //:INCLUDE NewSPLD.SubregLabelContent FROM ParentSLC.SubregLabelContent
   RESULT = IncludeSubobjectFromSubobject( NewSPLD, "SubregLabelContent", ParentSLC, "SubregLabelContent", zPOS_AFTER );
   //:// Usage Entries.
   //:// Usage Entries come from original SPLD. Only those in ParentSLC, however, are used to create Usages in the new SPLD.
   //:FOR EACH SourceSPLD.SPLD_Usage
   RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST ParentSLC.S_Usage WHERE ParentSLC.S_Usage.UsageType = SourceSPLD.SPLD_Usage.UsageType
      //:                                     AND ParentSLC.S_Usage.Name = SourceSPLD.SPLD_Usage.Name
      RESULT = SetCursorFirstEntity( ParentSLC, "S_Usage", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( ParentSLC, "S_Usage", "UsageType", SourceSPLD, "SPLD_Usage", "UsageType" ) != 0 ||
                 CompareAttributeToAttribute( ParentSLC, "S_Usage", "Name", SourceSPLD, "SPLD_Usage", "Name" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( ParentSLC, "S_Usage", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_Usage
         RESULT = CreateEntity( NewSPLD, "SPLD_Usage", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_Usage", ParentSLC, "S_Usage", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_Usage", ParentSLC, "S_Usage", zSET_NULL );
      } 

      RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_Usage", "" );
      //:END
   } 

   //:END
   //:// General Section
   //:// Build from SLC, same as in BuildSPLD_FromSLC.
   //:FOR EACH ParentSLC.S_GeneralSection
   RESULT = SetCursorFirstEntity( ParentSLC, "S_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_GeneralSection
      RESULT = CreateEntity( NewSPLD, "SPLD_GeneralSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralSection", ParentSLC, "S_GeneralSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralSection", ParentSLC, "S_GeneralSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_GeneralSection FROM ParentSLC.S_GeneralSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_GeneralSection", ParentSLC, "S_GeneralSection", zPOS_AFTER );
      //:FOR EACH ParentSLC.S_GeneralStatement
      RESULT = SetCursorFirstEntity( ParentSLC, "S_GeneralStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_GeneralStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_GeneralStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralStatement", ParentSLC, "S_GeneralStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralStatement", ParentSLC, "S_GeneralStatement", zSET_NULL );
         //:INCLUDE NewSPLD.S_GeneralStatement FROM ParentSLC.S_GeneralStatement
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_GeneralStatement", ParentSLC, "S_GeneralStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( ParentSLC, "S_GeneralStatement", "" );
      } 

      RESULT = SetCursorNextEntity( ParentSLC, "S_GeneralSection", "" );
      //:END
   } 

   //:END
   //:// Ingredients Section
   //:// Build from SLC, same as in BuildSPLD_FromSLC.
   //:FOR EACH ParentSLC.S_IngredientsSection
   RESULT = SetCursorFirstEntity( ParentSLC, "S_IngredientsSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_IngredientsSection
      RESULT = CreateEntity( NewSPLD, "SPLD_IngredientsSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsSection", ParentSLC, "S_IngredientsSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsSection", ParentSLC, "S_IngredientsSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_IngredientsSection FROM ParentSLC.S_IngredientsSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_IngredientsSection", ParentSLC, "S_IngredientsSection", zPOS_AFTER );
      //:FOR EACH ParentSLC.S_IngredientsStatement
      RESULT = SetCursorFirstEntity( ParentSLC, "S_IngredientsStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_IngredientsStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_IngredientsStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsStatement", ParentSLC, "S_IngredientsStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsStatement", ParentSLC, "S_IngredientsStatement", zSET_NULL );
         //:INCLUDE NewSPLD.S_IngredientsStatement FROM ParentSLC.S_IngredientsStatement
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_IngredientsStatement", ParentSLC, "S_IngredientsStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( ParentSLC, "S_IngredientsStatement", "" );
      } 

      RESULT = SetCursorNextEntity( ParentSLC, "S_IngredientsSection", "" );
      //:END
   } 

   //:END
   //:// StorageDisposal Section
   //:// Build from SLC, same as in BuildSPLD_FromSLC, except that the container size is driven from the source SPLD.
   //:SET CURSOR FIRST ParentSLC.S_StorageDisposalSection WHERE ParentSLC.S_StorageDisposalSection.ContainerVolume = SourceSPLD.SPLD_StorageDisposalSection.ContainerVolume
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, SourceSPLD, "SPLD_StorageDisposalSection", "ContainerVolume" );
   szTempString_0 = sb_szTempString_0.toString( );}
   RESULT = SetCursorFirstEntityByString( ParentSLC, "S_StorageDisposalSection", "ContainerVolume", szTempString_0, "" );
   //:CREATE ENTITY NewSPLD.SPLD_StorageDisposalSection
   RESULT = CreateEntity( NewSPLD, "SPLD_StorageDisposalSection", zPOS_AFTER );
   //:SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalSection", ParentSLC, "S_StorageDisposalSection", zSET_NULL )
   SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalSection", ParentSLC, "S_StorageDisposalSection", zSET_NULL );
   //:INCLUDE NewSPLD.S_StorageDisposalSection FROM ParentSLC.S_StorageDisposalSection
   RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_StorageDisposalSection", ParentSLC, "S_StorageDisposalSection", zPOS_AFTER );
   //:FOR EACH ParentSLC.S_StorageDisposalStatement
   RESULT = SetCursorFirstEntity( ParentSLC, "S_StorageDisposalStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_StorageDisposalStatement
      RESULT = CreateEntity( NewSPLD, "SPLD_StorageDisposalStatement", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalStatement", ParentSLC, "S_StorageDisposalStatement", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalStatement", ParentSLC, "S_StorageDisposalStatement", zSET_NULL );
      //:INCLUDE NewSPLD.S_StorageDisposalStatement FROM ParentSLC.S_StorageDisposalStatement
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_StorageDisposalStatement", ParentSLC, "S_StorageDisposalStatement", zPOS_AFTER );
      RESULT = SetCursorNextEntity( ParentSLC, "S_StorageDisposalStatement", "" );
   } 

   //:END
   //:// DirectionsForUse Section
   //:// Build from SLC, same as in BuildSPLD_FromSLC, except that the Directions sections selected are driven by the Usages from
   //:// the source SPLD, which have already be copied to new SPLD..
   //:FOR EACH ParentSLC.S_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( ParentSLC, "S_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_DirectionsForUseSection
      RESULT = CreateEntity( NewSPLD, "SPLD_DirectionsForUseSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseSection", ParentSLC, "S_DirectionsForUseSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseSection", ParentSLC, "S_DirectionsForUseSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_DirectionsForUseSection FROM ParentSLC.S_DirectionsForUseSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_DirectionsForUseSection", ParentSLC, "S_DirectionsForUseSection", zPOS_AFTER );
      //:FOR EACH ParentSLC.S_DirectionsForUseStatement
      RESULT = SetCursorFirstEntity( ParentSLC, "S_DirectionsForUseStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_DirectionsForUseStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_DirectionsForUseStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseStatement", ParentSLC, "S_DirectionsForUseStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseStatement", ParentSLC, "S_DirectionsForUseStatement", zSET_NULL );
         //:INCLUDE NewSPLD.S_DirectionsForUseStatement FROM ParentSLC.S_DirectionsForUseStatement
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_DirectionsForUseStatement", ParentSLC, "S_DirectionsForUseStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( ParentSLC, "S_DirectionsForUseStatement", "" );
      } 

      //:END
      //:// Add each Usage from the SLC that is one of the Usages in new the SPLD.
      //:FOR EACH ParentSLC.S_DirectionsUsage WITHIN ParentSLC.S_DirectionsForUseSection
      RESULT = SetCursorFirstEntity( ParentSLC, "S_DirectionsUsage", "S_DirectionsForUseSection" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:SET CURSOR FIRST NewSPLD.SPLD_Usage WHERE NewSPLD.SPLD_Usage.UsageType = ParentSLC.S_DirectionsUsage.UsageType
         //:                                      AND NewSPLD.SPLD_Usage.Name = ParentSLC.S_DirectionsUsage.Name
         RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_Usage", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSPLD, "SPLD_Usage", "UsageType", ParentSLC, "S_DirectionsUsage", "UsageType" ) != 0 ||
                    CompareAttributeToAttribute( NewSPLD, "SPLD_Usage", "Name", ParentSLC, "S_DirectionsUsage", "Name" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( NewSPLD, "SPLD_Usage", "" );
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:CREATE ENTITY NewSPLD.SPLD_DirectionsUsageOrdering
            RESULT = CreateEntity( NewSPLD, "SPLD_DirectionsUsageOrdering", zPOS_AFTER );
            //:INCLUDE NewSPLD.SPLD_DirectionsUsage FROM NewSPLD.SPLD_Usage
            RESULT = IncludeSubobjectFromSubobject( NewSPLD, "SPLD_DirectionsUsage", NewSPLD, "SPLD_Usage", zPOS_AFTER );
         } 

         RESULT = SetCursorNextEntity( ParentSLC, "S_DirectionsUsage", "S_DirectionsForUseSection" );
         //:END
      } 

      RESULT = SetCursorNextEntity( ParentSLC, "S_DirectionsForUseSection", "" );
      //:END
   } 

   //:END
   //:// Now delete any "NON General" Directions for Use that have no Usages remaining.
   //:FOR EACH NewSPLD.SPLD_DirectionsForUseSection WHERE NewSPLD.SPLD_DirectionsForUseSection.GeneralUse != "Y"
   RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( NewSPLD, "SPLD_DirectionsForUseSection", "GeneralUse", "Y" ) != 0 )
      { 
         //:IF NewSPLD.SPLD_DirectionsUsage DOES NOT EXIST
         lTempInteger_0 = CheckExistenceOfEntity( NewSPLD, "SPLD_DirectionsUsage" );
         if ( lTempInteger_0 != 0 )
         { 
            //:DELETE ENTITY NewSPLD.SPLD_DirectionsForUseSection NONE
            RESULT = DeleteEntity( NewSPLD, "SPLD_DirectionsForUseSection", zREPOS_NONE );
         } 

      } 

      RESULT = SetCursorNextEntity( NewSPLD, "SPLD_DirectionsForUseSection", "" );
      //:END
   } 

   //:END
   //:// Marketing Section
   //:// These are driven  from the original SPLD. We progress from the original SPLD to its SLC, then to the S_MarketingSection of the
   //:// new SLC and generate the new SPLD Marketing Section from the S_MarketingSection of the new SLC.
   //:// Note that we won't recreate a Marketing Section that is not in the new SLC.
   //:FOR EACH SourceSPLD.SPLD_MarketingSection
   RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST ParentSLC.SP_MarketingSection WITHIN ParentSLC.SubregLabelContent
      //:           WHERE ParentSLC.SP_MarketingSection.ID = SourceSPLD.S_MarketingSection.ID
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, SourceSPLD, "S_MarketingSection", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( ParentSLC, "SP_MarketingSection", "ID", lTempInteger_1, "SubregLabelContent" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_MarketingSection
         RESULT = CreateEntity( NewSPLD, "SPLD_MarketingSection", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingSection", ParentSLC, "S_MarketingSection", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingSection", ParentSLC, "S_MarketingSection", zSET_NULL );
         //:INCLUDE NewSPLD.S_MarketingSection FROM ParentSLC.S_MarketingSection
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_MarketingSection", ParentSLC, "S_MarketingSection", zPOS_AFTER );
         //:FOR EACH ParentSLC.S_MarketingStatement
         RESULT = SetCursorFirstEntity( ParentSLC, "S_MarketingStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY NewSPLD.SPLD_MarketingStatement
            RESULT = CreateEntity( NewSPLD, "SPLD_MarketingStatement", zPOS_AFTER );
            //:SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingStatement", ParentSLC, "S_MarketingStatement", zSET_NULL )
            SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingStatement", ParentSLC, "S_MarketingStatement", zSET_NULL );
            //:INCLUDE NewSPLD.S_MarketingStatement FROM ParentSLC.S_MarketingStatement
            RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_MarketingStatement", ParentSLC, "S_MarketingStatement", zPOS_AFTER );
            RESULT = SetCursorNextEntity( ParentSLC, "S_MarketingStatement", "" );
         } 

         //:END
         //:// Add each Usage from the SLC that is one of the Usages in new the SPLD.
         //:// They are handled the same as for Marketing For Use above.
         //:FOR EACH ParentSLC.S_MarketingUsage WITHIN ParentSLC.S_MarketingSection
         RESULT = SetCursorFirstEntity( ParentSLC, "S_MarketingUsage", "S_MarketingSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST NewSPLD.SPLD_Usage WHERE NewSPLD.SPLD_Usage.UsageType = ParentSLC.S_MarketingUsage.UsageType
            //:                                      AND NewSPLD.SPLD_Usage.Name = ParentSLC.S_MarketingUsage.Name
            RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSPLD, "SPLD_Usage", "UsageType", ParentSLC, "S_MarketingUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( NewSPLD, "SPLD_Usage", "Name", ParentSLC, "S_MarketingUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( NewSPLD, "SPLD_Usage", "" );
               } 

            } 

            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:CREATE ENTITY NewSPLD.SPLD_MarketingUsageOrdering
               RESULT = CreateEntity( NewSPLD, "SPLD_MarketingUsageOrdering", zPOS_AFTER );
               //:INCLUDE NewSPLD.SPLD_MarketingUsage FROM NewSPLD.SPLD_Usage
               RESULT = IncludeSubobjectFromSubobject( NewSPLD, "SPLD_MarketingUsage", NewSPLD, "SPLD_Usage", zPOS_AFTER );
            } 

            RESULT = SetCursorNextEntity( ParentSLC, "S_MarketingUsage", "S_MarketingSection" );
            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_MarketingSection", "" );
      //:END
   } 

   //:END
   //:// HumanHazard Section
   //:// Build from SLC, same as in BuildSPLD_FromSLC.
   //:FOR EACH ParentSLC.S_HumanHazardSection
   RESULT = SetCursorFirstEntity( ParentSLC, "S_HumanHazardSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_HumanHazardSection
      RESULT = CreateEntity( NewSPLD, "SPLD_HumanHazardSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_HumanHazardSection", ParentSLC, "S_HumanHazardSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_HumanHazardSection", ParentSLC, "S_HumanHazardSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_HumanHazardSection FROM ParentSLC.S_HumanHazardSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_HumanHazardSection", ParentSLC, "S_HumanHazardSection", zPOS_AFTER );
      RESULT = SetCursorNextEntity( ParentSLC, "S_HumanHazardSection", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omSPLDef_DuplicateSPLD( View     NewSPLD,
                        View     SourceSPLD )
{
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;

   //:DuplicateSPLD( VIEW NewSPLD    BASED ON LOD mSPLDef,
   //:            VIEW SourceSPLD BASED ON LOD mSPLDef )

   //:// Build an new SPLD from an existing SPLD.
   //:CREATE ENTITY NewSPLD.SubregPhysicalLabelDef 
   RESULT = CreateEntity( NewSPLD, "SubregPhysicalLabelDef", zPOS_AFTER );
   //:SetMatchingAttributesByName( NewSPLD, "SubregPhysicalLabelDef", SourceSPLD, "SubregPhysicalLabelDef", zSET_NULL )
   SetMatchingAttributesByName( NewSPLD, "SubregPhysicalLabelDef", SourceSPLD, "SubregPhysicalLabelDef", zSET_NULL );
   //:NewSPLD.SubregPhysicalLabelDef.Name = SourceSPLD.SubregPhysicalLabelDef.Name + " (Duplicate)"
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, SourceSPLD, "SubregPhysicalLabelDef", "Name" );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
      ZeidonStringConcat( sb_szTempString_0, 1, 0, " (Duplicate)", 1, 0, 255 );
   szTempString_0 = sb_szTempString_0.toString( );}
   SetAttributeFromString( NewSPLD, "SubregPhysicalLabelDef", "Name", szTempString_0 );
   //:INCLUDE NewSPLD.SubregLabelContent FROM SourceSPLD.SubregLabelContent
   RESULT = IncludeSubobjectFromSubobject( NewSPLD, "SubregLabelContent", SourceSPLD, "SubregLabelContent", zPOS_AFTER );

   //:// SPLD_UsageType Entries
   //:FOR EACH SourceSPLD.SPLD_UsageType 
   RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_UsageType", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_UsageType 
      RESULT = CreateEntity( NewSPLD, "SPLD_UsageType", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_UsageType", SourceSPLD, "SPLD_UsageType", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_UsageType", SourceSPLD, "SPLD_UsageType", zSET_NULL );
      //:FOR EACH SourceSPLD.SPLD_Usage 
      RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_Usage", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_Usage 
         RESULT = CreateEntity( NewSPLD, "SPLD_Usage", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_Usage", SourceSPLD, "SPLD_Usage", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_Usage", SourceSPLD, "SPLD_Usage", zSET_NULL );
         //:INCLUDE NewSPLD.S_Usage FROM SourceSPLD.S_Usage 
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_Usage", SourceSPLD, "S_Usage", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_Usage", "" );
      } 

      RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_UsageType", "" );
      //:END
   } 

   //:END

   //:// SPLD_GeneralSection 
   //:FOR EACH SourceSPLD.SPLD_GeneralSection 
   RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_GeneralSection 
      RESULT = CreateEntity( NewSPLD, "SPLD_GeneralSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralSection", SourceSPLD, "SPLD_GeneralSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralSection", SourceSPLD, "SPLD_GeneralSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_GeneralSection FROM SourceSPLD.S_GeneralSection 
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_GeneralSection", SourceSPLD, "S_GeneralSection", zPOS_AFTER );
      //:FOR EACH SourceSPLD.SPLD_GeneralStatement 
      RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_GeneralStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_GeneralStatement 
         RESULT = CreateEntity( NewSPLD, "SPLD_GeneralStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralStatement", SourceSPLD, "SPLD_GeneralStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralStatement", SourceSPLD, "SPLD_GeneralStatement", zSET_NULL );
         //:INCLUDE NewSPLD.S_GeneralStatement FROM SourceSPLD.S_GeneralStatement 
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_GeneralStatement", SourceSPLD, "S_GeneralStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_GeneralStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_GeneralSection", "" );
      //:END
   } 

   //:END

   //:// Ingredients
   //:FOR EACH SourceSPLD.SPLD_IngredientsSection 
   RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_IngredientsSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_IngredientsSection 
      RESULT = CreateEntity( NewSPLD, "SPLD_IngredientsSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsSection", SourceSPLD, "SPLD_IngredientsSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsSection", SourceSPLD, "SPLD_IngredientsSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_IngredientsSection FROM SourceSPLD.S_IngredientsSection 
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_IngredientsSection", SourceSPLD, "S_IngredientsSection", zPOS_AFTER );
      //:FOR EACH SourceSPLD.SPLD_IngredientsStatement 
      RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_IngredientsStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_IngredientsStatement 
         RESULT = CreateEntity( NewSPLD, "SPLD_IngredientsStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsStatement", SourceSPLD, "SPLD_IngredientsStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsStatement", SourceSPLD, "SPLD_IngredientsStatement", zSET_NULL );
         //:INCLUDE NewSPLD.S_IngredientsStatement FROM SourceSPLD.S_IngredientsStatement 
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_IngredientsStatement", SourceSPLD, "S_IngredientsStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_IngredientsStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_IngredientsSection", "" );
      //:END
   } 

   //:END

   //:// SPLD_StorageDisposalSection 
   //:FOR EACH SourceSPLD.SPLD_StorageDisposalSection 
   RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_StorageDisposalSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_StorageDisposalSection 
      RESULT = CreateEntity( NewSPLD, "SPLD_StorageDisposalSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalSection", SourceSPLD, "SPLD_StorageDisposalSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalSection", SourceSPLD, "SPLD_StorageDisposalSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_StorageDisposalSection FROM SourceSPLD.S_StorageDisposalSection 
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_StorageDisposalSection", SourceSPLD, "S_StorageDisposalSection", zPOS_AFTER );
      //:FOR EACH SourceSPLD.SPLD_StorageDisposalStatement 
      RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_StorageDisposalStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_StorageDisposalStatement 
         RESULT = CreateEntity( NewSPLD, "SPLD_StorageDisposalStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalStatement", SourceSPLD, "SPLD_StorageDisposalStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalStatement", SourceSPLD, "SPLD_StorageDisposalStatement", zSET_NULL );
         //:INCLUDE NewSPLD.S_StorageDisposalStatement FROM SourceSPLD.S_StorageDisposalStatement 
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_StorageDisposalStatement", SourceSPLD, "S_StorageDisposalStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_StorageDisposalStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_StorageDisposalSection", "" );
      //:END
   } 

   //:END

   //:// SPLD_DirectionsForUseSection
   //:FOR EACH SourceSPLD.SPLD_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_DirectionsForUseSection
      RESULT = CreateEntity( NewSPLD, "SPLD_DirectionsForUseSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseSection", SourceSPLD, "SPLD_DirectionsForUseSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseSection", SourceSPLD, "SPLD_DirectionsForUseSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_DirectionsForUseSection FROM SourceSPLD.S_DirectionsForUseSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_DirectionsForUseSection", SourceSPLD, "S_DirectionsForUseSection", zPOS_AFTER );
      //:FOR EACH SourceSPLD.SPLD_DirectionsForUseStatement
      RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_DirectionsForUseStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_DirectionsForUseStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_DirectionsForUseStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseStatement", SourceSPLD, "SPLD_DirectionsForUseStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsForUseStatement", SourceSPLD, "SPLD_DirectionsForUseStatement", zSET_NULL );
         //:INCLUDE NewSPLD.S_DirectionsForUseStatement FROM SourceSPLD.S_DirectionsForUseStatement
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_DirectionsForUseStatement", SourceSPLD, "S_DirectionsForUseStatement", zPOS_AFTER );

         //:// UsageOrder entries
         //:FOR EACH SourceSPLD.SPLD_DirectionsUsageOrdering 
         RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_DirectionsUsageOrdering", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST NewSPLD.S_Usage WITHIN NewSPLD.SubregPhysicalLabelDef 
            //:           WHERE NewSPLD.S_Usage.ID = SourceSPLD.S_Usage.ID 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, SourceSPLD, "S_Usage", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( NewSPLD, "S_Usage", "ID", lTempInteger_0, "SubregPhysicalLabelDef" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:CREATE ENTITY NewSPLD.SPLD_DirectionsUsageOrdering 
               RESULT = CreateEntity( NewSPLD, "SPLD_DirectionsUsageOrdering", zPOS_AFTER );
               //:SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsUsageOrdering", SourceSPLD, "SPLD_DirectionsUsageOrdering", zSET_NULL )
               SetMatchingAttributesByName( NewSPLD, "SPLD_DirectionsUsageOrdering", SourceSPLD, "SPLD_DirectionsUsageOrdering", zSET_NULL );
               //:// We need to include the new SPLD_Usage entry created in the NewSPLD.
               //:INCLUDE NewSPLD.SPLD_DirectionsUsage FROM NewSPLD.SPLD_Usage
               RESULT = IncludeSubobjectFromSubobject( NewSPLD, "SPLD_DirectionsUsage", NewSPLD, "SPLD_Usage", zPOS_AFTER );
            } 

            RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_DirectionsUsageOrdering", "" );
            //:END
         } 

         RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_DirectionsForUseStatement", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// SPLD_MarketingSection
   //:FOR EACH SourceSPLD.SPLD_MarketingSection
   RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_MarketingSection
      RESULT = CreateEntity( NewSPLD, "SPLD_MarketingSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingSection", SourceSPLD, "SPLD_MarketingSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingSection", SourceSPLD, "SPLD_MarketingSection", zSET_NULL );
      //:INCLUDE NewSPLD.S_MarketingSection FROM SourceSPLD.S_MarketingSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_MarketingSection", SourceSPLD, "S_MarketingSection", zPOS_AFTER );
      //:FOR EACH SourceSPLD.SPLD_MarketingStatement
      RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_MarketingStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.SPLD_MarketingStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_MarketingStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingStatement", SourceSPLD, "SPLD_MarketingStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingStatement", SourceSPLD, "SPLD_MarketingStatement", zSET_NULL );
         //:INCLUDE NewSPLD.S_MarketingStatement FROM SourceSPLD.S_MarketingStatement
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_MarketingStatement", SourceSPLD, "S_MarketingStatement", zPOS_AFTER );

         //:// UsageOrder entries
         //:FOR EACH SourceSPLD.SPLD_MarketingUsageOrdering 
         RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_MarketingUsageOrdering", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST NewSPLD.S_Usage WITHIN NewSPLD.SubregPhysicalLabelDef 
            //:           WHERE NewSPLD.S_Usage.ID = SourceSPLD.S_Usage.ID 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                         GetIntegerFromAttribute( mi_lTempInteger_1, SourceSPLD, "S_Usage", "ID" );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( NewSPLD, "S_Usage", "ID", lTempInteger_1, "SubregPhysicalLabelDef" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:CREATE ENTITY NewSPLD.SPLD_MarketingUsageOrdering 
               RESULT = CreateEntity( NewSPLD, "SPLD_MarketingUsageOrdering", zPOS_AFTER );
               //:SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingUsageOrdering", SourceSPLD, "SPLD_MarketingUsageOrdering", zSET_NULL )
               SetMatchingAttributesByName( NewSPLD, "SPLD_MarketingUsageOrdering", SourceSPLD, "SPLD_MarketingUsageOrdering", zSET_NULL );
               //:// We need to include the new SPLD_Usage entry created in the NewSPLD.
               //:INCLUDE NewSPLD.SPLD_MarketingUsage FROM NewSPLD.SPLD_Usage 
               RESULT = IncludeSubobjectFromSubobject( NewSPLD, "SPLD_MarketingUsage", NewSPLD, "SPLD_Usage", zPOS_AFTER );
            } 

            RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_MarketingUsageOrdering", "" );
            //:END
         } 

         RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_MarketingStatement", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_MarketingSection", "" );
      //:END
   } 

   //:END

   //:// SPLD_HumanHazardSection 
   //:FOR EACH SourceSPLD.SPLD_HumanHazardSection 
   RESULT = SetCursorFirstEntity( SourceSPLD, "SPLD_HumanHazardSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.SPLD_HumanHazardSection 
      RESULT = CreateEntity( NewSPLD, "SPLD_HumanHazardSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "SPLD_HumanHazardSection", SourceSPLD, "SPLD_HumanHazardSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_HumanHazardSection", SourceSPLD, "SPLD_HumanHazardSection", zSET_NULL );
      //:IF SourceSPLD.S_HumanHazardSection EXISTS
      lTempInteger_2 = CheckExistenceOfEntity( SourceSPLD, "S_HumanHazardSection" );
      if ( lTempInteger_2 == 0 )
      { 
         //:INCLUDE NewSPLD.S_HumanHazardSection FROM SourceSPLD.S_HumanHazardSection 
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_HumanHazardSection", SourceSPLD, "S_HumanHazardSection", zPOS_AFTER );
      } 

      RESULT = SetCursorNextEntity( SourceSPLD, "SPLD_HumanHazardSection", "" );
      //:END
   } 

   //:END

   //:// SPLD_LLD 
   //:CREATE ENTITY NewSPLD.SPLD_LLD 
   RESULT = CreateEntity( NewSPLD, "SPLD_LLD", zPOS_AFTER );
   //:SetMatchingAttributesByName( NewSPLD, "S_HumanHazardSection", SourceSPLD, "S_HumanHazardSection", zSET_NULL )
   SetMatchingAttributesByName( NewSPLD, "S_HumanHazardSection", SourceSPLD, "S_HumanHazardSection", zSET_NULL );
   //:FOR EACH SourceSPLD.LLD_Page 
   RESULT = SetCursorFirstEntity( SourceSPLD, "LLD_Page", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.LLD_Page 
      RESULT = CreateEntity( NewSPLD, "LLD_Page", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "LLD_Page", SourceSPLD, "LLD_Page", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "LLD_Page", SourceSPLD, "LLD_Page", zSET_NULL );
      //:IF SourceSPLD.PageBackgroundColor EXISTS
      lTempInteger_3 = CheckExistenceOfEntity( SourceSPLD, "PageBackgroundColor" );
      if ( lTempInteger_3 == 0 )
      { 
         //:INCLUDE NewSPLD.PageBackgroundColor FROM SourceSPLD.PageBackgroundColor 
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "PageBackgroundColor", SourceSPLD, "PageBackgroundColor", zPOS_AFTER );
      } 

      //:END
      //:FOR EACH SourceSPLD.LLD_Panel 
      RESULT = SetCursorFirstEntity( SourceSPLD, "LLD_Panel", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSPLD.LLD_Panel 
         RESULT = CreateEntity( NewSPLD, "LLD_Panel", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSPLD, "LLD_Panel", SourceSPLD, "LLD_Panel", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "LLD_Panel", SourceSPLD, "LLD_Panel", zSET_NULL );
         //:IF SourceSPLD.PanelBackgroundColor EXISTS
         lTempInteger_4 = CheckExistenceOfEntity( SourceSPLD, "PanelBackgroundColor" );
         if ( lTempInteger_4 == 0 )
         { 
            //:INCLUDE NewSPLD.PanelBackgroundColor FROM SourceSPLD.PanelBackgroundColor 
            RESULT = IncludeSubobjectFromSubobject( NewSPLD, "PanelBackgroundColor", SourceSPLD, "PanelBackgroundColor", zPOS_AFTER );
         } 

         //:END
         //:IF SourceSPLD.PanelBorderColor EXISTS
         lTempInteger_5 = CheckExistenceOfEntity( SourceSPLD, "PanelBorderColor" );
         if ( lTempInteger_5 == 0 )
         { 
            //:INCLUDE NewSPLD.PanelBorderColor FROM SourceSPLD.PanelBorderColor 
            RESULT = IncludeSubobjectFromSubobject( NewSPLD, "PanelBorderColor", SourceSPLD, "PanelBorderColor", zPOS_AFTER );
         } 

         //:END
         //:FOR EACH SourceSPLD.LLD_Block 
         RESULT = SetCursorFirstEntity( SourceSPLD, "LLD_Block", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:// Use recursive routine to duplicate Block.
            //:DuplicateSPLD_Block( NewSPLD, SourceSPLD )
            omSPLDef_DuplicateSPLD_Block( NewSPLD, SourceSPLD );
            RESULT = SetCursorNextEntity( SourceSPLD, "LLD_Block", "" );
         } 

         RESULT = SetCursorNextEntity( SourceSPLD, "LLD_Panel", "" );
         //:END 
      } 

      RESULT = SetCursorNextEntity( SourceSPLD, "LLD_Page", "" );
      //:END 
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omSPLDef_DuplicateSPLD_Block( View     NewSPLD,
                              View     SourceSPLD )
{
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   //:DuplicateSPLD_Block( VIEW NewSPLD    BASED ON LOD mSPLDef,
   //:                  VIEW SourceSPLD BASED ON LOD mSPLDef )

   //:// This is a recursive routine to duplicate an LLD_Block / LLD_SubBlock.
   //:CREATE ENTITY NewSPLD.LLD_Block  
   RESULT = CreateEntity( NewSPLD, "LLD_Block", zPOS_AFTER );
   //:SetMatchingAttributesByName( NewSPLD, "LLD_Block", SourceSPLD, "LLD_Block", zSET_NULL ) 
   SetMatchingAttributesByName( NewSPLD, "LLD_Block", SourceSPLD, "LLD_Block", zSET_NULL );
   //:IF SourceSPLD.BlockBackgroundColor EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( SourceSPLD, "BlockBackgroundColor" );
   if ( lTempInteger_0 == 0 )
   { 
      //:INCLUDE NewSPLD.BlockBackgroundColor FROM SourceSPLD.BlockBackgroundColor 
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "BlockBackgroundColor", SourceSPLD, "BlockBackgroundColor", zPOS_AFTER );
   } 

   //:END
   //:IF SourceSPLD.BlockBorderColor EXISTS
   lTempInteger_1 = CheckExistenceOfEntity( SourceSPLD, "BlockBorderColor" );
   if ( lTempInteger_1 == 0 )
   { 
      //:INCLUDE NewSPLD.BlockBorderColor FROM SourceSPLD.BlockBorderColor 
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "BlockBorderColor", SourceSPLD, "BlockBorderColor", zPOS_AFTER );
   } 

   //:END
   //:FOR EACH SourceSPLD.LLD_SpecialSectionAttribute 
   RESULT = SetCursorFirstEntity( SourceSPLD, "LLD_SpecialSectionAttribute", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSPLD.LLD_SpecialSectionAttribute 
      RESULT = CreateEntity( NewSPLD, "LLD_SpecialSectionAttribute", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "LLD_SpecialSectionAttribute", SourceSPLD, "LLD_SpecialSectionAttribute", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "LLD_SpecialSectionAttribute", SourceSPLD, "LLD_SpecialSectionAttribute", zSET_NULL );
      //:CREATE ENTITY NewSPLD.LLD_SpecialSectionAttrBlock 
      RESULT = CreateEntity( NewSPLD, "LLD_SpecialSectionAttrBlock", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSPLD, "LLD_SpecialSectionAttrBlock", SourceSPLD, "LLD_SpecialSectionAttrBlock", zSET_NULL )  
      SetMatchingAttributesByName( NewSPLD, "LLD_SpecialSectionAttrBlock", SourceSPLD, "LLD_SpecialSectionAttrBlock", zSET_NULL );
      RESULT = SetCursorNextEntity( SourceSPLD, "LLD_SpecialSectionAttribute", "" );
   } 

   //:END
   //:FOR EACH SourceSPLD.LLD_SubBlock 
   RESULT = SetCursorFirstEntity( SourceSPLD, "LLD_SubBlock", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SetViewToSubobject( SourceSPLD, "LLD_SubBlock" )
      SetViewToSubobject( SourceSPLD, "LLD_SubBlock" );
      //:SetViewToSubobject( NewSPLD, "LLD_SubBlock" )
      SetViewToSubobject( NewSPLD, "LLD_SubBlock" );

      //:DuplicateSPLD_Block( NewSPLD, SourceSPLD )
      omSPLDef_DuplicateSPLD_Block( NewSPLD, SourceSPLD );

      //:ResetViewFromSubobject( SourceSPLD )
      ResetViewFromSubobject( SourceSPLD );
      //:ResetViewFromSubobject( NewSPLD )
      ResetViewFromSubobject( NewSPLD );
      RESULT = SetCursorNextEntity( SourceSPLD, "LLD_SubBlock", "" );
   } 

   //:END
   return( 0 );
//    
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dFullHazardStatement( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )

//:   STRING ( 256 ) szString
public int 
omSPLDef_dFullHazardStatement( View     mSPLDef,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   String   szString = null;
   //:STRING ( 256 ) szReplaceString
   String   szReplaceString = null;
   //:STRING ( 256 ) szLocation
   String   szLocation = null;
   //:STRING ( 2 )   szSeparator
   String   szSeparator = null;
   //:STRING ( 1 )   szOpenSeparator
   String   szOpenSeparator = null;
   //:STRING ( 1 )   szCloseSeparator
   String   szCloseSeparator = null;
   //:STRING ( 1 )   szEncloseFirst
   String   szEncloseFirst = null;
   //:SHORT nPosStart
   int      nPosStart = 0;
   //:SHORT nPosEnd
   int      nPosEnd = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   int      lTempInteger_13 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mSPLDef.SPLD_HumanHazardSection  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "SPLD_HumanHazardSection" );
         if ( lTempInteger_0 == 0 )
         { 

            //:szSeparator = mSPLDef.SPLD_HumanHazardSection.LocationSeparator
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szSeparator;
            if ( szSeparator == null )
               sb_szSeparator = new StringBuilder( 32 );
            else
               sb_szSeparator = new StringBuilder( szSeparator );
                         GetVariableFromAttribute( sb_szSeparator, mi_lTempInteger_1, 'S', 3, mSPLDef, "SPLD_HumanHazardSection", "LocationSeparator", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szSeparator = sb_szSeparator.toString( );}
            //:szOpenSeparator = ""
             {StringBuilder sb_szOpenSeparator;
            if ( szOpenSeparator == null )
               sb_szOpenSeparator = new StringBuilder( 32 );
            else
               sb_szOpenSeparator = new StringBuilder( szOpenSeparator );
                        ZeidonStringCopy( sb_szOpenSeparator, 1, 0, "", 1, 0, 2 );
            szOpenSeparator = sb_szOpenSeparator.toString( );}
            //:szCloseSeparator = ""
             {StringBuilder sb_szCloseSeparator;
            if ( szCloseSeparator == null )
               sb_szCloseSeparator = new StringBuilder( 32 );
            else
               sb_szCloseSeparator = new StringBuilder( szCloseSeparator );
                        ZeidonStringCopy( sb_szCloseSeparator, 1, 0, "", 1, 0, 2 );
            szCloseSeparator = sb_szCloseSeparator.toString( );}
            //:nPosStart  = zstrlen( szSeparator )
            nPosStart = zstrlen( szSeparator );
            //:IF nPosStart > 0
            if ( nPosStart > 0 )
            { 
               //:zstrncpy( szOpenSeparator, szSeparator, 1 )
                {StringBuilder sb_szOpenSeparator;
               if ( szOpenSeparator == null )
                  sb_szOpenSeparator = new StringBuilder( 32 );
               else
                  sb_szOpenSeparator = new StringBuilder( szOpenSeparator );
                              zstrncpy( sb_szOpenSeparator, szSeparator, 1 );
               szOpenSeparator = sb_szOpenSeparator.toString( );}
               //:IF nPosStart > 1
               if ( nPosStart > 1 )
               { 
                  //:zstrncpyoffset( szCloseSeparator, szSeparator, 1, 1 )
                   {StringBuilder sb_szCloseSeparator;
                  if ( szCloseSeparator == null )
                     sb_szCloseSeparator = new StringBuilder( 32 );
                  else
                     sb_szCloseSeparator = new StringBuilder( szCloseSeparator );
                                    zstrncpyoffset( sb_szCloseSeparator, szSeparator, 1, 1 );
                  szCloseSeparator = sb_szCloseSeparator.toString( );}
               } 

               //:END
            } 

            //:END

            //:szEncloseFirst = mSPLDef.SPLD_HumanHazardSection.EncloseFirstLocation
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szEncloseFirst;
            if ( szEncloseFirst == null )
               sb_szEncloseFirst = new StringBuilder( 32 );
            else
               sb_szEncloseFirst = new StringBuilder( szEncloseFirst );
                         GetVariableFromAttribute( sb_szEncloseFirst, mi_lTempInteger_2, 'S', 2, mSPLDef, "SPLD_HumanHazardSection", "EncloseFirstLocation", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szEncloseFirst = sb_szEncloseFirst.toString( );}
            //:szString = mSPLDef.SPLD_HumanHazardSection.PrecautionaryStatement
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_3, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "PrecautionaryStatement", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szString = sb_szString.toString( );}
            //:nPosStart  = zSearchSubString( szString, "{{Precautionary Panel Location}}", "f", 0 )
            nPosStart = zSearchSubString( szString, "{{Precautionary Panel Location}}", "f", 0 );
            //:IF nPosStart >= 0
            if ( nPosStart >= 0 )
            { 

               //:nPosEnd = nPosStart + 32 // length of "{{Precautionary Panel Location}}"
               nPosEnd = nPosStart + 32;
               //:szReplaceString = ""
                {StringBuilder sb_szReplaceString;
               if ( szReplaceString == null )
                  sb_szReplaceString = new StringBuilder( 32 );
               else
                  sb_szReplaceString = new StringBuilder( szReplaceString );
                              ZeidonStringCopy( sb_szReplaceString, 1, 0, "", 1, 0, 257 );
               szReplaceString = sb_szReplaceString.toString( );}
               //:szLocation = mSPLDef.SPLD_HumanHazardSection.PanelLoc1
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_4, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc1", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:IF szEncloseFirst = "Y" AND szCloseSeparator != ""
                  if ( ZeidonStringCompare( szEncloseFirst, 1, 0, "Y", 1, 0, 2 ) == 0 && ZeidonStringCompare( szCloseSeparator, 1, 0, "", 1, 0, 2 ) != 0 )
                  { 
                     //:szReplaceString = szReplaceString + szOpenSeparator + szLocation + szCloseSeparator
                      {StringBuilder sb_szReplaceString;
                     if ( szReplaceString == null )
                        sb_szReplaceString = new StringBuilder( 32 );
                     else
                        sb_szReplaceString = new StringBuilder( szReplaceString );
                                          ZeidonStringConcat( sb_szReplaceString, 1, 0, szOpenSeparator, 1, 0, 257 );
                     szReplaceString = sb_szReplaceString.toString( );}
                      {StringBuilder sb_szReplaceString;
                     if ( szReplaceString == null )
                        sb_szReplaceString = new StringBuilder( 32 );
                     else
                        sb_szReplaceString = new StringBuilder( szReplaceString );
                                          ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                     szReplaceString = sb_szReplaceString.toString( );}
                      {StringBuilder sb_szReplaceString;
                     if ( szReplaceString == null )
                        sb_szReplaceString = new StringBuilder( 32 );
                     else
                        sb_szReplaceString = new StringBuilder( szReplaceString );
                                          ZeidonStringConcat( sb_szReplaceString, 1, 0, szCloseSeparator, 1, 0, 257 );
                     szReplaceString = sb_szReplaceString.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:szReplaceString = szReplaceString + szLocation
                      {StringBuilder sb_szReplaceString;
                     if ( szReplaceString == null )
                        sb_szReplaceString = new StringBuilder( 32 );
                     else
                        sb_szReplaceString = new StringBuilder( szReplaceString );
                                          ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                     szReplaceString = sb_szReplaceString.toString( );}
                  } 

                  //:END
               } 

               //:END

               //:szLocation = mSPLDef.SPLD_HumanHazardSection.PanelLoc2
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_5, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc2", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + szOpenSeparator + szLocation + szCloseSeparator
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szOpenSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szCloseSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:szLocation = mSPLDef.SPLD_HumanHazardSection.PanelLoc3
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_6, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc3", "", 0 );
               lTempInteger_6 = mi_lTempInteger_6.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + szOpenSeparator + szLocation + szCloseSeparator
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szOpenSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szCloseSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:szLocation = mSPLDef.SPLD_HumanHazardSection.PanelLoc4
               {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_7, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc4", "", 0 );
               lTempInteger_7 = mi_lTempInteger_7.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + szOpenSeparator + szLocation + szCloseSeparator
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szOpenSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szCloseSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:szLocation = mSPLDef.SPLD_HumanHazardSection.PanelLoc5
               {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_8, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc5", "", 0 );
               lTempInteger_8 = mi_lTempInteger_8.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + szOpenSeparator + szLocation + szCloseSeparator
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szOpenSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szCloseSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:zReplaceSubString( szString, nPosStart, nPosEnd, szReplaceString )
               {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                               zReplaceSubString( sb_szString, nPosStart, nPosEnd, szReplaceString );
               szString = sb_szString.toString( );}
            } 

            //:END

            //:nPosStart  = zSearchSubString( szString, "{{Precautionary Label Location}}", "f", 0 )
            nPosStart = zSearchSubString( szString, "{{Precautionary Label Location}}", "f", 0 );
            //:IF nPosStart >= 0
            if ( nPosStart >= 0 )
            { 

               //:nPosEnd = nPosStart + 32 // length of "{{Precautionary Label Location}}"
               nPosEnd = nPosStart + 32;
               //:szReplaceString = ""
                {StringBuilder sb_szReplaceString;
               if ( szReplaceString == null )
                  sb_szReplaceString = new StringBuilder( 32 );
               else
                  sb_szReplaceString = new StringBuilder( szReplaceString );
                              ZeidonStringCopy( sb_szReplaceString, 1, 0, "", 1, 0, 257 );
               szReplaceString = sb_szReplaceString.toString( );}
               //:szLocation = mSPLDef.SPLD_HumanHazardSection.LabelLoc1
               {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_9, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "LabelLoc1", "", 0 );
               lTempInteger_9 = mi_lTempInteger_9.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:IF szCloseSeparator != ""
                  if ( ZeidonStringCompare( szCloseSeparator, 1, 0, "", 1, 0, 2 ) != 0 )
                  { 
                     //:szReplaceString = szReplaceString + szOpenSeparator + szLocation + szCloseSeparator
                      {StringBuilder sb_szReplaceString;
                     if ( szReplaceString == null )
                        sb_szReplaceString = new StringBuilder( 32 );
                     else
                        sb_szReplaceString = new StringBuilder( szReplaceString );
                                          ZeidonStringConcat( sb_szReplaceString, 1, 0, szOpenSeparator, 1, 0, 257 );
                     szReplaceString = sb_szReplaceString.toString( );}
                      {StringBuilder sb_szReplaceString;
                     if ( szReplaceString == null )
                        sb_szReplaceString = new StringBuilder( 32 );
                     else
                        sb_szReplaceString = new StringBuilder( szReplaceString );
                                          ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                     szReplaceString = sb_szReplaceString.toString( );}
                      {StringBuilder sb_szReplaceString;
                     if ( szReplaceString == null )
                        sb_szReplaceString = new StringBuilder( 32 );
                     else
                        sb_szReplaceString = new StringBuilder( szReplaceString );
                                          ZeidonStringConcat( sb_szReplaceString, 1, 0, szCloseSeparator, 1, 0, 257 );
                     szReplaceString = sb_szReplaceString.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:szReplaceString = szReplaceString + szLocation
                      {StringBuilder sb_szReplaceString;
                     if ( szReplaceString == null )
                        sb_szReplaceString = new StringBuilder( 32 );
                     else
                        sb_szReplaceString = new StringBuilder( szReplaceString );
                                          ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                     szReplaceString = sb_szReplaceString.toString( );}
                  } 

                  //:END
               } 

               //:END

               //:szLocation = mSPLDef.SPLD_HumanHazardSection.LabelLoc2
               {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_10, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "LabelLoc2", "", 0 );
               lTempInteger_10 = mi_lTempInteger_10.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + szOpenSeparator + szLocation + szCloseSeparator
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szOpenSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szCloseSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:szLocation = mSPLDef.SPLD_HumanHazardSection.LabelLoc3
               {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_11, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "LabelLoc3", "", 0 );
               lTempInteger_11 = mi_lTempInteger_11.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + szOpenSeparator + szLocation + szCloseSeparator
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szOpenSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szCloseSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:szLocation = mSPLDef.SPLD_HumanHazardSection.LabelLoc4
               {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_12, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "LabelLoc4", "", 0 );
               lTempInteger_12 = mi_lTempInteger_12.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + szOpenSeparator + szLocation + szCloseSeparator
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szOpenSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szCloseSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:szLocation = mSPLDef.SPLD_HumanHazardSection.LabelLoc5
               {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_13, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "LabelLoc5", "", 0 );
               lTempInteger_13 = mi_lTempInteger_13.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + szOpenSeparator + szLocation + szCloseSeparator
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szOpenSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szCloseSeparator, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:zReplaceSubString( szString, nPosStart, nPosEnd, szReplaceString )
               {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                               zReplaceSubString( sb_szString, nPosStart, nPosEnd, szReplaceString );
               szString = sb_szString.toString( );}
            } 

            //:END

            //:ELSE
         } 
         else
         { 
            //:szString = ""
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringCopy( sb_szString, 1, 0, "", 1, 0, 257 );
            szString = sb_szString.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szString );
         //:RETURN 0
         if(8==8)return( 0 );

         //:/* end zDERIVED_GET */
         //:OF   zDERIVED_SET:
         case zDERIVED_SET :
            break ;
      } 


      //:/* end zDERIVED_SET */
      //:END  /* case */
      return( 0 );
   } 


   //:DERIVED ATTRIBUTE OPERATION
   //:dSelectedHazardStmt( VIEW mSPLDef BASED ON LOD mSPLDef,
   //:                  STRING ( 32 ) InternalEntityStructure,
   //:                  STRING ( 32 ) InternalAttribStructure,
   //:                  SHORT GetOrSetFlag )

   //:STRING ( 256 ) szString
public int 
omSPLDef_dSelectedHazardStmt( View     mSPLDef,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szString = null;
   //:STRING ( 256 ) szReplaceString
   String   szReplaceString = null;
   //:STRING ( 256 ) szLocation
   String   szLocation = null;
   //:INTEGER lLocation
   int      lLocation = 0;
   //:SHORT nPosStart
   int      nPosStart = 0;
   //:SHORT nPosEnd
   int      nPosEnd = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:szString = ""
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringCopy( sb_szString, 1, 0, "", 1, 0, 257 );
         szString = sb_szString.toString( );}

         //:// Build Precautionary Statement by inserting correct Location within general statement, so that a
         //:// statement such as, "See {{Precautionary Panel Position}}" for Precautionary Statements and First Aid." becomes
         //:// "See side panel for Precautionary Statements and First Aid."
         //:IF mSPLDef.SPLD_HumanHazardSection  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "SPLD_HumanHazardSection" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSPLDef.SPLD_HumanHazardSection.PrecautionaryStatement
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "PrecautionaryStatement", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
            //:lLocation = 1
            lLocation = 1;
            //:nPosStart  = zSearchSubString( szString, "{{Precautionary Panel Position}}", "f", 0 )
            nPosStart = zSearchSubString( szString, "{{Precautionary Panel Position}}", "f", 0 );
            //:IF nPosStart >= 0
            if ( nPosStart >= 0 )
            { 
               //:nPosEnd = nPosStart + 32 // length of "{{Precautionary Panel Position}}"
               nPosEnd = nPosStart + 32;
               //:szLocation = ""
                {StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                              ZeidonStringCopy( sb_szLocation, 1, 0, "", 1, 0, 257 );
               szLocation = sb_szLocation.toString( );}
               //:IF lLocation = 1
               if ( lLocation == 1 )
               { 
                  //:szLocation = mSPLDef.SPLD_HumanHazardSection.PanelLoc1
                  {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                  StringBuilder sb_szLocation;
                  if ( szLocation == null )
                     sb_szLocation = new StringBuilder( 32 );
                  else
                     sb_szLocation = new StringBuilder( szLocation );
                                     GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_2, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc1", "", 0 );
                  lTempInteger_2 = mi_lTempInteger_2.intValue( );
                  szLocation = sb_szLocation.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:IF lLocation = 2
                  if ( lLocation == 2 )
                  { 
                     //:szLocation = mSPLDef.SPLD_HumanHazardSection.PanelLoc2
                     {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                     StringBuilder sb_szLocation;
                     if ( szLocation == null )
                        sb_szLocation = new StringBuilder( 32 );
                     else
                        sb_szLocation = new StringBuilder( szLocation );
                                           GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_3, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc2", "", 0 );
                     lTempInteger_3 = mi_lTempInteger_3.intValue( );
                     szLocation = sb_szLocation.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF lLocation = 3
                     if ( lLocation == 3 )
                     { 
                        //:szLocation = mSPLDef.SPLD_HumanHazardSection.PanelLoc3
                        {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                        StringBuilder sb_szLocation;
                        if ( szLocation == null )
                           sb_szLocation = new StringBuilder( 32 );
                        else
                           sb_szLocation = new StringBuilder( szLocation );
                                                 GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_4, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc3", "", 0 );
                        lTempInteger_4 = mi_lTempInteger_4.intValue( );
                        szLocation = sb_szLocation.toString( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:IF lLocation = 4
                        if ( lLocation == 4 )
                        { 
                           //:szLocation = mSPLDef.SPLD_HumanHazardSection.PanelLoc4
                           {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                           StringBuilder sb_szLocation;
                           if ( szLocation == null )
                              sb_szLocation = new StringBuilder( 32 );
                           else
                              sb_szLocation = new StringBuilder( szLocation );
                                                       GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_5, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc4", "", 0 );
                           lTempInteger_5 = mi_lTempInteger_5.intValue( );
                           szLocation = sb_szLocation.toString( );}
                           //:ELSE
                        } 
                        else
                        { 
                           //:IF lLocation = 5
                           if ( lLocation == 5 )
                           { 
                              //:szLocation = mSPLDef.SPLD_HumanHazardSection.PanelLoc5
                              {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                              StringBuilder sb_szLocation;
                              if ( szLocation == null )
                                 sb_szLocation = new StringBuilder( 32 );
                              else
                                 sb_szLocation = new StringBuilder( szLocation );
                                                             GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_6, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "PanelLoc5", "", 0 );
                              lTempInteger_6 = mi_lTempInteger_6.intValue( );
                              szLocation = sb_szLocation.toString( );}
                           } 

                           //:END
                        } 

                        //:END
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
               //:zReplaceSubString( szString, nPosStart, nPosEnd, szLocation )
               {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                               zReplaceSubString( sb_szString, nPosStart, nPosEnd, szLocation );
               szString = sb_szString.toString( );}
            } 

            //:END

            //:lLocation = 1
            lLocation = 1;
            //:nPosStart  = zSearchSubString( szString, "{{Precautionary Label Position}}", "f", 0 )
            nPosStart = zSearchSubString( szString, "{{Precautionary Label Position}}", "f", 0 );
            //:IF nPosStart >= 0
            if ( nPosStart >= 0 )
            { 
               //:nPosEnd = nPosStart + 32 // length of "{{Precautionary Label Position}}"
               nPosEnd = nPosStart + 32;
               //:szLocation = ""
                {StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                              ZeidonStringCopy( sb_szLocation, 1, 0, "", 1, 0, 257 );
               szLocation = sb_szLocation.toString( );}
               //:IF lLocation = 1
               if ( lLocation == 1 )
               { 
                  //:szLocation = mSPLDef.SPLD_HumanHazardSection.LabelLoc1
                  {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                  StringBuilder sb_szLocation;
                  if ( szLocation == null )
                     sb_szLocation = new StringBuilder( 32 );
                  else
                     sb_szLocation = new StringBuilder( szLocation );
                                     GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_7, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "LabelLoc1", "", 0 );
                  lTempInteger_7 = mi_lTempInteger_7.intValue( );
                  szLocation = sb_szLocation.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:IF lLocation = 2
                  if ( lLocation == 2 )
                  { 
                     //:szLocation = mSPLDef.SPLD_HumanHazardSection.LabelLoc2
                     {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                     StringBuilder sb_szLocation;
                     if ( szLocation == null )
                        sb_szLocation = new StringBuilder( 32 );
                     else
                        sb_szLocation = new StringBuilder( szLocation );
                                           GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_8, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "LabelLoc2", "", 0 );
                     lTempInteger_8 = mi_lTempInteger_8.intValue( );
                     szLocation = sb_szLocation.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF lLocation = 3
                     if ( lLocation == 3 )
                     { 
                        //:szLocation = mSPLDef.SPLD_HumanHazardSection.LabelLoc3
                        {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
                        StringBuilder sb_szLocation;
                        if ( szLocation == null )
                           sb_szLocation = new StringBuilder( 32 );
                        else
                           sb_szLocation = new StringBuilder( szLocation );
                                                 GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_9, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "LabelLoc3", "", 0 );
                        lTempInteger_9 = mi_lTempInteger_9.intValue( );
                        szLocation = sb_szLocation.toString( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:IF lLocation = 4
                        if ( lLocation == 4 )
                        { 
                           //:szLocation = mSPLDef.SPLD_HumanHazardSection.LabelLoc4
                           {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
                           StringBuilder sb_szLocation;
                           if ( szLocation == null )
                              sb_szLocation = new StringBuilder( 32 );
                           else
                              sb_szLocation = new StringBuilder( szLocation );
                                                       GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_10, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "LabelLoc4", "", 0 );
                           lTempInteger_10 = mi_lTempInteger_10.intValue( );
                           szLocation = sb_szLocation.toString( );}
                           //:ELSE
                        } 
                        else
                        { 
                           //:IF lLocation = 5
                           if ( lLocation == 5 )
                           { 
                              //:szLocation = mSPLDef.SPLD_HumanHazardSection.LabelLoc5
                              {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
                              StringBuilder sb_szLocation;
                              if ( szLocation == null )
                                 sb_szLocation = new StringBuilder( 32 );
                              else
                                 sb_szLocation = new StringBuilder( szLocation );
                                                             GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_11, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "LabelLoc5", "", 0 );
                              lTempInteger_11 = mi_lTempInteger_11.intValue( );
                              szLocation = sb_szLocation.toString( );}
                           } 

                           //:END
                        } 

                        //:END
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
               //:zReplaceSubString( szString, nPosStart, nPosEnd, szLocation )
               {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                               zReplaceSubString( sb_szString, nPosStart, nPosEnd, szLocation );
               szString = sb_szString.toString( );}
            } 

            //:END
         } 

         //:END
         //:TraceLineS( "Hazard Statement: ", szString )
         TraceLineS( "Hazard Statement: ", szString );
         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szString );
         break ;

      //:/* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:     /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:FormatBlockContainer( VIEW mSPLDefPDF BASED ON LOD mSPLDef,
//:                      VIEW mSPLDef      BASED ON LOD mSPLDef,
//:                      INTEGER lFile,
//:                      STRING ( 50 )    szLeadingBlanks,
//:                      STRING ( 32000 ) szWriteBuffer,
//:                      STRING ( 10 )    szTopPosition )

//:   STRING ( 32 )  szSectionType
public int 
omSPLDef_FormatBlockContainer( View     mSPLDefPDF,
                               View     mSPLDef,
                               int      lFile,
                               String   szLeadingBlanks,
                               String   szWriteBuffer,
                               String   szTopPosition )
{
   String   szSectionType = null;
   //:STRING ( 10 )  szTop
   String   szTop = null;
   //:STRING ( 10 )  szHeight
   String   szHeight = null;
   //:STRING ( 10 )  szWidth
   String   szWidth = null;
   //:STRING ( 10 )  szLeft
   String   szLeft = null;
   //:STRING ( 10 )  szBorderStyle
   String   szBorderStyle = null;
   //:STRING ( 10 )  szColor
   String   szColor = null;
   //:STRING ( 10 )  szFontFamily
   String   szFontFamily = null;
   //:STRING ( 10 )  szFontSize
   String   szFontSize = null;
   //:STRING ( 10 )  szDefaultFontSize
   String   szDefaultFontSize = null;
   //:STRING ( 10 )  szFontWeight
   String   szFontWeight = null;
   //:STRING ( 10 )  szMarginTop
   String   szMarginTop = null;
   //:STRING ( 10 )  szMarginBottom
   String   szMarginBottom = null;
   //:STRING ( 10 )  szMarginLeft
   String   szMarginLeft = null;
   //:STRING ( 10 )  szMarginRight
   String   szMarginRight = null;
   //:STRING ( 10 )  szTextAlign
   String   szTextAlign = null;
   //:STRING ( 256 ) szImg
   String   szImg = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      RESULT = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   int      lTempInteger_13 = 0;
   int      lTempInteger_14 = 0;
   int      lTempInteger_15 = 0;


   //:// Process a Block and its subcomponents, which builds the XSL statement for the Container.
   //:// Note that mSPLDefPDF is addressing ONLY the LLD subobject and that this subobject could be in a temporary
   //:// object where that LLD subobject is the only thing valid in that object.

   //:// Block Container Start, which is either passed in or specified in Block.
   //:// 1. It is passed in as last paramter.
   //:// 2. It is specified in Computed Top attribute.
   //:IF szTopPosition != ""
   if ( ZeidonStringCompare( szTopPosition, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szTop = szTopPosition
       {StringBuilder sb_szTop;
      if ( szTop == null )
         sb_szTop = new StringBuilder( 32 );
      else
         sb_szTop = new StringBuilder( szTop );
            ZeidonStringCopy( sb_szTop, 1, 0, szTopPosition, 1, 0, 11 );
      szTop = sb_szTop.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szTop = mSPLDefPDF.LLD_Block.wComputedTopPosition 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szTop;
      if ( szTop == null )
         sb_szTop = new StringBuilder( 32 );
      else
         sb_szTop = new StringBuilder( szTop );
             GetVariableFromAttribute( sb_szTop, mi_lTempInteger_0, 'S', 11, mSPLDefPDF, "LLD_Block", "wComputedTopPosition", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szTop = sb_szTop.toString( );}
   } 

   //:END
   //:szWriteBuffer = szLeadingBlanks + "<fo:block-container position=^absolute^"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<fo:block-container position=^absolute^", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}

   //:// Default font size will be set to 8pt for now.
   //:szDefaultFontSize = "8pt"
    {StringBuilder sb_szDefaultFontSize;
   if ( szDefaultFontSize == null )
      sb_szDefaultFontSize = new StringBuilder( 32 );
   else
      sb_szDefaultFontSize = new StringBuilder( szDefaultFontSize );
      ZeidonStringCopy( sb_szDefaultFontSize, 1, 0, "8pt", 1, 0, 11 );
   szDefaultFontSize = sb_szDefaultFontSize.toString( );}

   //://szTop = mSPLDefPDF.LLD_Block.Top 
   //:IF szTop != ""
   if ( ZeidonStringCompare( szTop, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " top=^" + szTop + "in^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " top=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTop, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szHeight = mSPLDefPDF.LLD_Block.Height 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szHeight;
   if ( szHeight == null )
      sb_szHeight = new StringBuilder( 32 );
   else
      sb_szHeight = new StringBuilder( szHeight );
       GetVariableFromAttribute( sb_szHeight, mi_lTempInteger_1, 'S', 11, mSPLDefPDF, "LLD_Block", "Height", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szHeight = sb_szHeight.toString( );}
   //:IF szHeight != ""
   if ( ZeidonStringCompare( szHeight, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " height=^" + szHeight + "in^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " height=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szHeight, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szWidth = mSPLDefPDF.LLD_Block.Width 
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szWidth;
   if ( szWidth == null )
      sb_szWidth = new StringBuilder( 32 );
   else
      sb_szWidth = new StringBuilder( szWidth );
       GetVariableFromAttribute( sb_szWidth, mi_lTempInteger_2, 'S', 11, mSPLDefPDF, "LLD_Block", "Width", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szWidth = sb_szWidth.toString( );}
   //:IF szWidth != ""
   if ( ZeidonStringCompare( szWidth, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " width=^" + szWidth + "in^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " width=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szWidth, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szLeft = mSPLDefPDF.LLD_Block.Left 
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szLeft;
   if ( szLeft == null )
      sb_szLeft = new StringBuilder( 32 );
   else
      sb_szLeft = new StringBuilder( szLeft );
       GetVariableFromAttribute( sb_szLeft, mi_lTempInteger_3, 'S', 11, mSPLDefPDF, "LLD_Block", "Left", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szLeft = sb_szLeft.toString( );}
   //:IF szLeft != ""
   if ( ZeidonStringCompare( szLeft, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " left=^" + szLeft + "in^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " left=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szLeft, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:// Other Block paramters come from the "Block" LLD_SpecialSectionAttrBlock entry, if there is one.
   //:SET CURSOR FIRST mSPLDefPDF.LLD_SpecialSectionAttribute WHERE mSPLDefPDF.LLD_SpecialSectionAttribute.Name = "Block"
   RESULT = SetCursorFirstEntityByString( mSPLDefPDF, "LLD_SpecialSectionAttribute", "Name", "Block", "" );
   //:IF RESULT >= zCURSOR_SET 
   if ( RESULT >= zCURSOR_SET )
   { 
      //:szBorderStyle = mSPLDefPDF.LLD_SpecialSectionAttrBlock.BorderStyle 
      {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
      StringBuilder sb_szBorderStyle;
      if ( szBorderStyle == null )
         sb_szBorderStyle = new StringBuilder( 32 );
      else
         sb_szBorderStyle = new StringBuilder( szBorderStyle );
             GetVariableFromAttribute( sb_szBorderStyle, mi_lTempInteger_4, 'S', 11, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "BorderStyle", "", 0 );
      lTempInteger_4 = mi_lTempInteger_4.intValue( );
      szBorderStyle = sb_szBorderStyle.toString( );}
      //:IF szBorderStyle != ""
      if ( ZeidonStringCompare( szBorderStyle, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " border-style=^" + szBorderStyle + "^" 
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " border-style=^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szBorderStyle, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szWidth = mSPLDefPDF.LLD_SpecialSectionAttrBlock.BorderWidth
      {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
      StringBuilder sb_szWidth;
      if ( szWidth == null )
         sb_szWidth = new StringBuilder( 32 );
      else
         sb_szWidth = new StringBuilder( szWidth );
             GetVariableFromAttribute( sb_szWidth, mi_lTempInteger_5, 'S', 11, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "BorderWidth", "", 0 );
      lTempInteger_5 = mi_lTempInteger_5.intValue( );
      szWidth = sb_szWidth.toString( );}
      //:IF szWidth != ""
      if ( ZeidonStringCompare( szWidth, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " border-width=^" + szWidth + "^" 
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " border-width=^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szWidth, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szColor = mSPLDefPDF.LLD_SpecialSectionAttrBlock.BorderColor 
      {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
      StringBuilder sb_szColor;
      if ( szColor == null )
         sb_szColor = new StringBuilder( 32 );
      else
         sb_szColor = new StringBuilder( szColor );
             GetVariableFromAttribute( sb_szColor, mi_lTempInteger_6, 'S', 11, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "BorderColor", "", 0 );
      lTempInteger_6 = mi_lTempInteger_6.intValue( );
      szColor = sb_szColor.toString( );}
      //:IF szColor != ""
      if ( ZeidonStringCompare( szColor, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " border-color=^" + szColor + "^" 
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " border-color=^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szColor, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szColor = mSPLDefPDF.LLD_SpecialSectionAttrBlock.TextColor 
      {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
      StringBuilder sb_szColor;
      if ( szColor == null )
         sb_szColor = new StringBuilder( 32 );
      else
         sb_szColor = new StringBuilder( szColor );
             GetVariableFromAttribute( sb_szColor, mi_lTempInteger_7, 'S', 11, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "TextColor", "", 0 );
      lTempInteger_7 = mi_lTempInteger_7.intValue( );
      szColor = sb_szColor.toString( );}
      //:IF szColor != ""
      if ( ZeidonStringCompare( szColor, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " color=^" + szColor + "^" 
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " color=^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szColor, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szFontFamily = mSPLDefPDF.LLD_SpecialSectionAttrBlock.FontFamily 
      {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
      StringBuilder sb_szFontFamily;
      if ( szFontFamily == null )
         sb_szFontFamily = new StringBuilder( 32 );
      else
         sb_szFontFamily = new StringBuilder( szFontFamily );
             GetVariableFromAttribute( sb_szFontFamily, mi_lTempInteger_8, 'S', 11, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "FontFamily", "", 0 );
      lTempInteger_8 = mi_lTempInteger_8.intValue( );
      szFontFamily = sb_szFontFamily.toString( );}
      //:IF szFontFamily != ""
      if ( ZeidonStringCompare( szFontFamily, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " font-family=^" + szFontFamily + "^" 
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " font-family=^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szFontFamily, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:IF mSPLDefPDF.LLD_SpecialSectionAttrBlock.FontSize = ""
      if ( CompareAttributeToString( mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "FontSize", "" ) == 0 )
      { 
         //:szFontSize = szDefaultFontSize
          {StringBuilder sb_szFontSize;
         if ( szFontSize == null )
            sb_szFontSize = new StringBuilder( 32 );
         else
            sb_szFontSize = new StringBuilder( szFontSize );
                  ZeidonStringCopy( sb_szFontSize, 1, 0, szDefaultFontSize, 1, 0, 11 );
         szFontSize = sb_szFontSize.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szFontSize = mSPLDefPDF.LLD_SpecialSectionAttrBlock.FontSize 
         {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
         StringBuilder sb_szFontSize;
         if ( szFontSize == null )
            sb_szFontSize = new StringBuilder( 32 );
         else
            sb_szFontSize = new StringBuilder( szFontSize );
                   GetVariableFromAttribute( sb_szFontSize, mi_lTempInteger_9, 'S', 11, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "FontSize", "", 0 );
         lTempInteger_9 = mi_lTempInteger_9.intValue( );
         szFontSize = sb_szFontSize.toString( );}
      } 

      //:END
      //:IF szFontSize != ""
      if ( ZeidonStringCompare( szFontSize, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " font-size=^" + szFontSize + "^" 
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " font-size=^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szFontSize, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END
      //:szFontWeight = mSPLDefPDF.LLD_SpecialSectionAttrBlock.FontWeight 
      {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
      StringBuilder sb_szFontWeight;
      if ( szFontWeight == null )
         sb_szFontWeight = new StringBuilder( 32 );
      else
         sb_szFontWeight = new StringBuilder( szFontWeight );
             GetVariableFromAttribute( sb_szFontWeight, mi_lTempInteger_10, 'S', 11, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "FontWeight", "", 0 );
      lTempInteger_10 = mi_lTempInteger_10.intValue( );
      szFontWeight = sb_szFontWeight.toString( );}
      //:IF szFontWeight != ""
      if ( ZeidonStringCompare( szFontWeight, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " font-weight=^" + szFontWeight + "^" 
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " font-weight=^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szFontWeight, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szMarginTop = mSPLDefPDF.LLD_SpecialSectionAttrBlock.MarginTop 
      {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
      StringBuilder sb_szMarginTop;
      if ( szMarginTop == null )
         sb_szMarginTop = new StringBuilder( 32 );
      else
         sb_szMarginTop = new StringBuilder( szMarginTop );
             GetVariableFromAttribute( sb_szMarginTop, mi_lTempInteger_11, 'S', 11, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "MarginTop", "", 0 );
      lTempInteger_11 = mi_lTempInteger_11.intValue( );
      szMarginTop = sb_szMarginTop.toString( );}
      //:IF szMarginTop != ""
      if ( ZeidonStringCompare( szMarginTop, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " margin-top=^" + szMarginTop + "in^" 
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " margin-top=^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szMarginTop, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szMarginBottom = mSPLDefPDF.LLD_SpecialSectionAttrBlock.MarginBottom 
      {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
      StringBuilder sb_szMarginBottom;
      if ( szMarginBottom == null )
         sb_szMarginBottom = new StringBuilder( 32 );
      else
         sb_szMarginBottom = new StringBuilder( szMarginBottom );
             GetVariableFromAttribute( sb_szMarginBottom, mi_lTempInteger_12, 'S', 11, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "MarginBottom", "", 0 );
      lTempInteger_12 = mi_lTempInteger_12.intValue( );
      szMarginBottom = sb_szMarginBottom.toString( );}
      //:IF szMarginBottom != ""
      if ( ZeidonStringCompare( szMarginBottom, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " margin-bottom=^" + szMarginBottom + "in^" 
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " margin-bottom=^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szMarginBottom, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szMarginLeft = mSPLDefPDF.LLD_SpecialSectionAttrBlock.MarginLeft 
      {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
      StringBuilder sb_szMarginLeft;
      if ( szMarginLeft == null )
         sb_szMarginLeft = new StringBuilder( 32 );
      else
         sb_szMarginLeft = new StringBuilder( szMarginLeft );
             GetVariableFromAttribute( sb_szMarginLeft, mi_lTempInteger_13, 'S', 11, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "MarginLeft", "", 0 );
      lTempInteger_13 = mi_lTempInteger_13.intValue( );
      szMarginLeft = sb_szMarginLeft.toString( );}
      //:IF szMarginLeft != ""
      if ( ZeidonStringCompare( szMarginLeft, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " margin-left=^" + szMarginLeft + "in^" 
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " margin-left=^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szMarginLeft, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szMarginRight = mSPLDefPDF.LLD_SpecialSectionAttrBlock.MarginRight 
      {MutableInt mi_lTempInteger_14 = new MutableInt( lTempInteger_14 );
      StringBuilder sb_szMarginRight;
      if ( szMarginRight == null )
         sb_szMarginRight = new StringBuilder( 32 );
      else
         sb_szMarginRight = new StringBuilder( szMarginRight );
             GetVariableFromAttribute( sb_szMarginRight, mi_lTempInteger_14, 'S', 11, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "MarginRight", "", 0 );
      lTempInteger_14 = mi_lTempInteger_14.intValue( );
      szMarginRight = sb_szMarginRight.toString( );}
      //:IF szMarginRight != ""
      if ( ZeidonStringCompare( szMarginRight, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " margin-right=^" + szMarginRight + "in^" 
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " margin-right=^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szMarginRight, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szTextAlign = mSPLDefPDF.LLD_SpecialSectionAttrBlock.TextAlign 
      {MutableInt mi_lTempInteger_15 = new MutableInt( lTempInteger_15 );
      StringBuilder sb_szTextAlign;
      if ( szTextAlign == null )
         sb_szTextAlign = new StringBuilder( 32 );
      else
         sb_szTextAlign = new StringBuilder( szTextAlign );
             GetVariableFromAttribute( sb_szTextAlign, mi_lTempInteger_15, 'S', 11, mSPLDefPDF, "LLD_SpecialSectionAttrBlock", "TextAlign", "", 0 );
      lTempInteger_15 = mi_lTempInteger_15.intValue( );
      szTextAlign = sb_szTextAlign.toString( );}
      //:IF szTextAlign != ""
      if ( ZeidonStringCompare( szTextAlign, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " text-align=^" + szTextAlign + "^" 
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " text-align=^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTextAlign, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END
   } 

   //:END

   //:// Add dotted border if requested, unless regular border is specified.
   //:IF szBorderStyle = "" AND mSPLDef.SubregPhysicalLabelDef.wFormatWithDottedBorders = "Y"
   if ( ZeidonStringCompare( szBorderStyle, 1, 0, "", 1, 0, 11 ) == 0 && CompareAttributeToString( mSPLDef, "SubregPhysicalLabelDef", "wFormatWithDottedBorders", "Y" ) == 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " border=^1.0pt dotted red^"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " border=^1.0pt dotted red^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:// Add default font size of 8pt, if font wasn't specified above.
   //:IF szFontSize = ""
   if ( ZeidonStringCompare( szFontSize, 1, 0, "", 1, 0, 11 ) == 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " font-size=^" + szDefaultFontSize + "^"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " font-size=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szDefaultFontSize, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:// Close the entry.
   //:szWriteBuffer = szWriteBuffer + ">"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, ">", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}

   //:// Write out the combined Block value.
   //:WL_QC( mSPLDefPDF, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDefPDF );
    m_ZGlobal1_Operation.WL_QC( mSPLDefPDF, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:FormatBlock( VIEW mSPLDef BASED ON LOD mSPLDef,
//:             INTEGER lFile,
//:             STRING ( 50 )    szLeadingBlanks,
//:             STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 32 )  szSectionType
public int 
omSPLDef_FormatBlock( View     mSPLDef,
                      int      lFile,
                      String   szLeadingBlanks,
                      String   szWriteBuffer )
{
   String   szSectionType = null;
   //:STRING ( 10 )  szTop
   String   szTop = null;
   //:STRING ( 10 )  szHeight
   String   szHeight = null;
   //:STRING ( 10 )  szWidth
   String   szWidth = null;
   //:STRING ( 10 )  szLeft
   String   szLeft = null;
   //:STRING ( 10 )  szBorderStyle
   String   szBorderStyle = null;
   //:STRING ( 10 )  szColor
   String   szColor = null;
   //:STRING ( 10 )  szFontFamily
   String   szFontFamily = null;
   //:STRING ( 10 )  szFontSize
   String   szFontSize = null;
   //:STRING ( 10 )  szFontWeight
   String   szFontWeight = null;
   //:STRING ( 10 )  szMarginTop
   String   szMarginTop = null;
   //:STRING ( 10 )  szMarginBottom
   String   szMarginBottom = null;
   //:STRING ( 10 )  szMarginLeft
   String   szMarginLeft = null;
   //:STRING ( 10 )  szMarginRight
   String   szMarginRight = null;
   //:STRING ( 10 )  szTextAlign
   String   szTextAlign = null;
   //:STRING ( 256 ) szImg
   String   szImg = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   int      lTempInteger_13 = 0;
   int      lTempInteger_14 = 0;

   //:// Process a Block and its subcomponents.
   //:// Block Container Start
   //:szWriteBuffer = szLeadingBlanks + "<fo:block"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szLeadingBlanks, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<fo:block", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}

   //:szTop = mSPLDef.LLD_Block.Top 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szTop;
   if ( szTop == null )
      sb_szTop = new StringBuilder( 32 );
   else
      sb_szTop = new StringBuilder( szTop );
       GetVariableFromAttribute( sb_szTop, mi_lTempInteger_0, 'S', 11, mSPLDef, "LLD_Block", "Top", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szTop = sb_szTop.toString( );}
   //:IF szTop != ""
   if ( ZeidonStringCompare( szTop, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " top=^" + szTop + "in^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " top=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTop, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szHeight = mSPLDef.LLD_Block.Height 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szHeight;
   if ( szHeight == null )
      sb_szHeight = new StringBuilder( 32 );
   else
      sb_szHeight = new StringBuilder( szHeight );
       GetVariableFromAttribute( sb_szHeight, mi_lTempInteger_1, 'S', 11, mSPLDef, "LLD_Block", "Height", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szHeight = sb_szHeight.toString( );}
   //:IF szHeight != ""
   if ( ZeidonStringCompare( szHeight, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " height=^" + szHeight + "in^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " height=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szHeight, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szWidth = mSPLDef.LLD_Block.Width 
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szWidth;
   if ( szWidth == null )
      sb_szWidth = new StringBuilder( 32 );
   else
      sb_szWidth = new StringBuilder( szWidth );
       GetVariableFromAttribute( sb_szWidth, mi_lTempInteger_2, 'S', 11, mSPLDef, "LLD_Block", "Width", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szWidth = sb_szWidth.toString( );}
   //:IF szWidth != ""
   if ( ZeidonStringCompare( szWidth, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " width=^" + szWidth + "in^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " width=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szWidth, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szLeft = mSPLDef.LLD_Block.Left 
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szLeft;
   if ( szLeft == null )
      sb_szLeft = new StringBuilder( 32 );
   else
      sb_szLeft = new StringBuilder( szLeft );
       GetVariableFromAttribute( sb_szLeft, mi_lTempInteger_3, 'S', 11, mSPLDef, "LLD_Block", "Left", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szLeft = sb_szLeft.toString( );}
   //:IF szLeft != ""
   if ( ZeidonStringCompare( szLeft, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " left=^" + szLeft + "in^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " left=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szLeft, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szBorderStyle = mSPLDef.LLD_Block.BorderStyle 
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
   StringBuilder sb_szBorderStyle;
   if ( szBorderStyle == null )
      sb_szBorderStyle = new StringBuilder( 32 );
   else
      sb_szBorderStyle = new StringBuilder( szBorderStyle );
       GetVariableFromAttribute( sb_szBorderStyle, mi_lTempInteger_4, 'S', 11, mSPLDef, "LLD_Block", "BorderStyle", "", 0 );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );
   szBorderStyle = sb_szBorderStyle.toString( );}
   //:IF szBorderStyle != ""
   if ( ZeidonStringCompare( szBorderStyle, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " border-style=^" + szBorderStyle + "^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " border-style=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szBorderStyle, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szColor = mSPLDef.LLD_Block.BorderColor 
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
   StringBuilder sb_szColor;
   if ( szColor == null )
      sb_szColor = new StringBuilder( 32 );
   else
      sb_szColor = new StringBuilder( szColor );
       GetVariableFromAttribute( sb_szColor, mi_lTempInteger_5, 'S', 11, mSPLDef, "LLD_Block", "BorderColor", "", 0 );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );
   szColor = sb_szColor.toString( );}
   //:IF szColor != ""
   if ( ZeidonStringCompare( szColor, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " border-color=^" + szColor + "^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " border-color=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szColor, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szColor = mSPLDef.LLD_Block.TextColor 
   {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
   StringBuilder sb_szColor;
   if ( szColor == null )
      sb_szColor = new StringBuilder( 32 );
   else
      sb_szColor = new StringBuilder( szColor );
       GetVariableFromAttribute( sb_szColor, mi_lTempInteger_6, 'S', 11, mSPLDef, "LLD_Block", "TextColor", "", 0 );
   lTempInteger_6 = mi_lTempInteger_6.intValue( );
   szColor = sb_szColor.toString( );}
   //:IF szColor != ""
   if ( ZeidonStringCompare( szColor, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " color=^" + szColor + "^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " color=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szColor, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szFontFamily = mSPLDef.LLD_Block.FontFamily 
   {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
   StringBuilder sb_szFontFamily;
   if ( szFontFamily == null )
      sb_szFontFamily = new StringBuilder( 32 );
   else
      sb_szFontFamily = new StringBuilder( szFontFamily );
       GetVariableFromAttribute( sb_szFontFamily, mi_lTempInteger_7, 'S', 11, mSPLDef, "LLD_Block", "FontFamily", "", 0 );
   lTempInteger_7 = mi_lTempInteger_7.intValue( );
   szFontFamily = sb_szFontFamily.toString( );}
   //:IF szFontFamily != ""
   if ( ZeidonStringCompare( szFontFamily, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " font-family=^" + szFontFamily + "^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " font-family=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szFontFamily, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szFontSize = mSPLDef.LLD_Block.FontSize 
   {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
   StringBuilder sb_szFontSize;
   if ( szFontSize == null )
      sb_szFontSize = new StringBuilder( 32 );
   else
      sb_szFontSize = new StringBuilder( szFontSize );
       GetVariableFromAttribute( sb_szFontSize, mi_lTempInteger_8, 'S', 11, mSPLDef, "LLD_Block", "FontSize", "", 0 );
   lTempInteger_8 = mi_lTempInteger_8.intValue( );
   szFontSize = sb_szFontSize.toString( );}
   //:IF szFontSize != ""
   if ( ZeidonStringCompare( szFontSize, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " font-size=^" + szFontSize + "^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " font-size=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szFontSize, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szFontWeight = mSPLDef.LLD_Block.FontWeight 
   {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
   StringBuilder sb_szFontWeight;
   if ( szFontWeight == null )
      sb_szFontWeight = new StringBuilder( 32 );
   else
      sb_szFontWeight = new StringBuilder( szFontWeight );
       GetVariableFromAttribute( sb_szFontWeight, mi_lTempInteger_9, 'S', 11, mSPLDef, "LLD_Block", "FontWeight", "", 0 );
   lTempInteger_9 = mi_lTempInteger_9.intValue( );
   szFontWeight = sb_szFontWeight.toString( );}
   //:IF szFontWeight != ""
   if ( ZeidonStringCompare( szFontWeight, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " font-weight=^" + szFontWeight + "^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " font-weight=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szFontWeight, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szMarginTop = mSPLDef.LLD_Block.MarginTop 
   {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
   StringBuilder sb_szMarginTop;
   if ( szMarginTop == null )
      sb_szMarginTop = new StringBuilder( 32 );
   else
      sb_szMarginTop = new StringBuilder( szMarginTop );
       GetVariableFromAttribute( sb_szMarginTop, mi_lTempInteger_10, 'S', 11, mSPLDef, "LLD_Block", "MarginTop", "", 0 );
   lTempInteger_10 = mi_lTempInteger_10.intValue( );
   szMarginTop = sb_szMarginTop.toString( );}
   //:IF szMarginTop != ""
   if ( ZeidonStringCompare( szMarginTop, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " margin-top=^" + szMarginTop + "in^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " margin-top=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szMarginTop, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szMarginBottom = mSPLDef.LLD_Block.MarginBottom 
   {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
   StringBuilder sb_szMarginBottom;
   if ( szMarginBottom == null )
      sb_szMarginBottom = new StringBuilder( 32 );
   else
      sb_szMarginBottom = new StringBuilder( szMarginBottom );
       GetVariableFromAttribute( sb_szMarginBottom, mi_lTempInteger_11, 'S', 11, mSPLDef, "LLD_Block", "MarginBottom", "", 0 );
   lTempInteger_11 = mi_lTempInteger_11.intValue( );
   szMarginBottom = sb_szMarginBottom.toString( );}
   //:IF szMarginBottom != ""
   if ( ZeidonStringCompare( szMarginBottom, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " margin-bottom=^" + szMarginBottom + "in^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " margin-bottom=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szMarginBottom, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szMarginLeft = mSPLDef.LLD_Block.MarginLeft 
   {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
   StringBuilder sb_szMarginLeft;
   if ( szMarginLeft == null )
      sb_szMarginLeft = new StringBuilder( 32 );
   else
      sb_szMarginLeft = new StringBuilder( szMarginLeft );
       GetVariableFromAttribute( sb_szMarginLeft, mi_lTempInteger_12, 'S', 11, mSPLDef, "LLD_Block", "MarginLeft", "", 0 );
   lTempInteger_12 = mi_lTempInteger_12.intValue( );
   szMarginLeft = sb_szMarginLeft.toString( );}
   //:IF szMarginLeft != ""
   if ( ZeidonStringCompare( szMarginLeft, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " margin-left=^" + szMarginLeft + "in^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " margin-left=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szMarginLeft, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szMarginRight = mSPLDef.LLD_Block.MarginRight 
   {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
   StringBuilder sb_szMarginRight;
   if ( szMarginRight == null )
      sb_szMarginRight = new StringBuilder( 32 );
   else
      sb_szMarginRight = new StringBuilder( szMarginRight );
       GetVariableFromAttribute( sb_szMarginRight, mi_lTempInteger_13, 'S', 11, mSPLDef, "LLD_Block", "MarginRight", "", 0 );
   lTempInteger_13 = mi_lTempInteger_13.intValue( );
   szMarginRight = sb_szMarginRight.toString( );}
   //:IF szMarginRight != ""
   if ( ZeidonStringCompare( szMarginRight, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " margin-right=^" + szMarginRight + "in^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " margin-right=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szMarginRight, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:szTextAlign = mSPLDef.LLD_Block.TextAlign 
   {MutableInt mi_lTempInteger_14 = new MutableInt( lTempInteger_14 );
   StringBuilder sb_szTextAlign;
   if ( szTextAlign == null )
      sb_szTextAlign = new StringBuilder( 32 );
   else
      sb_szTextAlign = new StringBuilder( szTextAlign );
       GetVariableFromAttribute( sb_szTextAlign, mi_lTempInteger_14, 'S', 11, mSPLDef, "LLD_Block", "TextAlign", "", 0 );
   lTempInteger_14 = mi_lTempInteger_14.intValue( );
   szTextAlign = sb_szTextAlign.toString( );}
   //:IF szTextAlign != ""
   if ( ZeidonStringCompare( szTextAlign, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = szWriteBuffer + " text-align=^" + szTextAlign + "^" 
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " text-align=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTextAlign, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
   } 

   //:END

   //:// Close the entry.
   //:szWriteBuffer = szWriteBuffer + ">"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, ">", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}

   //:// Write out the combined Block value.
   //:WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 )
   try
   {
       {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
    m_ZGlobal1_Operation.WL_QC( mSPLDef, lFile, szWriteBuffer, "^", 0 );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:AddFormatToSpecialText( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                        STRING ( 32 )    SpecialTextTitle,
//:                        STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 32 )  szSectionType
public int 
omSPLDef_AddFormatToSpecialText( View     mSPLDef,
                                 String   SpecialTextTitle,
                                 StringBuilder   szWriteBuffer )
{
   String   szSectionType = null;
   //:STRING ( 10 )  szTop
   String   szTop = null;
   //:STRING ( 10 )  szHeight
   String   szHeight = null;
   //:STRING ( 10 )  szWidth
   String   szWidth = null;
   //:STRING ( 10 )  szLeft
   String   szLeft = null;
   //:STRING ( 10 )  szBorderStyle
   String   szBorderStyle = null;
   //:STRING ( 10 )  szColor
   String   szColor = null;
   //:STRING ( 10 )  szFontFamily
   String   szFontFamily = null;
   //:STRING ( 10 )  szFontSize
   String   szFontSize = null;
   //:STRING ( 10 )  szFontWeight
   String   szFontWeight = null;
   //:STRING ( 10 )  szMarginTop
   String   szMarginTop = null;
   //:STRING ( 10 )  szMarginBottom
   String   szMarginBottom = null;
   //:STRING ( 10 )  szMarginLeft
   String   szMarginLeft = null;
   //:STRING ( 10 )  szMarginRight
   String   szMarginRight = null;
   //:STRING ( 10 )  szTextAlign
   String   szTextAlign = null;
   //:STRING ( 10 )  szLineHeight
   String   szLineHeight = null;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   //:STRING ( 256 ) szImg
   String   szImg = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;


   //:// Add any Special Attribute Block formatting variables to the text statement passed in szWriteBuffer.
   //:// Skip if the Special Text Attribute hasn't been defined.

   //:NAME VIEW mSPLDef "mSPLDefHazards"
   SetNameForView( mSPLDef, "mSPLDefHazards", null, zLEVEL_TASK );
   //:// TraceLineS( "$$$$ At Hazards: ", "" )
   //:SET CURSOR FIRST mSPLDef.LLD_SpecialSectionAttribute WHERE mSPLDef.LLD_SpecialSectionAttribute.Name = SpecialTextTitle
   RESULT = SetCursorFirstEntityByString( mSPLDef, "LLD_SpecialSectionAttribute", "Name", SpecialTextTitle, "" );
   //:IF RESULT >= zCURSOR_SET 
   if ( RESULT >= zCURSOR_SET )
   { 

      //:szColor = mSPLDef.LLD_SpecialSectionAttrBlock.TextColor 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szColor;
      if ( szColor == null )
         sb_szColor = new StringBuilder( 32 );
      else
         sb_szColor = new StringBuilder( szColor );
             GetVariableFromAttribute( sb_szColor, mi_lTempInteger_0, 'S', 11, mSPLDef, "LLD_SpecialSectionAttrBlock", "TextColor", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szColor = sb_szColor.toString( );}
      //:IF szColor != ""
      if ( ZeidonStringCompare( szColor, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " color=^" + szColor + "^" 
         ZeidonStringConcat( szWriteBuffer, 1, 0, " color=^", 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, szColor, 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
      } 

      //:END

      //:szFontFamily = mSPLDef.LLD_SpecialSectionAttrBlock.FontFamily 
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szFontFamily;
      if ( szFontFamily == null )
         sb_szFontFamily = new StringBuilder( 32 );
      else
         sb_szFontFamily = new StringBuilder( szFontFamily );
             GetVariableFromAttribute( sb_szFontFamily, mi_lTempInteger_1, 'S', 11, mSPLDef, "LLD_SpecialSectionAttrBlock", "FontFamily", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szFontFamily = sb_szFontFamily.toString( );}
      //:IF szFontFamily != ""
      if ( ZeidonStringCompare( szFontFamily, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " font-family=^" + szFontFamily + "^" 
         ZeidonStringConcat( szWriteBuffer, 1, 0, " font-family=^", 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, szFontFamily, 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
      } 

      //:END

      //:szFontSize = mSPLDef.LLD_SpecialSectionAttrBlock.FontSize 
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szFontSize;
      if ( szFontSize == null )
         sb_szFontSize = new StringBuilder( 32 );
      else
         sb_szFontSize = new StringBuilder( szFontSize );
             GetVariableFromAttribute( sb_szFontSize, mi_lTempInteger_2, 'S', 11, mSPLDef, "LLD_SpecialSectionAttrBlock", "FontSize", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szFontSize = sb_szFontSize.toString( );}
      //:IF szFontSize != ""
      if ( ZeidonStringCompare( szFontSize, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " font-size=^" + szFontSize + "^" 
         ZeidonStringConcat( szWriteBuffer, 1, 0, " font-size=^", 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, szFontSize, 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
      } 

      //:END

      //:szFontWeight = mSPLDef.LLD_SpecialSectionAttrBlock.FontWeight 
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szFontWeight;
      if ( szFontWeight == null )
         sb_szFontWeight = new StringBuilder( 32 );
      else
         sb_szFontWeight = new StringBuilder( szFontWeight );
             GetVariableFromAttribute( sb_szFontWeight, mi_lTempInteger_3, 'S', 11, mSPLDef, "LLD_SpecialSectionAttrBlock", "FontWeight", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szFontWeight = sb_szFontWeight.toString( );}
      //:IF szFontWeight != ""
      if ( ZeidonStringCompare( szFontWeight, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " font-weight=^" + szFontWeight + "^" 
         ZeidonStringConcat( szWriteBuffer, 1, 0, " font-weight=^", 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, szFontWeight, 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
      } 

      //:END

      //:szLineHeight = mSPLDef.LLD_SpecialSectionAttrBlock.TextLineHeight 
      {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
      StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
             GetVariableFromAttribute( sb_szLineHeight, mi_lTempInteger_4, 'S', 11, mSPLDef, "LLD_SpecialSectionAttrBlock", "TextLineHeight", "", 0 );
      lTempInteger_4 = mi_lTempInteger_4.intValue( );
      szLineHeight = sb_szLineHeight.toString( );}
      //:IF szLineHeight != ""
      if ( ZeidonStringCompare( szLineHeight, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " line-height=^" + szLineHeight + "^" 
         ZeidonStringConcat( szWriteBuffer, 1, 0, " line-height=^", 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, szLineHeight, 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
      } 

      //:END

      //:szMarginTop = mSPLDef.LLD_SpecialSectionAttrBlock.MarginTop 
      {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
      StringBuilder sb_szMarginTop;
      if ( szMarginTop == null )
         sb_szMarginTop = new StringBuilder( 32 );
      else
         sb_szMarginTop = new StringBuilder( szMarginTop );
             GetVariableFromAttribute( sb_szMarginTop, mi_lTempInteger_5, 'S', 11, mSPLDef, "LLD_SpecialSectionAttrBlock", "MarginTop", "", 0 );
      lTempInteger_5 = mi_lTempInteger_5.intValue( );
      szMarginTop = sb_szMarginTop.toString( );}
      //:IF szMarginTop != ""
      if ( ZeidonStringCompare( szMarginTop, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " margin-top=^" + szMarginTop + "in^" 
         ZeidonStringConcat( szWriteBuffer, 1, 0, " margin-top=^", 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, szMarginTop, 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      } 

      //:END

      //:szMarginBottom = mSPLDef.LLD_SpecialSectionAttrBlock.MarginBottom 
      {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
      StringBuilder sb_szMarginBottom;
      if ( szMarginBottom == null )
         sb_szMarginBottom = new StringBuilder( 32 );
      else
         sb_szMarginBottom = new StringBuilder( szMarginBottom );
             GetVariableFromAttribute( sb_szMarginBottom, mi_lTempInteger_6, 'S', 11, mSPLDef, "LLD_SpecialSectionAttrBlock", "MarginBottom", "", 0 );
      lTempInteger_6 = mi_lTempInteger_6.intValue( );
      szMarginBottom = sb_szMarginBottom.toString( );}
      //:IF szMarginBottom != ""
      if ( ZeidonStringCompare( szMarginBottom, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " margin-bottom=^" + szMarginBottom + "in^" 
         ZeidonStringConcat( szWriteBuffer, 1, 0, " margin-bottom=^", 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, szMarginBottom, 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      } 

      //:END

      //:szMarginLeft = mSPLDef.LLD_SpecialSectionAttrBlock.MarginLeft 
      {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
      StringBuilder sb_szMarginLeft;
      if ( szMarginLeft == null )
         sb_szMarginLeft = new StringBuilder( 32 );
      else
         sb_szMarginLeft = new StringBuilder( szMarginLeft );
             GetVariableFromAttribute( sb_szMarginLeft, mi_lTempInteger_7, 'S', 11, mSPLDef, "LLD_SpecialSectionAttrBlock", "MarginLeft", "", 0 );
      lTempInteger_7 = mi_lTempInteger_7.intValue( );
      szMarginLeft = sb_szMarginLeft.toString( );}
      //:IF szMarginLeft != ""
      if ( ZeidonStringCompare( szMarginLeft, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " margin-left=^" + szMarginLeft + "in^" 
         ZeidonStringConcat( szWriteBuffer, 1, 0, " margin-left=^", 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, szMarginLeft, 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      } 

      //:END

      //:szMarginRight = mSPLDef.LLD_SpecialSectionAttrBlock.MarginRight 
      {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
      StringBuilder sb_szMarginRight;
      if ( szMarginRight == null )
         sb_szMarginRight = new StringBuilder( 32 );
      else
         sb_szMarginRight = new StringBuilder( szMarginRight );
             GetVariableFromAttribute( sb_szMarginRight, mi_lTempInteger_8, 'S', 11, mSPLDef, "LLD_SpecialSectionAttrBlock", "MarginRight", "", 0 );
      lTempInteger_8 = mi_lTempInteger_8.intValue( );
      szMarginRight = sb_szMarginRight.toString( );}
      //:IF szMarginRight != ""
      if ( ZeidonStringCompare( szMarginRight, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " margin-right=^" + szMarginRight + "in^" 
         ZeidonStringConcat( szWriteBuffer, 1, 0, " margin-right=^", 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, szMarginRight, 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      } 

      //:END

      //:szTextAlign = mSPLDef.LLD_SpecialSectionAttrBlock.TextAlign 
      {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
      StringBuilder sb_szTextAlign;
      if ( szTextAlign == null )
         sb_szTextAlign = new StringBuilder( 32 );
      else
         sb_szTextAlign = new StringBuilder( szTextAlign );
             GetVariableFromAttribute( sb_szTextAlign, mi_lTempInteger_9, 'S', 11, mSPLDef, "LLD_SpecialSectionAttrBlock", "TextAlign", "", 0 );
      lTempInteger_9 = mi_lTempInteger_9.intValue( );
      szTextAlign = sb_szTextAlign.toString( );}
      //:IF szTextAlign != ""
      if ( ZeidonStringCompare( szTextAlign, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + " text-align=^" + szTextAlign + "^" 
         ZeidonStringConcat( szWriteBuffer, 1, 0, " text-align=^", 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, szTextAlign, 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, "^", 1, 0, 32001 );
      } 

      //:END

      //:ELSE
   } 
   else
   { 
      //:// If the block has a Bottom Margin, use it for each block below.
      //:IF mSPLDef.LLD_Block.MarginBottom != ""
      if ( CompareAttributeToString( mSPLDef, "LLD_Block", "MarginBottom", "" ) != 0 )
      { 
         //:szMarginBottom = mSPLDef.LLD_Block.MarginBottom
         {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
         StringBuilder sb_szMarginBottom;
         if ( szMarginBottom == null )
            sb_szMarginBottom = new StringBuilder( 32 );
         else
            sb_szMarginBottom = new StringBuilder( szMarginBottom );
                   GetVariableFromAttribute( sb_szMarginBottom, mi_lTempInteger_10, 'S', 11, mSPLDef, "LLD_Block", "MarginBottom", "", 0 );
         lTempInteger_10 = mi_lTempInteger_10.intValue( );
         szMarginBottom = sb_szMarginBottom.toString( );}
         //:szWriteBuffer = szWriteBuffer + " margin-bottom=^" + szMarginBottom + "in^" 
         ZeidonStringConcat( szWriteBuffer, 1, 0, " margin-bottom=^", 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, szMarginBottom, 1, 0, 32001 );
         ZeidonStringConcat( szWriteBuffer, 1, 0, "in^", 1, 0, 32001 );
      } 

      //:END
   } 

   //:END

   //:// Close the Block.
   //:szWriteBuffer = szWriteBuffer + ">" 
   ZeidonStringConcat( szWriteBuffer, 1, 0, ">", 1, 0, 32001 );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:GetLPLR_SourceDirectory( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                         STRING ( 400 ) ReturnedDirectory )

//:   VIEW vTZZOLFLO
private int 
omSPLDef_GetLPLR_SourceDirectory( View     mSPLDef,
                                  String   ReturnedDirectory )
{
   zVIEW    vTZZOLFLO = new zVIEW( );
   //:STRING ( 400 ) szFileName
   String   szFileName = null;
   //:STRING ( 32 )  szApplicationName
   String   szApplicationName = null;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   //:SHORT          nRC
   int      nRC = 0;
   int      RESULT = 0;


   //:// Return the LPLR Source directory to the caller.
   //:// We will get it from the MetaSource directory in the XLP, which speicifies the source of the Query
   //:// View list for both read and write.

   //:// Try to use the existing view.
   //:GET VIEW vTZZOLFLO NAMED "TZZOLFLO"
   RESULT = GetViewByName( vTZZOLFLO, "TZZOLFLO", mSPLDef, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:// Get the XLP directory structure and file name.
      //:GetApplDirectoryFromView( szFileName, mSPLDef, zAPPL_DIR_OBJECT, 400 )
      {StringBuilder sb_szFileName;
      if ( szFileName == null )
         sb_szFileName = new StringBuilder( 32 );
      else
         sb_szFileName = new StringBuilder( szFileName );
             GetApplDirectoryFromView( sb_szFileName, mSPLDef, zAPPL_DIR_OBJECT, 400 );
      szFileName = sb_szFileName.toString( );}
      //:GetCurrentApplicationName( szApplicationName, 32, mSPLDef )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       {StringBuilder sb_szApplicationName;
      if ( szApplicationName == null )
         sb_szApplicationName = new StringBuilder( 32 );
      else
         sb_szApplicationName = new StringBuilder( szApplicationName );
             m_ZGlobal1_Operation.GetCurrentApplicationName( sb_szApplicationName, 32, mSPLDef );
      szApplicationName = sb_szApplicationName.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szFileName = szFileName + szApplicationName + ".XLP"
       {StringBuilder sb_szFileName;
      if ( szFileName == null )
         sb_szFileName = new StringBuilder( 32 );
      else
         sb_szFileName = new StringBuilder( szFileName );
            ZeidonStringConcat( sb_szFileName, 1, 0, szApplicationName, 1, 0, 401 );
      szFileName = sb_szFileName.toString( );}
       {StringBuilder sb_szFileName;
      if ( szFileName == null )
         sb_szFileName = new StringBuilder( 32 );
      else
         sb_szFileName = new StringBuilder( szFileName );
            ZeidonStringConcat( sb_szFileName, 1, 0, ".XLP", 1, 0, 401 );
      szFileName = sb_szFileName.toString( );}
      //:// Activate the XLP to the query LODs.
      //:// 536870912 is ACTIVATE_SYSTEM in the following activate statement.
      //:nRC = ActivateOI_FromFile( vTZZOLFLO, "TZCMLPLO", mSPLDef, szFileName, 536870912 )
      nRC = ActivateOI_FromFile( vTZZOLFLO, "TZCMLPLO", mSPLDef, szFileName, 536870912 );
      //:IF nRC < 0
      if ( nRC < 0 )
      { 
         //:szMsg = "Cannot activate the Query .XLP from executable directory, " + szFileName + "."
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "Cannot activate the Query .XLP from executable directory, ", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szFileName, 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, ".", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
         //:MessageSend( mSPLDef, "", "Open Query",
         //:             szMsg,
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mSPLDef, "", "Open Query", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( mSPLDef, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( mSPLDef, zWAB_StayOnWindow, "", "" );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
      //:NAME VIEW vTZZOLFLO "TZZOLFLO"
      SetNameForView( vTZZOLFLO, "TZZOLFLO", null, zLEVEL_TASK );
   } 

   //:END
   //:// Get the Meta Source name from the root.
   //:GetStringFromAttribute( ReturnedDirectory, vTZZOLFLO, "LPLR", "MetaSrcDir" )
   {StringBuilder sb_ReturnedDirectory;
   if ( ReturnedDirectory == null )
      sb_ReturnedDirectory = new StringBuilder( 32 );
   else
      sb_ReturnedDirectory = new StringBuilder( ReturnedDirectory );
       GetStringFromAttribute( sb_ReturnedDirectory, vTZZOLFLO, "LPLR", "MetaSrcDir" );
   ReturnedDirectory = sb_ReturnedDirectory.toString( );}
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateXML_File( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                  STRING ( 32 )  szTopEntityName,
//:                  STRING ( 300 ) szDirectoryAndFileName )

//:   STRING ( 50 )   szIndentation
public int 
omSPLDef_GenerateXML_File( View     mSPLDef,
                           String   szTopEntityName,
                           String   szDirectoryAndFileName )
{
   String   szIndentation = null;
   //:STRING ( 400 )  szMsg
   String   szMsg = null;
   //:STRING ( 400 )  szFileName
   String   szFileName = null;
   //:STRING ( 5000 ) szValue
   String   szValue = null;
   //:STRING ( 5000 ) szOutputLine
   String   szOutputLine = null;
   //:STRING ( 50 )   szSectionName
   String   szSectionName = null;
   //:STRING ( 50 )   szStatementName
   String   szStatementName = null;
   //:STRING ( 50 )   szDateTimeDisplay
   String   szDateTimeDisplay = null;
   //:INTEGER         lFileHandle
   int      lFileHandle = 0;
   //:SHORT           nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      RESULT = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   int      lTempInteger_13 = 0;
   int      lTempInteger_14 = 0;
   int      lTempInteger_15 = 0;
   int      lTempInteger_16 = 0;
   int      lTempInteger_17 = 0;
   int      lTempInteger_18 = 0;
   int      lTempInteger_19 = 0;
   int      lTempInteger_20 = 0;
   int      lTempInteger_21 = 0;
   int      lTempInteger_22 = 0;
   int      lTempInteger_23 = 0;
   int      lTempInteger_24 = 0;


   //:// Build an XML object from selected entries in the mSPLDef object instance.

   //:// Open XML output file.
   //:lFileHandle = SysOpenFile( mSPLDef, szDirectoryAndFileName, COREFILE_WRITE )
   try
   {
       lFileHandle = m_KZOEP1AA.SysOpenFile( mSPLDef, szDirectoryAndFileName, COREFILE_WRITE );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:IF lFileHandle < 0
   if ( lFileHandle < 0 )
   { 
      //:szMsg = "Cannot open XML Output File, " + szDirectoryAndFileName
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringCopy( sb_szMsg, 1, 0, "Cannot open XML Output File, ", 1, 0, 401 );
      szMsg = sb_szMsg.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, szDirectoryAndFileName, 1, 0, 401 );
      szMsg = sb_szMsg.toString( );}
      //:MessageSend( mSPLDef, "", "Generate XSLT",
      //:             szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
      MessageSend( mSPLDef, "", "Generate XSLT", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Build Header entries.
   //:szOutputLine = "<?xml version=" + QUOTES + "1.0" + QUOTES + "?>"
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
      ZeidonStringConcat( sb_szOutputLine, 1, 0, "?>", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
   //:GetStringFromAttributeByContext( szDateTimeDisplay, mSPLDef, "SubregPhysicalLabelDef", "wDateTime", "YYYY/MM/DD HH:MM:SS.S AM", 30 )
   {StringBuilder sb_szDateTimeDisplay;
   if ( szDateTimeDisplay == null )
      sb_szDateTimeDisplay = new StringBuilder( 32 );
   else
      sb_szDateTimeDisplay = new StringBuilder( szDateTimeDisplay );
       GetStringFromAttributeByContext( sb_szDateTimeDisplay, mSPLDef, "SubregPhysicalLabelDef", "wDateTime", "YYYY/MM/DD HH:MM:SS.S AM", 30 );
   szDateTimeDisplay = sb_szDateTimeDisplay.toString( );}
   //:szOutputLine = "<!-- Output created by ePamms   " + szDateTimeDisplay + " -->"
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringCopy( sb_szOutputLine, 1, 0, "<!-- Output created by ePamms   ", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringConcat( sb_szOutputLine, 1, 0, szDateTimeDisplay, 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringConcat( sb_szOutputLine, 1, 0, " -->", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );

   //:szOutputLine = "   <zOI>"
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringCopy( sb_szOutputLine, 1, 0, "   <zOI>", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
   //:szOutputLine = "      <SubregPhysicalLabelDef>"
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringCopy( sb_szOutputLine, 1, 0, "      <SubregPhysicalLabelDef>", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );

   //:szIndentation = "         "
    {StringBuilder sb_szIndentation;
   if ( szIndentation == null )
      sb_szIndentation = new StringBuilder( 32 );
   else
      sb_szIndentation = new StringBuilder( szIndentation );
      ZeidonStringCopy( sb_szIndentation, 1, 0, "         ", 1, 0, 51 );
   szIndentation = sb_szIndentation.toString( );}
   //:szValue = mSPLDef.SubregPhysicalLabelDef.Name
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szValue;
   if ( szValue == null )
      sb_szValue = new StringBuilder( 32 );
   else
      sb_szValue = new StringBuilder( szValue );
       GetVariableFromAttribute( sb_szValue, mi_lTempInteger_0, 'S', 5001, mSPLDef, "SubregPhysicalLabelDef", "Name", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szValue = sb_szValue.toString( );}
   //:GenerateAttribute( mSPLDef, lFileHandle, "Name", szIndentation, szValue )
   omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "Name", szIndentation, szValue );
   //:szValue = mSPLDef.SubregPhysicalLabelDef.ProductName
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szValue;
   if ( szValue == null )
      sb_szValue = new StringBuilder( 32 );
   else
      sb_szValue = new StringBuilder( szValue );
       GetVariableFromAttribute( sb_szValue, mi_lTempInteger_1, 'S', 5001, mSPLDef, "SubregPhysicalLabelDef", "ProductName", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szValue = sb_szValue.toString( );}
   //:GenerateAttribute( mSPLDef, lFileHandle, "ProductName", szIndentation, szValue )
   omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "ProductName", szIndentation, szValue );

   //:// <SubregLabelContent>
   //:szOutputLine = "         <SubregLabelContent>"
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringCopy( sb_szOutputLine, 1, 0, "         <SubregLabelContent>", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
   //:szIndentation = "            "
    {StringBuilder sb_szIndentation;
   if ( szIndentation == null )
      sb_szIndentation = new StringBuilder( 32 );
   else
      sb_szIndentation = new StringBuilder( szIndentation );
      ZeidonStringCopy( sb_szIndentation, 1, 0, "            ", 1, 0, 51 );
   szIndentation = sb_szIndentation.toString( );}
   //:szValue = mSPLDef.SubregLabelContent.EPA_RegistrationNumber 
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szValue;
   if ( szValue == null )
      sb_szValue = new StringBuilder( 32 );
   else
      sb_szValue = new StringBuilder( szValue );
       GetVariableFromAttribute( sb_szValue, mi_lTempInteger_2, 'S', 5001, mSPLDef, "SubregLabelContent", "EPA_RegistrationNumber", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szValue = sb_szValue.toString( );}
   //:GenerateAttribute( mSPLDef, lFileHandle, "EPA_RegistrationNumber", szIndentation, szValue )
   omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "EPA_RegistrationNumber", szIndentation, szValue );
   //:szValue = mSPLDef.SubregLabelContent.EPA_EstablishmentNumber 
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szValue;
   if ( szValue == null )
      sb_szValue = new StringBuilder( 32 );
   else
      sb_szValue = new StringBuilder( szValue );
       GetVariableFromAttribute( sb_szValue, mi_lTempInteger_3, 'S', 5001, mSPLDef, "SubregLabelContent", "EPA_EstablishmentNumber", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szValue = sb_szValue.toString( );}
   //:GenerateAttribute( mSPLDef, lFileHandle, "EPA_EstablishmentNumber", szIndentation, szValue )
   omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "EPA_EstablishmentNumber", szIndentation, szValue );
   //:szValue = mSPLDef.SubregLabelContent.ESL_Date 
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
   StringBuilder sb_szValue;
   if ( szValue == null )
      sb_szValue = new StringBuilder( 32 );
   else
      sb_szValue = new StringBuilder( szValue );
       GetVariableFromAttribute( sb_szValue, mi_lTempInteger_4, 'S', 5001, mSPLDef, "SubregLabelContent", "ESL_Date", "", 0 );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );
   szValue = sb_szValue.toString( );}
   //:GenerateAttribute( mSPLDef, lFileHandle, "ESL_Date", szIndentation, szValue )
   omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "ESL_Date", szIndentation, szValue );
   //:szOutputLine = "         </SubregLabelContent>"
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringCopy( sb_szOutputLine, 1, 0, "         </SubregLabelContent>", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );

   //:// <SPLD_GeneralSection>
   //:FOR EACH mSPLDef.SPLD_GeneralSection 
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szOutputLine = "         <SPLD_GeneralSection>"
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringCopy( sb_szOutputLine, 1, 0, "         <SPLD_GeneralSection>", 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
      //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
      omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
      //:szIndentation = "            "
       {StringBuilder sb_szIndentation;
      if ( szIndentation == null )
         sb_szIndentation = new StringBuilder( 32 );
      else
         sb_szIndentation = new StringBuilder( szIndentation );
            ZeidonStringCopy( sb_szIndentation, 1, 0, "            ", 1, 0, 51 );
      szIndentation = sb_szIndentation.toString( );}
      //:szValue = mSPLDef.SPLD_GeneralSection.Title 
      {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
      StringBuilder sb_szValue;
      if ( szValue == null )
         sb_szValue = new StringBuilder( 32 );
      else
         sb_szValue = new StringBuilder( szValue );
             GetVariableFromAttribute( sb_szValue, mi_lTempInteger_5, 'S', 5001, mSPLDef, "SPLD_GeneralSection", "Title", "", 0 );
      lTempInteger_5 = mi_lTempInteger_5.intValue( );
      szValue = sb_szValue.toString( );}
      //:GenerateAttribute( mSPLDef, lFileHandle, "Title", szIndentation, szValue )
      omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "Title", szIndentation, szValue );
      //:szValue = mSPLDef.SPLD_GeneralSection.Subtitle 
      {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
      StringBuilder sb_szValue;
      if ( szValue == null )
         sb_szValue = new StringBuilder( 32 );
      else
         sb_szValue = new StringBuilder( szValue );
             GetVariableFromAttribute( sb_szValue, mi_lTempInteger_6, 'S', 5001, mSPLDef, "SPLD_GeneralSection", "Subtitle", "", 0 );
      lTempInteger_6 = mi_lTempInteger_6.intValue( );
      szValue = sb_szValue.toString( );}
      //:GenerateAttribute( mSPLDef, lFileHandle, "Subtitle", szIndentation, szValue )
      omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "Subtitle", szIndentation, szValue );

      //:// <SPLD_GeneralStatement>
      //:FOR EACH mSPLDef.SPLD_GeneralStatement 
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_GeneralStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:szOutputLine = "            <SPLD_GeneralStatement>"
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringCopy( sb_szOutputLine, 1, 0, "            <SPLD_GeneralStatement>", 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
         //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
         omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
         //:szIndentation = "               "
          {StringBuilder sb_szIndentation;
         if ( szIndentation == null )
            sb_szIndentation = new StringBuilder( 32 );
         else
            sb_szIndentation = new StringBuilder( szIndentation );
                  ZeidonStringCopy( sb_szIndentation, 1, 0, "               ", 1, 0, 51 );
         szIndentation = sb_szIndentation.toString( );}
         //:szValue = mSPLDef.SPLD_GeneralStatement.Text 
         {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
         StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                   GetVariableFromAttribute( sb_szValue, mi_lTempInteger_7, 'S', 5001, mSPLDef, "SPLD_GeneralStatement", "Text", "", 0 );
         lTempInteger_7 = mi_lTempInteger_7.intValue( );
         szValue = sb_szValue.toString( );}
         //:GenerateAttribute( mSPLDef, lFileHandle, "Text", szIndentation, szValue )
         omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "Text", szIndentation, szValue );
         //:szOutputLine = "            </SPLD_GeneralStatement>"
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringCopy( sb_szOutputLine, 1, 0, "            </SPLD_GeneralStatement>", 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
         //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
         omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_GeneralStatement", "" );
      } 

      //:END

      //:szOutputLine = "         </SPLD_GeneralSection>"
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringCopy( sb_szOutputLine, 1, 0, "         </SPLD_GeneralSection>", 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
      //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
      omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_GeneralSection", "" );
   } 

   //:END

   //:// <SPLD_IngredientsSection>
   //:FOR EACH mSPLDef.SPLD_IngredientsSection 
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_IngredientsSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szOutputLine = "         <SPLD_IngredientsSection>"
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringCopy( sb_szOutputLine, 1, 0, "         <SPLD_IngredientsSection>", 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
      //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
      omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
      //:szIndentation = "            "
       {StringBuilder sb_szIndentation;
      if ( szIndentation == null )
         sb_szIndentation = new StringBuilder( 32 );
      else
         sb_szIndentation = new StringBuilder( szIndentation );
            ZeidonStringCopy( sb_szIndentation, 1, 0, "            ", 1, 0, 51 );
      szIndentation = sb_szIndentation.toString( );}
      //:szValue = mSPLDef.SPLD_IngredientsSection.ActiveTitle 
      {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
      StringBuilder sb_szValue;
      if ( szValue == null )
         sb_szValue = new StringBuilder( 32 );
      else
         sb_szValue = new StringBuilder( szValue );
             GetVariableFromAttribute( sb_szValue, mi_lTempInteger_8, 'S', 5001, mSPLDef, "SPLD_IngredientsSection", "ActiveTitle", "", 0 );
      lTempInteger_8 = mi_lTempInteger_8.intValue( );
      szValue = sb_szValue.toString( );}
      //:GenerateAttribute( mSPLDef, lFileHandle, "ActiveTitle", szIndentation, szValue )
      omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "ActiveTitle", szIndentation, szValue );
      //:szValue = mSPLDef.SPLD_IngredientsSection.InertTitle 
      {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
      StringBuilder sb_szValue;
      if ( szValue == null )
         sb_szValue = new StringBuilder( 32 );
      else
         sb_szValue = new StringBuilder( szValue );
             GetVariableFromAttribute( sb_szValue, mi_lTempInteger_9, 'S', 5001, mSPLDef, "SPLD_IngredientsSection", "InertTitle", "", 0 );
      lTempInteger_9 = mi_lTempInteger_9.intValue( );
      szValue = sb_szValue.toString( );}
      //:GenerateAttribute( mSPLDef, lFileHandle, "InertTitle", szIndentation, szValue )
      omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "InertTitle", szIndentation, szValue );
      //:szValue = mSPLDef.SPLD_IngredientsSection.GeneralInactivePercent 
      {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
      StringBuilder sb_szValue;
      if ( szValue == null )
         sb_szValue = new StringBuilder( 32 );
      else
         sb_szValue = new StringBuilder( szValue );
             GetVariableFromAttribute( sb_szValue, mi_lTempInteger_10, 'S', 5001, mSPLDef, "SPLD_IngredientsSection", "GeneralInactivePercent", "", 0 );
      lTempInteger_10 = mi_lTempInteger_10.intValue( );
      szValue = sb_szValue.toString( );}
      //:GenerateAttribute( mSPLDef, lFileHandle, "GeneralInactivePercent", szIndentation, szValue )
      omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "GeneralInactivePercent", szIndentation, szValue );

      //:// <SPLD_IngredientsStatement>
      //:FOR EACH mSPLDef.SPLD_IngredientsStatement 
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_IngredientsStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:szOutputLine = "            <SPLD_IngredientsStatement>"
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringCopy( sb_szOutputLine, 1, 0, "            <SPLD_IngredientsStatement>", 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
         //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
         omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
         //:szIndentation = "               "
          {StringBuilder sb_szIndentation;
         if ( szIndentation == null )
            sb_szIndentation = new StringBuilder( 32 );
         else
            sb_szIndentation = new StringBuilder( szIndentation );
                  ZeidonStringCopy( sb_szIndentation, 1, 0, "               ", 1, 0, 51 );
         szIndentation = sb_szIndentation.toString( );}
         //:szValue = mSPLDef.SPLD_IngredientsStatement.ChemicalName
         {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
         StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                   GetVariableFromAttribute( sb_szValue, mi_lTempInteger_11, 'S', 5001, mSPLDef, "SPLD_IngredientsStatement", "ChemicalName", "", 0 );
         lTempInteger_11 = mi_lTempInteger_11.intValue( );
         szValue = sb_szValue.toString( );}
         //:GenerateAttribute( mSPLDef, lFileHandle, "ChemicalName", szIndentation, szValue )
         omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "ChemicalName", szIndentation, szValue );
         //:szValue = mSPLDef.SPLD_IngredientsStatement.Percent 
         {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
         StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                   GetVariableFromAttribute( sb_szValue, mi_lTempInteger_12, 'S', 5001, mSPLDef, "SPLD_IngredientsStatement", "Percent", "", 0 );
         lTempInteger_12 = mi_lTempInteger_12.intValue( );
         szValue = sb_szValue.toString( );}
         //:GenerateAttribute( mSPLDef, lFileHandle, "Percent", szIndentation, szValue )
         omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "Percent", szIndentation, szValue );
         //:szOutputLine = "            </SPLD_IngredientsStatement>"
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringCopy( sb_szOutputLine, 1, 0, "            </SPLD_IngredientsStatement>", 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
         //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
         omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_IngredientsStatement", "" );
      } 

      //:END

      //:szOutputLine = "         </SPLD_IngredientsSection>"
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringCopy( sb_szOutputLine, 1, 0, "         </SPLD_IngredientsSection>", 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
      //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
      omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_IngredientsSection", "" );
   } 

   //:END

   //:// <SPLD_StorageDisposalSection>
   //:/*FOR EACH mSPLDef.SPLD_StorageDisposalSection 
   //:   szOutputLine = "         <SPLD_StorageDisposalSection>"
   //:   GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:   szIndentation = "            "
   //:   szValue = mSPLDef.SPLD_StorageDisposalSection.Title 
   //:   GenerateAttribute( mSPLDef, lFileHandle, "Title", szIndentation, szValue )
   //:   
   //:   // <SPLD_StorageDisposalStatement>
   //:   FOR EACH mSPLDef.SPLD_StorageDisposalStatement 
   //:      szOutputLine = "            <SPLD_StorageDisposalStatement>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:      szIndentation = "               "
   //:      szValue = mSPLDef.SPLD_StorageDisposalStatement.Title 
   //:      GenerateAttribute( mSPLDef, lFileHandle, "Title", szIndentation, szValue )
   //:      szValue = mSPLDef.SPLD_StorageDisposalStatement.Text 
   //:      GenerateAttribute( mSPLDef, lFileHandle, "Text", szIndentation, szValue )
   //:      szOutputLine = "            </SPLD_StorageDisposalStatement>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:   END
   //:   
   //:   szOutputLine = "         </SPLD_StorageDisposalSection>"
   //:   GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:END

   //:// <SPLD_DirectionsForUseSection>
   //:FOR EACH mSPLDef.SPLD_DirectionsForUseSection 
   //:   szOutputLine = "         <SPLD_DirectionsForUseSection>"
   //:   GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:   szIndentation = "            "
   //:   szValue = mSPLDef.SPLD_DirectionsForUseSection.Title 
   //:   GenerateAttribute( mSPLDef, lFileHandle, "Title", szIndentation, szValue )
   //:   
   //:   // <SPLD_DirectionsForUseStatement>
   //:   FOR EACH mSPLDef.SPLD_DirectionsForUseStatement 
   //:      szOutputLine = "            <SPLD_DirectionsForUseStatement>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:      szIndentation = "               "
   //:      szValue = mSPLDef.SPLD_DirectionsForUseStatement.Title 
   //:      GenerateAttribute( mSPLDef, lFileHandle, "Title", szIndentation, szValue )
   //:      szValue = mSPLDef.SPLD_DirectionsForUseStatement.DisplayText     // We'll use DisplayText because it includes inserted characters.
   //:      GenerateAttribute( mSPLDef, lFileHandle, "Text", szIndentation, szValue )
   //:      szOutputLine = "            </SPLD_DirectionsForUseStatement>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:   END
   //:   
   //:   // <SPLD_DirectionsUsageOrdering>
   //:   FOR EACH mSPLDef.SPLD_DirectionsUsageOrdering 
   //:      szOutputLine = "         <SPLD_DirectionsUsageOrdering>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:      szIndentation = "            "
   //:      szValue = mSPLDef.SPLD_DirectionsUsageOrdering.wSortOrder  
   //:      GenerateAttribute( mSPLDef, lFileHandle, "wSortOrder", szIndentation, szValue )
   //:      
   //:      // <SPLD_DirectionsUsageOrdering>
   //:      szOutputLine = "            <SPLD_DirectionsUsage>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:      szIndentation = "               "
   //:      szValue = mSPLDef.SPLD_DirectionsUsage.UsageType 
   //:      GenerateAttribute( mSPLDef, lFileHandle, "UsageType", szIndentation, szValue )
   //:      szValue = mSPLDef.SPLD_DirectionsUsage.Name 
   //:      GenerateAttribute( mSPLDef, lFileHandle, "Name", szIndentation, szValue )
   //:      szOutputLine = "            </SPLD_DirectionsUsage>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:      
   //:      szOutputLine = "         </SPLD_DirectionsUsageOrdering>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:   END
   //:   
   //:   szOutputLine = "         </SPLD_DirectionsForUseSection>"
   //:   GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:END

   //:// <SPLD_MarketingSection>
   //:FOR EACH mSPLDef.SPLD_MarketingSection 
   //:   // The Marketing Section name includes the Sequence Number to get uniqueness
   //:   szSectionName = mSPLDef.SPLD_MarketingSection.wXML_MarketingName 
   //:   szOutputLine = "         <" + szSectionName + ">"
   //:   GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:   szIndentation = "            "
   //:   szValue = mSPLDef.SPLD_MarketingSection.Title 
   //:   GenerateAttribute( mSPLDef, lFileHandle, "Title", szIndentation, szValue )
   //:   
   //:   // <SPLD_MarketingStatement>
   //:   FOR EACH mSPLDef.SPLD_MarketingStatement 
   //:      szOutputLine = "            <SPLD_MarketingStatement>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:      szIndentation = "               "
   //:      szValue = mSPLDef.SPLD_MarketingStatement.Title 
   //:      GenerateAttribute( mSPLDef, lFileHandle, "Title", szIndentation, szValue )
   //:      szValue = mSPLDef.SPLD_MarketingStatement.DisplayText     // We'll use DisplayText because it includes inserted characters.
   //:      GenerateAttribute( mSPLDef, lFileHandle, "Text", szIndentation, szValue )
   //:      szOutputLine = "            </SPLD_MarketingStatement>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:   END
   //:   
   //:   // <SPLD_DirectionsUsageOrdering>
   //:   FOR EACH mSPLDef.SPLD_DirectionsUsageOrdering 
   //:      szOutputLine = "         <SPLD_DirectionsUsageOrdering>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:      szIndentation = "               "
   //:      szValue = mSPLDef.SPLD_DirectionsUsageOrdering.wSortOrder  
   //:      GenerateAttribute( mSPLDef, lFileHandle, "wSortOrder", szIndentation, szValue )
   //:      
   //:      // <SPLD_DirectionsUsageOrdering>
   //:      szOutputLine = "            <SPLD_DirectionsUsage>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:      szIndentation = "               "
   //:      szValue = mSPLDef.SPLD_DirectionsUsage.UsageType 
   //:      GenerateAttribute( mSPLDef, lFileHandle, "UsageType", szIndentation, szValue )
   //:      szValue = mSPLDef.SPLD_DirectionsUsage.Name 
   //:      GenerateAttribute( mSPLDef, lFileHandle, "Name", szIndentation, szValue )
   //:      szOutputLine = "            </SPLD_DirectionsUsage>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:      
   //:      szOutputLine = "         </SPLD_DirectionsUsageOrdering>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:   END
   //:   
   //:   // <DisplayUsageColumn1>
   //:   FOR EACH mSPLDef.DisplayUsageColumn1 
   //:      szOutputLine = "            <DisplayUsageColumn1>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:      szIndentation = "               "
   //:      szValue = mSPLDef.DisplayUsageColumn1.Name  
   //:      GenerateAttribute( mSPLDef, lFileHandle, "Name", szIndentation, szValue )
   //:      szOutputLine = "            </DisplayUsageColumn1>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:   END
   //:   
   //:   // <DisplayUsageColumn2>
   //:   FOR EACH mSPLDef.DisplayUsageColumn2 
   //:      szOutputLine = "            <DisplayUsageColumn2>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:      szIndentation = "               "
   //:      szValue = mSPLDef.DisplayUsageColumn2.Name  
   //:      GenerateAttribute( mSPLDef, lFileHandle, "Name", szIndentation, szValue )
   //:      szOutputLine = "            </DisplayUsageColumn2>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:   END
   //:   
   //:   // <DisplayUsageColumn3>
   //:   FOR EACH mSPLDef.DisplayUsageColumn3 
   //:      szOutputLine = "            <DisplayUsageColumn3>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:      szIndentation = "               "
   //:      szValue = mSPLDef.DisplayUsageColumn3.Name  
   //:      GenerateAttribute( mSPLDef, lFileHandle, "Name", szIndentation, szValue )
   //:      szOutputLine = "            </DisplayUsageColumn3>"
   //:      GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:   END
   //:   
   //:   szOutputLine = "         </" + szSectionName + ">"
   //:   GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   //:END*/

   //:// <DisplaySection>
   //:FOR EACH mSPLDef.DisplaySection 
   RESULT = SetCursorFirstEntity( mSPLDef, "DisplaySection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szSectionName = mSPLDef.DisplaySection.XML_SectionName 
      {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
      StringBuilder sb_szSectionName;
      if ( szSectionName == null )
         sb_szSectionName = new StringBuilder( 32 );
      else
         sb_szSectionName = new StringBuilder( szSectionName );
             GetVariableFromAttribute( sb_szSectionName, mi_lTempInteger_13, 'S', 51, mSPLDef, "DisplaySection", "XML_SectionName", "", 0 );
      lTempInteger_13 = mi_lTempInteger_13.intValue( );
      szSectionName = sb_szSectionName.toString( );}
      //:szOutputLine = "         <" + szSectionName + ">"
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringCopy( sb_szOutputLine, 1, 0, "         <", 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, szSectionName, 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, ">", 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
      //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
      omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
      //:szIndentation = "            "
       {StringBuilder sb_szIndentation;
      if ( szIndentation == null )
         sb_szIndentation = new StringBuilder( 32 );
      else
         sb_szIndentation = new StringBuilder( szIndentation );
            ZeidonStringCopy( sb_szIndentation, 1, 0, "            ", 1, 0, 51 );
      szIndentation = sb_szIndentation.toString( );}
      //:szValue = mSPLDef.DisplaySection.Title 
      {MutableInt mi_lTempInteger_14 = new MutableInt( lTempInteger_14 );
      StringBuilder sb_szValue;
      if ( szValue == null )
         sb_szValue = new StringBuilder( 32 );
      else
         sb_szValue = new StringBuilder( szValue );
             GetVariableFromAttribute( sb_szValue, mi_lTempInteger_14, 'S', 5001, mSPLDef, "DisplaySection", "Title", "", 0 );
      lTempInteger_14 = mi_lTempInteger_14.intValue( );
      szValue = sb_szValue.toString( );}
      //:GenerateAttribute( mSPLDef, lFileHandle, "Title", szIndentation, szValue )
      omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "Title", szIndentation, szValue );

      //:// <DisplayStatement>
      //:FOR EACH mSPLDef.DisplayStatement 
      RESULT = SetCursorFirstEntity( mSPLDef, "DisplayStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:szStatementName = mSPLDef.DisplayStatement.XML_StatementName 
         {MutableInt mi_lTempInteger_15 = new MutableInt( lTempInteger_15 );
         StringBuilder sb_szStatementName;
         if ( szStatementName == null )
            sb_szStatementName = new StringBuilder( 32 );
         else
            sb_szStatementName = new StringBuilder( szStatementName );
                   GetVariableFromAttribute( sb_szStatementName, mi_lTempInteger_15, 'S', 51, mSPLDef, "DisplayStatement", "XML_StatementName", "", 0 );
         lTempInteger_15 = mi_lTempInteger_15.intValue( );
         szStatementName = sb_szStatementName.toString( );}
         //:szOutputLine = "            <" + szStatementName + ">"
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringCopy( sb_szOutputLine, 1, 0, "            <", 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringConcat( sb_szOutputLine, 1, 0, szStatementName, 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringConcat( sb_szOutputLine, 1, 0, ">", 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
         //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
         omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
         //:szIndentation = "               "
          {StringBuilder sb_szIndentation;
         if ( szIndentation == null )
            sb_szIndentation = new StringBuilder( 32 );
         else
            sb_szIndentation = new StringBuilder( szIndentation );
                  ZeidonStringCopy( sb_szIndentation, 1, 0, "               ", 1, 0, 51 );
         szIndentation = sb_szIndentation.toString( );}
         //:szValue = mSPLDef.DisplayStatement.Title 
         {MutableInt mi_lTempInteger_16 = new MutableInt( lTempInteger_16 );
         StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                   GetVariableFromAttribute( sb_szValue, mi_lTempInteger_16, 'S', 5001, mSPLDef, "DisplayStatement", "Title", "", 0 );
         lTempInteger_16 = mi_lTempInteger_16.intValue( );
         szValue = sb_szValue.toString( );}
         //:GenerateAttribute( mSPLDef, lFileHandle, "Title", szIndentation, szValue )
         omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "Title", szIndentation, szValue );
         //:szValue = mSPLDef.DisplayStatement.Text  
         {MutableInt mi_lTempInteger_17 = new MutableInt( lTempInteger_17 );
         StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                   GetVariableFromAttribute( sb_szValue, mi_lTempInteger_17, 'S', 5001, mSPLDef, "DisplayStatement", "Text", "", 0 );
         lTempInteger_17 = mi_lTempInteger_17.intValue( );
         szValue = sb_szValue.toString( );}
         //:GenerateAttribute( mSPLDef, lFileHandle, "Text", szIndentation, szValue )
         omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "Text", szIndentation, szValue );
         //:szStatementName = mSPLDef.DisplayStatement.XML_StatementName 
         {MutableInt mi_lTempInteger_18 = new MutableInt( lTempInteger_18 );
         StringBuilder sb_szStatementName;
         if ( szStatementName == null )
            sb_szStatementName = new StringBuilder( 32 );
         else
            sb_szStatementName = new StringBuilder( szStatementName );
                   GetVariableFromAttribute( sb_szStatementName, mi_lTempInteger_18, 'S', 51, mSPLDef, "DisplayStatement", "XML_StatementName", "", 0 );
         lTempInteger_18 = mi_lTempInteger_18.intValue( );
         szStatementName = sb_szStatementName.toString( );}
         //:szOutputLine = "            </" + szStatementName + ">"
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringCopy( sb_szOutputLine, 1, 0, "            </", 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringConcat( sb_szOutputLine, 1, 0, szStatementName, 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringConcat( sb_szOutputLine, 1, 0, ">", 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
         //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
         omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
         RESULT = SetCursorNextEntity( mSPLDef, "DisplayStatement", "" );
      } 

      //:END

      //:// <DisplayUsageColumn1>
      //:FOR EACH mSPLDef.DisplayUsageColumn1 
      RESULT = SetCursorFirstEntity( mSPLDef, "DisplayUsageColumn1", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:szOutputLine = "            <DisplayUsageColumn1>"
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringCopy( sb_szOutputLine, 1, 0, "            <DisplayUsageColumn1>", 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
         //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
         omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
         //:szIndentation = "               "
          {StringBuilder sb_szIndentation;
         if ( szIndentation == null )
            sb_szIndentation = new StringBuilder( 32 );
         else
            sb_szIndentation = new StringBuilder( szIndentation );
                  ZeidonStringCopy( sb_szIndentation, 1, 0, "               ", 1, 0, 51 );
         szIndentation = sb_szIndentation.toString( );}
         //:szValue = mSPLDef.DisplayUsageColumn1.Name  
         {MutableInt mi_lTempInteger_19 = new MutableInt( lTempInteger_19 );
         StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                   GetVariableFromAttribute( sb_szValue, mi_lTempInteger_19, 'S', 5001, mSPLDef, "DisplayUsageColumn1", "Name", "", 0 );
         lTempInteger_19 = mi_lTempInteger_19.intValue( );
         szValue = sb_szValue.toString( );}
         //:GenerateAttribute( mSPLDef, lFileHandle, "Name", szIndentation, szValue )
         omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "Name", szIndentation, szValue );
         //:szOutputLine = "            </DisplayUsageColumn1>"
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringCopy( sb_szOutputLine, 1, 0, "            </DisplayUsageColumn1>", 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
         //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
         omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
         RESULT = SetCursorNextEntity( mSPLDef, "DisplayUsageColumn1", "" );
      } 

      //:END

      //:// <DisplayUsageColumn2>
      //:FOR EACH mSPLDef.DisplayUsageColumn2 
      RESULT = SetCursorFirstEntity( mSPLDef, "DisplayUsageColumn2", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:szOutputLine = "            <DisplayUsageColumn2>"
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringCopy( sb_szOutputLine, 1, 0, "            <DisplayUsageColumn2>", 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
         //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
         omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
         //:szIndentation = "               "
          {StringBuilder sb_szIndentation;
         if ( szIndentation == null )
            sb_szIndentation = new StringBuilder( 32 );
         else
            sb_szIndentation = new StringBuilder( szIndentation );
                  ZeidonStringCopy( sb_szIndentation, 1, 0, "               ", 1, 0, 51 );
         szIndentation = sb_szIndentation.toString( );}
         //:szValue = mSPLDef.DisplayUsageColumn2.Name  
         {MutableInt mi_lTempInteger_20 = new MutableInt( lTempInteger_20 );
         StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                   GetVariableFromAttribute( sb_szValue, mi_lTempInteger_20, 'S', 5001, mSPLDef, "DisplayUsageColumn2", "Name", "", 0 );
         lTempInteger_20 = mi_lTempInteger_20.intValue( );
         szValue = sb_szValue.toString( );}
         //:GenerateAttribute( mSPLDef, lFileHandle, "Name", szIndentation, szValue )
         omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "Name", szIndentation, szValue );
         //:szOutputLine = "            </DisplayUsageColumn2>"
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringCopy( sb_szOutputLine, 1, 0, "            </DisplayUsageColumn2>", 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
         //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
         omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
         RESULT = SetCursorNextEntity( mSPLDef, "DisplayUsageColumn2", "" );
      } 

      //:END

      //:// <DisplayUsageColumn3>
      //:FOR EACH mSPLDef.DisplayUsageColumn3 
      RESULT = SetCursorFirstEntity( mSPLDef, "DisplayUsageColumn3", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:szOutputLine = "            <DisplayUsageColumn3>"
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringCopy( sb_szOutputLine, 1, 0, "            <DisplayUsageColumn3>", 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
         //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
         omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
         //:szIndentation = "               "
          {StringBuilder sb_szIndentation;
         if ( szIndentation == null )
            sb_szIndentation = new StringBuilder( 32 );
         else
            sb_szIndentation = new StringBuilder( szIndentation );
                  ZeidonStringCopy( sb_szIndentation, 1, 0, "               ", 1, 0, 51 );
         szIndentation = sb_szIndentation.toString( );}
         //:szValue = mSPLDef.DisplayUsageColumn3.Name  
         {MutableInt mi_lTempInteger_21 = new MutableInt( lTempInteger_21 );
         StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                   GetVariableFromAttribute( sb_szValue, mi_lTempInteger_21, 'S', 5001, mSPLDef, "DisplayUsageColumn3", "Name", "", 0 );
         lTempInteger_21 = mi_lTempInteger_21.intValue( );
         szValue = sb_szValue.toString( );}
         //:GenerateAttribute( mSPLDef, lFileHandle, "Name", szIndentation, szValue )
         omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "Name", szIndentation, szValue );
         //:szOutputLine = "            </DisplayUsageColumn3>"
          {StringBuilder sb_szOutputLine;
         if ( szOutputLine == null )
            sb_szOutputLine = new StringBuilder( 32 );
         else
            sb_szOutputLine = new StringBuilder( szOutputLine );
                  ZeidonStringCopy( sb_szOutputLine, 1, 0, "            </DisplayUsageColumn3>", 1, 0, 5001 );
         szOutputLine = sb_szOutputLine.toString( );}
         //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
         omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
         RESULT = SetCursorNextEntity( mSPLDef, "DisplayUsageColumn3", "" );
      } 

      //:END

      //:szOutputLine = "         </" + szSectionName + ">"
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringCopy( sb_szOutputLine, 1, 0, "         </", 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, szSectionName, 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringConcat( sb_szOutputLine, 1, 0, ">", 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
      //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
      omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
      RESULT = SetCursorNextEntity( mSPLDef, "DisplaySection", "" );
   } 

   //:END

   //:// <SPLD_HumanHazardSection>
   //:FOR EACH mSPLDef.SPLD_HumanHazardSection 
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_HumanHazardSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szOutputLine = "         <SPLD_HumanHazardSection>"
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringCopy( sb_szOutputLine, 1, 0, "         <SPLD_HumanHazardSection>", 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
      //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
      omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
      //:szIndentation = "            "
       {StringBuilder sb_szIndentation;
      if ( szIndentation == null )
         sb_szIndentation = new StringBuilder( 32 );
      else
         sb_szIndentation = new StringBuilder( szIndentation );
            ZeidonStringCopy( sb_szIndentation, 1, 0, "            ", 1, 0, 51 );
      szIndentation = sb_szIndentation.toString( );}
      //:szValue = mSPLDef.SPLD_HumanHazardSection.EPA_SignalWord 
      {MutableInt mi_lTempInteger_22 = new MutableInt( lTempInteger_22 );
      StringBuilder sb_szValue;
      if ( szValue == null )
         sb_szValue = new StringBuilder( 32 );
      else
         sb_szValue = new StringBuilder( szValue );
             GetVariableFromAttribute( sb_szValue, mi_lTempInteger_22, 'S', 5001, mSPLDef, "SPLD_HumanHazardSection", "EPA_SignalWord", "", 0 );
      lTempInteger_22 = mi_lTempInteger_22.intValue( );
      szValue = sb_szValue.toString( );}
      //:GenerateAttribute( mSPLDef, lFileHandle, "EPA_SignalWord", szIndentation, szValue )
      omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "EPA_SignalWord", szIndentation, szValue );
      //:szValue = mSPLDef.SPLD_HumanHazardSection.EPA_ChildHazardWarning 
      {MutableInt mi_lTempInteger_23 = new MutableInt( lTempInteger_23 );
      StringBuilder sb_szValue;
      if ( szValue == null )
         sb_szValue = new StringBuilder( 32 );
      else
         sb_szValue = new StringBuilder( szValue );
             GetVariableFromAttribute( sb_szValue, mi_lTempInteger_23, 'S', 5001, mSPLDef, "SPLD_HumanHazardSection", "EPA_ChildHazardWarning", "", 0 );
      lTempInteger_23 = mi_lTempInteger_23.intValue( );
      szValue = sb_szValue.toString( );}
      //:GenerateAttribute( mSPLDef, lFileHandle, "EPA_ChildHazardWarning", szIndentation, szValue )
      omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "EPA_ChildHazardWarning", szIndentation, szValue );
      //:szValue = mSPLDef.SPLD_HumanHazardSection.PrecautionaryStatement 
      {MutableInt mi_lTempInteger_24 = new MutableInt( lTempInteger_24 );
      StringBuilder sb_szValue;
      if ( szValue == null )
         sb_szValue = new StringBuilder( 32 );
      else
         sb_szValue = new StringBuilder( szValue );
             GetVariableFromAttribute( sb_szValue, mi_lTempInteger_24, 'S', 5001, mSPLDef, "SPLD_HumanHazardSection", "PrecautionaryStatement", "", 0 );
      lTempInteger_24 = mi_lTempInteger_24.intValue( );
      szValue = sb_szValue.toString( );}
      //:GenerateAttribute( mSPLDef, lFileHandle, "PrecautionaryStatement", szIndentation, szValue )
      omSPLDef_GenerateAttribute( mSPLDef, lFileHandle, "PrecautionaryStatement", szIndentation, szValue );
      //:szOutputLine = "         </SPLD_HumanHazardSection>"
       {StringBuilder sb_szOutputLine;
      if ( szOutputLine == null )
         sb_szOutputLine = new StringBuilder( 32 );
      else
         sb_szOutputLine = new StringBuilder( szOutputLine );
            ZeidonStringCopy( sb_szOutputLine, 1, 0, "         </SPLD_HumanHazardSection>", 1, 0, 5001 );
      szOutputLine = sb_szOutputLine.toString( );}
      //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
      omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_HumanHazardSection", "" );
   } 

   //:END

   //:// Build Footer entries.
   //:szOutputLine = "      </SubregPhysicalLabelDef>"
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringCopy( sb_szOutputLine, 1, 0, "      </SubregPhysicalLabelDef>", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
   //:szOutputLine = "   </zOI>"
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringCopy( sb_szOutputLine, 1, 0, "   </zOI>", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );

   //:// Close the XML file.
   //:SysCloseFile( mSPLDef, lFileHandle, 0 )
   try
   {
       m_KZOEP1AA.SysCloseFile( mSPLDef, lFileHandle, null );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   return( 0 );
// END
} 


//:LOCAL OPERATION
private int 
omSPLDef_GenerateLine( View     mSPLDef,
                       int      lFileHandle,
                       String   szOutputLine )
{

   //:GenerateLine( VIEW mSPLDef BASED ON LOD mSPLDef,
   //:           INTEGER lFileHandle,
   //:           STRING ( 5000 ) szOutputLine )

   //:// This is just a SysWriteLine with an options TraceLineS statement.
   //://TraceLineS( "*** Line: ", szOutputLine )
   //:SysWriteLine( mSPLDef, lFileHandle, szOutputLine )
   try
   {
       m_KZOEP1AA.SysWriteLine( mSPLDef, lFileHandle, szOutputLine );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:GenerateAttribute( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                   INTEGER lFileHandle,
//:                   STRING ( 32 )   szAttributeName,
//:                   STRING ( 50 )   szLeadingBlanks,
//:                   STRING ( 5000 ) szValue )

//:   STRING ( 5000 ) szOutputLine
private int 
omSPLDef_GenerateAttribute( View     mSPLDef,
                            int      lFileHandle,
                            String   szAttributeName,
                            String   szLeadingBlanks,
                            String   szValue )
{
   String   szOutputLine = null;


   //:// Format an XML line for an attribute value.
   //:szOutputLine = szLeadingBlanks + "<" + szAttributeName + ">" + szValue + "</" + szAttributeName + ">"
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringCopy( sb_szOutputLine, 1, 0, szLeadingBlanks, 1, 0, 5001 );
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
      ZeidonStringConcat( sb_szOutputLine, 1, 0, szValue, 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
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
      ZeidonStringConcat( sb_szOutputLine, 1, 0, szAttributeName, 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringConcat( sb_szOutputLine, 1, 0, ">", 1, 0, 5001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:GenerateLine( mSPLDef, lFileHandle, szOutputLine )
   omSPLDef_GenerateLine( mSPLDef, lFileHandle, szOutputLine );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ComputeTopPositions( VIEW mSPLDef BASED ON LOD mSPLDef )

//:   DECIMAL LastBlockTopPosition
public int 
omSPLDef_ComputeTopPositions( View     mSPLDef )
{
   double  LastBlockTopPosition = 0.0;
   //:DECIMAL LastSubBlockTopPosition
   double  LastSubBlockTopPosition = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;
   double  dTempDecimal_4 = 0.0;
   double  dTempDecimal_5 = 0.0;


   //:// Calculate the Top position for each Block and SubBlock. SubBlock positions are relative
   //:// to the parent Block
   //:// If the Top Position is specified, we'll use it. Otherwise, we'll use the last position of the 
   //:// previous Block or SubBlock entry plust its Height.
   //:FOR EACH mSPLDef.LLD_Panel WITHIN mSPLDef.SubregPhysicalLabelDef  
   RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Panel", "SubregPhysicalLabelDef" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:LastBlockTopPosition = 0
      LastBlockTopPosition = 0;
      //:FOR EACH mSPLDef.LLD_Block 
      RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Block", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSPLDef.LLD_Block.Top != ""
         if ( CompareAttributeToString( mSPLDef, "LLD_Block", "Top", "" ) != 0 )
         { 
            //:mSPLDef.LLD_Block.wComputedTopPosition = mSPLDef.LLD_Block.Top 
            SetAttributeFromAttribute( mSPLDef, "LLD_Block", "wComputedTopPosition", mSPLDef, "LLD_Block", "Top" );
            //:LastBlockTopPosition = mSPLDef.LLD_Block.Top + mSPLDef.LLD_Block.Height
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mSPLDef, "LLD_Block", "Top" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                         GetDecimalFromAttribute( md_dTempDecimal_1, mSPLDef, "LLD_Block", "Height" );
            dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
            LastBlockTopPosition = dTempDecimal_0 + dTempDecimal_1;
            //:ELSE
         } 
         else
         { 
            //:mSPLDef.LLD_Block.wComputedTopPosition = LastBlockTopPosition
            SetAttributeFromDecimal( mSPLDef, "LLD_Block", "wComputedTopPosition", LastBlockTopPosition );
            //:LastBlockTopPosition = LastBlockTopPosition + mSPLDef.LLD_Block.Height
            {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                         GetDecimalFromAttribute( md_dTempDecimal_2, mSPLDef, "LLD_Block", "Height" );
            dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
            LastBlockTopPosition = LastBlockTopPosition + dTempDecimal_2;
         } 

         //:END
         //:LastSubBlockTopPosition = 0
         LastSubBlockTopPosition = 0;
         //:FOR EACH mSPLDef.LLD_SubBlock 
         RESULT = SetCursorFirstEntity( mSPLDef, "LLD_SubBlock", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mSPLDef.LLD_SubBlock.Top != ""
            if ( CompareAttributeToString( mSPLDef, "LLD_SubBlock", "Top", "" ) != 0 )
            { 
               //:mSPLDef.LLD_SubBlock.wComputedTopPosition = mSPLDef.LLD_SubBlock.Top 
               SetAttributeFromAttribute( mSPLDef, "LLD_SubBlock", "wComputedTopPosition", mSPLDef, "LLD_SubBlock", "Top" );
               //:LastSubBlockTopPosition = mSPLDef.LLD_SubBlock.Top + mSPLDef.LLD_SubBlock.Height 
               {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                               GetDecimalFromAttribute( md_dTempDecimal_3, mSPLDef, "LLD_SubBlock", "Top" );
               dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
               {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                               GetDecimalFromAttribute( md_dTempDecimal_4, mSPLDef, "LLD_SubBlock", "Height" );
               dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
               LastSubBlockTopPosition = dTempDecimal_3 + dTempDecimal_4;
               //:ELSE
            } 
            else
            { 
               //:mSPLDef.LLD_SubBlock.wComputedTopPosition = LastSubBlockTopPosition
               SetAttributeFromDecimal( mSPLDef, "LLD_SubBlock", "wComputedTopPosition", LastSubBlockTopPosition );
               //:LastSubBlockTopPosition = LastSubBlockTopPosition + mSPLDef.LLD_SubBlock.Height
               {MutableDouble md_dTempDecimal_5 = new MutableDouble( dTempDecimal_5 );
                               GetDecimalFromAttribute( md_dTempDecimal_5, mSPLDef, "LLD_SubBlock", "Height" );
               dTempDecimal_5 = md_dTempDecimal_5.doubleValue( );}
               LastSubBlockTopPosition = LastSubBlockTopPosition + dTempDecimal_5;
            } 

            RESULT = SetCursorNextEntity( mSPLDef, "LLD_SubBlock", "" );
            //:END
         } 

         RESULT = SetCursorNextEntity( mSPLDef, "LLD_Block", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "LLD_Panel", "SubregPhysicalLabelDef" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:OBJECT CONSTRAINT OPERATION
public int 
omSPLDef_ObjectConstraints( View     mSPLDef,
                            Integer   Event,
                            Integer   State )
{
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;

   //:ObjectConstraints( VIEW mSPLDef BASED ON LOD mSPLDef,
   //:                SHORT Event,
   //:                SHORT State )

   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:// Compute work position entries for SubBlocks and put them in position order.
         //:ComputeTopPositions( mSPLDef )
         omSPLDef_ComputeTopPositions( mSPLDef );
         //:FOR EACH mSPLDef.LLD_Page 
         RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Page", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:FOR EACH mSPLDef.LLD_Panel 
            RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Panel", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:FOR EACH mSPLDef.LLD_Block 
               RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Block", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:OrderEntityForView( mSPLDef, "LLD_SubBlock", "wComputedTopPosition A" )
                  OrderEntityForView( mSPLDef, "LLD_SubBlock", "wComputedTopPosition A" );
                  RESULT = SetCursorNextEntity( mSPLDef, "LLD_Block", "" );
               } 

               RESULT = SetCursorNextEntity( mSPLDef, "LLD_Panel", "" );
               //:END
            } 

            RESULT = SetCursorNextEntity( mSPLDef, "LLD_Page", "" );
            //:END 
         } 

         //:END
         //:SET CURSOR FIRST mSPLDef.LLD_Page  
         RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Page", "" );

         //:// Make sure the new SPLD_UsageType subobject structure is used.
         //:IF mSPLDef.SPLD_UsageType DOES NOT EXIST
         lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "SPLD_UsageType" );
         if ( lTempInteger_0 != 0 )
         { 
            //:FOR EACH mSPLDef.SPLD_UsageDelete 
            RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_UsageDelete", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:SET CURSOR FIRST mSPLDef.SPLD_UsageType WHERE mSPLDef.SPLD_UsageType.UsageType = mSPLDef.SPLD_UsageDelete.UsageType 
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, mSPLDef, "SPLD_UsageDelete", "UsageType" );
               szTempString_0 = sb_szTempString_0.toString( );}
               RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_UsageType", "UsageType", szTempString_0, "" );
               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:CREATE ENTITY mSPLDef.SPLD_UsageType 
                  RESULT = CreateEntity( mSPLDef, "SPLD_UsageType", zPOS_AFTER );
                  //:mSPLDef.SPLD_UsageType.UsageType = mSPLDef.SPLD_UsageDelete.UsageType  
                  SetAttributeFromAttribute( mSPLDef, "SPLD_UsageType", "UsageType", mSPLDef, "SPLD_UsageDelete", "UsageType" );
               } 

               //:END 
               //:CREATE ENTITY mSPLDef.SPLD_Usage 
               RESULT = CreateEntity( mSPLDef, "SPLD_Usage", zPOS_AFTER );
               //:SetMatchingAttributesByName( mSPLDef, "SPLD_Usage", mSPLDef, "SPLD_UsageDelete", zSET_NULL )
               SetMatchingAttributesByName( mSPLDef, "SPLD_Usage", mSPLDef, "SPLD_UsageDelete", zSET_NULL );
               //:INCLUDE mSPLDef.S_Usage FROM mSPLDef.S_UsageDelete 
               RESULT = IncludeSubobjectFromSubobject( mSPLDef, "S_Usage", mSPLDef, "S_UsageDelete", zPOS_AFTER );
               RESULT = SetCursorNextEntity( mSPLDef, "SPLD_UsageDelete", "" );
            } 

            //:END
         } 

         //:END
         break ;

      //:  /* end zOCE_ACTIVATE */
      //:OF   zOCE_ACTIVATE_EMPTY:
      case zOCE_ACTIVATE_EMPTY :
         break ;

      //:  /* end zOCE_ACTIVATE_EMPTY */
      //:OF   zOCE_COMMIT:
      case zOCE_COMMIT :
         break ;

      //:  /* end zOCE_COMMIT */
      //:OF   zOCE_DROPOI:
      case zOCE_DROPOI :
         break ;
   } 


   //:     /* end zOCE_DROPOI */
   //:END  /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omSPLDef_BuildUsageEntriesFrSLC( View     mSPLDef,
                                 View     SourceSLC )
{
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;

   //:BuildUsageEntriesFrSLC( VIEW mSPLDef   BASED ON LOD mSPLDef,
   //:                     VIEW SourceSLC BASED ON LOD mSubLC )

   //:// Build the Usage, Directions for Use and Marketing subobject structures from the creating SourceSLC.

   //:// Delete current entries.
   //:FOR EACH mSPLDef.SPLD_Usage 
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mSPLDef.SPLD_Usage NONE 
      RESULT = DeleteEntity( mSPLDef, "SPLD_Usage", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_Usage", "" );
   } 

   //:END

   //:// Build Usage Entries (Surface, Application Type, Area of Use, Organism Claim)
   //:FOR EACH SourceSLC.S_Usage WITHIN SourceSLC.SubregLabelContent 
   RESULT = SetCursorFirstEntity( SourceSLC, "S_Usage", "SubregLabelContent" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSPLDef.SPLD_Usage
      RESULT = CreateEntity( mSPLDef, "SPLD_Usage", zPOS_AFTER );
      //:SetMatchingAttributesByName( mSPLDef, "SPLD_Usage", SourceSLC, "S_Usage", zSET_NULL )
      SetMatchingAttributesByName( mSPLDef, "SPLD_Usage", SourceSLC, "S_Usage", zSET_NULL );
      //:INCLUDE mSPLDef.S_Usage FROM SourceSLC.S_Usage 
      RESULT = IncludeSubobjectFromSubobject( mSPLDef, "S_Usage", SourceSLC, "S_Usage", zPOS_AFTER );
      RESULT = SetCursorNextEntity( SourceSLC, "S_Usage", "SubregLabelContent" );
   } 

   //:END

   //:// Build Directions for Use Usage Entries.
   //:FOR EACH SourceSLC.S_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST mSPLDef.S_DirectionsForUseSection WITHIN mSPLDef.SubregPhysicalLabelDef 
      //:           WHERE mSPLDef.S_DirectionsForUseSection.ID = SourceSLC.S_DirectionsForUseSection.ID 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, SourceSLC, "S_DirectionsForUseSection", "ID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( mSPLDef, "S_DirectionsForUseSection", "ID", lTempInteger_0, "SubregPhysicalLabelDef" );
      //:FOR EACH SourceSLC.S_DirectionsForUseStatement
      RESULT = SetCursorFirstEntity( SourceSLC, "S_DirectionsForUseStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:SET CURSOR FIRST mSPLDef.S_DirectionsForUseStatement WITHIN mSPLDef.SPLD_DirectionsForUseSection 
         //:           WHERE mSPLDef.S_DirectionsForUseStatement.ID = SourceSLC.S_DirectionsForUseStatement.ID 
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                   GetIntegerFromAttribute( mi_lTempInteger_1, SourceSLC, "S_DirectionsForUseStatement", "ID" );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mSPLDef, "S_DirectionsForUseStatement", "ID", lTempInteger_1, "SPLD_DirectionsForUseSection" );

         //:// Remove current entries.
         //:FOR EACH mSPLDef.SPLD_DirectionsUsageOrdering
         RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_DirectionsUsageOrdering", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:DELETE ENTITY mSPLDef.SPLD_DirectionsUsageOrdering NONE 
            RESULT = DeleteEntity( mSPLDef, "SPLD_DirectionsUsageOrdering", zREPOS_NONE );
            RESULT = SetCursorNextEntity( mSPLDef, "SPLD_DirectionsUsageOrdering", "" );
         } 

         //:END

         //:// Add entries from SLC.
         //:FOR EACH SourceSLC.S_DirectionsUsageOrdering 
         RESULT = SetCursorFirstEntity( SourceSLC, "S_DirectionsUsageOrdering", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST mSPLDef.S_Usage WITHIN mSPLDef.SubregPhysicalLabelDef 
            //:           WHERE mSPLDef.S_Usage.ID = SourceSLC.S_DirectionsUsage.ID 
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, SourceSLC, "S_DirectionsUsage", "ID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mSPLDef, "S_Usage", "ID", lTempInteger_2, "SubregPhysicalLabelDef" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:CREATE ENTITY mSPLDef.SPLD_DirectionsUsageOrdering
               RESULT = CreateEntity( mSPLDef, "SPLD_DirectionsUsageOrdering", zPOS_AFTER );
               //:INCLUDE mSPLDef.SPLD_DirectionsUsage FROM mSPLDef.SPLD_Usage
               RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLD_DirectionsUsage", mSPLDef, "SPLD_Usage", zPOS_AFTER );
            } 

            RESULT = SetCursorNextEntity( SourceSLC, "S_DirectionsUsageOrdering", "" );
            //:END
         } 

         RESULT = SetCursorNextEntity( SourceSLC, "S_DirectionsForUseStatement", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// Build Marketing Entries.
   //:FOR EACH SourceSLC.S_MarketingSection 
   RESULT = SetCursorFirstEntity( SourceSLC, "S_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST mSPLDef.S_MarketingSection WITHIN mSPLDef.SubregPhysicalLabelDef 
      //:           WHERE mSPLDef.S_MarketingSection.ID = SourceSLC.S_MarketingSection.ID 
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
             GetIntegerFromAttribute( mi_lTempInteger_3, SourceSLC, "S_MarketingSection", "ID" );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( mSPLDef, "S_MarketingSection", "ID", lTempInteger_3, "SubregPhysicalLabelDef" );
      //:FOR EACH SourceSLC.S_MarketingStatement
      RESULT = SetCursorFirstEntity( SourceSLC, "S_MarketingStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:SET CURSOR FIRST mSPLDef.S_MarketingStatement WITHIN mSPLDef.SPLD_MarketingSection 
         //:           WHERE mSPLDef.S_MarketingStatement.ID = SourceSLC.S_MarketingStatement.ID 
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                   GetIntegerFromAttribute( mi_lTempInteger_4, SourceSLC, "S_MarketingStatement", "ID" );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mSPLDef, "S_MarketingStatement", "ID", lTempInteger_4, "SPLD_MarketingSection" );

         //:// Remove current entries.
         //:FOR EACH mSPLDef.SPLD_MarketingUsageOrdering
         RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:DELETE ENTITY mSPLDef.SPLD_MarketingUsageOrdering NONE 
            RESULT = DeleteEntity( mSPLDef, "SPLD_MarketingUsageOrdering", zREPOS_NONE );
            RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingUsageOrdering", "" );
         } 

         //:END

         //:// Add entries from SLC.
         //:FOR EACH SourceSLC.S_MarketingUsageOrdering 
         RESULT = SetCursorFirstEntity( SourceSLC, "S_MarketingUsageOrdering", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST mSPLDef.S_Usage WITHIN mSPLDef.SubregPhysicalLabelDef 
            //:           WHERE mSPLDef.S_Usage.ID = SourceSLC.S_MarketingUsage.ID 
            {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                         GetIntegerFromAttribute( mi_lTempInteger_5, SourceSLC, "S_MarketingUsage", "ID" );
            lTempInteger_5 = mi_lTempInteger_5.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mSPLDef, "S_Usage", "ID", lTempInteger_5, "SubregPhysicalLabelDef" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:CREATE ENTITY mSPLDef.SPLD_MarketingUsageOrdering
               RESULT = CreateEntity( mSPLDef, "SPLD_MarketingUsageOrdering", zPOS_AFTER );
               //:INCLUDE mSPLDef.SPLD_MarketingUsage FROM mSPLDef.SPLD_Usage
               RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLD_MarketingUsage", mSPLDef, "SPLD_Usage", zPOS_AFTER );
            } 

            RESULT = SetCursorNextEntity( SourceSLC, "S_MarketingUsageOrdering", "" );
            //:END
         } 

         RESULT = SetCursorNextEntity( SourceSLC, "S_MarketingStatement", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_MarketingSection", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omSPLDef_CheckAddKeywordEntry( View     mSPLDefBlock,
                               String   szKeywordName )
{
   int      RESULT = 0;

   //:CheckAddKeywordEntry( VIEW mSPLDefBlock BASED ON LOD mSPLDef,
   //:                   STRING ( 50 ) szKeywordName )

   //:// Make sure that the entry for the Keyword passed in exists.
   //:SET CURSOR FIRST mSPLDefBlock.LLD_SpecialSectionAttribute WHERE mSPLDefBlock.LLD_SpecialSectionAttribute.Name = szKeywordName
   RESULT = SetCursorFirstEntityByString( mSPLDefBlock, "LLD_SpecialSectionAttribute", "Name", szKeywordName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:TraceLineS( "CheckAddKeyordEntry adding: ", szKeywordName )
      TraceLineS( "CheckAddKeyordEntry adding: ", szKeywordName );
      //:SET CURSOR LAST mSPLDefBlock.LLD_SpecialSectionAttribute  
      RESULT = SetCursorLastEntity( mSPLDefBlock, "LLD_SpecialSectionAttribute", "" );
      //:CREATE ENTITY mSPLDefBlock.LLD_SpecialSectionAttribute
      RESULT = CreateEntity( mSPLDefBlock, "LLD_SpecialSectionAttribute", zPOS_AFTER );
      //:mSPLDefBlock.LLD_SpecialSectionAttribute.Name = szKeywordName
      SetAttributeFromString( mSPLDefBlock, "LLD_SpecialSectionAttribute", "Name", szKeywordName );
      //:CREATE ENTITY mSPLDefBlock.LLD_SpecialSectionAttrBlock  
      RESULT = CreateEntity( mSPLDefBlock, "LLD_SpecialSectionAttrBlock", zPOS_AFTER );
      //:ELSE
   } 
   else
   { 
      //:TraceLineS( "CheckAddKeyordEntry located: ", szKeywordName )
      TraceLineS( "CheckAddKeyordEntry located: ", szKeywordName );
   } 

   //:END 
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
public int 
omSPLDef_SetUpKeywordEntries( View     mSPLDefBlock,
                              String   szSectionType )
{

   //:SetUpKeywordEntries( VIEW mSPLDefBlock BASED ON LOD mSPLDef,
   //:                  STRING ( 50 ) szSectionType )

   //:// Make sure that the appropriate Keyword entries are set up for a given Section/Block Type.
   //:IF szSectionType = "HumanHazard" 
   if ( ZeidonStringCompare( szSectionType, 1, 0, "HumanHazard", 1, 0, 51 ) == 0 )
   { 
      //:// Human Hazard
      //:CheckAddKeywordEntry( mSPLDefBlock, "Hazards Warning" )
      omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Hazards Warning" );
      //:CheckAddKeywordEntry( mSPLDefBlock, "Hazards Signal Word" )
      omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Hazards Signal Word" );
      //:CheckAddKeywordEntry( mSPLDefBlock, "Hazards Precautionary" )
      omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Hazards Precautionary" );
      //:ELSE
   } 
   else
   { 
      //:IF szSectionType = "Ingredients" 
      if ( ZeidonStringCompare( szSectionType, 1, 0, "Ingredients", 1, 0, 51 ) == 0 )
      { 
         //:// Ingredients
         //:CheckAddKeywordEntry( mSPLDefBlock, "Title" )
         omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Title" );
         //:CheckAddKeywordEntry( mSPLDefBlock, "Ingredients Items" )
         omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Ingredients Items" );
         //:CheckAddKeywordEntry( mSPLDefBlock, "Ingredients Inert" )
         omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Ingredients Inert" );
         //:CheckAddKeywordEntry( mSPLDefBlock, "Ingredients Total" ) 
         omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Ingredients Total" );
         //:ELSE
      } 
      else
      { 
         //:IF szSectionType = "DirectionsForUse" 
         if ( ZeidonStringCompare( szSectionType, 1, 0, "DirectionsForUse", 1, 0, 51 ) == 0 )
         { 
            //:// Directions for Use
            //:CheckAddKeywordEntry( mSPLDefBlock, "Title" )
            omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Title" );
            //:CheckAddKeywordEntry( mSPLDefBlock, "Text" )
            omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Text" );
            //:CheckAddKeywordEntry( mSPLDefBlock, "Header" )
            omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Header" );
            //:ELSE
         } 
         else
         { 
            //:IF szSectionType = "Marketing"
            if ( ZeidonStringCompare( szSectionType, 1, 0, "Marketing", 1, 0, 51 ) == 0 )
            { 
               //:// Marketing
               //:CheckAddKeywordEntry( mSPLDefBlock, "Title" )
               omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Title" );
               //:CheckAddKeywordEntry( mSPLDefBlock, "Text" )
               omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Text" );
               //:CheckAddKeywordEntry( mSPLDefBlock, "Column List" )
               omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Column List" );
               //:CheckAddKeywordEntry( mSPLDefBlock, "Header" )
               omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Header" );
               //:ELSE
            } 
            else
            { 
               //:IF szSectionType = "FirstAid"
               if ( ZeidonStringCompare( szSectionType, 1, 0, "FirstAid", 1, 0, 51 ) == 0 )
               { 
                  //:// First Aid
                  //:CheckAddKeywordEntry( mSPLDefBlock, "Title" )
                  omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Title" );
                  //:CheckAddKeywordEntry( mSPLDefBlock, "Text" )
                  omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Text" );
                  //:CheckAddKeywordEntry( mSPLDefBlock, "Header" )
                  omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Header" );
                  //:ELSE
               } 
               else
               { 
                  //:// Default
                  //:CheckAddKeywordEntry( mSPLDefBlock, "Title" )
                  omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Title" );
                  //:CheckAddKeywordEntry( mSPLDefBlock, "Text" )
                  omSPLDef_CheckAddKeywordEntry( mSPLDefBlock, "Text" );
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:END
   return( 0 );
//    
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dColorName( VIEW mSPLDef BASED ON LOD mSPLDef,
//:            STRING ( 32 ) InternalEntityStructure,
//:            STRING ( 32 ) InternalAttribStructure,
//:            SHORT GetOrSetFlag )

//:   STRING ( 32 ) szName
public int 
omSPLDef_dColorName( View     mSPLDef,
                     String InternalEntityStructure,
                     String InternalAttribStructure,
                     Integer   GetOrSetFlag )
{
   String   szName = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetStringFromAttribute( szName, mSPLDef, InternalEntityStructure, "Pantone" )
         {StringBuilder sb_szName;
         if ( szName == null )
            sb_szName = new StringBuilder( 32 );
         else
            sb_szName = new StringBuilder( szName );
                   GetStringFromAttribute( sb_szName, mSPLDef, InternalEntityStructure, "Pantone" );
         szName = sb_szName.toString( );}
         //:IF szName = ""
         if ( ZeidonStringCompare( szName, 1, 0, "", 1, 0, 33 ) == 0 )
         { 
            //:GetStringFromAttribute( szName, mSPLDef, InternalEntityStructure, "Name" )
            {StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                         GetStringFromAttribute( sb_szName, mSPLDef, InternalEntityStructure, "Name" );
            szName = sb_szName.toString( );}
         } 

         //:END
         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szName )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szName );
         //:RETURN 0
         if(8==8)return( 0 );

         //:/* end zDERIVED_GET */
         //:OF   zDERIVED_SET:
         case zDERIVED_SET :
            break ;
      } 


      //:  /* end zDERIVED_SET */
      //:END  /* case */
      return( 0 );
   } 



}
