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

/**
   @author QuinSoft
**/

public class lMLCATgt_Object extends VmlObjectOperations
{
   public lMLCATgt_Object( View view )
   {
      super( view );
   }


//:TRANSFORMATION OPERATION
//:BuildDifferencesMLC( VIEW lMLCATgt BASED ON LOD lMLCATgt,
//:                     VIEW lMLCASrc BASED ON LOD lMLCASrc )

//:   STRING ( 20 ) szUsageType
public int 
olMLCATgt_BuildDifferencesMLC( View     lMLCATgt,
                               View     lMLCASrc )
{
   String   szUsageType = null;
   //:STRING ( 20 ) szPercent
   String   szPercent = null;
   //:STRING ( 50 ) szSortTitle
   String   szSortTitle = null;
   //:INTEGER       lSortEntryNbr
   int      lSortEntryNbr = 0;
   //:INTEGER       lSortSectionNbr
   int      lSortSectionNbr = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_6 = 0;
   String   szTempString_3 = null;
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   String   szTempString_6 = null;
   String   szTempString_7 = null;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   String   szTempString_8 = null;
   String   szTempString_9 = null;
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


   //:// Build a difference subobject in the Target object that shows the differences between the two MLCs.

   //:// Sorting: Note that we use SetDifferencesSortVs operation to set sort values that will put all the Difference entries for a Section
   //:// together even though they were constructed separately.

   //:// GENERAL

   //:// First Process each Target entry against the corresponding Source entry.
   //:FOR EACH lMLCATgt.M_GeneralSection
   RESULT = SetCursorFirstEntity( lMLCATgt, "M_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// Title
      //:CREATE ENTITY lMLCATgt.ComparisonDifference
      RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
      //:lMLCATgt.ComparisonDifference.Title = "General: " + lMLCATgt.M_GeneralSection.Title
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
             GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_0, 'S', 255, lMLCATgt, "M_GeneralSection", "Title", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szTempString_1 = sb_szTempString_1.toString( );}
       {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
            ZeidonStringCopy( sb_szTempString_0, 1, 0, "General: ", 1, 0, 1026 );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
            ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 1026 );
      szTempString_0 = sb_szTempString_0.toString( );}
      SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", szTempString_0 );
      //:lMLCATgt.ComparisonDifference.TitleFlag = "Y"
      SetAttributeFromString( lMLCATgt, "ComparisonDifference", "TitleFlag", "Y" );
      //:lSortSectionNbr = lSortSectionNbr + 1
      lSortSectionNbr = lSortSectionNbr + 1;
      //:lMLCATgt.M_GeneralSection.wSectionSortOrder     = lSortSectionNbr
      SetAttributeFromInteger( lMLCATgt, "M_GeneralSection", "wSectionSortOrder", lSortSectionNbr );
      //:lMLCATgt.ComparisonDifference.SortSectionNumber = lSortSectionNbr
      SetAttributeFromInteger( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lSortSectionNbr );
      //:szSortTitle = "1General" //+ lMLCATgt.ComparisonDifference.Title
       {StringBuilder sb_szSortTitle;
      if ( szSortTitle == null )
         sb_szSortTitle = new StringBuilder( 32 );
      else
         sb_szSortTitle = new StringBuilder( szSortTitle );
            ZeidonStringCopy( sb_szSortTitle, 1, 0, "1General", 1, 0, 51 );
      szSortTitle = sb_szSortTitle.toString( );}
      //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
      olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );

      //:IF lMLCATgt.MP_GeneralSection EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( lMLCATgt, "MP_GeneralSection" );
      if ( lTempInteger_1 == 0 )
      { 
         //:// This section was created from a Source, so see if Title and Statement match.
         //:IF lMLCATgt.M_GeneralSection.Title != lMLCATgt.MP_GeneralSection.Title
         if ( CompareAttributeToAttribute( lMLCATgt, "M_GeneralSection", "Title", lMLCATgt, "MP_GeneralSection", "Title" ) != 0 )
         { 
            //:// Section Title has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...General Section Title"
            SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...General Section Title" );
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_GeneralSection.Title
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_GeneralSection", "Title" );
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCATgt.MP_GeneralSection.Title
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCATgt, "MP_GeneralSection", "Title" );
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_GeneralSection.wSectionSortOrder
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_GeneralSection", "wSectionSortOrder" );
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:FOR EACH lMLCATgt.M_GeneralStatement
         RESULT = SetCursorFirstEntity( lMLCATgt, "M_GeneralStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lMLCATgt.MP_GeneralStatement EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( lMLCATgt, "MP_GeneralStatement" );
            if ( lTempInteger_2 == 0 )
            { 
               //:IF lMLCATgt.M_GeneralStatement.Text != lMLCATgt.MP_GeneralStatement.Text
               if ( CompareAttributeToAttribute( lMLCATgt, "M_GeneralStatement", "Text", lMLCATgt, "MP_GeneralStatement", "Text" ) != 0 )
               { 
                  //:// Statement Text has changed
                  //:CREATE ENTITY lMLCATgt.ComparisonDifference
                  RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
                  //:lMLCATgt.ComparisonDifference.Title                = "...General Statement Text"
                  SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...General Statement Text" );
                  //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_GeneralStatement.Text
                  SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_GeneralStatement", "Text" );
                  //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCATgt.MP_GeneralStatement.Text
                  SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCATgt, "MP_GeneralStatement", "Text" );
                  //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_GeneralSection.wSectionSortOrder
                  SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_GeneralSection", "wSectionSortOrder" );
                  //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
                  olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// Statement is new.
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...General Statement Text"
               SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...General Statement Text" );
               //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_GeneralStatement.Text
               SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_GeneralStatement", "Text" );
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_GeneralSection.wSectionSortOrder
               SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_GeneralSection", "wSectionSortOrder" );
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lMLCATgt, "M_GeneralStatement", "" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// This section is new (NOT created from a Source), so list each Title and Statement text as differences.
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...General Section Title"
         SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...General Section Title" );
         //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_GeneralSection.Title
         SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_GeneralSection", "Title" );
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:FOR EACH lMLCATgt.M_GeneralStatement
         RESULT = SetCursorFirstEntity( lMLCATgt, "M_GeneralStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...General Statement Text"
            SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...General Statement Text" );
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_GeneralStatement.Text
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_GeneralStatement", "Text" );
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_GeneralSection.wSectionSortOrder
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_GeneralSection", "wSectionSortOrder" );
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lMLCATgt, "M_GeneralStatement", "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lMLCATgt, "M_GeneralSection", "" );
      //:END
   } 

   //:END
   //:// Process each source Section and Statement that doesn't have a corresponding Target entry.
   //:FOR EACH lMLCASrc.M_GeneralSection
   RESULT = SetCursorFirstEntity( lMLCASrc, "M_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF lMLCASrc.MN_GeneralSection EXISTS
      lTempInteger_3 = CheckExistenceOfEntity( lMLCASrc, "MN_GeneralSection" );
      if ( lTempInteger_3 == 0 )
      { 
         //:// Section exists in Target, check for each Statement.
         //:FOR EACH lMLCASrc.M_GeneralStatement
         RESULT = SetCursorFirstEntity( lMLCASrc, "M_GeneralStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lMLCASrc.MN_GeneralStatement DOES NOT EXIST
            lTempInteger_4 = CheckExistenceOfEntity( lMLCASrc, "MN_GeneralStatement" );
            if ( lTempInteger_4 != 0 )
            { 
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...General Statement Text"
               SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...General Statement Text" );
               //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_GeneralStatement.Text
               SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCASrc, "M_GeneralStatement", "Text" );
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_GeneralSection.wSectionSortOrder
               SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_GeneralSection", "wSectionSortOrder" );
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lMLCASrc, "M_GeneralStatement", "" );
            //:END
         } 

         //:END

         //:ELSE
      } 
      else
      { 
         //:// Section and Statements do not exist in in Target.
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...General Section Title"
         SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...General Section Title" );
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_GeneralSection.Title
         SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCASrc, "M_GeneralSection", "Title" );
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_GeneralSection.wSectionSortOrder
         SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_GeneralSection", "wSectionSortOrder" );
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:FOR EACH lMLCASrc.M_GeneralStatement
         RESULT = SetCursorFirstEntity( lMLCASrc, "M_GeneralStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...General Statement Text"
            SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...General Statement Text" );
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_GeneralStatement.Text
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCASrc, "M_GeneralStatement", "Text" );
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_GeneralSection.wSectionSortOrder
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_GeneralSection", "wSectionSortOrder" );
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lMLCASrc, "M_GeneralStatement", "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lMLCASrc, "M_GeneralSection", "" );
      //:END
   } 

   //:END

   //:// INGREDIENTS

   //:// Ingredients differences are only in Statement ChemicalName and Percent.
   //:// Title
   //:CREATE ENTITY lMLCATgt.ComparisonDifference
   RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
   //:lMLCATgt.ComparisonDifference.Title = "Ingredients"
   SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "Ingredients" );
   //:lSortSectionNbr = lSortSectionNbr + 1
   lSortSectionNbr = lSortSectionNbr + 1;
   //:lMLCATgt.M_IngredientsSection.wSectionSortOrder = lSortSectionNbr
   SetAttributeFromInteger( lMLCATgt, "M_IngredientsSection", "wSectionSortOrder", lSortSectionNbr );
   //:lMLCATgt.ComparisonDifference.SortSectionNumber = lSortSectionNbr
   SetAttributeFromInteger( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lSortSectionNbr );
   //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, "2Ingredients" )
   olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, "2Ingredients" );
   //:lMLCATgt.ComparisonDifference.TitleFlag = "Y"
   SetAttributeFromString( lMLCATgt, "ComparisonDifference", "TitleFlag", "Y" );
   //:szSortTitle = "2Ingredients" //+ lMLCATgt.ComparisonDifference.Title
    {StringBuilder sb_szSortTitle;
   if ( szSortTitle == null )
      sb_szSortTitle = new StringBuilder( 32 );
   else
      sb_szSortTitle = new StringBuilder( szSortTitle );
      ZeidonStringCopy( sb_szSortTitle, 1, 0, "2Ingredients", 1, 0, 51 );
   szSortTitle = sb_szSortTitle.toString( );}

   //:// First check Target.
   //:FOR EACH lMLCATgt.M_IngredientsStatement
   RESULT = SetCursorFirstEntity( lMLCATgt, "M_IngredientsStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// SystemChemical Name
      //:IF lMLCATgt.MP_IngredientsStatement EXISTS
      lTempInteger_5 = CheckExistenceOfEntity( lMLCATgt, "MP_IngredientsStatement" );
      if ( lTempInteger_5 == 0 )
      { 
         //:// SystemChemical Name
         //:IF lMLCATgt.M_IngredientsStatement.ChemicalName != lMLCATgt.MP_IngredientsStatement.ChemicalName
         if ( CompareAttributeToAttribute( lMLCATgt, "M_IngredientsStatement", "ChemicalName", lMLCATgt, "MP_IngredientsStatement", "ChemicalName" ) != 0 )
         { 
            //:// SystemChemical Name has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Ingredient SystemChemical"
            SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Ingredient SystemChemical" );
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_IngredientsStatement.ChemicalName
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_IngredientsStatement", "ChemicalName" );
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCATgt.MP_IngredientsStatement.ChemicalName
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCATgt, "MP_IngredientsStatement", "ChemicalName" );
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_IngredientsSection.wSectionSortOrder
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_IngredientsSection", "wSectionSortOrder" );
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:// Percent
         //:IF lMLCATgt.M_IngredientsStatement.Percent != lMLCATgt.MP_IngredientsStatement.Percent
         if ( CompareAttributeToAttribute( lMLCATgt, "M_IngredientsStatement", "Percent", lMLCATgt, "MP_IngredientsStatement", "Percent" ) != 0 )
         { 
            //:// Percent has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Ingredient Percent"
            SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Ingredient Percent" );
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_IngredientsStatement.Percent
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_IngredientsStatement", "Percent" );
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCATgt.MP_IngredientsStatement.Percent
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCATgt, "MP_IngredientsStatement", "Percent" );
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_IngredientsSection.wSectionSortOrder
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_IngredientsSection", "wSectionSortOrder" );
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Statement does not exist in Source.
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Ingredient/Percent"
         SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Ingredient/Percent" );
         //:GetStringFromAttributeByContext( szPercent, lMLCATgt, "M_IngredientsStatement", "Percent", "", 20 )
         {StringBuilder sb_szPercent;
         if ( szPercent == null )
            sb_szPercent = new StringBuilder( 32 );
         else
            sb_szPercent = new StringBuilder( szPercent );
                   GetStringFromAttributeByContext( sb_szPercent, lMLCATgt, "M_IngredientsStatement", "Percent", "", 20 );
         szPercent = sb_szPercent.toString( );}
         //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_IngredientsStatement.ChemicalName + ": " + szPercent
         {StringBuilder sb_szTempString_2;
         if ( szTempString_2 == null )
            sb_szTempString_2 = new StringBuilder( 32 );
         else
            sb_szTempString_2 = new StringBuilder( szTempString_2 );
                   GetStringFromAttribute( sb_szTempString_2, lMLCATgt, "M_IngredientsStatement", "ChemicalName" );
         szTempString_2 = sb_szTempString_2.toString( );}
          {StringBuilder sb_szTempString_2;
         if ( szTempString_2 == null )
            sb_szTempString_2 = new StringBuilder( 32 );
         else
            sb_szTempString_2 = new StringBuilder( szTempString_2 );
                  ZeidonStringConcat( sb_szTempString_2, 1, 0, ": ", 1, 0, 255 );
         szTempString_2 = sb_szTempString_2.toString( );}
          {StringBuilder sb_szTempString_2;
         if ( szTempString_2 == null )
            sb_szTempString_2 = new StringBuilder( 32 );
         else
            sb_szTempString_2 = new StringBuilder( szTempString_2 );
                  ZeidonStringConcat( sb_szTempString_2, 1, 0, szPercent, 1, 0, 255 );
         szTempString_2 = sb_szTempString_2.toString( );}
         SetAttributeFromString( lMLCATgt, "ComparisonDifference", "TargetStatementValue", szTempString_2 );
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_IngredientsSection.wSectionSortOrder
         SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_IngredientsSection", "wSectionSortOrder" );
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
      } 

      RESULT = SetCursorNextEntity( lMLCATgt, "M_IngredientsStatement", "" );
      //:END
   } 

   //:END
   //:// Next check Source
   //:FOR EACH lMLCASrc.M_IngredientsStatement
   RESULT = SetCursorFirstEntity( lMLCASrc, "M_IngredientsStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF lMLCASrc.MN_IngredientsStatement DOES NOT EXIST
      lTempInteger_6 = CheckExistenceOfEntity( lMLCASrc, "MN_IngredientsStatement" );
      if ( lTempInteger_6 != 0 )
      { 
         //:// Statement does not exist in Target.
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Ingredient/Percent"
         SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Ingredient/Percent" );
         //:GetStringFromAttributeByContext( szPercent, lMLCASrc, "M_IngredientsStatement", "Percent", "", 20 )
         {StringBuilder sb_szPercent;
         if ( szPercent == null )
            sb_szPercent = new StringBuilder( 32 );
         else
            sb_szPercent = new StringBuilder( szPercent );
                   GetStringFromAttributeByContext( sb_szPercent, lMLCASrc, "M_IngredientsStatement", "Percent", "", 20 );
         szPercent = sb_szPercent.toString( );}
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_IngredientsStatement.ChemicalName + ": " + szPercent
         {StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                   GetStringFromAttribute( sb_szTempString_3, lMLCASrc, "M_IngredientsStatement", "ChemicalName" );
         szTempString_3 = sb_szTempString_3.toString( );}
          {StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                  ZeidonStringConcat( sb_szTempString_3, 1, 0, ": ", 1, 0, 255 );
         szTempString_3 = sb_szTempString_3.toString( );}
          {StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                  ZeidonStringConcat( sb_szTempString_3, 1, 0, szPercent, 1, 0, 255 );
         szTempString_3 = sb_szTempString_3.toString( );}
         SetAttributeFromString( lMLCATgt, "ComparisonDifference", "SourceStatementValue", szTempString_3 );
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_IngredientsSection.wSectionSortOrder
         SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_IngredientsSection", "wSectionSortOrder" );
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
      } 

      RESULT = SetCursorNextEntity( lMLCASrc, "M_IngredientsStatement", "" );
      //:END
   } 

   //:END

   //:// USAGE STATEMENTS

   //:// List any Usage statements that are in Target but not is Source and vice versa.
   //:// First check Target Usages.

   //:// Title
   //:CREATE ENTITY lMLCATgt.ComparisonDifference
   RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
   //:lMLCATgt.ComparisonDifference.Title = "Usages"
   SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "Usages" );
   //:szSortTitle = "Usages" //+ lMLCATgt.ComparisonDifference.Title
    {StringBuilder sb_szSortTitle;
   if ( szSortTitle == null )
      sb_szSortTitle = new StringBuilder( 32 );
   else
      sb_szSortTitle = new StringBuilder( szSortTitle );
      ZeidonStringCopy( sb_szSortTitle, 1, 0, "Usages", 1, 0, 51 );
   szSortTitle = sb_szSortTitle.toString( );}
   //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
   olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
   //:lMLCATgt.ComparisonDifference.TitleFlag = "Y"
   SetAttributeFromString( lMLCATgt, "ComparisonDifference", "TitleFlag", "Y" );

   //:FOR EACH lMLCATgt.M_Usage
   RESULT = SetCursorFirstEntity( lMLCATgt, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST lMLCASrc.M_Usage
      //:           WHERE lMLCASrc.M_Usage.UsageType = lMLCATgt.M_Usage.UsageType
      //:             AND lMLCASrc.M_Usage.Name = lMLCATgt.M_Usage.Name
      RESULT = SetCursorFirstEntity( lMLCASrc, "M_Usage", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lMLCASrc, "M_Usage", "UsageType", lMLCATgt, "M_Usage", "UsageType" ) != 0 || CompareAttributeToAttribute( lMLCASrc, "M_Usage", "Name", lMLCATgt, "M_Usage", "Name" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( lMLCASrc, "M_Usage", "" );
         } 

      } 

      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:GetStringFromAttributeByContext( szUsageType, lMLCATgt, "M_Usage", "UsageType", "", 20 )
         {StringBuilder sb_szUsageType;
         if ( szUsageType == null )
            sb_szUsageType = new StringBuilder( 32 );
         else
            sb_szUsageType = new StringBuilder( szUsageType );
                   GetStringFromAttributeByContext( sb_szUsageType, lMLCATgt, "M_Usage", "UsageType", "", 20 );
         szUsageType = sb_szUsageType.toString( );}
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "..." + szUsageType
          {StringBuilder sb_szTempString_4;
         if ( szTempString_4 == null )
            sb_szTempString_4 = new StringBuilder( 32 );
         else
            sb_szTempString_4 = new StringBuilder( szTempString_4 );
                  ZeidonStringCopy( sb_szTempString_4, 1, 0, "...", 1, 0, 255 );
         szTempString_4 = sb_szTempString_4.toString( );}
          {StringBuilder sb_szTempString_4;
         if ( szTempString_4 == null )
            sb_szTempString_4 = new StringBuilder( 32 );
         else
            sb_szTempString_4 = new StringBuilder( szTempString_4 );
                  ZeidonStringConcat( sb_szTempString_4, 1, 0, szUsageType, 1, 0, 255 );
         szTempString_4 = sb_szTempString_4.toString( );}
         SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", szTempString_4 );
         //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_Usage.Name
         SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_Usage", "Name" );
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
      } 

      RESULT = SetCursorNextEntity( lMLCATgt, "M_Usage", "" );
      //:END
   } 

   //:END

   //:// Next check Source Usages.
   //:FOR EACH lMLCASrc.M_Usage
   RESULT = SetCursorFirstEntity( lMLCASrc, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST lMLCATgt.M_Usage
      //:           WHERE lMLCATgt.M_Usage.UsageType = lMLCASrc.M_Usage.UsageType
      //:             AND lMLCATgt.M_Usage.Name = lMLCASrc.M_Usage.Name
      RESULT = SetCursorFirstEntity( lMLCATgt, "M_Usage", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lMLCATgt, "M_Usage", "UsageType", lMLCASrc, "M_Usage", "UsageType" ) != 0 || CompareAttributeToAttribute( lMLCATgt, "M_Usage", "Name", lMLCASrc, "M_Usage", "Name" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( lMLCATgt, "M_Usage", "" );
         } 

      } 

      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:GetStringFromAttributeByContext( szUsageType, lMLCASrc, "M_Usage", "UsageType", "", 20 )
         {StringBuilder sb_szUsageType;
         if ( szUsageType == null )
            sb_szUsageType = new StringBuilder( 32 );
         else
            sb_szUsageType = new StringBuilder( szUsageType );
                   GetStringFromAttributeByContext( sb_szUsageType, lMLCASrc, "M_Usage", "UsageType", "", 20 );
         szUsageType = sb_szUsageType.toString( );}
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "..." + szUsageType
          {StringBuilder sb_szTempString_5;
         if ( szTempString_5 == null )
            sb_szTempString_5 = new StringBuilder( 32 );
         else
            sb_szTempString_5 = new StringBuilder( szTempString_5 );
                  ZeidonStringCopy( sb_szTempString_5, 1, 0, "...", 1, 0, 255 );
         szTempString_5 = sb_szTempString_5.toString( );}
          {StringBuilder sb_szTempString_5;
         if ( szTempString_5 == null )
            sb_szTempString_5 = new StringBuilder( 32 );
         else
            sb_szTempString_5 = new StringBuilder( szTempString_5 );
                  ZeidonStringConcat( sb_szTempString_5, 1, 0, szUsageType, 1, 0, 255 );
         szTempString_5 = sb_szTempString_5.toString( );}
         SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", szTempString_5 );
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_Usage.Name
         SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCASrc, "M_Usage", "Name" );
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
      } 

      RESULT = SetCursorNextEntity( lMLCASrc, "M_Usage", "" );
      //:END
   } 

   //:END

   //:// STORAGE AND DISPOSAL

   //:// First Process each Target entry against the corresponding Source entry.
   //:FOR EACH lMLCATgt.M_StorageDisposalSection
   RESULT = SetCursorFirstEntity( lMLCATgt, "M_StorageDisposalSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// Title
      //:CREATE ENTITY lMLCATgt.ComparisonDifference
      RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
      //:lMLCATgt.ComparisonDifference.Title = "Storage + Disposal: " + lMLCATgt.M_StorageDisposalSection.Title
      {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
      StringBuilder sb_szTempString_7;
      if ( szTempString_7 == null )
         sb_szTempString_7 = new StringBuilder( 32 );
      else
         sb_szTempString_7 = new StringBuilder( szTempString_7 );
             GetVariableFromAttribute( sb_szTempString_7, mi_lTempInteger_7, 'S', 255, lMLCATgt, "M_StorageDisposalSection", "Title", "", 0 );
      lTempInteger_7 = mi_lTempInteger_7.intValue( );
      szTempString_7 = sb_szTempString_7.toString( );}
       {StringBuilder sb_szTempString_6;
      if ( szTempString_6 == null )
         sb_szTempString_6 = new StringBuilder( 32 );
      else
         sb_szTempString_6 = new StringBuilder( szTempString_6 );
            ZeidonStringCopy( sb_szTempString_6, 1, 0, "Storage + Disposal: ", 1, 0, 255 );
      szTempString_6 = sb_szTempString_6.toString( );}
       {StringBuilder sb_szTempString_6;
      if ( szTempString_6 == null )
         sb_szTempString_6 = new StringBuilder( 32 );
      else
         sb_szTempString_6 = new StringBuilder( szTempString_6 );
            ZeidonStringConcat( sb_szTempString_6, 1, 0, szTempString_7, 1, 0, 255 );
      szTempString_6 = sb_szTempString_6.toString( );}
      SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", szTempString_6 );
      //:lMLCATgt.ComparisonDifference.TitleFlag = "Y"
      SetAttributeFromString( lMLCATgt, "ComparisonDifference", "TitleFlag", "Y" );
      //:lSortSectionNbr = lSortSectionNbr + 1
      lSortSectionNbr = lSortSectionNbr + 1;
      //:lMLCATgt.M_StorageDisposalSection.wSectionSortOrder = lSortSectionNbr
      SetAttributeFromInteger( lMLCATgt, "M_StorageDisposalSection", "wSectionSortOrder", lSortSectionNbr );
      //:lMLCATgt.ComparisonDifference.SortSectionNumber     = lSortSectionNbr
      SetAttributeFromInteger( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lSortSectionNbr );
      //:szSortTitle = "3Storage + Disposal" //+ lMLCATgt.ComparisonDifference.Title
       {StringBuilder sb_szSortTitle;
      if ( szSortTitle == null )
         sb_szSortTitle = new StringBuilder( 32 );
      else
         sb_szSortTitle = new StringBuilder( szSortTitle );
            ZeidonStringCopy( sb_szSortTitle, 1, 0, "3Storage + Disposal", 1, 0, 51 );
      szSortTitle = sb_szSortTitle.toString( );}
      //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
      olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );

      //:IF lMLCATgt.MP_StorageDisposalSection EXISTS
      lTempInteger_8 = CheckExistenceOfEntity( lMLCATgt, "MP_StorageDisposalSection" );
      if ( lTempInteger_8 == 0 )
      { 
         //:// This section was created from a Source, so see if Title and Statement match.
         //:IF lMLCATgt.M_StorageDisposalSection.Title != lMLCATgt.MP_StorageDisposalSection.Title
         if ( CompareAttributeToAttribute( lMLCATgt, "M_StorageDisposalSection", "Title", lMLCATgt, "MP_StorageDisposalSection", "Title" ) != 0 )
         { 
            //:// Section Title has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Storage Disposal Section Title"
            SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Storage Disposal Section Title" );
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_StorageDisposalSection.Title
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_StorageDisposalSection", "Title" );
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCATgt.MP_StorageDisposalSection.Title
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCATgt, "MP_StorageDisposalSection", "Title" );
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_StorageDisposalSection.wSectionSortOrder
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_StorageDisposalSection", "wSectionSortOrder" );
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:FOR EACH lMLCATgt.M_StorageDisposalStatement
         RESULT = SetCursorFirstEntity( lMLCATgt, "M_StorageDisposalStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lMLCATgt.MP_StorageDisposalStatement EXISTS
            lTempInteger_9 = CheckExistenceOfEntity( lMLCATgt, "MP_StorageDisposalStatement" );
            if ( lTempInteger_9 == 0 )
            { 
               //:IF lMLCATgt.M_StorageDisposalStatement.Text != lMLCATgt.MP_StorageDisposalStatement.Text
               if ( CompareAttributeToAttribute( lMLCATgt, "M_StorageDisposalStatement", "Text", lMLCATgt, "MP_StorageDisposalStatement", "Text" ) != 0 )
               { 
                  //:// Statement Text has changed
                  //:CREATE ENTITY lMLCATgt.ComparisonDifference
                  RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
                  //:lMLCATgt.ComparisonDifference.Title                = "...Storage Disposal Statement Text"
                  SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Storage Disposal Statement Text" );
                  //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_StorageDisposalStatement.Text
                  SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_StorageDisposalStatement", "Text" );
                  //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCATgt.MP_StorageDisposalStatement.Text
                  SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCATgt, "MP_StorageDisposalStatement", "Text" );
                  //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_StorageDisposalSection.wSectionSortOrder
                  SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_StorageDisposalSection", "wSectionSortOrder" );
                  //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
                  olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// Statement is new.
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Storage Disposal Statement Text"
               SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Storage Disposal Statement Text" );
               //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_StorageDisposalStatement.Text
               SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_StorageDisposalStatement", "Text" );
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_StorageDisposalSection.wSectionSortOrder
               SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_StorageDisposalSection", "wSectionSortOrder" );
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lMLCATgt, "M_StorageDisposalStatement", "" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// This section is new (NOT created from a Source), so list each Title and Statement text as differences.
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Storage Disposal Section Title"
         SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Storage Disposal Section Title" );
         //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_StorageDisposalSection.Title
         SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_StorageDisposalSection", "Title" );
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_StorageDisposalSection.wSectionSortOrder
         SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_StorageDisposalSection", "wSectionSortOrder" );
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:FOR EACH lMLCATgt.M_StorageDisposalStatement
         RESULT = SetCursorFirstEntity( lMLCATgt, "M_StorageDisposalStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Storage Disposal Statement Text"
            SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Storage Disposal Statement Text" );
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_StorageDisposalStatement.Text
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_StorageDisposalStatement", "Text" );
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_StorageDisposalSection.wSectionSortOrder
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_StorageDisposalSection", "wSectionSortOrder" );
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lMLCATgt, "M_StorageDisposalStatement", "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lMLCATgt, "M_StorageDisposalSection", "" );
      //:END
   } 

   //:END
   //:// Process each source Section and Statement that doesn't have a corresponding Target entry.
   //:FOR EACH lMLCASrc.M_StorageDisposalSection
   RESULT = SetCursorFirstEntity( lMLCASrc, "M_StorageDisposalSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF lMLCASrc.MN_StorageDisposalSection EXISTS
      lTempInteger_10 = CheckExistenceOfEntity( lMLCASrc, "MN_StorageDisposalSection" );
      if ( lTempInteger_10 == 0 )
      { 
         //:// Section exists in Target, check for each Statement.
         //:FOR EACH lMLCASrc.M_StorageDisposalStatement
         RESULT = SetCursorFirstEntity( lMLCASrc, "M_StorageDisposalStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lMLCASrc.MN_StorageDisposalStatement DOES NOT EXIST
            lTempInteger_11 = CheckExistenceOfEntity( lMLCASrc, "MN_StorageDisposalStatement" );
            if ( lTempInteger_11 != 0 )
            { 
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Storage Disposal Statement Text"
               SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Storage Disposal Statement Text" );
               //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_StorageDisposalStatement.Text
               SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCASrc, "M_StorageDisposalStatement", "Text" );
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_StorageDisposalSection.wSectionSortOrder
               SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_StorageDisposalSection", "wSectionSortOrder" );
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lMLCASrc, "M_StorageDisposalStatement", "" );
            //:END
         } 

         //:END

         //:ELSE
      } 
      else
      { 
         //:// Section and Statements do not exist in in Target.
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Storage Disposal Section Title"
         SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Storage Disposal Section Title" );
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_StorageDisposalSection.Title
         SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCASrc, "M_StorageDisposalSection", "Title" );
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_StorageDisposalSection.wSectionSortOrder
         SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_StorageDisposalSection", "wSectionSortOrder" );
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:FOR EACH lMLCASrc.M_StorageDisposalStatement
         RESULT = SetCursorFirstEntity( lMLCASrc, "M_StorageDisposalStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Storage Disposal Statement Text"
            SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Storage Disposal Statement Text" );
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_StorageDisposalStatement.Text
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCASrc, "M_StorageDisposalStatement", "Text" );
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_StorageDisposalSection.wSectionSortOrder
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_StorageDisposalSection", "wSectionSortOrder" );
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lMLCASrc, "M_StorageDisposalStatement", "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lMLCASrc, "M_StorageDisposalSection", "" );
      //:END
   } 

   //:END

   //:// DIRECTIONS FOR USE

   //:// First Process each Target entry against the corresponding Source entry.
   //:FOR EACH lMLCATgt.M_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( lMLCATgt, "M_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// Title
      //:CREATE ENTITY lMLCATgt.ComparisonDifference
      RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
      //:lMLCATgt.ComparisonDifference.Title = "Directions for Use: " + lMLCATgt.M_DirectionsForUseSection.Title
      {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
      StringBuilder sb_szTempString_9;
      if ( szTempString_9 == null )
         sb_szTempString_9 = new StringBuilder( 32 );
      else
         sb_szTempString_9 = new StringBuilder( szTempString_9 );
             GetVariableFromAttribute( sb_szTempString_9, mi_lTempInteger_12, 'S', 255, lMLCATgt, "M_DirectionsForUseSection", "Title", "", 0 );
      lTempInteger_12 = mi_lTempInteger_12.intValue( );
      szTempString_9 = sb_szTempString_9.toString( );}
       {StringBuilder sb_szTempString_8;
      if ( szTempString_8 == null )
         sb_szTempString_8 = new StringBuilder( 32 );
      else
         sb_szTempString_8 = new StringBuilder( szTempString_8 );
            ZeidonStringCopy( sb_szTempString_8, 1, 0, "Directions for Use: ", 1, 0, 255 );
      szTempString_8 = sb_szTempString_8.toString( );}
       {StringBuilder sb_szTempString_8;
      if ( szTempString_8 == null )
         sb_szTempString_8 = new StringBuilder( 32 );
      else
         sb_szTempString_8 = new StringBuilder( szTempString_8 );
            ZeidonStringConcat( sb_szTempString_8, 1, 0, szTempString_9, 1, 0, 255 );
      szTempString_8 = sb_szTempString_8.toString( );}
      SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", szTempString_8 );
      //:lMLCATgt.ComparisonDifference.TitleFlag = "Y"
      SetAttributeFromString( lMLCATgt, "ComparisonDifference", "TitleFlag", "Y" );
      //:lSortSectionNbr = lSortSectionNbr + 1
      lSortSectionNbr = lSortSectionNbr + 1;
      //:lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder = lSortSectionNbr
      SetAttributeFromInteger( lMLCATgt, "M_DirectionsForUseSection", "wSectionSortOrder", lSortSectionNbr );
      //:lMLCATgt.ComparisonDifference.SortSectionNumber      = lSortSectionNbr
      SetAttributeFromInteger( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lSortSectionNbr );
      //:szSortTitle = "4Directions" //+ lMLCATgt.ComparisonDifference.Title
       {StringBuilder sb_szSortTitle;
      if ( szSortTitle == null )
         sb_szSortTitle = new StringBuilder( 32 );
      else
         sb_szSortTitle = new StringBuilder( szSortTitle );
            ZeidonStringCopy( sb_szSortTitle, 1, 0, "4Directions", 1, 0, 51 );
      szSortTitle = sb_szSortTitle.toString( );}
      //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
      olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );

      //:IF lMLCATgt.MP_DirectionsForUseSection EXISTS
      lTempInteger_13 = CheckExistenceOfEntity( lMLCATgt, "MP_DirectionsForUseSection" );
      if ( lTempInteger_13 == 0 )
      { 
         //:// This section was created from a Source, so see if Title and Statement match.
         //:IF lMLCATgt.M_DirectionsForUseSection.Title != lMLCATgt.MP_DirectionsForUseSection.Title
         if ( CompareAttributeToAttribute( lMLCATgt, "M_DirectionsForUseSection", "Title", lMLCATgt, "MP_DirectionsForUseSection", "Title" ) != 0 )
         { 
            //:// Section Title has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Directions Section Title"
            SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Directions Section Title" );
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_DirectionsForUseSection.Title
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_DirectionsForUseSection", "Title" );
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCATgt.MP_DirectionsForUseSection.Title
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCATgt, "MP_DirectionsForUseSection", "Title" );
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
            SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_DirectionsForUseSection", "wSectionSortOrder" );
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:FOR EACH lMLCATgt.M_DirectionsForUseStatement
         RESULT = SetCursorFirstEntity( lMLCATgt, "M_DirectionsForUseStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lMLCATgt.MP_DirectionsForUseStatement EXISTS
            lTempInteger_14 = CheckExistenceOfEntity( lMLCATgt, "MP_DirectionsForUseStatement" );
            if ( lTempInteger_14 == 0 )
            { 
               //:IF lMLCATgt.M_DirectionsForUseStatement.Text != lMLCATgt.MP_DirectionsForUseStatement.Text
               if ( CompareAttributeToAttribute( lMLCATgt, "M_DirectionsForUseStatement", "Text", lMLCATgt, "MP_DirectionsForUseStatement", "Text" ) != 0 )
               { 
                  //:// Statement Text has changed
                  //:CREATE ENTITY lMLCATgt.ComparisonDifference
                  RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
                  //:lMLCATgt.ComparisonDifference.Title                = "...Directions Statement Text"
                  SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Directions Statement Text" );
                  //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_DirectionsForUseStatement.Text
                  SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_DirectionsForUseStatement", "Text" );
                  //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCATgt.MP_DirectionsForUseStatement.Text
                  SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SourceStatementValue", lMLCATgt, "MP_DirectionsForUseStatement", "Text" );
                  //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
                  SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_DirectionsForUseSection", "wSectionSortOrder" );
                  //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
                  olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// Statement is new.
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Directions Statement Text"
               SetAttributeFromString( lMLCATgt, "ComparisonDifference", "Title", "...Directions Statement Text" );
               //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_DirectionsForUseStatement.Text
               SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "TargetStatementValue", lMLCATgt, "M_DirectionsForUseStatement", "Text" );
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
               SetAttributeFromAttribute( lMLCATgt, "ComparisonDifference", "SortSectionNumber", lMLCATgt, "M_DirectionsForUseSection", "wSectionSortOrder" );
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lMLCATgt, "M_DirectionsForUseStatement", "" );
            //:END
         } 

         //:END
         //:// Target Usages not in Source
         //:SET CURSOR FIRST lMLCASrc.M_DirectionsForUseSection WHERE lMLCASrc.M_DirectionsForUseSection.ID = lMLCATgt.MP_DirectionsForUseSection.ID
         {MutableInt mi_lTempInteger_15 = new MutableInt( lTempInteger_15 );
                   GetIntegerFromAttribute( mi_lTempInteger_15, lMLCATgt, "MP_DirectionsForUseSection", "ID" );
         lTempInteger_15 = mi_lTempInteger_15.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( lMLCASrc, "M_DirectionsForUseSection", "ID", lTempInteger_15, "" );
         //:FOR EACH lMLCATgt.M_DirectionsUsage WITHIN lMLCATgt.M_DirectionsForUseSection
         RESULT = SetCursorFirstEntity( lMLCATgt, "M_DirectionsUsage", "M_DirectionsForUseSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST lMLCASrc.M_DirectionsUsage WITHIN lMLCASrc.M_DirectionsForUseSection
            //:           WHERE lMLCASrc.M_DirectionsUsage.UsageType = lMLCATgt.M_DirectionsUsage.UsageType
            //:             AND lMLCASrc.M_DirectionsUsage.Name = lMLCATgt.M_DirectionsUsage.Name
            RESULT = SetCursorFirstEntity( lMLCASrc, "M_DirectionsUsage", "M_DirectionsForUseSection" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lMLCASrc, "M_DirectionsUsage", "UsageType", lMLCATgt, "M_DirectionsUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( lMLCASrc, "M_DirectionsUsage", "Name", lMLCATgt, "M_DirectionsUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( lMLCASrc, "M_DirectionsUsage", "M_DirectionsForUseSection" );
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:GetStringFromAttributeByContext( szUsageType, lMLCATgt, "M_DirectionsUsage", "UsageType", "", 20 )
               {StringBuilder sb_szUsageType;
               if ( szUsageType == null )
                  sb_szUsageType = new StringBuilder( 32 );
               else
                  sb_szUsageType = new StringBuilder( szUsageType );
                               GetStringFromAttributeByContext( sb_szUsageType, lMLCATgt, "M_DirectionsUsage", "UsageType", "", 20 );
               szUsageType = sb_szUsageType.toString( );}
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Directions " + szUsageType
               //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_DirectionsUsage.Name
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lMLCATgt, "M_DirectionsUsage", "M_DirectionsForUseSection" );
            //:END
         } 

         //:END
         //:// Source Usages not in Target
         //:FOR EACH lMLCASrc.M_DirectionsUsage WITHIN lMLCASrc.M_DirectionsForUseSection
         RESULT = SetCursorFirstEntity( lMLCASrc, "M_DirectionsUsage", "M_DirectionsForUseSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST lMLCATgt.M_DirectionsUsage WITHIN lMLCATgt.M_DirectionsForUseSection
            //:           WHERE lMLCATgt.M_DirectionsUsage.UsageType = lMLCASrc.M_DirectionsUsage.UsageType
            //:             AND lMLCATgt.M_DirectionsUsage.Name = lMLCASrc.M_DirectionsUsage.Name
            RESULT = SetCursorFirstEntity( lMLCATgt, "M_DirectionsUsage", "M_DirectionsForUseSection" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lMLCATgt, "M_DirectionsUsage", "UsageType", lMLCASrc, "M_DirectionsUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( lMLCATgt, "M_DirectionsUsage", "Name", lMLCASrc, "M_DirectionsUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( lMLCATgt, "M_DirectionsUsage", "M_DirectionsForUseSection" );
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:GetStringFromAttributeByContext( szUsageType, lMLCASrc, "M_DirectionsUsage", "UsageType", "", 20 )
               {StringBuilder sb_szUsageType;
               if ( szUsageType == null )
                  sb_szUsageType = new StringBuilder( 32 );
               else
                  sb_szUsageType = new StringBuilder( szUsageType );
                               GetStringFromAttributeByContext( sb_szUsageType, lMLCASrc, "M_DirectionsUsage", "UsageType", "", 20 );
               szUsageType = sb_szUsageType.toString( );}
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Directions " + szUsageType
               //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_DirectionsUsage.Name
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lMLCASrc, "M_DirectionsUsage", "M_DirectionsForUseSection" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// This section is new (NOT created from the Source MLC), so list each Title and Statement text as differences.
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...DirectionsForUse Section Title"
         //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_DirectionsForUseSection.Title
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:FOR EACH lMLCATgt.M_DirectionsForUseStatement
         RESULT = SetCursorFirstEntity( lMLCATgt, "M_DirectionsForUseStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Directions Statement Text"
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_DirectionsForUseStatement.Text
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lMLCATgt, "M_DirectionsForUseStatement", "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lMLCATgt, "M_DirectionsForUseSection", "" );
      //:END
   } 

   //:END
   //:// Process each source Section and Statement that doesn't have a corresponding Target entry.
   //:FOR EACH lMLCASrc.M_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( lMLCASrc, "M_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF lMLCASrc.MN_DirectionsForUseSection EXISTS
      lTempInteger_16 = CheckExistenceOfEntity( lMLCASrc, "MN_DirectionsForUseSection" );
      if ( lTempInteger_16 == 0 )
      { 
         //:// Section exists in Target, check for each Statement.
         //:FOR EACH lMLCASrc.M_DirectionsForUseStatement
         RESULT = SetCursorFirstEntity( lMLCASrc, "M_DirectionsForUseStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lMLCASrc.MN_DirectionsForUseStatement DOES NOT EXIST
            lTempInteger_17 = CheckExistenceOfEntity( lMLCASrc, "MN_DirectionsForUseStatement" );
            if ( lTempInteger_17 != 0 )
            { 
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Directions Statement Text"
               //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_DirectionsForUseStatement.Text
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lMLCASrc, "M_DirectionsForUseStatement", "" );
            //:END
         } 

         //:END

         //:ELSE
      } 
      else
      { 
         //:// Section and Statements do not exist in in Target.
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Directions Section Title"
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_DirectionsForUseSection.Title
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:FOR EACH lMLCASrc.M_DirectionsForUseStatement
         RESULT = SetCursorFirstEntity( lMLCASrc, "M_DirectionsForUseStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Directions Statement Text"
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_DirectionsForUseStatement.Text
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lMLCASrc, "M_DirectionsForUseStatement", "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lMLCASrc, "M_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// MARKETING

   //:// First Process each Target entry against the corresponding Source entry.
   //:FOR EACH lMLCATgt.M_MarketingSection
   RESULT = SetCursorFirstEntity( lMLCATgt, "M_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// Title
      //:CREATE ENTITY lMLCATgt.ComparisonDifference
      RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
      //:lMLCATgt.ComparisonDifference.Title = "Marketing: " + lMLCATgt.M_MarketingSection.Title
      //:lMLCATgt.ComparisonDifference.TitleFlag = "Y"
      //:lSortSectionNbr = lSortSectionNbr + 1
      //:lMLCATgt.M_MarketingSection.wSectionSortOrder   = lSortSectionNbr
      //:lMLCATgt.ComparisonDifference.SortSectionNumber = lSortSectionNbr
      //:szSortTitle = "5Marketing" //+ lMLCATgt.ComparisonDifference.Title
      //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
      olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );

      //:IF lMLCATgt.MP_MarketingSection EXISTS
      lTempInteger_18 = CheckExistenceOfEntity( lMLCATgt, "MP_MarketingSection" );
      if ( lTempInteger_18 == 0 )
      { 
         //:// This section was created from a Source, so see if Title and Statement match.
         //:IF lMLCATgt.M_MarketingSection.Title != lMLCATgt.MP_MarketingSection.Title
         if ( CompareAttributeToAttribute( lMLCATgt, "M_MarketingSection", "Title", lMLCATgt, "MP_MarketingSection", "Title" ) != 0 )
         { 
            //:// Section Title has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Marketing Section Title"
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_MarketingSection.Title
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCATgt.MP_MarketingSection.Title
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:FOR EACH lMLCATgt.M_MarketingStatement
         RESULT = SetCursorFirstEntity( lMLCATgt, "M_MarketingStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lMLCATgt.MP_MarketingStatement EXISTS
            lTempInteger_19 = CheckExistenceOfEntity( lMLCATgt, "MP_MarketingStatement" );
            if ( lTempInteger_19 == 0 )
            { 
               //:IF lMLCATgt.M_MarketingStatement.Text != lMLCATgt.MP_MarketingStatement.Text
               if ( CompareAttributeToAttribute( lMLCATgt, "M_MarketingStatement", "Text", lMLCATgt, "MP_MarketingStatement", "Text" ) != 0 )
               { 
                  //:// Statement Text has changed
                  //:CREATE ENTITY lMLCATgt.ComparisonDifference
                  RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
                  //:lMLCATgt.ComparisonDifference.Title                = "....Marketing Statement Text"
                  //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_MarketingStatement.Text
                  //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCATgt.MP_MarketingStatement.Text
                  //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
                  //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
                  olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// Statement is new.
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Marketing Statement Text"
               //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_MarketingStatement.Text
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lMLCATgt, "M_MarketingStatement", "" );
            //:END
         } 

         //:END
         //:// Target Usages not in Source
         //:SET CURSOR FIRST lMLCASrc.M_MarketingSection WHERE lMLCASrc.M_MarketingSection.ID = lMLCATgt.MP_MarketingSection.ID
         {MutableInt mi_lTempInteger_20 = new MutableInt( lTempInteger_20 );
                   GetIntegerFromAttribute( mi_lTempInteger_20, lMLCATgt, "MP_MarketingSection", "ID" );
         lTempInteger_20 = mi_lTempInteger_20.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( lMLCASrc, "M_MarketingSection", "ID", lTempInteger_20, "" );
         //:FOR EACH lMLCATgt.M_MarketingUsage WITHIN lMLCATgt.M_MarketingSection
         RESULT = SetCursorFirstEntity( lMLCATgt, "M_MarketingUsage", "M_MarketingSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST lMLCASrc.M_MarketingUsage WITHIN lMLCASrc.M_MarketingSection
            //:           WHERE lMLCASrc.M_MarketingUsage.UsageType = lMLCATgt.M_MarketingUsage.UsageType
            //:             AND lMLCASrc.M_MarketingUsage.Name = lMLCATgt.M_MarketingUsage.Name
            RESULT = SetCursorFirstEntity( lMLCASrc, "M_MarketingUsage", "M_MarketingSection" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lMLCASrc, "M_MarketingUsage", "UsageType", lMLCATgt, "M_MarketingUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( lMLCASrc, "M_MarketingUsage", "Name", lMLCATgt, "M_MarketingUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( lMLCASrc, "M_MarketingUsage", "M_MarketingSection" );
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:GetStringFromAttributeByContext( szUsageType, lMLCATgt, "M_MarketingUsage", "UsageType", "", 20 )
               {StringBuilder sb_szUsageType;
               if ( szUsageType == null )
                  sb_szUsageType = new StringBuilder( 32 );
               else
                  sb_szUsageType = new StringBuilder( szUsageType );
                               GetStringFromAttributeByContext( sb_szUsageType, lMLCATgt, "M_MarketingUsage", "UsageType", "", 20 );
               szUsageType = sb_szUsageType.toString( );}
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Marketing " + szUsageType
               //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_MarketingUsage.Name
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lMLCATgt, "M_MarketingUsage", "M_MarketingSection" );
            //:END
         } 

         //:END
         //:// Source Usages not in Target
         //:FOR EACH lMLCASrc.M_MarketingUsage WITHIN lMLCASrc.M_MarketingSection
         RESULT = SetCursorFirstEntity( lMLCASrc, "M_MarketingUsage", "M_MarketingSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST lMLCATgt.M_MarketingUsage WITHIN lMLCATgt.M_MarketingSection
            //:           WHERE lMLCATgt.M_MarketingUsage.UsageType = lMLCASrc.M_MarketingUsage.UsageType
            //:             AND lMLCATgt.M_MarketingUsage.Name = lMLCASrc.M_MarketingUsage.Name
            RESULT = SetCursorFirstEntity( lMLCATgt, "M_MarketingUsage", "M_MarketingSection" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lMLCATgt, "M_MarketingUsage", "UsageType", lMLCASrc, "M_MarketingUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( lMLCATgt, "M_MarketingUsage", "Name", lMLCASrc, "M_MarketingUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( lMLCATgt, "M_MarketingUsage", "M_MarketingSection" );
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:GetStringFromAttributeByContext( szUsageType, lMLCASrc, "M_MarketingUsage", "UsageType", "", 20 )
               {StringBuilder sb_szUsageType;
               if ( szUsageType == null )
                  sb_szUsageType = new StringBuilder( 32 );
               else
                  sb_szUsageType = new StringBuilder( szUsageType );
                               GetStringFromAttributeByContext( sb_szUsageType, lMLCASrc, "M_MarketingUsage", "UsageType", "", 20 );
               szUsageType = sb_szUsageType.toString( );}
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Marketing " + szUsageType
               //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_MarketingUsage.Name
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lMLCASrc, "M_MarketingUsage", "M_MarketingSection" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// This section is new (NOT created from a Source), so list each Title and Statement text as differences.
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Marketing Section Title"
         //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_MarketingSection.Title
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:FOR EACH lMLCATgt.M_MarketingStatement
         RESULT = SetCursorFirstEntity( lMLCATgt, "M_MarketingStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Marketing Statement Text"
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_MarketingStatement.Text
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lMLCATgt, "M_MarketingStatement", "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lMLCATgt, "M_MarketingSection", "" );
      //:END
   } 

   //:END
   //:// Process each source Section and Statement that doesn't have a corresponding Target entry.
   //:FOR EACH lMLCASrc.M_MarketingSection
   RESULT = SetCursorFirstEntity( lMLCASrc, "M_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF lMLCASrc.MN_MarketingSection EXISTS
      lTempInteger_21 = CheckExistenceOfEntity( lMLCASrc, "MN_MarketingSection" );
      if ( lTempInteger_21 == 0 )
      { 
         //:// Section exists in Target, check for each Statement.
         //:FOR EACH lMLCASrc.M_MarketingStatement
         RESULT = SetCursorFirstEntity( lMLCASrc, "M_MarketingStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lMLCASrc.MN_MarketingStatement DOES NOT EXIST
            lTempInteger_22 = CheckExistenceOfEntity( lMLCASrc, "MN_MarketingStatement" );
            if ( lTempInteger_22 != 0 )
            { 
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Marketing Statement Text"
               //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_MarketingStatement.Text
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lMLCASrc, "M_MarketingStatement", "" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Section and Statements do not exist in in Target.
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Marketing Section Title"
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_MarketingSection.Title
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:FOR EACH lMLCASrc.M_MarketingStatement
         RESULT = SetCursorFirstEntity( lMLCASrc, "M_MarketingStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Marketing Statement Text"
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_MarketingStatement.Text
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lMLCASrc, "M_MarketingStatement", "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lMLCASrc, "M_MarketingSection", "" );
      //:END
   } 

   //:END

   //:// HUMAN HAZARD

   //:// First Process each Target entry against the corresponding Source entry.
   //:FOR EACH lMLCATgt.M_HumanHazardSection
   RESULT = SetCursorFirstEntity( lMLCATgt, "M_HumanHazardSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// Title
      //:CREATE ENTITY lMLCATgt.ComparisonDifference
      RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
      //:lMLCATgt.ComparisonDifference.Title = "Human Hazard: " + lMLCATgt.MP_HumanHazardSection.EPA_SignalWord
      //:lMLCATgt.ComparisonDifference.TitleFlag = "Y"
      //:lSortSectionNbr = lSortSectionNbr + 1
      //:lMLCATgt.M_HumanHazardSection.wSectionSortOrder = lSortSectionNbr
      //:lMLCATgt.ComparisonDifference.SortSectionNumber = lSortSectionNbr
      //:szSortTitle = "6Hazards" //+ lMLCATgt.ComparisonDifference.Title
      //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
      olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );

      //:IF lMLCATgt.MP_HumanHazardSection EXISTS
      lTempInteger_23 = CheckExistenceOfEntity( lMLCATgt, "MP_HumanHazardSection" );
      if ( lTempInteger_23 == 0 )
      { 
         //:// This section was created from a Source, so see if EPA_SignalWord and EPA_ChildHazardWarning match.
         //:IF lMLCATgt.M_HumanHazardSection.EPA_SignalWord != lMLCATgt.MP_HumanHazardSection.EPA_SignalWord
         if ( CompareAttributeToAttribute( lMLCATgt, "M_HumanHazardSection", "EPA_SignalWord", lMLCATgt, "MP_HumanHazardSection", "EPA_SignalWord" ) != 0 )
         { 
            //:// Section Title has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Hazards EPA Signal Word"
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_HumanHazardSection.EPA_SignalWord
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCATgt.MP_HumanHazardSection.EPA_SignalWord
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_HumanHazardSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:IF lMLCATgt.M_HumanHazardSection.EPA_ChildHazardWarning != lMLCATgt.MP_HumanHazardSection.EPA_ChildHazardWarning
         if ( CompareAttributeToAttribute( lMLCATgt, "M_HumanHazardSection", "EPA_ChildHazardWarning", lMLCATgt, "MP_HumanHazardSection", "EPA_ChildHazardWarning" ) != 0 )
         { 
            //:// Section Title has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Hazards EPA Warning"
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_HumanHazardSection.EPA_ChildHazardWarning
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCATgt.MP_HumanHazardSection.EPA_ChildHazardWarning
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_HumanHazardSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// This section is new (NOT created from a Source), so list each EPA_SignalWord and EPA_ChildHazardWarning as differences.
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Hazards EPA Signal Word"
         //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_HumanHazardSection.EPA_SignalWord
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_HumanHazardSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Hazards EPA Warning"
         //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_HumanHazardSection.EPA_ChildHazardWarning
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_HumanHazardSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
      } 

      RESULT = SetCursorNextEntity( lMLCATgt, "M_HumanHazardSection", "" );
      //:END
   } 

   //:END
   //:// Process each source Section and Statement that doesn't have a corresponding Target entry.
   //:FOR EACH lMLCASrc.M_HumanHazardSection
   RESULT = SetCursorFirstEntity( lMLCASrc, "M_HumanHazardSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF lMLCASrc.MN_HumanHazardSection DOES NOT EXIST
      lTempInteger_24 = CheckExistenceOfEntity( lMLCASrc, "MN_HumanHazardSection" );
      if ( lTempInteger_24 != 0 )
      { 
         //:// Section and Statements do not exist in in Target.
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Hazards EPA Signal Word"
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_HumanHazardSection.EPA_SignalWord
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_HumanHazardSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Hazards EPA Warning"
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_HumanHazardSection.EPA_ChildHazardWarning
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_HumanHazardSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
      } 

      RESULT = SetCursorNextEntity( lMLCASrc, "M_HumanHazardSection", "" );
      //:END
   } 

   //:END

   //:OrderEntityForView( lMLCATgt, "ComparisonDifference", "SortSectionTitle A SortEntryNumber A" )
   OrderEntityForView( lMLCATgt, "ComparisonDifference", "SortSectionTitle A SortEntryNumber A" );
   //:lMLCATgt.MasterLabelContent.wLastSortNumber = lSortEntryNbr
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildDifferencesSLC( VIEW lMLCATgt BASED ON LOD lMLCATgt,
//:                     VIEW lMLCASrc BASED ON LOD lMLCASrc,
//:                     VIEW lSLCAnal BASED ON LOD lSLCAnal )

//:   STRING ( 20 ) szUsageType
public int 
olMLCATgt_BuildDifferencesSLC( View     lMLCATgt,
                               View     lMLCASrc,
                               View     lSLCAnal )
{
   String   szUsageType = null;
   //:STRING ( 20 ) szPercent
   String   szPercent = null;
   //:STRING ( 50 ) szSortTitle
   String   szSortTitle = null;
   //:INTEGER       lSortEntryNbr
   int      lSortEntryNbr = 0;
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
   int      lTempInteger_25 = 0;
   int      lTempInteger_26 = 0;


   //:// Build a difference subobject in the Target object that shows the differences between an SLC and
   //:// a new MLC. For this analysis, we must progress from the SLC to its related MLC (lMLCASrc) and then to the new MLC (lMLCATgt).
   //:// There are three kinds of differences:
   //:// 1. There are new entries in the new MLC. For this we will run BuildDifferencesMLC and keep just the new entries.
   //:// 2. There are differences between what was used for the SLC and the new MLC.
   //:// 3. There are entries in the SLC that have been deleted from the MLC.

   //:// Sorting: Note that we use SetDifferencesSortVs operation to set sort values that will put all the Difference entries for a Section
   //:// together even though they were constructed separately.

   //:// NEW MLC ENTRIES

   //:// First execute BuildDifferencesMLC to build MLC differences and delete all resulting entries except those for new MLC entries.
   //:BuildDifferencesMLC( lMLCATgt, lMLCASrc )
   olMLCATgt_BuildDifferencesMLC( lMLCATgt, lMLCASrc );
   //:FOR EACH lMLCATgt.ComparisonDifference
   RESULT = SetCursorFirstEntity( lMLCATgt, "ComparisonDifference", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF lMLCATgt.ComparisonDifference.SourceStatementValue != ""
      if ( CompareAttributeToString( lMLCATgt, "ComparisonDifference", "SourceStatementValue", "" ) != 0 )
      { 
         //:DELETE ENTITY lMLCATgt.ComparisonDifference NONE
         RESULT = DeleteEntity( lMLCATgt, "ComparisonDifference", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( lMLCATgt, "ComparisonDifference", "" );
      //:END
   } 

   //:END
   //:lSortEntryNbr = lMLCATgt.MasterLabelContent.wLastSortNumber

   //:// GENERAL

   //:// Compare SLC data to new MLC data through the SLC's associated MLC.
   //:FOR EACH lSLCAnal.S_GeneralSection
   RESULT = SetCursorFirstEntity( lSLCAnal, "S_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Set up sort value so entries in this Operaiton sort with entries created earlier.
      //:szSortTitle = "1General: " //+ lSLCAnal.M_GeneralSection.Title

      //:// Follow SLC Section to related MLC and then to new MLC.
      //:SET CURSOR FIRST lMLCASrc.M_GeneralSection WHERE lMLCASrc.M_GeneralSection.ID = lSLCAnal.M_GeneralSection.ID
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, lSLCAnal, "M_GeneralSection", "ID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lMLCASrc, "M_GeneralSection", "ID", lTempInteger_0, "" );
      //:SET CURSOR FIRST lMLCATgt.M_GeneralSection WHERE lMLCATgt.M_GeneralSection.ID = lMLCASrc.MN_GeneralSection.ID
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, lMLCASrc, "MN_GeneralSection", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lMLCATgt, "M_GeneralSection", "ID", lTempInteger_1, "" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// The SLC Section exists in the new MLC.
         //:IF lMLCATgt.M_GeneralSection.Title != lSLCAnal.S_GeneralSection.Title
         if ( CompareAttributeToAttribute( lMLCATgt, "M_GeneralSection", "Title", lSLCAnal, "S_GeneralSection", "Title" ) != 0 )
         { 
            //:// Section Title has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...General Section Title"
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_GeneralSection.Title
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_GeneralSection.Title
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_GeneralSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:FOR EACH lSLCAnal.S_GeneralStatement
         RESULT = SetCursorFirstEntity( lSLCAnal, "S_GeneralStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST lMLCASrc.M_GeneralStatement WHERE lMLCASrc.M_GeneralStatement.ID = lSLCAnal.M_GeneralStatement.ID
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, lSLCAnal, "M_GeneralStatement", "ID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( lMLCASrc, "M_GeneralStatement", "ID", lTempInteger_2, "" );
            //:IF lMLCASrc.MN_GeneralStatement EXISTS
            lTempInteger_3 = CheckExistenceOfEntity( lMLCASrc, "MN_GeneralStatement" );
            if ( lTempInteger_3 == 0 )
            { 
               //:SET CURSOR FIRST lMLCATgt.M_GeneralStatement WHERE lMLCATgt.M_GeneralStatement.ID = lMLCASrc.MN_GeneralStatement.ID
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                               GetIntegerFromAttribute( mi_lTempInteger_4, lMLCASrc, "MN_GeneralStatement", "ID" );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( lMLCATgt, "M_GeneralStatement", "ID", lTempInteger_4, "" );
               //:IF lMLCATgt.M_GeneralStatement.Text != lSLCAnal.S_GeneralStatement.Text
               if ( CompareAttributeToAttribute( lMLCATgt, "M_GeneralStatement", "Text", lSLCAnal, "S_GeneralStatement", "Text" ) != 0 )
               { 
                  //:// Statement Text has changed
                  //:CREATE ENTITY lMLCATgt.ComparisonDifference
                  RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
                  //:lMLCATgt.ComparisonDifference.Title                = "...General Statement Text"
                  //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_GeneralStatement.Text
                  //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_GeneralStatement.Text
                  //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_GeneralSection.wSectionSortOrder
                  //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
                  olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// SLC Statement has been deleted.
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...General Statement Text"
               //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_GeneralStatement.Text
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_GeneralSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lSLCAnal, "S_GeneralStatement", "" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// The SLC Section DOES NOT exist in the new MLC. (It has been deleted.)
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...General Section Title"
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_GeneralSection.Title
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_GeneralSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:FOR EACH lSLCAnal.S_GeneralStatement
         RESULT = SetCursorFirstEntity( lSLCAnal, "S_GeneralStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...General Statement Text"
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_GeneralStatement.Text
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_GeneralSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lSLCAnal, "S_GeneralStatement", "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lSLCAnal, "S_GeneralSection", "" );
      //:END
   } 

   //:END

   //:// INGREDIENTS

   //:// Ingredients differences are only in Statement ChemicalName and Percent.
   //:// Compare SLC data to new MLC data through the SLC's associated MLC.

   //:// Set up sort value so entries in this Operaiton sort with entries created earlier.
   //:szSortTitle = "2Ingredients"

   //:FOR EACH lSLCAnal.M_IngredientsStatement
   RESULT = SetCursorFirstEntity( lSLCAnal, "M_IngredientsStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Follow SLC Section to related MLC and then to new MLC.
      //:SET CURSOR FIRST lMLCASrc.M_IngredientsSection WHERE lMLCASrc.M_IngredientsSection.ID = lSLCAnal.M_IngredientsSection.ID
      {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
             GetIntegerFromAttribute( mi_lTempInteger_5, lSLCAnal, "M_IngredientsSection", "ID" );
      lTempInteger_5 = mi_lTempInteger_5.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lMLCASrc, "M_IngredientsSection", "ID", lTempInteger_5, "" );
      //:SET CURSOR FIRST lMLCATgt.M_IngredientsSection WHERE lMLCATgt.M_IngredientsSection.ID = lMLCASrc.MN_IngredientsSection.ID
      {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
             GetIntegerFromAttribute( mi_lTempInteger_6, lMLCASrc, "MN_IngredientsSection", "ID" );
      lTempInteger_6 = mi_lTempInteger_6.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lMLCATgt, "M_IngredientsSection", "ID", lTempInteger_6, "" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// The SLC Section exists in the new MLC.
         //:IF lMLCATgt.M_IngredientsSection.ActiveTitle != lSLCAnal.S_IngredientsSection.ActiveTitle
         if ( CompareAttributeToAttribute( lMLCATgt, "M_IngredientsSection", "ActiveTitle", lSLCAnal, "S_IngredientsSection", "ActiveTitle" ) != 0 )
         { 
            //:// Section Title has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Ingredients Section Title"
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_IngredientsSection.ActiveTitle
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_IngredientsSection.ActiveTitle
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_IngredientsSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:FOR EACH lSLCAnal.S_IngredientsStatement
         RESULT = SetCursorFirstEntity( lSLCAnal, "S_IngredientsStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST lMLCASrc.M_IngredientsStatement WHERE lMLCASrc.M_IngredientsStatement.ID = lSLCAnal.M_IngredientsStatement.ID
            {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                         GetIntegerFromAttribute( mi_lTempInteger_7, lSLCAnal, "M_IngredientsStatement", "ID" );
            lTempInteger_7 = mi_lTempInteger_7.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( lMLCASrc, "M_IngredientsStatement", "ID", lTempInteger_7, "" );
            //:IF lMLCASrc.MN_IngredientsStatement EXISTS
            lTempInteger_8 = CheckExistenceOfEntity( lMLCASrc, "MN_IngredientsStatement" );
            if ( lTempInteger_8 == 0 )
            { 
               //:SET CURSOR FIRST lMLCATgt.M_IngredientsStatement WHERE lMLCATgt.M_IngredientsStatement.ID = lMLCASrc.MN_IngredientsStatement.ID
               {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
                               GetIntegerFromAttribute( mi_lTempInteger_9, lMLCASrc, "MN_IngredientsStatement", "ID" );
               lTempInteger_9 = mi_lTempInteger_9.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( lMLCATgt, "M_IngredientsStatement", "ID", lTempInteger_9, "" );
               //:IF lMLCATgt.M_IngredientsStatement.ChemicalName != lSLCAnal.S_IngredientsStatement.ChemicalName
               if ( CompareAttributeToAttribute( lMLCATgt, "M_IngredientsStatement", "ChemicalName", lSLCAnal, "S_IngredientsStatement", "ChemicalName" ) != 0 )
               { 
                  //:// Statement Text has changed
                  //:CREATE ENTITY lMLCATgt.ComparisonDifference
                  RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
                  //:lMLCATgt.ComparisonDifference.Title                = "...Ingredients Statement SystemChemical"
                  //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_IngredientsStatement.ChemicalName
                  //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_IngredientsStatement.ChemicalName
                  //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_IngredientsSection.wSectionSortOrder
                  //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
                  olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
               } 

               //:END
               //:IF lMLCATgt.M_IngredientsStatement.Percent != lSLCAnal.S_IngredientsStatement.Percent
               if ( CompareAttributeToAttribute( lMLCATgt, "M_IngredientsStatement", "Percent", lSLCAnal, "S_IngredientsStatement", "Percent" ) != 0 )
               { 
                  //:// Statement Text has changed
                  //:CREATE ENTITY lMLCATgt.ComparisonDifference
                  RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
                  //:lMLCATgt.ComparisonDifference.Title                = "...Ingredients Statement Percent"
                  //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_IngredientsStatement.Percent
                  //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_IngredientsStatement.Percent
                  //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_IngredientsSection.wSectionSortOrder
                  //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
                  olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// SLC Statement has been deleted.
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Ingredients Statement SystemChemical"
               //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_IngredientsStatement.ChemicalName
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_IngredientsSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Ingredients Statement Percent"
               //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_IngredientsStatement.Percent
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_IngredientsSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lSLCAnal, "S_IngredientsStatement", "" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// The SLC Section DOES NOT exist in the new MLC. (It has been deleted.)
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Ingredients Section Title"
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_IngredientsSection.ActiveTitle
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_IngredientsSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:FOR EACH lSLCAnal.S_IngredientsStatement
         RESULT = SetCursorFirstEntity( lSLCAnal, "S_IngredientsStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Ingredients Statement SystemChemical"
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_IngredientsStatement.ChemicalName
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_IngredientsSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Ingredients Statement Percent"
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_IngredientsStatement.Percent
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_IngredientsSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lSLCAnal, "S_IngredientsStatement", "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lSLCAnal, "M_IngredientsStatement", "" );
      //:END
   } 

   //:END

   //:// USAGE STATEMENTS

   //:// List any Usage statements that are in SLC (which is same as those in source MLC) but not in Target MLC. Those new to
   //:// Target MLC were created in operation, BuildDifferencesMLC.

   //:// Set up sort value so entries in this Operaiton sort with entries created earlier.
   //:szSortTitle = "9Usages"

   //:FOR EACH lMLCASrc.M_Usage
   RESULT = SetCursorFirstEntity( lMLCASrc, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST lMLCATgt.M_Usage
      //:           WHERE lMLCATgt.M_Usage.UsageType = lMLCASrc.M_Usage.UsageType
      //:             AND lMLCATgt.M_Usage.Name = lMLCASrc.M_Usage.Name
      RESULT = SetCursorFirstEntity( lMLCATgt, "M_Usage", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lMLCATgt, "M_Usage", "UsageType", lMLCASrc, "M_Usage", "UsageType" ) != 0 || CompareAttributeToAttribute( lMLCATgt, "M_Usage", "Name", lMLCASrc, "M_Usage", "Name" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( lMLCATgt, "M_Usage", "" );
         } 

      } 

      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:GetStringFromAttributeByContext( szUsageType, lMLCASrc, "M_Usage", "UsageType", "", 20 )
         {StringBuilder sb_szUsageType;
         if ( szUsageType == null )
            sb_szUsageType = new StringBuilder( 32 );
         else
            sb_szUsageType = new StringBuilder( szUsageType );
                   GetStringFromAttributeByContext( sb_szUsageType, lMLCASrc, "M_Usage", "UsageType", "", 20 );
         szUsageType = sb_szUsageType.toString( );}
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "..." + szUsageType
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lMLCASrc.M_Usage.Name
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
      } 

      RESULT = SetCursorNextEntity( lMLCASrc, "M_Usage", "" );
      //:END
   } 

   //:END

   //:// STORAGE AND DISPOSAL

   //:// Compare SLC data to new MLC data through the SLC's associated MLC.
   //:FOR EACH lSLCAnal.S_StorageDisposalSection
   RESULT = SetCursorFirstEntity( lSLCAnal, "S_StorageDisposalSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Set up sort value so entries in this Operaiton sort with entries created earlier.
      //:szSortTitle = "3Storage + Disposal: " //+ lSLCAnal.M_StorageDisposalSection.Title

      //:// Follow SLC Section to related MLC and then to new MLC.
      //:SET CURSOR FIRST lMLCASrc.M_StorageDisposalSection WHERE lMLCASrc.M_StorageDisposalSection.ID = lSLCAnal.M_StorageDisposalSection.ID
      {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
             GetIntegerFromAttribute( mi_lTempInteger_10, lSLCAnal, "M_StorageDisposalSection", "ID" );
      lTempInteger_10 = mi_lTempInteger_10.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lMLCASrc, "M_StorageDisposalSection", "ID", lTempInteger_10, "" );
      //:SET CURSOR FIRST lMLCATgt.M_StorageDisposalSection WHERE lMLCATgt.M_StorageDisposalSection.ID = lMLCASrc.MN_StorageDisposalSection.ID
      {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
             GetIntegerFromAttribute( mi_lTempInteger_11, lMLCASrc, "MN_StorageDisposalSection", "ID" );
      lTempInteger_11 = mi_lTempInteger_11.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lMLCATgt, "M_StorageDisposalSection", "ID", lTempInteger_11, "" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// The SLC Section exists in the new MLC.
         //:IF lMLCATgt.M_StorageDisposalSection.Title != lSLCAnal.S_StorageDisposalSection.Title
         if ( CompareAttributeToAttribute( lMLCATgt, "M_StorageDisposalSection", "Title", lSLCAnal, "S_StorageDisposalSection", "Title" ) != 0 )
         { 
            //:// Section Title has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Storage/Disposal Section Title"
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_StorageDisposalSection.Title
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_StorageDisposalSection.Title
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_StorageDisposalSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:FOR EACH lSLCAnal.S_StorageDisposalStatement
         RESULT = SetCursorFirstEntity( lSLCAnal, "S_StorageDisposalStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST lMLCASrc.M_StorageDisposalStatement WHERE lMLCASrc.M_StorageDisposalStatement.ID = lSLCAnal.M_StorageDisposalStatement.ID
            {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
                         GetIntegerFromAttribute( mi_lTempInteger_12, lSLCAnal, "M_StorageDisposalStatement", "ID" );
            lTempInteger_12 = mi_lTempInteger_12.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( lMLCASrc, "M_StorageDisposalStatement", "ID", lTempInteger_12, "" );
            //:IF lMLCASrc.MN_StorageDisposalStatement EXISTS
            lTempInteger_13 = CheckExistenceOfEntity( lMLCASrc, "MN_StorageDisposalStatement" );
            if ( lTempInteger_13 == 0 )
            { 
               //:SET CURSOR FIRST lMLCATgt.M_StorageDisposalStatement WHERE lMLCATgt.M_StorageDisposalStatement.ID = lMLCASrc.MN_StorageDisposalStatement.ID
               {MutableInt mi_lTempInteger_14 = new MutableInt( lTempInteger_14 );
                               GetIntegerFromAttribute( mi_lTempInteger_14, lMLCASrc, "MN_StorageDisposalStatement", "ID" );
               lTempInteger_14 = mi_lTempInteger_14.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( lMLCATgt, "M_StorageDisposalStatement", "ID", lTempInteger_14, "" );
               //:IF lMLCATgt.M_StorageDisposalStatement.Text != lSLCAnal.S_StorageDisposalStatement.Text
               if ( CompareAttributeToAttribute( lMLCATgt, "M_StorageDisposalStatement", "Text", lSLCAnal, "S_StorageDisposalStatement", "Text" ) != 0 )
               { 
                  //:// Statement Text has changed
                  //:CREATE ENTITY lMLCATgt.ComparisonDifference
                  RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
                  //:lMLCATgt.ComparisonDifference.Title                = "...Storage/Disposal Statement Text"
                  //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_StorageDisposalStatement.Text
                  //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_StorageDisposalStatement.Text
                  //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_StorageDisposalSection.wSectionSortOrder
                  //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
                  olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// SLC Statement has been deleted.
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Storage/Disposal Statement Text"
               //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_StorageDisposalStatement.Text
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_StorageDisposalSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lSLCAnal, "S_StorageDisposalStatement", "" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// The SLC Section DOES NOT exist in the new MLC. (It has been deleted.)
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Storage/Disposal Section Title"
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_StorageDisposalSection.Title
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_StorageDisposalSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:FOR EACH lSLCAnal.S_StorageDisposalStatement
         RESULT = SetCursorFirstEntity( lSLCAnal, "S_StorageDisposalStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Storage/Disposal Statement Text"
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_StorageDisposalStatement.Text
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_StorageDisposalSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lSLCAnal, "S_StorageDisposalStatement", "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lSLCAnal, "S_StorageDisposalSection", "" );
      //:END
   } 

   //:END

   //:// DIRECTIONS FOR USE

   //:// Compare SLC data to new MLC data through the SLC's associated MLC.
   //:FOR EACH lSLCAnal.S_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( lSLCAnal, "S_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Set up sort value so entries in this Operaiton sort with entries created earlier.
      //:szSortTitle = "4Directions" //+ lSLCAnal.M_DirectionsForUseSection.Title

      //:// Follow SLC Section to related MLC and then to new MLC.
      //:SET CURSOR FIRST lMLCASrc.M_DirectionsForUseSection WHERE lMLCASrc.M_DirectionsForUseSection.ID = lSLCAnal.M_DirectionsForUseSection.ID
      {MutableInt mi_lTempInteger_15 = new MutableInt( lTempInteger_15 );
             GetIntegerFromAttribute( mi_lTempInteger_15, lSLCAnal, "M_DirectionsForUseSection", "ID" );
      lTempInteger_15 = mi_lTempInteger_15.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lMLCASrc, "M_DirectionsForUseSection", "ID", lTempInteger_15, "" );
      //:SET CURSOR FIRST lMLCATgt.M_DirectionsForUseSection WHERE lMLCATgt.M_DirectionsForUseSection.ID = lMLCASrc.MN_DirectionsForUseSection.ID
      {MutableInt mi_lTempInteger_16 = new MutableInt( lTempInteger_16 );
             GetIntegerFromAttribute( mi_lTempInteger_16, lMLCASrc, "MN_DirectionsForUseSection", "ID" );
      lTempInteger_16 = mi_lTempInteger_16.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lMLCATgt, "M_DirectionsForUseSection", "ID", lTempInteger_16, "" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// The SLC Section exists in the new MLC.
         //:IF lMLCATgt.M_DirectionsForUseSection.Title != lSLCAnal.S_DirectionsForUseSection.Title
         if ( CompareAttributeToAttribute( lMLCATgt, "M_DirectionsForUseSection", "Title", lSLCAnal, "S_DirectionsForUseSection", "Title" ) != 0 )
         { 
            //:// Section Title has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Directions Section Title"
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_DirectionsForUseSection.Title
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_DirectionsForUseSection.Title
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:FOR EACH lSLCAnal.S_DirectionsForUseStatement
         RESULT = SetCursorFirstEntity( lSLCAnal, "S_DirectionsForUseStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST lMLCASrc.M_DirectionsForUseStatement WHERE lMLCASrc.M_DirectionsForUseStatement.ID = lSLCAnal.M_DirectionsForUseStatement.ID
            {MutableInt mi_lTempInteger_17 = new MutableInt( lTempInteger_17 );
                         GetIntegerFromAttribute( mi_lTempInteger_17, lSLCAnal, "M_DirectionsForUseStatement", "ID" );
            lTempInteger_17 = mi_lTempInteger_17.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( lMLCASrc, "M_DirectionsForUseStatement", "ID", lTempInteger_17, "" );
            //:IF lMLCASrc.MN_DirectionsForUseStatement EXISTS
            lTempInteger_18 = CheckExistenceOfEntity( lMLCASrc, "MN_DirectionsForUseStatement" );
            if ( lTempInteger_18 == 0 )
            { 
               //:SET CURSOR FIRST lMLCATgt.M_DirectionsForUseStatement WHERE lMLCATgt.M_DirectionsForUseStatement.ID = lMLCASrc.MN_DirectionsForUseStatement.ID
               {MutableInt mi_lTempInteger_19 = new MutableInt( lTempInteger_19 );
                               GetIntegerFromAttribute( mi_lTempInteger_19, lMLCASrc, "MN_DirectionsForUseStatement", "ID" );
               lTempInteger_19 = mi_lTempInteger_19.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( lMLCATgt, "M_DirectionsForUseStatement", "ID", lTempInteger_19, "" );
               //:IF lMLCATgt.M_DirectionsForUseStatement.Text != lSLCAnal.S_DirectionsForUseStatement.Text
               if ( CompareAttributeToAttribute( lMLCATgt, "M_DirectionsForUseStatement", "Text", lSLCAnal, "S_DirectionsForUseStatement", "Text" ) != 0 )
               { 
                  //:// Statement Text has changed
                  //:CREATE ENTITY lMLCATgt.ComparisonDifference
                  RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
                  //:lMLCATgt.ComparisonDifference.Title                = "...Directions Statement Text"
                  //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_DirectionsForUseStatement.Text
                  //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_DirectionsForUseStatement.Text
                  //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
                  //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
                  olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// SLC Statement has been deleted.
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Directions Statement Text"
               //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_DirectionsForUseStatement.Text
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lSLCAnal, "S_DirectionsForUseStatement", "" );
            //:END
         } 

         //:END
         //:// Check if any Source Usages are not in new Target. (Those new to Target were processed in BuildDifferencesMLC.)
         //:FOR EACH lSLCAnal.S_DirectionsUsage WITHIN lSLCAnal.S_DirectionsForUseSection
         RESULT = SetCursorFirstEntity( lSLCAnal, "S_DirectionsUsage", "S_DirectionsForUseSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST lMLCATgt.M_DirectionsUsage WITHIN lMLCATgt.M_DirectionsForUseSection
            //:           WHERE lMLCATgt.M_DirectionsUsage.Name = lSLCAnal.S_DirectionsUsage.Name
            //:             AND lMLCATgt.M_DirectionsUsage.UsageType = lSLCAnal.S_DirectionsUsage.UsageType
            RESULT = SetCursorFirstEntity( lMLCATgt, "M_DirectionsUsage", "M_DirectionsForUseSection" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lMLCATgt, "M_DirectionsUsage", "Name", lSLCAnal, "S_DirectionsUsage", "Name" ) != 0 ||
                       CompareAttributeToAttribute( lMLCATgt, "M_DirectionsUsage", "UsageType", lSLCAnal, "S_DirectionsUsage", "UsageType" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( lMLCATgt, "M_DirectionsUsage", "M_DirectionsForUseSection" );
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:GetStringFromAttributeByContext( szUsageType, lSLCAnal, "S_DirectionsUsage", "UsageType", "", 20 )
               {StringBuilder sb_szUsageType;
               if ( szUsageType == null )
                  sb_szUsageType = new StringBuilder( 32 );
               else
                  sb_szUsageType = new StringBuilder( szUsageType );
                               GetStringFromAttributeByContext( sb_szUsageType, lSLCAnal, "S_DirectionsUsage", "UsageType", "", 20 );
               szUsageType = sb_szUsageType.toString( );}
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Directions " + szUsageType
               //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_DirectionsUsage.Name
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lSLCAnal, "S_DirectionsUsage", "S_DirectionsForUseSection" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// The SLC Section DOES NOT exist in the new MLC. (It has been deleted.)
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Directions Section Title"
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_DirectionsForUseSection.Title
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:FOR EACH lSLCAnal.S_DirectionsForUseStatement
         RESULT = SetCursorFirstEntity( lSLCAnal, "S_DirectionsForUseStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Directions Statement Text"
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_DirectionsForUseStatement.Text
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lSLCAnal, "S_DirectionsForUseStatement", "" );
         } 

         //:END
         //:FOR EACH lSLCAnal.S_DirectionsUsage WITHIN lSLCAnal.S_DirectionsForUseSection
         RESULT = SetCursorFirstEntity( lSLCAnal, "S_DirectionsUsage", "S_DirectionsForUseSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:GetStringFromAttributeByContext( szUsageType, lSLCAnal, "S_DirectionsUsage", "UsageType", "", 20 )
            {StringBuilder sb_szUsageType;
            if ( szUsageType == null )
               sb_szUsageType = new StringBuilder( 32 );
            else
               sb_szUsageType = new StringBuilder( szUsageType );
                         GetStringFromAttributeByContext( sb_szUsageType, lSLCAnal, "S_DirectionsUsage", "UsageType", "", 20 );
            szUsageType = sb_szUsageType.toString( );}
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Directions " + szUsageType
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lSLCAnal.S_DirectionsUsage.Name
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_DirectionsForUseSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lSLCAnal, "S_DirectionsUsage", "S_DirectionsForUseSection" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lSLCAnal, "S_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// MARKETING

   //:// Compare SLC data to new MLC data through the SLC's associated MLC.
   //:FOR EACH lSLCAnal.S_MarketingSection
   RESULT = SetCursorFirstEntity( lSLCAnal, "S_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Set up sort value so entries in this Operaiton sort with entries created earlier.
      //:szSortTitle = "5Marketing: " //+ lSLCAnal.M_MarketingSection.Title

      //:// Follow SLC Section to related MLC and then to new MLC.
      //:SET CURSOR FIRST lMLCASrc.M_MarketingSection WHERE lMLCASrc.M_MarketingSection.ID = lSLCAnal.M_MarketingSection.ID
      {MutableInt mi_lTempInteger_20 = new MutableInt( lTempInteger_20 );
             GetIntegerFromAttribute( mi_lTempInteger_20, lSLCAnal, "M_MarketingSection", "ID" );
      lTempInteger_20 = mi_lTempInteger_20.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lMLCASrc, "M_MarketingSection", "ID", lTempInteger_20, "" );
      //:SET CURSOR FIRST lMLCATgt.M_MarketingSection WHERE lMLCATgt.M_MarketingSection.ID = lMLCASrc.MN_MarketingSection.ID
      {MutableInt mi_lTempInteger_21 = new MutableInt( lTempInteger_21 );
             GetIntegerFromAttribute( mi_lTempInteger_21, lMLCASrc, "MN_MarketingSection", "ID" );
      lTempInteger_21 = mi_lTempInteger_21.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lMLCATgt, "M_MarketingSection", "ID", lTempInteger_21, "" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// The SLC Section exists in the new MLC.
         //:IF lMLCATgt.M_MarketingSection.Title != lSLCAnal.S_MarketingSection.Title
         if ( CompareAttributeToAttribute( lMLCATgt, "M_MarketingSection", "Title", lSLCAnal, "S_MarketingSection", "Title" ) != 0 )
         { 
            //:// Section Title has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Marketing Section Title"
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_MarketingSection.Title
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_MarketingSection.Title
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:FOR EACH lSLCAnal.S_MarketingStatement
         RESULT = SetCursorFirstEntity( lSLCAnal, "S_MarketingStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST lMLCASrc.M_MarketingStatement WHERE lMLCASrc.M_MarketingStatement.ID = lSLCAnal.M_MarketingStatement.ID
            {MutableInt mi_lTempInteger_22 = new MutableInt( lTempInteger_22 );
                         GetIntegerFromAttribute( mi_lTempInteger_22, lSLCAnal, "M_MarketingStatement", "ID" );
            lTempInteger_22 = mi_lTempInteger_22.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( lMLCASrc, "M_MarketingStatement", "ID", lTempInteger_22, "" );
            //:IF lMLCASrc.MN_MarketingStatement EXISTS
            lTempInteger_23 = CheckExistenceOfEntity( lMLCASrc, "MN_MarketingStatement" );
            if ( lTempInteger_23 == 0 )
            { 
               //:SET CURSOR FIRST lMLCATgt.M_MarketingStatement WHERE lMLCATgt.M_MarketingStatement.ID = lMLCASrc.MN_MarketingStatement.ID
               {MutableInt mi_lTempInteger_24 = new MutableInt( lTempInteger_24 );
                               GetIntegerFromAttribute( mi_lTempInteger_24, lMLCASrc, "MN_MarketingStatement", "ID" );
               lTempInteger_24 = mi_lTempInteger_24.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( lMLCATgt, "M_MarketingStatement", "ID", lTempInteger_24, "" );
               //:IF lMLCATgt.M_MarketingStatement.Text != lSLCAnal.S_MarketingStatement.Text
               if ( CompareAttributeToAttribute( lMLCATgt, "M_MarketingStatement", "Text", lSLCAnal, "S_MarketingStatement", "Text" ) != 0 )
               { 
                  //:// Statement Text has changed
                  //:CREATE ENTITY lMLCATgt.ComparisonDifference
                  RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
                  //:lMLCATgt.ComparisonDifference.Title                = "...Marketing Statement Text"
                  //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_MarketingStatement.Text
                  //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_MarketingStatement.Text
                  //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
                  //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
                  olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// SLC Statement has been deleted.
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Marketing Statement Text"
               //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_MarketingStatement.Text
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lSLCAnal, "S_MarketingStatement", "" );
            //:END
         } 

         //:END
         //:// Check if any Source Usages are not in new Target. (Those new to Target were processed in BuildDifferencesMLC.
         //:FOR EACH lSLCAnal.S_MarketingUsage WITHIN lSLCAnal.S_MarketingSection
         RESULT = SetCursorFirstEntity( lSLCAnal, "S_MarketingUsage", "S_MarketingSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST lMLCATgt.M_MarketingUsage WITHIN lMLCATgt.M_MarketingSection
            //:           WHERE lMLCATgt.M_MarketingUsage.Name = lSLCAnal.S_MarketingUsage.Name
            //:             AND lMLCATgt.M_MarketingUsage.UsageType = lSLCAnal.S_MarketingUsage.UsageType
            RESULT = SetCursorFirstEntity( lMLCATgt, "M_MarketingUsage", "M_MarketingSection" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lMLCATgt, "M_MarketingUsage", "Name", lSLCAnal, "S_MarketingUsage", "Name" ) != 0 ||
                       CompareAttributeToAttribute( lMLCATgt, "M_MarketingUsage", "UsageType", lSLCAnal, "S_MarketingUsage", "UsageType" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( lMLCATgt, "M_MarketingUsage", "M_MarketingSection" );
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:GetStringFromAttributeByContext( szUsageType, lSLCAnal, "S_MarketingUsage", "UsageType", "", 20 )
               {StringBuilder sb_szUsageType;
               if ( szUsageType == null )
                  sb_szUsageType = new StringBuilder( 32 );
               else
                  sb_szUsageType = new StringBuilder( szUsageType );
                               GetStringFromAttributeByContext( sb_szUsageType, lSLCAnal, "S_MarketingUsage", "UsageType", "", 20 );
               szUsageType = sb_szUsageType.toString( );}
               //:CREATE ENTITY lMLCATgt.ComparisonDifference
               RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
               //:lMLCATgt.ComparisonDifference.Title                = "...Marketing " + szUsageType
               //:lMLCATgt.ComparisonDifference.TargetStatementValue = lSLCAnal.S_MarketingUsage.Name
               //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
               //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
               olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            } 

            RESULT = SetCursorNextEntity( lSLCAnal, "S_MarketingUsage", "S_MarketingSection" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// The SLC Section DOES NOT exist in the new MLC. (It has been deleted.)
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Marketing Section Title"
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_MarketingSection.Title
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:FOR EACH lSLCAnal.S_MarketingStatement
         RESULT = SetCursorFirstEntity( lSLCAnal, "S_MarketingStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Marketing Statement Text"
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_MarketingStatement.Text
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lSLCAnal, "S_MarketingStatement", "" );
         } 

         //:END
         //:FOR EACH lSLCAnal.S_MarketingUsage
         RESULT = SetCursorFirstEntity( lSLCAnal, "S_MarketingUsage", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:GetStringFromAttributeByContext( szUsageType, lSLCAnal, "M_MarketingUsage", "UsageType", "", 20 )
            {StringBuilder sb_szUsageType;
            if ( szUsageType == null )
               sb_szUsageType = new StringBuilder( 32 );
            else
               sb_szUsageType = new StringBuilder( szUsageType );
                         GetStringFromAttributeByContext( sb_szUsageType, lSLCAnal, "M_MarketingUsage", "UsageType", "", 20 );
            szUsageType = sb_szUsageType.toString( );}
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Marketing " + szUsageType
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lSLCAnal.S_MarketingUsage.Name
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_MarketingSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
            RESULT = SetCursorNextEntity( lSLCAnal, "S_MarketingUsage", "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lSLCAnal, "S_MarketingSection", "" );
      //:END
   } 

   //:END

   //:// HUMAN HAZARD

   //:// Compare SLC data to new MLC data through the SLC's associated MLC.
   //:FOR EACH lSLCAnal.S_HumanHazardSection
   RESULT = SetCursorFirstEntity( lSLCAnal, "S_HumanHazardSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Set up sort value so entries in this Operaiton sort with entries created earlier.
      //:szSortTitle = "6Human Hazard: " //+ lSLCAnal.M_HumanHazardSection.EPA_SignalWord

      //:// Follow SLC Section to related MLC and then to new MLC.
      //:SET CURSOR FIRST lMLCASrc.M_HumanHazardSection WHERE lMLCASrc.M_HumanHazardSection.ID = lSLCAnal.M_HumanHazardSection.ID
      {MutableInt mi_lTempInteger_25 = new MutableInt( lTempInteger_25 );
             GetIntegerFromAttribute( mi_lTempInteger_25, lSLCAnal, "M_HumanHazardSection", "ID" );
      lTempInteger_25 = mi_lTempInteger_25.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lMLCASrc, "M_HumanHazardSection", "ID", lTempInteger_25, "" );
      //:SET CURSOR FIRST lMLCATgt.M_HumanHazardSection WHERE lMLCATgt.M_HumanHazardSection.ID = lMLCASrc.MN_HumanHazardSection.ID
      {MutableInt mi_lTempInteger_26 = new MutableInt( lTempInteger_26 );
             GetIntegerFromAttribute( mi_lTempInteger_26, lMLCASrc, "MN_HumanHazardSection", "ID" );
      lTempInteger_26 = mi_lTempInteger_26.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lMLCATgt, "M_HumanHazardSection", "ID", lTempInteger_26, "" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// The SLC Section exists in the new MLC.
         //:IF lMLCATgt.M_HumanHazardSection.EPA_SignalWord != lSLCAnal.S_HumanHazardSection.EPA_SignalWord
         if ( CompareAttributeToAttribute( lMLCATgt, "M_HumanHazardSection", "EPA_SignalWord", lSLCAnal, "S_HumanHazardSection", "EPA_SignalWord" ) != 0 )
         { 
            //:// Section Title has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Hazards EPA Signal Word"
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_HumanHazardSection.EPA_SignalWord
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_HumanHazardSection.EPA_SignalWord
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_HumanHazardSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:IF lMLCATgt.M_HumanHazardSection.EPA_ChildHazardWarning != lSLCAnal.S_HumanHazardSection.EPA_ChildHazardWarning
         if ( CompareAttributeToAttribute( lMLCATgt, "M_HumanHazardSection", "EPA_ChildHazardWarning", lSLCAnal, "S_HumanHazardSection", "EPA_ChildHazardWarning" ) != 0 )
         { 
            //:// Section Title has changed
            //:CREATE ENTITY lMLCATgt.ComparisonDifference
            RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
            //:lMLCATgt.ComparisonDifference.Title                = "...Hazards EPA Warning"
            //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_HumanHazardSection.EPA_ChildHazardWarning
            //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_HumanHazardSection.EPA_ChildHazardWarning
            //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_HumanHazardSection.wSectionSortOrder
            //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
            olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// The SLC Section DOES NOT exist in the new MLC. (It has been deleted.)
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Hazards EPA Signal Word"
         //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_HumanHazardSection.EPA_SignalWord
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_HumanHazardSection.EPA_SignalWord
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_HumanHazardSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
         //:// Section Title has changed
         //:CREATE ENTITY lMLCATgt.ComparisonDifference
         RESULT = CreateEntity( lMLCATgt, "ComparisonDifference", zPOS_AFTER );
         //:lMLCATgt.ComparisonDifference.Title                = "...Hazards EPA Warning"
         //:lMLCATgt.ComparisonDifference.TargetStatementValue = lMLCATgt.M_HumanHazardSection.EPA_ChildHazardWarning
         //:lMLCATgt.ComparisonDifference.SourceStatementValue = lSLCAnal.S_HumanHazardSection.EPA_ChildHazardWarning
         //:lMLCATgt.ComparisonDifference.SortSectionNumber    = lMLCATgt.M_HumanHazardSection.wSectionSortOrder
         //:SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle )
         olMLCATgt_SetDifferencesSortVs( lMLCATgt, lSortEntryNbr, szSortTitle );
      } 

      RESULT = SetCursorNextEntity( lSLCAnal, "S_HumanHazardSection", "" );
      //:END
   } 

   //:END

   //:// Order the entries so that the entries added in this operation are integrated with the entries added during the
   //:// operation, BuildDifferencesMLC.
   //:OrderEntityForView( lMLCATgt, "ComparisonDifference", "SortSectionTitle A SortSectionNumber A SortEntryNumber A" )
   OrderEntityForView( lMLCATgt, "ComparisonDifference", "SortSectionTitle A SortSectionNumber A SortEntryNumber A" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:SetDifferencesSortVs( VIEW lMLCATgt BASED ON LOD lMLCATgt,
//:                      INTEGER lSortNbr,
//:                      STRING ( 20 ) szTitle )
public int 
olMLCATgt_SetDifferencesSortVs( View     lMLCATgt,
                                int      lSortNbr,
                                String   szTitle )
{


   //:// Set values that are used for sorting Difference entries by sequence number within Section Title.
   //:lSortNbr = lSortNbr + 1
   //:lMLCATgt.ComparisonDifference.SortEntryNumber  = lSortNbr
   //:lMLCATgt.ComparisonDifference.SortSectionTitle = szTitle
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:DeleteEmptyTitles( VIEW lMLCATgt BASED ON LOD lMLCATgt )

//:   VIEW lMLCATgt2 BASED ON LOD lMLCATgt
public int 
olMLCATgt_DeleteEmptyTitles( View     lMLCATgt )
{
   zVIEW    lMLCATgt2 = new zVIEW( );
   int      RESULT = 0;


   //:// Remove any Title Difference enties that don't have any details following them.
   //:// These are those where the next entry is also a Title.
   //:FOR EACH lMLCATgt.ComparisonDifference
   RESULT = SetCursorFirstEntity( lMLCATgt, "ComparisonDifference", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF lMLCATgt.ComparisonDifference.TitleFlag = "Y"
      if ( CompareAttributeToString( lMLCATgt, "ComparisonDifference", "TitleFlag", "Y" ) == 0 )
      { 
         //:CreateViewFromView( lMLCATgt2, lMLCATgt )
         CreateViewFromView( lMLCATgt2, lMLCATgt );
         //:SET CURSOR NEXT lMLCATgt2.ComparisonDifference
         RESULT = SetCursorNextEntity( lMLCATgt2, "ComparisonDifference", "" );
         //:IF lMLCATgt2.ComparisonDifference.TitleFlag = "Y"
         if ( CompareAttributeToString( lMLCATgt2, "ComparisonDifference", "TitleFlag", "Y" ) == 0 )
         { 
            //:DELETE ENTITY lMLCATgt.ComparisonDifference NONE
            RESULT = DeleteEntity( lMLCATgt, "ComparisonDifference", zREPOS_NONE );
         } 

         //:END
         //:DropView( lMLCATgt2 )
         DropView( lMLCATgt2 );
      } 

      RESULT = SetCursorNextEntity( lMLCATgt, "ComparisonDifference", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 



}
