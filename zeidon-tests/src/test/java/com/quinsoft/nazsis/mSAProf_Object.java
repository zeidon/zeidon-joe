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

package  com.quinsoft.nazsis;

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

/*
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
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 
*/

   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
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
omSAProf_fnLocalBuildQual_3( View     vSubtask,
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
                             int      lTempInteger_0,
                             int      lTempInteger_1,
                             int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SAAnnualAdmin" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeTerm" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_6( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_7 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountRuleSet" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountRuleSet" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_7 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_7( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_14 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountRuleSet" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountRuleSet" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_14 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_8( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_21 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentAccountRuleSet" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentAccountRuleSet" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_21 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAProf_fnLocalBuildQual_9( View     vSubtask,
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
omSAProf_fnLocalBuildQual_10( View     vSubtask,
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
omSAProf_fnLocalBuildQual_11( View     vSubtask,
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
omSAProf_fnLocalBuildQual_12( View     vSubtask,
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
omSAProf_fnLocalBuildQual_13( View     vSubtask,
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
omSAProf_fnLocalBuildQual_14( View     vSubtask,
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
omSAProf_fnLocalBuildQual_15( View     vSubtask,
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
omSAProf_fnLocalBuildQual_16( View     vSubtask,
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


//:DERIVED ATTRIBUTE OPERATION
//:dPayTotalWithFee( VIEW mSAProf BASED ON LOD mSAProf,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:DECIMAL szAmount
public int 
omSAProf_dPayTotalWithFee( View     mSAProf,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   double  szAmount = 0.0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;

/*   
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:szAmount=mSAProf.StudentAccountProfile.wPayAmountEntered+mSAProf.StudentAccountProfile.dPayAmountFee
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf, "StudentAccountProfile", "wPayAmountEntered" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                   GetDecimalFromAttribute( md_dTempDecimal_1, mSAProf, "StudentAccountProfile", "dPayAmountFee" );
         dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
         szAmount = dTempDecimal_0 + dTempDecimal_1;
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, szAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szAmount, 0 );
         break ;

      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

*/
   //:     /* end zDERIVED_SET */
   //:END  /* case */
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

/*
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
            //:DropObjectInstance( mFAProf2 )
            DropObjectInstance( mFAProf2 );
         } 

         //:END

         //:StoreValueInRecord( mSAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

*/
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

//://   VIEW mSAChrgT BASED ON LOD mSAChrgT
//: //  VIEW lSATrnSh BASED ON LOD lSATrnSh
//:   DECIMAL dDiscountRate 
public int 
omSAProf_dPotentialCashDiscount( View     mSAProf,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   double  dDiscountRate = 0.0;
   //:DECIMAL dCashDiscount
   double  dCashDiscount = 0.0;
   //:DECIMAL dTotalEligibleCharges
   double  dTotalEligibleCharges = 0.0;

/*
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
         } 

          //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dCashDiscount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dCashDiscount, 0 );
         break ;
      //:   

      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 
*/
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
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
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

/*
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
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

*/
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

/*
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
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

*/
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
      //:RETURN 2
      if(8==8)return( 2 );
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
   omSAProf_fnLocalBuildQual_15( mSAProf, vTempViewVar_0, lTempInteger_1, lTempInteger_2, lTempInteger_3 );
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
      //:RETURN 2
      if(8==8)return( 2 );
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
   omSAProf_fnLocalBuildQual_16( mSAProf, vTempViewVar_0, lTempInteger_1, lTempInteger_2, lTempInteger_3 );
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

/*
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
         RESULT = SetCursorFirstEntity( mSAProf2, "PeriodTransApplied", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:dTotalCharges = dTotalCharges + mSAProf2.PeriodTransApplied.Amount 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf2, "PeriodTransApplied", "Amount" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dTotalCharges = dTotalCharges + dTempDecimal_0;
            RESULT = SetCursorNextEntity( mSAProf2, "PeriodTransApplied", "" );
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

      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 
*/
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
//                   MessageSend( mSAProf, "mSAProf009", "Delete Transactions", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
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
   //:STRING ( 100 ) szLangConv  
   String   szLangConv = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;

   //:// Process any Transaction deletes as separate commits.
   //:FOR EACH mSAProf.SATransAppliedDeletedEntry 
   RESULT = SetCursorFirstEntity( mSAProf, "SATransAppliedDeletedEntry", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:ACTIVATE dSATrans WHERE dSATrans.StudentAccountTransApplied.ID = mSAProf.SATransAppliedDeletedEntry.SATransAppliedID
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, mSAProf, "SATransAppliedDeletedEntry", "SATransAppliedID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      omSAProf_fnLocalBuildQual_13( mSAProf, vTempViewVar_0, lTempInteger_0 );
      RESULT = ActivateObjectInstance( dSATrans, "dSATrans", mSAProf, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW dSATrans "dSATrans"
      SetNameForView( dSATrans, "dSATrans", null, zLEVEL_TASK );

      //:// Give error message if a Disbursement entity exists.
      //:IF dSATrans.FinAidAwardDisbursement EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( dSATrans, "FinAidAwardDisbursement" );
      if ( lTempInteger_1 == 0 )
      { 
         //:GetTextConv(szLangConv,mSAProf,"A Disbursement is tied to Transaction","")
         //:szMsg = szLangConv+": " + dSATrans.StudentAccountTransApplied.Description + NEW_LINE
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, szLangConv, 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, ": ", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
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
                  ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_0, 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
         //:szMsg =szMsg+szLangConv
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szLangConv, 1, 0, 201 );
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

      RESULT = SetCursorNextEntity( mSAProf, "SATransAppliedDeletedEntry", "" );
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
      //:GetViewByName( mUser, "mUser", mSAStu, zLEVEL_TASK )
      GetViewByName( mUser, "mUser", mSAStu, zLEVEL_TASK );
   } 

   //:END

   //:CreateViewFromView( lTermLSTT, lTermLST )
   CreateViewFromView( lTermLSTT, lTermLST );
   //:// Position on the term we are looking at so that we can restrict the
   //:// meal plan based on that term.
   //:SET CURSOR FIRST lTermLSTT.CollegeTerm WHERE lTermLSTT.CollegeTerm.ID = nCollegeTermID  
   RESULT = SetCursorFirstEntityByInteger( lTermLSTT, "CollegeTerm", "ID", nCollegeTermID, "" );

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
   omSAProf_fnLocalBuildQual_14( mSAStu, vTempViewVar_0, lTempInteger_0, lTempInteger_1, nCollegeYearID, szTempString_0, szTempString_1 );
   RESULT = ActivateObjectInstance( mSAProf, "mSAProf", mSAStu, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:IF RESULT < 0 
   if ( RESULT < 0 )
   { 
      //:RETURN RESULT  
      if(8==8)return( RESULT );
   } 

   //:END
   //:DropObjectInstance ( lTermLSTT )
   DropObjectInstance( lTermLSTT );

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

//:   STRING ( 18 ) szString
public int 
omSAProf_dSATransactionCode( View     mSAProf,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szString = null;
   int      lTempInteger_0 = 0;

/*
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
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

*/
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
 
         //:END
         //:nDays = nDays * 2
         nDays = nDays * 2;
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
/*
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


      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

*/
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
/*
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
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 
*/

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

/*
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
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

*/
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

/*
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
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

*/
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
//://   VIEW  mSAProfB BASED ON LOD mSAProfB
//:   VIEW  mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dBalance( View     mSAProf,
                   String InternalEntityStructure,
                   String InternalAttribStructure,
                   Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;

/*
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:dAmount = 0
         dAmount = 0;
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:IF mSAProf.StudentAccountProfile.ID != ""
         if ( CompareAttributeToString( mSAProf, "StudentAccountProfile", "ID", "" ) != 0 )
         { 
            //:// On 3/17/2011, DonC changed the code below to use mSAProf instead of mSAProfB for computing the
            //:// balance, because the balance wasn't being updated correctly.
            //:dAmount = mSAProf.StudentAccountProfile.BalanceForward 
            {MutableDouble md_dAmount = new MutableDouble( dAmount );
                         GetDecimalFromAttribute( md_dAmount, mSAProf, "StudentAccountProfile", "BalanceForward" );
            dAmount = md_dAmount.doubleValue( );}
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
         } 

         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                     InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

*/
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
/*

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
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;

      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 
*/
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

/*
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
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 
*/

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

/*
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Total the credit hours that will be billable. Note that a Class dropped after the
         //:// Dropped Date for the Term will be added here.
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
         //:dTotalCredits = 0
         dTotalCredits = 0;
         //:FOR EACH mSAProf2.TermEnrolled 
         RESULT = SetCursorFirstEntity( mSAProf2, "TermEnrolled", "" );
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

            RESULT = SetCursorNextEntity( mSAProf2, "TermEnrolled", "" );
            //:END
         } 

         //:END
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dTotalCredits, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dTotalCredits, 0 );
         break ;
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

*/
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

/*
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Total the Directed Study credit hours that will be billable. Note that a Class dropped after the
         //:// Dropped Date for the Term will be added here.
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
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
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dTotalCredits, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dTotalCredits, 0 );
         break ;
       //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 
*/

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

/*
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Total the number of Directed Study classes that will be billable. Note that a Class dropped after the
         //:// Dropped Date for the Term will be added here.
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
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
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, TotalClasses, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, TotalClasses, 0 );
         break ;
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

*/
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

/*
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Total the Audited credit hours that will be billable. Note that a Class dropped after the
         //:// Dropped Date for the Term will be added here.
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
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
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, TotalClasses, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, TotalClasses, 0 );
         break ;
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

*/
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

/*
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Total the Internship credit hours that will be billable. Note that a Class dropped after the
         //:// Dropped Date for the Term will be added here.
         //:CreateViewFromView ( mSAProf2, mSAProf )
         CreateViewFromView( mSAProf2, mSAProf );
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
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreValueInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, dTotalCredits, 0 )
         StoreValueInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, dTotalCredits, 0 );
         break ;
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 
*/

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
   int      lTempInteger_6 = 0;

   RESULT = GetViewByName( mUser, "mUser", mSAStu, zLEVEL_TASK );

   //:    ACTIVATE mSAProf MULTIPLE //temp
   //:       WHERE mSAProf.Student.ID = mSAStu.Student.ID 
   //:         AND mSAProf.ProfileAdministrativeDivision.ID = mUser.CurrentAdministrativeDivision.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSAStu, "Student", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mUser, "CurrentAdministrativeDivision", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   //:       RESTRICTING mSAProf.Enrolled TO 
   //:                   (mSAProf.EnrollmentCollegeYear.ID = nCollegeYearID OR mSAProf.RegisteredCollegeYear.ID = nCollegeYearID)
   //:       RESTRICTING mSAProf.BillingPeriod TO 
   //:                   ( mSAProf.PeriodCollegeYear.ID = nCollegeYearID OR mSAProf.BillingPeriod.Undated = "Y")
   omSAProf_fnLocalBuildQual_0( mSAStu, vTempViewVar_0, lTempInteger_0, lTempInteger_1, nCollegeYearID );
   RESULT = ActivateObjectInstance( mSAProf, "mSAProf", mSAStu, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:                  
   //:    IF RESULT < 0 
   if ( RESULT < 0 )
   { 
      //:    TraceLineS("In Activate SA Prof For Year","NOPE")
      TraceLineS( "In Activate SA Prof For Year", "NOPE" );
      //:    RETURN RESULT  
      if(8==8)return( RESULT );
   } 

   //:    END

   //:IF mSAProf.StudentAccountTransApplied EXISTS 
   lTempInteger_2 = CheckExistenceOfEntity( mSAProf, "StudentAccountTransApplied" );
   if ( lTempInteger_2 == 0 )
   { 
      //:OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D ID D" )
      OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D ID D" );
   } 

   //:END

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
   //:   TraceLineS("In Activate SA Prof For Year","3")
   TraceLineS( "In Activate SA Prof For Year", "3" );
   //:FOR EACH mSAProf.PeriodCollegeTerm WITHIN mSAProf.StudentAccountProfile 
   RESULT = SetCursorFirstEntity( mSAProf, "PeriodCollegeTerm", "StudentAccountProfile" );
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

         RESULT = SetCursorNextEntity( mSAProf, "Enrolled", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSAProf, "PeriodCollegeTerm", "StudentAccountProfile" );
      //:END
   } 

   //:END
   //:   TraceLineS("In Activate SA Prof For Year","4")
   TraceLineS( "In Activate SA Prof For Year", "4" );

   //:   GET VIEW lTermLST NAMED "lSATermLST"
   RESULT = GetViewByName( lTermLST, "lSATermLST", mSAStu, zLEVEL_TASK );
   //:   IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:       UpdateBillingPeriods( mSAProf, lTermLST.CollegeYear.ID ) 
      {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
             GetIntegerFromAttribute( mi_lTempInteger_5, lTermLST, "CollegeYear", "ID" );
      lTempInteger_5 = mi_lTempInteger_5.intValue( );}
      omSAProf_UpdateBillingPeriods( mSAProf, lTempInteger_5 );
      //:      SET CURSOR FIRST mSAProf.PeriodCollegeTerm WITHIN mSAProf.StudentAccountProfile 
      //:              WHERE mSAProf.PeriodCollegeTerm.ID = lTermLST.CollegeTerm.ID 
      {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
             GetIntegerFromAttribute( mi_lTempInteger_6, lTermLST, "CollegeTerm", "ID" );
      lTempInteger_6 = mi_lTempInteger_6.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( mSAProf, "PeriodCollegeTerm", "ID", lTempInteger_6, "StudentAccountProfile" );
      //:   IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:   szMsg = "No Billing Period exists for currently selected Term." 
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "No Billing Period exists for currently selected Term.", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
         //:   MessageSend( mSAProf, "mSAProf001", "Generate Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mSAProf, "mSAProf001", "Generate Charges", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      } 

      //:   END
   } 

   //:   END
   //: 
   //:TraceLineS("In Activate SA Prof For Year","5")
   TraceLineS( "In Activate SA Prof For Year", "5" );

   //:LocateCurrentRoom( mSAProf )
   omSAProf_LocateCurrentRoom( mSAProf );
   //:LocateMealPlan( mSAProf )
   omSAProf_LocateMealPlan( mSAProf );
   //:TraceLineS("In Activate SA Prof For Year","6")
   TraceLineS( "In Activate SA Prof For Year", "6" );
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
      //:GetViewByName( mUser, "mUser", mSAStu, zLEVEL_TASK )
      GetViewByName( mUser, "mUser", mSAStu, zLEVEL_TASK );
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
   //:   RESTRICTING mSAProf.Enrolled      TO mSAProf.EnrollmentCollegeYear.ID = 0
   //:   RESTRICTING mSAProf.BillingPeriod TO mSAProf.PeriodCollegeTerm.ID = nCollegeTermID
   omSAProf_fnLocalBuildQual_1( mSAStu, vTempViewVar_0, nSAProfileID, szTempString_0, szTempString_1, nCollegeTermID );
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


   //:TraceLineS("Set up SA PRof for Year: ","1")
   TraceLineS( "Set up SA PRof for Year: ", "1" );
   //:IF mSAProf.StudentAccountTransApplied EXISTS 
   lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "StudentAccountTransApplied" );
   if ( lTempInteger_0 == 0 )
   { 
      //:OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D" )
      OrderEntityForView( mSAProf, "StudentAccountTransApplied", "TransactionDate D" );
   } 

   //:END
   //:TraceLineS("Set up SA PRof for Year: ","2")
   TraceLineS( "Set up SA PRof for Year: ", "2" );
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
      //:TraceLineS("Set up SA PRof for Year: ","3")
      TraceLineS( "Set up SA PRof for Year: ", "3" );
      //:SET CURSOR FIRST mSAProf.PeriodCollegeTerm  
      //:   WHERE mSAProf.PeriodCollegeTerm.ID = nCollegeTermID  
      RESULT = SetCursorFirstEntityByInteger( mSAProf, "PeriodCollegeTerm", "ID", nCollegeTermID, "" );
      //:ELSE 
   } 
   else
   { 
      //:TraceLineS("Set up SA PRof for Year: ","4")
      TraceLineS( "Set up SA PRof for Year: ", "4" );
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
   //:   TraceLineS("Set up SA PRof for Year: ","5")
   TraceLineS( "Set up SA PRof for Year: ", "5" );
   //:FOR EACH mSAProf.PeriodCollegeTerm
   RESULT = SetCursorFirstEntity( mSAProf, "PeriodCollegeTerm", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:TraceLineS("Set up SA PRof for Year: ","6")
      TraceLineS( "Set up SA PRof for Year: ", "6" );
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
         //:TraceLineS("Set up SA PRof for Year: ","7")
         TraceLineS( "Set up SA PRof for Year: ", "7" );
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
         //:   TraceLineS("Set up SA PRof for Year: ","8")
         TraceLineS( "Set up SA PRof for Year: ", "8" );
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

         //:END
         //:   TraceLineS("Set up SA PRof for Year: ","9")
         TraceLineS( "Set up SA PRof for Year: ", "9" );
         RESULT = SetCursorNextEntity( mSAProf, "Enrolled", "" );
      } 

      RESULT = SetCursorNextEntity( mSAProf, "PeriodCollegeTerm", "" );
      //:END
   } 

   //:END
   //:TraceLineS("Set up SA PRof for Year: ","10")
   TraceLineS( "Set up SA PRof for Year: ", "10" );
   //:LocateCurrentRoom( mSAProf )
   omSAProf_LocateCurrentRoom( mSAProf );
   //:   TraceLineS("Set up SA PRof for Year: ","11")
   TraceLineS( "Set up SA PRof for Year: ", "11" );
   //:LocateMealPlan( mSAProf )
   omSAProf_LocateMealPlan( mSAProf );
   //:   TraceLineS("Set up SA PRof for Year: ","12")
   TraceLineS( "Set up SA PRof for Year: ", "12" );
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

/*
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
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 
*/

   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:CreateReturningSAOrig( VIEW mSAProfNew BASED ON LOD mSAProf,
//:                   VIEW mSAStu     BASED ON LOD mSAStu,
//:                   INTEGER OldTermID,
//:                   INTEGER NewTermID )
public int 
omSAProf_CreateReturningSAOrig( View     mSAProfNew,
                                View     mSAStu,
                                int      OldTermID,
                                int      NewTermID )
{

   return( 0 );
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
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateReverseCharge( VIEW mSAProf BASED ON LOD mSAProf )
//:  VIEW wXferO     REGISTERED AS wXferO
public int 
omSAProf_GenerateReverseCharge( View     mSAProf )
{
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

/*
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
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreStringInRecord( mSAProf, InternalEntityStructure, 
         //:                     InternalAttribStructure, szPhone )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szPhone );
         break ;

      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 
*/
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

   //:TraceLineS("Locate current Room: ","1")
   TraceLineS( "Locate current Room: ", "1" );
   //:IF mSAProf.TermOfResidence DOES not EXIST 
   lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "TermOfResidence" );
   if ( lTempInteger_0 != 0 )
   { 
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:TraceLineS("Locate current Room: ","2")
   TraceLineS( "Locate current Room: ", "2" );
   //:IF mSAProf.PeriodCollegeTerm DOES not EXIST 
   lTempInteger_1 = CheckExistenceOfEntity( mSAProf, "PeriodCollegeTerm" );
   if ( lTempInteger_1 != 0 )
   { 
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:TraceLineS("Locate current Room: ","3")
   TraceLineS( "Locate current Room: ", "3" );
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
   //:TraceLineS("Locate current Room: ","4")
   TraceLineS( "Locate current Room: ", "4" );
   //:FOR EACH mSAProf2.TermOfResidence 
   RESULT = SetCursorFirstEntity( mSAProf2, "TermOfResidence", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:TraceLineS("Locate current Room: ","5")
      TraceLineS( "Locate current Room: ", "5" );
      //:IF mSAProf2.TermOfResidence.WithdrawalDate = "" 
      if ( CompareAttributeToString( mSAProf2, "TermOfResidence", "WithdrawalDate", "" ) == 0 )
      { 
         //:IF mSAProf2.TermOfResidence.BeginDate >= mSAProf2.PeriodCollegeTerm.ResidencyStartDate AND
         //:   mSAProf2.TermOfResidence.EndDate   <= mSAProf2.PeriodCollegeTerm.ResidencyEndDate 
         if ( CompareAttributeToAttribute( mSAProf2, "TermOfResidence", "BeginDate", mSAProf2, "PeriodCollegeTerm", "ResidencyStartDate" ) >= 0 &&
              CompareAttributeToAttribute( mSAProf2, "TermOfResidence", "EndDate", mSAProf2, "PeriodCollegeTerm", "ResidencyEndDate" ) <= 0 )
         { 
            //:TraceLineS("Locate current Room: ","6")
            TraceLineS( "Locate current Room: ", "6" );
            //:INCLUDE mSAProf.BillingTermOfResidence  FROM mSAProf2.TermOfResidence 
            RESULT = IncludeSubobjectFromSubobject( mSAProf, "BillingTermOfResidence", mSAProf2, "TermOfResidence", zPOS_AFTER );
            //:ELSE 
         } 
         else
         { 
            //:// if the res period begin and end dates go within the res date then 
            //:// they must already be living there. 
            //:IF mSAProf2.PeriodCollegeTerm.ResidencyStartDate >= mSAProf2.TermOfResidence.BeginDate AND
            //:   mSAProf2.PeriodCollegeTerm.ResidencyStartDate <= mSAProf2.TermOfResidence.EndDate
            if ( CompareAttributeToAttribute( mSAProf2, "PeriodCollegeTerm", "ResidencyStartDate", mSAProf2, "TermOfResidence", "BeginDate" ) >= 0 &&
                 CompareAttributeToAttribute( mSAProf2, "PeriodCollegeTerm", "ResidencyStartDate", mSAProf2, "TermOfResidence", "EndDate" ) <= 0 )
            { 
               //:TraceLineS("Locate current Room: ","7")
               TraceLineS( "Locate current Room: ", "7" );
               //:INCLUDE mSAProf.BillingTermOfResidence  FROM mSAProf2.TermOfResidence 
               RESULT = IncludeSubobjectFromSubobject( mSAProf, "BillingTermOfResidence", mSAProf2, "TermOfResidence", zPOS_AFTER );
            } 


            //:END
         } 

         //:END 
      } 

      RESULT = SetCursorNextEntity( mSAProf2, "TermOfResidence", "" );
      //:END 
   } 

   //:END
   //:TraceLineS("Locate current Room: ","8")
   TraceLineS( "Locate current Room: ", "8" );
   //:// The following code was deleted by DonC and Aadit on 1/4/2012 because we do NOT want a BillingTermOfResidence
   //:// created if the Student doesn't have a valid TermOfResidence for the Billing Period. If that happens, we will
   //:// use a default charge.
   //:/*IF mSAProf.BillingTermOfResidence  DOES not EXIST
   //:   OrderEntityForView( mSAProf2, "TermOfResidence", "BeginDate D" )
   //:   SET CURSOR FIRST mSAProf2.TermOfResidence 
   //:   IF RESULT >= zCURSOR_SET 
   //:      INCLUDE mSAProf.BillingTermOfResidence  FROM mSAProf2.TermOfResidence 
   //:      DropObjectInstance( mSAProf2 )
   //:      RETURN 0
   //:   END
   //:END*/
   //:DropView( mSAProf2 )
   DropView( mSAProf2 );
   //:   TraceLineS("Locate current Room: ","9")
   TraceLineS( "Locate current Room: ", "9" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:LocateMealPlan( VIEW mSAProf BASED ON LOD mSAProf )
//:VIEW mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_LocateMealPlan( View     mSAProf )
{
   zVIEW    mSAProf2 = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;

   //:TraceLineS("Locate meal plan: ","1")
   TraceLineS( "Locate meal plan: ", "1" );
   //:IF mSAProf.TermOfMealPlan DOES not EXIST 
   lTempInteger_0 = CheckExistenceOfEntity( mSAProf, "TermOfMealPlan" );
   if ( lTempInteger_0 != 0 )
   { 
      //:TraceLineS("Locate meal plan: ","1a")
      TraceLineS( "Locate meal plan: ", "1a" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END 
   //:TraceLineS("Locate meal plan: ","1b")
   TraceLineS( "Locate meal plan: ", "1b" );
   //:IF mSAProf.PeriodCollegeTerm DOES not EXIST 
   lTempInteger_1 = CheckExistenceOfEntity( mSAProf, "PeriodCollegeTerm" );
   if ( lTempInteger_1 != 0 )
   { 
      //:TraceLineS("Locate meal plan: ","1c")
      TraceLineS( "Locate meal plan: ", "1c" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:TraceLineS("Locate meal plan: ","2")
   TraceLineS( "Locate meal plan: ", "2" );
   //:CreateViewFromView ( mSAProf2, mSAProf )
   CreateViewFromView( mSAProf2, mSAProf );
   //:FOR EACH mSAProf2.BillingTermOfMealPlan 
   RESULT = SetCursorFirstEntity( mSAProf2, "BillingTermOfMealPlan", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:EXCLUDE  mSAProf2.BillingTermOfMealPlan NONE  
      RESULT = ExcludeEntity( mSAProf2, "BillingTermOfMealPlan", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSAProf2, "BillingTermOfMealPlan", "" );
   } 

   //:END

   //:   TraceLineS("Locate meal plan: ","3")
   TraceLineS( "Locate meal plan: ", "3" );
   //:FOR EACH mSAProf2.TermOfMealPlan  
   RESULT = SetCursorFirstEntity( mSAProf2, "TermOfMealPlan", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSAProf2.TermOfMealPlan.WithdrawalDate = "" 
      if ( CompareAttributeToString( mSAProf2, "TermOfMealPlan", "WithdrawalDate", "" ) == 0 )
      { 
         //:IF mSAProf2.TermOfMealPlan.BeginDate >= mSAProf2.PeriodCollegeTerm.ResidencyStartDate 
         //:      AND ( mSAProf2.TermOfMealPlan.EndDate <= mSAProf2.PeriodCollegeTerm.ResidencyEndDate 
         //:       OR mSAProf2.TermOfMealPlan.EndDate = "" )
         if ( CompareAttributeToAttribute( mSAProf2, "TermOfMealPlan", "BeginDate", mSAProf2, "PeriodCollegeTerm", "ResidencyStartDate" ) >= 0 &&
              ( CompareAttributeToAttribute( mSAProf2, "TermOfMealPlan", "EndDate", mSAProf2, "PeriodCollegeTerm", "ResidencyEndDate" ) <= 0 || CompareAttributeToString( mSAProf2, "TermOfMealPlan", "EndDate", "" ) == 0 ) )
         { 
            //:INCLUDE mSAProf.BillingTermOfMealPlan FROM mSAProf2.TermOfMealPlan
            RESULT = IncludeSubobjectFromSubobject( mSAProf, "BillingTermOfMealPlan", mSAProf2, "TermOfMealPlan", zPOS_AFTER );

            //:ELSE 
         } 
         else
         { 
            //:IF mSAProf2.PeriodCollegeTerm.ResidencyStartDate >= mSAProf2.TermOfMealPlan.BeginDate 
            //:      AND mSAProf2.PeriodCollegeTerm.ResidencyStartDate <= mSAProf2.TermOfMealPlan.EndDate
            if ( CompareAttributeToAttribute( mSAProf2, "PeriodCollegeTerm", "ResidencyStartDate", mSAProf2, "TermOfMealPlan", "BeginDate" ) >= 0 &&
                 CompareAttributeToAttribute( mSAProf2, "PeriodCollegeTerm", "ResidencyStartDate", mSAProf2, "TermOfMealPlan", "EndDate" ) <= 0 )
            { 
               //:INCLUDE mSAProf.BillingTermOfMealPlan FROM mSAProf2.TermOfMealPlan
               RESULT = IncludeSubobjectFromSubobject( mSAProf, "BillingTermOfMealPlan", mSAProf2, "TermOfMealPlan", zPOS_AFTER );
            } 

            //:   
            //:END
         } 

         //:END 
      } 

      RESULT = SetCursorNextEntity( mSAProf2, "TermOfMealPlan", "" );
      //:END 
   } 

   //:END
   //:   TraceLineS("Locate meal plan: ","4")
   TraceLineS( "Locate meal plan: ", "4" );
   //:DropView( mSAProf2 )
   DropView( mSAProf2 );
   //:   TraceLineS("Locate meal plan: ","5")
   TraceLineS( "Locate meal plan: ", "5" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:CreateReturningSA( VIEW mSAProf  BASED ON LOD mSAProf)
//:    VIEW mSAMealP   REGISTERED AS mSAMealP 
public int 
omSAProf_CreateReturningSA( View     mSAProf )
{
   zVIEW    mSAMealP = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mYearCurrent REGISTERED AS mYearCurrent 
   zVIEW    mYearCurrent = new zVIEW( );
   //:VIEW mYearNew REGISTERED AS mYearNew 
   zVIEW    mYearNew = new zVIEW( );
   //:VIEW mSAProfOld  BASED ON LOD mSAProf
   zVIEW    mSAProfOld = new zVIEW( );
   //:VIEW wXferO      REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   //:INTEGER nUseOldTerm
   int      nUseOldTerm = 0;
   //:INTEGER nCollegeYearID
   int      nCollegeYearID = 0;
   //:INTEGER nRC 
   int      nRC = 0;
   //:INTEGER OldTermID
   int      OldTermID = 0;
   //:INTEGER NewTermID
   int      NewTermID = 0;
   //:DECIMAL dBalanceForward
   double  dBalanceForward = 0.0;
   //:STRING (  100  ) szLastMP
   String   szLastMP = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;

   RESULT = GetViewByName( mSAMealP, "mSAMealP", mSAProf, zLEVEL_TASK );
   RESULT = GetViewByName( mYearCurrent, "mYearCurrent", mSAProf, zLEVEL_TASK );
   RESULT = GetViewByName( mYearNew, "mYearNew", mSAProf, zLEVEL_TASK );
   RESULT = GetViewByName( wXferO, "wXferO", mSAProf, zLEVEL_TASK );
   //:TraceLineS("create returning sa","1")
   TraceLineS( "create returning sa", "1" );
   //:CreateViewFromView( mSAProfOld, mSAProf ) 
   CreateViewFromView( mSAProfOld, mSAProf );
   //:TraceLineS("create returning sa","2")  
   TraceLineS( "create returning sa", "2" );
   //:// Set up BillingPeriod derived entities for the new/old Profiles, including entities TermEnrolled, 
   //:// BillingTermOfResidence and BillingTermOfMealPlan.
   //:SetupSAProfForYear( mSAProfOld, mYearCurrent.CollegeYear.ID, mYearCurrent.CollegeTerm.ID )
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mYearCurrent, "CollegeYear", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mYearCurrent, "CollegeTerm", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   omSAProf_SetupSAProfForYear( mSAProfOld, lTempInteger_0, lTempInteger_1 );
   //:SetupSAProfForYear( mSAProf, mYearNew.CollegeYear.ID, mYearNew.CollegeTerm.ID )
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mYearNew, "CollegeYear", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, mYearNew, "CollegeTerm", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   omSAProf_SetupSAProfForYear( mSAProf, lTempInteger_2, lTempInteger_3 );
   //:TraceLineS("create returning sa","4")
   TraceLineS( "create returning sa", "4" );
   //:nCollegeYearID  = mYearNew.CollegeYear.ID 
   {MutableInt mi_nCollegeYearID = new MutableInt( nCollegeYearID );
       GetIntegerFromAttribute( mi_nCollegeYearID, mYearNew, "CollegeYear", "ID" );
   nCollegeYearID = mi_nCollegeYearID.intValue( );}
   //:OldTermID       = mYearCurrent.CollegeTerm.ID
   {MutableInt mi_OldTermID = new MutableInt( OldTermID );
       GetIntegerFromAttribute( mi_OldTermID, mYearCurrent, "CollegeTerm", "ID" );
   OldTermID = mi_OldTermID.intValue( );}
   //:dBalanceForward = mSAProfOld.StudentAccountProfile.dBalance 
   {MutableDouble md_dBalanceForward = new MutableDouble( dBalanceForward );
       GetDecimalFromAttribute( md_dBalanceForward, mSAProfOld, "StudentAccountProfile", "dBalance" );
   dBalanceForward = md_dBalanceForward.doubleValue( );}

   //:   // CREATE BILLING PERIODS AS NECESSARY FOR EACH TERM FOR THE NEW COLLEGE YEAR.
   //:   
   //:   // Make sure that Residency Start/End Dates exist in College Term.      ???
   //: TraceLineS("create returning sa","4")  
   TraceLineS( "create returning sa", "4" );
   //:   FOR EACH mYearNew.CollegeTerm 
   RESULT = SetCursorFirstEntity( mYearNew, "CollegeTerm", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:   SET CURSOR FIRST mSAProf.BillingPeriod WHERE mSAProf.PeriodCollegeTerm.ID = mYearNew.CollegeTerm.ID 
      RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSAProf, "PeriodCollegeTerm", "ID", mYearNew, "CollegeTerm", "ID" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( mSAProf, "BillingPeriod", "" );
         } 

      } 

      //:   IF  RESULT < zCURSOR_SET  
      if ( RESULT < zCURSOR_SET )
      { 
         //:   CREATE ENTITY mSAProf.BillingPeriod 
         RESULT = CreateEntity( mSAProf, "BillingPeriod", zPOS_AFTER );
         //:   mSAProf.BillingPeriod.PeriodDesignator = mYearNew.CollegeTerm.YearSemester 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "PeriodDesignator", mYearNew, "CollegeTerm", "YearSemester" );
         //:   mSAProf.BillingPeriod.BeginDate = mYearNew.CollegeTerm.ResidencyStartDate 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearNew, "CollegeTerm", "ResidencyStartDate" );
         //:   mSAProf.BillingPeriod.EndDate = mYearNew.CollegeTerm.ResidencyEndDate 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearNew, "CollegeTerm", "ResidencyEndDate" );
         //:   INCLUDE mSAProf.PeriodCollegeTerm FROM mYearNew.CollegeTerm
         RESULT = IncludeSubobjectFromSubobject( mSAProf, "PeriodCollegeTerm", mYearNew, "CollegeTerm", zPOS_AFTER );
      } 

      RESULT = SetCursorNextEntity( mYearNew, "CollegeTerm", "" );
      //:   END 
   } 

   //:   END
   //:   TraceLineS("create returning sa","5")
   TraceLineS( "create returning sa", "5" );
   //:   // CREATE A HISTORICAL PROFILE AS NECESSARY FOR THE OLD COLLEGE YEAR
   //:   
   //:   SET CURSOR FIRST mSAProf.StudentAccountHistoricalProfile WHERE mSAProf.HistoricalCollegeYear.ID = mYearCurrent.CollegeYear.ID 
   RESULT = SetCursorFirstEntity( mSAProf, "StudentAccountHistoricalProfile", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSAProf, "HistoricalCollegeYear", "ID", mYearCurrent, "CollegeYear", "ID" ) != 0 ) )
      { 
         RESULT = SetCursorNextEntity( mSAProf, "StudentAccountHistoricalProfile", "" );
      } 

   } 

   //:   IF RESULT < zCURSOR_SET 
   if ( RESULT < zCURSOR_SET )
   { 
      //:   CREATE ENTITY  mSAProf.StudentAccountHistoricalProfile 
      RESULT = CreateEntity( mSAProf, "StudentAccountHistoricalProfile", zPOS_AFTER );
      //:   INCLUDE mSAProf.HistoricalCollegeYear FROM   mYearCurrent.CollegeYear 
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "HistoricalCollegeYear", mYearCurrent, "CollegeYear", zPOS_AFTER );
      //:   SetMatchingAttributesByName( mSAProf, "StudentAccountHistoricalProfile", 
      //:                                mSAProf, "StudentAccountProfile", zSET_NOTNULL )
      SetMatchingAttributesByName( mSAProf, "StudentAccountHistoricalProfile", mSAProf, "StudentAccountProfile", zSET_NOTNULL );
      //:   mSAProf.StudentAccountHistoricalProfile.CollegeYear = mSAProf.HistoricalCollegeYear.Year 
      SetAttributeFromAttribute( mSAProf, "StudentAccountHistoricalProfile", "CollegeYear", mSAProf, "HistoricalCollegeYear", "Year" );
      //:   mSAProf.StudentAccountProfile.BalanceForward = 0 
      SetAttributeFromInteger( mSAProf, "StudentAccountProfile", "BalanceForward", 0 );
      //:   mSAProf.StudentAccountProfile.BalanceForward = mSAProf.StudentAccountProfile.dBalance - dBalanceForward 
      {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
             GetDecimalFromAttribute( md_dTempDecimal_0, mSAProf, "StudentAccountProfile", "dBalance" );
      dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
      dTempDecimal_1 = dTempDecimal_0 - dBalanceForward;
      SetAttributeFromDecimal( mSAProf, "StudentAccountProfile", "BalanceForward", dTempDecimal_1 );
      //:   mSAProf.StudentAccountProfile.BalanceForwardDate = wXferO.Root.dCurrentDate 
      SetAttributeFromAttribute( mSAProf, "StudentAccountProfile", "BalanceForwardDate", wXferO, "Root", "dCurrentDate" );
      //:   mSAProf.StudentAccountProfile.HealthInsuranceWaiver = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "HealthInsuranceWaiver", "" );
      //:   mSAProf.StudentAccountProfile.HealthInsuranceNote = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "HealthInsuranceNote", "" );
      //:   mSAProf.StudentAccountProfile.NoInterest = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "NoInterest", "" );
      //:   mSAProf.StudentAccountProfile.PaymentContractSent = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "PaymentContractSent", "" );
      //:   mSAProf.StudentAccountProfile.PaymentContractReceived = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "PaymentContractReceived", "" );
      //:   mSAProf.StudentAccountProfile.PaymentPlanBeginDate = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "PaymentPlanBeginDate", "" );
      //:   mSAProf.StudentAccountProfile.PaymentPlanEndDate = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "PaymentPlanEndDate", "" );
      //:   mSAProf.StudentAccountProfile.MealPlanWaiver = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "MealPlanWaiver", "" );
      //:   mSAProf.StudentAccountProfile.MealPlanWaiverDate = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "MealPlanWaiverDate", "" );
      //:   mSAProf.StudentAccountProfile.FinAidYearBegin = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "FinAidYearBegin", "" );
      //:   mSAProf.StudentAccountProfile.FinAidYearEnd = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "FinAidYearEnd", "" );
      //:   mSAProf.StudentAccountProfile.BillingNeedsUpdated = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "BillingNeedsUpdated", "" );
      //:   mSAProf.StudentAccountProfile.Cleared = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "Cleared", "" );
      //:   mSAProf.StudentAccountProfile.HasVehicle = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "HasVehicle", "" );
      //:   mSAProf.StudentAccountProfile.VehicleRegistrationNumber = ""
      SetAttributeFromString( mSAProf, "StudentAccountProfile", "VehicleRegistrationNumber", "" );
   } 

   //:      
   //:   END
   //:TraceLineS("create returning sa","6")
   TraceLineS( "create returning sa", "6" );
   //://COMMIT mSAProf
   //:SET CURSOR FIRST mSAProf.BillingPeriod WHERE mSAProf.PeriodCollegeTerm.ID = NewTermID
   RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mSAProf, "PeriodCollegeTerm", "ID", NewTermID ) != 0 ) )
      { 
         RESULT = SetCursorNextEntity( mSAProf, "BillingPeriod", "" );
      } 

   } 

   //:TraceLineS("create returning sa","7")
   TraceLineS( "create returning sa", "7" );
   return( 0 );
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

/*
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

      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

*/
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
   //:VIEW mSAAdmin     BASED ON LOD  mSAAdmin
   zVIEW    mSAAdmin = new zVIEW( );
   //:VIEW mUser        BASED ON LOD  mUser
   zVIEW    mUser = new zVIEW( );
   //:INTEGER nTransCodeID 
   int      nTransCodeID = 0;
   //:INTEGER nTransToChargeID
   int      nTransToChargeID = 0;
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
   //:STRING ( 8 )  szResidencyStartDate
   String   szResidencyStartDate = null;
   //:STRING ( 8 )  szCurrentDate
   String   szCurrentDate = null;
   //:STRING ( 8 )  szWithdrawalDate
   String   szWithdrawalDate = null;
   //:STRING ( 1 )  szReverseTranFlag
   String   szReverseTranFlag = null;
   //:STRING ( 300 ) szMsg
   String   szMsg = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_12 = 0;
   int      lTempInteger_13 = 0;
   int      lTempInteger_14 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   int      lTempInteger_15 = 0;
   int      lTempInteger_16 = 0;
   int      lTempInteger_17 = 0;
   int      lTempInteger_18 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_19 = 0;
   int      lTempInteger_20 = 0;
   int      lTempInteger_21 = 0;
   zVIEW    vTempViewVar_3 = new zVIEW( );
   int      lTempInteger_22 = 0;
   int      lTempInteger_23 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_24 = 0;
   int      lTempInteger_25 = 0;
   int      lTempInteger_26 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_27 = 0;
   int      lTempInteger_28 = 0;
   int      lTempInteger_29 = 0;
   int      lTempInteger_30 = 0;
   String   szTempString_4 = null;

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

/*
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
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreStringInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, szDate )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szDate );
         break ;
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 

*/
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
   
   /*
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
         //:DropObjectInstance( mSAProf2 ) 
         DropObjectInstance( mSAProf2 );
         //:StoreStringInRecord( mSAProf, InternalEntityStructure, 
         //:                    InternalAttribStructure, szDate )
         StoreStringInRecord( mSAProf, InternalEntityStructure, InternalAttribStructure, szDate );
         break ;
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 
*/
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
   omSAProf_fnLocalBuildQual_9( mSAProf, vTempViewVar_0, nCollegeYear );
   RESULT = ActivateObjectInstance( mYearLST, "mYear", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );


   //:   SET CURSOR FIRST mSAProf.BillingPeriod 
   //:      WHERE mSAProf.PeriodCollegeTerm.ID = mYearLST.CollegeTerm.ID
   RESULT = SetCursorFirstEntity( mSAProf, "BillingPeriod", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSAProf, "PeriodCollegeTerm", "ID", mYearLST, "CollegeTerm", "ID" ) != 0 ) )
      { 
         RESULT = SetCursorNextEntity( mSAProf, "BillingPeriod", "" );
      } 

   } 

   //:   IF RESULT >= zCURSOR_SET 
   if ( RESULT >= zCURSOR_SET )
   { 
      //:   DropObjectInstance( mYearLST )
      DropObjectInstance( mYearLST );
      //:   RETURN 2 
      if(8==8)return( 2 );
   } 

   //:   END
   //:   FOR EACH mYearLST.CollegeTerm 
   RESULT = SetCursorFirstEntity( mYearLST, "CollegeTerm", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:   CREATE ENTITY mSAProf.BillingPeriod 
      RESULT = CreateEntity( mSAProf, "BillingPeriod", zPOS_AFTER );
      //:   mSAProf.BillingPeriod.PeriodDesignator = mYearLST.CollegeTerm.YearSemester 
      SetAttributeFromAttribute( mSAProf, "BillingPeriod", "PeriodDesignator", mYearLST, "CollegeTerm", "YearSemester" );
      //:   IF mYearLST.CollegeTerm.ResidencyStartDate = "" OR mYearLST.CollegeTerm.ResidencyEndDate = ""
      if ( CompareAttributeToString( mYearLST, "CollegeTerm", "ResidencyStartDate", "" ) == 0 || CompareAttributeToString( mYearLST, "CollegeTerm", "ResidencyEndDate", "" ) == 0 )
      { 
         //:   mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeTerm.CourseStartDate 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeTerm", "CourseStartDate" );
         //:   mSAProf.BillingPeriod.EndDate   = mYearLST.CollegeTerm.CourseEndDate 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeTerm", "CourseEndDate" );
         //:ELSE
      } 
      else
      { 
         //:   mSAProf.BillingPeriod.BeginDate = mYearLST.CollegeTerm.ResidencyStartDate 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "BeginDate", mYearLST, "CollegeTerm", "ResidencyStartDate" );
         //:   mSAProf.BillingPeriod.EndDate   = mYearLST.CollegeTerm.ResidencyEndDate 
         SetAttributeFromAttribute( mSAProf, "BillingPeriod", "EndDate", mYearLST, "CollegeTerm", "ResidencyEndDate" );
      } 

      //:   END
      //:   INCLUDE mSAProf.PeriodCollegeTerm FROM mYearLST.CollegeTerm 
      RESULT = IncludeSubobjectFromSubobject( mSAProf, "PeriodCollegeTerm", mYearLST, "CollegeTerm", zPOS_AFTER );
      RESULT = SetCursorNextEntity( mYearLST, "CollegeTerm", "" );
   } 

   //:   END

   //:DropObjectInstance( mYearLST )
   DropObjectInstance( mYearLST );
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
   omSAProf_fnLocalBuildQual_10( mSAProf, vTempViewVar_0, nCollegeYear );
   RESULT = ActivateObjectInstance( mYearLST, "mYear", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:// If the College Terms for the year is not complete, give error message and return.
   //:FOR EACH mYearLST.CollegeTerm 
   RESULT = SetCursorFirstEntity( mYearLST, "CollegeTerm", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mYearLST.CollegeTerm.CourseStartDate    = "" OR 
      //:   mYearLST.CollegeTerm.CourseEndDate      = ""
      if ( CompareAttributeToString( mYearLST, "CollegeTerm", "CourseStartDate", "" ) == 0 || CompareAttributeToString( mYearLST, "CollegeTerm", "CourseEndDate", "" ) == 0 )
      { 

         //:MessageSend( mSAProf, "mSAProf006", "Generate Billing Period", 
         //:             "Billing Periods will not be generated because Course dates for College Term for the year have not been set.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mSAProf, "mSAProf006", "Generate Billing Period", "Billing Periods will not be generated because Course dates for College Term for the year have not been set.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      RESULT = SetCursorNextEntity( mYearLST, "CollegeTerm", "" );
      //:END
   } 

   //:/*   IF mYearLST.CollegeTerm.ResidencyStartDate = "" OR
   //:      mYearLST.CollegeTerm.ResidencyEndDate   = ""
   //:      
   //:      MessageSend( mSAProf, "mSAProf007", "Generate Billing Period", 
   //:                   "Billing Periods will not be generated because Residency dates for College Term for the year have not been set.",
   //:                   zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //:      RETURN 2
   //:   END */
   //:END

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

      RESULT = SetCursorNextEntity( mYearLST, "CollegeTerm", "" );
      //:END
   } 

   //:END
   //:OrderEntityForView( mSAProf, "BillingPeriod", "BeginDate A" )
   OrderEntityForView( mSAProf, "BillingPeriod", "BeginDate A" );

   //:// Include TermEnrolled entries.
   //:// Check first if they're already there, as that could happen if we're adding additional Periods when a Period already exists.
   //:FOR EACH mSAProf.PeriodCollegeTerm WITHIN mSAProf.StudentAccountProfile
   RESULT = SetCursorFirstEntity( mSAProf, "PeriodCollegeTerm", "StudentAccountProfile" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CollegeTermID = mSAProf.PeriodCollegeTerm.ID
      {MutableInt mi_CollegeTermID = new MutableInt( CollegeTermID );
             GetIntegerFromAttribute( mi_CollegeTermID, mSAProf, "PeriodCollegeTerm", "ID" );
      CollegeTermID = mi_CollegeTermID.intValue( );}
      //:FOR EACH mSAProf.Enrolled 
      RESULT = SetCursorFirstEntity( mSAProf, "Enrolled", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:SET CURSOR FIRST mSAProf.TermEnrolled WHERE mSAProf.TermEnrolled.ID = mSAProf.Enrolled.ID
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                   GetIntegerFromAttribute( mi_lTempInteger_1, mSAProf, "Enrolled", "ID" );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mSAProf, "TermEnrolled", "ID", lTempInteger_1, "" );
         //:IF RESULT < zCURSOR_SET 
         if ( RESULT < zCURSOR_SET )
         { 
            //:SET CURSOR LAST mSAProf.TermEnrolled
            RESULT = SetCursorLastEntity( mSAProf, "TermEnrolled", "" );
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

         RESULT = SetCursorNextEntity( mSAProf, "Enrolled", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSAProf, "PeriodCollegeTerm", "StudentAccountProfile" );
      //:END
   } 

   //:END

   //:DropObjectInstance( mYearLST )
   DropObjectInstance( mYearLST );
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
   omSAProf_fnLocalBuildQual_11( mSAProf, vTempViewVar_0, lTempInteger_0, CollegeTermID );
   RESULT = ActivateObjectInstance( mSAStuEn, "mSAStuEn", mSAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSAStuEn "mSAStuEn"
   SetNameForView( mSAStuEn, "mSAStuEn", null, zLEVEL_TASK );

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
   //:STRING ( 100 ) szLangConv
   String   szLangConv = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_2 = 0;
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
   //:STRING ( 100 ) szLangConv
   String   szLangConv = null;
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

   //:  
   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :
         //:TraceLineS("mSAProf object activate commit: ","1")
         TraceLineS( "mSAProf object activate commit: ", "1" );
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
         //:TraceLineS("mSAProf object activate commit: ","2")      
         TraceLineS( "mSAProf object activate commit: ", "2" );
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
         //:TraceLineS("mSAProf object activate commit: ","3")
         TraceLineS( "mSAProf object activate commit: ", "3" );
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
         //:TraceLineS("mSAProf object activate commit: ","4")
         TraceLineS( "mSAProf object activate commit: ", "4" );
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
         //:TraceLineS("mSAProf object activate commit: ","5")
         TraceLineS( "mSAProf object activate commit: ", "5" );
         //:// Order Enrolled entries by Class Date.
         //:IF mSAProf.Enrolled EXISTS
         lTempInteger_4 = CheckExistenceOfEntity( mSAProf, "Enrolled" );
         if ( lTempInteger_4 == 0 )
         { 

            //:GET VIEW lTermLST NAMED "lSATermLST"
            RESULT = GetViewByName( lTermLST, "lSATermLST", mSAProf, zLEVEL_TASK );
            //:IF RESULT < 0
            if ( RESULT < 0 )
            { 
               //:GET VIEW lTermLST NAMED "lTermLST" 
               RESULT = GetViewByName( lTermLST, "lTermLST", mSAProf, zLEVEL_TASK );
            } 

            //:END

            //:OrderEntityForView( mSAProf, "Enrolled", "Class.ClassStartDate D" )
            OrderEntityForView( mSAProf, "Enrolled", "Class.ClassStartDate D" );

            //:SET CURSOR FIRST mSAProf.Enrolled
            RESULT = SetCursorFirstEntity( mSAProf, "Enrolled", "" );
         } 

         //:END
         //:TraceLineS("mSAProf object activate commit: ","6")
         TraceLineS( "mSAProf object activate commit: ", "6" );
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
         //:TraceLineS("mSAProf object activate commit: ","7")
         TraceLineS( "mSAProf object activate commit: ", "7" );
         //:// Order StudentAccountHistoricalProfile by Year.
         //:OrderEntityForView( mSAProf, "StudentAccountHistoricalProfile", "HistoricalCollegeYear.Year D" )
         OrderEntityForView( mSAProf, "StudentAccountHistoricalProfile", "HistoricalCollegeYear.Year D" );

         //:// Initialize flags used for determining if accounting data has been updated when charges are generated.
         //:mSAProf.StudentAccountProfile.wOrigHealthInsuranceWaiver = mSAProf.StudentAccountProfile.HealthInsuranceWaiver 
         SetAttributeFromAttribute( mSAProf, "StudentAccountProfile", "wOrigHealthInsuranceWaiver", mSAProf, "StudentAccountProfile", "HealthInsuranceWaiver" );
         //:mSAProf.StudentAccountProfile.wOrigMealPlanWaiver        = mSAProf.StudentAccountProfile.MealPlanWaiver 
         SetAttributeFromAttribute( mSAProf, "StudentAccountProfile", "wOrigMealPlanWaiver", mSAProf, "StudentAccountProfile", "MealPlanWaiver" );

         //:GET VIEW lTermLST NAMED "lSATermLST"
         RESULT = GetViewByName( lTermLST, "lSATermLST", mSAProf, zLEVEL_TASK );
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 
            //:SET CURSOR FIRST mSAProf.BillingPeriod
            //:           WHERE mSAProf.PeriodCollegeTerm.YearSemester = lTermLST.CollegeTerm.YearSemester 
            //:             AND ( mSAProf.BillingPeriod.BeginDate = "" OR mSAProf.BillingPeriod.EndDate = "" ) 
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
               //:GetTextConv(szLangConv,mSAProf,"Student","")
               //:szMsg = szLangConv + mSAProf.Person.dFullName + NEW_LINE
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
                              ZeidonStringCopy( sb_szMsg, 1, 0, szLangConv, 1, 0, 201 );
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
                              ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 201 );
               szMsg = sb_szMsg.toString( );}
               //:GetTextConv(szLangConv,mSAProf,"Billing Period for Term","")
               //:szMsg=szMsg+szLangConv+" " + mSAProf.PeriodCollegeTerm.YearSemester + " "
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, szLangConv, 1, 0, 201 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, " ", 1, 0, 201 );
               szMsg = sb_szMsg.toString( );}
               {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
               StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_7, 'S', 51, mSAProf, "PeriodCollegeTerm", "YearSemester", "", 0 );
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
                              ZeidonStringConcat( sb_szMsg, 1, 0, " ", 1, 0, 201 );
               szMsg = sb_szMsg.toString( );}
               //:GetTextConv(szLangConv,mSAProf,"without valid Start/End Dates.","")
                //:szMsg=szMsg+szLangConv + NEW_LINE
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, szLangConv, 1, 0, 201 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 201 );
               szMsg = sb_szMsg.toString( );}
               //:MessageSend( mSAProf, "", "Activate mSAProf", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
               MessageSend( mSAProf, "", "Activate mSAProf", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
            } 

            //:END
         } 

         //:END
         //:TraceLineS("mSAProf object activate commit: ","8")
         TraceLineS( "mSAProf object activate commit: ", "8" );
         break ;
      //:  /* end zOCE_ACTIVATE */
      //:OF   zOCE_ACTIVATE_EMPTY:
      case zOCE_ACTIVATE_EMPTY :
         break ;

      //:  /* end zOCE_ACTIVATE_EMPTY */
      //:OF   zOCE_COMMIT:
      case zOCE_COMMIT :
         //:TraceLineS("mSAProf object commit: ","1")
         TraceLineS( "mSAProf object commit: ", "1" );
         //:// Make sure AccountBalance is set from the derived value.
         //:mSAProf.StudentAccountProfile.AccountBalance = mSAProf.StudentAccountProfile.dBalance 
         SetAttributeFromAttribute( mSAProf, "StudentAccountProfile", "AccountBalance", mSAProf, "StudentAccountProfile", "dBalance" );
         //:TraceLineS("mSAProf object activate commit: ","2")
         TraceLineS( "mSAProf object activate commit: ", "2" );
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
//:                   VIEW mFASrcAwarded    /* BASED ON LOD mFASrc*/,
//:                   DECIMAL dSwappableAmount )

//:   VIEW mSAProfT BASED ON LOD mSAProf
public int 
omSAProf_GenerateSwapTrans( View     mSAProf,
                            View     mFAProf,
                            View     mFAProfOrigAward,
                            View     mFASrcAwarded,
                            double  dSwappableAmount )
{
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateTuitRemTrans( VIEW mSAProf  BASED ON LOD mSAProf,
//:                      VIEW mSAClass /*BASED ON LOD mSAClass*/,
//:                      VIEW mEmplTDs /*BASED ON LOD mEmplTDs*/,
//:                      DECIMAL dTuitionAmount,
//:                      STRING ( 1 ) szGenerateEstimatedCostFlag )
public int 
omSAProf_GenerateTuitRemTrans( View     mSAProf,
                               View     mSAClass,
                               View     mEmplTDs,
                               double  dTuitionAmount,
                               String   szGenerateEstimatedCostFlag )
{

   return( 0 );
//  
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dSATransFA_Period( VIEW mSAProf BASED ON LOD mSAProf,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   VIEW mSAProf2 BASED ON LOD mSAProf
public int 
omSAProf_dSATransFA_Period( View     mSAProf,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   zVIEW    mSAProf2 = new zVIEW( );
   //:STRING ( 18 ) szPeriod
   String   szPeriod = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      RESULT = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;


   //:      /* end zDERIVED_SET */
   //:END   /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dIsDevelopingCountry( VIEW mSAProf BASED ON LOD mSAProf,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )
//:STRING ( 100 ) szString
public int 
omSAProf_dIsDevelopingCountry( View     mSAProf,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   String   szString = null;
   String   szTempString_0 = null;
   //:     /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dPayAmountFee( VIEW mSAProf BASED ON LOD mSAProf,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:VIEW  sHost REGISTERED AS sHost
public int 
omSAProf_dPayAmountFee( View     mSAProf,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   zVIEW    sHost = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL szFeeAmount
   double  szFeeAmount = 0.0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;

   //:     /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 



}
