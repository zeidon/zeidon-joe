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

/**
 * @author QuinSoft
 *
 */

public class mSAProf_Object extends VmlObjectOperations
{
   public mSAProf_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dChargeOrCredit( VIEW mSAProf BASED ON LOD mSAProf,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )

//:   STRING( 2 ) szCredit
public int 
omSAProf_dChargeOrCredit( View     mSAProf,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   String   szCredit = null;


   //:szCredit = ""
    {StringBuilder sb_szCredit;
   if ( szCredit == null )
      sb_szCredit = new StringBuilder( 32 );
   else
      sb_szCredit = new StringBuilder( szCredit );
      ZeidonStringCopy( sb_szCredit, 1, 0, "", 1, 0, 3 );
   szCredit = sb_szCredit.toString( );}
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:IF mSAProf.StudentAccountTransApplied.Amount < 0
         if ( CompareAttributeToInteger( mSAProf, "StudentAccountTransApplied", "Amount", 0 ) < 0 )
         { 
            //:szCredit = "CR"
             {StringBuilder sb_szCredit;
            if ( szCredit == null )
               sb_szCredit = new StringBuilder( 32 );
            else
               sb_szCredit = new StringBuilder( szCredit );
                        ZeidonStringCopy( sb_szCredit, 1, 0, "CR", 1, 0, 3 );
            szCredit = sb_szCredit.toString( );}
         } 

         //:END 
         //:StoreStringInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, szCredit )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szCredit );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


private int 
omSAProf_fnLocalBuildQual_3( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_4( View     vSubtask,
                             zVIEW    vQualObject,
                             int      nChargeID,
                             int      nTermID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SATransactionCode" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nChargeID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SATransactionTerm" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nTermID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_5( View     vSubtask,
                             zVIEW    vQualObject,
                             int      nChargeID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SATransactionCode" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nChargeID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_6( View     vSubtask,
                             zVIEW    vQualObject,
                             String   szDate )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "BatchEditList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "BatchEditList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "BatchEditDate" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDate.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "BatchEditList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Posted" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_7( View     vSubtask,
                             zVIEW    vQualObject,
                             String   szDate )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "BatchEditList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "BatchEditList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "BatchEditDate" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDate.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "BatchEditList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Posted" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_8( View     vSubtask,
                             zVIEW    vQualObject,
                             int      nTermID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeTerm" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nTermID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_9( View     vSubtask,
                             zVIEW    vQualObject,
                             int      nChargeID,
                             int      nTermID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SATransactionCode" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nChargeID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SATransactionTerm" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nTermID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_10( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SATransactionCode" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "BillType" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "T" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Program" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_11( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SATransactionCode" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "BillType" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "L" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Program" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_12( View     vSubtask,
                              zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SATransactionCode" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Description" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Directed Study Fee" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_13( View     vSubtask,
                              zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SATransactionCode" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Description" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Corporate Discount" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ActiveFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_14( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_4 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountRuleSet" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountRuleSet" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_4 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_15( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_12 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountRuleSet" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountRuleSet" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_12 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_16( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_20 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountRuleSet" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountRuleSet" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_20 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_17( View     vSubtask,
                              zVIEW    vQualObject,
                              int      nCollegeYear )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeYear" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nCollegeYear );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_18( View     vSubtask,
                              zVIEW    vQualObject,
                              int      nCollegeYear )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeYear" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nCollegeYear );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_19( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0,
                              int      CollegeTermID )
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Registration" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "RegistrationCollegeTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", CollegeTermID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "RegisteredCollegeTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", CollegeTermID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentWaitlisted" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentWaitlistedCollegeTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", CollegeTermID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_20( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SASubAccount" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SASubAccount" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_21( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountTransApplied" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountTransApplied" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_22( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0,
                              int      lTempInteger_1,
                              int      nCollegeYearID,
                              String   szTempString_0,
                              String   szTempString_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ProfileAdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidCollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nCollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Enrolled" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Enrolled" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "TermOfResidence" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "TermOfResidence" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountTransApplied" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountTransApplied" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "TermOfMealPlan" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "TermOfMealPlan" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "BeginDate" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ">=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "TermOfMealPlan" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "BeginDate" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_1.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "<=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "BillingPeriod" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PeriodCollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nCollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "BillingPeriod" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Undated" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_23( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SATransactionCode" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "TransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "CASHDISC" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SATransactionTerm" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_24( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountTransApplied" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "BillingPeriod" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "EligibleForCashDiscount" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_25( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_1,
                              int      lTempInteger_2,
                              int      lTempInteger_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountProfile" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountTransApplied" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "TransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "CASHDISC" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AppliedBillingPeriodTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "BillingPeriod" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "BillingPeriod" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PeriodTransApplied" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PeriodTransApplied" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "TermOfMealPlan" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "TermOfMealPlan" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SASubAccount" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SASubAccount" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountHistoricalProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountHistoricalProfile" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_26( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_1,
                              int      lTempInteger_2,
                              int      lTempInteger_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountProfile" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountTransApplied" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "TransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "CASHDISC" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AppliedBillingPeriodTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "BillingPeriod" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "BillingPeriod" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PeriodTransApplied" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PeriodTransApplied" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "TermOfMealPlan" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "TermOfMealPlan" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SASubAccount" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SASubAccount" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountHistoricalProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountHistoricalProfile" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_0( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_0,
                             int      lTempInteger_1,
                             int      nCollegeYearID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ProfileAdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidCollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nCollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Enrolled" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "EnrollmentCollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nCollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "RegisteredCollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nCollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Cohort" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "BillingPeriod" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PeriodCollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nCollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "BillingPeriod" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Undated" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_1( View     vSubtask,
                             zVIEW    vQualObject,
                             int      nSAProfileID,
                             String   szTempString_0,
                             String   szTempString_1,
                             int      nCollegeYearID,
                             int      nCollegeTermID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountProfile" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nSAProfileID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountTransApplied" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AppliedBillingPeriod" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "BeginDate" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ">=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AppliedBillingPeriod" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "BeginDate" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_1.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "<=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidCollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nCollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Enrolled" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "EnrollmentCollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "BillingPeriod" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PeriodCollegeTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nCollegeTermID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_2( View     vSubtask,
                             zVIEW    vQualObject,
                             int      nCollegeYearID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeYear" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nCollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DERIVED ATTRIBUTE OPERATION
//:dTotalPerPeriodRegApp( VIEW mSAProf BASED ON LOD mSAProf,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   VIEW mFAProf  BASED ON LOD mFAProf
public int 
omSAProf_dTotalPerPeriodRegApp( View     mSAProf,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   zVIEW    mFAProf = new zVIEW( );
   //:VIEW mFAProf2 BASED ON LOD mFAProf
   zVIEW    mFAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// This is same as dRegistrationDisbAmountApproved attribute in mFAProf.
         //:dAmount = 0
         dAmount = 0;
         //:GET VIEW mFAProf NAMED "mFAProf"
         RESULT = GetViewByName( mFAProf, "mFAProf", mSAProf, zLEVEL_TASK );
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 
            //:CreateViewFromView( mFAProf2, mFAProf )
            CreateViewFromView( mFAProf2, mFAProf );
            //:NAME VIEW mFAProf2 "mFAProf2"
            SetNameForView( mFAProf2, "mFAProf2", null, zLEVEL_TASK );
            //:SET CURSOR FIRST mFAProf2.CollegeTerm WITHIN mFAProf2.FinAidProfile 
            //:           WHERE mFAProf2.CollegeTerm.ID = mSAProf.PeriodCollegeTerm.ID
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf, "PeriodCollegeTerm", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = mFAProf2.cursor( "CollegeTerm" ).setFirst( "ID", lTempInteger_0, "FinAidProfile" ).toInt();
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:dAmount = mFAProf2.PerProfileFinAidAwardPeriod.dRegistrationDisbAmountApproved
               {MutableDouble md_dAmount = new MutableDouble( dAmount );
                               GetDecimalFromAttribute( md_dAmount, mFAProf2, "PerProfileFinAidAwardPeriod", "dRegistrationDisbAmountApproved" );
               dAmount = md_dAmount.doubleValue( );}
            } 

            //:END
            //:DropView( mFAProf2 )
            DropView( mFAProf2 );
         } 

         //:END

         //:StoreValueInRecord( mSAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:     /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dPotentialCashDiscount( VIEW mSAProf BASED ON LOD mSAProf,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   VIEW mSAChrgT BASED ON LOD mSAChrgT
public int 
omSAProf_dPotentialCashDiscount( View     mSAProf,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   zVIEW    mSAChrgT = new zVIEW( );
   //:VIEW lSATrnSh BASED ON LOD lSATrnSh
   zVIEW    lSATrnSh = new zVIEW( );
   //:DECIMAL dDiscountRate 
   double  dDiscountRate = 0.0;
   //:DECIMAL dCashDiscount
   double  dCashDiscount = 0.0;
   //:DECIMAL dTotalEligibleCharges
   double  dTotalEligibleCharges = 0.0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Determine the potential Cash Discount by adding up those charges for the Period that qualify
         //:// and multiplying them by the Discount Rate for the Term.
         //:// If no Discount Rate exists, don't compute a Cash Discount.

         //:// Don't calculate if it already exists.
         //:IF mSAProf.BillingPeriod.wPotentialCashDiscount != "" 
         if ( CompareAttributeToString( mSAProf, "BillingPeriod", "wPotentialCashDiscount", "" ) != 0 )
         { 
            //:dCashDiscount = mSAProf.BillingPeriod.wPotentialCashDiscount 
            {MutableDouble md_dCashDiscount = new MutableDouble( dCashDiscount );
                         GetDecimalFromAttribute( md_dCashDiscount, mSAProf, "BillingPeriod", "wPotentialCashDiscount" );
            dCashDiscount = md_dCashDiscount.doubleValue( );}
            //:ELSE
         } 
         else
         { 
            //:// Activate the Discount Rate for the Term.
            //:ACTIVATE mSAChrgT WHERE mSAChrgT.SATransactionCode.TransactionCode = "CASHDISC"
            //:     RESTRICTING mSAChrgT.SATransactionTerm TO mSAChrgT.CollegeTerm.ID = mSAProf.PeriodCollegeTerm.ID 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf, "PeriodCollegeTerm", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            omSAProf_fnLocalBuildQual_23( mSAProf, vTempViewVar_0, lTempInteger_0 );
            RESULT = ActivateObjectInstance( mSAChrgT, "mSAChrgT", mSAProf, vTempViewVar_0, zSINGLE );
            DropView( vTempViewVar_0 );
            //:NAME VIEW mSAChrgT "mSAChrgTDiscount"
            SetNameForView( mSAChrgT, "mSAChrgTDiscount", null, zLEVEL_TASK );
            //:IF mSAChrgT.SATransactionTerm DOES NOT EXIST
            lTempInteger_1 = CheckExistenceOfEntity( mSAChrgT, "SATransactionTerm" );
            if ( lTempInteger_1 != 0 )
            { 
               //:DropObjectInstance( mSAChrgT )
               DropObjectInstance( mSAChrgT );
               //:RETURN -1
               if(8==8)return( -1 );
            } 

            //:END
            //:dDiscountRate = mSAChrgT.SATransactionTerm.Amount
            {MutableDouble md_dDiscountRate = new MutableDouble( dDiscountRate );
                         GetDecimalFromAttribute( md_dDiscountRate, mSAChrgT, "SATransactionTerm", "Amount" );
            dDiscountRate = md_dDiscountRate.doubleValue( );}

            //:// Activate list of Transactions for the current BillingPeriod that are eligible for a discount.
            //:ACTIVATE lSATrnSh Multiple WHERE lSATrnSh.BillingPeriod.ID = mSAProf.BillingPeriod.ID 
            //:                             AND lSATrnSh.SATransactionCode.EligibleForCashDiscount = "Y"
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, mSAProf, "BillingPeriod", "ID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            omSAProf_fnLocalBuildQual_24( mSAProf, vTempViewVar_1, lTempInteger_2 );
            RESULT = ActivateObjectInstance( lSATrnSh, "lSATrnSh", mSAProf, vTempViewVar_1, zMULTIPLE );
            DropView( vTempViewVar_1 );
            //:NAME VIEW lSATrnSh "lSATrnSh"
            SetNameForView( lSATrnSh, "lSATrnSh", null, zLEVEL_TASK );
            //:dTotalEligibleCharges = 0
            dTotalEligibleCharges = 0;
            //:FOR EACH lSATrnSh.StudentAccountTransApplied 
            RESULT = lSATrnSh.cursor( "StudentAccountTransApplied" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:dTotalEligibleCharges = dTotalEligibleCharges + lSATrnSh.StudentAccountTransApplied.Amount 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, lSATrnSh, "StudentAccountTransApplied", "Amount" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dTotalEligibleCharges = dTotalEligibleCharges + dTempDecimal_0;
               RESULT = lSATrnSh.cursor( "StudentAccountTransApplied" ).setNextContinue().toInt();;
            } 

            //:END
            //:            
            //:// Cash Discount is (Total Eligible Transactions - Approved Aid) * .03
            //:dCashDiscount = ( dTotalEligibleCharges - mSAProf.BillingPeriod.dRegistrationDisbAmountApproved ) * dDiscountRate
            {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                         GetDecimalFromAttribute( md_dTempDecimal_1, mSAProf, "BillingPeriod", "dRegistrationDisbAmountApproved" );
            dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
            dCashDiscount = ( dTotalEligibleCharges - dTempDecimal_1 ) * dDiscountRate;
            //:IF dCashDiscount < 0
            if ( dCashDiscount < 0 )
            { 
               //:dCashDiscount = 0
               dCashDiscount = 0;
            } 

            //:END

            //:// Save it in case it is recomputed.
            //:mSAProf.BillingPeriod.wPotentialCashDiscount = dCashDiscount
            SetAttributeFromDecimal( mSAProf, "BillingPeriod", "wPotentialCashDiscount", dCashDiscount );

            //:DropObjectInstance( lSATrnSh )
            DropObjectInstance( lSATrnSh );
            //:DropObjectInstance( mSAChrgT )
            DropObjectInstance( mSAChrgT );
         } 

         //:END

         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dCashDiscount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dCashDiscount, 0 );
         break ;
      //:   
      //:   /* end zDERIVED_GET */

      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

   //:   /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dTermCreditsNoCashD( VIEW mSAProf BASED ON LOD mSAProf,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   VIEW  mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dTermCreditsNoCashD( View     mSAProf,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );

         //:dAmount = 0
         dAmount = 0;
         //:// Sum PeriodTransApplied where they credits, not charges.
         //:// Do not include the cash discount transaction.
         //:FOR EACH mSAProf2.PeriodTransApplied WHERE mSAProf2.PeriodSATransactionCode.Type = "R" 
         RESULT = mSAProf2.cursor( "PeriodTransApplied" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( mSAProf2, "PeriodSATransactionCode", "Type", "R" ) == 0 )
            { 
               //:IF mSAProf2.PeriodSATransactionCode.TransactionCode != "CASHDISC" 
               if ( CompareAttributeToString( mSAProf2, "PeriodSATransactionCode", "TransactionCode", "CASHDISC" ) != 0 )
               { 
                  //:dAmount = dAmount + mSAProf2.PeriodTransApplied.Amount 
                  {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                     GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "PeriodTransApplied", "Amount" );
                  dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                  dAmount = dAmount + dTempDecimal_0;
               } 

            } 

            RESULT = mSAProf2.cursor( "PeriodTransApplied" ).setNextContinue().toInt();;
            //:END
         } 

         //:END 
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dTermCredits( VIEW mSAProf BASED ON LOD mSAProf,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )

//:   VIEW  mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dTermCredits( View     mSAProf,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );

         //:dAmount = 0
         dAmount = 0;
         //:// Sum PeriodTransApplied where they credits, not charges.
         //:FOR EACH mSAProf2.PeriodTransApplied WHERE mSAProf2.PeriodSATransactionCode.Type = "R" 
         RESULT = mSAProf2.cursor( "PeriodTransApplied" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( mSAProf2, "PeriodSATransactionCode", "Type", "R" ) == 0 )
            { 
               //://WHERE mSAProf2.StudentAccountTransApplied.Amount > 0
               //:dAmount = dAmount + mSAProf2.PeriodTransApplied.Amount 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "PeriodTransApplied", "Amount" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dAmount = dAmount + dTempDecimal_0;
            } 

            RESULT = mSAProf2.cursor( "PeriodTransApplied" ).setNextContinue().toInt();;
         } 

         //:END 
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dTermCharges( VIEW mSAProf BASED ON LOD mSAProf,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )

//:   VIEW  mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dTermCharges( View     mSAProf,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );

         //:dAmount = 0
         dAmount = 0;
         //:// Sum PeriodTransApplied where they are not credits, only charges.
         //:FOR EACH mSAProf2.PeriodTransApplied WHERE mSAProf2.PeriodSATransactionCode.Type = "C" 
         RESULT = mSAProf2.cursor( "PeriodTransApplied" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( mSAProf2, "PeriodSATransactionCode", "Type", "C" ) == 0 )
            { 
               //://WHERE mSAProf2.StudentAccountTransApplied.Amount > 0
               //:dAmount = dAmount + mSAProf2.PeriodTransApplied.Amount 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "PeriodTransApplied", "Amount" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dAmount = dAmount + dTempDecimal_0;
            } 

            RESULT = mSAProf2.cursor( "PeriodTransApplied" ).setNextContinue().toInt();;
         } 

         //:END 
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildCashDiscountTran( VIEW mSAProf BASED ON LOD mSAProf )

//:   VIEW mSAProfT BASED ON LOD mSAProf
public int 
omSAProf_BuildCashDiscountTran( View     mSAProf )
{
   zVIEW    mSAProfT = new zVIEW( );
   //:DECIMAL CashDiscountAmount
   double  CashDiscountAmount = 0.0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;


   //:// Generate a Cash Discount SA Transaction for the College Term identified. Replace any current
   //:// Cash Discount SA Transaction for the Term, unless it has been posted.

   //:IF mSAProf.BillingPeriod DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "BillingPeriod" );
   if ( lTempInteger_0 != 0 )
   { 
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Determine Cash Discount. Note the amount is made negative here for generation of the SA Transaction.
   //:CashDiscountAmount = 0 - mSAProf.BillingPeriod.dPotentialCashDiscount
   {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
       GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf, "BillingPeriod", "dPotentialCashDiscount" );
   dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
   CashDiscountAmount = 0 - dTempDecimal_0;

   //:// Get new mSAProf for creating CASHDISC Transaction.
   //:ACTIVATE mSAProfT WHERE mSAProfT.StudentAccountProfile.ID = mSAProf.StudentAccountProfile.ID 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mSAProf, "StudentAccountProfile", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   //:       RESTRICTING mSAProfT.StudentAccountTransApplied      TO mSAProfT.SATransactionCode.TransactionCode = "CASHDISC" AND 
   //:                                                               mSAProfT.AppliedBillingPeriodTerm.ID = mSAProf.PeriodCollegeTerm.ID 
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mSAProf, "PeriodCollegeTerm", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   //:       RESTRICTING mSAProfT.BillingPeriod                   TO mSAProfT.BillingPeriod.ID = mSAProf.BillingPeriod.ID 
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, mSAProf, "BillingPeriod", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   //:       RESTRICTING mSAProf.PeriodTransApplied               TO mSAProf.PeriodTransApplied.ID = 0
   //:       RESTRICTING mSAProfT.Student                         TO mSAProfT.Student.ID = 0
   //:       RESTRICTING mSAProfT.TermOfMealPlan                  TO mSAProfT.TermOfMealPlan.ID = 0
   //:       RESTRICTING mSAProfT.SASubAccount                    TO mSAProfT.SASubAccount.ID = 0
   //:       RESTRICTING mSAProfT.StudentAccountHistoricalProfile TO mSAProfT.StudentAccountHistoricalProfile.ID = 0
   omSAProf_fnLocalBuildQual_25( mSAProf, vTempViewVar_0, lTempInteger_1, lTempInteger_2, lTempInteger_3 );
   RESULT = ActivateObjectInstance( mSAProfT, "mSAProf", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSAProfT "mSAProfDiscount"
   SetNameForView( mSAProfT, "mSAProfDiscount", null, zLEVEL_TASK );

   //:// Check if existing discount exists. 
   //:// If it does and is for the same amount, do nothing.
   //:// If it does but is for a different amount, delete it.

   //:IF mSAProfT.StudentAccountTransApplied EXISTS 
   lTempInteger_4 = CheckExistenceOfEntity( mSAProfT, "StudentAccountTransApplied" );
   if ( lTempInteger_4 == 0 )
   { 
      //:IF mSAProfT.StudentAccountTransApplied.Amount = CashDiscountAmount
      if ( CompareAttributeToDecimal( mSAProfT, "StudentAccountTransApplied", "Amount", CashDiscountAmount ) == 0 )
      { 
         //:RETURN 
         if(8==8)return( 0 );
         //:ELSE 
      } 
      else
      { 
         //:IF mSAProfT.StudentAccountTransApplied.DatePosted != ""
         if ( CompareAttributeToString( mSAProfT, "StudentAccountTransApplied", "DatePosted", "" ) != 0 )
         { 
            //:RETURN 
            if(8==8)return( 0 );
            //:ELSE
         } 
         else
         { 
            //:DELETE ENTITY mSAProfT.StudentAccountTransApplied 
            RESULT = DeleteEntity( mSAProfT, "StudentAccountTransApplied", zPOS_NEXT );
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:// Generate Cash Discount Transaction.
   //:nRC = GenerateUpdateCharge( mSAProfT, 
   //:                            mSAProfT.PeriodCollegeTerm.ID,
   //:                            3152,   // CASHDISC TransactionCode ID
   //:                            CashDiscountAmount, "", "", "" )
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
       GetIntegerFromAttribute( mi_lTempInteger_5, mSAProfT, "PeriodCollegeTerm", "ID" );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );}
   nRC = omSAProf_GenerateUpdateCharge( mSAProfT, lTempInteger_5, 3152, CashDiscountAmount, "", "", "" );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:AcceptSubobject( mSAProfT, "StudentAccountTransApplied" )
      AcceptSubobject( mSAProfT, "StudentAccountTransApplied" );
   } 

   //:END

   //:COMMIT mSAProfT
   RESULT = CommitObjectInstance( mSAProfT );
   //:DropObjectInstance( mSAProfT )
   DropObjectInstance( mSAProfT );
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:DeleteCashDiscountTran( VIEW mSAProf BASED ON LOD mSAProf )


//:   VIEW mSAProfT BASED ON LOD mSAProf
public int 
omSAProf_DeleteCashDiscountTran( View     mSAProf )
{
   zVIEW    mSAProfT = new zVIEW( );
   //:DECIMAL CashDiscountAmount
   double  CashDiscountAmount = 0.0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_4 = 0;


   //:// Generate a Cash Discount SA Transaction for the College Term identified. Replace any current
   //:// Cash Discount SA Transaction for the Term, unless it has been posted.

   //:IF mSAProf.BillingPeriod DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "BillingPeriod" );
   if ( lTempInteger_0 != 0 )
   { 
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Get new mSAProf for creating CASHDISC Transaction.
   //:ACTIVATE mSAProfT WHERE mSAProfT.StudentAccountProfile.ID = mSAProf.StudentAccountProfile.ID 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mSAProf, "StudentAccountProfile", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   //:       RESTRICTING mSAProfT.StudentAccountTransApplied      TO mSAProfT.SATransactionCode.TransactionCode = "CASHDISC" AND 
   //:                                                               mSAProfT.AppliedBillingPeriodTerm.ID = mSAProf.PeriodCollegeTerm.ID 
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mSAProf, "PeriodCollegeTerm", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   //:       RESTRICTING mSAProfT.BillingPeriod                   TO mSAProfT.BillingPeriod.ID = mSAProf.BillingPeriod.ID 
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, mSAProf, "BillingPeriod", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   //:       RESTRICTING mSAProf.PeriodTransApplied               TO mSAProf.PeriodTransApplied.ID = 0
   //:       RESTRICTING mSAProfT.Student                         TO mSAProfT.Student.ID = 0
   //:       RESTRICTING mSAProfT.TermOfMealPlan                  TO mSAProfT.TermOfMealPlan.ID = 0
   //:       RESTRICTING mSAProfT.SASubAccount                    TO mSAProfT.SASubAccount.ID = 0
   //:       RESTRICTING mSAProfT.StudentAccountHistoricalProfile TO mSAProfT.StudentAccountHistoricalProfile.ID = 0
   omSAProf_fnLocalBuildQual_26( mSAProf, vTempViewVar_0, lTempInteger_1, lTempInteger_2, lTempInteger_3 );
   RESULT = ActivateObjectInstance( mSAProfT, "mSAProf", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSAProfT "mSAProfDiscount"
   SetNameForView( mSAProfT, "mSAProfDiscount", null, zLEVEL_TASK );

   //:// Check if existing discount exists. 
   //:// If it does and is for the same amount, do nothing.
   //:// If it does but is for a different amount, delete it.

   //:IF mSAProfT.StudentAccountTransApplied EXISTS 
   lTempInteger_4 = CheckExistenceOfEntity( mSAProfT, "StudentAccountTransApplied" );
   if ( lTempInteger_4 == 0 )
   { 
      //:IF mSAProfT.StudentAccountTransApplied.DatePosted != ""
      if ( CompareAttributeToString( mSAProfT, "StudentAccountTransApplied", "DatePosted", "" ) != 0 )
      { 
         //:RETURN 
         if(8==8)return( 0 );
         //:ELSE
      } 
      else
      { 
         //:DELETE ENTITY mSAProfT.StudentAccountTransApplied 
         RESULT = DeleteEntity( mSAProfT, "StudentAccountTransApplied", zPOS_NEXT );
      } 

      //:END
   } 

   //:END

   //:COMMIT mSAProfT
   RESULT = CommitObjectInstance( mSAProfT );
   //:DropObjectInstance( mSAProfT )
   DropObjectInstance( mSAProfT );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dTermBalance( VIEW mSAProf BASED ON LOD mSAProf,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )

//:   VIEW  mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dTermBalance( View     mSAProf,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   //:DECIMAL dBalance
   double  dBalance = 0.0;
   //:DECIMAL dTotalCharges
   double  dTotalCharges = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:dBalance = mSAProf.StudentAccountProfile.dBalance 
         {MutableDouble md_dBalance = new MutableDouble( dBalance );
                   GetDecimalFromAttribute( md_dBalance, mSAProf, "StudentAccountProfile", "dBalance" );
         dBalance = md_dBalance.doubleValue( );}
         //:// This derived attribute will only work when all the transactions under
         //:// billing period exist.  When doing Finincial Registration, we first get all
         //:// PeriodTransApplied but then we delete ones that are not charges.  We must
         //:// get dTermBalance before deleting any PeriodTransApplied.
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:dTotalCharges = 0
         dTotalCharges = 0;
         //:FOR EACH mSAProf2.PeriodTransApplied  
         RESULT = mSAProf2.cursor( "PeriodTransApplied" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:dTotalCharges = dTotalCharges + mSAProf2.PeriodTransApplied.Amount 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "PeriodTransApplied", "Amount" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dTotalCharges = dTotalCharges + dTempDecimal_0;
            RESULT = mSAProf2.cursor( "PeriodTransApplied" ).setNextContinue().toInt();;
         } 

         //:END 

         //:// The amount for the term is the total of the student's transactions minus
         //:// any charges/credits for the current term.
         //:dAmount = dBalance - dTotalCharges
         dAmount = dBalance - dTotalCharges;
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:   /* end zDERIVED_GET */

      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

   //:   /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
//    
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dPaymentLineDesc( VIEW mSAProf BASED ON LOD mSAProf,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )
//:                  
//:   STRING ( 20 ) strDueDate
public int 
omSAProf_dPaymentLineDesc( View     mSAProf,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   String   strDueDate = null;
   //:STRING ( 50 ) szText
   String   szText = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetStrFromAttrByContext( strDueDate, 20, mSAProf, "PaymentPlanLineAssigned", "DueDate", "" )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
          {StringBuilder sb_strDueDate;
         if ( strDueDate == null )
            sb_strDueDate = new StringBuilder( 32 );
         else
            sb_strDueDate = new StringBuilder( strDueDate );
                   m_ZGLOBAL1_Operation.GetStrFromAttrByContext( sb_strDueDate, 20, mSAProf, "PaymentPlanLineAssigned", "DueDate", "" );
         strDueDate = sb_strDueDate.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF strDueDate = ""
         if ( ZeidonStringCompare( strDueDate, 1, 0, "", 1, 0, 21 ) == 0 )
         { 
            //:szText = "Due by Registration"
             {StringBuilder sb_szText;
            if ( szText == null )
               sb_szText = new StringBuilder( 32 );
            else
               sb_szText = new StringBuilder( szText );
                        ZeidonStringCopy( sb_szText, 1, 0, "Due by Registration", 1, 0, 51 );
            szText = sb_szText.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szText = "Due by " + strDueDate
             {StringBuilder sb_szText;
            if ( szText == null )
               sb_szText = new StringBuilder( 32 );
            else
               sb_szText = new StringBuilder( szText );
                        ZeidonStringCopy( sb_szText, 1, 0, "Due by ", 1, 0, 51 );
            szText = sb_szText.toString( );}
             {StringBuilder sb_szText;
            if ( szText == null )
               sb_szText = new StringBuilder( 32 );
            else
               sb_szText = new StringBuilder( szText );
                        ZeidonStringConcat( sb_szText, 1, 0, strDueDate, 1, 0, 51 );
            szText = sb_szText.toString( );}
         } 

         //:END
         //:StoreStringInRecord ( mSAProf, InternalEntityStructure, InternalAttribStructure, szText )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szText );
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
//:RemoveChargesForTerm( VIEW mSAProf BASED ON LOD mSAProf,
//:                      INTEGER CollegeTermID )

//:   VIEW dSATrans BASED ON LOD  dSATrans
public int 
omSAProf_RemoveChargesForTerm( View     mSAProf,
                               int      CollegeTermID )
{
   zVIEW    dSATrans = new zVIEW( );
   //:STRING ( 200 ) szMsg
   String   szMsg = null;

   return( 0 );
//    // Remove the current charges for the College Term, except for those identified as not generatable.
//    /*FOR EACH mSAProf.AppliedBillingPeriodTerm WITHIN mSAProf.StudentAccountProfile
//       IF mSAProf.AppliedBillingPeriodTerm.ID = CollegeTermID
//       
//          // Only delete transactions where ....
//          IF mSAProf.SATransactionCode.ClearOnRegenerateChargesFlag = "Y"
//          
//             // Skip Removing posted entries.
//             IF mSAProf.StudentAccountTransApplied.DatePosted = "" 
//                
//                ACTIVATE dSATrans WHERE dSATrans.StudentAccountTransApplied.ID = mSAProf.StudentAccountTransApplied.ID 
//                NAME VIEW dSATrans "dSATrans"
//                IF //dSATrans.Class EXISTS OR
//                   dSATrans.DepositGrouping EXISTS OR
//                   dSATrans.SASubAccountItem EXISTS OR
//                   dSATrans.APBillPayment EXISTS OR
//                   dSATrans.GLJournalEntry EXISTS OR
//                   dSATrans.Vendor EXISTS
//                   
//                   szMsg = "Transaction, " + dSATrans.StudentAccountTransApplied.Description + ", is related to other data and cannot be deleted."
//                   MessageSend( mSAProf, "", "Delete Transactions", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//                ELSE
//                   CREATE ENTITY mSAProf.SATransAppliedDeletedEntry 
//                   mSAProf.SATransAppliedDeletedEntry.SATransAppliedID = mSAProf.StudentAccountTransApplied.ID  
//                   SET CURSOR FIRST mSAProf.PeriodTransApplied WITHIN mSAProf.StudentAccountProfile 
//                              WHERE mSAProf.PeriodTransApplied.ID = mSAProf.StudentAccountTransApplied.ID 
//                   IF RESULT >= zCURSOR_SET 
//                      DropEntity( mSAProf, "PeriodTransApplied", zREPOS_NONE )
//                   END 
//                   DropEntity( mSAProf, "StudentAccountTransApplied", zREPOS_NONE )
//                END
//                
//                DropObjectInstance( dSATrans )
//             END
//          END
//       END
//    END*/
// END
} 


//:TRANSFORMATION OPERATION
//:ProcessDeletes( VIEW mSAProf BASED ON LOD mSAProf )

//:   VIEW dSATrans  BASED ON LOD dSATrans
public int 
omSAProf_ProcessDeletes( View     mSAProf )
{
   zVIEW    dSATrans = new zVIEW( );
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;


   //:// Process any Transaction deletes as separate commits.
   //:FOR EACH mSAProf.SATransAppliedDeletedEntry 
   RESULT = mSAProf.cursor( "SATransAppliedDeletedEntry" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:ACTIVATE dSATrans WHERE dSATrans.StudentAccountTransApplied.ID = mSAProf.SATransAppliedDeletedEntry.SATransAppliedID
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf, "SATransAppliedDeletedEntry", "SATransAppliedID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      omSAProf_fnLocalBuildQual_21( mSAProf, vTempViewVar_0, lTempInteger_0 );
      RESULT = ActivateObjectInstance( dSATrans, "dSATrans", mSAProf, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW dSATrans "dSATrans"
      SetNameForView( dSATrans, "dSATrans", null, zLEVEL_TASK );

      //:// Give error message if a Disbursement entity exists.
      //:IF dSATrans.FinAidAwardDisbursement EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( dSATrans, "FinAidAwardDisbursement" );
      if ( lTempInteger_1 == 0 )
      { 
         //:szMsg = "A Disbursement is tied to Transaction, '" + dSATrans.StudentAccountTransApplied.Description + 
         //:        "'. You will have to delete the Disbursement to delete this Transaction."
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 255, dSATrans, "StudentAccountTransApplied", "Description", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "A Disbursement is tied to Transaction, '", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
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
                  ZeidonStringConcat( sb_szMsg, 1, 0, "'. You will have to delete the Disbursement to delete this Transaction.", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
         //:MessageSend( mSAProf, "", "Save SA Profile", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mSAProf, "", "Save SA Profile", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:ELSE
      } 
      else
      { 
         //:DELETE ENTITY dSATrans.StudentAccountTransApplied 
         RESULT = DeleteEntity( dSATrans, "StudentAccountTransApplied", zPOS_NEXT );
         //:COMMIT dSATrans
         RESULT = CommitObjectInstance( dSATrans );
         //:DropObjectInstance( dSATrans ) 
         DropObjectInstance( dSATrans );
         //:DELETE ENTITY mSAProf.SATransAppliedDeletedEntry NONE 
         RESULT = DeleteEntity( mSAProf, "SATransAppliedDeletedEntry", zREPOS_NONE );
      } 

      RESULT = mSAProf.cursor( "SATransAppliedDeletedEntry" ).setNextContinue().toInt();;
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateSAProfForYearW( VIEW mSAProf BASED ON LOD mSAProf,
//:                        VIEW mSAStu BASED ON LOD mSAStu,
//:                        INTEGER nCollegeYearID,
//:                        INTEGER nCollegeTermID )

//:   VIEW mUser     BASED ON LOD  mUser
public int 
omSAProf_ActivateSAProfForYearW( zVIEW    mSAProf,
                                 View     mSAStu,
                                 int      nCollegeYearID,
                                 int      nCollegeTermID )
{
   zVIEW    mUser = new zVIEW( );
   //:VIEW lTermLST  REGISTERED AS lTermLST
   zVIEW    lTermLST = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lTermLSTT BASED ON LOD  lTermLST
   zVIEW    lTermLSTT = new zVIEW( );
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( lTermLST, "lTermLST", mSAStu, zLEVEL_TASK );

   //:GET VIEW mUser NAMED "mUser"
   RESULT = GetViewByName( mUser, "mUser", mSAStu, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:GetViewByName( mUser, "mUser", mSAStu, zLEVEL_APPLICATION )
      GetViewByName( mUser, "mUser", mSAStu, zLEVEL_APPLICATION );
   } 

   //:END

   //:CreateViewFromView( lTermLSTT, lTermLST )
   CreateViewFromView( lTermLSTT, lTermLST );
   //:// Position on the term we are looking at so that we can restrict the
   //:// meal plan based on that term.
   //:SET CURSOR FIRST lTermLSTT.CollegeTerm WHERE lTermLSTT.CollegeTerm.ID = nCollegeTermID  
   RESULT = lTermLSTT.cursor( "CollegeTerm" ).setFirst( "ID", nCollegeTermID ).toInt();

   //:ACTIVATE mSAProf 
   //:   WHERE mSAProf.Student.ID = mSAStu.Student.ID 
   //:     AND mSAProf.ProfileAdministrativeDivision.ID = mUser.CurrentAdministrativeDivision.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSAStu, "Student", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mUser, "CurrentAdministrativeDivision", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   //:   RESTRICTING mSAProf.FinAidProfile TO mSAProf.FinAidCollegeYear.ID = nCollegeYearID 
   //:   //RESTRICTING mSAProf.Student TO mSAProf.Student.ID = 0
   //:   RESTRICTING mSAProf.Person TO mSAProf.Person.ID = 0 
   //:   RESTRICTING mSAProf.Enrolled TO mSAProf.Enrolled.ID = 0
   //:   RESTRICTING mSAProf.TermOfResidence TO mSAProf.TermOfResidence.ID = 0
   //:   RESTRICTING mSAProf.StudentAccountTransApplied TO mSAProf.StudentAccountTransApplied.ID = 0
   //:   RESTRICTING mSAProf.TermOfMealPlan TO mSAProf.TermOfMealPlan.BeginDate >= lTermLSTT.CollegeTerm.ResidencyStartDate 
   //:            AND mSAProf.TermOfMealPlan.BeginDate <= lTermLSTT.CollegeTerm.ResidencyEndDate 
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, lTermLSTT, "CollegeTerm", "ResidencyStartDate" );
   szTempString_0 = sb_szTempString_0.toString( );}
   {StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
       GetStringFromAttribute( sb_szTempString_1, lTermLSTT, "CollegeTerm", "ResidencyEndDate" );
   szTempString_1 = sb_szTempString_1.toString( );}
   //:            //AND mSAProf.TermOfMealPlan.WithdrawalDate = ""
   //:   RESTRICTING mSAProf.BillingPeriod TO 
   //:               ( mSAProf.PeriodCollegeYear.ID = nCollegeYearID OR mSAProf.BillingPeriod.Undated = "Y" )
   omSAProf_fnLocalBuildQual_22( mSAStu, vTempViewVar_0, lTempInteger_0, lTempInteger_1, nCollegeYearID, szTempString_0, szTempString_1 );
   RESULT = ActivateObjectInstance( mSAProf, "mSAProf", mSAStu, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:IF RESULT < 0 
   if ( RESULT < 0 )
   { 
      //:RETURN RESULT  
      if(8==8)return( RESULT );
   } 

   //:END
   //:DropView ( lTermLSTT )
   DropView( lTermLSTT );

   //:// Build the Billing Periods if they don't currently exist.
   //:IF mSAProf.BillingPeriod DOES NOT EXIST
   lTempInteger_2 = CheckExistenceOfEntity( mSAProf, "BillingPeriod" );
   if ( lTempInteger_2 != 0 )
   { 
      //:// I do not want to BuildTermEnrolled which is in UpdateBillingPeriods.  
      //:// Not sure what to do.
      //:UpdateBillingPeriods(  mSAProf, nCollegeYearID )
      omSAProf_UpdateBillingPeriods( mSAProf, nCollegeYearID );
   } 

   //:END

   //:// Position on current Term.
   //:SET CURSOR FIRST mSAProf.BillingPeriod WHERE mSAProf.PeriodCollegeTerm.ID = nCollegeTermID  
   RESULT = mSAProf.cursor( "BillingPeriod" ).setFirst().toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeTerm", "ID", nCollegeTermID ) != 0 ) )
      { 
         RESULT = mSAProf.cursor( "BillingPeriod" ).setNextContinue().toInt();;
      } 

   } 

   //:IF RESULT >= zCURSOR_SET  
   if ( RESULT >= zCURSOR_SET )
   { 
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm WHERE mSAProf.PeriodCollegeTerm.ID = nCollegeTermID  
      RESULT = mSAProf.cursor( "PeriodCollegeTerm" ).setFirst( "ID", nCollegeTermID ).toInt();
      //:ELSE 
   } 
   else
   { 
      //:SET CURSOR FIRST mSAProf.BillingPeriod WHERE mSAProf.PeriodCollegeYear.ID = nCollegeYearID  
      RESULT = mSAProf.cursor( "BillingPeriod" ).setFirst().toInt();
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeYear", "ID", nCollegeYearID ) != 0 ) )
         { 
            RESULT = mSAProf.cursor( "BillingPeriod" ).setNextContinue().toInt();;
         } 

      } 

      //:IF RESULT < zCURSOR_SET  
      if ( RESULT < zCURSOR_SET )
      { 
         //:SET CURSOR FIRST mSAProf.BillingPeriod // in this case only
         RESULT = mSAProf.cursor( "BillingPeriod" ).setFirst().toInt();
      } 

      //:END 
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm // in this case only
      RESULT = mSAProf.cursor( "PeriodCollegeTerm" ).setFirst().toInt();
   } 

   //:END
   return( 0 );
//       
//    //LocateMealPlan( mSAProf )
//    
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dWeeksInResidence( VIEW mSAProf BASED ON LOD mSAProf,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )
//:   INTEGER nDays
public int 
omSAProf_dWeeksInResidence( View     mSAProf,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   int      nDays = 0;
   //:STRING (  18  ) szBeginDate
   String   szBeginDate = null;
   //:STRING (  18  ) szEndDateToUser
   String   szEndDateToUser = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:IF  mSAProf.TermOfResidence.WithdrawalDate = "" 
         if ( CompareAttributeToString( mSAProf, "TermOfResidence", "WithdrawalDate", "" ) == 0 )
         { 
            //:GetDateAttributeDifferenceInDays( nDays, 
            //:                                  mSAProf, "TermOfResidence", "BeginDate",
            //:                                  mSAProf, "TermOfResidence", "EndDate" )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
             {MutableInt mi_nDays = new MutableInt( nDays );
                         m_ZGLOBAL1_Operation.GetDateAttributeDifferenceInDays( mi_nDays, mSAProf, "TermOfResidence", "BeginDate", mSAProf, "TermOfResidence", "EndDate" );
            nDays = mi_nDays.intValue( );}
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:nDays = nDays / 7
            nDays = nDays / 7;
            //:ELSE 
         } 
         else
         { 
            //:GetDateAttributeDifferenceInDays( nDays, 
            //:                                  mSAProf, "TermOfResidence", "BeginDate",
            //:                                  mSAProf, "TermOfResidence", "WithdrawalDate" )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
             {MutableInt mi_nDays = new MutableInt( nDays );
                         m_ZGLOBAL1_Operation.GetDateAttributeDifferenceInDays( mi_nDays, mSAProf, "TermOfResidence", "BeginDate", mSAProf, "TermOfResidence", "WithdrawalDate" );
            nDays = mi_nDays.intValue( );}
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //: nDays = nDays / 7
            nDays = nDays / 7;
         } 

         //:END
         //:nDays = nDays * -1
         nDays = nDays * -1;
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                     InternalAttribStructure, nDays, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, nDays, 0 );
         break ;
      //:/* end zDERIVED_GET */
      //:OF  zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

   //:  

   //:  /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dSATransactionCode( VIEW mSAProf BASED ON LOD mSAProf,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   STRING (  18  ) szString
public int 
omSAProf_dSATransactionCode( View     mSAProf,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szString = null;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:szString = mSAProf.SATransactionCode.TransactionCode 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetVariableFromAttribute( sb_szString, mi_lTempInteger_0, 'S', 19, mSAProf, "SATransactionCode", "TransactionCode", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szString = sb_szString.toString( );}
         //:StoreStringInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, szString )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szString );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dBillDaysInResidence( VIEW mSAProf BASED ON LOD mSAProf,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   INTEGER nDays
public int 
omSAProf_dBillDaysInResidence( View     mSAProf,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   int      nDays = 0;
   //:STRING (  18  ) szBeginDate
   String   szBeginDate = null;
   //:STRING (  18  ) szEndDateToUser
   String   szEndDateToUser = null;
   int      lTempInteger_0 = 0;

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:nDays = 0
         nDays = 0;
         //:IF  mSAProf.BillingTermOfResidence EXISTS 
         lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "BillingTermOfResidence" );
         if ( lTempInteger_0 == 0 )
         { 
            //:IF  mSAProf.BillingTermOfResidence.WithdrawalDate = "" 
            if ( CompareAttributeToString( mSAProf, "BillingTermOfResidence", "WithdrawalDate", "" ) == 0 )
            { 
               //:GetDateAttributeDifferenceInDays( nDays, 
               //:                                 mSAProf, "BillingTermOfResidence", "BeginDate",
               //:                                 mSAProf, "BillingTermOfResidence", "EndDate" )
               {
                ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
                {MutableInt mi_nDays = new MutableInt( nDays );
                               m_ZGLOBAL1_Operation.GetDateAttributeDifferenceInDays( mi_nDays, mSAProf, "BillingTermOfResidence", "BeginDate", mSAProf, "BillingTermOfResidence", "EndDate" );
               nDays = mi_nDays.intValue( );}
                // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
               }
               //:ELSE 
            } 
            else
            { 
               //:GetDateAttributeDifferenceInDays( nDays, 
               //:                                 mSAProf, "BillingTermOfResidence", "BeginDate",
               //:                                 mSAProf, "BillingTermOfResidence", "WithdrawalDate" )
               {
                ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
                {MutableInt mi_nDays = new MutableInt( nDays );
                               m_ZGLOBAL1_Operation.GetDateAttributeDifferenceInDays( mi_nDays, mSAProf, "BillingTermOfResidence", "BeginDate", mSAProf, "BillingTermOfResidence", "WithdrawalDate" );
               nDays = mi_nDays.intValue( );}
                // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
               }
            } 

            //:END
         } 

         //:END
         //:nDays = nDays * -1
         nDays = nDays * -1;
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, nDays, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, nDays, 0 );
         break ;
      //:/* end zDERIVED_GET */
      //:OF  zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

   //:  

   //:  /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dBillDaysInMealPlan( VIEW mSAProf BASED ON LOD mSAProf,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   INTEGER nDays
public int 
omSAProf_dBillDaysInMealPlan( View     mSAProf,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   int      nDays = 0;
   //:STRING (  18  ) szBeginDate
   String   szBeginDate = null;
   //:STRING (  18  ) szEndDateToUser
   String   szEndDateToUser = null;
   int      lTempInteger_0 = 0;

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:nDays = 0
         nDays = 0;
         //:IF mSAProf.BillingTermOfMealPlan EXISTS 
         lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "BillingTermOfMealPlan" );
         if ( lTempInteger_0 == 0 )
         { 
            //:IF mSAProf.BillingTermOfMealPlan.WithdrawalDate = "" 
            if ( CompareAttributeToString( mSAProf, "BillingTermOfMealPlan", "WithdrawalDate", "" ) == 0 )
            { 
               //:GetDateAttributeDifferenceInDays( nDays, 
               //:                                 mSAProf, "BillingTermOfMealPlan", "BeginDate",
               //:                                 mSAProf, "BillingTermOfMealPlan", "EndDate" )
               {
                ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
                {MutableInt mi_nDays = new MutableInt( nDays );
                               m_ZGLOBAL1_Operation.GetDateAttributeDifferenceInDays( mi_nDays, mSAProf, "BillingTermOfMealPlan", "BeginDate", mSAProf, "BillingTermOfMealPlan", "EndDate" );
               nDays = mi_nDays.intValue( );}
                // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
               }
               //:ELSE 
            } 
            else
            { 
               //:GetDateAttributeDifferenceInDays( nDays, 
               //:                                 mSAProf, "BillingTermOfMealPlan", "BeginDate",
               //:                                 mSAProf, "BillingTermOfMealPlan", "WithdrawalDate" )
               {
                ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
                {MutableInt mi_nDays = new MutableInt( nDays );
                               m_ZGLOBAL1_Operation.GetDateAttributeDifferenceInDays( mi_nDays, mSAProf, "BillingTermOfMealPlan", "BeginDate", mSAProf, "BillingTermOfMealPlan", "WithdrawalDate" );
               nDays = mi_nDays.intValue( );}
                // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
               }
            } 

            //:END
         } 

         //:END
         //:nDays = nDays * -1
         nDays = nDays * -1;
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, nDays, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, nDays, 0 );
         break ;
      //:/* end zDERIVED_GET */
      //:OF  zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

   //:  

   //:  /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dBillDaysTerm( VIEW mSAProf BASED ON LOD mSAProf,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   INTEGER nDays
public int 
omSAProf_dBillDaysTerm( View     mSAProf,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   int      nDays = 0;
   //:STRING (  18  ) szBeginDate
   String   szBeginDate = null;
   //:STRING (  18  ) szEndDateToUser
   String   szEndDateToUser = null;
   int      lTempInteger_0 = 0;

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:nDays = 0
         nDays = 0;

            //:END

         //:END
         //:nDays = nDays * -1
         nDays = nDays * -1;
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, nDays, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, nDays, 0 );
         break ;
      //:/* end zDERIVED_GET */
      //:OF  zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

   //:  

   //:  /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
public int 
omSAProf_dClassIsIndStudy( View     mSAProf,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   String   szTempString_0 = null;

   //:dClassIsIndStudy( VIEW mSAProf BASED ON LOD mSAProf,
   //:               STRING ( 32 ) InternalEntityStructure,
   //:               STRING ( 32 ) InternalAttribStructure,
   //:               SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:StoreStringInRecord( mSAProf,
         //:                     InternalEntityStructure, InternalAttribStructure, mSAProf.TermClass.IndependentStudyFlag )
         {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetStringFromAttribute( sb_szTempString_0, mSAProf, "TermClass", "IndependentStudyFlag" );
         szTempString_0 = sb_szTempString_0.toString( );}
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szTempString_0 );
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
omSAProf_dClassIsDirectedStudy( View     mSAProf,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   String   szTempString_0 = null;

   //:dClassIsDirectedStudy( VIEW mSAProf BASED ON LOD mSAProf,
   //:                    STRING ( 32 ) InternalEntityStructure,
   //:                    STRING ( 32 ) InternalAttribStructure,
   //:                    SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:StoreStringInRecord( mSAProf,
         //:                     InternalEntityStructure, InternalAttribStructure, mSAProf.TermClass.DirectedStudyFlag  )
         {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetStringFromAttribute( sb_szTempString_0, mSAProf, "TermClass", "DirectedStudyFlag" );
         szTempString_0 = sb_szTempString_0.toString( );}
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szTempString_0 );
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
//:dTotalSACharges( VIEW mSAProf BASED ON LOD mSAProf,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )
//:  
//:   VIEW  mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dTotalSACharges( View     mSAProf,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:IF mSAProf2.StudentAccountProfile.BalanceForward > 0 
         if ( CompareAttributeToInteger( mSAProf2, "StudentAccountProfile", "BalanceForward", 0 ) > 0 )
         { 
            //:dAmount = mSAProf2.StudentAccountProfile.BalanceForward 
            {MutableDouble md_dAmount = new MutableDouble( dAmount );
                         GetDecimalFromAttribute( md_dAmount, mSAProf2, "StudentAccountProfile", "BalanceForward" );
            dAmount = md_dAmount.doubleValue( );}
            //:ELSE 
         } 
         else
         { 
            //:dAmount = 0
            dAmount = 0;
         } 

         //:END
         //:FOR EACH mSAProf2.StudentAccountTransApplied  
         //:      WHERE mSAProf2.StudentAccountTransApplied.Amount >= 0
         RESULT = mSAProf2.cursor( "StudentAccountTransApplied" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToInteger( mSAProf2, "StudentAccountTransApplied", "Amount", 0 ) >= 0 )
            { 
               //:dAmount = dAmount + mSAProf2.StudentAccountTransApplied.Amount 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "StudentAccountTransApplied", "Amount" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dAmount = dAmount + dTempDecimal_0;
            } 

            RESULT = mSAProf2.cursor( "StudentAccountTransApplied" ).setNextContinue().toInt();;
         } 

         //:END 
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dClassCharges( VIEW mSAProf BASED ON LOD mSAProf,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )
//:  
//:   VIEW  mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dClassCharges( View     mSAProf,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:dAmount = 0
         dAmount = 0;
         //:FOR EACH mSAProf2.StudentAccountTransApplied  
         RESULT = mSAProf2.cursor( "StudentAccountTransApplied" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:dAmount = dAmount + mSAProf2.StudentAccountTransApplied.Amount 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "StudentAccountTransApplied", "Amount" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dAmount = dAmount + dTempDecimal_0;
            RESULT = mSAProf2.cursor( "StudentAccountTransApplied" ).setNextContinue().toInt();;
         } 

         //:END 
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dBalance( VIEW mSAProf BASED ON LOD mSAProf,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )
//:   VIEW  mSAProfB BASED ON LOD mSAProfB
public int 
omSAProf_dBalance( View     mSAProf,
                   String InternalEntityStructure,
                   String InternalAttribStructure,
                   Integer   GetOrSetFlag )
{
   zVIEW    mSAProfB = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:dAmount = 0
         dAmount = 0;
         //:IF mSAProf.StudentAccountProfile.ID != ""
         if ( CompareAttributeToString( mSAProf, "StudentAccountProfile", "ID", "" ) != 0 )
         { 
            //:// On 3/17/2011, DonC changed the code below to use mSAProf instead of mSAProfB for computing the
            //:// balance, because the balance wasn't being updated correctly.
            //:dAmount = mSAProf.StudentAccountProfile.BalanceForward 
            {MutableDouble md_dAmount = new MutableDouble( dAmount );
                         GetDecimalFromAttribute( md_dAmount, mSAProf, "StudentAccountProfile", "BalanceForward" );
            dAmount = md_dAmount.doubleValue( );}
            //:FOR EACH mSAProf.StudentAccountTransApplied  
            RESULT = mSAProf.cursor( "StudentAccountTransApplied" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:dAmount = dAmount + mSAProf.StudentAccountTransApplied.Amount 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf, "StudentAccountTransApplied", "Amount" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dAmount = dAmount + dTempDecimal_0;
               RESULT = mSAProf.cursor( "StudentAccountTransApplied" ).setNextContinue().toInt();;
            } 

            //:END
         } 


         //:   /*ACTIVATE mSAProfB
         //:      WHERE mSAProfB.StudentAccountProfile.ID = mSAProf.StudentAccountProfile.ID 
         //:   IF RESULT >= 0  
         //:      dAmount = mSAProfB.StudentAccountProfile.BalanceForward 
         //:      FOR EACH mSAProfB.StudentAccountTransApplied  
         //:         dAmount = dAmount + mSAProfB.StudentAccountTransApplied.Amount 
         //:      END 
         //:      DropView( mSAProfB ) 
         //:   END*/
         //:END
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                     InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dTotalChargesPerPeriod( VIEW mSAProf BASED ON LOD mSAProf,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )
//:   VIEW  mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dTotalChargesPerPeriod( View     mSAProf,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:dAmount = 0
         dAmount = 0;
         //:FOR EACH mSAProf2.PeriodTransApplied 
         //:      WHERE mSAProf2.PeriodSATransactionCode.BillType != ""
         RESULT = mSAProf2.cursor( "PeriodTransApplied" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( mSAProf2, "PeriodSATransactionCode", "BillType", "" ) != 0 )
            { 
               //:dAmount = dAmount + mSAProf2.PeriodTransApplied.Amount 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "PeriodTransApplied", "Amount" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dAmount = dAmount + dTempDecimal_0;
            } 

            RESULT = mSAProf2.cursor( "PeriodTransApplied" ).setNextContinue().toInt();;
         } 

         //:END 
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:   /* end zDERIVED_GET */

      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

   //:   /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dTotalCredits( VIEW mSAProf BASED ON LOD mSAProf,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )
//:   VIEW  mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dTotalCredits( View     mSAProf,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:dAmount = 0
         dAmount = 0;
         //:FOR EACH mSAProf2.TermEnrolled 
         RESULT = mSAProf2.cursor( "TermEnrolled" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mSAProf2.TermEnrolled.Status ="T"
            if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "T" ) == 0 )
            { 
               //:dAmount = dAmount + mSAProf2.TermEnrolled.CreditHours 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "TermEnrolled", "CreditHours" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dAmount = dAmount + dTempDecimal_0;
            } 

            RESULT = mSAProf2.cursor( "TermEnrolled" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dCreditsEnComp( VIEW mSAProf BASED ON LOD mSAProf,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )
//:   VIEW mSAProf2  BASED ON LOD mSAProf
public int 
omSAProf_dCreditsEnComp( View     mSAProf,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dTotalCredits 
   double  dTotalCredits = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Total the credit hours that will be billable. Note that a Class dropped after the
         //:// Dropped Date for the Term will be added here.
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:NAME VIEW mSAProf2 "mSAProf2"
         SetNameForView( mSAProf2, "mSAProf2", null, zLEVEL_TASK );
         //:dTotalCredits = 0
         dTotalCredits = 0;
         //:FOR EACH mSAProf2.TermEnrolled 
         RESULT = mSAProf2.cursor( "TermEnrolled" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mSAProf2.TermEnrolled.Status ="T"  OR mSAProf2.TermEnrolled.Status ="C" 
            if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "T" ) == 0 || CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "C" ) == 0 )
            { 
               //:dTotalCredits = dTotalCredits + mSAProf2.TermEnrolled.CreditHours 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "TermEnrolled", "CreditHours" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dTotalCredits = dTotalCredits + dTempDecimal_0;
               //:ELSE
            } 
            else
            { 
               //:IF mSAProf2.TermEnrolled.Status ="D" OR mSAProf2.TermEnrolled.Status ="W" 
               if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "D" ) == 0 || CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "W" ) == 0 )
               { 
                  //:IF mSAProf2.TermEnrolled.DroppedDate > mSAProf2.PeriodCollegeTerm.AddDropDeadlineDate 
                  if ( CompareAttributeToAttribute( mSAProf2, "TermEnrolled", "DroppedDate", mSAProf2, "PeriodCollegeTerm", "AddDropDeadlineDate" ) > 0 )
                  { 
                     //:dTotalCredits = dTotalCredits + mSAProf2.TermEnrolled.CreditHours
                     {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                                           GetDecimalFromAttribute( md_dTempDecimal_1, mSAProf2, "TermEnrolled", "CreditHours" );
                     dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
                     dTotalCredits = dTotalCredits + dTempDecimal_1;
                  } 

                  //:END
               } 

               //:END
            } 

            RESULT = mSAProf2.cursor( "TermEnrolled" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dTotalCredits, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dTotalCredits, 0 );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dCreditsDirectedStudy( VIEW mSAProf BASED ON LOD mSAProf,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )
//:   VIEW mSAProf2  BASED ON LOD mSAProf
public int 
omSAProf_dCreditsDirectedStudy( View     mSAProf,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dTotalCredits 
   double  dTotalCredits = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Total the Directed Study credit hours that will be billable. Note that a Class dropped after the
         //:// Dropped Date for the Term will be added here.
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:NAME VIEW mSAProf2 "mSAProf2"
         SetNameForView( mSAProf2, "mSAProf2", null, zLEVEL_TASK );
         //:dTotalCredits = 0
         dTotalCredits = 0;
         //:FOR EACH mSAProf2.TermEnrolled 
         RESULT = mSAProf2.cursor( "TermEnrolled" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mSAProf2.TermClass.DirectedStudyFlag = "Y"
            if ( CompareAttributeToString( mSAProf2, "TermClass", "DirectedStudyFlag", "Y" ) == 0 )
            { 
               //:IF mSAProf2.TermEnrolled.Status ="T"  OR mSAProf2.TermEnrolled.Status ="C" 
               if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "T" ) == 0 || CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "C" ) == 0 )
               { 
                  //:dTotalCredits = dTotalCredits + mSAProf2.TermEnrolled.CreditHours 
                  {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                     GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "TermEnrolled", "CreditHours" );
                  dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                  dTotalCredits = dTotalCredits + dTempDecimal_0;
                  //:ELSE
               } 
               else
               { 
                  //:IF mSAProf2.TermEnrolled.Status ="D" OR mSAProf2.TermEnrolled.Status ="W" 
                  if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "D" ) == 0 || CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "W" ) == 0 )
                  { 
                     //:IF mSAProf2.TermEnrolled.DroppedDate > mSAProf2.PeriodCollegeTerm.AddDropDeadlineDate 
                     if ( CompareAttributeToAttribute( mSAProf2, "TermEnrolled", "DroppedDate", mSAProf2, "PeriodCollegeTerm", "AddDropDeadlineDate" ) > 0 )
                     { 
                        //:dTotalCredits = dTotalCredits + mSAProf2.TermEnrolled.CreditHours
                        {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_1, mSAProf2, "TermEnrolled", "CreditHours" );
                        dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
                        dTotalCredits = dTotalCredits + dTempDecimal_1;
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            RESULT = mSAProf2.cursor( "TermEnrolled" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dTotalCredits, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dTotalCredits, 0 );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dClassesDirectedStudy( VIEW mSAProf BASED ON LOD mSAProf,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )
//:   VIEW mSAProf2  BASED ON LOD mSAProf
public int 
omSAProf_dClassesDirectedStudy( View     mSAProf,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:INTEGER TotalClasses
   int      TotalClasses = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Total the number of Directed Study classes that will be billable. Note that a Class dropped after the
         //:// Dropped Date for the Term will be added here.
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:NAME VIEW mSAProf2 "mSAProf2"
         SetNameForView( mSAProf2, "mSAProf2", null, zLEVEL_TASK );
         //:TotalClasses = 0
         TotalClasses = 0;
         //:FOR EACH mSAProf2.TermEnrolled 
         RESULT = mSAProf2.cursor( "TermEnrolled" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mSAProf2.TermClass.DirectedStudyFlag = "Y"
            if ( CompareAttributeToString( mSAProf2, "TermClass", "DirectedStudyFlag", "Y" ) == 0 )
            { 
               //:IF mSAProf2.TermEnrolled.Status ="T"  OR mSAProf2.TermEnrolled.Status ="C" 
               if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "T" ) == 0 || CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "C" ) == 0 )
               { 
                  //:TotalClasses = TotalClasses + 1
                  TotalClasses = TotalClasses + 1;
                  //:ELSE
               } 
               else
               { 
                  //:IF mSAProf2.TermEnrolled.Status ="D" OR mSAProf2.TermEnrolled.Status ="W" 
                  if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "D" ) == 0 || CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "W" ) == 0 )
                  { 
                     //:IF mSAProf2.TermEnrolled.DroppedDate > mSAProf2.PeriodCollegeTerm.AddDropDeadlineDate 
                     if ( CompareAttributeToAttribute( mSAProf2, "TermEnrolled", "DroppedDate", mSAProf2, "PeriodCollegeTerm", "AddDropDeadlineDate" ) > 0 )
                     { 
                        //:TotalClasses = TotalClasses + 1
                        TotalClasses = TotalClasses + 1;
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            RESULT = mSAProf2.cursor( "TermEnrolled" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, TotalClasses, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, TotalClasses, 0 );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dClassesAudited( VIEW mSAProf BASED ON LOD mSAProf,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )
//:   VIEW mSAProf2  BASED ON LOD mSAProf
public int 
omSAProf_dClassesAudited( View     mSAProf,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:INTEGER TotalClasses
   int      TotalClasses = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Total the Audited credit hours that will be billable. Note that a Class dropped after the
         //:// Dropped Date for the Term will be added here.
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:NAME VIEW mSAProf2 "mSAProf2"
         SetNameForView( mSAProf2, "mSAProf2", null, zLEVEL_TASK );
         //:TotalClasses = 0
         TotalClasses = 0;
         //:FOR EACH mSAProf2.TermEnrolled 
         RESULT = mSAProf2.cursor( "TermEnrolled" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mSAProf2.TermEnrolled.TakingClassType = "A"
            if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "TakingClassType", "A" ) == 0 )
            { 
               //:IF mSAProf2.TermEnrolled.Status ="T"  OR mSAProf2.TermEnrolled.Status ="C" 
               if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "T" ) == 0 || CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "C" ) == 0 )
               { 
                  //:TotalClasses = TotalClasses + 1
                  TotalClasses = TotalClasses + 1;
                  //:ELSE
               } 
               else
               { 
                  //:IF mSAProf2.TermEnrolled.Status ="D" OR mSAProf2.TermEnrolled.Status ="W" 
                  if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "D" ) == 0 || CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "W" ) == 0 )
                  { 
                     //:IF mSAProf2.TermEnrolled.DroppedDate > mSAProf2.PeriodCollegeTerm.AddDropDeadlineDate 
                     if ( CompareAttributeToAttribute( mSAProf2, "TermEnrolled", "DroppedDate", mSAProf2, "PeriodCollegeTerm", "AddDropDeadlineDate" ) > 0 )
                     { 
                        //:TotalClasses = TotalClasses + 1
                        TotalClasses = TotalClasses + 1;
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            RESULT = mSAProf2.cursor( "TermEnrolled" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, TotalClasses, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, TotalClasses, 0 );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dCreditsInternship( VIEW mSAProf BASED ON LOD mSAProf,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )
//:   VIEW mSAProf2  BASED ON LOD mSAProf
public int 
omSAProf_dCreditsInternship( View     mSAProf,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dTotalCredits 
   double  dTotalCredits = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Total the Internship credit hours that will be billable. Note that a Class dropped after the
         //:// Dropped Date for the Term will be added here.
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:NAME VIEW mSAProf2 "mSAProf2"
         SetNameForView( mSAProf2, "mSAProf2", null, zLEVEL_TASK );
         //:dTotalCredits = 0
         dTotalCredits = 0;
         //:FOR EACH mSAProf2.TermEnrolled 
         RESULT = mSAProf2.cursor( "TermEnrolled" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mSAProf2.TermCourse.InternshipFlag = "Y"
            if ( CompareAttributeToString( mSAProf2, "TermCourse", "InternshipFlag", "Y" ) == 0 )
            { 
               //:IF mSAProf2.TermEnrolled.Status ="T"  OR mSAProf2.TermEnrolled.Status ="C" 
               if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "T" ) == 0 || CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "C" ) == 0 )
               { 
                  //:dTotalCredits = dTotalCredits + mSAProf2.TermEnrolled.CreditHours 
                  {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                     GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "TermEnrolled", "CreditHours" );
                  dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                  dTotalCredits = dTotalCredits + dTempDecimal_0;
                  //:ELSE
               } 
               else
               { 
                  //:IF mSAProf2.TermEnrolled.Status ="D" OR mSAProf2.TermEnrolled.Status ="W" 
                  if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "D" ) == 0 || CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "W" ) == 0 )
                  { 
                     //:IF mSAProf2.TermEnrolled.DroppedDate > mSAProf2.PeriodCollegeTerm.AddDropDeadlineDate 
                     if ( CompareAttributeToAttribute( mSAProf2, "TermEnrolled", "DroppedDate", mSAProf2, "PeriodCollegeTerm", "AddDropDeadlineDate" ) > 0 )
                     { 
                        //:dTotalCredits = dTotalCredits + mSAProf2.TermEnrolled.CreditHours
                        {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_1, mSAProf2, "TermEnrolled", "CreditHours" );
                        dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
                        dTotalCredits = dTotalCredits + dTempDecimal_1;
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            RESULT = mSAProf2.cursor( "TermEnrolled" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dTotalCredits, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dTotalCredits, 0 );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateSAProfForYear( VIEW    mSAProf BASED ON LOD mSAProf,
//:                       VIEW    mSAStu  BASED ON LOD mSAStu,
//:                       INTEGER nCollegeYearID,
//:                       INTEGER nCollegeTermID )
//:   VIEW mUser     REGISTERED AS mUser
public int 
omSAProf_ActivateSAProfForYear( zVIEW    mSAProf,
                                View     mSAStu,
                                int      nCollegeYearID,
                                int      nCollegeTermID )
{
   zVIEW    mUser = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lTermLST  BASED ON LOD  lTermLST
   zVIEW    lTermLST = new zVIEW( );
   //:DECIMAL TotalCredits
   double  TotalCredits = 0.0;
   //:INTEGER CurrentTermID
   int      CurrentTermID = 0;
   //:INTEGER CollegeTermID
   int      CollegeTermID = 0;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_5 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_6 = 0;

   RESULT = GetViewByName( mUser, "mUser", mSAStu, zLEVEL_TASK );

   //:ACTIVATE mSAProf MULTIPLE //temp
   //:   WHERE mSAProf.Student.ID = mSAStu.Student.ID 
   //:     AND mSAProf.ProfileAdministrativeDivision.ID = mUser.CurrentAdministrativeDivision.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSAStu, "Student", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mUser, "CurrentAdministrativeDivision", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   //:   RESTRICTING mSAProf.FinAidProfile TO mSAProf.FinAidCollegeYear.ID = nCollegeYearID 
   //:   RESTRICTING mSAProf.Enrolled TO 
   //:               (mSAProf.EnrollmentCollegeYear.ID = nCollegeYearID OR mSAProf.RegisteredCollegeYear.ID = nCollegeYearID)
   //:               OR mSAProf.Cohort EXISTS 
   //:   RESTRICTING mSAProf.BillingPeriod TO 
   //:               ( mSAProf.PeriodCollegeYear.ID = nCollegeYearID OR mSAProf.BillingPeriod.Undated = "Y")
   omSAProf_fnLocalBuildQual_0( mSAStu, vTempViewVar_0, lTempInteger_0, lTempInteger_1, nCollegeYearID );
   RESULT = ActivateObjectInstance( mSAProf, "mSAProf", mSAStu, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:IF RESULT < 0 
   if ( RESULT < 0 )
   { 
      //:RETURN RESULT  
      if(8==8)return( RESULT );
   } 

   //:END

   //:IF mSAProf.StudentAccountTransApplied EXISTS 
   lTempInteger_2 = CheckExistenceOfEntity( mSAProf, "StudentAccountTransApplied" );
   if ( lTempInteger_2 == 0 )
   { 
      //:OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D" )
      OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D" );
   } 

   //:END

   //:SET CURSOR FIRST mSAProf.BillingPeriod WHERE mSAProf.PeriodCollegeTerm.ID = nCollegeTermID  
   RESULT = mSAProf.cursor( "BillingPeriod" ).setFirst().toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeTerm", "ID", nCollegeTermID ) != 0 ) )
      { 
         RESULT = mSAProf.cursor( "BillingPeriod" ).setNextContinue().toInt();;
      } 

   } 

   //:IF RESULT >= zCURSOR_SET  
   if ( RESULT >= zCURSOR_SET )
   { 
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm WHERE mSAProf.PeriodCollegeTerm.ID = nCollegeTermID  
      RESULT = mSAProf.cursor( "PeriodCollegeTerm" ).setFirst( "ID", nCollegeTermID ).toInt();
      //:ELSE 
   } 
   else
   { 
      //:SET CURSOR FIRST mSAProf.BillingPeriod WHERE mSAProf.PeriodCollegeYear.ID = nCollegeYearID  
      RESULT = mSAProf.cursor( "BillingPeriod" ).setFirst().toInt();
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeYear", "ID", nCollegeYearID ) != 0 ) )
         { 
            RESULT = mSAProf.cursor( "BillingPeriod" ).setNextContinue().toInt();;
         } 

      } 

      //:IF RESULT < zCURSOR_SET  
      if ( RESULT < zCURSOR_SET )
      { 
         //:SET CURSOR FIRST mSAProf.BillingPeriod // in this case only
         RESULT = mSAProf.cursor( "BillingPeriod" ).setFirst().toInt();
      } 

      //:END 
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm // in this case only
      RESULT = mSAProf.cursor( "PeriodCollegeTerm" ).setFirst().toInt();
   } 

   //:END

   //:FOR EACH mSAProf.PeriodCollegeTerm WITHIN mSAProf.StudentAccountProfile 
   RESULT = mSAProf.cursor( "PeriodCollegeTerm" ).setFirst( "StudentAccountProfile" ).toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:TotalCredits = 0
      TotalCredits = 0;
      //:CollegeTermID = mSAProf.PeriodCollegeTerm.ID
      {MutableInt mi_CollegeTermID = new MutableInt( CollegeTermID );
             GetIntegerFromAttribute( mi_CollegeTermID, mSAProf, "PeriodCollegeTerm", "ID" );
      CollegeTermID = mi_CollegeTermID.intValue( );}
      //:FOR EACH mSAProf.Enrolled 
      RESULT = mSAProf.cursor( "Enrolled" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSAProf.RegisteredCollegeTerm EXISTS
         lTempInteger_3 = CheckExistenceOfEntity( mSAProf, "RegisteredCollegeTerm" );
         if ( lTempInteger_3 == 0 )
         { 
            //:CurrentTermID = mSAProf.RegisteredCollegeTerm.ID
            {MutableInt mi_CurrentTermID = new MutableInt( CurrentTermID );
                         GetIntegerFromAttribute( mi_CurrentTermID, mSAProf, "RegisteredCollegeTerm", "ID" );
            CurrentTermID = mi_CurrentTermID.intValue( );}
            //:ELSE
         } 
         else
         { 
            //:IF mSAProf.CurrentCollegeTerm EXISTS 
            lTempInteger_4 = CheckExistenceOfEntity( mSAProf, "CurrentCollegeTerm" );
            if ( lTempInteger_4 == 0 )
            { 
               //:CurrentTermID = mSAProf.CurrentCollegeTerm.ID
               {MutableInt mi_CurrentTermID = new MutableInt( CurrentTermID );
                               GetIntegerFromAttribute( mi_CurrentTermID, mSAProf, "CurrentCollegeTerm", "ID" );
               CurrentTermID = mi_CurrentTermID.intValue( );}
               //:ELSE 
            } 
            else
            { 
               //:CurrentTermID = 0
               CurrentTermID = 0;
            } 

            //:END
         } 

         //:END
         //://IF CurrentTermID = CollegeTermID OR CurrentTermID = 0
         //:IF CurrentTermID = CollegeTermID     // Modified from above by Don C on 8/16/06
         if ( CurrentTermID == CollegeTermID )
         { 
            //:IF mSAProf.Enrolled.Status = "T" OR mSAProf.Enrolled.Status = "C" OR mSAProf.Enrolled.Status = "D" OR mSAProf.Enrolled.Status = "W" 
            if ( CompareAttributeToString( mSAProf, "Enrolled", "Status", "T" ) == 0 || CompareAttributeToString( mSAProf, "Enrolled", "Status", "C" ) == 0 || CompareAttributeToString( mSAProf, "Enrolled", "Status", "D" ) == 0 ||
                 CompareAttributeToString( mSAProf, "Enrolled", "Status", "W" ) == 0 )
            { 
               //:TotalCredits = TotalCredits + mSAProf.Enrolled.CreditHours  
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf, "Enrolled", "CreditHours" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               TotalCredits = TotalCredits + dTempDecimal_0;
               //:INCLUDE mSAProf.TermEnrolled FROM mSAProf.Enrolled
               RESULT = IncludeSubobjectFromSubobject( mSAProf, "TermEnrolled", mSAProf, "Enrolled", zPOS_AFTER );
            } 

            //:END
         } 

         RESULT = mSAProf.cursor( "Enrolled" ).setNextContinue().toInt();;
         //:END
      } 

      RESULT = mSAProf.cursor( "PeriodCollegeTerm" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:// Make sure that we are positioned on correct Billing Period for Main Campus Students.
   //:IF mSAProf.AdministrativeDivision.UsesCohortsFlag != "Y"
   if ( CompareAttributeToString( mSAProf, "AdministrativeDivision", "UsesCohortsFlag", "Y" ) != 0 )
   { 
      //:GET VIEW lTermLST NAMED "lSATermLST"
      RESULT = GetViewByName( lTermLST, "lSATermLST", mSAStu, zLEVEL_TASK );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm WITHIN mSAProf.StudentAccountProfile 
         //:           WHERE mSAProf.PeriodCollegeTerm.ID = lTermLST.CollegeTerm.ID 
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                   GetIntegerFromAttribute( mi_lTempInteger_5, lTermLST, "CollegeTerm", "ID" );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );}
         RESULT = mSAProf.cursor( "PeriodCollegeTerm" ).setFirst( "ID", lTempInteger_5, "StudentAccountProfile" ).toInt();
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:szMsg = "No Billing Period exists for " + mSAProf.Person.dFullNameLFM + " for currently selected Term." 
            {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_6, 'S', 255, mSAProf, "Person", "dFullNameLFM", "", 0 );
            lTempInteger_6 = mi_lTempInteger_6.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringCopy( sb_szMsg, 1, 0, "No Billing Period exists for ", 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
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
                        ZeidonStringConcat( sb_szMsg, 1, 0, " for currently selected Term.", 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
            //:MessageSend( mSAProf, "", "Generate Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mSAProf, "", "Generate Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:// if the student's admin div uses cohorts then it is not by year, there is just one SA Profile at all
   //:LocateCurrentRoom( mSAProf )
   omSAProf_LocateCurrentRoom( mSAProf );
   //:LocateMealPlan( mSAProf )
   omSAProf_LocateMealPlan( mSAProf );
   //:RETURN 0
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateSAProfForGen( VIEW    mSAProf BASED ON LOD mSAProf,
//:                      VIEW    mSAStu  BASED ON LOD mSAStu,
//:                      INTEGER nSAProfileID,
//:                      INTEGER nCollegeYearID,
//:                      INTEGER nCollegeTermID )
//:   
//:   VIEW mUser     BASED ON LOD mUser
public int 
omSAProf_ActivateSAProfForGen( zVIEW    mSAProf,
                               View     mSAStu,
                               int      nSAProfileID,
                               int      nCollegeYearID,
                               int      nCollegeTermID )
{
   zVIEW    mUser = new zVIEW( );
   //:VIEW lTermLST  REGISTERED AS lTermLST
   zVIEW    lTermLST = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   //:SHORT nRC
   int      nRC = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( lTermLST, "lTermLST", mSAStu, zLEVEL_TASK );

   //:GET VIEW mUser NAMED "mUser"
   RESULT = GetViewByName( mUser, "mUser", mSAStu, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:GetViewByName( mUser, "mUser", mSAStu, zLEVEL_APPLICATION )
      GetViewByName( mUser, "mUser", mSAStu, zLEVEL_APPLICATION );
   } 

   //:END
   //:SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.ID = nCollegeTermID
   RESULT = lTermLST.cursor( "CollegeTerm" ).setFirst( "ID", nCollegeTermID ).toInt();

   //:// We're restricting StudentAccountTransApplied to the College Year.
   //:ACTIVATE mSAProf WHERE mSAProf.StudentAccountProfile.ID = nSAProfileID
   //:   RESTRICTING mSAProf.StudentAccountTransApplied TO mSAProf.AppliedBillingPeriod.BeginDate >= lTermLST.CollegeYear.BeginDate AND 
   //:                                                     mSAProf.AppliedBillingPeriod.BeginDate <= lTermLST.CollegeYear.EndDate 
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, lTermLST, "CollegeYear", "BeginDate" );
   szTempString_0 = sb_szTempString_0.toString( );}
   {StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
       GetStringFromAttribute( sb_szTempString_1, lTermLST, "CollegeYear", "EndDate" );
   szTempString_1 = sb_szTempString_1.toString( );}
   //:   RESTRICTING mSAProf.FinAidProfile TO mSAProf.FinAidCollegeYear.ID = nCollegeYearID 
   //:   RESTRICTING mSAProf.Enrolled      TO mSAProf.EnrollmentCollegeYear.ID = 0
   //:   RESTRICTING mSAProf.BillingPeriod TO mSAProf.PeriodCollegeTerm.ID = nCollegeTermID
   omSAProf_fnLocalBuildQual_1( mSAStu, vTempViewVar_0, nSAProfileID, szTempString_0, szTempString_1, nCollegeYearID, nCollegeTermID );
   RESULT = ActivateObjectInstance( mSAProf, "mSAProf", mSAStu, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:IF RESULT < 0 
   if ( RESULT < 0 )
   { 
      //:RETURN RESULT  
      if(8==8)return( RESULT );
   } 

   //:END

   //:IF mSAProf.StudentAccountTransApplied EXISTS 
   lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "StudentAccountTransApplied" );
   if ( lTempInteger_0 == 0 )
   { 
      //:OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D" )
      OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D" );
   } 

   //:END

   //:// Build the Billing Period Enrolled records for the Term.
   //:BuildTermEnrolledTerm( mSAProf, nCollegeTermID )
   omSAProf_BuildTermEnrolledTerm( mSAProf, nCollegeTermID );

   //:LocateCurrentRoom( mSAProf )
   omSAProf_LocateCurrentRoom( mSAProf );
   //:LocateMealPlan( mSAProf )
   omSAProf_LocateMealPlan( mSAProf );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:SetupSAProfForYear( VIEW mSAProf BASED ON LOD mSAProf,
//:                    INTEGER nCollegeYearID,
//:                    INTEGER nCollegeTermID )
//:   VIEW    mUser BASED ON LOD mUser
public int 
omSAProf_SetupSAProfForYear( View     mSAProf,
                             int      nCollegeYearID,
                             int      nCollegeTermID )
{
   zVIEW    mUser = new zVIEW( );
   //:DECIMAL TotalCredits
   double  TotalCredits = 0.0;
   //:INTEGER CurrentTermID
   int      CurrentTermID = 0;
   //:INTEGER CollegeTermID
   int      CollegeTermID = 0;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   double  dTempDecimal_0 = 0.0;


   //:IF mSAProf.StudentAccountTransApplied EXISTS 
   lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "StudentAccountTransApplied" );
   if ( lTempInteger_0 == 0 )
   { 
      //:OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D" )
      OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D" );
   } 

   //:END

   //:SET CURSOR FIRST mSAProf.BillingPeriod 
   //:   WHERE mSAProf.PeriodCollegeTerm.ID = nCollegeTermID  
   RESULT = mSAProf.cursor( "BillingPeriod" ).setFirst().toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeTerm", "ID", nCollegeTermID ) != 0 ) )
      { 
         RESULT = mSAProf.cursor( "BillingPeriod" ).setNextContinue().toInt();;
      } 

   } 

   //:IF RESULT >= zCURSOR_SET  
   if ( RESULT >= zCURSOR_SET )
   { 
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm  
      //:   WHERE mSAProf.PeriodCollegeTerm.ID = nCollegeTermID  
      RESULT = mSAProf.cursor( "PeriodCollegeTerm" ).setFirst( "ID", nCollegeTermID ).toInt();
      //:ELSE 
   } 
   else
   { 
      //:SET CURSOR FIRST mSAProf.BillingPeriod 
      //:   WHERE mSAProf.PeriodCollegeYear.ID = nCollegeYearID  
      RESULT = mSAProf.cursor( "BillingPeriod" ).setFirst().toInt();
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeYear", "ID", nCollegeYearID ) != 0 ) )
         { 
            RESULT = mSAProf.cursor( "BillingPeriod" ).setNextContinue().toInt();;
         } 

      } 

      //:IF RESULT < zCURSOR_SET  
      if ( RESULT < zCURSOR_SET )
      { 
         //:SET CURSOR FIRST mSAProf.BillingPeriod // in this case only
         RESULT = mSAProf.cursor( "BillingPeriod" ).setFirst().toInt();
      } 

      //:END 
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm // in this case only
      RESULT = mSAProf.cursor( "PeriodCollegeTerm" ).setFirst().toInt();
   } 

   //:END

   //:FOR EACH mSAProf.PeriodCollegeTerm
   RESULT = mSAProf.cursor( "PeriodCollegeTerm" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:TotalCredits = 0
      TotalCredits = 0;
      //:CollegeTermID = mSAProf.PeriodCollegeTerm.ID
      {MutableInt mi_CollegeTermID = new MutableInt( CollegeTermID );
             GetIntegerFromAttribute( mi_CollegeTermID, mSAProf, "PeriodCollegeTerm", "ID" );
      CollegeTermID = mi_CollegeTermID.intValue( );}
      //:FOR EACH mSAProf.Enrolled 
      RESULT = mSAProf.cursor( "Enrolled" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSAProf.RegisteredCollegeTerm EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mSAProf, "RegisteredCollegeTerm" );
         if ( lTempInteger_1 == 0 )
         { 
            //:CurrentTermID = mSAProf.RegisteredCollegeTerm.ID
            {MutableInt mi_CurrentTermID = new MutableInt( CurrentTermID );
                         GetIntegerFromAttribute( mi_CurrentTermID, mSAProf, "RegisteredCollegeTerm", "ID" );
            CurrentTermID = mi_CurrentTermID.intValue( );}
            //:ELSE
         } 
         else
         { 
            //:IF mSAProf.CurrentCollegeTerm EXISTS 
            lTempInteger_2 = CheckExistenceOfEntity( mSAProf, "CurrentCollegeTerm" );
            if ( lTempInteger_2 == 0 )
            { 
               //:CurrentTermID = mSAProf.CurrentCollegeTerm.ID
               {MutableInt mi_CurrentTermID = new MutableInt( CurrentTermID );
                               GetIntegerFromAttribute( mi_CurrentTermID, mSAProf, "CurrentCollegeTerm", "ID" );
               CurrentTermID = mi_CurrentTermID.intValue( );}
               //:ELSE 
            } 
            else
            { 
               //:CurrentTermID = 0
               CurrentTermID = 0;
            } 

            //:END
         } 

         //:END
         //://IF CurrentTermID = CollegeTermID OR CurrentTermID = 0
         //:IF CurrentTermID = CollegeTermID     // Modified from above by Don C on 8/16/06
         if ( CurrentTermID == CollegeTermID )
         { 
            //:IF mSAProf.Enrolled.Status = "T" OR mSAProf.Enrolled.Status = "C" 
            if ( CompareAttributeToString( mSAProf, "Enrolled", "Status", "T" ) == 0 || CompareAttributeToString( mSAProf, "Enrolled", "Status", "C" ) == 0 )
            { 
               //:TotalCredits = TotalCredits + mSAProf.Enrolled.CreditHours  
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf, "Enrolled", "CreditHours" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               TotalCredits = TotalCredits + dTempDecimal_0;
               //:INCLUDE mSAProf.TermEnrolled FROM mSAProf.Enrolled
               RESULT = IncludeSubobjectFromSubobject( mSAProf, "TermEnrolled", mSAProf, "Enrolled", zPOS_AFTER );
            } 

            //:END
         } 

         RESULT = mSAProf.cursor( "Enrolled" ).setNextContinue().toInt();;
         //:END
      } 

      RESULT = mSAProf.cursor( "PeriodCollegeTerm" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:// if the student's admin div uses cohorts then it is not by year, there is just one SA Profile at all
   //:LocateCurrentRoom( mSAProf )
   omSAProf_LocateCurrentRoom( mSAProf );
   //:LocateMealPlan( mSAProf )
   omSAProf_LocateMealPlan( mSAProf );
   //:RETURN 0
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:CreateSA( VIEW mSAProf BASED ON LOD mSAProf,
//:          VIEW mSAStu  BASED ON LOD mSAStu, 
//:          INTEGER nCollegeYearID )

//:   VIEW mYearLST BASED ON LOD  mYear 
public int 
omSAProf_CreateSA( zVIEW    mSAProf,
                   View     mSAStu,
                   int      nCollegeYearID )
{
   zVIEW    mYearLST = new zVIEW( );
   //:VIEW mFAProf  BASED ON LOD  mFAProf
   zVIEW    mFAProf = new zVIEW( );
   //:VIEW mFAAdmin BASED ON LOD  mFAAdmin
   zVIEW    mFAAdmin = new zVIEW( );
   //:VIEW mFATrk   BASED ON LOD  mFATrk
   zVIEW    mFATrk = new zVIEW( );
   //:VIEW mPerson  BASED ON LOD  mPerson
   zVIEW    mPerson = new zVIEW( );
   //:VIEW mSAStu2  BASED ON LOD  mSAStu  
   zVIEW    mSAStu2 = new zVIEW( );
   //:INTEGER nID
   int      nID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:ACTIVATE mYearLST 
   //:   WHERE mYearLST.CollegeYear.ID = nCollegeYearID
   omSAProf_fnLocalBuildQual_2( mSAStu, vTempViewVar_0, nCollegeYearID );
   RESULT = ActivateObjectInstance( mYearLST, "mYear", mSAStu, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:ACTIVATE mSAProf EMPTY 
   RESULT = ActivateEmptyObjectInstance( mSAProf, "mSAProf", mSAStu, zSINGLE );
   //:CREATE ENTITY mSAProf.StudentAccountProfile 
   RESULT = CreateEntity( mSAProf, "StudentAccountProfile", zPOS_AFTER );
   //:IF mSAStu.Student DOES not EXIST 
   lTempInteger_0 = CheckExistenceOfEntity( mSAStu, "Student" );
   if ( lTempInteger_0 != 0 )
   { 
      //:ACTIVATE  mSAStu2    WHERE   mSAStu2.Person.ID = mSAStu.Person.ID 
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, mSAStu, "Person", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      omSAProf_fnLocalBuildQual_3( mSAStu, vTempViewVar_1, lTempInteger_1 );
      RESULT = ActivateObjectInstance( mSAStu2, "mSAStu", mSAStu, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:INCLUDE  mSAProf.Student     FROM mSAStu2.Student 
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "Student", mSAStu2, "Student", zPOS_AFTER );
      //:INCLUDE  mSAProf.ProfileAdministrativeDivision FROM mSAStu2.AdministrativeDivision 
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "ProfileAdministrativeDivision", mSAStu2, "AdministrativeDivision", zPOS_AFTER );
      //:ELSE 
   } 
   else
   { 
      //:INCLUDE  mSAProf.Student     FROM mSAStu.Student 
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "Student", mSAStu, "Student", zPOS_AFTER );
      //:INCLUDE  mSAProf.ProfileAdministrativeDivision FROM mSAStu.AdministrativeDivision 
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "ProfileAdministrativeDivision", mSAStu, "AdministrativeDivision", zPOS_AFTER );
   } 

   //:END
   //:IF mSAStu.Prospect EXISTS 
   lTempInteger_2 = CheckExistenceOfEntity( mSAStu, "Prospect" );
   if ( lTempInteger_2 == 0 )
   { 
      //:mSAProf.StudentAccountProfile.SAPaymentMethod = mSAStu.Prospect.SAPaymentMethod  
      SetAttributeFromAttribute( mSAProf, "StudentAccountProfile", "SAPaymentMethod", mSAStu, "Prospect", "SAPaymentMethod" );
   } 

   //:END

   //:IF mSAStu.AdministrativeDivision.UsesCohortsFlag ="Y" 
   if ( CompareAttributeToString( mSAStu, "AdministrativeDivision", "UsesCohortsFlag", "Y" ) == 0 )
   { 
      //:CREATE ENTITY mSAProf.BillingPeriod 
      RESULT = CreateEntity( mSAProf, "BillingPeriod", zPOS_AFTER );
      //:mSAProf.BillingPeriod.PeriodDesignator = mYearLST.CollegeTerm.YearSemester 
      SetAttributeFromAttribute( mSAProf, "BillingPeriod", "PeriodDesignator", mYearLST, "CollegeTerm", "YearSemester" );
      //:mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeYear.BeginDate  
      SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeYear", "BeginDate" );
      //:mSAProf.BillingPeriod.EndDate = mYearLST.CollegeYear.EndDate  
      SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeYear", "EndDate" );
      //:FOR EACH mYearLST.CollegeTerm 
      RESULT = mYearLST.cursor( "CollegeTerm" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:INCLUDE mSAProf.PeriodCollegeTerm FROM mYearLST.CollegeTerm 
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "PeriodCollegeTerm", mYearLST, "CollegeTerm", zPOS_AFTER );
         RESULT = mYearLST.cursor( "CollegeTerm" ).setNextContinue().toInt();;
      } 

      //:END
      //:ELSE  
   } 
   else
   { 
      //:FOR EACH mYearLST.CollegeTerm 
      RESULT = mYearLST.cursor( "CollegeTerm" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSAProf.BillingPeriod 
         RESULT = CreateEntity( mSAProf, "BillingPeriod", zPOS_AFTER );
         //:mSAProf.BillingPeriod.PeriodDesignator = mYearLST.CollegeTerm.YearSemester 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "PeriodDesignator", mYearLST, "CollegeTerm", "YearSemester" );
         //:IF mYearLST.CollegeTerm.ResidencyStartDate = "" OR mYearLST.CollegeTerm.ResidencyEndDate = ""
         if ( CompareAttributeToString( mYearLST, "CollegeTerm", "ResidencyStartDate", "" ) == 0 || CompareAttributeToString( mYearLST, "CollegeTerm", "ResidencyEndDate", "" ) == 0 )
         { 
            //:mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeTerm.CourseStartDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeTerm", "CourseStartDate" );
            //:mSAProf.BillingPeriod.EndDate   = mYearLST.CollegeTerm.CourseEndDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeTerm", "CourseEndDate" );
            //:ELSE
         } 
         else
         { 
            //:mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeTerm.ResidencyStartDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeTerm", "ResidencyStartDate" );
            //:mSAProf.BillingPeriod.EndDate   = mYearLST.CollegeTerm.ResidencyEndDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeTerm", "ResidencyEndDate" );
         } 

         //:END
         //:INCLUDE mSAProf.PeriodCollegeTerm FROM mYearLST.CollegeTerm 
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "PeriodCollegeTerm", mYearLST, "CollegeTerm", zPOS_AFTER );
         RESULT = mYearLST.cursor( "CollegeTerm" ).setNextContinue().toInt();;
      } 

      //:END
   } 

   //:END
   //:DropView( mYearLST )
   DropView( mYearLST );

   //:COMMIT mSAProf
   RESULT = CommitObjectInstance( mSAProf );
   //:DropView( mSAProf )
   DropView( mSAProf );

   //:IF mSAStu.Student DOES not EXIST 
   lTempInteger_3 = CheckExistenceOfEntity( mSAStu, "Student" );
   if ( lTempInteger_3 != 0 )
   { 
      //:ActivateSAProfForYear( mSAProf, mSAStu2, nCollegeYearID, 0 )
      omSAProf_ActivateSAProfForYear( mSAProf, mSAStu2, nCollegeYearID, 0 );
      //:DropView( mSAStu2 )
      DropView( mSAStu2 );
      //:ELSE 
   } 
   else
   { 
      //:ActivateSAProfForYear( mSAProf, mSAStu, nCollegeYearID, 0 )
      omSAProf_ActivateSAProfForYear( mSAProf, mSAStu, nCollegeYearID, 0 );
   } 

   //:END 
   //:NAME VIEW mSAProf "mSAProf"
   SetNameForView( mSAProf, "mSAProf", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dTotalCreditsPerDisb( VIEW mSAProf BASED ON LOD mSAProf,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )
//:   DECIMAL dAmount 
public int 
omSAProf_dTotalCreditsPerDisb( View     mSAProf,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   double  dAmount = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:dAmount = mSAProf.PeriodCollegeTerm.dCreditsEnrolledCompleted 
         {MutableDouble md_dAmount = new MutableDouble( dAmount );
                   GetDecimalFromAttribute( md_dAmount, mSAProf, "PeriodCollegeTerm", "dCreditsEnrolledCompleted" );
         dAmount = md_dAmount.doubleValue( );}
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GeneratePerCohortClass( VIEW mSAProf      BASED ON LOD mSAProf,
//:                        VIEW mSAClass     BASED ON LOD mSAClass,
//:                        VIEW mSAChrgTList BASED ON LOD mSAChrgT  )
//:   
//:   VIEW mSAChrgITA REGISTERED AS mSAChrgITA
public int 
omSAProf_GeneratePerCohortClass( View     mSAProf,
                                 View     mSAClass,
                                 View     mSAChrgTList )
{
   zVIEW    mSAChrgITA = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSAChrgI   BASED ON LOD mSAChrgI 
   zVIEW    mSAChrgI = new zVIEW( );
   //:DECIMAL dAdjustedAmount
   double  dAdjustedAmount = 0.0;
   //:DECIMAL dTuitionCharge
   double  dTuitionCharge = 0.0;
   //:DECIMAL dOtherCharge
   double  dOtherCharge = 0.0;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   String   szTempString_1 = null;
   int      lTempInteger_4 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   zVIEW    vTempViewVar_3 = new zVIEW( );
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   double  dTempDecimal_2 = 0.0;
   String   szTempString_2 = null;
   int      lTempInteger_7 = 0;

   RESULT = GetViewByName( mSAChrgITA, "mSAChrgITA", mSAProf, zLEVEL_TASK );

   //:// This operation generates the charges that are driven by a Student taking a Cohort Class.
   //:// The charges driven by a Class are:
   //:// 1. Tuition is charged either per credit or a flat amount, based on the Cohort. In either case, the amount
   //://    comes from the Cohort and the SA Transaction is defined by the Program for the Cohort.
   //:// 2. Lab Fee, which is charged as a single amount defined in the Class. The SA Transaction is defined by the 
   //://    Program for the Cohort.
   //:// 3. Directed Study, which is an extra fee which is charged if the Class is marked 'Directed Study'.
   //:// 4. Corporate Discount, which is a discount to the computed Tuition Charge based on a percent in
   //://    the Organization entity.


   //:// SET UP LIST OF TRANSACTION CODES
   //:// Make sure that object containing Charge Transactions has entries, as it is initalized to empty by the caller
   //:// for the first Student for the Class.
   //:// The object needs to have Transactions for the charges listed above.
   //:IF mSAChrgTList.SATransactionCode DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mSAChrgTList, "SATransactionCode" );
   if ( lTempInteger_0 != 0 )
   { 

      //:// 1. Tuition Transaction.
      //:ACTIVATE mSAChrgI WHERE mSAChrgI.SATransactionCode.BillType   = "T"
      //:                     AND mSAChrgI.SATransactionCode.ActiveFlag = ""
      //:                     AND mSAChrgI.Program.ID = mSAClass.Program.ID 
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, mSAClass, "Program", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      omSAProf_fnLocalBuildQual_10( mSAProf, vTempViewVar_0, lTempInteger_1 );
      RESULT = ActivateObjectInstance( mSAChrgI, "mSAChrgI", mSAProf, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:INCLUDE mSAChrgTList.SATransactionCode FROM mSAChrgI.SATransactionCode 
         RESULT = IncludeSubobjectFromSubobject( mSAChrgTList, "SATransactionCode", mSAChrgI, "SATransactionCode", zPOS_AFTER );
         //:DropObjectInstance( mSAChrgI )
         DropObjectInstance( mSAChrgI );
         //:ELSE
      } 
      else
      { 
         //:szMsg = "Tuition Generation Error: The Program, " + mSAClass.Program.Name + ", is not tied to an SA Transaction Code"
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 51, mSAClass, "Program", "Name", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "Tuition Generation Error: The Program, ", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
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
                  ZeidonStringConcat( sb_szMsg, 1, 0, ", is not tied to an SA Transaction Code", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
         //:MessageSend( mSAProf, "", "Generate Cohort Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mSAProf, "", "Generate Cohort Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END

      //:// 2. Lab Fee
      //:ACTIVATE mSAChrgI WHERE mSAChrgI.SATransactionCode.BillType   = "L"
      //:                     AND mSAChrgI.SATransactionCode.ActiveFlag = ""
      //:                     AND mSAChrgI.Program.ID = mSAClass.Program.ID 
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
             GetIntegerFromAttribute( mi_lTempInteger_3, mSAClass, "Program", "ID" );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );}
      omSAProf_fnLocalBuildQual_11( mSAProf, vTempViewVar_1, lTempInteger_3 );
      RESULT = ActivateObjectInstance( mSAChrgI, "mSAChrgI", mSAProf, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:INCLUDE mSAChrgTList.SATransactionCode FROM mSAChrgI.SATransactionCode 
         RESULT = IncludeSubobjectFromSubobject( mSAChrgTList, "SATransactionCode", mSAChrgI, "SATransactionCode", zPOS_AFTER );
         //:DropObjectInstance( mSAChrgI )
         DropObjectInstance( mSAChrgI );
         //:ELSE
      } 
      else
      { 
         //:szMsg = "Lab Fee Generation Error: The Program, " + mSAClass.Program.Name + ", is not tied to an SA Transaction Code"
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
         StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_4, 'S', 51, mSAClass, "Program", "Name", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "Lab Fee Generation Error: The Program, ", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
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
                  ZeidonStringConcat( sb_szMsg, 1, 0, ", is not tied to an SA Transaction Code", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
         //:MessageSend( mSAProf, "", "Generate Cohort Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mSAProf, "", "Generate Cohort Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END

      //:// 3. Directed Study
      //:IF mSAClass.Class.DirectedStudyFlag = "Y"
      if ( CompareAttributeToString( mSAClass, "Class", "DirectedStudyFlag", "Y" ) == 0 )
      { 
         //:ACTIVATE mSAChrgI WHERE mSAChrgI.SATransactionCode.Description = "Directed Study Fee"
         //:                    AND mSAChrgI.SATransactionCode.ActiveFlag = ""
         omSAProf_fnLocalBuildQual_12( mSAProf, vTempViewVar_2 );
         RESULT = ActivateObjectInstance( mSAChrgI, "mSAChrgI", mSAProf, vTempViewVar_2, zSINGLE );
         DropView( vTempViewVar_2 );
         //:IF RESULT >= 0 
         if ( RESULT >= 0 )
         { 
            //:INCLUDE mSAChrgTList.SATransactionCode FROM mSAChrgI.SATransactionCode 
            RESULT = IncludeSubobjectFromSubobject( mSAChrgTList, "SATransactionCode", mSAChrgI, "SATransactionCode", zPOS_AFTER );
            //:DropObjectInstance( mSAChrgI )
            DropObjectInstance( mSAChrgI );
            //:ELSE
         } 
         else
         { 
            //:MessageSend( mSAProf, "", "Generate Charges", 
            //:             "The Class is marked 'Directed Study' but no 'Directed Study' Transaction Code exists.. Processing is aborted.", 
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mSAProf, "", "Generate Charges", "The Class is marked 'Directed Study' but no 'Directed Study' Transaction Code exists.. Processing is aborted.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
      } 

      //:END

      //:// 4. Corporate Discount
      //://    This transaction is only required if one of the Students gets a corporate discount.
      //:ACTIVATE mSAChrgI WHERE mSAChrgI.SATransactionCode.Description = "Corporate Discount"
      //:                    AND mSAChrgI.SATransactionCode.ActiveFlag = ""
      omSAProf_fnLocalBuildQual_13( mSAProf, vTempViewVar_3 );
      RESULT = ActivateObjectInstance( mSAChrgI, "mSAChrgI", mSAProf, vTempViewVar_3, zSINGLE );
      DropView( vTempViewVar_3 );
      //:IF RESULT >= 0 
      if ( RESULT >= 0 )
      { 
         //:INCLUDE mSAChrgTList.SATransactionCode FROM mSAChrgI.SATransactionCode 
         RESULT = IncludeSubobjectFromSubobject( mSAChrgTList, "SATransactionCode", mSAChrgI, "SATransactionCode", zPOS_AFTER );
      } 

      //:END
      //:DropObjectInstance( mSAChrgI )
      DropObjectInstance( mSAChrgI );
   } 

   //:   
   //:END


   //:// Tuition Charge
   //:// 
   //:// The amount charged is the Amount per Credit Hour from the Cohort times the number of credits from the Class OR
   //:// The flat amount specified in the Cohort uses the same PricePerCreditHour attribute.
   //:// The Transaction Code is determined from the SATuitionTransactionCode for the Class
   //:IF mSAClass.Cohort.FlatPrice = "Y" 
   if ( CompareAttributeToString( mSAClass, "Cohort", "FlatPrice", "Y" ) == 0 )
   { 
      //:dTuitionCharge = mSAClass.Cohort.PricePerCreditHour 
      {MutableDouble md_dTuitionCharge = new MutableDouble( dTuitionCharge );
             GetDecimalFromAttribute( md_dTuitionCharge, mSAClass, "Cohort", "PricePerCreditHour" );
      dTuitionCharge = md_dTuitionCharge.doubleValue( );}
      //:ELSE 
   } 
   else
   { 
      //:dTuitionCharge = mSAClass.Class.CreditHours * mSAClass.Cohort.PricePerCreditHour 
      {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
             GetDecimalFromAttribute( md_dTempDecimal_0, mSAClass, "Class", "CreditHours" );
      dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
      {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
             GetDecimalFromAttribute( md_dTempDecimal_1, mSAClass, "Cohort", "PricePerCreditHour" );
      dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
      dTuitionCharge = dTempDecimal_0 * dTempDecimal_1;
   } 

   //:END
   //:SET CURSOR FIRST mSAChrgTList.SATransactionCode WHERE mSAChrgTList.SATransactionCode.BillType = "T"
   RESULT = mSAChrgTList.cursor( "SATransactionCode" ).setFirst( "BillType", "T" ).toInt();
   //:GenFeeForCohortWClass( mSAProf, mSAClass, mSAChrgTList, dTuitionCharge )
   omSAProf_GenFeeForCohortWClass( mSAProf, mSAClass, mSAChrgTList, dTuitionCharge );


   //:// Lab Fee
   //://
   //:// Lab Fee is charged if there is an amount specified for the Class AND if there is an SA Transacion for the Program
   //:// that is a Lab Fee.
   //:IF mSAClass.Class.LabFee != ""
   if ( CompareAttributeToString( mSAClass, "Class", "LabFee", "" ) != 0 )
   { 
      //:SET CURSOR FIRST mSAClass.ProgramSATransactionCode WHERE mSAClass.ProgramSATransactionCode.BillType = "L"
      RESULT = mSAClass.cursor( "ProgramSATransactionCode" ).setFirst( "BillType", "L" ).toInt();
      //:IF RESULT >= zCURSOR_SET 
      if ( RESULT >= zCURSOR_SET )
      { 
         //:SET CURSOR FIRST mSAChrgTList.SATransactionCode WHERE mSAChrgTList.SATransactionCode.ID = mSAClass.ProgramSATransactionCode.ID 
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                   GetIntegerFromAttribute( mi_lTempInteger_5, mSAClass, "ProgramSATransactionCode", "ID" );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );}
         RESULT = mSAChrgTList.cursor( "SATransactionCode" ).setFirst( "ID", lTempInteger_5 ).toInt();
         //:IF RESULT >= zCURSOR_SET 
         if ( RESULT >= zCURSOR_SET )
         { 
            //:dOtherCharge = mSAClass.Class.LabFee
            {MutableDouble md_dOtherCharge = new MutableDouble( dOtherCharge );
                         GetDecimalFromAttribute( md_dOtherCharge, mSAClass, "Class", "LabFee" );
            dOtherCharge = md_dOtherCharge.doubleValue( );}
            //:GenFeeForCohortWClass( mSAProf, mSAClass, mSAChrgTList, dOtherCharge )
            omSAProf_GenFeeForCohortWClass( mSAProf, mSAClass, mSAChrgTList, dOtherCharge );
         } 

         //:END
      } 

      //:END
   } 

   //:END


   //:// Directed Study Fee
   //://
   //:// Directed Study Fee is charged based on flag in Class. Amount comes from the Directed Study Transaction.
   //:IF mSAClass.Class.DirectedStudyFlag = "Y"
   if ( CompareAttributeToString( mSAClass, "Class", "DirectedStudyFlag", "Y" ) == 0 )
   { 
      //:SET CURSOR FIRST mSAChrgTList.SATransactionCode WHERE mSAChrgTList.SATransactionCode.Description = "Directed Study Fee"
      RESULT = mSAChrgTList.cursor( "SATransactionCode" ).setFirst( "Description", "Directed Study Fee" ).toInt();
      //:dOtherCharge = mSAChrgTList.SATransactionCode.DefaultAmount
      {MutableDouble md_dOtherCharge = new MutableDouble( dOtherCharge );
             GetDecimalFromAttribute( md_dOtherCharge, mSAChrgTList, "SATransactionCode", "DefaultAmount" );
      dOtherCharge = md_dOtherCharge.doubleValue( );}
      //:GenFeeForCohortWClass( mSAProf, mSAClass, mSAChrgTList, dOtherCharge )
      omSAProf_GenFeeForCohortWClass( mSAProf, mSAClass, mSAChrgTList, dOtherCharge );
   } 

   //:END  


   //:// Corporate Discount
   //://
   //:// If the Student has an employee relationship with to an Employer (Organization entity) with a discount, apply it here.
   //:// Note that dTuitionCharge computed above is the tuition charge against which the discount applies.
   //:IF mSAProf.Organization EXISTS
   lTempInteger_6 = CheckExistenceOfEntity( mSAProf, "Organization" );
   if ( lTempInteger_6 == 0 )
   { 
      //:IF mSAProf.Organization.TuitionDiscount > 0
      if ( CompareAttributeToInteger( mSAProf, "Organization", "TuitionDiscount", 0 ) > 0 )
      { 
         //:SET CURSOR FIRST mSAChrgTList.SATransactionCode WHERE mSAChrgTList.SATransactionCode.Description = "Corporate Discount" 
         RESULT = mSAChrgTList.cursor( "SATransactionCode" ).setFirst( "Description", "Corporate Discount" ).toInt();
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:NAME VIEW mSAChrgTList "mSAChrgT_LabFee"
            SetNameForView( mSAChrgTList, "mSAChrgT_LabFee", null, zLEVEL_TASK );
            //:dAdjustedAmount = dTuitionCharge * mSAProf.Organization.TuitionDiscount 
            {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                         GetDecimalFromAttribute( md_dTempDecimal_2, mSAProf, "Organization", "TuitionDiscount" );
            dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
            dAdjustedAmount = dTuitionCharge * dTempDecimal_2;
            //:dAdjustedAmount = dAdjustedAmount * -1    // Invert to negative amount
            dAdjustedAmount = dAdjustedAmount * -1;
            //:GenFeeForCohortWClass( mSAProf, mSAClass, mSAChrgTList, dAdjustedAmount )
            omSAProf_GenFeeForCohortWClass( mSAProf, mSAClass, mSAChrgTList, dAdjustedAmount );
            //:ELSE
         } 
         else
         { 
            //:szMsg = "Student, " + mSAClass.EnrolledStudentPerson.dFullNameLFM + ", gets a corporate discount but no 'Corporate Discount' Transaction exists."
            //:                    + NEW_LINE + "No Discount Transaction will be generated."
            {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
            StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_7, 'S', 255, mSAClass, "EnrolledStudentPerson", "dFullNameLFM", "", 0 );
            lTempInteger_7 = mi_lTempInteger_7.intValue( );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringCopy( sb_szMsg, 1, 0, "Student, ", 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
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
                        ZeidonStringConcat( sb_szMsg, 1, 0, ", gets a corporate discount but no 'Corporate Discount' Transaction exists.", 1, 0, 201 );
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
                        ZeidonStringConcat( sb_szMsg, 1, 0, "No Discount Transaction will be generated.", 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
            //:MessageSend( mSAProf, "", "Generate Charges",  szMsg,  zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mSAProf, "", "Generate Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
//:GenFeeForCohortWClass( VIEW mSAProf  BASED ON LOD mSAProf,
//:                       VIEW mSAClass BASED ON LOD mSAClass,
//:                       VIEW mSAChrgT BASED ON LOD mSAChrgT ,
//:                       DECIMAL dAmount )

//:   VIEW wXferO REGISTERED AS wXferO
public int 
omSAProf_GenFeeForCohortWClass( View     mSAProf,
                                View     mSAClass,
                                View     mSAChrgT,
                                double  dAmount )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:INTEGER nTranCodeID
   int      nTranCodeID = 0;
   //:INTEGER nClassID
   int      nClassID = 0;
   //:STRING ( 8 )   szCurrentDate
   String   szCurrentDate = null;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mSAProf, zLEVEL_TASK );

   //:// GENERATE AN SA TRANSACTION FOR A FEE TIED TO A COHORT CLASS.

   //:// Make sure that Debit/Credit Chart Entries exist.
   //:IF mSAChrgT.DebitGLChartEntry DOES NOT EXIST OR mSAChrgT.CreditGLChartEntry DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mSAChrgT, "DebitGLChartEntry" );
   lTempInteger_1 = CheckExistenceOfEntity( mSAChrgT, "CreditGLChartEntry" );
   if ( lTempInteger_0 != 0 || lTempInteger_1 != 0 )
   { 
      //:szMsg = "The Transaction named '" + mSAChrgT.SATransactionCode.Description + "' does not have both a Debit Account and a Credit Account specified."
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 255, mSAChrgT, "SATransactionCode", "Description", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringCopy( sb_szMsg, 1, 0, "The Transaction named '", 1, 0, 201 );
      szMsg = sb_szMsg.toString( );}
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
            ZeidonStringConcat( sb_szMsg, 1, 0, "' does not have both a Debit Account and a Credit Account specified.", 1, 0, 201 );
      szMsg = sb_szMsg.toString( );}
      //:MessageSend( mSAProf, "", "Generate Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mSAProf, "", "Generate Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// See if we already charged for this class
   //:nClassID = mSAClass.Class.ID 
   {MutableInt mi_nClassID = new MutableInt( nClassID );
       GetIntegerFromAttribute( mi_nClassID, mSAClass, "Class", "ID" );
   nClassID = mi_nClassID.intValue( );}
   //:nTranCodeID = mSAChrgT.SATransactionCode.ID
   {MutableInt mi_nTranCodeID = new MutableInt( nTranCodeID );
       GetIntegerFromAttribute( mi_nTranCodeID, mSAChrgT, "SATransactionCode", "ID" );
   nTranCodeID = mi_nTranCodeID.intValue( );}
   //:FOR EACH mSAProf.StudentAccountTransApplied WHERE mSAProf.SATransactionCode.ID = nTranCodeID
   RESULT = mSAProf.cursor( "StudentAccountTransApplied" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToInteger( mSAProf, "SATransactionCode", "ID", nTranCodeID ) == 0 )
      { 
         //:IF mSAProf.TransForClass EXISTS 
         lTempInteger_3 = CheckExistenceOfEntity( mSAProf, "TransForClass" );
         if ( lTempInteger_3 == 0 )
         { 
            //:IF mSAProf.TransForClass.ID = nClassID 
            if ( CompareAttributeToInteger( mSAProf, "TransForClass", "ID", nClassID ) == 0 )
            { 
               //:IF mSAProf.StudentAccountTransApplied.Amount = dAmount 
               if ( CompareAttributeToDecimal( mSAProf, "StudentAccountTransApplied", "Amount", dAmount ) == 0 )
               { 
                  //:RETURN -1
                  if(8==8)return( -1 );
               } 

               //:END
            } 

            //:END
         } 

      } 

      RESULT = mSAProf.cursor( "StudentAccountTransApplied" ).setNextContinue().toInt();;
      //:END
   } 

   //:END 

   //:// Build the Transaction entry.
   //:CREATE ENTITY mSAProf.StudentAccountTransApplied
   RESULT = CreateEntity( mSAProf, "StudentAccountTransApplied", zPOS_AFTER );
   //:INCLUDE mSAProf.SATransactionCode FROM mSAChrgT.SATransactionCode
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "SATransactionCode", mSAChrgT, "SATransactionCode", zPOS_AFTER );
   //://INCLUDE mSAProf.AppliedBillingPeriod  FROM mSAProf.BillingPeriod 
   //:INCLUDE mSAProf.DebitGLChartEntry FROM mSAChrgT.DebitGLChartEntry  
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "DebitGLChartEntry", mSAChrgT, "DebitGLChartEntry", zPOS_AFTER );
   //:INCLUDE mSAProf.CreditGLChartEntry FROM mSAChrgT.CreditGLChartEntry 
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "CreditGLChartEntry", mSAChrgT, "CreditGLChartEntry", zPOS_AFTER );
   //:INCLUDE mSAProf.TransForClass FROM mSAClass.Class 
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "TransForClass", mSAClass, "Class", zPOS_AFTER );
   //:SetMatchingAttributesByName( mSAProf, "StudentAccountTransApplied", 
   //:                             mSAChrgT, "SATransactionCode", zSET_NOTNULL )
   SetMatchingAttributesByName( mSAProf, "StudentAccountTransApplied", mSAChrgT, "SATransactionCode", zSET_NOTNULL );
   //:mSAProf.StudentAccountTransApplied.Amount = dAmount
   SetAttributeFromDecimal( mSAProf, "StudentAccountTransApplied", "Amount", dAmount );

   //:// Add the Class Number to the transaction description.
   //:mSAProf.StudentAccountTransApplied.Description = mSAChrgT.SATransactionCode.Description + ": " + 
   //:                                                 mSAClass.Course.Number
   {StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
       GetStringFromAttribute( sb_szTempString_1, mSAChrgT, "SATransactionCode", "Description" );
   szTempString_1 = sb_szTempString_1.toString( );}
    {StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
      ZeidonStringConcat( sb_szTempString_1, 1, 0, ": ", 1, 0, 255 );
   szTempString_1 = sb_szTempString_1.toString( );}
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
   StringBuilder sb_szTempString_2;
   if ( szTempString_2 == null )
      sb_szTempString_2 = new StringBuilder( 32 );
   else
      sb_szTempString_2 = new StringBuilder( szTempString_2 );
       GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_4, 'S', 11, mSAClass, "Course", "Number", "", 0 );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );
   szTempString_2 = sb_szTempString_2.toString( );}
    {StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
      ZeidonStringConcat( sb_szTempString_1, 1, 0, szTempString_2, 1, 0, 255 );
   szTempString_1 = sb_szTempString_1.toString( );}
   SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szTempString_1 );

   //:// The Transaction Date will be the earlier of today or the Class Start Date.
   //:szCurrentDate = wXferO.Root.dCurrentDate 
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
   StringBuilder sb_szCurrentDate;
   if ( szCurrentDate == null )
      sb_szCurrentDate = new StringBuilder( 32 );
   else
      sb_szCurrentDate = new StringBuilder( szCurrentDate );
       GetVariableFromAttribute( sb_szCurrentDate, mi_lTempInteger_5, 'S', 9, wXferO, "Root", "dCurrentDate", "", 0 );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );
   szCurrentDate = sb_szCurrentDate.toString( );}
   //:mSAProf.StudentAccountTransApplied.DateEntered = szCurrentDate
   SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "DateEntered", szCurrentDate );
   //:IF szCurrentDate > mSAClass.Class.ClassStartDate
   if ( CompareAttributeToString( mSAClass, "Class", "ClassStartDate", szCurrentDate ) < 0 )
   { 
      //:mSAProf.StudentAccountTransApplied.TransactionDate = szCurrentDate 
      SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "TransactionDate", szCurrentDate );
      //:ELSE
   } 
   else
   { 
      //:mSAProf.StudentAccountTransApplied.TransactionDate = mSAClass.Class.ClassStartDate 
      SetAttributeFromAttribute( mSAProf, "StudentAccountTransApplied", "TransactionDate", mSAClass, "Class", "ClassStartDate" );
   } 

   //:END 

   //:// Indicate if the Transaction is to be combined on the statement with other transactions.
   //:IF mSAChrgT.SACombineCode EXISTS  
   lTempInteger_6 = CheckExistenceOfEntity( mSAChrgT, "SACombineCode" );
   if ( lTempInteger_6 == 0 )
   { 
      //:mSAProf.StudentAccountTransApplied.CombineCode = mSAChrgT.SACombineCode.CombineCode 
      SetAttributeFromAttribute( mSAProf, "StudentAccountTransApplied", "CombineCode", mSAChrgT, "SACombineCode", "CombineCode" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GenFeeForCohortWOClass( VIEW mSAProf  BASED ON LOD mSAProf,
//:                        VIEW mSAChrgT BASED ON LOD mSAChrgT ,
//:                        DECIMAL dAmount )
//: 
//:   VIEW wXferO REGISTERED AS wXferO
public int 
omSAProf_GenFeeForCohortWOClass( View     mSAProf,
                                 View     mSAChrgT,
                                 double  dAmount )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:INTEGER nTranCodeID
   int      nTranCodeID = 0;
   //:STRING ( 8 )   szCurrentDate
   String   szCurrentDate = null;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mSAProf, zLEVEL_TASK );

   //:// GENERATE AN SA TRANSACTION FOR A FEE FOR AN ADMIN DIV WITH COHORT BILLING.

   //:// Make sure that Debit/Credit Chart Entries exist.
   //:IF mSAChrgT.DebitGLChartEntry DOES NOT EXIST OR mSAChrgT.CreditGLChartEntry DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mSAChrgT, "DebitGLChartEntry" );
   lTempInteger_1 = CheckExistenceOfEntity( mSAChrgT, "CreditGLChartEntry" );
   if ( lTempInteger_0 != 0 || lTempInteger_1 != 0 )
   { 
      //:szMsg = "The Transaction named '" + mSAChrgT.SATransactionCode.Description + "' does not have both a Debit Account and a Credit Account specified."
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 255, mSAChrgT, "SATransactionCode", "Description", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringCopy( sb_szMsg, 1, 0, "The Transaction named '", 1, 0, 201 );
      szMsg = sb_szMsg.toString( );}
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
            ZeidonStringConcat( sb_szMsg, 1, 0, "' does not have both a Debit Account and a Credit Account specified.", 1, 0, 201 );
      szMsg = sb_szMsg.toString( );}
      //:MessageSend( mSAProf, "", "Generate Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mSAProf, "", "Generate Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Build the Transaction entry.
   //:CREATE ENTITY mSAProf.StudentAccountTransApplied
   RESULT = CreateEntity( mSAProf, "StudentAccountTransApplied", zPOS_AFTER );
   //:INCLUDE mSAProf.SATransactionCode FROM mSAChrgT.SATransactionCode
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "SATransactionCode", mSAChrgT, "SATransactionCode", zPOS_AFTER );
   //://INCLUDE mSAProf.AppliedBillingPeriod  FROM mSAProf.BillingPeriod 
   //:INCLUDE mSAProf.DebitGLChartEntry FROM mSAChrgT.DebitGLChartEntry  
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "DebitGLChartEntry", mSAChrgT, "DebitGLChartEntry", zPOS_AFTER );
   //:INCLUDE mSAProf.CreditGLChartEntry FROM mSAChrgT.CreditGLChartEntry 
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "CreditGLChartEntry", mSAChrgT, "CreditGLChartEntry", zPOS_AFTER );
   //:SetMatchingAttributesByName( mSAProf, "StudentAccountTransApplied", 
   //:                             mSAChrgT, "SATransactionCode", zSET_NOTNULL )
   SetMatchingAttributesByName( mSAProf, "StudentAccountTransApplied", mSAChrgT, "SATransactionCode", zSET_NOTNULL );
   //:mSAProf.StudentAccountTransApplied.Amount = dAmount
   SetAttributeFromDecimal( mSAProf, "StudentAccountTransApplied", "Amount", dAmount );
   //:mSAProf.StudentAccountTransApplied.TransactionDate = wXferO.Root.dCurrentDate
   SetAttributeFromAttribute( mSAProf, "StudentAccountTransApplied", "TransactionDate", wXferO, "Root", "dCurrentDate" );
   //:mSAProf.StudentAccountTransApplied.DateEntered     = wXferO.Root.dCurrentDate
   SetAttributeFromAttribute( mSAProf, "StudentAccountTransApplied", "DateEntered", wXferO, "Root", "dCurrentDate" );

   //:// Indicate if the Transaction is to be combined on the statement with other transactions.
   //:IF mSAChrgT.SACombineCode EXISTS  
   lTempInteger_3 = CheckExistenceOfEntity( mSAChrgT, "SACombineCode" );
   if ( lTempInteger_3 == 0 )
   { 
      //:mSAProf.StudentAccountTransApplied.CombineCode = mSAChrgT.SACombineCode.CombineCode 
      SetAttributeFromAttribute( mSAProf, "StudentAccountTransApplied", "CombineCode", mSAChrgT, "SACombineCode", "CombineCode" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omSAProf_CreateReturningSAOrig( View     mSAProfNew,
                                View     mSAStu,
                                int      OldTermID,
                                int      NewTermID )
{

   //:CreateReturningSAOrig( VIEW mSAProfNew BASED ON LOD mSAProf,
   //:                VIEW mSAStu     BASED ON LOD mSAStu,
   //:                INTEGER OldTermID,
   //:                INTEGER NewTermID )

   //:IssueError( mSAStu, 0,0, "This code is not supposed to be executed." )
   IssueError( mSAStu, 0, 0, "This code is not supposed to be executed." );
   return( 0 );
//    /*VIEW mSAMealP    BASED ON LOD mSAMealP 
//    VIEW mYearLSTOld BASED ON LOD mYear 
//    VIEW mYearLSTNew BASED ON LOD mYear 
//    VIEW mSAProfOld  BASED ON LOD  mSAProf
//    INTEGER nUseOldTerm
//    INTEGER nCollegeYearID
//    INTEGER nRC 
//    STRING (  100  ) szLastMP
//    
//    ACTIVATE mSAMealP MULTIPLE 
//    ACTIVATE mYearLSTOld 
//       WHERE mYearLSTOld.CollegeTerm.ID = OldTermID
//    SET CURSOR FIRST mYearLSTOld.CollegeTerm 
//       WHERE mYearLSTOld.CollegeTerm.ID = OldTermID
//         
//    ACTIVATE mYearLSTNew 
//       WHERE mYearLSTNew.CollegeTerm.ID = NewTermID
//    SET CURSOR FIRST mYearLSTNew.CollegeTerm 
//       WHERE mYearLSTNew.CollegeTerm.ID = NewTermID
//    nCollegeYearID = mYearLSTNew.CollegeYear.ID 
//    
//    mSAProfOld = 0
//    nRC = ActivateSAProfForYear( mSAProfOld, mSAStu, mYearLSTOld.CollegeYear.ID, OldTermID )
//    IF nRC < 0 OR mSAProfOld.StudentAccountProfile DOES NOT EXIST  
//       DropView( mSAProfOld )
//       mSAProfOld = 0
//    END 
//         
//    nRC = ActivateSAProfForYear( mSAProfNew, mSAStu, nCollegeYearID, NewTermID )
//    IF nRC < 0 OR mSAProfNew.StudentAccountProfile DOES NOT EXIST  
//       ACTIVATE mSAProfNew EMPTY 
//       CREATE ENTITY mSAProfNew.StudentAccountProfile 
//       IF mSAProfOld != 0
//          mSAProfNew.StudentAccountProfile.BalanceForward = mSAProfOld.StudentAccountProfile.dBalance 
//          SET CURSOR FIRST mSAProfOld.BillingPeriod 
//             WHERE mSAProfOld.PeriodCollegeTerm.ID = OldTermID // Semester = mYearLSTNew.CollegeTerm.Semester 
//          IF RESULT >= zCURSOR_SET 
//             IF  mSAProfOld.BillingMealPlan  EXISTS   
//             ////////////////////////////////////////////
//                szLastMP = mSAProfOld.BillingMealPlan.Name  
//             
//                IF szLastMP = "10 Meals / Week - Fall"
//                   szLastMP = "10 Meals / Week - Spring"
//                ELSE 
//                IF szLastMP = "10 Meals / Week - Spring"
//                   szLastMP = "10 Meals / Week - Fall"
//                ELSE 
//                IF szLastMP = "10 Meals / Week-Commuter-Fall"
//                   szLastMP = "10 Meals / Week-Commuter-Spring"
//                ELSE 
//                IF szLastMP = "10 Meals / Week-Commuter-Spring"
//                   szLastMP = "10 Meals / Week-Commuter-Fall"
//                ELSE 
//                IF szLastMP = "14 Meals / Week - Fall"
//                   szLastMP = "14 Meals / Week - Spring"
//                ELSE 
//                IF szLastMP = "14 Meals / Week - Spring"
//                   szLastMP = "14 Meals / Week - Fall"
//                ELSE 
//                IF szLastMP = "14 Meals / Week-Commuter-Fall"
//                   szLastMP = "14 Meals / Week-Commuter-Spring"
//                ELSE 
//                IF szLastMP = "14 Meals / Week-Commuter-Spring"
//                   szLastMP = "14 Meals / Week-Commuter-Fall"
//                ELSE 
//                IF szLastMP = "20 Meals / Week"
//                   szLastMP = "20 Meals / Week -Spring"
//                ELSE 
//                IF szLastMP = "20 Meals / Week -Spring"
//                   szLastMP = "20 Meals / Week"
//                ELSE 
//                IF szLastMP = "20 Meals / Week-Commuter-Fall"
//                   szLastMP = "20 Meals / Week-Commuter-Spring"
//                ELSE 
//                IF szLastMP = "20 Meals / Week-Commuter-Spring"
//                   szLastMP = "20 Meals / Week-Commuter-Fall"
//                END END END END END END END END END END END END 
//                
//                SET CURSOR FIRST mSAMealP.MealPlan 
//                   WHERE mSAMealP.MealPlan.Name = szLastMP
//            
//                CREATE ENTITY mSAProfNew.TermOfMealPlan 
//                mSAProfNew.TermOfMealPlan.BeginDate = mYearLSTNew.CollegeTerm.ResidencyStartDate  
//                mSAProfNew.TermOfMealPlan.EndDate =  mYearLSTNew.CollegeTerm.ResidencyEndDate 
//                INCLUDE mSAProfNew.MealPlan FROM mSAMealP.MealPlan 
//             
//             
//             ////////////////////////////////////////////
//             //   CREATE ENTITY mSAProfNew.TermOfMealPlan 
//             //   mSAProfNew.TermOfMealPlan.BeginDate = mYearLSTNew.CollegeTerm.ResidencyStartDate  
//             //   mSAProfNew.TermOfMealPlan.EndDate =  mYearLSTNew.CollegeTerm.ResidencyEndDate 
//             //   INCLUDE mSAProfNew.MealPlan FROM mSAProfOld.BillingMealPlan  
//             END
//          END
//          DropView( mSAProfOld )
//          mSAProfOld = 0
//       END
//       INCLUDE  mSAProfNew.Student     FROM mSAStu.Student 
// //      INCLUDE  mSAProfNew.CollegeYear FROM mYearLSTNew.CollegeYear 
// //      INCLUDE  mSAProfNew.ProfileAdministrativeDivision  FROM mSAStu.AdministrativeDivision 
// //      FOR EACH mSAProfNew.CollegeTerm 
// //         CREATE ENTITY mSAProfNew.BillingPeriod 
// //         mSAProfNew.BillingPeriod.PeriodDesignator = mSAProfNew.CollegeTerm.YearSemester 
// //         mSAProfNew.BillingPeriod.BeginDate = mSAProfNew.CollegeTerm.ResidencyStartDate 
// //         mSAProfNew.BillingPeriod.EndDate = mSAProfNew.CollegeTerm.ResidencyEndDate 
// //         INCLUDE mSAProfNew.PeriodCollegeTerm FROM mSAProfNew.CollegeTerm 
// //      END
//    END
//    SET CURSOR FIRST mSAProfNew.BillingPeriod 
//       WHERE mSAProfNew.PeriodCollegeTerm.ID = NewTermID
//    DropView( mYearLSTNew )
//    DropView( mYearLSTOld )
//    COMMIT   mSAProfNew
//    DropView( mSAProfNew )
//    ActivateSAProfForYear( mSAProfNew, mSAStu, nCollegeYearID, NewTermID )
//    NAME VIEW mSAProfNew "mSAProf"  
//    SET CURSOR FIRST mSAProfNew.BillingPeriod 
//       WHERE mSAProfNew.PeriodCollegeTerm.ID = NewTermID*/
// //IssueError( mSAProfNew, 0, 0, "Check mSAProf for classes under enrolled period." )
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateCharge( VIEW mSAProf BASED ON LOD mSAProf,
//:                INTEGER nTermID,
//:                INTEGER nChargeID,
//:                DECIMAL dAmount,
//:                STRING( 1 )  szUseDefault,
//:                STRING( 128 )  szDesc )

//:   VIEW mSAChrgT   BASED ON LOD mSAChrgT 
public int 
omSAProf_GenerateCharge( View     mSAProf,
                         int      nTermID,
                         int      nChargeID,
                         double  dAmount,
                         String   szUseDefault,
                         String   szDesc )
{
   zVIEW    mSAChrgT = new zVIEW( );
   //:VIEW lSATermLST BASED ON LOD lTermLST
   zVIEW    lSATermLST = new zVIEW( );
   //:VIEW mSARuleS   BASED ON LOD mSARuleS
   zVIEW    mSARuleS = new zVIEW( );
   //:INTEGER nRC 
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:// Build the StudentAccountTransaction entry in mSAProf from the TransactionCode object for
   //:// the College Term and TransactionCode ID's that are passes as parameters in the operation.
   //:// Note that the StudentAccountTransaction created is "temperal" and must be "Accepted" by the caller.

   //:ACTIVATE mSAChrgT  
   //:   WHERE mSAChrgT.SATransactionCode.ID = nChargeID
   //:   RESTRICTING mSAChrgT.SATransactionTerm TO mSAChrgT.CollegeTerm.ID = nTermID
   omSAProf_fnLocalBuildQual_4( mSAProf, vTempViewVar_0, nChargeID, nTermID );
   RESULT = ActivateObjectInstance( mSAChrgT, "mSAChrgT", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:SET CURSOR FIRST mSAProf.BillingPeriod 
   //:           WHERE mSAProf.PeriodCollegeTerm.ID = nTermID
   RESULT = mSAProf.cursor( "BillingPeriod" ).setFirst().toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeTerm", "ID", nTermID ) != 0 ) )
      { 
         RESULT = mSAProf.cursor( "BillingPeriod" ).setNextContinue().toInt();;
      } 

   } 

   //:   
   //:CreateTemporalEntity( mSAProf, "StudentAccountTransApplied", zPOS_AFTER )
   CreateTemporalEntity( mSAProf, "StudentAccountTransApplied", zPOS_AFTER );
   //:INCLUDE mSAProf.SATransactionCode FROM mSAChrgT.SATransactionCode
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "SATransactionCode", mSAChrgT, "SATransactionCode", zPOS_AFTER );
   //:INCLUDE mSAProf.DebitGLChartEntry    FROM mSAChrgT.DebitGLChartEntry  
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "DebitGLChartEntry", mSAChrgT, "DebitGLChartEntry", zPOS_AFTER );
   //:INCLUDE mSAProf.CreditGLChartEntry   FROM mSAChrgT.CreditGLChartEntry 
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "CreditGLChartEntry", mSAChrgT, "CreditGLChartEntry", zPOS_AFTER );
   //:INCLUDE mSAProf.AppliedBillingPeriod FROM mSAProf.BillingPeriod 
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "AppliedBillingPeriod", mSAProf, "BillingPeriod", zPOS_AFTER );
   //:IF dAmount = 0 AND szUseDefault = "Y"
   if ( dAmount == 0 && ZeidonStringCompare( szUseDefault, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:mSAProf.StudentAccountTransApplied.Amount = mSAChrgT.SATransactionTerm.Amount 
      SetAttributeFromAttribute( mSAProf, "StudentAccountTransApplied", "Amount", mSAChrgT, "SATransactionTerm", "Amount" );
      //:ELSE  
   } 
   else
   { 
      //:mSAProf.StudentAccountTransApplied.Amount = dAmount 
      SetAttributeFromDecimal( mSAProf, "StudentAccountTransApplied", "Amount", dAmount );
   } 

   //:END
   //:SetMatchingAttributesByName( mSAProf, "StudentAccountTransApplied", 
   //:                             mSAChrgT, "SATransactionCode", zSET_NOTNULL )
   SetMatchingAttributesByName( mSAProf, "StudentAccountTransApplied", mSAChrgT, "SATransactionCode", zSET_NOTNULL );
   //:IF ( szDesc != "" ) 
   if ( ZeidonStringCompare( szDesc, 1, 0, "", 1, 0, 129 ) != 0 )
   { 
      //:mSAProf.StudentAccountTransApplied.Description = szDesc
      SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szDesc );
   } 

   //:END                             
   //:SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "DateEntered" )
   {
    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
    m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "DateEntered" );
    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
   }
   //:SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "TransactionDate" )
   {
    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
    m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "TransactionDate" );
    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF mSAChrgT.SACombineCode EXISTS  
   lTempInteger_0 = CheckExistenceOfEntity( mSAChrgT, "SACombineCode" );
   if ( lTempInteger_0 == 0 )
   { 
      //:mSAProf.StudentAccountTransApplied.CombineCode = mSAChrgT.SACombineCode.CombineCode 
      SetAttributeFromAttribute( mSAProf, "StudentAccountTransApplied", "CombineCode", mSAChrgT, "SACombineCode", "CombineCode" );
   } 

   //:END
   //:DropView( mSAChrgT )
   DropView( mSAChrgT );
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateChargeCohort( VIEW mSAProf BASED ON LOD mSAProf,
//:                      INTEGER nChargeID,
//:                      DECIMAL dAmount,
//:                      STRING( 1 )  szUseDefault,
//:                      STRING( 128 )  szDesc )

//:   VIEW mSAChrgT   BASED ON LOD mSAChrgT 
public int 
omSAProf_GenerateChargeCohort( View     mSAProf,
                               int      nChargeID,
                               double  dAmount,
                               String   szUseDefault,
                               String   szDesc )
{
   zVIEW    mSAChrgT = new zVIEW( );
   //:VIEW lSATermLST BASED ON LOD lTermLST
   zVIEW    lSATermLST = new zVIEW( );
   //:VIEW mSARuleS   BASED ON LOD mSARuleS
   zVIEW    mSARuleS = new zVIEW( );
   //:INTEGER nRC 
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;


   //:// Same as GenerateCharge above, except with the elimination of Term references.

   //:ACTIVATE mSAChrgT  
   //:   WHERE mSAChrgT.SATransactionCode.ID = nChargeID
   omSAProf_fnLocalBuildQual_5( mSAProf, vTempViewVar_0, nChargeID );
   RESULT = ActivateObjectInstance( mSAChrgT, "mSAChrgT", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:   
   //:CreateTemporalEntity( mSAProf, "StudentAccountTransApplied", zPOS_AFTER )
   CreateTemporalEntity( mSAProf, "StudentAccountTransApplied", zPOS_AFTER );
   //:INCLUDE mSAProf.SATransactionCode FROM mSAChrgT.SATransactionCode
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "SATransactionCode", mSAChrgT, "SATransactionCode", zPOS_AFTER );
   //:INCLUDE mSAProf.DebitGLChartEntry    FROM mSAChrgT.DebitGLChartEntry  
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "DebitGLChartEntry", mSAChrgT, "DebitGLChartEntry", zPOS_AFTER );
   //:INCLUDE mSAProf.CreditGLChartEntry   FROM mSAChrgT.CreditGLChartEntry 
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "CreditGLChartEntry", mSAChrgT, "CreditGLChartEntry", zPOS_AFTER );
   //:mSAProf.StudentAccountTransApplied.Amount = dAmount 
   SetAttributeFromDecimal( mSAProf, "StudentAccountTransApplied", "Amount", dAmount );
   //:SetMatchingAttributesByName( mSAProf, "StudentAccountTransApplied", 
   //:                             mSAChrgT, "SATransactionCode", zSET_NOTNULL )
   SetMatchingAttributesByName( mSAProf, "StudentAccountTransApplied", mSAChrgT, "SATransactionCode", zSET_NOTNULL );
   //:IF ( szDesc != "" ) 
   if ( ZeidonStringCompare( szDesc, 1, 0, "", 1, 0, 129 ) != 0 )
   { 
      //:mSAProf.StudentAccountTransApplied.Description = 
      //:   mSAProf.StudentAccountTransApplied.Description  + "-" + szDesc
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, mSAProf, "StudentAccountTransApplied", "Description" );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
            ZeidonStringConcat( sb_szTempString_0, 1, 0, "-", 1, 0, 255 );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
            ZeidonStringConcat( sb_szTempString_0, 1, 0, szDesc, 1, 0, 255 );
      szTempString_0 = sb_szTempString_0.toString( );}
      SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szTempString_0 );
   } 

   //:END                             
   //:SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "DateEntered" )
   {
    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
    m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "DateEntered" );
    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
   }
   //:SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "TransactionDate" )
   {
    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
    m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "TransactionDate" );
    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF mSAChrgT.SACombineCode EXISTS  
   lTempInteger_0 = CheckExistenceOfEntity( mSAChrgT, "SACombineCode" );
   if ( lTempInteger_0 == 0 )
   { 
      //:mSAProf.StudentAccountTransApplied.CombineCode = mSAChrgT.SACombineCode.CombineCode 
      SetAttributeFromAttribute( mSAProf, "StudentAccountTransApplied", "CombineCode", mSAChrgT, "SACombineCode", "CombineCode" );
   } 

   //:END
   //:DropView( mSAChrgT )
   DropView( mSAChrgT );
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateReverseCharge( VIEW mSAProf BASED ON LOD mSAProf )
//:   VIEW wXferO     REGISTERED AS wXferO
public int 
omSAProf_GenerateReverseCharge( View     mSAProf )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSAProf2   BASED ON LOD  mSAProf
   zVIEW    mSAProf2 = new zVIEW( );
   //:VIEW mBatEdit   BASED ON LOD  mBatEdit
   zVIEW    mBatEdit = new zVIEW( );
   //:VIEW mSAChrgT   BASED ON LOD  mSAChrgT 
   zVIEW    mSAChrgT = new zVIEW( );
   //:VIEW lSATermLST BASED ON LOD  lTermLST
   zVIEW    lSATermLST = new zVIEW( );
   //:VIEW mSARuleS   BASED ON LOD  mSARuleS
   zVIEW    mSARuleS = new zVIEW( );
   //:INTEGER nRC 
   int      nRC = 0;
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   //:STRING ( 18 ) szDate
   String   szDate = null;
   //:STRING ( 4 )  szYear
   String   szYear = null;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mSAProf, zLEVEL_TASK );

   //:szDate = wXferO.Root.dCurrentDate 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szDate;
   if ( szDate == null )
      sb_szDate = new StringBuilder( 32 );
   else
      sb_szDate = new StringBuilder( szDate );
       GetVariableFromAttribute( sb_szDate, mi_lTempInteger_0, 'S', 19, wXferO, "Root", "dCurrentDate", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szDate = sb_szDate.toString( );}
   //:ACTIVATE mBatEdit 
   //:   WHERE mBatEdit.BatchEditList.BatchEditDate = szDate
   //:     AND mBatEdit.BatchEditList.Posted = ""
   omSAProf_fnLocalBuildQual_6( mSAProf, vTempViewVar_0, szDate );
   RESULT = ActivateObjectInstance( mBatEdit, "mBatEdit", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:IF RESULT > 0 
   if ( RESULT > 0 )
   { 
      //:NAME VIEW mBatEdit "mBatEdit" 
      SetNameForView( mBatEdit, "mBatEdit", null, zLEVEL_TASK );
      //:ELSE 
   } 
   else
   { 
      //:ACTIVATE mBatEdit EMPTY 
      RESULT = ActivateEmptyObjectInstance( mBatEdit, "mBatEdit", mSAProf, zSINGLE );
      //:CREATE ENTITY mBatEdit.BatchEditList
      RESULT = CreateEntity( mBatEdit, "BatchEditList", zPOS_AFTER );
      //:mBatEdit.BatchEditList.BatchEditDate = szDate 
      SetAttributeFromString( mBatEdit, "BatchEditList", "BatchEditDate", szDate );
      //:COMMIT mBatEdit
      RESULT = CommitObjectInstance( mBatEdit );
      //:DropView( mBatEdit )
      DropView( mBatEdit );

      //:ACTIVATE mBatEdit 
      //:   WHERE mBatEdit.BatchEditList.BatchEditDate = szDate
      //:     AND mBatEdit.BatchEditList.Posted = ""
      omSAProf_fnLocalBuildQual_7( mSAProf, vTempViewVar_1, szDate );
      RESULT = ActivateObjectInstance( mBatEdit, "mBatEdit", mSAProf, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:NAME VIEW mBatEdit "mBatEdit" 
      SetNameForView( mBatEdit, "mBatEdit", null, zLEVEL_TASK );
   } 

   //:END

   //:CreateViewFromView( mSAProf2, mSAProf )
   CreateViewFromView( mSAProf2, mSAProf );

   //:CREATE ENTITY mSAProf.StudentAccountTransApplied
   RESULT = CreateEntity( mSAProf, "StudentAccountTransApplied", zPOS_AFTER );
   //:INCLUDE mSAProf.SATransactionCode    FROM mSAProf2.SATransactionCode 
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "SATransactionCode", mSAProf2, "SATransactionCode", zPOS_AFTER );
   //:INCLUDE mSAProf.DebitGLChartEntry FROM mSAProf2.DebitGLChartEntry 
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "DebitGLChartEntry", mSAProf2, "DebitGLChartEntry", zPOS_AFTER );
   //:INCLUDE mSAProf.CreditGLChartEntry FROM mSAProf2.CreditGLChartEntry 
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "CreditGLChartEntry", mSAProf2, "CreditGLChartEntry", zPOS_AFTER );
   //:IF mSAProf2.AppliedBillingPeriod EXISTS
   lTempInteger_1 = CheckExistenceOfEntity( mSAProf2, "AppliedBillingPeriod" );
   if ( lTempInteger_1 == 0 )
   { 
      //:INCLUDE mSAProf.AppliedBillingPeriod FROM mSAProf2.AppliedBillingPeriod 
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "AppliedBillingPeriod", mSAProf2, "AppliedBillingPeriod", zPOS_AFTER );
   } 

   //:END
   //:SetMatchingAttributesByName( mSAProf,  "StudentAccountTransApplied", 
   //:                             mSAProf2, "StudentAccountTransApplied", zSET_NOTNULL )
   SetMatchingAttributesByName( mSAProf, "StudentAccountTransApplied", mSAProf2, "StudentAccountTransApplied", zSET_NOTNULL );
   //:mSAProf.StudentAccountTransApplied.DatePosted = ""
   SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "DatePosted", "" );
   //:mSAProf.StudentAccountTransApplied.Posted     = ""
   SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Posted", "" );
   //:mSAProf.StudentAccountTransApplied.Journal    = "ADJ"
   SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Journal", "ADJ" );
   //:mSAProf.StudentAccountTransApplied.DateSentGL = ""
   SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "DateSentGL", "" );
   //:dAmount = mSAProf2.StudentAccountTransApplied.Amount 
   {MutableDouble md_dAmount = new MutableDouble( dAmount );
       GetDecimalFromAttribute( md_dAmount, mSAProf2, "StudentAccountTransApplied", "Amount" );
   dAmount = md_dAmount.doubleValue( );}
   //:dAmount = dAmount * -1
   dAmount = dAmount * -1;
   //:mSAProf.StudentAccountTransApplied.Amount = dAmount 
   SetAttributeFromDecimal( mSAProf, "StudentAccountTransApplied", "Amount", dAmount );

   //:// Tie original Transaction to reversal entry and set date for 1098 generation.
   //:szDate = mSAProf2.StudentAccountTransApplied.TransactionDate
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szDate;
   if ( szDate == null )
      sb_szDate = new StringBuilder( 32 );
   else
      sb_szDate = new StringBuilder( szDate );
       GetVariableFromAttribute( sb_szDate, mi_lTempInteger_2, 'S', 19, mSAProf2, "StudentAccountTransApplied", "TransactionDate", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szDate = sb_szDate.toString( );}
   //:szYear = szDate
    {StringBuilder sb_szYear;
   if ( szYear == null )
      sb_szYear = new StringBuilder( 32 );
   else
      sb_szYear = new StringBuilder( szYear );
      ZeidonStringCopy( sb_szYear, 1, 0, szDate, 1, 0, 5 );
   szYear = sb_szYear.toString( );}
   //:mSAProf.StudentAccountTransApplied.Tx1098_RefundAppliedYear = szYear
   SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Tx1098_RefundAppliedYear", szYear );
   //:INCLUDE mSAProf.AdjustedSATransApplied FROM mSAProf2.StudentAccountTransApplied
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "AdjustedSATransApplied", mSAProf2, "StudentAccountTransApplied", zPOS_AFTER );

   //:SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "DateEntered" )
   {
    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
    m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "DateEntered" );
    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
   }
   //:SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "TransactionDate" )
   {
    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
    m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "TransactionDate" );
    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
   }

   //:DropView( mSAProf2 )
   DropView( mSAProf2 );
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateUpdateCharge( VIEW mSAProf BASED ON LOD mSAProf,
//:                      INTEGER nTermID,
//:                      INTEGER nChargeID,
//:                      DECIMAL dAmount,
//:                      STRING( 1 )   szUseDefault,
//:                      STRING( 128 ) szDesc, 
//:                      STRING( 1 )   szIncludeClass )

//:   VIEW    wXferO     REGISTERED AS wXferO    
public int 
omSAProf_GenerateUpdateCharge( View     mSAProf,
                               int      nTermID,
                               int      nChargeID,
                               double  dAmount,
                               String   szUseDefault,
                               String   szDesc,
                               String   szIncludeClass )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW    mSAChrgT   BASED ON LOD mSAChrgT 
   zVIEW    mSAChrgT = new zVIEW( );
   //:VIEW    lSATermLST BASED ON LOD lTermLST
   zVIEW    lSATermLST = new zVIEW( );
   //:VIEW    mSARuleS   BASED ON LOD mSARuleS
   zVIEW    mSARuleS = new zVIEW( );
   //:VIEW    mSAProf2   BASED ON LOD mSAProf
   zVIEW    mSAProf2 = new zVIEW( );
   //:INTEGER nRC 
   int      nRC = 0;
   //:INTEGER lClassID
   int      lClassID = 0;
   //:INTEGER lTransactionID
   int      lTransactionID = 0;
   //:DECIMAL dCurrentValue
   double  dCurrentValue = 0.0;
   //:DECIMAL dNewValue
   double  dNewValue = 0.0;
   //:DECIMAL dDifferenceValue
   double  dDifferenceValue = 0.0;
   //:DECIMAL dAmountTot
   double  dAmountTot = 0.0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mSAProf, zLEVEL_TASK );

   //:ACTIVATE lSATermLST  
   //:   WHERE lSATermLST.CollegeTerm.ID = nTermID
   omSAProf_fnLocalBuildQual_8( mSAProf, vTempViewVar_0, nTermID );
   RESULT = ActivateObjectInstance( lSATermLST, "lTermLST", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:ACTIVATE mSAChrgT  
   //:   WHERE mSAChrgT.SATransactionCode.ID = nChargeID
   //:   RESTRICTING mSAChrgT.SATransactionTerm TO mSAChrgT.CollegeTerm.ID = nTermID
   omSAProf_fnLocalBuildQual_9( mSAProf, vTempViewVar_1, nChargeID, nTermID );
   RESULT = ActivateObjectInstance( mSAChrgT, "mSAChrgT", mSAProf, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );

   //:IF dAmount= 0 AND szUseDefault = "Y"
   if ( dAmount == 0 && ZeidonStringCompare( szUseDefault, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:// dNewValue = mSAChrgT.SATransactionTerm.Amount 
      //:// if there is a term default, use that second.
      //:IF mSAChrgT.SATransactionTerm EXISTS 
      lTempInteger_0 = CheckExistenceOfEntity( mSAChrgT, "SATransactionTerm" );
      //:      AND mSAChrgT.SATransactionTerm.Amount != 0 
      if ( lTempInteger_0 == 0 && CompareAttributeToInteger( mSAChrgT, "SATransactionTerm", "Amount", 0 ) != 0 )
      { 
         //:dNewValue = mSAChrgT.SATransactionTerm.Amount 
         {MutableDouble md_dNewValue = new MutableDouble( dNewValue );
                   GetDecimalFromAttribute( md_dNewValue, mSAChrgT, "SATransactionTerm", "Amount" );
         dNewValue = md_dNewValue.doubleValue( );}
         //:ELSE 
      } 
      else
      { 
         //:dNewValue = mSAChrgT.SATransactionCode.DefaultAmount 
         {MutableDouble md_dNewValue = new MutableDouble( dNewValue );
                   GetDecimalFromAttribute( md_dNewValue, mSAChrgT, "SATransactionCode", "DefaultAmount" );
         dNewValue = md_dNewValue.doubleValue( );}
      } 

      //:END
      //:ELSE   
   } 
   else
   { 
      //:dNewValue = dAmount 
      dNewValue = dAmount;
   } 

   //:END

   //:// see if this tr code is already in use here 
   //:CreateViewFromView( mSAProf2, mSAProf ) 
   CreateViewFromView( mSAProf2, mSAProf );
   //:dAmountTot = 0
   dAmountTot = 0;
   //://FOR EACH mSAProf2.StudentAccountTransApplied 
   //:FOR EACH mSAProf2.PeriodTransApplied 
   //:      WHERE mSAProf2.PeriodSATransactionCode.ID = nChargeID
   RESULT = mSAProf2.cursor( "PeriodTransApplied" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToInteger( mSAProf2, "PeriodSATransactionCode", "ID", nChargeID ) == 0 )
      { 
         //:IF mSAProf2.PeriodTransApplied.Journal != "ADJ" 
         if ( CompareAttributeToString( mSAProf2, "PeriodTransApplied", "Journal", "ADJ" ) != 0 )
         { 
            //:dAmountTot = dAmountTot + mSAProf2.PeriodTransApplied.Amount 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "PeriodTransApplied", "Amount" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dAmountTot = dAmountTot + dTempDecimal_0;
         } 

      } 

      RESULT = mSAProf2.cursor( "PeriodTransApplied" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:// Update by Don C on 8/15/06 so that entries will be created for different Classes.
   //:IF szIncludeClass = "Y"
   if ( ZeidonStringCompare( szIncludeClass, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:lClassID = 0
      lClassID = 0;
      //:IF mSAProf.PeriodTransClass EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( mSAProf, "PeriodTransClass" );
      if ( lTempInteger_1 == 0 )
      { 
         //:lClassID = mSAProf.PeriodTransClass.ID 
         {MutableInt mi_lClassID = new MutableInt( lClassID );
                   GetIntegerFromAttribute( mi_lClassID, mSAProf, "PeriodTransClass", "ID" );
         lClassID = mi_lClassID.intValue( );}
      } 

      //:END
      //:lTransactionID = 0
      lTransactionID = 0;
      //:IF mSAProf.PeriodSATransactionCode EXISTS
      lTempInteger_2 = CheckExistenceOfEntity( mSAProf, "PeriodSATransactionCode" );
      if ( lTempInteger_2 == 0 )
      { 
         //:lTransactionID = mSAProf.PeriodSATransactionCode.ID 
         {MutableInt mi_lTransactionID = new MutableInt( lTransactionID );
                   GetIntegerFromAttribute( mi_lTransactionID, mSAProf, "PeriodSATransactionCode", "ID" );
         lTransactionID = mi_lTransactionID.intValue( );}
      } 

      //:END
      //:// Class fee entry: exit if no change in amount for same Class.
      //:IF dAmountTot     = dNewValue AND 
      //:   lClassID       = mSAProf.TermClass.ID AND 
      //:   lTransactionID = nChargeID
      if ( dAmountTot == dNewValue && CompareAttributeToInteger( mSAProf, "TermClass", "ID", lClassID ) == 0 && lTransactionID == nChargeID )
      { 

         //:DropView( lSATermLST )
         DropView( lSATermLST );
         //:DropView( mSAChrgT )         
         DropView( mSAChrgT );
         //:DropView( mSAProf2 )         
         DropView( mSAProf2 );
         //:RETURN 1
         if(8==8)return( 1 );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// Regular entry: exit if no change in amount.
      //:IF dAmountTot  = dNewValue  // just skip it
      if ( dAmountTot == dNewValue )
      { 
         //:DropView( lSATermLST )
         DropView( lSATermLST );
         //:DropView( mSAChrgT )         
         DropView( mSAChrgT );
         //:DropView( mSAProf2 )         
         DropView( mSAProf2 );
         //:RETURN 1
         if(8==8)return( 1 );
      } 

      //:END
   } 

   //:END
   //:   
   //:IF dNewValue != 0  
   if ( dNewValue != 0 )
   { 
      //:CreateTemporalEntity( mSAProf, "StudentAccountTransApplied", zPOS_AFTER )
      CreateTemporalEntity( mSAProf, "StudentAccountTransApplied", zPOS_AFTER );
      //:INCLUDE mSAProf.SATransactionCode    FROM mSAChrgT.SATransactionCode
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "SATransactionCode", mSAChrgT, "SATransactionCode", zPOS_AFTER );
      //:INCLUDE mSAProf.DebitGLChartEntry    FROM mSAChrgT.DebitGLChartEntry  
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "DebitGLChartEntry", mSAChrgT, "DebitGLChartEntry", zPOS_AFTER );
      //:INCLUDE mSAProf.CreditGLChartEntry   FROM mSAChrgT.CreditGLChartEntry 
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "CreditGLChartEntry", mSAChrgT, "CreditGLChartEntry", zPOS_AFTER );
      //:INCLUDE mSAProf.AppliedBillingPeriod FROM mSAProf.BillingPeriod 
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "AppliedBillingPeriod", mSAProf, "BillingPeriod", zPOS_AFTER );
      //:IF szIncludeClass = "Y"
      if ( ZeidonStringCompare( szIncludeClass, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:INCLUDE mSAProf.TransForClass FROM mSAProf.TermClass    // Don C correction 8/15/06, changed .Class to .TermClass
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "TransForClass", mSAProf, "TermClass", zPOS_AFTER );
      } 

      //:END 
      //:mSAProf.StudentAccountTransApplied.Amount = dNewValue  
      SetAttributeFromDecimal( mSAProf, "StudentAccountTransApplied", "Amount", dNewValue );

      //:SetMatchingAttributesByName( mSAProf,  "StudentAccountTransApplied", 
      //:                             mSAChrgT, "SATransactionCode", zSET_NOTNULL )
      SetMatchingAttributesByName( mSAProf, "StudentAccountTransApplied", mSAChrgT, "SATransactionCode", zSET_NOTNULL );
      //:IF ( szDesc != "" ) 
      if ( ZeidonStringCompare( szDesc, 1, 0, "", 1, 0, 129 ) != 0 )
      { 
         //:mSAProf.StudentAccountTransApplied.Description = mSAProf.StudentAccountTransApplied.Description  + "-" + szDesc
         {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetStringFromAttribute( sb_szTempString_0, mSAProf, "StudentAccountTransApplied", "Description" );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, "-", 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, szDesc, 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
         SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szTempString_0 );
      } 

      //:END                             
      //:SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "DateEntered" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "DateEntered" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "TransactionDate" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mSAProf, "StudentAccountTransApplied", "TransactionDate" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:IF mSAChrgT.SACombineCode EXISTS  
      lTempInteger_3 = CheckExistenceOfEntity( mSAChrgT, "SACombineCode" );
      if ( lTempInteger_3 == 0 )
      { 
         //:mSAProf.StudentAccountTransApplied.CombineCode = mSAChrgT.SACombineCode.CombineCode 
         SetAttributeFromAttribute( mSAProf, "StudentAccountTransApplied", "CombineCode", mSAChrgT, "SACombineCode", "CombineCode" );
      } 

      //:END
      //:ELSE 
   } 
   else
   { 
      //:DropView( lSATermLST )
      DropView( lSATermLST );
      //:DropView( mSAChrgT )
      DropView( mSAChrgT );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END 
   //:DropView( lSATermLST )
   DropView( lSATermLST );
   //:DropView( mSAChrgT )
   DropView( mSAChrgT );
   return( 0 );
//    
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dDormExtension( VIEW mSAProf BASED ON LOD mSAProf,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   VIEW  mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dDormExtension( View     mSAProf,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:VIEW  wXferO   REGISTERED AS wXferO 
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:STRING (  32  ) szPhone
   String   szPhone = null;
   //:STRING (  32  ) szNow
   String   szNow = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mSAProf, zLEVEL_TASK );

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:szPhone = ""
          {StringBuilder sb_szPhone;
         if ( szPhone == null )
            sb_szPhone = new StringBuilder( 32 );
         else
            sb_szPhone = new StringBuilder( szPhone );
                  ZeidonStringCopy( sb_szPhone, 1, 0, "", 1, 0, 33 );
         szPhone = sb_szPhone.toString( );}
         //:szNow = wXferO.Root.dCurrentDate 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szNow;
         if ( szNow == null )
            sb_szNow = new StringBuilder( 32 );
         else
            sb_szNow = new StringBuilder( szNow );
                   GetVariableFromAttribute( sb_szNow, mi_lTempInteger_0, 'S', 33, wXferO, "Root", "dCurrentDate", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szNow = sb_szNow.toString( );}
         //:OrderEntityForView( mSAProf2, "TermOfResidence", "EndDate D" )
         OrderEntityForView( mSAProf2, "TermOfResidence", "EndDate D" );

         //:SET CURSOR FIRST mSAProf2.TermOfResidence 
         //:   WHERE mSAProf2.TermOfResidence.WithdrawalDate = ""
         //:     AND mSAProf2.TermOfResidence.BeginDate <= szNow
         //:     AND mSAProf2.TermOfResidence.EndDate >= szNow
         RESULT = mSAProf2.cursor( "TermOfResidence" ).setFirst().toInt();
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSAProf2, "TermOfResidence", "WithdrawalDate", "" ) != 0 || CompareAttributeToString( mSAProf2, "TermOfResidence", "BeginDate", szNow ) > 0 ||
                    CompareAttributeToString( mSAProf2, "TermOfResidence", "EndDate", szNow ) < 0 ) )
            { 
               RESULT = mSAProf2.cursor( "TermOfResidence" ).setNextContinue().toInt();;
            } 

         } 

         //:IF RESULT >= zCURSOR_SET 
         if ( RESULT >= zCURSOR_SET )
         { 
            //:IF mSAProf2.OccupantUnit EXISTS  
            lTempInteger_1 = CheckExistenceOfEntity( mSAProf2, "OccupantUnit" );
            if ( lTempInteger_1 == 0 )
            { 
               //:szPhone = mSAProf2.OccupantUnit.PhoneNo 
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szPhone;
               if ( szPhone == null )
                  sb_szPhone = new StringBuilder( 32 );
               else
                  sb_szPhone = new StringBuilder( szPhone );
                               GetVariableFromAttribute( sb_szPhone, mi_lTempInteger_2, 'S', 33, mSAProf2, "OccupantUnit", "PhoneNo", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szPhone = sb_szPhone.toString( );}
            } 

            //:END
         } 

         //:END 
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreStringInRecord( mSAProf, InternalEntityStructure, 
         //:                     InternalAttribStructure, szPhone )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szPhone );
         break ;
      //:   /* end zDERIVED_GET */

      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

   //:   /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:LocateCurrentRoom( VIEW mSAProf BASED ON LOD mSAProf )
//:   VIEW mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_LocateCurrentRoom( View     mSAProf )
{
   zVIEW    mSAProf2 = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;


   //:IF mSAProf.TermOfResidence DOES not EXIST 
   lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "TermOfResidence" );
   if ( lTempInteger_0 != 0 )
   { 
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   //:IF mSAProf.PeriodCollegeTerm DOES not EXIST 
   lTempInteger_1 = CheckExistenceOfEntity( mSAProf, "PeriodCollegeTerm" );
   if ( lTempInteger_1 != 0 )
   { 
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:CreateViewFromView ( mSAProf2, mSAProf )
   CreateViewFromView( mSAProf2, mSAProf );
   //:FOR EACH mSAProf2.BillingTermOfResidence 
   RESULT = mSAProf2.cursor( "BillingTermOfResidence" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:EXCLUDE  mSAProf2.BillingTermOfResidence NONE  
      RESULT = ExcludeEntity( mSAProf2, "BillingTermOfResidence", zREPOS_NONE );
      RESULT = mSAProf2.cursor( "BillingTermOfResidence" ).setNextContinue().toInt();;
   } 

   //:END

   //:FOR EACH mSAProf2.TermOfResidence 
   RESULT = mSAProf2.cursor( "TermOfResidence" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSAProf2.TermOfResidence.WithdrawalDate = "" 
      if ( CompareAttributeToString( mSAProf2, "TermOfResidence", "WithdrawalDate", "" ) == 0 )
      { 
         //:IF mSAProf2.TermOfResidence.BeginDate >= mSAProf2.PeriodCollegeTerm.ResidencyStartDate AND
         //:   mSAProf2.TermOfResidence.EndDate   <= mSAProf2.PeriodCollegeTerm.ResidencyEndDate 
         if ( CompareAttributeToAttribute( mSAProf2, "TermOfResidence", "BeginDate", mSAProf2, "PeriodCollegeTerm", "ResidencyStartDate" ) >= 0 &&
              CompareAttributeToAttribute( mSAProf2, "TermOfResidence", "EndDate", mSAProf2, "PeriodCollegeTerm", "ResidencyEndDate" ) <= 0 )
         { 

            //:INCLUDE mSAProf.BillingTermOfResidence  FROM mSAProf2.TermOfResidence 
            RESULT = IncludeSubobjectFromSubobject( mSAProf, "BillingTermOfResidence", mSAProf2, "TermOfResidence", zPOS_AFTER );
            //:DropView( mSAProf2 )
            DropView( mSAProf2 );
            //:RETURN 0
            if(8==8)return( 0 );
            //:ELSE 
         } 
         else
         { 
            //:// if the res period begin and end dates fall within the res date then 
            //:// they must already be living there. 
            //:IF mSAProf2.PeriodCollegeTerm.ResidencyStartDate >= mSAProf2.TermOfResidence.BeginDate AND
            //:   mSAProf2.PeriodCollegeTerm.ResidencyStartDate <= mSAProf2.TermOfResidence.EndDate
            if ( CompareAttributeToAttribute( mSAProf2, "PeriodCollegeTerm", "ResidencyStartDate", mSAProf2, "TermOfResidence", "BeginDate" ) >= 0 &&
                 CompareAttributeToAttribute( mSAProf2, "PeriodCollegeTerm", "ResidencyStartDate", mSAProf2, "TermOfResidence", "EndDate" ) <= 0 )
            { 

               //:INCLUDE mSAProf.BillingTermOfResidence  FROM mSAProf2.TermOfResidence 
               RESULT = IncludeSubobjectFromSubobject( mSAProf, "BillingTermOfResidence", mSAProf2, "TermOfResidence", zPOS_AFTER );
               //:DropView( mSAProf2 )
               DropView( mSAProf2 );
               //:RETURN 0
               if(8==8)return( 0 );
            } 

            //:END
         } 

         //:END 
      } 

      RESULT = mSAProf2.cursor( "TermOfResidence" ).setNextContinue().toInt();;
      //:END 
   } 

   //:END
   //:IF mSAProf.BillingTermOfResidence  DOES not EXIST
   lTempInteger_2 = CheckExistenceOfEntity( mSAProf, "BillingTermOfResidence" );
   if ( lTempInteger_2 != 0 )
   { 
      //:OrderEntityForView( mSAProf2, "TermOfResidence", "BeginDate D" )
      OrderEntityForView( mSAProf2, "TermOfResidence", "BeginDate D" );
      //:SET CURSOR FIRST mSAProf2.TermOfResidence 
      RESULT = mSAProf2.cursor( "TermOfResidence" ).setFirst().toInt();
      //:IF RESULT >= zCURSOR_SET 
      if ( RESULT >= zCURSOR_SET )
      { 
         //:INCLUDE mSAProf.BillingTermOfResidence  FROM mSAProf2.TermOfResidence 
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "BillingTermOfResidence", mSAProf2, "TermOfResidence", zPOS_AFTER );
         //:DropView( mSAProf2 )
         DropView( mSAProf2 );
         //:RETURN 0
         if(8==8)return( 0 );
      } 

      //:END
   } 

   //:END
   //:DropView( mSAProf2 )
   DropView( mSAProf2 );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omSAProf_LocateMealPlan( View     mSAProf )
{
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;

   //:LocateMealPlan( VIEW mSAProf BASED ON LOD mSAProf )

   //:IF mSAProf.TermOfMealPlan DOES not EXIST 
   lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "TermOfMealPlan" );
   if ( lTempInteger_0 != 0 )
   { 
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END 

   //:IF mSAProf.PeriodCollegeTerm DOES not EXIST 
   lTempInteger_1 = CheckExistenceOfEntity( mSAProf, "PeriodCollegeTerm" );
   if ( lTempInteger_1 != 0 )
   { 
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:FOR EACH mSAProf.BillingTermOfMealPlan  
   RESULT = mSAProf.cursor( "BillingTermOfMealPlan" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:EXCLUDE  mSAProf.BillingTermOfMealPlan NONE  
      RESULT = ExcludeEntity( mSAProf, "BillingTermOfMealPlan", zREPOS_NONE );
      RESULT = mSAProf.cursor( "BillingTermOfMealPlan" ).setNextContinue().toInt();;
   } 

   //:END

   //:// Skip this code if the Student has a Meal Plan waiver.
   //:IF mSAProf.StudentAccountProfile.MealPlanWaiver = "Y"
   if ( CompareAttributeToString( mSAProf, "StudentAccountProfile", "MealPlanWaiver", "Y" ) == 0 )
   { 
      //:RETURN 
      if(8==8)return( 0 );
   } 

   //:END

   //:// Note that this operation assumes we are positioned on the correct Billing Period.

   //:// We will position on the latest TermOfMealPlan entity that falls within the Residency
   //:// Start/End dates.
   //:// First order the entries in reverse date order.
   //:OrderEntityForView( mSAProf, "TermOfMealPlan", "BeginDate D" )
   OrderEntityForView( mSAProf, "TermOfMealPlan", "BeginDate D" );
   //:SET CURSOR FIRST mSAProf.TermOfMealPlan 
   //:           WHERE mSAProf.TermOfMealPlan.BeginDate >= mSAProf.PeriodCollegeTerm.ResidencyStartDate 
   //:             AND mSAProf.TermOfMealPlan.BeginDate <= mSAProf.PeriodCollegeTerm.ResidencyEndDate 
   //:             AND mSAProf.TermOfMealPlan.WithdrawalDate = ""
   RESULT = mSAProf.cursor( "TermOfMealPlan" ).setFirst().toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSAProf, "TermOfMealPlan", "BeginDate", mSAProf, "PeriodCollegeTerm", "ResidencyStartDate" ) < 0 ||
              CompareAttributeToAttribute( mSAProf, "TermOfMealPlan", "BeginDate", mSAProf, "PeriodCollegeTerm", "ResidencyEndDate" ) > 0 || CompareAttributeToString( mSAProf, "TermOfMealPlan", "WithdrawalDate", "" ) != 0 ) )
      { 
         RESULT = mSAProf.cursor( "TermOfMealPlan" ).setNextContinue().toInt();;
      } 

   } 

   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:INCLUDE mSAProf.BillingTermOfMealPlan FROM mSAProf.TermOfMealPlan
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "BillingTermOfMealPlan", mSAProf, "TermOfMealPlan", zPOS_AFTER );
   } 

   //:END
   //:SET CURSOR FIRST mSAProf.TermOfMealPlan
   RESULT = mSAProf.cursor( "TermOfMealPlan" ).setFirst().toInt();
   return( 0 );
