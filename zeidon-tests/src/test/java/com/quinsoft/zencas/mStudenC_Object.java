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
import com.quinsoft.zencas.ZGLOBAL2_Operation;
import com.quinsoft.zencas.lTrnscpt_Object;

import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;

/**
   @author QuinSoft
**/

public class mStudenC_Object extends VmlObjectOperations
{
   private final ZDRVROPR m_ZDRVROPR;
   public mStudenC_Object( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dCourseOL_Value( VIEW mStudenC BASED ON LOD mStudenC,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )

//:   STRING ( 200 ) szOL_Value
public int 
omStudenC_dCourseOL_Value( View     mStudenC,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   String   szOL_Value = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:STRING ( 5 )   szGrade
   String   szGrade = null;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mStudenC );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "HS_RequiredGroupCourse"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "HS_RequiredGroupCourse", 1, 0, 33 ) == 0 )
         { 
            //:szOL_Value = mStudenC.HS_RequiredGroupCourse.Number + " - " +
            //:          mStudenC.HS_RequiredGroupCourse.Title
            {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                         GetStringFromAttribute( sb_szOL_Value, mStudenC, "HS_RequiredGroupCourse", "Number" );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, " - ", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 101, mStudenC, "HS_RequiredGroupCourse", "Title", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
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
            //:IF szEntityName = "OrigHS_RequiredGroupCourse"
            if ( ZeidonStringCompare( szEntityName, 1, 0, "OrigHS_RequiredGroupCourse", 1, 0, 33 ) == 0 )
            { 
               //:szOL_Value = mStudenC.OrigHS_RequiredGroupCourse.Number + " - " +
               //:          mStudenC.OrigHS_RequiredGroupCourse.Title
               {StringBuilder sb_szOL_Value;
               if ( szOL_Value == null )
                  sb_szOL_Value = new StringBuilder( 32 );
               else
                  sb_szOL_Value = new StringBuilder( szOL_Value );
                               GetStringFromAttribute( sb_szOL_Value, mStudenC, "OrigHS_RequiredGroupCourse", "Number" );
               szOL_Value = sb_szOL_Value.toString( );}
                {StringBuilder sb_szOL_Value;
               if ( szOL_Value == null )
                  sb_szOL_Value = new StringBuilder( 32 );
               else
                  sb_szOL_Value = new StringBuilder( szOL_Value );
                              ZeidonStringConcat( sb_szOL_Value, 1, 0, " - ", 1, 0, 201 );
               szOL_Value = sb_szOL_Value.toString( );}
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 101, mStudenC, "OrigHS_RequiredGroupCourse", "Title", "", 0 );
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
               //:szOL_Value = "EQUIVALENCY: " +
               //:          mStudenC.StudentEquivalencyCourse.Number  + " - " +
               //:          mStudenC.StudentEquivalencyCourse.Title
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szTempString_2;
               if ( szTempString_2 == null )
                  sb_szTempString_2 = new StringBuilder( 32 );
               else
                  sb_szTempString_2 = new StringBuilder( szTempString_2 );
                               GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_2, 'S', 11, mStudenC, "StudentEquivalencyCourse", "Number", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szTempString_2 = sb_szTempString_2.toString( );}
                {StringBuilder sb_szOL_Value;
               if ( szOL_Value == null )
                  sb_szOL_Value = new StringBuilder( 32 );
               else
                  sb_szOL_Value = new StringBuilder( szOL_Value );
                              ZeidonStringCopy( sb_szOL_Value, 1, 0, "EQUIVALENCY: ", 1, 0, 201 );
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
                              ZeidonStringConcat( sb_szOL_Value, 1, 0, " - ", 1, 0, 201 );
               szOL_Value = sb_szOL_Value.toString( );}
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szTempString_3;
               if ( szTempString_3 == null )
                  sb_szTempString_3 = new StringBuilder( 32 );
               else
                  sb_szTempString_3 = new StringBuilder( szTempString_3 );
                               GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_3, 'S', 101, mStudenC, "StudentEquivalencyCourse", "Title", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szTempString_3 = sb_szTempString_3.toString( );}
                {StringBuilder sb_szOL_Value;
               if ( szOL_Value == null )
                  sb_szOL_Value = new StringBuilder( 32 );
               else
                  sb_szOL_Value = new StringBuilder( szOL_Value );
                              ZeidonStringConcat( sb_szOL_Value, 1, 0, szTempString_3, 1, 0, 201 );
               szOL_Value = sb_szOL_Value.toString( );}
               //:GetStringFromAttributeByContext( szGrade,
               //:                              mStudenC, "StudentEquivalency", "ClassGrade", "", 5 )
               {StringBuilder sb_szGrade;
               if ( szGrade == null )
                  sb_szGrade = new StringBuilder( 32 );
               else
                  sb_szGrade = new StringBuilder( szGrade );
                               GetStringFromAttributeByContext( sb_szGrade, mStudenC, "StudentEquivalency", "ClassGrade", "", 5 );
               szGrade = sb_szGrade.toString( );}
               //:IF szGrade != ""
               if ( ZeidonStringCompare( szGrade, 1, 0, "", 1, 0, 6 ) != 0 )
               { 
                  //:szOL_Value = szOL_Value + " (" + szGrade + ")"
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
                                    ZeidonStringConcat( sb_szOL_Value, 1, 0, szGrade, 1, 0, 201 );
                  szOL_Value = sb_szOL_Value.toString( );}
                   {StringBuilder sb_szOL_Value;
                  if ( szOL_Value == null )
                     sb_szOL_Value = new StringBuilder( 32 );
                  else
                     sb_szOL_Value = new StringBuilder( szOL_Value );
                                    ZeidonStringConcat( sb_szOL_Value, 1, 0, ")", 1, 0, 201 );
                  szOL_Value = sb_szOL_Value.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:StoreStringInRecord ( mStudenC,
         //:                   InternalEntityStructure, InternalAttribStructure, szOL_Value )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szOL_Value );
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
omStudenC_fnLocalBuildQual_1( View     vSubtask,
                              zVIEW    vQualObject,
                              String   StudentTestingStatus )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "RequiredGroup" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "RequiredGroup" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "StudentTestingStatus" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", StudentTestingStatus.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omStudenC_fnLocalBuildQual_2( View     vSubtask,
                              zVIEW    vQualObject,
                              String   StudentTestingStatus )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "RequiredGroup" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "RequiredGroup" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "StudentTestingStatus" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", StudentTestingStatus.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omStudenC_fnLocalBuildQual_3( View     vSubtask,
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
omStudenC_fnLocalBuildQual_4( View     vSubtask,
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
omStudenC_fnLocalBuildQual_5( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omStudenC_fnLocalBuildQual_6( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0,
                              int      lTempInteger_1 )
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
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omStudenC_fnLocalBuildQual_7( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Class" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Class" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Transaction" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Transaction" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omStudenC_fnLocalBuildQual_8( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_5 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Class" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Class" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_5 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Transaction" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Transaction" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omStudenC_fnLocalBuildQual_9( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_6 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Class" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Class" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_6 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Transaction" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Transaction" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omStudenC_fnLocalBuildQual_0( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Class" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Class" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Transaction" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Transaction" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DERIVED ATTRIBUTE OPERATION
//:dMidtermGradeGPA( VIEW mStudenC BASED ON LOD mStudenC,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   VIEW wXferO REGISTERED AS wXferO
public int 
omStudenC_dMidtermGradeGPA( View     mStudenC,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 10 ) szMidtermGradePoints
   String   szMidtermGradePoints = null;
   //:STRING ( 10 ) szMidtermGrade
   String   szMidtermGrade = null;
   //:STRING ( 10 ) szMidtermGradeGPA
   String   szMidtermGradeGPA = null;
   //:DECIMAL TotalDivisor
   double  TotalDivisor = 0.0;
   //:DECIMAL ClassCredits
   double  ClassCredits = 0.0;
   //:DECIMAL TotalPoints
   double  TotalPoints = 0.0;
   //:DECIMAL MidtermGradePoints
   double  MidtermGradePoints = 0.0;
   //:DECIMAL MidtermGPA
   double  MidtermGPA = 0.0;
   //:INTEGER CollegeTermID
   int      CollegeTermID = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mStudenC, zLEVEL_TASK );

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Compute Midterm GPA for the College Term CurrentScheduleEntry.
         //:szMidtermGradeGPA = ""
          {StringBuilder sb_szMidtermGradeGPA;
         if ( szMidtermGradeGPA == null )
            sb_szMidtermGradeGPA = new StringBuilder( 32 );
         else
            sb_szMidtermGradeGPA = new StringBuilder( szMidtermGradeGPA );
                  ZeidonStringCopy( sb_szMidtermGradeGPA, 1, 0, "", 1, 0, 11 );
         szMidtermGradeGPA = sb_szMidtermGradeGPA.toString( );}
         //:IF mStudenC.CurrentClass EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "CurrentClass" );
         if ( lTempInteger_0 == 0 )
         { 
            //:IF mStudenC.CurrentClass.wStatus = ""
            if ( CompareAttributeToString( mStudenC, "CurrentClass", "wStatus", "" ) == 0 )
            { 
               //:// This is entry for formatting total credits and thus Midterm GPA.
               //:CollegeTermID = mStudenC.CurrentScheduleEntry.wCollegeTermID 
               {MutableInt mi_CollegeTermID = new MutableInt( CollegeTermID );
                               GetIntegerFromAttribute( mi_CollegeTermID, mStudenC, "CurrentScheduleEntry", "wCollegeTermID" );
               CollegeTermID = mi_CollegeTermID.intValue( );}
               //:FOR EACH mStudenC.RegistrationClassCollegeTerm WITHIN mStudenC.Student 
               //:WHERE mStudenC.RegistrationClassCollegeTerm.ID = CollegeTermID
               RESULT = SetCursorFirstEntityByInteger( mStudenC, "RegistrationClassCollegeTerm", "ID", CollegeTermID, "Student" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 

                  //:szMidtermGrade = mStudenC.Registration.MidtermGrade 
                  {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                  StringBuilder sb_szMidtermGrade;
                  if ( szMidtermGrade == null )
                     sb_szMidtermGrade = new StringBuilder( 32 );
                  else
                     sb_szMidtermGrade = new StringBuilder( szMidtermGrade );
                                     GetVariableFromAttribute( sb_szMidtermGrade, mi_lTempInteger_1, 'S', 11, mStudenC, "Registration", "MidtermGrade", "", 0 );
                  lTempInteger_1 = mi_lTempInteger_1.intValue( );
                  szMidtermGrade = sb_szMidtermGrade.toString( );}
                  //:GetStringFromAttributeByContext( szMidtermGradePoints,
                  //:                              mStudenC, "Registration", "MidtermGrade", "DegreeTrackGradePointValue", 20 )
                  {StringBuilder sb_szMidtermGradePoints;
                  if ( szMidtermGradePoints == null )
                     sb_szMidtermGradePoints = new StringBuilder( 32 );
                  else
                     sb_szMidtermGradePoints = new StringBuilder( szMidtermGradePoints );
                                     GetStringFromAttributeByContext( sb_szMidtermGradePoints, mStudenC, "Registration", "MidtermGrade", "DegreeTrackGradePointValue", 20 );
                  szMidtermGradePoints = sb_szMidtermGradePoints.toString( );}
                  //:MidtermGradePoints = StrToDecimal( szMidtermGradePoints )
                  {
                   ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mStudenC );
                   MidtermGradePoints = m_ZGLOBAL1_Operation.StrToDecimal( szMidtermGradePoints );
                   // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                  }
                  //:// We'll consider the entry if the Midterm Grade was an F or it was a grade that was worth points. F is the
                  //:// only zero points grade we will consider.
                  //:IF szMidtermGrade = "F" OR MidtermGradePoints > 0
                  if ( ZeidonStringCompare( szMidtermGrade, 1, 0, "F", 1, 0, 11 ) == 0 || MidtermGradePoints > 0 )
                  { 
                     //:ClassCredits = mStudenC.Registration.CreditHours
                     {MutableDouble md_ClassCredits = new MutableDouble( ClassCredits );
                                           GetDecimalFromAttribute( md_ClassCredits, mStudenC, "Registration", "CreditHours" );
                     ClassCredits = md_ClassCredits.doubleValue( );}
                     //:TotalDivisor = TotalDivisor + ClassCredits
                     TotalDivisor = TotalDivisor + ClassCredits;
                     //:TotalPoints  = TotalPoints  + (MidtermGradePoints * ClassCredits)
                     TotalPoints = TotalPoints + ( MidtermGradePoints * ClassCredits );
                  } 

                  RESULT = SetCursorNextEntityByInteger( mStudenC, "RegistrationClassCollegeTerm", "ID", CollegeTermID, "Student" );
                  //:END
               } 

               //:END
               //:MidtermGPA = TotalPoints / TotalDivisor
               MidtermGPA = TotalPoints / TotalDivisor;
               //:mStudenC.CurrentScheduleEntry.wMidtermGPA = MidtermGPA
               SetAttributeFromDecimal( mStudenC, "CurrentScheduleEntry", "wMidtermGPA", MidtermGPA );
               //:szMidtermGradeGPA = mStudenC.CurrentScheduleEntry.wMidtermGPA 
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szMidtermGradeGPA;
               if ( szMidtermGradeGPA == null )
                  sb_szMidtermGradeGPA = new StringBuilder( 32 );
               else
                  sb_szMidtermGradeGPA = new StringBuilder( szMidtermGradeGPA );
                               GetVariableFromAttribute( sb_szMidtermGradeGPA, mi_lTempInteger_2, 'S', 11, mStudenC, "CurrentScheduleEntry", "wMidtermGPA", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szMidtermGradeGPA = sb_szMidtermGradeGPA.toString( );}

               //:StoreValueInRecord ( mStudenC,
               //:                  InternalEntityStructure, InternalAttribStructure, szMidtermGradeGPA, 0 )
               StoreValueInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szMidtermGradeGPA, 0 );
               //:ELSE
            } 
            else
            { 
               //:IF mStudenC.CurrentClass.wMidtermGrade != ""
               if ( CompareAttributeToString( mStudenC, "CurrentClass", "wMidtermGrade", "" ) != 0 )
               { 
                  //:// This is Registration entry and needs Midterm Grade formatted.
                  //:szMidtermGradeGPA = mStudenC.CurrentClass.wMidtermGrade 
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szMidtermGradeGPA;
                  if ( szMidtermGradeGPA == null )
                     sb_szMidtermGradeGPA = new StringBuilder( 32 );
                  else
                     sb_szMidtermGradeGPA = new StringBuilder( szMidtermGradeGPA );
                                     GetVariableFromAttribute( sb_szMidtermGradeGPA, mi_lTempInteger_3, 'S', 11, mStudenC, "CurrentClass", "wMidtermGrade", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szMidtermGradeGPA = sb_szMidtermGradeGPA.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:StoreStringInRecord ( mStudenC,
         //:                   InternalEntityStructure, InternalAttribStructure, szMidtermGradeGPA )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szMidtermGradeGPA );
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
//:BuildStuClassHistory( VIEW mStudenC BASED ON LOD mStudenC, 
//:                      VIEW lTrnscpt BASED ON LOD lTrnscpt,
//:                      STRING ( 1 ) szCollegeType )

//:   VIEW wXferO    REGISTERED AS wXferO
public int 
omStudenC_BuildStuClassHistory( View     mStudenC,
                                View     lTrnscpt,
                                String   szCollegeType )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mStudCTmp BASED ON LOD  mStudenC 
   zVIEW    mStudCTmp = new zVIEW( );
   //:DECIMAL TotalTermCredits
   double  TotalTermCredits = 0.0;
   //:DECIMAL TotalYearCredits
   double  TotalYearCredits = 0.0;
   //:DECIMAL TotalCredits
   double  TotalCredits = 0.0;
   //:STRING ( 20 )  szDecimalString
   String   szDecimalString = null;
   //:STRING ( 254 ) szMeetingSchedule
   String   szMeetingSchedule = null;
   //:STRING ( 254 ) szLastTermYear
   String   szLastTermYear = null;
   //:STRING ( 254 ) szTermYear
   String   szTermYear = null;
   //:STRING ( 254 ) szLastYear
   String   szLastYear = null;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   //:STRING ( 20 )  szExternalStatus
   String   szExternalStatus = null;
   //:STRING ( 20 )  szRanking
   String   szRanking = null;
   //:STRING ( 20 )  szSemester
   String   szSemester = null;
   //:STRING ( 1 )   FoundFlag
   String   FoundFlag = null;
   //:STRING ( 1 )   TotalEntryFlag
   String   TotalEntryFlag = null;
   //:STRING ( 1 )   TermCreatedFlag
   String   TermCreatedFlag = null;
   //:STRING ( 1 )   szClassExists
   String   szClassExists = null;
   //:INTEGER        Count
   int      Count = 0;
   //:INTEGER        LastTermID
   int      LastTermID = 0;
   //:SHORT          nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;

   RESULT = GetViewByName( wXferO, "wXferO", mStudenC, zLEVEL_TASK );
   //:  
   //:// Create the derived current schedule path (CurrentScheduleEntry) for the current year (StudentScheduleCollegeYear).
   //:// For each Term, we will create a CurrentScheduleEntryTerm entity, with a null entry between Terms. We will also
   //:// create a CurrentScheduleEntryTerm entry to hold each CurrentClass entity.
   //:// Note that this routine first adds all entries, including drops and withdrawns. Drops are later removed by the
   //:// calling operation.

   //:NAME VIEW mStudenC "mStudenCLoading"
   SetNameForView( mStudenC, "mStudenCLoading", null, zLEVEL_TASK );

   //:// Delete any previous entries.
   //:FOR EACH mStudenC.StudentClassHistory 
   RESULT = SetCursorFirstEntity( mStudenC, "StudentClassHistory", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mStudenC.StudentClassHistory.ID != ""
      if ( CompareAttributeToString( mStudenC, "StudentClassHistory", "ID", "" ) != 0 )
      { 
         //:EXCLUDE  mStudenC.StudentClassHistory NONE
         RESULT = ExcludeEntity( mStudenC, "StudentClassHistory", zREPOS_NONE );
         //:ELSE 
      } 
      else
      { 
         //:DELETE ENTITY mStudenC.StudentClassHistory NONE 
         RESULT = DeleteEntity( mStudenC, "StudentClassHistory", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mStudenC, "StudentClassHistory", "" );
      //:END
   } 

   //:END
   //:   
   //:// Add registered entries.
   //:TotalTermCredits = 0
   TotalTermCredits = 0;
   //:TotalYearCredits = 0
   TotalYearCredits = 0;
   //:LastTermID = 0
   LastTermID = 0;
   //:szLastTermYear = ""
    {StringBuilder sb_szLastTermYear;
   if ( szLastTermYear == null )
      sb_szLastTermYear = new StringBuilder( 32 );
   else
      sb_szLastTermYear = new StringBuilder( szLastTermYear );
      ZeidonStringCopy( sb_szLastTermYear, 1, 0, "", 1, 0, 255 );
   szLastTermYear = sb_szLastTermYear.toString( );}
   //:Count = 0
   Count = 0;


   //:// If this is a new student there might not be a lTranscpt.PrintGroup
   //:IF lTrnscpt.PrintGroup EXISTS 
   lTempInteger_0 = CheckExistenceOfEntity( lTrnscpt, "PrintGroup" );
   if ( lTempInteger_0 == 0 )
   { 

      //:FOR EACH lTrnscpt.PrintGroup  WITHIN lTrnscpt.Student 
      RESULT = SetCursorFirstEntity( lTrnscpt, "PrintGroup", "Student" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 

         //:TermCreatedFlag = "N"
          {StringBuilder sb_TermCreatedFlag;
         if ( TermCreatedFlag == null )
            sb_TermCreatedFlag = new StringBuilder( 32 );
         else
            sb_TermCreatedFlag = new StringBuilder( TermCreatedFlag );
                  ZeidonStringCopy( sb_TermCreatedFlag, 1, 0, "N", 1, 0, 2 );
         TermCreatedFlag = sb_TermCreatedFlag.toString( );}

         //:// If PrintRegistration exists then this is not a waived class we want to make sure we create a term heading
         //:// for this class (or classes) if one exists.
         //:IF lTrnscpt.PrintRegistration EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( lTrnscpt, "PrintRegistration" );
         if ( lTempInteger_1 == 0 )
         { 

            //:SET CURSOR  FIRST mStudenC.Registration WITHIN mStudenC.Student 
            //:            WHERE mStudenC.Registration.ID = lTrnscpt.PrintRegistration.ID 
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, lTrnscpt, "PrintRegistration", "ID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mStudenC, "Registration", "ID", lTempInteger_2, "Student" );


            //:CREATE ENTITY mStudenC.StudentClassHistory 
            RESULT = CreateEntity( mStudenC, "StudentClassHistory", zPOS_AFTER );

            //:IF mStudenC.RegistrationClass EXISTS
            lTempInteger_3 = CheckExistenceOfEntity( mStudenC, "RegistrationClass" );
            if ( lTempInteger_3 == 0 )
            { 
               //:GetStringFromAttributeByContext( szSemester, mStudenC, "RegistrationClassCollegeTerm", "Semester", "", 20 )
               {StringBuilder sb_szSemester;
               if ( szSemester == null )
                  sb_szSemester = new StringBuilder( 32 );
               else
                  sb_szSemester = new StringBuilder( szSemester );
                               GetStringFromAttributeByContext( sb_szSemester, mStudenC, "RegistrationClassCollegeTerm", "Semester", "", 20 );
               szSemester = sb_szSemester.toString( );}
               //:szTermYear = mStudenC.RegistrationClassCollegeYear.Year + " " + szSemester
               {StringBuilder sb_szTermYear;
               if ( szTermYear == null )
                  sb_szTermYear = new StringBuilder( 32 );
               else
                  sb_szTermYear = new StringBuilder( szTermYear );
                               GetStringFromAttribute( sb_szTermYear, mStudenC, "RegistrationClassCollegeYear", "Year" );
               szTermYear = sb_szTermYear.toString( );}
                {StringBuilder sb_szTermYear;
               if ( szTermYear == null )
                  sb_szTermYear = new StringBuilder( 32 );
               else
                  sb_szTermYear = new StringBuilder( szTermYear );
                              ZeidonStringConcat( sb_szTermYear, 1, 0, " ", 1, 0, 255 );
               szTermYear = sb_szTermYear.toString( );}
                {StringBuilder sb_szTermYear;
               if ( szTermYear == null )
                  sb_szTermYear = new StringBuilder( 32 );
               else
                  sb_szTermYear = new StringBuilder( szTermYear );
                              ZeidonStringConcat( sb_szTermYear, 1, 0, szSemester, 1, 0, 255 );
               szTermYear = sb_szTermYear.toString( );}
               //:mStudenC.StudentClassHistory.wYearSemester = szTermYear 
               SetAttributeFromString( mStudenC, "StudentClassHistory", "wYearSemester", szTermYear );
               //:ELSE 
            } 
            else
            { 
               //:// Transfered course.
               //:IF mStudenC.TransferRecordTerm EXISTS
               lTempInteger_4 = CheckExistenceOfEntity( mStudenC, "TransferRecordTerm" );
               if ( lTempInteger_4 == 0 )
               { 
                  //:GetStringFromAttributeByContext( szSemester, mStudenC, "TransferRecordTerm", "Semester", "", 20 )
                  {StringBuilder sb_szSemester;
                  if ( szSemester == null )
                     sb_szSemester = new StringBuilder( 32 );
                  else
                     sb_szSemester = new StringBuilder( szSemester );
                                     GetStringFromAttributeByContext( sb_szSemester, mStudenC, "TransferRecordTerm", "Semester", "", 20 );
                  szSemester = sb_szSemester.toString( );}
                  //:szTermYear = mStudenC.TransferRecordYear.Year + " " + szSemester
                  {StringBuilder sb_szTermYear;
                  if ( szTermYear == null )
                     sb_szTermYear = new StringBuilder( 32 );
                  else
                     sb_szTermYear = new StringBuilder( szTermYear );
                                     GetStringFromAttribute( sb_szTermYear, mStudenC, "TransferRecordYear", "Year" );
                  szTermYear = sb_szTermYear.toString( );}
                   {StringBuilder sb_szTermYear;
                  if ( szTermYear == null )
                     sb_szTermYear = new StringBuilder( 32 );
                  else
                     sb_szTermYear = new StringBuilder( szTermYear );
                                    ZeidonStringConcat( sb_szTermYear, 1, 0, " ", 1, 0, 255 );
                  szTermYear = sb_szTermYear.toString( );}
                   {StringBuilder sb_szTermYear;
                  if ( szTermYear == null )
                     sb_szTermYear = new StringBuilder( 32 );
                  else
                     sb_szTermYear = new StringBuilder( szTermYear );
                                    ZeidonStringConcat( sb_szTermYear, 1, 0, szSemester, 1, 0, 255 );
                  szTermYear = sb_szTermYear.toString( );}
                  //:mStudenC.StudentClassHistory.wYearSemester = szTermYear 
                  SetAttributeFromString( mStudenC, "StudentClassHistory", "wYearSemester", szTermYear );
                  //:TermCreatedFlag = "Y"
                   {StringBuilder sb_TermCreatedFlag;
                  if ( TermCreatedFlag == null )
                     sb_TermCreatedFlag = new StringBuilder( 32 );
                  else
                     sb_TermCreatedFlag = new StringBuilder( TermCreatedFlag );
                                    ZeidonStringCopy( sb_TermCreatedFlag, 1, 0, "Y", 1, 0, 2 );
                  TermCreatedFlag = sb_TermCreatedFlag.toString( );}
                  //:ELSE 
               } 
               else
               { 
                  //:// If there are transferred courses that are not tied to a sememster, then just put them under a 
                  //:// generic heading
                  //:mStudenC.StudentClassHistory.wYearSemester = "No Specific Semester"  
                  SetAttributeFromString( mStudenC, "StudentClassHistory", "wYearSemester", "No Specific Semester" );
                  //:TermCreatedFlag = "Y"           
                   {StringBuilder sb_TermCreatedFlag;
                  if ( TermCreatedFlag == null )
                     sb_TermCreatedFlag = new StringBuilder( 32 );
                  else
                     sb_TermCreatedFlag = new StringBuilder( TermCreatedFlag );
                                    ZeidonStringCopy( sb_TermCreatedFlag, 1, 0, "Y", 1, 0, 2 );
                  TermCreatedFlag = sb_TermCreatedFlag.toString( );}
               } 

               //:END         
            } 

            //:END                  
            //:// Loop through all the classes under PrintGroup        
            //:FOR  EACH lTrnscpt.PrintRegistration 
            RESULT = SetCursorFirstEntity( lTrnscpt, "PrintRegistration", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:SET CURSOR  FIRST mStudenC.Registration WITHIN mStudenC.Student 
               //:            WHERE mStudenC.Registration.ID = lTrnscpt.PrintRegistration.ID 
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                               GetIntegerFromAttribute( mi_lTempInteger_5, lTrnscpt, "PrintRegistration", "ID" );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mStudenC, "Registration", "ID", lTempInteger_5, "Student" );
               //:IF  RESULT >= zCURSOR_SET 
               if ( RESULT >= zCURSOR_SET )
               { 

                  //:IF mStudenC.RegistrationClass EXISTS
                  lTempInteger_6 = CheckExistenceOfEntity( mStudenC, "RegistrationClass" );
                  if ( lTempInteger_6 == 0 )
                  { 
                     //:INCLUDE mStudenC.StudentClassHistory FROM mStudenC.RegistrationClass 
                     RESULT = IncludeSubobjectFromSubobject( mStudenC, "StudentClassHistory", mStudenC, "RegistrationClass", zPOS_AFTER );
                     //:ELSE 
                  } 
                  else
                  { 
                     //:// Transfered course. RegistrationClass does not exist
                     //:CREATE ENTITY mStudenC.StudentClassHistory 
                     RESULT = CreateEntity( mStudenC, "StudentClassHistory", zPOS_AFTER );
                  } 

                  //:END
                  //:mStudenC.StudentClassHistory.wMidtermGrade              = mStudenC.Registration.MidtermGrade 
                  SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "wMidtermGrade", mStudenC, "Registration", "MidtermGrade" );
                  //:mStudenC.StudentClassHistory.wFinalGrade                = mStudenC.Registration.FinalGrade 
                  SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "wFinalGrade", mStudenC, "Registration", "FinalGrade" );
                  //:mStudenC.StudentClassHistory.wTakingClassType           = mStudenC.Registration.TakingClassType 
                  SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "wTakingClassType", mStudenC, "Registration", "TakingClassType" );
                  //:GetStringFromAttributeByContext( szExternalStatus,
                  //:                                 mStudenC, "Registration", "Status", "", 20 )
                  {StringBuilder sb_szExternalStatus;
                  if ( szExternalStatus == null )
                     sb_szExternalStatus = new StringBuilder( 32 );
                  else
                     sb_szExternalStatus = new StringBuilder( szExternalStatus );
                                     GetStringFromAttributeByContext( sb_szExternalStatus, mStudenC, "Registration", "Status", "", 20 );
                  szExternalStatus = sb_szExternalStatus.toString( );}
                  //:mStudenC.StudentClassHistory.wStatus = szExternalStatus
                  SetAttributeFromString( mStudenC, "StudentClassHistory", "wStatus", szExternalStatus );
                  //://mStudenC.StudentClassHistory.wCourseTitle   = mStudenC.Registration.wCourseTitle
                  //:mStudenC.StudentClassHistory.wCourseTitle   = mStudenC.Registration.dEnrollmentCourseTitle
                  SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "wCourseTitle", mStudenC, "Registration", "dEnrollmentCourseTitle" );
                  //:mStudenC.StudentClassHistory.wCourseSection = mStudenC.Registration.wCourseNumber 
                  SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "wCourseSection", mStudenC, "Registration", "wCourseNumber" );
                  //:mStudenC.StudentClassHistory.SumAttemptedCredits = lTrnscpt.PrintRegistration.AttemptedCredits 
                  SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "SumAttemptedCredits", lTrnscpt, "PrintRegistration", "AttemptedCredits" );
                  //:mStudenC.StudentClassHistory.SumEarnedCredits = lTrnscpt.PrintRegistration.EarnedCredits 
                  SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "SumEarnedCredits", lTrnscpt, "PrintRegistration", "EarnedCredits" );
               } 

               RESULT = SetCursorNextEntity( lTrnscpt, "PrintRegistration", "" );
               //:END  
            } 

            //:END
         } 

         //:END 
         //:// Waived courses under PrintGroup.
         //:FOR EACH lTrnscpt.PrintWaivedEntry  
         RESULT = SetCursorFirstEntity( lTrnscpt, "PrintWaivedEntry", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 

            //:CREATE ENTITY mStudenC.StudentClassHistory 
            RESULT = CreateEntity( mStudenC, "StudentClassHistory", zPOS_AFTER );

            //:// If there are transferred courses that are not tied to a sememster, then just put them under a 
            //:// generic heading
            //:IF TermCreatedFlag = "N" 
            if ( ZeidonStringCompare( TermCreatedFlag, 1, 0, "N", 1, 0, 2 ) == 0 )
            { 
               //:mStudenC.StudentClassHistory.wYearSemester = "No Specific Semester"  
               SetAttributeFromString( mStudenC, "StudentClassHistory", "wYearSemester", "No Specific Semester" );
               //:TermCreatedFlag = "Y"           
                {StringBuilder sb_TermCreatedFlag;
               if ( TermCreatedFlag == null )
                  sb_TermCreatedFlag = new StringBuilder( 32 );
               else
                  sb_TermCreatedFlag = new StringBuilder( TermCreatedFlag );
                              ZeidonStringCopy( sb_TermCreatedFlag, 1, 0, "Y", 1, 0, 2 );
               TermCreatedFlag = sb_TermCreatedFlag.toString( );}
            } 

            //:END
            //:CREATE ENTITY mStudenC.StudentClassHistory 
            RESULT = CreateEntity( mStudenC, "StudentClassHistory", zPOS_AFTER );
            //:mStudenC.StudentClassHistory.wCourseTitle   = lTrnscpt.PrintWaivedEntry.wCourseTitle 
            SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "wCourseTitle", lTrnscpt, "PrintWaivedEntry", "wCourseTitle" );
            //:mStudenC.StudentClassHistory.wCourseSection = lTrnscpt.PrintWaivedEntry.wCourseNumber  
            SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "wCourseSection", lTrnscpt, "PrintWaivedEntry", "wCourseNumber" );
            //:mStudenC.StudentClassHistory.SumAttemptedCredits = lTrnscpt.PrintWaivedEntry.wCreditHours  
            SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "SumAttemptedCredits", lTrnscpt, "PrintWaivedEntry", "wCreditHours" );
            //:mStudenC.StudentClassHistory.SumEarnedCredits = lTrnscpt.PrintWaivedEntry.wCreditHours  
            SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "SumEarnedCredits", lTrnscpt, "PrintWaivedEntry", "wCreditHours" );
            //:mStudenC.StudentClassHistory.wStatus = "Waived"
            SetAttributeFromString( mStudenC, "StudentClassHistory", "wStatus", "Waived" );
            RESULT = SetCursorNextEntity( lTrnscpt, "PrintWaivedEntry", "" );
         } 

         //:END

         //:IF lTrnscpt.PrintGroup.SumAttemptedCredits != ""
         if ( CompareAttributeToString( lTrnscpt, "PrintGroup", "SumAttemptedCredits", "" ) != 0 )
         { 

            //:CREATE ENTITY mStudenC.StudentClassHistory 
            RESULT = CreateEntity( mStudenC, "StudentClassHistory", zPOS_AFTER );
            //:mStudenC.StudentClassHistory.wStatus = "<span class='credittotals'>Term Totals:</span>"
            SetAttributeFromString( mStudenC, "StudentClassHistory", "wStatus", "<span class='credittotals'>Term Totals:</span>" );
            //:mStudenC.StudentClassHistory.SumAttemptedCredits = lTrnscpt.PrintGroup.SumAttemptedCredits 
            SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "SumAttemptedCredits", lTrnscpt, "PrintGroup", "SumAttemptedCredits" );
            //:mStudenC.StudentClassHistory.SumEarnedCredits = lTrnscpt.PrintGroup.SumEarnedCredits 
            SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "SumEarnedCredits", lTrnscpt, "PrintGroup", "SumEarnedCredits" );
            //:mStudenC.StudentClassHistory.SumGradePoints = lTrnscpt.PrintGroup.SumGradePoints 
            SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "SumGradePoints", lTrnscpt, "PrintGroup", "SumGradePoints" );
            //:mStudenC.StudentClassHistory.SumGPA = lTrnscpt.PrintGroup.SumGPA 
            SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "SumGPA", lTrnscpt, "PrintGroup", "SumGPA" );

            //:CREATE ENTITY mStudenC.StudentClassHistory 
            RESULT = CreateEntity( mStudenC, "StudentClassHistory", zPOS_AFTER );
            //:mStudenC.StudentClassHistory.wStatus = "<span class='credittotals'>Cumulative Totals:</span>"
            SetAttributeFromString( mStudenC, "StudentClassHistory", "wStatus", "<span class='credittotals'>Cumulative Totals:</span>" );
            //:mStudenC.StudentClassHistory.SumAttemptedCredits = lTrnscpt.PrintGroup.CumulativeAttemptedCredits  
            SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "SumAttemptedCredits", lTrnscpt, "PrintGroup", "CumulativeAttemptedCredits" );
            //:mStudenC.StudentClassHistory.SumEarnedCredits = lTrnscpt.PrintGroup.CumulativeEarnedCredits  
            SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "SumEarnedCredits", lTrnscpt, "PrintGroup", "CumulativeEarnedCredits" );
            //:mStudenC.StudentClassHistory.SumGradePoints = lTrnscpt.PrintGroup.CumulativeGPA_Points  
            SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "SumGradePoints", lTrnscpt, "PrintGroup", "CumulativeGPA_Points" );
            //:mStudenC.StudentClassHistory.SumGPA = lTrnscpt.PrintGroup.CumulativeGPA  
            SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "SumGPA", lTrnscpt, "PrintGroup", "CumulativeGPA" );
         } 

         RESULT = SetCursorNextEntity( lTrnscpt, "PrintGroup", "Student" );
         //:END
      } 

      //:   
      //:END

      //:IF lTrnscpt.PrintGroup.CumulativeAttemptedCredits != ""
      if ( CompareAttributeToString( lTrnscpt, "PrintGroup", "CumulativeAttemptedCredits", "" ) != 0 )
      { 
         //:mStudenC.Student.CumulativeAttemptedCredits = lTrnscpt.PrintGroup.CumulativeAttemptedCredits
         SetAttributeFromAttribute( mStudenC, "Student", "CumulativeAttemptedCredits", lTrnscpt, "PrintGroup", "CumulativeAttemptedCredits" );
         //:mStudenC.Student.CumulativeEarnedCredits = lTrnscpt.PrintGroup.CumulativeEarnedCredits
         SetAttributeFromAttribute( mStudenC, "Student", "CumulativeEarnedCredits", lTrnscpt, "PrintGroup", "CumulativeEarnedCredits" );
         //:mStudenC.Student.CumulativeGPA_Points = lTrnscpt.PrintGroup.CumulativeGPA_Points
         SetAttributeFromAttribute( mStudenC, "Student", "CumulativeGPA_Points", lTrnscpt, "PrintGroup", "CumulativeGPA_Points" );
         //:mStudenC.Student.CumulativeGPA = lTrnscpt.PrintGroup.CumulativeGPA
         SetAttributeFromAttribute( mStudenC, "Student", "CumulativeGPA", lTrnscpt, "PrintGroup", "CumulativeGPA" );

         //:TotalCredits = lTrnscpt.PrintGroup.CumulativeAttemptedCredits
         {MutableDouble md_TotalCredits = new MutableDouble( TotalCredits );
                   GetDecimalFromAttribute( md_TotalCredits, lTrnscpt, "PrintGroup", "CumulativeAttemptedCredits" );
         TotalCredits = md_TotalCredits.doubleValue( );}

         //:// Do we want to add what the student is currently enrolled in?
         //:SET CURSOR NEXT mStudenC.Registration
         RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
         //:ELSE
      } 
      else
      { 
         //:// This is a new student so there was nothing in the transcript.
         //:// Add classes student is currently enrolled in.
         //:SET CURSOR FIRST mStudenC.Registration 
         RESULT = SetCursorFirstEntity( mStudenC, "Registration", "" );
      } 

      //:END

      //:ELSE
   } 
   else
   { 
      //:// This is a new student so there was nothing in the transcript.
      //:// Add classes student is currently enrolled in.
      //:SET CURSOR FIRST mStudenC.Registration 
      RESULT = SetCursorFirstEntity( mStudenC, "Registration", "" );
   } 


   //:END

   //:// If there were dropped classes from this sememster, we need to get past them and
   //:// see if this student has classes that they are currently taking but haven't completed.
   //:// KJS 08/19/2009 - We just came across a student "morina" that had transferred classes and then
   //:// this is her first sememster.  When we got here we were on a "Registration" entity that was a class
   //:// she has taken but didn't transfer over so when we created the StudentClassHistory and tried to get the
   //:// Semester, RegistrationClassCollegeTerm didn't exist.  We will try stepping past all registrations that don't
   //:// have a RegistrationClass and hopefully then we are pointed to current enrolled classes.  I believe this should
   //:// only be a problem when this is a new student with no classes that have been taken.
   //:// KJS 10/11/09 - This isn't working if someone like "harperr" has a transfered class that has happened after they have
   //:// already taken a class at ENC.  Don't see why I did this, can't I just position on where Registration.Status = "T" which
   //:// is enrolled.  Those would be the latest classes.
   //://LOOP WHILE RESULT >= zCURSOR_SET AND ( mStudenC.Registration.Status = "D" OR mStudenC.RegistrationClass DOES NOT EXIST )
   //://   SET CURSOR NEXT mStudenC.Registration 
   //://END

   //:// After creating classes that have been completed or transfered, see if there are classes where the 
   //:// student is currently enrolled.
   //:IF szCollegeType = "U"
   if ( ZeidonStringCompare( szCollegeType, 1, 0, "U", 1, 0, 2 ) == 0 )
   { 
      //:// Undergraduate classes.
      //:SET CURSOR FIRST mStudenC.Registration WHERE mStudenC.Registration.Status = "T" AND 
      //:                 ((mStudenC.RegistrationCourseCollege.ID = 1 AND mStudenC.Registration.GradUndergradOverrideFlag = "") OR
      //:                  (mStudenC.RegistrationCourseCollege.ID = 2 AND mStudenC.Registration.GradUndergradOverrideFlag = "U"))
      RESULT = SetCursorFirstEntity( mStudenC, "Registration", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) != 0 || ( ( CompareAttributeToInteger( mStudenC, "RegistrationCourseCollege", "ID", 1 ) != 0 ||
                 CompareAttributeToString( mStudenC, "Registration", "GradUndergradOverrideFlag", "" ) != 0 ) && ( CompareAttributeToInteger( mStudenC, "RegistrationCourseCollege", "ID", 2 ) != 0 ||
                 CompareAttributeToString( mStudenC, "Registration", "GradUndergradOverrideFlag", "U" ) != 0 ) ) ) )
         { 
            RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
         } 

      } 

      //:ELSE 
   } 
   else
   { 
      //:IF szCollegeType = "G" 
      if ( ZeidonStringCompare( szCollegeType, 1, 0, "G", 1, 0, 2 ) == 0 )
      { 
         //:// Graduate classes.
         //:SET CURSOR FIRST mStudenC.Registration WHERE mStudenC.Registration.Status = "T" AND 
         //:              ((mStudenC.RegistrationCourseCollege.ID = 2 AND mStudenC.Registration.GradUndergradOverrideFlag = "") OR
         //:              (mStudenC.RegistrationCourseCollege.ID = 1 AND mStudenC.Registration.GradUndergradOverrideFlag = "G"))
         RESULT = SetCursorFirstEntity( mStudenC, "Registration", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) != 0 || ( ( CompareAttributeToInteger( mStudenC, "RegistrationCourseCollege", "ID", 2 ) != 0 ||
                    CompareAttributeToString( mStudenC, "Registration", "GradUndergradOverrideFlag", "" ) != 0 ) && ( CompareAttributeToInteger( mStudenC, "RegistrationCourseCollege", "ID", 1 ) != 0 ||
                    CompareAttributeToString( mStudenC, "Registration", "GradUndergradOverrideFlag", "G" ) != 0 ) ) ) )
            { 
               RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
            } 

         } 

         //:ELSE
      } 
      else
      { 
         //:// Certificate.      
         //:SET CURSOR FIRST mStudenC.Registration WHERE mStudenC.Registration.Status = "T" AND mStudenC.RegistrationCourseCollege.ID = 5
         RESULT = SetCursorFirstEntity( mStudenC, "Registration", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) != 0 || CompareAttributeToInteger( mStudenC, "RegistrationCourseCollege", "ID", 5 ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
            } 

         } 

      } 

      //:END
   } 

   //:END

   //:TotalTermCredits = 0
   TotalTermCredits = 0;

   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:   
      //:CREATE ENTITY mStudenC.StudentClassHistory 
      RESULT = CreateEntity( mStudenC, "StudentClassHistory", zPOS_AFTER );
      //:GetStringFromAttributeByContext( szSemester, mStudenC, "RegistrationClassCollegeTerm", "Semester", "", 20 )
      {StringBuilder sb_szSemester;
      if ( szSemester == null )
         sb_szSemester = new StringBuilder( 32 );
      else
         sb_szSemester = new StringBuilder( szSemester );
             GetStringFromAttributeByContext( sb_szSemester, mStudenC, "RegistrationClassCollegeTerm", "Semester", "", 20 );
      szSemester = sb_szSemester.toString( );}
      //:szTermYear = mStudenC.RegistrationClassCollegeYear.Year + " " + szSemester
      {StringBuilder sb_szTermYear;
      if ( szTermYear == null )
         sb_szTermYear = new StringBuilder( 32 );
      else
         sb_szTermYear = new StringBuilder( szTermYear );
             GetStringFromAttribute( sb_szTermYear, mStudenC, "RegistrationClassCollegeYear", "Year" );
      szTermYear = sb_szTermYear.toString( );}
       {StringBuilder sb_szTermYear;
      if ( szTermYear == null )
         sb_szTermYear = new StringBuilder( 32 );
      else
         sb_szTermYear = new StringBuilder( szTermYear );
            ZeidonStringConcat( sb_szTermYear, 1, 0, " ", 1, 0, 255 );
      szTermYear = sb_szTermYear.toString( );}
       {StringBuilder sb_szTermYear;
      if ( szTermYear == null )
         sb_szTermYear = new StringBuilder( 32 );
      else
         sb_szTermYear = new StringBuilder( szTermYear );
            ZeidonStringConcat( sb_szTermYear, 1, 0, szSemester, 1, 0, 255 );
      szTermYear = sb_szTermYear.toString( );}
      //:szLastTermYear = szTermYear
       {StringBuilder sb_szLastTermYear;
      if ( szLastTermYear == null )
         sb_szLastTermYear = new StringBuilder( 32 );
      else
         sb_szLastTermYear = new StringBuilder( szLastTermYear );
            ZeidonStringCopy( sb_szLastTermYear, 1, 0, szTermYear, 1, 0, 255 );
      szLastTermYear = sb_szLastTermYear.toString( );}
      //:mStudenC.StudentClassHistory.wYearSemester = szTermYear 
      SetAttributeFromString( mStudenC, "StudentClassHistory", "wYearSemester", szTermYear );

      //:LOOP WHILE RESULT >= zCURSOR_SET
      while ( RESULT >= zCURSOR_SET )
      { 

         //:GetStringFromAttributeByContext( szSemester, mStudenC, "RegistrationClassCollegeTerm", "Semester", "", 20 )
         {StringBuilder sb_szSemester;
         if ( szSemester == null )
            sb_szSemester = new StringBuilder( 32 );
         else
            sb_szSemester = new StringBuilder( szSemester );
                   GetStringFromAttributeByContext( sb_szSemester, mStudenC, "RegistrationClassCollegeTerm", "Semester", "", 20 );
         szSemester = sb_szSemester.toString( );}
         //:szTermYear = mStudenC.RegistrationClassCollegeYear.Year + " " + szSemester
         {StringBuilder sb_szTermYear;
         if ( szTermYear == null )
            sb_szTermYear = new StringBuilder( 32 );
         else
            sb_szTermYear = new StringBuilder( szTermYear );
                   GetStringFromAttribute( sb_szTermYear, mStudenC, "RegistrationClassCollegeYear", "Year" );
         szTermYear = sb_szTermYear.toString( );}
          {StringBuilder sb_szTermYear;
         if ( szTermYear == null )
            sb_szTermYear = new StringBuilder( 32 );
         else
            sb_szTermYear = new StringBuilder( szTermYear );
                  ZeidonStringConcat( sb_szTermYear, 1, 0, " ", 1, 0, 255 );
         szTermYear = sb_szTermYear.toString( );}
          {StringBuilder sb_szTermYear;
         if ( szTermYear == null )
            sb_szTermYear = new StringBuilder( 32 );
         else
            sb_szTermYear = new StringBuilder( szTermYear );
                  ZeidonStringConcat( sb_szTermYear, 1, 0, szSemester, 1, 0, 255 );
         szTermYear = sb_szTermYear.toString( );}

         //:// If we get into a new term, then these would be future registered classes.  We are not going to show those.
         //:IF szTermYear = szLastTermYear 
         if ( ZeidonStringCompare( szTermYear, 1, 0, szLastTermYear, 1, 0, 255 ) == 0 )
         { 

            //:IF  mStudenC.Registration.Status != "D" 
            if ( CompareAttributeToString( mStudenC, "Registration", "Status", "D" ) != 0 )
            { 
               //:// KJS 12/23/09 - I am not sure why but I have an entity that when I look at it on mStudenC.Registration the
               //:// status = "T" (enrolled) but when I look at the same entity on lTranscrpt it says the status = "C" so when
               //:// I get here, the entity is already created, I get an error.  Check if the entity exists first. This is for swensone.
               //:CreateViewFromView( mStudCTmp, mStudenC)
               CreateViewFromView( mStudCTmp, mStudenC );
               //:szClassExists = "N"
                {StringBuilder sb_szClassExists;
               if ( szClassExists == null )
                  sb_szClassExists = new StringBuilder( 32 );
               else
                  sb_szClassExists = new StringBuilder( szClassExists );
                              ZeidonStringCopy( sb_szClassExists, 1, 0, "N", 1, 0, 2 );
               szClassExists = sb_szClassExists.toString( );}
               //:FOR EACH  mStudCTmp.StudentClassHistory 
               RESULT = SetCursorFirstEntity( mStudCTmp, "StudentClassHistory", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:IF mStudCTmp.StudentClassHistory.ID = mStudenC.RegistrationClass.ID 
                  if ( CompareAttributeToAttribute( mStudCTmp, "StudentClassHistory", "ID", mStudenC, "RegistrationClass", "ID" ) == 0 )
                  { 
                     //:// Don't add
                     //:szClassExists = "Y" 
                      {StringBuilder sb_szClassExists;
                     if ( szClassExists == null )
                        sb_szClassExists = new StringBuilder( 32 );
                     else
                        sb_szClassExists = new StringBuilder( szClassExists );
                                          ZeidonStringCopy( sb_szClassExists, 1, 0, "Y", 1, 0, 2 );
                     szClassExists = sb_szClassExists.toString( );}
                  } 

                  RESULT = SetCursorNextEntity( mStudCTmp, "StudentClassHistory", "" );
                  //:END
               } 

               //:END
               //:DropView( mStudCTmp )
               DropView( mStudCTmp );

               //:IF szClassExists = "N" 
               if ( ZeidonStringCompare( szClassExists, 1, 0, "N", 1, 0, 2 ) == 0 )
               { 

                  //:INCLUDE mStudenC.StudentClassHistory FROM mStudenC.RegistrationClass 
                  RESULT = IncludeSubobjectFromSubobject( mStudenC, "StudentClassHistory", mStudenC, "RegistrationClass", zPOS_AFTER );
                  //:GetStringFromAttributeByContext( szExternalStatus,
                  //:                                 mStudenC, "Registration", "Status", "", 20 )
                  {StringBuilder sb_szExternalStatus;
                  if ( szExternalStatus == null )
                     sb_szExternalStatus = new StringBuilder( 32 );
                  else
                     sb_szExternalStatus = new StringBuilder( szExternalStatus );
                                     GetStringFromAttributeByContext( sb_szExternalStatus, mStudenC, "Registration", "Status", "", 20 );
                  szExternalStatus = sb_szExternalStatus.toString( );}
                  //:mStudenC.StudentClassHistory.wStatus = szExternalStatus
                  SetAttributeFromString( mStudenC, "StudentClassHistory", "wStatus", szExternalStatus );
                  //:mStudenC.StudentClassHistory.wCourseTitle   = mStudenC.Registration.wCourseTitle
                  SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "wCourseTitle", mStudenC, "Registration", "wCourseTitle" );
                  //:mStudenC.StudentClassHistory.wCourseSection = mStudenC.Registration.wCourseNumber 
                  SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "wCourseSection", mStudenC, "Registration", "wCourseNumber" );
                  //:mStudenC.StudentClassHistory.SumAttemptedCredits = mStudenC.RegistrationClass.CreditHours  
                  SetAttributeFromAttribute( mStudenC, "StudentClassHistory", "SumAttemptedCredits", mStudenC, "RegistrationClass", "CreditHours" );
                  //:TotalTermCredits = TotalTermCredits + mStudenC.RegistrationClass.CreditHours
                  {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                     GetDecimalFromAttribute( md_dTempDecimal_0, mStudenC, "RegistrationClass", "CreditHours" );
                  dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                  TotalTermCredits = TotalTermCredits + dTempDecimal_0;
                  //:mStudenC.Student.CumulativeAttemptedCredits = mStudenC.Student.CumulativeAttemptedCredits + mStudenC.RegistrationClass.CreditHours
                  {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                                     GetDecimalFromAttribute( md_dTempDecimal_1, mStudenC, "Student", "CumulativeAttemptedCredits" );
                  dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
                  {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                                     GetDecimalFromAttribute( md_dTempDecimal_2, mStudenC, "RegistrationClass", "CreditHours" );
                  dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
                  dTempDecimal_3 = dTempDecimal_1 + dTempDecimal_2;
                  SetAttributeFromDecimal( mStudenC, "Student", "CumulativeAttemptedCredits", dTempDecimal_3 );
               } 

               //:END
            } 

            //:END
         } 


         //:END
         //://SET CURSOR NEXT mStudenC.Registration 
         //:IF szCollegeType = "U"
         if ( ZeidonStringCompare( szCollegeType, 1, 0, "U", 1, 0, 2 ) == 0 )
         { 
            //:// Undergraduate classes.
            //:SET CURSOR NEXT mStudenC.Registration WHERE mStudenC.Registration.Status = "T" AND 
            //:           ((mStudenC.RegistrationCourseCollege.ID = 1 AND mStudenC.Registration.GradUndergradOverrideFlag = "") OR
            //:            (mStudenC.RegistrationCourseCollege.ID = 2 AND mStudenC.Registration.GradUndergradOverrideFlag = "U"))
            RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) != 0 || ( ( CompareAttributeToInteger( mStudenC, "RegistrationCourseCollege", "ID", 1 ) != 0 ||
                       CompareAttributeToString( mStudenC, "Registration", "GradUndergradOverrideFlag", "" ) != 0 ) && ( CompareAttributeToInteger( mStudenC, "RegistrationCourseCollege", "ID", 2 ) != 0 ||
                       CompareAttributeToString( mStudenC, "Registration", "GradUndergradOverrideFlag", "U" ) != 0 ) ) ) )
               { 
                  RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
               } 

            } 

            //:ELSE 
         } 
         else
         { 
            //:IF szCollegeType = "G" 
            if ( ZeidonStringCompare( szCollegeType, 1, 0, "G", 1, 0, 2 ) == 0 )
            { 
               //:// Graduate classes.
               //:SET CURSOR NEXT mStudenC.Registration WHERE mStudenC.Registration.Status = "T" AND 
               //:        ((mStudenC.RegistrationCourseCollege.ID = 2 AND mStudenC.Registration.GradUndergradOverrideFlag = "") OR
               //:        (mStudenC.RegistrationCourseCollege.ID = 1 AND mStudenC.Registration.GradUndergradOverrideFlag = "G"))
               RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) != 0 || ( ( CompareAttributeToInteger( mStudenC, "RegistrationCourseCollege", "ID", 2 ) != 0 ||
                        CompareAttributeToString( mStudenC, "Registration", "GradUndergradOverrideFlag", "" ) != 0 ) && ( CompareAttributeToInteger( mStudenC, "RegistrationCourseCollege", "ID", 1 ) != 0 ||
                        CompareAttributeToString( mStudenC, "Registration", "GradUndergradOverrideFlag", "G" ) != 0 ) ) ) )
                  { 
                     RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
                  } 

               } 

               //:ELSE
            } 
            else
            { 
               //:// Certificate.            
               //:SET CURSOR NEXT mStudenC.Registration WHERE mStudenC.Registration.Status = "T" AND mStudenC.RegistrationCourseCollege.ID = 5
               RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) != 0 || CompareAttributeToInteger( mStudenC, "RegistrationCourseCollege", "ID", 5 ) != 0 ) )
                  { 
                     RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
                  } 

               } 

            } 

            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:END 
   //:   
   //:IF TotalTermCredits > 0
   if ( TotalTermCredits > 0 )
   { 
      //:// Create Final Totals entry.
      //:CREATE ENTITY mStudenC.StudentClassHistory 
      RESULT = CreateEntity( mStudenC, "StudentClassHistory", zPOS_AFTER );
      //:mStudenC.StudentClassHistory.wStatus = "<span class='credittotals'>Term Totals:</span>"
      SetAttributeFromString( mStudenC, "StudentClassHistory", "wStatus", "<span class='credittotals'>Term Totals:</span>" );
      //:mStudenC.StudentClassHistory.SumAttemptedCredits = TotalTermCredits
      SetAttributeFromDecimal( mStudenC, "StudentClassHistory", "SumAttemptedCredits", TotalTermCredits );
      //:CREATE ENTITY mStudenC.StudentClassHistory 
      RESULT = CreateEntity( mStudenC, "StudentClassHistory", zPOS_AFTER );
      //:mStudenC.StudentClassHistory.wStatus = "<span class='credittotals'>Cumulative Totals:</span>"
      SetAttributeFromString( mStudenC, "StudentClassHistory", "wStatus", "<span class='credittotals'>Cumulative Totals:</span>" );
      //:TotalCredits = TotalCredits + TotalTermCredits
      TotalCredits = TotalCredits + TotalTermCredits;
      //:mStudenC.StudentClassHistory.SumAttemptedCredits = TotalCredits  
      SetAttributeFromDecimal( mStudenC, "StudentClassHistory", "SumAttemptedCredits", TotalCredits );
   } 

   //:END
   //:   
   //:DropNameForView( mStudenC, "mStudenCLoading", mStudenC, zLEVEL_TASK )
   DropNameForView( mStudenC, "mStudenCLoading", mStudenC, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dEquivalencyOL_Value( VIEW mStudenC BASED ON LOD mStudenC,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )

//:   STRING ( 200 ) szOL_Value
public int 
omStudenC_dEquivalencyOL_Value( View     mStudenC,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   String   szOL_Value = null;
   //:STRING ( 5 )   szGrade
   String   szGrade = null;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:GetStringFromAttributeByContext( szGrade,
         //:                                 mStudenC, "StudentEquivalency", "ClassGrade", "", 5 )
         {StringBuilder sb_szGrade;
         if ( szGrade == null )
            sb_szGrade = new StringBuilder( 32 );
         else
            sb_szGrade = new StringBuilder( szGrade );
                   GetStringFromAttributeByContext( sb_szGrade, mStudenC, "StudentEquivalency", "ClassGrade", "", 5 );
         szGrade = sb_szGrade.toString( );}
         //:szOL_Value = "FOREIGN CLASS: " +
         //:          mStudenC.StudentEquivalency.ForeignClassName + " - " +
         //:          mStudenC.StudentEquivalency.ForeignClassDescription
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 51, mStudenC, "StudentEquivalency", "ForeignClassName", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szOL_Value;
         if ( szOL_Value == null )
            sb_szOL_Value = new StringBuilder( 32 );
         else
            sb_szOL_Value = new StringBuilder( szOL_Value );
                  ZeidonStringCopy( sb_szOL_Value, 1, 0, "FOREIGN CLASS: ", 1, 0, 201 );
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
                  ZeidonStringConcat( sb_szOL_Value, 1, 0, " - ", 1, 0, 201 );
         szOL_Value = sb_szOL_Value.toString( );}
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 10001, mStudenC, "StudentEquivalency", "ForeignClassDescription", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szOL_Value;
         if ( szOL_Value == null )
            sb_szOL_Value = new StringBuilder( 32 );
         else
            sb_szOL_Value = new StringBuilder( szOL_Value );
                  ZeidonStringConcat( sb_szOL_Value, 1, 0, szTempString_1, 1, 0, 201 );
         szOL_Value = sb_szOL_Value.toString( );}
         //:IF szGrade != ""
         if ( ZeidonStringCompare( szGrade, 1, 0, "", 1, 0, 6 ) != 0 )
         { 
            //:szOL_Value = szOL_Value + " (" + szGrade + ")"
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
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, szGrade, 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
             {StringBuilder sb_szOL_Value;
            if ( szOL_Value == null )
               sb_szOL_Value = new StringBuilder( 32 );
            else
               sb_szOL_Value = new StringBuilder( szOL_Value );
                        ZeidonStringConcat( sb_szOL_Value, 1, 0, ")", 1, 0, 201 );
            szOL_Value = sb_szOL_Value.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mStudenC,
         //:                   InternalEntityStructure, InternalAttribStructure, szOL_Value )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szOL_Value );
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
//:dTermOL_Value( VIEW mStudenC BASED ON LOD mStudenC,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   STRING ( 200 ) szOL_Value
public int 
omStudenC_dTermOL_Value( View     mStudenC,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   String   szOL_Value = null;
   //:STRING ( 10 )  szTermText
   String   szTermText = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:GetStringFromAttributeByContext( szTermText,
         //:                              mStudenC, "StudentCollegeTerm", "YearSemester", "", 10 )
         {StringBuilder sb_szTermText;
         if ( szTermText == null )
            sb_szTermText = new StringBuilder( 32 );
         else
            sb_szTermText = new StringBuilder( szTermText );
                   GetStringFromAttributeByContext( sb_szTermText, mStudenC, "StudentCollegeTerm", "YearSemester", "", 10 );
         szTermText = sb_szTermText.toString( );}
         //:szOL_Value = "TERM: " + szTermText
          {StringBuilder sb_szOL_Value;
         if ( szOL_Value == null )
            sb_szOL_Value = new StringBuilder( 32 );
         else
            sb_szOL_Value = new StringBuilder( szOL_Value );
                  ZeidonStringCopy( sb_szOL_Value, 1, 0, "TERM: ", 1, 0, 201 );
         szOL_Value = sb_szOL_Value.toString( );}
          {StringBuilder sb_szOL_Value;
         if ( szOL_Value == null )
            sb_szOL_Value = new StringBuilder( 32 );
         else
            sb_szOL_Value = new StringBuilder( szOL_Value );
                  ZeidonStringConcat( sb_szOL_Value, 1, 0, szTermText, 1, 0, 201 );
         szOL_Value = sb_szOL_Value.toString( );}

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mStudenC,
         //:                   InternalEntityStructure, InternalAttribStructure, szOL_Value )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szOL_Value );
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
//:dGradePointAverage( VIEW mStudenC BASED ON LOD mStudenC,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   DECIMAL dGPA
public int 
omStudenC_dGradePointAverage( View     mStudenC,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   double  dGPA = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :

         //:ComputeGradePointAverage( mStudenC, dGPA )

         //:StoreValueInRecord ( mStudenC,
         //:                  InternalEntityStructure, InternalAttribStructure, dGPA, 0 )
         StoreValueInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, dGPA, 0 );
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
//:dEarnedCredits( VIEW mStudenC BASED ON LOD mStudenC,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   DECIMAL TotalCredits
public int 
omStudenC_dEarnedCredits( View     mStudenC,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   double  TotalCredits = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :

         //:IssueError( mStudenC,0,0, "dEarnedCredits Operation has been removed." )
         IssueError( mStudenC, 0, 0, "dEarnedCredits Operation has been removed." );
         break ;
      //:/*ComputeEarnedCredits( mStudenC, TotalCredits )

      //:StoreValueInRecord ( mStudenC,
      //:                     InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 )*/

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
//:dEnrolledCredits( VIEW mStudenC BASED ON LOD mStudenC,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   VIEW mStudenCT BASED ON LOD mStudenC
public int 
omStudenC_dEnrolledCredits( View     mStudenC,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   zVIEW    mStudenCT = new zVIEW( );
   //:DECIMAL TotalCredits
   double  TotalCredits = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView( mStudenCT, mStudenC )
         CreateViewFromView( mStudenCT, mStudenC );
         //:TotalCredits = 0
         TotalCredits = 0;
         //:FOR EACH mStudenCT.Registration
         RESULT = SetCursorFirstEntity( mStudenCT, "Registration", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mStudenCT.Registration.Status = "T"
            if ( CompareAttributeToString( mStudenCT, "Registration", "Status", "T" ) == 0 )
            { 
               //:TotalCredits = TotalCredits + mStudenCT.Registration.CreditHours 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mStudenCT, "Registration", "CreditHours" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               TotalCredits = TotalCredits + dTempDecimal_0;
               //:ELSE
            } 
            else
            { 
               //:IF mStudenCT.Registration.FinalGrade = "IP"
               if ( CompareAttributeToString( mStudenCT, "Registration", "FinalGrade", "IP" ) == 0 )
               { 
                  //:TotalCredits = TotalCredits + mStudenCT.Registration.CreditHours
                  {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                                     GetDecimalFromAttribute( md_dTempDecimal_1, mStudenCT, "Registration", "CreditHours" );
                  dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
                  TotalCredits = TotalCredits + dTempDecimal_1;
               } 

               //:END
            } 

            RESULT = SetCursorNextEntity( mStudenCT, "Registration", "" );
            //:END
         } 

         //:END
         //:DropView( mStudenCT )
         DropView( mStudenCT );

         //:StoreValueInRecord ( mStudenC,
         //:                  InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 )
         StoreValueInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 );
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
//:dEnrolledCreditsTerm( VIEW mStudenC BASED ON LOD mStudenC,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )

//:   VIEW mStudenCT BASED ON LOD mStudenC
public int 
omStudenC_dEnrolledCreditsTerm( View     mStudenC,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   zVIEW    mStudenCT = new zVIEW( );
   //:DECIMAL TotalCredits
   double  TotalCredits = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView( mStudenCT, mStudenC )
         CreateViewFromView( mStudenCT, mStudenC );
         //:TotalCredits = 0
         TotalCredits = 0;
         //:FOR EACH mStudenCT.US_Registration WHERE mStudenCT.US_Registration.Status = "T"
         RESULT = SetCursorFirstEntityByString( mStudenCT, "US_Registration", "Status", "T", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:TotalCredits = TotalCredits + mStudenCT.US_Registration.CreditHours 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mStudenCT, "US_Registration", "CreditHours" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            TotalCredits = TotalCredits + dTempDecimal_0;
            RESULT = SetCursorNextEntityByString( mStudenCT, "US_Registration", "Status", "T", "" );
         } 

         //:END
         //:DropView( mStudenCT )
         DropView( mStudenCT );

         //:StoreValueInRecord ( mStudenC,
         //:                  InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 )
         StoreValueInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 );
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
//:dPreTransferCredits( VIEW mStudenC BASED ON LOD mStudenC,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   VIEW mStudenCT BASED ON LOD mStudenC
public int 
omStudenC_dPreTransferCredits( View     mStudenC,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   zVIEW    mStudenCT = new zVIEW( );
   //:DECIMAL TotalCredits
   double  TotalCredits = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView( mStudenCT, mStudenC )
         CreateViewFromView( mStudenCT, mStudenC );
         //:TotalCredits = 0
         TotalCredits = 0;
         //:FOR EACH mStudenCT.Registration WHERE mStudenCT.Registration.Status = "P"
         RESULT = SetCursorFirstEntityByString( mStudenCT, "Registration", "Status", "P", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:TotalCredits = TotalCredits + mStudenCT.Registration.CreditHours 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mStudenCT, "Registration", "CreditHours" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            TotalCredits = TotalCredits + dTempDecimal_0;
            RESULT = SetCursorNextEntityByString( mStudenCT, "Registration", "Status", "P", "" );
         } 

         //:END
         //:DropView( mStudenCT )
         DropView( mStudenCT );

         //:StoreValueInRecord ( mStudenC,
         //:                  InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 )
         StoreValueInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 );
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
//:dDivisorCredits( VIEW mStudenC BASED ON LOD mStudenC,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )

//:   DECIMAL TotalCredits
public int 
omStudenC_dDivisorCredits( View     mStudenC,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   double  TotalCredits = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :

         //:IssueError( mStudenC,0,0, "dDivisorCredits Operation has been removed." )
         IssueError( mStudenC, 0, 0, "dDivisorCredits Operation has been removed." );
         //:ComputeGPA_Divisor( mStudenC, TotalCredits )
         //:StoreValueInRecord ( mStudenC,
         //:                  InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 )
         StoreValueInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 );
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
//:dLastClassEndDate( VIEW mStudenC BASED ON LOD mStudenC,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )
//:   VIEW mStudenC2 BASED ON LOD mStudenC
public int 
omStudenC_dLastClassEndDate( View     mStudenC,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   zVIEW    mStudenC2 = new zVIEW( );
   //:STRING (  8  ) szDate 
   String   szDate = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView( mStudenC2, mStudenC )
         CreateViewFromView( mStudenC2, mStudenC );
         //:FOR EACH mStudenC2.RegistrationClass WITHIN mStudenC2.Student 
         RESULT = SetCursorFirstEntity( mStudenC2, "RegistrationClass", "Student" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mStudenC2.Registration.Status = "T" OR
            //:   mStudenC2.Registration.Status = "C"
            if ( CompareAttributeToString( mStudenC2, "Registration", "Status", "T" ) == 0 || CompareAttributeToString( mStudenC2, "Registration", "Status", "C" ) == 0 )
            { 

               //:IF szDate = ""
               if ( ZeidonStringCompare( szDate, 1, 0, "", 1, 0, 9 ) == 0 )
               { 
                  //:szDate = mStudenC2.RegistrationClass.ClassEndDate 
                  {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                  StringBuilder sb_szDate;
                  if ( szDate == null )
                     sb_szDate = new StringBuilder( 32 );
                  else
                     sb_szDate = new StringBuilder( szDate );
                                     GetVariableFromAttribute( sb_szDate, mi_lTempInteger_0, 'S', 9, mStudenC2, "RegistrationClass", "ClassEndDate", "", 0 );
                  lTempInteger_0 = mi_lTempInteger_0.intValue( );
                  szDate = sb_szDate.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:IF szDate < mStudenC2.RegistrationClass.ClassEndDate 
                  if ( CompareAttributeToString( mStudenC2, "RegistrationClass", "ClassEndDate", szDate ) > 0 )
                  { 
                     //:szDate = mStudenC2.RegistrationClass.ClassEndDate
                     {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                     StringBuilder sb_szDate;
                     if ( szDate == null )
                        sb_szDate = new StringBuilder( 32 );
                     else
                        sb_szDate = new StringBuilder( szDate );
                                           GetVariableFromAttribute( sb_szDate, mi_lTempInteger_1, 'S', 9, mStudenC2, "RegistrationClass", "ClassEndDate", "", 0 );
                     lTempInteger_1 = mi_lTempInteger_1.intValue( );
                     szDate = sb_szDate.toString( );}
                  } 

                  //:END
               } 

               //:END
            } 

            RESULT = SetCursorNextEntity( mStudenC2, "RegistrationClass", "Student" );
            //:END
         } 

         //:END
         //:DropView( mStudenC2 )
         DropView( mStudenC2 );
         //:StoreStringInRecord ( mStudenC,
         //:                      InternalEntityStructure, InternalAttribStructure, szDate )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szDate );
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
//:BuildClassScheduleAttr( VIEW mStudenC BASED ON LOD mStudenC,
//:                        STRING ( 254 ) szMeetingSchedule,
//:                        STRING ( 32 )  szRegistrationEntityName )

//:   STRING ( 10 )  szMeetingDay
public int 
omStudenC_BuildClassScheduleAttr( View     mStudenC,
                                  StringBuilder   szMeetingSchedule,
                                  String   szRegistrationEntityName )
{
   String   szMeetingDay = null;
   //:STRING ( 10 )  szMeetingStartTime
   String   szMeetingStartTime = null;
   //:STRING ( 10 )  szMeetingEndTime
   String   szMeetingEndTime = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:// Build the Class Schedule attributefor either the "Registration" or "US_Registration" subobject.
   //:// If a standard schedule exists. simply use it.
   //:// If a custom schedule exists, we will have to build the attribute.
   //:IF szRegistrationEntityName = "Registration"
   if ( ZeidonStringCompare( szRegistrationEntityName, 1, 0, "Registration", 1, 0, 33 ) == 0 )
   { 
      //:IF mStudenC.ClassRoomStandardSchedule EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "ClassRoomStandardSchedule" );
      if ( lTempInteger_0 == 0 )
      { 
         //:szMeetingSchedule = mStudenC.ClassRoomStandardSchedule.Title 
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                   GetVariableFromAttribute( szMeetingSchedule, mi_lTempInteger_1, 'S', 255, mStudenC, "ClassRoomStandardSchedule", "Title", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
         //:ELSE
      } 
      else
      { 
         //:szMeetingSchedule = ""
         ZeidonStringCopy( szMeetingSchedule, 1, 0, "", 1, 0, 255 );
         //:FOR EACH mStudenC.ClassRoomSession
         RESULT = SetCursorFirstEntity( mStudenC, "ClassRoomSession", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mStudenC.ClassRoomSession.MeetingDate = ""
            if ( CompareAttributeToString( mStudenC, "ClassRoomSession", "MeetingDate", "" ) == 0 )
            { 
               //:GetStringFromAttributeByContext( szMeetingDay,
               //:                                 mStudenC, "ClassRoomSession", "MeetingDay", "", 10 )
               {StringBuilder sb_szMeetingDay;
               if ( szMeetingDay == null )
                  sb_szMeetingDay = new StringBuilder( 32 );
               else
                  sb_szMeetingDay = new StringBuilder( szMeetingDay );
                               GetStringFromAttributeByContext( sb_szMeetingDay, mStudenC, "ClassRoomSession", "MeetingDay", "", 10 );
               szMeetingDay = sb_szMeetingDay.toString( );}
               //:ELSE
            } 
            else
            { 
               //:GetStringFromAttributeByContext( szMeetingDay,
               //:                                 mStudenC, "ClassRoomSession", "MeetingDate", "", 10 )
               {StringBuilder sb_szMeetingDay;
               if ( szMeetingDay == null )
                  sb_szMeetingDay = new StringBuilder( 32 );
               else
                  sb_szMeetingDay = new StringBuilder( szMeetingDay );
                               GetStringFromAttributeByContext( sb_szMeetingDay, mStudenC, "ClassRoomSession", "MeetingDate", "", 10 );
               szMeetingDay = sb_szMeetingDay.toString( );}
            } 

            //:END
            //:GetStringFromAttributeByContext( szMeetingStartTime,
            //:                                 mStudenC, "ClassRoomSession", "StartTime", "", 10 )
            {StringBuilder sb_szMeetingStartTime;
            if ( szMeetingStartTime == null )
               sb_szMeetingStartTime = new StringBuilder( 32 );
            else
               sb_szMeetingStartTime = new StringBuilder( szMeetingStartTime );
                         GetStringFromAttributeByContext( sb_szMeetingStartTime, mStudenC, "ClassRoomSession", "StartTime", "", 10 );
            szMeetingStartTime = sb_szMeetingStartTime.toString( );}
            //:GetStringFromAttributeByContext( szMeetingEndTime,
            //:                                 mStudenC, "ClassRoomSession", "EndTime", "", 10 )
            {StringBuilder sb_szMeetingEndTime;
            if ( szMeetingEndTime == null )
               sb_szMeetingEndTime = new StringBuilder( 32 );
            else
               sb_szMeetingEndTime = new StringBuilder( szMeetingEndTime );
                         GetStringFromAttributeByContext( sb_szMeetingEndTime, mStudenC, "ClassRoomSession", "EndTime", "", 10 );
            szMeetingEndTime = sb_szMeetingEndTime.toString( );}
            //:szMeetingSchedule = szMeetingSchedule + szMeetingDay + ":" +
            //:                    szMeetingStartTime  + "-" + szMeetingEndTime + " "
            ZeidonStringConcat( szMeetingSchedule, 1, 0, szMeetingDay, 1, 0, 255 );
            ZeidonStringConcat( szMeetingSchedule, 1, 0, ":", 1, 0, 255 );
            ZeidonStringConcat( szMeetingSchedule, 1, 0, szMeetingStartTime, 1, 0, 255 );
            ZeidonStringConcat( szMeetingSchedule, 1, 0, "-", 1, 0, 255 );
            ZeidonStringConcat( szMeetingSchedule, 1, 0, szMeetingEndTime, 1, 0, 255 );
            ZeidonStringConcat( szMeetingSchedule, 1, 0, " ", 1, 0, 255 );
            RESULT = SetCursorNextEntity( mStudenC, "ClassRoomSession", "" );
         } 

         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:IF mStudenC.US_ClassRoomStandardSchedule EXISTS
      lTempInteger_2 = CheckExistenceOfEntity( mStudenC, "US_ClassRoomStandardSchedule" );
      if ( lTempInteger_2 == 0 )
      { 
         //:szMeetingSchedule = mStudenC.US_ClassRoomStandardSchedule.Title 
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                   GetVariableFromAttribute( szMeetingSchedule, mi_lTempInteger_3, 'S', 255, mStudenC, "US_ClassRoomStandardSchedule", "Title", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );}
         //:ELSE
      } 
      else
      { 
         //:szMeetingSchedule = ""
         ZeidonStringCopy( szMeetingSchedule, 1, 0, "", 1, 0, 255 );
         //:FOR EACH mStudenC.US_ClassRoomSession 
         RESULT = SetCursorFirstEntity( mStudenC, "US_ClassRoomSession", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mStudenC.US_ClassRoomSession.MeetingDate = ""
            if ( CompareAttributeToString( mStudenC, "US_ClassRoomSession", "MeetingDate", "" ) == 0 )
            { 
               //:GetStringFromAttributeByContext( szMeetingDay,
               //:                                 mStudenC, "US_ClassRoomSession", "MeetingDay", "", 10 )
               {StringBuilder sb_szMeetingDay;
               if ( szMeetingDay == null )
                  sb_szMeetingDay = new StringBuilder( 32 );
               else
                  sb_szMeetingDay = new StringBuilder( szMeetingDay );
                               GetStringFromAttributeByContext( sb_szMeetingDay, mStudenC, "US_ClassRoomSession", "MeetingDay", "", 10 );
               szMeetingDay = sb_szMeetingDay.toString( );}
               //:ELSE
            } 
            else
            { 
               //:GetStringFromAttributeByContext( szMeetingDay,
               //:                                 mStudenC, "US_ClassRoomSession", "MeetingDate", "", 10 )
               {StringBuilder sb_szMeetingDay;
               if ( szMeetingDay == null )
                  sb_szMeetingDay = new StringBuilder( 32 );
               else
                  sb_szMeetingDay = new StringBuilder( szMeetingDay );
                               GetStringFromAttributeByContext( sb_szMeetingDay, mStudenC, "US_ClassRoomSession", "MeetingDate", "", 10 );
               szMeetingDay = sb_szMeetingDay.toString( );}
            } 

            //:END
            //:GetStringFromAttributeByContext( szMeetingStartTime,
            //:                                 mStudenC, "US_ClassRoomSession", "StartTime", "", 10 )
            {StringBuilder sb_szMeetingStartTime;
            if ( szMeetingStartTime == null )
               sb_szMeetingStartTime = new StringBuilder( 32 );
            else
               sb_szMeetingStartTime = new StringBuilder( szMeetingStartTime );
                         GetStringFromAttributeByContext( sb_szMeetingStartTime, mStudenC, "US_ClassRoomSession", "StartTime", "", 10 );
            szMeetingStartTime = sb_szMeetingStartTime.toString( );}
            //:GetStringFromAttributeByContext( szMeetingEndTime,
            //:                                 mStudenC, "US_ClassRoomSession", "EndTime", "", 10 )
            {StringBuilder sb_szMeetingEndTime;
            if ( szMeetingEndTime == null )
               sb_szMeetingEndTime = new StringBuilder( 32 );
            else
               sb_szMeetingEndTime = new StringBuilder( szMeetingEndTime );
                         GetStringFromAttributeByContext( sb_szMeetingEndTime, mStudenC, "US_ClassRoomSession", "EndTime", "", 10 );
            szMeetingEndTime = sb_szMeetingEndTime.toString( );}
            //:szMeetingSchedule = szMeetingSchedule + szMeetingDay + ":" +
            //:                    szMeetingStartTime  + "-" + szMeetingEndTime + " "
            ZeidonStringConcat( szMeetingSchedule, 1, 0, szMeetingDay, 1, 0, 255 );
            ZeidonStringConcat( szMeetingSchedule, 1, 0, ":", 1, 0, 255 );
            ZeidonStringConcat( szMeetingSchedule, 1, 0, szMeetingStartTime, 1, 0, 255 );
            ZeidonStringConcat( szMeetingSchedule, 1, 0, "-", 1, 0, 255 );
            ZeidonStringConcat( szMeetingSchedule, 1, 0, szMeetingEndTime, 1, 0, 255 );
            ZeidonStringConcat( szMeetingSchedule, 1, 0, " ", 1, 0, 255 );
            RESULT = SetCursorNextEntity( mStudenC, "US_ClassRoomSession", "" );
         } 

         //:END
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:OBJECT CONSTRAINT OPERATION
//:ObjectConstraints( VIEW mStudenC BASED ON LOD mStudenC,
//:                   SHORT Event,
//:                   SHORT State )
//:   
//:   VIEW mStudenCT BASED ON LOD  mStudenC
public int 
omStudenC_ObjectConstraints( View     mStudenC,
                             Integer   Event,
                             Integer   State )
{
   zVIEW    mStudenCT = new zVIEW( );
   //:VIEW mStudent  BASED ON LOD  mStudent
   zVIEW    mStudent = new zVIEW( );
   //:VIEW lTermLST  BASED ON LOD  mTerm
   zVIEW    lTermLST = new zVIEW( );
   //:VIEW lTermLST2 BASED ON LOD  mTerm
   zVIEW    lTermLST2 = new zVIEW( );
   //:VIEW wXferO    REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL EarlierGrade
   double  EarlierGrade = 0.0;
   //:DECIMAL LaterGrade
   double  LaterGrade = 0.0;
   //:STRING ( 20 ) szDecimalString
   String   szDecimalString = null;
   //:STRING ( 20 ) szEntryTerm
   String   szEntryTerm = null;
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
   String   szTempString_0 = null;
   int      lTempInteger_12 = 0;
   int      lTempInteger_13 = 0;
   int      lTempInteger_14 = 0;
   int      lTempInteger_15 = 0;
   int      lTempInteger_16 = 0;
   int      lTempInteger_17 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mStudenC, zLEVEL_TASK );

   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:// Set Course Number and Course Title in each Registration entry, as well as YearSemester, YearID and TermID.
         //:FOR EACH mStudenC.Registration 
         RESULT = SetCursorFirstEntity( mStudenC, "Registration", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mStudenC.RegistrationCourse EXISTS
            lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "RegistrationCourse" );
            if ( lTempInteger_0 == 0 )
            { 
               //:IF mStudenC.CrossListedCourse EXISTS
               lTempInteger_1 = CheckExistenceOfEntity( mStudenC, "CrossListedCourse" );
               if ( lTempInteger_1 == 0 )
               { 
                  //:mStudenC.Registration.wCourseNumber = mStudenC.CrossListedCourse.Number
                  SetAttributeFromAttribute( mStudenC, "Registration", "wCourseNumber", mStudenC, "CrossListedCourse", "Number" );
                  //:mStudenC.Registration.wCourseTitle  = mStudenC.CrossListedCourse.Title
                  SetAttributeFromAttribute( mStudenC, "Registration", "wCourseTitle", mStudenC, "CrossListedCourse", "Title" );
                  //:ELSE
               } 
               else
               { 
                  //:mStudenC.Registration.wCourseNumber = mStudenC.RegistrationCourse.Number
                  SetAttributeFromAttribute( mStudenC, "Registration", "wCourseNumber", mStudenC, "RegistrationCourse", "Number" );
                  //:mStudenC.Registration.wCourseTitle  = mStudenC.RegistrationCourse.Title
                  SetAttributeFromAttribute( mStudenC, "Registration", "wCourseTitle", mStudenC, "RegistrationCourse", "Title" );
               } 

               //:END
               //:// Override Title if a CourseTopic exists.
               //:IF mStudenC.CourseTopic EXISTS
               lTempInteger_2 = CheckExistenceOfEntity( mStudenC, "CourseTopic" );
               if ( lTempInteger_2 == 0 )
               { 
                  //:mStudenC.Registration.wCourseTitle  = mStudenC.CourseTopic.Title
                  SetAttributeFromAttribute( mStudenC, "Registration", "wCourseTitle", mStudenC, "CourseTopic", "Title" );
               } 

               //:END
               //:mStudenC.Registration.wRepeatableForCreditFlag = mStudenC.RegistrationCourse.RepeatableForCreditFlag
               SetAttributeFromAttribute( mStudenC, "Registration", "wRepeatableForCreditFlag", mStudenC, "RegistrationCourse", "RepeatableForCreditFlag" );
               //:ELSE
            } 
            else
            { 
               //:IF mStudenC.EquivalentCourse EXISTS
               lTempInteger_3 = CheckExistenceOfEntity( mStudenC, "EquivalentCourse" );
               if ( lTempInteger_3 == 0 )
               { 
                  //:mStudenC.Registration.wCourseNumber            = mStudenC.EquivalentCourse.Number 
                  SetAttributeFromAttribute( mStudenC, "Registration", "wCourseNumber", mStudenC, "EquivalentCourse", "Number" );
                  //:mStudenC.Registration.wRepeatableForCreditFlag = mStudenC.EquivalentCourse.RepeatableForCreditFlag
                  SetAttributeFromAttribute( mStudenC, "Registration", "wRepeatableForCreditFlag", mStudenC, "EquivalentCourse", "RepeatableForCreditFlag" );
               } 

               //:END
            } 

            //:END
            //:IF mStudenC.RegisteredCollegeTerm EXISTS
            lTempInteger_4 = CheckExistenceOfEntity( mStudenC, "RegisteredCollegeTerm" );
            if ( lTempInteger_4 == 0 )
            { 
               //:mStudenC.Registration.wCollegeYearSemester = mStudenC.RegisteredCollegeTerm.YearSemester 
               SetAttributeFromAttribute( mStudenC, "Registration", "wCollegeYearSemester", mStudenC, "RegisteredCollegeTerm", "YearSemester" );
               //:mStudenC.Registration.wCollegeTermID       = mStudenC.RegisteredCollegeTerm.ID
               SetAttributeFromAttribute( mStudenC, "Registration", "wCollegeTermID", mStudenC, "RegisteredCollegeTerm", "ID" );
               //:mStudenC.Registration.wCollegeYearID       = mStudenC.RegisteredCollegeYear.ID 
               SetAttributeFromAttribute( mStudenC, "Registration", "wCollegeYearID", mStudenC, "RegisteredCollegeYear", "ID" );
               //:ELSE
            } 
            else
            { 
               //:IF mStudenC.RegistrationClassCollegeTerm EXISTS
               lTempInteger_5 = CheckExistenceOfEntity( mStudenC, "RegistrationClassCollegeTerm" );
               if ( lTempInteger_5 == 0 )
               { 
                  //:mStudenC.Registration.wCollegeYearSemester = mStudenC.RegistrationClassCollegeTerm.YearSemester 
                  SetAttributeFromAttribute( mStudenC, "Registration", "wCollegeYearSemester", mStudenC, "RegistrationClassCollegeTerm", "YearSemester" );
                  //:mStudenC.Registration.wCollegeTermID       = mStudenC.RegistrationClassCollegeTerm.ID
                  SetAttributeFromAttribute( mStudenC, "Registration", "wCollegeTermID", mStudenC, "RegistrationClassCollegeTerm", "ID" );
                  //:mStudenC.Registration.wCollegeYearID       = mStudenC.RegistrationClassCollegeYear.ID 
                  SetAttributeFromAttribute( mStudenC, "Registration", "wCollegeYearID", mStudenC, "RegistrationClassCollegeYear", "ID" );
               } 

               //:END
            } 

            RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
            //:END
         } 

         //:END

         //:// Set Course Number and Course Title in each Waitlisted entry.
         //:FOR EACH mStudenC.StudentWaitlisted 
         RESULT = SetCursorFirstEntity( mStudenC, "StudentWaitlisted", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mStudenC.StudentWaitlistedXListedCourse EXISTS
            lTempInteger_6 = CheckExistenceOfEntity( mStudenC, "StudentWaitlistedXListedCourse" );
            if ( lTempInteger_6 == 0 )
            { 
               //:mStudenC.StudentWaitlisted.wCourseNumber = mStudenC.StudentWaitlistedXListedCourse.Number 
               SetAttributeFromAttribute( mStudenC, "StudentWaitlisted", "wCourseNumber", mStudenC, "StudentWaitlistedXListedCourse", "Number" );
               //:mStudenC.StudentWaitlisted.wCourseTitle  = mStudenC.StudentWaitlistedXListedCourse.Title
               SetAttributeFromAttribute( mStudenC, "StudentWaitlisted", "wCourseTitle", mStudenC, "StudentWaitlistedXListedCourse", "Title" );
               //:ELSE
            } 
            else
            { 
               //:mStudenC.StudentWaitlisted.wCourseNumber = mStudenC.StudentWaitlistedCourse.Number 
               SetAttributeFromAttribute( mStudenC, "StudentWaitlisted", "wCourseNumber", mStudenC, "StudentWaitlistedCourse", "Number" );
               //:mStudenC.StudentWaitlisted.wCourseTitle  = mStudenC.StudentWaitlistedCourse.Title 
               SetAttributeFromAttribute( mStudenC, "StudentWaitlisted", "wCourseTitle", mStudenC, "StudentWaitlistedCourse", "Title" );
            } 

            //:END 
            //:// Override Title if a CourseTopic exists.
            //:IF mStudenC.StudentWaitlistedCourseTopic EXISTS
            lTempInteger_7 = CheckExistenceOfEntity( mStudenC, "StudentWaitlistedCourseTopic" );
            if ( lTempInteger_7 == 0 )
            { 
               //:mStudenC.StudentWaitlisted.wCourseTitle  = mStudenC.StudentWaitlistedCourseTopic.Title
               SetAttributeFromAttribute( mStudenC, "StudentWaitlisted", "wCourseTitle", mStudenC, "StudentWaitlistedCourseTopic", "Title" );
            } 

            RESULT = SetCursorNextEntity( mStudenC, "StudentWaitlisted", "" );
            //:END
         } 

         //:END

         //:// Order the Enrollment records by Term and Course.
         //:OrderEntityForView( mStudenC, "Registration", "wCollegeYearSemester A wCourseNumber A" )
         OrderEntityForView( mStudenC, "Registration", "wCollegeYearSemester A wCourseNumber A" );

         //:// Order the Saved Degree Audits by date.
         //:OrderEntityForView( mStudenC, "SavedDegreeAudit", "CreatedDateTime D" )
         OrderEntityForView( mStudenC, "SavedDegreeAudit", "CreatedDateTime D" );

         //:// Flag any duplicate Course entries that have a lower grade and mark them as "Repeated", unless the entries are either
         //:// a manual repeat of a repeatable class or a manual repeat override. In the latter case, mark the entries accordingly.
         //:// Also mark any duplicate Course entries where a Student is anticipating repeating a Course.
         //:FOR EACH mStudenC.Registration
         //:WHERE mStudenC.Registration.Status = "C"    // Completed
         //:OR mStudenC.Registration.Status = "T"    // Currently Taking
         //://OR mStudenC.Registration.Status = "V"    // Waived
         //:OR mStudenC.Registration.Status = "F"    // Transferred
         //:OR mStudenC.Registration.Status = "X"    // L. Transferred
         //://OR mStudenC.Registration.Status = "Y"    // L. Waived
         //:OR mStudenC.Registration.Status = "R"    // Pre-enrolled
         RESULT = SetCursorFirstEntity( mStudenC, "Registration", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( mStudenC, "Registration", "Status", "C" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "F" ) == 0 ||
                 CompareAttributeToString( mStudenC, "Registration", "Status", "X" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "R" ) == 0 )
            { 

               //:IF mStudenC.RegistrationClass EXISTS OR mStudenC.EquivalentCourse EXISTS 
               lTempInteger_8 = CheckExistenceOfEntity( mStudenC, "RegistrationClass" );
               lTempInteger_9 = CheckExistenceOfEntity( mStudenC, "EquivalentCourse" );
               if ( lTempInteger_8 == 0 || lTempInteger_9 == 0 )
               { 
                  //:IF mStudenC.Registration.ManuallyRepeatingRegistrationID != ""
                  if ( CompareAttributeToString( mStudenC, "Registration", "ManuallyRepeatingRegistrationID", "" ) != 0 )
                  { 
                     //:mStudenC.Registration.wRepeatedClass = "R"
                     SetAttributeFromString( mStudenC, "Registration", "wRepeatedClass", "R" );
                     //:ELSE
                  } 
                  else
                  { 

                     //:IF mStudenC.Registration.wRepeatableForCreditFlag != "Y"
                     if ( CompareAttributeToString( mStudenC, "Registration", "wRepeatableForCreditFlag", "Y" ) != 0 )
                     { 
                        //:CreateViewFromView( mStudenCT, mStudenC )
                        CreateViewFromView( mStudenCT, mStudenC );
                        //:SET CURSOR FIRST mStudenCT.Registration
                        //:  WHERE mStudenCT.Registration.wCourseNumber = mStudenC.Registration.wCourseNumber
                        //:    AND mStudenCT.Registration.ID           != mStudenC.Registration.ID 
                        //:    AND mStudenCT.Registration.Status       != "D"    // Dropped
                        //:    AND mStudenCT.Registration.Status       != "W"    // Withdrawn
                        //:    AND mStudenCT.Registration.Status       != "V"    // Waived
                        //:    AND mStudenCT.Registration.Status       != "Y"    // L. Waived
                        //:    AND mStudenCT.Registration.Status       != "N"    // Denied Transfer
                        //:    AND mStudenCT.Registration.wRepeatedClass = ""
                        RESULT = SetCursorFirstEntity( mStudenCT, "Registration", "" );
                        if ( RESULT > zCURSOR_UNCHANGED )
                        { 
                           while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mStudenCT, "Registration", "wCourseNumber", mStudenC, "Registration", "wCourseNumber" ) != 0 ||
                        CompareAttributeToAttribute( mStudenCT, "Registration", "ID", mStudenC, "Registration", "ID" ) == 0 || CompareAttributeToString( mStudenCT, "Registration", "Status", "D" ) == 0 ||
                        CompareAttributeToString( mStudenCT, "Registration", "Status", "W" ) == 0 || CompareAttributeToString( mStudenCT, "Registration", "Status", "V" ) == 0 ||
                        CompareAttributeToString( mStudenCT, "Registration", "Status", "Y" ) == 0 || CompareAttributeToString( mStudenCT, "Registration", "Status", "N" ) == 0 ||
                        CompareAttributeToString( mStudenCT, "Registration", "wRepeatedClass", "" ) != 0 ) )
                           { 
                              RESULT = SetCursorNextEntity( mStudenCT, "Registration", "" );
                           } 

                        } 

                        //:IF RESULT >= zCURSOR_SET
                        if ( RESULT >= zCURSOR_SET )
                        { 
                           //:GetStringFromAttributeByContext( szDecimalString,
                           //:                        mStudenC, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 )
                           {StringBuilder sb_szDecimalString;
                           if ( szDecimalString == null )
                              sb_szDecimalString = new StringBuilder( 32 );
                           else
                              sb_szDecimalString = new StringBuilder( szDecimalString );
                                                       GetStringFromAttributeByContext( sb_szDecimalString, mStudenC, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 );
                           szDecimalString = sb_szDecimalString.toString( );}
                           //:EarlierGrade = StrToDecimal( szDecimalString )
                           {
                            ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mStudenC );
                            EarlierGrade = m_ZGLOBAL1_Operation.StrToDecimal( szDecimalString );
                            // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                           }
                           //:GetStringFromAttributeByContext( szDecimalString,
                           //:                        mStudenCT, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 )
                           {StringBuilder sb_szDecimalString;
                           if ( szDecimalString == null )
                              sb_szDecimalString = new StringBuilder( 32 );
                           else
                              sb_szDecimalString = new StringBuilder( szDecimalString );
                                                       GetStringFromAttributeByContext( sb_szDecimalString, mStudenCT, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 );
                           szDecimalString = sb_szDecimalString.toString( );}
                           //:LaterGrade = StrToDecimal( szDecimalString )
                           {
                            ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mStudenC );
                            LaterGrade = m_ZGLOBAL1_Operation.StrToDecimal( szDecimalString );
                            // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                           }
                           //:// If one of the Classes is currently being taken, the other one will be marked "Attempted Repeated",
                           //:// regardless of grade.
                           //:IF (mStudenC.Registration.FinalGrade  = "" AND mStudenC.Registration.TakingClassType != "A") OR 
                           //:(mStudenCT.Registration.FinalGrade = "" AND mStudenCT.Registration.TakingClassType != "A")
                           if ( ( CompareAttributeToString( mStudenC, "Registration", "FinalGrade", "" ) == 0 && CompareAttributeToString( mStudenC, "Registration", "TakingClassType", "A" ) != 0 ) ||
                        ( CompareAttributeToString( mStudenCT, "Registration", "FinalGrade", "" ) == 0 && CompareAttributeToString( mStudenCT, "Registration", "TakingClassType", "A" ) != 0 ) )
                           { 

                              //:IF mStudenCT.Registration.FinalGrade = ""
                              if ( CompareAttributeToString( mStudenCT, "Registration", "FinalGrade", "" ) == 0 )
                              { 
                                 //:mStudenC.Registration.wRepeatedClass = "AR"
                                 SetAttributeFromString( mStudenC, "Registration", "wRepeatedClass", "AR" );
                                 //:ELSE
                              } 
                              else
                              { 
                                 //:mStudenCT.Registration.wRepeatedClass = "AR"
                                 SetAttributeFromString( mStudenCT, "Registration", "wRepeatedClass", "AR" );
                              } 

                              //:END
                              //:ELSE
                           } 
                           else
                           { 
                              //:IF LaterGrade = EarlierGrade
                              if ( LaterGrade == EarlierGrade )
                              { 
                                 //:// If both grades are equal, we will set the earlier one to "R", making sure both aren't set.
                                 //:// It is important that is the earlier one because this covers the situation where a Class with grade "U" is
                                 //:// repeated by a Class with grade "S". Both have a DegreeTrackGradePointValue of zero.
                                 //:IF mStudenC.Registration.wRepeatedClass = "" AND mStudenCT.Registration.wRepeatedClass = ""
                                 if ( CompareAttributeToString( mStudenC, "Registration", "wRepeatedClass", "" ) == 0 && CompareAttributeToString( mStudenCT, "Registration", "wRepeatedClass", "" ) == 0 )
                                 { 
                                    //:mStudenC.Registration.wRepeatedClass = "R"
                                    SetAttributeFromString( mStudenC, "Registration", "wRepeatedClass", "R" );
                                 } 

                                 //:END
                                 //:ELSE
                              } 
                              else
                              { 
                                 //:// If one grade is greater, set the lower grade entry to "R".
                                 //:IF LaterGrade > EarlierGrade
                                 if ( LaterGrade > EarlierGrade )
                                 { 
                                    //:mStudenC.Registration.wRepeatedClass = "R"
                                    SetAttributeFromString( mStudenC, "Registration", "wRepeatedClass", "R" );
                                    //:ELSE
                                 } 
                                 else
                                 { 
                                    //:mStudenCT.Registration.wRepeatedClass = "R"
                                    SetAttributeFromString( mStudenCT, "Registration", "wRepeatedClass", "R" );
                                 } 

                                 //:END
                              } 

                              //:END
                           } 

                           //:END
                        } 

                        //:END
                        //:DropView( mStudenCT )
                        DropView( mStudenCT );
                     } 

                     //:END
                  } 

                  //:END
               } 

            } 

            RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
            //:END
         } 

         //:END

         //:// Create the Schedule derived path for the College Year of the current College Term, if one has been defined.
         //:// This is set in wXferO, so that it is held accross multiple students.
         //:IF wXferO.StudentScheduleCollegeYear DOES NOT EXIST 
         lTempInteger_10 = CheckExistenceOfEntity( wXferO, "StudentScheduleCollegeYear" );
         if ( lTempInteger_10 != 0 )
         { 
            //:GET VIEW lTermLST NAMED "lTermLST"
            RESULT = GetViewByName( lTermLST, "lTermLST", mStudenC, zLEVEL_TASK );
            //:IF RESULT >= 0
            if ( RESULT >= 0 )
            { 
               //:CreateViewFromView( lTermLST2, lTermLST )
               CreateViewFromView( lTermLST2, lTermLST );
               //:SET CURSOR FIRST lTermLST2.CollegeTerm WHERE lTermLST2.CollegeTerm.CurrentTermFlag = "Y"
               RESULT = SetCursorFirstEntityByString( lTermLST2, "CollegeTerm", "CurrentTermFlag", "Y", "" );
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //://INCLUDE wXferO.StudentScheduleCollegeYear FROM lTermLST2.CollegeYear 
                  //:// Don C - 11/24/04 - The include above was causing a core bit map error on closing the system.
                  //:CREATE ENTITY wXferO.StudentScheduleCollegeYear
                  RESULT = CreateEntity( wXferO, "StudentScheduleCollegeYear", zPOS_AFTER );
                  //:SetMatchingAttributesByName( wXferO,    "StudentScheduleCollegeYear",
                  //:                       lTermLST2, "CollegeYear", zSET_ALL )
                  SetMatchingAttributesByName( wXferO, "StudentScheduleCollegeYear", lTermLST2, "CollegeYear", zSET_ALL );
               } 

               //:END
               //:DropView( lTermLST2 )
               DropView( lTermLST2 );
            } 

            //:END
         } 

         //:END

         //:// Create the derived current schedule path (CurrentScheduleEntry) for the current year.
         //:BuildCurrentSchedule( mStudenC )
         omStudenC_BuildCurrentSchedule( mStudenC );
         //:// Remove any dropped entries.
         //:FOR EACH mStudenC.CurrentScheduleEntry 
         RESULT = SetCursorFirstEntity( mStudenC, "CurrentScheduleEntry", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mStudenC.CurrentClass EXISTS
            lTempInteger_11 = CheckExistenceOfEntity( mStudenC, "CurrentClass" );
            if ( lTempInteger_11 == 0 )
            { 
               //:IF mStudenC.CurrentClass.wStatus = "Dropped"
               if ( CompareAttributeToString( mStudenC, "CurrentClass", "wStatus", "Dropped" ) == 0 )
               { 
                  //:DELETE ENTITY mStudenC.CurrentScheduleEntry NONE 
                  RESULT = DeleteEntity( mStudenC, "CurrentScheduleEntry", zREPOS_NONE );
               } 

               //:END 
            } 

            RESULT = SetCursorNextEntity( mStudenC, "CurrentScheduleEntry", "" );
            //:END
         } 

         //:END

         //:// Set up derived path of AcademicStanding entries and order both.
         //:OrderEntityForView( mStudenC, "AcademicStanding", "dDisplayYearTerm A AcademicStanding.CreateDateTime A " )
         OrderEntityForView( mStudenC, "AcademicStanding", "dDisplayYearTerm A AcademicStanding.CreateDateTime A " );
         //:FOR EACH mStudenC.AcademicStanding 
         RESULT = SetCursorFirstEntity( mStudenC, "AcademicStanding", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:INCLUDE mStudenC.DisplayAcademicStanding FROM mStudenC.AcademicStanding
            RESULT = IncludeSubobjectFromSubobject( mStudenC, "DisplayAcademicStanding", mStudenC, "AcademicStanding", zPOS_AFTER );
            RESULT = SetCursorNextEntity( mStudenC, "AcademicStanding", "" );
         } 

         //:END
         //:OrderEntityForView( mStudenC, "DisplayAcademicStanding", "DisplayAcademicStandingTerm.YearSemester D" )
         OrderEntityForView( mStudenC, "DisplayAcademicStanding", "DisplayAcademicStandingTerm.YearSemester D" );
         //:SET CURSOR FIRST mStudenC.DisplayAcademicStanding 
         RESULT = SetCursorFirstEntity( mStudenC, "DisplayAcademicStanding", "" );

         //:// Go to generate the HS Required Groups derived subobjects for display purposes.
         //:GET VIEW mStudent NAMED "mStudent"
         RESULT = GetViewByName( mStudent, "mStudent", mStudenC, zLEVEL_TASK );
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 
            //:GenerateHS_ReqGroups( mStudenC, mStudent.Student.TestingStatus )
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, mStudent, "Student", "TestingStatus" );
            szTempString_0 = sb_szTempString_0.toString( );}
            omStudenC_GenerateHS_ReqGroups( mStudenC, szTempString_0 );
         } 

         //:END

         //:// Set up the Enrollment Display entries.
         //:mStudenC.Student.wShowHideStatus = "S"
         SetAttributeFromString( mStudenC, "Student", "wShowHideStatus", "S" );
         //:FOR EACH mStudenC.Registration 
         RESULT = SetCursorFirstEntity( mStudenC, "Registration", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY mStudenC.DisplayEnrollment
            RESULT = CreateEntity( mStudenC, "DisplayEnrollment", zPOS_AFTER );
            //:mStudenC.DisplayEnrollment.RegistrationID   = mStudenC.Registration.ID 
            SetAttributeFromAttribute( mStudenC, "DisplayEnrollment", "RegistrationID", mStudenC, "Registration", "ID" );
            //:mStudenC.DisplayEnrollment.Status           = mStudenC.Registration.Status 
            SetAttributeFromAttribute( mStudenC, "DisplayEnrollment", "Status", mStudenC, "Registration", "Status" );
            //:mStudenC.DisplayEnrollment.TermCohort       = mStudenC.Registration.dCollegeTerm 
            SetAttributeFromAttribute( mStudenC, "DisplayEnrollment", "TermCohort", mStudenC, "Registration", "dCollegeTerm" );
            //:mStudenC.DisplayEnrollment.CourseNumber     = mStudenC.Registration.dClassNumber 
            SetAttributeFromAttribute( mStudenC, "DisplayEnrollment", "CourseNumber", mStudenC, "Registration", "dClassNumber" );
            //:mStudenC.DisplayEnrollment.CourseTitle      = mStudenC.Registration.dEnrollmentCourseTitle 
            SetAttributeFromAttribute( mStudenC, "DisplayEnrollment", "CourseTitle", mStudenC, "Registration", "dEnrollmentCourseTitle" );
            //:mStudenC.DisplayEnrollment.TakingClassType  = mStudenC.Registration.TakingClassType 
            SetAttributeFromAttribute( mStudenC, "DisplayEnrollment", "TakingClassType", mStudenC, "Registration", "TakingClassType" );
            //:mStudenC.DisplayEnrollment.CreditHours      = mStudenC.Registration.CreditHours 
            SetAttributeFromAttribute( mStudenC, "DisplayEnrollment", "CreditHours", mStudenC, "Registration", "CreditHours" );
            //:mStudenC.DisplayEnrollment.FinalGrade       = mStudenC.Registration.FinalGrade
            SetAttributeFromAttribute( mStudenC, "DisplayEnrollment", "FinalGrade", mStudenC, "Registration", "FinalGrade" );
            //:mStudenC.DisplayEnrollment.DroppedDate      = mStudenC.Registration.DroppedDate
            SetAttributeFromAttribute( mStudenC, "DisplayEnrollment", "DroppedDate", mStudenC, "Registration", "DroppedDate" );
            //:IF mStudenC.RegistrationClass EXISTS
            lTempInteger_12 = CheckExistenceOfEntity( mStudenC, "RegistrationClass" );
            if ( lTempInteger_12 == 0 )
            { 
               //:mStudenC.DisplayEnrollment.ClassStartDate   = mStudenC.RegistrationClass.ClassStartDate 
               SetAttributeFromAttribute( mStudenC, "DisplayEnrollment", "ClassStartDate", mStudenC, "RegistrationClass", "ClassStartDate" );
               //:mStudenC.DisplayEnrollment.ClassEndDate     = mStudenC.RegistrationClass.ClassEndDate 
               SetAttributeFromAttribute( mStudenC, "DisplayEnrollment", "ClassEndDate", mStudenC, "RegistrationClass", "ClassEndDate" );
               //:mStudenC.DisplayEnrollment.ClassCreditHours = mStudenC.RegistrationClass.CreditHours 
               SetAttributeFromAttribute( mStudenC, "DisplayEnrollment", "ClassCreditHours", mStudenC, "RegistrationClass", "CreditHours" );
            } 

            //:END
            //:IF mStudenC.FinAidAwardPeriod EXISTS
            lTempInteger_13 = CheckExistenceOfEntity( mStudenC, "FinAidAwardPeriod" );
            if ( lTempInteger_13 == 0 )
            { 
               //:mStudenC.DisplayEnrollment.PeriodDesignator = mStudenC.FinAidAwardPeriod.PeriodDesignator 
               SetAttributeFromAttribute( mStudenC, "DisplayEnrollment", "PeriodDesignator", mStudenC, "FinAidAwardPeriod", "PeriodDesignator" );
            } 

            //:END
            //:IF mStudenC.RegistrationCourse EXISTS
            lTempInteger_14 = CheckExistenceOfEntity( mStudenC, "RegistrationCourse" );
            if ( lTempInteger_14 == 0 )
            { 
               //:mStudenC.DisplayEnrollment.DevelopmentalFlag = mStudenC.RegistrationCourse.DevelopmentalFlag 
               SetAttributeFromAttribute( mStudenC, "DisplayEnrollment", "DevelopmentalFlag", mStudenC, "RegistrationCourse", "DevelopmentalFlag" );
            } 

            RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
            //:END
         } 

         //:END

         //:// Order Enrollment Modification entries by date/time, with latest first.
         //:OrderEntityForView( mStudenC, "EnrollmentModification", "ModifiedDateTime D" )
         OrderEntityForView( mStudenC, "EnrollmentModification", "ModifiedDateTime D" );

         //:// Order Transcript Custom Statements by Term.
         //:OrderEntityForView( mStudenC, "CustomTransciptTerm", "CustomTransciptCollegeYear.Year A CustomTransciptCollegeTerm.Semester  A" )
         OrderEntityForView( mStudenC, "CustomTransciptTerm", "CustomTransciptCollegeYear.Year A CustomTransciptCollegeTerm.Semester  A" );

         //:SET CURSOR FIRST mStudenC.Registration
         RESULT = SetCursorFirstEntity( mStudenC, "Registration", "" );
         break ;


      //:  /* end zOCE_ACTIVATE */
      //:OF   zOCE_ACTIVATE_EMPTY:
      case zOCE_ACTIVATE_EMPTY :
         break ;

      //:  /* end zOCE_ACTIVATE_EMPTY */
      //:OF   zOCE_COMMIT:
      case zOCE_COMMIT :

         //:// Create the Change Log entry.
         //:// We need to change the following with a special academic record.
         //:IF mStudenC.Student EXISTS
         lTempInteger_15 = CheckExistenceOfEntity( mStudenC, "Student" );
         if ( lTempInteger_15 == 0 )
         { 
            //:GenerateTransactionEntryWDesc( mStudenC, "Academic Data" )
            {
             ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mStudenC );
             m_ZGLOBAL2_Operation.GenerateTransactionEntryWDesc( mStudenC, "Academic Data" );
             // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
            }
         } 

         //:END

         //:// Make sure that Student.EntryYearMonth is set.
         //:IF mStudenC.Student.EntryYearMonth = "" AND
         //:mStudenC.Registration EXISTS
         lTempInteger_16 = CheckExistenceOfEntity( mStudenC, "Registration" );
         if ( CompareAttributeToString( mStudenC, "Student", "EntryYearMonth", "" ) == 0 && lTempInteger_16 == 0 )
         { 

            //:szEntryTerm = ""
             {StringBuilder sb_szEntryTerm;
            if ( szEntryTerm == null )
               sb_szEntryTerm = new StringBuilder( 32 );
            else
               sb_szEntryTerm = new StringBuilder( szEntryTerm );
                        ZeidonStringCopy( sb_szEntryTerm, 1, 0, "", 1, 0, 21 );
            szEntryTerm = sb_szEntryTerm.toString( );}
            //:CreateViewFromView( mStudenCT, mStudenC )
            CreateViewFromView( mStudenCT, mStudenC );
            //:FOR EACH mStudenCT.Registration 
            RESULT = SetCursorFirstEntity( mStudenCT, "Registration", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF szEntryTerm = "" OR
               //:szEntryTerm > mStudenCT.Registration.dCollegeTerm 
               if ( ZeidonStringCompare( szEntryTerm, 1, 0, "", 1, 0, 21 ) == 0 || CompareAttributeToString( mStudenCT, "Registration", "dCollegeTerm", szEntryTerm ) < 0 )
               { 

                  //:szEntryTerm = mStudenCT.Registration.dCollegeTerm 
                  {MutableInt mi_lTempInteger_17 = new MutableInt( lTempInteger_17 );
                  StringBuilder sb_szEntryTerm;
                  if ( szEntryTerm == null )
                     sb_szEntryTerm = new StringBuilder( 32 );
                  else
                     sb_szEntryTerm = new StringBuilder( szEntryTerm );
                                     GetVariableFromAttribute( sb_szEntryTerm, mi_lTempInteger_17, 'S', 21, mStudenCT, "Registration", "dCollegeTerm", "", 0 );
                  lTempInteger_17 = mi_lTempInteger_17.intValue( );
                  szEntryTerm = sb_szEntryTerm.toString( );}
               } 

               RESULT = SetCursorNextEntity( mStudenCT, "Registration", "" );
               //:END
            } 

            //:END
            //:DropView( mStudenCT )
            DropView( mStudenCT );
            //:GET VIEW lTermLST NAMED "lTermLST"
            RESULT = GetViewByName( lTermLST, "lTermLST", mStudenC, zLEVEL_TASK );
            //:IF RESULT >= 0
            if ( RESULT >= 0 )
            { 
               //:CreateViewFromView( lTermLST2, lTermLST )
               CreateViewFromView( lTermLST2, lTermLST );
               //:SET CURSOR FIRST lTermLST2.CollegeTerm WHERE lTermLST2.CollegeTerm.YearSemester = szEntryTerm
               RESULT = SetCursorFirstEntityByString( lTermLST2, "CollegeTerm", "YearSemester", szEntryTerm, "" );
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:mStudenC.Student.EntryYearMonth = lTermLST2.CollegeTerm.StudentEntryYearMonth 
                  SetAttributeFromAttribute( mStudenC, "Student", "EntryYearMonth", lTermLST2, "CollegeTerm", "StudentEntryYearMonth" );
               } 

               //:END
               //:DropView( lTermLST2 )
               DropView( lTermLST2 );
            } 

            //:END
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


//:DERIVED ATTRIBUTE OPERATION
//:dEnrollmentCourseName( VIEW mStudenC BASED ON LOD mStudenC,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   STRING ( 50 ) szCourseName
public int 
omStudenC_dEnrollmentCourseName( View     mStudenC,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   String   szCourseName = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// For internal classes, this is the Course Number plus the Section.
         //:// For transfer classes, this is the transfer course name.

         //:IF mStudenC.RegistrationCourse EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "RegistrationCourse" );
         if ( lTempInteger_0 == 0 )
         { 
            //:/*IF mStudenC.CourseTopic EXISTS
            //:szCourseName = mStudenC.Registration.dClassNumber + " / " + mStudenC.CourseTopic.Title 
            //:ELSE
            //:szCourseName = mStudenC.Registration.dClassNumber + " / " + mStudenC.RegistrationCourse.Title 
            //:END*/
            //:szCourseName = mStudenC.Registration.dClassNumber
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szCourseName;
            if ( szCourseName == null )
               sb_szCourseName = new StringBuilder( 32 );
            else
               sb_szCourseName = new StringBuilder( szCourseName );
                         GetVariableFromAttribute( sb_szCourseName, mi_lTempInteger_1, 'S', 51, mStudenC, "Registration", "dClassNumber", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szCourseName = sb_szCourseName.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mStudenC.EquivalentCourse EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mStudenC, "EquivalentCourse" );
            if ( lTempInteger_2 == 0 )
            { 
               //:IF mStudenC.Registration.ForeignClassName = ""
               if ( CompareAttributeToString( mStudenC, "Registration", "ForeignClassName", "" ) == 0 )
               { 
                  //:szCourseName = mStudenC.EquivalentCourse.Number
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szCourseName;
                  if ( szCourseName == null )
                     sb_szCourseName = new StringBuilder( 32 );
                  else
                     sb_szCourseName = new StringBuilder( szCourseName );
                                     GetVariableFromAttribute( sb_szCourseName, mi_lTempInteger_3, 'S', 51, mStudenC, "EquivalentCourse", "Number", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szCourseName = sb_szCourseName.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szCourseName = mStudenC.EquivalentCourse.Number + " / " + mStudenC.Registration.ForeignClassName
                  {StringBuilder sb_szCourseName;
                  if ( szCourseName == null )
                     sb_szCourseName = new StringBuilder( 32 );
                  else
                     sb_szCourseName = new StringBuilder( szCourseName );
                                     GetStringFromAttribute( sb_szCourseName, mStudenC, "EquivalentCourse", "Number" );
                  szCourseName = sb_szCourseName.toString( );}
                   {StringBuilder sb_szCourseName;
                  if ( szCourseName == null )
                     sb_szCourseName = new StringBuilder( 32 );
                  else
                     sb_szCourseName = new StringBuilder( szCourseName );
                                    ZeidonStringConcat( sb_szCourseName, 1, 0, " / ", 1, 0, 51 );
                  szCourseName = sb_szCourseName.toString( );}
                  {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                  StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                     GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_4, 'S', 101, mStudenC, "Registration", "ForeignClassName", "", 0 );
                  lTempInteger_4 = mi_lTempInteger_4.intValue( );
                  szTempString_0 = sb_szTempString_0.toString( );}
                   {StringBuilder sb_szCourseName;
                  if ( szCourseName == null )
                     sb_szCourseName = new StringBuilder( 32 );
                  else
                     sb_szCourseName = new StringBuilder( szCourseName );
                                    ZeidonStringConcat( sb_szCourseName, 1, 0, szTempString_0, 1, 0, 51 );
                  szCourseName = sb_szCourseName.toString( );}
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:szCourseName = mStudenC.Registration.ForeignClassName
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szCourseName;
               if ( szCourseName == null )
                  sb_szCourseName = new StringBuilder( 32 );
               else
                  sb_szCourseName = new StringBuilder( szCourseName );
                               GetVariableFromAttribute( sb_szCourseName, mi_lTempInteger_5, 'S', 51, mStudenC, "Registration", "ForeignClassName", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szCourseName = sb_szCourseName.toString( );}
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mStudenC,
         //:                InternalEntityStructure, InternalAttribStructure, szCourseName )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szCourseName );
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
//:dEnrollmentCourseTitle( VIEW mStudenC BASED ON LOD mStudenC,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   STRING ( 50 ) szCourseTitle
public int 
omStudenC_dEnrollmentCourseTitle( View     mStudenC,
                                  String InternalEntityStructure,
                                  String InternalAttribStructure,
                                  Integer   GetOrSetFlag )
{
   String   szCourseTitle = null;
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
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// For internal classes, this is either the Topic Title or Course Title.
         //:// For transfer classes, this is the transfer course Title.

         //:IF mStudenC.RegistrationCourse EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "RegistrationCourse" );
         if ( lTempInteger_0 == 0 )
         { 
            //:IF mStudenC.CourseTopic EXISTS
            lTempInteger_1 = CheckExistenceOfEntity( mStudenC, "CourseTopic" );
            if ( lTempInteger_1 == 0 )
            { 
               //:szCourseTitle = mStudenC.CourseTopic.Title 
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szCourseTitle;
               if ( szCourseTitle == null )
                  sb_szCourseTitle = new StringBuilder( 32 );
               else
                  sb_szCourseTitle = new StringBuilder( szCourseTitle );
                               GetVariableFromAttribute( sb_szCourseTitle, mi_lTempInteger_2, 'S', 51, mStudenC, "CourseTopic", "Title", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szCourseTitle = sb_szCourseTitle.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szCourseTitle = mStudenC.RegistrationCourse.Title 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szCourseTitle;
               if ( szCourseTitle == null )
                  sb_szCourseTitle = new StringBuilder( 32 );
               else
                  sb_szCourseTitle = new StringBuilder( szCourseTitle );
                               GetVariableFromAttribute( sb_szCourseTitle, mi_lTempInteger_3, 'S', 51, mStudenC, "RegistrationCourse", "Title", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szCourseTitle = sb_szCourseTitle.toString( );}
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF mStudenC.EquivalentCourse EXISTS
            lTempInteger_4 = CheckExistenceOfEntity( mStudenC, "EquivalentCourse" );
            if ( lTempInteger_4 == 0 )
            { 
               //:IF mStudenC.Registration.ForeignClassName = ""
               if ( CompareAttributeToString( mStudenC, "Registration", "ForeignClassName", "" ) == 0 )
               { 
                  //:szCourseTitle = mStudenC.EquivalentCourse.Title
                  {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                  StringBuilder sb_szCourseTitle;
                  if ( szCourseTitle == null )
                     sb_szCourseTitle = new StringBuilder( 32 );
                  else
                     sb_szCourseTitle = new StringBuilder( szCourseTitle );
                                     GetVariableFromAttribute( sb_szCourseTitle, mi_lTempInteger_5, 'S', 51, mStudenC, "EquivalentCourse", "Title", "", 0 );
                  lTempInteger_5 = mi_lTempInteger_5.intValue( );
                  szCourseTitle = sb_szCourseTitle.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szCourseTitle = mStudenC.Registration.ForeignClassName
                  {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                  StringBuilder sb_szCourseTitle;
                  if ( szCourseTitle == null )
                     sb_szCourseTitle = new StringBuilder( 32 );
                  else
                     sb_szCourseTitle = new StringBuilder( szCourseTitle );
                                     GetVariableFromAttribute( sb_szCourseTitle, mi_lTempInteger_6, 'S', 51, mStudenC, "Registration", "ForeignClassName", "", 0 );
                  lTempInteger_6 = mi_lTempInteger_6.intValue( );
                  szCourseTitle = sb_szCourseTitle.toString( );}
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:szCourseTitle = mStudenC.Registration.ForeignClassName
               {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
               StringBuilder sb_szCourseTitle;
               if ( szCourseTitle == null )
                  sb_szCourseTitle = new StringBuilder( 32 );
               else
                  sb_szCourseTitle = new StringBuilder( szCourseTitle );
                               GetVariableFromAttribute( sb_szCourseTitle, mi_lTempInteger_7, 'S', 51, mStudenC, "Registration", "ForeignClassName", "", 0 );
               lTempInteger_7 = mi_lTempInteger_7.intValue( );
               szCourseTitle = sb_szCourseTitle.toString( );}
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mStudenC,
         //:                InternalEntityStructure, InternalAttribStructure, szCourseTitle )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szCourseTitle );
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
//:dEnrollmentClassNumber( VIEW mStudenC BASED ON LOD mStudenC,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   STRING ( 50 ) szCourseName
public int 
omStudenC_dEnrollmentClassNumber( View     mStudenC,
                                  String InternalEntityStructure,
                                  String InternalAttribStructure,
                                  Integer   GetOrSetFlag )
{
   String   szCourseName = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// For internal classes, this is the Course Number plus the Section.
         //:// For transfer classes, this is the transfer course name.

         //:IF mStudenC.RegistrationCourse EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "RegistrationCourse" );
         if ( lTempInteger_0 == 0 )
         { 
            //:IF mStudenC.RegistrationClass.Section = ""
            if ( CompareAttributeToString( mStudenC, "RegistrationClass", "Section", "" ) == 0 )
            { 
               //:szCourseName = mStudenC.Registration.wCourseNumber 
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_szCourseName;
               if ( szCourseName == null )
                  sb_szCourseName = new StringBuilder( 32 );
               else
                  sb_szCourseName = new StringBuilder( szCourseName );
                               GetVariableFromAttribute( sb_szCourseName, mi_lTempInteger_1, 'S', 51, mStudenC, "Registration", "wCourseNumber", "", 0 );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );
               szCourseName = sb_szCourseName.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szCourseName = mStudenC.Registration.wCourseNumber + " - Section: " + mStudenC.RegistrationClass.Section 
               {StringBuilder sb_szCourseName;
               if ( szCourseName == null )
                  sb_szCourseName = new StringBuilder( 32 );
               else
                  sb_szCourseName = new StringBuilder( szCourseName );
                               GetStringFromAttribute( sb_szCourseName, mStudenC, "Registration", "wCourseNumber" );
               szCourseName = sb_szCourseName.toString( );}
                {StringBuilder sb_szCourseName;
               if ( szCourseName == null )
                  sb_szCourseName = new StringBuilder( 32 );
               else
                  sb_szCourseName = new StringBuilder( szCourseName );
                              ZeidonStringConcat( sb_szCourseName, 1, 0, " - Section: ", 1, 0, 51 );
               szCourseName = sb_szCourseName.toString( );}
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 3, mStudenC, "RegistrationClass", "Section", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szCourseName;
               if ( szCourseName == null )
                  sb_szCourseName = new StringBuilder( 32 );
               else
                  sb_szCourseName = new StringBuilder( szCourseName );
                              ZeidonStringConcat( sb_szCourseName, 1, 0, szTempString_0, 1, 0, 51 );
               szCourseName = sb_szCourseName.toString( );}
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF mStudenC.EquivalentCourse EXISTS
            lTempInteger_3 = CheckExistenceOfEntity( mStudenC, "EquivalentCourse" );
            if ( lTempInteger_3 == 0 )
            { 
               //:IF mStudenC.Registration.ForeignClassName = ""
               if ( CompareAttributeToString( mStudenC, "Registration", "ForeignClassName", "" ) == 0 )
               { 
                  //:szCourseName = mStudenC.EquivalentCourse.Number
                  {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                  StringBuilder sb_szCourseName;
                  if ( szCourseName == null )
                     sb_szCourseName = new StringBuilder( 32 );
                  else
                     sb_szCourseName = new StringBuilder( szCourseName );
                                     GetVariableFromAttribute( sb_szCourseName, mi_lTempInteger_4, 'S', 51, mStudenC, "EquivalentCourse", "Number", "", 0 );
                  lTempInteger_4 = mi_lTempInteger_4.intValue( );
                  szCourseName = sb_szCourseName.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szCourseName = mStudenC.EquivalentCourse.Number + " - " + mStudenC.Registration.ForeignClassName
                  {StringBuilder sb_szCourseName;
                  if ( szCourseName == null )
                     sb_szCourseName = new StringBuilder( 32 );
                  else
                     sb_szCourseName = new StringBuilder( szCourseName );
                                     GetStringFromAttribute( sb_szCourseName, mStudenC, "EquivalentCourse", "Number" );
                  szCourseName = sb_szCourseName.toString( );}
                   {StringBuilder sb_szCourseName;
                  if ( szCourseName == null )
                     sb_szCourseName = new StringBuilder( 32 );
                  else
                     sb_szCourseName = new StringBuilder( szCourseName );
                                    ZeidonStringConcat( sb_szCourseName, 1, 0, " - ", 1, 0, 51 );
                  szCourseName = sb_szCourseName.toString( );}
                  {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                  StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_5, 'S', 101, mStudenC, "Registration", "ForeignClassName", "", 0 );
                  lTempInteger_5 = mi_lTempInteger_5.intValue( );
                  szTempString_1 = sb_szTempString_1.toString( );}
                   {StringBuilder sb_szCourseName;
                  if ( szCourseName == null )
                     sb_szCourseName = new StringBuilder( 32 );
                  else
                     sb_szCourseName = new StringBuilder( szCourseName );
                                    ZeidonStringConcat( sb_szCourseName, 1, 0, szTempString_1, 1, 0, 51 );
                  szCourseName = sb_szCourseName.toString( );}
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:szCourseName = mStudenC.Registration.ForeignClassName
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
               StringBuilder sb_szCourseName;
               if ( szCourseName == null )
                  sb_szCourseName = new StringBuilder( 32 );
               else
                  sb_szCourseName = new StringBuilder( szCourseName );
                               GetVariableFromAttribute( sb_szCourseName, mi_lTempInteger_6, 'S', 51, mStudenC, "Registration", "ForeignClassName", "", 0 );
               lTempInteger_6 = mi_lTempInteger_6.intValue( );
               szCourseName = sb_szCourseName.toString( );}
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mStudenC,
         //:                InternalEntityStructure, InternalAttribStructure, szCourseName )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szCourseName );
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
//:dEnrollmentTerm( VIEW mStudenC BASED ON LOD mStudenC,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )

//:   STRING ( 20 ) szCollegeTerm
public int 
omStudenC_dEnrollmentTerm( View     mStudenC,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   String   szCollegeTerm = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// The CollegeTerm from either the Class or TransferRecord or the Cohort.

         //:IF mStudenC.RegistrationClassCollegeTerm EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "RegistrationClassCollegeTerm" );
         if ( lTempInteger_0 == 0 )
         { 
            //:GetStringFromAttributeByContext( szCollegeTerm,
            //:                           mStudenC, "RegistrationClassCollegeTerm", "YearSemester", "", 20 )
            {StringBuilder sb_szCollegeTerm;
            if ( szCollegeTerm == null )
               sb_szCollegeTerm = new StringBuilder( 32 );
            else
               sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                         GetStringFromAttributeByContext( sb_szCollegeTerm, mStudenC, "RegistrationClassCollegeTerm", "YearSemester", "", 20 );
            szCollegeTerm = sb_szCollegeTerm.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mStudenC.TransferRecordTerm EXISTS
            lTempInteger_1 = CheckExistenceOfEntity( mStudenC, "TransferRecordTerm" );
            if ( lTempInteger_1 == 0 )
            { 
               //:GetStringFromAttributeByContext( szCollegeTerm,
               //:                           mStudenC, "TransferRecordTerm", "YearSemester", "", 20 )
               {StringBuilder sb_szCollegeTerm;
               if ( szCollegeTerm == null )
                  sb_szCollegeTerm = new StringBuilder( 32 );
               else
                  sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                               GetStringFromAttributeByContext( sb_szCollegeTerm, mStudenC, "TransferRecordTerm", "YearSemester", "", 20 );
               szCollegeTerm = sb_szCollegeTerm.toString( );}
               //:ELSE
            } 
            else
            { 
               //:IF mStudenC.ClassCohort EXISTS
               lTempInteger_2 = CheckExistenceOfEntity( mStudenC, "ClassCohort" );
               if ( lTempInteger_2 == 0 )
               { 
                  //:szCollegeTerm = mStudenC.ClassCohort.Name 
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szCollegeTerm;
                  if ( szCollegeTerm == null )
                     sb_szCollegeTerm = new StringBuilder( 32 );
                  else
                     sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                                     GetVariableFromAttribute( sb_szCollegeTerm, mi_lTempInteger_3, 'S', 21, mStudenC, "ClassCohort", "Name", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szCollegeTerm = sb_szCollegeTerm.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szCollegeTerm = ""
                   {StringBuilder sb_szCollegeTerm;
                  if ( szCollegeTerm == null )
                     sb_szCollegeTerm = new StringBuilder( 32 );
                  else
                     sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                                    ZeidonStringCopy( sb_szCollegeTerm, 1, 0, "", 1, 0, 21 );
                  szCollegeTerm = sb_szCollegeTerm.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mStudenC,
         //:                InternalEntityStructure, InternalAttribStructure, szCollegeTerm )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szCollegeTerm );
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
//:FindPrerequisiteClass( VIEW mStudenC BASED ON LOD mStudenC,
//:                       STRING ( 10 )  szPrerequisiteCourseNumber,
//:                       STRING ( 20 )  szPrerequisiteYearSemester )

//:   VIEW mStudenC2 BASED ON LOD  mStudenC
public int 
omStudenC_FindPrerequisiteClass( View     mStudenC,
                                 String   szPrerequisiteCourseNumber,
                                 String   szPrerequisiteYearSemester )
{
   zVIEW    mStudenC2 = new zVIEW( );
   //:STRING ( 10 )  szCourseNumber
   String   szCourseNumber = null;
   //:STRING ( 20 )  szYearSemester
   String   szYearSemester = null;
   //:STRING ( 1 )   RequisiteFound
   String   RequisiteFound = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   String   szTempString_1 = null;


   //:// Loop through all Registration entries looking for a match on the course and term.
   //:RequisiteFound = ""
    {StringBuilder sb_RequisiteFound;
   if ( RequisiteFound == null )
      sb_RequisiteFound = new StringBuilder( 32 );
   else
      sb_RequisiteFound = new StringBuilder( RequisiteFound );
      ZeidonStringCopy( sb_RequisiteFound, 1, 0, "", 1, 0, 2 );
   RequisiteFound = sb_RequisiteFound.toString( );}
   //:CreateViewFromView( mStudenC2, mStudenC )
   CreateViewFromView( mStudenC2, mStudenC );
   //:NAME VIEW mStudenC2 "mStudenC2"
   SetNameForView( mStudenC2, "mStudenC2", null, zLEVEL_TASK );
   //:FOR EACH mStudenC2.Registration 
   //:                 WHERE mStudenC2.Registration.Status != "D"
   //:                   AND mStudenC2.Registration.Status != "N"
   RESULT = SetCursorFirstEntity( mStudenC2, "Registration", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( mStudenC2, "Registration", "Status", "D" ) != 0 && CompareAttributeToString( mStudenC2, "Registration", "Status", "N" ) != 0 )
      { 
         //:szCourseNumber = ""
          {StringBuilder sb_szCourseNumber;
         if ( szCourseNumber == null )
            sb_szCourseNumber = new StringBuilder( 32 );
         else
            sb_szCourseNumber = new StringBuilder( szCourseNumber );
                  ZeidonStringCopy( sb_szCourseNumber, 1, 0, "", 1, 0, 11 );
         szCourseNumber = sb_szCourseNumber.toString( );}
         //:szYearSemester = ""
          {StringBuilder sb_szYearSemester;
         if ( szYearSemester == null )
            sb_szYearSemester = new StringBuilder( 32 );
         else
            sb_szYearSemester = new StringBuilder( szYearSemester );
                  ZeidonStringCopy( sb_szYearSemester, 1, 0, "", 1, 0, 21 );
         szYearSemester = sb_szYearSemester.toString( );}
         //:IF mStudenC2.RegistrationCourse EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mStudenC2, "RegistrationCourse" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szCourseNumber = mStudenC2.Registration.wCourseNumber
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szCourseNumber;
            if ( szCourseNumber == null )
               sb_szCourseNumber = new StringBuilder( 32 );
            else
               sb_szCourseNumber = new StringBuilder( szCourseNumber );
                         GetVariableFromAttribute( sb_szCourseNumber, mi_lTempInteger_1, 'S', 11, mStudenC2, "Registration", "wCourseNumber", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szCourseNumber = sb_szCourseNumber.toString( );}
            //:IF mStudenC2.ClassCohort EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mStudenC2, "ClassCohort" );
            if ( lTempInteger_2 == 0 )
            { 
               //:// For Classes within Cohorts, we will make the szYearSemester the start time of the Class, since Couses should
               //:// be taken in sequence.
               //:szYearSemester = mStudenC2.RegistrationClass.ClassStartDate
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szYearSemester;
               if ( szYearSemester == null )
                  sb_szYearSemester = new StringBuilder( 32 );
               else
                  sb_szYearSemester = new StringBuilder( szYearSemester );
                               GetVariableFromAttribute( sb_szYearSemester, mi_lTempInteger_3, 'S', 21, mStudenC2, "RegistrationClass", "ClassStartDate", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szYearSemester = sb_szYearSemester.toString( );}
               //:ELSE 
            } 
            else
            { 
               //:szYearSemester = mStudenC2.RegistrationClassCollegeYear.Year + mStudenC2.RegistrationClassCollegeTerm.Semester 
               {StringBuilder sb_szYearSemester;
               if ( szYearSemester == null )
                  sb_szYearSemester = new StringBuilder( 32 );
               else
                  sb_szYearSemester = new StringBuilder( szYearSemester );
                               GetStringFromAttribute( sb_szYearSemester, mStudenC2, "RegistrationClassCollegeYear", "Year" );
               szYearSemester = sb_szYearSemester.toString( );}
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, mStudenC2, "RegistrationClassCollegeTerm", "Semester" );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szYearSemester;
               if ( szYearSemester == null )
                  sb_szYearSemester = new StringBuilder( 32 );
               else
                  sb_szYearSemester = new StringBuilder( szYearSemester );
                              ZeidonStringConcat( sb_szYearSemester, 1, 0, szTempString_0, 1, 0, 21 );
               szYearSemester = sb_szYearSemester.toString( );}
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF mStudenC2.EquivalentCourse EXISTS
            lTempInteger_4 = CheckExistenceOfEntity( mStudenC2, "EquivalentCourse" );
            if ( lTempInteger_4 == 0 )
            { 
               //:szCourseNumber = mStudenC2.EquivalentCourse.Number
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szCourseNumber;
               if ( szCourseNumber == null )
                  sb_szCourseNumber = new StringBuilder( 32 );
               else
                  sb_szCourseNumber = new StringBuilder( szCourseNumber );
                               GetVariableFromAttribute( sb_szCourseNumber, mi_lTempInteger_5, 'S', 11, mStudenC2, "EquivalentCourse", "Number", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szCourseNumber = sb_szCourseNumber.toString( );}
            } 

            //:END
            //:IF mStudenC2.TransferRecordTerm EXISTS 
            lTempInteger_6 = CheckExistenceOfEntity( mStudenC2, "TransferRecordTerm" );
            if ( lTempInteger_6 == 0 )
            { 
               //:szYearSemester = mStudenC2.TransferRecordYear.Year + mStudenC2.TransferRecordTerm.Semester 
               {StringBuilder sb_szYearSemester;
               if ( szYearSemester == null )
                  sb_szYearSemester = new StringBuilder( 32 );
               else
                  sb_szYearSemester = new StringBuilder( szYearSemester );
                               GetStringFromAttribute( sb_szYearSemester, mStudenC2, "TransferRecordYear", "Year" );
               szYearSemester = sb_szYearSemester.toString( );}
               {StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetStringFromAttribute( sb_szTempString_1, mStudenC2, "TransferRecordTerm", "Semester" );
               szTempString_1 = sb_szTempString_1.toString( );}
                {StringBuilder sb_szYearSemester;
               if ( szYearSemester == null )
                  sb_szYearSemester = new StringBuilder( 32 );
               else
                  sb_szYearSemester = new StringBuilder( szYearSemester );
                              ZeidonStringConcat( sb_szYearSemester, 1, 0, szTempString_1, 1, 0, 21 );
               szYearSemester = sb_szYearSemester.toString( );}
            } 

            //:END
         } 

         //:END
         //:IF szCourseNumber != ""
         if ( ZeidonStringCompare( szCourseNumber, 1, 0, "", 1, 0, 11 ) != 0 )
         { 
            //:// Normally, we compare on CourseNumber, YearSemester and grade. However, if no Term existed for the Registration entry
            //:// (the entry is for a transferred Course without a Term or for a waived Course), then we will only compare on CourseNumber.
            //:IF szYearSemester = ""
            if ( ZeidonStringCompare( szYearSemester, 1, 0, "", 1, 0, 21 ) == 0 )
            { 
               //:// Transferred entry without a Term or a Waived entry.
               //:IF szCourseNumber = szPrerequisiteCourseNumber
               if ( ZeidonStringCompare( szCourseNumber, 1, 0, szPrerequisiteCourseNumber, 1, 0, 11 ) == 0 )
               { 
                  //:// The prerequisite was found.
                  //:RequisiteFound = "Y"
                   {StringBuilder sb_RequisiteFound;
                  if ( RequisiteFound == null )
                     sb_RequisiteFound = new StringBuilder( 32 );
                  else
                     sb_RequisiteFound = new StringBuilder( RequisiteFound );
                                    ZeidonStringCopy( sb_RequisiteFound, 1, 0, "Y", 1, 0, 2 );
                  RequisiteFound = sb_RequisiteFound.toString( );}
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// Normal entry
               //:IF szCourseNumber = szPrerequisiteCourseNumber AND
               //:szYearSemester < szPrerequisiteYearSemester AND
               //:mStudenC2.Registration.CreditHours > 0 AND
               //:mStudenC2.Registration.FinalGrade != "F" AND
               //:mStudenC2.Registration.FinalGrade != "U" AND
               //:mStudenC2.Registration.FinalGrade != "NC" AND
               //:mStudenC2.Registration.FinalGrade != "W"
               if ( ZeidonStringCompare( szCourseNumber, 1, 0, szPrerequisiteCourseNumber, 1, 0, 11 ) == 0 && ZeidonStringCompare( szYearSemester, 1, 0, szPrerequisiteYearSemester, 1, 0, 21 ) > 0 &&
                    CompareAttributeToInteger( mStudenC2, "Registration", "CreditHours", 0 ) > 0 && CompareAttributeToString( mStudenC2, "Registration", "FinalGrade", "F" ) != 0 &&
                    CompareAttributeToString( mStudenC2, "Registration", "FinalGrade", "U" ) != 0 && CompareAttributeToString( mStudenC2, "Registration", "FinalGrade", "NC" ) != 0 &&
                    CompareAttributeToString( mStudenC2, "Registration", "FinalGrade", "W" ) != 0 )
               { 

                  //:// The prerequisite was found.
                  //:RequisiteFound = "Y"
                   {StringBuilder sb_RequisiteFound;
                  if ( RequisiteFound == null )
                     sb_RequisiteFound = new StringBuilder( 32 );
                  else
                     sb_RequisiteFound = new StringBuilder( RequisiteFound );
                                    ZeidonStringCopy( sb_RequisiteFound, 1, 0, "Y", 1, 0, 2 );
                  RequisiteFound = sb_RequisiteFound.toString( );}
               } 

               //:END
            } 

            //:END
         } 

      } 

      RESULT = SetCursorNextEntity( mStudenC2, "Registration", "" );
      //:END
   } 

   //:END
   //:DropView( mStudenC2 )
   DropView( mStudenC2 );
   //:IF RequisiteFound = "Y"
   if ( ZeidonStringCompare( RequisiteFound, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:RETURN 0
      if(8==8)return( 0 );
      //:ELSE 
   } 
   else
   { 
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:FindCorequisiteClass( VIEW mStudenC BASED ON LOD mStudenC,
//:                      STRING ( 10 )  szPrerequisiteCourseNumber )

//:   VIEW mStudenC2 BASED ON LOD  mStudenC
public int 
omStudenC_FindCorequisiteClass( View     mStudenC,
                                String   szPrerequisiteCourseNumber )
{
   zVIEW    mStudenC2 = new zVIEW( );
   //:STRING ( 30 )  szYearSemester
   String   szYearSemester = null;
   //:STRING ( 1 )   RequisiteFound
   String   RequisiteFound = null;
   //:SHORT nRC
   int      nRC = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      RESULT = 0;


   //:// Loop through all Registration entries looking for a match on the course and term.
   //:// The Corequisite find differs from the Prerequisite find in that a prerequisite requires a match on 
   //:// an earlier Term while the corequisite requires a match on either the current or an earlier Term.
   //:// Thus, we will execute the prerequisite checks and then the current term checks.

   //:// First perform the Prerequisite check.
   //:szYearSemester = mStudenC.UpdateSchedule.CollegeYear + mStudenC.UpdateSchedule.CollegeTermSemester 
   {StringBuilder sb_szYearSemester;
   if ( szYearSemester == null )
      sb_szYearSemester = new StringBuilder( 32 );
   else
      sb_szYearSemester = new StringBuilder( szYearSemester );
       GetStringFromAttribute( sb_szYearSemester, mStudenC, "UpdateSchedule", "CollegeYear" );
   szYearSemester = sb_szYearSemester.toString( );}
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, mStudenC, "UpdateSchedule", "CollegeTermSemester" );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szYearSemester;
   if ( szYearSemester == null )
      sb_szYearSemester = new StringBuilder( 32 );
   else
      sb_szYearSemester = new StringBuilder( szYearSemester );
      ZeidonStringConcat( sb_szYearSemester, 1, 0, szTempString_0, 1, 0, 31 );
   szYearSemester = sb_szYearSemester.toString( );}
   //:nRC = FindPrerequisiteClass( mStudenC, szPrerequisiteCourseNumber, mStudenC.UpdateSchedule.YearSemester )
   {StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
       GetStringFromAttribute( sb_szTempString_1, mStudenC, "UpdateSchedule", "YearSemester" );
   szTempString_1 = sb_szTempString_1.toString( );}
   nRC = omStudenC_FindPrerequisiteClass( mStudenC, szPrerequisiteCourseNumber, szTempString_1 );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:// Course was found in an earlier Term.
      //:RETURN 0
      if(8==8)return( 0 );
   } 

   //:END

   //:// Then the Corequisite check.
   //:RequisiteFound = ""
    {StringBuilder sb_RequisiteFound;
   if ( RequisiteFound == null )
      sb_RequisiteFound = new StringBuilder( 32 );
   else
      sb_RequisiteFound = new StringBuilder( RequisiteFound );
      ZeidonStringCopy( sb_RequisiteFound, 1, 0, "", 1, 0, 2 );
   RequisiteFound = sb_RequisiteFound.toString( );}
   //:CreateViewFromView( mStudenC2, mStudenC )
   CreateViewFromView( mStudenC2, mStudenC );
   //:FOR EACH mStudenC2.US_Registration WHERE mStudenC2.US_Registration.Status != "D"
   RESULT = SetCursorFirstEntity( mStudenC2, "US_Registration", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( mStudenC2, "US_Registration", "Status", "D" ) != 0 )
      { 
         //:IF mStudenC2.US_Class.wCourseSection = szPrerequisiteCourseNumber
         if ( CompareAttributeToString( mStudenC2, "US_Class", "wCourseSection", szPrerequisiteCourseNumber ) == 0 )
         { 
            //:// The corequisite was found.
            //:RequisiteFound = "Y"
             {StringBuilder sb_RequisiteFound;
            if ( RequisiteFound == null )
               sb_RequisiteFound = new StringBuilder( 32 );
            else
               sb_RequisiteFound = new StringBuilder( RequisiteFound );
                        ZeidonStringCopy( sb_RequisiteFound, 1, 0, "Y", 1, 0, 2 );
            RequisiteFound = sb_RequisiteFound.toString( );}
         } 

      } 

      RESULT = SetCursorNextEntity( mStudenC2, "US_Registration", "" );
      //:END
   } 

   //:END
   //:DropView( mStudenC2 )
   DropView( mStudenC2 );
   //:IF RequisiteFound = "Y"
   if ( ZeidonStringCompare( RequisiteFound, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:RETURN 0
      if(8==8)return( 0 );
      //:ELSE 
   } 
   else
   { 
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:ValidateAddedCourse( VIEW mStudenC BASED ON LOD mStudenC,
//:                     VIEW lClsLstC BASED ON LOD lClsLstC )

//:   STRING ( 1000 ) szMsg
public int 
omStudenC_ValidateAddedCourse( View     mStudenC,
                               View     lClsLstC )
{
   String   szMsg = null;
   //:STRING ( 1000 ) szPartMsg
   String   szPartMsg = null;
   //:STRING ( 1 )   PreReqsNotMet
   String   PreReqsNotMet = null;
   //:STRING ( 5 )   szCount
   String   szCount = null;
   //:INTEGER        Count
   int      Count = 0;
   //:SHORT nRC
   int      nRC = 0;
   //:SHORT nValidationRC
   int      nValidationRC = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;


   //:// Remove any earlier errors.
   //:FOR EACH mStudenC.ValidationErrorMessage 
   RESULT = SetCursorFirstEntity( mStudenC, "ValidationErrorMessage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mStudenC.ValidationErrorMessage NONE 
      RESULT = DeleteEntity( mStudenC, "ValidationErrorMessage", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mStudenC, "ValidationErrorMessage", "" );
   } 

   //:END

   //:// Go to perform standard validations.
   //:nValidationRC = ValidateAddedCourseR( mStudenC, lClsLstC, PreReqsNotMet )
   {StringBuilder sb_PreReqsNotMet;
   if ( PreReqsNotMet == null )
      sb_PreReqsNotMet = new StringBuilder( 32 );
   else
      sb_PreReqsNotMet = new StringBuilder( PreReqsNotMet );
       nValidationRC = omStudenC_ValidateAddedCourseR( mStudenC, lClsLstC, sb_PreReqsNotMet );
   PreReqsNotMet = sb_PreReqsNotMet.toString( );}
   //:IF nValidationRC < 0
   if ( nValidationRC < 0 )
   { 
      //:// This is a hard error and cannot be overridden.
      //:szMsg = "Validation Error for student " + mStudenC.Student.dPersonFullName + "." + NEW_LINE + 
      //:        mStudenC.ValidationErrorMessage.Value
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 255, mStudenC, "Student", "dPersonFullName", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringCopy( sb_szMsg, 1, 0, "Validation Error for student ", 1, 0, 1001 );
      szMsg = sb_szMsg.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_0, 1, 0, 1001 );
      szMsg = sb_szMsg.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, ".", 1, 0, 1001 );
      szMsg = sb_szMsg.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 1001 );
      szMsg = sb_szMsg.toString( );}
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
             GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 10001, mStudenC, "ValidationErrorMessage", "Value", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szTempString_1 = sb_szTempString_1.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_1, 1, 0, 1001 );
      szMsg = sb_szMsg.toString( );}
      //:MessageSend( mStudenC, "", "Course Validations", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mStudenC, "", "Course Validations", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
      //:ELSE
   } 
   else
   { 
      //:// Any of these errors can be overridden by the User.
      //:// Format the single/multiple error message.
      //:SET CURSOR FIRST mStudenC.ValidationErrorMessage 
      RESULT = SetCursorFirstEntity( mStudenC, "ValidationErrorMessage", "" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// There is at least one error message.
         //:SET CURSOR NEXT mStudenC.ValidationErrorMessage 
         RESULT = SetCursorNextEntity( mStudenC, "ValidationErrorMessage", "" );
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:// There are at least two error messages.
            //:Count = 1
            Count = 1;
            //:szMsg = "Multiple validation errors occurred for student " + mStudenC.Student.dPersonFullName + "." + NEW_LINE + NEW_LINE
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_2, 'S', 255, mStudenC, "Student", "dPersonFullName", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringCopy( sb_szMsg, 1, 0, "Multiple validation errors occurred for student ", 1, 0, 1001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_2, 1, 0, 1001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, ".", 1, 0, 1001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 1001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 1001 );
            szMsg = sb_szMsg.toString( );}
            //:FOR EACH mStudenC.ValidationErrorMessage 
            RESULT = SetCursorFirstEntity( mStudenC, "ValidationErrorMessage", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:szCount = Count
                {StringBuilder sb_szCount;
               if ( szCount == null )
                  sb_szCount = new StringBuilder( 32 );
               else
                  sb_szCount = new StringBuilder( szCount );
                              ZeidonStringConvertFromNumber( sb_szCount, 1, 0, 5, Count, (double) 0.0, "I" );
               szCount = sb_szCount.toString( );}
               //:szPartMsg = mStudenC.ValidationErrorMessage.Value
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szPartMsg;
               if ( szPartMsg == null )
                  sb_szPartMsg = new StringBuilder( 32 );
               else
                  sb_szPartMsg = new StringBuilder( szPartMsg );
                               GetVariableFromAttribute( sb_szPartMsg, mi_lTempInteger_3, 'S', 1001, mStudenC, "ValidationErrorMessage", "Value", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szPartMsg = sb_szPartMsg.toString( );}
               //:szMsg = szMsg + "  Error " + szCount + ": " + szPartMsg + NEW_LINE + NEW_LINE
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, "  Error ", 1, 0, 1001 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, szCount, 1, 0, 1001 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, ": ", 1, 0, 1001 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, szPartMsg, 1, 0, 1001 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 1001 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 1001 );
               szMsg = sb_szMsg.toString( );}
               //:Count = Count + 1
               Count = Count + 1;
               RESULT = SetCursorNextEntity( mStudenC, "ValidationErrorMessage", "" );
            } 

            //:END
            //:szMsg = szMsg + "Can the student take the class anyway?"
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, "Can the student take the class anyway?", 1, 0, 1001 );
            szMsg = sb_szMsg.toString( );}

            //:ELSE
         } 
         else
         { 
            //:// There is one error message.
            //:szPartMsg = mStudenC.ValidationErrorMessage.Value
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szPartMsg;
            if ( szPartMsg == null )
               sb_szPartMsg = new StringBuilder( 32 );
            else
               sb_szPartMsg = new StringBuilder( szPartMsg );
                         GetVariableFromAttribute( sb_szPartMsg, mi_lTempInteger_4, 'S', 1001, mStudenC, "ValidationErrorMessage", "Value", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szPartMsg = sb_szPartMsg.toString( );}
            //:szMsg = szPartMsg  + NEW_LINE + NEW_LINE + "Can the student take the class anyway?"
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringCopy( sb_szMsg, 1, 0, szPartMsg, 1, 0, 1001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 1001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 1001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, "Can the student take the class anyway?", 1, 0, 1001 );
            szMsg = sb_szMsg.toString( );}
         } 

         //:END
         //:nRC = MessagePrompt( mStudenC, "", "Course Validations",
         //:                     szMsg,
         //:                     0, zBUTTONS_YESNO, zRESPONSE_NO, 0 )
         nRC = MessagePrompt( mStudenC, "", "Course Validations", szMsg, 0, zBUTTONS_YESNO, zRESPONSE_NO, 0 );
         //:IF nRC = zRESPONSE_NO
         if ( nRC == zRESPONSE_NO )
         { 
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:IF PreReqsNotMet = "Y"   // Pre-reqs weren't met but the User accepted the class anyway.
   if ( ZeidonStringCompare( PreReqsNotMet, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:RETURN 2
      if(8==8)return( 2 );
      //:ELSE
   } 
   else
   { 
      //:RETURN 0
      if(8==8)return( 0 );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildCurrentSchedule( VIEW mStudenC BASED ON LOD mStudenC )

//:   VIEW lTermLST  REGISTERED AS lTermLST
public int 
omStudenC_BuildCurrentSchedule( View     mStudenC )
{
   zVIEW    lTermLST = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lTermLST2 BASED ON LOD  lTermLST
   zVIEW    lTermLST2 = new zVIEW( );
   //:VIEW mTestView
   zVIEW    mTestView = new zVIEW( );
   //:VIEW wXferO    REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   //:DECIMAL TotalTermCredits
   double  TotalTermCredits = 0.0;
   //:DECIMAL TotalYearCredits
   double  TotalYearCredits = 0.0;
   //:STRING ( 20 )  szDecimalString
   String   szDecimalString = null;
   //:STRING ( 254 ) szMeetingSchedule
   String   szMeetingSchedule = null;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   //:STRING ( 20 )  szExternalStatus
   String   szExternalStatus = null;
   //:STRING ( 20 )  szRanking
   String   szRanking = null;
   //:STRING ( 20 )  szSemester
   String   szSemester = null;
   //:STRING ( 1 )   FoundFlag
   String   FoundFlag = null;
   //:STRING ( 1 )   TotalEntryFlag
   String   TotalEntryFlag = null;
   //:INTEGER        Count
   int      Count = 0;
   //:INTEGER        LastTermID
   int      LastTermID = 0;
   //:SHORT          nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( lTermLST, "lTermLST", mStudenC, zLEVEL_TASK );
   RESULT = GetViewByName( wXferO, "wXferO", mStudenC, zLEVEL_TASK );

   //:// Create the derived current schedule path (CurrentScheduleEntry) for the current year (StudentScheduleCollegeYear).
   //:// For each Term, we will create a CurrentScheduleEntryTerm entity, with a null entry between Terms. We will also
   //:// create a CurrentScheduleEntryTerm entry to hold each CurrentClass entity.
   //:// Note that this routine first adds all entries, including drops and withdrawns. Drops are later removed by the
   //:// calling operation.

   //:CreateViewFromView( lTermLST2, lTermLST )
   CreateViewFromView( lTermLST2, lTermLST );
   //:NAME VIEW mStudenC "mStudenCLoading"
   SetNameForView( mStudenC, "mStudenCLoading", null, zLEVEL_TASK );

   //:// Delete any previous entries.
   //:FOR EACH mStudenC.CurrentScheduleEntry 
   RESULT = SetCursorFirstEntity( mStudenC, "CurrentScheduleEntry", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mStudenC.CurrentScheduleEntry NONE 
      RESULT = DeleteEntity( mStudenC, "CurrentScheduleEntry", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mStudenC, "CurrentScheduleEntry", "" );
   } 

   //:END

   //:// Exit if CollegeYear is not selected.
   //:IF wXferO.StudentScheduleCollegeYear DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( wXferO, "StudentScheduleCollegeYear" );
   if ( lTempInteger_0 != 0 )
   { 
      //:RETURN -1 
      if(8==8)return( -1 );
   } 

   //:END

   //:// Before creating the entries, order the Registration entries so the schedules will come out in Course
   //:// Number order within the Semester within the Year.
   //:OrderRegistrationRecs( mStudenC )
   omStudenC_OrderRegistrationRecs( mStudenC );

   //:// Also build the list of Semesters for the Year in Semester Order. First delete prior members.
   //:FOR EACH wXferO.StudentScheduleCollegeTerm
   RESULT = SetCursorFirstEntity( wXferO, "StudentScheduleCollegeTerm", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:EXCLUDE wXferO.StudentScheduleCollegeTerm NONE 
      RESULT = ExcludeEntity( wXferO, "StudentScheduleCollegeTerm", zREPOS_NONE );
      RESULT = SetCursorNextEntity( wXferO, "StudentScheduleCollegeTerm", "" );
   } 

   //:END
   //:FOR EACH lTermLST2.CollegeTerm WHERE lTermLST2.CollegeYear.ID = wXferO.StudentScheduleCollegeYear.ID 
   RESULT = SetCursorFirstEntity( lTermLST2, "CollegeTerm", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToAttribute( lTermLST2, "CollegeYear", "ID", wXferO, "StudentScheduleCollegeYear", "ID" ) == 0 )
      { 
         //:INCLUDE wXferO.StudentScheduleCollegeTerm FROM lTermLST2.CollegeTerm 
         RESULT = IncludeSubobjectFromSubobject( wXferO, "StudentScheduleCollegeTerm", lTermLST2, "CollegeTerm", zPOS_AFTER );
      } 

      RESULT = SetCursorNextEntity( lTermLST2, "CollegeTerm", "" );
   } 

   //:END
   //:OrderEntityForView( wXferO, "StudentScheduleCollegeTerm", "Semester A" )
   OrderEntityForView( wXferO, "StudentScheduleCollegeTerm", "Semester A" );

   //:// Create Term entries, one each for Fall, January, Spring and Summer, etc.
   //:// Add a blank line between entries.
   //:Count = 0
   Count = 0;
   //:FOR EACH wXferO.StudentScheduleCollegeTerm
   RESULT = SetCursorFirstEntity( wXferO, "StudentScheduleCollegeTerm", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:Count = Count + 1
      Count = Count + 1;
      //:IF Count > 1
      if ( Count > 1 )
      { 
         //:CREATE ENTITY mStudenC.CurrentScheduleEntry  
         RESULT = CreateEntity( mStudenC, "CurrentScheduleEntry", zPOS_AFTER );
      } 

      //:END
      //:CREATE ENTITY mStudenC.CurrentScheduleEntry
      RESULT = CreateEntity( mStudenC, "CurrentScheduleEntry", zPOS_AFTER );
      //:GetStringFromAttributeByContext( szSemester, wXferO, "StudentScheduleCollegeTerm", "Semester", "", 20 )
      {StringBuilder sb_szSemester;
      if ( szSemester == null )
         sb_szSemester = new StringBuilder( 32 );
      else
         sb_szSemester = new StringBuilder( szSemester );
             GetStringFromAttributeByContext( sb_szSemester, wXferO, "StudentScheduleCollegeTerm", "Semester", "", 20 );
      szSemester = sb_szSemester.toString( );}
      //:mStudenC.CurrentScheduleEntry.YearSemester = wXferO.StudentScheduleCollegeYear.Year + " " + szSemester
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, wXferO, "StudentScheduleCollegeYear", "Year" );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
            ZeidonStringConcat( sb_szTempString_0, 1, 0, " ", 1, 0, 21 );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
            ZeidonStringConcat( sb_szTempString_0, 1, 0, szSemester, 1, 0, 21 );
      szTempString_0 = sb_szTempString_0.toString( );}
      SetAttributeFromString( mStudenC, "CurrentScheduleEntry", "YearSemester", szTempString_0 );
      RESULT = SetCursorNextEntity( wXferO, "StudentScheduleCollegeTerm", "" );
   } 

   //:END

   //:// Set IDs and make sure Term has been defined.
   //:FOR EACH mStudenC.CurrentScheduleEntry WHERE mStudenC.CurrentScheduleEntry.YearSemester != ""
   RESULT = SetCursorFirstEntity( mStudenC, "CurrentScheduleEntry", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( mStudenC, "CurrentScheduleEntry", "YearSemester", "" ) != 0 )
      { 
         //:SET CURSOR FIRST lTermLST2.CollegeTerm WHERE lTermLST2.CollegeTerm.YearSemester = mStudenC.CurrentScheduleEntry.YearSemester  
         {StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetStringFromAttribute( sb_szTempString_1, mStudenC, "CurrentScheduleEntry", "YearSemester" );
         szTempString_1 = sb_szTempString_1.toString( );}
         RESULT = SetCursorFirstEntityByString( lTermLST2, "CollegeTerm", "YearSemester", szTempString_1, "" );
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:mStudenC.CurrentScheduleEntry.wCollegeTermID = lTermLST2.CollegeTerm.ID 
            SetAttributeFromAttribute( mStudenC, "CurrentScheduleEntry", "wCollegeTermID", lTermLST2, "CollegeTerm", "ID" );
         } 

      } 

      RESULT = SetCursorNextEntity( mStudenC, "CurrentScheduleEntry", "" );
      //:END 
   } 

   //:END

   //:// Add registered entries.
   //:TotalTermCredits = 0
   TotalTermCredits = 0;
   //:TotalYearCredits = 0
   TotalYearCredits = 0;
   //:LastTermID = 0
   LastTermID = 0;
   //:Count = 0
   Count = 0;
   //:FOR EACH mStudenC.RegistrationClass WITHIN mStudenC.Student 
   RESULT = SetCursorFirstEntity( mStudenC, "RegistrationClass", "Student" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mStudenC.Registration.wCollegeYearID = wXferO.StudentScheduleCollegeYear.ID 
      if ( CompareAttributeToAttribute( mStudenC, "Registration", "wCollegeYearID", wXferO, "StudentScheduleCollegeYear", "ID" ) == 0 )
      { 
         //:IF mStudenC.Registration.wCollegeTermID != LastTermID
         if ( CompareAttributeToInteger( mStudenC, "Registration", "wCollegeTermID", LastTermID ) != 0 )
         { 
            //:Count = Count + 1
            Count = Count + 1;
            //:IF Count > 1
            if ( Count > 1 )
            { 
               //:// Create Totals entry.
               //:CREATE ENTITY mStudenC.CurrentScheduleEntry
               RESULT = CreateEntity( mStudenC, "CurrentScheduleEntry", zPOS_AFTER );
               //:CREATE ENTITY mStudenC.CurrentClass 
               RESULT = CreateEntity( mStudenC, "CurrentClass", zPOS_AFTER );
               //:CREATE ENTITY mStudenC.CurrentCourse 
               RESULT = CreateEntity( mStudenC, "CurrentCourse", zPOS_AFTER );
               //:mStudenC.CurrentCourse.Title = "Term Totals: "
               SetAttributeFromString( mStudenC, "CurrentCourse", "Title", "Term Totals: " );
               //:mStudenC.CurrentClass.wStudentCreditHours = TotalTermCredits
               SetAttributeFromDecimal( mStudenC, "CurrentClass", "wStudentCreditHours", TotalTermCredits );
            } 

            //:END

            //:SET CURSOR FIRST mStudenC.CurrentScheduleEntry 
            //:           WHERE mStudenC.CurrentScheduleEntry.YearSemester = mStudenC.Registration.wCollegeYearSemester 
            {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetStringFromAttribute( sb_szTempString_2, mStudenC, "Registration", "wCollegeYearSemester" );
            szTempString_2 = sb_szTempString_2.toString( );}
            RESULT = SetCursorFirstEntityByString( mStudenC, "CurrentScheduleEntry", "YearSemester", szTempString_2, "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:IssueError( mStudenC,0,0, "Couldn't position on Semester" )
               IssueError( mStudenC, 0, 0, "Couldn't position on Semester" );
            } 

            //:END
            //:TotalTermCredits = 0
            TotalTermCredits = 0;
            //:LastTermID = mStudenC.Registration.wCollegeTermID 
            {MutableInt mi_LastTermID = new MutableInt( LastTermID );
                         GetIntegerFromAttribute( mi_LastTermID, mStudenC, "Registration", "wCollegeTermID" );
            LastTermID = mi_LastTermID.intValue( );}
            //:mStudenC.CurrentScheduleEntry.wCollegeTermID = LastTermID
            SetAttributeFromInteger( mStudenC, "CurrentScheduleEntry", "wCollegeTermID", LastTermID );
         } 

         //:END

         //:// Create the Class entry.
         //:CREATE ENTITY mStudenC.CurrentScheduleEntry
         RESULT = CreateEntity( mStudenC, "CurrentScheduleEntry", zPOS_AFTER );
         //:mStudenC.CurrentScheduleEntry.wDroppedDate = mStudenC.Registration.DroppedDate
         SetAttributeFromAttribute( mStudenC, "CurrentScheduleEntry", "wDroppedDate", mStudenC, "Registration", "DroppedDate" );
         //:INCLUDE mStudenC.CurrentClass FROM mStudenC.RegistrationClass 
         RESULT = IncludeSubobjectFromSubobject( mStudenC, "CurrentClass", mStudenC, "RegistrationClass", zPOS_AFTER );
         //:mStudenC.CurrentClass.wMidtermGrade              = mStudenC.Registration.MidtermGrade 
         SetAttributeFromAttribute( mStudenC, "CurrentClass", "wMidtermGrade", mStudenC, "Registration", "MidtermGrade" );
         //:mStudenC.CurrentClass.wFinalGrade                = mStudenC.Registration.FinalGrade 
         SetAttributeFromAttribute( mStudenC, "CurrentClass", "wFinalGrade", mStudenC, "Registration", "FinalGrade" );
         //:mStudenC.CurrentClass.wTakingClassType           = mStudenC.Registration.TakingClassType 
         SetAttributeFromAttribute( mStudenC, "CurrentClass", "wTakingClassType", mStudenC, "Registration", "TakingClassType" );
         //:GetStringFromAttributeByContext( szExternalStatus,
         //:                                 mStudenC, "Registration", "Status", "", 20 )
         {StringBuilder sb_szExternalStatus;
         if ( szExternalStatus == null )
            sb_szExternalStatus = new StringBuilder( 32 );
         else
            sb_szExternalStatus = new StringBuilder( szExternalStatus );
                   GetStringFromAttributeByContext( sb_szExternalStatus, mStudenC, "Registration", "Status", "", 20 );
         szExternalStatus = sb_szExternalStatus.toString( );}
         //:mStudenC.CurrentClass.wStatus = szExternalStatus
         SetAttributeFromString( mStudenC, "CurrentClass", "wStatus", szExternalStatus );
         //:mStudenC.CurrentClass.wCourseTitle   = mStudenC.Registration.wCourseTitle
         SetAttributeFromAttribute( mStudenC, "CurrentClass", "wCourseTitle", mStudenC, "Registration", "wCourseTitle" );
         //:mStudenC.CurrentClass.wCourseSection = mStudenC.Registration.dClassNumber 
         SetAttributeFromAttribute( mStudenC, "CurrentClass", "wCourseSection", mStudenC, "Registration", "dClassNumber" );
         //:mStudenC.CurrentClass.wPreReqsNotMet = mStudenC.Registration.PrereqsNotMet 
         SetAttributeFromAttribute( mStudenC, "CurrentClass", "wPreReqsNotMet", mStudenC, "Registration", "PrereqsNotMet" );

         //:// In all cases we will total the following entries.
         //:TotalEntryFlag = ""
          {StringBuilder sb_TotalEntryFlag;
         if ( TotalEntryFlag == null )
            sb_TotalEntryFlag = new StringBuilder( 32 );
         else
            sb_TotalEntryFlag = new StringBuilder( TotalEntryFlag );
                  ZeidonStringCopy( sb_TotalEntryFlag, 1, 0, "", 1, 0, 2 );
         TotalEntryFlag = sb_TotalEntryFlag.toString( );}
         //:IF mStudenC.Registration.Status = "R" OR // Registered
         //:   mStudenC.Registration.Status = "T" OR // Completed
         //:   mStudenC.Registration.Status = "C" OR // Currently Taking
         //:   mStudenC.Registration.Status = "F" OR // Transferred
         //:   mStudenC.Registration.Status = "X"    // Legacy Transferred
         if ( CompareAttributeToString( mStudenC, "Registration", "Status", "R" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "C" ) == 0 ||
              CompareAttributeToString( mStudenC, "Registration", "Status", "F" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "X" ) == 0 )
         { 

            //:TotalEntryFlag = "Y"
             {StringBuilder sb_TotalEntryFlag;
            if ( TotalEntryFlag == null )
               sb_TotalEntryFlag = new StringBuilder( 32 );
            else
               sb_TotalEntryFlag = new StringBuilder( TotalEntryFlag );
                        ZeidonStringCopy( sb_TotalEntryFlag, 1, 0, "Y", 1, 0, 2 );
            TotalEntryFlag = sb_TotalEntryFlag.toString( );}
         } 

         //:END

         //:// If we are executing this from within Student Accounts or Financial Aid, we want Withdrawn entries
         //:// to be included in total credits and we want to show the entry with its credits (instead of null for Dropped entries.)
         //:GET VIEW mTestView NAMED "mSAStu"
         RESULT = GetViewByName( mTestView, "mSAStu", mStudenC, zLEVEL_TASK );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:GET VIEW mTestView NAMED "mFAStu"
            RESULT = GetViewByName( mTestView, "mFAStu", mStudenC, zLEVEL_TASK );
         } 

         //:END
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 
            //:IF mStudenC.Registration.Status = "W"    // Withdrawn
            if ( CompareAttributeToString( mStudenC, "Registration", "Status", "W" ) == 0 )
            { 
               //:TotalEntryFlag = "Y"
                {StringBuilder sb_TotalEntryFlag;
               if ( TotalEntryFlag == null )
                  sb_TotalEntryFlag = new StringBuilder( 32 );
               else
                  sb_TotalEntryFlag = new StringBuilder( TotalEntryFlag );
                              ZeidonStringCopy( sb_TotalEntryFlag, 1, 0, "Y", 1, 0, 2 );
               TotalEntryFlag = sb_TotalEntryFlag.toString( );}
            } 

            //:END
         } 

         //:END

         //:// Process each valid entry as determined above.
         //:IF TotalEntryFlag = "Y"
         if ( ZeidonStringCompare( TotalEntryFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
         { 
            //:// We'll only create the following info (including adding credits) for the types of records above.
            //:BuildClassScheduleAttr( mStudenC, szMeetingSchedule, "Registration" )
            {StringBuilder sb_szMeetingSchedule;
            if ( szMeetingSchedule == null )
               sb_szMeetingSchedule = new StringBuilder( 32 );
            else
               sb_szMeetingSchedule = new StringBuilder( szMeetingSchedule );
                         omStudenC_BuildClassScheduleAttr( mStudenC, sb_szMeetingSchedule, "Registration" );
            szMeetingSchedule = sb_szMeetingSchedule.toString( );}
            //:mStudenC.CurrentClass.wMeetingSchedule = szMeetingSchedule
            SetAttributeFromString( mStudenC, "CurrentClass", "wMeetingSchedule", szMeetingSchedule );
            //:mStudenC.CurrentClass.wStudentCreditHours = mStudenC.Registration.CreditHours
            SetAttributeFromAttribute( mStudenC, "CurrentClass", "wStudentCreditHours", mStudenC, "Registration", "CreditHours" );
            //:TotalTermCredits = TotalTermCredits + mStudenC.CurrentClass.wStudentCreditHours
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mStudenC, "CurrentClass", "wStudentCreditHours" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            TotalTermCredits = TotalTermCredits + dTempDecimal_0;
            //:TotalYearCredits = TotalYearCredits + mStudenC.CurrentClass.wStudentCreditHours
            {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                         GetDecimalFromAttribute( md_dTempDecimal_1, mStudenC, "CurrentClass", "wStudentCreditHours" );
            dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
            TotalYearCredits = TotalYearCredits + dTempDecimal_1;
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mStudenC, "RegistrationClass", "Student" );
      //:END
   } 

   //:END
   //:IF TotalTermCredits > 0
   if ( TotalTermCredits > 0 )
   { 
      //:// Create Final Totals entry.
      //:CREATE ENTITY mStudenC.CurrentScheduleEntry
      RESULT = CreateEntity( mStudenC, "CurrentScheduleEntry", zPOS_AFTER );
      //:CREATE ENTITY mStudenC.CurrentClass 
      RESULT = CreateEntity( mStudenC, "CurrentClass", zPOS_AFTER );
      //:CREATE ENTITY mStudenC.CurrentCourse 
      RESULT = CreateEntity( mStudenC, "CurrentCourse", zPOS_AFTER );
      //:mStudenC.CurrentCourse.Title = "Term Totals: "
      SetAttributeFromString( mStudenC, "CurrentCourse", "Title", "Term Totals: " );
      //:mStudenC.CurrentClass.wStudentCreditHours = TotalTermCredits
      SetAttributeFromDecimal( mStudenC, "CurrentClass", "wStudentCreditHours", TotalTermCredits );
   } 

   //:END
   //:mStudenC.Student.wTotalCreditsForYear = TotalYearCredits
   SetAttributeFromDecimal( mStudenC, "Student", "wTotalCreditsForYear", TotalYearCredits );

   //:// Add any waitlisted entries before Dropped entries at the end of entries for the Term.
   //:LastTermID = 0
   LastTermID = 0;
   //:FOR EACH mStudenC.StudentWaitlistedYear WITHIN mStudenC.Student  
   //:   WHERE mStudenC.StudentWaitlistedYear.ID = wXferO.StudentScheduleCollegeYear.ID 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, wXferO, "StudentScheduleCollegeYear", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mStudenC, "StudentWaitlistedYear", "ID", lTempInteger_1, "Student" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:IF mStudenC.StudentWaitlistedTerm.ID != LastTermID
      if ( CompareAttributeToInteger( mStudenC, "StudentWaitlistedTerm", "ID", LastTermID ) != 0 )
      { 
         //:SET CURSOR FIRST mStudenC.CurrentScheduleEntry 
         //:           WHERE mStudenC.CurrentScheduleEntry.YearSemester = mStudenC.StudentWaitlistedTerm.YearSemester 
         {StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetStringFromAttribute( sb_szTempString_1, mStudenC, "StudentWaitlistedTerm", "YearSemester" );
         szTempString_1 = sb_szTempString_1.toString( );}
         RESULT = SetCursorFirstEntityByString( mStudenC, "CurrentScheduleEntry", "YearSemester", szTempString_1, "" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:IssueError( mStudenC,0,0, "Couldn't position on Semester for waitlist." )
            IssueError( mStudenC, 0, 0, "Couldn't position on Semester for waitlist." );
         } 

         //:END
         //:LastTermID = mStudenC.StudentWaitlistedTerm.ID
         {MutableInt mi_LastTermID = new MutableInt( LastTermID );
                   GetIntegerFromAttribute( mi_LastTermID, mStudenC, "StudentWaitlistedTerm", "ID" );
         LastTermID = mi_LastTermID.intValue( );}

         //:// Add entries before any Dropped entries for this Term or at the end of entries for this Term.
         //:// Do this by starting at the next Term or at end of all Terms and backing up.
         //:SET CURSOR NEXT mStudenC.CurrentScheduleEntry WHERE mStudenC.CurrentScheduleEntry.YearSemester != ""
         RESULT = SetCursorNextEntity( mStudenC, "CurrentScheduleEntry", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mStudenC, "CurrentScheduleEntry", "YearSemester", "" ) == 0 ) )
            { 
               RESULT = SetCursorNextEntity( mStudenC, "CurrentScheduleEntry", "" );
            } 

         } 

         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:SET CURSOR LAST mStudenC.CurrentScheduleEntry  
            RESULT = SetCursorLastEntity( mStudenC, "CurrentScheduleEntry", "" );
            //:ELSE
         } 
         else
         { 
            //:SET CURSOR PREVIOUS mStudenC.CurrentScheduleEntry  
            RESULT = SetCursorPrevEntity( mStudenC, "CurrentScheduleEntry", "" );
         } 

         //:END
         //:FoundFlag = ""
          {StringBuilder sb_FoundFlag;
         if ( FoundFlag == null )
            sb_FoundFlag = new StringBuilder( 32 );
         else
            sb_FoundFlag = new StringBuilder( FoundFlag );
                  ZeidonStringCopy( sb_FoundFlag, 1, 0, "", 1, 0, 2 );
         FoundFlag = sb_FoundFlag.toString( );}
         //:nRC = zCURSOR_SET
         nRC = zCURSOR_SET;
         //:LOOP WHILE nRC >= zCURSOR_SET AND FoundFlag = ""
         while ( nRC >= zCURSOR_SET && ZeidonStringCompare( FoundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
         { 
            //:IF mStudenC.CurrentClass EXISTS 
            lTempInteger_2 = CheckExistenceOfEntity( mStudenC, "CurrentClass" );
            if ( lTempInteger_2 == 0 )
            { 
               //:IF mStudenC.CurrentClass.wStatus != "" AND 
               //:   mStudenC.CurrentClass.wStatus != "Dropped"
               if ( CompareAttributeToString( mStudenC, "CurrentClass", "wStatus", "" ) != 0 && CompareAttributeToString( mStudenC, "CurrentClass", "wStatus", "Dropped" ) != 0 )
               { 

                  //:FoundFlag = "Y"
                   {StringBuilder sb_FoundFlag;
                  if ( FoundFlag == null )
                     sb_FoundFlag = new StringBuilder( 32 );
                  else
                     sb_FoundFlag = new StringBuilder( FoundFlag );
                                    ZeidonStringCopy( sb_FoundFlag, 1, 0, "Y", 1, 0, 2 );
                  FoundFlag = sb_FoundFlag.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:nRC = SetCursorPrevEntity( mStudenC, "CurrentScheduleEntry", "" )
                  nRC = SetCursorPrevEntity( mStudenC, "CurrentScheduleEntry", "" );
               } 

               //:END
               //:ELSE 
            } 
            else
            { 
               //:IF mStudenC.CurrentScheduleEntry.YearSemester = ""
               if ( CompareAttributeToString( mStudenC, "CurrentScheduleEntry", "YearSemester", "" ) == 0 )
               { 
                  //:nRC = SetCursorPrevEntity( mStudenC, "CurrentScheduleEntry", "" )
                  nRC = SetCursorPrevEntity( mStudenC, "CurrentScheduleEntry", "" );
                  //:ELSE
               } 
               else
               { 
                  //:// We've backed up to the previous Term.
                  //:FoundFlag = "Y"
                   {StringBuilder sb_FoundFlag;
                  if ( FoundFlag == null )
                     sb_FoundFlag = new StringBuilder( 32 );
                  else
                     sb_FoundFlag = new StringBuilder( FoundFlag );
                                    ZeidonStringCopy( sb_FoundFlag, 1, 0, "Y", 1, 0, 2 );
                  FoundFlag = sb_FoundFlag.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:// Create the Class entry.
      //:CREATE ENTITY mStudenC.CurrentScheduleEntry
      RESULT = CreateEntity( mStudenC, "CurrentScheduleEntry", zPOS_AFTER );
      //:INCLUDE mStudenC.CurrentClass FROM mStudenC.StudentWaitlistedClass
      RESULT = IncludeSubobjectFromSubobject( mStudenC, "CurrentClass", mStudenC, "StudentWaitlistedClass", zPOS_AFTER );
      //:GetStringFromAttribute( szRanking, mStudenC, "StudentWaitlisted", "WaitListRanking" )
      {StringBuilder sb_szRanking;
      if ( szRanking == null )
         sb_szRanking = new StringBuilder( 32 );
      else
         sb_szRanking = new StringBuilder( szRanking );
             GetStringFromAttribute( sb_szRanking, mStudenC, "StudentWaitlisted", "WaitListRanking" );
      szRanking = sb_szRanking.toString( );}
      //:IF szRanking = ""
      if ( ZeidonStringCompare( szRanking, 1, 0, "", 1, 0, 21 ) == 0 )
      { 
         //:szExternalStatus = "Waitlisted"
          {StringBuilder sb_szExternalStatus;
         if ( szExternalStatus == null )
            sb_szExternalStatus = new StringBuilder( 32 );
         else
            sb_szExternalStatus = new StringBuilder( szExternalStatus );
                  ZeidonStringCopy( sb_szExternalStatus, 1, 0, "Waitlisted", 1, 0, 21 );
         szExternalStatus = sb_szExternalStatus.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szExternalStatus = "Waitlisted - " + szRanking
          {StringBuilder sb_szExternalStatus;
         if ( szExternalStatus == null )
            sb_szExternalStatus = new StringBuilder( 32 );
         else
            sb_szExternalStatus = new StringBuilder( szExternalStatus );
                  ZeidonStringCopy( sb_szExternalStatus, 1, 0, "Waitlisted - ", 1, 0, 21 );
         szExternalStatus = sb_szExternalStatus.toString( );}
          {StringBuilder sb_szExternalStatus;
         if ( szExternalStatus == null )
            sb_szExternalStatus = new StringBuilder( 32 );
         else
            sb_szExternalStatus = new StringBuilder( szExternalStatus );
                  ZeidonStringConcat( sb_szExternalStatus, 1, 0, szRanking, 1, 0, 21 );
         szExternalStatus = sb_szExternalStatus.toString( );}
      } 

      //:END
      //:mStudenC.CurrentClass.wStatus = szExternalStatus
      SetAttributeFromString( mStudenC, "CurrentClass", "wStatus", szExternalStatus );
      //:mStudenC.CurrentClass.wStudentCreditHours = mStudenC.CurrentClass.CreditHours
      SetAttributeFromAttribute( mStudenC, "CurrentClass", "wStudentCreditHours", mStudenC, "CurrentClass", "CreditHours" );
      //:mStudenC.CurrentClass.wCourseTitle   = mStudenC.StudentWaitlisted.wCourseTitle
      SetAttributeFromAttribute( mStudenC, "CurrentClass", "wCourseTitle", mStudenC, "StudentWaitlisted", "wCourseTitle" );
      //:mStudenC.CurrentClass.wCourseSection = mStudenC.StudentWaitlisted.dClassNumber 
      SetAttributeFromAttribute( mStudenC, "CurrentClass", "wCourseSection", mStudenC, "StudentWaitlisted", "dClassNumber" );
      RESULT = SetCursorNextEntityByInteger( mStudenC, "StudentWaitlistedYear", "ID", lTempInteger_1, "Student" );
   } 

   //:END

   //:DropView( lTermLST2 )
   DropView( lTermLST2 );
   //:DropNameForView( mStudenC, "mStudenCLoading", mStudenC, zLEVEL_TASK )
   DropNameForView( mStudenC, "mStudenCLoading", mStudenC, zLEVEL_TASK );
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:ValidateAddedCourses( VIEW mStudenC BASED ON LOD mStudenC,
//:                      VIEW lClsLstC BASED ON LOD lClsLstC )

//:   STRING ( 100 ) szMsg
public int 
omStudenC_ValidateAddedCourses( View     mStudenC,
                                View     lClsLstC )
{
   String   szMsg = null;
   //:STRING ( 50 )  szCourseNumber
   String   szCourseNumber = null;
   //:SHORT nRC
   int      nRC = 0;
   int      RESULT = 0;


   //:// Validate the selected Classes in lClsLstC for addition to mStudenC.
   //:FOR EACH lClsLstC.Class 
   RESULT = SetCursorFirstEntity( lClsLstC, "Class", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:nRC = GetSelectStateOfEntity( lClsLstC, "Class" )
      nRC = GetSelectStateOfEntity( lClsLstC, "Class" );
      //:IF nRC = 1
      if ( nRC == 1 )
      { 
         //:nRC = ValidateAddedCourse( mStudenC, lClsLstC )
         nRC = omStudenC_ValidateAddedCourse( mStudenC, lClsLstC );
         //:IF nRC < 0
         if ( nRC < 0 )
         { 
            //:RETURN nRC 
            if(8==8)return( nRC );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lClsLstC, "Class", "" );
      //:END 
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:SaveAcademicObject( VIEW mStudenC BASED ON LOD mStudenC )

//:   VIEW mStudent BASED ON LOD mStudent
public int 
omStudenC_SaveAcademicObject( View     mStudenC )
{
   zVIEW    mStudent = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;


   //:// On Save, remove and re-add the CurrentScheduleEntry subobject as it creates a problem on commit
   //:// over a network. Also position on EnrollmentModification entity, as the cursor is often null and
   //:// that creates a problem.
   //:// Also rerank Class waitlisted entries for any delete Student waitlisted entries.

   //:FOR EACH mStudenC.CurrentScheduleEntry 
   RESULT = SetCursorFirstEntity( mStudenC, "CurrentScheduleEntry", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mStudenC.CurrentScheduleEntry NONE  
      RESULT = DeleteEntity( mStudenC, "CurrentScheduleEntry", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mStudenC, "CurrentScheduleEntry", "" );
   } 

   //:END
   //:IF mStudenC.HS_SubjectAreaUpdate EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "HS_SubjectAreaUpdate" );
   if ( lTempInteger_0 == 0 )
   { 
      //:EXCLUDE mStudenC.HS_SubjectAreaUpdate   
      RESULT = ExcludeEntity( mStudenC, "HS_SubjectAreaUpdate", zREPOS_AFTER );
   } 

   //:END
   //:FOR EACH mStudenC.HS_RequiredGroup 
   RESULT = SetCursorFirstEntity( mStudenC, "HS_RequiredGroup", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:EXCLUDE mStudenC.HS_RequiredGroup NONE 
      RESULT = ExcludeEntity( mStudenC, "HS_RequiredGroup", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mStudenC, "HS_RequiredGroup", "" );
   } 

   //:END
   //:SET CURSOR FIRST mStudenC.EnrollmentModification  
   RESULT = SetCursorFirstEntity( mStudenC, "EnrollmentModification", "" );
   //:COMMIT mStudenC
   RESULT = CommitObjectInstance( mStudenC );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:RETURN -1 
      if(8==8)return( -1 );
   } 

   //:END
   //:RerankDeletedWaitlists( mStudenC )
   omStudenC_RerankDeletedWaitlists( mStudenC );
   //:BuildCurrentSchedule( mStudenC )
   omStudenC_BuildCurrentSchedule( mStudenC );

   //:// Go to generate the HS Required Groups derived subobjects for display purposes.
   //:GET VIEW mStudent NAMED "mStudent"
   RESULT = GetViewByName( mStudent, "mStudent", mStudenC, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:GenerateHS_ReqGroups( mStudenC, mStudent.Student.TestingStatus )
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, mStudent, "Student", "TestingStatus" );
      szTempString_0 = sb_szTempString_0.toString( );}
      omStudenC_GenerateHS_ReqGroups( mStudenC, szTempString_0 );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildRegistrationEntry( VIEW mStudenC BASED ON LOD mStudenC,
//:                        STRING ( 32 ) szRegistrationEntityName )

//:  STRING ( 254 ) szMeetingSchedule
public int 
omStudenC_BuildRegistrationEntry( View     mStudenC,
                                  String   szRegistrationEntityName )
{
   String   szMeetingSchedule = null;


   //:IF szRegistrationEntityName = "US_Registration"
   if ( ZeidonStringCompare( szRegistrationEntityName, 1, 0, "US_Registration", 1, 0, 33 ) == 0 )
   { 
      //:mStudenC.US_Registration.Status = "T"
      SetAttributeFromString( mStudenC, "US_Registration", "Status", "T" );
      //:mStudenC.US_Registration.CreditHours = mStudenC.US_Class.CreditHours 
      SetAttributeFromAttribute( mStudenC, "US_Registration", "CreditHours", mStudenC, "US_Class", "CreditHours" );
      //:BuildClassScheduleAttr( mStudenC, szMeetingSchedule, szRegistrationEntityName )
      {StringBuilder sb_szMeetingSchedule;
      if ( szMeetingSchedule == null )
         sb_szMeetingSchedule = new StringBuilder( 32 );
      else
         sb_szMeetingSchedule = new StringBuilder( szMeetingSchedule );
             omStudenC_BuildClassScheduleAttr( mStudenC, sb_szMeetingSchedule, szRegistrationEntityName );
      szMeetingSchedule = sb_szMeetingSchedule.toString( );}
      //:mStudenC.US_Class.wMeetingSchedule = szMeetingSchedule
      SetAttributeFromString( mStudenC, "US_Class", "wMeetingSchedule", szMeetingSchedule );
      //:IF mStudenC.US_Class.PassFailClass = "Y"
      if ( CompareAttributeToString( mStudenC, "US_Class", "PassFailClass", "Y" ) == 0 )
      { 
         //:mStudenC.US_Registration.TakingClassType = "P"  
         SetAttributeFromString( mStudenC, "US_Registration", "TakingClassType", "P" );
      } 

      //:END
      //:IF mStudenC.US_Class.HonorsFlag = "Y"
      if ( CompareAttributeToString( mStudenC, "US_Class", "HonorsFlag", "Y" ) == 0 )
      { 
         //:mStudenC.US_Registration.TakingClassType = "H" 
         SetAttributeFromString( mStudenC, "US_Registration", "TakingClassType", "H" );
      } 

      //:END 
      //:ELSE
   } 
   else
   { 
      //:mStudenC.Registration.Status = "T"
      SetAttributeFromString( mStudenC, "Registration", "Status", "T" );
      //:mStudenC.Registration.CreditHours = mStudenC.RegistrationClass.CreditHours 
      SetAttributeFromAttribute( mStudenC, "Registration", "CreditHours", mStudenC, "RegistrationClass", "CreditHours" );
      //:IF mStudenC.RegistrationClass.PassFailClass = "Y"
      if ( CompareAttributeToString( mStudenC, "RegistrationClass", "PassFailClass", "Y" ) == 0 )
      { 
         //:mStudenC.Registration.TakingClassType = "P"  
         SetAttributeFromString( mStudenC, "Registration", "TakingClassType", "P" );
      } 

      //:END
      //:IF mStudenC.RegistrationClass.HonorsFlag = "Y"
      if ( CompareAttributeToString( mStudenC, "RegistrationClass", "HonorsFlag", "Y" ) == 0 )
      { 
         //:mStudenC.Registration.TakingClassType = "H" 
         SetAttributeFromString( mStudenC, "Registration", "TakingClassType", "H" );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:UpdateScheduleEntries( VIEW mStudenC BASED ON LOD mStudenC )

//:   VIEW mStudenC2 BASED ON LOD  mStudenC
public int 
omStudenC_UpdateScheduleEntries( View     mStudenC )
{
   zVIEW    mStudenC2 = new zVIEW( );
   //:VIEW wXferO    REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mUser     BASED ON LOD  mUser
   zVIEW    mUser = new zVIEW( );
   //:VIEW lTermLST  REGISTERED AS lTermLST
   zVIEW    lTermLST = new zVIEW( );
   //:STRING ( 1 ) szModificationFlag
   String   szModificationFlag = null;
   //:STRING ( 1 ) szMidtermModificationFlag
   String   szMidtermModificationFlag = null;
   //:STRING ( 1 ) szOI_ModificationFlag
   String   szOI_ModificationFlag = null;
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

   RESULT = GetViewByName( wXferO, "wXferO", mStudenC, zLEVEL_TASK );
   RESULT = GetViewByName( lTermLST, "lTermLST", mStudenC, zLEVEL_TASK );

   //:// Update the Register entries from the work subobject, adding, deleting or updating as necessary.
   //:// Return 0 if no modifications were done and 1 if modifications were done.
   //:// Return -1 if there were validation errors.

   //:GetViewByName( mUser, "mUser", mStudenC, zLEVEL_APPLICATION )
   GetViewByName( mUser, "mUser", mStudenC, zLEVEL_APPLICATION );

   //:// First delete entries.
   //:szOI_ModificationFlag = ""
    {StringBuilder sb_szOI_ModificationFlag;
   if ( szOI_ModificationFlag == null )
      sb_szOI_ModificationFlag = new StringBuilder( 32 );
   else
      sb_szOI_ModificationFlag = new StringBuilder( szOI_ModificationFlag );
      ZeidonStringCopy( sb_szOI_ModificationFlag, 1, 0, "", 1, 0, 2 );
   szOI_ModificationFlag = sb_szOI_ModificationFlag.toString( );}
   //:FOR EACH mStudenC.US_RegistrationDeleted 
   RESULT = SetCursorFirstEntity( mStudenC, "US_RegistrationDeleted", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mStudenC.US_RegistrationDeleted.Status = "L"
      if ( CompareAttributeToString( mStudenC, "US_RegistrationDeleted", "Status", "L" ) == 0 )
      { 
         //:// Waitlisted entry
         //:SET CURSOR FIRST mStudenC.StudentWaitlistedClass WITHIN mStudenC.Student 
         //:           WHERE mStudenC.StudentWaitlistedClass.ID = mStudenC.US_RegistrationDeleted.ID 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mStudenC, "US_RegistrationDeleted", "ID" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mStudenC, "StudentWaitlistedClass", "ID", lTempInteger_0, "Student" );
         //:CREATE ENTITY mStudenC.DeletedWaitlistEntry 
         RESULT = CreateEntity( mStudenC, "DeletedWaitlistEntry", zPOS_AFTER );
         //:mStudenC.DeletedWaitlistEntry.ClassID = mStudenC.US_RegistrationDeleted.ID
         SetAttributeFromAttribute( mStudenC, "DeletedWaitlistEntry", "ClassID", mStudenC, "US_RegistrationDeleted", "ID" );
         //:DELETE ENTITY mStudenC.StudentWaitlisted NONE 
         RESULT = DeleteEntity( mStudenC, "StudentWaitlisted", zREPOS_NONE );
         //:szOI_ModificationFlag = "Y"
          {StringBuilder sb_szOI_ModificationFlag;
         if ( szOI_ModificationFlag == null )
            sb_szOI_ModificationFlag = new StringBuilder( 32 );
         else
            sb_szOI_ModificationFlag = new StringBuilder( szOI_ModificationFlag );
                  ZeidonStringCopy( sb_szOI_ModificationFlag, 1, 0, "Y", 1, 0, 2 );
         szOI_ModificationFlag = sb_szOI_ModificationFlag.toString( );}
         //:ELSE
      } 
      else
      { 
         //:// Regular entry
         //:CREATE ENTITY mStudenC.EnrollmentModification 
         RESULT = CreateEntity( mStudenC, "EnrollmentModification", zPOS_AFTER );
         //:INCLUDE mStudenC.User FROM mUser.User
         RESULT = IncludeSubobjectFromSubobject( mStudenC, "User", mUser, "User", zPOS_AFTER );
         //:mStudenC.EnrollmentModification.NewStatus    = "D"
         SetAttributeFromString( mStudenC, "EnrollmentModification", "NewStatus", "D" );
         //:mStudenC.EnrollmentModification.CourseNumber = mStudenC.US_DeletedModificationRecord.CourseNumber 
         SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "CourseNumber", mStudenC, "US_DeletedModificationRecord", "CourseNumber" );
         //:mStudenC.EnrollmentModification.YearSemester = mStudenC.US_DeletedModificationRecord.YearSemester 
         SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "YearSemester", mStudenC, "US_DeletedModificationRecord", "YearSemester" );
         //:mStudenC.EnrollmentModification.ModifiedDateTime = wXferO.Root.dCurrentDateTime
         SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "ModifiedDateTime", wXferO, "Root", "dCurrentDateTime" );
         //:SET CURSOR FIRST mStudenC.Registration 
         //:           WHERE mStudenC.Registration.ID = mStudenC.US_RegistrationDeleted.ID   
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                   GetIntegerFromAttribute( mi_lTempInteger_1, mStudenC, "US_RegistrationDeleted", "ID" );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mStudenC, "Registration", "ID", lTempInteger_1, "" );
         //:DELETE ENTITY mStudenC.Registration NONE 
         RESULT = DeleteEntity( mStudenC, "Registration", zREPOS_NONE );
         //:szOI_ModificationFlag = "Y"
          {StringBuilder sb_szOI_ModificationFlag;
         if ( szOI_ModificationFlag == null )
            sb_szOI_ModificationFlag = new StringBuilder( 32 );
         else
            sb_szOI_ModificationFlag = new StringBuilder( szOI_ModificationFlag );
                  ZeidonStringCopy( sb_szOI_ModificationFlag, 1, 0, "Y", 1, 0, 2 );
         szOI_ModificationFlag = sb_szOI_ModificationFlag.toString( );}
      } 

      RESULT = SetCursorNextEntity( mStudenC, "US_RegistrationDeleted", "" );
      //:END
   } 

   //:END

   //:// Next, add/update entries.
   //:FOR EACH mStudenC.US_Registration 
   RESULT = SetCursorFirstEntity( mStudenC, "US_Registration", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mStudenC.US_Class EXISTS
      lTempInteger_2 = CheckExistenceOfEntity( mStudenC, "US_Class" );
      if ( lTempInteger_2 == 0 )
      { 
         //:IF mStudenC.US_Registration.Status = "L"
         if ( CompareAttributeToString( mStudenC, "US_Registration", "Status", "L" ) == 0 )
         { 
            //:// If Original Status was "L", we do nothing.
            //:IF mStudenC.US_Registration.wOrigStatus != "L"
            if ( CompareAttributeToString( mStudenC, "US_Registration", "wOrigStatus", "L" ) != 0 )
            { 
               //:IF mStudenC.US_Registration.wOrigStatus != "" 
               if ( CompareAttributeToString( mStudenC, "US_Registration", "wOrigStatus", "" ) != 0 )
               { 
                  //:// The original entry was a regular enrollment, so delete it.
                  //:SET CURSOR FIRST mStudenC.Registration 
                  //:           WHERE mStudenC.Registration.ID = mStudenC.US_Registration.ID
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                                     GetIntegerFromAttribute( mi_lTempInteger_3, mStudenC, "US_Registration", "ID" );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mStudenC, "Registration", "ID", lTempInteger_3, "" );
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:DELETE ENTITY mStudenC.Registration NONE 
                     RESULT = DeleteEntity( mStudenC, "Registration", zREPOS_NONE );
                     //:ELSE
                  } 
                  else
                  { 
                     //:// To not find it is an error.
                     //:IssueError( mStudenC,0,0, "Programming Error 1 in Update Schedule" )
                     IssueError( mStudenC, 0, 0, "Programming Error 1 in Update Schedule" );
                  } 

                  //:END
               } 

               //:END
               //:// Add Waitlisted entry
               //:CREATE ENTITY mStudenC.StudentWaitlisted 
               RESULT = CreateEntity( mStudenC, "StudentWaitlisted", zPOS_AFTER );
               //:mStudenC.US_Registration.Status = "L"
               SetAttributeFromString( mStudenC, "US_Registration", "Status", "L" );
               //:SetAttributeFromCurrentDateTime( mStudenC, "StudentWaitlisted", "CreatedDateTime" )
               {
                ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mStudenC );
                m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mStudenC, "StudentWaitlisted", "CreatedDateTime" );
                // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
               }
               //:INCLUDE mStudenC.StudentWaitlistedClass FROM mStudenC.US_Class 
               RESULT = IncludeSubobjectFromSubobject( mStudenC, "StudentWaitlistedClass", mStudenC, "US_Class", zPOS_AFTER );
               //:szOI_ModificationFlag = "Y"
                {StringBuilder sb_szOI_ModificationFlag;
               if ( szOI_ModificationFlag == null )
                  sb_szOI_ModificationFlag = new StringBuilder( 32 );
               else
                  sb_szOI_ModificationFlag = new StringBuilder( szOI_ModificationFlag );
                              ZeidonStringCopy( sb_szOI_ModificationFlag, 1, 0, "Y", 1, 0, 2 );
               szOI_ModificationFlag = sb_szOI_ModificationFlag.toString( );}
            } 

            //:END
            //:   
            //:ELSE
         } 
         else
         { 
            //:// Regular entry
            //:IF mStudenC.US_Registration.ID = ""
            if ( CompareAttributeToString( mStudenC, "US_Registration", "ID", "" ) == 0 )
            { 
               //:CREATE ENTITY mStudenC.Registration 
               RESULT = CreateEntity( mStudenC, "Registration", zPOS_AFTER );
               //:SetMatchingAttributesByName( mStudenC, "Registration",
               //:                             mStudenC, "US_Registration", zSET_NOTNULL ) 
               SetMatchingAttributesByName( mStudenC, "Registration", mStudenC, "US_Registration", zSET_NOTNULL );
               //:INCLUDE mStudenC.RegistrationClass FROM mStudenC.US_Class 
               RESULT = IncludeSubobjectFromSubobject( mStudenC, "RegistrationClass", mStudenC, "US_Class", zPOS_AFTER );
               //:mStudenC.Registration.wCourseNumber = mStudenC.RegistrationCourse.Number 
               SetAttributeFromAttribute( mStudenC, "Registration", "wCourseNumber", mStudenC, "RegistrationCourse", "Number" );
               //:IF mStudenC.US_CrossListedCourse EXISTS
               lTempInteger_4 = CheckExistenceOfEntity( mStudenC, "US_CrossListedCourse" );
               if ( lTempInteger_4 == 0 )
               { 
                  //:INCLUDE mStudenC.CrossListedCourse FROM mStudenC.US_CrossListedCourse 
                  RESULT = IncludeSubobjectFromSubobject( mStudenC, "CrossListedCourse", mStudenC, "US_CrossListedCourse", zPOS_AFTER );
               } 

               //:END
               //:IF mStudenC.UpdateSchedule.CollegeTermID != "" AND
               //:   mStudenC.US_CollegeTerm DOES NOT EXIST
               lTempInteger_5 = CheckExistenceOfEntity( mStudenC, "US_CollegeTerm" );
               if ( CompareAttributeToString( mStudenC, "UpdateSchedule", "CollegeTermID", "" ) != 0 && lTempInteger_5 != 0 )
               { 
                  //:// This is the case where we are scheduling Classes for a Term but have selected
                  //:// a Class that is for a Cohort and not for a Term. In this case, we include the Term
                  //:// for the Scheduled Term.
                  //:SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.ID = mStudenC.UpdateSchedule.CollegeTermID 
                  {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                                     GetIntegerFromAttribute( mi_lTempInteger_6, mStudenC, "UpdateSchedule", "CollegeTermID" );
                  lTempInteger_6 = mi_lTempInteger_6.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( lTermLST, "CollegeTerm", "ID", lTempInteger_6, "" );
                  //:INCLUDE mStudenC.RegisteredCollegeTerm FROM lTermLST.CollegeTerm
                  RESULT = IncludeSubobjectFromSubobject( mStudenC, "RegisteredCollegeTerm", lTermLST, "CollegeTerm", zPOS_AFTER );
               } 

               //:END
               //:szOI_ModificationFlag = "Y"
                {StringBuilder sb_szOI_ModificationFlag;
               if ( szOI_ModificationFlag == null )
                  sb_szOI_ModificationFlag = new StringBuilder( 32 );
               else
                  sb_szOI_ModificationFlag = new StringBuilder( szOI_ModificationFlag );
                              ZeidonStringCopy( sb_szOI_ModificationFlag, 1, 0, "Y", 1, 0, 2 );
               szOI_ModificationFlag = sb_szOI_ModificationFlag.toString( );}
               //:ELSE
            } 
            else
            { 
               //:SET CURSOR FIRST mStudenC.Registration 
               //:           WHERE mStudenC.Registration.ID = mStudenC.US_Registration.ID   
               {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                               GetIntegerFromAttribute( mi_lTempInteger_7, mStudenC, "US_Registration", "ID" );
               lTempInteger_7 = mi_lTempInteger_7.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mStudenC, "Registration", "ID", lTempInteger_7, "" );
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:SetMatchingAttributesByName( mStudenC, "Registration",
                  //:                             mStudenC, "US_Registration", zSET_NOTNULL )
                  SetMatchingAttributesByName( mStudenC, "Registration", mStudenC, "US_Registration", zSET_NOTNULL );
                  //:ELSE
               } 
               else
               { 
                  //:SET CURSOR FIRST mStudenC.StudentWaitlistedClass WITHIN mStudenC.Student  
                  //:           WHERE mStudenC.StudentWaitlistedClass.ID = mStudenC.US_Registration.ID  
                  {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                                     GetIntegerFromAttribute( mi_lTempInteger_8, mStudenC, "US_Registration", "ID" );
                  lTempInteger_8 = mi_lTempInteger_8.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mStudenC, "StudentWaitlistedClass", "ID", lTempInteger_8, "Student" );
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:DELETE ENTITY mStudenC.StudentWaitlisted 
                     RESULT = DeleteEntity( mStudenC, "StudentWaitlisted", zPOS_NEXT );
                     //:// Following code is the same as Regular entry above.
                     //:CREATE ENTITY mStudenC.Registration 
                     RESULT = CreateEntity( mStudenC, "Registration", zPOS_AFTER );
                     //:SetMatchingAttributesByName( mStudenC, "Registration",
                     //:                             mStudenC, "US_Registration", zSET_NOTNULL ) 
                     SetMatchingAttributesByName( mStudenC, "Registration", mStudenC, "US_Registration", zSET_NOTNULL );
                     //:INCLUDE mStudenC.RegistrationClass FROM mStudenC.US_Class 
                     RESULT = IncludeSubobjectFromSubobject( mStudenC, "RegistrationClass", mStudenC, "US_Class", zPOS_AFTER );
                     //:mStudenC.Registration.wCourseNumber = mStudenC.RegistrationCourse.Number 
                     SetAttributeFromAttribute( mStudenC, "Registration", "wCourseNumber", mStudenC, "RegistrationCourse", "Number" );
                     //:szOI_ModificationFlag = "Y"
                      {StringBuilder sb_szOI_ModificationFlag;
                     if ( szOI_ModificationFlag == null )
                        sb_szOI_ModificationFlag = new StringBuilder( 32 );
                     else
                        sb_szOI_ModificationFlag = new StringBuilder( szOI_ModificationFlag );
                                          ZeidonStringCopy( sb_szOI_ModificationFlag, 1, 0, "Y", 1, 0, 2 );
                     szOI_ModificationFlag = sb_szOI_ModificationFlag.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:// To not find it is an error.
                     //:IssueError( mStudenC,0,0, "Programming Error 2 in Update Schedule" )
                     IssueError( mStudenC, 0, 0, "Programming Error 2 in Update Schedule" );
                  } 

                  //:END
               } 

               //:END

               //:// Cross Listed Entry
               //:IF mStudenC.US_CrossListedCourse EXISTS
               lTempInteger_9 = CheckExistenceOfEntity( mStudenC, "US_CrossListedCourse" );
               if ( lTempInteger_9 == 0 )
               { 
                  //:// Include the entry, unless it already exists.
                  //:IF mStudenC.CrossListedCourse EXISTS
                  lTempInteger_10 = CheckExistenceOfEntity( mStudenC, "CrossListedCourse" );
                  if ( lTempInteger_10 == 0 )
                  { 
                     //:IF mStudenC.US_CrossListedCourse.ID != mStudenC.CrossListedCourse.ID
                     if ( CompareAttributeToAttribute( mStudenC, "US_CrossListedCourse", "ID", mStudenC, "CrossListedCourse", "ID" ) != 0 )
                     { 
                        //:EXCLUDE mStudenC.CrossListedCourse 
                        RESULT = ExcludeEntity( mStudenC, "CrossListedCourse", zREPOS_AFTER );
                        //:INCLUDE mStudenC.CrossListedCourse FROM mStudenC.US_CrossListedCourse
                        RESULT = IncludeSubobjectFromSubobject( mStudenC, "CrossListedCourse", mStudenC, "US_CrossListedCourse", zPOS_AFTER );
                        //:szOI_ModificationFlag = "Y"
                         {StringBuilder sb_szOI_ModificationFlag;
                        if ( szOI_ModificationFlag == null )
                           sb_szOI_ModificationFlag = new StringBuilder( 32 );
                        else
                           sb_szOI_ModificationFlag = new StringBuilder( szOI_ModificationFlag );
                                                ZeidonStringCopy( sb_szOI_ModificationFlag, 1, 0, "Y", 1, 0, 2 );
                        szOI_ModificationFlag = sb_szOI_ModificationFlag.toString( );}
                     } 

                     //:END
                     //:ELSE
                  } 
                  else
                  { 
                     //:INCLUDE mStudenC.CrossListedCourse FROM mStudenC.US_CrossListedCourse
                     RESULT = IncludeSubobjectFromSubobject( mStudenC, "CrossListedCourse", mStudenC, "US_CrossListedCourse", zPOS_AFTER );
                     //:szOI_ModificationFlag = "Y"
                      {StringBuilder sb_szOI_ModificationFlag;
                     if ( szOI_ModificationFlag == null )
                        sb_szOI_ModificationFlag = new StringBuilder( 32 );
                     else
                        sb_szOI_ModificationFlag = new StringBuilder( szOI_ModificationFlag );
                                          ZeidonStringCopy( sb_szOI_ModificationFlag, 1, 0, "Y", 1, 0, 2 );
                     szOI_ModificationFlag = sb_szOI_ModificationFlag.toString( );}
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:// Remove any existing entry.
                  //:IF mStudenC.CrossListedCourse EXISTS
                  lTempInteger_11 = CheckExistenceOfEntity( mStudenC, "CrossListedCourse" );
                  if ( lTempInteger_11 == 0 )
                  { 
                     //:EXCLUDE mStudenC.CrossListedCourse
                     RESULT = ExcludeEntity( mStudenC, "CrossListedCourse", zREPOS_AFTER );
                     //:szOI_ModificationFlag = "Y" 
                      {StringBuilder sb_szOI_ModificationFlag;
                     if ( szOI_ModificationFlag == null )
                        sb_szOI_ModificationFlag = new StringBuilder( 32 );
                     else
                        sb_szOI_ModificationFlag = new StringBuilder( szOI_ModificationFlag );
                                          ZeidonStringCopy( sb_szOI_ModificationFlag, 1, 0, "Y", 1, 0, 2 );
                     szOI_ModificationFlag = sb_szOI_ModificationFlag.toString( );}
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mStudenC, "US_Registration", "" );
      //:END
   } 

   //:END

   //:// Check if Manually Repeated Registration entries have been modified. In the dialog, any changes in those entries
   //:// resulted in the modification flag being set for the Registration entries.
   //:// First remove any necessary entries.
   //:SET CURSOR FIRST mStudenC.Registration WHERE mStudenC.Registration.wUpdateRegistrationModifiedFlag = "Y"
   RESULT = SetCursorFirstEntityByString( mStudenC, "Registration", "wUpdateRegistrationModifiedFlag", "Y", "" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:szOI_ModificationFlag = "Y"
       {StringBuilder sb_szOI_ModificationFlag;
      if ( szOI_ModificationFlag == null )
         sb_szOI_ModificationFlag = new StringBuilder( 32 );
      else
         sb_szOI_ModificationFlag = new StringBuilder( szOI_ModificationFlag );
            ZeidonStringCopy( sb_szOI_ModificationFlag, 1, 0, "Y", 1, 0, 2 );
      szOI_ModificationFlag = sb_szOI_ModificationFlag.toString( );}
   } 

   //:END 
   //: 
   //:// Create schedule change entries.
   //:FOR EACH mStudenC.US_Registration 
   RESULT = SetCursorFirstEntity( mStudenC, "US_Registration", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mStudenC.US_Class EXISTS
      lTempInteger_12 = CheckExistenceOfEntity( mStudenC, "US_Class" );
      if ( lTempInteger_12 == 0 )
      { 
         //:IF mStudenC.US_Registration.Status = "L"
         if ( CompareAttributeToString( mStudenC, "US_Registration", "Status", "L" ) == 0 )
         { 
            //:// Waitlisted entry: We will make no change entry.
            //:ELSE
         } 
         else
         { 
            //:// Regular entry
            //:CreateTemporalEntity( mStudenC, "EnrollmentModification", zPOS_AFTER )
            CreateTemporalEntity( mStudenC, "EnrollmentModification", zPOS_AFTER );
            //:szModificationFlag = ""
             {StringBuilder sb_szModificationFlag;
            if ( szModificationFlag == null )
               sb_szModificationFlag = new StringBuilder( 32 );
            else
               sb_szModificationFlag = new StringBuilder( szModificationFlag );
                        ZeidonStringCopy( sb_szModificationFlag, 1, 0, "", 1, 0, 2 );
            szModificationFlag = sb_szModificationFlag.toString( );}
            //:IF mStudenC.US_Registration.wOrigStatus != mStudenC.US_Registration.Status 
            if ( CompareAttributeToAttribute( mStudenC, "US_Registration", "wOrigStatus", mStudenC, "US_Registration", "Status" ) != 0 )
            { 
               //:mStudenC.EnrollmentModification.OriginalStatus = mStudenC.US_Registration.wOrigStatus 
               SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "OriginalStatus", mStudenC, "US_Registration", "wOrigStatus" );
               //:mStudenC.EnrollmentModification.NewStatus      = mStudenC.US_Registration.Status 
               SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "NewStatus", mStudenC, "US_Registration", "Status" );
               //:szModificationFlag = "Y"
                {StringBuilder sb_szModificationFlag;
               if ( szModificationFlag == null )
                  sb_szModificationFlag = new StringBuilder( 32 );
               else
                  sb_szModificationFlag = new StringBuilder( szModificationFlag );
                              ZeidonStringCopy( sb_szModificationFlag, 1, 0, "Y", 1, 0, 2 );
               szModificationFlag = sb_szModificationFlag.toString( );}
            } 

            //:END
            //:IF mStudenC.US_Registration.wOrigFinalGrade != mStudenC.US_Registration.FinalGrade 
            if ( CompareAttributeToAttribute( mStudenC, "US_Registration", "wOrigFinalGrade", mStudenC, "US_Registration", "FinalGrade" ) != 0 )
            { 
               //:mStudenC.EnrollmentModification.OriginalFinalGrade = mStudenC.US_Registration.wOrigFinalGrade 
               SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "OriginalFinalGrade", mStudenC, "US_Registration", "wOrigFinalGrade" );
               //:mStudenC.EnrollmentModification.NewFinalGrade      = mStudenC.US_Registration.FinalGrade 
               SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "NewFinalGrade", mStudenC, "US_Registration", "FinalGrade" );
               //:mStudenC.EnrollmentModification.ReasonForGradeChange = mStudenC.US_Registration.wReasonForGradeChange 
               SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "ReasonForGradeChange", mStudenC, "US_Registration", "wReasonForGradeChange" );

               //:szModificationFlag = "Y"
                {StringBuilder sb_szModificationFlag;
               if ( szModificationFlag == null )
                  sb_szModificationFlag = new StringBuilder( 32 );
               else
                  sb_szModificationFlag = new StringBuilder( szModificationFlag );
                              ZeidonStringCopy( sb_szModificationFlag, 1, 0, "Y", 1, 0, 2 );
               szModificationFlag = sb_szModificationFlag.toString( );}
            } 

            //:END
            //:IF mStudenC.US_Registration.wOrigTakingClassType != mStudenC.US_Registration.TakingClassType 
            if ( CompareAttributeToAttribute( mStudenC, "US_Registration", "wOrigTakingClassType", mStudenC, "US_Registration", "TakingClassType" ) != 0 )
            { 
               //:mStudenC.EnrollmentModification.OriginalTakingClassType = mStudenC.US_Registration.wOrigTakingClassType 
               SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "OriginalTakingClassType", mStudenC, "US_Registration", "wOrigTakingClassType" );
               //:mStudenC.EnrollmentModification.NewTakingClassType      = mStudenC.US_Registration.TakingClassType 
               SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "NewTakingClassType", mStudenC, "US_Registration", "TakingClassType" );

               //:szModificationFlag = "Y"
                {StringBuilder sb_szModificationFlag;
               if ( szModificationFlag == null )
                  sb_szModificationFlag = new StringBuilder( 32 );
               else
                  sb_szModificationFlag = new StringBuilder( szModificationFlag );
                              ZeidonStringCopy( sb_szModificationFlag, 1, 0, "Y", 1, 0, 2 );
               szModificationFlag = sb_szModificationFlag.toString( );}
            } 

            //:END
            //:IF mStudenC.US_Registration.wOrigCreditHours != mStudenC.US_Registration.CreditHours 
            if ( CompareAttributeToAttribute( mStudenC, "US_Registration", "wOrigCreditHours", mStudenC, "US_Registration", "CreditHours" ) != 0 )
            { 
               //:mStudenC.EnrollmentModification.OriginalCreditHours = mStudenC.US_Registration.wOrigCreditHours 
               SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "OriginalCreditHours", mStudenC, "US_Registration", "wOrigCreditHours" );
               //:mStudenC.EnrollmentModification.NewCreditHours      = mStudenC.US_Registration.CreditHours 
               SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "NewCreditHours", mStudenC, "US_Registration", "CreditHours" );
               //:szModificationFlag = "Y"
                {StringBuilder sb_szModificationFlag;
               if ( szModificationFlag == null )
                  sb_szModificationFlag = new StringBuilder( 32 );
               else
                  sb_szModificationFlag = new StringBuilder( szModificationFlag );
                              ZeidonStringCopy( sb_szModificationFlag, 1, 0, "Y", 1, 0, 2 );
               szModificationFlag = sb_szModificationFlag.toString( );}
            } 

            //:END
            //:IF mStudenC.US_Registration.wOrigMidtermGrade != mStudenC.US_Registration.MidtermGrade 
            if ( CompareAttributeToAttribute( mStudenC, "US_Registration", "wOrigMidtermGrade", mStudenC, "US_Registration", "MidtermGrade" ) != 0 )
            { 
               //:// We won't create an EnrollmentModification entry for Midterm Grade, but we will set the update flag.
               //:szMidtermModificationFlag = "Y"
                {StringBuilder sb_szMidtermModificationFlag;
               if ( szMidtermModificationFlag == null )
                  sb_szMidtermModificationFlag = new StringBuilder( 32 );
               else
                  sb_szMidtermModificationFlag = new StringBuilder( szMidtermModificationFlag );
                              ZeidonStringCopy( sb_szMidtermModificationFlag, 1, 0, "Y", 1, 0, 2 );
               szMidtermModificationFlag = sb_szMidtermModificationFlag.toString( );}
            } 

            //:END
            //:IF mStudenC.US_Registration.wOrigGradUndergradOverrideFlag != mStudenC.US_Registration.GradUndergradOverrideFlag
            if ( CompareAttributeToAttribute( mStudenC, "US_Registration", "wOrigGradUndergradOverrideFlag", mStudenC, "US_Registration", "GradUndergradOverrideFlag" ) != 0 )
            { 
               //:// We won't create an EnrollmentModification entry for Grad/Undergrad Override, but we will set the update flag.
               //:szMidtermModificationFlag = "Y"
                {StringBuilder sb_szMidtermModificationFlag;
               if ( szMidtermModificationFlag == null )
                  sb_szMidtermModificationFlag = new StringBuilder( 32 );
               else
                  sb_szMidtermModificationFlag = new StringBuilder( szMidtermModificationFlag );
                              ZeidonStringCopy( sb_szMidtermModificationFlag, 1, 0, "Y", 1, 0, 2 );
               szMidtermModificationFlag = sb_szMidtermModificationFlag.toString( );}
            } 

            //:END
            //:IF mStudenC.US_Registration.wOrigDroppedDate != mStudenC.US_Registration.DroppedDate 
            if ( CompareAttributeToAttribute( mStudenC, "US_Registration", "wOrigDroppedDate", mStudenC, "US_Registration", "DroppedDate" ) != 0 )
            { 
               //:// We won't create an EnrollmentModification entry for Dropped Date change, but we will set the update flag.
               //:szMidtermModificationFlag = "Y"
                {StringBuilder sb_szMidtermModificationFlag;
               if ( szMidtermModificationFlag == null )
                  sb_szMidtermModificationFlag = new StringBuilder( 32 );
               else
                  sb_szMidtermModificationFlag = new StringBuilder( szMidtermModificationFlag );
                              ZeidonStringCopy( sb_szMidtermModificationFlag, 1, 0, "Y", 1, 0, 2 );
               szMidtermModificationFlag = sb_szMidtermModificationFlag.toString( );}
            } 

            //:END
            //:IF szModificationFlag = "Y"
            if ( ZeidonStringCompare( szModificationFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
            { 
               //:szOI_ModificationFlag = "Y"
                {StringBuilder sb_szOI_ModificationFlag;
               if ( szOI_ModificationFlag == null )
                  sb_szOI_ModificationFlag = new StringBuilder( 32 );
               else
                  sb_szOI_ModificationFlag = new StringBuilder( szOI_ModificationFlag );
                              ZeidonStringCopy( sb_szOI_ModificationFlag, 1, 0, "Y", 1, 0, 2 );
               szOI_ModificationFlag = sb_szOI_ModificationFlag.toString( );}
               //:INCLUDE mStudenC.User FROM mUser.User
               RESULT = IncludeSubobjectFromSubobject( mStudenC, "User", mUser, "User", zPOS_AFTER );
               //:AcceptSubobject( mStudenC, "EnrollmentModification" )
               AcceptSubobject( mStudenC, "EnrollmentModification" );
               //:mStudenC.EnrollmentModification.CourseNumber     = mStudenC.US_Course.Number 
               SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "CourseNumber", mStudenC, "US_Course", "Number" );
               //:mStudenC.EnrollmentModification.ModifiedDateTime = wXferO.Root.dCurrentDateTime 
               SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "ModifiedDateTime", wXferO, "Root", "dCurrentDateTime" );
               //:IF mStudenC.US_CollegeTerm EXISTS
               lTempInteger_13 = CheckExistenceOfEntity( mStudenC, "US_CollegeTerm" );
               if ( lTempInteger_13 == 0 )
               { 
                  //:mStudenC.EnrollmentModification.YearSemester = mStudenC.US_CollegeTerm.YearSemester
                  SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "YearSemester", mStudenC, "US_CollegeTerm", "YearSemester" );
                  //:ELSE
               } 
               else
               { 
                  //:IF mStudenC.US_ClassCohort EXISTS
                  lTempInteger_14 = CheckExistenceOfEntity( mStudenC, "US_ClassCohort" );
                  if ( lTempInteger_14 == 0 )
                  { 
                     //:mStudenC.EnrollmentModification.YearSemester = mStudenC.US_ClassCohort.Name
                     SetAttributeFromAttribute( mStudenC, "EnrollmentModification", "YearSemester", mStudenC, "US_ClassCohort", "Name" );
                  } 

                  //:END
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:CancelSubobject( mStudenC, "EnrollmentModification" )
               CancelSubobject( mStudenC, "EnrollmentModification" );
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mStudenC, "US_Registration", "" );
      //:END
   } 

   //:END

   //:IF szOI_ModificationFlag = "Y" OR szMidtermModificationFlag = "Y"
   if ( ZeidonStringCompare( szOI_ModificationFlag, 1, 0, "Y", 1, 0, 2 ) == 0 || ZeidonStringCompare( szMidtermModificationFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:RETURN 1
      if(8==8)return( 1 );
      //:ELSE
   } 
   else
   { 
      //:RETURN 0
      if(8==8)return( 0 );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:RerankDeletedWaitlists( VIEW mStudenC BASED ON LOD mStudenC )

//:   VIEW mClassSt BASED ON LOD mClassSt
public int 
omStudenC_RerankDeletedWaitlists( View     mStudenC )
{
   zVIEW    mClassSt = new zVIEW( );
   //:INTEGER Count
   int      Count = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:// Rerank the waitlist entries within Classes for the deleted waitlisted entries within the Student.
   //:FOR EACH mStudenC.DeletedWaitlistEntry 
   RESULT = SetCursorFirstEntity( mStudenC, "DeletedWaitlistEntry", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:ACTIVATE mClassSt WHERE mClassSt.Class.ID = mStudenC.DeletedWaitlistEntry.ClassID
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, mStudenC, "DeletedWaitlistEntry", "ClassID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      //:        RESTRICTING mClassSt.Transaction TO mClassSt.Transaction.ID = 0
      omStudenC_fnLocalBuildQual_0( mStudenC, vTempViewVar_0, lTempInteger_0 );
      RESULT = ActivateObjectInstance( mClassSt, "mClassSt", mStudenC, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:OrderEntityForView( mClassSt, "StudentWaitlisted", "WaitListRanking A CreatedDateTime A" )
      OrderEntityForView( mClassSt, "StudentWaitlisted", "WaitListRanking A CreatedDateTime A" );
      //:Count = 0
      Count = 0;
      //:FOR EACH mClassSt.StudentWaitlisted 
      RESULT = SetCursorFirstEntity( mClassSt, "StudentWaitlisted", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:Count = Count + 1
         Count = Count + 1;
         //:mClassSt.StudentWaitlisted.WaitListRanking = Count 
         SetAttributeFromInteger( mClassSt, "StudentWaitlisted", "WaitListRanking", Count );
         RESULT = SetCursorNextEntity( mClassSt, "StudentWaitlisted", "" );
      } 

      //:END
      //:COMMIT mClassSt
      RESULT = CommitObjectInstance( mClassSt );
      //:DropObjectInstance( mClassSt )
      DropObjectInstance( mClassSt );
      RESULT = SetCursorNextEntity( mStudenC, "DeletedWaitlistEntry", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omStudenC_OrderRegistrationRecs( View     mStudenC )
{
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   //:OrderRegistrationRecs( VIEW mStudenC BASED ON LOD mStudenC )

   //:// Create a default ordering of the Registration entities so that the schedules will come out in Course
   //:// Number order within the Semester within the Year.
   //:// Build work status sort order so that, within a Term, Completed, Pre-Enrolled and Enrolled show first,
   //:// Waitlisted second and Dropped third.
   //:FOR EACH mStudenC.RegistrationClass WITHIN mStudenC.Student 
   RESULT = SetCursorFirstEntity( mStudenC, "RegistrationClass", "Student" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// Set Status sort attribute.
      //:IF mStudenC.Registration.Status = "C"
      if ( CompareAttributeToString( mStudenC, "Registration", "Status", "C" ) == 0 )
      { 
         //:mStudenC.Registration.wStatusSortOrder = 1
         SetAttributeFromInteger( mStudenC, "Registration", "wStatusSortOrder", 1 );
         //:ELSE 
      } 
      else
      { 
         //:IF mStudenC.Registration.Status = "R"
         if ( CompareAttributeToString( mStudenC, "Registration", "Status", "R" ) == 0 )
         { 
            //:mStudenC.Registration.wStatusSortOrder = 2
            SetAttributeFromInteger( mStudenC, "Registration", "wStatusSortOrder", 2 );
            //:ELSE
         } 
         else
         { 
            //:IF mStudenC.Registration.Status = "T"
            if ( CompareAttributeToString( mStudenC, "Registration", "Status", "T" ) == 0 )
            { 
               //:mStudenC.Registration.wStatusSortOrder = 3
               SetAttributeFromInteger( mStudenC, "Registration", "wStatusSortOrder", 3 );
               //:ELSE
            } 
            else
            { 
               //:IF mStudenC.Registration.Status = "D"
               if ( CompareAttributeToString( mStudenC, "Registration", "Status", "D" ) == 0 )
               { 
                  //:mStudenC.Registration.wStatusSortOrder = 4
                  SetAttributeFromInteger( mStudenC, "Registration", "wStatusSortOrder", 4 );
               } 

               //:END 
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:// Set Semester and Year sort attribute.
      //:IF mStudenC.RegistrationClassCollegeTerm EXISTS 
      lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "RegistrationClassCollegeTerm" );
      if ( lTempInteger_0 == 0 )
      { 
         //:mStudenC.Registration.wCollegeYear     = mStudenC.RegistrationClassCollegeYear.Year 
         SetAttributeFromAttribute( mStudenC, "Registration", "wCollegeYear", mStudenC, "RegistrationClassCollegeYear", "Year" );
         //:mStudenC.Registration.wCollegeSemester = mStudenC.RegistrationClassCollegeTerm.Semester 
         SetAttributeFromAttribute( mStudenC, "Registration", "wCollegeSemester", mStudenC, "RegistrationClassCollegeTerm", "Semester" );
         //:ELSE
      } 
      else
      { 
         //:IF mStudenC.RegisteredCollegeTerm EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mStudenC, "RegisteredCollegeTerm" );
         if ( lTempInteger_1 == 0 )
         { 
            //:mStudenC.Registration.wCollegeYear     = mStudenC.RegisteredCollegeYear.Year 
            SetAttributeFromAttribute( mStudenC, "Registration", "wCollegeYear", mStudenC, "RegisteredCollegeYear", "Year" );
            //:mStudenC.Registration.wCollegeSemester = mStudenC.RegisteredCollegeTerm.Semester
            SetAttributeFromAttribute( mStudenC, "Registration", "wCollegeSemester", mStudenC, "RegisteredCollegeTerm", "Semester" );
            //:ELSE
         } 
         else
         { 
            //:IF mStudenC.TransferRecordYear EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mStudenC, "TransferRecordYear" );
            if ( lTempInteger_2 == 0 )
            { 
               //:mStudenC.Registration.wCollegeYear     = mStudenC.TransferRecordYear.Year 
               SetAttributeFromAttribute( mStudenC, "Registration", "wCollegeYear", mStudenC, "TransferRecordYear", "Year" );
               //:mStudenC.Registration.wCollegeSemester = mStudenC.TransferRecordTerm.Semester
               SetAttributeFromAttribute( mStudenC, "Registration", "wCollegeSemester", mStudenC, "TransferRecordTerm", "Semester" );
               //:ELSE
            } 
            else
            { 
               //:mStudenC.Registration.wCollegeYear     = "" 
               SetAttributeFromString( mStudenC, "Registration", "wCollegeYear", "" );
               //:mStudenC.Registration.wCollegeSemester = ""
               SetAttributeFromString( mStudenC, "Registration", "wCollegeSemester", "" );
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mStudenC, "RegistrationClass", "Student" );
      //:END
   } 

   //:END
   //:OrderEntityForView( mStudenC, "Registration", 
   //:                    "wCollegeYear A wCollegeSemester A wStatusSortOrder A wCourseNumber A" )
   OrderEntityForView( mStudenC, "Registration", "wCollegeYear A wCollegeSemester A wStatusSortOrder A wCourseNumber A" );
   return( 0 );
//    
// END
} 


//:ENTITY CONSTRAINT OPERATION
//:US_Registration( VIEW mStudenC BASED ON LOD mStudenC,
//:                 STRING ( 32 ) NameOfEntity,
//:                 SHORT Event,
//:                 SHORT State )
//:   SHORT nRC
public int 
omStudenC_US_Registration( View     mStudenC,
                           String   NameOfEntity,
                           Integer   Event,
                           Integer   State )
{
   int      nRC = 0;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;


   //:CASE Event
   switch( Event )
   { 
      //:OF   zECE_ACCEPT:
      case zECE_ACCEPT :

         //:// Validate US_Registration entries.
         //:IF mStudenC.US_Registration.Status = "C" AND
         //:mStudenC.US_Registration.FinalGrade = "" AND 
         //:mStudenC.US_Registration.TakingClassType != "A"
         if ( CompareAttributeToString( mStudenC, "US_Registration", "Status", "C" ) == 0 && CompareAttributeToString( mStudenC, "US_Registration", "FinalGrade", "" ) == 0 &&
              CompareAttributeToString( mStudenC, "US_Registration", "TakingClassType", "A" ) != 0 )
         { 

            //:szMsg = "Status should not be set to 'Completed' if no Final Grade has been set." + NEW_LINE +
            //:  "Do you want to continue anyway?"
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringCopy( sb_szMsg, 1, 0, "Status should not be set to 'Completed' if no Final Grade has been set.", 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, "Do you want to continue anyway?", 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
            //:nRC = MessagePrompt( mStudenC, "", "Registration Entry Update",
            //:               szMsg,
            //:               0, zBUTTONS_YESNO, zRESPONSE_NO, 0 )
            nRC = MessagePrompt( mStudenC, "", "Registration Entry Update", szMsg, 0, zBUTTONS_YESNO, zRESPONSE_NO, 0 );
            //:IF nRC = zRESPONSE_NO
            if ( nRC == zRESPONSE_NO )
            { 
               //:RETURN -1
               if(8==8)return( -1 );
            } 

            //:END
         } 

         //:END

         //:// For audited courses, set the Credit Hours to null.
         //:IF mStudenC.US_Registration.TakingClassType = "A" AND mStudenC.US_Registration.CreditHours != ""
         if ( CompareAttributeToString( mStudenC, "US_Registration", "TakingClassType", "A" ) == 0 && CompareAttributeToString( mStudenC, "US_Registration", "CreditHours", "" ) != 0 )
         { 
            //:mStudenC.US_Registration.CreditHours = ""
            SetAttributeFromString( mStudenC, "US_Registration", "CreditHours", "" );
            //:MessageSend( mStudenC, "", "Registration Validation",
            //:       "Credit Hours for audited Class are being set to null.",
            //:       zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
            MessageSend( mStudenC, "", "Registration Validation", "Credit Hours for audited Class are being set to null.", zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
         } 

         //:END
         break ;

      //:  /* end zECE_ACCEPT */
      //:OF   zECE_CANCEL:
      case zECE_CANCEL :
         break ;

      //:  /* end zECE_CANCEL */
      //:OF   zECE_CREATE:
      case zECE_CREATE :
         break ;

      //:  /* end zECE_CREATE */
      //:OF   zECE_DELETE:
      case zECE_DELETE :
         break ;

      //:  /* end zECE_DELETE */
      //:OF   zECE_EXCLUDE:
      case zECE_EXCLUDE :
         break ;

      //:  /* end zECE_EXCLUDE */
      //:OF   zECE_INCLUDE:
      case zECE_INCLUDE :
         break ;
   } 


   //:     /* end zECE_INCLUDE */
   //:END  /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildUS_RegEntries( VIEW mStudenC BASED ON LOD mStudenC,
//:                    INTEGER CollegeTermID )

//:   VIEW lTermLST  REGISTERED AS lTermLST
public int 
omStudenC_BuildUS_RegEntries( View     mStudenC,
                              int      CollegeTermID )
{
   zVIEW    lTermLST = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 254 ) szMeetingSchedule
   String   szMeetingSchedule = null;
   //:DECIMAL TotalCredits
   double  TotalCredits = 0.0;
   //:INTEGER ClassCollegeTermID
   int      ClassCollegeTermID = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_4 = 0;
   double  dTempDecimal_1 = 0.0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;

   RESULT = GetViewByName( lTermLST, "lTermLST", mStudenC, zLEVEL_TASK );

   //:// Create the Registration entries.
   //:IF mStudenC.UpdateSchedule EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "UpdateSchedule" );
   if ( lTempInteger_0 == 0 )
   { 
      //:DELETE ENTITY mStudenC.UpdateSchedule 
      RESULT = DeleteEntity( mStudenC, "UpdateSchedule", zPOS_NEXT );
   } 

   //:END
   //:TotalCredits = 0
   TotalCredits = 0;
   //:CreateTemporalEntity( mStudenC, "UpdateSchedule", zPOS_AFTER )
   CreateTemporalEntity( mStudenC, "UpdateSchedule", zPOS_AFTER );
   //:IF CollegeTermID = 0
   if ( CollegeTermID == 0 )
   { 
      //:// The Registration entries are for a Cohort.
      //:mStudenC.UpdateSchedule.YearSemester        = ""
      SetAttributeFromString( mStudenC, "UpdateSchedule", "YearSemester", "" );
      //:mStudenC.UpdateSchedule.CollegeTermID       = ""
      SetAttributeFromString( mStudenC, "UpdateSchedule", "CollegeTermID", "" );
      //:mStudenC.UpdateSchedule.CollegeYear         = ""
      SetAttributeFromString( mStudenC, "UpdateSchedule", "CollegeYear", "" );
      //:mStudenC.UpdateSchedule.CollegeTermSemester = ""
      SetAttributeFromString( mStudenC, "UpdateSchedule", "CollegeTermSemester", "" );
      //:ELSE
   } 
   else
   { 
      //:// The Registration entries are for a Term.
      //:SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.ID = CollegeTermID
      RESULT = SetCursorFirstEntityByInteger( lTermLST, "CollegeTerm", "ID", CollegeTermID, "" );
      //:mStudenC.UpdateSchedule.CollegeTermID       = CollegeTermID
      SetAttributeFromInteger( mStudenC, "UpdateSchedule", "CollegeTermID", CollegeTermID );
      //:mStudenC.UpdateSchedule.YearSemester        = lTermLST.CollegeTerm.YearSemester 
      SetAttributeFromAttribute( mStudenC, "UpdateSchedule", "YearSemester", lTermLST, "CollegeTerm", "YearSemester" );
      //:mStudenC.UpdateSchedule.CollegeYear         = lTermLST.CollegeYear.Year 
      SetAttributeFromAttribute( mStudenC, "UpdateSchedule", "CollegeYear", lTermLST, "CollegeYear", "Year" );
      //:mStudenC.UpdateSchedule.CollegeTermSemester = lTermLST.CollegeTerm.Semester 
      SetAttributeFromAttribute( mStudenC, "UpdateSchedule", "CollegeTermSemester", lTermLST, "CollegeTerm", "Semester" );
   } 

   //:END

   //:FOR EACH mStudenC.Registration
   RESULT = SetCursorFirstEntity( mStudenC, "Registration", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mStudenC.RegistrationClassCollegeTerm EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( mStudenC, "RegistrationClassCollegeTerm" );
      if ( lTempInteger_1 == 0 )
      { 
         //:ClassCollegeTermID = mStudenC.RegistrationClassCollegeTerm.ID 
         {MutableInt mi_ClassCollegeTermID = new MutableInt( ClassCollegeTermID );
                   GetIntegerFromAttribute( mi_ClassCollegeTermID, mStudenC, "RegistrationClassCollegeTerm", "ID" );
         ClassCollegeTermID = mi_ClassCollegeTermID.intValue( );}
         //:ELSE
      } 
      else
      { 
         //:IF mStudenC.RegisteredCollegeTerm EXISTS
         lTempInteger_2 = CheckExistenceOfEntity( mStudenC, "RegisteredCollegeTerm" );
         if ( lTempInteger_2 == 0 )
         { 
            //:ClassCollegeTermID = mStudenC.RegisteredCollegeTerm.ID 
            {MutableInt mi_ClassCollegeTermID = new MutableInt( ClassCollegeTermID );
                         GetIntegerFromAttribute( mi_ClassCollegeTermID, mStudenC, "RegisteredCollegeTerm", "ID" );
            ClassCollegeTermID = mi_ClassCollegeTermID.intValue( );}
            //:ELSE
         } 
         else
         { 
            //:ClassCollegeTermID = 0
            ClassCollegeTermID = 0;
         } 

         //:END
      } 

      //:END
      //:IF ClassCollegeTermID != 0 AND CollegeTermID != 0
      if ( ClassCollegeTermID != 0 && CollegeTermID != 0 )
      { 
         //:IF ClassCollegeTermID = CollegeTermID
         if ( ClassCollegeTermID == CollegeTermID )
         { 
            //:// Create the Registration information for the Term.
            //:CREATE ENTITY mStudenC.US_Registration
            RESULT = CreateEntity( mStudenC, "US_Registration", zPOS_AFTER );
            //:SetMatchingAttributesByName( mStudenC, "US_Registration",
            //:                             mStudenC, "Registration", zSET_ALL )
            SetMatchingAttributesByName( mStudenC, "US_Registration", mStudenC, "Registration", zSET_ALL );
            //:INCLUDE mStudenC.US_Class FROM mStudenC.RegistrationClass 
            RESULT = IncludeSubobjectFromSubobject( mStudenC, "US_Class", mStudenC, "RegistrationClass", zPOS_AFTER );
            //:BuildClassScheduleAttr( mStudenC, szMeetingSchedule, "Registration" )
            {StringBuilder sb_szMeetingSchedule;
            if ( szMeetingSchedule == null )
               sb_szMeetingSchedule = new StringBuilder( 32 );
            else
               sb_szMeetingSchedule = new StringBuilder( szMeetingSchedule );
                         omStudenC_BuildClassScheduleAttr( mStudenC, sb_szMeetingSchedule, "Registration" );
            szMeetingSchedule = sb_szMeetingSchedule.toString( );}
            //:mStudenC.US_Class.wMeetingSchedule = szMeetingSchedule
            SetAttributeFromString( mStudenC, "US_Class", "wMeetingSchedule", szMeetingSchedule );
            //:mStudenC.US_Registration.wOrigStatus                    = mStudenC.US_Registration.Status 
            SetAttributeFromAttribute( mStudenC, "US_Registration", "wOrigStatus", mStudenC, "US_Registration", "Status" );
            //:mStudenC.US_Registration.wOrigFinalGrade                = mStudenC.US_Registration.FinalGrade 
            SetAttributeFromAttribute( mStudenC, "US_Registration", "wOrigFinalGrade", mStudenC, "US_Registration", "FinalGrade" );
            //:mStudenC.US_Registration.wOrigCreditHours               = mStudenC.US_Registration.CreditHours 
            SetAttributeFromAttribute( mStudenC, "US_Registration", "wOrigCreditHours", mStudenC, "US_Registration", "CreditHours" );
            //:mStudenC.US_Registration.wOrigTakingClassType           = mStudenC.US_Registration.TakingClassType 
            SetAttributeFromAttribute( mStudenC, "US_Registration", "wOrigTakingClassType", mStudenC, "US_Registration", "TakingClassType" );
            //:mStudenC.US_Registration.wOrigMidtermGrade              = mStudenC.US_Registration.MidtermGrade 
            SetAttributeFromAttribute( mStudenC, "US_Registration", "wOrigMidtermGrade", mStudenC, "US_Registration", "MidtermGrade" );
            //:mStudenC.US_Registration.wOrigGradUndergradOverrideFlag = mStudenC.US_Registration.GradUndergradOverrideFlag 
            SetAttributeFromAttribute( mStudenC, "US_Registration", "wOrigGradUndergradOverrideFlag", mStudenC, "US_Registration", "GradUndergradOverrideFlag" );
            //:mStudenC.US_Registration.wOrigDroppedDate               = mStudenC.US_Registration.DroppedDate
            SetAttributeFromAttribute( mStudenC, "US_Registration", "wOrigDroppedDate", mStudenC, "US_Registration", "DroppedDate" );
            //:IF mStudenC.CrossListedCourse EXISTS
            lTempInteger_3 = CheckExistenceOfEntity( mStudenC, "CrossListedCourse" );
            if ( lTempInteger_3 == 0 )
            { 
               //:INCLUDE mStudenC.US_CrossListedCourse FROM mStudenC.CrossListedCourse 
               RESULT = IncludeSubobjectFromSubobject( mStudenC, "US_CrossListedCourse", mStudenC, "CrossListedCourse", zPOS_AFTER );
            } 

            //:END
            //:IF mStudenC.US_Registration.Status = "T" OR
            //:   mStudenC.US_Registration.Status = "C"
            if ( CompareAttributeToString( mStudenC, "US_Registration", "Status", "T" ) == 0 || CompareAttributeToString( mStudenC, "US_Registration", "Status", "C" ) == 0 )
            { 

               //:TotalCredits = TotalCredits + mStudenC.US_Registration.CreditHours
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mStudenC, "US_Registration", "CreditHours" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               TotalCredits = TotalCredits + dTempDecimal_0;
            } 

            //:END
            //:mStudenC.US_Class.wCourseTitle = mStudenC.Registration.wCourseTitle
            SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseTitle", mStudenC, "Registration", "wCourseTitle" );
            //:mStudenC.US_Class.wCourseSection = mStudenC.US_Registration.dClassNumber 
            SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseSection", mStudenC, "US_Registration", "dClassNumber" );
            //:IF mStudenC.Registration.ManuallyRepeatedRegistrationID != ""
            if ( CompareAttributeToString( mStudenC, "Registration", "ManuallyRepeatedRegistrationID", "" ) != 0 )
            { 
               //:CREATE ENTITY mStudenC.US_OriginalRepeatedRegistration 
               RESULT = CreateEntity( mStudenC, "US_OriginalRepeatedRegistration", zPOS_AFTER );
               //:mStudenC.US_OriginalRepeatedRegistration.ID = mStudenC.Registration.ManuallyRepeatedRegistrationID
               SetAttributeFromAttribute( mStudenC, "US_OriginalRepeatedRegistration", "ID", mStudenC, "Registration", "ManuallyRepeatedRegistrationID" );
            } 

            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF mStudenC.ClassCohort EXISTS AND CollegeTermID = 0
         lTempInteger_4 = CheckExistenceOfEntity( mStudenC, "ClassCohort" );
         if ( lTempInteger_4 == 0 && CollegeTermID == 0 )
         { 
            //:// Create the Registration information for the Cohort.
            //:CREATE ENTITY mStudenC.US_Registration
            RESULT = CreateEntity( mStudenC, "US_Registration", zPOS_AFTER );
            //:SetMatchingAttributesByName( mStudenC, "US_Registration",
            //:                             mStudenC, "Registration", zSET_ALL )
            SetMatchingAttributesByName( mStudenC, "US_Registration", mStudenC, "Registration", zSET_ALL );
            //:INCLUDE mStudenC.US_Class FROM mStudenC.RegistrationClass 
            RESULT = IncludeSubobjectFromSubobject( mStudenC, "US_Class", mStudenC, "RegistrationClass", zPOS_AFTER );
            //://BuildClassScheduleAttr( mStudenC, szMeetingSchedule, "Registration" )
            //://mStudenC.US_Class.wMeetingSchedule = szMeetingSchedule
            //:mStudenC.US_Registration.wOrigStatus                    = mStudenC.US_Registration.Status 
            SetAttributeFromAttribute( mStudenC, "US_Registration", "wOrigStatus", mStudenC, "US_Registration", "Status" );
            //:mStudenC.US_Registration.wOrigFinalGrade                = mStudenC.US_Registration.FinalGrade 
            SetAttributeFromAttribute( mStudenC, "US_Registration", "wOrigFinalGrade", mStudenC, "US_Registration", "FinalGrade" );
            //:mStudenC.US_Registration.wOrigCreditHours               = mStudenC.US_Registration.CreditHours 
            SetAttributeFromAttribute( mStudenC, "US_Registration", "wOrigCreditHours", mStudenC, "US_Registration", "CreditHours" );
            //:mStudenC.US_Registration.wOrigTakingClassType           = mStudenC.US_Registration.TakingClassType 
            SetAttributeFromAttribute( mStudenC, "US_Registration", "wOrigTakingClassType", mStudenC, "US_Registration", "TakingClassType" );
            //:mStudenC.US_Registration.wOrigMidtermGrade              = mStudenC.US_Registration.MidtermGrade 
            SetAttributeFromAttribute( mStudenC, "US_Registration", "wOrigMidtermGrade", mStudenC, "US_Registration", "MidtermGrade" );
            //:mStudenC.US_Registration.wOrigGradUndergradOverrideFlag = mStudenC.US_Registration.GradUndergradOverrideFlag 
            SetAttributeFromAttribute( mStudenC, "US_Registration", "wOrigGradUndergradOverrideFlag", mStudenC, "US_Registration", "GradUndergradOverrideFlag" );
            //:IF mStudenC.US_Registration.Status = "T" OR
            //:   mStudenC.US_Registration.Status = "C"
            if ( CompareAttributeToString( mStudenC, "US_Registration", "Status", "T" ) == 0 || CompareAttributeToString( mStudenC, "US_Registration", "Status", "C" ) == 0 )
            { 

               //:TotalCredits = TotalCredits + mStudenC.US_Registration.CreditHours
               {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                               GetDecimalFromAttribute( md_dTempDecimal_1, mStudenC, "US_Registration", "CreditHours" );
               dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
               TotalCredits = TotalCredits + dTempDecimal_1;
            } 

            //:END
            //:mStudenC.US_Class.wCourseTitle   = mStudenC.Registration.wCourseTitle
            SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseTitle", mStudenC, "Registration", "wCourseTitle" );
            //:mStudenC.US_Class.wCourseSection = mStudenC.US_Registration.dClassNumber 
            SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseSection", mStudenC, "US_Registration", "dClassNumber" );
            //:IF mStudenC.Registration.ManuallyRepeatedRegistrationID != ""
            if ( CompareAttributeToString( mStudenC, "Registration", "ManuallyRepeatedRegistrationID", "" ) != 0 )
            { 
               //:CREATE ENTITY mStudenC.US_OriginalRepeatedRegistration 
               RESULT = CreateEntity( mStudenC, "US_OriginalRepeatedRegistration", zPOS_AFTER );
               //:mStudenC.US_OriginalRepeatedRegistration.ID = mStudenC.Registration.ManuallyRepeatedRegistrationID
               SetAttributeFromAttribute( mStudenC, "US_OriginalRepeatedRegistration", "ID", mStudenC, "Registration", "ManuallyRepeatedRegistrationID" );
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
      //:END
   } 

   //:END

   //:// Create the Waitlisted entries.
   //:FOR EACH mStudenC.StudentWaitlisted
   RESULT = SetCursorFirstEntity( mStudenC, "StudentWaitlisted", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mStudenC.StudentWaitlistedTerm EXISTS
      lTempInteger_5 = CheckExistenceOfEntity( mStudenC, "StudentWaitlistedTerm" );
      if ( lTempInteger_5 == 0 )
      { 
         //:IF mStudenC.StudentWaitlistedTerm.ID = CollegeTermID
         if ( CompareAttributeToInteger( mStudenC, "StudentWaitlistedTerm", "ID", CollegeTermID ) == 0 )
         { 
            //:CREATE ENTITY mStudenC.US_Registration
            RESULT = CreateEntity( mStudenC, "US_Registration", zPOS_AFTER );
            //:mStudenC.US_Registration.ID = mStudenC.StudentWaitlistedClass.ID // Set ID since Save Processing uses it.
            SetAttributeFromAttribute( mStudenC, "US_Registration", "ID", mStudenC, "StudentWaitlistedClass", "ID" );
            //:mStudenC.US_Registration.Status = "L"
            SetAttributeFromString( mStudenC, "US_Registration", "Status", "L" );
            //:mStudenC.US_Registration.wOrigStatus = "L"
            SetAttributeFromString( mStudenC, "US_Registration", "wOrigStatus", "L" );
            //:mStudenC.US_Registration.CreditHours = mStudenC.StudentWaitlistedClass.CreditHours 
            SetAttributeFromAttribute( mStudenC, "US_Registration", "CreditHours", mStudenC, "StudentWaitlistedClass", "CreditHours" );
            //:INCLUDE mStudenC.US_Class FROM mStudenC.StudentWaitlistedClass 
            RESULT = IncludeSubobjectFromSubobject( mStudenC, "US_Class", mStudenC, "StudentWaitlistedClass", zPOS_AFTER );
            //:IF mStudenC.StudentWaitlisteStandardSchedule EXISTS
            lTempInteger_6 = CheckExistenceOfEntity( mStudenC, "StudentWaitlisteStandardSchedule" );
            if ( lTempInteger_6 == 0 )
            { 
               //:mStudenC.US_Class.wMeetingSchedule = mStudenC.StudentWaitlisteStandardSchedule.Title 
               SetAttributeFromAttribute( mStudenC, "US_Class", "wMeetingSchedule", mStudenC, "StudentWaitlisteStandardSchedule", "Title" );
               //:ELSE
            } 
            else
            { 
               //:mStudenC.US_Class.wMeetingSchedule = "Custom"
               SetAttributeFromString( mStudenC, "US_Class", "wMeetingSchedule", "Custom" );
            } 

            //:END
            //:mStudenC.US_Class.wCourseTitle = mStudenC.StudentWaitlisted.wCourseTitle
            SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseTitle", mStudenC, "StudentWaitlisted", "wCourseTitle" );
            //:mStudenC.US_Class.wCourseSection = mStudenC.US_Registration.dClassNumber 
            SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseSection", mStudenC, "US_Registration", "dClassNumber" );
         } 

         //:END 
      } 

      RESULT = SetCursorNextEntity( mStudenC, "StudentWaitlisted", "" );
      //:END
   } 

   //:END

   //:// Create Total Credits entry.
   //:CREATE ENTITY mStudenC.US_Registration
   RESULT = CreateEntity( mStudenC, "US_Registration", zPOS_AFTER );
   //:CREATE ENTITY mStudenC.US_Registration
   RESULT = CreateEntity( mStudenC, "US_Registration", zPOS_AFTER );
   //:mStudenC.US_Registration.CreditHours = TotalCredits
   SetAttributeFromDecimal( mStudenC, "US_Registration", "CreditHours", TotalCredits );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omStudenC_DeleteRegisEntry( View     mStudenC )
{
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   //:DeleteRegisEntry( VIEW mStudenC BASED ON LOD mStudenC )

   //:// Delete the US_Registration entry.
   //:IF mStudenC.US_Registration.ID != ""
   if ( CompareAttributeToString( mStudenC, "US_Registration", "ID", "" ) != 0 )
   { 
      //:// For existing entries, create a deleted record and a modification record.
      //:CREATE ENTITY mStudenC.US_RegistrationDeleted 
      RESULT = CreateEntity( mStudenC, "US_RegistrationDeleted", zPOS_AFTER );
      //:mStudenC.US_RegistrationDeleted.ID       = mStudenC.US_Registration.ID
      SetAttributeFromAttribute( mStudenC, "US_RegistrationDeleted", "ID", mStudenC, "US_Registration", "ID" );
      //:mStudenC.US_RegistrationDeleted.Status   = mStudenC.US_Registration.Status
      SetAttributeFromAttribute( mStudenC, "US_RegistrationDeleted", "Status", mStudenC, "US_Registration", "Status" );
      //:mStudenC.US_RegistrationDeleted.wClassID = mStudenC.US_Class.ID 
      SetAttributeFromAttribute( mStudenC, "US_RegistrationDeleted", "wClassID", mStudenC, "US_Class", "ID" );
      //:CREATE ENTITY mStudenC.US_DeletedModificationRecord 
      RESULT = CreateEntity( mStudenC, "US_DeletedModificationRecord", zPOS_AFTER );
      //:mStudenC.US_DeletedModificationRecord.CourseNumber = mStudenC.US_Course.Number 
      SetAttributeFromAttribute( mStudenC, "US_DeletedModificationRecord", "CourseNumber", mStudenC, "US_Course", "Number" );
      //:IF mStudenC.US_CollegeTerm EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "US_CollegeTerm" );
      if ( lTempInteger_0 == 0 )
      { 
         //:mStudenC.US_DeletedModificationRecord.YearSemester = mStudenC.US_CollegeTerm.YearSemester
         SetAttributeFromAttribute( mStudenC, "US_DeletedModificationRecord", "YearSemester", mStudenC, "US_CollegeTerm", "YearSemester" );
         //:ELSE
      } 
      else
      { 
         //:IF mStudenC.US_ClassCohort EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mStudenC, "US_ClassCohort" );
         if ( lTempInteger_1 == 0 )
         { 
            //:mStudenC.US_DeletedModificationRecord.YearSemester = mStudenC.US_ClassCohort.Name
            SetAttributeFromAttribute( mStudenC, "US_DeletedModificationRecord", "YearSemester", mStudenC, "US_ClassCohort", "Name" );
         } 

         //:END
      } 

      //:END
   } 

   //:    
   //:END
   //:DELETE ENTITY mStudenC.US_Registration NONE
   RESULT = DeleteEntity( mStudenC, "US_Registration", zREPOS_NONE );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ValidateTermSchedules( VIEW mStudenC BASED ON LOD mStudenC )

//:   VIEW mCRStdPLST BASED ON LOD mCRStdP
public int 
omStudenC_ValidateTermSchedules( View     mStudenC )
{
   zVIEW    mCRStdPLST = new zVIEW( );
   //:VIEW wStudent   BASED ON LOD wStudent
   zVIEW    wStudent = new zVIEW( );
   //:VIEW wStudent2  BASED ON LOD wStudent
   zVIEW    wStudent2 = new zVIEW( );
   //:STRING ( 4000 ) szMsg
   String   szMsg = null;
   //:STRING ( 20 ) StartTime1
   String   StartTime1 = null;
   //:STRING ( 20 ) EndTime1
   String   EndTime1 = null;
   //:STRING ( 20 ) StartTime2
   String   StartTime2 = null;
   //:STRING ( 20 ) EndTime2
   String   EndTime2 = null;
   //:STRING ( 20 ) szMeetingDay
   String   szMeetingDay = null;
   //:SHORT nRC
   int      nRC = 0;

   return( 0 );
//    // Make sure the list of Standard Schedules exists.
//    // DonC note on 4/6/2005
//    // At this time we only perform validations when a Class is selected.
//    /*GET VIEW mCRStdPLST NAMED "mCRStdPLST"
//    IF RESULT < 0
//       ACTIVATE mCRStdPLST Multiple
//       NAME VIEW mCRStdPLST "mCRStdPLST"
//    END
//    
//    // Set up work object of all Class Sessions for Term.
//    ACTIVATE wStudent EMPTY
//    CREATE ENTITY wStudent.Work 
//    FOR EACH mStudenC.US_Registration
//       // Only enrolled and preenrolled entries are considered.
//       IF mStudenC.US_Registration.Status = "R" OR
//          mStudenC.US_Registration.Status = "T"
//       
//          IF mStudenC.US_ClassRoomStandardSchedule EXISTS
//             SET CURSOR FIRST mCRStdPLST.ClassRoomStandardSchedule 
//                        WHERE mCRStdPLST.ClassRoomStandardSchedule.ID = mStudenC.US_ClassRoomStandardSchedule.ID   
//             IF RESULT >= zCURSOR_SET
//                FOR EACH mCRStdPLST.ClassRoomSession 
//                   CREATE ENTITY wStudent.ClassRoomSession
//                   SetMatchingAttributesByName( wStudent,   "ClassRoomSession",
//                                                mCRStdPLST, "ClassRoomSession", zSET_ALL )
//                   CREATE ENTITY wStudent.Class 
//                   wStudent.Class.ID          = mStudenC.US_Class.ID 
//                   wStudent.Class.ClassNumber = mStudenC.US_Class.wCourseSection 
//                   wStudent.Class.ClassTitle  = mStudenC.US_Class.wCourseTitle 
//                END
//             END
//          END
//          FOR EACH mStudenC.US_ClassRoomSession WHERE mStudenC.US_ClassRoomSession.MeetingDay != ""
//             CREATE ENTITY wStudent.ClassRoomSession
//             SetMatchingAttributesByName( wStudent, "ClassRoomSession",
//                                          mStudenC, "US_ClassRoomSession", zSET_ALL )
//             CREATE ENTITY wStudent.Class 
//             wStudent.Class.ID          = mStudenC.US_Class.ID 
//             wStudent.Class.ClassNumber = mStudenC.US_Class.wCourseSection 
//             wStudent.Class.ClassTitle  = mStudenC.US_Class.wCourseTitle 
//          END
//       END
//    END
//    
//    // Order the Schedules by Start/End Dates, Day and Time and then search for overlapping entries.
//    OrderEntityForView( wStudent, "ClassRoomSession", "StartDate A EndDate A MeetingDay A StartTime A EndTime A" )
//    szMsg = ""
//    NAME VIEW wStudent "wStudent"
//    FOR EACH wStudent.ClassRoomSession 
//       CreateViewFromView( wStudent2, wStudent )
//       NAME VIEW wStudent2 "wStudent2"
//       SET CURSOR NEXT wStudent2.ClassRoomSession 
//       IF RESULT >= zCURSOR_SET
//          // First check if the ClassRoomSession dates of wStudent2 overlap the ClassRoomSession dates of wStudent.
//          // Since the entries are in order by StartDate, we only need to check that the StartDate of wStudent2 < the EndDate of wStudent.
//          IF wStudent2.ClassRoomSession.StartDate < wStudent.ClassRoomSession.EndDate
//             // Check if the MeetingDay is the same for both entries.
//             IF wStudent2.ClassRoomSession.MeetingDay = wStudent.ClassRoomSession.MeetingDay
//                // Finally check for overlap of times.
//                // Since the entries are in order by StartTime, we only need to check that the StartTime of wStudent2 < the EndTime of wStudent.
//                IF wStudent2.ClassRoomSession.StartTime < wStudent.ClassRoomSession.EndTime
//                   GetStringFromAttributeByContext( StartTime1, wStudent, "ClassRoomSession", "StartTime", "", 20 )
//                   GetStringFromAttributeByContext( EndTime1, wStudent, "ClassRoomSession", "EndTime", "", 20 )
//                   GetStringFromAttributeByContext( StartTime2, wStudent2, "ClassRoomSession", "StartTime", "", 20 )
//                   GetStringFromAttributeByContext( EndTime2, wStudent2, "ClassRoomSession", "EndTime", "", 20 )
//                   GetStringFromAttributeByContext( szMeetingDay, wStudent, "ClassRoomSession", "MeetingDay", "", 20 )
//                   szMsg = szMsg + "Overlap for Class session: " + wStudent.Class.ClassNumber + ", " + szMeetingDay  + " " + StartTime1 + " to " + EndTime1 +
//                        NEW_LINE + "        and Class session: " + wStudent2.Class.ClassNumber + ", " + szMeetingDay  + " " + StartTime2 + " to " + EndTime2 + "." +
//                        NEW_LINE
//                   SET CURSOR NEXT wStudent.ClassRoomSession      // Skip the duplicate entry
//                END
//             END
//          END
//       END
//       DropView( wStudent2 )
//    END
//    DropObjectInstance( wStudent )
//    
//    // Return 0 if there was no overlap and -1 if there was.
//    IF szMsg != ""
//       szMsg = szMsg + "Do you want to accept the schedules with their conflicts anyway?"
//       nRC = MessagePrompt( mStudenC, "", "Validate Schedules",
//                            szMsg,
//                            0, zBUTTONS_YESNO, zRESPONSE_NO, 0 )
//       IF nRC = zRESPONSE_NO
//          //MessageSend( mStudenC, "", "Course Validations",
//          //             "Transaction aborting because of overlapping schedules.",
//          //             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//          RETURN -1
//       END
//    END*/
//    
// END
} 


//:TRANSFORMATION OPERATION
//:ValidateClassSchedule( VIEW mStudenC BASED ON LOD mStudenC,
//:                       VIEW lClsLstC BASED ON LOD lClsLstC )

//:   STRING ( 4000 ) szMsg
public int 
omStudenC_ValidateClassSchedule( View     mStudenC,
                                 View     lClsLstC )
{
   String   szMsg = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;


   //:// Use the reusable routine to validate class schedules. This routine checks if an error message was created
   //:// and notifies the operator if it was.
   //:ValidateClassScheduleR( mStudenC, lClsLstC )
   omStudenC_ValidateClassScheduleR( mStudenC, lClsLstC );

   //:// Return 0 if there was no overlap and -1 if there was.
   //:IF mStudenC.ValidationErrorMessage EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "ValidationErrorMessage" );
   if ( lTempInteger_0 == 0 )
   { 
      //:szMsg = mStudenC.ValidationErrorMessage.Value + NEW_LINE +
      //:        "Do you want to accept the class with its conflicts anyway?"
      {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
             GetStringFromAttribute( sb_szMsg, mStudenC, "ValidationErrorMessage", "Value" );
      szMsg = sb_szMsg.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 4001 );
      szMsg = sb_szMsg.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, "Do you want to accept the class with its conflicts anyway?", 1, 0, 4001 );
      szMsg = sb_szMsg.toString( );}
      //:nRC = MessagePrompt( mStudenC, "", "Validate Schedules",
      //:                     szMsg,
      //:                     0, zBUTTONS_YESNO, zRESPONSE_NO, 0 )
      nRC = MessagePrompt( mStudenC, "", "Validate Schedules", szMsg, 0, zBUTTONS_YESNO, zRESPONSE_NO, 0 );
      //:IF nRC = zRESPONSE_NO
      if ( nRC == zRESPONSE_NO )
      { 
         //:MessageSend( mStudenC, "", "Course Validations",
         //:             "Transaction aborting because of overlapping schedules.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mStudenC, "", "Course Validations", "Transaction aborting because of overlapping schedules.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
   } 

   //:END
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:ValidateClassScheduleR( VIEW mStudenC BASED ON LOD mStudenC,
//:                        VIEW lClsLstC BASED ON LOD lClsLstC )

//:   VIEW mCRStdPLST BASED ON LOD mCRStdP
public int 
omStudenC_ValidateClassScheduleR( View     mStudenC,
                                  View     lClsLstC )
{
   zVIEW    mCRStdPLST = new zVIEW( );
   //:VIEW wStudent   BASED ON LOD wStudent
   zVIEW    wStudent = new zVIEW( );
   //:VIEW wStudent2  BASED ON LOD wStudent
   zVIEW    wStudent2 = new zVIEW( );
   //:STRING ( 4000 ) szMsg
   String   szMsg = null;
   //:STRING ( 20 ) StartTime1
   String   StartTime1 = null;
   //:STRING ( 20 ) EndTime1
   String   EndTime1 = null;
   //:STRING ( 20 ) StartTime2
   String   StartTime2 = null;
   //:STRING ( 20 ) EndTime2
   String   EndTime2 = null;
   //:STRING ( 20 ) szMeetingDay
   String   szMeetingDay = null;
   //:STRING ( 1 )  szOverlapDate
   String   szOverlapDate = null;
   //:STRING ( 1 )  szOverlapTime
   String   szOverlapTime = null;
   //:SHORT nRC
   int      nRC = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_5 = 0;


   //:// Validate the Class Schedule to make sure that no two classes are scheduled at the same time.
   //:// This is accomplished by creating a work object of enrolled Classes, with their meeting date range and times,
   //:// sorting them by date, day and time, and then checking for overlapping entries.
   //:// The work object must combine both standard and custom schedule into a single schedule structure.
   //:// Custom schedule entries for a Date will create one entry in the table with the StartDate and EndDate being
   //:// the same.
   //:// Note that any errors are returned in entity mStudenC.ValidationErrorMessage.

   //:// Make sure the list of Standard Schedules exists.
   //:ACTIVATE mCRStdPLST Multiple
   RESULT = ActivateObjectInstance( mCRStdPLST, "mCRStdP", mStudenC, 0, zMULTIPLE );
   //:NAME VIEW mCRStdPLST "mCRStdPLST_Validate"
   SetNameForView( mCRStdPLST, "mCRStdPLST_Validate", null, zLEVEL_TASK );

   //:// Set up work object for Sessions of the Class being added.
   //:ACTIVATE wStudent EMPTY
   RESULT = ActivateEmptyObjectInstance( wStudent, "wStudent", mStudenC, zSINGLE );
   //:CREATE ENTITY wStudent.Work 
   RESULT = CreateEntity( wStudent, "Work", zPOS_AFTER );
   //:NAME VIEW wStudent "wStudent1"
   SetNameForView( wStudent, "wStudent1", null, zLEVEL_TASK );
   //:IF lClsLstC.ClassRoomStandardSchedule EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( lClsLstC, "ClassRoomStandardSchedule" );
   if ( lTempInteger_0 == 0 )
   { 
      //:SET CURSOR FIRST mCRStdPLST.ClassRoomStandardSchedule 
      //:           WHERE mCRStdPLST.ClassRoomStandardSchedule.ID = lClsLstC.ClassRoomStandardSchedule.ID   
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, lClsLstC, "ClassRoomStandardSchedule", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( mCRStdPLST, "ClassRoomStandardSchedule", "ID", lTempInteger_1, "" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:FOR EACH mCRStdPLST.ClassRoomSession 
         RESULT = SetCursorFirstEntity( mCRStdPLST, "ClassRoomSession", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY wStudent.ClassRoomSession
            RESULT = CreateEntity( wStudent, "ClassRoomSession", zPOS_AFTER );
            //:SetMatchingAttributesByName( wStudent,   "ClassRoomSession",
            //:                             mCRStdPLST, "ClassRoomSession", zSET_ALL )
            SetMatchingAttributesByName( wStudent, "ClassRoomSession", mCRStdPLST, "ClassRoomSession", zSET_ALL );
            //:wStudent.ClassRoomSession.StartDate = lClsLstC.Class.ClassStartDate 
            SetAttributeFromAttribute( wStudent, "ClassRoomSession", "StartDate", lClsLstC, "Class", "ClassStartDate" );
            //:wStudent.ClassRoomSession.EndDate   = lClsLstC.Class.ClassEndDate 
            SetAttributeFromAttribute( wStudent, "ClassRoomSession", "EndDate", lClsLstC, "Class", "ClassEndDate" );
            //:CREATE ENTITY wStudent.Class 
            RESULT = CreateEntity( wStudent, "Class", zPOS_AFTER );
            //:wStudent.Class.ID          = lClsLstC.Class.ID 
            SetAttributeFromAttribute( wStudent, "Class", "ID", lClsLstC, "Class", "ID" );
            //:wStudent.Class.ClassNumber = lClsLstC.Class.wClassNumberTopicSection 
            SetAttributeFromAttribute( wStudent, "Class", "ClassNumber", lClsLstC, "Class", "wClassNumberTopicSection" );
            //:wStudent.Class.ClassTitle  = lClsLstC.Class.dClassTitle 
            SetAttributeFromAttribute( wStudent, "Class", "ClassTitle", lClsLstC, "Class", "dClassTitle" );
            RESULT = SetCursorNextEntity( mCRStdPLST, "ClassRoomSession", "" );
         } 

         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:FOR EACH lClsLstC.ClassRoomSession 
      RESULT = SetCursorFirstEntity( lClsLstC, "ClassRoomSession", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY wStudent.ClassRoomSession
         RESULT = CreateEntity( wStudent, "ClassRoomSession", zPOS_AFTER );
         //:SetMatchingAttributesByName( wStudent,  "ClassRoomSession",
         //:                             lClsLstC,  "ClassRoomSession", zSET_ALL )
         SetMatchingAttributesByName( wStudent, "ClassRoomSession", lClsLstC, "ClassRoomSession", zSET_ALL );
         //:CREATE ENTITY wStudent.Class 
         RESULT = CreateEntity( wStudent, "Class", zPOS_AFTER );
         //:wStudent.Class.ID          = lClsLstC.Class.ID 
         SetAttributeFromAttribute( wStudent, "Class", "ID", lClsLstC, "Class", "ID" );
         //:wStudent.Class.ClassNumber = lClsLstC.Class.wClassNumberTopicSection 
         SetAttributeFromAttribute( wStudent, "Class", "ClassNumber", lClsLstC, "Class", "wClassNumberTopicSection" );
         //:wStudent.Class.ClassTitle  = lClsLstC.Class.dClassTitle 
         SetAttributeFromAttribute( wStudent, "Class", "ClassTitle", lClsLstC, "Class", "dClassTitle" );
         //:IF lClsLstC.ClassRoomSession.MeetingDate = ""
         if ( CompareAttributeToString( lClsLstC, "ClassRoomSession", "MeetingDate", "" ) == 0 )
         { 
            //:wStudent.ClassRoomSession.StartDate = lClsLstC.Class.ClassStartDate 
            SetAttributeFromAttribute( wStudent, "ClassRoomSession", "StartDate", lClsLstC, "Class", "ClassStartDate" );
            //:wStudent.ClassRoomSession.EndDate   = lClsLstC.Class.ClassEndDate 
            SetAttributeFromAttribute( wStudent, "ClassRoomSession", "EndDate", lClsLstC, "Class", "ClassEndDate" );
            //:ELSE
         } 
         else
         { 
            //:// Convert the Date into StartDate, EndDate and DayOfWeek.
            //:wStudent.ClassRoomSession.StartDate = lClsLstC.ClassRoomSession.MeetingDate 
            SetAttributeFromAttribute( wStudent, "ClassRoomSession", "StartDate", lClsLstC, "ClassRoomSession", "MeetingDate" );
            //:wStudent.ClassRoomSession.EndDate   = lClsLstC.ClassRoomSession.MeetingDate 
            SetAttributeFromAttribute( wStudent, "ClassRoomSession", "EndDate", lClsLstC, "ClassRoomSession", "MeetingDate" );
            //:GetStringFromAttributeByContext( szMeetingDay, wStudent, "ClassRoomSession", "MeetingDate", "DayOfWeek", 20 )
            {StringBuilder sb_szMeetingDay;
            if ( szMeetingDay == null )
               sb_szMeetingDay = new StringBuilder( 32 );
            else
               sb_szMeetingDay = new StringBuilder( szMeetingDay );
                         GetStringFromAttributeByContext( sb_szMeetingDay, wStudent, "ClassRoomSession", "MeetingDate", "DayOfWeek", 20 );
            szMeetingDay = sb_szMeetingDay.toString( );}
            //:SetAttrFromStrByContext( wStudent, "ClassRoomSession", "MeetingDay", szMeetingDay, "" )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wStudent );
             m_ZGLOBAL1_Operation.SetAttrFromStrByContext( wStudent, "ClassRoomSession", "MeetingDay", szMeetingDay, "" );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
         } 

         RESULT = SetCursorNextEntity( lClsLstC, "ClassRoomSession", "" );
         //:END
      } 

      //:END
   } 

   //:END

   //:// Set up work object for Sessions of the Classes currently registered.
   //:// Order the result by Day, StartDate and Times for comparison purposes. 
   //:ACTIVATE wStudent2 EMPTY
   RESULT = ActivateEmptyObjectInstance( wStudent2, "wStudent", mStudenC, zSINGLE );
   //:CREATE ENTITY wStudent2.Work 
   RESULT = CreateEntity( wStudent2, "Work", zPOS_AFTER );
   //:NAME VIEW wStudent2 "wStudent2"
   SetNameForView( wStudent2, "wStudent2", null, zLEVEL_TASK );
   //:FOR EACH mStudenC.US_Registration
   RESULT = SetCursorFirstEntity( mStudenC, "US_Registration", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// Only enrolled entries are considered.
      //:IF mStudenC.US_Registration.Status = "T"
      if ( CompareAttributeToString( mStudenC, "US_Registration", "Status", "T" ) == 0 )
      { 
         //:IF mStudenC.US_ClassRoomStandardSchedule EXISTS
         lTempInteger_2 = CheckExistenceOfEntity( mStudenC, "US_ClassRoomStandardSchedule" );
         if ( lTempInteger_2 == 0 )
         { 
            //:SET CURSOR FIRST mCRStdPLST.ClassRoomStandardSchedule 
            //:           WHERE mCRStdPLST.ClassRoomStandardSchedule.ID = mStudenC.US_ClassRoomStandardSchedule.ID   
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                         GetIntegerFromAttribute( mi_lTempInteger_3, mStudenC, "US_ClassRoomStandardSchedule", "ID" );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mCRStdPLST, "ClassRoomStandardSchedule", "ID", lTempInteger_3, "" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:FOR EACH mCRStdPLST.ClassRoomSession 
               RESULT = SetCursorFirstEntity( mCRStdPLST, "ClassRoomSession", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:CREATE ENTITY wStudent2.ClassRoomSession
                  RESULT = CreateEntity( wStudent2, "ClassRoomSession", zPOS_AFTER );
                  //:SetMatchingAttributesByName( wStudent2,   "ClassRoomSession",
                  //:                             mCRStdPLST, "ClassRoomSession", zSET_ALL )
                  SetMatchingAttributesByName( wStudent2, "ClassRoomSession", mCRStdPLST, "ClassRoomSession", zSET_ALL );
                  //:wStudent2.ClassRoomSession.StartDate = mStudenC.US_Class.ClassStartDate 
                  SetAttributeFromAttribute( wStudent2, "ClassRoomSession", "StartDate", mStudenC, "US_Class", "ClassStartDate" );
                  //:wStudent2.ClassRoomSession.EndDate   = mStudenC.US_Class.ClassEndDate 
                  SetAttributeFromAttribute( wStudent2, "ClassRoomSession", "EndDate", mStudenC, "US_Class", "ClassEndDate" );
                  //:CREATE ENTITY wStudent2.Class 
                  RESULT = CreateEntity( wStudent2, "Class", zPOS_AFTER );
                  //:wStudent2.Class.ID          = mStudenC.US_Class.ID 
                  SetAttributeFromAttribute( wStudent2, "Class", "ID", mStudenC, "US_Class", "ID" );
                  //:IF mStudenC.US_Class.wCourseSection != ""
                  if ( CompareAttributeToString( mStudenC, "US_Class", "wCourseSection", "" ) != 0 )
                  { 
                     //:wStudent2.Class.ClassNumber = mStudenC.US_Class.wCourseSection 
                     SetAttributeFromAttribute( wStudent2, "Class", "ClassNumber", mStudenC, "US_Class", "wCourseSection" );
                     //:ELSE  
                  } 
                  else
                  { 
                     //:wStudent2.Class.ClassNumber = mStudenC.US_Class.wClassNumberTopicSection 
                     SetAttributeFromAttribute( wStudent2, "Class", "ClassNumber", mStudenC, "US_Class", "wClassNumberTopicSection" );
                  } 

                  //:END
                  //:wStudent2.Class.ClassTitle  = mStudenC.US_Class.wCourseTitle 
                  SetAttributeFromAttribute( wStudent2, "Class", "ClassTitle", mStudenC, "US_Class", "wCourseTitle" );
                  RESULT = SetCursorNextEntity( mCRStdPLST, "ClassRoomSession", "" );
               } 

               //:END
            } 

            //:END
         } 

         //:END
         //:FOR EACH mStudenC.US_ClassRoomSession WHERE mStudenC.US_ClassRoomSession.MeetingDay != ""
         RESULT = SetCursorFirstEntity( mStudenC, "US_ClassRoomSession", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( mStudenC, "US_ClassRoomSession", "MeetingDay", "" ) != 0 )
            { 
               //:CREATE ENTITY wStudent2.ClassRoomSession
               RESULT = CreateEntity( wStudent2, "ClassRoomSession", zPOS_AFTER );
               //:SetMatchingAttributesByName( wStudent2, "ClassRoomSession",
               //:                          mStudenC, "US_ClassRoomSession", zSET_ALL )
               SetMatchingAttributesByName( wStudent2, "ClassRoomSession", mStudenC, "US_ClassRoomSession", zSET_ALL );
               //:CREATE ENTITY wStudent2.Class 
               RESULT = CreateEntity( wStudent2, "Class", zPOS_AFTER );
               //:wStudent2.Class.ID          = mStudenC.US_Class.ID 
               SetAttributeFromAttribute( wStudent2, "Class", "ID", mStudenC, "US_Class", "ID" );
               //:wStudent2.Class.ClassNumber = mStudenC.US_Class.wCourseSection 
               SetAttributeFromAttribute( wStudent2, "Class", "ClassNumber", mStudenC, "US_Class", "wCourseSection" );
               //:wStudent2.Class.ClassTitle  = mStudenC.US_Class.wCourseTitle 
               SetAttributeFromAttribute( wStudent2, "Class", "ClassTitle", mStudenC, "US_Class", "wCourseTitle" );
               //:IF mStudenC.US_ClassRoomSession.MeetingDate = ""
               if ( CompareAttributeToString( mStudenC, "US_ClassRoomSession", "MeetingDate", "" ) == 0 )
               { 
                  //:wStudent2.ClassRoomSession.StartDate = mStudenC.US_Class.ClassStartDate 
                  SetAttributeFromAttribute( wStudent2, "ClassRoomSession", "StartDate", mStudenC, "US_Class", "ClassStartDate" );
                  //:wStudent2.ClassRoomSession.EndDate   = mStudenC.US_Class.ClassEndDate 
                  SetAttributeFromAttribute( wStudent2, "ClassRoomSession", "EndDate", mStudenC, "US_Class", "ClassEndDate" );
                  //:ELSE
               } 
               else
               { 
                  //:// Convert the Date into StartDate, EndDate and DayOfWeek.
                  //:wStudent2.ClassRoomSession.StartDate = mStudenC.US_ClassRoomSession.MeetingDate 
                  SetAttributeFromAttribute( wStudent2, "ClassRoomSession", "StartDate", mStudenC, "US_ClassRoomSession", "MeetingDate" );
                  //:wStudent2.ClassRoomSession.EndDate   = mStudenC.US_ClassRoomSession.MeetingDate 
                  SetAttributeFromAttribute( wStudent2, "ClassRoomSession", "EndDate", mStudenC, "US_ClassRoomSession", "MeetingDate" );
                  //:GetStringFromAttributeByContext( szMeetingDay, wStudent2, "ClassRoomSession", "MeetingDate", "DayOfWeek", 20 )
                  {StringBuilder sb_szMeetingDay;
                  if ( szMeetingDay == null )
                     sb_szMeetingDay = new StringBuilder( 32 );
                  else
                     sb_szMeetingDay = new StringBuilder( szMeetingDay );
                                     GetStringFromAttributeByContext( sb_szMeetingDay, wStudent2, "ClassRoomSession", "MeetingDate", "DayOfWeek", 20 );
                  szMeetingDay = sb_szMeetingDay.toString( );}
                  //:SetAttrFromStrByContext( wStudent2, "ClassRoomSession", "MeetingDay", szMeetingDay, "" )
                  {
                   ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wStudent2 );
                   m_ZGLOBAL1_Operation.SetAttrFromStrByContext( wStudent2, "ClassRoomSession", "MeetingDay", szMeetingDay, "" );
                   // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                  }
               } 

            } 

            RESULT = SetCursorNextEntity( mStudenC, "US_ClassRoomSession", "" );
            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mStudenC, "US_Registration", "" );
      //:END
   } 

   //:END
   //:OrderEntityForView( wStudent2, "ClassRoomSession", "MeetingDay A StartDate A StartTime A EndTime A" )
   OrderEntityForView( wStudent2, "ClassRoomSession", "MeetingDay A StartDate A StartTime A EndTime A" );


   //:// Search for overlapping entries between wStudent and wStudent2.
   //:// ValidateTermSchedules, we will only look for overlap for the Class selected in lClsLstC. Thus the two
   //:// sets of code are identical except for the statement, "IF wStudent.Class.ID = SelectedClassID".
   //:szMsg = ""
    {StringBuilder sb_szMsg;
   if ( szMsg == null )
      sb_szMsg = new StringBuilder( 32 );
   else
      sb_szMsg = new StringBuilder( szMsg );
      ZeidonStringCopy( sb_szMsg, 1, 0, "", 1, 0, 4001 );
   szMsg = sb_szMsg.toString( );}
   //://NAME VIEW wStudent "wStudent"
   //:FOR EACH wStudent.ClassRoomSession
   RESULT = SetCursorFirstEntity( wStudent, "ClassRoomSession", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// Search for entries in wStudent2 that overlap entries in wStudent. We have broken up the subboolean operators into
      //:// szOverlapDate and szOverlapTime because of problems with the combined boolean and because the breakup makes debugging
      //:// easier.
      //:FOR EACH wStudent2.ClassRoomSession
      RESULT = SetCursorFirstEntity( wStudent2, "ClassRoomSession", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:// Break Overlaps into two conditions.
         //:IF ( wStudent2.ClassRoomSession.StartDate >= wStudent.ClassRoomSession.StartDate AND    // StartDate is within range
         //:     wStudent2.ClassRoomSession.StartDate <= wStudent.ClassRoomSession.EndDate ) OR
         //:   ( wStudent2.ClassRoomSession.EndDate >= wStudent.ClassRoomSession.StartDate AND      // EndDate is within range
         //:     wStudent2.ClassRoomSession.EndDate <= wStudent.ClassRoomSession.EndDate ) OR
         //:   ( wStudent2.ClassRoomSession.StartDate <= wStudent.ClassRoomSession.StartDate AND    // StartDate/EndDate contains range
         //:     wStudent2.ClassRoomSession.EndDate >= wStudent.ClassRoomSession.EndDate )
         if ( ( CompareAttributeToAttribute( wStudent2, "ClassRoomSession", "StartDate", wStudent, "ClassRoomSession", "StartDate" ) >= 0 &&
              CompareAttributeToAttribute( wStudent2, "ClassRoomSession", "StartDate", wStudent, "ClassRoomSession", "EndDate" ) <= 0 ) ||
              ( CompareAttributeToAttribute( wStudent2, "ClassRoomSession", "EndDate", wStudent, "ClassRoomSession", "StartDate" ) >= 0 &&
              CompareAttributeToAttribute( wStudent2, "ClassRoomSession", "EndDate", wStudent, "ClassRoomSession", "EndDate" ) <= 0 ) ||
              ( CompareAttributeToAttribute( wStudent2, "ClassRoomSession", "StartDate", wStudent, "ClassRoomSession", "StartDate" ) <= 0 &&
              CompareAttributeToAttribute( wStudent2, "ClassRoomSession", "EndDate", wStudent, "ClassRoomSession", "EndDate" ) >= 0 ) )
         { 

            //:szOverlapDate = "Y"
             {StringBuilder sb_szOverlapDate;
            if ( szOverlapDate == null )
               sb_szOverlapDate = new StringBuilder( 32 );
            else
               sb_szOverlapDate = new StringBuilder( szOverlapDate );
                        ZeidonStringCopy( sb_szOverlapDate, 1, 0, "Y", 1, 0, 2 );
            szOverlapDate = sb_szOverlapDate.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szOverlapDate = ""
             {StringBuilder sb_szOverlapDate;
            if ( szOverlapDate == null )
               sb_szOverlapDate = new StringBuilder( 32 );
            else
               sb_szOverlapDate = new StringBuilder( szOverlapDate );
                        ZeidonStringCopy( sb_szOverlapDate, 1, 0, "", 1, 0, 2 );
            szOverlapDate = sb_szOverlapDate.toString( );}
         } 

         //:END
         //:IF ( wStudent2.ClassRoomSession.StartTime >= wStudent.ClassRoomSession.StartTime AND    // StartTime is within range
         //:     wStudent2.ClassRoomSession.StartTime <= wStudent.ClassRoomSession.EndTime ) OR
         //:   ( wStudent2.ClassRoomSession.EndTime >= wStudent.ClassRoomSession.StartTime AND      // EndTime is within range 
         //:     wStudent2.ClassRoomSession.EndTime <= wStudent.ClassRoomSession.EndTime ) OR
         //:   ( wStudent2.ClassRoomSession.StartTime <= wStudent.ClassRoomSession.StartTime AND    // StartTime/EndTime contains range 
         //:     wStudent2.ClassRoomSession.EndTime >= wStudent.ClassRoomSession.EndTime )
         if ( ( CompareAttributeToAttribute( wStudent2, "ClassRoomSession", "StartTime", wStudent, "ClassRoomSession", "StartTime" ) >= 0 &&
              CompareAttributeToAttribute( wStudent2, "ClassRoomSession", "StartTime", wStudent, "ClassRoomSession", "EndTime" ) <= 0 ) ||
              ( CompareAttributeToAttribute( wStudent2, "ClassRoomSession", "EndTime", wStudent, "ClassRoomSession", "StartTime" ) >= 0 &&
              CompareAttributeToAttribute( wStudent2, "ClassRoomSession", "EndTime", wStudent, "ClassRoomSession", "EndTime" ) <= 0 ) ||
              ( CompareAttributeToAttribute( wStudent2, "ClassRoomSession", "StartTime", wStudent, "ClassRoomSession", "StartTime" ) <= 0 &&
              CompareAttributeToAttribute( wStudent2, "ClassRoomSession", "EndTime", wStudent, "ClassRoomSession", "EndTime" ) >= 0 ) )
         { 

            //:szOverlapTime = "Y"
             {StringBuilder sb_szOverlapTime;
            if ( szOverlapTime == null )
               sb_szOverlapTime = new StringBuilder( 32 );
            else
               sb_szOverlapTime = new StringBuilder( szOverlapTime );
                        ZeidonStringCopy( sb_szOverlapTime, 1, 0, "Y", 1, 0, 2 );
            szOverlapTime = sb_szOverlapTime.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szOverlapTime = ""
             {StringBuilder sb_szOverlapTime;
            if ( szOverlapTime == null )
               sb_szOverlapTime = new StringBuilder( 32 );
            else
               sb_szOverlapTime = new StringBuilder( szOverlapTime );
                        ZeidonStringCopy( sb_szOverlapTime, 1, 0, "", 1, 0, 2 );
            szOverlapTime = sb_szOverlapTime.toString( );}
         } 

         //:END
         //:IF wStudent2.ClassRoomSession.MeetingDay = wStudent.ClassRoomSession.MeetingDay
         //:   AND szOverlapDate = "Y" AND szOverlapTime = "Y"
         if ( CompareAttributeToAttribute( wStudent2, "ClassRoomSession", "MeetingDay", wStudent, "ClassRoomSession", "MeetingDay" ) == 0 && ZeidonStringCompare( szOverlapDate, 1, 0, "Y", 1, 0, 2 ) == 0 &&
              ZeidonStringCompare( szOverlapTime, 1, 0, "Y", 1, 0, 2 ) == 0 )
         { 

            //:// There is overlap between the ending time entries.
            //:GetStringFromAttributeByContext( StartTime1, wStudent, "ClassRoomSession", "StartTime", "", 20 )
            {StringBuilder sb_StartTime1;
            if ( StartTime1 == null )
               sb_StartTime1 = new StringBuilder( 32 );
            else
               sb_StartTime1 = new StringBuilder( StartTime1 );
                         GetStringFromAttributeByContext( sb_StartTime1, wStudent, "ClassRoomSession", "StartTime", "", 20 );
            StartTime1 = sb_StartTime1.toString( );}
            //:GetStringFromAttributeByContext( EndTime1, wStudent, "ClassRoomSession", "EndTime", "", 20 )
            {StringBuilder sb_EndTime1;
            if ( EndTime1 == null )
               sb_EndTime1 = new StringBuilder( 32 );
            else
               sb_EndTime1 = new StringBuilder( EndTime1 );
                         GetStringFromAttributeByContext( sb_EndTime1, wStudent, "ClassRoomSession", "EndTime", "", 20 );
            EndTime1 = sb_EndTime1.toString( );}
            //:GetStringFromAttributeByContext( StartTime2, wStudent2, "ClassRoomSession", "StartTime", "", 20 )
            {StringBuilder sb_StartTime2;
            if ( StartTime2 == null )
               sb_StartTime2 = new StringBuilder( 32 );
            else
               sb_StartTime2 = new StringBuilder( StartTime2 );
                         GetStringFromAttributeByContext( sb_StartTime2, wStudent2, "ClassRoomSession", "StartTime", "", 20 );
            StartTime2 = sb_StartTime2.toString( );}
            //:GetStringFromAttributeByContext( EndTime2, wStudent2, "ClassRoomSession", "EndTime", "", 20 )
            {StringBuilder sb_EndTime2;
            if ( EndTime2 == null )
               sb_EndTime2 = new StringBuilder( 32 );
            else
               sb_EndTime2 = new StringBuilder( EndTime2 );
                         GetStringFromAttributeByContext( sb_EndTime2, wStudent2, "ClassRoomSession", "EndTime", "", 20 );
            EndTime2 = sb_EndTime2.toString( );}
            //:GetStringFromAttributeByContext( szMeetingDay, wStudent, "ClassRoomSession", "MeetingDay", "", 20 )
            {StringBuilder sb_szMeetingDay;
            if ( szMeetingDay == null )
               sb_szMeetingDay = new StringBuilder( 32 );
            else
               sb_szMeetingDay = new StringBuilder( szMeetingDay );
                         GetStringFromAttributeByContext( sb_szMeetingDay, wStudent, "ClassRoomSession", "MeetingDay", "", 20 );
            szMeetingDay = sb_szMeetingDay.toString( );}
            //:szMsg = szMsg + "Overlap for Class session: " + wStudent.Class.ClassNumber + ", " + szMeetingDay  + " " + StartTime1 + " to " + EndTime1 +
            //:     NEW_LINE + "        and Class session: " + wStudent2.Class.ClassNumber + ", " + szMeetingDay  + " " + StartTime2 + " to " + EndTime2 + "." +
            //:     NEW_LINE
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, "Overlap for Class session: ", 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_4, 'S', 255, wStudent, "Class", "ClassNumber", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_0, 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, ", ", 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, szMeetingDay, 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, " ", 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, StartTime1, 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, " to ", 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, EndTime1, 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, "        and Class session: ", 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
            {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_5, 'S', 255, wStudent2, "Class", "ClassNumber", "", 0 );
            lTempInteger_5 = mi_lTempInteger_5.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_1, 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, ", ", 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, szMeetingDay, 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, " ", 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, StartTime2, 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, " to ", 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, EndTime2, 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, ".", 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 4001 );
            szMsg = sb_szMsg.toString( );}
         } 

         RESULT = SetCursorNextEntity( wStudent2, "ClassRoomSession", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( wStudent, "ClassRoomSession", "" );
      //:END
   } 

   //:END
   //:DropObjectInstance( wStudent )
   DropObjectInstance( wStudent );
   //:DropObjectInstance( wStudent2 )
   DropObjectInstance( wStudent2 );
   //:DropObjectInstance( mCRStdPLST )
   DropObjectInstance( mCRStdPLST );

   //:// Return 0 if there was no overlap and -1 if there was.
   //:IF szMsg != ""
   if ( ZeidonStringCompare( szMsg, 1, 0, "", 1, 0, 4001 ) != 0 )
   { 
      //:CREATE ENTITY mStudenC.ValidationErrorMessage 
      RESULT = CreateEntity( mStudenC, "ValidationErrorMessage", zPOS_AFTER );
      //:mStudenC.ValidationErrorMessage.Value = szMsg
      SetAttributeFromString( mStudenC, "ValidationErrorMessage", "Value", szMsg );
      //:RETURN 1
      if(8==8)return( 1 );
   } 

   //:END
   return( 0 );
//    
// END
} 


//:ENTITY CONSTRAINT OPERATION
public int 
omStudenC_AcceptHS_Course( View     mStudenC,
                           String   NameOfEntity,
                           Integer   Event,
                           Integer   State )
{
   int      lTempInteger_0 = 0;

   //:AcceptHS_Course( VIEW mStudenC BASED ON LOD mStudenC,
   //:              STRING ( 32 ) NameOfEntity,
   //:              SHORT Event,
   //:              SHORT State )

   //:CASE Event
   switch( Event )
   { 
      //:OF   zECE_ACCEPT:
      case zECE_ACCEPT :

         //:// Make sure that a Course has been chosen.
         //:IF mStudenC.HS_StandardCourse DOES NOT EXIST
         lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "HS_StandardCourse" );
         if ( lTempInteger_0 != 0 )
         { 
            //:MessageSend( mStudenC, "", "Accept HS Course", 
            //:       "A Course entry must be selected.",
            //:       zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mStudenC, "", "Accept HS Course", "A Course entry must be selected.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
         break ;

      //:  /* end zECE_ACCEPT */
      //:OF   zECE_CANCEL:
      case zECE_CANCEL :
         break ;

      //:  /* end zECE_CANCEL */
      //:OF   zECE_CREATE:
      case zECE_CREATE :
         break ;

      //:  /* end zECE_CREATE */
      //:OF   zECE_DELETE:
      case zECE_DELETE :
         break ;

      //:  /* end zECE_DELETE */
      //:OF   zECE_EXCLUDE:
      case zECE_EXCLUDE :
         break ;

      //:  /* end zECE_EXCLUDE */
      //:OF   zECE_INCLUDE:
      case zECE_INCLUDE :
         break ;
   } 


   //:     /* end zECE_INCLUDE */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
public int 
omStudenC_dFullNameLFM( View     mStudenC,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{

   //:dFullNameLFM( VIEW mStudenC BASED ON LOD mStudenC,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_LastFirstMiddle( mStudenC, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mStudenC );
          m_ZGLOBAL1_Operation.PersonName_LastFirstMiddle( mStudenC, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
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
//:dRepeatedCourseNumber( VIEW mStudenC_Orig BASED ON LOD mStudenC,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   VIEW mStudenC BASED ON LOD mStudenC
public int 
omStudenC_dRepeatedCourseNumber( View     mStudenC_Orig,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   zVIEW    mStudenC = new zVIEW( );
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:STRING ( 50 )  szCourseNumber
   String   szCourseNumber = null;
   //:STRING ( 10 )  szSection
   String   szSection = null;
   //:INTEGER        RegistrationID
   int      RegistrationID = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_6 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView( mStudenC, mStudenC_Orig )
         CreateViewFromView( mStudenC, mStudenC_Orig );
         //:// Code Example
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mStudenC_Orig );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:GetIntegerFromAttribute( RegistrationID, mStudenC, szEntityName, "ID" )
         {MutableInt mi_RegistrationID = new MutableInt( RegistrationID );
                   GetIntegerFromAttribute( mi_RegistrationID, mStudenC, szEntityName, "ID" );
         RegistrationID = mi_RegistrationID.intValue( );}
         //:SET CURSOR FIRST mStudenC.Registration WHERE mStudenC.Registration.ID = RegistrationID
         RESULT = SetCursorFirstEntityByInteger( mStudenC, "Registration", "ID", RegistrationID, "" );
         //:IF mStudenC.RegistrationCourse EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "RegistrationCourse" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szSection = mStudenC.RegistrationClass.Section
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szSection;
            if ( szSection == null )
               sb_szSection = new StringBuilder( 32 );
            else
               sb_szSection = new StringBuilder( szSection );
                         GetVariableFromAttribute( sb_szSection, mi_lTempInteger_1, 'S', 11, mStudenC, "RegistrationClass", "Section", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szSection = sb_szSection.toString( );}
            //:IF mStudenC.CrossListedCourse EXISTS 
            lTempInteger_2 = CheckExistenceOfEntity( mStudenC, "CrossListedCourse" );
            if ( lTempInteger_2 == 0 )
            { 
               //:szCourseNumber = mStudenC.CrossListedCourse.Number + szSection + " - " + mStudenC.CrossListedCourse.Title 
               {StringBuilder sb_szCourseNumber;
               if ( szCourseNumber == null )
                  sb_szCourseNumber = new StringBuilder( 32 );
               else
                  sb_szCourseNumber = new StringBuilder( szCourseNumber );
                               GetStringFromAttribute( sb_szCourseNumber, mStudenC, "CrossListedCourse", "Number" );
               szCourseNumber = sb_szCourseNumber.toString( );}
                {StringBuilder sb_szCourseNumber;
               if ( szCourseNumber == null )
                  sb_szCourseNumber = new StringBuilder( 32 );
               else
                  sb_szCourseNumber = new StringBuilder( szCourseNumber );
                              ZeidonStringConcat( sb_szCourseNumber, 1, 0, szSection, 1, 0, 51 );
               szCourseNumber = sb_szCourseNumber.toString( );}
                {StringBuilder sb_szCourseNumber;
               if ( szCourseNumber == null )
                  sb_szCourseNumber = new StringBuilder( 32 );
               else
                  sb_szCourseNumber = new StringBuilder( szCourseNumber );
                              ZeidonStringConcat( sb_szCourseNumber, 1, 0, " - ", 1, 0, 51 );
               szCourseNumber = sb_szCourseNumber.toString( );}
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_3, 'S', 101, mStudenC, "CrossListedCourse", "Title", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szCourseNumber;
               if ( szCourseNumber == null )
                  sb_szCourseNumber = new StringBuilder( 32 );
               else
                  sb_szCourseNumber = new StringBuilder( szCourseNumber );
                              ZeidonStringConcat( sb_szCourseNumber, 1, 0, szTempString_0, 1, 0, 51 );
               szCourseNumber = sb_szCourseNumber.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szCourseNumber = mStudenC.RegistrationCourse.Number + szSection + " - " + mStudenC.RegistrationCourse.Title 
               {StringBuilder sb_szCourseNumber;
               if ( szCourseNumber == null )
                  sb_szCourseNumber = new StringBuilder( 32 );
               else
                  sb_szCourseNumber = new StringBuilder( szCourseNumber );
                               GetStringFromAttribute( sb_szCourseNumber, mStudenC, "RegistrationCourse", "Number" );
               szCourseNumber = sb_szCourseNumber.toString( );}
                {StringBuilder sb_szCourseNumber;
               if ( szCourseNumber == null )
                  sb_szCourseNumber = new StringBuilder( 32 );
               else
                  sb_szCourseNumber = new StringBuilder( szCourseNumber );
                              ZeidonStringConcat( sb_szCourseNumber, 1, 0, szSection, 1, 0, 51 );
               szCourseNumber = sb_szCourseNumber.toString( );}
                {StringBuilder sb_szCourseNumber;
               if ( szCourseNumber == null )
                  sb_szCourseNumber = new StringBuilder( 32 );
               else
                  sb_szCourseNumber = new StringBuilder( szCourseNumber );
                              ZeidonStringConcat( sb_szCourseNumber, 1, 0, " - ", 1, 0, 51 );
               szCourseNumber = sb_szCourseNumber.toString( );}
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_4, 'S', 101, mStudenC, "RegistrationCourse", "Title", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szTempString_1 = sb_szTempString_1.toString( );}
                {StringBuilder sb_szCourseNumber;
               if ( szCourseNumber == null )
                  sb_szCourseNumber = new StringBuilder( 32 );
               else
                  sb_szCourseNumber = new StringBuilder( szCourseNumber );
                              ZeidonStringConcat( sb_szCourseNumber, 1, 0, szTempString_1, 1, 0, 51 );
               szCourseNumber = sb_szCourseNumber.toString( );}
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF mStudenC.EquivalentCourse EXISTS
            lTempInteger_5 = CheckExistenceOfEntity( mStudenC, "EquivalentCourse" );
            if ( lTempInteger_5 == 0 )
            { 
               //:szCourseNumber = mStudenC.EquivalentCourse.Number + " - " + mStudenC.Registration.ForeignClassName 
               {StringBuilder sb_szCourseNumber;
               if ( szCourseNumber == null )
                  sb_szCourseNumber = new StringBuilder( 32 );
               else
                  sb_szCourseNumber = new StringBuilder( szCourseNumber );
                               GetStringFromAttribute( sb_szCourseNumber, mStudenC, "EquivalentCourse", "Number" );
               szCourseNumber = sb_szCourseNumber.toString( );}
                {StringBuilder sb_szCourseNumber;
               if ( szCourseNumber == null )
                  sb_szCourseNumber = new StringBuilder( 32 );
               else
                  sb_szCourseNumber = new StringBuilder( szCourseNumber );
                              ZeidonStringConcat( sb_szCourseNumber, 1, 0, " - ", 1, 0, 51 );
               szCourseNumber = sb_szCourseNumber.toString( );}
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
               StringBuilder sb_szTempString_2;
               if ( szTempString_2 == null )
                  sb_szTempString_2 = new StringBuilder( 32 );
               else
                  sb_szTempString_2 = new StringBuilder( szTempString_2 );
                               GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_6, 'S', 101, mStudenC, "Registration", "ForeignClassName", "", 0 );
               lTempInteger_6 = mi_lTempInteger_6.intValue( );
               szTempString_2 = sb_szTempString_2.toString( );}
                {StringBuilder sb_szCourseNumber;
               if ( szCourseNumber == null )
                  sb_szCourseNumber = new StringBuilder( 32 );
               else
                  sb_szCourseNumber = new StringBuilder( szCourseNumber );
                              ZeidonStringConcat( sb_szCourseNumber, 1, 0, szTempString_2, 1, 0, 51 );
               szCourseNumber = sb_szCourseNumber.toString( );}
               //:ELSE 
            } 
            else
            { 
               //:szCourseNumber = ""
                {StringBuilder sb_szCourseNumber;
               if ( szCourseNumber == null )
                  sb_szCourseNumber = new StringBuilder( 32 );
               else
                  sb_szCourseNumber = new StringBuilder( szCourseNumber );
                              ZeidonStringCopy( sb_szCourseNumber, 1, 0, "", 1, 0, 51 );
               szCourseNumber = sb_szCourseNumber.toString( );}
            } 

            //:END
         } 

         //:END 
         //:StoreStringInRecord ( mStudenC,
         //:                   InternalEntityStructure, InternalAttribStructure, szCourseNumber )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szCourseNumber );
         //:DropView( mStudenC )
         DropView( mStudenC );
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
//:GenerateHS_ReqGroups( VIEW mStudenC BASED ON LOD mStudenC,
//:                      STRING ( 50 ) StudentTestingStatus )

//:   VIEW mDegTrkR BASED ON LOD mDegTrkR
public int 
omStudenC_GenerateHS_ReqGroups( View     mStudenC,
                                String   StudentTestingStatus )
{
   zVIEW    mDegTrkR = new zVIEW( );
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   zVIEW    vTempViewVar_1 = new zVIEW( );


   //:// Create the mStudenC.HS_RequiredGroup subobjects.

   //:// Remove all original entries.
   //:FOR EACH mStudenC.HS_RequiredGroup
   RESULT = SetCursorFirstEntity( mStudenC, "HS_RequiredGroup", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:EXCLUDE mStudenC.HS_RequiredGroup NONE 
      RESULT = ExcludeEntity( mStudenC, "HS_RequiredGroup", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mStudenC, "HS_RequiredGroup", "" );
   } 

   //:END

   //:// First, generate any group directly assigned to this Student.
   //:FOR EACH mStudenC.OrigHS_RequiredGroup 
   RESULT = SetCursorFirstEntity( mStudenC, "OrigHS_RequiredGroup", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:INCLUDE mStudenC.HS_RequiredGroup FROM mStudenC.OrigHS_RequiredGroup 
      RESULT = IncludeSubobjectFromSubobject( mStudenC, "HS_RequiredGroup", mStudenC, "OrigHS_RequiredGroup", zPOS_AFTER );
      RESULT = SetCursorNextEntity( mStudenC, "OrigHS_RequiredGroup", "" );
   } 

   //:END

   //:// Next generate the group defined by the StudentTestingStatus.
   //:IF StudentTestingStatus != ""
   if ( ZeidonStringCompare( StudentTestingStatus, 1, 0, "", 1, 0, 51 ) != 0 )
   { 
      //:GET VIEW mDegTrkR NAMED "HS_ReqGroups"
      RESULT = GetViewByName( mDegTrkR, "HS_ReqGroups", mStudenC, zLEVEL_TASK );
      //:IF RESULT < 0
      if ( RESULT < 0 )
      { 
         //:ACTIVATE mDegTrkR WHERE mDegTrkR.RequiredGroup.StudentTestingStatus = StudentTestingStatus
         omStudenC_fnLocalBuildQual_1( mStudenC, vTempViewVar_0, StudentTestingStatus );
         RESULT = ActivateObjectInstance( mDegTrkR, "mDegTrkR", mStudenC, vTempViewVar_0, zSINGLE );
         DropView( vTempViewVar_0 );
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 
            //:NAME VIEW mDegTrkR "HS_ReqGroups"
            SetNameForView( mDegTrkR, "HS_ReqGroups", null, zLEVEL_TASK );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF mDegTrkR.RequiredGroup.StudentTestingStatus != StudentTestingStatus
         if ( CompareAttributeToString( mDegTrkR, "RequiredGroup", "StudentTestingStatus", StudentTestingStatus ) != 0 )
         { 
            //:DropObjectInstance( mDegTrkR )
            DropObjectInstance( mDegTrkR );
            //:ACTIVATE mDegTrkR WHERE mDegTrkR.RequiredGroup.StudentTestingStatus = StudentTestingStatus
            omStudenC_fnLocalBuildQual_2( mStudenC, vTempViewVar_1, StudentTestingStatus );
            RESULT = ActivateObjectInstance( mDegTrkR, "mDegTrkR", mStudenC, vTempViewVar_1, zSINGLE );
            DropView( vTempViewVar_1 );
            //:IF RESULT >= 0
            if ( RESULT >= 0 )
            { 
               //:NAME VIEW mDegTrkR "HS_ReqGroups"
               SetNameForView( mDegTrkR, "HS_ReqGroups", null, zLEVEL_TASK );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:RESULT = 0
            RESULT = 0;
         } 

         //:END
      } 

      //:END
      //:   
      //:IF RESULT < 0
      if ( RESULT < 0 )
      { 
         //:MessageSend( mStudenC, "", "Generate HS Groups",
         //:             "The Testing Status for this Student does not have a corresponding Required Group entry.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mStudenC, "", "Generate HS Groups", "The Testing Status for this Student does not have a corresponding Required Group entry.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
      //:INCLUDE mStudenC.HS_RequiredGroup FROM mDegTrkR.RequiredGroup 
      RESULT = IncludeSubobjectFromSubobject( mStudenC, "HS_RequiredGroup", mDegTrkR, "RequiredGroup", zPOS_AFTER );
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dManualRepeatCourseID( VIEW mStudenC BASED ON LOD mStudenC,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )
//:   
//:   VIEW mStudenC2 BASED ON LOD mStudenC
public int 
omStudenC_dManualRepeatCourseID( View     mStudenC,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   zVIEW    mStudenC2 = new zVIEW( );
   //:STRING ( 20 ) szCourseNumber
   String   szCourseNumber = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// For a manually repeating or repeated Registration entry, this is the Course Number identifying the
         //:// repeating or repeated Course.
         //:IF mStudenC.Registration.ManuallyRepeatedRegistrationID != "" OR
         //:mStudenC.Registration.ManuallyRepeatingRegistrationID != ""
         if ( CompareAttributeToString( mStudenC, "Registration", "ManuallyRepeatedRegistrationID", "" ) != 0 || CompareAttributeToString( mStudenC, "Registration", "ManuallyRepeatingRegistrationID", "" ) != 0 )
         { 

            //:CreateViewFromView( mStudenC2, mStudenC )
            CreateViewFromView( mStudenC2, mStudenC );
            //:IF mStudenC.Registration.ManuallyRepeatedRegistrationID != ""
            if ( CompareAttributeToString( mStudenC, "Registration", "ManuallyRepeatedRegistrationID", "" ) != 0 )
            { 
               //:SET CURSOR FIRST mStudenC2.Registration WHERE mStudenC2.Registration.ID = mStudenC.Registration.ManuallyRepeatedRegistrationID
               {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                               GetIntegerFromAttribute( mi_lTempInteger_0, mStudenC, "Registration", "ManuallyRepeatedRegistrationID" );
               lTempInteger_0 = mi_lTempInteger_0.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mStudenC2, "Registration", "ID", lTempInteger_0, "" );
               //:ELSE
            } 
            else
            { 
               //:SET CURSOR FIRST mStudenC2.Registration WHERE mStudenC2.Registration.ID = mStudenC.Registration.ManuallyRepeatingRegistrationID 
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                               GetIntegerFromAttribute( mi_lTempInteger_1, mStudenC, "Registration", "ManuallyRepeatingRegistrationID" );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mStudenC2, "Registration", "ID", lTempInteger_1, "" );
            } 

            //:END
            //:szCourseNumber = mStudenC2.Registration.wCourseNumber 
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szCourseNumber;
            if ( szCourseNumber == null )
               sb_szCourseNumber = new StringBuilder( 32 );
            else
               sb_szCourseNumber = new StringBuilder( szCourseNumber );
                         GetVariableFromAttribute( sb_szCourseNumber, mi_lTempInteger_2, 'S', 21, mStudenC2, "Registration", "wCourseNumber", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szCourseNumber = sb_szCourseNumber.toString( );}
            //:DropView( mStudenC2 )
            DropView( mStudenC2 );
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mStudenC,
         //:                   InternalEntityStructure, InternalAttribStructure, szCourseNumber )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szCourseNumber );
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
//:dEnrollWaitClassNumber( VIEW mStudenC BASED ON LOD mStudenC,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   STRING ( 32 )  szEntityName
public int 
omStudenC_dEnrollWaitClassNumber( View     mStudenC,
                                  String InternalEntityStructure,
                                  String InternalAttribStructure,
                                  Integer   GetOrSetFlag )
{
   String   szEntityName = null;
   //:STRING ( 20 )  szClassNumber
   String   szClassNumber = null;
   //:STRING ( 10 )  szCourseNumber
   String   szCourseNumber = null;
   //:STRING ( 3 )   szTopicNumber
   String   szTopicNumber = null;
   //:STRING ( 3 )   szSection
   String   szSection = null;
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


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:szClassNumber = ""
          {StringBuilder sb_szClassNumber;
         if ( szClassNumber == null )
            sb_szClassNumber = new StringBuilder( 32 );
         else
            sb_szClassNumber = new StringBuilder( szClassNumber );
                  ZeidonStringCopy( sb_szClassNumber, 1, 0, "", 1, 0, 21 );
         szClassNumber = sb_szClassNumber.toString( );}
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mStudenC );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "Registration"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "Registration", 1, 0, 33 ) == 0 )
         { 
            //:IF mStudenC.RegistrationClass EXISTS
            lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "RegistrationClass" );
            if ( lTempInteger_0 == 0 )
            { 
               //:IF mStudenC.CrossListedCourse EXISTS
               lTempInteger_1 = CheckExistenceOfEntity( mStudenC, "CrossListedCourse" );
               if ( lTempInteger_1 == 0 )
               { 
                  //:szCourseNumber = mStudenC.CrossListedCourse.Number 
                  {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                  StringBuilder sb_szCourseNumber;
                  if ( szCourseNumber == null )
                     sb_szCourseNumber = new StringBuilder( 32 );
                  else
                     sb_szCourseNumber = new StringBuilder( szCourseNumber );
                                     GetVariableFromAttribute( sb_szCourseNumber, mi_lTempInteger_2, 'S', 11, mStudenC, "CrossListedCourse", "Number", "", 0 );
                  lTempInteger_2 = mi_lTempInteger_2.intValue( );
                  szCourseNumber = sb_szCourseNumber.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szCourseNumber = mStudenC.RegistrationCourse.Number 
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szCourseNumber;
                  if ( szCourseNumber == null )
                     sb_szCourseNumber = new StringBuilder( 32 );
                  else
                     sb_szCourseNumber = new StringBuilder( szCourseNumber );
                                     GetVariableFromAttribute( sb_szCourseNumber, mi_lTempInteger_3, 'S', 11, mStudenC, "RegistrationCourse", "Number", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szCourseNumber = sb_szCourseNumber.toString( );}
               } 

               //:END
               //:IF mStudenC.CourseTopic EXISTS
               lTempInteger_4 = CheckExistenceOfEntity( mStudenC, "CourseTopic" );
               if ( lTempInteger_4 == 0 )
               { 
                  //:szTopicNumber = mStudenC.CourseTopic.Number 
                  {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                  StringBuilder sb_szTopicNumber;
                  if ( szTopicNumber == null )
                     sb_szTopicNumber = new StringBuilder( 32 );
                  else
                     sb_szTopicNumber = new StringBuilder( szTopicNumber );
                                     GetVariableFromAttribute( sb_szTopicNumber, mi_lTempInteger_5, 'S', 4, mStudenC, "CourseTopic", "Number", "", 0 );
                  lTempInteger_5 = mi_lTempInteger_5.intValue( );
                  szTopicNumber = sb_szTopicNumber.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szTopicNumber = ""
                   {StringBuilder sb_szTopicNumber;
                  if ( szTopicNumber == null )
                     sb_szTopicNumber = new StringBuilder( 32 );
                  else
                     sb_szTopicNumber = new StringBuilder( szTopicNumber );
                                    ZeidonStringCopy( sb_szTopicNumber, 1, 0, "", 1, 0, 4 );
                  szTopicNumber = sb_szTopicNumber.toString( );}
               } 

               //:END
               //:szSection = mStudenC.RegistrationClass.Section 
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
               StringBuilder sb_szSection;
               if ( szSection == null )
                  sb_szSection = new StringBuilder( 32 );
               else
                  sb_szSection = new StringBuilder( szSection );
                               GetVariableFromAttribute( sb_szSection, mi_lTempInteger_6, 'S', 4, mStudenC, "RegistrationClass", "Section", "", 0 );
               lTempInteger_6 = mi_lTempInteger_6.intValue( );
               szSection = sb_szSection.toString( );}
               //:IF szSection != "" OR szTopicNumber != ""
               if ( ZeidonStringCompare( szSection, 1, 0, "", 1, 0, 4 ) != 0 || ZeidonStringCompare( szTopicNumber, 1, 0, "", 1, 0, 4 ) != 0 )
               { 
                  //:szClassNumber = szCourseNumber + "-" + szTopicNumber + szSection
                   {StringBuilder sb_szClassNumber;
                  if ( szClassNumber == null )
                     sb_szClassNumber = new StringBuilder( 32 );
                  else
                     sb_szClassNumber = new StringBuilder( szClassNumber );
                                    ZeidonStringCopy( sb_szClassNumber, 1, 0, szCourseNumber, 1, 0, 21 );
                  szClassNumber = sb_szClassNumber.toString( );}
                   {StringBuilder sb_szClassNumber;
                  if ( szClassNumber == null )
                     sb_szClassNumber = new StringBuilder( 32 );
                  else
                     sb_szClassNumber = new StringBuilder( szClassNumber );
                                    ZeidonStringConcat( sb_szClassNumber, 1, 0, "-", 1, 0, 21 );
                  szClassNumber = sb_szClassNumber.toString( );}
                   {StringBuilder sb_szClassNumber;
                  if ( szClassNumber == null )
                     sb_szClassNumber = new StringBuilder( 32 );
                  else
                     sb_szClassNumber = new StringBuilder( szClassNumber );
                                    ZeidonStringConcat( sb_szClassNumber, 1, 0, szTopicNumber, 1, 0, 21 );
                  szClassNumber = sb_szClassNumber.toString( );}
                   {StringBuilder sb_szClassNumber;
                  if ( szClassNumber == null )
                     sb_szClassNumber = new StringBuilder( 32 );
                  else
                     sb_szClassNumber = new StringBuilder( szClassNumber );
                                    ZeidonStringConcat( sb_szClassNumber, 1, 0, szSection, 1, 0, 21 );
                  szClassNumber = sb_szClassNumber.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szClassNumber = szCourseNumber
                   {StringBuilder sb_szClassNumber;
                  if ( szClassNumber == null )
                     sb_szClassNumber = new StringBuilder( 32 );
                  else
                     sb_szClassNumber = new StringBuilder( szClassNumber );
                                    ZeidonStringCopy( sb_szClassNumber, 1, 0, szCourseNumber, 1, 0, 21 );
                  szClassNumber = sb_szClassNumber.toString( );}
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF mStudenC.EquivalentCourse EXISTS
               lTempInteger_7 = CheckExistenceOfEntity( mStudenC, "EquivalentCourse" );
               if ( lTempInteger_7 == 0 )
               { 
                  //:szClassNumber = szCourseNumber
                   {StringBuilder sb_szClassNumber;
                  if ( szClassNumber == null )
                     sb_szClassNumber = new StringBuilder( 32 );
                  else
                     sb_szClassNumber = new StringBuilder( szClassNumber );
                                    ZeidonStringCopy( sb_szClassNumber, 1, 0, szCourseNumber, 1, 0, 21 );
                  szClassNumber = sb_szClassNumber.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szClassNumber = ""
                   {StringBuilder sb_szClassNumber;
                  if ( szClassNumber == null )
                     sb_szClassNumber = new StringBuilder( 32 );
                  else
                     sb_szClassNumber = new StringBuilder( szClassNumber );
                                    ZeidonStringCopy( sb_szClassNumber, 1, 0, "", 1, 0, 21 );
                  szClassNumber = sb_szClassNumber.toString( );}
               } 

               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF szEntityName = "StudentWaitlisted"
            if ( ZeidonStringCompare( szEntityName, 1, 0, "StudentWaitlisted", 1, 0, 33 ) == 0 )
            { 
               //:IF mStudenC.StudentWaitlistedXListedCourse EXISTS
               lTempInteger_8 = CheckExistenceOfEntity( mStudenC, "StudentWaitlistedXListedCourse" );
               if ( lTempInteger_8 == 0 )
               { 
                  //:szCourseNumber = mStudenC.StudentWaitlistedXListedCourse.Number 
                  {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
                  StringBuilder sb_szCourseNumber;
                  if ( szCourseNumber == null )
                     sb_szCourseNumber = new StringBuilder( 32 );
                  else
                     sb_szCourseNumber = new StringBuilder( szCourseNumber );
                                     GetVariableFromAttribute( sb_szCourseNumber, mi_lTempInteger_9, 'S', 11, mStudenC, "StudentWaitlistedXListedCourse", "Number", "", 0 );
                  lTempInteger_9 = mi_lTempInteger_9.intValue( );
                  szCourseNumber = sb_szCourseNumber.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szCourseNumber = mStudenC.StudentWaitlistedCourse.Number 
                  {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
                  StringBuilder sb_szCourseNumber;
                  if ( szCourseNumber == null )
                     sb_szCourseNumber = new StringBuilder( 32 );
                  else
                     sb_szCourseNumber = new StringBuilder( szCourseNumber );
                                     GetVariableFromAttribute( sb_szCourseNumber, mi_lTempInteger_10, 'S', 11, mStudenC, "StudentWaitlistedCourse", "Number", "", 0 );
                  lTempInteger_10 = mi_lTempInteger_10.intValue( );
                  szCourseNumber = sb_szCourseNumber.toString( );}
               } 

               //:END
               //:IF mStudenC.StudentWaitlistedCourseTopic EXISTS
               lTempInteger_11 = CheckExistenceOfEntity( mStudenC, "StudentWaitlistedCourseTopic" );
               if ( lTempInteger_11 == 0 )
               { 
                  //:szTopicNumber = mStudenC.StudentWaitlistedCourseTopic.Number 
                  {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
                  StringBuilder sb_szTopicNumber;
                  if ( szTopicNumber == null )
                     sb_szTopicNumber = new StringBuilder( 32 );
                  else
                     sb_szTopicNumber = new StringBuilder( szTopicNumber );
                                     GetVariableFromAttribute( sb_szTopicNumber, mi_lTempInteger_12, 'S', 4, mStudenC, "StudentWaitlistedCourseTopic", "Number", "", 0 );
                  lTempInteger_12 = mi_lTempInteger_12.intValue( );
                  szTopicNumber = sb_szTopicNumber.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szTopicNumber = ""
                   {StringBuilder sb_szTopicNumber;
                  if ( szTopicNumber == null )
                     sb_szTopicNumber = new StringBuilder( 32 );
                  else
                     sb_szTopicNumber = new StringBuilder( szTopicNumber );
                                    ZeidonStringCopy( sb_szTopicNumber, 1, 0, "", 1, 0, 4 );
                  szTopicNumber = sb_szTopicNumber.toString( );}
               } 

               //:END
               //:szSection = mStudenC.StudentWaitlistedClass.Section 
               {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
               StringBuilder sb_szSection;
               if ( szSection == null )
                  sb_szSection = new StringBuilder( 32 );
               else
                  sb_szSection = new StringBuilder( szSection );
                               GetVariableFromAttribute( sb_szSection, mi_lTempInteger_13, 'S', 4, mStudenC, "StudentWaitlistedClass", "Section", "", 0 );
               lTempInteger_13 = mi_lTempInteger_13.intValue( );
               szSection = sb_szSection.toString( );}
               //:IF szSection != "" OR szTopicNumber != ""
               if ( ZeidonStringCompare( szSection, 1, 0, "", 1, 0, 4 ) != 0 || ZeidonStringCompare( szTopicNumber, 1, 0, "", 1, 0, 4 ) != 0 )
               { 
                  //:szClassNumber = szCourseNumber + "-" + szTopicNumber + szSection
                   {StringBuilder sb_szClassNumber;
                  if ( szClassNumber == null )
                     sb_szClassNumber = new StringBuilder( 32 );
                  else
                     sb_szClassNumber = new StringBuilder( szClassNumber );
                                    ZeidonStringCopy( sb_szClassNumber, 1, 0, szCourseNumber, 1, 0, 21 );
                  szClassNumber = sb_szClassNumber.toString( );}
                   {StringBuilder sb_szClassNumber;
                  if ( szClassNumber == null )
                     sb_szClassNumber = new StringBuilder( 32 );
                  else
                     sb_szClassNumber = new StringBuilder( szClassNumber );
                                    ZeidonStringConcat( sb_szClassNumber, 1, 0, "-", 1, 0, 21 );
                  szClassNumber = sb_szClassNumber.toString( );}
                   {StringBuilder sb_szClassNumber;
                  if ( szClassNumber == null )
                     sb_szClassNumber = new StringBuilder( 32 );
                  else
                     sb_szClassNumber = new StringBuilder( szClassNumber );
                                    ZeidonStringConcat( sb_szClassNumber, 1, 0, szTopicNumber, 1, 0, 21 );
                  szClassNumber = sb_szClassNumber.toString( );}
                   {StringBuilder sb_szClassNumber;
                  if ( szClassNumber == null )
                     sb_szClassNumber = new StringBuilder( 32 );
                  else
                     sb_szClassNumber = new StringBuilder( szClassNumber );
                                    ZeidonStringConcat( sb_szClassNumber, 1, 0, szSection, 1, 0, 21 );
                  szClassNumber = sb_szClassNumber.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szClassNumber = szCourseNumber
                   {StringBuilder sb_szClassNumber;
                  if ( szClassNumber == null )
                     sb_szClassNumber = new StringBuilder( 32 );
                  else
                     sb_szClassNumber = new StringBuilder( szClassNumber );
                                    ZeidonStringCopy( sb_szClassNumber, 1, 0, szCourseNumber, 1, 0, 21 );
                  szClassNumber = sb_szClassNumber.toString( );}
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF szEntityName = "US_Registration"
               if ( ZeidonStringCompare( szEntityName, 1, 0, "US_Registration", 1, 0, 33 ) == 0 )
               { 
                  //:IF mStudenC.US_Class EXISTS
                  lTempInteger_14 = CheckExistenceOfEntity( mStudenC, "US_Class" );
                  if ( lTempInteger_14 == 0 )
                  { 
                     //:IF mStudenC.US_CrossListedCourse EXISTS
                     lTempInteger_15 = CheckExistenceOfEntity( mStudenC, "US_CrossListedCourse" );
                     if ( lTempInteger_15 == 0 )
                     { 
                        //:szCourseNumber = mStudenC.US_CrossListedCourse.Number 
                        {MutableInt mi_lTempInteger_16 = new MutableInt( lTempInteger_16 );
                        StringBuilder sb_szCourseNumber;
                        if ( szCourseNumber == null )
                           sb_szCourseNumber = new StringBuilder( 32 );
                        else
                           sb_szCourseNumber = new StringBuilder( szCourseNumber );
                                                 GetVariableFromAttribute( sb_szCourseNumber, mi_lTempInteger_16, 'S', 11, mStudenC, "US_CrossListedCourse", "Number", "", 0 );
                        lTempInteger_16 = mi_lTempInteger_16.intValue( );
                        szCourseNumber = sb_szCourseNumber.toString( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:szCourseNumber = mStudenC.US_Course.Number 
                        {MutableInt mi_lTempInteger_17 = new MutableInt( lTempInteger_17 );
                        StringBuilder sb_szCourseNumber;
                        if ( szCourseNumber == null )
                           sb_szCourseNumber = new StringBuilder( 32 );
                        else
                           sb_szCourseNumber = new StringBuilder( szCourseNumber );
                                                 GetVariableFromAttribute( sb_szCourseNumber, mi_lTempInteger_17, 'S', 11, mStudenC, "US_Course", "Number", "", 0 );
                        lTempInteger_17 = mi_lTempInteger_17.intValue( );
                        szCourseNumber = sb_szCourseNumber.toString( );}
                     } 

                     //:END
                     //:IF mStudenC.US_CourseTopic EXISTS
                     lTempInteger_18 = CheckExistenceOfEntity( mStudenC, "US_CourseTopic" );
                     if ( lTempInteger_18 == 0 )
                     { 
                        //:szTopicNumber = mStudenC.US_CourseTopic.Number 
                        {MutableInt mi_lTempInteger_19 = new MutableInt( lTempInteger_19 );
                        StringBuilder sb_szTopicNumber;
                        if ( szTopicNumber == null )
                           sb_szTopicNumber = new StringBuilder( 32 );
                        else
                           sb_szTopicNumber = new StringBuilder( szTopicNumber );
                                                 GetVariableFromAttribute( sb_szTopicNumber, mi_lTempInteger_19, 'S', 4, mStudenC, "US_CourseTopic", "Number", "", 0 );
                        lTempInteger_19 = mi_lTempInteger_19.intValue( );
                        szTopicNumber = sb_szTopicNumber.toString( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:szTopicNumber = ""
                         {StringBuilder sb_szTopicNumber;
                        if ( szTopicNumber == null )
                           sb_szTopicNumber = new StringBuilder( 32 );
                        else
                           sb_szTopicNumber = new StringBuilder( szTopicNumber );
                                                ZeidonStringCopy( sb_szTopicNumber, 1, 0, "", 1, 0, 4 );
                        szTopicNumber = sb_szTopicNumber.toString( );}
                     } 

                     //:END
                     //:szSection = mStudenC.US_Class.Section 
                     {MutableInt mi_lTempInteger_20 = new MutableInt( lTempInteger_20 );
                     StringBuilder sb_szSection;
                     if ( szSection == null )
                        sb_szSection = new StringBuilder( 32 );
                     else
                        sb_szSection = new StringBuilder( szSection );
                                           GetVariableFromAttribute( sb_szSection, mi_lTempInteger_20, 'S', 4, mStudenC, "US_Class", "Section", "", 0 );
                     lTempInteger_20 = mi_lTempInteger_20.intValue( );
                     szSection = sb_szSection.toString( );}
                     //:IF szSection != "" OR szTopicNumber != ""
                     if ( ZeidonStringCompare( szSection, 1, 0, "", 1, 0, 4 ) != 0 || ZeidonStringCompare( szTopicNumber, 1, 0, "", 1, 0, 4 ) != 0 )
                     { 
                        //:szClassNumber = szCourseNumber + "-" + szTopicNumber + szSection
                         {StringBuilder sb_szClassNumber;
                        if ( szClassNumber == null )
                           sb_szClassNumber = new StringBuilder( 32 );
                        else
                           sb_szClassNumber = new StringBuilder( szClassNumber );
                                                ZeidonStringCopy( sb_szClassNumber, 1, 0, szCourseNumber, 1, 0, 21 );
                        szClassNumber = sb_szClassNumber.toString( );}
                         {StringBuilder sb_szClassNumber;
                        if ( szClassNumber == null )
                           sb_szClassNumber = new StringBuilder( 32 );
                        else
                           sb_szClassNumber = new StringBuilder( szClassNumber );
                                                ZeidonStringConcat( sb_szClassNumber, 1, 0, "-", 1, 0, 21 );
                        szClassNumber = sb_szClassNumber.toString( );}
                         {StringBuilder sb_szClassNumber;
                        if ( szClassNumber == null )
                           sb_szClassNumber = new StringBuilder( 32 );
                        else
                           sb_szClassNumber = new StringBuilder( szClassNumber );
                                                ZeidonStringConcat( sb_szClassNumber, 1, 0, szTopicNumber, 1, 0, 21 );
                        szClassNumber = sb_szClassNumber.toString( );}
                         {StringBuilder sb_szClassNumber;
                        if ( szClassNumber == null )
                           sb_szClassNumber = new StringBuilder( 32 );
                        else
                           sb_szClassNumber = new StringBuilder( szClassNumber );
                                                ZeidonStringConcat( sb_szClassNumber, 1, 0, szSection, 1, 0, 21 );
                        szClassNumber = sb_szClassNumber.toString( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:szClassNumber = szCourseNumber
                         {StringBuilder sb_szClassNumber;
                        if ( szClassNumber == null )
                           sb_szClassNumber = new StringBuilder( 32 );
                        else
                           sb_szClassNumber = new StringBuilder( szClassNumber );
                                                ZeidonStringCopy( sb_szClassNumber, 1, 0, szCourseNumber, 1, 0, 21 );
                        szClassNumber = sb_szClassNumber.toString( );}
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

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mStudenC,
         //:                   InternalEntityStructure, InternalAttribStructure, szClassNumber )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szClassNumber );
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
//:dAnticipatedClassif( VIEW mStudenC BASED ON LOD mStudenC,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   VIEW sHost    BASED ON LOD  sHost
public int 
omStudenC_dAnticipatedClassif( View     mStudenC,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   zVIEW    sHost = new zVIEW( );
   //:VIEW lTrnscpt BASED ON LOD  lTrnscpt
   zVIEW    lTrnscpt = new zVIEW( );
   //:VIEW lClsLstC BASED ON LOD  lClsLstC
   zVIEW    lClsLstC = new zVIEW( );
   //:STRING ( 2 )   szClassification
   String   szClassification = null;
   //:STRING ( 1 )   szCollegeType
   String   szCollegeType = null;
   //:STRING ( 20 )  szCollegeYearSemester
   String   szCollegeYearSemester = null;
   //:STRING ( 20 )  szCurrentCollegeYearSemester
   String   szCurrentCollegeYearSemester = null;
   //:STRING ( 20 )  szTranscriptCollegeYearSemester
   String   szTranscriptCollegeYearSemester = null;
   //:STRING ( 1 )   szStatus
   String   szStatus = null;
   //:STRING ( 1 )   szErrorFlag
   String   szErrorFlag = null;
   //:DECIMAL        dTotalCredits
   double  dTotalCredits = 0.0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :


         //:// It's an error if either view lClsLstC or sHost does not exist.
         //:GET VIEW lClsLstC NAMED "lClsLstC"
         RESULT = GetViewByName( lClsLstC, "lClsLstC", mStudenC, zLEVEL_TASK );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:GET VIEW lClsLstC NAMED "TermslClsLstC"
            RESULT = GetViewByName( lClsLstC, "TermslClsLstC", mStudenC, zLEVEL_TASK );
            //:IF RESULT < 0
            if ( RESULT < 0 )
            { 
               //:szErrorFlag = "Y"
                {StringBuilder sb_szErrorFlag;
               if ( szErrorFlag == null )
                  sb_szErrorFlag = new StringBuilder( 32 );
               else
                  sb_szErrorFlag = new StringBuilder( szErrorFlag );
                              ZeidonStringCopy( sb_szErrorFlag, 1, 0, "Y", 1, 0, 2 );
               szErrorFlag = sb_szErrorFlag.toString( );}
            } 

            //:END
         } 

         //:END
         //:GET VIEW sHost NAMED "sHost"
         RESULT = GetViewByName( sHost, "sHost", mStudenC, zLEVEL_TASK );
         //:IF sHost.HostStudentClassificationData DOES NOT EXIST
         lTempInteger_0 = CheckExistenceOfEntity( sHost, "HostStudentClassificationData" );
         if ( lTempInteger_0 != 0 )
         { 
            //:szErrorFlag = "Y"
             {StringBuilder sb_szErrorFlag;
            if ( szErrorFlag == null )
               sb_szErrorFlag = new StringBuilder( 32 );
            else
               sb_szErrorFlag = new StringBuilder( szErrorFlag );
                        ZeidonStringCopy( sb_szErrorFlag, 1, 0, "Y", 1, 0, 2 );
            szErrorFlag = sb_szErrorFlag.toString( );}
         } 

         //:END

         //:// Determine the anticipated Student Classification (ie., Student Academic Level) for the
         //:// CollegeTerm from the Class in the lClsLstC.
         //:// We will get the total accumulated credits from the lTranscpt object for the College Term. If we get them once,
         //:// we will store them in mStudenC.UpdateSchedule so we don't retrieve it multiple times.

         //:IF szErrorFlag = ""
         if ( ZeidonStringCompare( szErrorFlag, 1, 0, "", 1, 0, 2 ) == 0 )
         { 
            //:OrderEntityForView( sHost, "HostStudentClassificationData", "MinCreditsForClassification A" )
            OrderEntityForView( sHost, "HostStudentClassificationData", "MinCreditsForClassification A" );

            //:// Accumulate credits only if we didn't do it before.
            //:dTotalCredits = mStudenC.UpdateSchedule.dAnticipatedAccumulatedCredits 
            {MutableDouble md_dTotalCredits = new MutableDouble( dTotalCredits );
                         GetDecimalFromAttribute( md_dTotalCredits, mStudenC, "UpdateSchedule", "dAnticipatedAccumulatedCredits" );
            dTotalCredits = md_dTotalCredits.doubleValue( );}

            //:// Use accumulated credits and min credits per classification from sHost to determine Classification.
            //:SET CURSOR LAST sHost.HostStudentClassificationData WHERE sHost.HostStudentClassificationData.MinCreditsForClassification <= dTotalCredits
            RESULT = SetCursorLastEntity( sHost, "HostStudentClassificationData", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToDecimal( sHost, "HostStudentClassificationData", "MinCreditsForClassification", dTotalCredits ) > 0 ) )
               { 
                  RESULT = SetCursorPrevEntity( sHost, "HostStudentClassificationData", "" );
               } 

            } 

            //:szClassification = sHost.HostStudentClassificationData.StudentClassification
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szClassification;
            if ( szClassification == null )
               sb_szClassification = new StringBuilder( 32 );
            else
               sb_szClassification = new StringBuilder( szClassification );
                         GetVariableFromAttribute( sb_szClassification, mi_lTempInteger_1, 'S', 3, sHost, "HostStudentClassificationData", "StudentClassification", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szClassification = sb_szClassification.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szClassification = ""
             {StringBuilder sb_szClassification;
            if ( szClassification == null )
               sb_szClassification = new StringBuilder( 32 );
            else
               sb_szClassification = new StringBuilder( szClassification );
                        ZeidonStringCopy( sb_szClassification, 1, 0, "", 1, 0, 3 );
            szClassification = sb_szClassification.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mStudenC,
         //:                   InternalEntityStructure, InternalAttribStructure, szClassification )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szClassification );
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
//:dClassRoomNumber( VIEW mStudenC BASED ON LOD mStudenC,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING ( 32 )  szEntityName
public int 
omStudenC_dClassRoomNumber( View     mStudenC,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szEntityName = null;
   //:STRING ( 10 )  szRoomNumber
   String   szRoomNumber = null;
   //:STRING ( 10 )  szBuildingNumber
   String   szBuildingNumber = null;
   //:STRING ( 20 )  szBuildingRoomNumber
   String   szBuildingRoomNumber = null;
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

         //:// Code Example
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mStudenC );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "Room"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "Room", 1, 0, 33 ) == 0 )
         { 
            //:szRoomNumber     = mStudenC.Room.Number 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szRoomNumber;
            if ( szRoomNumber == null )
               sb_szRoomNumber = new StringBuilder( 32 );
            else
               sb_szRoomNumber = new StringBuilder( szRoomNumber );
                         GetVariableFromAttribute( sb_szRoomNumber, mi_lTempInteger_0, 'S', 11, mStudenC, "Room", "Number", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szRoomNumber = sb_szRoomNumber.toString( );}
            //:szBuildingNumber = mStudenC.Building.ShortName 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szBuildingNumber;
            if ( szBuildingNumber == null )
               sb_szBuildingNumber = new StringBuilder( 32 );
            else
               sb_szBuildingNumber = new StringBuilder( szBuildingNumber );
                         GetVariableFromAttribute( sb_szBuildingNumber, mi_lTempInteger_1, 'S', 11, mStudenC, "Building", "ShortName", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szBuildingNumber = sb_szBuildingNumber.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF szEntityName = "CurrentRoom"
            if ( ZeidonStringCompare( szEntityName, 1, 0, "CurrentRoom", 1, 0, 33 ) == 0 )
            { 
               //:szRoomNumber     = mStudenC.CurrentRoom.Number 
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szRoomNumber;
               if ( szRoomNumber == null )
                  sb_szRoomNumber = new StringBuilder( 32 );
               else
                  sb_szRoomNumber = new StringBuilder( szRoomNumber );
                               GetVariableFromAttribute( sb_szRoomNumber, mi_lTempInteger_2, 'S', 11, mStudenC, "CurrentRoom", "Number", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szRoomNumber = sb_szRoomNumber.toString( );}
               //:szBuildingNumber = mStudenC.CurrentBuilding.ShortName 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szBuildingNumber;
               if ( szBuildingNumber == null )
                  sb_szBuildingNumber = new StringBuilder( 32 );
               else
                  sb_szBuildingNumber = new StringBuilder( szBuildingNumber );
                               GetVariableFromAttribute( sb_szBuildingNumber, mi_lTempInteger_3, 'S', 11, mStudenC, "CurrentBuilding", "ShortName", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szBuildingNumber = sb_szBuildingNumber.toString( );}
               //:ELSE
            } 
            else
            { 
               //:IF szEntityName = "US_Room"
               if ( ZeidonStringCompare( szEntityName, 1, 0, "US_Room", 1, 0, 33 ) == 0 )
               { 
                  //:szRoomNumber     = mStudenC.US_Room.Number 
                  {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                  StringBuilder sb_szRoomNumber;
                  if ( szRoomNumber == null )
                     sb_szRoomNumber = new StringBuilder( 32 );
                  else
                     sb_szRoomNumber = new StringBuilder( szRoomNumber );
                                     GetVariableFromAttribute( sb_szRoomNumber, mi_lTempInteger_4, 'S', 11, mStudenC, "US_Room", "Number", "", 0 );
                  lTempInteger_4 = mi_lTempInteger_4.intValue( );
                  szRoomNumber = sb_szRoomNumber.toString( );}
                  //:szBuildingNumber = mStudenC.US_Building.ShortName 
                  {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                  StringBuilder sb_szBuildingNumber;
                  if ( szBuildingNumber == null )
                     sb_szBuildingNumber = new StringBuilder( 32 );
                  else
                     sb_szBuildingNumber = new StringBuilder( szBuildingNumber );
                                     GetVariableFromAttribute( sb_szBuildingNumber, mi_lTempInteger_5, 'S', 11, mStudenC, "US_Building", "ShortName", "", 0 );
                  lTempInteger_5 = mi_lTempInteger_5.intValue( );
                  szBuildingNumber = sb_szBuildingNumber.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END
         //:szBuildingRoomNumber = szBuildingNumber + szRoomNumber
          {StringBuilder sb_szBuildingRoomNumber;
         if ( szBuildingRoomNumber == null )
            sb_szBuildingRoomNumber = new StringBuilder( 32 );
         else
            sb_szBuildingRoomNumber = new StringBuilder( szBuildingRoomNumber );
                  ZeidonStringCopy( sb_szBuildingRoomNumber, 1, 0, szBuildingNumber, 1, 0, 21 );
         szBuildingRoomNumber = sb_szBuildingRoomNumber.toString( );}
          {StringBuilder sb_szBuildingRoomNumber;
         if ( szBuildingRoomNumber == null )
            sb_szBuildingRoomNumber = new StringBuilder( 32 );
         else
            sb_szBuildingRoomNumber = new StringBuilder( szBuildingRoomNumber );
                  ZeidonStringConcat( sb_szBuildingRoomNumber, 1, 0, szRoomNumber, 1, 0, 21 );
         szBuildingRoomNumber = sb_szBuildingRoomNumber.toString( );}

         //:StoreStringInRecord ( mStudenC,
         //:                   InternalEntityStructure, InternalAttribStructure, szBuildingRoomNumber )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szBuildingRoomNumber );
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
//:CheckPreReqGroup( VIEW mStudenC  BASED ON LOD mStudenC,
//:                  VIEW lCrsReqL  BASED ON LOD  lCrsReqL,
//:                  STRING ( 20 )  szPrerequisiteYearSemester )

//:   VIEW mStudenC2 BASED ON LOD  mStudenC
private int 
omStudenC_CheckPreReqGroup( View     mStudenC,
                            View     lCrsReqL,
                            String   szPrerequisiteYearSemester )
{
   zVIEW    mStudenC2 = new zVIEW( );
   //:STRING ( 10 )  szRequisiteCourseNumber
   String   szRequisiteCourseNumber = null;
   //:STRING ( 1 )   RequisiteError
   String   RequisiteError = null;
   //:SHORT nRC
   int      nRC = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:CreateViewFromView( mStudenC2, mStudenC )
   CreateViewFromView( mStudenC2, mStudenC );
   //:IF lCrsReqL.CoursePrerequisiteGroup.PrerequisiteBooleanOperator = "A"
   if ( CompareAttributeToString( lCrsReqL, "CoursePrerequisiteGroup", "PrerequisiteBooleanOperator", "A" ) == 0 )
   { 
      //:// Prerequisites are to be AND'd.
      //:RequisiteError = ""
       {StringBuilder sb_RequisiteError;
      if ( RequisiteError == null )
         sb_RequisiteError = new StringBuilder( 32 );
      else
         sb_RequisiteError = new StringBuilder( RequisiteError );
            ZeidonStringCopy( sb_RequisiteError, 1, 0, "", 1, 0, 2 );
      RequisiteError = sb_RequisiteError.toString( );}
      //:FOR EACH lCrsReqL.PrerequisiteSubCourse 
      RESULT = SetCursorFirstEntity( lCrsReqL, "PrerequisiteSubCourse", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:// Loop through all entries looking for a match on the prerequisite.
         //:szRequisiteCourseNumber = lCrsReqL.PrerequisiteSubCourse.Number
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szRequisiteCourseNumber;
         if ( szRequisiteCourseNumber == null )
            sb_szRequisiteCourseNumber = new StringBuilder( 32 );
         else
            sb_szRequisiteCourseNumber = new StringBuilder( szRequisiteCourseNumber );
                   GetVariableFromAttribute( sb_szRequisiteCourseNumber, mi_lTempInteger_0, 'S', 11, lCrsReqL, "PrerequisiteSubCourse", "Number", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szRequisiteCourseNumber = sb_szRequisiteCourseNumber.toString( );}

         //:// If the Course is a Developmental Course, we will only perform the prerequisite check if the Student is
         //:// required to take the Developmental Course. This is determined by the entries under the HS_RequiredGroup entity.
         //:// Note that we only need to make this check for the "AND" condition as all Developmental requirements are anded.
         //:IF lCrsReqL.PrerequisiteSubCourse.DevelopmentalFlag = "Y"
         if ( CompareAttributeToString( lCrsReqL, "PrerequisiteSubCourse", "DevelopmentalFlag", "Y" ) == 0 )
         { 
            //:SET CURSOR FIRST mStudenC2.HS_RequiredGroupCourse WITHIN mStudenC2.Student 
            //:           WHERE mStudenC2.HS_RequiredGroupCourse.Number = lCrsReqL.PrerequisiteSubCourse.Number 
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, lCrsReqL, "PrerequisiteSubCourse", "Number" );
            szTempString_0 = sb_szTempString_0.toString( );}
            RESULT = SetCursorFirstEntityByString( mStudenC2, "HS_RequiredGroupCourse", "Number", szTempString_0, "Student" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:nRC = FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester )
               nRC = omStudenC_FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester );
               //:ELSE
            } 
            else
            { 
               //:nRC = 0
               nRC = 0;
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:nRC = FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester )
            nRC = omStudenC_FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester );
         } 

         //:END
         //:IF nRC < 0     // The Prerequisite was NOT found.
         if ( nRC < 0 )
         { 
            //:RequisiteError = "Y"
             {StringBuilder sb_RequisiteError;
            if ( RequisiteError == null )
               sb_RequisiteError = new StringBuilder( 32 );
            else
               sb_RequisiteError = new StringBuilder( RequisiteError );
                        ZeidonStringCopy( sb_RequisiteError, 1, 0, "Y", 1, 0, 2 );
            RequisiteError = sb_RequisiteError.toString( );}
         } 

         RESULT = SetCursorNextEntity( lCrsReqL, "PrerequisiteSubCourse", "" );
         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// Prerequisites are to be OR'd.
      //:RequisiteError = "Y"
       {StringBuilder sb_RequisiteError;
      if ( RequisiteError == null )
         sb_RequisiteError = new StringBuilder( 32 );
      else
         sb_RequisiteError = new StringBuilder( RequisiteError );
            ZeidonStringCopy( sb_RequisiteError, 1, 0, "Y", 1, 0, 2 );
      RequisiteError = sb_RequisiteError.toString( );}
      //:FOR EACH lCrsReqL.PrerequisiteSubCourse
      RESULT = SetCursorFirstEntity( lCrsReqL, "PrerequisiteSubCourse", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:// Entry is for Prerequisite Course
         //:// Loop through all Registration entries looking for a match on the prerequisite.
         //:szRequisiteCourseNumber = lCrsReqL.PrerequisiteSubCourse.Number
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szRequisiteCourseNumber;
         if ( szRequisiteCourseNumber == null )
            sb_szRequisiteCourseNumber = new StringBuilder( 32 );
         else
            sb_szRequisiteCourseNumber = new StringBuilder( szRequisiteCourseNumber );
                   GetVariableFromAttribute( sb_szRequisiteCourseNumber, mi_lTempInteger_1, 'S', 11, lCrsReqL, "PrerequisiteSubCourse", "Number", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szRequisiteCourseNumber = sb_szRequisiteCourseNumber.toString( );}
         //:nRC = FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester )
         nRC = omStudenC_FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester );
         //:IF nRC = 0   // The Prerequisite WAS found.
         if ( nRC == 0 )
         { 
            //:RequisiteError = ""
             {StringBuilder sb_RequisiteError;
            if ( RequisiteError == null )
               sb_RequisiteError = new StringBuilder( 32 );
            else
               sb_RequisiteError = new StringBuilder( RequisiteError );
                        ZeidonStringCopy( sb_RequisiteError, 1, 0, "", 1, 0, 2 );
            RequisiteError = sb_RequisiteError.toString( );}
         } 

         RESULT = SetCursorNextEntity( lCrsReqL, "PrerequisiteSubCourse", "" );
         //:END
      } 

      //:END
   } 

   //:END
   //:IF RequisiteError = "Y"
   if ( ZeidonStringCompare( RequisiteError, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:DropView( mStudenC2 )
      DropView( mStudenC2 );
      //:RETURN -1
      if(8==8)return( -1 );
      //:ELSE
   } 
   else
   { 
      //:DropView( mStudenC2 )
      DropView( mStudenC2 );
      //:RETURN 0
      if(8==8)return( 0 );
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dEnrolledCreditsRepeat( VIEW mStudenC BASED ON LOD mStudenC,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   VIEW mStudenCT   BASED ON LOD  mStudenC
public int 
omStudenC_dEnrolledCreditsRepeat( View     mStudenC,
                                  String InternalEntityStructure,
                                  String InternalAttribStructure,
                                  Integer   GetOrSetFlag )
{
   zVIEW    mStudenCT = new zVIEW( );
   //:VIEW mCollegeLST REGISTERED AS mCollegeLST
   zVIEW    mCollegeLST = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL TotalCredits
   double  TotalCredits = 0.0;
   //:STRING ( 1 ) szGrade    // Grade without minus or plus.
   String   szGrade = null;
   //:STRING ( 1 ) szGradFlag
   String   szGradFlag = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   double  dTempDecimal_0 = 0.0;

   RESULT = GetViewByName( mCollegeLST, "mCollegeLST", mStudenC, zLEVEL_TASK );

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :

         //:// This is a credit total of the classes that are currently being repeated by Enrolled entries and
         //:// that were passed so that their credits were added to the earned total.
         //:CreateViewFromView( mStudenCT, mStudenC )
         CreateViewFromView( mStudenCT, mStudenC );
         //:TotalCredits = 0
         TotalCredits = 0;
         //:FOR EACH mStudenCT.Registration WHERE mStudenCT.Registration.wRepeatedClass = "AR"
         RESULT = SetCursorFirstEntityByString( mStudenCT, "Registration", "wRepeatedClass", "AR", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:szGrade = mStudenCT.Registration.FinalGrade
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szGrade;
            if ( szGrade == null )
               sb_szGrade = new StringBuilder( 32 );
            else
               sb_szGrade = new StringBuilder( szGrade );
                         GetVariableFromAttribute( sb_szGrade, mi_lTempInteger_0, 'S', 2, mStudenCT, "Registration", "FinalGrade", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szGrade = sb_szGrade.toString( );}
            //:IF szGrade = "D" OR szGrade = "C" OR szGrade = "B" OR szGrade = "A"
            if ( ZeidonStringCompare( szGrade, 1, 0, "D", 1, 0, 2 ) == 0 || ZeidonStringCompare( szGrade, 1, 0, "C", 1, 0, 2 ) == 0 || ZeidonStringCompare( szGrade, 1, 0, "B", 1, 0, 2 ) == 0 || ZeidonStringCompare( szGrade, 1, 0, "A", 1, 0, 2 ) == 0 )
            { 
               //:// Only add if this is an undergrad course. The default will be undergrad.
               //:szGradFlag = "U"
                {StringBuilder sb_szGradFlag;
               if ( szGradFlag == null )
                  sb_szGradFlag = new StringBuilder( 32 );
               else
                  sb_szGradFlag = new StringBuilder( szGradFlag );
                              ZeidonStringCopy( sb_szGradFlag, 1, 0, "U", 1, 0, 2 );
               szGradFlag = sb_szGradFlag.toString( );}
               //:IF mStudenCT.RegistrationCourseCollege EXISTS
               lTempInteger_1 = CheckExistenceOfEntity( mStudenCT, "RegistrationCourseCollege" );
               if ( lTempInteger_1 == 0 )
               { 
                  //:SET CURSOR FIRST mCollegeLST.College WHERE mCollegeLST.College.ID = mStudenCT.RegistrationCourseCollege.ID 
                  {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                                     GetIntegerFromAttribute( mi_lTempInteger_2, mStudenCT, "RegistrationCourseCollege", "ID" );
                  lTempInteger_2 = mi_lTempInteger_2.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mCollegeLST, "College", "ID", lTempInteger_2, "" );
                  //:IF mCollegeLST.College.Type = "G"
                  if ( CompareAttributeToString( mCollegeLST, "College", "Type", "G" ) == 0 )
                  { 
                     //:szGradFlag = "G"
                      {StringBuilder sb_szGradFlag;
                     if ( szGradFlag == null )
                        sb_szGradFlag = new StringBuilder( 32 );
                     else
                        sb_szGradFlag = new StringBuilder( szGradFlag );
                                          ZeidonStringCopy( sb_szGradFlag, 1, 0, "G", 1, 0, 2 );
                     szGradFlag = sb_szGradFlag.toString( );}
                  } 

                  //:END 
               } 

               //:END
               //:IF szGradFlag = "U"
               if ( ZeidonStringCompare( szGradFlag, 1, 0, "U", 1, 0, 2 ) == 0 )
               { 
                  //:TotalCredits = TotalCredits + mStudenCT.Registration.CreditHours 
                  {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                     GetDecimalFromAttribute( md_dTempDecimal_0, mStudenCT, "Registration", "CreditHours" );
                  dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                  TotalCredits = TotalCredits + dTempDecimal_0;
               } 

               //:END
            } 

            RESULT = SetCursorNextEntityByString( mStudenCT, "Registration", "wRepeatedClass", "AR", "" );

            //:END
         } 

         //:END
         //:DropView( mStudenCT )
         DropView( mStudenCT );

         //:StoreValueInRecord ( mStudenC,
         //:                  InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 )
         StoreValueInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 );
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
//:dEnrolledCreditsRpGrad( VIEW mStudenC BASED ON LOD mStudenC,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   VIEW mStudenCT   BASED ON LOD  mStudenC
public int 
omStudenC_dEnrolledCreditsRpGrad( View     mStudenC,
                                  String InternalEntityStructure,
                                  String InternalAttribStructure,
                                  Integer   GetOrSetFlag )
{
   zVIEW    mStudenCT = new zVIEW( );
   //:VIEW mCollegeLST REGISTERED AS mCollegeLST
   zVIEW    mCollegeLST = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL TotalCredits
   double  TotalCredits = 0.0;
   //:STRING ( 1 ) szGrade    // Grade without minus or plus.
   String   szGrade = null;
   //:STRING ( 1 ) szGradFlag
   String   szGradFlag = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   double  dTempDecimal_0 = 0.0;

   RESULT = GetViewByName( mCollegeLST, "mCollegeLST", mStudenC, zLEVEL_TASK );

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :

         //:// This is a credit total of the classes that are currently being repeated by Enrolled entries and
         //:// that were passed so that their credits were added to the earned total.
         //:CreateViewFromView( mStudenCT, mStudenC )
         CreateViewFromView( mStudenCT, mStudenC );
         //:TotalCredits = 0
         TotalCredits = 0;
         //:FOR EACH mStudenCT.Registration WHERE mStudenCT.Registration.wRepeatedClass = "AR"
         RESULT = SetCursorFirstEntityByString( mStudenCT, "Registration", "wRepeatedClass", "AR", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:szGrade = mStudenCT.Registration.FinalGrade
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szGrade;
            if ( szGrade == null )
               sb_szGrade = new StringBuilder( 32 );
            else
               sb_szGrade = new StringBuilder( szGrade );
                         GetVariableFromAttribute( sb_szGrade, mi_lTempInteger_0, 'S', 2, mStudenCT, "Registration", "FinalGrade", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szGrade = sb_szGrade.toString( );}
            //:IF szGrade = "D" OR szGrade = "C" OR szGrade = "B" OR szGrade = "A"
            if ( ZeidonStringCompare( szGrade, 1, 0, "D", 1, 0, 2 ) == 0 || ZeidonStringCompare( szGrade, 1, 0, "C", 1, 0, 2 ) == 0 || ZeidonStringCompare( szGrade, 1, 0, "B", 1, 0, 2 ) == 0 || ZeidonStringCompare( szGrade, 1, 0, "A", 1, 0, 2 ) == 0 )
            { 
               //:// Only add if this is an undergrad course. The default will be undergrad.
               //:szGradFlag = "U"
                {StringBuilder sb_szGradFlag;
               if ( szGradFlag == null )
                  sb_szGradFlag = new StringBuilder( 32 );
               else
                  sb_szGradFlag = new StringBuilder( szGradFlag );
                              ZeidonStringCopy( sb_szGradFlag, 1, 0, "U", 1, 0, 2 );
               szGradFlag = sb_szGradFlag.toString( );}
               //:IF mStudenCT.RegistrationCourseCollege EXISTS
               lTempInteger_1 = CheckExistenceOfEntity( mStudenCT, "RegistrationCourseCollege" );
               if ( lTempInteger_1 == 0 )
               { 
                  //:SET CURSOR FIRST mCollegeLST.College WHERE mCollegeLST.College.ID = mStudenCT.RegistrationCourseCollege.ID 
                  {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                                     GetIntegerFromAttribute( mi_lTempInteger_2, mStudenCT, "RegistrationCourseCollege", "ID" );
                  lTempInteger_2 = mi_lTempInteger_2.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mCollegeLST, "College", "ID", lTempInteger_2, "" );
                  //:IF mCollegeLST.College.Type = "G"
                  if ( CompareAttributeToString( mCollegeLST, "College", "Type", "G" ) == 0 )
                  { 
                     //:szGradFlag = "G"
                      {StringBuilder sb_szGradFlag;
                     if ( szGradFlag == null )
                        sb_szGradFlag = new StringBuilder( 32 );
                     else
                        sb_szGradFlag = new StringBuilder( szGradFlag );
                                          ZeidonStringCopy( sb_szGradFlag, 1, 0, "G", 1, 0, 2 );
                     szGradFlag = sb_szGradFlag.toString( );}
                  } 

                  //:END 
               } 

               //:END
               //:IF szGradFlag = "G"
               if ( ZeidonStringCompare( szGradFlag, 1, 0, "G", 1, 0, 2 ) == 0 )
               { 
                  //:TotalCredits = TotalCredits + mStudenCT.Registration.CreditHours 
                  {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                     GetDecimalFromAttribute( md_dTempDecimal_0, mStudenCT, "Registration", "CreditHours" );
                  dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                  TotalCredits = TotalCredits + dTempDecimal_0;
               } 

               //:END
            } 

            RESULT = SetCursorNextEntityByString( mStudenCT, "Registration", "wRepeatedClass", "AR", "" );

            //:END
         } 

         //:END
         //:DropView( mStudenCT )
         DropView( mStudenCT );

         //:StoreValueInRecord ( mStudenC,
         //:                  InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 )
         StoreValueInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 );
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
//:dAcadStandingYearTerm( VIEW mStudenC BASED ON LOD mStudenC,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   STRING ( 20 ) DisplayYearTerm 
public int 
omStudenC_dAcadStandingYearTerm( View     mStudenC,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   String   DisplayYearTerm = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
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
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mStudenC );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "DisplayAcademicStanding"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "DisplayAcademicStanding", 1, 0, 33 ) == 0 )
         { 
            //:IF mStudenC.DisplayAcademicStandingTerm EXISTS
            lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "DisplayAcademicStandingTerm" );
            if ( lTempInteger_0 == 0 )
            { 
               //:DisplayYearTerm = mStudenC.DisplayAcademicStandingTerm.YearSemester 
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_DisplayYearTerm;
               if ( DisplayYearTerm == null )
                  sb_DisplayYearTerm = new StringBuilder( 32 );
               else
                  sb_DisplayYearTerm = new StringBuilder( DisplayYearTerm );
                               GetVariableFromAttribute( sb_DisplayYearTerm, mi_lTempInteger_1, 'S', 21, mStudenC, "DisplayAcademicStandingTerm", "YearSemester", "", 0 );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );
               DisplayYearTerm = sb_DisplayYearTerm.toString( );}
               //:ELSE
            } 
            else
            { 
               //:DisplayYearTerm = mStudenC.DisplayAcademicStanding.Year
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_DisplayYearTerm;
               if ( DisplayYearTerm == null )
                  sb_DisplayYearTerm = new StringBuilder( 32 );
               else
                  sb_DisplayYearTerm = new StringBuilder( DisplayYearTerm );
                               GetVariableFromAttribute( sb_DisplayYearTerm, mi_lTempInteger_2, 'S', 21, mStudenC, "DisplayAcademicStanding", "Year", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               DisplayYearTerm = sb_DisplayYearTerm.toString( );}
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF mStudenC.AcademicStandingCollegeTerm EXISTS
            lTempInteger_3 = CheckExistenceOfEntity( mStudenC, "AcademicStandingCollegeTerm" );
            if ( lTempInteger_3 == 0 )
            { 
               //:DisplayYearTerm = mStudenC.AcademicStandingCollegeTerm.YearSemester 
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_DisplayYearTerm;
               if ( DisplayYearTerm == null )
                  sb_DisplayYearTerm = new StringBuilder( 32 );
               else
                  sb_DisplayYearTerm = new StringBuilder( DisplayYearTerm );
                               GetVariableFromAttribute( sb_DisplayYearTerm, mi_lTempInteger_4, 'S', 21, mStudenC, "AcademicStandingCollegeTerm", "YearSemester", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               DisplayYearTerm = sb_DisplayYearTerm.toString( );}
               //:ELSE
            } 
            else
            { 
               //:DisplayYearTerm = mStudenC.AcademicStanding.Year
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_DisplayYearTerm;
               if ( DisplayYearTerm == null )
                  sb_DisplayYearTerm = new StringBuilder( 32 );
               else
                  sb_DisplayYearTerm = new StringBuilder( DisplayYearTerm );
                               GetVariableFromAttribute( sb_DisplayYearTerm, mi_lTempInteger_5, 'S', 21, mStudenC, "AcademicStanding", "Year", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               DisplayYearTerm = sb_DisplayYearTerm.toString( );}
            } 

            //:END
         } 

         //:END


         //:StoreStringInRecord ( mStudenC,
         //:                      InternalEntityStructure, InternalAttribStructure, DisplayYearTerm )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, DisplayYearTerm );
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
//:BuildStuAttendForClass( VIEW mStudenC_Orig BASED ON LOD mStudenC,
//:                        VIEW ViewToWindow,
//:                        INTEGER lClassID )

//:   VIEW mStudenC BASED ON LOD mStudenC
public int 
omStudenC_BuildStuAttendForClass( View     mStudenC_Orig,
                                  View     ViewToWindow,
                                  int      lClassID )
{
   zVIEW    mStudenC = new zVIEW( );
   //:VIEW lStuClAt BASED ON LOD lStuClAt
   zVIEW    lStuClAt = new zVIEW( );
   //:STRING ( 8 )  SessionDate
   String   SessionDate = null;
   //:STRING ( 32 ) AttendanceRowAttributeName
   String   AttendanceRowAttributeName = null;
   //:STRING ( 2 )  szMappingAttributeCount
   String   szMappingAttributeCount = null;
   //:INTEGER MappingAttributeCount
   int      MappingAttributeCount = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );


   //:// Build the Student's Class attendance records for the Class ID passed in.

   //:CreateViewFromView( mStudenC, mStudenC_Orig )
   CreateViewFromView( mStudenC, mStudenC_Orig );

   //:// Remove any current Titles.
   //:MappingAttributeCount = 1
   MappingAttributeCount = 1;
   //:LOOP WHILE MappingAttributeCount < 11 
   while ( MappingAttributeCount < 11 )
   { 
      //:SetCtrlRowColText( ViewToWindow, "LBAttendanceGrid", 0, MappingAttributeCount, "" )
      m_ZDRVROPR.SetCtrlRowColText( ViewToWindow, "LBAttendanceGrid", 0, MappingAttributeCount, "" );
      //:MappingAttributeCount = 
      //:   MappingAttributeCount + 1
      MappingAttributeCount = MappingAttributeCount + 1;
   } 

   //:END

   //:// Remove any existing attendance entries.
   //:FOR EACH mStudenC.StudentAttendanceRow 
   RESULT = SetCursorFirstEntity( mStudenC, "StudentAttendanceRow", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mStudenC.StudentAttendanceRow NONE  
      RESULT = DeleteEntity( mStudenC, "StudentAttendanceRow", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mStudenC, "StudentAttendanceRow", "" );
   } 

   //:END

   //:// Make sure Attendance object exists.
   //:GET VIEW lStuClAt NAMED "lStuClAt"
   RESULT = GetViewByName( lStuClAt, "lStuClAt", mStudenC_Orig, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE lStuClAt WHERE lStuClAt.Student.ID = mStudenC.Student.ID 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, mStudenC, "Student", "ID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      omStudenC_fnLocalBuildQual_4( mStudenC_Orig, vTempViewVar_0, lTempInteger_0 );
      RESULT = ActivateObjectInstance( lStuClAt, "lStuClAt", mStudenC_Orig, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW lStuClAt "lStuClAt"
      SetNameForView( lStuClAt, "lStuClAt", null, zLEVEL_TASK );
      //:OrderEntityForView( lStuClAt, "AttendanceSession", "Date A" )
      OrderEntityForView( lStuClAt, "AttendanceSession", "Date A" );
      //:ELSE
   } 
   else
   { 
      //:IF lStuClAt.Student.ID != mStudenC.Student.ID 
      if ( CompareAttributeToAttribute( lStuClAt, "Student", "ID", mStudenC, "Student", "ID" ) != 0 )
      { 
         //:DropObjectInstance( lStuClAt )
         DropObjectInstance( lStuClAt );
         //:ACTIVATE lStuClAt WHERE lStuClAt.Student.ID = mStudenC.Student.ID 
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                   GetIntegerFromAttribute( mi_lTempInteger_1, mStudenC, "Student", "ID" );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
         omStudenC_fnLocalBuildQual_5( mStudenC_Orig, vTempViewVar_1, lTempInteger_1 );
         RESULT = ActivateObjectInstance( lStuClAt, "lStuClAt", mStudenC_Orig, vTempViewVar_1, zSINGLE );
         DropView( vTempViewVar_1 );
         //:NAME VIEW lStuClAt "lStuClAt"
         SetNameForView( lStuClAt, "lStuClAt", null, zLEVEL_TASK );
         //:OrderEntityForView( lStuClAt, "AttendanceSession", "Date A" )
         OrderEntityForView( lStuClAt, "AttendanceSession", "Date A" );
      } 

      //:END
   } 

   //:END

   //:// Create the Attendance entries
   //:// Set values for each attendance entry.
   //:CREATE ENTITY mStudenC.StudentAttendanceRow 
   RESULT = CreateEntity( mStudenC, "StudentAttendanceRow", zPOS_AFTER );
   //:MappingAttributeCount = 0
   MappingAttributeCount = 0;
   //:FOR EACH lStuClAt.AttendanceSession
   RESULT = SetCursorFirstEntity( lStuClAt, "AttendanceSession", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF lStuClAt.AttendedClass.ID = lClassID
      if ( CompareAttributeToInteger( lStuClAt, "AttendedClass", "ID", lClassID ) == 0 )
      { 
         //:MappingAttributeCount = MappingAttributeCount + 1
         MappingAttributeCount = MappingAttributeCount + 1;
         //:IF  MappingAttributeCount < 11  
         if ( MappingAttributeCount < 11 )
         { 
            //:GetStringFromAttributeByContext( SessionDate, lStuClAt, "AttendanceSession", "Date", "MM/DD/YY", 8 )
            {StringBuilder sb_SessionDate;
            if ( SessionDate == null )
               sb_SessionDate = new StringBuilder( 32 );
            else
               sb_SessionDate = new StringBuilder( SessionDate );
                         GetStringFromAttributeByContext( sb_SessionDate, lStuClAt, "AttendanceSession", "Date", "MM/DD/YY", 8 );
            SessionDate = sb_SessionDate.toString( );}
            //:SetCtrlRowColText( ViewToWindow, "LBAttendanceGrid", 0, MappingAttributeCount, SessionDate )
            m_ZDRVROPR.SetCtrlRowColText( ViewToWindow, "LBAttendanceGrid", 0, MappingAttributeCount, SessionDate );
            //:szMappingAttributeCount = MappingAttributeCount
             {StringBuilder sb_szMappingAttributeCount;
            if ( szMappingAttributeCount == null )
               sb_szMappingAttributeCount = new StringBuilder( 32 );
            else
               sb_szMappingAttributeCount = new StringBuilder( szMappingAttributeCount );
                        ZeidonStringConvertFromNumber( sb_szMappingAttributeCount, 1, 0, 2, MappingAttributeCount, (double) 0.0, "I" );
            szMappingAttributeCount = sb_szMappingAttributeCount.toString( );}
            //:AttendanceRowAttributeName = "AttendanceAtSession" + szMappingAttributeCount
             {StringBuilder sb_AttendanceRowAttributeName;
            if ( AttendanceRowAttributeName == null )
               sb_AttendanceRowAttributeName = new StringBuilder( 32 );
            else
               sb_AttendanceRowAttributeName = new StringBuilder( AttendanceRowAttributeName );
                        ZeidonStringCopy( sb_AttendanceRowAttributeName, 1, 0, "AttendanceAtSession", 1, 0, 33 );
            AttendanceRowAttributeName = sb_AttendanceRowAttributeName.toString( );}
             {StringBuilder sb_AttendanceRowAttributeName;
            if ( AttendanceRowAttributeName == null )
               sb_AttendanceRowAttributeName = new StringBuilder( 32 );
            else
               sb_AttendanceRowAttributeName = new StringBuilder( AttendanceRowAttributeName );
                        ZeidonStringConcat( sb_AttendanceRowAttributeName, 1, 0, szMappingAttributeCount, 1, 0, 33 );
            AttendanceRowAttributeName = sb_AttendanceRowAttributeName.toString( );}
            //:SetAttributeFromAttribute( mStudenC, "StudentAttendanceRow", AttendanceRowAttributeName,
            //:                           lStuClAt, "AttendanceSession",   "AttendanceAtSession" )
            SetAttributeFromAttribute( mStudenC, "StudentAttendanceRow", AttendanceRowAttributeName, lStuClAt, "AttendanceSession", "AttendanceAtSession" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( lStuClAt, "AttendanceSession", "" );
      //:END
   } 

   //:END
   //:RefreshCtrl( ViewToWindow, "LBAttendanceGrid" )
   m_ZDRVROPR.RefreshCtrl( ViewToWindow, "LBAttendanceGrid" );
   //:DropView( mStudenC )
   DropView( mStudenC );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ValidateAddedCourseR( VIEW mStudenC BASED ON LOD mStudenC,
//:                      VIEW lClsLstC BASED ON LOD lClsLstC,
//:                      STRING ( 1 )   PreReqsNotMet )

//:   VIEW mStudent  REGISTERED AS mStudent
public int 
omStudenC_ValidateAddedCourseR( View     mStudenC,
                                View     lClsLstC,
                                StringBuilder   PreReqsNotMet )
{
   zVIEW    mStudent = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mStudenC2 BASED ON LOD  mStudenC
   zVIEW    mStudenC2 = new zVIEW( );
   //:VIEW lCrsReqL  BASED ON LOD  lCrsReqL
   zVIEW    lCrsReqL = new zVIEW( );
   //:STRING ( 1000 ) szMsg
   String   szMsg = null;
   //:STRING ( 10 )   szCourseNumber
   String   szCourseNumber = null;
   //:STRING ( 10 )   szRequisiteCourseNumber
   String   szRequisiteCourseNumber = null;
   //:STRING ( 10 )   szYearSemester
   String   szYearSemester = null;
   //:STRING ( 20 )   szPrerequisiteYearSemester
   String   szPrerequisiteYearSemester = null;
   //:STRING ( 20 )    szClassification
   String   szClassification = null;
   //:STRING ( 20 )    szClassClassification
   String   szClassClassification = null;
   //:STRING ( 1 )    RequisiteError
   String   RequisiteError = null;
   //:SHORT nNonPreReqErrorRC
   int      nNonPreReqErrorRC = 0;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_3 = 0;
   String   szTempString_2 = null;
   String   szTempString_3 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   int      lTempInteger_5 = 0;
   String   szTempString_6 = null;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   String   szTempString_7 = null;
   int      lTempInteger_8 = 0;
   String   szTempString_8 = null;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   String   szTempString_9 = null;
   int      lTempInteger_13 = 0;
   String   szTempString_10 = null;
   String   szTempString_11 = null;
   int      lTempInteger_14 = 0;
   int      lTempInteger_15 = 0;
   int      lTempInteger_16 = 0;
   int      lTempInteger_17 = 0;
   int      lTempInteger_18 = 0;
   String   szTempString_12 = null;
   String   szTempString_13 = null;
   int      lTempInteger_19 = 0;
   int      lTempInteger_20 = 0;
   int      lTempInteger_21 = 0;
   int      lTempInteger_22 = 0;
   int      lTempInteger_23 = 0;
   String   szTempString_14 = null;
   int      lTempInteger_24 = 0;

   RESULT = GetViewByName( mStudent, "mStudent", mStudenC, zLEVEL_TASK );

   //:// Perform validations on adding the Class to the Student.

   //:// Make sure the list of Prerequisites and Corequisites exists and that we're positioned on the
   //:// correct Course in that list.
   //:GET VIEW lCrsReqL NAMED "lCrsReqL"
   RESULT = GetViewByName( lCrsReqL, "lCrsReqL", mStudenC, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE lCrsReqL Multiple
      RESULT = ActivateObjectInstance( lCrsReqL, "lCrsReqL", mStudenC, 0, zMULTIPLE );
      //:NAME VIEW lCrsReqL "lCrsReqL"
      SetNameForView( lCrsReqL, "lCrsReqL", null, zLEVEL_TASK );
   } 

   //:END
   //:SET CURSOR FIRST lCrsReqL.Course WHERE lCrsReqL.Course.ID = lClsLstC.Course.ID  
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lClsLstC, "Course", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( lCrsReqL, "Course", "ID", lTempInteger_0, "" );

   //:// Get Course Number, which is used for several of the validations.
   //:szCourseNumber = lClsLstC.Class.wClassNumberTopicSection
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szCourseNumber;
   if ( szCourseNumber == null )
      sb_szCourseNumber = new StringBuilder( 32 );
   else
      sb_szCourseNumber = new StringBuilder( szCourseNumber );
       GetVariableFromAttribute( sb_szCourseNumber, mi_lTempInteger_1, 'S', 11, lClsLstC, "Class", "wClassNumberTopicSection", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szCourseNumber = sb_szCourseNumber.toString( );}

   //:// Check duplicate registration.
   //:SET CURSOR FIRST mStudenC.US_Class WITHIN mStudenC.UpdateSchedule  
   //:           WHERE mStudenC.US_Class.ID = lClsLstC.Class.ID   
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, lClsLstC, "Class", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mStudenC, "US_Class", "ID", lTempInteger_2, "UpdateSchedule" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:IF mStudenC.US_Registration.Status = "R" OR mStudenC.US_Registration.Status = "T" OR mStudenC.US_Registration.Status = "D"
      if ( CompareAttributeToString( mStudenC, "US_Registration", "Status", "R" ) == 0 || CompareAttributeToString( mStudenC, "US_Registration", "Status", "T" ) == 0 || CompareAttributeToString( mStudenC, "US_Registration", "Status", "D" ) == 0 )
      { 
         //:// Error if student is enrolled or preenrolled.
         //:CREATE ENTITY mStudenC.ValidationErrorMessage
         RESULT = CreateEntity( mStudenC, "ValidationErrorMessage", zPOS_AFTER );
         //:IF mStudenC.US_Registration.Status = "D"
         if ( CompareAttributeToString( mStudenC, "US_Registration", "Status", "D" ) == 0 )
         { 
            //:mStudenC.ValidationErrorMessage.Value = "Student has dropped the Class, " + szCourseNumber + "/" + mStudenC.Registration.dCollegeTerm +
            //:                                        ". You must change the 'Dropped' to 'Enrolled' to re-enroll the Student."
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringCopy( sb_szTempString_0, 1, 0, "Student has dropped the Class, ", 1, 0, 10001 );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringConcat( sb_szTempString_0, 1, 0, szCourseNumber, 1, 0, 10001 );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringConcat( sb_szTempString_0, 1, 0, "/", 1, 0, 10001 );
            szTempString_0 = sb_szTempString_0.toString( );}
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_3, 'S', 255, mStudenC, "Registration", "dCollegeTerm", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 10001 );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringConcat( sb_szTempString_0, 1, 0, ". You must change the 'Dropped' to 'Enrolled' to re-enroll the Student.", 1, 0, 10001 );
            szTempString_0 = sb_szTempString_0.toString( );}
            SetAttributeFromString( mStudenC, "ValidationErrorMessage", "Value", szTempString_0 );
            //:ELSE
         } 
         else
         { 
            //:mStudenC.ValidationErrorMessage.Value = "Student is already registered for the Class: " + szCourseNumber + "/" + mStudenC.Registration.dCollegeTerm + "."
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringCopy( sb_szTempString_2, 1, 0, "Student is already registered for the Class: ", 1, 0, 10001 );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringConcat( sb_szTempString_2, 1, 0, szCourseNumber, 1, 0, 10001 );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringConcat( sb_szTempString_2, 1, 0, "/", 1, 0, 10001 );
            szTempString_2 = sb_szTempString_2.toString( );}
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                         GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_4, 'S', 255, mStudenC, "Registration", "dCollegeTerm", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szTempString_3 = sb_szTempString_3.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringConcat( sb_szTempString_2, 1, 0, szTempString_3, 1, 0, 10001 );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringConcat( sb_szTempString_2, 1, 0, ".", 1, 0, 10001 );
            szTempString_2 = sb_szTempString_2.toString( );}
            SetAttributeFromString( mStudenC, "ValidationErrorMessage", "Value", szTempString_2 );
         } 

         //:END
         //:RETURN -1     // This is a hard error so we won't continue.
         if(8==8)return( -1 );
      } 

      //:END
   } 

   //:END

   //:// Check for canceled Class.
   //:IF lClsLstC.Class.Status = "C"
   if ( CompareAttributeToString( lClsLstC, "Class", "Status", "C" ) == 0 )
   { 
      //:CREATE ENTITY mStudenC.ValidationErrorMessage
      RESULT = CreateEntity( mStudenC, "ValidationErrorMessage", zPOS_AFTER );
      //:mStudenC.ValidationErrorMessage.Value = "Class " + szCourseNumber + " has been canceled and cannot be selected for enrollment."
       {StringBuilder sb_szTempString_4;
      if ( szTempString_4 == null )
         sb_szTempString_4 = new StringBuilder( 32 );
      else
         sb_szTempString_4 = new StringBuilder( szTempString_4 );
            ZeidonStringCopy( sb_szTempString_4, 1, 0, "Class ", 1, 0, 10001 );
      szTempString_4 = sb_szTempString_4.toString( );}
       {StringBuilder sb_szTempString_4;
      if ( szTempString_4 == null )
         sb_szTempString_4 = new StringBuilder( 32 );
      else
         sb_szTempString_4 = new StringBuilder( szTempString_4 );
            ZeidonStringConcat( sb_szTempString_4, 1, 0, szCourseNumber, 1, 0, 10001 );
      szTempString_4 = sb_szTempString_4.toString( );}
       {StringBuilder sb_szTempString_4;
      if ( szTempString_4 == null )
         sb_szTempString_4 = new StringBuilder( 32 );
      else
         sb_szTempString_4 = new StringBuilder( szTempString_4 );
            ZeidonStringConcat( sb_szTempString_4, 1, 0, " has been canceled and cannot be selected for enrollment.", 1, 0, 10001 );
      szTempString_4 = sb_szTempString_4.toString( );}
      SetAttributeFromString( mStudenC, "ValidationErrorMessage", "Value", szTempString_4 );
      //:nNonPreReqErrorRC = 1
      nNonPreReqErrorRC = 1;
   } 

   //:END

   //:// If Special Permission is required for the Course, ask the operator if Special Permission
   //:// has been granted.
   //:IF lClsLstC.Course.SpecialPermissionFlag = "Y" OR
   //:   lClsLstC.Class.SpecialPermissionFlag  = "Y"
   if ( CompareAttributeToString( lClsLstC, "Course", "SpecialPermissionFlag", "Y" ) == 0 || CompareAttributeToString( lClsLstC, "Class", "SpecialPermissionFlag", "Y" ) == 0 )
   { 

      //:CREATE ENTITY mStudenC.ValidationErrorMessage
      RESULT = CreateEntity( mStudenC, "ValidationErrorMessage", zPOS_AFTER );
      //:mStudenC.ValidationErrorMessage.Value = "Special permission is required for Course, " + szCourseNumber + "."
       {StringBuilder sb_szTempString_5;
      if ( szTempString_5 == null )
         sb_szTempString_5 = new StringBuilder( 32 );
      else
         sb_szTempString_5 = new StringBuilder( szTempString_5 );
            ZeidonStringCopy( sb_szTempString_5, 1, 0, "Special permission is required for Course, ", 1, 0, 10001 );
      szTempString_5 = sb_szTempString_5.toString( );}
       {StringBuilder sb_szTempString_5;
      if ( szTempString_5 == null )
         sb_szTempString_5 = new StringBuilder( 32 );
      else
         sb_szTempString_5 = new StringBuilder( szTempString_5 );
            ZeidonStringConcat( sb_szTempString_5, 1, 0, szCourseNumber, 1, 0, 10001 );
      szTempString_5 = sb_szTempString_5.toString( );}
       {StringBuilder sb_szTempString_5;
      if ( szTempString_5 == null )
         sb_szTempString_5 = new StringBuilder( 32 );
      else
         sb_szTempString_5 = new StringBuilder( szTempString_5 );
            ZeidonStringConcat( sb_szTempString_5, 1, 0, ".", 1, 0, 10001 );
      szTempString_5 = sb_szTempString_5.toString( );}
      SetAttributeFromString( mStudenC, "ValidationErrorMessage", "Value", szTempString_5 );
      //:nNonPreReqErrorRC = 1
      nNonPreReqErrorRC = 1;
   } 

   //:END

   //:szClassification = mStudenC.UpdateSchedule.dAnticipatedClassification
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
   StringBuilder sb_szClassification;
   if ( szClassification == null )
      sb_szClassification = new StringBuilder( 32 );
   else
      sb_szClassification = new StringBuilder( szClassification );
       GetVariableFromAttribute( sb_szClassification, mi_lTempInteger_5, 'S', 21, mStudenC, "UpdateSchedule", "dAnticipatedClassification", "", 0 );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );
   szClassification = sb_szClassification.toString( );}

   //:// If minimum Class ranking is required and the student doesn't meet it, prompt the operator.
   //:// This rule is not triggered for Graduate Students.
   //:IF lClsLstC.Course.MinimumClassRanking != "" AND 
   //:   szClassification < lClsLstC.Course.MinimumClassRanking AND 
   //:   mStudent.Student.CurrentLevel != "6"    // "6" is Graduate Student
   if ( CompareAttributeToString( lClsLstC, "Course", "MinimumClassRanking", "" ) != 0 && CompareAttributeToString( lClsLstC, "Course", "MinimumClassRanking", szClassification ) > 0 &&
        CompareAttributeToString( mStudent, "Student", "CurrentLevel", "6" ) != 0 )
   { 

      //:// Previously we were using the derived attribute dAnticipatedClassification in the above IF statement but while this 
      //:// works in windows, it is crashing Tomcat.  Separating it out (using szClassification) works correctly.
      //://mStudenC.UpdateSchedule.dAnticipatedClassification < lClsLstC.Course.MinimumClassRanking 
      //:GetStringFromAttributeByContext( szClassification, mStudenC, "UpdateSchedule", "dAnticipatedClassification", "", 20)
      {StringBuilder sb_szClassification;
      if ( szClassification == null )
         sb_szClassification = new StringBuilder( 32 );
      else
         sb_szClassification = new StringBuilder( szClassification );
             GetStringFromAttributeByContext( sb_szClassification, mStudenC, "UpdateSchedule", "dAnticipatedClassification", "", 20 );
      szClassification = sb_szClassification.toString( );}
      //:GetStringFromAttributeByContext( szClassClassification, lClsLstC, "Course", "MinimumClassRanking", "", 20)
      {StringBuilder sb_szClassClassification;
      if ( szClassClassification == null )
         sb_szClassClassification = new StringBuilder( 32 );
      else
         sb_szClassClassification = new StringBuilder( szClassClassification );
             GetStringFromAttributeByContext( sb_szClassClassification, lClsLstC, "Course", "MinimumClassRanking", "", 20 );
      szClassClassification = sb_szClassClassification.toString( );}
      //:CREATE ENTITY mStudenC.ValidationErrorMessage
      RESULT = CreateEntity( mStudenC, "ValidationErrorMessage", zPOS_AFTER );
      //:mStudenC.ValidationErrorMessage.Value = "The student classification (" + szClassification + ") is below the minimum specified for Course, " + szCourseNumber + 
      //:" (" + szClassClassification + ")."
       {StringBuilder sb_szTempString_6;
      if ( szTempString_6 == null )
         sb_szTempString_6 = new StringBuilder( 32 );
      else
         sb_szTempString_6 = new StringBuilder( szTempString_6 );
            ZeidonStringCopy( sb_szTempString_6, 1, 0, "The student classification (", 1, 0, 10001 );
      szTempString_6 = sb_szTempString_6.toString( );}
       {StringBuilder sb_szTempString_6;
      if ( szTempString_6 == null )
         sb_szTempString_6 = new StringBuilder( 32 );
      else
         sb_szTempString_6 = new StringBuilder( szTempString_6 );
            ZeidonStringConcat( sb_szTempString_6, 1, 0, szClassification, 1, 0, 10001 );
      szTempString_6 = sb_szTempString_6.toString( );}
       {StringBuilder sb_szTempString_6;
      if ( szTempString_6 == null )
         sb_szTempString_6 = new StringBuilder( 32 );
      else
         sb_szTempString_6 = new StringBuilder( szTempString_6 );
            ZeidonStringConcat( sb_szTempString_6, 1, 0, ") is below the minimum specified for Course, ", 1, 0, 10001 );
      szTempString_6 = sb_szTempString_6.toString( );}
       {StringBuilder sb_szTempString_6;
      if ( szTempString_6 == null )
         sb_szTempString_6 = new StringBuilder( 32 );
      else
         sb_szTempString_6 = new StringBuilder( szTempString_6 );
            ZeidonStringConcat( sb_szTempString_6, 1, 0, szCourseNumber, 1, 0, 10001 );
      szTempString_6 = sb_szTempString_6.toString( );}
       {StringBuilder sb_szTempString_6;
      if ( szTempString_6 == null )
         sb_szTempString_6 = new StringBuilder( 32 );
      else
         sb_szTempString_6 = new StringBuilder( szTempString_6 );
            ZeidonStringConcat( sb_szTempString_6, 1, 0, " (", 1, 0, 10001 );
      szTempString_6 = sb_szTempString_6.toString( );}
       {StringBuilder sb_szTempString_6;
      if ( szTempString_6 == null )
         sb_szTempString_6 = new StringBuilder( 32 );
      else
         sb_szTempString_6 = new StringBuilder( szTempString_6 );
            ZeidonStringConcat( sb_szTempString_6, 1, 0, szClassClassification, 1, 0, 10001 );
      szTempString_6 = sb_szTempString_6.toString( );}
       {StringBuilder sb_szTempString_6;
      if ( szTempString_6 == null )
         sb_szTempString_6 = new StringBuilder( 32 );
      else
         sb_szTempString_6 = new StringBuilder( szTempString_6 );
            ZeidonStringConcat( sb_szTempString_6, 1, 0, ").", 1, 0, 10001 );
      szTempString_6 = sb_szTempString_6.toString( );}
      SetAttributeFromString( mStudenC, "ValidationErrorMessage", "Value", szTempString_6 );
      //:nNonPreReqErrorRC = 1
      nNonPreReqErrorRC = 1;
   } 

   //:END

   //:// Check if Student has already taken the Course with a passing grade.
   //:// We can use the Prerequisite find as it locates a class in an earlier Term with a passing grade.
   //:// Don't check for repeatable Course.
   //:IF lClsLstC.Course.RepeatableForCreditFlag != "Y"
   if ( CompareAttributeToString( lClsLstC, "Course", "RepeatableForCreditFlag", "Y" ) != 0 )
   { 
      //:szRequisiteCourseNumber = lClsLstC.Course.Number
      {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
      StringBuilder sb_szRequisiteCourseNumber;
      if ( szRequisiteCourseNumber == null )
         sb_szRequisiteCourseNumber = new StringBuilder( 32 );
      else
         sb_szRequisiteCourseNumber = new StringBuilder( szRequisiteCourseNumber );
             GetVariableFromAttribute( sb_szRequisiteCourseNumber, mi_lTempInteger_6, 'S', 11, lClsLstC, "Course", "Number", "", 0 );
      lTempInteger_6 = mi_lTempInteger_6.intValue( );
      szRequisiteCourseNumber = sb_szRequisiteCourseNumber.toString( );}
      //:IF lClsLstC.CollegeTerm EXISTS
      lTempInteger_7 = CheckExistenceOfEntity( lClsLstC, "CollegeTerm" );
      if ( lTempInteger_7 == 0 )
      { 
         //:szPrerequisiteYearSemester = lClsLstC.CollegeYear.Year + lClsLstC.CollegeTerm.Semester 
         {StringBuilder sb_szPrerequisiteYearSemester;
         if ( szPrerequisiteYearSemester == null )
            sb_szPrerequisiteYearSemester = new StringBuilder( 32 );
         else
            sb_szPrerequisiteYearSemester = new StringBuilder( szPrerequisiteYearSemester );
                   GetStringFromAttribute( sb_szPrerequisiteYearSemester, lClsLstC, "CollegeYear", "Year" );
         szPrerequisiteYearSemester = sb_szPrerequisiteYearSemester.toString( );}
         {StringBuilder sb_szTempString_7;
         if ( szTempString_7 == null )
            sb_szTempString_7 = new StringBuilder( 32 );
         else
            sb_szTempString_7 = new StringBuilder( szTempString_7 );
                   GetStringFromAttribute( sb_szTempString_7, lClsLstC, "CollegeTerm", "Semester" );
         szTempString_7 = sb_szTempString_7.toString( );}
          {StringBuilder sb_szPrerequisiteYearSemester;
         if ( szPrerequisiteYearSemester == null )
            sb_szPrerequisiteYearSemester = new StringBuilder( 32 );
         else
            sb_szPrerequisiteYearSemester = new StringBuilder( szPrerequisiteYearSemester );
                  ZeidonStringConcat( sb_szPrerequisiteYearSemester, 1, 0, szTempString_7, 1, 0, 21 );
         szPrerequisiteYearSemester = sb_szPrerequisiteYearSemester.toString( );}
         //:ELSE
      } 
      else
      { 
         //:// If the Class has no CollegeTerm, it must be for a Cohort and we will use the Class Start Date.
         //:szPrerequisiteYearSemester = lClsLstC.Class.ClassStartDate
         {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
         StringBuilder sb_szPrerequisiteYearSemester;
         if ( szPrerequisiteYearSemester == null )
            sb_szPrerequisiteYearSemester = new StringBuilder( 32 );
         else
            sb_szPrerequisiteYearSemester = new StringBuilder( szPrerequisiteYearSemester );
                   GetVariableFromAttribute( sb_szPrerequisiteYearSemester, mi_lTempInteger_8, 'S', 21, lClsLstC, "Class", "ClassStartDate", "", 0 );
         lTempInteger_8 = mi_lTempInteger_8.intValue( );
         szPrerequisiteYearSemester = sb_szPrerequisiteYearSemester.toString( );}
      } 

      //:END 
      //:nRC = FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester )
      nRC = omStudenC_FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester );
      //:IF nRC = 0   // The Course WAS found.
      if ( nRC == 0 )
      { 
         //:CREATE ENTITY mStudenC.ValidationErrorMessage
         RESULT = CreateEntity( mStudenC, "ValidationErrorMessage", zPOS_AFTER );
         //:mStudenC.ValidationErrorMessage.Value = "The student is taking or has already taken Course, " + szRequisiteCourseNumber + ", with a passing grade."
          {StringBuilder sb_szTempString_8;
         if ( szTempString_8 == null )
            sb_szTempString_8 = new StringBuilder( 32 );
         else
            sb_szTempString_8 = new StringBuilder( szTempString_8 );
                  ZeidonStringCopy( sb_szTempString_8, 1, 0, "The student is taking or has already taken Course, ", 1, 0, 10001 );
         szTempString_8 = sb_szTempString_8.toString( );}
          {StringBuilder sb_szTempString_8;
         if ( szTempString_8 == null )
            sb_szTempString_8 = new StringBuilder( 32 );
         else
            sb_szTempString_8 = new StringBuilder( szTempString_8 );
                  ZeidonStringConcat( sb_szTempString_8, 1, 0, szRequisiteCourseNumber, 1, 0, 10001 );
         szTempString_8 = sb_szTempString_8.toString( );}
          {StringBuilder sb_szTempString_8;
         if ( szTempString_8 == null )
            sb_szTempString_8 = new StringBuilder( 32 );
         else
            sb_szTempString_8 = new StringBuilder( szTempString_8 );
                  ZeidonStringConcat( sb_szTempString_8, 1, 0, ", with a passing grade.", 1, 0, 10001 );
         szTempString_8 = sb_szTempString_8.toString( );}
         SetAttributeFromString( mStudenC, "ValidationErrorMessage", "Value", szTempString_8 );
         //:nNonPreReqErrorRC = 1
         nNonPreReqErrorRC = 1;
      } 

      //:END
   } 

   //:END

   //:// Check if student meets prerequisites.
   //:PreReqsNotMet = ""
   ZeidonStringCopy( PreReqsNotMet, 1, 0, "", 1, 0, 2 );
   //:IF lCrsReqL.CoursePrerequisiteGroup EXISTS
   lTempInteger_9 = CheckExistenceOfEntity( lCrsReqL, "CoursePrerequisiteGroup" );
   if ( lTempInteger_9 == 0 )
   { 
      //:CreateViewFromView( mStudenC2, mStudenC )
      CreateViewFromView( mStudenC2, mStudenC );
      //:IF lCrsReqL.Course.PrerequisiteBooleanOperator = "A"
      if ( CompareAttributeToString( lCrsReqL, "Course", "PrerequisiteBooleanOperator", "A" ) == 0 )
      { 
         //:// Prerequisites are to be AND'd.
         //:szMsg = "The following prerequisites have not been met for Course " + szCourseNumber + ": "
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "The following prerequisites have not been met for Course ", 1, 0, 1001 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szCourseNumber, 1, 0, 1001 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, ": ", 1, 0, 1001 );
         szMsg = sb_szMsg.toString( );}
         //:RequisiteError = ""
          {StringBuilder sb_RequisiteError;
         if ( RequisiteError == null )
            sb_RequisiteError = new StringBuilder( 32 );
         else
            sb_RequisiteError = new StringBuilder( RequisiteError );
                  ZeidonStringCopy( sb_RequisiteError, 1, 0, "", 1, 0, 2 );
         RequisiteError = sb_RequisiteError.toString( );}
         //:FOR EACH lCrsReqL.CoursePrerequisiteGroup 
         RESULT = SetCursorFirstEntity( lCrsReqL, "CoursePrerequisiteGroup", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lCrsReqL.PrerequisiteCourse EXISTS
            lTempInteger_10 = CheckExistenceOfEntity( lCrsReqL, "PrerequisiteCourse" );
            if ( lTempInteger_10 == 0 )
            { 
               //:// Entry is for Prerequisite Course
               //:// Loop through all Registration entries looking for a match on the prerequisite.
               //:szRequisiteCourseNumber = lCrsReqL.PrerequisiteCourse.Number
               {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
               StringBuilder sb_szRequisiteCourseNumber;
               if ( szRequisiteCourseNumber == null )
                  sb_szRequisiteCourseNumber = new StringBuilder( 32 );
               else
                  sb_szRequisiteCourseNumber = new StringBuilder( szRequisiteCourseNumber );
                               GetVariableFromAttribute( sb_szRequisiteCourseNumber, mi_lTempInteger_11, 'S', 11, lCrsReqL, "PrerequisiteCourse", "Number", "", 0 );
               lTempInteger_11 = mi_lTempInteger_11.intValue( );
               szRequisiteCourseNumber = sb_szRequisiteCourseNumber.toString( );}
               //:IF lClsLstC.CollegeTerm EXISTS
               lTempInteger_12 = CheckExistenceOfEntity( lClsLstC, "CollegeTerm" );
               if ( lTempInteger_12 == 0 )
               { 
                  //:szPrerequisiteYearSemester = lClsLstC.CollegeYear.Year + lClsLstC.CollegeTerm.Semester 
                  {StringBuilder sb_szPrerequisiteYearSemester;
                  if ( szPrerequisiteYearSemester == null )
                     sb_szPrerequisiteYearSemester = new StringBuilder( 32 );
                  else
                     sb_szPrerequisiteYearSemester = new StringBuilder( szPrerequisiteYearSemester );
                                     GetStringFromAttribute( sb_szPrerequisiteYearSemester, lClsLstC, "CollegeYear", "Year" );
                  szPrerequisiteYearSemester = sb_szPrerequisiteYearSemester.toString( );}
                  {StringBuilder sb_szTempString_9;
                  if ( szTempString_9 == null )
                     sb_szTempString_9 = new StringBuilder( 32 );
                  else
                     sb_szTempString_9 = new StringBuilder( szTempString_9 );
                                     GetStringFromAttribute( sb_szTempString_9, lClsLstC, "CollegeTerm", "Semester" );
                  szTempString_9 = sb_szTempString_9.toString( );}
                   {StringBuilder sb_szPrerequisiteYearSemester;
                  if ( szPrerequisiteYearSemester == null )
                     sb_szPrerequisiteYearSemester = new StringBuilder( 32 );
                  else
                     sb_szPrerequisiteYearSemester = new StringBuilder( szPrerequisiteYearSemester );
                                    ZeidonStringConcat( sb_szPrerequisiteYearSemester, 1, 0, szTempString_9, 1, 0, 21 );
                  szPrerequisiteYearSemester = sb_szPrerequisiteYearSemester.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:// If the Class has no CollegeTerm, it must be for a Cohort and we will use the Class Start Date.
                  //:szPrerequisiteYearSemester = lClsLstC.Class.ClassStartDate
                  {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
                  StringBuilder sb_szPrerequisiteYearSemester;
                  if ( szPrerequisiteYearSemester == null )
                     sb_szPrerequisiteYearSemester = new StringBuilder( 32 );
                  else
                     sb_szPrerequisiteYearSemester = new StringBuilder( szPrerequisiteYearSemester );
                                     GetVariableFromAttribute( sb_szPrerequisiteYearSemester, mi_lTempInteger_13, 'S', 21, lClsLstC, "Class", "ClassStartDate", "", 0 );
                  lTempInteger_13 = mi_lTempInteger_13.intValue( );
                  szPrerequisiteYearSemester = sb_szPrerequisiteYearSemester.toString( );}
               } 

               //:END

               //:// If the Course is a Developmental Course, we will only perform the prerequisite check if the Student is
               //:// required to take the Developmental Course. This is determined by the entries under the HS_RequiredGroup entity.
               //:// Note that we only need to make this check for the "AND" condition as all Developmental requirements are anded.
               //:IF lCrsReqL.PrerequisiteCourse.DevelopmentalFlag = "Y"
               if ( CompareAttributeToString( lCrsReqL, "PrerequisiteCourse", "DevelopmentalFlag", "Y" ) == 0 )
               { 
                  //:SET CURSOR FIRST mStudenC2.HS_RequiredGroupCourse WITHIN mStudenC2.Student 
                  //:           WHERE mStudenC2.HS_RequiredGroupCourse.Number = lCrsReqL.PrerequisiteCourse.Number 
                  {StringBuilder sb_szTempString_10;
                  if ( szTempString_10 == null )
                     sb_szTempString_10 = new StringBuilder( 32 );
                  else
                     sb_szTempString_10 = new StringBuilder( szTempString_10 );
                                     GetStringFromAttribute( sb_szTempString_10, lCrsReqL, "PrerequisiteCourse", "Number" );
                  szTempString_10 = sb_szTempString_10.toString( );}
                  RESULT = SetCursorFirstEntityByString( mStudenC2, "HS_RequiredGroupCourse", "Number", szTempString_10, "Student" );
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:nRC = FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester )
                     nRC = omStudenC_FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester );
                     //:ELSE
                  } 
                  else
                  { 
                     //:nRC = 0
                     nRC = 0;
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:nRC = FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester )
                  nRC = omStudenC_FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// Entry is for group of Prerequisite Subcourses.
               //:// Go to operation to evaluate subcourse entries.
               //:nRC = CheckPreReqGroup( mStudenC, lCrsReqL, szPrerequisiteYearSemester )
               nRC = omStudenC_CheckPreReqGroup( mStudenC, lCrsReqL, szPrerequisiteYearSemester );
            } 

            //:END

            //:IF nRC < 0     // The Prerequisite was NOT found.
            if ( nRC < 0 )
            { 
               //:RequisiteError = "Y"
                {StringBuilder sb_RequisiteError;
               if ( RequisiteError == null )
                  sb_RequisiteError = new StringBuilder( 32 );
               else
                  sb_RequisiteError = new StringBuilder( RequisiteError );
                              ZeidonStringCopy( sb_RequisiteError, 1, 0, "Y", 1, 0, 2 );
               RequisiteError = sb_RequisiteError.toString( );}
               //:szMsg = szMsg + lCrsReqL.CoursePrerequisiteGroup.dDisplayCourseOrGroup + "; "
               {MutableInt mi_lTempInteger_14 = new MutableInt( lTempInteger_14 );
               StringBuilder sb_szTempString_11;
               if ( szTempString_11 == null )
                  sb_szTempString_11 = new StringBuilder( 32 );
               else
                  sb_szTempString_11 = new StringBuilder( szTempString_11 );
                               GetVariableFromAttribute( sb_szTempString_11, mi_lTempInteger_14, 'S', 255, lCrsReqL, "CoursePrerequisiteGroup", "dDisplayCourseOrGroup", "", 0 );
               lTempInteger_14 = mi_lTempInteger_14.intValue( );
               szTempString_11 = sb_szTempString_11.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_11, 1, 0, 1001 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, "; ", 1, 0, 1001 );
               szMsg = sb_szMsg.toString( );}
            } 

            RESULT = SetCursorNextEntity( lCrsReqL, "CoursePrerequisiteGroup", "" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Prerequisites are to be OR'd.
         //:szMsg = "One of the following prerequisites must be met for Course " + szCourseNumber + ": "
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "One of the following prerequisites must be met for Course ", 1, 0, 1001 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szCourseNumber, 1, 0, 1001 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, ": ", 1, 0, 1001 );
         szMsg = sb_szMsg.toString( );}
         //:RequisiteError = "Y"
          {StringBuilder sb_RequisiteError;
         if ( RequisiteError == null )
            sb_RequisiteError = new StringBuilder( 32 );
         else
            sb_RequisiteError = new StringBuilder( RequisiteError );
                  ZeidonStringCopy( sb_RequisiteError, 1, 0, "Y", 1, 0, 2 );
         RequisiteError = sb_RequisiteError.toString( );}
         //:FOR EACH lCrsReqL.CoursePrerequisiteGroup
         RESULT = SetCursorFirstEntity( lCrsReqL, "CoursePrerequisiteGroup", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lCrsReqL.PrerequisiteCourse EXISTS
            lTempInteger_15 = CheckExistenceOfEntity( lCrsReqL, "PrerequisiteCourse" );
            if ( lTempInteger_15 == 0 )
            { 
               //:// Entry is for Prerequisite Course
               //:// Loop through all Registration entries looking for a match on the prerequisite.
               //:szRequisiteCourseNumber = lCrsReqL.PrerequisiteCourse.Number
               {MutableInt mi_lTempInteger_16 = new MutableInt( lTempInteger_16 );
               StringBuilder sb_szRequisiteCourseNumber;
               if ( szRequisiteCourseNumber == null )
                  sb_szRequisiteCourseNumber = new StringBuilder( 32 );
               else
                  sb_szRequisiteCourseNumber = new StringBuilder( szRequisiteCourseNumber );
                               GetVariableFromAttribute( sb_szRequisiteCourseNumber, mi_lTempInteger_16, 'S', 11, lCrsReqL, "PrerequisiteCourse", "Number", "", 0 );
               lTempInteger_16 = mi_lTempInteger_16.intValue( );
               szRequisiteCourseNumber = sb_szRequisiteCourseNumber.toString( );}
               //:IF lClsLstC.CollegeTerm EXISTS
               lTempInteger_17 = CheckExistenceOfEntity( lClsLstC, "CollegeTerm" );
               if ( lTempInteger_17 == 0 )
               { 
                  //:szPrerequisiteYearSemester = lClsLstC.CollegeYear.Year + lClsLstC.CollegeTerm.Semester 
                  {StringBuilder sb_szPrerequisiteYearSemester;
                  if ( szPrerequisiteYearSemester == null )
                     sb_szPrerequisiteYearSemester = new StringBuilder( 32 );
                  else
                     sb_szPrerequisiteYearSemester = new StringBuilder( szPrerequisiteYearSemester );
                                     GetStringFromAttribute( sb_szPrerequisiteYearSemester, lClsLstC, "CollegeYear", "Year" );
                  szPrerequisiteYearSemester = sb_szPrerequisiteYearSemester.toString( );}
                  {StringBuilder sb_szTempString_10;
                  if ( szTempString_10 == null )
                     sb_szTempString_10 = new StringBuilder( 32 );
                  else
                     sb_szTempString_10 = new StringBuilder( szTempString_10 );
                                     GetStringFromAttribute( sb_szTempString_10, lClsLstC, "CollegeTerm", "Semester" );
                  szTempString_10 = sb_szTempString_10.toString( );}
                   {StringBuilder sb_szPrerequisiteYearSemester;
                  if ( szPrerequisiteYearSemester == null )
                     sb_szPrerequisiteYearSemester = new StringBuilder( 32 );
                  else
                     sb_szPrerequisiteYearSemester = new StringBuilder( szPrerequisiteYearSemester );
                                    ZeidonStringConcat( sb_szPrerequisiteYearSemester, 1, 0, szTempString_10, 1, 0, 21 );
                  szPrerequisiteYearSemester = sb_szPrerequisiteYearSemester.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:// If the Class has no CollegeTerm, it must be for a Cohort and we will use the Class Start Date.
                  //:szPrerequisiteYearSemester = lClsLstC.Class.ClassStartDate
                  {MutableInt mi_lTempInteger_18 = new MutableInt( lTempInteger_18 );
                  StringBuilder sb_szPrerequisiteYearSemester;
                  if ( szPrerequisiteYearSemester == null )
                     sb_szPrerequisiteYearSemester = new StringBuilder( 32 );
                  else
                     sb_szPrerequisiteYearSemester = new StringBuilder( szPrerequisiteYearSemester );
                                     GetVariableFromAttribute( sb_szPrerequisiteYearSemester, mi_lTempInteger_18, 'S', 21, lClsLstC, "Class", "ClassStartDate", "", 0 );
                  lTempInteger_18 = mi_lTempInteger_18.intValue( );
                  szPrerequisiteYearSemester = sb_szPrerequisiteYearSemester.toString( );}
               } 

               //:END
               //:IF lCrsReqL.PrerequisiteCourse.DevelopmentalFlag = "Y"
               if ( CompareAttributeToString( lCrsReqL, "PrerequisiteCourse", "DevelopmentalFlag", "Y" ) == 0 )
               { 
                  //:SET CURSOR FIRST mStudenC2.HS_RequiredGroupCourse WITHIN mStudenC2.Student 
                  //:           WHERE mStudenC2.HS_RequiredGroupCourse.Number = lCrsReqL.PrerequisiteCourse.Number 
                  {StringBuilder sb_szTempString_12;
                  if ( szTempString_12 == null )
                     sb_szTempString_12 = new StringBuilder( 32 );
                  else
                     sb_szTempString_12 = new StringBuilder( szTempString_12 );
                                     GetStringFromAttribute( sb_szTempString_12, lCrsReqL, "PrerequisiteCourse", "Number" );
                  szTempString_12 = sb_szTempString_12.toString( );}
                  RESULT = SetCursorFirstEntityByString( mStudenC2, "HS_RequiredGroupCourse", "Number", szTempString_12, "Student" );
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:nRC = FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester )
                     nRC = omStudenC_FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester );
                     //:ELSE
                  } 
                  else
                  { 
                     //:nRC = 0
                     nRC = 0;
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:nRC = FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester )
                  nRC = omStudenC_FindPrerequisiteClass( mStudenC, szRequisiteCourseNumber, szPrerequisiteYearSemester );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// Entry is for group of Prerequisite Subcourses.
               //:// Go to operation to evaluate subcourse entries.
               //:nRC = CheckPreReqGroup( mStudenC, lCrsReqL, szPrerequisiteYearSemester )
               nRC = omStudenC_CheckPreReqGroup( mStudenC, lCrsReqL, szPrerequisiteYearSemester );
            } 

            //:END

            //:IF nRC = 0   // The Prerequisite WAS found.
            if ( nRC == 0 )
            { 
               //:RequisiteError = ""
                {StringBuilder sb_RequisiteError;
               if ( RequisiteError == null )
                  sb_RequisiteError = new StringBuilder( 32 );
               else
                  sb_RequisiteError = new StringBuilder( RequisiteError );
                              ZeidonStringCopy( sb_RequisiteError, 1, 0, "", 1, 0, 2 );
               RequisiteError = sb_RequisiteError.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szMsg = szMsg + lCrsReqL.CoursePrerequisiteGroup.dDisplayCourseOrGroup + "; "
               {MutableInt mi_lTempInteger_19 = new MutableInt( lTempInteger_19 );
               StringBuilder sb_szTempString_13;
               if ( szTempString_13 == null )
                  sb_szTempString_13 = new StringBuilder( 32 );
               else
                  sb_szTempString_13 = new StringBuilder( szTempString_13 );
                               GetVariableFromAttribute( sb_szTempString_13, mi_lTempInteger_19, 'S', 255, lCrsReqL, "CoursePrerequisiteGroup", "dDisplayCourseOrGroup", "", 0 );
               lTempInteger_19 = mi_lTempInteger_19.intValue( );
               szTempString_13 = sb_szTempString_13.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_13, 1, 0, 1001 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, "; ", 1, 0, 1001 );
               szMsg = sb_szMsg.toString( );}
            } 

            RESULT = SetCursorNextEntity( lCrsReqL, "CoursePrerequisiteGroup", "" );
            //:END
         } 

         //:END
      } 

      //:END
      //:IF RequisiteError = "Y"
      if ( ZeidonStringCompare( RequisiteError, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:CREATE ENTITY mStudenC.ValidationErrorMessage
         RESULT = CreateEntity( mStudenC, "ValidationErrorMessage", zPOS_AFTER );
         //:mStudenC.ValidationErrorMessage.Value = szMsg
         SetAttributeFromString( mStudenC, "ValidationErrorMessage", "Value", szMsg );
         //:PreReqsNotMet = "Y"
         ZeidonStringCopy( PreReqsNotMet, 1, 0, "Y", 1, 0, 2 );
      } 

      //:END
      //:DropView( mStudenC2 )
      DropView( mStudenC2 );
   } 

   //:END

   //:// Check if student meets corequisites.
   //:// Note that there should be no corequisites for Cohort Classes.
   //:IF lCrsReqL.CorequisiteCourse EXISTS
   lTempInteger_20 = CheckExistenceOfEntity( lCrsReqL, "CorequisiteCourse" );
   if ( lTempInteger_20 == 0 )
   { 
      //:CreateViewFromView( mStudenC2, mStudenC )
      CreateViewFromView( mStudenC2, mStudenC );
      //:IF lCrsReqL.Course.CorequisiteBooleanOperator = "A"
      if ( CompareAttributeToString( lCrsReqL, "Course", "CorequisiteBooleanOperator", "A" ) == 0 )
      { 
         //:// Corequisites are to be AND'd.
         //:szMsg = "The following corequisites have not been met for Course " + szCourseNumber + ": "
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "The following corequisites have not been met for Course ", 1, 0, 1001 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szCourseNumber, 1, 0, 1001 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, ": ", 1, 0, 1001 );
         szMsg = sb_szMsg.toString( );}
         //:RequisiteError = ""
          {StringBuilder sb_RequisiteError;
         if ( RequisiteError == null )
            sb_RequisiteError = new StringBuilder( 32 );
         else
            sb_RequisiteError = new StringBuilder( RequisiteError );
                  ZeidonStringCopy( sb_RequisiteError, 1, 0, "", 1, 0, 2 );
         RequisiteError = sb_RequisiteError.toString( );}
         //:FOR EACH lCrsReqL.CorequisiteCourse 
         RESULT = SetCursorFirstEntity( lCrsReqL, "CorequisiteCourse", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:// Loop through all Registration entries looking for a match on the prerequisite.
            //:szRequisiteCourseNumber = lCrsReqL.CorequisiteCourse.Number
            {MutableInt mi_lTempInteger_21 = new MutableInt( lTempInteger_21 );
            StringBuilder sb_szRequisiteCourseNumber;
            if ( szRequisiteCourseNumber == null )
               sb_szRequisiteCourseNumber = new StringBuilder( 32 );
            else
               sb_szRequisiteCourseNumber = new StringBuilder( szRequisiteCourseNumber );
                         GetVariableFromAttribute( sb_szRequisiteCourseNumber, mi_lTempInteger_21, 'S', 11, lCrsReqL, "CorequisiteCourse", "Number", "", 0 );
            lTempInteger_21 = mi_lTempInteger_21.intValue( );
            szRequisiteCourseNumber = sb_szRequisiteCourseNumber.toString( );}
            //:nRC = FindCorequisiteClass( mStudenC, szRequisiteCourseNumber )
            nRC = omStudenC_FindCorequisiteClass( mStudenC, szRequisiteCourseNumber );
            //:IF nRC < 0     // The Prerequisite was NOT found.
            if ( nRC < 0 )
            { 
               //:RequisiteError = "Y"
                {StringBuilder sb_RequisiteError;
               if ( RequisiteError == null )
                  sb_RequisiteError = new StringBuilder( 32 );
               else
                  sb_RequisiteError = new StringBuilder( RequisiteError );
                              ZeidonStringCopy( sb_RequisiteError, 1, 0, "Y", 1, 0, 2 );
               RequisiteError = sb_RequisiteError.toString( );}
               //:szMsg = szMsg + lCrsReqL.CorequisiteCourse.Number + "; "
               {MutableInt mi_lTempInteger_22 = new MutableInt( lTempInteger_22 );
               StringBuilder sb_szTempString_12;
               if ( szTempString_12 == null )
                  sb_szTempString_12 = new StringBuilder( 32 );
               else
                  sb_szTempString_12 = new StringBuilder( szTempString_12 );
                               GetVariableFromAttribute( sb_szTempString_12, mi_lTempInteger_22, 'S', 11, lCrsReqL, "CorequisiteCourse", "Number", "", 0 );
               lTempInteger_22 = mi_lTempInteger_22.intValue( );
               szTempString_12 = sb_szTempString_12.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_12, 1, 0, 1001 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, "; ", 1, 0, 1001 );
               szMsg = sb_szMsg.toString( );}
            } 

            RESULT = SetCursorNextEntity( lCrsReqL, "CorequisiteCourse", "" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Corequisites are to be OR'd.
         //:szMsg = "One of the following corequisites must be met for Course " + szCourseNumber + ": "
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "One of the following corequisites must be met for Course ", 1, 0, 1001 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szCourseNumber, 1, 0, 1001 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, ": ", 1, 0, 1001 );
         szMsg = sb_szMsg.toString( );}
         //:RequisiteError = "Y"
          {StringBuilder sb_RequisiteError;
         if ( RequisiteError == null )
            sb_RequisiteError = new StringBuilder( 32 );
         else
            sb_RequisiteError = new StringBuilder( RequisiteError );
                  ZeidonStringCopy( sb_RequisiteError, 1, 0, "Y", 1, 0, 2 );
         RequisiteError = sb_RequisiteError.toString( );}
         //:FOR EACH lCrsReqL.CorequisiteCourse
         RESULT = SetCursorFirstEntity( lCrsReqL, "CorequisiteCourse", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:// Loop through all Registration entries looking for a match on the prerequisite.
            //:szRequisiteCourseNumber = lCrsReqL.CorequisiteCourse.Number
            {MutableInt mi_lTempInteger_23 = new MutableInt( lTempInteger_23 );
            StringBuilder sb_szRequisiteCourseNumber;
            if ( szRequisiteCourseNumber == null )
               sb_szRequisiteCourseNumber = new StringBuilder( 32 );
            else
               sb_szRequisiteCourseNumber = new StringBuilder( szRequisiteCourseNumber );
                         GetVariableFromAttribute( sb_szRequisiteCourseNumber, mi_lTempInteger_23, 'S', 11, lCrsReqL, "CorequisiteCourse", "Number", "", 0 );
            lTempInteger_23 = mi_lTempInteger_23.intValue( );
            szRequisiteCourseNumber = sb_szRequisiteCourseNumber.toString( );}
            //:nRC = FindCorequisiteClass( mStudenC, szRequisiteCourseNumber )
            nRC = omStudenC_FindCorequisiteClass( mStudenC, szRequisiteCourseNumber );
            //:IF nRC = 0   // The Prerequisite WAS found.
            if ( nRC == 0 )
            { 
               //:RequisiteError = ""
                {StringBuilder sb_RequisiteError;
               if ( RequisiteError == null )
                  sb_RequisiteError = new StringBuilder( 32 );
               else
                  sb_RequisiteError = new StringBuilder( RequisiteError );
                              ZeidonStringCopy( sb_RequisiteError, 1, 0, "", 1, 0, 2 );
               RequisiteError = sb_RequisiteError.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szMsg = szMsg + lCrsReqL.CorequisiteCourse.Number + "; "
               {MutableInt mi_lTempInteger_24 = new MutableInt( lTempInteger_24 );
               StringBuilder sb_szTempString_14;
               if ( szTempString_14 == null )
                  sb_szTempString_14 = new StringBuilder( 32 );
               else
                  sb_szTempString_14 = new StringBuilder( szTempString_14 );
                               GetVariableFromAttribute( sb_szTempString_14, mi_lTempInteger_24, 'S', 11, lCrsReqL, "CorequisiteCourse", "Number", "", 0 );
               lTempInteger_24 = mi_lTempInteger_24.intValue( );
               szTempString_14 = sb_szTempString_14.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_14, 1, 0, 1001 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, "; ", 1, 0, 1001 );
               szMsg = sb_szMsg.toString( );}
            } 

            RESULT = SetCursorNextEntity( lCrsReqL, "CorequisiteCourse", "" );
            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:// Go to validate Class Schedules.
   //:nRC = ValidateClassScheduleR( mStudenC, lClsLstC )
   nRC = omStudenC_ValidateClassScheduleR( mStudenC, lClsLstC );

   //:// If any of the above errors have produced a Return Code of 1, pass that back to the caller. Otherwise, pass back a 0.
   //:IF nRC = 1 OR nNonPreReqErrorRC = 1
   if ( nRC == 1 || nNonPreReqErrorRC == 1 )
   { 
      //:RETURN 1
      if(8==8)return( 1 );
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dAnticipatedAccCredits( VIEW mStudenC BASED ON LOD mStudenC,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   VIEW lTrnscpt BASED ON LOD  lTrnscpt
public int 
omStudenC_dAnticipatedAccCredits( View     mStudenC,
                                  String InternalEntityStructure,
                                  String InternalAttribStructure,
                                  Integer   GetOrSetFlag )
{
   zVIEW    lTrnscpt = new zVIEW( );
   //:VIEW lClsLstC BASED ON LOD  lClsLstC
   zVIEW    lClsLstC = new zVIEW( );
   //:VIEW mStuPSch BASED ON LOD  mStuPSch
   zVIEW    mStuPSch = new zVIEW( );
   //:STRING ( 1 )   szCollegeType
   String   szCollegeType = null;
   //:STRING ( 20 )  szCollegeYearSemester
   String   szCollegeYearSemester = null;
   //:STRING ( 20 )  szCurrentCollegeYearSemester
   String   szCurrentCollegeYearSemester = null;
   //:STRING ( 20 )  szTranscriptCollegeYearSemester
   String   szTranscriptCollegeYearSemester = null;
   //:STRING ( 1 )   szStatus
   String   szStatus = null;
   //:STRING ( 1 )   szErrorFlag
   String   szErrorFlag = null;
   //:DECIMAL        dTotalCredits
   double  dTotalCredits = 0.0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_3 = null;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_5 = 0;
   String   szTempString_4 = null;
   int      lTempInteger_6 = 0;
   String   szTempString_5 = null;
   double  dTempDecimal_1 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Determine the anticipated accumulated credits for the CollegeTerm from the Class in the lClsLstC.
         //:// We will get the total accumulated credits from the lTranscpt object for the College Term.

         //:// Don't process if we don't have the lClsLstC view.
         //:GET VIEW lClsLstC NAMED "lClsLstC"
         RESULT = GetViewByName( lClsLstC, "lClsLstC", mStudenC, zLEVEL_TASK );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:GET VIEW lClsLstC NAMED "TermslClsLstC"
            RESULT = GetViewByName( lClsLstC, "TermslClsLstC", mStudenC, zLEVEL_TASK );
            //:IF RESULT < 0
            if ( RESULT < 0 )
            { 
               //:szErrorFlag = "Y"
                {StringBuilder sb_szErrorFlag;
               if ( szErrorFlag == null )
                  sb_szErrorFlag = new StringBuilder( 32 );
               else
                  sb_szErrorFlag = new StringBuilder( szErrorFlag );
                              ZeidonStringCopy( sb_szErrorFlag, 1, 0, "Y", 1, 0, 2 );
               szErrorFlag = sb_szErrorFlag.toString( );}
            } 

            //:END
         } 

         //:END

         //:IF  szErrorFlag = "" 
         if ( ZeidonStringCompare( szErrorFlag, 1, 0, "", 1, 0, 2 ) == 0 )
         { 


            //:// Get list of Academic Standing entries for the Student and order by date so we can get the latest entry.
            //:ACTIVATE lTrnscpt WHERE lTrnscpt.Student.ID = mStudenC.Student.ID 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mStudenC, "Student", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            omStudenC_fnLocalBuildQual_3( mStudenC, vTempViewVar_0, lTempInteger_0 );
            RESULT = ActivateObjectInstance( lTrnscpt, "lTrnscpt", mStudenC, vTempViewVar_0, zSINGLE );
            DropView( vTempViewVar_0 );
            //:NAME VIEW lTrnscpt "lTrnscptClassification"
            SetNameForView( lTrnscpt, "lTrnscptClassification", null, zLEVEL_TASK );

            //:// Determine Grad/Undergrad status from Class.
            //:szCollegeType = lClsLstC.College.Type 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szCollegeType;
            if ( szCollegeType == null )
               sb_szCollegeType = new StringBuilder( 32 );
            else
               sb_szCollegeType = new StringBuilder( szCollegeType );
                         GetVariableFromAttribute( sb_szCollegeType, mi_lTempInteger_1, 'S', 2, lClsLstC, "College", "Type", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szCollegeType = sb_szCollegeType.toString( );}

            //:// Build transcript to get accumulative credit totals.
            //:BuildTranscript( lTrnscpt, szCollegeType )
            {
             lTrnscpt_Object m_lTrnscpt_Object = new lTrnscpt_Object( lTrnscpt );
             m_lTrnscpt_Object.olTrnscpt_BuildTranscript( lTrnscpt, szCollegeType );
             // m_lTrnscpt_Object = null;  // permit gc  (unnecessary)
            }
            //:SET CURSOR LAST lTrnscpt.PrintGroup 
            RESULT = SetCursorLastEntity( lTrnscpt, "PrintGroup", "" );
            //:dTotalCredits = lTrnscpt.PrintGroup.CumulativeEarnedCredits
            {MutableDouble md_dTotalCredits = new MutableDouble( dTotalCredits );
                         GetDecimalFromAttribute( md_dTotalCredits, lTrnscpt, "PrintGroup", "CumulativeEarnedCredits" );
            dTotalCredits = md_dTotalCredits.doubleValue( );}
            //:szTranscriptCollegeYearSemester = lTrnscpt.PrintGroup.wCollegeYear + lTrnscpt.PrintGroup.Semester
            {StringBuilder sb_szTranscriptCollegeYearSemester;
            if ( szTranscriptCollegeYearSemester == null )
               sb_szTranscriptCollegeYearSemester = new StringBuilder( 32 );
            else
               sb_szTranscriptCollegeYearSemester = new StringBuilder( szTranscriptCollegeYearSemester );
                         GetStringFromAttribute( sb_szTranscriptCollegeYearSemester, lTrnscpt, "PrintGroup", "wCollegeYear" );
            szTranscriptCollegeYearSemester = sb_szTranscriptCollegeYearSemester.toString( );}
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, lTrnscpt, "PrintGroup", "Semester" );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szTranscriptCollegeYearSemester;
            if ( szTranscriptCollegeYearSemester == null )
               sb_szTranscriptCollegeYearSemester = new StringBuilder( 32 );
            else
               sb_szTranscriptCollegeYearSemester = new StringBuilder( szTranscriptCollegeYearSemester );
                        ZeidonStringConcat( sb_szTranscriptCollegeYearSemester, 1, 0, szTempString_0, 1, 0, 21 );
            szTranscriptCollegeYearSemester = sb_szTranscriptCollegeYearSemester.toString( );}

            //:// Get current Year and Semester for comparison purposes.
            //:szCurrentCollegeYearSemester = mStudenC.UpdateSchedule.CollegeYear + mStudenC.UpdateSchedule.CollegeTermSemester
            {StringBuilder sb_szCurrentCollegeYearSemester;
            if ( szCurrentCollegeYearSemester == null )
               sb_szCurrentCollegeYearSemester = new StringBuilder( 32 );
            else
               sb_szCurrentCollegeYearSemester = new StringBuilder( szCurrentCollegeYearSemester );
                         GetStringFromAttribute( sb_szCurrentCollegeYearSemester, mStudenC, "UpdateSchedule", "CollegeYear" );
            szCurrentCollegeYearSemester = sb_szCurrentCollegeYearSemester.toString( );}
            {StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetStringFromAttribute( sb_szTempString_1, mStudenC, "UpdateSchedule", "CollegeTermSemester" );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szCurrentCollegeYearSemester;
            if ( szCurrentCollegeYearSemester == null )
               sb_szCurrentCollegeYearSemester = new StringBuilder( 32 );
            else
               sb_szCurrentCollegeYearSemester = new StringBuilder( szCurrentCollegeYearSemester );
                        ZeidonStringConcat( sb_szCurrentCollegeYearSemester, 1, 0, szTempString_1, 1, 0, 21 );
            szCurrentCollegeYearSemester = sb_szCurrentCollegeYearSemester.toString( );}

            //:// Add any Classes enrolled since the last Term in the Transcript but less than the current Term.
            //:// Because any "W" or "C" status would cause the term for which the Student is currently enrolled to appear on the
            //:// Transcript, we will only look for Enrolled entries.
            //:FOR EACH mStudenC.Registration 
            RESULT = SetCursorFirstEntity( mStudenC, "Registration", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:szStatus = mStudenC.Registration.Status
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szStatus;
               if ( szStatus == null )
                  sb_szStatus = new StringBuilder( 32 );
               else
                  sb_szStatus = new StringBuilder( szStatus );
                               GetVariableFromAttribute( sb_szStatus, mi_lTempInteger_2, 'S', 2, mStudenC, "Registration", "Status", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szStatus = sb_szStatus.toString( );}
               //:IF szStatus = "T"
               if ( ZeidonStringCompare( szStatus, 1, 0, "T", 1, 0, 2 ) == 0 )
               { 
                  //:szCollegeYearSemester = ""
                   {StringBuilder sb_szCollegeYearSemester;
                  if ( szCollegeYearSemester == null )
                     sb_szCollegeYearSemester = new StringBuilder( 32 );
                  else
                     sb_szCollegeYearSemester = new StringBuilder( szCollegeYearSemester );
                                    ZeidonStringCopy( sb_szCollegeYearSemester, 1, 0, "", 1, 0, 21 );
                  szCollegeYearSemester = sb_szCollegeYearSemester.toString( );}
                  //:IF mStudenC.RegistrationClassCollegeTerm EXISTS
                  lTempInteger_3 = CheckExistenceOfEntity( mStudenC, "RegistrationClassCollegeTerm" );
                  if ( lTempInteger_3 == 0 )
                  { 
                     //:szCollegeYearSemester = mStudenC.RegistrationClassCollegeYear.Year + mStudenC.RegistrationClassCollegeTerm.Semester
                     {StringBuilder sb_szCollegeYearSemester;
                     if ( szCollegeYearSemester == null )
                        sb_szCollegeYearSemester = new StringBuilder( 32 );
                     else
                        sb_szCollegeYearSemester = new StringBuilder( szCollegeYearSemester );
                                           GetStringFromAttribute( sb_szCollegeYearSemester, mStudenC, "RegistrationClassCollegeYear", "Year" );
                     szCollegeYearSemester = sb_szCollegeYearSemester.toString( );}
                     {StringBuilder sb_szTempString_2;
                     if ( szTempString_2 == null )
                        sb_szTempString_2 = new StringBuilder( 32 );
                     else
                        sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                           GetStringFromAttribute( sb_szTempString_2, mStudenC, "RegistrationClassCollegeTerm", "Semester" );
                     szTempString_2 = sb_szTempString_2.toString( );}
                      {StringBuilder sb_szCollegeYearSemester;
                     if ( szCollegeYearSemester == null )
                        sb_szCollegeYearSemester = new StringBuilder( 32 );
                     else
                        sb_szCollegeYearSemester = new StringBuilder( szCollegeYearSemester );
                                          ZeidonStringConcat( sb_szCollegeYearSemester, 1, 0, szTempString_2, 1, 0, 21 );
                     szCollegeYearSemester = sb_szCollegeYearSemester.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF mStudenC.RegisteredCollegeTerm EXISTS
                     lTempInteger_4 = CheckExistenceOfEntity( mStudenC, "RegisteredCollegeTerm" );
                     if ( lTempInteger_4 == 0 )
                     { 
                        //:szCollegeYearSemester = mStudenC.RegisteredCollegeYear.Year + mStudenC.RegisteredCollegeTerm.Semester 
                        {StringBuilder sb_szCollegeYearSemester;
                        if ( szCollegeYearSemester == null )
                           sb_szCollegeYearSemester = new StringBuilder( 32 );
                        else
                           sb_szCollegeYearSemester = new StringBuilder( szCollegeYearSemester );
                                                 GetStringFromAttribute( sb_szCollegeYearSemester, mStudenC, "RegisteredCollegeYear", "Year" );
                        szCollegeYearSemester = sb_szCollegeYearSemester.toString( );}
                        {StringBuilder sb_szTempString_3;
                        if ( szTempString_3 == null )
                           sb_szTempString_3 = new StringBuilder( 32 );
                        else
                           sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                                 GetStringFromAttribute( sb_szTempString_3, mStudenC, "RegisteredCollegeTerm", "Semester" );
                        szTempString_3 = sb_szTempString_3.toString( );}
                         {StringBuilder sb_szCollegeYearSemester;
                        if ( szCollegeYearSemester == null )
                           sb_szCollegeYearSemester = new StringBuilder( 32 );
                        else
                           sb_szCollegeYearSemester = new StringBuilder( szCollegeYearSemester );
                                                ZeidonStringConcat( sb_szCollegeYearSemester, 1, 0, szTempString_3, 1, 0, 21 );
                        szCollegeYearSemester = sb_szCollegeYearSemester.toString( );}
                     } 

                     //:END
                  } 

                  //:END
                  //:IF szCollegeYearSemester >= szTranscriptCollegeYearSemester AND szCollegeYearSemester < szCurrentCollegeYearSemester
                  if ( ZeidonStringCompare( szCollegeYearSemester, 1, 0, szTranscriptCollegeYearSemester, 1, 0, 21 ) <= 0 && ZeidonStringCompare( szCollegeYearSemester, 1, 0, szCurrentCollegeYearSemester, 1, 0, 21 ) > 0 )
                  { 
                     //:// Finally add these credits to the total.
                     //:dTotalCredits = dTotalCredits + mStudenC.Registration.CreditHours
                     {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                           GetDecimalFromAttribute( md_dTempDecimal_0, mStudenC, "Registration", "CreditHours" );
                     dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                     dTotalCredits = dTotalCredits + dTempDecimal_0;
                  } 

                  //:END
               } 

               RESULT = SetCursorNextEntity( mStudenC, "Registration", "" );
               //:END
            } 

            //:END

            //:DropObjectInstance( lTrnscpt )
            DropObjectInstance( lTrnscpt );


            //:// We need to also add in any proposed registrations.
            //:GET VIEW  mStuPSch  NAMED "mStuPSch"
            RESULT = GetViewByName( mStuPSch, "mStuPSch", mStudenC, zLEVEL_TASK );
            //:IF  RESULT >= 0
            if ( RESULT >= 0 )
            { 
               //:FOR EACH mStuPSch.ProposedEnrollment 
               RESULT = SetCursorFirstEntity( mStuPSch, "ProposedEnrollment", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:IF  mStuPSch.ProposedEnrollment.DeleteEntryFlag != "Y"
                  if ( CompareAttributeToString( mStuPSch, "ProposedEnrollment", "DeleteEntryFlag", "Y" ) != 0 )
                  { 
                     //:szCollegeYearSemester = ""
                      {StringBuilder sb_szCollegeYearSemester;
                     if ( szCollegeYearSemester == null )
                        sb_szCollegeYearSemester = new StringBuilder( 32 );
                     else
                        sb_szCollegeYearSemester = new StringBuilder( szCollegeYearSemester );
                                          ZeidonStringCopy( sb_szCollegeYearSemester, 1, 0, "", 1, 0, 21 );
                     szCollegeYearSemester = sb_szCollegeYearSemester.toString( );}
                     //:// Do I need to know what college term we are using for the class being added????

                     //:IF mStudenC.RegistrationClassCollegeTerm EXISTS
                     lTempInteger_5 = CheckExistenceOfEntity( mStudenC, "RegistrationClassCollegeTerm" );
                     if ( lTempInteger_5 == 0 )
                     { 
                        //:szCollegeYearSemester = mStudenC.RegistrationClassCollegeYear.Year + mStudenC.RegistrationClassCollegeTerm.Semester
                        {StringBuilder sb_szCollegeYearSemester;
                        if ( szCollegeYearSemester == null )
                           sb_szCollegeYearSemester = new StringBuilder( 32 );
                        else
                           sb_szCollegeYearSemester = new StringBuilder( szCollegeYearSemester );
                                                 GetStringFromAttribute( sb_szCollegeYearSemester, mStudenC, "RegistrationClassCollegeYear", "Year" );
                        szCollegeYearSemester = sb_szCollegeYearSemester.toString( );}
                        {StringBuilder sb_szTempString_4;
                        if ( szTempString_4 == null )
                           sb_szTempString_4 = new StringBuilder( 32 );
                        else
                           sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                                 GetStringFromAttribute( sb_szTempString_4, mStudenC, "RegistrationClassCollegeTerm", "Semester" );
                        szTempString_4 = sb_szTempString_4.toString( );}
                         {StringBuilder sb_szCollegeYearSemester;
                        if ( szCollegeYearSemester == null )
                           sb_szCollegeYearSemester = new StringBuilder( 32 );
                        else
                           sb_szCollegeYearSemester = new StringBuilder( szCollegeYearSemester );
                                                ZeidonStringConcat( sb_szCollegeYearSemester, 1, 0, szTempString_4, 1, 0, 21 );
                        szCollegeYearSemester = sb_szCollegeYearSemester.toString( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:IF mStudenC.RegisteredCollegeTerm EXISTS
                        lTempInteger_6 = CheckExistenceOfEntity( mStudenC, "RegisteredCollegeTerm" );
                        if ( lTempInteger_6 == 0 )
                        { 
                           //:szCollegeYearSemester = mStudenC.RegisteredCollegeYear.Year + mStudenC.RegisteredCollegeTerm.Semester 
                           {StringBuilder sb_szCollegeYearSemester;
                           if ( szCollegeYearSemester == null )
                              sb_szCollegeYearSemester = new StringBuilder( 32 );
                           else
                              sb_szCollegeYearSemester = new StringBuilder( szCollegeYearSemester );
                                                       GetStringFromAttribute( sb_szCollegeYearSemester, mStudenC, "RegisteredCollegeYear", "Year" );
                           szCollegeYearSemester = sb_szCollegeYearSemester.toString( );}
                           {StringBuilder sb_szTempString_5;
                           if ( szTempString_5 == null )
                              sb_szTempString_5 = new StringBuilder( 32 );
                           else
                              sb_szTempString_5 = new StringBuilder( szTempString_5 );
                                                       GetStringFromAttribute( sb_szTempString_5, mStudenC, "RegisteredCollegeTerm", "Semester" );
                           szTempString_5 = sb_szTempString_5.toString( );}
                            {StringBuilder sb_szCollegeYearSemester;
                           if ( szCollegeYearSemester == null )
                              sb_szCollegeYearSemester = new StringBuilder( 32 );
                           else
                              sb_szCollegeYearSemester = new StringBuilder( szCollegeYearSemester );
                                                      ZeidonStringConcat( sb_szCollegeYearSemester, 1, 0, szTempString_5, 1, 0, 21 );
                           szCollegeYearSemester = sb_szCollegeYearSemester.toString( );}
                        } 

                        //:END
                     } 

                     //:END
                     //:IF szCollegeYearSemester > szTranscriptCollegeYearSemester AND szCollegeYearSemester < szCurrentCollegeYearSemester
                     if ( ZeidonStringCompare( szCollegeYearSemester, 1, 0, szTranscriptCollegeYearSemester, 1, 0, 21 ) < 0 && ZeidonStringCompare( szCollegeYearSemester, 1, 0, szCurrentCollegeYearSemester, 1, 0, 21 ) > 0 )
                     { 
                        //:// Finally add these credits to the total.
                        //:dTotalCredits = dTotalCredits + mStuPSch.ProposedEnrollment.CreditHours 
                        {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_1, mStuPSch, "ProposedEnrollment", "CreditHours" );
                        dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
                        dTotalCredits = dTotalCredits + dTempDecimal_1;
                     } 

                     //:END
                  } 

                  RESULT = SetCursorNextEntity( mStuPSch, "ProposedEnrollment", "" );
                  //:                     
                  //:END 
               } 

               //:END 
            } 

            //:END

            //:// Store the calculated credits in the object.
            //:StoreValueInRecord ( mStudenC,
            //:                  InternalEntityStructure, InternalAttribStructure, dTotalCredits, 0 )
            StoreValueInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, dTotalCredits, 0 );
            //:ELSE
         } 
         else
         { 
            //:StoreStringInRecord ( mStudenC,
            //:                   InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, "" );
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


//:TRANSFORMATION OPERATION
//:AddClassToStudentSched( VIEW mStudenC BASED ON LOD mStudenC,
//:                        VIEW lClsLstC BASED ON LOD lClsLstC,
//:                        STRING ( 100 ) szStudentNameMsg )

//:   STRING ( 200 ) szMeetingSchedule
public int 
omStudenC_AddClassToStudentSched( View     mStudenC,
                                  View     lClsLstC,
                                  String   szStudentNameMsg )
{
   String   szMeetingSchedule = null;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   //:STRING ( 30 )  szMsgPart
   String   szMsgPart = null;
   //:SHORT nRC
   int      nRC = 0;
   //:SHORT nRC_PreReq
   int      nRC_PreReq = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_2 = 0;
   int      RESULT = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;


   //:// Reusable Operation for registering a student for a Class.
   //:// Try to add the Class selected (positioned on) in lClsLstC. Process validation errors as necessary.
   //:// When called from normal Term scheduling, we would expect any number of errors.
   //:// When called from processing Proposed Schedules, we would only expect errors where a Class is full or closed.
   //:// Note that szStudentNameMsg is null when called from normal Term scheduling, but contains Student name when
   //:// called from Proposed Schedules.

   //:// First run validations on Class.
   //:nRC_PreReq = ValidateAddedCourse( mStudenC, lClsLstC )  // A return code of 2 means Pre-reqs were not met but the operator accepted the Class anyway.
   nRC_PreReq = omStudenC_ValidateAddedCourse( mStudenC, lClsLstC );
   //:IF nRC_PreReq >= 0
   if ( nRC_PreReq >= 0 )
   { 
      //:// If Class is closed, Prompt operator if Student should be waitlisted for Class.
      //:IF lClsLstC.Class.Status = "L" OR
      //:   lClsLstC.Class.dTotalEnrolled >= lClsLstC.Class.EnrollmentLimit OR 
      //:   lClsLstC.StudentWaitlisted EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( lClsLstC, "StudentWaitlisted" );
      if ( CompareAttributeToString( lClsLstC, "Class", "Status", "L" ) == 0 || CompareAttributeToAttribute( lClsLstC, "Class", "dTotalEnrolled", lClsLstC, "Class", "EnrollmentLimit" ) >= 0 || lTempInteger_0 == 0 )
      { 

         //:IF lClsLstC.Class.Status = "L"
         if ( CompareAttributeToString( lClsLstC, "Class", "Status", "L" ) == 0 )
         { 
            //:szMsgPart = " is closed."
             {StringBuilder sb_szMsgPart;
            if ( szMsgPart == null )
               sb_szMsgPart = new StringBuilder( 32 );
            else
               sb_szMsgPart = new StringBuilder( szMsgPart );
                        ZeidonStringCopy( sb_szMsgPart, 1, 0, " is closed.", 1, 0, 31 );
            szMsgPart = sb_szMsgPart.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF lClsLstC.Class.dTotalEnrolled >= lClsLstC.Class.EnrollmentLimit
            if ( CompareAttributeToAttribute( lClsLstC, "Class", "dTotalEnrolled", lClsLstC, "Class", "EnrollmentLimit" ) >= 0 )
            { 
               //:szMsgPart = " has reached its limit."
                {StringBuilder sb_szMsgPart;
               if ( szMsgPart == null )
                  sb_szMsgPart = new StringBuilder( 32 );
               else
                  sb_szMsgPart = new StringBuilder( szMsgPart );
                              ZeidonStringCopy( sb_szMsgPart, 1, 0, " has reached its limit.", 1, 0, 31 );
               szMsgPart = sb_szMsgPart.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szMsgPart = " has waitlisted students."
                {StringBuilder sb_szMsgPart;
               if ( szMsgPart == null )
                  sb_szMsgPart = new StringBuilder( 32 );
               else
                  sb_szMsgPart = new StringBuilder( szMsgPart );
                              ZeidonStringCopy( sb_szMsgPart, 1, 0, " has waitlisted students.", 1, 0, 31 );
               szMsgPart = sb_szMsgPart.toString( );}
            } 

            //:END
         } 

         //:END
         //:szMsg = szStudentNameMsg + "Class " + lClsLstC.Course.Number + " " + lClsLstC.Class.Section + szMsgPart + NEW_LINE +
         //:        "Should the student be added to the class anyway?"
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, szStudentNameMsg, 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, "Class ", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, lClsLstC, "Course", "Number", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_0, 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, " ", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 3, lClsLstC, "Class", "Section", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_1, 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szMsgPart, 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, "Should the student be added to the class anyway?", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
         //:nRC = MessagePrompt( mStudenC, "", "Course Validations",
         //:                     szMsg,
         //:                     0, zBUTTONS_YESNO, zRESPONSE_NO, 0 )
         nRC = MessagePrompt( mStudenC, "", "Course Validations", szMsg, 0, zBUTTONS_YESNO, zRESPONSE_NO, 0 );
         //:IF nRC = zRESPONSE_YES
         if ( nRC == zRESPONSE_YES )
         { 
            //:CREATE ENTITY mStudenC.US_Registration 
            RESULT = CreateEntity( mStudenC, "US_Registration", zPOS_AFTER );
            //:IF nRC_PreReq = 2
            if ( nRC_PreReq == 2 )
            { 
               //:mStudenC.US_Registration.PrereqsNotMet = "Y"
               SetAttributeFromString( mStudenC, "US_Registration", "PrereqsNotMet", "Y" );
            } 

            //:END
            //:INCLUDE mStudenC.US_Class FROM lClsLstC.Class 
            RESULT = IncludeSubobjectFromSubobject( mStudenC, "US_Class", lClsLstC, "Class", zPOS_AFTER );
            //:IF lClsLstC.CorrespondingCrossListedCourse EXISTS
            lTempInteger_3 = CheckExistenceOfEntity( lClsLstC, "CorrespondingCrossListedCourse" );
            if ( lTempInteger_3 == 0 )
            { 
               //:INCLUDE mStudenC.US_CrossListedCourse FROM lClsLstC.CorrespondingCrossListedCourse
               RESULT = IncludeSubobjectFromSubobject( mStudenC, "US_CrossListedCourse", lClsLstC, "CorrespondingCrossListedCourse", zPOS_AFTER );
               //:IF lClsLstC.CorrespondingCrossListedCollege.Type != lClsLstC.College.Type 
               if ( CompareAttributeToAttribute( lClsLstC, "CorrespondingCrossListedCollege", "Type", lClsLstC, "College", "Type" ) != 0 )
               { 
                  //:// Since the CrossListedCourse is of different Type than the PrimaryCourse (e.g., G instead of U),
                  //:// we need to override the Student's enrollment entry.
                  //:mStudenC.US_Registration.GradUndergradOverrideFlag = lClsLstC.CorrespondingCrossListedCollege.Type 
                  SetAttributeFromAttribute( mStudenC, "US_Registration", "GradUndergradOverrideFlag", lClsLstC, "CorrespondingCrossListedCollege", "Type" );
               } 

               //:END
            } 

            //:END
            //:BuildRegistrationEntry( mStudenC, "US_Registration" )
            omStudenC_BuildRegistrationEntry( mStudenC, "US_Registration" );
            //:IF mStudenC.US_CourseTopic EXISTS
            lTempInteger_4 = CheckExistenceOfEntity( mStudenC, "US_CourseTopic" );
            if ( lTempInteger_4 == 0 )
            { 
               //:mStudenC.US_Class.wCourseTitle = mStudenC.US_CourseTopic.Title 
               SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseTitle", mStudenC, "US_CourseTopic", "Title" );
               //:ELSE
            } 
            else
            { 
               //:mStudenC.US_Class.wCourseTitle = mStudenC.US_Course.Title 
               SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseTitle", mStudenC, "US_Course", "Title" );
            } 

            //:END
            //:mStudenC.US_Class.wCourseSection = mStudenC.US_Registration.dClassNumber 
            SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseSection", mStudenC, "US_Registration", "dClassNumber" );
            //:ELSE
         } 
         else
         { 
            //:szMsg = szStudentNameMsg + "Class " + lClsLstC.Course.Number + " is closed." + NEW_LINE +
            //:        "Should the student be waitlisted for the class?"
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringCopy( sb_szMsg, 1, 0, szStudentNameMsg, 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, "Class ", 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
            {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
            StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_5, 'S', 11, lClsLstC, "Course", "Number", "", 0 );
            lTempInteger_5 = mi_lTempInteger_5.intValue( );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_2, 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, " is closed.", 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, "Should the student be waitlisted for the class?", 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
            //:nRC = MessagePrompt( mStudenC, "", "Course Validations",
            //:                     szMsg,
            //:                     0, zBUTTONS_YESNO, zRESPONSE_NO, 0 )
            nRC = MessagePrompt( mStudenC, "", "Course Validations", szMsg, 0, zBUTTONS_YESNO, zRESPONSE_NO, 0 );
            //:IF nRC = zRESPONSE_YES
            if ( nRC == zRESPONSE_YES )
            { 
               //:CREATE ENTITY mStudenC.US_Registration 
               RESULT = CreateEntity( mStudenC, "US_Registration", zPOS_AFTER );
               //:IF nRC_PreReq = 2
               if ( nRC_PreReq == 2 )
               { 
                  //:mStudenC.US_Registration.PrereqsNotMet = "Y"
                  SetAttributeFromString( mStudenC, "US_Registration", "PrereqsNotMet", "Y" );
               } 

               //:END
               //:INCLUDE mStudenC.US_Class FROM lClsLstC.Class 
               RESULT = IncludeSubobjectFromSubobject( mStudenC, "US_Class", lClsLstC, "Class", zPOS_AFTER );
               //:IF lClsLstC.CorrespondingCrossListedCourse EXISTS
               lTempInteger_6 = CheckExistenceOfEntity( lClsLstC, "CorrespondingCrossListedCourse" );
               if ( lTempInteger_6 == 0 )
               { 
                  //:INCLUDE mStudenC.US_CrossListedCourse FROM lClsLstC.CorrespondingCrossListedCourse
                  RESULT = IncludeSubobjectFromSubobject( mStudenC, "US_CrossListedCourse", lClsLstC, "CorrespondingCrossListedCourse", zPOS_AFTER );
                  //:IF lClsLstC.CorrespondingCrossListedCollege.Type != lClsLstC.College.Type 
                  if ( CompareAttributeToAttribute( lClsLstC, "CorrespondingCrossListedCollege", "Type", lClsLstC, "College", "Type" ) != 0 )
                  { 
                     //:// Since the CrossListedCourse is of different Type than the PrimaryCourse (e.g., G instead of U),
                     //:// we need to override the Student's enrollment entry.
                     //:mStudenC.US_Registration.GradUndergradOverrideFlag = lClsLstC.CorrespondingCrossListedCollege.Type 
                     SetAttributeFromAttribute( mStudenC, "US_Registration", "GradUndergradOverrideFlag", lClsLstC, "CorrespondingCrossListedCollege", "Type" );
                  } 

                  //:END
               } 

               //:END
               //:BuildRegistrationEntry( mStudenC, "US_Registration" )
               omStudenC_BuildRegistrationEntry( mStudenC, "US_Registration" );
               //:IF mStudenC.US_CourseTopic EXISTS
               lTempInteger_7 = CheckExistenceOfEntity( mStudenC, "US_CourseTopic" );
               if ( lTempInteger_7 == 0 )
               { 
                  //:mStudenC.US_Class.wCourseTitle = mStudenC.US_CourseTopic.Title 
                  SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseTitle", mStudenC, "US_CourseTopic", "Title" );
                  //:ELSE
               } 
               else
               { 
                  //:mStudenC.US_Class.wCourseTitle = mStudenC.US_Course.Title 
                  SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseTitle", mStudenC, "US_Course", "Title" );
               } 

               //:END
               //:mStudenC.US_Class.wCourseSection = mStudenC.US_Registration.dClassNumber 
               SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseSection", mStudenC, "US_Registration", "dClassNumber" );
               //:mStudenC.US_Registration.CreditHours = mStudenC.US_Class.CreditHours 
               SetAttributeFromAttribute( mStudenC, "US_Registration", "CreditHours", mStudenC, "US_Class", "CreditHours" );
               //:mStudenC.US_Registration.Status = "L"      // Reset status to waitlisted.
               SetAttributeFromString( mStudenC, "US_Registration", "Status", "L" );
            } 

            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// All validations are OK and Class is open, so add entry.
         //:CREATE ENTITY mStudenC.US_Registration 
         RESULT = CreateEntity( mStudenC, "US_Registration", zPOS_AFTER );
         //:IF nRC_PreReq = 2
         if ( nRC_PreReq == 2 )
         { 
            //:mStudenC.US_Registration.PrereqsNotMet = "Y"
            SetAttributeFromString( mStudenC, "US_Registration", "PrereqsNotMet", "Y" );
         } 

         //:END
         //:INCLUDE mStudenC.US_Class FROM lClsLstC.Class 
         RESULT = IncludeSubobjectFromSubobject( mStudenC, "US_Class", lClsLstC, "Class", zPOS_AFTER );
         //:IF lClsLstC.CorrespondingCrossListedCourse EXISTS
         lTempInteger_8 = CheckExistenceOfEntity( lClsLstC, "CorrespondingCrossListedCourse" );
         if ( lTempInteger_8 == 0 )
         { 
            //:INCLUDE mStudenC.US_CrossListedCourse FROM lClsLstC.CorrespondingCrossListedCourse
            RESULT = IncludeSubobjectFromSubobject( mStudenC, "US_CrossListedCourse", lClsLstC, "CorrespondingCrossListedCourse", zPOS_AFTER );
            //:IF lClsLstC.CorrespondingCrossListedCollege.Type != lClsLstC.College.Type 
            if ( CompareAttributeToAttribute( lClsLstC, "CorrespondingCrossListedCollege", "Type", lClsLstC, "College", "Type" ) != 0 )
            { 
               //:// Since the CrossListedCourse is of different Type than the PrimaryCourse (e.g., G instead of U),
               //:// we need to override the Student's enrollment entry.
               //:mStudenC.US_Registration.GradUndergradOverrideFlag = lClsLstC.CorrespondingCrossListedCollege.Type 
               SetAttributeFromAttribute( mStudenC, "US_Registration", "GradUndergradOverrideFlag", lClsLstC, "CorrespondingCrossListedCollege", "Type" );
            } 

            //:END
         } 

         //:END
         //:BuildRegistrationEntry( mStudenC, "US_Registration" )
         omStudenC_BuildRegistrationEntry( mStudenC, "US_Registration" );
         //:IF mStudenC.US_CourseTopic EXISTS
         lTempInteger_9 = CheckExistenceOfEntity( mStudenC, "US_CourseTopic" );
         if ( lTempInteger_9 == 0 )
         { 
            //:mStudenC.US_Class.wCourseTitle = mStudenC.US_CourseTopic.Title 
            SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseTitle", mStudenC, "US_CourseTopic", "Title" );
            //:ELSE
         } 
         else
         { 
            //:mStudenC.US_Class.wCourseTitle = mStudenC.US_Course.Title 
            SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseTitle", mStudenC, "US_Course", "Title" );
         } 

         //:END
         //:mStudenC.US_Class.wCourseSection = mStudenC.US_Registration.dClassNumber 
         SetAttributeFromAttribute( mStudenC, "US_Class", "wCourseSection", mStudenC, "US_Registration", "dClassNumber" );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:SaveStudentSchedule( VIEW mStudenC BASED ON LOD mStudenC,
//:                     VIEW ViewToWindow )

//:   VIEW mStudenCN BASED ON LOD  mStudenC
public int 
omStudenC_SaveStudentSchedule( View     mStudenC,
                               View     ViewToWindow )
{
   zVIEW    mStudenCN = new zVIEW( );
   //:VIEW mClassSt  BASED ON LOD  mClassSt
   zVIEW    mClassSt = new zVIEW( );
   //:VIEW mStuBFlg  BASED ON LOD  mStuBFlg
   zVIEW    mStuBFlg = new zVIEW( );
   //:VIEW mUser     BASED ON LOD  mUser
   zVIEW    mUser = new zVIEW( );
   //:SHORT          nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   int      lTempInteger_6 = 0;
   zVIEW    vTempViewVar_3 = new zVIEW( );


   //:// Save the Student Schedule and complete processing by updating related data.

   //:// Check if there are any overlapping schedule entries and exit if there are and the user specified abort.
   //:nRC = ValidateTermSchedules( mStudenC )
   nRC = omStudenC_ValidateTermSchedules( mStudenC );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Finally, get rid of the temporal subobject and commit mStudenC.
   //://CancelSubobject( mStudenC, "UpdateSchedule" )
   //:AcceptSubobject( mStudenC, "UpdateSchedule" )
   AcceptSubobject( mStudenC, "UpdateSchedule" );
   //:SaveAcademicObject( mStudenC )
   omStudenC_SaveAcademicObject( mStudenC );

   //:// Set the Student Accounts flag to indicate that this Student should be evaluated for billing changes.
   //:// We do this anytime the Student's schedule changes.
   //:GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_APPLICATION )
   GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_APPLICATION );
   //:ACTIVATE mStuBFlg WHERE mStuBFlg.Student.ID = mStudenC.Student.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mStudenC, "Student", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   //:      RESTRICTING mStuBFlg.StudentAccountProfile TO mStuBFlg.AdministrativeDivision.ID = mUser.CurrentAdministrativeDivision.ID 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mUser, "CurrentAdministrativeDivision", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   omStudenC_fnLocalBuildQual_6( mStudenC, vTempViewVar_0, lTempInteger_0, lTempInteger_1 );
   RESULT = ActivateObjectInstance( mStuBFlg, "mStuBFlg", mStudenC, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:IF mStuBFlg.StudentAccountProfile EXISTS
   lTempInteger_2 = CheckExistenceOfEntity( mStuBFlg, "StudentAccountProfile" );
   if ( lTempInteger_2 == 0 )
   { 
      //:mStuBFlg.StudentAccountProfile.BillingNeedsUpdated  = "Y"
      SetAttributeFromString( mStuBFlg, "StudentAccountProfile", "BillingNeedsUpdated", "Y" );
      //:mStuBFlg.StudentAccountProfile.BillingFlagAcademics = "Y"
      SetAttributeFromString( mStuBFlg, "StudentAccountProfile", "BillingFlagAcademics", "Y" );
      //:COMMIT mStuBFlg
      RESULT = CommitObjectInstance( mStuBFlg );
   } 

   //:END
   //:DropObjectInstance( mStuBFlg )
   DropObjectInstance( mStuBFlg );

   //:// Set the Class Status to 'Closed' for any Classes where the enrollment limit has been reached or reset
   //:// to 'Open' if the enrollment for a Closed class has fallen below the limit.
   //:CreateViewFromView( mStudenCN, mStudenC )
   CreateViewFromView( mStudenCN, mStudenC );
   //:FOR EACH mStudenC.US_RegistrationDeleted
   RESULT = SetCursorFirstEntity( mStudenC, "US_RegistrationDeleted", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mStudenC.US_RegistrationDeleted.Status != "L"     // Not waitlisted entry.
      if ( CompareAttributeToString( mStudenC, "US_RegistrationDeleted", "Status", "L" ) != 0 )
      { 
         //:ACTIVATE mClassSt WHERE mClassSt.Class.ID = mStudenC.US_RegistrationDeleted.wClassID
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                   GetIntegerFromAttribute( mi_lTempInteger_3, mStudenC, "US_RegistrationDeleted", "wClassID" );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );}
         //:        RESTRICTING mClassSt.Transaction TO mClassSt.Transaction.ID = 0
         omStudenC_fnLocalBuildQual_7( mStudenC, vTempViewVar_1, lTempInteger_3 );
         RESULT = ActivateObjectInstance( mClassSt, "mClassSt", mStudenC, vTempViewVar_1, zSINGLE );
         DropView( vTempViewVar_1 );
         //:UPDATE_ClassData( mClassSt )
         //:DropObjectInstance( mClassSt )
         DropObjectInstance( mClassSt );
      } 

      RESULT = SetCursorNextEntity( mStudenC, "US_RegistrationDeleted", "" );
      //:END
   } 

   //:END
   //:FOR EACH mStudenC.US_Registration
   RESULT = SetCursorFirstEntity( mStudenC, "US_Registration", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mStudenC.US_Class EXISTS
      lTempInteger_4 = CheckExistenceOfEntity( mStudenC, "US_Class" );
      if ( lTempInteger_4 == 0 )
      { 
         //:ACTIVATE mClassSt WHERE mClassSt.Class.ID = mStudenC.US_Class.ID 
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                   GetIntegerFromAttribute( mi_lTempInteger_5, mStudenC, "US_Class", "ID" );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );}
         //:        RESTRICTING mClassSt.Transaction TO mClassSt.Transaction.ID = 0
         omStudenC_fnLocalBuildQual_8( mStudenC, vTempViewVar_2, lTempInteger_5 );
         RESULT = ActivateObjectInstance( mClassSt, "mClassSt", mStudenC, vTempViewVar_2, zSINGLE );
         DropView( vTempViewVar_2 );
         //:UPDATE_ClassData( mClassSt )
         //:IF mStudenC.US_Registration.wOrigStatus = "L" AND
         //:   mStudenC.US_Registration.Status     != "L"
         if ( CompareAttributeToString( mStudenC, "US_Registration", "wOrigStatus", "L" ) == 0 && CompareAttributeToString( mStudenC, "US_Registration", "Status", "L" ) != 0 )
         { 

            //:UPDATE_ClassRanking( mClassSt )
          } 

         //:END
         //:DropObjectInstance( mClassSt )
         DropObjectInstance( mClassSt );
      } 

      RESULT = SetCursorNextEntity( mStudenC, "US_Registration", "" );
      //:END
   } 

   //:END
   //:DropView( mStudenCN )
   DropView( mStudenCN );

   //:// Update Class Waitlist Rankings for any new waitlisted entries (entries for which the WaitListRanking is null).
   //:FOR EACH mStudenC.StudentWaitlisted WHERE mStudenC.StudentWaitlisted.WaitListRanking = ""
   RESULT = SetCursorFirstEntityByString( mStudenC, "StudentWaitlisted", "WaitListRanking", "", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:ACTIVATE mClassSt WHERE mClassSt.Class.ID = mStudenC.StudentWaitlistedClass.ID
      {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
             GetIntegerFromAttribute( mi_lTempInteger_6, mStudenC, "StudentWaitlistedClass", "ID" );
      lTempInteger_6 = mi_lTempInteger_6.intValue( );}
      //:        RESTRICTING mClassSt.Transaction TO mClassSt.Transaction.ID = 0
      omStudenC_fnLocalBuildQual_9( mStudenC, vTempViewVar_3, lTempInteger_6 );
      RESULT = ActivateObjectInstance( mClassSt, "mClassSt", mStudenC, vTempViewVar_3, zSINGLE );
      DropView( vTempViewVar_3 );
      //:NAME VIEW mClassSt "mClassSt"
      SetNameForView( mClassSt, "mClassSt", null, zLEVEL_TASK );
      //:UPDATE_ClassRanking( mClassSt )
      //:DropObjectInstance( mClassSt )
      DropObjectInstance( mClassSt );
      RESULT = SetCursorNextEntityByString( mStudenC, "StudentWaitlisted", "WaitListRanking", "", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildUS_RegTotals( VIEW mStudenC BASED ON LOD mStudenC )

//:   DECIMAL TotalCredits
public int 
omStudenC_BuildUS_RegTotals( View     mStudenC )
{
   double  TotalCredits = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:// Create Total Credits entry for UpdateSchedule subobject.
   //:TotalCredits = 0
   TotalCredits = 0;
   //:FOR EACH mStudenC.US_Registration 
   RESULT = SetCursorFirstEntity( mStudenC, "US_Registration", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mStudenC.US_Registration.Status != "L"    // Add all but waitlisted.
      if ( CompareAttributeToString( mStudenC, "US_Registration", "Status", "L" ) != 0 )
      { 
         //:TotalCredits = TotalCredits + mStudenC.US_Registration.CreditHours 
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, mStudenC, "US_Registration", "CreditHours" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         TotalCredits = TotalCredits + dTempDecimal_0;
      } 

      RESULT = SetCursorNextEntity( mStudenC, "US_Registration", "" );
      //:END 
   } 

   //:END
   //:CREATE ENTITY mStudenC.US_Registration
   RESULT = CreateEntity( mStudenC, "US_Registration", zPOS_AFTER );
   //:CREATE ENTITY mStudenC.US_Registration
   RESULT = CreateEntity( mStudenC, "US_Registration", zPOS_AFTER );
   //:mStudenC.US_Registration.CreditHours = TotalCredits
   SetAttributeFromDecimal( mStudenC, "US_Registration", "CreditHours", TotalCredits );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dStuPersonFullName( VIEW mStudenC BASED ON LOD mStudenC,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   VIEW mStudent    BASED ON LOD mStudent
public int 
omStudenC_dStuPersonFullName( View     mStudenC,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   zVIEW    mStudent = new zVIEW( );
   //:VIEW lStuPSchLST BASED ON LOD lStuPSch
   zVIEW    lStuPSchLST = new zVIEW( );
   //:STRING ( 50 ) szStudentName
   String   szStudentName = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// This is the Person Name for the Student from either the mStudent view or the lStuPSchLST view.
         //:GET VIEW mStudent NAMED "mStudent"
         RESULT = GetViewByName( mStudent, "mStudent", mStudenC, zLEVEL_TASK );
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 
            //:szStudentName = mStudent.Person.dFullName 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szStudentName;
            if ( szStudentName == null )
               sb_szStudentName = new StringBuilder( 32 );
            else
               sb_szStudentName = new StringBuilder( szStudentName );
                         GetVariableFromAttribute( sb_szStudentName, mi_lTempInteger_0, 'S', 51, mStudent, "Person", "dFullName", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szStudentName = sb_szStudentName.toString( );}
            //:ELSE
         } 
         else
         { 
            //:GET VIEW lStuPSchLST NAMED "lStuPSchLST"
            RESULT = GetViewByName( lStuPSchLST, "lStuPSchLST", mStudenC, zLEVEL_TASK );
            //:IF RESULT >= 0 
            if ( RESULT >= 0 )
            { 
               //:szStudentName = lStuPSchLST.Person.dFullName 
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_szStudentName;
               if ( szStudentName == null )
                  sb_szStudentName = new StringBuilder( 32 );
               else
                  sb_szStudentName = new StringBuilder( szStudentName );
                               GetVariableFromAttribute( sb_szStudentName, mi_lTempInteger_1, 'S', 51, lStuPSchLST, "Person", "dFullName", "", 0 );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );
               szStudentName = sb_szStudentName.toString( );}
            } 

            //:END
         } 

         //:END

         //:StoreStringInRecord ( mStudenC,
         //:                   InternalEntityStructure, InternalAttribStructure, szStudentName )
         StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szStudentName );
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
//:dSurveyName( VIEW mStudenC BASED ON LOD mStudenC,
//:             STRING ( 32 ) InternalEntityStructure,
//:             STRING ( 32 ) InternalAttribStructure,
//:             SHORT GetOrSetFlag )
//:             
//:   VIEW  wXferO REGISTERED AS wXferO
public int 
omStudenC_dSurveyName( View     mStudenC,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 10 ) szCurrentDate
   String   szCurrentDate = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;

   RESULT = GetViewByName( wXferO, "wXferO", mStudenC, zLEVEL_TASK );

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :



         //:wXferO.Root.WorkDate  = mStudenC.CurrentClass.ClassEndDate  
         SetAttributeFromAttribute( wXferO, "Root", "WorkDate", mStudenC, "CurrentClass", "ClassEndDate" );
         //:szCurrentDate = wXferO.Root.dCurrentDate 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szCurrentDate;
         if ( szCurrentDate == null )
            sb_szCurrentDate = new StringBuilder( 32 );
         else
            sb_szCurrentDate = new StringBuilder( szCurrentDate );
                   GetVariableFromAttribute( sb_szCurrentDate, mi_lTempInteger_0, 'S', 11, wXferO, "Root", "dCurrentDate", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szCurrentDate = sb_szCurrentDate.toString( );}
         //:AddDaysToDate( wXferO, "Root", "WorkDate", -7 ) 
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
          m_ZGLOBAL1_Operation.AddDaysToDate( wXferO, "Root", "WorkDate", -7 );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }

         //:// If the student hasn't taken the survey and if the student has not finished the survey, then display the name.
         //:IF ( mStudenC.StudentSurvey DOES NOT EXIST OR ( mStudenC.StudentSurvey EXISTS AND mStudenC.StudentSurvey.DateTimeSubmitted = "" ) ) 
         lTempInteger_1 = CheckExistenceOfEntity( mStudenC, "StudentSurvey" );
         lTempInteger_2 = CheckExistenceOfEntity( mStudenC, "StudentSurvey" );
         if ( lTempInteger_1 != 0 || ( lTempInteger_2 == 0 && CompareAttributeToString( mStudenC, "StudentSurvey", "DateTimeSubmitted", "" ) == 0 ) )
         { 
            //://AND szCurrentDate >= wXferO.Root.WorkDate
            //:StoreStringInRecord ( mStudenC,
            //:                   InternalEntityStructure, InternalAttribStructure, mStudenC.SurveyQuestionaire.Name )
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, mStudenC, "SurveyQuestionaire", "Name" );
            szTempString_0 = sb_szTempString_0.toString( );}
            StoreStringInRecord( mStudenC, InternalEntityStructure, InternalAttribStructure, szTempString_0 );
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
