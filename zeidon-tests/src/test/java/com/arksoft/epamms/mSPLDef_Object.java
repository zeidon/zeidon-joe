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

package com.arksoft.epamms;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.arksoft.epamms.ZGlobal1_Operation;

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
//:GenerateHTML_Label( VIEW mSPLDef BASED ON LOD mSPLDef )

//:   STRING ( 32000 ) szWriteBuffer
public int 
omSPLDef_GenerateHTML_Label( View     mSPLDef )
{
   String   szWriteBuffer = null;
   //:INTEGER lFile
   int      lFile = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:STRING ( 10 ) szSize
   String   szSize = null;
   //:STRING ( 32 ) szLastSectionType
   String   szLastSectionType = null;
   //:STRING ( 32 ) szVoid
   String   szVoid = null;
   //:STRING ( 2 )  szCount
   String   szCount = null;
   //:INTEGER       lCnt
   int      lCnt = 0;
   //:SHORT         nRC
   int      nRC = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;


   //:// Generate an HTML Label from the SPLD.

   //:// Open the File (use szWriteBuffer to hold FileName).
   //:SysMakeWebFileName( szWriteBuffer, mSPLDef, 2 )
   {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
       m_KZOEP1AA.SysMakeWebFileName( sb_szWriteBuffer, mSPLDef, 2 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:szWriteBuffer = szWriteBuffer + ".html"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, ".html", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:// szWriteBuffer = "c:\Program Files\Apache Group\Tomcat 5.5\webapps\ROOT\ePamms\HTML_Temp.html" // test purposes only
   //:TraceLineS( "Output Filename: ", szWriteBuffer )
   TraceLineS( "Output Filename: ", szWriteBuffer );
   //:lFile = SysOpenFile( mSPLDef, szWriteBuffer, COREFILE_DELETE )
   try
   {
       lFile = m_KZOEP1AA.SysOpenFile( mSPLDef, szWriteBuffer, COREFILE_DELETE );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:lFile = SysOpenFile( mSPLDef, szWriteBuffer, COREFILE_WRITE )
   try
   {
       lFile = m_KZOEP1AA.SysOpenFile( mSPLDef, szWriteBuffer, COREFILE_WRITE );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Put out header data.
   //:szWriteBuffer = "<!DOCTYPE HTML PUBLIC ^-//W3C//DTD HTML 4.01//EN^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<!DOCTYPE HTML PUBLIC ^-//W3C//DTD HTML 4.01//EN^>", 1, 0, 32001 );
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
   //:szWriteBuffer = "<html>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<html>", 1, 0, 32001 );
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
   //:szWriteBuffer = "<head>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<head>", 1, 0, 32001 );
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
   //:szWriteBuffer = "<meta http-equiv=^Content-Type^ content=^text/html; charset=iso-8859-1^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<meta http-equiv=^Content-Type^ content=^text/html; charset=iso-8859-1^>", 1, 0, 32001 );
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
   //:szWriteBuffer = "<link href=^" + mSPLDef.SPLD_Template.CSS_FileName + ".css^ rel=^stylesheet^ type=^text/css^>"
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 255, mSPLDef, "SPLD_Template", "CSS_FileName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<link href=^", 1, 0, 32001 );
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
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, ".css^ rel=^stylesheet^ type=^text/css^>", 1, 0, 32001 );
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
   //:szWriteBuffer = "</head>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "</head>", 1, 0, 32001 );
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
   //:szWriteBuffer = "<body>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<body>", 1, 0, 32001 );
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

   //:// Loop through each PANEL, creating a div for each Panel.
   //:lCnt = 1
   lCnt = 1;
   //:FOR EACH mSPLDef.SPLD_TemplatePanel
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_TemplatePanel", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Set up panel div.
      //:szWriteBuffer = "    <div id=^" + mSPLDef.SPLD_TemplatePanel.dPanelName
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
             GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 255, mSPLDef, "SPLD_TemplatePanel", "dPanelName", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szTempString_1 = sb_szTempString_1.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "    <div id=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTempString_1, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:szWriteBuffer = szWriteBuffer + "^ class=^panel^ style=^"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^ class=^panel^ style=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}

      //:szSize = mSPLDef.SPLD_TemplatePanel.dKerning
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szSize;
      if ( szSize == null )
         sb_szSize = new StringBuilder( 32 );
      else
         sb_szSize = new StringBuilder( szSize );
             GetVariableFromAttribute( sb_szSize, mi_lTempInteger_2, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "dKerning", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szSize = sb_szSize.toString( );}
      //:IF szSize != ""
      if ( ZeidonStringCompare( szSize, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + "letter-spacing:" + szSize + "; "
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "letter-spacing:", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSize, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "; ", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szSize = mSPLDef.SPLD_TemplatePanel.dLeading
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szSize;
      if ( szSize == null )
         sb_szSize = new StringBuilder( 32 );
      else
         sb_szSize = new StringBuilder( szSize );
             GetVariableFromAttribute( sb_szSize, mi_lTempInteger_3, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "dLeading", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szSize = sb_szSize.toString( );}
      //:IF szSize != ""
      if ( ZeidonStringCompare( szSize, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + "line-height:" + szSize + "; "
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "line-height:", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSize, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "; ", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szSize = mSPLDef.SPLD_TemplatePanel.SizeX
      {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
      StringBuilder sb_szSize;
      if ( szSize == null )
         sb_szSize = new StringBuilder( 32 );
      else
         sb_szSize = new StringBuilder( szSize );
             GetVariableFromAttribute( sb_szSize, mi_lTempInteger_4, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "SizeX", "", 0 );
      lTempInteger_4 = mi_lTempInteger_4.intValue( );
      szSize = sb_szSize.toString( );}
      //:IF szSize != ""
      if ( ZeidonStringCompare( szSize, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + "width:" + szSize + "px; "
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "width:", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSize, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "px; ", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szSize = mSPLDef.SPLD_TemplatePanel.PosX
      {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
      StringBuilder sb_szSize;
      if ( szSize == null )
         sb_szSize = new StringBuilder( 32 );
      else
         sb_szSize = new StringBuilder( szSize );
             GetVariableFromAttribute( sb_szSize, mi_lTempInteger_5, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "PosX", "", 0 );
      lTempInteger_5 = mi_lTempInteger_5.intValue( );
      szSize = sb_szSize.toString( );}
      //:IF szSize = ""
      if ( ZeidonStringCompare( szSize, 1, 0, "", 1, 0, 11 ) == 0 )
      { 
         //:szSize = "20"  //???
          {StringBuilder sb_szSize;
         if ( szSize == null )
            sb_szSize = new StringBuilder( 32 );
         else
            sb_szSize = new StringBuilder( szSize );
                  ZeidonStringCopy( sb_szSize, 1, 0, "20", 1, 0, 11 );
         szSize = sb_szSize.toString( );}
      } 

      //:END

      //:szWriteBuffer = szWriteBuffer + "margin-left:" + szSize + "px; ^>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "margin-left:", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSize, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "px; ^>", 1, 0, 32001 );
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

      //:// Generate HTML for each GROUP.
      //:FOR EACH mSPLDef.SPLD_TemplateBlock
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_TemplateBlock", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:GenerateHTML_Group( mSPLDef, lFile, szLastSectionType, szWriteBuffer )
         omSPLDef_GenerateHTML_Group( mSPLDef, lFile, szLastSectionType, szWriteBuffer );
         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_TemplateBlock", "" );
      } 

      //:END

      //:szWriteBuffer = "    </div>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "    </div>", 1, 0, 32001 );
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

      //:lControl = zPOS_NEXT + zTEST_CSR_RESULT
      lControl = zPOS_NEXT + zTEST_CSR_RESULT;
      //:nRC = SetEntityCursor( mSPLDef, "SPLD_TemplatePanel", "", lControl,
      //:                       szVoid, "", "", 0, "", "" )
      nRC = SetEntityCursor( mSPLDef, "SPLD_TemplatePanel", "", lControl, szVoid, "", "", 0, "", "" );
      //:IF nRC >= zCURSOR_SET
      if ( nRC >= zCURSOR_SET )
      { 

         //:szWriteBuffer = "<br style=^page-break-after:always;^ />"
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<br style=^page-break-after:always;^ />", 1, 0, 32001 );
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

         //:lCnt = lCnt + 1
         lCnt = lCnt + 1;
         //:zIntegerToString( szCount, 2, lCnt )
         {StringBuilder sb_szCount;
         if ( szCount == null )
            sb_szCount = new StringBuilder( 32 );
         else
            sb_szCount = new StringBuilder( szCount );
                   zIntegerToString( sb_szCount, 2, lCnt );
         szCount = sb_szCount.toString( );}

         //:// Remove these lines prior to deployment!!!
         //:szWriteBuffer = "<br clear=both /><p>(New Panel" + szCount + ")</p>"
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<br clear=both /><p>(New Panel", 1, 0, 32001 );
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
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, ")</p>", 1, 0, 32001 );
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

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_TemplatePanel", "" );
      //:   // End of: Remove these lines prior to deployment!!!

      //:END
   } 

   //:END

   //:szWriteBuffer = "</body>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "</body>", 1, 0, 32001 );
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
   //:szWriteBuffer = "</html>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "</html>", 1, 0, 32001 );
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

   //:// Close the file.
   //:SysCloseFile( mSPLDef, lFile, 0 )
   try
   {
       m_KZOEP1AA.SysCloseFile( mSPLDef, lFile, null );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   return( 0 );
// END
} 


private int 
omSPLDef_fnLocalBuildQual_0( View     vSubtask,
                             zVIEW    vQualObject,
                             int      TemplID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Template" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Template" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", TemplID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:TRANSFORMATION OPERATION
//:GeneratePDF_Label( VIEW mSPLDef BASED ON LOD mSPLDef )

//:   STRING ( 32000 ) szWriteBuffer
public int 
omSPLDef_GeneratePDF_Label( View     mSPLDef )
{
   String   szWriteBuffer = null;
   //:INTEGER lFile
   int      lFile = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:STRING ( 10 ) szSize
   String   szSize = null;
   //:STRING ( 32 ) szLastSectionType
   String   szLastSectionType = null;
   //:STRING ( 32 ) szVoid
   String   szVoid = null;
   //:STRING ( 2 )  szCount
   String   szCount = null;
   //:INTEGER       lCnt
   int      lCnt = 0;
   //:SHORT         nRC
   int      nRC = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;


   //:// Generate XML ... test purposes only
   //:szWriteBuffer = "c:\Program Files\Apache Group\Tomcat 7.0\webapps\ROOT\ePamms\Temp.xml"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "c:\\Program Files\\Apache Group\\Tomcat 7.0\\webapps\\ROOT\\ePamms\\Temp.xml", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:// lFile = SysOpenFile( mSPLDef, szWriteBuffer, COREFILE_DELETE )
   //:// lFile = SysOpenFile( mSPLDef, szWriteBuffer, COREFILE_WRITE )
   //:CommitOI_ToXML_File( mSPLDef, szWriteBuffer, 0 )
   CommitOI_ToXML_File( mSPLDef, szWriteBuffer, 0 );

   //:// Generate an PDF Label from the SPLD.

   //:// Open the File (use szWriteBuffer to hold FileName).
   //:SysMakeWebFileName( szWriteBuffer, mSPLDef, 2 )
   {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
       m_KZOEP1AA.SysMakeWebFileName( sb_szWriteBuffer, mSPLDef, 2 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:szWriteBuffer = szWriteBuffer + ".pdf"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, ".pdf", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:TraceLineS( "Output Filename: ", szWriteBuffer )
   TraceLineS( "Output Filename: ", szWriteBuffer );
   //:lFile = SysOpenFile( mSPLDef, szWriteBuffer, COREFILE_DELETE )
   try
   {
       lFile = m_KZOEP1AA.SysOpenFile( mSPLDef, szWriteBuffer, COREFILE_DELETE );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:lFile = SysOpenFile( mSPLDef, szWriteBuffer, COREFILE_WRITE )
   try
   {
       lFile = m_KZOEP1AA.SysOpenFile( mSPLDef, szWriteBuffer, COREFILE_WRITE );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Put out header data.
   //:szWriteBuffer = "<!DOCTYPE PDF PUBLIC ^-//W3C//DTD PDF 4.01//EN^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<!DOCTYPE PDF PUBLIC ^-//W3C//DTD PDF 4.01//EN^>", 1, 0, 32001 );
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
   //:szWriteBuffer = "<html>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<html>", 1, 0, 32001 );
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
   //:szWriteBuffer = "<head>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<head>", 1, 0, 32001 );
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
   //:szWriteBuffer = "<meta http-equiv=^Content-Type^ content=^text/html; charset=iso-8859-1^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<meta http-equiv=^Content-Type^ content=^text/html; charset=iso-8859-1^>", 1, 0, 32001 );
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
   //:szWriteBuffer = "<link href=^" + mSPLDef.SPLD_Template.CSS_FileName + ".css^ rel=^stylesheet^ type=^text/css^>"
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 255, mSPLDef, "SPLD_Template", "CSS_FileName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<link href=^", 1, 0, 32001 );
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
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, ".css^ rel=^stylesheet^ type=^text/css^>", 1, 0, 32001 );
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
   //:szWriteBuffer = "</head>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "</head>", 1, 0, 32001 );
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
   //:szWriteBuffer = "<body>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<body>", 1, 0, 32001 );
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

   //:// Loop through each PANEL, creating a div for each Panel.
   //:lCnt = 1
   lCnt = 1;
   //:FOR EACH mSPLDef.SPLD_TemplatePanel
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_TemplatePanel", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Set up panel div.
      //:szWriteBuffer = "    <div id=^" + mSPLDef.SPLD_TemplatePanel.dPanelName
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
             GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 255, mSPLDef, "SPLD_TemplatePanel", "dPanelName", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szTempString_1 = sb_szTempString_1.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "    <div id=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTempString_1, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:szWriteBuffer = szWriteBuffer + "^ class=^panel^ style=^"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^ class=^panel^ style=^", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}

      //:szSize = mSPLDef.SPLD_TemplatePanel.dKerning
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szSize;
      if ( szSize == null )
         sb_szSize = new StringBuilder( 32 );
      else
         sb_szSize = new StringBuilder( szSize );
             GetVariableFromAttribute( sb_szSize, mi_lTempInteger_2, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "dKerning", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szSize = sb_szSize.toString( );}
      //:IF szSize != ""
      if ( ZeidonStringCompare( szSize, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + "letter-spacing:" + szSize + "; "
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "letter-spacing:", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSize, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "; ", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szSize = mSPLDef.SPLD_TemplatePanel.dLeading
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szSize;
      if ( szSize == null )
         sb_szSize = new StringBuilder( 32 );
      else
         sb_szSize = new StringBuilder( szSize );
             GetVariableFromAttribute( sb_szSize, mi_lTempInteger_3, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "dLeading", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szSize = sb_szSize.toString( );}
      //:IF szSize != ""
      if ( ZeidonStringCompare( szSize, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + "line-height:" + szSize + "; "
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "line-height:", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSize, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "; ", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szSize = mSPLDef.SPLD_TemplatePanel.SizeX
      {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
      StringBuilder sb_szSize;
      if ( szSize == null )
         sb_szSize = new StringBuilder( 32 );
      else
         sb_szSize = new StringBuilder( szSize );
             GetVariableFromAttribute( sb_szSize, mi_lTempInteger_4, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "SizeX", "", 0 );
      lTempInteger_4 = mi_lTempInteger_4.intValue( );
      szSize = sb_szSize.toString( );}
      //:IF szSize != ""
      if ( ZeidonStringCompare( szSize, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szWriteBuffer = szWriteBuffer + "width:" + szSize + "px; "
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "width:", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSize, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "px; ", 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
      } 

      //:END

      //:szSize = mSPLDef.SPLD_TemplatePanel.PosX
      {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
      StringBuilder sb_szSize;
      if ( szSize == null )
         sb_szSize = new StringBuilder( 32 );
      else
         sb_szSize = new StringBuilder( szSize );
             GetVariableFromAttribute( sb_szSize, mi_lTempInteger_5, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "PosX", "", 0 );
      lTempInteger_5 = mi_lTempInteger_5.intValue( );
      szSize = sb_szSize.toString( );}
      //:IF szSize = ""
      if ( ZeidonStringCompare( szSize, 1, 0, "", 1, 0, 11 ) == 0 )
      { 
         //:szSize = "20"  //???
          {StringBuilder sb_szSize;
         if ( szSize == null )
            sb_szSize = new StringBuilder( 32 );
         else
            sb_szSize = new StringBuilder( szSize );
                  ZeidonStringCopy( sb_szSize, 1, 0, "20", 1, 0, 11 );
         szSize = sb_szSize.toString( );}
      } 

      //:END

      //:szWriteBuffer = szWriteBuffer + "margin-left:" + szSize + "px; ^>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "margin-left:", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSize, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "px; ^>", 1, 0, 32001 );
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

      //:// Generate PDF for each GROUP.
      //:FOR EACH mSPLDef.SPLD_TemplateBlock
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_TemplateBlock", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:GeneratePDF_Group( mSPLDef, lFile, szLastSectionType, szWriteBuffer )
         omSPLDef_GeneratePDF_Group( mSPLDef, lFile, szLastSectionType, szWriteBuffer );
         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_TemplateBlock", "" );
      } 

      //:END

      //:szWriteBuffer = "    </div>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "    </div>", 1, 0, 32001 );
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

      //:lControl = zPOS_NEXT + zTEST_CSR_RESULT
      lControl = zPOS_NEXT + zTEST_CSR_RESULT;
      //:nRC = SetEntityCursor( mSPLDef, "SPLD_TemplatePanel", "", lControl,
      //:                       szVoid, "", "", 0, "", "" )
      nRC = SetEntityCursor( mSPLDef, "SPLD_TemplatePanel", "", lControl, szVoid, "", "", 0, "", "" );
      //:IF nRC >= zCURSOR_SET
      if ( nRC >= zCURSOR_SET )
      { 

         //:szWriteBuffer = "<br style=^page-break-after:always;^ />"
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<br style=^page-break-after:always;^ />", 1, 0, 32001 );
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

         //:lCnt = lCnt + 1
         lCnt = lCnt + 1;
         //:zIntegerToString( szCount, 2, lCnt )
         {StringBuilder sb_szCount;
         if ( szCount == null )
            sb_szCount = new StringBuilder( 32 );
         else
            sb_szCount = new StringBuilder( szCount );
                   zIntegerToString( sb_szCount, 2, lCnt );
         szCount = sb_szCount.toString( );}

         //:// Remove these lines prior to deployment!!!
         //:szWriteBuffer = "<br clear=both /><p>(New Panel" + szCount + ")</p>"
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "<br clear=both /><p>(New Panel", 1, 0, 32001 );
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
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, ")</p>", 1, 0, 32001 );
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

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_TemplatePanel", "" );
      //:   // End of: Remove these lines prior to deployment!!!

      //:END
   } 

   //:END

   //:szWriteBuffer = "</body>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "</body>", 1, 0, 32001 );
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
   //:szWriteBuffer = "</html>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "</html>", 1, 0, 32001 );
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

   //:// Close the file.
   //:SysCloseFile( mSPLDef, lFile, 0 )
   try
   {
       m_KZOEP1AA.SysCloseFile( mSPLDef, lFile, null );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateHTML_Group( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                    INTEGER lFile,
//:                    STRING ( 32 )    szLastSectionType,
//:                    STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 32 )  szSectionType
public int 
omSPLDef_GenerateHTML_Group( View     mSPLDef,
                             int      lFile,
                             String   szLastSectionType,
                             String   szWriteBuffer )
{
   String   szSectionType = null;
   //:STRING ( 10 )  szPosX
   String   szPosX = null;
   //:STRING ( 10 )  szPosY
   String   szPosY = null;
   //:STRING ( 10 )  szSizeX
   String   szSizeX = null;
   //:STRING ( 10 )  szSizeY
   String   szSizeY = null;
   //:STRING ( 10 )  szPosSizeUnits
   String   szPosSizeUnits = null;
   //:STRING ( 32 )  szWidthHeight
   String   szWidthHeight = null;
   //:STRING ( 32 )  szClass
   String   szClass = null;
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
   String   szTempString_0 = null;
   int      lTempInteger_8 = 0;
   int      RESULT = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   int      lTempInteger_13 = 0;


   //:// Generate HTML for a TemplateBlock subobject.
   //:// If the Group has a graphic, we simply generate the graphic using positioning information from the first Section.
   //:// Otherwise, we'll loop through each Section.

   //:szWriteBuffer = " "
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, " ", 1, 0, 32001 );
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

   //:// We will create a div entry for each GROUP, with a border if requested.
   //:IF mSPLDef.SPLD_TemplateBlock.ShowBox = "Y"
   if ( CompareAttributeToString( mSPLDef, "SPLD_TemplateBlock", "ShowBox", "Y" ) == 0 )
   { 
      //:szWriteBuffer = "        <div class=^groupwithborder^>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "        <div class=^groupwithborder^>", 1, 0, 32001 );
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
      //:szWriteBuffer = "        <div>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "        <div>", 1, 0, 32001 );
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

   //:szWriteBuffer = mSPLDef.SPLD_TemplateBlock.BlockTitle
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
       GetVariableFromAttribute( sb_szWriteBuffer, mi_lTempInteger_0, 'S', 32001, mSPLDef, "SPLD_TemplateBlock", "BlockTitle", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:IF szWriteBuffer != ""
   if ( ZeidonStringCompare( szWriteBuffer, 1, 0, "", 1, 0, 32001 ) != 0 )
   { 
      //:szClass = mSPLDef.SPLD_TemplateBlock.TitleClass
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szClass;
      if ( szClass == null )
         sb_szClass = new StringBuilder( 32 );
      else
         sb_szClass = new StringBuilder( szClass );
             GetVariableFromAttribute( sb_szClass, mi_lTempInteger_1, 'S', 33, mSPLDef, "SPLD_TemplateBlock", "TitleClass", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szClass = sb_szClass.toString( );}
      //:IF szClass != ""
      if ( ZeidonStringCompare( szClass, 1, 0, "", 1, 0, 33 ) != 0 )
      { 
         //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 1 )
         m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 1 );
         //:ELSE
      } 
      else
      { 
         //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
         m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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
   } 

   //:END
   //:IF mSPLDef.SPLD_TemplateBlock.ExternalGraphicFileName != ""
   if ( CompareAttributeToString( mSPLDef, "SPLD_TemplateBlock", "ExternalGraphicFileName", "" ) != 0 )
   { 

      //:// IMAGE
      //:szImg = "            <img style=^border: 0px 0px 0px 0px; "
       {StringBuilder sb_szImg;
      if ( szImg == null )
         sb_szImg = new StringBuilder( 32 );
      else
         sb_szImg = new StringBuilder( szImg );
            ZeidonStringCopy( sb_szImg, 1, 0, "            <img style=^border: 0px 0px 0px 0px; ", 1, 0, 257 );
      szImg = sb_szImg.toString( );}

      //:IF mSPLDef.SPLD_TemplateSection EXISTS
      lTempInteger_2 = CheckExistenceOfEntity( mSPLDef, "SPLD_TemplateSection" );
      if ( lTempInteger_2 == 0 )
      { 
         //:szPosX = mSPLDef.SPLD_TemplateBlock.PosX
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szPosX;
         if ( szPosX == null )
            sb_szPosX = new StringBuilder( 32 );
         else
            sb_szPosX = new StringBuilder( szPosX );
                   GetVariableFromAttribute( sb_szPosX, mi_lTempInteger_3, 'S', 11, mSPLDef, "SPLD_TemplateBlock", "PosX", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         szPosX = sb_szPosX.toString( );}
         //:szPosY = mSPLDef.SPLD_TemplateBlock.PosY
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
         StringBuilder sb_szPosY;
         if ( szPosY == null )
            sb_szPosY = new StringBuilder( 32 );
         else
            sb_szPosY = new StringBuilder( szPosY );
                   GetVariableFromAttribute( sb_szPosY, mi_lTempInteger_4, 'S', 11, mSPLDef, "SPLD_TemplateBlock", "PosY", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );
         szPosY = sb_szPosY.toString( );}
         //:szSizeX = mSPLDef.SPLD_TemplateBlock.SizeX
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
         StringBuilder sb_szSizeX;
         if ( szSizeX == null )
            sb_szSizeX = new StringBuilder( 32 );
         else
            sb_szSizeX = new StringBuilder( szSizeX );
                   GetVariableFromAttribute( sb_szSizeX, mi_lTempInteger_5, 'S', 11, mSPLDef, "SPLD_TemplateBlock", "SizeX", "", 0 );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );
         szSizeX = sb_szSizeX.toString( );}
         //:szSizeY = mSPLDef.SPLD_TemplateBlock.SizeY
         {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
         StringBuilder sb_szSizeY;
         if ( szSizeY == null )
            sb_szSizeY = new StringBuilder( 32 );
         else
            sb_szSizeY = new StringBuilder( szSizeY );
                   GetVariableFromAttribute( sb_szSizeY, mi_lTempInteger_6, 'S', 11, mSPLDef, "SPLD_TemplateBlock", "SizeY", "", 0 );
         lTempInteger_6 = mi_lTempInteger_6.intValue( );
         szSizeY = sb_szSizeY.toString( );}
         //:szPosSizeUnits = mSPLDef.SPLD_TemplateBlock.PosSizeUnits
         {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
         StringBuilder sb_szPosSizeUnits;
         if ( szPosSizeUnits == null )
            sb_szPosSizeUnits = new StringBuilder( 32 );
         else
            sb_szPosSizeUnits = new StringBuilder( szPosSizeUnits );
                   GetVariableFromAttribute( sb_szPosSizeUnits, mi_lTempInteger_7, 'S', 11, mSPLDef, "SPLD_TemplateBlock", "PosSizeUnits", "", 0 );
         lTempInteger_7 = mi_lTempInteger_7.intValue( );
         szPosSizeUnits = sb_szPosSizeUnits.toString( );}
         //:IF szPosX != ""
         if ( ZeidonStringCompare( szPosX, 1, 0, "", 1, 0, 11 ) != 0 )
         { 
            //:szImg = szImg + "float:left; margin-left:" + szPosX + szPosSizeUnits + "; "
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, "float:left; margin-left:", 1, 0, 257 );
            szImg = sb_szImg.toString( );}
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, szPosX, 1, 0, 257 );
            szImg = sb_szImg.toString( );}
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, szPosSizeUnits, 1, 0, 257 );
            szImg = sb_szImg.toString( );}
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, "; ", 1, 0, 257 );
            szImg = sb_szImg.toString( );}
         } 

         //:END

         //:IF szPosY != ""
         if ( ZeidonStringCompare( szPosY, 1, 0, "", 1, 0, 11 ) != 0 )
         { 
            //:szImg = szImg + "float:top; margin-top:" + szPosY + szPosSizeUnits + "; "
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, "float:top; margin-top:", 1, 0, 257 );
            szImg = sb_szImg.toString( );}
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, szPosY, 1, 0, 257 );
            szImg = sb_szImg.toString( );}
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, szPosSizeUnits, 1, 0, 257 );
            szImg = sb_szImg.toString( );}
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, "; ", 1, 0, 257 );
            szImg = sb_szImg.toString( );}
         } 

         //:END

         //:szWidthHeight = ""
          {StringBuilder sb_szWidthHeight;
         if ( szWidthHeight == null )
            sb_szWidthHeight = new StringBuilder( 32 );
         else
            sb_szWidthHeight = new StringBuilder( szWidthHeight );
                  ZeidonStringCopy( sb_szWidthHeight, 1, 0, "", 1, 0, 33 );
         szWidthHeight = sb_szWidthHeight.toString( );}
         //:IF szSizeX != ""
         if ( ZeidonStringCompare( szSizeX, 1, 0, "", 1, 0, 11 ) != 0 )
         { 
            //:szWidthHeight = szWidthHeight + "width=^" + szSizeX + "^ "
             {StringBuilder sb_szWidthHeight;
            if ( szWidthHeight == null )
               sb_szWidthHeight = new StringBuilder( 32 );
            else
               sb_szWidthHeight = new StringBuilder( szWidthHeight );
                        ZeidonStringConcat( sb_szWidthHeight, 1, 0, "width=^", 1, 0, 33 );
            szWidthHeight = sb_szWidthHeight.toString( );}
             {StringBuilder sb_szWidthHeight;
            if ( szWidthHeight == null )
               sb_szWidthHeight = new StringBuilder( 32 );
            else
               sb_szWidthHeight = new StringBuilder( szWidthHeight );
                        ZeidonStringConcat( sb_szWidthHeight, 1, 0, szSizeX, 1, 0, 33 );
            szWidthHeight = sb_szWidthHeight.toString( );}
             {StringBuilder sb_szWidthHeight;
            if ( szWidthHeight == null )
               sb_szWidthHeight = new StringBuilder( 32 );
            else
               sb_szWidthHeight = new StringBuilder( szWidthHeight );
                        ZeidonStringConcat( sb_szWidthHeight, 1, 0, "^ ", 1, 0, 33 );
            szWidthHeight = sb_szWidthHeight.toString( );}
         } 

         //:END

         //:IF szSizeY != ""
         if ( ZeidonStringCompare( szSizeY, 1, 0, "", 1, 0, 11 ) != 0 )
         { 
            //:szWidthHeight = szWidthHeight + "height=^" + szSizeY + "^ "
             {StringBuilder sb_szWidthHeight;
            if ( szWidthHeight == null )
               sb_szWidthHeight = new StringBuilder( 32 );
            else
               sb_szWidthHeight = new StringBuilder( szWidthHeight );
                        ZeidonStringConcat( sb_szWidthHeight, 1, 0, "height=^", 1, 0, 33 );
            szWidthHeight = sb_szWidthHeight.toString( );}
             {StringBuilder sb_szWidthHeight;
            if ( szWidthHeight == null )
               sb_szWidthHeight = new StringBuilder( 32 );
            else
               sb_szWidthHeight = new StringBuilder( szWidthHeight );
                        ZeidonStringConcat( sb_szWidthHeight, 1, 0, szSizeY, 1, 0, 33 );
            szWidthHeight = sb_szWidthHeight.toString( );}
             {StringBuilder sb_szWidthHeight;
            if ( szWidthHeight == null )
               sb_szWidthHeight = new StringBuilder( 32 );
            else
               sb_szWidthHeight = new StringBuilder( szWidthHeight );
                        ZeidonStringConcat( sb_szWidthHeight, 1, 0, "^ ", 1, 0, 33 );
            szWidthHeight = sb_szWidthHeight.toString( );}
         } 

         //:END
      } 


      //:END

      //:szImg = szImg + "^ "
       {StringBuilder sb_szImg;
      if ( szImg == null )
         sb_szImg = new StringBuilder( 32 );
      else
         sb_szImg = new StringBuilder( szImg );
            ZeidonStringConcat( sb_szImg, 1, 0, "^ ", 1, 0, 257 );
      szImg = sb_szImg.toString( );}

      //:szWriteBuffer = szImg + szWidthHeight + "src=^images/" + mSPLDef.SPLD_TemplateBlock.ExternalGraphicFileName + "^ />"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szImg, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szWidthHeight, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "src=^images/", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
      StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_8, 'S', 255, mSPLDef, "SPLD_TemplateBlock", "ExternalGraphicFileName", "", 0 );
      lTempInteger_8 = mi_lTempInteger_8.intValue( );
      szTempString_0 = sb_szTempString_0.toString( );}
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
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^ />", 1, 0, 32001 );
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

   //:FOR EACH mSPLDef.SPLD_TemplateSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_TemplateSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Process each Section separately by Type.
      //:szSectionType = mSPLDef.SPLD_TemplateSection.TSectionType
      {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
      StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             GetVariableFromAttribute( sb_szSectionType, mi_lTempInteger_9, 'S', 33, mSPLDef, "SPLD_TemplateSection", "TSectionType", "", 0 );
      lTempInteger_9 = mi_lTempInteger_9.intValue( );
      szSectionType = sb_szSectionType.toString( );}

      //:// Create a table entry for each Section.
      //:// Note that the User has already specified which Sections go under which Groups, so we need
      //:// only loop through the Sections here, without concern for placement within the label.

      //:// DIRECTIONS FOR USE
      //:IF szSectionType = "DirectionsForUse"
      if ( ZeidonStringCompare( szSectionType, 1, 0, "DirectionsForUse", 1, 0, 33 ) == 0 )
      { 
         //:TraceLineS( "GenerateHTML_DFU: ", szSectionType )
         TraceLineS( "GenerateHTML_DFU: ", szSectionType );
         //:FOR EACH mSPLDef.SPLDT_DirectionsForUseSection
         RESULT = SetCursorFirstEntity( mSPLDef, "SPLDT_DirectionsForUseSection", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:GenerateHTML_DFU( mSPLDef, lFile,
            //:                  "SPLDT_DirectionsForUseSection",
            //:                  "SPLD_DirectionsForUseSection",
            //:                  "SPLD_DirectionsForUseStatement",
            //:                  "SPLD_DirectionsUsage",
            //:                  szWriteBuffer )
            omSPLDef_GenerateHTML_DFU( mSPLDef, lFile, "SPLDT_DirectionsForUseSection", "SPLD_DirectionsForUseSection", "SPLD_DirectionsForUseStatement", "SPLD_DirectionsUsage", szWriteBuffer );
            RESULT = SetCursorNextEntity( mSPLDef, "SPLDT_DirectionsForUseSection", "" );
         } 

         //:END
         //:ELSE
      } 
      else
      { 

         //:// MARKETING
         //:IF szSectionType = "Marketing"
         if ( ZeidonStringCompare( szSectionType, 1, 0, "Marketing", 1, 0, 33 ) == 0 )
         { 
            //:// IssueError( mSPLDef, 0, 0, "At Marketing" )
            //:TraceLineS( "GenerateHTML_DFU: ", szSectionType )
            TraceLineS( "GenerateHTML_DFU: ", szSectionType );
            //:FOR EACH mSPLDef.SPLDT_MarketingSection
            RESULT = SetCursorFirstEntity( mSPLDef, "SPLDT_MarketingSection", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:GenerateHTML_DFU( mSPLDef, lFile,
               //:               "SPLDT_MarketingSection",
               //:               "SPLD_MarketingSection",
               //:               "SPLD_MarketingStatement",
               //:               "SPLD_MarketingUsage",
               //:               szWriteBuffer )
               omSPLDef_GenerateHTML_DFU( mSPLDef, lFile, "SPLDT_MarketingSection", "SPLD_MarketingSection", "SPLD_MarketingStatement", "SPLD_MarketingUsage", szWriteBuffer );
               RESULT = SetCursorNextEntity( mSPLDef, "SPLDT_MarketingSection", "" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 

            //:// GENERAL (ENVIRONMENTAL/PHYSICAL HAZARD, FIRST AID or PRECAUTIONARY)
            //:IF szSectionType = "OtherHazard" OR
            //:szSectionType = "FirstAid" OR
            //:szSectionType = "Precautionary"
            if ( ZeidonStringCompare( szSectionType, 1, 0, "OtherHazard", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "FirstAid", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "Precautionary", 1, 0, 33 ) == 0 )
            { 

               //:// We can get by using the same operation because all 3 Section Types are handled the same way and the child entity,
               //:// SPLDT_GeneralSection, is pointing to the instance of the correct Type.
               //:TraceLineS( "GenerateHTML_General: ", szSectionType )
               TraceLineS( "GenerateHTML_General: ", szSectionType );
               //:FOR EACH mSPLDef.SPLDT_GeneralSection
               RESULT = SetCursorFirstEntity( mSPLDef, "SPLDT_GeneralSection", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:GenerateHTML_General( mSPLDef, lFile, szWriteBuffer )
                  omSPLDef_GenerateHTML_General( mSPLDef, lFile, szWriteBuffer );
                  RESULT = SetCursorNextEntity( mSPLDef, "SPLDT_GeneralSection", "" );
               } 

               //:END
               //:ELSE
            } 
            else
            { 

               //:// STORAGE AND DISPOSAL
               //:IF szSectionType = "Storage" OR
               //:szSectionType = "Disposal" OR
               //:szSectionType = "ContainerDisposal" OR
               //:szSectionType = "StorageDisposal1" OR
               //:szSectionType = "StorageDisposal2"
               if ( ZeidonStringCompare( szSectionType, 1, 0, "Storage", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "Disposal", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "ContainerDisposal", 1, 0, 33 ) == 0 ||
                    ZeidonStringCompare( szSectionType, 1, 0, "StorageDisposal1", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "StorageDisposal2", 1, 0, 33 ) == 0 )
               { 

                  //:TraceLineS( "GenerateHTML_StorDisp: ", szSectionType )
                  TraceLineS( "GenerateHTML_StorDisp: ", szSectionType );
                  //:FOR EACH mSPLDef.SPLDT_StorageDisposalSection
                  RESULT = SetCursorFirstEntity( mSPLDef, "SPLDT_StorageDisposalSection", "" );
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //:GenerateHTML_StorDisp( mSPLDef, lFile, szSectionType, szWriteBuffer )
                     omSPLDef_GenerateHTML_StorDisp( mSPLDef, lFile, szSectionType, szWriteBuffer );
                     RESULT = SetCursorNextEntity( mSPLDef, "SPLDT_StorageDisposalSection", "" );
                  } 

                  //:END

                  //:ELSE
               } 
               else
               { 

                  //:// HUMAN HAZARD
                  //:IF szSectionType = "HumanHazard"  // initial check out!!!
                  if ( ZeidonStringCompare( szSectionType, 1, 0, "HumanHazard", 1, 0, 33 ) == 0 )
                  { 

                     //:// Generate the single Hazards entry.
                     //:TraceLineS( "GenerateHTML_Hazards: ", szSectionType )
                     TraceLineS( "GenerateHTML_Hazards: ", szSectionType );
                     //:GenerateHTML_Hazards( mSPLDef, lFile, szWriteBuffer )
                     omSPLDef_GenerateHTML_Hazards( mSPLDef, lFile, szWriteBuffer );

                     //:ELSE
                  } 
                  else
                  { 

                     //:// INGREDIENTS
                     //:IF szSectionType = "Ingredients"  // initial check out!!!
                     if ( ZeidonStringCompare( szSectionType, 1, 0, "Ingredients", 1, 0, 33 ) == 0 )
                     { 

                        //:// Go to generate Active Ingredients.
                        //:TraceLineS( "GenerateHTML_Ingred: ", szSectionType )
                        TraceLineS( "GenerateHTML_Ingred: ", szSectionType );
                        //:GenerateHTML_Ingred( mSPLDef, lFile, szWriteBuffer )
                        omSPLDef_GenerateHTML_Ingred( mSPLDef, lFile, szWriteBuffer );

                        //:ELSE
                     } 
                     else
                     { 

                        //:// NET CONTENTS
                        //:IF szSectionType = "NetContents"  // initial check out!!!
                        if ( ZeidonStringCompare( szSectionType, 1, 0, "NetContents", 1, 0, 33 ) == 0 )
                        { 

                           //:// Go to generate Net Contents.
                           //:TraceLineS( "GenerateHTML_Content: ", szSectionType )
                           TraceLineS( "GenerateHTML_Content: ", szSectionType );
                           //:GenerateHTML_Content( mSPLDef, lFile, szWriteBuffer )
                           omSPLDef_GenerateHTML_Content( mSPLDef, lFile, szWriteBuffer );

                           //:ELSE
                        } 
                        else
                        { 

                           //:// EPA REGISTRATION AND ESTABLISHMENT NUMBERS
                           //:IF szSectionType = "EPA_RegAndEstNbr"  // initial check out!!!
                           if ( ZeidonStringCompare( szSectionType, 1, 0, "EPA_RegAndEstNbr", 1, 0, 33 ) == 0 )
                           { 

                              //:// Go to generate EPA Reg and Est Numbers.
                              //:TraceLineS( "GenerateHTML_EPA_Reg: ", szSectionType )
                              TraceLineS( "GenerateHTML_EPA_Reg: ", szSectionType );
                              //:GenerateHTML_EPA_Reg( mSPLDef, lFile, szWriteBuffer )
                              omSPLDef_GenerateHTML_EPA_Reg( mSPLDef, lFile, szWriteBuffer );

                              //:ELSE
                           } 
                           else
                           { 

                              //:// GRAPHIC OR PRODUCT SPECIFIC
                              //:IF szSectionType = "Graphic" OR szSectionType = "Product"
                              if ( ZeidonStringCompare( szSectionType, 1, 0, "Graphic", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "Product", 1, 0, 33 ) == 0 )
                              { 

                                 //:IF mSPLDef.SPLD_TemplateSection.TitleOverride != ""
                                 if ( CompareAttributeToString( mSPLDef, "SPLD_TemplateSection", "TitleOverride", "" ) != 0 )
                                 { 
                                    //:szWriteBuffer = mSPLDef.SPLD_TemplateSection.TitleOverride
                                    {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
                                    StringBuilder sb_szWriteBuffer;
                                    if ( szWriteBuffer == null )
                                       sb_szWriteBuffer = new StringBuilder( 32 );
                                    else
                                       sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                         GetVariableFromAttribute( sb_szWriteBuffer, mi_lTempInteger_10, 'S', 32001, mSPLDef, "SPLD_TemplateSection", "TitleOverride", "", 0 );
                                    lTempInteger_10 = mi_lTempInteger_10.intValue( );
                                    szWriteBuffer = sb_szWriteBuffer.toString( );}
                                    //:szClass = mSPLDef.SPLD_TemplateSection.TitleClass
                                    {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
                                    StringBuilder sb_szClass;
                                    if ( szClass == null )
                                       sb_szClass = new StringBuilder( 32 );
                                    else
                                       sb_szClass = new StringBuilder( szClass );
                                                                         GetVariableFromAttribute( sb_szClass, mi_lTempInteger_11, 'S', 33, mSPLDef, "SPLD_TemplateSection", "TitleClass", "", 0 );
                                    lTempInteger_11 = mi_lTempInteger_11.intValue( );
                                    szClass = sb_szClass.toString( );}
                                    //:IF szClass != ""
                                    if ( ZeidonStringCompare( szClass, 1, 0, "", 1, 0, 33 ) != 0 )
                                    { 
                                       //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 1 )
                                       m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 1 );
                                       //:ELSE
                                    } 
                                    else
                                    { 
                                       //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
                                       m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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
                                 } 

                                 //:END

                                 //:IF mSPLDef.SPLD_TemplateSection.SubtitleOverride != ""
                                 if ( CompareAttributeToString( mSPLDef, "SPLD_TemplateSection", "SubtitleOverride", "" ) != 0 )
                                 { 
                                    //:szWriteBuffer = mSPLDef.SPLD_TemplateSection.SubtitleOverride
                                    {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
                                    StringBuilder sb_szWriteBuffer;
                                    if ( szWriteBuffer == null )
                                       sb_szWriteBuffer = new StringBuilder( 32 );
                                    else
                                       sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                         GetVariableFromAttribute( sb_szWriteBuffer, mi_lTempInteger_12, 'S', 32001, mSPLDef, "SPLD_TemplateSection", "SubtitleOverride", "", 0 );
                                    lTempInteger_12 = mi_lTempInteger_12.intValue( );
                                    szWriteBuffer = sb_szWriteBuffer.toString( );}
                                    //:szClass = mSPLDef.SPLD_TemplateSection.SubtitleClass
                                    {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
                                    StringBuilder sb_szClass;
                                    if ( szClass == null )
                                       sb_szClass = new StringBuilder( 32 );
                                    else
                                       sb_szClass = new StringBuilder( szClass );
                                                                         GetVariableFromAttribute( sb_szClass, mi_lTempInteger_13, 'S', 33, mSPLDef, "SPLD_TemplateSection", "SubtitleClass", "", 0 );
                                    lTempInteger_13 = mi_lTempInteger_13.intValue( );
                                    szClass = sb_szClass.toString( );}
                                    //:IF szClass != ""
                                    if ( ZeidonStringCompare( szClass, 1, 0, "", 1, 0, 33 ) != 0 )
                                    { 
                                       //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 1 )
                                       m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 1 );
                                       //:ELSE
                                    } 
                                    else
                                    { 
                                       //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
                                       m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_TemplateSection", "" );
      //:END
   } 


   //:END

   //:szWriteBuffer = "        </div>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "        </div>", 1, 0, 32001 );
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
//:GeneratePDF_Group( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                    INTEGER lFile,
//:                    STRING ( 32 )    szLastSectionType,
//:                    STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 32 )  szSectionType
public int 
omSPLDef_GeneratePDF_Group( View     mSPLDef,
                            int      lFile,
                            String   szLastSectionType,
                            String   szWriteBuffer )
{
   String   szSectionType = null;
   //:STRING ( 10 )  szPosX
   String   szPosX = null;
   //:STRING ( 10 )  szPosY
   String   szPosY = null;
   //:STRING ( 10 )  szSizeX
   String   szSizeX = null;
   //:STRING ( 10 )  szSizeY
   String   szSizeY = null;
   //:STRING ( 10 )  szPosSizeUnits
   String   szPosSizeUnits = null;
   //:STRING ( 32 )  szWidthHeight
   String   szWidthHeight = null;
   //:STRING ( 32 )  szClass
   String   szClass = null;
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
   String   szTempString_0 = null;
   int      lTempInteger_8 = 0;
   int      RESULT = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   int      lTempInteger_13 = 0;


   //:// Generate PDF for a TemplateBlock subobject.
   //:// If the Group has a graphic, we simply generate the graphic using positioning information from the first Section.
   //:// Otherwise, we'll loop through each Section.

   //:szWriteBuffer = " "
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, " ", 1, 0, 32001 );
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

   //:// We will create a div entry for each GROUP, with a border if requested.
   //:IF mSPLDef.SPLD_TemplateBlock.ShowBox = "Y"
   if ( CompareAttributeToString( mSPLDef, "SPLD_TemplateBlock", "ShowBox", "Y" ) == 0 )
   { 
      //:szWriteBuffer = "        <div class=^groupwithborder^>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "        <div class=^groupwithborder^>", 1, 0, 32001 );
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
      //:szWriteBuffer = "        <div>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "        <div>", 1, 0, 32001 );
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

   //:szWriteBuffer = mSPLDef.SPLD_TemplateBlock.BlockTitle
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
       GetVariableFromAttribute( sb_szWriteBuffer, mi_lTempInteger_0, 'S', 32001, mSPLDef, "SPLD_TemplateBlock", "BlockTitle", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:IF szWriteBuffer != ""
   if ( ZeidonStringCompare( szWriteBuffer, 1, 0, "", 1, 0, 32001 ) != 0 )
   { 
      //:szClass = mSPLDef.SPLD_TemplateBlock.TitleClass
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szClass;
      if ( szClass == null )
         sb_szClass = new StringBuilder( 32 );
      else
         sb_szClass = new StringBuilder( szClass );
             GetVariableFromAttribute( sb_szClass, mi_lTempInteger_1, 'S', 33, mSPLDef, "SPLD_TemplateBlock", "TitleClass", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szClass = sb_szClass.toString( );}
      //:IF szClass != ""
      if ( ZeidonStringCompare( szClass, 1, 0, "", 1, 0, 33 ) != 0 )
      { 
         //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 1 )
         m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 1 );
         //:ELSE
      } 
      else
      { 
         //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
         m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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
   } 

   //:END
   //:IF mSPLDef.SPLD_TemplateBlock.ExternalGraphicFileName != ""
   if ( CompareAttributeToString( mSPLDef, "SPLD_TemplateBlock", "ExternalGraphicFileName", "" ) != 0 )
   { 

      //:// IMAGE
      //:szImg = "            <img style=^border: 0px 0px 0px 0px; "
       {StringBuilder sb_szImg;
      if ( szImg == null )
         sb_szImg = new StringBuilder( 32 );
      else
         sb_szImg = new StringBuilder( szImg );
            ZeidonStringCopy( sb_szImg, 1, 0, "            <img style=^border: 0px 0px 0px 0px; ", 1, 0, 257 );
      szImg = sb_szImg.toString( );}

      //:IF mSPLDef.SPLD_TemplateSection EXISTS
      lTempInteger_2 = CheckExistenceOfEntity( mSPLDef, "SPLD_TemplateSection" );
      if ( lTempInteger_2 == 0 )
      { 
         //:szPosX = mSPLDef.SPLD_TemplateBlock.PosX
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szPosX;
         if ( szPosX == null )
            sb_szPosX = new StringBuilder( 32 );
         else
            sb_szPosX = new StringBuilder( szPosX );
                   GetVariableFromAttribute( sb_szPosX, mi_lTempInteger_3, 'S', 11, mSPLDef, "SPLD_TemplateBlock", "PosX", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         szPosX = sb_szPosX.toString( );}
         //:szPosY = mSPLDef.SPLD_TemplateBlock.PosY
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
         StringBuilder sb_szPosY;
         if ( szPosY == null )
            sb_szPosY = new StringBuilder( 32 );
         else
            sb_szPosY = new StringBuilder( szPosY );
                   GetVariableFromAttribute( sb_szPosY, mi_lTempInteger_4, 'S', 11, mSPLDef, "SPLD_TemplateBlock", "PosY", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );
         szPosY = sb_szPosY.toString( );}
         //:szSizeX = mSPLDef.SPLD_TemplateBlock.SizeX
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
         StringBuilder sb_szSizeX;
         if ( szSizeX == null )
            sb_szSizeX = new StringBuilder( 32 );
         else
            sb_szSizeX = new StringBuilder( szSizeX );
                   GetVariableFromAttribute( sb_szSizeX, mi_lTempInteger_5, 'S', 11, mSPLDef, "SPLD_TemplateBlock", "SizeX", "", 0 );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );
         szSizeX = sb_szSizeX.toString( );}
         //:szSizeY = mSPLDef.SPLD_TemplateBlock.SizeY
         {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
         StringBuilder sb_szSizeY;
         if ( szSizeY == null )
            sb_szSizeY = new StringBuilder( 32 );
         else
            sb_szSizeY = new StringBuilder( szSizeY );
                   GetVariableFromAttribute( sb_szSizeY, mi_lTempInteger_6, 'S', 11, mSPLDef, "SPLD_TemplateBlock", "SizeY", "", 0 );
         lTempInteger_6 = mi_lTempInteger_6.intValue( );
         szSizeY = sb_szSizeY.toString( );}
         //:szPosSizeUnits = mSPLDef.SPLD_TemplateBlock.PosSizeUnits
         {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
         StringBuilder sb_szPosSizeUnits;
         if ( szPosSizeUnits == null )
            sb_szPosSizeUnits = new StringBuilder( 32 );
         else
            sb_szPosSizeUnits = new StringBuilder( szPosSizeUnits );
                   GetVariableFromAttribute( sb_szPosSizeUnits, mi_lTempInteger_7, 'S', 11, mSPLDef, "SPLD_TemplateBlock", "PosSizeUnits", "", 0 );
         lTempInteger_7 = mi_lTempInteger_7.intValue( );
         szPosSizeUnits = sb_szPosSizeUnits.toString( );}
         //:IF szPosX != ""
         if ( ZeidonStringCompare( szPosX, 1, 0, "", 1, 0, 11 ) != 0 )
         { 
            //:szImg = szImg + "float:left; margin-left:" + szPosX + szPosSizeUnits + "; "
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, "float:left; margin-left:", 1, 0, 257 );
            szImg = sb_szImg.toString( );}
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, szPosX, 1, 0, 257 );
            szImg = sb_szImg.toString( );}
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, szPosSizeUnits, 1, 0, 257 );
            szImg = sb_szImg.toString( );}
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, "; ", 1, 0, 257 );
            szImg = sb_szImg.toString( );}
         } 

         //:END

         //:IF szPosY != ""
         if ( ZeidonStringCompare( szPosY, 1, 0, "", 1, 0, 11 ) != 0 )
         { 
            //:szImg = szImg + "float:top; margin-top:" + szPosY + szPosSizeUnits + "; "
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, "float:top; margin-top:", 1, 0, 257 );
            szImg = sb_szImg.toString( );}
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, szPosY, 1, 0, 257 );
            szImg = sb_szImg.toString( );}
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, szPosSizeUnits, 1, 0, 257 );
            szImg = sb_szImg.toString( );}
             {StringBuilder sb_szImg;
            if ( szImg == null )
               sb_szImg = new StringBuilder( 32 );
            else
               sb_szImg = new StringBuilder( szImg );
                        ZeidonStringConcat( sb_szImg, 1, 0, "; ", 1, 0, 257 );
            szImg = sb_szImg.toString( );}
         } 

         //:END

         //:szWidthHeight = ""
          {StringBuilder sb_szWidthHeight;
         if ( szWidthHeight == null )
            sb_szWidthHeight = new StringBuilder( 32 );
         else
            sb_szWidthHeight = new StringBuilder( szWidthHeight );
                  ZeidonStringCopy( sb_szWidthHeight, 1, 0, "", 1, 0, 33 );
         szWidthHeight = sb_szWidthHeight.toString( );}
         //:IF szSizeX != ""
         if ( ZeidonStringCompare( szSizeX, 1, 0, "", 1, 0, 11 ) != 0 )
         { 
            //:szWidthHeight = szWidthHeight + "width=^" + szSizeX + "^ "
             {StringBuilder sb_szWidthHeight;
            if ( szWidthHeight == null )
               sb_szWidthHeight = new StringBuilder( 32 );
            else
               sb_szWidthHeight = new StringBuilder( szWidthHeight );
                        ZeidonStringConcat( sb_szWidthHeight, 1, 0, "width=^", 1, 0, 33 );
            szWidthHeight = sb_szWidthHeight.toString( );}
             {StringBuilder sb_szWidthHeight;
            if ( szWidthHeight == null )
               sb_szWidthHeight = new StringBuilder( 32 );
            else
               sb_szWidthHeight = new StringBuilder( szWidthHeight );
                        ZeidonStringConcat( sb_szWidthHeight, 1, 0, szSizeX, 1, 0, 33 );
            szWidthHeight = sb_szWidthHeight.toString( );}
             {StringBuilder sb_szWidthHeight;
            if ( szWidthHeight == null )
               sb_szWidthHeight = new StringBuilder( 32 );
            else
               sb_szWidthHeight = new StringBuilder( szWidthHeight );
                        ZeidonStringConcat( sb_szWidthHeight, 1, 0, "^ ", 1, 0, 33 );
            szWidthHeight = sb_szWidthHeight.toString( );}
         } 

         //:END

         //:IF szSizeY != ""
         if ( ZeidonStringCompare( szSizeY, 1, 0, "", 1, 0, 11 ) != 0 )
         { 
            //:szWidthHeight = szWidthHeight + "height=^" + szSizeY + "^ "
             {StringBuilder sb_szWidthHeight;
            if ( szWidthHeight == null )
               sb_szWidthHeight = new StringBuilder( 32 );
            else
               sb_szWidthHeight = new StringBuilder( szWidthHeight );
                        ZeidonStringConcat( sb_szWidthHeight, 1, 0, "height=^", 1, 0, 33 );
            szWidthHeight = sb_szWidthHeight.toString( );}
             {StringBuilder sb_szWidthHeight;
            if ( szWidthHeight == null )
               sb_szWidthHeight = new StringBuilder( 32 );
            else
               sb_szWidthHeight = new StringBuilder( szWidthHeight );
                        ZeidonStringConcat( sb_szWidthHeight, 1, 0, szSizeY, 1, 0, 33 );
            szWidthHeight = sb_szWidthHeight.toString( );}
             {StringBuilder sb_szWidthHeight;
            if ( szWidthHeight == null )
               sb_szWidthHeight = new StringBuilder( 32 );
            else
               sb_szWidthHeight = new StringBuilder( szWidthHeight );
                        ZeidonStringConcat( sb_szWidthHeight, 1, 0, "^ ", 1, 0, 33 );
            szWidthHeight = sb_szWidthHeight.toString( );}
         } 

         //:END
      } 


      //:END

      //:szImg = szImg + "^ "
       {StringBuilder sb_szImg;
      if ( szImg == null )
         sb_szImg = new StringBuilder( 32 );
      else
         sb_szImg = new StringBuilder( szImg );
            ZeidonStringConcat( sb_szImg, 1, 0, "^ ", 1, 0, 257 );
      szImg = sb_szImg.toString( );}

      //:szWriteBuffer = szImg + szWidthHeight + "src=^images/" + mSPLDef.SPLD_TemplateBlock.ExternalGraphicFileName + "^ />"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szImg, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szWidthHeight, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "src=^images/", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
      StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_8, 'S', 255, mSPLDef, "SPLD_TemplateBlock", "ExternalGraphicFileName", "", 0 );
      lTempInteger_8 = mi_lTempInteger_8.intValue( );
      szTempString_0 = sb_szTempString_0.toString( );}
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
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "^ />", 1, 0, 32001 );
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

   //:FOR EACH mSPLDef.SPLD_TemplateSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_TemplateSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Process each Section separately by Type.
      //:szSectionType = mSPLDef.SPLD_TemplateSection.TSectionType
      {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
      StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             GetVariableFromAttribute( sb_szSectionType, mi_lTempInteger_9, 'S', 33, mSPLDef, "SPLD_TemplateSection", "TSectionType", "", 0 );
      lTempInteger_9 = mi_lTempInteger_9.intValue( );
      szSectionType = sb_szSectionType.toString( );}

      //:// Create a table entry for each Section.
      //:// Note that the User has already specified which Sections go under which Groups, so we need
      //:// only loop through the Sections here, without concern for placement within the label.

      //:// DIRECTIONS FOR USE
      //:IF szSectionType = "DirectionsForUse"
      if ( ZeidonStringCompare( szSectionType, 1, 0, "DirectionsForUse", 1, 0, 33 ) == 0 )
      { 
         //:TraceLineS( "GeneratePDF_DFU: ", szSectionType )
         TraceLineS( "GeneratePDF_DFU: ", szSectionType );
         //:FOR EACH mSPLDef.SPLDT_DirectionsForUseSection
         RESULT = SetCursorFirstEntity( mSPLDef, "SPLDT_DirectionsForUseSection", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:GeneratePDF_DFU( mSPLDef, lFile,
            //:                  "SPLDT_DirectionsForUseSection",
            //:                  "SPLD_DirectionsForUseSection",
            //:                  "SPLD_DirectionsForUseStatement",
            //:                  "SPLD_DirectionsUsage",
            //:                  szWriteBuffer )
            omSPLDef_GeneratePDF_DFU( mSPLDef, lFile, "SPLDT_DirectionsForUseSection", "SPLD_DirectionsForUseSection", "SPLD_DirectionsForUseStatement", "SPLD_DirectionsUsage", szWriteBuffer );
            RESULT = SetCursorNextEntity( mSPLDef, "SPLDT_DirectionsForUseSection", "" );
         } 

         //:END
         //:ELSE
      } 
      else
      { 

         //:// MARKETING
         //:IF szSectionType = "Marketing"
         if ( ZeidonStringCompare( szSectionType, 1, 0, "Marketing", 1, 0, 33 ) == 0 )
         { 
            //:// IssueError( mSPLDef, 0, 0, "At Marketing" )
            //:TraceLineS( "GeneratePDF_DFU: ", szSectionType )
            TraceLineS( "GeneratePDF_DFU: ", szSectionType );
            //:FOR EACH mSPLDef.SPLDT_MarketingSection
            RESULT = SetCursorFirstEntity( mSPLDef, "SPLDT_MarketingSection", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:GeneratePDF_DFU( mSPLDef, lFile,
               //:               "SPLDT_MarketingSection",
               //:               "SPLD_MarketingSection",
               //:               "SPLD_MarketingStatement",
               //:               "SPLD_MarketingUsage",
               //:               szWriteBuffer )
               omSPLDef_GeneratePDF_DFU( mSPLDef, lFile, "SPLDT_MarketingSection", "SPLD_MarketingSection", "SPLD_MarketingStatement", "SPLD_MarketingUsage", szWriteBuffer );
               RESULT = SetCursorNextEntity( mSPLDef, "SPLDT_MarketingSection", "" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 

            //:// GENERAL (ENVIRONMENTAL/PHYSICAL HAZARD, FIRST AID or PRECAUTIONARY)
            //:IF szSectionType = "OtherHazard" OR
            //:szSectionType = "FirstAid" OR
            //:szSectionType = "Precautionary"
            if ( ZeidonStringCompare( szSectionType, 1, 0, "OtherHazard", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "FirstAid", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "Precautionary", 1, 0, 33 ) == 0 )
            { 

               //:// We can get by using the same operation because all 3 Section Types are handled the same way and the child entity,
               //:// SPLDT_GeneralSection, is pointing to the instance of the correct Type.
               //:TraceLineS( "GeneratePDF_General: ", szSectionType )
               TraceLineS( "GeneratePDF_General: ", szSectionType );
               //:FOR EACH mSPLDef.SPLDT_GeneralSection
               RESULT = SetCursorFirstEntity( mSPLDef, "SPLDT_GeneralSection", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:GeneratePDF_General( mSPLDef, lFile, szWriteBuffer )
                  omSPLDef_GeneratePDF_General( mSPLDef, lFile, szWriteBuffer );
                  RESULT = SetCursorNextEntity( mSPLDef, "SPLDT_GeneralSection", "" );
               } 

               //:END
               //:ELSE
            } 
            else
            { 

               //:// STORAGE AND DISPOSAL
               //:IF szSectionType = "Storage" OR
               //:szSectionType = "Disposal" OR
               //:szSectionType = "ContainerDisposal" OR
               //:szSectionType = "StorageDisposal1" OR
               //:szSectionType = "StorageDisposal2"
               if ( ZeidonStringCompare( szSectionType, 1, 0, "Storage", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "Disposal", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "ContainerDisposal", 1, 0, 33 ) == 0 ||
                    ZeidonStringCompare( szSectionType, 1, 0, "StorageDisposal1", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "StorageDisposal2", 1, 0, 33 ) == 0 )
               { 

                  //:TraceLineS( "GeneratePDF_StorDisp: ", szSectionType )
                  TraceLineS( "GeneratePDF_StorDisp: ", szSectionType );
                  //:FOR EACH mSPLDef.SPLDT_StorageDisposalSection
                  RESULT = SetCursorFirstEntity( mSPLDef, "SPLDT_StorageDisposalSection", "" );
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //:GeneratePDF_StorDisp( mSPLDef, lFile, szSectionType, szWriteBuffer )
                     omSPLDef_GeneratePDF_StorDisp( mSPLDef, lFile, szSectionType, szWriteBuffer );
                     RESULT = SetCursorNextEntity( mSPLDef, "SPLDT_StorageDisposalSection", "" );
                  } 

                  //:END

                  //:ELSE
               } 
               else
               { 

                  //:// HUMAN HAZARD
                  //:IF szSectionType = "HumanHazard"  // initial check out!!!
                  if ( ZeidonStringCompare( szSectionType, 1, 0, "HumanHazard", 1, 0, 33 ) == 0 )
                  { 

                     //:// Generate the single Hazards entry.
                     //:TraceLineS( "GeneratePDF_Hazards: ", szSectionType )
                     TraceLineS( "GeneratePDF_Hazards: ", szSectionType );
                     //:GeneratePDF_Hazards( mSPLDef, lFile, szWriteBuffer )
                     omSPLDef_GeneratePDF_Hazards( mSPLDef, lFile, szWriteBuffer );

                     //:ELSE
                  } 
                  else
                  { 

                     //:// INGREDIENTS
                     //:IF szSectionType = "Ingredients"  // initial check out!!!
                     if ( ZeidonStringCompare( szSectionType, 1, 0, "Ingredients", 1, 0, 33 ) == 0 )
                     { 

                        //:// Go to generate Active Ingredients.
                        //:TraceLineS( "GeneratePDF_Ingred: ", szSectionType )
                        TraceLineS( "GeneratePDF_Ingred: ", szSectionType );
                        //:GeneratePDF_Ingred( mSPLDef, lFile, szWriteBuffer )
                        omSPLDef_GeneratePDF_Ingred( mSPLDef, lFile, szWriteBuffer );

                        //:ELSE
                     } 
                     else
                     { 

                        //:// NET CONTENTS
                        //:IF szSectionType = "NetContents"  // initial check out!!!
                        if ( ZeidonStringCompare( szSectionType, 1, 0, "NetContents", 1, 0, 33 ) == 0 )
                        { 

                           //:// Go to generate Net Contents.
                           //:TraceLineS( "GeneratePDF_Content: ", szSectionType )
                           TraceLineS( "GeneratePDF_Content: ", szSectionType );
                           //:GeneratePDF_Content( mSPLDef, lFile, szWriteBuffer )
                           omSPLDef_GeneratePDF_Content( mSPLDef, lFile, szWriteBuffer );

                           //:ELSE
                        } 
                        else
                        { 

                           //:// EPA REGISTRATION AND ESTABLISHMENT NUMBERS
                           //:IF szSectionType = "EPA_RegAndEstNbr"  // initial check out!!!
                           if ( ZeidonStringCompare( szSectionType, 1, 0, "EPA_RegAndEstNbr", 1, 0, 33 ) == 0 )
                           { 

                              //:// Go to generate EPA Reg and Est Numbers.
                              //:TraceLineS( "GeneratePDF_EPA_Reg: ", szSectionType )
                              TraceLineS( "GeneratePDF_EPA_Reg: ", szSectionType );
                              //:GeneratePDF_EPA_Reg( mSPLDef, lFile, szWriteBuffer )
                              omSPLDef_GeneratePDF_EPA_Reg( mSPLDef, lFile, szWriteBuffer );

                              //:ELSE
                           } 
                           else
                           { 

                              //:// GRAPHIC OR PRODUCT SPECIFIC
                              //:IF szSectionType = "Graphic" OR szSectionType = "Product"
                              if ( ZeidonStringCompare( szSectionType, 1, 0, "Graphic", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "Product", 1, 0, 33 ) == 0 )
                              { 

                                 //:IF mSPLDef.SPLD_TemplateSection.TitleOverride != ""
                                 if ( CompareAttributeToString( mSPLDef, "SPLD_TemplateSection", "TitleOverride", "" ) != 0 )
                                 { 
                                    //:szWriteBuffer = mSPLDef.SPLD_TemplateSection.TitleOverride
                                    {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
                                    StringBuilder sb_szWriteBuffer;
                                    if ( szWriteBuffer == null )
                                       sb_szWriteBuffer = new StringBuilder( 32 );
                                    else
                                       sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                         GetVariableFromAttribute( sb_szWriteBuffer, mi_lTempInteger_10, 'S', 32001, mSPLDef, "SPLD_TemplateSection", "TitleOverride", "", 0 );
                                    lTempInteger_10 = mi_lTempInteger_10.intValue( );
                                    szWriteBuffer = sb_szWriteBuffer.toString( );}
                                    //:szClass = mSPLDef.SPLD_TemplateSection.TitleClass
                                    {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
                                    StringBuilder sb_szClass;
                                    if ( szClass == null )
                                       sb_szClass = new StringBuilder( 32 );
                                    else
                                       sb_szClass = new StringBuilder( szClass );
                                                                         GetVariableFromAttribute( sb_szClass, mi_lTempInteger_11, 'S', 33, mSPLDef, "SPLD_TemplateSection", "TitleClass", "", 0 );
                                    lTempInteger_11 = mi_lTempInteger_11.intValue( );
                                    szClass = sb_szClass.toString( );}
                                    //:IF szClass != ""
                                    if ( ZeidonStringCompare( szClass, 1, 0, "", 1, 0, 33 ) != 0 )
                                    { 
                                       //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 1 )
                                       m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 1 );
                                       //:ELSE
                                    } 
                                    else
                                    { 
                                       //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
                                       m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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
                                 } 

                                 //:END

                                 //:IF mSPLDef.SPLD_TemplateSection.SubtitleOverride != ""
                                 if ( CompareAttributeToString( mSPLDef, "SPLD_TemplateSection", "SubtitleOverride", "" ) != 0 )
                                 { 
                                    //:szWriteBuffer = mSPLDef.SPLD_TemplateSection.SubtitleOverride
                                    {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
                                    StringBuilder sb_szWriteBuffer;
                                    if ( szWriteBuffer == null )
                                       sb_szWriteBuffer = new StringBuilder( 32 );
                                    else
                                       sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                                                         GetVariableFromAttribute( sb_szWriteBuffer, mi_lTempInteger_12, 'S', 32001, mSPLDef, "SPLD_TemplateSection", "SubtitleOverride", "", 0 );
                                    lTempInteger_12 = mi_lTempInteger_12.intValue( );
                                    szWriteBuffer = sb_szWriteBuffer.toString( );}
                                    //:szClass = mSPLDef.SPLD_TemplateSection.SubtitleClass
                                    {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
                                    StringBuilder sb_szClass;
                                    if ( szClass == null )
                                       sb_szClass = new StringBuilder( 32 );
                                    else
                                       sb_szClass = new StringBuilder( szClass );
                                                                         GetVariableFromAttribute( sb_szClass, mi_lTempInteger_13, 'S', 33, mSPLDef, "SPLD_TemplateSection", "SubtitleClass", "", 0 );
                                    lTempInteger_13 = mi_lTempInteger_13.intValue( );
                                    szClass = sb_szClass.toString( );}
                                    //:IF szClass != ""
                                    if ( ZeidonStringCompare( szClass, 1, 0, "", 1, 0, 33 ) != 0 )
                                    { 
                                       //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 1 )
                                       m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 1 );
                                       //:ELSE
                                    } 
                                    else
                                    { 
                                       //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
                                       m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_TemplateSection", "" );
      //:END
   } 


   //:END

   //:szWriteBuffer = "        </div>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "        </div>", 1, 0, 32001 );
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
//:GenerateHTML_DFU( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                  INTEGER lFile,
//:                  STRING ( 32 ) szSPLDT_SectionName,
//:                  STRING ( 32 ) szSPLD_SectionName,
//:                  STRING ( 32 ) szStatementName,
//:                  STRING ( 32 ) szUsageName,
//:                  STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 32000 )  szStatementText
public int 
omSPLDef_GenerateHTML_DFU( View     mSPLDef,
                           int      lFile,
                           String   szSPLDT_SectionName,
                           String   szSPLD_SectionName,
                           String   szStatementName,
                           String   szUsageName,
                           String   szWriteBuffer )
{
   String   szStatementText = null;
   //:STRING ( 256 )    szCombinedTitle
   String   szCombinedTitle = null;
   //:STRING ( 10 )     szSeparatorCharacters
   String   szSeparatorCharacters = null;
   //:STRING ( 10 )     szNumberedText
   String   szNumberedText = null;
   //:STRING ( 32 )     szLineHeight
   String   szLineHeight = null;
   //:STRING ( 10 )     szStatementLeading
   String   szStatementLeading = null;
   //:STRING ( 2 )      szStatementFormat
   String   szStatementFormat = null;
   //:STRING ( 1 )      szFoundFirstNumberedEntryFlag
   String   szFoundFirstNumberedEntryFlag = null;
   //:INTEGER           lCnt
   int      lCnt = 0;
   //:INTEGER           lObjectID
   int      lObjectID = 0;
   //:SHORT             nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:// Generate HTML for a "Directory of Use" or "Marketing" Section.

   //:szLineHeight = ""
    {StringBuilder sb_szLineHeight;
   if ( szLineHeight == null )
      sb_szLineHeight = new StringBuilder( 32 );
   else
      sb_szLineHeight = new StringBuilder( szLineHeight );
      ZeidonStringCopy( sb_szLineHeight, 1, 0, "", 1, 0, 33 );
   szLineHeight = sb_szLineHeight.toString( );}
   //:szStatementLeading = mSPLDef.SPLD_TemplateSection.dStatementLeading
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szStatementLeading;
   if ( szStatementLeading == null )
      sb_szStatementLeading = new StringBuilder( 32 );
   else
      sb_szStatementLeading = new StringBuilder( szStatementLeading );
       GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_0, 'S', 11, mSPLDef, "SPLD_TemplateSection", "dStatementLeading", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szStatementLeading = sb_szStatementLeading.toString( );}
   //:IF szStatementLeading != ""
   if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szLineHeight = "line-height:" + szStatementLeading +";"
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szStatementLeading = mSPLDef.SPLD_TemplatePanel.dStatementLeadingDefault
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szStatementLeading;
      if ( szStatementLeading == null )
         sb_szStatementLeading = new StringBuilder( 32 );
      else
         sb_szStatementLeading = new StringBuilder( szStatementLeading );
             GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "dStatementLeadingDefault", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szStatementLeading = sb_szStatementLeading.toString( );}
      //:IF szStatementLeading != ""
      if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szLineHeight = "line-height:" + szStatementLeading +";"
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
      } 

      //:END
   } 

   //:END

   //://SET CURSOR FIRST mSPLDef.SPLD_DirectionsForUseSection WHERE mSPLDef.SPLD_DirectionsForUseSection.ID = mSPLDef.SPLDT_DirectionsForUseSection.ID
   //:GetIntegerFromAttribute( lObjectID, mSPLDef, szSPLDT_SectionName, "ID" )
   {MutableInt mi_lObjectID = new MutableInt( lObjectID );
       GetIntegerFromAttribute( mi_lObjectID, mSPLDef, szSPLDT_SectionName, "ID" );
   lObjectID = mi_lObjectID.intValue( );}
   //:SetCursorFirstEntityByInteger( mSPLDef, szSPLD_SectionName, "ID", lObjectID, "" )
   SetCursorFirstEntityByInteger( mSPLDef, szSPLD_SectionName, "ID", lObjectID, "" );
   //:// GetStringFromAttribute( szTitle, mSPLDef, szSPLD_SectionName, "Title" )
   //:// DisplayEntityInstance( mSPLDef, szSPLD_SectionName )
   //:GenerateHTML_Title( mSPLDef, lFile, szSPLD_SectionName, szCombinedTitle, szWriteBuffer )
   omSPLDef_GenerateHTML_Title( mSPLDef, lFile, szSPLD_SectionName, szCombinedTitle, szWriteBuffer );

   //:szStatementFormat = mSPLDef.SPLD_TemplateSection.StatementFormat
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szStatementFormat;
   if ( szStatementFormat == null )
      sb_szStatementFormat = new StringBuilder( 32 );
   else
      sb_szStatementFormat = new StringBuilder( szStatementFormat );
       GetVariableFromAttribute( sb_szStatementFormat, mi_lTempInteger_2, 'S', 3, mSPLDef, "SPLD_TemplateSection", "StatementFormat", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szStatementFormat = sb_szStatementFormat.toString( );}
   //:TraceLineS( "GenerateHTML_DFU Statement Format: ", szStatementFormat )
   TraceLineS( "GenerateHTML_DFU Statement Format: ", szStatementFormat );
   //:IF szStatementFormat = "PU" OR szStatementFormat = ""
   if ( ZeidonStringCompare( szStatementFormat, 1, 0, "PU", 1, 0, 3 ) == 0 || ZeidonStringCompare( szStatementFormat, 1, 0, "", 1, 0, 3 ) == 0 )
   { 

      //:// PU - Paragraph with Usage Statements
      //:// Process each Statement within the Section, using the option that combines Usage entries into the paragraph.
      //:nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" )
      nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" );
      //:LOOP WHILE nRC >= zCURSOR_SET
      while ( nRC >= zCURSOR_SET )
      { 

         //:// Process any merged Usage entries.
         //:// Directions for Use can have "Usage" words merged into the regular paragraph text. This is managed through the following key words.
         //:// {{Usage}} This merges all the Usage statements together in their entity order.
         //:// For the following, only that type is merged:
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
         //:// We will process this by calling the Operation, MergeUsageParagraph, for each keyword.
         //:GetStringFromAttribute( szWriteBuffer, mSPLDef, szStatementName, "Text" )
         {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                   GetStringFromAttribute( sb_szWriteBuffer, mSPLDef, szStatementName, "Text" );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
         //:szStatementText = szCombinedTitle + szWriteBuffer
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                  ZeidonStringCopy( sb_szStatementText, 1, 0, szCombinedTitle, 1, 0, 32001 );
         szStatementText = sb_szStatementText.toString( );}
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                  ZeidonStringConcat( sb_szStatementText, 1, 0, szWriteBuffer, 1, 0, 32001 );
         szStatementText = sb_szStatementText.toString( );}
         //:szSeparatorCharacters = mSPLDef.SPLD_TemplateSection.UsageSeparatorCharacters
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szSeparatorCharacters;
         if ( szSeparatorCharacters == null )
            sb_szSeparatorCharacters = new StringBuilder( 32 );
         else
            sb_szSeparatorCharacters = new StringBuilder( szSeparatorCharacters );
                   GetVariableFromAttribute( sb_szSeparatorCharacters, mi_lTempInteger_3, 'S', 11, mSPLDef, "SPLD_TemplateSection", "UsageSeparatorCharacters", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         szSeparatorCharacters = sb_szSeparatorCharacters.toString( );}
         //:nRC = CheckExistenceOfEntity( mSPLDef, szUsageName )
         nRC = CheckExistenceOfEntity( mSPLDef, szUsageName );
         //:IF nRC >= zCURSOR_SET
         if ( nRC >= zCURSOR_SET )
         { 
            //:InsertUsageWordsIntoString( mSPLDef, szStatementText, 32000, "A", "{{Usage}}", szUsageName, szSeparatorCharacters )
            {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             {StringBuilder sb_szStatementText;
            if ( szStatementText == null )
               sb_szStatementText = new StringBuilder( 32 );
            else
               sb_szStatementText = new StringBuilder( szStatementText );
                         m_ZGlobal1_Operation.InsertUsageWordsIntoString( mSPLDef, sb_szStatementText, 32000, "A", "{{Usage}}", szUsageName, szSeparatorCharacters );
            szStatementText = sb_szStatementText.toString( );}
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            }
            //:InsertUsageWordsIntoString( mSPLDef, szStatementText, 32000, "C", "{{Claim}}", szUsageName, szSeparatorCharacters )
            {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             {StringBuilder sb_szStatementText;
            if ( szStatementText == null )
               sb_szStatementText = new StringBuilder( 32 );
            else
               sb_szStatementText = new StringBuilder( szStatementText );
                         m_ZGlobal1_Operation.InsertUsageWordsIntoString( mSPLDef, sb_szStatementText, 32000, "C", "{{Claim}}", szUsageName, szSeparatorCharacters );
            szStatementText = sb_szStatementText.toString( );}
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            }
            //:InsertUsageWordsIntoString( mSPLDef, szStatementText, 32000, "S", "{{Surface}}", szUsageName, szSeparatorCharacters )
            {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             {StringBuilder sb_szStatementText;
            if ( szStatementText == null )
               sb_szStatementText = new StringBuilder( 32 );
            else
               sb_szStatementText = new StringBuilder( szStatementText );
                         m_ZGlobal1_Operation.InsertUsageWordsIntoString( mSPLDef, sb_szStatementText, 32000, "S", "{{Surface}}", szUsageName, szSeparatorCharacters );
            szStatementText = sb_szStatementText.toString( );}
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            }
            //:InsertUsageWordsIntoString( mSPLDef, szStatementText, 32000, "U", "{{Area Of Use}}", szUsageName, szSeparatorCharacters )
            {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             {StringBuilder sb_szStatementText;
            if ( szStatementText == null )
               sb_szStatementText = new StringBuilder( 32 );
            else
               sb_szStatementText = new StringBuilder( szStatementText );
                         m_ZGlobal1_Operation.InsertUsageWordsIntoString( mSPLDef, sb_szStatementText, 32000, "U", "{{Area Of Use}}", szUsageName, szSeparatorCharacters );
            szStatementText = sb_szStatementText.toString( );}
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            }
            //:InsertUsageWordsIntoString( mSPLDef, szStatementText, 32000, "T", "{{Application Type}}", szUsageName, szSeparatorCharacters )
            {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             {StringBuilder sb_szStatementText;
            if ( szStatementText == null )
               sb_szStatementText = new StringBuilder( 32 );
            else
               sb_szStatementText = new StringBuilder( szStatementText );
                         m_ZGlobal1_Operation.InsertUsageWordsIntoString( mSPLDef, sb_szStatementText, 32000, "T", "{{Application Type}}", szUsageName, szSeparatorCharacters );
            szStatementText = sb_szStatementText.toString( );}
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            }

            //:szWriteBuffer = szStatementText
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:IF szLineHeight != ""
            if ( ZeidonStringCompare( szLineHeight, 1, 0, "", 1, 0, 33 ) != 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 );
               //:ELSE
            } 
            else
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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
            //:ELSE
         } 
         else
         { 
            //:// There are no Usage entities, so just format the Statement.
            //:szWriteBuffer = szCombinedTitle + szStatementText
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szCombinedTitle, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:IF szLineHeight != ""
            if ( ZeidonStringCompare( szLineHeight, 1, 0, "", 1, 0, 33 ) != 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 );
               //:ELSE
            } 
            else
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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
         } 

         //:END

         //:szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
          {StringBuilder sb_szCombinedTitle;
         if ( szCombinedTitle == null )
            sb_szCombinedTitle = new StringBuilder( 32 );
         else
            sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                  ZeidonStringCopy( sb_szCombinedTitle, 1, 0, "", 1, 0, 257 );
         szCombinedTitle = sb_szCombinedTitle.toString( );}

         //:nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" )
         nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" );
      } 

      //:END
      //:ELSE
   } 
   else
   { 

      //:IF szStatementFormat = "SP"
      if ( ZeidonStringCompare( szStatementFormat, 1, 0, "SP", 1, 0, 3 ) == 0 )
      { 

         //:// SP - Separate Paragraph
         //:// Process each Statement within the Section, simply formatting each as a paragraph.
         //:nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" )
         nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" );
         //:LOOP WHILE nRC >= zCURSOR_SET
         while ( nRC >= zCURSOR_SET )
         { 

            //:GetStringFromAttribute( szWriteBuffer, mSPLDef, szStatementName, "Text" )
            {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                         GetStringFromAttribute( sb_szWriteBuffer, mSPLDef, szStatementName, "Text" );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:szStatementText = szCombinedTitle + szWriteBuffer
             {StringBuilder sb_szStatementText;
            if ( szStatementText == null )
               sb_szStatementText = new StringBuilder( 32 );
            else
               sb_szStatementText = new StringBuilder( szStatementText );
                        ZeidonStringCopy( sb_szStatementText, 1, 0, szCombinedTitle, 1, 0, 32001 );
            szStatementText = sb_szStatementText.toString( );}
             {StringBuilder sb_szStatementText;
            if ( szStatementText == null )
               sb_szStatementText = new StringBuilder( 32 );
            else
               sb_szStatementText = new StringBuilder( szStatementText );
                        ZeidonStringConcat( sb_szStatementText, 1, 0, szWriteBuffer, 1, 0, 32001 );
            szStatementText = sb_szStatementText.toString( );}
            //:szWriteBuffer = szCombinedTitle + szStatementText
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szCombinedTitle, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:IF szLineHeight != ""
            if ( ZeidonStringCompare( szLineHeight, 1, 0, "", 1, 0, 33 ) != 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 );
               //:ELSE
            } 
            else
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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

            //:szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
             {StringBuilder sb_szCombinedTitle;
            if ( szCombinedTitle == null )
               sb_szCombinedTitle = new StringBuilder( 32 );
            else
               sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                        ZeidonStringCopy( sb_szCombinedTitle, 1, 0, "", 1, 0, 257 );
            szCombinedTitle = sb_szCombinedTitle.toString( );}

            //:nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" )
            nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" );
         } 

         //:END

         //:ELSE
      } 
      else
      { 

         //:IF szStatementFormat = "SN"
         if ( ZeidonStringCompare( szStatementFormat, 1, 0, "SN", 1, 0, 3 ) == 0 )
         { 

            //:// SN - Separate Numbered Paragraph
            //:// Process each Statement within the Section, indenting any text that follows a number.
            //:// We will do this by determining if the first character in the text is a number.
            //:// If it is not, we'll simply format as for SP above.
            //:// If it is, we'll find the first character after any spaces following the number and indent that text, after the number.

            //:szWriteBuffer = "            <table>"
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <table>", 1, 0, 32001 );
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

            //:// First process any non-numbered statement preceding numbered statements.
            //:szFoundFirstNumberedEntryFlag = ""
             {StringBuilder sb_szFoundFirstNumberedEntryFlag;
            if ( szFoundFirstNumberedEntryFlag == null )
               sb_szFoundFirstNumberedEntryFlag = new StringBuilder( 32 );
            else
               sb_szFoundFirstNumberedEntryFlag = new StringBuilder( szFoundFirstNumberedEntryFlag );
                        ZeidonStringCopy( sb_szFoundFirstNumberedEntryFlag, 1, 0, "", 1, 0, 2 );
            szFoundFirstNumberedEntryFlag = sb_szFoundFirstNumberedEntryFlag.toString( );}
            //:nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" )
            nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" );
            //:LOOP WHILE nRC >= zCURSOR_SET AND szFoundFirstNumberedEntryFlag = ""
            while ( nRC >= zCURSOR_SET && ZeidonStringCompare( szFoundFirstNumberedEntryFlag, 1, 0, "", 1, 0, 2 ) == 0 )
            { 

               //:GetStringFromAttribute( szStatementText, mSPLDef, szStatementName, "Text" )
               {StringBuilder sb_szStatementText;
               if ( szStatementText == null )
                  sb_szStatementText = new StringBuilder( 32 );
               else
                  sb_szStatementText = new StringBuilder( szStatementText );
                               GetStringFromAttribute( sb_szStatementText, mSPLDef, szStatementName, "Text" );
               szStatementText = sb_szStatementText.toString( );}

               //:// Separate Numbered text, and only process non-numbered entries.
               //:nRC = SeparateNumberedStatement( szStatementText, 32000, szNumberedText )
               {
                ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                nRC = m_ZGlobal1_Operation.SeparateNumberedStatement( szStatementText, 32000, szNumberedText );
                // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
               }
               //:IF nRC = 0
               if ( nRC == 0 )
               { 
                  //:// This is a numbered text entry.
                  //:szFoundFirstNumberedEntryFlag = "Y"
                   {StringBuilder sb_szFoundFirstNumberedEntryFlag;
                  if ( szFoundFirstNumberedEntryFlag == null )
                     sb_szFoundFirstNumberedEntryFlag = new StringBuilder( 32 );
                  else
                     sb_szFoundFirstNumberedEntryFlag = new StringBuilder( szFoundFirstNumberedEntryFlag );
                                    ZeidonStringCopy( sb_szFoundFirstNumberedEntryFlag, 1, 0, "Y", 1, 0, 2 );
                  szFoundFirstNumberedEntryFlag = sb_szFoundFirstNumberedEntryFlag.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:// Text has NOT been separated, so just write out szStatementText.
                  //:szWriteBuffer = szCombinedTitle + szStatementText
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szCombinedTitle, 1, 0, 32001 );
                  szWriteBuffer = sb_szWriteBuffer.toString( );}
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
                  szWriteBuffer = sb_szWriteBuffer.toString( );}
                  //:IF szLineHeight != ""
                  if ( ZeidonStringCompare( szLineHeight, 1, 0, "", 1, 0, 33 ) != 0 )
                  { 
                     //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
                     m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 );
                     //:ELSE
                  } 
                  else
                  { 
                     //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
                     m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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
               } 

               //:END

               //:szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
                {StringBuilder sb_szCombinedTitle;
               if ( szCombinedTitle == null )
                  sb_szCombinedTitle = new StringBuilder( 32 );
               else
                  sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                              ZeidonStringCopy( sb_szCombinedTitle, 1, 0, "", 1, 0, 257 );
               szCombinedTitle = sb_szCombinedTitle.toString( );}

               //:nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" )
               nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" );
            } 


            //:END

            //:nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" )
            nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" );
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

               //:// Separate Numbered text, and only process numbered entries.
               //:nRC = SeparateNumberedStatement( szStatementText, 32000, szNumberedText )
               {
                ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                nRC = m_ZGlobal1_Operation.SeparateNumberedStatement( szStatementText, 32000, szNumberedText );
                // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
               }
               //:IF nRC = 0
               if ( nRC == 0 )
               { 
                  //:// Text has been separated, so write out as two columns.
                  //:szWriteBuffer = "                <tr>"
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                <tr>", 1, 0, 32001 );
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

                  //:szWriteBuffer = "                  <td class=^NumberColumn1^>"
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <td class=^NumberColumn1^>", 1, 0, 32001 );
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
                  //:szWriteBuffer = szNumberedText
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szNumberedText, 1, 0, 32001 );
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
                  //:szWriteBuffer = "                  </td>"
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  </td>", 1, 0, 32001 );
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

                  //:szWriteBuffer = "                  <td>"
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <td>", 1, 0, 32001 );
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
                  //:szWriteBuffer = szStatementText
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
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
                  //:szWriteBuffer = "                  </td>"
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  </td>", 1, 0, 32001 );
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

                  //:szWriteBuffer = "                </tr>"
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                </tr>", 1, 0, 32001 );
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

               //:szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
                {StringBuilder sb_szCombinedTitle;
               if ( szCombinedTitle == null )
                  sb_szCombinedTitle = new StringBuilder( 32 );
               else
                  sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                              ZeidonStringCopy( sb_szCombinedTitle, 1, 0, "", 1, 0, 257 );
               szCombinedTitle = sb_szCombinedTitle.toString( );}

               //:nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" )
               nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" );
            } 


            //:END

            //:szWriteBuffer = "            </table>"
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            </table>", 1, 0, 32001 );
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

            //:IF szStatementFormat = "L3"
            if ( ZeidonStringCompare( szStatementFormat, 1, 0, "L3", 1, 0, 3 ) == 0 )
            { 

               //:// LIST3 - 3 Column List of Marketing Claim Usage Statements
               //:GenerateHTML_ClmList3( mSPLDef, lFile, szCombinedTitle,
               //:              szWriteBuffer, szStatementText )
               omSPLDef_GenerateHTML_ClmList3( mSPLDef, lFile, szCombinedTitle, szWriteBuffer, szStatementText );
            } 

            //:END
         } 


         //:END
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GeneratePDF_DFU( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                  INTEGER lFile,
//:                  STRING ( 32 ) szSPLDT_SectionName,
//:                  STRING ( 32 ) szSPLD_SectionName,
//:                  STRING ( 32 ) szStatementName,
//:                  STRING ( 32 ) szUsageName,
//:                  STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 32000 )  szStatementText
public int 
omSPLDef_GeneratePDF_DFU( View     mSPLDef,
                          int      lFile,
                          String   szSPLDT_SectionName,
                          String   szSPLD_SectionName,
                          String   szStatementName,
                          String   szUsageName,
                          String   szWriteBuffer )
{
   String   szStatementText = null;
   //:STRING ( 256 )    szCombinedTitle
   String   szCombinedTitle = null;
   //:STRING ( 10 )     szSeparatorCharacters
   String   szSeparatorCharacters = null;
   //:STRING ( 10 )     szNumberedText
   String   szNumberedText = null;
   //:STRING ( 32 )     szLineHeight
   String   szLineHeight = null;
   //:STRING ( 10 )     szStatementLeading
   String   szStatementLeading = null;
   //:STRING ( 2 )      szStatementFormat
   String   szStatementFormat = null;
   //:STRING ( 1 )      szFoundFirstNumberedEntryFlag
   String   szFoundFirstNumberedEntryFlag = null;
   //:INTEGER           lCnt
   int      lCnt = 0;
   //:INTEGER           lObjectID
   int      lObjectID = 0;
   //:SHORT             nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:// Generate PDF for a "Directory of Use" or "Marketing" Section.

   //:szLineHeight = ""
    {StringBuilder sb_szLineHeight;
   if ( szLineHeight == null )
      sb_szLineHeight = new StringBuilder( 32 );
   else
      sb_szLineHeight = new StringBuilder( szLineHeight );
      ZeidonStringCopy( sb_szLineHeight, 1, 0, "", 1, 0, 33 );
   szLineHeight = sb_szLineHeight.toString( );}
   //:szStatementLeading = mSPLDef.SPLD_TemplateSection.dStatementLeading
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szStatementLeading;
   if ( szStatementLeading == null )
      sb_szStatementLeading = new StringBuilder( 32 );
   else
      sb_szStatementLeading = new StringBuilder( szStatementLeading );
       GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_0, 'S', 11, mSPLDef, "SPLD_TemplateSection", "dStatementLeading", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szStatementLeading = sb_szStatementLeading.toString( );}
   //:IF szStatementLeading != ""
   if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szLineHeight = "line-height:" + szStatementLeading +";"
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szStatementLeading = mSPLDef.SPLD_TemplatePanel.dStatementLeadingDefault
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szStatementLeading;
      if ( szStatementLeading == null )
         sb_szStatementLeading = new StringBuilder( 32 );
      else
         sb_szStatementLeading = new StringBuilder( szStatementLeading );
             GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "dStatementLeadingDefault", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szStatementLeading = sb_szStatementLeading.toString( );}
      //:IF szStatementLeading != ""
      if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szLineHeight = "line-height:" + szStatementLeading +";"
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
      } 

      //:END
   } 

   //:END

   //://SET CURSOR FIRST mSPLDef.SPLD_DirectionsForUseSection WHERE mSPLDef.SPLD_DirectionsForUseSection.ID = mSPLDef.SPLDT_DirectionsForUseSection.ID
   //:GetIntegerFromAttribute( lObjectID, mSPLDef, szSPLDT_SectionName, "ID" )
   {MutableInt mi_lObjectID = new MutableInt( lObjectID );
       GetIntegerFromAttribute( mi_lObjectID, mSPLDef, szSPLDT_SectionName, "ID" );
   lObjectID = mi_lObjectID.intValue( );}
   //:SetCursorFirstEntityByInteger( mSPLDef, szSPLD_SectionName, "ID", lObjectID, "" )
   SetCursorFirstEntityByInteger( mSPLDef, szSPLD_SectionName, "ID", lObjectID, "" );
   //:// GetStringFromAttribute( szTitle, mSPLDef, szSPLD_SectionName, "Title" )
   //:// DisplayEntityInstance( mSPLDef, szSPLD_SectionName )
   //:GeneratePDF_Title( mSPLDef, lFile, szSPLD_SectionName, szCombinedTitle, szWriteBuffer )
   omSPLDef_GeneratePDF_Title( mSPLDef, lFile, szSPLD_SectionName, szCombinedTitle, szWriteBuffer );

   //:szStatementFormat = mSPLDef.SPLD_TemplateSection.StatementFormat
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szStatementFormat;
   if ( szStatementFormat == null )
      sb_szStatementFormat = new StringBuilder( 32 );
   else
      sb_szStatementFormat = new StringBuilder( szStatementFormat );
       GetVariableFromAttribute( sb_szStatementFormat, mi_lTempInteger_2, 'S', 3, mSPLDef, "SPLD_TemplateSection", "StatementFormat", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szStatementFormat = sb_szStatementFormat.toString( );}
   //:TraceLineS( "GeneratePDF_DFU Statement Format: ", szStatementFormat )
   TraceLineS( "GeneratePDF_DFU Statement Format: ", szStatementFormat );
   //:IF szStatementFormat = "PU" OR szStatementFormat = ""
   if ( ZeidonStringCompare( szStatementFormat, 1, 0, "PU", 1, 0, 3 ) == 0 || ZeidonStringCompare( szStatementFormat, 1, 0, "", 1, 0, 3 ) == 0 )
   { 

      //:// PU - Paragraph with Usage Statements
      //:// Process each Statement within the Section, using the option that combines Usage entries into the paragraph.
      //:nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" )
      nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" );
      //:LOOP WHILE nRC >= zCURSOR_SET
      while ( nRC >= zCURSOR_SET )
      { 

         //:// Process any merged Usage entries.
         //:// Directions for Use can have "Usage" words merged into the regular paragraph text. This is managed through the following key words.
         //:// {{Usage}} This merges all the Usage statements together in their entity order.
         //:// For the following, only that type is merged:
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
         //:// We will process this by calling the Operation, MergeUsageParagraph, for each keyword.
         //:GetStringFromAttribute( szWriteBuffer, mSPLDef, szStatementName, "Text" )
         {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                   GetStringFromAttribute( sb_szWriteBuffer, mSPLDef, szStatementName, "Text" );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
         //:szStatementText = szCombinedTitle + szWriteBuffer
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                  ZeidonStringCopy( sb_szStatementText, 1, 0, szCombinedTitle, 1, 0, 32001 );
         szStatementText = sb_szStatementText.toString( );}
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                  ZeidonStringConcat( sb_szStatementText, 1, 0, szWriteBuffer, 1, 0, 32001 );
         szStatementText = sb_szStatementText.toString( );}
         //:szSeparatorCharacters = mSPLDef.SPLD_TemplateSection.UsageSeparatorCharacters
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szSeparatorCharacters;
         if ( szSeparatorCharacters == null )
            sb_szSeparatorCharacters = new StringBuilder( 32 );
         else
            sb_szSeparatorCharacters = new StringBuilder( szSeparatorCharacters );
                   GetVariableFromAttribute( sb_szSeparatorCharacters, mi_lTempInteger_3, 'S', 11, mSPLDef, "SPLD_TemplateSection", "UsageSeparatorCharacters", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         szSeparatorCharacters = sb_szSeparatorCharacters.toString( );}
         //:nRC = CheckExistenceOfEntity( mSPLDef, szUsageName )
         nRC = CheckExistenceOfEntity( mSPLDef, szUsageName );
         //:IF nRC >= zCURSOR_SET
         if ( nRC >= zCURSOR_SET )
         { 
            //:InsertUsageWordsIntoString( mSPLDef, szStatementText, 32000, "A", "{{Usage}}", szUsageName, szSeparatorCharacters )
            {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             {StringBuilder sb_szStatementText;
            if ( szStatementText == null )
               sb_szStatementText = new StringBuilder( 32 );
            else
               sb_szStatementText = new StringBuilder( szStatementText );
                         m_ZGlobal1_Operation.InsertUsageWordsIntoString( mSPLDef, sb_szStatementText, 32000, "A", "{{Usage}}", szUsageName, szSeparatorCharacters );
            szStatementText = sb_szStatementText.toString( );}
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            }
            //:InsertUsageWordsIntoString( mSPLDef, szStatementText, 32000, "C", "{{Claim}}", szUsageName, szSeparatorCharacters )
            {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             {StringBuilder sb_szStatementText;
            if ( szStatementText == null )
               sb_szStatementText = new StringBuilder( 32 );
            else
               sb_szStatementText = new StringBuilder( szStatementText );
                         m_ZGlobal1_Operation.InsertUsageWordsIntoString( mSPLDef, sb_szStatementText, 32000, "C", "{{Claim}}", szUsageName, szSeparatorCharacters );
            szStatementText = sb_szStatementText.toString( );}
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            }
            //:InsertUsageWordsIntoString( mSPLDef, szStatementText, 32000, "S", "{{Surface}}", szUsageName, szSeparatorCharacters )
            {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             {StringBuilder sb_szStatementText;
            if ( szStatementText == null )
               sb_szStatementText = new StringBuilder( 32 );
            else
               sb_szStatementText = new StringBuilder( szStatementText );
                         m_ZGlobal1_Operation.InsertUsageWordsIntoString( mSPLDef, sb_szStatementText, 32000, "S", "{{Surface}}", szUsageName, szSeparatorCharacters );
            szStatementText = sb_szStatementText.toString( );}
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            }
            //:InsertUsageWordsIntoString( mSPLDef, szStatementText, 32000, "U", "{{Area Of Use}}", szUsageName, szSeparatorCharacters )
            {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             {StringBuilder sb_szStatementText;
            if ( szStatementText == null )
               sb_szStatementText = new StringBuilder( 32 );
            else
               sb_szStatementText = new StringBuilder( szStatementText );
                         m_ZGlobal1_Operation.InsertUsageWordsIntoString( mSPLDef, sb_szStatementText, 32000, "U", "{{Area Of Use}}", szUsageName, szSeparatorCharacters );
            szStatementText = sb_szStatementText.toString( );}
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            }
            //:InsertUsageWordsIntoString( mSPLDef, szStatementText, 32000, "T", "{{Application Type}}", szUsageName, szSeparatorCharacters )
            {
             ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
             {StringBuilder sb_szStatementText;
            if ( szStatementText == null )
               sb_szStatementText = new StringBuilder( 32 );
            else
               sb_szStatementText = new StringBuilder( szStatementText );
                         m_ZGlobal1_Operation.InsertUsageWordsIntoString( mSPLDef, sb_szStatementText, 32000, "T", "{{Application Type}}", szUsageName, szSeparatorCharacters );
            szStatementText = sb_szStatementText.toString( );}
             // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
            }

            //:szWriteBuffer = szStatementText
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:IF szLineHeight != ""
            if ( ZeidonStringCompare( szLineHeight, 1, 0, "", 1, 0, 33 ) != 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 );
               //:ELSE
            } 
            else
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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
            //:ELSE
         } 
         else
         { 
            //:// There are no Usage entities, so just format the Statement.
            //:szWriteBuffer = szCombinedTitle + szStatementText
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szCombinedTitle, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:IF szLineHeight != ""
            if ( ZeidonStringCompare( szLineHeight, 1, 0, "", 1, 0, 33 ) != 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 );
               //:ELSE
            } 
            else
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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
         } 

         //:END

         //:szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
          {StringBuilder sb_szCombinedTitle;
         if ( szCombinedTitle == null )
            sb_szCombinedTitle = new StringBuilder( 32 );
         else
            sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                  ZeidonStringCopy( sb_szCombinedTitle, 1, 0, "", 1, 0, 257 );
         szCombinedTitle = sb_szCombinedTitle.toString( );}

         //:nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" )
         nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" );
      } 

      //:END
      //:ELSE
   } 
   else
   { 

      //:IF szStatementFormat = "SP"
      if ( ZeidonStringCompare( szStatementFormat, 1, 0, "SP", 1, 0, 3 ) == 0 )
      { 

         //:// SP - Separate Paragraph
         //:// Process each Statement within the Section, simply formatting each as a paragraph.
         //:nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" )
         nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" );
         //:LOOP WHILE nRC >= zCURSOR_SET
         while ( nRC >= zCURSOR_SET )
         { 

            //:GetStringFromAttribute( szWriteBuffer, mSPLDef, szStatementName, "Text" )
            {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                         GetStringFromAttribute( sb_szWriteBuffer, mSPLDef, szStatementName, "Text" );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:szStatementText = szCombinedTitle + szWriteBuffer
             {StringBuilder sb_szStatementText;
            if ( szStatementText == null )
               sb_szStatementText = new StringBuilder( 32 );
            else
               sb_szStatementText = new StringBuilder( szStatementText );
                        ZeidonStringCopy( sb_szStatementText, 1, 0, szCombinedTitle, 1, 0, 32001 );
            szStatementText = sb_szStatementText.toString( );}
             {StringBuilder sb_szStatementText;
            if ( szStatementText == null )
               sb_szStatementText = new StringBuilder( 32 );
            else
               sb_szStatementText = new StringBuilder( szStatementText );
                        ZeidonStringConcat( sb_szStatementText, 1, 0, szWriteBuffer, 1, 0, 32001 );
            szStatementText = sb_szStatementText.toString( );}
            //:szWriteBuffer = szCombinedTitle + szStatementText
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szCombinedTitle, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:IF szLineHeight != ""
            if ( ZeidonStringCompare( szLineHeight, 1, 0, "", 1, 0, 33 ) != 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 );
               //:ELSE
            } 
            else
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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

            //:szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
             {StringBuilder sb_szCombinedTitle;
            if ( szCombinedTitle == null )
               sb_szCombinedTitle = new StringBuilder( 32 );
            else
               sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                        ZeidonStringCopy( sb_szCombinedTitle, 1, 0, "", 1, 0, 257 );
            szCombinedTitle = sb_szCombinedTitle.toString( );}

            //:nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" )
            nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" );
         } 

         //:END

         //:ELSE
      } 
      else
      { 

         //:IF szStatementFormat = "SN"
         if ( ZeidonStringCompare( szStatementFormat, 1, 0, "SN", 1, 0, 3 ) == 0 )
         { 

            //:// SN - Separate Numbered Paragraph
            //:// Process each Statement within the Section, indenting any text that follows a number.
            //:// We will do this by determining if the first character in the text is a number.
            //:// If it is not, we'll simply format as for SP above.
            //:// If it is, we'll find the first character after any spaces following the number and indent that text, after the number.

            //:szWriteBuffer = "            <table>"
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <table>", 1, 0, 32001 );
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

            //:// First process any non-numbered statement preceding numbered statements.
            //:szFoundFirstNumberedEntryFlag = ""
             {StringBuilder sb_szFoundFirstNumberedEntryFlag;
            if ( szFoundFirstNumberedEntryFlag == null )
               sb_szFoundFirstNumberedEntryFlag = new StringBuilder( 32 );
            else
               sb_szFoundFirstNumberedEntryFlag = new StringBuilder( szFoundFirstNumberedEntryFlag );
                        ZeidonStringCopy( sb_szFoundFirstNumberedEntryFlag, 1, 0, "", 1, 0, 2 );
            szFoundFirstNumberedEntryFlag = sb_szFoundFirstNumberedEntryFlag.toString( );}
            //:nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" )
            nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" );
            //:LOOP WHILE nRC >= zCURSOR_SET AND szFoundFirstNumberedEntryFlag = ""
            while ( nRC >= zCURSOR_SET && ZeidonStringCompare( szFoundFirstNumberedEntryFlag, 1, 0, "", 1, 0, 2 ) == 0 )
            { 

               //:GetStringFromAttribute( szStatementText, mSPLDef, szStatementName, "Text" )
               {StringBuilder sb_szStatementText;
               if ( szStatementText == null )
                  sb_szStatementText = new StringBuilder( 32 );
               else
                  sb_szStatementText = new StringBuilder( szStatementText );
                               GetStringFromAttribute( sb_szStatementText, mSPLDef, szStatementName, "Text" );
               szStatementText = sb_szStatementText.toString( );}

               //:// Separate Numbered text, and only process non-numbered entries.
               //:nRC = SeparateNumberedStatement( szStatementText, 32000, szNumberedText )
               {
                ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                nRC = m_ZGlobal1_Operation.SeparateNumberedStatement( szStatementText, 32000, szNumberedText );
                // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
               }
               //:IF nRC = 0
               if ( nRC == 0 )
               { 
                  //:// This is a numbered text entry.
                  //:szFoundFirstNumberedEntryFlag = "Y"
                   {StringBuilder sb_szFoundFirstNumberedEntryFlag;
                  if ( szFoundFirstNumberedEntryFlag == null )
                     sb_szFoundFirstNumberedEntryFlag = new StringBuilder( 32 );
                  else
                     sb_szFoundFirstNumberedEntryFlag = new StringBuilder( szFoundFirstNumberedEntryFlag );
                                    ZeidonStringCopy( sb_szFoundFirstNumberedEntryFlag, 1, 0, "Y", 1, 0, 2 );
                  szFoundFirstNumberedEntryFlag = sb_szFoundFirstNumberedEntryFlag.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:// Text has NOT been separated, so just write out szStatementText.
                  //:szWriteBuffer = szCombinedTitle + szStatementText
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szCombinedTitle, 1, 0, 32001 );
                  szWriteBuffer = sb_szWriteBuffer.toString( );}
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
                  szWriteBuffer = sb_szWriteBuffer.toString( );}
                  //:IF szLineHeight != ""
                  if ( ZeidonStringCompare( szLineHeight, 1, 0, "", 1, 0, 33 ) != 0 )
                  { 
                     //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
                     m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 );
                     //:ELSE
                  } 
                  else
                  { 
                     //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
                     m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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
               } 

               //:END

               //:szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
                {StringBuilder sb_szCombinedTitle;
               if ( szCombinedTitle == null )
                  sb_szCombinedTitle = new StringBuilder( 32 );
               else
                  sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                              ZeidonStringCopy( sb_szCombinedTitle, 1, 0, "", 1, 0, 257 );
               szCombinedTitle = sb_szCombinedTitle.toString( );}

               //:nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" )
               nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" );
            } 


            //:END

            //:nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" )
            nRC = SetCursorFirstEntity( mSPLDef, szStatementName, "" );
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

               //:// Separate Numbered text, and only process numbered entries.
               //:nRC = SeparateNumberedStatement( szStatementText, 32000, szNumberedText )
               {
                ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
                nRC = m_ZGlobal1_Operation.SeparateNumberedStatement( szStatementText, 32000, szNumberedText );
                // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
               }
               //:IF nRC = 0
               if ( nRC == 0 )
               { 
                  //:// Text has been separated, so write out as two columns.
                  //:szWriteBuffer = "                <tr>"
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                <tr>", 1, 0, 32001 );
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

                  //:szWriteBuffer = "                  <td class=^NumberColumn1^>"
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <td class=^NumberColumn1^>", 1, 0, 32001 );
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
                  //:szWriteBuffer = szNumberedText
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szNumberedText, 1, 0, 32001 );
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
                  //:szWriteBuffer = "                  </td>"
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  </td>", 1, 0, 32001 );
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

                  //:szWriteBuffer = "                  <td>"
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  <td>", 1, 0, 32001 );
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
                  //:szWriteBuffer = szStatementText
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
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
                  //:szWriteBuffer = "                  </td>"
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                  </td>", 1, 0, 32001 );
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

                  //:szWriteBuffer = "                </tr>"
                   {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                                    ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                </tr>", 1, 0, 32001 );
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

               //:szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
                {StringBuilder sb_szCombinedTitle;
               if ( szCombinedTitle == null )
                  sb_szCombinedTitle = new StringBuilder( 32 );
               else
                  sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                              ZeidonStringCopy( sb_szCombinedTitle, 1, 0, "", 1, 0, 257 );
               szCombinedTitle = sb_szCombinedTitle.toString( );}

               //:nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" )
               nRC = SetCursorNextEntity( mSPLDef, szStatementName, "" );
            } 


            //:END

            //:szWriteBuffer = "            </table>"
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            </table>", 1, 0, 32001 );
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

            //:IF szStatementFormat = "L3"
            if ( ZeidonStringCompare( szStatementFormat, 1, 0, "L3", 1, 0, 3 ) == 0 )
            { 

               //:// LIST3 - 3 Column List of Marketing Claim Usage Statements
               //:GeneratePDF_ClmList3( mSPLDef, lFile, szCombinedTitle,
               //:              szWriteBuffer, szStatementText )
               omSPLDef_GeneratePDF_ClmList3( mSPLDef, lFile, szCombinedTitle, szWriteBuffer, szStatementText );
            } 

            //:END
         } 


         //:END
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateHTML_ClmList3( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                       INTEGER lFile,
//:                       STRING ( 256 )   szCombinedTitle,
//:                       STRING ( 32000 ) szWriteBuffer,
//:                       STRING ( 32000 ) szStatementText )

//:   STRING ( 32 )     szAttributeName
public int 
omSPLDef_GenerateHTML_ClmList3( View     mSPLDef,
                                int      lFile,
                                String   szCombinedTitle,
                                String   szWriteBuffer,
                                String   szStatementText )
{
   String   szAttributeName = null;
   //:STRING ( 32 )     szLineHeight
   String   szLineHeight = null;
   //:STRING ( 256 )    szRowHeight
   String   szRowHeight = null;
   //:STRING ( 10 )     szStatementLeading
   String   szStatementLeading = null;
   //:STRING ( 10 )     szSizeX
   String   szSizeX = null;
   //:INTEGER           lCnt
   int      lCnt = 0;
   //:INTEGER           lTempCnt
   int      lTempCnt = 0;
   //:INTEGER           lTotalRows
   int      lTotalRows = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_9 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_10 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_11 = 0;

   //:// INTEGER           lTempTotalCnt

   //:szLineHeight = ""
    {StringBuilder sb_szLineHeight;
   if ( szLineHeight == null )
      sb_szLineHeight = new StringBuilder( 32 );
   else
      sb_szLineHeight = new StringBuilder( szLineHeight );
      ZeidonStringCopy( sb_szLineHeight, 1, 0, "", 1, 0, 33 );
   szLineHeight = sb_szLineHeight.toString( );}
   //:szStatementLeading = mSPLDef.SPLD_TemplateSection.dStatementLeading
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szStatementLeading;
   if ( szStatementLeading == null )
      sb_szStatementLeading = new StringBuilder( 32 );
   else
      sb_szStatementLeading = new StringBuilder( szStatementLeading );
       GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_0, 'S', 11, mSPLDef, "SPLD_TemplateSection", "dStatementLeading", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szStatementLeading = sb_szStatementLeading.toString( );}
   //:IF szStatementLeading != ""
   if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szLineHeight = "line-height:" + szStatementLeading +";"
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szStatementLeading = mSPLDef.SPLD_TemplatePanel.dStatementLeadingDefault
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szStatementLeading;
      if ( szStatementLeading == null )
         sb_szStatementLeading = new StringBuilder( 32 );
      else
         sb_szStatementLeading = new StringBuilder( szStatementLeading );
             GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "dStatementLeadingDefault", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szStatementLeading = sb_szStatementLeading.toString( );}
      //:IF szStatementLeading != ""
      if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szLineHeight = "line-height:" + szStatementLeading +";"
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
      } 

      //:END
   } 

   //:END

   //:szRowHeight = szStatementLeading
    {StringBuilder sb_szRowHeight;
   if ( szRowHeight == null )
      sb_szRowHeight = new StringBuilder( 32 );
   else
      sb_szRowHeight = new StringBuilder( szRowHeight );
      ZeidonStringCopy( sb_szRowHeight, 1, 0, szStatementLeading, 1, 0, 257 );
   szRowHeight = sb_szRowHeight.toString( );}

   //:// LIST3 - 3 Column List of Claim Usage Statements
   //:// The dependent Claim Usage statements are to be listed in 3 columns, after any regular Statements
   //:// and organized by Claims Classifications.
   //:// IssueError( mSPLDef, 0, 0, "At Beginning" )

   //:// First, process each Statement within the Section, simply formatting each as a paragraph.
   //:lCnt = 0
   lCnt = 0;
   //:FOR EACH mSPLDef.SPLD_MarketingStatement
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// DisplayEntityInstance( mSPLDef, "SPLD_MarketingStatement" )
      //:lCnt = lCnt + 1
      lCnt = lCnt + 1;
      //:szWriteBuffer = mSPLDef.SPLD_MarketingStatement.Text
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
             GetVariableFromAttribute( sb_szWriteBuffer, mi_lTempInteger_2, 'S', 32001, mSPLDef, "SPLD_MarketingStatement", "Text", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:IF lCnt = 1
      if ( lCnt == 1 )
      { 
         //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", "ClaimsTitle", zQUOTES, 1 )
         m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", "ClaimsTitle", zQUOTES, 1 );
         //:ELSE
      } 
      else
      { 
         //:IF lCnt = 2
         if ( lCnt == 2 )
         { 
            //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", "ClaimsSubTitle", zQUOTES, 1 )
            m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", "ClaimsSubTitle", zQUOTES, 1 );
            //:ELSE
         } 
         else
         { 
            //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
            m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
         } 

         //:END
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
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingStatement", "" );
   } 


   //:   //szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
   //:END

   //:// Build the work object containing each Claim under the appropriate Classification.
   //:// Type: C - Claim;  S - Surface;  T - Application Type;  U - Area of Use.
   //:// ClaimsClassification: Bacteria; Protozoa; Viruses; Fungi.
   //:FOR EACH mSPLDef.SPLD_MarketingOrdering WHERE mSPLDef.SPLD_MarketingUsage.UsageType = "C" // just looking for Type=Claim
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingOrdering", "" );
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
               //:TraceLineS( "GenerateHTML_ClmList3 Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ======== CAN'T HAPPEN ===========================" )
               TraceLineS( "GenerateHTML_ClmList3 Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ======== CAN'T HAPPEN ===========================" );
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
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                         GetIntegerFromAttribute( mi_lTempInteger_3, mSPLDef, "SPLD_MarketingUsage", "ID" );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLDI_ClaimsUsage", "ID", lTempInteger_3, "" );
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
                  //:TraceLineS( "GenerateHTML_ClmList3 Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ===========CAN'T HAPPEN EITHER ========================" )
                  TraceLineS( "GenerateHTML_ClmList3 Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ===========CAN'T HAPPEN EITHER ========================" );
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
               //:TraceLineS( "GenerateHTML_ClmList3 Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ====== ALREADY INCLUDED ... COULD HAPPEN =============================" )
               TraceLineS( "GenerateHTML_ClmList3 Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ====== ALREADY INCLUDED ... COULD HAPPEN =============================" );
               //:// DisplayEntityInstance( mSPLDef, "SPLDI_ClaimsUsage" )
               //:DisplayEntityInstance( mSPLDef, "SPLD_MarketingUsage" )
               DisplayEntityInstance( mSPLDef, "SPLD_MarketingUsage" );
            } 

            //:END
         } 

      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingOrdering", "" );
      //:END
   } 

   //:END

   //:// Determine how many total rows there will be.
   //:// We get an entry for each Classification (virus/bacteria/...) and an entry for each Claim.
   //:lTotalRows = 0
   lTotalRows = 0;
   //:FOR EACH mSPLDef.SPLDI_ClaimsClassification
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_ClaimsClassification", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:lTotalRows = lTotalRows + 1
      lTotalRows = lTotalRows + 1;
      //:// For each Classification after the first, add 1 for a blank entry (prior to the classification title).
      //:IF lTotalRows > 1
      if ( lTotalRows > 1 )
      { 
         //:lTotalRows = lTotalRows + 1
         lTotalRows = lTotalRows + 1;
      } 

      //:END

      //:FOR EACH mSPLDef.SPLDI_ClaimsUsage
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_ClaimsUsage", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:szStatementText = mSPLDef.SPLDI_ClaimsUsage.Name
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
         StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                   GetVariableFromAttribute( sb_szStatementText, mi_lTempInteger_4, 'S', 32001, mSPLDef, "SPLDI_ClaimsUsage", "Name", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );
         szStatementText = sb_szStatementText.toString( );}

         //:// Count the number of <p> attributes at the "top level" in this usage.
         //:lTempCnt = AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "", "", "", 0 )
         lTempCnt = m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "", "", "", 0 );
         //:IF lTempCnt = 0  // there were no <p> attributes in the original, but added 1
         if ( lTempCnt == 0 )
         { 
            //:lTempCnt = 1
            lTempCnt = 1;
         } 

         //:END

         //:lTotalRows = lTotalRows + lTempCnt
         lTotalRows = lTotalRows + lTempCnt;
         RESULT = SetCursorNextEntity( mSPLDef, "SPLDI_ClaimsUsage", "" );
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLDI_ClaimsClassification", "" );
      //:END
   } 

   //:END

   //:IF lTotalRows <= 4
   if ( lTotalRows <= 4 )
   { 
      //:MessageSend( mSPLDef, "", "Generate Usage List",
      //:             "A list must have have least 4 Usage Statements.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mSPLDef, "", "Generate Usage List", "A list must have have least 4 Usage Statements.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:lTotalRows = (lTotalRows + 2) / 3  // round up to the next multiple of 3
   lTotalRows = ( lTotalRows + 2 ) / 3;

   //:// Delete any existing work entities.
   //:FOR EACH mSPLDef.WkListRow
   RESULT = SetCursorFirstEntity( mSPLDef, "WkListRow", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mSPLDef.WkListRow NONE
      RESULT = DeleteEntity( mSPLDef, "WkListRow", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
   } 

   //:END

   //:// Loop through the rows setting the values.
   //:// As we loop, the first "lTotalRows" entries will go into ColumnValue1, the second into ColumnValue2
   //:// and the third into ColumnValue3.

   //:szAttributeName = "ColumnValue1"
    {StringBuilder sb_szAttributeName;
   if ( szAttributeName == null )
      sb_szAttributeName = new StringBuilder( 32 );
   else
      sb_szAttributeName = new StringBuilder( szAttributeName );
      ZeidonStringCopy( sb_szAttributeName, 1, 0, "ColumnValue1", 1, 0, 33 );
   szAttributeName = sb_szAttributeName.toString( );}
   //:lCnt = 0
   lCnt = 0;
   //:FOR EACH mSPLDef.SPLDI_ClaimsClassification  // Bacteria; Protozoa; Viruses; Fungi
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_ClaimsClassification", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:lCnt = lCnt + 1
      lCnt = lCnt + 1;
      //:IF szAttributeName = "ColumnValue1"  // processing first column ... set Classification in new entity.
      if ( ZeidonStringCompare( szAttributeName, 1, 0, "ColumnValue1", 1, 0, 33 ) == 0 )
      { 
         //:CREATE ENTITY mSPLDef.WkListRow
         RESULT = CreateEntity( mSPLDef, "WkListRow", zPOS_AFTER );
         //:szStatementText = mSPLDef.SPLDI_ClaimsClassification.ClaimsClassification
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
         StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                   GetVariableFromAttribute( sb_szStatementText, mi_lTempInteger_5, 'S', 32001, mSPLDef, "SPLDI_ClaimsClassification", "ClaimsClassification", "", 0 );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );
         szStatementText = sb_szStatementText.toString( );}
         //:AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "class", "ClaimsClassification", zQUOTES, 1 )
         m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "class", "ClaimsClassification", zQUOTES, 1 );
         //:SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText )
         SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText );
         //:ELSE
      } 
      else
      { 
         //:// We're processing second or third column, set Classification in existing entity after a blank entry.
         //:SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, "<p>&nbsp</p>" )  // add blank before the new classification
         SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, "<p>&nbsp</p>" );
         //:SET CURSOR NEXT mSPLDef.WkListRow
         RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
         //:szStatementText = mSPLDef.SPLDI_ClaimsClassification.ClaimsClassification
         {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
         StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                   GetVariableFromAttribute( sb_szStatementText, mi_lTempInteger_6, 'S', 32001, mSPLDef, "SPLDI_ClaimsClassification", "ClaimsClassification", "", 0 );
         lTempInteger_6 = mi_lTempInteger_6.intValue( );
         szStatementText = sb_szStatementText.toString( );}
         //:AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "class", "ClaimsClassification", zQUOTES, 1 )
         m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "class", "ClaimsClassification", zQUOTES, 1 );
         //:SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText )
         SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText );
         //:SET CURSOR NEXT mSPLDef.WkListRow
         RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
      } 

      //:END

      //:IF lCnt = lTotalRows
      if ( lCnt == lTotalRows )
      { 
         //:// We've come to the end of the rows, so change Attribute Name, restart count and reposition at beginning of mSPLDef.WkListRow.
         //:IF szAttributeName = "ColumnValue1"
         if ( ZeidonStringCompare( szAttributeName, 1, 0, "ColumnValue1", 1, 0, 33 ) == 0 )
         { 
            //:szAttributeName = "ColumnValue2"
             {StringBuilder sb_szAttributeName;
            if ( szAttributeName == null )
               sb_szAttributeName = new StringBuilder( 32 );
            else
               sb_szAttributeName = new StringBuilder( szAttributeName );
                        ZeidonStringCopy( sb_szAttributeName, 1, 0, "ColumnValue2", 1, 0, 33 );
            szAttributeName = sb_szAttributeName.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szAttributeName = "ColumnValue3"
             {StringBuilder sb_szAttributeName;
            if ( szAttributeName == null )
               sb_szAttributeName = new StringBuilder( 32 );
            else
               sb_szAttributeName = new StringBuilder( szAttributeName );
                        ZeidonStringCopy( sb_szAttributeName, 1, 0, "ColumnValue3", 1, 0, 33 );
            szAttributeName = sb_szAttributeName.toString( );}
         } 

         //:END

         //:lCnt = 0
         lCnt = 0;
         //:SET CURSOR FIRST mSPLDef.WkListRow
         RESULT = SetCursorFirstEntity( mSPLDef, "WkListRow", "" );
      } 

      //:END

      //:FOR EACH mSPLDef.SPLDI_ClaimsUsage
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_ClaimsUsage", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 

         //:szStatementText = mSPLDef.SPLDI_ClaimsUsage.Name
         {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
         StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                   GetVariableFromAttribute( sb_szStatementText, mi_lTempInteger_7, 'S', 32001, mSPLDef, "SPLDI_ClaimsUsage", "Name", "", 0 );
         lTempInteger_7 = mi_lTempInteger_7.intValue( );
         szStatementText = sb_szStatementText.toString( );}

         //:// Count the number of <p> attributes at the "top level" in this usage.
         //:lTempCnt = AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "", "", "", 0 )
         lTempCnt = m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "", "", "", 0 );
         //:IF lTempCnt = 0  // there were no <p> attributes in the original, but added 1
         if ( lTempCnt == 0 )
         { 
            //:lTempCnt = 1
            lTempCnt = 1;
         } 

         //:END

         //:lCnt = lCnt + lTempCnt
         lCnt = lCnt + lTempCnt;
         //:IF szAttributeName = "ColumnValue1"
         if ( ZeidonStringCompare( szAttributeName, 1, 0, "ColumnValue1", 1, 0, 33 ) == 0 )
         { 
            //:// We're processing first column, set Claim in new entity.
            //:IF lTempCnt = 1
            if ( lTempCnt == 1 )
            { 
               //:CREATE ENTITY mSPLDef.WkListRow
               RESULT = CreateEntity( mSPLDef, "WkListRow", zPOS_AFTER );
               //:SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText )
               SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText );
               //:ELSE
            } 
            else
            { 
               //:szWriteBuffer = szStatementText
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:LOOP WHILE lTempCnt > 0
               while ( lTempCnt > 0 )
               { 
                  //:RemoveTagFromHTML( mSPLDef, szStatementText, szWriteBuffer, "p" )
                  {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  StringBuilder sb_szStatementText;
                  if ( szStatementText == null )
                     sb_szStatementText = new StringBuilder( 32 );
                  else
                     sb_szStatementText = new StringBuilder( szStatementText );
                                     m_ZDRVROPR.RemoveTagFromHTML( mSPLDef, sb_szStatementText, sb_szWriteBuffer, "p" );
                  szWriteBuffer = sb_szWriteBuffer.toString( );
                  szStatementText = sb_szStatementText.toString( );}
                  //:CREATE ENTITY mSPLDef.WkListRow
                  RESULT = CreateEntity( mSPLDef, "WkListRow", zPOS_AFTER );
                  //:SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText )
                  SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText );
                  //:lTempCnt = lTempCnt - 1
                  lTempCnt = lTempCnt - 1;
               } 

               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:// We're processing second or third column, set Classification in existing entity.
            //:IF lTempCnt = 1
            if ( lTempCnt == 1 )
            { 
               //:SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText )
               SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText );
               //:SET CURSOR NEXT mSPLDef.WkListRow
               RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
               //:ELSE
            } 
            else
            { 
               //:szWriteBuffer = szStatementText
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:LOOP WHILE lTempCnt > 0
               while ( lTempCnt > 0 )
               { 
                  //:RemoveTagFromHTML( mSPLDef, szStatementText, szWriteBuffer, "p" )
                  {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  StringBuilder sb_szStatementText;
                  if ( szStatementText == null )
                     sb_szStatementText = new StringBuilder( 32 );
                  else
                     sb_szStatementText = new StringBuilder( szStatementText );
                                     m_ZDRVROPR.RemoveTagFromHTML( mSPLDef, sb_szStatementText, sb_szWriteBuffer, "p" );
                  szWriteBuffer = sb_szWriteBuffer.toString( );
                  szStatementText = sb_szStatementText.toString( );}
                  //:SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText )
                  SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText );
                  //:SET CURSOR NEXT mSPLDef.WkListRow
                  RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
                  //:lTempCnt = lTempCnt - 1
                  lTempCnt = lTempCnt - 1;
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:IF lCnt = lTotalRows
         if ( lCnt == lTotalRows )
         { 
            //:// We've come to the end of the rows, so change Attribute Name, restart count and reposition at beginning of mSPLDef.WkListRow.
            //:IF szAttributeName = "ColumnValue1"
            if ( ZeidonStringCompare( szAttributeName, 1, 0, "ColumnValue1", 1, 0, 33 ) == 0 )
            { 
               //:szAttributeName = "ColumnValue2"
                {StringBuilder sb_szAttributeName;
               if ( szAttributeName == null )
                  sb_szAttributeName = new StringBuilder( 32 );
               else
                  sb_szAttributeName = new StringBuilder( szAttributeName );
                              ZeidonStringCopy( sb_szAttributeName, 1, 0, "ColumnValue2", 1, 0, 33 );
               szAttributeName = sb_szAttributeName.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szAttributeName = "ColumnValue3"
                {StringBuilder sb_szAttributeName;
               if ( szAttributeName == null )
                  sb_szAttributeName = new StringBuilder( 32 );
               else
                  sb_szAttributeName = new StringBuilder( szAttributeName );
                              ZeidonStringCopy( sb_szAttributeName, 1, 0, "ColumnValue3", 1, 0, 33 );
               szAttributeName = sb_szAttributeName.toString( );}
            } 

            //:END

            //:lCnt = 0
            lCnt = 0;
            //:SET CURSOR FIRST mSPLDef.WkListRow
            RESULT = SetCursorFirstEntity( mSPLDef, "WkListRow", "" );
         } 

         RESULT = SetCursorNextEntity( mSPLDef, "SPLDI_ClaimsUsage", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLDI_ClaimsClassification", "" );
      //:END
   } 

   //:END

   //:// Finally generate the HTML for each WkListRow, one row for each entity.
   //:szSizeX = mSPLDef.SPLD_TemplatePanel.SizeX
   {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
   StringBuilder sb_szSizeX;
   if ( szSizeX == null )
      sb_szSizeX = new StringBuilder( 32 );
   else
      sb_szSizeX = new StringBuilder( szSizeX );
       GetVariableFromAttribute( sb_szSizeX, mi_lTempInteger_8, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "SizeX", "", 0 );
   lTempInteger_8 = mi_lTempInteger_8.intValue( );
   szSizeX = sb_szSizeX.toString( );}
   //:IF szSizeX != ""
   if ( ZeidonStringCompare( szSizeX, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = "   <table border=^1^ cellpadding=^10^ style=^width:" + szSizeX + "px;^>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   <table border=^1^ cellpadding=^10^ style=^width:", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSizeX, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "px;^>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szWriteBuffer = "   <table border=^1^ cellpadding=^10^ width=^100%^>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   <table border=^1^ cellpadding=^10^ width=^100%^>", 1, 0, 32001 );
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

   //:IF szRowHeight != ""
   if ( ZeidonStringCompare( szRowHeight, 1, 0, "", 1, 0, 257 ) != 0 )
   { 
      //:szStatementLeading = szRowHeight
       {StringBuilder sb_szStatementLeading;
      if ( szStatementLeading == null )
         sb_szStatementLeading = new StringBuilder( 32 );
      else
         sb_szStatementLeading = new StringBuilder( szStatementLeading );
            ZeidonStringCopy( sb_szStatementLeading, 1, 0, szRowHeight, 1, 0, 11 );
      szStatementLeading = sb_szStatementLeading.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szStatementLeading = ""
       {StringBuilder sb_szStatementLeading;
      if ( szStatementLeading == null )
         sb_szStatementLeading = new StringBuilder( 32 );
      else
         sb_szStatementLeading = new StringBuilder( szStatementLeading );
            ZeidonStringCopy( sb_szStatementLeading, 1, 0, "", 1, 0, 11 );
      szStatementLeading = sb_szStatementLeading.toString( );}
   } 

   //:END

   //:IF szStatementLeading != ""
   if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szRowHeight = "         <td width=^33%^ valign=^top^ height=^" + szStatementLeading + "px^ style=^text-align:left; font-size:"
       {StringBuilder sb_szRowHeight;
      if ( szRowHeight == null )
         sb_szRowHeight = new StringBuilder( 32 );
      else
         sb_szRowHeight = new StringBuilder( szRowHeight );
            ZeidonStringCopy( sb_szRowHeight, 1, 0, "         <td width=^33%^ valign=^top^ height=^", 1, 0, 257 );
      szRowHeight = sb_szRowHeight.toString( );}
       {StringBuilder sb_szRowHeight;
      if ( szRowHeight == null )
         sb_szRowHeight = new StringBuilder( 32 );
      else
         sb_szRowHeight = new StringBuilder( szRowHeight );
            ZeidonStringConcat( sb_szRowHeight, 1, 0, szStatementLeading, 1, 0, 257 );
      szRowHeight = sb_szRowHeight.toString( );}
       {StringBuilder sb_szRowHeight;
      if ( szRowHeight == null )
         sb_szRowHeight = new StringBuilder( 32 );
      else
         sb_szRowHeight = new StringBuilder( szRowHeight );
            ZeidonStringConcat( sb_szRowHeight, 1, 0, "px^ style=^text-align:left; font-size:", 1, 0, 257 );
      szRowHeight = sb_szRowHeight.toString( );}
      //:szRowHeight = szRowHeight + szStatementLeading + "px^>"
       {StringBuilder sb_szRowHeight;
      if ( szRowHeight == null )
         sb_szRowHeight = new StringBuilder( 32 );
      else
         sb_szRowHeight = new StringBuilder( szRowHeight );
            ZeidonStringConcat( sb_szRowHeight, 1, 0, szStatementLeading, 1, 0, 257 );
      szRowHeight = sb_szRowHeight.toString( );}
       {StringBuilder sb_szRowHeight;
      if ( szRowHeight == null )
         sb_szRowHeight = new StringBuilder( 32 );
      else
         sb_szRowHeight = new StringBuilder( szRowHeight );
            ZeidonStringConcat( sb_szRowHeight, 1, 0, "px^>", 1, 0, 257 );
      szRowHeight = sb_szRowHeight.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szRowHeight = "         <td width=^33%^ valign=^top^ style=^text-align:left;^>"
       {StringBuilder sb_szRowHeight;
      if ( szRowHeight == null )
         sb_szRowHeight = new StringBuilder( 32 );
      else
         sb_szRowHeight = new StringBuilder( szRowHeight );
            ZeidonStringCopy( sb_szRowHeight, 1, 0, "         <td width=^33%^ valign=^top^ style=^text-align:left;^>", 1, 0, 257 );
      szRowHeight = sb_szRowHeight.toString( );}
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

   //:szWriteBuffer = "      <tr>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "      <tr>", 1, 0, 32001 );
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

   //:// ColumnValue1
   //:szWriteBuffer = szRowHeight
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szRowHeight, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:FOR EACH mSPLDef.WkListRow
   RESULT = SetCursorFirstEntity( mSPLDef, "WkListRow", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szWriteBuffer = szWriteBuffer + mSPLDef.WkListRow.ColumnValue1 //  + "<br />"
      {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
      StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
             GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_9, 'S', 255, mSPLDef, "WkListRow", "ColumnValue1", "", 0 );
      lTempInteger_9 = mi_lTempInteger_9.intValue( );
      szTempString_1 = sb_szTempString_1.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTempString_1, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
   } 

   //:END

   //:szWriteBuffer = szWriteBuffer + "</td>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</td>", 1, 0, 32001 );
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

   //:// ColumnValue2
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

   //:szWriteBuffer = szRowHeight
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szRowHeight, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:FOR EACH mSPLDef.WkListRow
   RESULT = SetCursorFirstEntity( mSPLDef, "WkListRow", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szWriteBuffer = szWriteBuffer + mSPLDef.WkListRow.ColumnValue2 //  + "<br />"
      {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
      StringBuilder sb_szTempString_2;
      if ( szTempString_2 == null )
         sb_szTempString_2 = new StringBuilder( 32 );
      else
         sb_szTempString_2 = new StringBuilder( szTempString_2 );
             GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_10, 'S', 255, mSPLDef, "WkListRow", "ColumnValue2", "", 0 );
      lTempInteger_10 = mi_lTempInteger_10.intValue( );
      szTempString_2 = sb_szTempString_2.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTempString_2, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
   } 

   //:END

   //:szWriteBuffer = szWriteBuffer + "</td>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</td>", 1, 0, 32001 );
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

   //:// ColumnValue3
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

   //:szWriteBuffer = szRowHeight
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szRowHeight, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:FOR EACH mSPLDef.WkListRow
   RESULT = SetCursorFirstEntity( mSPLDef, "WkListRow", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szWriteBuffer = szWriteBuffer + mSPLDef.WkListRow.ColumnValue3 //  + "<br />"
      {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
      StringBuilder sb_szTempString_3;
      if ( szTempString_3 == null )
         sb_szTempString_3 = new StringBuilder( 32 );
      else
         sb_szTempString_3 = new StringBuilder( szTempString_3 );
             GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_11, 'S', 255, mSPLDef, "WkListRow", "ColumnValue3", "", 0 );
      lTempInteger_11 = mi_lTempInteger_11.intValue( );
      szTempString_3 = sb_szTempString_3.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTempString_3, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
   } 

   //:END

   //:szWriteBuffer = szWriteBuffer + "</td>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</td>", 1, 0, 32001 );
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

   //:szWriteBuffer = "      </tr>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "      </tr>", 1, 0, 32001 );
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

   //:szWriteBuffer = "   </table>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   </table>", 1, 0, 32001 );
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
//:GeneratePDF_ClmList3( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                       INTEGER lFile,
//:                       STRING ( 256 )   szCombinedTitle,
//:                       STRING ( 32000 ) szWriteBuffer,
//:                       STRING ( 32000 ) szStatementText )

//:   STRING ( 32 )     szAttributeName
public int 
omSPLDef_GeneratePDF_ClmList3( View     mSPLDef,
                               int      lFile,
                               String   szCombinedTitle,
                               String   szWriteBuffer,
                               String   szStatementText )
{
   String   szAttributeName = null;
   //:STRING ( 32 )     szLineHeight
   String   szLineHeight = null;
   //:STRING ( 256 )    szRowHeight
   String   szRowHeight = null;
   //:STRING ( 10 )     szStatementLeading
   String   szStatementLeading = null;
   //:STRING ( 10 )     szSizeX
   String   szSizeX = null;
   //:INTEGER           lCnt
   int      lCnt = 0;
   //:INTEGER           lTempCnt
   int      lTempCnt = 0;
   //:INTEGER           lTotalRows
   int      lTotalRows = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_9 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_10 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_11 = 0;

   //:// INTEGER           lTempTotalCnt

   //:szLineHeight = ""
    {StringBuilder sb_szLineHeight;
   if ( szLineHeight == null )
      sb_szLineHeight = new StringBuilder( 32 );
   else
      sb_szLineHeight = new StringBuilder( szLineHeight );
      ZeidonStringCopy( sb_szLineHeight, 1, 0, "", 1, 0, 33 );
   szLineHeight = sb_szLineHeight.toString( );}
   //:szStatementLeading = mSPLDef.SPLD_TemplateSection.dStatementLeading
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szStatementLeading;
   if ( szStatementLeading == null )
      sb_szStatementLeading = new StringBuilder( 32 );
   else
      sb_szStatementLeading = new StringBuilder( szStatementLeading );
       GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_0, 'S', 11, mSPLDef, "SPLD_TemplateSection", "dStatementLeading", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szStatementLeading = sb_szStatementLeading.toString( );}
   //:IF szStatementLeading != ""
   if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szLineHeight = "line-height:" + szStatementLeading +";"
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szStatementLeading = mSPLDef.SPLD_TemplatePanel.dStatementLeadingDefault
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szStatementLeading;
      if ( szStatementLeading == null )
         sb_szStatementLeading = new StringBuilder( 32 );
      else
         sb_szStatementLeading = new StringBuilder( szStatementLeading );
             GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "dStatementLeadingDefault", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szStatementLeading = sb_szStatementLeading.toString( );}
      //:IF szStatementLeading != ""
      if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szLineHeight = "line-height:" + szStatementLeading +";"
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
      } 

      //:END
   } 

   //:END

   //:szRowHeight = szStatementLeading
    {StringBuilder sb_szRowHeight;
   if ( szRowHeight == null )
      sb_szRowHeight = new StringBuilder( 32 );
   else
      sb_szRowHeight = new StringBuilder( szRowHeight );
      ZeidonStringCopy( sb_szRowHeight, 1, 0, szStatementLeading, 1, 0, 257 );
   szRowHeight = sb_szRowHeight.toString( );}

   //:// LIST3 - 3 Column List of Claim Usage Statements
   //:// The dependent Claim Usage statements are to be listed in 3 columns, after any regular Statements
   //:// and organized by Claims Classifications.
   //:// IssueError( mSPLDef, 0, 0, "At Beginning" )

   //:// First, process each Statement within the Section, simply formatting each as a paragraph.
   //:lCnt = 0
   lCnt = 0;
   //:FOR EACH mSPLDef.SPLD_MarketingStatement
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// DisplayEntityInstance( mSPLDef, "SPLD_MarketingStatement" )
      //:lCnt = lCnt + 1
      lCnt = lCnt + 1;
      //:szWriteBuffer = mSPLDef.SPLD_MarketingStatement.Text
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
             GetVariableFromAttribute( sb_szWriteBuffer, mi_lTempInteger_2, 'S', 32001, mSPLDef, "SPLD_MarketingStatement", "Text", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:IF lCnt = 1
      if ( lCnt == 1 )
      { 
         //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", "ClaimsTitle", zQUOTES, 1 )
         m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", "ClaimsTitle", zQUOTES, 1 );
         //:ELSE
      } 
      else
      { 
         //:IF lCnt = 2
         if ( lCnt == 2 )
         { 
            //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", "ClaimsSubTitle", zQUOTES, 1 )
            m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", "ClaimsSubTitle", zQUOTES, 1 );
            //:ELSE
         } 
         else
         { 
            //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
            m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
         } 

         //:END
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
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingStatement", "" );
   } 


   //:   //szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
   //:END

   //:// Build the work object containing each Claim under the appropriate Classification.
   //:// Type: C - Claim;  S - Surface;  T - Application Type;  U - Area of Use.
   //:// ClaimsClassification: Bacteria; Protozoa; Viruses; Fungi.
   //:FOR EACH mSPLDef.SPLD_MarketingOrdering WHERE mSPLDef.SPLD_MarketingUsage.UsageType = "C" // just looking for Type=Claim
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingOrdering", "" );
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
               //:TraceLineS( "GeneratePDF_ClmList3 Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ======== CAN'T HAPPEN ===========================" )
               TraceLineS( "GeneratePDF_ClmList3 Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ======== CAN'T HAPPEN ===========================" );
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
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                         GetIntegerFromAttribute( mi_lTempInteger_3, mSPLDef, "SPLD_MarketingUsage", "ID" );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLDI_ClaimsUsage", "ID", lTempInteger_3, "" );
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
                  //:TraceLineS( "GeneratePDF_ClmList3 Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ===========CAN'T HAPPEN EITHER ========================" )
                  TraceLineS( "GeneratePDF_ClmList3 Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ===========CAN'T HAPPEN EITHER ========================" );
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
               //:TraceLineS( "GeneratePDF_ClmList3 Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ====== ALREADY INCLUDED ... COULD HAPPEN =============================" )
               TraceLineS( "GeneratePDF_ClmList3 Include SPLDI_ClaimsUsage FROM SPLD_MarketingUsage", " ====== ALREADY INCLUDED ... COULD HAPPEN =============================" );
               //:// DisplayEntityInstance( mSPLDef, "SPLDI_ClaimsUsage" )
               //:DisplayEntityInstance( mSPLDef, "SPLD_MarketingUsage" )
               DisplayEntityInstance( mSPLDef, "SPLD_MarketingUsage" );
            } 

            //:END
         } 

      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingOrdering", "" );
      //:END
   } 

   //:END

   //:// Determine how many total rows there will be.
   //:// We get an entry for each Classification (virus/bacteria/...) and an entry for each Claim.
   //:lTotalRows = 0
   lTotalRows = 0;
   //:FOR EACH mSPLDef.SPLDI_ClaimsClassification
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_ClaimsClassification", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:lTotalRows = lTotalRows + 1
      lTotalRows = lTotalRows + 1;
      //:// For each Classification after the first, add 1 for a blank entry (prior to the classification title).
      //:IF lTotalRows > 1
      if ( lTotalRows > 1 )
      { 
         //:lTotalRows = lTotalRows + 1
         lTotalRows = lTotalRows + 1;
      } 

      //:END

      //:FOR EACH mSPLDef.SPLDI_ClaimsUsage
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_ClaimsUsage", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:szStatementText = mSPLDef.SPLDI_ClaimsUsage.Name
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
         StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                   GetVariableFromAttribute( sb_szStatementText, mi_lTempInteger_4, 'S', 32001, mSPLDef, "SPLDI_ClaimsUsage", "Name", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );
         szStatementText = sb_szStatementText.toString( );}

         //:// Count the number of <p> attributes at the "top level" in this usage.
         //:lTempCnt = AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "", "", "", 0 )
         lTempCnt = m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "", "", "", 0 );
         //:IF lTempCnt = 0  // there were no <p> attributes in the original, but added 1
         if ( lTempCnt == 0 )
         { 
            //:lTempCnt = 1
            lTempCnt = 1;
         } 

         //:END

         //:lTotalRows = lTotalRows + lTempCnt
         lTotalRows = lTotalRows + lTempCnt;
         RESULT = SetCursorNextEntity( mSPLDef, "SPLDI_ClaimsUsage", "" );
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLDI_ClaimsClassification", "" );
      //:END
   } 

   //:END

   //:IF lTotalRows <= 4
   if ( lTotalRows <= 4 )
   { 
      //:MessageSend( mSPLDef, "", "Generate Usage List",
      //:             "A list must have have least 4 Usage Statements.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mSPLDef, "", "Generate Usage List", "A list must have have least 4 Usage Statements.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:lTotalRows = (lTotalRows + 2) / 3  // round up to the next multiple of 3
   lTotalRows = ( lTotalRows + 2 ) / 3;

   //:// Delete any existing work entities.
   //:FOR EACH mSPLDef.WkListRow
   RESULT = SetCursorFirstEntity( mSPLDef, "WkListRow", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mSPLDef.WkListRow NONE
      RESULT = DeleteEntity( mSPLDef, "WkListRow", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
   } 

   //:END

   //:// Loop through the rows setting the values.
   //:// As we loop, the first "lTotalRows" entries will go into ColumnValue1, the second into ColumnValue2
   //:// and the third into ColumnValue3.

   //:szAttributeName = "ColumnValue1"
    {StringBuilder sb_szAttributeName;
   if ( szAttributeName == null )
      sb_szAttributeName = new StringBuilder( 32 );
   else
      sb_szAttributeName = new StringBuilder( szAttributeName );
      ZeidonStringCopy( sb_szAttributeName, 1, 0, "ColumnValue1", 1, 0, 33 );
   szAttributeName = sb_szAttributeName.toString( );}
   //:lCnt = 0
   lCnt = 0;
   //:FOR EACH mSPLDef.SPLDI_ClaimsClassification  // Bacteria; Protozoa; Viruses; Fungi
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_ClaimsClassification", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:lCnt = lCnt + 1
      lCnt = lCnt + 1;
      //:IF szAttributeName = "ColumnValue1"  // processing first column ... set Classification in new entity.
      if ( ZeidonStringCompare( szAttributeName, 1, 0, "ColumnValue1", 1, 0, 33 ) == 0 )
      { 
         //:CREATE ENTITY mSPLDef.WkListRow
         RESULT = CreateEntity( mSPLDef, "WkListRow", zPOS_AFTER );
         //:szStatementText = mSPLDef.SPLDI_ClaimsClassification.ClaimsClassification
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
         StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                   GetVariableFromAttribute( sb_szStatementText, mi_lTempInteger_5, 'S', 32001, mSPLDef, "SPLDI_ClaimsClassification", "ClaimsClassification", "", 0 );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );
         szStatementText = sb_szStatementText.toString( );}
         //:AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "class", "ClaimsClassification", zQUOTES, 1 )
         m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "class", "ClaimsClassification", zQUOTES, 1 );
         //:SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText )
         SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText );
         //:ELSE
      } 
      else
      { 
         //:// We're processing second or third column, set Classification in existing entity after a blank entry.
         //:SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, "<p>&nbsp</p>" )  // add blank before the new classification
         SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, "<p>&nbsp</p>" );
         //:SET CURSOR NEXT mSPLDef.WkListRow
         RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
         //:szStatementText = mSPLDef.SPLDI_ClaimsClassification.ClaimsClassification
         {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
         StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                   GetVariableFromAttribute( sb_szStatementText, mi_lTempInteger_6, 'S', 32001, mSPLDef, "SPLDI_ClaimsClassification", "ClaimsClassification", "", 0 );
         lTempInteger_6 = mi_lTempInteger_6.intValue( );
         szStatementText = sb_szStatementText.toString( );}
         //:AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "class", "ClaimsClassification", zQUOTES, 1 )
         m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "class", "ClaimsClassification", zQUOTES, 1 );
         //:SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText )
         SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText );
         //:SET CURSOR NEXT mSPLDef.WkListRow
         RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
      } 

      //:END

      //:IF lCnt = lTotalRows
      if ( lCnt == lTotalRows )
      { 
         //:// We've come to the end of the rows, so change Attribute Name, restart count and reposition at beginning of mSPLDef.WkListRow.
         //:IF szAttributeName = "ColumnValue1"
         if ( ZeidonStringCompare( szAttributeName, 1, 0, "ColumnValue1", 1, 0, 33 ) == 0 )
         { 
            //:szAttributeName = "ColumnValue2"
             {StringBuilder sb_szAttributeName;
            if ( szAttributeName == null )
               sb_szAttributeName = new StringBuilder( 32 );
            else
               sb_szAttributeName = new StringBuilder( szAttributeName );
                        ZeidonStringCopy( sb_szAttributeName, 1, 0, "ColumnValue2", 1, 0, 33 );
            szAttributeName = sb_szAttributeName.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szAttributeName = "ColumnValue3"
             {StringBuilder sb_szAttributeName;
            if ( szAttributeName == null )
               sb_szAttributeName = new StringBuilder( 32 );
            else
               sb_szAttributeName = new StringBuilder( szAttributeName );
                        ZeidonStringCopy( sb_szAttributeName, 1, 0, "ColumnValue3", 1, 0, 33 );
            szAttributeName = sb_szAttributeName.toString( );}
         } 

         //:END

         //:lCnt = 0
         lCnt = 0;
         //:SET CURSOR FIRST mSPLDef.WkListRow
         RESULT = SetCursorFirstEntity( mSPLDef, "WkListRow", "" );
      } 

      //:END

      //:FOR EACH mSPLDef.SPLDI_ClaimsUsage
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_ClaimsUsage", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 

         //:szStatementText = mSPLDef.SPLDI_ClaimsUsage.Name
         {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
         StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                   GetVariableFromAttribute( sb_szStatementText, mi_lTempInteger_7, 'S', 32001, mSPLDef, "SPLDI_ClaimsUsage", "Name", "", 0 );
         lTempInteger_7 = mi_lTempInteger_7.intValue( );
         szStatementText = sb_szStatementText.toString( );}

         //:// Count the number of <p> attributes at the "top level" in this usage.
         //:lTempCnt = AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "", "", "", 0 )
         lTempCnt = m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szStatementText, 32000, "p", "", "", "", 0 );
         //:IF lTempCnt = 0  // there were no <p> attributes in the original, but added 1
         if ( lTempCnt == 0 )
         { 
            //:lTempCnt = 1
            lTempCnt = 1;
         } 

         //:END

         //:lCnt = lCnt + lTempCnt
         lCnt = lCnt + lTempCnt;
         //:IF szAttributeName = "ColumnValue1"
         if ( ZeidonStringCompare( szAttributeName, 1, 0, "ColumnValue1", 1, 0, 33 ) == 0 )
         { 
            //:// We're processing first column, set Claim in new entity.
            //:IF lTempCnt = 1
            if ( lTempCnt == 1 )
            { 
               //:CREATE ENTITY mSPLDef.WkListRow
               RESULT = CreateEntity( mSPLDef, "WkListRow", zPOS_AFTER );
               //:SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText )
               SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText );
               //:ELSE
            } 
            else
            { 
               //:szWriteBuffer = szStatementText
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:LOOP WHILE lTempCnt > 0
               while ( lTempCnt > 0 )
               { 
                  //:RemoveTagFromHTML( mSPLDef, szStatementText, szWriteBuffer, "p" )
                  {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  StringBuilder sb_szStatementText;
                  if ( szStatementText == null )
                     sb_szStatementText = new StringBuilder( 32 );
                  else
                     sb_szStatementText = new StringBuilder( szStatementText );
                                     m_ZDRVROPR.RemoveTagFromHTML( mSPLDef, sb_szStatementText, sb_szWriteBuffer, "p" );
                  szWriteBuffer = sb_szWriteBuffer.toString( );
                  szStatementText = sb_szStatementText.toString( );}
                  //:CREATE ENTITY mSPLDef.WkListRow
                  RESULT = CreateEntity( mSPLDef, "WkListRow", zPOS_AFTER );
                  //:SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText )
                  SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText );
                  //:lTempCnt = lTempCnt - 1
                  lTempCnt = lTempCnt - 1;
               } 

               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:// We're processing second or third column, set Classification in existing entity.
            //:IF lTempCnt = 1
            if ( lTempCnt == 1 )
            { 
               //:SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText )
               SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText );
               //:SET CURSOR NEXT mSPLDef.WkListRow
               RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
               //:ELSE
            } 
            else
            { 
               //:szWriteBuffer = szStatementText
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:LOOP WHILE lTempCnt > 0
               while ( lTempCnt > 0 )
               { 
                  //:RemoveTagFromHTML( mSPLDef, szStatementText, szWriteBuffer, "p" )
                  {StringBuilder sb_szWriteBuffer;
                  if ( szWriteBuffer == null )
                     sb_szWriteBuffer = new StringBuilder( 32 );
                  else
                     sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  StringBuilder sb_szStatementText;
                  if ( szStatementText == null )
                     sb_szStatementText = new StringBuilder( 32 );
                  else
                     sb_szStatementText = new StringBuilder( szStatementText );
                                     m_ZDRVROPR.RemoveTagFromHTML( mSPLDef, sb_szStatementText, sb_szWriteBuffer, "p" );
                  szWriteBuffer = sb_szWriteBuffer.toString( );
                  szStatementText = sb_szStatementText.toString( );}
                  //:SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText )
                  SetAttributeFromString( mSPLDef, "WkListRow", szAttributeName, szStatementText );
                  //:SET CURSOR NEXT mSPLDef.WkListRow
                  RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
                  //:lTempCnt = lTempCnt - 1
                  lTempCnt = lTempCnt - 1;
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:IF lCnt = lTotalRows
         if ( lCnt == lTotalRows )
         { 
            //:// We've come to the end of the rows, so change Attribute Name, restart count and reposition at beginning of mSPLDef.WkListRow.
            //:IF szAttributeName = "ColumnValue1"
            if ( ZeidonStringCompare( szAttributeName, 1, 0, "ColumnValue1", 1, 0, 33 ) == 0 )
            { 
               //:szAttributeName = "ColumnValue2"
                {StringBuilder sb_szAttributeName;
               if ( szAttributeName == null )
                  sb_szAttributeName = new StringBuilder( 32 );
               else
                  sb_szAttributeName = new StringBuilder( szAttributeName );
                              ZeidonStringCopy( sb_szAttributeName, 1, 0, "ColumnValue2", 1, 0, 33 );
               szAttributeName = sb_szAttributeName.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szAttributeName = "ColumnValue3"
                {StringBuilder sb_szAttributeName;
               if ( szAttributeName == null )
                  sb_szAttributeName = new StringBuilder( 32 );
               else
                  sb_szAttributeName = new StringBuilder( szAttributeName );
                              ZeidonStringCopy( sb_szAttributeName, 1, 0, "ColumnValue3", 1, 0, 33 );
               szAttributeName = sb_szAttributeName.toString( );}
            } 

            //:END

            //:lCnt = 0
            lCnt = 0;
            //:SET CURSOR FIRST mSPLDef.WkListRow
            RESULT = SetCursorFirstEntity( mSPLDef, "WkListRow", "" );
         } 

         RESULT = SetCursorNextEntity( mSPLDef, "SPLDI_ClaimsUsage", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLDI_ClaimsClassification", "" );
      //:END
   } 

   //:END

   //:// Finally generate the PDF for each WkListRow, one row for each entity.
   //:szSizeX = mSPLDef.SPLD_TemplatePanel.SizeX
   {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
   StringBuilder sb_szSizeX;
   if ( szSizeX == null )
      sb_szSizeX = new StringBuilder( 32 );
   else
      sb_szSizeX = new StringBuilder( szSizeX );
       GetVariableFromAttribute( sb_szSizeX, mi_lTempInteger_8, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "SizeX", "", 0 );
   lTempInteger_8 = mi_lTempInteger_8.intValue( );
   szSizeX = sb_szSizeX.toString( );}
   //:IF szSizeX != ""
   if ( ZeidonStringCompare( szSizeX, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szWriteBuffer = "   <table border=^1^ cellpadding=^10^ style=^width:" + szSizeX + "px;^>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   <table border=^1^ cellpadding=^10^ style=^width:", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSizeX, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "px;^>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szWriteBuffer = "   <table border=^1^ cellpadding=^10^ width=^100%^>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   <table border=^1^ cellpadding=^10^ width=^100%^>", 1, 0, 32001 );
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

   //:IF szRowHeight != ""
   if ( ZeidonStringCompare( szRowHeight, 1, 0, "", 1, 0, 257 ) != 0 )
   { 
      //:szStatementLeading = szRowHeight
       {StringBuilder sb_szStatementLeading;
      if ( szStatementLeading == null )
         sb_szStatementLeading = new StringBuilder( 32 );
      else
         sb_szStatementLeading = new StringBuilder( szStatementLeading );
            ZeidonStringCopy( sb_szStatementLeading, 1, 0, szRowHeight, 1, 0, 11 );
      szStatementLeading = sb_szStatementLeading.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szStatementLeading = ""
       {StringBuilder sb_szStatementLeading;
      if ( szStatementLeading == null )
         sb_szStatementLeading = new StringBuilder( 32 );
      else
         sb_szStatementLeading = new StringBuilder( szStatementLeading );
            ZeidonStringCopy( sb_szStatementLeading, 1, 0, "", 1, 0, 11 );
      szStatementLeading = sb_szStatementLeading.toString( );}
   } 

   //:END

   //:IF szStatementLeading != ""
   if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szRowHeight = "         <td width=^33%^ valign=^top^ height=^" + szStatementLeading + "px^ style=^text-align:left; font-size:"
       {StringBuilder sb_szRowHeight;
      if ( szRowHeight == null )
         sb_szRowHeight = new StringBuilder( 32 );
      else
         sb_szRowHeight = new StringBuilder( szRowHeight );
            ZeidonStringCopy( sb_szRowHeight, 1, 0, "         <td width=^33%^ valign=^top^ height=^", 1, 0, 257 );
      szRowHeight = sb_szRowHeight.toString( );}
       {StringBuilder sb_szRowHeight;
      if ( szRowHeight == null )
         sb_szRowHeight = new StringBuilder( 32 );
      else
         sb_szRowHeight = new StringBuilder( szRowHeight );
            ZeidonStringConcat( sb_szRowHeight, 1, 0, szStatementLeading, 1, 0, 257 );
      szRowHeight = sb_szRowHeight.toString( );}
       {StringBuilder sb_szRowHeight;
      if ( szRowHeight == null )
         sb_szRowHeight = new StringBuilder( 32 );
      else
         sb_szRowHeight = new StringBuilder( szRowHeight );
            ZeidonStringConcat( sb_szRowHeight, 1, 0, "px^ style=^text-align:left; font-size:", 1, 0, 257 );
      szRowHeight = sb_szRowHeight.toString( );}
      //:szRowHeight = szRowHeight + szStatementLeading + "px^>"
       {StringBuilder sb_szRowHeight;
      if ( szRowHeight == null )
         sb_szRowHeight = new StringBuilder( 32 );
      else
         sb_szRowHeight = new StringBuilder( szRowHeight );
            ZeidonStringConcat( sb_szRowHeight, 1, 0, szStatementLeading, 1, 0, 257 );
      szRowHeight = sb_szRowHeight.toString( );}
       {StringBuilder sb_szRowHeight;
      if ( szRowHeight == null )
         sb_szRowHeight = new StringBuilder( 32 );
      else
         sb_szRowHeight = new StringBuilder( szRowHeight );
            ZeidonStringConcat( sb_szRowHeight, 1, 0, "px^>", 1, 0, 257 );
      szRowHeight = sb_szRowHeight.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szRowHeight = "         <td width=^33%^ valign=^top^ style=^text-align:left;^>"
       {StringBuilder sb_szRowHeight;
      if ( szRowHeight == null )
         sb_szRowHeight = new StringBuilder( 32 );
      else
         sb_szRowHeight = new StringBuilder( szRowHeight );
            ZeidonStringCopy( sb_szRowHeight, 1, 0, "         <td width=^33%^ valign=^top^ style=^text-align:left;^>", 1, 0, 257 );
      szRowHeight = sb_szRowHeight.toString( );}
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

   //:szWriteBuffer = "      <tr>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "      <tr>", 1, 0, 32001 );
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

   //:// ColumnValue1
   //:szWriteBuffer = szRowHeight
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szRowHeight, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:FOR EACH mSPLDef.WkListRow
   RESULT = SetCursorFirstEntity( mSPLDef, "WkListRow", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szWriteBuffer = szWriteBuffer + mSPLDef.WkListRow.ColumnValue1 //  + "<br />"
      {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
      StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
             GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_9, 'S', 255, mSPLDef, "WkListRow", "ColumnValue1", "", 0 );
      lTempInteger_9 = mi_lTempInteger_9.intValue( );
      szTempString_1 = sb_szTempString_1.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTempString_1, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
   } 

   //:END

   //:szWriteBuffer = szWriteBuffer + "</td>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</td>", 1, 0, 32001 );
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

   //:// ColumnValue2
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

   //:szWriteBuffer = szRowHeight
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szRowHeight, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:FOR EACH mSPLDef.WkListRow
   RESULT = SetCursorFirstEntity( mSPLDef, "WkListRow", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szWriteBuffer = szWriteBuffer + mSPLDef.WkListRow.ColumnValue2 //  + "<br />"
      {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
      StringBuilder sb_szTempString_2;
      if ( szTempString_2 == null )
         sb_szTempString_2 = new StringBuilder( 32 );
      else
         sb_szTempString_2 = new StringBuilder( szTempString_2 );
             GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_10, 'S', 255, mSPLDef, "WkListRow", "ColumnValue2", "", 0 );
      lTempInteger_10 = mi_lTempInteger_10.intValue( );
      szTempString_2 = sb_szTempString_2.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTempString_2, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
   } 

   //:END

   //:szWriteBuffer = szWriteBuffer + "</td>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</td>", 1, 0, 32001 );
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

   //:// ColumnValue3
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

   //:szWriteBuffer = szRowHeight
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szRowHeight, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
   //:FOR EACH mSPLDef.WkListRow
   RESULT = SetCursorFirstEntity( mSPLDef, "WkListRow", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szWriteBuffer = szWriteBuffer + mSPLDef.WkListRow.ColumnValue3 //  + "<br />"
      {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
      StringBuilder sb_szTempString_3;
      if ( szTempString_3 == null )
         sb_szTempString_3 = new StringBuilder( 32 );
      else
         sb_szTempString_3 = new StringBuilder( szTempString_3 );
             GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_11, 'S', 255, mSPLDef, "WkListRow", "ColumnValue3", "", 0 );
      lTempInteger_11 = mi_lTempInteger_11.intValue( );
      szTempString_3 = sb_szTempString_3.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTempString_3, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      RESULT = SetCursorNextEntity( mSPLDef, "WkListRow", "" );
   } 

   //:END

   //:szWriteBuffer = szWriteBuffer + "</td>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</td>", 1, 0, 32001 );
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

   //:szWriteBuffer = "      </tr>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "      </tr>", 1, 0, 32001 );
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

   //:szWriteBuffer = "   </table>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "   </table>", 1, 0, 32001 );
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
//:GenerateHTML_Ingred( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                     INTEGER lFile,
//:                     STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 512 )   szIngredientsText
public int 
omSPLDef_GenerateHTML_Ingred( View     mSPLDef,
                              int      lFile,
                              String   szWriteBuffer )
{
   String   szIngredientsText = null;
   //:STRING ( 256 )   szPeriodFiller
   String   szPeriodFiller = null;
   //:STRING ( 16 )    szPercent
   String   szPercent = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:// Generate HTML for a "Active Ingredients" Section.

   //:SET CURSOR FIRST mSPLDef.SPLD_IngredientsSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_IngredientsSection", "" );
   //:szPeriodFiller = " ..................................................................................................." +
   //:                 "...................................................................................................."
    {StringBuilder sb_szPeriodFiller;
   if ( szPeriodFiller == null )
      sb_szPeriodFiller = new StringBuilder( 32 );
   else
      sb_szPeriodFiller = new StringBuilder( szPeriodFiller );
      ZeidonStringCopy( sb_szPeriodFiller, 1, 0, " ...................................................................................................", 1, 0, 257 );
   szPeriodFiller = sb_szPeriodFiller.toString( );}
    {StringBuilder sb_szPeriodFiller;
   if ( szPeriodFiller == null )
      sb_szPeriodFiller = new StringBuilder( 32 );
   else
      sb_szPeriodFiller = new StringBuilder( szPeriodFiller );
      ZeidonStringConcat( sb_szPeriodFiller, 1, 0, "....................................................................................................", 1, 0, 257 );
   szPeriodFiller = sb_szPeriodFiller.toString( );}

   //:szWriteBuffer = "            <table class=^tableingredients^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <table class=^tableingredients^>", 1, 0, 32001 );
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
   //:szIngredientsText = mSPLDef.SPLD_IngredientsSection.ActiveTitle
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szIngredientsText;
   if ( szIngredientsText == null )
      sb_szIngredientsText = new StringBuilder( 32 );
   else
      sb_szIngredientsText = new StringBuilder( szIngredientsText );
       GetVariableFromAttribute( sb_szIngredientsText, mi_lTempInteger_0, 'S', 513, mSPLDef, "SPLD_IngredientsSection", "ActiveTitle", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szIngredientsText = sb_szIngredientsText.toString( );}
   //:szWriteBuffer = "               <tr><td style=^text-align:left;^ nowrap><b>" + szIngredientsText + "</b></td></tr>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <tr><td style=^text-align:left;^ nowrap><b>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szIngredientsText, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</b></td></tr>", 1, 0, 32001 );
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

   //:FOR EACH mSPLDef.SPLD_IngredientsStatement
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_IngredientsStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Column Ingredient plus periods
      //:szWriteBuffer = mSPLDef.SPLD_IngredientsStatement.dIngredientName
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
             GetVariableFromAttribute( sb_szWriteBuffer, mi_lTempInteger_1, 'S', 32001, mSPLDef, "SPLD_IngredientsStatement", "dIngredientName", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:zSearchAndReplace( szWriteBuffer, 512, "<p>", "" )
      {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
             zSearchAndReplace( sb_szWriteBuffer, 512, "<p>", "" );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:zSearchAndReplace( szWriteBuffer, 512, "</p>", "" )
      {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
             zSearchAndReplace( sb_szWriteBuffer, 512, "</p>", "" );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:szIngredientsText = "<div>" + szWriteBuffer + szPeriodFiller + "</div>"
       {StringBuilder sb_szIngredientsText;
      if ( szIngredientsText == null )
         sb_szIngredientsText = new StringBuilder( 32 );
      else
         sb_szIngredientsText = new StringBuilder( szIngredientsText );
            ZeidonStringCopy( sb_szIngredientsText, 1, 0, "<div>", 1, 0, 513 );
      szIngredientsText = sb_szIngredientsText.toString( );}
       {StringBuilder sb_szIngredientsText;
      if ( szIngredientsText == null )
         sb_szIngredientsText = new StringBuilder( 32 );
      else
         sb_szIngredientsText = new StringBuilder( szIngredientsText );
            ZeidonStringConcat( sb_szIngredientsText, 1, 0, szWriteBuffer, 1, 0, 513 );
      szIngredientsText = sb_szIngredientsText.toString( );}
       {StringBuilder sb_szIngredientsText;
      if ( szIngredientsText == null )
         sb_szIngredientsText = new StringBuilder( 32 );
      else
         sb_szIngredientsText = new StringBuilder( szIngredientsText );
            ZeidonStringConcat( sb_szIngredientsText, 1, 0, szPeriodFiller, 1, 0, 513 );
      szIngredientsText = sb_szIngredientsText.toString( );}
       {StringBuilder sb_szIngredientsText;
      if ( szIngredientsText == null )
         sb_szIngredientsText = new StringBuilder( 32 );
      else
         sb_szIngredientsText = new StringBuilder( szIngredientsText );
            ZeidonStringConcat( sb_szIngredientsText, 1, 0, "</div>", 1, 0, 513 );
      szIngredientsText = sb_szIngredientsText.toString( );}
      //:szWriteBuffer = "               <tr><td class=^tableingredientscol1^ style=^text-align:left;^ nowrap>" + szIngredientsText + "</td>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <tr><td class=^tableingredientscol1^ style=^text-align:left;^ nowrap>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szIngredientsText, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</td>", 1, 0, 32001 );
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

      //:// Column Percent, right justified
      //:GetStringFromAttributeByContext( szPercent, mSPLDef, "SPLD_IngredientsStatement", "Percent", "", 10 )
      {StringBuilder sb_szPercent;
      if ( szPercent == null )
         sb_szPercent = new StringBuilder( 32 );
      else
         sb_szPercent = new StringBuilder( szPercent );
             GetStringFromAttributeByContext( sb_szPercent, mSPLDef, "SPLD_IngredientsStatement", "Percent", "", 10 );
      szPercent = sb_szPercent.toString( );}
      //:szWriteBuffer = "               <td style=^text-align:right;^>" + szPercent + "%</td></tr>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <td style=^text-align:right;^>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szPercent, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "%</td></tr>", 1, 0, 32001 );
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
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_IngredientsStatement", "" );
   } 

   //:END

   //:GetStringFromAttributeByContext( szPercent, mSPLDef, "SPLD_IngredientsSection", "GeneralInactivePercent", "", 10 )
   {StringBuilder sb_szPercent;
   if ( szPercent == null )
      sb_szPercent = new StringBuilder( 32 );
   else
      sb_szPercent = new StringBuilder( szPercent );
       GetStringFromAttributeByContext( sb_szPercent, mSPLDef, "SPLD_IngredientsSection", "GeneralInactivePercent", "", 10 );
   szPercent = sb_szPercent.toString( );}
   //:IF szIngredientsText != "0.000"
   if ( ZeidonStringCompare( szIngredientsText, 1, 0, "0.000", 1, 0, 513 ) != 0 )
   { 
      //:szIngredientsText = mSPLDef.SPLD_IngredientsSection.InertTitle
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szIngredientsText;
      if ( szIngredientsText == null )
         sb_szIngredientsText = new StringBuilder( 32 );
      else
         sb_szIngredientsText = new StringBuilder( szIngredientsText );
             GetVariableFromAttribute( sb_szIngredientsText, mi_lTempInteger_2, 'S', 513, mSPLDef, "SPLD_IngredientsSection", "InertTitle", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szIngredientsText = sb_szIngredientsText.toString( );}
      //:szWriteBuffer = "               <tr><td class=^tableingredientscol1^ style=^text-align:left;^ nowrap><div><b>" + szIngredientsText + szPeriodFiller + "</b></div></td>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <tr><td class=^tableingredientscol1^ style=^text-align:left;^ nowrap><div><b>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szIngredientsText, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szPeriodFiller, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</b></div></td>", 1, 0, 32001 );
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
      //:szWriteBuffer = "               <td style=^text-align:right;^>" + szPercent + "%</td></tr>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <td style=^text-align:right;^>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szPercent, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "%</td></tr>", 1, 0, 32001 );
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

   //:szWriteBuffer = "            </table>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            </table>", 1, 0, 32001 );
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
//:GeneratePDF_Ingred( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                     INTEGER lFile,
//:                     STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 512 )   szIngredientsText
public int 
omSPLDef_GeneratePDF_Ingred( View     mSPLDef,
                             int      lFile,
                             String   szWriteBuffer )
{
   String   szIngredientsText = null;
   //:STRING ( 256 )   szPeriodFiller
   String   szPeriodFiller = null;
   //:STRING ( 16 )    szPercent
   String   szPercent = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:// Generate PDF for a "Active Ingredients" Section.

   //:SET CURSOR FIRST mSPLDef.SPLD_IngredientsSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_IngredientsSection", "" );
   //:szPeriodFiller = " ..................................................................................................." +
   //:                 "...................................................................................................."
    {StringBuilder sb_szPeriodFiller;
   if ( szPeriodFiller == null )
      sb_szPeriodFiller = new StringBuilder( 32 );
   else
      sb_szPeriodFiller = new StringBuilder( szPeriodFiller );
      ZeidonStringCopy( sb_szPeriodFiller, 1, 0, " ...................................................................................................", 1, 0, 257 );
   szPeriodFiller = sb_szPeriodFiller.toString( );}
    {StringBuilder sb_szPeriodFiller;
   if ( szPeriodFiller == null )
      sb_szPeriodFiller = new StringBuilder( 32 );
   else
      sb_szPeriodFiller = new StringBuilder( szPeriodFiller );
      ZeidonStringConcat( sb_szPeriodFiller, 1, 0, "....................................................................................................", 1, 0, 257 );
   szPeriodFiller = sb_szPeriodFiller.toString( );}

   //:szWriteBuffer = "            <table class=^tableingredients^>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <table class=^tableingredients^>", 1, 0, 32001 );
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
   //:szIngredientsText = mSPLDef.SPLD_IngredientsSection.ActiveTitle
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szIngredientsText;
   if ( szIngredientsText == null )
      sb_szIngredientsText = new StringBuilder( 32 );
   else
      sb_szIngredientsText = new StringBuilder( szIngredientsText );
       GetVariableFromAttribute( sb_szIngredientsText, mi_lTempInteger_0, 'S', 513, mSPLDef, "SPLD_IngredientsSection", "ActiveTitle", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szIngredientsText = sb_szIngredientsText.toString( );}
   //:szWriteBuffer = "               <tr><td style=^text-align:left;^ nowrap><b>" + szIngredientsText + "</b></td></tr>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <tr><td style=^text-align:left;^ nowrap><b>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szIngredientsText, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</b></td></tr>", 1, 0, 32001 );
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

   //:FOR EACH mSPLDef.SPLD_IngredientsStatement
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_IngredientsStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Column Ingredient plus periods
      //:szWriteBuffer = mSPLDef.SPLD_IngredientsStatement.dIngredientName
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
             GetVariableFromAttribute( sb_szWriteBuffer, mi_lTempInteger_1, 'S', 32001, mSPLDef, "SPLD_IngredientsStatement", "dIngredientName", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:zSearchAndReplace( szWriteBuffer, 512, "<p>", "" )
      {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
             zSearchAndReplace( sb_szWriteBuffer, 512, "<p>", "" );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:zSearchAndReplace( szWriteBuffer, 512, "</p>", "" )
      {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
             zSearchAndReplace( sb_szWriteBuffer, 512, "</p>", "" );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
      //:szIngredientsText = "<div>" + szWriteBuffer + szPeriodFiller + "</div>"
       {StringBuilder sb_szIngredientsText;
      if ( szIngredientsText == null )
         sb_szIngredientsText = new StringBuilder( 32 );
      else
         sb_szIngredientsText = new StringBuilder( szIngredientsText );
            ZeidonStringCopy( sb_szIngredientsText, 1, 0, "<div>", 1, 0, 513 );
      szIngredientsText = sb_szIngredientsText.toString( );}
       {StringBuilder sb_szIngredientsText;
      if ( szIngredientsText == null )
         sb_szIngredientsText = new StringBuilder( 32 );
      else
         sb_szIngredientsText = new StringBuilder( szIngredientsText );
            ZeidonStringConcat( sb_szIngredientsText, 1, 0, szWriteBuffer, 1, 0, 513 );
      szIngredientsText = sb_szIngredientsText.toString( );}
       {StringBuilder sb_szIngredientsText;
      if ( szIngredientsText == null )
         sb_szIngredientsText = new StringBuilder( 32 );
      else
         sb_szIngredientsText = new StringBuilder( szIngredientsText );
            ZeidonStringConcat( sb_szIngredientsText, 1, 0, szPeriodFiller, 1, 0, 513 );
      szIngredientsText = sb_szIngredientsText.toString( );}
       {StringBuilder sb_szIngredientsText;
      if ( szIngredientsText == null )
         sb_szIngredientsText = new StringBuilder( 32 );
      else
         sb_szIngredientsText = new StringBuilder( szIngredientsText );
            ZeidonStringConcat( sb_szIngredientsText, 1, 0, "</div>", 1, 0, 513 );
      szIngredientsText = sb_szIngredientsText.toString( );}
      //:szWriteBuffer = "               <tr><td class=^tableingredientscol1^ style=^text-align:left;^ nowrap>" + szIngredientsText + "</td>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <tr><td class=^tableingredientscol1^ style=^text-align:left;^ nowrap>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szIngredientsText, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</td>", 1, 0, 32001 );
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

      //:// Column Percent, right justified
      //:GetStringFromAttributeByContext( szPercent, mSPLDef, "SPLD_IngredientsStatement", "Percent", "", 10 )
      {StringBuilder sb_szPercent;
      if ( szPercent == null )
         sb_szPercent = new StringBuilder( 32 );
      else
         sb_szPercent = new StringBuilder( szPercent );
             GetStringFromAttributeByContext( sb_szPercent, mSPLDef, "SPLD_IngredientsStatement", "Percent", "", 10 );
      szPercent = sb_szPercent.toString( );}
      //:szWriteBuffer = "               <td style=^text-align:right;^>" + szPercent + "%</td></tr>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <td style=^text-align:right;^>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szPercent, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "%</td></tr>", 1, 0, 32001 );
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
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_IngredientsStatement", "" );
   } 

   //:END

   //:GetStringFromAttributeByContext( szPercent, mSPLDef, "SPLD_IngredientsSection", "GeneralInactivePercent", "", 10 )
   {StringBuilder sb_szPercent;
   if ( szPercent == null )
      sb_szPercent = new StringBuilder( 32 );
   else
      sb_szPercent = new StringBuilder( szPercent );
       GetStringFromAttributeByContext( sb_szPercent, mSPLDef, "SPLD_IngredientsSection", "GeneralInactivePercent", "", 10 );
   szPercent = sb_szPercent.toString( );}
   //:IF szIngredientsText != "0.000"
   if ( ZeidonStringCompare( szIngredientsText, 1, 0, "0.000", 1, 0, 513 ) != 0 )
   { 
      //:szIngredientsText = mSPLDef.SPLD_IngredientsSection.InertTitle
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szIngredientsText;
      if ( szIngredientsText == null )
         sb_szIngredientsText = new StringBuilder( 32 );
      else
         sb_szIngredientsText = new StringBuilder( szIngredientsText );
             GetVariableFromAttribute( sb_szIngredientsText, mi_lTempInteger_2, 'S', 513, mSPLDef, "SPLD_IngredientsSection", "InertTitle", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szIngredientsText = sb_szIngredientsText.toString( );}
      //:szWriteBuffer = "               <tr><td class=^tableingredientscol1^ style=^text-align:left;^ nowrap><div><b>" + szIngredientsText + szPeriodFiller + "</b></div></td>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <tr><td class=^tableingredientscol1^ style=^text-align:left;^ nowrap><div><b>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szIngredientsText, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szPeriodFiller, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</b></div></td>", 1, 0, 32001 );
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
      //:szWriteBuffer = "               <td style=^text-align:right;^>" + szPercent + "%</td></tr>"
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "               <td style=^text-align:right;^>", 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szPercent, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "%</td></tr>", 1, 0, 32001 );
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

   //:szWriteBuffer = "            </table>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            </table>", 1, 0, 32001 );
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
//:GenerateHTML_Content( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                      INTEGER lFile,
//:                      STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 512 )   szNetContentsText
public int 
omSPLDef_GenerateHTML_Content( View     mSPLDef,
                               int      lFile,
                               String   szWriteBuffer )
{
   String   szNetContentsText = null;
   int      lTempInteger_0 = 0;


   //:// Generate HTML for "NetContents".

   //:// Net Contents.GenerateHTML_Content
   //:szNetContentsText = mSPLDef.SPLD_TemplateSection.TitleOverride
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szNetContentsText;
   if ( szNetContentsText == null )
      sb_szNetContentsText = new StringBuilder( 32 );
   else
      sb_szNetContentsText = new StringBuilder( szNetContentsText );
       GetVariableFromAttribute( sb_szNetContentsText, mi_lTempInteger_0, 'S', 513, mSPLDef, "SPLD_TemplateSection", "TitleOverride", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szNetContentsText = sb_szNetContentsText.toString( );}
   //:IF szNetContentsText = ""
   if ( ZeidonStringCompare( szNetContentsText, 1, 0, "", 1, 0, 513 ) == 0 )
   { 
      //:szNetContentsText = "<p><b>Net Contents: One Gallon</b> (3.784 litres)</p>"
       {StringBuilder sb_szNetContentsText;
      if ( szNetContentsText == null )
         sb_szNetContentsText = new StringBuilder( 32 );
      else
         sb_szNetContentsText = new StringBuilder( szNetContentsText );
            ZeidonStringCopy( sb_szNetContentsText, 1, 0, "<p><b>Net Contents: One Gallon</b> (3.784 litres)</p>", 1, 0, 513 );
      szNetContentsText = sb_szNetContentsText.toString( );}
   } 

   //:END

   //:szWriteBuffer = "            <div id=^netcontents^>" + szNetContentsText + "</div>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <div id=^netcontents^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szNetContentsText, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</div>", 1, 0, 32001 );
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
//:GeneratePDF_Content( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                      INTEGER lFile,
//:                      STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 512 )   szNetContentsText
public int 
omSPLDef_GeneratePDF_Content( View     mSPLDef,
                              int      lFile,
                              String   szWriteBuffer )
{
   String   szNetContentsText = null;
   int      lTempInteger_0 = 0;


   //:// Generate PDF for "NetContents".

   //:// Net Contents.GeneratePDF_Content
   //:szNetContentsText = mSPLDef.SPLD_TemplateSection.TitleOverride
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szNetContentsText;
   if ( szNetContentsText == null )
      sb_szNetContentsText = new StringBuilder( 32 );
   else
      sb_szNetContentsText = new StringBuilder( szNetContentsText );
       GetVariableFromAttribute( sb_szNetContentsText, mi_lTempInteger_0, 'S', 513, mSPLDef, "SPLD_TemplateSection", "TitleOverride", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szNetContentsText = sb_szNetContentsText.toString( );}
   //:IF szNetContentsText = ""
   if ( ZeidonStringCompare( szNetContentsText, 1, 0, "", 1, 0, 513 ) == 0 )
   { 
      //:szNetContentsText = "<p><b>Net Contents: One Gallon</b> (3.784 litres)</p>"
       {StringBuilder sb_szNetContentsText;
      if ( szNetContentsText == null )
         sb_szNetContentsText = new StringBuilder( 32 );
      else
         sb_szNetContentsText = new StringBuilder( szNetContentsText );
            ZeidonStringCopy( sb_szNetContentsText, 1, 0, "<p><b>Net Contents: One Gallon</b> (3.784 litres)</p>", 1, 0, 513 );
      szNetContentsText = sb_szNetContentsText.toString( );}
   } 

   //:END

   //:szWriteBuffer = "            <div id=^netcontents^>" + szNetContentsText + "</div>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <div id=^netcontents^>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szNetContentsText, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</div>", 1, 0, 32001 );
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
//:GenerateHTML_Hazards( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                      INTEGER lFile,
//:                      STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 32000 ) szStatementText
public int 
omSPLDef_GenerateHTML_Hazards( View     mSPLDef,
                               int      lFile,
                               String   szWriteBuffer )
{
   String   szStatementText = null;
   //:STRING ( 20 )    szEPA_RegNbr
   String   szEPA_RegNbr = null;
   //:STRING ( 20 )    szEPA_EstNbr
   String   szEPA_EstNbr = null;
   //:STRING ( 50 )    szWork
   String   szWork = null;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;


   //:// Generate HTML for each Hazards key word.

   //:SET CURSOR FIRST mSPLDef.SPLD_HumanHazardSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_HumanHazardSection", "" );

   //:// Regular Hazard Statements
   //:GetStringFromAttributeByContext( szWork, mSPLDef, "SPLD_HumanHazardSection", "EPA_ChildHazardWarning", "", 50 )
   {StringBuilder sb_szWork;
   if ( szWork == null )
      sb_szWork = new StringBuilder( 32 );
   else
      sb_szWork = new StringBuilder( szWork );
       GetStringFromAttributeByContext( sb_szWork, mSPLDef, "SPLD_HumanHazardSection", "EPA_ChildHazardWarning", "", 50 );
   szWork = sb_szWork.toString( );}
   //:szWriteBuffer = "            <div id=^hazardskoroc^><p>" + szWork + "<br></p></div>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <div id=^hazardskoroc^><p>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szWork, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<br></p></div>", 1, 0, 32001 );
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
   //:szWriteBuffer = "            <div id=^hazardsdanger^><p>" + mSPLDef.SPLD_HumanHazardSection.EPA_SignalWord + "<br></p></div>"
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 33, mSPLDef, "SPLD_HumanHazardSection", "EPA_SignalWord", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <div id=^hazardsdanger^><p>", 1, 0, 32001 );
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
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<br></p></div>", 1, 0, 32001 );
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
   //:szWriteBuffer = "            <div id=^hazardsseepanel^><p>" + mSPLDef.SPLD_HumanHazardSection.dSelectedStatement + "</p></div>"
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
       GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 255, mSPLDef, "SPLD_HumanHazardSection", "dSelectedStatement", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szTempString_1 = sb_szTempString_1.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <div id=^hazardsseepanel^><p>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTempString_1, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</p></div>", 1, 0, 32001 );
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
//:GeneratePDF_Hazards( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                      INTEGER lFile,
//:                      STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 32000 ) szStatementText
public int 
omSPLDef_GeneratePDF_Hazards( View     mSPLDef,
                              int      lFile,
                              String   szWriteBuffer )
{
   String   szStatementText = null;
   //:STRING ( 20 )    szEPA_RegNbr
   String   szEPA_RegNbr = null;
   //:STRING ( 20 )    szEPA_EstNbr
   String   szEPA_EstNbr = null;
   //:STRING ( 50 )    szWork
   String   szWork = null;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;


   //:// Generate PDF for each Hazards key word.

   //:SET CURSOR FIRST mSPLDef.SPLD_HumanHazardSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_HumanHazardSection", "" );

   //:// Regular Hazard Statements
   //:GetStringFromAttributeByContext( szWork, mSPLDef, "SPLD_HumanHazardSection", "EPA_ChildHazardWarning", "", 50 )
   {StringBuilder sb_szWork;
   if ( szWork == null )
      sb_szWork = new StringBuilder( 32 );
   else
      sb_szWork = new StringBuilder( szWork );
       GetStringFromAttributeByContext( sb_szWork, mSPLDef, "SPLD_HumanHazardSection", "EPA_ChildHazardWarning", "", 50 );
   szWork = sb_szWork.toString( );}
   //:szWriteBuffer = "            <div id=^hazardskoroc^><p>" + szWork + "<br></p></div>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <div id=^hazardskoroc^><p>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szWork, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<br></p></div>", 1, 0, 32001 );
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
   //:szWriteBuffer = "            <div id=^hazardsdanger^><p>" + mSPLDef.SPLD_HumanHazardSection.EPA_SignalWord + "<br></p></div>"
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 33, mSPLDef, "SPLD_HumanHazardSection", "EPA_SignalWord", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <div id=^hazardsdanger^><p>", 1, 0, 32001 );
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
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "<br></p></div>", 1, 0, 32001 );
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
   //:szWriteBuffer = "            <div id=^hazardsseepanel^><p>" + mSPLDef.SPLD_HumanHazardSection.dSelectedStatement + "</p></div>"
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
       GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 255, mSPLDef, "SPLD_HumanHazardSection", "dSelectedStatement", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szTempString_1 = sb_szTempString_1.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <div id=^hazardsseepanel^><p>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szTempString_1, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</p></div>", 1, 0, 32001 );
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
//:GenerateHTML_General( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                      INTEGER lFile,
//:                      STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 32000 )  szStatementText
public int 
omSPLDef_GenerateHTML_General( View     mSPLDef,
                               int      lFile,
                               String   szWriteBuffer )
{
   String   szStatementText = null;
   //:STRING ( 256 )    szCombinedTitle
   String   szCombinedTitle = null;
   //:STRING ( 32 )     szLineHeight
   String   szLineHeight = null;
   //:STRING ( 10 )     szStatementLeading
   String   szStatementLeading = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;


   //:// Generate HTML for a "General" Section, which can be Precautionary, Environmental/Physical Hazard or First Aid.

   //:szLineHeight = ""
    {StringBuilder sb_szLineHeight;
   if ( szLineHeight == null )
      sb_szLineHeight = new StringBuilder( 32 );
   else
      sb_szLineHeight = new StringBuilder( szLineHeight );
      ZeidonStringCopy( sb_szLineHeight, 1, 0, "", 1, 0, 33 );
   szLineHeight = sb_szLineHeight.toString( );}
   //:szStatementLeading = mSPLDef.SPLD_TemplateSection.dStatementLeading
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szStatementLeading;
   if ( szStatementLeading == null )
      sb_szStatementLeading = new StringBuilder( 32 );
   else
      sb_szStatementLeading = new StringBuilder( szStatementLeading );
       GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_0, 'S', 11, mSPLDef, "SPLD_TemplateSection", "dStatementLeading", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szStatementLeading = sb_szStatementLeading.toString( );}
   //:IF szStatementLeading != ""
   if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szLineHeight = "line-height:" + szStatementLeading +";"
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szStatementLeading = mSPLDef.SPLD_TemplatePanel.dStatementLeadingDefault
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szStatementLeading;
      if ( szStatementLeading == null )
         sb_szStatementLeading = new StringBuilder( 32 );
      else
         sb_szStatementLeading = new StringBuilder( szStatementLeading );
             GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "dStatementLeadingDefault", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szStatementLeading = sb_szStatementLeading.toString( );}
      //:IF szStatementLeading != ""
      if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szLineHeight = "line-height:" + szStatementLeading +";"
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
      } 

      //:END
   } 

   //:END

   //:SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.ID = mSPLDef.SPLDT_GeneralSection.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mSPLDef, "SPLDT_GeneralSection", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_GeneralSection", "ID", lTempInteger_2, "" );
   //:// DisplayEntityInstance( mSPLDef, "SPLD_GeneralSection" )
   //:GenerateHTML_Title( mSPLDef, lFile, "SPLD_GeneralSection", szCombinedTitle, szWriteBuffer )
   omSPLDef_GenerateHTML_Title( mSPLDef, lFile, "SPLD_GeneralSection", szCombinedTitle, szWriteBuffer );

   //:FOR EACH mSPLDef.SPLD_GeneralStatement
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_GeneralStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:szStatementText = szCombinedTitle + mSPLDef.SPLD_GeneralStatement.Text
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_3, 'S', 32001, mSPLDef, "SPLD_GeneralStatement", "Text", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szStatementText;
      if ( szStatementText == null )
         sb_szStatementText = new StringBuilder( 32 );
      else
         sb_szStatementText = new StringBuilder( szStatementText );
            ZeidonStringCopy( sb_szStatementText, 1, 0, szCombinedTitle, 1, 0, 32001 );
      szStatementText = sb_szStatementText.toString( );}
       {StringBuilder sb_szStatementText;
      if ( szStatementText == null )
         sb_szStatementText = new StringBuilder( 32 );
      else
         sb_szStatementText = new StringBuilder( szStatementText );
            ZeidonStringConcat( sb_szStatementText, 1, 0, szTempString_0, 1, 0, 32001 );
      szStatementText = sb_szStatementText.toString( );}
      //:szWriteBuffer = szCombinedTitle + szStatementText
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szCombinedTitle, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}

      //:IF szLineHeight != ""
      if ( ZeidonStringCompare( szLineHeight, 1, 0, "", 1, 0, 33 ) != 0 )
      { 
         //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
         m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 );
         //:ELSE
      } 
      else
      { 
         //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
         m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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

      //:szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
       {StringBuilder sb_szCombinedTitle;
      if ( szCombinedTitle == null )
         sb_szCombinedTitle = new StringBuilder( 32 );
      else
         sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
            ZeidonStringCopy( sb_szCombinedTitle, 1, 0, "", 1, 0, 257 );
      szCombinedTitle = sb_szCombinedTitle.toString( );}
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_GeneralStatement", "" );
   } 


   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GeneratePDF_General( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                      INTEGER lFile,
//:                      STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 32000 )  szStatementText
public int 
omSPLDef_GeneratePDF_General( View     mSPLDef,
                              int      lFile,
                              String   szWriteBuffer )
{
   String   szStatementText = null;
   //:STRING ( 256 )    szCombinedTitle
   String   szCombinedTitle = null;
   //:STRING ( 32 )     szLineHeight
   String   szLineHeight = null;
   //:STRING ( 10 )     szStatementLeading
   String   szStatementLeading = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;


   //:// Generate PDF for a "General" Section, which can be Precautionary, Environmental/Physical Hazard or First Aid.

   //:szLineHeight = ""
    {StringBuilder sb_szLineHeight;
   if ( szLineHeight == null )
      sb_szLineHeight = new StringBuilder( 32 );
   else
      sb_szLineHeight = new StringBuilder( szLineHeight );
      ZeidonStringCopy( sb_szLineHeight, 1, 0, "", 1, 0, 33 );
   szLineHeight = sb_szLineHeight.toString( );}
   //:szStatementLeading = mSPLDef.SPLD_TemplateSection.dStatementLeading
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szStatementLeading;
   if ( szStatementLeading == null )
      sb_szStatementLeading = new StringBuilder( 32 );
   else
      sb_szStatementLeading = new StringBuilder( szStatementLeading );
       GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_0, 'S', 11, mSPLDef, "SPLD_TemplateSection", "dStatementLeading", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szStatementLeading = sb_szStatementLeading.toString( );}
   //:IF szStatementLeading != ""
   if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szLineHeight = "line-height:" + szStatementLeading +";"
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szStatementLeading = mSPLDef.SPLD_TemplatePanel.dStatementLeadingDefault
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szStatementLeading;
      if ( szStatementLeading == null )
         sb_szStatementLeading = new StringBuilder( 32 );
      else
         sb_szStatementLeading = new StringBuilder( szStatementLeading );
             GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "dStatementLeadingDefault", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szStatementLeading = sb_szStatementLeading.toString( );}
      //:IF szStatementLeading != ""
      if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szLineHeight = "line-height:" + szStatementLeading +";"
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
      } 

      //:END
   } 

   //:END

   //:SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.ID = mSPLDef.SPLDT_GeneralSection.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mSPLDef, "SPLDT_GeneralSection", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_GeneralSection", "ID", lTempInteger_2, "" );
   //:// DisplayEntityInstance( mSPLDef, "SPLD_GeneralSection" )
   //:GeneratePDF_Title( mSPLDef, lFile, "SPLD_GeneralSection", szCombinedTitle, szWriteBuffer )
   omSPLDef_GeneratePDF_Title( mSPLDef, lFile, "SPLD_GeneralSection", szCombinedTitle, szWriteBuffer );

   //:FOR EACH mSPLDef.SPLD_GeneralStatement
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_GeneralStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:szStatementText = szCombinedTitle + mSPLDef.SPLD_GeneralStatement.Text
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_3, 'S', 32001, mSPLDef, "SPLD_GeneralStatement", "Text", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szStatementText;
      if ( szStatementText == null )
         sb_szStatementText = new StringBuilder( 32 );
      else
         sb_szStatementText = new StringBuilder( szStatementText );
            ZeidonStringCopy( sb_szStatementText, 1, 0, szCombinedTitle, 1, 0, 32001 );
      szStatementText = sb_szStatementText.toString( );}
       {StringBuilder sb_szStatementText;
      if ( szStatementText == null )
         sb_szStatementText = new StringBuilder( 32 );
      else
         sb_szStatementText = new StringBuilder( szStatementText );
            ZeidonStringConcat( sb_szStatementText, 1, 0, szTempString_0, 1, 0, 32001 );
      szStatementText = sb_szStatementText.toString( );}
      //:szWriteBuffer = szCombinedTitle + szStatementText
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szCombinedTitle, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}
       {StringBuilder sb_szWriteBuffer;
      if ( szWriteBuffer == null )
         sb_szWriteBuffer = new StringBuilder( 32 );
      else
         sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
            ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
      szWriteBuffer = sb_szWriteBuffer.toString( );}

      //:IF szLineHeight != ""
      if ( ZeidonStringCompare( szLineHeight, 1, 0, "", 1, 0, 33 ) != 0 )
      { 
         //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
         m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 );
         //:ELSE
      } 
      else
      { 
         //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
         m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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

      //:szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
       {StringBuilder sb_szCombinedTitle;
      if ( szCombinedTitle == null )
         sb_szCombinedTitle = new StringBuilder( 32 );
      else
         sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
            ZeidonStringCopy( sb_szCombinedTitle, 1, 0, "", 1, 0, 257 );
      szCombinedTitle = sb_szCombinedTitle.toString( );}
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_GeneralStatement", "" );
   } 


   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateHTML_StorDisp( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                       INTEGER lFile,
//:                       STRING ( 32 )    szSectionType,
//:                       STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 32000 )  szStatementText
public int 
omSPLDef_GenerateHTML_StorDisp( View     mSPLDef,
                                int      lFile,
                                String   szSectionType,
                                String   szWriteBuffer )
{
   String   szStatementText = null;
   //:STRING ( 32 )     szType
   String   szType = null;
   //:STRING ( 512 )    szCombinedTitle
   String   szCombinedTitle = null;
   //:STRING ( 32 )     szLineHeight
   String   szLineHeight = null;
   //:STRING ( 10 )     szStatementLeading
   String   szStatementLeading = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_4 = 0;


   //:// Only generate the section if its Container Volume matches that for the Template???

   //:// Generate HTML for Storage and Disposal Section.

   //:szLineHeight = ""
    {StringBuilder sb_szLineHeight;
   if ( szLineHeight == null )
      sb_szLineHeight = new StringBuilder( 32 );
   else
      sb_szLineHeight = new StringBuilder( szLineHeight );
      ZeidonStringCopy( sb_szLineHeight, 1, 0, "", 1, 0, 33 );
   szLineHeight = sb_szLineHeight.toString( );}
   //:szStatementLeading = mSPLDef.SPLD_TemplateSection.dStatementLeading
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szStatementLeading;
   if ( szStatementLeading == null )
      sb_szStatementLeading = new StringBuilder( 32 );
   else
      sb_szStatementLeading = new StringBuilder( szStatementLeading );
       GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_0, 'S', 11, mSPLDef, "SPLD_TemplateSection", "dStatementLeading", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szStatementLeading = sb_szStatementLeading.toString( );}
   //:IF szStatementLeading != ""
   if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szLineHeight = "line-height:" + szStatementLeading +";"
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szStatementLeading = mSPLDef.SPLD_TemplatePanel.dStatementLeadingDefault
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szStatementLeading;
      if ( szStatementLeading == null )
         sb_szStatementLeading = new StringBuilder( 32 );
      else
         sb_szStatementLeading = new StringBuilder( szStatementLeading );
             GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "dStatementLeadingDefault", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szStatementLeading = sb_szStatementLeading.toString( );}
      //:IF szStatementLeading != ""
      if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szLineHeight = "line-height:" + szStatementLeading +";"
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
      } 

      //:END
   } 

   //:END

   //:SET CURSOR FIRST mSPLDef.SPLD_StorageDisposalSection WHERE mSPLDef.SPLD_StorageDisposalSection.ID = mSPLDef.SPLDT_StorageDisposalSection.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mSPLDef, "SPLDT_StorageDisposalSection", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_StorageDisposalSection", "ID", lTempInteger_2, "" );
   //:// DisplayEntityInstance( mSPLDef, "SPLD_StorageDisposalSection" )
   //:szType = mSPLDef.SPLD_StorageDisposalSection.SDSectionType
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szType;
   if ( szType == null )
      sb_szType = new StringBuilder( 32 );
   else
      sb_szType = new StringBuilder( szType );
       GetVariableFromAttribute( sb_szType, mi_lTempInteger_3, 'S', 33, mSPLDef, "SPLD_StorageDisposalSection", "SDSectionType", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szType = sb_szType.toString( );}
   //:IF szType = szSectionType
   if ( ZeidonStringCompare( szType, 1, 0, szSectionType, 1, 0, 33 ) == 0 )
   { 
      //:GenerateHTML_Title( mSPLDef, lFile, "SPLD_StorageDisposalSection", szCombinedTitle, szWriteBuffer )
      omSPLDef_GenerateHTML_Title( mSPLDef, lFile, "SPLD_StorageDisposalSection", szCombinedTitle, szWriteBuffer );

      //:FOR EACH mSPLDef.SPLD_StorageDisposalStatement
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_StorageDisposalStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 

         //:szStatementText = szCombinedTitle + mSPLDef.SPLD_StorageDisposalStatement.Text
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_4, 'S', 32001, mSPLDef, "SPLD_StorageDisposalStatement", "Text", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                  ZeidonStringCopy( sb_szStatementText, 1, 0, szCombinedTitle, 1, 0, 32001 );
         szStatementText = sb_szStatementText.toString( );}
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                  ZeidonStringConcat( sb_szStatementText, 1, 0, szTempString_0, 1, 0, 32001 );
         szStatementText = sb_szStatementText.toString( );}
         //:szWriteBuffer = szCombinedTitle + szStatementText
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szCombinedTitle, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}

         //:IF szLineHeight != ""
         if ( ZeidonStringCompare( szLineHeight, 1, 0, "", 1, 0, 33 ) != 0 )
         { 
            //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
            m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 );
            //:ELSE
         } 
         else
         { 
            //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
            m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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

         //:szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
          {StringBuilder sb_szCombinedTitle;
         if ( szCombinedTitle == null )
            sb_szCombinedTitle = new StringBuilder( 32 );
         else
            sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                  ZeidonStringCopy( sb_szCombinedTitle, 1, 0, "", 1, 0, 513 );
         szCombinedTitle = sb_szCombinedTitle.toString( );}
         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_StorageDisposalStatement", "" );
      } 


      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GeneratePDF_StorDisp( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                       INTEGER lFile,
//:                       STRING ( 32 )    szSectionType,
//:                       STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 32000 )  szStatementText
public int 
omSPLDef_GeneratePDF_StorDisp( View     mSPLDef,
                               int      lFile,
                               String   szSectionType,
                               String   szWriteBuffer )
{
   String   szStatementText = null;
   //:STRING ( 32 )     szType
   String   szType = null;
   //:STRING ( 512 )    szCombinedTitle
   String   szCombinedTitle = null;
   //:STRING ( 32 )     szLineHeight
   String   szLineHeight = null;
   //:STRING ( 10 )     szStatementLeading
   String   szStatementLeading = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_4 = 0;


   //:// Only generate the section if its Container Volume matches that for the Template???

   //:// Generate PDF for Storage and Disposal Section.

   //:szLineHeight = ""
    {StringBuilder sb_szLineHeight;
   if ( szLineHeight == null )
      sb_szLineHeight = new StringBuilder( 32 );
   else
      sb_szLineHeight = new StringBuilder( szLineHeight );
      ZeidonStringCopy( sb_szLineHeight, 1, 0, "", 1, 0, 33 );
   szLineHeight = sb_szLineHeight.toString( );}
   //:szStatementLeading = mSPLDef.SPLD_TemplateSection.dStatementLeading
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szStatementLeading;
   if ( szStatementLeading == null )
      sb_szStatementLeading = new StringBuilder( 32 );
   else
      sb_szStatementLeading = new StringBuilder( szStatementLeading );
       GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_0, 'S', 11, mSPLDef, "SPLD_TemplateSection", "dStatementLeading", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szStatementLeading = sb_szStatementLeading.toString( );}
   //:IF szStatementLeading != ""
   if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szLineHeight = "line-height:" + szStatementLeading +";"
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
       {StringBuilder sb_szLineHeight;
      if ( szLineHeight == null )
         sb_szLineHeight = new StringBuilder( 32 );
      else
         sb_szLineHeight = new StringBuilder( szLineHeight );
            ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
      szLineHeight = sb_szLineHeight.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szStatementLeading = mSPLDef.SPLD_TemplatePanel.dStatementLeadingDefault
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szStatementLeading;
      if ( szStatementLeading == null )
         sb_szStatementLeading = new StringBuilder( 32 );
      else
         sb_szStatementLeading = new StringBuilder( szStatementLeading );
             GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "dStatementLeadingDefault", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szStatementLeading = sb_szStatementLeading.toString( );}
      //:IF szStatementLeading != ""
      if ( ZeidonStringCompare( szStatementLeading, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szLineHeight = "line-height:" + szStatementLeading +";"
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, szStatementLeading, 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
          {StringBuilder sb_szLineHeight;
         if ( szLineHeight == null )
            sb_szLineHeight = new StringBuilder( 32 );
         else
            sb_szLineHeight = new StringBuilder( szLineHeight );
                  ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
         szLineHeight = sb_szLineHeight.toString( );}
      } 

      //:END
   } 

   //:END

   //:SET CURSOR FIRST mSPLDef.SPLD_StorageDisposalSection WHERE mSPLDef.SPLD_StorageDisposalSection.ID = mSPLDef.SPLDT_StorageDisposalSection.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mSPLDef, "SPLDT_StorageDisposalSection", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_StorageDisposalSection", "ID", lTempInteger_2, "" );
   //:// DisplayEntityInstance( mSPLDef, "SPLD_StorageDisposalSection" )
   //:szType = mSPLDef.SPLD_StorageDisposalSection.SDSectionType
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szType;
   if ( szType == null )
      sb_szType = new StringBuilder( 32 );
   else
      sb_szType = new StringBuilder( szType );
       GetVariableFromAttribute( sb_szType, mi_lTempInteger_3, 'S', 33, mSPLDef, "SPLD_StorageDisposalSection", "SDSectionType", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szType = sb_szType.toString( );}
   //:IF szType = szSectionType
   if ( ZeidonStringCompare( szType, 1, 0, szSectionType, 1, 0, 33 ) == 0 )
   { 
      //:GeneratePDF_Title( mSPLDef, lFile, "SPLD_StorageDisposalSection", szCombinedTitle, szWriteBuffer )
      omSPLDef_GeneratePDF_Title( mSPLDef, lFile, "SPLD_StorageDisposalSection", szCombinedTitle, szWriteBuffer );

      //:FOR EACH mSPLDef.SPLD_StorageDisposalStatement
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_StorageDisposalStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 

         //:szStatementText = szCombinedTitle + mSPLDef.SPLD_StorageDisposalStatement.Text
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_4, 'S', 32001, mSPLDef, "SPLD_StorageDisposalStatement", "Text", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                  ZeidonStringCopy( sb_szStatementText, 1, 0, szCombinedTitle, 1, 0, 32001 );
         szStatementText = sb_szStatementText.toString( );}
          {StringBuilder sb_szStatementText;
         if ( szStatementText == null )
            sb_szStatementText = new StringBuilder( 32 );
         else
            sb_szStatementText = new StringBuilder( szStatementText );
                  ZeidonStringConcat( sb_szStatementText, 1, 0, szTempString_0, 1, 0, 32001 );
         szStatementText = sb_szStatementText.toString( );}
         //:szWriteBuffer = szCombinedTitle + szStatementText
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szCombinedTitle, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}
          {StringBuilder sb_szWriteBuffer;
         if ( szWriteBuffer == null )
            sb_szWriteBuffer = new StringBuilder( 32 );
         else
            sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                  ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szStatementText, 1, 0, 32001 );
         szWriteBuffer = sb_szWriteBuffer.toString( );}

         //:IF szLineHeight != ""
         if ( ZeidonStringCompare( szLineHeight, 1, 0, "", 1, 0, 33 ) != 0 )
         { 
            //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
            m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 );
            //:ELSE
         } 
         else
         { 
            //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
            m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
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

         //:szCombinedTitle = ""    // null out any combined Title that was to be included on the first statement
          {StringBuilder sb_szCombinedTitle;
         if ( szCombinedTitle == null )
            sb_szCombinedTitle = new StringBuilder( 32 );
         else
            sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                  ZeidonStringCopy( sb_szCombinedTitle, 1, 0, "", 1, 0, 513 );
         szCombinedTitle = sb_szCombinedTitle.toString( );}
         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_StorageDisposalStatement", "" );
      } 


      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateHTML_EPA_Reg( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                      INTEGER lFile,
//:                      STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 32000 ) szStatementText
public int 
omSPLDef_GenerateHTML_EPA_Reg( View     mSPLDef,
                               int      lFile,
                               String   szWriteBuffer )
{
   String   szStatementText = null;
   //:STRING ( 20 )    szEPA_RegNbr
   String   szEPA_RegNbr = null;
   //:STRING ( 20 )    szEPA_EstNbr
   String   szEPA_EstNbr = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:// Generate HTML for EPA Reg. No. and EPA Est. No..

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
   //:szWriteBuffer = "            <div id=^epareg^><p>" + "EPA Reg. No. " + szEPA_RegNbr + " &nbsp; &nbsp; EPA Est. No. " + szEPA_EstNbr + "</p></div>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <div id=^epareg^><p>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "EPA Reg. No. ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szEPA_RegNbr, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " &nbsp; &nbsp; EPA Est. No. ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szEPA_EstNbr, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</p></div>", 1, 0, 32001 );
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
//:GeneratePDF_EPA_Reg( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                      INTEGER lFile,
//:                      STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 32000 ) szStatementText
public int 
omSPLDef_GeneratePDF_EPA_Reg( View     mSPLDef,
                              int      lFile,
                              String   szWriteBuffer )
{
   String   szStatementText = null;
   //:STRING ( 20 )    szEPA_RegNbr
   String   szEPA_RegNbr = null;
   //:STRING ( 20 )    szEPA_EstNbr
   String   szEPA_EstNbr = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:// Generate PDF for EPA Reg. No. and EPA Est. No..

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
   //:szWriteBuffer = "            <div id=^epareg^><p>" + "EPA Reg. No. " + szEPA_RegNbr + " &nbsp; &nbsp; EPA Est. No. " + szEPA_EstNbr + "</p></div>"
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "            <div id=^epareg^><p>", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "EPA Reg. No. ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szEPA_RegNbr, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, " &nbsp; &nbsp; EPA Est. No. ", 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szEPA_EstNbr, 1, 0, 32001 );
   szWriteBuffer = sb_szWriteBuffer.toString( );}
    {StringBuilder sb_szWriteBuffer;
   if ( szWriteBuffer == null )
      sb_szWriteBuffer = new StringBuilder( 32 );
   else
      sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
      ZeidonStringConcat( sb_szWriteBuffer, 1, 0, "</p></div>", 1, 0, 32001 );
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
public int 
omSPLDef_dFullNameLFM( View     mSPLDef,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{

   //:dFullNameLFM( VIEW mSPLDef BASED ON LOD mSPLDef,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:PersonName_LastFirstMiddle( mSPLDef, InternalEntityStructure,
         //:                           InternalAttribStructure, GetOrSetFlag )
         {
          ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
          m_ZGlobal1_Operation.PersonName_LastFirstMiddle( mSPLDef, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
          // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
         }
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
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 129, mSPLDef, "PrimaryRegistrant", "EPA_CompanyNumber", "", 0 );
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
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 129, mSPLDef, "Subregistrant", "EPA_CompanyNumber", "", 0 );
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
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;


   //:// IssueError( NewSLC, 0, 0, "Start of Build SPLD" )

   //:// Build a new SPLD from the selected Subregistrant Label Content entry.

   //:// Tie back to SLC.
   //:IF NewSPLD.SubregLabelContent DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( NewSPLD, "SubregLabelContent" );
   if ( lTempInteger_0 != 0 )
   { 
      //:INCLUDE NewSPLD.SubregLabelContent FROM SourceSLC.SubregLabelContent
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "SubregLabelContent", SourceSLC, "SubregLabelContent", zPOS_AFTER );
   } 

   //:END

   //:IF NewSPLD.MasterLabelContent DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( NewSPLD, "MasterLabelContent" );
   if ( lTempInteger_1 != 0 )
   { 
      //:INCLUDE NewSPLD.MasterLabelContent FROM SourceSLC.MasterLabelContent
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "MasterLabelContent", SourceSLC, "MasterLabelContent", zPOS_AFTER );
   } 

   //:END

   //:// Usage Entries ... Surface, Application Type, Area of Use, Organism Claim
   //:FOR EACH SourceSLC.S_Usage
   RESULT = SetCursorFirstEntity( SourceSLC, "S_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// Check for duplicates.
      //:// IF SourceSLC.S_Usage.Selected = "Y"  we want all usages (at least for the moment)!!!
      //:   SET CURSOR FIRST NewSPLD.SPLD_Usage WHERE NewSPLD.SPLD_Usage.UsageType = SourceSLC.S_Usage.UsageType
      //:                                         AND NewSPLD.SPLD_Usage.Name = SourceSLC.S_Usage.Name
      RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_Usage", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSPLD, "SPLD_Usage", "UsageType", SourceSLC, "S_Usage", "UsageType" ) != 0 ||
                 CompareAttributeToAttribute( NewSPLD, "SPLD_Usage", "Name", SourceSLC, "S_Usage", "Name" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( NewSPLD, "SPLD_Usage", "" );
         } 

      } 

      //:   IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:   SET CURSOR LAST NewSPLD.SPLD_Usage
         RESULT = SetCursorLastEntity( NewSPLD, "SPLD_Usage", "" );
         //:   CREATE ENTITY NewSPLD.SPLD_Usage
         RESULT = CreateEntity( NewSPLD, "SPLD_Usage", zPOS_AFTER );
         //:   SetMatchingAttributesByName( NewSPLD, "SPLD_Usage", SourceSLC, "S_Usage", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_Usage", SourceSLC, "S_Usage", zSET_NULL );
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_Usage", "" );
      //:   END
   } 

   //:// END
   //:END

   //:// General Section ... Precautionary, First Aid, Other Hazard
   //:FOR EACH SourceSLC.S_GeneralSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// IF SourceSLC.S_GeneralSection.Selected = "Y"  we want 'em all
      //:   CREATE ENTITY NewSPLD.SPLD_GeneralSection
      RESULT = CreateEntity( NewSPLD, "SPLD_GeneralSection", zPOS_AFTER );
      //:   SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralSection", SourceSLC, "S_GeneralSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralSection", SourceSLC, "S_GeneralSection", zSET_NULL );
      //:   INCLUDE NewSPLD.S_GeneralSection FROM SourceSLC.S_GeneralSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_GeneralSection", SourceSLC, "S_GeneralSection", zPOS_AFTER );
      //:   FOR EACH SourceSLC.S_GeneralStatement
      RESULT = SetCursorFirstEntity( SourceSLC, "S_GeneralStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:   CREATE ENTITY NewSPLD.SPLD_GeneralStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_GeneralStatement", zPOS_AFTER );
         //:   SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralStatement", SourceSLC, "S_GeneralStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_GeneralStatement", SourceSLC, "S_GeneralStatement", zSET_NULL );
         //:   INCLUDE NewSPLD.S_GeneralStatement FROM SourceSLC.S_GeneralStatement
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_GeneralStatement", SourceSLC, "S_GeneralStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceSLC, "S_GeneralStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_GeneralSection", "" );
      //:   END
   } 

   //:// END
   //:END

   //:// Ingredients Section
   //:FOR EACH SourceSLC.S_IngredientsSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_IngredientsSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// IF SourceSLC.S_IngredientsSection.Selected = "Y"  we want all ingredients!!!
      //:   CREATE ENTITY NewSPLD.SPLD_IngredientsSection
      RESULT = CreateEntity( NewSPLD, "SPLD_IngredientsSection", zPOS_AFTER );
      //:   SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsSection", SourceSLC, "S_IngredientsSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsSection", SourceSLC, "S_IngredientsSection", zSET_NULL );
      //:   INCLUDE NewSPLD.S_IngredientsSection FROM SourceSLC.S_IngredientsSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_IngredientsSection", SourceSLC, "S_IngredientsSection", zPOS_AFTER );
      //:   FOR EACH SourceSLC.S_IngredientsStatement
      RESULT = SetCursorFirstEntity( SourceSLC, "S_IngredientsStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:   CREATE ENTITY NewSPLD.SPLD_IngredientsStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_IngredientsStatement", zPOS_AFTER );
         //:   SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsStatement", SourceSLC, "S_IngredientsStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_IngredientsStatement", SourceSLC, "S_IngredientsStatement", zSET_NULL );
         //:   INCLUDE NewSPLD.S_IngredientsStatement FROM SourceSLC.S_IngredientsStatement
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_IngredientsStatement", SourceSLC, "S_IngredientsStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceSLC, "S_IngredientsStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_IngredientsSection", "" );
      //:   END
   } 

   //:// END
   //:END

   //:// StorageDisposal Section
   //:FOR EACH SourceSLC.S_StorageDisposalSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_StorageDisposalSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// IF SourceSLC.S_StorageDisposalSection.Selected = "Y"  we want all storage/disposal!!!
      //:   CREATE ENTITY NewSPLD.SPLD_StorageDisposalSection
      RESULT = CreateEntity( NewSPLD, "SPLD_StorageDisposalSection", zPOS_AFTER );
      //:   SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalSection", SourceSLC, "S_StorageDisposalSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalSection", SourceSLC, "S_StorageDisposalSection", zSET_NULL );
      //:   INCLUDE NewSPLD.S_StorageDisposalSection FROM SourceSLC.S_StorageDisposalSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_StorageDisposalSection", SourceSLC, "S_StorageDisposalSection", zPOS_AFTER );
      //:   FOR EACH SourceSLC.S_StorageDisposalStatement
      RESULT = SetCursorFirstEntity( SourceSLC, "S_StorageDisposalStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:   CREATE ENTITY NewSPLD.SPLD_StorageDisposalStatement
         RESULT = CreateEntity( NewSPLD, "SPLD_StorageDisposalStatement", zPOS_AFTER );
         //:   SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalStatement", SourceSLC, "S_StorageDisposalStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSPLD, "SPLD_StorageDisposalStatement", SourceSLC, "S_StorageDisposalStatement", zSET_NULL );
         //:   INCLUDE NewSPLD.S_StorageDisposalStatement FROM SourceSLC.S_StorageDisposalStatement
         RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_StorageDisposalStatement", SourceSLC, "S_StorageDisposalStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceSLC, "S_StorageDisposalStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_StorageDisposalSection", "" );
      //:   END
   } 

   //:// END
   //:END

   //:// DirectionsForUse Section
   //:FOR EACH SourceSLC.S_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF SourceSLC.S_DirectionsForUseSection.Selected = "Y"  // get only those selected
      if ( CompareAttributeToString( SourceSLC, "S_DirectionsForUseSection", "Selected", "Y" ) == 0 )
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

         //:END

         //:FOR EACH SourceSLC.S_DirectionsUsage WITHIN SourceSLC.S_DirectionsForUseSection
         RESULT = SetCursorFirstEntity( SourceSLC, "S_DirectionsUsage", "S_DirectionsForUseSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST NewSPLD.SPLD_Usage WHERE NewSPLD.SPLD_Usage.UsageType = SourceSLC.S_DirectionsUsage.UsageType
            //:                                      AND NewSPLD.SPLD_Usage.Name = SourceSLC.S_DirectionsUsage.Name
            RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSPLD, "SPLD_Usage", "UsageType", SourceSLC, "S_DirectionsUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( NewSPLD, "SPLD_Usage", "Name", SourceSLC, "S_DirectionsUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( NewSPLD, "SPLD_Usage", "" );
               } 

            } 

            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:// Check duplicates.
               //:SET CURSOR FIRST NewSPLD.SPLD_OriginalDirectionsUsage WHERE NewSPLD.SPLD_OriginalDirectionsUsage.UsageType = SourceSLC.S_DirectionsUsage.UsageType
               //:                                                        AND NewSPLD.SPLD_OriginalDirectionsUsage.Name = SourceSLC.S_DirectionsUsage.Name
               RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_OriginalDirectionsUsage", "" );
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSPLD, "SPLD_OriginalDirectionsUsage", "UsageType", SourceSLC, "S_DirectionsUsage", "UsageType" ) != 0 ||
                        CompareAttributeToAttribute( NewSPLD, "SPLD_OriginalDirectionsUsage", "Name", SourceSLC, "S_DirectionsUsage", "Name" ) != 0 ) )
                  { 
                     RESULT = SetCursorNextEntity( NewSPLD, "SPLD_OriginalDirectionsUsage", "" );
                  } 

               } 

               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:CREATE ENTITY NewSPLD.SPLD_DirectionsOrdering
                  RESULT = CreateEntity( NewSPLD, "SPLD_DirectionsOrdering", zPOS_AFTER );
                  //:INCLUDE NewSPLD.SPLD_DirectionsUsage         FROM NewSPLD.SPLD_Usage
                  RESULT = IncludeSubobjectFromSubobject( NewSPLD, "SPLD_DirectionsUsage", NewSPLD, "SPLD_Usage", zPOS_AFTER );
                  //:CREATE ENTITY NewSPLD.SPLD_OriginalDirectionsOrdering
                  RESULT = CreateEntity( NewSPLD, "SPLD_OriginalDirectionsOrdering", zPOS_AFTER );
                  //:INCLUDE NewSPLD.SPLD_OriginalDirectionsUsage FROM NewSPLD.SPLD_Usage
                  RESULT = IncludeSubobjectFromSubobject( NewSPLD, "SPLD_OriginalDirectionsUsage", NewSPLD, "SPLD_Usage", zPOS_AFTER );
               } 

               //:END
            } 

            RESULT = SetCursorNextEntity( SourceSLC, "S_DirectionsUsage", "S_DirectionsForUseSection" );
            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// DirectionsForUse Usage
   //:FOR EACH SourceSLC.S_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF SourceSLC.S_DirectionsForUseSection.Selected = "Y"  // get only those selected
      if ( CompareAttributeToString( SourceSLC, "S_DirectionsForUseSection", "Selected", "Y" ) == 0 )
      { 
         //:SET CURSOR FIRST NewSPLD.SPLD_DirectionsForUseSection
         //:           WHERE NewSPLD.SPLD_DirectionsForUseSection.Title = SourceSLC.S_DirectionsForUseSection.Title
         {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetStringFromAttribute( sb_szTempString_0, SourceSLC, "S_DirectionsForUseSection", "Title" );
         szTempString_0 = sb_szTempString_0.toString( );}
         RESULT = SetCursorFirstEntityByString( NewSPLD, "SPLD_DirectionsForUseSection", "Title", szTempString_0, "" );
         //:FOR EACH SourceSLC.S_DirectionsUsage WITHIN SourceSLC.S_DirectionsForUseSection
         RESULT = SetCursorFirstEntity( SourceSLC, "S_DirectionsUsage", "S_DirectionsForUseSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CreateViewFromView( mSPLDef2, NewSPLD )
            CreateViewFromView( mSPLDef2, NewSPLD );
            //:SET CURSOR FIRST mSPLDef2.SPLD_DirectionsUsage WITHIN mSPLDef2.SPLD_DirectionsForUseSection
            //:           WHERE mSPLDef2.SPLD_DirectionsUsage.UsageType = SourceSLC.S_DirectionsUsage.UsageType
            //:             AND mSPLDef2.SPLD_DirectionsUsage.Name = SourceSLC.S_DirectionsUsage.Name
            RESULT = SetCursorFirstEntity( mSPLDef2, "SPLD_DirectionsUsage", "SPLD_DirectionsForUseSection" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSPLDef2, "SPLD_DirectionsUsage", "UsageType", SourceSLC, "S_DirectionsUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( mSPLDef2, "SPLD_DirectionsUsage", "Name", SourceSLC, "S_DirectionsUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( mSPLDef2, "SPLD_DirectionsUsage", "SPLD_DirectionsForUseSection" );
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:SET CURSOR FIRST NewSPLD.SPLD_Usage WHERE NewSPLD.SPLD_Usage.Name = SourceSLC.S_DirectionsUsage.Name
               //:                                      AND NewSPLD.SPLD_Usage.UsageType = SourceSLC.S_DirectionsUsage.UsageType
               RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_Usage", "" );
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSPLD, "SPLD_Usage", "Name", SourceSLC, "S_DirectionsUsage", "Name" ) != 0 ||
                        CompareAttributeToAttribute( NewSPLD, "SPLD_Usage", "UsageType", SourceSLC, "S_DirectionsUsage", "UsageType" ) != 0 ) )
                  { 
                     RESULT = SetCursorNextEntity( NewSPLD, "SPLD_Usage", "" );
                  } 

               } 

               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:IssueError( NewSPLD, 0, 0, "Programming Error 1" )
                  IssueError( NewSPLD, 0, 0, "Programming Error 1" );
               } 

               //:END

               //:CREATE ENTITY NewSPLD.SPLD_DirectionsOrdering
               RESULT = CreateEntity( NewSPLD, "SPLD_DirectionsOrdering", zPOS_AFTER );
               //:INCLUDE NewSPLD.SPLD_DirectionsUsage FROM NewSPLD.SPLD_Usage
               RESULT = IncludeSubobjectFromSubobject( NewSPLD, "SPLD_DirectionsUsage", NewSPLD, "SPLD_Usage", zPOS_AFTER );
            } 

            //:END

            //:DropView( mSPLDef2 )
            DropView( mSPLDef2 );
            RESULT = SetCursorNextEntity( SourceSLC, "S_DirectionsUsage", "S_DirectionsForUseSection" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// Marketing Section
   //:FOR EACH SourceSLC.S_MarketingSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF SourceSLC.S_MarketingSection.Selected = "Y"  // get only those selected
      if ( CompareAttributeToString( SourceSLC, "S_MarketingSection", "Selected", "Y" ) == 0 )
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

         //:END

         //:FOR EACH SourceSLC.S_MarketingUsage WITHIN SourceSLC.S_MarketingSection
         RESULT = SetCursorFirstEntity( SourceSLC, "S_MarketingUsage", "S_MarketingSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST NewSPLD.SPLD_Usage WHERE NewSPLD.SPLD_Usage.UsageType = SourceSLC.S_MarketingUsage.UsageType
            //:                                      AND NewSPLD.SPLD_Usage.Name = SourceSLC.S_MarketingUsage.Name
            RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSPLD, "SPLD_Usage", "UsageType", SourceSLC, "S_MarketingUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( NewSPLD, "SPLD_Usage", "Name", SourceSLC, "S_MarketingUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( NewSPLD, "SPLD_Usage", "" );
               } 

            } 

            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:// Check duplicates.
               //:SET CURSOR FIRST NewSPLD.SPLD_OriginalMarketingUsage WHERE NewSPLD.SPLD_OriginalMarketingUsage.UsageType = SourceSLC.S_MarketingUsage.UsageType
               //:                                                       AND NewSPLD.SPLD_OriginalMarketingUsage.Name = SourceSLC.S_MarketingUsage.Name
               RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_OriginalMarketingUsage", "" );
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSPLD, "SPLD_OriginalMarketingUsage", "UsageType", SourceSLC, "S_MarketingUsage", "UsageType" ) != 0 ||
                        CompareAttributeToAttribute( NewSPLD, "SPLD_OriginalMarketingUsage", "Name", SourceSLC, "S_MarketingUsage", "Name" ) != 0 ) )
                  { 
                     RESULT = SetCursorNextEntity( NewSPLD, "SPLD_OriginalMarketingUsage", "" );
                  } 

               } 

               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:CREATE ENTITY NewSPLD.SPLD_MarketingOrdering
                  RESULT = CreateEntity( NewSPLD, "SPLD_MarketingOrdering", zPOS_AFTER );
                  //:INCLUDE NewSPLD.SPLD_MarketingUsage         FROM NewSPLD.SPLD_Usage
                  RESULT = IncludeSubobjectFromSubobject( NewSPLD, "SPLD_MarketingUsage", NewSPLD, "SPLD_Usage", zPOS_AFTER );
                  //:CREATE ENTITY NewSPLD.SPLD_OriginalMarketingOrdering
                  RESULT = CreateEntity( NewSPLD, "SPLD_OriginalMarketingOrdering", zPOS_AFTER );
                  //:INCLUDE NewSPLD.SPLD_OriginalMarketingUsage FROM NewSPLD.SPLD_Usage
                  RESULT = IncludeSubobjectFromSubobject( NewSPLD, "SPLD_OriginalMarketingUsage", NewSPLD, "SPLD_Usage", zPOS_AFTER );
               } 

               //:END
            } 

            RESULT = SetCursorNextEntity( SourceSLC, "S_MarketingUsage", "S_MarketingSection" );
            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_MarketingSection", "" );
      //:END
   } 

   //:END

   //:// Marketing Usage
   //:FOR EACH SourceSLC.S_MarketingSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF SourceSLC.S_MarketingSection.Selected = "Y"  // get only those selected
      if ( CompareAttributeToString( SourceSLC, "S_MarketingSection", "Selected", "Y" ) == 0 )
      { 
         //:SET CURSOR FIRST NewSPLD.SPLD_MarketingSection
         //:           WHERE NewSPLD.SPLD_MarketingSection.Title = SourceSLC.S_MarketingSection.Title
         {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetStringFromAttribute( sb_szTempString_0, SourceSLC, "S_MarketingSection", "Title" );
         szTempString_0 = sb_szTempString_0.toString( );}
         RESULT = SetCursorFirstEntityByString( NewSPLD, "SPLD_MarketingSection", "Title", szTempString_0, "" );
         //:FOR EACH SourceSLC.S_MarketingUsage WITHIN SourceSLC.S_MarketingSection
         RESULT = SetCursorFirstEntity( SourceSLC, "S_MarketingUsage", "S_MarketingSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CreateViewFromView( mSPLDef2, NewSPLD )
            CreateViewFromView( mSPLDef2, NewSPLD );
            //:SET CURSOR FIRST mSPLDef2.SPLD_MarketingUsage WITHIN mSPLDef2.SPLD_MarketingSection
            //:           WHERE mSPLDef2.SPLD_MarketingUsage.UsageType = SourceSLC.S_MarketingUsage.UsageType
            //:             AND mSPLDef2.SPLD_MarketingUsage.Name = SourceSLC.S_MarketingUsage.Name
            RESULT = SetCursorFirstEntity( mSPLDef2, "SPLD_MarketingUsage", "SPLD_MarketingSection" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSPLDef2, "SPLD_MarketingUsage", "UsageType", SourceSLC, "S_MarketingUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( mSPLDef2, "SPLD_MarketingUsage", "Name", SourceSLC, "S_MarketingUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( mSPLDef2, "SPLD_MarketingUsage", "SPLD_MarketingSection" );
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:SET CURSOR FIRST NewSPLD.SPLD_Usage WHERE NewSPLD.SPLD_Usage.Name = SourceSLC.S_MarketingUsage.Name
               //:                                      AND NewSPLD.SPLD_Usage.UsageType = SourceSLC.S_MarketingUsage.UsageType
               RESULT = SetCursorFirstEntity( NewSPLD, "SPLD_Usage", "" );
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSPLD, "SPLD_Usage", "Name", SourceSLC, "S_MarketingUsage", "Name" ) != 0 ||
                        CompareAttributeToAttribute( NewSPLD, "SPLD_Usage", "UsageType", SourceSLC, "S_MarketingUsage", "UsageType" ) != 0 ) )
                  { 
                     RESULT = SetCursorNextEntity( NewSPLD, "SPLD_Usage", "" );
                  } 

               } 

               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:IssueError( NewSPLD, 0, 0, "Programming Error 2" )
                  IssueError( NewSPLD, 0, 0, "Programming Error 2" );
               } 

               //:END

               //:CREATE ENTITY NewSPLD.SPLD_MarketingOrdering
               RESULT = CreateEntity( NewSPLD, "SPLD_MarketingOrdering", zPOS_AFTER );
               //:INCLUDE NewSPLD.SPLD_MarketingUsage FROM NewSPLD.SPLD_Usage
               RESULT = IncludeSubobjectFromSubobject( NewSPLD, "SPLD_MarketingUsage", NewSPLD, "SPLD_Usage", zPOS_AFTER );
            } 

            //:END

            //:DropView( mSPLDef2 )
            DropView( mSPLDef2 );
            RESULT = SetCursorNextEntity( SourceSLC, "S_MarketingUsage", "S_MarketingSection" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( SourceSLC, "S_MarketingSection", "" );
      //:END
   } 

   //:END

   //:// HumanHazard Section
   //:FOR EACH SourceSLC.S_HumanHazardSection
   RESULT = SetCursorFirstEntity( SourceSLC, "S_HumanHazardSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// IF SourceSLC.S_HumanHazardSection.Selected = "Y"  we want all human hazards!!!
      //:   CREATE ENTITY NewSPLD.SPLD_HumanHazardSection
      RESULT = CreateEntity( NewSPLD, "SPLD_HumanHazardSection", zPOS_AFTER );
      //:   SetMatchingAttributesByName( NewSPLD, "SPLD_HumanHazardSection", SourceSLC, "S_HumanHazardSection", zSET_NULL )
      SetMatchingAttributesByName( NewSPLD, "SPLD_HumanHazardSection", SourceSLC, "S_HumanHazardSection", zSET_NULL );
      //:   INCLUDE NewSPLD.S_HumanHazardSection FROM SourceSLC.S_HumanHazardSection
      RESULT = IncludeSubobjectFromSubobject( NewSPLD, "S_HumanHazardSection", SourceSLC, "S_HumanHazardSection", zPOS_AFTER );
      RESULT = SetCursorNextEntity( SourceSLC, "S_HumanHazardSection", "" );
   } 

   //:// END
   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildSPLD_Template( VIEW mSPLDef  BASED ON LOD mSPLDef,
//:                    INTEGER TemplID )

//:   VIEW mTempl BASED ON LOD mTempl
public int 
omSPLDef_BuildSPLD_Template( View     mSPLDef,
                             int      TemplID )
{
   zVIEW    mTempl = new zVIEW( );
   //:STRING ( 32 ) szSectionType
   String   szSectionType = null;
   //:STRING ( 32 ) szType
   String   szType = null;
   //:STRING ( 1 )  szMarketingProcessedFlag
   String   szMarketingProcessedFlag = null;
   //:STRING ( 1 )  szDirectionsProcessedFlag
   String   szDirectionsProcessedFlag = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:// Build SPLD Components (subobject SPLD_Template) from a Template.

   //:ACTIVATE mTempl WHERE mTempl.Template.ID = TemplID
   omSPLDef_fnLocalBuildQual_0( mSPLDef, vTempViewVar_0, TemplID );
   RESULT = ActivateObjectInstance( mTempl, "mTempl", mSPLDef, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mTempl "mTempl"
   SetNameForView( mTempl, "mTempl", null, zLEVEL_TASK );

   //:// Delete any current structure.
   //:IF mSPLDef.SPLD_Template EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "SPLD_Template" );
   if ( lTempInteger_0 == 0 )
   { 
      //:DELETE ENTITY mSPLDef.SPLD_Template
      RESULT = DeleteEntity( mSPLDef, "SPLD_Template", zPOS_NEXT );
   } 

   //:END

   //:// Build basic Template structure, without ties to Sections.
   //:CREATE ENTITY mSPLDef.SPLD_Template
   RESULT = CreateEntity( mSPLDef, "SPLD_Template", zPOS_AFTER );
   //:SetMatchingAttributesByName( mSPLDef, "SPLD_Template", mTempl, "Template", zSET_NULL )
   SetMatchingAttributesByName( mSPLDef, "SPLD_Template", mTempl, "Template", zSET_NULL );
   //:FOR EACH mTempl.TemplatePanel
   RESULT = SetCursorFirstEntity( mTempl, "TemplatePanel", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSPLDef.SPLD_TemplatePanel
      RESULT = CreateEntity( mSPLDef, "SPLD_TemplatePanel", zPOS_AFTER );
      //:SetMatchingAttributesByName( mSPLDef, "SPLD_TemplatePanel", mTempl, "TemplatePanel", zSET_NULL )
      SetMatchingAttributesByName( mSPLDef, "SPLD_TemplatePanel", mTempl, "TemplatePanel", zSET_NULL );
      //:FOR EACH mTempl.TemplateBlock
      RESULT = SetCursorFirstEntity( mTempl, "TemplateBlock", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSPLDef.SPLD_TemplateBlock
         RESULT = CreateEntity( mSPLDef, "SPLD_TemplateBlock", zPOS_AFTER );
         //:SetMatchingAttributesByName( mSPLDef, "SPLD_TemplateBlock", mTempl, "TemplateBlock", zSET_NULL )
         SetMatchingAttributesByName( mSPLDef, "SPLD_TemplateBlock", mTempl, "TemplateBlock", zSET_NULL );
         //:FOR EACH mTempl.TemplateSection
         RESULT = SetCursorFirstEntity( mTempl, "TemplateSection", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY mSPLDef.SPLD_TemplateSection
            RESULT = CreateEntity( mSPLDef, "SPLD_TemplateSection", zPOS_AFTER );
            //:SetMatchingAttributesByName( mSPLDef, "SPLD_TemplateSection", mTempl, "TemplateSection", zSET_NULL )
            SetMatchingAttributesByName( mSPLDef, "SPLD_TemplateSection", mTempl, "TemplateSection", zSET_NULL );
            RESULT = SetCursorNextEntity( mTempl, "TemplateSection", "" );
         } 

         RESULT = SetCursorNextEntity( mTempl, "TemplateBlock", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mTempl, "TemplatePanel", "" );
      //:END
   } 

   //:END

   //:szMarketingProcessedFlag = ""
    {StringBuilder sb_szMarketingProcessedFlag;
   if ( szMarketingProcessedFlag == null )
      sb_szMarketingProcessedFlag = new StringBuilder( 32 );
   else
      sb_szMarketingProcessedFlag = new StringBuilder( szMarketingProcessedFlag );
      ZeidonStringCopy( sb_szMarketingProcessedFlag, 1, 0, "", 1, 0, 2 );
   szMarketingProcessedFlag = sb_szMarketingProcessedFlag.toString( );}
   //:szDirectionsProcessedFlag = ""
    {StringBuilder sb_szDirectionsProcessedFlag;
   if ( szDirectionsProcessedFlag == null )
      sb_szDirectionsProcessedFlag = new StringBuilder( 32 );
   else
      sb_szDirectionsProcessedFlag = new StringBuilder( szDirectionsProcessedFlag );
      ZeidonStringCopy( sb_szDirectionsProcessedFlag, 1, 0, "", 1, 0, 2 );
   szDirectionsProcessedFlag = sb_szDirectionsProcessedFlag.toString( );}

   //:// Next, create relationships to Sections for General, Ingredients and Storage Disposal.
   //:// Also, put all Directions for Use Sections under the first SPLD_TemplateSection that is for DirectionsForUse.
   //:// Note that we will not tie Marketing Sections to the Template structure.
   //:FOR EACH mSPLDef.SPLD_TemplateSection WITHIN mSPLDef.SPLD_Template
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_TemplateSection", "SPLD_Template" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:szSectionType = mSPLDef.SPLD_TemplateSection.TSectionType
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             GetVariableFromAttribute( sb_szSectionType, mi_lTempInteger_1, 'S', 33, mSPLDef, "SPLD_TemplateSection", "TSectionType", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szSectionType = sb_szSectionType.toString( );}
      //:TraceLineS( "BuildSPLD_Template: ", szSectionType )
      TraceLineS( "BuildSPLD_Template: ", szSectionType );

      //:// Precautionary, Physical Hazard and First Aid.
      //:IF szSectionType = "Precautionary"
      if ( ZeidonStringCompare( szSectionType, 1, 0, "Precautionary", 1, 0, 33 ) == 0 )
      { 
         //:SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.SectionType = "P" // P is Precautionary
         RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_GeneralSection", "SectionType", "P", "" );
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:INCLUDE mSPLDef.SPLDT_GeneralSection FROM mSPLDef.SPLD_GeneralSection
            RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLDT_GeneralSection", mSPLDef, "SPLD_GeneralSection", zPOS_AFTER );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF szSectionType = "OtherHazard" // Environmental and Physical Hazard
         if ( ZeidonStringCompare( szSectionType, 1, 0, "OtherHazard", 1, 0, 33 ) == 0 )
         { 
            //:SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.SectionType = "E" // E is Environmental/Physical Hazard
            RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_GeneralSection", "SectionType", "E", "" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:INCLUDE mSPLDef.SPLDT_GeneralSection FROM mSPLDef.SPLD_GeneralSection
               RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLDT_GeneralSection", mSPLDef, "SPLD_GeneralSection", zPOS_AFTER );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF szSectionType = "FirstAid"
            if ( ZeidonStringCompare( szSectionType, 1, 0, "FirstAid", 1, 0, 33 ) == 0 )
            { 
               //:SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.SectionType = "F" // F is First Aid
               RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_GeneralSection", "SectionType", "F", "" );
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:INCLUDE mSPLDef.SPLDT_GeneralSection FROM mSPLDef.SPLD_GeneralSection
                  RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLDT_GeneralSection", mSPLDef, "SPLD_GeneralSection", zPOS_AFTER );
               } 

               //:END

               //:ELSE
            } 
            else
            { 
               //:// Ingredients
               //:IF szSectionType = "Ingredients"
               if ( ZeidonStringCompare( szSectionType, 1, 0, "Ingredients", 1, 0, 33 ) == 0 )
               { 
                  //:FOR EACH mSPLDef.SPLD_IngredientsSection
                  RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_IngredientsSection", "" );
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //:INCLUDE mSPLDef.SPLDT_IngredientsSection FROM mSPLDef.SPLD_IngredientsSection
                     RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLDT_IngredientsSection", mSPLDef, "SPLD_IngredientsSection", zPOS_AFTER );
                     RESULT = SetCursorNextEntity( mSPLDef, "SPLD_IngredientsSection", "" );
                  } 

                  //:END

                  //:ELSE
               } 
               else
               { 
                  //:// Storage & Disposal
                  //:IF szSectionType = "Storage" OR
                  //:szSectionType = "Disposal" OR
                  //:szSectionType = "ContainerDisposal" OR
                  //:szSectionType = "StorageDisposal1" OR
                  //:szSectionType = "StorageDisposal2"
                  if ( ZeidonStringCompare( szSectionType, 1, 0, "Storage", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "Disposal", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "ContainerDisposal", 1, 0, 33 ) == 0 ||
                       ZeidonStringCompare( szSectionType, 1, 0, "StorageDisposal1", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSectionType, 1, 0, "StorageDisposal2", 1, 0, 33 ) == 0 )
                  { 

                     //:FOR EACH mSPLDef.SPLD_StorageDisposalSection
                     RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_StorageDisposalSection", "" );
                     while ( RESULT > zCURSOR_UNCHANGED )
                     { 
                        //:szType = mSPLDef.SPLD_StorageDisposalSection.SDSectionType
                        {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                        StringBuilder sb_szType;
                        if ( szType == null )
                           sb_szType = new StringBuilder( 32 );
                        else
                           sb_szType = new StringBuilder( szType );
                                                 GetVariableFromAttribute( sb_szType, mi_lTempInteger_2, 'S', 33, mSPLDef, "SPLD_StorageDisposalSection", "SDSectionType", "", 0 );
                        lTempInteger_2 = mi_lTempInteger_2.intValue( );
                        szType = sb_szType.toString( );}
                        //:IF szType = szSectionType OR szType = ""
                        if ( ZeidonStringCompare( szType, 1, 0, szSectionType, 1, 0, 33 ) == 0 || ZeidonStringCompare( szType, 1, 0, "", 1, 0, 33 ) == 0 )
                        { 
                           //:INCLUDE mSPLDef.SPLDT_StorageDisposalSection FROM mSPLDef.SPLD_StorageDisposalSection
                           RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLDT_StorageDisposalSection", mSPLDef, "SPLD_StorageDisposalSection", zPOS_AFTER );
                        } 

                        RESULT = SetCursorNextEntity( mSPLDef, "SPLD_StorageDisposalSection", "" );
                        //:END
                     } 

                     //:END
                     //:ELSE
                  } 
                  else
                  { 
                     //:// Human Hazard
                     //:IF szSectionType = "HumanHazard"
                     if ( ZeidonStringCompare( szSectionType, 1, 0, "HumanHazard", 1, 0, 33 ) == 0 )
                     { 

                        //:ELSE
                     } 
                     else
                     { 
                        //:// Marketing ... only do for first Marketing Section
                        //:IF szSectionType = "Marketing"
                        if ( ZeidonStringCompare( szSectionType, 1, 0, "Marketing", 1, 0, 33 ) == 0 )
                        { 
                           //:IF szMarketingProcessedFlag = ""
                           if ( ZeidonStringCompare( szMarketingProcessedFlag, 1, 0, "", 1, 0, 2 ) == 0 )
                           { 

                              //:FOR EACH mSPLDef.SPLD_MarketingSection
                              RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingSection", "" );
                              while ( RESULT > zCURSOR_UNCHANGED )
                              { 
                                 //:INCLUDE mSPLDef.SPLDT_MarketingSection FROM mSPLDef.SPLD_MarketingSection
                                 RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLDT_MarketingSection", mSPLDef, "SPLD_MarketingSection", zPOS_AFTER );
                                 RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingSection", "" );
                              } 

                              //:END

                              //:szMarketingProcessedFlag = "Y"
                               {StringBuilder sb_szMarketingProcessedFlag;
                              if ( szMarketingProcessedFlag == null )
                                 sb_szMarketingProcessedFlag = new StringBuilder( 32 );
                              else
                                 sb_szMarketingProcessedFlag = new StringBuilder( szMarketingProcessedFlag );
                                                            ZeidonStringCopy( sb_szMarketingProcessedFlag, 1, 0, "Y", 1, 0, 2 );
                              szMarketingProcessedFlag = sb_szMarketingProcessedFlag.toString( );}
                           } 

                           //:END
                           //:ELSE
                        } 
                        else
                        { 
                           //:// Directions for Use ... only do for first DirectionsForUse Section
                           //:IF szSectionType = "DirectionsForUse"
                           if ( ZeidonStringCompare( szSectionType, 1, 0, "DirectionsForUse", 1, 0, 33 ) == 0 )
                           { 
                              //:IF szDirectionsProcessedFlag = ""
                              if ( ZeidonStringCompare( szDirectionsProcessedFlag, 1, 0, "", 1, 0, 2 ) == 0 )
                              { 

                                 //:FOR EACH mSPLDef.SPLD_DirectionsForUseSection
                                 RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_DirectionsForUseSection", "" );
                                 while ( RESULT > zCURSOR_UNCHANGED )
                                 { 
                                    //:INCLUDE mSPLDef.SPLDT_DirectionsForUseSection FROM mSPLDef.SPLD_DirectionsForUseSection
                                    RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLDT_DirectionsForUseSection", mSPLDef, "SPLD_DirectionsForUseSection", zPOS_AFTER );
                                    RESULT = SetCursorNextEntity( mSPLDef, "SPLD_DirectionsForUseSection", "" );
                                 } 

                                 //:END

                                 //:szDirectionsProcessedFlag = "Y"
                                  {StringBuilder sb_szDirectionsProcessedFlag;
                                 if ( szDirectionsProcessedFlag == null )
                                    sb_szDirectionsProcessedFlag = new StringBuilder( 32 );
                                 else
                                    sb_szDirectionsProcessedFlag = new StringBuilder( szDirectionsProcessedFlag );
                                                                  ZeidonStringCopy( sb_szDirectionsProcessedFlag, 1, 0, "Y", 1, 0, 2 );
                                 szDirectionsProcessedFlag = sb_szDirectionsProcessedFlag.toString( );}
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

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_TemplateSection", "SPLD_Template" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildDisplayStructure( VIEW mSPLDef BASED ON LOD mSPLDef )

//:   VIEW mSPLDefHier BASED ON LOD  mSPLDef
public int 
omSPLDef_BuildDisplayStructure( View     mSPLDef )
{
   zVIEW    mSPLDefHier = new zVIEW( );
   //:STRING ( 256 ) szDisplayText
   String   szDisplayText = null;
   //:STRING ( 20 )  szPanelName
   String   szPanelName = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:STRING ( 32 )  szSectionType
   String   szSectionType = null;
   //:SHORT          lLevel
   int      lLevel = 0;
   //:SHORT          nRC
   int      nRC = 0;
   //:INTEGER        lPos
   int      lPos = 0;
   //:INTEGER        lSize
   int      lSize = 0;
   //:INTEGER        lCnt
   int      lCnt = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_3 = 0;
   String   szTempString_4 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_5 = null;
   int      lTempInteger_5 = 0;
   String   szTempString_6 = null;
   int      lTempInteger_6 = 0;
   String   szTempString_7 = null;
   int      lTempInteger_7 = 0;
   String   szTempString_8 = null;
   int      lTempInteger_8 = 0;
   String   szTempString_9 = null;
   int      lTempInteger_9 = 0;
   String   szTempString_10 = null;
   int      lTempInteger_10 = 0;


   //:// Remove any old entries.
   //:FOR EACH mSPLDef.DisplayTemplateStatement
   RESULT = SetCursorFirstEntity( mSPLDef, "DisplayTemplateStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mSPLDef.DisplayTemplateStatement NONE
      RESULT = DeleteEntity( mSPLDef, "DisplayTemplateStatement", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "DisplayTemplateStatement", "" );
   } 

   //:END

   //:// Set the Panel Count number.
   //:lCnt = 0
   lCnt = 0;
   //:FOR EACH mSPLDef.SPLD_TemplatePanel
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_TemplatePanel", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:lCnt = lCnt + 1
      lCnt = lCnt + 1;
      //:mSPLDef.SPLD_TemplatePanel.wSequentialPanelNumber = lCnt
      SetAttributeFromInteger( mSPLDef, "SPLD_TemplatePanel", "wSequentialPanelNumber", lCnt );
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_TemplatePanel", "" );
   } 

   //:END

   //:// Build the DisplayTemplateStatement structure, which is a single list of all
   //:// Panel, Group and Section entities.
   //:SET CURSOR FIRST mSPLDef.SPLD_TemplatePanel
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_TemplatePanel", "" );
   //:CreateViewFromView( mSPLDefHier, mSPLDef )
   CreateViewFromView( mSPLDefHier, mSPLDef );
   //:DefineHierarchicalCursor( mSPLDefHier, "SPLD_Template" )
   DefineHierarchicalCursor( mSPLDefHier, "SPLD_Template" );

   //:// If this is uncommented, we will skip the SPLD_Template entity.
   //:// nRC = SetCursorNextEntityHierarchical( lLevel, szEntityName, mSPLDefHier )

   //:GetEntityNameForHierarchicalCsr( lLevel, szEntityName, lPos, mSPLDefHier )
   {MutableInt mi_lPos = new MutableInt( lPos );
   StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
   MutableInt mi_lLevel = new MutableInt( lLevel );
       GetEntityNameForHierarchicalCsr( mi_lLevel, sb_szEntityName, mi_lPos, mSPLDefHier );
   lPos = mi_lPos.intValue( );
   szEntityName = sb_szEntityName.toString( );
   lLevel = mi_lLevel.intValue( );}
   //:nRC = zCURSOR_SET  // we want the first entity (the root: SPLD_Template)
   nRC = zCURSOR_SET;
   //:LOOP WHILE nRC >= zCURSOR_SET
   while ( nRC >= zCURSOR_SET )
   { 

      //:IF szEntityName = "SPLD_Template"
      if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLD_Template", 1, 0, 33 ) == 0 )
      { 
         //:// TEMPLATE
         //:CREATE ENTITY mSPLDef.DisplayTemplateStatement
         RESULT = CreateEntity( mSPLDef, "DisplayTemplateStatement", zPOS_AFTER );
         //:mSPLDef.DisplayTemplateStatement.EntityType  = "Template"
         SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "EntityType", "Template" );
         //:mSPLDef.DisplayTemplateStatement.EntityID    = mSPLDefHier.SPLD_Template.ID
         SetAttributeFromAttribute( mSPLDef, "DisplayTemplateStatement", "EntityID", mSPLDefHier, "SPLD_Template", "ID" );
         //:szDisplayText = "Template:   " + mSPLDefHier.SPLD_Template.Name
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 129, mSPLDefHier, "SPLD_Template", "Name", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szDisplayText;
         if ( szDisplayText == null )
            sb_szDisplayText = new StringBuilder( 32 );
         else
            sb_szDisplayText = new StringBuilder( szDisplayText );
                  ZeidonStringCopy( sb_szDisplayText, 1, 0, "Template:   ", 1, 0, 257 );
         szDisplayText = sb_szDisplayText.toString( );}
          {StringBuilder sb_szDisplayText;
         if ( szDisplayText == null )
            sb_szDisplayText = new StringBuilder( 32 );
         else
            sb_szDisplayText = new StringBuilder( szDisplayText );
                  ZeidonStringConcat( sb_szDisplayText, 1, 0, szTempString_0, 1, 0, 257 );
         szDisplayText = sb_szDisplayText.toString( );}
         //:szDisplayText = szDisplayText + "  CSS: "
          {StringBuilder sb_szDisplayText;
         if ( szDisplayText == null )
            sb_szDisplayText = new StringBuilder( 32 );
         else
            sb_szDisplayText = new StringBuilder( szDisplayText );
                  ZeidonStringConcat( sb_szDisplayText, 1, 0, "  CSS: ", 1, 0, 257 );
         szDisplayText = sb_szDisplayText.toString( );}
         //:szDisplayText = szDisplayText + mSPLDefHier.SPLD_Template.CSS_FileName
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 255, mSPLDefHier, "SPLD_Template", "CSS_FileName", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szDisplayText;
         if ( szDisplayText == null )
            sb_szDisplayText = new StringBuilder( 32 );
         else
            sb_szDisplayText = new StringBuilder( szDisplayText );
                  ZeidonStringConcat( sb_szDisplayText, 1, 0, szTempString_1, 1, 0, 257 );
         szDisplayText = sb_szDisplayText.toString( );}

         //:// TraceLineS( "BuildDisplayStructure (TEMPLATE): ", szDisplayText )
         //:mSPLDef.DisplayTemplateStatement.DisplayText = szDisplayText
         SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "DisplayText", szDisplayText );

         //:ELSE
      } 
      else
      { 
         //:IF szEntityName = "SPLD_TemplatePanel"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLD_TemplatePanel", 1, 0, 33 ) == 0 )
         { 
            //:// PANEL
            //:CREATE ENTITY mSPLDef.DisplayTemplateStatement
            RESULT = CreateEntity( mSPLDef, "DisplayTemplateStatement", zPOS_AFTER );
            //:mSPLDef.DisplayTemplateStatement.EntityType  = "Panel"
            SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "EntityType", "Panel" );
            //:mSPLDef.DisplayTemplateStatement.EntityID    = mSPLDefHier.SPLD_TemplatePanel.ID
            SetAttributeFromAttribute( mSPLDef, "DisplayTemplateStatement", "EntityID", mSPLDefHier, "SPLD_TemplatePanel", "ID" );
            //:szDisplayText = "Panel:   " + mSPLDefHier.SPLD_TemplatePanel.dPanelName
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_2, 'S', 255, mSPLDefHier, "SPLD_TemplatePanel", "dPanelName", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szDisplayText;
            if ( szDisplayText == null )
               sb_szDisplayText = new StringBuilder( 32 );
            else
               sb_szDisplayText = new StringBuilder( szDisplayText );
                        ZeidonStringCopy( sb_szDisplayText, 1, 0, "Panel:   ", 1, 0, 257 );
            szDisplayText = sb_szDisplayText.toString( );}
             {StringBuilder sb_szDisplayText;
            if ( szDisplayText == null )
               sb_szDisplayText = new StringBuilder( 32 );
            else
               sb_szDisplayText = new StringBuilder( szDisplayText );
                        ZeidonStringConcat( sb_szDisplayText, 1, 0, szTempString_2, 1, 0, 257 );
            szDisplayText = sb_szDisplayText.toString( );}
            //:lSize = mSPLDefHier.SPLD_TemplatePanel.SizeY
            {MutableInt mi_lSize = new MutableInt( lSize );
                         GetIntegerFromAttribute( mi_lSize, mSPLDefHier, "SPLD_TemplatePanel", "SizeY" );
            lSize = mi_lSize.intValue( );}
            //:IF lSize != 0
            if ( lSize != 0 )
            { 
               //:szDisplayText = szDisplayText + "  Height: "
                {StringBuilder sb_szDisplayText;
               if ( szDisplayText == null )
                  sb_szDisplayText = new StringBuilder( 32 );
               else
                  sb_szDisplayText = new StringBuilder( szDisplayText );
                              ZeidonStringConcat( sb_szDisplayText, 1, 0, "  Height: ", 1, 0, 257 );
               szDisplayText = sb_szDisplayText.toString( );}
               //:szDisplayText = szDisplayText + mSPLDefHier.SPLD_TemplatePanel.SizeY
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szTempString_3;
               if ( szTempString_3 == null )
                  sb_szTempString_3 = new StringBuilder( 32 );
               else
                  sb_szTempString_3 = new StringBuilder( szTempString_3 );
                               GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_3, 'S', 11, mSPLDefHier, "SPLD_TemplatePanel", "SizeY", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szTempString_3 = sb_szTempString_3.toString( );}
                {StringBuilder sb_szDisplayText;
               if ( szDisplayText == null )
                  sb_szDisplayText = new StringBuilder( 32 );
               else
                  sb_szDisplayText = new StringBuilder( szDisplayText );
                              ZeidonStringConcat( sb_szDisplayText, 1, 0, szTempString_3, 1, 0, 257 );
               szDisplayText = sb_szDisplayText.toString( );}
            } 

            //:END

            //:lSize = mSPLDefHier.SPLD_TemplatePanel.SizeX
            {MutableInt mi_lSize = new MutableInt( lSize );
                         GetIntegerFromAttribute( mi_lSize, mSPLDefHier, "SPLD_TemplatePanel", "SizeX" );
            lSize = mi_lSize.intValue( );}
            //:IF lSize != 0
            if ( lSize != 0 )
            { 
               //:szDisplayText = szDisplayText + "  Width: "
                {StringBuilder sb_szDisplayText;
               if ( szDisplayText == null )
                  sb_szDisplayText = new StringBuilder( 32 );
               else
                  sb_szDisplayText = new StringBuilder( szDisplayText );
                              ZeidonStringConcat( sb_szDisplayText, 1, 0, "  Width: ", 1, 0, 257 );
               szDisplayText = sb_szDisplayText.toString( );}
               //:szDisplayText = szDisplayText + mSPLDefHier.SPLD_TemplatePanel.SizeX
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szTempString_4;
               if ( szTempString_4 == null )
                  sb_szTempString_4 = new StringBuilder( 32 );
               else
                  sb_szTempString_4 = new StringBuilder( szTempString_4 );
                               GetVariableFromAttribute( sb_szTempString_4, mi_lTempInteger_4, 'S', 11, mSPLDefHier, "SPLD_TemplatePanel", "SizeX", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szTempString_4 = sb_szTempString_4.toString( );}
                {StringBuilder sb_szDisplayText;
               if ( szDisplayText == null )
                  sb_szDisplayText = new StringBuilder( 32 );
               else
                  sb_szDisplayText = new StringBuilder( szDisplayText );
                              ZeidonStringConcat( sb_szDisplayText, 1, 0, szTempString_4, 1, 0, 257 );
               szDisplayText = sb_szDisplayText.toString( );}
            } 

            //:END

            //:// TraceLineS( "BuildDisplayStructure (PANEL): ", szDisplayText )
            //:mSPLDef.DisplayTemplateStatement.DisplayText = szDisplayText
            SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "DisplayText", szDisplayText );

            //:ELSE
         } 
         else
         { 
            //:IF szEntityName = "SPLD_TemplateBlock"
            if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLD_TemplateBlock", 1, 0, 33 ) == 0 )
            { 
               //:// GROUP
               //:CREATE ENTITY mSPLDef.DisplayTemplateStatement
               RESULT = CreateEntity( mSPLDef, "DisplayTemplateStatement", zPOS_AFTER );
               //:mSPLDef.DisplayTemplateStatement.EntityType  = "Group"
               SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "EntityType", "Group" );
               //:mSPLDef.DisplayTemplateStatement.EntityID    = mSPLDefHier.SPLD_TemplateBlock.ID
               SetAttributeFromAttribute( mSPLDef, "DisplayTemplateStatement", "EntityID", mSPLDefHier, "SPLD_TemplateBlock", "ID" );
               //:szDisplayText = "...Group:   " + mSPLDefHier.SPLD_TemplateBlock.Name
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szTempString_5;
               if ( szTempString_5 == null )
                  sb_szTempString_5 = new StringBuilder( 32 );
               else
                  sb_szTempString_5 = new StringBuilder( szTempString_5 );
                               GetVariableFromAttribute( sb_szTempString_5, mi_lTempInteger_5, 'S', 129, mSPLDefHier, "SPLD_TemplateBlock", "Name", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szTempString_5 = sb_szTempString_5.toString( );}
                {StringBuilder sb_szDisplayText;
               if ( szDisplayText == null )
                  sb_szDisplayText = new StringBuilder( 32 );
               else
                  sb_szDisplayText = new StringBuilder( szDisplayText );
                              ZeidonStringCopy( sb_szDisplayText, 1, 0, "...Group:   ", 1, 0, 257 );
               szDisplayText = sb_szDisplayText.toString( );}
                {StringBuilder sb_szDisplayText;
               if ( szDisplayText == null )
                  sb_szDisplayText = new StringBuilder( 32 );
               else
                  sb_szDisplayText = new StringBuilder( szDisplayText );
                              ZeidonStringConcat( sb_szDisplayText, 1, 0, szTempString_5, 1, 0, 257 );
               szDisplayText = sb_szDisplayText.toString( );}

               //:IF mSPLDefHier.SPLD_TemplateBlock.ShowBox = "Y"
               if ( CompareAttributeToString( mSPLDefHier, "SPLD_TemplateBlock", "ShowBox", "Y" ) == 0 )
               { 
                  //:szDisplayText = szDisplayText + "  Show Box"
                   {StringBuilder sb_szDisplayText;
                  if ( szDisplayText == null )
                     sb_szDisplayText = new StringBuilder( 32 );
                  else
                     sb_szDisplayText = new StringBuilder( szDisplayText );
                                    ZeidonStringConcat( sb_szDisplayText, 1, 0, "  Show Box", 1, 0, 257 );
                  szDisplayText = sb_szDisplayText.toString( );}
               } 

               //:END

               //:IF mSPLDefHier.SPLD_TemplateBlock.ExternalGraphicFileName != ""
               if ( CompareAttributeToString( mSPLDefHier, "SPLD_TemplateBlock", "ExternalGraphicFileName", "" ) != 0 )
               { 
                  //:szDisplayText = szDisplayText + "  Graphic: "
                   {StringBuilder sb_szDisplayText;
                  if ( szDisplayText == null )
                     sb_szDisplayText = new StringBuilder( 32 );
                  else
                     sb_szDisplayText = new StringBuilder( szDisplayText );
                                    ZeidonStringConcat( sb_szDisplayText, 1, 0, "  Graphic: ", 1, 0, 257 );
                  szDisplayText = sb_szDisplayText.toString( );}
                  //:szDisplayText = szDisplayText + mSPLDefHier.SPLD_TemplateBlock.ExternalGraphicFileName
                  {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                  StringBuilder sb_szTempString_6;
                  if ( szTempString_6 == null )
                     sb_szTempString_6 = new StringBuilder( 32 );
                  else
                     sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                     GetVariableFromAttribute( sb_szTempString_6, mi_lTempInteger_6, 'S', 255, mSPLDefHier, "SPLD_TemplateBlock", "ExternalGraphicFileName", "", 0 );
                  lTempInteger_6 = mi_lTempInteger_6.intValue( );
                  szTempString_6 = sb_szTempString_6.toString( );}
                   {StringBuilder sb_szDisplayText;
                  if ( szDisplayText == null )
                     sb_szDisplayText = new StringBuilder( 32 );
                  else
                     sb_szDisplayText = new StringBuilder( szDisplayText );
                                    ZeidonStringConcat( sb_szDisplayText, 1, 0, szTempString_6, 1, 0, 257 );
                  szDisplayText = sb_szDisplayText.toString( );}
               } 

               //:END

               //:// TraceLineS( "BuildDisplayStructure (GROUP): ", szDisplayText )
               //:mSPLDef.DisplayTemplateStatement.DisplayText = szDisplayText
               SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "DisplayText", szDisplayText );

               //:ELSE
            } 
            else
            { 
               //:IF szEntityName = "SPLD_TemplateSection"
               if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLD_TemplateSection", 1, 0, 33 ) == 0 )
               { 
                  //:// SECTION
                  //:CREATE ENTITY mSPLDef.DisplayTemplateStatement
                  RESULT = CreateEntity( mSPLDef, "DisplayTemplateStatement", zPOS_AFTER );
                  //:mSPLDef.DisplayTemplateStatement.EntityType = "Section"
                  SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "EntityType", "Section" );
                  //:GetStringFromAttributeByContext( szSectionType, mSPLDefHier, "SPLD_TemplateSection", "TSectionType", "", 32 )
                  {StringBuilder sb_szSectionType;
                  if ( szSectionType == null )
                     sb_szSectionType = new StringBuilder( 32 );
                  else
                     sb_szSectionType = new StringBuilder( szSectionType );
                                     GetStringFromAttributeByContext( sb_szSectionType, mSPLDefHier, "SPLD_TemplateSection", "TSectionType", "", 32 );
                  szSectionType = sb_szSectionType.toString( );}
                  //:mSPLDef.DisplayTemplateStatement.EntityID    = mSPLDefHier.SPLD_TemplateSection.ID
                  SetAttributeFromAttribute( mSPLDef, "DisplayTemplateStatement", "EntityID", mSPLDefHier, "SPLD_TemplateSection", "ID" );
                  //:// If the EntityID is null, the SPLD_TemplateSection entity was created this transaction and we must
                  //:// use the Temporary ID, SPLD_TemplateSection.wTempID.
                  //:IF mSPLDef.DisplayTemplateStatement.EntityID = ""
                  if ( CompareAttributeToString( mSPLDef, "DisplayTemplateStatement", "EntityID", "" ) == 0 )
                  { 
                     //:mSPLDef.DisplayTemplateStatement.wTempID = mSPLDefHier.SPLD_TemplateSection.wTempID
                     SetAttributeFromAttribute( mSPLDef, "DisplayTemplateStatement", "wTempID", mSPLDefHier, "SPLD_TemplateSection", "wTempID" );
                  } 

                  //:END

                  //:szDisplayText = "......Section:      " + szSectionType
                   {StringBuilder sb_szDisplayText;
                  if ( szDisplayText == null )
                     sb_szDisplayText = new StringBuilder( 32 );
                  else
                     sb_szDisplayText = new StringBuilder( szDisplayText );
                                    ZeidonStringCopy( sb_szDisplayText, 1, 0, "......Section:      ", 1, 0, 257 );
                  szDisplayText = sb_szDisplayText.toString( );}
                   {StringBuilder sb_szDisplayText;
                  if ( szDisplayText == null )
                     sb_szDisplayText = new StringBuilder( 32 );
                  else
                     sb_szDisplayText = new StringBuilder( szDisplayText );
                                    ZeidonStringConcat( sb_szDisplayText, 1, 0, szSectionType, 1, 0, 257 );
                  szDisplayText = sb_szDisplayText.toString( );}
                  //:// TraceLineS( "BuildDisplayStructure (SECTION): ", szDisplayText )
                  //:mSPLDef.DisplayTemplateStatement.DisplayText = szDisplayText
                  SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "DisplayText", szDisplayText );

                  //:ELSE
               } 
               else
               { 
                  //:IF szEntityName = "SPLDT_GeneralSection"
                  if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLDT_GeneralSection", 1, 0, 33 ) == 0 )
                  { 
                     //:// GENERAL SECTION
                     //:CREATE ENTITY mSPLDef.DisplayTemplateStatement
                     RESULT = CreateEntity( mSPLDef, "DisplayTemplateStatement", zPOS_AFTER );
                     //:mSPLDef.DisplayTemplateStatement.EntityType  = "GeneralSection"
                     SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "EntityType", "GeneralSection" );
                     //:mSPLDef.DisplayTemplateStatement.EntityID    = mSPLDefHier.SPLDT_GeneralSection.ID
                     SetAttributeFromAttribute( mSPLDef, "DisplayTemplateStatement", "EntityID", mSPLDefHier, "SPLDT_GeneralSection", "ID" );
                     //:szDisplayText = "..........." + mSPLDefHier.SPLDT_GeneralSection.Title
                     {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                     StringBuilder sb_szTempString_7;
                     if ( szTempString_7 == null )
                        sb_szTempString_7 = new StringBuilder( 32 );
                     else
                        sb_szTempString_7 = new StringBuilder( szTempString_7 );
                                           GetVariableFromAttribute( sb_szTempString_7, mi_lTempInteger_7, 'S', 255, mSPLDefHier, "SPLDT_GeneralSection", "Title", "", 0 );
                     lTempInteger_7 = mi_lTempInteger_7.intValue( );
                     szTempString_7 = sb_szTempString_7.toString( );}
                      {StringBuilder sb_szDisplayText;
                     if ( szDisplayText == null )
                        sb_szDisplayText = new StringBuilder( 32 );
                     else
                        sb_szDisplayText = new StringBuilder( szDisplayText );
                                          ZeidonStringCopy( sb_szDisplayText, 1, 0, "...........", 1, 0, 257 );
                     szDisplayText = sb_szDisplayText.toString( );}
                      {StringBuilder sb_szDisplayText;
                     if ( szDisplayText == null )
                        sb_szDisplayText = new StringBuilder( 32 );
                     else
                        sb_szDisplayText = new StringBuilder( szDisplayText );
                                          ZeidonStringConcat( sb_szDisplayText, 1, 0, szTempString_7, 1, 0, 257 );
                     szDisplayText = sb_szDisplayText.toString( );}
                     //:// TraceLineS( "BuildDisplayStructure (GENERAL SECTION): ", szDisplayText )
                     //:mSPLDef.DisplayTemplateStatement.DisplayText = szDisplayText
                     SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "DisplayText", szDisplayText );

                     //:ELSE
                  } 
                  else
                  { 
                     //:IF szEntityName = "SPLDT_IngredientsSection"
                     if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLDT_IngredientsSection", 1, 0, 33 ) == 0 )
                     { 
                        //:// INGREDIENTS SECTION
                        //:CREATE ENTITY mSPLDef.DisplayTemplateStatement
                        RESULT = CreateEntity( mSPLDef, "DisplayTemplateStatement", zPOS_AFTER );
                        //:mSPLDef.DisplayTemplateStatement.EntityType = "IngredientsSection"
                        SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "EntityType", "IngredientsSection" );
                        //:mSPLDef.DisplayTemplateStatement.EntityID    = mSPLDefHier.SPLDT_IngredientsSection.ID
                        SetAttributeFromAttribute( mSPLDef, "DisplayTemplateStatement", "EntityID", mSPLDefHier, "SPLDT_IngredientsSection", "ID" );
                        //:szDisplayText = "..........Ingredients"
                         {StringBuilder sb_szDisplayText;
                        if ( szDisplayText == null )
                           sb_szDisplayText = new StringBuilder( 32 );
                        else
                           sb_szDisplayText = new StringBuilder( szDisplayText );
                                                ZeidonStringCopy( sb_szDisplayText, 1, 0, "..........Ingredients", 1, 0, 257 );
                        szDisplayText = sb_szDisplayText.toString( );}
                        //:// TraceLineS( "BuildDisplayStructure (INGREDIENTS SECTION): ", szDisplayText )
                        //:mSPLDef.DisplayTemplateStatement.DisplayText = szDisplayText
                        SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "DisplayText", szDisplayText );

                        //:ELSE
                     } 
                     else
                     { 
                        //:IF szEntityName = "SPLDT_StorageDisposalSection"
                        if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLDT_StorageDisposalSection", 1, 0, 33 ) == 0 )
                        { 
                           //:// STORAGE & DISPOSAL SECTION
                           //:CREATE ENTITY mSPLDef.DisplayTemplateStatement
                           RESULT = CreateEntity( mSPLDef, "DisplayTemplateStatement", zPOS_AFTER );
                           //:mSPLDef.DisplayTemplateStatement.EntityType  = "StorageDisposalSection"
                           SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "EntityType", "StorageDisposalSection" );
                           //:mSPLDef.DisplayTemplateStatement.EntityID    = mSPLDefHier.SPLDT_StorageDisposalSection.ID
                           SetAttributeFromAttribute( mSPLDef, "DisplayTemplateStatement", "EntityID", mSPLDefHier, "SPLDT_StorageDisposalSection", "ID" );
                           //:szDisplayText = ".........." + mSPLDefHier.SPLDT_StorageDisposalSection.Title
                           {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                           StringBuilder sb_szTempString_8;
                           if ( szTempString_8 == null )
                              sb_szTempString_8 = new StringBuilder( 32 );
                           else
                              sb_szTempString_8 = new StringBuilder( szTempString_8 );
                                                       GetVariableFromAttribute( sb_szTempString_8, mi_lTempInteger_8, 'S', 255, mSPLDefHier, "SPLDT_StorageDisposalSection", "Title", "", 0 );
                           lTempInteger_8 = mi_lTempInteger_8.intValue( );
                           szTempString_8 = sb_szTempString_8.toString( );}
                            {StringBuilder sb_szDisplayText;
                           if ( szDisplayText == null )
                              sb_szDisplayText = new StringBuilder( 32 );
                           else
                              sb_szDisplayText = new StringBuilder( szDisplayText );
                                                      ZeidonStringCopy( sb_szDisplayText, 1, 0, "..........", 1, 0, 257 );
                           szDisplayText = sb_szDisplayText.toString( );}
                            {StringBuilder sb_szDisplayText;
                           if ( szDisplayText == null )
                              sb_szDisplayText = new StringBuilder( 32 );
                           else
                              sb_szDisplayText = new StringBuilder( szDisplayText );
                                                      ZeidonStringConcat( sb_szDisplayText, 1, 0, szTempString_8, 1, 0, 257 );
                           szDisplayText = sb_szDisplayText.toString( );}
                           //:// TraceLineS( "BuildDisplayStructure (STORAGE & DISPOSAL SECTION): ", szDisplayText )
                           //:mSPLDef.DisplayTemplateStatement.DisplayText = szDisplayText
                           SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "DisplayText", szDisplayText );

                           //:ELSE
                        } 
                        else
                        { 
                           //:IF szEntityName = "SPLDT_DirectionsForUseSection"
                           if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLDT_DirectionsForUseSection", 1, 0, 33 ) == 0 )
                           { 
                              //:// DIRECTIONS FOR USE SECTION
                              //:CREATE ENTITY mSPLDef.DisplayTemplateStatement
                              RESULT = CreateEntity( mSPLDef, "DisplayTemplateStatement", zPOS_AFTER );
                              //:mSPLDef.DisplayTemplateStatement.EntityType  = "DirectionsForUseSection"
                              SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "EntityType", "DirectionsForUseSection" );
                              //:mSPLDef.DisplayTemplateStatement.EntityID    = mSPLDefHier.SPLDT_DirectionsForUseSection.ID
                              SetAttributeFromAttribute( mSPLDef, "DisplayTemplateStatement", "EntityID", mSPLDefHier, "SPLDT_DirectionsForUseSection", "ID" );
                              //:szDisplayText = ".........." + mSPLDefHier.SPLDT_DirectionsForUseSection.Title
                              {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
                              StringBuilder sb_szTempString_9;
                              if ( szTempString_9 == null )
                                 sb_szTempString_9 = new StringBuilder( 32 );
                              else
                                 sb_szTempString_9 = new StringBuilder( szTempString_9 );
                                                             GetVariableFromAttribute( sb_szTempString_9, mi_lTempInteger_9, 'S', 255, mSPLDefHier, "SPLDT_DirectionsForUseSection", "Title", "", 0 );
                              lTempInteger_9 = mi_lTempInteger_9.intValue( );
                              szTempString_9 = sb_szTempString_9.toString( );}
                               {StringBuilder sb_szDisplayText;
                              if ( szDisplayText == null )
                                 sb_szDisplayText = new StringBuilder( 32 );
                              else
                                 sb_szDisplayText = new StringBuilder( szDisplayText );
                                                            ZeidonStringCopy( sb_szDisplayText, 1, 0, "..........", 1, 0, 257 );
                              szDisplayText = sb_szDisplayText.toString( );}
                               {StringBuilder sb_szDisplayText;
                              if ( szDisplayText == null )
                                 sb_szDisplayText = new StringBuilder( 32 );
                              else
                                 sb_szDisplayText = new StringBuilder( szDisplayText );
                                                            ZeidonStringConcat( sb_szDisplayText, 1, 0, szTempString_9, 1, 0, 257 );
                              szDisplayText = sb_szDisplayText.toString( );}
                              //:// TraceLineS( "BuildDisplayStructure (DIRECTIONS FOR USE SECTION): ", szDisplayText )
                              //:mSPLDef.DisplayTemplateStatement.DisplayText = szDisplayText
                              SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "DisplayText", szDisplayText );

                              //:ELSE
                           } 
                           else
                           { 
                              //:IF szEntityName = "SPLDT_MarketingSection"
                              if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLDT_MarketingSection", 1, 0, 33 ) == 0 )
                              { 
                                 //:// MARKETING SECTION
                                 //:CREATE ENTITY mSPLDef.DisplayTemplateStatement
                                 RESULT = CreateEntity( mSPLDef, "DisplayTemplateStatement", zPOS_AFTER );
                                 //:mSPLDef.DisplayTemplateStatement.EntityType  = "MarketingSection"
                                 SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "EntityType", "MarketingSection" );
                                 //:mSPLDef.DisplayTemplateStatement.EntityID    = mSPLDefHier.SPLDT_MarketingSection.ID
                                 SetAttributeFromAttribute( mSPLDef, "DisplayTemplateStatement", "EntityID", mSPLDefHier, "SPLDT_MarketingSection", "ID" );
                                 //:szDisplayText = ".........." + mSPLDefHier.SPLDT_MarketingSection.Title
                                 {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
                                 StringBuilder sb_szTempString_10;
                                 if ( szTempString_10 == null )
                                    sb_szTempString_10 = new StringBuilder( 32 );
                                 else
                                    sb_szTempString_10 = new StringBuilder( szTempString_10 );
                                                                   GetVariableFromAttribute( sb_szTempString_10, mi_lTempInteger_10, 'S', 255, mSPLDefHier, "SPLDT_MarketingSection", "Title", "", 0 );
                                 lTempInteger_10 = mi_lTempInteger_10.intValue( );
                                 szTempString_10 = sb_szTempString_10.toString( );}
                                  {StringBuilder sb_szDisplayText;
                                 if ( szDisplayText == null )
                                    sb_szDisplayText = new StringBuilder( 32 );
                                 else
                                    sb_szDisplayText = new StringBuilder( szDisplayText );
                                                                  ZeidonStringCopy( sb_szDisplayText, 1, 0, "..........", 1, 0, 257 );
                                 szDisplayText = sb_szDisplayText.toString( );}
                                  {StringBuilder sb_szDisplayText;
                                 if ( szDisplayText == null )
                                    sb_szDisplayText = new StringBuilder( 32 );
                                 else
                                    sb_szDisplayText = new StringBuilder( szDisplayText );
                                                                  ZeidonStringConcat( sb_szDisplayText, 1, 0, szTempString_10, 1, 0, 257 );
                                 szDisplayText = sb_szDisplayText.toString( );}
                                 //:// TraceLineS( "BuildDisplayStructure (MARKETING SECTION): ", szDisplayText )
                                 //:mSPLDef.DisplayTemplateStatement.DisplayText = szDisplayText
                                 SetAttributeFromString( mSPLDef, "DisplayTemplateStatement", "DisplayText", szDisplayText );
                                 //:ELSE
                              } 
                              else
                              { 
                                 //:IF szEntityName != "DisplayTemplateStatement"
                                 if ( ZeidonStringCompare( szEntityName, 1, 0, "DisplayTemplateStatement", 1, 0, 33 ) != 0 )
                                 { 
                                 } 

                                 //:// TraceLineS( "BuildDisplayStructure (UNKNOWN SECTION): ", szEntityName )
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

      //:nRC = SetCursorNextEntityHierarchical( lLevel, szEntityName, mSPLDefHier )
      {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
      MutableInt mi_lLevel = new MutableInt( lLevel );
             nRC = SetCursorNextEntityHierarchical( mi_lLevel, sb_szEntityName, mSPLDefHier );
      szEntityName = sb_szEntityName.toString( );
      lLevel = mi_lLevel.intValue( );}
   } 

   //:END
   //:DropHierarchicalCursor( mSPLDefHier )
   DropHierarchicalCursor( mSPLDefHier );
   //:DropView( mSPLDefHier )
   DropView( mSPLDefHier );
   //:SET CURSOR FIRST mSPLDef.SPLD_TemplatePanel
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_TemplatePanel", "" );
   //:SET CURSOR FIRST mSPLDef.DisplayTemplateStatement
   RESULT = SetCursorFirstEntity( mSPLDef, "DisplayTemplateStatement", "" );
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
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Currently the Panel Name is just the "panel" + Panel Number.
         //:szPanelName = "panel"
          {StringBuilder sb_szPanelName;
         if ( szPanelName == null )
            sb_szPanelName = new StringBuilder( 32 );
         else
            sb_szPanelName = new StringBuilder( szPanelName );
                  ZeidonStringCopy( sb_szPanelName, 1, 0, "panel", 1, 0, 17 );
         szPanelName = sb_szPanelName.toString( );}
         //:szPanelName = szPanelName + mSPLDef.SPLD_TemplatePanel.wSequentialPanelNumber
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "wSequentialPanelNumber", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szPanelName;
         if ( szPanelName == null )
            sb_szPanelName = new StringBuilder( 32 );
         else
            sb_szPanelName = new StringBuilder( szPanelName );
                  ZeidonStringConcat( sb_szPanelName, 1, 0, szTempString_0, 1, 0, 17 );
         szPanelName = sb_szPanelName.toString( );}

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef,
         //:               InternalEntityStructure, InternalAttribStructure, szPanelName )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szPanelName );
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
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 129, mSPLDef, "PrimaryRegistrant", "EPA_CompanyNumber", "", 0 );
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
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 129, mSPLDef, "MasterProduct", "Number", "", 0 );
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
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 129, mSPLDef, "SubregProduct", "Number", "", 0 );
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
            //:CREATE ENTITY NewSPLD.SPLD_DirectionsOrdering
            RESULT = CreateEntity( NewSPLD, "SPLD_DirectionsOrdering", zPOS_AFTER );
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
               //:CREATE ENTITY NewSPLD.SPLD_MarketingOrdering
               RESULT = CreateEntity( NewSPLD, "SPLD_MarketingOrdering", zPOS_AFTER );
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


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

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
            //:nPosStart  = zSearchSubString( szString, "{{Precautionary Position}}", "f", 0 )
            nPosStart = zSearchSubString( szString, "{{Precautionary Position}}", "f", 0 );
            //:IF nPosStart >= 0
            if ( nPosStart >= 0 )
            { 

               //:nPosEnd = nPosStart + 26 // length of "{{Precautionary Position}}"
               nPosEnd = nPosStart + 26;
               //:szReplaceString = ""
                {StringBuilder sb_szReplaceString;
               if ( szReplaceString == null )
                  sb_szReplaceString = new StringBuilder( 32 );
               else
                  sb_szReplaceString = new StringBuilder( szReplaceString );
                              ZeidonStringCopy( sb_szReplaceString, 1, 0, "", 1, 0, 257 );
               szReplaceString = sb_szReplaceString.toString( );}
               //:szLocation = mSPLDef.SPLD_HumanHazardSection.Location1
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_2, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "Location1", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + "[" + szLocation + "]"
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "[", 1, 0, 257 );
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
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "]", 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:szLocation = mSPLDef.SPLD_HumanHazardSection.Location2
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_3, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "Location2", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + "[" + szLocation + "]"
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "[", 1, 0, 257 );
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
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "]", 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:szLocation = mSPLDef.SPLD_HumanHazardSection.Location3
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_4, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "Location3", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + "[" + szLocation + "]"
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "[", 1, 0, 257 );
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
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "]", 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:szLocation = mSPLDef.SPLD_HumanHazardSection.Location4
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_5, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "Location4", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + "[" + szLocation + "]"
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "[", 1, 0, 257 );
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
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "]", 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:szLocation = mSPLDef.SPLD_HumanHazardSection.Location5
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_6, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "Location5", "", 0 );
               lTempInteger_6 = mi_lTempInteger_6.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + "[" + szLocation + "]"
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "[", 1, 0, 257 );
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
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "]", 1, 0, 257 );
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
         //:StoreStringInRecord( mSPLDef,
         //:                  InternalEntityStructure, InternalAttribStructure, szString )
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


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

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

            //:IF mSPLDef.SPLD_Template  EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mSPLDef, "SPLD_Template" );
            if ( lTempInteger_2 == 0 )
            { 
               //:lLocation = mSPLDef.SPLD_Template.HazardPanel
               {MutableInt mi_lLocation = new MutableInt( lLocation );
                               GetIntegerFromAttribute( mi_lLocation, mSPLDef, "SPLD_Template", "HazardPanel" );
               lLocation = mi_lLocation.intValue( );}
               //:ELSE
            } 
            else
            { 
               //:lLocation = 0
               lLocation = 0;
            } 

            //:END

            //:nPosStart  = zSearchSubString( szString, "{{Precautionary Position}}", "f", 0 )
            nPosStart = zSearchSubString( szString, "{{Precautionary Position}}", "f", 0 );
            //:IF nPosStart >= 0
            if ( nPosStart >= 0 )
            { 

               //:nPosEnd = nPosStart + 26 // length of "{{Precautionary Position}}"
               nPosEnd = nPosStart + 26;
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
                  //:szLocation = mSPLDef.SPLD_HumanHazardSection.Location1
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szLocation;
                  if ( szLocation == null )
                     sb_szLocation = new StringBuilder( 32 );
                  else
                     sb_szLocation = new StringBuilder( szLocation );
                                     GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_3, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "Location1", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szLocation = sb_szLocation.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:IF lLocation = 2
                  if ( lLocation == 2 )
                  { 
                     //:szLocation = mSPLDef.SPLD_HumanHazardSection.Location2
                     {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                     StringBuilder sb_szLocation;
                     if ( szLocation == null )
                        sb_szLocation = new StringBuilder( 32 );
                     else
                        sb_szLocation = new StringBuilder( szLocation );
                                           GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_4, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "Location2", "", 0 );
                     lTempInteger_4 = mi_lTempInteger_4.intValue( );
                     szLocation = sb_szLocation.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF lLocation = 3
                     if ( lLocation == 3 )
                     { 
                        //:szLocation = mSPLDef.SPLD_HumanHazardSection.Location3
                        {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                        StringBuilder sb_szLocation;
                        if ( szLocation == null )
                           sb_szLocation = new StringBuilder( 32 );
                        else
                           sb_szLocation = new StringBuilder( szLocation );
                                                 GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_5, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "Location3", "", 0 );
                        lTempInteger_5 = mi_lTempInteger_5.intValue( );
                        szLocation = sb_szLocation.toString( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:IF lLocation = 4
                        if ( lLocation == 4 )
                        { 
                           //:szLocation = mSPLDef.SPLD_HumanHazardSection.Location4
                           {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                           StringBuilder sb_szLocation;
                           if ( szLocation == null )
                              sb_szLocation = new StringBuilder( 32 );
                           else
                              sb_szLocation = new StringBuilder( szLocation );
                                                       GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_6, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "Location4", "", 0 );
                           lTempInteger_6 = mi_lTempInteger_6.intValue( );
                           szLocation = sb_szLocation.toString( );}
                           //:ELSE
                        } 
                        else
                        { 
                           //:IF lLocation = 5
                           if ( lLocation == 5 )
                           { 
                              //:szLocation = mSPLDef.SPLD_HumanHazardSection.Location5
                              {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                              StringBuilder sb_szLocation;
                              if ( szLocation == null )
                                 sb_szLocation = new StringBuilder( 32 );
                              else
                                 sb_szLocation = new StringBuilder( szLocation );
                                                             GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_7, 'S', 257, mSPLDef, "SPLD_HumanHazardSection", "Location5", "", 0 );
                              lTempInteger_7 = mi_lTempInteger_7.intValue( );
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
         //:StoreStringInRecord( mSPLDef,
         //:                  InternalEntityStructure, InternalAttribStructure, szString )
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


   //:LOCAL OPERATION
   //:GenerateHTML_Title( VIEW mSPLDef BASED ON LOD mSPLDef,
   //:                 INTEGER lFile,
   //:                 STRING ( 32 )    szEntity,
   //:                 STRING ( 512 )   szCombinedTitle,
   //:                 STRING ( 32000 ) szWriteBuffer )

   //:STRING ( 256 )    szTemp
private void 
omSPLDef_GenerateHTML_Title( View     mSPLDef,
                             int      lFile,
                             String   szEntity,
                             String   szCombinedTitle,
                             String   szWriteBuffer )
{
   String   szTemp = null;
   //:STRING ( 256 )    szTitle
   String   szTitle = null;
   //:STRING ( 256 )    szSubtitle
   String   szSubtitle = null;
   //:STRING ( 2 )      szTitlePosition
   String   szTitlePosition = null;
   //:STRING ( 10 )     szTitleLeading
   String   szTitleLeading = null;
   //:STRING ( 1 )      szBoldItalic
   String   szBoldItalic = null;
   //:STRING ( 32 )     szClass
   String   szClass = null;
   //:STRING ( 32 )     szLineHeight
   String   szLineHeight = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;


   //:szCombinedTitle = ""  // don't pass anything back to display
    {StringBuilder sb_szCombinedTitle;
   if ( szCombinedTitle == null )
      sb_szCombinedTitle = new StringBuilder( 32 );
   else
      sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
      ZeidonStringCopy( sb_szCombinedTitle, 1, 0, "", 1, 0, 513 );
   szCombinedTitle = sb_szCombinedTitle.toString( );}

   //:// Process Title if it exists.  We will either generate it as its own line, or prepend it to the Statement Line.
   //:// Also, generate headers for any bold/italic requests.
   //:GetStringFromAttributeByContext( szTitle, mSPLDef, szEntity, "Title", "", 256 )
   {StringBuilder sb_szTitle;
   if ( szTitle == null )
      sb_szTitle = new StringBuilder( 32 );
   else
      sb_szTitle = new StringBuilder( szTitle );
       GetStringFromAttributeByContext( sb_szTitle, mSPLDef, szEntity, "Title", "", 256 );
   szTitle = sb_szTitle.toString( );}
   //:IF szTitle != ""
   if ( ZeidonStringCompare( szTitle, 1, 0, "", 1, 0, 257 ) != 0 )
   { 

      //:GetStringFromAttributeByContext( szSubtitle, mSPLDef, szEntity, "Subtitle", "", 256 )
      {StringBuilder sb_szSubtitle;
      if ( szSubtitle == null )
         sb_szSubtitle = new StringBuilder( 32 );
      else
         sb_szSubtitle = new StringBuilder( szSubtitle );
             GetStringFromAttributeByContext( sb_szSubtitle, mSPLDef, szEntity, "Subtitle", "", 256 );
      szSubtitle = sb_szSubtitle.toString( );}
      //:szTitlePosition = mSPLDef.SPLD_TemplateSection.TitlePosition
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szTitlePosition;
      if ( szTitlePosition == null )
         sb_szTitlePosition = new StringBuilder( 32 );
      else
         sb_szTitlePosition = new StringBuilder( szTitlePosition );
             GetVariableFromAttribute( sb_szTitlePosition, mi_lTempInteger_0, 'S', 3, mSPLDef, "SPLD_TemplateSection", "TitlePosition", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szTitlePosition = sb_szTitlePosition.toString( );}
      //:szBoldItalic = mSPLDef.SPLD_TemplateSection.TitleBoldItalic
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szBoldItalic;
      if ( szBoldItalic == null )
         sb_szBoldItalic = new StringBuilder( 32 );
      else
         sb_szBoldItalic = new StringBuilder( szBoldItalic );
             GetVariableFromAttribute( sb_szBoldItalic, mi_lTempInteger_1, 'S', 2, mSPLDef, "SPLD_TemplateSection", "TitleBoldItalic", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szBoldItalic = sb_szBoldItalic.toString( );}
      //:IF szTitlePosition = "SK"  // skip Title
      if ( ZeidonStringCompare( szTitlePosition, 1, 0, "SK", 1, 0, 3 ) == 0 )
      { 

         //:// do nothing with the title

         //:ELSE
      } 
      else
      { 
         //:IF szTitlePosition = "CF"  // combine Title with the First line
         if ( ZeidonStringCompare( szTitlePosition, 1, 0, "CF", 1, 0, 3 ) == 0 )
         { 

            //:szWriteBuffer = szTitle
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szTitle, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
            m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
            //:IF szBoldItalic = "B" OR szBoldItalic = "+"
            if ( ZeidonStringCompare( szBoldItalic, 1, 0, "B", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 );
            } 

            //:END

            //:IF szBoldItalic = "I" OR szBoldItalic = "+"
            if ( ZeidonStringCompare( szBoldItalic, 1, 0, "I", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 );
            } 

            //:END

            //:szCombinedTitle = szWriteBuffer
             {StringBuilder sb_szCombinedTitle;
            if ( szCombinedTitle == null )
               sb_szCombinedTitle = new StringBuilder( 32 );
            else
               sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                        ZeidonStringCopy( sb_szCombinedTitle, 1, 0, szWriteBuffer, 1, 0, 513 );
            szCombinedTitle = sb_szCombinedTitle.toString( );}
            //:IF szSubtitle != ""
            if ( ZeidonStringCompare( szSubtitle, 1, 0, "", 1, 0, 257 ) != 0 )
            { 
               //:szBoldItalic = mSPLDef.SPLD_TemplateSection.SubtitleBoldItalic
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szBoldItalic;
               if ( szBoldItalic == null )
                  sb_szBoldItalic = new StringBuilder( 32 );
               else
                  sb_szBoldItalic = new StringBuilder( szBoldItalic );
                               GetVariableFromAttribute( sb_szBoldItalic, mi_lTempInteger_2, 'S', 2, mSPLDef, "SPLD_TemplateSection", "SubtitleBoldItalic", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szBoldItalic = sb_szBoldItalic.toString( );}
               //:szWriteBuffer = " " + szSubtitle
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, " ", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSubtitle, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
               //:IF szBoldItalic = "B" OR szBoldItalic = "+"
               if ( ZeidonStringCompare( szBoldItalic, 1, 0, "B", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
               { 
                  //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 )
                  m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 );
               } 

               //:END

               //:IF szBoldItalic = "I" OR szBoldItalic = "+"
               if ( ZeidonStringCompare( szBoldItalic, 1, 0, "I", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
               { 
                  //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 )
                  m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 );
               } 

               //:END

               //:szCombinedTitle = szCombinedTitle + " " + szWriteBuffer
                {StringBuilder sb_szCombinedTitle;
               if ( szCombinedTitle == null )
                  sb_szCombinedTitle = new StringBuilder( 32 );
               else
                  sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                              ZeidonStringConcat( sb_szCombinedTitle, 1, 0, " ", 1, 0, 513 );
               szCombinedTitle = sb_szCombinedTitle.toString( );}
                {StringBuilder sb_szCombinedTitle;
               if ( szCombinedTitle == null )
                  sb_szCombinedTitle = new StringBuilder( 32 );
               else
                  sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                              ZeidonStringConcat( sb_szCombinedTitle, 1, 0, szWriteBuffer, 1, 0, 513 );
               szCombinedTitle = sb_szCombinedTitle.toString( );}
            } 

            //:END

            //:ELSE
         } 
         else
         { 

            //:szTitleLeading = mSPLDef.SPLD_TemplatePanel.dTitleLeadingDefault
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szTitleLeading;
            if ( szTitleLeading == null )
               sb_szTitleLeading = new StringBuilder( 32 );
            else
               sb_szTitleLeading = new StringBuilder( szTitleLeading );
                         GetVariableFromAttribute( sb_szTitleLeading, mi_lTempInteger_3, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "dTitleLeadingDefault", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szTitleLeading = sb_szTitleLeading.toString( );}
            //:szClass = mSPLDef.SPLD_TemplateSection.TitleClass
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szClass;
            if ( szClass == null )
               sb_szClass = new StringBuilder( 32 );
            else
               sb_szClass = new StringBuilder( szClass );
                         GetVariableFromAttribute( sb_szClass, mi_lTempInteger_4, 'S', 33, mSPLDef, "SPLD_TemplateSection", "TitleClass", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szClass = sb_szClass.toString( );}

            //:szWriteBuffer = szTitle
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szTitle, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
            m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );

            //:IF szClass != ""
            if ( ZeidonStringCompare( szClass, 1, 0, "", 1, 0, 33 ) != 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 2 );
            } 

            //:END

            //:IF szBoldItalic = "B" OR szBoldItalic = "+"
            if ( ZeidonStringCompare( szBoldItalic, 1, 0, "B", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 );
            } 

            //:END

            //:IF szBoldItalic = "I" OR szBoldItalic = "+"
            if ( ZeidonStringCompare( szBoldItalic, 1, 0, "I", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 );
            } 

            //:END

            //:IF szTitleLeading != ""
            if ( ZeidonStringCompare( szTitleLeading, 1, 0, "", 1, 0, 11 ) != 0 )
            { 
               //:szLineHeight = "line-height:" + szTitleLeading +";"
                {StringBuilder sb_szLineHeight;
               if ( szLineHeight == null )
                  sb_szLineHeight = new StringBuilder( 32 );
               else
                  sb_szLineHeight = new StringBuilder( szLineHeight );
                              ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
               szLineHeight = sb_szLineHeight.toString( );}
                {StringBuilder sb_szLineHeight;
               if ( szLineHeight == null )
                  sb_szLineHeight = new StringBuilder( 32 );
               else
                  sb_szLineHeight = new StringBuilder( szLineHeight );
                              ZeidonStringConcat( sb_szLineHeight, 1, 0, szTitleLeading, 1, 0, 33 );
               szLineHeight = sb_szLineHeight.toString( );}
                {StringBuilder sb_szLineHeight;
               if ( szLineHeight == null )
                  sb_szLineHeight = new StringBuilder( 32 );
               else
                  sb_szLineHeight = new StringBuilder( szLineHeight );
                              ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
               szLineHeight = sb_szLineHeight.toString( );}
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 );
            } 

            //:END

            //:IF szTitlePosition = "SC"
            if ( ZeidonStringCompare( szTitlePosition, 1, 0, "SC", 1, 0, 3 ) == 0 )
            { 
               //:// Title is center justified
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "div", "style", "text-align:center;", zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "div", "style", "text-align:center;", zQUOTES, 2 );
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
               //:// Title is left justified.
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "div", "style", "text-align:left;", zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "div", "style", "text-align:left;", zQUOTES, 2 );
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

            //:IF szSubtitle != ""
            if ( ZeidonStringCompare( szSubtitle, 1, 0, "", 1, 0, 257 ) != 0 )
            { 

               //:szClass = mSPLDef.SPLD_TemplateSection.SubtitleClass
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szClass;
               if ( szClass == null )
                  sb_szClass = new StringBuilder( 32 );
               else
                  sb_szClass = new StringBuilder( szClass );
                               GetVariableFromAttribute( sb_szClass, mi_lTempInteger_5, 'S', 33, mSPLDef, "SPLD_TemplateSection", "SubtitleClass", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szClass = sb_szClass.toString( );}
               //:szBoldItalic = mSPLDef.SPLD_TemplateSection.SubtitleBoldItalic
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
               StringBuilder sb_szBoldItalic;
               if ( szBoldItalic == null )
                  sb_szBoldItalic = new StringBuilder( 32 );
               else
                  sb_szBoldItalic = new StringBuilder( szBoldItalic );
                               GetVariableFromAttribute( sb_szBoldItalic, mi_lTempInteger_6, 'S', 2, mSPLDef, "SPLD_TemplateSection", "SubtitleBoldItalic", "", 0 );
               lTempInteger_6 = mi_lTempInteger_6.intValue( );
               szBoldItalic = sb_szBoldItalic.toString( );}
               //:szWriteBuffer = szSubtitle
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szSubtitle, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:IF szClass != ""
               if ( ZeidonStringCompare( szClass, 1, 0, "", 1, 0, 33 ) != 0 )
               { 
                  //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 2 )
                  m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 2 );
               } 

               //:END

               //:IF szBoldItalic = "I" OR szBoldItalic = "+"
               if ( ZeidonStringCompare( szBoldItalic, 1, 0, "I", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
               { 
                  //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 )
                  m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 );
               } 

               //:END

               //:IF szBoldItalic = "B" OR szBoldItalic = "+"
               if ( ZeidonStringCompare( szBoldItalic, 1, 0, "B", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
               { 
                  //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 )
                  m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 );
               } 

               //:END

               //:IF szTitlePosition = "SC"
               if ( ZeidonStringCompare( szTitlePosition, 1, 0, "SC", 1, 0, 3 ) == 0 )
               { 
                  //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "div", "style", "text-align:center;", zQUOTES, 2 )
                  m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "div", "style", "text-align:center;", zQUOTES, 2 );
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
            } 


            //:END

            //:szWriteBuffer = "                </tr>"
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                </tr>", 1, 0, 32001 );
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
      } 

      //:END
   } 

   //:END
   return;
// END
} 


//:LOCAL OPERATION
//:GeneratePDF_Title( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                    INTEGER lFile,
//:                    STRING ( 32 )    szEntity,
//:                    STRING ( 512 )   szCombinedTitle,
//:                    STRING ( 32000 ) szWriteBuffer )

//:   STRING ( 256 )    szTemp
private void 
omSPLDef_GeneratePDF_Title( View     mSPLDef,
                            int      lFile,
                            String   szEntity,
                            String   szCombinedTitle,
                            String   szWriteBuffer )
{
   String   szTemp = null;
   //:STRING ( 256 )    szTitle
   String   szTitle = null;
   //:STRING ( 256 )    szSubtitle
   String   szSubtitle = null;
   //:STRING ( 2 )      szTitlePosition
   String   szTitlePosition = null;
   //:STRING ( 10 )     szTitleLeading
   String   szTitleLeading = null;
   //:STRING ( 1 )      szBoldItalic
   String   szBoldItalic = null;
   //:STRING ( 32 )     szClass
   String   szClass = null;
   //:STRING ( 32 )     szLineHeight
   String   szLineHeight = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;


   //:szCombinedTitle = ""  // don't pass anything back to display
    {StringBuilder sb_szCombinedTitle;
   if ( szCombinedTitle == null )
      sb_szCombinedTitle = new StringBuilder( 32 );
   else
      sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
      ZeidonStringCopy( sb_szCombinedTitle, 1, 0, "", 1, 0, 513 );
   szCombinedTitle = sb_szCombinedTitle.toString( );}

   //:// Process Title if it exists.  We will either generate it as its own line, or prepend it to the Statement Line.
   //:// Also, generate headers for any bold/italic requests.
   //:GetStringFromAttributeByContext( szTitle, mSPLDef, szEntity, "Title", "", 256 )
   {StringBuilder sb_szTitle;
   if ( szTitle == null )
      sb_szTitle = new StringBuilder( 32 );
   else
      sb_szTitle = new StringBuilder( szTitle );
       GetStringFromAttributeByContext( sb_szTitle, mSPLDef, szEntity, "Title", "", 256 );
   szTitle = sb_szTitle.toString( );}
   //:IF szTitle != ""
   if ( ZeidonStringCompare( szTitle, 1, 0, "", 1, 0, 257 ) != 0 )
   { 

      //:GetStringFromAttributeByContext( szSubtitle, mSPLDef, szEntity, "Subtitle", "", 256 )
      {StringBuilder sb_szSubtitle;
      if ( szSubtitle == null )
         sb_szSubtitle = new StringBuilder( 32 );
      else
         sb_szSubtitle = new StringBuilder( szSubtitle );
             GetStringFromAttributeByContext( sb_szSubtitle, mSPLDef, szEntity, "Subtitle", "", 256 );
      szSubtitle = sb_szSubtitle.toString( );}
      //:szTitlePosition = mSPLDef.SPLD_TemplateSection.TitlePosition
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szTitlePosition;
      if ( szTitlePosition == null )
         sb_szTitlePosition = new StringBuilder( 32 );
      else
         sb_szTitlePosition = new StringBuilder( szTitlePosition );
             GetVariableFromAttribute( sb_szTitlePosition, mi_lTempInteger_0, 'S', 3, mSPLDef, "SPLD_TemplateSection", "TitlePosition", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szTitlePosition = sb_szTitlePosition.toString( );}
      //:szBoldItalic = mSPLDef.SPLD_TemplateSection.TitleBoldItalic
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szBoldItalic;
      if ( szBoldItalic == null )
         sb_szBoldItalic = new StringBuilder( 32 );
      else
         sb_szBoldItalic = new StringBuilder( szBoldItalic );
             GetVariableFromAttribute( sb_szBoldItalic, mi_lTempInteger_1, 'S', 2, mSPLDef, "SPLD_TemplateSection", "TitleBoldItalic", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szBoldItalic = sb_szBoldItalic.toString( );}
      //:IF szTitlePosition = "SK"  // skip Title
      if ( ZeidonStringCompare( szTitlePosition, 1, 0, "SK", 1, 0, 3 ) == 0 )
      { 

         //:// do nothing with the title

         //:ELSE
      } 
      else
      { 
         //:IF szTitlePosition = "CF"  // combine Title with the First line
         if ( ZeidonStringCompare( szTitlePosition, 1, 0, "CF", 1, 0, 3 ) == 0 )
         { 

            //:szWriteBuffer = szTitle
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szTitle, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
            m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
            //:IF szBoldItalic = "B" OR szBoldItalic = "+"
            if ( ZeidonStringCompare( szBoldItalic, 1, 0, "B", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 );
            } 

            //:END

            //:IF szBoldItalic = "I" OR szBoldItalic = "+"
            if ( ZeidonStringCompare( szBoldItalic, 1, 0, "I", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 );
            } 

            //:END

            //:szCombinedTitle = szWriteBuffer
             {StringBuilder sb_szCombinedTitle;
            if ( szCombinedTitle == null )
               sb_szCombinedTitle = new StringBuilder( 32 );
            else
               sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                        ZeidonStringCopy( sb_szCombinedTitle, 1, 0, szWriteBuffer, 1, 0, 513 );
            szCombinedTitle = sb_szCombinedTitle.toString( );}
            //:IF szSubtitle != ""
            if ( ZeidonStringCompare( szSubtitle, 1, 0, "", 1, 0, 257 ) != 0 )
            { 
               //:szBoldItalic = mSPLDef.SPLD_TemplateSection.SubtitleBoldItalic
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szBoldItalic;
               if ( szBoldItalic == null )
                  sb_szBoldItalic = new StringBuilder( 32 );
               else
                  sb_szBoldItalic = new StringBuilder( szBoldItalic );
                               GetVariableFromAttribute( sb_szBoldItalic, mi_lTempInteger_2, 'S', 2, mSPLDef, "SPLD_TemplateSection", "SubtitleBoldItalic", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szBoldItalic = sb_szBoldItalic.toString( );}
               //:szWriteBuffer = " " + szSubtitle
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, " ", 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringConcat( sb_szWriteBuffer, 1, 0, szSubtitle, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );
               //:IF szBoldItalic = "B" OR szBoldItalic = "+"
               if ( ZeidonStringCompare( szBoldItalic, 1, 0, "B", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
               { 
                  //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 )
                  m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 );
               } 

               //:END

               //:IF szBoldItalic = "I" OR szBoldItalic = "+"
               if ( ZeidonStringCompare( szBoldItalic, 1, 0, "I", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
               { 
                  //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 )
                  m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 );
               } 

               //:END

               //:szCombinedTitle = szCombinedTitle + " " + szWriteBuffer
                {StringBuilder sb_szCombinedTitle;
               if ( szCombinedTitle == null )
                  sb_szCombinedTitle = new StringBuilder( 32 );
               else
                  sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                              ZeidonStringConcat( sb_szCombinedTitle, 1, 0, " ", 1, 0, 513 );
               szCombinedTitle = sb_szCombinedTitle.toString( );}
                {StringBuilder sb_szCombinedTitle;
               if ( szCombinedTitle == null )
                  sb_szCombinedTitle = new StringBuilder( 32 );
               else
                  sb_szCombinedTitle = new StringBuilder( szCombinedTitle );
                              ZeidonStringConcat( sb_szCombinedTitle, 1, 0, szWriteBuffer, 1, 0, 513 );
               szCombinedTitle = sb_szCombinedTitle.toString( );}
            } 

            //:END

            //:ELSE
         } 
         else
         { 

            //:szTitleLeading = mSPLDef.SPLD_TemplatePanel.dTitleLeadingDefault
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szTitleLeading;
            if ( szTitleLeading == null )
               sb_szTitleLeading = new StringBuilder( 32 );
            else
               sb_szTitleLeading = new StringBuilder( szTitleLeading );
                         GetVariableFromAttribute( sb_szTitleLeading, mi_lTempInteger_3, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "dTitleLeadingDefault", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szTitleLeading = sb_szTitleLeading.toString( );}
            //:szClass = mSPLDef.SPLD_TemplateSection.TitleClass
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szClass;
            if ( szClass == null )
               sb_szClass = new StringBuilder( 32 );
            else
               sb_szClass = new StringBuilder( szClass );
                         GetVariableFromAttribute( sb_szClass, mi_lTempInteger_4, 'S', 33, mSPLDef, "SPLD_TemplateSection", "TitleClass", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szClass = sb_szClass.toString( );}

            //:szWriteBuffer = szTitle
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szTitle, 1, 0, 32001 );
            szWriteBuffer = sb_szWriteBuffer.toString( );}
            //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 )
            m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "", "", "", 0 );

            //:IF szClass != ""
            if ( ZeidonStringCompare( szClass, 1, 0, "", 1, 0, 33 ) != 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 2 );
            } 

            //:END

            //:IF szBoldItalic = "B" OR szBoldItalic = "+"
            if ( ZeidonStringCompare( szBoldItalic, 1, 0, "B", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 );
            } 

            //:END

            //:IF szBoldItalic = "I" OR szBoldItalic = "+"
            if ( ZeidonStringCompare( szBoldItalic, 1, 0, "I", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
            { 
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 );
            } 

            //:END

            //:IF szTitleLeading != ""
            if ( ZeidonStringCompare( szTitleLeading, 1, 0, "", 1, 0, 11 ) != 0 )
            { 
               //:szLineHeight = "line-height:" + szTitleLeading +";"
                {StringBuilder sb_szLineHeight;
               if ( szLineHeight == null )
                  sb_szLineHeight = new StringBuilder( 32 );
               else
                  sb_szLineHeight = new StringBuilder( szLineHeight );
                              ZeidonStringCopy( sb_szLineHeight, 1, 0, "line-height:", 1, 0, 33 );
               szLineHeight = sb_szLineHeight.toString( );}
                {StringBuilder sb_szLineHeight;
               if ( szLineHeight == null )
                  sb_szLineHeight = new StringBuilder( 32 );
               else
                  sb_szLineHeight = new StringBuilder( szLineHeight );
                              ZeidonStringConcat( sb_szLineHeight, 1, 0, szTitleLeading, 1, 0, 33 );
               szLineHeight = sb_szLineHeight.toString( );}
                {StringBuilder sb_szLineHeight;
               if ( szLineHeight == null )
                  sb_szLineHeight = new StringBuilder( 32 );
               else
                  sb_szLineHeight = new StringBuilder( szLineHeight );
                              ZeidonStringConcat( sb_szLineHeight, 1, 0, ";", 1, 0, 33 );
               szLineHeight = sb_szLineHeight.toString( );}
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", szLineHeight, zQUOTES, 2 );
            } 

            //:END

            //:IF szTitlePosition = "SC"
            if ( ZeidonStringCompare( szTitlePosition, 1, 0, "SC", 1, 0, 3 ) == 0 )
            { 
               //:// Title is center justified
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "div", "style", "text-align:center;", zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "div", "style", "text-align:center;", zQUOTES, 2 );
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
               //:// Title is left justified.
               //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "div", "style", "text-align:left;", zQUOTES, 2 )
               m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "div", "style", "text-align:left;", zQUOTES, 2 );
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

            //:IF szSubtitle != ""
            if ( ZeidonStringCompare( szSubtitle, 1, 0, "", 1, 0, 257 ) != 0 )
            { 

               //:szClass = mSPLDef.SPLD_TemplateSection.SubtitleClass
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szClass;
               if ( szClass == null )
                  sb_szClass = new StringBuilder( 32 );
               else
                  sb_szClass = new StringBuilder( szClass );
                               GetVariableFromAttribute( sb_szClass, mi_lTempInteger_5, 'S', 33, mSPLDef, "SPLD_TemplateSection", "SubtitleClass", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szClass = sb_szClass.toString( );}
               //:szBoldItalic = mSPLDef.SPLD_TemplateSection.SubtitleBoldItalic
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
               StringBuilder sb_szBoldItalic;
               if ( szBoldItalic == null )
                  sb_szBoldItalic = new StringBuilder( 32 );
               else
                  sb_szBoldItalic = new StringBuilder( szBoldItalic );
                               GetVariableFromAttribute( sb_szBoldItalic, mi_lTempInteger_6, 'S', 2, mSPLDef, "SPLD_TemplateSection", "SubtitleBoldItalic", "", 0 );
               lTempInteger_6 = mi_lTempInteger_6.intValue( );
               szBoldItalic = sb_szBoldItalic.toString( );}
               //:szWriteBuffer = szSubtitle
                {StringBuilder sb_szWriteBuffer;
               if ( szWriteBuffer == null )
                  sb_szWriteBuffer = new StringBuilder( 32 );
               else
                  sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                              ZeidonStringCopy( sb_szWriteBuffer, 1, 0, szSubtitle, 1, 0, 32001 );
               szWriteBuffer = sb_szWriteBuffer.toString( );}
               //:IF szClass != ""
               if ( ZeidonStringCompare( szClass, 1, 0, "", 1, 0, 33 ) != 0 )
               { 
                  //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 2 )
                  m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "class", szClass, zQUOTES, 2 );
               } 

               //:END

               //:IF szBoldItalic = "I" OR szBoldItalic = "+"
               if ( ZeidonStringCompare( szBoldItalic, 1, 0, "I", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
               { 
                  //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 )
                  m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-style:italic;", zQUOTES, 2 );
               } 

               //:END

               //:IF szBoldItalic = "B" OR szBoldItalic = "+"
               if ( ZeidonStringCompare( szBoldItalic, 1, 0, "B", 1, 0, 2 ) == 0 || ZeidonStringCompare( szBoldItalic, 1, 0, "+", 1, 0, 2 ) == 0 )
               { 
                  //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 )
                  m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "p", "style", "font-weight:bold;", zQUOTES, 2 );
               } 

               //:END

               //:IF szTitlePosition = "SC"
               if ( ZeidonStringCompare( szTitlePosition, 1, 0, "SC", 1, 0, 3 ) == 0 )
               { 
                  //:AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "div", "style", "text-align:center;", zQUOTES, 2 )
                  m_ZDRVROPR.AddHTML_TagAttribute( mSPLDef, szWriteBuffer, 32000, "div", "style", "text-align:center;", zQUOTES, 2 );
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
            } 


            //:END

            //:szWriteBuffer = "                </tr>"
             {StringBuilder sb_szWriteBuffer;
            if ( szWriteBuffer == null )
               sb_szWriteBuffer = new StringBuilder( 32 );
            else
               sb_szWriteBuffer = new StringBuilder( szWriteBuffer );
                        ZeidonStringCopy( sb_szWriteBuffer, 1, 0, "                </tr>", 1, 0, 32001 );
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
      } 

      //:END
   } 

   //:END
   return;
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dLeading( VIEW mSPLDef BASED ON LOD mSPLDef,
//:          STRING ( 32 ) InternalEntityStructure,
//:          STRING ( 32 ) InternalAttribStructure,
//:          SHORT GetOrSetFlag )

//:   STRING ( 16 ) szLeadingHeight
public int 
omSPLDef_dLeading( View     mSPLDef,
                   String InternalEntityStructure,
                   String InternalAttribStructure,
                   Integer   GetOrSetFlag )
{
   String   szLeadingHeight = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Add Leading Units to Leading Height if the height is not 0.
         //:IF mSPLDef.SPLD_TemplatePanel.LeadingHeight = 0.0
         if ( CompareAttributeToDecimal( mSPLDef, "SPLD_TemplatePanel", "LeadingHeight", (double) 0.0 ) == 0 )
         { 
            //:szLeadingHeight = ""
             {StringBuilder sb_szLeadingHeight;
            if ( szLeadingHeight == null )
               sb_szLeadingHeight = new StringBuilder( 32 );
            else
               sb_szLeadingHeight = new StringBuilder( szLeadingHeight );
                        ZeidonStringCopy( sb_szLeadingHeight, 1, 0, "", 1, 0, 17 );
            szLeadingHeight = sb_szLeadingHeight.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szLeadingHeight = mSPLDef.SPLD_TemplatePanel.LeadingHeight
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szLeadingHeight;
            if ( szLeadingHeight == null )
               sb_szLeadingHeight = new StringBuilder( 32 );
            else
               sb_szLeadingHeight = new StringBuilder( szLeadingHeight );
                         GetVariableFromAttribute( sb_szLeadingHeight, mi_lTempInteger_0, 'S', 17, mSPLDef, "SPLD_TemplatePanel", "LeadingHeight", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szLeadingHeight = sb_szLeadingHeight.toString( );}
            //:zSearchAndReplace( szLeadingHeight, 10, ".0", "" )  // make #.0 -> #
            {StringBuilder sb_szLeadingHeight;
            if ( szLeadingHeight == null )
               sb_szLeadingHeight = new StringBuilder( 32 );
            else
               sb_szLeadingHeight = new StringBuilder( szLeadingHeight );
                         zSearchAndReplace( sb_szLeadingHeight, 10, ".0", "" );
            szLeadingHeight = sb_szLeadingHeight.toString( );}
            //:szLeadingHeight = szLeadingHeight + mSPLDef.SPLD_TemplatePanel.LeadingUnits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "LeadingUnits", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szLeadingHeight;
            if ( szLeadingHeight == null )
               sb_szLeadingHeight = new StringBuilder( 32 );
            else
               sb_szLeadingHeight = new StringBuilder( szLeadingHeight );
                        ZeidonStringConcat( sb_szLeadingHeight, 1, 0, szTempString_0, 1, 0, 17 );
            szLeadingHeight = sb_szLeadingHeight.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef,
         //:               InternalEntityStructure, InternalAttribStructure, szLeadingHeight )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szLeadingHeight );
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
//:dKerning( VIEW mSPLDef BASED ON LOD mSPLDef,
//:          STRING ( 32 ) InternalEntityStructure,
//:          STRING ( 32 ) InternalAttribStructure,
//:          SHORT GetOrSetFlag )

//:   STRING ( 16 ) szKerningWidth
public int 
omSPLDef_dKerning( View     mSPLDef,
                   String InternalEntityStructure,
                   String InternalAttribStructure,
                   Integer   GetOrSetFlag )
{
   String   szKerningWidth = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Add Kerning Units to Kerning Width if the width is not 0.
         //:IF mSPLDef.SPLD_TemplatePanel.KerningWidth = 0.0
         if ( CompareAttributeToDecimal( mSPLDef, "SPLD_TemplatePanel", "KerningWidth", (double) 0.0 ) == 0 )
         { 
            //:szKerningWidth = ""
             {StringBuilder sb_szKerningWidth;
            if ( szKerningWidth == null )
               sb_szKerningWidth = new StringBuilder( 32 );
            else
               sb_szKerningWidth = new StringBuilder( szKerningWidth );
                        ZeidonStringCopy( sb_szKerningWidth, 1, 0, "", 1, 0, 17 );
            szKerningWidth = sb_szKerningWidth.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szKerningWidth = mSPLDef.SPLD_TemplatePanel.KerningWidth
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szKerningWidth;
            if ( szKerningWidth == null )
               sb_szKerningWidth = new StringBuilder( 32 );
            else
               sb_szKerningWidth = new StringBuilder( szKerningWidth );
                         GetVariableFromAttribute( sb_szKerningWidth, mi_lTempInteger_0, 'S', 17, mSPLDef, "SPLD_TemplatePanel", "KerningWidth", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szKerningWidth = sb_szKerningWidth.toString( );}
            //:zSearchAndReplace( szKerningWidth, 10, ".0", "" )  // make #.0 -> #
            {StringBuilder sb_szKerningWidth;
            if ( szKerningWidth == null )
               sb_szKerningWidth = new StringBuilder( 32 );
            else
               sb_szKerningWidth = new StringBuilder( szKerningWidth );
                         zSearchAndReplace( sb_szKerningWidth, 10, ".0", "" );
            szKerningWidth = sb_szKerningWidth.toString( );}
            //:szKerningWidth = szKerningWidth + mSPLDef.SPLD_TemplatePanel.KerningUnits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "KerningUnits", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szKerningWidth;
            if ( szKerningWidth == null )
               sb_szKerningWidth = new StringBuilder( 32 );
            else
               sb_szKerningWidth = new StringBuilder( szKerningWidth );
                        ZeidonStringConcat( sb_szKerningWidth, 1, 0, szTempString_0, 1, 0, 17 );
            szKerningWidth = sb_szKerningWidth.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef,
         //:               InternalEntityStructure, InternalAttribStructure, szKerningWidth )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szKerningWidth );
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
//:dWordSpacing( VIEW mSPLDef BASED ON LOD mSPLDef,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )

//:   STRING ( 16 ) szWordSpacing
public int 
omSPLDef_dWordSpacing( View     mSPLDef,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{
   String   szWordSpacing = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Add Word Spacing Units to Word Spacing Width if the width is not 0.
         //:IF mSPLDef.SPLD_TemplatePanel.WordSpacingWidth = 0.0
         if ( CompareAttributeToDecimal( mSPLDef, "SPLD_TemplatePanel", "WordSpacingWidth", (double) 0.0 ) == 0 )
         { 
            //:szWordSpacing = ""
             {StringBuilder sb_szWordSpacing;
            if ( szWordSpacing == null )
               sb_szWordSpacing = new StringBuilder( 32 );
            else
               sb_szWordSpacing = new StringBuilder( szWordSpacing );
                        ZeidonStringCopy( sb_szWordSpacing, 1, 0, "", 1, 0, 17 );
            szWordSpacing = sb_szWordSpacing.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szWordSpacing = mSPLDef.SPLD_TemplatePanel.WordSpacingWidth
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szWordSpacing;
            if ( szWordSpacing == null )
               sb_szWordSpacing = new StringBuilder( 32 );
            else
               sb_szWordSpacing = new StringBuilder( szWordSpacing );
                         GetVariableFromAttribute( sb_szWordSpacing, mi_lTempInteger_0, 'S', 17, mSPLDef, "SPLD_TemplatePanel", "WordSpacingWidth", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szWordSpacing = sb_szWordSpacing.toString( );}
            //:zSearchAndReplace( szWordSpacing, 10, ".0", "" )  // make #.0 -> #
            {StringBuilder sb_szWordSpacing;
            if ( szWordSpacing == null )
               sb_szWordSpacing = new StringBuilder( 32 );
            else
               sb_szWordSpacing = new StringBuilder( szWordSpacing );
                         zSearchAndReplace( sb_szWordSpacing, 10, ".0", "" );
            szWordSpacing = sb_szWordSpacing.toString( );}
            //:szWordSpacing = szWordSpacing + mSPLDef.SPLD_TemplatePanel.WordSpacingUnits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "WordSpacingUnits", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szWordSpacing;
            if ( szWordSpacing == null )
               sb_szWordSpacing = new StringBuilder( 32 );
            else
               sb_szWordSpacing = new StringBuilder( szWordSpacing );
                        ZeidonStringConcat( sb_szWordSpacing, 1, 0, szTempString_0, 1, 0, 17 );
            szWordSpacing = sb_szWordSpacing.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef,
         //:               InternalEntityStructure, InternalAttribStructure, szWordSpacing )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szWordSpacing );
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
//:dTitleLeadingDefault( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )

//:   STRING ( 16 ) szTitleLeadingHeightDefault
public int 
omSPLDef_dTitleLeadingDefault( View     mSPLDef,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   String   szTitleLeadingHeightDefault = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Add Leading Units to Leading Height if the height is not 0.
         //:IF mSPLDef.SPLD_TemplatePanel.TitleLeadingHeightDefault = 0.0
         if ( CompareAttributeToDecimal( mSPLDef, "SPLD_TemplatePanel", "TitleLeadingHeightDefault", (double) 0.0 ) == 0 )
         { 
            //:szTitleLeadingHeightDefault = ""
             {StringBuilder sb_szTitleLeadingHeightDefault;
            if ( szTitleLeadingHeightDefault == null )
               sb_szTitleLeadingHeightDefault = new StringBuilder( 32 );
            else
               sb_szTitleLeadingHeightDefault = new StringBuilder( szTitleLeadingHeightDefault );
                        ZeidonStringCopy( sb_szTitleLeadingHeightDefault, 1, 0, "", 1, 0, 17 );
            szTitleLeadingHeightDefault = sb_szTitleLeadingHeightDefault.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szTitleLeadingHeightDefault = mSPLDef.SPLD_TemplatePanel.TitleLeadingHeightDefault
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTitleLeadingHeightDefault;
            if ( szTitleLeadingHeightDefault == null )
               sb_szTitleLeadingHeightDefault = new StringBuilder( 32 );
            else
               sb_szTitleLeadingHeightDefault = new StringBuilder( szTitleLeadingHeightDefault );
                         GetVariableFromAttribute( sb_szTitleLeadingHeightDefault, mi_lTempInteger_0, 'S', 17, mSPLDef, "SPLD_TemplatePanel", "TitleLeadingHeightDefault", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTitleLeadingHeightDefault = sb_szTitleLeadingHeightDefault.toString( );}
            //:zSearchAndReplace( szTitleLeadingHeightDefault, 10, ".0", "" )  // make #.0 -> #
            {StringBuilder sb_szTitleLeadingHeightDefault;
            if ( szTitleLeadingHeightDefault == null )
               sb_szTitleLeadingHeightDefault = new StringBuilder( 32 );
            else
               sb_szTitleLeadingHeightDefault = new StringBuilder( szTitleLeadingHeightDefault );
                         zSearchAndReplace( sb_szTitleLeadingHeightDefault, 10, ".0", "" );
            szTitleLeadingHeightDefault = sb_szTitleLeadingHeightDefault.toString( );}
            //:szTitleLeadingHeightDefault = szTitleLeadingHeightDefault + mSPLDef.SPLD_TemplatePanel.LeadingUnits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "LeadingUnits", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szTitleLeadingHeightDefault;
            if ( szTitleLeadingHeightDefault == null )
               sb_szTitleLeadingHeightDefault = new StringBuilder( 32 );
            else
               sb_szTitleLeadingHeightDefault = new StringBuilder( szTitleLeadingHeightDefault );
                        ZeidonStringConcat( sb_szTitleLeadingHeightDefault, 1, 0, szTempString_0, 1, 0, 17 );
            szTitleLeadingHeightDefault = sb_szTitleLeadingHeightDefault.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef,
         //:               InternalEntityStructure, InternalAttribStructure, szTitleLeadingHeightDefault )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szTitleLeadingHeightDefault );
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
//:dStmtLeadingDefault( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   STRING ( 16 ) szStmtLeadingHeightDefault
public int 
omSPLDef_dStmtLeadingDefault( View     mSPLDef,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szStmtLeadingHeightDefault = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Add Leading Units to Leading Height if the height is not 0.
         //:IF mSPLDef.SPLD_TemplatePanel.StatementLeadingHeightDefault = 0.0
         if ( CompareAttributeToDecimal( mSPLDef, "SPLD_TemplatePanel", "StatementLeadingHeightDefault", (double) 0.0 ) == 0 )
         { 
            //:szStmtLeadingHeightDefault = ""
             {StringBuilder sb_szStmtLeadingHeightDefault;
            if ( szStmtLeadingHeightDefault == null )
               sb_szStmtLeadingHeightDefault = new StringBuilder( 32 );
            else
               sb_szStmtLeadingHeightDefault = new StringBuilder( szStmtLeadingHeightDefault );
                        ZeidonStringCopy( sb_szStmtLeadingHeightDefault, 1, 0, "", 1, 0, 17 );
            szStmtLeadingHeightDefault = sb_szStmtLeadingHeightDefault.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szStmtLeadingHeightDefault = mSPLDef.SPLD_TemplatePanel.StatementLeadingHeightDefault
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szStmtLeadingHeightDefault;
            if ( szStmtLeadingHeightDefault == null )
               sb_szStmtLeadingHeightDefault = new StringBuilder( 32 );
            else
               sb_szStmtLeadingHeightDefault = new StringBuilder( szStmtLeadingHeightDefault );
                         GetVariableFromAttribute( sb_szStmtLeadingHeightDefault, mi_lTempInteger_0, 'S', 17, mSPLDef, "SPLD_TemplatePanel", "StatementLeadingHeightDefault", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szStmtLeadingHeightDefault = sb_szStmtLeadingHeightDefault.toString( );}
            //:zSearchAndReplace( szStmtLeadingHeightDefault, 10, ".0", "" )  // make #.0 -> #
            {StringBuilder sb_szStmtLeadingHeightDefault;
            if ( szStmtLeadingHeightDefault == null )
               sb_szStmtLeadingHeightDefault = new StringBuilder( 32 );
            else
               sb_szStmtLeadingHeightDefault = new StringBuilder( szStmtLeadingHeightDefault );
                         zSearchAndReplace( sb_szStmtLeadingHeightDefault, 10, ".0", "" );
            szStmtLeadingHeightDefault = sb_szStmtLeadingHeightDefault.toString( );}
            //:szStmtLeadingHeightDefault = szStmtLeadingHeightDefault + mSPLDef.SPLD_TemplatePanel.LeadingUnits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplatePanel", "LeadingUnits", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szStmtLeadingHeightDefault;
            if ( szStmtLeadingHeightDefault == null )
               sb_szStmtLeadingHeightDefault = new StringBuilder( 32 );
            else
               sb_szStmtLeadingHeightDefault = new StringBuilder( szStmtLeadingHeightDefault );
                        ZeidonStringConcat( sb_szStmtLeadingHeightDefault, 1, 0, szTempString_0, 1, 0, 17 );
            szStmtLeadingHeightDefault = sb_szStmtLeadingHeightDefault.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef,
         //:               InternalEntityStructure, InternalAttribStructure, szStmtLeadingHeightDefault )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szStmtLeadingHeightDefault );
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
//:dStatementKerning( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   STRING ( 16 ) szStatementKerning
public int 
omSPLDef_dStatementKerning( View     mSPLDef,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szStatementKerning = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Add Kerning Units to Kerning Width if the width is not 0.
         //:IF mSPLDef.SPLD_TemplateSection.KerningWidth = 0.0
         if ( CompareAttributeToDecimal( mSPLDef, "SPLD_TemplateSection", "KerningWidth", (double) 0.0 ) == 0 )
         { 
            //:szStatementKerning = ""
             {StringBuilder sb_szStatementKerning;
            if ( szStatementKerning == null )
               sb_szStatementKerning = new StringBuilder( 32 );
            else
               sb_szStatementKerning = new StringBuilder( szStatementKerning );
                        ZeidonStringCopy( sb_szStatementKerning, 1, 0, "", 1, 0, 17 );
            szStatementKerning = sb_szStatementKerning.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szStatementKerning = mSPLDef.SPLD_TemplateSection.KerningWidth
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szStatementKerning;
            if ( szStatementKerning == null )
               sb_szStatementKerning = new StringBuilder( 32 );
            else
               sb_szStatementKerning = new StringBuilder( szStatementKerning );
                         GetVariableFromAttribute( sb_szStatementKerning, mi_lTempInteger_0, 'S', 17, mSPLDef, "SPLD_TemplateSection", "KerningWidth", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szStatementKerning = sb_szStatementKerning.toString( );}
            //:zSearchAndReplace( szStatementKerning, 10, ".0", "" )  // make #.0 -> #
            {StringBuilder sb_szStatementKerning;
            if ( szStatementKerning == null )
               sb_szStatementKerning = new StringBuilder( 32 );
            else
               sb_szStatementKerning = new StringBuilder( szStatementKerning );
                         zSearchAndReplace( sb_szStatementKerning, 10, ".0", "" );
            szStatementKerning = sb_szStatementKerning.toString( );}
            //:szStatementKerning = szStatementKerning + mSPLDef.SPLD_TemplateSection.KerningUnits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplateSection", "KerningUnits", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szStatementKerning;
            if ( szStatementKerning == null )
               sb_szStatementKerning = new StringBuilder( 32 );
            else
               sb_szStatementKerning = new StringBuilder( szStatementKerning );
                        ZeidonStringConcat( sb_szStatementKerning, 1, 0, szTempString_0, 1, 0, 17 );
            szStatementKerning = sb_szStatementKerning.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef,
         //:               InternalEntityStructure, InternalAttribStructure, szStatementKerning )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szStatementKerning );
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
//:dStatementLeading( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   STRING ( 16 ) szStatementLeading
public int 
omSPLDef_dStatementLeading( View     mSPLDef,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szStatementLeading = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Add Leading Units to Leading Height if the height is not 0.
         //:IF mSPLDef.SPLD_TemplateSection.LeadingHeight = 0.0
         if ( CompareAttributeToDecimal( mSPLDef, "SPLD_TemplateSection", "LeadingHeight", (double) 0.0 ) == 0 )
         { 
            //:szStatementLeading = ""
             {StringBuilder sb_szStatementLeading;
            if ( szStatementLeading == null )
               sb_szStatementLeading = new StringBuilder( 32 );
            else
               sb_szStatementLeading = new StringBuilder( szStatementLeading );
                        ZeidonStringCopy( sb_szStatementLeading, 1, 0, "", 1, 0, 17 );
            szStatementLeading = sb_szStatementLeading.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szStatementLeading = mSPLDef.SPLD_TemplateSection.LeadingHeight
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szStatementLeading;
            if ( szStatementLeading == null )
               sb_szStatementLeading = new StringBuilder( 32 );
            else
               sb_szStatementLeading = new StringBuilder( szStatementLeading );
                         GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_0, 'S', 17, mSPLDef, "SPLD_TemplateSection", "LeadingHeight", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szStatementLeading = sb_szStatementLeading.toString( );}
            //:zSearchAndReplace( szStatementLeading, 10, ".0", "" )  // make #.0 -> #
            {StringBuilder sb_szStatementLeading;
            if ( szStatementLeading == null )
               sb_szStatementLeading = new StringBuilder( 32 );
            else
               sb_szStatementLeading = new StringBuilder( szStatementLeading );
                         zSearchAndReplace( sb_szStatementLeading, 10, ".0", "" );
            szStatementLeading = sb_szStatementLeading.toString( );}
            //:szStatementLeading = szStatementLeading + mSPLDef.SPLD_TemplateSection.LeadingUnits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mSPLDef, "SPLD_TemplateSection", "LeadingUnits", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szStatementLeading;
            if ( szStatementLeading == null )
               sb_szStatementLeading = new StringBuilder( 32 );
            else
               sb_szStatementLeading = new StringBuilder( szStatementLeading );
                        ZeidonStringConcat( sb_szStatementLeading, 1, 0, szTempString_0, 1, 0, 17 );
            szStatementLeading = sb_szStatementLeading.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSPLDef,
         //:               InternalEntityStructure, InternalAttribStructure, szStatementLeading )
         StoreStringInRecord( mSPLDef, InternalEntityStructure, InternalAttribStructure, szStatementLeading );
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



}
