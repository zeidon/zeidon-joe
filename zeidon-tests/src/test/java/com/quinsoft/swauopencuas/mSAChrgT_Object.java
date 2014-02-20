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

/**
   @author QuinSoft
**/

public class mSAChrgT_Object extends VmlObjectOperations
{
   public mSAChrgT_Object( View view )
   {
      super( view );
   }


//:OBJECT CONSTRAINT OPERATION
public int 
omSAChrgT_ObjectConstraint( View     mSAChrgT,
                            Integer   Event,
                            Integer   State )
{
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   //:ObjectConstraint( VIEW mSAChrgT BASED ON LOD mSAChrgT,
   //:               SHORT Event,
   //:               SHORT State )

   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:// Make sure that any Variable Name changes from InternalRuleVariableDef are updated.
         //:// What we will do is delete any SAInternalRuleVariable entries that don't have a matching
         //:// InternalRuleVariableDef entry and then add any new SAInternalRuleVariable entries.
         //:FOR EACH mSAChrgT.SATransactionTerm 
         RESULT = SetCursorFirstEntity( mSAChrgT, "SATransactionTerm", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:FOR EACH mSAChrgT.SAInternalRuleVariable 
            RESULT = SetCursorFirstEntity( mSAChrgT, "SAInternalRuleVariable", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:SET CURSOR FIRST mSAChrgT.InternalRuleVariableDef WHERE mSAChrgT.InternalRuleVariableDef.Name = mSAChrgT.SAInternalRuleVariable.Name 
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, mSAChrgT, "SAInternalRuleVariable", "Name" );
               szTempString_0 = sb_szTempString_0.toString( );}
               RESULT = SetCursorFirstEntityByString( mSAChrgT, "InternalRuleVariableDef", "Name", szTempString_0, "" );
               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:DELETE ENTITY mSAChrgT.SAInternalRuleVariable NONE 
                  RESULT = DeleteEntity( mSAChrgT, "SAInternalRuleVariable", zREPOS_NONE );
               } 

