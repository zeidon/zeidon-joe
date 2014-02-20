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

package com.quinsoft.zencas;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.quinsoft.zencas.ZGLOBAL1_Operation;
import com.quinsoft.zencas.lTrnscpt_Object;

/**
 * @author QuinSoft
 *
 */

public class mDegTrk_Object extends VmlObjectOperations
{
   public mDegTrk_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dGroupOL_Value( VIEW mDegTrk BASED ON LOD mDegTrk,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   STRING ( 200 ) szOL_Value
public int 
omDegTrk_dGroupOL_Value( View     mDegTrk,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   String   szOL_Value = null;
   //:STRING ( 10 )  szCredits
   String   szCredits = null;
   //:STRING ( 10 )  szMinGrade
   String   szMinGrade = null;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :


         //:IF mDegTrk.RequiredGroup.ReusableFlag = "Y"
         if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "ReusableFlag", "Y" ) == 0 )
         { 
            //:szOL_Value = "(R) Type " + mDegTrk.RequiredGroup.Type  + ", " + mDegTrk.RequiredGroup.Name
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 2, mDegTrk, "RequiredGroup", "Type", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringCopy( sb_szOL_Value, 1, 0, "(R) Type ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szTempString_0, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, ", ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 51, mDegTrk, "RequiredGroup", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szTempString_1, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
            //:ELSE 
         } 
         else
         { 
            //:szOL_Value = "Type " + mDegTrk.RequiredGroup.Type  + ", " + mDegTrk.RequiredGroup.Name
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_2, 'S', 2, mDegTrk, "RequiredGroup", "Type", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringCopy( sb_szOL_Value, 1, 0, "Type ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szTempString_2, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, ", ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                         GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_3, 'S', 51, mDegTrk, "RequiredGroup", "Name", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szTempString_3 = sb_szTempString_3.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szTempString_3, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
         } 

         //:END
         //:IF mDegTrk.RequiredGroup.Type = "2" OR mDegTrk.RequiredGroup.Type = "3"
         if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "2" ) == 0 || CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "3" ) == 0 )
         { 
            //:szCredits = mDegTrk.RequiredGroup.TotalRequiredCredits
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szCredits;
            if ( szCredits == null )
               sb_szCredits = new StringBuilder( 32 );
            else
               sb_szCredits = new StringBuilder( szCredits );
                         GetVariableFromAttribute( sb_szCredits, mi_lTempInteger_4, 'S', 11, mDegTrk, "RequiredGroup", "TotalRequiredCredits", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szCredits = sb_szCredits.toString( );}

            //:szOL_Value = szOL_Value + ", Req Credits: " + szCredits
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, ", Req Credits: ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szCredits, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
         } 

         //:END
         //:GetStringFromAttributeByContext( szMinGrade,
         //:                              mDegTrk, "RequiredGroup", "MinimumGrade", "", 10 )
         {StringBuilder sb_szMinGrade;
         if ( szMinGrade == null )
            sb_szMinGrade = new StringBuilder( 32 );
         else
            sb_szMinGrade = new StringBuilder( szMinGrade );
                   GetStringFromAttributeByContext( sb_szMinGrade, mDegTrk, "RequiredGroup", "MinimumGrade", "", 10 );
         szMinGrade = sb_szMinGrade.toString( );}
         //:szOL_Value = szOL_Value + ", Min Grade: " + szMinGrade
          {StringBuilder sb_szOL_Value;
         if ( szOL_Value == null )
            sb_szOL_Value = new StringBuilder( 32 );
         else
            sb_szOL_Value = new StringBuilder( szOL_Value );
                  ZeidonStringConcat( sb_szOL_Value, 1, 0, ", Min Grade: ", 1, 0, 201 );
         szOL_Value = sb_szOL_Value.toString( );}
          {StringBuilder sb_szOL_Value;
         if ( szOL_Value == null )
            sb_szOL_Value = new StringBuilder( 32 );
         else
            sb_szOL_Value = new StringBuilder( szOL_Value );
                  ZeidonStringConcat( sb_szOL_Value, 1, 0, szMinGrade, 1, 0, 201 );
         szOL_Value = sb_szOL_Value.toString( );}

         //:StoreStringInRecord ( mDegTrk,
         //:                   InternalEntityStructure, InternalAttribStructure, szOL_Value )
         StoreStringInRecord( mDegTrk, InternalEntityStructure, InternalAttribStructure, szOL_Value );
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


private int 
omDegTrk_fnLocalBuildQual_2( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "DegreeTrack" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "DegreeTrack" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omDegTrk_fnLocalBuildQual_3( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "DegreeTrack" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "DegreeTrack" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omDegTrk_fnLocalBuildQual_0( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_5 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_5 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omDegTrk_fnLocalBuildQual_1( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_12 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "RequiredGroup" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "RequiredGroup" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_12 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DERIVED ATTRIBUTE OPERATION
//:dReptGroupOL_Value( VIEW mDegTrk BASED ON LOD mDegTrk,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   STRING ( 200 ) szOL_Value
public int 
omDegTrk_dReptGroupOL_Value( View     mDegTrk,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szOL_Value = null;
   //:STRING ( 10 )  szCredits
   String   szCredits = null;
   //:STRING ( 10 )  szMinGrade
   String   szMinGrade = null;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mDegTrk.RequiredGroup.Type = ""
         if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "" ) == 0 )
         { 
            //:szOL_Value = "Type " + mDegTrk.RequiredGroup.Name
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 51, mDegTrk, "RequiredGroup", "Name", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringCopy( sb_szOL_Value, 1, 0, "Type ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szTempString_0, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szOL_Value = mDegTrk.RequiredGroup.Name
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                         GetVariableFromAttribute( sb_szOL_Value, mi_lTempInteger_1, 'S', 201, mDegTrk, "RequiredGroup", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szOL_Value = sb_szOL_Value.toString( );}
            //:IF mDegTrk.RequiredGroup.Type = "2" OR mDegTrk.RequiredGroup.Type = "3"
            if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "2" ) == 0 || CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "3" ) == 0 )
            { 
               //:szCredits = mDegTrk.RequiredGroup.TotalRequiredCredits
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szCredits;
               if ( szCredits == null )
                  sb_szCredits = new StringBuilder( 32 );
               else
                  sb_szCredits = new StringBuilder( szCredits );
                               GetVariableFromAttribute( sb_szCredits, mi_lTempInteger_2, 'S', 11, mDegTrk, "RequiredGroup", "TotalRequiredCredits", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szCredits = sb_szCredits.toString( );}

               //:szOL_Value = szOL_Value + ", Req Credits: " + szCredits
                {StringBuilder sb_szOL_Value;
               if ( szOL_Value == null )
                  sb_szOL_Value = new StringBuilder( 32 );
               else
                  sb_szOL_Value = new StringBuilder( szOL_Value );
                              ZeidonStringConcat( sb_szOL_Value, 1, 0, ", Req Credits: ", 1, 0, 201 );
               szOL_Value = sb_szOL_Value.toString( );}
                {StringBuilder sb_szOL_Value;
               if ( szOL_Value == null )
                  sb_szOL_Value = new StringBuilder( 32 );
               else
                  sb_szOL_Value = new StringBuilder( szOL_Value );
                              ZeidonStringConcat( sb_szOL_Value, 1, 0, szCredits, 1, 0, 201 );
               szOL_Value = sb_szOL_Value.toString( );}
            } 

            //:END
            //:GetStringFromAttributeByContext( szMinGrade,
            //:                              mDegTrk, "RequiredGroup", "MinimumGrade", "", 10 )
            {StringBuilder sb_szMinGrade;
            if ( szMinGrade == null )
               sb_szMinGrade = new StringBuilder( 32 );
            else
               sb_szMinGrade = new StringBuilder( szMinGrade );
                         GetStringFromAttributeByContext( sb_szMinGrade, mDegTrk, "RequiredGroup", "MinimumGrade", "", 10 );
            szMinGrade = sb_szMinGrade.toString( );}
            //:szOL_Value = szOL_Value + ", Min Grade: " + szMinGrade
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, ", Min Grade: ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szMinGrade, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mDegTrk,
         //:                   InternalEntityStructure, InternalAttribStructure, szOL_Value )
         StoreStringInRecord( mDegTrk, InternalEntityStructure, InternalAttribStructure, szOL_Value );
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
//:dCourseOL_Value( VIEW mDegTrk BASED ON LOD mDegTrk,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   STRING ( 200 ) szOL_Value
public int 
omDegTrk_dCourseOL_Value( View     mDegTrk,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   String   szOL_Value = null;
   //:STRING ( 100 ) szName
   String   szName = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mDegTrk );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "CourseType1or2"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "CourseType1or2", 1, 0, 33 ) == 0 )
         { 
            //:szName = mDegTrk.CourseType1or2.Title
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                         GetVariableFromAttribute( sb_szName, mi_lTempInteger_0, 'S', 101, mDegTrk, "CourseType1or2", "Title", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szName = sb_szName.toString( );}
            //:szOL_Value = mDegTrk.CourseType1or2.Number + " - " + szName
            {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                         GetStringFromAttribute( sb_szOL_Value, mDegTrk, "CourseType1or2", "Number" );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, " - ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szName, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szName = mDegTrk.CourseType3or4.Title
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                         GetVariableFromAttribute( sb_szName, mi_lTempInteger_1, 'S', 101, mDegTrk, "CourseType3or4", "Title", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szName = sb_szName.toString( );}
            //:szOL_Value = mDegTrk.CourseType3or4.Number + " - " + szName
            {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                         GetStringFromAttribute( sb_szOL_Value, mDegTrk, "CourseType3or4", "Number" );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, " - ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szName, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mDegTrk,
         //:                   InternalEntityStructure, InternalAttribStructure, szOL_Value )
         StoreStringInRecord( mDegTrk, InternalEntityStructure, InternalAttribStructure, szOL_Value );
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
//:dSubgroupOL_Value( VIEW mDegTrk BASED ON LOD mDegTrk,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   STRING ( 200 ) szOL_Value
public int 
omDegTrk_dSubgroupOL_Value( View     mDegTrk,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szOL_Value = null;
   //:STRING ( 10 )  szCredits
   String   szCredits = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :


         //:IF mDegTrk.RequiredGroup.Type = "4"
         if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "4" ) == 0 )
         { 
            //:IF mDegTrk.RequiredSubGroup.Name = ""
            if ( CompareAttributeToString( mDegTrk, "RequiredSubGroup", "Name", "" ) == 0 )
            { 
               //:szOL_Value = "OR Group"
                {StringBuilder sb_szOL_Value;
               if ( szOL_Value == null )
                  sb_szOL_Value = new StringBuilder( 32 );
               else
                  sb_szOL_Value = new StringBuilder( szOL_Value );
                              ZeidonStringCopy( sb_szOL_Value, 1, 0, "OR Group", 1, 0, 201 );
               szOL_Value = sb_szOL_Value.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szOL_Value = mDegTrk.RequiredSubGroup.Name
               {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
               StringBuilder sb_szOL_Value;
               if ( szOL_Value == null )
                  sb_szOL_Value = new StringBuilder( 32 );
               else
                  sb_szOL_Value = new StringBuilder( szOL_Value );
                               GetVariableFromAttribute( sb_szOL_Value, mi_lTempInteger_0, 'S', 201, mDegTrk, "RequiredSubGroup", "Name", "", 0 );
               lTempInteger_0 = mi_lTempInteger_0.intValue( );
               szOL_Value = sb_szOL_Value.toString( );}
            } 

            //:END
            //:szCredits = mDegTrk.RequiredSubGroup.TotalRequiredCredits
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szCredits;
            if ( szCredits == null )
               sb_szCredits = new StringBuilder( 32 );
            else
               sb_szCredits = new StringBuilder( szCredits );
                         GetVariableFromAttribute( sb_szCredits, mi_lTempInteger_1, 'S', 11, mDegTrk, "RequiredSubGroup", "TotalRequiredCredits", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szCredits = sb_szCredits.toString( );}
            //:szOL_Value = szOL_Value + ", Req'd Credits: " + szCredits
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, ", Req'd Credits: ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szCredits, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mDegTrk.RequiredSubGroup.Name = ""
            if ( CompareAttributeToString( mDegTrk, "RequiredSubGroup", "Name", "" ) == 0 )
            { 
               //:szOL_Value = "MIN Group"
                {StringBuilder sb_szOL_Value;
               if ( szOL_Value == null )
                  sb_szOL_Value = new StringBuilder( 32 );
               else
                  sb_szOL_Value = new StringBuilder( szOL_Value );
                              ZeidonStringCopy( sb_szOL_Value, 1, 0, "MIN Group", 1, 0, 201 );
               szOL_Value = sb_szOL_Value.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szOL_Value = mDegTrk.RequiredSubGroup.Name
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szOL_Value;
               if ( szOL_Value == null )
                  sb_szOL_Value = new StringBuilder( 32 );
               else
                  sb_szOL_Value = new StringBuilder( szOL_Value );
                               GetVariableFromAttribute( sb_szOL_Value, mi_lTempInteger_2, 'S', 201, mDegTrk, "RequiredSubGroup", "Name", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szOL_Value = sb_szOL_Value.toString( );}
            } 

            //:END
            //:szCredits = mDegTrk.RequiredSubGroup.TotalRequiredCredits
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szCredits;
            if ( szCredits == null )
               sb_szCredits = new StringBuilder( 32 );
            else
               sb_szCredits = new StringBuilder( szCredits );
                         GetVariableFromAttribute( sb_szCredits, mi_lTempInteger_3, 'S', 11, mDegTrk, "RequiredSubGroup", "TotalRequiredCredits", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szCredits = sb_szCredits.toString( );}
            //:szOL_Value = szOL_Value + ", Min Credits: " + szCredits
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, ", Min Credits: ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szCredits, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mDegTrk,
         //:                   InternalEntityStructure, InternalAttribStructure, szOL_Value )
         StoreStringInRecord( mDegTrk, InternalEntityStructure, InternalAttribStructure, szOL_Value );
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


//:LOCAL OPERATION
//:CreateElectiveEntry( VIEW mDegTrk  BASED ON LOD mDegTrk,
//:                     VIEW mStudenC BASED ON LOD mStudenC,
//:                     VIEW mStudent BASED ON LOD mStudent )

//:   INTEGER        CourseID
private int 
omDegTrk_CreateElectiveEntry( View     mDegTrk,
                              View     mStudenC,
                              View     mStudent )
{
   int      CourseID = 0;
   //:STRING ( 10 )  CourseNumber
   String   CourseNumber = null;
   //:STRING ( 100 ) CourseTitle
   String   CourseTitle = null;
   //:STRING ( 20 )  StudentStatus
   String   StudentStatus = null;
   //:STRING ( 20 )  Semester
   String   Semester = null;
   //:STRING ( 8 )   ClassEndDate
   String   ClassEndDate = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;


   //:ReturnCourseVariables( mStudenC, CourseID, CourseNumber, CourseTitle, Semester, ClassEndDate )
   {StringBuilder sb_ClassEndDate;
   if ( ClassEndDate == null )
      sb_ClassEndDate = new StringBuilder( 32 );
   else
      sb_ClassEndDate = new StringBuilder( ClassEndDate );
   StringBuilder sb_Semester;
   if ( Semester == null )
      sb_Semester = new StringBuilder( 32 );
   else
      sb_Semester = new StringBuilder( Semester );
   StringBuilder sb_CourseTitle;
   if ( CourseTitle == null )
      sb_CourseTitle = new StringBuilder( 32 );
   else
      sb_CourseTitle = new StringBuilder( CourseTitle );
   StringBuilder sb_CourseNumber;
   if ( CourseNumber == null )
      sb_CourseNumber = new StringBuilder( 32 );
   else
      sb_CourseNumber = new StringBuilder( CourseNumber );
   MutableInt mi_CourseID = new MutableInt( CourseID );
       omDegTrk_ReturnCourseVariables( mStudenC, mi_CourseID, sb_CourseNumber, sb_CourseTitle, sb_Semester, sb_ClassEndDate );
   ClassEndDate = sb_ClassEndDate.toString( );
   Semester = sb_Semester.toString( );
   CourseTitle = sb_CourseTitle.toString( );
   CourseNumber = sb_CourseNumber.toString( );
   CourseID = mi_CourseID.intValue( );}

   //:GetStringFromAttributeByContext( StudentStatus, mStudenC, "Registration", "Status", "", 20 )
   {StringBuilder sb_StudentStatus;
   if ( StudentStatus == null )
      sb_StudentStatus = new StringBuilder( 32 );
   else
      sb_StudentStatus = new StringBuilder( StudentStatus );
       GetStringFromAttributeByContext( sb_StudentStatus, mStudenC, "Registration", "Status", "", 20 );
   StudentStatus = sb_StudentStatus.toString( );}
   //:SET CURSOR FIRST mDegTrk.RequiredGroup WITHIN mDegTrk.DegreeTrack 
   //:           WHERE mDegTrk.RequiredGroup.Type = "E"
   RESULT = mDegTrk.cursor( "RequiredGroup" ).setFirst( "Type", "E", "DegreeTrack" ).toInt();
   //:CREATE ENTITY mDegTrk.RequiredGroupCourseType1or2  
   RESULT = CreateEntity( mDegTrk, "RequiredGroupCourseType1or2", zPOS_AFTER );
   //:CREATE ENTITY mDegTrk.CourseType1or2 
   RESULT = CreateEntity( mDegTrk, "CourseType1or2", zPOS_AFTER );
   //:mDegTrk.CourseType1or2.ID                        = CourseID
   SetAttributeFromInteger( mDegTrk, "CourseType1or2", "ID", CourseID );
   //:mDegTrk.CourseType1or2.Number                    = CourseNumber 
   SetAttributeFromString( mDegTrk, "CourseType1or2", "Number", CourseNumber );
   //:mDegTrk.CourseType1or2.CreditHours               = mStudenC.Registration.CreditHours 
   SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "CreditHours", mStudenC, "Registration", "CreditHours" );
   //:mDegTrk.CourseType1or2.wFinalGrade               = mStudenC.Registration.FinalGrade
   SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wFinalGrade", mStudenC, "Registration", "FinalGrade" );
   //:mDegTrk.CourseType1or2.wCreditsTaken             = mStudenC.Registration.CreditHours
   SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wCreditsTaken", mStudenC, "Registration", "CreditHours" );
   //:mDegTrk.CourseType1or2.wRegistrationSatisfyingID = mStudenC.Registration.ID
   SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wRegistrationSatisfyingID", mStudenC, "Registration", "ID" );
   //:mDegTrk.CourseType1or2.wCourseSatisfyingID       = CourseID
   SetAttributeFromInteger( mDegTrk, "CourseType1or2", "wCourseSatisfyingID", CourseID );
   //:IF mStudenC.CourseTopic EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "CourseTopic" );
   if ( lTempInteger_0 == 0 )
   { 
      //:mDegTrk.CourseType1or2.Title = mStudenC.CourseTopic.Title 
      SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "Title", mStudenC, "CourseTopic", "Title" );
      //:ELSE
   } 
   else
   { 
      //:mDegTrk.CourseType1or2.Title = CourseTitle 
      SetAttributeFromString( mDegTrk, "CourseType1or2", "Title", CourseTitle );
   } 

   //:END

   //:// Flag the course as having been taken for credit.
   //:// If a graduation date has been set for the Student, then we will only flag those courses
   //:// that will be taken prior to the graduation date and we will never flag "Pre-Transfer" courses.
   //:IF mStudenC.Registration.Status != "P"
   if ( CompareAttributeToString( mStudenC, "Registration", "Status", "P" ) != 0 )
   { 
      //:IF mStudent.DC_GraduationDate EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( mStudent, "DC_GraduationDate" );
      if ( lTempInteger_1 == 0 )
      { 
         //:IF mStudent.DC_GraduationDate.Date >= ClassEndDate
         if ( CompareAttributeToString( mStudent, "DC_GraduationDate", "Date", ClassEndDate ) >= 0 )
         { 
            //:mDegTrk.CourseType1or2.wDegreeAuditCourseTaken = "Y"
            SetAttributeFromString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseTaken", "Y" );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:mDegTrk.CourseType1or2.wDegreeAuditCourseTaken = "Y"
         SetAttributeFromString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseTaken", "Y" );
      } 

      //:END
      //:IF mStudenC.Registration.Status = "C" OR
      //:   mStudenC.Registration.Status = "F" OR
      //:   mStudenC.Registration.Status = "X"
      if ( CompareAttributeToString( mStudenC, "Registration", "Status", "C" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "F" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "X" ) == 0 )
      { 

         //:mDegTrk.CourseType1or2.wDegreeAuditCourseCompleted = "Y"
         SetAttributeFromString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseCompleted", "Y" );
      } 

      //:END
   } 

   //:END

   //:// Set course status from Registration.Status.
   //:// If this entry is Enrolled or Pre-Transfer, we will also display the Semester.
   //:IF mStudenC.Registration.Status = "T" OR
   //:   mStudenC.Registration.Status = "P"
   if ( CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "P" ) == 0 )
   { 

      //:mDegTrk.CourseType1or2.wCourseStatus = StudentStatus + " " + Semester
       {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
            ZeidonStringCopy( sb_szTempString_0, 1, 0, StudentStatus, 1, 0, 255 );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
            ZeidonStringConcat( sb_szTempString_0, 1, 0, " ", 1, 0, 255 );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
            ZeidonStringConcat( sb_szTempString_0, 1, 0, Semester, 1, 0, 255 );
      szTempString_0 = sb_szTempString_0.toString( );}
      SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", szTempString_0 );
      //:ELSE
   } 
   else
   { 
      //:mDegTrk.CourseType1or2.wCourseStatus = StudentStatus 
      SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", StudentStatus );
   } 

   //:END
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:CreateDevelopmentEntry( VIEW mDegTrk  BASED ON LOD mDegTrk,
//:                        VIEW mStudenC BASED ON LOD mStudenC,
//:                        VIEW mStudent BASED ON LOD mStudent )

//:   STRING ( 10 )  CourseNumber
private int 
omDegTrk_CreateDevelopmentEntry( View     mDegTrk,
                                 View     mStudenC,
                                 View     mStudent )
{
   String   CourseNumber = null;
   //:STRING ( 100 ) CourseTitle
   String   CourseTitle = null;
   //:STRING ( 20 )  StudentStatus
   String   StudentStatus = null;
   //:STRING ( 20 )  Semester
   String   Semester = null;
   //:STRING ( 8 )   ClassEndDate
   String   ClassEndDate = null;
   //:STRING ( 20 )  szFinalGradeString
   String   szFinalGradeString = null;
   //:DECIMAL        FinalGrade
   double  FinalGrade = 0.0;
   //:INTEGER        MinimumElectiveGrade
   int      MinimumElectiveGrade = 0;
   //:INTEGER        CourseID
   int      CourseID = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:MinimumElectiveGrade = 1       // Minimum grade is D, which has quality points of 1.
   MinimumElectiveGrade = 1;
   //:ReturnCourseVariables( mStudenC, CourseID, CourseNumber, CourseTitle, Semester, ClassEndDate )
   {StringBuilder sb_ClassEndDate;
   if ( ClassEndDate == null )
      sb_ClassEndDate = new StringBuilder( 32 );
   else
      sb_ClassEndDate = new StringBuilder( ClassEndDate );
   StringBuilder sb_Semester;
   if ( Semester == null )
      sb_Semester = new StringBuilder( 32 );
   else
      sb_Semester = new StringBuilder( Semester );
   StringBuilder sb_CourseTitle;
   if ( CourseTitle == null )
      sb_CourseTitle = new StringBuilder( 32 );
   else
      sb_CourseTitle = new StringBuilder( CourseTitle );
   StringBuilder sb_CourseNumber;
   if ( CourseNumber == null )
      sb_CourseNumber = new StringBuilder( 32 );
   else
      sb_CourseNumber = new StringBuilder( CourseNumber );
   MutableInt mi_CourseID = new MutableInt( CourseID );
       omDegTrk_ReturnCourseVariables( mStudenC, mi_CourseID, sb_CourseNumber, sb_CourseTitle, sb_Semester, sb_ClassEndDate );
   ClassEndDate = sb_ClassEndDate.toString( );
   Semester = sb_Semester.toString( );
   CourseTitle = sb_CourseTitle.toString( );
   CourseNumber = sb_CourseNumber.toString( );
   CourseID = mi_CourseID.intValue( );}
   //:GetStringFromAttributeByContext( StudentStatus, mStudenC, "Registration", "Status", "", 20 )
   {StringBuilder sb_StudentStatus;
   if ( StudentStatus == null )
      sb_StudentStatus = new StringBuilder( 32 );
   else
      sb_StudentStatus = new StringBuilder( StudentStatus );
       GetStringFromAttributeByContext( sb_StudentStatus, mStudenC, "Registration", "Status", "", 20 );
   StudentStatus = sb_StudentStatus.toString( );}
   //:SET CURSOR FIRST mDegTrk.RequiredGroup WITHIN mDegTrk.DegreeTrack 
   //:           WHERE mDegTrk.RequiredGroup.Type = "D" 
   //:             AND mDegTrk.RequiredGroup.Name = "Developmental"
   RESULT = mDegTrk.cursor( "RequiredGroup" ).setFirst( "DegreeTrack" ).toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "D" ) != 0 || CompareAttributeToString( mDegTrk, "RequiredGroup", "Name", "Developmental" ) != 0 ) )
      { 
         RESULT = mDegTrk.cursor( "RequiredGroup" ).setNextContinue().toInt();;
      } 

   } 

   //:CREATE ENTITY mDegTrk.RequiredGroupCourseType1or2 
   RESULT = CreateEntity( mDegTrk, "RequiredGroupCourseType1or2", zPOS_AFTER );
   //:CREATE ENTITY mDegTrk.CourseType1or2
   RESULT = CreateEntity( mDegTrk, "CourseType1or2", zPOS_AFTER );
   //:mDegTrk.CourseType1or2.ID                        = CourseID
   SetAttributeFromInteger( mDegTrk, "CourseType1or2", "ID", CourseID );
   //:mDegTrk.CourseType1or2.Number                    = CourseNumber 
   SetAttributeFromString( mDegTrk, "CourseType1or2", "Number", CourseNumber );
   //:mDegTrk.CourseType1or2.Title                     = CourseTitle
   SetAttributeFromString( mDegTrk, "CourseType1or2", "Title", CourseTitle );
   //:mDegTrk.CourseType1or2.wFinalGrade               = mStudenC.Registration.FinalGrade
   SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wFinalGrade", mStudenC, "Registration", "FinalGrade" );
   //:mDegTrk.CourseType1or2.wRegistrationSatisfyingID = mStudenC.Registration.ID
   SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wRegistrationSatisfyingID", mStudenC, "Registration", "ID" );
   //:mDegTrk.CourseType1or2.wDevelopmentalFlag        = "Y"
   SetAttributeFromString( mDegTrk, "CourseType1or2", "wDevelopmentalFlag", "Y" );
   //:GetStringFromAttributeByContext( szFinalGradeString,
   //:                              mStudenC, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 )
   {StringBuilder sb_szFinalGradeString;
   if ( szFinalGradeString == null )
      sb_szFinalGradeString = new StringBuilder( 32 );
   else
      sb_szFinalGradeString = new StringBuilder( szFinalGradeString );
       GetStringFromAttributeByContext( sb_szFinalGradeString, mStudenC, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 );
   szFinalGradeString = sb_szFinalGradeString.toString( );}
   //:FinalGrade = StrToDecimal( szFinalGradeString )   // FinalGrade is in "Quality Points".
   {
    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mDegTrk );
    FinalGrade = m_ZGLOBAL1_Operation.StrToDecimal( szFinalGradeString );
    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
   }
   //:szFinalGradeString = mStudenC.Registration.FinalGrade     // Get grade again as normal string.
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szFinalGradeString;
   if ( szFinalGradeString == null )
      sb_szFinalGradeString = new StringBuilder( 32 );
   else
      sb_szFinalGradeString = new StringBuilder( szFinalGradeString );
       GetVariableFromAttribute( sb_szFinalGradeString, mi_lTempInteger_0, 'S', 21, mStudenC, "Registration", "FinalGrade", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szFinalGradeString = sb_szFinalGradeString.toString( );}
   //:IF FinalGrade >= MinimumElectiveGrade OR szFinalGradeString = "S" OR szFinalGradeString = ""
   if ( FinalGrade >= MinimumElectiveGrade || ZeidonStringCompare( szFinalGradeString, 1, 0, "S", 1, 0, 21 ) == 0 || ZeidonStringCompare( szFinalGradeString, 1, 0, "", 1, 0, 21 ) == 0 )
   { 
      //:mDegTrk.CourseType1or2.wCourseStatus = StudentStatus
      SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", StudentStatus );
      //:mDegTrk.CourseType1or2.wCreditsTaken = mStudenC.Registration.CreditHours
      SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wCreditsTaken", mStudenC, "Registration", "CreditHours" );
      //:ELSE
   } 
   else
   { 
      //:// The Student must have failed the Course or got an incomplete.
      //:IF mStudenC.Registration.FinalGrade = "F" OR mStudenC.Registration.FinalGrade = "U"
      if ( CompareAttributeToString( mStudenC, "Registration", "FinalGrade", "F" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "FinalGrade", "U" ) == 0 )
      { 
         //:mDegTrk.CourseType1or2.wCourseStatus = "Failed"
         SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", "Failed" );
         //:ELSE
      } 
      else
      { 
         //:mDegTrk.CourseType1or2.wCourseStatus = "Incomplete"
         SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", "Incomplete" );
      } 

      //:END
      //:mDegTrk.CourseType1or2.wCreditsTaken = 0
      SetAttributeFromInteger( mDegTrk, "CourseType1or2", "wCreditsTaken", 0 );
   } 

   //:END
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:CourseType1or2Match( VIEW mDegTrk  BASED ON LOD mDegTrk,
//:                     VIEW mStudenC BASED ON LOD mStudenC,
//:                     VIEW mStudent BASED ON LOD mStudent )
//:   
//:   INTEGER       MinimumElectiveGrade
private int 
omDegTrk_CourseType1or2Match( View     mDegTrk,
                              View     mStudenC,
                              View     mStudent )
{
   int      MinimumElectiveGrade = 0;
   //:INTEGER       CourseID
   int      CourseID = 0;
   //:INTEGER       SubstituteDegreeTrackID
   int      SubstituteDegreeTrackID = 0;
   //:DECIMAL       FinalGrade
   double  FinalGrade = 0.0;
   //:DECIMAL       MinimumGrade
   double  MinimumGrade = 0.0;
   //:STRING ( 20 ) szFinalGradeString
   String   szFinalGradeString = null;
   //:STRING ( 20 ) szMinimumGradeString
   String   szMinimumGradeString = null;
   //:STRING ( 20 ) StudentStatus
   String   StudentStatus = null;
   //:STRING ( 20 ) CourseNumber
   String   CourseNumber = null;
   //:STRING ( 100 ) CourseTitle
   String   CourseTitle = null;
   //:STRING ( 20 ) Semester
   String   Semester = null;
   //:STRING ( 8 )  ClassEndDate
   String   ClassEndDate = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;


   //:// There was a match on CourseType1or2, so see if the grade is adequate.
   //:// Minimum grade is from Degree Track requirements.
   //:// Note that if the Student is currently taking the Course, (Registration.Status = "T") or
   //:// is pre-transferred for the Course, (Registration.Status = "P") then
   //:// we'll assume the Student will get an adequate grade in the Class.

   //:MinimumElectiveGrade = 1
   MinimumElectiveGrade = 1;

   //:// The SubstituteDegreeTrackID variable identifies the DegreeTrack of a substitute entry, used to determine
   //:// if a substitute is for this Track or another Track.
   //:SubstituteDegreeTrackID = 0
   SubstituteDegreeTrackID = 0;
   //:IF mStudenC.DAS_Course EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "DAS_Course" );
   if ( lTempInteger_0 == 0 )
   { 
      //:SubstituteDegreeTrackID = mStudenC.DAS_DegreeTrack.ID 
      {MutableInt mi_SubstituteDegreeTrackID = new MutableInt( SubstituteDegreeTrackID );
             GetIntegerFromAttribute( mi_SubstituteDegreeTrackID, mStudenC, "DAS_DegreeTrack", "ID" );
      SubstituteDegreeTrackID = mi_SubstituteDegreeTrackID.intValue( );}
   } 

   //:END

   //:// Final Grade
   //:GetStringFromAttributeByContext( szFinalGradeString,
   //:                                 mStudenC, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 )
   {StringBuilder sb_szFinalGradeString;
   if ( szFinalGradeString == null )
      sb_szFinalGradeString = new StringBuilder( 32 );
   else
      sb_szFinalGradeString = new StringBuilder( szFinalGradeString );
       GetStringFromAttributeByContext( sb_szFinalGradeString, mStudenC, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 );
   szFinalGradeString = sb_szFinalGradeString.toString( );}
   //:FinalGrade = StrToDecimal( szFinalGradeString )   // FinalGrade is in "Quality Points".
   {
    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mDegTrk );
    FinalGrade = m_ZGLOBAL1_Operation.StrToDecimal( szFinalGradeString );
    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
   }
   //:szFinalGradeString = mStudenC.Registration.FinalGrade     // Get grade again as normal string.
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szFinalGradeString;
   if ( szFinalGradeString == null )
      sb_szFinalGradeString = new StringBuilder( 32 );
   else
      sb_szFinalGradeString = new StringBuilder( szFinalGradeString );
       GetVariableFromAttribute( sb_szFinalGradeString, mi_lTempInteger_1, 'S', 21, mStudenC, "Registration", "FinalGrade", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szFinalGradeString = sb_szFinalGradeString.toString( );}

   //:szMinimumGradeString = mDegTrk.RequiredGroup.MinimumGrade
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szMinimumGradeString;
   if ( szMinimumGradeString == null )
      sb_szMinimumGradeString = new StringBuilder( 32 );
   else
      sb_szMinimumGradeString = new StringBuilder( szMinimumGradeString );
       GetVariableFromAttribute( sb_szMinimumGradeString, mi_lTempInteger_2, 'S', 21, mDegTrk, "RequiredGroup", "MinimumGrade", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szMinimumGradeString = sb_szMinimumGradeString.toString( );}
   //:IF szMinimumGradeString = "S"
   if ( ZeidonStringCompare( szMinimumGradeString, 1, 0, "S", 1, 0, 21 ) == 0 )
   { 
      //:MinimumGrade = 1
      MinimumGrade = 1;
      //:ELSE
   } 
   else
   { 
      //:GetStringFromAttributeByContext( szMinimumGradeString,
      //:                                 mDegTrk, "RequiredGroup", "MinimumGrade", "DegreeTrackGradePointValue", 20 )
      {StringBuilder sb_szMinimumGradeString;
      if ( szMinimumGradeString == null )
         sb_szMinimumGradeString = new StringBuilder( 32 );
      else
         sb_szMinimumGradeString = new StringBuilder( szMinimumGradeString );
             GetStringFromAttributeByContext( sb_szMinimumGradeString, mDegTrk, "RequiredGroup", "MinimumGrade", "DegreeTrackGradePointValue", 20 );
      szMinimumGradeString = sb_szMinimumGradeString.toString( );}
      //:MinimumGrade = StrToDecimal( szMinimumGradeString )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mDegTrk );
       MinimumGrade = m_ZGLOBAL1_Operation.StrToDecimal( szMinimumGradeString );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
   } 

   //:END
   //:ReturnCourseVariables( mStudenC, CourseID, CourseNumber, CourseTitle, Semester, ClassEndDate )
   {StringBuilder sb_ClassEndDate;
   if ( ClassEndDate == null )
      sb_ClassEndDate = new StringBuilder( 32 );
   else
      sb_ClassEndDate = new StringBuilder( ClassEndDate );
   StringBuilder sb_Semester;
   if ( Semester == null )
      sb_Semester = new StringBuilder( 32 );
   else
      sb_Semester = new StringBuilder( Semester );
   StringBuilder sb_CourseTitle;
   if ( CourseTitle == null )
      sb_CourseTitle = new StringBuilder( 32 );
   else
      sb_CourseTitle = new StringBuilder( CourseTitle );
   StringBuilder sb_CourseNumber;
   if ( CourseNumber == null )
      sb_CourseNumber = new StringBuilder( 32 );
   else
      sb_CourseNumber = new StringBuilder( CourseNumber );
   MutableInt mi_CourseID = new MutableInt( CourseID );
       omDegTrk_ReturnCourseVariables( mStudenC, mi_CourseID, sb_CourseNumber, sb_CourseTitle, sb_Semester, sb_ClassEndDate );
   ClassEndDate = sb_ClassEndDate.toString( );
   Semester = sb_Semester.toString( );
   CourseTitle = sb_CourseTitle.toString( );
   CourseNumber = sb_CourseNumber.toString( );
   CourseID = mi_CourseID.intValue( );}
   //:mDegTrk.CourseType1or2.wCourseSatisfyingID = CourseID
   SetAttributeFromInteger( mDegTrk, "CourseType1or2", "wCourseSatisfyingID", CourseID );

   //:GetStringFromAttributeByContext( StudentStatus, mStudenC, "Registration", "Status", "", 20 )
   {StringBuilder sb_StudentStatus;
   if ( StudentStatus == null )
      sb_StudentStatus = new StringBuilder( 32 );
   else
      sb_StudentStatus = new StringBuilder( StudentStatus );
       GetStringFromAttributeByContext( sb_StudentStatus, mStudenC, "Registration", "Status", "", 20 );
   StudentStatus = sb_StudentStatus.toString( );}

   //:// Check for the need of a duplicate Course entry for repeatable Courses and create as necessary.
   //:CheckForRepeatable( mDegTrk, mStudenC, "1" )
   omDegTrk_CheckForRepeatable( mDegTrk, mStudenC, "1" );
   //:IF FinalGrade >= MinimumGrade OR szFinalGradeString = "S" OR szFinalGradeString = ""
   if ( FinalGrade >= MinimumGrade || ZeidonStringCompare( szFinalGradeString, 1, 0, "S", 1, 0, 21 ) == 0 || ZeidonStringCompare( szFinalGradeString, 1, 0, "", 1, 0, 21 ) == 0 )
   { 
      //:// Final Grade is OK or is still null, so we flag the course as having been taken for credit.
      //:// If a graduation date has been set for the Student, then we will only flag those courses
      //:// that will be taken prior to the graduation date and we will never flag "Pre-Transfer" courses.
      //:IF mStudenC.Registration.Status != "P"
      if ( CompareAttributeToString( mStudenC, "Registration", "Status", "P" ) != 0 )
      { 
         //:IF mStudent.DC_GraduationDate EXISTS
         lTempInteger_3 = CheckExistenceOfEntity( mStudent, "DC_GraduationDate" );
         if ( lTempInteger_3 == 0 )
         { 
            //:IF mStudent.DC_GraduationDate.Date >= ClassEndDate OR szFinalGradeString != ""
            if ( CompareAttributeToString( mStudent, "DC_GraduationDate", "Date", ClassEndDate ) >= 0 || ZeidonStringCompare( szFinalGradeString, 1, 0, "", 1, 0, 21 ) != 0 )
            { 
               //:mDegTrk.CourseType1or2.wDegreeAuditCourseTaken = "Y"
               SetAttributeFromString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseTaken", "Y" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:mDegTrk.CourseType1or2.wDegreeAuditCourseTaken = "Y"
            SetAttributeFromString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseTaken", "Y" );
         } 

         //:END
         //:IF mStudenC.Registration.Status = "C" OR
         //:   mStudenC.Registration.Status = "F" OR
         //:   mStudenC.Registration.Status = "X" OR
         //:   mStudenC.Registration.Status = "V" OR
         //:   mStudenC.Registration.Status = "Y"
         if ( CompareAttributeToString( mStudenC, "Registration", "Status", "C" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "F" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "X" ) == 0 ||
              CompareAttributeToString( mStudenC, "Registration", "Status", "V" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "Y" ) == 0 )
         { 

            //:mDegTrk.CourseType1or2.wDegreeAuditCourseCompleted = "Y"
            SetAttributeFromString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseCompleted", "Y" );
         } 

         //:END
      } 

      //:END
      //:IF SubstituteDegreeTrackID = mDegTrk.DegreeTrack.ID 
      if ( CompareAttributeToInteger( mDegTrk, "DegreeTrack", "ID", SubstituteDegreeTrackID ) == 0 )
      { 
         //:// Course is override equivalent, so indicate in wCourseStatus.
         //:mDegTrk.CourseType1or2.wCourseStatus = CourseNumber + " is Approved Substitute"
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringCopy( sb_szTempString_0, 1, 0, CourseNumber, 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, " is Approved Substitute", 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
         SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", szTempString_0 );
         //:ELSE
      } 
      else
      { 
         //:// Regular Course/Group match, so wCourseStatus is from Registration.Status.
         //:// If this entry is Enrolled or Pre-Transfer, we will also display the Semester.
         //:IF mStudenC.Registration.Status = "T" OR
         //:   mStudenC.Registration.Status = "P"
         if ( CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "P" ) == 0 )
         { 

            //:mDegTrk.CourseType1or2.wCourseStatus = StudentStatus + " " + Semester
             {StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                        ZeidonStringCopy( sb_szTempString_1, 1, 0, StudentStatus, 1, 0, 255 );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                        ZeidonStringConcat( sb_szTempString_1, 1, 0, " ", 1, 0, 255 );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                        ZeidonStringConcat( sb_szTempString_1, 1, 0, Semester, 1, 0, 255 );
            szTempString_1 = sb_szTempString_1.toString( );}
            SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", szTempString_1 );
            //:ELSE
         } 
         else
         { 
            //:mDegTrk.CourseType1or2.wCourseStatus = StudentStatus
            SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", StudentStatus );
         } 

         //:END
      } 

      //:END
      //:IF szFinalGradeString = ""
      if ( ZeidonStringCompare( szFinalGradeString, 1, 0, "", 1, 0, 21 ) == 0 )
      { 
         //:mDegTrk.CourseType1or2.wFinalGrade   = ""
         SetAttributeFromString( mDegTrk, "CourseType1or2", "wFinalGrade", "" );
         //:ELSE
      } 
      else
      { 
         //:mDegTrk.CourseType1or2.wFinalGrade   = mStudenC.Registration.FinalGrade
         SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wFinalGrade", mStudenC, "Registration", "FinalGrade" );
      } 

      //:END
      //:mDegTrk.CourseType1or2.wCreditsTaken = mStudenC.Registration.CreditHours
      SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wCreditsTaken", mStudenC, "Registration", "CreditHours" );
      //:mDegTrk.CourseType1or2.wRegistrationSatisfyingID = mStudenC.Registration.ID
      SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wRegistrationSatisfyingID", mStudenC, "Registration", "ID" );
      //:IF mStudenC.Registration.Status = "T"
      if ( CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) == 0 )
      { 
         //:mDegTrk.RequiredGroup.wDegreeAuditHasTakingFlag = "Y"
         SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditHasTakingFlag", "Y" );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// Final Grade is not OK and the Course has NOT been retaken.
      //:// If the Final Grade satisfies Elective, create as "Completed, not for major" entry.
      //:// If the Final Grade does NOT satisfy Elective, create an entry for 0 credits.
      //:IF FinalGrade >= MinimumElectiveGrade
      if ( FinalGrade >= MinimumElectiveGrade )
      { 
         //:// "Completed, not for major" entry.
         //:IF mStudenC.DAS_Course EXISTS
         lTempInteger_4 = CheckExistenceOfEntity( mStudenC, "DAS_Course" );
         if ( lTempInteger_4 == 0 )
         { 
            //:mDegTrk.CourseType1or2.wCourseStatus = mStudenC.Registration.dClassNumber + " Completed, not for major"
            {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetStringFromAttribute( sb_szTempString_2, mStudenC, "Registration", "dClassNumber" );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringConcat( sb_szTempString_2, 1, 0, " Completed, not for major", 1, 0, 255 );
            szTempString_2 = sb_szTempString_2.toString( );}
            SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", szTempString_2 );
            //:ELSE
         } 
         else
         { 
            //:mDegTrk.CourseType1or2.wCourseStatus = "Completed, not for major"
            SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", "Completed, not for major" );
         } 

         //:END
         //:mDegTrk.CourseType1or2.wFinalGrade   = mStudenC.Registration.FinalGrade
         SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wFinalGrade", mStudenC, "Registration", "FinalGrade" );
         //:mDegTrk.CourseType1or2.wCreditsTaken = mStudenC.Registration.CreditHours
         SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wCreditsTaken", mStudenC, "Registration", "CreditHours" );
         //:mDegTrk.CourseType1or2.wRegistrationSatisfyingID = mStudenC.Registration.ID
         SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wRegistrationSatisfyingID", mStudenC, "Registration", "ID" );
         //:ELSE
      } 
      else
      { 
         //:// Create 0 Credits entry.
         //:// The Student must have failed the Course or got an incomplete.
         //:IF mStudenC.Registration.FinalGrade = "F" OR mStudenC.Registration.FinalGrade = "U"
         if ( CompareAttributeToString( mStudenC, "Registration", "FinalGrade", "F" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "FinalGrade", "U" ) == 0 )
         { 
            //:mDegTrk.CourseType1or2.wCourseStatus = "Failed"
            SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", "Failed" );
            //:ELSE
         } 
         else
         { 
            //:mDegTrk.CourseType1or2.wCourseStatus = "Incomplete"
            SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", "Incomplete" );
         } 

         //:END
         //:mDegTrk.CourseType1or2.wFinalGrade   = mStudenC.Registration.FinalGrade
         SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wFinalGrade", mStudenC, "Registration", "FinalGrade" );
         //:mDegTrk.CourseType1or2.wCreditsTaken = 0
         SetAttributeFromInteger( mDegTrk, "CourseType1or2", "wCreditsTaken", 0 );
         //:mDegTrk.CourseType1or2.wRegistrationSatisfyingID = mStudenC.Registration.ID
         SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wRegistrationSatisfyingID", mStudenC, "Registration", "ID" );
      } 

      //:END
   } 

   //:END

   //:// Set Developmental Flag.
   //:IF mStudenC.RegistrationCourse EXISTS
   lTempInteger_5 = CheckExistenceOfEntity( mStudenC, "RegistrationCourse" );
   if ( lTempInteger_5 == 0 )
   { 
      //:mDegTrk.CourseType1or2.wDevelopmentalFlag = mStudenC.RegistrationCourse.DevelopmentalFlag 
      SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wDevelopmentalFlag", mStudenC, "RegistrationCourse", "DevelopmentalFlag" );
   } 

   //:END

   //:// For a Course Topic, change the Title to be that of the topic.
   //:// We will not do this, however, when we are processing an override specification. This is because
   //:// the Registration entry we are on is for the override Course and not for the Course in the Degree Track.
   //:IF SubstituteDegreeTrackID = mDegTrk.DegreeTrack.ID
   if ( CompareAttributeToInteger( mDegTrk, "DegreeTrack", "ID", SubstituteDegreeTrackID ) == 0 )
   { 
      //:IF mStudenC.DAS_Course.ID != mDegTrk.CourseType1or2.ID 
      if ( CompareAttributeToAttribute( mStudenC, "DAS_Course", "ID", mDegTrk, "CourseType1or2", "ID" ) != 0 )
      { 
         //:IF mStudenC.CourseTopic EXISTS
         lTempInteger_6 = CheckExistenceOfEntity( mStudenC, "CourseTopic" );
         if ( lTempInteger_6 == 0 )
         { 
            //:mDegTrk.CourseType1or2.Title = mStudenC.CourseTopic.Title  
            SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "Title", mStudenC, "CourseTopic", "Title" );
         } 

         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:IF mStudenC.CourseTopic EXISTS
      lTempInteger_7 = CheckExistenceOfEntity( mStudenC, "CourseTopic" );
      if ( lTempInteger_7 == 0 )
      { 
         //:mDegTrk.CourseType1or2.Title = mStudenC.CourseTopic.Title  
         SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "Title", mStudenC, "CourseTopic", "Title" );
      } 

      //:END
   } 

   //:END
   return( 0 );
//    
// END
} 


//:LOCAL OPERATION
//:CourseType3or4Match( VIEW mDegTrk  BASED ON LOD mDegTrk,
//:                     VIEW mStudenC BASED ON LOD mStudenC,
//:                     VIEW mStudent BASED ON LOD mStudent )
//:   
//:   INTEGER       MinimumElectiveGrade
private int 
omDegTrk_CourseType3or4Match( View     mDegTrk,
                              View     mStudenC,
                              View     mStudent )
{
   int      MinimumElectiveGrade = 0;
   //:INTEGER       CourseID
   int      CourseID = 0;
   //:INTEGER       SubstituteDegreeTrackID
   int      SubstituteDegreeTrackID = 0;
   //:DECIMAL       FinalGrade
   double  FinalGrade = 0.0;
   //:DECIMAL       MinimumGrade
   double  MinimumGrade = 0.0;
   //:STRING ( 20 ) szFinalGradeString
   String   szFinalGradeString = null;
   //:STRING ( 20 ) szMinimumGradeString
   String   szMinimumGradeString = null;
   //:STRING ( 20 ) StudentStatus
   String   StudentStatus = null;
   //:STRING ( 20 ) CourseNumber
   String   CourseNumber = null;
   //:STRING ( 100 ) CourseTitle
   String   CourseTitle = null;
   //:STRING ( 20 ) Semester
   String   Semester = null;
   //:STRING ( 8 )  ClassEndDate
   String   ClassEndDate = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;


   //:// There was a match on CourseType3or4, so see if the grade is adequate.
   //:// Minimum grade is from Degree Track requirements.
   //:// Note that if the Student is currently taking the Course, (Registration.Status = "T") or
   //:// is pre-transferred for the Course, (Registration.Status = "P") then
   //:// we'll assume the Student will get an adequate grade in the Class.

   //:MinimumElectiveGrade = 1
   MinimumElectiveGrade = 1;
   //:GetStringFromAttributeByContext( StudentStatus, mStudenC, "Registration", "Status", "", 20 )
   {StringBuilder sb_StudentStatus;
   if ( StudentStatus == null )
      sb_StudentStatus = new StringBuilder( 32 );
   else
      sb_StudentStatus = new StringBuilder( StudentStatus );
       GetStringFromAttributeByContext( sb_StudentStatus, mStudenC, "Registration", "Status", "", 20 );
   StudentStatus = sb_StudentStatus.toString( );}

   //:// The SubstituteDegreeTrackID variable identifies the DegreeTrack of a substitute entry, used to determine
   //:// if a substitute is for this Track or another Track.
   //:SubstituteDegreeTrackID = 0
   SubstituteDegreeTrackID = 0;
   //:IF mStudenC.DAS_Course EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "DAS_Course" );
   if ( lTempInteger_0 == 0 )
   { 
      //:SubstituteDegreeTrackID = mStudenC.DAS_DegreeTrack.ID 
      {MutableInt mi_SubstituteDegreeTrackID = new MutableInt( SubstituteDegreeTrackID );
             GetIntegerFromAttribute( mi_SubstituteDegreeTrackID, mStudenC, "DAS_DegreeTrack", "ID" );
      SubstituteDegreeTrackID = mi_SubstituteDegreeTrackID.intValue( );}
   } 

   //:END

   //:// Final Grade
   //:GetStringFromAttributeByContext( szFinalGradeString,
   //:                                 mStudenC, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 )
   {StringBuilder sb_szFinalGradeString;
   if ( szFinalGradeString == null )
      sb_szFinalGradeString = new StringBuilder( 32 );
   else
      sb_szFinalGradeString = new StringBuilder( szFinalGradeString );
       GetStringFromAttributeByContext( sb_szFinalGradeString, mStudenC, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 );
   szFinalGradeString = sb_szFinalGradeString.toString( );}
   //:FinalGrade = StrToDecimal( szFinalGradeString )   // FinalGrade is in "Quality Points".
   {
    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mDegTrk );
    FinalGrade = m_ZGLOBAL1_Operation.StrToDecimal( szFinalGradeString );
    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
   }
   //:szFinalGradeString = mStudenC.Registration.FinalGrade     // Get grade again as normal string.
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szFinalGradeString;
   if ( szFinalGradeString == null )
      sb_szFinalGradeString = new StringBuilder( 32 );
   else
      sb_szFinalGradeString = new StringBuilder( szFinalGradeString );
       GetVariableFromAttribute( sb_szFinalGradeString, mi_lTempInteger_1, 'S', 21, mStudenC, "Registration", "FinalGrade", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szFinalGradeString = sb_szFinalGradeString.toString( );}

   //:szMinimumGradeString = mDegTrk.RequiredGroup.MinimumGrade
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szMinimumGradeString;
   if ( szMinimumGradeString == null )
      sb_szMinimumGradeString = new StringBuilder( 32 );
   else
      sb_szMinimumGradeString = new StringBuilder( szMinimumGradeString );
       GetVariableFromAttribute( sb_szMinimumGradeString, mi_lTempInteger_2, 'S', 21, mDegTrk, "RequiredGroup", "MinimumGrade", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szMinimumGradeString = sb_szMinimumGradeString.toString( );}
   //:IF szMinimumGradeString = "S"
   if ( ZeidonStringCompare( szMinimumGradeString, 1, 0, "S", 1, 0, 21 ) == 0 )
   { 
      //:MinimumGrade = 1
      MinimumGrade = 1;
      //:ELSE
   } 
   else
   { 
      //:GetStringFromAttributeByContext( szMinimumGradeString,
      //:                                 mDegTrk, "RequiredGroup", "MinimumGrade", "DegreeTrackGradePointValue", 20 )
      {StringBuilder sb_szMinimumGradeString;
      if ( szMinimumGradeString == null )
         sb_szMinimumGradeString = new StringBuilder( 32 );
      else
         sb_szMinimumGradeString = new StringBuilder( szMinimumGradeString );
             GetStringFromAttributeByContext( sb_szMinimumGradeString, mDegTrk, "RequiredGroup", "MinimumGrade", "DegreeTrackGradePointValue", 20 );
      szMinimumGradeString = sb_szMinimumGradeString.toString( );}
      //:MinimumGrade = StrToDecimal( szMinimumGradeString )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mDegTrk );
       MinimumGrade = m_ZGLOBAL1_Operation.StrToDecimal( szMinimumGradeString );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
   } 

   //:END
   //:ReturnCourseVariables( mStudenC, CourseID, CourseNumber, CourseTitle, Semester, ClassEndDate )
   {StringBuilder sb_ClassEndDate;
   if ( ClassEndDate == null )
      sb_ClassEndDate = new StringBuilder( 32 );
   else
      sb_ClassEndDate = new StringBuilder( ClassEndDate );
   StringBuilder sb_Semester;
   if ( Semester == null )
      sb_Semester = new StringBuilder( 32 );
   else
      sb_Semester = new StringBuilder( Semester );
   StringBuilder sb_CourseTitle;
   if ( CourseTitle == null )
      sb_CourseTitle = new StringBuilder( 32 );
   else
      sb_CourseTitle = new StringBuilder( CourseTitle );
   StringBuilder sb_CourseNumber;
   if ( CourseNumber == null )
      sb_CourseNumber = new StringBuilder( 32 );
   else
      sb_CourseNumber = new StringBuilder( CourseNumber );
   MutableInt mi_CourseID = new MutableInt( CourseID );
       omDegTrk_ReturnCourseVariables( mStudenC, mi_CourseID, sb_CourseNumber, sb_CourseTitle, sb_Semester, sb_ClassEndDate );
   ClassEndDate = sb_ClassEndDate.toString( );
   Semester = sb_Semester.toString( );
   CourseTitle = sb_CourseTitle.toString( );
   CourseNumber = sb_CourseNumber.toString( );
   CourseID = mi_CourseID.intValue( );}
   //:mDegTrk.CourseType3or4.wCourseSatisfyingID = CourseID
   SetAttributeFromInteger( mDegTrk, "CourseType3or4", "wCourseSatisfyingID", CourseID );

   //:// Check for the need of a duplicate Course entry for repeatable Courses and create as necessary.
   //:CheckForRepeatable( mDegTrk, mStudenC, "3" )
   omDegTrk_CheckForRepeatable( mDegTrk, mStudenC, "3" );
   //:IF FinalGrade >= MinimumGrade OR szFinalGradeString = "S" OR szFinalGradeString = ""
   if ( FinalGrade >= MinimumGrade || ZeidonStringCompare( szFinalGradeString, 1, 0, "S", 1, 0, 21 ) == 0 || ZeidonStringCompare( szFinalGradeString, 1, 0, "", 1, 0, 21 ) == 0 )
   { 
      //:// Final Grade is OK or is still null, so we flag the course as having been taken for credit.
      //:// If a graduation date has been set for the Student, then we will only flag those courses
      //:// that will be taken prior to the graduation date and we will never flag "Pre-Transfer" courses.
      //:IF mStudenC.Registration.Status != "P"
      if ( CompareAttributeToString( mStudenC, "Registration", "Status", "P" ) != 0 )
      { 
         //:IF mStudent.DC_GraduationDate EXISTS
         lTempInteger_3 = CheckExistenceOfEntity( mStudent, "DC_GraduationDate" );
         if ( lTempInteger_3 == 0 )
         { 
            //:IF mStudent.DC_GraduationDate.Date >= ClassEndDate OR  szFinalGradeString != ""
            if ( CompareAttributeToString( mStudent, "DC_GraduationDate", "Date", ClassEndDate ) >= 0 || ZeidonStringCompare( szFinalGradeString, 1, 0, "", 1, 0, 21 ) != 0 )
            { 
               //:mDegTrk.CourseType3or4.wDegreeAuditCourseTaken = "Y"
               SetAttributeFromString( mDegTrk, "CourseType3or4", "wDegreeAuditCourseTaken", "Y" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:mDegTrk.CourseType3or4.wDegreeAuditCourseTaken = "Y"
            SetAttributeFromString( mDegTrk, "CourseType3or4", "wDegreeAuditCourseTaken", "Y" );
         } 

         //:END
         //:IF mStudenC.Registration.Status = "C" OR
         //:   mStudenC.Registration.Status = "F" OR
         //:   mStudenC.Registration.Status = "X" OR
         //:   mStudenC.Registration.Status = "V" OR
         //:   mStudenC.Registration.Status = "Y"
         if ( CompareAttributeToString( mStudenC, "Registration", "Status", "C" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "F" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "X" ) == 0 ||
              CompareAttributeToString( mStudenC, "Registration", "Status", "V" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "Y" ) == 0 )
         { 

            //:mDegTrk.CourseType3or4.wDegreeAuditCourseCompleted = "Y"
            SetAttributeFromString( mDegTrk, "CourseType3or4", "wDegreeAuditCourseCompleted", "Y" );
         } 

         //:END
      } 

      //:END
      //:IF SubstituteDegreeTrackID = mDegTrk.DegreeTrack.ID 
      if ( CompareAttributeToInteger( mDegTrk, "DegreeTrack", "ID", SubstituteDegreeTrackID ) == 0 )
      { 
         //:// Course is override equivalent, so indicate in wCourseStatus.
         //:mDegTrk.CourseType3or4.wCourseStatus = CourseNumber + " is Approved Substitute"
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringCopy( sb_szTempString_0, 1, 0, CourseNumber, 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, " is Approved Substitute", 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
         SetAttributeFromString( mDegTrk, "CourseType3or4", "wCourseStatus", szTempString_0 );
         //:ELSE
      } 
      else
      { 
         //:// Regular Course/Group match, so wCourseStatus is from Registration.Status.
         //:// If this entry is Enrolled or Pre-Transfer, we will also display the Semester.
         //:IF mStudenC.Registration.Status = "T" OR
         //:   mStudenC.Registration.Status = "P"
         if ( CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "P" ) == 0 )
         { 

            //:mDegTrk.CourseType3or4.wCourseStatus = StudentStatus + " " + Semester
             {StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                        ZeidonStringCopy( sb_szTempString_1, 1, 0, StudentStatus, 1, 0, 255 );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                        ZeidonStringConcat( sb_szTempString_1, 1, 0, " ", 1, 0, 255 );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                        ZeidonStringConcat( sb_szTempString_1, 1, 0, Semester, 1, 0, 255 );
            szTempString_1 = sb_szTempString_1.toString( );}
            SetAttributeFromString( mDegTrk, "CourseType3or4", "wCourseStatus", szTempString_1 );
            //:ELSE
         } 
         else
         { 
            //:mDegTrk.CourseType3or4.wCourseStatus = StudentStatus
            SetAttributeFromString( mDegTrk, "CourseType3or4", "wCourseStatus", StudentStatus );
         } 

         //:END
      } 

      //:END
      //:IF szFinalGradeString = ""
      if ( ZeidonStringCompare( szFinalGradeString, 1, 0, "", 1, 0, 21 ) == 0 )
      { 
         //:mDegTrk.CourseType3or4.wFinalGrade   = ""
         SetAttributeFromString( mDegTrk, "CourseType3or4", "wFinalGrade", "" );
         //:ELSE
      } 
      else
      { 
         //:mDegTrk.CourseType3or4.wFinalGrade   = mStudenC.Registration.FinalGrade
         SetAttributeFromAttribute( mDegTrk, "CourseType3or4", "wFinalGrade", mStudenC, "Registration", "FinalGrade" );
      } 

      //:END
      //:mDegTrk.CourseType3or4.wCreditsTaken = mStudenC.Registration.CreditHours
      SetAttributeFromAttribute( mDegTrk, "CourseType3or4", "wCreditsTaken", mStudenC, "Registration", "CreditHours" );
      //:mDegTrk.CourseType3or4.wRegistrationSatisfyingID = mStudenC.Registration.ID
      SetAttributeFromAttribute( mDegTrk, "CourseType3or4", "wRegistrationSatisfyingID", mStudenC, "Registration", "ID" );
      //:IF mStudenC.Registration.Status = "T"
      if ( CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) == 0 )
      { 
         //:mDegTrk.RequiredGroup.wDegreeAuditHasTakingFlag = "Y"
         SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditHasTakingFlag", "Y" );
      } 

      //:END

      //:ELSE
   } 
   else
   { 
      //:// Final Grade is not OK and the Course has NOT been retaken.
      //:// If the Final Grade satisfies Elective, create as "Completed, not for major" entry.
      //:// If the Final Grade does NOT satisfy Elective, create an entry for 0 credits.
      //:IF FinalGrade >= MinimumElectiveGrade
      if ( FinalGrade >= MinimumElectiveGrade )
      { 
         //:// "Completed, not for major" entry.
         //:IF mStudenC.DAS_Course EXISTS
         lTempInteger_4 = CheckExistenceOfEntity( mStudenC, "DAS_Course" );
         if ( lTempInteger_4 == 0 )
         { 
            //:mDegTrk.CourseType3or4.wCourseStatus = mStudenC.Registration.dClassNumber + " Completed, not for major"
            {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetStringFromAttribute( sb_szTempString_2, mStudenC, "Registration", "dClassNumber" );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringConcat( sb_szTempString_2, 1, 0, " Completed, not for major", 1, 0, 255 );
            szTempString_2 = sb_szTempString_2.toString( );}
            SetAttributeFromString( mDegTrk, "CourseType3or4", "wCourseStatus", szTempString_2 );
            //:ELSE
         } 
         else
         { 
            //:mDegTrk.CourseType3or4.wCourseStatus = "Completed, not for major"
            SetAttributeFromString( mDegTrk, "CourseType3or4", "wCourseStatus", "Completed, not for major" );
         } 

         //:END
         //:mDegTrk.CourseType3or4.wFinalGrade   = mStudenC.Registration.FinalGrade
         SetAttributeFromAttribute( mDegTrk, "CourseType3or4", "wFinalGrade", mStudenC, "Registration", "FinalGrade" );
         //:mDegTrk.CourseType3or4.wCreditsTaken = mStudenC.Registration.CreditHours
         SetAttributeFromAttribute( mDegTrk, "CourseType3or4", "wCreditsTaken", mStudenC, "Registration", "CreditHours" );
         //:mDegTrk.CourseType3or4.wRegistrationSatisfyingID = mStudenC.Registration.ID
         SetAttributeFromAttribute( mDegTrk, "CourseType3or4", "wRegistrationSatisfyingID", mStudenC, "Registration", "ID" );
         //:ELSE
      } 
      else
      { 
         //:// Create 0 Credits entry.
         //:// The Student must have failed the Course or got an incomplete.
         //:IF mStudenC.Registration.FinalGrade = "F" OR mStudenC.Registration.FinalGrade = "U"
         if ( CompareAttributeToString( mStudenC, "Registration", "FinalGrade", "F" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "FinalGrade", "U" ) == 0 )
         { 
            //:mDegTrk.CourseType3or4.wCourseStatus = "Failed"
            SetAttributeFromString( mDegTrk, "CourseType3or4", "wCourseStatus", "Failed" );
            //:ELSE
         } 
         else
         { 
            //:mDegTrk.CourseType3or4.wCourseStatus = "Incomplete"
            SetAttributeFromString( mDegTrk, "CourseType3or4", "wCourseStatus", "Incomplete" );
         } 

         //:END
         //:mDegTrk.CourseType3or4.wFinalGrade   = mStudenC.Registration.FinalGrade
         SetAttributeFromAttribute( mDegTrk, "CourseType3or4", "wFinalGrade", mStudenC, "Registration", "FinalGrade" );
         //:mDegTrk.CourseType3or4.wCreditsTaken = 0
         SetAttributeFromInteger( mDegTrk, "CourseType3or4", "wCreditsTaken", 0 );
         //:mDegTrk.CourseType3or4.wRegistrationSatisfyingID = mStudenC.Registration.ID
         SetAttributeFromAttribute( mDegTrk, "CourseType3or4", "wRegistrationSatisfyingID", mStudenC, "Registration", "ID" );
      } 

      //:END
   } 

   //:END

   //:// For a Course Topic, change the Title to be that of the topic.
   //:// We will not do this, however, when we are processing an override specification. This is because
   //:// the Registration entry we are on is for the override Course and not for the Course in the Degree Track.
   //:IF SubstituteDegreeTrackID = mDegTrk.DegreeTrack.ID
   if ( CompareAttributeToInteger( mDegTrk, "DegreeTrack", "ID", SubstituteDegreeTrackID ) == 0 )
   { 
      //:IF mStudenC.DAS_Course.ID != mDegTrk.CourseType3or4.ID 
      if ( CompareAttributeToAttribute( mStudenC, "DAS_Course", "ID", mDegTrk, "CourseType3or4", "ID" ) != 0 )
      { 
         //:IF mStudenC.CourseTopic EXISTS
         lTempInteger_5 = CheckExistenceOfEntity( mStudenC, "CourseTopic" );
         if ( lTempInteger_5 == 0 )
         { 
            //:mDegTrk.CourseType1or2.Title = mStudenC.CourseTopic.Title  
            SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "Title", mStudenC, "CourseTopic", "Title" );
         } 

         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:IF mStudenC.CourseTopic EXISTS
      lTempInteger_6 = CheckExistenceOfEntity( mStudenC, "CourseTopic" );
      if ( lTempInteger_6 == 0 )
      { 
         //:mDegTrk.CourseType3or4.Title = mStudenC.CourseTopic.Title  
         SetAttributeFromAttribute( mDegTrk, "CourseType3or4", "Title", mStudenC, "CourseTopic", "Title" );
      } 

      //:END
   } 

   //:END
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:AuditStudentCourses( VIEW mDegTrk       BASED ON LOD mDegTrk,
//:                     VIEW mStudenC_Orig BASED ON LOD mStudenC )

//:   VIEW mStudenC    BASED ON LOD  mStudenC
public int 
omDegTrk_AuditStudentCourses( View     mDegTrk,
                              View     mStudenC_Orig )
{
   zVIEW    mStudenC = new zVIEW( );
   //:VIEW mStudenCT   BASED ON LOD  mStudenC
   zVIEW    mStudenCT = new zVIEW( );
   //:VIEW mDegTrkC    BASED ON LOD  mDegTrk
   zVIEW    mDegTrkC = new zVIEW( );
   //:VIEW mDegTrkR    BASED ON LOD  mDegTrkR
   zVIEW    mDegTrkR = new zVIEW( );
   //:VIEW mStudent    REGISTERED AS mStudent
   zVIEW    mStudent = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mDegreeLST  BASED ON LOD  mDegree
   zVIEW    mDegreeLST = new zVIEW( );
   //:VIEW mUser       BASED ON LOD  mUser
   zVIEW    mUser = new zVIEW( );
   //:VIEW mCollegeLST BASED ON LOD  mCollege
   zVIEW    mCollegeLST = new zVIEW( );
   //:VIEW lTrnscpt    BASED ON LOD  lTrnscpt
   zVIEW    lTrnscpt = new zVIEW( );
   //:VIEW lTrnscptT   BASED ON LOD  lTrnscpt
   zVIEW    lTrnscptT = new zVIEW( );
   //:INTEGER       CourseID
   int      CourseID = 0;
   //:INTEGER       CourseCount
   int      CourseCount = 0;
   //:INTEGER       CourseTakenCount
   int      CourseTakenCount = 0;
   //:INTEGER       CourseCompletedCount
   int      CourseCompletedCount = 0;
   //:INTEGER       MinimumElectiveGrade
   int      MinimumElectiveGrade = 0;
   //:INTEGER       Count
   int      Count = 0;
   //:DECIMAL       CreditsTaken
   double  CreditsTaken = 0.0;
   //:DECIMAL       CreditsCompleted
   double  CreditsCompleted = 0.0;
   //:DECIMAL       GraduationCreditsEarned
   double  GraduationCreditsEarned = 0.0;
   //:DECIMAL       MinCreditsTaken
   double  MinCreditsTaken = 0.0;
   //:DECIMAL       MinCreditsCompleted
   double  MinCreditsCompleted = 0.0;
   //:DECIMAL       MinCreditsRequired
   double  MinCreditsRequired = 0.0;
   //:DECIMAL       FinalGrade
   double  FinalGrade = 0.0;
   //:DECIMAL       TotalDegreeTrackCreditsTaken
   double  TotalDegreeTrackCreditsTaken = 0.0;
   //:DECIMAL       TotalDegreeTrackCreditsEarned
   double  TotalDegreeTrackCreditsEarned = 0.0;
   //:DECIMAL       MinimumGPA
   double  MinimumGPA = 0.0;
   //:STRING ( 5 )  szCourseCount
   String   szCourseCount = null;
   //:STRING ( 5 )  szCourseTakenCount
   String   szCourseTakenCount = null;
   //:STRING ( 10 ) szCreditsNeeded
   String   szCreditsNeeded = null;
   //:STRING ( 10 ) szCreditsTaken
   String   szCreditsTaken = null;
   //:STRING ( 1 )  szCourseTakenFlag
   String   szCourseTakenFlag = null;
   //:STRING ( 1 )  szCourseCompletedFlag
   String   szCourseCompletedFlag = null;
   //:STRING ( 10 ) CourseNumber
   String   CourseNumber = null;
   //:STRING ( 50 ) CourseTitle
   String   CourseTitle = null;
   //:STRING ( 20 ) szFinalGradeString
   String   szFinalGradeString = null;
   //:STRING ( 1 )  SubstituteFlag
   String   SubstituteFlag = null;
   //:STRING ( 1 )  RepeatableForCreditFlag
   String   RepeatableForCreditFlag = null;
   //:STRING ( 1 )  DevelopmentalFlag
   String   DevelopmentalFlag = null;
   //:STRING ( 20 ) StudentStatus
   String   StudentStatus = null;
   //:STRING ( 4 )  szWorkYear
   String   szWorkYear = null;
   //:STRING ( 8 )  szWorkDate
   String   szWorkDate = null;
   //:STRING ( 200 ) Msg
   String   Msg = null;
   //:STRING ( 20 ) szCollegeType
   String   szCollegeType = null;
   //:STRING ( 1 )  GradUndergradFlag
   String   GradUndergradFlag = null;
   //:STRING ( 1 )  TransferGradUndergradFlag
   String   TransferGradUndergradFlag = null;
   //:SHORT         nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_13 = 0;
   int      lTempInteger_14 = 0;
   int      lTempInteger_15 = 0;
   int      lTempInteger_16 = 0;
   int      lTempInteger_17 = 0;
   int      lTempInteger_18 = 0;
   int      lTempInteger_19 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_20 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_21 = 0;
   int      lTempInteger_22 = 0;
   int      lTempInteger_23 = 0;
   int      lTempInteger_24 = 0;
   int      lTempInteger_25 = 0;
   int      lTempInteger_26 = 0;
   int      lTempInteger_27 = 0;
   int      lTempInteger_28 = 0;
   int      lTempInteger_29 = 0;
   int      lTempInteger_30 = 0;
   int      lTempInteger_31 = 0;
   int      lTempInteger_32 = 0;
   int      lTempInteger_33 = 0;
   int      lTempInteger_34 = 0;
   int      lTempInteger_35 = 0;
   int      lTempInteger_36 = 0;
   int      lTempInteger_37 = 0;
   int      lTempInteger_38 = 0;
   int      lTempInteger_39 = 0;
   int      lTempInteger_40 = 0;
   int      lTempInteger_41 = 0;
   int      lTempInteger_42 = 0;
   int      lTempInteger_43 = 0;
   int      lTempInteger_44 = 0;
   int      lTempInteger_45 = 0;
   int      lTempInteger_46 = 0;
   int      lTempInteger_47 = 0;
   int      lTempInteger_48 = 0;
   int      lTempInteger_49 = 0;
   int      lTempInteger_50 = 0;
   int      lTempInteger_51 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   String   szTempString_2 = null;
   String   szTempString_3 = null;
   double  dTempDecimal_3 = 0.0;
   double  dTempDecimal_4 = 0.0;
   double  dTempDecimal_5 = 0.0;
   double  dTempDecimal_6 = 0.0;
   int      lTempInteger_52 = 0;
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   double  dTempDecimal_7 = 0.0;
   double  dTempDecimal_8 = 0.0;
   double  dTempDecimal_9 = 0.0;
   double  dTempDecimal_10 = 0.0;
   double  dTempDecimal_11 = 0.0;
   double  dTempDecimal_12 = 0.0;
   int      lTempInteger_53 = 0;
   String   szTempString_6 = null;
   String   szTempString_7 = null;
   int      lTempInteger_54 = 0;
   String   szTempString_8 = null;
   String   szTempString_9 = null;
   String   szTempString_10 = null;
   String   szTempString_11 = null;
   double  dTempDecimal_13 = 0.0;
   double  dTempDecimal_14 = 0.0;
   double  dTempDecimal_15 = 0.0;
   double  dTempDecimal_16 = 0.0;
   String   szTempString_12 = null;
   int      lTempInteger_55 = 0;
   String   szTempString_13 = null;
   String   szTempString_14 = null;
   String   szTempString_15 = null;
   double  dTempDecimal_17 = 0.0;
   double  dTempDecimal_18 = 0.0;
   double  dTempDecimal_19 = 0.0;
   String   szTempString_16 = null;
   double  dTempDecimal_20 = 0.0;
   String   szTempString_17 = null;
   int      lTempInteger_56 = 0;
   int      lTempInteger_57 = 0;
   String   szTempString_18 = null;
   String   szTempString_19 = null;
   int      lTempInteger_58 = 0;
   int      lTempInteger_59 = 0;
   int      lTempInteger_60 = 0;
   String   szTempString_20 = null;
   String   szTempString_21 = null;
   int      lTempInteger_61 = 0;
   int      lTempInteger_62 = 0;
   String   szTempString_22 = null;
   String   szTempString_23 = null;
   int      lTempInteger_63 = 0;
   int      lTempInteger_64 = 0;
   int      lTempInteger_65 = 0;
   int      lTempInteger_66 = 0;
   double  dTempDecimal_21 = 0.0;
   double  dTempDecimal_22 = 0.0;

   RESULT = GetViewByName( mStudent, "mStudent", mDegTrk, zLEVEL_TASK );

   //:// Process the courses taken in mStudenC against the degree audit requirements of mDegTrk.
   //:// The results will be specified in the work attributes of mDegTrk.

   //:nRC = GetViewByName( mUser, "mUser", mDegTrk, zLEVEL_APPLICATION )
   nRC = GetViewByName( mUser, "mUser", mDegTrk, zLEVEL_APPLICATION );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:// Use task view in case we're running under Web.
      //:GetViewByName( mUser, "mUser", mDegTrk, zLEVEL_TASK )
      GetViewByName( mUser, "mUser", mDegTrk, zLEVEL_TASK );
   } 

   //:END
   //:mDegTrk.DegreeTrack.wAuditorName = mUser.User.dFullNameLFM 
   SetAttributeFromAttribute( mDegTrk, "DegreeTrack", "wAuditorName", mUser, "User", "dFullNameLFM" );

   //:// Build the Student data from view mStudent.
   //:IF mDegTrk.AuditedStudent EXISTS 
   lTempInteger_0 = CheckExistenceOfEntity( mDegTrk, "AuditedStudent" );
   if ( lTempInteger_0 == 0 )
   { 
      //:DELETE ENTITY mDegTrk.AuditedStudent  
      RESULT = DeleteEntity( mDegTrk, "AuditedStudent", zPOS_NEXT );
   } 

   //:END
   //:CREATE ENTITY mDegTrk.AuditedStudent 
   RESULT = CreateEntity( mDegTrk, "AuditedStudent", zPOS_AFTER );
   //:mDegTrk.AuditedStudent.FullName               = mStudent.Person.dFullName
   SetAttributeFromAttribute( mDegTrk, "AuditedStudent", "FullName", mStudent, "Person", "dFullName" );
   //:mDegTrk.AuditedStudent.DegreeMajorName        = mStudent.DegreeMajor.Name 
   SetAttributeFromAttribute( mDegTrk, "AuditedStudent", "DegreeMajorName", mStudent, "DegreeMajor", "Name" );
   //:mDegTrk.AuditedStudent.DegreeMajorTrackName   = mStudent.MajorDegreeTrack.Name 
   SetAttributeFromAttribute( mDegTrk, "AuditedStudent", "DegreeMajorTrackName", mStudent, "MajorDegreeTrack", "Name" );
   //:mDegTrk.AuditedStudent.ClassificationAtAudit  = mStudent.Student.CurrentLevel 
   SetAttributeFromAttribute( mDegTrk, "AuditedStudent", "ClassificationAtAudit", mStudent, "Student", "CurrentLevel" );
   //:mDegTrk.AuditedStudent.ComprehensiveTestScore = mStudent.StudentMajorDegreeTrack.ComprehensiveTestScore 
   SetAttributeFromAttribute( mDegTrk, "AuditedStudent", "ComprehensiveTestScore", mStudent, "StudentMajorDegreeTrack", "ComprehensiveTestScore" );
   //:mDegTrk.AuditedStudent.eMailAddress           = mStudent.Student.eMailAddress
   SetAttributeFromAttribute( mDegTrk, "AuditedStudent", "eMailAddress", mStudent, "Student", "eMailAddress" );
   //:IF mStudent.DegreeTrackCollegeDegree EXISTS
   lTempInteger_1 = CheckExistenceOfEntity( mStudent, "DegreeTrackCollegeDegree" );
   if ( lTempInteger_1 == 0 )
   { 
      //:mDegTrk.AuditedStudent.DegreeTrackCollegeDegreeName = mStudent.DegreeTrackCollegeDegree.Name 
      SetAttributeFromAttribute( mDegTrk, "AuditedStudent", "DegreeTrackCollegeDegreeName", mStudent, "DegreeTrackCollegeDegree", "Name" );
   } 

   //:END
   //:IF mStudent.Advisor EXISTS
   lTempInteger_2 = CheckExistenceOfEntity( mStudent, "Advisor" );
   if ( lTempInteger_2 == 0 )
   { 
      //:mDegTrk.AuditedStudent.StudentAdvisorShortName = mStudent.Advisor.ShortName 
      SetAttributeFromAttribute( mDegTrk, "AuditedStudent", "StudentAdvisorShortName", mStudent, "Advisor", "ShortName" );
   } 

   //:END
   //:SET CURSOR FIRST mStudent.DegreeMinorCollege WITHIN mStudent.Student 
   //:           WHERE mStudent.DegreeMinorCollege.ID = mStudent.DegreeMajorCollege.ID 
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, mStudent, "DegreeMajorCollege", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   RESULT = mStudent.cursor( "DegreeMinorCollege" ).setFirst( "ID", lTempInteger_3, "Student" ).toInt();
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:mDegTrk.AuditedStudent.DegreeMinorName = mStudent.DegreeMinor.Name 
      SetAttributeFromAttribute( mDegTrk, "AuditedStudent", "DegreeMinorName", mStudent, "DegreeMinor", "Name" );
   } 

   //:END
   //:IF mStudent.DC_AnticipatedGraduationDate EXISTS
   lTempInteger_4 = CheckExistenceOfEntity( mStudent, "DC_AnticipatedGraduationDate" );
   if ( lTempInteger_4 == 0 )
   { 
      //:mDegTrk.AuditedStudent.AnticipatedGraduationDateName = mStudent.DC_AnticipatedGraduationDate.Name
      SetAttributeFromAttribute( mDegTrk, "AuditedStudent", "AnticipatedGraduationDateName", mStudent, "DC_AnticipatedGraduationDate", "Name" );
   } 

   //:END
   //: 

   //:// Activate the Transcript object, which is used for displaying Earned Credits, GPA, etc.
   //:GET VIEW lTrnscpt NAMED "lTrnscpt"
   RESULT = GetViewByName( lTrnscpt, "lTrnscpt", mDegTrk, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( lTrnscpt ) 
      DropObjectInstance( lTrnscpt );
   } 

   //:END
   //:ACTIVATE lTrnscpt WHERE lTrnscpt.Student.ID = mStudenC_Orig.Student.ID
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
       GetIntegerFromAttribute( mi_lTempInteger_5, mStudenC_Orig, "Student", "ID" );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );}
   omDegTrk_fnLocalBuildQual_0( mDegTrk, vTempViewVar_0, lTempInteger_5 );
   RESULT = ActivateObjectInstance( lTrnscpt, "lTrnscpt", mDegTrk, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lTrnscpt "lTrnscpt"
   SetNameForView( lTrnscpt, "lTrnscpt", null, zLEVEL_TASK );

   //:// Build the Transcript object as either graduate or undergraduate, depending on the type of DegreeTrack.
   //:szCollegeType = mDegTrk.DegreeMajorCollege.Type  // Type is Graduate or Undergraduate
   {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
   StringBuilder sb_szCollegeType;
   if ( szCollegeType == null )
      sb_szCollegeType = new StringBuilder( 32 );
   else
      sb_szCollegeType = new StringBuilder( szCollegeType );
       GetVariableFromAttribute( sb_szCollegeType, mi_lTempInteger_6, 'S', 21, mDegTrk, "DegreeMajorCollege", "Type", "", 0 );
   lTempInteger_6 = mi_lTempInteger_6.intValue( );
   szCollegeType = sb_szCollegeType.toString( );}
   //:GradUndergradFlag = szCollegeType                // Flag is G or U
    {StringBuilder sb_GradUndergradFlag;
   if ( GradUndergradFlag == null )
      sb_GradUndergradFlag = new StringBuilder( 32 );
   else
      sb_GradUndergradFlag = new StringBuilder( GradUndergradFlag );
      ZeidonStringCopy( sb_GradUndergradFlag, 1, 0, szCollegeType, 1, 0, 2 );
   GradUndergradFlag = sb_GradUndergradFlag.toString( );}
   //:BuildTranscript( lTrnscpt, GradUndergradFlag )
   {
    lTrnscpt_Object m_lTrnscpt_Object = new lTrnscpt_Object( lTrnscpt );
    m_lTrnscpt_Object.olTrnscpt_BuildTranscript( lTrnscpt, GradUndergradFlag );
    // m_lTrnscpt_Object = null;  // permit gc  (unnecessary)
   }

   //:// Use a new copy of mStudenC, since we may drop entities in the object.
   //:ActivateOI_FromOI( mStudenC, mStudenC_Orig, zSINGLE )
   ActivateOI_FromOI( mStudenC, mStudenC_Orig, zSINGLE );
   //:NAME VIEW mStudenC "mStudenC_Audit"
   SetNameForView( mStudenC, "mStudenC_Audit", null, zLEVEL_TASK );

   //:// Treat any IP grades as if the student is still enrolled in the Class.
   //:FOR EACH mStudenC.Registration
   RESULT = mStudenC.cursor( "Registration" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szFinalGradeString = mStudenC.Registration.FinalGrade
      {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
      StringBuilder sb_szFinalGradeString;
      if ( szFinalGradeString == null )
         sb_szFinalGradeString = new StringBuilder( 32 );
      else
         sb_szFinalGradeString = new StringBuilder( szFinalGradeString );
             GetVariableFromAttribute( sb_szFinalGradeString, mi_lTempInteger_7, 'S', 21, mStudenC, "Registration", "FinalGrade", "", 0 );
      lTempInteger_7 = mi_lTempInteger_7.intValue( );
      szFinalGradeString = sb_szFinalGradeString.toString( );}
      //:IF szFinalGradeString = "IP" OR
      //:   szFinalGradeString = "I"  OR
      //:   szFinalGradeString = "NG" OR
      //:   szFinalGradeString = "X"  OR
      //:   szFinalGradeString = "AI"
      if ( ZeidonStringCompare( szFinalGradeString, 1, 0, "IP", 1, 0, 21 ) == 0 || ZeidonStringCompare( szFinalGradeString, 1, 0, "I", 1, 0, 21 ) == 0 || ZeidonStringCompare( szFinalGradeString, 1, 0, "NG", 1, 0, 21 ) == 0 ||
           ZeidonStringCompare( szFinalGradeString, 1, 0, "X", 1, 0, 21 ) == 0 || ZeidonStringCompare( szFinalGradeString, 1, 0, "AI", 1, 0, 21 ) == 0 )
      { 

         //:mStudenC.Registration.FinalGrade = ""
         SetAttributeFromString( mStudenC, "Registration", "FinalGrade", "" );
         //:mStudenC.Registration.Status = "T"
         SetAttributeFromString( mStudenC, "Registration", "Status", "T" );
      } 

      RESULT = mStudenC.cursor( "Registration" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:// Drop any Registration entries that are not for the grad/undergrad type of the College of the DegreeMajor.
   //:IF mDegTrk.DegreeMajorCollege EXISTS
   lTempInteger_8 = CheckExistenceOfEntity( mDegTrk, "DegreeMajorCollege" );
   if ( lTempInteger_8 == 0 )
   { 
      //:GET VIEW mCollegeLST NAMED "mCollegeLST"
      RESULT = GetViewByName( mCollegeLST, "mCollegeLST", mDegTrk, zLEVEL_TASK );
      //:IF RESULT < 0
      if ( RESULT < 0 )
      { 
         //:ACTIVATE mCollegeLST RootOnlyMultiple
         RESULT = ActivateObjectInstance( mCollegeLST, "mCollege", mDegTrk, 0, zACTIVATE_ROOTONLY_MULTIPLE );
         //:NAME VIEW mCollegeLST "mCollegeLST"
         SetNameForView( mCollegeLST, "mCollegeLST", null, zLEVEL_TASK );
      } 

      //:END
      //:FOR EACH mStudenC.Registration
      RESULT = mStudenC.cursor( "Registration" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mStudenC.Registration.GradUndergradOverrideFlag != ""
         if ( CompareAttributeToString( mStudenC, "Registration", "GradUndergradOverrideFlag", "" ) != 0 )
         { 
            //:IF mStudenC.Registration.GradUndergradOverrideFlag != GradUndergradFlag
            if ( CompareAttributeToString( mStudenC, "Registration", "GradUndergradOverrideFlag", GradUndergradFlag ) != 0 )
            { 
               //:DropEntity( mStudenC, "Registration", zREPOS_NONE )
               DropEntity( mStudenC, "Registration", zREPOS_NONE );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF mStudenC.RegistrationCourseCollege EXISTS
            lTempInteger_9 = CheckExistenceOfEntity( mStudenC, "RegistrationCourseCollege" );
            if ( lTempInteger_9 == 0 )
            { 
               //:SET CURSOR FIRST mCollegeLST.College WHERE mCollegeLST.College.ID = mStudenC.RegistrationCourseCollege.ID 
               {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
                               GetIntegerFromAttribute( mi_lTempInteger_10, mStudenC, "RegistrationCourseCollege", "ID" );
               lTempInteger_10 = mi_lTempInteger_10.intValue( );}
               RESULT = mCollegeLST.cursor( "College" ).setFirst( "ID", lTempInteger_10 ).toInt();
               //:IF mCollegeLST.College.Type != szCollegeType
               if ( CompareAttributeToString( mCollegeLST, "College", "Type", szCollegeType ) != 0 )
               { 
                  //:DropEntity( mStudenC, "Registration", zREPOS_NONE )
                  DropEntity( mStudenC, "Registration", zREPOS_NONE );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:TransferGradUndergradFlag = "U"
                {StringBuilder sb_TransferGradUndergradFlag;
               if ( TransferGradUndergradFlag == null )
                  sb_TransferGradUndergradFlag = new StringBuilder( 32 );
               else
                  sb_TransferGradUndergradFlag = new StringBuilder( TransferGradUndergradFlag );
                              ZeidonStringCopy( sb_TransferGradUndergradFlag, 1, 0, "U", 1, 0, 2 );
               TransferGradUndergradFlag = sb_TransferGradUndergradFlag.toString( );}
               //:IF mStudenC.RegTransferGroup EXISTS
               lTempInteger_11 = CheckExistenceOfEntity( mStudenC, "RegTransferGroup" );
               if ( lTempInteger_11 == 0 )
               { 
                  //:// Converted by DonC from GraduateCollegeFlag to ApplyToCollegeLevel on 1/13/2008.
                  //://IF mStudenC.RegTransferGroup.GraduateCollegeFlag = "Y"
                  //:IF mStudenC.RegTransferGroup.ApplyToCollegeLevel = "G"
                  if ( CompareAttributeToString( mStudenC, "RegTransferGroup", "ApplyToCollegeLevel", "G" ) == 0 )
                  { 
                     //:TransferGradUndergradFlag = "G"
                      {StringBuilder sb_TransferGradUndergradFlag;
                     if ( TransferGradUndergradFlag == null )
                        sb_TransferGradUndergradFlag = new StringBuilder( 32 );
                     else
                        sb_TransferGradUndergradFlag = new StringBuilder( TransferGradUndergradFlag );
                                          ZeidonStringCopy( sb_TransferGradUndergradFlag, 1, 0, "G", 1, 0, 2 );
                     TransferGradUndergradFlag = sb_TransferGradUndergradFlag.toString( );}
                  } 

                  //:END
               } 

               //:END
               //:IF TransferGradUndergradFlag != GradUndergradFlag
               if ( ZeidonStringCompare( TransferGradUndergradFlag, 1, 0, GradUndergradFlag, 1, 0, 2 ) != 0 )
               { 
                  //:DropEntity( mStudenC, "Registration", zREPOS_NONE )
                  DropEntity( mStudenC, "Registration", zREPOS_NONE );
               } 

               //:END
            } 

            //:END
         } 

         RESULT = mStudenC.cursor( "Registration" ).setNextContinue().toInt();;
         //:END
      } 

      //:END
   } 

   //:END

   //:// Add an "Electives" group for putting all classes that don't fall into one of the original Degree Track
   //:// Groups.
   //:SET CURSOR LAST mDegTrk.DegreeTrackRequiredGroup
   RESULT = mDegTrk.cursor( "DegreeTrackRequiredGroup" ).setLast().toInt();;
   //:CREATE ENTITY mDegTrk.DegreeTrackRequiredGroup AFTER
   RESULT = CreateEntity( mDegTrk, "DegreeTrackRequiredGroup", zPOS_AFTER );
   //:CREATE ENTITY mDegTrk.RequiredGroup 
   RESULT = CreateEntity( mDegTrk, "RequiredGroup", zPOS_AFTER );
   //:mDegTrk.RequiredGroup.Name = "Electives"
   SetAttributeFromString( mDegTrk, "RequiredGroup", "Name", "Electives" );
   //:mDegTrk.RequiredGroup.Type = "E"
   SetAttributeFromString( mDegTrk, "RequiredGroup", "Type", "E" );

   //:// Add any Student Requirements groups at the beginning.
   //:SET CURSOR FIRST mDegTrk.DegreeTrackRequiredGroup
   RESULT = mDegTrk.cursor( "DegreeTrackRequiredGroup" ).setFirst().toInt();
   //:FOR EACH mStudenC.HS_RequiredGroup 
   RESULT = mStudenC.cursor( "HS_RequiredGroup" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:ACTIVATE mDegTrkR WHERE mDegTrkR.RequiredGroup.ID = mStudenC.HS_RequiredGroup.ID 
      {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
             GetIntegerFromAttribute( mi_lTempInteger_12, mStudenC, "HS_RequiredGroup", "ID" );
      lTempInteger_12 = mi_lTempInteger_12.intValue( );}
      omDegTrk_fnLocalBuildQual_1( mDegTrk, vTempViewVar_1, lTempInteger_12 );
      RESULT = ActivateObjectInstance( mDegTrkR, "mDegTrkR", mDegTrk, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:CREATE ENTITY mDegTrk.DegreeTrackRequiredGroup BEFORE
      RESULT = CreateEntity( mDegTrk, "DegreeTrackRequiredGroup", zPOS_BEFORE );
      //:INCLUDE mDegTrk.RequiredGroup FROM mDegTrkR.RequiredGroup
      RESULT = IncludeSubobjectFromSubobject( mDegTrk, "RequiredGroup", mDegTrkR, "RequiredGroup", zPOS_AFTER );
      //:DropObjectInstance( mDegTrkR )
      DropObjectInstance( mDegTrkR );
      RESULT = mStudenC.cursor( "HS_RequiredGroup" ).setNextContinue().toInt();;
   } 

   //:END

   //:// Add an additional "Developmental" group at the beginning.
   //:SET CURSOR FIRST mDegTrk.DegreeTrackRequiredGroup
   RESULT = mDegTrk.cursor( "DegreeTrackRequiredGroup" ).setFirst().toInt();
   //:CREATE ENTITY mDegTrk.DegreeTrackRequiredGroup BEFORE
   RESULT = CreateEntity( mDegTrk, "DegreeTrackRequiredGroup", zPOS_BEFORE );
   //:CREATE ENTITY mDegTrk.RequiredGroup 
   RESULT = CreateEntity( mDegTrk, "RequiredGroup", zPOS_AFTER );
   //:mDegTrk.RequiredGroup.Name = "Developmental"
   SetAttributeFromString( mDegTrk, "RequiredGroup", "Name", "Developmental" );
   //:mDegTrk.RequiredGroup.Type = "D"
   SetAttributeFromString( mDegTrk, "RequiredGroup", "Type", "D" );
   //:SetAttributeFromCurrentDateTime( mDegTrk, "DegreeTrack", "wCreatedDate" )
   {
    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mDegTrk );
    m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mDegTrk, "DegreeTrack", "wCreatedDate" );
    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
   }

   //:// First, loop through and process any substituted Courses and assign them to the correct Group Course or Group.
   //:// If one or more entries exist to assign the Course to a particular Group and/or Course, we will first try to 
   //:// use that information in assigning the Course. If it doesn't work or if no particular Course was requested,
   //:// we will skip assignment here.
   //:FOR EACH mStudenC.Registration 
   //:                       WHERE mStudenC.Registration.Status = "C"    // Completed
   //:                          OR mStudenC.Registration.Status = "T"    // Currently Enrolled
   //:                          OR mStudenC.Registration.Status = "V"    // Waived
   //:                          OR mStudenC.Registration.Status = "F"    // Transferred
   //:                          OR mStudenC.Registration.Status = "X"    // L. Transferred
   //:                          OR mStudenC.Registration.Status = "Y"    // L. Waived
   //:                          OR mStudenC.Registration.Status = "P"    // Pre-Transferred
   RESULT = mStudenC.cursor( "Registration" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( mStudenC, "Registration", "Status", "C" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "V" ) == 0 ||
           CompareAttributeToString( mStudenC, "Registration", "Status", "F" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "X" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "Y" ) == 0 ||
           CompareAttributeToString( mStudenC, "Registration", "Status", "P" ) == 0 )
      { 

         //:IF mStudenC.RegistrationClass EXISTS
         lTempInteger_13 = CheckExistenceOfEntity( mStudenC, "RegistrationClass" );
         if ( lTempInteger_13 == 0 )
         { 
            //:IF mStudenC.CrossListedCourse EXISTS
            lTempInteger_14 = CheckExistenceOfEntity( mStudenC, "CrossListedCourse" );
            if ( lTempInteger_14 == 0 )
            { 
               //:CourseID = mStudenC.CrossListedCourse.ID 
               {MutableInt mi_CourseID = new MutableInt( CourseID );
                               GetIntegerFromAttribute( mi_CourseID, mStudenC, "CrossListedCourse", "ID" );
               CourseID = mi_CourseID.intValue( );}
               //:ELSE
            } 
            else
            { 
               //:CourseID = mStudenC.RegistrationCourse.ID
               {MutableInt mi_CourseID = new MutableInt( CourseID );
                               GetIntegerFromAttribute( mi_CourseID, mStudenC, "RegistrationCourse", "ID" );
               CourseID = mi_CourseID.intValue( );}
            } 

            //:END
            //:RepeatableForCreditFlag = mStudenC.RegistrationCourse.RepeatableForCreditFlag 
            {MutableInt mi_lTempInteger_15 = new MutableInt( lTempInteger_15 );
            StringBuilder sb_RepeatableForCreditFlag;
            if ( RepeatableForCreditFlag == null )
               sb_RepeatableForCreditFlag = new StringBuilder( 32 );
            else
               sb_RepeatableForCreditFlag = new StringBuilder( RepeatableForCreditFlag );
                         GetVariableFromAttribute( sb_RepeatableForCreditFlag, mi_lTempInteger_15, 'S', 2, mStudenC, "RegistrationCourse", "RepeatableForCreditFlag", "", 0 );
            lTempInteger_15 = mi_lTempInteger_15.intValue( );
            RepeatableForCreditFlag = sb_RepeatableForCreditFlag.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mStudenC.EquivalentCourse EXISTS
            lTempInteger_16 = CheckExistenceOfEntity( mStudenC, "EquivalentCourse" );
            if ( lTempInteger_16 == 0 )
            { 
               //:CourseID                = mStudenC.EquivalentCourse.ID
               {MutableInt mi_CourseID = new MutableInt( CourseID );
                               GetIntegerFromAttribute( mi_CourseID, mStudenC, "EquivalentCourse", "ID" );
               CourseID = mi_CourseID.intValue( );}
               //:RepeatableForCreditFlag = mStudenC.EquivalentCourse.RepeatableForCreditFlag 
               {MutableInt mi_lTempInteger_17 = new MutableInt( lTempInteger_17 );
               StringBuilder sb_RepeatableForCreditFlag;
               if ( RepeatableForCreditFlag == null )
                  sb_RepeatableForCreditFlag = new StringBuilder( 32 );
               else
                  sb_RepeatableForCreditFlag = new StringBuilder( RepeatableForCreditFlag );
                               GetVariableFromAttribute( sb_RepeatableForCreditFlag, mi_lTempInteger_17, 'S', 2, mStudenC, "EquivalentCourse", "RepeatableForCreditFlag", "", 0 );
               lTempInteger_17 = mi_lTempInteger_17.intValue( );
               RepeatableForCreditFlag = sb_RepeatableForCreditFlag.toString( );}
               //:ELSE
            } 
            else
            { 
               //:CourseID = 0
               CourseID = 0;
            } 

            //:END
         } 

         //:END

         //:SubstituteFlag = ""
          {StringBuilder sb_SubstituteFlag;
         if ( SubstituteFlag == null )
            sb_SubstituteFlag = new StringBuilder( 32 );
         else
            sb_SubstituteFlag = new StringBuilder( SubstituteFlag );
                  ZeidonStringCopy( sb_SubstituteFlag, 1, 0, "", 1, 0, 2 );
         SubstituteFlag = sb_SubstituteFlag.toString( );}
         //:mStudenC.Registration.wSubstituteFlag = ""
         SetAttributeFromString( mStudenC, "Registration", "wSubstituteFlag", "" );
         //:FOR EACH mStudenC.DegreeAuditSpecification 
         RESULT = mStudenC.cursor( "DegreeAuditSpecification" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mStudenC.DAS_DegreeTrack DOES NOT EXIST
            lTempInteger_18 = CheckExistenceOfEntity( mStudenC, "DAS_DegreeTrack" );
            if ( lTempInteger_18 != 0 )
            { 
               //:IF mStudenC.DAS_Course EXISTS
               lTempInteger_19 = CheckExistenceOfEntity( mStudenC, "DAS_Course" );
               if ( lTempInteger_19 == 0 )
               { 
                  //:Msg = "The Degree Track for Substitute Course, " + mStudenC.DAS_Course.Number + ", does not exist." + NEW_LINE +
                  //:   "Please delete the manual assignment entry."
                  {MutableInt mi_lTempInteger_20 = new MutableInt( lTempInteger_20 );
                  StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                     GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_20, 'S', 11, mStudenC, "DAS_Course", "Number", "", 0 );
                  lTempInteger_20 = mi_lTempInteger_20.intValue( );
                  szTempString_0 = sb_szTempString_0.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringCopy( sb_Msg, 1, 0, "The Degree Track for Substitute Course, ", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, szTempString_0, 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, ", does not exist.", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, "Please delete the manual assignment entry.", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                  //:MessageSend( mStudenC, "", "Degree Audit", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( mStudenC, "", "Degree Audit", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:ELSE
               } 
               else
               { 
                  //:Msg = "The Degree Track for Substitute Group, " + mStudenC.DAS_RequiredGroup.Name + ", does not exist." + NEW_LINE +
                  //:   "Please delete the manual assignment entry."
                  {MutableInt mi_lTempInteger_21 = new MutableInt( lTempInteger_21 );
                  StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_21, 'S', 51, mStudenC, "DAS_RequiredGroup", "Name", "", 0 );
                  lTempInteger_21 = mi_lTempInteger_21.intValue( );
                  szTempString_1 = sb_szTempString_1.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringCopy( sb_Msg, 1, 0, "The Degree Track for Substitute Group, ", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, szTempString_1, 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, ", does not exist.", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, "Please delete the manual assignment entry.", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                  //:MessageSend( mStudenC, "", "Degree Audit", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( mStudenC, "", "Degree Audit", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF SubstituteFlag = "" AND mStudenC.DAS_DegreeTrack.ID = mDegTrk.DegreeTrack.ID 
               if ( ZeidonStringCompare( SubstituteFlag, 1, 0, "", 1, 0, 2 ) == 0 && CompareAttributeToAttribute( mStudenC, "DAS_DegreeTrack", "ID", mDegTrk, "DegreeTrack", "ID" ) == 0 )
               { 
                  //:SET CURSOR FIRST mDegTrk.RequiredGroup WITHIN mDegTrk.DegreeTrack
                  //:        WHERE mDegTrk.RequiredGroup.ID = mStudenC.DAS_RequiredGroup.ID
                  {MutableInt mi_lTempInteger_22 = new MutableInt( lTempInteger_22 );
                                     GetIntegerFromAttribute( mi_lTempInteger_22, mStudenC, "DAS_RequiredGroup", "ID" );
                  lTempInteger_22 = mi_lTempInteger_22.intValue( );}
                  RESULT = mDegTrk.cursor( "RequiredGroup" ).setFirst( "ID", lTempInteger_22, "DegreeTrack" ).toInt();
                  //:IF mStudenC.DAS_RequiredSubGroup EXISTS
                  lTempInteger_23 = CheckExistenceOfEntity( mStudenC, "DAS_RequiredSubGroup" );
                  if ( lTempInteger_23 == 0 )
                  { 
                     //:SET CURSOR FIRST mDegTrk.RequiredSubGroup 
                     //:        WHERE mDegTrk.RequiredSubGroup.ID = mStudenC.DAS_RequiredSubGroup.ID  
                     {MutableInt mi_lTempInteger_24 = new MutableInt( lTempInteger_24 );
                                           GetIntegerFromAttribute( mi_lTempInteger_24, mStudenC, "DAS_RequiredSubGroup", "ID" );
                     lTempInteger_24 = mi_lTempInteger_24.intValue( );}
                     RESULT = mDegTrk.cursor( "RequiredSubGroup" ).setFirst( "ID", lTempInteger_24 ).toInt();
                  } 

                  //:END
                  //:IF mStudenC.DAS_Course EXISTS
                  lTempInteger_25 = CheckExistenceOfEntity( mStudenC, "DAS_Course" );
                  if ( lTempInteger_25 == 0 )
                  { 
                     //:// A particular Course override entry is selected, so just position on that Course.
                     //:SET CURSOR FIRST mDegTrk.CourseType1or2 WITHIN mDegTrk.RequiredGroup
                     //:        WHERE mDegTrk.CourseType1or2.ID = mStudenC.DAS_Course.ID 
                     {MutableInt mi_lTempInteger_26 = new MutableInt( lTempInteger_26 );
                                           GetIntegerFromAttribute( mi_lTempInteger_26, mStudenC, "DAS_Course", "ID" );
                     lTempInteger_26 = mi_lTempInteger_26.intValue( );}
                     RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "ID", lTempInteger_26, "RequiredGroup" ).toInt();
                     //:IF RESULT >= zCURSOR_SET AND mDegTrk.RequiredGroup.Type != "E"
                     if ( RESULT >= zCURSOR_SET && CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "E" ) != 0 )
                     { 
                        //:CourseType1or2Match( mDegTrk, mStudenC, mStudent )
                        omDegTrk_CourseType1or2Match( mDegTrk, mStudenC, mStudent );
                        //:SubstituteFlag = "Y"
                         {StringBuilder sb_SubstituteFlag;
                        if ( SubstituteFlag == null )
                           sb_SubstituteFlag = new StringBuilder( 32 );
                        else
                           sb_SubstituteFlag = new StringBuilder( SubstituteFlag );
                                                ZeidonStringCopy( sb_SubstituteFlag, 1, 0, "Y", 1, 0, 2 );
                        SubstituteFlag = sb_SubstituteFlag.toString( );}
                        //:mStudenC.Registration.wSubstituteFlag = "Y"
                        SetAttributeFromString( mStudenC, "Registration", "wSubstituteFlag", "Y" );
                        //:ELSE
                     } 
                     else
                     { 
                        //:IF mStudenC.DAS_RequiredSubGroup EXISTS
                        lTempInteger_27 = CheckExistenceOfEntity( mStudenC, "DAS_RequiredSubGroup" );
                        if ( lTempInteger_27 == 0 )
                        { 
                           //:SET CURSOR FIRST mDegTrk.RequiredSubGroup 
                           //:        WHERE mDegTrk.RequiredSubGroup.ID = mStudenC.DAS_RequiredSubGroup.ID  
                           {MutableInt mi_lTempInteger_28 = new MutableInt( lTempInteger_28 );
                                                       GetIntegerFromAttribute( mi_lTempInteger_28, mStudenC, "DAS_RequiredSubGroup", "ID" );
                           lTempInteger_28 = mi_lTempInteger_28.intValue( );}
                           RESULT = mDegTrk.cursor( "RequiredSubGroup" ).setFirst( "ID", lTempInteger_28 ).toInt();
                           //:SET CURSOR FIRST mDegTrk.CourseType3or4 WITHIN mDegTrk.RequiredSubGroup
                           //:        WHERE mDegTrk.CourseType3or4.ID = mStudenC.DAS_Course.ID       
                           {MutableInt mi_lTempInteger_29 = new MutableInt( lTempInteger_29 );
                                                       GetIntegerFromAttribute( mi_lTempInteger_29, mStudenC, "DAS_Course", "ID" );
                           lTempInteger_29 = mi_lTempInteger_29.intValue( );}
                           RESULT = mDegTrk.cursor( "CourseType3or4" ).setFirst( "ID", lTempInteger_29, "RequiredSubGroup" ).toInt();
                           //:ELSE
                        } 
                        else
                        { 
                           //:SET CURSOR FIRST mDegTrk.CourseType3or4 WITHIN mDegTrk.RequiredGroup
                           //:        WHERE mDegTrk.CourseType3or4.ID = mStudenC.DAS_Course.ID 
                           {MutableInt mi_lTempInteger_30 = new MutableInt( lTempInteger_30 );
                                                       GetIntegerFromAttribute( mi_lTempInteger_30, mStudenC, "DAS_Course", "ID" );
                           lTempInteger_30 = mi_lTempInteger_30.intValue( );}
                           RESULT = mDegTrk.cursor( "CourseType3or4" ).setFirst( "ID", lTempInteger_30, "RequiredGroup" ).toInt();
                        } 

                        //:END
                        //:IF RESULT >= zCURSOR_SET AND mDegTrk.RequiredGroup.Type != "E"
                        if ( RESULT >= zCURSOR_SET && CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "E" ) != 0 )
                        { 
                           //:CourseType3or4Match( mDegTrk, mStudenC, mStudent )
                           omDegTrk_CourseType3or4Match( mDegTrk, mStudenC, mStudent );
                           //:SubstituteFlag = "Y"
                            {StringBuilder sb_SubstituteFlag;
                           if ( SubstituteFlag == null )
                              sb_SubstituteFlag = new StringBuilder( 32 );
                           else
                              sb_SubstituteFlag = new StringBuilder( SubstituteFlag );
                                                      ZeidonStringCopy( sb_SubstituteFlag, 1, 0, "Y", 1, 0, 2 );
                           SubstituteFlag = sb_SubstituteFlag.toString( );}
                           //:mStudenC.Registration.wSubstituteFlag = "Y"
                           SetAttributeFromString( mStudenC, "Registration", "wSubstituteFlag", "Y" );
                        } 

                        //:END
                     } 

                     //:END
                     //:ELSE
                  } 
                  else
                  { 
                     //:// A particular Course entry is not selected.
                     //:IF RepeatableForCreditFlag = "Y"
                     if ( ZeidonStringCompare( RepeatableForCreditFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
                     { 
                        //:// For Repeatable Course, we don't care about whether we've already processed the entry.
                        //:SET CURSOR FIRST mDegTrk.CourseType1or2 WITHIN mDegTrk.RequiredGroup
                        //:        WHERE mDegTrk.CourseType1or2.ID = CourseID
                        RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "ID", CourseID, "RequiredGroup" ).toInt();
                        //:IF RESULT >= zCURSOR_SET AND mDegTrk.RequiredGroup.Type != "E"
                        if ( RESULT >= zCURSOR_SET && CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "E" ) != 0 )
                        { 
                           //:CourseType1or2Match( mDegTrk, mStudenC, mStudent )
                           omDegTrk_CourseType1or2Match( mDegTrk, mStudenC, mStudent );
                           //:SubstituteFlag = "Y"
                            {StringBuilder sb_SubstituteFlag;
                           if ( SubstituteFlag == null )
                              sb_SubstituteFlag = new StringBuilder( 32 );
                           else
                              sb_SubstituteFlag = new StringBuilder( SubstituteFlag );
                                                      ZeidonStringCopy( sb_SubstituteFlag, 1, 0, "Y", 1, 0, 2 );
                           SubstituteFlag = sb_SubstituteFlag.toString( );}
                           //:mStudenC.Registration.wSubstituteFlag = "Y"
                           SetAttributeFromString( mStudenC, "Registration", "wSubstituteFlag", "Y" );
                           //:ELSE
                        } 
                        else
                        { 
                           //:IF mStudenC.DAS_RequiredSubGroup EXISTS
                           lTempInteger_31 = CheckExistenceOfEntity( mStudenC, "DAS_RequiredSubGroup" );
                           if ( lTempInteger_31 == 0 )
                           { 
                              //:SET CURSOR FIRST mDegTrk.CourseType3or4 WITHIN mDegTrk.RequiredSubGroup
                              //:        WHERE mDegTrk.CourseType3or4.ID = CourseID       
                              RESULT = mDegTrk.cursor( "CourseType3or4" ).setFirst( "ID", CourseID, "RequiredSubGroup" ).toInt();
                              //:ELSE
                           } 
                           else
                           { 
                              //:SET CURSOR FIRST mDegTrk.CourseType3or4 WITHIN mDegTrk.RequiredGroup
                              //:        WHERE mDegTrk.CourseType3or4.ID = CourseID 
                              RESULT = mDegTrk.cursor( "CourseType3or4" ).setFirst( "ID", CourseID, "RequiredGroup" ).toInt();
                           } 

                           //:END
                           //:IF RESULT >= zCURSOR_SET AND mDegTrk.RequiredGroup.Type != "E"
                           if ( RESULT >= zCURSOR_SET && CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "E" ) != 0 )
                           { 
                              //:CourseType3or4Match( mDegTrk, mStudenC, mStudent )
                              omDegTrk_CourseType3or4Match( mDegTrk, mStudenC, mStudent );
                              //:SubstituteFlag = "Y"
                               {StringBuilder sb_SubstituteFlag;
                              if ( SubstituteFlag == null )
                                 sb_SubstituteFlag = new StringBuilder( 32 );
                              else
                                 sb_SubstituteFlag = new StringBuilder( SubstituteFlag );
                                                            ZeidonStringCopy( sb_SubstituteFlag, 1, 0, "Y", 1, 0, 2 );
                              SubstituteFlag = sb_SubstituteFlag.toString( );}
                              //:mStudenC.Registration.wSubstituteFlag = "Y"
                              SetAttributeFromString( mStudenC, "Registration", "wSubstituteFlag", "Y" );
                           } 

                           //:END
                        } 

                        //:END
                        //:ELSE
                     } 
                     else
                     { 
                        //:// For non-Repeatable Course, we DO care about whether we've already processed the entry.
                        //:SET CURSOR FIRST mDegTrk.CourseType1or2 WITHIN mDegTrk.RequiredGroup
                        //:        WHERE mDegTrk.CourseType1or2.ID = CourseID
                        //:          AND mDegTrk.CourseType1or2.wDegreeAuditCourseTaken = ""
                        RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "RequiredGroup" ).toInt();
                        if ( RESULT > zCURSOR_UNCHANGED )
                        { 
                           while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mDegTrk, "CourseType1or2", "ID", CourseID ) != 0 || CompareAttributeToString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseTaken", "" ) != 0 ) )
                           { 
                              RESULT = mDegTrk.cursor( "CourseType1or2" ).setNextContinue().toInt();;
                           } 

                        } 

                        //:IF RESULT >= zCURSOR_SET AND mDegTrk.RequiredGroup.Type != "E"
                        if ( RESULT >= zCURSOR_SET && CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "E" ) != 0 )
                        { 
                           //:CourseType1or2Match( mDegTrk, mStudenC, mStudent )
                           omDegTrk_CourseType1or2Match( mDegTrk, mStudenC, mStudent );
                           //:SubstituteFlag = "Y"
                            {StringBuilder sb_SubstituteFlag;
                           if ( SubstituteFlag == null )
                              sb_SubstituteFlag = new StringBuilder( 32 );
                           else
                              sb_SubstituteFlag = new StringBuilder( SubstituteFlag );
                                                      ZeidonStringCopy( sb_SubstituteFlag, 1, 0, "Y", 1, 0, 2 );
                           SubstituteFlag = sb_SubstituteFlag.toString( );}
                           //:mStudenC.Registration.wSubstituteFlag = "Y"
                           SetAttributeFromString( mStudenC, "Registration", "wSubstituteFlag", "Y" );
                           //:ELSE
                        } 
                        else
                        { 
                           //:IF mStudenC.DAS_RequiredSubGroup EXISTS
                           lTempInteger_32 = CheckExistenceOfEntity( mStudenC, "DAS_RequiredSubGroup" );
                           if ( lTempInteger_32 == 0 )
                           { 
                              //:SET CURSOR FIRST mDegTrk.CourseType3or4 WITHIN mDegTrk.RequiredSubGroup
                              //:        WHERE mDegTrk.CourseType3or4.ID = CourseID
                              //:          AND mDegTrk.CourseType3or4.wDegreeAuditCourseTaken = ""    
                              RESULT = mDegTrk.cursor( "CourseType3or4" ).setFirst( "RequiredSubGroup" ).toInt();
                              if ( RESULT > zCURSOR_UNCHANGED )
                              { 
                                 while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mDegTrk, "CourseType3or4", "ID", CourseID ) != 0 || CompareAttributeToString( mDegTrk, "CourseType3or4", "wDegreeAuditCourseTaken", "" ) != 0 ) )
                                 { 
                                    RESULT = mDegTrk.cursor( "CourseType3or4" ).setNextContinue().toInt();;
                                 } 

                              } 

                              //:ELSE
                           } 
                           else
                           { 
                              //:SET CURSOR FIRST mDegTrk.CourseType3or4 WITHIN mDegTrk.RequiredGroup
                              //:        WHERE mDegTrk.CourseType3or4.ID = CourseID
                              //:          AND mDegTrk.CourseType3or4.wDegreeAuditCourseTaken = ""
                              RESULT = mDegTrk.cursor( "CourseType3or4" ).setFirst( "RequiredGroup" ).toInt();
                              if ( RESULT > zCURSOR_UNCHANGED )
                              { 
                                 while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mDegTrk, "CourseType3or4", "ID", CourseID ) != 0 || CompareAttributeToString( mDegTrk, "CourseType3or4", "wDegreeAuditCourseTaken", "" ) != 0 ) )
                                 { 
                                    RESULT = mDegTrk.cursor( "CourseType3or4" ).setNextContinue().toInt();;
                                 } 

                              } 

                           } 

                           //:END
                           //:IF RESULT >= zCURSOR_SET AND mDegTrk.RequiredGroup.Type != "E"
                           if ( RESULT >= zCURSOR_SET && CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "E" ) != 0 )
                           { 
                              //:CourseType3or4Match( mDegTrk, mStudenC, mStudent )
                              omDegTrk_CourseType3or4Match( mDegTrk, mStudenC, mStudent );
                              //:SubstituteFlag = "Y"
                               {StringBuilder sb_SubstituteFlag;
                              if ( SubstituteFlag == null )
                                 sb_SubstituteFlag = new StringBuilder( 32 );
                              else
                                 sb_SubstituteFlag = new StringBuilder( SubstituteFlag );
                                                            ZeidonStringCopy( sb_SubstituteFlag, 1, 0, "Y", 1, 0, 2 );
                              SubstituteFlag = sb_SubstituteFlag.toString( );}
                              //:mStudenC.Registration.wSubstituteFlag = "Y"
                              SetAttributeFromString( mStudenC, "Registration", "wSubstituteFlag", "Y" );
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

            RESULT = mStudenC.cursor( "DegreeAuditSpecification" ).setNextContinue().toInt();;
            //:END
         } 

      } 

      RESULT = mStudenC.cursor( "Registration" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:// Next, loop through all non-substitute courses taken (or equivalency, if taken somewhere else) and indicate in
   //:// mDegTrk if the grade in the class qualifies the course for credit in mDegTrk.
   //:// We will not consider repeated courses or Developmental courses on this pass.
   //:MinimumElectiveGrade = 1       // Minimum grade is D, which has quality points of 1.
   MinimumElectiveGrade = 1;
   //:FOR EACH mStudenC.Registration 
   //:                       WHERE mStudenC.Registration.Status = "C"    // Completed
   //:                          OR mStudenC.Registration.Status = "T"    // Currently Enrolled
   //:                          OR mStudenC.Registration.Status = "V"    // Waived
   //:                          OR mStudenC.Registration.Status = "F"    // Transferred
   //:                          OR mStudenC.Registration.Status = "X"    // L. Transferred
   //:                          OR mStudenC.Registration.Status = "Y"    // L. Waived
   //:                          OR mStudenC.Registration.Status = "P"    // Pre-Transferred
   RESULT = mStudenC.cursor( "Registration" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( mStudenC, "Registration", "Status", "C" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "V" ) == 0 ||
           CompareAttributeToString( mStudenC, "Registration", "Status", "F" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "X" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "Y" ) == 0 ||
           CompareAttributeToString( mStudenC, "Registration", "Status", "P" ) == 0 )
      { 

         //:IF mStudenC.RegistrationClass EXISTS
         lTempInteger_33 = CheckExistenceOfEntity( mStudenC, "RegistrationClass" );
         if ( lTempInteger_33 == 0 )
         { 
            //:IF mStudenC.CrossListedCourse EXISTS
            lTempInteger_34 = CheckExistenceOfEntity( mStudenC, "CrossListedCourse" );
            if ( lTempInteger_34 == 0 )
            { 
               //:CourseID = mStudenC.CrossListedCourse.ID 
               {MutableInt mi_CourseID = new MutableInt( CourseID );
                               GetIntegerFromAttribute( mi_CourseID, mStudenC, "CrossListedCourse", "ID" );
               CourseID = mi_CourseID.intValue( );}
               //:ELSE
            } 
            else
            { 
               //:CourseID = mStudenC.RegistrationCourse.ID
               {MutableInt mi_CourseID = new MutableInt( CourseID );
                               GetIntegerFromAttribute( mi_CourseID, mStudenC, "RegistrationCourse", "ID" );
               CourseID = mi_CourseID.intValue( );}
            } 

            //:END
            //:RepeatableForCreditFlag = mStudenC.RegistrationCourse.RepeatableForCreditFlag 
            {MutableInt mi_lTempInteger_35 = new MutableInt( lTempInteger_35 );
            StringBuilder sb_RepeatableForCreditFlag;
            if ( RepeatableForCreditFlag == null )
               sb_RepeatableForCreditFlag = new StringBuilder( 32 );
            else
               sb_RepeatableForCreditFlag = new StringBuilder( RepeatableForCreditFlag );
                         GetVariableFromAttribute( sb_RepeatableForCreditFlag, mi_lTempInteger_35, 'S', 2, mStudenC, "RegistrationCourse", "RepeatableForCreditFlag", "", 0 );
            lTempInteger_35 = mi_lTempInteger_35.intValue( );
            RepeatableForCreditFlag = sb_RepeatableForCreditFlag.toString( );}
            //:DevelopmentalFlag       = mStudenC.RegistrationCourse.DevelopmentalFlag 
            {MutableInt mi_lTempInteger_36 = new MutableInt( lTempInteger_36 );
            StringBuilder sb_DevelopmentalFlag;
            if ( DevelopmentalFlag == null )
               sb_DevelopmentalFlag = new StringBuilder( 32 );
            else
               sb_DevelopmentalFlag = new StringBuilder( DevelopmentalFlag );
                         GetVariableFromAttribute( sb_DevelopmentalFlag, mi_lTempInteger_36, 'S', 2, mStudenC, "RegistrationCourse", "DevelopmentalFlag", "", 0 );
            lTempInteger_36 = mi_lTempInteger_36.intValue( );
            DevelopmentalFlag = sb_DevelopmentalFlag.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mStudenC.EquivalentCourse EXISTS
            lTempInteger_37 = CheckExistenceOfEntity( mStudenC, "EquivalentCourse" );
            if ( lTempInteger_37 == 0 )
            { 
               //:CourseID                = mStudenC.EquivalentCourse.ID
               {MutableInt mi_CourseID = new MutableInt( CourseID );
                               GetIntegerFromAttribute( mi_CourseID, mStudenC, "EquivalentCourse", "ID" );
               CourseID = mi_CourseID.intValue( );}
               //:RepeatableForCreditFlag = mStudenC.EquivalentCourse.RepeatableForCreditFlag 
               {MutableInt mi_lTempInteger_38 = new MutableInt( lTempInteger_38 );
               StringBuilder sb_RepeatableForCreditFlag;
               if ( RepeatableForCreditFlag == null )
                  sb_RepeatableForCreditFlag = new StringBuilder( 32 );
               else
                  sb_RepeatableForCreditFlag = new StringBuilder( RepeatableForCreditFlag );
                               GetVariableFromAttribute( sb_RepeatableForCreditFlag, mi_lTempInteger_38, 'S', 2, mStudenC, "EquivalentCourse", "RepeatableForCreditFlag", "", 0 );
               lTempInteger_38 = mi_lTempInteger_38.intValue( );
               RepeatableForCreditFlag = sb_RepeatableForCreditFlag.toString( );}
               //:DevelopmentalFlag       = ""
                {StringBuilder sb_DevelopmentalFlag;
               if ( DevelopmentalFlag == null )
                  sb_DevelopmentalFlag = new StringBuilder( 32 );
               else
                  sb_DevelopmentalFlag = new StringBuilder( DevelopmentalFlag );
                              ZeidonStringCopy( sb_DevelopmentalFlag, 1, 0, "", 1, 0, 2 );
               DevelopmentalFlag = sb_DevelopmentalFlag.toString( );}
               //:ELSE
            } 
            else
            { 
               //:CourseID = 0
               CourseID = 0;
            } 

            //:END
         } 

         //:END
         //:GetStringFromAttributeByContext( StudentStatus, mStudenC, "Registration", "Status", "", 20 )
         {StringBuilder sb_StudentStatus;
         if ( StudentStatus == null )
            sb_StudentStatus = new StringBuilder( 32 );
         else
            sb_StudentStatus = new StringBuilder( StudentStatus );
                   GetStringFromAttributeByContext( sb_StudentStatus, mStudenC, "Registration", "Status", "", 20 );
         StudentStatus = sb_StudentStatus.toString( );}
         //:// Skip repeated courses, Developmental courses or substituted courses.
         //:IF mStudenC.Registration.wRepeatedClass = "" AND
         //://DevelopmentalFlag = "" AND 
         //:mStudenC.Registration.wSubstituteFlag != "Y"
         if ( CompareAttributeToString( mStudenC, "Registration", "wRepeatedClass", "" ) == 0 && CompareAttributeToString( mStudenC, "Registration", "wSubstituteFlag", "Y" ) != 0 )
         { 

            //:GetStringFromAttributeByContext( szFinalGradeString,
            //:                              mStudenC, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 )
            {StringBuilder sb_szFinalGradeString;
            if ( szFinalGradeString == null )
               sb_szFinalGradeString = new StringBuilder( 32 );
            else
               sb_szFinalGradeString = new StringBuilder( szFinalGradeString );
                         GetStringFromAttributeByContext( sb_szFinalGradeString, mStudenC, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 );
            szFinalGradeString = sb_szFinalGradeString.toString( );}
            //:FinalGrade = StrToDecimal( szFinalGradeString )   // FinalGrade is in "Quality Points".
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mDegTrk );
             FinalGrade = m_ZGLOBAL1_Operation.StrToDecimal( szFinalGradeString );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:szFinalGradeString = mStudenC.Registration.FinalGrade     // Get grade again as normal string.
            {MutableInt mi_lTempInteger_39 = new MutableInt( lTempInteger_39 );
            StringBuilder sb_szFinalGradeString;
            if ( szFinalGradeString == null )
               sb_szFinalGradeString = new StringBuilder( 32 );
            else
               sb_szFinalGradeString = new StringBuilder( szFinalGradeString );
                         GetVariableFromAttribute( sb_szFinalGradeString, mi_lTempInteger_39, 'S', 21, mStudenC, "Registration", "FinalGrade", "", 0 );
            lTempInteger_39 = mi_lTempInteger_39.intValue( );
            szFinalGradeString = sb_szFinalGradeString.toString( );}

            //:// Process Course ID just selected if it has not already been assigned as a substitue Course.
            //:// How we try to position on a particular Course entry depends on whether or not the Course is repeatable and
            //:// whether the Course entry has been selected to be assigned to a particular Group or a particular Course within
            //:// a Group..

            //:IF mStudenC.Registration.wSubstituteFlag = ""
            if ( CompareAttributeToString( mStudenC, "Registration", "wSubstituteFlag", "" ) == 0 )
            { 
               //:IF mStudenC.Registration.TakingClassType = "A"
               if ( CompareAttributeToString( mStudenC, "Registration", "TakingClassType", "A" ) == 0 )
               { 
                  //:// Course is being audited, so add to Elective group.
                  //:CreateElectiveEntry( mDegTrk, mStudenC, mStudent )
                  omDegTrk_CreateElectiveEntry( mDegTrk, mStudenC, mStudent );
                  //:mDegTrk.CourseType1or2.wCreditsTaken = 0
                  SetAttributeFromInteger( mDegTrk, "CourseType1or2", "wCreditsTaken", 0 );
                  //:IF mStudenC.Registration.Status = "C"
                  if ( CompareAttributeToString( mStudenC, "Registration", "Status", "C" ) == 0 )
                  { 
                     //:mDegTrk.CourseType1or2.wCourseStatus = "Audited"
                     SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", "Audited" );
                     //:ELSE
                  } 
                  else
                  { 
                     //:mDegTrk.CourseType1or2.wCourseStatus = "Auditing"
                     SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", "Auditing" );
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:IF RepeatableForCreditFlag = "Y"
                  if ( ZeidonStringCompare( RepeatableForCreditFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
                  { 
                     //:// For Repeatable Course, we don't care about whether we've already processed the entry.
                     //:SET CURSOR FIRST mDegTrk.CourseType1or2 WITHIN mDegTrk.DegreeTrack
                     //:        WHERE mDegTrk.CourseType1or2.ID = CourseID
                     RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "ID", CourseID, "DegreeTrack" ).toInt();
                     //:// If the RequiredGroup is Type 1, then we do care about positioning on an entry that we've not yet processed.
                     //:IF mDegTrk.RequiredGroup.Type = "1"
                     if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "1" ) == 0 )
                     { 
                        //:SET CURSOR FIRST mDegTrk.CourseType1or2 WITHIN mDegTrk.DegreeTrack
                        //:        WHERE mDegTrk.CourseType1or2.ID = CourseID
                        //:          AND mDegTrk.CourseType1or2.wDegreeAuditCourseTaken = ""
                        RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "DegreeTrack" ).toInt();
                        if ( RESULT > zCURSOR_UNCHANGED )
                        { 
                           while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mDegTrk, "CourseType1or2", "ID", CourseID ) != 0 || CompareAttributeToString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseTaken", "" ) != 0 ) )
                           { 
                              RESULT = mDegTrk.cursor( "CourseType1or2" ).setNextContinue().toInt();;
                           } 

                        } 

                     } 

                     //:END
                     //:ELSE
                  } 
                  else
                  { 
                     //:// For non-Repeatable Course, we DO care about whether we've already processed the entry.
                     //:SET CURSOR FIRST mDegTrk.CourseType1or2 WITHIN mDegTrk.DegreeTrack
                     //:        WHERE mDegTrk.CourseType1or2.ID = CourseID
                     //:          AND mDegTrk.CourseType1or2.wDegreeAuditCourseTaken = ""
                     RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "DegreeTrack" ).toInt();
                     if ( RESULT > zCURSOR_UNCHANGED )
                     { 
                        while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mDegTrk, "CourseType1or2", "ID", CourseID ) != 0 || CompareAttributeToString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseTaken", "" ) != 0 ) )
                        { 
                           RESULT = mDegTrk.cursor( "CourseType1or2" ).setNextContinue().toInt();;
                        } 

                     } 

                  } 

                  //:END

                  //:IF RESULT >= zCURSOR_SET AND mDegTrk.RequiredGroup.Type != "E"
                  if ( RESULT >= zCURSOR_SET && CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "E" ) != 0 )
                  { 
                     //:// There was a match on CourseType1or2. Go process entry.
                     //:CourseType1or2Match( mDegTrk, mStudenC, mStudent )
                     omDegTrk_CourseType1or2Match( mDegTrk, mStudenC, mStudent );
                     //:ELSE
                  } 
                  else
                  { 
                     //:// There wasn't a match on CourseType1or2, so try CourseType3or4.
                     //:IF RepeatableForCreditFlag = "Y"
                     if ( ZeidonStringCompare( RepeatableForCreditFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
                     { 
                        //:// For Repeatable Course, we don't care about whether we've already processed the entry.
                        //:SET CURSOR FIRST mDegTrk.CourseType3or4 WITHIN mDegTrk.DegreeTrack
                        //:        WHERE mDegTrk.CourseType3or4.ID = CourseID
                        RESULT = mDegTrk.cursor( "CourseType3or4" ).setFirst( "ID", CourseID, "DegreeTrack" ).toInt();
                        //:ELSE
                     } 
                     else
                     { 
                        //:// For non-Repeatable Course, we DO care about whether we've already processed the entry.
                        //:SET CURSOR FIRST mDegTrk.CourseType3or4 WITHIN mDegTrk.DegreeTrack
                        //:        WHERE mDegTrk.CourseType3or4.ID = CourseID
                        //:          AND mDegTrk.CourseType3or4.wDegreeAuditCourseTaken = ""
                        RESULT = mDegTrk.cursor( "CourseType3or4" ).setFirst( "DegreeTrack" ).toInt();
                        if ( RESULT > zCURSOR_UNCHANGED )
                        { 
                           while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mDegTrk, "CourseType3or4", "ID", CourseID ) != 0 || CompareAttributeToString( mDegTrk, "CourseType3or4", "wDegreeAuditCourseTaken", "" ) != 0 ) )
                           { 
                              RESULT = mDegTrk.cursor( "CourseType3or4" ).setNextContinue().toInt();;
                           } 

                        } 

                     } 

                     //:END
                     //:IF RESULT >= zCURSOR_SET AND mDegTrk.RequiredGroup.Type != "E"
                     if ( RESULT >= zCURSOR_SET && CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "E" ) != 0 )
                     { 
                        //:// There was a match on CourseType3or4. Go process entry.
                        //:CourseType3or4Match( mDegTrk, mStudenC, mStudent )
                        omDegTrk_CourseType3or4Match( mDegTrk, mStudenC, mStudent );
                        //:ELSE
                     } 
                     else
                     { 
                        //:// There was no match with any of the Required Groups, so add to Electives or general Developmental group.
                        //:// For "not-waived courses", if the course wasn't passed  we'll override the CreditHours to 0.
                        //:IF DevelopmentalFlag = "Y" 
                        if ( ZeidonStringCompare( DevelopmentalFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
                        { 
                           //:CreateDevelopmentEntry( mDegTrk, mStudenC, mStudent )
                           omDegTrk_CreateDevelopmentEntry( mDegTrk, mStudenC, mStudent );
                           //:ELSE
                        } 
                        else
                        { 
                           //:CreateElectiveEntry( mDegTrk, mStudenC, mStudent )
                           omDegTrk_CreateElectiveEntry( mDegTrk, mStudenC, mStudent );
                           //:IF mStudenC.Registration.Status != "V" AND   // Waived
                           //:mStudenC.Registration.Status != "Y"       // L. Waived 
                           if ( CompareAttributeToString( mStudenC, "Registration", "Status", "V" ) != 0 && CompareAttributeToString( mStudenC, "Registration", "Status", "Y" ) != 0 )
                           { 

                              //:IF FinalGrade < MinimumElectiveGrade AND szFinalGradeString != "S" AND szFinalGradeString != ""
                              if ( FinalGrade < MinimumElectiveGrade && ZeidonStringCompare( szFinalGradeString, 1, 0, "S", 1, 0, 21 ) != 0 && ZeidonStringCompare( szFinalGradeString, 1, 0, "", 1, 0, 21 ) != 0 )
                              { 
                                 //:mDegTrk.CourseType1or2.wCreditsTaken = 0
                                 SetAttributeFromInteger( mDegTrk, "CourseType1or2", "wCreditsTaken", 0 );
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

      } 

      RESULT = mStudenC.cursor( "Registration" ).setNextContinue().toInt();;
      //:      
      //:END
   } 

   //:END

   //:// Make another pass and add any repeated Courses to the entry representing the superceding Course.
   //:// We will duplicate the original line and show as "Repeated" with 0 Credits.
   //:FOR EACH mStudenC.Registration 
   //:                          WHERE mStudenC.Registration.Status = "C"    // Completed
   //:                             OR mStudenC.Registration.Status = "T"    // Currently Taking
   //:                             OR mStudenC.Registration.Status = "V"    // Waived
   //:                             OR mStudenC.Registration.Status = "F"    // Transferred
   //:                             OR mStudenC.Registration.Status = "X"    // L. Transferred
   //:                             OR mStudenC.Registration.Status = "Y"    // L. Waived
   //:                             OR mStudenC.Registration.Status = "P"    // Pre-Transferred
   RESULT = mStudenC.cursor( "Registration" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( mStudenC, "Registration", "Status", "C" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "V" ) == 0 ||
           CompareAttributeToString( mStudenC, "Registration", "Status", "F" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "X" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "Y" ) == 0 ||
           CompareAttributeToString( mStudenC, "Registration", "Status", "P" ) == 0 )
      { 

         //:IF mStudenC.Registration.wRepeatedClass = "R" OR mStudenC.Registration.wRepeatedClass = "AR"
         if ( CompareAttributeToString( mStudenC, "Registration", "wRepeatedClass", "R" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "wRepeatedClass", "AR" ) == 0 )
         { 
            //:IF mStudenC.RegistrationClass EXISTS
            lTempInteger_40 = CheckExistenceOfEntity( mStudenC, "RegistrationClass" );
            if ( lTempInteger_40 == 0 )
            { 
               //:IF mStudenC.Registration.ManuallyRepeatingRegistrationID = ""
               if ( CompareAttributeToString( mStudenC, "Registration", "ManuallyRepeatingRegistrationID", "" ) == 0 )
               { 
                  //:IF mStudenC.CrossListedCourse EXISTS
                  lTempInteger_41 = CheckExistenceOfEntity( mStudenC, "CrossListedCourse" );
                  if ( lTempInteger_41 == 0 )
                  { 
                     //:CourseID = mStudenC.CrossListedCourse.ID 
                     {MutableInt mi_CourseID = new MutableInt( CourseID );
                                           GetIntegerFromAttribute( mi_CourseID, mStudenC, "CrossListedCourse", "ID" );
                     CourseID = mi_CourseID.intValue( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:CourseID = mStudenC.RegistrationCourse.ID
                     {MutableInt mi_CourseID = new MutableInt( CourseID );
                                           GetIntegerFromAttribute( mi_CourseID, mStudenC, "RegistrationCourse", "ID" );
                     CourseID = mi_CourseID.intValue( );}
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:CreateViewFromView( mStudenCT, mStudenC )
                  CreateViewFromView( mStudenCT, mStudenC );
                  //:SET CURSOR FIRST mStudenCT.Registration WHERE 
                  //:              mStudenCT.Registration.ID = mStudenC.Registration.ManuallyRepeatingRegistrationID 
                  {MutableInt mi_lTempInteger_42 = new MutableInt( lTempInteger_42 );
                                     GetIntegerFromAttribute( mi_lTempInteger_42, mStudenC, "Registration", "ManuallyRepeatingRegistrationID" );
                  lTempInteger_42 = mi_lTempInteger_42.intValue( );}
                  RESULT = mStudenCT.cursor( "Registration" ).setFirst( "ID", lTempInteger_42 ).toInt();
                  //:IF mStudenCT.EquivalentCourse EXISTS
                  lTempInteger_43 = CheckExistenceOfEntity( mStudenCT, "EquivalentCourse" );
                  if ( lTempInteger_43 == 0 )
                  { 
                     //:CourseID = mStudenCT.EquivalentCourse.ID 
                     {MutableInt mi_CourseID = new MutableInt( CourseID );
                                           GetIntegerFromAttribute( mi_CourseID, mStudenCT, "EquivalentCourse", "ID" );
                     CourseID = mi_CourseID.intValue( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF mStudenCT.CrossListedCourse EXISTS
                     lTempInteger_44 = CheckExistenceOfEntity( mStudenCT, "CrossListedCourse" );
                     if ( lTempInteger_44 == 0 )
                     { 
                        //:CourseID = mStudenCT.CrossListedCourse.ID 
                        {MutableInt mi_CourseID = new MutableInt( CourseID );
                                                 GetIntegerFromAttribute( mi_CourseID, mStudenCT, "CrossListedCourse", "ID" );
                        CourseID = mi_CourseID.intValue( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:CourseID = mStudenCT.RegistrationCourse.ID
                        {MutableInt mi_CourseID = new MutableInt( CourseID );
                                                 GetIntegerFromAttribute( mi_CourseID, mStudenCT, "RegistrationCourse", "ID" );
                        CourseID = mi_CourseID.intValue( );}
                     } 

                     //:END
                  } 

                  //:END
                  //:DropView( mStudenCT )
                  DropView( mStudenCT );
               } 

               //:END
               //:CourseNumber = mStudenC.RegistrationCourse.Number 
               {MutableInt mi_lTempInteger_45 = new MutableInt( lTempInteger_45 );
               StringBuilder sb_CourseNumber;
               if ( CourseNumber == null )
                  sb_CourseNumber = new StringBuilder( 32 );
               else
                  sb_CourseNumber = new StringBuilder( CourseNumber );
                               GetVariableFromAttribute( sb_CourseNumber, mi_lTempInteger_45, 'S', 11, mStudenC, "RegistrationCourse", "Number", "", 0 );
               lTempInteger_45 = mi_lTempInteger_45.intValue( );
               CourseNumber = sb_CourseNumber.toString( );}
               //:IF mStudenC.CourseTopic EXISTS
               lTempInteger_46 = CheckExistenceOfEntity( mStudenC, "CourseTopic" );
               if ( lTempInteger_46 == 0 )
               { 
                  //:CourseTitle  = mStudenC.CourseTopic.Title 
                  {MutableInt mi_lTempInteger_47 = new MutableInt( lTempInteger_47 );
                  StringBuilder sb_CourseTitle;
                  if ( CourseTitle == null )
                     sb_CourseTitle = new StringBuilder( 32 );
                  else
                     sb_CourseTitle = new StringBuilder( CourseTitle );
                                     GetVariableFromAttribute( sb_CourseTitle, mi_lTempInteger_47, 'S', 51, mStudenC, "CourseTopic", "Title", "", 0 );
                  lTempInteger_47 = mi_lTempInteger_47.intValue( );
                  CourseTitle = sb_CourseTitle.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:CourseTitle  = mStudenC.RegistrationCourse.Title 
                  {MutableInt mi_lTempInteger_48 = new MutableInt( lTempInteger_48 );
                  StringBuilder sb_CourseTitle;
                  if ( CourseTitle == null )
                     sb_CourseTitle = new StringBuilder( 32 );
                  else
                     sb_CourseTitle = new StringBuilder( CourseTitle );
                                     GetVariableFromAttribute( sb_CourseTitle, mi_lTempInteger_48, 'S', 51, mStudenC, "RegistrationCourse", "Title", "", 0 );
                  lTempInteger_48 = mi_lTempInteger_48.intValue( );
                  CourseTitle = sb_CourseTitle.toString( );}
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF mStudenC.EquivalentCourse EXISTS
               lTempInteger_49 = CheckExistenceOfEntity( mStudenC, "EquivalentCourse" );
               if ( lTempInteger_49 == 0 )
               { 
                  //:CourseID     = mStudenC.EquivalentCourse.ID 
                  {MutableInt mi_CourseID = new MutableInt( CourseID );
                                     GetIntegerFromAttribute( mi_CourseID, mStudenC, "EquivalentCourse", "ID" );
                  CourseID = mi_CourseID.intValue( );}
                  //:CourseNumber = mStudenC.EquivalentCourse.Number 
                  {MutableInt mi_lTempInteger_50 = new MutableInt( lTempInteger_50 );
                  StringBuilder sb_CourseNumber;
                  if ( CourseNumber == null )
                     sb_CourseNumber = new StringBuilder( 32 );
                  else
                     sb_CourseNumber = new StringBuilder( CourseNumber );
                                     GetVariableFromAttribute( sb_CourseNumber, mi_lTempInteger_50, 'S', 11, mStudenC, "EquivalentCourse", "Number", "", 0 );
                  lTempInteger_50 = mi_lTempInteger_50.intValue( );
                  CourseNumber = sb_CourseNumber.toString( );}
                  //:CourseTitle  = mStudenC.EquivalentCourse.Title 
                  {MutableInt mi_lTempInteger_51 = new MutableInt( lTempInteger_51 );
                  StringBuilder sb_CourseTitle;
                  if ( CourseTitle == null )
                     sb_CourseTitle = new StringBuilder( 32 );
                  else
                     sb_CourseTitle = new StringBuilder( CourseTitle );
                                     GetVariableFromAttribute( sb_CourseTitle, mi_lTempInteger_51, 'S', 51, mStudenC, "EquivalentCourse", "Title", "", 0 );
                  lTempInteger_51 = mi_lTempInteger_51.intValue( );
                  CourseTitle = sb_CourseTitle.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:CourseID = 0
                  CourseID = 0;
               } 

               //:END
            } 

            //:END

            //:IF CourseID != 0
            if ( CourseID != 0 )
            { 
               //:SET CURSOR FIRST mDegTrk.CourseType1or2 WITHIN mDegTrk.DegreeTrack
               //:        WHERE mDegTrk.CourseType1or2.wCourseSatisfyingID = CourseID
               RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "wCourseSatisfyingID", CourseID, "DegreeTrack" ).toInt();
               //:        //WHERE mDegTrk.CourseType1or2.ID = CourseID
               //:        //  AND mDegTrk.CourseType1or2.wDegreeAuditCourseTaken = "Y"
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:// Repeated course for CourseType1or2
                  //:CREATE ENTITY mDegTrk.RequiredGroupCourseType1or2 
                  RESULT = CreateEntity( mDegTrk, "RequiredGroupCourseType1or2", zPOS_AFTER );
                  //:CREATE ENTITY mDegTrk.CourseType1or2
                  RESULT = CreateEntity( mDegTrk, "CourseType1or2", zPOS_AFTER );
                  //:mDegTrk.CourseType1or2.ID     = CourseID
                  SetAttributeFromInteger( mDegTrk, "CourseType1or2", "ID", CourseID );
                  //:mDegTrk.CourseType1or2.Number = CourseNumber
                  SetAttributeFromString( mDegTrk, "CourseType1or2", "Number", CourseNumber );
                  //:mDegTrk.CourseType1or2.Title  = CourseTitle
                  SetAttributeFromString( mDegTrk, "CourseType1or2", "Title", CourseTitle );
                  //:mDegTrk.CourseType1or2.wCourseStatus = "Repeated"
                  SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", "Repeated" );
                  //:mDegTrk.CourseType1or2.wFinalGrade   = mStudenC.Registration.FinalGrade
                  SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wFinalGrade", mStudenC, "Registration", "FinalGrade" );
                  //:mDegTrk.CourseType1or2.wCreditsTaken = 0
                  SetAttributeFromInteger( mDegTrk, "CourseType1or2", "wCreditsTaken", 0 );
                  //:mDegTrk.CourseType1or2.wRegistrationSatisfyingID = mStudenC.Registration.ID
                  SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wRegistrationSatisfyingID", mStudenC, "Registration", "ID" );
                  //:ELSE
               } 
               else
               { 
                  //:SET CURSOR FIRST mDegTrk.CourseType3or4 WITHIN mDegTrk.DegreeTrack
                  //:        WHERE mDegTrk.CourseType3or4.wCourseSatisfyingID = CourseID
                  RESULT = mDegTrk.cursor( "CourseType3or4" ).setFirst( "wCourseSatisfyingID", CourseID, "DegreeTrack" ).toInt();
                  //:        //WHERE mDegTrk.CourseType3or4.ID = CourseID
                  //:        //  AND mDegTrk.CourseType3or4.wDegreeAuditCourseTaken = "Y"
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:// Repeated course for CourseType3or4
                     //:CREATE ENTITY mDegTrk.RequiredGroupCourseType3or4 
                     RESULT = CreateEntity( mDegTrk, "RequiredGroupCourseType3or4", zPOS_AFTER );
                     //:CREATE ENTITY mDegTrk.CourseType3or4
                     RESULT = CreateEntity( mDegTrk, "CourseType3or4", zPOS_AFTER );
                     //:mDegTrk.CourseType3or4.ID     = CourseID
                     SetAttributeFromInteger( mDegTrk, "CourseType3or4", "ID", CourseID );
                     //:mDegTrk.CourseType3or4.Number = CourseNumber
                     SetAttributeFromString( mDegTrk, "CourseType3or4", "Number", CourseNumber );
                     //:mDegTrk.CourseType3or4.Title  = CourseTitle
                     SetAttributeFromString( mDegTrk, "CourseType3or4", "Title", CourseTitle );
                     //:mDegTrk.CourseType3or4.wCourseStatus = "Repeated"
                     SetAttributeFromString( mDegTrk, "CourseType3or4", "wCourseStatus", "Repeated" );
                     //:mDegTrk.CourseType3or4.wFinalGrade   = mStudenC.Registration.FinalGrade
                     SetAttributeFromAttribute( mDegTrk, "CourseType3or4", "wFinalGrade", mStudenC, "Registration", "FinalGrade" );
                     //:mDegTrk.CourseType3or4.wCreditsTaken = 0
                     SetAttributeFromInteger( mDegTrk, "CourseType3or4", "wCreditsTaken", 0 );
                     //:mDegTrk.CourseType3or4.wRegistrationSatisfyingID = mStudenC.Registration.ID
                     SetAttributeFromAttribute( mDegTrk, "CourseType3or4", "wRegistrationSatisfyingID", mStudenC, "Registration", "ID" );
                     //:ELSE
                  } 
                  else
                  { 
                     //:SET CURSOR FIRST mDegTrk.RequiredGroup WITHIN mDegTrk.DegreeTrack 
                     //:        WHERE mDegTrk.RequiredGroup.Type = "D"
                     RESULT = mDegTrk.cursor( "RequiredGroup" ).setFirst( "Type", "D", "DegreeTrack" ).toInt();
                     //:SET CURSOR FIRST mDegTrk.CourseType1or2 WITHIN mDegTrk.RequiredGroup
                     //:        WHERE mDegTrk.CourseType1or2.ID = CourseID
                     RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "ID", CourseID, "RequiredGroup" ).toInt();
                     //:IF RESULT >= zCURSOR_SET
                     if ( RESULT >= zCURSOR_SET )
                     { 
                        //:// Repeated course for Development course
                        //:CREATE ENTITY mDegTrk.RequiredGroupCourseType1or2 
                        RESULT = CreateEntity( mDegTrk, "RequiredGroupCourseType1or2", zPOS_AFTER );
                        //:CREATE ENTITY mDegTrk.CourseType1or2
                        RESULT = CreateEntity( mDegTrk, "CourseType1or2", zPOS_AFTER );
                        //:mDegTrk.CourseType1or2.ID     = CourseID                         
                        SetAttributeFromInteger( mDegTrk, "CourseType1or2", "ID", CourseID );
                        //:mDegTrk.CourseType1or2.Number = CourseNumber
                        SetAttributeFromString( mDegTrk, "CourseType1or2", "Number", CourseNumber );
                        //:mDegTrk.CourseType1or2.Title  = CourseTitle 
                        SetAttributeFromString( mDegTrk, "CourseType1or2", "Title", CourseTitle );
                        //:mDegTrk.CourseType1or2.wCourseStatus = "Repeated"
                        SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", "Repeated" );
                        //:mDegTrk.CourseType1or2.wFinalGrade   = mStudenC.Registration.FinalGrade
                        SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wFinalGrade", mStudenC, "Registration", "FinalGrade" );
                        //:mDegTrk.CourseType1or2.wCreditsTaken = 0
                        SetAttributeFromInteger( mDegTrk, "CourseType1or2", "wCreditsTaken", 0 );
                        //:mDegTrk.CourseType1or2.wRegistrationSatisfyingID = mStudenC.Registration.ID
                        SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wRegistrationSatisfyingID", mStudenC, "Registration", "ID" );
                        //:ELSE
                     } 
                     else
                     { 
                        //:// Duplicate entry where the CourseID does not match.
                        //:Msg = "There is a duplicate Course Number or otherwise erroneous Course entry for " + 
                        //:   CourseNumber  + NEW_LINE +
                        //:   "The repeated entry for the Course is not being considered in the Audit."
                         {StringBuilder sb_Msg;
                        if ( Msg == null )
                           sb_Msg = new StringBuilder( 32 );
                        else
                           sb_Msg = new StringBuilder( Msg );
                                                ZeidonStringCopy( sb_Msg, 1, 0, "There is a duplicate Course Number or otherwise erroneous Course entry for ", 1, 0, 201 );
                        Msg = sb_Msg.toString( );}
                         {StringBuilder sb_Msg;
                        if ( Msg == null )
                           sb_Msg = new StringBuilder( 32 );
                        else
                           sb_Msg = new StringBuilder( Msg );
                                                ZeidonStringConcat( sb_Msg, 1, 0, CourseNumber, 1, 0, 201 );
                        Msg = sb_Msg.toString( );}
                         {StringBuilder sb_Msg;
                        if ( Msg == null )
                           sb_Msg = new StringBuilder( 32 );
                        else
                           sb_Msg = new StringBuilder( Msg );
                                                ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 201 );
                        Msg = sb_Msg.toString( );}
                         {StringBuilder sb_Msg;
                        if ( Msg == null )
                           sb_Msg = new StringBuilder( 32 );
                        else
                           sb_Msg = new StringBuilder( Msg );
                                                ZeidonStringConcat( sb_Msg, 1, 0, "The repeated entry for the Course is not being considered in the Audit.", 1, 0, 201 );
                        Msg = sb_Msg.toString( );}
                        //:MessageSend( mStudenC, "", "Degree Audit", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                        MessageSend( mStudenC, "", "Degree Audit", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
         } 

      } 

      RESULT = mStudenC.cursor( "Registration" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:// Evaluate the courses that have been flagged against the degree audit requirements.
   //:TotalDegreeTrackCreditsTaken = 0
   TotalDegreeTrackCreditsTaken = 0;
   //:TotalDegreeTrackCreditsEarned = 0
   TotalDegreeTrackCreditsEarned = 0;
   //:FOR EACH mDegTrk.RequiredGroup WITHIN mDegTrk.DegreeTrack 
   RESULT = mDegTrk.cursor( "RequiredGroup" ).setFirst( "DegreeTrack" ).toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //: // Group Type 1: All courses are required.

      //: IF mDegTrk.RequiredGroup.Type = "1"
      if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "1" ) == 0 )
      { 

         //: CourseCount          = 0
         CourseCount = 0;
         //: CourseTakenCount     = 0
         CourseTakenCount = 0;
         //: CourseCompletedCount = 0
         CourseCompletedCount = 0;
         //: CreditsTaken         = 0
         CreditsTaken = 0;
         //: FOR EACH mDegTrk.CourseType1or2 WITHIN mDegTrk.RequiredGroup 
         RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "RequiredGroup" ).toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //: IF mDegTrk.CourseType1or2.wCourseStatus != "Repeated"
            if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "wCourseStatus", "Repeated" ) != 0 )
            { 
               //: CourseCount = CourseCount + 1
               CourseCount = CourseCount + 1;
               //: IF mDegTrk.CourseType1or2.wDegreeAuditCourseTaken = "Y"
               if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseTaken", "Y" ) == 0 )
               { 
                  //: CourseTakenCount = CourseTakenCount + 1
                  CourseTakenCount = CourseTakenCount + 1;
                  //: CreditsTaken = CreditsTaken + mDegTrk.CourseType1or2.wCreditsTaken 
                  {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                     GetDecimalFromAttribute( md_dTempDecimal_0, mDegTrk, "CourseType1or2", "wCreditsTaken" );
                  dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                  CreditsTaken = CreditsTaken + dTempDecimal_0;
               } 

               //: END
               //: TotalDegreeTrackCreditsTaken = TotalDegreeTrackCreditsTaken + mDegTrk.CourseType1or2.wCreditsTaken
               {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                               GetDecimalFromAttribute( md_dTempDecimal_1, mDegTrk, "CourseType1or2", "wCreditsTaken" );
               dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
               TotalDegreeTrackCreditsTaken = TotalDegreeTrackCreditsTaken + dTempDecimal_1;
               //: IF mDegTrk.CourseType1or2.wDegreeAuditCourseCompleted = "Y"
               if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseCompleted", "Y" ) == 0 )
               { 
                  //: CourseCompletedCount = CourseCompletedCount + 1
                  CourseCompletedCount = CourseCompletedCount + 1;
                  //: TotalDegreeTrackCreditsEarned = TotalDegreeTrackCreditsEarned + mDegTrk.CourseType1or2.wCreditsTaken
                  {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                                     GetDecimalFromAttribute( md_dTempDecimal_2, mDegTrk, "CourseType1or2", "wCreditsTaken" );
                  dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
                  TotalDegreeTrackCreditsEarned = TotalDegreeTrackCreditsEarned + dTempDecimal_2;
               } 

               //: END
            } 

            RESULT = mDegTrk.cursor( "CourseType1or2" ).setNextContinue().toInt();;
            //: END
         } 

         //: END
         //: szCourseTakenCount = CourseTakenCount
          {StringBuilder sb_szCourseTakenCount;
         if ( szCourseTakenCount == null )
            sb_szCourseTakenCount = new StringBuilder( 32 );
         else
            sb_szCourseTakenCount = new StringBuilder( szCourseTakenCount );
                  ZeidonStringConvertFromNumber( sb_szCourseTakenCount, 1, 0, 5, CourseTakenCount, (double) 0.0, "I" );
         szCourseTakenCount = sb_szCourseTakenCount.toString( );}
         //: szCourseCount      = CourseCount
          {StringBuilder sb_szCourseCount;
         if ( szCourseCount == null )
            sb_szCourseCount = new StringBuilder( 32 );
         else
            sb_szCourseCount = new StringBuilder( szCourseCount );
                  ZeidonStringConvertFromNumber( sb_szCourseCount, 1, 0, 5, CourseCount, (double) 0.0, "I" );
         szCourseCount = sb_szCourseCount.toString( );}
         //: IF mDegTrk.RequiredGroup.wDegreeAuditHasTakingFlag = "Y"
         if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "wDegreeAuditHasTakingFlag", "Y" ) == 0 )
         { 
            //: mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.dReportValue +
            //:                  "  (" + szCourseTakenCount + " of " + szCourseCount + " courses completed/enrolled)"
            {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetStringFromAttribute( sb_szTempString_2, mDegTrk, "RequiredGroup", "dReportValue" );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringConcat( sb_szTempString_2, 1, 0, "  (", 1, 0, 255 );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringConcat( sb_szTempString_2, 1, 0, szCourseTakenCount, 1, 0, 255 );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringConcat( sb_szTempString_2, 1, 0, " of ", 1, 0, 255 );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringConcat( sb_szTempString_2, 1, 0, szCourseCount, 1, 0, 255 );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringConcat( sb_szTempString_2, 1, 0, " courses completed/enrolled)", 1, 0, 255 );
            szTempString_2 = sb_szTempString_2.toString( );}
            SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", szTempString_2 );
            //:ELSE
         } 
         else
         { 
            //: mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.dReportValue +
            //:                  "  (" + szCourseTakenCount + " of " + szCourseCount + " courses completed)"
            {StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                         GetStringFromAttribute( sb_szTempString_3, mDegTrk, "RequiredGroup", "dReportValue" );
            szTempString_3 = sb_szTempString_3.toString( );}
             {StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                        ZeidonStringConcat( sb_szTempString_3, 1, 0, "  (", 1, 0, 255 );
            szTempString_3 = sb_szTempString_3.toString( );}
             {StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                        ZeidonStringConcat( sb_szTempString_3, 1, 0, szCourseTakenCount, 1, 0, 255 );
            szTempString_3 = sb_szTempString_3.toString( );}
             {StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                        ZeidonStringConcat( sb_szTempString_3, 1, 0, " of ", 1, 0, 255 );
            szTempString_3 = sb_szTempString_3.toString( );}
             {StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                        ZeidonStringConcat( sb_szTempString_3, 1, 0, szCourseCount, 1, 0, 255 );
            szTempString_3 = sb_szTempString_3.toString( );}
             {StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                        ZeidonStringConcat( sb_szTempString_3, 1, 0, " courses completed)", 1, 0, 255 );
            szTempString_3 = sb_szTempString_3.toString( );}
            SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", szTempString_3 );
         } 

         //: END 
         //: IF CourseCompletedCount >= CourseCount
         if ( CourseCompletedCount >= CourseCount )
         { 
            //: mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "C"
            SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "C" );
            //:ELSE
         } 
         else
         { 
            //: IF CourseTakenCount >= CourseCount
            if ( CourseTakenCount >= CourseCount )
            { 
               //: mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "T"
               SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "T" );
               //:ELSE
            } 
            else
            { 
               //: mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "N"
               SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "N" );
            } 

            //: END
         } 

         //: END
         //: mDegTrk.RequiredGroup.wDegreeAuditTotalCreditsTaken = CreditsTaken
         SetAttributeFromDecimal( mDegTrk, "RequiredGroup", "wDegreeAuditTotalCreditsTaken", CreditsTaken );


         //:// Group Type 2: A minimum number of credits is required for courses in the group.

         //:ELSE
      } 
      else
      { 
         //:IF mDegTrk.RequiredGroup.Type = "2"
         if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "2" ) == 0 )
         { 

            //:CreditsTaken     = 0
            CreditsTaken = 0;
            //:CreditsCompleted = 0
            CreditsCompleted = 0;
            //:FOR EACH mDegTrk.CourseType1or2 WITHIN mDegTrk.RequiredGroup
            RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "RequiredGroup" ).toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF mDegTrk.CourseType1or2.wDegreeAuditCourseTaken = "Y"
               if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseTaken", "Y" ) == 0 )
               { 
                  //:CreditsTaken = CreditsTaken + mDegTrk.CourseType1or2.wCreditsTaken 
                  {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                                     GetDecimalFromAttribute( md_dTempDecimal_3, mDegTrk, "CourseType1or2", "wCreditsTaken" );
                  dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
                  CreditsTaken = CreditsTaken + dTempDecimal_3;
               } 

               //:END
               //:TotalDegreeTrackCreditsTaken = TotalDegreeTrackCreditsTaken + mDegTrk.CourseType1or2.wCreditsTaken
               {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                               GetDecimalFromAttribute( md_dTempDecimal_4, mDegTrk, "CourseType1or2", "wCreditsTaken" );
               dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
               TotalDegreeTrackCreditsTaken = TotalDegreeTrackCreditsTaken + dTempDecimal_4;
               //:IF mDegTrk.CourseType1or2.wDegreeAuditCourseCompleted = "Y"
               if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseCompleted", "Y" ) == 0 )
               { 
                  //:CreditsCompleted = CreditsCompleted + mDegTrk.CourseType1or2.wCreditsTaken
                  {MutableDouble md_dTempDecimal_5 = new MutableDouble( dTempDecimal_5 );
                                     GetDecimalFromAttribute( md_dTempDecimal_5, mDegTrk, "CourseType1or2", "wCreditsTaken" );
                  dTempDecimal_5 = md_dTempDecimal_5.doubleValue( );}
                  CreditsCompleted = CreditsCompleted + dTempDecimal_5;
                  //:TotalDegreeTrackCreditsEarned = TotalDegreeTrackCreditsEarned + mDegTrk.CourseType1or2.wCreditsTaken
                  {MutableDouble md_dTempDecimal_6 = new MutableDouble( dTempDecimal_6 );
                                     GetDecimalFromAttribute( md_dTempDecimal_6, mDegTrk, "CourseType1or2", "wCreditsTaken" );
                  dTempDecimal_6 = md_dTempDecimal_6.doubleValue( );}
                  TotalDegreeTrackCreditsEarned = TotalDegreeTrackCreditsEarned + dTempDecimal_6;
               } 

               RESULT = mDegTrk.cursor( "CourseType1or2" ).setNextContinue().toInt();;
               //:END
            } 

            //:END
            //:szCreditsNeeded = mDegTrk.RequiredGroup.TotalRequiredCredits
            {MutableInt mi_lTempInteger_52 = new MutableInt( lTempInteger_52 );
            StringBuilder sb_szCreditsNeeded;
            if ( szCreditsNeeded == null )
               sb_szCreditsNeeded = new StringBuilder( 32 );
            else
               sb_szCreditsNeeded = new StringBuilder( szCreditsNeeded );
                         GetVariableFromAttribute( sb_szCreditsNeeded, mi_lTempInteger_52, 'S', 11, mDegTrk, "RequiredGroup", "TotalRequiredCredits", "", 0 );
            lTempInteger_52 = mi_lTempInteger_52.intValue( );
            szCreditsNeeded = sb_szCreditsNeeded.toString( );}
            //:szCreditsTaken  = CreditsTaken
             {StringBuilder sb_szCreditsTaken;
            if ( szCreditsTaken == null )
               sb_szCreditsTaken = new StringBuilder( 32 );
            else
               sb_szCreditsTaken = new StringBuilder( szCreditsTaken );
                        ZeidonStringConvertFromNumber( sb_szCreditsTaken, 1, 0, 10, 0, CreditsTaken, "D" );
            szCreditsTaken = sb_szCreditsTaken.toString( );}
            //:IF mDegTrk.RequiredGroup.wDegreeAuditHasTakingFlag = "Y"
            if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "wDegreeAuditHasTakingFlag", "Y" ) == 0 )
            { 
               //:mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.dReportValue +
               //:               "  (" + szCreditsTaken + " of " + szCreditsNeeded + " credits completed/enrolled.)"
               {StringBuilder sb_szTempString_4;
               if ( szTempString_4 == null )
                  sb_szTempString_4 = new StringBuilder( 32 );
               else
                  sb_szTempString_4 = new StringBuilder( szTempString_4 );
                               GetStringFromAttribute( sb_szTempString_4, mDegTrk, "RequiredGroup", "dReportValue" );
               szTempString_4 = sb_szTempString_4.toString( );}
                {StringBuilder sb_szTempString_4;
               if ( szTempString_4 == null )
                  sb_szTempString_4 = new StringBuilder( 32 );
               else
                  sb_szTempString_4 = new StringBuilder( szTempString_4 );
                              ZeidonStringConcat( sb_szTempString_4, 1, 0, "  (", 1, 0, 255 );
               szTempString_4 = sb_szTempString_4.toString( );}
                {StringBuilder sb_szTempString_4;
               if ( szTempString_4 == null )
                  sb_szTempString_4 = new StringBuilder( 32 );
               else
                  sb_szTempString_4 = new StringBuilder( szTempString_4 );
                              ZeidonStringConcat( sb_szTempString_4, 1, 0, szCreditsTaken, 1, 0, 255 );
               szTempString_4 = sb_szTempString_4.toString( );}
                {StringBuilder sb_szTempString_4;
               if ( szTempString_4 == null )
                  sb_szTempString_4 = new StringBuilder( 32 );
               else
                  sb_szTempString_4 = new StringBuilder( szTempString_4 );
                              ZeidonStringConcat( sb_szTempString_4, 1, 0, " of ", 1, 0, 255 );
               szTempString_4 = sb_szTempString_4.toString( );}
                {StringBuilder sb_szTempString_4;
               if ( szTempString_4 == null )
                  sb_szTempString_4 = new StringBuilder( 32 );
               else
                  sb_szTempString_4 = new StringBuilder( szTempString_4 );
                              ZeidonStringConcat( sb_szTempString_4, 1, 0, szCreditsNeeded, 1, 0, 255 );
               szTempString_4 = sb_szTempString_4.toString( );}
                {StringBuilder sb_szTempString_4;
               if ( szTempString_4 == null )
                  sb_szTempString_4 = new StringBuilder( 32 );
               else
                  sb_szTempString_4 = new StringBuilder( szTempString_4 );
                              ZeidonStringConcat( sb_szTempString_4, 1, 0, " credits completed/enrolled.)", 1, 0, 255 );
               szTempString_4 = sb_szTempString_4.toString( );}
               SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", szTempString_4 );
               //:ELSE
            } 
            else
            { 
               //:mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.dReportValue +
               //:               "  (" + szCreditsTaken + " of " + szCreditsNeeded + " credits completed.)"
               {StringBuilder sb_szTempString_5;
               if ( szTempString_5 == null )
                  sb_szTempString_5 = new StringBuilder( 32 );
               else
                  sb_szTempString_5 = new StringBuilder( szTempString_5 );
                               GetStringFromAttribute( sb_szTempString_5, mDegTrk, "RequiredGroup", "dReportValue" );
               szTempString_5 = sb_szTempString_5.toString( );}
                {StringBuilder sb_szTempString_5;
               if ( szTempString_5 == null )
                  sb_szTempString_5 = new StringBuilder( 32 );
               else
                  sb_szTempString_5 = new StringBuilder( szTempString_5 );
                              ZeidonStringConcat( sb_szTempString_5, 1, 0, "  (", 1, 0, 255 );
               szTempString_5 = sb_szTempString_5.toString( );}
                {StringBuilder sb_szTempString_5;
               if ( szTempString_5 == null )
                  sb_szTempString_5 = new StringBuilder( 32 );
               else
                  sb_szTempString_5 = new StringBuilder( szTempString_5 );
                              ZeidonStringConcat( sb_szTempString_5, 1, 0, szCreditsTaken, 1, 0, 255 );
               szTempString_5 = sb_szTempString_5.toString( );}
                {StringBuilder sb_szTempString_5;
               if ( szTempString_5 == null )
                  sb_szTempString_5 = new StringBuilder( 32 );
               else
                  sb_szTempString_5 = new StringBuilder( szTempString_5 );
                              ZeidonStringConcat( sb_szTempString_5, 1, 0, " of ", 1, 0, 255 );
               szTempString_5 = sb_szTempString_5.toString( );}
                {StringBuilder sb_szTempString_5;
               if ( szTempString_5 == null )
                  sb_szTempString_5 = new StringBuilder( 32 );
               else
                  sb_szTempString_5 = new StringBuilder( szTempString_5 );
                              ZeidonStringConcat( sb_szTempString_5, 1, 0, szCreditsNeeded, 1, 0, 255 );
               szTempString_5 = sb_szTempString_5.toString( );}
                {StringBuilder sb_szTempString_5;
               if ( szTempString_5 == null )
                  sb_szTempString_5 = new StringBuilder( 32 );
               else
                  sb_szTempString_5 = new StringBuilder( szTempString_5 );
                              ZeidonStringConcat( sb_szTempString_5, 1, 0, " credits completed.)", 1, 0, 255 );
               szTempString_5 = sb_szTempString_5.toString( );}
               SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", szTempString_5 );
            } 

            //:END
            //:IF CreditsCompleted >= mDegTrk.RequiredGroup.TotalRequiredCredits
            if ( CompareAttributeToDecimal( mDegTrk, "RequiredGroup", "TotalRequiredCredits", CreditsCompleted ) <= 0 )
            { 
               //:mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "C"
               SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "C" );
               //:ELSE
            } 
            else
            { 
               //:IF CreditsTaken >= mDegTrk.RequiredGroup.TotalRequiredCredits
               if ( CompareAttributeToDecimal( mDegTrk, "RequiredGroup", "TotalRequiredCredits", CreditsTaken ) <= 0 )
               { 
                  //:mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "T"
                  SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "T" );
                  //:ELSE
               } 
               else
               { 
                  //:mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "N"
                  SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "N" );
               } 

               //:END
            } 

            //:END
            //:mDegTrk.RequiredGroup.wDegreeAuditTotalCreditsTaken = CreditsTaken
            SetAttributeFromDecimal( mDegTrk, "RequiredGroup", "wDegreeAuditTotalCreditsTaken", CreditsTaken );


            //:// Group Type 3: The same as Group Type 2, except that all courses exist under subgroups,
            //://               where each subgroup defines the minimum number of credits that must be
            //://               taken from the courses under it. All courses must be defined under a subgroup.

            //:ELSE
         } 
         else
         { 
            //:IF mDegTrk.RequiredGroup.Type = "3"
            if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "3" ) == 0 )
            { 

               //:CreditsTaken     = 0
               CreditsTaken = 0;
               //:CreditsCompleted = 0
               CreditsCompleted = 0;
               //:FOR EACH mDegTrk.RequiredSubGroup
               RESULT = mDegTrk.cursor( "RequiredSubGroup" ).setFirst().toInt();
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:MinCreditsTaken     = 0
                  MinCreditsTaken = 0;
                  //:MinCreditsCompleted = 0
                  MinCreditsCompleted = 0;
                  //:FOR EACH mDegTrk.CourseType3or4 WITHIN mDegTrk.RequiredSubGroup
                  RESULT = mDegTrk.cursor( "CourseType3or4" ).setFirst( "RequiredSubGroup" ).toInt();
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //:IF mDegTrk.CourseType3or4.wDegreeAuditCourseTaken = "Y"
                     if ( CompareAttributeToString( mDegTrk, "CourseType3or4", "wDegreeAuditCourseTaken", "Y" ) == 0 )
                     { 
                        //:CreditsTaken    = CreditsTaken + mDegTrk.CourseType3or4.wCreditsTaken 
                        {MutableDouble md_dTempDecimal_7 = new MutableDouble( dTempDecimal_7 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_7, mDegTrk, "CourseType3or4", "wCreditsTaken" );
                        dTempDecimal_7 = md_dTempDecimal_7.doubleValue( );}
                        CreditsTaken = CreditsTaken + dTempDecimal_7;
                        //:MinCreditsTaken = MinCreditsTaken + mDegTrk.CourseType3or4.wCreditsTaken
                        {MutableDouble md_dTempDecimal_8 = new MutableDouble( dTempDecimal_8 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_8, mDegTrk, "CourseType3or4", "wCreditsTaken" );
                        dTempDecimal_8 = md_dTempDecimal_8.doubleValue( );}
                        MinCreditsTaken = MinCreditsTaken + dTempDecimal_8;
                     } 

                     //:END
                     //:TotalDegreeTrackCreditsTaken = TotalDegreeTrackCreditsTaken + mDegTrk.CourseType3or4.wCreditsTaken
                     {MutableDouble md_dTempDecimal_9 = new MutableDouble( dTempDecimal_9 );
                                           GetDecimalFromAttribute( md_dTempDecimal_9, mDegTrk, "CourseType3or4", "wCreditsTaken" );
                     dTempDecimal_9 = md_dTempDecimal_9.doubleValue( );}
                     TotalDegreeTrackCreditsTaken = TotalDegreeTrackCreditsTaken + dTempDecimal_9;
                     //:IF mDegTrk.CourseType3or4.wDegreeAuditCourseCompleted = "Y"
                     if ( CompareAttributeToString( mDegTrk, "CourseType3or4", "wDegreeAuditCourseCompleted", "Y" ) == 0 )
                     { 
                        //:CreditsCompleted    = CreditsCompleted + mDegTrk.CourseType3or4.wCreditsTaken 
                        {MutableDouble md_dTempDecimal_10 = new MutableDouble( dTempDecimal_10 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_10, mDegTrk, "CourseType3or4", "wCreditsTaken" );
                        dTempDecimal_10 = md_dTempDecimal_10.doubleValue( );}
                        CreditsCompleted = CreditsCompleted + dTempDecimal_10;
                        //:MinCreditsCompleted = MinCreditsCompleted + mDegTrk.CourseType3or4.wCreditsTaken
                        {MutableDouble md_dTempDecimal_11 = new MutableDouble( dTempDecimal_11 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_11, mDegTrk, "CourseType3or4", "wCreditsTaken" );
                        dTempDecimal_11 = md_dTempDecimal_11.doubleValue( );}
                        MinCreditsCompleted = MinCreditsCompleted + dTempDecimal_11;
                        //:TotalDegreeTrackCreditsEarned = TotalDegreeTrackCreditsEarned + mDegTrk.CourseType3or4.wCreditsTaken
                        {MutableDouble md_dTempDecimal_12 = new MutableDouble( dTempDecimal_12 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_12, mDegTrk, "CourseType3or4", "wCreditsTaken" );
                        dTempDecimal_12 = md_dTempDecimal_12.doubleValue( );}
                        TotalDegreeTrackCreditsEarned = TotalDegreeTrackCreditsEarned + dTempDecimal_12;
                     } 

                     RESULT = mDegTrk.cursor( "CourseType3or4" ).setNextContinue().toInt();;
                     //:END
                  } 

                  //:END
                  //:szCreditsNeeded = mDegTrk.RequiredSubGroup.TotalRequiredCredits
                  {MutableInt mi_lTempInteger_53 = new MutableInt( lTempInteger_53 );
                  StringBuilder sb_szCreditsNeeded;
                  if ( szCreditsNeeded == null )
                     sb_szCreditsNeeded = new StringBuilder( 32 );
                  else
                     sb_szCreditsNeeded = new StringBuilder( szCreditsNeeded );
                                     GetVariableFromAttribute( sb_szCreditsNeeded, mi_lTempInteger_53, 'S', 11, mDegTrk, "RequiredSubGroup", "TotalRequiredCredits", "", 0 );
                  lTempInteger_53 = mi_lTempInteger_53.intValue( );
                  szCreditsNeeded = sb_szCreditsNeeded.toString( );}
                  //:szCreditsTaken  = MinCreditsTaken
                   {StringBuilder sb_szCreditsTaken;
                  if ( szCreditsTaken == null )
                     sb_szCreditsTaken = new StringBuilder( 32 );
                  else
                     sb_szCreditsTaken = new StringBuilder( szCreditsTaken );
                                    ZeidonStringConvertFromNumber( sb_szCreditsTaken, 1, 0, 10, 0, MinCreditsTaken, "D" );
                  szCreditsTaken = sb_szCreditsTaken.toString( );}
                  //:IF mDegTrk.RequiredSubGroup.wDegreeAuditHasTakingFlag = "Y"
                  if ( CompareAttributeToString( mDegTrk, "RequiredSubGroup", "wDegreeAuditHasTakingFlag", "Y" ) == 0 )
                  { 
                     //:mDegTrk.RequiredSubGroup.wDegreeAuditOL_Value = mDegTrk.RequiredSubGroup.dReportValue +
                     //:            "  (" + szCreditsTaken + " of " + szCreditsNeeded + " credits completed/enrolled.)"
                     {StringBuilder sb_szTempString_6;
                     if ( szTempString_6 == null )
                        sb_szTempString_6 = new StringBuilder( 32 );
                     else
                        sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                           GetStringFromAttribute( sb_szTempString_6, mDegTrk, "RequiredSubGroup", "dReportValue" );
                     szTempString_6 = sb_szTempString_6.toString( );}
                      {StringBuilder sb_szTempString_6;
                     if ( szTempString_6 == null )
                        sb_szTempString_6 = new StringBuilder( 32 );
                     else
                        sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                          ZeidonStringConcat( sb_szTempString_6, 1, 0, "  (", 1, 0, 255 );
                     szTempString_6 = sb_szTempString_6.toString( );}
                      {StringBuilder sb_szTempString_6;
                     if ( szTempString_6 == null )
                        sb_szTempString_6 = new StringBuilder( 32 );
                     else
                        sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                          ZeidonStringConcat( sb_szTempString_6, 1, 0, szCreditsTaken, 1, 0, 255 );
                     szTempString_6 = sb_szTempString_6.toString( );}
                      {StringBuilder sb_szTempString_6;
                     if ( szTempString_6 == null )
                        sb_szTempString_6 = new StringBuilder( 32 );
                     else
                        sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                          ZeidonStringConcat( sb_szTempString_6, 1, 0, " of ", 1, 0, 255 );
                     szTempString_6 = sb_szTempString_6.toString( );}
                      {StringBuilder sb_szTempString_6;
                     if ( szTempString_6 == null )
                        sb_szTempString_6 = new StringBuilder( 32 );
                     else
                        sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                          ZeidonStringConcat( sb_szTempString_6, 1, 0, szCreditsNeeded, 1, 0, 255 );
                     szTempString_6 = sb_szTempString_6.toString( );}
                      {StringBuilder sb_szTempString_6;
                     if ( szTempString_6 == null )
                        sb_szTempString_6 = new StringBuilder( 32 );
                     else
                        sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                          ZeidonStringConcat( sb_szTempString_6, 1, 0, " credits completed/enrolled.)", 1, 0, 255 );
                     szTempString_6 = sb_szTempString_6.toString( );}
                     SetAttributeFromString( mDegTrk, "RequiredSubGroup", "wDegreeAuditOL_Value", szTempString_6 );
                     //:ELSE
                  } 
                  else
                  { 
                     //:mDegTrk.RequiredSubGroup.wDegreeAuditOL_Value = mDegTrk.RequiredSubGroup.dReportValue +
                     //:            "  (" + szCreditsTaken + " of " + szCreditsNeeded + " credits completed.)"
                     {StringBuilder sb_szTempString_7;
                     if ( szTempString_7 == null )
                        sb_szTempString_7 = new StringBuilder( 32 );
                     else
                        sb_szTempString_7 = new StringBuilder( szTempString_7 );
                                           GetStringFromAttribute( sb_szTempString_7, mDegTrk, "RequiredSubGroup", "dReportValue" );
                     szTempString_7 = sb_szTempString_7.toString( );}
                      {StringBuilder sb_szTempString_7;
                     if ( szTempString_7 == null )
                        sb_szTempString_7 = new StringBuilder( 32 );
                     else
                        sb_szTempString_7 = new StringBuilder( szTempString_7 );
                                          ZeidonStringConcat( sb_szTempString_7, 1, 0, "  (", 1, 0, 255 );
                     szTempString_7 = sb_szTempString_7.toString( );}
                      {StringBuilder sb_szTempString_7;
                     if ( szTempString_7 == null )
                        sb_szTempString_7 = new StringBuilder( 32 );
                     else
                        sb_szTempString_7 = new StringBuilder( szTempString_7 );
                                          ZeidonStringConcat( sb_szTempString_7, 1, 0, szCreditsTaken, 1, 0, 255 );
                     szTempString_7 = sb_szTempString_7.toString( );}
                      {StringBuilder sb_szTempString_7;
                     if ( szTempString_7 == null )
                        sb_szTempString_7 = new StringBuilder( 32 );
                     else
                        sb_szTempString_7 = new StringBuilder( szTempString_7 );
                                          ZeidonStringConcat( sb_szTempString_7, 1, 0, " of ", 1, 0, 255 );
                     szTempString_7 = sb_szTempString_7.toString( );}
                      {StringBuilder sb_szTempString_7;
                     if ( szTempString_7 == null )
                        sb_szTempString_7 = new StringBuilder( 32 );
                     else
                        sb_szTempString_7 = new StringBuilder( szTempString_7 );
                                          ZeidonStringConcat( sb_szTempString_7, 1, 0, szCreditsNeeded, 1, 0, 255 );
                     szTempString_7 = sb_szTempString_7.toString( );}
                      {StringBuilder sb_szTempString_7;
                     if ( szTempString_7 == null )
                        sb_szTempString_7 = new StringBuilder( 32 );
                     else
                        sb_szTempString_7 = new StringBuilder( szTempString_7 );
                                          ZeidonStringConcat( sb_szTempString_7, 1, 0, " credits completed.)", 1, 0, 255 );
                     szTempString_7 = sb_szTempString_7.toString( );}
                     SetAttributeFromString( mDegTrk, "RequiredSubGroup", "wDegreeAuditOL_Value", szTempString_7 );
                  } 

                  //:END
                  //:IF MinCreditsCompleted >= mDegTrk.RequiredSubGroup.TotalRequiredCredits
                  if ( CompareAttributeToDecimal( mDegTrk, "RequiredSubGroup", "TotalRequiredCredits", MinCreditsCompleted ) <= 0 )
                  { 
                     //:mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "C"
                     SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "C" );
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF MinCreditsTaken >= mDegTrk.RequiredSubGroup.TotalRequiredCredits
                     if ( CompareAttributeToDecimal( mDegTrk, "RequiredSubGroup", "TotalRequiredCredits", MinCreditsTaken ) <= 0 )
                     { 
                        //:mDegTrk.RequiredSubGroup.wDegreeAuditSatisfied = "T"
                        SetAttributeFromString( mDegTrk, "RequiredSubGroup", "wDegreeAuditSatisfied", "T" );
                        //:ELSE
                     } 
                     else
                     { 
                        //:mDegTrk.RequiredSubGroup.wDegreeAuditSatisfied = "N"
                        SetAttributeFromString( mDegTrk, "RequiredSubGroup", "wDegreeAuditSatisfied", "N" );
                     } 

                     //:END
                  } 

                  RESULT = mDegTrk.cursor( "RequiredSubGroup" ).setNextContinue().toInt();;
                  //:END
               } 

               //:END
               //:szCreditsNeeded = mDegTrk.RequiredGroup.TotalRequiredCredits
               {MutableInt mi_lTempInteger_54 = new MutableInt( lTempInteger_54 );
               StringBuilder sb_szCreditsNeeded;
               if ( szCreditsNeeded == null )
                  sb_szCreditsNeeded = new StringBuilder( 32 );
               else
                  sb_szCreditsNeeded = new StringBuilder( szCreditsNeeded );
                               GetVariableFromAttribute( sb_szCreditsNeeded, mi_lTempInteger_54, 'S', 11, mDegTrk, "RequiredGroup", "TotalRequiredCredits", "", 0 );
               lTempInteger_54 = mi_lTempInteger_54.intValue( );
               szCreditsNeeded = sb_szCreditsNeeded.toString( );}
               //:szCreditsTaken  = CreditsTaken
                {StringBuilder sb_szCreditsTaken;
               if ( szCreditsTaken == null )
                  sb_szCreditsTaken = new StringBuilder( 32 );
               else
                  sb_szCreditsTaken = new StringBuilder( szCreditsTaken );
                              ZeidonStringConvertFromNumber( sb_szCreditsTaken, 1, 0, 10, 0, CreditsTaken, "D" );
               szCreditsTaken = sb_szCreditsTaken.toString( );}
               //:IF mDegTrk.RequiredGroup.wDegreeAuditHasTakingFlag = "Y"
               if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "wDegreeAuditHasTakingFlag", "Y" ) == 0 )
               { 
                  //:mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.dReportValue +
                  //:            "  (" + szCreditsTaken + " of " + szCreditsNeeded + " credits completed/enrolled.)"
                  {StringBuilder sb_szTempString_8;
                  if ( szTempString_8 == null )
                     sb_szTempString_8 = new StringBuilder( 32 );
                  else
                     sb_szTempString_8 = new StringBuilder( szTempString_8 );
                                     GetStringFromAttribute( sb_szTempString_8, mDegTrk, "RequiredGroup", "dReportValue" );
                  szTempString_8 = sb_szTempString_8.toString( );}
                   {StringBuilder sb_szTempString_8;
                  if ( szTempString_8 == null )
                     sb_szTempString_8 = new StringBuilder( 32 );
                  else
                     sb_szTempString_8 = new StringBuilder( szTempString_8 );
                                    ZeidonStringConcat( sb_szTempString_8, 1, 0, "  (", 1, 0, 255 );
                  szTempString_8 = sb_szTempString_8.toString( );}
                   {StringBuilder sb_szTempString_8;
                  if ( szTempString_8 == null )
                     sb_szTempString_8 = new StringBuilder( 32 );
                  else
                     sb_szTempString_8 = new StringBuilder( szTempString_8 );
                                    ZeidonStringConcat( sb_szTempString_8, 1, 0, szCreditsTaken, 1, 0, 255 );
                  szTempString_8 = sb_szTempString_8.toString( );}
                   {StringBuilder sb_szTempString_8;
                  if ( szTempString_8 == null )
                     sb_szTempString_8 = new StringBuilder( 32 );
                  else
                     sb_szTempString_8 = new StringBuilder( szTempString_8 );
                                    ZeidonStringConcat( sb_szTempString_8, 1, 0, " of ", 1, 0, 255 );
                  szTempString_8 = sb_szTempString_8.toString( );}
                   {StringBuilder sb_szTempString_8;
                  if ( szTempString_8 == null )
                     sb_szTempString_8 = new StringBuilder( 32 );
                  else
                     sb_szTempString_8 = new StringBuilder( szTempString_8 );
                                    ZeidonStringConcat( sb_szTempString_8, 1, 0, szCreditsNeeded, 1, 0, 255 );
                  szTempString_8 = sb_szTempString_8.toString( );}
                   {StringBuilder sb_szTempString_8;
                  if ( szTempString_8 == null )
                     sb_szTempString_8 = new StringBuilder( 32 );
                  else
                     sb_szTempString_8 = new StringBuilder( szTempString_8 );
                                    ZeidonStringConcat( sb_szTempString_8, 1, 0, " credits completed/enrolled.)", 1, 0, 255 );
                  szTempString_8 = sb_szTempString_8.toString( );}
                  SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", szTempString_8 );
                  //:ELSE
               } 
               else
               { 
                  //:mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.dReportValue +
                  //:            "  (" + szCreditsTaken + " of " + szCreditsNeeded + " credits completed.)"
                  {StringBuilder sb_szTempString_9;
                  if ( szTempString_9 == null )
                     sb_szTempString_9 = new StringBuilder( 32 );
                  else
                     sb_szTempString_9 = new StringBuilder( szTempString_9 );
                                     GetStringFromAttribute( sb_szTempString_9, mDegTrk, "RequiredGroup", "dReportValue" );
                  szTempString_9 = sb_szTempString_9.toString( );}
                   {StringBuilder sb_szTempString_9;
                  if ( szTempString_9 == null )
                     sb_szTempString_9 = new StringBuilder( 32 );
                  else
                     sb_szTempString_9 = new StringBuilder( szTempString_9 );
                                    ZeidonStringConcat( sb_szTempString_9, 1, 0, "  (", 1, 0, 255 );
                  szTempString_9 = sb_szTempString_9.toString( );}
                   {StringBuilder sb_szTempString_9;
                  if ( szTempString_9 == null )
                     sb_szTempString_9 = new StringBuilder( 32 );
                  else
                     sb_szTempString_9 = new StringBuilder( szTempString_9 );
                                    ZeidonStringConcat( sb_szTempString_9, 1, 0, szCreditsTaken, 1, 0, 255 );
                  szTempString_9 = sb_szTempString_9.toString( );}
                   {StringBuilder sb_szTempString_9;
                  if ( szTempString_9 == null )
                     sb_szTempString_9 = new StringBuilder( 32 );
                  else
                     sb_szTempString_9 = new StringBuilder( szTempString_9 );
                                    ZeidonStringConcat( sb_szTempString_9, 1, 0, " of ", 1, 0, 255 );
                  szTempString_9 = sb_szTempString_9.toString( );}
                   {StringBuilder sb_szTempString_9;
                  if ( szTempString_9 == null )
                     sb_szTempString_9 = new StringBuilder( 32 );
                  else
                     sb_szTempString_9 = new StringBuilder( szTempString_9 );
                                    ZeidonStringConcat( sb_szTempString_9, 1, 0, szCreditsNeeded, 1, 0, 255 );
                  szTempString_9 = sb_szTempString_9.toString( );}
                   {StringBuilder sb_szTempString_9;
                  if ( szTempString_9 == null )
                     sb_szTempString_9 = new StringBuilder( 32 );
                  else
                     sb_szTempString_9 = new StringBuilder( szTempString_9 );
                                    ZeidonStringConcat( sb_szTempString_9, 1, 0, " credits completed.)", 1, 0, 255 );
                  szTempString_9 = sb_szTempString_9.toString( );}
                  SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", szTempString_9 );
               } 

               //:END
               //:// All subgroups must meet minimums for the group to meet requirements.
               //:// Add to subgroup display value.
               //:SET CURSOR FIRST mDegTrk.RequiredSubGroup WHERE mDegTrk.RequiredSubGroup.wDegreeAuditSatisfied = "N"
               RESULT = mDegTrk.cursor( "RequiredSubGroup" ).setFirst( "wDegreeAuditSatisfied", "N" ).toInt();
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "N"
                  SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "N" );
                  //:mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.wDegreeAuditOL_Value +
                  //:                                        " (Subgroups not met)"
                  {StringBuilder sb_szTempString_10;
                  if ( szTempString_10 == null )
                     sb_szTempString_10 = new StringBuilder( 32 );
                  else
                     sb_szTempString_10 = new StringBuilder( szTempString_10 );
                                     GetStringFromAttribute( sb_szTempString_10, mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value" );
                  szTempString_10 = sb_szTempString_10.toString( );}
                   {StringBuilder sb_szTempString_10;
                  if ( szTempString_10 == null )
                     sb_szTempString_10 = new StringBuilder( 32 );
                  else
                     sb_szTempString_10 = new StringBuilder( szTempString_10 );
                                    ZeidonStringConcat( sb_szTempString_10, 1, 0, " (Subgroups not met)", 1, 0, 255 );
                  szTempString_10 = sb_szTempString_10.toString( );}
                  SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", szTempString_10 );
                  //:ELSE
               } 
               else
               { 
                  //:mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.wDegreeAuditOL_Value +
                  //:                                        " (Subgroups met)"
                  {StringBuilder sb_szTempString_11;
                  if ( szTempString_11 == null )
                     sb_szTempString_11 = new StringBuilder( 32 );
                  else
                     sb_szTempString_11 = new StringBuilder( szTempString_11 );
                                     GetStringFromAttribute( sb_szTempString_11, mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value" );
                  szTempString_11 = sb_szTempString_11.toString( );}
                   {StringBuilder sb_szTempString_11;
                  if ( szTempString_11 == null )
                     sb_szTempString_11 = new StringBuilder( 32 );
                  else
                     sb_szTempString_11 = new StringBuilder( szTempString_11 );
                                    ZeidonStringConcat( sb_szTempString_11, 1, 0, " (Subgroups met)", 1, 0, 255 );
                  szTempString_11 = sb_szTempString_11.toString( );}
                  SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", szTempString_11 );
                  //:SET CURSOR FIRST mDegTrk.RequiredSubGroup WHERE mDegTrk.RequiredSubGroup.wDegreeAuditSatisfied = "T"
                  RESULT = mDegTrk.cursor( "RequiredSubGroup" ).setFirst( "wDegreeAuditSatisfied", "T" ).toInt();
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "T"
                     SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "T" );
                     //:ELSE
                  } 
                  else
                  { 
                     //:mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "C"
                     SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "C" );
                  } 

                  //:END
               } 

               //:END
               //:mDegTrk.RequiredGroup.wDegreeAuditTotalCreditsTaken = CreditsTaken
               SetAttributeFromDecimal( mDegTrk, "RequiredGroup", "wDegreeAuditTotalCreditsTaken", CreditsTaken );



               //:// Group Type 4: The same as Group Type 2, except that all courses exist under subgroups,
               //://               and all the courses in one of the subgroups must be taken. The subgroups are
               //://               thus evaluated as OR conditions. All courses must be defined under a subgroup.

               //:ELSE
            } 
            else
            { 
               //:IF mDegTrk.RequiredGroup.Type = "4"
               if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "4" ) == 0 )
               { 

                  //:CreditsTaken     = 0
                  CreditsTaken = 0;
                  //:CreditsCompleted = 0
                  CreditsCompleted = 0;
                  //:FOR EACH mDegTrk.RequiredSubGroup
                  RESULT = mDegTrk.cursor( "RequiredSubGroup" ).setFirst().toInt();
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //:szCourseTakenFlag     = "Y"
                      {StringBuilder sb_szCourseTakenFlag;
                     if ( szCourseTakenFlag == null )
                        sb_szCourseTakenFlag = new StringBuilder( 32 );
                     else
                        sb_szCourseTakenFlag = new StringBuilder( szCourseTakenFlag );
                                          ZeidonStringCopy( sb_szCourseTakenFlag, 1, 0, "Y", 1, 0, 2 );
                     szCourseTakenFlag = sb_szCourseTakenFlag.toString( );}
                     //:szCourseCompletedFlag = "Y"
                      {StringBuilder sb_szCourseCompletedFlag;
                     if ( szCourseCompletedFlag == null )
                        sb_szCourseCompletedFlag = new StringBuilder( 32 );
                     else
                        sb_szCourseCompletedFlag = new StringBuilder( szCourseCompletedFlag );
                                          ZeidonStringCopy( sb_szCourseCompletedFlag, 1, 0, "Y", 1, 0, 2 );
                     szCourseCompletedFlag = sb_szCourseCompletedFlag.toString( );}
                     //:FOR EACH mDegTrk.CourseType3or4 WITHIN mDegTrk.RequiredSubGroup
                     RESULT = mDegTrk.cursor( "CourseType3or4" ).setFirst( "RequiredSubGroup" ).toInt();
                     while ( RESULT > zCURSOR_UNCHANGED )
                     { 
                        //:IF mDegTrk.CourseType3or4.wDegreeAuditCourseTaken = "Y"
                        if ( CompareAttributeToString( mDegTrk, "CourseType3or4", "wDegreeAuditCourseTaken", "Y" ) == 0 )
                        { 
                           //:CreditsTaken = CreditsTaken + mDegTrk.CourseType3or4.wCreditsTaken 
                           {MutableDouble md_dTempDecimal_13 = new MutableDouble( dTempDecimal_13 );
                                                       GetDecimalFromAttribute( md_dTempDecimal_13, mDegTrk, "CourseType3or4", "wCreditsTaken" );
                           dTempDecimal_13 = md_dTempDecimal_13.doubleValue( );}
                           CreditsTaken = CreditsTaken + dTempDecimal_13;
                           //:ELSE
                        } 
                        else
                        { 
                           //:szCourseTakenFlag = "N"
                            {StringBuilder sb_szCourseTakenFlag;
                           if ( szCourseTakenFlag == null )
                              sb_szCourseTakenFlag = new StringBuilder( 32 );
                           else
                              sb_szCourseTakenFlag = new StringBuilder( szCourseTakenFlag );
                                                      ZeidonStringCopy( sb_szCourseTakenFlag, 1, 0, "N", 1, 0, 2 );
                           szCourseTakenFlag = sb_szCourseTakenFlag.toString( );}
                        } 

                        //:END
                        //:TotalDegreeTrackCreditsTaken = TotalDegreeTrackCreditsTaken + mDegTrk.CourseType3or4.wCreditsTaken
                        {MutableDouble md_dTempDecimal_14 = new MutableDouble( dTempDecimal_14 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_14, mDegTrk, "CourseType3or4", "wCreditsTaken" );
                        dTempDecimal_14 = md_dTempDecimal_14.doubleValue( );}
                        TotalDegreeTrackCreditsTaken = TotalDegreeTrackCreditsTaken + dTempDecimal_14;
                        //:IF mDegTrk.CourseType3or4.wDegreeAuditCourseCompleted = "Y"
                        if ( CompareAttributeToString( mDegTrk, "CourseType3or4", "wDegreeAuditCourseCompleted", "Y" ) == 0 )
                        { 
                           //:CreditsCompleted = CreditsCompleted + mDegTrk.CourseType3or4.wCreditsTaken
                           {MutableDouble md_dTempDecimal_15 = new MutableDouble( dTempDecimal_15 );
                                                       GetDecimalFromAttribute( md_dTempDecimal_15, mDegTrk, "CourseType3or4", "wCreditsTaken" );
                           dTempDecimal_15 = md_dTempDecimal_15.doubleValue( );}
                           CreditsCompleted = CreditsCompleted + dTempDecimal_15;
                           //:TotalDegreeTrackCreditsEarned = TotalDegreeTrackCreditsEarned + mDegTrk.CourseType3or4.wCreditsTaken
                           {MutableDouble md_dTempDecimal_16 = new MutableDouble( dTempDecimal_16 );
                                                       GetDecimalFromAttribute( md_dTempDecimal_16, mDegTrk, "CourseType3or4", "wCreditsTaken" );
                           dTempDecimal_16 = md_dTempDecimal_16.doubleValue( );}
                           TotalDegreeTrackCreditsEarned = TotalDegreeTrackCreditsEarned + dTempDecimal_16;
                           //:ELSE
                        } 
                        else
                        { 
                           //:szCourseCompletedFlag = "N"
                            {StringBuilder sb_szCourseCompletedFlag;
                           if ( szCourseCompletedFlag == null )
                              sb_szCourseCompletedFlag = new StringBuilder( 32 );
                           else
                              sb_szCourseCompletedFlag = new StringBuilder( szCourseCompletedFlag );
                                                      ZeidonStringCopy( sb_szCourseCompletedFlag, 1, 0, "N", 1, 0, 2 );
                           szCourseCompletedFlag = sb_szCourseCompletedFlag.toString( );}
                        } 

                        RESULT = mDegTrk.cursor( "CourseType3or4" ).setNextContinue().toInt();;
                        //:END
                     } 

                     //:END
                     //:IF szCourseTakenFlag = "Y"
                     if ( ZeidonStringCompare( szCourseTakenFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
                     { 
                        //:mDegTrk.RequiredSubGroup.wDegreeAuditOL_Value = mDegTrk.RequiredSubGroup.dReportValue +
                        //:    "  (All courses for subgroup taken.)"
                        {StringBuilder sb_szTempString_12;
                        if ( szTempString_12 == null )
                           sb_szTempString_12 = new StringBuilder( 32 );
                        else
                           sb_szTempString_12 = new StringBuilder( szTempString_12 );
                                                 GetStringFromAttribute( sb_szTempString_12, mDegTrk, "RequiredSubGroup", "dReportValue" );
                        szTempString_12 = sb_szTempString_12.toString( );}
                         {StringBuilder sb_szTempString_12;
                        if ( szTempString_12 == null )
                           sb_szTempString_12 = new StringBuilder( 32 );
                        else
                           sb_szTempString_12 = new StringBuilder( szTempString_12 );
                                                ZeidonStringConcat( sb_szTempString_12, 1, 0, "  (All courses for subgroup taken.)", 1, 0, 255 );
                        szTempString_12 = sb_szTempString_12.toString( );}
                        SetAttributeFromString( mDegTrk, "RequiredSubGroup", "wDegreeAuditOL_Value", szTempString_12 );
                        //:IF szCourseCompletedFlag = "Y"
                        if ( ZeidonStringCompare( szCourseCompletedFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
                        { 
                           //:mDegTrk.RequiredSubGroup.wDegreeAuditSatisfied = "C"
                           SetAttributeFromString( mDegTrk, "RequiredSubGroup", "wDegreeAuditSatisfied", "C" );
                           //:ELSE
                        } 
                        else
                        { 
                           //:mDegTrk.RequiredSubGroup.wDegreeAuditSatisfied = "T"
                           SetAttributeFromString( mDegTrk, "RequiredSubGroup", "wDegreeAuditSatisfied", "T" );
                        } 

                        //:END
                        //:ELSE
                     } 
                     else
                     { 
                        //:mDegTrk.RequiredSubGroup.wDegreeAuditOL_Value = mDegTrk.RequiredSubGroup.dReportValue
                        SetAttributeFromAttribute( mDegTrk, "RequiredSubGroup", "wDegreeAuditOL_Value", mDegTrk, "RequiredSubGroup", "dReportValue" );
                        //:mDegTrk.RequiredSubGroup.wDegreeAuditSatisfied = "N"
                        SetAttributeFromString( mDegTrk, "RequiredSubGroup", "wDegreeAuditSatisfied", "N" );
                     } 

                     RESULT = mDegTrk.cursor( "RequiredSubGroup" ).setNextContinue().toInt();;
                     //:END
                  } 


                  //:END
                  //:szCreditsNeeded = mDegTrk.RequiredGroup.TotalRequiredCredits
                  {MutableInt mi_lTempInteger_55 = new MutableInt( lTempInteger_55 );
                  StringBuilder sb_szCreditsNeeded;
                  if ( szCreditsNeeded == null )
                     sb_szCreditsNeeded = new StringBuilder( 32 );
                  else
                     sb_szCreditsNeeded = new StringBuilder( szCreditsNeeded );
                                     GetVariableFromAttribute( sb_szCreditsNeeded, mi_lTempInteger_55, 'S', 11, mDegTrk, "RequiredGroup", "TotalRequiredCredits", "", 0 );
                  lTempInteger_55 = mi_lTempInteger_55.intValue( );
                  szCreditsNeeded = sb_szCreditsNeeded.toString( );}
                  //:szCreditsTaken  = CreditsTaken
                   {StringBuilder sb_szCreditsTaken;
                  if ( szCreditsTaken == null )
                     sb_szCreditsTaken = new StringBuilder( 32 );
                  else
                     sb_szCreditsTaken = new StringBuilder( szCreditsTaken );
                                    ZeidonStringConvertFromNumber( sb_szCreditsTaken, 1, 0, 10, 0, CreditsTaken, "D" );
                  szCreditsTaken = sb_szCreditsTaken.toString( );}
                  //:/*IF mDegTrk.RequiredGroup.wDegreeAuditHasTakingFlag = "Y"
                  //:mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.dReportValue +
                  //:            "  (" + szCreditsTaken + " of " + szCreditsNeeded + " credits completed/enrolled.)"
                  //:ELSE
                  //:mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.dReportValue +
                  //:            "  (" + szCreditsTaken + " of " + szCreditsNeeded + " credits completed.)"
                  //:END*/
                  //:// One subgroup must have all courses taken for the group to meet requirements.
                  //:// Add to subgroup display value.
                  //:SET CURSOR FIRST mDegTrk.RequiredSubGroup WHERE mDegTrk.RequiredSubGroup.wDegreeAuditSatisfied = "C"
                  RESULT = mDegTrk.cursor( "RequiredSubGroup" ).setFirst( "wDegreeAuditSatisfied", "C" ).toInt();
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.dReportValue +
                     //:                                     " (All courses in one subgroup must be taken: Reqmt MET)"
                     {StringBuilder sb_szTempString_13;
                     if ( szTempString_13 == null )
                        sb_szTempString_13 = new StringBuilder( 32 );
                     else
                        sb_szTempString_13 = new StringBuilder( szTempString_13 );
                                           GetStringFromAttribute( sb_szTempString_13, mDegTrk, "RequiredGroup", "dReportValue" );
                     szTempString_13 = sb_szTempString_13.toString( );}
                      {StringBuilder sb_szTempString_13;
                     if ( szTempString_13 == null )
                        sb_szTempString_13 = new StringBuilder( 32 );
                     else
                        sb_szTempString_13 = new StringBuilder( szTempString_13 );
                                          ZeidonStringConcat( sb_szTempString_13, 1, 0, " (All courses in one subgroup must be taken: Reqmt MET)", 1, 0, 255 );
                     szTempString_13 = sb_szTempString_13.toString( );}
                     SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", szTempString_13 );
                     //:IF CreditsCompleted >= mDegTrk.RequiredGroup.TotalRequiredCredits
                     if ( CompareAttributeToDecimal( mDegTrk, "RequiredGroup", "TotalRequiredCredits", CreditsCompleted ) <= 0 )
                     { 
                        //:mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "C"
                        SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "C" );
                        //:ELSE
                     } 
                     else
                     { 
                        //:IF CreditsTaken >= mDegTrk.RequiredGroup.TotalRequiredCredits
                        if ( CompareAttributeToDecimal( mDegTrk, "RequiredGroup", "TotalRequiredCredits", CreditsTaken ) <= 0 )
                        { 
                           //:mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "T"
                           SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "T" );
                           //:ELSE
                        } 
                        else
                        { 
                           //:mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "N"
                           SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "N" );
                        } 

                        //:END
                     } 

                     //:END
                     //:ELSE
                  } 
                  else
                  { 
                     //:SET CURSOR FIRST mDegTrk.RequiredSubGroup WHERE mDegTrk.RequiredSubGroup.wDegreeAuditSatisfied = "T"
                     RESULT = mDegTrk.cursor( "RequiredSubGroup" ).setFirst( "wDegreeAuditSatisfied", "T" ).toInt();
                     //:IF RESULT >= zCURSOR_SET
                     if ( RESULT >= zCURSOR_SET )
                     { 
                        //:mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.dReportValue +
                        //:                                     " (All courses in one subgroup must be taken: Reqmt MET)"
                        {StringBuilder sb_szTempString_14;
                        if ( szTempString_14 == null )
                           sb_szTempString_14 = new StringBuilder( 32 );
                        else
                           sb_szTempString_14 = new StringBuilder( szTempString_14 );
                                                 GetStringFromAttribute( sb_szTempString_14, mDegTrk, "RequiredGroup", "dReportValue" );
                        szTempString_14 = sb_szTempString_14.toString( );}
                         {StringBuilder sb_szTempString_14;
                        if ( szTempString_14 == null )
                           sb_szTempString_14 = new StringBuilder( 32 );
                        else
                           sb_szTempString_14 = new StringBuilder( szTempString_14 );
                                                ZeidonStringConcat( sb_szTempString_14, 1, 0, " (All courses in one subgroup must be taken: Reqmt MET)", 1, 0, 255 );
                        szTempString_14 = sb_szTempString_14.toString( );}
                        SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", szTempString_14 );
                        //:IF CreditsTaken >= mDegTrk.RequiredGroup.TotalRequiredCredits
                        if ( CompareAttributeToDecimal( mDegTrk, "RequiredGroup", "TotalRequiredCredits", CreditsTaken ) <= 0 )
                        { 
                           //:mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "T"
                           SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "T" );
                           //:ELSE
                        } 
                        else
                        { 
                           //:mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "N"
                           SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "N" );
                        } 

                        //:END
                        //:ELSE
                     } 
                     else
                     { 
                        //:mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "N"
                        SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "N" );
                        //:mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.dReportValue +
                        //:                                     " (NO subgroup meets reqmts)"
                        {StringBuilder sb_szTempString_15;
                        if ( szTempString_15 == null )
                           sb_szTempString_15 = new StringBuilder( 32 );
                        else
                           sb_szTempString_15 = new StringBuilder( szTempString_15 );
                                                 GetStringFromAttribute( sb_szTempString_15, mDegTrk, "RequiredGroup", "dReportValue" );
                        szTempString_15 = sb_szTempString_15.toString( );}
                         {StringBuilder sb_szTempString_15;
                        if ( szTempString_15 == null )
                           sb_szTempString_15 = new StringBuilder( 32 );
                        else
                           sb_szTempString_15 = new StringBuilder( szTempString_15 );
                                                ZeidonStringConcat( sb_szTempString_15, 1, 0, " (NO subgroup meets reqmts)", 1, 0, 255 );
                        szTempString_15 = sb_szTempString_15.toString( );}
                        SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", szTempString_15 );
                     } 

                     //:END
                  } 

                  //:END
                  //:mDegTrk.RequiredGroup.wDegreeAuditTotalCreditsTaken = CreditsTaken
                  SetAttributeFromDecimal( mDegTrk, "RequiredGroup", "wDegreeAuditTotalCreditsTaken", CreditsTaken );
                  //:ELSE
               } 
               else
               { 

                  //:// Group Type E: Electives

                  //:IF mDegTrk.RequiredGroup.Type = "E"
                  if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "E" ) == 0 )
                  { 

                     //:CourseTakenCount  = 0
                     CourseTakenCount = 0;
                     //:CreditsTaken      = 0
                     CreditsTaken = 0;
                     //:FOR EACH mDegTrk.CourseType1or2 WITHIN mDegTrk.RequiredGroup
                     RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "RequiredGroup" ).toInt();
                     while ( RESULT > zCURSOR_UNCHANGED )
                     { 
                        //:IF mDegTrk.CourseType1or2.wDegreeAuditCourseTaken = "Y"
                        if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseTaken", "Y" ) == 0 )
                        { 
                           //:CourseTakenCount = CourseTakenCount + 1
                           CourseTakenCount = CourseTakenCount + 1;
                           //:CreditsTaken     = CreditsTaken + mDegTrk.CourseType1or2.wCreditsTaken 
                           {MutableDouble md_dTempDecimal_17 = new MutableDouble( dTempDecimal_17 );
                                                       GetDecimalFromAttribute( md_dTempDecimal_17, mDegTrk, "CourseType1or2", "wCreditsTaken" );
                           dTempDecimal_17 = md_dTempDecimal_17.doubleValue( );}
                           CreditsTaken = CreditsTaken + dTempDecimal_17;
                        } 

                        //:END
                        //:TotalDegreeTrackCreditsTaken = TotalDegreeTrackCreditsTaken + mDegTrk.CourseType1or2.wCreditsTaken
                        {MutableDouble md_dTempDecimal_18 = new MutableDouble( dTempDecimal_18 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_18, mDegTrk, "CourseType1or2", "wCreditsTaken" );
                        dTempDecimal_18 = md_dTempDecimal_18.doubleValue( );}
                        TotalDegreeTrackCreditsTaken = TotalDegreeTrackCreditsTaken + dTempDecimal_18;
                        //:IF mDegTrk.CourseType1or2.wCourseStatus = "Completed"
                        if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "wCourseStatus", "Completed" ) == 0 )
                        { 
                           //:TotalDegreeTrackCreditsEarned = TotalDegreeTrackCreditsEarned + mDegTrk.CourseType1or2.wCreditsTaken
                           {MutableDouble md_dTempDecimal_19 = new MutableDouble( dTempDecimal_19 );
                                                       GetDecimalFromAttribute( md_dTempDecimal_19, mDegTrk, "CourseType1or2", "wCreditsTaken" );
                           dTempDecimal_19 = md_dTempDecimal_19.doubleValue( );}
                           TotalDegreeTrackCreditsEarned = TotalDegreeTrackCreditsEarned + dTempDecimal_19;
                        } 

                        RESULT = mDegTrk.cursor( "CourseType1or2" ).setNextContinue().toInt();;
                        //:END
                     } 

                     //:END
                     //:szCourseTakenCount = CourseTakenCount
                      {StringBuilder sb_szCourseTakenCount;
                     if ( szCourseTakenCount == null )
                        sb_szCourseTakenCount = new StringBuilder( 32 );
                     else
                        sb_szCourseTakenCount = new StringBuilder( szCourseTakenCount );
                                          ZeidonStringConvertFromNumber( sb_szCourseTakenCount, 1, 0, 5, CourseTakenCount, (double) 0.0, "I" );
                     szCourseTakenCount = sb_szCourseTakenCount.toString( );}
                     //:szCreditsTaken     = CreditsTaken
                      {StringBuilder sb_szCreditsTaken;
                     if ( szCreditsTaken == null )
                        sb_szCreditsTaken = new StringBuilder( 32 );
                     else
                        sb_szCreditsTaken = new StringBuilder( szCreditsTaken );
                                          ZeidonStringConvertFromNumber( sb_szCreditsTaken, 1, 0, 10, 0, CreditsTaken, "D" );
                     szCreditsTaken = sb_szCreditsTaken.toString( );}
                     //:mDegTrk.RequiredGroup.wDegreeAuditOL_Value = "Electives - " +
                     //:         "Courses: " + szCourseTakenCount + ", Credits: " + szCreditsTaken
                      {StringBuilder sb_szTempString_16;
                     if ( szTempString_16 == null )
                        sb_szTempString_16 = new StringBuilder( 32 );
                     else
                        sb_szTempString_16 = new StringBuilder( szTempString_16 );
                                          ZeidonStringCopy( sb_szTempString_16, 1, 0, "Electives - ", 1, 0, 255 );
                     szTempString_16 = sb_szTempString_16.toString( );}
                      {StringBuilder sb_szTempString_16;
                     if ( szTempString_16 == null )
                        sb_szTempString_16 = new StringBuilder( 32 );
                     else
                        sb_szTempString_16 = new StringBuilder( szTempString_16 );
                                          ZeidonStringConcat( sb_szTempString_16, 1, 0, "Courses: ", 1, 0, 255 );
                     szTempString_16 = sb_szTempString_16.toString( );}
                      {StringBuilder sb_szTempString_16;
                     if ( szTempString_16 == null )
                        sb_szTempString_16 = new StringBuilder( 32 );
                     else
                        sb_szTempString_16 = new StringBuilder( szTempString_16 );
                                          ZeidonStringConcat( sb_szTempString_16, 1, 0, szCourseTakenCount, 1, 0, 255 );
                     szTempString_16 = sb_szTempString_16.toString( );}
                      {StringBuilder sb_szTempString_16;
                     if ( szTempString_16 == null )
                        sb_szTempString_16 = new StringBuilder( 32 );
                     else
                        sb_szTempString_16 = new StringBuilder( szTempString_16 );
                                          ZeidonStringConcat( sb_szTempString_16, 1, 0, ", Credits: ", 1, 0, 255 );
                     szTempString_16 = sb_szTempString_16.toString( );}
                      {StringBuilder sb_szTempString_16;
                     if ( szTempString_16 == null )
                        sb_szTempString_16 = new StringBuilder( 32 );
                     else
                        sb_szTempString_16 = new StringBuilder( szTempString_16 );
                                          ZeidonStringConcat( sb_szTempString_16, 1, 0, szCreditsTaken, 1, 0, 255 );
                     szTempString_16 = sb_szTempString_16.toString( );}
                     SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", szTempString_16 );
                     //:mDegTrk.RequiredGroup.wDegreeAuditTotalCreditsTaken = CreditsTaken
                     SetAttributeFromDecimal( mDegTrk, "RequiredGroup", "wDegreeAuditTotalCreditsTaken", CreditsTaken );
                     //:OrderEntityForView( mDegTrk, "RequiredGroupCourseType1or2", "CourseType1or2.Number A" )
                     OrderEntityForView( mDegTrk, "RequiredGroupCourseType1or2", "CourseType1or2.Number A" );

                     //:ELSE
                  } 
                  else
                  { 

                     //:// Group Type D: Developmental Courses

                     //:IF mDegTrk.RequiredGroup.Type = "D"
                     if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "D" ) == 0 )
                     { 

                        //:CourseTakenCount  = 0
                        CourseTakenCount = 0;
                        //:CreditsTaken      = 0
                        CreditsTaken = 0;
                        //:FOR EACH mDegTrk.CourseType1or2 WITHIN mDegTrk.RequiredGroup
                        RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "RequiredGroup" ).toInt();
                        while ( RESULT > zCURSOR_UNCHANGED )
                        { 
                           //:CourseTakenCount = CourseTakenCount + 1
                           CourseTakenCount = CourseTakenCount + 1;
                           //:CreditsTaken     = CreditsTaken + mDegTrk.CourseType1or2.wCreditsTaken 
                           {MutableDouble md_dTempDecimal_20 = new MutableDouble( dTempDecimal_20 );
                                                       GetDecimalFromAttribute( md_dTempDecimal_20, mDegTrk, "CourseType1or2", "wCreditsTaken" );
                           dTempDecimal_20 = md_dTempDecimal_20.doubleValue( );}
                           CreditsTaken = CreditsTaken + dTempDecimal_20;
                           RESULT = mDegTrk.cursor( "CourseType1or2" ).setNextContinue().toInt();;
                        } 

                        //:END
                        //:szCourseTakenCount = CourseTakenCount
                         {StringBuilder sb_szCourseTakenCount;
                        if ( szCourseTakenCount == null )
                           sb_szCourseTakenCount = new StringBuilder( 32 );
                        else
                           sb_szCourseTakenCount = new StringBuilder( szCourseTakenCount );
                                                ZeidonStringConvertFromNumber( sb_szCourseTakenCount, 1, 0, 5, CourseTakenCount, (double) 0.0, "I" );
                        szCourseTakenCount = sb_szCourseTakenCount.toString( );}
                        //:szCreditsTaken     = CreditsTaken
                         {StringBuilder sb_szCreditsTaken;
                        if ( szCreditsTaken == null )
                           sb_szCreditsTaken = new StringBuilder( 32 );
                        else
                           sb_szCreditsTaken = new StringBuilder( szCreditsTaken );
                                                ZeidonStringConvertFromNumber( sb_szCreditsTaken, 1, 0, 10, 0, CreditsTaken, "D" );
                        szCreditsTaken = sb_szCreditsTaken.toString( );}
                        //:mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.Name +
                        //:      " - Courses: " + szCourseTakenCount + ", Credits: " + szCreditsTaken
                        {StringBuilder sb_szTempString_17;
                        if ( szTempString_17 == null )
                           sb_szTempString_17 = new StringBuilder( 32 );
                        else
                           sb_szTempString_17 = new StringBuilder( szTempString_17 );
                                                 GetStringFromAttribute( sb_szTempString_17, mDegTrk, "RequiredGroup", "Name" );
                        szTempString_17 = sb_szTempString_17.toString( );}
                         {StringBuilder sb_szTempString_17;
                        if ( szTempString_17 == null )
                           sb_szTempString_17 = new StringBuilder( 32 );
                        else
                           sb_szTempString_17 = new StringBuilder( szTempString_17 );
                                                ZeidonStringConcat( sb_szTempString_17, 1, 0, " - Courses: ", 1, 0, 255 );
                        szTempString_17 = sb_szTempString_17.toString( );}
                         {StringBuilder sb_szTempString_17;
                        if ( szTempString_17 == null )
                           sb_szTempString_17 = new StringBuilder( 32 );
                        else
                           sb_szTempString_17 = new StringBuilder( szTempString_17 );
                                                ZeidonStringConcat( sb_szTempString_17, 1, 0, szCourseTakenCount, 1, 0, 255 );
                        szTempString_17 = sb_szTempString_17.toString( );}
                         {StringBuilder sb_szTempString_17;
                        if ( szTempString_17 == null )
                           sb_szTempString_17 = new StringBuilder( 32 );
                        else
                           sb_szTempString_17 = new StringBuilder( szTempString_17 );
                                                ZeidonStringConcat( sb_szTempString_17, 1, 0, ", Credits: ", 1, 0, 255 );
                        szTempString_17 = sb_szTempString_17.toString( );}
                         {StringBuilder sb_szTempString_17;
                        if ( szTempString_17 == null )
                           sb_szTempString_17 = new StringBuilder( 32 );
                        else
                           sb_szTempString_17 = new StringBuilder( szTempString_17 );
                                                ZeidonStringConcat( sb_szTempString_17, 1, 0, szCreditsTaken, 1, 0, 255 );
                        szTempString_17 = sb_szTempString_17.toString( );}
                        SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", szTempString_17 );
                        //:OrderEntityForView( mDegTrk, "RequiredGroupCourseType1or2", "CourseType1or2.Number A" )
                        OrderEntityForView( mDegTrk, "RequiredGroupCourseType1or2", "CourseType1or2.Number A" );
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

      //: END
      //: 
      //: // If the RequiredGroup has been manually satisfied, we will override the results set above.
      //: // We will first validate that no ManuallySatisfied entry exists without a DegreeTrack entity as this
      //: // case leads to a Core error.
      //: FOR EACH mStudenC.ManuallySatisfied 
      RESULT = mStudenC.cursor( "ManuallySatisfied" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //: IF mStudenC.MS_DegreeTrack DOES NOT EXIST
         lTempInteger_56 = CheckExistenceOfEntity( mStudenC, "MS_DegreeTrack" );
         if ( lTempInteger_56 != 0 )
         { 
            //: MessageSend( mStudenC, "", "Degree Audit",
            //:              "The Student has a 'Manually Satisfied Groups' entry that doesn't have a Degree Track associated with it.",
            //:              zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mStudenC, "", "Degree Audit", "The Student has a 'Manually Satisfied Groups' entry that doesn't have a Degree Track associated with it.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //: RETURN -1
            if(8==8)return( -1 );
         } 

         //: END
         //: IF mStudenC.MS_RequiredGroup DOES NOT EXIST
         lTempInteger_57 = CheckExistenceOfEntity( mStudenC, "MS_RequiredGroup" );
         if ( lTempInteger_57 != 0 )
         { 
            //: MessageSend( mStudenC, "", "Degree Audit",
            //:              "The Student has a 'Manually Satisfied Groups' entry that doesn't have a Required Group associated with it.",
            //:              zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mStudenC, "", "Degree Audit", "The Student has a 'Manually Satisfied Groups' entry that doesn't have a Required Group associated with it.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //: RETURN -1
            if(8==8)return( -1 );
         } 

         RESULT = mStudenC.cursor( "ManuallySatisfied" ).setNextContinue().toInt();;
         //: END
      } 

      //: END
      //: SET CURSOR FIRST mStudenC.ManuallySatisfied 
      //:            WHERE mStudenC.MS_DegreeTrack.ID   = mDegTrk.DegreeTrack.ID 
      //:              AND mStudenC.MS_RequiredGroup.ID = mDegTrk.RequiredGroup.ID 
      RESULT = mStudenC.cursor( "ManuallySatisfied" ).setFirst().toInt();
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mStudenC, "MS_DegreeTrack", "ID", mDegTrk, "DegreeTrack", "ID" ) != 0 ||
                 CompareAttributeToAttribute( mStudenC, "MS_RequiredGroup", "ID", mDegTrk, "RequiredGroup", "ID" ) != 0 ) )
         { 
            RESULT = mStudenC.cursor( "ManuallySatisfied" ).setNextContinue().toInt();;
         } 

      } 

      //: IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //: mDegTrk.RequiredGroup.wDegreeAuditOL_Value = mDegTrk.RequiredGroup.dReportValue +
         //:                     "  (Manually satisfied: " + mStudenC.ManuallySatisfied.SatisfiedReason + ")"
         {StringBuilder sb_szTempString_18;
         if ( szTempString_18 == null )
            sb_szTempString_18 = new StringBuilder( 32 );
         else
            sb_szTempString_18 = new StringBuilder( szTempString_18 );
                   GetStringFromAttribute( sb_szTempString_18, mDegTrk, "RequiredGroup", "dReportValue" );
         szTempString_18 = sb_szTempString_18.toString( );}
          {StringBuilder sb_szTempString_18;
         if ( szTempString_18 == null )
            sb_szTempString_18 = new StringBuilder( 32 );
         else
            sb_szTempString_18 = new StringBuilder( szTempString_18 );
                  ZeidonStringConcat( sb_szTempString_18, 1, 0, "  (Manually satisfied: ", 1, 0, 255 );
         szTempString_18 = sb_szTempString_18.toString( );}
         {MutableInt mi_lTempInteger_58 = new MutableInt( lTempInteger_58 );
         StringBuilder sb_szTempString_19;
         if ( szTempString_19 == null )
            sb_szTempString_19 = new StringBuilder( 32 );
         else
            sb_szTempString_19 = new StringBuilder( szTempString_19 );
                   GetVariableFromAttribute( sb_szTempString_19, mi_lTempInteger_58, 'S', 51, mStudenC, "ManuallySatisfied", "SatisfiedReason", "", 0 );
         lTempInteger_58 = mi_lTempInteger_58.intValue( );
         szTempString_19 = sb_szTempString_19.toString( );}
          {StringBuilder sb_szTempString_18;
         if ( szTempString_18 == null )
            sb_szTempString_18 = new StringBuilder( 32 );
         else
            sb_szTempString_18 = new StringBuilder( szTempString_18 );
                  ZeidonStringConcat( sb_szTempString_18, 1, 0, szTempString_19, 1, 0, 255 );
         szTempString_18 = sb_szTempString_18.toString( );}
          {StringBuilder sb_szTempString_18;
         if ( szTempString_18 == null )
            sb_szTempString_18 = new StringBuilder( 32 );
         else
            sb_szTempString_18 = new StringBuilder( szTempString_18 );
                  ZeidonStringConcat( sb_szTempString_18, 1, 0, ")", 1, 0, 255 );
         szTempString_18 = sb_szTempString_18.toString( );}
         SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", szTempString_18 );
         //: mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "C"
         SetAttributeFromString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "C" );
      } 

      RESULT = mDegTrk.cursor( "RequiredGroup" ).setNextContinue().toInt();;
      //: END
   } 


   //:END

   //:// Add any Waitlisted courses, though they won't apply to any credit counts or audit results.
   //:// We also won't consider any repeatable classes that have already been taken.
   //:FOR EACH mStudenC.StudentWaitlisted 
   RESULT = mStudenC.cursor( "StudentWaitlisted" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST mDegTrk.CourseType1or2 WITHIN mDegTrk.DegreeTrack
      //:           WHERE mDegTrk.CourseType1or2.ID = mStudenC.StudentWaitlistedCourse.ID 
      {MutableInt mi_lTempInteger_59 = new MutableInt( lTempInteger_59 );
             GetIntegerFromAttribute( mi_lTempInteger_59, mStudenC, "StudentWaitlistedCourse", "ID" );
      lTempInteger_59 = mi_lTempInteger_59.intValue( );}
      RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "ID", lTempInteger_59, "DegreeTrack" ).toInt();
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// There was a match on CourseType1or2. 
         //:// If the entry has already been satisfied, add a new item.
         //:IF mDegTrk.CourseType1or2.wDegreeAuditCourseTaken = ""
         if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseTaken", "" ) == 0 )
         { 
            //:mDegTrk.CourseType1or2.wCourseStatus = "Waitlisted"
            SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", "Waitlisted" );
            //:mDegTrk.CourseType1or2.wCreditsTaken = mStudenC.StudentWaitlistedClass.CreditHours
            SetAttributeFromAttribute( mDegTrk, "CourseType1or2", "wCreditsTaken", mStudenC, "StudentWaitlistedClass", "CreditHours" );
            //:ELSE
         } 
         else
         { 
            //:CreateViewFromView( mDegTrkC, mDegTrk )
            CreateViewFromView( mDegTrkC, mDegTrk );
            //:CREATE ENTITY mDegTrkC.RequiredGroupCourseType1or2 
            RESULT = CreateEntity( mDegTrkC, "RequiredGroupCourseType1or2", zPOS_AFTER );
            //:CREATE ENTITY mDegTrkC.CourseType1or2 
            RESULT = CreateEntity( mDegTrkC, "CourseType1or2", zPOS_AFTER );
            //:SetMatchingAttributesByName( mDegTrkC, "CourseType1or2", mDegTrk, "CourseType1or2", zSET_NULL )
            SetMatchingAttributesByName( mDegTrkC, "CourseType1or2", mDegTrk, "CourseType1or2", zSET_NULL );
            //:IF mStudenC.StudentWaitlistedCourseTopic EXISTS
            lTempInteger_60 = CheckExistenceOfEntity( mStudenC, "StudentWaitlistedCourseTopic" );
            if ( lTempInteger_60 == 0 )
            { 
               //:mDegTrkC.CourseType1or2.Number = mStudenC.StudentWaitlistedCourse.Number + 
               //:                                 mStudenC.StudentWaitlistedCourseTopic.Number 
               {StringBuilder sb_szTempString_20;
               if ( szTempString_20 == null )
                  sb_szTempString_20 = new StringBuilder( 32 );
               else
                  sb_szTempString_20 = new StringBuilder( szTempString_20 );
                               GetStringFromAttribute( sb_szTempString_20, mStudenC, "StudentWaitlistedCourse", "Number" );
               szTempString_20 = sb_szTempString_20.toString( );}
               {StringBuilder sb_szTempString_21;
               if ( szTempString_21 == null )
                  sb_szTempString_21 = new StringBuilder( 32 );
               else
                  sb_szTempString_21 = new StringBuilder( szTempString_21 );
                               GetStringFromAttribute( sb_szTempString_21, mStudenC, "StudentWaitlistedCourseTopic", "Number" );
               szTempString_21 = sb_szTempString_21.toString( );}
                {StringBuilder sb_szTempString_20;
               if ( szTempString_20 == null )
                  sb_szTempString_20 = new StringBuilder( 32 );
               else
                  sb_szTempString_20 = new StringBuilder( szTempString_20 );
                              ZeidonStringConcat( sb_szTempString_20, 1, 0, szTempString_21, 1, 0, 11 );
               szTempString_20 = sb_szTempString_20.toString( );}
               SetAttributeFromString( mDegTrkC, "CourseType1or2", "Number", szTempString_20 );
               //:mDegTrkC.CourseType1or2.Title  = mStudenC.StudentWaitlistedCourseTopic.Title
               SetAttributeFromAttribute( mDegTrkC, "CourseType1or2", "Title", mStudenC, "StudentWaitlistedCourseTopic", "Title" );
               //:ELSE
            } 
            else
            { 
               //:mDegTrkC.CourseType1or2.Number = mStudenC.StudentWaitlistedCourse.Number 
               SetAttributeFromAttribute( mDegTrkC, "CourseType1or2", "Number", mStudenC, "StudentWaitlistedCourse", "Number" );
               //:mDegTrkC.CourseType1or2.Title  = mStudenC.StudentWaitlistedCourse.Title
               SetAttributeFromAttribute( mDegTrkC, "CourseType1or2", "Title", mStudenC, "StudentWaitlistedCourse", "Title" );
            } 

            //:END
            //:mDegTrkC.CourseType1or2.wCourseStatus = "Waitlisted"
            SetAttributeFromString( mDegTrkC, "CourseType1or2", "wCourseStatus", "Waitlisted" );
            //:mDegTrkC.CourseType1or2.wCreditsTaken = mStudenC.StudentWaitlistedClass.CreditHours
            SetAttributeFromAttribute( mDegTrkC, "CourseType1or2", "wCreditsTaken", mStudenC, "StudentWaitlistedClass", "CreditHours" );
            //:DropView( mDegTrkC )
            DropView( mDegTrkC );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// There wasn't a match on CourseType1or2, so try CourseType3or4.
         //:SET CURSOR FIRST mDegTrk.CourseType3or4 WITHIN mDegTrk.DegreeTrack
         //:           WHERE mDegTrk.CourseType3or4.ID = mStudenC.StudentWaitlistedCourse.ID 
         {MutableInt mi_lTempInteger_61 = new MutableInt( lTempInteger_61 );
                   GetIntegerFromAttribute( mi_lTempInteger_61, mStudenC, "StudentWaitlistedCourse", "ID" );
         lTempInteger_61 = mi_lTempInteger_61.intValue( );}
         RESULT = mDegTrk.cursor( "CourseType3or4" ).setFirst( "ID", lTempInteger_61, "DegreeTrack" ).toInt();
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:// There was a match on CourseType3or4.
            //:// If the entry has already been satisfied, add a new item.
            //:IF mDegTrk.CourseType3or4.wDegreeAuditCourseTaken = ""
            if ( CompareAttributeToString( mDegTrk, "CourseType3or4", "wDegreeAuditCourseTaken", "" ) == 0 )
            { 
               //:mDegTrk.CourseType3or4.wCourseStatus = "Waitlisted"
               SetAttributeFromString( mDegTrk, "CourseType3or4", "wCourseStatus", "Waitlisted" );
               //:mDegTrk.CourseType3or4.wCreditsTaken = mStudenC.StudentWaitlistedClass.CreditHours
               SetAttributeFromAttribute( mDegTrk, "CourseType3or4", "wCreditsTaken", mStudenC, "StudentWaitlistedClass", "CreditHours" );
               //:ELSE
            } 
            else
            { 
               //:CreateViewFromView( mDegTrkC, mDegTrk )
               CreateViewFromView( mDegTrkC, mDegTrk );
               //:CREATE ENTITY mDegTrkC.RequiredGroupCourseType3or4 
               RESULT = CreateEntity( mDegTrkC, "RequiredGroupCourseType3or4", zPOS_AFTER );
               //:CREATE ENTITY mDegTrkC.CourseType3or4 
               RESULT = CreateEntity( mDegTrkC, "CourseType3or4", zPOS_AFTER );
               //:SetMatchingAttributesByName( mDegTrkC, "CourseType3or4", mDegTrk, "CourseType3or4", zSET_NULL )
               SetMatchingAttributesByName( mDegTrkC, "CourseType3or4", mDegTrk, "CourseType3or4", zSET_NULL );
               //:IF mStudenC.StudentWaitlistedCourseTopic EXISTS
               lTempInteger_62 = CheckExistenceOfEntity( mStudenC, "StudentWaitlistedCourseTopic" );
               if ( lTempInteger_62 == 0 )
               { 
                  //:mDegTrkC.CourseType3or4.Number = mStudenC.StudentWaitlistedCourse.Number + 
                  //:                                 mStudenC.StudentWaitlistedCourseTopic.Number 
                  {StringBuilder sb_szTempString_22;
                  if ( szTempString_22 == null )
                     sb_szTempString_22 = new StringBuilder( 32 );
                  else
                     sb_szTempString_22 = new StringBuilder( szTempString_22 );
                                     GetStringFromAttribute( sb_szTempString_22, mStudenC, "StudentWaitlistedCourse", "Number" );
                  szTempString_22 = sb_szTempString_22.toString( );}
                  {StringBuilder sb_szTempString_23;
                  if ( szTempString_23 == null )
                     sb_szTempString_23 = new StringBuilder( 32 );
                  else
                     sb_szTempString_23 = new StringBuilder( szTempString_23 );
                                     GetStringFromAttribute( sb_szTempString_23, mStudenC, "StudentWaitlistedCourseTopic", "Number" );
                  szTempString_23 = sb_szTempString_23.toString( );}
                   {StringBuilder sb_szTempString_22;
                  if ( szTempString_22 == null )
                     sb_szTempString_22 = new StringBuilder( 32 );
                  else
                     sb_szTempString_22 = new StringBuilder( szTempString_22 );
                                    ZeidonStringConcat( sb_szTempString_22, 1, 0, szTempString_23, 1, 0, 11 );
                  szTempString_22 = sb_szTempString_22.toString( );}
                  SetAttributeFromString( mDegTrkC, "CourseType3or4", "Number", szTempString_22 );
                  //:mDegTrkC.CourseType3or4.Title  = mStudenC.StudentWaitlistedCourseTopic.Title
                  SetAttributeFromAttribute( mDegTrkC, "CourseType3or4", "Title", mStudenC, "StudentWaitlistedCourseTopic", "Title" );
                  //:ELSE
               } 
               else
               { 
                  //:mDegTrkC.CourseType3or4.Number = mStudenC.StudentWaitlistedCourse.Number 
                  SetAttributeFromAttribute( mDegTrkC, "CourseType3or4", "Number", mStudenC, "StudentWaitlistedCourse", "Number" );
                  //:mDegTrkC.CourseType3or4.Title  = mStudenC.StudentWaitlistedCourse.Title
                  SetAttributeFromAttribute( mDegTrkC, "CourseType3or4", "Title", mStudenC, "StudentWaitlistedCourse", "Title" );
               } 

               //:END
               //:mDegTrkC.CourseType3or4.wCourseStatus = "Waitlisted"
               SetAttributeFromString( mDegTrkC, "CourseType3or4", "wCourseStatus", "Waitlisted" );
               //:mDegTrkC.CourseType3or4.wCreditsTaken = mStudenC.StudentWaitlistedClass.CreditHours
               SetAttributeFromAttribute( mDegTrkC, "CourseType3or4", "wCreditsTaken", mStudenC, "StudentWaitlistedClass", "CreditHours" );
               //:DropView( mDegTrkC )
               DropView( mDegTrkC );
            } 

            //:END
         } 

         //:END
      } 

      RESULT = mStudenC.cursor( "StudentWaitlisted" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:// Mark Course entries "No longer offered" if are no longer Active, unless they are satisfied 
   //:// by a Course.
   //:FOR EACH mDegTrk.CourseType1or2 WITHIN mDegTrk.DegreeTrack 
   RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "DegreeTrack" ).toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mDegTrk.CourseType1or2.Status != "" AND
      //:   mDegTrk.CourseType1or2.Status != "A" AND
      //:   mDegTrk.CourseType1or2.wCourseStatus = ""
      if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "Status", "" ) != 0 && CompareAttributeToString( mDegTrk, "CourseType1or2", "Status", "A" ) != 0 && CompareAttributeToString( mDegTrk, "CourseType1or2", "wCourseStatus", "" ) == 0 )
      { 

         //:mDegTrk.CourseType1or2.wCourseStatus = "No longer offered"
         SetAttributeFromString( mDegTrk, "CourseType1or2", "wCourseStatus", "No longer offered" );
      } 

      RESULT = mDegTrk.cursor( "CourseType1or2" ).setNextContinue().toInt();;
      //:END 
   } 

   //:END
   //:FOR EACH mDegTrk.CourseType3or4 WITHIN mDegTrk.DegreeTrack 
   RESULT = mDegTrk.cursor( "CourseType3or4" ).setFirst( "DegreeTrack" ).toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mDegTrk.CourseType3or4.Status != "" AND
      //:   mDegTrk.CourseType3or4.Status != "A" AND
      //:   mDegTrk.CourseType3or4.wCourseStatus = ""
      if ( CompareAttributeToString( mDegTrk, "CourseType3or4", "Status", "" ) != 0 && CompareAttributeToString( mDegTrk, "CourseType3or4", "Status", "A" ) != 0 && CompareAttributeToString( mDegTrk, "CourseType3or4", "wCourseStatus", "" ) == 0 )
      { 

         //:mDegTrk.CourseType3or4.wCourseStatus = "No longer offered"
         SetAttributeFromString( mDegTrk, "CourseType3or4", "wCourseStatus", "No longer offered" );
      } 

      RESULT = mDegTrk.cursor( "CourseType3or4" ).setNextContinue().toInt();;
      //:END 
   } 

   //:END

   //:// Drop any entries for Generic Courses that have not been satisfied.
   //:/*FOR EACH mDegTrk.CourseType1or2 WITHIN mDegTrk.DegreeTrack 
   //:   IF mDegTrk.CourseType1or2.GenericCourseFlag = "Y" AND
   //:      mDegTrk.CourseType1or2.wCourseStatus = ""
   //:      
   //:      DropEntity( mDegTrk, "RequiredGroupCourseType1or2", zREPOS_NONE )
   //:   END 
   //:END
   //:FOR EACH mDegTrk.CourseType3or4 WITHIN mDegTrk.DegreeTrack 
   //:   IF mDegTrk.CourseType3or4.GenericCourseFlag = "Y" AND
   //:      mDegTrk.CourseType3or4.wCourseStatus = ""
   //:      
   //:      DropEntity( mDegTrk, "RequiredGroupCourseType3or4", zREPOS_NONE )
   //:   END 
   //:END*/

   //:// Delete the general "Developmental" group if it has no entries.
   //:SET CURSOR FIRST mDegTrk.RequiredGroup WITHIN mDegTrk.DegreeTrack 
   //:           WHERE mDegTrk.RequiredGroup.Type = "D" 
   RESULT = mDegTrk.cursor( "RequiredGroup" ).setFirst( "Type", "D", "DegreeTrack" ).toInt();
   //:IF mDegTrk.RequiredGroupCourseType1or2 DOES NOT EXIST
   lTempInteger_63 = CheckExistenceOfEntity( mDegTrk, "RequiredGroupCourseType1or2" );
   if ( lTempInteger_63 != 0 )
   { 
      //:DELETE ENTITY mDegTrk.DegreeTrackRequiredGroup   
      RESULT = DeleteEntity( mDegTrk, "DegreeTrackRequiredGroup", zPOS_NEXT );
   } 

   //:END

   //:// Set total credits applicable to Degree Track.
   //:mDegTrk.DegreeTrack.wDegreeAuditTotalCreditsTaken = TotalDegreeTrackCreditsTaken
   SetAttributeFromDecimal( mDegTrk, "DegreeTrack", "wDegreeAuditTotalCreditsTaken", TotalDegreeTrackCreditsTaken );
   //:mDegTrk.DegreeTrack.wDegreeAuditTotalCreditsEarned = TotalDegreeTrackCreditsEarned
   SetAttributeFromDecimal( mDegTrk, "DegreeTrack", "wDegreeAuditTotalCreditsEarned", TotalDegreeTrackCreditsEarned );

   //:// Set indication whether or not all audit requirements are met. The indication depends on a combination of whether or
   //:// not Class Requirements are met and the value of the Comprehensive Test Score.
   //:// There are three different possibilities for whether Class Requirements are met.
   //:// 1. Classes Satisfied (CS)
   //://    - All RequiredGroup requirements are met by courses completed.
   //://    - The EarnedCredits - DevelopmentCredits are >= MinimumCredits required, as specified by Degree and graduating year.
   //://    - The Student's GPA is >= MinimumGPA, as specified on the Degree Track.
   //:// 2. Classes On Track (CT)
   //://    - All RequiredGroup requirements are met by courses completed and enrolled (not considering Pre_Transfer courses).
   //://    - The EarnedCredits + EnrolledCredits - DevelopmentCredits (Graduation Credits) are >= MinimumCredits required, 
   //://      as specified by Degree and graduating year.
   //://    - The Student's GPA is >= MinimumGPA, as specified on the Degree Track.
   //:// 3. Classes Not Satisfied (CN)
   //://    - If neither of the above is true.

   //:// The final indication value depends one of the above cases and on the value of the Comprehensive Test Score as follows:
   //:// 1. Requirements Satisfied
   //://    - Courses are Satisfied.
   //://    - The ComprehensiveTestScore value is L, H, D, P, N or R.
   //:// 2. Courses Satisfied, No Comp Results
   //://    - Courses are Satisfied.
   //://    - The ComprehensiveTestScore value is null.
   //:// 3. Courses Satisfied, Failed Comp
   //://    - Courses are Satisfied.
   //://    - The ComprehensiveTestScore value is F.
   //:// 4. Courses on Track, Passed Comp
   //://    - Courses on Track
   //://    - The ComprehensiveTestScore value is L, H, D, P, N or R.
   //:// 5. Courses on Track, No Comp Results
   //://    - Courses on Track
   //://    - The ComprehensiveTestScore value is null.
   //:// 6. Courses on Track, Failed Comp
   //://    - Courses on Track
   //://    - The ComprehensiveTestScore value is F.
   //:// 7. Requirements NOT satisfied
   //://    - If none of the above is true.

   //:// Minimum Credits Required
   //:GET VIEW mDegreeLST NAMED "mDegreeLST"
   RESULT = GetViewByName( mDegreeLST, "mDegreeLST", mDegTrk, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mDegreeLST Multiple
      RESULT = ActivateObjectInstance( mDegreeLST, "mDegree", mDegTrk, 0, zMULTIPLE );
      //:NAME VIEW mDegreeLST "mDegreeLST" 
      SetNameForView( mDegreeLST, "mDegreeLST", null, zLEVEL_TASK );
   } 

   //:END
   //:IF mStudent.DC_AnticipatedGraduationDate DOES NOT EXIST
   lTempInteger_64 = CheckExistenceOfEntity( mStudent, "DC_AnticipatedGraduationDate" );
   if ( lTempInteger_64 != 0 )
   { 
      //:MinCreditsRequired = 124
      MinCreditsRequired = 124;
      //:ELSE
   } 
   else
   { 
      //:szWorkDate = mStudent.DC_AnticipatedGraduationDate.Date 
      {MutableInt mi_lTempInteger_65 = new MutableInt( lTempInteger_65 );
      StringBuilder sb_szWorkDate;
      if ( szWorkDate == null )
         sb_szWorkDate = new StringBuilder( 32 );
      else
         sb_szWorkDate = new StringBuilder( szWorkDate );
             GetVariableFromAttribute( sb_szWorkDate, mi_lTempInteger_65, 'S', 9, mStudent, "DC_AnticipatedGraduationDate", "Date", "", 0 );
      lTempInteger_65 = mi_lTempInteger_65.intValue( );
      szWorkDate = sb_szWorkDate.toString( );}
      //:szWorkYear = szWorkDate
       {StringBuilder sb_szWorkYear;
      if ( szWorkYear == null )
         sb_szWorkYear = new StringBuilder( 32 );
      else
         sb_szWorkYear = new StringBuilder( szWorkYear );
            ZeidonStringCopy( sb_szWorkYear, 1, 0, szWorkDate, 1, 0, 5 );
      szWorkYear = sb_szWorkYear.toString( );}
      //:SET CURSOR FIRST mDegreeLST.CollegeDegree WHERE mDegreeLST.CollegeDegree.ID = mStudent.DegreeTrackCollegeDegree.ID 
      {MutableInt mi_lTempInteger_66 = new MutableInt( lTempInteger_66 );
             GetIntegerFromAttribute( mi_lTempInteger_66, mStudent, "DegreeTrackCollegeDegree", "ID" );
      lTempInteger_66 = mi_lTempInteger_66.intValue( );}
      RESULT = mDegreeLST.cursor( "CollegeDegree" ).setFirst( "ID", lTempInteger_66 ).toInt();
      //:SET CURSOR FIRST mDegreeLST.DegreeYearData 
      //:           WHERE mDegreeLST.DegreeYearData.FromYear    <= szWorkYear 
      //:             AND mDegreeLST.DegreeYearData.ThroughYear >= szWorkYear 
      RESULT = mDegreeLST.cursor( "DegreeYearData" ).setFirst().toInt();
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mDegreeLST, "DegreeYearData", "FromYear", szWorkYear ) > 0 || CompareAttributeToString( mDegreeLST, "DegreeYearData", "ThroughYear", szWorkYear ) < 0 ) )
         { 
            RESULT = mDegreeLST.cursor( "DegreeYearData" ).setNextContinue().toInt();;
         } 

      } 

      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:MinCreditsRequired = 124
         MinCreditsRequired = 124;
         //:ELSE
      } 
      else
      { 
         //:MinCreditsRequired = mDegreeLST.DegreeYearData.MinimumCreditsRequired 
         {MutableDouble md_MinCreditsRequired = new MutableDouble( MinCreditsRequired );
                   GetDecimalFromAttribute( md_MinCreditsRequired, mDegreeLST, "DegreeYearData", "MinimumCreditsRequired" );
         MinCreditsRequired = md_MinCreditsRequired.doubleValue( );}
      } 

      //:END 
   } 

   //:END
   //:mDegTrk.DegreeTrack.wMinimumCreditsRequired = MinCreditsRequired
   SetAttributeFromDecimal( mDegTrk, "DegreeTrack", "wMinimumCreditsRequired", MinCreditsRequired );

   //:// Set summary attributes in DegreeTrack.
   //:mDegTrk.DegreeTrack.AuditTotalCreditsEarned       = lTrnscpt.Student.dEarnedCredits 
   SetAttributeFromAttribute( mDegTrk, "DegreeTrack", "AuditTotalCreditsEarned", lTrnscpt, "Student", "dEarnedCredits" );
   //:mDegTrk.DegreeTrack.AuditDevelopmentalCredits     = mDegTrk.DegreeTrack.dDevelopmentalCredits 
   SetAttributeFromAttribute( mDegTrk, "DegreeTrack", "AuditDevelopmentalCredits", mDegTrk, "DegreeTrack", "dDevelopmentalCredits" );
   //:mDegTrk.DegreeTrack.AuditEnrolledCredits          = mStudenC.Student.dEnrolledCredits 
   SetAttributeFromAttribute( mDegTrk, "DegreeTrack", "AuditEnrolledCredits", mStudenC, "Student", "dEnrolledCredits" );
   //:mDegTrk.DegreeTrack.AuditGraduationCredits        = mDegTrk.DegreeTrack.dGraduationCredits
   SetAttributeFromAttribute( mDegTrk, "DegreeTrack", "AuditGraduationCredits", mDegTrk, "DegreeTrack", "dGraduationCredits" );
   //:mDegTrk.DegreeTrack.AuditEnrolledCreditsRepeating = mStudenC.Student.dEnrolledCreditsRepeated 
   SetAttributeFromAttribute( mDegTrk, "DegreeTrack", "AuditEnrolledCreditsRepeating", mStudenC, "Student", "dEnrolledCreditsRepeated" );
   //:mDegTrk.DegreeTrack.AuditPreTransferCredits       = mStudenC.Student.dPreTransferCredits 
   SetAttributeFromAttribute( mDegTrk, "DegreeTrack", "AuditPreTransferCredits", mStudenC, "Student", "dPreTransferCredits" );
   //:mDegTrk.DegreeTrack.AuditMinimumCreditsRequired   = mDegTrk.DegreeTrack.wMinimumCreditsRequired 
   SetAttributeFromAttribute( mDegTrk, "DegreeTrack", "AuditMinimumCreditsRequired", mDegTrk, "DegreeTrack", "wMinimumCreditsRequired" );
   //:mDegTrk.DegreeTrack.AuditGPA                      = lTrnscpt.Student.dGradePointAverage 
   SetAttributeFromAttribute( mDegTrk, "DegreeTrack", "AuditGPA", lTrnscpt, "Student", "dGradePointAverage" );
   //:mDegTrk.DegreeTrack.AuditMajorGPA                 = mStudent.StudentMajorDegreeTrack.dMajorGPA
   SetAttributeFromAttribute( mDegTrk, "DegreeTrack", "AuditMajorGPA", mStudent, "StudentMajorDegreeTrack", "dMajorGPA" );

   //:GraduationCreditsEarned = lTrnscpt.Student.dEarnedCredits - mDegTrk.DegreeTrack.AuditDevelopmentalCredits
   {MutableDouble md_dTempDecimal_21 = new MutableDouble( dTempDecimal_21 );
       GetDecimalFromAttribute( md_dTempDecimal_21, lTrnscpt, "Student", "dEarnedCredits" );
   dTempDecimal_21 = md_dTempDecimal_21.doubleValue( );}
   {MutableDouble md_dTempDecimal_22 = new MutableDouble( dTempDecimal_22 );
       GetDecimalFromAttribute( md_dTempDecimal_22, mDegTrk, "DegreeTrack", "AuditDevelopmentalCredits" );
   dTempDecimal_22 = md_dTempDecimal_22.doubleValue( );}
   GraduationCreditsEarned = dTempDecimal_21 - dTempDecimal_22;

   //:// Compute Local Institution GPA, which will be the AuiditGPA from a copy of the Transcript without
   //:// transfer entries. Thus, if there are not transfer entries, it will be the normal AuditGPA.
   //:SET CURSOR FIRST lTrnscpt.Registration WHERE lTrnscpt.Registration.Status = "F" OR lTrnscpt.Registration.Status = "X"
   RESULT = lTrnscpt.cursor( "Registration" ).setFirst().toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( lTrnscpt, "Registration", "Status", "F" ) != 0 && CompareAttributeToString( lTrnscpt, "Registration", "Status", "X" ) != 0 ) )
      { 
         RESULT = lTrnscpt.cursor( "Registration" ).setNextContinue().toInt();;
      } 

   } 

   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:// There are transfer records, so create a copy of the transcript and eliminate transfer records.
      //:ActivateOI_FromOI( lTrnscptT, lTrnscpt, zSINGLE )
      ActivateOI_FromOI( lTrnscptT, lTrnscpt, zSINGLE );
      //:FOR EACH lTrnscptT.PrintGroup 
      RESULT = lTrnscptT.cursor( "PrintGroup" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:DropEntity( lTrnscptT, "PrintGroup", zREPOS_NONE )
         DropEntity( lTrnscptT, "PrintGroup", zREPOS_NONE );
         RESULT = lTrnscptT.cursor( "PrintGroup" ).setNextContinue().toInt();;
      } 

      //:END
      //:FOR EACH lTrnscptT.TranscriptGroup 
      RESULT = lTrnscptT.cursor( "TranscriptGroup" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF lTrnscptT.TranscriptGroup.wRecordType = "T"
         if ( CompareAttributeToString( lTrnscptT, "TranscriptGroup", "wRecordType", "T" ) == 0 )
         { 
            //:DropEntity( lTrnscptT, "TranscriptGroup", zREPOS_NONE )
            DropEntity( lTrnscptT, "TranscriptGroup", zREPOS_NONE );
         } 

         RESULT = lTrnscptT.cursor( "TranscriptGroup" ).setNextContinue().toInt();;
         //:END
      } 

      //:END
      //:BuildPrintGroup( lTrnscptT )
      {
       lTrnscpt_Object m_lTrnscpt_Object = new lTrnscpt_Object( lTrnscptT );
       m_lTrnscpt_Object.olTrnscpt_BuildPrintGroup( lTrnscptT );
       // m_lTrnscpt_Object = null;  // permit gc  (unnecessary)
      }
      //:mDegTrk.DegreeTrack.AuditLocalCollegeGPA = lTrnscptT.Student.dGradePointAverage
      SetAttributeFromAttribute( mDegTrk, "DegreeTrack", "AuditLocalCollegeGPA", lTrnscptT, "Student", "dGradePointAverage" );
      //:DropObjectInstance( lTrnscptT )
      DropObjectInstance( lTrnscptT );
      //:ELSE
   } 
   else
   { 
      //:// There are not transfer records, so use AuditGPA.
      //:mDegTrk.DegreeTrack.AuditLocalCollegeGPA = mDegTrk.DegreeTrack.AuditGPA 
      SetAttributeFromAttribute( mDegTrk, "DegreeTrack", "AuditLocalCollegeGPA", mDegTrk, "DegreeTrack", "AuditGPA" );
   } 

   //:END

   //:// Minimum GPA
   //:IF mDegTrk.DegreeTrack.MinimumGPA = ""
   if ( CompareAttributeToString( mDegTrk, "DegreeTrack", "MinimumGPA", "" ) == 0 )
   { 
      //:MessageSend( mDegTrk, "", "Degree Audit", 
      //:             "Minimum GPA is not set for the Degree Track. '2.0' will be used.",
      //:             zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
      MessageSend( mDegTrk, "", "Degree Audit", "Minimum GPA is not set for the Degree Track. '2.0' will be used.", zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
      //:MinimumGPA = 2.0
      MinimumGPA = 2.0;
      //:ELSE
   } 
   else
   { 
      //:MinimumGPA = mDegTrk.DegreeTrack.MinimumGPA
      {MutableDouble md_MinimumGPA = new MutableDouble( MinimumGPA );
             GetDecimalFromAttribute( md_MinimumGPA, mDegTrk, "DegreeTrack", "MinimumGPA" );
      MinimumGPA = md_MinimumGPA.doubleValue( );}
   } 

   //:END

   //:SET CURSOR FIRST mDegTrk.RequiredGroup WITHIN mDegTrk.DegreeTrack 
   //:           WHERE mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "N" OR
   //:                 mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "T"
   RESULT = mDegTrk.cursor( "RequiredGroup" ).setFirst( "DegreeTrack" ).toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "N" ) != 0 && CompareAttributeToString( mDegTrk, "RequiredGroup", "wDegreeAuditSatisfied", "T" ) != 0 ) )
      { 
         RESULT = mDegTrk.cursor( "RequiredGroup" ).setNextContinue().toInt();;
      } 

   } 

   //:IF RESULT < zCURSOR_SET AND    // All group requirements are met as Completed
   //:   GraduationCreditsEarned >= MinCreditsRequired AND 
   //:   lTrnscpt.Student.dGradePointAverage >= MinimumGPA
   if ( RESULT < zCURSOR_SET && GraduationCreditsEarned >= MinCreditsRequired && CompareAttributeToDecimal( lTrnscpt, "Student", "dGradePointAverage", MinimumGPA ) >= 0 )
   { 

      //:mDegTrk.DegreeTrack.wDegreeAuditSatisfiedDescription = "Courses SATISFIED"
      SetAttributeFromString( mDegTrk, "DegreeTrack", "wDegreeAuditSatisfiedDescription", "Courses SATISFIED" );
      //:ELSE
   } 
   else
   { 
      //:SET CURSOR FIRST mDegTrk.RequiredGroup WITHIN mDegTrk.DegreeTrack 
      //:           WHERE mDegTrk.RequiredGroup.wDegreeAuditSatisfied = "N" 
      RESULT = mDegTrk.cursor( "RequiredGroup" ).setFirst( "wDegreeAuditSatisfied", "N", "DegreeTrack" ).toInt();
      //:IF RESULT < zCURSOR_SET AND    // All group requirements are met as Taken or Completed
      //:   mDegTrk.DegreeTrack.AuditGraduationCredits >= MinCreditsRequired AND 
      //:   lTrnscpt.Student.dGradePointAverage >= MinimumGPA
      if ( RESULT < zCURSOR_SET && CompareAttributeToDecimal( mDegTrk, "DegreeTrack", "AuditGraduationCredits", MinCreditsRequired ) >= 0 && CompareAttributeToDecimal( lTrnscpt, "Student", "dGradePointAverage", MinimumGPA ) >= 0 )
      { 

         //:mDegTrk.DegreeTrack.wDegreeAuditSatisfiedDescription = "Courses on Track"
         SetAttributeFromString( mDegTrk, "DegreeTrack", "wDegreeAuditSatisfiedDescription", "Courses on Track" );
         //:ELSE
      } 
      else
      { 
         //:mDegTrk.DegreeTrack.wDegreeAuditSatisfiedDescription = "Courses NOT satisfied"
         SetAttributeFromString( mDegTrk, "DegreeTrack", "wDegreeAuditSatisfiedDescription", "Courses NOT satisfied" );
      } 

      //:END
   } 

   //:END

   //:// Make sure the RequiredGroup entries are sequenced.
   //:Count = 0
   Count = 0;
   //:FOR EACH mDegTrk.DegreeTrackRequiredGroup 
   RESULT = mDegTrk.cursor( "DegreeTrackRequiredGroup" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:Count = Count + 1
      Count = Count + 1;
      //:mDegTrk.DegreeTrackRequiredGroup.SequenceNumber = Count 
      SetAttributeFromInteger( mDegTrk, "DegreeTrackRequiredGroup", "SequenceNumber", Count );
      RESULT = mDegTrk.cursor( "DegreeTrackRequiredGroup" ).setNextContinue().toInt();;
   } 

   //:END

   //:SET CURSOR FIRST mDegTrk.DegreeTrack
   RESULT = mDegTrk.cursor( "DegreeTrack" ).setFirst().toInt();
   //:SetSelectStateOfEntity( mDegTrk, "DegreeTrack", 1 )
   SetSelectStateOfEntity( mDegTrk, "DegreeTrack", 1 );
   //:mDegTrk.DegreeTrack.wShowHideToggleStatus = "A"
   SetAttributeFromString( mDegTrk, "DegreeTrack", "wShowHideToggleStatus", "A" );

   //:// Set up the copy object for showing and contracting entries.
   //:ActivateOI_FromOI( mDegTrkC, mDegTrk, zSINGLE )
   ActivateOI_FromOI( mDegTrkC, mDegTrk, zSINGLE );
   //:NAME VIEW mDegTrkC "mDegTrkCopy"
   SetNameForView( mDegTrkC, "mDegTrkCopy", null, zLEVEL_TASK );

   //:DropObjectInstance( mStudenC )
   DropObjectInstance( mStudenC );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dAuditCourseOL_Value( VIEW mDegTrk BASED ON LOD mDegTrk,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )

//:   STRING ( 200 ) szOL_Value
public int 
omDegTrk_dAuditCourseOL_Value( View     mDegTrk,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   String   szOL_Value = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:STRING ( 100 ) szName
   String   szName = null;
   //:STRING ( 50 )  szAuditResult
   String   szAuditResult = null;
   //:STRING ( 5 )   szFinalGrade
   String   szFinalGrade = null;
   //:STRING ( 10 )  szCreditHours
   String   szCreditHours = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mDegTrk );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "CourseType1or2"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "CourseType1or2", 1, 0, 33 ) == 0 )
         { 
            //:szName = mDegTrk.CourseType1or2.Title
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                         GetVariableFromAttribute( sb_szName, mi_lTempInteger_0, 'S', 101, mDegTrk, "CourseType1or2", "Title", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szName = sb_szName.toString( );}
            //:szOL_Value = mDegTrk.CourseType1or2.Number + " - " + szName
            {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                         GetStringFromAttribute( sb_szOL_Value, mDegTrk, "CourseType1or2", "Number" );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, " - ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szName, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
            //:IF mDegTrk.CourseType1or2.wCourseStatus != ""
            if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "wCourseStatus", "" ) != 0 )
            { 
               //:IF mDegTrk.CourseType1or2.wCourseStatus = "No longer offered"
               if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "wCourseStatus", "No longer offered" ) == 0 )
               { 
                  //:szOL_Value = szOL_Value + "  (No longer offered)"
                   {StringBuilder sb_szOL_Value;
                  if ( szOL_Value == null )
                     sb_szOL_Value = new StringBuilder( 32 );
                  else
                     sb_szOL_Value = new StringBuilder( szOL_Value );
                                    ZeidonStringConcat( sb_szOL_Value, 1, 0, "  (No longer offered)", 1, 0, 201 );
                  szOL_Value = sb_szOL_Value.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:GetStringFromAttributeByContext( szAuditResult,
                  //:                              mDegTrk, "CourseType1or2", "wCourseStatus", "AuditDisplay", 50 )
                  {StringBuilder sb_szAuditResult;
                  if ( szAuditResult == null )
                     sb_szAuditResult = new StringBuilder( 32 );
                  else
                     sb_szAuditResult = new StringBuilder( szAuditResult );
                                     GetStringFromAttributeByContext( sb_szAuditResult, mDegTrk, "CourseType1or2", "wCourseStatus", "AuditDisplay", 50 );
                  szAuditResult = sb_szAuditResult.toString( );}
                  //:szCreditHours = mDegTrk.CourseType1or2.wCreditsTaken 
                  {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                  StringBuilder sb_szCreditHours;
                  if ( szCreditHours == null )
                     sb_szCreditHours = new StringBuilder( 32 );
                  else
                     sb_szCreditHours = new StringBuilder( szCreditHours );
                                     GetVariableFromAttribute( sb_szCreditHours, mi_lTempInteger_1, 'S', 11, mDegTrk, "CourseType1or2", "wCreditsTaken", "", 0 );
                  lTempInteger_1 = mi_lTempInteger_1.intValue( );
                  szCreditHours = sb_szCreditHours.toString( );}
                  //:szOL_Value = szOL_Value + " (" + szAuditResult + ", Cr: " + szCreditHours
                   {StringBuilder sb_szOL_Value;
                  if ( szOL_Value == null )
                     sb_szOL_Value = new StringBuilder( 32 );
                  else
                     sb_szOL_Value = new StringBuilder( szOL_Value );
                                    ZeidonStringConcat( sb_szOL_Value, 1, 0, " (", 1, 0, 201 );
                  szOL_Value = sb_szOL_Value.toString( );}
                   {StringBuilder sb_szOL_Value;
                  if ( szOL_Value == null )
                     sb_szOL_Value = new StringBuilder( 32 );
                  else
                     sb_szOL_Value = new StringBuilder( szOL_Value );
                                    ZeidonStringConcat( sb_szOL_Value, 1, 0, szAuditResult, 1, 0, 201 );
                  szOL_Value = sb_szOL_Value.toString( );}
                   {StringBuilder sb_szOL_Value;
                  if ( szOL_Value == null )
                     sb_szOL_Value = new StringBuilder( 32 );
                  else
                     sb_szOL_Value = new StringBuilder( szOL_Value );
                                    ZeidonStringConcat( sb_szOL_Value, 1, 0, ", Cr: ", 1, 0, 201 );
                  szOL_Value = sb_szOL_Value.toString( );}
                   {StringBuilder sb_szOL_Value;
                  if ( szOL_Value == null )
                     sb_szOL_Value = new StringBuilder( 32 );
                  else
                     sb_szOL_Value = new StringBuilder( szOL_Value );
                                    ZeidonStringConcat( sb_szOL_Value, 1, 0, szCreditHours, 1, 0, 201 );
                  szOL_Value = sb_szOL_Value.toString( );}
                  //:szFinalGrade = mDegTrk.CourseType1or2.wFinalGrade
                  {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                  StringBuilder sb_szFinalGrade;
                  if ( szFinalGrade == null )
                     sb_szFinalGrade = new StringBuilder( 32 );
                  else
                     sb_szFinalGrade = new StringBuilder( szFinalGrade );
                                     GetVariableFromAttribute( sb_szFinalGrade, mi_lTempInteger_2, 'S', 6, mDegTrk, "CourseType1or2", "wFinalGrade", "", 0 );
                  lTempInteger_2 = mi_lTempInteger_2.intValue( );
                  szFinalGrade = sb_szFinalGrade.toString( );}
                  //:IF szFinalGrade = ""
                  if ( ZeidonStringCompare( szFinalGrade, 1, 0, "", 1, 0, 6 ) == 0 )
                  { 
                     //:szOL_Value = szOL_Value + " )"
                      {StringBuilder sb_szOL_Value;
                     if ( szOL_Value == null )
                        sb_szOL_Value = new StringBuilder( 32 );
                     else
                        sb_szOL_Value = new StringBuilder( szOL_Value );
                                          ZeidonStringConcat( sb_szOL_Value, 1, 0, " )", 1, 0, 201 );
                     szOL_Value = sb_szOL_Value.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:szOL_Value = szOL_Value + ", Grade: " + szFinalGrade + " )"
                      {StringBuilder sb_szOL_Value;
                     if ( szOL_Value == null )
                        sb_szOL_Value = new StringBuilder( 32 );
                     else
                        sb_szOL_Value = new StringBuilder( szOL_Value );
                                          ZeidonStringConcat( sb_szOL_Value, 1, 0, ", Grade: ", 1, 0, 201 );
                     szOL_Value = sb_szOL_Value.toString( );}
                      {StringBuilder sb_szOL_Value;
                     if ( szOL_Value == null )
                        sb_szOL_Value = new StringBuilder( 32 );
                     else
                        sb_szOL_Value = new StringBuilder( szOL_Value );
                                          ZeidonStringConcat( sb_szOL_Value, 1, 0, szFinalGrade, 1, 0, 201 );
                     szOL_Value = sb_szOL_Value.toString( );}
                      {StringBuilder sb_szOL_Value;
                     if ( szOL_Value == null )
                        sb_szOL_Value = new StringBuilder( 32 );
                     else
                        sb_szOL_Value = new StringBuilder( szOL_Value );
                                          ZeidonStringConcat( sb_szOL_Value, 1, 0, " )", 1, 0, 201 );
                     szOL_Value = sb_szOL_Value.toString( );}
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
            //:szName = mDegTrk.CourseType3or4.Title
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                         GetVariableFromAttribute( sb_szName, mi_lTempInteger_3, 'S', 101, mDegTrk, "CourseType3or4", "Title", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szName = sb_szName.toString( );}
            //:szOL_Value = mDegTrk.CourseType3or4.Number + " - " + szName
            {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                         GetStringFromAttribute( sb_szOL_Value, mDegTrk, "CourseType3or4", "Number" );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, " - ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szName, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
            //:IF mDegTrk.CourseType3or4.wCourseStatus != ""
            if ( CompareAttributeToString( mDegTrk, "CourseType3or4", "wCourseStatus", "" ) != 0 )
            { 
               //:IF mDegTrk.CourseType3or4.wCourseStatus = "No longer offered"
               if ( CompareAttributeToString( mDegTrk, "CourseType3or4", "wCourseStatus", "No longer offered" ) == 0 )
               { 
                  //:szOL_Value = szOL_Value + "  (No longer offered)"
                   {StringBuilder sb_szOL_Value;
                  if ( szOL_Value == null )
                     sb_szOL_Value = new StringBuilder( 32 );
                  else
                     sb_szOL_Value = new StringBuilder( szOL_Value );
                                    ZeidonStringConcat( sb_szOL_Value, 1, 0, "  (No longer offered)", 1, 0, 201 );
                  szOL_Value = sb_szOL_Value.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:GetStringFromAttributeByContext( szAuditResult,
                  //:                              mDegTrk, "CourseType3or4", "wCourseStatus", "AuditDisplay", 50 )
                  {StringBuilder sb_szAuditResult;
                  if ( szAuditResult == null )
                     sb_szAuditResult = new StringBuilder( 32 );
                  else
                     sb_szAuditResult = new StringBuilder( szAuditResult );
                                     GetStringFromAttributeByContext( sb_szAuditResult, mDegTrk, "CourseType3or4", "wCourseStatus", "AuditDisplay", 50 );
                  szAuditResult = sb_szAuditResult.toString( );}
                  //:szCreditHours = mDegTrk.CourseType3or4.wCreditsTaken 
                  {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                  StringBuilder sb_szCreditHours;
                  if ( szCreditHours == null )
                     sb_szCreditHours = new StringBuilder( 32 );
                  else
                     sb_szCreditHours = new StringBuilder( szCreditHours );
                                     GetVariableFromAttribute( sb_szCreditHours, mi_lTempInteger_4, 'S', 11, mDegTrk, "CourseType3or4", "wCreditsTaken", "", 0 );
                  lTempInteger_4 = mi_lTempInteger_4.intValue( );
                  szCreditHours = sb_szCreditHours.toString( );}
                  //:szOL_Value = szOL_Value + " (" + szAuditResult + ", Cr: " + szCreditHours
                   {StringBuilder sb_szOL_Value;
                  if ( szOL_Value == null )
                     sb_szOL_Value = new StringBuilder( 32 );
                  else
                     sb_szOL_Value = new StringBuilder( szOL_Value );
                                    ZeidonStringConcat( sb_szOL_Value, 1, 0, " (", 1, 0, 201 );
                  szOL_Value = sb_szOL_Value.toString( );}
                   {StringBuilder sb_szOL_Value;
                  if ( szOL_Value == null )
                     sb_szOL_Value = new StringBuilder( 32 );
                  else
                     sb_szOL_Value = new StringBuilder( szOL_Value );
                                    ZeidonStringConcat( sb_szOL_Value, 1, 0, szAuditResult, 1, 0, 201 );
                  szOL_Value = sb_szOL_Value.toString( );}
                   {StringBuilder sb_szOL_Value;
                  if ( szOL_Value == null )
                     sb_szOL_Value = new StringBuilder( 32 );
                  else
                     sb_szOL_Value = new StringBuilder( szOL_Value );
                                    ZeidonStringConcat( sb_szOL_Value, 1, 0, ", Cr: ", 1, 0, 201 );
                  szOL_Value = sb_szOL_Value.toString( );}
                   {StringBuilder sb_szOL_Value;
                  if ( szOL_Value == null )
                     sb_szOL_Value = new StringBuilder( 32 );
                  else
                     sb_szOL_Value = new StringBuilder( szOL_Value );
                                    ZeidonStringConcat( sb_szOL_Value, 1, 0, szCreditHours, 1, 0, 201 );
                  szOL_Value = sb_szOL_Value.toString( );}
                  //:szFinalGrade = mDegTrk.CourseType3or4.wFinalGrade 
                  {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                  StringBuilder sb_szFinalGrade;
                  if ( szFinalGrade == null )
                     sb_szFinalGrade = new StringBuilder( 32 );
                  else
                     sb_szFinalGrade = new StringBuilder( szFinalGrade );
                                     GetVariableFromAttribute( sb_szFinalGrade, mi_lTempInteger_5, 'S', 6, mDegTrk, "CourseType3or4", "wFinalGrade", "", 0 );
                  lTempInteger_5 = mi_lTempInteger_5.intValue( );
                  szFinalGrade = sb_szFinalGrade.toString( );}
                  //:IF szFinalGrade = ""
                  if ( ZeidonStringCompare( szFinalGrade, 1, 0, "", 1, 0, 6 ) == 0 )
                  { 
                     //:szOL_Value = szOL_Value + " )"
                      {StringBuilder sb_szOL_Value;
                     if ( szOL_Value == null )
                        sb_szOL_Value = new StringBuilder( 32 );
                     else
                        sb_szOL_Value = new StringBuilder( szOL_Value );
                                          ZeidonStringConcat( sb_szOL_Value, 1, 0, " )", 1, 0, 201 );
                     szOL_Value = sb_szOL_Value.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:szOL_Value = szOL_Value + ", Grade: " + szFinalGrade + " )"
                      {StringBuilder sb_szOL_Value;
                     if ( szOL_Value == null )
                        sb_szOL_Value = new StringBuilder( 32 );
                     else
                        sb_szOL_Value = new StringBuilder( szOL_Value );
                                          ZeidonStringConcat( sb_szOL_Value, 1, 0, ", Grade: ", 1, 0, 201 );
                     szOL_Value = sb_szOL_Value.toString( );}
                      {StringBuilder sb_szOL_Value;
                     if ( szOL_Value == null )
                        sb_szOL_Value = new StringBuilder( 32 );
                     else
                        sb_szOL_Value = new StringBuilder( szOL_Value );
                                          ZeidonStringConcat( sb_szOL_Value, 1, 0, szFinalGrade, 1, 0, 201 );
                     szOL_Value = sb_szOL_Value.toString( );}
                      {StringBuilder sb_szOL_Value;
                     if ( szOL_Value == null )
                        sb_szOL_Value = new StringBuilder( 32 );
                     else
                        sb_szOL_Value = new StringBuilder( szOL_Value );
                                          ZeidonStringConcat( sb_szOL_Value, 1, 0, " )", 1, 0, 201 );
                     szOL_Value = sb_szOL_Value.toString( );}
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:StoreStringInRecord ( mDegTrk,
         //:                   InternalEntityStructure, InternalAttribStructure, szOL_Value )
         StoreStringInRecord( mDegTrk, InternalEntityStructure, InternalAttribStructure, szOL_Value );
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
//:dCreditsPromptText( VIEW mDegTrk BASED ON LOD mDegTrk,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   STRING ( 50 ) szValue
public int 
omDegTrk_dCreditsPromptText( View     mDegTrk,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szValue = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :


         //:IF mDegTrk.RequiredGroup.Type = "3"
         if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "3" ) == 0 )
         { 
            //:szValue = "Minimum Credits:"
             {StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                        ZeidonStringCopy( sb_szValue, 1, 0, "Minimum Credits:", 1, 0, 51 );
            szValue = sb_szValue.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szValue = "Required Credits:"
             {StringBuilder sb_szValue;
            if ( szValue == null )
               sb_szValue = new StringBuilder( 32 );
            else
               sb_szValue = new StringBuilder( szValue );
                        ZeidonStringCopy( sb_szValue, 1, 0, "Required Credits:", 1, 0, 51 );
            szValue = sb_szValue.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mDegTrk,
         //:                   InternalEntityStructure, InternalAttribStructure, szValue )
         StoreStringInRecord( mDegTrk, InternalEntityStructure, InternalAttribStructure, szValue );
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


//:OBJECT CONSTRAINT OPERATION
//:ObjectConstraints( VIEW mDegTrk BASED ON LOD mDegTrk,
//:                   SHORT Event,
//:                   SHORT State )
//:   
//:   INTEGER Count
public int 
omDegTrk_ObjectConstraints( View     mDegTrk,
                            Integer   Event,
                            Integer   State )
{
   int      Count = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;


   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:// Order RequiredGroups by SequenceNumber.
         //:OrderEntityForView( mDegTrk, "DegreeTrackRequiredGroup", "SequenceNumber A" )
         OrderEntityForView( mDegTrk, "DegreeTrackRequiredGroup", "SequenceNumber A" );

         //:// Order Courses in subobjects by Number.
         //:FOR EACH mDegTrk.DegreeTrackRequiredGroup 
         RESULT = mDegTrk.cursor( "DegreeTrackRequiredGroup" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mDegTrk.RequiredGroupCourseType1or2 EXISTS
            lTempInteger_0 = CheckExistenceOfEntity( mDegTrk, "RequiredGroupCourseType1or2" );
            if ( lTempInteger_0 == 0 )
            { 
               //:OrderEntityForView( mDegTrk, "RequiredGroupCourseType1or2", "CourseType1or2.Number A" )
               OrderEntityForView( mDegTrk, "RequiredGroupCourseType1or2", "CourseType1or2.Number A" );
            } 

            //:END
            //:FOR EACH mDegTrk.RequiredSubGroup 
            RESULT = mDegTrk.cursor( "RequiredSubGroup" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:OrderEntityForView( mDegTrk, "RequiredGroupCourseType3or4", "CourseType3or4.Number A" )
               OrderEntityForView( mDegTrk, "RequiredGroupCourseType3or4", "CourseType3or4.Number A" );
               RESULT = mDegTrk.cursor( "RequiredSubGroup" ).setNextContinue().toInt();;
            } 

            RESULT = mDegTrk.cursor( "DegreeTrackRequiredGroup" ).setNextContinue().toInt();;
            //:END 
         } 

         //:END
         //:SET CURSOR FIRST mDegTrk.RequiredGroup 
         RESULT = mDegTrk.cursor( "RequiredGroup" ).setFirst().toInt();

         //:// Set default Degree Audit Report Title.
         //:mDegTrk.DegreeTrack.wDegreeAuditTitle = "Degree Audit For"
         SetAttributeFromString( mDegTrk, "DegreeTrack", "wDegreeAuditTitle", "Degree Audit For" );

         //:// Temporary Warning Message: This should be moved to a database attribute which is set in the
         //:// Administrative dialog.
         //:mDegTrk.DegreeTrack.wTempAuditWarning = "This degree audit is a planning tool to help students track their progress " +
         //:                                  "toward meeting degree requirements. It is not a contract between ENC and its " +
         //:                                  "students. Although ENC has attempted to ensure that the results presented in " +
         //:                                  "this audit are accurate and complete at the time of printing, the results are " +
         //:                                  "subject to a final review of the Registrar's Office to unilaterally add, " +
         //:                                  "withdraw or revise the contents."
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringCopy( sb_szTempString_0, 1, 0, "This degree audit is a planning tool to help students track their progress ", 1, 0, 10001 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, "toward meeting degree requirements. It is not a contract between ENC and its ", 1, 0, 10001 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, "students. Although ENC has attempted to ensure that the results presented in ", 1, 0, 10001 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, "this audit are accurate and complete at the time of printing, the results are ", 1, 0, 10001 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, "subject to a final review of the Registrar's Office to unilaterally add, ", 1, 0, 10001 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, "withdraw or revise the contents.", 1, 0, 10001 );
         szTempString_0 = sb_szTempString_0.toString( );}
         SetAttributeFromString( mDegTrk, "DegreeTrack", "wTempAuditWarning", szTempString_0 );
         break ;

      //:  /* end zOCE_ACTIVATE */
      //:OF   zOCE_ACTIVATE_EMPTY:
      case zOCE_ACTIVATE_EMPTY :
         break ;

      //:  /* end zOCE_ACTIVATE_EMPTY */
      //:OF   zOCE_COMMIT:
      case zOCE_COMMIT :

         //:// Make sure the RequiredGroup entries are sequenced.
         //:Count = 0
         Count = 0;
         //:FOR EACH mDegTrk.DegreeTrackRequiredGroup 
         RESULT = mDegTrk.cursor( "DegreeTrackRequiredGroup" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:Count = Count + 1
            Count = Count + 1;
            //:mDegTrk.DegreeTrackRequiredGroup.SequenceNumber = Count 
            SetAttributeFromInteger( mDegTrk, "DegreeTrackRequiredGroup", "SequenceNumber", Count );
            RESULT = mDegTrk.cursor( "DegreeTrackRequiredGroup" ).setNextContinue().toInt();;
         } 

         //:END
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


//:LOCAL OPERATION
//:CheckForRepeatable( VIEW mDegTrk  BASED ON LOD mDegTrk,
//:                    VIEW mStudenC BASED ON LOD mStudenC,
//:                    STRING ( 32 ) EntryType )

//:   VIEW mDegTrk2 BASED ON LOD mDegTrk
private int 
omDegTrk_CheckForRepeatable( View     mDegTrk,
                             View     mStudenC,
                             String   EntryType )
{
   zVIEW    mDegTrk2 = new zVIEW( );
   //:STRING ( 9 ) PreTransferCheck
   String   PreTransferCheck = null;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   int      lTempInteger_1 = 0;


   //:// Duplicate the Course entry if it already has an entry defined against it.
   //:IF EntryType = "1" OR EntryType = "2"
   if ( ZeidonStringCompare( EntryType, 1, 0, "1", 1, 0, 33 ) == 0 || ZeidonStringCompare( EntryType, 1, 0, "2", 1, 0, 33 ) == 0 )
   { 
      //:PreTransferCheck = mDegTrk.CourseType1or2.wCourseStatus 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_PreTransferCheck;
      if ( PreTransferCheck == null )
         sb_PreTransferCheck = new StringBuilder( 32 );
      else
         sb_PreTransferCheck = new StringBuilder( PreTransferCheck );
             GetVariableFromAttribute( sb_PreTransferCheck, mi_lTempInteger_0, 'S', 10, mDegTrk, "CourseType1or2", "wCourseStatus", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      PreTransferCheck = sb_PreTransferCheck.toString( );}
      //:IF mDegTrk.CourseType1or2.wDegreeAuditCourseTaken = "Y" OR
      //:   PreTransferCheck = "Pre-Trans"
      if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "wDegreeAuditCourseTaken", "Y" ) == 0 || ZeidonStringCompare( PreTransferCheck, 1, 0, "Pre-Trans", 1, 0, 10 ) == 0 )
      { 

         //:// This an additional entry for a repeatable course, so add another RequiredGroupCourse subobject.
         //:CreateViewFromView( mDegTrk2, mDegTrk ) 
         CreateViewFromView( mDegTrk2, mDegTrk );
         //:CREATE ENTITY mDegTrk.RequiredGroupCourseType1or2 
         RESULT = CreateEntity( mDegTrk, "RequiredGroupCourseType1or2", zPOS_AFTER );
         //:CREATE ENTITY mDegTrk.CourseType1or2 
         RESULT = CreateEntity( mDegTrk, "CourseType1or2", zPOS_AFTER );
         //:SetMatchingAttributesByName( mDegTrk,  "CourseType1or2",
         //:                             mDegTrk2, "CourseType1or2", zSET_ALL )
         SetMatchingAttributesByName( mDegTrk, "CourseType1or2", mDegTrk2, "CourseType1or2", zSET_ALL );
         //:mDegTrk.CourseType1or2.wCreditsTaken = ""
         SetAttributeFromString( mDegTrk, "CourseType1or2", "wCreditsTaken", "" );
         //:mDegTrk.CourseType1or2.wFinalGrade   = ""
         SetAttributeFromString( mDegTrk, "CourseType1or2", "wFinalGrade", "" );
         //:DropView( mDegTrk2 )
         DropView( mDegTrk2 );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:PreTransferCheck = mDegTrk.CourseType3or4.wCourseStatus 
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_PreTransferCheck;
      if ( PreTransferCheck == null )
         sb_PreTransferCheck = new StringBuilder( 32 );
      else
         sb_PreTransferCheck = new StringBuilder( PreTransferCheck );
             GetVariableFromAttribute( sb_PreTransferCheck, mi_lTempInteger_1, 'S', 10, mDegTrk, "CourseType3or4", "wCourseStatus", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      PreTransferCheck = sb_PreTransferCheck.toString( );}
      //:IF mDegTrk.CourseType3or4.wDegreeAuditCourseTaken = "Y" OR
      //:   PreTransferCheck = "Pre-Trans"
      if ( CompareAttributeToString( mDegTrk, "CourseType3or4", "wDegreeAuditCourseTaken", "Y" ) == 0 || ZeidonStringCompare( PreTransferCheck, 1, 0, "Pre-Trans", 1, 0, 10 ) == 0 )
      { 

         //:// This an additional entry for a repeatable course, so add another RequiredGroupCourse subobject.
         //:CreateViewFromView( mDegTrk2, mDegTrk ) 
         CreateViewFromView( mDegTrk2, mDegTrk );
         //:CREATE ENTITY mDegTrk.RequiredGroupCourseType3or4 
         RESULT = CreateEntity( mDegTrk, "RequiredGroupCourseType3or4", zPOS_AFTER );
         //:CREATE ENTITY mDegTrk.CourseType3or4 
         RESULT = CreateEntity( mDegTrk, "CourseType3or4", zPOS_AFTER );
         //:SetMatchingAttributesByName( mDegTrk,  "CourseType3or4",
         //:                             mDegTrk2, "CourseType3or4", zSET_ALL )
         SetMatchingAttributesByName( mDegTrk, "CourseType3or4", mDegTrk2, "CourseType3or4", zSET_ALL );
         //:mDegTrk.CourseType3or4.wCreditsTaken = ""
         SetAttributeFromString( mDegTrk, "CourseType3or4", "wCreditsTaken", "" );
         //:mDegTrk.CourseType3or4.wFinalGrade   = ""
         SetAttributeFromString( mDegTrk, "CourseType3or4", "wFinalGrade", "" );
         //:DropView( mDegTrk2 )
         DropView( mDegTrk2 );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:CombineDegreeTracks( VIEW mReturnedTrack BASED ON LOD mDegTrk,
//:                     VIEW mStudent       BASED ON LOD mStudent )

//:   VIEW mMajorTrack BASED ON LOD mDegTrk
public int 
omDegTrk_CombineDegreeTracks( zVIEW    mReturnedTrack,
                              View     mStudent )
{
   zVIEW    mMajorTrack = new zVIEW( );
   //:VIEW mMinorTrack BASED ON LOD mDegTrk
   zVIEW    mMinorTrack = new zVIEW( );
   //:VIEW mStudenC    BASED ON LOD mStudenC 
   zVIEW    mStudenC = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );


   //:// Combine any Minor Degree Track groups into the Major Degree Track, but only for those minors that
   //:// are for the same College. (ex., we don't want an undergraduate minor for a graduate degree track.)
   //:ACTIVATE mMajorTrack WHERE mMajorTrack.DegreeTrack.ID = mStudent.MajorDegreeTrack.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mStudent, "MajorDegreeTrack", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omDegTrk_fnLocalBuildQual_2( mStudent, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMajorTrack, "mDegTrk", mStudent, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMajorTrack "mDegTrk"
   SetNameForView( mMajorTrack, "mDegTrk", null, zLEVEL_TASK );
   //:FOR EACH mStudent.DegreeMinorCollege WITHIN mStudent.Student  
   //:   WHERE mStudent.DegreeMinorCollege.ID = mStudent.DegreeMajorCollege.ID
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mStudent, "DegreeMajorCollege", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   RESULT = mStudent.cursor( "DegreeMinorCollege" ).setFirst( "ID", lTempInteger_1, "Student" ).toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:ACTIVATE mMinorTrack WHERE mMinorTrack.DegreeTrack.ID = mStudent.MinorDegreeTrack.ID 
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
             GetIntegerFromAttribute( mi_lTempInteger_2, mStudent, "MinorDegreeTrack", "ID" );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
      omDegTrk_fnLocalBuildQual_3( mStudent, vTempViewVar_1, lTempInteger_2 );
      RESULT = ActivateObjectInstance( mMinorTrack, "mDegTrk", mStudent, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:SET CURSOR LAST mMajorTrack.DegreeTrackRequiredGroup 
      RESULT = mMajorTrack.cursor( "DegreeTrackRequiredGroup" ).setLast().toInt();;
      //:FOR EACH mMinorTrack.DegreeTrackRequiredGroup 
      RESULT = mMinorTrack.cursor( "DegreeTrackRequiredGroup" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mMajorTrack.DegreeTrackRequiredGroup 
         RESULT = CreateEntity( mMajorTrack, "DegreeTrackRequiredGroup", zPOS_AFTER );
         //:INCLUDE mMajorTrack.RequiredGroup FROM mMinorTrack.RequiredGroup  
         RESULT = IncludeSubobjectFromSubobject( mMajorTrack, "RequiredGroup", mMinorTrack, "RequiredGroup", zPOS_AFTER );
         RESULT = mMinorTrack.cursor( "DegreeTrackRequiredGroup" ).setNextContinue().toInt();;
      } 

      //:END
      //:INCLUDE mMajorTrack.AuditMinorDegreeTrack FROM mMinorTrack.DegreeTrack 
      RESULT = IncludeSubobjectFromSubobject( mMajorTrack, "AuditMinorDegreeTrack", mMinorTrack, "DegreeTrack", zPOS_AFTER );
      //:DropObjectInstance( mMinorTrack )
      DropObjectInstance( mMinorTrack );
      RESULT = mStudent.cursor( "DegreeMinorCollege" ).setNextContinue().toInt();;
   } 

   //:END

   //:mReturnedTrack = mMajorTrack
   SetViewFromView( mReturnedTrack, mMajorTrack );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dAuditCourseResult( VIEW mDegTrk BASED ON LOD mDegTrk,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   STRING ( 200 ) szResult
public int 
omDegTrk_dAuditCourseResult( View     mDegTrk,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szResult = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:STRING ( 50 )  szAuditResult
   String   szAuditResult = null;
   //:STRING ( 5 )   szFinalGrade
   String   szFinalGrade = null;
   //:STRING ( 10 )  szCreditHours
   String   szCreditHours = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mDegTrk );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "CourseType1or2"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "CourseType1or2", 1, 0, 33 ) == 0 )
         { 
            //:IF mDegTrk.CourseType1or2.wCourseStatus != ""
            if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "wCourseStatus", "" ) != 0 )
            { 
               //:IF mDegTrk.CourseType1or2.wCourseStatus = "No longer offered"
               if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "wCourseStatus", "No longer offered" ) == 0 )
               { 
                  //:szResult = "(No longer offered)"
                   {StringBuilder sb_szResult;
                  if ( szResult == null )
                     sb_szResult = new StringBuilder( 32 );
                  else
                     sb_szResult = new StringBuilder( szResult );
                                    ZeidonStringCopy( sb_szResult, 1, 0, "(No longer offered)", 1, 0, 201 );
                  szResult = sb_szResult.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:GetStringFromAttributeByContext( szAuditResult,
                  //:                              mDegTrk, "CourseType1or2", "wCourseStatus", "AuditDisplay", 50 )
                  {StringBuilder sb_szAuditResult;
                  if ( szAuditResult == null )
                     sb_szAuditResult = new StringBuilder( 32 );
                  else
                     sb_szAuditResult = new StringBuilder( szAuditResult );
                                     GetStringFromAttributeByContext( sb_szAuditResult, mDegTrk, "CourseType1or2", "wCourseStatus", "AuditDisplay", 50 );
                  szAuditResult = sb_szAuditResult.toString( );}
                  //:szCreditHours = mDegTrk.CourseType1or2.wCreditsTaken 
                  {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                  StringBuilder sb_szCreditHours;
                  if ( szCreditHours == null )
                     sb_szCreditHours = new StringBuilder( 32 );
                  else
                     sb_szCreditHours = new StringBuilder( szCreditHours );
                                     GetVariableFromAttribute( sb_szCreditHours, mi_lTempInteger_0, 'S', 11, mDegTrk, "CourseType1or2", "wCreditsTaken", "", 0 );
                  lTempInteger_0 = mi_lTempInteger_0.intValue( );
                  szCreditHours = sb_szCreditHours.toString( );}
                  //:szResult = " (" + szAuditResult + ", Credits: " + szCreditHours
                   {StringBuilder sb_szResult;
                  if ( szResult == null )
                     sb_szResult = new StringBuilder( 32 );
                  else
                     sb_szResult = new StringBuilder( szResult );
                                    ZeidonStringCopy( sb_szResult, 1, 0, " (", 1, 0, 201 );
                  szResult = sb_szResult.toString( );}
                   {StringBuilder sb_szResult;
                  if ( szResult == null )
                     sb_szResult = new StringBuilder( 32 );
                  else
                     sb_szResult = new StringBuilder( szResult );
                                    ZeidonStringConcat( sb_szResult, 1, 0, szAuditResult, 1, 0, 201 );
                  szResult = sb_szResult.toString( );}
                   {StringBuilder sb_szResult;
                  if ( szResult == null )
                     sb_szResult = new StringBuilder( 32 );
                  else
                     sb_szResult = new StringBuilder( szResult );
                                    ZeidonStringConcat( sb_szResult, 1, 0, ", Credits: ", 1, 0, 201 );
                  szResult = sb_szResult.toString( );}
                   {StringBuilder sb_szResult;
                  if ( szResult == null )
                     sb_szResult = new StringBuilder( 32 );
                  else
                     sb_szResult = new StringBuilder( szResult );
                                    ZeidonStringConcat( sb_szResult, 1, 0, szCreditHours, 1, 0, 201 );
                  szResult = sb_szResult.toString( );}
                  //:szFinalGrade = mDegTrk.CourseType1or2.wFinalGrade
                  {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                  StringBuilder sb_szFinalGrade;
                  if ( szFinalGrade == null )
                     sb_szFinalGrade = new StringBuilder( 32 );
                  else
                     sb_szFinalGrade = new StringBuilder( szFinalGrade );
                                     GetVariableFromAttribute( sb_szFinalGrade, mi_lTempInteger_1, 'S', 6, mDegTrk, "CourseType1or2", "wFinalGrade", "", 0 );
                  lTempInteger_1 = mi_lTempInteger_1.intValue( );
                  szFinalGrade = sb_szFinalGrade.toString( );}
                  //:IF szFinalGrade = ""
                  if ( ZeidonStringCompare( szFinalGrade, 1, 0, "", 1, 0, 6 ) == 0 )
                  { 
                     //:szResult = szResult + " )"
                      {StringBuilder sb_szResult;
                     if ( szResult == null )
                        sb_szResult = new StringBuilder( 32 );
                     else
                        sb_szResult = new StringBuilder( szResult );
                                          ZeidonStringConcat( sb_szResult, 1, 0, " )", 1, 0, 201 );
                     szResult = sb_szResult.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:szResult = szResult + ", Grade: " + szFinalGrade + " )"
                      {StringBuilder sb_szResult;
                     if ( szResult == null )
                        sb_szResult = new StringBuilder( 32 );
                     else
                        sb_szResult = new StringBuilder( szResult );
                                          ZeidonStringConcat( sb_szResult, 1, 0, ", Grade: ", 1, 0, 201 );
                     szResult = sb_szResult.toString( );}
                      {StringBuilder sb_szResult;
                     if ( szResult == null )
                        sb_szResult = new StringBuilder( 32 );
                     else
                        sb_szResult = new StringBuilder( szResult );
                                          ZeidonStringConcat( sb_szResult, 1, 0, szFinalGrade, 1, 0, 201 );
                     szResult = sb_szResult.toString( );}
                      {StringBuilder sb_szResult;
                     if ( szResult == null )
                        sb_szResult = new StringBuilder( 32 );
                     else
                        sb_szResult = new StringBuilder( szResult );
                                          ZeidonStringConcat( sb_szResult, 1, 0, " )", 1, 0, 201 );
                     szResult = sb_szResult.toString( );}
                  } 

                  //:END
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:szResult = ""
                {StringBuilder sb_szResult;
               if ( szResult == null )
                  sb_szResult = new StringBuilder( 32 );
               else
                  sb_szResult = new StringBuilder( szResult );
                              ZeidonStringCopy( sb_szResult, 1, 0, "", 1, 0, 201 );
               szResult = sb_szResult.toString( );}
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF mDegTrk.CourseType3or4.wCourseStatus != ""
            if ( CompareAttributeToString( mDegTrk, "CourseType3or4", "wCourseStatus", "" ) != 0 )
            { 
               //:IF mDegTrk.CourseType3or4.wCourseStatus = "No longer offered"
               if ( CompareAttributeToString( mDegTrk, "CourseType3or4", "wCourseStatus", "No longer offered" ) == 0 )
               { 
                  //:szResult = "(No longer offered)"
                   {StringBuilder sb_szResult;
                  if ( szResult == null )
                     sb_szResult = new StringBuilder( 32 );
                  else
                     sb_szResult = new StringBuilder( szResult );
                                    ZeidonStringCopy( sb_szResult, 1, 0, "(No longer offered)", 1, 0, 201 );
                  szResult = sb_szResult.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:GetStringFromAttributeByContext( szAuditResult,
                  //:                              mDegTrk, "CourseType3or4", "wCourseStatus", "AuditDisplay", 50 )
                  {StringBuilder sb_szAuditResult;
                  if ( szAuditResult == null )
                     sb_szAuditResult = new StringBuilder( 32 );
                  else
                     sb_szAuditResult = new StringBuilder( szAuditResult );
                                     GetStringFromAttributeByContext( sb_szAuditResult, mDegTrk, "CourseType3or4", "wCourseStatus", "AuditDisplay", 50 );
                  szAuditResult = sb_szAuditResult.toString( );}
                  //:szCreditHours = mDegTrk.CourseType3or4.wCreditsTaken 
                  {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                  StringBuilder sb_szCreditHours;
                  if ( szCreditHours == null )
                     sb_szCreditHours = new StringBuilder( 32 );
                  else
                     sb_szCreditHours = new StringBuilder( szCreditHours );
                                     GetVariableFromAttribute( sb_szCreditHours, mi_lTempInteger_2, 'S', 11, mDegTrk, "CourseType3or4", "wCreditsTaken", "", 0 );
                  lTempInteger_2 = mi_lTempInteger_2.intValue( );
                  szCreditHours = sb_szCreditHours.toString( );}
                  //:szResult = szResult + " (" + szAuditResult + ", Credits: " + szCreditHours
                   {StringBuilder sb_szResult;
                  if ( szResult == null )
                     sb_szResult = new StringBuilder( 32 );
                  else
                     sb_szResult = new StringBuilder( szResult );
                                    ZeidonStringConcat( sb_szResult, 1, 0, " (", 1, 0, 201 );
                  szResult = sb_szResult.toString( );}
                   {StringBuilder sb_szResult;
                  if ( szResult == null )
                     sb_szResult = new StringBuilder( 32 );
                  else
                     sb_szResult = new StringBuilder( szResult );
                                    ZeidonStringConcat( sb_szResult, 1, 0, szAuditResult, 1, 0, 201 );
                  szResult = sb_szResult.toString( );}
                   {StringBuilder sb_szResult;
                  if ( szResult == null )
                     sb_szResult = new StringBuilder( 32 );
                  else
                     sb_szResult = new StringBuilder( szResult );
                                    ZeidonStringConcat( sb_szResult, 1, 0, ", Credits: ", 1, 0, 201 );
                  szResult = sb_szResult.toString( );}
                   {StringBuilder sb_szResult;
                  if ( szResult == null )
                     sb_szResult = new StringBuilder( 32 );
                  else
                     sb_szResult = new StringBuilder( szResult );
                                    ZeidonStringConcat( sb_szResult, 1, 0, szCreditHours, 1, 0, 201 );
                  szResult = sb_szResult.toString( );}
                  //:szFinalGrade = mDegTrk.CourseType3or4.wFinalGrade 
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szFinalGrade;
                  if ( szFinalGrade == null )
                     sb_szFinalGrade = new StringBuilder( 32 );
                  else
                     sb_szFinalGrade = new StringBuilder( szFinalGrade );
                                     GetVariableFromAttribute( sb_szFinalGrade, mi_lTempInteger_3, 'S', 6, mDegTrk, "CourseType3or4", "wFinalGrade", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szFinalGrade = sb_szFinalGrade.toString( );}
                  //:IF szFinalGrade = ""
                  if ( ZeidonStringCompare( szFinalGrade, 1, 0, "", 1, 0, 6 ) == 0 )
                  { 
                     //:szResult = szResult + " )"
                      {StringBuilder sb_szResult;
                     if ( szResult == null )
                        sb_szResult = new StringBuilder( 32 );
                     else
                        sb_szResult = new StringBuilder( szResult );
                                          ZeidonStringConcat( sb_szResult, 1, 0, " )", 1, 0, 201 );
                     szResult = sb_szResult.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:szResult = szResult + ", Grade: " + szFinalGrade + " )"
                      {StringBuilder sb_szResult;
                     if ( szResult == null )
                        sb_szResult = new StringBuilder( 32 );
                     else
                        sb_szResult = new StringBuilder( szResult );
                                          ZeidonStringConcat( sb_szResult, 1, 0, ", Grade: ", 1, 0, 201 );
                     szResult = sb_szResult.toString( );}
                      {StringBuilder sb_szResult;
                     if ( szResult == null )
                        sb_szResult = new StringBuilder( 32 );
                     else
                        sb_szResult = new StringBuilder( szResult );
                                          ZeidonStringConcat( sb_szResult, 1, 0, szFinalGrade, 1, 0, 201 );
                     szResult = sb_szResult.toString( );}
                      {StringBuilder sb_szResult;
                     if ( szResult == null )
                        sb_szResult = new StringBuilder( 32 );
                     else
                        sb_szResult = new StringBuilder( szResult );
                                          ZeidonStringConcat( sb_szResult, 1, 0, " )", 1, 0, 201 );
                     szResult = sb_szResult.toString( );}
                  } 

                  //:END
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:szResult = ""
                {StringBuilder sb_szResult;
               if ( szResult == null )
                  sb_szResult = new StringBuilder( 32 );
               else
                  sb_szResult = new StringBuilder( szResult );
                              ZeidonStringCopy( sb_szResult, 1, 0, "", 1, 0, 201 );
               szResult = sb_szResult.toString( );}
            } 

            //:END
         } 

         //:END

         //:StoreStringInRecord ( mDegTrk,
         //:                   InternalEntityStructure, InternalAttribStructure, szResult )
         StoreStringInRecord( mDegTrk, InternalEntityStructure, InternalAttribStructure, szResult );
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
//:dAuditGroupOL_Value( VIEW mDegTrk BASED ON LOD mDegTrk,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   STRING ( 200 ) szOL_Value
public int 
omDegTrk_dAuditGroupOL_Value( View     mDegTrk,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szOL_Value = null;
   //:STRING ( 10 )  szCredits
   String   szCredits = null;
   //:STRING ( 10 )  szMinGrade
   String   szMinGrade = null;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mDegTrk.RequiredGroup.Type = ""
         if ( CompareAttributeToString( mDegTrk, "RequiredGroup", "Type", "" ) == 0 )
         { 
            //:szOL_Value = "Type " + mDegTrk.RequiredGroup.Name
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 51, mDegTrk, "RequiredGroup", "Name", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringCopy( sb_szOL_Value, 1, 0, "Type ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szTempString_0, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szOL_Value = "Type " + mDegTrk.RequiredGroup.Type  +
            //:          ", " + mDegTrk.RequiredGroup.wDegreeAuditOL_Value
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 2, mDegTrk, "RequiredGroup", "Type", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringCopy( sb_szOL_Value, 1, 0, "Type ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szTempString_1, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, ", ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_2, 'S', 255, mDegTrk, "RequiredGroup", "wDegreeAuditOL_Value", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szTempString_2, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mDegTrk,
         //:                   InternalEntityStructure, InternalAttribStructure, szOL_Value )
         StoreStringInRecord( mDegTrk, InternalEntityStructure, InternalAttribStructure, szOL_Value );
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
//:dGraduationCredits( VIEW mDegTrk BASED ON LOD mDegTrk,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   VIEW mStudenC REGISTERED AS mStudenC
public int 
omDegTrk_dGraduationCredits( View     mDegTrk,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   zVIEW    mStudenC = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL GraduationCredits
   double  GraduationCredits = 0.0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;
   double  dTempDecimal_4 = 0.0;
   double  dTempDecimal_5 = 0.0;
   double  dTempDecimal_6 = 0.0;
   double  dTempDecimal_7 = 0.0;

   RESULT = GetViewByName( mStudenC, "mStudenC", mDegTrk, zLEVEL_TASK );

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :

         //:// Subtract Developmental Credits from wDegreeAuditTotalCreditsTaken.
         //:IF mDegTrk.DegreeMajorCollege.Type = "G"
         if ( CompareAttributeToString( mDegTrk, "DegreeMajorCollege", "Type", "G" ) == 0 )
         { 
            //:GraduationCredits = mDegTrk.DegreeTrack.AuditTotalCreditsEarned +
            //:                 mDegTrk.DegreeTrack.AuditEnrolledCredits -
            //:                 mDegTrk.DegreeTrack.dDevelopmentalCredits -
            //:                 mStudenC.Student.dEnrolledCreditsRepeatedGrad
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mDegTrk, "DegreeTrack", "AuditTotalCreditsEarned" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                         GetDecimalFromAttribute( md_dTempDecimal_1, mDegTrk, "DegreeTrack", "AuditEnrolledCredits" );
            dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
            {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                         GetDecimalFromAttribute( md_dTempDecimal_2, mDegTrk, "DegreeTrack", "dDevelopmentalCredits" );
            dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
            {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                         GetDecimalFromAttribute( md_dTempDecimal_3, mStudenC, "Student", "dEnrolledCreditsRepeatedGrad" );
            dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
            GraduationCredits = dTempDecimal_0 + dTempDecimal_1 - dTempDecimal_2 - dTempDecimal_3;
            //:ELSE
         } 
         else
         { 
            //:GraduationCredits = mDegTrk.DegreeTrack.AuditTotalCreditsEarned +
            //:                 mDegTrk.DegreeTrack.AuditEnrolledCredits -
            //:                 mDegTrk.DegreeTrack.dDevelopmentalCredits -
            //:                 mStudenC.Student.dEnrolledCreditsRepeated
            {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                         GetDecimalFromAttribute( md_dTempDecimal_4, mDegTrk, "DegreeTrack", "AuditTotalCreditsEarned" );
            dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
            {MutableDouble md_dTempDecimal_5 = new MutableDouble( dTempDecimal_5 );
                         GetDecimalFromAttribute( md_dTempDecimal_5, mDegTrk, "DegreeTrack", "AuditEnrolledCredits" );
            dTempDecimal_5 = md_dTempDecimal_5.doubleValue( );}
            {MutableDouble md_dTempDecimal_6 = new MutableDouble( dTempDecimal_6 );
                         GetDecimalFromAttribute( md_dTempDecimal_6, mDegTrk, "DegreeTrack", "dDevelopmentalCredits" );
            dTempDecimal_6 = md_dTempDecimal_6.doubleValue( );}
            {MutableDouble md_dTempDecimal_7 = new MutableDouble( dTempDecimal_7 );
                         GetDecimalFromAttribute( md_dTempDecimal_7, mStudenC, "Student", "dEnrolledCreditsRepeated" );
            dTempDecimal_7 = md_dTempDecimal_7.doubleValue( );}
            GraduationCredits = dTempDecimal_4 + dTempDecimal_5 - dTempDecimal_6 - dTempDecimal_7;
         } 

         //:END

         //:StoreValueInRecord ( mDegTrk,
         //:                  InternalEntityStructure, InternalAttribStructure, GraduationCredits, 0 )
         StoreValueInRecord( mDegTrk, InternalEntityStructure, InternalAttribStructure, GraduationCredits, 0 );
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
//:dDevelopmentalCredits( VIEW mDegTrkO BASED ON LOD mDegTrk,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   VIEW mDegTrk BASED ON LOD mDegTrk
public int 
omDegTrk_dDevelopmentalCredits( View     mDegTrkO,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   zVIEW    mDegTrk = new zVIEW( );
   //:DECIMAL TotalCredits
   double  TotalCredits = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :

         //:// Total Developmental Credits.
         //:CreateViewFromView( mDegTrk, mDegTrkO )
         CreateViewFromView( mDegTrk, mDegTrkO );
         //:TotalCredits = 0 
         TotalCredits = 0;
         //:FOR EACH mDegTrk.CourseType1or2 WITHIN mDegTrk.DegreeTrack 
         RESULT = mDegTrk.cursor( "CourseType1or2" ).setFirst( "DegreeTrack" ).toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mDegTrk.CourseType1or2.wDevelopmentalFlag = "Y"
            if ( CompareAttributeToString( mDegTrk, "CourseType1or2", "wDevelopmentalFlag", "Y" ) == 0 )
            { 
               //:TotalCredits = TotalCredits + mDegTrk.CourseType1or2.wCreditsTaken
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mDegTrk, "CourseType1or2", "wCreditsTaken" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               TotalCredits = TotalCredits + dTempDecimal_0;
            } 

            RESULT = mDegTrk.cursor( "CourseType1or2" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:DropView( mDegTrk )
         DropView( mDegTrk );

         //:StoreValueInRecord ( mDegTrkO,
         //:                  InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 )
         StoreValueInRecord( mDegTrkO, InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 );
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


//:LOCAL OPERATION
//:ReturnCourseVariables( VIEW mStudenC  BASED ON LOD mStudenC,
//:                       INTEGER        ReturnedCourseID,
//:                       STRING ( 20 )  ReturnedCourseNumber,
//:                       STRING ( 100 ) ReturnedCourseTitle,
//:                       STRING ( 20 )  ReturnedSemester,
//:                       STRING ( 8 )   ReturnedClassEndDate )
//:   
//:   STRING ( 10 ) Semester
private int 
omDegTrk_ReturnCourseVariables( View     mStudenC,
                                MutableInt   ReturnedCourseID,
                                StringBuilder   ReturnedCourseNumber,
                                StringBuilder   ReturnedCourseTitle,
                                StringBuilder   ReturnedSemester,
                                StringBuilder   ReturnedClassEndDate )
{
   String   Semester = null;
   //:STRING ( 4 )  szYear
   String   szYear = null;
   //:STRING ( 4 )  szNewYear
   String   szNewYear = null;
   //:STRING ( 8 )  szSemesterDate
   String   szSemesterDate = null;
   //:INTEGER       NewYear
   int      NewYear = 0;
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


   //:// Set up the formatting variables for Course/Class information.
   //:// This is where the atoms of a line such as "Enrolled Fall 06" are built.

   //:IF mStudenC.RegistrationCourse EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "RegistrationCourse" );
   if ( lTempInteger_0 == 0 )
   { 
      //:   
      //:IF mStudenC.CrossListedCourse EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( mStudenC, "CrossListedCourse" );
      if ( lTempInteger_1 == 0 )
      { 
         //:ReturnedCourseNumber = mStudenC.CrossListedCourse.Number 
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                   GetVariableFromAttribute( ReturnedCourseNumber, mi_lTempInteger_2, 'S', 21, mStudenC, "CrossListedCourse", "Number", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );}
         //:ReturnedCourseTitle  = mStudenC.CrossListedCourse.Title
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                   GetVariableFromAttribute( ReturnedCourseTitle, mi_lTempInteger_3, 'S', 101, mStudenC, "CrossListedCourse", "Title", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );}
         //:ReturnedCourseID = mStudenC.CrossListedCourse.ID 
         GetIntegerFromAttribute( ReturnedCourseID, mStudenC, "CrossListedCourse", "ID" );
         //:ELSE
      } 
      else
      { 
         //:ReturnedCourseNumber = mStudenC.RegistrationCourse.Number 
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                   GetVariableFromAttribute( ReturnedCourseNumber, mi_lTempInteger_4, 'S', 21, mStudenC, "RegistrationCourse", "Number", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );}
         //:ReturnedCourseTitle  = mStudenC.RegistrationCourse.Title
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                   GetVariableFromAttribute( ReturnedCourseTitle, mi_lTempInteger_5, 'S', 101, mStudenC, "RegistrationCourse", "Title", "", 0 );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );}
         //:ReturnedCourseID = mStudenC.RegistrationCourse.ID
         GetIntegerFromAttribute( ReturnedCourseID, mStudenC, "RegistrationCourse", "ID" );
      } 

      //:END
      //:IF mStudenC.ClassCohort EXISTS
      lTempInteger_6 = CheckExistenceOfEntity( mStudenC, "ClassCohort" );
      if ( lTempInteger_6 == 0 )
      { 
         //:GetStringFromAttributeByContext( szSemesterDate, mStudenC, "RegistrationClass", "ClassStartDate", "", 8 )
         {StringBuilder sb_szSemesterDate;
         if ( szSemesterDate == null )
            sb_szSemesterDate = new StringBuilder( 32 );
         else
            sb_szSemesterDate = new StringBuilder( szSemesterDate );
                   GetStringFromAttributeByContext( sb_szSemesterDate, mStudenC, "RegistrationClass", "ClassStartDate", "", 8 );
         szSemesterDate = sb_szSemesterDate.toString( );}
         //:ReturnedSemester = szSemesterDate
         ZeidonStringCopy( ReturnedSemester, 1, 0, szSemesterDate, 1, 0, 21 );
         //:ReturnedClassEndDate = mStudenC.RegistrationClass.ClassStartDate 
         {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                   GetVariableFromAttribute( ReturnedClassEndDate, mi_lTempInteger_7, 'S', 9, mStudenC, "RegistrationClass", "ClassStartDate", "", 0 );
         lTempInteger_7 = mi_lTempInteger_7.intValue( );}
         //:ELSE
      } 
      else
      { 
         //:GetStringFromAttributeByContext( Semester, mStudenC, "RegistrationClassCollegeTerm", "Semester", "", 10 )
         {StringBuilder sb_Semester;
         if ( Semester == null )
            sb_Semester = new StringBuilder( 32 );
         else
            sb_Semester = new StringBuilder( Semester );
                   GetStringFromAttributeByContext( sb_Semester, mStudenC, "RegistrationClassCollegeTerm", "Semester", "", 10 );
         Semester = sb_Semester.toString( );}
         //:GetStringFromAttributeByContext( szYear, mStudenC, "RegistrationClassCollegeYear", "EndDate", "YYYY", 4 )
         {StringBuilder sb_szYear;
         if ( szYear == null )
            sb_szYear = new StringBuilder( 32 );
         else
            sb_szYear = new StringBuilder( szYear );
                   GetStringFromAttributeByContext( sb_szYear, mStudenC, "RegistrationClassCollegeYear", "EndDate", "YYYY", 4 );
         szYear = sb_szYear.toString( );}
         //:IF Semester = "Fall"
         if ( ZeidonStringCompare( Semester, 1, 0, "Fall", 1, 0, 11 ) == 0 )
         { 
            //:// Change Year to previous Year, since College Year ends inmStudenC.RegistrationClass.ClassStartDate  the spring.
            //:NewYear = zStringToInteger( szYear )
            NewYear = zStringToInteger( szYear );
            //:NewYear = NewYear - 1
            NewYear = NewYear - 1;
            //:szYear = NewYear
             {StringBuilder sb_szYear;
            if ( szYear == null )
               sb_szYear = new StringBuilder( 32 );
            else
               sb_szYear = new StringBuilder( szYear );
                        ZeidonStringConvertFromNumber( sb_szYear, 1, 0, 4, NewYear, (double) 0.0, "I" );
            szYear = sb_szYear.toString( );}
         } 

         //:END
         //:ReturnedSemester = Semester + " " + szYear[3:2]
         ZeidonStringCopy( ReturnedSemester, 1, 0, Semester, 1, 0, 21 );
         ZeidonStringConcat( ReturnedSemester, 1, 0, " ", 1, 0, 21 );
         ZeidonStringConcat( ReturnedSemester, 1, 0, szYear, 3, 2, 21 );
         //:ReturnedClassEndDate = mStudenC.RegistrationClassCollegeTerm.CourseEndDate 
         {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                   GetVariableFromAttribute( ReturnedClassEndDate, mi_lTempInteger_8, 'S', 9, mStudenC, "RegistrationClassCollegeTerm", "CourseEndDate", "", 0 );
         lTempInteger_8 = mi_lTempInteger_8.intValue( );}
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:IF mStudenC.EquivalentCourse EXISTS
      lTempInteger_9 = CheckExistenceOfEntity( mStudenC, "EquivalentCourse" );
      if ( lTempInteger_9 == 0 )
      { 
         //:ReturnedCourseNumber = mStudenC.EquivalentCourse.Number 
         {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
                   GetVariableFromAttribute( ReturnedCourseNumber, mi_lTempInteger_10, 'S', 21, mStudenC, "EquivalentCourse", "Number", "", 0 );
         lTempInteger_10 = mi_lTempInteger_10.intValue( );}
         //:ReturnedCourseTitle  = mStudenC.Registration.ForeignClassName
         {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
                   GetVariableFromAttribute( ReturnedCourseTitle, mi_lTempInteger_11, 'S', 101, mStudenC, "Registration", "ForeignClassName", "", 0 );
         lTempInteger_11 = mi_lTempInteger_11.intValue( );}
         //:ReturnedCourseID     = mStudenC.EquivalentCourse.ID 
         GetIntegerFromAttribute( ReturnedCourseID, mStudenC, "EquivalentCourse", "ID" );
         //:IF mStudenC.TransferRecordTerm EXISTS
         lTempInteger_12 = CheckExistenceOfEntity( mStudenC, "TransferRecordTerm" );
         if ( lTempInteger_12 == 0 )
         { 
            //:GetStringFromAttributeByContext( Semester, mStudenC, "TransferRecordTerm", "Semester", "", 10 )
            {StringBuilder sb_Semester;
            if ( Semester == null )
               sb_Semester = new StringBuilder( 32 );
            else
               sb_Semester = new StringBuilder( Semester );
                         GetStringFromAttributeByContext( sb_Semester, mStudenC, "TransferRecordTerm", "Semester", "", 10 );
            Semester = sb_Semester.toString( );}
            //:GetStringFromAttributeByContext( szYear, mStudenC, "TransferRecordYear", "EndDate", "YYYY", 4 )
            {StringBuilder sb_szYear;
            if ( szYear == null )
               sb_szYear = new StringBuilder( 32 );
            else
               sb_szYear = new StringBuilder( szYear );
                         GetStringFromAttributeByContext( sb_szYear, mStudenC, "TransferRecordYear", "EndDate", "YYYY", 4 );
            szYear = sb_szYear.toString( );}
            //:IF Semester = "Fall"
            if ( ZeidonStringCompare( Semester, 1, 0, "Fall", 1, 0, 11 ) == 0 )
            { 
               //:// Change Year to previous Year, since College Year ends in the spring.
               //:NewYear = zStringToInteger( szYear )
               NewYear = zStringToInteger( szYear );
               //:NewYear = NewYear - 1
               NewYear = NewYear - 1;
               //:szYear = NewYear
                {StringBuilder sb_szYear;
               if ( szYear == null )
                  sb_szYear = new StringBuilder( 32 );
               else
                  sb_szYear = new StringBuilder( szYear );
                              ZeidonStringConvertFromNumber( sb_szYear, 1, 0, 4, NewYear, (double) 0.0, "I" );
               szYear = sb_szYear.toString( );}
            } 

            //:END
            //:ReturnedSemester = Semester + " " + szYear[3:2]
            ZeidonStringCopy( ReturnedSemester, 1, 0, Semester, 1, 0, 21 );
            ZeidonStringConcat( ReturnedSemester, 1, 0, " ", 1, 0, 21 );
            ZeidonStringConcat( ReturnedSemester, 1, 0, szYear, 3, 2, 21 );
            //:ReturnedClassEndDate = mStudenC.TransferRecordTerm.CourseEndDate 
            {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
                         GetVariableFromAttribute( ReturnedClassEndDate, mi_lTempInteger_13, 'S', 9, mStudenC, "TransferRecordTerm", "CourseEndDate", "", 0 );
            lTempInteger_13 = mi_lTempInteger_13.intValue( );}
            //:ELSE
         } 
         else
         { 
            //:ReturnedSemester     = ""
            ZeidonStringCopy( ReturnedSemester, 1, 0, "", 1, 0, 21 );
            //:ReturnedClassEndDate = ""
            ZeidonStringCopy( ReturnedClassEndDate, 1, 0, "", 1, 0, 9 );
         } 

         //:END
         //:ELSE 
      } 
      else
      { 
         //:ReturnedCourseNumber = ""
         ZeidonStringCopy( ReturnedCourseNumber, 1, 0, "", 1, 0, 21 );
         //:ReturnedCourseTitle  = mStudenC.Registration.ForeignClassName 
         {MutableInt mi_lTempInteger_14 = new MutableInt( lTempInteger_14 );
                   GetVariableFromAttribute( ReturnedCourseTitle, mi_lTempInteger_14, 'S', 101, mStudenC, "Registration", "ForeignClassName", "", 0 );
         lTempInteger_14 = mi_lTempInteger_14.intValue( );}
         //:ReturnedCourseID     = 0
         ReturnedCourseID.setValue( 0 );
         //:ReturnedSemester     = ""
         ZeidonStringCopy( ReturnedSemester, 1, 0, "", 1, 0, 21 );
         //:ReturnedClassEndDate = ""
         ZeidonStringCopy( ReturnedClassEndDate, 1, 0, "", 1, 0, 9 );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 



}
