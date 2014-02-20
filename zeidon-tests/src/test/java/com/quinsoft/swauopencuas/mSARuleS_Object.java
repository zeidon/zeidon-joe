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

package com.quinsoft.swauopencuas;

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

public class mSARuleS_Object extends VmlObjectOperations
{
   public mSARuleS_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:DescribeRule( VIEW mSARuleS BASED ON LOD mSARuleS,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )

//:   VIEW mSARuleS2     BASED ON LOD mSARuleS
public int 
omSARuleS_DescribeRule( View     mSARuleS,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   zVIEW    mSARuleS2 = new zVIEW( );
   //:STRING ( 2000 ) szDescription
   String   szDescription = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:szDescription = ""
          {StringBuilder sb_szDescription;
         if ( szDescription == null )
            sb_szDescription = new StringBuilder( 32 );
         else
            sb_szDescription = new StringBuilder( szDescription );
                  ZeidonStringCopy( sb_szDescription, 1, 0, "", 1, 0, 2001 );
         szDescription = sb_szDescription.toString( );}
         //:CreateViewFromView( mSARuleS2, mSARuleS )
         CreateViewFromView( mSARuleS2, mSARuleS );

         //:BuildDesc( mSARuleS2, szDescription )
         {StringBuilder sb_szDescription;
         if ( szDescription == null )
            sb_szDescription = new StringBuilder( 32 );
         else
            sb_szDescription = new StringBuilder( szDescription );
                   omSARuleS_BuildDesc( mSARuleS2, sb_szDescription );
         szDescription = sb_szDescription.toString( );}

         //:DropView( mSARuleS2 )
         DropView( mSARuleS2 );
         //:StoreStringInRecord( mSARuleS, 
         //:                     InternalEntityStructure, 
         //:                     InternalAttribStructure, szDescription )
         StoreStringInRecord( mSARuleS, InternalEntityStructure, InternalAttribStructure, szDescription );
         break ;
      //:/* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:   /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


private int 
omSARuleS_fnLocalBuildQual_0( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_5 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountRuleSet" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountRuleSet" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_5 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSARuleS_fnLocalBuildQual_1( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSARuleS_fnLocalBuildQual_2( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountRuleSet" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountRuleSet" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:TRANSFORMATION OPERATION
//:BuildDesc( VIEW mSARuleS BASED ON LOD mSARuleS,
//:           STRING ( 2000 ) szDescription )

//:   VIEW mChildSARuleS BASED ON LOD mSARuleS
public int 
omSARuleS_BuildDesc( View     mSARuleS,
                     StringBuilder   szDescription )
{
   zVIEW    mChildSARuleS = new zVIEW( );
   //:STRING ( 128 ) szEA
   String   szEA = null;
   //:STRING ( 15 )  szMin
   String   szMin = null;
   //:STRING ( 15 )  szMax
   String   szMax = null;
   //:STRING ( 254 ) szValue
   String   szValue = null;
   //:STRING ( 254 ) szStringValue
   String   szStringValue = null;
   //:STRING ( 5 )   szTop
   String   szTop = null;
   //:STRING ( 5 )   szAND
   String   szAND = null;
   //:STRING ( 4 )   szOR
   String   szOR = null;
   //:STRING ( 3 )   szLParen
   String   szLParen = null;
   //:STRING ( 3 )   szRParen
   String   szRParen = null;
   //:STRING ( 10 )   szOper
   String   szOper = null;
   //:STRING ( 1 )   szSPACE
   String   szSPACE = null;
   //:INTEGER nInLoopAgain 
   int      nInLoopAgain = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szAND = " AND "
    {StringBuilder sb_szAND;
   if ( szAND == null )
      sb_szAND = new StringBuilder( 32 );
   else
      sb_szAND = new StringBuilder( szAND );
      ZeidonStringCopy( sb_szAND, 1, 0, " AND ", 1, 0, 6 );
   szAND = sb_szAND.toString( );}
   //:szOR  = " OR "
    {StringBuilder sb_szOR;
   if ( szOR == null )
      sb_szOR = new StringBuilder( 32 );
   else
      sb_szOR = new StringBuilder( szOR );
      ZeidonStringCopy( sb_szOR, 1, 0, " OR ", 1, 0, 5 );
   szOR = sb_szOR.toString( );}
   //:szLParen = "( "
    {StringBuilder sb_szLParen;
   if ( szLParen == null )
      sb_szLParen = new StringBuilder( 32 );
   else
      sb_szLParen = new StringBuilder( szLParen );
      ZeidonStringCopy( sb_szLParen, 1, 0, "( ", 1, 0, 4 );
   szLParen = sb_szLParen.toString( );}
   //:szRParen = " ) "
    {StringBuilder sb_szRParen;
   if ( szRParen == null )
      sb_szRParen = new StringBuilder( 32 );
   else
      sb_szRParen = new StringBuilder( szRParen );
      ZeidonStringCopy( sb_szRParen, 1, 0, " ) ", 1, 0, 4 );
   szRParen = sb_szRParen.toString( );}
   //:szSPACE = " " 
    {StringBuilder sb_szSPACE;
   if ( szSPACE == null )
      sb_szSPACE = new StringBuilder( 32 );
   else
      sb_szSPACE = new StringBuilder( szSPACE );
      ZeidonStringCopy( sb_szSPACE, 1, 0, " ", 1, 0, 2 );
   szSPACE = sb_szSPACE.toString( );}
   //:szOper = ""
    {StringBuilder sb_szOper;
   if ( szOper == null )
      sb_szOper = new StringBuilder( 32 );
   else
      sb_szOper = new StringBuilder( szOper );
      ZeidonStringCopy( sb_szOper, 1, 0, "", 1, 0, 11 );
   szOper = sb_szOper.toString( );}
   //:szEA = ""
    {StringBuilder sb_szEA;
   if ( szEA == null )
      sb_szEA = new StringBuilder( 32 );
   else
      sb_szEA = new StringBuilder( szEA );
      ZeidonStringCopy( sb_szEA, 1, 0, "", 1, 0, 129 );
   szEA = sb_szEA.toString( );}
   //:szTop = ""
    {StringBuilder sb_szTop;
   if ( szTop == null )
      sb_szTop = new StringBuilder( 32 );
   else
      sb_szTop = new StringBuilder( szTop );
      ZeidonStringCopy( sb_szTop, 1, 0, "", 1, 0, 6 );
   szTop = sb_szTop.toString( );}
   //:szMin = ""
    {StringBuilder sb_szMin;
   if ( szMin == null )
      sb_szMin = new StringBuilder( 32 );
   else
      sb_szMin = new StringBuilder( szMin );
      ZeidonStringCopy( sb_szMin, 1, 0, "", 1, 0, 16 );
   szMin = sb_szMin.toString( );}
   //:szMax = ""
    {StringBuilder sb_szMax;
   if ( szMax == null )
      sb_szMax = new StringBuilder( 32 );
   else
      sb_szMax = new StringBuilder( szMax );
      ZeidonStringCopy( sb_szMax, 1, 0, "", 1, 0, 16 );
   szMax = sb_szMax.toString( );}
   //:szValue = ""
    {StringBuilder sb_szValue;
   if ( szValue == null )
      sb_szValue = new StringBuilder( 32 );
   else
      sb_szValue = new StringBuilder( szValue );
      ZeidonStringCopy( sb_szValue, 1, 0, "", 1, 0, 255 );
   szValue = sb_szValue.toString( );}

   //:IF mSARuleS.StudentAccountRuleSet.QualBooleanOperator = "A"
   if ( CompareAttributeToString( mSARuleS, "StudentAccountRuleSet", "QualBooleanOperator", "A" ) == 0 )
   { 
      //:szTop = szAND
       {StringBuilder sb_szTop;
      if ( szTop == null )
         sb_szTop = new StringBuilder( 32 );
      else
         sb_szTop = new StringBuilder( szTop );
            ZeidonStringCopy( sb_szTop, 1, 0, szAND, 1, 0, 6 );
      szTop = sb_szTop.toString( );}
      //:ELSE 
   } 
   else
   { 
      //:szTop = szOR 
       {StringBuilder sb_szTop;
      if ( szTop == null )
         sb_szTop = new StringBuilder( 32 );
      else
         sb_szTop = new StringBuilder( szTop );
            ZeidonStringCopy( sb_szTop, 1, 0, szOR, 1, 0, 6 );
      szTop = sb_szTop.toString( );}
   } 

   //:END

   //:nInLoopAgain = 0
   nInLoopAgain = 0;
   //:FOR EACH mSARuleS.StudentAccountRule 
   RESULT = SetCursorFirstEntity( mSARuleS, "StudentAccountRule", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF nInLoopAgain = 1
      if ( nInLoopAgain == 1 )
      { 
         //:szDescription = szDescription + szTop + NEW_LINE
         ZeidonStringConcat( szDescription, 1, 0, szTop, 1, 0, 2001 );
         ZeidonStringConcat( szDescription, 1, 0, NEW_LINE, 1, 0, 2001 );
      } 

      //:END
      //:IF nInLoopAgain = 0
      if ( nInLoopAgain == 0 )
      { 
         //:nInLoopAgain = 1
         nInLoopAgain = 1;
      } 

      //:END
      //:szEA  = mSARuleS.StudentAccountRule.QualEntityName + "." + mSARuleS.StudentAccountRule.QualAttributeName 
      {StringBuilder sb_szEA;
      if ( szEA == null )
         sb_szEA = new StringBuilder( 32 );
      else
         sb_szEA = new StringBuilder( szEA );
             GetStringFromAttribute( sb_szEA, mSARuleS, "StudentAccountRule", "QualEntityName" );
      szEA = sb_szEA.toString( );}
       {StringBuilder sb_szEA;
      if ( szEA == null )
         sb_szEA = new StringBuilder( 32 );
      else
         sb_szEA = new StringBuilder( szEA );
            ZeidonStringConcat( sb_szEA, 1, 0, ".", 1, 0, 129 );
      szEA = sb_szEA.toString( );}
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 255, mSARuleS, "StudentAccountRule", "QualAttributeName", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szEA;
      if ( szEA == null )
         sb_szEA = new StringBuilder( 32 );
      else
         sb_szEA = new StringBuilder( szEA );
            ZeidonStringConcat( sb_szEA, 1, 0, szTempString_0, 1, 0, 129 );
      szEA = sb_szEA.toString( );}
      //:szOper= " " + mSARuleS.StudentAccountRule.QualOperator + " "
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
             GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 11, mSARuleS, "StudentAccountRule", "QualOperator", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szTempString_1 = sb_szTempString_1.toString( );}
       {StringBuilder sb_szOper;
      if ( szOper == null )
         sb_szOper = new StringBuilder( 32 );
      else
         sb_szOper = new StringBuilder( szOper );
            ZeidonStringCopy( sb_szOper, 1, 0, " ", 1, 0, 11 );
      szOper = sb_szOper.toString( );}
       {StringBuilder sb_szOper;
      if ( szOper == null )
         sb_szOper = new StringBuilder( 32 );
      else
         sb_szOper = new StringBuilder( szOper );
            ZeidonStringConcat( sb_szOper, 1, 0, szTempString_1, 1, 0, 11 );
      szOper = sb_szOper.toString( );}
       {StringBuilder sb_szOper;
      if ( szOper == null )
         sb_szOper = new StringBuilder( 32 );
      else
         sb_szOper = new StringBuilder( szOper );
            ZeidonStringConcat( sb_szOper, 1, 0, " ", 1, 0, 11 );
      szOper = sb_szOper.toString( );}
      //:szMin = mSARuleS.StudentAccountRule.QualMinNumericValue 
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szMin;
      if ( szMin == null )
         sb_szMin = new StringBuilder( 32 );
      else
         sb_szMin = new StringBuilder( szMin );
             GetVariableFromAttribute( sb_szMin, mi_lTempInteger_2, 'S', 16, mSARuleS, "StudentAccountRule", "QualMinNumericValue", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szMin = sb_szMin.toString( );}
      //:szMax = mSARuleS.StudentAccountRule.QualMaxNumericValue 
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szMax;
      if ( szMax == null )
         sb_szMax = new StringBuilder( 32 );
      else
         sb_szMax = new StringBuilder( szMax );
             GetVariableFromAttribute( sb_szMax, mi_lTempInteger_3, 'S', 16, mSARuleS, "StudentAccountRule", "QualMaxNumericValue", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szMax = sb_szMax.toString( );}
      //:szStringValue = mSARuleS.StudentAccountRule.QualStringValue 
      {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
      StringBuilder sb_szStringValue;
      if ( szStringValue == null )
         sb_szStringValue = new StringBuilder( 32 );
      else
         sb_szStringValue = new StringBuilder( szStringValue );
             GetVariableFromAttribute( sb_szStringValue, mi_lTempInteger_4, 'S', 255, mSARuleS, "StudentAccountRule", "QualStringValue", "", 0 );
      lTempInteger_4 = mi_lTempInteger_4.intValue( );
      szStringValue = sb_szStringValue.toString( );}
      //:IF szOper = " between " 
      if ( ZeidonStringCompare( szOper, 1, 0, " between ", 1, 0, 11 ) == 0 )
      { 
         //:szValue =  szOper + szSPACE + szMin + szAND + szMax + szSPACE
          {StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                  ZeidonStringCopy( sb_szValue, 1, 0, szOper, 1, 0, 255 );
         szValue = sb_szValue.toString( );}
          {StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                  ZeidonStringConcat( sb_szValue, 1, 0, szSPACE, 1, 0, 255 );
         szValue = sb_szValue.toString( );}
          {StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                  ZeidonStringConcat( sb_szValue, 1, 0, szMin, 1, 0, 255 );
         szValue = sb_szValue.toString( );}
          {StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                  ZeidonStringConcat( sb_szValue, 1, 0, szAND, 1, 0, 255 );
         szValue = sb_szValue.toString( );}
          {StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                  ZeidonStringConcat( sb_szValue, 1, 0, szMax, 1, 0, 255 );
         szValue = sb_szValue.toString( );}
          {StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                  ZeidonStringConcat( sb_szValue, 1, 0, szSPACE, 1, 0, 255 );
         szValue = sb_szValue.toString( );}
         //:ELSE 
      } 
      else
      { 
         //:IF szStringValue = ""
         if ( ZeidonStringCompare( szStringValue, 1, 0, "", 1, 0, 255 ) == 0 )
         { 
            //:szValue = szOper + szSPACE + szMin 
             {StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                        ZeidonStringCopy( sb_szValue, 1, 0, szOper, 1, 0, 255 );
            szValue = sb_szValue.toString( );}
             {StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                        ZeidonStringConcat( sb_szValue, 1, 0, szSPACE, 1, 0, 255 );
            szValue = sb_szValue.toString( );}
             {StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                        ZeidonStringConcat( sb_szValue, 1, 0, szMin, 1, 0, 255 );
            szValue = sb_szValue.toString( );}
            //:ELSE 
         } 
         else
         { 
            //:szValue = szOper + szSPACE + QUOTES + szStringValue + QUOTES
             {StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                        ZeidonStringCopy( sb_szValue, 1, 0, szOper, 1, 0, 255 );
            szValue = sb_szValue.toString( );}
             {StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                        ZeidonStringConcat( sb_szValue, 1, 0, szSPACE, 1, 0, 255 );
            szValue = sb_szValue.toString( );}
             {StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                        ZeidonStringConcat( sb_szValue, 1, 0, QUOTES, 1, 0, 255 );
            szValue = sb_szValue.toString( );}
             {StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                        ZeidonStringConcat( sb_szValue, 1, 0, szStringValue, 1, 0, 255 );
            szValue = sb_szValue.toString( );}
             {StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                        ZeidonStringConcat( sb_szValue, 1, 0, QUOTES, 1, 0, 255 );
            szValue = sb_szValue.toString( );}
         } 

         //:END
      } 

      //:END 
      //:szDescription = szDescription + szEA + szValue //+ szTop + NEW_LINE
      ZeidonStringConcat( szDescription, 1, 0, szEA, 1, 0, 2001 );
      ZeidonStringConcat( szDescription, 1, 0, szValue, 1, 0, 2001 );
      RESULT = SetCursorNextEntity( mSARuleS, "StudentAccountRule", "" );
   } 

   //:END

   //:FOR EACH mSARuleS.ChildStudentAccountRuleSet  
   RESULT = SetCursorFirstEntity( mSARuleS, "ChildStudentAccountRuleSet", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF nInLoopAgain = 1
      if ( nInLoopAgain == 1 )
      { 
         //:szDescription = szDescription + szTop + NEW_LINE
         ZeidonStringConcat( szDescription, 1, 0, szTop, 1, 0, 2001 );
         ZeidonStringConcat( szDescription, 1, 0, NEW_LINE, 1, 0, 2001 );
      } 

      //:END
      //:IF nInLoopAgain = 0
      if ( nInLoopAgain == 0 )
      { 
         //:nInLoopAgain = 1
         nInLoopAgain = 1;
      } 

      //:END

      //:ACTIVATE mChildSARuleS  
      //:   WHERE mChildSARuleS.StudentAccountRuleSet.ID = mSARuleS.ChildStudentAccountRuleSet.ID  
      {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
             GetIntegerFromAttribute( mi_lTempInteger_5, mSARuleS, "ChildStudentAccountRuleSet", "ID" );
      lTempInteger_5 = mi_lTempInteger_5.intValue( );}
      omSARuleS_fnLocalBuildQual_0( mSARuleS, vTempViewVar_0, lTempInteger_5 );
      RESULT = ActivateObjectInstance( mChildSARuleS, "mSARuleS", mSARuleS, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:szDescription = szDescription + szLParen
      ZeidonStringConcat( szDescription, 1, 0, szLParen, 1, 0, 2001 );
      //:BuildDesc( mChildSARuleS,  szDescription )
      omSARuleS_BuildDesc( mChildSARuleS, szDescription );
      //:DropView ( mChildSARuleS )
      DropView( mChildSARuleS );
      //:szDescription = szDescription + szRParen
      ZeidonStringConcat( szDescription, 1, 0, szRParen, 1, 0, 2001 );
      RESULT = SetCursorNextEntity( mSARuleS, "ChildStudentAccountRuleSet", "" );
   } 

   //:END 
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:TestRuleSet( VIEW mSARuleS BASED ON LOD mSARuleS,
//:             VIEW mSAProf BASED ON LOD mSAProf )
//:   VIEW mPerson BASED ON LOD mPerson
public int 
omSARuleS_TestRuleSet( View     mSARuleS,
                       View     mSAProf )
{
   zVIEW    mPerson = new zVIEW( );
   //:STRING (  100  ) szValue 
   String   szValue = null;
   //:INTEGER nRC 
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_1 = 0;

   //:   
   //:ACTIVATE  mPerson  WHERE mPerson.Person.ID = mSAProf.Person.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf, "Person", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omSARuleS_fnLocalBuildQual_1( mSARuleS, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mPerson, "mPerson", mSARuleS, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:nRC = QualifyRuleSet( mSARuleS, mSAProf, mPerson )
   nRC = omSARuleS_QualifyRuleSet( mSARuleS, mSAProf, mPerson );
   //:IF nRC = 1
   if ( nRC == 1 )
   { 
      //:IssueError( mSARuleS, 0, 0, "QUAL TRUE IS YES!" )
      IssueError( mSARuleS, 0, 0, "QUAL TRUE IS YES!" );
      //:IF mSARuleS.StudentAccountRuleSet.RuleType = "T" // table lookup 
      if ( CompareAttributeToString( mSARuleS, "StudentAccountRuleSet", "RuleType", "T" ) == 0 )
      { 
         //:IssueError( mSARuleS, 0, 0, "QUAL Need to Look up Value!" )
         IssueError( mSARuleS, 0, 0, "QUAL Need to Look up Value!" );
         //:FindRuleSetValue( mSARuleS )
         omSARuleS_FindRuleSetValue( mSARuleS );
         //:szValue = mSARuleS.StudentAccountRuleSet.RuleStringResult 
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                   GetVariableFromAttribute( sb_szValue, mi_lTempInteger_1, 'S', 101, mSARuleS, "StudentAccountRuleSet", "RuleStringResult", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szValue = sb_szValue.toString( );}
         //:IssueError( mSARuleS, 0, 0, szValue )
         IssueError( mSARuleS, 0, 0, szValue );
      } 

      //:END  
      //:ELSE 
   } 
   else
   { 
      //:IssueError( mSARuleS, 0, 0, "QUAL TRUE IS no..." )
      IssueError( mSARuleS, 0, 0, "QUAL TRUE IS no..." );
   } 

   //:END
   //:DropView( mSARuleS )
   DropView( mSARuleS );
   //:DropView( mPerson )
   DropView( mPerson );
   return( 0 );
// END 
} 


//:TRANSFORMATION OPERATION
//:FindRuleSetValue( VIEW mSARuleS BASED ON LOD mSARuleS )
//:   STRING (  100  ) szValue 
public int 
omSARuleS_FindRuleSetValue( View     mSARuleS )
{
   String   szValue = null;
   //:INTEGER nRC 
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:IF mSARuleS.StudentAccountRuleSet.RuleType != "T" 
   if ( CompareAttributeToString( mSARuleS, "StudentAccountRuleSet", "RuleType", "T" ) != 0 )
   { 
      //:RETURN -1 // not a table lookup   
      if(8==8)return( -1 );
   } 

   //:END

   //:IF mSARuleS.SACalcLookupRow EXISTS 
   lTempInteger_0 = CheckExistenceOfEntity( mSARuleS, "SACalcLookupRow" );
   if ( lTempInteger_0 == 0 )
   { 
      //:FOR EACH mSARuleS.SACalcLookupRow
      RESULT = SetCursorFirstEntity( mSARuleS, "SACalcLookupRow", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:// look for the correct row
         //:nRC = ApplyRowRule( mSARuleS )
         nRC = omSARuleS_ApplyRowRule( mSARuleS );
         //:IF mSARuleS.SACalcLookupRow.wQualTrue = "Y"
         if ( CompareAttributeToString( mSARuleS, "SACalcLookupRow", "wQualTrue", "Y" ) == 0 )
         { 
            //:IF mSARuleS.SACalcLookupColumn EXISTS 
            lTempInteger_1 = CheckExistenceOfEntity( mSARuleS, "SACalcLookupColumn" );
            if ( lTempInteger_1 == 0 )
            { 
               //:FOR EACH  mSARuleS.SACalcLookupColumn
               RESULT = SetCursorFirstEntity( mSARuleS, "SACalcLookupColumn", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:// look for the correct column
                  //:nRC = ApplyColumnRule( mSARuleS )
                  nRC = omSARuleS_ApplyColumnRule( mSARuleS );
                  //:IF mSARuleS.SACalcLookupColumn.wQualTrue = "Y"
                  if ( CompareAttributeToString( mSARuleS, "SACalcLookupColumn", "wQualTrue", "Y" ) == 0 )
                  { 
                     //:szValue = mSARuleS.SACalcLookupColumn.dCalcAwardAmount //AwardAmount 
                     {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                     StringBuilder sb_szValue;
                     if ( szValue == null )
                        sb_szValue = new StringBuilder( 32 );
                     else
                        sb_szValue = new StringBuilder( szValue );
                                           GetVariableFromAttribute( sb_szValue, mi_lTempInteger_2, 'S', 101, mSARuleS, "SACalcLookupColumn", "dCalcAwardAmount", "", 0 );
                     lTempInteger_2 = mi_lTempInteger_2.intValue( );
                     szValue = sb_szValue.toString( );}
                     //:mSARuleS.StudentAccountRuleSet.RuleStringResult = szValue
                     SetAttributeFromString( mSARuleS, "StudentAccountRuleSet", "RuleStringResult", szValue );
                     //:RETURN 1
                     if(8==8)return( 1 );
                  } 

                  RESULT = SetCursorNextEntity( mSARuleS, "SACalcLookupColumn", "" );
                  //:END 
               } 

               //:END
               //:RETURN -1
               if(8==8)return( -1 );
               //:ELSE 
            } 
            else
            { 
               //://szValue = mSARuleS.SACalcLookupRow.AwardAmount 
               //://mSARuleS.StudentAccountRuleSet.RuleStringResult = szValue
               //:szValue = mSARuleS.SACalcLookupRow.dCalcAwardAmount 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szValue;
               if ( szValue == null )
                  sb_szValue = new StringBuilder( 32 );
               else
                  sb_szValue = new StringBuilder( szValue );
                               GetVariableFromAttribute( sb_szValue, mi_lTempInteger_3, 'S', 101, mSARuleS, "SACalcLookupRow", "dCalcAwardAmount", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szValue = sb_szValue.toString( );}
               //:mSARuleS.StudentAccountRuleSet.RuleStringResult = szValue
               SetAttributeFromString( mSARuleS, "StudentAccountRuleSet", "RuleStringResult", szValue );
               //:RETURN 1
               if(8==8)return( 1 );
            } 

            //:END
         } 

         RESULT = SetCursorNextEntity( mSARuleS, "SACalcLookupRow", "" );
         //:END
      } 

      //:END  
   } 

   //:END
   return( 0 );
// END 
} 


//:TRANSFORMATION OPERATION
//:ApplyRowRule( VIEW mSARuleS BASED ON LOD mSARuleS )
//:   VIEW  vAnyView
public int 
omSARuleS_ApplyRowRule( View     mSARuleS )
{
   zVIEW    vAnyView = new zVIEW( );
   //:STRING (  1024  ) szStringToCompare
   String   szStringToCompare = null;
   //:DECIMAL dValueToCompare
   double  dValueToCompare = 0.0;
   //:STRING (  64  ) szView
   String   szView = null;
   //:STRING (  64  ) szEntity
   String   szEntity = null;
   //:STRING (  64  ) szAttribute
   String   szAttribute = null;
   //:STRING (  1  )  szAttrType
   String   szAttrType = null;
   //:STRING (  10  )  szOper 
   String   szOper = null;
   //:INTEGER nCompareResult
   int      nCompareResult = 0;
   //:INTEGER nCompareResult2
   int      nCompareResult2 = 0;
   //:INTEGER nRC 
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      RESULT = 0;


   //:// get the values we are looking for 
   //:szEntity    = mSARuleS.SACalcLookupRow.QualEntityName 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntity;
   if ( szEntity == null )
      sb_szEntity = new StringBuilder( 32 );
   else
      sb_szEntity = new StringBuilder( szEntity );
       GetVariableFromAttribute( sb_szEntity, mi_lTempInteger_0, 'S', 65, mSARuleS, "SACalcLookupRow", "QualEntityName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntity = sb_szEntity.toString( );}
   //:szAttribute = mSARuleS.SACalcLookupRow.QualAttributeName 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szAttribute;
   if ( szAttribute == null )
      sb_szAttribute = new StringBuilder( 32 );
   else
      sb_szAttribute = new StringBuilder( szAttribute );
       GetVariableFromAttribute( sb_szAttribute, mi_lTempInteger_1, 'S', 65, mSARuleS, "SACalcLookupRow", "QualAttributeName", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szAttribute = sb_szAttribute.toString( );}
   //:szAttrType  = mSARuleS.SACalcLookupRow.QualAttributeType 
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szAttrType;
   if ( szAttrType == null )
      sb_szAttrType = new StringBuilder( 32 );
   else
      sb_szAttrType = new StringBuilder( szAttrType );
       GetVariableFromAttribute( sb_szAttrType, mi_lTempInteger_2, 'S', 2, mSARuleS, "SACalcLookupRow", "QualAttributeType", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szAttrType = sb_szAttrType.toString( );}
   //:szOper      = mSARuleS.SACalcLookupRow.QualOperator 
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szOper;
   if ( szOper == null )
      sb_szOper = new StringBuilder( 32 );
   else
      sb_szOper = new StringBuilder( szOper );
       GetVariableFromAttribute( sb_szOper, mi_lTempInteger_3, 'S', 11, mSARuleS, "SACalcLookupRow", "QualOperator", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szOper = sb_szOper.toString( );}
   //:szView      = mSARuleS.SACalcLookupRow.QualViewName 
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
   StringBuilder sb_szView;
   if ( szView == null )
      sb_szView = new StringBuilder( 32 );
   else
      sb_szView = new StringBuilder( szView );
       GetVariableFromAttribute( sb_szView, mi_lTempInteger_4, 'S', 65, mSARuleS, "SACalcLookupRow", "QualViewName", "", 0 );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );
   szView = sb_szView.toString( );}

   //:GET VIEW vAnyView NAMED szView
   RESULT = GetViewByName( vAnyView, szView, mSARuleS, zLEVEL_TASK );

   //:// see what type the attribute is. 
   //:IF szAttrType = "S"
   if ( ZeidonStringCompare( szAttrType, 1, 0, "S", 1, 0, 2 ) == 0 )
   { 
      //:GetStringFromAttribute( szStringToCompare, vAnyView, szEntity, szAttribute ) 
      {StringBuilder sb_szStringToCompare;
      if ( szStringToCompare == null )
         sb_szStringToCompare = new StringBuilder( 32 );
      else
         sb_szStringToCompare = new StringBuilder( szStringToCompare );
             GetStringFromAttribute( sb_szStringToCompare, vAnyView, szEntity, szAttribute );
      szStringToCompare = sb_szStringToCompare.toString( );}
      //:nCompareResult = 
      //:   CompareAttributeToString( mSARuleS, "SACalcLookupRow", "QualStringValue", szStringToCompare )
      nCompareResult = CompareAttributeToString( mSARuleS, "SACalcLookupRow", "QualStringValue", szStringToCompare );
      //:ELSE 
   } 
   else
   { 
      //:GetDecimalFromAttribute( dValueToCompare, vAnyView, szEntity, szAttribute )
      {MutableDouble md_dValueToCompare = new MutableDouble( dValueToCompare );
             GetDecimalFromAttribute( md_dValueToCompare, vAnyView, szEntity, szAttribute );
      dValueToCompare = md_dValueToCompare.doubleValue( );}

      //:nCompareResult = 
      //:   CompareAttributeToDecimal( mSARuleS, "SACalcLookupRow", "MinAttributeForAward", dValueToCompare )
      nCompareResult = CompareAttributeToDecimal( mSARuleS, "SACalcLookupRow", "MinAttributeForAward", dValueToCompare );
      //:nCompareResult2 = 
      //:   CompareAttributeToDecimal( mSARuleS, "SACalcLookupRow", "MaxAttributeForAward", dValueToCompare )
      nCompareResult2 = CompareAttributeToDecimal( mSARuleS, "SACalcLookupRow", "MaxAttributeForAward", dValueToCompare );
   } 


   //:END 

   //:// -1   attribute value is less than string value 
   //://  0   attribute value is equal to string value
   //://  1   attribute value is greater than string value 
   //:nRC = CompareResults( szOper, nCompareResult, nCompareResult2 )
   nRC = omSARuleS_CompareResults( szOper, nCompareResult, nCompareResult2 );
   //:IF nRC = 1 
   if ( nRC == 1 )
   { 
      //:mSARuleS.SACalcLookupRow.wQualTrue = "Y"
      SetAttributeFromString( mSARuleS, "SACalcLookupRow", "wQualTrue", "Y" );
      //:RETURN 1
      if(8==8)return( 1 );
   } 

   //:END 
   //:IF nRC = -1   
   if ( nRC == -1 )
   { 
      //:mSARuleS.SACalcLookupRow.wQualTrue = "N"
      SetAttributeFromString( mSARuleS, "SACalcLookupRow", "wQualTrue", "N" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ApplyColumnRule( VIEW mSARuleS BASED ON LOD mSARuleS )
//:   VIEW  vAnyView
public int 
omSARuleS_ApplyColumnRule( View     mSARuleS )
{
   zVIEW    vAnyView = new zVIEW( );
   //:STRING (  1024  ) szStringToCompare
   String   szStringToCompare = null;
   //:DECIMAL dValueToCompare
   double  dValueToCompare = 0.0;
   //:STRING (  64  ) szView
   String   szView = null;
   //:STRING (  64  ) szEntity
   String   szEntity = null;
   //:STRING (  64  ) szAttribute
   String   szAttribute = null;
   //:STRING (  1  )  szAttrType
   String   szAttrType = null;
   //:STRING (  10  )  szOper 
   String   szOper = null;
   //:INTEGER nCompareResult
   int      nCompareResult = 0;
   //:INTEGER nCompareResult2
   int      nCompareResult2 = 0;
   //:INTEGER nRC 
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      RESULT = 0;


   //:// get the values we are looking for 
   //:szEntity    = mSARuleS.SACalcLookupColumn.QualEntityName  
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntity;
   if ( szEntity == null )
      sb_szEntity = new StringBuilder( 32 );
   else
      sb_szEntity = new StringBuilder( szEntity );
       GetVariableFromAttribute( sb_szEntity, mi_lTempInteger_0, 'S', 65, mSARuleS, "SACalcLookupColumn", "QualEntityName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntity = sb_szEntity.toString( );}
   //:szAttribute = mSARuleS.SACalcLookupColumn.QualAttributeName 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szAttribute;
   if ( szAttribute == null )
      sb_szAttribute = new StringBuilder( 32 );
   else
      sb_szAttribute = new StringBuilder( szAttribute );
       GetVariableFromAttribute( sb_szAttribute, mi_lTempInteger_1, 'S', 65, mSARuleS, "SACalcLookupColumn", "QualAttributeName", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szAttribute = sb_szAttribute.toString( );}
   //:szAttrType  = mSARuleS.SACalcLookupColumn.QualAttributeType 
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szAttrType;
   if ( szAttrType == null )
      sb_szAttrType = new StringBuilder( 32 );
   else
      sb_szAttrType = new StringBuilder( szAttrType );
       GetVariableFromAttribute( sb_szAttrType, mi_lTempInteger_2, 'S', 2, mSARuleS, "SACalcLookupColumn", "QualAttributeType", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szAttrType = sb_szAttrType.toString( );}
   //:szOper      = mSARuleS.SACalcLookupColumn.QualOperator 
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szOper;
   if ( szOper == null )
      sb_szOper = new StringBuilder( 32 );
   else
      sb_szOper = new StringBuilder( szOper );
       GetVariableFromAttribute( sb_szOper, mi_lTempInteger_3, 'S', 11, mSARuleS, "SACalcLookupColumn", "QualOperator", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szOper = sb_szOper.toString( );}
   //:szView      = mSARuleS.SACalcLookupColumn.QualViewName 
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
   StringBuilder sb_szView;
   if ( szView == null )
      sb_szView = new StringBuilder( 32 );
   else
      sb_szView = new StringBuilder( szView );
       GetVariableFromAttribute( sb_szView, mi_lTempInteger_4, 'S', 65, mSARuleS, "SACalcLookupColumn", "QualViewName", "", 0 );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );
   szView = sb_szView.toString( );}

   //:GET VIEW vAnyView NAMED szView
   RESULT = GetViewByName( vAnyView, szView, mSARuleS, zLEVEL_TASK );

   //:// see what type the attribute is. 
   //:IF szAttrType = "S"
   if ( ZeidonStringCompare( szAttrType, 1, 0, "S", 1, 0, 2 ) == 0 )
   { 
      //:GetStringFromAttribute( szStringToCompare, vAnyView, szEntity, szAttribute ) 
      {StringBuilder sb_szStringToCompare;
      if ( szStringToCompare == null )
         sb_szStringToCompare = new StringBuilder( 32 );
      else
         sb_szStringToCompare = new StringBuilder( szStringToCompare );
             GetStringFromAttribute( sb_szStringToCompare, vAnyView, szEntity, szAttribute );
      szStringToCompare = sb_szStringToCompare.toString( );}
      //:nCompareResult = 
      //:   CompareAttributeToString( mSARuleS, "SACalcLookupColumn", "QualStringValue", szStringToCompare )
      nCompareResult = CompareAttributeToString( mSARuleS, "SACalcLookupColumn", "QualStringValue", szStringToCompare );
      //:ELSE 
   } 
   else
   { 
      //:GetDecimalFromAttribute( dValueToCompare, vAnyView, szEntity, szAttribute )
      {MutableDouble md_dValueToCompare = new MutableDouble( dValueToCompare );
             GetDecimalFromAttribute( md_dValueToCompare, vAnyView, szEntity, szAttribute );
      dValueToCompare = md_dValueToCompare.doubleValue( );}

      //:nCompareResult = 
      //:   CompareAttributeToDecimal( mSARuleS, "SACalcLookupColumn", "MinAttributeForAward", dValueToCompare )
      nCompareResult = CompareAttributeToDecimal( mSARuleS, "SACalcLookupColumn", "MinAttributeForAward", dValueToCompare );
      //:nCompareResult2 = 
      //:   CompareAttributeToDecimal( mSARuleS, "SACalcLookupColumn", "MaxAttributeForAward", dValueToCompare )
      nCompareResult2 = CompareAttributeToDecimal( mSARuleS, "SACalcLookupColumn", "MaxAttributeForAward", dValueToCompare );
   } 


   //:END 

   //:// -1   attribute value is less than string value 
   //://  0   attribute value is equal to string value
   //://  1   attribute value is greater than string value 
   //:nRC = CompareResults( szOper, nCompareResult, nCompareResult2 )
   nRC = omSARuleS_CompareResults( szOper, nCompareResult, nCompareResult2 );
   //:IF nRC = 1 
   if ( nRC == 1 )
   { 
      //:mSARuleS.SACalcLookupColumn.wQualTrue = "Y"
      SetAttributeFromString( mSARuleS, "SACalcLookupColumn", "wQualTrue", "Y" );
      //:RETURN 1
      if(8==8)return( 1 );
   } 

   //:END 
   //:IF nRC = -1   
   if ( nRC == -1 )
   { 
      //:mSARuleS.SACalcLookupColumn.wQualTrue = "N"
      SetAttributeFromString( mSARuleS, "SACalcLookupColumn", "wQualTrue", "N" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:QualifyRuleSet( VIEW mSARuleS BASED ON LOD mSARuleS,
//:                VIEW mSAProf  BASED ON LOD mSAProf,
//:                VIEW mPerson  BASED ON LOD mPerson )
//:                
//:   VIEW mSARule       BASED ON LOD mSARule
public int 
omSARuleS_QualifyRuleSet( View     mSARuleS,
                          View     mSAProf,
                          View     mPerson )
{
   zVIEW    mSARule = new zVIEW( );
   //:VIEW mChildSARuleS BASED ON LOD mSARuleS
   zVIEW    mChildSARuleS = new zVIEW( );
   //:VIEW mSAProfTEST   BASED ON LOD mSAProf
   zVIEW    mSAProfTEST = new zVIEW( );
   //:VIEW mPersonTEST   BASED ON LOD mPerson 
   zVIEW    mPersonTEST = new zVIEW( );
   //:STRING( 1 ) szANDOR
   String   szANDOR = null;
   //:STRING( 1 ) szAnyTrue
   String   szAnyTrue = null;
   //:STRING( 1 ) szAnyFalse
   String   szAnyFalse = null;
   //:INTEGER nRC 
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szANDOR = mSARuleS.StudentAccountRuleSet.QualBooleanOperator // A = and O = OR 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szANDOR;
   if ( szANDOR == null )
      sb_szANDOR = new StringBuilder( 32 );
   else
      sb_szANDOR = new StringBuilder( szANDOR );
       GetVariableFromAttribute( sb_szANDOR, mi_lTempInteger_0, 'S', 2, mSARuleS, "StudentAccountRuleSet", "QualBooleanOperator", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szANDOR = sb_szANDOR.toString( );}
   //:// is blank it is and
   //:IF  szANDOR = ""
   if ( ZeidonStringCompare( szANDOR, 1, 0, "", 1, 0, 2 ) == 0 )
   { 
      //:szANDOR = "A" 
       {StringBuilder sb_szANDOR;
      if ( szANDOR == null )
         sb_szANDOR = new StringBuilder( 32 );
      else
         sb_szANDOR = new StringBuilder( szANDOR );
            ZeidonStringCopy( sb_szANDOR, 1, 0, "A", 1, 0, 2 );
      szANDOR = sb_szANDOR.toString( );}
   } 

   //:END
   //:szAnyTrue = "N"
    {StringBuilder sb_szAnyTrue;
   if ( szAnyTrue == null )
      sb_szAnyTrue = new StringBuilder( 32 );
   else
      sb_szAnyTrue = new StringBuilder( szAnyTrue );
      ZeidonStringCopy( sb_szAnyTrue, 1, 0, "N", 1, 0, 2 );
   szAnyTrue = sb_szAnyTrue.toString( );}
   //:szAnyFalse = "N"
    {StringBuilder sb_szAnyFalse;
   if ( szAnyFalse == null )
      sb_szAnyFalse = new StringBuilder( 32 );
   else
      sb_szAnyFalse = new StringBuilder( szAnyFalse );
      ZeidonStringCopy( sb_szAnyFalse, 1, 0, "N", 1, 0, 2 );
   szAnyFalse = sb_szAnyFalse.toString( );}

   //:CreateViewFromView( mSAProfTEST, mSAProf ) 
   CreateViewFromView( mSAProfTEST, mSAProf );
   //:IF  mPerson > 0 
   if ( mPerson != null )
   { 
      //:CreateViewFromView( mPersonTEST, mPerson ) 
      CreateViewFromView( mPersonTEST, mPerson );
      //:ELSE 
   } 
   else
   { 
      //:mPersonTEST = 0
      mPersonTEST = null;
   } 

   //:END
   //:FOR EACH mSARuleS.StudentAccountRule 
   RESULT = SetCursorFirstEntity( mSARuleS, "StudentAccountRule", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// evaluate the rule and out the result in the correct attributes.
      //:nRC = ApplyRule( mSARuleS, mSAProfTEST, mPersonTEST )
      nRC = omSARuleS_ApplyRule( mSARuleS, mSAProfTEST, mPersonTEST );

      //:// if this is AND but we found a rule that does not apply, bail out now.
      //:IF  nRC < 0  AND szANDOR = "A"
      if ( nRC < 0 && ZeidonStringCompare( szANDOR, 1, 0, "A", 1, 0, 2 ) == 0 )
      { 
         //:mSARuleS.StudentAccountRuleSet.wQualTrue = "N"
         SetAttributeFromString( mSARuleS, "StudentAccountRuleSet", "wQualTrue", "N" );
         //:DropView( mSAProfTEST ) 
         DropView( mSAProfTEST );
         //:IF mPersonTEST > 0
         if ( mPersonTEST != null )
         { 
            //:DropView( mPersonTEST )
            DropView( mPersonTEST );
         } 

         //:END 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      RESULT = SetCursorNextEntity( mSARuleS, "StudentAccountRule", "" );
      //:END
   } 

   //:END 
   //:FOR EACH mSARuleS.ChildStudentAccountRuleSet 
   RESULT = SetCursorFirstEntity( mSARuleS, "ChildStudentAccountRuleSet", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:ACTIVATE mChildSARuleS
      //:   WHERE mChildSARuleS.StudentAccountRuleSet.ID = mSARuleS.ChildStudentAccountRuleSet.ID 
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, mSARuleS, "ChildStudentAccountRuleSet", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      omSARuleS_fnLocalBuildQual_2( mSARuleS, vTempViewVar_0, lTempInteger_1 );
      RESULT = ActivateObjectInstance( mChildSARuleS, "mSARuleS", mSARuleS, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:IF  RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:RelinkInstanceToInstance( mSARuleS, "ChildStudentAccountRuleSet", 
         //:                          mChildSARuleS, "StudentAccountRuleSet" )
         RelinkInstanceToInstance( mSARuleS, "ChildStudentAccountRuleSet", mChildSARuleS, "StudentAccountRuleSet" );
         //:nRC = QualifyRuleSet( mChildSARuleS, mSAProfTEST, mPersonTEST )
         nRC = omSARuleS_QualifyRuleSet( mChildSARuleS, mSAProfTEST, mPersonTEST );
         //:DropView( mChildSARuleS )
         DropView( mChildSARuleS );

         //:IF  nRC < 0  
         if ( nRC < 0 )
         { 
            //:mSARuleS.StudentAccountRuleSet.wQualTrue = "N"
            SetAttributeFromString( mSARuleS, "StudentAccountRuleSet", "wQualTrue", "N" );
            //:ELSE 
         } 
         else
         { 
            //:mSARuleS.StudentAccountRuleSet.wQualTrue = "Y"
            SetAttributeFromString( mSARuleS, "StudentAccountRuleSet", "wQualTrue", "Y" );
         } 

         //:END
         //:// if this is AND but we found a rule that does not apply, bail out now.
         //:IF  nRC < 0  AND szANDOR = "A"
         if ( nRC < 0 && ZeidonStringCompare( szANDOR, 1, 0, "A", 1, 0, 2 ) == 0 )
         { 
            //:DropView( mSAProfTEST ) 
            DropView( mSAProfTEST );
            //:IF  mPersonTEST  > 0 
            if ( mPersonTEST != null )
            { 
               //:DropView( mPersonTEST )
               DropView( mPersonTEST );
            } 

            //:END 
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mSARuleS, "ChildStudentAccountRuleSet", "" );
      //:END
   } 

   //:END
   //:DropView( mSAProfTEST ) 
   DropView( mSAProfTEST );

   //:IF mPersonTEST > 0
   if ( mPersonTEST != null )
   { 
      //:DropView( mPersonTEST )
      DropView( mPersonTEST );
   } 

   //:END 
   //:FOR EACH mSARuleS.StudentAccountRule 
   RESULT = SetCursorFirstEntity( mSARuleS, "StudentAccountRule", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSARuleS.StudentAccountRule.wQualTrue = "Y"
      if ( CompareAttributeToString( mSARuleS, "StudentAccountRule", "wQualTrue", "Y" ) == 0 )
      { 
         //:szAnyTrue = "Y"
          {StringBuilder sb_szAnyTrue;
         if ( szAnyTrue == null )
            sb_szAnyTrue = new StringBuilder( 32 );
         else
            sb_szAnyTrue = new StringBuilder( szAnyTrue );
                  ZeidonStringCopy( sb_szAnyTrue, 1, 0, "Y", 1, 0, 2 );
         szAnyTrue = sb_szAnyTrue.toString( );}
      } 

      //:END
      //:IF mSARuleS.StudentAccountRule.wQualTrue = "N"
      if ( CompareAttributeToString( mSARuleS, "StudentAccountRule", "wQualTrue", "N" ) == 0 )
      { 
         //:szAnyFalse = "Y"
          {StringBuilder sb_szAnyFalse;
         if ( szAnyFalse == null )
            sb_szAnyFalse = new StringBuilder( 32 );
         else
            sb_szAnyFalse = new StringBuilder( szAnyFalse );
                  ZeidonStringCopy( sb_szAnyFalse, 1, 0, "Y", 1, 0, 2 );
         szAnyFalse = sb_szAnyFalse.toString( );}
      } 

      RESULT = SetCursorNextEntity( mSARuleS, "StudentAccountRule", "" );
      //:END
   } 

   //:END 

   //:FOR EACH mSARuleS.ChildStudentAccountRuleSet 
   RESULT = SetCursorFirstEntity( mSARuleS, "ChildStudentAccountRuleSet", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSARuleS.ChildStudentAccountRuleSet.wQualTrue = "Y"
      if ( CompareAttributeToString( mSARuleS, "ChildStudentAccountRuleSet", "wQualTrue", "Y" ) == 0 )
      { 
         //:szAnyTrue = "Y"
          {StringBuilder sb_szAnyTrue;
         if ( szAnyTrue == null )
            sb_szAnyTrue = new StringBuilder( 32 );
         else
            sb_szAnyTrue = new StringBuilder( szAnyTrue );
                  ZeidonStringCopy( sb_szAnyTrue, 1, 0, "Y", 1, 0, 2 );
         szAnyTrue = sb_szAnyTrue.toString( );}
      } 

      //:END
      //:IF mSARuleS.ChildStudentAccountRuleSet.wQualTrue = "N"
      if ( CompareAttributeToString( mSARuleS, "ChildStudentAccountRuleSet", "wQualTrue", "N" ) == 0 )
      { 
         //:szAnyFalse = "Y"
          {StringBuilder sb_szAnyFalse;
         if ( szAnyFalse == null )
            sb_szAnyFalse = new StringBuilder( 32 );
         else
            sb_szAnyFalse = new StringBuilder( szAnyFalse );
                  ZeidonStringCopy( sb_szAnyFalse, 1, 0, "Y", 1, 0, 2 );
         szAnyFalse = sb_szAnyFalse.toString( );}
      } 

      RESULT = SetCursorNextEntity( mSARuleS, "ChildStudentAccountRuleSet", "" );
      //:END
   } 

   //:END 

   //:// now compare and or or 
   //:IF szANDOR = "A"
   if ( ZeidonStringCompare( szANDOR, 1, 0, "A", 1, 0, 2 ) == 0 )
   { 
      //:IF szAnyFalse = "Y"
      if ( ZeidonStringCompare( szAnyFalse, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:mSARuleS.StudentAccountRuleSet.wQualTrue = "N"
         SetAttributeFromString( mSARuleS, "StudentAccountRuleSet", "wQualTrue", "N" );
         //:RETURN -1
         if(8==8)return( -1 );
         //:ELSE 
      } 
      else
      { 
         //:mSARuleS.StudentAccountRuleSet.wQualTrue = "Y"
         SetAttributeFromString( mSARuleS, "StudentAccountRuleSet", "wQualTrue", "Y" );
         //:RETURN 1
         if(8==8)return( 1 );
      } 

      //:END
   } 

   //:END 
   //:IF szANDOR = "O" 
   if ( ZeidonStringCompare( szANDOR, 1, 0, "O", 1, 0, 2 ) == 0 )
   { 
      //:IF szAnyTrue = "Y"
      if ( ZeidonStringCompare( szAnyTrue, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:mSARuleS.StudentAccountRuleSet.wQualTrue = "Y"
         SetAttributeFromString( mSARuleS, "StudentAccountRuleSet", "wQualTrue", "Y" );
         //:RETURN 1
         if(8==8)return( 1 );
         //:ELSE 
      } 
      else
      { 
         //:mSARuleS.StudentAccountRuleSet.wQualTrue = "N"
         SetAttributeFromString( mSARuleS, "StudentAccountRuleSet", "wQualTrue", "N" );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
   } 

   //:END
   //:// if it gets here, something is wrong.
   //:RETURN -1
   return( -1 );
// END
} 


//:TRANSFORMATION OPERATION
//:ApplyRule( VIEW mSARuleS BASED ON LOD mSARuleS,
//:           VIEW mSAProf  BASED ON LOD mSAProf,
//:           VIEW mPerson  BASED ON LOD mPerson )

//:   VIEW   vAnyView
public int 
omSARuleS_ApplyRule( View     mSARuleS,
                     View     mSAProf,
                     View     mPerson )
{
   zVIEW    vAnyView = new zVIEW( );
   //:VIEW   vTestView
   zVIEW    vTestView = new zVIEW( );
   //:VIEW   vCompareView
   zVIEW    vCompareView = new zVIEW( );
   //:STRING (  1024  ) szStringToCompare
   String   szStringToCompare = null;
   //:DECIMAL dValueToCompare
   double  dValueToCompare = 0.0;
   //:STRING ( 64 ) szView
   String   szView = null;
   //:STRING ( 64 ) szEntity
   String   szEntity = null;
   //:STRING ( 64 ) szAttribute
   String   szAttribute = null;
   //:STRING ( 1 )  szAttrType
   String   szAttrType = null;
   //:STRING ( 10 ) szOper 
   String   szOper = null;

   //:STRING ( 64 ) szViewValue
   String   szViewValue = null;
   //:STRING ( 64 ) szRootEntity
   String   szRootEntity = null;
   //:STRING ( 64 ) szEntityValue
   String   szEntityValue = null;
   //:STRING ( 64 ) szAttributeValue
   String   szAttributeValue = null;
   //:STRING ( 64 ) szMess
   String   szMess = null;
   //:INTEGER nCompareResult
   int      nCompareResult = 0;
   //:INTEGER nCompareResult2
   int      nCompareResult2 = 0;
   //:INTEGER nRC 
   int      nRC = 0;
   //:INTEGER nLoop 
   int      nLoop = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      RESULT = 0;


   //:/* 
   //:   NOTE: IsParentRule - (Y/N) is temporarily being used as a flag to determine if the rule we are attempting to 
   //:   apply is for the currently selected entity or can be for ANY twin entity. For example. If we wanted to see if
   //:   any Class entity is being taken as an Audit, we would set IsParentRule = Y. That indicates to loop through the 
   //:   classes to see if any one matches.
   //:*/
   //:// mSARuleS.StudentAccountRule.wQualTrue = "Y"
   //:// assume that the rule does NOT apply until we see otherwise.
   //:// get the values we are looking for 
   //:szViewValue      = mSARuleS.StudentAccountRule.QualViewValue 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szViewValue;
   if ( szViewValue == null )
      sb_szViewValue = new StringBuilder( 32 );
   else
      sb_szViewValue = new StringBuilder( szViewValue );
       GetVariableFromAttribute( sb_szViewValue, mi_lTempInteger_0, 'S', 65, mSARuleS, "StudentAccountRule", "QualViewValue", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szViewValue = sb_szViewValue.toString( );}
   //:szEntityValue    = mSARuleS.StudentAccountRule.QualEntityValue 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szEntityValue;
   if ( szEntityValue == null )
      sb_szEntityValue = new StringBuilder( 32 );
   else
      sb_szEntityValue = new StringBuilder( szEntityValue );
       GetVariableFromAttribute( sb_szEntityValue, mi_lTempInteger_1, 'S', 65, mSARuleS, "StudentAccountRule", "QualEntityValue", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szEntityValue = sb_szEntityValue.toString( );}
   //:szAttributeValue = mSARuleS.StudentAccountRule.QualAttributeValue
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szAttributeValue;
   if ( szAttributeValue == null )
      sb_szAttributeValue = new StringBuilder( 32 );
   else
      sb_szAttributeValue = new StringBuilder( szAttributeValue );
       GetVariableFromAttribute( sb_szAttributeValue, mi_lTempInteger_2, 'S', 65, mSARuleS, "StudentAccountRule", "QualAttributeValue", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szAttributeValue = sb_szAttributeValue.toString( );}

   //:szView      = mSARuleS.StudentAccountRule.QualViewName 
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szView;
   if ( szView == null )
      sb_szView = new StringBuilder( 32 );
   else
      sb_szView = new StringBuilder( szView );
       GetVariableFromAttribute( sb_szView, mi_lTempInteger_3, 'S', 65, mSARuleS, "StudentAccountRule", "QualViewName", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szView = sb_szView.toString( );}
   //:szEntity    = mSARuleS.StudentAccountRule.QualEntityName 
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
   StringBuilder sb_szEntity;
   if ( szEntity == null )
      sb_szEntity = new StringBuilder( 32 );
   else
      sb_szEntity = new StringBuilder( szEntity );
       GetVariableFromAttribute( sb_szEntity, mi_lTempInteger_4, 'S', 65, mSARuleS, "StudentAccountRule", "QualEntityName", "", 0 );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );
   szEntity = sb_szEntity.toString( );}
   //:szAttribute = mSARuleS.StudentAccountRule.QualAttributeName 
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
   StringBuilder sb_szAttribute;
   if ( szAttribute == null )
      sb_szAttribute = new StringBuilder( 32 );
   else
      sb_szAttribute = new StringBuilder( szAttribute );
       GetVariableFromAttribute( sb_szAttribute, mi_lTempInteger_5, 'S', 65, mSARuleS, "StudentAccountRule", "QualAttributeName", "", 0 );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );
   szAttribute = sb_szAttribute.toString( );}

   //:szAttrType  = mSARuleS.StudentAccountRule.QualAttributeType 
   {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
   StringBuilder sb_szAttrType;
   if ( szAttrType == null )
      sb_szAttrType = new StringBuilder( 32 );
   else
      sb_szAttrType = new StringBuilder( szAttrType );
       GetVariableFromAttribute( sb_szAttrType, mi_lTempInteger_6, 'S', 2, mSARuleS, "StudentAccountRule", "QualAttributeType", "", 0 );
   lTempInteger_6 = mi_lTempInteger_6.intValue( );
   szAttrType = sb_szAttrType.toString( );}
   //:szOper      = mSARuleS.StudentAccountRule.QualOperator 
   {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
   StringBuilder sb_szOper;
   if ( szOper == null )
      sb_szOper = new StringBuilder( 32 );
   else
      sb_szOper = new StringBuilder( szOper );
       GetVariableFromAttribute( sb_szOper, mi_lTempInteger_7, 'S', 11, mSARuleS, "StudentAccountRule", "QualOperator", "", 0 );
   lTempInteger_7 = mi_lTempInteger_7.intValue( );
   szOper = sb_szOper.toString( );}
   //:vAnyView = 0
   vAnyView = null;
   //:IF szView = "mSAProf"
   if ( ZeidonStringCompare( szView, 1, 0, "mSAProf", 1, 0, 65 ) == 0 )
   { 
      //:// CreateViewFromView( vAnyView, mSAProf )    
      //:vAnyView = mSAProf 
      SetViewFromView( vAnyView, mSAProf );
      //:szRootEntity = "StudentAccountProfile"
       {StringBuilder sb_szRootEntity;
      if ( szRootEntity == null )
         sb_szRootEntity = new StringBuilder( 32 );
      else
         sb_szRootEntity = new StringBuilder( szRootEntity );
            ZeidonStringCopy( sb_szRootEntity, 1, 0, "StudentAccountProfile", 1, 0, 65 );
      szRootEntity = sb_szRootEntity.toString( );}
      //:ELSE 
   } 
   else
   { 
      //:IF szView = "mPerson"
      if ( ZeidonStringCompare( szView, 1, 0, "mPerson", 1, 0, 65 ) == 0 )
      { 
         //:// CreateViewFromView( vAnyView, mPerson )    
         //:vAnyView = mPerson 
         SetViewFromView( vAnyView, mPerson );
         //:szRootEntity = "Person"
          {StringBuilder sb_szRootEntity;
         if ( szRootEntity == null )
            sb_szRootEntity = new StringBuilder( 32 );
         else
            sb_szRootEntity = new StringBuilder( szRootEntity );
                  ZeidonStringCopy( sb_szRootEntity, 1, 0, "Person", 1, 0, 65 );
         szRootEntity = sb_szRootEntity.toString( );}
         //:ELSE 
      } 
      else
      { 
         //:nRC = GetViewByName( vAnyView, szView, mSARuleS, zLEVEL_TASK )
         nRC = GetViewByName( vAnyView, szView, mSARuleS, zLEVEL_TASK );
         //:IF nRC <= 0
         if ( nRC <= 0 )
         { 
            //:RETURN -1 
            if(8==8)return( -1 );
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:nRC = zLodContainsAttribute( vAnyView, szEntity, szAttribute )
   nRC = zLodContainsAttribute( vAnyView, szEntity, szAttribute );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:szMess = "Either " + szEntity + " or " + szAttribute  + " does not exist "
       {StringBuilder sb_szMess;
      if ( szMess == null )
         sb_szMess = new StringBuilder( 32 );
      else
         sb_szMess = new StringBuilder( szMess );
            ZeidonStringCopy( sb_szMess, 1, 0, "Either ", 1, 0, 65 );
      szMess = sb_szMess.toString( );}
       {StringBuilder sb_szMess;
      if ( szMess == null )
         sb_szMess = new StringBuilder( 32 );
      else
         sb_szMess = new StringBuilder( szMess );
            ZeidonStringConcat( sb_szMess, 1, 0, szEntity, 1, 0, 65 );
      szMess = sb_szMess.toString( );}
       {StringBuilder sb_szMess;
      if ( szMess == null )
         sb_szMess = new StringBuilder( 32 );
      else
         sb_szMess = new StringBuilder( szMess );
            ZeidonStringConcat( sb_szMess, 1, 0, " or ", 1, 0, 65 );
      szMess = sb_szMess.toString( );}
       {StringBuilder sb_szMess;
      if ( szMess == null )
         sb_szMess = new StringBuilder( 32 );
      else
         sb_szMess = new StringBuilder( szMess );
            ZeidonStringConcat( sb_szMess, 1, 0, szAttribute, 1, 0, 65 );
      szMess = sb_szMess.toString( );}
       {StringBuilder sb_szMess;
      if ( szMess == null )
         sb_szMess = new StringBuilder( 32 );
      else
         sb_szMess = new StringBuilder( szMess );
            ZeidonStringConcat( sb_szMess, 1, 0, " does not exist ", 1, 0, 65 );
      szMess = sb_szMess.toString( );}
      //:mSARuleS.StudentAccountRule.wQualTrue = "N" 
      SetAttributeFromString( mSARuleS, "StudentAccountRule", "wQualTrue", "N" );
      //:IssueError( mSARuleS, 0, 0, szMess )
      IssueError( mSARuleS, 0, 0, szMess );
      //:RETURN -1 
      if(8==8)return( -1 );
   } 

   //:END

   //:CreateViewFromView( vTestView, vAnyView )    
   CreateViewFromView( vTestView, vAnyView );
   //:// see if we can find the entity
   //:nRC = SetCursorFirstEntity( vTestView, szEntity, "" )
   nRC = SetCursorFirstEntity( vTestView, szEntity, "" );
   //:IF nRC < zCURSOR_SET 
   if ( nRC < zCURSOR_SET )
   { 
      //:mSARuleS.StudentAccountRule.wQualTrue = "N" 
      SetAttributeFromString( mSARuleS, "StudentAccountRule", "wQualTrue", "N" );
      //:DropView( vTestView )
      DropView( vTestView );
      //:RETURN -1 
      if(8==8)return( -1 );
   } 

   //:END
   //:DropView( vTestView )
   DropView( vTestView );

   //:IF mSARuleS.StudentAccountRule.ApplyToAny = "Y" // loop through for any
   if ( CompareAttributeToString( mSARuleS, "StudentAccountRule", "ApplyToAny", "Y" ) == 0 )
   { 
      //:nLoop = SetCursorFirstEntity( vAnyView, szEntity, szRootEntity )
      nLoop = SetCursorFirstEntity( vAnyView, szEntity, szRootEntity );
      //:LOOP WHILE ( nLoop >= zCURSOR_SET )
      while ( nLoop >= zCURSOR_SET )
      { 
         //:IF szViewValue != ""
         if ( ZeidonStringCompare( szViewValue, 1, 0, "", 1, 0, 65 ) != 0 )
         { 
            //:GET VIEW vCompareView NAMED szViewValue
            RESULT = GetViewByName( vCompareView, szViewValue, mSARuleS, zLEVEL_TASK );
            //:nCompareResult = 
            //:   CompareAttributeToAttribute( vCompareView, szEntityValue, szAttributeValue,
            //:                                vAnyView, szEntity, szAttribute )
            nCompareResult = CompareAttributeToAttribute( vCompareView, szEntityValue, szAttributeValue, vAnyView, szEntity, szAttribute );
            //:ELSE 
         } 
         else
         { 
            //:// see what type the attribute is. 
            //:IF szAttrType = "S"
            if ( ZeidonStringCompare( szAttrType, 1, 0, "S", 1, 0, 2 ) == 0 )
            { 
               //:GetStringFromAttribute( szStringToCompare, vAnyView, szEntity, szAttribute ) 
               {StringBuilder sb_szStringToCompare;
               if ( szStringToCompare == null )
                  sb_szStringToCompare = new StringBuilder( 32 );
               else
                  sb_szStringToCompare = new StringBuilder( szStringToCompare );
                               GetStringFromAttribute( sb_szStringToCompare, vAnyView, szEntity, szAttribute );
               szStringToCompare = sb_szStringToCompare.toString( );}
               //:nCompareResult = 
               //:   CompareAttributeToString( mSARuleS, "StudentAccountRule", "QualStringValue", szStringToCompare )
               nCompareResult = CompareAttributeToString( mSARuleS, "StudentAccountRule", "QualStringValue", szStringToCompare );
               //:ELSE 
            } 
            else
            { 
               //:GetDecimalFromAttribute( dValueToCompare, vAnyView, szEntity, szAttribute )
               {MutableDouble md_dValueToCompare = new MutableDouble( dValueToCompare );
                               GetDecimalFromAttribute( md_dValueToCompare, vAnyView, szEntity, szAttribute );
               dValueToCompare = md_dValueToCompare.doubleValue( );}

               //:nCompareResult = 
               //:   CompareAttributeToDecimal( mSARuleS, "StudentAccountRule", "QualMinNumericValue", dValueToCompare )
               nCompareResult = CompareAttributeToDecimal( mSARuleS, "StudentAccountRule", "QualMinNumericValue", dValueToCompare );
               //:nCompareResult2 = 
               //:   CompareAttributeToDecimal( mSARuleS, "StudentAccountRule", "QualMaxNumericValue", dValueToCompare )
               nCompareResult2 = CompareAttributeToDecimal( mSARuleS, "StudentAccountRule", "QualMaxNumericValue", dValueToCompare );
            } 


            //:END 
         } 

         //:END 
         //:nRC = CompareResults( szOper, nCompareResult, nCompareResult2 )
         nRC = omSARuleS_CompareResults( szOper, nCompareResult, nCompareResult2 );
         //:IF nRC = 1 
         if ( nRC == 1 )
         { 
            //://IF vAnyView != 0
            //://   DropView( vAnyView )    
            //://END
            //:mSARuleS.StudentAccountRule.wQualTrue = "Y"
            SetAttributeFromString( mSARuleS, "StudentAccountRule", "wQualTrue", "Y" );
            //:RETURN 1
            if(8==8)return( 1 );
         } 

         //:END 
         //:nLoop = SetCursorNextEntity( vAnyView, szEntity, szRootEntity )
         nLoop = SetCursorNextEntity( vAnyView, szEntity, szRootEntity );
      } 

      //:END
      //:IF nRC = -1
      if ( nRC == -1 )
      { 
         //://IF vAnyView != 0
         //://   DropView( vAnyView )    
         //://END
         //:mSARuleS.StudentAccountRule.wQualTrue = "N"
         SetAttributeFromString( mSARuleS, "StudentAccountRule", "wQualTrue", "N" );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
      //:ELSE 
   } 
   else
   { 
      //:IF mSARuleS.StudentAccountRule.ApplyToAll = "Y" // loop through for any
      if ( CompareAttributeToString( mSARuleS, "StudentAccountRule", "ApplyToAll", "Y" ) == 0 )
      { 
         //:nLoop = SetCursorFirstEntity( vAnyView, szEntity, szRootEntity )
         nLoop = SetCursorFirstEntity( vAnyView, szEntity, szRootEntity );
         //:mSARuleS.StudentAccountRule.wQualTrue = "Y"
         SetAttributeFromString( mSARuleS, "StudentAccountRule", "wQualTrue", "Y" );
         //:IF ( nLoop  != zCURSOR_SET )  
         if ( nLoop != zCURSOR_SET )
         { 
            //:mSARuleS.StudentAccountRule.wQualTrue = "N"
            SetAttributeFromString( mSARuleS, "StudentAccountRule", "wQualTrue", "N" );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
         //:LOOP WHILE ( nLoop  = zCURSOR_SET )
         while ( nLoop == zCURSOR_SET )
         { 
            //:IF szViewValue != ""
            if ( ZeidonStringCompare( szViewValue, 1, 0, "", 1, 0, 65 ) != 0 )
            { 
               //:GET VIEW vCompareView NAMED szViewValue
               RESULT = GetViewByName( vCompareView, szViewValue, mSARuleS, zLEVEL_TASK );
               //:nCompareResult = 
               //:   CompareAttributeToAttribute( vCompareView, szEntityValue, szAttributeValue,
               //:                                vAnyView, szEntity, szAttribute )
               nCompareResult = CompareAttributeToAttribute( vCompareView, szEntityValue, szAttributeValue, vAnyView, szEntity, szAttribute );
               //:ELSE 
            } 
            else
            { 
               //:// see what type the attribute is. 
               //:IF szAttrType = "S"
               if ( ZeidonStringCompare( szAttrType, 1, 0, "S", 1, 0, 2 ) == 0 )
               { 
                  //:GetStringFromAttribute( szStringToCompare, vAnyView, szEntity, szAttribute ) 
                  {StringBuilder sb_szStringToCompare;
                  if ( szStringToCompare == null )
                     sb_szStringToCompare = new StringBuilder( 32 );
                  else
                     sb_szStringToCompare = new StringBuilder( szStringToCompare );
                                     GetStringFromAttribute( sb_szStringToCompare, vAnyView, szEntity, szAttribute );
                  szStringToCompare = sb_szStringToCompare.toString( );}
                  //:nCompareResult = 
                  //:   CompareAttributeToString( mSARuleS, "StudentAccountRule", "QualStringValue", szStringToCompare )
                  nCompareResult = CompareAttributeToString( mSARuleS, "StudentAccountRule", "QualStringValue", szStringToCompare );
                  //:ELSE 
               } 
               else
               { 
                  //:GetDecimalFromAttribute( dValueToCompare, vAnyView, szEntity, szAttribute )
                  {MutableDouble md_dValueToCompare = new MutableDouble( dValueToCompare );
                                     GetDecimalFromAttribute( md_dValueToCompare, vAnyView, szEntity, szAttribute );
                  dValueToCompare = md_dValueToCompare.doubleValue( );}

                  //:nCompareResult = 
                  //:   CompareAttributeToDecimal( mSARuleS, "StudentAccountRule", "QualMinNumericValue", dValueToCompare )
                  nCompareResult = CompareAttributeToDecimal( mSARuleS, "StudentAccountRule", "QualMinNumericValue", dValueToCompare );
                  //:nCompareResult2 = 
                  //:   CompareAttributeToDecimal( mSARuleS, "StudentAccountRule", "QualMaxNumericValue", dValueToCompare )
                  nCompareResult2 = CompareAttributeToDecimal( mSARuleS, "StudentAccountRule", "QualMaxNumericValue", dValueToCompare );
               } 


               //:END 
            } 

            //:END 
            //:nRC = CompareResults( szOper, nCompareResult, nCompareResult2 )
            nRC = omSARuleS_CompareResults( szOper, nCompareResult, nCompareResult2 );
            //:IF nRC != 1 
            if ( nRC != 1 )
            { 
               //:mSARuleS.StudentAccountRule.wQualTrue = "N"
               SetAttributeFromString( mSARuleS, "StudentAccountRule", "wQualTrue", "N" );
               //:RETURN 1
               if(8==8)return( 1 );
            } 

            //:END 
            //:nLoop = SetCursorNextEntity( vAnyView, szEntity, szRootEntity )
            nLoop = SetCursorNextEntity( vAnyView, szEntity, szRootEntity );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF szViewValue != ""
         if ( ZeidonStringCompare( szViewValue, 1, 0, "", 1, 0, 65 ) != 0 )
         { 
            //:GET VIEW vCompareView NAMED szViewValue
            RESULT = GetViewByName( vCompareView, szViewValue, mSARuleS, zLEVEL_TASK );
            //:nCompareResult = 
            //:   CompareAttributeToAttribute( vCompareView, szEntityValue, szAttributeValue,
            //:                                vAnyView, szEntity, szAttribute )
            nCompareResult = CompareAttributeToAttribute( vCompareView, szEntityValue, szAttributeValue, vAnyView, szEntity, szAttribute );
            //:ELSE 
         } 
         else
         { 
            //:// see what type the attribute is. 
            //:IF szAttrType = "S"
            if ( ZeidonStringCompare( szAttrType, 1, 0, "S", 1, 0, 2 ) == 0 )
            { 
               //:GetStringFromAttribute( szStringToCompare, vAnyView, szEntity, szAttribute ) 
               {StringBuilder sb_szStringToCompare;
               if ( szStringToCompare == null )
                  sb_szStringToCompare = new StringBuilder( 32 );
               else
                  sb_szStringToCompare = new StringBuilder( szStringToCompare );
                               GetStringFromAttribute( sb_szStringToCompare, vAnyView, szEntity, szAttribute );
               szStringToCompare = sb_szStringToCompare.toString( );}
               //:nCompareResult = 
               //:   CompareAttributeToString( mSARuleS, "StudentAccountRule", "QualStringValue", szStringToCompare )
               nCompareResult = CompareAttributeToString( mSARuleS, "StudentAccountRule", "QualStringValue", szStringToCompare );
               //:ELSE 
            } 
            else
            { 
               //:GetDecimalFromAttribute( dValueToCompare, vAnyView, szEntity, szAttribute )
               {MutableDouble md_dValueToCompare = new MutableDouble( dValueToCompare );
                               GetDecimalFromAttribute( md_dValueToCompare, vAnyView, szEntity, szAttribute );
               dValueToCompare = md_dValueToCompare.doubleValue( );}

               //:nCompareResult = 
               //:   CompareAttributeToDecimal( mSARuleS, "StudentAccountRule", "QualMinNumericValue", dValueToCompare )
               nCompareResult = CompareAttributeToDecimal( mSARuleS, "StudentAccountRule", "QualMinNumericValue", dValueToCompare );
               //:nCompareResult2 = 
               //:   CompareAttributeToDecimal( mSARuleS, "StudentAccountRule", "QualMaxNumericValue", dValueToCompare )
               nCompareResult2 = CompareAttributeToDecimal( mSARuleS, "StudentAccountRule", "QualMaxNumericValue", dValueToCompare );
            } 

            //:END 
         } 

         //:END

         //://IF vAnyView != 0
         //://   DropView( vAnyView )    
         //://END

         //:nRC = CompareResults( szOper, nCompareResult, nCompareResult2 )
         nRC = omSARuleS_CompareResults( szOper, nCompareResult, nCompareResult2 );
         //:IF nRC = 1 
         if ( nRC == 1 )
         { 
            //:mSARuleS.StudentAccountRule.wQualTrue = "Y"
            SetAttributeFromString( mSARuleS, "StudentAccountRule", "wQualTrue", "Y" );
            //:RETURN 1
            if(8==8)return( 1 );
         } 

         //:END 
         //:IF nRC = -1   
         if ( nRC == -1 )
         { 
            //:mSARuleS.StudentAccountRule.wQualTrue = "N"
            SetAttributeFromString( mSARuleS, "StudentAccountRule", "wQualTrue", "N" );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:CompareResults( STRING ( 32 ) szOper,
//:                INTEGER nCompareMin,
//:                INTEGER nCompareMax )
private int 
omSARuleS_CompareResults( String   szOper,
                          int      nCompareMin,
                          int      nCompareMax )
{


   //:// -1   attribute value is less than string value 
   //://  0   attribute value is equal to string value
   //://  1   attribute value is greater than string value 

   //:IF szOper = "="
   if ( ZeidonStringCompare( szOper, 1, 0, "=", 1, 0, 33 ) == 0 )
   { 
      //:IF nCompareMin = 0 // equal 
      if ( nCompareMin == 0 )
      { 
         //:RETURN 1
         if(8==8)return( 1 );
         //:ELSE 
      } 
      else
      { 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END 
   } 

   //:END

   //:IF szOper = "<"
   if ( ZeidonStringCompare( szOper, 1, 0, "<", 1, 0, 33 ) == 0 )
   { 
      //:IF nCompareMin = 1 // attr less than value 
      if ( nCompareMin == 1 )
      { 
         //:RETURN 1
         if(8==8)return( 1 );
         //:ELSE 
      } 
      else
      { 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END 
   } 

   //:END

   //:IF szOper = "<="
   if ( ZeidonStringCompare( szOper, 1, 0, "<=", 1, 0, 33 ) == 0 )
   { 
      //:IF nCompareMin = 1 OR nCompareMin = 0 // attr less than value  OR equal 
      if ( nCompareMin == 1 || nCompareMin == 0 )
      { 
         //:RETURN 1
         if(8==8)return( 1 );
         //:ELSE 
      } 
      else
      { 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END 
   } 

   //:END

   //:IF szOper = ">"
   if ( ZeidonStringCompare( szOper, 1, 0, ">", 1, 0, 33 ) == 0 )
   { 
      //:IF nCompareMin = -1 // attr greater than value  
      if ( nCompareMin == -1 )
      { 
         //:RETURN 1
         if(8==8)return( 1 );
         //:ELSE 
      } 
      else
      { 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END 
   } 

   //:END

   //:IF szOper = ">="
   if ( ZeidonStringCompare( szOper, 1, 0, ">=", 1, 0, 33 ) == 0 )
   { 
      //:IF nCompareMin = -1 OR nCompareMin = 0 // attr greater than value  OR equal 
      if ( nCompareMin == -1 || nCompareMin == 0 )
      { 
         //:RETURN 1
         if(8==8)return( 1 );
         //:ELSE 
      } 
      else
      { 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END 
   } 

   //:END

   //:IF szOper = "<>"
   if ( ZeidonStringCompare( szOper, 1, 0, "<>", 1, 0, 33 ) == 0 )
   { 
      //:IF nCompareMin != 0 // attr not equal 
      if ( nCompareMin != 0 )
      { 
         //:RETURN 1
         if(8==8)return( 1 );
         //:ELSE 
      } 
      else
      { 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END 
   } 

   //:END

   //:IF szOper = "between"
   if ( ZeidonStringCompare( szOper, 1, 0, "between", 1, 0, 33 ) == 0 )
   { 
      //:IF nCompareMin <= 0 AND nCompareMax >= 0
      if ( nCompareMin <= 0 && nCompareMax >= 0 )
      { 
         //:RETURN 1
         if(8==8)return( 1 );
         //:ELSE 
      } 
      else
      { 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END 
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dGetRowCalcAward( VIEW mSARuleS BASED ON LOD mSARuleS,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )
//:   DECIMAL dMinAward
public int 
omSARuleS_dGetRowCalcAward( View     mSARuleS,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   double  dMinAward = 0.0;
   //:DECIMAL dAwardAmount
   double  dAwardAmount = 0.0;
   //:DECIMAL dValue
   double  dValue = 0.0;
   //:DECIMAL dNewValue 
   double  dNewValue = 0.0;
   //:STRING ( 64 ) szView
   String   szView = null;
   //:STRING ( 64 ) szEntity
   String   szEntity = null;
   //:STRING ( 64 ) szAttribute
   String   szAttribute = null;
   //:STRING ( 64 ) szAttrType
   String   szAttrType = null;
   //:STRING ( 2000 ) szValue
   String   szValue = null;
   //:STRING (  1  ) szUseMult
   String   szUseMult = null;
   //:VIEW vAnyView
   zVIEW    vAnyView = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      RESULT = 0;


   //:szUseMult = ""
    {StringBuilder sb_szUseMult;
   if ( szUseMult == null )
      sb_szUseMult = new StringBuilder( 32 );
   else
      sb_szUseMult = new StringBuilder( szUseMult );
      ZeidonStringCopy( sb_szUseMult, 1, 0, "", 1, 0, 2 );
   szUseMult = sb_szUseMult.toString( );}
   //:dMinAward = 0
   dMinAward = 0;

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:szUseMult = mSARuleS.SACalcLookupRow.UseAmountAsMultiplier 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szUseMult;
         if ( szUseMult == null )
            sb_szUseMult = new StringBuilder( 32 );
         else
            sb_szUseMult = new StringBuilder( szUseMult );
                   GetVariableFromAttribute( sb_szUseMult, mi_lTempInteger_0, 'S', 2, mSARuleS, "SACalcLookupRow", "UseAmountAsMultiplier", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szUseMult = sb_szUseMult.toString( );}
         //:dMinAward = mSARuleS.SACalcLookupRow.MinimumAwardAmount 
         {MutableDouble md_dMinAward = new MutableDouble( dMinAward );
                   GetDecimalFromAttribute( md_dMinAward, mSARuleS, "SACalcLookupRow", "MinimumAwardAmount" );
         dMinAward = md_dMinAward.doubleValue( );}
         //:szAttrType  = mSARuleS.SACalcLookupRow.QualAttributeType 
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szAttrType;
         if ( szAttrType == null )
            sb_szAttrType = new StringBuilder( 32 );
         else
            sb_szAttrType = new StringBuilder( szAttrType );
                   GetVariableFromAttribute( sb_szAttrType, mi_lTempInteger_1, 'S', 65, mSARuleS, "SACalcLookupRow", "QualAttributeType", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szAttrType = sb_szAttrType.toString( );}
         //:// see what type the attribute is. 
         //:IF szAttrType = "S"
         if ( ZeidonStringCompare( szAttrType, 1, 0, "S", 1, 0, 65 ) == 0 )
         { 
            //:szValue = mSARuleS.SACalcLookupRow.AwardAmount
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                         GetVariableFromAttribute( sb_szValue, mi_lTempInteger_2, 'S', 2001, mSARuleS, "SACalcLookupRow", "AwardAmount", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szValue = sb_szValue.toString( );}
            //:StoreStringInRecord( mSARuleS, InternalEntityStructure, 
            //:                     InternalAttribStructure, szValue )
            StoreStringInRecord( mSARuleS, InternalEntityStructure, InternalAttribStructure, szValue );
            //:ELSE
         } 
         else
         { 
            //:IF szUseMult  != "Y"  
            if ( ZeidonStringCompare( szUseMult, 1, 0, "Y", 1, 0, 2 ) != 0 )
            { 
               //:dAwardAmount = mSARuleS.SACalcLookupRow.AwardAmount
               {MutableDouble md_dAwardAmount = new MutableDouble( dAwardAmount );
                               GetDecimalFromAttribute( md_dAwardAmount, mSARuleS, "SACalcLookupRow", "AwardAmount" );
               dAwardAmount = md_dAwardAmount.doubleValue( );}
               //:StoreValueInRecord( mSARuleS, InternalEntityStructure, 
               //:                    InternalAttribStructure, dAwardAmount, 0 )
               StoreValueInRecord( mSARuleS, InternalEntityStructure, InternalAttribStructure, dAwardAmount, 0 );
               //:ELSE 
            } 
            else
            { 
               //:// get the values we are looking for    
               //:szView      = mSARuleS.SACalcLookupRow.QualViewName 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szView;
               if ( szView == null )
                  sb_szView = new StringBuilder( 32 );
               else
                  sb_szView = new StringBuilder( szView );
                               GetVariableFromAttribute( sb_szView, mi_lTempInteger_3, 'S', 65, mSARuleS, "SACalcLookupRow", "QualViewName", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szView = sb_szView.toString( );}
               //:szEntity    = mSARuleS.SACalcLookupRow.QualEntityName 
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szEntity;
               if ( szEntity == null )
                  sb_szEntity = new StringBuilder( 32 );
               else
                  sb_szEntity = new StringBuilder( szEntity );
                               GetVariableFromAttribute( sb_szEntity, mi_lTempInteger_4, 'S', 65, mSARuleS, "SACalcLookupRow", "QualEntityName", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szEntity = sb_szEntity.toString( );}
               //:szAttribute = mSARuleS.SACalcLookupRow.QualAttributeName 
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szAttribute;
               if ( szAttribute == null )
                  sb_szAttribute = new StringBuilder( 32 );
               else
                  sb_szAttribute = new StringBuilder( szAttribute );
                               GetVariableFromAttribute( sb_szAttribute, mi_lTempInteger_5, 'S', 65, mSARuleS, "SACalcLookupRow", "QualAttributeName", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szAttribute = sb_szAttribute.toString( );}
               //:GET VIEW vAnyView NAMED szView
               RESULT = GetViewByName( vAnyView, szView, mSARuleS, zLEVEL_TASK );
               //:GetDecimalFromAttribute( dValue, vAnyView, szEntity, szAttribute )
               {MutableDouble md_dValue = new MutableDouble( dValue );
                               GetDecimalFromAttribute( md_dValue, vAnyView, szEntity, szAttribute );
               dValue = md_dValue.doubleValue( );}
               //:dAwardAmount = mSARuleS.SACalcLookupRow.AwardAmount
               {MutableDouble md_dAwardAmount = new MutableDouble( dAwardAmount );
                               GetDecimalFromAttribute( md_dAwardAmount, mSARuleS, "SACalcLookupRow", "AwardAmount" );
               dAwardAmount = md_dAwardAmount.doubleValue( );}
               //:dNewValue = dValue * dAwardAmount 
               dNewValue = dValue * dAwardAmount;
               //:IF dNewValue <  dMinAward 
               if ( dNewValue < dMinAward )
               { 
                  //:dNewValue = dMinAward 
                  dNewValue = dMinAward;
               } 

               //:END
               //:StoreValueInRecord( mSARuleS, InternalEntityStructure, 
               //:                    InternalAttribStructure, dNewValue, 0 )
               StoreValueInRecord( mSARuleS, InternalEntityStructure, InternalAttribStructure, dNewValue, 0 );
            } 

            //:END
         } 

         //:END 
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
//:dGetColCalcAward( VIEW mSARuleS BASED ON LOD mSARuleS,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )
//:   DECIMAL dMinAward
public int 
omSARuleS_dGetColCalcAward( View     mSARuleS,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   double  dMinAward = 0.0;
   //:DECIMAL dAwardAmount
   double  dAwardAmount = 0.0;
   //:DECIMAL dValue
   double  dValue = 0.0;
   //:DECIMAL dNewValue
   double  dNewValue = 0.0;
   //:STRING ( 64 ) szView
   String   szView = null;
   //:STRING ( 64 ) szEntity
   String   szEntity = null;
   //:STRING ( 64 ) szAttribute
   String   szAttribute = null;
   //:STRING ( 64 ) szAttrType
   String   szAttrType = null;
   //:STRING ( 2000 ) szValue
   String   szValue = null;
   //:STRING (  1  ) szUseMult
   String   szUseMult = null;
   //:VIEW vAnyView
   zVIEW    vAnyView = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      RESULT = 0;


   //:szUseMult = ""
    {StringBuilder sb_szUseMult;
   if ( szUseMult == null )
      sb_szUseMult = new StringBuilder( 32 );
   else
      sb_szUseMult = new StringBuilder( szUseMult );
      ZeidonStringCopy( sb_szUseMult, 1, 0, "", 1, 0, 2 );
   szUseMult = sb_szUseMult.toString( );}
   //:dMinAward = 0
   dMinAward = 0;

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:szUseMult = mSARuleS.SACalcLookupColumn.UseAmountAsMultiplier 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szUseMult;
         if ( szUseMult == null )
            sb_szUseMult = new StringBuilder( 32 );
         else
            sb_szUseMult = new StringBuilder( szUseMult );
                   GetVariableFromAttribute( sb_szUseMult, mi_lTempInteger_0, 'S', 2, mSARuleS, "SACalcLookupColumn", "UseAmountAsMultiplier", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szUseMult = sb_szUseMult.toString( );}
         //:dMinAward = mSARuleS.SACalcLookupColumn.MinimumAwardAmount 
         {MutableDouble md_dMinAward = new MutableDouble( dMinAward );
                   GetDecimalFromAttribute( md_dMinAward, mSARuleS, "SACalcLookupColumn", "MinimumAwardAmount" );
         dMinAward = md_dMinAward.doubleValue( );}
         //:szAttrType  = mSARuleS.SACalcLookupColumn.QualAttributeType 
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szAttrType;
         if ( szAttrType == null )
            sb_szAttrType = new StringBuilder( 32 );
         else
            sb_szAttrType = new StringBuilder( szAttrType );
                   GetVariableFromAttribute( sb_szAttrType, mi_lTempInteger_1, 'S', 65, mSARuleS, "SACalcLookupColumn", "QualAttributeType", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szAttrType = sb_szAttrType.toString( );}
         //:// see what type the attribute is. 
         //:IF szAttrType = "S"
         if ( ZeidonStringCompare( szAttrType, 1, 0, "S", 1, 0, 65 ) == 0 )
         { 
            //:szValue = mSARuleS.SACalcLookupRow.AwardAmount
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                         GetVariableFromAttribute( sb_szValue, mi_lTempInteger_2, 'S', 2001, mSARuleS, "SACalcLookupRow", "AwardAmount", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szValue = sb_szValue.toString( );}
            //:StoreStringInRecord( mSARuleS, InternalEntityStructure, 
            //:                     InternalAttribStructure, szValue )
            StoreStringInRecord( mSARuleS, InternalEntityStructure, InternalAttribStructure, szValue );
            //:ELSE
         } 
         else
         { 
            //:IF szUseMult  != "Y"  
            if ( ZeidonStringCompare( szUseMult, 1, 0, "Y", 1, 0, 2 ) != 0 )
            { 
               //:dAwardAmount = mSARuleS.SACalcLookupColumn.AwardAmount
               {MutableDouble md_dAwardAmount = new MutableDouble( dAwardAmount );
                               GetDecimalFromAttribute( md_dAwardAmount, mSARuleS, "SACalcLookupColumn", "AwardAmount" );
               dAwardAmount = md_dAwardAmount.doubleValue( );}
               //:StoreValueInRecord( mSARuleS, InternalEntityStructure, 
               //:                    InternalAttribStructure, dAwardAmount, 0 )
               StoreValueInRecord( mSARuleS, InternalEntityStructure, InternalAttribStructure, dAwardAmount, 0 );
               //:ELSE 
            } 
            else
            { 
               //:// get the values we are looking for    
               //:szView      = mSARuleS.SACalcLookupColumn.QualViewName 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szView;
               if ( szView == null )
                  sb_szView = new StringBuilder( 32 );
               else
                  sb_szView = new StringBuilder( szView );
                               GetVariableFromAttribute( sb_szView, mi_lTempInteger_3, 'S', 65, mSARuleS, "SACalcLookupColumn", "QualViewName", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szView = sb_szView.toString( );}
               //:szEntity    = mSARuleS.SACalcLookupColumn.QualEntityName 
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szEntity;
               if ( szEntity == null )
                  sb_szEntity = new StringBuilder( 32 );
               else
                  sb_szEntity = new StringBuilder( szEntity );
                               GetVariableFromAttribute( sb_szEntity, mi_lTempInteger_4, 'S', 65, mSARuleS, "SACalcLookupColumn", "QualEntityName", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szEntity = sb_szEntity.toString( );}
               //:szAttribute = mSARuleS.SACalcLookupColumn.QualAttributeName 
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szAttribute;
               if ( szAttribute == null )
                  sb_szAttribute = new StringBuilder( 32 );
               else
                  sb_szAttribute = new StringBuilder( szAttribute );
                               GetVariableFromAttribute( sb_szAttribute, mi_lTempInteger_5, 'S', 65, mSARuleS, "SACalcLookupColumn", "QualAttributeName", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szAttribute = sb_szAttribute.toString( );}
               //:GET VIEW vAnyView NAMED szView
               RESULT = GetViewByName( vAnyView, szView, mSARuleS, zLEVEL_TASK );
               //:GetDecimalFromAttribute( dValue, vAnyView, szEntity, szAttribute )
               {MutableDouble md_dValue = new MutableDouble( dValue );
                               GetDecimalFromAttribute( md_dValue, vAnyView, szEntity, szAttribute );
               dValue = md_dValue.doubleValue( );}
               //:dAwardAmount = mSARuleS.SACalcLookupColumn.AwardAmount
               {MutableDouble md_dAwardAmount = new MutableDouble( dAwardAmount );
                               GetDecimalFromAttribute( md_dAwardAmount, mSARuleS, "SACalcLookupColumn", "AwardAmount" );
               dAwardAmount = md_dAwardAmount.doubleValue( );}
               //:dNewValue = dValue * dAwardAmount 
               dNewValue = dValue * dAwardAmount;
               //:IF dNewValue <  dMinAward 
               if ( dNewValue < dMinAward )
               { 
                  //:dNewValue = dMinAward 
                  dNewValue = dMinAward;
               } 

               //:END
               //:StoreValueInRecord( mSARuleS, InternalEntityStructure, 
               //:                    InternalAttribStructure, dNewValue, 0 )
               StoreValueInRecord( mSARuleS, InternalEntityStructure, InternalAttribStructure, dNewValue, 0 );
            } 

            //:END
         } 

         //:END 
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