               RESULT = SetCursorNextEntity( mSAChrgT, "SAInternalRuleVariable", "" );
               //:END
            } 

            //:END
            //:FOR EACH mSAChrgT.InternalRuleVariableDef 
            RESULT = SetCursorFirstEntity( mSAChrgT, "InternalRuleVariableDef", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = mSAChrgT.InternalRuleVariableDef.Name 
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, mSAChrgT, "InternalRuleVariableDef", "Name" );
               szTempString_0 = sb_szTempString_0.toString( );}
               RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", szTempString_0, "" );
               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:CREATE ENTITY mSAChrgT.SAInternalRuleVariable 
                  RESULT = CreateEntity( mSAChrgT, "SAInternalRuleVariable", zPOS_AFTER );
                  //:mSAChrgT.SAInternalRuleVariable.Name = mSAChrgT.InternalRuleVariableDef.Name  
                  SetAttributeFromAttribute( mSAChrgT, "SAInternalRuleVariable", "Name", mSAChrgT, "InternalRuleVariableDef", "Name" );
               } 

               RESULT = SetCursorNextEntity( mSAChrgT, "InternalRuleVariableDef", "" );
               //:END 
            } 

            RESULT = SetCursorNextEntity( mSAChrgT, "SATransactionTerm", "" );
            //:END
         } 

         //:END

         //:// If the object has mSAChrgT.InternalRuleVariableDef entities, make sure the corresponding
         //:// entities exist under mSAChrgT.SATransactionTerm.
         //:IF mSAChrgT.InternalRuleVariableDef EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSAChrgT, "InternalRuleVariableDef" );
         if ( lTempInteger_0 == 0 )
         { 
            //:FOR EACH mSAChrgT.SATransactionTerm 
            RESULT = SetCursorFirstEntity( mSAChrgT, "SATransactionTerm", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF mSAChrgT.SAInternalRuleVariable DOES NOT EXIST
               lTempInteger_1 = CheckExistenceOfEntity( mSAChrgT, "SAInternalRuleVariable" );
               if ( lTempInteger_1 != 0 )
               { 
                  //:FOR EACH mSAChrgT.InternalRuleVariableDef 
                  RESULT = SetCursorFirstEntity( mSAChrgT, "InternalRuleVariableDef", "" );
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //:CREATE ENTITY mSAChrgT.SAInternalRuleVariable 
                     RESULT = CreateEntity( mSAChrgT, "SAInternalRuleVariable", zPOS_AFTER );
                     //:mSAChrgT.SAInternalRuleVariable.Name = mSAChrgT.InternalRuleVariableDef.Name  
                     SetAttributeFromAttribute( mSAChrgT, "SAInternalRuleVariable", "Name", mSAChrgT, "InternalRuleVariableDef", "Name" );
                     RESULT = SetCursorNextEntity( mSAChrgT, "InternalRuleVariableDef", "" );
                  } 

                  //:END
               } 

               RESULT = SetCursorNextEntity( mSAChrgT, "SATransactionTerm", "" );
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


private int 
omSAChrgT_fnLocalBuildQual_0( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Prospect" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Category" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Category" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "PBS Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAChrgT_fnLocalBuildQual_1( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Prospect" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Category" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Category" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "PBS Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:TRANSFORMATION OPERATION
public int 
omSAChrgT_CreateInternalRule( zVIEW    mSAChrgT,
                              View     mSAChrgI,
                              String   szInternalRuleName )
{
   int      RESULT = 0;

   //:CreateInternalRule( VIEW mSAChrgT BASED ON LOD mSAChrgT,
   //:                 VIEW mSAChrgI BASED ON LOD mSAChrgI,
   //:                 STRING (50) szInternalRuleName )

   //:// Create the single Internal Rule entry.
   //:CREATE ENTITY mSAChrgI.InternalRule  
   RESULT = CreateEntity( mSAChrgI, "InternalRule", zPOS_AFTER );
   //:mSAChrgI.InternalRule.Name = szInternalRuleName
   SetAttributeFromString( mSAChrgI, "InternalRule", "Name", szInternalRuleName );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ProcessInternalRule( VIEW mSAChrgT BASED ON LOD mSAChrgT,
//:                     VIEW mSAProf  BASED ON LOD mSAProf,
//:                     STRING ( 1 )   szRefundFlag )

//:   VIEW mSAProf2    BASED ON LOD  mSAProf
public int 
omSAChrgT_ProcessInternalRule( View     mSAChrgT,
                               View     mSAProf,
                               String   szRefundFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:VIEW lSATermLST  REGISTERED AS lSATermLST
   zVIEW    lSATermLST = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lSATermLST2 BASED ON LOD  lTermLST
   zVIEW    lSATermLST2 = new zVIEW( );
   //:VIEW wXferO      REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   //:VIEW mCollegeLST BASED ON LOD  mCollege
   zVIEW    mCollegeLST = new zVIEW( );
   //:VIEW mSAChrgT2   BASED ON LOD  mSAChrgT
   zVIEW    mSAChrgT2 = new zVIEW( );
   //:STRING ( 1 )   szFoundRuleFlag
   String   szFoundRuleFlag = null;
   //:STRING ( 1 )   szExemptionFlag
   String   szExemptionFlag = null;
   //:STRING ( 300 ) szMsg
   String   szMsg = null;
   //:STRING ( 100 ) szDesc
   String   szDesc = null;
   //:STRING ( 100 ) szCourseList
   String   szCourseList = null;
   //:STRING ( 20 )  szDisplayCredits
   String   szDisplayCredits = null;
   //:STRING ( 20 )  szDisplayNumberOfClasses
   String   szDisplayNumberOfClasses = null;
   //:STRING ( 50 )  szInternalRuleName
   String   szInternalRuleName = null;
   //:STRING ( 1 )   szStudentHasVehicleRegCharge
   String   szStudentHasVehicleRegCharge = null;
   //:STRING ( 4 )   szSelectedYear
   String   szSelectedYear = null;
   //:STRING ( 8 )   szSelectedYearDate
   String   szSelectedYearDate = null;
   //:STRING ( 8 )   szBeginYearDate
   String   szBeginYearDate = null;
   //:STRING ( 8 )   szEndYearDate
   String   szEndYearDate = null;
   //:STRING ( 8 )   szAddDropDeadlineDate
   String   szAddDropDeadlineDate = null;
   //:STRING ( 8 )   szWorkDate
   String   szWorkDate = null;
   //:STRING ( 8 )   szFirstClassDate
   String   szFirstClassDate = null;
   //:STRING ( 8 )   szInsuranceBenefitDate
   String   szInsuranceBenefitDate = null;
   //:STRING ( 8 )   szStartOfNextMonthDate
   String   szStartOfNextMonthDate = null;
   //:STRING ( 2 )   szMonth
   String   szMonth = null;
   //:STRING ( 1 )   szDroppedAllClassesFlag
   String   szDroppedAllClassesFlag = null;
   //:STRING ( 1 )   szResidencyStatus
   String   szResidencyStatus = null;
   //:STRING ( 10 )  szTransactionCode
   String   szTransactionCode = null;
   //:STRING ( 10 )  szFallHealthInsuranceTranCode
   String   szFallHealthInsuranceTranCode = null;
   //:STRING ( 10 )  szValidHealthInsuranceTranCode
   String   szValidHealthInsuranceTranCode = null;
   //:STRING ( 20 )  szVariableName
   String   szVariableName = null;
   //:INTEGER        DropCount
   int      DropCount = 0;
   //:INTEGER        TotalClasses
   int      TotalClasses = 0;
   //:INTEGER        Month
   int      Month = 0;
   //:DECIMAL        dChargePerCreditHour
   double  dChargePerCreditHour = 0.0;
   //:DECIMAL        dOverloadCredits
   double  dOverloadCredits = 0.0;
   //:DECIMAL        dTotalCredits
   double  dTotalCredits = 0.0;
   //:DECIMAL        dTotalAuditedCredits
   double  dTotalAuditedCredits = 0.0;
   //:DECIMAL        dChargePerClass
   double  dChargePerClass = 0.0;
   //:DECIMAL        dAmount
   double  dAmount = 0.0;
   //:DECIMAL        dAmountAudit
   double  dAmountAudit = 0.0;
   //:DECIMAL        dRefund
   double  dRefund = 0.0;
   //:DECIMAL        dMaximumCredits
   double  dMaximumCredits = 0.0;
   //:DECIMAL        dTotal
   double  dTotal = 0.0;
   //:SHORT          nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   double  dTempDecimal_2 = 0.0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   int      lTempInteger_13 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_14 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_15 = 0;

   RESULT = GetViewByName( lSATermLST, "lSATermLST", mSAChrgT, zLEVEL_TASK );
   RESULT = GetViewByName( wXferO, "wXferO", mSAChrgT, zLEVEL_TASK );

   //:// Process the Internal Rule specified in mSAChrgT.SATransactionCode.InternalRuleName.

   //:// NOTE THAT THERE ARE THREE STEPS TO ADDING A NEW INTERNAL RULE.
   //:// 1. ADD THE RULE NAME IN BuildInternalRuleList ABOVE SO THAT THE RULE WILL APPEAR IN THE DROP DOWN LIST.
   //:// 2. ADD THE PROCESSING OF THE RULE IN THIS OPERATION.
   //:// 3. IF THE RULE HAS VARIABLES, ADD THE CREATION OF THOSE VARIABLES IN BuildInternalRuleVars BELOW.

   //:// The following Internal Rules are processed here. Note that they need to be added in operation, BuildInternalRuleList, above.
   //:// 1. Graduate Charge/Credit
   //:// 2. Undergrad Tuition Block Charge
   //:// 3. Health Insurance
   //:// 4. Summer Tuition
   //:// 5. DL Tuition
   //:// 6. Meal Plan
   //:// 7. Residency
   //:// 8. UNIV110 Fees
   //:// 9. Independent Study

   //:// Make sure list of colleges exists for determining grad/undergrad courses.
   //:GET VIEW mCollegeLST NAMED "mCollegeLST"
   RESULT = GetViewByName( mCollegeLST, "mCollegeLST", mSAChrgT, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mCollegeLST RootOnlyMultiple
      RESULT = ActivateObjectInstance( mCollegeLST, "mCollege", mSAChrgT, 0, zACTIVATE_ROOTONLY_MULTIPLE );
      //:NAME VIEW mCollegeLST "mCollegeLST"
      SetNameForView( mCollegeLST, "mCollegeLST", null, zLEVEL_TASK );
   } 

   //:END

   //:// If this Transaction is for a Term, make sure that the Minimum Credits attributes have been set for the Term. (Except for Jan.)
   //:IF mSAChrgT.CollegeTerm EXISTS 
   lTempInteger_0 = CheckExistenceOfEntity( mSAChrgT, "CollegeTerm" );
   if ( lTempInteger_0 == 0 )
   { 
      //:IF mSAChrgT.CollegeTerm.Semester = "01" OR mSAChrgT.CollegeTerm.Semester = "03" OR mSAChrgT.CollegeTerm.Semester = "04"
      if ( CompareAttributeToString( mSAChrgT, "CollegeTerm", "Semester", "01" ) == 0 || CompareAttributeToString( mSAChrgT, "CollegeTerm", "Semester", "03" ) == 0 || CompareAttributeToString( mSAChrgT, "CollegeTerm", "Semester", "04" ) == 0 )
      { 
         //:IF mSAChrgT.CollegeTerm.FullTimeMinCredits = "" OR mSAChrgT.CollegeTerm.OverloadMinCredits = "" OR 
         //:   mSAChrgT.CollegeTerm.PartTimeMinCredits = "" OR mSAChrgT.CollegeTerm.HalfTimeMinCredits = ""
         if ( CompareAttributeToString( mSAChrgT, "CollegeTerm", "FullTimeMinCredits", "" ) == 0 || CompareAttributeToString( mSAChrgT, "CollegeTerm", "OverloadMinCredits", "" ) == 0 ||
              CompareAttributeToString( mSAChrgT, "CollegeTerm", "PartTimeMinCredits", "" ) == 0 || CompareAttributeToString( mSAChrgT, "CollegeTerm", "HalfTimeMinCredits", "" ) == 0 )
         { 

            //:szMsg = "Minimum Credits for Term ," + mSAChrgT.CollegeTerm.YearSemester + ", have not all been specified." + NEW_LINE + 
            //:        "The 'Generate Charges' process is being aborted."
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 21, mSAChrgT, "CollegeTerm", "YearSemester", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringCopy( sb_szMsg, 1, 0, "Minimum Credits for Term ,", 1, 0, 301 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_0, 1, 0, 301 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, ", have not all been specified.", 1, 0, 301 );
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
                        ZeidonStringConcat( sb_szMsg, 1, 0, "The 'Generate Charges' process is being aborted.", 1, 0, 301 );
            szMsg = sb_szMsg.toString( );}
            //:MessageSend( mSAChrgT, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mSAChrgT, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:szInternalRuleName = mSAChrgT.SATransactionCode.InternalRuleName
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szInternalRuleName;
   if ( szInternalRuleName == null )
      sb_szInternalRuleName = new StringBuilder( 32 );
   else
      sb_szInternalRuleName = new StringBuilder( szInternalRuleName );
       GetVariableFromAttribute( sb_szInternalRuleName, mi_lTempInteger_2, 'S', 51, mSAChrgT, "SATransactionCode", "InternalRuleName", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szInternalRuleName = sb_szInternalRuleName.toString( );}
   //:szTransactionCode  = mSAChrgT.SATransactionCode.TransactionCode
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szTransactionCode;
   if ( szTransactionCode == null )
      sb_szTransactionCode = new StringBuilder( 32 );
   else
      sb_szTransactionCode = new StringBuilder( szTransactionCode );
       GetVariableFromAttribute( sb_szTransactionCode, mi_lTempInteger_3, 'S', 11, mSAChrgT, "SATransactionCode", "TransactionCode", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szTransactionCode = sb_szTransactionCode.toString( );}

   //:IF szInternalRuleName = ""
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "", 1, 0, 51 ) == 0 )
   { 
      //:szMsg = "The Internal Rule Name for Transaction '" + mSAChrgT.SATransactionCode.Description + "' is null."
      {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
      StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
             GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_4, 'S', 255, mSAChrgT, "SATransactionCode", "Description", "", 0 );
      lTempInteger_4 = mi_lTempInteger_4.intValue( );
      szTempString_1 = sb_szTempString_1.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringCopy( sb_szMsg, 1, 0, "The Internal Rule Name for Transaction '", 1, 0, 301 );
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
            ZeidonStringConcat( sb_szMsg, 1, 0, "' is null.", 1, 0, 301 );
      szMsg = sb_szMsg.toString( );}
      //:MessageSend( mSAChrgT, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mSAChrgT, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Determine if all Classes have been dropped before the first day of class and set a flag if they have.
   //:// This is used below to give a full refund when a student drops classes.
   //:// We will initialize the flag as set and then reset it if any enrollment entry has status enrolled or completed or
   //:// is dropped with a dropped date after the first day of classes.
   //:szDroppedAllClassesFlag = "Y"
    {StringBuilder sb_szDroppedAllClassesFlag;
   if ( szDroppedAllClassesFlag == null )
      sb_szDroppedAllClassesFlag = new StringBuilder( 32 );
   else
      sb_szDroppedAllClassesFlag = new StringBuilder( szDroppedAllClassesFlag );
      ZeidonStringCopy( sb_szDroppedAllClassesFlag, 1, 0, "Y", 1, 0, 2 );
   szDroppedAllClassesFlag = sb_szDroppedAllClassesFlag.toString( );}
   //:szFirstClassDate = mSAProf.PeriodCollegeTerm.CourseStartDate
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
   StringBuilder sb_szFirstClassDate;
   if ( szFirstClassDate == null )
      sb_szFirstClassDate = new StringBuilder( 32 );
   else
      sb_szFirstClassDate = new StringBuilder( szFirstClassDate );
       GetVariableFromAttribute( sb_szFirstClassDate, mi_lTempInteger_5, 'S', 9, mSAProf, "PeriodCollegeTerm", "CourseStartDate", "", 0 );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );
   szFirstClassDate = sb_szFirstClassDate.toString( );}
   //:FOR EACH mSAProf.TermEnrolled 
   RESULT = SetCursorFirstEntity( mSAProf, "TermEnrolled", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSAProf.TermEnrolled.Status = "T" OR 
      //:   mSAProf.TermEnrolled.Status = "C" OR 
      //:   mSAProf.TermEnrolled.Status = "W" OR
      //:   ( mSAProf.TermEnrolled.Status = "D" AND mSAProf.TermEnrolled.DroppedDate > szFirstClassDate )
      if ( CompareAttributeToString( mSAProf, "TermEnrolled", "Status", "T" ) == 0 || CompareAttributeToString( mSAProf, "TermEnrolled", "Status", "C" ) == 0 || CompareAttributeToString( mSAProf, "TermEnrolled", "Status", "W" ) == 0 ||
           ( CompareAttributeToString( mSAProf, "TermEnrolled", "Status", "D" ) == 0 && CompareAttributeToString( mSAProf, "TermEnrolled", "DroppedDate", szFirstClassDate ) > 0 ) )
      { 

         //:szDroppedAllClassesFlag = ""
          {StringBuilder sb_szDroppedAllClassesFlag;
         if ( szDroppedAllClassesFlag == null )
            sb_szDroppedAllClassesFlag = new StringBuilder( 32 );
         else
            sb_szDroppedAllClassesFlag = new StringBuilder( szDroppedAllClassesFlag );
                  ZeidonStringCopy( sb_szDroppedAllClassesFlag, 1, 0, "", 1, 0, 2 );
         szDroppedAllClassesFlag = sb_szDroppedAllClassesFlag.toString( );}
      } 

      RESULT = SetCursorNextEntity( mSAProf, "TermEnrolled", "" );
      //:END
   } 

   //:END



   //:// GRADUATE CHARGE/CREDIT

   //:// The Student is simply charged an amount per credit hour.

   //:// Student is simply charged per credit regardless of Undergrad/Grad status.

   //:IF szInternalRuleName = "Graduate Charge/Credit"
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "Graduate Charge/Credit", 1, 0, 51 ) == 0 )
   { 

      //:// We skip if the student is Distance Learning, as charges there are computed separately.
      //://SET CURSOR FIRST  mSAProf.Category WHERE mSAProf.Category.Name = "Distance Learning" 
      //://IF RESULT >= zCURSOR_SET
      //:IF mSAProf.Student.AdultStudiesFlag = "Y"
      if ( CompareAttributeToString( mSAProf, "Student", "AdultStudiesFlag", "Y" ) == 0 )
      { 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END

      //:// Make sure they are not both medtech and grad
      //:SET CURSOR FIRST  mSAProf.Category WHERE mSAProf.Category.Name = "Clinical Lab Science" 
      RESULT = SetCursorFirstEntityByString( mSAProf, "Category", "Name", "Clinical Lab Science", "" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END

      //:szFoundRuleFlag = "Y"
       {StringBuilder sb_szFoundRuleFlag;
      if ( szFoundRuleFlag == null )
         sb_szFoundRuleFlag = new StringBuilder( 32 );
      else
         sb_szFoundRuleFlag = new StringBuilder( szFoundRuleFlag );
            ZeidonStringCopy( sb_szFoundRuleFlag, 1, 0, "Y", 1, 0, 2 );
      szFoundRuleFlag = sb_szFoundRuleFlag.toString( );}

      //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable 
      //:           WHERE mSAChrgT.SAInternalRuleVariable.Name = "Charge/Credit"
      RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "Charge/Credit", "" );
      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:szMsg = "Missing variable name, 'Charge/Credit', for Internal Rule," + szInternalRuleName + "'."
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "Missing variable name, 'Charge/Credit', for Internal Rule,", 1, 0, 301 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szInternalRuleName, 1, 0, 301 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, "'.", 1, 0, 301 );
         szMsg = sb_szMsg.toString( );}
         //:MessageSend( mSAChrgT, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mSAChrgT, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END

      //:// Compute total Grad and Audit credits.
      //:dTotalCredits        = 0
      dTotalCredits = 0;
      //:dTotalAuditedCredits = 0
      dTotalAuditedCredits = 0;
      //:FOR EACH mSAProf.TermEnrolled 
      RESULT = SetCursorFirstEntity( mSAProf, "TermEnrolled", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 

         //:// Only add up enrolled or completed classes that are not Adult Studies (which is Distance Learning).
         //:IF (mSAProf.TermEnrolled.Status ="T" OR mSAProf.TermEnrolled.Status ="C") AND mSAProf.TermClass.AdultStudiesFlag != "Y"
         if ( ( CompareAttributeToString( mSAProf, "TermEnrolled", "Status", "T" ) == 0 || CompareAttributeToString( mSAProf, "TermEnrolled", "Status", "C" ) == 0 ) && CompareAttributeToString( mSAProf, "TermClass", "AdultStudiesFlag", "Y" ) != 0 )
         { 
            //:// Regular Grad Credits.
            //:SET CURSOR FIRST mCollegeLST.College WHERE mCollegeLST.College.ID = mSAProf.TermCourseCollege.ID 
            {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                         GetIntegerFromAttribute( mi_lTempInteger_6, mSAProf, "TermCourseCollege", "ID" );
            lTempInteger_6 = mi_lTempInteger_6.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mCollegeLST, "College", "ID", lTempInteger_6, "" );
            //:IF mCollegeLST.College.Type = "G"
            if ( CompareAttributeToString( mCollegeLST, "College", "Type", "G" ) == 0 )
            { 
               //:dTotalCredits = dTotalCredits + mSAProf.TermEnrolled.CreditHours
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf, "TermEnrolled", "CreditHours" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dTotalCredits = dTotalCredits + dTempDecimal_0;
            } 

            //:END

            //:// Audit Credits.
            //:IF mSAProf.TermEnrolled.TakingClassType = "A" AND mCollegeLST.College.Type = "G"
            if ( CompareAttributeToString( mSAProf, "TermEnrolled", "TakingClassType", "A" ) == 0 && CompareAttributeToString( mCollegeLST, "College", "Type", "G" ) == 0 )
            { 
               //://dTotalAuditedCredits = dTotalAuditedCredits + mSAProf.TermEnrolled.CreditHours
               //:dTotalAuditedCredits = dTotalAuditedCredits + mSAProf.Class.CreditHours
               {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                               GetDecimalFromAttribute( md_dTempDecimal_1, mSAProf, "Class", "CreditHours" );
               dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
               dTotalAuditedCredits = dTotalAuditedCredits + dTempDecimal_1;
            } 

            //:END
         } 

         RESULT = SetCursorNextEntity( mSAProf, "TermEnrolled", "" );
         //:END
      } 

      //:   
      //:END

      //:IF szRefundFlag = ""
      if ( ZeidonStringCompare( szRefundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
      { 
         //:// Only process for Graduate Student that is taking credits.
         //:// dTotalCredits = mSAProf.PeriodCollegeTerm.dCreditsEnrolledCompleted

         //://IF mSAProf.Student.CurrentLevel = "6"  AND     // 6 is Graduate Student.
         //://   dTotalCredits > 0
         //:IF dTotalCredits > 0
         if ( dTotalCredits > 0 )
         { 

            //:// Regular Charge, not a refund.
            //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value 
            {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                         GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
            dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
            //:dAmount = dTotalCredits * dChargePerCreditHour
            dAmount = dTotalCredits * dChargePerCreditHour;
            //:dAmountAudit = dTotalAuditedCredits * ( dChargePerCreditHour / 2 )
            dAmountAudit = dTotalAuditedCredits * ( dChargePerCreditHour / 2 );
            //:dAmount = dAmount + dAmountAudit 
            dAmount = dAmount + dAmountAudit;

            //:// Generate the charge if it doesn't already exist, which is determined inside operation GenerateChargeEntry.
            //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, "" )
            nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, "" );
            //:IF nRC >= 0
            if ( nRC >= 0 )
            { 
               //:// Add number of overload credits to description.
               //:szDisplayCredits = dTotalCredits
                {StringBuilder sb_szDisplayCredits;
               if ( szDisplayCredits == null )
                  sb_szDisplayCredits = new StringBuilder( 32 );
               else
                  sb_szDisplayCredits = new StringBuilder( szDisplayCredits );
                              ZeidonStringConvertFromNumber( sb_szDisplayCredits, 1, 0, 20, 0, dTotalCredits, "D" );
               szDisplayCredits = sb_szDisplayCredits.toString( );}
               //:szDesc = mSAProf.StudentAccountTransApplied.Description + " - " + szDisplayCredits + " CR"
               {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                               GetStringFromAttribute( sb_szDesc, mSAProf, "StudentAccountTransApplied", "Description" );
               szDesc = sb_szDesc.toString( );}
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringConcat( sb_szDesc, 1, 0, " - ", 1, 0, 101 );
               szDesc = sb_szDesc.toString( );}
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringConcat( sb_szDesc, 1, 0, szDisplayCredits, 1, 0, 101 );
               szDesc = sb_szDesc.toString( );}
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringConcat( sb_szDesc, 1, 0, " CR", 1, 0, 101 );
               szDesc = sb_szDesc.toString( );}
               //:mSAProf.StudentAccountTransApplied.Description = szDesc
               SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szDesc );
               //:RETURN 0 
               if(8==8)return( 0 );
            } 

            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Refund.
         //:// Note that we will process a Refund if the Amount has changed and the latest Dropped Date for a Class is not later
         //:// than the AddDropDeadlineDate for the Term. The new Amount will be added back in GenerateCharges.
         //:// Also note that we are currently positioned on the PeriodTransApplied entry to be reversed.
         //:dTotalCredits = mSAProf.PeriodCollegeTerm.dCreditsEnrolledCompleted
         {MutableDouble md_dTotalCredits = new MutableDouble( dTotalCredits );
                   GetDecimalFromAttribute( md_dTotalCredits, mSAProf, "PeriodCollegeTerm", "dCreditsEnrolledCompleted" );
         dTotalCredits = md_dTotalCredits.doubleValue( );}
         //:IF dTotalCredits < 0
         if ( dTotalCredits < 0 )
         { 
            //:dTotalCredits = 0
            dTotalCredits = 0;
         } 

         //:END
         //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value 
         {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                   GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
         dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
         //:dAmount = dTotalCredits * dChargePerCreditHour
         dAmount = dTotalCredits * dChargePerCreditHour;
         //:IF dAmount != mSAProf.PeriodTransApplied.Amount
         if ( CompareAttributeToDecimal( mSAProf, "PeriodTransApplied", "Amount", dAmount ) != 0 )
         { 
            //:// Go to create refund or give User and error message, if any classes were dropped after Add/Drop deadline.
            //:CreateRefundByDropDate( mSAChrgT, mSAProf )
            omSAChrgT_CreateRefundByDropDate( mSAChrgT, mSAProf );
            //:RETURN 0 
            if(8==8)return( 0 );
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:// UNDERGRAD TUITION CHARGE CREDIT    
   //:IF szInternalRuleName = "Undergrad Tuition Block Charge"
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "Undergrad Tuition Block Charge", 1, 0, 51 ) == 0 )
   { 
      //:szFoundRuleFlag = "Y"
       {StringBuilder sb_szFoundRuleFlag;
      if ( szFoundRuleFlag == null )
         sb_szFoundRuleFlag = new StringBuilder( 32 );
      else
         sb_szFoundRuleFlag = new StringBuilder( szFoundRuleFlag );
            ZeidonStringCopy( sb_szFoundRuleFlag, 1, 0, "Y", 1, 0, 2 );
      szFoundRuleFlag = sb_szFoundRuleFlag.toString( );}
      //:nRC = Rule_UnderGradTuition( mSAChrgT, mSAProf, szRefundFlag )
      nRC = omSAChrgT_Rule_UnderGradTuition( mSAChrgT, mSAProf, szRefundFlag );
      //:RETURN nRC 
      if(8==8)return( nRC );
   } 

   //:END    



   //:// HEALTH INSURANCE

   //:// Student is charged once per school year according to rules below.

   //:IF szInternalRuleName = "Health Insurance"
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "Health Insurance", 1, 0, 51 ) == 0 )
   { 

      //:szFoundRuleFlag = "Y"
       {StringBuilder sb_szFoundRuleFlag;
      if ( szFoundRuleFlag == null )
         sb_szFoundRuleFlag = new StringBuilder( 32 );
      else
         sb_szFoundRuleFlag = new StringBuilder( szFoundRuleFlag );
            ZeidonStringCopy( sb_szFoundRuleFlag, 1, 0, "Y", 1, 0, 2 );
      szFoundRuleFlag = sb_szFoundRuleFlag.toString( );}

      //:// This rule is ignored for Distance Learning Students.
      //://SET CURSOR FIRST mSAProf.Category WHERE mSAProf.Category.Name = "Distance Learning"
      //://                                     OR mSAProf.Category.Name = "ADP Student"
      //://IF RESULT >= zCURSOR_SET
      //:IF mSAProf.Student.AdultStudiesFlag = "Y"
      if ( CompareAttributeToString( mSAProf, "Student", "AdultStudiesFlag", "Y" ) == 0 )
      { 
         //:RETURN 
         if(8==8)return( 0 );
      } 

      //:END

      //:// A single Fee is charged per College Year. *** Overridden as explained below.
      //:// There is no charge if the student is taking less than 6 credits.
      //:// For Fall, it is always charged when student has 6 credits or more.
      //:// For Spring, it is charged only if it wasn't charged in the Fall of the same College Year.
      //:// If the student is international (VisaClassification is "F-1"), the "Foreign Student Insurance" transaction, "F1INSURA",
      //:// is used for generating the charge.
      //:// Otherwise, the student is domestic and the "Student Insurance" transaction, "INSURANC", is used (which is the transaction
      //:// triggering this rule).

      //:// On 6/27/2012, the above rule was changed so that the charge was made every Term, instead of once per year.

      //:// Because we may generate the charge to a different TransactionCode, we will create a new view to hold the charge TransactionCode.
      //:CreateViewFromView( mSAChrgT2, mSAChrgT )
      CreateViewFromView( mSAChrgT2, mSAChrgT );
      //:NAME VIEW mSAChrgT2 "mSAChrgT2"
      SetNameForView( mSAChrgT2, "mSAChrgT2", null, zLEVEL_TASK );

      //:// First determine the charge.
      //:dAmount = 0
      dAmount = 0;
      //:IF mSAProf.Student.Status = "N" OR szDroppedAllClassesFlag = "Y"
      if ( CompareAttributeToString( mSAProf, "Student", "Status", "N" ) == 0 || ZeidonStringCompare( szDroppedAllClassesFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:// For NoShow Status or if all classes were dropped before start of classes, there is no charge.

         //:ELSE
      } 
      else
      { 

         //:// If the Student has Medical Exemption set, there also is no charge.
         //:szExemptionFlag = ""
          {StringBuilder sb_szExemptionFlag;
         if ( szExemptionFlag == null )
            sb_szExemptionFlag = new StringBuilder( 32 );
         else
            sb_szExemptionFlag = new StringBuilder( szExemptionFlag );
                  ZeidonStringCopy( sb_szExemptionFlag, 1, 0, "", 1, 0, 2 );
         szExemptionFlag = sb_szExemptionFlag.toString( );}
         //:IF mSAProf.StudentHealth EXISTS
         lTempInteger_7 = CheckExistenceOfEntity( mSAProf, "StudentHealth" );
         if ( lTempInteger_7 == 0 )
         { 
            //:// The following IF was changed on 8/20/2012 by DonC per email request from Chas on 8/18 at 5:33PM.
            //://IF mSAProf.StudentHealth.MedicalExemptionFlag = "Y"
            //:IF mSAProf.StudentHealth.RelgiousExemptionFlag = "Y"
            if ( CompareAttributeToString( mSAProf, "StudentHealth", "RelgiousExemptionFlag", "Y" ) == 0 )
            { 
               //:szExemptionFlag = "Y"
                {StringBuilder sb_szExemptionFlag;
               if ( szExemptionFlag == null )
                  sb_szExemptionFlag = new StringBuilder( 32 );
               else
                  sb_szExemptionFlag = new StringBuilder( szExemptionFlag );
                              ZeidonStringCopy( sb_szExemptionFlag, 1, 0, "Y", 1, 0, 2 );
               szExemptionFlag = sb_szExemptionFlag.toString( );}
            } 

            //:END
         } 

         //:END
         //:IF szExemptionFlag = "Y"
         if ( ZeidonStringCompare( szExemptionFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
         { 
            //:// Religious Exemption is set.

            //:ELSE
         } 
         else
         { 

            //:dTotalCredits = mSAProf.PeriodCollegeTerm.dCreditsEnrolledCompleted
            {MutableDouble md_dTotalCredits = new MutableDouble( dTotalCredits );
                         GetDecimalFromAttribute( md_dTotalCredits, mSAProf, "PeriodCollegeTerm", "dCreditsEnrolledCompleted" );
            dTotalCredits = md_dTotalCredits.doubleValue( );}
            //:IF mSAProf.Demographics.VisaClassification = "F-1"
            if ( CompareAttributeToString( mSAProf, "Demographics", "VisaClassification", "F-1" ) == 0 )
            { 
               //:dMaximumCredits = 1
               dMaximumCredits = 1;
               //:ELSE
            } 
            else
            { 
               //:dMaximumCredits = 6
               dMaximumCredits = 6;
            } 

            //:END

            //:IF dTotalCredits >= dMaximumCredits AND
            //:   mSAProf.StudentHealth.InternalInsuranceCompanyFlag = "Y" AND 
            //:   ( mSAProf.PeriodCollegeTerm.Semester = "01" OR mSAProf.PeriodCollegeTerm.Semester = "03" )
            if ( dTotalCredits >= dMaximumCredits && CompareAttributeToString( mSAProf, "StudentHealth", "InternalInsuranceCompanyFlag", "Y" ) == 0 && ( CompareAttributeToString( mSAProf, "PeriodCollegeTerm", "Semester", "01" ) == 0 ||
                 CompareAttributeToString( mSAProf, "PeriodCollegeTerm", "Semester", "03" ) == 0 ) )
            { 

               //:// If the Student is International, position on that SA Transaction. Otherwise position on the transaction
               //:// triggering this rule.
               //:IF mSAProf.Demographics.VisaClassification = "F-1"
               if ( CompareAttributeToString( mSAProf, "Demographics", "VisaClassification", "F-1" ) == 0 )
               { 
                  //:SET CURSOR FIRST mSAChrgT2.SATransactionCode WHERE mSAChrgT2.SATransactionCode.TransactionCode = "F1INSURA" 
                  RESULT = SetCursorFirstEntityByString( mSAChrgT2, "SATransactionCode", "TransactionCode", "F1INSURA", "" );
                  //:ELSE
               } 
               else
               { 
                  //:SET CURSOR FIRST mSAChrgT2.SATransactionCode WHERE mSAChrgT2.SATransactionCode.ID = mSAChrgT.SATransactionCode.ID
                  {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                                     GetIntegerFromAttribute( mi_lTempInteger_8, mSAChrgT, "SATransactionCode", "ID" );
                  lTempInteger_8 = mi_lTempInteger_8.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mSAChrgT2, "SATransactionCode", "ID", lTempInteger_8, "" );
               } 

               //:END

               //:IF mSAProf.PeriodCollegeTerm.Semester = "01" OR 
               //:   mSAProf.PeriodCollegeTerm.Semester = "03" 
               if ( CompareAttributeToString( mSAProf, "PeriodCollegeTerm", "Semester", "01" ) == 0 || CompareAttributeToString( mSAProf, "PeriodCollegeTerm", "Semester", "03" ) == 0 )
               { 

                  //:// Always charge fee for Fall and Spring.
                  //:dAmount = mSAChrgT2.SATransactionTerm.Amount
                  {MutableDouble md_dAmount = new MutableDouble( dAmount );
                                     GetDecimalFromAttribute( md_dAmount, mSAChrgT2, "SATransactionTerm", "Amount" );
                  dAmount = md_dAmount.doubleValue( );}
                  //:ELSE
               } 
               else
               { 

                  //:IF mSAProf.PeriodCollegeTerm.Semester = "04"
                  if ( CompareAttributeToString( mSAProf, "PeriodCollegeTerm", "Semester", "04" ) == 0 )
                  { 
                     //:// It's Summer, so see if we've already paid it in the Spring. To do that, we'll need to locate
                     //:// the Spring Term.
                     //:CreateViewFromView( lSATermLST2, lSATermLST )
                     CreateViewFromView( lSATermLST2, lSATermLST );
                     //:SET CURSOR FIRST lSATermLST2.CollegeTerm WHERE lSATermLST2.CollegeTerm.Semester = "03" AND 
                     //:                                               lSATermLST2.CollegeYear.ID       = mSAProf.PeriodCollegeYear.ID
                     RESULT = SetCursorFirstEntity( lSATermLST2, "CollegeTerm", "" );
                     if ( RESULT > zCURSOR_UNCHANGED )
                     { 
                        while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( lSATermLST2, "CollegeTerm", "Semester", "03" ) != 0 || CompareAttributeToAttribute( lSATermLST2, "CollegeYear", "ID", mSAProf, "PeriodCollegeYear", "ID" ) != 0 ) )
                        { 
                           RESULT = SetCursorNextEntity( lSATermLST2, "CollegeTerm", "" );
                        } 

                     } 

                     //:IF RESULT < zCURSOR_SET
                     if ( RESULT < zCURSOR_SET )
                     { 
                        //:IssueError( mSAProf,0,0, "Programming Error Fall Semester" )
                        IssueError( mSAProf, 0, 0, "Programming Error Fall Semester" );
                     } 

                     //:END
                     //:szFallHealthInsuranceTranCode = "INSURANC"
                      {StringBuilder sb_szFallHealthInsuranceTranCode;
                     if ( szFallHealthInsuranceTranCode == null )
                        sb_szFallHealthInsuranceTranCode = new StringBuilder( 32 );
                     else
                        sb_szFallHealthInsuranceTranCode = new StringBuilder( szFallHealthInsuranceTranCode );
                                          ZeidonStringCopy( sb_szFallHealthInsuranceTranCode, 1, 0, "INSURANC", 1, 0, 11 );
                     szFallHealthInsuranceTranCode = sb_szFallHealthInsuranceTranCode.toString( );}
                     //:// Since there could be a refund as well as a charge, we'll total up Spring Transactions of the Insurance type.
                     //:dTotal = 0
                     dTotal = 0;
                     //:FOR EACH mSAProf.StudentAccountTransApplied
                     RESULT = SetCursorFirstEntity( mSAProf, "StudentAccountTransApplied", "" );
                     while ( RESULT > zCURSOR_UNCHANGED )
                     { 
                        //:IF mSAProf.SATransactionCode.TransactionCode = szFallHealthInsuranceTranCode AND 
                        //:   mSAProf.AppliedBillingPeriodTerm.ID = lSATermLST2.CollegeTerm.ID 
                        if ( CompareAttributeToString( mSAProf, "SATransactionCode", "TransactionCode", szFallHealthInsuranceTranCode ) == 0 &&
                        CompareAttributeToAttribute( mSAProf, "AppliedBillingPeriodTerm", "ID", lSATermLST2, "CollegeTerm", "ID" ) == 0 )
                        { 

                           //:dTotal = dTotal + mSAProf.StudentAccountTransApplied.Amount
                           {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                                                       GetDecimalFromAttribute( md_dTempDecimal_2, mSAProf, "StudentAccountTransApplied", "Amount" );
                           dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
                           dTotal = dTotal + dTempDecimal_2;
                        } 

                        RESULT = SetCursorNextEntity( mSAProf, "StudentAccountTransApplied", "" );
                        //:END
                     } 

                     //:END
                     //:IF dTotal > 0 
                     if ( dTotal > 0 )
                     { 
                        //:// The Fee was paid in the Spring.
                        //:dAmount = 0
                        dAmount = 0;
                        //:ELSE
                     } 
                     else
                     { 
                        //:// The Fee was not paid and needs to be charged.
                        //:dAmount = mSAChrgT2.SATransactionTerm.Amount 
                        {MutableDouble md_dAmount = new MutableDouble( dAmount );
                                                 GetDecimalFromAttribute( md_dAmount, mSAChrgT2, "SATransactionTerm", "Amount" );
                        dAmount = md_dAmount.doubleValue( );}
                     } 

                     //:END
                     //:DropView( lSATermLST2 )
                     DropView( lSATermLST2 );
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

      //:// Process resulting Charge or Refund.
      //:IF szRefundFlag = ""
      if ( ZeidonStringCompare( szRefundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
      { 
         //:// Regular Charge, not Refund. Generate entry if there is a positive amount.
         //:IF dAmount > 0
         if ( dAmount > 0 )
         { 
            //:// Generate the charge if it isn't already there.
            //:GenerateChargeEntry( mSAChrgT2, mSAProf, dAmount, "" )
            omSAChrgT_GenerateChargeEntry( mSAChrgT2, mSAProf, dAmount, "" );

            //:// For a Health Insurance charge, we need to set the Transaction Date based on the InsuranceBenefit date for
            //:// the term, either Domestic or International. If the current date is NOT greater than the end of the month of the
            //:// benefit date, then change the Transaction Date to the correct benefit date. Otherwise, we will keep today's date.
            //:IF mSAProf.Demographics.VisaClassification = "F-1"
            if ( CompareAttributeToString( mSAProf, "Demographics", "VisaClassification", "F-1" ) == 0 )
            { 
               //:// Use International benefit date.
               //:szInsuranceBenefitDate = mSAProf.PeriodCollegeTerm.InsuranceBenefitDateInternat 
               {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
               StringBuilder sb_szInsuranceBenefitDate;
               if ( szInsuranceBenefitDate == null )
                  sb_szInsuranceBenefitDate = new StringBuilder( 32 );
               else
                  sb_szInsuranceBenefitDate = new StringBuilder( szInsuranceBenefitDate );
                               GetVariableFromAttribute( sb_szInsuranceBenefitDate, mi_lTempInteger_9, 'S', 9, mSAProf, "PeriodCollegeTerm", "InsuranceBenefitDateInternat", "", 0 );
               lTempInteger_9 = mi_lTempInteger_9.intValue( );
               szInsuranceBenefitDate = sb_szInsuranceBenefitDate.toString( );}
               //:ELSE
            } 
            else
            { 
               //:// Use Domestic benefit date.
               //:szInsuranceBenefitDate = mSAProf.PeriodCollegeTerm.InsuranceBenefitDateDomestic 
               {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
               StringBuilder sb_szInsuranceBenefitDate;
               if ( szInsuranceBenefitDate == null )
                  sb_szInsuranceBenefitDate = new StringBuilder( 32 );
               else
                  sb_szInsuranceBenefitDate = new StringBuilder( szInsuranceBenefitDate );
                               GetVariableFromAttribute( sb_szInsuranceBenefitDate, mi_lTempInteger_10, 'S', 9, mSAProf, "PeriodCollegeTerm", "InsuranceBenefitDateDomestic", "", 0 );
               lTempInteger_10 = mi_lTempInteger_10.intValue( );
               szInsuranceBenefitDate = sb_szInsuranceBenefitDate.toString( );}
            } 

            //:END
            //:// Get begining of next month after benefit date. (Note that the month is never 12 (Dec.))
            //:szMonth = szInsuranceBenefitDate[5:2]
             {StringBuilder sb_szMonth;
            if ( szMonth == null )
               sb_szMonth = new StringBuilder( 32 );
            else
               sb_szMonth = new StringBuilder( szMonth );
                        ZeidonStringCopy( sb_szMonth, 1, 0, szInsuranceBenefitDate, 5, 2, 3 );
            szMonth = sb_szMonth.toString( );}
            //:Month   = zStringToInteger( szMonth )
            Month = zStringToInteger( szMonth );
            //:Month   = Month  + 1
            Month = Month + 1;
            //:szMonth = Month
             {StringBuilder sb_szMonth;
            if ( szMonth == null )
               sb_szMonth = new StringBuilder( 32 );
            else
               sb_szMonth = new StringBuilder( szMonth );
                        ZeidonStringConvertFromNumber( sb_szMonth, 1, 0, 2, Month, (double) 0.0, "I" );
            szMonth = sb_szMonth.toString( );}
            //:szStartOfNextMonthDate = szInsuranceBenefitDate[1:4] + szMonth + "01"
             {StringBuilder sb_szStartOfNextMonthDate;
            if ( szStartOfNextMonthDate == null )
               sb_szStartOfNextMonthDate = new StringBuilder( 32 );
            else
               sb_szStartOfNextMonthDate = new StringBuilder( szStartOfNextMonthDate );
                        ZeidonStringCopy( sb_szStartOfNextMonthDate, 1, 0, szInsuranceBenefitDate, 1, 4, 9 );
            szStartOfNextMonthDate = sb_szStartOfNextMonthDate.toString( );}
             {StringBuilder sb_szStartOfNextMonthDate;
            if ( szStartOfNextMonthDate == null )
               sb_szStartOfNextMonthDate = new StringBuilder( 32 );
            else
               sb_szStartOfNextMonthDate = new StringBuilder( szStartOfNextMonthDate );
                        ZeidonStringConcat( sb_szStartOfNextMonthDate, 1, 0, szMonth, 1, 0, 9 );
            szStartOfNextMonthDate = sb_szStartOfNextMonthDate.toString( );}
             {StringBuilder sb_szStartOfNextMonthDate;
            if ( szStartOfNextMonthDate == null )
               sb_szStartOfNextMonthDate = new StringBuilder( 32 );
            else
               sb_szStartOfNextMonthDate = new StringBuilder( szStartOfNextMonthDate );
                        ZeidonStringConcat( sb_szStartOfNextMonthDate, 1, 0, "01", 1, 0, 9 );
            szStartOfNextMonthDate = sb_szStartOfNextMonthDate.toString( );}
            //:szWorkDate = wXferO.Root.dCurrentDate 
            {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
            StringBuilder sb_szWorkDate;
            if ( szWorkDate == null )
               sb_szWorkDate = new StringBuilder( 32 );
            else
               sb_szWorkDate = new StringBuilder( szWorkDate );
                         GetVariableFromAttribute( sb_szWorkDate, mi_lTempInteger_11, 'S', 9, wXferO, "Root", "dCurrentDate", "", 0 );
            lTempInteger_11 = mi_lTempInteger_11.intValue( );
            szWorkDate = sb_szWorkDate.toString( );}
            //:IF szWorkDate < szStartOfNextMonthDate
            if ( ZeidonStringCompare( szWorkDate, 1, 0, szStartOfNextMonthDate, 1, 0, 9 ) > 0 )
            { 
               //:// Change Transaction Date to Benefit Date since today's date is NOT in the next month after benefit date.
               //:mSAProf.StudentAccountTransApplied.TransactionDate = szInsuranceBenefitDate
               SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "TransactionDate", szInsuranceBenefitDate );
            } 

            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Refund.
         //:// We'll process a Refund if the Amount has changed.
         //:IF dAmount != mSAProf.PeriodTransApplied.Amount 
         if ( CompareAttributeToDecimal( mSAProf, "PeriodTransApplied", "Amount", dAmount ) != 0 )
         { 
            //:GenerateRefundEntry( mSAChrgT2, mSAProf )
            omSAChrgT_GenerateRefundEntry( mSAChrgT2, mSAProf );
         } 

         //:END
      } 

      //:END
      //:DropView( mSAChrgT2 )
      DropView( mSAChrgT2 );
      //:RETURN 0 
      if(8==8)return( 0 );
   } 

   //:END


   //:// UNIV110 FEES

   //:// If the Student is taking UNIV110, they will be charged the CKTCAMPS Fee always and the CKTCAMP Fee for Fall Term.

   //:IF szInternalRuleName = "UNIV110 Fees"
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "UNIV110 Fees", 1, 0, 51 ) == 0 )
   { 

      //:szFoundRuleFlag = "Y"
       {StringBuilder sb_szFoundRuleFlag;
      if ( szFoundRuleFlag == null )
         sb_szFoundRuleFlag = new StringBuilder( 32 );
      else
         sb_szFoundRuleFlag = new StringBuilder( szFoundRuleFlag );
            ZeidonStringCopy( sb_szFoundRuleFlag, 1, 0, "Y", 1, 0, 2 );
      szFoundRuleFlag = sb_szFoundRuleFlag.toString( );}
      //:dAmount = 0
      dAmount = 0;

      //:// Determine if student is taking UNIV110.
      //:SET CURSOR FIRST mSAProf.TermEnrolled WHERE ( mSAProf.TermEnrolled.Status = "T" OR mSAProf.TermEnrolled.Status = "C" )
      //:                                        AND mSAProf.TermCourse.Number = "UNIV110"
      RESULT = SetCursorFirstEntity( mSAProf, "TermEnrolled", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( ( CompareAttributeToString( mSAProf, "TermEnrolled", "Status", "T" ) != 0 && CompareAttributeToString( mSAProf, "TermEnrolled", "Status", "C" ) != 0 ) ||
                 CompareAttributeToString( mSAProf, "TermCourse", "Number", "UNIV110" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( mSAProf, "TermEnrolled", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// Process either CKTCAMP or CKTCAMPS Transaction.
         //:IF mSAChrgT.SATransactionCode.TransactionCode = "CKTCAMPS"
         if ( CompareAttributeToString( mSAChrgT, "SATransactionCode", "TransactionCode", "CKTCAMPS" ) == 0 )
         { 
            //:// CKTCAMPS is always charged.
            //:dAmount = mSAChrgT.SATransactionTerm.Amount
            {MutableDouble md_dAmount = new MutableDouble( dAmount );
                         GetDecimalFromAttribute( md_dAmount, mSAChrgT, "SATransactionTerm", "Amount" );
            dAmount = md_dAmount.doubleValue( );}
            //:ELSE
         } 
         else
         { 
            //:IF mSAChrgT.SATransactionCode.TransactionCode = "CKTCAMP"
            if ( CompareAttributeToString( mSAChrgT, "SATransactionCode", "TransactionCode", "CKTCAMP" ) == 0 )
            { 
               //:// CKTCAMP is only charged in the Fall.
               //:IF mSAProf.PeriodCollegeTerm.Semester = "01"
               if ( CompareAttributeToString( mSAProf, "PeriodCollegeTerm", "Semester", "01" ) == 0 )
               { 
                  //:dAmount = mSAChrgT.SATransactionTerm.Amount
                  {MutableDouble md_dAmount = new MutableDouble( dAmount );
                                     GetDecimalFromAttribute( md_dAmount, mSAChrgT, "SATransactionTerm", "Amount" );
                  dAmount = md_dAmount.doubleValue( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:// Process resulting Charge or Refund.
      //:IF szRefundFlag = ""
      if ( ZeidonStringCompare( szRefundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
      { 
         //:// Regular Charge, not Refund. Generate entry if there is a positive amount.
         //:IF dAmount > 0
         if ( dAmount > 0 )
         { 
            //:// Generate the charge if it isn't already there.
            //:GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, "" )
            omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, "" );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Refund.
         //:// We'll process a Refund if the Amount has changed.
         //:IF dAmount != mSAProf.PeriodTransApplied.Amount 
         if ( CompareAttributeToDecimal( mSAProf, "PeriodTransApplied", "Amount", dAmount ) != 0 )
         { 
            //:GenerateRefundEntry( mSAChrgT, mSAProf )
            omSAChrgT_GenerateRefundEntry( mSAChrgT, mSAProf );
         } 

         //:END
      } 

      //:END
      //:RETURN 0 
      if(8==8)return( 0 );
   } 

   //:END


   //:// MEAL PLAN

   //:// Student is charged once per College Term.

   //:IF szInternalRuleName = "Meal Plan"
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "Meal Plan", 1, 0, 51 ) == 0 )
   { 

      //:// A single Fee is charged per College Term if the Student has a Meal Plan for the date range
      //:// of the current College Term. We will consider the Meal Plan to be in the date range if either the
      //:// Meal Plan BeginDate or EndDate is within the Term range.

      //:dAmount = 0
      dAmount = 0;

      //:// First find if there is a Meal Plan specified for the current Term.
      //:CreateViewFromView( mSAProf2, mSAProf )
      CreateViewFromView( mSAProf2, mSAProf );
      //:SET CURSOR FIRST mSAProf2.TermOfMealPlan WHERE ( mSAProf2.TermOfMealPlan.BeginDate >= mSAProf2.PeriodCollegeTerm.ResidencyStartDate AND 
      //:                                                 mSAProf2.TermOfMealPlan.BeginDate <= mSAProf2.PeriodCollegeTerm.ResidencyEndDate  )
      //:                                            OR ( mSAProf2.TermOfMealPlan.EndDate   >= mSAProf2.PeriodCollegeTerm.ResidencyStartDate AND 
      //:                                                 mSAProf2.TermOfMealPlan.EndDate   <= mSAProf2.PeriodCollegeTerm.ResidencyEndDate  )
      RESULT = SetCursorFirstEntity( mSAProf2, "TermOfMealPlan", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( ( CompareAttributeToAttribute( mSAProf2, "TermOfMealPlan", "BeginDate", mSAProf2, "PeriodCollegeTerm", "ResidencyStartDate" ) < 0 ||
                 CompareAttributeToAttribute( mSAProf2, "TermOfMealPlan", "BeginDate", mSAProf2, "PeriodCollegeTerm", "ResidencyEndDate" ) > 0 ) &&
                 ( CompareAttributeToAttribute( mSAProf2, "TermOfMealPlan", "EndDate", mSAProf2, "PeriodCollegeTerm", "ResidencyStartDate" ) < 0 ||
                 CompareAttributeToAttribute( mSAProf2, "TermOfMealPlan", "EndDate", mSAProf2, "PeriodCollegeTerm", "ResidencyEndDate" ) > 0 ) ) )
         { 
            RESULT = SetCursorNextEntity( mSAProf2, "TermOfMealPlan", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET 
      if ( RESULT >= zCURSOR_SET )
      { 

         //:// There is a Meal Plan for the current Term. See if it is for this Term.
         //:IF mSAProf2.MPSATransactionCode.TransactionCode = mSAChrgT.SATransactionCode.TransactionCode 
         if ( CompareAttributeToAttribute( mSAProf2, "MPSATransactionCode", "TransactionCode", mSAChrgT, "SATransactionCode", "TransactionCode" ) == 0 )
         { 
            //:IF RESULT >= zCURSOR_SET 
            if ( RESULT >= zCURSOR_SET )
            { 

               //:// There is a Charge for this Transaction.
               //:dAmount = mSAChrgT.SATransactionTerm.Amount
               {MutableDouble md_dAmount = new MutableDouble( dAmount );
                               GetDecimalFromAttribute( md_dAmount, mSAChrgT, "SATransactionTerm", "Amount" );
               dAmount = md_dAmount.doubleValue( );}
            } 

            //:   
            //:END
         } 

         //:END
      } 

      //:END
      //:DropView( mSAProf2 )
      DropView( mSAProf2 );

      //:// Process resulting Charge or Refund.
      //:IF szRefundFlag = ""
      if ( ZeidonStringCompare( szRefundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
      { 
         //:// Regular Charge, not Refund. Generate entry if there is a positive amount.
         //:IF dAmount > 0
         if ( dAmount > 0 )
         { 
            //:// Generate the charge if it isn't already there.
            //:GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, "" )
            omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, "" );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Refund.
         //:// We'll process a Refund if the Amount has changed.
         //:IF dAmount != mSAProf.PeriodTransApplied.Amount 
         if ( CompareAttributeToDecimal( mSAProf, "PeriodTransApplied", "Amount", dAmount ) != 0 )
         { 
            //:GenerateRefundEntry( mSAChrgT, mSAProf )
            omSAChrgT_GenerateRefundEntry( mSAChrgT, mSAProf );
         } 

         //:END
      } 

      //:END

      //:RETURN 0
      if(8==8)return( 0 );
   } 


   //:END

   //:// RESIDENCY

   //:// Student is charged once per College Term, based on Residency in Billing Period.

   //:IF szInternalRuleName = "Residency"
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "Residency", 1, 0, 51 ) == 0 )
   { 

      //:// A single Fee is charged per College Term if the Billing Period Residency is Residence Hall.
      //:// If the Billing Period Residency is null, we will use the Residency from the Student entity.

      //:dAmount = 0
      dAmount = 0;

      //:// First get Residency value from Billing Period, or Student, if Billing Period value is null.
      //:IF mSAProf.BillingPeriod.ResidencyStatusTerm = ""
      if ( CompareAttributeToString( mSAProf, "BillingPeriod", "ResidencyStatusTerm", "" ) == 0 )
      { 
         //:szResidencyStatus = mSAProf.Student.ResidencyStatus 
         {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
         StringBuilder sb_szResidencyStatus;
         if ( szResidencyStatus == null )
            sb_szResidencyStatus = new StringBuilder( 32 );
         else
            sb_szResidencyStatus = new StringBuilder( szResidencyStatus );
                   GetVariableFromAttribute( sb_szResidencyStatus, mi_lTempInteger_12, 'S', 2, mSAProf, "Student", "ResidencyStatus", "", 0 );
         lTempInteger_12 = mi_lTempInteger_12.intValue( );
         szResidencyStatus = sb_szResidencyStatus.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szResidencyStatus = mSAProf.BillingPeriod.ResidencyStatusTerm
         {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
         StringBuilder sb_szResidencyStatus;
         if ( szResidencyStatus == null )
            sb_szResidencyStatus = new StringBuilder( 32 );
         else
            sb_szResidencyStatus = new StringBuilder( szResidencyStatus );
                   GetVariableFromAttribute( sb_szResidencyStatus, mi_lTempInteger_13, 'S', 2, mSAProf, "BillingPeriod", "ResidencyStatusTerm", "", 0 );
         lTempInteger_13 = mi_lTempInteger_13.intValue( );
         szResidencyStatus = sb_szResidencyStatus.toString( );}
      } 

      //:END

      //:// First find if the is a Resident or Off Campus..
      //:IF szResidencyStatus = "R"
      if ( ZeidonStringCompare( szResidencyStatus, 1, 0, "R", 1, 0, 2 ) == 0 )
      { 

         //:// The Student is a Resident. Check if Transaction Code matches Gender.
         //:// RESMEN is Male.
         //:// RESWOMEN is Female.
         //:IF ( mSAChrgT.SATransactionCode.TransactionCode = "RESMEN"   AND mSAProf.Person.Gender = "M" ) OR
         //:   ( mSAChrgT.SATransactionCode.TransactionCode = "RESWOMEN" AND mSAProf.Person.Gender = "F" )
         if ( ( CompareAttributeToString( mSAChrgT, "SATransactionCode", "TransactionCode", "RESMEN" ) == 0 && CompareAttributeToString( mSAProf, "Person", "Gender", "M" ) == 0 ) ||
              ( CompareAttributeToString( mSAChrgT, "SATransactionCode", "TransactionCode", "RESWOMEN" ) == 0 && CompareAttributeToString( mSAProf, "Person", "Gender", "F" ) == 0 ) )
         { 
            //:   
            //:   // There is a Charge for this Transaction.
            //:   dAmount = mSAChrgT.SATransactionTerm.Amount
            {MutableDouble md_dAmount = new MutableDouble( dAmount );
                         GetDecimalFromAttribute( md_dAmount, mSAChrgT, "SATransactionTerm", "Amount" );
            dAmount = md_dAmount.doubleValue( );}
         } 

         //:      
         //:END
      } 

      //:END

      //:// Process resulting Charge or Refund.
      //:IF szRefundFlag = ""
      if ( ZeidonStringCompare( szRefundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
      { 
         //:// Regular Charge, not Refund. Generate entry if there is a positive amount.
         //:IF dAmount > 0
         if ( dAmount > 0 )
         { 
            //:// Generate the charge if it isn't already there.
            //:GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, "" )
            omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, "" );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Refund.
         //:// We'll process a Refund if the Amount has changed.
         //:IF dAmount != mSAProf.PeriodTransApplied.Amount 
         if ( CompareAttributeToDecimal( mSAProf, "PeriodTransApplied", "Amount", dAmount ) != 0 )
         { 
            //:GenerateRefundEntry( mSAChrgT, mSAProf )
            omSAChrgT_GenerateRefundEntry( mSAChrgT, mSAProf );
         } 

         //:END
      } 

      //:END

      //:RETURN 0
      if(8==8)return( 0 );
   } 


   //:END

   //:// DL TUITION CHARGE

   //:// Tuition charge is driven by Class Section and Enrolled Credits.

   //:IF szInternalRuleName = "DL Tuition"
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "DL Tuition", 1, 0, 51 ) == 0 )
   { 

      //:szFoundRuleFlag = "Y"
       {StringBuilder sb_szFoundRuleFlag;
      if ( szFoundRuleFlag == null )
         sb_szFoundRuleFlag = new StringBuilder( 32 );
      else
         sb_szFoundRuleFlag = new StringBuilder( szFoundRuleFlag );
            ZeidonStringCopy( sb_szFoundRuleFlag, 1, 0, "Y", 1, 0, 2 );
      szFoundRuleFlag = sb_szFoundRuleFlag.toString( );}
      //:Rule_DL_Tuition( mSAChrgT, mSAProf, szRefundFlag )
      omSAChrgT_Rule_DL_Tuition( mSAChrgT, mSAProf, szRefundFlag );

      //:RETURN 0 
      if(8==8)return( 0 );
   } 

   //:END

   //:// SUMMER TUITION CHARGE

   //:// Tuition charge is driven by Class Section and Enrolled Credits.

   //:IF szInternalRuleName = "Summer Tuition"
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "Summer Tuition", 1, 0, 51 ) == 0 )
   { 

      //:szFoundRuleFlag = "Y"
       {StringBuilder sb_szFoundRuleFlag;
      if ( szFoundRuleFlag == null )
         sb_szFoundRuleFlag = new StringBuilder( 32 );
      else
         sb_szFoundRuleFlag = new StringBuilder( szFoundRuleFlag );
            ZeidonStringCopy( sb_szFoundRuleFlag, 1, 0, "Y", 1, 0, 2 );
      szFoundRuleFlag = sb_szFoundRuleFlag.toString( );}
      //:Rule_SummerTuition( mSAChrgT, mSAProf, szRefundFlag )
      omSAChrgT_Rule_SummerTuition( mSAChrgT, mSAProf, szRefundFlag );

      //:RETURN 0 
      if(8==8)return( 0 );
   } 

   //:END

   //:// INDEPENDENT STUDY 

   //:// For each Independent Study Class, the student is charged a fee per hour.

   //:IF szInternalRuleName = "Independent Study"
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "Independent Study", 1, 0, 51 ) == 0 )
   { 

      //:// Process per Independent Study Class
      //:FOR EACH mSAProf.TermEnrolled
      RESULT = SetCursorFirstEntity( mSAProf, "TermEnrolled", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 

         //:dAmount = 0
         dAmount = 0;
         //:IF mSAProf.TermClass.IndependentStudyFlag = "Y"
         if ( CompareAttributeToString( mSAProf, "TermClass", "IndependentStudyFlag", "Y" ) == 0 )
         { 
            //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable 
            //:           WHERE mSAChrgT.SAInternalRuleVariable.Name = "Charge/Credit"
            RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "Charge/Credit", "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:szMsg = "Missing variable name, 'Charge/Credit', for Internal Rule," + szInternalRuleName + "'."
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringCopy( sb_szMsg, 1, 0, "Missing variable name, 'Charge/Credit', for Internal Rule,", 1, 0, 301 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, szInternalRuleName, 1, 0, 301 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, "'.", 1, 0, 301 );
               szMsg = sb_szMsg.toString( );}
               //:MessageSend( mSAChrgT, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               MessageSend( mSAChrgT, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               //:RETURN -1
               if(8==8)return( -1 );
            } 

            //:END

            //:// Fee is Credits * Charge/Credit, for enrolled or completed classes.
            //:IF (mSAProf.TermEnrolled.Status ="T" OR mSAProf.TermEnrolled.Status ="C")
            if ( CompareAttributeToString( mSAProf, "TermEnrolled", "Status", "T" ) == 0 || CompareAttributeToString( mSAProf, "TermEnrolled", "Status", "C" ) == 0 )
            { 
               //:dTotalCredits = mSAProf.TermEnrolled.CreditHours
               {MutableDouble md_dTotalCredits = new MutableDouble( dTotalCredits );
                               GetDecimalFromAttribute( md_dTotalCredits, mSAProf, "TermEnrolled", "CreditHours" );
               dTotalCredits = md_dTotalCredits.doubleValue( );}
               //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value 
               {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                               GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
               dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
               //:dAmount = dTotalCredits * dChargePerCreditHour
               dAmount = dTotalCredits * dChargePerCreditHour;
            } 

            //:END

            //:// Process Amount at Charge or Refund.
            //:IF szRefundFlag = ""
            if ( ZeidonStringCompare( szRefundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
            { 
               //:IF dAmount > 0
               if ( dAmount > 0 )
               { 
                  //:// Use Description in GenerateChargeEntry because we want the compare for duplicate by Description.
                  //:szDisplayCredits = dTotalCredits
                   {StringBuilder sb_szDisplayCredits;
                  if ( szDisplayCredits == null )
                     sb_szDisplayCredits = new StringBuilder( 32 );
                  else
                     sb_szDisplayCredits = new StringBuilder( szDisplayCredits );
                                    ZeidonStringConvertFromNumber( sb_szDisplayCredits, 1, 0, 20, 0, dTotalCredits, "D" );
                  szDisplayCredits = sb_szDisplayCredits.toString( );}
                  //:szDesc = "Indep Study Fee (" + mSAProf.TermCourse.Number + ") - " + szDisplayCredits + " CR"
                  {MutableInt mi_lTempInteger_14 = new MutableInt( lTempInteger_14 );
                  StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                     GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_14, 'S', 11, mSAProf, "TermCourse", "Number", "", 0 );
                  lTempInteger_14 = mi_lTempInteger_14.intValue( );
                  szTempString_2 = sb_szTempString_2.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringCopy( sb_szDesc, 1, 0, "Indep Study Fee (", 1, 0, 101 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, szTempString_2, 1, 0, 101 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, ") - ", 1, 0, 101 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, szDisplayCredits, 1, 0, 101 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, " CR", 1, 0, 101 );
                  szDesc = sb_szDesc.toString( );}
                  //:// Generate the charge if it doesn't already exist, which is determined inside operation GenerateChargeEntry.
                  //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, szDesc )
                  nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, szDesc );
                  //:IF nRC >= 0
                  if ( nRC >= 0 )
                  { 
                     //:// Modify Description.
                     //:mSAProf.StudentAccountTransApplied.Description = szDesc
                     SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szDesc );
                  } 

                  //:END
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// Refund.
               //:IF dAmount != mSAProf.PeriodTransApplied.Amount
               if ( CompareAttributeToDecimal( mSAProf, "PeriodTransApplied", "Amount", dAmount ) != 0 )
               { 
                  //:// Go to create refund or give User and error message, if any classes were dropped after Add/Drop deadline.
                  //:CreateRefundByDropDate( mSAChrgT, mSAProf )
                  omSAChrgT_CreateRefundByDropDate( mSAChrgT, mSAProf );
               } 

               //:END
            } 

            //:END
         } 

         RESULT = SetCursorNextEntity( mSAProf, "TermEnrolled", "" );
         //:END
      } 

      //:END

      //:RETURN 0 
      if(8==8)return( 0 );
   } 

   //:END


   //:// Give error if the Rule was not found.
   //:IF szFoundRuleFlag = ""
   if ( ZeidonStringCompare( szFoundRuleFlag, 1, 0, "", 1, 0, 2 ) == 0 )
   { 
      //:szMsg = "The Internal Rule Name, '" + szInternalRuleName + "', was not found for Transaction Code, " +
      //:        mSAChrgT.SATransactionCode.TransactionCode + "."
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringCopy( sb_szMsg, 1, 0, "The Internal Rule Name, '", 1, 0, 301 );
      szMsg = sb_szMsg.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, szInternalRuleName, 1, 0, 301 );
      szMsg = sb_szMsg.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, "', was not found for Transaction Code, ", 1, 0, 301 );
      szMsg = sb_szMsg.toString( );}
      {MutableInt mi_lTempInteger_15 = new MutableInt( lTempInteger_15 );
      StringBuilder sb_szTempString_3;
      if ( szTempString_3 == null )
         sb_szTempString_3 = new StringBuilder( 32 );
      else
         sb_szTempString_3 = new StringBuilder( szTempString_3 );
             GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_15, 'S', 21, mSAChrgT, "SATransactionCode", "TransactionCode", "", 0 );
      lTempInteger_15 = mi_lTempInteger_15.intValue( );
      szTempString_3 = sb_szTempString_3.toString( );}
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
            ZeidonStringConcat( sb_szMsg, 1, 0, ".", 1, 0, 301 );
      szMsg = sb_szMsg.toString( );}
      //:MessageSend( mSAChrgT, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
      MessageSend( mSAChrgT, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:RETURN 1
   return( 1 );
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateChargeEntry( VIEW mSAChrgT BASED ON LOD mSAChrgT,
//:                     VIEW mSAProf  BASED ON LOD mSAProf,
//:                     DECIMAL dTotal,
//:                     STRING ( 50 ) szDescription )

//:   VIEW mSAProf2 BASED ON LOD mSAProf
public int 
omSAChrgT_GenerateChargeEntry( View     mSAChrgT,
                               View     mSAProf,
                               double  dTotal,
                               String   szDescription )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:STRING ( 1 ) szFoundFlag
   String   szFoundFlag = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;


   //:// Create the StudentAccountTransApplied entry, if it's not already there.
   //:// Note that if the amount generated this time differs from what was before, the
   //:// CheckExistingCharges operation in mSAProf would have created an adjusting entry.
   //:// Also note that if a value for szDescription is passed in, we will use that value in the compare.

   //:// Check if entry is already there.
   //:szFoundFlag = ""
    {StringBuilder sb_szFoundFlag;
   if ( szFoundFlag == null )
      sb_szFoundFlag = new StringBuilder( 32 );
   else
      sb_szFoundFlag = new StringBuilder( szFoundFlag );
      ZeidonStringCopy( sb_szFoundFlag, 1, 0, "", 1, 0, 2 );
   szFoundFlag = sb_szFoundFlag.toString( );}
   //:CreateViewFromView( mSAProf2, mSAProf )
   CreateViewFromView( mSAProf2, mSAProf );
   //:SET CURSOR FIRST mSAProf2.PeriodTransApplied 
   RESULT = SetCursorFirstEntity( mSAProf2, "PeriodTransApplied", "" );
   //:IF szDescription = "" 
   if ( ZeidonStringCompare( szDescription, 1, 0, "", 1, 0, 51 ) == 0 )
   { 
      //:// Don't include Description in compare.
      //:LOOP WHILE RESULT >= zCURSOR_SET AND szFoundFlag = ""
      while ( RESULT >= zCURSOR_SET && ZeidonStringCompare( szFoundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
      { 
         //:IF mSAProf2.PeriodSATransactionCode.ID = mSAChrgT.SATransactionCode.ID AND
         //:   mSAProf2.PeriodAdjustedSATransApplied  DOES NOT EXIST AND 
         lTempInteger_0 = CheckExistenceOfEntity( mSAProf2, "PeriodAdjustedSATransApplied" );
         //:   mSAProf2.PeriodAdjustingSATransApplied DOES NOT EXIST
         lTempInteger_1 = CheckExistenceOfEntity( mSAProf2, "PeriodAdjustingSATransApplied" );
         if ( CompareAttributeToAttribute( mSAProf2, "PeriodSATransactionCode", "ID", mSAChrgT, "SATransactionCode", "ID" ) == 0 && lTempInteger_0 != 0 && lTempInteger_1 != 0 )
         { 

            //:szFoundFlag = "Y"
             {StringBuilder sb_szFoundFlag;
            if ( szFoundFlag == null )
               sb_szFoundFlag = new StringBuilder( 32 );
            else
               sb_szFoundFlag = new StringBuilder( szFoundFlag );
                        ZeidonStringCopy( sb_szFoundFlag, 1, 0, "Y", 1, 0, 2 );
            szFoundFlag = sb_szFoundFlag.toString( );}
            //:ELSE
         } 
         else
         { 
            //:SET CURSOR NEXT mSAProf2.PeriodTransApplied 
            RESULT = SetCursorNextEntity( mSAProf2, "PeriodTransApplied", "" );
         } 

         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// Include Description in compare.
      //:LOOP WHILE RESULT >= zCURSOR_SET AND szFoundFlag = ""
      while ( RESULT >= zCURSOR_SET && ZeidonStringCompare( szFoundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
      { 
         //:IF mSAProf2.PeriodSATransactionCode.ID = mSAChrgT.SATransactionCode.ID AND
         //:   mSAProf2.PeriodTransApplied.Description = szDescription AND
         //:   mSAProf2.PeriodAdjustedSATransApplied  DOES NOT EXIST AND 
         lTempInteger_2 = CheckExistenceOfEntity( mSAProf2, "PeriodAdjustedSATransApplied" );
         //:   mSAProf2.PeriodAdjustingSATransApplied DOES NOT EXIST
         lTempInteger_3 = CheckExistenceOfEntity( mSAProf2, "PeriodAdjustingSATransApplied" );
         if ( CompareAttributeToAttribute( mSAProf2, "PeriodSATransactionCode", "ID", mSAChrgT, "SATransactionCode", "ID" ) == 0 && CompareAttributeToString( mSAProf2, "PeriodTransApplied", "Description", szDescription ) == 0 && lTempInteger_2 != 0 &&
              lTempInteger_3 != 0 )
         { 

            //:szFoundFlag = "Y"
             {StringBuilder sb_szFoundFlag;
            if ( szFoundFlag == null )
               sb_szFoundFlag = new StringBuilder( 32 );
            else
               sb_szFoundFlag = new StringBuilder( szFoundFlag );
                        ZeidonStringCopy( sb_szFoundFlag, 1, 0, "Y", 1, 0, 2 );
            szFoundFlag = sb_szFoundFlag.toString( );}
            //:ELSE
         } 
         else
         { 
            //:SET CURSOR NEXT mSAProf2.PeriodTransApplied 
            RESULT = SetCursorNextEntity( mSAProf2, "PeriodTransApplied", "" );
         } 

         //:END
      } 

      //:END 
   } 

   //:END
   //:DropView( mSAProf2 )
   DropView( mSAProf2 );
   //:IF szFoundFlag = ""
   if ( ZeidonStringCompare( szFoundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
   { 
      //:// Entry does not already exist, so create it.
      //:CREATE ENTITY mSAProf.StudentAccountTransApplied 
      RESULT = CreateEntity( mSAProf, "StudentAccountTransApplied", zPOS_AFTER );
      //:INCLUDE mSAProf.SATransactionCode    FROM mSAChrgT.SATransactionCode
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "SATransactionCode", mSAChrgT, "SATransactionCode", zPOS_AFTER );
      //:INCLUDE mSAProf.DebitGLChartEntry    FROM mSAChrgT.DebitGLChartEntry  
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "DebitGLChartEntry", mSAChrgT, "DebitGLChartEntry", zPOS_AFTER );
      //:INCLUDE mSAProf.CreditGLChartEntry   FROM mSAChrgT.CreditGLChartEntry 
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "CreditGLChartEntry", mSAChrgT, "CreditGLChartEntry", zPOS_AFTER );
      //:INCLUDE mSAProf.AppliedBillingPeriod FROM mSAProf.BillingPeriod 
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "AppliedBillingPeriod", mSAProf, "BillingPeriod", zPOS_AFTER );
      //:mSAProf.StudentAccountTransApplied.Amount = dTotal  
      SetAttributeFromDecimal( mSAProf, "StudentAccountTransApplied", "Amount", dTotal );

      //:SetMatchingAttributesByName( mSAProf,  "StudentAccountTransApplied", 
      //:                             mSAChrgT, "SATransactionCode", zSET_NOTNULL )
      SetMatchingAttributesByName( mSAProf, "StudentAccountTransApplied", mSAChrgT, "SATransactionCode", zSET_NOTNULL );
      //:IF szDescription = ""
      if ( ZeidonStringCompare( szDescription, 1, 0, "", 1, 0, 51 ) == 0 )
      { 
         //:mSAProf.StudentAccountTransApplied.Description = mSAProf.SATransactionCode.Description 
         SetAttributeFromAttribute( mSAProf, "StudentAccountTransApplied", "Description", mSAProf, "SATransactionCode", "Description" );
         //:ELSE
      } 
      else
      { 
         //:mSAProf.StudentAccountTransApplied.Description = szDescription
         SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szDescription );
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
      lTempInteger_4 = CheckExistenceOfEntity( mSAProf, "PeriodCollegeTerm" );
      if ( lTempInteger_4 == 0 )
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

      //:ELSE
   } 
   else
   { 
      //:// Return with a negative return code to indicate the entry was not created.
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateRefundEntry( VIEW mSAChrgT BASED ON LOD mSAChrgT,
//:                     VIEW mSAProf  BASED ON LOD mSAProf )

//:   DECIMAL dAmount
public int 
omSAChrgT_GenerateRefundEntry( View     mSAChrgT,
                               View     mSAProf )
{
   double  dAmount = 0.0;
   //:DECIMAL dReversalAmount
   double  dReversalAmount = 0.0;
   int      RESULT = 0;
   String   szTempString_0 = null;


   //:// Create the StudentAccountTransApplied entry for the refund and include the original transaction
   //:// as AdjustedSATransApplied.

   //:// Entry does not already exist, so create it.
   //:CREATE ENTITY mSAProf.StudentAccountTransApplied 
   RESULT = CreateEntity( mSAProf, "StudentAccountTransApplied", zPOS_AFTER );
   //:INCLUDE mSAProf.SATransactionCode    FROM mSAChrgT.SATransactionCode
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "SATransactionCode", mSAChrgT, "SATransactionCode", zPOS_AFTER );
   //:INCLUDE mSAProf.DebitGLChartEntry    FROM mSAChrgT.DebitGLChartEntry  
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "DebitGLChartEntry", mSAChrgT, "DebitGLChartEntry", zPOS_AFTER );
   //:INCLUDE mSAProf.CreditGLChartEntry   FROM mSAChrgT.CreditGLChartEntry 
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "CreditGLChartEntry", mSAChrgT, "CreditGLChartEntry", zPOS_AFTER );
   //:INCLUDE mSAProf.AppliedBillingPeriod FROM mSAProf.BillingPeriod 
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "AppliedBillingPeriod", mSAProf, "BillingPeriod", zPOS_AFTER );
   //:dAmount = mSAProf.PeriodTransApplied.Amount    // The Amount of the Refund is the amount of the original Transaction.
   {MutableDouble md_dAmount = new MutableDouble( dAmount );
       GetDecimalFromAttribute( md_dAmount, mSAProf, "PeriodTransApplied", "Amount" );
   dAmount = md_dAmount.doubleValue( );}
   //:dReversalAmount = dAmount * -1
   dReversalAmount = dAmount * -1;
   //:mSAProf.StudentAccountTransApplied.Amount = dReversalAmount  
   SetAttributeFromDecimal( mSAProf, "StudentAccountTransApplied", "Amount", dReversalAmount );

   //:SetMatchingAttributesByName( mSAProf,  "StudentAccountTransApplied", 
   //:                             mSAChrgT, "SATransactionCode", zSET_NOTNULL )
   SetMatchingAttributesByName( mSAProf, "StudentAccountTransApplied", mSAChrgT, "SATransactionCode", zSET_NOTNULL );
   //:mSAProf.StudentAccountTransApplied.Description = mSAProf.PeriodTransApplied.Description + " (Refund)"
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, mSAProf, "PeriodTransApplied", "Description" );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
      ZeidonStringConcat( sb_szTempString_0, 1, 0, " (Refund)", 1, 0, 255 );
   szTempString_0 = sb_szTempString_0.toString( );}
   SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szTempString_0 );
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
   //:INCLUDE mSAProf.AdjustedSATransApplied FROM mSAProf.PeriodTransApplied
   RESULT = IncludeSubobjectFromSubobject( mSAProf, "AdjustedSATransApplied", mSAProf, "PeriodTransApplied", zPOS_AFTER );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:CreateRefundByDropDate( VIEW mSAChrgT BASED ON LOD mSAChrgT,
//:                        VIEW mSAProf  BASED ON LOD mSAProf )

//:   VIEW mSAProf2 BASED ON LOD mSAProf
public int 
omSAChrgT_CreateRefundByDropDate( View     mSAChrgT,
                                  View     mSAProf )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dAmount
   double  dAmount = 0.0;
   //:STRING ( 8 )   szDroppedDate
   String   szDroppedDate = null;
   //:STRING ( 8 )   szDroppedClassStartDate
   String   szDroppedClassStartDate = null;
   //:STRING ( 300 ) szMsg
   String   szMsg = null;
   //:STRING ( 100 ) szDesc
   String   szDesc = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;


   //:// Create the Refund entry using latest Dropped Date.
   //:// In the normal case, this simply executes GenerateRefundEntry.
   //:// However, if a class has been dropped after the Add/Drop Deadline, we will not process the refund but
   //:// give the User an error message to process the entry manually.

   //:// Find latest Dropped Date.
   //:CreateViewFromView( mSAProf2, mSAProf )
   CreateViewFromView( mSAProf2, mSAProf );
   //:szDroppedDate = ""
    {StringBuilder sb_szDroppedDate;
   if ( szDroppedDate == null )
      sb_szDroppedDate = new StringBuilder( 32 );
   else
      sb_szDroppedDate = new StringBuilder( szDroppedDate );
      ZeidonStringCopy( sb_szDroppedDate, 1, 0, "", 1, 0, 9 );
   szDroppedDate = sb_szDroppedDate.toString( );}
   //:FOR EACH mSAProf2.CurrentCollegeTerm WITHIN mSAProf2.Student WHERE
   //:         mSAProf2.CurrentCollegeTerm.ID = mSAProf2.PeriodCollegeTerm.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf2, "PeriodCollegeTerm", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mSAProf2, "CurrentCollegeTerm", "ID", lTempInteger_0, "Student" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:      
      //:IF mSAProf2.Enrolled.DroppedDate > szDroppedDate
      if ( CompareAttributeToString( mSAProf2, "Enrolled", "DroppedDate", szDroppedDate ) > 0 )
      { 
         //:szDroppedDate           = mSAProf2.Enrolled.DroppedDate 
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szDroppedDate;
         if ( szDroppedDate == null )
            sb_szDroppedDate = new StringBuilder( 32 );
         else
            sb_szDroppedDate = new StringBuilder( szDroppedDate );
                   GetVariableFromAttribute( sb_szDroppedDate, mi_lTempInteger_1, 'S', 9, mSAProf2, "Enrolled", "DroppedDate", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szDroppedDate = sb_szDroppedDate.toString( );}
         //:szDroppedClassStartDate = mSAProf2.Class.ClassStartDate
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szDroppedClassStartDate;
         if ( szDroppedClassStartDate == null )
            sb_szDroppedClassStartDate = new StringBuilder( 32 );
         else
            sb_szDroppedClassStartDate = new StringBuilder( szDroppedClassStartDate );
                   GetVariableFromAttribute( sb_szDroppedClassStartDate, mi_lTempInteger_2, 'S', 9, mSAProf2, "Class", "ClassStartDate", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szDroppedClassStartDate = sb_szDroppedClassStartDate.toString( );}
      } 

      RESULT = SetCursorNextEntityByInteger( mSAProf2, "CurrentCollegeTerm", "ID", lTempInteger_0, "Student" );
      //:END
   } 

   //:END

   //:// If we haven't dropped a Class after the deadline or start of class, reverse it.
   //:IF szDroppedDate <= mSAProf2.PeriodCollegeTerm.AddDropDeadlineDate OR 
   //:   szDroppedDate <= szDroppedClassStartDate
   if ( CompareAttributeToString( mSAProf2, "PeriodCollegeTerm", "AddDropDeadlineDate", szDroppedDate ) >= 0 || ZeidonStringCompare( szDroppedDate, 1, 0, szDroppedClassStartDate, 1, 0, 9 ) >= 0 )
   { 

      //:GenerateRefundEntry( mSAChrgT, mSAProf )
      omSAChrgT_GenerateRefundEntry( mSAChrgT, mSAProf );
      //:ELSE
   } 
   else
   { 
      //:// We will give the User a message indicating classes dropped after deadline or start of class.
      //:szMsg = "Potential Refund Error for " + mSAProf.Person.dFullNameLFM + ":" + NEW_LINE +
      //:        "A class has been dropped after the Term Drop Date Deadline or Class Start Date. " +
      //:        "Refund rules are not processed automatically in this case, so charges may need to be evaluated manually."
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_3, 'S', 255, mSAProf, "Person", "dFullNameLFM", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szTempString_0 = sb_szTempString_0.toString( );}
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
            ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_0, 1, 0, 301 );
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
      //:MessageSend( mSAChrgT, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
      MessageSend( mSAChrgT, "", "Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
   } 

   //:END
   //:DropView( mSAProf2 )
   DropView( mSAProf2 );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omSAChrgT_BuildInternalRuleList( zVIEW    mSAChrgT,
                                 View     mSAChrgI )
{
   int      RESULT = 0;

   //:BuildInternalRuleList( VIEW mSAChrgT BASED ON LOD mSAChrgT,
   //:                    VIEW mSAChrgI BASED ON LOD mSAChrgI )

   //:// Build the list of Internal Rules in the object, mSAChrgI.

   //:FOR EACH mSAChrgI.InternalRule 
   RESULT = SetCursorFirstEntity( mSAChrgI, "InternalRule", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mSAChrgI.InternalRule NONE 
      RESULT = DeleteEntity( mSAChrgI, "InternalRule", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSAChrgI, "InternalRule", "" );
   } 

   //:END

   //:CreateInternalRule( mSAChrgT, mSAChrgI, "Graduate Charge/Credit" )
   omSAChrgT_CreateInternalRule( mSAChrgT, mSAChrgI, "Graduate Charge/Credit" );
   //:CreateInternalRule( mSAChrgT, mSAChrgI, "Health Insurance" )
   omSAChrgT_CreateInternalRule( mSAChrgT, mSAChrgI, "Health Insurance" );
   //:CreateInternalRule( mSAChrgT, mSAChrgI, "Undergrad Tuition Block Charge" )
   omSAChrgT_CreateInternalRule( mSAChrgT, mSAChrgI, "Undergrad Tuition Block Charge" );
   //:CreateInternalRule( mSAChrgT, mSAChrgI, "Summer Tuition" )
   omSAChrgT_CreateInternalRule( mSAChrgT, mSAChrgI, "Summer Tuition" );
   //:CreateInternalRule( mSAChrgT, mSAChrgI, "DL Tuition" )
   omSAChrgT_CreateInternalRule( mSAChrgT, mSAChrgI, "DL Tuition" );
   //:CreateInternalRule( mSAChrgT, mSAChrgI, "Meal Plan" )
   omSAChrgT_CreateInternalRule( mSAChrgT, mSAChrgI, "Meal Plan" );
   //:CreateInternalRule( mSAChrgT, mSAChrgI, "Residency" )
   omSAChrgT_CreateInternalRule( mSAChrgT, mSAChrgI, "Residency" );
   //:CreateInternalRule( mSAChrgT, mSAChrgI, "UNIV110 Fees" )
   omSAChrgT_CreateInternalRule( mSAChrgT, mSAChrgI, "UNIV110 Fees" );
   //:CreateInternalRule( mSAChrgT, mSAChrgI, "Independent Study" )
   omSAChrgT_CreateInternalRule( mSAChrgT, mSAChrgI, "Independent Study" );
   //:OrderEntityForView( mSAChrgI, "InternalRule", "Name A" )
   OrderEntityForView( mSAChrgI, "InternalRule", "Name A" );
   //:SET CURSOR FIRST mSAChrgI.InternalRule 
   RESULT = SetCursorFirstEntity( mSAChrgI, "InternalRule", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildInternalRuleVars( VIEW mSAChrgT BASED ON LOD mSAChrgT,
//:                       VIEW mSAChrgI BASED ON LOD mSAChrgI )

//:   STRING ( 50 ) szInternalRuleName
public int 
omSAChrgT_BuildInternalRuleVars( zVIEW    mSAChrgT,
                                 View     mSAChrgI )
{
   String   szInternalRuleName = null;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;


   //:// Make sure that any mSAChrgI.InternalRuleVariableDef entries are properly set up for the
   //:// InternalProcessRule specified. 

   //:szInternalRuleName = mSAChrgI.SATransactionCode.InternalRuleName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szInternalRuleName;
   if ( szInternalRuleName == null )
      sb_szInternalRuleName = new StringBuilder( 32 );
   else
      sb_szInternalRuleName = new StringBuilder( szInternalRuleName );
       GetVariableFromAttribute( sb_szInternalRuleName, mi_lTempInteger_0, 'S', 51, mSAChrgI, "SATransactionCode", "InternalRuleName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szInternalRuleName = sb_szInternalRuleName.toString( );}


   //:// GRADUATE CHARGE/CREDIT

   //:IF szInternalRuleName = "Graduate Charge/Credit"
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "Graduate Charge/Credit", 1, 0, 51 ) == 0 )
   { 
      //:// First make sure there are no incorrect InternalRuleVariableDef entries.
      //:FOR EACH mSAChrgI.InternalRuleVariableDef 
      RESULT = SetCursorFirstEntity( mSAChrgI, "InternalRuleVariableDef", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSAChrgI.InternalRuleVariableDef.Name != "Charge/Credit"
         if ( CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "Charge/Credit" ) != 0 )
         { 
            //:DELETE ENTITY mSAChrgI.InternalRuleVariableDef NONE 
            RESULT = DeleteEntity( mSAChrgI, "InternalRuleVariableDef", zREPOS_NONE );
         } 

         RESULT = SetCursorNextEntity( mSAChrgI, "InternalRuleVariableDef", "" );
         //:END
      } 

      //:END
      //:// Second, make sure valid values are there.
      //:SET CURSOR FIRST mSAChrgI.InternalRuleVariableDef WHERE mSAChrgI.InternalRuleVariableDef.Name = "Charge/Credit"
      RESULT = SetCursorFirstEntityByString( mSAChrgI, "InternalRuleVariableDef", "Name", "Charge/Credit", "" );
      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:CREATE ENTITY mSAChrgI.InternalRuleVariableDef 
         RESULT = CreateEntity( mSAChrgI, "InternalRuleVariableDef", zPOS_AFTER );
         //:mSAChrgI.InternalRuleVariableDef.Name = "Charge/Credit"
         SetAttributeFromString( mSAChrgI, "InternalRuleVariableDef", "Name", "Charge/Credit" );
      } 

      //:END
   } 

   //:END


   //:// UNDERGRAD TUITION BLOCK CHARGE

   //:IF szInternalRuleName = "Undergrad Tuition Block Charge"
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "Undergrad Tuition Block Charge", 1, 0, 51 ) == 0 )
   { 
      //:// First make sure there are no incorrect InternalRuleVariableDef entries.
      //:FOR EACH mSAChrgI.InternalRuleVariableDef 
      RESULT = SetCursorFirstEntity( mSAChrgI, "InternalRuleVariableDef", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSAChrgI.InternalRuleVariableDef.Name != "PT Chg/CR" AND 
         //:   mSAChrgI.InternalRuleVariableDef.Name != "FT Block Chg" AND 
         //:   mSAChrgI.InternalRuleVariableDef.Name != "OvLd Chg/CR" AND 
         //:   mSAChrgI.InternalRuleVariableDef.Name != "PBS Disc/CR" AND
         //:   mSAChrgI.InternalRuleVariableDef.Name != "SrCit Chg/CR"
         if ( CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "PT Chg/CR" ) != 0 && CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "FT Block Chg" ) != 0 &&
              CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "OvLd Chg/CR" ) != 0 && CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "PBS Disc/CR" ) != 0 &&
              CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "SrCit Chg/CR" ) != 0 )
         { 

            //:DELETE ENTITY mSAChrgI.InternalRuleVariableDef NONE 
            RESULT = DeleteEntity( mSAChrgI, "InternalRuleVariableDef", zREPOS_NONE );
         } 

         RESULT = SetCursorNextEntity( mSAChrgI, "InternalRuleVariableDef", "" );
         //:END
      } 

      //:END

      //:// Make sure all charges exist.
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "PT Chg/CR" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "PT Chg/CR" );
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "FT Block Chg" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "FT Block Chg" );
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "OvLd Chg/CR" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "OvLd Chg/CR" );
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "PBS Disc/CR" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "PBS Disc/CR" );
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "SrCit Chg/CR" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "SrCit Chg/CR" );
   } 


   //:END


   //:// SUMMER TUITION

   //:IF szInternalRuleName = "Summer Tuition"
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "Summer Tuition", 1, 0, 51 ) == 0 )
   { 
      //:// First make sure there are no incorrect InternalRuleVariableDef entries.
      //:FOR EACH mSAChrgI.InternalRuleVariableDef 
      RESULT = SetCursorFirstEntity( mSAChrgI, "InternalRuleVariableDef", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSAChrgI.InternalRuleVariableDef.Name != "S1-19 Chg/CR" AND 
         //:   mSAChrgI.InternalRuleVariableDef.Name != "S20 Chg/CR" AND 
         //:   mSAChrgI.InternalRuleVariableDef.Name != "Grad Chg/CR" AND 
         //:   mSAChrgI.InternalRuleVariableDef.Name != "Field Chg/CR" AND
         //:   mSAChrgI.InternalRuleVariableDef.Name != "PBS Discount" 
         if ( CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "S1-19 Chg/CR" ) != 0 && CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "S20 Chg/CR" ) != 0 &&
              CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "Grad Chg/CR" ) != 0 && CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "Field Chg/CR" ) != 0 &&
              CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "PBS Discount" ) != 0 )
         { 

            //:DELETE ENTITY mSAChrgI.InternalRuleVariableDef NONE 
            RESULT = DeleteEntity( mSAChrgI, "InternalRuleVariableDef", zREPOS_NONE );
         } 

         RESULT = SetCursorNextEntity( mSAChrgI, "InternalRuleVariableDef", "" );
         //:END
      } 

      //:END

      //:// Make sure all charges exist.
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "S1-19 Chg/CR" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "S1-19 Chg/CR" );
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "S20 Chg/CR" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "S20 Chg/CR" );
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "Grad Chg/CR" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "Grad Chg/CR" );
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "Field Chg/CR" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "Field Chg/CR" );
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "PBS Discount" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "PBS Discount" );
   } 


   //:END


   //:// DL TUITION

   //:IF szInternalRuleName = "DL Tuition"
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "DL Tuition", 1, 0, 51 ) == 0 )
   { 
      //:// First make sure there are no incorrect InternalRuleVariableDef entries.
      //:FOR EACH mSAChrgI.InternalRuleVariableDef 
      RESULT = SetCursorFirstEntity( mSAChrgI, "InternalRuleVariableDef", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSAChrgI.InternalRuleVariableDef.Name != "SUCT Chg" AND 
         //:   mSAChrgI.InternalRuleVariableDef.Name != "CERT Chg/CR" AND 
         //:   mSAChrgI.InternalRuleVariableDef.Name != "Grad Chg/CR" AND 
         //:   mSAChrgI.InternalRuleVariableDef.Name != "DL Chg/CR" AND 
         //:   mSAChrgI.InternalRuleVariableDef.Name != "PBS Discount"
         if ( CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "SUCT Chg" ) != 0 && CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "CERT Chg/CR" ) != 0 &&
              CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "Grad Chg/CR" ) != 0 && CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "DL Chg/CR" ) != 0 &&
              CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "PBS Discount" ) != 0 )
         { 

            //:DELETE ENTITY mSAChrgI.InternalRuleVariableDef NONE 
            RESULT = DeleteEntity( mSAChrgI, "InternalRuleVariableDef", zREPOS_NONE );
         } 

         RESULT = SetCursorNextEntity( mSAChrgI, "InternalRuleVariableDef", "" );
         //:END
      } 

      //:END

      //:// Make sure all charges exist, plus PBS Discount.
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "SUCT Chg" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "SUCT Chg" );
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "CERT Chg/CR" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "CERT Chg/CR" );
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "Grad Chg/CR" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "Grad Chg/CR" );
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "DL Chg/CR" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "DL Chg/CR" );
      //:CreateInternalRuleVar( mSAChrgT, mSAChrgI, "PBS Discount" )
      omSAChrgT_CreateInternalRuleVar( mSAChrgT, mSAChrgI, "PBS Discount" );
   } 


   //:END

   //:// INDEPENDENT STUDY

   //:IF szInternalRuleName = "Independent Study"
   if ( ZeidonStringCompare( szInternalRuleName, 1, 0, "Independent Study", 1, 0, 51 ) == 0 )
   { 
      //:// First make sure there are no incorrect InternalRuleVariableDef entries.
      //:FOR EACH mSAChrgI.InternalRuleVariableDef 
      RESULT = SetCursorFirstEntity( mSAChrgI, "InternalRuleVariableDef", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSAChrgI.InternalRuleVariableDef.Name != "Charge/Credit"
         if ( CompareAttributeToString( mSAChrgI, "InternalRuleVariableDef", "Name", "Charge/Credit" ) != 0 )
         { 
            //:DELETE ENTITY mSAChrgI.InternalRuleVariableDef NONE 
            RESULT = DeleteEntity( mSAChrgI, "InternalRuleVariableDef", zREPOS_NONE );
         } 

         RESULT = SetCursorNextEntity( mSAChrgI, "InternalRuleVariableDef", "" );
         //:END
      } 

      //:END
      //:// Second, make sure valid values are there.
      //:SET CURSOR FIRST mSAChrgI.InternalRuleVariableDef WHERE mSAChrgI.InternalRuleVariableDef.Name = "Charge/Credit"
      RESULT = SetCursorFirstEntityByString( mSAChrgI, "InternalRuleVariableDef", "Name", "Charge/Credit", "" );
      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:CREATE ENTITY mSAChrgI.InternalRuleVariableDef 
         RESULT = CreateEntity( mSAChrgI, "InternalRuleVariableDef", zPOS_AFTER );
         //:mSAChrgI.InternalRuleVariableDef.Name = "Charge/Credit"
         SetAttributeFromString( mSAChrgI, "InternalRuleVariableDef", "Name", "Charge/Credit" );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:Rule_UnderGradTuition( VIEW mSAChrgT BASED ON LOD mSAChrgT,
//:                        VIEW mSAProf  BASED ON LOD mSAProf,
//:                        STRING ( 1 )   szRefundFlag )

//:   VIEW wXferO      REGISTERED AS wXferO
private int 
omSAChrgT_Rule_UnderGradTuition( View     mSAChrgT,
                                 View     mSAProf,
                                 String   szRefundFlag )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mCollegeLST BASED ON LOD  mCollege
   zVIEW    mCollegeLST = new zVIEW( );
   //:VIEW mSAChrgT2   BASED ON LOD  mSAChrgT
   zVIEW    mSAChrgT2 = new zVIEW( );
   //:STRING ( 1 )   szFoundRuleFlag
   String   szFoundRuleFlag = null;
   //:STRING ( 300 ) szMsg
   String   szMsg = null;
   //:STRING ( 300 ) szDesc
   String   szDesc = null;
   //:STRING ( 20 )  szDisplayCredits
   String   szDisplayCredits = null;
   //:STRING ( 50 )  szInternalRuleName
   String   szInternalRuleName = null;
   //:STRING ( 50 )  szTransactionCode
   String   szTransactionCode = null;
   //:STRING ( 1 )   szDroppedAllClassesFlag
   String   szDroppedAllClassesFlag = null;
   //:STRING ( 10 )  szCourseNumber
   String   szCourseNumber = null;
   //:STRING ( 4 )   szCourseNumber4
   String   szCourseNumber4 = null;
   //:STRING ( 20 )  szDecimal
   String   szDecimal = null;
   //:DECIMAL        dChargePerCreditHour
   double  dChargePerCreditHour = 0.0;
   //:DECIMAL        dOverloadCredits
   double  dOverloadCredits = 0.0;
   //:DECIMAL        dTotalCredits
   double  dTotalCredits = 0.0;
   //:DECIMAL        dBlockCharge
   double  dBlockCharge = 0.0;
   //:DECIMAL        dTotalESL_Credits
   double  dTotalESL_Credits = 0.0;
   //:DECIMAL        dTotalPBS_Credits
   double  dTotalPBS_Credits = 0.0;
   //:DECIMAL        dTotalOverloadCredits
   double  dTotalOverloadCredits = 0.0;
   //:DECIMAL        dTotalAuditCredits
   double  dTotalAuditCredits = 0.0;
   //:DECIMAL        dNetAuditCredits
   double  dNetAuditCredits = 0.0;
   //:DECIMAL        dRegularOverloadCredits
   double  dRegularOverloadCredits = 0.0;
   //:DECIMAL        dAuditOverloadCredits
   double  dAuditOverloadCredits = 0.0;
   //:DECIMAL        dTotalLessAuditCredits
   double  dTotalLessAuditCredits = 0.0;
   //:DECIMAL        dTotalCharge
   double  dTotalCharge = 0.0;
   //:DECIMAL        dESL_Discount
   double  dESL_Discount = 0.0;
   //:DECIMAL        dTotalBlockPBS_Credits
   double  dTotalBlockPBS_Credits = 0.0;
   //:DECIMAL        dTotalNonPBS_Credits
   double  dTotalNonPBS_Credits = 0.0;
   //:DECIMAL        dTotalOverloadPBS_Credits
   double  dTotalOverloadPBS_Credits = 0.0;
   //:INTEGER        lESL_Discount
   int      lESL_Discount = 0;
   //:SHORT          nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   int      lTempInteger_3 = 0;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;
   int      lTempInteger_4 = 0;
   double  dTempDecimal_4 = 0.0;
   double  dTempDecimal_5 = 0.0;
   double  dTempDecimal_6 = 0.0;
   String   szTempString_0 = null;
   int      lTempInteger_5 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mSAChrgT, zLEVEL_TASK );

   //:// This is only executed for Fall and Spring Terms (not Summer).
   //:IF mSAProf.PeriodCollegeTerm.Semester = "04"
   if ( CompareAttributeToString( mSAProf, "PeriodCollegeTerm", "Semester", "04" ) == 0 )
   { 
      //:RETURN 
      if(8==8)return( 0 );
   } 

   //:END

   //:// Exit if the Student is a Distance Learning student.
   //://SET CURSOR FIRST mSAProf.Category WHERE mSAProf.Category.Name = "Distance Learning"
   //://                                      OR mSAProf.Category.Name = "ADP Student"
   //://IF RESULT >= zCURSOR_SET
   //:IF mSAProf.Student.AdultStudiesFlag = "Y"
   if ( CompareAttributeToString( mSAProf, "Student", "AdultStudiesFlag", "Y" ) == 0 )
   { 
      //:RETURN 
      if(8==8)return( 0 );
   } 

   //:END

   //:// Make sure list of colleges exists for determining grad/undergrad courses.
   //:GET VIEW mCollegeLST NAMED "mCollegeLST"
   RESULT = GetViewByName( mCollegeLST, "mCollegeLST", mSAChrgT, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mCollegeLST RootOnlyMultiple
      RESULT = ActivateObjectInstance( mCollegeLST, "mCollege", mSAChrgT, 0, zACTIVATE_ROOTONLY_MULTIPLE );
      //:NAME VIEW mCollegeLST "mCollegeLST"
      SetNameForView( mCollegeLST, "mCollegeLST", null, zLEVEL_TASK );
   } 

   //:END

   //:szInternalRuleName = mSAChrgT.SATransactionCode.InternalRuleName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szInternalRuleName;
   if ( szInternalRuleName == null )
      sb_szInternalRuleName = new StringBuilder( 32 );
   else
      sb_szInternalRuleName = new StringBuilder( szInternalRuleName );
       GetVariableFromAttribute( sb_szInternalRuleName, mi_lTempInteger_0, 'S', 51, mSAChrgT, "SATransactionCode", "InternalRuleName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szInternalRuleName = sb_szInternalRuleName.toString( );}
   //:szTransactionCode  = mSAChrgT.SATransactionCode.TransactionCode
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szTransactionCode;
   if ( szTransactionCode == null )
      sb_szTransactionCode = new StringBuilder( 32 );
   else
      sb_szTransactionCode = new StringBuilder( szTransactionCode );
       GetVariableFromAttribute( sb_szTransactionCode, mi_lTempInteger_1, 'S', 51, mSAChrgT, "SATransactionCode", "TransactionCode", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szTransactionCode = sb_szTransactionCode.toString( );}

   //:// If this is a "Student Missionary/Task Force" student, then they cannot be taking any Classes except
   //:// for Courses UNIV103 and UNIV211. Their charge is a single block charge of 300.
   //:SET CURSOR FIRST mSAProf.Category WHERE mSAProf.Category.Name = "Student Missionary/Task Force"
   RESULT = SetCursorFirstEntityByString( mSAProf, "Category", "Name", "Student Missionary/Task Force", "" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 

      //:// STUDENT MISSIONARY/TASK FOURCE

      //:// Check if any Class is other than UNIV103 or UNIV211 .
      //:FOR EACH mSAProf.TermEnrolled 
      RESULT = SetCursorFirstEntity( mSAProf, "TermEnrolled", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSAProf.TermCourse.Number != "UNIV103" AND 
         //:   mSAProf.TermCourse.Number != "UNIV211" AND 
         //:   mSAProf.TermEnrolled.Status = "T"
         if ( CompareAttributeToString( mSAProf, "TermCourse", "Number", "UNIV103" ) != 0 && CompareAttributeToString( mSAProf, "TermCourse", "Number", "UNIV211" ) != 0 && CompareAttributeToString( mSAProf, "TermEnrolled", "Status", "T" ) == 0 )
         { 

            //:szMsg = "A 'Student Missionary/Task Force' student is only allowed to take courses UNIV103 and UNIV211."
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringCopy( sb_szMsg, 1, 0, "A 'Student Missionary/Task Force' student is only allowed to take courses UNIV103 and UNIV211.", 1, 0, 301 );
            szMsg = sb_szMsg.toString( );}
            //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         RESULT = SetCursorNextEntity( mSAProf, "TermEnrolled", "" );
         //:END
      } 

      //:END

      //:// Generate Charge
      //:dTotalCharge = 300
      dTotalCharge = 300;
      //:szDesc = "SM/TF Undergrad Tuition"
       {StringBuilder sb_szDesc;
      if ( szDesc == null )
         sb_szDesc = new StringBuilder( 32 );
      else
         sb_szDesc = new StringBuilder( szDesc );
            ZeidonStringCopy( sb_szDesc, 1, 0, "SM/TF Undergrad Tuition", 1, 0, 301 );
      szDesc = sb_szDesc.toString( );}
      //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc )
      nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc );

      //:RETURN 
      if(8==8)return( 0 );
   } 

   //:END

   //:// If this is a Senior Citizen, simply charge the Senior Citizen Per Credit price and return.
   //:SET CURSOR FIRST mSAProf.Category WHERE mSAProf.Category.Name = "Senior Citizen"
   RESULT = SetCursorFirstEntityByString( mSAProf, "Category", "Name", "Senior Citizen", "" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 

      //:// SENIOR CITIZEN

      //:// Note that we are processing Audit the same as non Audit here.
      //:dTotalCredits = 0
      dTotalCredits = 0;
      //:FOR EACH mSAProf.TermEnrolled
      RESULT = SetCursorFirstEntity( mSAProf, "TermEnrolled", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSAProf.TermEnrolled.Status != "D"
         if ( CompareAttributeToString( mSAProf, "TermEnrolled", "Status", "D" ) != 0 )
         { 
            //:SET CURSOR FIRST mCollegeLST.College WHERE mCollegeLST.College.ID = mSAProf.TermCourseCollege.ID 
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, mSAProf, "TermCourseCollege", "ID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mCollegeLST, "College", "ID", lTempInteger_2, "" );
            //:IF mCollegeLST.College.Type = "U"
            if ( CompareAttributeToString( mCollegeLST, "College", "Type", "U" ) == 0 )
            { 
               //:IF mSAProf.TermEnrolled.TakingClassType = "A"
               if ( CompareAttributeToString( mSAProf, "TermEnrolled", "TakingClassType", "A" ) == 0 )
               { 
                  //:// Auditing course.
                  //:dTotalCredits = dTotalCredits + mSAProf.TermClass.CreditHours 
                  {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                     GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf, "TermClass", "CreditHours" );
                  dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                  dTotalCredits = dTotalCredits + dTempDecimal_0;
                  //:ELSE
               } 
               else
               { 
                  //:// Taking course for credit.
                  //:dTotalCredits = dTotalCredits + mSAProf.TermEnrolled.CreditHours
                  {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                                     GetDecimalFromAttribute( md_dTempDecimal_1, mSAProf, "TermEnrolled", "CreditHours" );
                  dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
                  dTotalCredits = dTotalCredits + dTempDecimal_1;
               } 

               //:END
            } 

            //:END
         } 

         RESULT = SetCursorNextEntity( mSAProf, "TermEnrolled", "" );
         //:END
      } 

      //:END

      //:// Student is Senior Citizen, charge per credit hour at 'SrCit Chg/CR' rate.
      //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "SrCit Chg/CR"
      RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "SrCit Chg/CR", "" );
      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:szMsg = "No Rule Variable match on Tuition 'SrCit Chg/CR'"
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Tuition 'SrCit Chg/CR'", 1, 0, 301 );
         szMsg = sb_szMsg.toString( );}
         //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END

      //:// Generate Charge
      //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
      {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
             GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
      dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
      //:dTotalCharge = dTotalCredits * dChargePerCreditHour
      dTotalCharge = dTotalCredits * dChargePerCreditHour;
      //:szDisplayCredits = dTotalCredits
       {StringBuilder sb_szDisplayCredits;
      if ( szDisplayCredits == null )
         sb_szDisplayCredits = new StringBuilder( 32 );
      else
         sb_szDisplayCredits = new StringBuilder( szDisplayCredits );
            ZeidonStringConvertFromNumber( sb_szDisplayCredits, 1, 0, 20, 0, dTotalCredits, "D" );
      szDisplayCredits = sb_szDisplayCredits.toString( );}
      //:szDesc = "Sr. Cit. Undergrad Tuition - " + szDisplayCredits  + " CR"
       {StringBuilder sb_szDesc;
      if ( szDesc == null )
         sb_szDesc = new StringBuilder( 32 );
      else
         sb_szDesc = new StringBuilder( szDesc );
            ZeidonStringCopy( sb_szDesc, 1, 0, "Sr. Cit. Undergrad Tuition - ", 1, 0, 301 );
      szDesc = sb_szDesc.toString( );}
       {StringBuilder sb_szDesc;
      if ( szDesc == null )
         sb_szDesc = new StringBuilder( 32 );
      else
         sb_szDesc = new StringBuilder( szDesc );
            ZeidonStringConcat( sb_szDesc, 1, 0, szDisplayCredits, 1, 0, 301 );
      szDesc = sb_szDesc.toString( );}
       {StringBuilder sb_szDesc;
      if ( szDesc == null )
         sb_szDesc = new StringBuilder( 32 );
      else
         sb_szDesc = new StringBuilder( szDesc );
            ZeidonStringConcat( sb_szDesc, 1, 0, " CR", 1, 0, 301 );
      szDesc = sb_szDesc.toString( );}
      //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc )
      nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc );

      //:RETURN 
      if(8==8)return( 0 );
   } 

   //:END

   //:// Compute total Undergrad, PBS Discount and ESL Discount Credits.
   //:dTotalESL_Credits  = 0
   dTotalESL_Credits = 0;
   //:dTotalPBS_Credits  = 0
   dTotalPBS_Credits = 0;
   //:dTotalCredits      = 0
   dTotalCredits = 0;
   //:dTotalAuditCredits = 0
   dTotalAuditCredits = 0;
   //:FOR EACH mSAProf.TermEnrolled WHERE mSAProf.TermEnrolled.Status = "T" OR 
   //:                                    mSAProf.TermEnrolled.Status = "C"
   RESULT = SetCursorFirstEntity( mSAProf, "TermEnrolled", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( mSAProf, "TermEnrolled", "Status", "T" ) == 0 || CompareAttributeToString( mSAProf, "TermEnrolled", "Status", "C" ) == 0 )
      { 

         //:// Adult Studies Classes are Distance Learning.
         //:IF mSAProf.TermClass.AdultStudiesFlag != "Y"
         if ( CompareAttributeToString( mSAProf, "TermClass", "AdultStudiesFlag", "Y" ) != 0 )
         { 
            //:// ESL Discount Credits.
            //:szCourseNumber = mSAProf.TermCourse.Number 
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szCourseNumber;
            if ( szCourseNumber == null )
               sb_szCourseNumber = new StringBuilder( 32 );
            else
               sb_szCourseNumber = new StringBuilder( szCourseNumber );
                         GetVariableFromAttribute( sb_szCourseNumber, mi_lTempInteger_3, 'S', 11, mSAProf, "TermCourse", "Number", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szCourseNumber = sb_szCourseNumber.toString( );}
            //:szCourseNumber4 = szCourseNumber
             {StringBuilder sb_szCourseNumber4;
            if ( szCourseNumber4 == null )
               sb_szCourseNumber4 = new StringBuilder( 32 );
            else
               sb_szCourseNumber4 = new StringBuilder( szCourseNumber4 );
                        ZeidonStringCopy( sb_szCourseNumber4, 1, 0, szCourseNumber, 1, 0, 5 );
            szCourseNumber4 = sb_szCourseNumber4.toString( );}
            //:IF szCourseNumber4 = "ESOL"
            if ( ZeidonStringCompare( szCourseNumber4, 1, 0, "ESOL", 1, 0, 5 ) == 0 )
            { 
               //:dTotalESL_Credits = dTotalESL_Credits + mSAProf.TermEnrolled.CreditHours 
               {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                               GetDecimalFromAttribute( md_dTempDecimal_2, mSAProf, "TermEnrolled", "CreditHours" );
               dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
               dTotalESL_Credits = dTotalESL_Credits + dTempDecimal_2;
            } 

            //:END

            //:// PBS Discount Credits.
            //:// The rules for a PBS Student are found in the operation, CheckPBS_ForClass, which returns a "1" if the Student
            //:// and Class are eligible for PBS Discount.
            //:nRC = CheckPBS_ForClass( mSAChrgT, mSAProf )
            nRC = omSAChrgT_CheckPBS_ForClass( mSAChrgT, mSAProf );
            //:IF nRC = 1 
            if ( nRC == 1 )
            { 
               //:dTotalPBS_Credits = dTotalPBS_Credits + mSAProf.TermEnrolled.CreditHours 
               {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                               GetDecimalFromAttribute( md_dTempDecimal_3, mSAProf, "TermEnrolled", "CreditHours" );
               dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
               dTotalPBS_Credits = dTotalPBS_Credits + dTempDecimal_3;
            } 

            //:END

            //:// Regular Undergrad Credits.
            //:SET CURSOR FIRST mCollegeLST.College WHERE mCollegeLST.College.ID = mSAProf.TermCourseCollege.ID 
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                         GetIntegerFromAttribute( mi_lTempInteger_4, mSAProf, "TermCourseCollege", "ID" );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mCollegeLST, "College", "ID", lTempInteger_4, "" );
            //:IF mCollegeLST.College.Type = "U"
            if ( CompareAttributeToString( mCollegeLST, "College", "Type", "U" ) == 0 )
            { 
               //:IF mSAProf.TermEnrolled.TakingClassType = "A"
               if ( CompareAttributeToString( mSAProf, "TermEnrolled", "TakingClassType", "A" ) == 0 )
               { 
                  //:// Audit Class uses Credits from Class entry.
                  //:dTotalCredits = dTotalCredits + mSAProf.TermClass.CreditHours 
                  {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                                     GetDecimalFromAttribute( md_dTempDecimal_4, mSAProf, "TermClass", "CreditHours" );
                  dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
                  dTotalCredits = dTotalCredits + dTempDecimal_4;
                  //:ELSE
               } 
               else
               { 
                  //:// Regular Class uses Credits from Enrolled entry.
                  //:dTotalCredits = dTotalCredits + mSAProf.TermEnrolled.CreditHours
                  {MutableDouble md_dTempDecimal_5 = new MutableDouble( dTempDecimal_5 );
                                     GetDecimalFromAttribute( md_dTempDecimal_5, mSAProf, "TermEnrolled", "CreditHours" );
                  dTempDecimal_5 = md_dTempDecimal_5.doubleValue( );}
                  dTotalCredits = dTotalCredits + dTempDecimal_5;
               } 

               //:END
            } 

            //:END

            //:// Audit Credits.
            //:IF mSAProf.TermEnrolled.TakingClassType = "A" AND 
            //:szCourseNumber4 != "EOSL" AND szCourseNumber4 != "NURS"
            if ( CompareAttributeToString( mSAProf, "TermEnrolled", "TakingClassType", "A" ) == 0 && ZeidonStringCompare( szCourseNumber4, 1, 0, "EOSL", 1, 0, 5 ) != 0 && ZeidonStringCompare( szCourseNumber4, 1, 0, "NURS", 1, 0, 5 ) != 0 )
            { 

               //://dTotalAuditCredits = dTotalAuditCredits + mSAProf.TermEnrolled.CreditHours
               //:dTotalAuditCredits = dTotalAuditCredits + mSAProf.TermClass.CreditHours 
               {MutableDouble md_dTempDecimal_6 = new MutableDouble( dTempDecimal_6 );
                               GetDecimalFromAttribute( md_dTempDecimal_6, mSAProf, "TermClass", "CreditHours" );
               dTempDecimal_6 = md_dTempDecimal_6.doubleValue( );}
               dTotalAuditCredits = dTotalAuditCredits + dTempDecimal_6;
            } 

            //:END
         } 

      } 

      RESULT = SetCursorNextEntity( mSAProf, "TermEnrolled", "" );
      //:END
   } 

   //:   
   //:END

   //:// Compute Overload Credits. Don't include Audit Credits.
   //:dTotalLessAuditCredits = dTotalCredits - dTotalAuditCredits
   dTotalLessAuditCredits = dTotalCredits - dTotalAuditCredits;
   //:IF dTotalLessAuditCredits > 17
   if ( dTotalLessAuditCredits > 17 )
   { 
      //:dTotalOverloadCredits = dTotalLessAuditCredits - 17
      dTotalOverloadCredits = dTotalLessAuditCredits - 17;

      //:ELSE
   } 
   else
   { 
      //:dTotalOverloadCredits = 0
      dTotalOverloadCredits = 0;
   } 

   //:END

   //:// If ESL credits >= 12, student gets ESL Discount of 38% on block price.
   //:IF dTotalESL_Credits >= 12
   if ( dTotalESL_Credits >= 12 )
   { 

      //:// Process resulting Charge or Refund.
      //:IF szRefundFlag = ""
      if ( ZeidonStringCompare( szRefundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
      { 

         //:// Put out regular Block Credit charge line.
         //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "FT Block Chg"
         RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "FT Block Chg", "" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:szMsg = "No Rule Variable match on Tuition 'FT Block Chg'"
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Tuition 'FT Block Chg'", 1, 0, 301 );
            szMsg = sb_szMsg.toString( );}
            //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
         //:dBlockCharge = mSAChrgT.SAInternalRuleVariable.Value
         {MutableDouble md_dBlockCharge = new MutableDouble( dBlockCharge );
                   GetDecimalFromAttribute( md_dBlockCharge, mSAChrgT, "SAInternalRuleVariable", "Value" );
         dBlockCharge = md_dBlockCharge.doubleValue( );}
         //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dBlockCharge, "Undergrad Block Tuition" )
         nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dBlockCharge, "Undergrad Block Tuition" );

         //:// Put out ESL Discount line.
         //:dESL_Discount = dBlockCharge * .38      // Discount is 38%
         dESL_Discount = dBlockCharge * .38;
         //:// Round discount up to whole dollar.
         //:dESL_Discount = dESL_Discount + .5
         dESL_Discount = dESL_Discount + .5;
         //:lESL_Discount = dESL_Discount
         lESL_Discount = (int) dESL_Discount;
         //:dESL_Discount = lESL_Discount
         dESL_Discount = lESL_Discount;
         //:dESL_Discount = 0 - dESL_Discount       // Discount is negative of charge.
         dESL_Discount = 0 - dESL_Discount;

         //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dESL_Discount, "ESL Block Tuition Discount" )
         nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dESL_Discount, "ESL Block Tuition Discount" );

         //:ELSE
      } 
      else
      { 
      } 

      //:   // Refund.
      //:   // Because of the complexities with the discounts, we will not process refunds automatically at this point.
      //:   
      //:END


      //:ELSE 
   } 
   else
   { 

      //:// Regular UNDERGRAD Charges.

      //:// Process resulting Charge or Refund.
      //:IF szRefundFlag = ""
      if ( ZeidonStringCompare( szRefundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
      { 

         //:// Only continue if there are Undergrad Credits.
         //:IF dTotalCredits > 0
         if ( dTotalCredits > 0 )
         { 

            //:// If the Student is < min full time, they are simply charged an amount per credit hour.
            //:IF dTotalCredits < 12
            if ( dTotalCredits < 12 )
            { 

               //:// Student is part time, charge per credit hour.

               //:// Separate Audited Credits from regular credits since they are charged at different rates.
               //:dTotalLessAuditCredits = dTotalCredits - dTotalAuditCredits
               dTotalLessAuditCredits = dTotalCredits - dTotalAuditCredits;

               //:// Regular Charge
               //:IF dTotalLessAuditCredits > 0
               if ( dTotalLessAuditCredits > 0 )
               { 
                  //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "PT Chg/CR"
                  RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "PT Chg/CR", "" );
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:szMsg = "No Rule Variable match on Tuition 'PT Chg/CR'"
                      {StringBuilder sb_szMsg;
                     if ( szMsg == null )
                        sb_szMsg = new StringBuilder( 32 );
                     else
                        sb_szMsg = new StringBuilder( szMsg );
                                          ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Tuition 'PT Chg/CR'", 1, 0, 301 );
                     szMsg = sb_szMsg.toString( );}
                     //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                     MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                     //:RETURN -1
                     if(8==8)return( -1 );
                  } 

                  //:END

                  //:// Generate Charge
                  //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
                  {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                     GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
                  dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                  //:dTotalCharge = dTotalLessAuditCredits * dChargePerCreditHour
                  dTotalCharge = dTotalLessAuditCredits * dChargePerCreditHour;
                  //:szDisplayCredits = dTotalLessAuditCredits
                   {StringBuilder sb_szDisplayCredits;
                  if ( szDisplayCredits == null )
                     sb_szDisplayCredits = new StringBuilder( 32 );
                  else
                     sb_szDisplayCredits = new StringBuilder( szDisplayCredits );
                                    ZeidonStringConvertFromNumber( sb_szDisplayCredits, 1, 0, 20, 0, dTotalLessAuditCredits, "D" );
                  szDisplayCredits = sb_szDisplayCredits.toString( );}
                  //:szDesc = "P/T Undergrad Tuition - " + szDisplayCredits  + " CR"
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringCopy( sb_szDesc, 1, 0, "P/T Undergrad Tuition - ", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, szDisplayCredits, 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, " CR", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                  //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc )
                  nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc );

                  //:// PBS Discount by number credits.
                  //:IF dTotalPBS_Credits > 0
                  if ( dTotalPBS_Credits > 0 )
                  { 
                     //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "PBS Disc/CR"
                     RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "PBS Disc/CR", "" );
                     //:IF RESULT < zCURSOR_SET
                     if ( RESULT < zCURSOR_SET )
                     { 
                        //:szMsg = "No Rule Variable match on Tuition 'PBS Disc/CR'"
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Tuition 'PBS Disc/CR'", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                        //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                        MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                        //:RETURN -1
                        if(8==8)return( -1 );
                     } 

                     //:END
                     //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
                     {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                           GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
                     dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                     //:dTotalCharge = dTotalPBS_Credits * dChargePerCreditHour
                     dTotalCharge = dTotalPBS_Credits * dChargePerCreditHour;
                     //:dTotalCharge = 0 - dTotalCharge     // Discount is negative of charge.
                     dTotalCharge = 0 - dTotalCharge;
                     //:szDisplayCredits = dTotalPBS_Credits
                      {StringBuilder sb_szDisplayCredits;
                     if ( szDisplayCredits == null )
                        sb_szDisplayCredits = new StringBuilder( 32 );
                     else
                        sb_szDisplayCredits = new StringBuilder( szDisplayCredits );
                                          ZeidonStringConvertFromNumber( sb_szDisplayCredits, 1, 0, 20, 0, dTotalPBS_Credits, "D" );
                     szDisplayCredits = sb_szDisplayCredits.toString( );}
                     //:szDesc = "Undergrad Tuition PBS Disc - " + szDisplayCredits  + " CR"
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringCopy( sb_szDesc, 1, 0, "Undergrad Tuition PBS Disc - ", 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringConcat( sb_szDesc, 1, 0, szDisplayCredits, 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringConcat( sb_szDesc, 1, 0, " CR", 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}
                     //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc )
                     nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc );
                  } 

                  //:END
               } 

               //:END

               //:// Audit Credits
               //:IF dTotalAuditCredits > 0
               if ( dTotalAuditCredits > 0 )
               { 
                  //:// Audit Per Credit Charge, which is 50% of Part Time Charge.
                  //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "PT Chg/CR"
                  RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "PT Chg/CR", "" );
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:szMsg = "No Rule Variable match on Tuition 'PT Chg/CR'"
                      {StringBuilder sb_szMsg;
                     if ( szMsg == null )
                        sb_szMsg = new StringBuilder( 32 );
                     else
                        sb_szMsg = new StringBuilder( szMsg );
                                          ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Tuition 'PT Chg/CR'", 1, 0, 301 );
                     szMsg = sb_szMsg.toString( );}
                     //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                     MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                     //:RETURN -1
                     if(8==8)return( -1 );
                  } 

                  //:END

                  //:// Generate Charge
                  //:// Audit credits use SA Trans TUITAUD
                  //:CreateViewFromView( mSAChrgT2, mSAChrgT )
                  CreateViewFromView( mSAChrgT2, mSAChrgT );
                  //:SET CURSOR FIRST mSAChrgT2.SATransactionCode WHERE mSAChrgT2.SATransactionCode.TransactionCode = "TUITAUD"
                  RESULT = SetCursorFirstEntityByString( mSAChrgT2, "SATransactionCode", "TransactionCode", "TUITAUD", "" );
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:szMsg = "No SA Transaction for Audit credits."
                      {StringBuilder sb_szMsg;
                     if ( szMsg == null )
                        sb_szMsg = new StringBuilder( 32 );
                     else
                        sb_szMsg = new StringBuilder( szMsg );
                                          ZeidonStringCopy( sb_szMsg, 1, 0, "No SA Transaction for Audit credits.", 1, 0, 301 );
                     szMsg = sb_szMsg.toString( );}
                     //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                     MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                     //:RETURN -1
                     if(8==8)return( -1 );
                  } 

                  //:END
                  //:dChargePerCreditHour = mSAChrgT2.SATransactionTerm.Amount     // Charge is simple value for SA Transaction.
                  {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                     GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT2, "SATransactionTerm", "Amount" );
                  dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                  //:dTotalCharge = dTotalAuditCredits * dChargePerCreditHour
                  dTotalCharge = dTotalAuditCredits * dChargePerCreditHour;
                  //:szDisplayCredits = dTotalAuditCredits
                   {StringBuilder sb_szDisplayCredits;
                  if ( szDisplayCredits == null )
                     sb_szDisplayCredits = new StringBuilder( 32 );
                  else
                     sb_szDisplayCredits = new StringBuilder( szDisplayCredits );
                                    ZeidonStringConvertFromNumber( sb_szDisplayCredits, 1, 0, 20, 0, dTotalAuditCredits, "D" );
                  szDisplayCredits = sb_szDisplayCredits.toString( );}
                  //:szDesc = "Audit Undergrad Tuition - " + szDisplayCredits  + " CR"
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringCopy( sb_szDesc, 1, 0, "Audit Undergrad Tuition - ", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, szDisplayCredits, 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, " CR", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                  //:nRC = GenerateChargeEntry( mSAChrgT2, mSAProf, dTotalCharge, szDesc )
                  nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT2, mSAProf, dTotalCharge, szDesc );
                  //:DropView( mSAChrgT2 )
                  DropView( mSAChrgT2 );
               } 

               //:END

               //:ELSE
            } 
            else
            { 

               //:// Full-time Charges

               //:// Check if Audited courses removes Student from block charge.
               //:dTotalLessAuditCredits = dTotalCredits - dTotalAuditCredits
               dTotalLessAuditCredits = dTotalCredits - dTotalAuditCredits;
               //:IF dTotalLessAuditCredits < 12
               if ( dTotalLessAuditCredits < 12 )
               { 

                  //:// Student is combination regular and Audit credits, process as two separate charges.

                  //:// Net Audit credits (those that are charged at audit rates) is the difference between regular credits
                  //:// and full time credits.
                  //:dNetAuditCredits = 12 - dTotalLessAuditCredits
                  dNetAuditCredits = 12 - dTotalLessAuditCredits;

                  //:// Regular Part Time Per Credit Charge.
                  //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "PT Chg/CR"
                  RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "PT Chg/CR", "" );
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:szMsg = "No Rule Variable match on Tuition 'PT Chg/CR'"
                      {StringBuilder sb_szMsg;
                     if ( szMsg == null )
                        sb_szMsg = new StringBuilder( 32 );
                     else
                        sb_szMsg = new StringBuilder( szMsg );
                                          ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Tuition 'PT Chg/CR'", 1, 0, 301 );
                     szMsg = sb_szMsg.toString( );}
                     //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                     MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                     //:RETURN -1
                     if(8==8)return( -1 );
                  } 

                  //:END

                  //:// Generate Charge
                  //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
                  {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                     GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
                  dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                  //:dTotalCharge = dTotalLessAuditCredits * dChargePerCreditHour
                  dTotalCharge = dTotalLessAuditCredits * dChargePerCreditHour;
                  //:szDisplayCredits = dTotalCredits
                   {StringBuilder sb_szDisplayCredits;
                  if ( szDisplayCredits == null )
                     sb_szDisplayCredits = new StringBuilder( 32 );
                  else
                     sb_szDisplayCredits = new StringBuilder( szDisplayCredits );
                                    ZeidonStringConvertFromNumber( sb_szDisplayCredits, 1, 0, 20, 0, dTotalCredits, "D" );
                  szDisplayCredits = sb_szDisplayCredits.toString( );}
                  //:szDesc = "P/T Undergrad Tuition - " + szDisplayCredits  + " CR"
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringCopy( sb_szDesc, 1, 0, "P/T Undergrad Tuition - ", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, szDisplayCredits, 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, " CR", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                  //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc )
                  nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc );

                  //:// PBS Discount by number applicable credits.
                  //:IF dTotalPBS_Credits > 0
                  if ( dTotalPBS_Credits > 0 )
                  { 
                     //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "PBS Disc/CR"
                     RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "PBS Disc/CR", "" );
                     //:IF RESULT < zCURSOR_SET
                     if ( RESULT < zCURSOR_SET )
                     { 
                        //:szMsg = "No Rule Variable match on Tuition 'PBS Disc/CR'"
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Tuition 'PBS Disc/CR'", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                        //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                        MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                        //:RETURN -1
                        if(8==8)return( -1 );
                     } 

                     //:END
                     //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
                     {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                           GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
                     dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                     //:dTotalCharge = dTotalPBS_Credits * dChargePerCreditHour
                     dTotalCharge = dTotalPBS_Credits * dChargePerCreditHour;
                     //:dTotalCharge = 0 - dTotalCharge        // Discount is negative of charge.
                     dTotalCharge = 0 - dTotalCharge;
                     //:szDisplayCredits = dTotalCredits
                      {StringBuilder sb_szDisplayCredits;
                     if ( szDisplayCredits == null )
                        sb_szDisplayCredits = new StringBuilder( 32 );
                     else
                        sb_szDisplayCredits = new StringBuilder( szDisplayCredits );
                                          ZeidonStringConvertFromNumber( sb_szDisplayCredits, 1, 0, 20, 0, dTotalCredits, "D" );
                     szDisplayCredits = sb_szDisplayCredits.toString( );}
                     //:szDesc = "Undergrad Tuition PBS Disc - " + szDisplayCredits  + " CR"
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringCopy( sb_szDesc, 1, 0, "Undergrad Tuition PBS Disc - ", 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringConcat( sb_szDesc, 1, 0, szDisplayCredits, 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringConcat( sb_szDesc, 1, 0, " CR", 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}
                     //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc )
                     nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc );
                  } 

                  //:END

                  //:// Audit Per Credit Charge, which is 50% of Part Time Charge.
                  //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "PT Chg/CR"
                  RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "PT Chg/CR", "" );
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:szMsg = "No Rule Variable match on Tuition 'PT Chg/CR'"
                      {StringBuilder sb_szMsg;
                     if ( szMsg == null )
                        sb_szMsg = new StringBuilder( 32 );
                     else
                        sb_szMsg = new StringBuilder( szMsg );
                                          ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Tuition 'PT Chg/CR'", 1, 0, 301 );
                     szMsg = sb_szMsg.toString( );}
                     //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                     MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                     //:RETURN -1
                     if(8==8)return( -1 );
                  } 

                  //:END

                  //:// Generate Charge
                  //:// Audit credits use SA Trans TUITAUD
                  //:CreateViewFromView( mSAChrgT2, mSAChrgT )
                  CreateViewFromView( mSAChrgT2, mSAChrgT );
                  //:SET CURSOR FIRST mSAChrgT2.SATransactionCode WHERE mSAChrgT2.SATransactionCode.TransactionCode = "TUITAUD"
                  RESULT = SetCursorFirstEntityByString( mSAChrgT2, "SATransactionCode", "TransactionCode", "TUITAUD", "" );
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:szMsg = "No SA Transaction for Audit credits."
                      {StringBuilder sb_szMsg;
                     if ( szMsg == null )
                        sb_szMsg = new StringBuilder( 32 );
                     else
                        sb_szMsg = new StringBuilder( szMsg );
                                          ZeidonStringCopy( sb_szMsg, 1, 0, "No SA Transaction for Audit credits.", 1, 0, 301 );
                     szMsg = sb_szMsg.toString( );}
                     //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                     MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                     //:RETURN -1
                     if(8==8)return( -1 );
                  } 

                  //:END
                  //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value    // Charge is still based on regular Tuition Tran Code.
                  {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                     GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
                  dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                  //:dTotalCharge = dTotalAuditCredits * dChargePerCreditHour * .5
                  dTotalCharge = dTotalAuditCredits * dChargePerCreditHour * .5;
                  //:szDisplayCredits = dTotalAuditCredits
                   {StringBuilder sb_szDisplayCredits;
                  if ( szDisplayCredits == null )
                     sb_szDisplayCredits = new StringBuilder( 32 );
                  else
                     sb_szDisplayCredits = new StringBuilder( szDisplayCredits );
                                    ZeidonStringConvertFromNumber( sb_szDisplayCredits, 1, 0, 20, 0, dTotalAuditCredits, "D" );
                  szDisplayCredits = sb_szDisplayCredits.toString( );}
                  //:szDesc = "Audit Undergrad Tuition - " + szDisplayCredits  + " CR"
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringCopy( sb_szDesc, 1, 0, "Audit Undergrad Tuition - ", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, szDisplayCredits, 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, " CR", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                  //:nRC = GenerateChargeEntry( mSAChrgT2, mSAProf, dTotalCharge, szDesc )
                  nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT2, mSAProf, dTotalCharge, szDesc );
                  //:DropView( mSAChrgT2 )
                  DropView( mSAChrgT2 );

                  //:ELSE
               } 
               else
               { 

                  //:// Student is full time, charge block credit.

                  //:// Since the student is charged the block charge, there is no additional charge for audit credits, so set dTotalAuditCredits to zero.
                  //:dTotalAuditCredits = 0
                  dTotalAuditCredits = 0;

                  //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "FT Block Chg"
                  RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "FT Block Chg", "" );
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:szMsg = "No Rule Variable match on Tuition 'FT Block Chg'"
                      {StringBuilder sb_szMsg;
                     if ( szMsg == null )
                        sb_szMsg = new StringBuilder( 32 );
                     else
                        sb_szMsg = new StringBuilder( szMsg );
                                          ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Tuition 'FT Block Chg'", 1, 0, 301 );
                     szMsg = sb_szMsg.toString( );}
                     //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                     MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                     //:RETURN -1
                     if(8==8)return( -1 );
                  } 

                  //:END
                  //:dBlockCharge = mSAChrgT.SAInternalRuleVariable.Value
                  {MutableDouble md_dBlockCharge = new MutableDouble( dBlockCharge );
                                     GetDecimalFromAttribute( md_dBlockCharge, mSAChrgT, "SAInternalRuleVariable", "Value" );
                  dBlockCharge = md_dBlockCharge.doubleValue( );}
                  //:szDesc = "Undergrad Block Tuition - " + mSAProf.PeriodCollegeTerm.YearSemester
                  {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                  StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                     GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_5, 'S', 21, mSAProf, "PeriodCollegeTerm", "YearSemester", "", 0 );
                  lTempInteger_5 = mi_lTempInteger_5.intValue( );
                  szTempString_0 = sb_szTempString_0.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringCopy( sb_szDesc, 1, 0, "Undergrad Block Tuition - ", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, szTempString_0, 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                  //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dBlockCharge, szDesc )
                  nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dBlockCharge, szDesc );

                  //:// PBS Discount by number applicable credits.
                  //:// Applicable Block PBS Credits are 12 - dTotalNonPBS_Credits.
                  //:IF dTotalPBS_Credits > 0
                  if ( dTotalPBS_Credits > 0 )
                  { 
                     //:szDisplayCredits = dTotalPBS_Credits
                      {StringBuilder sb_szDisplayCredits;
                     if ( szDisplayCredits == null )
                        sb_szDisplayCredits = new StringBuilder( 32 );
                     else
                        sb_szDisplayCredits = new StringBuilder( szDisplayCredits );
                                          ZeidonStringConvertFromNumber( sb_szDisplayCredits, 1, 0, 20, 0, dTotalPBS_Credits, "D" );
                     szDisplayCredits = sb_szDisplayCredits.toString( );}
                     //:TraceLineS( "*** dTotalPBS_Credits: ", szDisplayCredits )
                     TraceLineS( "*** dTotalPBS_Credits: ", szDisplayCredits );
                     //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "PBS Disc/CR"
                     RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "PBS Disc/CR", "" );
                     //:IF RESULT < zCURSOR_SET
                     if ( RESULT < zCURSOR_SET )
                     { 
                        //:szMsg = "No Rule Variable match on Tuition 'PBS Disc/CR'"
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Tuition 'PBS Disc/CR'", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                        //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                        MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                        //:RETURN -1
                        if(8==8)return( -1 );
                     } 

                     //:END
                     //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
                     {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                           GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
                     dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                     //:dTotalNonPBS_Credits = dTotalLessAuditCredits - dTotalPBS_Credits
                     dTotalNonPBS_Credits = dTotalLessAuditCredits - dTotalPBS_Credits;
                     //:dTotalBlockPBS_Credits = 12.0 - dTotalNonPBS_Credits
                     dTotalBlockPBS_Credits = 12.0 - dTotalNonPBS_Credits;
                     //:IF dTotalBlockPBS_Credits > 0
                     if ( dTotalBlockPBS_Credits > 0 )
                     { 
                        //:dTotalCharge = dTotalBlockPBS_Credits * dChargePerCreditHour
                        dTotalCharge = dTotalBlockPBS_Credits * dChargePerCreditHour;
                        //:dTotalCharge = 0 - dTotalCharge        // Discount is negative of charge.
                        dTotalCharge = 0 - dTotalCharge;
                        //:szDisplayCredits = dTotalBlockPBS_Credits
                         {StringBuilder sb_szDisplayCredits;
                        if ( szDisplayCredits == null )
                           sb_szDisplayCredits = new StringBuilder( 32 );
                        else
                           sb_szDisplayCredits = new StringBuilder( szDisplayCredits );
                                                ZeidonStringConvertFromNumber( sb_szDisplayCredits, 1, 0, 20, 0, dTotalBlockPBS_Credits, "D" );
                        szDisplayCredits = sb_szDisplayCredits.toString( );}
                        //:szDesc = "Undergrad Block PBS Disc - " + szDisplayCredits  + " CR"
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringCopy( sb_szDesc, 1, 0, "Undergrad Block PBS Disc - ", 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, szDisplayCredits, 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, " CR", 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                        //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc )
                        nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc );
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            //:   
            //:END
         } 

         //:END

         //:IF dTotalOverloadCredits > 0
         if ( dTotalOverloadCredits > 0 )
         { 

            //:// Overload Credits
            //:// If some of the students credits are Audit, we will consider those courses first for overload and charge
            //:// at the individual Audit price. Any regular credits that still qualify for overload are charged at the 
            //:// "OvLd Chg/CR" price.

            //:// On 8/15/2011, DonC eliminated charging Overload Credits for Audited Credits.

            //:// Determine Regular overload credits.
            //://dRegularOverloadCredits = dTotalOverloadCredits - dTotalAuditCredits
            //://IF dRegularOverloadCredits <= 0
            //://   dAuditOverloadCredits = dTotalOverloadCredits
            //://ELSE
            //://   dAuditOverloadCredits = dTotalOverloadCredits - dRegularOverloadCredits
            //://END
            //:dRegularOverloadCredits = dTotalOverloadCredits
            dRegularOverloadCredits = dTotalOverloadCredits;
            //:dAuditOverloadCredits = 0
            dAuditOverloadCredits = 0;

            //:// Regular Overload Credits
            //:IF dRegularOverloadCredits > 0
            if ( dRegularOverloadCredits > 0 )
            { 
               //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "OvLd Chg/CR"
               RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "OvLd Chg/CR", "" );
               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:szMsg = "No Rule Variable match on Tuition 'OvLd Chg/CR'"
                   {StringBuilder sb_szMsg;
                  if ( szMsg == null )
                     sb_szMsg = new StringBuilder( 32 );
                  else
                     sb_szMsg = new StringBuilder( szMsg );
                                    ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Tuition 'OvLd Chg/CR'", 1, 0, 301 );
                  szMsg = sb_szMsg.toString( );}
                  //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:RETURN -1
                  if(8==8)return( -1 );
               } 

               //:END

               //:// Generate Charge
               //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
               {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                               GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
               dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
               //:dTotalCharge = dRegularOverloadCredits * dChargePerCreditHour
               dTotalCharge = dRegularOverloadCredits * dChargePerCreditHour;
               //:szDisplayCredits = dRegularOverloadCredits
                {StringBuilder sb_szDisplayCredits;
               if ( szDisplayCredits == null )
                  sb_szDisplayCredits = new StringBuilder( 32 );
               else
                  sb_szDisplayCredits = new StringBuilder( szDisplayCredits );
                              ZeidonStringConvertFromNumber( sb_szDisplayCredits, 1, 0, 20, 0, dRegularOverloadCredits, "D" );
               szDisplayCredits = sb_szDisplayCredits.toString( );}
               //:szDesc = "Undergrad Tuition Overload - " + szDisplayCredits  + " CR"
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringCopy( sb_szDesc, 1, 0, "Undergrad Tuition Overload - ", 1, 0, 301 );
               szDesc = sb_szDesc.toString( );}
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringConcat( sb_szDesc, 1, 0, szDisplayCredits, 1, 0, 301 );
               szDesc = sb_szDesc.toString( );}
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringConcat( sb_szDesc, 1, 0, " CR", 1, 0, 301 );
               szDesc = sb_szDesc.toString( );}
               //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc )
               nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc );
            } 

            //:END

            //:// PBS Discount for PBS Overload credits.
            //:// Applicable Credits are dTotalPBS_Credits - 17.
            //:IF dTotalPBS_Credits > 17.0
            if ( dTotalPBS_Credits > 17.0 )
            { 
               //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "PBS Disc/CR"
               RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "PBS Disc/CR", "" );
               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:szMsg = "No Rule Variable match on Tuition 'PBS Disc/CR'"
                   {StringBuilder sb_szMsg;
                  if ( szMsg == null )
                     sb_szMsg = new StringBuilder( 32 );
                  else
                     sb_szMsg = new StringBuilder( szMsg );
                                    ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Tuition 'PBS Disc/CR'", 1, 0, 301 );
                  szMsg = sb_szMsg.toString( );}
                  //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:RETURN -1
                  if(8==8)return( -1 );
               } 

               //:END
               //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
               {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                               GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
               dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
               //:dTotalOverloadPBS_Credits = dTotalPBS_Credits - 17.0
               dTotalOverloadPBS_Credits = dTotalPBS_Credits - 17.0;
               //:dTotalCharge = dTotalOverloadPBS_Credits * dChargePerCreditHour
               dTotalCharge = dTotalOverloadPBS_Credits * dChargePerCreditHour;
               //:dTotalCharge = 0 - dTotalCharge        // Discount is negative of charge.
               dTotalCharge = 0 - dTotalCharge;
               //:szDisplayCredits = dTotalOverloadPBS_Credits
                {StringBuilder sb_szDisplayCredits;
               if ( szDisplayCredits == null )
                  sb_szDisplayCredits = new StringBuilder( 32 );
               else
                  sb_szDisplayCredits = new StringBuilder( szDisplayCredits );
                              ZeidonStringConvertFromNumber( sb_szDisplayCredits, 1, 0, 20, 0, dTotalOverloadPBS_Credits, "D" );
               szDisplayCredits = sb_szDisplayCredits.toString( );}
               //:szDesc = "Undergrad Overload PBS Disc - " + szDisplayCredits  + " CR"
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringCopy( sb_szDesc, 1, 0, "Undergrad Overload PBS Disc - ", 1, 0, 301 );
               szDesc = sb_szDesc.toString( );}
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringConcat( sb_szDesc, 1, 0, szDisplayCredits, 1, 0, 301 );
               szDesc = sb_szDesc.toString( );}
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringConcat( sb_szDesc, 1, 0, " CR", 1, 0, 301 );
               szDesc = sb_szDesc.toString( );}
               //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc )
               nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dTotalCharge, szDesc );
            } 

            //:END

            //:// Audit Overload Credits
            //:// Audit Per Credit Charge is 50% of Part Time Charge.
            //:IF dAuditOverloadCredits > 0
            if ( dAuditOverloadCredits > 0 )
            { 
               //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "PT Chg/CR"
               RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "PT Chg/CR", "" );
               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:szMsg = "No Rule Variable match on Tuition 'PT Chg/CR'"
                   {StringBuilder sb_szMsg;
                  if ( szMsg == null )
                     sb_szMsg = new StringBuilder( 32 );
                  else
                     sb_szMsg = new StringBuilder( szMsg );
                                    ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Tuition 'PT Chg/CR'", 1, 0, 301 );
                  szMsg = sb_szMsg.toString( );}
                  //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:RETURN -1
                  if(8==8)return( -1 );
               } 

               //:END

               //:// Generate Charge
               //:// Audit credits use SA Trans TUITAUD
               //:CreateViewFromView( mSAChrgT2, mSAChrgT )
               CreateViewFromView( mSAChrgT2, mSAChrgT );
               //:SET CURSOR FIRST mSAChrgT2.SATransactionCode WHERE mSAChrgT2.SATransactionCode.TransactionCode = "TUITAUD"
               RESULT = SetCursorFirstEntityByString( mSAChrgT2, "SATransactionCode", "TransactionCode", "TUITAUD", "" );
               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:szMsg = "No SA Transaction for Audit credits."
                   {StringBuilder sb_szMsg;
                  if ( szMsg == null )
                     sb_szMsg = new StringBuilder( 32 );
                  else
                     sb_szMsg = new StringBuilder( szMsg );
                                    ZeidonStringCopy( sb_szMsg, 1, 0, "No SA Transaction for Audit credits.", 1, 0, 301 );
                  szMsg = sb_szMsg.toString( );}
                  //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:RETURN -1
                  if(8==8)return( -1 );
               } 

               //:END
               //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value    // Charge is still based on regular Tuition Tran Code.
               {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                               GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
               dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
               //:dTotalCharge = dTotalAuditCredits * dChargePerCreditHour * .5
               dTotalCharge = dTotalAuditCredits * dChargePerCreditHour * .5;
               //:szDisplayCredits = dTotalAuditCredits
                {StringBuilder sb_szDisplayCredits;
               if ( szDisplayCredits == null )
                  sb_szDisplayCredits = new StringBuilder( 32 );
               else
                  sb_szDisplayCredits = new StringBuilder( szDisplayCredits );
                              ZeidonStringConvertFromNumber( sb_szDisplayCredits, 1, 0, 20, 0, dTotalAuditCredits, "D" );
               szDisplayCredits = sb_szDisplayCredits.toString( );}
               //:szDesc = "Undergrad Tuition Audit Overload - " + szDisplayCredits  + " CR"
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringCopy( sb_szDesc, 1, 0, "Undergrad Tuition Audit Overload - ", 1, 0, 301 );
               szDesc = sb_szDesc.toString( );}
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringConcat( sb_szDesc, 1, 0, szDisplayCredits, 1, 0, 301 );
               szDesc = sb_szDesc.toString( );}
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringConcat( sb_szDesc, 1, 0, " CR", 1, 0, 301 );
               szDesc = sb_szDesc.toString( );}
               //:nRC = GenerateChargeEntry( mSAChrgT2, mSAProf, dTotalCharge, szDesc )
               nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT2, mSAProf, dTotalCharge, szDesc );
               //:DropView( mSAChrgT2 )
               DropView( mSAChrgT2 );
            } 


            //:END
         } 

         //:   
         //:END

         //:ELSE
      } 
      else
      { 
      } 


      //:   // Refund.
      //:   // Because of the complexities with the discounts, we will not process refunds automatically at this point.
      //:   
      //:END
   } 


   //:END
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:Rule_SummerTuition( VIEW mSAChrgT BASED ON LOD mSAChrgT,
//:                    VIEW mSAProf  BASED ON LOD mSAProf,
//:                    STRING ( 1 )   szRefundFlag )

//:   VIEW mSAProf2    BASED ON LOD mSAProf
private int 
omSAChrgT_Rule_SummerTuition( View     mSAChrgT,
                              View     mSAProf,
                              String   szRefundFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:VIEW mCollegeLST BASED ON LOD mCollege
   zVIEW    mCollegeLST = new zVIEW( );
   //:STRING ( 300 ) szMsg
   String   szMsg = null;
   //:STRING ( 300 ) szDesc
   String   szDesc = null;
   //:STRING ( 4 )   szCourseNumber4
   String   szCourseNumber4 = null;
   //:STRING ( 3 )   szSection
   String   szSection = null;
   //:STRING ( 1 )   szCERT_flag
   String   szCERT_flag = null;
   //:DECIMAL        dChargePerCreditHour
   double  dChargePerCreditHour = 0.0;
   //:DECIMAL        dCredits
   double  dCredits = 0.0;
   //:DECIMAL        dChargePerClass
   double  dChargePerClass = 0.0;
   //:DECIMAL        dAmount
   double  dAmount = 0.0;
   //:INTEGER        dAmountInteger
   int      dAmountInteger = 0;
   //:SHORT          nRC
   int      nRC = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_5 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_6 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_7 = 0;
   String   szTempString_4 = null;
   int      lTempInteger_8 = 0;
   String   szTempString_5 = null;
   int      lTempInteger_9 = 0;


   //:// Process Summer Tuition Charges Per Class.
   //:// Charges are generated here for all Classes that are NOT charged to DL Tuition, which is:
   //:// 1. The Student DOES NOT HAVE the AdultStudiesFlag set.AND
   //:// 2, The Class IS NOT Section 50.

   //:// There are five different ways that charges can be computed for Summer Tuition, but it is always per Class.
   //:// 1. If there is a tuition override value specified for the Class, we use that value.
   //:// 2. We use the "S1-19 Chg/CR" amount per credit when the Class Section is 01 through 19.
   //:// 3. We use the "S20 Chg/CR" amount per credit when the Class Section is 20
   //:// 4. We use the "Grad Chg/CR" amount per credit if the Class is a graduate Class. (This is determined by
   //://    the Course being tied to the College, "Graduate Studies".
   //:// 5. We use the "Field Chg/CR" amount per credit when the Course Number is one of the field courses defined in the rule.

   //:// In addition, there can be a PBS Discount applied to a Class. 
   //:// The rules identifying Student and Class eligibility for a PBS Discount are defined separately and those rules 
   //:// apply for all tuition and college terms. However, the amount of the discount is specified per Transaction Item / Term
   //:// using the value, "PBS Discount". 

   //:CreateViewFromView( mSAProf2, mSAProf )
   CreateViewFromView( mSAProf2, mSAProf );
   //:NAME VIEW mSAProf2 "mSAProf2"
   SetNameForView( mSAProf2, "mSAProf2", null, zLEVEL_TASK );
   //:   
   //:// Loop through each Class and generate charge and option discount transactions based on Section, credits and
   //:// other Student/Class information. 
   //:// The charge per credit or per Class is defined in SAInternalRuleVariable.
   //:FOR EACH mSAProf2.TermEnrolled WHERE mSAProf2.TermEnrolled.Status != "D"
   RESULT = SetCursorFirstEntity( mSAProf2, "TermEnrolled", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "D" ) != 0 )
      { 

         //:// The Class the Student is taking is a graduate class (The Course is tied to a Graduate College), so  don't process.
         //:GET VIEW mCollegeLST NAMED "mCollegeLST"
         RESULT = GetViewByName( mCollegeLST, "mCollegeLST", mSAChrgT, zLEVEL_TASK );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:ACTIVATE mCollegeLST Multiple
            RESULT = ActivateObjectInstance( mCollegeLST, "mCollege", mSAChrgT, 0, zMULTIPLE );
            //:NAME VIEW mCollegeLST "mCollegeLST"
            SetNameForView( mCollegeLST, "mCollegeLST", null, zLEVEL_TASK );
         } 

         //:END
         //:SET CURSOR FIRST mCollegeLST.College WHERE mCollegeLST.College.ID = mSAProf2.TermCourseCollege.ID 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf2, "TermCourseCollege", "ID" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mCollegeLST, "College", "ID", lTempInteger_0, "" );
         //:IF mCollegeLST.College.Type != "G"   
         if ( CompareAttributeToString( mCollegeLST, "College", "Type", "G" ) != 0 )
         { 
            //:dCredits  = mSAProf2.TermEnrolled.CreditHours 
            {MutableDouble md_dCredits = new MutableDouble( dCredits );
                         GetDecimalFromAttribute( md_dCredits, mSAProf2, "TermEnrolled", "CreditHours" );
            dCredits = md_dCredits.doubleValue( );}
            //:szSection = mSAProf2.TermClass.Section 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szSection;
            if ( szSection == null )
               sb_szSection = new StringBuilder( 32 );
            else
               sb_szSection = new StringBuilder( szSection );
                         GetVariableFromAttribute( sb_szSection, mi_lTempInteger_1, 'S', 4, mSAProf2, "TermClass", "Section", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szSection = sb_szSection.toString( );}

            //:szSection = mSAProf2.TermClass.Section 
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szSection;
            if ( szSection == null )
               sb_szSection = new StringBuilder( 32 );
            else
               sb_szSection = new StringBuilder( szSection );
                         GetVariableFromAttribute( sb_szSection, mi_lTempInteger_2, 'S', 4, mSAProf2, "TermClass", "Section", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szSection = sb_szSection.toString( );}
            //://SET CURSOR FIRST mSAProf2.Category WHERE mSAProf2.Category.Name = "Distance Learning"
            //://                                      OR mSAProf2.Category.Name = "ADP Student"
            //://IF RESULT < zCURSOR_SET AND mSAProf2.TermClass.AdultStudiesFlag != "Y" 
            //:IF mSAProf.Student.AdultStudiesFlag != "Y" AND mSAProf2.TermClass.AdultStudiesFlag != "Y" 
            if ( CompareAttributeToString( mSAProf, "Student", "AdultStudiesFlag", "Y" ) != 0 && CompareAttributeToString( mSAProf2, "TermClass", "AdultStudiesFlag", "Y" ) != 0 )
            { 

               //:// Tuition Override on Class
               //:IF mSAProf2.TermClass.OverrideClassTuition > 0
               if ( CompareAttributeToInteger( mSAProf2, "TermClass", "OverrideClassTuition", 0 ) > 0 )
               { 

                  //:// The Charge is simply the amount specified in Class.
                  //:dAmount = mSAProf2.TermClass.OverrideClassTuition 
                  {MutableDouble md_dAmount = new MutableDouble( dAmount );
                                     GetDecimalFromAttribute( md_dAmount, mSAProf2, "TermClass", "OverrideClassTuition" );
                  dAmount = md_dAmount.doubleValue( );}
                  //:// Process resulting Charge or Refund.
                  //:IF szRefundFlag = ""
                  if ( ZeidonStringCompare( szRefundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
                  { 
                     //:// Regular Charge, not Refund. Generate entry if there is a positive amount.
                     //:IF dAmount > 0
                     if ( dAmount > 0 )
                     { 
                        //:// Generate the charge if it isn't already there.
                        //:szDesc = mSAChrgT.SATransactionCode.Description + " - " + mSAProf2.TermCourse.Number + "-" + szSection + " Override Charge"
                        {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                 GetStringFromAttribute( sb_szDesc, mSAChrgT, "SATransactionCode", "Description" );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, " - ", 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                        {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                        StringBuilder sb_szTempString_0;
                        if ( szTempString_0 == null )
                           sb_szTempString_0 = new StringBuilder( 32 );
                        else
                           sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                                 GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_3, 'S', 11, mSAProf2, "TermCourse", "Number", "", 0 );
                        lTempInteger_3 = mi_lTempInteger_3.intValue( );
                        szTempString_0 = sb_szTempString_0.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, szTempString_0, 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, "-", 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, szSection, 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, " Override Charge", 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                        //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, szDesc )
                        nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, szDesc );
                        //:IF nRC >= 0
                        if ( nRC >= 0 )
                        { 
                           //:// Add Course Number and Class Section to description.
                           //:mSAProf.StudentAccountTransApplied.Description = szDesc
                           SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szDesc );
                        } 

                        //:END
                     } 

                     //:END
                     //:ELSE
                  } 
                  else
                  { 
                     //:// Refund.
                     //:// We'll process a Refund if the Amount has changed.
                     //:IF dAmount != mSAProf.PeriodTransApplied.Amount 
                     if ( CompareAttributeToDecimal( mSAProf, "PeriodTransApplied", "Amount", dAmount ) != 0 )
                     { 
                        //:GenerateRefundEntry( mSAChrgT, mSAProf )
                        omSAChrgT_GenerateRefundEntry( mSAChrgT, mSAProf );
                     } 

                     //:END
                  } 

                  //:END

                  //:ELSE
               } 
               else
               { 

                  //:// TUITION CHARGE FROM RULE VARIABLE
                  //:// In the following cases, the charge (dAmount) is determined by positioning on the correct Rule Variable.
                  //:// We we first position on the correct Rule Variable, and then create the charge.

                  //:// Graduate Class Charge/Credit
                  //:// The Class the Student is taking is a graduate class (The Course is tied to a Graduate College)
                  //:GET VIEW mCollegeLST NAMED "mCollegeLST"
                  RESULT = GetViewByName( mCollegeLST, "mCollegeLST", mSAChrgT, zLEVEL_TASK );
                  //:IF RESULT < 0
                  if ( RESULT < 0 )
                  { 
                     //:ACTIVATE mCollegeLST Multiple
                     RESULT = ActivateObjectInstance( mCollegeLST, "mCollege", mSAChrgT, 0, zMULTIPLE );
                     //:NAME VIEW mCollegeLST "mCollegeLST"
                     SetNameForView( mCollegeLST, "mCollegeLST", null, zLEVEL_TASK );
                  } 

                  //:END
                  //:SET CURSOR FIRST mCollegeLST.College WHERE mCollegeLST.College.ID = mSAProf2.TermCourseCollege.ID 
                  {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                                     GetIntegerFromAttribute( mi_lTempInteger_4, mSAProf2, "TermCourseCollege", "ID" );
                  lTempInteger_4 = mi_lTempInteger_4.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mCollegeLST, "College", "ID", lTempInteger_4, "" );
                  //:IF mCollegeLST.College.Type = "G"
                  if ( CompareAttributeToString( mCollegeLST, "College", "Type", "G" ) == 0 )
                  { 
                     //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "Grad Chg/CR"
                     RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "Grad Chg/CR", "" );
                     //:IF RESULT < zCURSOR_SET
                     if ( RESULT < zCURSOR_SET )
                     { 
                        //:szMsg = "No Rule Variable match on Summer Tuition 'Grad Chg/CR'"
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Summer Tuition 'Grad Chg/CR'", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                        //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                        MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                        //:RETURN -1
                        if(8==8)return( -1 );
                     } 

                     //:END
                     //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
                     {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                           GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
                     dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                     //:dAmount = dChargePerCreditHour * dCredits
                     dAmount = dChargePerCreditHour * dCredits;
                     //:szDesc = mSAChrgT.SATransactionCode.Description + " Grad - " + mSAProf2.TermCourse.Number + "-" + szSection
                     {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                           GetStringFromAttribute( sb_szDesc, mSAChrgT, "SATransactionCode", "Description" );
                     szDesc = sb_szDesc.toString( );}
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringConcat( sb_szDesc, 1, 0, " Grad - ", 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}
                     {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                     StringBuilder sb_szTempString_1;
                     if ( szTempString_1 == null )
                        sb_szTempString_1 = new StringBuilder( 32 );
                     else
                        sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                           GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_5, 'S', 11, mSAProf2, "TermCourse", "Number", "", 0 );
                     lTempInteger_5 = mi_lTempInteger_5.intValue( );
                     szTempString_1 = sb_szTempString_1.toString( );}
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringConcat( sb_szDesc, 1, 0, szTempString_1, 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringConcat( sb_szDesc, 1, 0, "-", 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringConcat( sb_szDesc, 1, 0, szSection, 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}

                     //:ELSE
                  } 
                  else
                  { 

                     //:// Field Trip Special Tuition
                     //:// Classes HRNS275,  HRNS375, GEOL240 ad GEOL330 use "Field Chg/CR" variable.
                     //:IF mSAProf2.TermCourse.Number = "HNRS275" OR 
                     //:mSAProf2.TermCourse.Number = "HNRS375" OR
                     //:mSAProf2.TermCourse.Number = "GEOL240" OR
                     //:mSAProf2.TermCourse.Number = "GEOL330"
                     if ( CompareAttributeToString( mSAProf2, "TermCourse", "Number", "HNRS275" ) == 0 || CompareAttributeToString( mSAProf2, "TermCourse", "Number", "HNRS375" ) == 0 ||
                        CompareAttributeToString( mSAProf2, "TermCourse", "Number", "GEOL240" ) == 0 || CompareAttributeToString( mSAProf2, "TermCourse", "Number", "GEOL330" ) == 0 )
                     { 

                        //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "Field Chg/CR"
                        RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "Field Chg/CR", "" );
                        //:IF RESULT < zCURSOR_SET
                        if ( RESULT < zCURSOR_SET )
                        { 
                           //:szMsg = "No Rule Variable match on Summer Tuition 'Field Chg/CR'"
                            {StringBuilder sb_szMsg;
                           if ( szMsg == null )
                              sb_szMsg = new StringBuilder( 32 );
                           else
                              sb_szMsg = new StringBuilder( szMsg );
                                                      ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Summer Tuition 'Field Chg/CR'", 1, 0, 301 );
                           szMsg = sb_szMsg.toString( );}
                           //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                           MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                           //:RETURN -1
                           if(8==8)return( -1 );
                        } 

                        //:END
                        //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
                        {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                                 GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
                        dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                        //:dAmount = dChargePerCreditHour * dCredits
                        dAmount = dChargePerCreditHour * dCredits;
                        //:szDesc = mSAChrgT.SATransactionCode.Description + " - " + mSAProf2.TermCourse.Number + "-" + szSection
                        {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                 GetStringFromAttribute( sb_szDesc, mSAChrgT, "SATransactionCode", "Description" );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, " - ", 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                        {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                        StringBuilder sb_szTempString_2;
                        if ( szTempString_2 == null )
                           sb_szTempString_2 = new StringBuilder( 32 );
                        else
                           sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                                 GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_6, 'S', 11, mSAProf2, "TermCourse", "Number", "", 0 );
                        lTempInteger_6 = mi_lTempInteger_6.intValue( );
                        szTempString_2 = sb_szTempString_2.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, szTempString_2, 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, "-", 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, szSection, 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}

                        //:ELSE
                     } 
                     else
                     { 

                        //:// Sections 01-19 Class Charge/Credit
                        //:// Any Class with Section "01" through "19" uses "S1-19 Chg/CR" variable.
                        //:IF szSection >= "01" AND szSection <= "19"
                        if ( ZeidonStringCompare( szSection, 1, 0, "01", 1, 0, 4 ) <= 0 && ZeidonStringCompare( szSection, 1, 0, "19", 1, 0, 4 ) >= 0 )
                        { 

                           //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "S1-19 Chg/CR"
                           RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "S1-19 Chg/CR", "" );
                           //:IF RESULT < zCURSOR_SET
                           if ( RESULT < zCURSOR_SET )
                           { 
                              //:szMsg = "No Rule Variable match on Summer Tuition 'S1-19 Chg/CR'"
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Summer Tuition 'S1-19 Chg/CR'", 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                              //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                              MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                              //:RETURN -1
                              if(8==8)return( -1 );
                           } 

                           //:END
                           //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
                           {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                                       GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
                           dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                           //:dAmount = dChargePerCreditHour * dCredits
                           dAmount = dChargePerCreditHour * dCredits;
                           //:szDesc = mSAChrgT.SATransactionCode.Description + " - " + mSAProf2.TermCourse.Number + "-" + szSection
                           {StringBuilder sb_szDesc;
                           if ( szDesc == null )
                              sb_szDesc = new StringBuilder( 32 );
                           else
                              sb_szDesc = new StringBuilder( szDesc );
                                                       GetStringFromAttribute( sb_szDesc, mSAChrgT, "SATransactionCode", "Description" );
                           szDesc = sb_szDesc.toString( );}
                            {StringBuilder sb_szDesc;
                           if ( szDesc == null )
                              sb_szDesc = new StringBuilder( 32 );
                           else
                              sb_szDesc = new StringBuilder( szDesc );
                                                      ZeidonStringConcat( sb_szDesc, 1, 0, " - ", 1, 0, 301 );
                           szDesc = sb_szDesc.toString( );}
                           {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                           StringBuilder sb_szTempString_3;
                           if ( szTempString_3 == null )
                              sb_szTempString_3 = new StringBuilder( 32 );
                           else
                              sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                                       GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_7, 'S', 11, mSAProf2, "TermCourse", "Number", "", 0 );
                           lTempInteger_7 = mi_lTempInteger_7.intValue( );
                           szTempString_3 = sb_szTempString_3.toString( );}
                            {StringBuilder sb_szDesc;
                           if ( szDesc == null )
                              sb_szDesc = new StringBuilder( 32 );
                           else
                              sb_szDesc = new StringBuilder( szDesc );
                                                      ZeidonStringConcat( sb_szDesc, 1, 0, szTempString_3, 1, 0, 301 );
                           szDesc = sb_szDesc.toString( );}
                            {StringBuilder sb_szDesc;
                           if ( szDesc == null )
                              sb_szDesc = new StringBuilder( 32 );
                           else
                              sb_szDesc = new StringBuilder( szDesc );
                                                      ZeidonStringConcat( sb_szDesc, 1, 0, "-", 1, 0, 301 );
                           szDesc = sb_szDesc.toString( );}
                            {StringBuilder sb_szDesc;
                           if ( szDesc == null )
                              sb_szDesc = new StringBuilder( 32 );
                           else
                              sb_szDesc = new StringBuilder( szDesc );
                                                      ZeidonStringConcat( sb_szDesc, 1, 0, szSection, 1, 0, 301 );
                           szDesc = sb_szDesc.toString( );}

                           //:ELSE
                        } 
                        else
                        { 

                           //:// Section 20 Class Charge/Credit
                           //:// Any Class with Section "20" uses "S20 Chg/CR" variable.
                           //:IF szSection = "20"
                           if ( ZeidonStringCompare( szSection, 1, 0, "20", 1, 0, 4 ) == 0 )
                           { 

                              //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "S20 Chg/CR"
                              RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "S20 Chg/CR", "" );
                              //:IF RESULT < zCURSOR_SET
                              if ( RESULT < zCURSOR_SET )
                              { 
                                 //:szMsg = "No Rule Variable match on Summer Tuition 'S20 Chg/CR'"
                                  {StringBuilder sb_szMsg;
                                 if ( szMsg == null )
                                    sb_szMsg = new StringBuilder( 32 );
                                 else
                                    sb_szMsg = new StringBuilder( szMsg );
                                                                  ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Summer Tuition 'S20 Chg/CR'", 1, 0, 301 );
                                 szMsg = sb_szMsg.toString( );}
                                 //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                                 MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                                 //:RETURN -1
                                 if(8==8)return( -1 );
                              } 

                              //:END
                              //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
                              {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                                             GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
                              dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                              //:dAmount = dChargePerCreditHour * dCredits
                              dAmount = dChargePerCreditHour * dCredits;
                              //:szDesc = mSAChrgT.SATransactionCode.Description + " - " + mSAProf2.TermCourse.Number + "-" + szSection
                              {StringBuilder sb_szDesc;
                              if ( szDesc == null )
                                 sb_szDesc = new StringBuilder( 32 );
                              else
                                 sb_szDesc = new StringBuilder( szDesc );
                                                             GetStringFromAttribute( sb_szDesc, mSAChrgT, "SATransactionCode", "Description" );
                              szDesc = sb_szDesc.toString( );}
                               {StringBuilder sb_szDesc;
                              if ( szDesc == null )
                                 sb_szDesc = new StringBuilder( 32 );
                              else
                                 sb_szDesc = new StringBuilder( szDesc );
                                                            ZeidonStringConcat( sb_szDesc, 1, 0, " - ", 1, 0, 301 );
                              szDesc = sb_szDesc.toString( );}
                              {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                              StringBuilder sb_szTempString_4;
                              if ( szTempString_4 == null )
                                 sb_szTempString_4 = new StringBuilder( 32 );
                              else
                                 sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                                             GetVariableFromAttribute( sb_szTempString_4, mi_lTempInteger_8, 'S', 11, mSAProf2, "TermCourse", "Number", "", 0 );
                              lTempInteger_8 = mi_lTempInteger_8.intValue( );
                              szTempString_4 = sb_szTempString_4.toString( );}
                               {StringBuilder sb_szDesc;
                              if ( szDesc == null )
                                 sb_szDesc = new StringBuilder( 32 );
                              else
                                 sb_szDesc = new StringBuilder( szDesc );
                                                            ZeidonStringConcat( sb_szDesc, 1, 0, szTempString_4, 1, 0, 301 );
                              szDesc = sb_szDesc.toString( );}
                               {StringBuilder sb_szDesc;
                              if ( szDesc == null )
                                 sb_szDesc = new StringBuilder( 32 );
                              else
                                 sb_szDesc = new StringBuilder( szDesc );
                                                            ZeidonStringConcat( sb_szDesc, 1, 0, "-", 1, 0, 301 );
                              szDesc = sb_szDesc.toString( );}
                               {StringBuilder sb_szDesc;
                              if ( szDesc == null )
                                 sb_szDesc = new StringBuilder( 32 );
                              else
                                 sb_szDesc = new StringBuilder( szDesc );
                                                            ZeidonStringConcat( sb_szDesc, 1, 0, szSection, 1, 0, 301 );
                              szDesc = sb_szDesc.toString( );}

                              //:ELSE
                           } 
                           else
                           { 

                              //:// Anything else is an error.
                              //:szMsg = "Unexpected Class Type on Summer Tuition"
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringCopy( sb_szMsg, 1, 0, "Unexpected Class Type on Summer Tuition", 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                              //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                              MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                              //:RETURN -1
                              if(8==8)return( -1 );
                           } 


                           //:END
                        } 

                        //:END
                     } 

                     //:END
                  } 

                  //:END

                  //:// Process resulting Charge or Refund.
                  //:IF szRefundFlag = ""
                  if ( ZeidonStringCompare( szRefundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
                  { 
                     //:// Regular Charge, not Refund. Generate entry if there is a positive amount.
                     //:IF dAmount > 0
                     if ( dAmount > 0 )
                     { 
                        //:// Generate the charge if it isn't already there. Add two blanks at end so it sorts before any discount.
                        //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, szDesc )
                        nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, szDesc );
                        //:IF nRC >= 0
                        if ( nRC >= 0 )
                        { 
                           //:// Add Course Number and Class Section to description.
                           //:mSAProf.StudentAccountTransApplied.Description = szDesc
                           SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szDesc );
                        } 

                        //:END

                        //:// PBS Student Discount
                        //:// The rules for a PBS Student are found in the operation, CheckPBS_ForClass, which returns a "1" if the Student
                        //:// and Class are eligible for PBS Discount.
                        //:nRC = CheckPBS_ForClass( mSAChrgT, mSAProf2 )
                        nRC = omSAChrgT_CheckPBS_ForClass( mSAChrgT, mSAProf2 );
                        //:IF nRC = 1 
                        if ( nRC == 1 )
                        { 

                           //:// The PBS Discount is 50% (rounded) of amount generated earlier.
                           //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "PBS Discount"
                           RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "PBS Discount", "" );
                           //:IF RESULT < zCURSOR_SET
                           if ( RESULT < zCURSOR_SET )
                           { 
                              //:szMsg = "No Rule Variable match on Summer Tuition 'PBS Discount'"
                               {StringBuilder sb_szMsg;
                              if ( szMsg == null )
                                 sb_szMsg = new StringBuilder( 32 );
                              else
                                 sb_szMsg = new StringBuilder( szMsg );
                                                            ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on Summer Tuition 'PBS Discount'", 1, 0, 301 );
                              szMsg = sb_szMsg.toString( );}
                              //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                              MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                              //:RETURN -1
                              if(8==8)return( -1 );
                           } 

                           //:END
                           //:dAmount = dAmount * .5
                           dAmount = dAmount * .5;
                           //:dAmountInteger = dAmount
                           dAmountInteger = (int) dAmount;
                           //:dAmount = 0 - dAmountInteger      // Charge is negative of dAmount.
                           dAmount = 0 - dAmountInteger;
                           //:szDesc = mSAChrgT.SATransactionCode.Description + " PBS Discount - " + mSAProf2.TermCourse.Number + "-" + szSection
                           {StringBuilder sb_szDesc;
                           if ( szDesc == null )
                              sb_szDesc = new StringBuilder( 32 );
                           else
                              sb_szDesc = new StringBuilder( szDesc );
                                                       GetStringFromAttribute( sb_szDesc, mSAChrgT, "SATransactionCode", "Description" );
                           szDesc = sb_szDesc.toString( );}
                            {StringBuilder sb_szDesc;
                           if ( szDesc == null )
                              sb_szDesc = new StringBuilder( 32 );
                           else
                              sb_szDesc = new StringBuilder( szDesc );
                                                      ZeidonStringConcat( sb_szDesc, 1, 0, " PBS Discount - ", 1, 0, 301 );
                           szDesc = sb_szDesc.toString( );}
                           {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
                           StringBuilder sb_szTempString_5;
                           if ( szTempString_5 == null )
                              sb_szTempString_5 = new StringBuilder( 32 );
                           else
                              sb_szTempString_5 = new StringBuilder( szTempString_5 );
                                                       GetVariableFromAttribute( sb_szTempString_5, mi_lTempInteger_9, 'S', 11, mSAProf2, "TermCourse", "Number", "", 0 );
                           lTempInteger_9 = mi_lTempInteger_9.intValue( );
                           szTempString_5 = sb_szTempString_5.toString( );}
                            {StringBuilder sb_szDesc;
                           if ( szDesc == null )
                              sb_szDesc = new StringBuilder( 32 );
                           else
                              sb_szDesc = new StringBuilder( szDesc );
                                                      ZeidonStringConcat( sb_szDesc, 1, 0, szTempString_5, 1, 0, 301 );
                           szDesc = sb_szDesc.toString( );}
                            {StringBuilder sb_szDesc;
                           if ( szDesc == null )
                              sb_szDesc = new StringBuilder( 32 );
                           else
                              sb_szDesc = new StringBuilder( szDesc );
                                                      ZeidonStringConcat( sb_szDesc, 1, 0, "-", 1, 0, 301 );
                           szDesc = sb_szDesc.toString( );}
                            {StringBuilder sb_szDesc;
                           if ( szDesc == null )
                              sb_szDesc = new StringBuilder( 32 );
                           else
                              sb_szDesc = new StringBuilder( szDesc );
                                                      ZeidonStringConcat( sb_szDesc, 1, 0, szSection, 1, 0, 301 );
                           szDesc = sb_szDesc.toString( );}
                           //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, szDesc )
                           nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, szDesc );
                        } 


                        //:END 
                     } 

                     //:END
                     //:ELSE
                  } 
                  else
                  { 
                     //:// Refund.
                     //:// Note that the ProcessInternalRule operation is called for each existing SA Transaction, meaning it is called
                     //:// for each Class Charge. Since we are in a loop for each Class for the Semester, we only want to handle a Class
                     //:// that is the same as the PeriodTransApplied entry for which we're processing the Refund.
                     //:// Thus, two things have to be true in order to process this Refund.
                     //:// 1. There has to be a match on the transaction description.
                     //:// 2. The Amount must have changed.
                     //:// We'll process a Refund if the Amount has changed.
                     //:IF mSAProf.PeriodTransApplied.Description = szDesc
                     if ( CompareAttributeToString( mSAProf, "PeriodTransApplied", "Description", szDesc ) == 0 )
                     { 
                        //:IF dAmount != mSAProf.PeriodTransApplied.Amount 
                        if ( CompareAttributeToDecimal( mSAProf, "PeriodTransApplied", "Amount", dAmount ) != 0 )
                        { 
                           //:GenerateRefundEntry( mSAChrgT, mSAProf )
                           omSAChrgT_GenerateRefundEntry( mSAChrgT, mSAProf );
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

      RESULT = SetCursorNextEntity( mSAProf2, "TermEnrolled", "" );
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
omSAChrgT_CreateInternalRuleVar( View     mSAChrgT,
                                 View     mSAChrgI,
                                 String   VariableName )
{
   int      RESULT = 0;

   //:CreateInternalRuleVar( VIEW mSAChrgT BASED ON LOD mSAChrgT,
   //:                    VIEW mSAChrgI BASED ON LOD mSAChrgI,
   //:                    STRING ( 100 ) VariableName )

   //:SET CURSOR FIRST mSAChrgI.InternalRuleVariableDef WHERE mSAChrgI.InternalRuleVariableDef.Name = VariableName
   RESULT = SetCursorFirstEntityByString( mSAChrgI, "InternalRuleVariableDef", "Name", VariableName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mSAChrgI.InternalRuleVariableDef 
      RESULT = CreateEntity( mSAChrgI, "InternalRuleVariableDef", zPOS_AFTER );
      //:mSAChrgI.InternalRuleVariableDef.Name = VariableName
      SetAttributeFromString( mSAChrgI, "InternalRuleVariableDef", "Name", VariableName );
   } 

   //:END
   //:mSAChrgI.InternalRuleVariableDef.wSelectedFlag = "Y"
   SetAttributeFromString( mSAChrgI, "InternalRuleVariableDef", "wSelectedFlag", "Y" );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:CheckPBS_ForClass( VIEW mSAChrgT BASED ON LOD mSAChrgT,
//:                   VIEW mSAProf  BASED ON LOD mSAProf )

//:   VIEW lProspct    BASED ON LOD lProspct
private int 
omSAChrgT_CheckPBS_ForClass( View     mSAChrgT,
                             View     mSAProf )
{
   zVIEW    lProspct = new zVIEW( );
   //:VIEW mCollegeLST BASED ON LOD mCollege
   zVIEW    mCollegeLST = new zVIEW( );
   //:STRING ( 4 )  szCourseNumber4
   String   szCourseNumber4 = null;
   //:STRING ( 20 ) szCourseNumber
   String   szCourseNumber = null;
   //:SHORT nRC
   int      nRC = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   String   szTempString_3 = null;


   //:// Check if the TermClass entity in mSAProf is valid for a PBS discount.
   //:// A return code of 1 indicates the TermClass is valid for discount. A 0 means it is not.
   //:// The conditions for discount are as follows.
   //:// 1. The Student has a Prospect Category or Student Category of "PBS Student".
   //:// 2. The Class is not a graduate class (ie., it is not for a graduate College).
   //:// 3. It's not an ESOL or NURS class.
   //:// 4. It's not a student teaching course, EDUC465, EDUC475, EDUC485.
   //:// 5. It's not a Section 20 Class.

   //:// Check for Prospect Category of "PBS Student" or Student Category of "PBS Student".
   //:// Note that we'll cache the lProspctPBS object for processing another Class for the Student.
   //:GET VIEW lProspct NAMED "lProspctPBS"
   RESULT = GetViewByName( lProspct, "lProspctPBS", mSAChrgT, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:IF lProspct.Person.ID != mSAProf.Person.ID
      if ( CompareAttributeToAttribute( lProspct, "Person", "ID", mSAProf, "Person", "ID" ) != 0 )
      { 
         //:DropObjectInstance( lProspct )
         DropObjectInstance( lProspct );
         //:ACTIVATE lProspct WHERE lProspct.Person.ID = mSAProf.Person.ID
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf, "Person", "ID" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         //:    RESTRICTING lProspct.Category TO lProspct.Category.Name = "PBS Student"
         omSAChrgT_fnLocalBuildQual_0( mSAChrgT, vTempViewVar_0, lTempInteger_0 );
         RESULT = ActivateObjectInstance( lProspct, "lProspct", mSAChrgT, vTempViewVar_0, zSINGLE );
         DropView( vTempViewVar_0 );
         //:NAME VIEW lProspct "lProspctPBS"
         SetNameForView( lProspct, "lProspctPBS", null, zLEVEL_TASK );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE lProspct WHERE lProspct.Person.ID = mSAProf.Person.ID
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, mSAProf, "Person", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      //:    RESTRICTING lProspct.Category TO lProspct.Category.Name = "PBS Student"
      omSAChrgT_fnLocalBuildQual_1( mSAChrgT, vTempViewVar_1, lTempInteger_1 );
      RESULT = ActivateObjectInstance( lProspct, "lProspct", mSAChrgT, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:NAME VIEW lProspct "lProspctPBS"
      SetNameForView( lProspct, "lProspctPBS", null, zLEVEL_TASK );
   } 

   //:END

   //:// We've had a legacy problem when the Student had no Prospect record, so we will check for it here.
   //:IF lProspct.Prospect DOES NOT EXIST
   lTempInteger_2 = CheckExistenceOfEntity( lProspct, "Prospect" );
   if ( lTempInteger_2 != 0 )
   { 
      //:DropObjectInstance( lProspct )
      DropObjectInstance( lProspct );
      //:RETURN 0
      if(8==8)return( 0 );
   } 

   //:END

   //:SET CURSOR FIRST lProspct.Category WHERE lProspct.Category.Name = "PBS Student"
   RESULT = SetCursorFirstEntityByString( lProspct, "Category", "Name", "PBS Student", "" );
   //:nRC = RESULT
   nRC = RESULT;
   //:SET CURSOR FIRST mSAProf.Category WHERE mSAProf.Category.Name = "PBS Student"
   RESULT = SetCursorFirstEntityByString( mSAProf, "Category", "Name", "PBS Student", "" );
   //:IF RESULT < 0 AND nRC < 0
   if ( RESULT < 0 && nRC < 0 )
   { 
      //:RETURN 0    // Neither Prospect nor Student are PBS Student.
      if(8==8)return( 0 );
   } 

   //:END

   //:// Check for graduate class.
   //:GET VIEW mCollegeLST NAMED "mCollegeLST"
   RESULT = GetViewByName( mCollegeLST, "mCollegeLST", mSAChrgT, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mCollegeLST Multiple
      RESULT = ActivateObjectInstance( mCollegeLST, "mCollege", mSAChrgT, 0, zMULTIPLE );
      //:NAME VIEW mCollegeLST "mCollegeLST"
      SetNameForView( mCollegeLST, "mCollegeLST", null, zLEVEL_TASK );
   } 

   //:END
   //:SET CURSOR FIRST mCollegeLST.College WHERE mCollegeLST.College.ID = mSAProf.TermCourseCollege.ID 
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, mSAProf, "TermCourseCollege", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mCollegeLST, "College", "ID", lTempInteger_3, "" );
   //:IF mCollegeLST.College.Type = "G"
   if ( CompareAttributeToString( mCollegeLST, "College", "Type", "G" ) == 0 )
   { 
      //:TraceLineS( "*** No PBS Discount because Class is Graduate for: ", mSAProf.TermCourse.Number )
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, mSAProf, "TermCourse", "Number" );
      szTempString_0 = sb_szTempString_0.toString( );}
      TraceLineS( "*** No PBS Discount because Class is Graduate for: ", szTempString_0 );
      //:RETURN 0    // Return if graduate class.   
      if(8==8)return( 0 );
   } 

   //:END

   //:// Check for ESOL, NURS, EDUC465, EDUC475, EDUC485.
   //:szCourseNumber = mSAProf.TermCourse.Number
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
   StringBuilder sb_szCourseNumber;
   if ( szCourseNumber == null )
      sb_szCourseNumber = new StringBuilder( 32 );
   else
      sb_szCourseNumber = new StringBuilder( szCourseNumber );
       GetVariableFromAttribute( sb_szCourseNumber, mi_lTempInteger_4, 'S', 21, mSAProf, "TermCourse", "Number", "", 0 );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );
   szCourseNumber = sb_szCourseNumber.toString( );}
   //:szCourseNumber4 = szCourseNumber
    {StringBuilder sb_szCourseNumber4;
   if ( szCourseNumber4 == null )
      sb_szCourseNumber4 = new StringBuilder( 32 );
   else
      sb_szCourseNumber4 = new StringBuilder( szCourseNumber4 );
      ZeidonStringCopy( sb_szCourseNumber4, 1, 0, szCourseNumber, 1, 0, 5 );
   szCourseNumber4 = sb_szCourseNumber4.toString( );}
   //:IF szCourseNumber4 = "ESOL" OR szCourseNumber4 = "NURS"
   if ( ZeidonStringCompare( szCourseNumber4, 1, 0, "ESOL", 1, 0, 5 ) == 0 || ZeidonStringCompare( szCourseNumber4, 1, 0, "NURS", 1, 0, 5 ) == 0 )
   { 
      //:TraceLineS( "*** No PBS Discount because Class is ESOL or NURS for: ", mSAProf.TermCourse.Number )
      {StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
             GetStringFromAttribute( sb_szTempString_1, mSAProf, "TermCourse", "Number" );
      szTempString_1 = sb_szTempString_1.toString( );}
      TraceLineS( "*** No PBS Discount because Class is ESOL or NURS for: ", szTempString_1 );
      //:RETURN 0    // Return if ESOL or NURS.
      if(8==8)return( 0 );
   } 

   //:END
   //:IF szCourseNumber = "EDUC465" OR szCourseNumber = "EDUC475" OR szCourseNumber = "EDUC485"
   if ( ZeidonStringCompare( szCourseNumber, 1, 0, "EDUC465", 1, 0, 21 ) == 0 || ZeidonStringCompare( szCourseNumber, 1, 0, "EDUC475", 1, 0, 21 ) == 0 || ZeidonStringCompare( szCourseNumber, 1, 0, "EDUC485", 1, 0, 21 ) == 0 )
   { 
      //:TraceLineS( "*** No PBS Discount because Class is EDUCxxx for: ", mSAProf.TermCourse.Number )
      {StringBuilder sb_szTempString_2;
      if ( szTempString_2 == null )
         sb_szTempString_2 = new StringBuilder( 32 );
      else
         sb_szTempString_2 = new StringBuilder( szTempString_2 );
             GetStringFromAttribute( sb_szTempString_2, mSAProf, "TermCourse", "Number" );
      szTempString_2 = sb_szTempString_2.toString( );}
      TraceLineS( "*** No PBS Discount because Class is EDUCxxx for: ", szTempString_2 );
      //:RETURN 0    // Return if EDUC465, EDUC475, or EDUC485.
      if(8==8)return( 0 );
   } 

   //:END

   //:// Check for Section 20.
   //:IF mSAProf.TermClass.Section = "20"
   if ( CompareAttributeToString( mSAProf, "TermClass", "Section", "20" ) == 0 )
   { 
      //:TraceLineS( "*** No PBS Discount because Class is Section 20 for: ", mSAProf.TermCourse.Number )
      {StringBuilder sb_szTempString_3;
      if ( szTempString_3 == null )
         sb_szTempString_3 = new StringBuilder( 32 );
      else
         sb_szTempString_3 = new StringBuilder( szTempString_3 );
             GetStringFromAttribute( sb_szTempString_3, mSAProf, "TermCourse", "Number" );
      szTempString_3 = sb_szTempString_3.toString( );}
      TraceLineS( "*** No PBS Discount because Class is Section 20 for: ", szTempString_3 );
      //:RETURN 0    // Return if Section 20
      if(8==8)return( 0 );
   } 

   //:END

   //:// If we've gotten to here, it must be OK, so return 1.
   //:RETURN 1
   return( 1 );
// END
} 


//:LOCAL OPERATION
//:Rule_DL_Tuition( VIEW mSAChrgT BASED ON LOD mSAChrgT,
//:                 VIEW mSAProf  BASED ON LOD mSAProf,
//:                 STRING ( 1 )   szRefundFlag )

//:   VIEW mSAProf2    BASED ON LOD  mSAProf
private int 
omSAChrgT_Rule_DL_Tuition( View     mSAChrgT,
                           View     mSAProf,
                           String   szRefundFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:VIEW mCollegeLST BASED ON LOD  mCollege
   zVIEW    mCollegeLST = new zVIEW( );
   //:VIEW wXferO      REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 300 ) szMsg
   String   szMsg = null;
   //:STRING ( 300 ) szDesc
   String   szDesc = null;
   //:STRING ( 4 )   szCourseNumber4
   String   szCourseNumber4 = null;
   //:STRING ( 3 )   szSection
   String   szSection = null;
   //:STRING ( 1 )   szCERT_flag
   String   szCERT_flag = null;
   //:STRING ( 1 )   szSUCT_flag
   String   szSUCT_flag = null;
   //:DECIMAL        dChargePerCreditHour
   double  dChargePerCreditHour = 0.0;
   //:DECIMAL        dCredits
   double  dCredits = 0.0;
   //:DECIMAL        dChargePerClass
   double  dChargePerClass = 0.0;
   //:DECIMAL        dAmount
   double  dAmount = 0.0;
   //:SHORT          nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_3 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_6 = 0;
   String   szTempString_4 = null;
   int      lTempInteger_7 = 0;
   String   szTempString_5 = null;
   int      lTempInteger_8 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mSAChrgT, zLEVEL_TASK );

   //:// Process Distance Learning Tuition Charges Per Class.
   //:// Charges are generated here under the following conditions:
   //:// 1. The Student has the AdultStudiesFlag set OR
   //:// 2, The Class has the AdultStudiesFlag set.

   //:// There are four different ways that charges can be computed for DL Tuition, but it is always per Class.
   //:// 1. We use the "SUCT Chg" amount per class under the following conditions:
   //://    - Category is "SW Union Conference Teacher" or "SW Union Conference Teacher MED"
   //:// 2. We use the "CERT Chg/CR" amount per class under the following conditions:
   //://    - Category is "ADP Student" or "Alternative Certification Program" and
   //://    - All Classes for the Term are "CERT" classes
   //:// 3. We use the "Grad Chg/CR" amount per credit if the Class is a graduate Class. (This is determined by
   //://    the Course being tied to the College, "Graduate Studies".
   //:// 4. We use the "DL Chg/CR" amount per credit for any class not covered above.

   //:// In addition, there can be a PBS Discount applied to a Class. 
   //:// The rules identifying Student and Class eligibility for a PBS Discount are defined separately and those rules 
   //:// apply for all tuition and college terms. However, the amount of the discount is specified per Transaction Item / Term
   //:// using the value, "PBS Discount". 
   //:// A specified amount is used instead of a percent because a rounded percent might not be give the exact value desired
   //:// and because the percent is different for ADP studnets in Summer Term versus Fall and Spring Terms.


   //:CreateViewFromView( mSAProf2, mSAProf )
   CreateViewFromView( mSAProf2, mSAProf );
   //:NAME VIEW mSAProf2 "mSAProf2BillingRules"
   SetNameForView( mSAProf2, "mSAProf2BillingRules", null, zLEVEL_TASK );

   //:// First check to see if all Classes are "CERT" Classes and set a flag if so. This is used for a CERT test below.
   //:szCERT_flag = "Y"
    {StringBuilder sb_szCERT_flag;
   if ( szCERT_flag == null )
      sb_szCERT_flag = new StringBuilder( 32 );
   else
      sb_szCERT_flag = new StringBuilder( szCERT_flag );
      ZeidonStringCopy( sb_szCERT_flag, 1, 0, "Y", 1, 0, 2 );
   szCERT_flag = sb_szCERT_flag.toString( );}
   //:FOR EACH mSAProf2.TermEnrolled WHERE mSAProf2.TermEnrolled.Status != "D"
   RESULT = SetCursorFirstEntity( mSAProf2, "TermEnrolled", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "D" ) != 0 )
      { 
         //:szCourseNumber4 = mSAProf2.TermCourse.Number
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szCourseNumber4;
         if ( szCourseNumber4 == null )
            sb_szCourseNumber4 = new StringBuilder( 32 );
         else
            sb_szCourseNumber4 = new StringBuilder( szCourseNumber4 );
                   GetVariableFromAttribute( sb_szCourseNumber4, mi_lTempInteger_0, 'S', 5, mSAProf2, "TermCourse", "Number", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szCourseNumber4 = sb_szCourseNumber4.toString( );}
         //:IF szCourseNumber4 != "CERT"
         if ( ZeidonStringCompare( szCourseNumber4, 1, 0, "CERT", 1, 0, 5 ) != 0 )
         { 
            //:szCERT_flag = ""
             {StringBuilder sb_szCERT_flag;
            if ( szCERT_flag == null )
               sb_szCERT_flag = new StringBuilder( 32 );
            else
               sb_szCERT_flag = new StringBuilder( szCERT_flag );
                        ZeidonStringCopy( sb_szCERT_flag, 1, 0, "", 1, 0, 2 );
            szCERT_flag = sb_szCERT_flag.toString( );}
         } 

      } 

      RESULT = SetCursorNextEntity( mSAProf2, "TermEnrolled", "" );
      //:END
   } 

   //:END
   //:   
   //:// Loop through each Class and generate charge and option discount transactions based on Section, credits and
   //:// other Student/Class information. 
   //:// The charge per credit or per Class is defined in SAInternalRuleVariable.
   //:// Only do for Distance Learning Classes or Students.
   //:FOR EACH mSAProf2.TermEnrolled WHERE mSAProf2.TermEnrolled.Status != "D"
   RESULT = SetCursorFirstEntity( mSAProf2, "TermEnrolled", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( mSAProf2, "TermEnrolled", "Status", "D" ) != 0 )
      { 

         //:szSection = mSAProf2.TermClass.Section 
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szSection;
         if ( szSection == null )
            sb_szSection = new StringBuilder( 32 );
         else
            sb_szSection = new StringBuilder( szSection );
                   GetVariableFromAttribute( sb_szSection, mi_lTempInteger_1, 'S', 4, mSAProf2, "TermClass", "Section", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szSection = sb_szSection.toString( );}
         //://SET CURSOR FIRST mSAProf2.Category WHERE mSAProf2.Category.Name = "Distance Learning"
         //://                                      OR mSAProf2.Category.Name = "ADP Student"
         //://IF RESULT >= zCURSOR_SET OR mSAProf2.TermClass.AdultStudiesFlag = "Y" 
         //:IF mSAProf.Student.AdultStudiesFlag = "Y" OR mSAProf2.TermClass.AdultStudiesFlag = "Y" 
         if ( CompareAttributeToString( mSAProf, "Student", "AdultStudiesFlag", "Y" ) == 0 || CompareAttributeToString( mSAProf2, "TermClass", "AdultStudiesFlag", "Y" ) == 0 )
         { 

            //:dCredits = mSAProf2.TermEnrolled.CreditHours 
            {MutableDouble md_dCredits = new MutableDouble( dCredits );
                         GetDecimalFromAttribute( md_dCredits, mSAProf2, "TermEnrolled", "CreditHours" );
            dCredits = md_dCredits.doubleValue( );}


            //:// TUITION CHARGE FROM RULE VARIABLE
            //:// In the following cases, the charge (dAmount) is determined by positioning on the correct Rule Variable.
            //:// We first position on the correct Rule Variable, and then create the charge.

            //:// SUCT Class Charge
            //:// The Student must be a SW Union Conference Teacher and the Class Section must be 50.
            //:// Charge is single value in Rule Variable.
            //:SET CURSOR FIRST mSAProf2.Category WHERE mSAProf2.Category.Name = "SW Union Conference Teacher"
            //:                                   OR mSAProf2.Category.Name = "SW Union Conference Teacher MED"
            RESULT = SetCursorFirstEntity( mSAProf2, "Category", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSAProf2, "Category", "Name", "SW Union Conference Teacher" ) != 0 && CompareAttributeToString( mSAProf2, "Category", "Name", "SW Union Conference Teacher MED" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( mSAProf2, "Category", "" );
               } 

            } 

            //:IF RESULT >= zCURSOR_SET AND mSAProf2.TermClass.AdultStudiesFlag = "Y" 
            if ( RESULT >= zCURSOR_SET && CompareAttributeToString( mSAProf2, "TermClass", "AdultStudiesFlag", "Y" ) == 0 )
            { 

               //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "SUCT Chg"
               RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "SUCT Chg", "" );
               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:szMsg = "No Rule Variable match on DL Tuition 'SUCT Chg'"
                   {StringBuilder sb_szMsg;
                  if ( szMsg == null )
                     sb_szMsg = new StringBuilder( 32 );
                  else
                     sb_szMsg = new StringBuilder( szMsg );
                                    ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on DL Tuition 'SUCT Chg'", 1, 0, 301 );
                  szMsg = sb_szMsg.toString( );}
                  //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:RETURN -1
                  if(8==8)return( -1 );
               } 

               //:END
               //:dAmount = mSAChrgT.SAInternalRuleVariable.Value
               {MutableDouble md_dAmount = new MutableDouble( dAmount );
                               GetDecimalFromAttribute( md_dAmount, mSAChrgT, "SAInternalRuleVariable", "Value" );
               dAmount = md_dAmount.doubleValue( );}
               //:szDesc = mSAChrgT.SATransactionCode.Description + " - " + mSAProf2.TermCourse.Number + "-" + szSection + " SUCT"
               {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                               GetStringFromAttribute( sb_szDesc, mSAChrgT, "SATransactionCode", "Description" );
               szDesc = sb_szDesc.toString( );}
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringConcat( sb_szDesc, 1, 0, " - ", 1, 0, 301 );
               szDesc = sb_szDesc.toString( );}
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 11, mSAProf2, "TermCourse", "Number", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringConcat( sb_szDesc, 1, 0, szTempString_0, 1, 0, 301 );
               szDesc = sb_szDesc.toString( );}
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringConcat( sb_szDesc, 1, 0, "-", 1, 0, 301 );
               szDesc = sb_szDesc.toString( );}
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringConcat( sb_szDesc, 1, 0, szSection, 1, 0, 301 );
               szDesc = sb_szDesc.toString( );}
                {StringBuilder sb_szDesc;
               if ( szDesc == null )
                  sb_szDesc = new StringBuilder( 32 );
               else
                  sb_szDesc = new StringBuilder( szDesc );
                              ZeidonStringConcat( sb_szDesc, 1, 0, " SUCT", 1, 0, 301 );
               szDesc = sb_szDesc.toString( );}

               //:// Indicate that this Class has been charged for SUCT so that the Student will not get a PBS Discount for the
               //:// Class later in this code.
               //:szSUCT_flag = "Y"
                {StringBuilder sb_szSUCT_flag;
               if ( szSUCT_flag == null )
                  sb_szSUCT_flag = new StringBuilder( 32 );
               else
                  sb_szSUCT_flag = new StringBuilder( szSUCT_flag );
                              ZeidonStringCopy( sb_szSUCT_flag, 1, 0, "Y", 1, 0, 2 );
               szSUCT_flag = sb_szSUCT_flag.toString( );}

               //:ELSE
            } 
            else
            { 

               //:// CERT Class Charge/Credit
               //:// Two things must be true to use the CERT charge/credit:
               //:// 1. The Student must be an "ADP Student" or "Alternative Certification Program" student.
               //:// 2. All Classes for the Term must be "CERT" classes.
               //:SET CURSOR FIRST mSAProf2.Category WHERE mSAProf2.Category.Name = "ADP Student"
               //:                                   OR mSAProf2.Category.Name = "Alternative Certification Program"
               RESULT = SetCursorFirstEntity( mSAProf2, "Category", "" );
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSAProf2, "Category", "Name", "ADP Student" ) != 0 && CompareAttributeToString( mSAProf2, "Category", "Name", "Alternative Certification Program" ) != 0 ) )
                  { 
                     RESULT = SetCursorNextEntity( mSAProf2, "Category", "" );
                  } 

               } 

               //:IF RESULT >= zCURSOR_SET AND szCERT_flag = "Y"
               if ( RESULT >= zCURSOR_SET && ZeidonStringCompare( szCERT_flag, 1, 0, "Y", 1, 0, 2 ) == 0 )
               { 

                  //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "CERT Chg/CR"
                  RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "CERT Chg/CR", "" );
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:szMsg = "No Rule Variable match on DL Tuition CERT"
                      {StringBuilder sb_szMsg;
                     if ( szMsg == null )
                        sb_szMsg = new StringBuilder( 32 );
                     else
                        sb_szMsg = new StringBuilder( szMsg );
                                          ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on DL Tuition CERT", 1, 0, 301 );
                     szMsg = sb_szMsg.toString( );}
                     //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                     MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                     //:RETURN -1
                     if(8==8)return( -1 );
                  } 

                  //:END
                  //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
                  {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                     GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
                  dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                  //:dAmount = dChargePerCreditHour * dCredits
                  dAmount = dChargePerCreditHour * dCredits;
                  //:szDesc = mSAChrgT.SATransactionCode.Description + " - " + mSAProf2.TermCourse.Number + "-" + szSection + " CERT"
                  {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                     GetStringFromAttribute( sb_szDesc, mSAChrgT, "SATransactionCode", "Description" );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, " - ", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_3, 'S', 11, mSAProf2, "TermCourse", "Number", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szTempString_1 = sb_szTempString_1.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, szTempString_1, 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, "-", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, szSection, 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, " CERT", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}

                  //:ELSE
               } 
               else
               { 

                  //:// Church Ministry Lay Leader Class Charge/Credit
                  //:// Two things must be true to use this charge/credit:
                  //:// 1. The Student must be an "ADP Student" or "Alternative Certification Program" student.
                  //:// 2. The Class is one of the following: RLGN101, RLGN211, RLGN220, RLGN230, RLGN285.
                  //:SET CURSOR FIRST mSAProf2.Category WHERE mSAProf2.Category.Name = "ADP Student"
                  RESULT = SetCursorFirstEntityByString( mSAProf2, "Category", "Name", "ADP Student", "" );
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:SET CURSOR FIRST mSAProf2.Category WHERE mSAProf2.Category.Name = "Church Ministry Lay Leader"
                     RESULT = SetCursorFirstEntityByString( mSAProf2, "Category", "Name", "Church Ministry Lay Leader", "" );
                  } 

                  //:END
                  //:IF RESULT >= zCURSOR_SET AND ( mSAProf2.TermCourse.Number = "RLGN101" OR
                  //:                            mSAProf2.TermCourse.Number = "RLGN211" OR
                  //:                            mSAProf2.TermCourse.Number = "RLGN220" OR
                  //:                            mSAProf2.TermCourse.Number = "RLGN230" OR
                  //:                            mSAProf2.TermCourse.Number = "RLGN285" )
                  if ( RESULT >= zCURSOR_SET && ( CompareAttributeToString( mSAProf2, "TermCourse", "Number", "RLGN101" ) == 0 || CompareAttributeToString( mSAProf2, "TermCourse", "Number", "RLGN211" ) == 0 ||
                       CompareAttributeToString( mSAProf2, "TermCourse", "Number", "RLGN220" ) == 0 || CompareAttributeToString( mSAProf2, "TermCourse", "Number", "RLGN230" ) == 0 ||
                       CompareAttributeToString( mSAProf2, "TermCourse", "Number", "RLGN285" ) == 0 ) )
                  { 

                     //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "SUCT Chg"
                     RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "SUCT Chg", "" );
                     //:IF RESULT < zCURSOR_SET
                     if ( RESULT < zCURSOR_SET )
                     { 
                        //:szMsg = "No Rule Variable match on DL Tuition SUCT"
                         {StringBuilder sb_szMsg;
                        if ( szMsg == null )
                           sb_szMsg = new StringBuilder( 32 );
                        else
                           sb_szMsg = new StringBuilder( szMsg );
                                                ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on DL Tuition SUCT", 1, 0, 301 );
                        szMsg = sb_szMsg.toString( );}
                        //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                        MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                        //:RETURN -1
                        if(8==8)return( -1 );
                     } 

                     //:END
                     //:dAmount = mSAChrgT.SAInternalRuleVariable.Value
                     {MutableDouble md_dAmount = new MutableDouble( dAmount );
                                           GetDecimalFromAttribute( md_dAmount, mSAChrgT, "SAInternalRuleVariable", "Value" );
                     dAmount = md_dAmount.doubleValue( );}
                     //:szDesc = mSAChrgT.SATransactionCode.Description + " - " + mSAProf2.TermCourse.Number + "-" + szSection + " CMLL"
                     {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                           GetStringFromAttribute( sb_szDesc, mSAChrgT, "SATransactionCode", "Description" );
                     szDesc = sb_szDesc.toString( );}
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringConcat( sb_szDesc, 1, 0, " - ", 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}
                     {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                     StringBuilder sb_szTempString_2;
                     if ( szTempString_2 == null )
                        sb_szTempString_2 = new StringBuilder( 32 );
                     else
                        sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                           GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_4, 'S', 11, mSAProf2, "TermCourse", "Number", "", 0 );
                     lTempInteger_4 = mi_lTempInteger_4.intValue( );
                     szTempString_2 = sb_szTempString_2.toString( );}
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringConcat( sb_szDesc, 1, 0, szTempString_2, 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringConcat( sb_szDesc, 1, 0, "-", 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringConcat( sb_szDesc, 1, 0, szSection, 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}
                      {StringBuilder sb_szDesc;
                     if ( szDesc == null )
                        sb_szDesc = new StringBuilder( 32 );
                     else
                        sb_szDesc = new StringBuilder( szDesc );
                                          ZeidonStringConcat( sb_szDesc, 1, 0, " CMLL", 1, 0, 301 );
                     szDesc = sb_szDesc.toString( );}

                     //:ELSE
                  } 
                  else
                  { 

                     //:// Graduate Class Charge/Credit
                     //:// The Class the Student is taking is a graduate class (The Course is tied to a Graduate College)
                     //:GET VIEW mCollegeLST NAMED "mCollegeLST"
                     RESULT = GetViewByName( mCollegeLST, "mCollegeLST", mSAChrgT, zLEVEL_TASK );
                     //:IF RESULT < 0
                     if ( RESULT < 0 )
                     { 
                        //:ACTIVATE mCollegeLST Multiple
                        RESULT = ActivateObjectInstance( mCollegeLST, "mCollege", mSAChrgT, 0, zMULTIPLE );
                        //:NAME VIEW mCollegeLST "mCollegeLST"
                        SetNameForView( mCollegeLST, "mCollegeLST", null, zLEVEL_TASK );
                     } 

                     //:END
                     //:SET CURSOR FIRST mCollegeLST.College WHERE mCollegeLST.College.ID = mSAProf.TermCourseCollege.ID 
                     {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                                           GetIntegerFromAttribute( mi_lTempInteger_5, mSAProf, "TermCourseCollege", "ID" );
                     lTempInteger_5 = mi_lTempInteger_5.intValue( );}
                     RESULT = SetCursorFirstEntityByInteger( mCollegeLST, "College", "ID", lTempInteger_5, "" );
                     //:IF mCollegeLST.College.Type = "G"
                     if ( CompareAttributeToString( mCollegeLST, "College", "Type", "G" ) == 0 )
                     { 
                        //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "Grad Chg/CR"
                        RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "Grad Chg/CR", "" );
                        //:IF RESULT < zCURSOR_SET
                        if ( RESULT < zCURSOR_SET )
                        { 
                           //:szMsg = "No Rule Variable match on DL Tuition 'Grad Chg/CR'"
                            {StringBuilder sb_szMsg;
                           if ( szMsg == null )
                              sb_szMsg = new StringBuilder( 32 );
                           else
                              sb_szMsg = new StringBuilder( szMsg );
                                                      ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on DL Tuition 'Grad Chg/CR'", 1, 0, 301 );
                           szMsg = sb_szMsg.toString( );}
                           //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                           MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                           //:RETURN -1
                           if(8==8)return( -1 );
                        } 

                        //:END
                        //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
                        {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                                 GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
                        dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                        //:dAmount = dChargePerCreditHour * dCredits
                        dAmount = dChargePerCreditHour * dCredits;
                        //:szDesc = mSAChrgT.SATransactionCode.Description + " Grad - " + mSAProf2.TermCourse.Number + "-" + szSection
                        {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                 GetStringFromAttribute( sb_szDesc, mSAChrgT, "SATransactionCode", "Description" );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, " Grad - ", 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                        {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                        StringBuilder sb_szTempString_3;
                        if ( szTempString_3 == null )
                           sb_szTempString_3 = new StringBuilder( 32 );
                        else
                           sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                                 GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_6, 'S', 11, mSAProf2, "TermCourse", "Number", "", 0 );
                        lTempInteger_6 = mi_lTempInteger_6.intValue( );
                        szTempString_3 = sb_szTempString_3.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, szTempString_3, 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, "-", 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, szSection, 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}

                        //:ELSE
                     } 
                     else
                     { 

                        //:// Anything else gets normal DL Charge / Credit..
                        //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "DL Chg/CR"
                        RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "DL Chg/CR", "" );
                        //:IF RESULT < zCURSOR_SET
                        if ( RESULT < zCURSOR_SET )
                        { 
                           //:szMsg = "No Rule Variable match on DL Tuition 'DL Chg/CR'"
                            {StringBuilder sb_szMsg;
                           if ( szMsg == null )
                              sb_szMsg = new StringBuilder( 32 );
                           else
                              sb_szMsg = new StringBuilder( szMsg );
                                                      ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on DL Tuition 'DL Chg/CR'", 1, 0, 301 );
                           szMsg = sb_szMsg.toString( );}
                           //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                           MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                           //:RETURN -1
                           if(8==8)return( -1 );
                        } 

                        //:END
                        //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
                        {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                                 GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
                        dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                        //:dAmount = dChargePerCreditHour * dCredits
                        dAmount = dChargePerCreditHour * dCredits;
                        //:szDesc = mSAChrgT.SATransactionCode.Description + " - " + mSAProf2.TermCourse.Number + "-" + szSection
                        {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                 GetStringFromAttribute( sb_szDesc, mSAChrgT, "SATransactionCode", "Description" );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, " - ", 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                        {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                        StringBuilder sb_szTempString_4;
                        if ( szTempString_4 == null )
                           sb_szTempString_4 = new StringBuilder( 32 );
                        else
                           sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                                 GetVariableFromAttribute( sb_szTempString_4, mi_lTempInteger_7, 'S', 11, mSAProf2, "TermCourse", "Number", "", 0 );
                        lTempInteger_7 = mi_lTempInteger_7.intValue( );
                        szTempString_4 = sb_szTempString_4.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, szTempString_4, 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, "-", 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                         {StringBuilder sb_szDesc;
                        if ( szDesc == null )
                           sb_szDesc = new StringBuilder( 32 );
                        else
                           sb_szDesc = new StringBuilder( szDesc );
                                                ZeidonStringConcat( sb_szDesc, 1, 0, szSection, 1, 0, 301 );
                        szDesc = sb_szDesc.toString( );}
                     } 


                     //:END
                  } 

                  //:END
               } 


               //:END
            } 

            //:END

            //:// Process resulting Charge or Refund.
            //:IF szRefundFlag = ""
            if ( ZeidonStringCompare( szRefundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
            { 
               //:// Regular Charge, not Refund. Generate entry if there is a positive amount.
               //:IF dAmount > 0
               if ( dAmount > 0 )
               { 
                  //:// Generate the charge if it isn't already there.
                  //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, szDesc )
                  nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, szDesc );
                  //:IF nRC >= 0
                  if ( nRC >= 0 )
                  { 
                     //:// Add Course Number and Class Section to description.
                     //:mSAProf.StudentAccountTransApplied.Description = szDesc
                     SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szDesc );
                  } 

                  //:END
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// Refund.
               //:// Note that the ProcessInternalRule operation is called for each existing SA Transaction, meaning it is called
               //:// for each Class Charge. Since we are in a loop for each Class for the Semester, we only want to handle a Class
               //:// that is the same as the PeriodTransApplied entry for which we're processing the Refund.
               //:// Thus, two things have to be true in order to process this Refund.
               //:// 1. There has to be a match on the transaction description.
               //:// 2. The Amount must have changed.
               //:// We'll process a Refund if the Amount has changed.
               //:IF mSAProf.PeriodTransApplied.Description = szDesc
               if ( CompareAttributeToString( mSAProf, "PeriodTransApplied", "Description", szDesc ) == 0 )
               { 
                  //:IF dAmount != mSAProf.PeriodTransApplied.Amount 
                  if ( CompareAttributeToDecimal( mSAProf, "PeriodTransApplied", "Amount", dAmount ) != 0 )
                  { 
                     //:GenerateRefundEntry( mSAChrgT, mSAProf )
                     omSAChrgT_GenerateRefundEntry( mSAChrgT, mSAProf );
                  } 

                  //:END
               } 

               //:END
            } 

            //:END

            //:// PBS Student Discount
            //:// The rules for a PBS Student are found in the operation, CheckPBS_ForClass, which returns a "1" if the Student
            //:// and Class are eligible for PBS Discount. Note CERT classes don't apply.
            //:IF szSUCT_flag = ""    // Only consider non-SUCT charges.
            if ( ZeidonStringCompare( szSUCT_flag, 1, 0, "", 1, 0, 2 ) == 0 )
            { 
               //:nRC = CheckPBS_ForClass( mSAChrgT, mSAProf )
               nRC = omSAChrgT_CheckPBS_ForClass( mSAChrgT, mSAProf );
               //:IF nRC = 1 AND szCERT_flag = ""
               if ( nRC == 1 && ZeidonStringCompare( szCERT_flag, 1, 0, "", 1, 0, 2 ) == 0 )
               { 

                  //:// The PBS Discount is specified as SAInternalRuleVariable.Name "PBS Discount".
                  //:SET CURSOR FIRST mSAChrgT.SAInternalRuleVariable WHERE mSAChrgT.SAInternalRuleVariable.Name = "PBS Discount"
                  RESULT = SetCursorFirstEntityByString( mSAChrgT, "SAInternalRuleVariable", "Name", "PBS Discount", "" );
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:szMsg = "No Rule Variable match on DL Tuition 'PBS Discount'"
                      {StringBuilder sb_szMsg;
                     if ( szMsg == null )
                        sb_szMsg = new StringBuilder( 32 );
                     else
                        sb_szMsg = new StringBuilder( szMsg );
                                          ZeidonStringCopy( sb_szMsg, 1, 0, "No Rule Variable match on DL Tuition 'PBS Discount'", 1, 0, 301 );
                     szMsg = sb_szMsg.toString( );}
                     //:MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                     MessageSend( mSAChrgT, "", "Billing Process Rule", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                     //:RETURN -1
                     if(8==8)return( -1 );
                  } 

                  //:END
                  //:dChargePerCreditHour = mSAChrgT.SAInternalRuleVariable.Value
                  {MutableDouble md_dChargePerCreditHour = new MutableDouble( dChargePerCreditHour );
                                     GetDecimalFromAttribute( md_dChargePerCreditHour, mSAChrgT, "SAInternalRuleVariable", "Value" );
                  dChargePerCreditHour = md_dChargePerCreditHour.doubleValue( );}
                  //:dAmount = dChargePerCreditHour * dCredits
                  dAmount = dChargePerCreditHour * dCredits;
                  //:dAmount = 0 - dAmount      // Charge is negative of dAmount.
                  dAmount = 0 - dAmount;
                  //:szDesc = mSAChrgT.SATransactionCode.Description + " - " + mSAProf2.TermCourse.Number + "-" + szSection + " PBS Discount"
                  {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                     GetStringFromAttribute( sb_szDesc, mSAChrgT, "SATransactionCode", "Description" );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, " - ", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                  {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                  StringBuilder sb_szTempString_5;
                  if ( szTempString_5 == null )
                     sb_szTempString_5 = new StringBuilder( 32 );
                  else
                     sb_szTempString_5 = new StringBuilder( szTempString_5 );
                                     GetVariableFromAttribute( sb_szTempString_5, mi_lTempInteger_8, 'S', 11, mSAProf2, "TermCourse", "Number", "", 0 );
                  lTempInteger_8 = mi_lTempInteger_8.intValue( );
                  szTempString_5 = sb_szTempString_5.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, szTempString_5, 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, "-", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, szSection, 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}
                   {StringBuilder sb_szDesc;
                  if ( szDesc == null )
                     sb_szDesc = new StringBuilder( 32 );
                  else
                     sb_szDesc = new StringBuilder( szDesc );
                                    ZeidonStringConcat( sb_szDesc, 1, 0, " PBS Discount", 1, 0, 301 );
                  szDesc = sb_szDesc.toString( );}

                  //:// Process resulting Charge. Note that we're not processing the Refund for PBS discount right now.
                  //:IF szRefundFlag = ""
                  if ( ZeidonStringCompare( szRefundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
                  { 
                     //:// Regular Charge. Always generate the entry regardless of Amount.
                     //:nRC = GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, szDesc )
                     nRC = omSAChrgT_GenerateChargeEntry( mSAChrgT, mSAProf, dAmount, szDesc );
                     //:IF nRC >= 0
                     if ( nRC >= 0 )
                     { 
                        //:// Add Course Number and Class Section to description.
                        //:mSAProf.StudentAccountTransApplied.Description = szDesc
                        SetAttributeFromString( mSAProf, "StudentAccountTransApplied", "Description", szDesc );
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

      RESULT = SetCursorNextEntity( mSAProf2, "TermEnrolled", "" );
      //:   
      //:END
   } 

   //:END

   //:DropView( mSAProf2 )
   DropView( mSAProf2 );
   return( 0 );
// END
} 



}
