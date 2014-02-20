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

/**
   @author QuinSoft
**/

public class mTempl_Object extends VmlObjectOperations
{
   public mTempl_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dPanelName( VIEW mTemplOrig BASED ON LOD mTempl,
//:            STRING ( 32 ) InternalEntityStructure,
//:            STRING ( 32 ) InternalAttribStructure,
//:            SHORT GetOrSetFlag )

//:   VIEW mTempl  BASED ON LOD mTempl
public int 
omTempl_dPanelName( View     mTemplOrig,
                    String InternalEntityStructure,
                    String InternalAttribStructure,
                    Integer   GetOrSetFlag )
{
   zVIEW    mTempl = new zVIEW( );
   //:VIEW mTempl2 BASED ON LOD mTempl
   zVIEW    mTempl2 = new zVIEW( );
   //:STRING ( 50 ) szPanelName
   String   szPanelName = null;
   //:STRING ( 10 ) szPanelNumber
   String   szPanelNumber = null;
   //:INTEGER       lPanelNumber
   int      lPanelNumber = 0;
   //:INTEGER       lTempID
   int      lTempID = 0;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Currently the Panel Name is just the Panel Number.
         //:// We will use the named view in memory for creating mTempl2, as the view in the operation may be hierarchical.
         //:/*GET VIEW mTempl NAMED "mTempl"
         //:CreateViewFromView( mTempl2, mTempl )
         //:lTempID = mTemplOrig.TemplatePanel.wTempID
         //:FOR EACH mTempl2.TemplatePanel
         //:lPanelNumber = lPanelNumber + 1
         //:IF mTempl2.TemplatePanel.wTempID = lTempID
         //:szPanelNumber = lPanelNumber
         //:szPanelName = "panel" + szPanelNumber
         //:END
         //:END
         //:DropView( mTempl2 )*/
         //:// Right now we'll assume no hierarchical subobject.
         //:szPanelNumber = mTemplOrig.TemplatePanel.wSequentialPanelNumber
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szPanelNumber;
         if ( szPanelNumber == null )
            sb_szPanelNumber = new StringBuilder( 32 );
         else
            sb_szPanelNumber = new StringBuilder( szPanelNumber );
                   GetVariableFromAttribute( sb_szPanelNumber, mi_lTempInteger_0, 'S', 11, mTemplOrig, "TemplatePanel", "wSequentialPanelNumber", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szPanelNumber = sb_szPanelNumber.toString( );}
         //:szPanelName = "panel" + szPanelNumber
          {StringBuilder sb_szPanelName;
         if ( szPanelName == null )
            sb_szPanelName = new StringBuilder( 32 );
         else
            sb_szPanelName = new StringBuilder( szPanelName );
                  ZeidonStringCopy( sb_szPanelName, 1, 0, "panel", 1, 0, 51 );
         szPanelName = sb_szPanelName.toString( );}
          {StringBuilder sb_szPanelName;
         if ( szPanelName == null )
            sb_szPanelName = new StringBuilder( 32 );
         else
            sb_szPanelName = new StringBuilder( szPanelName );
                  ZeidonStringConcat( sb_szPanelName, 1, 0, szPanelNumber, 1, 0, 51 );
         szPanelName = sb_szPanelName.toString( );}

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mTemplOrig,
         //:               InternalEntityStructure, InternalAttribStructure, szPanelName )
         StoreStringInRecord( mTemplOrig, InternalEntityStructure, InternalAttribStructure, szPanelName );
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
//:dPanelUpdateName( VIEW mTemplOrig BASED ON LOD mTempl,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   VIEW mTempl2 BASED ON LOD mTempl
public int 
omTempl_dPanelUpdateName( View     mTemplOrig,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   zVIEW    mTempl2 = new zVIEW( );
   //:STRING ( 50 ) szPanelName
   String   szPanelName = null;
   //:STRING ( 10 ) szPanelNumber
   String   szPanelNumber = null;
   //:INTEGER       lPanelNumber
   int      lPanelNumber = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Currently the Panel Name is just the Panel Number.
         //:CreateViewFromView( mTempl2, mTemplOrig )
         CreateViewFromView( mTempl2, mTemplOrig );
         //:lPanelNumber = 1
         lPanelNumber = 1;
         //:SET CURSOR PREVIOUS mTempl2.TemplatePanel
         RESULT = SetCursorPrevEntity( mTempl2, "TemplatePanel", "" );
         //:LOOP WHILE RESULT >= zCURSOR_SET
         while ( RESULT >= zCURSOR_SET )
         { 
            //:lPanelNumber = lPanelNumber + 1
            lPanelNumber = lPanelNumber + 1;
            //:SET CURSOR PREVIOUS mTempl2.TemplatePanel
            RESULT = SetCursorPrevEntity( mTempl2, "TemplatePanel", "" );
         } 

         //:END
         //:DropView( mTempl2 )
         DropView( mTempl2 );
         //:szPanelNumber = lPanelNumber
          {StringBuilder sb_szPanelNumber;
         if ( szPanelNumber == null )
            sb_szPanelNumber = new StringBuilder( 32 );
         else
            sb_szPanelNumber = new StringBuilder( szPanelNumber );
                  ZeidonStringConvertFromNumber( sb_szPanelNumber, 1, 0, 10, lPanelNumber, (double) 0.0, "I" );
         szPanelNumber = sb_szPanelNumber.toString( );}
         //:szPanelName = "Page " + szPanelNumber
          {StringBuilder sb_szPanelName;
         if ( szPanelName == null )
            sb_szPanelName = new StringBuilder( 32 );
         else
            sb_szPanelName = new StringBuilder( szPanelName );
                  ZeidonStringCopy( sb_szPanelName, 1, 0, "Page ", 1, 0, 51 );
         szPanelName = sb_szPanelName.toString( );}
          {StringBuilder sb_szPanelName;
         if ( szPanelName == null )
            sb_szPanelName = new StringBuilder( 32 );
         else
            sb_szPanelName = new StringBuilder( szPanelName );
                  ZeidonStringConcat( sb_szPanelName, 1, 0, szPanelNumber, 1, 0, 51 );
         szPanelName = sb_szPanelName.toString( );}

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mTemplOrig,
         //:               InternalEntityStructure, InternalAttribStructure, szPanelName )
         StoreStringInRecord( mTemplOrig, InternalEntityStructure, InternalAttribStructure, szPanelName );
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
//:dBlockName( VIEW mTemplOrig BASED ON LOD mTempl,
//:            STRING ( 32 ) InternalEntityStructure,
//:            STRING ( 32 ) InternalAttribStructure,
//:            SHORT GetOrSetFlag )

//:   VIEW mTempl  BASED ON LOD mTempl
public int 
omTempl_dBlockName( View     mTemplOrig,
                    String InternalEntityStructure,
                    String InternalAttribStructure,
                    Integer   GetOrSetFlag )
{
   zVIEW    mTempl = new zVIEW( );
   //:VIEW mTempl2 BASED ON LOD mTempl
   zVIEW    mTempl2 = new zVIEW( );
   //:STRING ( 50 ) szGroupName
   String   szGroupName = null;
   //:STRING ( 10 ) szGroupNumber
   String   szGroupNumber = null;
   //:INTEGER       lGroupNumber
   int      lGroupNumber = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Currently the Group Name is just the Group Number.
         //:// We will use the named view in memory for creating mTempl2, as the view in the operation may be hierarchical.
         //:GET VIEW mTempl NAMED "mTempl"
         RESULT = GetViewByName( mTempl, "mTempl", mTemplOrig, zLEVEL_TASK );
         //:CreateViewFromView( mTempl2, mTempl )
         CreateViewFromView( mTempl2, mTempl );
         //:mTempl.TemplateGroup.wCurrentFlag = "Y"
         SetAttributeFromString( mTempl, "TemplateGroup", "wCurrentFlag", "Y" );
         //:FOR EACH mTempl2.TemplateGroup
         RESULT = SetCursorFirstEntity( mTempl2, "TemplateGroup", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:lGroupNumber = lGroupNumber + 1
            lGroupNumber = lGroupNumber + 1;
            //:IF mTempl2.TemplateGroup.wCurrentFlag = "Y"
            if ( CompareAttributeToString( mTempl2, "TemplateGroup", "wCurrentFlag", "Y" ) == 0 )
            { 
               //:szGroupNumber = lGroupNumber
                {StringBuilder sb_szGroupNumber;
               if ( szGroupNumber == null )
                  sb_szGroupNumber = new StringBuilder( 32 );
               else
                  sb_szGroupNumber = new StringBuilder( szGroupNumber );
                              ZeidonStringConvertFromNumber( sb_szGroupNumber, 1, 0, 10, lGroupNumber, (double) 0.0, "I" );
               szGroupNumber = sb_szGroupNumber.toString( );}
               //:szGroupName = "Group " + szGroupNumber
                {StringBuilder sb_szGroupName;
               if ( szGroupName == null )
                  sb_szGroupName = new StringBuilder( 32 );
               else
                  sb_szGroupName = new StringBuilder( szGroupName );
                              ZeidonStringCopy( sb_szGroupName, 1, 0, "Group ", 1, 0, 51 );
               szGroupName = sb_szGroupName.toString( );}
                {StringBuilder sb_szGroupName;
               if ( szGroupName == null )
                  sb_szGroupName = new StringBuilder( 32 );
               else
                  sb_szGroupName = new StringBuilder( szGroupName );
                              ZeidonStringConcat( sb_szGroupName, 1, 0, szGroupNumber, 1, 0, 51 );
               szGroupName = sb_szGroupName.toString( );}
               //:mTempl.TemplateGroup.wCurrentFlag = ""
               SetAttributeFromString( mTempl, "TemplateGroup", "wCurrentFlag", "" );
            } 

            RESULT = SetCursorNextEntity( mTempl2, "TemplateGroup", "" );
            //:END
         } 

         //:END
         //:DropView( mTempl2 )
         DropView( mTempl2 );

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mTemplOrig,
         //:               InternalEntityStructure, InternalAttribStructure, szGroupName )
         StoreStringInRecord( mTemplOrig, InternalEntityStructure, InternalAttribStructure, szGroupName );
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
//:dKerning( VIEW mTempl BASED ON LOD mTempl,
//:          STRING ( 32 ) InternalEntityStructure,
//:          STRING ( 32 ) InternalAttribStructure,
//:          SHORT GetOrSetFlag )

//:   STRING ( 16 ) szKerningWidth
public int 
omTempl_dKerning( View     mTempl,
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
         //:IF mTempl.TemplatePanel.KerningWidth = 0.0
         if ( CompareAttributeToDecimal( mTempl, "TemplatePanel", "KerningWidth", (double) 0.0 ) == 0 )
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
            //:szKerningWidth = mTempl.TemplatePanel.KerningWidth
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szKerningWidth;
            if ( szKerningWidth == null )
               sb_szKerningWidth = new StringBuilder( 32 );
            else
               sb_szKerningWidth = new StringBuilder( szKerningWidth );
                         GetVariableFromAttribute( sb_szKerningWidth, mi_lTempInteger_0, 'S', 17, mTempl, "TemplatePanel", "KerningWidth", "", 0 );
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
            //:szKerningWidth = szKerningWidth + mTempl.TemplatePanel.KerningUnits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mTempl, "TemplatePanel", "KerningUnits", "", 0 );
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
         //:StoreStringInRecord( mTempl,
         //:               InternalEntityStructure, InternalAttribStructure, szKerningWidth )
         StoreStringInRecord( mTempl, InternalEntityStructure, InternalAttribStructure, szKerningWidth );
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
//:dWordSpacing( VIEW mTempl BASED ON LOD mTempl,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )

//:   STRING ( 16 ) szWordSpacing
public int 
omTempl_dWordSpacing( View     mTempl,
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
         //:IF mTempl.TemplatePanel.WordSpacingWidth = 0.0
         if ( CompareAttributeToDecimal( mTempl, "TemplatePanel", "WordSpacingWidth", (double) 0.0 ) == 0 )
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
            //:szWordSpacing = mTempl.TemplatePanel.WordSpacingWidth
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szWordSpacing;
            if ( szWordSpacing == null )
               sb_szWordSpacing = new StringBuilder( 32 );
            else
               sb_szWordSpacing = new StringBuilder( szWordSpacing );
                         GetVariableFromAttribute( sb_szWordSpacing, mi_lTempInteger_0, 'S', 17, mTempl, "TemplatePanel", "WordSpacingWidth", "", 0 );
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
            //:szWordSpacing = szWordSpacing + mTempl.TemplatePanel.WordSpacingUnits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mTempl, "TemplatePanel", "WordSpacingUnits", "", 0 );
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
         //:StoreStringInRecord( mTempl,
         //:               InternalEntityStructure, InternalAttribStructure, szWordSpacing )
         StoreStringInRecord( mTempl, InternalEntityStructure, InternalAttribStructure, szWordSpacing );
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
//:dLeading( VIEW mTempl BASED ON LOD mTempl,
//:          STRING ( 32 ) InternalEntityStructure,
//:          STRING ( 32 ) InternalAttribStructure,
//:          SHORT GetOrSetFlag )

//:   STRING ( 16 ) szLeadingHeight
public int 
omTempl_dLeading( View     mTempl,
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
         //:IF mTempl.TemplatePanel.LeadingHeight = 0.0
         if ( CompareAttributeToDecimal( mTempl, "TemplatePanel", "LeadingHeight", (double) 0.0 ) == 0 )
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
            //:szLeadingHeight = mTempl.TemplatePanel.LeadingHeight
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szLeadingHeight;
            if ( szLeadingHeight == null )
               sb_szLeadingHeight = new StringBuilder( 32 );
            else
               sb_szLeadingHeight = new StringBuilder( szLeadingHeight );
                         GetVariableFromAttribute( sb_szLeadingHeight, mi_lTempInteger_0, 'S', 17, mTempl, "TemplatePanel", "LeadingHeight", "", 0 );
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
            //:szLeadingHeight = szLeadingHeight + mTempl.TemplatePanel.LeadingUnits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mTempl, "TemplatePanel", "LeadingUnits", "", 0 );
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
         //:StoreStringInRecord( mTempl,
         //:               InternalEntityStructure, InternalAttribStructure, szLeadingHeight )
         StoreStringInRecord( mTempl, InternalEntityStructure, InternalAttribStructure, szLeadingHeight );
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
//:dTitleLeadingDefault( VIEW mTempl BASED ON LOD mTempl,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )

//:   STRING ( 16 ) szTitleLeadingHeightDefault
public int 
omTempl_dTitleLeadingDefault( View     mTempl,
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
         //:IF mTempl.TemplatePanel.TitleLeadingHeightDefault = 0.0
         if ( CompareAttributeToDecimal( mTempl, "TemplatePanel", "TitleLeadingHeightDefault", (double) 0.0 ) == 0 )
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
            //:szTitleLeadingHeightDefault = mTempl.TemplatePanel.TitleLeadingHeightDefault
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTitleLeadingHeightDefault;
            if ( szTitleLeadingHeightDefault == null )
               sb_szTitleLeadingHeightDefault = new StringBuilder( 32 );
            else
               sb_szTitleLeadingHeightDefault = new StringBuilder( szTitleLeadingHeightDefault );
                         GetVariableFromAttribute( sb_szTitleLeadingHeightDefault, mi_lTempInteger_0, 'S', 17, mTempl, "TemplatePanel", "TitleLeadingHeightDefault", "", 0 );
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
            //:szTitleLeadingHeightDefault = szTitleLeadingHeightDefault + mTempl.TemplatePanel.LeadingUnits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mTempl, "TemplatePanel", "LeadingUnits", "", 0 );
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
         //:StoreStringInRecord( mTempl,
         //:               InternalEntityStructure, InternalAttribStructure, szTitleLeadingHeightDefault )
         StoreStringInRecord( mTempl, InternalEntityStructure, InternalAttribStructure, szTitleLeadingHeightDefault );
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
//:dStmtLeadingDefault( VIEW mTempl BASED ON LOD mTempl,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   STRING ( 16 ) szStmtLeadingHeightDefault
public int 
omTempl_dStmtLeadingDefault( View     mTempl,
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

         //:// Add Leading Units to Leading Height if the Height is not 0.
         //:IF mTempl.TemplatePanel.StatementLeadingHeightDefault = 0.0
         if ( CompareAttributeToDecimal( mTempl, "TemplatePanel", "StatementLeadingHeightDefault", (double) 0.0 ) == 0 )
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
            //:szStmtLeadingHeightDefault = mTempl.TemplatePanel.StatementLeadingHeightDefault
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szStmtLeadingHeightDefault;
            if ( szStmtLeadingHeightDefault == null )
               sb_szStmtLeadingHeightDefault = new StringBuilder( 32 );
            else
               sb_szStmtLeadingHeightDefault = new StringBuilder( szStmtLeadingHeightDefault );
                         GetVariableFromAttribute( sb_szStmtLeadingHeightDefault, mi_lTempInteger_0, 'S', 17, mTempl, "TemplatePanel", "StatementLeadingHeightDefault", "", 0 );
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
            //:szStmtLeadingHeightDefault = szStmtLeadingHeightDefault + mTempl.TemplatePanel.LeadingUnits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mTempl, "TemplatePanel", "LeadingUnits", "", 0 );
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
         //:StoreStringInRecord( mTempl,
         //:               InternalEntityStructure, InternalAttribStructure, szStmtLeadingHeightDefault )
         StoreStringInRecord( mTempl, InternalEntityStructure, InternalAttribStructure, szStmtLeadingHeightDefault );
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


//:TRANSFORMATION OPERATION
//:BuildDisplayStructure( VIEW mTemplIn BASED ON LOD mTempl )

//:   VIEW mTempl BASED ON LOD mTempl
public int 
omTempl_BuildDisplayStructure( View     mTemplIn )
{
   zVIEW    mTempl = new zVIEW( );
   //:VIEW mTemplHier BASED ON LOD  mTempl
   zVIEW    mTemplHier = new zVIEW( );
   //:STRING ( 20 ) szPanelName
   String   szPanelName = null;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 50 ) szSectionType
   String   szSectionType = null;
   //:INTEGER       lTempID  // used to set the work attribute in the Templ entity and the EntityID in
   int      lTempID = 0;
   //:                       // the DisplayStatement entity so we can get to original Templ entity
   //:INTEGER       lCnt
   int      lCnt = 0;
   //:SHORT         lLevel
   int      lLevel = 0;
   //:SHORT         nRC
   int      nRC = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_2 = null;
   String   szTempString_3 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_4 = null;


   //:CreateViewFromView( mTempl, mTemplIn )  // try to hold onto current position
   CreateViewFromView( mTempl, mTemplIn );

   //:// Remove any old entries.
   //:FOR EACH mTempl.DisplayStatement
   RESULT = SetCursorFirstEntity( mTempl, "DisplayStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mTempl.DisplayStatement NONE
      RESULT = DeleteEntity( mTempl, "DisplayStatement", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mTempl, "DisplayStatement", "" );
   } 

   //:END

   //:// Set the Panel Count number.
   //:lCnt = 0
   lCnt = 0;
   //:FOR EACH mTempl.TemplatePanel
   RESULT = SetCursorFirstEntity( mTempl, "TemplatePanel", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:lCnt = lCnt + 1
      lCnt = lCnt + 1;
      //:mTempl.TemplatePanel.wSequentialPanelNumber = lCnt
      SetAttributeFromInteger( mTempl, "TemplatePanel", "wSequentialPanelNumber", lCnt );
      RESULT = SetCursorNextEntity( mTempl, "TemplatePanel", "" );
   } 

   //:END

   //:// Build the DisplayStatement structure, which is a single list of all Panel, Group and Section entries.
   //:SET CURSOR FIRST mTempl.TemplatePanel
   RESULT = SetCursorFirstEntity( mTempl, "TemplatePanel", "" );
   //:CreateViewFromView( mTemplHier, mTempl )
   CreateViewFromView( mTemplHier, mTempl );
   //:DefineHierarchicalCursor( mTemplHier, "Template" )
   DefineHierarchicalCursor( mTemplHier, "Template" );
   //:lTempID = 0
   lTempID = 0;
   //:nRC = SetCursorNextEntityHierarchical( lLevel, szEntityName, mTemplHier )
   {StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
   MutableInt mi_lLevel = new MutableInt( lLevel );
       nRC = SetCursorNextEntityHierarchical( mi_lLevel, sb_szEntityName, mTemplHier );
   szEntityName = sb_szEntityName.toString( );
   lLevel = mi_lLevel.intValue( );}
   //:LOOP WHILE nRC >= zCURSOR_SET
   while ( nRC >= zCURSOR_SET )
   { 

      //:lTempID = lTempID + 1
      lTempID = lTempID + 1;
      //:IF szEntityName = "TemplatePanel"
      if ( ZeidonStringCompare( szEntityName, 1, 0, "TemplatePanel", 1, 0, 33 ) == 0 )
      { 
         //:// PANEL
         //:CREATE ENTITY mTempl.DisplayStatement
         RESULT = CreateEntity( mTempl, "DisplayStatement", zPOS_AFTER );
         //:mTempl.DisplayStatement.EntityType = "Panel"
         SetAttributeFromString( mTempl, "DisplayStatement", "EntityType", "Panel" );
         //:mTemplHier.TemplatePanel.wTempID = lTempID
         SetAttributeFromInteger( mTemplHier, "TemplatePanel", "wTempID", lTempID );
         //:mTempl.DisplayStatement.EntityID = lTempID
         SetAttributeFromInteger( mTempl, "DisplayStatement", "EntityID", lTempID );
         //:mTempl.DisplayStatement.DisplayText = "Panel:   " + mTemplHier.TemplatePanel.dPanelName
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_0, 'S', 255, mTemplHier, "TemplatePanel", "dPanelName", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringCopy( sb_szTempString_0, 1, 0, "Panel:   ", 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
         SetAttributeFromString( mTempl, "DisplayStatement", "DisplayText", szTempString_0 );
         //:ELSE
      } 
      else
      { 
         //:IF szEntityName = "TemplateGroup"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "TemplateGroup", 1, 0, 33 ) == 0 )
         { 
            //:// GROUP
            //:CREATE ENTITY mTempl.DisplayStatement
            RESULT = CreateEntity( mTempl, "DisplayStatement", zPOS_AFTER );
            //:mTempl.DisplayStatement.EntityType = "Group"
            SetAttributeFromString( mTempl, "DisplayStatement", "EntityType", "Group" );
            //:mTemplHier.TemplateGroup.wTempID = lTempID
            SetAttributeFromInteger( mTemplHier, "TemplateGroup", "wTempID", lTempID );
            //:mTempl.DisplayStatement.EntityID = lTempID
            SetAttributeFromInteger( mTempl, "DisplayStatement", "EntityID", lTempID );
            //:mTempl.DisplayStatement.DisplayText = "..Group:   " + mTemplHier.TemplateGroup.Name
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                         GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_1, 'S', 129, mTemplHier, "TemplateGroup", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_3 = sb_szTempString_3.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringCopy( sb_szTempString_2, 1, 0, "..Group:   ", 1, 0, 255 );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringConcat( sb_szTempString_2, 1, 0, szTempString_3, 1, 0, 255 );
            szTempString_2 = sb_szTempString_2.toString( );}
            SetAttributeFromString( mTempl, "DisplayStatement", "DisplayText", szTempString_2 );
            //:ELSE
         } 
         else
         { 
            //:IF szEntityName = "TemplateSection"
            if ( ZeidonStringCompare( szEntityName, 1, 0, "TemplateSection", 1, 0, 33 ) == 0 )
            { 
               //:// SECTION
               //:CREATE ENTITY mTempl.DisplayStatement
               RESULT = CreateEntity( mTempl, "DisplayStatement", zPOS_AFTER );
               //:mTempl.DisplayStatement.EntityType = "Section"
               SetAttributeFromString( mTempl, "DisplayStatement", "EntityType", "Section" );
               //:GetStringFromAttributeByContext( szSectionType, mTemplHier, "TemplateSection", "SectionType", "", 50 )
               {StringBuilder sb_szSectionType;
               if ( szSectionType == null )
                  sb_szSectionType = new StringBuilder( 32 );
               else
                  sb_szSectionType = new StringBuilder( szSectionType );
                               GetStringFromAttributeByContext( sb_szSectionType, mTemplHier, "TemplateSection", "SectionType", "", 50 );
               szSectionType = sb_szSectionType.toString( );}
               //:mTemplHier.TemplateSection.wTempID = lTempID
               SetAttributeFromInteger( mTemplHier, "TemplateSection", "wTempID", lTempID );
               //:mTempl.DisplayStatement.EntityID = lTempID
               SetAttributeFromInteger( mTempl, "DisplayStatement", "EntityID", lTempID );
               //:mTempl.DisplayStatement.DisplayText = "....Section:      " + szSectionType
                {StringBuilder sb_szTempString_4;
               if ( szTempString_4 == null )
                  sb_szTempString_4 = new StringBuilder( 32 );
               else
                  sb_szTempString_4 = new StringBuilder( szTempString_4 );
                              ZeidonStringCopy( sb_szTempString_4, 1, 0, "....Section:      ", 1, 0, 255 );
               szTempString_4 = sb_szTempString_4.toString( );}
                {StringBuilder sb_szTempString_4;
               if ( szTempString_4 == null )
                  sb_szTempString_4 = new StringBuilder( 32 );
               else
                  sb_szTempString_4 = new StringBuilder( szTempString_4 );
                              ZeidonStringConcat( sb_szTempString_4, 1, 0, szSectionType, 1, 0, 255 );
               szTempString_4 = sb_szTempString_4.toString( );}
               SetAttributeFromString( mTempl, "DisplayStatement", "DisplayText", szTempString_4 );
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:nRC = SetCursorNextEntityHierarchical( lLevel, szEntityName, mTemplHier )
      {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
      MutableInt mi_lLevel = new MutableInt( lLevel );
             nRC = SetCursorNextEntityHierarchical( mi_lLevel, sb_szEntityName, mTemplHier );
      szEntityName = sb_szEntityName.toString( );
      lLevel = mi_lLevel.intValue( );}
   } 

   //:END

   //:DropHierarchicalCursor( mTemplHier )
   DropHierarchicalCursor( mTemplHier );
   //:DropView( mTemplHier )
   DropView( mTemplHier );
   //:DropView( mTempl )
   DropView( mTempl );
   return( 0 );
// // SET CURSOR FIRST mTempl.TemplatePanel    we are trying to hold onto position
//    // Save generated ID for next assignment.
//    //lTempID = lTempID + 1
//    //mTempl.Template.wNextTempID = lTempID
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dStatementKerning( VIEW mTempl BASED ON LOD mTempl,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   STRING ( 16 ) szStatementKerning
public int 
omTempl_dStatementKerning( View     mTempl,
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
         //:IF mTempl.TemplateSection.KerningWidth = 0.0
         if ( CompareAttributeToDecimal( mTempl, "TemplateSection", "KerningWidth", (double) 0.0 ) == 0 )
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
            //:szStatementKerning = mTempl.TemplateSection.KerningWidth
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szStatementKerning;
            if ( szStatementKerning == null )
               sb_szStatementKerning = new StringBuilder( 32 );
            else
               sb_szStatementKerning = new StringBuilder( szStatementKerning );
                         GetVariableFromAttribute( sb_szStatementKerning, mi_lTempInteger_0, 'S', 17, mTempl, "TemplateSection", "KerningWidth", "", 0 );
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
            //:szStatementKerning = szStatementKerning + mTempl.TemplateSection.KerningUnits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mTempl, "TemplateSection", "KerningUnits", "", 0 );
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
         //:StoreStringInRecord( mTempl,
         //:               InternalEntityStructure, InternalAttribStructure, szStatementKerning )
         StoreStringInRecord( mTempl, InternalEntityStructure, InternalAttribStructure, szStatementKerning );
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
//:dStatementLeading( VIEW mTempl BASED ON LOD mTempl,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   STRING ( 16 ) szStatementLeading
public int 
omTempl_dStatementLeading( View     mTempl,
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
         //:IF mTempl.TemplateSection.LeadingHeight = 0.0
         if ( CompareAttributeToDecimal( mTempl, "TemplateSection", "LeadingHeight", (double) 0.0 ) == 0 )
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
            //:szStatementLeading = mTempl.TemplateSection.LeadingHeight
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szStatementLeading;
            if ( szStatementLeading == null )
               sb_szStatementLeading = new StringBuilder( 32 );
            else
               sb_szStatementLeading = new StringBuilder( szStatementLeading );
                         GetVariableFromAttribute( sb_szStatementLeading, mi_lTempInteger_0, 'S', 17, mTempl, "TemplateSection", "LeadingHeight", "", 0 );
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
            //:szStatementLeading = szStatementLeading + mTempl.TemplateSection.LeadingUnits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mTempl, "TemplateSection", "LeadingUnits", "", 0 );
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
         //:StoreStringInRecord( mTempl,
         //:               InternalEntityStructure, InternalAttribStructure, szStatementLeading )
         StoreStringInRecord( mTempl, InternalEntityStructure, InternalAttribStructure, szStatementLeading );
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
