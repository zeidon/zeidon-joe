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

import com.quinsoft.swauopencuas.ZGLOBAL1_Operation;
import com.quinsoft.swauopencuas.ZGLOBAL2_Operation;
import com.quinsoft.swauopencuas.mSAChrgT_Object;
import com.quinsoft.swauopencuas.mSARuleS_Object;

/**
   @author QuinSoft
**/

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
omSAProf_fnLocalBuildQual_8( View     vSubtask,
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
omSAProf_fnLocalBuildQual_9( View     vSubtask,
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
omSAProf_fnLocalBuildQual_10( View     vSubtask,
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
omSAProf_fnLocalBuildQual_11( View     vSubtask,
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
omSAProf_fnLocalBuildQual_12( View     vSubtask,
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
omSAProf_fnLocalBuildQual_13( View     vSubtask,
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
omSAProf_fnLocalBuildQual_14( View     vSubtask,
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
omSAProf_fnLocalBuildQual_15( View     vSubtask,
                              zVIEW    vQualObject,
                              int      CollegeYearID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeYear" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", CollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_16( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0,
                              int      CollegeYearID )
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
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "RegistrationCollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", CollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "RegisteredCollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", CollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentWaitlisted" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentWaitlistedCollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", CollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_17( View     vSubtask,
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
omSAProf_fnLocalBuildQual_18( View     vSubtask,
                              zVIEW    vQualObject,
                              int      CollegeTermID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeTerm" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", CollegeTermID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_19( View     vSubtask,
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
omSAProf_fnLocalBuildQual_20( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_5 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountTransApplied" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountTransApplied" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_5 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_21( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_1 )
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
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_22( View     vSubtask,
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
omSAProf_fnLocalBuildQual_23( View     vSubtask,
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
omSAProf_fnLocalBuildQual_24( View     vSubtask,
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
omSAProf_fnLocalBuildQual_25( View     vSubtask,
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
omSAProf_fnLocalBuildQual_26( View     vSubtask,
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
omSAProf_fnLocalBuildQual_27( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_4,
                              int      lTempInteger_5,
                              int      nCollegeYearID,
                              String   szStartDate,
                              String   szEndDate )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_4 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ProfileAdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_5 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidCollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nCollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidProfile" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "VehicleRegistration" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "VehicleRegistration" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Address" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Address" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Organization" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Organization" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Prospect" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Prospect" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Demographics" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Demographics" );
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
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szStartDate.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ">=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "TermOfMealPlan" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "BeginDate" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szEndDate.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "<=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "BillingPeriod" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PeriodCollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nCollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_28( View     vSubtask,
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
omSAProf_fnLocalBuildQual_29( View     vSubtask,
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
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PeriodCollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nCollegeYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
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
                             int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountProfile" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_5( View     vSubtask,
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
omSAProf_fnLocalBuildQual_6( View     vSubtask,
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
//:dSATransPayeeName( VIEW mSAProf BASED ON LOD mSAProf,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   STRING ( 50 ) szPayeeName
public int 
omSAProf_dSATransPayeeName( View     mSAProf,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szPayeeName = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Get Payee Name from either StudentAccountReceipt or FinancialAccountTransaction.
         //:IF mSAProf.FinancialAccountTransaction EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "FinancialAccountTransaction" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szPayeeName = mSAProf.FinancialAccountTransaction.PayeeName 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szPayeeName;
            if ( szPayeeName == null )
               sb_szPayeeName = new StringBuilder( 32 );
            else
               sb_szPayeeName = new StringBuilder( szPayeeName );
                         GetVariableFromAttribute( sb_szPayeeName, mi_lTempInteger_1, 'S', 51, mSAProf, "FinancialAccountTransaction", "PayeeName", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szPayeeName = sb_szPayeeName.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mSAProf.StudentAccountReceipt EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mSAProf, "StudentAccountReceipt" );
            if ( lTempInteger_2 == 0 )
            { 
               //:szPayeeName = mSAProf.StudentAccountReceipt.ContactPerson
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szPayeeName;
               if ( szPayeeName == null )
                  sb_szPayeeName = new StringBuilder( 32 );
               else
                  sb_szPayeeName = new StringBuilder( szPayeeName );
                               GetVariableFromAttribute( sb_szPayeeName, mi_lTempInteger_3, 'S', 51, mSAProf, "StudentAccountReceipt", "ContactPerson", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szPayeeName = sb_szPayeeName.toString( );}
            } 

            //:END
         } 

         //:END
         //:StoreStringInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, szPayeeName )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szPayeeName );
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
         //:IF mSAProf.BillingPeriod EXISTS  
         lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "BillingPeriod" );
         if ( lTempInteger_0 == 0 )
         { 
            //:IF mSAProf.Student.DateOfDeparture != "" 
            if ( CompareAttributeToString( mSAProf, "Student", "DateOfDeparture", "" ) != 0 )
            { 
               //:GetDateAttributeDifferenceInDays( nDays, 
               //:                                 mSAProf, "BillingPeriod", "BeginDate",
               //:                                 mSAProf, "Student", "DateOfDeparture" )
               {
                ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
                {MutableInt mi_nDays = new MutableInt( nDays );
                               m_ZGLOBAL1_Operation.GetDateAttributeDifferenceInDays( mi_nDays, mSAProf, "BillingPeriod", "BeginDate", mSAProf, "Student", "DateOfDeparture" );
               nDays = mi_nDays.intValue( );}
                // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
               }
               //:ELSE 
            } 
            else
            { 
               //:GetDateAttributeDifferenceInDays( nDays, 
               //:                                 mSAProf, "BillingPeriod", "BeginDate",
               //:                                 mSAProf, "BillingPeriod", "EndDate" )
               {
                ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
                {MutableInt mi_nDays = new MutableInt( nDays );
                               m_ZGLOBAL1_Operation.GetDateAttributeDifferenceInDays( mi_nDays, mSAProf, "BillingPeriod", "BeginDate", mSAProf, "BillingPeriod", "EndDate" );
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
         RESULT = SetCursorFirstEntity( mSAProf2, "StudentAccountTransApplied", "" );
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

            RESULT = SetCursorNextEntity( mSAProf2, "StudentAccountTransApplied", "" );
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
         RESULT = SetCursorFirstEntity( mSAProf2, "StudentAccountTransApplied", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:dAmount = dAmount + mSAProf2.StudentAccountTransApplied.Amount 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "StudentAccountTransApplied", "Amount" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dAmount = dAmount + dTempDecimal_0;
            RESULT = SetCursorNextEntity( mSAProf2, "StudentAccountTransApplied", "" );
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
//:dPreviousBalance( VIEW mSAProf BASED ON LOD mSAProf,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )
//:   VIEW  mSAProfB BASED ON LOD mSAProfB
public int 
omSAProf_dPreviousBalance( View     mSAProf,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   zVIEW    mSAProfB = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Name changed from dBalance on 10/28/2011 by DonC to keep origingal dBalance code.
         //:// This is the balance currently out on the database.
         //:dAmount = 0
         dAmount = 0;
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


public int 
omSAProf_dCreditsDL( View     mSAProf,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   zVIEW    mSAProfB = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Name changed from dBalance on 10/28/2011 by DonC to keep origingal dBalance code.
         //:// This is the balance currently out on the database.
         //:dAmount = 0
         dAmount = 0;
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

public int 
omSAProf_dCreditsAudited( View     mSAProf,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   zVIEW    mSAProfB = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Name changed from dBalance on 10/28/2011 by DonC to keep origingal dBalance code.
         //:// This is the balance currently out on the database.
         //:dAmount = 0
         dAmount = 0;
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

         //:// Corrected 10/28/2011 by DonC based on similar change at ENC.
         //:// We have two dBalance derived attributes. This one here and one in mSAProfE.  This dBalance
         //:// uses the StudentAccountTransApplied entities as they appear in mSAProf, which could have been
         //:// manipulated, so this dBalance is a "work in progress" balance.
         //:// The dBalance in mSAProfE shows exactly the balance as it is in the database based on original 
         //:// StudentAccountTransApplied entities.
         //:// We want these two dBalances to be different!
         //:dAmount = mSAProf.StudentAccountProfile.BalanceForward 
         {MutableDouble md_dAmount = new MutableDouble( dAmount );
                   GetDecimalFromAttribute( md_dAmount, mSAProf, "StudentAccountProfile", "BalanceForward" );
         dAmount = md_dAmount.doubleValue( );}
         //:FOR EACH mSAProf.StudentAccountTransApplied  
         RESULT = SetCursorFirstEntity( mSAProf, "StudentAccountTransApplied", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:dAmount = dAmount + mSAProf.StudentAccountTransApplied.Amount 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf, "StudentAccountTransApplied", "Amount" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dAmount = dAmount + dTempDecimal_0;
            RESULT = SetCursorNextEntity( mSAProf, "StudentAccountTransApplied", "" );
         } 

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
         RESULT = SetCursorFirstEntity( mSAProf2, "PeriodTransApplied", "" );
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

            RESULT = SetCursorNextEntity( mSAProf2, "PeriodTransApplied", "" );
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
         RESULT = SetCursorFirstEntity( mSAProf2, "TermEnrolled", "" );
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

            RESULT = SetCursorNextEntity( mSAProf2, "TermEnrolled", "" );
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
   //:DECIMAL dCreditHours
   double  dCreditHours = 0.0;
   int      RESULT = 0;


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
         RESULT = SetCursorFirstEntity( mSAProf2, "TermEnrolled", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mSAProf2.TermEnrolled.TakingClassType = "A"
            if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "TakingClassType", "A" ) == 0 )
            { 
               //:dCreditHours = mSAProf2.TermClass.CreditHours 
               {MutableDouble md_dCreditHours = new MutableDouble( dCreditHours );
                               GetDecimalFromAttribute( md_dCreditHours, mSAProf2, "TermClass", "CreditHours" );
               dCreditHours = md_dCreditHours.doubleValue( );}
               //:ELSE
            } 
            else
            { 
               //:dCreditHours = mSAProf2.TermEnrolled.CreditHours 
               {MutableDouble md_dCreditHours = new MutableDouble( dCreditHours );
                               GetDecimalFromAttribute( md_dCreditHours, mSAProf2, "TermEnrolled", "CreditHours" );
               dCreditHours = md_dCreditHours.doubleValue( );}
            } 

            //:END
            //:IF mSAProf2.TermEnrolled.Status ="T"  OR mSAProf2.TermEnrolled.Status ="C" 
            if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "T" ) == 0 || CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "C" ) == 0 )
            { 
               //:dTotalCredits = dTotalCredits + dCreditHours 
               dTotalCredits = dTotalCredits + dCreditHours;
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
                     //:dTotalCredits = dTotalCredits + dCreditHours
                     dTotalCredits = dTotalCredits + dCreditHours;
                  } 

                  //:END
               } 

               //:END
            } 

            RESULT = SetCursorNextEntity( mSAProf2, "TermEnrolled", "" );
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
//:dNonAuditCredits( VIEW mSAProf BASED ON LOD mSAProf,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )
//:   VIEW mSAProf2  BASED ON LOD mSAProf
public int 
omSAProf_dNonAuditCredits( View     mSAProf,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dTotalCredits 
   double  dTotalCredits = 0.0;
   //:DECIMAL dCreditHours
   double  dCreditHours = 0.0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Total the credit hours that are not audited.
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:NAME VIEW mSAProf2 "mSAProf2"
         SetNameForView( mSAProf2, "mSAProf2", null, zLEVEL_TASK );
         //:dTotalCredits = 0
         dTotalCredits = 0;
         //:FOR EACH mSAProf2.TermEnrolled 
         RESULT = SetCursorFirstEntity( mSAProf2, "TermEnrolled", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mSAProf2.TermEnrolled.TakingClassType != "A"    // Don't include audited credits.
            if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "TakingClassType", "A" ) != 0 )
            { 
               //:dCreditHours = mSAProf2.TermEnrolled.CreditHours 
               {MutableDouble md_dCreditHours = new MutableDouble( dCreditHours );
                               GetDecimalFromAttribute( md_dCreditHours, mSAProf2, "TermEnrolled", "CreditHours" );
               dCreditHours = md_dCreditHours.doubleValue( );}
               //:IF mSAProf2.TermEnrolled.Status ="T"  OR mSAProf2.TermEnrolled.Status ="C" 
               if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "T" ) == 0 || CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "C" ) == 0 )
               { 
                  //:dTotalCredits = dTotalCredits + dCreditHours 
                  dTotalCredits = dTotalCredits + dCreditHours;
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
                        //:dTotalCredits = dTotalCredits + dCreditHours
                        dTotalCredits = dTotalCredits + dCreditHours;
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            RESULT = SetCursorNextEntity( mSAProf2, "TermEnrolled", "" );
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
         RESULT = SetCursorFirstEntity( mSAProf2, "TermEnrolled", "" );
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

            RESULT = SetCursorNextEntity( mSAProf2, "TermEnrolled", "" );
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
         RESULT = SetCursorFirstEntity( mSAProf2, "TermEnrolled", "" );
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

            RESULT = SetCursorNextEntity( mSAProf2, "TermEnrolled", "" );
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
         RESULT = SetCursorFirstEntity( mSAProf2, "TermEnrolled", "" );
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

            RESULT = SetCursorNextEntity( mSAProf2, "TermEnrolled", "" );
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
         RESULT = SetCursorFirstEntity( mSAProf2, "TermEnrolled", "" );
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

            RESULT = SetCursorNextEntity( mSAProf2, "TermEnrolled", "" );
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
//:   
//:   VIEW mUser     BASED ON LOD mUser
public int 
omSAProf_ActivateSAProfForYear( zVIEW    mSAProf,
                                View     mSAStu,
                                int      nCollegeYearID,
                                int      nCollegeTermID )
{
   zVIEW    mUser = new zVIEW( );
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   //:SHORT nRC
   int      nRC = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;


   //:GET VIEW mUser NAMED "mUser"
   RESULT = GetViewByName( mUser, "mUser", mSAStu, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:GetViewByName( mUser, "mUser", mSAStu, zLEVEL_APPLICATION )
      GetViewByName( mUser, "mUser", mSAStu, zLEVEL_APPLICATION );
   } 

   //:END
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
   //:   RESTRICTING mSAProf.Enrolled TO 
   //:               ( mSAProf.EnrollmentCollegeYear.ID = nCollegeYearID OR mSAProf.RegisteredCollegeYear.ID = nCollegeYearID )
   //:               OR mSAProf.Cohort EXISTS 
   //:   RESTRICTING mSAProf.BillingPeriod TO mSAProf.PeriodCollegeYear.ID = nCollegeYearID
   omSAProf_fnLocalBuildQual_0( mSAStu, vTempViewVar_0, lTempInteger_0, lTempInteger_1, nCollegeYearID );
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
   lTempInteger_2 = CheckExistenceOfEntity( mSAProf, "StudentAccountTransApplied" );
   if ( lTempInteger_2 == 0 )
   { 
      //:OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D" )
      OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D" );
   } 

   //:END

   //:// Build the Billing Periods if they don't currently exist.
   //:IF mSAProf.BillingPeriod DOES NOT EXIST
   lTempInteger_3 = CheckExistenceOfEntity( mSAProf, "BillingPeriod" );
   if ( lTempInteger_3 != 0 )
   { 
      //:UpdateBillingPeriods(  mSAProf, nCollegeYearID )
      omSAProf_UpdateBillingPeriods( mSAProf, nCollegeYearID );
      //:ELSE
   } 
   else
   { 
      //:// Go to build TermEnrolled Subobjects.
      //:IF mSAProf.PeriodCollegeYear EXISTS
      lTempInteger_4 = CheckExistenceOfEntity( mSAProf, "PeriodCollegeYear" );
      if ( lTempInteger_4 == 0 )
      { 
         //:BuildTermEnrolled( mSAProf, nCollegeYearID )
         omSAProf_BuildTermEnrolled( mSAProf, nCollegeYearID );
      } 

      //:END
   } 

   //:END

   //:// Position on current Term.
   //:SET CURSOR FIRST mSAProf.BillingPeriod WHERE mSAProf.PeriodCollegeTerm.ID = nCollegeTermID  
   RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeTerm", "ID", nCollegeTermID ) != 0 ) )
      { 
         RESULT = SetCursorNextEntity( mSAProf, "BillingPeriod", "" );
      } 

   } 

   //:IF RESULT >= zCURSOR_SET  
   if ( RESULT >= zCURSOR_SET )
   { 
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm WHERE mSAProf.PeriodCollegeTerm.ID = nCollegeTermID  
      RESULT = SetCursorFirstEntityByInteger( mSAProf, "PeriodCollegeTerm", "ID", nCollegeTermID, "" );
      //:ELSE 
   } 
   else
   { 
      //:SET CURSOR FIRST mSAProf.BillingPeriod WHERE mSAProf.PeriodCollegeYear.ID = nCollegeYearID  
      RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeYear", "ID", nCollegeYearID ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( mSAProf, "BillingPeriod", "" );
         } 

      } 

      //:IF RESULT < zCURSOR_SET  
      if ( RESULT < zCURSOR_SET )
      { 
         //:SET CURSOR FIRST mSAProf.BillingPeriod // in this case only
         RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
      } 

      //:END 
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm // in this case only
      RESULT = SetCursorFirstEntity( mSAProf, "PeriodCollegeTerm", "" );
   } 

   //:END

   //:// Make sure that we are positioned on correct Billing Period for Main Campus Students.
   //:/*
   //:IF mSAProf.AdministrativeDivision.UsesCohortsFlag != "Y"
   //:   SET CURSOR FIRST mSAProf.PeriodCollegeTerm WITHIN mSAProf.StudentAccountProfile 
   //:              WHERE mSAProf.PeriodCollegeTerm.ID = nCollegeTermID
   //:   IF RESULT < zCURSOR_SET
   //:      szMsg = "No Billing Period exists for " + mSAProf.Person.dFullNameLFM + " for currently selected Term." 
   //:      MessageSend( mSAProf, "", "Generate Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //:   END
   //:END
   //:*/

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
   RESULT = SetCursorFirstEntityByInteger( lTermLST, "CollegeTerm", "ID", nCollegeTermID, "" );

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
   RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeTerm", "ID", nCollegeTermID ) != 0 ) )
      { 
         RESULT = SetCursorNextEntity( mSAProf, "BillingPeriod", "" );
      } 

   } 

   //:IF RESULT >= zCURSOR_SET  
   if ( RESULT >= zCURSOR_SET )
   { 
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm  
      //:   WHERE mSAProf.PeriodCollegeTerm.ID = nCollegeTermID  
      RESULT = SetCursorFirstEntityByInteger( mSAProf, "PeriodCollegeTerm", "ID", nCollegeTermID, "" );
      //:ELSE 
   } 
   else
   { 
      //:SET CURSOR FIRST mSAProf.BillingPeriod 
      //:   WHERE mSAProf.PeriodCollegeYear.ID = nCollegeYearID  
      RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeYear", "ID", nCollegeYearID ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( mSAProf, "BillingPeriod", "" );
         } 

      } 

      //:IF RESULT < zCURSOR_SET  
      if ( RESULT < zCURSOR_SET )
      { 
         //:SET CURSOR FIRST mSAProf.BillingPeriod // in this case only
         RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
      } 

      //:END 
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm // in this case only
      RESULT = SetCursorFirstEntity( mSAProf, "PeriodCollegeTerm", "" );
   } 

   //:END

   //:FOR EACH mSAProf.PeriodCollegeTerm
   RESULT = SetCursorFirstEntity( mSAProf, "PeriodCollegeTerm", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:TotalCredits = 0
      TotalCredits = 0;
      //:CollegeTermID = mSAProf.PeriodCollegeTerm.ID
      {MutableInt mi_CollegeTermID = new MutableInt( CollegeTermID );
             GetIntegerFromAttribute( mi_CollegeTermID, mSAProf, "PeriodCollegeTerm", "ID" );
      CollegeTermID = mi_CollegeTermID.intValue( );}
      //:FOR EACH mSAProf.Enrolled 
      RESULT = SetCursorFirstEntity( mSAProf, "Enrolled", "" );
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

         RESULT = SetCursorNextEntity( mSAProf, "Enrolled", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSAProf, "PeriodCollegeTerm", "" );
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
      RESULT = SetCursorFirstEntity( mYearLST, "CollegeTerm", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:INCLUDE mSAProf.PeriodCollegeTerm FROM mYearLST.CollegeTerm 
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "PeriodCollegeTerm", mYearLST, "CollegeTerm", zPOS_AFTER );
         RESULT = SetCursorNextEntity( mYearLST, "CollegeTerm", "" );
      } 

      //:END
      //:ELSE  
   } 
   else
   { 
      //:FOR EACH mYearLST.CollegeTerm 
      RESULT = SetCursorFirstEntity( mYearLST, "CollegeTerm", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSAProf.BillingPeriod 
         RESULT = CreateEntity( mSAProf, "BillingPeriod", zPOS_AFTER );
         //:mSAProf.BillingPeriod.PeriodDesignator = mYearLST.CollegeTerm.YearSemester 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "PeriodDesignator", mYearLST, "CollegeTerm", "YearSemester" );
         //:IF mYearLST.CollegeTerm.BillingPeriodStartDate = "" OR mYearLST.CollegeTerm.BillingPeriodEndDate = ""
         if ( CompareAttributeToString( mYearLST, "CollegeTerm", "BillingPeriodStartDate", "" ) == 0 || CompareAttributeToString( mYearLST, "CollegeTerm", "BillingPeriodEndDate", "" ) == 0 )
         { 
            //:mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeTerm.CourseStartDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeTerm", "CourseStartDate" );
            //:mSAProf.BillingPeriod.EndDate   = mYearLST.CollegeTerm.CourseEndDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeTerm", "CourseEndDate" );
            //:ELSE
         } 
         else
         { 
            //:mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeTerm.BillingPeriodStartDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeTerm", "BillingPeriodStartDate" );
            //:mSAProf.BillingPeriod.EndDate   = mYearLST.CollegeTerm.BillingPeriodEndDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeTerm", "BillingPeriodEndDate" );
         } 

         //:END
         //:INCLUDE mSAProf.PeriodCollegeTerm FROM mYearLST.CollegeTerm 
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "PeriodCollegeTerm", mYearLST, "CollegeTerm", zPOS_AFTER );
         RESULT = SetCursorNextEntity( mYearLST, "CollegeTerm", "" );
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
public int 
omSAProf_GeneratePerCohortClass( View     mSAProf,
                                 View     mSAClass,
                                 View     mSAChrgTList )
{

   return( 0 );
//    
//    // For the following code to work, the mSAClass structure from Zencas must be moved over.
//    
//    /*VIEW mSAChrgITA REGISTERED AS mSAChrgITA
//    VIEW mSAChrgI   BASED ON LOD mSAChrgI 
//    DECIMAL dAdjustedAmount
//    DECIMAL dTuitionCharge
//    DECIMAL dOtherCharge
//    STRING ( 200 ) szMsg
//    
//    // This operation generates the charges that are driven by a Student taking a Cohort Class.
//    // The charges driven by a Class are:
//    // 1. Tuition is charged either per credit or a flat amount, based on the Cohort. In either case, the amount
//    //    comes from the Cohort and the SA Transaction is defined by the Program for the Cohort.
//    // 2. Lab Fee, which is charged as a single amount defined in the Class. The SA Transaction is defined by the 
//    //    Program for the Cohort.
//    // 3. Directed Study, which is an extra fee which is charged if the Class is marked 'Directed Study'.
//    // 4. Corporate Discount, which is a discount to the computed Tuition Charge based on a percent in
//    //    the Organization entity.
//    
//    
//    // SET UP LIST OF TRANSACTION CODES
//    // Make sure that object containing Charge Transactions has entries, as it is initalized to empty by the caller
//    // for the first Student for the Class.
//    // The object needs to have Transactions for the charges listed above.
//    IF mSAChrgTList.SATransactionCode DOES NOT EXIST
//    
//       // 1. Tuition Transaction.
//       ACTIVATE mSAChrgI WHERE mSAChrgI.SATransactionCode.BillType   = "T"
//                            AND mSAChrgI.SATransactionCode.ActiveFlag = ""
//                            AND mSAChrgI.Program.ID = mSAClass.Program.ID 
//       IF RESULT >= 0
//          INCLUDE mSAChrgTList.SATransactionCode FROM mSAChrgI.SATransactionCode 
//          DropObjectInstance( mSAChrgI )
//       ELSE
//          szMsg = "Tuition Generation Error: The Program, " + mSAClass.Program.Name + ", is not tied to an SA Transaction Code"
//          MessageSend( mSAProf, "", "Generate Cohort Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//          RETURN -1
//       END
//       
//       // 2. Lab Fee
//       ACTIVATE mSAChrgI WHERE mSAChrgI.SATransactionCode.BillType   = "L"
//                            AND mSAChrgI.SATransactionCode.ActiveFlag = ""
//                            AND mSAChrgI.Program.ID = mSAClass.Program.ID 
//       IF RESULT >= 0
//          INCLUDE mSAChrgTList.SATransactionCode FROM mSAChrgI.SATransactionCode 
//          DropObjectInstance( mSAChrgI )
//       ELSE
//          szMsg = "Lab Fee Generation Error: The Program, " + mSAClass.Program.Name + ", is not tied to an SA Transaction Code"
//          MessageSend( mSAProf, "", "Generate Cohort Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//          RETURN -1
//       END
//       
//       // 3. Directed Study
//       IF mSAClass.Class.DirectedStudyFlag = "Y"
//          ACTIVATE mSAChrgI WHERE mSAChrgI.SATransactionCode.Description = "Directed Study Fee"
//                              AND mSAChrgI.SATransactionCode.ActiveFlag = ""
//          IF RESULT >= 0 
//             INCLUDE mSAChrgTList.SATransactionCode FROM mSAChrgI.SATransactionCode 
//             DropObjectInstance( mSAChrgI )
//          ELSE
//             MessageSend( mSAProf, "", "Generate Charges", 
//                          "The Class is marked 'Directed Study' but no 'Directed Study' Transaction Code exists.. Processing is aborted.", 
//                          zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//             RETURN -1
//          END
//       END
//       
//       // 4. Corporate Discount
//       //    This transaction is only required if one of the Students gets a corporate discount.
//       ACTIVATE mSAChrgI WHERE mSAChrgI.SATransactionCode.Description = "Corporate Discount"
//                           AND mSAChrgI.SATransactionCode.ActiveFlag = ""
//       IF RESULT >= 0 
//          INCLUDE mSAChrgTList.SATransactionCode FROM mSAChrgI.SATransactionCode 
//       END
//       DropObjectInstance( mSAChrgI )
//       
//    END
//    
//    
//    // Tuition Charge
//    // 
//    // The amount charged is the Amount per Credit Hour from the Cohort times the number of credits from the Class OR
//    // The flat amount specified in the Cohort uses the same PricePerCreditHour attribute.
//    // The Transaction Code is determined from the SATuitionTransactionCode for the Class
//    IF mSAClass.Cohort.FlatPrice = "Y" 
//       dTuitionCharge = mSAClass.Cohort.PricePerCreditHour 
//    ELSE 
//       dTuitionCharge = mSAClass.Class.CreditHours * mSAClass.Cohort.PricePerCreditHour 
//    END
//    SET CURSOR FIRST mSAChrgTList.SATransactionCode WHERE mSAChrgTList.SATransactionCode.BillType = "T"
//    GenFeeForCohortWClass( mSAProf, mSAClass, mSAChrgTList, dTuitionCharge )
//    
//    
//    // Lab Fee
//    //
//    // Lab Fee is charged if there is an amount specified for the Class AND if there is an SA Transacion for the Program
//    // that is a Lab Fee.
//    IF mSAClass.Class.LabFee != ""
//       SET CURSOR FIRST mSAClass.ProgramSATransactionCode WHERE mSAClass.ProgramSATransactionCode.BillType = "L"
//       IF RESULT >= zCURSOR_SET 
//          SET CURSOR FIRST mSAChrgTList.SATransactionCode WHERE mSAChrgTList.SATransactionCode.ID = mSAClass.ProgramSATransactionCode.ID 
//          IF RESULT >= zCURSOR_SET 
//             dOtherCharge = mSAClass.Class.LabFee
//             GenFeeForCohortWClass( mSAProf, mSAClass, mSAChrgTList, dOtherCharge )
//          END
//       END
//    END
//    
//    
//    // Directed Study Fee
//    //
//    // Directed Study Fee is charged based on flag in Class. Amount comes from the Directed Study Transaction.
//    IF mSAClass.Class.DirectedStudyFlag = "Y"
//       SET CURSOR FIRST mSAChrgTList.SATransactionCode WHERE mSAChrgTList.SATransactionCode.Description = "Directed Study Fee"
//       dOtherCharge = mSAChrgTList.SATransactionCode.DefaultAmount
//       GenFeeForCohortWClass( mSAProf, mSAClass, mSAChrgTList, dOtherCharge )
//    END  
//    
//    
//    // Corporate Discount
//    //
//    // If the Student has an employee relationship with to an Employer (Organization entity) with a discount, apply it here.
//    // Note that dTuitionCharge computed above is the tuition charge against which the discount applies.
//    IF mSAProf.Organization EXISTS
//       IF mSAProf.Organization.TuitionDiscount > 0
//          SET CURSOR FIRST mSAChrgTList.SATransactionCode WHERE mSAChrgTList.SATransactionCode.Description = "Corporate Discount" 
//          IF RESULT >= zCURSOR_SET
//             NAME VIEW mSAChrgTList "mSAChrgT_LabFee"
//             dAdjustedAmount = dTuitionCharge * mSAProf.Organization.TuitionDiscount 
//             dAdjustedAmount = dAdjustedAmount * -1    // Invert to negative amount
//             GenFeeForCohortWClass( mSAProf, mSAClass, mSAChrgTList, dAdjustedAmount )
//          ELSE
//             szMsg = "Student, " + mSAClass.EnrolledStudentPerson.dFullNameLFM + ", gets a corporate discount but no 'Corporate Discount' Transaction exists."
//                                 + NEW_LINE + "No Discount Transaction will be generated."
//             MessageSend( mSAProf, "", "Generate Charges",  szMsg,  zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//          END
//       END
//    END*/
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
   RESULT = SetCursorFirstEntity( mSAProf, "StudentAccountTransApplied", "" );
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

      RESULT = SetCursorNextEntity( mSAProf, "StudentAccountTransApplied", "" );
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
   omSAProf_fnLocalBuildQual_5( mSAProf, vTempViewVar_0, nChargeID, nTermID );
   RESULT = ActivateObjectInstance( mSAChrgT, "mSAChrgT", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:SET CURSOR FIRST mSAProf.BillingPeriod 
   //:           WHERE mSAProf.PeriodCollegeTerm.ID = nTermID
   RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeTerm", "ID", nTermID ) != 0 ) )
      { 
         RESULT = SetCursorNextEntity( mSAProf, "BillingPeriod", "" );
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
   omSAProf_fnLocalBuildQual_6( mSAProf, vTempViewVar_0, nChargeID );
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
   omSAProf_fnLocalBuildQual_7( mSAProf, vTempViewVar_0, szDate );
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
      omSAProf_fnLocalBuildQual_8( mSAProf, vTempViewVar_1, szDate );
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
   int      lTempInteger_4 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mSAProf, zLEVEL_TASK );

   //:ACTIVATE lSATermLST  
   //:   WHERE lSATermLST.CollegeTerm.ID = nTermID
   omSAProf_fnLocalBuildQual_9( mSAProf, vTempViewVar_0, nTermID );
   RESULT = ActivateObjectInstance( lSATermLST, "lTermLST", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:ACTIVATE mSAChrgT  
   //:   WHERE mSAChrgT.SATransactionCode.ID = nChargeID
   //:   RESTRICTING mSAChrgT.SATransactionTerm TO mSAChrgT.CollegeTerm.ID = nTermID
   omSAProf_fnLocalBuildQual_10( mSAProf, vTempViewVar_1, nChargeID, nTermID );
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
   RESULT = SetCursorFirstEntity( mSAProf2, "PeriodTransApplied", "" );
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

      RESULT = SetCursorNextEntity( mSAProf2, "PeriodTransApplied", "" );
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

      //:// SWAU change 11/23/2010 so that charges could be generated for the next Term, with Transaction Date
      //:// being the beginning of that Term, instead of Current Date, as was generated in GenerateUpdateCharge.
      //:IF mSAProf.PeriodCollegeTerm EXISTS
      lTempInteger_3 = CheckExistenceOfEntity( mSAProf, "PeriodCollegeTerm" );
      if ( lTempInteger_3 == 0 )
      { 
         //:IF mSAProf.PeriodCollegeTerm.CourseStartDate > mSAProf.StudentAccountTransApplied.TransactionDate 
         if ( CompareAttributeToAttribute( mSAProf, "PeriodCollegeTerm", "CourseStartDate", mSAProf, "StudentAccountTransApplied", "TransactionDate" ) > 0 )
         { 
            //:mSAProf.StudentAccountTransApplied.TransactionDate = mSAProf.PeriodCollegeTerm.CourseStartDate
            SetAttributeFromAttribute( mSAProf, "StudentAccountTransApplied", "TransactionDate", mSAProf, "PeriodCollegeTerm", "CourseStartDate" );
         } 

         //:END
      } 

      //:END

      //:IF mSAChrgT.SACombineCode EXISTS  
      lTempInteger_4 = CheckExistenceOfEntity( mSAChrgT, "SACombineCode" );
      if ( lTempInteger_4 == 0 )
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
         RESULT = SetCursorFirstEntity( mSAProf2, "TermOfResidence", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSAProf2, "TermOfResidence", "WithdrawalDate", "" ) != 0 || CompareAttributeToString( mSAProf2, "TermOfResidence", "BeginDate", szNow ) > 0 ||
                    CompareAttributeToString( mSAProf2, "TermOfResidence", "EndDate", szNow ) < 0 ) )
            { 
               RESULT = SetCursorNextEntity( mSAProf2, "TermOfResidence", "" );
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
   //:STRING ( 10 ) szStartDate
   String   szStartDate = null;
   //:STRING ( 10 ) szEndDate
   String   szEndDate = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;


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
   RESULT = SetCursorFirstEntity( mSAProf2, "BillingTermOfResidence", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:EXCLUDE  mSAProf2.BillingTermOfResidence NONE  
      RESULT = ExcludeEntity( mSAProf2, "BillingTermOfResidence", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSAProf2, "BillingTermOfResidence", "" );
   } 

   //:END

   //:FOR EACH mSAProf2.TermOfResidence 
   RESULT = SetCursorFirstEntity( mSAProf2, "TermOfResidence", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSAProf2.TermOfResidence.WithdrawalDate = "" 
      if ( CompareAttributeToString( mSAProf2, "TermOfResidence", "WithdrawalDate", "" ) == 0 )
      { 
         //:IF mSAProf2.PeriodCollegeTerm.ResidencyStartDate = ""
         if ( CompareAttributeToString( mSAProf2, "PeriodCollegeTerm", "ResidencyStartDate", "" ) == 0 )
         { 
            //:szStartDate = mSAProf2.PeriodCollegeTerm.CourseStartDate
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szStartDate;
            if ( szStartDate == null )
               sb_szStartDate = new StringBuilder( 32 );
            else
               sb_szStartDate = new StringBuilder( szStartDate );
                         GetVariableFromAttribute( sb_szStartDate, mi_lTempInteger_2, 'S', 11, mSAProf2, "PeriodCollegeTerm", "CourseStartDate", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szStartDate = sb_szStartDate.toString( );}
            //:ELSE 
         } 
         else
         { 
            //:szStartDate = mSAProf2.PeriodCollegeTerm.ResidencyStartDate
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szStartDate;
            if ( szStartDate == null )
               sb_szStartDate = new StringBuilder( 32 );
            else
               sb_szStartDate = new StringBuilder( szStartDate );
                         GetVariableFromAttribute( sb_szStartDate, mi_lTempInteger_3, 'S', 11, mSAProf2, "PeriodCollegeTerm", "ResidencyStartDate", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szStartDate = sb_szStartDate.toString( );}
         } 

         //:END 
         //:IF mSAProf2.PeriodCollegeTerm.ResidencyEndDate = ""
         if ( CompareAttributeToString( mSAProf2, "PeriodCollegeTerm", "ResidencyEndDate", "" ) == 0 )
         { 
            //:szEndDate = mSAProf2.PeriodCollegeTerm.CourseEndDate
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szEndDate;
            if ( szEndDate == null )
               sb_szEndDate = new StringBuilder( 32 );
            else
               sb_szEndDate = new StringBuilder( szEndDate );
                         GetVariableFromAttribute( sb_szEndDate, mi_lTempInteger_4, 'S', 11, mSAProf2, "PeriodCollegeTerm", "CourseEndDate", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szEndDate = sb_szEndDate.toString( );}
            //:ELSE 
         } 
         else
         { 
            //:szEndDate = mSAProf2.PeriodCollegeTerm.ResidencyEndDate
            {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
            StringBuilder sb_szEndDate;
            if ( szEndDate == null )
               sb_szEndDate = new StringBuilder( 32 );
            else
               sb_szEndDate = new StringBuilder( szEndDate );
                         GetVariableFromAttribute( sb_szEndDate, mi_lTempInteger_5, 'S', 11, mSAProf2, "PeriodCollegeTerm", "ResidencyEndDate", "", 0 );
            lTempInteger_5 = mi_lTempInteger_5.intValue( );
            szEndDate = sb_szEndDate.toString( );}
         } 

         //:END 
         //:IF mSAProf2.TermOfResidence.BeginDate >= szStartDate AND
         //:   mSAProf2.TermOfResidence.EndDate   <= szEndDate 
         if ( CompareAttributeToString( mSAProf2, "TermOfResidence", "BeginDate", szStartDate ) >= 0 && CompareAttributeToString( mSAProf2, "TermOfResidence", "EndDate", szEndDate ) <= 0 )
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
            //:IF szStartDate >= mSAProf2.TermOfResidence.BeginDate AND
            //:   szStartDate <= mSAProf2.TermOfResidence.EndDate
            if ( CompareAttributeToString( mSAProf2, "TermOfResidence", "BeginDate", szStartDate ) <= 0 && CompareAttributeToString( mSAProf2, "TermOfResidence", "EndDate", szStartDate ) >= 0 )
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

      RESULT = SetCursorNextEntity( mSAProf2, "TermOfResidence", "" );
      //:END 
   } 

   //:END
   //:IF mSAProf.BillingTermOfResidence  DOES not EXIST
   lTempInteger_6 = CheckExistenceOfEntity( mSAProf, "BillingTermOfResidence" );
   if ( lTempInteger_6 != 0 )
   { 
      //:OrderEntityForView( mSAProf2, "TermOfResidence", "BeginDate D" )
      OrderEntityForView( mSAProf2, "TermOfResidence", "BeginDate D" );
      //:SET CURSOR FIRST mSAProf2.TermOfResidence 
      RESULT = SetCursorFirstEntity( mSAProf2, "TermOfResidence", "" );
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
//:LocateMealPlan( VIEW mSAProf BASED ON LOD mSAProf )
//:   STRING ( 10 ) szStartDate
public int 
omSAProf_LocateMealPlan( View     mSAProf )
{
   String   szStartDate = null;
   //:STRING ( 10 ) szEndDate
   String   szEndDate = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;


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
   RESULT = SetCursorFirstEntity( mSAProf, "BillingTermOfMealPlan", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:EXCLUDE  mSAProf.BillingTermOfMealPlan NONE  
      RESULT = ExcludeEntity( mSAProf, "BillingTermOfMealPlan", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSAProf, "BillingTermOfMealPlan", "" );
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
   //: 
   //:IF mSAProf.PeriodCollegeTerm.ResidencyStartDate = ""
   if ( CompareAttributeToString( mSAProf, "PeriodCollegeTerm", "ResidencyStartDate", "" ) == 0 )
   { 
      //: szStartDate = mSAProf.PeriodCollegeTerm.CourseStartDate
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szStartDate;
      if ( szStartDate == null )
         sb_szStartDate = new StringBuilder( 32 );
      else
         sb_szStartDate = new StringBuilder( szStartDate );
             GetVariableFromAttribute( sb_szStartDate, mi_lTempInteger_2, 'S', 11, mSAProf, "PeriodCollegeTerm", "CourseStartDate", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szStartDate = sb_szStartDate.toString( );}
      //:ELSE 
   } 
   else
   { 
      //: szStartDate = mSAProf.PeriodCollegeTerm.ResidencyStartDate
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szStartDate;
      if ( szStartDate == null )
         sb_szStartDate = new StringBuilder( 32 );
      else
         sb_szStartDate = new StringBuilder( szStartDate );
             GetVariableFromAttribute( sb_szStartDate, mi_lTempInteger_3, 'S', 11, mSAProf, "PeriodCollegeTerm", "ResidencyStartDate", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szStartDate = sb_szStartDate.toString( );}
   } 

   //:END 
   //:IF mSAProf.PeriodCollegeTerm.ResidencyEndDate = ""
   if ( CompareAttributeToString( mSAProf, "PeriodCollegeTerm", "ResidencyEndDate", "" ) == 0 )
   { 
      //: szEndDate = mSAProf.PeriodCollegeTerm.CourseEndDate
      {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
      StringBuilder sb_szEndDate;
      if ( szEndDate == null )
         sb_szEndDate = new StringBuilder( 32 );
      else
         sb_szEndDate = new StringBuilder( szEndDate );
             GetVariableFromAttribute( sb_szEndDate, mi_lTempInteger_4, 'S', 11, mSAProf, "PeriodCollegeTerm", "CourseEndDate", "", 0 );
      lTempInteger_4 = mi_lTempInteger_4.intValue( );
      szEndDate = sb_szEndDate.toString( );}
      //:ELSE 
   } 
   else
   { 
      //: szEndDate = mSAProf.PeriodCollegeTerm.ResidencyEndDate
      {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
      StringBuilder sb_szEndDate;
      if ( szEndDate == null )
         sb_szEndDate = new StringBuilder( 32 );
      else
         sb_szEndDate = new StringBuilder( szEndDate );
             GetVariableFromAttribute( sb_szEndDate, mi_lTempInteger_5, 'S', 11, mSAProf, "PeriodCollegeTerm", "ResidencyEndDate", "", 0 );
      lTempInteger_5 = mi_lTempInteger_5.intValue( );
      szEndDate = sb_szEndDate.toString( );}
   } 

   //:END 
   //: 
   //:SET CURSOR FIRST mSAProf.TermOfMealPlan 
   //:           WHERE mSAProf.TermOfMealPlan.BeginDate >= szStartDate 
   //:             AND mSAProf.TermOfMealPlan.BeginDate <= szEndDate 
   //:             AND mSAProf.TermOfMealPlan.WithdrawalDate = ""
   RESULT = SetCursorFirstEntity( mSAProf, "TermOfMealPlan", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSAProf, "TermOfMealPlan", "BeginDate", szStartDate ) < 0 || CompareAttributeToString( mSAProf, "TermOfMealPlan", "BeginDate", szEndDate ) > 0 ||
              CompareAttributeToString( mSAProf, "TermOfMealPlan", "WithdrawalDate", "" ) != 0 ) )
      { 
         RESULT = SetCursorNextEntity( mSAProf, "TermOfMealPlan", "" );
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
   RESULT = SetCursorFirstEntity( mSAProf, "TermOfMealPlan", "" );
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
   //:STRING ( 8 )  szCurrentDate
   String   szCurrentDate = null;
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
   int      lTempInteger_22 = 0;
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   int      lTempInteger_23 = 0;
   String   szTempString_6 = null;
   int      lTempInteger_24 = 0;
   int      lTempInteger_25 = 0;
   int      lTempInteger_26 = 0;
   int      lTempInteger_27 = 0;
   String   szTempString_7 = null;
   int      lTempInteger_28 = 0;
   int      lTempInteger_29 = 0;
   int      lTempInteger_30 = 0;
   int      lTempInteger_31 = 0;
   String   szTempString_8 = null;
   String   szTempString_9 = null;
   int      lTempInteger_32 = 0;
   String   szTempString_10 = null;
   int      lTempInteger_33 = 0;
   int      lTempInteger_34 = 0;
   int      lTempInteger_35 = 0;
   int      lTempInteger_36 = 0;
   int      lTempInteger_37 = 0;
   String   szTempString_11 = null;
   String   szTempString_12 = null;
   int      lTempInteger_38 = 0;
   String   szTempString_13 = null;
   int      lTempInteger_39 = 0;
   int      lTempInteger_40 = 0;
   int      lTempInteger_41 = 0;
   int      lTempInteger_42 = 0;
   String   szTempString_14 = null;

   RESULT = GetViewByName( mPerson, "mPerson", mSAProf, zLEVEL_TASK );
   RESULT = GetViewByName( mSAChrgIT, "mSAChrgIT", mSAProf, zLEVEL_TASK );
   RESULT = GetViewByName( mSAChrgITA, "mSAChrgITA", mSAProf, zLEVEL_TASK );
   RESULT = GetViewByName( mSAChrgITC, "mSAChrgITC", mSAProf, zLEVEL_TASK );
   RESULT = GetViewByName( mSAChrgITF, "mSAChrgITF", mSAProf, zLEVEL_TASK );
   RESULT = GetViewByName( wXferO, "wXferO", mSAProf, zLEVEL_TASK );

   //:// This routine generates Refunds and Adjustments.
   //:// It loops through each current SA Transaction and evaluates it to see if it is still applicable.
   //:// If not, it generates a full refund or a partial refund (adjustment) depending on refund rules.

   //:CreateViewFromView( mSAProf2, mSAProf ) 
   CreateViewFromView( mSAProf2, mSAProf );
   //:NAME VIEW mSAProf2 "mSAProf2"
   SetNameForView( mSAProf2, "mSAProf2", null, zLEVEL_TASK );
   //:/// loop through each transaction for the currently positioned billing period
   //:FOR EACH mSAProf2.PeriodTransApplied
   RESULT = SetCursorFirstEntity( mSAProf2, "PeriodTransApplied", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// We will not process adjusting transactions or transactions that have been adjusted.
      //:IF mSAProf2.PeriodAdjustedSATransApplied DOES NOT EXIST AND
      lTempInteger_0 = CheckExistenceOfEntity( mSAProf2, "PeriodAdjustedSATransApplied" );
      //:   mSAProf2.PeriodAdjustingSATransApplied DOES NOT EXIST
      lTempInteger_1 = CheckExistenceOfEntity( mSAProf2, "PeriodAdjustingSATransApplied" );
      if ( lTempInteger_0 != 0 && lTempInteger_1 != 0 )
      { 

         //:IF mSAProf2.PeriodTransApplied.ID != "" // not a new one just added
         if ( CompareAttributeToString( mSAProf2, "PeriodTransApplied", "ID", "" ) != 0 )
         { 
            //:nDone = 0 
            nDone = 0;
            //:// get the transaction code 
            //:nTransCodeID = mSAProf2.PeriodSATransactionCode.ID 
            {MutableInt mi_nTransCodeID = new MutableInt( nTransCodeID );
                         GetIntegerFromAttribute( mi_nTransCodeID, mSAProf2, "PeriodSATransactionCode", "ID" );
            nTransCodeID = mi_nTransCodeID.intValue( );}

            //://
            //:// TUITION Charges
            //://
            //:SET CURSOR FIRST mSAChrgIT.SATransactionCode WHERE mSAChrgIT.SATransactionCode.ID = nTransCodeID 
            RESULT = SetCursorFirstEntityByInteger( mSAChrgIT, "SATransactionCode", "ID", nTransCodeID, "" );
            //:IF RESULT >= zCURSOR_SET 
            if ( RESULT >= zCURSOR_SET )
            { 

               //:// If TransactionCode is handled by an internal function, go to process it.
               //:IF mSAChrgIT.SATransactionCode.InternalRuleName != ""
               if ( CompareAttributeToString( mSAChrgIT, "SATransactionCode", "InternalRuleName", "" ) != 0 )
               { 

                  //:// Internal Rule Refund
                  //:ProcessInternalRule( mSAChrgIT, mSAProf2, "Y" )  // Check rule and create transaction as necessary.
                  {
                   mSAChrgT_Object m_mSAChrgT_Object = new mSAChrgT_Object( mSAChrgIT );
                   m_mSAChrgT_Object.omSAChrgT_ProcessInternalRule( mSAChrgIT, mSAProf2, "Y" );
                   // m_mSAChrgT_Object = null;  // permit gc  (unnecessary)
                  }

                  //:ELSE
               } 
               else
               { 
                  //:// If there is an external rule, see if it still applies
                  //:IF mSAChrgIT.StudentAccountRuleSet EXISTS 
                  lTempInteger_2 = CheckExistenceOfEntity( mSAChrgIT, "StudentAccountRuleSet" );
                  if ( lTempInteger_2 == 0 )
                  { 
                     //:szNumber = mSAChrgIT.StudentAccountRuleSet.ID 
                     {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                     StringBuilder sb_szNumber;
                     if ( szNumber == null )
                        sb_szNumber = new StringBuilder( 32 );
                     else
                        sb_szNumber = new StringBuilder( szNumber );
                                           GetVariableFromAttribute( sb_szNumber, mi_lTempInteger_3, 'S', 33, mSAChrgIT, "StudentAccountRuleSet", "ID", "", 0 );
                     lTempInteger_3 = mi_lTempInteger_3.intValue( );
                     szNumber = sb_szNumber.toString( );}
                     //:szRuleName = "SA_RULE" + szNumber
                      {StringBuilder sb_szRuleName;
                     if ( szRuleName == null )
                        sb_szRuleName = new StringBuilder( 32 );
                     else
                        sb_szRuleName = new StringBuilder( szRuleName );
                                          ZeidonStringCopy( sb_szRuleName, 1, 0, "SA_RULE", 1, 0, 33 );
                     szRuleName = sb_szRuleName.toString( );}
                      {StringBuilder sb_szRuleName;
                     if ( szRuleName == null )
                        sb_szRuleName = new StringBuilder( 32 );
                     else
                        sb_szRuleName = new StringBuilder( szRuleName );
                                          ZeidonStringConcat( sb_szRuleName, 1, 0, szNumber, 1, 0, 33 );
                     szRuleName = sb_szRuleName.toString( );}
                     //:GET VIEW mSARuleS NAMED szRuleName
                     RESULT = GetViewByName( mSARuleS, szRuleName, mSAProf, zLEVEL_TASK );
                     //:IF RESULT <= 0
                     if ( RESULT <= 0 )
                     { 
                        //:ACTIVATE mSARuleS WHERE mSARuleS.StudentAccountRuleSet.ID = mSAChrgIT.StudentAccountRuleSet.ID 
                        {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                                                 GetIntegerFromAttribute( mi_lTempInteger_4, mSAChrgIT, "StudentAccountRuleSet", "ID" );
                        lTempInteger_4 = mi_lTempInteger_4.intValue( );}
                        omSAProf_fnLocalBuildQual_11( mSAProf, vTempViewVar_0, lTempInteger_4 );
                        RESULT = ActivateObjectInstance( mSARuleS, "mSARuleS", mSAProf, vTempViewVar_0, zSINGLE );
                        DropView( vTempViewVar_0 );
                        //:NAME VIEW mSARuleS szRuleName
                        SetNameForView( mSARuleS, szRuleName, null, zLEVEL_TASK );
                     } 

                     //:END 
                     //:nRC = QualifyRuleSet( mSARuleS, mSAProf2, mPerson )
                     {
                      mSARuleS_Object m_mSARuleS_Object = new mSARuleS_Object( mSARuleS );
                      nRC = m_mSARuleS_Object.omSARuleS_QualifyRuleSet( mSARuleS, mSAProf2, mPerson );
                      // m_mSARuleS_Object = null;  // permit gc  (unnecessary)
                     }
                     //:IF nRC > 0  
                     if ( nRC > 0 )
                     { 
                        //:// Found it and rule still applies so jump out
                        //:nDone = 1
                        nDone = 1;
                        //:ELSE 
                     } 
                     else
                     { 
                        //:// If we haven't passed the add/drop deadline date or start of class date, process full refund.
                        //:// The date we will use to determine this is the latest DroppedDate for an enrollment entry.
                        //:// Note for an external rule, we will only process a full refund. A partial refund requires using an
                        //:// Internal Rule, which is specified on the Transaction Code and checked above.
                        //:szLatestDroppedDate = ""
                         {StringBuilder sb_szLatestDroppedDate;
                        if ( szLatestDroppedDate == null )
                           sb_szLatestDroppedDate = new StringBuilder( 32 );
                        else
                           sb_szLatestDroppedDate = new StringBuilder( szLatestDroppedDate );
                                                ZeidonStringCopy( sb_szLatestDroppedDate, 1, 0, "", 1, 0, 9 );
                        szLatestDroppedDate = sb_szLatestDroppedDate.toString( );}
                        //:FOR EACH mSAProf2.CurrentCollegeTerm WITHIN mSAProf2.Student WHERE
                        //:         mSAProf2.CurrentCollegeTerm.ID = mSAProf2.PeriodCollegeTerm.ID 
                        {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                                                 GetIntegerFromAttribute( mi_lTempInteger_5, mSAProf2, "PeriodCollegeTerm", "ID" );
                        lTempInteger_5 = mi_lTempInteger_5.intValue( );}
                        RESULT = SetCursorFirstEntityByInteger( mSAProf2, "CurrentCollegeTerm", "ID", lTempInteger_5, "Student" );
                        while ( RESULT > zCURSOR_UNCHANGED )
                        { 
                           //:      
                           //:IF mSAProf2.Enrolled.DroppedDate > szLatestDroppedDate
                           if ( CompareAttributeToString( mSAProf2, "Enrolled", "DroppedDate", szLatestDroppedDate ) > 0 )
                           { 
                              //:szLatestDroppedDate     = mSAProf2.Enrolled.DroppedDate 
                              {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                              StringBuilder sb_szLatestDroppedDate;
                              if ( szLatestDroppedDate == null )
                                 sb_szLatestDroppedDate = new StringBuilder( 32 );
                              else
                                 sb_szLatestDroppedDate = new StringBuilder( szLatestDroppedDate );
                                                             GetVariableFromAttribute( sb_szLatestDroppedDate, mi_lTempInteger_6, 'S', 9, mSAProf2, "Enrolled", "DroppedDate", "", 0 );
                              lTempInteger_6 = mi_lTempInteger_6.intValue( );
                              szLatestDroppedDate = sb_szLatestDroppedDate.toString( );}
                              //:szDroppedClassStartDate = mSAProf2.Class.ClassStartDate
                              {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                              StringBuilder sb_szDroppedClassStartDate;
                              if ( szDroppedClassStartDate == null )
                                 sb_szDroppedClassStartDate = new StringBuilder( 32 );
                              else
                                 sb_szDroppedClassStartDate = new StringBuilder( szDroppedClassStartDate );
                                                             GetVariableFromAttribute( sb_szDroppedClassStartDate, mi_lTempInteger_7, 'S', 9, mSAProf2, "Class", "ClassStartDate", "", 0 );
                              lTempInteger_7 = mi_lTempInteger_7.intValue( );
                              szDroppedClassStartDate = sb_szDroppedClassStartDate.toString( );}
                           } 

                           RESULT = SetCursorNextEntityByInteger( mSAProf2, "CurrentCollegeTerm", "ID", lTempInteger_5, "Student" );
                           //:END
                        } 

                        //:END
                        //:// If we haven't dropped a Class after the deadline or start of class, reverse it.
                        //:IF szLatestDroppedDate <= mSAProf2.PeriodCollegeTerm.AddDropDeadlineDate OR 
                        //:   szLatestDroppedDate <= szDroppedClassStartDate
                        if ( CompareAttributeToString( mSAProf2, "PeriodCollegeTerm", "AddDropDeadlineDate", szLatestDroppedDate ) >= 0 || ZeidonStringCompare( szLatestDroppedDate, 1, 0, szDroppedClassStartDate, 1, 0, 9 ) >= 0 )
                        { 

                           //:dAmount = mSAProf2.PeriodTransApplied.Amount
                           {MutableDouble md_dAmount = new MutableDouble( dAmount );
                                                       GetDecimalFromAttribute( md_dAmount, mSAProf2, "PeriodTransApplied", "Amount" );
                           dAmount = md_dAmount.doubleValue( );}
                           //:dAmount = dAmount * -1
                           dAmount = dAmount * -1;
                           //:GenerateCharge( mSAProf, mSAProf.PeriodCollegeTerm.ID, 
                           //:                nTransCodeID, 
                           //:                dAmount, "N", "" )
                           {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                                                       GetIntegerFromAttribute( mi_lTempInteger_8, mSAProf, "PeriodCollegeTerm", "ID" );
                           lTempInteger_8 = mi_lTempInteger_8.intValue( );}
                           omSAProf_GenerateCharge( mSAProf, lTempInteger_8, nTransCodeID, dAmount, "N", "" );
                           //:// Override Description generated in GenerateCharge to use original description plus the word, "(Refund)"
                           //:mSAProf.StudentAccountTransApplied.Description = mSAProf2.PeriodTransApplied.Description + " (Refund)"
                           {StringBuilder sb_szTempString_0;
                           if ( szTempString_0 == null )
                              sb_szTempString_0 = new StringBuilder( 32 );
                           else
                              sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                                       GetStringFromAttribute( sb_szTempString_0, mSAProf2, "PeriodTransApplied", "Description" );
                           szTempString_0 = sb_szTempString_0.toString( );}
                            {StringBuilder sb_szTempString_0;
                           if ( szTempString_0 == null )
                              sb_szTempString_0 = new StringBuilder( 32 );
                           else
                              sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                                      ZeidonStringConcat( sb_szTempString_0, 1, 0, " (Refund)", 1, 0, 255 );
                           szTempString_0 = sb_szTempString_0.toString( );}
                           SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szTempString_0 );
                           //:AcceptSubobject( mSAProf, "StudentAccountTransApplied" )
                           AcceptSubobject( mSAProf, "StudentAccountTransApplied" );
                           //:INCLUDE mSAProf.AdjustedSATransApplied FROM mSAProf2.PeriodTransApplied
                           RESULT = IncludeSubobjectFromSubobject( mSAProf, "AdjustedSATransApplied", mSAProf2, "PeriodTransApplied", zPOS_AFTER );
                           //:ELSE
                        } 
                        else
                        { 
                           //:// We will give the User a message indicating classes dropped after deadline or start of class.
                           //:szMsg = "Potential Refund Error for " + mSAProf.Person.dFullNameLFM + ":" + NEW_LINE +
                           //:        "A class has been dropped after the Term Drop Date Deadline or Class Start Date. " +
                           //:        "Refund rules are not processed automatically in this case, so charges may need to be evaluated manually."
                           {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
                           StringBuilder sb_szTempString_1;
                           if ( szTempString_1 == null )
                              sb_szTempString_1 = new StringBuilder( 32 );
                           else
                              sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                                       GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_9, 'S', 255, mSAProf, "Person", "dFullNameLFM", "", 0 );
                           lTempInteger_9 = mi_lTempInteger_9.intValue( );
                           szTempString_1 = sb_szTempString_1.toString( );}
                            {StringBuilder sb_szMsg;
                           if ( szMsg == null )
                              sb_szMsg = new StringBuilder( 32 );
                           else
                              sb_szMsg = new StringBuilder( szMsg );
                                                      ZeidonStringCopy( sb_szMsg, 1, 0, "Potential Refund Error for ", 1, 0, 301 );
                           szMsg = sb_szMsg.toString( );}
                            {StringBuilder sb_szMsg;
                           if ( szMsg == null )
                              sb_szMsg = new StringBuilder( 32 );
                           else
                              sb_szMsg = new StringBuilder( szMsg );
                                                      ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_1, 1, 0, 301 );
                           szMsg = sb_szMsg.toString( );}
                            {StringBuilder sb_szMsg;
                           if ( szMsg == null )
                              sb_szMsg = new StringBuilder( 32 );
                           else
                              sb_szMsg = new StringBuilder( szMsg );
                                                      ZeidonStringConcat( sb_szMsg, 1, 0, ":", 1, 0, 301 );
                           szMsg = sb_szMsg.toString( );}
                            {StringBuilder sb_szMsg;
                           if ( szMsg == null )
                              sb_szMsg = new StringBuilder( 32 );
                           else
                              sb_szMsg = new StringBuilder( szMsg );
                                                      ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 301 );
                           szMsg = sb_szMsg.toString( );}
                            {StringBuilder sb_szMsg;
                           if ( szMsg == null )
                              sb_szMsg = new StringBuilder( 32 );
                           else
                              sb_szMsg = new StringBuilder( szMsg );
                                                      ZeidonStringConcat( sb_szMsg, 1, 0, "A class has been dropped after the Term Drop Date Deadline or Class Start Date. ", 1, 0, 301 );
                           szMsg = sb_szMsg.toString( );}
                            {StringBuilder sb_szMsg;
                           if ( szMsg == null )
                              sb_szMsg = new StringBuilder( 32 );
                           else
                              sb_szMsg = new StringBuilder( szMsg );
                                                      ZeidonStringConcat( sb_szMsg, 1, 0, "Refund rules are not processed automatically in this case, so charges may need to be evaluated manually.", 1, 0, 301 );
                           szMsg = sb_szMsg.toString( );}
                           //:MessageSend( mSAProf, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
                           MessageSend( mSAProf, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                        } 

                        //:END
                     } 

                     //:END 
                  } 

                  //:END 
               } 

               //:END

               //:nDone = 1   // Since this was a Tuition Tran Code, jump out.
               nDone = 1;
            } 

            //:END

            //:// 
            //:// TUITION Adjustments
            //:// 
            //:IF nDone = 0 
            if ( nDone == 0 )
            { 
               //:SET CURSOR FIRST mSAChrgITA.SATransactionCode WHERE mSAChrgITA.SATransactionCode.ID = nTransCodeID 
               RESULT = SetCursorFirstEntityByInteger( mSAChrgITA, "SATransactionCode", "ID", nTransCodeID, "" );
               //:IF RESULT >= zCURSOR_SET 
               if ( RESULT >= zCURSOR_SET )
               { 

                  //:// If TransactionCode is handled by an internal function, go to process it.
                  //:IF mSAChrgITA.SATransactionCode.InternalRuleName != ""
                  if ( CompareAttributeToString( mSAChrgITA, "SATransactionCode", "InternalRuleName", "" ) != 0 )
                  { 

                     //:// Internal Rule Refund
                     //:ProcessInternalRule( mSAChrgITA, mSAProf2, "Y" )  // Check rule and create transaction as necessary.
                     {
                      mSAChrgT_Object m_mSAChrgT_Object = new mSAChrgT_Object( mSAChrgITA );
                      m_mSAChrgT_Object.omSAChrgT_ProcessInternalRule( mSAChrgITA, mSAProf2, "Y" );
                      // m_mSAChrgT_Object = null;  // permit gc  (unnecessary)
                     }

                     //:ELSE
                  } 
                  else
                  { 

                     //:// If there is an external rule, see if it still applies
                     //:IF mSAChrgITA.StudentAccountRuleSet EXISTS 
                     lTempInteger_10 = CheckExistenceOfEntity( mSAChrgITA, "StudentAccountRuleSet" );
                     if ( lTempInteger_10 == 0 )
                     { 
                        //:szNumber = mSAChrgITA.StudentAccountRuleSet.ID 
                        {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
                        StringBuilder sb_szNumber;
                        if ( szNumber == null )
                           sb_szNumber = new StringBuilder( 32 );
                        else
                           sb_szNumber = new StringBuilder( szNumber );
                                                 GetVariableFromAttribute( sb_szNumber, mi_lTempInteger_11, 'S', 33, mSAChrgITA, "StudentAccountRuleSet", "ID", "", 0 );
                        lTempInteger_11 = mi_lTempInteger_11.intValue( );
                        szNumber = sb_szNumber.toString( );}
                        //:szRuleName = "SA_RULE" + szNumber
                         {StringBuilder sb_szRuleName;
                        if ( szRuleName == null )
                           sb_szRuleName = new StringBuilder( 32 );
                        else
                           sb_szRuleName = new StringBuilder( szRuleName );
                                                ZeidonStringCopy( sb_szRuleName, 1, 0, "SA_RULE", 1, 0, 33 );
                        szRuleName = sb_szRuleName.toString( );}
                         {StringBuilder sb_szRuleName;
                        if ( szRuleName == null )
                           sb_szRuleName = new StringBuilder( 32 );
                        else
                           sb_szRuleName = new StringBuilder( szRuleName );
                                                ZeidonStringConcat( sb_szRuleName, 1, 0, szNumber, 1, 0, 33 );
                        szRuleName = sb_szRuleName.toString( );}
                        //:GET VIEW mSARuleS NAMED szRuleName
                        RESULT = GetViewByName( mSARuleS, szRuleName, mSAProf, zLEVEL_TASK );
                        //:IF RESULT <= 0
                        if ( RESULT <= 0 )
                        { 
                           //:ACTIVATE mSARuleS WHERE mSARuleS.StudentAccountRuleSet.ID = mSAChrgITA.StudentAccountRuleSet.ID 
                           {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
                                                       GetIntegerFromAttribute( mi_lTempInteger_12, mSAChrgITA, "StudentAccountRuleSet", "ID" );
                           lTempInteger_12 = mi_lTempInteger_12.intValue( );}
                           omSAProf_fnLocalBuildQual_12( mSAProf, vTempViewVar_1, lTempInteger_12 );
                           RESULT = ActivateObjectInstance( mSARuleS, "mSARuleS", mSAProf, vTempViewVar_1, zSINGLE );
                           DropView( vTempViewVar_1 );
                           //:NAME VIEW mSARuleS szRuleName
                           SetNameForView( mSARuleS, szRuleName, null, zLEVEL_TASK );
                        } 

                        //:END 
                        //:nRC = QualifyRuleSet( mSARuleS, mSAProf2, mPerson )
                        {
                         mSARuleS_Object m_mSARuleS_Object = new mSARuleS_Object( mSARuleS );
                         nRC = m_mSARuleS_Object.omSARuleS_QualifyRuleSet( mSARuleS, mSAProf2, mPerson );
                         // m_mSARuleS_Object = null;  // permit gc  (unnecessary)
                        }
                        //:IF nRC > 0 
                        if ( nRC > 0 )
                        { 
                           //:// Found it and rule still applies so jump out.
                           //:// Note that this code doesn't check the situation where the Amount has changed.
                           //:nDone = 1
                           nDone = 1;
                           //:ELSE 
                        } 
                        else
                        { 
                           //:// If we haven't passed the add/drop deadline date or start of class date, process full refund.
                           //:// The date we will use to determine this is the latest DroppedDate for an enrollment entry.
                           //:// Note for an external rule, we will only process a full refund. A partial refund requires using an
                           //:// Internal Rule, which is specified on the Transaction Code and checked above.
                           //:szLatestDroppedDate = ""
                            {StringBuilder sb_szLatestDroppedDate;
                           if ( szLatestDroppedDate == null )
                              sb_szLatestDroppedDate = new StringBuilder( 32 );
                           else
                              sb_szLatestDroppedDate = new StringBuilder( szLatestDroppedDate );
                                                      ZeidonStringCopy( sb_szLatestDroppedDate, 1, 0, "", 1, 0, 9 );
                           szLatestDroppedDate = sb_szLatestDroppedDate.toString( );}
                           //:FOR EACH mSAProf2.CurrentCollegeTerm WITHIN mSAProf2.Student WHERE
                           //:         mSAProf2.CurrentCollegeTerm.ID = mSAProf2.PeriodCollegeTerm.ID 
                           {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
                                                       GetIntegerFromAttribute( mi_lTempInteger_13, mSAProf2, "PeriodCollegeTerm", "ID" );
                           lTempInteger_13 = mi_lTempInteger_13.intValue( );}
                           RESULT = SetCursorFirstEntityByInteger( mSAProf2, "CurrentCollegeTerm", "ID", lTempInteger_13, "Student" );
                           while ( RESULT > zCURSOR_UNCHANGED )
                           { 
                              //:      
                              //:IF mSAProf2.Enrolled.DroppedDate > szLatestDroppedDate
                              if ( CompareAttributeToString( mSAProf2, "Enrolled", "DroppedDate", szLatestDroppedDate ) > 0 )
                              { 
                                 //:szLatestDroppedDate     = mSAProf2.Enrolled.DroppedDate 
                                 {MutableInt mi_lTempInteger_14 = new MutableInt( lTempInteger_14 );
                                 StringBuilder sb_szLatestDroppedDate;
                                 if ( szLatestDroppedDate == null )
                                    sb_szLatestDroppedDate = new StringBuilder( 32 );
                                 else
                                    sb_szLatestDroppedDate = new StringBuilder( szLatestDroppedDate );
                                                                   GetVariableFromAttribute( sb_szLatestDroppedDate, mi_lTempInteger_14, 'S', 9, mSAProf2, "Enrolled", "DroppedDate", "", 0 );
                                 lTempInteger_14 = mi_lTempInteger_14.intValue( );
                                 szLatestDroppedDate = sb_szLatestDroppedDate.toString( );}
                                 //:szDroppedClassStartDate = mSAProf2.Class.ClassStartDate
                                 {MutableInt mi_lTempInteger_15 = new MutableInt( lTempInteger_15 );
                                 StringBuilder sb_szDroppedClassStartDate;
                                 if ( szDroppedClassStartDate == null )
                                    sb_szDroppedClassStartDate = new StringBuilder( 32 );
                                 else
                                    sb_szDroppedClassStartDate = new StringBuilder( szDroppedClassStartDate );
                                                                   GetVariableFromAttribute( sb_szDroppedClassStartDate, mi_lTempInteger_15, 'S', 9, mSAProf2, "Class", "ClassStartDate", "", 0 );
                                 lTempInteger_15 = mi_lTempInteger_15.intValue( );
                                 szDroppedClassStartDate = sb_szDroppedClassStartDate.toString( );}
                              } 

                              RESULT = SetCursorNextEntityByInteger( mSAProf2, "CurrentCollegeTerm", "ID", lTempInteger_13, "Student" );
                              //:END
                           } 

                           //:END
                           //:// If we haven't dropped a Class after the deadline or start of class, reverse it.
                           //:IF szLatestDroppedDate <= mSAProf2.PeriodCollegeTerm.AddDropDeadlineDate OR 
                           //:   szLatestDroppedDate <= szDroppedClassStartDate
                           if ( CompareAttributeToString( mSAProf2, "PeriodCollegeTerm", "AddDropDeadlineDate", szLatestDroppedDate ) >= 0 || ZeidonStringCompare( szLatestDroppedDate, 1, 0, szDroppedClassStartDate, 1, 0, 9 ) >= 0 )
                           { 

                              //:dAmount = mSAProf2.PeriodTransApplied.Amount
                              {MutableDouble md_dAmount = new MutableDouble( dAmount );
                                                             GetDecimalFromAttribute( md_dAmount, mSAProf2, "PeriodTransApplied", "Amount" );
                              dAmount = md_dAmount.doubleValue( );}
                              //:dAmount = dAmount * -1
                              dAmount = dAmount * -1;
                              //:GenerateCharge( mSAProf, mSAProf.PeriodCollegeTerm.ID, 
                              //:                nTransCodeID, 
                              //:                dAmount, "N", "" )
                              {MutableInt mi_lTempInteger_16 = new MutableInt( lTempInteger_16 );
                                                             GetIntegerFromAttribute( mi_lTempInteger_16, mSAProf, "PeriodCollegeTerm", "ID" );
                              lTempInteger_16 = mi_lTempInteger_16.intValue( );}
                              omSAProf_GenerateCharge( mSAProf, lTempInteger_16, nTransCodeID, dAmount, "N", "" );
                              //:// Override Description generated in GenerateCharge to use original description plus the word, "(Refund)"
                              //:mSAProf.StudentAccountTransApplied.Description = mSAProf2.PeriodTransApplied.Description + " (Refund)"
                              {StringBuilder sb_szTempString_2;
                              if ( szTempString_2 == null )
                                 sb_szTempString_2 = new StringBuilder( 32 );
                              else
                                 sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                                             GetStringFromAttribute( sb_szTempString_2, mSAProf2, "PeriodTransApplied", "Description" );
                              szTempString_2 = sb_szTempString_2.toString( );}
                               {StringBuilder sb_szTempString_2;
                              if ( szTempString_2 == null )
                                 sb_szTempString_2 = new StringBuilder( 32 );
                              else
                                 sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                                            ZeidonStringConcat( sb_szTempString_2, 1, 0, " (Refund)", 1, 0, 255 );
                              szTempString_2 = sb_szTempString_2.toString( );}
                              SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szTempString_2 );
                              //:AcceptSubobject( mSAProf, "StudentAccountTransApplied" )
                              AcceptSubobject( mSAProf, "StudentAccountTransApplied" );
                              //:INCLUDE mSAProf.AdjustedSATransApplied FROM mSAProf2.PeriodTransApplied
                              RESULT = IncludeSubobjectFromSubobject( mSAProf, "AdjustedSATransApplied", mSAProf2, "PeriodTransApplied", zPOS_AFTER );
                              //:ELSE
                           } 
                           else
                           { 
                              //:// We will give the User a message indicating classes dropped after deadline or start of class.
                              //:szMsg = "Potential Refund Error for " + mSAProf.Person.dFullNameLFM + ":" + NEW_LINE +
                              //:        "A class has been dropped after the Term Drop Date Deadline or Class Start Date. " +
                              //:        "Refund rules are not processed automatically in this case, so charges may need to be evaluated manually."
                              {MutableInt mi_lTempInteger_17 = new MutableInt( lTempInteger_17 );
                              StringBuilder sb_szTempString_3;
                              if ( szTempString_3 == null )
                                 sb_szTempString_3 = new StringBuilder( 32 );
                              else
                                 sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                                             GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_17, 'S', 255, mSAProf, "Person", "dFullNameLFM", "", 0 );
                              lTempInteger_17 = mi_lTempInteger_17.intValue( );
                              szTempString_3 = sb_szTempString_3.toString( );}
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringCopy( sb_szMsg, 1, 0, "Potential Refund Error for ", 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_3, 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringConcat( sb_szMsg, 1, 0, ":", 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringConcat( sb_szMsg, 1, 0, "A class has been dropped after the Term Drop Date Deadline or Class Start Date. ", 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringConcat( sb_szMsg, 1, 0, "Refund rules are not processed automatically in this case, so charges may need to be evaluated manually.", 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                              //:MessageSend( mSAProf, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
                              MessageSend( mSAProf, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                           } 

                           //:END
                        } 

                        //:END 
                     } 

                     //:END
                  } 

                  //:END 

                  //:nDone = 1   // Since this was a Tuition Adjustment Tran Code, jump out.
                  nDone = 1;
               } 

               //:END
            } 

            //:END
            //://
            //:// MISC Fees
            //://
            //:IF nDone = 0 
            if ( nDone == 0 )
            { 
               //:SET CURSOR FIRST mSAChrgITF.SATransactionCode WHERE mSAChrgITF.SATransactionCode.ID = nTransCodeID 
               RESULT = SetCursorFirstEntityByInteger( mSAChrgITF, "SATransactionCode", "ID", nTransCodeID, "" );
               //:IF RESULT >= zCURSOR_SET 
               if ( RESULT >= zCURSOR_SET )
               { 

                  //:// If TransactionCode is handled by an internal function, go to process it.
                  //:IF mSAChrgITF.SATransactionCode.InternalRuleName != ""
                  if ( CompareAttributeToString( mSAChrgITF, "SATransactionCode", "InternalRuleName", "" ) != 0 )
                  { 

                     //:// Internal Rule Refund
                     //:ProcessInternalRule( mSAChrgITF, mSAProf2, "Y" )  // Check rule and create transaction as necessary.
                     {
                      mSAChrgT_Object m_mSAChrgT_Object = new mSAChrgT_Object( mSAChrgITF );
                      m_mSAChrgT_Object.omSAChrgT_ProcessInternalRule( mSAChrgITF, mSAProf2, "Y" );
                      // m_mSAChrgT_Object = null;  // permit gc  (unnecessary)
                     }

                     //:ELSE
                  } 
                  else
                  { 

                     //:IF mSAChrgITF.StudentAccountRuleSet EXISTS 
                     lTempInteger_18 = CheckExistenceOfEntity( mSAChrgITF, "StudentAccountRuleSet" );
                     if ( lTempInteger_18 == 0 )
                     { 
                        //:szNumber = mSAChrgITF.StudentAccountRuleSet.ID 
                        {MutableInt mi_lTempInteger_19 = new MutableInt( lTempInteger_19 );
                        StringBuilder sb_szNumber;
                        if ( szNumber == null )
                           sb_szNumber = new StringBuilder( 32 );
                        else
                           sb_szNumber = new StringBuilder( szNumber );
                                                 GetVariableFromAttribute( sb_szNumber, mi_lTempInteger_19, 'S', 33, mSAChrgITF, "StudentAccountRuleSet", "ID", "", 0 );
                        lTempInteger_19 = mi_lTempInteger_19.intValue( );
                        szNumber = sb_szNumber.toString( );}
                        //:szRuleName = "SA_RULE" + szNumber
                         {StringBuilder sb_szRuleName;
                        if ( szRuleName == null )
                           sb_szRuleName = new StringBuilder( 32 );
                        else
                           sb_szRuleName = new StringBuilder( szRuleName );
                                                ZeidonStringCopy( sb_szRuleName, 1, 0, "SA_RULE", 1, 0, 33 );
                        szRuleName = sb_szRuleName.toString( );}
                         {StringBuilder sb_szRuleName;
                        if ( szRuleName == null )
                           sb_szRuleName = new StringBuilder( 32 );
                        else
                           sb_szRuleName = new StringBuilder( szRuleName );
                                                ZeidonStringConcat( sb_szRuleName, 1, 0, szNumber, 1, 0, 33 );
                        szRuleName = sb_szRuleName.toString( );}
                        //:GET VIEW mSARuleS NAMED szRuleName
                        RESULT = GetViewByName( mSARuleS, szRuleName, mSAProf, zLEVEL_TASK );
                        //:IF RESULT <= 0
                        if ( RESULT <= 0 )
                        { 
                           //:ACTIVATE mSARuleS WHERE mSARuleS.StudentAccountRuleSet.ID = mSAChrgITF.StudentAccountRuleSet.ID 
                           {MutableInt mi_lTempInteger_20 = new MutableInt( lTempInteger_20 );
                                                       GetIntegerFromAttribute( mi_lTempInteger_20, mSAChrgITF, "StudentAccountRuleSet", "ID" );
                           lTempInteger_20 = mi_lTempInteger_20.intValue( );}
                           omSAProf_fnLocalBuildQual_13( mSAProf, vTempViewVar_2, lTempInteger_20 );
                           RESULT = ActivateObjectInstance( mSARuleS, "mSARuleS", mSAProf, vTempViewVar_2, zSINGLE );
                           DropView( vTempViewVar_2 );
                           //:NAME VIEW mSARuleS szRuleName
                           SetNameForView( mSARuleS, szRuleName, null, zLEVEL_TASK );
                        } 

                        //:END 

                        //:nRC = QualifyRuleSet( mSARuleS, mSAProf2, mPerson )
                        {
                         mSARuleS_Object m_mSARuleS_Object = new mSARuleS_Object( mSARuleS );
                         nRC = m_mSARuleS_Object.omSARuleS_QualifyRuleSet( mSARuleS, mSAProf2, mPerson );
                         // m_mSARuleS_Object = null;  // permit gc  (unnecessary)
                        }
                        //:IF nRC <= 0  
                        if ( nRC <= 0 )
                        { 
                           //:// Rule doesn't apply, so process Refund.
                           //:// If today's date is before the Add/Drop Date Deadline, we'll process the refund.
                           //:// Otherwise, we'll give the operator a message.
                           //:szCurrentDate = wXferO.Root.dCurrentDate 
                           {MutableInt mi_lTempInteger_21 = new MutableInt( lTempInteger_21 );
                           StringBuilder sb_szCurrentDate;
                           if ( szCurrentDate == null )
                              sb_szCurrentDate = new StringBuilder( 32 );
                           else
                              sb_szCurrentDate = new StringBuilder( szCurrentDate );
                                                       GetVariableFromAttribute( sb_szCurrentDate, mi_lTempInteger_21, 'S', 9, wXferO, "Root", "dCurrentDate", "", 0 );
                           lTempInteger_21 = mi_lTempInteger_21.intValue( );
                           szCurrentDate = sb_szCurrentDate.toString( );}
                           //:IF szCurrentDate <= mSAProf.PeriodCollegeTerm.AddDropDeadlineDate
                           if ( CompareAttributeToString( mSAProf, "PeriodCollegeTerm", "AddDropDeadlineDate", szCurrentDate ) >= 0 )
                           { 
                              //:dAmount = mSAProf2.PeriodTransApplied.Amount 
                              {MutableDouble md_dAmount = new MutableDouble( dAmount );
                                                             GetDecimalFromAttribute( md_dAmount, mSAProf2, "PeriodTransApplied", "Amount" );
                              dAmount = md_dAmount.doubleValue( );}
                              //:dAmount = dAmount * -1
                              dAmount = dAmount * -1;
                              //:GenerateCharge( mSAProf, mSAProf2.PeriodCollegeTerm.ID, nTransCodeID, dAmount, "N", "" )
                              {MutableInt mi_lTempInteger_22 = new MutableInt( lTempInteger_22 );
                                                             GetIntegerFromAttribute( mi_lTempInteger_22, mSAProf2, "PeriodCollegeTerm", "ID" );
                              lTempInteger_22 = mi_lTempInteger_22.intValue( );}
                              omSAProf_GenerateCharge( mSAProf, lTempInteger_22, nTransCodeID, dAmount, "N", "" );
                              //:// Override Description generated in GenerateCharge to use original description plus the word, "(Refund)"
                              //:mSAProf.StudentAccountTransApplied.Description = mSAProf2.PeriodTransApplied.Description + " (Refund)"
                              {StringBuilder sb_szTempString_4;
                              if ( szTempString_4 == null )
                                 sb_szTempString_4 = new StringBuilder( 32 );
                              else
                                 sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                                             GetStringFromAttribute( sb_szTempString_4, mSAProf2, "PeriodTransApplied", "Description" );
                              szTempString_4 = sb_szTempString_4.toString( );}
                               {StringBuilder sb_szTempString_4;
                              if ( szTempString_4 == null )
                                 sb_szTempString_4 = new StringBuilder( 32 );
                              else
                                 sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                                            ZeidonStringConcat( sb_szTempString_4, 1, 0, " (Refund)", 1, 0, 255 );
                              szTempString_4 = sb_szTempString_4.toString( );}
                              SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szTempString_4 );
                              //:AcceptSubobject( mSAProf, "StudentAccountTransApplied" )
                              AcceptSubobject( mSAProf, "StudentAccountTransApplied" );
                              //:INCLUDE mSAProf.AdjustedSATransApplied FROM mSAProf2.PeriodTransApplied
                              RESULT = IncludeSubobjectFromSubobject( mSAProf, "AdjustedSATransApplied", mSAProf2, "PeriodTransApplied", zPOS_AFTER );
                              //:ELSE
                           } 
                           else
                           { 
                              //:// We will give the User a message indicating a potential refund.
                              //:szMsg = "Potential Refund Error for " + mSAProf.Person.dFullNameLFM + ":" + NEW_LINE +
                              //:        "Misc. charge, '" + mSAChrgITF.SATransactionCode.Description + "', is no longer valid, but we have passed " +
                              //:        "the deadline for an automatic refund to be generated, so charges may need to be evaluated manually."
                              {MutableInt mi_lTempInteger_23 = new MutableInt( lTempInteger_23 );
                              StringBuilder sb_szTempString_5;
                              if ( szTempString_5 == null )
                                 sb_szTempString_5 = new StringBuilder( 32 );
                              else
                                 sb_szTempString_5 = new StringBuilder( szTempString_5 );
                                                             GetVariableFromAttribute( sb_szTempString_5, mi_lTempInteger_23, 'S', 255, mSAProf, "Person", "dFullNameLFM", "", 0 );
                              lTempInteger_23 = mi_lTempInteger_23.intValue( );
                              szTempString_5 = sb_szTempString_5.toString( );}
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringCopy( sb_szMsg, 1, 0, "Potential Refund Error for ", 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_5, 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringConcat( sb_szMsg, 1, 0, ":", 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringConcat( sb_szMsg, 1, 0, "Misc. charge, '", 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                              {MutableInt mi_lTempInteger_24 = new MutableInt( lTempInteger_24 );
                              StringBuilder sb_szTempString_6;
                              if ( szTempString_6 == null )
                                 sb_szTempString_6 = new StringBuilder( 32 );
                              else
                                 sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                                             GetVariableFromAttribute( sb_szTempString_6, mi_lTempInteger_24, 'S', 255, mSAChrgITF, "SATransactionCode", "Description", "", 0 );
                              lTempInteger_24 = mi_lTempInteger_24.intValue( );
                              szTempString_6 = sb_szTempString_6.toString( );}
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_6, 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringConcat( sb_szMsg, 1, 0, "', is no longer valid, but we have passed ", 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringConcat( sb_szMsg, 1, 0, "the deadline for an automatic refund to be generated, so charges may need to be evaluated manually.", 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                              //:MessageSend( mSAProf, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
                              MessageSend( mSAProf, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                           } 

                           //:END
                        } 

                        //:END 
                     } 

                     //:END 
                  } 

                  //:END

                  //:nDone = 1
                  nDone = 1;
               } 

               //:END 
            } 

            //:END
            //:// 
            //:// MEAL Plan
            //:// 
            //:IF nDone = 0 
            if ( nDone == 0 )
            { 
               //:IF mSAProf2.PeriodSourceTermOfMealPlan EXISTS
               lTempInteger_25 = CheckExistenceOfEntity( mSAProf2, "PeriodSourceTermOfMealPlan" );
               if ( lTempInteger_25 == 0 )
               { 
                  //:// The Transaction is for a Meal Plan.
                  //:// We will do a full reversal of the entry if the Meal Plan entry no longer exists or if it has been terminated before
                  //:// the Add/DropDead date.
                  //:szMealPlanFlag = ""
                   {StringBuilder sb_szMealPlanFlag;
                  if ( szMealPlanFlag == null )
                     sb_szMealPlanFlag = new StringBuilder( 32 );
                  else
                     sb_szMealPlanFlag = new StringBuilder( szMealPlanFlag );
                                    ZeidonStringCopy( sb_szMealPlanFlag, 1, 0, "", 1, 0, 2 );
                  szMealPlanFlag = sb_szMealPlanFlag.toString( );}
                  //:SET CURSOR FIRST mSAProf.TermOfMealPlan WHERE mSAProf.TermOfMealPlan.ID = mSAProf2.PeriodSourceTermOfMealPlan.ID
                  {MutableInt mi_lTempInteger_26 = new MutableInt( lTempInteger_26 );
                                     GetIntegerFromAttribute( mi_lTempInteger_26, mSAProf2, "PeriodSourceTermOfMealPlan", "ID" );
                  lTempInteger_26 = mi_lTempInteger_26.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mSAProf, "TermOfMealPlan", "ID", lTempInteger_26, "" );
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:IF mSAProf.TermOfMealPlan.WithdrawalDate = "" OR 
                     //:   mSAProf.TermOfMealPlan.WithdrawalDate > mSAProf.PeriodCollegeTerm.AddDropDeadlineDate
                     if ( CompareAttributeToString( mSAProf, "TermOfMealPlan", "WithdrawalDate", "" ) == 0 || CompareAttributeToAttribute( mSAProf, "TermOfMealPlan", "WithdrawalDate", mSAProf, "PeriodCollegeTerm", "AddDropDeadlineDate" ) > 0 )
                     { 

                        //:// The Student is either still on the meal plan or has dropped it after the date.
                        //:szMealPlanFlag = "Y"
                         {StringBuilder sb_szMealPlanFlag;
                        if ( szMealPlanFlag == null )
                           sb_szMealPlanFlag = new StringBuilder( 32 );
                        else
                           sb_szMealPlanFlag = new StringBuilder( szMealPlanFlag );
                                                ZeidonStringCopy( sb_szMealPlanFlag, 1, 0, "Y", 1, 0, 2 );
                        szMealPlanFlag = sb_szMealPlanFlag.toString( );}
                     } 

                     //:END
                  } 

                  //:END
                  //:IF szMealPlanFlag = ""
                  if ( ZeidonStringCompare( szMealPlanFlag, 1, 0, "", 1, 0, 2 ) == 0 )
                  { 
                     //:// The Meal Plan no longer exists or has terminated, so reverse it.
                     //:dAmount = mSAProf2.PeriodTransApplied.Amount
                     {MutableDouble md_dAmount = new MutableDouble( dAmount );
                                           GetDecimalFromAttribute( md_dAmount, mSAProf2, "PeriodTransApplied", "Amount" );
                     dAmount = md_dAmount.doubleValue( );}
                     //:dAmount = dAmount * -1
                     dAmount = dAmount * -1;
                     //:GenerateCharge( mSAProf, mSAProf2.PeriodCollegeTerm.ID, nTransCodeID, dAmount, "N", "" )
                     {MutableInt mi_lTempInteger_27 = new MutableInt( lTempInteger_27 );
                                           GetIntegerFromAttribute( mi_lTempInteger_27, mSAProf2, "PeriodCollegeTerm", "ID" );
                     lTempInteger_27 = mi_lTempInteger_27.intValue( );}
                     omSAProf_GenerateCharge( mSAProf, lTempInteger_27, nTransCodeID, dAmount, "N", "" );
                     //:mSAProf.StudentAccountTransApplied.Description = mSAProf2.PeriodTransApplied.Description + " (Refund)"
                     {StringBuilder sb_szTempString_7;
                     if ( szTempString_7 == null )
                        sb_szTempString_7 = new StringBuilder( 32 );
                     else
                        sb_szTempString_7 = new StringBuilder( szTempString_7 );
                                           GetStringFromAttribute( sb_szTempString_7, mSAProf2, "PeriodTransApplied", "Description" );
                     szTempString_7 = sb_szTempString_7.toString( );}
                      {StringBuilder sb_szTempString_7;
                     if ( szTempString_7 == null )
                        sb_szTempString_7 = new StringBuilder( 32 );
                     else
                        sb_szTempString_7 = new StringBuilder( szTempString_7 );
                                          ZeidonStringConcat( sb_szTempString_7, 1, 0, " (Refund)", 1, 0, 255 );
                     szTempString_7 = sb_szTempString_7.toString( );}
                     SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szTempString_7 );
                     //:AcceptSubobject( mSAProf, "StudentAccountTransApplied" )
                     AcceptSubobject( mSAProf, "StudentAccountTransApplied" );
                     //:INCLUDE mSAProf.AdjustedSATransApplied FROM mSAProf2.PeriodTransApplied
                     RESULT = IncludeSubobjectFromSubobject( mSAProf, "AdjustedSATransApplied", mSAProf2, "PeriodTransApplied", zPOS_AFTER );
                  } 

                  //:END
                  //:nDone = 1
                  nDone = 1;
               } 

               //:END
            } 

            //:END

            //://
            //:// ROOM AND BOARD
            //://
            //:IF nDone = 0 
            if ( nDone == 0 )
            { 
               //:IF mSAProf2.PeriodSourceTermOfResidence EXISTS
               lTempInteger_28 = CheckExistenceOfEntity( mSAProf2, "PeriodSourceTermOfResidence" );
               if ( lTempInteger_28 == 0 )
               { 
                  //:// The Transaction is for a Term of Residence.
                  //:// We will do a full reversal of the entry if the Term of Residency entry no longer exists or if it has been terminated before
                  //:// the Add/DropDead date.
                  //:// We will give the User an error message, warning her to process the entry manualy if the entry has been terminated or
                  //:// if the End Date for the Residency Entry is different than the End Date for the Term.
                  //:szResidencyFlag = ""
                   {StringBuilder sb_szResidencyFlag;
                  if ( szResidencyFlag == null )
                     sb_szResidencyFlag = new StringBuilder( 32 );
                  else
                     sb_szResidencyFlag = new StringBuilder( szResidencyFlag );
                                    ZeidonStringCopy( sb_szResidencyFlag, 1, 0, "", 1, 0, 2 );
                  szResidencyFlag = sb_szResidencyFlag.toString( );}
                  //:SET CURSOR FIRST mSAProf2.TermOfResidence WHERE mSAProf2.TermOfResidence.ID = mSAProf2.PeriodSourceTermOfResidence.ID 
                  {MutableInt mi_lTempInteger_29 = new MutableInt( lTempInteger_29 );
                                     GetIntegerFromAttribute( mi_lTempInteger_29, mSAProf2, "PeriodSourceTermOfResidence", "ID" );
                  lTempInteger_29 = mi_lTempInteger_29.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mSAProf2, "TermOfResidence", "ID", lTempInteger_29, "" );
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:IF mSAProf2.TermOfResidence.WithdrawalDate = "" OR
                     //:   mSAProf2.TermOfResidence.WithdrawalDate > mSAProf.PeriodCollegeTerm.AddDropDeadlineDate
                     if ( CompareAttributeToString( mSAProf2, "TermOfResidence", "WithdrawalDate", "" ) == 0 || CompareAttributeToAttribute( mSAProf2, "TermOfResidence", "WithdrawalDate", mSAProf, "PeriodCollegeTerm", "AddDropDeadlineDate" ) > 0 )
                     { 

                        //:szResidencyFlag = "Y"
                         {StringBuilder sb_szResidencyFlag;
                        if ( szResidencyFlag == null )
                           sb_szResidencyFlag = new StringBuilder( 32 );
                        else
                           sb_szResidencyFlag = new StringBuilder( szResidencyFlag );
                                                ZeidonStringCopy( sb_szResidencyFlag, 1, 0, "Y", 1, 0, 2 );
                        szResidencyFlag = sb_szResidencyFlag.toString( );}
                     } 

                     //:END
                  } 

                  //:END
                  //:IF szResidencyFlag = ""
                  if ( ZeidonStringCompare( szResidencyFlag, 1, 0, "", 1, 0, 2 ) == 0 )
                  { 
                     //:   // There is no residency entry for the current Term so reverse the entry.
                     //:   dAmount = mSAProf2.PeriodTransApplied.Amount
                     {MutableDouble md_dAmount = new MutableDouble( dAmount );
                                           GetDecimalFromAttribute( md_dAmount, mSAProf2, "PeriodTransApplied", "Amount" );
                     dAmount = md_dAmount.doubleValue( );}
                     //:   dAmount = dAmount * -1
                     dAmount = dAmount * -1;
                     //:   GenerateCharge( mSAProf, mSAProf.PeriodCollegeTerm.ID, 
                     //:                   mSAProf2.PeriodSATransactionCode.ID, 
                     //:                   dAmount, "N", "" )
                     {MutableInt mi_lTempInteger_30 = new MutableInt( lTempInteger_30 );
                                           GetIntegerFromAttribute( mi_lTempInteger_30, mSAProf, "PeriodCollegeTerm", "ID" );
                     lTempInteger_30 = mi_lTempInteger_30.intValue( );}
                     {MutableInt mi_lTempInteger_31 = new MutableInt( lTempInteger_31 );
                                           GetIntegerFromAttribute( mi_lTempInteger_31, mSAProf2, "PeriodSATransactionCode", "ID" );
                     lTempInteger_31 = mi_lTempInteger_31.intValue( );}
                     omSAProf_GenerateCharge( mSAProf, lTempInteger_30, lTempInteger_31, dAmount, "N", "" );
                     //:   mSAProf.StudentAccountTransApplied.Description = mSAProf2.PeriodTransApplied.Description + " (Refund)"
                     {StringBuilder sb_szTempString_8;
                     if ( szTempString_8 == null )
                        sb_szTempString_8 = new StringBuilder( 32 );
                     else
                        sb_szTempString_8 = new StringBuilder( szTempString_8 );
                                           GetStringFromAttribute( sb_szTempString_8, mSAProf2, "PeriodTransApplied", "Description" );
                     szTempString_8 = sb_szTempString_8.toString( );}
                      {StringBuilder sb_szTempString_8;
                     if ( szTempString_8 == null )
                        sb_szTempString_8 = new StringBuilder( 32 );
                     else
                        sb_szTempString_8 = new StringBuilder( szTempString_8 );
                                          ZeidonStringConcat( sb_szTempString_8, 1, 0, " (Refund)", 1, 0, 255 );
                     szTempString_8 = sb_szTempString_8.toString( );}
                     SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szTempString_8 );
                     //:   AcceptSubobject( mSAProf, "StudentAccountTransApplied" )
                     AcceptSubobject( mSAProf, "StudentAccountTransApplied" );
                     //:   INCLUDE mSAProf.AdjustedSATransApplied FROM mSAProf2.PeriodTransApplied
                     RESULT = IncludeSubobjectFromSubobject( mSAProf, "AdjustedSATransApplied", mSAProf2, "PeriodTransApplied", zPOS_AFTER );
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF mSAProf2.TermOfResidence.EndDate < mSAProf2.PeriodCollegeTerm.ResidencyEndDate OR 
                     //:   mSAProf2.TermOfResidence.WithdrawalDate != ""
                     if ( CompareAttributeToAttribute( mSAProf2, "TermOfResidence", "EndDate", mSAProf2, "PeriodCollegeTerm", "ResidencyEndDate" ) < 0 || CompareAttributeToString( mSAProf2, "TermOfResidence", "WithdrawalDate", "" ) != 0 )
                     { 

                        //:// We will give the User a message indicating a potential refund.
                        //:szMsg = "Potential Refund Error for " + mSAProf.Person.dFullNameLFM + ":" + NEW_LINE +
                        //:        "Term of residency charge, '" + mSAChrgITF.SATransactionCode.Description + "', may need to be adjusted manually, " +
                        //:        "as the term of the residency has been modified."
                        {MutableInt mi_lTempInteger_32 = new MutableInt( lTempInteger_32 );
                        StringBuilder sb_szTempString_9;
                        if ( szTempString_9 == null )
                           sb_szTempString_9 = new StringBuilder( 32 );
                        else
                           sb_szTempString_9 = new StringBuilder( szTempString_9 );
                                                 GetVariableFromAttribute( sb_szTempString_9, mi_lTempInteger_32, 'S', 255, mSAProf, "Person", "dFullNameLFM", "", 0 );
                        lTempInteger_32 = mi_lTempInteger_32.intValue( );
                        szTempString_9 = sb_szTempString_9.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringCopy( sb_szMsg, 1, 0, "Potential Refund Error for ", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_9, 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringConcat( sb_szMsg, 1, 0, ":", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringConcat( sb_szMsg, 1, 0, "Term of residency charge, '", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                        {MutableInt mi_lTempInteger_33 = new MutableInt( lTempInteger_33 );
                        StringBuilder sb_szTempString_10;
                        if ( szTempString_10 == null )
                           sb_szTempString_10 = new StringBuilder( 32 );
                        else
                           sb_szTempString_10 = new StringBuilder( szTempString_10 );
                                                 GetVariableFromAttribute( sb_szTempString_10, mi_lTempInteger_33, 'S', 255, mSAChrgITF, "SATransactionCode", "Description", "", 0 );
                        lTempInteger_33 = mi_lTempInteger_33.intValue( );
                        szTempString_10 = sb_szTempString_10.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_10, 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringConcat( sb_szMsg, 1, 0, "', may need to be adjusted manually, ", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringConcat( sb_szMsg, 1, 0, "as the term of the residency has been modified.", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                        //:MessageSend( mSAProf, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
                        MessageSend( mSAProf, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            //:END

            //://
            //:// Class Course/Lab Fees
            //://
            //:IF nDone = 0 
            if ( nDone == 0 )
            { 
               //:IF mSAProf2.PeriodTransClass EXISTS
               lTempInteger_34 = CheckExistenceOfEntity( mSAProf2, "PeriodTransClass" );
               if ( lTempInteger_34 == 0 )
               { 

                  //:// We need to position on Transaction Code for later processing below.
                  //:SET CURSOR FIRST mSAChrgITC.SATransactionCode WHERE mSAChrgITC.SATransactionCode.ID = mSAProf2.PeriodSATransactionCode.ID
                  {MutableInt mi_lTempInteger_35 = new MutableInt( lTempInteger_35 );
                                     GetIntegerFromAttribute( mi_lTempInteger_35, mSAProf2, "PeriodSATransactionCode", "ID" );
                  lTempInteger_35 = mi_lTempInteger_35.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mSAChrgITC, "SATransactionCode", "ID", lTempInteger_35, "" );

                  //:// See if the SA_Transaction we are on has already been charged. To do this, we look to see if the Class generating 
                  //:// this Transaction exists and has not been dropped.
                  //:szLabFeeRefund = ""
                   {StringBuilder sb_szLabFeeRefund;
                  if ( szLabFeeRefund == null )
                     sb_szLabFeeRefund = new StringBuilder( 32 );
                  else
                     sb_szLabFeeRefund = new StringBuilder( szLabFeeRefund );
                                    ZeidonStringCopy( sb_szLabFeeRefund, 1, 0, "", 1, 0, 2 );
                  szLabFeeRefund = sb_szLabFeeRefund.toString( );}
                  //:SET CURSOR FIRST mSAProf2.TermClass WITHIN mSAProf2.PeriodCollegeTerm 
                  //:           WHERE mSAProf2.TermClass.ID = mSAProf2.PeriodTransClass.ID 
                  {MutableInt mi_lTempInteger_36 = new MutableInt( lTempInteger_36 );
                                     GetIntegerFromAttribute( mi_lTempInteger_36, mSAProf2, "PeriodTransClass", "ID" );
                  lTempInteger_36 = mi_lTempInteger_36.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mSAProf2, "TermClass", "ID", lTempInteger_36, "PeriodCollegeTerm" );
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:// Not there, so the Class must have been deleted before the start of the Term, so refund amount.
                     //:szLabFeeRefund = "Y"
                      {StringBuilder sb_szLabFeeRefund;
                     if ( szLabFeeRefund == null )
                        sb_szLabFeeRefund = new StringBuilder( 32 );
                     else
                        sb_szLabFeeRefund = new StringBuilder( szLabFeeRefund );
                                          ZeidonStringCopy( sb_szLabFeeRefund, 1, 0, "Y", 1, 0, 2 );
                     szLabFeeRefund = sb_szLabFeeRefund.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF mSAProf2.TermEnrolled.DroppedDate != ""    // The Class was dropped.
                     if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "DroppedDate", "" ) != 0 )
                     { 
                        //:// If the DropDate is before the Add/Drop Deadline, give a refund.
                        //:IF mSAProf2.TermEnrolled.DroppedDate <= mSAProf2.PeriodCollegeTerm.AddDropDeadlineDate
                        if ( CompareAttributeToAttribute( mSAProf2, "TermEnrolled", "DroppedDate", mSAProf2, "PeriodCollegeTerm", "AddDropDeadlineDate" ) <= 0 )
                        { 
                           //:szLabFeeRefund = "Y"
                            {StringBuilder sb_szLabFeeRefund;
                           if ( szLabFeeRefund == null )
                              sb_szLabFeeRefund = new StringBuilder( 32 );
                           else
                              sb_szLabFeeRefund = new StringBuilder( szLabFeeRefund );
                                                      ZeidonStringCopy( sb_szLabFeeRefund, 1, 0, "Y", 1, 0, 2 );
                           szLabFeeRefund = sb_szLabFeeRefund.toString( );}
                           //:ELSE
                        } 
                        else
                        { 
                           //:// The Class was dropped after the deadline, so give the User an error message.
                           //:szLabFeeRefund = "E"
                            {StringBuilder sb_szLabFeeRefund;
                           if ( szLabFeeRefund == null )
                              sb_szLabFeeRefund = new StringBuilder( 32 );
                           else
                              sb_szLabFeeRefund = new StringBuilder( szLabFeeRefund );
                                                      ZeidonStringCopy( sb_szLabFeeRefund, 1, 0, "E", 1, 0, 2 );
                           szLabFeeRefund = sb_szLabFeeRefund.toString( );}
                        } 

                        //:END
                     } 

                     //:END
                  } 

                  //:END
                  //:IF szLabFeeRefund = "Y" OR szLabFeeRefund = "E"
                  if ( ZeidonStringCompare( szLabFeeRefund, 1, 0, "Y", 1, 0, 2 ) == 0 || ZeidonStringCompare( szLabFeeRefund, 1, 0, "E", 1, 0, 2 ) == 0 )
                  { 
                     //:// The request is to refund the lab fee. If we haven't passed the  add/drop deadline, we'll create refund entry. Otherwise,
                     //:// we'll give the User a message warning her to process the entry manually.
                     //:IF szLabFeeRefund = "Y"
                     if ( ZeidonStringCompare( szLabFeeRefund, 1, 0, "Y", 1, 0, 2 ) == 0 )
                     { 
                        //:dAmount = mSAProf2.PeriodTransApplied.Amount 
                        {MutableDouble md_dAmount = new MutableDouble( dAmount );
                                                 GetDecimalFromAttribute( md_dAmount, mSAProf2, "PeriodTransApplied", "Amount" );
                        dAmount = md_dAmount.doubleValue( );}
                        //:dAmount = dAmount * -1
                        dAmount = dAmount * -1;
                        //:GenerateCharge( mSAProf, mSAProf.PeriodCollegeTerm.ID, 
                        //:                nTransCodeID, dAmount, "N", "" )
                        {MutableInt mi_lTempInteger_37 = new MutableInt( lTempInteger_37 );
                                                 GetIntegerFromAttribute( mi_lTempInteger_37, mSAProf, "PeriodCollegeTerm", "ID" );
                        lTempInteger_37 = mi_lTempInteger_37.intValue( );}
                        omSAProf_GenerateCharge( mSAProf, lTempInteger_37, nTransCodeID, dAmount, "N", "" );
                        //:// Override Description generated in GenerateCharge to use original description plus the word, "(Refund)"
                        //:mSAProf.StudentAccountTransApplied.Description = mSAProf2.PeriodTransApplied.Description + " (Refund)"
                        {StringBuilder sb_szTempString_11;
                        if ( szTempString_11 == null )
                           sb_szTempString_11 = new StringBuilder( 32 );
                        else
                           sb_szTempString_11 = new StringBuilder( szTempString_11 );
                                                 GetStringFromAttribute( sb_szTempString_11, mSAProf2, "PeriodTransApplied", "Description" );
                        szTempString_11 = sb_szTempString_11.toString( );}
                         {StringBuilder sb_szTempString_11;
                        if ( szTempString_11 == null )
                           sb_szTempString_11 = new StringBuilder( 32 );
                        else
                           sb_szTempString_11 = new StringBuilder( szTempString_11 );
                                                ZeidonStringConcat( sb_szTempString_11, 1, 0, " (Refund)", 1, 0, 255 );
                        szTempString_11 = sb_szTempString_11.toString( );}
                        SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szTempString_11 );
                        //:AcceptSubobject( mSAProf, "StudentAccountTransApplied" )
                        AcceptSubobject( mSAProf, "StudentAccountTransApplied" );
                        //:INCLUDE mSAProf.AdjustedSATransApplied FROM mSAProf2.PeriodTransApplied
                        RESULT = IncludeSubobjectFromSubobject( mSAProf, "AdjustedSATransApplied", mSAProf2, "PeriodTransApplied", zPOS_AFTER );
                        //:ELSE
                     } 
                     else
                     { 
                        //:szMsg = "Potential Refund Error for " + mSAProf.Person.dFullNameLFM + ":" + NEW_LINE +
                        //:        "Course/Lab Fee, '" + mSAChrgITC.SATransactionCode.Description + "', may need to be adjusted manually, " +
                        //:        "as the associated class was dropped after the Add/Drop Deadline Date."
                        {MutableInt mi_lTempInteger_38 = new MutableInt( lTempInteger_38 );
                        StringBuilder sb_szTempString_12;
                        if ( szTempString_12 == null )
                           sb_szTempString_12 = new StringBuilder( 32 );
                        else
                           sb_szTempString_12 = new StringBuilder( szTempString_12 );
                                                 GetVariableFromAttribute( sb_szTempString_12, mi_lTempInteger_38, 'S', 255, mSAProf, "Person", "dFullNameLFM", "", 0 );
                        lTempInteger_38 = mi_lTempInteger_38.intValue( );
                        szTempString_12 = sb_szTempString_12.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringCopy( sb_szMsg, 1, 0, "Potential Refund Error for ", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_12, 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringConcat( sb_szMsg, 1, 0, ":", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringConcat( sb_szMsg, 1, 0, "Course/Lab Fee, '", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                        {MutableInt mi_lTempInteger_39 = new MutableInt( lTempInteger_39 );
                        StringBuilder sb_szTempString_13;
                        if ( szTempString_13 == null )
                           sb_szTempString_13 = new StringBuilder( 32 );
                        else
                           sb_szTempString_13 = new StringBuilder( szTempString_13 );
                                                 GetVariableFromAttribute( sb_szTempString_13, mi_lTempInteger_39, 'S', 255, mSAChrgITC, "SATransactionCode", "Description", "", 0 );
                        lTempInteger_39 = mi_lTempInteger_39.intValue( );
                        szTempString_13 = sb_szTempString_13.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_13, 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringConcat( sb_szMsg, 1, 0, "', may need to be adjusted manually, ", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringConcat( sb_szMsg, 1, 0, "as the associated class was dropped after the Add/Drop Deadline Date.", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                        //:MessageSend( mSAProf, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
                        MessageSend( mSAProf, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                     } 

                     //:END
                  } 

                  //:END

                  //:nDone = 1
                  nDone = 1;
               } 

               //:END
            } 

            //:   
            //:END

            //://
            //:// Course Once/Term Charges
            //://
            //:IF nDone = 0 
            if ( nDone == 0 )
            { 
               //:IF mSAProf2.PeriodSATransactionCode.FrequencyType = "Term"
               if ( CompareAttributeToString( mSAProf2, "PeriodSATransactionCode", "FrequencyType", "Term" ) == 0 )
               { 
                  //:// We must loop through each TermEnrolled entry to see if a Class exists that triggers this Transaction.
                  //:// We won't bother considering whether they dropped the Class after Add/Drop date in this logic, as it seems like overkill.
                  //:szLabFeeRefund = "Y"   // Initialize the flag as if a refund should be given.
                   {StringBuilder sb_szLabFeeRefund;
                  if ( szLabFeeRefund == null )
                     sb_szLabFeeRefund = new StringBuilder( 32 );
                  else
                     sb_szLabFeeRefund = new StringBuilder( szLabFeeRefund );
                                    ZeidonStringCopy( sb_szLabFeeRefund, 1, 0, "Y", 1, 0, 2 );
                  szLabFeeRefund = sb_szLabFeeRefund.toString( );}
                  //:SET CURSOR FIRST mSAChrgITC.SATransactionCode WHERE mSAChrgITC.SATransactionCode.ID = mSAProf2.PeriodSATransactionCode.ID
                  {MutableInt mi_lTempInteger_40 = new MutableInt( lTempInteger_40 );
                                     GetIntegerFromAttribute( mi_lTempInteger_40, mSAProf2, "PeriodSATransactionCode", "ID" );
                  lTempInteger_40 = mi_lTempInteger_40.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mSAChrgITC, "SATransactionCode", "ID", lTempInteger_40, "" );
                  //:FOR EACH mSAProf2.TermEnrolled 
                  RESULT = SetCursorFirstEntity( mSAProf2, "TermEnrolled", "" );
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //:SET CURSOR FIRST mSAChrgITC.Course WHERE mSAChrgITC.Course.ID = mSAProf2.TermCourse.ID 
                     {MutableInt mi_lTempInteger_41 = new MutableInt( lTempInteger_41 );
                                           GetIntegerFromAttribute( mi_lTempInteger_41, mSAProf2, "TermCourse", "ID" );
                     lTempInteger_41 = mi_lTempInteger_41.intValue( );}
                     RESULT = SetCursorFirstEntityByInteger( mSAChrgITC, "Course", "ID", lTempInteger_41, "" );
                     //:IF RESULT >= zCURSOR_SET
                     if ( RESULT >= zCURSOR_SET )
                     { 
                        //:szLabFeeRefund = "N"   // We found a Course that triggers fee, so indicate no refund.
                         {StringBuilder sb_szLabFeeRefund;
                        if ( szLabFeeRefund == null )
                           sb_szLabFeeRefund = new StringBuilder( 32 );
                        else
                           sb_szLabFeeRefund = new StringBuilder( szLabFeeRefund );
                                                ZeidonStringCopy( sb_szLabFeeRefund, 1, 0, "N", 1, 0, 2 );
                        szLabFeeRefund = sb_szLabFeeRefund.toString( );}
                     } 

                     RESULT = SetCursorNextEntity( mSAProf2, "TermEnrolled", "" );
                     //:END 
                  } 

                  //:END
                  //:IF szLabFeeRefund = "Y"
                  if ( ZeidonStringCompare( szLabFeeRefund, 1, 0, "Y", 1, 0, 2 ) == 0 )
                  { 
                     //:dAmount = mSAProf2.PeriodTransApplied.Amount 
                     {MutableDouble md_dAmount = new MutableDouble( dAmount );
                                           GetDecimalFromAttribute( md_dAmount, mSAProf2, "PeriodTransApplied", "Amount" );
                     dAmount = md_dAmount.doubleValue( );}
                     //:dAmount = dAmount * -1
                     dAmount = dAmount * -1;
                     //:GenerateCharge( mSAProf, mSAProf.PeriodCollegeTerm.ID, 
                     //:                nTransCodeID, dAmount, "N", "" )
                     {MutableInt mi_lTempInteger_42 = new MutableInt( lTempInteger_42 );
                                           GetIntegerFromAttribute( mi_lTempInteger_42, mSAProf, "PeriodCollegeTerm", "ID" );
                     lTempInteger_42 = mi_lTempInteger_42.intValue( );}
                     omSAProf_GenerateCharge( mSAProf, lTempInteger_42, nTransCodeID, dAmount, "N", "" );
                     //:// Override Description generated in GenerateCharge to use original description plus the word, "(Refund)"
                     //:mSAProf.StudentAccountTransApplied.Description = mSAProf2.PeriodTransApplied.Description + " (Refund)"
                     {StringBuilder sb_szTempString_14;
                     if ( szTempString_14 == null )
                        sb_szTempString_14 = new StringBuilder( 32 );
                     else
                        sb_szTempString_14 = new StringBuilder( szTempString_14 );
                                           GetStringFromAttribute( sb_szTempString_14, mSAProf2, "PeriodTransApplied", "Description" );
                     szTempString_14 = sb_szTempString_14.toString( );}
                      {StringBuilder sb_szTempString_14;
                     if ( szTempString_14 == null )
                        sb_szTempString_14 = new StringBuilder( 32 );
                     else
                        sb_szTempString_14 = new StringBuilder( szTempString_14 );
                                          ZeidonStringConcat( sb_szTempString_14, 1, 0, " (Refund)", 1, 0, 255 );
                     szTempString_14 = sb_szTempString_14.toString( );}
                     SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szTempString_14 );
                     //:AcceptSubobject( mSAProf, "StudentAccountTransApplied" )
                     AcceptSubobject( mSAProf, "StudentAccountTransApplied" );
                     //:INCLUDE mSAProf.AdjustedSATransApplied FROM mSAProf2.PeriodTransApplied
                     RESULT = IncludeSubobjectFromSubobject( mSAProf, "AdjustedSATransApplied", mSAProf2, "PeriodTransApplied", zPOS_AFTER );
                  } 

                  //:END
                  //:nDone = 1
                  nDone = 1;
               } 

               //:END
            } 

            //:END
         } 


         //:END
      } 

      RESULT = SetCursorNextEntity( mSAProf2, "PeriodTransApplied", "" );
      //:END
   } 

   //:END
   //:DropView( mSAProf2 )
   DropView( mSAProf2 );
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
         RESULT = SetCursorFirstEntity( mSAProf2, "StudentAccountTransApplied", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf2, "StudentAccountTransApplied", "Amount", 0 ) >= 0 || CompareAttributeToString( mSAProf2, "StudentAccountTransApplied", "Instrument", "" ) == 0 ) )
            { 
               RESULT = SetCursorNextEntity( mSAProf2, "StudentAccountTransApplied", "" );
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
         RESULT = SetCursorFirstEntity( mSAProf2, "StudentAccountTransApplied", "" );
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
         RESULT = SetCursorFirstEntity( mSAProf2, "StudentAccountTransApplied", "" );
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

            RESULT = SetCursorNextEntity( mSAProf2, "StudentAccountTransApplied", "" );
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
   omSAProf_fnLocalBuildQual_14( mSAProf, vTempViewVar_0, nCollegeYear );
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
      RESULT = SetCursorFirstEntity( mYearLST, "CollegeTerm", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:INCLUDE mSAProf.PeriodCollegeTerm FROM mYearLST.CollegeTerm 
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "PeriodCollegeTerm", mYearLST, "CollegeTerm", zPOS_AFTER );
         RESULT = SetCursorNextEntity( mYearLST, "CollegeTerm", "" );
      } 

      //:END
      //:ELSE 
   } 
   else
   { 
      //:SET CURSOR FIRST mSAProf.BillingPeriod 
      //:   WHERE mSAProf.PeriodCollegeTerm.ID = mYearLST.CollegeTerm.ID
      RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSAProf, "PeriodCollegeTerm", "ID", mYearLST, "CollegeTerm", "ID" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( mSAProf, "BillingPeriod", "" );
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
      RESULT = SetCursorFirstEntity( mYearLST, "CollegeTerm", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSAProf.BillingPeriod 
         RESULT = CreateEntity( mSAProf, "BillingPeriod", zPOS_AFTER );
         //:mSAProf.BillingPeriod.PeriodDesignator = mYearLST.CollegeTerm.YearSemester 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "PeriodDesignator", mYearLST, "CollegeTerm", "YearSemester" );
         //:IF mYearLST.CollegeTerm.BillingPeriodStartDate = "" OR mYearLST.CollegeTerm.BillingPeriodEndDate = ""
         if ( CompareAttributeToString( mYearLST, "CollegeTerm", "BillingPeriodStartDate", "" ) == 0 || CompareAttributeToString( mYearLST, "CollegeTerm", "BillingPeriodEndDate", "" ) == 0 )
         { 
            //:mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeTerm.CourseStartDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeTerm", "CourseStartDate" );
            //:mSAProf.BillingPeriod.EndDate   = mYearLST.CollegeTerm.CourseEndDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeTerm", "CourseEndDate" );
            //:ELSE
         } 
         else
         { 
            //:mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeTerm.BillingPeriodStartDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeTerm", "BillingPeriodStartDate" );
            //:mSAProf.BillingPeriod.EndDate   = mYearLST.CollegeTerm.BillingPeriodEndDate 
            SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeTerm", "BillingPeriodEndDate" );
         } 

         //:END
         //:INCLUDE mSAProf.PeriodCollegeTerm FROM mYearLST.CollegeTerm 
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "PeriodCollegeTerm", mYearLST, "CollegeTerm", zPOS_AFTER );
         RESULT = SetCursorNextEntity( mYearLST, "CollegeTerm", "" );
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
//:                      INTEGER CollegeYearID )
//:   
//:   VIEW mYearLST BASED ON LOD  mYear 
public int 
omSAProf_UpdateBillingPeriods( View     mSAProf,
                               int      CollegeYearID )
{
   zVIEW    mYearLST = new zVIEW( );
   //:VIEW mUser    BASED ON LOD  mUser
   zVIEW    mUser = new zVIEW( );
   //:INTEGER CurrentTermID
   int      CurrentTermID = 0;
   //:INTEGER CollegeTermID
   int      CollegeTermID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;


   //:ACTIVATE mYearLST WHERE mYearLST.CollegeYear.ID = CollegeYearID
   omSAProf_fnLocalBuildQual_15( mSAProf, vTempViewVar_0, CollegeYearID );
   RESULT = ActivateObjectInstance( mYearLST, "mYear", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:// Add any missing Billing Periods.
   //:FOR EACH mYearLST.CollegeTerm 
   RESULT = SetCursorFirstEntity( mYearLST, "CollegeTerm", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm WITHIN mSAProf.StudentAccountProfile 
      //:           WHERE mSAProf.PeriodCollegeTerm.ID = mYearLST.CollegeTerm.ID 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, mYearLST, "CollegeTerm", "ID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( mSAProf, "PeriodCollegeTerm", "ID", lTempInteger_0, "StudentAccountProfile" );
      //:IF RESULT < zCURSOR_SET 
      if ( RESULT < zCURSOR_SET )
      { 
         //:TraceLineS("*** UpdateBillingPeriods CREATE BillingPeriod ", mYearLST.CollegeTerm.YearSemester) 
         {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetStringFromAttribute( sb_szTempString_0, mYearLST, "CollegeTerm", "YearSemester" );
         szTempString_0 = sb_szTempString_0.toString( );}
         TraceLineS( "*** UpdateBillingPeriods CREATE BillingPeriod ", szTempString_0 );
         //:CREATE ENTITY mSAProf.BillingPeriod 
         RESULT = CreateEntity( mSAProf, "BillingPeriod", zPOS_AFTER );
         //:mSAProf.BillingPeriod.PeriodDesignator = mYearLST.CollegeTerm.YearSemester 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "PeriodDesignator", mYearLST, "CollegeTerm", "YearSemester" );
         //:INCLUDE mSAProf.PeriodCollegeTerm FROM mYearLST.CollegeTerm 
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "PeriodCollegeTerm", mYearLST, "CollegeTerm", zPOS_AFTER );
         //:GET VIEW mUser NAMED "mUser"
         RESULT = GetViewByName( mUser, "mUser", mSAProf, zLEVEL_TASK );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:GetViewByName( mUser, "mUser", mSAProf, zLEVEL_APPLICATION )
            GetViewByName( mUser, "mUser", mSAProf, zLEVEL_APPLICATION );
         } 

         //:END
         //:SetAttributeFromCurrentDateTime( mSAProf, "BillingPeriod", "ModifiedDateTime" )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
          m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mSAProf, "BillingPeriod", "ModifiedDateTime" );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:mSAProf.BillingPeriod.LastModifiedBy = mUser.User.UserName + "UBP"
         {StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetStringFromAttribute( sb_szTempString_1, mUser, "User", "UserName" );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                  ZeidonStringConcat( sb_szTempString_1, 1, 0, "UBP", 1, 0, 51 );
         szTempString_1 = sb_szTempString_1.toString( );}
         SetAttributeFromString( mSAProf, "BillingPeriod", "LastModifiedBy", szTempString_1 );
      } 

      //:END

      //:// In all cases, make sure dates are correct.
      //:IF mYearLST.CollegeTerm.BillingPeriodStartDate = "" OR mYearLST.CollegeTerm.BillingPeriodEndDate = ""
      if ( CompareAttributeToString( mYearLST, "CollegeTerm", "BillingPeriodStartDate", "" ) == 0 || CompareAttributeToString( mYearLST, "CollegeTerm", "BillingPeriodEndDate", "" ) == 0 )
      { 
         //:mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeTerm.CourseStartDate 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeTerm", "CourseStartDate" );
         //:mSAProf.BillingPeriod.EndDate   = mYearLST.CollegeTerm.CourseEndDate 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeTerm", "CourseEndDate" );
         //:ELSE
      } 
      else
      { 
         //:mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeTerm.BillingPeriodStartDate 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeTerm", "BillingPeriodStartDate" );
         //:mSAProf.BillingPeriod.EndDate   = mYearLST.CollegeTerm.BillingPeriodEndDate 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeTerm", "BillingPeriodEndDate" );
      } 

      RESULT = SetCursorNextEntity( mYearLST, "CollegeTerm", "" );
      //:END
   } 

   //:END
   //:OrderEntityForView( mSAProf, "BillingPeriod", "BeginDate A" )
   OrderEntityForView( mSAProf, "BillingPeriod", "BeginDate A" );

   //:// Go to build TermEnrolled Subobjects.
   //:IF mSAProf.PeriodCollegeYear EXISTS
   lTempInteger_1 = CheckExistenceOfEntity( mSAProf, "PeriodCollegeYear" );
   if ( lTempInteger_1 == 0 )
   { 
      //:CollegeYearID = mSAProf.PeriodCollegeYear.ID
      {MutableInt mi_CollegeYearID = new MutableInt( CollegeYearID );
             GetIntegerFromAttribute( mi_CollegeYearID, mSAProf, "PeriodCollegeYear", "ID" );
      CollegeYearID = mi_CollegeYearID.intValue( );}
      //:BuildTermEnrolled( mSAProf, CollegeYearID )
      omSAProf_BuildTermEnrolled( mSAProf, CollegeYearID );
   } 

   //:END

   //:DropView( mYearLST )
   DropView( mYearLST );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildTermEnrolled( VIEW mSAProf BASED ON LOD mSAProf,
//:                   INTEGER CollegeYearID )
//:   
//:   VIEW mSAStuEn BASED ON LOD mSAStuEn 
public int 
omSAProf_BuildTermEnrolled( View     mSAProf,
                            int      CollegeYearID )
{
   zVIEW    mSAStuEn = new zVIEW( );
   //:INTEGER CurrentTermID
   int      CurrentTermID = 0;
   //:INTEGER CollegeTermID
   int      CollegeTermID = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:// Build TermEnrolled Subobjects.
   //:// SWAU Modification: Include Waitlisted classes in TermEnrolled because those classes are to be included in billing.
   //:ACTIVATE mSAStuEn WHERE mSAStuEn.Student.ID = mSAProf.Student.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf, "Student", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   //:     RESTRICTING mSAStuEn.Registration TO ( mSAStuEn.RegistrationCollegeYear.ID = CollegeYearID OR
   //:                                            mSAStuEn.RegisteredCollegeYear.ID   = CollegeYearID )
   //:     RESTRICTING mSAStuEn.StudentWaitlisted TO mSAStuEn.StudentWaitlistedCollegeYear.ID = CollegeYearID 
   omSAProf_fnLocalBuildQual_16( mSAProf, vTempViewVar_0, lTempInteger_0, CollegeYearID );
   RESULT = ActivateObjectInstance( mSAStuEn, "mSAStuEn", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSAStuEn "mSAStuEn"
   SetNameForView( mSAStuEn, "mSAStuEn", null, zLEVEL_TASK );
   //:FOR EACH mSAProf.PeriodCollegeTerm WITHIN mSAProf.StudentAccountProfile 
   RESULT = SetCursorFirstEntity( mSAProf, "PeriodCollegeTerm", "StudentAccountProfile" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CollegeTermID = mSAProf.PeriodCollegeTerm.ID
      {MutableInt mi_CollegeTermID = new MutableInt( CollegeTermID );
             GetIntegerFromAttribute( mi_CollegeTermID, mSAProf, "PeriodCollegeTerm", "ID" );
      CollegeTermID = mi_CollegeTermID.intValue( );}
      //:FOR EACH mSAStuEn.Registration 
      RESULT = SetCursorFirstEntity( mSAStuEn, "Registration", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSAStuEn.RegisteredCollegeTerm EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mSAStuEn, "RegisteredCollegeTerm" );
         if ( lTempInteger_1 == 0 )
         { 
            //:CurrentTermID = mSAStuEn.RegisteredCollegeTerm.ID
            {MutableInt mi_CurrentTermID = new MutableInt( CurrentTermID );
                         GetIntegerFromAttribute( mi_CurrentTermID, mSAStuEn, "RegisteredCollegeTerm", "ID" );
            CurrentTermID = mi_CurrentTermID.intValue( );}
            //:ELSE
         } 
         else
         { 
            //:IF mSAStuEn.RegistrationCollegeTerm EXISTS 
            lTempInteger_2 = CheckExistenceOfEntity( mSAStuEn, "RegistrationCollegeTerm" );
            if ( lTempInteger_2 == 0 )
            { 
               //:CurrentTermID = mSAStuEn.RegistrationCollegeTerm.ID
               {MutableInt mi_CurrentTermID = new MutableInt( CurrentTermID );
                               GetIntegerFromAttribute( mi_CurrentTermID, mSAStuEn, "RegistrationCollegeTerm", "ID" );
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

            //:END
         } 

         RESULT = SetCursorNextEntity( mSAStuEn, "Registration", "" );
         //:END
      } 

      //:END
      //:// SWAU modification to include Waitlisted classes in billing.
      //:FOR EACH mSAStuEn.StudentWaitlisted 
      RESULT = SetCursorFirstEntity( mSAStuEn, "StudentWaitlisted", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSAStuEn.StudentWaitlistedCollegeTerm.ID = CollegeTermID
         if ( CompareAttributeToInteger( mSAStuEn, "StudentWaitlistedCollegeTerm", "ID", CollegeTermID ) == 0 )
         { 
            //:CREATE ENTITY mSAProf.TermEnrolled 
            RESULT = CreateEntity( mSAProf, "TermEnrolled", zPOS_AFTER );
            //:mSAProf.TermEnrolled.Status      = "T"
            SetAttributeFromString( mSAProf, "TermEnrolled", "Status", "T" );
            //:mSAProf.TermEnrolled.CreditHours = mSAStuEn.StudentWaitlistedClass.CreditHours
            SetAttributeFromAttribute( mSAProf, "TermEnrolled", "CreditHours", mSAStuEn, "StudentWaitlistedClass", "CreditHours" );
            //:INCLUDE mSAProf.TermClass FROM mSAStuEn.StudentWaitlistedClass 
            RESULT = IncludeSubobjectFromSubobject( mSAProf, "TermClass", mSAStuEn, "StudentWaitlistedClass", zPOS_AFTER );
         } 

         RESULT = SetCursorNextEntity( mSAStuEn, "StudentWaitlisted", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSAProf, "PeriodCollegeTerm", "StudentAccountProfile" );
      //:END
   } 

   //:END
   //:DropObjectInstance( mSAStuEn )
   DropObjectInstance( mSAStuEn );
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
   //:VIEW lTermLST BASED ON LOD lTermLST
   zVIEW    lTermLST = new zVIEW( );
   //:INTEGER CurrentTermID
   int      CurrentTermID = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_2 = 0;


   //:// Build TermEnrolled Subobjects.
   //:// SWAU Modification: Include Waitlisted classes in TermEnrolled because those classes are to be included in billing.
   //:ACTIVATE mSAStuEn WHERE mSAStuEn.Student.ID = mSAProf.Student.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf, "Student", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   //:     RESTRICTING mSAStuEn.Registration TO mSAStuEn.RegistrationCollegeTerm.ID = CollegeTermID OR 
   //:                                          mSAStuEn.RegisteredCollegeTerm.ID   = CollegeTermID
   //:     RESTRICTING mSAStuEn.StudentWaitlisted TO mSAStuEn.StudentWaitlistedCollegeTerm.ID = CollegeTermID
   omSAProf_fnLocalBuildQual_17( mSAProf, vTempViewVar_0, lTempInteger_0, CollegeTermID );
   RESULT = ActivateObjectInstance( mSAStuEn, "mSAStuEn", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSAStuEn "mSAStuEn"
   SetNameForView( mSAStuEn, "mSAStuEn", null, zLEVEL_TASK );

   //:// Make sure Billing Periods exist.
   //:IF mSAProf.BillingPeriod DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( mSAProf, "BillingPeriod" );
   if ( lTempInteger_1 != 0 )
   { 
      //:ACTIVATE lTermLST WHERE lTermLST.CollegeTerm.ID = CollegeTermID
      omSAProf_fnLocalBuildQual_18( mSAProf, vTempViewVar_1, CollegeTermID );
      RESULT = ActivateObjectInstance( lTermLST, "lTermLST", mSAProf, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:CreateBillingPeriods( mSAProf, lTermLST.CollegeYear.ID ) 
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
             GetIntegerFromAttribute( mi_lTempInteger_2, lTermLST, "CollegeYear", "ID" );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
      omSAProf_CreateBillingPeriods( mSAProf, lTempInteger_2 );
      //:DropObjectInstance( lTermLST )
      DropObjectInstance( lTermLST );
      //:SET CURSOR FIRST mSAProf.BillingPeriod WHERE mSAProf.PeriodCollegeTerm.ID = CollegeTermID
      RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeTerm", "ID", CollegeTermID ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( mSAProf, "BillingPeriod", "" );
         } 

      } 

   } 

   //:END

   //:// Process BillingPeriod
   //:FOR EACH mSAStuEn.Registration 
   RESULT = SetCursorFirstEntity( mSAStuEn, "Registration", "" );
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

      RESULT = SetCursorNextEntity( mSAStuEn, "Registration", "" );
      //:END
   } 

   //:END

   //:// SWAU modification to include Waitlisted classes in billing.
   //:FOR EACH mSAStuEn.StudentWaitlisted 
   RESULT = SetCursorFirstEntity( mSAStuEn, "StudentWaitlisted", "" );
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
      RESULT = SetCursorNextEntity( mSAStuEn, "StudentWaitlisted", "" );
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
            omSAProf_fnLocalBuildQual_19( mSAProf, vTempViewVar_0, lTempInteger_0 );
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
            RESULT = SetCursorFirstEntity( mSAProf, "TermOfResidence", "" );
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
            RESULT = SetCursorFirstEntity( mSAProf, "TermOfResidence", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSAProf, "TermOfResidence", "BeginDate", szCurrentDate ) > 0 || CompareAttributeToString( mSAProf, "TermOfResidence", "EndDate", szCurrentDate ) < 0 ) )
               { 
                  RESULT = SetCursorNextEntity( mSAProf, "TermOfResidence", "" );
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
               RESULT = SetCursorFirstEntity( mSAProf, "TermOfResidence", "" );
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSAProf, "TermOfResidence", "BeginDate", szCurrentDate ) <= 0 ) )
                  { 
                     RESULT = SetCursorNextEntity( mSAProf, "TermOfResidence", "" );
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
         RESULT = SetCursorFirstEntity( mSAProf, "TermOfMealPlan", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSAProf, "TermOfMealPlan", "BeginDate", szCurrentDate ) > 0 || CompareAttributeToString( mSAProf, "TermOfMealPlan", "EndDate", szCurrentDate ) < 0 ) )
            { 
               RESULT = SetCursorNextEntity( mSAProf, "TermOfMealPlan", "" );
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
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;


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
            RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
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
            RESULT = SetCursorFirstEntity( mSAProf, "TermOfResidence", "" );
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
            RESULT = SetCursorFirstEntity( mSAProf, "TermOfMealPlan", "" );
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
            RESULT = SetCursorFirstEntity( mSAProf, "StudentAccountTransApplied", "" );
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
            if ( CompareAttributeToString( lTermLST, "CollegeTerm", "Semester", "01" ) == 0 )
            { 
               //:OrderEntityForView( mSAProf, "Enrolled", "Class.ClassStartDate A" )
               OrderEntityForView( mSAProf, "Enrolled", "Class.ClassStartDate A" );
               //:ELSE
            } 
            else
            { 
               //:OrderEntityForView( mSAProf, "Enrolled", "Class.ClassStartDate D" )
               OrderEntityForView( mSAProf, "Enrolled", "Class.ClassStartDate D" );
            } 

            //:END
            //:SET CURSOR FIRST mSAProf.Enrolled
            RESULT = SetCursorFirstEntity( mSAProf, "Enrolled", "" );
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
            RESULT = SetCursorFirstEntity( mSAProf, "DegreeCandidacy", "" );
         } 

         //:END

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
               RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSAProf, "PeriodCollegeTerm", "YearSemester", lTermLST, "CollegeTerm", "YearSemester" ) != 0 ||
                        ( CompareAttributeToString( mSAProf, "BillingPeriod", "BeginDate", "" ) != 0 && CompareAttributeToString( mSAProf, "BillingPeriod", "EndDate", "" ) != 0 ) ) )
                  { 
                     RESULT = SetCursorNextEntity( mSAProf, "BillingPeriod", "" );
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

         //:// Order Title4AuthorizationFlagHistory in reverse date order.
         //:IF mSAProf.Title4AuthorizationFlagHistory EXISTS
         lTempInteger_8 = CheckExistenceOfEntity( mSAProf, "Title4AuthorizationFlagHistory" );
         if ( lTempInteger_8 == 0 )
         { 
            //:OrderEntityForView( mSAProf, "Title4AuthorizationFlagHistory", "ModifiedDateTime D" )
            OrderEntityForView( mSAProf, "Title4AuthorizationFlagHistory", "ModifiedDateTime D" );
            //:SET CURSOR FIRST mSAProf.Title4AuthorizationFlagHistory 
            RESULT = SetCursorFirstEntity( mSAProf, "Title4AuthorizationFlagHistory", "" );
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

         //:// Only execute validations if StudentAccountProfile is not being deleted.
         //:IF mSAProf.StudentAccountProfile EXISTS 
         lTempInteger_9 = CheckExistenceOfEntity( mSAProf, "StudentAccountProfile" );
         if ( lTempInteger_9 == 0 )
         { 

            //:// Make sure AccountBalance is set from the derived value.
            //:mSAProf.StudentAccountProfile.AccountBalance = mSAProf.StudentAccountProfile.dBalance 
            SetAttributeFromAttribute( mSAProf, "StudentAccountProfile", "AccountBalance", mSAProf, "StudentAccountProfile", "dBalance" );

            //:// Create Title4AuthorizationFlagHistory as necessary.
            //:IF mSAProf.StudentAccountProfile.Title4AuthorizationFlag != ""
            if ( CompareAttributeToString( mSAProf, "StudentAccountProfile", "Title4AuthorizationFlag", "" ) != 0 )
            { 
               //:IF mSAProf.Title4AuthorizationFlagHistory EXISTS
               lTempInteger_10 = CheckExistenceOfEntity( mSAProf, "Title4AuthorizationFlagHistory" );
               if ( lTempInteger_10 == 0 )
               { 
                  //:// Check if last history entry is same as current entry.
                  //:OrderEntityForView( mSAProf, "Title4AuthorizationFlagHistory", "ModifiedDateTime D" )
                  OrderEntityForView( mSAProf, "Title4AuthorizationFlagHistory", "ModifiedDateTime D" );
                  //:SET CURSOR FIRST mSAProf.Title4AuthorizationFlagHistory 
                  RESULT = SetCursorFirstEntity( mSAProf, "Title4AuthorizationFlagHistory", "" );
                  //:IF mSAProf.Title4AuthorizationFlagHistory.Title4AuthorizationFlagValue != mSAProf.StudentAccountProfile.Title4AuthorizationFlag
                  if ( CompareAttributeToAttribute( mSAProf, "Title4AuthorizationFlagHistory", "Title4AuthorizationFlagValue", mSAProf, "StudentAccountProfile", "Title4AuthorizationFlag" ) != 0 )
                  { 
                     //:// The current entry doesn't match the history, so create a new history.
                     //:CREATE ENTITY mSAProf.Title4AuthorizationFlagHistory 
                     RESULT = CreateEntity( mSAProf, "Title4AuthorizationFlagHistory", zPOS_AFTER );
                     //:mSAProf.Title4AuthorizationFlagHistory.Title4AuthorizationFlagValue = mSAProf.StudentAccountProfile.Title4AuthorizationFlag 
                     SetAttributeFromAttribute( mSAProf, "Title4AuthorizationFlagHistory", "Title4AuthorizationFlagValue", mSAProf, "StudentAccountProfile", "Title4AuthorizationFlag" );
                     //:SetAttributeFromCurrentDateTime( mSAProf, "Title4AuthorizationFlagHistory", "ModifiedDateTime" ) 
                     {
                      ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
                      m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mSAProf, "Title4AuthorizationFlagHistory", "ModifiedDateTime" );
                      // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                     }
                  } 

                  //:END 
                  //:ELSE
               } 
               else
               { 
                  //:// There is no current history entry, so create the first one.
                  //:CREATE ENTITY mSAProf.Title4AuthorizationFlagHistory 
                  RESULT = CreateEntity( mSAProf, "Title4AuthorizationFlagHistory", zPOS_AFTER );
                  //:mSAProf.Title4AuthorizationFlagHistory.Title4AuthorizationFlagValue = mSAProf.StudentAccountProfile.Title4AuthorizationFlag 
                  SetAttributeFromAttribute( mSAProf, "Title4AuthorizationFlagHistory", "Title4AuthorizationFlagValue", mSAProf, "StudentAccountProfile", "Title4AuthorizationFlag" );
                  //:SetAttributeFromCurrentDateTime( mSAProf, "Title4AuthorizationFlagHistory", "ModifiedDateTime" ) 
                  {
                   ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mSAProf );
                   m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mSAProf, "Title4AuthorizationFlagHistory", "ModifiedDateTime" );
                   // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                  }
               } 

               //:END
            } 

            //:END
         } 

         //:END
         break ;

      //:// Create the Change Log entry.
      //://GenerateTransactionEntryWDesc( mSAProf, "SA Profile" )  
      //:// We need to execute a create Transaction operation with Transaction as the root.

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
   //:VIEW lSATrnSh BASED ON LOD lSATrnSh
   zVIEW    lSATrnSh = new zVIEW( );
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
   int      lTempInteger_5 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


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
   //:   

   //:// Tie the SA Transaction just created to the original SA Transaction that it modifies. This makes the new transaction an adjustment
   //:// to the original transaction.
   //:// For performance purposes, the mSAProf OI may not contain the original SA Transaction, In that case, we will have to activate the Transaction.
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
   RESULT = SetCursorFirstEntity( mFAProfOrigAward, "FinAidAwardTransApplied", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mFAProfOrigAward, "FinAidAwardTransApplied", "ID", "" ) == 0 ) )
      { 
         RESULT = SetCursorNextEntity( mFAProfOrigAward, "FinAidAwardTransApplied", "" );
      } 

   } 

   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:SET CURSOR FIRST mSAProfT.StudentAccountTransApplied WHERE mSAProfT.StudentAccountTransApplied.ID = mFAProfOrigAward.FinAidAwardTransApplied.ID 
      {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
             GetIntegerFromAttribute( mi_lTempInteger_4, mFAProfOrigAward, "FinAidAwardTransApplied", "ID" );
      lTempInteger_4 = mi_lTempInteger_4.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( mSAProfT, "StudentAccountTransApplied", "ID", lTempInteger_4, "" );
      //:IF RESULT >= zCURSOR_SET 
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// mSAProfT points to the original transaction and mSAProf to the new transaction just created.
         //:INCLUDE mSAProf.AdjustedSATransApplied FROM mSAProfT.StudentAccountTransApplied 
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "AdjustedSATransApplied", mSAProfT, "StudentAccountTransApplied", zPOS_AFTER );
         //:DropView( mSAProfT )
         DropView( mSAProfT );
         //:ELSE
      } 
      else
      { 
         //:// Try to activate the original Transaction.
         //:DropView( mSAProfT )
         DropView( mSAProfT );
         //:ACTIVATE lSATrnSh WHERE lSATrnSh.StudentAccountTransApplied.ID = mFAProfOrigAward.FinAidAwardTransApplied.ID
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                   GetIntegerFromAttribute( mi_lTempInteger_5, mFAProfOrigAward, "FinAidAwardTransApplied", "ID" );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );}
         omSAProf_fnLocalBuildQual_20( mSAProf, vTempViewVar_0, lTempInteger_5 );
         RESULT = ActivateObjectInstance( lSATrnSh, "lSATrnSh", mSAProf, vTempViewVar_0, zSINGLE );
         DropView( vTempViewVar_0 );
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 
            //:// mSAProfT points to the original transaction and mSAProf to the new transaction just created.
            //:INCLUDE mSAProf.AdjustedSATransApplied FROM lSATrnSh.StudentAccountTransApplied 
            RESULT = IncludeSubobjectFromSubobject( mSAProf, "AdjustedSATransApplied", lSATrnSh, "StudentAccountTransApplied", zPOS_AFTER );
         } 

         //:END
         //:DropObjectInstance( lSATrnSh )
         DropObjectInstance( lSATrnSh );
      } 

      //:END 
   } 

   //:END
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
   //:VIEW  mSAProfB BASED ON LOD mSAProfB
   zVIEW    mSAProfB = new zVIEW( );
   //:DECIMAL       dBeginningTermBalance 
   double  dBeginningTermBalance = 0.0;
   //:DECIMAL       dEndingTermBalance
   double  dEndingTermBalance = 0.0;
   //:DECIMAL       dTotalForPeriod
   double  dTotalForPeriod = 0.0;
   //:STRING ( 8 )  szEndingPeriodDate
   String   szEndingPeriodDate = null;
   //:STRING ( 8 )  szTransactionDate
   String   szTransactionDate = null;
   //:STRING ( 8 )  szTermBeginDate
   String   szTermBeginDate = null;
   //:STRING ( 8 )  szTermEndDate
   String   szTermEndDate = null;
   //:STRING ( 50 ) szTemp
   String   szTemp = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_7 = 0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //://TraceLineS("*** dTermBalance derived attribute *** ", "")

         //:// Account Balance at Beginning of the Billing Period.
         //:// Total all SA Transactions with Transaction Dates earlier than the end of this Period and then
         //:// subtract out SA Transactions for the Period to get the balance at the beginning of the Period.
         //:// We do this rather than just total SA Transactions prior to the beginning of the Period because there
         //:// are sometimes transactions, like Refunds, that come in during the Period but aren't tied to the 
         //:// Period, as they represent earlier activity, and won't be included in charges/payments for the Period.

         //:// This derived attribute will only work when all the transactions under the Billing Period exist.  
         //:IF mSAProf.BillingPeriod.wTermBalance != ""
         if ( CompareAttributeToString( mSAProf, "BillingPeriod", "wTermBalance", "" ) != 0 )
         { 
            //:// We don't want to reactivate mSAProfB if we've already computed the balance.
            //:dBeginningTermBalance = mSAProf.BillingPeriod.wTermBalance 
            {MutableDouble md_dBeginningTermBalance = new MutableDouble( dBeginningTermBalance );
                         GetDecimalFromAttribute( md_dBeginningTermBalance, mSAProf, "BillingPeriod", "wTermBalance" );
            dBeginningTermBalance = md_dBeginningTermBalance.doubleValue( );}
            //:szTemp = dBeginningTermBalance
             {StringBuilder sb_szTemp;
            if ( szTemp == null )
               sb_szTemp = new StringBuilder( 32 );
            else
               sb_szTemp = new StringBuilder( szTemp );
                        ZeidonStringConvertFromNumber( sb_szTemp, 1, 0, 50, 0, dBeginningTermBalance, "D" );
            szTemp = sb_szTemp.toString( );}
            //:TraceLineS("**** wTermBalance has value *** ", szTemp)
            TraceLineS( "**** wTermBalance has value *** ", szTemp );
            //:ELSE
         } 
         else
         { 

            //:// Total all SA Transactions that go with this Period or an earlier Period.
            //:szEndingPeriodDate = mSAProf.BillingPeriod.EndDate 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szEndingPeriodDate;
            if ( szEndingPeriodDate == null )
               sb_szEndingPeriodDate = new StringBuilder( 32 );
            else
               sb_szEndingPeriodDate = new StringBuilder( szEndingPeriodDate );
                         GetVariableFromAttribute( sb_szEndingPeriodDate, mi_lTempInteger_0, 'S', 9, mSAProf, "BillingPeriod", "EndDate", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szEndingPeriodDate = sb_szEndingPeriodDate.toString( );}
            //:TraceLineS("**** szEndingPeriodDate *** ", szEndingPeriodDate)
            TraceLineS( "**** szEndingPeriodDate *** ", szEndingPeriodDate );
            //:ACTIVATE mSAProfB WHERE mSAProfB.StudentAccountProfile.ID = mSAProf.StudentAccountProfile.ID 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                         GetIntegerFromAttribute( mi_lTempInteger_1, mSAProf, "StudentAccountProfile", "ID" );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );}
            omSAProf_fnLocalBuildQual_21( mSAProf, vTempViewVar_0, lTempInteger_1 );
            RESULT = ActivateObjectInstance( mSAProfB, "mSAProfB", mSAProf, vTempViewVar_0, zSINGLE );
            DropView( vTempViewVar_0 );
            //:IF RESULT >= 0  
            if ( RESULT >= 0 )
            { 
               //:// Don't include SA Transactions later than the end of the selected term. We will take the Transaction Date as the
               //:// Begin Date for the Billing Period tied to the SA Transaction.
               //:// If there is no Billing Period tied to the SA Transaction, then don't include the Transaction if its Trans Date is later than
               //:// the end of the selected Term.
               //:szTermBeginDate = mSAProf.BillingPeriod.BeginDate 
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szTermBeginDate;
               if ( szTermBeginDate == null )
                  sb_szTermBeginDate = new StringBuilder( 32 );
               else
                  sb_szTermBeginDate = new StringBuilder( szTermBeginDate );
                               GetVariableFromAttribute( sb_szTermBeginDate, mi_lTempInteger_2, 'S', 9, mSAProf, "BillingPeriod", "BeginDate", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szTermBeginDate = sb_szTermBeginDate.toString( );}
               //:szTermEndDate   = mSAProf.BillingPeriod.EndDate 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szTermEndDate;
               if ( szTermEndDate == null )
                  sb_szTermEndDate = new StringBuilder( 32 );
               else
                  sb_szTermEndDate = new StringBuilder( szTermEndDate );
                               GetVariableFromAttribute( sb_szTermEndDate, mi_lTempInteger_3, 'S', 9, mSAProf, "BillingPeriod", "EndDate", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szTermEndDate = sb_szTermEndDate.toString( );}
               //:FOR EACH mSAProfB.StudentAccountTransApplied 
               RESULT = SetCursorFirstEntity( mSAProfB, "StudentAccountTransApplied", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:IF mSAProfB.BillingPeriod EXISTS AND mSAProf.BillingPeriod EXISTS
                  lTempInteger_4 = CheckExistenceOfEntity( mSAProfB, "BillingPeriod" );
                  lTempInteger_5 = CheckExistenceOfEntity( mSAProf, "BillingPeriod" );
                  if ( lTempInteger_4 == 0 && lTempInteger_5 == 0 )
                  { 
                     //:szTransactionDate = mSAProfB.BillingPeriod.BeginDate 
                     {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                     StringBuilder sb_szTransactionDate;
                     if ( szTransactionDate == null )
                        sb_szTransactionDate = new StringBuilder( 32 );
                     else
                        sb_szTransactionDate = new StringBuilder( szTransactionDate );
                                           GetVariableFromAttribute( sb_szTransactionDate, mi_lTempInteger_6, 'S', 9, mSAProfB, "BillingPeriod", "BeginDate", "", 0 );
                     lTempInteger_6 = mi_lTempInteger_6.intValue( );
                     szTransactionDate = sb_szTransactionDate.toString( );}
                     //:IF szTransactionDate <= szTermEndDate
                     if ( ZeidonStringCompare( szTransactionDate, 1, 0, szTermEndDate, 1, 0, 9 ) >= 0 )
                     { 
                        //:dEndingTermBalance = dEndingTermBalance + mSAProfB.StudentAccountTransApplied.Amount 
                        {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_0, mSAProfB, "StudentAccountTransApplied", "Amount" );
                        dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                        dEndingTermBalance = dEndingTermBalance + dTempDecimal_0;
                     } 

                     //:END
                     //:ELSE
                  } 
                  else
                  { 
                     //:szTransactionDate = mSAProfB.StudentAccountTransApplied.TransactionDate 
                     {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                     StringBuilder sb_szTransactionDate;
                     if ( szTransactionDate == null )
                        sb_szTransactionDate = new StringBuilder( 32 );
                     else
                        sb_szTransactionDate = new StringBuilder( szTransactionDate );
                                           GetVariableFromAttribute( sb_szTransactionDate, mi_lTempInteger_7, 'S', 9, mSAProfB, "StudentAccountTransApplied", "TransactionDate", "", 0 );
                     lTempInteger_7 = mi_lTempInteger_7.intValue( );
                     szTransactionDate = sb_szTransactionDate.toString( );}
                     //:IF szTransactionDate <= szTermEndDate
                     if ( ZeidonStringCompare( szTransactionDate, 1, 0, szTermEndDate, 1, 0, 9 ) >= 0 )
                     { 
                        //:dEndingTermBalance = dEndingTermBalance + mSAProfB.StudentAccountTransApplied.Amount 
                        {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_1, mSAProfB, "StudentAccountTransApplied", "Amount" );
                        dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
                        dEndingTermBalance = dEndingTermBalance + dTempDecimal_1;
                     } 

                     //:END
                  } 

                  RESULT = SetCursorNextEntity( mSAProfB, "StudentAccountTransApplied", "" );
                  //:END
               } 

               //:END
            } 


            //:// The following code was replaced with the above by DonC on 5/7/2013 to handle the Insurance Charge that was 
            //:// charged to the next Fall Term, but had a TransactionDate that fell within the Start/End dates of the Summer Term.
            //:/*dEndingTermBalance = mSAProfB.StudentAccountProfile.BalanceForward 
            //://szTemp = dEndingTermBalance
            //://TraceLineS("**** dEndingTermBalance *** ", szTemp)
            //:FOR EACH mSAProfB.StudentAccountTransApplied  
            //:   IF mSAProfB.StudentAccountTransApplied.TransactionDate <= szEndingPeriodDate 
            //:      dEndingTermBalance = dEndingTermBalance + mSAProfB.StudentAccountTransApplied.Amount 
            //:   END
            //:END */
            //:END
            //:DropObjectInstance( mSAProfB )
            DropObjectInstance( mSAProfB );
            //://szTemp = dEndingTermBalance
            //://TraceLineS("**** dEndingTermBalance after StudentAccountTransApplied *** ", szTemp)

            //:// Subtract out transaction amounts for current Period.
            //:CreateViewFromView( mSAProf2, mSAProf )
            CreateViewFromView( mSAProf2, mSAProf );
            //:dTotalForPeriod = 0
            dTotalForPeriod = 0;
            //:FOR EACH mSAProf2.PeriodTransApplied  
            RESULT = SetCursorFirstEntity( mSAProf2, "PeriodTransApplied", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:dTotalForPeriod = dTotalForPeriod + mSAProf2.PeriodTransApplied.Amount 
               {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                               GetDecimalFromAttribute( md_dTempDecimal_2, mSAProf2, "PeriodTransApplied", "Amount" );
               dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
               dTotalForPeriod = dTotalForPeriod + dTempDecimal_2;
               RESULT = SetCursorNextEntity( mSAProf2, "PeriodTransApplied", "" );
            } 

            //:END 
            //:DropView( mSAProf2 )
            DropView( mSAProf2 );
            //://szTemp = dTotalForPeriod
            //://TraceLineS("**** dTotalForPeriod  *** ", szTemp)

            //:// The amount for the term is the balance at end of term minus any charges/credits for the current term.
            //:dBeginningTermBalance = dEndingTermBalance - dTotalForPeriod
            dBeginningTermBalance = dEndingTermBalance - dTotalForPeriod;
         } 

         //://szTemp = dBeginningTermBalance
         //://TraceLineS("**** dEndingTermBalance after dTotalForPeriod *** ", szTemp)

         //:END

         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                 InternalAttribStructure, dBeginningTermBalance, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dBeginningTermBalance, 0 );
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
         RESULT = SetCursorFirstEntity( mSAProf2, "PeriodTransApplied", "" );
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

            RESULT = SetCursorNextEntity( mSAProf2, "PeriodTransApplied", "" );
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
         RESULT = SetCursorFirstEntity( mSAProf2, "PeriodTransApplied", "" );
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

            RESULT = SetCursorNextEntity( mSAProf2, "PeriodTransApplied", "" );
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
         RESULT = SetCursorFirstEntity( mSAProf2, "PeriodTransApplied", "" );
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

            RESULT = SetCursorNextEntity( mSAProf2, "PeriodTransApplied", "" );
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
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;


   //:// Remove the current charges for the College Term, except for those identified as not generatable.
   //:NAME VIEW mSAProf "mSAProfTest"
   SetNameForView( mSAProf, "mSAProfTest", null, zLEVEL_TASK );
   //:FOR EACH mSAProf.AppliedBillingPeriodTerm WITHIN mSAProf.StudentAccountProfile
   RESULT = SetCursorFirstEntity( mSAProf, "AppliedBillingPeriodTerm", "StudentAccountProfile" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSAProf.AppliedBillingPeriodTerm.ID = CollegeTermID
      if ( CompareAttributeToInteger( mSAProf, "AppliedBillingPeriodTerm", "ID", CollegeTermID ) == 0 )
      { 

         //:// Only delete transactions where ....
         //:IF mSAProf.SATransactionCode.ClearOnRegenerateChargesFlag = "Y"
         if ( CompareAttributeToString( mSAProf, "SATransactionCode", "ClearOnRegenerateChargesFlag", "Y" ) == 0 )
         { 

            //:// Skip Removing posted entries.
            //:IF mSAProf.StudentAccountTransApplied.DatePosted = "" 
            if ( CompareAttributeToString( mSAProf, "StudentAccountTransApplied", "DatePosted", "" ) == 0 )
            { 

               //:ACTIVATE dSATrans WHERE dSATrans.StudentAccountTransApplied.ID = mSAProf.StudentAccountTransApplied.ID 
               {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                               GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf, "StudentAccountTransApplied", "ID" );
               lTempInteger_0 = mi_lTempInteger_0.intValue( );}
               omSAProf_fnLocalBuildQual_26( mSAProf, vTempViewVar_0, lTempInteger_0 );
               RESULT = ActivateObjectInstance( dSATrans, "dSATrans", mSAProf, vTempViewVar_0, zSINGLE );
               DropView( vTempViewVar_0 );
               //:NAME VIEW dSATrans "dSATrans"
               SetNameForView( dSATrans, "dSATrans", null, zLEVEL_TASK );
               //:IF dSATrans.DepositGrouping EXISTS OR
               lTempInteger_1 = CheckExistenceOfEntity( dSATrans, "DepositGrouping" );
               //:   dSATrans.SASubAccountItem EXISTS OR
               lTempInteger_2 = CheckExistenceOfEntity( dSATrans, "SASubAccountItem" );
               //:   dSATrans.APBillPayment EXISTS OR
               lTempInteger_3 = CheckExistenceOfEntity( dSATrans, "APBillPayment" );
               //:   dSATrans.GLJournalEntry EXISTS OR
               lTempInteger_4 = CheckExistenceOfEntity( dSATrans, "GLJournalEntry" );
               //:   dSATrans.Vendor EXISTS
               lTempInteger_5 = CheckExistenceOfEntity( dSATrans, "Vendor" );
               if ( lTempInteger_1 == 0 || lTempInteger_2 == 0 || lTempInteger_3 == 0 || lTempInteger_4 == 0 || lTempInteger_5 == 0 )
               { 

                  //:szMsg = "Transaction, " + dSATrans.StudentAccountTransApplied.Description + ", is related to other data and cannot be deleted."
                  {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                  StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                     GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_6, 'S', 255, dSATrans, "StudentAccountTransApplied", "Description", "", 0 );
                  lTempInteger_6 = mi_lTempInteger_6.intValue( );
                  szTempString_0 = sb_szTempString_0.toString( );}
                   {StringBuilder sb_szMsg;
                  if ( szMsg == null )
                     sb_szMsg = new StringBuilder( 32 );
                  else
                     sb_szMsg = new StringBuilder( szMsg );
                                    ZeidonStringCopy( sb_szMsg, 1, 0, "Transaction, ", 1, 0, 201 );
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
                                    ZeidonStringConcat( sb_szMsg, 1, 0, ", is related to other data and cannot be deleted.", 1, 0, 201 );
                  szMsg = sb_szMsg.toString( );}
                  //:MessageSend( mSAProf, "", "Delete Transactions", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( mSAProf, "", "Delete Transactions", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:ELSE
               } 
               else
               { 
                  //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm WITHIN mSAProf.StudentAccountProfile
                  //:           WHERE mSAProf.PeriodCollegeTerm.ID = CollegeTermID
                  RESULT = SetCursorFirstEntityByInteger( mSAProf, "PeriodCollegeTerm", "ID", CollegeTermID, "StudentAccountProfile" );
                  //:IF RESULT >= zCURSOR_SET 
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:SET CURSOR FIRST mSAProf.PeriodTransApplied WHERE mSAProf.PeriodTransApplied.ID = mSAProf.StudentAccountTransApplied.ID
                     {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                                           GetIntegerFromAttribute( mi_lTempInteger_7, mSAProf, "StudentAccountTransApplied", "ID" );
                     lTempInteger_7 = mi_lTempInteger_7.intValue( );}
                     RESULT = SetCursorFirstEntityByInteger( mSAProf, "PeriodTransApplied", "ID", lTempInteger_7, "" );
                     //:IF RESULT >= zCURSOR_SET 
                     if ( RESULT >= zCURSOR_SET )
                     { 
                        //:RelinkInstanceToInstance( mSAProf, "PeriodTransApplied", mSAProf, "StudentAccountTransApplied" )
                        RelinkInstanceToInstance( mSAProf, "PeriodTransApplied", mSAProf, "StudentAccountTransApplied" );
                     } 

                     //:END
                  } 

                  //:END
                  //:DELETE ENTITY mSAProf.StudentAccountTransApplied NONE 
                  RESULT = DeleteEntity( mSAProf, "StudentAccountTransApplied", zREPOS_NONE );
               } 

               //:END

               //:DropObjectInstance( dSATrans )
               DropObjectInstance( dSATrans );
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mSAProf, "AppliedBillingPeriodTerm", "StudentAccountProfile" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dIsDistanceLearning( VIEW mSAProf BASED ON LOD mSAProf,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )
//:                  
//:   VIEW mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dIsDistanceLearning( View     mSAProf,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //://CreateViewFromView( mSAProf2, mSAProf )
         //://SET CURSOR FIRST mSAProf2.Category WHERE mSAProf2.Category.Name = "Distance Learning" 
         //://IF RESULT >= zCURSOR_SET
         //:IF mSAProf.Student.AdultStudiesFlag = "Y"
         if ( CompareAttributeToString( mSAProf, "Student", "AdultStudiesFlag", "Y" ) == 0 )
         { 
            //:StoreStringInRecord ( mSAProf, InternalEntityStructure, InternalAttribStructure, "Y" )
            StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, "Y" );
            //:ELSE
         } 
         else
         { 
            //:StoreStringInRecord ( mSAProf, InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, "" );
         } 

         //:END
         break ;
      //:   //DropView( mSAProf2 )

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
   //:STRING ( 10 )  szStartDate
   String   szStartDate = null;
   //:STRING ( 10 )  szEndDate
   String   szEndDate = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_6 = 0;

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
   RESULT = SetCursorFirstEntityByInteger( lTermLSTT, "CollegeTerm", "ID", nCollegeTermID, "" );

   //:IF lTermLSTT.CollegeTerm.ResidencyStartDate = "" OR lTermLSTT.CollegeTerm.ResidencyEndDate = ""
   if ( CompareAttributeToString( lTermLSTT, "CollegeTerm", "ResidencyStartDate", "" ) == 0 || CompareAttributeToString( lTermLSTT, "CollegeTerm", "ResidencyEndDate", "" ) == 0 )
   { 
      //:szStartDate = lTermLSTT.CollegeTerm.CourseStartDate
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szStartDate;
      if ( szStartDate == null )
         sb_szStartDate = new StringBuilder( 32 );
      else
         sb_szStartDate = new StringBuilder( szStartDate );
             GetVariableFromAttribute( sb_szStartDate, mi_lTempInteger_0, 'S', 11, lTermLSTT, "CollegeTerm", "CourseStartDate", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szStartDate = sb_szStartDate.toString( );}
      //:szEndDate = lTermLSTT.CollegeTerm.CourseEndDate
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szEndDate;
      if ( szEndDate == null )
         sb_szEndDate = new StringBuilder( 32 );
      else
         sb_szEndDate = new StringBuilder( szEndDate );
             GetVariableFromAttribute( sb_szEndDate, mi_lTempInteger_1, 'S', 11, lTermLSTT, "CollegeTerm", "CourseEndDate", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szEndDate = sb_szEndDate.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szStartDate = lTermLSTT.CollegeTerm.ResidencyStartDate
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szStartDate;
      if ( szStartDate == null )
         sb_szStartDate = new StringBuilder( 32 );
      else
         sb_szStartDate = new StringBuilder( szStartDate );
             GetVariableFromAttribute( sb_szStartDate, mi_lTempInteger_2, 'S', 11, lTermLSTT, "CollegeTerm", "ResidencyStartDate", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szStartDate = sb_szStartDate.toString( );}
      //:szEndDate = lTermLSTT.CollegeTerm.ResidencyEndDate
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szEndDate;
      if ( szEndDate == null )
         sb_szEndDate = new StringBuilder( 32 );
      else
         sb_szEndDate = new StringBuilder( szEndDate );
             GetVariableFromAttribute( sb_szEndDate, mi_lTempInteger_3, 'S', 11, lTermLSTT, "CollegeTerm", "ResidencyEndDate", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szEndDate = sb_szEndDate.toString( );}
   } 

   //:END

   //:ACTIVATE mSAProf 
   //:   WHERE mSAProf.Student.ID = mSAStu.Student.ID 
   //:     AND mSAProf.ProfileAdministrativeDivision.ID = mUser.CurrentAdministrativeDivision.ID 
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
       GetIntegerFromAttribute( mi_lTempInteger_4, mSAStu, "Student", "ID" );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );}
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
       GetIntegerFromAttribute( mi_lTempInteger_5, mUser, "CurrentAdministrativeDivision", "ID" );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );}
   //:   RESTRICTING mSAProf.FinAidProfile TO mSAProf.FinAidCollegeYear.ID = nCollegeYearID 
   //:   //RESTRICTING mSAProf.Student TO mSAProf.Student.ID = 0
   //:   // I wanted to restrict person but then I was getting a cardinality violation on
   //:   // AcceptSubobject( mSAProf, "StudentAccountProfile")
   //:   //RESTRICTING mSAProf.Person TO mSAProf.Person.ID = 0 
   //:   RESTRICTING mSAProf.FinAidProfile TO mSAProf.FinAidProfile.ID = 0 
   //:   RESTRICTING mSAProf.VehicleRegistration TO mSAProf.VehicleRegistration.ID = 0 
   //:   RESTRICTING mSAProf.Address TO mSAProf.Address.ID = 0 
   //:   RESTRICTING mSAProf.Organization TO mSAProf.Organization.ID = 0 
   //:   RESTRICTING mSAProf.Prospect TO mSAProf.Prospect.ID = 0 
   //:   RESTRICTING mSAProf.Demographics TO mSAProf.Demographics.ID = 0 
   //:   RESTRICTING mSAProf.Enrolled TO mSAProf.Enrolled.ID = 0
   //:   RESTRICTING mSAProf.TermOfResidence TO mSAProf.TermOfResidence.ID = 0
   //:   RESTRICTING mSAProf.StudentAccountTransApplied TO mSAProf.StudentAccountTransApplied.ID = 0
   //:   RESTRICTING mSAProf.TermOfMealPlan TO mSAProf.TermOfMealPlan.BeginDate >= szStartDate 
   //:            AND mSAProf.TermOfMealPlan.BeginDate <= szEndDate 
   //:            //AND mSAProf.TermOfMealPlan.WithdrawalDate = ""
   //:   RESTRICTING mSAProf.BillingPeriod TO mSAProf.PeriodCollegeYear.ID = nCollegeYearID
   omSAProf_fnLocalBuildQual_27( mSAStu, vTempViewVar_0, lTempInteger_4, lTempInteger_5, nCollegeYearID, szStartDate, szEndDate );
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
   lTempInteger_6 = CheckExistenceOfEntity( mSAProf, "BillingPeriod" );
   if ( lTempInteger_6 != 0 )
   { 
      //:// I do not want to BuildTermEnrolled which is in UpdateBillingPeriods.  
      //:// Not sure what to do.
      //:UpdateBillingPeriods(  mSAProf, nCollegeYearID )
      omSAProf_UpdateBillingPeriods( mSAProf, nCollegeYearID );
   } 

   //:END

   //:// Position on current Term.
   //:SET CURSOR FIRST mSAProf.BillingPeriod WHERE mSAProf.PeriodCollegeTerm.ID = nCollegeTermID  
   RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeTerm", "ID", nCollegeTermID ) != 0 ) )
      { 
         RESULT = SetCursorNextEntity( mSAProf, "BillingPeriod", "" );
      } 

   } 

   //:IF RESULT >= zCURSOR_SET  
   if ( RESULT >= zCURSOR_SET )
   { 
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm WHERE mSAProf.PeriodCollegeTerm.ID = nCollegeTermID  
      RESULT = SetCursorFirstEntityByInteger( mSAProf, "PeriodCollegeTerm", "ID", nCollegeTermID, "" );
      //:ELSE 
   } 
   else
   { 
      //:SET CURSOR FIRST mSAProf.BillingPeriod WHERE mSAProf.PeriodCollegeYear.ID = nCollegeYearID  
      RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeYear", "ID", nCollegeYearID ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( mSAProf, "BillingPeriod", "" );
         } 

      } 

      //:IF RESULT < zCURSOR_SET  
      if ( RESULT < zCURSOR_SET )
      { 
         //:SET CURSOR FIRST mSAProf.BillingPeriod // in this case only
         RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
      } 

      //:END 
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm // in this case only
      RESULT = SetCursorFirstEntity( mSAProf, "PeriodCollegeTerm", "" );
   } 

   //:END
   return( 0 );
//       
//    //LocateMealPlan( mSAProf )
//    
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
   //:DECIMAL dCashDiscount3
   double  dCashDiscount3 = 0.0;
   //:DECIMAL dCashAmount
   double  dCashAmount = 0.0;
   //:DECIMAL dCreditAmount
   double  dCreditAmount = 0.0;
   //:DECIMAL dSubtractAmount
   double  dSubtractAmount = 0.0;
   //:DECIMAL dTotalEligibleCharges
   double  dTotalEligibleCharges = 0.0;
   //:DECIMAL dAmountOwed
   double  dAmountOwed = 0.0;
   //:DECIMAL dAmountOwedMinusCash
   double  dAmountOwedMinusCash = 0.0;
   //:STRING ( 25 ) szTemp
   String   szTemp = null;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:   TraceLineS("*** dPotentialCashDiscount Calculation *** ", szTemp )
         TraceLineS( "*** dPotentialCashDiscount Calculation *** ", szTemp );

         //:// Determine the potential Cash Discount by adding up those charges for the Period that qualify
         //:// and multiplying them by the Discount Rate for the Term.
         //:// If no Discount Rate exists, don't compute a Cash Discount.

         //:   // Activate the Discount Rate for the Term.
         //:   ACTIVATE mSAChrgT WHERE mSAChrgT.SATransactionCode.TransactionCode = "CASHDISC"
         //:        RESTRICTING mSAChrgT.SATransactionTerm TO mSAChrgT.CollegeTerm.ID = mSAProf.PeriodCollegeTerm.ID 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf, "PeriodCollegeTerm", "ID" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         omSAProf_fnLocalBuildQual_24( mSAProf, vTempViewVar_0, lTempInteger_0 );
         RESULT = ActivateObjectInstance( mSAChrgT, "mSAChrgT", mSAProf, vTempViewVar_0, zSINGLE );
         DropView( vTempViewVar_0 );
         //:   NAME VIEW mSAChrgT "mSAChrgTDiscount"
         SetNameForView( mSAChrgT, "mSAChrgTDiscount", null, zLEVEL_TASK );
         //:   IF mSAChrgT.SATransactionTerm DOES NOT EXIST
         lTempInteger_1 = CheckExistenceOfEntity( mSAChrgT, "SATransactionTerm" );
         if ( lTempInteger_1 != 0 )
         { 
            //:   DropObjectInstance( mSAChrgT )
            DropObjectInstance( mSAChrgT );
            //:   RETURN -1
            if(8==8)return( -1 );
         } 

         //:   END
         //:   dDiscountRate = mSAChrgT.SATransactionTerm.Amount
         {MutableDouble md_dDiscountRate = new MutableDouble( dDiscountRate );
                   GetDecimalFromAttribute( md_dDiscountRate, mSAChrgT, "SATransactionTerm", "Amount" );
         dDiscountRate = md_dDiscountRate.doubleValue( );}
         //:   
         //:   // Activate list of Transactions for the current BillingPeriod that are eligible for a discount.
         //:   ACTIVATE lSATrnSh Multiple WHERE lSATrnSh.BillingPeriod.ID = mSAProf.BillingPeriod.ID 
         //:                                AND lSATrnSh.SATransactionCode.EligibleForCashDiscount = "Y"
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                   GetIntegerFromAttribute( mi_lTempInteger_2, mSAProf, "BillingPeriod", "ID" );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );}
         omSAProf_fnLocalBuildQual_25( mSAProf, vTempViewVar_1, lTempInteger_2 );
         RESULT = ActivateObjectInstance( lSATrnSh, "lSATrnSh", mSAProf, vTempViewVar_1, zMULTIPLE );
         DropView( vTempViewVar_1 );
         //:   NAME VIEW lSATrnSh "lSATrnSh"
         SetNameForView( lSATrnSh, "lSATrnSh", null, zLEVEL_TASK );
         //:   dTotalEligibleCharges = 0
         dTotalEligibleCharges = 0;
         //:   FOR EACH lSATrnSh.StudentAccountTransApplied 
         RESULT = SetCursorFirstEntity( lSATrnSh, "StudentAccountTransApplied", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:   dTotalEligibleCharges = dTotalEligibleCharges + lSATrnSh.StudentAccountTransApplied.Amount 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, lSATrnSh, "StudentAccountTransApplied", "Amount" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dTotalEligibleCharges = dTotalEligibleCharges + dTempDecimal_0;
            RESULT = SetCursorNextEntity( lSATrnSh, "StudentAccountTransApplied", "" );
         } 

         //:   END
         //:               
         //:   // Cash Discount is (Total Eligible Transactions - Approved Aid) * .03
         //:   dCashDiscount = ( dTotalEligibleCharges - mSAProf.BillingPeriod.dRegistrationDisbAmountApproved ) * dDiscountRate
         {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                   GetDecimalFromAttribute( md_dTempDecimal_1, mSAProf, "BillingPeriod", "dRegistrationDisbAmountApproved" );
         dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
         dCashDiscount = ( dTotalEligibleCharges - dTempDecimal_1 ) * dDiscountRate;
         //:   szTemp = dCashDiscount
          {StringBuilder sb_szTemp;
         if ( szTemp == null )
            sb_szTemp = new StringBuilder( 32 );
         else
            sb_szTemp = new StringBuilder( szTemp );
                  ZeidonStringConvertFromNumber( sb_szTemp, 1, 0, 25, 0, dCashDiscount, "D" );
         szTemp = sb_szTemp.toString( );}
         //:   TraceLineS("*** dPotentialCashDiscount Original Cash Discount *** ", szTemp )
         TraceLineS( "*** dPotentialCashDiscount Original Cash Discount *** ", szTemp );
         //:   IF dCashDiscount < 0
         if ( dCashDiscount < 0 )
         { 
            //:   dCashDiscount = 0
            dCashDiscount = 0;
         } 

         //:   END
         //:   
         //:   // KJS 04/09/13 - I am going to change this at the moment. Because now I feel that the student can
         //:   // only enter in a credit payment (otherwise, the cash stays as it is). Then we can figure cash
         //:   // payment from what student will pay in credit?
         //:   dCashAmount = mSAProf.BillingPeriod.CashCheckAmount
         {MutableDouble md_dCashAmount = new MutableDouble( dCashAmount );
                   GetDecimalFromAttribute( md_dCashAmount, mSAProf, "BillingPeriod", "CashCheckAmount" );
         dCashAmount = md_dCashAmount.doubleValue( );}
         //:      szTemp = dCashAmount
          {StringBuilder sb_szTemp;
         if ( szTemp == null )
            sb_szTemp = new StringBuilder( 32 );
         else
            sb_szTemp = new StringBuilder( szTemp );
                  ZeidonStringConvertFromNumber( sb_szTemp, 1, 0, 25, 0, dCashAmount, "D" );
         szTemp = sb_szTemp.toString( );}
         //:      TraceLineS("*** dCashAmount *** ", szTemp )
         TraceLineS( "*** dCashAmount *** ", szTemp );
         //:   dCreditAmount = mSAProf.BillingPeriod.CreditDebitAmount
         {MutableDouble md_dCreditAmount = new MutableDouble( dCreditAmount );
                   GetDecimalFromAttribute( md_dCreditAmount, mSAProf, "BillingPeriod", "CreditDebitAmount" );
         dCreditAmount = md_dCreditAmount.doubleValue( );}
         //:      szTemp = dCreditAmount
          {StringBuilder sb_szTemp;
         if ( szTemp == null )
            sb_szTemp = new StringBuilder( 32 );
         else
            sb_szTemp = new StringBuilder( szTemp );
                  ZeidonStringConvertFromNumber( sb_szTemp, 1, 0, 25, 0, dCreditAmount, "D" );
         szTemp = sb_szTemp.toString( );}
         //:      TraceLineS("*** dCreditAmount *** ", szTemp )
         TraceLineS( "*** dCreditAmount *** ", szTemp );
         //:   dAmountOwed = mSAProf.BillingPeriod.wTotalOwedForTermNoCashDisc   
         {MutableDouble md_dAmountOwed = new MutableDouble( dAmountOwed );
                   GetDecimalFromAttribute( md_dAmountOwed, mSAProf, "BillingPeriod", "wTotalOwedForTermNoCashDisc" );
         dAmountOwed = md_dAmountOwed.doubleValue( );}
         //:      szTemp = dAmountOwed
          {StringBuilder sb_szTemp;
         if ( szTemp == null )
            sb_szTemp = new StringBuilder( 32 );
         else
            sb_szTemp = new StringBuilder( szTemp );
                  ZeidonStringConvertFromNumber( sb_szTemp, 1, 0, 25, 0, dAmountOwed, "D" );
         szTemp = sb_szTemp.toString( );}
         //:      TraceLineS("*** wTotalOwedForTermNoCashDisc *** ", szTemp )
         TraceLineS( "*** wTotalOwedForTermNoCashDisc *** ", szTemp );
         //:   
         //:   // Testing purposes
         //:   //mSAProf.BillingPeriod.wNewCashDiscount = 0        
         //:   
         //:   TraceLineS("*** Do we have a cash or credit amount?*** ", "")                                            
         TraceLineS( "*** Do we have a cash or credit amount?*** ", "" );
         //:   
         //:   // Only calculate a different cash discount than the original, if the Student has entered amounts
         //:   // in the Cash or Credit fields.
         //:   IF dCreditAmount > 0
         if ( dCreditAmount > 0 )
         { 
            //:            
            //:   TraceLineS("*** Yes we have a cash or credit amount *** ", "")                                            
            TraceLineS( "*** Yes we have a cash or credit amount *** ", "" );
            //:   // Might need to calculate a new cash discount based on how much of this
            //:   // will be paid in cash...

            //:   
            //:   szTemp = dAmountOwedMinusCash
             {StringBuilder sb_szTemp;
            if ( szTemp == null )
               sb_szTemp = new StringBuilder( 32 );
            else
               sb_szTemp = new StringBuilder( szTemp );
                        ZeidonStringConvertFromNumber( sb_szTemp, 1, 0, 25, 0, dAmountOwedMinusCash, "D" );
            szTemp = sb_szTemp.toString( );}
            //:   TraceLineS("*** dAmountOwedMinusCash *** ", szTemp )
            TraceLineS( "*** dAmountOwedMinusCash *** ", szTemp );
            //:   szTemp = dCashAmount
             {StringBuilder sb_szTemp;
            if ( szTemp == null )
               sb_szTemp = new StringBuilder( 32 );
            else
               sb_szTemp = new StringBuilder( szTemp );
                        ZeidonStringConvertFromNumber( sb_szTemp, 1, 0, 25, 0, dCashAmount, "D" );
            szTemp = sb_szTemp.toString( );}
            //:   TraceLineS("*** dCashAmount *** ", szTemp )
            TraceLineS( "*** dCashAmount *** ", szTemp );

            //:   dCashDiscount = ( dTotalEligibleCharges - mSAProf.BillingPeriod.dRegistrationDisbAmountApproved - dCreditAmount ) * dDiscountRate
            {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                         GetDecimalFromAttribute( md_dTempDecimal_2, mSAProf, "BillingPeriod", "dRegistrationDisbAmountApproved" );
            dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
            dCashDiscount = ( dTotalEligibleCharges - dTempDecimal_2 - dCreditAmount ) * dDiscountRate;
            //:      // Cash discount should not be less than zero.
            //:      IF dCashDiscount < 0
            if ( dCashDiscount < 0 )
            { 
               //:      dCashDiscount = 0 
               dCashDiscount = 0;
            } 

            //:      END
            //:      szTemp = dCashDiscount
             {StringBuilder sb_szTemp;
            if ( szTemp == null )
               sb_szTemp = new StringBuilder( 32 );
            else
               sb_szTemp = new StringBuilder( szTemp );
                        ZeidonStringConvertFromNumber( sb_szTemp, 1, 0, 25, 0, dCashDiscount, "D" );
            szTemp = sb_szTemp.toString( );}
            //:      TraceLineS("*** dPotentialCashDiscount Cash Discount using Credit/Debit *** ", szTemp )
            TraceLineS( "*** dPotentialCashDiscount Cash Discount using Credit/Debit *** ", szTemp );
         } 

         //:         // Now this is the new amount due with the new cash discount
         //:   
         //:   END
         //:                                       
         //:   DropObjectInstance( lSATrnSh )
         DropObjectInstance( lSATrnSh );
         //:   DropObjectInstance( mSAChrgT )
         DropObjectInstance( mSAChrgT );

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
   //:STRING ( 30 ) szTemp
   String   szTemp = null;
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
   omSAProf_fnLocalBuildQual_28( mSAProf, vTempViewVar_0, lTempInteger_1, lTempInteger_2, lTempInteger_3 );
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
   omSAProf_fnLocalBuildQual_29( mSAProf, vTempViewVar_0, lTempInteger_1, lTempInteger_2, lTempInteger_3 );
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


//:TRANSFORMATION OPERATION
//:ComputeTotalTermPaid( VIEW mSAProf BASED ON LOD mSAProf,
//:                      DECIMAL dReturnedTotalTermPaid,
//:                      VIEW mFAProf BASED ON LOD mFAProf )

//:   DECIMAL dTotalCreditAmounts
public int 
omSAProf_ComputeTotalTermPaid( View     mSAProf,
                               MutableDouble  dReturnedTotalTermPaid,
                               View     mFAProf )
{
   double  dTotalCreditAmounts = 0.0;
   //:DECIMAL dTotalSACredits
   double  dTotalSACredits = 0.0;
   //:DECIMAL dTotalDisbursements
   double  dTotalDisbursements = 0.0;
   //:STRING ( 20 ) szAmount
   String   szAmount = null;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;


   //:// Compute the total paid for the Term. This is a little complex because we can't determine the difference
   //:// between a payment SA Transaction and a financial aid SA Transaction. Thus, we will first total up
   //:// all credits for the period and then subtract all financial aid for the period from the mFAProf object.
   //:// Payments and Fin Aid transactions have Type of "Credit/Payment" and Bill Type of "Credit".

   //:// Total SA Credits for Term. We assume we're on correct Term.
   //:dTotalCreditAmounts = 0
   dTotalCreditAmounts = 0;
   //:FOR EACH mSAProf.PeriodTransApplied 
   RESULT = SetCursorFirstEntity( mSAProf, "PeriodTransApplied", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSAProf.PeriodSATransactionCode.BillType = "C" AND 
      //:   mSAProf.PeriodSATransactionCode.Type     = "R"
      if ( CompareAttributeToString( mSAProf, "PeriodSATransactionCode", "BillType", "C" ) == 0 && CompareAttributeToString( mSAProf, "PeriodSATransactionCode", "Type", "R" ) == 0 )
      { 

         //:dTotalCreditAmounts = dTotalCreditAmounts + mSAProf.PeriodTransApplied.Amount
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf, "PeriodTransApplied", "Amount" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         dTotalCreditAmounts = dTotalCreditAmounts + dTempDecimal_0;
      } 

      RESULT = SetCursorNextEntity( mSAProf, "PeriodTransApplied", "" );
      //:END
   } 

   //:END

   //:// dTotalCreditAmounts is always negative, so switch it to positive.
   //:dTotalSACredits = 0 - dTotalCreditAmounts
   dTotalSACredits = 0 - dTotalCreditAmounts;

   //:// Total FA Disbursements for Term. We assume we're on correct Term.
   //:// We are looking here for all "received" aid, which would be in the PeriodTransApplied entries above.
   //:// If the award entry has not been received, the mFAProf.PerPeriodFinAidAwardDisbursement.Amount value is 0.
   //:dTotalDisbursements = 0
   dTotalDisbursements = 0;
   //:IF mFAProf != 0 
   if ( getView( mFAProf ) != null )
   { 
      //:FOR EACH mFAProf.PerPeriodFinAidAwardDisbursement 
      RESULT = SetCursorFirstEntity( mFAProf, "PerPeriodFinAidAwardDisbursement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:dTotalDisbursements = dTotalDisbursements + mFAProf.PerPeriodFinAidAwardDisbursement.Amount 
         {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                   GetDecimalFromAttribute( md_dTempDecimal_1, mFAProf, "PerPeriodFinAidAwardDisbursement", "Amount" );
         dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
         dTotalDisbursements = dTotalDisbursements + dTempDecimal_1;
         RESULT = SetCursorNextEntity( mFAProf, "PerPeriodFinAidAwardDisbursement", "" );
      } 

      //:END
   } 

   //:END

   //:// Return Difference
   //:dReturnedTotalTermPaid = dTotalSACredits - dTotalDisbursements
   dReturnedTotalTermPaid.setValue( dTotalSACredits - dTotalDisbursements );
   //:szAmount = dReturnedTotalTermPaid
    {StringBuilder sb_szAmount;
   if ( szAmount == null )
      sb_szAmount = new StringBuilder( 32 );
   else
      sb_szAmount = new StringBuilder( szAmount );
      ZeidonStringConvertFromNumber( sb_szAmount, 1, 0, 20, 0, dReturnedTotalTermPaid.doubleValue( ), "D" );
   szAmount = sb_szAmount.toString( );}
   //:TraceLineS( "*** dReturnedTotalTermPaid: ", szAmount )
   TraceLineS( "*** dReturnedTotalTermPaid: ", szAmount );
   return( 0 );
// END
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
            RESULT = SetCursorFirstEntityByInteger( mFAProf2, "CollegeTerm", "ID", lTempInteger_0, "FinAidProfile" );
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


//:TRANSFORMATION OPERATION
//:CalculatePaymentPlan( VIEW mSAProf     BASED ON LOD mSAProf,
//:                      VIEW mFAProf     BASED ON LOD mFAProf,
//:                      VIEW lSAPPlanLST BASED ON LOD mSAPPlan )

//:   VIEW wXferO      REGISTERED AS wXferO
public int 
omSAProf_CalculatePaymentPlan( View     mSAProf,
                               View     mFAProf,
                               View     lSAPPlanLST )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL dPendingChargesForTerm
   double  dPendingChargesForTerm = 0.0;
   //:DECIMAL dApprovedAidForTerm
   double  dApprovedAidForTerm = 0.0;
   //:DECIMAL dCurrentBalanceAtTermBeginning
   double  dCurrentBalanceAtTermBeginning = 0.0;
   //:DECIMAL dCurrentAmountPaidForTerm
   double  dCurrentAmountPaidForTerm = 0.0;
   //:DECIMAL dAmountDueAtRegistration
   double  dAmountDueAtRegistration = 0.0;
   //:DECIMAL dAmountDueAtRegistrationCalc      // The amount calculated for Due at Registration
   double  dAmountDueAtRegistrationCalc = 0.0;
   //:DECIMAL dAmountStudentWillPayAtReg
   double  dAmountStudentWillPayAtReg = 0.0;
   //:DECIMAL dAmountAvailableForBooks
   double  dAmountAvailableForBooks = 0.0;
   //:DECIMAL dAmountAllocatedForBooks
   double  dAmountAllocatedForBooks = 0.0;
   //:DECIMAL dCashDiscountAmount
   double  dCashDiscountAmount = 0.0;
   //:DECIMAL dTotalAmountOwed
   double  dTotalAmountOwed = 0.0;
   //:DECIMAL dAmountToSplit
   double  dAmountToSplit = 0.0;
   //:DECIMAL dRegAmountDue
   double  dRegAmountDue = 0.0;
   //:DECIMAL dAddPayments
   double  dAddPayments = 0.0;
   //:DECIMAL dAddToRegAmount
   double  dAddToRegAmount = 0.0;
   //:DECIMAL dFirstPaymentMinusDiscount
   double  dFirstPaymentMinusDiscount = 0.0;
   //:DECIMAL dCurrentBalance
   double  dCurrentBalance = 0.0;
   //:INTEGER iCount
   int      iCount = 0;
   //:STRING ( 50 )  szTemp
   String   szTemp = null;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   //:STRING ( 10 )  szCurrentDate
   String   szCurrentDate = null;
   //:STRING ( 15 )  szDecimal
   String   szDecimal = null;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   double  dTempDecimal_3 = 0.0;
   double  dTempDecimal_4 = 0.0;
   double  dTempDecimal_5 = 0.0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   double  dTempDecimal_6 = 0.0;
   double  dTempDecimal_7 = 0.0;
   double  dTempDecimal_8 = 0.0;
   double  dTempDecimal_9 = 0.0;
   int      lTempInteger_5 = 0;
   double  dTempDecimal_10 = 0.0;
   double  dTempDecimal_11 = 0.0;
   double  dTempDecimal_12 = 0.0;
   int      lTempInteger_6 = 0;
   double  dTempDecimal_13 = 0.0;
   double  dTempDecimal_14 = 0.0;

   RESULT = GetViewByName( wXferO, "wXferO", mSAProf, zLEVEL_TASK );

   //://TraceLineS("***** CalculatePaymentPlan **** ", "")
   //: 
   //: IF mSAProf.BillingPeriod.wAmountStudentWillPayAtReg = 0
   if ( CompareAttributeToInteger( mSAProf, "BillingPeriod", "wAmountStudentWillPayAtReg", 0 ) == 0 )
   { 
      //: mSAProf.BillingPeriod.wAmountStudentWillPayAtReg = ""
      SetAttributeFromString( mSAProf, "BillingPeriod", "wAmountStudentWillPayAtReg", "" );
   } 

   //: END

   //: // Determine dCurrentBalanceAtTermBeginning, dPendingChargesForTerm and dApprovedAidForTerm
   //: dCurrentBalanceAtTermBeginning = mSAProf.BillingPeriod.wTermBalance 
   {MutableDouble md_dCurrentBalanceAtTermBeginning = new MutableDouble( dCurrentBalanceAtTermBeginning );
       GetDecimalFromAttribute( md_dCurrentBalanceAtTermBeginning, mSAProf, "BillingPeriod", "wTermBalance" );
   dCurrentBalanceAtTermBeginning = md_dCurrentBalanceAtTermBeginning.doubleValue( );}

   //: // Charges for the term.
   //: dPendingChargesForTerm = mSAProf.BillingPeriod.dTermCharges 
   {MutableDouble md_dPendingChargesForTerm = new MutableDouble( dPendingChargesForTerm );
       GetDecimalFromAttribute( md_dPendingChargesForTerm, mSAProf, "BillingPeriod", "dTermCharges" );
   dPendingChargesForTerm = md_dPendingChargesForTerm.doubleValue( );}

   //: // Modified by DonC on 1/20/2011.
   //: // If there is a FinAidProfile or Period for the current term, calculate dApprovedAidForTerm ane
   //: // dCurrentAmountPaidForTerm. Otherwise, they're zero.
   //: dApprovedAidForTerm = 0
   dApprovedAidForTerm = 0;
   //: dCurrentAmountPaidForTerm = 0
   dCurrentAmountPaidForTerm = 0;
   //: IF mFAProf != 0 
   if ( getView( mFAProf ) != null )
   { 
      //:  SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod WHERE mFAProf.CollegeTerm.ID = mSAProf.PeriodCollegeTerm.ID  
      RESULT = SetCursorFirstEntity( mFAProf, "PerProfileFinAidAwardPeriod", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mFAProf, "CollegeTerm", "ID", mSAProf, "PeriodCollegeTerm", "ID" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( mFAProf, "PerProfileFinAidAwardPeriod", "" );
         } 

      } 

      //:  IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:  dApprovedAidForTerm = mFAProf.PerProfileFinAidAwardPeriod.dRegistrationDisbAmountApproved
         {MutableDouble md_dApprovedAidForTerm = new MutableDouble( dApprovedAidForTerm );
                   GetDecimalFromAttribute( md_dApprovedAidForTerm, mFAProf, "PerProfileFinAidAwardPeriod", "dRegistrationDisbAmountApproved" );
         dApprovedAidForTerm = md_dApprovedAidForTerm.doubleValue( );}
         //:  // Compute Total Payments for Term. Note that the operation needs mFAProf to be positioned on the correct Term.
         //:  // This computes credits for the term but does not include fin aid disbursements or cash discount.
         //:  ComputeTotalTermPaid(  mSAProf, dCurrentAmountPaidForTerm, mFAProf )
         {MutableDouble md_dCurrentAmountPaidForTerm = new MutableDouble( dCurrentAmountPaidForTerm );
                   omSAProf_ComputeTotalTermPaid( mSAProf, md_dCurrentAmountPaidForTerm, mFAProf );
         dCurrentAmountPaidForTerm = md_dCurrentAmountPaidForTerm.doubleValue( );}
         //:ELSE
      } 
      else
      { 
         //:  ComputeTotalTermPaid(  mSAProf, dCurrentAmountPaidForTerm, 0 )
         {MutableDouble md_dCurrentAmountPaidForTerm = new MutableDouble( dCurrentAmountPaidForTerm );
                   omSAProf_ComputeTotalTermPaid( mSAProf, md_dCurrentAmountPaidForTerm, null );
         dCurrentAmountPaidForTerm = md_dCurrentAmountPaidForTerm.doubleValue( );}
      } 

      //:  END
      //:ELSE
   } 
   else
   { 
      //: ComputeTotalTermPaid(  mSAProf, dCurrentAmountPaidForTerm, 0 )
      {MutableDouble md_dCurrentAmountPaidForTerm = new MutableDouble( dCurrentAmountPaidForTerm );
             omSAProf_ComputeTotalTermPaid( mSAProf, md_dCurrentAmountPaidForTerm, null );
      dCurrentAmountPaidForTerm = md_dCurrentAmountPaidForTerm.doubleValue( );}
   } 

   //: END


   //: // Get the calculated amount for Due at Registration.
   //: // This would be for all plans except plan I (one cash payment).
   //: // This does not include cash discount because this is an amount determined before
   //: // factoring cash discount in.
   //: dAmountDueAtRegistrationCalc = dCurrentBalanceAtTermBeginning + 
   //:                                dPendingChargesForTerm * .6    - 
   //:                                dApprovedAidForTerm            -
   //:                                dCurrentAmountPaidForTerm
   dAmountDueAtRegistrationCalc = dCurrentBalanceAtTermBeginning + dPendingChargesForTerm * .6 - dApprovedAidForTerm - dCurrentAmountPaidForTerm;

   //: IF dAmountDueAtRegistrationCalc < 0
   if ( dAmountDueAtRegistrationCalc < 0 )
   { 
      //: dAmountDueAtRegistration = 0
      dAmountDueAtRegistration = 0;
      //:ELSE
   } 
   else
   { 
      //: dAmountDueAtRegistration = dAmountDueAtRegistrationCalc
      dAmountDueAtRegistration = dAmountDueAtRegistrationCalc;
   } 

   //: END
   //: 
   //: // If the student selects payment plan I (onetime payment at registration) then the student is eligible
   //: // for a cash discount.  Display the discount.
   //: dCashDiscountAmount = mSAProf.BillingPeriod.dPotentialCashDiscount 
   {MutableDouble md_dCashDiscountAmount = new MutableDouble( dCashDiscountAmount );
       GetDecimalFromAttribute( md_dCashDiscountAmount, mSAProf, "BillingPeriod", "dPotentialCashDiscount" );
   dCashDiscountAmount = md_dCashDiscountAmount.doubleValue( );}
   //:     
   //: 
   //: // Total Amount Owed is balance + charges - approved aid
   //: // We are not factoring Cash Discount into this Total because this is what is shown in the payment plan
   //: // listing and we show how much would be due and then show the cash discount.
   //: // This is the amount due at registration for Plan I (one cash payment).
   //: dTotalAmountOwed = dCurrentBalanceAtTermBeginning + 
   //:                    dPendingChargesForTerm - 
   //:                    dApprovedAidForTerm -
   //:                    dCurrentAmountPaidForTerm
   dTotalAmountOwed = dCurrentBalanceAtTermBeginning + dPendingChargesForTerm - dApprovedAidForTerm - dCurrentAmountPaidForTerm;
   //: 
   //: // For each payment plan, display what is due at registration.  
   //: // I was doing this on the first time into payment plans, but now I'm going to do it all the time
   //: // so that this code can be reusable everywhere.  
   //: FOR EACH lSAPPlanLST.PaymentPlan 
   RESULT = SetCursorFirstEntity( lSAPPlanLST, "PaymentPlan", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:  dAddPayments = 0
      dAddPayments = 0;
      //:  
      //:  // This is for cash (a onetime payment)
      //:  IF lSAPPlanLST.PaymentPlan.NumberOfPayments = 1 
      if ( CompareAttributeToInteger( lSAPPlanLST, "PaymentPlan", "NumberOfPayments", 1 ) == 0 )
      { 
         //:   // I am assuming that if the total owed is less than zero, we would
         //:   // show that owe zero at registration
         //:   IF dTotalAmountOwed < 0
         if ( dTotalAmountOwed < 0 )
         { 
            //:   lSAPPlanLST.PaymentPlan.FirstPaymentAmount = 0
            SetAttributeFromInteger( lSAPPlanLST, "PaymentPlan", "FirstPaymentAmount", 0 );
            //:ELSE 
         } 
         else
         { 
            //:   lSAPPlanLST.PaymentPlan.FirstPaymentAmount = dTotalAmountOwed
            SetAttributeFromDecimal( lSAPPlanLST, "PaymentPlan", "FirstPaymentAmount", dTotalAmountOwed );
         } 

         //:   END
         //:   IF dCashDiscountAmount < 0
         if ( dCashDiscountAmount < 0 )
         { 
            //:   lSAPPlanLST.PaymentPlan.wCashDiscountAmount = 0
            SetAttributeFromInteger( lSAPPlanLST, "PaymentPlan", "wCashDiscountAmount", 0 );
            //:ELSE
         } 
         else
         { 
            //:   lSAPPlanLST.PaymentPlan.wCashDiscountAmount = dCashDiscountAmount
            SetAttributeFromDecimal( lSAPPlanLST, "PaymentPlan", "wCashDiscountAmount", dCashDiscountAmount );
         } 

         //:   END
         //:   lSAPPlanLST.PaymentPlan.wFirstPaymentMinusDiscount = lSAPPlanLST.PaymentPlan.FirstPaymentAmount - lSAPPlanLST.PaymentPlan.wCashDiscountAmount 
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, lSAPPlanLST, "PaymentPlan", "FirstPaymentAmount" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                   GetDecimalFromAttribute( md_dTempDecimal_1, lSAPPlanLST, "PaymentPlan", "wCashDiscountAmount" );
         dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
         dTempDecimal_2 = dTempDecimal_0 - dTempDecimal_1;
         SetAttributeFromDecimal( lSAPPlanLST, "PaymentPlan", "wFirstPaymentMinusDiscount", dTempDecimal_2 );
         //:   IF lSAPPlanLST.PaymentPlan.wFirstPaymentMinusDiscount < 0
         if ( CompareAttributeToInteger( lSAPPlanLST, "PaymentPlan", "wFirstPaymentMinusDiscount", 0 ) < 0 )
         { 
            //:   lSAPPlanLST.PaymentPlan.wFirstPaymentMinusDiscount = 0
            SetAttributeFromInteger( lSAPPlanLST, "PaymentPlan", "wFirstPaymentMinusDiscount", 0 );
         } 

         //:   END
         //:   //lSAPPlanLST.PaymentPlanLine.PaymentAmount = lSAPPlanLST.PaymentPlan.FirstPaymentAmount  
         //:ELSE
      } 
      else
      { 
         //:   lSAPPlanLST.PaymentPlan.FirstPaymentAmount = dAmountDueAtRegistration
         SetAttributeFromDecimal( lSAPPlanLST, "PaymentPlan", "FirstPaymentAmount", dAmountDueAtRegistration );
         //:   // Only payment plan I gets a cash discount.
         //:   lSAPPlanLST.PaymentPlan.wCashDiscountAmount = 0
         SetAttributeFromInteger( lSAPPlanLST, "PaymentPlan", "wCashDiscountAmount", 0 );
         //:   lSAPPlanLST.PaymentPlan.wFirstPaymentMinusDiscount = lSAPPlanLST.PaymentPlan.FirstPaymentAmount
         SetAttributeFromAttribute( lSAPPlanLST, "PaymentPlan", "wFirstPaymentMinusDiscount", lSAPPlanLST, "PaymentPlan", "FirstPaymentAmount" );
      } 

      RESULT = SetCursorNextEntity( lSAPPlanLST, "PaymentPlan", "" );
      //:  END
   } 

   //: END

   //: // These couple of values will get set if a PaymentPlan exists and other criteria are met.
   //: mSAProf.BillingPeriod.wCashDiscountAmount = 0
   SetAttributeFromInteger( mSAProf, "BillingPeriod", "wCashDiscountAmount", 0 );
   //: dAmountAllocatedForBooks = 0
   dAmountAllocatedForBooks = 0;

   //: // Set to the correct Meal plan in lSAPPlanLST.  
   //: IF mSAProf.PaymentPlan EXISTS  
   lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "PaymentPlan" );
   if ( lTempInteger_0 == 0 )
   { 
      //: SET CURSOR FIRST lSAPPlanLST.PaymentPlan 
      //:    WHERE lSAPPlanLST.PaymentPlan.ID = mSAProf.PaymentPlan.ID  
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, mSAProf, "PaymentPlan", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lSAPPlanLST, "PaymentPlan", "ID", lTempInteger_1, "" );
      //://END  I have put this END down further because I don't think we would do all this other code, if
      //:     // the user hasn't selected a payment plan.


      //:  // If the student has selected payment plan I,
      //:  // Factor the cash discount into the credits for the term.
      //:  // We are doing a minus instead of add because currently I'm showing the credits as a negative amount.
      //:  IF mSAProf.PaymentPlan EXISTS AND mSAProf.PaymentPlan.NumberOfPayments = 1 
      lTempInteger_2 = CheckExistenceOfEntity( mSAProf, "PaymentPlan" );
      if ( lTempInteger_2 == 0 && CompareAttributeToInteger( mSAProf, "PaymentPlan", "NumberOfPayments", 1 ) == 0 )
      { 
         //:  mSAProf.BillingPeriod.wCashDiscountAmount = dCashDiscountAmount
         SetAttributeFromDecimal( mSAProf, "BillingPeriod", "wCashDiscountAmount", dCashDiscountAmount );
      } 

      //:  END

      //:  // Get the amount that is due at registration for this plan, which is first payment entry.
      //:  // KJS 11/17/10 - Added the subtract cash discount.  If it is any plan besides Plan I (cash payment) then
      //:  // the cash discount is zero.
      //:  dAmountDueAtRegistration = lSAPPlanLST.PaymentPlan.FirstPaymentAmount - lSAPPlanLST.PaymentPlan.wCashDiscountAmount 
      {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
             GetDecimalFromAttribute( md_dTempDecimal_3, lSAPPlanLST, "PaymentPlan", "FirstPaymentAmount" );
      dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
      {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
             GetDecimalFromAttribute( md_dTempDecimal_4, lSAPPlanLST, "PaymentPlan", "wCashDiscountAmount" );
      dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
      dAmountDueAtRegistration = dTempDecimal_3 - dTempDecimal_4;
      //:  
      //:  IF dAmountDueAtRegistration < 0
      if ( dAmountDueAtRegistration < 0 )
      { 
         //:  dAmountDueAtRegistration = 0 
         dAmountDueAtRegistration = 0;
      } 

      //:  END
      //:  mSAProf.BillingPeriod.wAmountDueAtRegistration = dAmountDueAtRegistration
      SetAttributeFromDecimal( mSAProf, "BillingPeriod", "wAmountDueAtRegistration", dAmountDueAtRegistration );
      //:  
      //:  // Amount Student will pay at Registration.
      //:  // It could be more than what is actually due, but not less. This was validated above.
      //:  // If the amount isn't specified, the dAmountDueAtRegistration is used.
      //:  IF mSAProf.BillingPeriod.wAmountStudentWillPayAtReg = ""
      if ( CompareAttributeToString( mSAProf, "BillingPeriod", "wAmountStudentWillPayAtReg", "" ) == 0 )
      { 
         //:  dAmountStudentWillPayAtReg = dAmountDueAtRegistration
         dAmountStudentWillPayAtReg = dAmountDueAtRegistration;
         //:ELSE
      } 
      else
      { 
         //:  dAmountStudentWillPayAtReg = mSAProf.BillingPeriod.wAmountStudentWillPayAtReg
         {MutableDouble md_dAmountStudentWillPayAtReg = new MutableDouble( dAmountStudentWillPayAtReg );
                   GetDecimalFromAttribute( md_dAmountStudentWillPayAtReg, mSAProf, "BillingPeriod", "wAmountStudentWillPayAtReg" );
         dAmountStudentWillPayAtReg = md_dAmountStudentWillPayAtReg.doubleValue( );}
      } 

      //:  END
      //:  
      //:  // This is only used to see if the student has changed the amount they are paying at registration
      //:  // and so need to select the "Recalc" button.
      //:  mSAProf.BillingPeriod.wOrigAmountStudentWillPayAtReg = mSAProf.BillingPeriod.wAmountStudentWillPayAtReg 
      SetAttributeFromAttribute( mSAProf, "BillingPeriod", "wOrigAmountStudentWillPayAtReg", mSAProf, "BillingPeriod", "wAmountStudentWillPayAtReg" );
      //:  
      //:  // Total Amount Owed is balance + charges - approved aid - credits - cash discount
      //:  dTotalAmountOwed = dCurrentBalanceAtTermBeginning + 
      //:                     dPendingChargesForTerm - 
      //:                     dApprovedAidForTerm - 
      //:                     dCurrentAmountPaidForTerm -
      //:                     mSAProf.BillingPeriod.wCashDiscountAmount
      {MutableDouble md_dTempDecimal_5 = new MutableDouble( dTempDecimal_5 );
             GetDecimalFromAttribute( md_dTempDecimal_5, mSAProf, "BillingPeriod", "wCashDiscountAmount" );
      dTempDecimal_5 = md_dTempDecimal_5.doubleValue( );}
      dTotalAmountOwed = dCurrentBalanceAtTermBeginning + dPendingChargesForTerm - dApprovedAidForTerm - dCurrentAmountPaidForTerm - dTempDecimal_5;
      //:                     
      //:  // Calculate Amount Available for Books.
      //:  // Note that this is same logic used in previous operation.
      //:  IF mSAProf.PaymentPlan.NumberOfPayments > 1
      if ( CompareAttributeToInteger( mSAProf, "PaymentPlan", "NumberOfPayments", 1 ) > 0 )
      { 
         //:  //dAmountAvailableForBooks = dAmountStudentWillPayAtReg - dAmountDueAtRegistrationCalc
         //:  dAmountAvailableForBooks = dAmountStudentWillPayAtReg - dAmountDueAtRegistration
         dAmountAvailableForBooks = dAmountStudentWillPayAtReg - dAmountDueAtRegistration;
         //:ELSE
      } 
      else
      { 
         //:  dAmountAvailableForBooks = dAmountStudentWillPayAtReg - dTotalAmountOwed
         dAmountAvailableForBooks = dAmountStudentWillPayAtReg - dTotalAmountOwed;
      } 

      //:  END
      //:  
      //:  IF dAmountAvailableForBooks > 500
      if ( dAmountAvailableForBooks > 500 )
      { 
         //:  dAmountAvailableForBooks = 500
         dAmountAvailableForBooks = 500;
      } 

      //:  END
      //:  IF dAmountAvailableForBooks < 0
      if ( dAmountAvailableForBooks < 0 )
      { 
         //:  dAmountAvailableForBooks = 0
         dAmountAvailableForBooks = 0;
      } 

      //:  END
      //:  
      //:  // This is for display on Web interface.
      //:  mSAProf.BillingPeriod.wAmountCanBeUsedForBooks = dAmountAvailableForBooks
      SetAttributeFromDecimal( mSAProf, "BillingPeriod", "wAmountCanBeUsedForBooks", dAmountAvailableForBooks );
      //:  
      //:  // ******If we are in windows, then we can not specify that the student authorizes the book we would just
      //:  // assume that if there is an amount in BookStoreAccountChargeAmount, that we would use that.
      //:  // do we need to pass a value to indicate where we are?"
      //:  // ****** Also, like now for Gonzales Sanchez, he has allocated to pay more at registration
      //:  // so he has a book store amount.  We aren't using what he said he will pay at registration on
      //:  // windows so then dAmountForBooks = 0.  Not exactly sure what to do with that.
      //:  szTemp = mSAProf.BillingPeriod.BookStoreAccountChargeAmount
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szTemp;
      if ( szTemp == null )
         sb_szTemp = new StringBuilder( 32 );
      else
         sb_szTemp = new StringBuilder( szTemp );
             GetVariableFromAttribute( sb_szTemp, mi_lTempInteger_3, 'S', 51, mSAProf, "BillingPeriod", "BookStoreAccountChargeAmount", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szTemp = sb_szTemp.toString( );}
      //:  TraceLineS("*** BookStoreAccountChargeAmount ***", szTemp )
      TraceLineS( "*** BookStoreAccountChargeAmount ***", szTemp );
      //:  IF 1 = 1 AND mSAProf.BillingPeriod.BookStoreAccountChargeAmount > 0
      if ( 1 == 1 && CompareAttributeToInteger( mSAProf, "BillingPeriod", "BookStoreAccountChargeAmount", 0 ) > 0 )
      { 
         //:  TraceLineS("*** Setting AuthorizeStoreCharge = Y ***", "")
         TraceLineS( "*** Setting AuthorizeStoreCharge = Y ***", "" );
         //:  mSAProf.BillingPeriod.wAuthorizeBookStoreCharge = "Y"  
         SetAttributeFromString( mSAProf, "BillingPeriod", "wAuthorizeBookStoreCharge", "Y" );
      } 

      //:  END
      //:  
      //:  // If there is nothing that can be charged to the bookstore, then the student
      //:  // can't authorize it, and make sure there's no amount charged.
      //:  IF dAmountAvailableForBooks = 0 AND mSAProf.BillingPeriod.wAuthorizeBookStoreCharge != ""
      if ( dAmountAvailableForBooks == 0 && CompareAttributeToString( mSAProf, "BillingPeriod", "wAuthorizeBookStoreCharge", "" ) != 0 )
      { 
         //:  mSAProf.BillingPeriod.wAuthorizeBookStoreCharge = "" 
         SetAttributeFromString( mSAProf, "BillingPeriod", "wAuthorizeBookStoreCharge", "" );
         //:  mSAProf.BillingPeriod.BookStoreAccountChargeAmount = ""
         SetAttributeFromString( mSAProf, "BillingPeriod", "BookStoreAccountChargeAmount", "" );
      } 

      //:  END
      //:  
      //:  // If this function is being called from Windows, then what should we do about the Book Charge?
      //:  // In the Web, we reset wAuthorizeBookStoreCharge to "" whenever a new payment plan is selected, but
      //:  // in windows, if a new plan is selected would we want to keep this?
      //:  IF mSAProf.BillingPeriod.wAuthorizeBookStoreCharge = "Y"
      if ( CompareAttributeToString( mSAProf, "BillingPeriod", "wAuthorizeBookStoreCharge", "Y" ) == 0 )
      { 
         //:  // KJS I am changing this for now to use dAmountAvailableForBooks so that if this is called from
         //:  // windows, BookStoreAccountChargeAmount might now have a new value.
         //:  dAmountAllocatedForBooks = dAmountAvailableForBooks  
         dAmountAllocatedForBooks = dAmountAvailableForBooks;
         //:  mSAProf.BillingPeriod.BookStoreAccountChargeAmount = dAmountAvailableForBooks 
         SetAttributeFromDecimal( mSAProf, "BillingPeriod", "BookStoreAccountChargeAmount", dAmountAvailableForBooks );
         //:  //dAmountAllocatedForBooks = mSAProf.BillingPeriod.BookStoreAccountChargeAmount   
         //:ELSE
      } 
      else
      { 
         //:  // Student doesn't want any amount for the bookstore.
         //:  dAmountAllocatedForBooks = 0
         dAmountAllocatedForBooks = 0;
         //:  mSAProf.BillingPeriod.BookStoreAccountChargeAmount = 0 
         SetAttributeFromInteger( mSAProf, "BillingPeriod", "BookStoreAccountChargeAmount", 0 );
      } 

      //:  END
      //:       
      //:  // Amount that can be split up into payments is difference between Amount Owed plus Amount Allocated for Books minus
      //:  // Amount Student will pay at Registration.
      //:  // It can't be less than 0.
      //:  dAmountToSplit = dTotalAmountOwed + dAmountAllocatedForBooks - dAmountStudentWillPayAtReg
      dAmountToSplit = dTotalAmountOwed + dAmountAllocatedForBooks - dAmountStudentWillPayAtReg;
      //:  IF dAmountToSplit < 0
      if ( dAmountToSplit < 0 )
      { 
         //:  dAmountToSplit = 0
         dAmountToSplit = 0;
      } 

      //:  END
      //:  
      //:  // Take the total amount that's due and minus what they will pay at registration.
      //:  // Add in what the student says they are going to use at the bookstore.
      //:  // Split the remaining amount among the number of payments.
      //:  dAddPayments = 0
      dAddPayments = 0;
      //:  iCount = 1
      iCount = 1;
      //:  
      //:  IF lSAPPlanLST.PaymentPlan.NumberOfPayments = 1 
      if ( CompareAttributeToInteger( lSAPPlanLST, "PaymentPlan", "NumberOfPayments", 1 ) == 0 )
      { 
         //:   // This is for cash (a onetime payment)
         //:   mSAProf.PaymentPlanLineAssigned.PaidAmount = dAmountStudentWillPayAtReg
         SetAttributeFromDecimal( mSAProf, "PaymentPlanLineAssigned", "PaidAmount", dAmountStudentWillPayAtReg );
         //:ELSE
      } 
      else
      { 

         //:   FOR EACH mSAProf.PaymentPlanLineAssigned             
         RESULT = SetCursorFirstEntity( mSAProf, "PaymentPlanLineAssigned", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:    // The first payment is the one for registration. 
            //:    IF iCount = 1
            if ( iCount == 1 )
            { 
               //:     mSAProf.PaymentPlanLineAssigned.PaidAmount = dAmountStudentWillPayAtReg  
               SetAttributeFromDecimal( mSAProf, "PaymentPlanLineAssigned", "PaidAmount", dAmountStudentWillPayAtReg );
               //:     //dAddPayments = dAddPayments + dAmountStudentWillPayAtReg    Deleted by DonC 1/11/2011
               //: ELSE     
            } 
            else
            { 
               //:     mSAProf.PaymentPlanLineAssigned.PaidAmount = dAmountToSplit / lSAPPlanLST.PaymentPlan.NumberOfPayments  
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                               GetIntegerFromAttribute( mi_lTempInteger_4, lSAPPlanLST, "PaymentPlan", "NumberOfPayments" );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );}
               dTempDecimal_6 = dAmountToSplit / lTempInteger_4;
               SetAttributeFromDecimal( mSAProf, "PaymentPlanLineAssigned", "PaidAmount", dTempDecimal_6 );
               //:     dAddPayments = dAddPayments + mSAProf.PaymentPlanLineAssigned.PaidAmount
               {MutableDouble md_dTempDecimal_7 = new MutableDouble( dTempDecimal_7 );
                               GetDecimalFromAttribute( md_dTempDecimal_7, mSAProf, "PaymentPlanLineAssigned", "PaidAmount" );
               dTempDecimal_7 = md_dTempDecimal_7.doubleValue( );}
               dAddPayments = dAddPayments + dTempDecimal_7;
            } 

            //:    END
            //:    iCount = iCount + 1
            iCount = iCount + 1;
            RESULT = SetCursorNextEntity( mSAProf, "PaymentPlanLineAssigned", "" );
         } 

         //:   END
         //:   
         //:   // Since we are rounding we don't want to be off by pennies.  This is only for the last paymentplanline
         //:   dAddPayments = dAmountToSplit - dAddPayments
         dAddPayments = dAmountToSplit - dAddPayments;
         //:   mSAProf.PaymentPlanLineAssigned.PaidAmount = mSAProf.PaymentPlanLineAssigned.PaidAmount + dAddPayments  
         {MutableDouble md_dTempDecimal_8 = new MutableDouble( dTempDecimal_8 );
                   GetDecimalFromAttribute( md_dTempDecimal_8, mSAProf, "PaymentPlanLineAssigned", "PaidAmount" );
         dTempDecimal_8 = md_dTempDecimal_8.doubleValue( );}
         dTempDecimal_9 = dTempDecimal_8 + dAddPayments;
         SetAttributeFromDecimal( mSAProf, "PaymentPlanLineAssigned", "PaidAmount", dTempDecimal_9 );
         //:   // If for some reason the student has allocated to pay more than what they really owe, then this last
         //:   // payment value would be negative. We don't want to show that they will pay a negative amount
         //:   // so set the paid amount to zero.  
         //:   IF mSAProf.PaymentPlanLineAssigned.PaidAmount < 0
         if ( CompareAttributeToInteger( mSAProf, "PaymentPlanLineAssigned", "PaidAmount", 0 ) < 0 )
         { 
            //:    mSAProf.PaymentPlanLineAssigned.PaidAmount = 0 
            SetAttributeFromInteger( mSAProf, "PaymentPlanLineAssigned", "PaidAmount", 0 );
         } 

         //:   END
         //:   
         //:   // Should we check here if one of their payments is already past due?
         //:   szCurrentDate = wXferO.Root.dCurrentDate 
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
         StringBuilder sb_szCurrentDate;
         if ( szCurrentDate == null )
            sb_szCurrentDate = new StringBuilder( 32 );
         else
            sb_szCurrentDate = new StringBuilder( szCurrentDate );
                   GetVariableFromAttribute( sb_szCurrentDate, mi_lTempInteger_5, 'S', 11, wXferO, "Root", "dCurrentDate", "", 0 );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );
         szCurrentDate = sb_szCurrentDate.toString( );}
         //:   dAddToRegAmount = 0
         dAddToRegAmount = 0;
         //:   iCount = 1
         iCount = 1;
         //:   FOR EACH mSAProf.PaymentPlanLineAssigned   
         RESULT = SetCursorFirstEntity( mSAProf, "PaymentPlanLineAssigned", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:    IF iCount != 1
            if ( iCount != 1 )
            { 
               //:    // If the due date for this payment was before today's date, then
               //:    // this payment needs to be added to the "Due at Reg" payment. Don't
               //:    // look at the "Due at Registration" PaymentPlanLineAssigned.
               //:    IF szCurrentDate > mSAProf.PaymentPlanLineAssigned.DueDate  
               if ( CompareAttributeToString( mSAProf, "PaymentPlanLineAssigned", "DueDate", szCurrentDate ) < 0 )
               { 
                  //:    dAddToRegAmount = dAddToRegAmount + mSAProf.PaymentPlanLineAssigned.PaidAmount 
                  {MutableDouble md_dTempDecimal_10 = new MutableDouble( dTempDecimal_10 );
                                     GetDecimalFromAttribute( md_dTempDecimal_10, mSAProf, "PaymentPlanLineAssigned", "PaidAmount" );
                  dTempDecimal_10 = md_dTempDecimal_10.doubleValue( );}
                  dAddToRegAmount = dAddToRegAmount + dTempDecimal_10;
                  //:    DELETE ENTITY mSAProf.PaymentPlanLineAssigned NONE 
                  RESULT = DeleteEntity( mSAProf, "PaymentPlanLineAssigned", zREPOS_NONE );
               } 

               //:    END
            } 

            //:    END
            //:    iCount = iCount + 1
            iCount = iCount + 1;
            RESULT = SetCursorNextEntity( mSAProf, "PaymentPlanLineAssigned", "" );
         } 

         //:   END 
         //:   
         //:   IF dAddToRegAmount > 0
         if ( dAddToRegAmount > 0 )
         { 
            //:   // Set to the payment line assigned that is "Due at Registration".
            //:   SET CURSOR FIRST mSAProf.PaymentPlanLineAssigned
            RESULT = SetCursorFirstEntity( mSAProf, "PaymentPlanLineAssigned", "" );
            //:   mSAProf.PaymentPlanLineAssigned.PaidAmount = mSAProf.PaymentPlanLineAssigned.PaidAmount + dAddToRegAmount  
            {MutableDouble md_dTempDecimal_11 = new MutableDouble( dTempDecimal_11 );
                         GetDecimalFromAttribute( md_dTempDecimal_11, mSAProf, "PaymentPlanLineAssigned", "PaidAmount" );
            dTempDecimal_11 = md_dTempDecimal_11.doubleValue( );}
            dTempDecimal_12 = dTempDecimal_11 + dAddToRegAmount;
            SetAttributeFromDecimal( mSAProf, "PaymentPlanLineAssigned", "PaidAmount", dTempDecimal_12 );
            //:   TraceLineS("*** Adding to Due at registration ***", "")
            TraceLineS( "*** Adding to Due at registration ***", "" );
         } 

         //:   END
      } 

      //:  END
   } 

   //:     
   //: END // *** END of checking if mSAProf.PaymentPlan EXISTS.
   //:   
   //:   
   //:   
   //: // **** Recalculate the totals on the payment summary page.

   //: // If the student has selected payment plan I and they do not already have a cash discount transaction.
   //: // put the cash discount in wCashDiscountAmount so that it will be seen from the summary page.
   //: IF mSAProf.PaymentPlan EXISTS AND mSAProf.PaymentPlan.NumberOfPayments = 1 
   lTempInteger_6 = CheckExistenceOfEntity( mSAProf, "PaymentPlan" );
   if ( lTempInteger_6 == 0 && CompareAttributeToInteger( mSAProf, "PaymentPlan", "NumberOfPayments", 1 ) == 0 )
   { 
      //: mSAProf.BillingPeriod.wCashDiscountAmount = lSAPPlanLST.PaymentPlan.wCashDiscountAmount  
      SetAttributeFromAttribute( mSAProf, "BillingPeriod", "wCashDiscountAmount", lSAPPlanLST, "PaymentPlan", "wCashDiscountAmount" );
      //:ELSE
   } 
   else
   { 
      //: mSAProf.BillingPeriod.wCashDiscountAmount = 0
      SetAttributeFromInteger( mSAProf, "BillingPeriod", "wCashDiscountAmount", 0 );
   } 

   //: END
   //:     
   //: //dCurrentBalance = mSAProf.BillingPeriod.wTermBalance + mSAProf.BillingPeriod.dTermCharges + mSAProf.BillingPeriod.wTermCredits 
   //: dTotalAmountOwed = dCurrentBalanceAtTermBeginning + 
   //:                    dPendingChargesForTerm - 
   //:                    dApprovedAidForTerm - 
   //:                    dCurrentAmountPaidForTerm -
   //:                    mSAProf.BillingPeriod.wCashDiscountAmount
   {MutableDouble md_dTempDecimal_13 = new MutableDouble( dTempDecimal_13 );
       GetDecimalFromAttribute( md_dTempDecimal_13, mSAProf, "BillingPeriod", "wCashDiscountAmount" );
   dTempDecimal_13 = md_dTempDecimal_13.doubleValue( );}
   dTotalAmountOwed = dCurrentBalanceAtTermBeginning + dPendingChargesForTerm - dApprovedAidForTerm - dCurrentAmountPaidForTerm - dTempDecimal_13;

   //: // If the student is charging some money to the bookstore, then this amount needs to be added to the total amount due.
   //: IF mSAProf.BillingPeriod.BookStoreAccountChargeAmount > 0
   if ( CompareAttributeToInteger( mSAProf, "BillingPeriod", "BookStoreAccountChargeAmount", 0 ) > 0 )
   { 
      //:  dTotalAmountOwed = dTotalAmountOwed + mSAProf.BillingPeriod.BookStoreAccountChargeAmount  
      {MutableDouble md_dTempDecimal_14 = new MutableDouble( dTempDecimal_14 );
             GetDecimalFromAttribute( md_dTempDecimal_14, mSAProf, "BillingPeriod", "BookStoreAccountChargeAmount" );
      dTempDecimal_14 = md_dTempDecimal_14.doubleValue( );}
      dTotalAmountOwed = dTotalAmountOwed + dTempDecimal_14;
   } 

   //: END
   //: // This is for display on Web interface.
   //: mSAProf.BillingPeriod.wTotalOwedForTerm = dTotalAmountOwed
   SetAttributeFromDecimal( mSAProf, "BillingPeriod", "wTotalOwedForTerm", dTotalAmountOwed );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:CalculatePaymentPlanO( VIEW mSAProf     BASED ON LOD mSAProf,
//:                       VIEW mFAProf     BASED ON LOD mFAProf,
//:                       VIEW lSAPPlanLST BASED ON LOD mSAPPlan )

//:   VIEW wXferO      REGISTERED AS wXferO
public int 
omSAProf_CalculatePaymentPlanO( View     mSAProf,
                                View     mFAProf,
                                View     lSAPPlanLST )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL dPendingChargesForTerm
   double  dPendingChargesForTerm = 0.0;
   //:DECIMAL dApprovedAidForTerm
   double  dApprovedAidForTerm = 0.0;
   //:DECIMAL dCurrentBalanceAtTermBeginning
   double  dCurrentBalanceAtTermBeginning = 0.0;
   //:DECIMAL dCurrentAmountPaidForTerm
   double  dCurrentAmountPaidForTerm = 0.0;
   //:DECIMAL dAmountDueAtRegistration
   double  dAmountDueAtRegistration = 0.0;
   //:DECIMAL dAmountDueAtRegistrationCalc      // The amount calculated for Due at Registration
   double  dAmountDueAtRegistrationCalc = 0.0;
   //:DECIMAL dAmountStudentWillPayAtReg
   double  dAmountStudentWillPayAtReg = 0.0;
   //:DECIMAL dAmountAvailableForBooks
   double  dAmountAvailableForBooks = 0.0;
   //:DECIMAL dAmountAllocatedForBooks
   double  dAmountAllocatedForBooks = 0.0;
   //:DECIMAL dCashDiscountAmount
   double  dCashDiscountAmount = 0.0;
   //:DECIMAL dTotalAmountOwed
   double  dTotalAmountOwed = 0.0;
   //:DECIMAL dAmountToSplit
   double  dAmountToSplit = 0.0;
   //:DECIMAL dRegAmountDue
   double  dRegAmountDue = 0.0;
   //:DECIMAL dAddPayments
   double  dAddPayments = 0.0;
   //:DECIMAL dAddToRegAmount
   double  dAddToRegAmount = 0.0;
   //:DECIMAL dFirstPaymentMinusDiscount
   double  dFirstPaymentMinusDiscount = 0.0;
   //:DECIMAL dCurrentBalance
   double  dCurrentBalance = 0.0;
   //:INTEGER iCount
   int      iCount = 0;
   //:STRING ( 50 )  szTemp
   String   szTemp = null;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   //:STRING ( 10 )  szCurrentDate
   String   szCurrentDate = null;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   double  dTempDecimal_3 = 0.0;
   double  dTempDecimal_4 = 0.0;
   double  dTempDecimal_5 = 0.0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   double  dTempDecimal_6 = 0.0;
   double  dTempDecimal_7 = 0.0;
   double  dTempDecimal_8 = 0.0;
   double  dTempDecimal_9 = 0.0;
   int      lTempInteger_5 = 0;
   double  dTempDecimal_10 = 0.0;
   double  dTempDecimal_11 = 0.0;
   double  dTempDecimal_12 = 0.0;
   int      lTempInteger_6 = 0;
   double  dTempDecimal_13 = 0.0;
   double  dTempDecimal_14 = 0.0;

   RESULT = GetViewByName( wXferO, "wXferO", mSAProf, zLEVEL_TASK );

   //:TraceLineS("***** CalculatePaymentPlan **** ", "")
   TraceLineS( "***** CalculatePaymentPlan **** ", "" );
   //: 
   //: IF mSAProf.BillingPeriod.wAmountStudentWillPayAtReg = 0
   if ( CompareAttributeToInteger( mSAProf, "BillingPeriod", "wAmountStudentWillPayAtReg", 0 ) == 0 )
   { 
      //: mSAProf.BillingPeriod.wAmountStudentWillPayAtReg = ""
      SetAttributeFromString( mSAProf, "BillingPeriod", "wAmountStudentWillPayAtReg", "" );
   } 

   //: END

   //: // Determine dCurrentBalanceAtTermBeginning, dPendingChargesForTerm and dApprovedAidForTerm
   //: dCurrentBalanceAtTermBeginning = mSAProf.BillingPeriod.wTermBalance 
   {MutableDouble md_dCurrentBalanceAtTermBeginning = new MutableDouble( dCurrentBalanceAtTermBeginning );
       GetDecimalFromAttribute( md_dCurrentBalanceAtTermBeginning, mSAProf, "BillingPeriod", "wTermBalance" );
   dCurrentBalanceAtTermBeginning = md_dCurrentBalanceAtTermBeginning.doubleValue( );}

   //: // Charges for the term.
   //: dPendingChargesForTerm = mSAProf.BillingPeriod.dTermCharges 
   {MutableDouble md_dPendingChargesForTerm = new MutableDouble( dPendingChargesForTerm );
       GetDecimalFromAttribute( md_dPendingChargesForTerm, mSAProf, "BillingPeriod", "dTermCharges" );
   dPendingChargesForTerm = md_dPendingChargesForTerm.doubleValue( );}

   //: // Modified by DonC on 1/20/2011.
   //: // If there is a FinAidProfile or Period for the current term, calculate dApprovedAidForTerm ane
   //: // dCurrentAmountPaidForTerm. Otherwise, they're zero.
   //: dApprovedAidForTerm = 0
   dApprovedAidForTerm = 0;
   //: dCurrentAmountPaidForTerm = 0
   dCurrentAmountPaidForTerm = 0;
   //: IF mFAProf != 0 
   if ( getView( mFAProf ) != null )
   { 
      //:  SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod WHERE mFAProf.CollegeTerm.ID = mSAProf.PeriodCollegeTerm.ID  
      RESULT = SetCursorFirstEntity( mFAProf, "PerProfileFinAidAwardPeriod", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mFAProf, "CollegeTerm", "ID", mSAProf, "PeriodCollegeTerm", "ID" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( mFAProf, "PerProfileFinAidAwardPeriod", "" );
         } 

      } 

      //:  IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:  dApprovedAidForTerm = mFAProf.PerProfileFinAidAwardPeriod.dRegistrationDisbAmountApproved
         {MutableDouble md_dApprovedAidForTerm = new MutableDouble( dApprovedAidForTerm );
                   GetDecimalFromAttribute( md_dApprovedAidForTerm, mFAProf, "PerProfileFinAidAwardPeriod", "dRegistrationDisbAmountApproved" );
         dApprovedAidForTerm = md_dApprovedAidForTerm.doubleValue( );}
         //:  // Compute Total Payments for Term. Note that the operation needs mFAProf to be positioned on the correct Term.
         //:  // This computes credits for the term but does not include fin aid disbursements or cash discount.
         //:  ComputeTotalTermPaid(  mSAProf, dCurrentAmountPaidForTerm, mFAProf )
         {MutableDouble md_dCurrentAmountPaidForTerm = new MutableDouble( dCurrentAmountPaidForTerm );
                   omSAProf_ComputeTotalTermPaid( mSAProf, md_dCurrentAmountPaidForTerm, mFAProf );
         dCurrentAmountPaidForTerm = md_dCurrentAmountPaidForTerm.doubleValue( );}
         //:ELSE
      } 
      else
      { 
         //:  ComputeTotalTermPaid(  mSAProf, dCurrentAmountPaidForTerm, 0 )
         {MutableDouble md_dCurrentAmountPaidForTerm = new MutableDouble( dCurrentAmountPaidForTerm );
                   omSAProf_ComputeTotalTermPaid( mSAProf, md_dCurrentAmountPaidForTerm, null );
         dCurrentAmountPaidForTerm = md_dCurrentAmountPaidForTerm.doubleValue( );}
      } 

      //:  END
      //:ELSE
   } 
   else
   { 
      //: ComputeTotalTermPaid(  mSAProf, dCurrentAmountPaidForTerm, 0 )
      {MutableDouble md_dCurrentAmountPaidForTerm = new MutableDouble( dCurrentAmountPaidForTerm );
             omSAProf_ComputeTotalTermPaid( mSAProf, md_dCurrentAmountPaidForTerm, null );
      dCurrentAmountPaidForTerm = md_dCurrentAmountPaidForTerm.doubleValue( );}
   } 

   //: END


   //: // Get the calculated amount for Due at Registration.
   //: // This would be for all plans except plan I (one cash payment).
   //: // This does not include cash discount because this is an amount determined before
   //: // factoring cash discount in.
   //: dAmountDueAtRegistrationCalc = dCurrentBalanceAtTermBeginning + 
   //:                                dPendingChargesForTerm * .6    - 
   //:                                dApprovedAidForTerm            -
   //:                                dCurrentAmountPaidForTerm
   dAmountDueAtRegistrationCalc = dCurrentBalanceAtTermBeginning + dPendingChargesForTerm * .6 - dApprovedAidForTerm - dCurrentAmountPaidForTerm;

   //: IF dAmountDueAtRegistrationCalc < 0
   if ( dAmountDueAtRegistrationCalc < 0 )
   { 
      //: dAmountDueAtRegistration = 0
      dAmountDueAtRegistration = 0;
      //:ELSE
   } 
   else
   { 
      //: dAmountDueAtRegistration = dAmountDueAtRegistrationCalc
      dAmountDueAtRegistration = dAmountDueAtRegistrationCalc;
   } 

   //: END
   //: 
   //: // If the student selects payment plan I (onetime payment at registration) then the student is eligible
   //: // for a cash discount.  Display the discount.
   //: dCashDiscountAmount = mSAProf.BillingPeriod.dPotentialCashDiscount 
   {MutableDouble md_dCashDiscountAmount = new MutableDouble( dCashDiscountAmount );
       GetDecimalFromAttribute( md_dCashDiscountAmount, mSAProf, "BillingPeriod", "dPotentialCashDiscount" );
   dCashDiscountAmount = md_dCashDiscountAmount.doubleValue( );}
   //:     
   //: 
   //: // Total Amount Owed is balance + charges - approved aid
   //: // We are not factoring Cash Discount into this Total because this is what is shown in the payment plan
   //: // listing and we show how much would be due and then show the cash discount.
   //: // This is the amount due at registration for Plan I (one cash payment).
   //: dTotalAmountOwed = dCurrentBalanceAtTermBeginning + 
   //:                    dPendingChargesForTerm - 
   //:                    dApprovedAidForTerm -
   //:                    dCurrentAmountPaidForTerm
   dTotalAmountOwed = dCurrentBalanceAtTermBeginning + dPendingChargesForTerm - dApprovedAidForTerm - dCurrentAmountPaidForTerm;
   //: 
   //: // For each payment plan, display what is due at registration.  
   //: // I was doing this on the first time into payment plans, but now I'm going to do it all the time
   //: // so that this code can be reusable everywhere.  
   //: FOR EACH lSAPPlanLST.PaymentPlan 
   RESULT = SetCursorFirstEntity( lSAPPlanLST, "PaymentPlan", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:  dAddPayments = 0
      dAddPayments = 0;
      //:  
      //:  // This is for cash (a onetime payment)
      //:  IF lSAPPlanLST.PaymentPlan.NumberOfPayments = 1 
      if ( CompareAttributeToInteger( lSAPPlanLST, "PaymentPlan", "NumberOfPayments", 1 ) == 0 )
      { 
         //:   // I am assuming that if the total owed is less than zero, we would
         //:   // show that owe zero at registration
         //:   IF dTotalAmountOwed < 0
         if ( dTotalAmountOwed < 0 )
         { 
            //:   lSAPPlanLST.PaymentPlan.FirstPaymentAmount = 0
            SetAttributeFromInteger( lSAPPlanLST, "PaymentPlan", "FirstPaymentAmount", 0 );
            //:ELSE 
         } 
         else
         { 
            //:   lSAPPlanLST.PaymentPlan.FirstPaymentAmount = dTotalAmountOwed
            SetAttributeFromDecimal( lSAPPlanLST, "PaymentPlan", "FirstPaymentAmount", dTotalAmountOwed );
         } 

         //:   END
         //:   IF dCashDiscountAmount < 0
         if ( dCashDiscountAmount < 0 )
         { 
            //:   lSAPPlanLST.PaymentPlan.wCashDiscountAmount = 0
            SetAttributeFromInteger( lSAPPlanLST, "PaymentPlan", "wCashDiscountAmount", 0 );
            //:ELSE
         } 
         else
         { 
            //:   lSAPPlanLST.PaymentPlan.wCashDiscountAmount = dCashDiscountAmount
            SetAttributeFromDecimal( lSAPPlanLST, "PaymentPlan", "wCashDiscountAmount", dCashDiscountAmount );
         } 

         //:   END
         //:   lSAPPlanLST.PaymentPlan.wFirstPaymentMinusDiscount = lSAPPlanLST.PaymentPlan.FirstPaymentAmount - lSAPPlanLST.PaymentPlan.wCashDiscountAmount 
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, lSAPPlanLST, "PaymentPlan", "FirstPaymentAmount" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                   GetDecimalFromAttribute( md_dTempDecimal_1, lSAPPlanLST, "PaymentPlan", "wCashDiscountAmount" );
         dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
         dTempDecimal_2 = dTempDecimal_0 - dTempDecimal_1;
         SetAttributeFromDecimal( lSAPPlanLST, "PaymentPlan", "wFirstPaymentMinusDiscount", dTempDecimal_2 );
         //:   IF lSAPPlanLST.PaymentPlan.wFirstPaymentMinusDiscount < 0
         if ( CompareAttributeToInteger( lSAPPlanLST, "PaymentPlan", "wFirstPaymentMinusDiscount", 0 ) < 0 )
         { 
            //:   lSAPPlanLST.PaymentPlan.wFirstPaymentMinusDiscount = 0
            SetAttributeFromInteger( lSAPPlanLST, "PaymentPlan", "wFirstPaymentMinusDiscount", 0 );
         } 

         //:   END
         //:   //lSAPPlanLST.PaymentPlanLine.PaymentAmount = lSAPPlanLST.PaymentPlan.FirstPaymentAmount  
         //:ELSE
      } 
      else
      { 
         //:   lSAPPlanLST.PaymentPlan.FirstPaymentAmount = dAmountDueAtRegistration
         SetAttributeFromDecimal( lSAPPlanLST, "PaymentPlan", "FirstPaymentAmount", dAmountDueAtRegistration );
         //:   // Only payment plan I gets a cash discount.
         //:   lSAPPlanLST.PaymentPlan.wCashDiscountAmount = 0
         SetAttributeFromInteger( lSAPPlanLST, "PaymentPlan", "wCashDiscountAmount", 0 );
         //:   lSAPPlanLST.PaymentPlan.wFirstPaymentMinusDiscount = lSAPPlanLST.PaymentPlan.FirstPaymentAmount
         SetAttributeFromAttribute( lSAPPlanLST, "PaymentPlan", "wFirstPaymentMinusDiscount", lSAPPlanLST, "PaymentPlan", "FirstPaymentAmount" );
      } 

      RESULT = SetCursorNextEntity( lSAPPlanLST, "PaymentPlan", "" );
      //:  END
   } 

   //: END

   //: // These couple of values will get set if a PaymentPlan exists and other criteria are met.
   //: mSAProf.BillingPeriod.wCashDiscountAmount = 0
   SetAttributeFromInteger( mSAProf, "BillingPeriod", "wCashDiscountAmount", 0 );
   //: dAmountAllocatedForBooks = 0
   dAmountAllocatedForBooks = 0;

   //: // Set to the correct Meal plan in lSAPPlanLST.  
   //: IF mSAProf.PaymentPlan EXISTS  
   lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "PaymentPlan" );
   if ( lTempInteger_0 == 0 )
   { 
      //: SET CURSOR FIRST lSAPPlanLST.PaymentPlan 
      //:    WHERE lSAPPlanLST.PaymentPlan.ID = mSAProf.PaymentPlan.ID  
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, mSAProf, "PaymentPlan", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lSAPPlanLST, "PaymentPlan", "ID", lTempInteger_1, "" );
      //://END  I have put this END down further because I don't think we would do all this other code, if
      //:     // the user hasn't selected a payment plan.


      //:  // If the student has selected payment plan I,
      //:  // Factor the cash discount into the credits for the term.
      //:  // We are doing a minus instead of add because currently I'm showing the credits as a negative amount.
      //:  IF mSAProf.PaymentPlan EXISTS AND mSAProf.PaymentPlan.NumberOfPayments = 1 
      lTempInteger_2 = CheckExistenceOfEntity( mSAProf, "PaymentPlan" );
      if ( lTempInteger_2 == 0 && CompareAttributeToInteger( mSAProf, "PaymentPlan", "NumberOfPayments", 1 ) == 0 )
      { 
         //:  mSAProf.BillingPeriod.wCashDiscountAmount = dCashDiscountAmount
         SetAttributeFromDecimal( mSAProf, "BillingPeriod", "wCashDiscountAmount", dCashDiscountAmount );
      } 

      //:  END

      //:  // Get the amount that is due at registration for this plan, which is first payment entry.
      //:  // KJS 11/17/10 - Added the subtract cash discount.  If it is any plan besides Plan I (cash payment) then
      //:  // the cash discount is zero.
      //:  dAmountDueAtRegistration = lSAPPlanLST.PaymentPlan.FirstPaymentAmount - lSAPPlanLST.PaymentPlan.wCashDiscountAmount 
      {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
             GetDecimalFromAttribute( md_dTempDecimal_3, lSAPPlanLST, "PaymentPlan", "FirstPaymentAmount" );
      dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
      {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
             GetDecimalFromAttribute( md_dTempDecimal_4, lSAPPlanLST, "PaymentPlan", "wCashDiscountAmount" );
      dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
      dAmountDueAtRegistration = dTempDecimal_3 - dTempDecimal_4;
      //:  
      //:  IF dAmountDueAtRegistration < 0
      if ( dAmountDueAtRegistration < 0 )
      { 
         //:  dAmountDueAtRegistration = 0 
         dAmountDueAtRegistration = 0;
      } 

      //:  END
      //:  mSAProf.BillingPeriod.wAmountDueAtRegistration = dAmountDueAtRegistration
      SetAttributeFromDecimal( mSAProf, "BillingPeriod", "wAmountDueAtRegistration", dAmountDueAtRegistration );
      //:  
      //:  // Amount Student will pay at Registration.
      //:  // It could be more than what is actually due, but not less. This was validated above.
      //:  // If the amount isn't specified, the dAmountDueAtRegistration is used.
      //:  IF mSAProf.BillingPeriod.wAmountStudentWillPayAtReg = ""
      if ( CompareAttributeToString( mSAProf, "BillingPeriod", "wAmountStudentWillPayAtReg", "" ) == 0 )
      { 
         //:  dAmountStudentWillPayAtReg = dAmountDueAtRegistration
         dAmountStudentWillPayAtReg = dAmountDueAtRegistration;
         //:ELSE
      } 
      else
      { 
         //:  dAmountStudentWillPayAtReg = mSAProf.BillingPeriod.wAmountStudentWillPayAtReg
         {MutableDouble md_dAmountStudentWillPayAtReg = new MutableDouble( dAmountStudentWillPayAtReg );
                   GetDecimalFromAttribute( md_dAmountStudentWillPayAtReg, mSAProf, "BillingPeriod", "wAmountStudentWillPayAtReg" );
         dAmountStudentWillPayAtReg = md_dAmountStudentWillPayAtReg.doubleValue( );}
      } 

      //:  END
      //:  
      //:  // This is only used to see if the student has changed the amount they are paying at registration
      //:  // and so need to select the "Recalc" button.
      //:  mSAProf.BillingPeriod.wOrigAmountStudentWillPayAtReg = mSAProf.BillingPeriod.wAmountStudentWillPayAtReg 
      SetAttributeFromAttribute( mSAProf, "BillingPeriod", "wOrigAmountStudentWillPayAtReg", mSAProf, "BillingPeriod", "wAmountStudentWillPayAtReg" );
      //:  
      //:  // Total Amount Owed is balance + charges - approved aid - credits - cash discount
      //:  dTotalAmountOwed = dCurrentBalanceAtTermBeginning + 
      //:                     dPendingChargesForTerm - 
      //:                     dApprovedAidForTerm - 
      //:                     dCurrentAmountPaidForTerm -
      //:                     mSAProf.BillingPeriod.wCashDiscountAmount
      {MutableDouble md_dTempDecimal_5 = new MutableDouble( dTempDecimal_5 );
             GetDecimalFromAttribute( md_dTempDecimal_5, mSAProf, "BillingPeriod", "wCashDiscountAmount" );
      dTempDecimal_5 = md_dTempDecimal_5.doubleValue( );}
      dTotalAmountOwed = dCurrentBalanceAtTermBeginning + dPendingChargesForTerm - dApprovedAidForTerm - dCurrentAmountPaidForTerm - dTempDecimal_5;
      //:                     
      //:  // Calculate Amount Available for Books.
      //:  // Note that this is same logic used in previous operation.
      //:  IF mSAProf.PaymentPlan.NumberOfPayments > 1
      if ( CompareAttributeToInteger( mSAProf, "PaymentPlan", "NumberOfPayments", 1 ) > 0 )
      { 
         //:  //dAmountAvailableForBooks = dAmountStudentWillPayAtReg - dAmountDueAtRegistrationCalc
         //:  dAmountAvailableForBooks = dAmountStudentWillPayAtReg - dAmountDueAtRegistration
         dAmountAvailableForBooks = dAmountStudentWillPayAtReg - dAmountDueAtRegistration;
         //:ELSE
      } 
      else
      { 
         //:  dAmountAvailableForBooks = dAmountStudentWillPayAtReg - dTotalAmountOwed
         dAmountAvailableForBooks = dAmountStudentWillPayAtReg - dTotalAmountOwed;
      } 

      //:  END
      //:  
      //:  IF dAmountAvailableForBooks > 500
      if ( dAmountAvailableForBooks > 500 )
      { 
         //:  dAmountAvailableForBooks = 500
         dAmountAvailableForBooks = 500;
      } 

      //:  END
      //:  IF dAmountAvailableForBooks < 0
      if ( dAmountAvailableForBooks < 0 )
      { 
         //:  dAmountAvailableForBooks = 0
         dAmountAvailableForBooks = 0;
      } 

      //:  END
      //:  
      //:  // This is for display on Web interface.
      //:  mSAProf.BillingPeriod.wAmountCanBeUsedForBooks = dAmountAvailableForBooks
      SetAttributeFromDecimal( mSAProf, "BillingPeriod", "wAmountCanBeUsedForBooks", dAmountAvailableForBooks );
      //:  
      //:  // ******If we are in windows, then we can not specify that the student authorizes the book we would just
      //:  // assume that if there is an amount in BookStoreAccountChargeAmount, that we would use that.
      //:  // do we need to pass a value to indicate where we are?"
      //:  // ****** Also, like now for Gonzales Sanchez, he has allocated to pay more at registration
      //:  // so he has a book store amount.  We aren't using what he said he will pay at registration on
      //:  // windows so then dAmountForBooks = 0.  Not exactly sure what to do with that.
      //:  szTemp = mSAProf.BillingPeriod.BookStoreAccountChargeAmount
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szTemp;
      if ( szTemp == null )
         sb_szTemp = new StringBuilder( 32 );
      else
         sb_szTemp = new StringBuilder( szTemp );
             GetVariableFromAttribute( sb_szTemp, mi_lTempInteger_3, 'S', 51, mSAProf, "BillingPeriod", "BookStoreAccountChargeAmount", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szTemp = sb_szTemp.toString( );}
      //:  TraceLineS("*** BookStoreAccountChargeAmount ***", szTemp )
      TraceLineS( "*** BookStoreAccountChargeAmount ***", szTemp );
      //:  IF 1 = 1 AND mSAProf.BillingPeriod.BookStoreAccountChargeAmount > 0
      if ( 1 == 1 && CompareAttributeToInteger( mSAProf, "BillingPeriod", "BookStoreAccountChargeAmount", 0 ) > 0 )
      { 
         //:  TraceLineS("*** Setting AuthorizeStoreCharge = Y ***", "")
         TraceLineS( "*** Setting AuthorizeStoreCharge = Y ***", "" );
         //:  mSAProf.BillingPeriod.wAuthorizeBookStoreCharge = "Y"  
         SetAttributeFromString( mSAProf, "BillingPeriod", "wAuthorizeBookStoreCharge", "Y" );
      } 

      //:  END
      //:  
      //:  // If there is nothing that can be charged to the bookstore, then the student
      //:  // can't authorize it, and make sure there's no amount charged.
      //:  IF dAmountAvailableForBooks = 0 AND mSAProf.BillingPeriod.wAuthorizeBookStoreCharge != ""
      if ( dAmountAvailableForBooks == 0 && CompareAttributeToString( mSAProf, "BillingPeriod", "wAuthorizeBookStoreCharge", "" ) != 0 )
      { 
         //:  mSAProf.BillingPeriod.wAuthorizeBookStoreCharge = "" 
         SetAttributeFromString( mSAProf, "BillingPeriod", "wAuthorizeBookStoreCharge", "" );
         //:  mSAProf.BillingPeriod.BookStoreAccountChargeAmount = ""
         SetAttributeFromString( mSAProf, "BillingPeriod", "BookStoreAccountChargeAmount", "" );
      } 

      //:  END
      //:  
      //:  // If this function is being called from Windows, then what should we do about the Book Charge?
      //:  // In the Web, we reset wAuthorizeBookStoreCharge to "" whenever a new payment plan is selected, but
      //:  // in windows, if a new plan is selected would we want to keep this?
      //:  IF mSAProf.BillingPeriod.wAuthorizeBookStoreCharge = "Y"
      if ( CompareAttributeToString( mSAProf, "BillingPeriod", "wAuthorizeBookStoreCharge", "Y" ) == 0 )
      { 
         //:  // KJS I am changing this for now to use dAmountAvailableForBooks so that if this is called from
         //:  // windows, BookStoreAccountChargeAmount might now have a new value.
         //:  dAmountAllocatedForBooks = dAmountAvailableForBooks  
         dAmountAllocatedForBooks = dAmountAvailableForBooks;
         //:  mSAProf.BillingPeriod.BookStoreAccountChargeAmount = dAmountAvailableForBooks 
         SetAttributeFromDecimal( mSAProf, "BillingPeriod", "BookStoreAccountChargeAmount", dAmountAvailableForBooks );
         //:  //dAmountAllocatedForBooks = mSAProf.BillingPeriod.BookStoreAccountChargeAmount   
         //:ELSE
      } 
      else
      { 
         //:  // Student doesn't want any amount for the bookstore.
         //:  dAmountAllocatedForBooks = 0
         dAmountAllocatedForBooks = 0;
         //:  mSAProf.BillingPeriod.BookStoreAccountChargeAmount = 0 
         SetAttributeFromInteger( mSAProf, "BillingPeriod", "BookStoreAccountChargeAmount", 0 );
      } 

      //:  END
      //:       
      //:  // Amount that can be split up into payments is difference between Amount Owed plus Amount Allocated for Books minus
      //:  // Amount Student will pay at Registration.
      //:  // It can't be less than 0.
      //:  dAmountToSplit = dTotalAmountOwed + dAmountAllocatedForBooks - dAmountStudentWillPayAtReg
      dAmountToSplit = dTotalAmountOwed + dAmountAllocatedForBooks - dAmountStudentWillPayAtReg;
      //:  IF dAmountToSplit < 0
      if ( dAmountToSplit < 0 )
      { 
         //:  dAmountToSplit = 0
         dAmountToSplit = 0;
      } 

      //:  END
      //:  
      //:  // Take the total amount that's due and minus what they will pay at registration.
      //:  // Add in what the student says they are going to use at the bookstore.
      //:  // Split the remaining amount among the number of payments.
      //:  dAddPayments = 0
      dAddPayments = 0;
      //:  iCount = 1
      iCount = 1;
      //:  
      //:  IF lSAPPlanLST.PaymentPlan.NumberOfPayments = 1 
      if ( CompareAttributeToInteger( lSAPPlanLST, "PaymentPlan", "NumberOfPayments", 1 ) == 0 )
      { 
         //:   // This is for cash (a onetime payment)
         //:   mSAProf.PaymentPlanLineAssigned.PaidAmount = dAmountStudentWillPayAtReg
         SetAttributeFromDecimal( mSAProf, "PaymentPlanLineAssigned", "PaidAmount", dAmountStudentWillPayAtReg );
         //:ELSE
      } 
      else
      { 

         //:   FOR EACH mSAProf.PaymentPlanLineAssigned             
         RESULT = SetCursorFirstEntity( mSAProf, "PaymentPlanLineAssigned", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:    // The first payment is the one for registration. 
            //:    IF iCount = 1
            if ( iCount == 1 )
            { 
               //:     mSAProf.PaymentPlanLineAssigned.PaidAmount = dAmountStudentWillPayAtReg  
               SetAttributeFromDecimal( mSAProf, "PaymentPlanLineAssigned", "PaidAmount", dAmountStudentWillPayAtReg );
               //:     //dAddPayments = dAddPayments + dAmountStudentWillPayAtReg    Deleted by DonC 1/11/2011
               //: ELSE     
            } 
            else
            { 
               //:     mSAProf.PaymentPlanLineAssigned.PaidAmount = dAmountToSplit / lSAPPlanLST.PaymentPlan.NumberOfPayments  
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                               GetIntegerFromAttribute( mi_lTempInteger_4, lSAPPlanLST, "PaymentPlan", "NumberOfPayments" );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );}
               dTempDecimal_6 = dAmountToSplit / lTempInteger_4;
               SetAttributeFromDecimal( mSAProf, "PaymentPlanLineAssigned", "PaidAmount", dTempDecimal_6 );
               //:     dAddPayments = dAddPayments + mSAProf.PaymentPlanLineAssigned.PaidAmount
               {MutableDouble md_dTempDecimal_7 = new MutableDouble( dTempDecimal_7 );
                               GetDecimalFromAttribute( md_dTempDecimal_7, mSAProf, "PaymentPlanLineAssigned", "PaidAmount" );
               dTempDecimal_7 = md_dTempDecimal_7.doubleValue( );}
               dAddPayments = dAddPayments + dTempDecimal_7;
            } 

            //:    END
            //:    iCount = iCount + 1
            iCount = iCount + 1;
            RESULT = SetCursorNextEntity( mSAProf, "PaymentPlanLineAssigned", "" );
         } 

         //:   END
         //:   
         //:   // Since we are rounding we don't want to be off by pennies.  This is only for the last paymentplanline
         //:   dAddPayments = dAmountToSplit - dAddPayments
         dAddPayments = dAmountToSplit - dAddPayments;
         //:   mSAProf.PaymentPlanLineAssigned.PaidAmount = mSAProf.PaymentPlanLineAssigned.PaidAmount + dAddPayments  
         {MutableDouble md_dTempDecimal_8 = new MutableDouble( dTempDecimal_8 );
                   GetDecimalFromAttribute( md_dTempDecimal_8, mSAProf, "PaymentPlanLineAssigned", "PaidAmount" );
         dTempDecimal_8 = md_dTempDecimal_8.doubleValue( );}
         dTempDecimal_9 = dTempDecimal_8 + dAddPayments;
         SetAttributeFromDecimal( mSAProf, "PaymentPlanLineAssigned", "PaidAmount", dTempDecimal_9 );
         //:   // If for some reason the student has allocated to pay more than what they really owe, then this last
         //:   // payment value would be negative. We don't want to show that they will pay a negative amount
         //:   // so set the paid amount to zero.  
         //:   IF mSAProf.PaymentPlanLineAssigned.PaidAmount < 0
         if ( CompareAttributeToInteger( mSAProf, "PaymentPlanLineAssigned", "PaidAmount", 0 ) < 0 )
         { 
            //:    mSAProf.PaymentPlanLineAssigned.PaidAmount = 0 
            SetAttributeFromInteger( mSAProf, "PaymentPlanLineAssigned", "PaidAmount", 0 );
         } 

         //:   END
         //:   
         //:   // Should we check here if one of their payments is already past due?
         //:   szCurrentDate = wXferO.Root.dCurrentDate 
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
         StringBuilder sb_szCurrentDate;
         if ( szCurrentDate == null )
            sb_szCurrentDate = new StringBuilder( 32 );
         else
            sb_szCurrentDate = new StringBuilder( szCurrentDate );
                   GetVariableFromAttribute( sb_szCurrentDate, mi_lTempInteger_5, 'S', 11, wXferO, "Root", "dCurrentDate", "", 0 );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );
         szCurrentDate = sb_szCurrentDate.toString( );}
         //:   dAddToRegAmount = 0
         dAddToRegAmount = 0;
         //:   iCount = 1
         iCount = 1;
         //:   FOR EACH mSAProf.PaymentPlanLineAssigned   
         RESULT = SetCursorFirstEntity( mSAProf, "PaymentPlanLineAssigned", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:    IF iCount != 1
            if ( iCount != 1 )
            { 
               //:    // If the due date for this payment was before today's date, then
               //:    // this payment needs to be added to the "Due at Reg" payment. Don't
               //:    // look at the "Due at Registration" PaymentPlanLineAssigned.
               //:    IF szCurrentDate > mSAProf.PaymentPlanLineAssigned.DueDate  
               if ( CompareAttributeToString( mSAProf, "PaymentPlanLineAssigned", "DueDate", szCurrentDate ) < 0 )
               { 
                  //:    dAddToRegAmount = dAddToRegAmount + mSAProf.PaymentPlanLineAssigned.PaidAmount 
                  {MutableDouble md_dTempDecimal_10 = new MutableDouble( dTempDecimal_10 );
                                     GetDecimalFromAttribute( md_dTempDecimal_10, mSAProf, "PaymentPlanLineAssigned", "PaidAmount" );
                  dTempDecimal_10 = md_dTempDecimal_10.doubleValue( );}
                  dAddToRegAmount = dAddToRegAmount + dTempDecimal_10;
                  //:    DELETE ENTITY mSAProf.PaymentPlanLineAssigned NONE 
                  RESULT = DeleteEntity( mSAProf, "PaymentPlanLineAssigned", zREPOS_NONE );
               } 

               //:    END
            } 

            //:    END
            //:    iCount = iCount + 1
            iCount = iCount + 1;
            RESULT = SetCursorNextEntity( mSAProf, "PaymentPlanLineAssigned", "" );
         } 

         //:   END 
         //:   
         //:   IF dAddToRegAmount > 0
         if ( dAddToRegAmount > 0 )
         { 
            //:   // Set to the payment line assigned that is "Due at Registration".
            //:   SET CURSOR FIRST mSAProf.PaymentPlanLineAssigned
            RESULT = SetCursorFirstEntity( mSAProf, "PaymentPlanLineAssigned", "" );
            //:   mSAProf.PaymentPlanLineAssigned.PaidAmount = mSAProf.PaymentPlanLineAssigned.PaidAmount + dAddToRegAmount  
            {MutableDouble md_dTempDecimal_11 = new MutableDouble( dTempDecimal_11 );
                         GetDecimalFromAttribute( md_dTempDecimal_11, mSAProf, "PaymentPlanLineAssigned", "PaidAmount" );
            dTempDecimal_11 = md_dTempDecimal_11.doubleValue( );}
            dTempDecimal_12 = dTempDecimal_11 + dAddToRegAmount;
            SetAttributeFromDecimal( mSAProf, "PaymentPlanLineAssigned", "PaidAmount", dTempDecimal_12 );
            //:   TraceLineS("*** Adding to Due at registration ***", "")
            TraceLineS( "*** Adding to Due at registration ***", "" );
         } 

         //:   END
      } 

      //:  END
   } 

   //:     
   //: END // *** END of checking if mSAProf.PaymentPlan EXISTS.
   //:   
   //:   
   //:   
   //: // **** Recalculate the totals on the payment summary page.

   //: // If the student has selected payment plan I and they do not already have a cash discount transaction.
   //: // put the cash discount in wCashDiscountAmount so that it will be seen from the summary page.
   //: IF mSAProf.PaymentPlan EXISTS AND mSAProf.PaymentPlan.NumberOfPayments = 1 
   lTempInteger_6 = CheckExistenceOfEntity( mSAProf, "PaymentPlan" );
   if ( lTempInteger_6 == 0 && CompareAttributeToInteger( mSAProf, "PaymentPlan", "NumberOfPayments", 1 ) == 0 )
   { 
      //: mSAProf.BillingPeriod.wCashDiscountAmount = lSAPPlanLST.PaymentPlan.wCashDiscountAmount  
      SetAttributeFromAttribute( mSAProf, "BillingPeriod", "wCashDiscountAmount", lSAPPlanLST, "PaymentPlan", "wCashDiscountAmount" );
      //:ELSE
   } 
   else
   { 
      //: mSAProf.BillingPeriod.wCashDiscountAmount = 0
      SetAttributeFromInteger( mSAProf, "BillingPeriod", "wCashDiscountAmount", 0 );
   } 

   //: END
   //:     
   //: //dCurrentBalance = mSAProf.BillingPeriod.wTermBalance + mSAProf.BillingPeriod.dTermCharges + mSAProf.BillingPeriod.wTermCredits 
   //: dTotalAmountOwed = dCurrentBalanceAtTermBeginning + 
   //:                    dPendingChargesForTerm - 
   //:                    dApprovedAidForTerm - 
   //:                    dCurrentAmountPaidForTerm -
   //:                    mSAProf.BillingPeriod.wCashDiscountAmount
   {MutableDouble md_dTempDecimal_13 = new MutableDouble( dTempDecimal_13 );
       GetDecimalFromAttribute( md_dTempDecimal_13, mSAProf, "BillingPeriod", "wCashDiscountAmount" );
   dTempDecimal_13 = md_dTempDecimal_13.doubleValue( );}
   dTotalAmountOwed = dCurrentBalanceAtTermBeginning + dPendingChargesForTerm - dApprovedAidForTerm - dCurrentAmountPaidForTerm - dTempDecimal_13;

   //: // If the student is charging some money to the bookstore, then this amount needs to be added to the total amount due.
   //: IF mSAProf.BillingPeriod.BookStoreAccountChargeAmount > 0
   if ( CompareAttributeToInteger( mSAProf, "BillingPeriod", "BookStoreAccountChargeAmount", 0 ) > 0 )
   { 
      //:  dTotalAmountOwed = dTotalAmountOwed + mSAProf.BillingPeriod.BookStoreAccountChargeAmount  
      {MutableDouble md_dTempDecimal_14 = new MutableDouble( dTempDecimal_14 );
             GetDecimalFromAttribute( md_dTempDecimal_14, mSAProf, "BillingPeriod", "BookStoreAccountChargeAmount" );
      dTempDecimal_14 = md_dTempDecimal_14.doubleValue( );}
      dTotalAmountOwed = dTotalAmountOwed + dTempDecimal_14;
   } 

   //: END
   //: // This is for display on Web interface.
   //: mSAProf.BillingPeriod.wTotalOwedForTerm = dTotalAmountOwed
   SetAttributeFromDecimal( mSAProf, "BillingPeriod", "wTotalOwedForTerm", dTotalAmountOwed );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dMaxPotentialCashDisc( VIEW mSAProf BASED ON LOD mSAProf,
//:                           STRING ( 32 ) InternalEntityStructure,
//:                           STRING ( 32 ) InternalAttribStructure,
//:                           SHORT GetOrSetFlag )

//:   VIEW mSAChrgT BASED ON LOD mSAChrgT
public int 
omSAProf_dMaxPotentialCashDisc( View     mSAProf,
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
   //:STRING ( 25 ) szTemp
   String   szTemp = null;
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
         //:   // Activate the Discount Rate for the Term.
         //:   ACTIVATE mSAChrgT WHERE mSAChrgT.SATransactionCode.TransactionCode = "CASHDISC"
         //:        RESTRICTING mSAChrgT.SATransactionTerm TO mSAChrgT.CollegeTerm.ID = mSAProf.PeriodCollegeTerm.ID 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf, "PeriodCollegeTerm", "ID" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         omSAProf_fnLocalBuildQual_22( mSAProf, vTempViewVar_0, lTempInteger_0 );
         RESULT = ActivateObjectInstance( mSAChrgT, "mSAChrgT", mSAProf, vTempViewVar_0, zSINGLE );
         DropView( vTempViewVar_0 );
         //:   NAME VIEW mSAChrgT "mSAChrgTDiscount"
         SetNameForView( mSAChrgT, "mSAChrgTDiscount", null, zLEVEL_TASK );
         //:   IF mSAChrgT.SATransactionTerm DOES NOT EXIST
         lTempInteger_1 = CheckExistenceOfEntity( mSAChrgT, "SATransactionTerm" );
         if ( lTempInteger_1 != 0 )
         { 
            //:   DropObjectInstance( mSAChrgT )
            DropObjectInstance( mSAChrgT );
            //:   RETURN -1
            if(8==8)return( -1 );
         } 

         //:   END
         //:   dDiscountRate = mSAChrgT.SATransactionTerm.Amount
         {MutableDouble md_dDiscountRate = new MutableDouble( dDiscountRate );
                   GetDecimalFromAttribute( md_dDiscountRate, mSAChrgT, "SATransactionTerm", "Amount" );
         dDiscountRate = md_dDiscountRate.doubleValue( );}
         //:   
         //:   // Activate list of Transactions for the current BillingPeriod that are eligible for a discount.
         //:   ACTIVATE lSATrnSh Multiple WHERE lSATrnSh.BillingPeriod.ID = mSAProf.BillingPeriod.ID 
         //:                                AND lSATrnSh.SATransactionCode.EligibleForCashDiscount = "Y"
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                   GetIntegerFromAttribute( mi_lTempInteger_2, mSAProf, "BillingPeriod", "ID" );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );}
         omSAProf_fnLocalBuildQual_23( mSAProf, vTempViewVar_1, lTempInteger_2 );
         RESULT = ActivateObjectInstance( lSATrnSh, "lSATrnSh", mSAProf, vTempViewVar_1, zMULTIPLE );
         DropView( vTempViewVar_1 );
         //:   NAME VIEW lSATrnSh "lSATrnSh"
         SetNameForView( lSATrnSh, "lSATrnSh", null, zLEVEL_TASK );
         //:   dTotalEligibleCharges = 0
         dTotalEligibleCharges = 0;
         //:   FOR EACH lSATrnSh.StudentAccountTransApplied 
         RESULT = SetCursorFirstEntity( lSATrnSh, "StudentAccountTransApplied", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:   dTotalEligibleCharges = dTotalEligibleCharges + lSATrnSh.StudentAccountTransApplied.Amount 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, lSATrnSh, "StudentAccountTransApplied", "Amount" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dTotalEligibleCharges = dTotalEligibleCharges + dTempDecimal_0;
            RESULT = SetCursorNextEntity( lSATrnSh, "StudentAccountTransApplied", "" );
         } 

         //:   END

         //:               
         //:   // Cash Discount is (Total Eligible Transactions - Approved Aid) * .03
         //:   dCashDiscount = ( dTotalEligibleCharges - mSAProf.BillingPeriod.dRegistrationDisbAmountApproved ) * dDiscountRate
         {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                   GetDecimalFromAttribute( md_dTempDecimal_1, mSAProf, "BillingPeriod", "dRegistrationDisbAmountApproved" );
         dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
         dCashDiscount = ( dTotalEligibleCharges - dTempDecimal_1 ) * dDiscountRate;
         //:   IF dCashDiscount < 0
         if ( dCashDiscount < 0 )
         { 
            //:   dCashDiscount = 0
            dCashDiscount = 0;
         } 

         //:   END
         //:                           
         //:   DropObjectInstance( lSATrnSh )
         DropObjectInstance( lSATrnSh );
         //:   DropObjectInstance( mSAChrgT )
         DropObjectInstance( mSAChrgT );

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



}
