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
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.vml.VmlDialog;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.quinsoft.swauopencuas.ZGLOBAL1_Operation;

import com.quinsoft.zeidon.zeidonoperations.KZOEP1AA;
import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;

/**
   @author QuinSoft
**/

public class ZGLOBAL2_Operation extends VmlDialog
{
   private final KZOEP1AA m_KZOEP1AA;
   private final ZDRVROPR m_ZDRVROPR;
   public ZGLOBAL2_Operation( View view )
   {
      super( view );
      m_KZOEP1AA = new KZOEP1AA( view );
      m_ZDRVROPR = new ZDRVROPR( view );
   }


//:GLOBAL OPERATION
//:GetDOWNumber( STRING ( 32 ) szDate )
//:   INTEGER nDOY
public int  
GetDOWNumber( String   szDate )
{
   int      nDOY = 0;
   //:STRING (  10  ) szDayOfWeek
   String   szDayOfWeek = null;


   //:UfFormatDateTime( szDayOfWeek, szDate, "DDD" )
   {StringBuilder sb_szDayOfWeek;
   if ( szDayOfWeek == null )
      sb_szDayOfWeek = new StringBuilder( 32 );
   else
      sb_szDayOfWeek = new StringBuilder( szDayOfWeek );
       UfFormatDateTime( sb_szDayOfWeek, szDate, "DDD" );
   szDayOfWeek = sb_szDayOfWeek.toString( );}

   //:IF  szDayOfWeek = "SUN"
   if ( ZeidonStringCompare( szDayOfWeek, 1, 0, "SUN", 1, 0, 11 ) == 0 )
   { 
      //: nDOY = 1
      nDOY = 1;
      //: RETURN nDOY
      if(8==8)return( nDOY );
   } 

   //:END
   //:IF  szDayOfWeek = "MON"
   if ( ZeidonStringCompare( szDayOfWeek, 1, 0, "MON", 1, 0, 11 ) == 0 )
   { 
      //: nDOY = 2
      nDOY = 2;
      //: RETURN nDOY
      if(8==8)return( nDOY );
   } 

   //:END
   //:IF  szDayOfWeek = "TUE"
   if ( ZeidonStringCompare( szDayOfWeek, 1, 0, "TUE", 1, 0, 11 ) == 0 )
   { 
      //: nDOY = 3
      nDOY = 3;
      //: RETURN nDOY
      if(8==8)return( nDOY );
   } 

   //:END
   //:IF  szDayOfWeek = "WED"
   if ( ZeidonStringCompare( szDayOfWeek, 1, 0, "WED", 1, 0, 11 ) == 0 )
   { 
      //: nDOY = 4
      nDOY = 4;
      //: RETURN nDOY
      if(8==8)return( nDOY );
   } 

   //:END
   //:IF  szDayOfWeek = "THU"
   if ( ZeidonStringCompare( szDayOfWeek, 1, 0, "THU", 1, 0, 11 ) == 0 )
   { 
      //: nDOY = 5
      nDOY = 5;
      //: RETURN nDOY
      if(8==8)return( nDOY );
   } 

   //:END
   //:IF  szDayOfWeek = "FRI"
   if ( ZeidonStringCompare( szDayOfWeek, 1, 0, "FRI", 1, 0, 11 ) == 0 )
   { 
      //: nDOY = 6
      nDOY = 6;
      //: RETURN nDOY
      if(8==8)return( nDOY );
   } 

   //:END
   //:IF  szDayOfWeek = "SAT"
   if ( ZeidonStringCompare( szDayOfWeek, 1, 0, "SAT", 1, 0, 11 ) == 0 )
   { 
      //: nDOY = 7
      nDOY = 7;
      //: RETURN nDOY
      if(8==8)return( nDOY );
   } 

   //:END
   return( 0 );
// END
} 


private int 
o_fnLocalBuildQual_3( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szTempString_0,
                      String   szTempString_1,
                      int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Document" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ObjectName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "FunctionalAreaName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_1.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_4( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szTempString_2,
                      String   szTempString_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Document" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ObjectName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_2.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "FunctionalAreaName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_3.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_5( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szTempString_0,
                      String   szTempString_1,
                      int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Document" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ObjectName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "FunctionalAreaName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_1.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_6( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szTempString_2,
                      String   szTempString_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Document" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ObjectName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_2.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "FunctionalAreaName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_3.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_7( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szTempString_4,
                      String   szTempString_5,
                      int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Document" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ObjectName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_4.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "FunctionalAreaName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_5.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "DocType" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "DocType" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "M" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_8( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szTempString_6,
                      String   szTempString_7 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Document" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ObjectName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_6.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "FunctionalAreaName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_7.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "DocType" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "DocType" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "M" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_9( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szTempString_8,
                      String   szTempString_9,
                      int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Document" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ObjectName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_8.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "FunctionalAreaName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_9.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "DocType" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "R" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_10( View     vSubtask,
                       zVIEW    vQualObject,
                       String   szTempString_10,
                       String   szTempString_11 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Document" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ObjectName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_10.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "FunctionalAreaName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_11.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "DocType" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "R" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Document" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_11( View     vSubtask,
                       zVIEW    vQualObject,
                       int      iAdminDiv )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", iAdminDiv );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "RequiresPreloadFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_12( View     vSubtask,
                       zVIEW    vQualObject,
                       String   eMailAddress )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Prospect" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "eMailAddress" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", eMailAddress.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_13( View     vSubtask,
                       zVIEW    vQualObject,
                       String   eMailAddress )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Prospect" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "eMailAddress" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", eMailAddress.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_14( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_15( View     vSubtask,
                       zVIEW    vQualObject,
                       int      SAProfileID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountProfile" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", SAProfileID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_16( View     vSubtask,
                       zVIEW    vQualObject,
                       String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_17( View     vSubtask,
                       zVIEW    vQualObject,
                       String   szTempString_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FAISIRDomain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FAISIRDomain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "CommentCodes" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FAISIRDomain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Year" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:GLOBAL OPERATION
//:GenerateAwardedFederal( VIEW qFinAidA BASED ON LOD qFinAidA )

//:   VIEW lFAAdmin BASED ON LOD  lFAAdmin
public int 
GenerateAwardedFederal( View     qFinAidA )
{
   zVIEW    lFAAdmin = new zVIEW( );
   //:VIEW wXferO   REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 30 ) szRevenueWithDollarSign
   String   szRevenueWithDollarSign = null;
   //:STRING ( 1 )  szFullYearFlag
   String   szFullYearFlag = null;
   //:STRING ( 2 )  szFootnoteNumber
   String   szFootnoteNumber = null;
   //:STRING ( 90 ) szAwardDescription
   String   szAwardDescription = null;
   //:STRING ( 1 )  szSpringAwardsFlag
   String   szSpringAwardsFlag = null;
   //:STRING ( 9 )  szCollegeYear
   String   szCollegeYear = null;
   //:STRING ( 20 ) szPeriodDesignatorFall
   String   szPeriodDesignatorFall = null;
   //:STRING ( 20 ) szPeriodDesignatorSpring
   String   szPeriodDesignatorSpring = null;
   //:DECIMAL dLoanOptionTotal
   double  dLoanOptionTotal = 0.0;
   //:DECIMAL dCostOfAttendanceTotal
   double  dCostOfAttendanceTotal = 0.0;
   //:DECIMAL dGrantsScholarshipsTotal
   double  dGrantsScholarshipsTotal = 0.0;
   //:DECIMAL dInstGrantsScholarshipsTotal
   double  dInstGrantsScholarshipsTotal = 0.0;
   //:DECIMAL dAwardAmount
   double  dAwardAmount = 0.0;
   //:DECIMAL dAmount
   double  dAmount = 0.0;
   //:DECIMAL dAmountFall
   double  dAmountFall = 0.0;
   //:DECIMAL dAmountSpring
   double  dAmountSpring = 0.0;
   //:DECIMAL dWorkOptionAmount
   double  dWorkOptionAmount = 0.0;
   //:INTEGER nFNNumber
   int      nFNNumber = 0;
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;
   double  dTempDecimal_4 = 0.0;
   double  dTempDecimal_5 = 0.0;
   int      lTempInteger_1 = 0;
   double  dTempDecimal_6 = 0.0;
   double  dTempDecimal_7 = 0.0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   String   szTempString_3 = null;
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   int      lTempInteger_5 = 0;
   double  dTempDecimal_8 = 0.0;
   double  dTempDecimal_9 = 0.0;
   double  dTempDecimal_10 = 0.0;
   double  dTempDecimal_11 = 0.0;
   double  dTempDecimal_12 = 0.0;
   double  dTempDecimal_13 = 0.0;
   double  dTempDecimal_14 = 0.0;
   double  dTempDecimal_15 = 0.0;
   double  dTempDecimal_16 = 0.0;
   double  dTempDecimal_17 = 0.0;
   double  dTempDecimal_18 = 0.0;
   double  dTempDecimal_19 = 0.0;
   double  dTempDecimal_20 = 0.0;
   double  dTempDecimal_21 = 0.0;
   double  dTempDecimal_22 = 0.0;
   double  dTempDecimal_23 = 0.0;
   double  dTempDecimal_24 = 0.0;

   RESULT = GetViewByName( wXferO, "wXferO", qFinAidA, zLEVEL_TASK );

   //:// Generate a list of Award Items for the new Federal award letter.

   //:CREATE ENTITY qFinAidA.AwardLetterFederal 
   RESULT = CreateEntity( qFinAidA, "AwardLetterFederal", zPOS_AFTER );

   //:// Institutional Cost of Attendance Items
   //:// We also want to determine here is the award letter is being formatted for a full year or only a single term.
   //:// We will determine that by whether or not there are any COA items that have either RevenueAmount or both a
   //:// FirstTerm and SecondTerm RevenueAmount.
   //:szFullYearFlag = ""
    {StringBuilder sb_szFullYearFlag;
   if ( szFullYearFlag == null )
      sb_szFullYearFlag = new StringBuilder( 32 );
   else
      sb_szFullYearFlag = new StringBuilder( szFullYearFlag );
      ZeidonStringCopy( sb_szFullYearFlag, 1, 0, "", 1, 0, 2 );
   szFullYearFlag = sb_szFullYearFlag.toString( );}
   //:FOR EACH qFinAidA.FinAidCOAItemAssigned 
   RESULT = SetCursorFirstEntity( qFinAidA, "FinAidCOAItemAssigned", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF qFinAidA.FinAidCOAItem EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( qFinAidA, "FinAidCOAItem" );
      if ( lTempInteger_0 == 0 )
      { 
         //:IF qFinAidA.FinAidCOAItem.InstitutionalCostFlag = "Y"
         if ( CompareAttributeToString( qFinAidA, "FinAidCOAItem", "InstitutionalCostFlag", "Y" ) == 0 )
         { 

            //:// Determine Amount
            //:dAmount = 0
            dAmount = 0;
            //:IF qFinAidA.FinAidCOAItemAssigned.RevenueAmount != ""
            if ( CompareAttributeToString( qFinAidA, "FinAidCOAItemAssigned", "RevenueAmount", "" ) != 0 )
            { 
               //:// Amount is from full year.
               //:szFullYearFlag = "Y"
                {StringBuilder sb_szFullYearFlag;
               if ( szFullYearFlag == null )
                  sb_szFullYearFlag = new StringBuilder( 32 );
               else
                  sb_szFullYearFlag = new StringBuilder( szFullYearFlag );
                              ZeidonStringCopy( sb_szFullYearFlag, 1, 0, "Y", 1, 0, 2 );
               szFullYearFlag = sb_szFullYearFlag.toString( );}
               //:dAmount = qFinAidA.FinAidCOAItemAssigned.RevenueAmount
               {MutableDouble md_dAmount = new MutableDouble( dAmount );
                               GetDecimalFromAttribute( md_dAmount, qFinAidA, "FinAidCOAItemAssigned", "RevenueAmount" );
               dAmount = md_dAmount.doubleValue( );}
               //:ELSE
            } 
            else
            { 
               //:// Amount is from First and Second Term.
               //:IF qFinAidA.FinAidCOAItemAssigned.FirstTermRevenueAmount > 0 AND 
               //:   qFinAidA.FinAidCOAItemAssigned.SecondTermRevenueAmount > 0
               if ( CompareAttributeToInteger( qFinAidA, "FinAidCOAItemAssigned", "FirstTermRevenueAmount", 0 ) > 0 && CompareAttributeToInteger( qFinAidA, "FinAidCOAItemAssigned", "SecondTermRevenueAmount", 0 ) > 0 )
               { 

                  //:szFullYearFlag = "Y"
                   {StringBuilder sb_szFullYearFlag;
                  if ( szFullYearFlag == null )
                     sb_szFullYearFlag = new StringBuilder( 32 );
                  else
                     sb_szFullYearFlag = new StringBuilder( szFullYearFlag );
                                    ZeidonStringCopy( sb_szFullYearFlag, 1, 0, "Y", 1, 0, 2 );
                  szFullYearFlag = sb_szFullYearFlag.toString( );}
               } 

               //:END
               //:dAmount = qFinAidA.FinAidCOAItemAssigned.FirstTermRevenueAmount + qFinAidA.FinAidCOAItemAssigned.SecondTermRevenueAmount 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, qFinAidA, "FinAidCOAItemAssigned", "FirstTermRevenueAmount" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                               GetDecimalFromAttribute( md_dTempDecimal_1, qFinAidA, "FinAidCOAItemAssigned", "SecondTermRevenueAmount" );
               dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
               dAmount = dTempDecimal_0 + dTempDecimal_1;
            } 

            //:END
            //:IF dAmount > 0
            if ( dAmount > 0 )
            { 
               //:// Only create entry with positive amount.
               //:CREATE ENTITY qFinAidA.AwardLetterFederalCOAItem 
               RESULT = CreateEntity( qFinAidA, "AwardLetterFederalCOAItem", zPOS_AFTER );
               //:qFinAidA.AwardLetterFederalCOAItem.Description = qFinAidA.FinAidCOAItem.Description 
               SetAttributeFromAttribute( qFinAidA, "AwardLetterFederalCOAItem", "Description", qFinAidA, "FinAidCOAItem", "Description" );
               //:qFinAidA.AwardLetterFederalCOAItem.Amount = dAmount
               SetAttributeFromDecimal( qFinAidA, "AwardLetterFederalCOAItem", "Amount", dAmount );
               //:GetStringFromAttributeByContext( szRevenueWithDollarSign, 
               //:                                 qFinAidA, "AwardLetterFederalCOAItem", "Amount", "RevenueWithDollarSign", 30 )
               {StringBuilder sb_szRevenueWithDollarSign;
               if ( szRevenueWithDollarSign == null )
                  sb_szRevenueWithDollarSign = new StringBuilder( 32 );
               else
                  sb_szRevenueWithDollarSign = new StringBuilder( szRevenueWithDollarSign );
                               GetStringFromAttributeByContext( sb_szRevenueWithDollarSign, qFinAidA, "AwardLetterFederalCOAItem", "Amount", "RevenueWithDollarSign", 30 );
               szRevenueWithDollarSign = sb_szRevenueWithDollarSign.toString( );}
               //:qFinAidA.AwardLetterFederalCOAItem.AmountWSign = szRevenueWithDollarSign
               SetAttributeFromString( qFinAidA, "AwardLetterFederalCOAItem", "AmountWSign", szRevenueWithDollarSign );

               //:// Set Fall and Spring Amounts.
               //:dAmountFall   = 0
               dAmountFall = 0;
               //:dAmountSpring = 0
               dAmountSpring = 0;
               //:IF qFinAidA.FinAidProfile.COA_OptionWholeYearFlag = "Y"
               if ( CompareAttributeToString( qFinAidA, "FinAidProfile", "COA_OptionWholeYearFlag", "Y" ) == 0 )
               { 
                  //:dAmountFall = dAmount / 2
                  dAmountFall = dAmount / 2;
                  //:dAmountSpring = dAmount - dAmountFall
                  dAmountSpring = dAmount - dAmountFall;
                  //:ELSE
               } 
               else
               { 
                  //:IF qFinAidA.FinAidProfile.COA_OptionFirstTermFlag = "Y"
                  if ( CompareAttributeToString( qFinAidA, "FinAidProfile", "COA_OptionFirstTermFlag", "Y" ) == 0 )
                  { 
                     //:dAmountFall = qFinAidA.FinAidCOAItemAssigned.FirstTermRevenueAmount 
                     {MutableDouble md_dAmountFall = new MutableDouble( dAmountFall );
                                           GetDecimalFromAttribute( md_dAmountFall, qFinAidA, "FinAidCOAItemAssigned", "FirstTermRevenueAmount" );
                     dAmountFall = md_dAmountFall.doubleValue( );}
                  } 

                  //:END
                  //:IF qFinAidA.FinAidProfile.COA_OptionSecondTermFlag = "Y"
                  if ( CompareAttributeToString( qFinAidA, "FinAidProfile", "COA_OptionSecondTermFlag", "Y" ) == 0 )
                  { 
                     //:dAmountSpring = qFinAidA.FinAidCOAItemAssigned.SecondTermRevenueAmount 
                     {MutableDouble md_dAmountSpring = new MutableDouble( dAmountSpring );
                                           GetDecimalFromAttribute( md_dAmountSpring, qFinAidA, "FinAidCOAItemAssigned", "SecondTermRevenueAmount" );
                     dAmountSpring = md_dAmountSpring.doubleValue( );}
                  } 

                  //:END
               } 

               //:END
               //:qFinAidA.AwardLetterFederalCOAItem.FallAmount   = dAmountFall
               SetAttributeFromDecimal( qFinAidA, "AwardLetterFederalCOAItem", "FallAmount", dAmountFall );
               //:qFinAidA.AwardLetterFederalCOAItem.SpringAmount = dAmountSpring
               SetAttributeFromDecimal( qFinAidA, "AwardLetterFederalCOAItem", "SpringAmount", dAmountSpring );
               //:qFinAidA.AwardLetterFederal.CostOfAttendanceFall   = qFinAidA.AwardLetterFederal.CostOfAttendanceFall   + dAmountFall
               {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                               GetDecimalFromAttribute( md_dTempDecimal_2, qFinAidA, "AwardLetterFederal", "CostOfAttendanceFall" );
               dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
               dTempDecimal_3 = dTempDecimal_2 + dAmountFall;
               SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "CostOfAttendanceFall", dTempDecimal_3 );
               //:qFinAidA.AwardLetterFederal.CostOfAttendanceSpring = qFinAidA.AwardLetterFederal.CostOfAttendanceSpring + dAmountSpring
               {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                               GetDecimalFromAttribute( md_dTempDecimal_4, qFinAidA, "AwardLetterFederal", "CostOfAttendanceSpring" );
               dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
               dTempDecimal_5 = dTempDecimal_4 + dAmountSpring;
               SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "CostOfAttendanceSpring", dTempDecimal_5 );
            } 

            //:END
            //:dCostOfAttendanceTotal = dCostOfAttendanceTotal + dAmount
            dCostOfAttendanceTotal = dCostOfAttendanceTotal + dAmount;
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( qFinAidA, "FinAidCOAItemAssigned", "" );
      //:END
   } 

   //:END

   //:// Get Fall and Spring PeriodDesignator values for determining Fall and Spring Disbursements below.
   //:szCollegeYear = qFinAidA.CollegeYear.Year 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szCollegeYear;
   if ( szCollegeYear == null )
      sb_szCollegeYear = new StringBuilder( 32 );
   else
      sb_szCollegeYear = new StringBuilder( szCollegeYear );
       GetVariableFromAttribute( sb_szCollegeYear, mi_lTempInteger_1, 'S', 10, qFinAidA, "CollegeYear", "Year", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szCollegeYear = sb_szCollegeYear.toString( );}
   //:szPeriodDesignatorFall   = szCollegeYear + " Fall"
    {StringBuilder sb_szPeriodDesignatorFall;
   if ( szPeriodDesignatorFall == null )
      sb_szPeriodDesignatorFall = new StringBuilder( 32 );
   else
      sb_szPeriodDesignatorFall = new StringBuilder( szPeriodDesignatorFall );
      ZeidonStringCopy( sb_szPeriodDesignatorFall, 1, 0, szCollegeYear, 1, 0, 21 );
   szPeriodDesignatorFall = sb_szPeriodDesignatorFall.toString( );}
    {StringBuilder sb_szPeriodDesignatorFall;
   if ( szPeriodDesignatorFall == null )
      sb_szPeriodDesignatorFall = new StringBuilder( 32 );
   else
      sb_szPeriodDesignatorFall = new StringBuilder( szPeriodDesignatorFall );
      ZeidonStringConcat( sb_szPeriodDesignatorFall, 1, 0, " Fall", 1, 0, 21 );
   szPeriodDesignatorFall = sb_szPeriodDesignatorFall.toString( );}
   //:szPeriodDesignatorSpring = szCollegeYear + " Spring"
    {StringBuilder sb_szPeriodDesignatorSpring;
   if ( szPeriodDesignatorSpring == null )
      sb_szPeriodDesignatorSpring = new StringBuilder( 32 );
   else
      sb_szPeriodDesignatorSpring = new StringBuilder( szPeriodDesignatorSpring );
      ZeidonStringCopy( sb_szPeriodDesignatorSpring, 1, 0, szCollegeYear, 1, 0, 21 );
   szPeriodDesignatorSpring = sb_szPeriodDesignatorSpring.toString( );}
    {StringBuilder sb_szPeriodDesignatorSpring;
   if ( szPeriodDesignatorSpring == null )
      sb_szPeriodDesignatorSpring = new StringBuilder( 32 );
   else
      sb_szPeriodDesignatorSpring = new StringBuilder( szPeriodDesignatorSpring );
      ZeidonStringConcat( sb_szPeriodDesignatorSpring, 1, 0, " Spring", 1, 0, 21 );
   szPeriodDesignatorSpring = sb_szPeriodDesignatorSpring.toString( );}

   //:// Grants and Scholarships
   //:nFNNumber = 0
   nFNNumber = 0;
   //:dGrantsScholarshipsTotal = 0
   dGrantsScholarshipsTotal = 0;
   //:FOR EACH qFinAidA.FinAidAwardAssigned 
   RESULT = SetCursorFirstEntity( qFinAidA, "FinAidAwardAssigned", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF ( qFinAidA.FinAidSource.SourceType = "G" OR qFinAidA.FinAidSource.SourceType = "S" ) AND 
      //:   ( qFinAidA.FinAidAwardAssigned.AwardStatus = "P" OR 
      //:     qFinAidA.FinAidAwardAssigned.AwardStatus = "Y" OR 
      //:     qFinAidA.FinAidAwardAssigned.AwardStatus = "A" )
      if ( ( CompareAttributeToString( qFinAidA, "FinAidSource", "SourceType", "G" ) == 0 || CompareAttributeToString( qFinAidA, "FinAidSource", "SourceType", "S" ) == 0 ) &&
           ( CompareAttributeToString( qFinAidA, "FinAidAwardAssigned", "AwardStatus", "P" ) == 0 || CompareAttributeToString( qFinAidA, "FinAidAwardAssigned", "AwardStatus", "Y" ) == 0 ||
           CompareAttributeToString( qFinAidA, "FinAidAwardAssigned", "AwardStatus", "A" ) == 0 ) )
      { 

         //:// Make sure Disbursements are ordered so Fall is first.
         //:OrderEntityForView( qFinAidA, "FinAidAwardDisbursement", "DisbursementDate A" )
         OrderEntityForView( qFinAidA, "FinAidAwardDisbursement", "DisbursementDate A" );

         //:// Create Grant/Scholarship entry as either Institutional or Other.
         //:IF qFinAidA.FinAidSource.Provider = "I"
         if ( CompareAttributeToString( qFinAidA, "FinAidSource", "Provider", "I" ) == 0 )
         { 
            //:// Institutional Aid
            //:CREATE ENTITY qFinAidA.AwardLetterInstGrant  
            RESULT = CreateEntity( qFinAidA, "AwardLetterInstGrant", zPOS_AFTER );
            //:qFinAidA.AwardLetterInstGrant.Description = qFinAidA.FinAidSource.Name
            SetAttributeFromAttribute( qFinAidA, "AwardLetterInstGrant", "Description", qFinAidA, "FinAidSource", "Name" );
            //:qFinAidA.AwardLetterInstGrant.Amount = qFinAidA.FinAidAwardAssigned.Amount  
            SetAttributeFromAttribute( qFinAidA, "AwardLetterInstGrant", "Amount", qFinAidA, "FinAidAwardAssigned", "Amount" );
            //:GetStringFromAttributeByContext( szRevenueWithDollarSign, 
            //:                                 qFinAidA, "AwardLetterInstGrant", "Amount", "RevenueWithDollarSign", 30 )
            {StringBuilder sb_szRevenueWithDollarSign;
            if ( szRevenueWithDollarSign == null )
               sb_szRevenueWithDollarSign = new StringBuilder( 32 );
            else
               sb_szRevenueWithDollarSign = new StringBuilder( szRevenueWithDollarSign );
                         GetStringFromAttributeByContext( sb_szRevenueWithDollarSign, qFinAidA, "AwardLetterInstGrant", "Amount", "RevenueWithDollarSign", 30 );
            szRevenueWithDollarSign = sb_szRevenueWithDollarSign.toString( );}
            //:qFinAidA.AwardLetterInstGrant.AmountWSign = szRevenueWithDollarSign
            SetAttributeFromString( qFinAidA, "AwardLetterInstGrant", "AmountWSign", szRevenueWithDollarSign );

            //:// Also set Fall and Spring Amounts.
            //:FOR EACH qFinAidA.FinAidAwardDisbursement 
            RESULT = SetCursorFirstEntity( qFinAidA, "FinAidAwardDisbursement", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF qFinAidA.FinAidAwardPeriod.PeriodDesignator = szPeriodDesignatorFall
               if ( CompareAttributeToString( qFinAidA, "FinAidAwardPeriod", "PeriodDesignator", szPeriodDesignatorFall ) == 0 )
               { 
                  //:qFinAidA.AwardLetterInstGrant.FallAmount = qFinAidA.FinAidAwardDisbursement.AmountExpected 
                  SetAttributeFromAttribute( qFinAidA, "AwardLetterInstGrant", "FallAmount", qFinAidA, "FinAidAwardDisbursement", "AmountExpected" );
                  //:ELSE
               } 
               else
               { 
                  //:IF qFinAidA.FinAidAwardPeriod.PeriodDesignator = szPeriodDesignatorSpring
                  if ( CompareAttributeToString( qFinAidA, "FinAidAwardPeriod", "PeriodDesignator", szPeriodDesignatorSpring ) == 0 )
                  { 
                     //:qFinAidA.AwardLetterInstGrant.SpringAmount = qFinAidA.FinAidAwardDisbursement.AmountExpected 
                     SetAttributeFromAttribute( qFinAidA, "AwardLetterInstGrant", "SpringAmount", qFinAidA, "FinAidAwardDisbursement", "AmountExpected" );
                     //:szSpringAwardsFlag = "Y"
                      {StringBuilder sb_szSpringAwardsFlag;
                     if ( szSpringAwardsFlag == null )
                        sb_szSpringAwardsFlag = new StringBuilder( 32 );
                     else
                        sb_szSpringAwardsFlag = new StringBuilder( szSpringAwardsFlag );
                                          ZeidonStringCopy( sb_szSpringAwardsFlag, 1, 0, "Y", 1, 0, 2 );
                     szSpringAwardsFlag = sb_szSpringAwardsFlag.toString( );}
                  } 

                  //:END
               } 

               RESULT = SetCursorNextEntity( qFinAidA, "FinAidAwardDisbursement", "" );
               //:END
            } 

            //:END

            //:IF qFinAidA.FinAidSource.SourceFootnote != ""
            if ( CompareAttributeToString( qFinAidA, "FinAidSource", "SourceFootnote", "" ) != 0 )
            { 
               //:qFinAidA.AwardLetterInstGrant.FootNoteText = qFinAidA.FinAidSource.SourceFootnote
               SetAttributeFromAttribute( qFinAidA, "AwardLetterInstGrant", "FootNoteText", qFinAidA, "FinAidSource", "SourceFootnote" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:// Other Aid
            //:CREATE ENTITY qFinAidA.AwardLetterOtherGrant 
            RESULT = CreateEntity( qFinAidA, "AwardLetterOtherGrant", zPOS_AFTER );
            //:qFinAidA.AwardLetterOtherGrant.Description = qFinAidA.FinAidSource.Name
            SetAttributeFromAttribute( qFinAidA, "AwardLetterOtherGrant", "Description", qFinAidA, "FinAidSource", "Name" );
            //:qFinAidA.AwardLetterOtherGrant.Amount = qFinAidA.FinAidAwardAssigned.Amount  
            SetAttributeFromAttribute( qFinAidA, "AwardLetterOtherGrant", "Amount", qFinAidA, "FinAidAwardAssigned", "Amount" );
            //:GetStringFromAttributeByContext( szRevenueWithDollarSign, 
            //:                                 qFinAidA, "AwardLetterOtherGrant", "Amount", "RevenueWithDollarSign", 30 )
            {StringBuilder sb_szRevenueWithDollarSign;
            if ( szRevenueWithDollarSign == null )
               sb_szRevenueWithDollarSign = new StringBuilder( 32 );
            else
               sb_szRevenueWithDollarSign = new StringBuilder( szRevenueWithDollarSign );
                         GetStringFromAttributeByContext( sb_szRevenueWithDollarSign, qFinAidA, "AwardLetterOtherGrant", "Amount", "RevenueWithDollarSign", 30 );
            szRevenueWithDollarSign = sb_szRevenueWithDollarSign.toString( );}
            //:qFinAidA.AwardLetterOtherGrant.AmountWSign = szRevenueWithDollarSign
            SetAttributeFromString( qFinAidA, "AwardLetterOtherGrant", "AmountWSign", szRevenueWithDollarSign );

            //:// Also set Fall and Spring Amounts.
            //:FOR EACH qFinAidA.FinAidAwardDisbursement 
            RESULT = SetCursorFirstEntity( qFinAidA, "FinAidAwardDisbursement", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF qFinAidA.FinAidAwardPeriod.PeriodDesignator = szPeriodDesignatorFall
               if ( CompareAttributeToString( qFinAidA, "FinAidAwardPeriod", "PeriodDesignator", szPeriodDesignatorFall ) == 0 )
               { 
                  //:qFinAidA.AwardLetterOtherGrant.FallAmount = qFinAidA.FinAidAwardDisbursement.AmountExpected 
                  SetAttributeFromAttribute( qFinAidA, "AwardLetterOtherGrant", "FallAmount", qFinAidA, "FinAidAwardDisbursement", "AmountExpected" );
                  //:ELSE
               } 
               else
               { 
                  //:IF qFinAidA.FinAidAwardPeriod.PeriodDesignator = szPeriodDesignatorSpring
                  if ( CompareAttributeToString( qFinAidA, "FinAidAwardPeriod", "PeriodDesignator", szPeriodDesignatorSpring ) == 0 )
                  { 
                     //:qFinAidA.AwardLetterOtherGrant.SpringAmount = qFinAidA.FinAidAwardDisbursement.AmountExpected 
                     SetAttributeFromAttribute( qFinAidA, "AwardLetterOtherGrant", "SpringAmount", qFinAidA, "FinAidAwardDisbursement", "AmountExpected" );
                     //:szSpringAwardsFlag = "Y"
                      {StringBuilder sb_szSpringAwardsFlag;
                     if ( szSpringAwardsFlag == null )
                        sb_szSpringAwardsFlag = new StringBuilder( 32 );
                     else
                        sb_szSpringAwardsFlag = new StringBuilder( szSpringAwardsFlag );
                                          ZeidonStringCopy( sb_szSpringAwardsFlag, 1, 0, "Y", 1, 0, 2 );
                     szSpringAwardsFlag = sb_szSpringAwardsFlag.toString( );}
                  } 

                  //:END
               } 

               RESULT = SetCursorNextEntity( qFinAidA, "FinAidAwardDisbursement", "" );
               //:END
            } 

            //:END

            //:IF qFinAidA.FinAidSource.SourceFootnote != ""
            if ( CompareAttributeToString( qFinAidA, "FinAidSource", "SourceFootnote", "" ) != 0 )
            { 
               //:qFinAidA.AwardLetterOtherGrant.FootNoteText = qFinAidA.FinAidSource.SourceFootnote
               SetAttributeFromAttribute( qFinAidA, "AwardLetterOtherGrant", "FootNoteText", qFinAidA, "FinAidSource", "SourceFootnote" );
            } 

            //:END
         } 

         //:END

         //:// Total all.
         //:dGrantsScholarshipsTotal = dGrantsScholarshipsTotal + qFinAidA.FinAidAwardAssigned.Amount
         {MutableDouble md_dTempDecimal_6 = new MutableDouble( dTempDecimal_6 );
                   GetDecimalFromAttribute( md_dTempDecimal_6, qFinAidA, "FinAidAwardAssigned", "Amount" );
         dTempDecimal_6 = md_dTempDecimal_6.doubleValue( );}
         dGrantsScholarshipsTotal = dGrantsScholarshipsTotal + dTempDecimal_6;
      } 

      RESULT = SetCursorNextEntity( qFinAidA, "FinAidAwardAssigned", "" );
      //:END 
   } 

   //:END

   //:// Total Institutional Aid.
   //:dInstGrantsScholarshipsTotal = 0
   dInstGrantsScholarshipsTotal = 0;
   //:FOR EACH qFinAidA.AwardLetterInstGrant 
   RESULT = SetCursorFirstEntity( qFinAidA, "AwardLetterInstGrant", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:dInstGrantsScholarshipsTotal = dInstGrantsScholarshipsTotal + qFinAidA.AwardLetterInstGrant.Amount 
      {MutableDouble md_dTempDecimal_7 = new MutableDouble( dTempDecimal_7 );
             GetDecimalFromAttribute( md_dTempDecimal_7, qFinAidA, "AwardLetterInstGrant", "Amount" );
      dTempDecimal_7 = md_dTempDecimal_7.doubleValue( );}
      dInstGrantsScholarshipsTotal = dInstGrantsScholarshipsTotal + dTempDecimal_7;
      RESULT = SetCursorNextEntity( qFinAidA, "AwardLetterInstGrant", "" );
   } 

   //:END

   //:// Handle Footnotes, where they exist.
   //:nFNNumber = 0
   nFNNumber = 0;
   //:FOR EACH qFinAidA.AwardLetterInstGrant 
   RESULT = SetCursorFirstEntity( qFinAidA, "AwardLetterInstGrant", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF qFinAidA.AwardLetterInstGrant.FootNoteText != ""
      if ( CompareAttributeToString( qFinAidA, "AwardLetterInstGrant", "FootNoteText", "" ) != 0 )
      { 
         //:nFNNumber = nFNNumber + 1
         nFNNumber = nFNNumber + 1;
         //:szFootnoteNumber = nFNNumber
          {StringBuilder sb_szFootnoteNumber;
         if ( szFootnoteNumber == null )
            sb_szFootnoteNumber = new StringBuilder( 32 );
         else
            sb_szFootnoteNumber = new StringBuilder( szFootnoteNumber );
                  ZeidonStringConvertFromNumber( sb_szFootnoteNumber, 1, 0, 2, nFNNumber, (double) 0.0, "I" );
         szFootnoteNumber = sb_szFootnoteNumber.toString( );}
         //:szAwardDescription = qFinAidA.AwardLetterInstGrant.Description
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szAwardDescription;
         if ( szAwardDescription == null )
            sb_szAwardDescription = new StringBuilder( 32 );
         else
            sb_szAwardDescription = new StringBuilder( szAwardDescription );
                   GetVariableFromAttribute( sb_szAwardDescription, mi_lTempInteger_2, 'S', 91, qFinAidA, "AwardLetterInstGrant", "Description", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szAwardDescription = sb_szAwardDescription.toString( );}
         //:qFinAidA.AwardLetterInstGrant.Description = szAwardDescription + " (*" + szFootnoteNumber + ")"
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringCopy( sb_szTempString_0, 1, 0, szAwardDescription, 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, " (*", 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, szFootnoteNumber, 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, ")", 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
         SetAttributeFromString( qFinAidA, "AwardLetterInstGrant", "Description", szTempString_0 );
         //:CREATE ENTITY qFinAidA.AwardLetterFederalFootNote 
         RESULT = CreateEntity( qFinAidA, "AwardLetterFederalFootNote", zPOS_AFTER );
         //:qFinAidA.AwardLetterFederalFootNote.Description = szFootnoteNumber + ": " + qFinAidA.AwardLetterInstGrant.FootNoteText 
          {StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                  ZeidonStringCopy( sb_szTempString_1, 1, 0, szFootnoteNumber, 1, 0, 255 );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                  ZeidonStringConcat( sb_szTempString_1, 1, 0, ": ", 1, 0, 255 );
         szTempString_1 = sb_szTempString_1.toString( );}
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szTempString_2;
         if ( szTempString_2 == null )
            sb_szTempString_2 = new StringBuilder( 32 );
         else
            sb_szTempString_2 = new StringBuilder( szTempString_2 );
                   GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_3, 'S', 255, qFinAidA, "AwardLetterInstGrant", "FootNoteText", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         szTempString_2 = sb_szTempString_2.toString( );}
          {StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                  ZeidonStringConcat( sb_szTempString_1, 1, 0, szTempString_2, 1, 0, 255 );
         szTempString_1 = sb_szTempString_1.toString( );}
         SetAttributeFromString( qFinAidA, "AwardLetterFederalFootNote", "Description", szTempString_1 );
      } 

      RESULT = SetCursorNextEntity( qFinAidA, "AwardLetterInstGrant", "" );
      //:END
   } 

   //:END
   //:FOR EACH qFinAidA.AwardLetterOtherGrant 
   RESULT = SetCursorFirstEntity( qFinAidA, "AwardLetterOtherGrant", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF qFinAidA.AwardLetterOtherGrant.FootNoteText != ""
      if ( CompareAttributeToString( qFinAidA, "AwardLetterOtherGrant", "FootNoteText", "" ) != 0 )
      { 
         //:nFNNumber = nFNNumber + 1
         nFNNumber = nFNNumber + 1;
         //:szFootnoteNumber = nFNNumber
          {StringBuilder sb_szFootnoteNumber;
         if ( szFootnoteNumber == null )
            sb_szFootnoteNumber = new StringBuilder( 32 );
         else
            sb_szFootnoteNumber = new StringBuilder( szFootnoteNumber );
                  ZeidonStringConvertFromNumber( sb_szFootnoteNumber, 1, 0, 2, nFNNumber, (double) 0.0, "I" );
         szFootnoteNumber = sb_szFootnoteNumber.toString( );}
         //:szAwardDescription = qFinAidA.AwardLetterOtherGrant.Description
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
         StringBuilder sb_szAwardDescription;
         if ( szAwardDescription == null )
            sb_szAwardDescription = new StringBuilder( 32 );
         else
            sb_szAwardDescription = new StringBuilder( szAwardDescription );
                   GetVariableFromAttribute( sb_szAwardDescription, mi_lTempInteger_4, 'S', 91, qFinAidA, "AwardLetterOtherGrant", "Description", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );
         szAwardDescription = sb_szAwardDescription.toString( );}
         //:qFinAidA.AwardLetterOtherGrant.Description = szAwardDescription + " (*" + szFootnoteNumber + ")"
          {StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                  ZeidonStringCopy( sb_szTempString_3, 1, 0, szAwardDescription, 1, 0, 255 );
         szTempString_3 = sb_szTempString_3.toString( );}
          {StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                  ZeidonStringConcat( sb_szTempString_3, 1, 0, " (*", 1, 0, 255 );
         szTempString_3 = sb_szTempString_3.toString( );}
          {StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                  ZeidonStringConcat( sb_szTempString_3, 1, 0, szFootnoteNumber, 1, 0, 255 );
         szTempString_3 = sb_szTempString_3.toString( );}
          {StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                  ZeidonStringConcat( sb_szTempString_3, 1, 0, ")", 1, 0, 255 );
         szTempString_3 = sb_szTempString_3.toString( );}
         SetAttributeFromString( qFinAidA, "AwardLetterOtherGrant", "Description", szTempString_3 );
         //:CREATE ENTITY qFinAidA.AwardLetterFederalFootNote 
         RESULT = CreateEntity( qFinAidA, "AwardLetterFederalFootNote", zPOS_AFTER );
         //:qFinAidA.AwardLetterFederalFootNote.Description = szFootnoteNumber + ": " + qFinAidA.AwardLetterOtherGrant.FootNoteText
          {StringBuilder sb_szTempString_4;
         if ( szTempString_4 == null )
            sb_szTempString_4 = new StringBuilder( 32 );
         else
            sb_szTempString_4 = new StringBuilder( szTempString_4 );
                  ZeidonStringCopy( sb_szTempString_4, 1, 0, szFootnoteNumber, 1, 0, 255 );
         szTempString_4 = sb_szTempString_4.toString( );}
          {StringBuilder sb_szTempString_4;
         if ( szTempString_4 == null )
            sb_szTempString_4 = new StringBuilder( 32 );
         else
            sb_szTempString_4 = new StringBuilder( szTempString_4 );
                  ZeidonStringConcat( sb_szTempString_4, 1, 0, ": ", 1, 0, 255 );
         szTempString_4 = sb_szTempString_4.toString( );}
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
         StringBuilder sb_szTempString_5;
         if ( szTempString_5 == null )
            sb_szTempString_5 = new StringBuilder( 32 );
         else
            sb_szTempString_5 = new StringBuilder( szTempString_5 );
                   GetVariableFromAttribute( sb_szTempString_5, mi_lTempInteger_5, 'S', 255, qFinAidA, "AwardLetterOtherGrant", "FootNoteText", "", 0 );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );
         szTempString_5 = sb_szTempString_5.toString( );}
          {StringBuilder sb_szTempString_4;
         if ( szTempString_4 == null )
            sb_szTempString_4 = new StringBuilder( 32 );
         else
            sb_szTempString_4 = new StringBuilder( szTempString_4 );
                  ZeidonStringConcat( sb_szTempString_4, 1, 0, szTempString_5, 1, 0, 255 );
         szTempString_4 = sb_szTempString_4.toString( );}
         SetAttributeFromString( qFinAidA, "AwardLetterFederalFootNote", "Description", szTempString_4 );
      } 

      RESULT = SetCursorNextEntity( qFinAidA, "AwardLetterOtherGrant", "" );
      //:END
   } 

   //:END

   //:// Create qFinAidA.AwardLetterFederalGrant entries from AwardLetterInstGrant and AwardLetterOtherGrant entries.
   //:FOR EACH qFinAidA.AwardLetterInstGrant 
   RESULT = SetCursorFirstEntity( qFinAidA, "AwardLetterInstGrant", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY qFinAidA.AwardLetterFederalGrant 
      RESULT = CreateEntity( qFinAidA, "AwardLetterFederalGrant", zPOS_AFTER );
      //:SetMatchingAttributesByName( qFinAidA, "AwardLetterFederalGrant", qFinAidA, "AwardLetterInstGrant", zSET_NULL ) 
      SetMatchingAttributesByName( qFinAidA, "AwardLetterFederalGrant", qFinAidA, "AwardLetterInstGrant", zSET_NULL );
      RESULT = SetCursorNextEntity( qFinAidA, "AwardLetterInstGrant", "" );
   } 

   //:END
   //:FOR EACH qFinAidA.AwardLetterOtherGrant 
   RESULT = SetCursorFirstEntity( qFinAidA, "AwardLetterOtherGrant", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY qFinAidA.AwardLetterFederalGrant 
      RESULT = CreateEntity( qFinAidA, "AwardLetterFederalGrant", zPOS_AFTER );
      //:SetMatchingAttributesByName( qFinAidA, "AwardLetterFederalGrant", qFinAidA, "AwardLetterOtherGrant", zSET_NULL ) 
      SetMatchingAttributesByName( qFinAidA, "AwardLetterFederalGrant", qFinAidA, "AwardLetterOtherGrant", zSET_NULL );
      RESULT = SetCursorNextEntity( qFinAidA, "AwardLetterOtherGrant", "" );
   } 

   //:END

   //:// Work Option
   //:GET VIEW lFAAdmin NAMED "lFAAdmin"
   RESULT = GetViewByName( lFAAdmin, "lFAAdmin", qFinAidA, zLEVEL_TASK );
   //:IF RESULT >= 0 
   if ( RESULT >= 0 )
   { 
      //:// We want to add the Work Option Amount ONLY if
      //:// 1. The student is NOT Distance Learning.
      //:// 2. The student is Dependent OR the student has a Federal Work Study award.
      //:IF qFinAidA.Student.AdultStudiesFlag != "Y"
      if ( CompareAttributeToString( qFinAidA, "Student", "AdultStudiesFlag", "Y" ) != 0 )
      { 
         //:SET CURSOR FIRST qFinAidA.FinAidSource WITHIN qFinAidA.FinAidProfile 
         //:           WHERE qFinAidA.FinAidSource.SourceType = "W"
         RESULT = SetCursorFirstEntityByString( qFinAidA, "FinAidSource", "SourceType", "W", "FinAidProfile" );
         //:IF RESULT >= zCURSOR_SET OR qFinAidA.FinAidProfile.DependencyStatus = "D"
         if ( RESULT >= zCURSOR_SET || CompareAttributeToString( qFinAidA, "FinAidProfile", "DependencyStatus", "D" ) == 0 )
         { 
            //:dWorkOptionAmount = lFAAdmin.FinAidAdmin.FederalAwardLetterWorkOptionAmt 
            {MutableDouble md_dWorkOptionAmount = new MutableDouble( dWorkOptionAmount );
                         GetDecimalFromAttribute( md_dWorkOptionAmount, lFAAdmin, "FinAidAdmin", "FederalAwardLetterWorkOptionAmt" );
            dWorkOptionAmount = md_dWorkOptionAmount.doubleValue( );}
         } 

         //:END 
      } 

      //:END
   } 

   //:END

   //:// Loan Options
   //:// Include all government loans.
   //:dLoanOptionTotal = 0
   dLoanOptionTotal = 0;
   //:FOR EACH qFinAidA.FinAidAwardAssigned 
   RESULT = SetCursorFirstEntity( qFinAidA, "FinAidAwardAssigned", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// We will skip any Declined entries.
      //:IF qFinAidA.FinAidAwardAssigned.AwardStatus = "P" OR 
      //:   qFinAidA.FinAidAwardAssigned.AwardStatus = "Y" OR 
      //:   qFinAidA.FinAidAwardAssigned.AwardStatus = "A"
      if ( CompareAttributeToString( qFinAidA, "FinAidAwardAssigned", "AwardStatus", "P" ) == 0 || CompareAttributeToString( qFinAidA, "FinAidAwardAssigned", "AwardStatus", "Y" ) == 0 ||
           CompareAttributeToString( qFinAidA, "FinAidAwardAssigned", "AwardStatus", "A" ) == 0 )
      { 

         //:IF qFinAidA.FinAidSource.SourceType = "L" AND qFinAidA.FinAidSource.Provider = "G"
         if ( CompareAttributeToString( qFinAidA, "FinAidSource", "SourceType", "L" ) == 0 && CompareAttributeToString( qFinAidA, "FinAidSource", "Provider", "G" ) == 0 )
         { 

            //:// Make sure Disbursements are ordered so Fall is first.
            //:OrderEntityForView( qFinAidA, "FinAidAwardDisbursement", "DisbursementDate A" )
            OrderEntityForView( qFinAidA, "FinAidAwardDisbursement", "DisbursementDate A" );
            //:dAwardAmount = qFinAidA.FinAidAwardAssigned.Amount 
            {MutableDouble md_dAwardAmount = new MutableDouble( dAwardAmount );
                         GetDecimalFromAttribute( md_dAwardAmount, qFinAidA, "FinAidAwardAssigned", "Amount" );
            dAwardAmount = md_dAwardAmount.doubleValue( );}
            //:IF dAwardAmount > 0
            if ( dAwardAmount > 0 )
            { 
               //:CREATE ENTITY qFinAidA.AwardLetterFederalLoan 
               RESULT = CreateEntity( qFinAidA, "AwardLetterFederalLoan", zPOS_AFTER );
               //:qFinAidA.AwardLetterFederalLoan.Description = qFinAidA.FinAidSource.Name
               SetAttributeFromAttribute( qFinAidA, "AwardLetterFederalLoan", "Description", qFinAidA, "FinAidSource", "Name" );
               //:qFinAidA.AwardLetterFederalLoan.Amount      = dAwardAmount
               SetAttributeFromDecimal( qFinAidA, "AwardLetterFederalLoan", "Amount", dAwardAmount );
               //:GetStringFromAttributeByContext( szRevenueWithDollarSign, 
               //:                                 qFinAidA, "AwardLetterFederalLoan", "Amount", "RevenueWithDollarSign", 30 )
               {StringBuilder sb_szRevenueWithDollarSign;
               if ( szRevenueWithDollarSign == null )
                  sb_szRevenueWithDollarSign = new StringBuilder( 32 );
               else
                  sb_szRevenueWithDollarSign = new StringBuilder( szRevenueWithDollarSign );
                               GetStringFromAttributeByContext( sb_szRevenueWithDollarSign, qFinAidA, "AwardLetterFederalLoan", "Amount", "RevenueWithDollarSign", 30 );
               szRevenueWithDollarSign = sb_szRevenueWithDollarSign.toString( );}
               //:qFinAidA.AwardLetterFederalLoan.AmountWSign = szRevenueWithDollarSign
               SetAttributeFromString( qFinAidA, "AwardLetterFederalLoan", "AmountWSign", szRevenueWithDollarSign );

               //:// Also set Fall and Spring Amounts.
               //:FOR EACH qFinAidA.FinAidAwardDisbursement 
               RESULT = SetCursorFirstEntity( qFinAidA, "FinAidAwardDisbursement", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:IF qFinAidA.FinAidAwardPeriod.PeriodDesignator = szPeriodDesignatorFall
                  if ( CompareAttributeToString( qFinAidA, "FinAidAwardPeriod", "PeriodDesignator", szPeriodDesignatorFall ) == 0 )
                  { 
                     //:qFinAidA.AwardLetterFederalLoan.FallAmount = qFinAidA.FinAidAwardDisbursement.AmountExpected 
                     SetAttributeFromAttribute( qFinAidA, "AwardLetterFederalLoan", "FallAmount", qFinAidA, "FinAidAwardDisbursement", "AmountExpected" );
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF qFinAidA.FinAidAwardPeriod.PeriodDesignator = szPeriodDesignatorSpring
                     if ( CompareAttributeToString( qFinAidA, "FinAidAwardPeriod", "PeriodDesignator", szPeriodDesignatorSpring ) == 0 )
                     { 
                        //:qFinAidA.AwardLetterFederalLoan.SpringAmount = qFinAidA.FinAidAwardDisbursement.AmountExpected 
                        SetAttributeFromAttribute( qFinAidA, "AwardLetterFederalLoan", "SpringAmount", qFinAidA, "FinAidAwardDisbursement", "AmountExpected" );
                     } 

                     //:END
                  } 

                  RESULT = SetCursorNextEntity( qFinAidA, "FinAidAwardDisbursement", "" );
                  //:END
               } 

               //:END

               //:dLoanOptionTotal = dLoanOptionTotal + dAwardAmount
               dLoanOptionTotal = dLoanOptionTotal + dAwardAmount;
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( qFinAidA, "FinAidAwardAssigned", "" );
      //:END
   } 

   //:END

   //:// Set Total Amounts.
   //:qFinAidA.AwardLetterFederal.CostOfAttendanceTotal           = dCostOfAttendanceTotal
   SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "CostOfAttendanceTotal", dCostOfAttendanceTotal );
   //:qFinAidA.AwardLetterFederal.InstGrantsAndScholarshipsTotal  = dInstGrantsScholarshipsTotal
   SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "InstGrantsAndScholarshipsTotal", dInstGrantsScholarshipsTotal );
   //:qFinAidA.AwardLetterFederal.InstGrantsAndScholarshipsTotal4 = dInstGrantsScholarshipsTotal * 4
   dTempDecimal_8 = dInstGrantsScholarshipsTotal * 4;
   SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "InstGrantsAndScholarshipsTotal4", dTempDecimal_8 );
   //:qFinAidA.AwardLetterFederal.GrantsAndScholarshipsTotal      = dGrantsScholarshipsTotal
   SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "GrantsAndScholarshipsTotal", dGrantsScholarshipsTotal );
   //:qFinAidA.AwardLetterFederal.NetCosts                        = dCostOfAttendanceTotal - dGrantsScholarshipsTotal
   dTempDecimal_9 = dCostOfAttendanceTotal - dGrantsScholarshipsTotal;
   SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "NetCosts", dTempDecimal_9 );
   //:qFinAidA.AwardLetterFederal.WorkOptionsTotal                = dWorkOptionAmount
   SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "WorkOptionsTotal", dWorkOptionAmount );
   //:qFinAidA.AwardLetterFederal.LoanOptionsTotal                = dLoanOptionTotal
   SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "LoanOptionsTotal", dLoanOptionTotal );
   //:qFinAidA.AwardLetterFederal.FinalNetCosts                   = dCostOfAttendanceTotal   - 
   //:                                                              dGrantsScholarshipsTotal -
   //:                                                              dWorkOptionAmount        -
   //:                                                              dLoanOptionTotal
   dTempDecimal_10 = dCostOfAttendanceTotal - dGrantsScholarshipsTotal - dWorkOptionAmount - dLoanOptionTotal;
   SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "FinalNetCosts", dTempDecimal_10 );

   //:// Set Fall and Spring Total amounts.
   //:IF szSpringAwardsFlag = "Y" OR szFullYearFlag = "Y"
   if ( ZeidonStringCompare( szSpringAwardsFlag, 1, 0, "Y", 1, 0, 2 ) == 0 || ZeidonStringCompare( szFullYearFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:// Both Fall and Spring amounts were specified.
      //:// First set Fall amounts as half of total amounts.
      //:qFinAidA.AwardLetterFederal.InstGrantsAndScholarshipsFall  = qFinAidA.AwardLetterFederal.InstGrantsAndScholarshipsTotal / 2
      {MutableDouble md_dTempDecimal_11 = new MutableDouble( dTempDecimal_11 );
             GetDecimalFromAttribute( md_dTempDecimal_11, qFinAidA, "AwardLetterFederal", "InstGrantsAndScholarshipsTotal" );
      dTempDecimal_11 = md_dTempDecimal_11.doubleValue( );}
      dTempDecimal_12 = dTempDecimal_11 / 2;
      SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "InstGrantsAndScholarshipsFall", dTempDecimal_12 );
      //:qFinAidA.AwardLetterFederal.InstGrantsAndScholarshipsFall4 = qFinAidA.AwardLetterFederal.InstGrantsAndScholarshipsTotal4 / 2
      {MutableDouble md_dTempDecimal_13 = new MutableDouble( dTempDecimal_13 );
             GetDecimalFromAttribute( md_dTempDecimal_13, qFinAidA, "AwardLetterFederal", "InstGrantsAndScholarshipsTotal4" );
      dTempDecimal_13 = md_dTempDecimal_13.doubleValue( );}
      dTempDecimal_14 = dTempDecimal_13 / 2;
      SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "InstGrantsAndScholarshipsFall4", dTempDecimal_14 );
      //:qFinAidA.AwardLetterFederal.GrantsAndScholarshipsFall      = qFinAidA.AwardLetterFederal.GrantsAndScholarshipsTotal / 2
      {MutableDouble md_dTempDecimal_15 = new MutableDouble( dTempDecimal_15 );
             GetDecimalFromAttribute( md_dTempDecimal_15, qFinAidA, "AwardLetterFederal", "GrantsAndScholarshipsTotal" );
      dTempDecimal_15 = md_dTempDecimal_15.doubleValue( );}
      dTempDecimal_16 = dTempDecimal_15 / 2;
      SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "GrantsAndScholarshipsFall", dTempDecimal_16 );
      //:qFinAidA.AwardLetterFederal.NetCostsFall                   = qFinAidA.AwardLetterFederal.NetCosts / 2
      {MutableDouble md_dTempDecimal_17 = new MutableDouble( dTempDecimal_17 );
             GetDecimalFromAttribute( md_dTempDecimal_17, qFinAidA, "AwardLetterFederal", "NetCosts" );
      dTempDecimal_17 = md_dTempDecimal_17.doubleValue( );}
      dTempDecimal_18 = dTempDecimal_17 / 2;
      SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "NetCostsFall", dTempDecimal_18 );
      //:qFinAidA.AwardLetterFederal.WorkOptionsFall                = qFinAidA.AwardLetterFederal.WorkOptionsTotal / 2
      {MutableDouble md_dTempDecimal_19 = new MutableDouble( dTempDecimal_19 );
             GetDecimalFromAttribute( md_dTempDecimal_19, qFinAidA, "AwardLetterFederal", "WorkOptionsTotal" );
      dTempDecimal_19 = md_dTempDecimal_19.doubleValue( );}
      dTempDecimal_20 = dTempDecimal_19 / 2;
      SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "WorkOptionsFall", dTempDecimal_20 );
      //:qFinAidA.AwardLetterFederal.LoanOptionsFall                = qFinAidA.AwardLetterFederal.LoanOptionsTotal / 2
      {MutableDouble md_dTempDecimal_21 = new MutableDouble( dTempDecimal_21 );
             GetDecimalFromAttribute( md_dTempDecimal_21, qFinAidA, "AwardLetterFederal", "LoanOptionsTotal" );
      dTempDecimal_21 = md_dTempDecimal_21.doubleValue( );}
      dTempDecimal_22 = dTempDecimal_21 / 2;
      SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "LoanOptionsFall", dTempDecimal_22 );
      //:qFinAidA.AwardLetterFederal.FinalNetCostsFall              = qFinAidA.AwardLetterFederal.FinalNetCosts / 2
      {MutableDouble md_dTempDecimal_23 = new MutableDouble( dTempDecimal_23 );
             GetDecimalFromAttribute( md_dTempDecimal_23, qFinAidA, "AwardLetterFederal", "FinalNetCosts" );
      dTempDecimal_23 = md_dTempDecimal_23.doubleValue( );}
      dTempDecimal_24 = dTempDecimal_23 / 2;
      SetAttributeFromDecimal( qFinAidA, "AwardLetterFederal", "FinalNetCostsFall", dTempDecimal_24 );

      //:// Then set Spring amounts from Fall amounts.
      //:qFinAidA.AwardLetterFederal.InstGrantsAndScholarshipsSpring  = qFinAidA.AwardLetterFederal.InstGrantsAndScholarshipsFall 
      SetAttributeFromAttribute( qFinAidA, "AwardLetterFederal", "InstGrantsAndScholarshipsSpring", qFinAidA, "AwardLetterFederal", "InstGrantsAndScholarshipsFall" );
      //:qFinAidA.AwardLetterFederal.InstGrantsAndScholarshipsSpring4 = qFinAidA.AwardLetterFederal.InstGrantsAndScholarshipsFall4 
      SetAttributeFromAttribute( qFinAidA, "AwardLetterFederal", "InstGrantsAndScholarshipsSpring4", qFinAidA, "AwardLetterFederal", "InstGrantsAndScholarshipsFall4" );
      //:qFinAidA.AwardLetterFederal.GrantsAndScholarshipsSpring      = qFinAidA.AwardLetterFederal.GrantsAndScholarshipsFall 
      SetAttributeFromAttribute( qFinAidA, "AwardLetterFederal", "GrantsAndScholarshipsSpring", qFinAidA, "AwardLetterFederal", "GrantsAndScholarshipsFall" );
      //:qFinAidA.AwardLetterFederal.NetCostsSpring                   = qFinAidA.AwardLetterFederal.NetCostsFall
      SetAttributeFromAttribute( qFinAidA, "AwardLetterFederal", "NetCostsSpring", qFinAidA, "AwardLetterFederal", "NetCostsFall" );
      //:qFinAidA.AwardLetterFederal.WorkOptionsSpring                = qFinAidA.AwardLetterFederal.WorkOptionsFall 
      SetAttributeFromAttribute( qFinAidA, "AwardLetterFederal", "WorkOptionsSpring", qFinAidA, "AwardLetterFederal", "WorkOptionsFall" );
      //:qFinAidA.AwardLetterFederal.LoanOptionsSpring                = qFinAidA.AwardLetterFederal.LoanOptionsFall 
      SetAttributeFromAttribute( qFinAidA, "AwardLetterFederal", "LoanOptionsSpring", qFinAidA, "AwardLetterFederal", "LoanOptionsFall" );
      //:qFinAidA.AwardLetterFederal.FinalNetCostsSpring              = qFinAidA.AwardLetterFederal.FinalNetCostsFall
      SetAttributeFromAttribute( qFinAidA, "AwardLetterFederal", "FinalNetCostsSpring", qFinAidA, "AwardLetterFederal", "FinalNetCostsFall" );
      //:ELSE
   } 
   else
   { 
      //:// Only Fall amounts were specified.
      //:qFinAidA.AwardLetterFederal.InstGrantsAndScholarshipsFall  = qFinAidA.AwardLetterFederal.InstGrantsAndScholarshipsTotal 
      SetAttributeFromAttribute( qFinAidA, "AwardLetterFederal", "InstGrantsAndScholarshipsFall", qFinAidA, "AwardLetterFederal", "InstGrantsAndScholarshipsTotal" );
      //:qFinAidA.AwardLetterFederal.InstGrantsAndScholarshipsFall4 = qFinAidA.AwardLetterFederal.InstGrantsAndScholarshipsTotal4 
      SetAttributeFromAttribute( qFinAidA, "AwardLetterFederal", "InstGrantsAndScholarshipsFall4", qFinAidA, "AwardLetterFederal", "InstGrantsAndScholarshipsTotal4" );
      //:qFinAidA.AwardLetterFederal.GrantsAndScholarshipsFall      = qFinAidA.AwardLetterFederal.GrantsAndScholarshipsTotal 
      SetAttributeFromAttribute( qFinAidA, "AwardLetterFederal", "GrantsAndScholarshipsFall", qFinAidA, "AwardLetterFederal", "GrantsAndScholarshipsTotal" );
      //:qFinAidA.AwardLetterFederal.NetCostsFall                   = qFinAidA.AwardLetterFederal.NetCosts 
      SetAttributeFromAttribute( qFinAidA, "AwardLetterFederal", "NetCostsFall", qFinAidA, "AwardLetterFederal", "NetCosts" );
      //:qFinAidA.AwardLetterFederal.WorkOptionsFall                = qFinAidA.AwardLetterFederal.WorkOptionsTotal 
      SetAttributeFromAttribute( qFinAidA, "AwardLetterFederal", "WorkOptionsFall", qFinAidA, "AwardLetterFederal", "WorkOptionsTotal" );
      //:qFinAidA.AwardLetterFederal.LoanOptionsFall                = qFinAidA.AwardLetterFederal.LoanOptionsTotal 
      SetAttributeFromAttribute( qFinAidA, "AwardLetterFederal", "LoanOptionsFall", qFinAidA, "AwardLetterFederal", "LoanOptionsTotal" );
      //:qFinAidA.AwardLetterFederal.FinalNetCostsFall              = qFinAidA.AwardLetterFederal.FinalNetCosts 
      SetAttributeFromAttribute( qFinAidA, "AwardLetterFederal", "FinalNetCostsFall", qFinAidA, "AwardLetterFederal", "FinalNetCosts" );
   } 

   //:END
   return( 0 );
//    
// END
} 


//:GLOBAL OPERATION
//:CalculatePellCOA( VIEW mFAProf BASED ON LOD mFAProf,
//:                  DECIMAL ReturnedTotal )

//:   VIEW mFAProf2 BASED ON LOD mFAProf
public int 
CalculatePellCOA( View     mFAProf,
                  MutableDouble  ReturnedTotal )
{
   zVIEW    mFAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   //:STRING ( 20 ) szDecimal
   String   szDecimal = null;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;


   //:// PELL COA is always all Whole Year costs.
   //:CreateViewFromView( mFAProf2, mFAProf )
   CreateViewFromView( mFAProf2, mFAProf );
   //:dAmount = 0 
   dAmount = 0;
   //:FOR EACH mFAProf2.FinAidCOAItemAssigned 
   RESULT = SetCursorFirstEntity( mFAProf2, "FinAidCOAItemAssigned", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mFAProf2.FinAidCOAItemAssigned.RevenueAmount != ""
      if ( CompareAttributeToString( mFAProf2, "FinAidCOAItemAssigned", "RevenueAmount", "" ) != 0 )
      { 
         //:dAmount = dAmount + mFAProf2.FinAidCOAItemAssigned.RevenueAmount 
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf2, "FinAidCOAItemAssigned", "RevenueAmount" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         dAmount = dAmount + dTempDecimal_0;
         //:ELSE
      } 
      else
      { 
         //:dAmount = dAmount + mFAProf2.FinAidCOAItemAssigned.FirstTermRevenueAmount +
         //:                    mFAProf2.FinAidCOAItemAssigned.SecondTermRevenueAmount
         {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                   GetDecimalFromAttribute( md_dTempDecimal_1, mFAProf2, "FinAidCOAItemAssigned", "FirstTermRevenueAmount" );
         dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
         {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                   GetDecimalFromAttribute( md_dTempDecimal_2, mFAProf2, "FinAidCOAItemAssigned", "SecondTermRevenueAmount" );
         dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
         dAmount = dAmount + dTempDecimal_1 + dTempDecimal_2;
      } 

      RESULT = SetCursorNextEntity( mFAProf2, "FinAidCOAItemAssigned", "" );
      //:END
   } 

   //:END
   //:ReturnedTotal = dAmount
   ReturnedTotal.setValue( dAmount );

   //:DropView( mFAProf2 )
   DropView( mFAProf2 );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:ConvertToString10( VIEW AnyView,
//:                   STRING ( 32 ) szStringIn,
//:                   STRING ( 32 ) szStringOut )

//:   INTEGER nLen
public void 
ConvertToString10( View     AnyView,
                   String   szStringIn,
                   StringBuilder   szStringOut )
{
   int      nLen = 0;
   //:INTEGER nLead
   int      nLead = 0;
   //:INTEGER i
   int      i = 0;
   //:STRING (  14  ) szTarget
   String   szTarget = null;


   //:// Pad the input string to get a string of length 10.

   //:szTarget = "          "
    {StringBuilder sb_szTarget;
   if ( szTarget == null )
      sb_szTarget = new StringBuilder( 32 );
   else
      sb_szTarget = new StringBuilder( szTarget );
      ZeidonStringCopy( sb_szTarget, 1, 0, "          ", 1, 0, 15 );
   szTarget = sb_szTarget.toString( );}
   //:nLen = zstrlen( szStringIn )
   nLen = zstrlen( szStringIn );
   //:nLead = 11 - nLen 
   nLead = 11 - nLen;
   //:szTarget[ nLead : nLen ] = szStringIn[ 1: nLen ]
    {StringBuilder sb_szTarget;
   if ( szTarget == null )
      sb_szTarget = new StringBuilder( 32 );
   else
      sb_szTarget = new StringBuilder( szTarget );
      ZeidonStringCopy( sb_szTarget, nLead, nLen, szStringIn, 1, nLen, 15 );
   szTarget = sb_szTarget.toString( );}

   //:szStringOut = szTarget
   ZeidonStringCopy( szStringOut, 1, 0, szTarget, 1, 0, 33 );
   return;
// END
} 


//:GLOBAL OPERATION
//:GenerateFA_Awarded( VIEW qFinAidA BASED ON LOD qFinAidA )

//:   VIEW mAdmDiv  BASED ON LOD  mAdmDiv
public int 
GenerateFA_Awarded( View     qFinAidA )
{
   zVIEW    mAdmDiv = new zVIEW( );
   //:VIEW wXferO   REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mFAConst BASED ON LOD  mFAConst
   zVIEW    mFAConst = new zVIEW( );
   //:STRING ( 30 ) szString
   String   szString = null;
   //:STRING ( 8  ) LatestDate
   String   LatestDate = null;
   //:STRING ( 254 ) szAwardValue
   String   szAwardValue = null;
   //:STRING ( 254 ) szAwardValue3Term
   String   szAwardValue3Term = null;
   //:STRING ( 254 ) szAwardValueTotal
   String   szAwardValueTotal = null;
   //:STRING ( 10000 ) szFootnote
   String   szFootnote = null;
   //:STRING ( 254 ) szAwardName
   String   szAwardName = null;
   //:STRING ( 14 ) szFallAmount
   String   szFallAmount = null;
   //:STRING ( 14 ) szSpringAmount
   String   szSpringAmount = null;
   //:STRING ( 14 ) szSummerAmount
   String   szSummerAmount = null;
   //:STRING ( 14 ) szAmount
   String   szAmount = null;
   //:STRING ( 14 ) szFootnoteNumber
   String   szFootnoteNumber = null;
   //:STRING ( 14 ) szSomeNumber
   String   szSomeNumber = null;
   //:STRING ( 9 )  szCollegeYear
   String   szCollegeYear = null;
   //:STRING ( 20 ) szCollegeTerm
   String   szCollegeTerm = null;
   //:STRING ( 254 ) szCOAString
   String   szCOAString = null;
   //:STRING ( 30 ) szCOAName
   String   szCOAName = null;
   //:STRING ( 254 ) szCOANumber
   String   szCOANumber = null;
   //:STRING ( 254 ) szMsg
   String   szMsg = null;
   //:STRING ( 20 )  szAcceptedDeclinedText
   String   szAcceptedDeclinedText = null;
   //:STRING ( 1 )   szClearedFlag
   String   szClearedFlag = null;
   //:INTEGER nFNNumber
   int      nFNNumber = 0;
   //:INTEGER nLen
   int      nLen = 0;
   //:DECIMAL dParentStudentLoan
   double  dParentStudentLoan = 0.0;
   //:DECIMAL dParentStudentLoanHalf
   double  dParentStudentLoanHalf = 0.0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;
   double  dTempDecimal_4 = 0.0;
   double  dTempDecimal_5 = 0.0;
   double  dTempDecimal_6 = 0.0;
   double  dTempDecimal_7 = 0.0;
   double  dTempDecimal_8 = 0.0;
   double  dTempDecimal_9 = 0.0;
   double  dTempDecimal_10 = 0.0;
   double  dTempDecimal_11 = 0.0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   String   szTempString_1 = null;
   double  dTempDecimal_12 = 0.0;
   double  dTempDecimal_13 = 0.0;
   double  dTempDecimal_14 = 0.0;
   double  dTempDecimal_15 = 0.0;
   double  dTempDecimal_16 = 0.0;
   double  dTempDecimal_17 = 0.0;
   double  dTempDecimal_18 = 0.0;
   double  dTempDecimal_19 = 0.0;
   double  dTempDecimal_20 = 0.0;
   double  dTempDecimal_21 = 0.0;
   double  dTempDecimal_22 = 0.0;
   double  dTempDecimal_23 = 0.0;
   String   szTempString_2 = null;
   double  dTempDecimal_24 = 0.0;
   double  dTempDecimal_25 = 0.0;
   double  dTempDecimal_26 = 0.0;
   double  dTempDecimal_27 = 0.0;
   double  dTempDecimal_28 = 0.0;
   double  dTempDecimal_29 = 0.0;
   double  dTempDecimal_30 = 0.0;
   double  dTempDecimal_31 = 0.0;
   double  dTempDecimal_32 = 0.0;
   double  dTempDecimal_33 = 0.0;
   double  dTempDecimal_34 = 0.0;
   double  dTempDecimal_35 = 0.0;
   String   szTempString_3 = null;
   double  dTempDecimal_36 = 0.0;
   double  dTempDecimal_37 = 0.0;
   double  dTempDecimal_38 = 0.0;
   double  dTempDecimal_39 = 0.0;
   double  dTempDecimal_40 = 0.0;
   double  dTempDecimal_41 = 0.0;
   double  dTempDecimal_42 = 0.0;
   double  dTempDecimal_43 = 0.0;
   double  dTempDecimal_44 = 0.0;
   double  dTempDecimal_45 = 0.0;
   double  dTempDecimal_46 = 0.0;
   double  dTempDecimal_47 = 0.0;
   double  dTempDecimal_48 = 0.0;
   double  dTempDecimal_49 = 0.0;
   double  dTempDecimal_50 = 0.0;
   double  dTempDecimal_51 = 0.0;
   double  dTempDecimal_52 = 0.0;
   double  dTempDecimal_53 = 0.0;
   double  dTempDecimal_54 = 0.0;
   double  dTempDecimal_55 = 0.0;
   double  dTempDecimal_56 = 0.0;
   double  dTempDecimal_57 = 0.0;
   double  dTempDecimal_58 = 0.0;
   double  dTempDecimal_59 = 0.0;
   int      lTempInteger_5 = 0;
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   String   szTempString_6 = null;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   double  dTempDecimal_60 = 0.0;
   double  dTempDecimal_61 = 0.0;
   double  dTempDecimal_62 = 0.0;
   double  dTempDecimal_63 = 0.0;
   double  dTempDecimal_64 = 0.0;
   double  dTempDecimal_65 = 0.0;
   double  dTempDecimal_66 = 0.0;
   double  dTempDecimal_67 = 0.0;
   double  dTempDecimal_68 = 0.0;
   String   szTempString_7 = null;
   double  dTempDecimal_69 = 0.0;
   double  dTempDecimal_70 = 0.0;
   double  dTempDecimal_71 = 0.0;
   int      lTempInteger_8 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", qFinAidA, zLEVEL_TASK );

   //:// Generate Fin Aid Awards work attributes for any object with the correct qFinAidA subobject structure
   //:// for formatting FA Requirements.

   //:nFNNumber = 0
   nFNNumber = 0;
   //:szFootnote = ""
    {StringBuilder sb_szFootnote;
   if ( szFootnote == null )
      sb_szFootnote = new StringBuilder( 32 );
   else
      sb_szFootnote = new StringBuilder( szFootnote );
      ZeidonStringCopy( sb_szFootnote, 1, 0, "", 1, 0, 10001 );
   szFootnote = sb_szFootnote.toString( );}

   //:CREATE ENTITY qFinAidA.AwardLetters  
   RESULT = CreateEntity( qFinAidA, "AwardLetters", zPOS_AFTER );

   //:// Generate Awards as either awarded or declined.
   //:FOR EACH qFinAidA.FinAidAwardAssigned WHERE qFinAidA.FinAidAwardAssigned.AwardStatus != "I"
   RESULT = SetCursorFirstEntity( qFinAidA, "FinAidAwardAssigned", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( qFinAidA, "FinAidAwardAssigned", "AwardStatus", "I" ) != 0 )
      { 

         //:// If the award is declined, create a FinAidDeclined entity instead.
         //:IF qFinAidA.FinAidAwardAssigned.AwardStatus = "N"
         if ( CompareAttributeToString( qFinAidA, "FinAidAwardAssigned", "AwardStatus", "N" ) == 0 )
         { 

            //:// The Award IS declined.
            //:CREATE ENTITY qFinAidA.FinAidDeclined  
            RESULT = CreateEntity( qFinAidA, "FinAidDeclined", zPOS_AFTER );
            //:qFinAidA.FinAidDeclined.AwardName = qFinAidA.FinAidSource.Name
            SetAttributeFromAttribute( qFinAidA, "FinAidDeclined", "AwardName", qFinAidA, "FinAidSource", "Name" );
            //:qFinAidA.FinAidDeclined.Amount    = qFinAidA.FinAidAwardAssigned.Amount 
            SetAttributeFromAttribute( qFinAidA, "FinAidDeclined", "Amount", qFinAidA, "FinAidAwardAssigned", "Amount" );

            //:ELSE
         } 
         else
         { 

            //:// The Award is NOT declined.

            //:szAwardValue = ""
             {StringBuilder sb_szAwardValue;
            if ( szAwardValue == null )
               sb_szAwardValue = new StringBuilder( 32 );
            else
               sb_szAwardValue = new StringBuilder( szAwardValue );
                        ZeidonStringCopy( sb_szAwardValue, 1, 0, "", 1, 0, 255 );
            szAwardValue = sb_szAwardValue.toString( );}
            //:szFallAmount = ""
             {StringBuilder sb_szFallAmount;
            if ( szFallAmount == null )
               sb_szFallAmount = new StringBuilder( 32 );
            else
               sb_szFallAmount = new StringBuilder( szFallAmount );
                        ZeidonStringCopy( sb_szFallAmount, 1, 0, "", 1, 0, 15 );
            szFallAmount = sb_szFallAmount.toString( );}
            //:szSpringAmount = ""
             {StringBuilder sb_szSpringAmount;
            if ( szSpringAmount == null )
               sb_szSpringAmount = new StringBuilder( 32 );
            else
               sb_szSpringAmount = new StringBuilder( szSpringAmount );
                        ZeidonStringCopy( sb_szSpringAmount, 1, 0, "", 1, 0, 15 );
            szSpringAmount = sb_szSpringAmount.toString( );}
            //:szSummerAmount = ""
             {StringBuilder sb_szSummerAmount;
            if ( szSummerAmount == null )
               sb_szSummerAmount = new StringBuilder( 32 );
            else
               sb_szSummerAmount = new StringBuilder( szSummerAmount );
                        ZeidonStringCopy( sb_szSummerAmount, 1, 0, "", 1, 0, 15 );
            szSummerAmount = sb_szSummerAmount.toString( );}

            //:CREATE ENTITY qFinAidA.FinAidAwarded 
            RESULT = CreateEntity( qFinAidA, "FinAidAwarded", zPOS_AFTER );
            //:szAwardName = qFinAidA.FinAidSource.Name
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szAwardName;
            if ( szAwardName == null )
               sb_szAwardName = new StringBuilder( 32 );
            else
               sb_szAwardName = new StringBuilder( szAwardName );
                         GetVariableFromAttribute( sb_szAwardName, mi_lTempInteger_0, 'S', 255, qFinAidA, "FinAidSource", "Name", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szAwardName = sb_szAwardName.toString( );}
            //:qFinAidA.FinAidAwarded.AwardName = szAwardName 
            SetAttributeFromString( qFinAidA, "FinAidAwarded", "AwardName", szAwardName );
            //:// We will not get the FinAidAwarded.Amount from the FinAidAwardAssigned.Amount, but instead
            //:// add the Disbursements below. This is because the FinAidAwarded entry could be divided between
            //:// Swapped and Stacked awards.
            //://qFinAidA.FinAidAwarded.Amount = qFinAidA.FinAidAwardAssigned.Amount 

            //:OrderEntityForView( qFinAidA, "PeriodTotals", "PeriodDesignator A" )
            OrderEntityForView( qFinAidA, "PeriodTotals", "PeriodDesignator A" );
            //:SET CURSOR FIRST qFinAidA.PeriodTotals
            RESULT = SetCursorFirstEntity( qFinAidA, "PeriodTotals", "" );

            //:// Compute totals for each Period and then set Fall and Spring values from those totals.
            //:FOR EACH qFinAidA.FinAidAwardDisbursement
            RESULT = SetCursorFirstEntity( qFinAidA, "FinAidAwardDisbursement", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:// Don't include entries created by swapping. (SwappedFromDisbursement doesn't exist)
               //:IF qFinAidA.SwappedFromDisbursement DOES NOT EXIST
               lTempInteger_1 = CheckExistenceOfEntity( qFinAidA, "SwappedFromDisbursement" );
               if ( lTempInteger_1 != 0 )
               { 
                  //:SET CURSOR FIRST qFinAidA.PeriodTotals 
                  //:        WHERE qFinAidA.PeriodTotals.PeriodDesignator = qFinAidA.FinAidAwardPeriod.PeriodDesignator  
                  {StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                     GetStringFromAttribute( sb_szTempString_0, qFinAidA, "FinAidAwardPeriod", "PeriodDesignator" );
                  szTempString_0 = sb_szTempString_0.toString( );}
                  RESULT = SetCursorFirstEntityByString( qFinAidA, "PeriodTotals", "PeriodDesignator", szTempString_0, "" );
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:CREATE ENTITY qFinAidA.PeriodTotals 
                     RESULT = CreateEntity( qFinAidA, "PeriodTotals", zPOS_AFTER );
                     //:qFinAidA.PeriodTotals.PeriodDesignator = qFinAidA.FinAidAwardPeriod.PeriodDesignator   
                     SetAttributeFromAttribute( qFinAidA, "PeriodTotals", "PeriodDesignator", qFinAidA, "FinAidAwardPeriod", "PeriodDesignator" );
                  } 

                  //:END
                  //:// The TotalAmount for the Period must consider that parts of the Disbursement could be swapped to another Disbursement.
                  //:// In that case, we must go to that other Disbursement and add that Amount.
                  //:// We'll also total amounts for the Award in the same way.
                  //:qFinAidA.FinAidAwarded.Amount = qFinAidA.FinAidAwarded.Amount + qFinAidA.FinAidAwardDisbursement.AmountExpected
                  {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                     GetDecimalFromAttribute( md_dTempDecimal_0, qFinAidA, "FinAidAwarded", "Amount" );
                  dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                  {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                                     GetDecimalFromAttribute( md_dTempDecimal_1, qFinAidA, "FinAidAwardDisbursement", "AmountExpected" );
                  dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
                  dTempDecimal_2 = dTempDecimal_0 + dTempDecimal_1;
                  SetAttributeFromDecimal( qFinAidA, "FinAidAwarded", "Amount", dTempDecimal_2 );

                  //:qFinAidA.PeriodTotals.TotalAmount = qFinAidA.PeriodTotals.TotalAmount + 
                  //:                                 qFinAidA.FinAidAwardDisbursement.AmountExpected
                  {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                                     GetDecimalFromAttribute( md_dTempDecimal_3, qFinAidA, "PeriodTotals", "TotalAmount" );
                  dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
                  {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                                     GetDecimalFromAttribute( md_dTempDecimal_4, qFinAidA, "FinAidAwardDisbursement", "AmountExpected" );
                  dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
                  dTempDecimal_5 = dTempDecimal_3 + dTempDecimal_4;
                  SetAttributeFromDecimal( qFinAidA, "PeriodTotals", "TotalAmount", dTempDecimal_5 );
                  //:FOR EACH qFinAidA.SwappedToDisbursement 
                  RESULT = SetCursorFirstEntity( qFinAidA, "SwappedToDisbursement", "" );
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //:qFinAidA.FinAidAwarded.Amount = qFinAidA.FinAidAwarded.Amount +
                     //:                             qFinAidA.SwappedToDisbursement.AmountExpected
                     {MutableDouble md_dTempDecimal_6 = new MutableDouble( dTempDecimal_6 );
                                           GetDecimalFromAttribute( md_dTempDecimal_6, qFinAidA, "FinAidAwarded", "Amount" );
                     dTempDecimal_6 = md_dTempDecimal_6.doubleValue( );}
                     {MutableDouble md_dTempDecimal_7 = new MutableDouble( dTempDecimal_7 );
                                           GetDecimalFromAttribute( md_dTempDecimal_7, qFinAidA, "SwappedToDisbursement", "AmountExpected" );
                     dTempDecimal_7 = md_dTempDecimal_7.doubleValue( );}
                     dTempDecimal_8 = dTempDecimal_6 + dTempDecimal_7;
                     SetAttributeFromDecimal( qFinAidA, "FinAidAwarded", "Amount", dTempDecimal_8 );
                     //:qFinAidA.PeriodTotals.TotalAmount = qFinAidA.PeriodTotals.TotalAmount + 
                     //:                                 qFinAidA.SwappedToDisbursement.AmountExpected 
                     {MutableDouble md_dTempDecimal_9 = new MutableDouble( dTempDecimal_9 );
                                           GetDecimalFromAttribute( md_dTempDecimal_9, qFinAidA, "PeriodTotals", "TotalAmount" );
                     dTempDecimal_9 = md_dTempDecimal_9.doubleValue( );}
                     {MutableDouble md_dTempDecimal_10 = new MutableDouble( dTempDecimal_10 );
                                           GetDecimalFromAttribute( md_dTempDecimal_10, qFinAidA, "SwappedToDisbursement", "AmountExpected" );
                     dTempDecimal_10 = md_dTempDecimal_10.doubleValue( );}
                     dTempDecimal_11 = dTempDecimal_9 + dTempDecimal_10;
                     SetAttributeFromDecimal( qFinAidA, "PeriodTotals", "TotalAmount", dTempDecimal_11 );
                     RESULT = SetCursorNextEntity( qFinAidA, "SwappedToDisbursement", "" );
                  } 

                  //:END
               } 

               RESULT = SetCursorNextEntity( qFinAidA, "FinAidAwardDisbursement", "" );
               //:END
            } 

            //:END

            //:// Set Fall, Spring and Summer Totals.
            //:szCollegeYear = qFinAidA.CollegeYear.Year 
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szCollegeYear;
            if ( szCollegeYear == null )
               sb_szCollegeYear = new StringBuilder( 32 );
            else
               sb_szCollegeYear = new StringBuilder( szCollegeYear );
                         GetVariableFromAttribute( sb_szCollegeYear, mi_lTempInteger_2, 'S', 10, qFinAidA, "CollegeYear", "Year", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szCollegeYear = sb_szCollegeYear.toString( );}

            //:// Fall
            //:szCollegeTerm = szCollegeYear + " Fall"
             {StringBuilder sb_szCollegeTerm;
            if ( szCollegeTerm == null )
               sb_szCollegeTerm = new StringBuilder( 32 );
            else
               sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                        ZeidonStringCopy( sb_szCollegeTerm, 1, 0, szCollegeYear, 1, 0, 21 );
            szCollegeTerm = sb_szCollegeTerm.toString( );}
             {StringBuilder sb_szCollegeTerm;
            if ( szCollegeTerm == null )
               sb_szCollegeTerm = new StringBuilder( 32 );
            else
               sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                        ZeidonStringConcat( sb_szCollegeTerm, 1, 0, " Fall", 1, 0, 21 );
            szCollegeTerm = sb_szCollegeTerm.toString( );}
            //:SET CURSOR FIRST qFinAidA.PeriodTotals WHERE qFinAidA.PeriodTotals.PeriodDesignator = szCollegeTerm
            RESULT = SetCursorFirstEntityByString( qFinAidA, "PeriodTotals", "PeriodDesignator", szCollegeTerm, "" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:// First Period is Fall.
               //:qFinAidA.FinAidAwarded.FallAmount = qFinAidA.PeriodTotals.TotalAmount
               SetAttributeFromAttribute( qFinAidA, "FinAidAwarded", "FallAmount", qFinAidA, "PeriodTotals", "TotalAmount" );
               //:GetStringFromAttributeByContext( szSomeNumber, qFinAidA, "FinAidAwarded", "FallAmount", "Revenue",10)
               {StringBuilder sb_szSomeNumber;
               if ( szSomeNumber == null )
                  sb_szSomeNumber = new StringBuilder( 32 );
               else
                  sb_szSomeNumber = new StringBuilder( szSomeNumber );
                               GetStringFromAttributeByContext( sb_szSomeNumber, qFinAidA, "FinAidAwarded", "FallAmount", "Revenue", 10 );
               szSomeNumber = sb_szSomeNumber.toString( );}
               //:nLen = zstrlen( szSomeNumber ) //szFallAmount
               nLen = zstrlen( szSomeNumber );
               //:ConvertToString10( qFinAidA, szSomeNumber, szFallAmount )
                {StringBuilder sb_szFallAmount;
               if ( szFallAmount == null )
                  sb_szFallAmount = new StringBuilder( 32 );
               else
                  sb_szFallAmount = new StringBuilder( szFallAmount );
                              ConvertToString10( qFinAidA, szSomeNumber, sb_szFallAmount );
               szFallAmount = sb_szFallAmount.toString( );}
            } 

            //:END

            //:// Spring
            //:szCollegeTerm = szCollegeYear + " Spring"
             {StringBuilder sb_szCollegeTerm;
            if ( szCollegeTerm == null )
               sb_szCollegeTerm = new StringBuilder( 32 );
            else
               sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                        ZeidonStringCopy( sb_szCollegeTerm, 1, 0, szCollegeYear, 1, 0, 21 );
            szCollegeTerm = sb_szCollegeTerm.toString( );}
             {StringBuilder sb_szCollegeTerm;
            if ( szCollegeTerm == null )
               sb_szCollegeTerm = new StringBuilder( 32 );
            else
               sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                        ZeidonStringConcat( sb_szCollegeTerm, 1, 0, " Spring", 1, 0, 21 );
            szCollegeTerm = sb_szCollegeTerm.toString( );}
            //:SET CURSOR FIRST qFinAidA.PeriodTotals WHERE qFinAidA.PeriodTotals.PeriodDesignator = szCollegeTerm
            RESULT = SetCursorFirstEntityByString( qFinAidA, "PeriodTotals", "PeriodDesignator", szCollegeTerm, "" );
            //:IF RESULT >= zCURSOR_SET 
            if ( RESULT >= zCURSOR_SET )
            { 
               //:// Second Period is Spring.
               //:qFinAidA.FinAidAwarded.SpringAmount = qFinAidA.PeriodTotals.TotalAmount
               SetAttributeFromAttribute( qFinAidA, "FinAidAwarded", "SpringAmount", qFinAidA, "PeriodTotals", "TotalAmount" );
               //:GetStringFromAttributeByContext( szSomeNumber, qFinAidA, "FinAidAwarded", "SpringAmount", "Revenue",10)
               {StringBuilder sb_szSomeNumber;
               if ( szSomeNumber == null )
                  sb_szSomeNumber = new StringBuilder( 32 );
               else
                  sb_szSomeNumber = new StringBuilder( szSomeNumber );
                               GetStringFromAttributeByContext( sb_szSomeNumber, qFinAidA, "FinAidAwarded", "SpringAmount", "Revenue", 10 );
               szSomeNumber = sb_szSomeNumber.toString( );}
               //:nLen = zstrlen( szSomeNumber ) //szSpringAmount
               nLen = zstrlen( szSomeNumber );
               //:ConvertToString10( qFinAidA, szSomeNumber, szSpringAmount )
                {StringBuilder sb_szSpringAmount;
               if ( szSpringAmount == null )
                  sb_szSpringAmount = new StringBuilder( 32 );
               else
                  sb_szSpringAmount = new StringBuilder( szSpringAmount );
                              ConvertToString10( qFinAidA, szSomeNumber, sb_szSpringAmount );
               szSpringAmount = sb_szSpringAmount.toString( );}
            } 

            //:END

            //:// Summer
            //:szCollegeTerm = szCollegeYear + " Summer"
             {StringBuilder sb_szCollegeTerm;
            if ( szCollegeTerm == null )
               sb_szCollegeTerm = new StringBuilder( 32 );
            else
               sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                        ZeidonStringCopy( sb_szCollegeTerm, 1, 0, szCollegeYear, 1, 0, 21 );
            szCollegeTerm = sb_szCollegeTerm.toString( );}
             {StringBuilder sb_szCollegeTerm;
            if ( szCollegeTerm == null )
               sb_szCollegeTerm = new StringBuilder( 32 );
            else
               sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                        ZeidonStringConcat( sb_szCollegeTerm, 1, 0, " Summer", 1, 0, 21 );
            szCollegeTerm = sb_szCollegeTerm.toString( );}
            //:SET CURSOR FIRST qFinAidA.PeriodTotals WHERE qFinAidA.PeriodTotals.PeriodDesignator = szCollegeTerm
            RESULT = SetCursorFirstEntityByString( qFinAidA, "PeriodTotals", "PeriodDesignator", szCollegeTerm, "" );
            //:IF RESULT >= zCURSOR_SET 
            if ( RESULT >= zCURSOR_SET )
            { 
               //:// Third Period is Summer.
               //:qFinAidA.FinAidAwarded.SummerAmount = qFinAidA.PeriodTotals.TotalAmount
               SetAttributeFromAttribute( qFinAidA, "FinAidAwarded", "SummerAmount", qFinAidA, "PeriodTotals", "TotalAmount" );
               //:GetStringFromAttributeByContext( szSomeNumber, qFinAidA, "FinAidAwarded", "SummerAmount", "Revenue",10)
               {StringBuilder sb_szSomeNumber;
               if ( szSomeNumber == null )
                  sb_szSomeNumber = new StringBuilder( 32 );
               else
                  sb_szSomeNumber = new StringBuilder( szSomeNumber );
                               GetStringFromAttributeByContext( sb_szSomeNumber, qFinAidA, "FinAidAwarded", "SummerAmount", "Revenue", 10 );
               szSomeNumber = sb_szSomeNumber.toString( );}
               //:nLen = zstrlen( szSomeNumber ) //szSummerAmount
               nLen = zstrlen( szSomeNumber );
               //:ConvertToString10( qFinAidA, szSomeNumber, szSummerAmount )
                {StringBuilder sb_szSummerAmount;
               if ( szSummerAmount == null )
                  sb_szSummerAmount = new StringBuilder( 32 );
               else
                  sb_szSummerAmount = new StringBuilder( szSummerAmount );
                              ConvertToString10( qFinAidA, szSomeNumber, sb_szSummerAmount );
               szSummerAmount = sb_szSummerAmount.toString( );}
            } 

            //:END
            //:OrderEntityForView( qFinAidA, "PeriodTotals", "PeriodDesignator A" )
            OrderEntityForView( qFinAidA, "PeriodTotals", "PeriodDesignator A" );

            //:// Build Award Groups by Federal Aid, Institutional Aid and Other Aid.
            //:// Don't Add if the entry has been swapped.
            //:IF qFinAidA.SwappedFromDisbursement DOES NOT EXIST
            lTempInteger_3 = CheckExistenceOfEntity( qFinAidA, "SwappedFromDisbursement" );
            if ( lTempInteger_3 != 0 )
            { 

               //:// Covert Amount to string.
               //:GetStringFromAttributeByContext( szSomeNumber, qFinAidA, "FinAidAwarded", "Amount", "Revenue",10)
               {StringBuilder sb_szSomeNumber;
               if ( szSomeNumber == null )
                  sb_szSomeNumber = new StringBuilder( 32 );
               else
                  sb_szSomeNumber = new StringBuilder( szSomeNumber );
                               GetStringFromAttributeByContext( sb_szSomeNumber, qFinAidA, "FinAidAwarded", "Amount", "Revenue", 10 );
               szSomeNumber = sb_szSomeNumber.toString( );}
               //:nLen = zstrlen( szSomeNumber ) //szAmount
               nLen = zstrlen( szSomeNumber );
               //:ConvertToString10( qFinAidA, szSomeNumber, szAmount )
                {StringBuilder sb_szAmount;
               if ( szAmount == null )
                  sb_szAmount = new StringBuilder( 32 );
               else
                  sb_szAmount = new StringBuilder( szAmount );
                              ConvertToString10( qFinAidA, szSomeNumber, sb_szAmount );
               szAmount = sb_szAmount.toString( );}

               //:szFootnote = qFinAidA.FinAidSource.SourceFootnote
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szFootnote;
               if ( szFootnote == null )
                  sb_szFootnote = new StringBuilder( 32 );
               else
                  sb_szFootnote = new StringBuilder( szFootnote );
                               GetVariableFromAttribute( sb_szFootnote, mi_lTempInteger_4, 'S', 10001, qFinAidA, "FinAidSource", "SourceFootnote", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szFootnote = sb_szFootnote.toString( );}
               //:// The following 3 lines were deleted by DonC on 4/14/2010 because it wasn't clear what they did or how we were positioned on mAdmDiv.
               //://IF qFinAidA.ReceivedItems EXISTS AND mAdmDiv.FA_FinAidRequirement EXISTS ???
               //://   qFinAidA.ReceivedItems.Description = mAdmDiv.FA_FinAidRequirement.Name ???
               //://END
               //:IF szFootnote != ""
               if ( ZeidonStringCompare( szFootnote, 1, 0, "", 1, 0, 10001 ) != 0 )
               { 
                  //:nFNNumber = nFNNumber + 1
                  nFNNumber = nFNNumber + 1;
                  //:zIntegerToString( szFootnoteNumber, 10, nFNNumber )
                  {StringBuilder sb_szFootnoteNumber;
                  if ( szFootnoteNumber == null )
                     sb_szFootnoteNumber = new StringBuilder( 32 );
                  else
                     sb_szFootnoteNumber = new StringBuilder( szFootnoteNumber );
                                     zIntegerToString( sb_szFootnoteNumber, 10, nFNNumber );
                  szFootnoteNumber = sb_szFootnoteNumber.toString( );}
                  //:szAwardName = szAwardName + "* " + szFootnoteNumber
                   {StringBuilder sb_szAwardName;
                  if ( szAwardName == null )
                     sb_szAwardName = new StringBuilder( 32 );
                  else
                     sb_szAwardName = new StringBuilder( szAwardName );
                                    ZeidonStringConcat( sb_szAwardName, 1, 0, "* ", 1, 0, 255 );
                  szAwardName = sb_szAwardName.toString( );}
                   {StringBuilder sb_szAwardName;
                  if ( szAwardName == null )
                     sb_szAwardName = new StringBuilder( 32 );
                  else
                     sb_szAwardName = new StringBuilder( szAwardName );
                                    ZeidonStringConcat( sb_szAwardName, 1, 0, szFootnoteNumber, 1, 0, 255 );
                  szAwardName = sb_szAwardName.toString( );}
                  //:qFinAidA.FinAidAwarded.AwardName = szAwardName 
                  SetAttributeFromString( qFinAidA, "FinAidAwarded", "AwardName", szAwardName );
                  //:CREATE ENTITY qFinAidA.Footnote
                  RESULT = CreateEntity( qFinAidA, "Footnote", zPOS_AFTER );
                  //:qFinAidA.Footnote.SourceFootnote = szFootnoteNumber + ": " + szFootnote
                   {StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                    ZeidonStringCopy( sb_szTempString_1, 1, 0, szFootnoteNumber, 1, 0, 10001 );
                  szTempString_1 = sb_szTempString_1.toString( );}
                   {StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                    ZeidonStringConcat( sb_szTempString_1, 1, 0, ": ", 1, 0, 10001 );
                  szTempString_1 = sb_szTempString_1.toString( );}
                   {StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                    ZeidonStringConcat( sb_szTempString_1, 1, 0, szFootnote, 1, 0, 10001 );
                  szTempString_1 = sb_szTempString_1.toString( );}
                  SetAttributeFromString( qFinAidA, "Footnote", "SourceFootnote", szTempString_1 );
                  //:qFinAidA.Footnote.AwardName = qFinAidA.FinAidAwarded.AwardName  
                  SetAttributeFromAttribute( qFinAidA, "Footnote", "AwardName", qFinAidA, "FinAidAwarded", "AwardName" );
                  //:szFootnote = ""
                   {StringBuilder sb_szFootnote;
                  if ( szFootnote == null )
                     sb_szFootnote = new StringBuilder( 32 );
                  else
                     sb_szFootnote = new StringBuilder( szFootnote );
                                    ZeidonStringCopy( sb_szFootnote, 1, 0, "", 1, 0, 10001 );
                  szFootnote = sb_szFootnote.toString( );}
               } 

               //:END
               //:szAwardValue      = qFinAidA.FinAidAwarded.AwardName + zTAB + szFallAmount + zTAB + szSpringAmount + zTAB + szAmount 
               {StringBuilder sb_szAwardValue;
               if ( szAwardValue == null )
                  sb_szAwardValue = new StringBuilder( 32 );
               else
                  sb_szAwardValue = new StringBuilder( szAwardValue );
                               GetStringFromAttribute( sb_szAwardValue, qFinAidA, "FinAidAwarded", "AwardName" );
               szAwardValue = sb_szAwardValue.toString( );}
                {StringBuilder sb_szAwardValue;
               if ( szAwardValue == null )
                  sb_szAwardValue = new StringBuilder( 32 );
               else
                  sb_szAwardValue = new StringBuilder( szAwardValue );
                              ZeidonStringConcat( sb_szAwardValue, 1, 0, zTAB, 1, 0, 255 );
               szAwardValue = sb_szAwardValue.toString( );}
                {StringBuilder sb_szAwardValue;
               if ( szAwardValue == null )
                  sb_szAwardValue = new StringBuilder( 32 );
               else
                  sb_szAwardValue = new StringBuilder( szAwardValue );
                              ZeidonStringConcat( sb_szAwardValue, 1, 0, szFallAmount, 1, 0, 255 );
               szAwardValue = sb_szAwardValue.toString( );}
                {StringBuilder sb_szAwardValue;
               if ( szAwardValue == null )
                  sb_szAwardValue = new StringBuilder( 32 );
               else
                  sb_szAwardValue = new StringBuilder( szAwardValue );
                              ZeidonStringConcat( sb_szAwardValue, 1, 0, zTAB, 1, 0, 255 );
               szAwardValue = sb_szAwardValue.toString( );}
                {StringBuilder sb_szAwardValue;
               if ( szAwardValue == null )
                  sb_szAwardValue = new StringBuilder( 32 );
               else
                  sb_szAwardValue = new StringBuilder( szAwardValue );
                              ZeidonStringConcat( sb_szAwardValue, 1, 0, szSpringAmount, 1, 0, 255 );
               szAwardValue = sb_szAwardValue.toString( );}
                {StringBuilder sb_szAwardValue;
               if ( szAwardValue == null )
                  sb_szAwardValue = new StringBuilder( 32 );
               else
                  sb_szAwardValue = new StringBuilder( szAwardValue );
                              ZeidonStringConcat( sb_szAwardValue, 1, 0, zTAB, 1, 0, 255 );
               szAwardValue = sb_szAwardValue.toString( );}
                {StringBuilder sb_szAwardValue;
               if ( szAwardValue == null )
                  sb_szAwardValue = new StringBuilder( 32 );
               else
                  sb_szAwardValue = new StringBuilder( szAwardValue );
                              ZeidonStringConcat( sb_szAwardValue, 1, 0, szAmount, 1, 0, 255 );
               szAwardValue = sb_szAwardValue.toString( );}
               //:szAwardValue3Term = qFinAidA.FinAidAwarded.AwardName + zTAB + szFallAmount + zTAB + szSpringAmount + zTAB + szSummerAmount + zTAB + szAmount 
               {StringBuilder sb_szAwardValue3Term;
               if ( szAwardValue3Term == null )
                  sb_szAwardValue3Term = new StringBuilder( 32 );
               else
                  sb_szAwardValue3Term = new StringBuilder( szAwardValue3Term );
                               GetStringFromAttribute( sb_szAwardValue3Term, qFinAidA, "FinAidAwarded", "AwardName" );
               szAwardValue3Term = sb_szAwardValue3Term.toString( );}
                {StringBuilder sb_szAwardValue3Term;
               if ( szAwardValue3Term == null )
                  sb_szAwardValue3Term = new StringBuilder( 32 );
               else
                  sb_szAwardValue3Term = new StringBuilder( szAwardValue3Term );
                              ZeidonStringConcat( sb_szAwardValue3Term, 1, 0, zTAB, 1, 0, 255 );
               szAwardValue3Term = sb_szAwardValue3Term.toString( );}
                {StringBuilder sb_szAwardValue3Term;
               if ( szAwardValue3Term == null )
                  sb_szAwardValue3Term = new StringBuilder( 32 );
               else
                  sb_szAwardValue3Term = new StringBuilder( szAwardValue3Term );
                              ZeidonStringConcat( sb_szAwardValue3Term, 1, 0, szFallAmount, 1, 0, 255 );
               szAwardValue3Term = sb_szAwardValue3Term.toString( );}
                {StringBuilder sb_szAwardValue3Term;
               if ( szAwardValue3Term == null )
                  sb_szAwardValue3Term = new StringBuilder( 32 );
               else
                  sb_szAwardValue3Term = new StringBuilder( szAwardValue3Term );
                              ZeidonStringConcat( sb_szAwardValue3Term, 1, 0, zTAB, 1, 0, 255 );
               szAwardValue3Term = sb_szAwardValue3Term.toString( );}
                {StringBuilder sb_szAwardValue3Term;
               if ( szAwardValue3Term == null )
                  sb_szAwardValue3Term = new StringBuilder( 32 );
               else
                  sb_szAwardValue3Term = new StringBuilder( szAwardValue3Term );
                              ZeidonStringConcat( sb_szAwardValue3Term, 1, 0, szSpringAmount, 1, 0, 255 );
               szAwardValue3Term = sb_szAwardValue3Term.toString( );}
                {StringBuilder sb_szAwardValue3Term;
               if ( szAwardValue3Term == null )
                  sb_szAwardValue3Term = new StringBuilder( 32 );
               else
                  sb_szAwardValue3Term = new StringBuilder( szAwardValue3Term );
                              ZeidonStringConcat( sb_szAwardValue3Term, 1, 0, zTAB, 1, 0, 255 );
               szAwardValue3Term = sb_szAwardValue3Term.toString( );}
                {StringBuilder sb_szAwardValue3Term;
               if ( szAwardValue3Term == null )
                  sb_szAwardValue3Term = new StringBuilder( 32 );
               else
                  sb_szAwardValue3Term = new StringBuilder( szAwardValue3Term );
                              ZeidonStringConcat( sb_szAwardValue3Term, 1, 0, szSummerAmount, 1, 0, 255 );
               szAwardValue3Term = sb_szAwardValue3Term.toString( );}
                {StringBuilder sb_szAwardValue3Term;
               if ( szAwardValue3Term == null )
                  sb_szAwardValue3Term = new StringBuilder( 32 );
               else
                  sb_szAwardValue3Term = new StringBuilder( szAwardValue3Term );
                              ZeidonStringConcat( sb_szAwardValue3Term, 1, 0, zTAB, 1, 0, 255 );
               szAwardValue3Term = sb_szAwardValue3Term.toString( );}
                {StringBuilder sb_szAwardValue3Term;
               if ( szAwardValue3Term == null )
                  sb_szAwardValue3Term = new StringBuilder( 32 );
               else
                  sb_szAwardValue3Term = new StringBuilder( szAwardValue3Term );
                              ZeidonStringConcat( sb_szAwardValue3Term, 1, 0, szAmount, 1, 0, 255 );
               szAwardValue3Term = sb_szAwardValue3Term.toString( );}
               //:szAwardValueTotal = qFinAidA.FinAidAwarded.AwardName + zTAB + szAmount 
               {StringBuilder sb_szAwardValueTotal;
               if ( szAwardValueTotal == null )
                  sb_szAwardValueTotal = new StringBuilder( 32 );
               else
                  sb_szAwardValueTotal = new StringBuilder( szAwardValueTotal );
                               GetStringFromAttribute( sb_szAwardValueTotal, qFinAidA, "FinAidAwarded", "AwardName" );
               szAwardValueTotal = sb_szAwardValueTotal.toString( );}
                {StringBuilder sb_szAwardValueTotal;
               if ( szAwardValueTotal == null )
                  sb_szAwardValueTotal = new StringBuilder( 32 );
               else
                  sb_szAwardValueTotal = new StringBuilder( szAwardValueTotal );
                              ZeidonStringConcat( sb_szAwardValueTotal, 1, 0, zTAB, 1, 0, 255 );
               szAwardValueTotal = sb_szAwardValueTotal.toString( );}
                {StringBuilder sb_szAwardValueTotal;
               if ( szAwardValueTotal == null )
                  sb_szAwardValueTotal = new StringBuilder( 32 );
               else
                  sb_szAwardValueTotal = new StringBuilder( szAwardValueTotal );
                              ZeidonStringConcat( sb_szAwardValueTotal, 1, 0, szAmount, 1, 0, 255 );
               szAwardValueTotal = sb_szAwardValueTotal.toString( );}

               //:/*IF qFinAidA.FinAidAwardAssigned.AwardStatus = "Y"
               //:szAcceptedDeclinedText = "Accepted"
               //:ELSE
               //:IF qFinAidA.FinAidAwardAssigned.AwardStatus = "N" 
               //:   szAcceptedDeclinedText = "         Declined"
               //:ELSE
               //:   szAcceptedDeclinedText = "_______  _______"
               //:END
               //:END*/
               //:IF qFinAidA.FinAidSource.Provider = "G"
               if ( CompareAttributeToString( qFinAidA, "FinAidSource", "Provider", "G" ) == 0 )
               { 
                  //:CREATE ENTITY qFinAidA.FederalAidGroup 
                  RESULT = CreateEntity( qFinAidA, "FederalAidGroup", zPOS_AFTER );
                  //:SetMatchingAttributesByName( qFinAidA, "FederalAidGroup", qFinAidA, "FinAidAwarded", zSET_ALL )
                  SetMatchingAttributesByName( qFinAidA, "FederalAidGroup", qFinAidA, "FinAidAwarded", zSET_ALL );
                  //:qFinAidA.FederalAidGroup.AwardValue = szAwardValue + zTAB + szAcceptedDeclinedText
                   {StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                    ZeidonStringCopy( sb_szTempString_0, 1, 0, szAwardValue, 1, 0, 255 );
                  szTempString_0 = sb_szTempString_0.toString( );}
                   {StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                    ZeidonStringConcat( sb_szTempString_0, 1, 0, zTAB, 1, 0, 255 );
                  szTempString_0 = sb_szTempString_0.toString( );}
                   {StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                    ZeidonStringConcat( sb_szTempString_0, 1, 0, szAcceptedDeclinedText, 1, 0, 255 );
                  szTempString_0 = sb_szTempString_0.toString( );}
                  SetAttributeFromString( qFinAidA, "FederalAidGroup", "AwardValue", szTempString_0 );
                  //:qFinAidA.AwardLetters.FederalAidGroupTotal    = qFinAidA.AwardLetters.FederalAidGroupTotal  + qFinAidA.FinAidAwarded.Amount 
                  {MutableDouble md_dTempDecimal_12 = new MutableDouble( dTempDecimal_12 );
                                     GetDecimalFromAttribute( md_dTempDecimal_12, qFinAidA, "AwardLetters", "FederalAidGroupTotal" );
                  dTempDecimal_12 = md_dTempDecimal_12.doubleValue( );}
                  {MutableDouble md_dTempDecimal_13 = new MutableDouble( dTempDecimal_13 );
                                     GetDecimalFromAttribute( md_dTempDecimal_13, qFinAidA, "FinAidAwarded", "Amount" );
                  dTempDecimal_13 = md_dTempDecimal_13.doubleValue( );}
                  dTempDecimal_14 = dTempDecimal_12 + dTempDecimal_13;
                  SetAttributeFromDecimal( qFinAidA, "AwardLetters", "FederalAidGroupTotal", dTempDecimal_14 );
                  //:qFinAidA.AwardLetters.FederalAidSpringTotal   = qFinAidA.AwardLetters.FederalAidSpringTotal + qFinAidA.FederalAidGroup.SpringAmount 
                  {MutableDouble md_dTempDecimal_15 = new MutableDouble( dTempDecimal_15 );
                                     GetDecimalFromAttribute( md_dTempDecimal_15, qFinAidA, "AwardLetters", "FederalAidSpringTotal" );
                  dTempDecimal_15 = md_dTempDecimal_15.doubleValue( );}
                  {MutableDouble md_dTempDecimal_16 = new MutableDouble( dTempDecimal_16 );
                                     GetDecimalFromAttribute( md_dTempDecimal_16, qFinAidA, "FederalAidGroup", "SpringAmount" );
                  dTempDecimal_16 = md_dTempDecimal_16.doubleValue( );}
                  dTempDecimal_17 = dTempDecimal_15 + dTempDecimal_16;
                  SetAttributeFromDecimal( qFinAidA, "AwardLetters", "FederalAidSpringTotal", dTempDecimal_17 );
                  //:qFinAidA.AwardLetters.FederalAidFallTotal     = qFinAidA.AwardLetters.FederalAidFallTotal   + qFinAidA.FederalAidGroup.FallAmount 
                  {MutableDouble md_dTempDecimal_18 = new MutableDouble( dTempDecimal_18 );
                                     GetDecimalFromAttribute( md_dTempDecimal_18, qFinAidA, "AwardLetters", "FederalAidFallTotal" );
                  dTempDecimal_18 = md_dTempDecimal_18.doubleValue( );}
                  {MutableDouble md_dTempDecimal_19 = new MutableDouble( dTempDecimal_19 );
                                     GetDecimalFromAttribute( md_dTempDecimal_19, qFinAidA, "FederalAidGroup", "FallAmount" );
                  dTempDecimal_19 = md_dTempDecimal_19.doubleValue( );}
                  dTempDecimal_20 = dTempDecimal_18 + dTempDecimal_19;
                  SetAttributeFromDecimal( qFinAidA, "AwardLetters", "FederalAidFallTotal", dTempDecimal_20 );
                  //:qFinAidA.AwardLetters.FederalAidSummerTotal   = qFinAidA.AwardLetters.FederalAidSummerTotal + qFinAidA.FederalAidGroup.SummerAmount 
                  {MutableDouble md_dTempDecimal_21 = new MutableDouble( dTempDecimal_21 );
                                     GetDecimalFromAttribute( md_dTempDecimal_21, qFinAidA, "AwardLetters", "FederalAidSummerTotal" );
                  dTempDecimal_21 = md_dTempDecimal_21.doubleValue( );}
                  {MutableDouble md_dTempDecimal_22 = new MutableDouble( dTempDecimal_22 );
                                     GetDecimalFromAttribute( md_dTempDecimal_22, qFinAidA, "FederalAidGroup", "SummerAmount" );
                  dTempDecimal_22 = md_dTempDecimal_22.doubleValue( );}
                  dTempDecimal_23 = dTempDecimal_21 + dTempDecimal_22;
                  SetAttributeFromDecimal( qFinAidA, "AwardLetters", "FederalAidSummerTotal", dTempDecimal_23 );
                  //:qFinAidA.FederalAidGroup.AcceptedDeclinedText = szAcceptedDeclinedText
                  SetAttributeFromString( qFinAidA, "FederalAidGroup", "AcceptedDeclinedText", szAcceptedDeclinedText );
                  //://fnFormatTextTabData( qFinAidA, "FederalAidGroup" )

                  //:// Also create GovernmentAuditAward, if the status is Accepted or Awarded.
                  //:IF qFinAidA.FinAidAwardAssigned.AwardStatus = "A" OR qFinAidA.FinAidAwardAssigned.AwardStatus = "Y"
                  if ( CompareAttributeToString( qFinAidA, "FinAidAwardAssigned", "AwardStatus", "A" ) == 0 || CompareAttributeToString( qFinAidA, "FinAidAwardAssigned", "AwardStatus", "Y" ) == 0 )
                  { 
                     //:CREATE ENTITY qFinAidA.GovernmentAuditAwarded
                     RESULT = CreateEntity( qFinAidA, "GovernmentAuditAwarded", zPOS_AFTER );
                     //:qFinAidA.GovernmentAuditAwarded.AwardName = qFinAidA.FinAidAwarded.AwardName 
                     SetAttributeFromAttribute( qFinAidA, "GovernmentAuditAwarded", "AwardName", qFinAidA, "FinAidAwarded", "AwardName" );
                     //:qFinAidA.GovernmentAuditAwarded.Amount    = qFinAidA.FinAidAwarded.Amount 
                     SetAttributeFromAttribute( qFinAidA, "GovernmentAuditAwarded", "Amount", qFinAidA, "FinAidAwarded", "Amount" );
                  } 

                  //:END
               } 

               //:END
               //:IF qFinAidA.FinAidSource.Provider = "I" 
               if ( CompareAttributeToString( qFinAidA, "FinAidSource", "Provider", "I" ) == 0 )
               { 
                  //:CREATE ENTITY qFinAidA.InstitutionalAidGroup 
                  RESULT = CreateEntity( qFinAidA, "InstitutionalAidGroup", zPOS_AFTER );
                  //:SetMatchingAttributesByName( qFinAidA, "InstitutionalAidGroup", qFinAidA, "FinAidAwarded", zSET_ALL ) 
                  SetMatchingAttributesByName( qFinAidA, "InstitutionalAidGroup", qFinAidA, "FinAidAwarded", zSET_ALL );
                  //:qFinAidA.InstitutionalAidGroup.AwardValue = szAwardValue + zTAB + szAcceptedDeclinedText
                   {StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                    ZeidonStringCopy( sb_szTempString_2, 1, 0, szAwardValue, 1, 0, 255 );
                  szTempString_2 = sb_szTempString_2.toString( );}
                   {StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                    ZeidonStringConcat( sb_szTempString_2, 1, 0, zTAB, 1, 0, 255 );
                  szTempString_2 = sb_szTempString_2.toString( );}
                   {StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                    ZeidonStringConcat( sb_szTempString_2, 1, 0, szAcceptedDeclinedText, 1, 0, 255 );
                  szTempString_2 = sb_szTempString_2.toString( );}
                  SetAttributeFromString( qFinAidA, "InstitutionalAidGroup", "AwardValue", szTempString_2 );
                  //:qFinAidA.AwardLetters.InstitutionalAidGroupTotal    = qFinAidA.AwardLetters.InstitutionalAidGroupTotal  + qFinAidA.FinAidAwarded.Amount 
                  {MutableDouble md_dTempDecimal_24 = new MutableDouble( dTempDecimal_24 );
                                     GetDecimalFromAttribute( md_dTempDecimal_24, qFinAidA, "AwardLetters", "InstitutionalAidGroupTotal" );
                  dTempDecimal_24 = md_dTempDecimal_24.doubleValue( );}
                  {MutableDouble md_dTempDecimal_25 = new MutableDouble( dTempDecimal_25 );
                                     GetDecimalFromAttribute( md_dTempDecimal_25, qFinAidA, "FinAidAwarded", "Amount" );
                  dTempDecimal_25 = md_dTempDecimal_25.doubleValue( );}
                  dTempDecimal_26 = dTempDecimal_24 + dTempDecimal_25;
                  SetAttributeFromDecimal( qFinAidA, "AwardLetters", "InstitutionalAidGroupTotal", dTempDecimal_26 );
                  //:qFinAidA.AwardLetters.InstitutionalAidSpringTotal   = qFinAidA.AwardLetters.InstitutionalAidSpringTotal + qFinAidA.InstitutionalAidGroup.SpringAmount 
                  {MutableDouble md_dTempDecimal_27 = new MutableDouble( dTempDecimal_27 );
                                     GetDecimalFromAttribute( md_dTempDecimal_27, qFinAidA, "AwardLetters", "InstitutionalAidSpringTotal" );
                  dTempDecimal_27 = md_dTempDecimal_27.doubleValue( );}
                  {MutableDouble md_dTempDecimal_28 = new MutableDouble( dTempDecimal_28 );
                                     GetDecimalFromAttribute( md_dTempDecimal_28, qFinAidA, "InstitutionalAidGroup", "SpringAmount" );
                  dTempDecimal_28 = md_dTempDecimal_28.doubleValue( );}
                  dTempDecimal_29 = dTempDecimal_27 + dTempDecimal_28;
                  SetAttributeFromDecimal( qFinAidA, "AwardLetters", "InstitutionalAidSpringTotal", dTempDecimal_29 );
                  //:qFinAidA.AwardLetters.InstitutionalAidFallTotal     = qFinAidA.AwardLetters.InstitutionalAidFallTotal   + qFinAidA.InstitutionalAidGroup.FallAmount 
                  {MutableDouble md_dTempDecimal_30 = new MutableDouble( dTempDecimal_30 );
                                     GetDecimalFromAttribute( md_dTempDecimal_30, qFinAidA, "AwardLetters", "InstitutionalAidFallTotal" );
                  dTempDecimal_30 = md_dTempDecimal_30.doubleValue( );}
                  {MutableDouble md_dTempDecimal_31 = new MutableDouble( dTempDecimal_31 );
                                     GetDecimalFromAttribute( md_dTempDecimal_31, qFinAidA, "InstitutionalAidGroup", "FallAmount" );
                  dTempDecimal_31 = md_dTempDecimal_31.doubleValue( );}
                  dTempDecimal_32 = dTempDecimal_30 + dTempDecimal_31;
                  SetAttributeFromDecimal( qFinAidA, "AwardLetters", "InstitutionalAidFallTotal", dTempDecimal_32 );
                  //:qFinAidA.AwardLetters.InstitutionalAidSummerTotal   = qFinAidA.AwardLetters.InstitutionalAidSummerTotal + qFinAidA.InstitutionalAidGroup.SummerAmount
                  {MutableDouble md_dTempDecimal_33 = new MutableDouble( dTempDecimal_33 );
                                     GetDecimalFromAttribute( md_dTempDecimal_33, qFinAidA, "AwardLetters", "InstitutionalAidSummerTotal" );
                  dTempDecimal_33 = md_dTempDecimal_33.doubleValue( );}
                  {MutableDouble md_dTempDecimal_34 = new MutableDouble( dTempDecimal_34 );
                                     GetDecimalFromAttribute( md_dTempDecimal_34, qFinAidA, "InstitutionalAidGroup", "SummerAmount" );
                  dTempDecimal_34 = md_dTempDecimal_34.doubleValue( );}
                  dTempDecimal_35 = dTempDecimal_33 + dTempDecimal_34;
                  SetAttributeFromDecimal( qFinAidA, "AwardLetters", "InstitutionalAidSummerTotal", dTempDecimal_35 );
                  //:qFinAidA.InstitutionalAidGroup.AcceptedDeclinedText = szAcceptedDeclinedText
                  SetAttributeFromString( qFinAidA, "InstitutionalAidGroup", "AcceptedDeclinedText", szAcceptedDeclinedText );
               } 

               //://fnFormatTextTabData( qFinAidA, "InstitutionalAidGroup" )
               //:END
               //:IF qFinAidA.FinAidSource.Provider = "O" 
               if ( CompareAttributeToString( qFinAidA, "FinAidSource", "Provider", "O" ) == 0 )
               { 
                  //:CREATE ENTITY qFinAidA.OtherAidGroup 
                  RESULT = CreateEntity( qFinAidA, "OtherAidGroup", zPOS_AFTER );
                  //:SetMatchingAttributesByName( qFinAidA, "OtherAidGroup", qFinAidA, "FinAidAwarded", zSET_ALL ) 
                  SetMatchingAttributesByName( qFinAidA, "OtherAidGroup", qFinAidA, "FinAidAwarded", zSET_ALL );
                  //:qFinAidA.OtherAidGroup.AwardValue           = szAwardValue + zTAB + szAcceptedDeclinedText
                   {StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                    ZeidonStringCopy( sb_szTempString_3, 1, 0, szAwardValue, 1, 0, 255 );
                  szTempString_3 = sb_szTempString_3.toString( );}
                   {StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                    ZeidonStringConcat( sb_szTempString_3, 1, 0, zTAB, 1, 0, 255 );
                  szTempString_3 = sb_szTempString_3.toString( );}
                   {StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                    ZeidonStringConcat( sb_szTempString_3, 1, 0, szAcceptedDeclinedText, 1, 0, 255 );
                  szTempString_3 = sb_szTempString_3.toString( );}
                  SetAttributeFromString( qFinAidA, "OtherAidGroup", "AwardValue", szTempString_3 );
                  //:qFinAidA.AwardLetters.OtherAidGroupTotal    = qFinAidA.AwardLetters.OtherAidGroupTotal  + qFinAidA.FinAidAwarded.Amount 
                  {MutableDouble md_dTempDecimal_36 = new MutableDouble( dTempDecimal_36 );
                                     GetDecimalFromAttribute( md_dTempDecimal_36, qFinAidA, "AwardLetters", "OtherAidGroupTotal" );
                  dTempDecimal_36 = md_dTempDecimal_36.doubleValue( );}
                  {MutableDouble md_dTempDecimal_37 = new MutableDouble( dTempDecimal_37 );
                                     GetDecimalFromAttribute( md_dTempDecimal_37, qFinAidA, "FinAidAwarded", "Amount" );
                  dTempDecimal_37 = md_dTempDecimal_37.doubleValue( );}
                  dTempDecimal_38 = dTempDecimal_36 + dTempDecimal_37;
                  SetAttributeFromDecimal( qFinAidA, "AwardLetters", "OtherAidGroupTotal", dTempDecimal_38 );
                  //:qFinAidA.AwardLetters.OtherAidSpringTotal   = qFinAidA.AwardLetters.OtherAidSpringTotal + qFinAidA.OtherAidGroup.SpringAmount 
                  {MutableDouble md_dTempDecimal_39 = new MutableDouble( dTempDecimal_39 );
                                     GetDecimalFromAttribute( md_dTempDecimal_39, qFinAidA, "AwardLetters", "OtherAidSpringTotal" );
                  dTempDecimal_39 = md_dTempDecimal_39.doubleValue( );}
                  {MutableDouble md_dTempDecimal_40 = new MutableDouble( dTempDecimal_40 );
                                     GetDecimalFromAttribute( md_dTempDecimal_40, qFinAidA, "OtherAidGroup", "SpringAmount" );
                  dTempDecimal_40 = md_dTempDecimal_40.doubleValue( );}
                  dTempDecimal_41 = dTempDecimal_39 + dTempDecimal_40;
                  SetAttributeFromDecimal( qFinAidA, "AwardLetters", "OtherAidSpringTotal", dTempDecimal_41 );
                  //:qFinAidA.AwardLetters.OtherAidFallTotal     = qFinAidA.AwardLetters.OtherAidFallTotal   + qFinAidA.OtherAidGroup.FallAmount 
                  {MutableDouble md_dTempDecimal_42 = new MutableDouble( dTempDecimal_42 );
                                     GetDecimalFromAttribute( md_dTempDecimal_42, qFinAidA, "AwardLetters", "OtherAidFallTotal" );
                  dTempDecimal_42 = md_dTempDecimal_42.doubleValue( );}
                  {MutableDouble md_dTempDecimal_43 = new MutableDouble( dTempDecimal_43 );
                                     GetDecimalFromAttribute( md_dTempDecimal_43, qFinAidA, "OtherAidGroup", "FallAmount" );
                  dTempDecimal_43 = md_dTempDecimal_43.doubleValue( );}
                  dTempDecimal_44 = dTempDecimal_42 + dTempDecimal_43;
                  SetAttributeFromDecimal( qFinAidA, "AwardLetters", "OtherAidFallTotal", dTempDecimal_44 );
                  //:qFinAidA.AwardLetters.OtherAidSummerTotal   = qFinAidA.AwardLetters.OtherAidSummerTotal + qFinAidA.OtherAidGroup.SummerAmount 
                  {MutableDouble md_dTempDecimal_45 = new MutableDouble( dTempDecimal_45 );
                                     GetDecimalFromAttribute( md_dTempDecimal_45, qFinAidA, "AwardLetters", "OtherAidSummerTotal" );
                  dTempDecimal_45 = md_dTempDecimal_45.doubleValue( );}
                  {MutableDouble md_dTempDecimal_46 = new MutableDouble( dTempDecimal_46 );
                                     GetDecimalFromAttribute( md_dTempDecimal_46, qFinAidA, "OtherAidGroup", "SummerAmount" );
                  dTempDecimal_46 = md_dTempDecimal_46.doubleValue( );}
                  dTempDecimal_47 = dTempDecimal_45 + dTempDecimal_46;
                  SetAttributeFromDecimal( qFinAidA, "AwardLetters", "OtherAidSummerTotal", dTempDecimal_47 );
                  //:qFinAidA.OtherAidGroup.AcceptedDeclinedText = szAcceptedDeclinedText
                  SetAttributeFromString( qFinAidA, "OtherAidGroup", "AcceptedDeclinedText", szAcceptedDeclinedText );
               } 

               //://fnFormatTextTabData( qFinAidA, "OtherAidGroup" )
               //:END
               //:qFinAidA.AwardLetters.FinAidFullYearTotal = qFinAidA.AwardLetters.FinAidFullYearTotal + qFinAidA.FinAidAwarded.Amount
               {MutableDouble md_dTempDecimal_48 = new MutableDouble( dTempDecimal_48 );
                               GetDecimalFromAttribute( md_dTempDecimal_48, qFinAidA, "AwardLetters", "FinAidFullYearTotal" );
               dTempDecimal_48 = md_dTempDecimal_48.doubleValue( );}
               {MutableDouble md_dTempDecimal_49 = new MutableDouble( dTempDecimal_49 );
                               GetDecimalFromAttribute( md_dTempDecimal_49, qFinAidA, "FinAidAwarded", "Amount" );
               dTempDecimal_49 = md_dTempDecimal_49.doubleValue( );}
               dTempDecimal_50 = dTempDecimal_48 + dTempDecimal_49;
               SetAttributeFromDecimal( qFinAidA, "AwardLetters", "FinAidFullYearTotal", dTempDecimal_50 );
               //:qFinAidA.AwardLetters.FinAidSpringTotal   = qFinAidA.AwardLetters.FinAidSpringTotal   + qFinAidA.FinAidAwarded.SpringAmount
               {MutableDouble md_dTempDecimal_51 = new MutableDouble( dTempDecimal_51 );
                               GetDecimalFromAttribute( md_dTempDecimal_51, qFinAidA, "AwardLetters", "FinAidSpringTotal" );
               dTempDecimal_51 = md_dTempDecimal_51.doubleValue( );}
               {MutableDouble md_dTempDecimal_52 = new MutableDouble( dTempDecimal_52 );
                               GetDecimalFromAttribute( md_dTempDecimal_52, qFinAidA, "FinAidAwarded", "SpringAmount" );
               dTempDecimal_52 = md_dTempDecimal_52.doubleValue( );}
               dTempDecimal_53 = dTempDecimal_51 + dTempDecimal_52;
               SetAttributeFromDecimal( qFinAidA, "AwardLetters", "FinAidSpringTotal", dTempDecimal_53 );
               //:qFinAidA.AwardLetters.FinAidFallTotal     = qFinAidA.AwardLetters.FinAidFallTotal     + qFinAidA.FinAidAwarded.FallAmount
               {MutableDouble md_dTempDecimal_54 = new MutableDouble( dTempDecimal_54 );
                               GetDecimalFromAttribute( md_dTempDecimal_54, qFinAidA, "AwardLetters", "FinAidFallTotal" );
               dTempDecimal_54 = md_dTempDecimal_54.doubleValue( );}
               {MutableDouble md_dTempDecimal_55 = new MutableDouble( dTempDecimal_55 );
                               GetDecimalFromAttribute( md_dTempDecimal_55, qFinAidA, "FinAidAwarded", "FallAmount" );
               dTempDecimal_55 = md_dTempDecimal_55.doubleValue( );}
               dTempDecimal_56 = dTempDecimal_54 + dTempDecimal_55;
               SetAttributeFromDecimal( qFinAidA, "AwardLetters", "FinAidFallTotal", dTempDecimal_56 );
               //:qFinAidA.AwardLetters.FinAidFallTotal     = qFinAidA.AwardLetters.FinAidSummerTotal   + qFinAidA.FinAidAwarded.SummerAmount
               {MutableDouble md_dTempDecimal_57 = new MutableDouble( dTempDecimal_57 );
                               GetDecimalFromAttribute( md_dTempDecimal_57, qFinAidA, "AwardLetters", "FinAidSummerTotal" );
               dTempDecimal_57 = md_dTempDecimal_57.doubleValue( );}
               {MutableDouble md_dTempDecimal_58 = new MutableDouble( dTempDecimal_58 );
                               GetDecimalFromAttribute( md_dTempDecimal_58, qFinAidA, "FinAidAwarded", "SummerAmount" );
               dTempDecimal_58 = md_dTempDecimal_58.doubleValue( );}
               dTempDecimal_59 = dTempDecimal_57 + dTempDecimal_58;
               SetAttributeFromDecimal( qFinAidA, "AwardLetters", "FinAidFallTotal", dTempDecimal_59 );

               //:qFinAidA.FinAidAwarded.AwardValue      = szAwardValue
               SetAttributeFromString( qFinAidA, "FinAidAwarded", "AwardValue", szAwardValue );
               //:qFinAidA.FinAidAwarded.AwardValueTotal = szAwardValueTotal
               SetAttributeFromString( qFinAidA, "FinAidAwarded", "AwardValueTotal", szAwardValueTotal );
               //:qFinAidA.FinAidAwarded.AwardValue3Term = szAwardValue3Term
               SetAttributeFromString( qFinAidA, "FinAidAwarded", "AwardValue3Term", szAwardValue3Term );
            } 

            //:END
         } 

      } 

      RESULT = SetCursorNextEntity( qFinAidA, "FinAidAwardAssigned", "" );
      //:END
   } 

   //:END

   //:// Create Swapped Entries. We do this after the creation of other award entries because we want the footnotes numbers to not be intermixed
   //:// with the footnote numbers of Institutional Aid Group entries.
   //:FOR EACH qFinAidA.FinAidAwardAssigned WHERE qFinAidA.FinAidAwardAssigned.AwardStatus != "I"
   //:                                        AND qFinAidA.FinAidAwardAssigned.AwardStatus != "N"
   RESULT = SetCursorFirstEntity( qFinAidA, "FinAidAwardAssigned", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( qFinAidA, "FinAidAwardAssigned", "AwardStatus", "I" ) != 0 && CompareAttributeToString( qFinAidA, "FinAidAwardAssigned", "AwardStatus", "N" ) != 0 )
      { 

         //:FOR EACH qFinAidA.FinAidAwardDisbursement
         RESULT = SetCursorFirstEntity( qFinAidA, "FinAidAwardDisbursement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:// Don't include entries created by swapping. (SwappedFromDisbursement doesn't exist.)
            //:IF qFinAidA.SwappedFromDisbursement EXISTS
            lTempInteger_5 = CheckExistenceOfEntity( qFinAidA, "SwappedFromDisbursement" );
            if ( lTempInteger_5 == 0 )
            { 
               //:SET CURSOR FIRST qFinAidA.SwappedAwards WHERE qFinAidA.SwappedAwards.AwardName = qFinAidA.FinAidSource.Name 
               {StringBuilder sb_szTempString_4;
               if ( szTempString_4 == null )
                  sb_szTempString_4 = new StringBuilder( 32 );
               else
                  sb_szTempString_4 = new StringBuilder( szTempString_4 );
                               GetStringFromAttribute( sb_szTempString_4, qFinAidA, "FinAidSource", "Name" );
               szTempString_4 = sb_szTempString_4.toString( );}
               RESULT = SetCursorFirstEntityByString( qFinAidA, "SwappedAwards", "AwardName", szTempString_4, "" );
               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:CREATE ENTITY qFinAidA.SwappedAwards 
                  RESULT = CreateEntity( qFinAidA, "SwappedAwards", zPOS_AFTER );
                  //:qFinAidA.SwappedAwards.AwardName      = qFinAidA.FinAidSource.Name
                  SetAttributeFromAttribute( qFinAidA, "SwappedAwards", "AwardName", qFinAidA, "FinAidSource", "Name" );
                  //:qFinAidA.SwappedAwards.SourceFootnote = qFinAidA.FinAidSource.SourceFootnote
                  SetAttributeFromAttribute( qFinAidA, "SwappedAwards", "SourceFootnote", qFinAidA, "FinAidSource", "SourceFootnote" );

                  //:// Process Footnote pointer and line.
                  //:nFNNumber = nFNNumber + 1
                  nFNNumber = nFNNumber + 1;
                  //:zIntegerToString( szFootnoteNumber, 10, nFNNumber )
                  {StringBuilder sb_szFootnoteNumber;
                  if ( szFootnoteNumber == null )
                     sb_szFootnoteNumber = new StringBuilder( 32 );
                  else
                     sb_szFootnoteNumber = new StringBuilder( szFootnoteNumber );
                                     zIntegerToString( sb_szFootnoteNumber, 10, nFNNumber );
                  szFootnoteNumber = sb_szFootnoteNumber.toString( );}
                  //:qFinAidA.SwappedAwards.DisplayLine = qFinAidA.FinAidSource.Name + "* " + szFootnoteNumber
                  {StringBuilder sb_szTempString_4;
                  if ( szTempString_4 == null )
                     sb_szTempString_4 = new StringBuilder( 32 );
                  else
                     sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                     GetStringFromAttribute( sb_szTempString_4, qFinAidA, "FinAidSource", "Name" );
                  szTempString_4 = sb_szTempString_4.toString( );}
                   {StringBuilder sb_szTempString_4;
                  if ( szTempString_4 == null )
                     sb_szTempString_4 = new StringBuilder( 32 );
                  else
                     sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                    ZeidonStringConcat( sb_szTempString_4, 1, 0, "* ", 1, 0, 255 );
                  szTempString_4 = sb_szTempString_4.toString( );}
                   {StringBuilder sb_szTempString_4;
                  if ( szTempString_4 == null )
                     sb_szTempString_4 = new StringBuilder( 32 );
                  else
                     sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                    ZeidonStringConcat( sb_szTempString_4, 1, 0, szFootnoteNumber, 1, 0, 255 );
                  szTempString_4 = sb_szTempString_4.toString( );}
                  SetAttributeFromString( qFinAidA, "SwappedAwards", "DisplayLine", szTempString_4 );
                  //:CREATE ENTITY qFinAidA.Footnote
                  RESULT = CreateEntity( qFinAidA, "Footnote", zPOS_AFTER );
                  //:qFinAidA.Footnote.SourceFootnote = szFootnoteNumber + ": " + qFinAidA.FinAidSource.SourceFootnote
                   {StringBuilder sb_szTempString_5;
                  if ( szTempString_5 == null )
                     sb_szTempString_5 = new StringBuilder( 32 );
                  else
                     sb_szTempString_5 = new StringBuilder( szTempString_5 );
                                    ZeidonStringCopy( sb_szTempString_5, 1, 0, szFootnoteNumber, 1, 0, 10001 );
                  szTempString_5 = sb_szTempString_5.toString( );}
                   {StringBuilder sb_szTempString_5;
                  if ( szTempString_5 == null )
                     sb_szTempString_5 = new StringBuilder( 32 );
                  else
                     sb_szTempString_5 = new StringBuilder( szTempString_5 );
                                    ZeidonStringConcat( sb_szTempString_5, 1, 0, ": ", 1, 0, 10001 );
                  szTempString_5 = sb_szTempString_5.toString( );}
                  {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                  StringBuilder sb_szTempString_6;
                  if ( szTempString_6 == null )
                     sb_szTempString_6 = new StringBuilder( 32 );
                  else
                     sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                     GetVariableFromAttribute( sb_szTempString_6, mi_lTempInteger_6, 'S', 10001, qFinAidA, "FinAidSource", "SourceFootnote", "", 0 );
                  lTempInteger_6 = mi_lTempInteger_6.intValue( );
                  szTempString_6 = sb_szTempString_6.toString( );}
                   {StringBuilder sb_szTempString_5;
                  if ( szTempString_5 == null )
                     sb_szTempString_5 = new StringBuilder( 32 );
                  else
                     sb_szTempString_5 = new StringBuilder( szTempString_5 );
                                    ZeidonStringConcat( sb_szTempString_5, 1, 0, szTempString_6, 1, 0, 10001 );
                  szTempString_5 = sb_szTempString_5.toString( );}
                  SetAttributeFromString( qFinAidA, "Footnote", "SourceFootnote", szTempString_5 );
                  //:qFinAidA.Footnote.AwardName = qFinAidA.FinAidAwarded.AwardName  
                  SetAttributeFromAttribute( qFinAidA, "Footnote", "AwardName", qFinAidA, "FinAidAwarded", "AwardName" );
               } 

               //:END
            } 

            RESULT = SetCursorNextEntity( qFinAidA, "FinAidAwardDisbursement", "" );
            //:END
         } 

      } 

      RESULT = SetCursorNextEntity( qFinAidA, "FinAidAwardAssigned", "" );
      //:END
   } 

   //:END


   //:FOR EACH qFinAidA.FinAidCOAItemAssigned 
   RESULT = SetCursorFirstEntity( qFinAidA, "FinAidCOAItemAssigned", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szCOAString = ""
       {StringBuilder sb_szCOAString;
      if ( szCOAString == null )
         sb_szCOAString = new StringBuilder( 32 );
      else
         sb_szCOAString = new StringBuilder( szCOAString );
            ZeidonStringCopy( sb_szCOAString, 1, 0, "", 1, 0, 255 );
      szCOAString = sb_szCOAString.toString( );}
      //:szCOAName   = qFinAidA.FinAidCOAItem.Description 
      {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
      StringBuilder sb_szCOAName;
      if ( szCOAName == null )
         sb_szCOAName = new StringBuilder( 32 );
      else
         sb_szCOAName = new StringBuilder( szCOAName );
             GetVariableFromAttribute( sb_szCOAName, mi_lTempInteger_7, 'S', 31, qFinAidA, "FinAidCOAItem", "Description", "", 0 );
      lTempInteger_7 = mi_lTempInteger_7.intValue( );
      szCOAName = sb_szCOAName.toString( );}
      //:nLen = zstrlen(szCOAName)
      nLen = zstrlen( szCOAName );
      //:IF nLen < 6 
      if ( nLen < 6 )
      { 
         //:szCOAName = szCOAName + zTAB + zTAB + zTAB + zTAB + zTAB
          {StringBuilder sb_szCOAName;
         if ( szCOAName == null )
            sb_szCOAName = new StringBuilder( 32 );
         else
            sb_szCOAName = new StringBuilder( szCOAName );
                  ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
         szCOAName = sb_szCOAName.toString( );}
          {StringBuilder sb_szCOAName;
         if ( szCOAName == null )
            sb_szCOAName = new StringBuilder( 32 );
         else
            sb_szCOAName = new StringBuilder( szCOAName );
                  ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
         szCOAName = sb_szCOAName.toString( );}
          {StringBuilder sb_szCOAName;
         if ( szCOAName == null )
            sb_szCOAName = new StringBuilder( 32 );
         else
            sb_szCOAName = new StringBuilder( szCOAName );
                  ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
         szCOAName = sb_szCOAName.toString( );}
          {StringBuilder sb_szCOAName;
         if ( szCOAName == null )
            sb_szCOAName = new StringBuilder( 32 );
         else
            sb_szCOAName = new StringBuilder( szCOAName );
                  ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
         szCOAName = sb_szCOAName.toString( );}
          {StringBuilder sb_szCOAName;
         if ( szCOAName == null )
            sb_szCOAName = new StringBuilder( 32 );
         else
            sb_szCOAName = new StringBuilder( szCOAName );
                  ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
         szCOAName = sb_szCOAName.toString( );}
         //:ELSE 
      } 
      else
      { 
         //:IF nLen < 12
         if ( nLen < 12 )
         { 
            //:szCOAName = szCOAName + zTAB + zTAB + zTAB + zTAB
             {StringBuilder sb_szCOAName;
            if ( szCOAName == null )
               sb_szCOAName = new StringBuilder( 32 );
            else
               sb_szCOAName = new StringBuilder( szCOAName );
                        ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
            szCOAName = sb_szCOAName.toString( );}
             {StringBuilder sb_szCOAName;
            if ( szCOAName == null )
               sb_szCOAName = new StringBuilder( 32 );
            else
               sb_szCOAName = new StringBuilder( szCOAName );
                        ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
            szCOAName = sb_szCOAName.toString( );}
             {StringBuilder sb_szCOAName;
            if ( szCOAName == null )
               sb_szCOAName = new StringBuilder( 32 );
            else
               sb_szCOAName = new StringBuilder( szCOAName );
                        ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
            szCOAName = sb_szCOAName.toString( );}
             {StringBuilder sb_szCOAName;
            if ( szCOAName == null )
               sb_szCOAName = new StringBuilder( 32 );
            else
               sb_szCOAName = new StringBuilder( szCOAName );
                        ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
            szCOAName = sb_szCOAName.toString( );}
            //:ELSE 
         } 
         else
         { 
            //:IF nLen < 18 
            if ( nLen < 18 )
            { 
               //:szCOAName = szCOAName + zTAB + zTAB + zTAB
                {StringBuilder sb_szCOAName;
               if ( szCOAName == null )
                  sb_szCOAName = new StringBuilder( 32 );
               else
                  sb_szCOAName = new StringBuilder( szCOAName );
                              ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
               szCOAName = sb_szCOAName.toString( );}
                {StringBuilder sb_szCOAName;
               if ( szCOAName == null )
                  sb_szCOAName = new StringBuilder( 32 );
               else
                  sb_szCOAName = new StringBuilder( szCOAName );
                              ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
               szCOAName = sb_szCOAName.toString( );}
                {StringBuilder sb_szCOAName;
               if ( szCOAName == null )
                  sb_szCOAName = new StringBuilder( 32 );
               else
                  sb_szCOAName = new StringBuilder( szCOAName );
                              ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
               szCOAName = sb_szCOAName.toString( );}
               //:ELSE 
            } 
            else
            { 
               //:IF nLen < 24
               if ( nLen < 24 )
               { 
                  //:szCOAName = szCOAName + zTAB + zTAB
                   {StringBuilder sb_szCOAName;
                  if ( szCOAName == null )
                     sb_szCOAName = new StringBuilder( 32 );
                  else
                     sb_szCOAName = new StringBuilder( szCOAName );
                                    ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
                  szCOAName = sb_szCOAName.toString( );}
                   {StringBuilder sb_szCOAName;
                  if ( szCOAName == null )
                     sb_szCOAName = new StringBuilder( 32 );
                  else
                     sb_szCOAName = new StringBuilder( szCOAName );
                                    ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
                  szCOAName = sb_szCOAName.toString( );}
                  //:ELSE 
               } 
               else
               { 
                  //:szCOAName = szCOAName + zTAB
                   {StringBuilder sb_szCOAName;
                  if ( szCOAName == null )
                     sb_szCOAName = new StringBuilder( 32 );
                  else
                     sb_szCOAName = new StringBuilder( szCOAName );
                                    ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
                  szCOAName = sb_szCOAName.toString( );}
               } 

               //:END 
            } 

            //:END 
         } 

         //:END 
      } 

      //:END

      //:// szCOAName = szCOAName + zTAB
      //:// GetStringFromAttributeByContext( szCOANumber, qFinAidA, "FinAidCOAItemAssigned", "RevenueAmount", "Revenue",20)
      //:GetStringFromAttributeByContext( szSomeNumber, qFinAidA, "FinAidCOAItemAssigned", "RevenueAmount", "Revenue",10)
      {StringBuilder sb_szSomeNumber;
      if ( szSomeNumber == null )
         sb_szSomeNumber = new StringBuilder( 32 );
      else
         sb_szSomeNumber = new StringBuilder( szSomeNumber );
             GetStringFromAttributeByContext( sb_szSomeNumber, qFinAidA, "FinAidCOAItemAssigned", "RevenueAmount", "Revenue", 10 );
      szSomeNumber = sb_szSomeNumber.toString( );}
      //:nLen = zstrlen( szSomeNumber ) //szFallAmount
      nLen = zstrlen( szSomeNumber );
      //:ConvertToString10( qFinAidA, szSomeNumber, szCOANumber )
       {StringBuilder sb_szCOANumber;
      if ( szCOANumber == null )
         sb_szCOANumber = new StringBuilder( 32 );
      else
         sb_szCOANumber = new StringBuilder( szCOANumber );
            ConvertToString10( qFinAidA, szSomeNumber, sb_szCOANumber );
      szCOANumber = sb_szCOANumber.toString( );}

      //:szCOAString = szCOAName + szCOANumber
       {StringBuilder sb_szCOAString;
      if ( szCOAString == null )
         sb_szCOAString = new StringBuilder( 32 );
      else
         sb_szCOAString = new StringBuilder( szCOAString );
            ZeidonStringCopy( sb_szCOAString, 1, 0, szCOAName, 1, 0, 255 );
      szCOAString = sb_szCOAString.toString( );}
       {StringBuilder sb_szCOAString;
      if ( szCOAString == null )
         sb_szCOAString = new StringBuilder( 32 );
      else
         sb_szCOAString = new StringBuilder( szCOAString );
            ZeidonStringConcat( sb_szCOAString, 1, 0, szCOANumber, 1, 0, 255 );
      szCOAString = sb_szCOAString.toString( );}
      //:qFinAidA.FinAidCOAItemAssigned.COAString = szCOAString 
      SetAttributeFromString( qFinAidA, "FinAidCOAItemAssigned", "COAString", szCOAString );

      //:// Add to Totals
      //:IF qFinAidA.FinAidCOAItem.COAItemType = "EDU" OR qFinAidA.FinAidCOAItem.COAItemType = "FEE"
      if ( CompareAttributeToString( qFinAidA, "FinAidCOAItem", "COAItemType", "EDU" ) == 0 || CompareAttributeToString( qFinAidA, "FinAidCOAItem", "COAItemType", "FEE" ) == 0 )
      { 
         //:qFinAidA.AwardLetters.TuitionAndFeesTotal = qFinAidA.AwardLetters.TuitionAndFeesTotal + qFinAidA.FinAidCOAItemAssigned.RevenueAmount 
         {MutableDouble md_dTempDecimal_60 = new MutableDouble( dTempDecimal_60 );
                   GetDecimalFromAttribute( md_dTempDecimal_60, qFinAidA, "AwardLetters", "TuitionAndFeesTotal" );
         dTempDecimal_60 = md_dTempDecimal_60.doubleValue( );}
         {MutableDouble md_dTempDecimal_61 = new MutableDouble( dTempDecimal_61 );
                   GetDecimalFromAttribute( md_dTempDecimal_61, qFinAidA, "FinAidCOAItemAssigned", "RevenueAmount" );
         dTempDecimal_61 = md_dTempDecimal_61.doubleValue( );}
         dTempDecimal_62 = dTempDecimal_60 + dTempDecimal_61;
         SetAttributeFromDecimal( qFinAidA, "AwardLetters", "TuitionAndFeesTotal", dTempDecimal_62 );
      } 

      //:END
      //:IF qFinAidA.FinAidCOAItem.COAItemType = "COL"
      if ( CompareAttributeToString( qFinAidA, "FinAidCOAItem", "COAItemType", "COL" ) == 0 )
      { 
         //:qFinAidA.AwardLetters.CostOfLivingTotal = qFinAidA.AwardLetters.CostOfLivingTotal + qFinAidA.FinAidCOAItemAssigned.RevenueAmount 
         {MutableDouble md_dTempDecimal_63 = new MutableDouble( dTempDecimal_63 );
                   GetDecimalFromAttribute( md_dTempDecimal_63, qFinAidA, "AwardLetters", "CostOfLivingTotal" );
         dTempDecimal_63 = md_dTempDecimal_63.doubleValue( );}
         {MutableDouble md_dTempDecimal_64 = new MutableDouble( dTempDecimal_64 );
                   GetDecimalFromAttribute( md_dTempDecimal_64, qFinAidA, "FinAidCOAItemAssigned", "RevenueAmount" );
         dTempDecimal_64 = md_dTempDecimal_64.doubleValue( );}
         dTempDecimal_65 = dTempDecimal_63 + dTempDecimal_64;
         SetAttributeFromDecimal( qFinAidA, "AwardLetters", "CostOfLivingTotal", dTempDecimal_65 );
      } 

      RESULT = SetCursorNextEntity( qFinAidA, "FinAidCOAItemAssigned", "" );
      //:END
   } 

   //:   
   //:END

   //:qFinAidA.AwardLetters.CostOfAttendanceTotal = qFinAidA.AwardLetters.TuitionAndFeesTotal + qFinAidA.AwardLetters.CostOfLivingTotal  
   {MutableDouble md_dTempDecimal_66 = new MutableDouble( dTempDecimal_66 );
       GetDecimalFromAttribute( md_dTempDecimal_66, qFinAidA, "AwardLetters", "TuitionAndFeesTotal" );
   dTempDecimal_66 = md_dTempDecimal_66.doubleValue( );}
   {MutableDouble md_dTempDecimal_67 = new MutableDouble( dTempDecimal_67 );
       GetDecimalFromAttribute( md_dTempDecimal_67, qFinAidA, "AwardLetters", "CostOfLivingTotal" );
   dTempDecimal_67 = md_dTempDecimal_67.doubleValue( );}
   dTempDecimal_68 = dTempDecimal_66 + dTempDecimal_67;
   SetAttributeFromDecimal( qFinAidA, "AwardLetters", "CostOfAttendanceTotal", dTempDecimal_68 );
   //:CREATE ENTITY qFinAidA.FinAidCOAItemAssigned  
   RESULT = CreateEntity( qFinAidA, "FinAidCOAItemAssigned", zPOS_AFTER );
   //:qFinAidA.FinAidCOAItemAssigned.COAString = zTAB + zTAB + zTAB + zTAB + zTAB + "____________"
    {StringBuilder sb_szTempString_7;
   if ( szTempString_7 == null )
      sb_szTempString_7 = new StringBuilder( 32 );
   else
      sb_szTempString_7 = new StringBuilder( szTempString_7 );
      ZeidonStringCopy( sb_szTempString_7, 1, 0, zTAB, 1, 0, 255 );
   szTempString_7 = sb_szTempString_7.toString( );}
    {StringBuilder sb_szTempString_7;
   if ( szTempString_7 == null )
      sb_szTempString_7 = new StringBuilder( 32 );
   else
      sb_szTempString_7 = new StringBuilder( szTempString_7 );
      ZeidonStringConcat( sb_szTempString_7, 1, 0, zTAB, 1, 0, 255 );
   szTempString_7 = sb_szTempString_7.toString( );}
    {StringBuilder sb_szTempString_7;
   if ( szTempString_7 == null )
      sb_szTempString_7 = new StringBuilder( 32 );
   else
      sb_szTempString_7 = new StringBuilder( szTempString_7 );
      ZeidonStringConcat( sb_szTempString_7, 1, 0, zTAB, 1, 0, 255 );
   szTempString_7 = sb_szTempString_7.toString( );}
    {StringBuilder sb_szTempString_7;
   if ( szTempString_7 == null )
      sb_szTempString_7 = new StringBuilder( 32 );
   else
      sb_szTempString_7 = new StringBuilder( szTempString_7 );
      ZeidonStringConcat( sb_szTempString_7, 1, 0, zTAB, 1, 0, 255 );
   szTempString_7 = sb_szTempString_7.toString( );}
    {StringBuilder sb_szTempString_7;
   if ( szTempString_7 == null )
      sb_szTempString_7 = new StringBuilder( 32 );
   else
      sb_szTempString_7 = new StringBuilder( szTempString_7 );
      ZeidonStringConcat( sb_szTempString_7, 1, 0, zTAB, 1, 0, 255 );
   szTempString_7 = sb_szTempString_7.toString( );}
    {StringBuilder sb_szTempString_7;
   if ( szTempString_7 == null )
      sb_szTempString_7 = new StringBuilder( 32 );
   else
      sb_szTempString_7 = new StringBuilder( szTempString_7 );
      ZeidonStringConcat( sb_szTempString_7, 1, 0, "____________", 1, 0, 255 );
   szTempString_7 = sb_szTempString_7.toString( );}
   SetAttributeFromString( qFinAidA, "FinAidCOAItemAssigned", "COAString", szTempString_7 );

   //:CREATE ENTITY qFinAidA.FinAidCOAItemAssigned  
   RESULT = CreateEntity( qFinAidA, "FinAidCOAItemAssigned", zPOS_AFTER );
   //:szCOAName = "Total Cost of Attendance:" + zTAB
    {StringBuilder sb_szCOAName;
   if ( szCOAName == null )
      sb_szCOAName = new StringBuilder( 32 );
   else
      sb_szCOAName = new StringBuilder( szCOAName );
      ZeidonStringCopy( sb_szCOAName, 1, 0, "Total Cost of Attendance:", 1, 0, 31 );
   szCOAName = sb_szCOAName.toString( );}
    {StringBuilder sb_szCOAName;
   if ( szCOAName == null )
      sb_szCOAName = new StringBuilder( 32 );
   else
      sb_szCOAName = new StringBuilder( szCOAName );
      ZeidonStringConcat( sb_szCOAName, 1, 0, zTAB, 1, 0, 31 );
   szCOAName = sb_szCOAName.toString( );}
   //://ZeidonStringConvertFromNumber( szSomeNumber, 1, 20, 20, 1, 
   //://                               qFinAidA.FinAidProfile.dTotalCOA, "D")
   //:wXferO.Root.WorkRevenue = qFinAidA.FinAidProfile.dTotalCOA
   SetAttributeFromAttribute( wXferO, "Root", "WorkRevenue", qFinAidA, "FinAidProfile", "dTotalCOA" );
   //:GetStringFromAttributeByContext( szSomeNumber, wXferO, "Root", "WorkRevenue", "Revenue",10)
   {StringBuilder sb_szSomeNumber;
   if ( szSomeNumber == null )
      sb_szSomeNumber = new StringBuilder( 32 );
   else
      sb_szSomeNumber = new StringBuilder( szSomeNumber );
       GetStringFromAttributeByContext( sb_szSomeNumber, wXferO, "Root", "WorkRevenue", "Revenue", 10 );
   szSomeNumber = sb_szSomeNumber.toString( );}
   //:nLen = zstrlen( szSomeNumber ) //szCOANumber
   nLen = zstrlen( szSomeNumber );
   //:ConvertToString10( qFinAidA, szSomeNumber, szCOANumber )
    {StringBuilder sb_szCOANumber;
   if ( szCOANumber == null )
      sb_szCOANumber = new StringBuilder( 32 );
   else
      sb_szCOANumber = new StringBuilder( szCOANumber );
      ConvertToString10( qFinAidA, szSomeNumber, sb_szCOANumber );
   szCOANumber = sb_szCOANumber.toString( );}

   //:szCOAString = szCOAName + szCOANumber
    {StringBuilder sb_szCOAString;
   if ( szCOAString == null )
      sb_szCOAString = new StringBuilder( 32 );
   else
      sb_szCOAString = new StringBuilder( szCOAString );
      ZeidonStringCopy( sb_szCOAString, 1, 0, szCOAName, 1, 0, 255 );
   szCOAString = sb_szCOAString.toString( );}
    {StringBuilder sb_szCOAString;
   if ( szCOAString == null )
      sb_szCOAString = new StringBuilder( 32 );
   else
      sb_szCOAString = new StringBuilder( szCOAString );
      ZeidonStringConcat( sb_szCOAString, 1, 0, szCOANumber, 1, 0, 255 );
   szCOAString = sb_szCOAString.toString( );}
   //:qFinAidA.FinAidCOAItemAssigned.COAString = szCOAString
   SetAttributeFromString( qFinAidA, "FinAidCOAItemAssigned", "COAString", szCOAString );

   //:// Compute Student remaining need as COA - Total Fin Aid
   //:qFinAidA.AwardLetters.COA_MinusFinAidTotal = qFinAidA.AwardLetters.CostOfAttendanceTotal - qFinAidA.AwardLetters.FinAidFullYearTotal 
   {MutableDouble md_dTempDecimal_69 = new MutableDouble( dTempDecimal_69 );
       GetDecimalFromAttribute( md_dTempDecimal_69, qFinAidA, "AwardLetters", "CostOfAttendanceTotal" );
   dTempDecimal_69 = md_dTempDecimal_69.doubleValue( );}
   {MutableDouble md_dTempDecimal_70 = new MutableDouble( dTempDecimal_70 );
       GetDecimalFromAttribute( md_dTempDecimal_70, qFinAidA, "AwardLetters", "FinAidFullYearTotal" );
   dTempDecimal_70 = md_dTempDecimal_70.doubleValue( );}
   dTempDecimal_71 = dTempDecimal_69 - dTempDecimal_70;
   SetAttributeFromDecimal( qFinAidA, "AwardLetters", "COA_MinusFinAidTotal", dTempDecimal_71 );
   //:IF qFinAidA.AwardLetters.COA_MinusFinAidTotal < 0 
   if ( CompareAttributeToInteger( qFinAidA, "AwardLetters", "COA_MinusFinAidTotal", 0 ) < 0 )
   { 
      //:qFinAidA.AwardLetters.COA_MinusFinAidTotal = 0    
      SetAttributeFromInteger( qFinAidA, "AwardLetters", "COA_MinusFinAidTotal", 0 );
   } 

   //:END

   //:// If there is any remaining amount, generate Option Funding entities.
   //:// DonC commented out the code below because he believed it was no longer used.
   //:/*IF qFinAidA.AwardLetters.COA_MinusFinAidTotal > 4000
   //:   dParentStudentLoan = qFinAidA.AwardLetters.COA_MinusFinAidTotal - 4000
   //:   dParentStudentLoanHalf = dParentStudentLoan / 2
   //:   CREATE ENTITY qFinAidA.OptionalFundingGroup 
   //:   qFinAidA.OptionalFundingGroup.AwardName    = "Summer Earnings"
   //:   qFinAidA.OptionalFundingGroup.FallAmount   = 1000
   //:   qFinAidA.OptionalFundingGroup.SpringAmount = 1000
   //:   qFinAidA.OptionalFundingGroup.Amount       = 2000
   //:   szAwardValue = "Summer Earnings" + zTAB + "  1,000.00" + zTAB + "  1,000.00" + zTAB + "  2,000.00"
   //:   qFinAidA.OptionalFundingGroup.AwardValue = szAwardValue
   //:   CREATE ENTITY qFinAidA.OptionalFundingGroup 
   //:   qFinAidA.OptionalFundingGroup.AwardName    = "Student Earnings or Federal Work Study"
   //:   qFinAidA.OptionalFundingGroup.FallAmount   = 1000
   //:   qFinAidA.OptionalFundingGroup.SpringAmount = 1000
   //:   qFinAidA.OptionalFundingGroup.Amount       = 2000
   //:   szAwardValue = "Student Earnings/Federal Work Study" + zTAB + "  1,000.00" + zTAB + "  1,000.00" + zTAB + "  2,000.00"
   //:   qFinAidA.OptionalFundingGroup.AwardValue = szAwardValue
   //:   CREATE ENTITY qFinAidA.OptionalFundingGroup 
   //:   qFinAidA.OptionalFundingGroup.AwardName    = "Parent PLUS Loan / Student Loan"
   //:   qFinAidA.OptionalFundingGroup.FallAmount   = dParentStudentLoanHalf
   //:   qFinAidA.OptionalFundingGroup.SpringAmount = dParentStudentLoanHalf
   //:   qFinAidA.OptionalFundingGroup.Amount       = dParentStudentLoan
   //:   GetStringFromAttributeByContext( szSomeNumber, qFinAidA, "OptionalFundingGroup", "FallAmount", "Revenue",10)
   //:   ConvertToString10( qFinAidA, szSomeNumber, szFallAmount )
   //:   GetStringFromAttributeByContext( szSomeNumber, qFinAidA, "OptionalFundingGroup", "Amount", "Revenue",10)
   //:   ConvertToString10( qFinAidA, szSomeNumber, szAmount )
   //:   szAwardValue = "Parent PLUS Loan/Student Loan" + zTAB + szFallAmount + zTAB + szFallAmount + zTAB + szAmount
   //:   qFinAidA.OptionalFundingGroup.AwardValue = szAwardValue
   //:END*/

   //:// If the Student Payment Contract has not been cleared, set the letter text.
   //:szClearedFlag = "N"
    {StringBuilder sb_szClearedFlag;
   if ( szClearedFlag == null )
      sb_szClearedFlag = new StringBuilder( 32 );
   else
      sb_szClearedFlag = new StringBuilder( szClearedFlag );
      ZeidonStringCopy( sb_szClearedFlag, 1, 0, "N", 1, 0, 2 );
   szClearedFlag = sb_szClearedFlag.toString( );}
   //:IF qFinAidA.StudentAccountProfile EXISTS
   lTempInteger_8 = CheckExistenceOfEntity( qFinAidA, "StudentAccountProfile" );
   if ( lTempInteger_8 == 0 )
   { 
      //:IF qFinAidA.StudentAccountProfile.Cleared = "Y"
      if ( CompareAttributeToString( qFinAidA, "StudentAccountProfile", "Cleared", "Y" ) == 0 )
      { 
         //:szClearedFlag = "Y"
          {StringBuilder sb_szClearedFlag;
         if ( szClearedFlag == null )
            sb_szClearedFlag = new StringBuilder( 32 );
         else
            sb_szClearedFlag = new StringBuilder( szClearedFlag );
                  ZeidonStringCopy( sb_szClearedFlag, 1, 0, "Y", 1, 0, 2 );
         szClearedFlag = sb_szClearedFlag.toString( );}
      } 

      //:END
   } 

   //:END
   //:IF szClearedFlag = "N" 
   if ( ZeidonStringCompare( szClearedFlag, 1, 0, "N", 1, 0, 2 ) == 0 )
   { 
      //:GET VIEW mFAConst NAMED "mFAConst"
      RESULT = GetViewByName( mFAConst, "mFAConst", qFinAidA, zLEVEL_TASK );
      //:IF RESULT < 0
      if ( RESULT < 0 )
      { 
         //:ACTIVATE mFAConst
         RESULT = ActivateObjectInstance( mFAConst, "mFAConst", qFinAidA, 0, zSINGLE );
         //:NAME VIEW mFAConst "mFAConst"
         SetNameForView( mFAConst, "mFAConst", null, zLEVEL_TASK );
      } 

      //:END
      //:IF mFAConst > 0
      if ( mFAConst != null )
      { 
         //:qFinAidA.AwardLetters.StudentAccountNotClearedText = mFAConst.FinancialAidConstants.StudentAccountNotClearedText
         SetAttributeFromAttribute( qFinAidA, "AwardLetters", "StudentAccountNotClearedText", mFAConst, "FinancialAidConstants", "StudentAccountNotClearedText" );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:GenerateFA_Requirements( VIEW qFinAidA BASED ON LOD qFinAidA )

//:   VIEW mFAISIRD BASED ON LOD mFAISIRD
public int 
GenerateFA_Requirements( View     qFinAidA )
{
   zVIEW    mFAISIRD = new zVIEW( );
   //:STRING ( 8 ) LatestDate
   String   LatestDate = null;
   //:STRING ( 1 ) szCommentCodesActivateFlag
   String   szCommentCodesActivateFlag = null;
   //:STRING ( 3 ) szCommentCode
   String   szCommentCode = null;
   int      RESULT = 0;
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:// Generate Fin Aid Requirements work attributes for any object with the correct qFinAidA subobject structure
   //:// for formatting FA Requirements.

   //:   // If a Requirement uses Comment Codes text, make sure we have the Comments Codes Text object for the appropriate Year.
   //:SET CURSOR FIRST qFinAidA.FinAidProfileRequirement WHERE qFinAidA.FinAidRequirement.UseCommentsCodeLetterText = "Y"
   RESULT = SetCursorFirstEntity( qFinAidA, "FinAidProfileRequirement", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( qFinAidA, "FinAidRequirement", "UseCommentsCodeLetterText", "Y" ) != 0 ) )
      { 
         RESULT = SetCursorNextEntity( qFinAidA, "FinAidProfileRequirement", "" );
      } 

   } 

   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:GET VIEW mFAISIRD NAMED "mFAISIRDCommentCodes"
      RESULT = GetViewByName( mFAISIRD, "mFAISIRDCommentCodes", qFinAidA, zLEVEL_TASK );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:IF mFAISIRD.FAISIRDomain.Year != qFinAidA.CollegeYear.Year 
         if ( CompareAttributeToAttribute( mFAISIRD, "FAISIRDomain", "Year", qFinAidA, "CollegeYear", "Year" ) != 0 )
         { 
            //:DropObjectInstance( mFAISIRD )
            DropObjectInstance( mFAISIRD );
            //:szCommentCodesActivateFlag = "Y"
             {StringBuilder sb_szCommentCodesActivateFlag;
            if ( szCommentCodesActivateFlag == null )
               sb_szCommentCodesActivateFlag = new StringBuilder( 32 );
            else
               sb_szCommentCodesActivateFlag = new StringBuilder( szCommentCodesActivateFlag );
                        ZeidonStringCopy( sb_szCommentCodesActivateFlag, 1, 0, "Y", 1, 0, 2 );
            szCommentCodesActivateFlag = sb_szCommentCodesActivateFlag.toString( );}
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:szCommentCodesActivateFlag = "Y"
          {StringBuilder sb_szCommentCodesActivateFlag;
         if ( szCommentCodesActivateFlag == null )
            sb_szCommentCodesActivateFlag = new StringBuilder( 32 );
         else
            sb_szCommentCodesActivateFlag = new StringBuilder( szCommentCodesActivateFlag );
                  ZeidonStringCopy( sb_szCommentCodesActivateFlag, 1, 0, "Y", 1, 0, 2 );
         szCommentCodesActivateFlag = sb_szCommentCodesActivateFlag.toString( );}
      } 

      //:END
      //:IF szCommentCodesActivateFlag = "Y"
      if ( ZeidonStringCompare( szCommentCodesActivateFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:ACTIVATE mFAISIRD WHERE mFAISIRD.FAISIRDomain.Name = "CommentCodes"
         //:                    AND mFAISIRD.FAISIRDomain.Year = qFinAidA.CollegeYear.Year 
         {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetStringFromAttribute( sb_szTempString_0, qFinAidA, "CollegeYear", "Year" );
         szTempString_0 = sb_szTempString_0.toString( );}
         o_fnLocalBuildQual_17( qFinAidA, vTempViewVar_0, szTempString_0 );
         RESULT = ActivateObjectInstance( mFAISIRD, "mFAISIRD", qFinAidA, vTempViewVar_0, zSINGLE );
         DropView( vTempViewVar_0 );
         //:NAME VIEW mFAISIRD "mFAISIRDCommentCode"
         SetNameForView( mFAISIRD, "mFAISIRDCommentCode", null, zLEVEL_TASK );
      } 

      //:END
   } 

   //:END

   //:// Generate a list of Requirements that have been met and that are yet to be accomplished.
   //:// Don't generate for Requirements that say "hide on interface".
   //:FOR EACH qFinAidA.FinAidProfileRequirement 
   RESULT = SetCursorFirstEntity( qFinAidA, "FinAidProfileRequirement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:IF qFinAidA.FinAidProfileRequirement.CurrentStatus = "M"
      if ( CompareAttributeToString( qFinAidA, "FinAidProfileRequirement", "CurrentStatus", "M" ) == 0 )
      { 

         //:IF qFinAidA.FinAidRequirement.HideOnInterface = "" 
         if ( CompareAttributeToString( qFinAidA, "FinAidRequirement", "HideOnInterface", "" ) == 0 )
         { 
            //:// Add Received Item.
            //:CREATE ENTITY qFinAidA.ReceivedItems 
            RESULT = CreateEntity( qFinAidA, "ReceivedItems", zPOS_AFTER );
            //:qFinAidA.ReceivedItems.Name             = qFinAidA.FinAidRequirement.Name 
            SetAttributeFromAttribute( qFinAidA, "ReceivedItems", "Name", qFinAidA, "FinAidRequirement", "Name" );
            //:qFinAidA.ReceivedItems.Description      = qFinAidA.FinAidRequirement.Description 
            SetAttributeFromAttribute( qFinAidA, "ReceivedItems", "Description", qFinAidA, "FinAidRequirement", "Description" );
            //:qFinAidA.ReceivedItems.PrintDescription = qFinAidA.FinAidRequirement.PrintDescription 
            SetAttributeFromAttribute( qFinAidA, "ReceivedItems", "PrintDescription", qFinAidA, "FinAidRequirement", "PrintDescription" );
            //:qFinAidA.ReceivedItems.DateReceived     = qFinAidA.FinAidProfileRequirement.DateReceived 
            SetAttributeFromAttribute( qFinAidA, "ReceivedItems", "DateReceived", qFinAidA, "FinAidProfileRequirement", "DateReceived" );
            //:// If the entry's text comes from comment code object, set it from there. Otherwise, use Description set above.
            //:IF qFinAidA.FinAidRequirement.UseCommentsCodeLetterText = "Y"
            if ( CompareAttributeToString( qFinAidA, "FinAidRequirement", "UseCommentsCodeLetterText", "Y" ) == 0 )
            { 
               //:szCommentCode = qFinAidA.FinAidRequirement.CommentCodesRule 
               {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
               StringBuilder sb_szCommentCode;
               if ( szCommentCode == null )
                  sb_szCommentCode = new StringBuilder( 32 );
               else
                  sb_szCommentCode = new StringBuilder( szCommentCode );
                               GetVariableFromAttribute( sb_szCommentCode, mi_lTempInteger_0, 'S', 4, qFinAidA, "FinAidRequirement", "CommentCodesRule", "", 0 );
               lTempInteger_0 = mi_lTempInteger_0.intValue( );
               szCommentCode = sb_szCommentCode.toString( );}
               //:SET CURSOR FIRST mFAISIRD.FAISIRDomainValue WHERE mFAISIRD.FAISIRDomainValue.InternalStringValue = szCommentCode
               RESULT = SetCursorFirstEntityByString( mFAISIRD, "FAISIRDomainValue", "InternalStringValue", szCommentCode, "" );
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:qFinAidA.ReceivedItems.Description = mFAISIRD.FAISIRDomainValue.ExternalDescription 
                  SetAttributeFromAttribute( qFinAidA, "ReceivedItems", "Description", mFAISIRD, "FAISIRDomainValue", "ExternalDescription" );
               } 

               //:END 
            } 

            //:END
         } 

         //:   
         //:END

         //:ELSE
      } 
      else
      { 
         //:IF qFinAidA.FinAidProfileRequirement.CurrentStatus != "N" AND   // Not Required
         //:   qFinAidA.FinAidProfileRequirement.CurrentStatus != "F" AND   // Final
         //:   qFinAidA.FinAidProfileRequirement.CurrentStatus != "W"       // Waived
         if ( CompareAttributeToString( qFinAidA, "FinAidProfileRequirement", "CurrentStatus", "N" ) != 0 && CompareAttributeToString( qFinAidA, "FinAidProfileRequirement", "CurrentStatus", "F" ) != 0 &&
              CompareAttributeToString( qFinAidA, "FinAidProfileRequirement", "CurrentStatus", "W" ) != 0 )
         { 

            //:IF qFinAidA.FinAidRequirement.HideOnInterface = "" 
            if ( CompareAttributeToString( qFinAidA, "FinAidRequirement", "HideOnInterface", "" ) == 0 )
            { 

               //:// Add Missing Item.
               //:CREATE ENTITY qFinAidA.MissingItems 
               RESULT = CreateEntity( qFinAidA, "MissingItems", zPOS_AFTER );
               //:qFinAidA.MissingItems.Name             = qFinAidA.FinAidRequirement.Name 
               SetAttributeFromAttribute( qFinAidA, "MissingItems", "Name", qFinAidA, "FinAidRequirement", "Name" );
               //:qFinAidA.MissingItems.Description      = qFinAidA.FinAidRequirement.Description 
               SetAttributeFromAttribute( qFinAidA, "MissingItems", "Description", qFinAidA, "FinAidRequirement", "Description" );
               //:qFinAidA.MissingItems.PrintDescription = qFinAidA.FinAidRequirement.PrintDescription 
               SetAttributeFromAttribute( qFinAidA, "MissingItems", "PrintDescription", qFinAidA, "FinAidRequirement", "PrintDescription" );
               //:// If the entry's text comes from comment code object, set it from there. Otherwise, use Description set above.
               //:IF qFinAidA.FinAidRequirement.UseCommentsCodeLetterText = "Y"
               if ( CompareAttributeToString( qFinAidA, "FinAidRequirement", "UseCommentsCodeLetterText", "Y" ) == 0 )
               { 
                  //:szCommentCode = qFinAidA.FinAidRequirement.CommentCodesRule 
                  {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                  StringBuilder sb_szCommentCode;
                  if ( szCommentCode == null )
                     sb_szCommentCode = new StringBuilder( 32 );
                  else
                     sb_szCommentCode = new StringBuilder( szCommentCode );
                                     GetVariableFromAttribute( sb_szCommentCode, mi_lTempInteger_1, 'S', 4, qFinAidA, "FinAidRequirement", "CommentCodesRule", "", 0 );
                  lTempInteger_1 = mi_lTempInteger_1.intValue( );
                  szCommentCode = sb_szCommentCode.toString( );}
                  //:SET CURSOR FIRST mFAISIRD.FAISIRDomainValue WHERE mFAISIRD.FAISIRDomainValue.InternalStringValue = szCommentCode
                  RESULT = SetCursorFirstEntityByString( mFAISIRD, "FAISIRDomainValue", "InternalStringValue", szCommentCode, "" );
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:qFinAidA.MissingItems.Description = mFAISIRD.FAISIRDomainValue.ExternalDescription 
                     SetAttributeFromAttribute( qFinAidA, "MissingItems", "Description", mFAISIRD, "FAISIRDomainValue", "ExternalDescription" );
                  } 

                  //:END 
               } 

               //:END
            } 


            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( qFinAidA, "FinAidProfileRequirement", "" );
      //:   
      //:END
   } 

   //:END

   //:// Set up the requirements received on the latest date.
   //:OrderEntityForView( qFinAidA, "FinAidProfileRequirement", "DateReceived A" )
   OrderEntityForView( qFinAidA, "FinAidProfileRequirement", "DateReceived A" );
   //:SET CURSOR LAST qFinAidA.FinAidProfileRequirement WHERE qFinAidA.FinAidProfileRequirement.CurrentStatus = "M"
   RESULT = SetCursorLastEntity( qFinAidA, "FinAidProfileRequirement", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( qFinAidA, "FinAidProfileRequirement", "CurrentStatus", "M" ) != 0 ) )
      { 
         RESULT = SetCursorPrevEntity( qFinAidA, "FinAidProfileRequirement", "" );
      } 

   } 

   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:LatestDate = qFinAidA.FinAidProfileRequirement.DateReceived 
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_LatestDate;
      if ( LatestDate == null )
         sb_LatestDate = new StringBuilder( 32 );
      else
         sb_LatestDate = new StringBuilder( LatestDate );
             GetVariableFromAttribute( sb_LatestDate, mi_lTempInteger_2, 'S', 9, qFinAidA, "FinAidProfileRequirement", "DateReceived", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      LatestDate = sb_LatestDate.toString( );}
      //:FOR EACH qFinAidA.FinAidProfileRequirement
      RESULT = SetCursorFirstEntity( qFinAidA, "FinAidProfileRequirement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF qFinAidA.FinAidProfileRequirement.DateReceived = LatestDate AND
         //:   qFinAidA.FinAidProfileRequirement.CurrentStatus = "M"
         if ( CompareAttributeToString( qFinAidA, "FinAidProfileRequirement", "DateReceived", LatestDate ) == 0 && CompareAttributeToString( qFinAidA, "FinAidProfileRequirement", "CurrentStatus", "M" ) == 0 )
         { 

            //:CREATE ENTITY qFinAidA.LastReceivedItem 
            RESULT = CreateEntity( qFinAidA, "LastReceivedItem", zPOS_AFTER );
            //:qFinAidA.LastReceivedItem.Name             = qFinAidA.FinAidRequirement.Name 
            SetAttributeFromAttribute( qFinAidA, "LastReceivedItem", "Name", qFinAidA, "FinAidRequirement", "Name" );
            //:qFinAidA.LastReceivedItem.Description      = qFinAidA.FinAidRequirement.Description 
            SetAttributeFromAttribute( qFinAidA, "LastReceivedItem", "Description", qFinAidA, "FinAidRequirement", "Description" );
            //:qFinAidA.LastReceivedItem.PrintDescription = qFinAidA.FinAidRequirement.PrintDescription 
            SetAttributeFromAttribute( qFinAidA, "LastReceivedItem", "PrintDescription", qFinAidA, "FinAidRequirement", "PrintDescription" );
            //:qFinAidA.LastReceivedItem.DateReceived     = LatestDate
            SetAttributeFromString( qFinAidA, "LastReceivedItem", "DateReceived", LatestDate );
         } 

         RESULT = SetCursorNextEntity( qFinAidA, "FinAidProfileRequirement", "" );
         //:END
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:SetIPEDS_Ethnicity( VIEW mPerson BASED ON LOD mPerson )

//:   INTEGER iCount
public int 
SetIPEDS_Ethnicity( View     mPerson )
{
   int      iCount = 0;


   //:// Set the IPEDS_Ethnicity attribute value from other Demographics information.
   //:// The view passed in can be any view containin an updatable Demographics entity.

   //:// If the Citizenship value is Nonresident Alien, then that is the value of the IPEDS_Ethnicity attribute,
   //:// regardless of what any of the Ethnic attributes are set to.
   //:IF mPerson.Demographics.CitizenshipCode = "FS"    // FS is Nonresident Alien
   if ( CompareAttributeToString( mPerson, "Demographics", "CitizenshipCode", "FS" ) == 0 )
   { 
      //:mPerson.Demographics.IPEDS_Ethnicity = "NRAlien"
      SetAttributeFromString( mPerson, "Demographics", "IPEDS_Ethnicity", "NRAlien" );
      //:ELSE
   } 
   else
   { 

      //:// If the student did not select anything, then mark this as Unknown
      //:IF mPerson.Demographics.EthnicityHispanic = "" AND mPerson.Demographics.EthnicityAmerIndian = "" AND
      //:   mPerson.Demographics.EthnicityAsian = "" AND mPerson.Demographics.EthnicityBlack = "" AND
      //:   mPerson.Demographics.EthnicityPacific = "" AND mPerson.Demographics.EthnicityWhite = ""
      if ( CompareAttributeToString( mPerson, "Demographics", "EthnicityHispanic", "" ) == 0 && CompareAttributeToString( mPerson, "Demographics", "EthnicityAmerIndian", "" ) == 0 &&
           CompareAttributeToString( mPerson, "Demographics", "EthnicityAsian", "" ) == 0 && CompareAttributeToString( mPerson, "Demographics", "EthnicityBlack", "" ) == 0 &&
           CompareAttributeToString( mPerson, "Demographics", "EthnicityPacific", "" ) == 0 && CompareAttributeToString( mPerson, "Demographics", "EthnicityWhite", "" ) == 0 )
      { 

         //:mPerson.Demographics.IPEDS_Ethnicity = "Unknown"
         SetAttributeFromString( mPerson, "Demographics", "IPEDS_Ethnicity", "Unknown" );
         //:ELSE
      } 
      else
      { 

         //:// If the user chose "No" on hispanic, but then didn't fill anything else out, this is Unknown.
         //:IF mPerson.Demographics.EthnicityHispanic = "N" AND mPerson.Demographics.EthnicityAmerIndian = "" AND
         //:mPerson.Demographics.EthnicityAsian = "" AND mPerson.Demographics.EthnicityBlack = "" AND
         //:mPerson.Demographics.EthnicityPacific = "" AND mPerson.Demographics.EthnicityWhite = ""
         if ( CompareAttributeToString( mPerson, "Demographics", "EthnicityHispanic", "N" ) == 0 && CompareAttributeToString( mPerson, "Demographics", "EthnicityAmerIndian", "" ) == 0 &&
              CompareAttributeToString( mPerson, "Demographics", "EthnicityAsian", "" ) == 0 && CompareAttributeToString( mPerson, "Demographics", "EthnicityBlack", "" ) == 0 &&
              CompareAttributeToString( mPerson, "Demographics", "EthnicityPacific", "" ) == 0 && CompareAttributeToString( mPerson, "Demographics", "EthnicityWhite", "" ) == 0 )
         { 

            //:mPerson.Demographics.IPEDS_Ethnicity = "Unknown"
            SetAttributeFromString( mPerson, "Demographics", "IPEDS_Ethnicity", "Unknown" );
            //:ELSE
         } 
         else
         { 

            //:// If they marked "Yes" on hispanic then it doesn't matter what else they have selected, IPEDS_Ethnicity will
            //:// be set to Hispanic.
            //:IF mPerson.Demographics.EthnicityHispanic = "Y"
            if ( CompareAttributeToString( mPerson, "Demographics", "EthnicityHispanic", "Y" ) == 0 )
            { 
               //:mPerson.Demographics.IPEDS_Ethnicity = "Hispanic"
               SetAttributeFromString( mPerson, "Demographics", "IPEDS_Ethnicity", "Hispanic" );
               //:ELSE
            } 
            else
            { 

               //:iCount = 0
               iCount = 0;
               //:IF mPerson.Demographics.EthnicityAmerIndian = "Y" 
               if ( CompareAttributeToString( mPerson, "Demographics", "EthnicityAmerIndian", "Y" ) == 0 )
               { 
                  //:iCount = iCount + 1
                  iCount = iCount + 1;
               } 

               //:END
               //:IF mPerson.Demographics.EthnicityAsian = "Y" 
               if ( CompareAttributeToString( mPerson, "Demographics", "EthnicityAsian", "Y" ) == 0 )
               { 
                  //:iCount = iCount + 1
                  iCount = iCount + 1;
               } 

               //:END
               //:IF mPerson.Demographics.EthnicityBlack = "Y" 
               if ( CompareAttributeToString( mPerson, "Demographics", "EthnicityBlack", "Y" ) == 0 )
               { 
                  //:iCount = iCount + 1
                  iCount = iCount + 1;
               } 

               //:END
               //:IF mPerson.Demographics.EthnicityPacific = "Y" 
               if ( CompareAttributeToString( mPerson, "Demographics", "EthnicityPacific", "Y" ) == 0 )
               { 
                  //:iCount = iCount + 1
                  iCount = iCount + 1;
               } 

               //:END
               //:IF mPerson.Demographics.EthnicityWhite = "Y" 
               if ( CompareAttributeToString( mPerson, "Demographics", "EthnicityWhite", "Y" ) == 0 )
               { 
                  //:iCount = iCount + 1
                  iCount = iCount + 1;
               } 

               //:END

               //:// If they've chosen more than one ethnicity, then IPEDS_Ethnicity is set to 2 or more.
               //:IF iCount > 1
               if ( iCount > 1 )
               { 
                  //:mPerson.Demographics.IPEDS_Ethnicity = "2ormore"
                  SetAttributeFromString( mPerson, "Demographics", "IPEDS_Ethnicity", "2ormore" );
                  //:ELSE
               } 
               else
               { 
                  //:// Only one option has been chosen.
                  //:IF mPerson.Demographics.EthnicityAmerIndian = "Y" 
                  if ( CompareAttributeToString( mPerson, "Demographics", "EthnicityAmerIndian", "Y" ) == 0 )
                  { 
                     //:mPerson.Demographics.IPEDS_Ethnicity = "AmerNat"
                     SetAttributeFromString( mPerson, "Demographics", "IPEDS_Ethnicity", "AmerNat" );
                  } 

                  //:END
                  //:IF mPerson.Demographics.EthnicityAsian = "Y" 
                  if ( CompareAttributeToString( mPerson, "Demographics", "EthnicityAsian", "Y" ) == 0 )
                  { 
                     //:mPerson.Demographics.IPEDS_Ethnicity = "Asian"
                     SetAttributeFromString( mPerson, "Demographics", "IPEDS_Ethnicity", "Asian" );
                  } 

                  //:END
                  //:IF mPerson.Demographics.EthnicityBlack = "Y" 
                  if ( CompareAttributeToString( mPerson, "Demographics", "EthnicityBlack", "Y" ) == 0 )
                  { 
                     //:mPerson.Demographics.IPEDS_Ethnicity = "Black"
                     SetAttributeFromString( mPerson, "Demographics", "IPEDS_Ethnicity", "Black" );
                  } 

                  //:END
                  //:IF mPerson.Demographics.EthnicityPacific = "Y" 
                  if ( CompareAttributeToString( mPerson, "Demographics", "EthnicityPacific", "Y" ) == 0 )
                  { 
                     //:mPerson.Demographics.IPEDS_Ethnicity = "HawPacIs"
                     SetAttributeFromString( mPerson, "Demographics", "IPEDS_Ethnicity", "HawPacIs" );
                  } 

                  //:END
                  //:IF mPerson.Demographics.EthnicityWhite = "Y" 
                  if ( CompareAttributeToString( mPerson, "Demographics", "EthnicityWhite", "Y" ) == 0 )
                  { 
                     //:mPerson.Demographics.IPEDS_Ethnicity = "White"
                     SetAttributeFromString( mPerson, "Demographics", "IPEDS_Ethnicity", "White" );
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
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:CalculateTotalCOA( VIEW qFAProf BASED ON LOD qFAProf,
//:                   DECIMAL ReturnedTotal )

//:   VIEW qFAProf2 BASED ON LOD qFAProf
public int 
CalculateTotalCOA( View     qFAProf,
                   MutableDouble  ReturnedTotal )
{
   zVIEW    qFAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   //:STRING ( 20 ) szDecimal
   String   szDecimal = null;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;
   double  dTempDecimal_4 = 0.0;
   double  dTempDecimal_5 = 0.0;
   double  dTempDecimal_6 = 0.0;
   double  dTempDecimal_7 = 0.0;
   double  dTempDecimal_8 = 0.0;


   //:// Compute Total COA for passed in view, which is here name qFAProf, but can be any view with the
   //:// required COA subobject structure.

   //:// Regular COA depends on the COA Option values as follows.
   //:// Whole Year Option: Total is simply total of all Whole Year costs.
   //:// The other values are additive, meaning we simply add the costs for the option as follows:
   //:// 1st Term: If FirstTermRevenueAmount is specified, use it. Otherwise, use 50% of Whole Year costs.
   //:// 2nd Term: If SecondTermRevenueAmount is specified, use it. Otherwise, use 50% of Whole Year costs.
   //:// 3rd Term: Always use ThirdTermRevenueAmount, even if it is 0.
   //:CreateViewFromView( qFAProf2, qFAProf )
   CreateViewFromView( qFAProf2, qFAProf );
   //:dAmount = 0 
   dAmount = 0;

   //:IF qFAProf.FinAidProfile.COA_OptionWholeYearFlag = "Y"
   if ( CompareAttributeToString( qFAProf, "FinAidProfile", "COA_OptionWholeYearFlag", "Y" ) == 0 )
   { 
      //:// This is Whole Year only.
      //:FOR EACH qFAProf2.FinAidCOAItemAssigned
      RESULT = SetCursorFirstEntity( qFAProf2, "FinAidCOAItemAssigned", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF qFAProf2.FinAidCOAItemAssigned.RevenueAmount != ""
         if ( CompareAttributeToString( qFAProf2, "FinAidCOAItemAssigned", "RevenueAmount", "" ) != 0 )
         { 
            //:dAmount = dAmount + qFAProf2.FinAidCOAItemAssigned.RevenueAmount  
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, qFAProf2, "FinAidCOAItemAssigned", "RevenueAmount" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dAmount = dAmount + dTempDecimal_0;
            //:ELSE
         } 
         else
         { 
            //:dAmount = dAmount + qFAProf2.FinAidCOAItemAssigned.FirstTermRevenueAmount +
            //:                    qFAProf2.FinAidCOAItemAssigned.SecondTermRevenueAmount 
            {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                         GetDecimalFromAttribute( md_dTempDecimal_1, qFAProf2, "FinAidCOAItemAssigned", "FirstTermRevenueAmount" );
            dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
            {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                         GetDecimalFromAttribute( md_dTempDecimal_2, qFAProf2, "FinAidCOAItemAssigned", "SecondTermRevenueAmount" );
            dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
            dAmount = dAmount + dTempDecimal_1 + dTempDecimal_2;
         } 

         //:END
         //:IF qFAProf2.FinAidProfile.COA_OptionThirdTermFlag = "Y"
         if ( CompareAttributeToString( qFAProf2, "FinAidProfile", "COA_OptionThirdTermFlag", "Y" ) == 0 )
         { 
            //:dAmount = dAmount + qFAProf2.FinAidCOAItemAssigned.ThirdTermRevenueAmount
            {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                         GetDecimalFromAttribute( md_dTempDecimal_3, qFAProf2, "FinAidCOAItemAssigned", "ThirdTermRevenueAmount" );
            dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
            dAmount = dAmount + dTempDecimal_3;
         } 

         RESULT = SetCursorNextEntity( qFAProf2, "FinAidCOAItemAssigned", "" );
         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// Add other values together.
      //:FOR EACH qFAProf2.FinAidCOAItemAssigned
      RESULT = SetCursorFirstEntity( qFAProf2, "FinAidCOAItemAssigned", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 

         //:// 1st Term
         //:IF qFAProf.FinAidProfile.COA_OptionFirstTermFlag = "Y"
         if ( CompareAttributeToString( qFAProf, "FinAidProfile", "COA_OptionFirstTermFlag", "Y" ) == 0 )
         { 
            //:IF qFAProf2.FinAidCOAItemAssigned.FirstTermRevenueAmount != ""
            if ( CompareAttributeToString( qFAProf2, "FinAidCOAItemAssigned", "FirstTermRevenueAmount", "" ) != 0 )
            { 
               //:// Add FirstTermRevenueAmount 
               //:dAmount = dAmount + qFAProf2.FinAidCOAItemAssigned.FirstTermRevenueAmount 
               {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                               GetDecimalFromAttribute( md_dTempDecimal_4, qFAProf2, "FinAidCOAItemAssigned", "FirstTermRevenueAmount" );
               dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
               dAmount = dAmount + dTempDecimal_4;
               //:ELSE
            } 
            else
            { 
               //:// Add 50% of RevenueAmount
               //:dAmount = dAmount + (qFAProf2.FinAidCOAItemAssigned.RevenueAmount / 2)
               {MutableDouble md_dTempDecimal_5 = new MutableDouble( dTempDecimal_5 );
                               GetDecimalFromAttribute( md_dTempDecimal_5, qFAProf2, "FinAidCOAItemAssigned", "RevenueAmount" );
               dTempDecimal_5 = md_dTempDecimal_5.doubleValue( );}
               dAmount = dAmount + ( dTempDecimal_5 / 2 );
            } 

            //:END
         } 

         //:END

         //:// 2nd Term
         //:IF qFAProf.FinAidProfile.COA_OptionSecondTermFlag = "Y"
         if ( CompareAttributeToString( qFAProf, "FinAidProfile", "COA_OptionSecondTermFlag", "Y" ) == 0 )
         { 
            //:IF qFAProf2.FinAidCOAItemAssigned.SecondTermRevenueAmount != ""
            if ( CompareAttributeToString( qFAProf2, "FinAidCOAItemAssigned", "SecondTermRevenueAmount", "" ) != 0 )
            { 
               //:// Add SecondTermRevenueAmount 
               //:dAmount = dAmount + qFAProf2.FinAidCOAItemAssigned.SecondTermRevenueAmount 
               {MutableDouble md_dTempDecimal_6 = new MutableDouble( dTempDecimal_6 );
                               GetDecimalFromAttribute( md_dTempDecimal_6, qFAProf2, "FinAidCOAItemAssigned", "SecondTermRevenueAmount" );
               dTempDecimal_6 = md_dTempDecimal_6.doubleValue( );}
               dAmount = dAmount + dTempDecimal_6;
               //:ELSE
            } 
            else
            { 
               //:// Add 50% of RevenueAmount
               //:dAmount = dAmount + (qFAProf2.FinAidCOAItemAssigned.RevenueAmount / 2)
               {MutableDouble md_dTempDecimal_7 = new MutableDouble( dTempDecimal_7 );
                               GetDecimalFromAttribute( md_dTempDecimal_7, qFAProf2, "FinAidCOAItemAssigned", "RevenueAmount" );
               dTempDecimal_7 = md_dTempDecimal_7.doubleValue( );}
               dAmount = dAmount + ( dTempDecimal_7 / 2 );
            } 

            //:END
         } 

         //:END

         //:// 3rd Term
         //:IF qFAProf.FinAidProfile.COA_OptionThirdTermFlag = "Y"
         if ( CompareAttributeToString( qFAProf, "FinAidProfile", "COA_OptionThirdTermFlag", "Y" ) == 0 )
         { 
            //:// Always add ThirdTermRevenueAmount 
            //:dAmount = dAmount + qFAProf2.FinAidCOAItemAssigned.ThirdTermRevenueAmount 
            {MutableDouble md_dTempDecimal_8 = new MutableDouble( dTempDecimal_8 );
                         GetDecimalFromAttribute( md_dTempDecimal_8, qFAProf2, "FinAidCOAItemAssigned", "ThirdTermRevenueAmount" );
            dTempDecimal_8 = md_dTempDecimal_8.doubleValue( );}
            dAmount = dAmount + dTempDecimal_8;
         } 

         RESULT = SetCursorNextEntity( qFAProf2, "FinAidCOAItemAssigned", "" );
         //:END
      } 

      //:   
      //:END
   } 

   //:END
   //:ReturnedTotal = dAmount
   ReturnedTotal.setValue( dAmount );

   //:DropView( qFAProf2 )
   DropView( qFAProf2 );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:MergeTemplateText( VIEW MergeDataView,
//:                   STRING ( 10000 ) szTemplateText,
//:                   STRING ( 10000 ) szReturnedText )

//:   STRING ( 10 )  szEmbeddedImages
public int 
MergeTemplateText( View     MergeDataView,
                   String   szTemplateText,
                   StringBuilder   szReturnedText )
{
   String   szEmbeddedImages = null;
   //:STRING ( 200 ) szFileNameDir
   String   szFileNameDir = null;
   //:STRING ( 200 ) szFileNameTemplete
   String   szFileNameTemplete = null;
   //:STRING ( 200 ) szFileNameResult
   String   szFileNameResult = null;
   //:INTEGER       FileHandle
   int      FileHandle = 0;
   //:SHORT         nRC
   int      nRC = 0;


   //:// Merge data from a Template string into a Result string

   //:// Get Work file directory and add file names.
   //:GetApplDirectoryFromView( szFileNameDir, MergeDataView, zAPPL_DIR_LOCAL, 400 )
   {StringBuilder sb_szFileNameDir;
   if ( szFileNameDir == null )
      sb_szFileNameDir = new StringBuilder( 32 );
   else
      sb_szFileNameDir = new StringBuilder( szFileNameDir );
       GetApplDirectoryFromView( sb_szFileNameDir, MergeDataView, zAPPL_DIR_LOCAL, 400 );
   szFileNameDir = sb_szFileNameDir.toString( );}
   //:szFileNameTemplete = szFileNameDir + "\MergeDataTemplate.txt"
    {StringBuilder sb_szFileNameTemplete;
   if ( szFileNameTemplete == null )
      sb_szFileNameTemplete = new StringBuilder( 32 );
   else
      sb_szFileNameTemplete = new StringBuilder( szFileNameTemplete );
      ZeidonStringCopy( sb_szFileNameTemplete, 1, 0, szFileNameDir, 1, 0, 201 );
   szFileNameTemplete = sb_szFileNameTemplete.toString( );}
    {StringBuilder sb_szFileNameTemplete;
   if ( szFileNameTemplete == null )
      sb_szFileNameTemplete = new StringBuilder( 32 );
   else
      sb_szFileNameTemplete = new StringBuilder( szFileNameTemplete );
      ZeidonStringConcat( sb_szFileNameTemplete, 1, 0, "\\MergeDataTemplate.txt", 1, 0, 201 );
   szFileNameTemplete = sb_szFileNameTemplete.toString( );}
   //:szFileNameResult   = szFileNameDir + "\MergeDataResult.txt"
    {StringBuilder sb_szFileNameResult;
   if ( szFileNameResult == null )
      sb_szFileNameResult = new StringBuilder( 32 );
   else
      sb_szFileNameResult = new StringBuilder( szFileNameResult );
      ZeidonStringCopy( sb_szFileNameResult, 1, 0, szFileNameDir, 1, 0, 201 );
   szFileNameResult = sb_szFileNameResult.toString( );}
    {StringBuilder sb_szFileNameResult;
   if ( szFileNameResult == null )
      sb_szFileNameResult = new StringBuilder( 32 );
   else
      sb_szFileNameResult = new StringBuilder( szFileNameResult );
      ZeidonStringConcat( sb_szFileNameResult, 1, 0, "\\MergeDataResult.txt", 1, 0, 201 );
   szFileNameResult = sb_szFileNameResult.toString( );}

   //:FileHandle = SysOpenFile( MergeDataView, szFileNameTemplete, COREFILE_CREATE )
   try
   {
       FileHandle = m_KZOEP1AA.SysOpenFile( MergeDataView, szFileNameTemplete, COREFILE_CREATE );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:SysWriteLine( MergeDataView, FileHandle, szTemplateText  )
   try
   {
       m_KZOEP1AA.SysWriteLine( MergeDataView, FileHandle, szTemplateText );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:SysCloseFile( MergeDataView, FileHandle, 0 )
   try
   {
       m_KZOEP1AA.SysCloseFile( MergeDataView, FileHandle, null );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:nRC = MergeSingleRS_EntryWithTemplate( MergeDataView,
   //:                                       szFileNameTemplete,
   //:                                       szFileNameResult, 
   //:                                       szEmbeddedImages,
   //:                                       szReturnedText,
   //:                                       10000,   // Max length of returned text
   //:                                       1 )      // 1 is type text
   try
   {
       {
    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( MergeDataView );
    nRC = m_ZGLOBAL1_Operation.MergeSingleRS_EntryWithTemplate( MergeDataView, szFileNameTemplete, szFileNameResult, szEmbeddedImages, szReturnedText, 10000, 1 );
    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:MessageSend( MergeDataView, "", "Merge Template Data",
      //:             "An error occurred merging the data with the template data.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( MergeDataView, "", "Merge Template Data", "An error occurred merging the data with the template data.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:CreateSecuritySubSection( VIEW   mUGroups,
//:                          STRING ( 32 ) szDlgName,
//:                          STRING ( 32 ) szWndName,
//:                          STRING ( 32 ) szTabCtrl,
//:                          SHORT  nInitAllowAccess )


//:   VIEW    vDialog
private void 
o_CreateSecuritySubSection( View     mUGroups,
                            String   szDlgName,
                            String   szWndName,
                            String   szTabCtrl,
                            int      nInitAllowAccess )
{
   zVIEW    vDialog = new zVIEW( );
   //:INTEGER lControl1
   int      lControl1 = 0;
   //:INTEGER lControl2
   int      lControl2 = 0;
   //:STRING ( 256 ) szNameExt
   String   szNameExt = null;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;


   //:GetApplDirectoryFromView( szNameExt, mUGroups, zAPPL_DIR_OBJECT, 256 )
   {StringBuilder sb_szNameExt;
   if ( szNameExt == null )
      sb_szNameExt = new StringBuilder( 32 );
   else
      sb_szNameExt = new StringBuilder( szNameExt );
       GetApplDirectoryFromView( sb_szNameExt, mUGroups, zAPPL_DIR_OBJECT, 256 );
   szNameExt = sb_szNameExt.toString( );}
   //:szNameExt = szNameExt + szDlgName
    {StringBuilder sb_szNameExt;
   if ( szNameExt == null )
      sb_szNameExt = new StringBuilder( 32 );
   else
      sb_szNameExt = new StringBuilder( szNameExt );
      ZeidonStringConcat( sb_szNameExt, 1, 0, szDlgName, 1, 0, 257 );
   szNameExt = sb_szNameExt.toString( );}
   //:szNameExt = szNameExt + ".xwd"
    {StringBuilder sb_szNameExt;
   if ( szNameExt == null )
      sb_szNameExt = new StringBuilder( 32 );
   else
      sb_szNameExt = new StringBuilder( szNameExt );
      ZeidonStringConcat( sb_szNameExt, 1, 0, ".xwd", 1, 0, 257 );
   szNameExt = sb_szNameExt.toString( );}

   //:// Activate using SfActivateSysOI_FromFile so that the kzwdlgxo.xod
   //:// does not have to be in the application LPLR.
   //:nRC = SfActivateSysOI_FromFile( vDialog, "KZWDLGXO", mUGroups,
   //:                                szNameExt, zSINGLE )
   nRC = SfActivateSysOI_FromFile( vDialog, "KZWDLGXO", mUGroups, szNameExt, zSINGLE );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 

      //:lControl1 = zQUAL_STRING + zPOS_FIRST
      lControl1 = zQUAL_STRING + zPOS_FIRST;
      //:lControl2 = zQUAL_STRING + zPOS_FIRST + zRECURS
      lControl2 = zQUAL_STRING + zPOS_FIRST + zRECURS;
      //:IF SetEntityCursor( vDialog, "Wnd", "Tag", lControl1,
      //:                    szWndName, "", "", 0,
      //:                    "Dlg", "" ) >= zCURSOR_SET AND
      //:   SetEntityCursor( vDialog, "Ctrl", "Tag", lControl2,
      //:                    szTabCtrl, "", "", 0,
      //:                    "Wnd", "" ) >= zCURSOR_SET
      lTempInteger_0 = SetEntityCursor( vDialog, "Wnd", "Tag", lControl1, szWndName, "", "", 0, "Dlg", "" );
      lTempInteger_1 = SetEntityCursor( vDialog, "Ctrl", "Tag", lControl2, szTabCtrl, "", "", 0, "Wnd", "" );
      if ( lTempInteger_0 >= zCURSOR_SET && lTempInteger_1 >= zCURSOR_SET )
      { 

         //:FOR EACH  vDialog.CtrlCtrl
         RESULT = SetCursorFirstEntity( vDialog, "CtrlCtrl", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 

            //:CREATE ENTITY  mUGroups.SecuritySubSection 
            RESULT = CreateEntity( mUGroups, "SecuritySubSection", zPOS_AFTER );
            //:mUGroups.SecuritySubSection.SubSectionName = vDialog.CtrlCtrl.Tag
            SetAttributeFromAttribute( mUGroups, "SecuritySubSection", "SubSectionName", vDialog, "CtrlCtrl", "Tag" );
            //:IF nInitAllowAccess = 1
            if ( nInitAllowAccess == 1 )
            { 
               //:mUGroups.SecuritySubSection.AllowAccess ="N"  
               SetAttributeFromString( mUGroups, "SecuritySubSection", "AllowAccess", "N" );
            } 

            RESULT = SetCursorNextEntity( vDialog, "CtrlCtrl", "" );
            //:END
         } 


         //:END
      } 

      //:END

      //:DropObjectInstance( vDialog )
      DropObjectInstance( vDialog );
   } 

   //:END
   return;
// END
} 


//:LOCAL OPERATION
public int 
CreateSecurityAdminSubSection( View     mUGroups )
{
   int      RESULT = 0;

   //:CreateSecurityAdminSubSection( VIEW mUGroups BASED ON LOD mUGroups )

   //:// Build the SubSection entries based on the FunctionalArea for the Adminsitrative Section.

   //:IF mUGroups.SecurityArea.FunctionalArea = "GeneralLedger"
   if ( CompareAttributeToString( mUGroups, "SecurityArea", "FunctionalArea", "GeneralLedger" ) == 0 )
   { 
      //:CREATE ENTITY  mUGroups.SecuritySubSection
      RESULT = CreateEntity( mUGroups, "SecuritySubSection", zPOS_AFTER );
      //:mUGroups.SecuritySubSection.SubSectionName = "FiscalYear"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "SubSectionName", "FiscalYear" );
      //:mUGroups.SecuritySubSection.AllowAccess ="N"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "AllowAccess", "N" );

      //:CREATE ENTITY  mUGroups.SecuritySubSection
      RESULT = CreateEntity( mUGroups, "SecuritySubSection", zPOS_AFTER );
      //:mUGroups.SecuritySubSection.SubSectionName = "Accounts"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "SubSectionName", "Accounts" );
      //:mUGroups.SecuritySubSection.AllowAccess ="N"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "AllowAccess", "N" );

      //:CREATE ENTITY  mUGroups.SecuritySubSection
      RESULT = CreateEntity( mUGroups, "SecuritySubSection", zPOS_AFTER );
      //:mUGroups.SecuritySubSection.SubSectionName = "Funds"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "SubSectionName", "Funds" );
      //:mUGroups.SecuritySubSection.AllowAccess ="N"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "AllowAccess", "N" );

      //:CREATE ENTITY  mUGroups.SecuritySubSection
      RESULT = CreateEntity( mUGroups, "SecuritySubSection", zPOS_AFTER );
      //:mUGroups.SecuritySubSection.SubSectionName = "CostCenters"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "SubSectionName", "CostCenters" );
      //:mUGroups.SecuritySubSection.AllowAccess ="N"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "AllowAccess", "N" );

      //:CREATE ENTITY  mUGroups.SecuritySubSection
      RESULT = CreateEntity( mUGroups, "SecuritySubSection", zPOS_AFTER );
      //:mUGroups.SecuritySubSection.SubSectionName = "Divisions"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "SubSectionName", "Divisions" );
      //:mUGroups.SecuritySubSection.AllowAccess ="N"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "AllowAccess", "N" );

      //:CREATE ENTITY  mUGroups.SecuritySubSection
      RESULT = CreateEntity( mUGroups, "SecuritySubSection", zPOS_AFTER );
      //:mUGroups.SecuritySubSection.SubSectionName = "ProjectCodes"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "SubSectionName", "ProjectCodes" );
      //:mUGroups.SecuritySubSection.AllowAccess ="N"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "AllowAccess", "N" );

      //:CREATE ENTITY  mUGroups.SecuritySubSection
      RESULT = CreateEntity( mUGroups, "SecuritySubSection", zPOS_AFTER );
      //:mUGroups.SecuritySubSection.SubSectionName = "Set Defaults"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "SubSectionName", "Set Defaults" );
      //:mUGroups.SecuritySubSection.AllowAccess ="N"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "AllowAccess", "N" );

      //:CREATE ENTITY  mUGroups.SecuritySubSection
      RESULT = CreateEntity( mUGroups, "SecuritySubSection", zPOS_AFTER );
      //:mUGroups.SecuritySubSection.SubSectionName = "GenerateBudgets"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "SubSectionName", "GenerateBudgets" );
      //:mUGroups.SecuritySubSection.AllowAccess ="N"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "AllowAccess", "N" );

      //:CREATE ENTITY  mUGroups.SecuritySubSection
      RESULT = CreateEntity( mUGroups, "SecuritySubSection", zPOS_AFTER );
      //:mUGroups.SecuritySubSection.SubSectionName = "AnalyzeBudgets"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "SubSectionName", "AnalyzeBudgets" );
      //:mUGroups.SecuritySubSection.AllowAccess ="N"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "AllowAccess", "N" );

      //:CREATE ENTITY  mUGroups.SecuritySubSection
      RESULT = CreateEntity( mUGroups, "SecuritySubSection", zPOS_AFTER );
      //:mUGroups.SecuritySubSection.SubSectionName = "RecurringTransactions"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "SubSectionName", "RecurringTransactions" );
      //:mUGroups.SecuritySubSection.AllowAccess ="N"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "AllowAccess", "N" );

      //:CREATE ENTITY  mUGroups.SecuritySubSection
      RESULT = CreateEntity( mUGroups, "SecuritySubSection", zPOS_AFTER );
      //:mUGroups.SecuritySubSection.SubSectionName = "TransferFromSA"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "SubSectionName", "TransferFromSA" );
      //:mUGroups.SecuritySubSection.AllowAccess ="N"
      SetAttributeFromString( mUGroups, "SecuritySubSection", "AllowAccess", "N" );
   } 

   //:END
   return( 0 );
//    
// END
} 


//:GLOBAL OPERATION
//:CREATE_SecuritySubSections_G( VIEW   mUGroups BASED ON LOD mUGroups,
//:                              SHORT  nInitAllowAccess )

//:   STRING ( 1 ) szAllowAccess
public int 
CREATE_SecuritySubSections_G( View     mUGroups,
                              int      nInitAllowAccess )
{
   String   szAllowAccess = null;
   //:SHORT nRC_Change
   int      nRC_Change = 0;


   //:nRC_Change = 0    // Initialize Return Code to indicate no changes were made.
   nRC_Change = 0;

   //:IF nInitAllowAccess = 1
   if ( nInitAllowAccess == 1 )
   { 
      //:szAllowAccess = "N"  
       {StringBuilder sb_szAllowAccess;
      if ( szAllowAccess == null )
         sb_szAllowAccess = new StringBuilder( 32 );
      else
         sb_szAllowAccess = new StringBuilder( szAllowAccess );
            ZeidonStringCopy( sb_szAllowAccess, 1, 0, "N", 1, 0, 2 );
      szAllowAccess = sb_szAllowAccess.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szAllowAccess = ""  
       {StringBuilder sb_szAllowAccess;
      if ( szAllowAccess == null )
         sb_szAllowAccess = new StringBuilder( 32 );
      else
         sb_szAllowAccess = new StringBuilder( szAllowAccess );
            ZeidonStringCopy( sb_szAllowAccess, 1, 0, "", 1, 0, 2 );
      szAllowAccess = sb_szAllowAccess.toString( );}
   } 

   //:END

   //:IF mUGroups.SecuritySection.SectionName = "Prospects"
   if ( CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Prospects" ) == 0 )
   { 
      //:IF mUGroups.SecurityArea.FunctionalArea = "Admissions"
      if ( CompareAttributeToString( mUGroups, "SecurityArea", "FunctionalArea", "Admissions" ) == 0 )
      { 
         //:nRC_Change = 1
         nRC_Change = 1;
         //:CreateSecuritySubSection( mUGroups, "mAdmitD", "ProspectDetail",
         //:                          "TBDetails", nInitAllowAccess )
         o_CreateSecuritySubSection( mUGroups, "mAdmitD", "ProspectDetail", "TBDetails", nInitAllowAccess );
         //:ELSE
      } 
      else
      { 
         //:nRC_Change = 1
         nRC_Change = 1;
         //:CreateSecuritySubSection( mUGroups, "mProsptD", "ProspectDetail",
         //:                          "TBDetails", nInitAllowAccess )
         o_CreateSecuritySubSection( mUGroups, "mProsptD", "ProspectDetail", "TBDetails", nInitAllowAccess );
      } 

      //:END
   } 

   //:END

   //:IF mUGroups.SecuritySection.SectionName = "Churches"
   if ( CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Churches" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mChurchD", "ChurchDetail",
      //:                          "TBChurch", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mChurchD", "ChurchDetail", "TBChurch", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecuritySection.SectionName = "Classes"
   if ( CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Classes" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mClassD", "ClassDetail",
      //:                          "TBClasses", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mClassD", "ClassDetail", "TBClasses", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecuritySection.SectionName = "Cohorts"
   if ( CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Cohorts" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mCohortD", "CohortDetail",
      //:                          "Tab1", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mCohortD", "CohortDetail", "Tab1", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecuritySection.SectionName = "Courses"
   if ( CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Courses" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mCourseD", "CourseDetail",
      //:                          "Tab1", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mCourseD", "CourseDetail", "Tab1", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecuritySection.SectionName = "Organizations"
   if ( CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Organizations" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mOrgD", "OrganizationDetail",
      //:                          "TBOrg", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mOrgD", "OrganizationDetail", "TBOrg", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecuritySection.SectionName = "Donors"
   if ( CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Donors" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mPersonD", "PersonDetail",
      //:                       "TBPerson", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mPersonD", "PersonDetail", "TBPerson", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecuritySection.SectionName = "Alumni"
   if ( CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Alumni" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mPersonD", "PersonDetail",
      //:                          "TBPerson", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mPersonD", "PersonDetail", "TBPerson", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecuritySection.SectionName = "Events"
   if ( CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Events" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mPlanD", "EventDetail",
      //:                          "Tab1", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mPlanD", "EventDetail", "Tab1", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecuritySection.SectionName = "Schools"
   if ( CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Schools" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mSchoolD", "SchoolDetail",
      //:                       "TBChurch", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mSchoolD", "SchoolDetail", "TBChurch", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecurityArea.FunctionalArea = "Registrar" AND 
   //:   mUGroups.SecuritySection.SectionName = "Students"
   if ( CompareAttributeToString( mUGroups, "SecurityArea", "FunctionalArea", "Registrar" ) == 0 && CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Students" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mStudntD", "StudentDetail",
      //:                          "TBAcademic", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mStudntD", "StudentDetail", "TBAcademic", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecurityArea.FunctionalArea = "StudentServices" AND 
   //:   mUGroups.SecuritySection.SectionName = "Students"
   if ( CompareAttributeToString( mUGroups, "SecurityArea", "FunctionalArea", "StudentServices" ) == 0 && CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Students" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mStudntD", "StudentServicesDetail",
      //:                          "TBAcademic", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mStudntD", "StudentServicesDetail", "TBAcademic", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecurityArea.FunctionalArea = "Retention" AND 
   //:   mUGroups.SecuritySection.SectionName = "Students"
   if ( CompareAttributeToString( mUGroups, "SecurityArea", "FunctionalArea", "Retention" ) == 0 && CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Students" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mStudntD", "StudentRetentionDetail",
      //:                          "TBAcademic", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mStudntD", "StudentRetentionDetail", "TBAcademic", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecurityArea.FunctionalArea = "FinancialAid" AND 
   //:   mUGroups.SecuritySection.SectionName = "Students"
   if ( CompareAttributeToString( mUGroups, "SecurityArea", "FunctionalArea", "FinancialAid" ) == 0 && CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Students" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mFAStuD", "StudentDetail",
      //:                          "TBAcademic", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mFAStuD", "StudentDetail", "TBAcademic", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecurityArea.FunctionalArea = "FinancialAid" AND 
   //:   mUGroups.SecuritySection.SectionName = "FinAidSources"
   if ( CompareAttributeToString( mUGroups, "SecurityArea", "FunctionalArea", "FinancialAid" ) == 0 && CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "FinAidSources" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mFinAidD", "FinAidSourceDetail",
      //:                          "TBFinAidSrcDetail", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mFinAidD", "FinAidSourceDetail", "TBFinAidSrcDetail", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecurityArea.FunctionalArea = "StudentAccounts" AND 
   //:   mUGroups.SecuritySection.SectionName = "Students"
   if ( CompareAttributeToString( mUGroups, "SecurityArea", "FunctionalArea", "StudentAccounts" ) == 0 && CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Students" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mSAStuD", "StudentDetail",
      //:                          "TBStudentAccounts", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mSAStuD", "StudentDetail", "TBStudentAccounts", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecuritySection.SectionName = "Persons"
   if ( CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Persons" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mPersonD", "PersonDetail",
      //:                          "TBPerson", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mPersonD", "PersonDetail", "TBPerson", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecuritySection.SectionName = "Employees"
   if ( CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "Employees" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mEmployD", "EmployeeDetail",
      //:                          "TBEmployee", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mEmployD", "EmployeeDetail", "TBEmployee", nInitAllowAccess );
   } 

   //:END

   //:IF mUGroups.SecuritySection.SectionName = "OtherCharges"
   if ( CompareAttributeToString( mUGroups, "SecuritySection", "SectionName", "OtherCharges" ) == 0 )
   { 
      //:nRC_Change = 1
      nRC_Change = 1;
      //:CreateSecuritySubSection( mUGroups, "mAcctSTD", "OtherCharges",
      //:                          "TBOtherCharges", nInitAllowAccess )
      o_CreateSecuritySubSection( mUGroups, "mAcctSTD", "OtherCharges", "TBOtherCharges", nInitAllowAccess );
   } 

   //:END

   //:RETURN nRC_Change
   return( nRC_Change );
// END
} 


//:GLOBAL OPERATION
public int 
CREATE_MenuSecuritySubSection( View     mUGroups,
                               String   szSubSectionName,
                               int      nInitAllowAccess )
{
   int      RESULT = 0;

   //:CREATE_MenuSecuritySubSection( VIEW mUGroups BASED ON LOD mUGroups,
   //:                            STRING ( 32 ) szSubSectionName,
   //:                            SHORT  nInitAllowAccess )

   //:// Create Menu Bar Security entry.
   //:CREATE ENTITY  mUGroups.SecuritySubSection 
   RESULT = CreateEntity( mUGroups, "SecuritySubSection", zPOS_AFTER );
   //:mUGroups.SecuritySubSection.SubSectionName = szSubSectionName
   SetAttributeFromString( mUGroups, "SecuritySubSection", "SubSectionName", szSubSectionName );
   //:IF nInitAllowAccess = 1
   if ( nInitAllowAccess == 1 )
   { 
      //:mUGroups.SecuritySubSection.AllowAccess ="N"  
      SetAttributeFromString( mUGroups, "SecuritySubSection", "AllowAccess", "N" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ValidateEmailValues( VIEW mUser BASED ON LOD mUser,
//:                     STRING ( 255 ) szSMTPServer,
//:                     STRING ( 255 ) szUserEmailAddress,
//:                     STRING ( 255 ) szEmailUserName,
//:                     STRING ( 255 ) szEmailPassword )
//:   
//:   VIEW sHost BASED ON LOD sHost
public int 
ValidateEmailValues( View     mUser,
                     StringBuilder   szSMTPServer,
                     StringBuilder   szUserEmailAddress,
                     StringBuilder   szEmailUserName,
                     StringBuilder   szEmailPassword )
{
   zVIEW    sHost = new zVIEW( );
   //:STRING ( 200 ) Msg
   String   Msg = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:// This operation is here instead of in mUser because it is called from operations in this DLL and having
   //:// it here avoids DLL locks.

   //:// Make sure Email information has been specified and return values.

   //:GetViewByName( sHost, "sHost", mUser, zLEVEL_APPLICATION )
   GetViewByName( sHost, "sHost", mUser, zLEVEL_APPLICATION );

   //:szSMTPServer = mUser.User.SMTPServer
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetVariableFromAttribute( szSMTPServer, mi_lTempInteger_0, 'S', 256, mUser, "User", "SMTPServer", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   //:IF szSMTPServer = ""
   if ( ZeidonStringCompare( szSMTPServer.toString( ), 1, 0, "", 1, 0, 256 ) == 0 )
   { 
      //:szSMTPServer = sHost.Host.SMTPServer
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetVariableFromAttribute( szSMTPServer, mi_lTempInteger_1, 'S', 256, sHost, "Host", "SMTPServer", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   } 

   //:END
   //:IF szSMTPServer = ""
   if ( ZeidonStringCompare( szSMTPServer.toString( ), 1, 0, "", 1, 0, 256 ) == 0 )
   { 
      //:Msg = "An SMTP server must be specified on the Host before email can be used."
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringCopy( sb_Msg, 1, 0, "An SMTP server must be specified on the Host before email can be used.", 1, 0, 201 );
      Msg = sb_Msg.toString( );}
      //:MessageSend( mUser, "", "Send Email", Msg,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mUser, "", "Send Email", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:szUserEmailAddress = mUser.User.eMailUserName
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetVariableFromAttribute( szUserEmailAddress, mi_lTempInteger_2, 'S', 256, mUser, "User", "eMailUserName", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   //:IF szUserEmailAddress = ""
   if ( ZeidonStringCompare( szUserEmailAddress.toString( ), 1, 0, "", 1, 0, 256 ) == 0 )
   { 
      //:Msg = "The sender's email address must be specified on the person record before email can be used."
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringCopy( sb_Msg, 1, 0, "The sender's email address must be specified on the person record before email can be used.", 1, 0, 201 );
      Msg = sb_Msg.toString( );}
      //:MessageSend( mUser, "", "Send Email", Msg,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mUser, "", "Send Email", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:szEmailUserName = mUser.User.eMailUserName
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetVariableFromAttribute( szEmailUserName, mi_lTempInteger_3, 'S', 256, mUser, "User", "eMailUserName", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   //:IF szEmailUserName = ""
   if ( ZeidonStringCompare( szEmailUserName.toString( ), 1, 0, "", 1, 0, 256 ) == 0 )
   { 
      //:Msg = "The sender's email user name must be specified on the System Admin USER record before email can be used."
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringCopy( sb_Msg, 1, 0, "The sender's email user name must be specified on the System Admin USER record before email can be used.", 1, 0, 201 );
      Msg = sb_Msg.toString( );}
      //:MessageSend( mUser, "", "Send Email", Msg,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mUser, "", "Send Email", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// szEMailPassword = mUser.User.eMailPassword
   //:GetStringFromAttributeByContext( szEmailPassword, mUser, "User",
   //:                                 "eMailPassword", "PasswordUL", 256 )
   GetStringFromAttributeByContext( szEmailPassword, mUser, "User", "eMailPassword", "PasswordUL", 256 );
   //:IF szEmailPassword = ""
   if ( ZeidonStringCompare( szEmailPassword.toString( ), 1, 0, "", 1, 0, 256 ) == 0 )
   { 
      //:Msg = "The sender's email password must be specified on the System Admin USER record before email can be used."
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringCopy( sb_Msg, 1, 0, "The sender's email password must be specified on the System Admin USER record before email can be used.", 1, 0, 201 );
      Msg = sb_Msg.toString( );}
      //:MessageSend( mUser, "", "Send Email", Msg,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mUser, "", "Send Email", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:SetCL_PositionalValue( VIEW AnyView,
//:                       STRING ( 4000 ) szLine,
//:                       STRING ( 1000 ) szValueIn,
//:                       INTEGER TargetLength,
//:                       STRING ( 1 ) szDataType )

//:   STRING ( 1000 ) szValueOut
public int 
SetCL_PositionalValue( View     AnyView,
                       String   szLine,
                       String   szValueIn,
                       int      TargetLength,
                       String   szDataType )
{
   String   szValueOut = null;
   //:STRING (  5 )   szZipCode5
   String   szZipCode5 = null;
   //:STRING (  4 )   szZipCode4
   String   szZipCode4 = null;
   //:SHORT  SourceLength
   int      SourceLength = 0;
   //:SHORT  PadLength
   int      PadLength = 0;


   //:// Set the Input string value at the end of the Output line for the length specified.
   //:// Make sure that we pad the output value with zeros or blanks, depending on data type.
   //:// Regular strings get padded with blanks on the right.
   //:// Numbers get padded with zeros on the left.
   //:// ZipCodes get padded with zeros on the right.
   //:IF TargetLength > 1000
   if ( TargetLength > 1000 )
   { 
      //:MessageSend( AnyView, "", "Set string by Positional Value",
      //:             "Length cannot exceed 1000 characters.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( AnyView, "", "Set string by Positional Value", "Length cannot exceed 1000 characters.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:SourceLength = zstrlen( szValueIn )
   SourceLength = zstrlen( szValueIn );
   //:PadLength = TargetLength - SourceLength
   PadLength = TargetLength - SourceLength;
   //:IF szDataType = "N" OR szDataType = "E" OR szDataType = "D"
   if ( ZeidonStringCompare( szDataType, 1, 0, "N", 1, 0, 2 ) == 0 || ZeidonStringCompare( szDataType, 1, 0, "E", 1, 0, 2 ) == 0 || ZeidonStringCompare( szDataType, 1, 0, "D", 1, 0, 2 ) == 0 )
   { 
      //:// Number field, which is padded with zeros on the left.
      //:szValueOut = ""
       {StringBuilder sb_szValueOut;
      if ( szValueOut == null )
         sb_szValueOut = new StringBuilder( 32 );
      else
         sb_szValueOut = new StringBuilder( szValueOut );
            ZeidonStringCopy( sb_szValueOut, 1, 0, "", 1, 0, 1001 );
      szValueOut = sb_szValueOut.toString( );}
      //:LOOP WHILE PadLength > 0
      while ( PadLength > 0 )
      { 
         //:szValueOut = szValueOut + "0"
          {StringBuilder sb_szValueOut;
         if ( szValueOut == null )
            sb_szValueOut = new StringBuilder( 32 );
         else
            sb_szValueOut = new StringBuilder( szValueOut );
                  ZeidonStringConcat( sb_szValueOut, 1, 0, "0", 1, 0, 1001 );
         szValueOut = sb_szValueOut.toString( );}
         //:PadLength = PadLength - 1
         PadLength = PadLength - 1;
      } 

      //:END
      //:szValueOut = szValueOut + szValueIn
       {StringBuilder sb_szValueOut;
      if ( szValueOut == null )
         sb_szValueOut = new StringBuilder( 32 );
      else
         sb_szValueOut = new StringBuilder( szValueOut );
            ZeidonStringConcat( sb_szValueOut, 1, 0, szValueIn, 1, 0, 1001 );
      szValueOut = sb_szValueOut.toString( );}
      //:ELSE
   } 
   else
   { 
      //:IF szDataType = "Z"
      if ( ZeidonStringCompare( szDataType, 1, 0, "Z", 1, 0, 2 ) == 0 )
      { 
         //:// Zip Code.
         //:IF SourceLength = 0
         if ( SourceLength == 0 )
         { 
            //:szValueOut = "000000000"
             {StringBuilder sb_szValueOut;
            if ( szValueOut == null )
               sb_szValueOut = new StringBuilder( 32 );
            else
               sb_szValueOut = new StringBuilder( szValueOut );
                        ZeidonStringCopy( sb_szValueOut, 1, 0, "000000000", 1, 0, 1001 );
            szValueOut = sb_szValueOut.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF SourceLength = 5
            if ( SourceLength == 5 )
            { 
               //:szValueOut = szValueIn + "0000"
                {StringBuilder sb_szValueOut;
               if ( szValueOut == null )
                  sb_szValueOut = new StringBuilder( 32 );
               else
                  sb_szValueOut = new StringBuilder( szValueOut );
                              ZeidonStringCopy( sb_szValueOut, 1, 0, szValueIn, 1, 0, 1001 );
               szValueOut = sb_szValueOut.toString( );}
                {StringBuilder sb_szValueOut;
               if ( szValueOut == null )
                  sb_szValueOut = new StringBuilder( 32 );
               else
                  sb_szValueOut = new StringBuilder( szValueOut );
                              ZeidonStringConcat( sb_szValueOut, 1, 0, "0000", 1, 0, 1001 );
               szValueOut = sb_szValueOut.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szValueOut = szValueIn
                {StringBuilder sb_szValueOut;
               if ( szValueOut == null )
                  sb_szValueOut = new StringBuilder( 32 );
               else
                  sb_szValueOut = new StringBuilder( szValueOut );
                              ZeidonStringCopy( sb_szValueOut, 1, 0, szValueIn, 1, 0, 1001 );
               szValueOut = sb_szValueOut.toString( );}
            } 

            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// The default is simple text padded with blanks on the right.
         //:szValueOut = szValueIn
          {StringBuilder sb_szValueOut;
         if ( szValueOut == null )
            sb_szValueOut = new StringBuilder( 32 );
         else
            sb_szValueOut = new StringBuilder( szValueOut );
                  ZeidonStringCopy( sb_szValueOut, 1, 0, szValueIn, 1, 0, 1001 );
         szValueOut = sb_szValueOut.toString( );}
         //:LOOP WHILE PadLength > 0
         while ( PadLength > 0 )
         { 
            //:szValueOut = szValueOut + " "
             {StringBuilder sb_szValueOut;
            if ( szValueOut == null )
               sb_szValueOut = new StringBuilder( 32 );
            else
               sb_szValueOut = new StringBuilder( szValueOut );
                        ZeidonStringConcat( sb_szValueOut, 1, 0, " ", 1, 0, 1001 );
            szValueOut = sb_szValueOut.toString( );}
            //:PadLength = PadLength - 1
            PadLength = PadLength - 1;
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:// Add resulting string to end of szLine.
   //:szLine = szLine + szValueOut
    {StringBuilder sb_szLine;
   if ( szLine == null )
      sb_szLine = new StringBuilder( 32 );
   else
      sb_szLine = new StringBuilder( szLine );
      ZeidonStringConcat( sb_szLine, 1, 0, szValueOut, 1, 0, 4001 );
   szLine = sb_szLine.toString( );}
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:SetAttributeByCL_PositionalValue( VIEW AnyView,
//:                                  STRING ( 32 )    szEntityName,
//:                                  STRING ( 32 )    szAttributeName,
//:                                  STRING ( 4000 ) szLine,
//:                                  INTEGER StartPosition,
//:                                  INTEGER Length,
//:                                  STRING ( 1 ) szDataType )

//:   STRING ( 1000 )  szValueIn
public int 
SetAttributeByCL_PositionalValue( View     AnyView,
                                  String   szEntityName,
                                  String   szAttributeName,
                                  String   szLine,
                                  int      StartPosition,
                                  int      Length,
                                  String   szDataType )
{
   String   szValueIn = null;
   //:STRING ( 1000 )  szValueOut
   String   szValueOut = null;
   //:STRING ( 5 )     szZipCode5
   String   szZipCode5 = null;
   //:STRING ( 4 )     szZipCode4
   String   szZipCode4 = null;
   //:STRING ( 3 )     szDate3
   String   szDate3 = null;
   //:INTEGER          IntegerValue
   int      IntegerValue = 0;
   //:DECIMAL          DecimalValue1
   double  DecimalValue1 = 0.0;
   //:DECIMAL          DecimalValue2
   double  DecimalValue2 = 0.0;


   //:// Set the attribute from a string identified by position and length.
   //:// Make sure that we trim any blanks off the end of the string.
   //:// Since this is a CommonLine attribute, make sure that any nulls are filled with blanks.
   //:IF Length > 1000
   if ( Length > 1000 )
   { 
      //:MessageSend( AnyView, "", "Set Attribute by Positional Value",
      //:             "Length cannot exceed 1000 characters.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( AnyView, "", "Set Attribute by Positional Value", "Length cannot exceed 1000 characters.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   //:szValueIn = szLine[ StartPosition: Length ]
    {StringBuilder sb_szValueIn;
   if ( szValueIn == null )
      sb_szValueIn = new StringBuilder( 32 );
   else
      sb_szValueIn = new StringBuilder( szValueIn );
      ZeidonStringCopy( sb_szValueIn, 1, 0, szLine, StartPosition, Length, 1001 );
   szValueIn = sb_szValueIn.toString( );}
   //:zRTrim( szValueIn, szValueOut, 1000 )
   {StringBuilder sb_szValueOut;
   if ( szValueOut == null )
      sb_szValueOut = new StringBuilder( 32 );
   else
      sb_szValueOut = new StringBuilder( szValueOut );
       zRTrim( szValueIn, sb_szValueOut, 1000 );
   szValueOut = sb_szValueOut.toString( );}
   //:IF szDataType = "Z"
   if ( ZeidonStringCompare( szDataType, 1, 0, "Z", 1, 0, 2 ) == 0 )
   { 
      //:// Zip Code for 9 Digits
      //:szZipCode5 = szValueOut[1:5]
       {StringBuilder sb_szZipCode5;
      if ( szZipCode5 == null )
         sb_szZipCode5 = new StringBuilder( 32 );
      else
         sb_szZipCode5 = new StringBuilder( szZipCode5 );
            ZeidonStringCopy( sb_szZipCode5, 1, 0, szValueOut, 1, 5, 6 );
      szZipCode5 = sb_szZipCode5.toString( );}
      //:szZipCode4 = szValueOut[6:4]
       {StringBuilder sb_szZipCode4;
      if ( szZipCode4 == null )
         sb_szZipCode4 = new StringBuilder( 32 );
      else
         sb_szZipCode4 = new StringBuilder( szZipCode4 );
            ZeidonStringCopy( sb_szZipCode4, 1, 0, szValueOut, 6, 4, 5 );
      szZipCode4 = sb_szZipCode4.toString( );}
      //:IF szZipCode5 = "00000"
      if ( ZeidonStringCompare( szZipCode5, 1, 0, "00000", 1, 0, 6 ) == 0 )
      { 
         //:szValueOut = ""
          {StringBuilder sb_szValueOut;
         if ( szValueOut == null )
            sb_szValueOut = new StringBuilder( 32 );
         else
            sb_szValueOut = new StringBuilder( szValueOut );
                  ZeidonStringCopy( sb_szValueOut, 1, 0, "", 1, 0, 1001 );
         szValueOut = sb_szValueOut.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szValueOut = szZipCode5
          {StringBuilder sb_szValueOut;
         if ( szValueOut == null )
            sb_szValueOut = new StringBuilder( 32 );
         else
            sb_szValueOut = new StringBuilder( szValueOut );
                  ZeidonStringCopy( sb_szValueOut, 1, 0, szZipCode5, 1, 0, 1001 );
         szValueOut = sb_szValueOut.toString( );}
         //:IF szZipCode4 != "0000"
         if ( ZeidonStringCompare( szZipCode4, 1, 0, "0000", 1, 0, 5 ) != 0 )
         { 
            //:szValueOut = szZipCode5 + szZipCode4
             {StringBuilder sb_szValueOut;
            if ( szValueOut == null )
               sb_szValueOut = new StringBuilder( 32 );
            else
               sb_szValueOut = new StringBuilder( szValueOut );
                        ZeidonStringCopy( sb_szValueOut, 1, 0, szZipCode5, 1, 0, 1001 );
            szValueOut = sb_szValueOut.toString( );}
             {StringBuilder sb_szValueOut;
            if ( szValueOut == null )
               sb_szValueOut = new StringBuilder( 32 );
            else
               sb_szValueOut = new StringBuilder( szValueOut );
                        ZeidonStringConcat( sb_szValueOut, 1, 0, szZipCode4, 1, 0, 1001 );
            szValueOut = sb_szValueOut.toString( );}
         } 

         //:END
      } 

      //:END
      //:SetAttributeFromString( AnyView, szEntityName, szAttributeName, szValueOut )
      SetAttributeFromString( AnyView, szEntityName, szAttributeName, szValueOut );
      //:ELSE
   } 
   else
   { 
      //:IF szDataType = "N"
      if ( ZeidonStringCompare( szDataType, 1, 0, "N", 1, 0, 2 ) == 0 )
      { 
         //:// Whole Number
         //:IntegerValue = zStringToInteger( szValueIn )
         IntegerValue = zStringToInteger( szValueIn );
         //:SetAttributeFromInteger( AnyView, szEntityName, szAttributeName, IntegerValue )
         SetAttributeFromInteger( AnyView, szEntityName, szAttributeName, IntegerValue );
         //:ELSE
      } 
      else
      { 
         //:IF szDataType = "D"
         if ( ZeidonStringCompare( szDataType, 1, 0, "D", 1, 0, 2 ) == 0 )
         { 
            //:// Decimal with two decimal digits.
            //:IntegerValue = zStringToInteger( szValueOut ) 
            IntegerValue = zStringToInteger( szValueOut );
            //:DecimalValue1 = IntegerValue
            DecimalValue1 = IntegerValue;
            //:DecimalValue2 = DecimalValue1 / 100
            DecimalValue2 = DecimalValue1 / 100;
            //:SetAttributeFromDecimal( AnyView, szEntityName, szAttributeName, DecimalValue2 )
            SetAttributeFromDecimal( AnyView, szEntityName, szAttributeName, DecimalValue2 );
            //:ELSE
         } 
         else
         { 
            //:IF szDataType = "E"
            if ( ZeidonStringCompare( szDataType, 1, 0, "E", 1, 0, 2 ) == 0 )
            { 
               //:// Date. Don't set if has zeros. Since number of zeros differs, we'll only look at first three characters.
               //:szDate3 = szValueIn
                {StringBuilder sb_szDate3;
               if ( szDate3 == null )
                  sb_szDate3 = new StringBuilder( 32 );
               else
                  sb_szDate3 = new StringBuilder( szDate3 );
                              ZeidonStringCopy( sb_szDate3, 1, 0, szValueIn, 1, 0, 4 );
               szDate3 = sb_szDate3.toString( );}
               //:IF szDate3 != "000" AND szDate3 != ""
               if ( ZeidonStringCompare( szDate3, 1, 0, "000", 1, 0, 4 ) != 0 && ZeidonStringCompare( szDate3, 1, 0, "", 1, 0, 4 ) != 0 )
               { 
                  //:SetAttributeFromString( AnyView, szEntityName, szAttributeName, szValueOut )
                  SetAttributeFromString( AnyView, szEntityName, szAttributeName, szValueOut );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// Default is String.
               //:SetAttributeFromString( AnyView, szEntityName, szAttributeName, szValueOut )
               SetAttributeFromString( AnyView, szEntityName, szAttributeName, szValueOut );
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


//:GLOBAL OPERATION
//:ActivateDynamicDomain( VIEW ViewToWindow,
//:                       STRING ( 32 ) szDomainName )

//:   VIEW DOMAINT   BASED ON LOD  DOMAINT
public void 
ActivateDynamicDomain( View     ViewToWindow,
                       String   szDomainName )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:VIEW DOMAINTN  BASED ON LOD  DOMAINT
   zVIEW    DOMAINTN = new zVIEW( );
   //:VIEW DOMAINTNC BASED ON LOD DOMAINT
   zVIEW    DOMAINTNC = new zVIEW( );
   //:STRING ( 50 ) szName
   String   szName = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;



   //:ACTIVATE DOMAINT MULTIPLE
   //:   WHERE DOMAINT.Domain.Name = szDomainName
   o_fnLocalBuildQual_16( ViewToWindow, vTempViewVar_0, szDomainName );
   RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:   
   //:// Create an entry for this dynamic Domain.
   //:CreateViewFromView( DOMAINTN, DOMAINT )
   CreateViewFromView( DOMAINTN, DOMAINT );
   //:szName = "X_" + DOMAINT.Domain.Name
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 33, DOMAINT, "Domain", "Name", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szName;
   if ( szName == null )
      sb_szName = new StringBuilder( 32 );
   else
      sb_szName = new StringBuilder( szName );
      ZeidonStringCopy( sb_szName, 1, 0, "X_", 1, 0, 51 );
   szName = sb_szName.toString( );}
    {StringBuilder sb_szName;
   if ( szName == null )
      sb_szName = new StringBuilder( 32 );
   else
      sb_szName = new StringBuilder( szName );
      ZeidonStringConcat( sb_szName, 1, 0, szTempString_0, 1, 0, 51 );
   szName = sb_szName.toString( );}
   //:NAME VIEW DOMAINTN szName
   SetNameForView( DOMAINTN, szName, null, zLEVEL_TASK );

   //:// Create an entry for each Domain Context.
   //:FOR EACH DOMAINTN.Context 
   RESULT = SetCursorFirstEntity( DOMAINTN, "Context", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY DOMAINT.Domain 
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = DOMAINTN.Context.Name 
      SetAttributeFromAttribute( DOMAINT, "Domain", "Name", DOMAINTN, "Context", "Name" );
      //:FOR EACH DOMAINTN.ContextDomainValue 
      RESULT = SetCursorFirstEntity( DOMAINTN, "ContextDomainValue", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY DOMAINT.DomainValue 
         RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
         //:DOMAINT.DomainValue.InternalStringValue = DOMAINTN.ContextDomainValue.InternalStringValue 
         SetAttributeFromAttribute( DOMAINT, "DomainValue", "InternalStringValue", DOMAINTN, "ContextDomainValue", "InternalStringValue" );
         //:DOMAINT.DomainValue.ExternalDescription = DOMAINTN.ContextDomainValue.ExternalDescription 
         SetAttributeFromAttribute( DOMAINT, "DomainValue", "ExternalDescription", DOMAINTN, "ContextDomainValue", "ExternalDescription" );
         RESULT = SetCursorNextEntity( DOMAINTN, "ContextDomainValue", "" );
      } 

      //:END
      //:CreateViewFromView( DOMAINTNC, DOMAINT )
      CreateViewFromView( DOMAINTNC, DOMAINT );
      //:szName = "X_" + DOMAINTNC.Domain.Name
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
             GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 33, DOMAINTNC, "Domain", "Name", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szTempString_1 = sb_szTempString_1.toString( );}
       {StringBuilder sb_szName;
      if ( szName == null )
         sb_szName = new StringBuilder( 32 );
      else
         sb_szName = new StringBuilder( szName );
            ZeidonStringCopy( sb_szName, 1, 0, "X_", 1, 0, 51 );
      szName = sb_szName.toString( );}
       {StringBuilder sb_szName;
      if ( szName == null )
         sb_szName = new StringBuilder( 32 );
      else
         sb_szName = new StringBuilder( szName );
            ZeidonStringConcat( sb_szName, 1, 0, szTempString_1, 1, 0, 51 );
      szName = sb_szName.toString( );}
      //:NAME VIEW DOMAINTNC szName
      SetNameForView( DOMAINTNC, szName, null, zLEVEL_TASK );
      RESULT = SetCursorNextEntity( DOMAINTN, "Context", "" );
   } 

   //:END
   return;
// END
} 


//:GLOBAL OPERATION
//:SendEmailMessage( VIEW mUser BASED ON LOD mUser,
//:                  STRING ( 255 )   szRecipientEmailAddress,
//:                  STRING ( 255 )   szSenderEmailAddress,
//:                  STRING ( 10000 ) szMessageText,
//:                  STRING ( 255 )   szSubjectText )

//:   STRING ( 255 )  szSMTPServer
public int 
SendEmailMessage( View     mUser,
                  String   szRecipientEmailAddress,
                  String   szSenderEmailAddress,
                  String   szMessageText,
                  String   szSubjectText )
{
   String   szSMTPServer = null;
   //:STRING ( 255 )  szUserEmailAddress
   String   szUserEmailAddress = null;
   //:STRING ( 255 )  szUserEmailName
   String   szUserEmailName = null;
   //:STRING ( 50 )   szUserEmailPassword
   String   szUserEmailPassword = null;
   //:STRING ( 10 )   szCreatedDate
   String   szCreatedDate = null;
   //:STRING ( 20 )   szPetitionReason
   String   szPetitionReason = null;
   //:INTEGER         lConnection
   int      lConnection = 0;
   //:SHORT           nRC
   int      nRC = 0;


   //:// Send an email message to the standard SDO email address (SDO@enc.edu) notifying SDO that a Nonresidency Petition
   //:// has been filed.

   //:// Validate email settings and retrieve email values.
   //:nRC = ValidateEmailValues( mUser, szSMTPServer, szUserEmailAddress, szUserEmailName, szUserEmailPassword )
   {StringBuilder sb_szUserEmailPassword;
   if ( szUserEmailPassword == null )
      sb_szUserEmailPassword = new StringBuilder( 32 );
   else
      sb_szUserEmailPassword = new StringBuilder( szUserEmailPassword );
   StringBuilder sb_szUserEmailName;
   if ( szUserEmailName == null )
      sb_szUserEmailName = new StringBuilder( 32 );
   else
      sb_szUserEmailName = new StringBuilder( szUserEmailName );
   StringBuilder sb_szUserEmailAddress;
   if ( szUserEmailAddress == null )
      sb_szUserEmailAddress = new StringBuilder( 32 );
   else
      sb_szUserEmailAddress = new StringBuilder( szUserEmailAddress );
   StringBuilder sb_szSMTPServer;
   if ( szSMTPServer == null )
      sb_szSMTPServer = new StringBuilder( 32 );
   else
      sb_szSMTPServer = new StringBuilder( szSMTPServer );
       nRC = ValidateEmailValues( mUser, sb_szSMTPServer, sb_szUserEmailAddress, sb_szUserEmailName, sb_szUserEmailPassword );
   szUserEmailPassword = sb_szUserEmailPassword.toString( );
   szUserEmailName = sb_szUserEmailName.toString( );
   szUserEmailAddress = sb_szUserEmailAddress.toString( );
   szSMTPServer = sb_szSMTPServer.toString( );}
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:// An error occurred, so return with error. The message has already been sent.
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Try to make connection.
   //:lConnection = CreateSeeConnection( szSMTPServer, szUserEmailAddress,
   //:                                   szUserEmailName, szUserEmailPassword )
   lConnection = m_ZDRVROPR.CreateSeeConnection( szSMTPServer, szUserEmailAddress, szUserEmailName, szUserEmailPassword );
   //:IF lConnection = 0
   if ( lConnection == 0 )
   { 
      //:MessageSend( mUser, "mRegisD05", "Send Email",
      //:             "The Email Server connection could not be established.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mUser, "mRegisD05", "Send Email", "The Email Server connection could not be established.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Send Email.
   //:// Recipient Email Address is passed as a parameter in the Operation.
   //:nRC = CreateSeeMessage( lConnection, 
   //:                        szSMTPServer, 
   //:                        szUserEmailAddress,
   //:                        szRecipientEmailAddress, "", "", 
   //:                        szSubjectText, 1, 
   //:                        szMessageText, "", "", 1,
   //:                        "", 
   //:                        szUserEmailName, 
   //:                        szUserEmailPassword )
   nRC = m_ZDRVROPR.CreateSeeMessage( lConnection, szSMTPServer, szUserEmailAddress, szRecipientEmailAddress, "", "", szSubjectText, 1, szMessageText, "", "", 1, "", szUserEmailName, szUserEmailPassword );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:MessageSend( mUser, "mRegisD05", "Send Email",
      //:             "There was an error sending the Email message.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mUser, "mRegisD05", "Send Email", "There was an error sending the Email message.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   } 

   //:END
   //:TraceLineS( "*** Email Sent successfully to: ", szUserEmailAddress )
   TraceLineS( "*** Email Sent successfully to: ", szUserEmailAddress );

   //:// Shut down the connection.
   //:CloseSeeConnection( lConnection )
   m_ZDRVROPR.CloseSeeConnection( lConnection );

   //:RETURN nRC
   return( nRC );
// END
} 


//:GLOBAL OPERATION
//:GenerateCampusID( VIEW mPerson BASED ON LOD mPerson,
//:                  STRING ( 32 ) szPersonEntityName )

//:   STRING ( 20 ) szPersonID
public void 
GenerateCampusID( View     mPerson,
                  String   szPersonEntityName )
{
   String   szPersonID = null;
   //:STRING ( 20 ) szCampusID
   String   szCampusID = null;
   //:SHORT         StringLength
   int      StringLength = 0;
   //:INTEGER       FillerBytes
   int      FillerBytes = 0;
   int      RESULT = 0;


   //:// CampusID
   //:// Generate the Campus ID from the view passed in and szPersonEntityName, which is a Person
   //:// entity in the object of any name.

   //:// The ID will be generated by the prefix, "xxx", plus the Person ID. This will generate a unique
   //:// ID because no non-OPENCUAS ID's start with xxx and, within OPENCUAS, the Person ID is unique.

   //:// Note that if the Person is new (ie., their ID is null), we will have to save the object to get an ID generated.)

   //:GetStringFromAttribute( szPersonID, mPerson, szPersonEntityName, "ID" )
   {StringBuilder sb_szPersonID;
   if ( szPersonID == null )
      sb_szPersonID = new StringBuilder( 32 );
   else
      sb_szPersonID = new StringBuilder( szPersonID );
       GetStringFromAttribute( sb_szPersonID, mPerson, szPersonEntityName, "ID" );
   szPersonID = sb_szPersonID.toString( );}
   //:IF szPersonID = ""
   if ( ZeidonStringCompare( szPersonID, 1, 0, "", 1, 0, 21 ) == 0 )
   { 
      //:// We're going to put in a temporary CampusID of 1 in the record, because we may have a validation in mPerson commit
      //:// constraint that checks for a null value.
      //:SetAttributeFromString( mPerson, szPersonEntityName, "CampusID", "1" )
      SetAttributeFromString( mPerson, szPersonEntityName, "CampusID", "1" );
      //:COMMIT mPerson
      RESULT = CommitObjectInstance( mPerson );
      //:GetStringFromAttribute( szPersonID, mPerson, szPersonEntityName, "ID" )
      {StringBuilder sb_szPersonID;
      if ( szPersonID == null )
         sb_szPersonID = new StringBuilder( 32 );
      else
         sb_szPersonID = new StringBuilder( szPersonID );
             GetStringFromAttribute( sb_szPersonID, mPerson, szPersonEntityName, "ID" );
      szPersonID = sb_szPersonID.toString( );}
   } 

   //:END
   //:StringLength = zstrlen( szPersonID )
   StringLength = zstrlen( szPersonID );
   //:FillerBytes = 6 - StringLength
   FillerBytes = 6 - StringLength;
   //:szCampusID = ""
    {StringBuilder sb_szCampusID;
   if ( szCampusID == null )
      sb_szCampusID = new StringBuilder( 32 );
   else
      sb_szCampusID = new StringBuilder( szCampusID );
      ZeidonStringCopy( sb_szCampusID, 1, 0, "", 1, 0, 21 );
   szCampusID = sb_szCampusID.toString( );}
   //:LOOP WHILE FillerBytes > 0
   while ( FillerBytes > 0 )
   { 
      //:szCampusID = szCampusID + "0"
       {StringBuilder sb_szCampusID;
      if ( szCampusID == null )
         sb_szCampusID = new StringBuilder( 32 );
      else
         sb_szCampusID = new StringBuilder( szCampusID );
            ZeidonStringConcat( sb_szCampusID, 1, 0, "0", 1, 0, 21 );
      szCampusID = sb_szCampusID.toString( );}
      //:FillerBytes = FillerBytes - 1
      FillerBytes = FillerBytes - 1;
   } 

   //:END
   //:szCampusID = szCampusID + szPersonID
    {StringBuilder sb_szCampusID;
   if ( szCampusID == null )
      sb_szCampusID = new StringBuilder( 32 );
   else
      sb_szCampusID = new StringBuilder( szCampusID );
      ZeidonStringConcat( sb_szCampusID, 1, 0, szPersonID, 1, 0, 21 );
   szCampusID = sb_szCampusID.toString( );}
   //:SetAttributeFromString( mPerson, szPersonEntityName, "CampusID", szCampusID )
   SetAttributeFromString( mPerson, szPersonEntityName, "CampusID", szCampusID );
   return;
// END
} 


//:GLOBAL OPERATION
//:SetAttributeByCommaDelimitValue( VIEW AnyView,
//:                                 STRING ( 32 )    szEntityName,
//:                                 STRING ( 32 )    szAttributeName,
//:                                 STRING ( 4000 ) szLine,
//:                                 INTEGER ValueCommaPos )

//:   STRING ( 4000 )  szValue
public int 
SetAttributeByCommaDelimitValue( View     AnyView,
                                 String   szEntityName,
                                 String   szAttributeName,
                                 String   szLine,
                                 int      ValueCommaPos )
{
   String   szValue = null;


   //:RetrieveCommaDeliminatedValue( szValue, szLine, ValueCommaPos )
   {StringBuilder sb_szValue;
   if ( szValue == null )
      sb_szValue = new StringBuilder( 32 );
   else
      sb_szValue = new StringBuilder( szValue );
       RetrieveCommaDeliminatedValue( sb_szValue, szLine, ValueCommaPos );
   szValue = sb_szValue.toString( );}
   //:SetAttributeFromString( AnyView, szEntityName, szAttributeName, szValue )
   SetAttributeFromString( AnyView, szEntityName, szAttributeName, szValue );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:SendSDO_NonresPetitionEmail( VIEW ViewToWindow,
//:                             VIEW mNonResP BASED ON LOD mNonResP,
//:                             VIEW mUser    BASED ON LOD mUser )

//:   STRING ( 255 )  szSMTPServer
public int 
SendSDO_NonresPetitionEmail( View     ViewToWindow,
                             View     mNonResP,
                             View     mUser )
{
   String   szSMTPServer = null;
   //:STRING ( 255 )  szRecipientEmailAddress
   String   szRecipientEmailAddress = null;
   //:STRING ( 255 )  szUserEmailAddress
   String   szUserEmailAddress = null;
   //:STRING ( 255 )  szUserEmailName
   String   szUserEmailName = null;
   //:STRING ( 50 )   szUserEmailPassword
   String   szUserEmailPassword = null;
   //:STRING ( 4000 ) szMessageBody
   String   szMessageBody = null;
   //:STRING ( 3000 ) szNote
   String   szNote = null;
   //:STRING ( 255 )  szSubjectText
   String   szSubjectText = null;
   //:STRING ( 10 )   szCreatedDate
   String   szCreatedDate = null;
   //:STRING ( 20 )   szPetitionReason
   String   szPetitionReason = null;
   //:INTEGER         lConnection
   int      lConnection = 0;
   //:SHORT           nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:// Send an email message to the standard SDO email address (SDO@enc.edu) notifying SDO that a Nonresidency Petition
   //:// has been filed.

   //:// Validate email settings and retrieve email values.
   //:nRC = ValidateEmailValues( mUser, szSMTPServer, szUserEmailAddress, szUserEmailName, szUserEmailPassword )
   {StringBuilder sb_szUserEmailPassword;
   if ( szUserEmailPassword == null )
      sb_szUserEmailPassword = new StringBuilder( 32 );
   else
      sb_szUserEmailPassword = new StringBuilder( szUserEmailPassword );
   StringBuilder sb_szUserEmailName;
   if ( szUserEmailName == null )
      sb_szUserEmailName = new StringBuilder( 32 );
   else
      sb_szUserEmailName = new StringBuilder( szUserEmailName );
   StringBuilder sb_szUserEmailAddress;
   if ( szUserEmailAddress == null )
      sb_szUserEmailAddress = new StringBuilder( 32 );
   else
      sb_szUserEmailAddress = new StringBuilder( szUserEmailAddress );
   StringBuilder sb_szSMTPServer;
   if ( szSMTPServer == null )
      sb_szSMTPServer = new StringBuilder( 32 );
   else
      sb_szSMTPServer = new StringBuilder( szSMTPServer );
       nRC = ValidateEmailValues( mUser, sb_szSMTPServer, sb_szUserEmailAddress, sb_szUserEmailName, sb_szUserEmailPassword );
   szUserEmailPassword = sb_szUserEmailPassword.toString( );
   szUserEmailName = sb_szUserEmailName.toString( );
   szUserEmailAddress = sb_szUserEmailAddress.toString( );
   szSMTPServer = sb_szSMTPServer.toString( );}
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:// An error occurred, so return with error. The message has already been sent.
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Try to make connection.
   //:lConnection = CreateSeeConnection( szSMTPServer, szUserEmailAddress,
   //:                                   szUserEmailName, szUserEmailPassword )
   lConnection = m_ZDRVROPR.CreateSeeConnection( szSMTPServer, szUserEmailAddress, szUserEmailName, szUserEmailPassword );
   //:IF lConnection = 0
   if ( lConnection == 0 )
   { 
      //:MessageSend( ViewToWindow, "mRegisD05", "Send Email",
      //:             "The Email Server connection could not be established.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "mRegisD05", "Send Email", "The Email Server connection could not be established.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Format Email Message body and Subject text that will be the same in each email, which will be composed of the
   //:// Person's name who filed the Petition, the date, the Petition Reason and the Note data.
   //:GetStringFromAttributeByContext( szPetitionReason, mNonResP, "StudentNonresidencyPetition", "PetitionReason", "", 20 )
   {StringBuilder sb_szPetitionReason;
   if ( szPetitionReason == null )
      sb_szPetitionReason = new StringBuilder( 32 );
   else
      sb_szPetitionReason = new StringBuilder( szPetitionReason );
       GetStringFromAttributeByContext( sb_szPetitionReason, mNonResP, "StudentNonresidencyPetition", "PetitionReason", "", 20 );
   szPetitionReason = sb_szPetitionReason.toString( );}
   //:GetStringFromAttributeByContext( szCreatedDate, mNonResP, "StudentNonresidencyPetition", "CreatedDateTime", "", 10 )
   {StringBuilder sb_szCreatedDate;
   if ( szCreatedDate == null )
      sb_szCreatedDate = new StringBuilder( 32 );
   else
      sb_szCreatedDate = new StringBuilder( szCreatedDate );
       GetStringFromAttributeByContext( sb_szCreatedDate, mNonResP, "StudentNonresidencyPetition", "CreatedDateTime", "", 10 );
   szCreatedDate = sb_szCreatedDate.toString( );}
   //:szNote = mNonResP.StudentNonresidencyPetition.ApplicantNote
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szNote;
   if ( szNote == null )
      sb_szNote = new StringBuilder( 32 );
   else
      sb_szNote = new StringBuilder( szNote );
       GetVariableFromAttribute( sb_szNote, mi_lTempInteger_0, 'S', 3001, mNonResP, "StudentNonresidencyPetition", "ApplicantNote", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szNote = sb_szNote.toString( );}
   //:szMessageBody = "A nonresidency petition has been filed by " + mNonResP.Person.dFullNameFML + " on " + szCreatedDate + ". " + NEW_LINE +
   //:                "The reason for the Petition was, " + szPetitionReason + ", and the Applicant Note was: " + NEW_LINE
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 255, mNonResP, "Person", "dFullNameFML", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szMessageBody;
   if ( szMessageBody == null )
      sb_szMessageBody = new StringBuilder( 32 );
   else
      sb_szMessageBody = new StringBuilder( szMessageBody );
      ZeidonStringCopy( sb_szMessageBody, 1, 0, "A nonresidency petition has been filed by ", 1, 0, 4001 );
   szMessageBody = sb_szMessageBody.toString( );}
    {StringBuilder sb_szMessageBody;
   if ( szMessageBody == null )
      sb_szMessageBody = new StringBuilder( 32 );
   else
      sb_szMessageBody = new StringBuilder( szMessageBody );
      ZeidonStringConcat( sb_szMessageBody, 1, 0, szTempString_0, 1, 0, 4001 );
   szMessageBody = sb_szMessageBody.toString( );}
    {StringBuilder sb_szMessageBody;
   if ( szMessageBody == null )
      sb_szMessageBody = new StringBuilder( 32 );
   else
      sb_szMessageBody = new StringBuilder( szMessageBody );
      ZeidonStringConcat( sb_szMessageBody, 1, 0, " on ", 1, 0, 4001 );
   szMessageBody = sb_szMessageBody.toString( );}
    {StringBuilder sb_szMessageBody;
   if ( szMessageBody == null )
      sb_szMessageBody = new StringBuilder( 32 );
   else
      sb_szMessageBody = new StringBuilder( szMessageBody );
      ZeidonStringConcat( sb_szMessageBody, 1, 0, szCreatedDate, 1, 0, 4001 );
   szMessageBody = sb_szMessageBody.toString( );}
    {StringBuilder sb_szMessageBody;
   if ( szMessageBody == null )
      sb_szMessageBody = new StringBuilder( 32 );
   else
      sb_szMessageBody = new StringBuilder( szMessageBody );
      ZeidonStringConcat( sb_szMessageBody, 1, 0, ". ", 1, 0, 4001 );
   szMessageBody = sb_szMessageBody.toString( );}
    {StringBuilder sb_szMessageBody;
   if ( szMessageBody == null )
      sb_szMessageBody = new StringBuilder( 32 );
   else
      sb_szMessageBody = new StringBuilder( szMessageBody );
      ZeidonStringConcat( sb_szMessageBody, 1, 0, NEW_LINE, 1, 0, 4001 );
   szMessageBody = sb_szMessageBody.toString( );}
    {StringBuilder sb_szMessageBody;
   if ( szMessageBody == null )
      sb_szMessageBody = new StringBuilder( 32 );
   else
      sb_szMessageBody = new StringBuilder( szMessageBody );
      ZeidonStringConcat( sb_szMessageBody, 1, 0, "The reason for the Petition was, ", 1, 0, 4001 );
   szMessageBody = sb_szMessageBody.toString( );}
    {StringBuilder sb_szMessageBody;
   if ( szMessageBody == null )
      sb_szMessageBody = new StringBuilder( 32 );
   else
      sb_szMessageBody = new StringBuilder( szMessageBody );
      ZeidonStringConcat( sb_szMessageBody, 1, 0, szPetitionReason, 1, 0, 4001 );
   szMessageBody = sb_szMessageBody.toString( );}
    {StringBuilder sb_szMessageBody;
   if ( szMessageBody == null )
      sb_szMessageBody = new StringBuilder( 32 );
   else
      sb_szMessageBody = new StringBuilder( szMessageBody );
      ZeidonStringConcat( sb_szMessageBody, 1, 0, ", and the Applicant Note was: ", 1, 0, 4001 );
   szMessageBody = sb_szMessageBody.toString( );}
    {StringBuilder sb_szMessageBody;
   if ( szMessageBody == null )
      sb_szMessageBody = new StringBuilder( 32 );
   else
      sb_szMessageBody = new StringBuilder( szMessageBody );
      ZeidonStringConcat( sb_szMessageBody, 1, 0, NEW_LINE, 1, 0, 4001 );
   szMessageBody = sb_szMessageBody.toString( );}
   //:szMessageBody = szMessageBody + szNote
    {StringBuilder sb_szMessageBody;
   if ( szMessageBody == null )
      sb_szMessageBody = new StringBuilder( 32 );
   else
      sb_szMessageBody = new StringBuilder( szMessageBody );
      ZeidonStringConcat( sb_szMessageBody, 1, 0, szNote, 1, 0, 4001 );
   szMessageBody = sb_szMessageBody.toString( );}
   //:szSubjectText = "Nonresidency Petition Notice"
    {StringBuilder sb_szSubjectText;
   if ( szSubjectText == null )
      sb_szSubjectText = new StringBuilder( 32 );
   else
      sb_szSubjectText = new StringBuilder( szSubjectText );
      ZeidonStringCopy( sb_szSubjectText, 1, 0, "Nonresidency Petition Notice", 1, 0, 256 );
   szSubjectText = sb_szSubjectText.toString( );}

   //:// Send Email.
   //:// NOTE THAT WE ARE HARD-CODING THE SDO EMAIL ADDRESS. THIS NEEDS TO BE REPLACED WITH THE NEW MESSAGE SYSTEM WHEN THAT SYSTEM
   //:// IS PUT INTO PLACE.
   //:szRecipientEmailAddress = "NonresidencyPetition@enc.edu"
    {StringBuilder sb_szRecipientEmailAddress;
   if ( szRecipientEmailAddress == null )
      sb_szRecipientEmailAddress = new StringBuilder( 32 );
   else
      sb_szRecipientEmailAddress = new StringBuilder( szRecipientEmailAddress );
      ZeidonStringCopy( sb_szRecipientEmailAddress, 1, 0, "NonresidencyPetition@enc.edu", 1, 0, 256 );
   szRecipientEmailAddress = sb_szRecipientEmailAddress.toString( );}
   //:nRC = CreateSeeMessage( lConnection, 
   //:                        szSMTPServer, 
   //:                        szUserEmailAddress,
   //:                        szRecipientEmailAddress, "", "", 
   //:                        szSubjectText, 1, 
   //:                        szMessageBody, "", "", 1,
   //:                        "", 
   //:                        szUserEmailName, 
   //:                        szUserEmailPassword )
   nRC = m_ZDRVROPR.CreateSeeMessage( lConnection, szSMTPServer, szUserEmailAddress, szRecipientEmailAddress, "", "", szSubjectText, 1, szMessageBody, "", "", 1, "", szUserEmailName, szUserEmailPassword );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:MessageSend( ViewToWindow, "mRegisD05", "Send Email",
      //:             "There was an error sending the Email message.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "mRegisD05", "Send Email", "There was an error sending the Email message.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   } 

   //:END
   //:TraceLineS( "*** Email Sent successfully to: ", szUserEmailAddress )
   TraceLineS( "*** Email Sent successfully to: ", szUserEmailAddress );

   //:// Shut down the connection.
   //:CloseSeeConnection( lConnection )
   m_ZDRVROPR.CloseSeeConnection( lConnection );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:GetPersonCombinedNameSalErr( STRING ( 100 ) szFullName,
//:                             VIEW qPerson BASED ON LOD qPerson,
//:                             STRING ( 32 )  szEntityName )

//:   VIEW qPerson2 BASED ON LOD qPerson
public int 
GetPersonCombinedNameSalErr( StringBuilder   szFullName,
                             View     qPerson,
                             String   szEntityName )
{
   zVIEW    qPerson2 = new zVIEW( );
   //:INTEGER        HusbandRanking
   int      HusbandRanking = 0;
   //:INTEGER        WifeRanking
   int      WifeRanking = 0;
   //:STRING ( 50 )  szFirstName
   String   szFirstName = null;
   //:STRING ( 50 )  szLastName
   String   szLastName = null;
   //:STRING ( 50 )  szOnlyLastName
   String   szOnlyLastName = null;
   //:STRING ( 10 )  szMainSuffix
   String   szMainSuffix = null;
   //:STRING ( 10 )  szHusbandSuffix
   String   szHusbandSuffix = null;
   //:STRING ( 50 )  szHusbandFirstName
   String   szHusbandFirstName = null;
   //:STRING ( 50 )  szWifeFirstName
   String   szWifeFirstName = null;
   //:STRING ( 50 )  szHusbandLastName
   String   szHusbandLastName = null;
   //:STRING ( 50 )  szWifeLastName
   String   szWifeLastName = null;
   //:STRING ( 10 )  szSalutation
   String   szSalutation = null;
   //:STRING ( 10 )  szHusbandSalutation
   String   szHusbandSalutation = null;
   //:STRING ( 10 )  szWifeSalutation
   String   szWifeSalutation = null;
   //:STRING ( 1 )   szGender
   String   szGender = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;


   //:CreateViewFromView( qPerson2, qPerson )
   CreateViewFromView( qPerson2, qPerson );
   //:szWifeFirstName      = ""
    {StringBuilder sb_szWifeFirstName;
   if ( szWifeFirstName == null )
      sb_szWifeFirstName = new StringBuilder( 32 );
   else
      sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
      ZeidonStringCopy( sb_szWifeFirstName, 1, 0, "", 1, 0, 51 );
   szWifeFirstName = sb_szWifeFirstName.toString( );}
   //:szHusbandFirstName   = ""
    {StringBuilder sb_szHusbandFirstName;
   if ( szHusbandFirstName == null )
      sb_szHusbandFirstName = new StringBuilder( 32 );
   else
      sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
      ZeidonStringCopy( sb_szHusbandFirstName, 1, 0, "", 1, 0, 51 );
   szHusbandFirstName = sb_szHusbandFirstName.toString( );}
   //:szHusbandSuffix = ""
    {StringBuilder sb_szHusbandSuffix;
   if ( szHusbandSuffix == null )
      sb_szHusbandSuffix = new StringBuilder( 32 );
   else
      sb_szHusbandSuffix = new StringBuilder( szHusbandSuffix );
      ZeidonStringCopy( sb_szHusbandSuffix, 1, 0, "", 1, 0, 11 );
   szHusbandSuffix = sb_szHusbandSuffix.toString( );}

   //:// Get first names and salutations.
   //:GetStringFromAttributeByContext( szSalutation, qPerson2, szEntityName, "Salutation", "", 10 )
   {StringBuilder sb_szSalutation;
   if ( szSalutation == null )
      sb_szSalutation = new StringBuilder( 32 );
   else
      sb_szSalutation = new StringBuilder( szSalutation );
       GetStringFromAttributeByContext( sb_szSalutation, qPerson2, szEntityName, "Salutation", "", 10 );
   szSalutation = sb_szSalutation.toString( );}
   //:GetStringFromAttribute( szLastName, qPerson2, szEntityName, "LastName" )
   {StringBuilder sb_szLastName;
   if ( szLastName == null )
      sb_szLastName = new StringBuilder( 32 );
   else
      sb_szLastName = new StringBuilder( szLastName );
       GetStringFromAttribute( sb_szLastName, qPerson2, szEntityName, "LastName" );
   szLastName = sb_szLastName.toString( );}

   //:SET CURSOR FIRST qPerson2.HusbandOrParentRole 
   //:           WHERE qPerson2.HusbandOrParentRole.Role = "S"
   RESULT = SetCursorFirstEntityByString( qPerson2, "HusbandOrParentRole", "Role", "S", "" );
   //:IF RESULT >= zCURSOR_SET AND qPerson2.HusbandOrParent.Deceased != "Y"
   if ( RESULT >= zCURSOR_SET && CompareAttributeToString( qPerson2, "HusbandOrParent", "Deceased", "Y" ) != 0 )
   { 
      //:GetStringFromAttribute( szWifeLastName, qPerson2, szEntityName, "LastName" )
      {StringBuilder sb_szWifeLastName;
      if ( szWifeLastName == null )
         sb_szWifeLastName = new StringBuilder( 32 );
      else
         sb_szWifeLastName = new StringBuilder( szWifeLastName );
             GetStringFromAttribute( sb_szWifeLastName, qPerson2, szEntityName, "LastName" );
      szWifeLastName = sb_szWifeLastName.toString( );}
      //:GetStringFromAttribute( szWifeFirstName, qPerson2, szEntityName, "FirstName" )
      {StringBuilder sb_szWifeFirstName;
      if ( szWifeFirstName == null )
         sb_szWifeFirstName = new StringBuilder( 32 );
      else
         sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
             GetStringFromAttribute( sb_szWifeFirstName, qPerson2, szEntityName, "FirstName" );
      szWifeFirstName = sb_szWifeFirstName.toString( );}
      //:GetStringFromAttributeByContext( szWifeSalutation, qPerson2, szEntityName, "Salutation", "", 10 )
      {StringBuilder sb_szWifeSalutation;
      if ( szWifeSalutation == null )
         sb_szWifeSalutation = new StringBuilder( 32 );
      else
         sb_szWifeSalutation = new StringBuilder( szWifeSalutation );
             GetStringFromAttributeByContext( sb_szWifeSalutation, qPerson2, szEntityName, "Salutation", "", 10 );
      szWifeSalutation = sb_szWifeSalutation.toString( );}
      //:szHusbandLastName        = qPerson2.HusbandOrParent.LastName
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szHusbandLastName;
      if ( szHusbandLastName == null )
         sb_szHusbandLastName = new StringBuilder( 32 );
      else
         sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
             GetVariableFromAttribute( sb_szHusbandLastName, mi_lTempInteger_0, 'S', 51, qPerson2, "HusbandOrParent", "LastName", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szHusbandLastName = sb_szHusbandLastName.toString( );}
      //:szHusbandFirstName       = qPerson2.HusbandOrParent.FirstName
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szHusbandFirstName;
      if ( szHusbandFirstName == null )
         sb_szHusbandFirstName = new StringBuilder( 32 );
      else
         sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
             GetVariableFromAttribute( sb_szHusbandFirstName, mi_lTempInteger_1, 'S', 51, qPerson2, "HusbandOrParent", "FirstName", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szHusbandFirstName = sb_szHusbandFirstName.toString( );}
      //:szHusbandSalutation = qPerson2.HusbandOrParent.Salutation 
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szHusbandSalutation;
      if ( szHusbandSalutation == null )
         sb_szHusbandSalutation = new StringBuilder( 32 );
      else
         sb_szHusbandSalutation = new StringBuilder( szHusbandSalutation );
             GetVariableFromAttribute( sb_szHusbandSalutation, mi_lTempInteger_2, 'S', 11, qPerson2, "HusbandOrParent", "Salutation", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szHusbandSalutation = sb_szHusbandSalutation.toString( );}
      //:szHusbandSuffix     = qPerson2.HusbandOrParent.Suffix 
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szHusbandSuffix;
      if ( szHusbandSuffix == null )
         sb_szHusbandSuffix = new StringBuilder( 32 );
      else
         sb_szHusbandSuffix = new StringBuilder( szHusbandSuffix );
             GetVariableFromAttribute( sb_szHusbandSuffix, mi_lTempInteger_3, 'S', 11, qPerson2, "HusbandOrParent", "Suffix", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szHusbandSuffix = sb_szHusbandSuffix.toString( );}
      //:ELSE
   } 
   else
   { 
      //:SET CURSOR FIRST qPerson2.WifeOrChildRole 
      //:           WHERE qPerson2.WifeOrChildRole.Role = "S" AND qPerson2.WifeOrChild.Deceased != "Y"
      RESULT = SetCursorFirstEntity( qPerson2, "WifeOrChildRole", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( qPerson2, "WifeOrChildRole", "Role", "S" ) != 0 || CompareAttributeToString( qPerson2, "WifeOrChild", "Deceased", "Y" ) == 0 ) )
         { 
            RESULT = SetCursorNextEntity( qPerson2, "WifeOrChildRole", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:szWifeLastName        = qPerson2.WifeOrChild.LastName 
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
         StringBuilder sb_szWifeLastName;
         if ( szWifeLastName == null )
            sb_szWifeLastName = new StringBuilder( 32 );
         else
            sb_szWifeLastName = new StringBuilder( szWifeLastName );
                   GetVariableFromAttribute( sb_szWifeLastName, mi_lTempInteger_4, 'S', 51, qPerson2, "WifeOrChild", "LastName", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );
         szWifeLastName = sb_szWifeLastName.toString( );}
         //:szWifeFirstName       = qPerson2.WifeOrChild.FirstName
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
         StringBuilder sb_szWifeFirstName;
         if ( szWifeFirstName == null )
            sb_szWifeFirstName = new StringBuilder( 32 );
         else
            sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
                   GetVariableFromAttribute( sb_szWifeFirstName, mi_lTempInteger_5, 'S', 51, qPerson2, "WifeOrChild", "FirstName", "", 0 );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );
         szWifeFirstName = sb_szWifeFirstName.toString( );}
         //:szWifeSalutation = qPerson2.WifeOrChild.Salutation 
         {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
         StringBuilder sb_szWifeSalutation;
         if ( szWifeSalutation == null )
            sb_szWifeSalutation = new StringBuilder( 32 );
         else
            sb_szWifeSalutation = new StringBuilder( szWifeSalutation );
                   GetVariableFromAttribute( sb_szWifeSalutation, mi_lTempInteger_6, 'S', 11, qPerson2, "WifeOrChild", "Salutation", "", 0 );
         lTempInteger_6 = mi_lTempInteger_6.intValue( );
         szWifeSalutation = sb_szWifeSalutation.toString( );}
         //:GetStringFromAttribute( szHusbandLastName,  qPerson2, szEntityName, "LastName" )
         {StringBuilder sb_szHusbandLastName;
         if ( szHusbandLastName == null )
            sb_szHusbandLastName = new StringBuilder( 32 );
         else
            sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
                   GetStringFromAttribute( sb_szHusbandLastName, qPerson2, szEntityName, "LastName" );
         szHusbandLastName = sb_szHusbandLastName.toString( );}
         //:GetStringFromAttribute( szHusbandFirstName, qPerson2, szEntityName, "FirstName" )
         {StringBuilder sb_szHusbandFirstName;
         if ( szHusbandFirstName == null )
            sb_szHusbandFirstName = new StringBuilder( 32 );
         else
            sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
                   GetStringFromAttribute( sb_szHusbandFirstName, qPerson2, szEntityName, "FirstName" );
         szHusbandFirstName = sb_szHusbandFirstName.toString( );}
         //:GetStringFromAttributeByContext( szHusbandSalutation, qPerson2, szEntityName, "Salutation", "", 10 )
         {StringBuilder sb_szHusbandSalutation;
         if ( szHusbandSalutation == null )
            sb_szHusbandSalutation = new StringBuilder( 32 );
         else
            sb_szHusbandSalutation = new StringBuilder( szHusbandSalutation );
                   GetStringFromAttributeByContext( sb_szHusbandSalutation, qPerson2, szEntityName, "Salutation", "", 10 );
         szHusbandSalutation = sb_szHusbandSalutation.toString( );}
      } 

      //:END
   } 

   //:END
   //:IF szHusbandSalutation = ""
   if ( ZeidonStringCompare( szHusbandSalutation, 1, 0, "", 1, 0, 11 ) == 0 )
   { 
      //:szHusbandSalutation = "Mr."
       {StringBuilder sb_szHusbandSalutation;
      if ( szHusbandSalutation == null )
         sb_szHusbandSalutation = new StringBuilder( 32 );
      else
         sb_szHusbandSalutation = new StringBuilder( szHusbandSalutation );
            ZeidonStringCopy( sb_szHusbandSalutation, 1, 0, "Mr.", 1, 0, 11 );
      szHusbandSalutation = sb_szHusbandSalutation.toString( );}
   } 

   //:END
   //:IF szWifeSalutation = ""
   if ( ZeidonStringCompare( szWifeSalutation, 1, 0, "", 1, 0, 11 ) == 0 )
   { 
      //:szWifeSalutation = "Mrs."
       {StringBuilder sb_szWifeSalutation;
      if ( szWifeSalutation == null )
         sb_szWifeSalutation = new StringBuilder( 32 );
      else
         sb_szWifeSalutation = new StringBuilder( szWifeSalutation );
            ZeidonStringCopy( sb_szWifeSalutation, 1, 0, "Mrs.", 1, 0, 11 );
      szWifeSalutation = sb_szWifeSalutation.toString( );}
   } 

   //:END

   //:// Get Suffix set up.
   //:GetStringFromAttribute( szMainSuffix, qPerson2, szEntityName, "Suffix" )
   {StringBuilder sb_szMainSuffix;
   if ( szMainSuffix == null )
      sb_szMainSuffix = new StringBuilder( 32 );
   else
      sb_szMainSuffix = new StringBuilder( szMainSuffix );
       GetStringFromAttribute( sb_szMainSuffix, qPerson2, szEntityName, "Suffix" );
   szMainSuffix = sb_szMainSuffix.toString( );}
   //:IF szMainSuffix != ""
   if ( ZeidonStringCompare( szMainSuffix, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szLastName = szLastName + " " + szMainSuffix
       {StringBuilder sb_szLastName;
      if ( szLastName == null )
         sb_szLastName = new StringBuilder( 32 );
      else
         sb_szLastName = new StringBuilder( szLastName );
            ZeidonStringConcat( sb_szLastName, 1, 0, " ", 1, 0, 51 );
      szLastName = sb_szLastName.toString( );}
       {StringBuilder sb_szLastName;
      if ( szLastName == null )
         sb_szLastName = new StringBuilder( 32 );
      else
         sb_szLastName = new StringBuilder( szLastName );
            ZeidonStringConcat( sb_szLastName, 1, 0, szMainSuffix, 1, 0, 51 );
      szLastName = sb_szLastName.toString( );}
      //:IF szWifeLastName != szHusbandLastName
      if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) != 0 )
      { 
         //:szHusbandLastName = szLastName + " " + szMainSuffix
          {StringBuilder sb_szHusbandLastName;
         if ( szHusbandLastName == null )
            sb_szHusbandLastName = new StringBuilder( 32 );
         else
            sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
                  ZeidonStringCopy( sb_szHusbandLastName, 1, 0, szLastName, 1, 0, 51 );
         szHusbandLastName = sb_szHusbandLastName.toString( );}
          {StringBuilder sb_szHusbandLastName;
         if ( szHusbandLastName == null )
            sb_szHusbandLastName = new StringBuilder( 32 );
         else
            sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
                  ZeidonStringConcat( sb_szHusbandLastName, 1, 0, " ", 1, 0, 51 );
         szHusbandLastName = sb_szHusbandLastName.toString( );}
          {StringBuilder sb_szHusbandLastName;
         if ( szHusbandLastName == null )
            sb_szHusbandLastName = new StringBuilder( 32 );
         else
            sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
                  ZeidonStringConcat( sb_szHusbandLastName, 1, 0, szMainSuffix, 1, 0, 51 );
         szHusbandLastName = sb_szHusbandLastName.toString( );}
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:IF szHusbandSuffix != ""
      if ( ZeidonStringCompare( szHusbandSuffix, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szLastName = szLastName + " " + szHusbandSuffix
          {StringBuilder sb_szLastName;
         if ( szLastName == null )
            sb_szLastName = new StringBuilder( 32 );
         else
            sb_szLastName = new StringBuilder( szLastName );
                  ZeidonStringConcat( sb_szLastName, 1, 0, " ", 1, 0, 51 );
         szLastName = sb_szLastName.toString( );}
          {StringBuilder sb_szLastName;
         if ( szLastName == null )
            sb_szLastName = new StringBuilder( 32 );
         else
            sb_szLastName = new StringBuilder( szLastName );
                  ZeidonStringConcat( sb_szLastName, 1, 0, szHusbandSuffix, 1, 0, 51 );
         szLastName = sb_szLastName.toString( );}
         //:IF szWifeLastName != szHusbandLastName
         if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) != 0 )
         { 
            //:szHusbandLastName = szLastName + " " + szHusbandSuffix
             {StringBuilder sb_szHusbandLastName;
            if ( szHusbandLastName == null )
               sb_szHusbandLastName = new StringBuilder( 32 );
            else
               sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
                        ZeidonStringCopy( sb_szHusbandLastName, 1, 0, szLastName, 1, 0, 51 );
            szHusbandLastName = sb_szHusbandLastName.toString( );}
             {StringBuilder sb_szHusbandLastName;
            if ( szHusbandLastName == null )
               sb_szHusbandLastName = new StringBuilder( 32 );
            else
               sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
                        ZeidonStringConcat( sb_szHusbandLastName, 1, 0, " ", 1, 0, 51 );
            szHusbandLastName = sb_szHusbandLastName.toString( );}
             {StringBuilder sb_szHusbandLastName;
            if ( szHusbandLastName == null )
               sb_szHusbandLastName = new StringBuilder( 32 );
            else
               sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
                        ZeidonStringConcat( sb_szHusbandLastName, 1, 0, szHusbandSuffix, 1, 0, 51 );
            szHusbandLastName = sb_szHusbandLastName.toString( );}
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:IF szHusbandFirstName != "" AND szWifeFirstName != ""
   if ( ZeidonStringCompare( szHusbandFirstName, 1, 0, "", 1, 0, 51 ) != 0 && ZeidonStringCompare( szWifeFirstName, 1, 0, "", 1, 0, 51 ) != 0 )
   { 
      //:// COMBINED SALUTATION
      //:// There is both a husband and wife name, so create combination.
      //:GetPersonCombinedNameRanking( HusbandRanking, szHusbandSalutation )
      {MutableInt mi_HusbandRanking = new MutableInt( HusbandRanking );
             GetPersonCombinedNameRanking( mi_HusbandRanking, szHusbandSalutation );
      HusbandRanking = mi_HusbandRanking.intValue( );}
      //:GetPersonCombinedNameRanking( WifeRanking, szWifeSalutation )
      {MutableInt mi_WifeRanking = new MutableInt( WifeRanking );
             GetPersonCombinedNameRanking( mi_WifeRanking, szWifeSalutation );
      WifeRanking = mi_WifeRanking.intValue( );}

      //:IF WifeRanking = 1 AND HusbandRanking > 1
      if ( WifeRanking == 1 && HusbandRanking > 1 )
      { 
         //:// Husband "outranks" wife and wife salutation is on the first level.
         //:IF szWifeLastName = szHusbandLastName
         if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) == 0 )
         { 
            //:szFullName = szHusbandSalutation + " & Mrs. " + szHusbandFirstName + " " + szLastName
            ZeidonStringCopy( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " & Mrs. ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
            //:ELSE
         } 
         else
         { 
            //:szFullName = szHusbandSalutation + " " + szHusbandFirstName + " " + szHusbandLastName + " & " + 
            //:             "Mrs. " + szWifeFirstName + " " +  szWifeLastName
            ZeidonStringCopy( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szHusbandLastName, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, "Mrs. ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szWifeLastName, 1, 0, 101 );
         } 

         //:END

         //:ELSE
      } 
      else
      { 
         //:IF WifeRanking > 1 AND HusbandRanking > WifeRanking
         if ( WifeRanking > 1 && HusbandRanking > WifeRanking )
         { 
            //:// Husband "outranks" wife and wife salutation is above first level.
            //:IF szWifeLastName = szHusbandLastName
            if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) == 0 )
            { 
               //:szFullName = szHusbandSalutation + " " + szHusbandFirstName + " & " + 
               //:          szWifeSalutation + " " + szWifeFirstName + " " +  szLastName
               ZeidonStringCopy( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szWifeSalutation, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
               //:ELSE
            } 
            else
            { 
               //:szFullName = szHusbandSalutation + " " + szHusbandFirstName + " " + szHusbandLastName + " & " + 
               //:          szWifeSalutation + " " + szWifeFirstName + " " +  szWifeLastName
               ZeidonStringCopy( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szHusbandLastName, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szWifeSalutation, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szWifeLastName, 1, 0, 101 );
            } 

            //:END

            //:ELSE
         } 
         else
         { 
            //:IF WifeRanking > HusbandRanking 
            if ( WifeRanking > HusbandRanking )
            { 
               //:// Wife "outranks" husband.
               //:IF szWifeLastName = szHusbandLastName
               if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) == 0 )
               { 
                  //:szFullName = szWifeSalutation + " " + szWifeFirstName + " & " + 
                  //:       szHusbandSalutation + " " + szHusbandFirstName + " " +  szLastName
                  ZeidonStringCopy( szFullName, 1, 0, szWifeSalutation, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
                  //:ELSE
               } 
               else
               { 
                  //:szFullName = szWifeSalutation + " " + szWifeFirstName + " " +  szWifeLastName + " & " + 
                  //:       szHusbandSalutation + " " + szHusbandFirstName + " " + szHusbandLastName 
                  ZeidonStringCopy( szFullName, 1, 0, szWifeSalutation, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szWifeLastName, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szHusbandLastName, 1, 0, 101 );
               } 

               //:          
               //:END

               //:ELSE
            } 
            else
            { 
               //:IF WifeRanking = HusbandRanking AND WifeRanking > 1 AND szHusbandSalutation = szWifeSalutation
               if ( WifeRanking == HusbandRanking && WifeRanking > 1 && ZeidonStringCompare( szHusbandSalutation, 1, 0, szWifeSalutation, 1, 0, 11 ) == 0 )
               { 
                  //:// Salutations are the same and above first level.
                  //:IF szHusbandSalutation = "Hon."
                  if ( ZeidonStringCompare( szHusbandSalutation, 1, 0, "Hon.", 1, 0, 11 ) == 0 )
                  { 
                     //:szSalutation = "Hons."
                      {StringBuilder sb_szSalutation;
                     if ( szSalutation == null )
                        sb_szSalutation = new StringBuilder( 32 );
                     else
                        sb_szSalutation = new StringBuilder( szSalutation );
                                          ZeidonStringCopy( sb_szSalutation, 1, 0, "Hons.", 1, 0, 11 );
                     szSalutation = sb_szSalutation.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF szHusbandSalutation = "Dr."
                     if ( ZeidonStringCompare( szHusbandSalutation, 1, 0, "Dr.", 1, 0, 11 ) == 0 )
                     { 
                        //:szSalutation = "Drs."
                         {StringBuilder sb_szSalutation;
                        if ( szSalutation == null )
                           sb_szSalutation = new StringBuilder( 32 );
                        else
                           sb_szSalutation = new StringBuilder( szSalutation );
                                                ZeidonStringCopy( sb_szSalutation, 1, 0, "Drs.", 1, 0, 11 );
                        szSalutation = sb_szSalutation.toString( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:IF szHusbandSalutation = "Rev."
                        if ( ZeidonStringCompare( szHusbandSalutation, 1, 0, "Rev.", 1, 0, 11 ) == 0 )
                        { 
                           //:szSalutation = "Revs."
                            {StringBuilder sb_szSalutation;
                           if ( szSalutation == null )
                              sb_szSalutation = new StringBuilder( 32 );
                           else
                              sb_szSalutation = new StringBuilder( szSalutation );
                                                      ZeidonStringCopy( sb_szSalutation, 1, 0, "Revs.", 1, 0, 11 );
                           szSalutation = sb_szSalutation.toString( );}
                           //:ELSE
                        } 
                        else
                        { 
                           //:IF szHusbandSalutation = "Prof."
                           if ( ZeidonStringCompare( szHusbandSalutation, 1, 0, "Prof.", 1, 0, 11 ) == 0 )
                           { 
                              //:szSalutation = "Profs."
                               {StringBuilder sb_szSalutation;
                              if ( szSalutation == null )
                                 sb_szSalutation = new StringBuilder( 32 );
                              else
                                 sb_szSalutation = new StringBuilder( szSalutation );
                                                            ZeidonStringCopy( sb_szSalutation, 1, 0, "Profs.", 1, 0, 11 );
                              szSalutation = sb_szSalutation.toString( );}
                              //:ELSE
                           } 
                           else
                           { 
                              //:szSalutation = szHusbandSalutation
                               {StringBuilder sb_szSalutation;
                              if ( szSalutation == null )
                                 sb_szSalutation = new StringBuilder( 32 );
                              else
                                 sb_szSalutation = new StringBuilder( szSalutation );
                                                            ZeidonStringCopy( sb_szSalutation, 1, 0, szHusbandSalutation, 1, 0, 11 );
                              szSalutation = sb_szSalutation.toString( );}
                           } 

                           //:END
                        } 

                        //:END
                     } 

                     //:END
                  } 

                  //:END
                  //:IF szWifeLastName = szHusbandLastName
                  if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) == 0 )
                  { 
                     //:szFullName = szSalutation + " " + szHusbandFirstName + " & " + szWifeFirstName + " " + szLastName
                     ZeidonStringCopy( szFullName, 1, 0, szSalutation, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
                     //:ELSE
                  } 
                  else
                  { 
                     //:szFullName = szSalutation + " " + szHusbandFirstName + " " + szHusbandLastName + " & " + 
                     //:    szSalutation + " " + szWifeFirstName + " " +  szWifeLastName
                     ZeidonStringCopy( szFullName, 1, 0, szSalutation, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szHusbandLastName, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szSalutation, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szWifeLastName, 1, 0, 101 );
                  } 

                  //:END

                  //:ELSE
               } 
               else
               { 
                  //:IF szHusbandSalutation = szWifeSalutation AND WifeRanking > 1
                  if ( ZeidonStringCompare( szHusbandSalutation, 1, 0, szWifeSalutation, 1, 0, 11 ) == 0 && WifeRanking > 1 )
                  { 
                     //:// Ranks are even and above first level, but are not the same salutation.
                     //:IF szWifeLastName = szHusbandLastName
                     if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) == 0 )
                     { 
                        //:szFullName = szHusbandSalutation + " " + szHusbandFirstName + " & " + 
                        //: szWifeSalutation + " " + szWifeFirstName + " " +  szLastName
                        ZeidonStringCopy( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szWifeSalutation, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
                        //:ELSE
                     } 
                     else
                     { 
                        //:szFullName = szHusbandSalutation + " " + szHusbandFirstName + " " + szHusbandLastName + " & " + 
                        //: szWifeSalutation + " " + szWifeFirstName + " " +  szWifeLastName
                        ZeidonStringCopy( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szHusbandLastName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szWifeSalutation, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szWifeLastName, 1, 0, 101 );
                     } 

                     //:END

                     //:ELSE
                  } 
                  else
                  { 
                     //:// The default must be Ranks are even and at first level.
                     //:IF szWifeLastName = szHusbandLastName
                     if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) == 0 )
                     { 
                        //:szFullName = "Mr. & Mrs. " + szHusbandFirstName + " " + szLastName
                        ZeidonStringCopy( szFullName, 1, 0, "Mr. & Mrs. ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
                        //:ELSE
                     } 
                     else
                     { 
                        //:szFullName = "Mr. " + szHusbandFirstName + " " + szHusbandLastName + " & " + 
                        //: "Mrs. " + szWifeFirstName + " " +  szWifeLastName
                        ZeidonStringCopy( szFullName, 1, 0, "Mr. ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szHusbandLastName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, "Mrs. ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szWifeLastName, 1, 0, 101 );
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


      //:ELSE
   } 
   else
   { 
      //:// SINGLE SALUTATION
      //:// There is only a single name, so set up a single salution.

      //:GetStringFromAttribute( szFirstName, qPerson2, szEntityName, "FirstName" )
      {StringBuilder sb_szFirstName;
      if ( szFirstName == null )
         sb_szFirstName = new StringBuilder( 32 );
      else
         sb_szFirstName = new StringBuilder( szFirstName );
             GetStringFromAttribute( sb_szFirstName, qPerson2, szEntityName, "FirstName" );
      szFirstName = sb_szFirstName.toString( );}
      //:GetStringFromAttribute( szGender, qPerson2, szEntityName, "Gender" )
      {StringBuilder sb_szGender;
      if ( szGender == null )
         sb_szGender = new StringBuilder( 32 );
      else
         sb_szGender = new StringBuilder( szGender );
             GetStringFromAttribute( sb_szGender, qPerson2, szEntityName, "Gender" );
      szGender = sb_szGender.toString( );}
      //:IF szSalutation = ""
      if ( ZeidonStringCompare( szSalutation, 1, 0, "", 1, 0, 11 ) == 0 )
      { 
         //:IF szGender = "F"
         if ( ZeidonStringCompare( szGender, 1, 0, "F", 1, 0, 2 ) == 0 )
         { 
            //:szSalutation = "Ms."
             {StringBuilder sb_szSalutation;
            if ( szSalutation == null )
               sb_szSalutation = new StringBuilder( 32 );
            else
               sb_szSalutation = new StringBuilder( szSalutation );
                        ZeidonStringCopy( sb_szSalutation, 1, 0, "Ms.", 1, 0, 11 );
            szSalutation = sb_szSalutation.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF szGender = "M"
            if ( ZeidonStringCompare( szGender, 1, 0, "M", 1, 0, 2 ) == 0 )
            { 
               //:szSalutation = "Mr."
                {StringBuilder sb_szSalutation;
               if ( szSalutation == null )
                  sb_szSalutation = new StringBuilder( 32 );
               else
                  sb_szSalutation = new StringBuilder( szSalutation );
                              ZeidonStringCopy( sb_szSalutation, 1, 0, "Mr.", 1, 0, 11 );
               szSalutation = sb_szSalutation.toString( );}
            } 

            //:END
         } 

         //:END 
      } 

      //:END
      //:IF szSalutation != ""
      if ( ZeidonStringCompare( szSalutation, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szFullName = szSalutation + " " + szFirstName + " " + szLastName
         ZeidonStringCopy( szFullName, 1, 0, szSalutation, 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szFirstName, 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
         //:ELSE
      } 
      else
      { 
         //:szFullName = szFirstName + " " + szLastName
         ZeidonStringCopy( szFullName, 1, 0, szFirstName, 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
      } 

      //:END
   } 

   //:END
   //:DropView( qPerson2 )
   DropView( qPerson2 );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:SetSA_ProfileBalance( VIEW AnyView,
//:                      INTEGER SAProfileID )

//:   VIEW mSAProfB BASED ON LOD mSAProfB
public void 
SetSA_ProfileBalance( View     AnyView,
                      int      SAProfileID )
{
   zVIEW    mSAProfB = new zVIEW( );
   //:DECIMAL dAmount
   double  dAmount = 0.0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:// Set Profile Balance for the SAProfile ID passed.

   //:ACTIVATE mSAProfB WHERE mSAProfB.StudentAccountProfile.ID = SAProfileID
   o_fnLocalBuildQual_15( AnyView, vTempViewVar_0, SAProfileID );
   RESULT = ActivateObjectInstance( mSAProfB, "mSAProfB", AnyView, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSAProfB "mSAProfB" 
   SetNameForView( mSAProfB, "mSAProfB", null, zLEVEL_TASK );
   //:dAmount = mSAProfB.StudentAccountProfile.BalanceForward 
   {MutableDouble md_dAmount = new MutableDouble( dAmount );
       GetDecimalFromAttribute( md_dAmount, mSAProfB, "StudentAccountProfile", "BalanceForward" );
   dAmount = md_dAmount.doubleValue( );}
   //:FOR EACH mSAProfB.StudentAccountTransApplied  
   RESULT = SetCursorFirstEntity( mSAProfB, "StudentAccountTransApplied", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:dAmount = dAmount + mSAProfB.StudentAccountTransApplied.Amount 
      {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
             GetDecimalFromAttribute( md_dTempDecimal_0, mSAProfB, "StudentAccountTransApplied", "Amount" );
      dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
      dAmount = dAmount + dTempDecimal_0;
      RESULT = SetCursorNextEntity( mSAProfB, "StudentAccountTransApplied", "" );
   } 

   //:END 
   //:mSAProfB.StudentAccountProfile.AccountBalance = dAmount
   SetAttributeFromDecimal( mSAProfB, "StudentAccountProfile", "AccountBalance", dAmount );
   //:COMMIT mSAProfB
   RESULT = CommitObjectInstance( mSAProfB );
   //:DropObjectInstance( mSAProfB )
   DropObjectInstance( mSAProfB );
   return;
// END
} 


//:GLOBAL OPERATION
//:ComputeNewHighExamScoreSAT( DECIMAL ReturnedScore,
//:                            VIEW mProspct BASED ON LOD mProspct )

//:   VIEW mProspct2 BASED ON LOD mProspct
public void 
ComputeNewHighExamScoreSAT( MutableDouble  ReturnedScore,
                            View     mProspct )
{
   zVIEW    mProspct2 = new zVIEW( );
   //:DECIMAL dHighMath
   double  dHighMath = 0.0;
   //:DECIMAL dHighVerbal 
   double  dHighVerbal = 0.0;
   //:DECIMAL dHighWriting
   double  dHighWriting = 0.0;
   //:DECIMAL dTotal 
   double  dTotal = 0.0;
   int      RESULT = 0;


   //:// Compute the highest total score for SAT exams for any object that has the ExamHistory subobject.
   //:CreateViewFromView( mProspct2, mProspct )
   CreateViewFromView( mProspct2, mProspct );
   //:dHighMath    = 0
   dHighMath = 0;
   //:dHighVerbal  = 0
   dHighVerbal = 0;
   //:dHighWriting = 0
   dHighWriting = 0;
   //:FOR EACH  mProspct2.ExamHistory 
   RESULT = SetCursorFirstEntity( mProspct2, "ExamHistory", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF  mProspct2.ExamHistory.ExamType = "SAT" AND mProspct2.ExamHistory.Writing != ""
      if ( CompareAttributeToString( mProspct2, "ExamHistory", "ExamType", "SAT" ) == 0 && CompareAttributeToString( mProspct2, "ExamHistory", "Writing", "" ) != 0 )
      { 
         //:IF  mProspct2.ExamHistory.MathQuantitative > dHighMath  
         if ( CompareAttributeToDecimal( mProspct2, "ExamHistory", "MathQuantitative", dHighMath ) > 0 )
         { 
            //:dHighMath = mProspct2.ExamHistory.MathQuantitative 
            {MutableDouble md_dHighMath = new MutableDouble( dHighMath );
                         GetDecimalFromAttribute( md_dHighMath, mProspct2, "ExamHistory", "MathQuantitative" );
            dHighMath = md_dHighMath.doubleValue( );}
         } 

         //:END 
         //:IF mProspct2.ExamHistory.Verbal > dHighVerbal  
         if ( CompareAttributeToDecimal( mProspct2, "ExamHistory", "Verbal", dHighVerbal ) > 0 )
         { 
            //:dHighVerbal = mProspct2.ExamHistory.Verbal 
            {MutableDouble md_dHighVerbal = new MutableDouble( dHighVerbal );
                         GetDecimalFromAttribute( md_dHighVerbal, mProspct2, "ExamHistory", "Verbal" );
            dHighVerbal = md_dHighVerbal.doubleValue( );}
         } 

         //:END 
         //:IF mProspct2.ExamHistory.Writing > dHighWriting  
         if ( CompareAttributeToDecimal( mProspct2, "ExamHistory", "Writing", dHighWriting ) > 0 )
         { 
            //:dHighWriting = mProspct2.ExamHistory.Writing 
            {MutableDouble md_dHighWriting = new MutableDouble( dHighWriting );
                         GetDecimalFromAttribute( md_dHighWriting, mProspct2, "ExamHistory", "Writing" );
            dHighWriting = md_dHighWriting.doubleValue( );}
         } 

         //:END 
      } 

      RESULT = SetCursorNextEntity( mProspct2, "ExamHistory", "" );
      //:END
   } 

   //:END
   //:dTotal = dHighMath + dHighVerbal + dHighWriting
   dTotal = dHighMath + dHighVerbal + dHighWriting;
   //:ReturnedScore = dTotal
   ReturnedScore.setValue( dTotal );
   //:DropView( mProspct2 )
   DropView( mProspct2 );
   return;
// END
} 


//:GLOBAL OPERATION
//:GetTimeDifferenceInMinutes( VIEW StartTimeView,
//:                            STRING ( 32 ) StartTimeEntityName,
//:                            STRING ( 32 ) StartTimeAttributeName,
//:                            VIEW EndTimeView,
//:                            STRING ( 32 ) EndTimeEntityName,
//:                            STRING ( 32 ) EndTimeAttributeName )

//:   STRING ( 20 ) szStartTime
public int  
GetTimeDifferenceInMinutes( View     StartTimeView,
                            String   StartTimeEntityName,
                            String   StartTimeAttributeName,
                            View     EndTimeView,
                            String   EndTimeEntityName,
                            String   EndTimeAttributeName )
{
   String   szStartTime = null;
   //:STRING ( 20 ) szEndTime
   String   szEndTime = null;
   //:STRING ( 2 )  szTimeHours
   String   szTimeHours = null;
   //:STRING ( 2 )  szTimeMinutes
   String   szTimeMinutes = null;
   //:INTEGER  TimeHours
   int      TimeHours = 0;
   //:INTEGER  TimeMinutes
   int      TimeMinutes = 0;
   //:INTEGER  StartTimeInMinutes
   int      StartTimeInMinutes = 0;
   //:INTEGER  EndTimeInMinutes
   int      EndTimeInMinutes = 0;
   //:INTEGER  TimeDifference
   int      TimeDifference = 0;


   //:// Compute StartTime in basic Minutes from start of day.
   //:GetStringFromAttribute( szStartTime, StartTimeView, StartTimeEntityName, StartTimeAttributeName )
   {StringBuilder sb_szStartTime;
   if ( szStartTime == null )
      sb_szStartTime = new StringBuilder( 32 );
   else
      sb_szStartTime = new StringBuilder( szStartTime );
       GetStringFromAttribute( sb_szStartTime, StartTimeView, StartTimeEntityName, StartTimeAttributeName );
   szStartTime = sb_szStartTime.toString( );}
   //:szTimeHours   = szStartTime[9:2]
    {StringBuilder sb_szTimeHours;
   if ( szTimeHours == null )
      sb_szTimeHours = new StringBuilder( 32 );
   else
      sb_szTimeHours = new StringBuilder( szTimeHours );
      ZeidonStringCopy( sb_szTimeHours, 1, 0, szStartTime, 9, 2, 3 );
   szTimeHours = sb_szTimeHours.toString( );}
   //:szTimeMinutes = szStartTime[11:2]
    {StringBuilder sb_szTimeMinutes;
   if ( szTimeMinutes == null )
      sb_szTimeMinutes = new StringBuilder( 32 );
   else
      sb_szTimeMinutes = new StringBuilder( szTimeMinutes );
      ZeidonStringCopy( sb_szTimeMinutes, 1, 0, szStartTime, 11, 2, 3 );
   szTimeMinutes = sb_szTimeMinutes.toString( );}
   //:TimeHours   = zStringToInteger( szTimeHours )
   TimeHours = zStringToInteger( szTimeHours );
   //:TimeMinutes = zStringToInteger( szTimeMinutes )
   TimeMinutes = zStringToInteger( szTimeMinutes );
   //:StartTimeInMinutes = 60 * TimeHours + TimeMinutes
   StartTimeInMinutes = 60 * TimeHours + TimeMinutes;

   //:// Compute EndTime in basic Minutes from End of day.
   //:GetStringFromAttribute( szEndTime, EndTimeView, EndTimeEntityName, EndTimeAttributeName )
   {StringBuilder sb_szEndTime;
   if ( szEndTime == null )
      sb_szEndTime = new StringBuilder( 32 );
   else
      sb_szEndTime = new StringBuilder( szEndTime );
       GetStringFromAttribute( sb_szEndTime, EndTimeView, EndTimeEntityName, EndTimeAttributeName );
   szEndTime = sb_szEndTime.toString( );}
   //:szTimeHours   = szEndTime[9:2]
    {StringBuilder sb_szTimeHours;
   if ( szTimeHours == null )
      sb_szTimeHours = new StringBuilder( 32 );
   else
      sb_szTimeHours = new StringBuilder( szTimeHours );
      ZeidonStringCopy( sb_szTimeHours, 1, 0, szEndTime, 9, 2, 3 );
   szTimeHours = sb_szTimeHours.toString( );}
   //:szTimeMinutes = szEndTime[11:2]
    {StringBuilder sb_szTimeMinutes;
   if ( szTimeMinutes == null )
      sb_szTimeMinutes = new StringBuilder( 32 );
   else
      sb_szTimeMinutes = new StringBuilder( szTimeMinutes );
      ZeidonStringCopy( sb_szTimeMinutes, 1, 0, szEndTime, 11, 2, 3 );
   szTimeMinutes = sb_szTimeMinutes.toString( );}
   //:TimeHours   = zStringToInteger( szTimeHours )
   TimeHours = zStringToInteger( szTimeHours );
   //:TimeMinutes = zStringToInteger( szTimeMinutes )
   TimeMinutes = zStringToInteger( szTimeMinutes );
   //:EndTimeInMinutes = 60 * TimeHours + TimeMinutes
   EndTimeInMinutes = 60 * TimeHours + TimeMinutes;

   //:// Compute Difference.
   //:TimeDifference = EndTimeInMinutes - StartTimeInMinutes
   TimeDifference = EndTimeInMinutes - StartTimeInMinutes;
   //:RETURN TimeDifference
   return( TimeDifference );
// END
} 


//:GLOBAL OPERATION
//:PrintLegacyTranscript( VIEW ViewToWindow,
//:                       STRING ( 32 ) szTranscriptFileName,
//:                       VIEW mStudent BASED ON LOD  mStudent )

//:   VIEW lStuLTrn BASED ON LOD  lStuLTrn
public int 
PrintLegacyTranscript( View     ViewToWindow,
                       String   szTranscriptFileName,
                       View     mStudent )
{
   zVIEW    lStuLTrn = new zVIEW( );
   //:VIEW wXferO   REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 1 ) GradUndergradFlag
   String   GradUndergradFlag = null;
   //:INTEGER Length
   int      Length = 0;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE lStuLTrn WHERE lStuLTrn.Student.ID = mStudent.Student.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mStudent, "Student", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_14( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( lStuLTrn, "lStuLTrn", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:IF mStudent.AdministrativeDivision.Name = "Main Campus"
   if ( CompareAttributeToString( mStudent, "AdministrativeDivision", "Name", "Main Campus" ) == 0 )
   { 
      //:IF wXferO.Root.GradUndergradFlag = "G"
      if ( CompareAttributeToString( wXferO, "Root", "GradUndergradFlag", "G" ) == 0 )
      { 
         //:// Main Campus Graduate Transcript
         //:GetAttributeLength( Length, lStuLTrn, "Student", "LegacyGraduateTranscript" )
         {MutableInt mi_Length = new MutableInt( Length );
                   GetAttributeLength( mi_Length, lStuLTrn, "Student", "LegacyGraduateTranscript" );
         Length = mi_Length.intValue( );}
         //:IF Length = 0
         if ( Length == 0 )
         { 
            //:MessageSend( ViewToWindow, "", "Print Legacy Transcript",
            //:             "No Legacy Transcript exists for the selected type.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "Print Legacy Transcript", "No Legacy Transcript exists for the selected type.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
            m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
         //:FixLegacyReportDate( wXferO, lStuLTrn, "Student", "LegacyGraduateTranscript", "Report date: " )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
          m_ZGLOBAL1_Operation.FixLegacyReportDate( wXferO, lStuLTrn, "Student", "LegacyGraduateTranscript", "Report date: " );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szTranscriptFileName = ""
         if ( ZeidonStringCompare( szTranscriptFileName, 1, 0, "", 1, 0, 33 ) == 0 )
         { 
            //:PrintTextBlob( ViewToWindow, "Print Legacy Transcript",
            //:               lStuLTrn, "Student", "LegacyGraduateTranscript", "Courier New", 85 )
            m_ZDRVROPR.PrintTextBlob( ViewToWindow, "Print Legacy Transcript", lStuLTrn, "Student", "LegacyGraduateTranscript", "Courier New", 85 );
            //:ELSE
         } 
         else
         { 
            //:WriteBlobToFile( lStuLTrn, "Student", "LegacyGraduateTranscript", szTranscriptFileName )
            WriteBlobToFile( lStuLTrn, "Student", "LegacyGraduateTranscript", szTranscriptFileName );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF wXferO.Root.GradUndergradFlag = "C"
         if ( CompareAttributeToString( wXferO, "Root", "GradUndergradFlag", "C" ) == 0 )
         { 
            //:// Main Campus Certificate Transcript (Invalid Request)
            //:MessageSend( ViewToWindow, "", "Print Transcript",
            //:             "A Certificate Transcript cannot be requested for Main Campus.",
            //:             zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
            MessageSend( ViewToWindow, "", "Print Transcript", "A Certificate Transcript cannot be requested for Main Campus.", zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
            //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
            m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
            //:RETURN -1
            if(8==8)return( -1 );
            //:ELSE
         } 
         else
         { 
            //:// Main Campus Undergraduate Transcript
            //:GetAttributeLength( Length, lStuLTrn, "Student", "LegacyUndergraduateTranscript" )
            {MutableInt mi_Length = new MutableInt( Length );
                         GetAttributeLength( mi_Length, lStuLTrn, "Student", "LegacyUndergraduateTranscript" );
            Length = mi_Length.intValue( );}
            //:IF Length = 0
            if ( Length == 0 )
            { 
               //:MessageSend( ViewToWindow, "", "Print Legacy Transcript",
               //:             "No Legacy Transcript exists for the selected type.",
               //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               MessageSend( ViewToWindow, "", "Print Legacy Transcript", "No Legacy Transcript exists for the selected type.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
               m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
               //:RETURN -1
               if(8==8)return( -1 );
            } 

            //:END
            //:FixLegacyReportDate( wXferO, lStuLTrn, "Student", "LegacyUndergraduateTranscript", "Report date: " )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
             m_ZGLOBAL1_Operation.FixLegacyReportDate( wXferO, lStuLTrn, "Student", "LegacyUndergraduateTranscript", "Report date: " );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:IF szTranscriptFileName = ""
            if ( ZeidonStringCompare( szTranscriptFileName, 1, 0, "", 1, 0, 33 ) == 0 )
            { 
               //:PrintTextBlob( ViewToWindow, "Print Legacy Transcript",
               //:               lStuLTrn, "Student", "LegacyUndergraduateTranscript", "Courier New", 85 )
               m_ZDRVROPR.PrintTextBlob( ViewToWindow, "Print Legacy Transcript", lStuLTrn, "Student", "LegacyUndergraduateTranscript", "Courier New", 85 );
               //:ELSE
            } 
            else
            { 
               //:WriteBlobToFile( lStuLTrn, "Student", "LegacyUndergraduateTranscript", szTranscriptFileName )
               WriteBlobToFile( lStuLTrn, "Student", "LegacyUndergraduateTranscript", szTranscriptFileName );
            } 

            //:END
         } 

         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:IF wXferO.Root.GradUndergradFlag = "G"
      if ( CompareAttributeToString( wXferO, "Root", "GradUndergradFlag", "G" ) == 0 )
      { 
         //:// Adult Studies Graduate Transcript
         //:GetAttributeLength( Length, lStuLTrn, "Student", "LegacyGraduateAS_Transcript" )
         {MutableInt mi_Length = new MutableInt( Length );
                   GetAttributeLength( mi_Length, lStuLTrn, "Student", "LegacyGraduateAS_Transcript" );
         Length = mi_Length.intValue( );}
         //:IF Length = 0
         if ( Length == 0 )
         { 
            //:MessageSend( ViewToWindow, "", "Print Legacy Transcript",
            //:             "No Legacy Transcript exists for the selected type.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "Print Legacy Transcript", "No Legacy Transcript exists for the selected type.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
            m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
         //:FixLegacyReportDate( wXferO, lStuLTrn, "Student", "LegacyGraduateAS_Transcript", "Report date: " )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
          m_ZGLOBAL1_Operation.FixLegacyReportDate( wXferO, lStuLTrn, "Student", "LegacyGraduateAS_Transcript", "Report date: " );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szTranscriptFileName = ""
         if ( ZeidonStringCompare( szTranscriptFileName, 1, 0, "", 1, 0, 33 ) == 0 )
         { 
            //:PrintTextBlob( ViewToWindow, "Print Legacy Transcript",
            //:               lStuLTrn, "Student", "LegacyGraduateAS_Transcript", "Courier New", 85 )
            m_ZDRVROPR.PrintTextBlob( ViewToWindow, "Print Legacy Transcript", lStuLTrn, "Student", "LegacyGraduateAS_Transcript", "Courier New", 85 );
            //:ELSE
         } 
         else
         { 
            //:WriteBlobToFile( lStuLTrn, "Student", "LegacyGraduateAS_Transcript", szTranscriptFileName )
            WriteBlobToFile( lStuLTrn, "Student", "LegacyGraduateAS_Transcript", szTranscriptFileName );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF wXferO.Root.GradUndergradFlag = "C"
         if ( CompareAttributeToString( wXferO, "Root", "GradUndergradFlag", "C" ) == 0 )
         { 
            //:// Adult Studies Certificate Transcript
            //:GetAttributeLength( Length, lStuLTrn, "Student", "LegacyCertificateTranscript" )
            {MutableInt mi_Length = new MutableInt( Length );
                         GetAttributeLength( mi_Length, lStuLTrn, "Student", "LegacyCertificateTranscript" );
            Length = mi_Length.intValue( );}
            //:IF Length = 0
            if ( Length == 0 )
            { 
               //:MessageSend( ViewToWindow, "", "Print Legacy Transcript",
               //:             "No Legacy Transcript exists for the selected type.",
               //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               MessageSend( ViewToWindow, "", "Print Legacy Transcript", "No Legacy Transcript exists for the selected type.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
               m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
               //:RETURN -1
               if(8==8)return( -1 );
            } 

            //:END
            //:FixLegacyReportDate( wXferO, lStuLTrn, "Student", "LegacyCertificateTranscript", "Report date: " )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
             m_ZGLOBAL1_Operation.FixLegacyReportDate( wXferO, lStuLTrn, "Student", "LegacyCertificateTranscript", "Report date: " );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:IF szTranscriptFileName = ""
            if ( ZeidonStringCompare( szTranscriptFileName, 1, 0, "", 1, 0, 33 ) == 0 )
            { 
               //:PrintTextBlob( ViewToWindow, "Print Legacy Transcript",
               //:               lStuLTrn, "Student", "LegacyCertificateTranscript", "Courier New", 85 )
               m_ZDRVROPR.PrintTextBlob( ViewToWindow, "Print Legacy Transcript", lStuLTrn, "Student", "LegacyCertificateTranscript", "Courier New", 85 );
               //:ELSE
            } 
            else
            { 
               //:WriteBlobToFile( lStuLTrn, "Student", "LegacyCertificateTranscript", szTranscriptFileName )
               WriteBlobToFile( lStuLTrn, "Student", "LegacyCertificateTranscript", szTranscriptFileName );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:// Adult Studies Undergraduate Transcript
            //:GetAttributeLength( Length, lStuLTrn, "Student", "LegacyUndergraduateAS_Transcript" )
            {MutableInt mi_Length = new MutableInt( Length );
                         GetAttributeLength( mi_Length, lStuLTrn, "Student", "LegacyUndergraduateAS_Transcript" );
            Length = mi_Length.intValue( );}
            //:IF Length = 0
            if ( Length == 0 )
            { 
               //:MessageSend( ViewToWindow, "", "Print Legacy Transcript",
               //:             "No Legacy Transcript exists for the selected type.",
               //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               MessageSend( ViewToWindow, "", "Print Legacy Transcript", "No Legacy Transcript exists for the selected type.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
               m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
               //:RETURN -1
               if(8==8)return( -1 );
            } 

            //:END
            //:FixLegacyReportDate( wXferO, lStuLTrn, "Student", "LegacyUndergraduateAS_Transcript", "Report date: " )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
             m_ZGLOBAL1_Operation.FixLegacyReportDate( wXferO, lStuLTrn, "Student", "LegacyUndergraduateAS_Transcript", "Report date: " );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:IF szTranscriptFileName = ""
            if ( ZeidonStringCompare( szTranscriptFileName, 1, 0, "", 1, 0, 33 ) == 0 )
            { 
               //:PrintTextBlob( ViewToWindow, "Print Legacy Transcript",
               //:               lStuLTrn, "Student", "LegacyUndergraduateAS_Transcript", "Courier New", 85 )
               m_ZDRVROPR.PrintTextBlob( ViewToWindow, "Print Legacy Transcript", lStuLTrn, "Student", "LegacyUndergraduateAS_Transcript", "Courier New", 85 );
               //:ELSE
            } 
            else
            { 
               //:WriteBlobToFile( lStuLTrn, "Student", "LegacyUndergraduateAS_Transcript", szTranscriptFileName )
               WriteBlobToFile( lStuLTrn, "Student", "LegacyUndergraduateAS_Transcript", szTranscriptFileName );
            } 

            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:END
   //:DropObjectInstance( lStuLTrn )
   DropObjectInstance( lStuLTrn );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:GenerateTransactionEntryWDesc( VIEW OIView,
//:                               STRING ( 50 ) szDescription )

//:   VIEW mUser BASED ON LOD  mUser
public int 
GenerateTransactionEntryWDesc( View     OIView,
                               String   szDescription )
{
   zVIEW    mUser = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;
   int      RESULT = 0;


   //:nRC = ObjectInstanceUpdated( OIView )
   nRC = ObjectInstanceUpdated( OIView );
   //:IF nRC > 0
   if ( nRC > 0 )
   { 
      //:nRC = GetViewByName( mUser, "mUser", OIView, zLEVEL_APPLICATION )
      nRC = GetViewByName( mUser, "mUser", OIView, zLEVEL_APPLICATION );
      //:IF nRC >= 0
      if ( nRC >= 0 )
      { 
         //:SET CURSOR LAST OIView.Transaction
         RESULT = SetCursorLastEntity( OIView, "Transaction", "" );
         //:CREATE ENTITY OIView.Transaction
         RESULT = CreateEntity( OIView, "Transaction", zPOS_AFTER );
         //:OIView.Transaction.UserID      = mUser.User.ID
         SetAttributeFromAttribute( OIView, "Transaction", "UserID", mUser, "User", "ID" );
         //:OIView.Transaction.UserName    = mUser.User.UserName
         SetAttributeFromAttribute( OIView, "Transaction", "UserName", mUser, "User", "UserName" );
         //:OIView.Transaction.Description = szDescription
         SetAttributeFromString( OIView, "Transaction", "Description", szDescription );
         //:SetAttributeFromCurrentDateTime( OIView, "Transaction", "TransDateTime" )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( OIView );
          m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( OIView, "Transaction", "TransDateTime" );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:GetPersonFullNameLFM( STRING ( 100 ) szFullName,
//:                      VIEW           vOI,
//:                      STRING ( 32 )  szEntityName )

//:   STRING ( 30 )  szFirstName
public void 
GetPersonFullNameLFM( StringBuilder   szFullName,
                      View     vOI,
                      String   szEntityName )
{
   String   szFirstName = null;
   //:STRING ( 30 )  szMiddleName
   String   szMiddleName = null;
   //:STRING ( 30 )  szLastName
   String   szLastName = null;
   //:STRING ( 10 )  szSuffix
   String   szSuffix = null;



   //:GetStringFromAttribute( szLastName, vOI, szEntityName, "LastName" )
   {StringBuilder sb_szLastName;
   if ( szLastName == null )
      sb_szLastName = new StringBuilder( 32 );
   else
      sb_szLastName = new StringBuilder( szLastName );
       GetStringFromAttribute( sb_szLastName, vOI, szEntityName, "LastName" );
   szLastName = sb_szLastName.toString( );}
   //:GetStringFromAttribute( szSuffix, vOI, szEntityName, "Suffix" )
   {StringBuilder sb_szSuffix;
   if ( szSuffix == null )
      sb_szSuffix = new StringBuilder( 32 );
   else
      sb_szSuffix = new StringBuilder( szSuffix );
       GetStringFromAttribute( sb_szSuffix, vOI, szEntityName, "Suffix" );
   szSuffix = sb_szSuffix.toString( );}
   //:IF szSuffix != ""
   if ( ZeidonStringCompare( szSuffix, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szLastName = szLastName + " " + szSuffix
       {StringBuilder sb_szLastName;
      if ( szLastName == null )
         sb_szLastName = new StringBuilder( 32 );
      else
         sb_szLastName = new StringBuilder( szLastName );
            ZeidonStringConcat( sb_szLastName, 1, 0, " ", 1, 0, 31 );
      szLastName = sb_szLastName.toString( );}
       {StringBuilder sb_szLastName;
      if ( szLastName == null )
         sb_szLastName = new StringBuilder( 32 );
      else
         sb_szLastName = new StringBuilder( szLastName );
            ZeidonStringConcat( sb_szLastName, 1, 0, szSuffix, 1, 0, 31 );
      szLastName = sb_szLastName.toString( );}
   } 

   //:END
   //:szFullName = szLastName + ","
   ZeidonStringCopy( szFullName, 1, 0, szLastName, 1, 0, 101 );
   ZeidonStringConcat( szFullName, 1, 0, ",", 1, 0, 101 );
   //:GetStringFromAttribute( szFirstName, vOI, szEntityName, "FirstName" )
   {StringBuilder sb_szFirstName;
   if ( szFirstName == null )
      sb_szFirstName = new StringBuilder( 32 );
   else
      sb_szFirstName = new StringBuilder( szFirstName );
       GetStringFromAttribute( sb_szFirstName, vOI, szEntityName, "FirstName" );
   szFirstName = sb_szFirstName.toString( );}
   //:IF szFirstName != ""
   if ( ZeidonStringCompare( szFirstName, 1, 0, "", 1, 0, 31 ) != 0 )
   { 
      //:szFullName = szFullName + " " + szFirstName
      ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
      ZeidonStringConcat( szFullName, 1, 0, szFirstName, 1, 0, 101 );
   } 

   //:END
   //:GetStringFromAttribute( szMiddleName, vOI, szEntityName, "MiddleName" )
   {StringBuilder sb_szMiddleName;
   if ( szMiddleName == null )
      sb_szMiddleName = new StringBuilder( 32 );
   else
      sb_szMiddleName = new StringBuilder( szMiddleName );
       GetStringFromAttribute( sb_szMiddleName, vOI, szEntityName, "MiddleName" );
   szMiddleName = sb_szMiddleName.toString( );}
   //:IF szMiddleName != ""
   if ( ZeidonStringCompare( szMiddleName, 1, 0, "", 1, 0, 31 ) != 0 )
   { 
      //:szFullName = szFullName + " " + szMiddleName
      ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
      ZeidonStringConcat( szFullName, 1, 0, szMiddleName, 1, 0, 101 );
   } 

   //:END
   return;
// END
} 


//:GLOBAL OPERATION
//:GenAndSetStudentEmailAddress( VIEW mStudent,
//:                              STRING ( 50 ) StudentEntityName,
//:                              STRING ( 50 ) PersonEntityName )

//:   VIEW mProsStu  BASED ON LOD mProsStu
public int 
GenAndSetStudentEmailAddress( View     mStudent,
                              String   StudentEntityName,
                              String   PersonEntityName )
{
   zVIEW    mProsStu = new zVIEW( );
   //:VIEW mUser     BASED ON LOD mUser
   zVIEW    mUser = new zVIEW( );
   //:STRING ( 1 )  MiddleInitial
   String   MiddleInitial = null;
   //:STRING ( 50 ) FirstName
   String   FirstName = null;
   //:STRING ( 50 ) LastName
   String   LastName = null;
   //:STRING ( 50 ) eMailBase
   String   eMailBase = null;
   //:STRING ( 50 ) eMailAddress
   String   eMailAddress = null;
   //:STRING ( 2 )  szCount
   String   szCount = null;
   //:INTEGER       Count
   int      Count = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );


   //:GetStringFromAttribute( FirstName, mStudent, PersonEntityName, "FirstName" )
   {StringBuilder sb_FirstName;
   if ( FirstName == null )
      sb_FirstName = new StringBuilder( 32 );
   else
      sb_FirstName = new StringBuilder( FirstName );
       GetStringFromAttribute( sb_FirstName, mStudent, PersonEntityName, "FirstName" );
   FirstName = sb_FirstName.toString( );}
   //:GetStringFromAttribute( LastName, mStudent, PersonEntityName, "LastName" )
   {StringBuilder sb_LastName;
   if ( LastName == null )
      sb_LastName = new StringBuilder( 32 );
   else
      sb_LastName = new StringBuilder( LastName );
       GetStringFromAttribute( sb_LastName, mStudent, PersonEntityName, "LastName" );
   LastName = sb_LastName.toString( );}
   //:GetStringFromAttribute( MiddleInitial, mStudent, PersonEntityName, "MiddleName" )
   {StringBuilder sb_MiddleInitial;
   if ( MiddleInitial == null )
      sb_MiddleInitial = new StringBuilder( 32 );
   else
      sb_MiddleInitial = new StringBuilder( MiddleInitial );
       GetStringFromAttribute( sb_MiddleInitial, mStudent, PersonEntityName, "MiddleName" );
   MiddleInitial = sb_MiddleInitial.toString( );}
   //:MiddleInitial = mStudent.Person.MiddleName 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_MiddleInitial;
   if ( MiddleInitial == null )
      sb_MiddleInitial = new StringBuilder( 32 );
   else
      sb_MiddleInitial = new StringBuilder( MiddleInitial );
       GetVariableFromAttribute( sb_MiddleInitial, mi_lTempInteger_0, 'S', 2, mStudent, "Person", "MiddleName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   MiddleInitial = sb_MiddleInitial.toString( );}
   //:IF MiddleInitial = ""
   if ( ZeidonStringCompare( MiddleInitial, 1, 0, "", 1, 0, 2 ) == 0 )
   { 
      //:eMailBase = FirstName + "." + LastName
       {StringBuilder sb_eMailBase;
      if ( eMailBase == null )
         sb_eMailBase = new StringBuilder( 32 );
      else
         sb_eMailBase = new StringBuilder( eMailBase );
            ZeidonStringCopy( sb_eMailBase, 1, 0, FirstName, 1, 0, 51 );
      eMailBase = sb_eMailBase.toString( );}
       {StringBuilder sb_eMailBase;
      if ( eMailBase == null )
         sb_eMailBase = new StringBuilder( 32 );
      else
         sb_eMailBase = new StringBuilder( eMailBase );
            ZeidonStringConcat( sb_eMailBase, 1, 0, ".", 1, 0, 51 );
      eMailBase = sb_eMailBase.toString( );}
       {StringBuilder sb_eMailBase;
      if ( eMailBase == null )
         sb_eMailBase = new StringBuilder( 32 );
      else
         sb_eMailBase = new StringBuilder( eMailBase );
            ZeidonStringConcat( sb_eMailBase, 1, 0, LastName, 1, 0, 51 );
      eMailBase = sb_eMailBase.toString( );}
      //:ELSE
   } 
   else
   { 
      //:eMailBase = FirstName + "." + MiddleInitial + "." + LastName
       {StringBuilder sb_eMailBase;
      if ( eMailBase == null )
         sb_eMailBase = new StringBuilder( 32 );
      else
         sb_eMailBase = new StringBuilder( eMailBase );
            ZeidonStringCopy( sb_eMailBase, 1, 0, FirstName, 1, 0, 51 );
      eMailBase = sb_eMailBase.toString( );}
       {StringBuilder sb_eMailBase;
      if ( eMailBase == null )
         sb_eMailBase = new StringBuilder( 32 );
      else
         sb_eMailBase = new StringBuilder( eMailBase );
            ZeidonStringConcat( sb_eMailBase, 1, 0, ".", 1, 0, 51 );
      eMailBase = sb_eMailBase.toString( );}
       {StringBuilder sb_eMailBase;
      if ( eMailBase == null )
         sb_eMailBase = new StringBuilder( 32 );
      else
         sb_eMailBase = new StringBuilder( eMailBase );
            ZeidonStringConcat( sb_eMailBase, 1, 0, MiddleInitial, 1, 0, 51 );
      eMailBase = sb_eMailBase.toString( );}
       {StringBuilder sb_eMailBase;
      if ( eMailBase == null )
         sb_eMailBase = new StringBuilder( 32 );
      else
         sb_eMailBase = new StringBuilder( eMailBase );
            ZeidonStringConcat( sb_eMailBase, 1, 0, ".", 1, 0, 51 );
      eMailBase = sb_eMailBase.toString( );}
       {StringBuilder sb_eMailBase;
      if ( eMailBase == null )
         sb_eMailBase = new StringBuilder( 32 );
      else
         sb_eMailBase = new StringBuilder( eMailBase );
            ZeidonStringConcat( sb_eMailBase, 1, 0, LastName, 1, 0, 51 );
      eMailBase = sb_eMailBase.toString( );}
   } 

   //:END
   //:eMailAddress = eMailBase + "@enc.edu"
    {StringBuilder sb_eMailAddress;
   if ( eMailAddress == null )
      sb_eMailAddress = new StringBuilder( 32 );
   else
      sb_eMailAddress = new StringBuilder( eMailAddress );
      ZeidonStringCopy( sb_eMailAddress, 1, 0, eMailBase, 1, 0, 51 );
   eMailAddress = sb_eMailAddress.toString( );}
    {StringBuilder sb_eMailAddress;
   if ( eMailAddress == null )
      sb_eMailAddress = new StringBuilder( 32 );
   else
      sb_eMailAddress = new StringBuilder( eMailAddress );
      ZeidonStringConcat( sb_eMailAddress, 1, 0, "@enc.edu", 1, 0, 51 );
   eMailAddress = sb_eMailAddress.toString( );}
   //:ACTIVATE mProsStu WHERE mProsStu.Student.eMailAddress = eMailAddress
   o_fnLocalBuildQual_12( mStudent, vTempViewVar_0, eMailAddress );
   RESULT = ActivateObjectInstance( mProsStu, "mProsStu", mStudent, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:Count = 1
      Count = 1;
      //:LOOP WHILE RESULT >= 0
      while ( RESULT >= 0 )
      { 
         //:DropObjectInstance( mProsStu )
         DropObjectInstance( mProsStu );
         //:Count = Count + 1
         Count = Count + 1;
         //:szCount = Count
          {StringBuilder sb_szCount;
         if ( szCount == null )
            sb_szCount = new StringBuilder( 32 );
         else
            sb_szCount = new StringBuilder( szCount );
                  ZeidonStringConvertFromNumber( sb_szCount, 1, 0, 2, Count, (double) 0.0, "I" );
         szCount = sb_szCount.toString( );}
         //:eMailAddress = eMailBase + szCount + "@enc.edu"
          {StringBuilder sb_eMailAddress;
         if ( eMailAddress == null )
            sb_eMailAddress = new StringBuilder( 32 );
         else
            sb_eMailAddress = new StringBuilder( eMailAddress );
                  ZeidonStringCopy( sb_eMailAddress, 1, 0, eMailBase, 1, 0, 51 );
         eMailAddress = sb_eMailAddress.toString( );}
          {StringBuilder sb_eMailAddress;
         if ( eMailAddress == null )
            sb_eMailAddress = new StringBuilder( 32 );
         else
            sb_eMailAddress = new StringBuilder( eMailAddress );
                  ZeidonStringConcat( sb_eMailAddress, 1, 0, szCount, 1, 0, 51 );
         eMailAddress = sb_eMailAddress.toString( );}
          {StringBuilder sb_eMailAddress;
         if ( eMailAddress == null )
            sb_eMailAddress = new StringBuilder( 32 );
         else
            sb_eMailAddress = new StringBuilder( eMailAddress );
                  ZeidonStringConcat( sb_eMailAddress, 1, 0, "@enc.edu", 1, 0, 51 );
         eMailAddress = sb_eMailAddress.toString( );}
         //:ACTIVATE mProsStu WHERE mProsStu.Student.eMailAddress = eMailAddress
         o_fnLocalBuildQual_13( mStudent, vTempViewVar_1, eMailAddress );
         RESULT = ActivateObjectInstance( mProsStu, "mProsStu", mStudent, vTempViewVar_1, zSINGLE );
         DropView( vTempViewVar_1 );
      } 

      //:END
   } 

   //:END
   //:DropObjectInstance( mProsStu )
   DropObjectInstance( mProsStu );
   //:SetAttributeFromString( mStudent, "Student", "eMailAddress", eMailAddress )
   SetAttributeFromString( mStudent, "Student", "eMailAddress", eMailAddress );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:SetUpAndStartEmailClientForListR( VIEW mConList,
//:                                  STRING ( 32 ) szIncludeEntityName,
//:                                  STRING ( 32 ) szCreateItemEntityName,
//:                                  STRING ( 32 ) szEmailEntityName,
//:                                  STRING ( 32 ) szEmailAttributeName,
//:                                  STRING ( 32 ) szAlternateEmailEntityName,
//:                                  STRING ( 32 ) szSelectedEntityName,
//:                                  STRING ( 1 )  szAllEntriesFlag,
//:                                  STRING ( 1 )  szBlindCopyFlag,
//:                                  STRING ( 256) szSubjectLine,
//:                                  STRING ( 5000 ) szBody )
//:   
//:   VIEW mUser  BASED ON LOD  mUser
public int 
SetUpAndStartEmailClientForListR( View     mConList,
                                  String   szIncludeEntityName,
                                  String   szCreateItemEntityName,
                                  String   szEmailEntityName,
                                  String   szEmailAttributeName,
                                  String   szAlternateEmailEntityName,
                                  String   szSelectedEntityName,
                                  String   szAllEntriesFlag,
                                  String   szBlindCopyFlag,
                                  String   szSubjectLine,
                                  String   szBody )
{
   zVIEW    mUser = new zVIEW( );
   //:VIEW wXferO REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mConListT
   zVIEW    mConListT = new zVIEW( );
   //:STRING ( 256 ) szEMailAddress
   String   szEMailAddress = null;
   //:STRING ( 256 ) szAlternateEMailAddress
   String   szAlternateEMailAddress = null;
   //:STRING ( 256 ) Msg
   String   Msg = null;
   //:STRING ( 32 )  szObjectName
   String   szObjectName = null;
   //:STRING ( 50 )  szPersonName
   String   szPersonName = null;
   //:SHORT   nRC
   int      nRC = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mConList, zLEVEL_TASK );

   //:// Perform common setup for calling StartEmailClientForList, which will format the email address from
   //:// entries in a Contact List. This Contact List will be built temporarily from the input Contact List, so
   //:// that it only contains the desired entries that have email addresses.
   //:// This includes building a work mConList of those entries to be included in the list.
   //:// If szAllEntriesFlag flag is "Y", it contains all entries that have an Email address.
   //:// If szAllEntriesFlag flag is "N", it contains all selected entries that have an Email address.

   //:GetViewByName( mUser, "mUser", mConList, zLEVEL_APPLICATION )
   GetViewByName( mUser, "mUser", mConList, zLEVEL_APPLICATION );

   //:// Create temporary Contact List with Email entries.
   //:MiGetObjectNameForView( szObjectName, mConList )
   {StringBuilder sb_szObjectName;
   if ( szObjectName == null )
      sb_szObjectName = new StringBuilder( 32 );
   else
      sb_szObjectName = new StringBuilder( szObjectName );
       MiGetObjectNameForView( sb_szObjectName, mConList );
   szObjectName = sb_szObjectName.toString( );}
   //:ActivateEmptyObjectInstance( mConListT, szObjectName, mConList, zSINGLE )
   ActivateEmptyObjectInstance( mConListT, szObjectName, mConList, zSINGLE );
   //:CREATE ENTITY mConListT.ContactList 
   RESULT = CreateEntity( mConListT, "ContactList", zPOS_AFTER );
   //:nRC = SetCursorFirstEntity( mConList, szIncludeEntityName, "ContactList" )
   nRC = SetCursorFirstEntity( mConList, szIncludeEntityName, "ContactList" );
   //:LOOP WHILE nRC >= zCURSOR_SET
   while ( nRC >= zCURSOR_SET )
   { 
      //:szAlternateEMailAddress = ""
       {StringBuilder sb_szAlternateEMailAddress;
      if ( szAlternateEMailAddress == null )
         sb_szAlternateEMailAddress = new StringBuilder( 32 );
      else
         sb_szAlternateEMailAddress = new StringBuilder( szAlternateEMailAddress );
            ZeidonStringCopy( sb_szAlternateEMailAddress, 1, 0, "", 1, 0, 257 );
      szAlternateEMailAddress = sb_szAlternateEMailAddress.toString( );}
      //:IF szAllEntriesFlag = "Y"
      if ( ZeidonStringCompare( szAllEntriesFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:// List is made up of ALL Contact List entries with Email addresses.
         //:GetStringFromAttribute( szEMailAddress, mConList, szEmailEntityName, szEmailAttributeName )
         {StringBuilder sb_szEMailAddress;
         if ( szEMailAddress == null )
            sb_szEMailAddress = new StringBuilder( 32 );
         else
            sb_szEMailAddress = new StringBuilder( szEMailAddress );
                   GetStringFromAttribute( sb_szEMailAddress, mConList, szEmailEntityName, szEmailAttributeName );
         szEMailAddress = sb_szEMailAddress.toString( );}
         //:IF szEMailAddress = "" AND szAlternateEmailEntityName != ""
         if ( ZeidonStringCompare( szEMailAddress, 1, 0, "", 1, 0, 257 ) == 0 && ZeidonStringCompare( szAlternateEmailEntityName, 1, 0, "", 1, 0, 33 ) != 0 )
         { 
            //:GetStringFromAttribute( szEMailAddress, mConList, szAlternateEmailEntityName, szEmailAttributeName )
            {StringBuilder sb_szEMailAddress;
            if ( szEMailAddress == null )
               sb_szEMailAddress = new StringBuilder( 32 );
            else
               sb_szEMailAddress = new StringBuilder( szEMailAddress );
                         GetStringFromAttribute( sb_szEMailAddress, mConList, szAlternateEmailEntityName, szEmailAttributeName );
            szEMailAddress = sb_szEMailAddress.toString( );}
            //:szAlternateEMailAddress = szEMailAddress
             {StringBuilder sb_szAlternateEMailAddress;
            if ( szAlternateEMailAddress == null )
               sb_szAlternateEMailAddress = new StringBuilder( 32 );
            else
               sb_szAlternateEMailAddress = new StringBuilder( szAlternateEMailAddress );
                        ZeidonStringCopy( sb_szAlternateEMailAddress, 1, 0, szEMailAddress, 1, 0, 257 );
            szAlternateEMailAddress = sb_szAlternateEMailAddress.toString( );}
         } 

         //:END
         //:IF szEMailAddress != ""
         if ( ZeidonStringCompare( szEMailAddress, 1, 0, "", 1, 0, 257 ) != 0 )
         { 
            //:IF szCreateItemEntityName != ""
            if ( ZeidonStringCompare( szCreateItemEntityName, 1, 0, "", 1, 0, 33 ) != 0 )
            { 
               //:CreateEntity( mConListT, szCreateItemEntityName, zPOS_AFTER )
               CreateEntity( mConListT, szCreateItemEntityName, zPOS_AFTER );
            } 

            //:END
            //:IncludeSubobjectFromSubobject( mConListT, szIncludeEntityName,
            //:                               mConList,  szIncludeEntityName, zPOS_AFTER )
            IncludeSubobjectFromSubobject( mConListT, szIncludeEntityName, mConList, szIncludeEntityName, zPOS_AFTER );
            //:// Make sure that the eMail attribute is set as it could be a work attribute.
            //:SetAttributeFromAttribute( mConListT, szEmailEntityName, szEmailAttributeName,
            //:                           mConList,  szEmailEntityName, szEmailAttributeName )
            SetAttributeFromAttribute( mConListT, szEmailEntityName, szEmailAttributeName, mConList, szEmailEntityName, szEmailAttributeName );
            //:                           
            //:// If the Email Address from the alternate entity was used, we have to set it in the temporary object.
            //:IF szAlternateEMailAddress != ""
            if ( ZeidonStringCompare( szAlternateEMailAddress, 1, 0, "", 1, 0, 257 ) != 0 )
            { 
               //:SetAttributeFromString( mConListT, szEmailEntityName, szEmailAttributeName, szAlternateEMailAddress )
               SetAttributeFromString( mConListT, szEmailEntityName, szEmailAttributeName, szAlternateEMailAddress );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:// Format error message containing name. Most names are from the Person entity, which is the email address.
            //:// There are a few expections, however, as defined below.
            //:IF szIncludeEntityName = "Employee"
            if ( ZeidonStringCompare( szIncludeEntityName, 1, 0, "Employee", 1, 0, 33 ) == 0 )
            { 
               //:GetStringFromAttribute( szPersonName, mConList, "EmployeePerson", "dFullNameLFM" )
               {StringBuilder sb_szPersonName;
               if ( szPersonName == null )
                  sb_szPersonName = new StringBuilder( 32 );
               else
                  sb_szPersonName = new StringBuilder( szPersonName );
                               GetStringFromAttribute( sb_szPersonName, mConList, "EmployeePerson", "dFullNameLFM" );
               szPersonName = sb_szPersonName.toString( );}
               //:ELSE
            } 
            else
            { 
               //:IF szIncludeEntityName = "Faculty"
               if ( ZeidonStringCompare( szIncludeEntityName, 1, 0, "Faculty", 1, 0, 33 ) == 0 )
               { 
                  //:GetStringFromAttribute( szPersonName, mConList, "FacultyPerson", "dFullNameLFM" )
                  {StringBuilder sb_szPersonName;
                  if ( szPersonName == null )
                     sb_szPersonName = new StringBuilder( 32 );
                  else
                     sb_szPersonName = new StringBuilder( szPersonName );
                                     GetStringFromAttribute( sb_szPersonName, mConList, "FacultyPerson", "dFullNameLFM" );
                  szPersonName = sb_szPersonName.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:GetStringFromAttribute( szPersonName, mConList, szEmailEntityName, "dFullNameLFM" )
                  {StringBuilder sb_szPersonName;
                  if ( szPersonName == null )
                     sb_szPersonName = new StringBuilder( 32 );
                  else
                     sb_szPersonName = new StringBuilder( szPersonName );
                                     GetStringFromAttribute( sb_szPersonName, mConList, szEmailEntityName, "dFullNameLFM" );
                  szPersonName = sb_szPersonName.toString( );}
               } 

               //:END
            } 

            //:END
            //:Msg = "No Email Address is specified for " + szPersonName + "."
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringCopy( sb_Msg, 1, 0, "No Email Address is specified for ", 1, 0, 257 );
            Msg = sb_Msg.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringConcat( sb_Msg, 1, 0, szPersonName, 1, 0, 257 );
            Msg = sb_Msg.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringConcat( sb_Msg, 1, 0, ".", 1, 0, 257 );
            Msg = sb_Msg.toString( );}
            //:MessageSend( mConList, "", "Generate Email", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mConList, "", "Generate Email", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// List is made up of SELECTED Contact List entries with Email addresses.
         //:// We will check both szEmailEntityName and szIncludeEntityName because there are OL controls
         //:// that select each of them.
         //:nRC = GetSelectStateOfEntity( mConList, szSelectedEntityName )
         nRC = GetSelectStateOfEntity( mConList, szSelectedEntityName );
         //:IF nRC = 0
         if ( nRC == 0 )
         { 
            //:nRC = GetSelectStateOfEntity( mConList, szIncludeEntityName )
            nRC = GetSelectStateOfEntity( mConList, szIncludeEntityName );
         } 

         //:END
         //:IF nRC = 1
         if ( nRC == 1 )
         { 
            //:GetStringFromAttribute( szEMailAddress, mConList, szEmailEntityName, szEmailAttributeName )
            {StringBuilder sb_szEMailAddress;
            if ( szEMailAddress == null )
               sb_szEMailAddress = new StringBuilder( 32 );
            else
               sb_szEMailAddress = new StringBuilder( szEMailAddress );
                         GetStringFromAttribute( sb_szEMailAddress, mConList, szEmailEntityName, szEmailAttributeName );
            szEMailAddress = sb_szEMailAddress.toString( );}
            //:IF szEMailAddress = "" AND szAlternateEmailEntityName != ""
            if ( ZeidonStringCompare( szEMailAddress, 1, 0, "", 1, 0, 257 ) == 0 && ZeidonStringCompare( szAlternateEmailEntityName, 1, 0, "", 1, 0, 33 ) != 0 )
            { 
               //:GetStringFromAttribute( szEMailAddress, mConList, szAlternateEmailEntityName, szEmailAttributeName )
               {StringBuilder sb_szEMailAddress;
               if ( szEMailAddress == null )
                  sb_szEMailAddress = new StringBuilder( 32 );
               else
                  sb_szEMailAddress = new StringBuilder( szEMailAddress );
                               GetStringFromAttribute( sb_szEMailAddress, mConList, szAlternateEmailEntityName, szEmailAttributeName );
               szEMailAddress = sb_szEMailAddress.toString( );}
               //:szAlternateEMailAddress = szEMailAddress
                {StringBuilder sb_szAlternateEMailAddress;
               if ( szAlternateEMailAddress == null )
                  sb_szAlternateEMailAddress = new StringBuilder( 32 );
               else
                  sb_szAlternateEMailAddress = new StringBuilder( szAlternateEMailAddress );
                              ZeidonStringCopy( sb_szAlternateEMailAddress, 1, 0, szEMailAddress, 1, 0, 257 );
               szAlternateEMailAddress = sb_szAlternateEMailAddress.toString( );}
            } 

            //:END
            //:IF szEMailAddress != ""
            if ( ZeidonStringCompare( szEMailAddress, 1, 0, "", 1, 0, 257 ) != 0 )
            { 
               //:IF szCreateItemEntityName != ""
               if ( ZeidonStringCompare( szCreateItemEntityName, 1, 0, "", 1, 0, 33 ) != 0 )
               { 
                  //:CreateEntity( mConListT, szCreateItemEntityName, zPOS_AFTER )
                  CreateEntity( mConListT, szCreateItemEntityName, zPOS_AFTER );
               } 

               //:END
               //:IncludeSubobjectFromSubobject( mConListT, szIncludeEntityName,
               //:                               mConList,  szIncludeEntityName, zPOS_AFTER )
               IncludeSubobjectFromSubobject( mConListT, szIncludeEntityName, mConList, szIncludeEntityName, zPOS_AFTER );
               //:// Make sure that the eMail attribute is set as it could be a work attribute.
               //:SetAttributeFromAttribute( mConListT, szEmailEntityName, szEmailAttributeName,
               //:                           mConList,  szEmailEntityName, szEmailAttributeName )
               SetAttributeFromAttribute( mConListT, szEmailEntityName, szEmailAttributeName, mConList, szEmailEntityName, szEmailAttributeName );
               //:// If the Email Address from the alternate entity was used, we have to set it in the temporary object.
               //:IF szAlternateEMailAddress != ""
               if ( ZeidonStringCompare( szAlternateEMailAddress, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:SetAttributeFromString( mConListT, szEmailEntityName, szEmailAttributeName, szAlternateEMailAddress )
                  SetAttributeFromString( mConListT, szEmailEntityName, szEmailAttributeName, szAlternateEMailAddress );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// Format error message containing name. Most names are from the Person entity, which is the email address.
               //:// There are a few expections, however, as defined below.
               //:IF szIncludeEntityName = "Employee"
               if ( ZeidonStringCompare( szIncludeEntityName, 1, 0, "Employee", 1, 0, 33 ) == 0 )
               { 
                  //:GetStringFromAttribute( szPersonName, mConList, "EmployeePerson", "dFullNameLFM" )
                  {StringBuilder sb_szPersonName;
                  if ( szPersonName == null )
                     sb_szPersonName = new StringBuilder( 32 );
                  else
                     sb_szPersonName = new StringBuilder( szPersonName );
                                     GetStringFromAttribute( sb_szPersonName, mConList, "EmployeePerson", "dFullNameLFM" );
                  szPersonName = sb_szPersonName.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:IF szIncludeEntityName = "Faculty"
                  if ( ZeidonStringCompare( szIncludeEntityName, 1, 0, "Faculty", 1, 0, 33 ) == 0 )
                  { 
                     //:GetStringFromAttribute( szPersonName, mConList, "FacultyPerson", "dFullNameLFM" )
                     {StringBuilder sb_szPersonName;
                     if ( szPersonName == null )
                        sb_szPersonName = new StringBuilder( 32 );
                     else
                        sb_szPersonName = new StringBuilder( szPersonName );
                                           GetStringFromAttribute( sb_szPersonName, mConList, "FacultyPerson", "dFullNameLFM" );
                     szPersonName = sb_szPersonName.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF szIncludeEntityName = "ContactListItemStudent"
                     if ( ZeidonStringCompare( szIncludeEntityName, 1, 0, "ContactListItemStudent", 1, 0, 33 ) == 0 )
                     { 
                        //:IF wXferO.Root.CurrentFunction = "EmailParent"
                        if ( CompareAttributeToString( wXferO, "Root", "CurrentFunction", "EmailParent" ) == 0 )
                        { 
                           //:// Entity is for Parent of Student.
                           //:szPersonName = "parent of Student: " + mConList.ContactListItemStudentPerson.dFullNameLFM
                           {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                           StringBuilder sb_szTempString_0;
                           if ( szTempString_0 == null )
                              sb_szTempString_0 = new StringBuilder( 32 );
                           else
                              sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                                       GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 1026, mConList, "ContactListItemStudentPerson", "dFullNameLFM", "", 0 );
                           lTempInteger_0 = mi_lTempInteger_0.intValue( );
                           szTempString_0 = sb_szTempString_0.toString( );}
                            {StringBuilder sb_szPersonName;
                           if ( szPersonName == null )
                              sb_szPersonName = new StringBuilder( 32 );
                           else
                              sb_szPersonName = new StringBuilder( szPersonName );
                                                      ZeidonStringCopy( sb_szPersonName, 1, 0, "parent of Student: ", 1, 0, 51 );
                           szPersonName = sb_szPersonName.toString( );}
                            {StringBuilder sb_szPersonName;
                           if ( szPersonName == null )
                              sb_szPersonName = new StringBuilder( 32 );
                           else
                              sb_szPersonName = new StringBuilder( szPersonName );
                                                      ZeidonStringConcat( sb_szPersonName, 1, 0, szTempString_0, 1, 0, 51 );
                           szPersonName = sb_szPersonName.toString( );}
                           //:ELSE
                        } 
                        else
                        { 
                           //:// Entity is for Student.
                           //:GetStringFromAttribute( szPersonName, mConList, "ContactListItemStudentPerson", "dFullNameLFM" )
                           {StringBuilder sb_szPersonName;
                           if ( szPersonName == null )
                              sb_szPersonName = new StringBuilder( 32 );
                           else
                              sb_szPersonName = new StringBuilder( szPersonName );
                                                       GetStringFromAttribute( sb_szPersonName, mConList, "ContactListItemStudentPerson", "dFullNameLFM" );
                           szPersonName = sb_szPersonName.toString( );}
                        } 

                        //:END
                        //:ELSE
                     } 
                     else
                     { 
                        //:GetStringFromAttribute( szPersonName, mConList, szEmailEntityName, "dFullNameLFM" )
                        {StringBuilder sb_szPersonName;
                        if ( szPersonName == null )
                           sb_szPersonName = new StringBuilder( 32 );
                        else
                           sb_szPersonName = new StringBuilder( szPersonName );
                                                 GetStringFromAttribute( sb_szPersonName, mConList, szEmailEntityName, "dFullNameLFM" );
                        szPersonName = sb_szPersonName.toString( );}
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
               //:Msg = "No Email Address is specified for " + szPersonName + "."
                {StringBuilder sb_Msg;
               if ( Msg == null )
                  sb_Msg = new StringBuilder( 32 );
               else
                  sb_Msg = new StringBuilder( Msg );
                              ZeidonStringCopy( sb_Msg, 1, 0, "No Email Address is specified for ", 1, 0, 257 );
               Msg = sb_Msg.toString( );}
                {StringBuilder sb_Msg;
               if ( Msg == null )
                  sb_Msg = new StringBuilder( 32 );
               else
                  sb_Msg = new StringBuilder( Msg );
                              ZeidonStringConcat( sb_Msg, 1, 0, szPersonName, 1, 0, 257 );
               Msg = sb_Msg.toString( );}
                {StringBuilder sb_Msg;
               if ( Msg == null )
                  sb_Msg = new StringBuilder( 32 );
               else
                  sb_Msg = new StringBuilder( Msg );
                              ZeidonStringConcat( sb_Msg, 1, 0, ".", 1, 0, 257 );
               Msg = sb_Msg.toString( );}
               //:MessageSend( mConList, "", "Generate Email", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               MessageSend( mConList, "", "Generate Email", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            } 

            //:END
            //:SetSelectStateOfEntity( mConList, szSelectedEntityName, 0 )
            SetSelectStateOfEntity( mConList, szSelectedEntityName, 0 );
         } 

         //:END
      } 

      //:END
      //:nRC = SetCursorNextEntity( mConList, szIncludeEntityName, "ContactList" )
      nRC = SetCursorNextEntity( mConList, szIncludeEntityName, "ContactList" );
   } 

   //:END

   //:// StartEmailClientForList( zVIEW   ResultSet,
   //://                          zCPCHAR cpcEntityName,
   //://                          zCPCHAR cpcAttributeName,
   //://                          zCPCHAR cpcContext,
   //://                          zCPCHAR cpcScope,
   //://                          zSHORT  bUseOnlySelectedEntities,
   //://                          zSHORT  bUseParentSelectedEntities,
   //://                          zCPCHAR cpcSubject,
   //://                          zCPCHAR cpcCopyTo,        // comma separated list
   //://                          zCPCHAR cpcBlindCopy,     // comma separated list
   //://                          zCPCHAR cpcBody,
   //://                          zCPCHAR cpcAttachment,
   //://                          zCPCHAR cpcEmailClient,
   //://                          zLONG   lFlags )          // reserved

   //:IF szBlindCopyFlag = "Y"
   if ( ZeidonStringCompare( szBlindCopyFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:StartBlindEmailClientForList( mConListT, szEmailEntityName, szEmailAttributeName, "", "ContactList",
      //:                              FALSE, TRUE, szSubjectLine, "", "", szBody, "", "", 0 )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConListT );
       m_ZGLOBAL1_Operation.StartBlindEmailClientForList( mConListT, szEmailEntityName, szEmailAttributeName, "", "ContactList", FALSE, TRUE, szSubjectLine, "", "", szBody, "", "", 0 );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:StartEmailClientForList( mConListT, szEmailEntityName, szEmailAttributeName, "", "ContactList",
      //:                         FALSE, TRUE, szSubjectLine, "", "", szBody, "", "", 0 )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConListT );
       m_ZGLOBAL1_Operation.StartEmailClientForList( mConListT, szEmailEntityName, szEmailAttributeName, "", "ContactList", FALSE, TRUE, szSubjectLine, "", "", szBody, "", "", 0 );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
   } 

   //:END
   //:   
   //:DropView( mConListT )
   DropView( mConListT );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:ActivateDocumentsForDivByType( VIEW ViewToWindow,
//:                               STRING ( 1 ) DocumentType )

//:   VIEW wXferO     REGISTERED AS wXferO
public int 
ActivateDocumentsForDivByType( View     ViewToWindow,
                               String   DocumentType )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW zqMDocOLST BASED ON LOD  zqMDocO
   zVIEW    zqMDocOLST = new zVIEW( );
   //:VIEW sAppMgr    BASED ON LOD  sAppMgr
   zVIEW    sAppMgr = new zVIEW( );
   //:VIEW mUser      BASED ON LOD  mUser
   zVIEW    mUser = new zVIEW( );
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   String   szTempString_2 = null;
   String   szTempString_3 = null;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   String   szTempString_6 = null;
   String   szTempString_7 = null;
   zVIEW    vTempViewVar_3 = new zVIEW( );
   String   szTempString_8 = null;
   String   szTempString_9 = null;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_4 = new zVIEW( );
   String   szTempString_10 = null;
   String   szTempString_11 = null;
   zVIEW    vTempViewVar_5 = new zVIEW( );

   RESULT = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_TASK );

   //:// Note that if the Functional Area is Admissions or Registrar or Retention, FinancialAid or StudentLife, 
   //:// we will also use the current Administrative Division in the activate.
   //:// If the DocumentType is Mailing, we will look for both null and "M" values, and older Documents did
   //:// not specify type.

   //:GET VIEW zqMDocOLST NAMED "zqMDocOLST"
   RESULT = GetViewByName( zqMDocOLST, "zqMDocOLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( zqMDocOLST )
      DropObjectInstance( zqMDocOLST );
   } 

   //:END
   //:GetViewByName( sAppMgr, "sAppMgr", ViewToWindow, zLEVEL_APPLICATION )
   GetViewByName( sAppMgr, "sAppMgr", ViewToWindow, zLEVEL_APPLICATION );
   //:IF DocumentType = ""
   if ( ZeidonStringCompare( DocumentType, 1, 0, "", 1, 0, 2 ) == 0 )
   { 
      //:IF sAppMgr.FunctionalArea.FunctionalAreaName = "Admissions" OR
      //:   sAppMgr.FunctionalArea.FunctionalAreaName = "Registrar" OR
      //:   sAppMgr.FunctionalArea.FunctionalAreaName = "Retention" OR
      //:   sAppMgr.FunctionalArea.FunctionalAreaName = "StudentServices" OR
      //:   sAppMgr.FunctionalArea.FunctionalAreaName = "StudentAccounts" OR
      //:   sAppMgr.FunctionalArea.FunctionalAreaName = "FinancialAid"
      if ( CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "Admissions" ) == 0 || CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "Registrar" ) == 0 ||
           CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "Retention" ) == 0 || CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "StudentServices" ) == 0 ||
           CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "StudentAccounts" ) == 0 || CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "FinancialAid" ) == 0 )
      { 

         //:GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_APPLICATION )
         GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_APPLICATION );
         //:ACTIVATE zqMDocOLST Multiple
         //:    WHERE zqMDocOLST.Document.ObjectName         = wXferO.QueryValues.QueryObjectName
         //:      AND zqMDocOLST.Document.FunctionalAreaName = sAppMgr.FunctionalArea.FunctionalAreaName
         //:      AND zqMDocOLST.AdministrativeDivision.ID   = mUser.CurrentAdministrativeDivision.ID
         //:      AND ( zqMDocOLST.Document.ActiveFlag = "Y" OR zqMDocOLST.Document.ActiveFlag = "" )
         {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetStringFromAttribute( sb_szTempString_0, wXferO, "QueryValues", "QueryObjectName" );
         szTempString_0 = sb_szTempString_0.toString( );}
         {StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetStringFromAttribute( sb_szTempString_1, sAppMgr, "FunctionalArea", "FunctionalAreaName" );
         szTempString_1 = sb_szTempString_1.toString( );}
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "CurrentAdministrativeDivision", "ID" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         o_fnLocalBuildQual_5( ViewToWindow, vTempViewVar_0, szTempString_0, szTempString_1, lTempInteger_0 );
         RESULT = ActivateObjectInstance( zqMDocOLST, "zqMDocO", ViewToWindow, vTempViewVar_0, zMULTIPLE );
         DropView( vTempViewVar_0 );
         //:ELSE
      } 
      else
      { 
         //:ACTIVATE zqMDocOLST Multiple
         //:    WHERE zqMDocOLST.Document.ObjectName         = wXferO.QueryValues.QueryObjectName
         //:      AND zqMDocOLST.Document.FunctionalAreaName = sAppMgr.FunctionalArea.FunctionalAreaName
         //:      AND ( zqMDocOLST.Document.ActiveFlag = "Y" OR zqMDocOLST.Document.ActiveFlag = "" )
         {StringBuilder sb_szTempString_2;
         if ( szTempString_2 == null )
            sb_szTempString_2 = new StringBuilder( 32 );
         else
            sb_szTempString_2 = new StringBuilder( szTempString_2 );
                   GetStringFromAttribute( sb_szTempString_2, wXferO, "QueryValues", "QueryObjectName" );
         szTempString_2 = sb_szTempString_2.toString( );}
         {StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                   GetStringFromAttribute( sb_szTempString_3, sAppMgr, "FunctionalArea", "FunctionalAreaName" );
         szTempString_3 = sb_szTempString_3.toString( );}
         o_fnLocalBuildQual_6( ViewToWindow, vTempViewVar_1, szTempString_2, szTempString_3 );
         RESULT = ActivateObjectInstance( zqMDocOLST, "zqMDocO", ViewToWindow, vTempViewVar_1, zMULTIPLE );
         DropView( vTempViewVar_1 );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:IF DocumentType = "M"
      if ( ZeidonStringCompare( DocumentType, 1, 0, "M", 1, 0, 2 ) == 0 )
      { 
         //:IF sAppMgr.FunctionalArea.FunctionalAreaName = "Admissions" OR
         //:   sAppMgr.FunctionalArea.FunctionalAreaName = "Registrar" OR
         //:   sAppMgr.FunctionalArea.FunctionalAreaName = "Retention" OR
         //:   sAppMgr.FunctionalArea.FunctionalAreaName = "StudentServices" OR
         //:   sAppMgr.FunctionalArea.FunctionalAreaName = "StudentAccounts" OR
         //:   sAppMgr.FunctionalArea.FunctionalAreaName = "FinancialAid"
         if ( CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "Admissions" ) == 0 || CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "Registrar" ) == 0 ||
              CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "Retention" ) == 0 || CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "StudentServices" ) == 0 ||
              CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "StudentAccounts" ) == 0 || CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "FinancialAid" ) == 0 )
         { 

            //:GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_APPLICATION )
            GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_APPLICATION );
            //:ACTIVATE zqMDocOLST Multiple
            //:    WHERE zqMDocOLST.Document.ObjectName         = wXferO.QueryValues.QueryObjectName
            //:      AND zqMDocOLST.Document.FunctionalAreaName = sAppMgr.FunctionalArea.FunctionalAreaName
            //:      AND zqMDocOLST.AdministrativeDivision.ID   = mUser.CurrentAdministrativeDivision.ID
            //:      AND ( zqMDocOLST.Document.DocType = "" OR zqMDocOLST.Document.DocType = "M" )
            //:      AND ( zqMDocOLST.Document.ActiveFlag = "Y" OR zqMDocOLST.Document.ActiveFlag = "" )
            {StringBuilder sb_szTempString_4;
            if ( szTempString_4 == null )
               sb_szTempString_4 = new StringBuilder( 32 );
            else
               sb_szTempString_4 = new StringBuilder( szTempString_4 );
                         GetStringFromAttribute( sb_szTempString_4, wXferO, "QueryValues", "QueryObjectName" );
            szTempString_4 = sb_szTempString_4.toString( );}
            {StringBuilder sb_szTempString_5;
            if ( szTempString_5 == null )
               sb_szTempString_5 = new StringBuilder( 32 );
            else
               sb_szTempString_5 = new StringBuilder( szTempString_5 );
                         GetStringFromAttribute( sb_szTempString_5, sAppMgr, "FunctionalArea", "FunctionalAreaName" );
            szTempString_5 = sb_szTempString_5.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                         GetIntegerFromAttribute( mi_lTempInteger_1, mUser, "CurrentAdministrativeDivision", "ID" );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );}
            o_fnLocalBuildQual_7( ViewToWindow, vTempViewVar_2, szTempString_4, szTempString_5, lTempInteger_1 );
            RESULT = ActivateObjectInstance( zqMDocOLST, "zqMDocO", ViewToWindow, vTempViewVar_2, zMULTIPLE );
            DropView( vTempViewVar_2 );
            //:ELSE
         } 
         else
         { 
            //:ACTIVATE zqMDocOLST Multiple
            //:    WHERE zqMDocOLST.Document.ObjectName         = wXferO.QueryValues.QueryObjectName
            //:      AND zqMDocOLST.Document.FunctionalAreaName = sAppMgr.FunctionalArea.FunctionalAreaName
            //:      AND ( zqMDocOLST.Document.DocType = "" OR zqMDocOLST.Document.DocType = "M" )
            //:      AND ( zqMDocOLST.Document.ActiveFlag = "Y" OR zqMDocOLST.Document.ActiveFlag = "" )
            {StringBuilder sb_szTempString_6;
            if ( szTempString_6 == null )
               sb_szTempString_6 = new StringBuilder( 32 );
            else
               sb_szTempString_6 = new StringBuilder( szTempString_6 );
                         GetStringFromAttribute( sb_szTempString_6, wXferO, "QueryValues", "QueryObjectName" );
            szTempString_6 = sb_szTempString_6.toString( );}
            {StringBuilder sb_szTempString_7;
            if ( szTempString_7 == null )
               sb_szTempString_7 = new StringBuilder( 32 );
            else
               sb_szTempString_7 = new StringBuilder( szTempString_7 );
                         GetStringFromAttribute( sb_szTempString_7, sAppMgr, "FunctionalArea", "FunctionalAreaName" );
            szTempString_7 = sb_szTempString_7.toString( );}
            o_fnLocalBuildQual_8( ViewToWindow, vTempViewVar_3, szTempString_6, szTempString_7 );
            RESULT = ActivateObjectInstance( zqMDocOLST, "zqMDocO", ViewToWindow, vTempViewVar_3, zMULTIPLE );
            DropView( vTempViewVar_3 );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF sAppMgr.FunctionalArea.FunctionalAreaName = "Admissions" OR
         //:   sAppMgr.FunctionalArea.FunctionalAreaName = "Registrar" OR
         //:   sAppMgr.FunctionalArea.FunctionalAreaName = "Retention" OR
         //:   sAppMgr.FunctionalArea.FunctionalAreaName = "StudentServices" OR
         //:   sAppMgr.FunctionalArea.FunctionalAreaName = "StudentAccounts" OR
         //:   sAppMgr.FunctionalArea.FunctionalAreaName = "FinancialAid"
         if ( CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "Admissions" ) == 0 || CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "Registrar" ) == 0 ||
              CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "Retention" ) == 0 || CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "StudentServices" ) == 0 ||
              CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "StudentAccounts" ) == 0 || CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "FinancialAid" ) == 0 )
         { 

            //:GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_APPLICATION )
            GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_APPLICATION );
            //:ACTIVATE zqMDocOLST Multiple
            //:    WHERE zqMDocOLST.Document.ObjectName         = wXferO.QueryValues.QueryObjectName
            //:      AND zqMDocOLST.Document.FunctionalAreaName = sAppMgr.FunctionalArea.FunctionalAreaName
            //:      AND zqMDocOLST.AdministrativeDivision.ID   = mUser.CurrentAdministrativeDivision.ID
            //:      AND zqMDocOLST.Document.DocType = "R"
            //:      AND ( zqMDocOLST.Document.ActiveFlag = "Y" OR zqMDocOLST.Document.ActiveFlag = "" )
            {StringBuilder sb_szTempString_8;
            if ( szTempString_8 == null )
               sb_szTempString_8 = new StringBuilder( 32 );
            else
               sb_szTempString_8 = new StringBuilder( szTempString_8 );
                         GetStringFromAttribute( sb_szTempString_8, wXferO, "QueryValues", "QueryObjectName" );
            szTempString_8 = sb_szTempString_8.toString( );}
            {StringBuilder sb_szTempString_9;
            if ( szTempString_9 == null )
               sb_szTempString_9 = new StringBuilder( 32 );
            else
               sb_szTempString_9 = new StringBuilder( szTempString_9 );
                         GetStringFromAttribute( sb_szTempString_9, sAppMgr, "FunctionalArea", "FunctionalAreaName" );
            szTempString_9 = sb_szTempString_9.toString( );}
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, mUser, "CurrentAdministrativeDivision", "ID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            o_fnLocalBuildQual_9( ViewToWindow, vTempViewVar_4, szTempString_8, szTempString_9, lTempInteger_2 );
            RESULT = ActivateObjectInstance( zqMDocOLST, "zqMDocO", ViewToWindow, vTempViewVar_4, zMULTIPLE );
            DropView( vTempViewVar_4 );
            //:ELSE
         } 
         else
         { 
            //:ACTIVATE zqMDocOLST Multiple
            //:    WHERE zqMDocOLST.Document.ObjectName         = wXferO.QueryValues.QueryObjectName
            //:      AND zqMDocOLST.Document.FunctionalAreaName = sAppMgr.FunctionalArea.FunctionalAreaName
            //:      AND zqMDocOLST.Document.DocType = "R"
            //:      AND ( zqMDocOLST.Document.ActiveFlag = "Y" OR zqMDocOLST.Document.ActiveFlag = "" )
            {StringBuilder sb_szTempString_10;
            if ( szTempString_10 == null )
               sb_szTempString_10 = new StringBuilder( 32 );
            else
               sb_szTempString_10 = new StringBuilder( szTempString_10 );
                         GetStringFromAttribute( sb_szTempString_10, wXferO, "QueryValues", "QueryObjectName" );
            szTempString_10 = sb_szTempString_10.toString( );}
            {StringBuilder sb_szTempString_11;
            if ( szTempString_11 == null )
               sb_szTempString_11 = new StringBuilder( 32 );
            else
               sb_szTempString_11 = new StringBuilder( szTempString_11 );
                         GetStringFromAttribute( sb_szTempString_11, sAppMgr, "FunctionalArea", "FunctionalAreaName" );
            szTempString_11 = sb_szTempString_11.toString( );}
            o_fnLocalBuildQual_10( ViewToWindow, vTempViewVar_5, szTempString_10, szTempString_11 );
            RESULT = ActivateObjectInstance( zqMDocOLST, "zqMDocO", ViewToWindow, vTempViewVar_5, zMULTIPLE );
            DropView( vTempViewVar_5 );
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:NAME VIEW zqMDocOLST "zqMDocOLST"
   SetNameForView( zqMDocOLST, "zqMDocOLST", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:ComputeHighExamScoreACT( DECIMAL ReturnedScore,
//:                         VIEW mProspct BASED ON LOD mProspct )

//:   VIEW mProspct2 BASED ON LOD mProspct
public int 
ComputeHighExamScoreACT( MutableDouble  ReturnedScore,
                         View     mProspct )
{
   zVIEW    mProspct2 = new zVIEW( );
   //:DECIMAL dTotalComposite
   double  dTotalComposite = 0.0;
   int      RESULT = 0;


   //:// Compute the highest total score for SAT exams for any object that has the ExamHistory subobject.
   //:CreateViewFromView( mProspct2, mProspct )
   CreateViewFromView( mProspct2, mProspct );
   //:dTotalComposite = 0
   dTotalComposite = 0;
   //:FOR EACH  mProspct2.ExamHistory 
   RESULT = SetCursorFirstEntity( mProspct2, "ExamHistory", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF  mProspct2.ExamHistory.ExamType = "ACT" 
      if ( CompareAttributeToString( mProspct2, "ExamHistory", "ExamType", "ACT" ) == 0 )
      { 
         //:IF mProspct2.ExamHistory.TotalComposite  > dTotalComposite
         if ( CompareAttributeToDecimal( mProspct2, "ExamHistory", "TotalComposite", dTotalComposite ) > 0 )
         { 
            //:dTotalComposite = mProspct2.ExamHistory.TotalComposite 
            {MutableDouble md_dTotalComposite = new MutableDouble( dTotalComposite );
                         GetDecimalFromAttribute( md_dTotalComposite, mProspct2, "ExamHistory", "TotalComposite" );
            dTotalComposite = md_dTotalComposite.doubleValue( );}
         } 

         //:END 
      } 

      RESULT = SetCursorNextEntity( mProspct2, "ExamHistory", "" );
      //:END
   } 

   //:END
   //:ReturnedScore = dTotalComposite
   ReturnedScore.setValue( dTotalComposite );
   //:DropView( mProspct2 )
   DropView( mProspct2 );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:ComputeHighExamScoreSAT( DECIMAL ReturnedScore,
//:                         VIEW mProspct BASED ON LOD mProspct )

//:   VIEW mProspct2 BASED ON LOD mProspct
public int 
ComputeHighExamScoreSAT( MutableDouble  ReturnedScore,
                         View     mProspct )
{
   zVIEW    mProspct2 = new zVIEW( );
   //:DECIMAL dHighMath
   double  dHighMath = 0.0;
   //:DECIMAL dHighVerbal 
   double  dHighVerbal = 0.0;
   //:DECIMAL dTotal 
   double  dTotal = 0.0;
   int      RESULT = 0;


   //:// Compute the highest total score for SAT exams for any object that has the ExamHistory subobject.
   //:CreateViewFromView( mProspct2, mProspct )
   CreateViewFromView( mProspct2, mProspct );
   //:dHighMath = 0
   dHighMath = 0;
   //:dHighVerbal = 0
   dHighVerbal = 0;
   //:FOR EACH  mProspct2.ExamHistory 
   RESULT = SetCursorFirstEntity( mProspct2, "ExamHistory", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mProspct2.ExamHistory.ExamType = "SAT" AND mProspct2.ExamHistory.Writing = ""
      if ( CompareAttributeToString( mProspct2, "ExamHistory", "ExamType", "SAT" ) == 0 && CompareAttributeToString( mProspct2, "ExamHistory", "Writing", "" ) == 0 )
      { 
         //:IF  mProspct2.ExamHistory.MathQuantitative > dHighMath  
         if ( CompareAttributeToDecimal( mProspct2, "ExamHistory", "MathQuantitative", dHighMath ) > 0 )
         { 
            //:dHighMath = mProspct2.ExamHistory.MathQuantitative 
            {MutableDouble md_dHighMath = new MutableDouble( dHighMath );
                         GetDecimalFromAttribute( md_dHighMath, mProspct2, "ExamHistory", "MathQuantitative" );
            dHighMath = md_dHighMath.doubleValue( );}
         } 

         //:END 
         //:IF mProspct2.ExamHistory.Verbal > dHighVerbal  
         if ( CompareAttributeToDecimal( mProspct2, "ExamHistory", "Verbal", dHighVerbal ) > 0 )
         { 
            //:dHighVerbal = mProspct2.ExamHistory.Verbal 
            {MutableDouble md_dHighVerbal = new MutableDouble( dHighVerbal );
                         GetDecimalFromAttribute( md_dHighVerbal, mProspct2, "ExamHistory", "Verbal" );
            dHighVerbal = md_dHighVerbal.doubleValue( );}
         } 

         //:END 
      } 

      RESULT = SetCursorNextEntity( mProspct2, "ExamHistory", "" );
      //:END
   } 

   //:END
   //:dTotal = dHighMath + dHighVerbal 
   dTotal = dHighMath + dHighVerbal;
   //:ReturnedScore = dTotal
   ReturnedScore.setValue( dTotal );
   //:DropView( mProspct2 )
   DropView( mProspct2 );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:RetrieveCommaDeliminatedValue( STRING ( 5000 ) ReturnedValue,
//:                               STRING ( 5000 ) szLine,
//:                               INTEGER ParameterNumber )

//:   INTEGER Length
public int 
RetrieveCommaDeliminatedValue( StringBuilder   ReturnedValue,
                               String   szLine,
                               int      ParameterNumber )
{
   int      Length = 0;
   //:INTEGER ParameterPosition
   int      ParameterPosition = 0;
   //:INTEGER CommaPos
   int      CommaPos = 0;
   //:STRING ( 1 )   szComma
   String   szComma = null;
   //:STRING ( 5000 ) szValue
   String   szValue = null;


   //:// Return the value at the comma deliminated position ParameterNumber.
   //:Length = zstrlen( szLine )
   Length = zstrlen( szLine );
   //:szValue = ""
    {StringBuilder sb_szValue;
   if ( szValue == null )
      sb_szValue = new StringBuilder( 32 );
   else
      sb_szValue = new StringBuilder( szValue );
      ZeidonStringCopy( sb_szValue, 1, 0, "", 1, 0, 5001 );
   szValue = sb_szValue.toString( );}
   //:CommaPos = 1
   CommaPos = 1;
   //:ParameterPosition = 1
   ParameterPosition = 1;
   //:szComma = szLine[ CommaPos: 1 ]
    {StringBuilder sb_szComma;
   if ( szComma == null )
      sb_szComma = new StringBuilder( 32 );
   else
      sb_szComma = new StringBuilder( szComma );
      ZeidonStringCopy( sb_szComma, 1, 0, szLine, CommaPos, 1, 2 );
   szComma = sb_szComma.toString( );}
   //:LOOP WHILE ParameterPosition <= ParameterNumber AND CommaPos <= Length
   while ( ParameterPosition <= ParameterNumber && CommaPos <= Length )
   { 
      //:IF szComma = QUOTES
      if ( ZeidonStringCompare( szComma, 1, 0, QUOTES, 1, 0, 2 ) == 0 )
      { 
         //:// The entry IS contained in quotes.
         //:CommaPos = CommaPos + 1
         CommaPos = CommaPos + 1;
         //:szComma = szLine[ CommaPos: 1 ]
          {StringBuilder sb_szComma;
         if ( szComma == null )
            sb_szComma = new StringBuilder( 32 );
         else
            sb_szComma = new StringBuilder( szComma );
                  ZeidonStringCopy( sb_szComma, 1, 0, szLine, CommaPos, 1, 2 );
         szComma = sb_szComma.toString( );}
         //:szValue = ""
          {StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                  ZeidonStringCopy( sb_szValue, 1, 0, "", 1, 0, 5001 );
         szValue = sb_szValue.toString( );}
         //:LOOP WHILE CommaPos < Length AND szComma != QUOTES
         while ( CommaPos < Length && ZeidonStringCompare( szComma, 1, 0, QUOTES, 1, 0, 2 ) != 0 )
         { 
            //:szValue = szValue + szComma
             {StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                        ZeidonStringConcat( sb_szValue, 1, 0, szComma, 1, 0, 5001 );
            szValue = sb_szValue.toString( );}
            //:CommaPos = CommaPos + 1
            CommaPos = CommaPos + 1;
            //:szComma = szLine[ CommaPos: 1 ]
             {StringBuilder sb_szComma;
            if ( szComma == null )
               sb_szComma = new StringBuilder( 32 );
            else
               sb_szComma = new StringBuilder( szComma );
                        ZeidonStringCopy( sb_szComma, 1, 0, szLine, CommaPos, 1, 2 );
            szComma = sb_szComma.toString( );}
         } 

         //:END
         //:CommaPos = CommaPos + 2
         CommaPos = CommaPos + 2;
         //:IF CommaPos < Length
         if ( CommaPos < Length )
         { 
            //:szComma = szLine[ CommaPos: 1 ]
             {StringBuilder sb_szComma;
            if ( szComma == null )
               sb_szComma = new StringBuilder( 32 );
            else
               sb_szComma = new StringBuilder( szComma );
                        ZeidonStringCopy( sb_szComma, 1, 0, szLine, CommaPos, 1, 2 );
            szComma = sb_szComma.toString( );}
         } 

         //:END
         //:ParameterPosition = ParameterPosition + 1
         ParameterPosition = ParameterPosition + 1;
         //:ELSE
      } 
      else
      { 
         //:// The entry is NOT contained in quotes.
         //:szValue = ""
          {StringBuilder sb_szValue;
         if ( szValue == null )
            sb_szValue = new StringBuilder( 32 );
         else
            sb_szValue = new StringBuilder( szValue );
                  ZeidonStringCopy( sb_szValue, 1, 0, "", 1, 0, 5001 );
         szValue = sb_szValue.toString( );}
         //:LOOP WHILE CommaPos < Length AND szComma != "," 
         while ( CommaPos < Length && ZeidonStringCompare( szComma, 1, 0, ",", 1, 0, 2 ) != 0 )
         { 
            //:szValue = szValue + szComma
             {StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                        ZeidonStringConcat( sb_szValue, 1, 0, szComma, 1, 0, 5001 );
            szValue = sb_szValue.toString( );}
            //:CommaPos = CommaPos + 1
            CommaPos = CommaPos + 1;
            //:szComma = szLine[ CommaPos: 1 ]
             {StringBuilder sb_szComma;
            if ( szComma == null )
               sb_szComma = new StringBuilder( 32 );
            else
               sb_szComma = new StringBuilder( szComma );
                        ZeidonStringCopy( sb_szComma, 1, 0, szLine, CommaPos, 1, 2 );
            szComma = sb_szComma.toString( );}
         } 

         //:END
         //:IF szComma != ","
         if ( ZeidonStringCompare( szComma, 1, 0, ",", 1, 0, 2 ) != 0 )
         { 
            //:szComma = szLine[ CommaPos: 1 ]
             {StringBuilder sb_szComma;
            if ( szComma == null )
               sb_szComma = new StringBuilder( 32 );
            else
               sb_szComma = new StringBuilder( szComma );
                        ZeidonStringCopy( sb_szComma, 1, 0, szLine, CommaPos, 1, 2 );
            szComma = sb_szComma.toString( );}
            //:szValue = szValue + szComma
             {StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                        ZeidonStringConcat( sb_szValue, 1, 0, szComma, 1, 0, 5001 );
            szValue = sb_szValue.toString( );}
         } 

         //:END
         //:CommaPos = CommaPos + 1
         CommaPos = CommaPos + 1;
         //:szComma = szLine[ CommaPos: 1 ]
          {StringBuilder sb_szComma;
         if ( szComma == null )
            sb_szComma = new StringBuilder( 32 );
         else
            sb_szComma = new StringBuilder( szComma );
                  ZeidonStringCopy( sb_szComma, 1, 0, szLine, CommaPos, 1, 2 );
         szComma = sb_szComma.toString( );}
         //:ParameterPosition = ParameterPosition + 1
         ParameterPosition = ParameterPosition + 1;
      } 

      //:END
   } 

   //:END
   //:ReturnedValue = szValue
   ZeidonStringCopy( ReturnedValue, 1, 0, szValue, 1, 0, 5001 );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:SetDateFromLegacyDate( VIEW TargetView,
//:                       STRING ( 32 ) TargetEntityName,
//:                       STRING ( 32 ) TargetAttributeName,
//:                       VIEW SourceView,
//:                       STRING ( 32 ) SourceEntityName,
//:                       STRING ( 32 ) SourceAttributeName )
//:                       
//:   STRING ( 8 ) szLS_Date
public int 
SetDateFromLegacyDate( View     TargetView,
                       String   TargetEntityName,
                       String   TargetAttributeName,
                       View     SourceView,
                       String   SourceEntityName,
                       String   SourceAttributeName )
{
   String   szLS_Date = null;
   //:STRING ( 8 ) szNewDate
   String   szNewDate = null;
   //:STRING ( 4 ) szYear
   String   szYear = null;
   //:STRING ( 2 ) szMonth
   String   szMonth = null;
   //:STRING ( 2 ) szDay
   String   szDay = null;
   //:SHORT  nRC 
   int      nRC = 0;


   //:GetStringFromAttributeByContext( szLS_Date, 
   //:                                 SourceView, SourceEntityName, SourceAttributeName, "", 8 )
   {StringBuilder sb_szLS_Date;
   if ( szLS_Date == null )
      sb_szLS_Date = new StringBuilder( 32 );
   else
      sb_szLS_Date = new StringBuilder( szLS_Date );
       GetStringFromAttributeByContext( sb_szLS_Date, SourceView, SourceEntityName, SourceAttributeName, "", 8 );
   szLS_Date = sb_szLS_Date.toString( );}
   //:nRC = zstrlen( szLS_Date )
   nRC = zstrlen( szLS_Date );
   //:IF nRC < 7
   if ( nRC < 7 )
   { 
      //:szNewDate = ""
       {StringBuilder sb_szNewDate;
      if ( szNewDate == null )
         sb_szNewDate = new StringBuilder( 32 );
      else
         sb_szNewDate = new StringBuilder( szNewDate );
            ZeidonStringCopy( sb_szNewDate, 1, 0, "", 1, 0, 9 );
      szNewDate = sb_szNewDate.toString( );}
      //:ELSE
   } 
   else
   { 
      //:IF nRC = 7
      if ( nRC == 7 )
      { 
         //:szMonth = "0" + szLS_Date[1:1]
          {StringBuilder sb_szMonth;
         if ( szMonth == null )
            sb_szMonth = new StringBuilder( 32 );
         else
            sb_szMonth = new StringBuilder( szMonth );
                  ZeidonStringCopy( sb_szMonth, 1, 0, "0", 1, 0, 3 );
         szMonth = sb_szMonth.toString( );}
          {StringBuilder sb_szMonth;
         if ( szMonth == null )
            sb_szMonth = new StringBuilder( 32 );
         else
            sb_szMonth = new StringBuilder( szMonth );
                  ZeidonStringConcat( sb_szMonth, 1, 0, szLS_Date, 1, 1, 3 );
         szMonth = sb_szMonth.toString( );}
         //:szDay   = szLS_Date[2:2]
          {StringBuilder sb_szDay;
         if ( szDay == null )
            sb_szDay = new StringBuilder( 32 );
         else
            sb_szDay = new StringBuilder( szDay );
                  ZeidonStringCopy( sb_szDay, 1, 0, szLS_Date, 2, 2, 3 );
         szDay = sb_szDay.toString( );}
         //:szYear  = szLS_Date[4:4]
          {StringBuilder sb_szYear;
         if ( szYear == null )
            sb_szYear = new StringBuilder( 32 );
         else
            sb_szYear = new StringBuilder( szYear );
                  ZeidonStringCopy( sb_szYear, 1, 0, szLS_Date, 4, 4, 5 );
         szYear = sb_szYear.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szMonth = szLS_Date[1:2]
          {StringBuilder sb_szMonth;
         if ( szMonth == null )
            sb_szMonth = new StringBuilder( 32 );
         else
            sb_szMonth = new StringBuilder( szMonth );
                  ZeidonStringCopy( sb_szMonth, 1, 0, szLS_Date, 1, 2, 3 );
         szMonth = sb_szMonth.toString( );}
         //:szDay   = szLS_Date[3:2]
          {StringBuilder sb_szDay;
         if ( szDay == null )
            sb_szDay = new StringBuilder( 32 );
         else
            sb_szDay = new StringBuilder( szDay );
                  ZeidonStringCopy( sb_szDay, 1, 0, szLS_Date, 3, 2, 3 );
         szDay = sb_szDay.toString( );}
         //:szYear  = szLS_Date[5:4]
          {StringBuilder sb_szYear;
         if ( szYear == null )
            sb_szYear = new StringBuilder( 32 );
         else
            sb_szYear = new StringBuilder( szYear );
                  ZeidonStringCopy( sb_szYear, 1, 0, szLS_Date, 5, 4, 5 );
         szYear = sb_szYear.toString( );}
      } 

      //:END
      //:szNewDate = szYear + szMonth + szDay
       {StringBuilder sb_szNewDate;
      if ( szNewDate == null )
         sb_szNewDate = new StringBuilder( 32 );
      else
         sb_szNewDate = new StringBuilder( szNewDate );
            ZeidonStringCopy( sb_szNewDate, 1, 0, szYear, 1, 0, 9 );
      szNewDate = sb_szNewDate.toString( );}
       {StringBuilder sb_szNewDate;
      if ( szNewDate == null )
         sb_szNewDate = new StringBuilder( 32 );
      else
         sb_szNewDate = new StringBuilder( szNewDate );
            ZeidonStringConcat( sb_szNewDate, 1, 0, szMonth, 1, 0, 9 );
      szNewDate = sb_szNewDate.toString( );}
       {StringBuilder sb_szNewDate;
      if ( szNewDate == null )
         sb_szNewDate = new StringBuilder( 32 );
      else
         sb_szNewDate = new StringBuilder( szNewDate );
            ZeidonStringConcat( sb_szNewDate, 1, 0, szDay, 1, 0, 9 );
      szNewDate = sb_szNewDate.toString( );}
   } 

   //:END
   //:SetAttributeFromString( TargetView, TargetEntityName, TargetAttributeName, szNewDate )
   SetAttributeFromString( TargetView, TargetEntityName, TargetAttributeName, szNewDate );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
public int 
SetUpAndStartEmailClientForList( View     mConList,
                                 String   szIncludeEntityName,
                                 String   szEmailEntityName,
                                 String   szAllEntriesFlag )
{

   //:SetUpAndStartEmailClientForList( VIEW mConList,
   //:                              STRING ( 32 ) szIncludeEntityName,
   //:                              STRING ( 32 ) szEmailEntityName,
   //:                              STRING ( 1 )  szAllEntriesFlag )

   //:SetUpAndStartEmailClientForListR( mConList,
   //:                                  szIncludeEntityName,
   //:                                  "",
   //:                                  szEmailEntityName,
   //:                                  "eMailAddress",
   //:                                  "",
   //:                                  szEmailEntityName,
   //:                                  szAllEntriesFlag,
   //:                                  "", "", "" )
   SetUpAndStartEmailClientForListR( mConList, szIncludeEntityName, "", szEmailEntityName, "eMailAddress", "", szEmailEntityName, szAllEntriesFlag, "", "", "" );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:GetPersonCombinedNameRanking( INTEGER ReturnedRanking,
//:                              STRING ( 32 ) szSalutation )
public int 
GetPersonCombinedNameRanking( MutableInt   ReturnedRanking,
                              String   szSalutation )
{


   //:IF szSalutation = "Mr." OR
   //:   szSalutation = "Mrs." OR
   //:   szSalutation = "Ms." OR
   //:   szSalutation = "Miss"
   if ( ZeidonStringCompare( szSalutation, 1, 0, "Mr.", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSalutation, 1, 0, "Mrs.", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSalutation, 1, 0, "Ms.", 1, 0, 33 ) == 0 ||
        ZeidonStringCompare( szSalutation, 1, 0, "Miss", 1, 0, 33 ) == 0 )
   { 

      //:ReturnedRanking = 1
      ReturnedRanking.setValue( 1 );
      //:ELSE
   } 
   else
   { 
      //:            
      //:IF szSalutation = "Hon."
      if ( ZeidonStringCompare( szSalutation, 1, 0, "Hon.", 1, 0, 33 ) == 0 )
      { 
         //:ReturnedRanking = 5
         ReturnedRanking.setValue( 5 );
         //:ELSE
      } 
      else
      { 
         //:IF szSalutation = "Dr." 
         if ( ZeidonStringCompare( szSalutation, 1, 0, "Dr.", 1, 0, 33 ) == 0 )
         { 
            //:ReturnedRanking = 4
            ReturnedRanking.setValue( 4 );
            //:ELSE
         } 
         else
         { 
            //:IF szSalutation = "Rev." OR
            //:   szSalutation = "Prof." OR
            //:   szSalutation = "Rabbi"
            if ( ZeidonStringCompare( szSalutation, 1, 0, "Rev.", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSalutation, 1, 0, "Prof.", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSalutation, 1, 0, "Rabbi", 1, 0, 33 ) == 0 )
            { 

               //:ReturnedRanking = 3
               ReturnedRanking.setValue( 3 );
               //:ELSE
            } 
            else
            { 
               //:IF szSalutation = "Capt." OR
               //:   szSalutation = "Col." OR
               //:   szSalutation = "Lcdr." OR
               //:   szSalutation = "Lt." OR
               //:   szSalutation = "Ltc." OR
               //:   szSalutation = "Sgt."
               if ( ZeidonStringCompare( szSalutation, 1, 0, "Capt.", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSalutation, 1, 0, "Col.", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSalutation, 1, 0, "Lcdr.", 1, 0, 33 ) == 0 ||
                    ZeidonStringCompare( szSalutation, 1, 0, "Lt.", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSalutation, 1, 0, "Ltc.", 1, 0, 33 ) == 0 || ZeidonStringCompare( szSalutation, 1, 0, "Sgt.", 1, 0, 33 ) == 0 )
               { 

                  //:ReturnedRanking = 3
                  ReturnedRanking.setValue( 3 );
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
// END
} 


//:GLOBAL OPERATION
//:GetPersonCombinedNameSalutation( STRING ( 100 ) szFullName,
//:                                 VIEW qPerson BASED ON LOD qPerson,
//:                                 STRING ( 32 )  szEntityName )

//:   VIEW qPerson2 BASED ON LOD qPerson
public int 
GetPersonCombinedNameSalutation( StringBuilder   szFullName,
                                 View     qPerson,
                                 String   szEntityName )
{
   zVIEW    qPerson2 = new zVIEW( );
   //:INTEGER        HusbandRanking
   int      HusbandRanking = 0;
   //:INTEGER        WifeRanking
   int      WifeRanking = 0;
   //:STRING ( 50 )  szFirstName
   String   szFirstName = null;
   //:STRING ( 50 )  szLastName
   String   szLastName = null;
   //:STRING ( 50 )  szOnlyLastName
   String   szOnlyLastName = null;
   //:STRING ( 10 )  szMainSuffix
   String   szMainSuffix = null;
   //:STRING ( 10 )  szHusbandSuffix
   String   szHusbandSuffix = null;
   //:STRING ( 10 )  szWifeSuffix
   String   szWifeSuffix = null;
   //:STRING ( 50 )  szHusbandFirstName
   String   szHusbandFirstName = null;
   //:STRING ( 50 )  szWifeFirstName
   String   szWifeFirstName = null;
   //:STRING ( 50 )  szHusbandLastName
   String   szHusbandLastName = null;
   //:STRING ( 50 )  szWifeLastName
   String   szWifeLastName = null;
   //:STRING ( 10 )  szSalutation
   String   szSalutation = null;
   //:STRING ( 10 )  szHusbandSalutation
   String   szHusbandSalutation = null;
   //:STRING ( 10 )  szWifeSalutation
   String   szWifeSalutation = null;
   //:STRING ( 10 )  szCombinedSalutation
   String   szCombinedSalutation = null;
   //:STRING ( 1 )   szGender
   String   szGender = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;


   //:CreateViewFromView( qPerson2, qPerson )
   CreateViewFromView( qPerson2, qPerson );
   //:szWifeFirstName      = ""
    {StringBuilder sb_szWifeFirstName;
   if ( szWifeFirstName == null )
      sb_szWifeFirstName = new StringBuilder( 32 );
   else
      sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
      ZeidonStringCopy( sb_szWifeFirstName, 1, 0, "", 1, 0, 51 );
   szWifeFirstName = sb_szWifeFirstName.toString( );}
   //:szHusbandFirstName   = ""
    {StringBuilder sb_szHusbandFirstName;
   if ( szHusbandFirstName == null )
      sb_szHusbandFirstName = new StringBuilder( 32 );
   else
      sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
      ZeidonStringCopy( sb_szHusbandFirstName, 1, 0, "", 1, 0, 51 );
   szHusbandFirstName = sb_szHusbandFirstName.toString( );}
   //:szHusbandSuffix = ""
    {StringBuilder sb_szHusbandSuffix;
   if ( szHusbandSuffix == null )
      sb_szHusbandSuffix = new StringBuilder( 32 );
   else
      sb_szHusbandSuffix = new StringBuilder( szHusbandSuffix );
      ZeidonStringCopy( sb_szHusbandSuffix, 1, 0, "", 1, 0, 11 );
   szHusbandSuffix = sb_szHusbandSuffix.toString( );}

   //:// Get first names and salutations.
   //:GetStringFromAttributeByContext( szSalutation, qPerson2, szEntityName, "Salutation", "", 10 )
   {StringBuilder sb_szSalutation;
   if ( szSalutation == null )
      sb_szSalutation = new StringBuilder( 32 );
   else
      sb_szSalutation = new StringBuilder( szSalutation );
       GetStringFromAttributeByContext( sb_szSalutation, qPerson2, szEntityName, "Salutation", "", 10 );
   szSalutation = sb_szSalutation.toString( );}
   //:GetStringFromAttribute( szLastName, qPerson2, szEntityName, "LastName" )
   {StringBuilder sb_szLastName;
   if ( szLastName == null )
      sb_szLastName = new StringBuilder( 32 );
   else
      sb_szLastName = new StringBuilder( szLastName );
       GetStringFromAttribute( sb_szLastName, qPerson2, szEntityName, "LastName" );
   szLastName = sb_szLastName.toString( );}

   //:SET CURSOR FIRST qPerson2.HusbandOrParentRole 
   //:           WHERE qPerson2.HusbandOrParentRole.Role = "S"
   RESULT = SetCursorFirstEntityByString( qPerson2, "HusbandOrParentRole", "Role", "S", "" );
   //:IF RESULT >= zCURSOR_SET AND qPerson2.HusbandOrParent.Deceased != "Y"
   if ( RESULT >= zCURSOR_SET && CompareAttributeToString( qPerson2, "HusbandOrParent", "Deceased", "Y" ) != 0 )
   { 
      //:// This is the case where the Wife record is on top and the Husband is underneath.
      //:GetStringFromAttribute( szWifeLastName, qPerson2, szEntityName, "LastName" )
      {StringBuilder sb_szWifeLastName;
      if ( szWifeLastName == null )
         sb_szWifeLastName = new StringBuilder( 32 );
      else
         sb_szWifeLastName = new StringBuilder( szWifeLastName );
             GetStringFromAttribute( sb_szWifeLastName, qPerson2, szEntityName, "LastName" );
      szWifeLastName = sb_szWifeLastName.toString( );}
      //:GetStringFromAttribute( szWifeFirstName, qPerson2, szEntityName, "FirstName" )
      {StringBuilder sb_szWifeFirstName;
      if ( szWifeFirstName == null )
         sb_szWifeFirstName = new StringBuilder( 32 );
      else
         sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
             GetStringFromAttribute( sb_szWifeFirstName, qPerson2, szEntityName, "FirstName" );
      szWifeFirstName = sb_szWifeFirstName.toString( );}
      //:GetStringFromAttribute( szWifeSuffix, qPerson2, szEntityName, "Suffix" )
      {StringBuilder sb_szWifeSuffix;
      if ( szWifeSuffix == null )
         sb_szWifeSuffix = new StringBuilder( 32 );
      else
         sb_szWifeSuffix = new StringBuilder( szWifeSuffix );
             GetStringFromAttribute( sb_szWifeSuffix, qPerson2, szEntityName, "Suffix" );
      szWifeSuffix = sb_szWifeSuffix.toString( );}
      //:GetStringFromAttributeByContext( szWifeSalutation, qPerson2, szEntityName, "Salutation", "", 10 )
      {StringBuilder sb_szWifeSalutation;
      if ( szWifeSalutation == null )
         sb_szWifeSalutation = new StringBuilder( 32 );
      else
         sb_szWifeSalutation = new StringBuilder( szWifeSalutation );
             GetStringFromAttributeByContext( sb_szWifeSalutation, qPerson2, szEntityName, "Salutation", "", 10 );
      szWifeSalutation = sb_szWifeSalutation.toString( );}
      //:szHusbandLastName        = qPerson2.HusbandOrParent.LastName
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szHusbandLastName;
      if ( szHusbandLastName == null )
         sb_szHusbandLastName = new StringBuilder( 32 );
      else
         sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
             GetVariableFromAttribute( sb_szHusbandLastName, mi_lTempInteger_0, 'S', 51, qPerson2, "HusbandOrParent", "LastName", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szHusbandLastName = sb_szHusbandLastName.toString( );}
      //:szHusbandFirstName       = qPerson2.HusbandOrParent.FirstName
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szHusbandFirstName;
      if ( szHusbandFirstName == null )
         sb_szHusbandFirstName = new StringBuilder( 32 );
      else
         sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
             GetVariableFromAttribute( sb_szHusbandFirstName, mi_lTempInteger_1, 'S', 51, qPerson2, "HusbandOrParent", "FirstName", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szHusbandFirstName = sb_szHusbandFirstName.toString( );}
      //:szHusbandSuffix          = qPerson2.HusbandOrParent.Suffix 
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szHusbandSuffix;
      if ( szHusbandSuffix == null )
         sb_szHusbandSuffix = new StringBuilder( 32 );
      else
         sb_szHusbandSuffix = new StringBuilder( szHusbandSuffix );
             GetVariableFromAttribute( sb_szHusbandSuffix, mi_lTempInteger_2, 'S', 11, qPerson2, "HusbandOrParent", "Suffix", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szHusbandSuffix = sb_szHusbandSuffix.toString( );}
      //:GetStringFromAttributeByContext( szHusbandSalutation, qPerson2, "HusbandOrParent", "Salutation", "", 10 )
      {StringBuilder sb_szHusbandSalutation;
      if ( szHusbandSalutation == null )
         sb_szHusbandSalutation = new StringBuilder( 32 );
      else
         sb_szHusbandSalutation = new StringBuilder( szHusbandSalutation );
             GetStringFromAttributeByContext( sb_szHusbandSalutation, qPerson2, "HusbandOrParent", "Salutation", "", 10 );
      szHusbandSalutation = sb_szHusbandSalutation.toString( );}
      //:ELSE
   } 
   else
   { 
      //:SET CURSOR FIRST qPerson2.WifeOrChildRole 
      //:           WHERE qPerson2.WifeOrChildRole.Role = "S" AND qPerson2.WifeOrChild.Deceased != "Y"
      RESULT = SetCursorFirstEntity( qPerson2, "WifeOrChildRole", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( qPerson2, "WifeOrChildRole", "Role", "S" ) != 0 || CompareAttributeToString( qPerson2, "WifeOrChild", "Deceased", "Y" ) == 0 ) )
         { 
            RESULT = SetCursorNextEntity( qPerson2, "WifeOrChildRole", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// This is the case where the Husband record is on top and the Wife is underneath.
         //:szWifeLastName        = qPerson2.WifeOrChild.LastName 
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szWifeLastName;
         if ( szWifeLastName == null )
            sb_szWifeLastName = new StringBuilder( 32 );
         else
            sb_szWifeLastName = new StringBuilder( szWifeLastName );
                   GetVariableFromAttribute( sb_szWifeLastName, mi_lTempInteger_3, 'S', 51, qPerson2, "WifeOrChild", "LastName", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         szWifeLastName = sb_szWifeLastName.toString( );}
         //:szWifeFirstName       = qPerson2.WifeOrChild.FirstName
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
         StringBuilder sb_szWifeFirstName;
         if ( szWifeFirstName == null )
            sb_szWifeFirstName = new StringBuilder( 32 );
         else
            sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
                   GetVariableFromAttribute( sb_szWifeFirstName, mi_lTempInteger_4, 'S', 51, qPerson2, "WifeOrChild", "FirstName", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );
         szWifeFirstName = sb_szWifeFirstName.toString( );}
         //:szWifeSuffix          = qPerson2.WifeOrChild.Suffix 
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
         StringBuilder sb_szWifeSuffix;
         if ( szWifeSuffix == null )
            sb_szWifeSuffix = new StringBuilder( 32 );
         else
            sb_szWifeSuffix = new StringBuilder( szWifeSuffix );
                   GetVariableFromAttribute( sb_szWifeSuffix, mi_lTempInteger_5, 'S', 11, qPerson2, "WifeOrChild", "Suffix", "", 0 );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );
         szWifeSuffix = sb_szWifeSuffix.toString( );}
         //:GetStringFromAttributeByContext( szWifeSalutation, qPerson2, "WifeOrChild", "Salutation", "", 10 )
         {StringBuilder sb_szWifeSalutation;
         if ( szWifeSalutation == null )
            sb_szWifeSalutation = new StringBuilder( 32 );
         else
            sb_szWifeSalutation = new StringBuilder( szWifeSalutation );
                   GetStringFromAttributeByContext( sb_szWifeSalutation, qPerson2, "WifeOrChild", "Salutation", "", 10 );
         szWifeSalutation = sb_szWifeSalutation.toString( );}
         //:GetStringFromAttribute( szHusbandLastName,  qPerson2, szEntityName, "LastName" )
         {StringBuilder sb_szHusbandLastName;
         if ( szHusbandLastName == null )
            sb_szHusbandLastName = new StringBuilder( 32 );
         else
            sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
                   GetStringFromAttribute( sb_szHusbandLastName, qPerson2, szEntityName, "LastName" );
         szHusbandLastName = sb_szHusbandLastName.toString( );}
         //:GetStringFromAttribute( szHusbandFirstName, qPerson2, szEntityName, "FirstName" )
         {StringBuilder sb_szHusbandFirstName;
         if ( szHusbandFirstName == null )
            sb_szHusbandFirstName = new StringBuilder( 32 );
         else
            sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
                   GetStringFromAttribute( sb_szHusbandFirstName, qPerson2, szEntityName, "FirstName" );
         szHusbandFirstName = sb_szHusbandFirstName.toString( );}
         //:GetStringFromAttribute( szHusbandSuffix, qPerson2, szEntityName, "Suffix" )
         {StringBuilder sb_szHusbandSuffix;
         if ( szHusbandSuffix == null )
            sb_szHusbandSuffix = new StringBuilder( 32 );
         else
            sb_szHusbandSuffix = new StringBuilder( szHusbandSuffix );
                   GetStringFromAttribute( sb_szHusbandSuffix, qPerson2, szEntityName, "Suffix" );
         szHusbandSuffix = sb_szHusbandSuffix.toString( );}
         //:GetStringFromAttributeByContext( szHusbandSalutation, qPerson2, szEntityName, "Salutation", "", 10 )
         {StringBuilder sb_szHusbandSalutation;
         if ( szHusbandSalutation == null )
            sb_szHusbandSalutation = new StringBuilder( 32 );
         else
            sb_szHusbandSalutation = new StringBuilder( szHusbandSalutation );
                   GetStringFromAttributeByContext( sb_szHusbandSalutation, qPerson2, szEntityName, "Salutation", "", 10 );
         szHusbandSalutation = sb_szHusbandSalutation.toString( );}
      } 

      //:END
   } 

   //:END
   //:IF szHusbandSalutation = ""
   if ( ZeidonStringCompare( szHusbandSalutation, 1, 0, "", 1, 0, 11 ) == 0 )
   { 
      //:szHusbandSalutation = "Mr."
       {StringBuilder sb_szHusbandSalutation;
      if ( szHusbandSalutation == null )
         sb_szHusbandSalutation = new StringBuilder( 32 );
      else
         sb_szHusbandSalutation = new StringBuilder( szHusbandSalutation );
            ZeidonStringCopy( sb_szHusbandSalutation, 1, 0, "Mr.", 1, 0, 11 );
      szHusbandSalutation = sb_szHusbandSalutation.toString( );}
   } 

   //:END
   //:IF szWifeSalutation = ""
   if ( ZeidonStringCompare( szWifeSalutation, 1, 0, "", 1, 0, 11 ) == 0 )
   { 
      //:szWifeSalutation = "Mrs."
       {StringBuilder sb_szWifeSalutation;
      if ( szWifeSalutation == null )
         sb_szWifeSalutation = new StringBuilder( 32 );
      else
         sb_szWifeSalutation = new StringBuilder( szWifeSalutation );
            ZeidonStringCopy( sb_szWifeSalutation, 1, 0, "Mrs.", 1, 0, 11 );
      szWifeSalutation = sb_szWifeSalutation.toString( );}
   } 

   //:END

   //:// Get Suffix set up.
   //:GetStringFromAttribute( szMainSuffix, qPerson2, szEntityName, "Suffix" )
   {StringBuilder sb_szMainSuffix;
   if ( szMainSuffix == null )
      sb_szMainSuffix = new StringBuilder( 32 );
   else
      sb_szMainSuffix = new StringBuilder( szMainSuffix );
       GetStringFromAttribute( sb_szMainSuffix, qPerson2, szEntityName, "Suffix" );
   szMainSuffix = sb_szMainSuffix.toString( );}
   //:IF szHusbandFirstName != "" AND szWifeFirstName != ""
   if ( ZeidonStringCompare( szHusbandFirstName, 1, 0, "", 1, 0, 51 ) != 0 && ZeidonStringCompare( szWifeFirstName, 1, 0, "", 1, 0, 51 ) != 0 )
   { 

      //:// Both husband and wife are to be combined.

      //:// Note that if a salutation is either Dr. or Rev., then the only suffixes we will use are Jr, I, II, III, IV.
      //:IF szHusbandSalutation[1:2] = "Dr" OR szHusbandSalutation[1:3] = "Rev"
      if ( ZeidonStringCompare( szHusbandSalutation, 1, 2, "Dr", 1, 0, 11 ) == 0 || ZeidonStringCompare( szHusbandSalutation, 1, 3, "Rev", 1, 0, 11 ) == 0 )
      { 
         //:IF szHusbandSuffix[1:2] != "Jr" AND szHusbandSuffix != "I" AND szHusbandSuffix != "II" AND szHusbandSuffix != "III" AND szHusbandSuffix != "IV"
         if ( ZeidonStringCompare( szHusbandSuffix, 1, 2, "Jr", 1, 0, 11 ) != 0 && ZeidonStringCompare( szHusbandSuffix, 1, 0, "I", 1, 0, 11 ) != 0 && ZeidonStringCompare( szHusbandSuffix, 1, 0, "II", 1, 0, 11 ) != 0 &&
              ZeidonStringCompare( szHusbandSuffix, 1, 0, "III", 1, 0, 11 ) != 0 && ZeidonStringCompare( szHusbandSuffix, 1, 0, "IV", 1, 0, 11 ) != 0 )
         { 
            //:szHusbandSuffix = ""
             {StringBuilder sb_szHusbandSuffix;
            if ( szHusbandSuffix == null )
               sb_szHusbandSuffix = new StringBuilder( 32 );
            else
               sb_szHusbandSuffix = new StringBuilder( szHusbandSuffix );
                        ZeidonStringCopy( sb_szHusbandSuffix, 1, 0, "", 1, 0, 11 );
            szHusbandSuffix = sb_szHusbandSuffix.toString( );}
         } 

         //:END
      } 

      //:END
      //:IF szWifeSalutation[1:2] = "Dr" OR szWifeSalutation[1:3] = "Rev"
      if ( ZeidonStringCompare( szWifeSalutation, 1, 2, "Dr", 1, 0, 11 ) == 0 || ZeidonStringCompare( szWifeSalutation, 1, 3, "Rev", 1, 0, 11 ) == 0 )
      { 
         //:IF szWifeSuffix[1:2] != "Jr" AND szWifeSuffix != "I" AND szWifeSuffix != "II" AND szWifeSuffix != "III" AND szWifeSuffix != "IV"
         if ( ZeidonStringCompare( szWifeSuffix, 1, 2, "Jr", 1, 0, 11 ) != 0 && ZeidonStringCompare( szWifeSuffix, 1, 0, "I", 1, 0, 11 ) != 0 && ZeidonStringCompare( szWifeSuffix, 1, 0, "II", 1, 0, 11 ) != 0 &&
              ZeidonStringCompare( szWifeSuffix, 1, 0, "III", 1, 0, 11 ) != 0 && ZeidonStringCompare( szWifeSuffix, 1, 0, "IV", 1, 0, 11 ) != 0 )
         { 
            //:szWifeSuffix = ""
             {StringBuilder sb_szWifeSuffix;
            if ( szWifeSuffix == null )
               sb_szWifeSuffix = new StringBuilder( 32 );
            else
               sb_szWifeSuffix = new StringBuilder( szWifeSuffix );
                        ZeidonStringCopy( sb_szWifeSuffix, 1, 0, "", 1, 0, 11 );
            szWifeSuffix = sb_szWifeSuffix.toString( );}
         } 

         //:END
      } 

      //:END

      //:// Suffixes for combined husband and wife name.
      //:IF szWifeLastName != szHusbandLastName OR szWifeSuffix != ""
      if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) != 0 || ZeidonStringCompare( szWifeSuffix, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:// Wife and husband have different last names or wife has suffix, so treat their names separately.
         //:IF szHusbandSuffix != ""
         if ( ZeidonStringCompare( szHusbandSuffix, 1, 0, "", 1, 0, 11 ) != 0 )
         { 
            //:szHusbandLastName = szHusbandLastName + " " + szHusbandSuffix
             {StringBuilder sb_szHusbandLastName;
            if ( szHusbandLastName == null )
               sb_szHusbandLastName = new StringBuilder( 32 );
            else
               sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
                        ZeidonStringConcat( sb_szHusbandLastName, 1, 0, " ", 1, 0, 51 );
            szHusbandLastName = sb_szHusbandLastName.toString( );}
             {StringBuilder sb_szHusbandLastName;
            if ( szHusbandLastName == null )
               sb_szHusbandLastName = new StringBuilder( 32 );
            else
               sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
                        ZeidonStringConcat( sb_szHusbandLastName, 1, 0, szHusbandSuffix, 1, 0, 51 );
            szHusbandLastName = sb_szHusbandLastName.toString( );}
         } 

         //:END
         //:IF szWifeSuffix != ""
         if ( ZeidonStringCompare( szWifeSuffix, 1, 0, "", 1, 0, 11 ) != 0 )
         { 
            //:szWifeLastName = szWifeLastName + " " + szWifeSuffix
             {StringBuilder sb_szWifeLastName;
            if ( szWifeLastName == null )
               sb_szWifeLastName = new StringBuilder( 32 );
            else
               sb_szWifeLastName = new StringBuilder( szWifeLastName );
                        ZeidonStringConcat( sb_szWifeLastName, 1, 0, " ", 1, 0, 51 );
            szWifeLastName = sb_szWifeLastName.toString( );}
             {StringBuilder sb_szWifeLastName;
            if ( szWifeLastName == null )
               sb_szWifeLastName = new StringBuilder( 32 );
            else
               sb_szWifeLastName = new StringBuilder( szWifeLastName );
                        ZeidonStringConcat( sb_szWifeLastName, 1, 0, szWifeSuffix, 1, 0, 51 );
            szWifeLastName = sb_szWifeLastName.toString( );}
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Wife and husband have same last name, so use husband suffix.
         //:IF szMainSuffix != ""
         if ( ZeidonStringCompare( szMainSuffix, 1, 0, "", 1, 0, 11 ) != 0 )
         { 
            //:szLastName = szLastName + " " + szHusbandSuffix
             {StringBuilder sb_szLastName;
            if ( szLastName == null )
               sb_szLastName = new StringBuilder( 32 );
            else
               sb_szLastName = new StringBuilder( szLastName );
                        ZeidonStringConcat( sb_szLastName, 1, 0, " ", 1, 0, 51 );
            szLastName = sb_szLastName.toString( );}
             {StringBuilder sb_szLastName;
            if ( szLastName == null )
               sb_szLastName = new StringBuilder( 32 );
            else
               sb_szLastName = new StringBuilder( szLastName );
                        ZeidonStringConcat( sb_szLastName, 1, 0, szHusbandSuffix, 1, 0, 51 );
            szLastName = sb_szLastName.toString( );}
         } 

         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// Suffix for no combined name.

      //:// Note that if a salutation is either Dr. or Rev., then the only suffixes we will use are Jr, I, II, III, IV.
      //:IF szSalutation[1:2] = "Dr" OR szSalutation[1:3] = "Rev"
      if ( ZeidonStringCompare( szSalutation, 1, 2, "Dr", 1, 0, 11 ) == 0 || ZeidonStringCompare( szSalutation, 1, 3, "Rev", 1, 0, 11 ) == 0 )
      { 
         //:IF szMainSuffix[1:2] != "Jr" AND szMainSuffix != "I" AND szMainSuffix != "II" AND szMainSuffix != "III" AND szMainSuffix != "IV"
         if ( ZeidonStringCompare( szMainSuffix, 1, 2, "Jr", 1, 0, 11 ) != 0 && ZeidonStringCompare( szMainSuffix, 1, 0, "I", 1, 0, 11 ) != 0 && ZeidonStringCompare( szMainSuffix, 1, 0, "II", 1, 0, 11 ) != 0 &&
              ZeidonStringCompare( szMainSuffix, 1, 0, "III", 1, 0, 11 ) != 0 && ZeidonStringCompare( szMainSuffix, 1, 0, "IV", 1, 0, 11 ) != 0 )
         { 
            //:szMainSuffix = ""
             {StringBuilder sb_szMainSuffix;
            if ( szMainSuffix == null )
               sb_szMainSuffix = new StringBuilder( 32 );
            else
               sb_szMainSuffix = new StringBuilder( szMainSuffix );
                        ZeidonStringCopy( sb_szMainSuffix, 1, 0, "", 1, 0, 11 );
            szMainSuffix = sb_szMainSuffix.toString( );}
         } 

         //:END
      } 

      //:END

      //:IF szMainSuffix != ""
      if ( ZeidonStringCompare( szMainSuffix, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szLastName = szLastName + " " + szMainSuffix
          {StringBuilder sb_szLastName;
         if ( szLastName == null )
            sb_szLastName = new StringBuilder( 32 );
         else
            sb_szLastName = new StringBuilder( szLastName );
                  ZeidonStringConcat( sb_szLastName, 1, 0, " ", 1, 0, 51 );
         szLastName = sb_szLastName.toString( );}
          {StringBuilder sb_szLastName;
         if ( szLastName == null )
            sb_szLastName = new StringBuilder( 32 );
         else
            sb_szLastName = new StringBuilder( szLastName );
                  ZeidonStringConcat( sb_szLastName, 1, 0, szMainSuffix, 1, 0, 51 );
         szLastName = sb_szLastName.toString( );}
      } 

      //:END
   } 

   //:END

   //:IF szHusbandFirstName != "" AND szWifeFirstName != ""
   if ( ZeidonStringCompare( szHusbandFirstName, 1, 0, "", 1, 0, 51 ) != 0 && ZeidonStringCompare( szWifeFirstName, 1, 0, "", 1, 0, 51 ) != 0 )
   { 
      //:// COMBINED SALUTATION
      //:// There is both a husband and wife name, so create combination.
      //:GetPersonCombinedNameRanking( HusbandRanking, szHusbandSalutation )
      {MutableInt mi_HusbandRanking = new MutableInt( HusbandRanking );
             GetPersonCombinedNameRanking( mi_HusbandRanking, szHusbandSalutation );
      HusbandRanking = mi_HusbandRanking.intValue( );}
      //:GetPersonCombinedNameRanking( WifeRanking, szWifeSalutation )
      {MutableInt mi_WifeRanking = new MutableInt( WifeRanking );
             GetPersonCombinedNameRanking( mi_WifeRanking, szWifeSalutation );
      WifeRanking = mi_WifeRanking.intValue( );}

      //:IF WifeRanking = 1 AND HusbandRanking > 1
      if ( WifeRanking == 1 && HusbandRanking > 1 )
      { 
         //:// Husband "outranks" wife and wife salutation is on the first level.
         //:IF szWifeLastName = szHusbandLastName
         if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) == 0 )
         { 
            //:szFullName = szHusbandSalutation + " & " + szWifeSalutation + " " + szHusbandFirstName + " " + szLastName
            ZeidonStringCopy( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szWifeSalutation, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
            //:ELSE
         } 
         else
         { 
            //:szFullName = szHusbandSalutation + " " + szHusbandFirstName + " " + szHusbandLastName + " & " + szWifeSalutation +
            //:             " " + szWifeFirstName + " " +  szWifeLastName
            ZeidonStringCopy( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szHusbandLastName, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szWifeSalutation, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szWifeLastName, 1, 0, 101 );
         } 

         //:END

         //:ELSE
      } 
      else
      { 
         //:IF WifeRanking > 1 AND HusbandRanking > WifeRanking
         if ( WifeRanking > 1 && HusbandRanking > WifeRanking )
         { 
            //:// Husband "outranks" wife and wife salutation is above first level.
            //:IF szWifeLastName = szHusbandLastName
            if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) == 0 )
            { 
               //:szFullName = szHusbandSalutation + " " + szHusbandFirstName + " & " + 
               //:          szWifeSalutation + " " + szWifeFirstName + " " +  szLastName
               ZeidonStringCopy( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szWifeSalutation, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
               //:ELSE
            } 
            else
            { 
               //:szFullName = szHusbandSalutation + " " + szHusbandFirstName + " " + szHusbandLastName + " & " + 
               //:          szWifeSalutation + " " + szWifeFirstName + " " +  szWifeLastName
               ZeidonStringCopy( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szHusbandLastName, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szWifeSalutation, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
               ZeidonStringConcat( szFullName, 1, 0, szWifeLastName, 1, 0, 101 );
            } 

            //:END

            //:ELSE
         } 
         else
         { 
            //:IF WifeRanking > HusbandRanking 
            if ( WifeRanking > HusbandRanking )
            { 
               //:// Wife "outranks" husband.
               //:IF szWifeLastName = szHusbandLastName
               if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) == 0 )
               { 
                  //:szFullName = szWifeSalutation + " " + szWifeFirstName + " & " + 
                  //:       szHusbandSalutation + " " + szHusbandFirstName + " " +  szLastName
                  ZeidonStringCopy( szFullName, 1, 0, szWifeSalutation, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
                  //:ELSE
               } 
               else
               { 
                  //:szFullName = szWifeSalutation + " " + szWifeFirstName + " " +  szWifeLastName + " & " + 
                  //:       szHusbandSalutation + " " + szHusbandFirstName + " " + szHusbandLastName 
                  ZeidonStringCopy( szFullName, 1, 0, szWifeSalutation, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szWifeLastName, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                  ZeidonStringConcat( szFullName, 1, 0, szHusbandLastName, 1, 0, 101 );
               } 

               //:          
               //:END

               //:ELSE
            } 
            else
            { 
               //:IF WifeRanking = HusbandRanking AND WifeRanking > 1 AND szHusbandSalutation = szWifeSalutation
               if ( WifeRanking == HusbandRanking && WifeRanking > 1 && ZeidonStringCompare( szHusbandSalutation, 1, 0, szWifeSalutation, 1, 0, 11 ) == 0 )
               { 
                  //:// Salutations are the same and above first level.
                  //:IF szHusbandSalutation = "Hon."
                  if ( ZeidonStringCompare( szHusbandSalutation, 1, 0, "Hon.", 1, 0, 11 ) == 0 )
                  { 
                     //:szCombinedSalutation = "Hons."
                      {StringBuilder sb_szCombinedSalutation;
                     if ( szCombinedSalutation == null )
                        sb_szCombinedSalutation = new StringBuilder( 32 );
                     else
                        sb_szCombinedSalutation = new StringBuilder( szCombinedSalutation );
                                          ZeidonStringCopy( sb_szCombinedSalutation, 1, 0, "Hons.", 1, 0, 11 );
                     szCombinedSalutation = sb_szCombinedSalutation.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF szHusbandSalutation = "Dr."
                     if ( ZeidonStringCompare( szHusbandSalutation, 1, 0, "Dr.", 1, 0, 11 ) == 0 )
                     { 
                        //:szCombinedSalutation = "Drs."
                         {StringBuilder sb_szCombinedSalutation;
                        if ( szCombinedSalutation == null )
                           sb_szCombinedSalutation = new StringBuilder( 32 );
                        else
                           sb_szCombinedSalutation = new StringBuilder( szCombinedSalutation );
                                                ZeidonStringCopy( sb_szCombinedSalutation, 1, 0, "Drs.", 1, 0, 11 );
                        szCombinedSalutation = sb_szCombinedSalutation.toString( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:IF szHusbandSalutation = "Rev."
                        if ( ZeidonStringCompare( szHusbandSalutation, 1, 0, "Rev.", 1, 0, 11 ) == 0 )
                        { 
                           //:szCombinedSalutation = "Revs."
                            {StringBuilder sb_szCombinedSalutation;
                           if ( szCombinedSalutation == null )
                              sb_szCombinedSalutation = new StringBuilder( 32 );
                           else
                              sb_szCombinedSalutation = new StringBuilder( szCombinedSalutation );
                                                      ZeidonStringCopy( sb_szCombinedSalutation, 1, 0, "Revs.", 1, 0, 11 );
                           szCombinedSalutation = sb_szCombinedSalutation.toString( );}
                           //:ELSE
                        } 
                        else
                        { 
                           //:IF szHusbandSalutation = "Prof."
                           if ( ZeidonStringCompare( szHusbandSalutation, 1, 0, "Prof.", 1, 0, 11 ) == 0 )
                           { 
                              //:szCombinedSalutation = "Profs."
                               {StringBuilder sb_szCombinedSalutation;
                              if ( szCombinedSalutation == null )
                                 sb_szCombinedSalutation = new StringBuilder( 32 );
                              else
                                 sb_szCombinedSalutation = new StringBuilder( szCombinedSalutation );
                                                            ZeidonStringCopy( sb_szCombinedSalutation, 1, 0, "Profs.", 1, 0, 11 );
                              szCombinedSalutation = sb_szCombinedSalutation.toString( );}
                              //:ELSE
                           } 
                           else
                           { 
                              //:szCombinedSalutation = szHusbandSalutation
                               {StringBuilder sb_szCombinedSalutation;
                              if ( szCombinedSalutation == null )
                                 sb_szCombinedSalutation = new StringBuilder( 32 );
                              else
                                 sb_szCombinedSalutation = new StringBuilder( szCombinedSalutation );
                                                            ZeidonStringCopy( sb_szCombinedSalutation, 1, 0, szHusbandSalutation, 1, 0, 11 );
                              szCombinedSalutation = sb_szCombinedSalutation.toString( );}
                           } 

                           //:END
                        } 

                        //:END
                     } 

                     //:END
                  } 

                  //:END
                  //:IF szWifeLastName = szHusbandLastName
                  if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) == 0 )
                  { 
                     //:szFullName = szCombinedSalutation + " " + szHusbandFirstName + " & " + szWifeFirstName + " " + szLastName
                     ZeidonStringCopy( szFullName, 1, 0, szCombinedSalutation, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
                     //:ELSE
                  } 
                  else
                  { 
                     //:szFullName = szSalutation + " " + szHusbandFirstName + " " + szHusbandLastName + " & " + 
                     //:    szSalutation + " " + szWifeFirstName + " " +  szWifeLastName
                     ZeidonStringCopy( szFullName, 1, 0, szSalutation, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szHusbandLastName, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szSalutation, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                     ZeidonStringConcat( szFullName, 1, 0, szWifeLastName, 1, 0, 101 );
                  } 

                  //:END

                  //:ELSE
               } 
               else
               { 
                  //:IF szHusbandSalutation = szWifeSalutation AND WifeRanking > 1
                  if ( ZeidonStringCompare( szHusbandSalutation, 1, 0, szWifeSalutation, 1, 0, 11 ) == 0 && WifeRanking > 1 )
                  { 
                     //:// Ranks are even and above first level, but are not the same salutation.
                     //:IF szWifeLastName = szHusbandLastName
                     if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) == 0 )
                     { 
                        //:szFullName = szHusbandSalutation + " " + szHusbandFirstName + " & " + 
                        //: szWifeSalutation + " " + szWifeFirstName + " " +  szLastName
                        ZeidonStringCopy( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szWifeSalutation, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
                        //:ELSE
                     } 
                     else
                     { 
                        //:szFullName = szHusbandSalutation + " " + szHusbandFirstName + " " + szHusbandLastName + " & " + 
                        //: szWifeSalutation + " " + szWifeFirstName + " " +  szWifeLastName
                        ZeidonStringCopy( szFullName, 1, 0, szHusbandSalutation, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szHusbandLastName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szWifeSalutation, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szWifeLastName, 1, 0, 101 );
                     } 

                     //:END

                     //:ELSE
                  } 
                  else
                  { 
                     //:// The default must be Ranks are even and at first level.
                     //:IF szWifeLastName = szHusbandLastName
                     if ( ZeidonStringCompare( szWifeLastName, 1, 0, szHusbandLastName, 1, 0, 51 ) == 0 )
                     { 
                        //:szFullName = "Mr. & Mrs. " + szHusbandFirstName + " " + szLastName
                        ZeidonStringCopy( szFullName, 1, 0, "Mr. & Mrs. ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
                        //:ELSE
                     } 
                     else
                     { 
                        //:szFullName = "Mr. " + szHusbandFirstName + " " + szHusbandLastName + " & " + 
                        //: "Mrs. " + szWifeFirstName + " " +  szWifeLastName
                        ZeidonStringCopy( szFullName, 1, 0, "Mr. ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szHusbandLastName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, "Mrs. ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
                        ZeidonStringConcat( szFullName, 1, 0, szWifeLastName, 1, 0, 101 );
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


      //:ELSE
   } 
   else
   { 
      //:// SINGLE SALUTATION
      //:// There is only a single name, so set up a single salution.

      //:GetStringFromAttribute( szFirstName, qPerson2, szEntityName, "FirstName" )
      {StringBuilder sb_szFirstName;
      if ( szFirstName == null )
         sb_szFirstName = new StringBuilder( 32 );
      else
         sb_szFirstName = new StringBuilder( szFirstName );
             GetStringFromAttribute( sb_szFirstName, qPerson2, szEntityName, "FirstName" );
      szFirstName = sb_szFirstName.toString( );}
      //:GetStringFromAttribute( szGender, qPerson2, szEntityName, "Gender" )
      {StringBuilder sb_szGender;
      if ( szGender == null )
         sb_szGender = new StringBuilder( 32 );
      else
         sb_szGender = new StringBuilder( szGender );
             GetStringFromAttribute( sb_szGender, qPerson2, szEntityName, "Gender" );
      szGender = sb_szGender.toString( );}
      //:IF szSalutation = ""
      if ( ZeidonStringCompare( szSalutation, 1, 0, "", 1, 0, 11 ) == 0 )
      { 
         //:IF szGender = "F"
         if ( ZeidonStringCompare( szGender, 1, 0, "F", 1, 0, 2 ) == 0 )
         { 
            //:szSalutation = "Ms."
             {StringBuilder sb_szSalutation;
            if ( szSalutation == null )
               sb_szSalutation = new StringBuilder( 32 );
            else
               sb_szSalutation = new StringBuilder( szSalutation );
                        ZeidonStringCopy( sb_szSalutation, 1, 0, "Ms.", 1, 0, 11 );
            szSalutation = sb_szSalutation.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF szGender = "M"
            if ( ZeidonStringCompare( szGender, 1, 0, "M", 1, 0, 2 ) == 0 )
            { 
               //:szSalutation = "Mr."
                {StringBuilder sb_szSalutation;
               if ( szSalutation == null )
                  sb_szSalutation = new StringBuilder( 32 );
               else
                  sb_szSalutation = new StringBuilder( szSalutation );
                              ZeidonStringCopy( sb_szSalutation, 1, 0, "Mr.", 1, 0, 11 );
               szSalutation = sb_szSalutation.toString( );}
            } 

            //:END
         } 

         //:END 
      } 

      //:END
      //:IF szSalutation != ""
      if ( ZeidonStringCompare( szSalutation, 1, 0, "", 1, 0, 11 ) != 0 )
      { 
         //:szFullName = szSalutation + " " + szFirstName + " " + szLastName
         ZeidonStringCopy( szFullName, 1, 0, szSalutation, 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szFirstName, 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
         //:ELSE
      } 
      else
      { 
         //:szFullName = szFirstName + " " + szLastName
         ZeidonStringCopy( szFullName, 1, 0, szFirstName, 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
      } 

      //:END
   } 

   //:END
   //:DropView( qPerson2 )
   DropView( qPerson2 );
   return( 0 );
//    
// END
} 


//:GLOBAL OPERATION
//:GetPersonCombinedGreetingMrMrs( STRING ( 100 ) szFullName,
//:                                VIEW qPerson BASED ON LOD qPerson,
//:                                STRING ( 32 )  szEntityName )

//:   VIEW qPerson2 BASED ON LOD qPerson
public int 
GetPersonCombinedGreetingMrMrs( StringBuilder   szFullName,
                                View     qPerson,
                                String   szEntityName )
{
   zVIEW    qPerson2 = new zVIEW( );
   //:STRING ( 50 )  szFirstName
   String   szFirstName = null;
   //:STRING ( 50 )  szLastName
   String   szLastName = null;
   //:STRING ( 50 )  szHusbandFirstName
   String   szHusbandFirstName = null;
   //:STRING ( 50 )  szWifeFirstName
   String   szWifeFirstName = null;
   //:STRING ( 50 )  szHusbandLastName
   String   szHusbandLastName = null;
   //:STRING ( 50 )  szWifeLastName
   String   szWifeLastName = null;
   //:STRING ( 1 )   szGender
   String   szGender = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:// Format husband and wife as either.
   //://     Mr. & Mrs. Baker, if same last name.
   //://     Mr. Donald Baker & Mrs. Mary Edwards, if different last name.
   //://     Mr. Donald Baker, if no spouse.

   //:CreateViewFromView( qPerson2, qPerson )
   CreateViewFromView( qPerson2, qPerson );
   //:szWifeFirstName    = ""
    {StringBuilder sb_szWifeFirstName;
   if ( szWifeFirstName == null )
      sb_szWifeFirstName = new StringBuilder( 32 );
   else
      sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
      ZeidonStringCopy( sb_szWifeFirstName, 1, 0, "", 1, 0, 51 );
   szWifeFirstName = sb_szWifeFirstName.toString( );}
   //:szHusbandFirstName = ""
    {StringBuilder sb_szHusbandFirstName;
   if ( szHusbandFirstName == null )
      sb_szHusbandFirstName = new StringBuilder( 32 );
   else
      sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
      ZeidonStringCopy( sb_szHusbandFirstName, 1, 0, "", 1, 0, 51 );
   szHusbandFirstName = sb_szHusbandFirstName.toString( );}
   //:GetStringFromAttribute( szLastName, qPerson2, szEntityName, "LastName" )
   {StringBuilder sb_szLastName;
   if ( szLastName == null )
      sb_szLastName = new StringBuilder( 32 );
   else
      sb_szLastName = new StringBuilder( szLastName );
       GetStringFromAttribute( sb_szLastName, qPerson2, szEntityName, "LastName" );
   szLastName = sb_szLastName.toString( );}
   //:SET CURSOR FIRST qPerson2.HusbandOrParentRole 
   //:           WHERE qPerson2.HusbandOrParentRole.Role = "S"
   RESULT = SetCursorFirstEntityByString( qPerson2, "HusbandOrParentRole", "Role", "S", "" );
   //:IF RESULT >= zCURSOR_SET AND qPerson2.HusbandOrParent.Deceased != "Y"
   if ( RESULT >= zCURSOR_SET && CompareAttributeToString( qPerson2, "HusbandOrParent", "Deceased", "Y" ) != 0 )
   { 
      //:GetStringFromAttribute( szWifeFirstName, qPerson2, szEntityName, "FirstName" )
      {StringBuilder sb_szWifeFirstName;
      if ( szWifeFirstName == null )
         sb_szWifeFirstName = new StringBuilder( 32 );
      else
         sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
             GetStringFromAttribute( sb_szWifeFirstName, qPerson2, szEntityName, "FirstName" );
      szWifeFirstName = sb_szWifeFirstName.toString( );}
      //:GetStringFromAttribute( szWifeLastName,  qPerson2, szEntityName, "LastName" )
      {StringBuilder sb_szWifeLastName;
      if ( szWifeLastName == null )
         sb_szWifeLastName = new StringBuilder( 32 );
      else
         sb_szWifeLastName = new StringBuilder( szWifeLastName );
             GetStringFromAttribute( sb_szWifeLastName, qPerson2, szEntityName, "LastName" );
      szWifeLastName = sb_szWifeLastName.toString( );}
      //:szHusbandFirstName = qPerson2.HusbandOrParent.FirstName 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szHusbandFirstName;
      if ( szHusbandFirstName == null )
         sb_szHusbandFirstName = new StringBuilder( 32 );
      else
         sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
             GetVariableFromAttribute( sb_szHusbandFirstName, mi_lTempInteger_0, 'S', 51, qPerson2, "HusbandOrParent", "FirstName", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szHusbandFirstName = sb_szHusbandFirstName.toString( );}
      //:szHusbandLastName  = qPerson2.HusbandOrParent.LastName
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szHusbandLastName;
      if ( szHusbandLastName == null )
         sb_szHusbandLastName = new StringBuilder( 32 );
      else
         sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
             GetVariableFromAttribute( sb_szHusbandLastName, mi_lTempInteger_1, 'S', 51, qPerson2, "HusbandOrParent", "LastName", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szHusbandLastName = sb_szHusbandLastName.toString( );}
      //:ELSE
   } 
   else
   { 
      //:SET CURSOR FIRST qPerson2.WifeOrChildRole 
      //:           WHERE qPerson2.WifeOrChildRole.Role = "S" AND qPerson2.WifeOrChild.Deceased != "Y"
      RESULT = SetCursorFirstEntity( qPerson2, "WifeOrChildRole", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( qPerson2, "WifeOrChildRole", "Role", "S" ) != 0 || CompareAttributeToString( qPerson2, "WifeOrChild", "Deceased", "Y" ) == 0 ) )
         { 
            RESULT = SetCursorNextEntity( qPerson2, "WifeOrChildRole", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:szWifeFirstName    = qPerson2.WifeOrChild.FirstName 
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szWifeFirstName;
         if ( szWifeFirstName == null )
            sb_szWifeFirstName = new StringBuilder( 32 );
         else
            sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
                   GetVariableFromAttribute( sb_szWifeFirstName, mi_lTempInteger_2, 'S', 51, qPerson2, "WifeOrChild", "FirstName", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szWifeFirstName = sb_szWifeFirstName.toString( );}
         //:szWifeLastName     = qPerson2.WifeOrChild.LastName
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szWifeLastName;
         if ( szWifeLastName == null )
            sb_szWifeLastName = new StringBuilder( 32 );
         else
            sb_szWifeLastName = new StringBuilder( szWifeLastName );
                   GetVariableFromAttribute( sb_szWifeLastName, mi_lTempInteger_3, 'S', 51, qPerson2, "WifeOrChild", "LastName", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         szWifeLastName = sb_szWifeLastName.toString( );}
         //:GetStringFromAttribute( szHusbandFirstName, qPerson2, szEntityName, "FirstName" )
         {StringBuilder sb_szHusbandFirstName;
         if ( szHusbandFirstName == null )
            sb_szHusbandFirstName = new StringBuilder( 32 );
         else
            sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
                   GetStringFromAttribute( sb_szHusbandFirstName, qPerson2, szEntityName, "FirstName" );
         szHusbandFirstName = sb_szHusbandFirstName.toString( );}
         //:GetStringFromAttribute( szHusbandLastName,  qPerson2, szEntityName, "LastName" )
         {StringBuilder sb_szHusbandLastName;
         if ( szHusbandLastName == null )
            sb_szHusbandLastName = new StringBuilder( 32 );
         else
            sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
                   GetStringFromAttribute( sb_szHusbandLastName, qPerson2, szEntityName, "LastName" );
         szHusbandLastName = sb_szHusbandLastName.toString( );}
      } 

      //:END
   } 

   //:END

   //:IF szHusbandFirstName != "" AND szWifeFirstName != ""
   if ( ZeidonStringCompare( szHusbandFirstName, 1, 0, "", 1, 0, 51 ) != 0 && ZeidonStringCompare( szWifeFirstName, 1, 0, "", 1, 0, 51 ) != 0 )
   { 
      //:IF szHusbandLastName = szWifeLastName
      if ( ZeidonStringCompare( szHusbandLastName, 1, 0, szWifeLastName, 1, 0, 51 ) == 0 )
      { 
         //:szFullName = "Mr. & Mrs. " + szLastName 
         ZeidonStringCopy( szFullName, 1, 0, "Mr. & Mrs. ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
         //:ELSE
      } 
      else
      { 
         //:szFullName = "Mr. " + szHusbandFirstName + " " + szHusbandLastName + " & Mrs. " + 
         //:             szWifeFirstName + " " + szWifeLastName
         ZeidonStringCopy( szFullName, 1, 0, "Mr. ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szHusbandLastName, 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, " & Mrs. ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szWifeLastName, 1, 0, 101 );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:GetStringFromAttribute( szFirstName, qPerson2, szEntityName, "FirstName" )
      {StringBuilder sb_szFirstName;
      if ( szFirstName == null )
         sb_szFirstName = new StringBuilder( 32 );
      else
         sb_szFirstName = new StringBuilder( szFirstName );
             GetStringFromAttribute( sb_szFirstName, qPerson2, szEntityName, "FirstName" );
      szFirstName = sb_szFirstName.toString( );}
      //:GetStringFromAttribute( szGender, qPerson2, szEntityName, "Gender" )
      {StringBuilder sb_szGender;
      if ( szGender == null )
         sb_szGender = new StringBuilder( 32 );
      else
         sb_szGender = new StringBuilder( szGender );
             GetStringFromAttribute( sb_szGender, qPerson2, szEntityName, "Gender" );
      szGender = sb_szGender.toString( );}
      //:IF szGender = "F"
      if ( ZeidonStringCompare( szGender, 1, 0, "F", 1, 0, 2 ) == 0 )
      { 
         //:szFullName = "Ms. " + szLastName
         ZeidonStringCopy( szFullName, 1, 0, "Ms. ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
         //:ELSE
      } 
      else
      { 
         //:IF szGender = "M"
         if ( ZeidonStringCompare( szGender, 1, 0, "M", 1, 0, 2 ) == 0 )
         { 
            //:szFullName = "Mr. " + szLastName
            ZeidonStringCopy( szFullName, 1, 0, "Mr. ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
            //:ELSE
         } 
         else
         { 
            //:szFullName = szFirstName + " " + szLastName
            ZeidonStringCopy( szFullName, 1, 0, szFirstName, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
         } 

         //:END
      } 

      //:END
   } 

   //:END
   //:DropView( qPerson2 )
   DropView( qPerson2 );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:GetPersonCombinedGreetingFirst( STRING ( 100 ) szFullName,
//:                                VIEW qPerson BASED ON LOD qPerson,
//:                                STRING ( 32 )  szEntityName )

//:   VIEW qPerson2 BASED ON LOD qPerson
public int 
GetPersonCombinedGreetingFirst( StringBuilder   szFullName,
                                View     qPerson,
                                String   szEntityName )
{
   zVIEW    qPerson2 = new zVIEW( );
   //:STRING ( 50 )  szHusbandFirstName
   String   szHusbandFirstName = null;
   //:STRING ( 50 )  szWifeFirstName
   String   szWifeFirstName = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:// Format husband and wife as either.
   //://     Donald & Mary, if both names exist.
   //://     Donald, if no spouse.

   //:CreateViewFromView( qPerson2, qPerson )
   CreateViewFromView( qPerson2, qPerson );
   //:szWifeFirstName    = ""
    {StringBuilder sb_szWifeFirstName;
   if ( szWifeFirstName == null )
      sb_szWifeFirstName = new StringBuilder( 32 );
   else
      sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
      ZeidonStringCopy( sb_szWifeFirstName, 1, 0, "", 1, 0, 51 );
   szWifeFirstName = sb_szWifeFirstName.toString( );}
   //:szHusbandFirstName = ""
    {StringBuilder sb_szHusbandFirstName;
   if ( szHusbandFirstName == null )
      sb_szHusbandFirstName = new StringBuilder( 32 );
   else
      sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
      ZeidonStringCopy( sb_szHusbandFirstName, 1, 0, "", 1, 0, 51 );
   szHusbandFirstName = sb_szHusbandFirstName.toString( );}
   //:SET CURSOR FIRST qPerson2.HusbandOrParentRole 
   //:           WHERE qPerson2.HusbandOrParentRole.Role = "S"
   RESULT = SetCursorFirstEntityByString( qPerson2, "HusbandOrParentRole", "Role", "S", "" );
   //:IF RESULT >= zCURSOR_SET AND qPerson2.HusbandOrParent.Deceased != "Y"
   if ( RESULT >= zCURSOR_SET && CompareAttributeToString( qPerson2, "HusbandOrParent", "Deceased", "Y" ) != 0 )
   { 
      //:GetStringFromAttribute( szWifeFirstName, qPerson2, szEntityName, "PreferedFirstName" )
      {StringBuilder sb_szWifeFirstName;
      if ( szWifeFirstName == null )
         sb_szWifeFirstName = new StringBuilder( 32 );
      else
         sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
             GetStringFromAttribute( sb_szWifeFirstName, qPerson2, szEntityName, "PreferedFirstName" );
      szWifeFirstName = sb_szWifeFirstName.toString( );}
      //:IF szWifeFirstName = ""
      if ( ZeidonStringCompare( szWifeFirstName, 1, 0, "", 1, 0, 51 ) == 0 )
      { 
         //:GetStringFromAttribute( szWifeFirstName, qPerson2, szEntityName, "FirstName" )
         {StringBuilder sb_szWifeFirstName;
         if ( szWifeFirstName == null )
            sb_szWifeFirstName = new StringBuilder( 32 );
         else
            sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
                   GetStringFromAttribute( sb_szWifeFirstName, qPerson2, szEntityName, "FirstName" );
         szWifeFirstName = sb_szWifeFirstName.toString( );}
      } 

      //:END
      //:szHusbandFirstName = qPerson2.HusbandOrParent.PreferedFirstName 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szHusbandFirstName;
      if ( szHusbandFirstName == null )
         sb_szHusbandFirstName = new StringBuilder( 32 );
      else
         sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
             GetVariableFromAttribute( sb_szHusbandFirstName, mi_lTempInteger_0, 'S', 51, qPerson2, "HusbandOrParent", "PreferedFirstName", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szHusbandFirstName = sb_szHusbandFirstName.toString( );}
      //:IF szHusbandFirstName = ""
      if ( ZeidonStringCompare( szHusbandFirstName, 1, 0, "", 1, 0, 51 ) == 0 )
      { 
         //:szHusbandFirstName = qPerson2.HusbandOrParent.FirstName 
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szHusbandFirstName;
         if ( szHusbandFirstName == null )
            sb_szHusbandFirstName = new StringBuilder( 32 );
         else
            sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
                   GetVariableFromAttribute( sb_szHusbandFirstName, mi_lTempInteger_1, 'S', 51, qPerson2, "HusbandOrParent", "FirstName", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szHusbandFirstName = sb_szHusbandFirstName.toString( );}
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:SET CURSOR FIRST qPerson2.WifeOrChildRole 
      //:           WHERE qPerson2.WifeOrChildRole.Role = "S" AND qPerson2.WifeOrChild.Deceased != "Y"
      RESULT = SetCursorFirstEntity( qPerson2, "WifeOrChildRole", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( qPerson2, "WifeOrChildRole", "Role", "S" ) != 0 || CompareAttributeToString( qPerson2, "WifeOrChild", "Deceased", "Y" ) == 0 ) )
         { 
            RESULT = SetCursorNextEntity( qPerson2, "WifeOrChildRole", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:szWifeFirstName    = qPerson2.WifeOrChild.PreferedFirstName 
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szWifeFirstName;
         if ( szWifeFirstName == null )
            sb_szWifeFirstName = new StringBuilder( 32 );
         else
            sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
                   GetVariableFromAttribute( sb_szWifeFirstName, mi_lTempInteger_2, 'S', 51, qPerson2, "WifeOrChild", "PreferedFirstName", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szWifeFirstName = sb_szWifeFirstName.toString( );}
         //:IF szWifeFirstName = ""
         if ( ZeidonStringCompare( szWifeFirstName, 1, 0, "", 1, 0, 51 ) == 0 )
         { 
            //:szWifeFirstName    = qPerson2.WifeOrChild.FirstName 
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szWifeFirstName;
            if ( szWifeFirstName == null )
               sb_szWifeFirstName = new StringBuilder( 32 );
            else
               sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
                         GetVariableFromAttribute( sb_szWifeFirstName, mi_lTempInteger_3, 'S', 51, qPerson2, "WifeOrChild", "FirstName", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szWifeFirstName = sb_szWifeFirstName.toString( );}
         } 

         //:END
         //:GetStringFromAttribute( szHusbandFirstName, qPerson2, szEntityName, "PreferedFirstName" )
         {StringBuilder sb_szHusbandFirstName;
         if ( szHusbandFirstName == null )
            sb_szHusbandFirstName = new StringBuilder( 32 );
         else
            sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
                   GetStringFromAttribute( sb_szHusbandFirstName, qPerson2, szEntityName, "PreferedFirstName" );
         szHusbandFirstName = sb_szHusbandFirstName.toString( );}
         //:IF szHusbandFirstName = ""
         if ( ZeidonStringCompare( szHusbandFirstName, 1, 0, "", 1, 0, 51 ) == 0 )
         { 
            //:GetStringFromAttribute( szHusbandFirstName, qPerson2, szEntityName, "FirstName" )
            {StringBuilder sb_szHusbandFirstName;
            if ( szHusbandFirstName == null )
               sb_szHusbandFirstName = new StringBuilder( 32 );
            else
               sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
                         GetStringFromAttribute( sb_szHusbandFirstName, qPerson2, szEntityName, "FirstName" );
            szHusbandFirstName = sb_szHusbandFirstName.toString( );}
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:IF szWifeFirstName != "" AND szHusbandFirstName != ""
   if ( ZeidonStringCompare( szWifeFirstName, 1, 0, "", 1, 0, 51 ) != 0 && ZeidonStringCompare( szHusbandFirstName, 1, 0, "", 1, 0, 51 ) != 0 )
   { 
      //:szFullName = szHusbandFirstName + " & " + szWifeFirstName
      ZeidonStringCopy( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
      ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
      ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
      //:ELSE
   } 
   else
   { 
      //:GetStringFromAttribute( szFullName, qPerson, szEntityName, "PreferedFirstName" )
      GetStringFromAttribute( szFullName, qPerson, szEntityName, "PreferedFirstName" );
      //:IF szFullName = ""
      if ( ZeidonStringCompare( szFullName.toString( ), 1, 0, "", 1, 0, 101 ) == 0 )
      { 
         //:GetStringFromAttribute( szFullName, qPerson, szEntityName, "FirstName" )
         GetStringFromAttribute( szFullName, qPerson, szEntityName, "FirstName" );
      } 

      //:END
   } 

   //:END
   //:DropView( qPerson2 )
   DropView( qPerson2 );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:GetPersonCombinedNameMrAndMrs( STRING ( 100 ) szFullName,
//:                                VIEW qPerson BASED ON LOD qPerson,
//:                                STRING ( 32 )  szEntityName )

//:   VIEW qPerson2 BASED ON LOD qPerson
public int 
GetPersonCombinedNameMrAndMrs( StringBuilder   szFullName,
                               View     qPerson,
                               String   szEntityName )
{
   zVIEW    qPerson2 = new zVIEW( );
   //:STRING ( 50 )  szFirstName
   String   szFirstName = null;
   //:STRING ( 50 )  szLastName
   String   szLastName = null;
   //:STRING ( 50 )  szHusbandFirstName
   String   szHusbandFirstName = null;
   //:STRING ( 50 )  szWifeFirstName
   String   szWifeFirstName = null;
   //:STRING ( 50 )  szHusbandLastName
   String   szHusbandLastName = null;
   //:STRING ( 50 )  szWifeLastName
   String   szWifeLastName = null;
   //:STRING ( 1 )   szGender
   String   szGender = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:// Format husband and wife as either.
   //://     Mr. & Mrs. Donald Baker, if same last name.
   //://     Mr. Donald Baker & Mrs. Mary Edwards, if different last name.
   //://     Mr. Donald Baker, if no spouse.

   //:CreateViewFromView( qPerson2, qPerson )
   CreateViewFromView( qPerson2, qPerson );
   //:szWifeFirstName    = ""
    {StringBuilder sb_szWifeFirstName;
   if ( szWifeFirstName == null )
      sb_szWifeFirstName = new StringBuilder( 32 );
   else
      sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
      ZeidonStringCopy( sb_szWifeFirstName, 1, 0, "", 1, 0, 51 );
   szWifeFirstName = sb_szWifeFirstName.toString( );}
   //:szHusbandFirstName = ""
    {StringBuilder sb_szHusbandFirstName;
   if ( szHusbandFirstName == null )
      sb_szHusbandFirstName = new StringBuilder( 32 );
   else
      sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
      ZeidonStringCopy( sb_szHusbandFirstName, 1, 0, "", 1, 0, 51 );
   szHusbandFirstName = sb_szHusbandFirstName.toString( );}
   //:GetStringFromAttribute( szLastName, qPerson2, szEntityName, "LastName" )
   {StringBuilder sb_szLastName;
   if ( szLastName == null )
      sb_szLastName = new StringBuilder( 32 );
   else
      sb_szLastName = new StringBuilder( szLastName );
       GetStringFromAttribute( sb_szLastName, qPerson2, szEntityName, "LastName" );
   szLastName = sb_szLastName.toString( );}
   //:SET CURSOR FIRST qPerson2.HusbandOrParentRole 
   //:           WHERE qPerson2.HusbandOrParentRole.Role = "S"
   RESULT = SetCursorFirstEntityByString( qPerson2, "HusbandOrParentRole", "Role", "S", "" );
   //:IF RESULT >= zCURSOR_SET AND qPerson2.HusbandOrParent.Deceased != "Y"
   if ( RESULT >= zCURSOR_SET && CompareAttributeToString( qPerson2, "HusbandOrParent", "Deceased", "Y" ) != 0 )
   { 
      //:GetStringFromAttribute( szWifeFirstName, qPerson2, szEntityName, "FirstName" )
      {StringBuilder sb_szWifeFirstName;
      if ( szWifeFirstName == null )
         sb_szWifeFirstName = new StringBuilder( 32 );
      else
         sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
             GetStringFromAttribute( sb_szWifeFirstName, qPerson2, szEntityName, "FirstName" );
      szWifeFirstName = sb_szWifeFirstName.toString( );}
      //:GetStringFromAttribute( szWifeLastName,  qPerson2, szEntityName, "LastName" )
      {StringBuilder sb_szWifeLastName;
      if ( szWifeLastName == null )
         sb_szWifeLastName = new StringBuilder( 32 );
      else
         sb_szWifeLastName = new StringBuilder( szWifeLastName );
             GetStringFromAttribute( sb_szWifeLastName, qPerson2, szEntityName, "LastName" );
      szWifeLastName = sb_szWifeLastName.toString( );}
      //:szHusbandFirstName = qPerson2.HusbandOrParent.FirstName 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szHusbandFirstName;
      if ( szHusbandFirstName == null )
         sb_szHusbandFirstName = new StringBuilder( 32 );
      else
         sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
             GetVariableFromAttribute( sb_szHusbandFirstName, mi_lTempInteger_0, 'S', 51, qPerson2, "HusbandOrParent", "FirstName", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szHusbandFirstName = sb_szHusbandFirstName.toString( );}
      //:szHusbandLastName  = qPerson2.HusbandOrParent.LastName
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szHusbandLastName;
      if ( szHusbandLastName == null )
         sb_szHusbandLastName = new StringBuilder( 32 );
      else
         sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
             GetVariableFromAttribute( sb_szHusbandLastName, mi_lTempInteger_1, 'S', 51, qPerson2, "HusbandOrParent", "LastName", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szHusbandLastName = sb_szHusbandLastName.toString( );}
      //:ELSE
   } 
   else
   { 
      //:SET CURSOR FIRST qPerson2.WifeOrChildRole 
      //:           WHERE qPerson2.WifeOrChildRole.Role = "S" AND qPerson2.WifeOrChild.Deceased != "Y"
      RESULT = SetCursorFirstEntity( qPerson2, "WifeOrChildRole", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( qPerson2, "WifeOrChildRole", "Role", "S" ) != 0 || CompareAttributeToString( qPerson2, "WifeOrChild", "Deceased", "Y" ) == 0 ) )
         { 
            RESULT = SetCursorNextEntity( qPerson2, "WifeOrChildRole", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:szWifeFirstName    = qPerson2.WifeOrChild.FirstName 
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szWifeFirstName;
         if ( szWifeFirstName == null )
            sb_szWifeFirstName = new StringBuilder( 32 );
         else
            sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
                   GetVariableFromAttribute( sb_szWifeFirstName, mi_lTempInteger_2, 'S', 51, qPerson2, "WifeOrChild", "FirstName", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szWifeFirstName = sb_szWifeFirstName.toString( );}
         //:szWifeLastName     = qPerson2.WifeOrChild.LastName
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szWifeLastName;
         if ( szWifeLastName == null )
            sb_szWifeLastName = new StringBuilder( 32 );
         else
            sb_szWifeLastName = new StringBuilder( szWifeLastName );
                   GetVariableFromAttribute( sb_szWifeLastName, mi_lTempInteger_3, 'S', 51, qPerson2, "WifeOrChild", "LastName", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         szWifeLastName = sb_szWifeLastName.toString( );}
         //:GetStringFromAttribute( szHusbandFirstName, qPerson2, szEntityName, "FirstName" )
         {StringBuilder sb_szHusbandFirstName;
         if ( szHusbandFirstName == null )
            sb_szHusbandFirstName = new StringBuilder( 32 );
         else
            sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
                   GetStringFromAttribute( sb_szHusbandFirstName, qPerson2, szEntityName, "FirstName" );
         szHusbandFirstName = sb_szHusbandFirstName.toString( );}
         //:GetStringFromAttribute( szHusbandLastName,  qPerson2, szEntityName, "LastName" )
         {StringBuilder sb_szHusbandLastName;
         if ( szHusbandLastName == null )
            sb_szHusbandLastName = new StringBuilder( 32 );
         else
            sb_szHusbandLastName = new StringBuilder( szHusbandLastName );
                   GetStringFromAttribute( sb_szHusbandLastName, qPerson2, szEntityName, "LastName" );
         szHusbandLastName = sb_szHusbandLastName.toString( );}
      } 

      //:END
   } 

   //:END

   //:IF szHusbandFirstName != "" AND szWifeFirstName != ""
   if ( ZeidonStringCompare( szHusbandFirstName, 1, 0, "", 1, 0, 51 ) != 0 && ZeidonStringCompare( szWifeFirstName, 1, 0, "", 1, 0, 51 ) != 0 )
   { 
      //:IF szHusbandLastName = szWifeLastName
      if ( ZeidonStringCompare( szHusbandLastName, 1, 0, szWifeLastName, 1, 0, 51 ) == 0 )
      { 
         //:szFullName = "Mr. & Mrs. " + szHusbandFirstName + " " + szLastName
         ZeidonStringCopy( szFullName, 1, 0, "Mr. & Mrs. ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
         //:ELSE
      } 
      else
      { 
         //:szFullName = "Mr. " + szHusbandFirstName + " " + szHusbandLastName + " & Mrs. " + 
         //:             szWifeFirstName + " " + szWifeLastName
         ZeidonStringCopy( szFullName, 1, 0, "Mr. ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szHusbandLastName, 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, " & Mrs. ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szWifeLastName, 1, 0, 101 );
      } 

      //:END
      //: 
      //:ELSE
   } 
   else
   { 
      //:GetStringFromAttribute( szFirstName, qPerson2, szEntityName, "FirstName" )
      {StringBuilder sb_szFirstName;
      if ( szFirstName == null )
         sb_szFirstName = new StringBuilder( 32 );
      else
         sb_szFirstName = new StringBuilder( szFirstName );
             GetStringFromAttribute( sb_szFirstName, qPerson2, szEntityName, "FirstName" );
      szFirstName = sb_szFirstName.toString( );}
      //:GetStringFromAttribute( szGender, qPerson2, szEntityName, "Gender" )
      {StringBuilder sb_szGender;
      if ( szGender == null )
         sb_szGender = new StringBuilder( 32 );
      else
         sb_szGender = new StringBuilder( szGender );
             GetStringFromAttribute( sb_szGender, qPerson2, szEntityName, "Gender" );
      szGender = sb_szGender.toString( );}
      //:IF szGender = "F"
      if ( ZeidonStringCompare( szGender, 1, 0, "F", 1, 0, 2 ) == 0 )
      { 
         //:szFullName = "Ms. " + szFirstName + " " + szLastName
         ZeidonStringCopy( szFullName, 1, 0, "Ms. ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szFirstName, 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
         //:ELSE
      } 
      else
      { 
         //:IF szGender = "M"
         if ( ZeidonStringCompare( szGender, 1, 0, "M", 1, 0, 2 ) == 0 )
         { 
            //:szFullName = "Mr. " + szFirstName + " " + szLastName
            ZeidonStringCopy( szFullName, 1, 0, "Mr. ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szFirstName, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
            //:ELSE
         } 
         else
         { 
            //:szFullName = szFirstName + " " + szLastName
            ZeidonStringCopy( szFullName, 1, 0, szFirstName, 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
            ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
         } 

         //:END
      } 

      //:END
   } 

   //:END
   //:DropView( qPerson2 )
   DropView( qPerson2 );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:GetPersonCombinedNameWithFirst( STRING ( 100 ) szFullName,
//:                                VIEW qPerson BASED ON LOD qPerson,
//:                                STRING ( 32 )  szEntityName )

//:   VIEW qPerson2 BASED ON LOD qPerson
public int 
GetPersonCombinedNameWithFirst( StringBuilder   szFullName,
                                View     qPerson,
                                String   szEntityName )
{
   zVIEW    qPerson2 = new zVIEW( );
   //:STRING ( 50 )  szFirstName
   String   szFirstName = null;
   //:STRING ( 50 )  szLastName
   String   szLastName = null;
   //:STRING ( 50 )  szHusbandFirstName
   String   szHusbandFirstName = null;
   //:STRING ( 50 )  szWifeFirstName
   String   szWifeFirstName = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:// Format husband and wife as either.
   //://     Donald & Mary Baker
   //://     Donald Baker, if no spouse.

   //:CreateViewFromView( qPerson2, qPerson )
   CreateViewFromView( qPerson2, qPerson );
   //:szWifeFirstName    = ""
    {StringBuilder sb_szWifeFirstName;
   if ( szWifeFirstName == null )
      sb_szWifeFirstName = new StringBuilder( 32 );
   else
      sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
      ZeidonStringCopy( sb_szWifeFirstName, 1, 0, "", 1, 0, 51 );
   szWifeFirstName = sb_szWifeFirstName.toString( );}
   //:szHusbandFirstName = ""
    {StringBuilder sb_szHusbandFirstName;
   if ( szHusbandFirstName == null )
      sb_szHusbandFirstName = new StringBuilder( 32 );
   else
      sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
      ZeidonStringCopy( sb_szHusbandFirstName, 1, 0, "", 1, 0, 51 );
   szHusbandFirstName = sb_szHusbandFirstName.toString( );}
   //:GetStringFromAttribute( szLastName, qPerson2, szEntityName, "LastName" )
   {StringBuilder sb_szLastName;
   if ( szLastName == null )
      sb_szLastName = new StringBuilder( 32 );
   else
      sb_szLastName = new StringBuilder( szLastName );
       GetStringFromAttribute( sb_szLastName, qPerson2, szEntityName, "LastName" );
   szLastName = sb_szLastName.toString( );}
   //:SET CURSOR FIRST qPerson2.HusbandOrParentRole 
   //:           WHERE qPerson2.HusbandOrParentRole.Role = "S"
   RESULT = SetCursorFirstEntityByString( qPerson2, "HusbandOrParentRole", "Role", "S", "" );
   //:IF RESULT >= zCURSOR_SET AND qPerson2.HusbandOrParent.Deceased != "Y"
   if ( RESULT >= zCURSOR_SET && CompareAttributeToString( qPerson2, "HusbandOrParent", "Deceased", "Y" ) != 0 )
   { 
      //:GetStringFromAttribute( szWifeFirstName, qPerson2, szEntityName, "PreferedFirstName" )
      {StringBuilder sb_szWifeFirstName;
      if ( szWifeFirstName == null )
         sb_szWifeFirstName = new StringBuilder( 32 );
      else
         sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
             GetStringFromAttribute( sb_szWifeFirstName, qPerson2, szEntityName, "PreferedFirstName" );
      szWifeFirstName = sb_szWifeFirstName.toString( );}
      //:IF szWifeFirstName = ""
      if ( ZeidonStringCompare( szWifeFirstName, 1, 0, "", 1, 0, 51 ) == 0 )
      { 
         //:GetStringFromAttribute( szWifeFirstName, qPerson2, szEntityName, "FirstName" )
         {StringBuilder sb_szWifeFirstName;
         if ( szWifeFirstName == null )
            sb_szWifeFirstName = new StringBuilder( 32 );
         else
            sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
                   GetStringFromAttribute( sb_szWifeFirstName, qPerson2, szEntityName, "FirstName" );
         szWifeFirstName = sb_szWifeFirstName.toString( );}
      } 

      //:END
      //:szHusbandFirstName = qPerson2.HusbandOrParent.PreferedFirstName 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szHusbandFirstName;
      if ( szHusbandFirstName == null )
         sb_szHusbandFirstName = new StringBuilder( 32 );
      else
         sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
             GetVariableFromAttribute( sb_szHusbandFirstName, mi_lTempInteger_0, 'S', 51, qPerson2, "HusbandOrParent", "PreferedFirstName", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szHusbandFirstName = sb_szHusbandFirstName.toString( );}
      //:IF szHusbandFirstName = ""
      if ( ZeidonStringCompare( szHusbandFirstName, 1, 0, "", 1, 0, 51 ) == 0 )
      { 
         //:szHusbandFirstName = qPerson2.HusbandOrParent.FirstName 
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szHusbandFirstName;
         if ( szHusbandFirstName == null )
            sb_szHusbandFirstName = new StringBuilder( 32 );
         else
            sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
                   GetVariableFromAttribute( sb_szHusbandFirstName, mi_lTempInteger_1, 'S', 51, qPerson2, "HusbandOrParent", "FirstName", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szHusbandFirstName = sb_szHusbandFirstName.toString( );}
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:SET CURSOR FIRST qPerson2.WifeOrChildRole 
      //:           WHERE qPerson2.WifeOrChildRole.Role = "S" AND qPerson2.WifeOrChild.Deceased != "Y"
      RESULT = SetCursorFirstEntity( qPerson2, "WifeOrChildRole", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( qPerson2, "WifeOrChildRole", "Role", "S" ) != 0 || CompareAttributeToString( qPerson2, "WifeOrChild", "Deceased", "Y" ) == 0 ) )
         { 
            RESULT = SetCursorNextEntity( qPerson2, "WifeOrChildRole", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:szWifeFirstName    = qPerson2.WifeOrChild.PreferedFirstName 
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szWifeFirstName;
         if ( szWifeFirstName == null )
            sb_szWifeFirstName = new StringBuilder( 32 );
         else
            sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
                   GetVariableFromAttribute( sb_szWifeFirstName, mi_lTempInteger_2, 'S', 51, qPerson2, "WifeOrChild", "PreferedFirstName", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szWifeFirstName = sb_szWifeFirstName.toString( );}
         //:IF szWifeFirstName = ""
         if ( ZeidonStringCompare( szWifeFirstName, 1, 0, "", 1, 0, 51 ) == 0 )
         { 
            //:szWifeFirstName    = qPerson2.WifeOrChild.FirstName 
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szWifeFirstName;
            if ( szWifeFirstName == null )
               sb_szWifeFirstName = new StringBuilder( 32 );
            else
               sb_szWifeFirstName = new StringBuilder( szWifeFirstName );
                         GetVariableFromAttribute( sb_szWifeFirstName, mi_lTempInteger_3, 'S', 51, qPerson2, "WifeOrChild", "FirstName", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szWifeFirstName = sb_szWifeFirstName.toString( );}
         } 

         //:END
         //:GetStringFromAttribute( szHusbandFirstName, qPerson2, szEntityName, "PreferedFirstName" )
         {StringBuilder sb_szHusbandFirstName;
         if ( szHusbandFirstName == null )
            sb_szHusbandFirstName = new StringBuilder( 32 );
         else
            sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
                   GetStringFromAttribute( sb_szHusbandFirstName, qPerson2, szEntityName, "PreferedFirstName" );
         szHusbandFirstName = sb_szHusbandFirstName.toString( );}
         //:IF szHusbandFirstName = ""
         if ( ZeidonStringCompare( szHusbandFirstName, 1, 0, "", 1, 0, 51 ) == 0 )
         { 
            //:GetStringFromAttribute( szHusbandFirstName, qPerson2, szEntityName, "FirstName" )
            {StringBuilder sb_szHusbandFirstName;
            if ( szHusbandFirstName == null )
               sb_szHusbandFirstName = new StringBuilder( 32 );
            else
               sb_szHusbandFirstName = new StringBuilder( szHusbandFirstName );
                         GetStringFromAttribute( sb_szHusbandFirstName, qPerson2, szEntityName, "FirstName" );
            szHusbandFirstName = sb_szHusbandFirstName.toString( );}
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:IF szWifeFirstName != "" AND szHusbandFirstName != ""
   if ( ZeidonStringCompare( szWifeFirstName, 1, 0, "", 1, 0, 51 ) != 0 && ZeidonStringCompare( szHusbandFirstName, 1, 0, "", 1, 0, 51 ) != 0 )
   { 
      //:szFullName = szHusbandFirstName + " & " + szWifeFirstName + " " + szLastName 
      ZeidonStringCopy( szFullName, 1, 0, szHusbandFirstName, 1, 0, 101 );
      ZeidonStringConcat( szFullName, 1, 0, " & ", 1, 0, 101 );
      ZeidonStringConcat( szFullName, 1, 0, szWifeFirstName, 1, 0, 101 );
      ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
      ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
      //:ELSE
   } 
   else
   { 
      //:GetStringFromAttribute( szFirstName, qPerson2, szEntityName, "PreferedFirstName" )
      {StringBuilder sb_szFirstName;
      if ( szFirstName == null )
         sb_szFirstName = new StringBuilder( 32 );
      else
         sb_szFirstName = new StringBuilder( szFirstName );
             GetStringFromAttribute( sb_szFirstName, qPerson2, szEntityName, "PreferedFirstName" );
      szFirstName = sb_szFirstName.toString( );}
      //:IF szFirstName = ""
      if ( ZeidonStringCompare( szFirstName, 1, 0, "", 1, 0, 51 ) == 0 )
      { 
         //:GetStringFromAttribute( szFirstName, qPerson2, szEntityName, "FirstName" )
         {StringBuilder sb_szFirstName;
         if ( szFirstName == null )
            sb_szFirstName = new StringBuilder( 32 );
         else
            sb_szFirstName = new StringBuilder( szFirstName );
                   GetStringFromAttribute( sb_szFirstName, qPerson2, szEntityName, "FirstName" );
         szFirstName = sb_szFirstName.toString( );}
      } 

      //:END
      //:szFullName = szFirstName + " " + szLastName 
      ZeidonStringCopy( szFullName, 1, 0, szFirstName, 1, 0, 101 );
      ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
      ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
   } 

   //:END
   //:DropView( qPerson2 )
   DropView( qPerson2 );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:BuildFamilyRole( VIEW xPerson )

//:   VIEW mPerson2 BASED ON LOD mPerson
public int 
BuildFamilyRole( View     xPerson )
{
   zVIEW    mPerson2 = new zVIEW( );
   //:STRING ( 1 )  szRole
   String   szRole = null;
   //:STRING ( 32 ) ParentRoleName
   String   ParentRoleName = null;
   //:STRING ( 32 ) ParentPersonName
   String   ParentPersonName = null;
   //:STRING ( 32 ) DescendantRoleName
   String   DescendantRoleName = null;
   //:STRING ( 32 ) DescendantPersonName
   String   DescendantPersonName = null;
   //:SHORT nRC
   int      nRC = 0;
   int      RESULT = 0;


   //:// Build the derived subobject paths FamilyRole/FamilyPerson.
   //:CreateViewFromView( mPerson2, xPerson)
   CreateViewFromView( mPerson2, xPerson );
   //:nRC = zLodContainsEntity( mPerson2, "DescendantRole" )
   nRC = zLodContainsEntity( mPerson2, "DescendantRole" );
   //:IF nRC >= 0
   if ( nRC >= 0 )
   { 
      //:ParentRoleName       = "ParentRole"
       {StringBuilder sb_ParentRoleName;
      if ( ParentRoleName == null )
         sb_ParentRoleName = new StringBuilder( 32 );
      else
         sb_ParentRoleName = new StringBuilder( ParentRoleName );
            ZeidonStringCopy( sb_ParentRoleName, 1, 0, "ParentRole", 1, 0, 33 );
      ParentRoleName = sb_ParentRoleName.toString( );}
      //:ParentPersonName     = "ParentPerson"
       {StringBuilder sb_ParentPersonName;
      if ( ParentPersonName == null )
         sb_ParentPersonName = new StringBuilder( 32 );
      else
         sb_ParentPersonName = new StringBuilder( ParentPersonName );
            ZeidonStringCopy( sb_ParentPersonName, 1, 0, "ParentPerson", 1, 0, 33 );
      ParentPersonName = sb_ParentPersonName.toString( );}
      //:DescendantRoleName   = "DescendantRole"
       {StringBuilder sb_DescendantRoleName;
      if ( DescendantRoleName == null )
         sb_DescendantRoleName = new StringBuilder( 32 );
      else
         sb_DescendantRoleName = new StringBuilder( DescendantRoleName );
            ZeidonStringCopy( sb_DescendantRoleName, 1, 0, "DescendantRole", 1, 0, 33 );
      DescendantRoleName = sb_DescendantRoleName.toString( );}
      //:DescendantPersonName = "DescendantPerson"
       {StringBuilder sb_DescendantPersonName;
      if ( DescendantPersonName == null )
         sb_DescendantPersonName = new StringBuilder( 32 );
      else
         sb_DescendantPersonName = new StringBuilder( DescendantPersonName );
            ZeidonStringCopy( sb_DescendantPersonName, 1, 0, "DescendantPerson", 1, 0, 33 );
      DescendantPersonName = sb_DescendantPersonName.toString( );}
      //:ELSE
   } 
   else
   { 
      //:ParentRoleName       = "HusbandOrParentRole"
       {StringBuilder sb_ParentRoleName;
      if ( ParentRoleName == null )
         sb_ParentRoleName = new StringBuilder( 32 );
      else
         sb_ParentRoleName = new StringBuilder( ParentRoleName );
            ZeidonStringCopy( sb_ParentRoleName, 1, 0, "HusbandOrParentRole", 1, 0, 33 );
      ParentRoleName = sb_ParentRoleName.toString( );}
      //:ParentPersonName     = "HusbandOrParent"
       {StringBuilder sb_ParentPersonName;
      if ( ParentPersonName == null )
         sb_ParentPersonName = new StringBuilder( 32 );
      else
         sb_ParentPersonName = new StringBuilder( ParentPersonName );
            ZeidonStringCopy( sb_ParentPersonName, 1, 0, "HusbandOrParent", 1, 0, 33 );
      ParentPersonName = sb_ParentPersonName.toString( );}
      //:DescendantRoleName   = "WifeOrChildRole"
       {StringBuilder sb_DescendantRoleName;
      if ( DescendantRoleName == null )
         sb_DescendantRoleName = new StringBuilder( 32 );
      else
         sb_DescendantRoleName = new StringBuilder( DescendantRoleName );
            ZeidonStringCopy( sb_DescendantRoleName, 1, 0, "WifeOrChildRole", 1, 0, 33 );
      DescendantRoleName = sb_DescendantRoleName.toString( );}
      //:DescendantPersonName = "WifeOrChild"
       {StringBuilder sb_DescendantPersonName;
      if ( DescendantPersonName == null )
         sb_DescendantPersonName = new StringBuilder( 32 );
      else
         sb_DescendantPersonName = new StringBuilder( DescendantPersonName );
            ZeidonStringCopy( sb_DescendantPersonName, 1, 0, "WifeOrChild", 1, 0, 33 );
      DescendantPersonName = sb_DescendantPersonName.toString( );}
   } 

   //:END
   //:FOR EACH mPerson2.Person
   RESULT = SetCursorFirstEntity( mPerson2, "Person", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:nRC = SetCursorFirstEntity( mPerson2, ParentRoleName, "" )
      nRC = SetCursorFirstEntity( mPerson2, ParentRoleName, "" );
      //:LOOP WHILE nRC >= zCURSOR_SET 
      while ( nRC >= zCURSOR_SET )
      { 
         //:// Make sure that the child entity exists, although it should if data is correct.
         //:nRC = CheckExistenceOfEntity( mPerson2, ParentPersonName )
         nRC = CheckExistenceOfEntity( mPerson2, ParentPersonName );
         //:IF nRC >= 0
         if ( nRC >= 0 )
         { 
            //://nRC = CompareAttributeToString( mPerson2, ParentPersonName, "Deceased", "Y" )
            //://IF nRC != 0
            //:   CREATE ENTITY mPerson2.FamilyRole
            RESULT = CreateEntity( mPerson2, "FamilyRole", zPOS_AFTER );
            //:   IncludeSubobjectFromSubobject( mPerson2, "FamilyPerson",
            //:                                  mPerson2, ParentPersonName, zPOS_AFTER )
            IncludeSubobjectFromSubobject( mPerson2, "FamilyPerson", mPerson2, ParentPersonName, zPOS_AFTER );
            //:   IF ParentRoleName != "HusbandOrParentRole"
            if ( ZeidonStringCompare( ParentRoleName, 1, 0, "HusbandOrParentRole", 1, 0, 33 ) != 0 )
            { 
               //:   SetAttributeFromAttribute( mPerson2, "FamilyRole",   "LivesWith",
               //:                              mPerson2, ParentRoleName, "LivesWith" )
               SetAttributeFromAttribute( mPerson2, "FamilyRole", "LivesWith", mPerson2, ParentRoleName, "LivesWith" );
            } 

            //:   END
            //:   GetStringFromAttribute( szRole, mPerson2, ParentRoleName, "Role" )
            {StringBuilder sb_szRole;
            if ( szRole == null )
               sb_szRole = new StringBuilder( 32 );
            else
               sb_szRole = new StringBuilder( szRole );
                         GetStringFromAttribute( sb_szRole, mPerson2, ParentRoleName, "Role" );
            szRole = sb_szRole.toString( );}
            //:   IF szRole = "F"
            if ( ZeidonStringCompare( szRole, 1, 0, "F", 1, 0, 2 ) == 0 )
            { 
               //:   mPerson2.FamilyRole.FamilyRole = "Father"
               SetAttributeFromString( mPerson2, "FamilyRole", "FamilyRole", "Father" );
               //:ELSE
            } 
            else
            { 
               //:IF szRole = "M"
               if ( ZeidonStringCompare( szRole, 1, 0, "M", 1, 0, 2 ) == 0 )
               { 
                  //:mPerson2.FamilyRole.FamilyRole = "Mother"
                  SetAttributeFromString( mPerson2, "FamilyRole", "FamilyRole", "Mother" );
                  //:ELSE
               } 
               else
               { 
                  //:IF szRole = "G"
                  if ( ZeidonStringCompare( szRole, 1, 0, "G", 1, 0, 2 ) == 0 )
                  { 
                     //:mPerson2.FamilyRole.FamilyRole = "Guardian"
                     SetAttributeFromString( mPerson2, "FamilyRole", "FamilyRole", "Guardian" );
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF szRole = "P"
                     if ( ZeidonStringCompare( szRole, 1, 0, "P", 1, 0, 2 ) == 0 )
                     { 
                        //:mPerson2.FamilyRole.FamilyRole = "Grandparent"
                        SetAttributeFromString( mPerson2, "FamilyRole", "FamilyRole", "Grandparent" );
                        //:ELSE
                     } 
                     else
                     { 
                        //:IF szRole = "O"
                        if ( ZeidonStringCompare( szRole, 1, 0, "O", 1, 0, 2 ) == 0 )
                        { 
                           //:mPerson2.FamilyRole.FamilyRole = "Other"
                           SetAttributeFromString( mPerson2, "FamilyRole", "FamilyRole", "Other" );
                           //:ELSE
                        } 
                        else
                        { 
                           //:IF szRole = "B"
                           if ( ZeidonStringCompare( szRole, 1, 0, "B", 1, 0, 2 ) == 0 )
                           { 
                              //:mPerson2.FamilyRole.FamilyRole = "Sibling"
                              SetAttributeFromString( mPerson2, "FamilyRole", "FamilyRole", "Sibling" );
                              //:ELSE
                           } 
                           else
                           { 
                              //:IF szRole = "D"
                              if ( ZeidonStringCompare( szRole, 1, 0, "D", 1, 0, 2 ) == 0 )
                              { 
                                 //:mPerson2.FamilyRole.FamilyRole = "Divorced"
                                 SetAttributeFromString( mPerson2, "FamilyRole", "FamilyRole", "Divorced" );
                                 //:ELSE
                              } 
                              else
                              { 
                                 //:mPerson2.FamilyRole.FamilyRole = "Husband"
                                 SetAttributeFromString( mPerson2, "FamilyRole", "FamilyRole", "Husband" );
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

            //:   END
         } 

         //:   //END
         //:END
         //:nRC = SetCursorNextEntity( mPerson2, ParentRoleName, "" )
         nRC = SetCursorNextEntity( mPerson2, ParentRoleName, "" );
      } 

      //:END

      //:nRC = SetCursorFirstEntity( mPerson2, DescendantRoleName, "" )
      nRC = SetCursorFirstEntity( mPerson2, DescendantRoleName, "" );
      //:LOOP WHILE nRC >= zCURSOR_SET 
      while ( nRC >= zCURSOR_SET )
      { 
         //:nRC = CheckExistenceOfEntity( mPerson2, DescendantPersonName )
         nRC = CheckExistenceOfEntity( mPerson2, DescendantPersonName );
         //:IF nRC >= 0
         if ( nRC >= 0 )
         { 
            //://nRC = CompareAttributeToString( mPerson2, DescendantPersonName, "Deceased", "Y" )
            //://IF nRC != 0
            //:   CREATE ENTITY mPerson2.FamilyRole
            RESULT = CreateEntity( mPerson2, "FamilyRole", zPOS_AFTER );
            //:   IncludeSubobjectFromSubobject( mPerson2, "FamilyPerson",
            //:                                  mPerson2, DescendantPersonName, zPOS_AFTER )
            IncludeSubobjectFromSubobject( mPerson2, "FamilyPerson", mPerson2, DescendantPersonName, zPOS_AFTER );
            //:   IF ParentRoleName != "HusbandOrParentRole"
            if ( ZeidonStringCompare( ParentRoleName, 1, 0, "HusbandOrParentRole", 1, 0, 33 ) != 0 )
            { 
               //:   SetAttributeFromAttribute( mPerson2, "FamilyRole",   "LivesWith",
               //:                              mPerson2, DescendantRoleName, "LivesWith" )
               SetAttributeFromAttribute( mPerson2, "FamilyRole", "LivesWith", mPerson2, DescendantRoleName, "LivesWith" );
            } 

            //:   END
            //:   GetStringFromAttribute( szRole, mPerson2, DescendantRoleName, "Role" )
            {StringBuilder sb_szRole;
            if ( szRole == null )
               sb_szRole = new StringBuilder( 32 );
            else
               sb_szRole = new StringBuilder( szRole );
                         GetStringFromAttribute( sb_szRole, mPerson2, DescendantRoleName, "Role" );
            szRole = sb_szRole.toString( );}
            //:   IF szRole = "S"
            if ( ZeidonStringCompare( szRole, 1, 0, "S", 1, 0, 2 ) == 0 )
            { 
               //:   mPerson2.FamilyRole.FamilyRole = "Wife"
               SetAttributeFromString( mPerson2, "FamilyRole", "FamilyRole", "Wife" );
               //:ELSE
            } 
            else
            { 
               //:IF szRole = "O"
               if ( ZeidonStringCompare( szRole, 1, 0, "O", 1, 0, 2 ) == 0 )
               { 
                  //:mPerson2.FamilyRole.FamilyRole = "Other"
                  SetAttributeFromString( mPerson2, "FamilyRole", "FamilyRole", "Other" );
                  //:ELSE
               } 
               else
               { 
                  //:IF szRole = "B"
                  if ( ZeidonStringCompare( szRole, 1, 0, "B", 1, 0, 2 ) == 0 )
                  { 
                     //:mPerson2.FamilyRole.FamilyRole = "Sibling"
                     SetAttributeFromString( mPerson2, "FamilyRole", "FamilyRole", "Sibling" );
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF szRole = "D"
                     if ( ZeidonStringCompare( szRole, 1, 0, "D", 1, 0, 2 ) == 0 )
                     { 
                        //:mPerson2.FamilyRole.FamilyRole = "Divorced"
                        SetAttributeFromString( mPerson2, "FamilyRole", "FamilyRole", "Divorced" );
                        //:ELSE
                     } 
                     else
                     { 
                        //:mPerson2.FamilyRole.FamilyRole = "Child"
                        SetAttributeFromString( mPerson2, "FamilyRole", "FamilyRole", "Child" );
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            //:   END
         } 

         //:   //END
         //:END
         //:nRC = SetCursorNextEntity( mPerson2, DescendantRoleName, "" )
         nRC = SetCursorNextEntity( mPerson2, DescendantRoleName, "" );
      } 

      RESULT = SetCursorNextEntity( mPerson2, "Person", "" );
      //:END
   } 

   //: END
   //:DropView( mPerson2 )
   DropView( mPerson2 );
   //:SET CURSOR FIRST xPerson.FamilyRole 
   RESULT = SetCursorFirstEntity( xPerson, "FamilyRole", "" );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:FormatDocumentFromTemplate( VIEW ViewToWindow,
//:                            VIEW ResultSet,
//:                            VIEW wXferO  BASED ON LOD wXferO,
//:                            VIEW zqMDocO BASED ON LOD zqMDocO,
//:                            VIEW mUser   BASED ON LOD mUser )

//:   VIEW mCntHist BASED ON LOD  mCntHist
public int 
FormatDocumentFromTemplate( View     ViewToWindow,
                            View     ResultSet,
                            View     wXferO,
                            View     zqMDocO,
                            View     mUser )
{
   zVIEW    mCntHist = new zVIEW( );
   //:VIEW sAppMgr  BASED ON LOD  sAppMgr
   zVIEW    sAppMgr = new zVIEW( );
   //:VIEW DocumentTarget
   zVIEW    DocumentTarget = new zVIEW( );
   //:STRING ( 400 ) Msg
   String   Msg = null;
   //:STRING ( 32 )  szRootEntityName
   String   szRootEntityName = null;
   //:STRING ( 32 )  szObjectName
   String   szObjectName = null;
   //:STRING ( 32 )  szSourceEntityName
   String   szSourceEntityName = null;
   //:STRING ( 32 )  szTargetEntityName
   String   szTargetEntityName = null;
   //:STRING ( 256 ) szFullPathName
   String   szFullPathName = null;
   //:STRING ( 256 ) szOpenFileName
   String   szOpenFileName = null;
   //:STRING ( 256 ) szPathName
   String   szPathName = null;
   //:STRING ( 256 ) szSortAttribute
   String   szSortAttribute = null;
   //:SHORT nRC
   int      nRC = 0;
   //:INTEGER lFlag
   int      lFlag = 0;
   //:STRING (  256  ) szTarget
   String   szTarget = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   String   szTempString_3 = null;
   int      lTempInteger_2 = 0;
   int      RESULT = 0;
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;


   //:// Sort the Result Set by either Name or PostalCode, depending upon user request.
   //:szRootEntityName = wXferO.QueryValues.RootEntityName 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szRootEntityName;
   if ( szRootEntityName == null )
      sb_szRootEntityName = new StringBuilder( 32 );
   else
      sb_szRootEntityName = new StringBuilder( szRootEntityName );
       GetVariableFromAttribute( sb_szRootEntityName, mi_lTempInteger_0, 'S', 33, wXferO, "QueryValues", "RootEntityName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szRootEntityName = sb_szRootEntityName.toString( );}
   //:szObjectName     = wXferO.QueryValues.QueryObjectName 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szObjectName;
   if ( szObjectName == null )
      sb_szObjectName = new StringBuilder( 32 );
   else
      sb_szObjectName = new StringBuilder( szObjectName );
       GetVariableFromAttribute( sb_szObjectName, mi_lTempInteger_1, 'S', 33, wXferO, "QueryValues", "QueryObjectName", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szObjectName = sb_szObjectName.toString( );}
   //:IF wXferO.Root.DocumentGenerationSortOption = "N"
   if ( CompareAttributeToString( wXferO, "Root", "DocumentGenerationSortOption", "N" ) == 0 )
   { 
      //:IF szRootEntityName = "Alumni" OR 
      //:   szRootEntityName = "Person" OR
      //:   szRootEntityName = "Student" OR
      //:   szRootEntityName = "Prospect"
      if ( ZeidonStringCompare( szRootEntityName, 1, 0, "Alumni", 1, 0, 33 ) == 0 || ZeidonStringCompare( szRootEntityName, 1, 0, "Person", 1, 0, 33 ) == 0 || ZeidonStringCompare( szRootEntityName, 1, 0, "Student", 1, 0, 33 ) == 0 ||
           ZeidonStringCompare( szRootEntityName, 1, 0, "Prospect", 1, 0, 33 ) == 0 )
      { 

         //:szSortAttribute = "Person.LastName A Person.FirstName A"
          {StringBuilder sb_szSortAttribute;
         if ( szSortAttribute == null )
            sb_szSortAttribute = new StringBuilder( 32 );
         else
            sb_szSortAttribute = new StringBuilder( szSortAttribute );
                  ZeidonStringCopy( sb_szSortAttribute, 1, 0, "Person.LastName A Person.FirstName A", 1, 0, 257 );
         szSortAttribute = sb_szSortAttribute.toString( );}
         //:ELSE
      } 
      else
      { 
         //:IF szRootEntityName = "Church" OR 
         //:   szRootEntityName = "Organization" OR
         //:   szRootEntityName = "School" 
         if ( ZeidonStringCompare( szRootEntityName, 1, 0, "Church", 1, 0, 33 ) == 0 || ZeidonStringCompare( szRootEntityName, 1, 0, "Organization", 1, 0, 33 ) == 0 || ZeidonStringCompare( szRootEntityName, 1, 0, "School", 1, 0, 33 ) == 0 )
         { 

            //:szSortAttribute = "Name A"
             {StringBuilder sb_szSortAttribute;
            if ( szSortAttribute == null )
               sb_szSortAttribute = new StringBuilder( 32 );
            else
               sb_szSortAttribute = new StringBuilder( szSortAttribute );
                        ZeidonStringCopy( sb_szSortAttribute, 1, 0, "Name A", 1, 0, 257 );
            szSortAttribute = sb_szSortAttribute.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF szRootEntityName = "Donor"
            if ( ZeidonStringCompare( szRootEntityName, 1, 0, "Donor", 1, 0, 33 ) == 0 )
            { 

               //:szSortAttribute = "Donor.dNameLFM A"
                {StringBuilder sb_szSortAttribute;
               if ( szSortAttribute == null )
                  sb_szSortAttribute = new StringBuilder( 32 );
               else
                  sb_szSortAttribute = new StringBuilder( szSortAttribute );
                              ZeidonStringCopy( sb_szSortAttribute, 1, 0, "Donor.dNameLFM A", 1, 0, 257 );
               szSortAttribute = sb_szSortAttribute.toString( );}
            } 

            //:END
         } 

         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:IF wXferO.Root.SeasonalAddressDate != ""
      if ( CompareAttributeToString( wXferO, "Root", "SeasonalAddressDate", "" ) != 0 )
      { 
         //:szSortAttribute = "CurrentMailingAddress.PostalCode A"
          {StringBuilder sb_szSortAttribute;
         if ( szSortAttribute == null )
            sb_szSortAttribute = new StringBuilder( 32 );
         else
            sb_szSortAttribute = new StringBuilder( szSortAttribute );
                  ZeidonStringCopy( sb_szSortAttribute, 1, 0, "CurrentMailingAddress.PostalCode A", 1, 0, 257 );
         szSortAttribute = sb_szSortAttribute.toString( );}
         //:ELSE 
      } 
      else
      { 
         //:IF szRootEntityName = "Donor" OR
         //:   szRootEntityName = "Student" OR
         //:   szRootEntityName = "Prospect"
         if ( ZeidonStringCompare( szRootEntityName, 1, 0, "Donor", 1, 0, 33 ) == 0 || ZeidonStringCompare( szRootEntityName, 1, 0, "Student", 1, 0, 33 ) == 0 || ZeidonStringCompare( szRootEntityName, 1, 0, "Prospect", 1, 0, 33 ) == 0 )
         { 

            //:szSortAttribute = "Address.PostalCode A"
             {StringBuilder sb_szSortAttribute;
            if ( szSortAttribute == null )
               sb_szSortAttribute = new StringBuilder( 32 );
            else
               sb_szSortAttribute = new StringBuilder( szSortAttribute );
                        ZeidonStringCopy( sb_szSortAttribute, 1, 0, "Address.PostalCode A", 1, 0, 257 );
            szSortAttribute = sb_szSortAttribute.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szSortAttribute = "PrimaryAddress.PostalCode A"
             {StringBuilder sb_szSortAttribute;
            if ( szSortAttribute == null )
               sb_szSortAttribute = new StringBuilder( 32 );
            else
               sb_szSortAttribute = new StringBuilder( szSortAttribute );
                        ZeidonStringCopy( sb_szSortAttribute, 1, 0, "PrimaryAddress.PostalCode A", 1, 0, 257 );
            szSortAttribute = sb_szSortAttribute.toString( );}
         } 

         //:END
      } 

      //:END
   } 

   //:END
   //:OrderEntityForView( ResultSet, szRootEntityName, szSortAttribute )
   OrderEntityForView( ResultSet, szRootEntityName, szSortAttribute );

   //:// Go to generate seasonal addresses, if necessary.
   //:GenerateSeasonalAddresses( ResultSet, wXferO, zqMDocO.Document.ObjectName )
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, zqMDocO, "Document", "ObjectName" );
   szTempString_0 = sb_szTempString_0.toString( );}
   GenerateSeasonalAddresses( ResultSet, wXferO, szTempString_0 );

   //:// Execute the operation to merge ResultSet information into the template document.
   //:nRC = InsertOI_DataIntoTemplateFile( ViewToWindow, ResultSet,
   //:                                     zqMDocO.Document.OutputFileName,
   //:                                     zqMDocO.Document.TemplateFileName, "",
   //:                                     wXferO.QueryValues.RootEntityName  )
   {StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
       GetStringFromAttribute( sb_szTempString_1, zqMDocO, "Document", "OutputFileName" );
   szTempString_1 = sb_szTempString_1.toString( );}
   {StringBuilder sb_szTempString_2;
   if ( szTempString_2 == null )
      sb_szTempString_2 = new StringBuilder( 32 );
   else
      sb_szTempString_2 = new StringBuilder( szTempString_2 );
       GetStringFromAttribute( sb_szTempString_2, zqMDocO, "Document", "TemplateFileName" );
   szTempString_2 = sb_szTempString_2.toString( );}
   {StringBuilder sb_szTempString_3;
   if ( szTempString_3 == null )
      sb_szTempString_3 = new StringBuilder( 32 );
   else
      sb_szTempString_3 = new StringBuilder( szTempString_3 );
       GetStringFromAttribute( sb_szTempString_3, wXferO, "QueryValues", "RootEntityName" );
   szTempString_3 = sb_szTempString_3.toString( );}
   try
   {
       {
    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( ViewToWindow );
    nRC = m_ZGLOBAL1_Operation.InsertOI_DataIntoTemplateFile( ViewToWindow, ResultSet, szTempString_1, szTempString_2, szTempString_3 );
    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:IF nRC = -2
   if ( nRC == -2 )
   { 
      //:// A return code of -2 means there was a parse error, which is probably the result of Word editing
      //:// characters getting stuck in the middle of a map name.
      //:MessageSend( ViewToWindow, "", "Select Document",
      //:             "There was a mapping error in the document. Recreate the document from scratch.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Select Document", "There was a mapping error in the document. Recreate the document from scratch.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN -1 
      if(8==8)return( -1 );
   } 

   //:END
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:Msg = "An error occurred trying to create the output file." + NEW_LINE +
      //:      "Check that the Generated File directory and Template File name are valid. " +
      //:      "If they are incorrect, correct them. " +
      //:      "If they are OK, report the error to Systems Support."
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringCopy( sb_Msg, 1, 0, "An error occurred trying to create the output file.", 1, 0, 401 );
      Msg = sb_Msg.toString( );}
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 401 );
      Msg = sb_Msg.toString( );}
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringConcat( sb_Msg, 1, 0, "Check that the Generated File directory and Template File name are valid. ", 1, 0, 401 );
      Msg = sb_Msg.toString( );}
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringConcat( sb_Msg, 1, 0, "If they are incorrect, correct them. ", 1, 0, 401 );
      Msg = sb_Msg.toString( );}
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringConcat( sb_Msg, 1, 0, "If they are OK, report the error to Systems Support.", 1, 0, 401 );
      Msg = sb_Msg.toString( );}
      //:MessageSend( ViewToWindow, "", "Select Document",
      //:             Msg,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Select Document", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Turn off select flag and set Processed Flag.
   //:// If Contact Activity is requested, create the entry for Prospect, Church,
   //:// School or Organization.
   //:szRootEntityName = wXferO.QueryValues.RootEntityName
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szRootEntityName;
   if ( szRootEntityName == null )
      sb_szRootEntityName = new StringBuilder( 32 );
   else
      sb_szRootEntityName = new StringBuilder( szRootEntityName );
       GetVariableFromAttribute( sb_szRootEntityName, mi_lTempInteger_2, 'S', 33, wXferO, "QueryValues", "RootEntityName", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szRootEntityName = sb_szRootEntityName.toString( );}
   //:IF zqMDocO.Document.GenerateContactActivityFlag = "Y"
   if ( CompareAttributeToString( zqMDocO, "Document", "GenerateContactActivityFlag", "Y" ) == 0 )
   { 

      //:// Determine source include entity names for including the Person, Church, School or
      //:// Organization into the history entry.
      //:IF szRootEntityName = "Donor" OR szRootEntityName = "Donation" 
      if ( ZeidonStringCompare( szRootEntityName, 1, 0, "Donor", 1, 0, 33 ) == 0 || ZeidonStringCompare( szRootEntityName, 1, 0, "Donation", 1, 0, 33 ) == 0 )
      { 
         //:/*IF ResultSet.DonorPerson EXISTS
         //:   szSourceEntityName = "DonorPerson"
         //:   szTargetEntityName = "Person"
         //:ELSE
         //:   IF ResultSet.DonorChurch EXISTS
         //:      szSourceEntityName = "DonorChurch"
         //:      szTargetEntityName = "Church"
         //:   ELSE
         //:      szSourceEntityName = "DonorOrganization"
         //:      szTargetEntityName = "Organization"
         //:   END
         //:END*/
         //:szSourceEntityName = "Donor"
          {StringBuilder sb_szSourceEntityName;
         if ( szSourceEntityName == null )
            sb_szSourceEntityName = new StringBuilder( 32 );
         else
            sb_szSourceEntityName = new StringBuilder( szSourceEntityName );
                  ZeidonStringCopy( sb_szSourceEntityName, 1, 0, "Donor", 1, 0, 33 );
         szSourceEntityName = sb_szSourceEntityName.toString( );}
         //:szTargetEntityName = "Donor"
          {StringBuilder sb_szTargetEntityName;
         if ( szTargetEntityName == null )
            sb_szTargetEntityName = new StringBuilder( 32 );
         else
            sb_szTargetEntityName = new StringBuilder( szTargetEntityName );
                  ZeidonStringCopy( sb_szTargetEntityName, 1, 0, "Donor", 1, 0, 33 );
         szTargetEntityName = sb_szTargetEntityName.toString( );}
         //:ELSE
      } 
      else
      { 
         //:IF szRootEntityName = "Prospect" OR szRootEntityName = "Alumni"
         if ( ZeidonStringCompare( szRootEntityName, 1, 0, "Prospect", 1, 0, 33 ) == 0 || ZeidonStringCompare( szRootEntityName, 1, 0, "Alumni", 1, 0, 33 ) == 0 )
         { 
            //:szSourceEntityName = "Person"
             {StringBuilder sb_szSourceEntityName;
            if ( szSourceEntityName == null )
               sb_szSourceEntityName = new StringBuilder( 32 );
            else
               sb_szSourceEntityName = new StringBuilder( szSourceEntityName );
                        ZeidonStringCopy( sb_szSourceEntityName, 1, 0, "Person", 1, 0, 33 );
            szSourceEntityName = sb_szSourceEntityName.toString( );}
            //:szTargetEntityName = "Person"
             {StringBuilder sb_szTargetEntityName;
            if ( szTargetEntityName == null )
               sb_szTargetEntityName = new StringBuilder( 32 );
            else
               sb_szTargetEntityName = new StringBuilder( szTargetEntityName );
                        ZeidonStringCopy( sb_szTargetEntityName, 1, 0, "Person", 1, 0, 33 );
            szTargetEntityName = sb_szTargetEntityName.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szSourceEntityName = szRootEntityName
             {StringBuilder sb_szSourceEntityName;
            if ( szSourceEntityName == null )
               sb_szSourceEntityName = new StringBuilder( 32 );
            else
               sb_szSourceEntityName = new StringBuilder( szSourceEntityName );
                        ZeidonStringCopy( sb_szSourceEntityName, 1, 0, szRootEntityName, 1, 0, 33 );
            szSourceEntityName = sb_szSourceEntityName.toString( );}
            //:szTargetEntityName = szRootEntityName
             {StringBuilder sb_szTargetEntityName;
            if ( szTargetEntityName == null )
               sb_szTargetEntityName = new StringBuilder( 32 );
            else
               sb_szTargetEntityName = new StringBuilder( szTargetEntityName );
                        ZeidonStringCopy( sb_szTargetEntityName, 1, 0, szRootEntityName, 1, 0, 33 );
            szTargetEntityName = sb_szTargetEntityName.toString( );}
         } 

         //:END
      } 

      //:END

      //:nRC = SetCursorFirstEntity( ResultSet, szRootEntityName, "" )
      nRC = SetCursorFirstEntity( ResultSet, szRootEntityName, "" );
      //:LOOP WHILE nRC >= zCURSOR_SET
      while ( nRC >= zCURSOR_SET )
      { 
         //:nRC = GetSelectStateOfEntity( ResultSet, szRootEntityName )
         nRC = GetSelectStateOfEntity( ResultSet, szRootEntityName );
         //:IF nRC = 1
         if ( nRC == 1 )
         { 
            //:/// have to check this on each loop for donor/donation as it may be different//
            //:IF szRootEntityName = "Donor" OR szRootEntityName = "Donation" 
            if ( ZeidonStringCompare( szRootEntityName, 1, 0, "Donor", 1, 0, 33 ) == 0 || ZeidonStringCompare( szRootEntityName, 1, 0, "Donation", 1, 0, 33 ) == 0 )
            { 
               //:/*IF ResultSet.DonorPerson EXISTS
               //:      szSourceEntityName = "DonorPerson"
               //:      szTargetEntityName = "Person"
               //:ELSE
               //:   IF ResultSet.DonorChurch EXISTS
               //:      szSourceEntityName = "DonorChurch"
               //:      szTargetEntityName = "Church"
               //:   ELSE
               //:      szSourceEntityName = "DonorOrganization"
               //:      szTargetEntityName = "Organization"
               //:   END
               //:END*/
               //:szSourceEntityName = "Donor"
                {StringBuilder sb_szSourceEntityName;
               if ( szSourceEntityName == null )
                  sb_szSourceEntityName = new StringBuilder( 32 );
               else
                  sb_szSourceEntityName = new StringBuilder( szSourceEntityName );
                              ZeidonStringCopy( sb_szSourceEntityName, 1, 0, "Donor", 1, 0, 33 );
               szSourceEntityName = sb_szSourceEntityName.toString( );}
               //:szTargetEntityName = "Donor"
                {StringBuilder sb_szTargetEntityName;
               if ( szTargetEntityName == null )
                  sb_szTargetEntityName = new StringBuilder( 32 );
               else
                  sb_szTargetEntityName = new StringBuilder( szTargetEntityName );
                              ZeidonStringCopy( sb_szTargetEntityName, 1, 0, "Donor", 1, 0, 33 );
               szTargetEntityName = sb_szTargetEntityName.toString( );}
            } 

            //:END

            //:///
            //:// Create ContactActivity and tie to Prospect, etc.
            //:// Note that szRootEntityName will be Prospect, Church, School or Organization, depending
            //:// on what object we are doing the document for.
            //:ACTIVATE mCntHist EMPTY
            RESULT = ActivateEmptyObjectInstance( mCntHist, "mCntHist", ViewToWindow, zSINGLE );
            //:CREATE ENTITY mCntHist.ContactActivity
            RESULT = CreateEntity( mCntHist, "ContactActivity", zPOS_AFTER );
            //:SetAttributeFromCurrentDateTime( mCntHist, "ContactActivity", "CompletedDateTime" )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mCntHist );
             m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mCntHist, "ContactActivity", "CompletedDateTime" );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:mCntHist.ContactActivity.Type = "7"    // Type 7 is "mailing"
            SetAttributeFromString( mCntHist, "ContactActivity", "Type", "7" );
            //:mCntHist.ContactActivity.Note = "Document: " + zqMDocO.Document.Name
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szTempString_5;
            if ( szTempString_5 == null )
               sb_szTempString_5 = new StringBuilder( 32 );
            else
               sb_szTempString_5 = new StringBuilder( szTempString_5 );
                         GetVariableFromAttribute( sb_szTempString_5, mi_lTempInteger_3, 'S', 65, zqMDocO, "Document", "Name", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szTempString_5 = sb_szTempString_5.toString( );}
             {StringBuilder sb_szTempString_4;
            if ( szTempString_4 == null )
               sb_szTempString_4 = new StringBuilder( 32 );
            else
               sb_szTempString_4 = new StringBuilder( szTempString_4 );
                        ZeidonStringCopy( sb_szTempString_4, 1, 0, "Document: ", 1, 0, 10001 );
            szTempString_4 = sb_szTempString_4.toString( );}
             {StringBuilder sb_szTempString_4;
            if ( szTempString_4 == null )
               sb_szTempString_4 = new StringBuilder( 32 );
            else
               sb_szTempString_4 = new StringBuilder( szTempString_4 );
                        ZeidonStringConcat( sb_szTempString_4, 1, 0, szTempString_5, 1, 0, 10001 );
            szTempString_4 = sb_szTempString_4.toString( );}
            SetAttributeFromString( mCntHist, "ContactActivity", "Note", szTempString_4 );
            //:nRC = GetViewByName( sAppMgr, "sAppMgr", mCntHist, zLEVEL_APPLICATION )
            nRC = GetViewByName( sAppMgr, "sAppMgr", mCntHist, zLEVEL_APPLICATION );
            //:IF nRC >= 0
            if ( nRC >= 0 )
            { 
               //:mCntHist.ContactActivity.FunctionalArea = sAppMgr.FunctionalArea.FunctionalAreaName
               SetAttributeFromAttribute( mCntHist, "ContactActivity", "FunctionalArea", sAppMgr, "FunctionalArea", "FunctionalAreaName" );
            } 

            //:END
            //:IncludeSubobjectFromSubobject( mCntHist,  szTargetEntityName,
            //:                               ResultSet, szSourceEntityName, zPOS_AFTER )
            IncludeSubobjectFromSubobject( mCntHist, szTargetEntityName, ResultSet, szSourceEntityName, zPOS_AFTER );
            //:INCLUDE mCntHist.User FROM mUser.User
            RESULT = IncludeSubobjectFromSubobject( mCntHist, "User", mUser, "User", zPOS_AFTER );
            //:INCLUDE mCntHist.Document FROM zqMDocO.Document
            RESULT = IncludeSubobjectFromSubobject( mCntHist, "Document", zqMDocO, "Document", zPOS_AFTER );
            //:IF wXferO.DocumentGenerationEvent EXISTS
            lTempInteger_4 = CheckExistenceOfEntity( wXferO, "DocumentGenerationEvent" );
            if ( lTempInteger_4 == 0 )
            { 
               //:INCLUDE mCntHist.Event FROM wXferO.DocumentGenerationEvent
               RESULT = IncludeSubobjectFromSubobject( mCntHist, "Event", wXferO, "DocumentGenerationEvent", zPOS_AFTER );
            } 

            //:END
            //:COMMIT mCntHist
            RESULT = CommitObjectInstance( mCntHist );
            //:DropObjectInstance( mCntHist )
            DropObjectInstance( mCntHist );

            //:SetSelectStateOfEntity( ResultSet, szRootEntityName, 0 )
            SetSelectStateOfEntity( ResultSet, szRootEntityName, 0 );
            //:SetAttributeFromString( ResultSet, szRootEntityName, "wProcessedFlag", "Y" )
            SetAttributeFromString( ResultSet, szRootEntityName, "wProcessedFlag", "Y" );
         } 

         //:END
         //:nRC = SetCursorNextEntity( ResultSet, szRootEntityName, "" )
         nRC = SetCursorNextEntity( ResultSet, szRootEntityName, "" );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// We're not creating a ContactActivity entry, so just reset and set the flags.
      //:nRC = SetCursorFirstEntity( ResultSet, szRootEntityName, "" )
      nRC = SetCursorFirstEntity( ResultSet, szRootEntityName, "" );
      //:LOOP WHILE nRC >= zCURSOR_SET
      while ( nRC >= zCURSOR_SET )
      { 
         //:nRC = GetSelectStateOfEntity( ResultSet, szRootEntityName )
         nRC = GetSelectStateOfEntity( ResultSet, szRootEntityName );
         //:IF nRC = 1
         if ( nRC == 1 )
         { 
            //:SetSelectStateOfEntity( ResultSet, szRootEntityName, 0 )
            SetSelectStateOfEntity( ResultSet, szRootEntityName, 0 );
            //:SetAttributeFromString( ResultSet, szRootEntityName, "wProcessedFlag", "Y" )
            SetAttributeFromString( ResultSet, szRootEntityName, "wProcessedFlag", "Y" );
         } 

         //:END
         //:nRC = SetCursorNextEntity( ResultSet, szRootEntityName, "" )
         nRC = SetCursorNextEntity( ResultSet, szRootEntityName, "" );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:SetAddressLongitudeLatitude( VIEW OI_View,
//:                             STRING ( 32 ) AddressEntityName,
//:                             STRING ( 10 ) ZipCode )
//:   STRING ( 5 ) ShortZipCode
public int 
SetAddressLongitudeLatitude( View     OI_View,
                             String   AddressEntityName,
                             String   ZipCode )
{
   String   ShortZipCode = null;
   //:DECIMAL      Latitude
   double  Latitude = 0.0;
   //:DECIMAL      Longitude
   double  Longitude = 0.0;


   //:ShortZipCode = ZipCode
    {StringBuilder sb_ShortZipCode;
   if ( ShortZipCode == null )
      sb_ShortZipCode = new StringBuilder( 32 );
   else
      sb_ShortZipCode = new StringBuilder( ShortZipCode );
      ZeidonStringCopy( sb_ShortZipCode, 1, 0, ZipCode, 1, 0, 6 );
   ShortZipCode = sb_ShortZipCode.toString( );}
   //:GetZipLatLong( ShortZipCode, OI_View, Latitude, Longitude )
   {MutableDouble md_Longitude = new MutableDouble( Longitude );
   MutableDouble md_Latitude = new MutableDouble( Latitude );
       GetZipLatLong( ShortZipCode, OI_View, md_Latitude, md_Longitude );
   Longitude = md_Longitude.doubleValue( );
   Latitude = md_Latitude.doubleValue( );}
   //:SetAttributeFromDecimal( OI_View, AddressEntityName, "Latitude", Latitude )
   SetAttributeFromDecimal( OI_View, AddressEntityName, "Latitude", Latitude );
   //:SetAttributeFromDecimal( OI_View, AddressEntityName, "Longitude", Longitude )
   SetAttributeFromDecimal( OI_View, AddressEntityName, "Longitude", Longitude );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:GenerateTransactionEntry( VIEW OIView )

//:   VIEW mUser BASED ON LOD  mUser
public int 
GenerateTransactionEntry( View     OIView )
{
   zVIEW    mUser = new zVIEW( );

   //:SHORT nRC
   int      nRC = 0;
   //:STRING ( 15 ) szDateTime1
   String   szDateTime1 = null;
   //:STRING ( 15 ) szDateTime2
   String   szDateTime2 = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:nRC = ObjectInstanceUpdated( OIView )
   nRC = ObjectInstanceUpdated( OIView );
   //:IF nRC > 0
   if ( nRC > 0 )
   { 
      //:nRC = GetViewByName( mUser, "mUser", OIView, zLEVEL_APPLICATION )
      nRC = GetViewByName( mUser, "mUser", OIView, zLEVEL_APPLICATION );
      //:IF nRC >= 0
      if ( nRC >= 0 )
      { 
         //:OrderEntityForView( OIView, "Transaction", "TransDateTime D" )
         OrderEntityForView( OIView, "Transaction", "TransDateTime D" );
         //:SET CURSOR FIRST OIView.Transaction
         RESULT = SetCursorFirstEntity( OIView, "Transaction", "" );
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:szDateTime1 = OIView.Transaction.TransDateTime
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szDateTime1;
            if ( szDateTime1 == null )
               sb_szDateTime1 = new StringBuilder( 32 );
            else
               sb_szDateTime1 = new StringBuilder( szDateTime1 );
                         GetVariableFromAttribute( sb_szDateTime1, mi_lTempInteger_0, 'S', 16, OIView, "Transaction", "TransDateTime", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szDateTime1 = sb_szDateTime1.toString( );}
         } 

         //:END
         //:CreateTemporalEntity( OIView, "Transaction", zPOS_AFTER )
         CreateTemporalEntity( OIView, "Transaction", zPOS_AFTER );
         //:OIView.Transaction.UserID   = mUser.User.ID
         SetAttributeFromAttribute( OIView, "Transaction", "UserID", mUser, "User", "ID" );
         //:OIView.Transaction.UserName = mUser.User.UserName
         SetAttributeFromAttribute( OIView, "Transaction", "UserName", mUser, "User", "UserName" );
         //:SetAttributeFromCurrentDateTime( OIView, "Transaction", "TransDateTime" )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( OIView );
          m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( OIView, "Transaction", "TransDateTime" );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:szDateTime2 = OIView.Transaction.TransDateTime
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szDateTime2;
         if ( szDateTime2 == null )
            sb_szDateTime2 = new StringBuilder( 32 );
         else
            sb_szDateTime2 = new StringBuilder( szDateTime2 );
                   GetVariableFromAttribute( sb_szDateTime2, mi_lTempInteger_1, 'S', 16, OIView, "Transaction", "TransDateTime", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szDateTime2 = sb_szDateTime2.toString( );}
         //:IF szDateTime2 = szDateTime1
         if ( ZeidonStringCompare( szDateTime2, 1, 0, szDateTime1, 1, 0, 16 ) == 0 )
         { 
            //:// We have two transactions in row with almost the same time/stamp, which means object was committed twice in row.
            //:// So, don't keep the new transaction.
            //:CancelSubobject( OIView, "Transaction" )
            CancelSubobject( OIView, "Transaction" );
            //:ELSE
         } 
         else
         { 
            //:AcceptSubobject( OIView, "Transaction" )
            AcceptSubobject( OIView, "Transaction" );
         } 

         //:END
         //:OrderEntityForView( OIView, "Transaction", "TransDateTime D" )
         OrderEntityForView( OIView, "Transaction", "TransDateTime D" );
         //:SET CURSOR FIRST OIView.Transaction
         RESULT = SetCursorFirstEntity( OIView, "Transaction", "" );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:InitializeDynamicDomain( VIEW DOMAINT BASED ON LOD DOMAINT )

//:   VIEW vXDM
public int 
InitializeDynamicDomain( View     DOMAINT )
{
   zVIEW    vXDM = new zVIEW( );
   //:STRING ( 400 ) szFileName
   String   szFileName = null;
   int      RESULT = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;


   //:// Initialize all Dynamic Domains with the values specified in the static Domain definition.

   //:// Activate the XDM object.
   //:GetApplDirectoryFromView( szFileName, DOMAINT, zAPPL_DIR_OBJECT, 400 )
   {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
       GetApplDirectoryFromView( sb_szFileName, DOMAINT, zAPPL_DIR_OBJECT, 400 );
   szFileName = sb_szFileName.toString( );}
   //:szFileName = szFileName + "zeidon.xdm"
    {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
      ZeidonStringConcat( sb_szFileName, 1, 0, "zeidon.xdm", 1, 0, 401 );
   szFileName = sb_szFileName.toString( );}
   //:// 536870912 is ACTIVATE_SYSTEM in the following activate statement.
   //:ActivateOI_FromFile( vXDM, "TZDMXGPO", DOMAINT, szFileName, 536870912 )
   ActivateOI_FromFile( vXDM, "TZDMXGPO", DOMAINT, szFileName, 536870912 );
   //:NAME VIEW vXDM "XDM"
   SetNameForView( vXDM, "XDM", null, zLEVEL_TASK );
   //:SET CURSOR FIRST vXDM.Domain WHERE vXDM.Domain.Name = DOMAINT.Domain.Name
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, DOMAINT, "Domain", "Name" );
   szTempString_0 = sb_szTempString_0.toString( );}
   RESULT = SetCursorFirstEntityByString( vXDM, "Domain", "Name", szTempString_0, "" );

   //:// Merge in any DomainValue entries that are not already there.
   //:FOR EACH vXDM.TableEntry WHERE vXDM.TableEntry.InternalValue != ""
   RESULT = SetCursorFirstEntity( vXDM, "TableEntry", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( vXDM, "TableEntry", "InternalValue", "" ) != 0 )
      { 
         //:SET CURSOR FIRST DOMAINT.DomainValue
         //:        WHERE DOMAINT.DomainValue.InternalStringValue = vXDM.TableEntry.InternalValue
         {StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetStringFromAttribute( sb_szTempString_1, vXDM, "TableEntry", "InternalValue" );
         szTempString_1 = sb_szTempString_1.toString( );}
         RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", szTempString_1, "" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:SET CURSOR LAST DOMAINT.DomainValue
            RESULT = SetCursorLastEntity( DOMAINT, "DomainValue", "" );
            //:CREATE ENTITY DOMAINT.DomainValue
            RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
            //:DOMAINT.DomainValue.InternalStringValue = vXDM.TableEntry.InternalValue
            SetAttributeFromAttribute( DOMAINT, "DomainValue", "InternalStringValue", vXDM, "TableEntry", "InternalValue" );
            //:DOMAINT.DomainValue.ExternalDescription = vXDM.TableEntry.ExternalValue
            SetAttributeFromAttribute( DOMAINT, "DomainValue", "ExternalDescription", vXDM, "TableEntry", "ExternalValue" );
         } 

      } 

      RESULT = SetCursorNextEntity( vXDM, "TableEntry", "" );
      //:END
   } 

   //:END
   //:SET CURSOR FIRST DOMAINT.DomainValue
   RESULT = SetCursorFirstEntity( DOMAINT, "DomainValue", "" );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:ActivateDomainsForDivision( VIEW ViewToWindow )

//:   VIEW wXferO    REGISTERED AS wXferO
public int 
ActivateDomainsForDivision( View     ViewToWindow )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW DOMAINT   BASED ON LOD DOMAINT
   zVIEW    DOMAINT = new zVIEW( );
   //:VIEW DOMAINTN  BASED ON LOD DOMAINT
   zVIEW    DOMAINTN = new zVIEW( );
   //:VIEW DOMAINTNC BASED ON LOD DOMAINT
   zVIEW    DOMAINTNC = new zVIEW( );
   //:VIEW mUser     BASED ON LOD mUser
   zVIEW    mUser = new zVIEW( );
   //:STRING ( 50 ) szDomainName
   String   szDomainName = null;
   //:INTEGER iAdminDiv
   int      iAdminDiv = 0;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_TASK );

   //:// Activate dynamic Domains that need to be preloaded. This is all Domains tied to a Admin Div, but
   //:// also all Domains requested for preload.

   //:// First check for mUser at the task level.  This is how it should be on the 
   //:// Web whereas Windows has views at the application level.
   //:nRC = GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_TASK )
   nRC = GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_TASK );
   //:IF nRC < 0 
   if ( nRC < 0 )
   { 
      //:// This is also called from the online inquiry page where there is no
      //:// mUser.  If mUser doesn't exist, set the admin div to 1.
      //:nRC = GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_APPLICATION )
      nRC = GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_APPLICATION );
      //:IF nRC >= 0 
      if ( nRC >= 0 )
      { 
         //:// CurrentAdministrativeDivision should usually exist but if it doesn't, assume
         //:// we are loading main campus domains.
         //:IF mUser.CurrentAdministrativeDivision EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mUser, "CurrentAdministrativeDivision" );
         if ( lTempInteger_0 == 0 )
         { 
            //:iAdminDiv = mUser.CurrentAdministrativeDivision.ID
            {MutableInt mi_iAdminDiv = new MutableInt( iAdminDiv );
                         GetIntegerFromAttribute( mi_iAdminDiv, mUser, "CurrentAdministrativeDivision", "ID" );
            iAdminDiv = mi_iAdminDiv.intValue( );}
            //:ELSE
         } 
         else
         { 
            //:iAdminDiv = 1
            iAdminDiv = 1;
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:iAdminDiv = 1
         iAdminDiv = 1;
      } 

      //:END
   } 

   //:END
   //:// For all the dynamic Domains that are tied to the current Administrative Division,
   //:// create domain named views of the form X_name, where name is the Domain Name.
   //:// This preloads the Domain tables for the dynamic Domains with Administrative Divisions.
   //:GET VIEW DOMAINT NAMED "X_DynamicDomains"
   RESULT = GetViewByName( DOMAINT, "X_DynamicDomains", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( DOMAINT )
      DropObjectInstance( DOMAINT );
   } 

   //:END
   //: 
   //:ACTIVATE DOMAINT MULTIPLE
   //:   WHERE DOMAINT.AdministrativeDivision.ID = iAdminDiv
   //:      OR DOMAINT.Domain.RequiresPreloadFlag = "Y"
   o_fnLocalBuildQual_11( ViewToWindow, vTempViewVar_0, iAdminDiv );
   RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW DOMAINT "X_DynamicDomains"
   SetNameForView( DOMAINT, "X_DynamicDomains", null, zLEVEL_TASK );
   //:FOR EACH DOMAINT.Domain
   RESULT = SetCursorFirstEntity( DOMAINT, "Domain", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// Create an entry for each Domain.
      //:CreateViewFromView( DOMAINTN, DOMAINT )
      CreateViewFromView( DOMAINTN, DOMAINT );
      //:szDomainName = "X_" + DOMAINT.Domain.Name
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 33, DOMAINT, "Domain", "Name", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szDomainName;
      if ( szDomainName == null )
         sb_szDomainName = new StringBuilder( 32 );
      else
         sb_szDomainName = new StringBuilder( szDomainName );
            ZeidonStringCopy( sb_szDomainName, 1, 0, "X_", 1, 0, 51 );
      szDomainName = sb_szDomainName.toString( );}
       {StringBuilder sb_szDomainName;
      if ( szDomainName == null )
         sb_szDomainName = new StringBuilder( 32 );
      else
         sb_szDomainName = new StringBuilder( szDomainName );
            ZeidonStringConcat( sb_szDomainName, 1, 0, szTempString_0, 1, 0, 51 );
      szDomainName = sb_szDomainName.toString( );}
      //:NAME VIEW DOMAINTN szDomainName
      SetNameForView( DOMAINTN, szDomainName, null, zLEVEL_TASK );
      //://SetNameForView( DOMAINTN, szDomainName, ViewToWindow, zLEVEL_TASK )

      //:// Create an entry for each Domain Context.
      //:FOR EACH DOMAINTN.Context 
      RESULT = SetCursorFirstEntity( DOMAINTN, "Context", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY DOMAINT.Domain 
         RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
         //:DOMAINT.Domain.Name = DOMAINTN.Context.Name 
         SetAttributeFromAttribute( DOMAINT, "Domain", "Name", DOMAINTN, "Context", "Name" );
         //:FOR EACH DOMAINTN.ContextDomainValue 
         RESULT = SetCursorFirstEntity( DOMAINTN, "ContextDomainValue", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY DOMAINT.DomainValue 
            RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
            //:DOMAINT.DomainValue.InternalStringValue = DOMAINTN.ContextDomainValue.InternalStringValue 
            SetAttributeFromAttribute( DOMAINT, "DomainValue", "InternalStringValue", DOMAINTN, "ContextDomainValue", "InternalStringValue" );
            //:DOMAINT.DomainValue.ExternalDescription = DOMAINTN.ContextDomainValue.ExternalDescription 
            SetAttributeFromAttribute( DOMAINT, "DomainValue", "ExternalDescription", DOMAINTN, "ContextDomainValue", "ExternalDescription" );
            RESULT = SetCursorNextEntity( DOMAINTN, "ContextDomainValue", "" );
         } 

         //:END
         //:CreateViewFromView( DOMAINTNC, DOMAINT )
         CreateViewFromView( DOMAINTNC, DOMAINT );
         //:szDomainName = "X_" + DOMAINTNC.Domain.Name
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 33, DOMAINTNC, "Domain", "Name", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szDomainName;
         if ( szDomainName == null )
            sb_szDomainName = new StringBuilder( 32 );
         else
            sb_szDomainName = new StringBuilder( szDomainName );
                  ZeidonStringCopy( sb_szDomainName, 1, 0, "X_", 1, 0, 51 );
         szDomainName = sb_szDomainName.toString( );}
          {StringBuilder sb_szDomainName;
         if ( szDomainName == null )
            sb_szDomainName = new StringBuilder( 32 );
         else
            sb_szDomainName = new StringBuilder( szDomainName );
                  ZeidonStringConcat( sb_szDomainName, 1, 0, szTempString_1, 1, 0, 51 );
         szDomainName = sb_szDomainName.toString( );}
         //:NAME VIEW DOMAINTNC szDomainName
         SetNameForView( DOMAINTNC, szDomainName, null, zLEVEL_TASK );
         RESULT = SetCursorNextEntity( DOMAINTN, "Context", "" );
      } 

      RESULT = SetCursorNextEntity( DOMAINT, "Domain", "" );
      //:   //SetNameForView( DOMAINTN, szDomainName, ViewToWindow, zLEVEL_TASK )
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:ActivateDocumentsForDivision( VIEW ViewToWindow )

//:   VIEW wXferO     REGISTERED AS wXferO
public int 
ActivateDocumentsForDivision( View     ViewToWindow )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW zqMDocOLST BASED ON LOD  zqMDocO
   zVIEW    zqMDocOLST = new zVIEW( );
   //:VIEW sAppMgr    BASED ON LOD  sAppMgr
   zVIEW    sAppMgr = new zVIEW( );
   //:VIEW mUser      BASED ON LOD  mUser
   zVIEW    mUser = new zVIEW( );
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   String   szTempString_2 = null;
   String   szTempString_3 = null;
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_TASK );

   //:// This operation activates ALL Documents for maintenance by the FunctionalAreaName. Since the list
   //:// is for maintenance, it includes both activate and inactive entries.

   //:GET VIEW zqMDocOLST NAMED "zqMDocOLST"
   RESULT = GetViewByName( zqMDocOLST, "zqMDocOLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( zqMDocOLST )
      DropObjectInstance( zqMDocOLST );
   } 

   //:END
   //:GetViewByName( sAppMgr, "sAppMgr", ViewToWindow, zLEVEL_APPLICATION )
   GetViewByName( sAppMgr, "sAppMgr", ViewToWindow, zLEVEL_APPLICATION );

   //:IF sAppMgr.FunctionalArea.FunctionalAreaName = "Admissions" OR
   //:   sAppMgr.FunctionalArea.FunctionalAreaName = "Registrar" OR
   //:   sAppMgr.FunctionalArea.FunctionalAreaName = "Retention" OR
   //:   sAppMgr.FunctionalArea.FunctionalAreaName = "StudentServices" OR
   //:   sAppMgr.FunctionalArea.FunctionalAreaName = "StudentAccounts" OR
   //:   sAppMgr.FunctionalArea.FunctionalAreaName = "FinancialAid"
   if ( CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "Admissions" ) == 0 || CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "Registrar" ) == 0 ||
        CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "Retention" ) == 0 || CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "StudentServices" ) == 0 ||
        CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "StudentAccounts" ) == 0 || CompareAttributeToString( sAppMgr, "FunctionalArea", "FunctionalAreaName", "FinancialAid" ) == 0 )
   { 

      //:GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_APPLICATION )
      GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_APPLICATION );
      //:ACTIVATE zqMDocOLST Multiple
      //:    WHERE zqMDocOLST.Document.ObjectName         = wXferO.QueryValues.QueryObjectName
      //:      AND zqMDocOLST.Document.FunctionalAreaName = sAppMgr.FunctionalArea.FunctionalAreaName
      //:      AND zqMDocOLST.AdministrativeDivision.ID   = mUser.CurrentAdministrativeDivision.ID
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, wXferO, "QueryValues", "QueryObjectName" );
      szTempString_0 = sb_szTempString_0.toString( );}
      {StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
             GetStringFromAttribute( sb_szTempString_1, sAppMgr, "FunctionalArea", "FunctionalAreaName" );
      szTempString_1 = sb_szTempString_1.toString( );}
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "CurrentAdministrativeDivision", "ID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, szTempString_0, szTempString_1, lTempInteger_0 );
      RESULT = ActivateObjectInstance( zqMDocOLST, "zqMDocO", ViewToWindow, vTempViewVar_0, zMULTIPLE );
      DropView( vTempViewVar_0 );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE zqMDocOLST Multiple
      //:    WHERE zqMDocOLST.Document.ObjectName         = wXferO.QueryValues.QueryObjectName
      //:      AND zqMDocOLST.Document.FunctionalAreaName = sAppMgr.FunctionalArea.FunctionalAreaName
      {StringBuilder sb_szTempString_2;
      if ( szTempString_2 == null )
         sb_szTempString_2 = new StringBuilder( 32 );
      else
         sb_szTempString_2 = new StringBuilder( szTempString_2 );
             GetStringFromAttribute( sb_szTempString_2, wXferO, "QueryValues", "QueryObjectName" );
      szTempString_2 = sb_szTempString_2.toString( );}
      {StringBuilder sb_szTempString_3;
      if ( szTempString_3 == null )
         sb_szTempString_3 = new StringBuilder( 32 );
      else
         sb_szTempString_3 = new StringBuilder( szTempString_3 );
             GetStringFromAttribute( sb_szTempString_3, sAppMgr, "FunctionalArea", "FunctionalAreaName" );
      szTempString_3 = sb_szTempString_3.toString( );}
      o_fnLocalBuildQual_4( ViewToWindow, vTempViewVar_1, szTempString_2, szTempString_3 );
      RESULT = ActivateObjectInstance( zqMDocOLST, "zqMDocO", ViewToWindow, vTempViewVar_1, zMULTIPLE );
      DropView( vTempViewVar_1 );
   } 

   //:END

   //:NAME VIEW zqMDocOLST "zqMDocOLST"
   SetNameForView( zqMDocOLST, "zqMDocOLST", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:GetPersonFullName( STRING ( 100 ) szFullName,
//:                   VIEW           vOI,
//:                   STRING ( 32 )  szEntityName )

//:   STRING ( 30 )  szFirstName
public int 
GetPersonFullName( StringBuilder   szFullName,
                   View     vOI,
                   String   szEntityName )
{
   String   szFirstName = null;
   //:STRING ( 30 )  szMiddleName
   String   szMiddleName = null;
   //:STRING ( 30 )  szLastName
   String   szLastName = null;
   //:STRING ( 10 )  szSuffix
   String   szSuffix = null;


   //:szFullName = ""
   ZeidonStringCopy( szFullName, 1, 0, "", 1, 0, 101 );
   //:GetStringFromAttribute( szFirstName, vOI, szEntityName, "FirstName" )
   {StringBuilder sb_szFirstName;
   if ( szFirstName == null )
      sb_szFirstName = new StringBuilder( 32 );
   else
      sb_szFirstName = new StringBuilder( szFirstName );
       GetStringFromAttribute( sb_szFirstName, vOI, szEntityName, "FirstName" );
   szFirstName = sb_szFirstName.toString( );}
   //:IF szFirstName != ""
   if ( ZeidonStringCompare( szFirstName, 1, 0, "", 1, 0, 31 ) != 0 )
   { 
      //:szFullName = szFirstName
      ZeidonStringCopy( szFullName, 1, 0, szFirstName, 1, 0, 101 );
   } 

   //:END
   //:GetStringFromAttribute( szMiddleName, vOI, szEntityName, "MiddleName" )
   {StringBuilder sb_szMiddleName;
   if ( szMiddleName == null )
      sb_szMiddleName = new StringBuilder( 32 );
   else
      sb_szMiddleName = new StringBuilder( szMiddleName );
       GetStringFromAttribute( sb_szMiddleName, vOI, szEntityName, "MiddleName" );
   szMiddleName = sb_szMiddleName.toString( );}
   //:IF szMiddleName != ""
   if ( ZeidonStringCompare( szMiddleName, 1, 0, "", 1, 0, 31 ) != 0 )
   { 
      //:IF szFullName != ""
      if ( ZeidonStringCompare( szFullName.toString( ), 1, 0, "", 1, 0, 101 ) != 0 )
      { 
         //:szFullName = szFullName + " "
         ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
      } 

      //:END
      //:szFullName = szFullName + szMiddleName
      ZeidonStringConcat( szFullName, 1, 0, szMiddleName, 1, 0, 101 );
   } 

   //:END
   //:GetStringFromAttribute( szLastName, vOI, szEntityName, "LastName" )
   {StringBuilder sb_szLastName;
   if ( szLastName == null )
      sb_szLastName = new StringBuilder( 32 );
   else
      sb_szLastName = new StringBuilder( szLastName );
       GetStringFromAttribute( sb_szLastName, vOI, szEntityName, "LastName" );
   szLastName = sb_szLastName.toString( );}
   //:GetStringFromAttribute( szSuffix, vOI, szEntityName, "Suffix" )
   {StringBuilder sb_szSuffix;
   if ( szSuffix == null )
      sb_szSuffix = new StringBuilder( 32 );
   else
      sb_szSuffix = new StringBuilder( szSuffix );
       GetStringFromAttribute( sb_szSuffix, vOI, szEntityName, "Suffix" );
   szSuffix = sb_szSuffix.toString( );}
   //:IF szSuffix != ""
   if ( ZeidonStringCompare( szSuffix, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:szLastName = szLastName + " " + szSuffix
       {StringBuilder sb_szLastName;
      if ( szLastName == null )
         sb_szLastName = new StringBuilder( 32 );
      else
         sb_szLastName = new StringBuilder( szLastName );
            ZeidonStringConcat( sb_szLastName, 1, 0, " ", 1, 0, 31 );
      szLastName = sb_szLastName.toString( );}
       {StringBuilder sb_szLastName;
      if ( szLastName == null )
         sb_szLastName = new StringBuilder( 32 );
      else
         sb_szLastName = new StringBuilder( szLastName );
            ZeidonStringConcat( sb_szLastName, 1, 0, szSuffix, 1, 0, 31 );
      szLastName = sb_szLastName.toString( );}
   } 

   //:END
   //:IF szLastName != ""
   if ( ZeidonStringCompare( szLastName, 1, 0, "", 1, 0, 31 ) != 0 )
   { 
      //:IF szFullName != ""
      if ( ZeidonStringCompare( szFullName.toString( ), 1, 0, "", 1, 0, 101 ) != 0 )
      { 
         //:szFullName = szFullName + " "
         ZeidonStringConcat( szFullName, 1, 0, " ", 1, 0, 101 );
      } 

      //:END
      //:szFullName = szFullName + szLastName
      ZeidonStringConcat( szFullName, 1, 0, szLastName, 1, 0, 101 );
   } 

   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:OperatorWebPrompt( VIEW ViewToWindow,
//:                   STRING ( 32 ) Title,
//:                   STRING ( 32 ) Message )

//:   VIEW wXferO REGISTERED AS wXferO
public int 
OperatorWebPrompt( View     ViewToWindow,
                   String   Title,
                   String   Message )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szObjectName
   String   szObjectName = null;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_TASK );

   //:nRC = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_TASK )
   nRC = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_TASK );
   //:IF nRC <= 0
   if ( nRC <= 0 )
   { 
      //:nRC = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_APPLICATION )
      nRC = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_APPLICATION );
   } 

   //:END

   //:// If the ViewToWindow is a valid Windows view, process as standard windows error with a
   //:// MessageSend and SetWindowActionBehavior.
   //:// If it is a Web view, simply put the message in the wXferO area.
   //:MiGetObjectNameForView( szObjectName, ViewToWindow )
   {StringBuilder sb_szObjectName;
   if ( szObjectName == null )
      sb_szObjectName = new StringBuilder( 32 );
   else
      sb_szObjectName = new StringBuilder( szObjectName );
       MiGetObjectNameForView( sb_szObjectName, ViewToWindow );
   szObjectName = sb_szObjectName.toString( );}
   //:IF szObjectName != ""
   if ( ZeidonStringCompare( szObjectName, 1, 0, "", 1, 0, 33 ) != 0 )
   { 
      //:// It is a windows view, prompt operator.
      //:nRC = MessagePrompt( ViewToWindow,
      //:                     "",
      //:                     Title,
      //:                     Message,
      //:                     0,
      //:                     zBUTTONS_YESNO,
      //:                     zRESPONSE_NO,
      //:                     0 )
      nRC = MessagePrompt( ViewToWindow, "", Title, Message, 0, zBUTTONS_YESNO, zRESPONSE_NO, 0 );
      //:RETURN nRC
      if(8==8)return( nRC );
      //:ELSE
   } 
   else
   { 
      //:// It is a web condition.
      //:IF wXferO.Root.WebPromptValue = 0
      if ( CompareAttributeToInteger( wXferO, "Root", "WebPromptValue", 0 ) == 0 )
      { 
         //:wXferO.Root.WebReturnMessage = Message
         SetAttributeFromString( wXferO, "Root", "WebReturnMessage", Message );
         //:RETURN 8
         if(8==8)return( 8 );
         //:ELSE
      } 
      else
      { 
         //:nRC = wXferO.Root.WebPromptValue
         {MutableInt mi_nRC = new MutableInt( nRC );
                   GetIntegerFromAttribute( mi_nRC, wXferO, "Root", "WebPromptValue" );
         nRC = mi_nRC.intValue( );}
         //:RETURN nRC
         if(8==8)return( nRC );
      } 

      //:END
   } 


   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:OperatorErrorResponse( VIEW ViewToWindow,
//:                       STRING ( 32 ) Title,
//:                       STRING ( 32 ) Message )

//:   VIEW wXferO BASED ON LOD wXferO
public int 
OperatorErrorResponse( View     ViewToWindow,
                       String   Title,
                       String   Message )
{
   zVIEW    wXferO = new zVIEW( );
   //:STRING ( 32 ) szObjectName
   String   szObjectName = null;
   //:SHORT nRC
   int      nRC = 0;


   //:nRC = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_TASK )
   nRC = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_TASK );
   //:IF nRC <= 0
   if ( nRC <= 0 )
   { 
      //:nRC = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_APPLICATION )
      nRC = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_APPLICATION );
   } 

   //:END

   //:// If the ViewToWindow is a valid Windows view, process as standard windows error with a
   //:// MessageSend and SetWindowActionBehavior.
   //:// If it is a Web view, simply put the message in the wXferO area.
   //:MiGetObjectNameForView( szObjectName, ViewToWindow )
   {StringBuilder sb_szObjectName;
   if ( szObjectName == null )
      sb_szObjectName = new StringBuilder( 32 );
   else
      sb_szObjectName = new StringBuilder( szObjectName );
       MiGetObjectNameForView( sb_szObjectName, ViewToWindow );
   szObjectName = sb_szObjectName.toString( );}
   //:IF szObjectName != ""
   if ( ZeidonStringCompare( szObjectName, 1, 0, "", 1, 0, 33 ) != 0 )
   { 
      //:// It is a windows view. Send message, if there is one.
      //:IF Message != ""
      if ( ZeidonStringCompare( Message, 1, 0, "", 1, 0, 33 ) != 0 )
      { 
         //:MessageSend( ViewToWindow, "", Title,
         //:             Message,
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", Title, Message, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      } 

      //:END
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   } 

   //:/*ELSE
   //:   // It is a web condition.
   //:   wXferO.Root.WebReturnMessage = Message  */
   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:InitializeDynamicDomains( VIEW AnyView )

//:   VIEW vXDM
public int 
InitializeDynamicDomains( View     AnyView )
{
   zVIEW    vXDM = new zVIEW( );
   //:VIEW DOMAINT  BASED ON LOD DOMAINT
   zVIEW    DOMAINT = new zVIEW( );
   //:VIEW DOMAINT2 BASED ON LOD DOMAINT
   zVIEW    DOMAINT2 = new zVIEW( );
   //:STRING ( 400 ) szFileName
   String   szFileName = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   String   szTempString_1 = null;


   //:// Initialize all Dynamic Domains with the values specified in the static Domain definition.

   //:// Activate the XDM object.
   //:GetApplDirectoryFromView( szFileName, AnyView, zAPPL_DIR_OBJECT, 400 )
   {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
       GetApplDirectoryFromView( sb_szFileName, AnyView, zAPPL_DIR_OBJECT, 400 );
   szFileName = sb_szFileName.toString( );}
   //:szFileName = szFileName + "zeidon.xdm"
    {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
      ZeidonStringConcat( sb_szFileName, 1, 0, "zeidon.xdm", 1, 0, 401 );
   szFileName = sb_szFileName.toString( );}
   //:// 536870912 is ACTIVATE_SYSTEM in the following activate statement.
   //:ActivateOI_FromFile( vXDM, "TZDMXGPO", AnyView, szFileName, 536870912 )
   ActivateOI_FromFile( vXDM, "TZDMXGPO", AnyView, szFileName, 536870912 );
   //:NAME VIEW vXDM "XDM"
   SetNameForView( vXDM, "XDM", null, zLEVEL_TASK );

   //:// Loop through each Dynamic Domain in the XDM, activating the Dynamic Domain and
   //:// merging the values in the XDM.
   //:FOR EACH vXDM.Domain
   RESULT = SetCursorFirstEntity( vXDM, "Domain", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF vXDM.Domain.OperName != "" AND vXDM.TableEntry EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( vXDM, "TableEntry" );
      if ( CompareAttributeToString( vXDM, "Domain", "OperName", "" ) != 0 && lTempInteger_0 == 0 )
      { 
         //:ACTIVATE DOMAINT WHERE DOMAINT.Domain.Name = vXDM.Domain.Name
         {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetStringFromAttribute( sb_szTempString_0, vXDM, "Domain", "Name" );
         szTempString_0 = sb_szTempString_0.toString( );}
         o_fnLocalBuildQual_2( AnyView, vTempViewVar_0, szTempString_0 );
         RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", AnyView, vTempViewVar_0, zSINGLE );
         DropView( vTempViewVar_0 );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:ACTIVATE DOMAINT EMPTY
            RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", AnyView, zSINGLE );
            //:CREATE ENTITY DOMAINT.Domain
            RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
            //:DOMAINT.Domain.Name = vXDM.Domain.Name
            SetAttributeFromAttribute( DOMAINT, "Domain", "Name", vXDM, "Domain", "Name" );
         } 

         //:END
         //:CreateViewFromView( DOMAINT2, DOMAINT )
         CreateViewFromView( DOMAINT2, DOMAINT );
         //:SET CURSOR FIRST DOMAINT2.Domain
         RESULT = SetCursorFirstEntity( DOMAINT2, "Domain", "" );
         //:FOR EACH vXDM.TableEntry WHERE vXDM.TableEntry.InternalValue != ""
         RESULT = SetCursorFirstEntity( vXDM, "TableEntry", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( vXDM, "TableEntry", "InternalValue", "" ) != 0 )
            { 
               //:SET CURSOR FIRST DOMAINT.DomainValue
               //:        WHERE DOMAINT.DomainValue.InternalStringValue = vXDM.TableEntry.InternalValue
               {StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetStringFromAttribute( sb_szTempString_1, vXDM, "TableEntry", "InternalValue" );
               szTempString_1 = sb_szTempString_1.toString( );}
               RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", szTempString_1, "" );
               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:CREATE ENTITY DOMAINT.DomainValue
                  RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
                  //:DOMAINT.DomainValue.InternalStringValue = vXDM.TableEntry.InternalValue
                  SetAttributeFromAttribute( DOMAINT, "DomainValue", "InternalStringValue", vXDM, "TableEntry", "InternalValue" );
                  //:DOMAINT.DomainValue.ExternalDescription = vXDM.TableEntry.ExternalValue
                  SetAttributeFromAttribute( DOMAINT, "DomainValue", "ExternalDescription", vXDM, "TableEntry", "ExternalValue" );
               } 

            } 

            RESULT = SetCursorNextEntity( vXDM, "TableEntry", "" );
            //:END
         } 

         //:END
         //:COMMIT DOMAINT
         RESULT = CommitObjectInstance( DOMAINT );
         //:DropObjectInstance( DOMAINT )
         DropObjectInstance( DOMAINT );
      } 

      RESULT = SetCursorNextEntity( vXDM, "Domain", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:GenerateSeasonalAddresses( VIEW vResultSet,
//:                           VIEW wXferO BASED ON LOD wXferO,
//:                           STRING ( 32 ) ObjectViewName )

//:   STRING ( 32 ) RootEntityName
public int 
GenerateSeasonalAddresses( View     vResultSet,
                           View     wXferO,
                           String   ObjectViewName )
{
   String   RootEntityName = null;
   //:STRING ( 32 ) AddressEntityName
   String   AddressEntityName = null;
   //:STRING ( 32 ) PrimaryAddressEntityName
   String   PrimaryAddressEntityName = null;
   //:STRING ( 8 )  szSeasonalDate
   String   szSeasonalDate = null;
   //:STRING ( 4 )  szSeasonalMonthDay
   String   szSeasonalMonthDay = null;
   //:STRING ( 4 )  szFromMonthDay
   String   szFromMonthDay = null;
   //:STRING ( 4 )  szToMonthDay
   String   szToMonthDay = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;


   //:// Generate the derived entity CurrentMailingAddress for query objects that have that entity defined in the LOD.
   //:// It is expected that the object has the Address entity named "Address" and the Primary Address named "PrimaryAddress",
   //:// unless it is identified as an exception below, as for qDonor and qDonate.

   //:// Exist if the object does not have a CurrentMailingAddress entity.
   //:nRC = zLodContainsEntity( vResultSet, "CurrentMailingAddress" )
   nRC = zLodContainsEntity( vResultSet, "CurrentMailingAddress" );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:RETURN 
      if(8==8)return( 0 );
   } 

   //:END

   //:// Determine the Address/Primary address entity names for those objects that aren't using the standard Address and
   //:// PrimaryAddress entity names.
   //:IF ObjectViewName = "qProspct"
   if ( ZeidonStringCompare( ObjectViewName, 1, 0, "qProspct", 1, 0, 33 ) == 0 )
   { 
      //:AddressEntityName        = "Address"
       {StringBuilder sb_AddressEntityName;
      if ( AddressEntityName == null )
         sb_AddressEntityName = new StringBuilder( 32 );
      else
         sb_AddressEntityName = new StringBuilder( AddressEntityName );
            ZeidonStringCopy( sb_AddressEntityName, 1, 0, "Address", 1, 0, 33 );
      AddressEntityName = sb_AddressEntityName.toString( );}
      //:PrimaryAddressEntityName = "AllAddress"
       {StringBuilder sb_PrimaryAddressEntityName;
      if ( PrimaryAddressEntityName == null )
         sb_PrimaryAddressEntityName = new StringBuilder( 32 );
      else
         sb_PrimaryAddressEntityName = new StringBuilder( PrimaryAddressEntityName );
            ZeidonStringCopy( sb_PrimaryAddressEntityName, 1, 0, "AllAddress", 1, 0, 33 );
      PrimaryAddressEntityName = sb_PrimaryAddressEntityName.toString( );}
      //:ELSE
   } 
   else
   { 
      //:IF ObjectViewName = "qDonor" OR ObjectViewName = "qDonate" OR ObjectViewName = "qDonCmb"
      if ( ZeidonStringCompare( ObjectViewName, 1, 0, "qDonor", 1, 0, 33 ) == 0 || ZeidonStringCompare( ObjectViewName, 1, 0, "qDonate", 1, 0, 33 ) == 0 || ZeidonStringCompare( ObjectViewName, 1, 0, "qDonCmb", 1, 0, 33 ) == 0 )
      { 
         //:AddressEntityName        = "DonorPersonAddress"
          {StringBuilder sb_AddressEntityName;
         if ( AddressEntityName == null )
            sb_AddressEntityName = new StringBuilder( 32 );
         else
            sb_AddressEntityName = new StringBuilder( AddressEntityName );
                  ZeidonStringCopy( sb_AddressEntityName, 1, 0, "DonorPersonAddress", 1, 0, 33 );
         AddressEntityName = sb_AddressEntityName.toString( );}
         //:PrimaryAddressEntityName = "DonorPersonPrimaryAddress"
          {StringBuilder sb_PrimaryAddressEntityName;
         if ( PrimaryAddressEntityName == null )
            sb_PrimaryAddressEntityName = new StringBuilder( 32 );
         else
            sb_PrimaryAddressEntityName = new StringBuilder( PrimaryAddressEntityName );
                  ZeidonStringCopy( sb_PrimaryAddressEntityName, 1, 0, "DonorPersonPrimaryAddress", 1, 0, 33 );
         PrimaryAddressEntityName = sb_PrimaryAddressEntityName.toString( );}
         //:ELSE
      } 
      else
      { 
         //:AddressEntityName        = "Address"
          {StringBuilder sb_AddressEntityName;
         if ( AddressEntityName == null )
            sb_AddressEntityName = new StringBuilder( 32 );
         else
            sb_AddressEntityName = new StringBuilder( AddressEntityName );
                  ZeidonStringCopy( sb_AddressEntityName, 1, 0, "Address", 1, 0, 33 );
         AddressEntityName = sb_AddressEntityName.toString( );}
         //:PrimaryAddressEntityName = "PrimaryAddress"
          {StringBuilder sb_PrimaryAddressEntityName;
         if ( PrimaryAddressEntityName == null )
            sb_PrimaryAddressEntityName = new StringBuilder( 32 );
         else
            sb_PrimaryAddressEntityName = new StringBuilder( PrimaryAddressEntityName );
                  ZeidonStringCopy( sb_PrimaryAddressEntityName, 1, 0, "PrimaryAddress", 1, 0, 33 );
         PrimaryAddressEntityName = sb_PrimaryAddressEntityName.toString( );}
      } 

      //:END
   } 

   //:END

   //:// A Current Mailing address is always generated depending on the following two rules.
   //:// 1. If an address exists with a date range that includes the SeasonalAddressDate sent
   //://    to this operation, then include that address.
   //:// 2. Otherwise, include the PrimaryAddress as the CurrentMailingAddress.
   //:// Note that if no SeasonalAddressDate exists, we always include the PrimaryAddress as the CurrentMailingAddress.

   //:IF wXferO.Root.SeasonalAddressDate = ""
   if ( CompareAttributeToString( wXferO, "Root", "SeasonalAddressDate", "" ) == 0 )
   { 
      //:szSeasonalDate = wXferO.Root.dCurrentDate
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szSeasonalDate;
      if ( szSeasonalDate == null )
         sb_szSeasonalDate = new StringBuilder( 32 );
      else
         sb_szSeasonalDate = new StringBuilder( szSeasonalDate );
             GetVariableFromAttribute( sb_szSeasonalDate, mi_lTempInteger_0, 'S', 9, wXferO, "Root", "dCurrentDate", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szSeasonalDate = sb_szSeasonalDate.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szSeasonalDate = wXferO.Root.SeasonalAddressDate
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szSeasonalDate;
      if ( szSeasonalDate == null )
         sb_szSeasonalDate = new StringBuilder( 32 );
      else
         sb_szSeasonalDate = new StringBuilder( szSeasonalDate );
             GetVariableFromAttribute( sb_szSeasonalDate, mi_lTempInteger_1, 'S', 9, wXferO, "Root", "SeasonalAddressDate", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szSeasonalDate = sb_szSeasonalDate.toString( );}
   } 

   //:END
   //:szSeasonalMonthDay = szSeasonalDate[5:4]
    {StringBuilder sb_szSeasonalMonthDay;
   if ( szSeasonalMonthDay == null )
      sb_szSeasonalMonthDay = new StringBuilder( 32 );
   else
      sb_szSeasonalMonthDay = new StringBuilder( szSeasonalMonthDay );
      ZeidonStringCopy( sb_szSeasonalMonthDay, 1, 0, szSeasonalDate, 5, 4, 5 );
   szSeasonalMonthDay = sb_szSeasonalMonthDay.toString( );}

   //:// Loop through each root entity and create CurrentMailingAddress entity.
   //:zGetFirstEntityNameForView( vResultSet, RootEntityName )
   {StringBuilder sb_RootEntityName;
   if ( RootEntityName == null )
      sb_RootEntityName = new StringBuilder( 32 );
   else
      sb_RootEntityName = new StringBuilder( RootEntityName );
       zGetFirstEntityNameForView( vResultSet, sb_RootEntityName );
   RootEntityName = sb_RootEntityName.toString( );}
   //:nRC = SetCursorFirstEntity( vResultSet, RootEntityName, "" )
   nRC = SetCursorFirstEntity( vResultSet, RootEntityName, "" );
   //:LOOP WHILE nRC >= zCURSOR_SET
   while ( nRC >= zCURSOR_SET )
   { 

      //:IF szSeasonalDate != ""
      if ( ZeidonStringCompare( szSeasonalDate, 1, 0, "", 1, 0, 9 ) != 0 )
      { 
         //:// A Seasonal Date has been specified, so process each Address entry.
         //:nRC = SetCursorFirstEntity( vResultSet, AddressEntityName, "" )
         nRC = SetCursorFirstEntity( vResultSet, AddressEntityName, "" );
         //:LOOP WHILE nRC >= zCURSOR_SET
         while ( nRC >= zCURSOR_SET )
         { 
            //:GetStringFromAttribute( szFromMonthDay, vResultSet, AddressEntityName, "ValidFromMonthDay" )
            {StringBuilder sb_szFromMonthDay;
            if ( szFromMonthDay == null )
               sb_szFromMonthDay = new StringBuilder( 32 );
            else
               sb_szFromMonthDay = new StringBuilder( szFromMonthDay );
                         GetStringFromAttribute( sb_szFromMonthDay, vResultSet, AddressEntityName, "ValidFromMonthDay" );
            szFromMonthDay = sb_szFromMonthDay.toString( );}
            //:GetStringFromAttribute( szToMonthDay, vResultSet, AddressEntityName, "ValidToMonthDay" )
            {StringBuilder sb_szToMonthDay;
            if ( szToMonthDay == null )
               sb_szToMonthDay = new StringBuilder( 32 );
            else
               sb_szToMonthDay = new StringBuilder( szToMonthDay );
                         GetStringFromAttribute( sb_szToMonthDay, vResultSet, AddressEntityName, "ValidToMonthDay" );
            szToMonthDay = sb_szToMonthDay.toString( );}

            //:// There are two different situations for the compare:
            //:// 1. The FromMonthDay is less than the ToMonthDay, meaning both dates will be for the same year.
            //:// 2. The FromMonthDay is greater than the ToMonthDay, meaning the two dates are for different years.
            //:// In the first case, we do a simple compare of the two values to szSeasonalMonthDay.
            //:// In the second case, we will have to do two compares, the first involving the period from FromMonthDay to 12/31
            //:// and the second involving the period from 01/01 to ToMonthDay.
            //:// Skip the code if either szFromMonthDay or szToMonthDay are null
            //:IF szFromMonthDay != "" AND szToMonthDay != ""
            if ( ZeidonStringCompare( szFromMonthDay, 1, 0, "", 1, 0, 5 ) != 0 && ZeidonStringCompare( szToMonthDay, 1, 0, "", 1, 0, 5 ) != 0 )
            { 
               //:IF szFromMonthDay < szToMonthDay
               if ( ZeidonStringCompare( szFromMonthDay, 1, 0, szToMonthDay, 1, 0, 5 ) > 0 )
               { 
                  //:// Simple single compare
                  //:IF szSeasonalMonthDay >= szFromMonthDay AND 
                  //:   szSeasonalMonthDay <= szToMonthDay
                  if ( ZeidonStringCompare( szSeasonalMonthDay, 1, 0, szFromMonthDay, 1, 0, 5 ) <= 0 && ZeidonStringCompare( szSeasonalMonthDay, 1, 0, szToMonthDay, 1, 0, 5 ) >= 0 )
                  { 

                     //:IF vResultSet.CurrentMailingAddress DOES NOT EXIST
                     lTempInteger_2 = CheckExistenceOfEntity( vResultSet, "CurrentMailingAddress" );
                     if ( lTempInteger_2 != 0 )
                     { 
                        //:IncludeSubobjectFromSubobject( vResultSet, "CurrentMailingAddress",
                        //:                               vResultSet, AddressEntityName, zPOS_AFTER )
                        IncludeSubobjectFromSubobject( vResultSet, "CurrentMailingAddress", vResultSet, AddressEntityName, zPOS_AFTER );
                     } 

                     //:END
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:// More complex double compare
                  //:IF ( szSeasonalMonthDay >= szFromMonthDay AND szSeasonalMonthDay <= "1231" ) OR
                  //:   ( szSeasonalMonthDay >= "0101"        AND szSeasonalMonthDay <=  szToMonthDay )
                  if ( ( ZeidonStringCompare( szSeasonalMonthDay, 1, 0, szFromMonthDay, 1, 0, 5 ) <= 0 && ZeidonStringCompare( szSeasonalMonthDay, 1, 0, "1231", 1, 0, 5 ) >= 0 ) ||
                       ( ZeidonStringCompare( szSeasonalMonthDay, 1, 0, "0101", 1, 0, 5 ) <= 0 && ZeidonStringCompare( szSeasonalMonthDay, 1, 0, szToMonthDay, 1, 0, 5 ) >= 0 ) )
                  { 

                     //:IF vResultSet.CurrentMailingAddress DOES NOT EXIST
                     lTempInteger_3 = CheckExistenceOfEntity( vResultSet, "CurrentMailingAddress" );
                     if ( lTempInteger_3 != 0 )
                     { 
                        //:IncludeSubobjectFromSubobject( vResultSet, "CurrentMailingAddress",
                        //:                               vResultSet, AddressEntityName, zPOS_AFTER )
                        IncludeSubobjectFromSubobject( vResultSet, "CurrentMailingAddress", vResultSet, AddressEntityName, zPOS_AFTER );
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
            //:nRC = SetCursorNextEntity( vResultSet, AddressEntityName, "" )
            nRC = SetCursorNextEntity( vResultSet, AddressEntityName, "" );
         } 

         //:END
      } 

      //:END

      //:// Include Primary Address if we didn't get a seasonal match above.
      //:IF vResultSet.CurrentMailingAddress DOES NOT EXIST
      lTempInteger_4 = CheckExistenceOfEntity( vResultSet, "CurrentMailingAddress" );
      if ( lTempInteger_4 != 0 )
      { 
         //:nRC = CheckExistenceOfEntity( vResultSet, PrimaryAddressEntityName )
         nRC = CheckExistenceOfEntity( vResultSet, PrimaryAddressEntityName );
         //:IF nRC >= zCURSOR_SET
         if ( nRC >= zCURSOR_SET )
         { 
            //:IncludeSubobjectFromSubobject( vResultSet, "CurrentMailingAddress",
            //:                               vResultSet, PrimaryAddressEntityName, zPOS_AFTER )
            IncludeSubobjectFromSubobject( vResultSet, "CurrentMailingAddress", vResultSet, PrimaryAddressEntityName, zPOS_AFTER );
            //:ELSE
         } 
         else
         { 
            //:// If that fails, include Address.
            //:nRC = CheckExistenceOfEntity( vResultSet, AddressEntityName )
            nRC = CheckExistenceOfEntity( vResultSet, AddressEntityName );
            //: IF nRC >= zCURSOR_SET
            if ( nRC >= zCURSOR_SET )
            { 
               //: IncludeSubobjectFromSubobject( vResultSet, "CurrentMailingAddress",
               //:                                vResultSet, AddressEntityName, zPOS_AFTER )
               IncludeSubobjectFromSubobject( vResultSet, "CurrentMailingAddress", vResultSet, AddressEntityName, zPOS_AFTER );
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:nRC = SetCursorNextEntity( vResultSet, RootEntityName, "" )
      nRC = SetCursorNextEntity( vResultSet, RootEntityName, "" );
   } 

   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:GetZipLatLong( STRING ( 32 ) szZipCode,
//:               VIEW  zAny,
//:               DECIMAL dLatitude,
//:               DECIMAL dLongitude )
//:               
//:   VIEW sZipFind  BASED ON LOD sZipFind
public int 
GetZipLatLong( String   szZipCode,
               View     zAny,
               MutableDouble  dLatitude,
               MutableDouble  dLongitude )
{
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:GetDaysInMonth( STRING ( 32 ) szDate )

//:   INTEGER nDays
public int  
GetDaysInMonth( String   szDate )
{
   int      nDays = 0;
   //:INTEGER nLeapYear
   int      nLeapYear = 0;
   //:STRING (  10  ) szMonth
   String   szMonth = null;


   //:UfFormatDateTime( szMonth, szDate, "M" )
   {StringBuilder sb_szMonth;
   if ( szMonth == null )
      sb_szMonth = new StringBuilder( 32 );
   else
      sb_szMonth = new StringBuilder( szMonth );
       UfFormatDateTime( sb_szMonth, szDate, "M" );
   szMonth = sb_szMonth.toString( );}

   //:IF szMonth = "4" OR szMonth = "6" OR szMonth = "9" OR szMonth = "11"
   if ( ZeidonStringCompare( szMonth, 1, 0, "4", 1, 0, 11 ) == 0 || ZeidonStringCompare( szMonth, 1, 0, "6", 1, 0, 11 ) == 0 || ZeidonStringCompare( szMonth, 1, 0, "9", 1, 0, 11 ) == 0 || ZeidonStringCompare( szMonth, 1, 0, "11", 1, 0, 11 ) == 0 )
   { 
      //:nDays = 30
      nDays = 30;
      //:RETURN nDays
      if(8==8)return( nDays );
   } 

   //:END
   //:IF szMonth = "1" OR szMonth = "3" OR szMonth = "5" OR szMonth = "7"
   //:      OR szMonth = "8" OR szMonth = "10" OR szMonth = "12"
   if ( ZeidonStringCompare( szMonth, 1, 0, "1", 1, 0, 11 ) == 0 || ZeidonStringCompare( szMonth, 1, 0, "3", 1, 0, 11 ) == 0 || ZeidonStringCompare( szMonth, 1, 0, "5", 1, 0, 11 ) == 0 || ZeidonStringCompare( szMonth, 1, 0, "7", 1, 0, 11 ) == 0 ||
        ZeidonStringCompare( szMonth, 1, 0, "8", 1, 0, 11 ) == 0 || ZeidonStringCompare( szMonth, 1, 0, "10", 1, 0, 11 ) == 0 || ZeidonStringCompare( szMonth, 1, 0, "12", 1, 0, 11 ) == 0 )
   { 
      //:nDays = 31
      nDays = 31;
      //:RETURN nDays
      if(8==8)return( nDays );
   } 

   //:END
   //:IF szMonth = "2"
   if ( ZeidonStringCompare( szMonth, 1, 0, "2", 1, 0, 11 ) == 0 )
   { 
      //:nLeapYear = zDateIsLeapYear( szDate )
      nLeapYear = zDateIsLeapYear( szDate );
      //:IF nLeapYear > 0
      if ( nLeapYear > 0 )
      { 
         //:nDays = 29
         nDays = 29;
         //:RETURN nDays
         if(8==8)return( nDays );
         //:ELSE
      } 
      else
      { 
         //:nDays = 28
         nDays = 28;
         //:RETURN nDays
         if(8==8)return( nDays );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


private int 
o_fnLocalBuildQual_0( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szZipCode )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ZipCodeLookUpTable" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ZipCodeLookUpTable" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ZipCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szZipCode.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_1( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szZipCode4 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ZipCodeLookUpTable" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ZipCodeLookUpTable" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ZipCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szZipCode4.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "LIKE" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_2( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szTempString_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 



}