//    
//    /*FOR EACH mSAProf2.TermOfMealPlan  
//       IF mSAProf2.TermOfMealPlan.WithdrawalDate = "" 
//          IF mSAProf2.TermOfMealPlan.BeginDate >= mSAProf2.PeriodCollegeTerm.ResidencyStartDate 
//                AND ( mSAProf2.TermOfMealPlan.EndDate <= mSAProf2.PeriodCollegeTerm.ResidencyEndDate 
//                 OR mSAProf2.TermOfMealPlan.EndDate = "" )
//             INCLUDE mSAProf.BillingTermOfMealPlan FROM mSAProf2.TermOfMealPlan
//             DropView( mSAProf2 )
//             RETURN 0
//          ELSE 
//             IF mSAProf2.PeriodCollegeTerm.ResidencyStartDate >= mSAProf2.TermOfMealPlan.BeginDate 
//                   AND mSAProf2.PeriodCollegeTerm.ResidencyStartDate <= mSAProf2.TermOfMealPlan.EndDate
//                INCLUDE mSAProf.BillingTermOfMealPlan FROM mSAProf2.TermOfMealPlan
//                DropView( mSAProf2 )
//                RETURN 0 
//             END
//          END 
//       END 
//    END*/
//    
// END
} 


//:TRANSFORMATION OPERATION
//:CreateReturningSA( VIEW mSAProf  BASED ON LOD mSAProf,
//:                   VIEW mSAProfR BASED ON LOD mSAProfR )
public int 
omSAProf_CreateReturningSA( View     mSAProf,
                            View     mSAProfR )
{

   return( 0 );
//    /*VIEW mSAMealP    BASED ON LOD mSAMealP 
//    VIEW mYearLSTOld BASED ON LOD mYear 
//    VIEW mYearLSTNew BASED ON LOD mYear 
//    VIEW mSAProfOld  BASED ON LOD mSAProf
//    VIEW wXferO      REGISTERED AS wXferO
//    INTEGER nUseOldTerm
//    INTEGER nCollegeYearID
//    INTEGER nRC 
//    INTEGER OldTermID
//    INTEGER NewTermID
//    DECIMAL dBalanceForward
//    STRING (  100  ) szLastMP
//    
//    GET VIEW mSAMealP    NAMED "mSAMealP"
//    GET VIEW mYearLSTOld NAMED "lSATermLSTCurrent" //"mYearCurrent"
//    GET VIEW mYearLSTNew NAMED "lSATermLSTNew"
//    CreateViewFromView( mSAProfOld, mSAProf ) 
//    
//    // Set up BillingPeriod derived entities for the new/old Profiles, including entities TermEnrolled, 
//    // BillingTermOfResidence and BillingTermOfMealPlan.
//    SetupSAProfForYear( mSAProfOld, mYearLSTOld.CollegeYear.ID, mYearLSTOld.CollegeTerm.ID )
//    SetupSAProfForYear( mSAProf, mYearLSTNew.CollegeYear.ID, mYearLSTNew.CollegeTerm.ID )
//    
//    nCollegeYearID  = mYearLSTNew.CollegeYear.ID 
//    OldTermID       = mYearLSTOld.CollegeTerm.ID
//    dBalanceForward = mSAProfOld.StudentAccountProfile.dBalance 
//    
//    // Create Meal Plan, Billing Period and Historical Profile information as needed, but only if there
//    // is a BillingPeriod in the Profile for the original College Term (OldTermID).
//    
//    SET CURSOR FIRST mSAProfOld.BillingPeriod WHERE mSAProfOld.PeriodCollegeTerm.ID = OldTermID
//    IF RESULT >= zCURSOR_SET 
//       IF mSAProfOld.BillingMealPlan  EXISTS
//       
//          // CREATE MEAL PLAN FOR THE NEW COLLEGE TERM
//          //                     (Note that a TermOfMealPlan is always created. We don't first look to see if there
//          //                      is one by the same name and Term.)
//       
//          ////////////////////////////////////////////
//          szLastMP = mSAProfOld.BillingMealPlan.Name  
//             
//          IF szLastMP = "10 Meals / Week - Fall"
//             szLastMP = "10 Meals / Week - Spring"
//          ELSE 
//          IF szLastMP = "10 Meals / Week - Spring"
//             szLastMP = "10 Meals / Week - Fall"
//          ELSE 
//          IF szLastMP = "10 Meals / Week-Commuter-Fall"                        
//             szLastMP = "10 Meals / Week-Commuter-Spring"
//          ELSE 
//          IF szLastMP = "10 Meals / Week-Commuter-Spring"
//             szLastMP = "10 Meals / Week-Commuter-Fall"                        
//          ELSE 
//          IF szLastMP = "10 Meal Block Plan - Fall"
//             szLastMP = "10 Meal Block Plan - Spring"
//          ELSE 
//          IF szLastMP = "10 Meal Block Plan - Spring"
//             szLastMP = "10 Meal Block Plan - Fall"
//          ELSE 
//          IF szLastMP = "14 Meals / Week - Fall"
//             szLastMP = "14 Meals / Week - Spring"
//          ELSE 
//          IF szLastMP = "14 Meals / Week - Spring"
//             szLastMP = "14 Meals / Week - Fall"
//          ELSE 
//          IF szLastMP = "14 Meals / Week-Commuter-Fall"
//             szLastMP = "14 Meals / Week-Commuter-Spring"
//          ELSE 
//          IF szLastMP = "14 Meals / Week-Commuter-Spring"
//             szLastMP = "14 Meals / Week-Commuter-Fall"
//          ELSE 
//          IF szLastMP = "14 Meal Block Plan - Spring"
//             szLastMP = "14 Meal Block Plan - Fall"
//          ELSE 
//          IF szLastMP = "14 Meal Block Plan - Fall"
//             szLastMP = "14 Meal Block Plan - Spring"
//          ELSE 
//          IF szLastMP = "20 Meals / Week"
//             szLastMP = "20 Meals / Week -Spring"
//          ELSE 
//          IF szLastMP = "20 Meals / Week -Spring"
//             szLastMP = "20 Meals / Week"
//          ELSE 
//          IF szLastMP = "20 Meals / Week-Commuter-Fall"
//             szLastMP = "20 Meals / Week-Commuter-Spring"
//          ELSE 
//          IF szLastMP = "20 Meals / Week-Commuter-Spring"
//             szLastMP = "20 Meals / Week-Commuter-Fall"
//          ELSE 
//          IF szLastMP = "20 Meal Block Plan - Fall"
//             szLastMP = "20 Meal Block Plan - Spring"
//          ELSE 
//          IF szLastMP = "20 Meal Block Plan - Spring"
//             szLastMP = "20 Meal Block Plan - Fall"
//          END END END END END END END END END END END END END END END END END END  
//                
//          SET CURSOR FIRST mSAMealP.MealPlan WHERE mSAMealP.MealPlan.Name = szLastMP
//            
//          CREATE ENTITY mSAProf.TermOfMealPlan 
//          mSAProf.TermOfMealPlan.BeginDate = mYearLSTNew.CollegeTerm.ResidencyStartDate  
//          mSAProf.TermOfMealPlan.EndDate =  mYearLSTNew.CollegeTerm.ResidencyEndDate 
//          INCLUDE mSAProf.MealPlan FROM mSAMealP.MealPlan 
//          DropView( mSAProfOld )
//          mSAProfOld = 0
//       END
//       
//       // CREATE BILLING PERIODS AS NECESSARY FOR EACH TERM FOR THE NEW COLLEGE YEAR.
//       
//       // Make sure that Residency Start/End Dates exist in College Term.
//       ???
//       
//       FOR EACH mYearLSTNew.CollegeTerm 
//          SET CURSOR FIRST mSAProf.BillingPeriod WHERE mSAProf.PeriodCollegeTerm.ID = mYearLSTNew.CollegeTerm.ID 
//          IF  RESULT < zCURSOR_SET  
//             CREATE ENTITY mSAProf.BillingPeriod 
//             mSAProf.BillingPeriod.PeriodDesignator = mYearLSTNew.CollegeTerm.YearSemester 
//             mSAProf.BillingPeriod.BeginDate = mYearLSTNew.CollegeTerm.ResidencyStartDate 
//             mSAProf.BillingPeriod.EndDate = mYearLSTNew.CollegeTerm.ResidencyEndDate 
//             INCLUDE mSAProf.PeriodCollegeTerm FROM mYearLSTNew.CollegeTerm
//          END 
//       END
//       
//       // CREATE A HISTORICAL PROFILE AS NECESSARY FOR THE OLD COLLEGE YEAR
//       
//       SET CURSOR FIRST mSAProf.StudentAccountHistoricalProfile WHERE mSAProf.HistoricalCollegeYear.ID = mYearLSTOld.CollegeYear.ID 
//       IF RESULT < zCURSOR_SET 
//          CREATE ENTITY  mSAProf.StudentAccountHistoricalProfile 
//          INCLUDE mSAProf.HistoricalCollegeYear FROM   mYearLSTOld.CollegeYear 
//          SetMatchingAttributesByName( mSAProf, "StudentAccountHistoricalProfile", 
//                                       mSAProf, "StudentAccountProfile", zSET_NOTNULL )
//          //mSAProf.StudentAccountHistoricalProfile.CollegeYear = mSAProf.HistoricalCollegeYear.Year 
//          //mSAProf.StudentAccountProfile.BalanceForward = 0 
//          //mSAProf.StudentAccountProfile.BalanceForward = mSAProf.StudentAccountProfile.dBalance - dBalanceForward 
//          //mSAProf.StudentAccountProfile.BalanceForwardDate = wXferO.Root.dCurrentDate 
//          mSAProf.StudentAccountProfile.HealthInsuranceWaiver = ""
//          mSAProf.StudentAccountProfile.HealthInsuranceNote = ""
//          mSAProf.StudentAccountProfile.NoInterest = ""
//          mSAProf.StudentAccountProfile.PaymentPlanProvider = ""
//          mSAProf.StudentAccountProfile.PaymentContractSent = ""
//          mSAProf.StudentAccountProfile.PaymentContractReceived = ""
//          mSAProf.StudentAccountProfile.PaymentPlanFA = ""
//          mSAProf.StudentAccountProfile.PaymentPlanSL = ""
//          mSAProf.StudentAccountProfile.PaymentPlanPP = ""
//          mSAProf.StudentAccountProfile.PaymentPlanOL = ""
//          mSAProf.StudentAccountProfile.PaymentPlanCK = ""
//          mSAProf.StudentAccountProfile.PaymentPlanBeginDate = ""
//          mSAProf.StudentAccountProfile.PaymentPlanEndDate = ""
//          mSAProf.StudentAccountProfile.MealPlanWaiver = ""
//          mSAProf.StudentAccountProfile.MealPlanWaiverDate = ""
//          mSAProf.StudentAccountProfile.FinAidYearBegin = ""
//          mSAProf.StudentAccountProfile.FinAidYearEnd = ""
//          mSAProf.StudentAccountProfile.BillingNeedsUpdated = ""
//          mSAProf.StudentAccountProfile.Cleared = ""
//          mSAProf.StudentAccountProfile.PaymentPlanPL = ""
//          mSAProf.StudentAccountProfile.PaymentPlanOLCompany = ""
//          mSAProf.StudentAccountProfile.HasVehicle = ""
//          mSAProf.StudentAccountProfile.VehicleRegistrationNumber = ""
//          mSAProf.StudentAccountProfile.SAPaymentMethod = ""
//       END
//    END
//    
//    //COMMIT mSAProf
//    SET CURSOR FIRST mSAProf.BillingPeriod WHERE mSAProf.PeriodCollegeTerm.ID = NewTermID*/
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dStudentFullName( VIEW mSAProf BASED ON LOD mSAProf,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING ( 100 ) szFullName
public int 
omSAProf_dStudentFullName( View     mSAProf,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   String   szFullName = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetPersonFullName( szFullName, mSAProf, "Person" )
         {
          ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mSAProf );
          {StringBuilder sb_szFullName;
         if ( szFullName == null )
            sb_szFullName = new StringBuilder( 32 );
         else
            sb_szFullName = new StringBuilder( szFullName );
                   m_ZGLOBAL2_Operation.GetPersonFullName( sb_szFullName, mSAProf, "Person" );
         szFullName = sb_szFullName.toString( );}
          // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
         }

         //:StoreStringInRecord( mSAProf,
         //:                  InternalEntityStructure, InternalAttribStructure, szFullName )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szFullName );
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
//:dAddressLabelFull( VIEW mSAProf BASED ON LOD mSAProf,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   SHORT nRC
public int 
omSAProf_dAddressLabelFull( View     mSAProf,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   int      nRC = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:nRC = dAdressLabelFull( mSAProf /* vAnyObject */,
         //:   InternalEntityStructure /* szInternalEntityStructure */,
         //:   InternalAttribStructure /* szInternalAttribStructure */,
         //:   GetOrSetFlag /* nGetOrSetFlag */ )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
          nRC = m_ZGLOBAL1_Operation.dAdressLabelFull( mSAProf, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
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
//:dDisplayAccountName( VIEW mSAProf BASED ON LOD mSAProf,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   STRING ( 100 ) szAccountName
public int 
omSAProf_dDisplayAccountName( View     mSAProf,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szAccountName = null;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:szAccountName = mSAProf.Person.dFullName
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szAccountName;
         if ( szAccountName == null )
            sb_szAccountName = new StringBuilder( 32 );
         else
            sb_szAccountName = new StringBuilder( szAccountName );
                   GetVariableFromAttribute( sb_szAccountName, mi_lTempInteger_0, 'S', 101, mSAProf, "Person", "dFullName", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szAccountName = sb_szAccountName.toString( );}
         //:StoreStringInRecord( mSAProf,
         //:                     InternalEntityStructure, InternalAttribStructure, szAccountName )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szAccountName );
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
omSAProf_dFullNameLFM( View     mSAProf,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{

   //:dFullNameLFM( VIEW mSAProf BASED ON LOD mSAProf,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_LastFirstMiddle( mSAProf, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
          m_ZGLOBAL1_Operation.PersonName_LastFirstMiddle( mSAProf, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
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
public int 
omSAProf_dFullNameFML( View     mSAProf,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{

   //:dFullNameFML( VIEW mSAProf BASED ON LOD mSAProf,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_FirstMiddleLast( mSAProf, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
          m_ZGLOBAL1_Operation.PersonName_FirstMiddleLast( mSAProf, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
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


//:TRANSFORMATION OPERATION
//:CheckExistingCharges( VIEW mSAProf BASED ON LOD mSAProf )
//:   VIEW mSAProf2     BASED ON LOD mSAProf
public int 
omSAProf_CheckExistingCharges( View     mSAProf )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:VIEW mSAProfRev   BASED ON LOD mSAProf
   zVIEW    mSAProfRev = new zVIEW( );
   //:VIEW mPerson      REGISTERED AS mPerson
   zVIEW    mPerson = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSAChrgIT    REGISTERED AS mSAChrgIT
   zVIEW    mSAChrgIT = new zVIEW( );
   //:VIEW mSAChrgITA   REGISTERED AS mSAChrgITA
   zVIEW    mSAChrgITA = new zVIEW( );
   //:VIEW mSAChrgITC   REGISTERED AS mSAChrgITC
   zVIEW    mSAChrgITC = new zVIEW( );
   //:VIEW mSAChrgITF   REGISTERED AS mSAChrgITF
   zVIEW    mSAChrgITF = new zVIEW( );
   //:VIEW wXferO       REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   //:VIEW mSAChrgMP    BASED ON LOD  mSAChrgI
   zVIEW    mSAChrgMP = new zVIEW( );
   //:VIEW mSARuleS     BASED ON LOD  mSARuleS
   zVIEW    mSARuleS = new zVIEW( );
   //:INTEGER nTransCodeID 
   int      nTransCodeID = 0;
   //:INTEGER nDone
   int      nDone = 0;
   //:INTEGER nRC
   int      nRC = 0;
   //:DECIMAL dTotalCharged
   double  dTotalCharged = 0.0;
   //:DECIMAL dTotalPos
   double  dTotalPos = 0.0;
   //:DECIMAL dAmount
   double  dAmount = 0.0;
   //:STRING ( 32 ) szRuleName
   String   szRuleName = null;
   //:STRING ( 32 ) szSrcName
   String   szSrcName = null;
   //:STRING ( 32 ) szNumber
   String   szNumber = null;
   //:STRING ( 1 )  szMealPlanFlag
   String   szMealPlanFlag = null;
   //:STRING ( 1 )  szResidencyFlag
   String   szResidencyFlag = null;
   //:STRING ( 1 )  szLabFeeRefund
   String   szLabFeeRefund = null;
   //:STRING ( 8 )  szLatestDroppedDate
   String   szLatestDroppedDate = null;
   //:STRING ( 8 )  szDroppedClassStartDate
   String   szDroppedClassStartDate = null;
   //:STRING ( 300 ) szMsg
   String   szMsg = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_13 = 0;
   int      lTempInteger_14 = 0;
   int      lTempInteger_15 = 0;
   int      lTempInteger_16 = 0;
   String   szTempString_2 = null;
   String   szTempString_3 = null;
   int      lTempInteger_17 = 0;
   int      lTempInteger_18 = 0;
   int      lTempInteger_19 = 0;
   int      lTempInteger_20 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   int      lTempInteger_21 = 0;
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   int      lTempInteger_22 = 0;
   String   szTempString_6 = null;
   int      lTempInteger_23 = 0;
   int      lTempInteger_24 = 0;
   int      lTempInteger_25 = 0;
   int      lTempInteger_26 = 0;
   String   szTempString_7 = null;
   int      lTempInteger_27 = 0;
   int      lTempInteger_28 = 0;
   int      lTempInteger_29 = 0;
   int      lTempInteger_30 = 0;
   String   szTempString_8 = null;
   String   szTempString_9 = null;
   int      lTempInteger_31 = 0;
   String   szTempString_10 = null;
   int      lTempInteger_32 = 0;
   int      lTempInteger_33 = 0;
   int      lTempInteger_34 = 0;
   int      lTempInteger_35 = 0;
   int      lTempInteger_36 = 0;
   String   szTempString_11 = null;
   String   szTempString_12 = null;
   int      lTempInteger_37 = 0;
   String   szTempString_13 = null;
   int      lTempInteger_38 = 0;
   int      lTempInteger_39 = 0;
   int      lTempInteger_40 = 0;
   int      lTempInteger_41 = 0;
   String   szTempString_14 = null;

   RESULT = GetViewByName( mPerson, "mPerson", mSAProf, zLEVEL_TASK );
   RESULT = GetViewByName( mSAChrgIT, "mSAChrgIT", mSAProf, zLEVEL_TASK );
   RESULT = GetViewByName( mSAChrgITA, "mSAChrgITA", mSAProf, zLEVEL_TASK );
   RESULT = GetViewByName( mSAChrgITC, "mSAChrgITC", mSAProf, zLEVEL_TASK );
   RESULT = GetViewByName( mSAChrgITF, "mSAChrgITF", mSAProf, zLEVEL_TASK );
   RESULT = GetViewByName( wXferO, "wXferO", mSAProf, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dDateOfLastPayment( VIEW mSAProf BASED ON LOD mSAProf,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   VIEW  mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dDateOfLastPayment( View     mSAProf,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:STRING (  10  ) szDate 
   String   szDate = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:OrderEntityForView( mSAProf2, "StudentAccountTransApplied", "TransactionDate D" )
         OrderEntityForView( mSAProf2, "StudentAccountTransApplied", "TransactionDate D" );
         //:SET CURSOR FIRST mSAProf2.StudentAccountTransApplied  
         //:   WHERE mSAProf2.StudentAccountTransApplied.Amount < 0
         //:     AND mSAProf2.StudentAccountTransApplied.Instrument != ""
         RESULT = mSAProf2.cursor( "StudentAccountTransApplied" ).setFirst().toInt();
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf2, "StudentAccountTransApplied", "Amount", 0 ) >= 0 || CompareAttributeToString( mSAProf2, "StudentAccountTransApplied", "Instrument", "" ) == 0 ) )
            { 
               RESULT = mSAProf2.cursor( "StudentAccountTransApplied" ).setNextContinue().toInt();;
            } 

         } 

         //:IF RESULT >= 0 
         if ( RESULT >= 0 )
         { 
            //:szDate = mSAProf2.StudentAccountTransApplied.TransactionDate 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szDate;
            if ( szDate == null )
               sb_szDate = new StringBuilder( 32 );
            else
               sb_szDate = new StringBuilder( szDate );
                         GetVariableFromAttribute( sb_szDate, mi_lTempInteger_0, 'S', 11, mSAProf2, "StudentAccountTransApplied", "TransactionDate", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szDate = sb_szDate.toString( );}
            //:ELSE 
         } 
         else
         { 
            //:szDate = ""
             {StringBuilder sb_szDate;
            if ( szDate == null )
               sb_szDate = new StringBuilder( 32 );
            else
               sb_szDate = new StringBuilder( szDate );
                        ZeidonStringCopy( sb_szDate, 1, 0, "", 1, 0, 11 );
            szDate = sb_szDate.toString( );}
         } 

         //:END   
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreStringInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, szDate )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szDate );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dDateOfLastActivity( VIEW mSAProf BASED ON LOD mSAProf,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   VIEW  mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dDateOfLastActivity( View     mSAProf,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:STRING (  10  ) szDate 
   String   szDate = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:OrderEntityForView( mSAProf2, "StudentAccountTransApplied", "TransactionDate D" )
         OrderEntityForView( mSAProf2, "StudentAccountTransApplied", "TransactionDate D" );
         //:SET CURSOR FIRST mSAProf2.StudentAccountTransApplied  
         RESULT = mSAProf2.cursor( "StudentAccountTransApplied" ).setFirst().toInt();
         //:IF RESULT >= 0 
         if ( RESULT >= 0 )
         { 
            //:szDate = mSAProf2.StudentAccountTransApplied.TransactionDate 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szDate;
            if ( szDate == null )
               sb_szDate = new StringBuilder( 32 );
            else
               sb_szDate = new StringBuilder( szDate );
                         GetVariableFromAttribute( sb_szDate, mi_lTempInteger_0, 'S', 11, mSAProf2, "StudentAccountTransApplied", "TransactionDate", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szDate = sb_szDate.toString( );}
            //:ELSE 
         } 
         else
         { 
            //:szDate = ""
             {StringBuilder sb_szDate;
            if ( szDate == null )
               sb_szDate = new StringBuilder( 32 );
            else
               sb_szDate = new StringBuilder( szDate );
                        ZeidonStringCopy( sb_szDate, 1, 0, "", 1, 0, 11 );
            szDate = sb_szDate.toString( );}
         } 

         //:END   
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreStringInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, szDate )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szDate );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dDaysInResidence( VIEW mSAProf BASED ON LOD mSAProf,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   INTEGER nDays
public int 
omSAProf_dDaysInResidence( View     mSAProf,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   int      nDays = 0;
   //:STRING (  18  ) szBeginDate
   String   szBeginDate = null;
   //:STRING (  18  ) szEndDateToUser
   String   szEndDateToUser = null;

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:IF  mSAProf.TermOfResidence.WithdrawalDate = "" 
         if ( CompareAttributeToString( mSAProf, "TermOfResidence", "WithdrawalDate", "" ) == 0 )
         { 
            //:GetDateAttributeDifferenceInDays( nDays, 
            //:                                 mSAProf, "TermOfResidence", "BeginDate",
            //:                                 mSAProf, "TermOfResidence", "EndDate" )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
             {MutableInt mi_nDays = new MutableInt( nDays );
                         m_ZGLOBAL1_Operation.GetDateAttributeDifferenceInDays( mi_nDays, mSAProf, "TermOfResidence", "BeginDate", mSAProf, "TermOfResidence", "EndDate" );
            nDays = mi_nDays.intValue( );}
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:ELSE 
         } 
         else
         { 
            //:GetDateAttributeDifferenceInDays( nDays, 
            //:                                 mSAProf, "TermOfResidence", "BeginDate",
            //:                                 mSAProf, "TermOfResidence", "WithdrawalDate" )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
             {MutableInt mi_nDays = new MutableInt( nDays );
                         m_ZGLOBAL1_Operation.GetDateAttributeDifferenceInDays( mi_nDays, mSAProf, "TermOfResidence", "BeginDate", mSAProf, "TermOfResidence", "WithdrawalDate" );
            nDays = mi_nDays.intValue( );}
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
         } 

         //:END
         //:nDays = nDays * -1
         nDays = nDays * -1;
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, nDays, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, nDays, 0 );
         break ;
      //:/* end zDERIVED_GET */
      //:OF  zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

   //:  

   //:  /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dTotalSACredits( VIEW mSAProf BASED ON LOD mSAProf,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )
//:  
//:   VIEW  mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dTotalSACredits( View     mSAProf,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:IF mSAProf2.StudentAccountProfile.BalanceForward < 0 
         if ( CompareAttributeToInteger( mSAProf2, "StudentAccountProfile", "BalanceForward", 0 ) < 0 )
         { 
            //:dAmount = mSAProf2.StudentAccountProfile.BalanceForward 
            {MutableDouble md_dAmount = new MutableDouble( dAmount );
                         GetDecimalFromAttribute( md_dAmount, mSAProf2, "StudentAccountProfile", "BalanceForward" );
            dAmount = md_dAmount.doubleValue( );}
            //:ELSE 
         } 
         else
         { 
            //:dAmount = 0
            dAmount = 0;
         } 

         //:END
         //:FOR EACH mSAProf2.StudentAccountTransApplied  
         //:      WHERE mSAProf2.StudentAccountTransApplied.Amount < 0
         RESULT = mSAProf2.cursor( "StudentAccountTransApplied" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToInteger( mSAProf2, "StudentAccountTransApplied", "Amount", 0 ) < 0 )
            { 
               //:dAmount = dAmount + mSAProf2.StudentAccountTransApplied.Amount 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "StudentAccountTransApplied", "Amount" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dAmount = dAmount + dTempDecimal_0;
            } 

            RESULT = mSAProf2.cursor( "StudentAccountTransApplied" ).setNextContinue().toInt();;
         } 

         //:END 
         //:DropView( mSAProf2 ) 
         DropView( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:CreateBillingPeriods( VIEW mSAProf BASED ON LOD mSAProf,
//:                      INTEGER nCollegeYear )
//:   VIEW mYearLST BASED ON LOD  mYear 
public int 
omSAProf_CreateBillingPeriods( View     mSAProf,
                               int      nCollegeYear )
{
   zVIEW    mYearLST = new zVIEW( );
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mYearLST 
   //:   WHERE mYearLST.CollegeYear.ID = nCollegeYear
   omSAProf_fnLocalBuildQual_17( mSAProf, vTempViewVar_0, nCollegeYear );
   RESULT = ActivateObjectInstance( mYearLST, "mYear", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF mSAProf.ProfileAdministrativeDivision.UsesCohortsFlag  ="Y" 
   if ( CompareAttributeToString( mSAProf, "ProfileAdministrativeDivision", "UsesCohortsFlag", "Y" ) == 0 )
   { 
      //:CREATE ENTITY mSAProf.BillingPeriod 
      RESULT = CreateEntity( mSAProf, "BillingPeriod", zPOS_AFTER );
      //:mSAProf.BillingPeriod.PeriodDesignator = mYearLST.CollegeTerm.YearSemester 
      SetAttributeFromAttribute( mSAProf, "BillingPeriod", "PeriodDesignator", mYearLST, "CollegeTerm", "YearSemester" );
      //:mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeYear.BeginDate  
      SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeYear", "BeginDate" );
      //:mSAProf.BillingPeriod.EndDate = mYearLST.CollegeYear.EndDate  
      SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeYear", "EndDate" );
      //:FOR EACH mYearLST.CollegeTerm 
      RESULT = mYearLST.cursor( "CollegeTerm" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:INCLUDE mSAProf.PeriodCollegeTerm FROM mYearLST.CollegeTerm 
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "PeriodCollegeTerm", mYearLST, "CollegeTerm", zPOS_AFTER );
         RESULT = mYearLST.cursor( "CollegeTerm" ).setNextContinue().toInt();;
      } 

      //:END
      //:ELSE 
   } 
   else
   { 
      //:SET CURSOR FIRST mSAProf.BillingPeriod 
      //:   WHERE mSAProf.PeriodCollegeTerm.ID = mYearLST.CollegeTerm.ID
      RESULT = mSAProf.cursor( "BillingPeriod" ).setFirst().toInt();
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSAProf, "PeriodCollegeTerm", "ID", mYearLST, "CollegeTerm", "ID" ) != 0 ) )
         { 
            RESULT = mSAProf.cursor( "BillingPeriod" ).setNextContinue().toInt();;
         } 

      } 

      //:IF RESULT >= zCURSOR_SET 
      if ( RESULT >= zCURSOR_SET )
      { 
         //:DropView( mYearLST )
         DropView( mYearLST );
         //:RETURN -1 
         if(8==8)return( -1 );
      } 

      //:END
      //:FOR EACH mYearLST.CollegeTerm 
      RESULT = mYearLST.cursor( "CollegeTerm" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSAProf.BillingPeriod 
         RESULT = CreateEntity( mSAProf, "BillingPeriod", zPOS_AFTER );
         //:mSAProf.BillingPeriod.PeriodDesignator = mYearLST.CollegeTerm.YearSemester 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "PeriodDesignator", mYearLST, "CollegeTerm", "YearSemester" );
         //:IF mYearLST.CollegeTerm.ResidencyStartDate = "" OR mYearLST.CollegeTerm.ResidencyEndDate = ""
         if ( CompareAttributeToString( mYearLST, "CollegeTerm", "ResidencyStartDate", "" ) == 0 || CompareAttributeToString( mYearLST, "CollegeTerm", "ResidencyEndDate", "" ) == 0 )
         { 
            //:mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeTerm.CourseStartDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeTerm", "CourseStartDate" );
            //:mSAProf.BillingPeriod.EndDate   = mYearLST.CollegeTerm.CourseEndDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeTerm", "CourseEndDate" );
            //:ELSE
         } 
         else
         { 
            //:mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeTerm.ResidencyStartDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeTerm", "ResidencyStartDate" );
            //:mSAProf.BillingPeriod.EndDate   = mYearLST.CollegeTerm.ResidencyEndDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeTerm", "ResidencyEndDate" );
         } 

         //:END
         //:INCLUDE mSAProf.PeriodCollegeTerm FROM mYearLST.CollegeTerm 
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "PeriodCollegeTerm", mYearLST, "CollegeTerm", zPOS_AFTER );
         RESULT = mYearLST.cursor( "CollegeTerm" ).setNextContinue().toInt();;
      } 

      //:END
   } 

   //:END
   //:DropView( mYearLST )
   DropView( mYearLST );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:UpdateBillingPeriods( VIEW mSAProf BASED ON LOD mSAProf,
//:                      INTEGER nCollegeYear )
//:   
//:   VIEW mYearLST BASED ON LOD  mYear 
public int 
omSAProf_UpdateBillingPeriods( View     mSAProf,
                               int      nCollegeYear )
{
   zVIEW    mYearLST = new zVIEW( );
   //:INTEGER CurrentTermID
   int      CurrentTermID = 0;
   //:INTEGER CollegeTermID
   int      CollegeTermID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:ACTIVATE mYearLST WHERE mYearLST.CollegeYear.ID = nCollegeYear
   omSAProf_fnLocalBuildQual_18( mSAProf, vTempViewVar_0, nCollegeYear );
   RESULT = ActivateObjectInstance( mYearLST, "mYear", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:// If the College Terms for the year is not complete, give error message and return.
   //:FOR EACH mYearLST.CollegeTerm 
   RESULT = mYearLST.cursor( "CollegeTerm" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mYearLST.CollegeTerm.CourseStartDate    = "" OR 
      //:   mYearLST.CollegeTerm.CourseEndDate      = ""
      if ( CompareAttributeToString( mYearLST, "CollegeTerm", "CourseStartDate", "" ) == 0 || CompareAttributeToString( mYearLST, "CollegeTerm", "CourseEndDate", "" ) == 0 )
      { 

         //:MessageSend( mSAProf, "", "Generate Billing Period", 
         //:             "Billing Periods will not be generated because Course dates for College Term for the year have not been set.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mSAProf, "", "Generate Billing Period", "Billing Periods will not be generated because Course dates for College Term for the year have not been set.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
      //:IF mYearLST.CollegeTerm.ResidencyStartDate = "" OR
      //:   mYearLST.CollegeTerm.ResidencyEndDate   = ""
      if ( CompareAttributeToString( mYearLST, "CollegeTerm", "ResidencyStartDate", "" ) == 0 || CompareAttributeToString( mYearLST, "CollegeTerm", "ResidencyEndDate", "" ) == 0 )
      { 

         //:MessageSend( mSAProf, "", "Generate Billing Period", 
         //:             "Billing Periods will not be generated because Residency dates for College Term for the year have not been set.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mSAProf, "", "Generate Billing Period", "Billing Periods will not be generated because Residency dates for College Term for the year have not been set.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      RESULT = mYearLST.cursor( "CollegeTerm" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:// Add any missing Billing Periods.
   //:FOR EACH mYearLST.CollegeTerm 
   RESULT = mYearLST.cursor( "CollegeTerm" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm WITHIN mSAProf.StudentAccountProfile 
      //:           WHERE mSAProf.PeriodCollegeTerm.ID = mYearLST.CollegeTerm.ID 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, mYearLST, "CollegeTerm", "ID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      RESULT = mSAProf.cursor( "PeriodCollegeTerm" ).setFirst( "ID", lTempInteger_0, "StudentAccountProfile" ).toInt();
      //:IF RESULT < zCURSOR_SET 
      if ( RESULT < zCURSOR_SET )
      { 
         //:CREATE ENTITY mSAProf.BillingPeriod 
         RESULT = CreateEntity( mSAProf, "BillingPeriod", zPOS_AFTER );
         //:mSAProf.BillingPeriod.PeriodDesignator = mYearLST.CollegeTerm.YearSemester 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "PeriodDesignator", mYearLST, "CollegeTerm", "YearSemester" );
         //:INCLUDE mSAProf.PeriodCollegeTerm FROM mYearLST.CollegeTerm 
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "PeriodCollegeTerm", mYearLST, "CollegeTerm", zPOS_AFTER );
      } 

      //:END

      //:// In all cases, make sure dates are correct.
      //:IF mYearLST.CollegeTerm.ResidencyStartDate = "" OR mYearLST.CollegeTerm.ResidencyEndDate = ""
      if ( CompareAttributeToString( mYearLST, "CollegeTerm", "ResidencyStartDate", "" ) == 0 || CompareAttributeToString( mYearLST, "CollegeTerm", "ResidencyEndDate", "" ) == 0 )
      { 
         //:mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeTerm.CourseStartDate 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeTerm", "CourseStartDate" );
         //:mSAProf.BillingPeriod.EndDate   = mYearLST.CollegeTerm.CourseEndDate 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeTerm", "CourseEndDate" );
         //:ELSE
      } 
      else
      { 
         //:mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeTerm.ResidencyStartDate 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeTerm", "ResidencyStartDate" );
         //:mSAProf.BillingPeriod.EndDate   = mYearLST.CollegeTerm.ResidencyEndDate 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeTerm", "ResidencyEndDate" );
      } 

      RESULT = mYearLST.cursor( "CollegeTerm" ).setNextContinue().toInt();;
      //:END
   } 

   //:END
   //:OrderEntityForView( mSAProf, "BillingPeriod", "BeginDate A" )
   OrderEntityForView( mSAProf, "BillingPeriod", "BeginDate A" );

   //:// Include TermEnrolled entries.
   //:// Check first if they're already there, as that could happen if we're adding additional Periods when a Period already exists.
   //:FOR EACH mSAProf.PeriodCollegeTerm WITHIN mSAProf.StudentAccountProfile
   RESULT = mSAProf.cursor( "PeriodCollegeTerm" ).setFirst( "StudentAccountProfile" ).toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CollegeTermID = mSAProf.PeriodCollegeTerm.ID
      {MutableInt mi_CollegeTermID = new MutableInt( CollegeTermID );
             GetIntegerFromAttribute( mi_CollegeTermID, mSAProf, "PeriodCollegeTerm", "ID" );
      CollegeTermID = mi_CollegeTermID.intValue( );}
      //:FOR EACH mSAProf.Enrolled 
      RESULT = mSAProf.cursor( "Enrolled" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:SET CURSOR FIRST mSAProf.TermEnrolled WHERE mSAProf.TermEnrolled.ID = mSAProf.Enrolled.ID
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                   GetIntegerFromAttribute( mi_lTempInteger_1, mSAProf, "Enrolled", "ID" );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
         RESULT = mSAProf.cursor( "TermEnrolled" ).setFirst( "ID", lTempInteger_1 ).toInt();
         //:IF RESULT < zCURSOR_SET 
         if ( RESULT < zCURSOR_SET )
         { 
            //:SET CURSOR LAST mSAProf.TermEnrolled
            RESULT = mSAProf.cursor( "TermEnrolled" ).setLast().toInt();;
            //:IF mSAProf.RegisteredCollegeTerm EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mSAProf, "RegisteredCollegeTerm" );
            if ( lTempInteger_2 == 0 )
            { 
               //:CurrentTermID = mSAProf.RegisteredCollegeTerm.ID
               {MutableInt mi_CurrentTermID = new MutableInt( CurrentTermID );
                               GetIntegerFromAttribute( mi_CurrentTermID, mSAProf, "RegisteredCollegeTerm", "ID" );
               CurrentTermID = mi_CurrentTermID.intValue( );}
               //:ELSE
            } 
            else
            { 
               //:IF mSAProf.CurrentCollegeTerm EXISTS 
               lTempInteger_3 = CheckExistenceOfEntity( mSAProf, "CurrentCollegeTerm" );
               if ( lTempInteger_3 == 0 )
               { 
                  //:CurrentTermID = mSAProf.CurrentCollegeTerm.ID
                  {MutableInt mi_CurrentTermID = new MutableInt( CurrentTermID );
                                     GetIntegerFromAttribute( mi_CurrentTermID, mSAProf, "CurrentCollegeTerm", "ID" );
                  CurrentTermID = mi_CurrentTermID.intValue( );}
                  //:ELSE 
               } 
               else
               { 
                  //:CurrentTermID = 0
                  CurrentTermID = 0;
               } 

               //:END
            } 

            //:END
            //://IF CurrentTermID = CollegeTermID OR CurrentTermID = 0
            //:IF CurrentTermID = CollegeTermID     // Modified from above by Don C on 8/16/06
            if ( CurrentTermID == CollegeTermID )
            { 
               //:IF mSAProf.Enrolled.Status = "T" OR mSAProf.Enrolled.Status = "C" 
               if ( CompareAttributeToString( mSAProf, "Enrolled", "Status", "T" ) == 0 || CompareAttributeToString( mSAProf, "Enrolled", "Status", "C" ) == 0 )
               { 
                  //:INCLUDE mSAProf.TermEnrolled FROM mSAProf.Enrolled
                  RESULT = IncludeSubobjectFromSubobject( mSAProf, "TermEnrolled", mSAProf, "Enrolled", zPOS_AFTER );
               } 

               //:END
            } 

            //:END
         } 

         RESULT = mSAProf.cursor( "Enrolled" ).setNextContinue().toInt();;
         //:END
      } 

      RESULT = mSAProf.cursor( "PeriodCollegeTerm" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:DropView( mYearLST )
   DropView( mYearLST );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildTermEnrolledTerm( VIEW mSAProf BASED ON LOD mSAProf,
//:                       INTEGER CollegeTermID )
//:   
//:   VIEW mSAStuEn BASED ON LOD mSAStuEn 
public int 
omSAProf_BuildTermEnrolledTerm( View     mSAProf,
                                int      CollegeTermID )
{
   zVIEW    mSAStuEn = new zVIEW( );
   //:INTEGER CurrentTermID
   int      CurrentTermID = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:// Build TermEnrolled Subobjects.
   //:// SWAU Modification: Include Waitlisted classes in TermEnrolled because those classes are to be included in billing.
   //:ACTIVATE mSAStuEn WHERE mSAStuEn.Student.ID = mSAProf.Student.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf, "Student", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   //:     RESTRICTING mSAStuEn.Registration TO mSAStuEn.RegistrationCollegeTerm.ID = CollegeTermID OR 
   //:                                          mSAStuEn.RegisteredCollegeTerm.ID   = CollegeTermID
   //:     RESTRICTING mSAStuEn.StudentWaitlisted TO mSAStuEn.StudentWaitlistedCollegeTerm.ID = CollegeTermID
   omSAProf_fnLocalBuildQual_19( mSAProf, vTempViewVar_0, lTempInteger_0, CollegeTermID );
   RESULT = ActivateObjectInstance( mSAStuEn, "mSAStuEn", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSAStuEn "mSAStuEn"
   SetNameForView( mSAStuEn, "mSAStuEn", null, zLEVEL_TASK );

   //:// Process BillingPeriod
   //:FOR EACH mSAStuEn.Registration 
   RESULT = mSAStuEn.cursor( "Registration" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSAStuEn.Registration.Status = "T" OR 
      //:   mSAStuEn.Registration.Status = "C" OR 
      //:   mSAStuEn.Registration.Status = "D" OR 
      //:   mSAStuEn.Registration.Status = "W" 
      if ( CompareAttributeToString( mSAStuEn, "Registration", "Status", "T" ) == 0 || CompareAttributeToString( mSAStuEn, "Registration", "Status", "C" ) == 0 || CompareAttributeToString( mSAStuEn, "Registration", "Status", "D" ) == 0 ||
           CompareAttributeToString( mSAStuEn, "Registration", "Status", "W" ) == 0 )
      { 

         //:INCLUDE mSAProf.TermEnrolled FROM mSAStuEn.Registration 
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "TermEnrolled", mSAStuEn, "Registration", zPOS_AFTER );
      } 

      RESULT = mSAStuEn.cursor( "Registration" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:// SWAU modification to include Waitlisted classes in billing.
   //:FOR EACH mSAStuEn.StudentWaitlisted 
   RESULT = mSAStuEn.cursor( "StudentWaitlisted" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSAProf.TermEnrolled 
      RESULT = CreateEntity( mSAProf, "TermEnrolled", zPOS_AFTER );
      //:mSAProf.TermEnrolled.Status      = "T"
      SetAttributeFromString( mSAProf, "TermEnrolled", "Status", "T" );
      //:mSAProf.TermEnrolled.CreditHours = mSAStuEn.StudentWaitlistedClass.CreditHours
      SetAttributeFromAttribute( mSAProf, "TermEnrolled", "CreditHours", mSAStuEn, "StudentWaitlistedClass", "CreditHours" );
      //:INCLUDE mSAProf.TermClass FROM mSAStuEn.StudentWaitlistedClass 
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "TermClass", mSAStuEn, "StudentWaitlistedClass", zPOS_AFTER );
      RESULT = mSAStuEn.cursor( "StudentWaitlisted" ).setNextContinue().toInt();;
   } 

   //:END
   //:DropObjectInstance( mSAStuEn )
   DropObjectInstance( mSAStuEn );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dSubAccountBalance( VIEW mSAProf BASED ON LOD mSAProf,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   VIEW mSASubAL BASED ON LOD mSASubAL
public int 
omSAProf_dSubAccountBalance( View     mSAProf,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   zVIEW    mSASubAL = new zVIEW( );
   //:DECIMAL dAmount
   double  dAmount = 0.0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:dAmount = 0
         dAmount = 0;
         //:IF mSAProf.SASubAccount.ClosedDate = "" 
         if ( CompareAttributeToString( mSAProf, "SASubAccount", "ClosedDate", "" ) == 0 )
         { 
            //:ACTIVATE mSASubAL WHERE mSASubAL.SASubAccount.ID = mSAProf.SASubAccount.ID 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf, "SASubAccount", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            omSAProf_fnLocalBuildQual_20( mSAProf, vTempViewVar_0, lTempInteger_0 );
            RESULT = ActivateObjectInstance( mSASubAL, "mSASubAL", mSAProf, vTempViewVar_0, zSINGLE );
            DropView( vTempViewVar_0 );
            //:IF RESULT >= 0
            if ( RESULT >= 0 )
            { 
               //:dAmount = mSASubAL.SASubAccount.dCurrentBalance 
               {MutableDouble md_dAmount = new MutableDouble( dAmount );
                               GetDecimalFromAttribute( md_dAmount, mSASubAL, "SASubAccount", "dCurrentBalance" );
               dAmount = md_dAmount.doubleValue( );}
            } 

            //:END
            //:DropView( mSASubAL )
            DropView( mSASubAL );
         } 

         //:END

         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;

      //:/* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:   /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dLatestRoomAssignment( VIEW mSAProf BASED ON LOD mSAProf,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )
//:   
//:   STRING ( 100 ) szRoomAssignment
public int 
omSAProf_dLatestRoomAssignment( View     mSAProf,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   String   szRoomAssignment = null;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Display the latest Dorm room information, if there is any.
         //:szRoomAssignment = ""
          {StringBuilder sb_szRoomAssignment;
         if ( szRoomAssignment == null )
            sb_szRoomAssignment = new StringBuilder( 32 );
         else
            sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                  ZeidonStringCopy( sb_szRoomAssignment, 1, 0, "", 1, 0, 101 );
         szRoomAssignment = sb_szRoomAssignment.toString( );}
         //:IF mSAProf.TermOfResidence EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "TermOfResidence" );
         if ( lTempInteger_0 == 0 )
         { 
            //:// Make sure residence is sorted in reverse date order.
            //:OrderEntityForView( mSAProf, "TermOfResidence", "BeginDate D" )
            OrderEntityForView( mSAProf, "TermOfResidence", "BeginDate D" );
            //:SET CURSOR FIRST mSAProf.TermOfResidence 
            RESULT = mSAProf.cursor( "TermOfResidence" ).setFirst().toInt();
            //:szRoomAssignment = mSAProf.Building.Name + " Room " + mSAProf.Room.Number 
            {StringBuilder sb_szRoomAssignment;
            if ( szRoomAssignment == null )
               sb_szRoomAssignment = new StringBuilder( 32 );
            else
               sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                         GetStringFromAttribute( sb_szRoomAssignment, mSAProf, "Building", "Name" );
            szRoomAssignment = sb_szRoomAssignment.toString( );}
             {StringBuilder sb_szRoomAssignment;
            if ( szRoomAssignment == null )
               sb_szRoomAssignment = new StringBuilder( 32 );
            else
               sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                        ZeidonStringConcat( sb_szRoomAssignment, 1, 0, " Room ", 1, 0, 101 );
            szRoomAssignment = sb_szRoomAssignment.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 255, mSAProf, "Room", "Number", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szRoomAssignment;
            if ( szRoomAssignment == null )
               sb_szRoomAssignment = new StringBuilder( 32 );
            else
               sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                        ZeidonStringConcat( sb_szRoomAssignment, 1, 0, szTempString_0, 1, 0, 101 );
            szRoomAssignment = sb_szRoomAssignment.toString( );}
         } 

         //:END
         //:StoreStringInRecord ( mSAProf,
         //:                   InternalEntityStructure, InternalAttribStructure, szRoomAssignment )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szRoomAssignment );
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


//:DERIVED ATTRIBUTE OPERATION
//:dCurrentRoomAssignment( VIEW mSAProf BASED ON LOD mSAProf,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )
//:   
//:   VIEW wXferO REGISTERED AS wXferO
public int 
omSAProf_dCurrentRoomAssignment( View     mSAProf,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 100 ) szRoomAssignment
   String   szRoomAssignment = null;
   //:STRING ( 8 )   szCurrentDate
   String   szCurrentDate = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mSAProf, zLEVEL_TASK );

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Display the latest Dorm room information, if there is any.
         //:// (This operation is the same as that in mStudent.)
         //:szRoomAssignment = ""
          {StringBuilder sb_szRoomAssignment;
         if ( szRoomAssignment == null )
            sb_szRoomAssignment = new StringBuilder( 32 );
         else
            sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                  ZeidonStringCopy( sb_szRoomAssignment, 1, 0, "", 1, 0, 101 );
         szRoomAssignment = sb_szRoomAssignment.toString( );}
         //:IF mSAProf.Student.ResidencyStatus = "R"
         if ( CompareAttributeToString( mSAProf, "Student", "ResidencyStatus", "R" ) == 0 )
         { 
            //:szCurrentDate = wXferO.Root.dCurrentDate
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szCurrentDate;
            if ( szCurrentDate == null )
               sb_szCurrentDate = new StringBuilder( 32 );
            else
               sb_szCurrentDate = new StringBuilder( szCurrentDate );
                         GetVariableFromAttribute( sb_szCurrentDate, mi_lTempInteger_0, 'S', 9, wXferO, "Root", "dCurrentDate", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szCurrentDate = sb_szCurrentDate.toString( );}
            //:SET CURSOR FIRST mSAProf.TermOfResidence 
            //:     WHERE mSAProf.TermOfResidence.BeginDate <= szCurrentDate
            //:       AND mSAProf.TermOfResidence.EndDate   >= szCurrentDate 
            RESULT = mSAProf.cursor( "TermOfResidence" ).setFirst().toInt();
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSAProf, "TermOfResidence", "BeginDate", szCurrentDate ) > 0 || CompareAttributeToString( mSAProf, "TermOfResidence", "EndDate", szCurrentDate ) < 0 ) )
               { 
                  RESULT = mSAProf.cursor( "TermOfResidence" ).setNextContinue().toInt();;
               } 

            } 

            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:szRoomAssignment = mSAProf.Building.Name + " Room " + mSAProf.Room.Number 
               {StringBuilder sb_szRoomAssignment;
               if ( szRoomAssignment == null )
                  sb_szRoomAssignment = new StringBuilder( 32 );
               else
                  sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                               GetStringFromAttribute( sb_szRoomAssignment, mSAProf, "Building", "Name" );
               szRoomAssignment = sb_szRoomAssignment.toString( );}
                {StringBuilder sb_szRoomAssignment;
               if ( szRoomAssignment == null )
                  sb_szRoomAssignment = new StringBuilder( 32 );
               else
                  sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                              ZeidonStringConcat( sb_szRoomAssignment, 1, 0, " Room ", 1, 0, 101 );
               szRoomAssignment = sb_szRoomAssignment.toString( );}
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 255, mSAProf, "Room", "Number", "", 0 );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szRoomAssignment;
               if ( szRoomAssignment == null )
                  sb_szRoomAssignment = new StringBuilder( 32 );
               else
                  sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                              ZeidonStringConcat( sb_szRoomAssignment, 1, 0, szTempString_0, 1, 0, 101 );
               szRoomAssignment = sb_szRoomAssignment.toString( );}
               //:ELSE
            } 
            else
            { 
               //:SET CURSOR FIRST mSAProf.TermOfResidence WHERE mSAProf.TermOfResidence.BeginDate > szCurrentDate
               RESULT = mSAProf.cursor( "TermOfResidence" ).setFirst().toInt();
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSAProf, "TermOfResidence", "BeginDate", szCurrentDate ) <= 0 ) )
                  { 
                     RESULT = mSAProf.cursor( "TermOfResidence" ).setNextContinue().toInt();;
                  } 

               } 

               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:szRoomAssignment = mSAProf.Building.Name + " Room " + mSAProf.Room.Number 
                  {StringBuilder sb_szRoomAssignment;
                  if ( szRoomAssignment == null )
                     sb_szRoomAssignment = new StringBuilder( 32 );
                  else
                     sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                                     GetStringFromAttribute( sb_szRoomAssignment, mSAProf, "Building", "Name" );
                  szRoomAssignment = sb_szRoomAssignment.toString( );}
                   {StringBuilder sb_szRoomAssignment;
                  if ( szRoomAssignment == null )
                     sb_szRoomAssignment = new StringBuilder( 32 );
                  else
                     sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                                    ZeidonStringConcat( sb_szRoomAssignment, 1, 0, " Room ", 1, 0, 101 );
                  szRoomAssignment = sb_szRoomAssignment.toString( );}
                  {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                  StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 255, mSAProf, "Room", "Number", "", 0 );
                  lTempInteger_2 = mi_lTempInteger_2.intValue( );
                  szTempString_1 = sb_szTempString_1.toString( );}
                   {StringBuilder sb_szRoomAssignment;
                  if ( szRoomAssignment == null )
                     sb_szRoomAssignment = new StringBuilder( 32 );
                  else
                     sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                                    ZeidonStringConcat( sb_szRoomAssignment, 1, 0, szTempString_1, 1, 0, 101 );
                  szRoomAssignment = sb_szRoomAssignment.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szRoomAssignment = "(No Room Assignment)"
                   {StringBuilder sb_szRoomAssignment;
                  if ( szRoomAssignment == null )
                     sb_szRoomAssignment = new StringBuilder( 32 );
                  else
                     sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                                    ZeidonStringCopy( sb_szRoomAssignment, 1, 0, "(No Room Assignment)", 1, 0, 101 );
                  szRoomAssignment = sb_szRoomAssignment.toString( );}
               } 

               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:szRoomAssignment = "(Nonresident Student)"
             {StringBuilder sb_szRoomAssignment;
            if ( szRoomAssignment == null )
               sb_szRoomAssignment = new StringBuilder( 32 );
            else
               sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                        ZeidonStringCopy( sb_szRoomAssignment, 1, 0, "(Nonresident Student)", 1, 0, 101 );
            szRoomAssignment = sb_szRoomAssignment.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mSAProf,
         //:                InternalEntityStructure, InternalAttribStructure, szRoomAssignment )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szRoomAssignment );
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
//:dCurrentMealPlan( VIEW mSAProf BASED ON LOD mSAProf,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   VIEW wXferO REGISTERED AS wXferO
public int 
omSAProf_dCurrentMealPlan( View     mSAProf,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 100 ) szMealPlan
   String   szMealPlan = null;
   //:STRING ( 8 )   szCurrentDate
   String   szCurrentDate = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mSAProf, zLEVEL_TASK );

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Display the latest Dorm room information, if there is any.
         //:szMealPlan = ""
          {StringBuilder sb_szMealPlan;
         if ( szMealPlan == null )
            sb_szMealPlan = new StringBuilder( 32 );
         else
            sb_szMealPlan = new StringBuilder( szMealPlan );
                  ZeidonStringCopy( sb_szMealPlan, 1, 0, "", 1, 0, 101 );
         szMealPlan = sb_szMealPlan.toString( );}
         //:szCurrentDate = wXferO.Root.dCurrentDate
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szCurrentDate;
         if ( szCurrentDate == null )
            sb_szCurrentDate = new StringBuilder( 32 );
         else
            sb_szCurrentDate = new StringBuilder( szCurrentDate );
                   GetVariableFromAttribute( sb_szCurrentDate, mi_lTempInteger_0, 'S', 9, wXferO, "Root", "dCurrentDate", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szCurrentDate = sb_szCurrentDate.toString( );}
         //:IF wXferO.Root.dCurrentDate >= "20080530" AND wXferO.Root.dCurrentDate <= "20080830"
         if ( CompareAttributeToString( wXferO, "Root", "dCurrentDate", "20080530" ) >= 0 && CompareAttributeToString( wXferO, "Root", "dCurrentDate", "20080830" ) <= 0 )
         { 
            //:TraceLineS("**** MAKE DATE IN FALL Meal Plan", "")
            TraceLineS( "**** MAKE DATE IN FALL Meal Plan", "" );
            //:szCurrentDate = "20071015" 
             {StringBuilder sb_szCurrentDate;
            if ( szCurrentDate == null )
               sb_szCurrentDate = new StringBuilder( 32 );
            else
               sb_szCurrentDate = new StringBuilder( szCurrentDate );
                        ZeidonStringCopy( sb_szCurrentDate, 1, 0, "20071015", 1, 0, 9 );
            szCurrentDate = sb_szCurrentDate.toString( );}
         } 

         //:END
         //:SET CURSOR FIRST mSAProf.TermOfMealPlan  
         //:     WHERE mSAProf.TermOfMealPlan.BeginDate <= szCurrentDate
         //:       AND mSAProf.TermOfMealPlan.EndDate   >= szCurrentDate 
         RESULT = mSAProf.cursor( "TermOfMealPlan" ).setFirst().toInt();
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSAProf, "TermOfMealPlan", "BeginDate", szCurrentDate ) > 0 || CompareAttributeToString( mSAProf, "TermOfMealPlan", "EndDate", szCurrentDate ) < 0 ) )
            { 
               RESULT = mSAProf.cursor( "TermOfMealPlan" ).setNextContinue().toInt();;
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:szMealPlan = mSAProf.MealPlan.Name 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szMealPlan;
            if ( szMealPlan == null )
               sb_szMealPlan = new StringBuilder( 32 );
            else
               sb_szMealPlan = new StringBuilder( szMealPlan );
                         GetVariableFromAttribute( sb_szMealPlan, mi_lTempInteger_1, 'S', 101, mSAProf, "MealPlan", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szMealPlan = sb_szMealPlan.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szMealPlan = "No current meal plan"
             {StringBuilder sb_szMealPlan;
            if ( szMealPlan == null )
               sb_szMealPlan = new StringBuilder( 32 );
            else
               sb_szMealPlan = new StringBuilder( szMealPlan );
                        ZeidonStringCopy( sb_szMealPlan, 1, 0, "No current meal plan", 1, 0, 101 );
            szMealPlan = sb_szMealPlan.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mSAProf,
         //:                InternalEntityStructure, InternalAttribStructure, szMealPlan )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szMealPlan );
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
//:ObjectConstraints( VIEW mSAProf BASED ON LOD mSAProf,
//:                   SHORT Event,
//:                   SHORT State )

//:   VIEW lTermLST BASED ON LOD lTermLST
public int 
omSAProf_ObjectConstraints( View     mSAProf,
                            Integer   Event,
                            Integer   State )
{
   zVIEW    lTermLST = new zVIEW( );
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_6 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_7 = 0;


   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:// Make sure BillingPeriod is sorted by College Term Semester within academic year.
         //:IF mSAProf.PeriodCollegeTerm EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "PeriodCollegeTerm" );
         if ( lTempInteger_0 == 0 )
         { 
            //:OrderEntityForView( mSAProf, "BillingPeriod", "PeriodCollegeTerm.Semester A" )
            OrderEntityForView( mSAProf, "BillingPeriod", "PeriodCollegeTerm.Semester A" );
            //:SET CURSOR FIRST mSAProf.BillingPeriod 
            RESULT = mSAProf.cursor( "BillingPeriod" ).setFirst().toInt();
         } 

         //:END

         //:// Make sure that Meal Plans are in reverse date order.
         //:IF mSAProf.TermOfResidence EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mSAProf, "TermOfResidence" );
         if ( lTempInteger_1 == 0 )
         { 
            //:OrderEntityForView( mSAProf, "TermOfResidence", "BeginDate D" )
            OrderEntityForView( mSAProf, "TermOfResidence", "BeginDate D" );
            //:SET CURSOR FIRST mSAProf.TermOfResidence 
            RESULT = mSAProf.cursor( "TermOfResidence" ).setFirst().toInt();
         } 

         //:END

         //:// Make sure that Residency entries are in reverse date order.
         //:IF mSAProf.TermOfMealPlan EXISTS
         lTempInteger_2 = CheckExistenceOfEntity( mSAProf, "TermOfMealPlan" );
         if ( lTempInteger_2 == 0 )
         { 
            //:OrderEntityForView( mSAProf, "TermOfMealPlan", "BeginDate D" )
            OrderEntityForView( mSAProf, "TermOfMealPlan", "BeginDate D" );
            //:SET CURSOR FIRST mSAProf.TermOfMealPlan 
            RESULT = mSAProf.cursor( "TermOfMealPlan" ).setFirst().toInt();
         } 

         //:END

         //:// Order Transactions inversely by date.
         //:IF mSAProf.StudentAccountTransApplied EXISTS
         lTempInteger_3 = CheckExistenceOfEntity( mSAProf, "StudentAccountTransApplied" );
         if ( lTempInteger_3 == 0 )
         { 
            //:OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D ID D" )
            OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D ID D" );
            //:SET CURSOR FIRST mSAProf.StudentAccountTransApplied 
            RESULT = mSAProf.cursor( "StudentAccountTransApplied" ).setFirst().toInt();
         } 

         //:END

         //:// Order Enrolled entries by Class Date.
         //:IF mSAProf.Enrolled EXISTS
         lTempInteger_4 = CheckExistenceOfEntity( mSAProf, "Enrolled" );
         if ( lTempInteger_4 == 0 )
         { 
            //:// If current Term is Fall, sort in ascending order.
            //:// Otherwise, sort in descending order.
            //:// This is only for display purposes and does not effect generating charges.
            //:GET VIEW lTermLST NAMED "lSATermLST"
            RESULT = GetViewByName( lTermLST, "lSATermLST", mSAProf, zLEVEL_TASK );
            //:IF RESULT < 0
            if ( RESULT < 0 )
            { 
               //:GET VIEW lTermLST NAMED "lTermLST" 
               RESULT = GetViewByName( lTermLST, "lTermLST", mSAProf, zLEVEL_TASK );
            } 

            //:END
            //:IF lTermLST.CollegeTerm.Semester = "01"

            //:END
            //:SET CURSOR FIRST mSAProf.Enrolled
            RESULT = mSAProf.cursor( "Enrolled" ).setFirst().toInt();
         } 

         //:END

         //:// Make sure latest DegreeCandidacy entry is ordered first.
         //:IF mSAProf.DegreeCandidacy EXISTS
         lTempInteger_5 = CheckExistenceOfEntity( mSAProf, "DegreeCandidacy" );
         if ( lTempInteger_5 == 0 )
         { 
            //:OrderEntityForView( mSAProf, "DegreeCandidacy", "ID D" )
            OrderEntityForView( mSAProf, "DegreeCandidacy", "ID D" );
            //:SET CURSOR FIRST mSAProf.DegreeCandidacy 
            RESULT = mSAProf.cursor( "DegreeCandidacy" ).setFirst().toInt();
         } 

         //:END

         //:// Order StudentAccountHistoricalProfile by Year.
         //:OrderEntityForView( mSAProf, "StudentAccountHistoricalProfile", "HistoricalCollegeYear.Year D" )
         OrderEntityForView( mSAProf, "StudentAccountHistoricalProfile", "HistoricalCollegeYear.Year D" );

         //:// Initialize flags used for determining if accounting data has been updated when charges are generated.
         //:mSAProf.StudentAccountProfile.wOrigHealthInsuranceWaiver = mSAProf.StudentAccountProfile.HealthInsuranceWaiver 
         SetAttributeFromAttribute( mSAProf, "StudentAccountProfile", "wOrigHealthInsuranceWaiver", mSAProf, "StudentAccountProfile", "HealthInsuranceWaiver" );
         //:mSAProf.StudentAccountProfile.wOrigMealPlanWaiver        = mSAProf.StudentAccountProfile.MealPlanWaiver 
         SetAttributeFromAttribute( mSAProf, "StudentAccountProfile", "wOrigMealPlanWaiver", mSAProf, "StudentAccountProfile", "MealPlanWaiver" );

         //:// Give a warning message if a BillingPeriod does not have a StartDate or an EndDate for the currently selecteD Term.
         //:// We will only do this for Terms equal or later than 2008-2009.
         //:GET VIEW lTermLST NAMED "lSATermLST"
         RESULT = GetViewByName( lTermLST, "lSATermLST", mSAProf, zLEVEL_TASK );
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 
            //:IF lTermLST.CollegeTerm.YearSemester >= "2008-2009"
            if ( CompareAttributeToString( lTermLST, "CollegeTerm", "YearSemester", "2008-2009" ) >= 0 )
            { 
               //:SET CURSOR FIRST mSAProf.BillingPeriod
               //:        WHERE mSAProf.PeriodCollegeTerm.YearSemester = lTermLST.CollegeTerm.YearSemester 
               //:          AND ( mSAProf.BillingPeriod.BeginDate = "" OR mSAProf.BillingPeriod.EndDate = "" ) 
               RESULT = mSAProf.cursor( "BillingPeriod" ).setFirst().toInt();
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSAProf, "PeriodCollegeTerm", "YearSemester", lTermLST, "CollegeTerm", "YearSemester" ) != 0 ||
                        ( CompareAttributeToString( mSAProf, "BillingPeriod", "BeginDate", "" ) != 0 && CompareAttributeToString( mSAProf, "BillingPeriod", "EndDate", "" ) != 0 ) ) )
                  { 
                     RESULT = mSAProf.cursor( "BillingPeriod" ).setNextContinue().toInt();;
                  } 

               } 

               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:szMsg = "Student, " + mSAProf.Person.dFullName + ", has a Billing Period for Term, " + 
                  //:     mSAProf.PeriodCollegeTerm.YearSemester + ", without valid Start/End Dates." + NEW_LINE +
                  //:     "This is a serious error and should be reported to Systems Support."
                  {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                  StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                     GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_6, 'S', 255, mSAProf, "Person", "dFullName", "", 0 );
                  lTempInteger_6 = mi_lTempInteger_6.intValue( );
                  szTempString_0 = sb_szTempString_0.toString( );}
                   {StringBuilder sb_szMsg;
                  if ( szMsg == null )
                     sb_szMsg = new StringBuilder( 32 );
                  else
                     sb_szMsg = new StringBuilder( szMsg );
                                    ZeidonStringCopy( sb_szMsg, 1, 0, "Student, ", 1, 0, 201 );
                  szMsg = sb_szMsg.toString( );}
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
                                    ZeidonStringConcat( sb_szMsg, 1, 0, ", has a Billing Period for Term, ", 1, 0, 201 );
                  szMsg = sb_szMsg.toString( );}
                  {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                  StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_7, 'S', 21, mSAProf, "PeriodCollegeTerm", "YearSemester", "", 0 );
                  lTempInteger_7 = mi_lTempInteger_7.intValue( );
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
                                    ZeidonStringConcat( sb_szMsg, 1, 0, ", without valid Start/End Dates.", 1, 0, 201 );
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
                                    ZeidonStringConcat( sb_szMsg, 1, 0, "This is a serious error and should be reported to Systems Support.", 1, 0, 201 );
                  szMsg = sb_szMsg.toString( );}
                  //:MessageSend( mSAProf, "", "Activate mSAProf", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
                  MessageSend( mSAProf, "", "Activate mSAProf", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
               } 

               //:END
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

         //:// Make sure AccountBalance is set from the derived value.
         //:mSAProf.StudentAccountProfile.AccountBalance = mSAProf.StudentAccountProfile.dBalance 
         SetAttributeFromAttribute( mSAProf, "StudentAccountProfile", "AccountBalance", mSAProf, "StudentAccountProfile", "dBalance" );
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
//:GenerateSwapTrans( VIEW mSAProf           BASED ON LOD mSAProf,
//:                   VIEW mFAProf           BASED ON LOD mFAProf,
//:                   VIEW mFAProfOrigAward  BASED ON LOD mFAProf,
//:                   VIEW mFASrcAwarded     BASED ON LOD mFASrc,
//:                   DECIMAL dSwappableAmount )

//:   VIEW mSAProfT BASED ON LOD mSAProf
public int 
omSAProf_GenerateSwapTrans( View     mSAProf,
                            View     mFAProf,
                            View     mFAProfOrigAward,
                            View     mFASrcAwarded,
                            double  dSwappableAmount )
{
   zVIEW    mSAProfT = new zVIEW( );
   //:DECIMAL ReverseAmount
   double  ReverseAmount = 0.0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_4 = 0;


   //:// Generate the two transactions for swapping awards from institutional awards to scholarships.
   //:// The first transaction assigns the moneys to the new Fin Aid Source and the second transaction
   //:// adjusts the moneys from the original institutional award.

   //:// There are two FinAidAward entries involved in this operation:
   //:// View mFAProfOrigAward is positioned on the original award (the Institutional Aid award that is being swapped.)
   //:// View mFAProf is positioned on the new swapped award.

   //:// SA TRANSACTION FOR NEW AMOUNT
   //:// Generate the SA Transaction entry in mSAProf for the AMOUNT SWAPPED TO NEW FINAID SOURCE and the Transaction Code of the FinAid Source.
   //:ReverseAmount = 0 - dSwappableAmount
   ReverseAmount = 0 - dSwappableAmount;
   //:GenerateCharge( mSAProf, 
   //:                mFAProfOrigAward.FinAidAwardPeriodTerm.ID,    // The College Term for the new Disbursement is same as the original.
   //:                mFASrcAwarded.SATransactionCode.ID,           // Transaction Code from the FinAid Source, determining the type of Transaction.
   //:                ReverseAmount, "N",                           // Amount to Swap as entered by User.
   //:                mFASrcAwarded.FinAidSource.Name )             // Name of the FinAid Source, whioch will become the description of the Transaction.
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mFAProfOrigAward, "FinAidAwardPeriodTerm", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mFASrcAwarded, "SATransactionCode", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, mFASrcAwarded, "FinAidSource", "Name" );
   szTempString_0 = sb_szTempString_0.toString( );}
   omSAProf_GenerateCharge( mSAProf, lTempInteger_0, lTempInteger_1, ReverseAmount, "N", szTempString_0 );
   //:AcceptSubobject( mSAProf, "StudentAccountTransApplied" )      // The generate creates a termporal subobject.
   AcceptSubobject( mSAProf, "StudentAccountTransApplied" );
   //:INCLUDE mSAProf.FinAidAwardDisbursement FROM mFAProf.FinAidAwardDisbursement
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "FinAidAwardDisbursement", mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
   //:mSAProf.StudentAccountTransApplied.NoShowOnBill = "Y"
   SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "NoShowOnBill", "Y" );


   //:// SA TRANSACTION FOR MODIFICATION TO PRIOR AMOUNT
   //:// Generate the SA Transaction entry in mSAProf for the AMOUNT DECREMENTED FROM ORIGINAL AWARD and associated FinAid Source.
   //:// Note that mFAProfOrigAward is pointing to the original Disbursement and FinAid Source in mFAProf.
   //:GenerateCharge( mSAProf, 
   //:                mFAProfOrigAward.FinAidAwardPeriodTerm.ID,    // The College Term for the new Disbursement is same as the original.
   //:                mFAProfOrigAward.SATransactionCode.ID,        // Transaction Code from the orig FinAid Source, determining the type of Transaction.
   //:                dSwappableAmount, "N",                        // Negative of Amount to Swap.
   //:                mFAProfOrigAward.FinAidSource.Name  )         // Name of the orig FinAid Source, whioch will become the description of the Transaction.
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mFAProfOrigAward, "FinAidAwardPeriodTerm", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, mFAProfOrigAward, "SATransactionCode", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   {StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
       GetStringFromAttribute( sb_szTempString_1, mFAProfOrigAward, "FinAidSource", "Name" );
   szTempString_1 = sb_szTempString_1.toString( );}
   omSAProf_GenerateCharge( mSAProf, lTempInteger_2, lTempInteger_3, dSwappableAmount, "N", szTempString_1 );
   //:AcceptSubobject( mSAProf, "StudentAccountTransApplied" )      // The generate creates a termporal subobject.
   AcceptSubobject( mSAProf, "StudentAccountTransApplied" );
   //:INCLUDE mSAProf.FinAidAwardDisbursement FROM mFAProfOrigAward.FinAidAwardDisbursement
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "FinAidAwardDisbursement", mFAProfOrigAward, "FinAidAwardDisbursement", zPOS_AFTER );
   //:mSAProf.StudentAccountTransApplied.NoShowOnBill = "Y"
   SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "NoShowOnBill", "Y" );
   //:   

   //:// Tie the SA Transaction just created to the original SA Transaction that it modifies. This makes the new transaction an adjustment
   //:// to the original transaction.
   //:CreateViewFromView( mSAProfT,  mSAProf )
   CreateViewFromView( mSAProfT, mSAProf );
   //:NAME VIEW mSAProfT "mSAProfT"
   SetNameForView( mSAProfT, "mSAProfT", null, zLEVEL_TASK );
   //:// Position on the original transaction to be adjusted. We need to make sure that the original transaction exists, as older entries
   //:// did not make the tie between disbursement and SA transaction.
   //:// We will also order the original transactions by ID and position on oldest, in case the original Transaction has already been adjusted by a Disbursement.
   //:OrderEntityForView( mFAProfOrigAward, "FinAidAwardTransApplied", "ID A" )
   OrderEntityForView( mFAProfOrigAward, "FinAidAwardTransApplied", "ID A" );
   //:SET CURSOR FIRST mFAProfOrigAward.FinAidAwardTransApplied WHERE mFAProfOrigAward.FinAidAwardTransApplied.ID != ""
   RESULT = mFAProfOrigAward.cursor( "FinAidAwardTransApplied" ).setFirst().toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mFAProfOrigAward, "FinAidAwardTransApplied", "ID", "" ) == 0 ) )
      { 
         RESULT = mFAProfOrigAward.cursor( "FinAidAwardTransApplied" ).setNextContinue().toInt();;
      } 

   } 

   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:SET CURSOR FIRST mSAProfT.StudentAccountTransApplied WHERE mSAProfT.StudentAccountTransApplied.ID = mFAProfOrigAward.FinAidAwardTransApplied.ID 
      {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
             GetIntegerFromAttribute( mi_lTempInteger_4, mFAProfOrigAward, "FinAidAwardTransApplied", "ID" );
      lTempInteger_4 = mi_lTempInteger_4.intValue( );}
      RESULT = mSAProfT.cursor( "StudentAccountTransApplied" ).setFirst( "ID", lTempInteger_4 ).toInt();
      //:// mSAProfT points to the original transaction and mSAProf to the new transaction just created.
      //:INCLUDE mSAProf.AdjustedSATransApplied FROM mSAProfT.StudentAccountTransApplied 
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "AdjustedSATransApplied", mSAProfT, "StudentAccountTransApplied", zPOS_AFTER );
   } 

   //:END
   //:DropView( mSAProfT )
   DropView( mSAProfT );
   return( 0 );
// END
} 



}
