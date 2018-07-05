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

import com.quinsoft.zencas.ZGLOBAL2_Operation;
import com.quinsoft.zencas.ZGLOBAL1_Operation;

/**
 * @author QuinSoft
 *
 */

public class mFAProf_Object extends VmlObjectOperations
{
   public mFAProf_Object( View view )
   {
      super( view );
   }


//:OBJECT CONSTRAINT OPERATION
//:ObjectConstraints( VIEW mFAProfOrig BASED ON LOD mFAProf,
//:                   SHORT Event,
//:                   SHORT State )
//:   
//:   VIEW mFAProf   BASED ON LOD mFAProf
public int 
omFAProf_ObjectConstraints( View     mFAProfOrig,
                            Integer   Event,
                            Integer   State )
{
   zVIEW    mFAProf = new zVIEW( );
   //:VIEW mFAProf2  BASED ON LOD mFAProf
   zVIEW    mFAProf2 = new zVIEW( );
   //:VIEW mAdmDiv   REGISTERED AS mAdmDiv
   zVIEW    mAdmDiv = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wXferO    REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   //:VIEW mFAISIRD  BASED ON LOD  mFAISIRD
   zVIEW    mFAISIRD = new zVIEW( );
   //:VIEW lFAAdmin  BASED ON LOD  mFAAdmin
   zVIEW    lFAAdmin = new zVIEW( );
   //:VIEW lFAAdminT BASED ON LOD  mFAAdmin
   zVIEW    lFAAdminT = new zVIEW( );
   //:STRING (  32  ) szDate
   String   szDate = null;
   //:SHORT   nRC 
   int      nRC = 0;
   //:STRING ( 500 )   szSourceString
   String   szSourceString = null;
   //:STRING ( 120 )   szCode
   String   szCode = null;
   //:STRING ( 32 )    szCommentsDomain
   String   szCommentsDomain = null;
   //:STRING ( 10000 ) szCommentsString
   String   szCommentsString = null;
   //:STRING ( 2 )     szSemester
   String   szSemester = null;
   //:STRING ( 50 )    szName
   String   szName = null;
   //:STRING ( 1 )     szTermError
   String   szTermError = null;
   //:STRING ( 10 )    szRevisionType
   String   szRevisionType = null;
   //:INTEGER nIndex
   int      nIndex = 0;
   //:INTEGER nLen 
   int      nLen = 0;
   //:INTEGER nCollegeYearID
   int      nCollegeYearID = 0;
   //:INTEGER nCollegeTermID
   int      nCollegeTermID = 0;
   //:INTEGER nProfileCollegeYearID
   int      nProfileCollegeYearID = 0;
   //:DECIMAL dPeriod1Credits
   double  dPeriod1Credits = 0.0;
   //:DECIMAL dPeriod2Credits
   double  dPeriod2Credits = 0.0;
   //:DECIMAL dMinFallCredits
   double  dMinFallCredits = 0.0;
   //:DECIMAL dMinSpringCredits
   double  dMinSpringCredits = 0.0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_0 = null;
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
   String   szTempString_1 = null;
   int      lTempInteger_21 = 0;
   int      lTempInteger_22 = 0;
   String   szTempString_2 = null;
   String   szTempString_3 = null;
   int      lTempInteger_23 = 0;
   int      lTempInteger_24 = 0;
   int      lTempInteger_25 = 0;
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   int      lTempInteger_26 = 0;
   int      lTempInteger_27 = 0;
   int      lTempInteger_28 = 0;
   int      lTempInteger_29 = 0;
   String   szTempString_6 = null;
   int      lTempInteger_30 = 0;
   int      lTempInteger_31 = 0;
   int      lTempInteger_32 = 0;

   RESULT = GetViewByName( mAdmDiv, "mAdmDiv", mFAProfOrig, zLEVEL_TASK );
   RESULT = GetViewByName( wXferO, "wXferO", mFAProfOrig, zLEVEL_TASK );

   //:CASE Event
   switch( Event )
   { 
      //:OF zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:CreateViewFromView( mFAProf, mFAProfOrig )
         CreateViewFromView( mFAProf, mFAProfOrig );
         //:NAME VIEW mFAProf "FAProfConstraints"
         SetNameForView( mFAProf, "FAProfConstraints", null, zLEVEL_TASK );

         //:// Sort School entry FAISIRSchool in descending order for display of HousingCode.
         //:IF mFAProf.FAISIRSchool EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mFAProf, "FAISIRSchool" );
         if ( lTempInteger_0 == 0 )
         { 
            //:OrderEntityForView( mFAProf, "FAISIRSchool", "SchoolNumber D" )
            OrderEntityForView( mFAProf, "FAISIRSchool", "SchoolNumber D" );
            //:SET CURSOR FIRST mFAProf.FAISIRSchool 
            RESULT = mFAProf.cursor( "FAISIRSchool" ).setFirst().toInt();
         } 

         //:END

         //:// Relink Disbursement entities.
         //:FOR EACH mFAProf.PerPeriodFinAidAwardDisbursement WITHIN mFAProf.FinAidProfile 
         RESULT = mFAProf.cursor( "PerPeriodFinAidAwardDisbursement" ).setFirst( "FinAidProfile" ).toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST mFAProf.FinAidAwardDisbursement WITHIN mFAProf.FinAidProfile
            //:           WHERE mFAProf.FinAidAwardDisbursement.ID = mFAProf.PerPeriodFinAidAwardDisbursement.ID   
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                         GetIntegerFromAttribute( mi_lTempInteger_1, mFAProf, "PerPeriodFinAidAwardDisbursement", "ID" );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );}
            RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setFirst( "ID", lTempInteger_1, "FinAidProfile" ).toInt();
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:IssueError( mFAProf,0,0, "Programming Error on relink of FinAidAward" )
               IssueError( mFAProf, 0, 0, "Programming Error on relink of FinAidAward" );
               //:ELSE
            } 
            else
            { 
               //:RelinkInstanceToInstance( mFAProf, "PerPeriodFinAidAwardDisbursement",
               //:                          mFAProf, "FinAidAwardDisbursement" )
               RelinkInstanceToInstance( mFAProf, "PerPeriodFinAidAwardDisbursement", mFAProf, "FinAidAwardDisbursement" );
               //:RelinkInstanceToInstance( mFAProf, "DisbFinAidAwardAssigned",
               //:                          mFAProf, "FinAidAward"  )
               RelinkInstanceToInstance( mFAProf, "DisbFinAidAwardAssigned", mFAProf, "FinAidAward" );
            } 

            RESULT = mFAProf.cursor( "PerPeriodFinAidAwardDisbursement" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:FOR EACH mFAProf.FinAidAwardPeriod WITHIN mFAProf.FinAidProfile 
         RESULT = mFAProf.cursor( "FinAidAwardPeriod" ).setFirst( "FinAidProfile" ).toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod 
            //:           WHERE mFAProf.PerProfileFinAidAwardPeriod.ID = mFAProf.FinAidAwardPeriod.ID  
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, mFAProf, "FinAidAwardPeriod", "ID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst( "ID", lTempInteger_2 ).toInt();
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:RelinkInstanceToInstance( mFAProf, "FinAidAwardPeriod",
               //:                          mFAProf, "PerProfileFinAidAwardPeriod" )
               RelinkInstanceToInstance( mFAProf, "FinAidAwardPeriod", mFAProf, "PerProfileFinAidAwardPeriod" );
            } 

            RESULT = mFAProf.cursor( "FinAidAwardPeriod" ).setNextContinue().toInt();;
            //:END
         } 

         //:END

         //:// Order PerProfileFinAidAwardPeriod by Term (same as BeginDate).
         //:OrderEntityForView( mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate A" )
         OrderEntityForView( mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate A" );

         //:// Eliminate any assigned COA Item entries if the COA item has been deleted.
         //:FOR EACH mFAProf.FinAidCOAItemAssigned 
         RESULT = mFAProf.cursor( "FinAidCOAItemAssigned" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mFAProf.FinAidCOAItem DOES NOT EXIST
            lTempInteger_3 = CheckExistenceOfEntity( mFAProf, "FinAidCOAItem" );
            if ( lTempInteger_3 != 0 )
            { 
               //:DELETE ENTITY mFAProf.FinAidCOAItemAssigned NONE
               RESULT = DeleteEntity( mFAProf, "FinAidCOAItemAssigned", zREPOS_NONE );
            } 

            RESULT = mFAProf.cursor( "FinAidCOAItemAssigned" ).setNextContinue().toInt();;
            //:END
         } 

         //:END 

         //:// The following sections of code are not executed if we are on the web for Students, which is identified by the existence of
         //:// the object, lFAAdmin.
         //:GET VIEW lFAAdmin NAMED "lFAAdmin" 
         RESULT = GetViewByName( lFAAdmin, "lFAAdmin", mFAProfOrig, zLEVEL_TASK );
         //:IF RESULT >= 0 
         if ( RESULT >= 0 )
         { 

            //:// Set the Default Disbursement Date for each Period, if it is null.
            //:CreateViewFromView( lFAAdminT, lFAAdmin )
            CreateViewFromView( lFAAdminT, lFAAdmin );
            //:FOR EACH mFAProf.PerProfileFinAidAwardPeriod
            RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF mFAProf.PerProfileFinAidAwardPeriod.DefaultDisbursementDate = ""
               if ( CompareAttributeToString( mFAProf, "PerProfileFinAidAwardPeriod", "DefaultDisbursementDate", "" ) == 0 )
               { 
                  //:SET CURSOR FIRST lFAAdminT.FinAidAdmin WHERE lFAAdminT.CollegeYear.ID = mFAProf.CollegeYear.ID
                  RESULT = lFAAdminT.cursor( "FinAidAdmin" ).setFirst().toInt();
                  if ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lFAAdminT, "CollegeYear", "ID", mFAProf, "CollegeYear", "ID" ) != 0 ) )
                     { 
                        RESULT = lFAAdminT.cursor( "FinAidAdmin" ).setNextContinue().toInt();;
                     } 

                  } 

                  //:SET CURSOR FIRST lFAAdminT.FinAidAwardPeriod WHERE lFAAdminT.FinAidAwardPeriod.PeriodDesignator = mFAProf.PerProfileFinAidAwardPeriod.PeriodDesignator 
                  {StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                     GetStringFromAttribute( sb_szTempString_0, mFAProf, "PerProfileFinAidAwardPeriod", "PeriodDesignator" );
                  szTempString_0 = sb_szTempString_0.toString( );}
                  RESULT = lFAAdminT.cursor( "FinAidAwardPeriod" ).setFirst( "PeriodDesignator", szTempString_0 ).toInt();
                  //:mFAProf.PerProfileFinAidAwardPeriod.DefaultDisbursementDate = lFAAdminT.FinAidAwardPeriod.DefaultDisbursementDate 
                  SetAttributeFromAttribute( mFAProf, "PerProfileFinAidAwardPeriod", "DefaultDisbursementDate", lFAAdminT, "FinAidAwardPeriod", "DefaultDisbursementDate" );
               } 

               RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
               //:END
            } 

            //:END


            //:// CHECK FOR VALID TERMS UNDER PERIODS
            //:// Make sure that CollegeT PeProfileFinAidAwardPeriod either exists or doesn't exist depending
            //:// on whether or not the Admin Div uses Cohorts.
            //:// If there's an error, prompt the User for possible correction.
            //:szTermError = ""
             {StringBuilder sb_szTermError;
            if ( szTermError == null )
               sb_szTermError = new StringBuilder( 32 );
            else
               sb_szTermError = new StringBuilder( szTermError );
                        ZeidonStringCopy( sb_szTermError, 1, 0, "", 1, 0, 2 );
            szTermError = sb_szTermError.toString( );}
            //:IF mFAProf.AdministrativeDivision.UsesCohortsFlag = "Y"
            if ( CompareAttributeToString( mFAProf, "AdministrativeDivision", "UsesCohortsFlag", "Y" ) == 0 )
            { 
               //:FOR EACH mFAProf.PerProfileFinAidAwardPeriod 
               RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:IF mFAProf.CollegeTerm EXISTS
                  lTempInteger_4 = CheckExistenceOfEntity( mFAProf, "CollegeTerm" );
                  if ( lTempInteger_4 == 0 )
                  { 
                     //:szTermError = "Y"
                      {StringBuilder sb_szTermError;
                     if ( szTermError == null )
                        sb_szTermError = new StringBuilder( 32 );
                     else
                        sb_szTermError = new StringBuilder( szTermError );
                                          ZeidonStringCopy( sb_szTermError, 1, 0, "Y", 1, 0, 2 );
                     szTermError = sb_szTermError.toString( );}
                  } 

                  RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
                  //:END
               } 

               //:END
               //:IF szTermError = "Y"
               if ( ZeidonStringCompare( szTermError, 1, 0, "Y", 1, 0, 2 ) == 0 )
               { 
                  //:nRC = MessagePrompt( mFAProf, "", "Loading FA Profile",
                  //:      "The current Financial Aid Profile is for a Cohort Admin Div, but the periods contain College Terms. Do you want them removed?",
                  //:      0, zBUTTONS_YESNO, zRESPONSE_NO, 0 )
                  nRC = MessagePrompt( mFAProf, "", "Loading FA Profile", "The current Financial Aid Profile is for a Cohort Admin Div, but the periods contain College Terms. Do you want them removed?", 0, zBUTTONS_YESNO, zRESPONSE_NO, 0 );
                  //:IF nRC = zRESPONSE_YES
                  if ( nRC == zRESPONSE_YES )
                  { 
                     //:FOR EACH mFAProf.PerProfileFinAidAwardPeriod 
                     RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
                     while ( RESULT > zCURSOR_UNCHANGED )
                     { 
                        //:IF mFAProf.CollegeTerm EXISTS 
                        lTempInteger_5 = CheckExistenceOfEntity( mFAProf, "CollegeTerm" );
                        if ( lTempInteger_5 == 0 )
                        { 
                           //:EXCLUDE mFAProf.CollegeTerm 
                           RESULT = ExcludeEntity( mFAProf, "CollegeTerm", zREPOS_AFTER );
                        } 

                        RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
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
               //:FOR EACH mFAProf.PerProfileFinAidAwardPeriod 
               RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:IF mFAProf.CollegeTerm DOES NOT EXIST
                  lTempInteger_6 = CheckExistenceOfEntity( mFAProf, "CollegeTerm" );
                  if ( lTempInteger_6 != 0 )
                  { 
                     //:szTermError = "Y"
                      {StringBuilder sb_szTermError;
                     if ( szTermError == null )
                        sb_szTermError = new StringBuilder( 32 );
                     else
                        sb_szTermError = new StringBuilder( szTermError );
                                          ZeidonStringCopy( sb_szTermError, 1, 0, "Y", 1, 0, 2 );
                     szTermError = sb_szTermError.toString( );}
                  } 

                  RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
                  //:END
               } 

               //:END
               //:IF szTermError = "Y"
               if ( ZeidonStringCompare( szTermError, 1, 0, "Y", 1, 0, 2 ) == 0 )
               { 
                  //:nRC = MessagePrompt( mFAProf, "", "Loading FA Profile",
                  //:      "The current Financial Aid Profile is for a traditional Admin Div, but the periods do not contain College Terms. Do you want them added?",
                  //:      0, zBUTTONS_YESNO, zRESPONSE_NO, 0 )
                  nRC = MessagePrompt( mFAProf, "", "Loading FA Profile", "The current Financial Aid Profile is for a traditional Admin Div, but the periods do not contain College Terms. Do you want them added?", 0, zBUTTONS_YESNO, zRESPONSE_NO, 0 );
                  //:IF nRC = zRESPONSE_YES
                  if ( nRC == zRESPONSE_YES )
                  { 
                     //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod
                     RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
                     //:SET CURSOR FIRST lFAAdminT.FinAidAdmin WHERE lFAAdminT.CollegeYear.ID = mFAProf.CollegeYear.ID
                     RESULT = lFAAdminT.cursor( "FinAidAdmin" ).setFirst().toInt();
                     if ( RESULT > zCURSOR_UNCHANGED )
                     { 
                        while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lFAAdminT, "CollegeYear", "ID", mFAProf, "CollegeYear", "ID" ) != 0 ) )
                        { 
                           RESULT = lFAAdminT.cursor( "FinAidAdmin" ).setNextContinue().toInt();;
                        } 

                     } 


                     //:// Check Period 1.
                     //:IF mFAProf.CollegeTerm DOES NOT EXIST
                     lTempInteger_7 = CheckExistenceOfEntity( mFAProf, "CollegeTerm" );
                     if ( lTempInteger_7 != 0 )
                     { 
                        //:SET CURSOR FIRST lFAAdminT.FinAidAwardPeriod WHERE lFAAdminT.DisbCollegeTerm.Semester = "01"
                        RESULT = lFAAdminT.cursor( "FinAidAwardPeriod" ).setFirst().toInt();
                        if ( RESULT > zCURSOR_UNCHANGED )
                        { 
                           while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( lFAAdminT, "DisbCollegeTerm", "Semester", "01" ) != 0 ) )
                           { 
                              RESULT = lFAAdminT.cursor( "FinAidAwardPeriod" ).setNextContinue().toInt();;
                           } 

                        } 

                        //:INCLUDE mFAProf.CollegeTerm FROM lFAAdminT.DisbCollegeTerm
                        RESULT = IncludeSubobjectFromSubobject( mFAProf, "CollegeTerm", lFAAdminT, "DisbCollegeTerm", zPOS_AFTER );
                        //:mFAProf.PerProfileFinAidAwardPeriod.BeginDate = lFAAdminT.FinAidAwardPeriod.BeginDate 
                        SetAttributeFromAttribute( mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate", lFAAdminT, "FinAidAwardPeriod", "BeginDate" );
                        //:mFAProf.PerProfileFinAidAwardPeriod.EndDate   = lFAAdminT.FinAidAwardPeriod.EndDate 
                        SetAttributeFromAttribute( mFAProf, "PerProfileFinAidAwardPeriod", "EndDate", lFAAdminT, "FinAidAwardPeriod", "EndDate" );
                     } 

                     //:END

                     //:// Check Period 2.
                     //:SET CURSOR NEXT mFAProf.PerProfileFinAidAwardPeriod
                     RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
                     //:IF mFAProf.CollegeTerm DOES NOT EXIST
                     lTempInteger_8 = CheckExistenceOfEntity( mFAProf, "CollegeTerm" );
                     if ( lTempInteger_8 != 0 )
                     { 
                        //:SET CURSOR FIRST lFAAdminT.FinAidAwardPeriod  WHERE lFAAdminT.DisbCollegeTerm.Semester = "03"
                        RESULT = lFAAdminT.cursor( "FinAidAwardPeriod" ).setFirst().toInt();
                        if ( RESULT > zCURSOR_UNCHANGED )
                        { 
                           while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( lFAAdminT, "DisbCollegeTerm", "Semester", "03" ) != 0 ) )
                           { 
                              RESULT = lFAAdminT.cursor( "FinAidAwardPeriod" ).setNextContinue().toInt();;
                           } 

                        } 

                        //:INCLUDE mFAProf.CollegeTerm FROM lFAAdminT.DisbCollegeTerm
                        RESULT = IncludeSubobjectFromSubobject( mFAProf, "CollegeTerm", lFAAdminT, "DisbCollegeTerm", zPOS_AFTER );
                        //:mFAProf.PerProfileFinAidAwardPeriod.BeginDate = lFAAdminT.FinAidAwardPeriod.BeginDate 
                        SetAttributeFromAttribute( mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate", lFAAdminT, "FinAidAwardPeriod", "BeginDate" );
                        //:mFAProf.PerProfileFinAidAwardPeriod.EndDate   = lFAAdminT.FinAidAwardPeriod.EndDate
                        SetAttributeFromAttribute( mFAProf, "PerProfileFinAidAwardPeriod", "EndDate", lFAAdminT, "FinAidAwardPeriod", "EndDate" );
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
            //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod 
            RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
            //:DropView( lFAAdminT )
            DropView( lFAAdminT );


            //:// Build the list of Enrolled entries under the Period, which depends on whether the Admin div is traditional or adult studies model.
            //:// If this is the Adult Studies model (uses Cohorts), then the displayed enrollments are those designated at the interface.
            //:// If this is the traditional model, then the following rules apply:
            //:// ENC Rule Begin
            //:// 1. All Fall classes are added to first period and all Spring and Summer classes are added to second period.
            //:// 2. The January Term classes are added to Fall unless:
            //://    a. The Student took no classes in Fall.
            //://    b. The Student is full-time in Fall and part-time in Spring
            //:// ENC Rule End
            //:// Regular OpenCUAS rule is that the Class appears under its College Term.
            //:IF mFAProf.PerProfileFinAidAwardPeriod EXISTS
            lTempInteger_9 = CheckExistenceOfEntity( mFAProf, "PerProfileFinAidAwardPeriod" );
            if ( lTempInteger_9 == 0 )
            { 
               //:IF mFAProf.AdministrativeDivision.UsesCohortsFlag = "Y"
               if ( CompareAttributeToString( mFAProf, "AdministrativeDivision", "UsesCohortsFlag", "Y" ) == 0 )
               { 
                  //:// ADULT STUDIES MODEL
                  //:FOR EACH mFAProf.PeriodEnrolled WITHIN mFAProf.FinAidProfile 
                  RESULT = mFAProf.cursor( "PeriodEnrolled" ).setFirst( "FinAidProfile" ).toInt();
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //:INCLUDE mFAProf.DisplayEnrolled FROM mFAProf.PeriodEnrolled 
                     RESULT = IncludeSubobjectFromSubobject( mFAProf, "DisplayEnrolled", mFAProf, "PeriodEnrolled", zPOS_AFTER );
                     RESULT = mFAProf.cursor( "PeriodEnrolled" ).setNextContinue().toInt();;
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:// TRADITIONAL TERM MODEL
                  //:// ENC Rule Start.
                  //:// First add Fall classes to first period and Spring and Summer classes to 2nd period.
                  //:nProfileCollegeYearID = mFAProf.CollegeYear.ID
                  {MutableInt mi_nProfileCollegeYearID = new MutableInt( nProfileCollegeYearID );
                                     GetIntegerFromAttribute( mi_nProfileCollegeYearID, mFAProf, "CollegeYear", "ID" );
                  nProfileCollegeYearID = mi_nProfileCollegeYearID.intValue( );}
                  //:FOR EACH mFAProf.Enrolled
                  RESULT = mFAProf.cursor( "Enrolled" ).setFirst().toInt();
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //:nCollegeYearID = 0
                     nCollegeYearID = 0;
                     //:IF mFAProf.EnrolledClassCollegeYear EXISTS
                     lTempInteger_10 = CheckExistenceOfEntity( mFAProf, "EnrolledClassCollegeYear" );
                     if ( lTempInteger_10 == 0 )
                     { 
                        //:nCollegeYearID = mFAProf.EnrolledClassCollegeYear.ID 
                        {MutableInt mi_nCollegeYearID = new MutableInt( nCollegeYearID );
                                                 GetIntegerFromAttribute( mi_nCollegeYearID, mFAProf, "EnrolledClassCollegeYear", "ID" );
                        nCollegeYearID = mi_nCollegeYearID.intValue( );}
                        //:szSemester     = mFAProf.EnrolledClassCollegeTerm.Semester 
                        {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
                        StringBuilder sb_szSemester;
                        if ( szSemester == null )
                           sb_szSemester = new StringBuilder( 32 );
                        else
                           sb_szSemester = new StringBuilder( szSemester );
                                                 GetVariableFromAttribute( sb_szSemester, mi_lTempInteger_11, 'S', 3, mFAProf, "EnrolledClassCollegeTerm", "Semester", "", 0 );
                        lTempInteger_11 = mi_lTempInteger_11.intValue( );
                        szSemester = sb_szSemester.toString( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:IF mFAProf.EnrolledOverrideCollegeYear EXISTS
                        lTempInteger_12 = CheckExistenceOfEntity( mFAProf, "EnrolledOverrideCollegeYear" );
                        if ( lTempInteger_12 == 0 )
                        { 
                           //:nCollegeYearID = mFAProf.EnrolledOverrideCollegeYear.ID 
                           {MutableInt mi_nCollegeYearID = new MutableInt( nCollegeYearID );
                                                       GetIntegerFromAttribute( mi_nCollegeYearID, mFAProf, "EnrolledOverrideCollegeYear", "ID" );
                           nCollegeYearID = mi_nCollegeYearID.intValue( );}
                           //:szSemester     = mFAProf.EnrolledOverrideCollegeTerm.Semester 
                           {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
                           StringBuilder sb_szSemester;
                           if ( szSemester == null )
                              sb_szSemester = new StringBuilder( 32 );
                           else
                              sb_szSemester = new StringBuilder( szSemester );
                                                       GetVariableFromAttribute( sb_szSemester, mi_lTempInteger_13, 'S', 3, mFAProf, "EnrolledOverrideCollegeTerm", "Semester", "", 0 );
                           lTempInteger_13 = mi_lTempInteger_13.intValue( );
                           szSemester = sb_szSemester.toString( );}
                        } 

                        //:END
                     } 

                     //:END
                     //:IF nCollegeYearID = nProfileCollegeYearID AND 
                     //:   (szSemester = "01" OR szSemester = "03" OR szSemester = "04")
                     if ( nCollegeYearID == nProfileCollegeYearID && ( ZeidonStringCompare( szSemester, 1, 0, "01", 1, 0, 3 ) == 0 || ZeidonStringCompare( szSemester, 1, 0, "03", 1, 0, 3 ) == 0 ||
                        ZeidonStringCompare( szSemester, 1, 0, "04", 1, 0, 3 ) == 0 ) )
                     { 
                        //:// The Enrollment entry is for the same College Year, so include Fall entries in first period and Spring
                        //:// and Summer entries in 2nd period.
                        //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod 
                        RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
                        //:IF szSemester = "03" OR szSemester = "04"
                        if ( ZeidonStringCompare( szSemester, 1, 0, "03", 1, 0, 3 ) == 0 || ZeidonStringCompare( szSemester, 1, 0, "04", 1, 0, 3 ) == 0 )
                        { 
                           //:SET CURSOR NEXT mFAProf.PerProfileFinAidAwardPeriod  
                           RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
                        } 

                        //:END 
                        //:INCLUDE mFAProf.DisplayEnrolled FROM mFAProf.Enrolled 
                        RESULT = IncludeSubobjectFromSubobject( mFAProf, "DisplayEnrolled", mFAProf, "Enrolled", zPOS_AFTER );
                     } 

                     RESULT = mFAProf.cursor( "Enrolled" ).setNextContinue().toInt();;
                     //:END
                  } 

                  //:END // End ENC Rule

                  //:// Compute Credits per period.
                  //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod 
                  RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
                  //:dPeriod1Credits = mFAProf.PerProfileFinAidAwardPeriod.dCreditsPerDisb   // This is now just Fall credits.
                  {MutableDouble md_dPeriod1Credits = new MutableDouble( dPeriod1Credits );
                                     GetDecimalFromAttribute( md_dPeriod1Credits, mFAProf, "PerProfileFinAidAwardPeriod", "dCreditsPerDisb" );
                  dPeriod1Credits = md_dPeriod1Credits.doubleValue( );}
                  //:SET CURSOR NEXT mFAProf.PerProfileFinAidAwardPeriod
                  RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
                  //:dPeriod2Credits = mFAProf.PerProfileFinAidAwardPeriod.dCreditsPerDisb   // This is now Spring and Summer credits.
                  {MutableDouble md_dPeriod2Credits = new MutableDouble( dPeriod2Credits );
                                     GetDecimalFromAttribute( md_dPeriod2Credits, mFAProf, "PerProfileFinAidAwardPeriod", "dCreditsPerDisb" );
                  dPeriod2Credits = md_dPeriod2Credits.doubleValue( );}

                  //:// Use rules above to determine if January course should be included under first or 2nd period.
                  //:CreateViewFromView( lFAAdminT, lFAAdmin )
                  CreateViewFromView( lFAAdminT, lFAAdmin );
                  //:SET CURSOR FIRST lFAAdminT.FinAidAdmin WHERE lFAAdminT.CollegeYear.ID = nProfileCollegeYearID
                  RESULT = lFAAdminT.cursor( "FinAidAdmin" ).setFirst().toInt();
                  if ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( lFAAdminT, "CollegeYear", "ID", nProfileCollegeYearID ) != 0 ) )
                     { 
                        RESULT = lFAAdminT.cursor( "FinAidAdmin" ).setNextContinue().toInt();;
                     } 

                  } 

                  //:SET CURSOR FIRST lFAAdminT.CollegeTerm WHERE lFAAdminT.CollegeTerm.Semester = "01"
                  RESULT = lFAAdminT.cursor( "CollegeTerm" ).setFirst( "Semester", "01" ).toInt();
                  //:dMinFallCredits = lFAAdminT.CollegeTerm.FullTimeMinCredits 
                  {MutableDouble md_dMinFallCredits = new MutableDouble( dMinFallCredits );
                                     GetDecimalFromAttribute( md_dMinFallCredits, lFAAdminT, "CollegeTerm", "FullTimeMinCredits" );
                  dMinFallCredits = md_dMinFallCredits.doubleValue( );}
                  //:SET CURSOR FIRST lFAAdminT.CollegeTerm WHERE lFAAdminT.CollegeTerm.Semester = "03"
                  RESULT = lFAAdminT.cursor( "CollegeTerm" ).setFirst( "Semester", "03" ).toInt();
                  //:dMinSpringCredits = lFAAdminT.CollegeTerm.FullTimeMinCredits 
                  {MutableDouble md_dMinSpringCredits = new MutableDouble( dMinSpringCredits );
                                     GetDecimalFromAttribute( md_dMinSpringCredits, lFAAdminT, "CollegeTerm", "FullTimeMinCredits" );
                  dMinSpringCredits = md_dMinSpringCredits.doubleValue( );}
                  //:DropView( lFAAdminT )
                  DropView( lFAAdminT );
                  //:IF dPeriod1Credits = 0 OR 
                  //:   (dPeriod2Credits > 0 AND dPeriod2Credits < dMinSpringCredits AND dPeriod1Credits >= dMinFallCredits)
                  if ( dPeriod1Credits == 0 || ( dPeriod2Credits > 0 && dPeriod2Credits < dMinSpringCredits && dPeriod1Credits >= dMinFallCredits ) )
                  { 

                     //:// January classes are added to Spring Term.
                     //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod 
                     RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
                     //:SET CURSOR NEXT mFAProf.PerProfileFinAidAwardPeriod
                     RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
                     //:ELSE
                  } 
                  else
                  { 
                     //:// January classes are added to Fall Term
                     //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod 
                     RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
                  } 

                  //:END
                  //:// Include January classes to correct period.
                  //:FOR EACH mFAProf.Enrolled
                  RESULT = mFAProf.cursor( "Enrolled" ).setFirst().toInt();
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //:nCollegeYearID = 0
                     nCollegeYearID = 0;
                     //:IF mFAProf.EnrolledClassCollegeYear EXISTS
                     lTempInteger_14 = CheckExistenceOfEntity( mFAProf, "EnrolledClassCollegeYear" );
                     if ( lTempInteger_14 == 0 )
                     { 
                        //:nCollegeYearID = mFAProf.EnrolledClassCollegeYear.ID 
                        {MutableInt mi_nCollegeYearID = new MutableInt( nCollegeYearID );
                                                 GetIntegerFromAttribute( mi_nCollegeYearID, mFAProf, "EnrolledClassCollegeYear", "ID" );
                        nCollegeYearID = mi_nCollegeYearID.intValue( );}
                        //:szSemester     = mFAProf.EnrolledClassCollegeTerm.Semester 
                        {MutableInt mi_lTempInteger_15 = new MutableInt( lTempInteger_15 );
                        StringBuilder sb_szSemester;
                        if ( szSemester == null )
                           sb_szSemester = new StringBuilder( 32 );
                        else
                           sb_szSemester = new StringBuilder( szSemester );
                                                 GetVariableFromAttribute( sb_szSemester, mi_lTempInteger_15, 'S', 3, mFAProf, "EnrolledClassCollegeTerm", "Semester", "", 0 );
                        lTempInteger_15 = mi_lTempInteger_15.intValue( );
                        szSemester = sb_szSemester.toString( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:IF mFAProf.EnrolledOverrideCollegeYear EXISTS
                        lTempInteger_16 = CheckExistenceOfEntity( mFAProf, "EnrolledOverrideCollegeYear" );
                        if ( lTempInteger_16 == 0 )
                        { 
                           //:nCollegeYearID = mFAProf.EnrolledOverrideCollegeYear.ID 
                           {MutableInt mi_nCollegeYearID = new MutableInt( nCollegeYearID );
                                                       GetIntegerFromAttribute( mi_nCollegeYearID, mFAProf, "EnrolledOverrideCollegeYear", "ID" );
                           nCollegeYearID = mi_nCollegeYearID.intValue( );}
                           //:szSemester     = mFAProf.EnrolledOverrideCollegeTerm.Semester 
                           {MutableInt mi_lTempInteger_17 = new MutableInt( lTempInteger_17 );
                           StringBuilder sb_szSemester;
                           if ( szSemester == null )
                              sb_szSemester = new StringBuilder( 32 );
                           else
                              sb_szSemester = new StringBuilder( szSemester );
                                                       GetVariableFromAttribute( sb_szSemester, mi_lTempInteger_17, 'S', 3, mFAProf, "EnrolledOverrideCollegeTerm", "Semester", "", 0 );
                           lTempInteger_17 = mi_lTempInteger_17.intValue( );
                           szSemester = sb_szSemester.toString( );}
                        } 

                        //:END
                     } 

                     //:END
                     //:IF nCollegeYearID = nProfileCollegeYearID AND szSemester = "02"
                     if ( nCollegeYearID == nProfileCollegeYearID && ZeidonStringCompare( szSemester, 1, 0, "02", 1, 0, 3 ) == 0 )
                     { 
                        //:INCLUDE mFAProf.DisplayEnrolled FROM mFAProf.Enrolled 
                        RESULT = IncludeSubobjectFromSubobject( mFAProf, "DisplayEnrolled", mFAProf, "Enrolled", zPOS_AFTER );
                     } 

                     RESULT = mFAProf.cursor( "Enrolled" ).setNextContinue().toInt();;
                     //:END
                  } 

                  //:END
               } 

               //:   
               //:END
            } 

            //:END
         } 

         //:END

         //:// Make sure award disbursements are sorted by period.
         //:FOR EACH mFAProf.FinAidAward
         RESULT = mFAProf.cursor( "FinAidAward" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:OrderEntityForView( mFAProf, "FinAidAwardDisbursement", "FinAidAwardPeriod.BeginDate A" )
            OrderEntityForView( mFAProf, "FinAidAwardDisbursement", "FinAidAwardPeriod.BeginDate A" );
            RESULT = mFAProf.cursor( "FinAidAward" ).setNextContinue().toInt();;
         } 

         //:END

         //:// Build the expanded list of Comment Codes.
         //:IF mFAProf.FAISIREligibility EXISTS 
         lTempInteger_18 = CheckExistenceOfEntity( mFAProf, "FAISIREligibility" );
         if ( lTempInteger_18 == 0 )
         { 
            //:// Get access to the Comment Codes conversion object.
            //:// We will first access an attribute with domain that uses the X_FAISIR_Tables to get the table loaded.
            //:GetStringFromAttributeByContext( szSourceString, mFAProf, "FAISIREligibility", "SAACitizenshipFlag", "", 500 )  // Used just to load proper Domain object.
            {StringBuilder sb_szSourceString;
            if ( szSourceString == null )
               sb_szSourceString = new StringBuilder( 32 );
            else
               sb_szSourceString = new StringBuilder( szSourceString );
                         GetStringFromAttributeByContext( sb_szSourceString, mFAProf, "FAISIREligibility", "SAACitizenshipFlag", "", 500 );
            szSourceString = sb_szSourceString.toString( );}
            //:szCommentsDomain = "X_FAISIR_Tables" + mFAProf.FAISIR.YearIndicator 
            {MutableInt mi_lTempInteger_19 = new MutableInt( lTempInteger_19 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_19, 'S', 255, mFAProf, "FAISIR", "YearIndicator", "", 0 );
            lTempInteger_19 = mi_lTempInteger_19.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szCommentsDomain;
            if ( szCommentsDomain == null )
               sb_szCommentsDomain = new StringBuilder( 32 );
            else
               sb_szCommentsDomain = new StringBuilder( szCommentsDomain );
                        ZeidonStringCopy( sb_szCommentsDomain, 1, 0, "X_FAISIR_Tables", 1, 0, 33 );
            szCommentsDomain = sb_szCommentsDomain.toString( );}
             {StringBuilder sb_szCommentsDomain;
            if ( szCommentsDomain == null )
               sb_szCommentsDomain = new StringBuilder( 32 );
            else
               sb_szCommentsDomain = new StringBuilder( szCommentsDomain );
                        ZeidonStringConcat( sb_szCommentsDomain, 1, 0, szTempString_0, 1, 0, 33 );
            szCommentsDomain = sb_szCommentsDomain.toString( );}
            //:GET VIEW mFAISIRD NAMED szCommentsDomain
            RESULT = GetViewByName( mFAISIRD, szCommentsDomain, mFAProfOrig, zLEVEL_TASK );
            //:IF RESULT >= 0
            if ( RESULT >= 0 )
            { 
               //:SET CURSOR FIRST mFAISIRD.FAISIRDomain WHERE mFAISIRD.FAISIRDomain.Name = "CommentCodes" 
               RESULT = mFAISIRD.cursor( "FAISIRDomain" ).setFirst( "Name", "CommentCodes" ).toInt();
               //:szSourceString = mFAProf.FAISIREligibility.CommentCodes 
               {MutableInt mi_lTempInteger_20 = new MutableInt( lTempInteger_20 );
               StringBuilder sb_szSourceString;
               if ( szSourceString == null )
                  sb_szSourceString = new StringBuilder( 32 );
               else
                  sb_szSourceString = new StringBuilder( szSourceString );
                               GetVariableFromAttribute( sb_szSourceString, mi_lTempInteger_20, 'S', 501, mFAProf, "FAISIREligibility", "CommentCodes", "", 0 );
               lTempInteger_20 = mi_lTempInteger_20.intValue( );
               szSourceString = sb_szSourceString.toString( );}
               //:nLen = zGetStringLen( szSourceString )
               nLen = zGetStringLen( szSourceString );
               //:nLen = nLen - 3
               nLen = nLen - 3;
               //:nIndex = 1
               nIndex = 1;
               //:LOOP WHILE nIndex <= nLen
               while ( nIndex <= nLen )
               { 
                  //:szCode = szSourceString [ nIndex : 3 ]
                   {StringBuilder sb_szCode;
                  if ( szCode == null )
                     sb_szCode = new StringBuilder( 32 );
                  else
                     sb_szCode = new StringBuilder( szCode );
                                    ZeidonStringCopy( sb_szCode, 1, 0, szSourceString, nIndex, 3, 121 );
                  szCode = sb_szCode.toString( );}
                  //:SET CURSOR FIRST mFAISIRD.FAISIRDomainValue WHERE mFAISIRD.FAISIRDomainValue.InternalStringValue = szCode
                  RESULT = mFAISIRD.cursor( "FAISIRDomainValue" ).setFirst( "InternalStringValue", szCode ).toInt();
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:szCommentsString = szCommentsString + szCode + NEW_LINE + "  " + mFAISIRD.FAISIRDomainValue.ExternalDescription + NEW_LINE + NEW_LINE
                      {StringBuilder sb_szCommentsString;
                     if ( szCommentsString == null )
                        sb_szCommentsString = new StringBuilder( 32 );
                     else
                        sb_szCommentsString = new StringBuilder( szCommentsString );
                                          ZeidonStringConcat( sb_szCommentsString, 1, 0, szCode, 1, 0, 10001 );
                     szCommentsString = sb_szCommentsString.toString( );}
                      {StringBuilder sb_szCommentsString;
                     if ( szCommentsString == null )
                        sb_szCommentsString = new StringBuilder( 32 );
                     else
                        sb_szCommentsString = new StringBuilder( szCommentsString );
                                          ZeidonStringConcat( sb_szCommentsString, 1, 0, NEW_LINE, 1, 0, 10001 );
                     szCommentsString = sb_szCommentsString.toString( );}
                      {StringBuilder sb_szCommentsString;
                     if ( szCommentsString == null )
                        sb_szCommentsString = new StringBuilder( 32 );
                     else
                        sb_szCommentsString = new StringBuilder( szCommentsString );
                                          ZeidonStringConcat( sb_szCommentsString, 1, 0, "  ", 1, 0, 10001 );
                     szCommentsString = sb_szCommentsString.toString( );}
                     {MutableInt mi_lTempInteger_21 = new MutableInt( lTempInteger_21 );
                     StringBuilder sb_szTempString_1;
                     if ( szTempString_1 == null )
                        sb_szTempString_1 = new StringBuilder( 32 );
                     else
                        sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                           GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_21, 'S', 4001, mFAISIRD, "FAISIRDomainValue", "ExternalDescription", "", 0 );
                     lTempInteger_21 = mi_lTempInteger_21.intValue( );
                     szTempString_1 = sb_szTempString_1.toString( );}
                      {StringBuilder sb_szCommentsString;
                     if ( szCommentsString == null )
                        sb_szCommentsString = new StringBuilder( 32 );
                     else
                        sb_szCommentsString = new StringBuilder( szCommentsString );
                                          ZeidonStringConcat( sb_szCommentsString, 1, 0, szTempString_1, 1, 0, 10001 );
                     szCommentsString = sb_szCommentsString.toString( );}
                      {StringBuilder sb_szCommentsString;
                     if ( szCommentsString == null )
                        sb_szCommentsString = new StringBuilder( 32 );
                     else
                        sb_szCommentsString = new StringBuilder( szCommentsString );
                                          ZeidonStringConcat( sb_szCommentsString, 1, 0, NEW_LINE, 1, 0, 10001 );
                     szCommentsString = sb_szCommentsString.toString( );}
                      {StringBuilder sb_szCommentsString;
                     if ( szCommentsString == null )
                        sb_szCommentsString = new StringBuilder( 32 );
                     else
                        sb_szCommentsString = new StringBuilder( szCommentsString );
                                          ZeidonStringConcat( sb_szCommentsString, 1, 0, NEW_LINE, 1, 0, 10001 );
                     szCommentsString = sb_szCommentsString.toString( );}
                  } 

                  //:END
                  //:nIndex = nIndex + 3
                  nIndex = nIndex + 3;
               } 

               //:END
               //:mFAProf.FAISIREligibility.wCommentsList = szCommentsString
               SetAttributeFromString( mFAProf, "FAISIREligibility", "wCommentsList", szCommentsString );
            } 

            //:END
         } 

         //:END

         //:// Build Revision/Swapped data for display.
         //:FOR EACH mFAProf.FinAidAwardDisbursement WITHIN mFAProf.FinAidProfile
         RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setFirst( "FinAidProfile" ).toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mFAProf.SwappedToDisbursement EXISTS
            lTempInteger_5 = CheckExistenceOfEntity( mFAProf, "SwappedToDisbursement" );
            if ( lTempInteger_5 == 0 )
            { 
               //:mFAProf.FinAidAwardDisbursement.wDisplayRevisionSwappedData = "Swapped To"
               SetAttributeFromString( mFAProf, "FinAidAwardDisbursement", "wDisplayRevisionSwappedData", "Swapped To" );
               //:FOR EACH mFAProf.SwappedToDisbursement
               RESULT = mFAProf.cursor( "SwappedToDisbursement" ).setFirst().toInt();
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:CREATE ENTITY mFAProf.DisplaySwappedEntries 
                  RESULT = CreateEntity( mFAProf, "DisplaySwappedEntries", zPOS_AFTER );
                  //:CreateViewFromView( mFAProf2, mFAProf )
                  CreateViewFromView( mFAProf2, mFAProf );
                  //:SET CURSOR FIRST mFAProf2.FinAidAwardDisbursement WITHIN mFAProf2.FinAidProfile
                  //:           WHERE mFAProf2.FinAidAwardDisbursement.ID = mFAProf2.SwappedToDisbursement.ID 
                  {MutableInt mi_lTempInteger_22 = new MutableInt( lTempInteger_22 );
                                     GetIntegerFromAttribute( mi_lTempInteger_22, mFAProf2, "SwappedToDisbursement", "ID" );
                  lTempInteger_22 = mi_lTempInteger_22.intValue( );}
                  RESULT = mFAProf2.cursor( "FinAidAwardDisbursement" ).setFirst( "ID", lTempInteger_22, "FinAidProfile" ).toInt();
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:IssueError( mFAProf,0,0, "Programming Error" )
                     IssueError( mFAProf, 0, 0, "Programming Error" );
                  } 

                  //:END
                  //:mFAProf.DisplaySwappedEntries.DisbursementDate = mFAProf2.FinAidAwardDisbursement.DisbursementDate 
                  SetAttributeFromAttribute( mFAProf, "DisplaySwappedEntries", "DisbursementDate", mFAProf2, "FinAidAwardDisbursement", "DisbursementDate" );
                  //:mFAProf.DisplaySwappedEntries.AmountExpected   = mFAProf2.FinAidAwardDisbursement.AmountExpected 
                  SetAttributeFromAttribute( mFAProf, "DisplaySwappedEntries", "AmountExpected", mFAProf2, "FinAidAwardDisbursement", "AmountExpected" );
                  //:mFAProf.DisplaySwappedEntries.Amount           = mFAProf2.FinAidAwardDisbursement.Amount 
                  SetAttributeFromAttribute( mFAProf, "DisplaySwappedEntries", "Amount", mFAProf2, "FinAidAwardDisbursement", "Amount" );
                  //:mFAProf.DisplaySwappedEntries.PeriodDesignator = mFAProf2.FinAidAwardPeriod.PeriodDesignator 
                  SetAttributeFromAttribute( mFAProf, "DisplaySwappedEntries", "PeriodDesignator", mFAProf2, "FinAidAwardPeriod", "PeriodDesignator" );
                  //:mFAProf.DisplaySwappedEntries.SwappedSourceDescription = "Swapped To " + mFAProf2.FinAidSource.Name 
                  {MutableInt mi_lTempInteger_23 = new MutableInt( lTempInteger_23 );
                  StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                     GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_23, 'S', 51, mFAProf2, "FinAidSource", "Name", "", 0 );
                  lTempInteger_23 = mi_lTempInteger_23.intValue( );
                  szTempString_3 = sb_szTempString_3.toString( );}
                   {StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                    ZeidonStringCopy( sb_szTempString_2, 1, 0, "Swapped To ", 1, 0, 255 );
                  szTempString_2 = sb_szTempString_2.toString( );}
                   {StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                    ZeidonStringConcat( sb_szTempString_2, 1, 0, szTempString_3, 1, 0, 255 );
                  szTempString_2 = sb_szTempString_2.toString( );}
                  SetAttributeFromString( mFAProf, "DisplaySwappedEntries", "SwappedSourceDescription", szTempString_2 );
                  //:DropView( mFAProf2 )
                  DropView( mFAProf2 );
                  RESULT = mFAProf.cursor( "SwappedToDisbursement" ).setNextContinue().toInt();;
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF mFAProf.SwappedFromDisbursement EXISTS
               lTempInteger_24 = CheckExistenceOfEntity( mFAProf, "SwappedFromDisbursement" );
               if ( lTempInteger_24 == 0 )
               { 
                  //:mFAProf.FinAidAwardDisbursement.wDisplayRevisionSwappedData = "Swapped From"
                  SetAttributeFromString( mFAProf, "FinAidAwardDisbursement", "wDisplayRevisionSwappedData", "Swapped From" );
                  //:CREATE ENTITY mFAProf.DisplaySwappedEntries 
                  RESULT = CreateEntity( mFAProf, "DisplaySwappedEntries", zPOS_AFTER );
                  //:CreateViewFromView( mFAProf2, mFAProf )
                  CreateViewFromView( mFAProf2, mFAProf );
                  //:SET CURSOR FIRST mFAProf2.FinAidAwardDisbursement WITHIN mFAProf2.FinAidProfile
                  //:           WHERE mFAProf2.FinAidAwardDisbursement.ID = mFAProf2.SwappedFromDisbursement.ID 
                  {MutableInt mi_lTempInteger_25 = new MutableInt( lTempInteger_25 );
                                     GetIntegerFromAttribute( mi_lTempInteger_25, mFAProf2, "SwappedFromDisbursement", "ID" );
                  lTempInteger_25 = mi_lTempInteger_25.intValue( );}
                  RESULT = mFAProf2.cursor( "FinAidAwardDisbursement" ).setFirst( "ID", lTempInteger_25, "FinAidProfile" ).toInt();
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:IssueError( mFAProf,0,0, "Programming Error" )
                     IssueError( mFAProf, 0, 0, "Programming Error" );
                  } 

                  //:END
                  //:mFAProf.DisplaySwappedEntries.DisbursementDate = mFAProf2.FinAidAwardDisbursement.DisbursementDate 
                  SetAttributeFromAttribute( mFAProf, "DisplaySwappedEntries", "DisbursementDate", mFAProf2, "FinAidAwardDisbursement", "DisbursementDate" );
                  //:mFAProf.DisplaySwappedEntries.AmountExpected   = mFAProf2.FinAidAwardDisbursement.AmountExpected 
                  SetAttributeFromAttribute( mFAProf, "DisplaySwappedEntries", "AmountExpected", mFAProf2, "FinAidAwardDisbursement", "AmountExpected" );
                  //:mFAProf.DisplaySwappedEntries.Amount           = mFAProf2.FinAidAwardDisbursement.Amount 
                  SetAttributeFromAttribute( mFAProf, "DisplaySwappedEntries", "Amount", mFAProf2, "FinAidAwardDisbursement", "Amount" );
                  //:mFAProf.DisplaySwappedEntries.PeriodDesignator = mFAProf2.FinAidAwardPeriod.PeriodDesignator 
                  SetAttributeFromAttribute( mFAProf, "DisplaySwappedEntries", "PeriodDesignator", mFAProf2, "FinAidAwardPeriod", "PeriodDesignator" );
                  //:mFAProf.DisplaySwappedEntries.SwappedSourceDescription = "Swapped From " + mFAProf2.FinAidSource.Name 
                  {MutableInt mi_lTempInteger_26 = new MutableInt( lTempInteger_26 );
                  StringBuilder sb_szTempString_5;
                  if ( szTempString_5 == null )
                     sb_szTempString_5 = new StringBuilder( 32 );
                  else
                     sb_szTempString_5 = new StringBuilder( szTempString_5 );
                                     GetVariableFromAttribute( sb_szTempString_5, mi_lTempInteger_26, 'S', 51, mFAProf2, "FinAidSource", "Name", "", 0 );
                  lTempInteger_26 = mi_lTempInteger_26.intValue( );
                  szTempString_5 = sb_szTempString_5.toString( );}
                   {StringBuilder sb_szTempString_4;
                  if ( szTempString_4 == null )
                     sb_szTempString_4 = new StringBuilder( 32 );
                  else
                     sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                    ZeidonStringCopy( sb_szTempString_4, 1, 0, "Swapped From ", 1, 0, 255 );
                  szTempString_4 = sb_szTempString_4.toString( );}
                   {StringBuilder sb_szTempString_4;
                  if ( szTempString_4 == null )
                     sb_szTempString_4 = new StringBuilder( 32 );
                  else
                     sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                    ZeidonStringConcat( sb_szTempString_4, 1, 0, szTempString_5, 1, 0, 255 );
                  szTempString_4 = sb_szTempString_4.toString( );}
                  SetAttributeFromString( mFAProf, "DisplaySwappedEntries", "SwappedSourceDescription", szTempString_4 );
                  //:DropView( mFAProf2 )
                  DropView( mFAProf2 );
                  //:ELSE
               } 
               else
               { 
                  //:IF mFAProf.FinAidAwardDisbursement.RevisionType != ""
                  if ( CompareAttributeToString( mFAProf, "FinAidAwardDisbursement", "RevisionType", "" ) != 0 )
                  { 
                     //:GetStringFromAttributeByContext( szRevisionType, mFAProf, "FinAidAwardDisbursement", "RevisionType", "", 10 )
                     {StringBuilder sb_szRevisionType;
                     if ( szRevisionType == null )
                        sb_szRevisionType = new StringBuilder( 32 );
                     else
                        sb_szRevisionType = new StringBuilder( szRevisionType );
                                           GetStringFromAttributeByContext( sb_szRevisionType, mFAProf, "FinAidAwardDisbursement", "RevisionType", "", 10 );
                     szRevisionType = sb_szRevisionType.toString( );}
                     //:mFAProf.FinAidAwardDisbursement.wDisplayRevisionSwappedData = szRevisionType
                     SetAttributeFromString( mFAProf, "FinAidAwardDisbursement", "wDisplayRevisionSwappedData", szRevisionType );
                  } 

                  //:END
               } 

               //:END
            } 

            RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setNextContinue().toInt();;
            //:END
         } 

         //:END

         //:// Initialize list of awards to not show inactive or declined entries.
         //:ShowHidePeriodAwards( mFAProf )
         omFAProf_ShowHidePeriodAwards( mFAProf );
         //:SET CURSOR FIRST mFAProf.FinAidProfile     // Reposition all subobjects on first entry. 
         RESULT = mFAProf.cursor( "FinAidProfile" ).setFirst().toInt();

         //:DropView( mFAProf )
         DropView( mFAProf );
         break ;

      //:/* end zOCE_ACTIVATE */
      //:OF   zOCE_ACTIVATE_EMPTY:
      case zOCE_ACTIVATE_EMPTY :
         break ;

      //:/* end zOCE_ACTIVATE_EMPTY */

      //:OF   zOCE_COMMIT:
      case zOCE_COMMIT :

         //:CreateViewFromView( mFAProf, mFAProfOrig )
         CreateViewFromView( mFAProf, mFAProfOrig );
         //:FOR EACH mFAProf.FinAidProfile
         RESULT = mFAProf.cursor( "FinAidProfile" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF  mFAProf.Person DOES not EXIST 
            lTempInteger_27 = CheckExistenceOfEntity( mFAProf, "Person" );
            if ( lTempInteger_27 != 0 )
            { 
               //:nRC = MessagePrompt( mFAProf, "", "mFAProf No Person",
               //:      "This Financial Aid Profile has no Person attached. Are you SURE you want to save it?",
               //:      0, zBUTTONS_YESNO, zRESPONSE_NO, 0 )
               nRC = MessagePrompt( mFAProf, "", "mFAProf No Person", "This Financial Aid Profile has no Person attached. Are you SURE you want to save it?", 0, zBUTTONS_YESNO, zRESPONSE_NO, 0 );
               //:IF nRC = zRESPONSE_NO
               if ( nRC == zRESPONSE_NO )
               { 
                  //:RETURN -1 
                  if(8==8)return( -1 );
               } 

               //:END
            } 

            //:END
            //:IF  mFAProf.FinAidProfile DOES not EXIST 
            lTempInteger_28 = CheckExistenceOfEntity( mFAProf, "FinAidProfile" );
            if ( lTempInteger_28 != 0 )
            { 
               //:nRC = MessagePrompt( mFAProf, "", "mFAProf No Profile ",
               //:      "This Financial Aid Profile has no Root. Are you SURE you want to save it?",
               //:      0, zBUTTONS_YESNO, zRESPONSE_NO, 0 )
               nRC = MessagePrompt( mFAProf, "", "mFAProf No Profile ", "This Financial Aid Profile has no Root. Are you SURE you want to save it?", 0, zBUTTONS_YESNO, zRESPONSE_NO, 0 );
               //:IF nRC = zRESPONSE_NO
               if ( nRC == zRESPONSE_NO )
               { 
                  //:RETURN -1 
                  if(8==8)return( -1 );
               } 

               //:END
            } 

            //:END
            //:IF mFAProf.CurrentPerson EXISTS 
            lTempInteger_29 = CheckExistenceOfEntity( mFAProf, "CurrentPerson" );
            if ( lTempInteger_29 == 0 )
            { 
               //:EXCLUDE  mFAProf.CurrentPerson 
               RESULT = ExcludeEntity( mFAProf, "CurrentPerson", zREPOS_AFTER );
            } 

            //:END
            //:INCLUDE  mFAProf.CurrentPerson  FROM mFAProf.Person 
            RESULT = IncludeSubobjectFromSubobject( mFAProf, "CurrentPerson", mFAProf, "Person", zPOS_AFTER );
            //:szName = mFAProf.Person.LastName + ", " + mFAProf.Person.FirstName 
            {StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                         GetStringFromAttribute( sb_szName, mFAProf, "Person", "LastName" );
            szName = sb_szName.toString( );}
             {StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                        ZeidonStringConcat( sb_szName, 1, 0, ", ", 1, 0, 51 );
            szName = sb_szName.toString( );}
            {MutableInt mi_lTempInteger_30 = new MutableInt( lTempInteger_30 );
            StringBuilder sb_szTempString_6;
            if ( szTempString_6 == null )
               sb_szTempString_6 = new StringBuilder( 32 );
            else
               sb_szTempString_6 = new StringBuilder( szTempString_6 );
                         GetVariableFromAttribute( sb_szTempString_6, mi_lTempInteger_30, 'S', 51, mFAProf, "Person", "FirstName", "", 0 );
            lTempInteger_30 = mi_lTempInteger_30.intValue( );
            szTempString_6 = sb_szTempString_6.toString( );}
             {StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                        ZeidonStringConcat( sb_szName, 1, 0, szTempString_6, 1, 0, 51 );
            szName = sb_szName.toString( );}
            //:TraceLineS("mFAProf Commiting: ", szName )
            TraceLineS( "mFAProf Commiting: ", szName );
            //:szDate = wXferO.Root.dCurrentDate 
            {MutableInt mi_lTempInteger_31 = new MutableInt( lTempInteger_31 );
            StringBuilder sb_szDate;
            if ( szDate == null )
               sb_szDate = new StringBuilder( 32 );
            else
               sb_szDate = new StringBuilder( szDate );
                         GetVariableFromAttribute( sb_szDate, mi_lTempInteger_31, 'S', 33, wXferO, "Root", "dCurrentDate", "", 0 );
            lTempInteger_31 = mi_lTempInteger_31.intValue( );
            szDate = sb_szDate.toString( );}
            //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod 
            //:   WHERE mFAProf.PerProfileFinAidAwardPeriod.BeginDate <= szDate 
            //:     AND mFAProf.PerProfileFinAidAwardPeriod.EndDate >= szDate 
            RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate", szDate ) > 0 || CompareAttributeToString( mFAProf, "PerProfileFinAidAwardPeriod", "EndDate", szDate ) < 0 ) )
               { 
                  RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
               } 

            } 

            //:IF RESULT >= zCURSOR_SET 
            if ( RESULT >= zCURSOR_SET )
            { 
               //:IF mFAProf.FinAidProfile.FullPartTime != mFAProf.PerProfileFinAidAwardPeriod.dFullPartTime 
               if ( CompareAttributeToAttribute( mFAProf, "FinAidProfile", "FullPartTime", mFAProf, "PerProfileFinAidAwardPeriod", "dFullPartTime" ) != 0 )
               { 
                  //:mFAProf.FinAidProfile.FullPartTime = mFAProf.PerProfileFinAidAwardPeriod.dFullPartTime 
                  SetAttributeFromAttribute( mFAProf, "FinAidProfile", "FullPartTime", mFAProf, "PerProfileFinAidAwardPeriod", "dFullPartTime" );
               } 

               //:END 
            } 

            //:END

            //:// Set ProfileYearBegin and ProfileYearEnd.
            //:// For Term Students, we determine the dates from the CollegeYear.
            //:// For Cohort Students, we determine the dates from the FinancialAidPeriods.
            //:// On 6/21/2006 Don C changed code below so that both Term Students and Cohort
            //:// students get the Begin/End Dates from the Periods.
            //:OrderEntityForView( mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate A" )
            OrderEntityForView( mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate A" );
            //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod
            RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
            //:IF RESULT >= zCURSOR_SET 
            if ( RESULT >= zCURSOR_SET )
            { 
               //:IF mFAProf.FinAidProfile.ProfileYearBegin != mFAProf.PerProfileFinAidAwardPeriod.BeginDate
               if ( CompareAttributeToAttribute( mFAProf, "FinAidProfile", "ProfileYearBegin", mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate" ) != 0 )
               { 
                  //:mFAProf.FinAidProfile.ProfileYearBegin = mFAProf.PerProfileFinAidAwardPeriod.BeginDate
                  SetAttributeFromAttribute( mFAProf, "FinAidProfile", "ProfileYearBegin", mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate" );
               } 

               //:END
               //:SET CURSOR LAST mFAProf.PerProfileFinAidAwardPeriod
               RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setLast().toInt();;
               //:IF mFAProf.FinAidProfile.ProfileYearEnd != mFAProf.PerProfileFinAidAwardPeriod.EndDate 
               if ( CompareAttributeToAttribute( mFAProf, "FinAidProfile", "ProfileYearEnd", mFAProf, "PerProfileFinAidAwardPeriod", "EndDate" ) != 0 )
               { 
                  //:mFAProf.FinAidProfile.ProfileYearEnd = mFAProf.PerProfileFinAidAwardPeriod.EndDate 
                  SetAttributeFromAttribute( mFAProf, "FinAidProfile", "ProfileYearEnd", mFAProf, "PerProfileFinAidAwardPeriod", "EndDate" );
               } 

               //:END
            } 

            //:END

            //:// Set the Original Award and Disbursement Amounts, if no Disburesements are swapped.
            //:FOR EACH mFAProf.FinAidAward 
            RESULT = mFAProf.cursor( "FinAidAward" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:// Changed by DonC on 8/3/2010.
               //:SET CURSOR FIRST mFAProf.SwappedToDisbursement WITHIN mFAProf.FinAidAward 
               RESULT = mFAProf.cursor( "SwappedToDisbursement" ).setFirst( "FinAidAward" ).toInt();
               //:IF RESULT < zCURSOR_SET 
               if ( RESULT < zCURSOR_SET )
               { 
                  //:mFAProf.FinAidAward.OriginalAmountOffered = mFAProf.FinAidAward.Amount
                  SetAttributeFromAttribute( mFAProf, "FinAidAward", "OriginalAmountOffered", mFAProf, "FinAidAward", "Amount" );
                  //:FOR EACH mFAProf.FinAidAwardDisbursement 
                  RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setFirst().toInt();
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //:mFAProf.FinAidAwardDisbursement.OriginalAmountExpected = mFAProf.FinAidAwardDisbursement.AmountExpected
                     SetAttributeFromAttribute( mFAProf, "FinAidAwardDisbursement", "OriginalAmountExpected", mFAProf, "FinAidAwardDisbursement", "AmountExpected" );
                     RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setNextContinue().toInt();;
                  } 

                  //:END
               } 

               RESULT = mFAProf.cursor( "FinAidAward" ).setNextContinue().toInt();;
               //:END
            } 

            //:END

            //:// Create the Change Log entry.
            //:IF mFAProf.Student EXISTS
            lTempInteger_32 = CheckExistenceOfEntity( mFAProf, "Student" );
            if ( lTempInteger_32 == 0 )
            { 
               //:GenerateTransactionEntryWDesc( mFAProf, "Fin Aid Profile" )
               {
                ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mFAProf );
                m_ZGLOBAL2_Operation.GenerateTransactionEntryWDesc( mFAProf, "Fin Aid Profile" );
                // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
               }
            } 

            //:END

            //:// Generate the persistent TotalUnmetNeed value.
            //:mFAProf.FinAidProfile.TotalUnmetNeed = mFAProf.FinAidProfile.dTotalUnmetNeed
            SetAttributeFromAttribute( mFAProf, "FinAidProfile", "TotalUnmetNeed", mFAProf, "FinAidProfile", "dTotalUnmetNeed" );

            RESULT = mFAProf.cursor( "FinAidProfile" ).setNextContinue().toInt();;
         } 


         //:END
         //:DropView( mFAProf )
         DropView( mFAProf );
         break ;
      //:   
      //:/* end zOCE_COMMIT */
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
omFAProf_fnLocalBuildQual_22( View     vSubtask,
                              zVIEW    vQualObject,
                              int      iYearID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidRequirement" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", iYearID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_5( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidAdmin" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidAdmin" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_6( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidCOA" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_7( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidProfile" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_8( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_5 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_5 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_9( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_7 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_7 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_10( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_9 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeTerm" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_9 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_11( View     vSubtask,
                              zVIEW    vQualObject,
                              int      nAdminID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidAdmin" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidAdmin" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nAdminID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_12( View     vSubtask,
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
omFAProf_fnLocalBuildQual_13( View     vSubtask,
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
omFAProf_fnLocalBuildQual_14( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidCOA" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_15( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidProfile" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_16( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_4 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Student" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_4 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_17( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_6 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "CollegeTerm" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_6 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_18( View     vSubtask,
                              zVIEW    vQualObject,
                              int      nID,
                              String   szYear )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Year" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szYear.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_19( View     vSubtask,
                              zVIEW    vQualObject,
                              int      nID,
                              String   szYear )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidProfile" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Prospect" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Year" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szYear.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_20( View     vSubtask,
                              zVIEW    vQualObject,
                              int      nSchShipID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidSource" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Scholarship" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nSchShipID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidAwardAssigned" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidAwardAssigned" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "<" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_21( View     vSubtask,
                              zVIEW    vQualObject,
                              int      nSrcID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidSource" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidSource" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nSrcID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidAwardAssigned" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidAwardAssigned" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "<" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_0( View     vSubtask,
                             zVIEW    vQualObject,
                             int      nAdminDivID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidAdmin" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nAdminDivID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_1( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_9 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidAdmin" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidAdmin" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_9 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_2( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_11 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidAdmin" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidAdmin" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_11 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_3( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_12 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidAdmin" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidAdmin" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_12 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFAProf_fnLocalBuildQual_4( View     vSubtask,
                             zVIEW    vQualObject,
                             int      lTempInteger_13 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidAdmin" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidAdmin" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_13 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DERIVED ATTRIBUTE OPERATION
//:dAggregateAllLoans( VIEW mFAProf BASED ON LOD mFAProf,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure, 
//:                    SHORT GetOrSetFlag )
//:                
//:   DECIMAL dTotalAmount 
public int 
omFAProf_dAggregateAllLoans( View     mFAProf,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   double  dTotalAmount = 0.0;
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;
   double  dTempDecimal_4 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mFAProf.FAISIRFinAidHistory EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mFAProf, "FAISIRFinAidHistory" );
         if ( lTempInteger_0 == 0 )
         { 
            //:dTotalAmount = mFAProf.FAISIRFinAidHistory.AggComSumTotal +
            //:               mFAProf.FinAidProfile.AdditionalUnsubLoanAggregate +
            //:               mFAProf.FinAidProfile.AdditionalSubLoanAggregate 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "FAISIRFinAidHistory", "AggComSumTotal" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                         GetDecimalFromAttribute( md_dTempDecimal_1, mFAProf, "FinAidProfile", "AdditionalUnsubLoanAggregate" );
            dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
            {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                         GetDecimalFromAttribute( md_dTempDecimal_2, mFAProf, "FinAidProfile", "AdditionalSubLoanAggregate" );
            dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
            dTotalAmount = dTempDecimal_0 + dTempDecimal_1 + dTempDecimal_2;
            //:ELSE
         } 
         else
         { 
            //:dTotalAmount = mFAProf.FinAidProfile.AdditionalUnsubLoanAggregate +
            //:               mFAProf.FinAidProfile.AdditionalSubLoanAggregate 
            {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                         GetDecimalFromAttribute( md_dTempDecimal_3, mFAProf, "FinAidProfile", "AdditionalUnsubLoanAggregate" );
            dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
            {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                         GetDecimalFromAttribute( md_dTempDecimal_4, mFAProf, "FinAidProfile", "AdditionalSubLoanAggregate" );
            dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
            dTotalAmount = dTempDecimal_3 + dTempDecimal_4;
         } 

         //:END

         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dTotalAmount, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dTotalAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dEarnedCreditsPrPeriod( VIEW mFAProf BASED ON LOD mFAProf,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )
//:   DECIMAL dCredits 
public int 
omFAProf_dEarnedCreditsPrPeriod( View     mFAProf,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   double  dCredits = 0.0;
   //:VIEW mFAProf2 BASED ON LOD mFAProf
   zVIEW    mFAProf2 = new zVIEW( );
   //:STRING ( 20 ) szFinalGrade
   String   szFinalGrade = null;
   //:DECIMAL FinalGrade
   double  FinalGrade = 0.0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:dCredits = 0
         dCredits = 0;
         //:CreateViewFromView( mFAProf2, mFAProf )
         CreateViewFromView( mFAProf2, mFAProf );
         //:FOR EACH mFAProf2.DisplayEnrolled 
         RESULT = mFAProf2.cursor( "DisplayEnrolled" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mFAProf2.DisplayEnrolled.Status = "C"
            if ( CompareAttributeToString( mFAProf2, "DisplayEnrolled", "Status", "C" ) == 0 )
            { 
               //:szFinalGrade = mFAProf2.DisplayEnrolled.FinalGrade 
               {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
               StringBuilder sb_szFinalGrade;
               if ( szFinalGrade == null )
                  sb_szFinalGrade = new StringBuilder( 32 );
               else
                  sb_szFinalGrade = new StringBuilder( szFinalGrade );
                               GetVariableFromAttribute( sb_szFinalGrade, mi_lTempInteger_0, 'S', 21, mFAProf2, "DisplayEnrolled", "FinalGrade", "", 0 );
               lTempInteger_0 = mi_lTempInteger_0.intValue( );
               szFinalGrade = sb_szFinalGrade.toString( );}
               //:IF szFinalGrade = "S"
               if ( ZeidonStringCompare( szFinalGrade, 1, 0, "S", 1, 0, 21 ) == 0 )
               { 
                  //:// For passed grade we count credits as earned.
                  //:dCredits = dCredits + mFAProf2.DisplayEnrolled.CreditHours 
                  {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                     GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf2, "DisplayEnrolled", "CreditHours" );
                  dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                  dCredits = dCredits + dTempDecimal_0;
                  //:ELSE
               } 
               else
               { 
                  //:// For regular grade, grade points must be a positive number.
                  //:GetStringFromAttributeByContext( szFinalGrade,
                  //:                                 mFAProf2, "DisplayEnrolled", "FinalGrade", "DegreeTrackGradePointValue", 20 )
                  {StringBuilder sb_szFinalGrade;
                  if ( szFinalGrade == null )
                     sb_szFinalGrade = new StringBuilder( 32 );
                  else
                     sb_szFinalGrade = new StringBuilder( szFinalGrade );
                                     GetStringFromAttributeByContext( sb_szFinalGrade, mFAProf2, "DisplayEnrolled", "FinalGrade", "DegreeTrackGradePointValue", 20 );
                  szFinalGrade = sb_szFinalGrade.toString( );}
                  //:FinalGrade = StrToDecimal( szFinalGrade )
                  {
                   ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mFAProf );
                   FinalGrade = m_ZGLOBAL1_Operation.StrToDecimal( szFinalGrade );
                   // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                  }
                  //:IF FinalGrade > 0
                  if ( FinalGrade > 0 )
                  { 
                     //:dCredits = dCredits + mFAProf2.DisplayEnrolled.CreditHours 
                     {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                                           GetDecimalFromAttribute( md_dTempDecimal_1, mFAProf2, "DisplayEnrolled", "CreditHours" );
                     dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
                     dCredits = dCredits + dTempDecimal_1;
                  } 

                  //:END
               } 

               //:END
            } 

            RESULT = mFAProf2.cursor( "DisplayEnrolled" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:DropView( mFAProf2 )
         DropView( mFAProf2 );
         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dCredits, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dCredits, 0 );
         break ;
      //:                       
      //:/* end zDERIVED_GET */
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
//:dReadyToPackageDate( VIEW mFAProf BASED ON LOD mFAProf,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   VIEW mFAProf2  BASED ON LOD mFAProf
public int 
omFAProf_dReadyToPackageDate( View     mFAProf,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   zVIEW    mFAProf2 = new zVIEW( );
   //:STRING ( 8 ) szLatestDate
   String   szLatestDate = null;
   //:STRING ( 8 ) szCurrentDate
   String   szCurrentDate = null;
   //:STRING ( 1 ) szNotMetFlag
   String   szNotMetFlag = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// If all Requirements have been met, the latest date met of those Requirements.
         //:CreateViewFromView( mFAProf2, mFAProf )
         CreateViewFromView( mFAProf2, mFAProf );
         //:FOR EACH mFAProf2.FinAidProfileRequirement 
         RESULT = mFAProf2.cursor( "FinAidProfileRequirement" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mFAProf2.FinAidProfileRequirement.CurrentStatus = "M"
            if ( CompareAttributeToString( mFAProf2, "FinAidProfileRequirement", "CurrentStatus", "M" ) == 0 )
            { 
               //:szCurrentDate = mFAProf2.FinAidProfileRequirement.DateReceived
               {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
               StringBuilder sb_szCurrentDate;
               if ( szCurrentDate == null )
                  sb_szCurrentDate = new StringBuilder( 32 );
               else
                  sb_szCurrentDate = new StringBuilder( szCurrentDate );
                               GetVariableFromAttribute( sb_szCurrentDate, mi_lTempInteger_0, 'S', 9, mFAProf2, "FinAidProfileRequirement", "DateReceived", "", 0 );
               lTempInteger_0 = mi_lTempInteger_0.intValue( );
               szCurrentDate = sb_szCurrentDate.toString( );}
               //:IF szLatestDate = "" OR szCurrentDate > szLatestDate
               if ( ZeidonStringCompare( szLatestDate, 1, 0, "", 1, 0, 9 ) == 0 || ZeidonStringCompare( szCurrentDate, 1, 0, szLatestDate, 1, 0, 9 ) < 0 )
               { 
                  //:szLatestDate = mFAProf2.FinAidProfileRequirement.DateReceived
                  {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                  StringBuilder sb_szLatestDate;
                  if ( szLatestDate == null )
                     sb_szLatestDate = new StringBuilder( 32 );
                  else
                     sb_szLatestDate = new StringBuilder( szLatestDate );
                                     GetVariableFromAttribute( sb_szLatestDate, mi_lTempInteger_1, 'S', 9, mFAProf2, "FinAidProfileRequirement", "DateReceived", "", 0 );
                  lTempInteger_1 = mi_lTempInteger_1.intValue( );
                  szLatestDate = sb_szLatestDate.toString( );}
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// Set the flag that a Requirement has not been met, unless this Requirement has been marked as:
               //:// 1. Not Required
               //:// 2. Waived
               //:// 3. Identified as "Excluded From Packaging"
               //:IF mFAProf2.FinAidProfileRequirementReq.ExcludeFromPackaging != "Y" AND 
               //:   mFAProf2.FinAidProfileRequirement.CurrentStatus           != "N" AND 
               //:   mFAProf2.FinAidProfileRequirement.CurrentStatus           != "W"
               if ( CompareAttributeToString( mFAProf2, "FinAidProfileRequirementReq", "ExcludeFromPackaging", "Y" ) != 0 && CompareAttributeToString( mFAProf2, "FinAidProfileRequirement", "CurrentStatus", "N" ) != 0 &&
                    CompareAttributeToString( mFAProf2, "FinAidProfileRequirement", "CurrentStatus", "W" ) != 0 )
               { 

                  //:szNotMetFlag = "Y"   // Indicate the Requirement is yet to be met.
                   {StringBuilder sb_szNotMetFlag;
                  if ( szNotMetFlag == null )
                     sb_szNotMetFlag = new StringBuilder( 32 );
                  else
                     sb_szNotMetFlag = new StringBuilder( szNotMetFlag );
                                    ZeidonStringCopy( sb_szNotMetFlag, 1, 0, "Y", 1, 0, 2 );
                  szNotMetFlag = sb_szNotMetFlag.toString( );}
               } 

               //:END
            } 

            RESULT = mFAProf2.cursor( "FinAidProfileRequirement" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:DropView( mFAProf2 )
         DropView( mFAProf2 );

         //:// Date is null if all Requirements weren't met.
         //:IF szNotMetFlag = "Y"
         if ( ZeidonStringCompare( szNotMetFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
         { 
            //:szLatestDate = ""
             {StringBuilder sb_szLatestDate;
            if ( szLatestDate == null )
               sb_szLatestDate = new StringBuilder( 32 );
            else
               sb_szLatestDate = new StringBuilder( szLatestDate );
                        ZeidonStringCopy( sb_szLatestDate, 1, 0, "", 1, 0, 9 );
            szLatestDate = sb_szLatestDate.toString( );}
         } 

         //:END

         //:StoreStringInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, szLatestDate )
         StoreStringInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, szLatestDate );
         break ;
      //:                       
      //:/* end zDERIVED_GET */
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
//:dTotalInstitCosts( VIEW mFAProf BASED ON LOD mFAProf,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )
//:   VIEW mFAProf2 BASED ON LOD mFAProf
public int 
omFAProf_dTotalInstitCosts( View     mFAProf,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   zVIEW    mFAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;
   double  dTempDecimal_4 = 0.0;
   double  dTempDecimal_5 = 0.0;
   double  dTempDecimal_6 = 0.0;
   double  dTempDecimal_7 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Institutional Costs are the same as COA costs, except that we only add up COA Items
         //:// that are flagged as Institutional Costs.
         //:// Thus, the basic structure of the rule is the same as for dTotalCOA.
         //:CreateViewFromView( mFAProf2, mFAProf )
         CreateViewFromView( mFAProf2, mFAProf );
         //:dAmount = 0 
         dAmount = 0;

         //:IF mFAProf.FinAidProfile.COA_OptionWholeYearFlag = "Y"
         if ( CompareAttributeToString( mFAProf, "FinAidProfile", "COA_OptionWholeYearFlag", "Y" ) == 0 )
         { 
            //:// This is Whole Year only.
            //:FOR EACH mFAProf2.FinAidCOAItemAssigned
            RESULT = mFAProf2.cursor( "FinAidCOAItemAssigned" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF mFAProf2.FinAidCOAItem.InstitutionalCostFlag = "Y"
               if ( CompareAttributeToString( mFAProf2, "FinAidCOAItem", "InstitutionalCostFlag", "Y" ) == 0 )
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

                  //:END
               } 

               RESULT = mFAProf2.cursor( "FinAidCOAItemAssigned" ).setNextContinue().toInt();;
               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:// Add other values together.
            //:FOR EACH mFAProf2.FinAidCOAItemAssigned
            RESULT = mFAProf2.cursor( "FinAidCOAItemAssigned" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF mFAProf2.FinAidCOAItem.InstitutionalCostFlag = "Y"
               if ( CompareAttributeToString( mFAProf2, "FinAidCOAItem", "InstitutionalCostFlag", "Y" ) == 0 )
               { 

                  //:// 1st Term
                  //:IF mFAProf.FinAidProfile.COA_OptionFirstTermFlag = "Y"
                  if ( CompareAttributeToString( mFAProf, "FinAidProfile", "COA_OptionFirstTermFlag", "Y" ) == 0 )
                  { 
                     //:IF mFAProf2.FinAidCOAItemAssigned.FirstTermRevenueAmount != ""
                     if ( CompareAttributeToString( mFAProf2, "FinAidCOAItemAssigned", "FirstTermRevenueAmount", "" ) != 0 )
                     { 
                        //:// Add FirstTermRevenueAmount 
                        //:dAmount = dAmount + mFAProf2.FinAidCOAItemAssigned.FirstTermRevenueAmount 
                        {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_3, mFAProf2, "FinAidCOAItemAssigned", "FirstTermRevenueAmount" );
                        dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
                        dAmount = dAmount + dTempDecimal_3;
                        //:ELSE
                     } 
                     else
                     { 
                        //:// Add 50% of RevenueAmount
                        //:dAmount = dAmount + (mFAProf2.FinAidCOAItemAssigned.RevenueAmount / 2)
                        {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_4, mFAProf2, "FinAidCOAItemAssigned", "RevenueAmount" );
                        dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
                        dAmount = dAmount + ( dTempDecimal_4 / 2 );
                     } 

                     //:END
                  } 

                  //:END

                  //:// 2nd Term
                  //:IF mFAProf.FinAidProfile.COA_OptionSecondTermFlag = "Y"
                  if ( CompareAttributeToString( mFAProf, "FinAidProfile", "COA_OptionSecondTermFlag", "Y" ) == 0 )
                  { 
                     //:IF mFAProf2.FinAidCOAItemAssigned.SecondTermRevenueAmount != ""
                     if ( CompareAttributeToString( mFAProf2, "FinAidCOAItemAssigned", "SecondTermRevenueAmount", "" ) != 0 )
                     { 
                        //:// Add SecondTermRevenueAmount 
                        //:dAmount = dAmount + mFAProf2.FinAidCOAItemAssigned.SecondTermRevenueAmount 
                        {MutableDouble md_dTempDecimal_5 = new MutableDouble( dTempDecimal_5 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_5, mFAProf2, "FinAidCOAItemAssigned", "SecondTermRevenueAmount" );
                        dTempDecimal_5 = md_dTempDecimal_5.doubleValue( );}
                        dAmount = dAmount + dTempDecimal_5;
                        //:ELSE
                     } 
                     else
                     { 
                        //:// Add 50% of RevenueAmount
                        //:dAmount = dAmount + (mFAProf2.FinAidCOAItemAssigned.RevenueAmount / 2)
                        {MutableDouble md_dTempDecimal_6 = new MutableDouble( dTempDecimal_6 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_6, mFAProf2, "FinAidCOAItemAssigned", "RevenueAmount" );
                        dTempDecimal_6 = md_dTempDecimal_6.doubleValue( );}
                        dAmount = dAmount + ( dTempDecimal_6 / 2 );
                     } 

                     //:END
                  } 

                  //:END

                  //:// 3rd Term
                  //:IF mFAProf.FinAidProfile.COA_OptionThirdTermFlag = "Y"
                  if ( CompareAttributeToString( mFAProf, "FinAidProfile", "COA_OptionThirdTermFlag", "Y" ) == 0 )
                  { 
                     //:// Always add ThirdTermRevenueAmount 
                     //:dAmount = dAmount + mFAProf2.FinAidCOAItemAssigned.ThirdTermRevenueAmount 
                     {MutableDouble md_dTempDecimal_7 = new MutableDouble( dTempDecimal_7 );
                                           GetDecimalFromAttribute( md_dTempDecimal_7, mFAProf2, "FinAidCOAItemAssigned", "ThirdTermRevenueAmount" );
                     dTempDecimal_7 = md_dTempDecimal_7.doubleValue( );}
                     dAmount = dAmount + dTempDecimal_7;
                  } 

                  //:END
               } 

               RESULT = mFAProf2.cursor( "FinAidCOAItemAssigned" ).setNextContinue().toInt();;
               //:END
            } 

            //:   
            //:END
         } 

         //:END
         //:DropView( mFAProf2 ) 
         DropView( mFAProf2 );
         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dTotalPELL_COA( VIEW mFAProf BASED ON LOD mFAProf,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )
//:   VIEW mFAProf2 BASED ON LOD mFAProf
public int 
omFAProf_dTotalPELL_COA( View     mFAProf,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   zVIEW    mFAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// PELL COA are all Whole Year costs.
         //:CreateViewFromView( mFAProf2, mFAProf )
         CreateViewFromView( mFAProf2, mFAProf );
         //:dAmount = 0 
         dAmount = 0;
         //:FOR EACH mFAProf2.FinAidCOAItemAssigned 
         RESULT = mFAProf2.cursor( "FinAidCOAItemAssigned" ).setFirst().toInt();
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

            RESULT = mFAProf2.cursor( "FinAidCOAItemAssigned" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:DropView( mFAProf2 ) 
         DropView( mFAProf2 );
         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dFullPartTime( VIEW mFAProf BASED ON LOD mFAProf,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )
//:   VIEW lFAAdmin  BASED ON LOD lFAAdmin
public int 
omFAProf_dFullPartTime( View     mFAProf,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   zVIEW    lFAAdmin = new zVIEW( );
   //:VIEW lFAAdminT BASED ON LOD lFAAdmin
   zVIEW    lFAAdminT = new zVIEW( );
   //:VIEW mFAProf2  BASED ON LOD mFAProf
   zVIEW    mFAProf2 = new zVIEW( );
   //:DECIMAL dCredits 
   double  dCredits = 0.0;
   //:STRING (  1  ) szFP
   String   szFP = null;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// If there is no Term (adult studies model), the FullTime/PartTime flag will be null.


         //:IF mFAProf.CollegeTerm EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mFAProf, "CollegeTerm" );
         if ( lTempInteger_0 == 0 )
         { 
            //:// We're analyzing a Period with a Term (traditional model).
            //:GET VIEW  lFAAdmin  NAMED "lFAAdmin"
            RESULT = GetViewByName( lFAAdmin, "lFAAdmin", mFAProf, zLEVEL_TASK );
            //:IF  RESULT >= 0
            if ( RESULT >= 0 )
            { 
               //:CreateViewFromView( lFAAdminT, lFAAdmin )
               CreateViewFromView( lFAAdminT, lFAAdmin );
               //:SET CURSOR FIRST lFAAdminT.FinAidAdmin WHERE lFAAdminT.CollegeYear.ID = mFAProf.PerPeriodTermCollegeYear.ID 
               RESULT = lFAAdminT.cursor( "FinAidAdmin" ).setFirst().toInt();
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lFAAdminT, "CollegeYear", "ID", mFAProf, "PerPeriodTermCollegeYear", "ID" ) != 0 ) )
                  { 
                     RESULT = lFAAdminT.cursor( "FinAidAdmin" ).setNextContinue().toInt();;
                  } 

               } 

               //:SET CURSOR FIRST lFAAdminT.CollegeTerm WHERE lFAAdminT.CollegeTerm.Semester = mFAProf.CollegeTerm.Semester   
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, mFAProf, "CollegeTerm", "Semester" );
               szTempString_0 = sb_szTempString_0.toString( );}
               RESULT = lFAAdminT.cursor( "CollegeTerm" ).setFirst( "Semester", szTempString_0 ).toInt();
               //:dCredits = mFAProf.PerProfileFinAidAwardPeriod.dCreditsPerDisb 
               {MutableDouble md_dCredits = new MutableDouble( dCredits );
                               GetDecimalFromAttribute( md_dCredits, mFAProf, "PerProfileFinAidAwardPeriod", "dCreditsPerDisb" );
               dCredits = md_dCredits.doubleValue( );}
               //:IF dCredits >= lFAAdminT.CollegeTerm.FullTimeMinCredits
               if ( CompareAttributeToDecimal( lFAAdminT, "CollegeTerm", "FullTimeMinCredits", dCredits ) <= 0 )
               { 
                  //:szFP = "F"
                   {StringBuilder sb_szFP;
                  if ( szFP == null )
                     sb_szFP = new StringBuilder( 32 );
                  else
                     sb_szFP = new StringBuilder( szFP );
                                    ZeidonStringCopy( sb_szFP, 1, 0, "F", 1, 0, 2 );
                  szFP = sb_szFP.toString( );}
                  //:ELSE 
               } 
               else
               { 
                  //:IF dCredits > 0
                  if ( dCredits > 0 )
                  { 
                     //:szFP = "P"
                      {StringBuilder sb_szFP;
                     if ( szFP == null )
                        sb_szFP = new StringBuilder( 32 );
                     else
                        sb_szFP = new StringBuilder( szFP );
                                          ZeidonStringCopy( sb_szFP, 1, 0, "P", 1, 0, 2 );
                     szFP = sb_szFP.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:szFP = ""
                      {StringBuilder sb_szFP;
                     if ( szFP == null )
                        sb_szFP = new StringBuilder( 32 );
                     else
                        sb_szFP = new StringBuilder( szFP );
                                          ZeidonStringCopy( sb_szFP, 1, 0, "", 1, 0, 2 );
                     szFP = sb_szFP.toString( );}
                  } 

                  //:END
               } 

               //:END
               //:DropView( lFAAdminT )
               DropView( lFAAdminT );
               //:ELSE 
            } 
            else
            { 

               //:szFP = ""
                {StringBuilder sb_szFP;
               if ( szFP == null )
                  sb_szFP = new StringBuilder( 32 );
               else
                  sb_szFP = new StringBuilder( szFP );
                              ZeidonStringCopy( sb_szFP, 1, 0, "", 1, 0, 2 );
               szFP = sb_szFP.toString( );}
               //:CreateViewFromView( mFAProf2, mFAProf )
               CreateViewFromView( mFAProf2, mFAProf );
               //:dCredits = 0
               dCredits = 0;
               //:dCredits = mFAProf2.PerProfileFinAidAwardPeriod.dCreditsPerDisb 
               {MutableDouble md_dCredits = new MutableDouble( dCredits );
                               GetDecimalFromAttribute( md_dCredits, mFAProf2, "PerProfileFinAidAwardPeriod", "dCreditsPerDisb" );
               dCredits = md_dCredits.doubleValue( );}
               //:IF dCredits >=  mFAProf2.FinAidAdmin.dPeriodFullTimeMinCredits 
               //:      OR dCredits = 0 // assume they are not pre ot registered
               if ( CompareAttributeToDecimal( mFAProf2, "FinAidAdmin", "dPeriodFullTimeMinCredits", dCredits ) <= 0 || dCredits == 0 )
               { 
                  //:szFP = "F"
                   {StringBuilder sb_szFP;
                  if ( szFP == null )
                     sb_szFP = new StringBuilder( 32 );
                  else
                     sb_szFP = new StringBuilder( szFP );
                                    ZeidonStringCopy( sb_szFP, 1, 0, "F", 1, 0, 2 );
                  szFP = sb_szFP.toString( );}
                  //:ELSE 
               } 
               else
               { 
                  //:szFP = "P"
                   {StringBuilder sb_szFP;
                  if ( szFP == null )
                     sb_szFP = new StringBuilder( 32 );
                  else
                     sb_szFP = new StringBuilder( szFP );
                                    ZeidonStringCopy( sb_szFP, 1, 0, "P", 1, 0, 2 );
                  szFP = sb_szFP.toString( );}
               } 

               //:END
               //:DropView( mFAProf2 )
               DropView( mFAProf2 );
               //:StoreStringInRecord( mFAProf,InternalEntityStructure,
               //:                     InternalAttribStructure, szFP )
               StoreStringInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, szFP );
            } 

            //:      
            //:END

            //:ELSE
         } 
         else
         { 
            //:// We're analyzing a Period without a Term (adult studies model)
            //:// Below, we will simply get the min credits from the first Term for the Year.
            //:szFP = ""
             {StringBuilder sb_szFP;
            if ( szFP == null )
               sb_szFP = new StringBuilder( 32 );
            else
               sb_szFP = new StringBuilder( szFP );
                        ZeidonStringCopy( sb_szFP, 1, 0, "", 1, 0, 2 );
            szFP = sb_szFP.toString( );}
         } 

         //:END

         //:   

         //:StoreStringInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, szFP )
         StoreStringInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, szFP );
         break ;
      //:/* end zDERIVED_GET */
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
//:dPerProfileOLDisp( VIEW mFAProf BASED ON LOD mFAProf,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   STRING (  254  ) szString
public int 
omFAProf_dPerProfileOLDisp( View     mFAProf,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szString = null;
   //:STRING (  254  ) szShortString
   String   szShortString = null;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:szString = mFAProf.PerProfileFinAidAwardPeriod.PeriodDesignator + " " 
         {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetStringFromAttribute( sb_szString, mFAProf, "PerProfileFinAidAwardPeriod", "PeriodDesignator" );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
         szString = sb_szString.toString( );}
         //:GetStringFromAttributeByContext( szShortString, mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate", "", 10 )
         {StringBuilder sb_szShortString;
         if ( szShortString == null )
            sb_szShortString = new StringBuilder( 32 );
         else
            sb_szShortString = new StringBuilder( szShortString );
                   GetStringFromAttributeByContext( sb_szShortString, mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate", "", 10 );
         szShortString = sb_szShortString.toString( );}
         //:AppendSpacesToString( szShortString, 11 )
         omFAProf_AppendSpacesToString( szShortString, 11 );
         //:szString = szString + szShortString + " - "
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, szShortString, 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, " - ", 1, 0, 255 );
         szString = sb_szString.toString( );}
         //:GetStringFromAttributeByContext( szShortString, mFAProf, "PerProfileFinAidAwardPeriod", "EndDate", "", 10 )
         {StringBuilder sb_szShortString;
         if ( szShortString == null )
            sb_szShortString = new StringBuilder( 32 );
         else
            sb_szShortString = new StringBuilder( szShortString );
                   GetStringFromAttributeByContext( sb_szShortString, mFAProf, "PerProfileFinAidAwardPeriod", "EndDate", "", 10 );
         szShortString = sb_szShortString.toString( );}
         //:AppendSpacesToString( szShortString, 11 )
         omFAProf_AppendSpacesToString( szShortString, 11 );
         //:szString = szString + szShortString + "  "
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, szShortString, 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, "  ", 1, 0, 255 );
         szString = sb_szString.toString( );}
         //:szShortString = mFAProf.PerProfileFinAidAwardPeriod.dCreditsPerDisbCohort  
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szShortString;
         if ( szShortString == null )
            sb_szShortString = new StringBuilder( 32 );
         else
            sb_szShortString = new StringBuilder( szShortString );
                   GetVariableFromAttribute( sb_szShortString, mi_lTempInteger_0, 'S', 255, mFAProf, "PerProfileFinAidAwardPeriod", "dCreditsPerDisbCohort", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szShortString = sb_szShortString.toString( );}
         //:szString = szString + "[" + szShortString + "]"
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, "[", 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, szShortString, 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, "]", 1, 0, 255 );
         szString = sb_szString.toString( );}
         //:StoreStringInRecord ( mFAProf,
         //:                      InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, szString );
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
//:dMonthsCovered( VIEW mFAProf BASED ON LOD mFAProf,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )
//:   VIEW mFAProf2 BASED ON LOD mFAProf
public int 
omFAProf_dMonthsCovered( View     mFAProf,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   zVIEW    mFAProf2 = new zVIEW( );
   //:STRING ( 18 ) szDate 
   String   szDate = null;
   //:INTEGER nDiff
   int      nDiff = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mFAProf.FinAidProfile.ProfileYearEnd = "" 
         if ( CompareAttributeToString( mFAProf, "FinAidProfile", "ProfileYearEnd", "" ) == 0 )
         { 
            //:CreateViewFromView( mFAProf2, mFAProf )
            CreateViewFromView( mFAProf2, mFAProf );
            //:OrderEntityForView( mFAProf2, "Enrolled", "Class.ClassEndDate D" )
            OrderEntityForView( mFAProf2, "Enrolled", "Class.ClassEndDate D" );
            //:SET CURSOR FIRST mFAProf2.Enrolled 
            RESULT = mFAProf2.cursor( "Enrolled" ).setFirst().toInt();
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:nDiff = 0
               nDiff = 0;
               //:ELSE
            } 
            else
            { 
               //:GetDateAttributeDifferenceInDays( nDiff, mFAProf, "FinAidProfile", "ProfileYearEnd",
               //:                                         mFAProf, "Class",         "ClassEndDate" )
               {
                ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mFAProf );
                {MutableInt mi_nDiff = new MutableInt( nDiff );
                               m_ZGLOBAL1_Operation.GetDateAttributeDifferenceInDays( mi_nDiff, mFAProf, "FinAidProfile", "ProfileYearEnd", mFAProf, "Class", "ClassEndDate" );
               nDiff = mi_nDiff.intValue( );}
                // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
               }
            } 

            //:END
            //:DropView( mFAProf2 )
            DropView( mFAProf2 );

            //:ELSE
         } 
         else
         { 
            //:GetDateAttributeDifferenceInDays( nDiff, mFAProf, "FinAidProfile", "ProfileYearEnd",
            //:                                         mFAProf, "FinAidProfile", "ProfileYearBegin" )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mFAProf );
             {MutableInt mi_nDiff = new MutableInt( nDiff );
                         m_ZGLOBAL1_Operation.GetDateAttributeDifferenceInDays( mi_nDiff, mFAProf, "FinAidProfile", "ProfileYearEnd", mFAProf, "FinAidProfile", "ProfileYearBegin" );
            nDiff = mi_nDiff.intValue( );}
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
         } 

         //:END
         //:nDiff = nDiff / 30
         nDiff = nDiff / 30;
         //:nDiff = nDiff + 1
         nDiff = nDiff + 1;


         //:StoreValueInRecord ( mFAProf,
         //:                      InternalEntityStructure, InternalAttribStructure, nDiff, 0)
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, nDiff, 0 );
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
//:dPdFullTimeMin( VIEW mFAProf BASED ON LOD mFAProf,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )
//:   DECIMAL dPerPeriod
public int 
omFAProf_dPdFullTimeMin( View     mFAProf,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   double  dPerPeriod = 0.0;
   //:INTEGER nPeriods
   int      nPeriods = 0;
   double  dTempDecimal_0 = 0.0;



   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:nPeriods = mFAProf.FinAidAdmin.DisbursementPeriodsPerYear
         {MutableInt mi_nPeriods = new MutableInt( nPeriods );
                   GetIntegerFromAttribute( mi_nPeriods, mFAProf, "FinAidAdmin", "DisbursementPeriodsPerYear" );
         nPeriods = mi_nPeriods.intValue( );}
         //:IF nPeriods = 0
         if ( nPeriods == 0 )
         { 
            //:nPeriods = 1
            nPeriods = 1;
         } 

         //:END
         //:dPerPeriod = mFAProf.FinAidAdmin.FullTimeMinCredits / nPeriods  
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "FinAidAdmin", "FullTimeMinCredits" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         dPerPeriod = dTempDecimal_0 / nPeriods;

         //:StoreValueInRecord ( mFAProf,
         //:                      InternalEntityStructure, InternalAttribStructure, dPerPeriod, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dPerPeriod, 0 );
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
//:dPdHalfTimeMin( VIEW mFAProf BASED ON LOD mFAProf,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   DECIMAL dPerPeriod
public int 
omFAProf_dPdHalfTimeMin( View     mFAProf,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   double  dPerPeriod = 0.0;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:dPerPeriod = mFAProf.FinAidAdmin.HalfTimeMinCredits / mFAProf.FinAidAdmin.DisbursementPeriodsPerYear 
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "FinAidAdmin", "HalfTimeMinCredits" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mFAProf, "FinAidAdmin", "DisbursementPeriodsPerYear" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         dPerPeriod = dTempDecimal_0 / lTempInteger_0;
         //:StoreValueInRecord ( mFAProf,
         //:                      InternalEntityStructure, InternalAttribStructure, dPerPeriod, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dPerPeriod, 0 );
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
//:dPdOneQuarterTimeMin( VIEW mFAProf BASED ON LOD mFAProf,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )


//:   DECIMAL dPerPeriod
public int 
omFAProf_dPdOneQuarterTimeMin( View     mFAProf,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   double  dPerPeriod = 0.0;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:dPerPeriod = mFAProf.FinAidAdmin.OneQuarterTimeMinCredits / mFAProf.FinAidAdmin.DisbursementPeriodsPerYear 
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "FinAidAdmin", "OneQuarterTimeMinCredits" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mFAProf, "FinAidAdmin", "DisbursementPeriodsPerYear" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         dPerPeriod = dTempDecimal_0 / lTempInteger_0;
         //:StoreValueInRecord ( mFAProf,
         //:                      InternalEntityStructure, InternalAttribStructure, dPerPeriod, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dPerPeriod, 0 );
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
//:dPdPartTimeMin( VIEW mFAProf BASED ON LOD mFAProf,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   DECIMAL dPerPeriod
public int 
omFAProf_dPdPartTimeMin( View     mFAProf,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   double  dPerPeriod = 0.0;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:dPerPeriod =  mFAProf.FinAidAdmin.PartTimeMinCredits  / mFAProf.FinAidAdmin.DisbursementPeriodsPerYear 
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "FinAidAdmin", "PartTimeMinCredits" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mFAProf, "FinAidAdmin", "DisbursementPeriodsPerYear" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         dPerPeriod = dTempDecimal_0 / lTempInteger_0;
         //:StoreValueInRecord ( mFAProf,
         //:                      InternalEntityStructure, InternalAttribStructure, dPerPeriod, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dPerPeriod, 0 );
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
//:dPdThreeQuarterTimeMin( VIEW mFAProf BASED ON LOD mFAProf,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   DECIMAL dPerPeriod
public int 
omFAProf_dPdThreeQuarterTimeMin( View     mFAProf,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   double  dPerPeriod = 0.0;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:dPerPeriod =  mFAProf.FinAidAdmin.ThreeQuarterTimeMinCredits / mFAProf.FinAidAdmin.DisbursementPeriodsPerYear 
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "FinAidAdmin", "ThreeQuarterTimeMinCredits" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mFAProf, "FinAidAdmin", "DisbursementPeriodsPerYear" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         dPerPeriod = dTempDecimal_0 / lTempInteger_0;
         //:StoreValueInRecord ( mFAProf,
         //:                      InternalEntityStructure, InternalAttribStructure, dPerPeriod, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dPerPeriod, 0 );
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


//:TRANSFORMATION OPERATION
//:CreateFinAidProfile( VIEW mFAProf BASED ON LOD mFAProf,
//:                     VIEW mPerson BASED ON LOD mPerson )
//:      
//:   VIEW mAdmDiv     REGISTERED AS mAdmDiv
public int 
omFAProf_CreateFinAidProfile( zVIEW    mFAProf,
                              View     mPerson )
{
   zVIEW    mAdmDiv = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lFAAdmin    BASED ON LOD  lFAAdmin
   zVIEW    lFAAdmin = new zVIEW( );
   //:VIEW mFAAdmin    BASED ON LOD  mFAAdmin
   zVIEW    mFAAdmin = new zVIEW( );
   //:VIEW mYearLST    BASED ON LOD  mYear
   zVIEW    mYearLST = new zVIEW( );
   //:STRING ( 18 ) szYear
   String   szYear = null;
   //:STRING ( 18 ) szTerm
   String   szTerm = null;
   //:STRING ( 500 ) szMsg
   String   szMsg = null;
   //:DECIMAL nTotalReg
   double  nTotalReg = 0.0;
   //:INTEGER nFound
   int      nFound = 0;
   //:SHORT   nRC
   int      nRC = 0;
   //:INTEGER nFirstProfile
   int      nFirstProfile = 0;
   //:INTEGER nAdminDivID
   int      nAdminDivID = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   String   szTempString_1 = null;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   int      lTempInteger_12 = 0;
   zVIEW    vTempViewVar_3 = new zVIEW( );
   int      lTempInteger_13 = 0;
   zVIEW    vTempViewVar_4 = new zVIEW( );
   int      lTempInteger_14 = 0;

   RESULT = GetViewByName( mAdmDiv, "mAdmDiv", mPerson, zLEVEL_TASK );

   //:nTotalReg = 0
   nTotalReg = 0;
   //:nFound = 0
   nFound = 0;
   //:szYear = ""
    {StringBuilder sb_szYear;
   if ( szYear == null )
      sb_szYear = new StringBuilder( 32 );
   else
      sb_szYear = new StringBuilder( szYear );
      ZeidonStringCopy( sb_szYear, 1, 0, "", 1, 0, 19 );
   szYear = sb_szYear.toString( );}
   //:nFirstProfile = 0 
   nFirstProfile = 0;
   //:nAdminDivID = 0 
   nAdminDivID = 0;

   //:// first see if we have a profile 
   //:IF mPerson.FinAidProfile  DOES NOT EXIST //first profile  
   lTempInteger_0 = CheckExistenceOfEntity( mPerson, "FinAidProfile" );
   if ( lTempInteger_0 != 0 )
   { 
      //:nFirstProfile = 1
      nFirstProfile = 1;
   } 

   //:END 
   //:// next get the correct admin div 
   //:IF mPerson.Student EXISTS 
   lTempInteger_1 = CheckExistenceOfEntity( mPerson, "Student" );
   if ( lTempInteger_1 == 0 )
   { 
      //:IF mPerson.StudentAdministrativeDivision EXISTS 
      lTempInteger_2 = CheckExistenceOfEntity( mPerson, "StudentAdministrativeDivision" );
      if ( lTempInteger_2 == 0 )
      { 
         //:nAdminDivID = mPerson.StudentAdministrativeDivision.ID   
         {MutableInt mi_nAdminDivID = new MutableInt( nAdminDivID );
                   GetIntegerFromAttribute( mi_nAdminDivID, mPerson, "StudentAdministrativeDivision", "ID" );
         nAdminDivID = mi_nAdminDivID.intValue( );}
      } 

      //:END 
   } 

   //:END 
   //:IF nAdminDivID = 0 // still zero 
   if ( nAdminDivID == 0 )
   { 
      //:IF mPerson.Prospect EXISTS
      lTempInteger_3 = CheckExistenceOfEntity( mPerson, "Prospect" );
      if ( lTempInteger_3 == 0 )
      { 
         //:IF mPerson.AdministrativeDivision EXISTS 
         lTempInteger_4 = CheckExistenceOfEntity( mPerson, "AdministrativeDivision" );
         if ( lTempInteger_4 == 0 )
         { 
            //:nAdminDivID = mPerson.AdministrativeDivision.ID   
            {MutableInt mi_nAdminDivID = new MutableInt( nAdminDivID );
                         GetIntegerFromAttribute( mi_nAdminDivID, mPerson, "AdministrativeDivision", "ID" );
            nAdminDivID = mi_nAdminDivID.intValue( );}
         } 

         //:END 
      } 

      //:END 
   } 

   //:END 
   //:IF nAdminDivID = 0 // still zero  
   if ( nAdminDivID == 0 )
   { 
      //:// have nothing to work with
      //:MessageSend( mPerson, "", "Create FinAid Profile",
      //:             "No Administrative Division tied to person in Create FinAidProfile.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mPerson, "", "Create FinAid Profile", "No Administrative Division tied to person in Create FinAidProfile.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1 // no admin div      
      if(8==8)return( -1 );
   } 

   //:END

   //:ACTIVATE lFAAdmin MULTIPLE WHERE lFAAdmin.AdministrativeDivision.ID = nAdminDivID    
   omFAProf_fnLocalBuildQual_0( mPerson, vTempViewVar_0, nAdminDivID );
   RESULT = ActivateObjectInstance( lFAAdmin, "lFAAdmin", mPerson, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:SET CURSOR FIRST lFAAdmin.FinAidAdmin WHERE lFAAdmin.FinAidAdmin.CurrentForFinancialAid = "Y"
   RESULT = lFAAdmin.cursor( "FinAidAdmin" ).setFirst( "CurrentForFinancialAid", "Y" ).toInt();
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:MessageSend( mPerson, "", "Create FinAid Profile",
      //:             "No profile will be generated because no Financial Aid Year is marked as current.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mPerson, "", "Create FinAid Profile", "No profile will be generated because no Financial Aid Year is marked as current.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:IF nFirstProfile = 1
   if ( nFirstProfile == 1 )
   { 
      //:IF mPerson.Prospect EXISTS 
      lTempInteger_5 = CheckExistenceOfEntity( mPerson, "Prospect" );
      if ( lTempInteger_5 == 0 )
      { 
         //:szYear = mPerson.Prospect.ExpectedEntryYear
         {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
         StringBuilder sb_szYear;
         if ( szYear == null )
            sb_szYear = new StringBuilder( 32 );
         else
            sb_szYear = new StringBuilder( szYear );
                   GetVariableFromAttribute( sb_szYear, mi_lTempInteger_6, 'S', 19, mPerson, "Prospect", "ExpectedEntryYear", "", 0 );
         lTempInteger_6 = mi_lTempInteger_6.intValue( );
         szYear = sb_szYear.toString( );}
         //:szTerm = mPerson.Prospect.ExpectedEntryTerm          
         {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
         StringBuilder sb_szTerm;
         if ( szTerm == null )
            sb_szTerm = new StringBuilder( 32 );
         else
            sb_szTerm = new StringBuilder( szTerm );
                   GetVariableFromAttribute( sb_szTerm, mi_lTempInteger_7, 'S', 19, mPerson, "Prospect", "ExpectedEntryTerm", "", 0 );
         lTempInteger_7 = mi_lTempInteger_7.intValue( );
         szTerm = sb_szTerm.toString( );}
         //:IF szYear = "" OR szTerm = ""
         if ( ZeidonStringCompare( szYear, 1, 0, "", 1, 0, 19 ) == 0 || ZeidonStringCompare( szTerm, 1, 0, "", 1, 0, 19 ) == 0 )
         { 
            //:szMsg = "No Fin Aid Profile will be created because either 'Expected Entry Year' or 'Term' has not been specified." + NEW_LINE +
            //:        "Do you want to create the Profile for the current year, " +
            //:        lFAAdmin.FinAidAdmin.dCollegeYear + "?"
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringCopy( sb_szMsg, 1, 0, "No Fin Aid Profile will be created because either 'Expected Entry Year' or 'Term' has not been specified.", 1, 0, 501 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, NEW_LINE, 1, 0, 501 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, "Do you want to create the Profile for the current year, ", 1, 0, 501 );
            szMsg = sb_szMsg.toString( );}
            {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_8, 'S', 255, lFAAdmin, "FinAidAdmin", "dCollegeYear", "", 0 );
            lTempInteger_8 = mi_lTempInteger_8.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_0, 1, 0, 501 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, "?", 1, 0, 501 );
            szMsg = sb_szMsg.toString( );}
            //:nRC = MessagePrompt( mPerson, "", "Create Fin Aid Profile", szMsg,
            //:      0, zBUTTONS_YESNO, zRESPONSE_NO, 0 )
            nRC = MessagePrompt( mPerson, "", "Create Fin Aid Profile", szMsg, 0, zBUTTONS_YESNO, zRESPONSE_NO, 0 );
            //:IF nRC = zRESPONSE_NO
            if ( nRC == zRESPONSE_NO )
            { 
               //:RETURN -1
               if(8==8)return( -1 );
               //:ELSE
            } 
            else
            { 
               //:// Use current Year.
               //:ACTIVATE mFAAdmin WHERE mFAAdmin.FinAidAdmin.ID = lFAAdmin.FinAidAdmin.ID
               {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
                               GetIntegerFromAttribute( mi_lTempInteger_9, lFAAdmin, "FinAidAdmin", "ID" );
               lTempInteger_9 = mi_lTempInteger_9.intValue( );}
               omFAProf_fnLocalBuildQual_1( mPerson, vTempViewVar_1, lTempInteger_9 );
               RESULT = ActivateObjectInstance( mFAAdmin, "mFAAdmin", mPerson, vTempViewVar_1, zSINGLE );
               DropView( vTempViewVar_1 );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:nRC = ActivateByEntryYear( mFAAdmin, lFAAdmin, szYear, szTerm )
            {
             //mFAAdmin_Object m_mFAAdmin_Object = new mFAAdmin_Object( lFAAdmin );
             //nRC = m_mFAAdmin_Object.omFAAdmin_ActivateByEntryYear( mFAAdmin, lFAAdmin, szYear, szTerm );
             // m_mFAAdmin_Object = null;  // permit gc  (unnecessary)
            	nRC = 0;
            }
            //:IF nRC < 0
            if ( nRC < 0 )
            { 
               //:szMsg = "There was no Fin Aid Profile Year match on the Prospect's entry year. " +
               //:        "Do you want to create the Profile for the current year, " +
               //:        lFAAdmin.FinAidAdmin.dCollegeYear + "?"
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringCopy( sb_szMsg, 1, 0, "There was no Fin Aid Profile Year match on the Prospect's entry year. ", 1, 0, 501 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, "Do you want to create the Profile for the current year, ", 1, 0, 501 );
               szMsg = sb_szMsg.toString( );}
               {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
               StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_10, 'S', 255, lFAAdmin, "FinAidAdmin", "dCollegeYear", "", 0 );
               lTempInteger_10 = mi_lTempInteger_10.intValue( );
               szTempString_1 = sb_szTempString_1.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_1, 1, 0, 501 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, "?", 1, 0, 501 );
               szMsg = sb_szMsg.toString( );}
               //:nRC = MessagePrompt( mPerson, "", "Create Fin Aid Profile", szMsg,
               //:      0, zBUTTONS_YESNO, zRESPONSE_NO, 0 )
               nRC = MessagePrompt( mPerson, "", "Create Fin Aid Profile", szMsg, 0, zBUTTONS_YESNO, zRESPONSE_NO, 0 );
               //:IF nRC = zRESPONSE_NO
               if ( nRC == zRESPONSE_NO )
               { 
                  //:RETURN -1
                  if(8==8)return( -1 );
                  //:ELSE
               } 
               else
               { 
                  //:// Use current Year.
                  //:ACTIVATE mFAAdmin WHERE mFAAdmin.FinAidAdmin.ID = lFAAdmin.FinAidAdmin.ID
                  {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
                                     GetIntegerFromAttribute( mi_lTempInteger_11, lFAAdmin, "FinAidAdmin", "ID" );
                  lTempInteger_11 = mi_lTempInteger_11.intValue( );}
                  omFAProf_fnLocalBuildQual_2( mPerson, vTempViewVar_2, lTempInteger_11 );
                  RESULT = ActivateObjectInstance( mFAAdmin, "mFAAdmin", mPerson, vTempViewVar_2, zSINGLE );
                  DropView( vTempViewVar_2 );
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
         //:// Use current Year.
         //:ACTIVATE mFAAdmin WHERE mFAAdmin.FinAidAdmin.ID = lFAAdmin.FinAidAdmin.ID
         {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
                   GetIntegerFromAttribute( mi_lTempInteger_12, lFAAdmin, "FinAidAdmin", "ID" );
         lTempInteger_12 = mi_lTempInteger_12.intValue( );}
         omFAProf_fnLocalBuildQual_3( mPerson, vTempViewVar_3, lTempInteger_12 );
         RESULT = ActivateObjectInstance( mFAAdmin, "mFAAdmin", mPerson, vTempViewVar_3, zSINGLE );
         DropView( vTempViewVar_3 );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// Use current Year.
      //:ACTIVATE mFAAdmin WHERE mFAAdmin.FinAidAdmin.ID = lFAAdmin.FinAidAdmin.ID
      {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
             GetIntegerFromAttribute( mi_lTempInteger_13, lFAAdmin, "FinAidAdmin", "ID" );
      lTempInteger_13 = mi_lTempInteger_13.intValue( );}
      omFAProf_fnLocalBuildQual_4( mPerson, vTempViewVar_4, lTempInteger_13 );
      RESULT = ActivateObjectInstance( mFAAdmin, "mFAAdmin", mPerson, vTempViewVar_4, zSINGLE );
      DropView( vTempViewVar_4 );
   } 

   //:END

   //:   
   //:// Make sure that mYearLST exists, as it is used later in the activate for mFASrc.
   //:GET VIEW mYearLST NAMED "mYearLST"
   RESULT = GetViewByName( mYearLST, "mYearLST", mPerson, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mYearLST RootOnlyMultiple
      RESULT = ActivateObjectInstance( mYearLST, "mYear", mPerson, 0, zACTIVATE_ROOTONLY_MULTIPLE );
      //:NAME VIEW mYearLST "mYearLST"
      SetNameForView( mYearLST, "mYearLST", null, zLEVEL_TASK );
   } 

   //:END
   //:SET CURSOR FIRST mYearLST.CollegeYear WHERE mYearLST.CollegeYear.ID = mFAAdmin.CollegeYear.ID
   {MutableInt mi_lTempInteger_14 = new MutableInt( lTempInteger_14 );
       GetIntegerFromAttribute( mi_lTempInteger_14, mFAAdmin, "CollegeYear", "ID" );
   lTempInteger_14 = mi_lTempInteger_14.intValue( );}
   RESULT = mYearLST.cursor( "CollegeYear" ).setFirst( "ID", lTempInteger_14 ).toInt();

   //:// User reusable code to actually create the Profile.
   //:CreateFAProfileReus( mFAProf, mPerson, mFAAdmin )
   omFAProf_CreateFAProfileReus( mFAProf, mPerson, mFAAdmin );
   //:DropObjectInstance( mFAAdmin )
   DropObjectInstance( mFAAdmin );
   //:DropObjectInstance( lFAAdmin )
   DropObjectInstance( lFAAdmin );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:CreateFAProfByFAAdmin( VIEW mFAProf  BASED ON LOD mFAProf,
//:                       VIEW mPerson  BASED ON LOD mPerson,
//:                       VIEW lFAAdmin BASED ON LOD lFAAdmin )

//:   VIEW mFAAdmin BASED ON LOD mFAAdmin
public int 
omFAProf_CreateFAProfByFAAdmin( zVIEW    mFAProf,
                                View     mPerson,
                                View     lFAAdmin )
{
   zVIEW    mFAAdmin = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mFAAdmin WHERE mFAAdmin.FinAidAdmin.ID = lFAAdmin.FinAidAdmin.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lFAAdmin, "FinAidAdmin", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omFAProf_fnLocalBuildQual_5( mPerson, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mFAAdmin, "mFAAdmin", mPerson, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:CreateFAProfileReus( mFAProf, mPerson, mFAAdmin )
   omFAProf_CreateFAProfileReus( mFAProf, mPerson, mFAAdmin );
   //:DropObjectInstance( mFAAdmin )
   DropObjectInstance( mFAAdmin );
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:CreateFAProfileReus( VIEW mFAProf  BASED ON LOD mFAProf,
//:                     VIEW mPerson  BASED ON LOD mPerson,
//:                     VIEW mFAAdmin BASED ON LOD mFAAdmin )
//:   VIEW wXferO      REGISTERED AS wXferO    
public int 
omFAProf_CreateFAProfileReus( zVIEW    mFAProf,
                              View     mPerson,
                              View     mFAAdmin )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mAdmDivLST  REGISTERED AS mAdmDivLST
   zVIEW    mAdmDivLST = new zVIEW( );
   //:VIEW mFAProfTemp BASED ON LOD  mFAProf
   zVIEW    mFAProfTemp = new zVIEW( );
   //:VIEW mFAStu      BASED ON LOD  mFAStu
   zVIEW    mFAStu = new zVIEW( );
   //:VIEW mStudenC    BASED ON LOD  mStudenC
   zVIEW    mStudenC = new zVIEW( );
   //:VIEW mProspct    BASED ON LOD  mProspct
   zVIEW    mProspct = new zVIEW( );
   //:VIEW mFAProfLAST BASED ON LOD  mFAProf
   zVIEW    mFAProfLAST = new zVIEW( );
   //:VIEW mFANdPro    BASED ON LOD  mFANdPro
   zVIEW    mFANdPro = new zVIEW( );
   //:VIEW mFARuleS    BASED ON LOD  mFARuleS
   zVIEW    mFARuleS = new zVIEW( );
   //:VIEW lTermLST    BASED ON LOD  lTermLST
   zVIEW    lTermLST = new zVIEW( );
   //:VIEW mStuSmal    BASED ON LOD  mStuSmal
   zVIEW    mStuSmal = new zVIEW( );
   //:STRING ( 127) szString
   String   szString = null;
   //:STRING ( 18 ) szDate
   String   szDate = null;
   //:STRING ( 18 ) szBeginDate
   String   szBeginDate = null;
   //:STRING ( 18 ) szEndDate
   String   szEndDate = null;
   //:STRING ( 18 ) szAdmittedDate
   String   szAdmittedDate = null;
   //:STRING ( 18 ) szYear
   String   szYear = null;
   //:STRING ( 18 ) szTerm
   String   szTerm = null;
   //:STRING ( 500 ) szMsg
   String   szMsg = null;
   //:DECIMAL nTotalReg
   double  nTotalReg = 0.0;
   //:INTEGER nFound
   int      nFound = 0;
   //:SHORT   nRC
   int      nRC = 0;
   //:INTEGER nDropmStudenC
   int      nDropmStudenC = 0;
   //:INTEGER nAdmitted
   int      nAdmitted = 0;
   //:INTEGER nFirstProfile
   int      nFirstProfile = 0;
   //:INTEGER nID
   int      nID = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   zVIEW    vTempViewVar_3 = new zVIEW( );
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   zVIEW    vTempViewVar_4 = new zVIEW( );
   int      lTempInteger_10 = 0;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   int      lTempInteger_13 = 0;
   double  dTempDecimal_1 = 0.0;

   RESULT = GetViewByName( wXferO, "wXferO", mPerson, zLEVEL_TASK );
   RESULT = GetViewByName( mAdmDivLST, "mAdmDivLST", mPerson, zLEVEL_TASK );

   //:// Reusable code to create a Fin aid Profile

   //:SET CURSOR FIRST mAdmDivLST.AdministrativeDivision WHERE mAdmDivLST.AdministrativeDivision.ID = mFAAdmin.AdministrativeDivision.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mFAAdmin, "AdministrativeDivision", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   RESULT = mAdmDivLST.cursor( "AdministrativeDivision" ).setFirst( "ID", lTempInteger_0 ).toInt();
   //:ACTIVATE mFANdPro MULTIPLE WHERE mFANdPro.AdministrativeDivision.ID = mFAAdmin.AdministrativeDivision.ID
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mFAAdmin, "AdministrativeDivision", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   omFAProf_fnLocalBuildQual_6( mPerson, vTempViewVar_0, lTempInteger_1 );
   RESULT = ActivateObjectInstance( mFANdPro, "mFANdPro", mPerson, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:SET CURSOR FIRST mPerson.FinAidProfile WHERE mPerson.CollegeYear.ID = mFAAdmin.CollegeYear.ID
   RESULT = mPerson.cursor( "FinAidProfile" ).setFirst().toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mPerson, "CollegeYear", "ID", mFAAdmin, "CollegeYear", "ID" ) != 0 ) )
      { 
         RESULT = mPerson.cursor( "FinAidProfile" ).setNextContinue().toInt();;
      } 

   } 

   //:IF RESULT >= zCURSOR_SET 
   if ( RESULT >= zCURSOR_SET )
   { 
      //:szString = "A Financial Aid Profile already exists for Year " + mFAAdmin.CollegeYear.Year 
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 11, mFAAdmin, "CollegeYear", "Year", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szString;
      if ( szString == null )
         sb_szString = new StringBuilder( 32 );
      else
         sb_szString = new StringBuilder( szString );
            ZeidonStringCopy( sb_szString, 1, 0, "A Financial Aid Profile already exists for Year ", 1, 0, 128 );
      szString = sb_szString.toString( );}
       {StringBuilder sb_szString;
      if ( szString == null )
         sb_szString = new StringBuilder( 32 );
      else
         sb_szString = new StringBuilder( szString );
            ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 128 );
      szString = sb_szString.toString( );}
      //:MessageSend( mPerson, "", "Create Profile", szString, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mPerson, "", "Create Profile", szString, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -4
      if(8==8)return( -4 );
   } 

   //:END
   //:ACTIVATE  mFAProf EMPTY
   RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", mPerson, zSINGLE );
   //:CREATE ENTITY mFAProf.FinAidProfile 
   RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
   //:OrderEntityForView( mPerson, "FinAidProfile", "CollegeYear.Year D" )
   OrderEntityForView( mPerson, "FinAidProfile", "CollegeYear.Year D" );
   //:SET CURSOR LAST mPerson.FinAidProfile
   RESULT = mPerson.cursor( "FinAidProfile" ).setLast().toInt();;
   //:IF RESULT >= zCURSOR_SET  
   if ( RESULT >= zCURSOR_SET )
   { 
      //:ACTIVATE  mFAProfLAST WHERE mFAProfLAST.FinAidProfile.ID = mPerson.FinAidProfile.ID 
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
             GetIntegerFromAttribute( mi_lTempInteger_3, mPerson, "FinAidProfile", "ID" );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );}
      omFAProf_fnLocalBuildQual_7( mPerson, vTempViewVar_1, lTempInteger_3 );
      RESULT = ActivateObjectInstance( mFAProfLAST, "mFAProf", mPerson, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:nAdmitted = 2
      nAdmitted = 2;
      //:ELSE 
   } 
   else
   { 
      //:mFAProfLAST = 0
      mFAProfLAST = null;
   } 

   //:END 
   //:INCLUDE mFAProf.Person         FROM mPerson.Person
   RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );
   //:INCLUDE mFAProf.FinAidAdmin    FROM mFAAdmin.FinAidAdmin 
   RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAdmin", mFAAdmin, "FinAidAdmin", zPOS_AFTER );
   //:/*COMMIT mFAProf   // Why???????
   //:nID = mFAProf.FinAidProfile.ID
   //:DropView( mFAProf )
   //:ACTIVATE mFAProf 
   //:   WHERE mFAProf.FinAidProfile.ID = nID */
   //:// if this is for a student, do the work on the profile object 
   //://   not the student object.
   //:IF mPerson.Prospect EXISTS 
   lTempInteger_4 = CheckExistenceOfEntity( mPerson, "Prospect" );
   if ( lTempInteger_4 == 0 )
   { 
      //:IF mPerson.Prospect.InitEntranceClassification = "FR"  
      if ( CompareAttributeToString( mPerson, "Prospect", "InitEntranceClassification", "FR" ) == 0 )
      { 
         //:mFAProf.FinAidProfile.AnticipatedClassification = "1"
         SetAttributeFromString( mFAProf, "FinAidProfile", "AnticipatedClassification", "1" );
         //:ELSE 
      } 
      else
      { 
         //:IF mPerson.Prospect.InitEntranceClassification = "SP" 
         if ( CompareAttributeToString( mPerson, "Prospect", "InitEntranceClassification", "SP" ) == 0 )
         { 
            //:mFAProf.FinAidProfile.AnticipatedClassification = "7"
            SetAttributeFromString( mFAProf, "FinAidProfile", "AnticipatedClassification", "7" );
         } 

         //:END
      } 

      //:END
   } 

   //:END
   //:ACTIVATE  mStuSmal 
   //:   WHERE mStuSmal.Person.ID = mPerson.Person.ID 
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
       GetIntegerFromAttribute( mi_lTempInteger_5, mPerson, "Person", "ID" );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );}
   omFAProf_fnLocalBuildQual_8( mPerson, vTempViewVar_2, lTempInteger_5 );
   RESULT = ActivateObjectInstance( mStuSmal, "mStuSmal", mPerson, vTempViewVar_2, zSINGLE );
   DropView( vTempViewVar_2 );
   //:IF RESULT >= 0 AND mStuSmal.Student EXISTS  
   lTempInteger_6 = CheckExistenceOfEntity( mStuSmal, "Student" );
   if ( RESULT >= 0 && lTempInteger_6 == 0 )
   { 
      //:IF mStuSmal.Student.CurrentLevel = "1" OR mStuSmal.Student.CurrentLevel = "8"
      if ( CompareAttributeToString( mStuSmal, "Student", "CurrentLevel", "1" ) == 0 || CompareAttributeToString( mStuSmal, "Student", "CurrentLevel", "8" ) == 0 )
      { 
         //:mFAProf.FinAidProfile.AnticipatedClassification = "2"
         SetAttributeFromString( mFAProf, "FinAidProfile", "AnticipatedClassification", "2" );
         //:ELSE    
      } 
      else
      { 
         //:IF mStuSmal.Student.CurrentLevel = "2" 
         if ( CompareAttributeToString( mStuSmal, "Student", "CurrentLevel", "2" ) == 0 )
         { 
            //:mFAProf.FinAidProfile.AnticipatedClassification = "3"
            SetAttributeFromString( mFAProf, "FinAidProfile", "AnticipatedClassification", "3" );
            //:ELSE 
         } 
         else
         { 
            //:IF mStuSmal.Student.CurrentLevel = "3" 
            if ( CompareAttributeToString( mStuSmal, "Student", "CurrentLevel", "3" ) == 0 )
            { 
               //:mFAProf.FinAidProfile.AnticipatedClassification = "4"
               SetAttributeFromString( mFAProf, "FinAidProfile", "AnticipatedClassification", "4" );
            } 

            //:END 
         } 

         //:END 
      } 

      //:END 
      //:IF mFAProf.FinAidProfile.AnticipatedClassification = ""
      if ( CompareAttributeToString( mFAProf, "FinAidProfile", "AnticipatedClassification", "" ) == 0 )
      { 
         //:mFAProf.FinAidProfile.AnticipatedClassification = "1"
         SetAttributeFromString( mFAProf, "FinAidProfile", "AnticipatedClassification", "1" );
      } 

      //:END

      //:ACTIVATE  mStudenC  
      //:   WHERE  mStudenC.Student.ID = mStuSmal.Student.ID 
      {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
             GetIntegerFromAttribute( mi_lTempInteger_7, mStuSmal, "Student", "ID" );
      lTempInteger_7 = mi_lTempInteger_7.intValue( );}
      omFAProf_fnLocalBuildQual_9( mPerson, vTempViewVar_3, lTempInteger_7 );
      RESULT = ActivateObjectInstance( mStudenC, "mStudenC", mPerson, vTempViewVar_3, zSINGLE );
      DropView( vTempViewVar_3 );
      //:SET CURSOR FIRST mStudenC.ScheduleCollegeYear  
      //:           WHERE mStudenC.ScheduleCollegeYear.ID = mFAAdmin.CollegeYear.ID //mYearLST.CollegeYear.ID
      {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
             GetIntegerFromAttribute( mi_lTempInteger_8, mFAAdmin, "CollegeYear", "ID" );
      lTempInteger_8 = mi_lTempInteger_8.intValue( );}
      RESULT = mStudenC.cursor( "ScheduleCollegeYear" ).setFirst( "ID", lTempInteger_8 ).toInt();
      //:IF RESULT < 0
      if ( RESULT < 0 )
      { 
         //:INCLUDE mStudenC.ScheduleCollegeYear FROM  mFAAdmin.CollegeYear // mYearLST.CollegeYear
         RESULT = IncludeSubobjectFromSubobject( mStudenC, "ScheduleCollegeYear", mFAAdmin, "CollegeYear", zPOS_AFTER );
      } 

      //:END
      //:GET VIEW lTermLST NAMED "lTermLST"
      RESULT = GetViewByName( lTermLST, "lTermLST", mPerson, zLEVEL_TASK );
      //:IF RESULT < 0
      if ( RESULT < 0 )
      { 
         //:ACTIVATE lTermLST MULTIPLE 
         //:   WHERE lTermLST.CollegeYear.ID = mFAAdmin.CollegeYear.ID // mYearLST.CollegeYear.ID
         {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
                   GetIntegerFromAttribute( mi_lTempInteger_9, mFAAdmin, "CollegeYear", "ID" );
         lTempInteger_9 = mi_lTempInteger_9.intValue( );}
         omFAProf_fnLocalBuildQual_10( mPerson, vTempViewVar_4, lTempInteger_9 );
         RESULT = ActivateObjectInstance( lTermLST, "lTermLST", mPerson, vTempViewVar_4, zMULTIPLE );
         DropView( vTempViewVar_4 );
         //:NAME VIEW lTermLST "lTermLST"
         SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
      } 

      //:END 
       //:nTotalReg = 0
      nTotalReg = 0;
      //:FOR EACH mStudenC.CurrentScheduleEntry 
      RESULT = mStudenC.cursor( "CurrentScheduleEntry" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mStudenC.CurrentClass EXISTS 
         lTempInteger_10 = CheckExistenceOfEntity( mStudenC, "CurrentClass" );
         if ( lTempInteger_10 == 0 )
         { 
            //:// get the largets value as it will be the 
            //:OrderEntityForView( mStudenC, "CurrentClass", "wStudentCreditHours  D" )
            OrderEntityForView( mStudenC, "CurrentClass", "wStudentCreditHours  D" );
            //:IF  mStudenC.CurrentClass.ID > 0 // the total lines do not have an ID so jut grab those.
            if ( CompareAttributeToInteger( mStudenC, "CurrentClass", "ID", 0 ) > 0 )
            { 
               //:ELSE 
            } 
            else
            { 
               //:nTotalReg = nTotalReg + mStudenC.CurrentClass.wStudentCreditHours 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mStudenC, "CurrentClass", "wStudentCreditHours" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               nTotalReg = nTotalReg + dTempDecimal_0;
            } 

            //:END   
         } 

         RESULT = mStudenC.cursor( "CurrentScheduleEntry" ).setNextContinue().toInt();;
         //:END 
      } 

      //:END
      //:DropView( mStudenC )
      DropView( mStudenC );
   } 

   //:END 

   //:IF nTotalReg = 0
   if ( nTotalReg == 0 )
   { 
      //:nTotalReg = mFAAdmin.FinAidAdmin.FullTimeMinCredits 
      {MutableDouble md_nTotalReg = new MutableDouble( nTotalReg );
             GetDecimalFromAttribute( md_nTotalReg, mFAAdmin, "FinAidAdmin", "FullTimeMinCredits" );
      nTotalReg = md_nTotalReg.doubleValue( );}
   } 

   //:END
   //:IF nTotalReg >= mFAAdmin.FinAidAdmin.FullTimeMinCredits 
   if ( CompareAttributeToDecimal( mFAAdmin, "FinAidAdmin", "FullTimeMinCredits", nTotalReg ) <= 0 )
   { 
      //:mFAProf.FinAidProfile.FullPartTime = "F"
      SetAttributeFromString( mFAProf, "FinAidProfile", "FullPartTime", "F" );
      //:ELSE 
   } 
   else
   { 
      //:mFAProf.FinAidProfile.FullPartTime = "P"
      SetAttributeFromString( mFAProf, "FinAidProfile", "FullPartTime", "P" );
   } 

   //:END
   //:mFAProf.FinAidProfile.ExpectedCreditLoad = nTotalReg
   SetAttributeFromDecimal( mFAProf, "FinAidProfile", "ExpectedCreditLoad", nTotalReg );
   //:   
   //:// set up the disb periods
   //:IF mFAAdmin.AdministrativeDivision.UsesCohortsFlag != "Y" 
   if ( CompareAttributeToString( mFAAdmin, "AdministrativeDivision", "UsesCohortsFlag", "Y" ) != 0 )
   { 
      //:// Doesn't use Cohorts.
      //:IF mFAAdmin.FinAidAdmin.FullTimeCalculated = "S" 
      //:      OR mFAAdmin.FinAidAdmin.FullTimeCalculated = "Q"
      if ( CompareAttributeToString( mFAAdmin, "FinAidAdmin", "FullTimeCalculated", "S" ) == 0 || CompareAttributeToString( mFAAdmin, "FinAidAdmin", "FullTimeCalculated", "Q" ) == 0 )
      { 
         //:FOR EACH mFAAdmin.FinAidAwardPeriod 
         RESULT = mFAAdmin.cursor( "FinAidAwardPeriod" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY mFAProf.PerProfileFinAidAwardPeriod 
            RESULT = CreateEntity( mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
            //:mFAProf.PerProfileFinAidAwardPeriod.PeriodDesignator = mFAAdmin.FinAidAwardPeriod.PeriodDesignator 
            SetAttributeFromAttribute( mFAProf, "PerProfileFinAidAwardPeriod", "PeriodDesignator", mFAAdmin, "FinAidAwardPeriod", "PeriodDesignator" );
            //:mFAProf.PerProfileFinAidAwardPeriod.BeginDate = mFAAdmin.FinAidAwardPeriod.BeginDate 
            SetAttributeFromAttribute( mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate", mFAAdmin, "FinAidAwardPeriod", "BeginDate" );
            //:mFAProf.PerProfileFinAidAwardPeriod.EndDate = mFAAdmin.FinAidAwardPeriod.EndDate 
            SetAttributeFromAttribute( mFAProf, "PerProfileFinAidAwardPeriod", "EndDate", mFAAdmin, "FinAidAwardPeriod", "EndDate" );
            //:FOR EACH mFAAdmin.DisbCollegeTerm
            RESULT = mFAAdmin.cursor( "DisbCollegeTerm" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:INCLUDE mFAProf.CollegeTerm FROM mFAAdmin.DisbCollegeTerm 
               RESULT = IncludeSubobjectFromSubobject( mFAProf, "CollegeTerm", mFAAdmin, "DisbCollegeTerm", zPOS_AFTER );
               RESULT = mFAAdmin.cursor( "DisbCollegeTerm" ).setNextContinue().toInt();;
            } 

            RESULT = mFAAdmin.cursor( "FinAidAwardPeriod" ).setNextContinue().toInt();;
            //:END 
         } 

         //:END
      } 

      //:END
      //:ELSE 
   } 
   else
   { 
      //:// Uses Cohorts
      //:FOR EACH mFAAdmin.FinAidAwardPeriod 
      RESULT = mFAAdmin.cursor( "FinAidAwardPeriod" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mFAProf.PerProfileFinAidAwardPeriod 
         RESULT = CreateEntity( mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
         //:mFAProf.PerProfileFinAidAwardPeriod.PeriodDesignator = mFAAdmin.FinAidAwardPeriod.PeriodDesignator 
         SetAttributeFromAttribute( mFAProf, "PerProfileFinAidAwardPeriod", "PeriodDesignator", mFAAdmin, "FinAidAwardPeriod", "PeriodDesignator" );
         RESULT = mFAAdmin.cursor( "FinAidAwardPeriod" ).setNextContinue().toInt();;
      } 

      //:END
   } 

   //:END
   //:mFAProf.FinAidProfile.DependencyStatus = "D"
   SetAttributeFromString( mFAProf, "FinAidProfile", "DependencyStatus", "D" );
   //:IF mFAProfLAST != 0
   if ( mFAProfLAST != null && mFAProfLAST.getView( ) != null )
   { 
      //:szDate = wXferO.Root.dCurrentDate 
      {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
      StringBuilder sb_szDate;
      if ( szDate == null )
         sb_szDate = new StringBuilder( 32 );
      else
         sb_szDate = new StringBuilder( szDate );
             GetVariableFromAttribute( sb_szDate, mi_lTempInteger_11, 'S', 19, wXferO, "Root", "dCurrentDate", "", 0 );
      lTempInteger_11 = mi_lTempInteger_11.intValue( );
      szDate = sb_szDate.toString( );}
      //:mFAProf.FinAidProfile.DependencyStatus = mFAProfLAST.FinAidProfile.DependencyStatus
      SetAttributeFromAttribute( mFAProf, "FinAidProfile", "DependencyStatus", mFAProfLAST, "FinAidProfile", "DependencyStatus" );
      //:mFAProf.FinAidProfile.ResidencyStatus  = mFAProfLAST.FinAidProfile.ResidencyStatus
      SetAttributeFromAttribute( mFAProf, "FinAidProfile", "ResidencyStatus", mFAProfLAST, "FinAidProfile", "ResidencyStatus" );
      //:mFAProf.FinAidProfile.MasterPromissorySignedDate = mFAProfLAST.FinAidProfile.MasterPromissorySignedDate
      SetAttributeFromAttribute( mFAProf, "FinAidProfile", "MasterPromissorySignedDate", mFAProfLAST, "FinAidProfile", "MasterPromissorySignedDate" );
      //:DropView( mFAProfLAST )
      DropView( mFAProfLAST );
   } 

   //:END 
   //:IF mFAProf.FinAidProfile.ResidencyStatus = ""
   if ( CompareAttributeToString( mFAProf, "FinAidProfile", "ResidencyStatus", "" ) == 0 )
   { 
      //:IF mFAProf.Prospect EXISTS 
      lTempInteger_12 = CheckExistenceOfEntity( mFAProf, "Prospect" );
      if ( lTempInteger_12 == 0 )
      { 
         //:IF mFAProf.Prospect.Resident != "" OR mFAProf.Prospect.Resident = "Y" 
         if ( CompareAttributeToString( mFAProf, "Prospect", "Resident", "" ) != 0 || CompareAttributeToString( mFAProf, "Prospect", "Resident", "Y" ) == 0 )
         { 
            //:mFAProf.FinAidProfile.ResidencyStatus = "R" 
            SetAttributeFromString( mFAProf, "FinAidProfile", "ResidencyStatus", "R" );
         } 

         //:END 
         //:ELSE 
      } 
      else
      { 
         //:mFAProf.FinAidProfile.ResidencyStatus = "R" 
         SetAttributeFromString( mFAProf, "FinAidProfile", "ResidencyStatus", "R" );
      } 

      //:END
   } 

   //:END

   //:// If this is the first FA Profile for the Person, add each Scholarship awarded by Admissions.
   //:// We determined if a Profile existed above and set varaible mFAProfLAST.
   //:IF mFAProfLAST = 0
   if ( mFAProfLAST == null || mFAProfLAST.getView( ) == null )
   { 
      //:FOR EACH mPerson.ScholarshipAward  
      RESULT = mPerson.cursor( "ScholarshipAward" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:AddScholarshipAward( mFAProf, mPerson.Scholarship.ID, mPerson.ScholarshipAward.Amount )
         {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
                   GetIntegerFromAttribute( mi_lTempInteger_13, mPerson, "Scholarship", "ID" );
         lTempInteger_13 = mi_lTempInteger_13.intValue( );}
         {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                   GetDecimalFromAttribute( md_dTempDecimal_1, mPerson, "ScholarshipAward", "Amount" );
         dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
         omFAProf_AddScholarshipAward( mFAProf, lTempInteger_13, dTempDecimal_1 );
         RESULT = mPerson.cursor( "ScholarshipAward" ).setNextContinue().toInt();;
      } 

      //:END
   } 

   //:END

   //:// Deleted by DonC on 3/16/2010
   //:/*FOR EACH mFANdPro.FinAidCOA
   //:   IF nFound = 0 
   //:      IF mFANdPro.QualifyingFinAidRuleSet EXISTS 
   //:         ACTIVATE mFARuleS 
   //:            WHERE mFARuleS.FinAidRuleSet.ID = mFANdPro.QualifyingFinAidRuleSet.ID 
   //:         IF RESULT >= 0 
   //:            nRC = QualifyRuleSet( mFARuleS, mFAProf, 0, mPerson )
   //:            IF  nRC > 0
   //:               // Include each selected Requirement into the target FinAid Track.
   //:               FOR EACH mFANdPro.FinAidCOAItem   
   //:                  CREATE ENTITY  mFAProf.FinAidCOAItemAssigned  
   //:                  INCLUDE mFAProf.FinAidCOAItem  FROM mFANdPro.FinAidCOAItem
   //:                  mFAProf.FinAidCOAItemAssigned.RevenueAmount = mFAProf.FinAidCOAItem.FixedRevenueAmount  
   //:               END 
   //:               nFound = 1
   //:               SET CURSOR LAST mFANdPro.FinAidCOA
   //:            END 
   //:         END 
   //:         DropView( mFARuleS ) 
   //:      END
   //:   END
   //:END*/

   //:// If a default Admission Track exists, include it here and initial Admissions Requirements.
   //:/*IF mFAProf.FinAidTrack DOES NOT EXIST  
   //:   SET CURSOR FIRST mAdmDivLST.FinAidTrack 
   //:      WHERE mAdmDivLST.FinAidTrack.DefaultFlag = "Y" 
   //:        AND mAdmDivLST.FinAidTrack.ActiveFlag  = "Y" 
   //:   IF RESULT >= zCURSOR_SET 
   //:      INCLUDE mFAProf.FinAidTrack    FROM mAdmDivLST.FinAidTrack 
   //:   END 
   //:END */

   //:NAME VIEW  mFAProf  "mFAProf" 
   SetNameForView( mFAProf, "mFAProf", null, zLEVEL_TASK );
   //:COMMIT mFAProf 
   RESULT = CommitObjectInstance( mFAProf );
   //:DropView( mFANdPro )
   DropView( mFANdPro );
   //:DropView( mStuSmal )
   DropView( mStuSmal );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:CreateReturningProfile( VIEW mCurrentFAProf BASED ON LOD mFAProf, INTEGER nAdminID )
//:   VIEW mFAProf   BASED ON LOD  mFAProf
public int 
omFAProf_CreateReturningProfile( View     mCurrentFAProf,
                                 int      nAdminID )
{
   zVIEW    mFAProf = new zVIEW( );
   //:VIEW wXferO    REGISTERED AS wXferO    
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mAdmDiv   REGISTERED AS mAdmDiv
   zVIEW    mAdmDiv = new zVIEW( );
   //:VIEW mFAStu    BASED ON LOD  mFAStu
   zVIEW    mFAStu = new zVIEW( );
   //:VIEW mStudenC  BASED ON LOD  mStudenC
   zVIEW    mStudenC = new zVIEW( );
   //:VIEW mPerson   BASED ON LOD  mPerson
   zVIEW    mPerson = new zVIEW( );
   //:VIEW mFAProfLAST BASED ON LOD mFAProf
   zVIEW    mFAProfLAST = new zVIEW( );
   //:VIEW mFAAdmin  BASED ON LOD  mFAAdmin
   zVIEW    mFAAdmin = new zVIEW( );
   //:VIEW mFANdPro  BASED ON LOD  mFANdPro
   zVIEW    mFANdPro = new zVIEW( );
   //:VIEW mFARuleS  BASED ON LOD  mFARuleS
   zVIEW    mFARuleS = new zVIEW( );
   //:VIEW lTermLST  BASED ON LOD  lTermLST
   zVIEW    lTermLST = new zVIEW( );
   //:STRING ( 127) szString
   String   szString = null;
   //:STRING ( 18 ) szDate
   String   szDate = null;
   //:STRING ( 18 ) szBeginDate
   String   szBeginDate = null;
   //:STRING ( 18 ) szEndDate
   String   szEndDate = null;
   //:STRING ( 18 ) szYear
   String   szYear = null;
   //:STRING ( 18 ) szTerm
   String   szTerm = null;
   //:DECIMAL nTotalReg
   double  nTotalReg = 0.0;
   //:DECIMAL TotalAwardAmount
   double  TotalAwardAmount = 0.0;
   //:INTEGER nFound
   int      nFound = 0;
   //:INTEGER nRC
   int      nRC = 0;
   //:INTEGER nID
   int      nID = 0;
   //:INTEGER nDropmStudenC
   int      nDropmStudenC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_3 = new zVIEW( );
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_4 = new zVIEW( );
   int      lTempInteger_4 = 0;
   zVIEW    vTempViewVar_5 = new zVIEW( );
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   zVIEW    vTempViewVar_6 = new zVIEW( );
   int      lTempInteger_7 = 0;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   double  dTempDecimal_1 = 0.0;
   int      lTempInteger_10 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mCurrentFAProf, zLEVEL_TASK );
   RESULT = GetViewByName( mAdmDiv, "mAdmDiv", mCurrentFAProf, zLEVEL_TASK );
   //: 
   //:nTotalReg = 0
   nTotalReg = 0;
   //:nFound = 0
   nFound = 0;
   //:szYear = ""
    {StringBuilder sb_szYear;
   if ( szYear == null )
      sb_szYear = new StringBuilder( 32 );
   else
      sb_szYear = new StringBuilder( szYear );
      ZeidonStringCopy( sb_szYear, 1, 0, "", 1, 0, 19 );
   szYear = sb_szYear.toString( );}

   //:ACTIVATE mFAAdmin  
   //:   WHERE mFAAdmin.FinAidAdmin.ID = nAdminID 
   omFAProf_fnLocalBuildQual_11( mCurrentFAProf, vTempViewVar_0, nAdminID );
   RESULT = ActivateObjectInstance( mFAAdmin, "mFAAdmin", mCurrentFAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT < 0 
   if ( RESULT < 0 )
   { 
      //:IssueError( mAdmDiv, 0,0, "Invalid Year ID" )
      IssueError( mAdmDiv, 0, 0, "Invalid Year ID" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END 

   //:ACTIVATE mPerson 
   //:   WHERE mPerson.Person.ID = mCurrentFAProf.Person.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mCurrentFAProf, "Person", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omFAProf_fnLocalBuildQual_12( mCurrentFAProf, vTempViewVar_1, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mPerson, "mPerson", mCurrentFAProf, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );

   //:ACTIVATE mFAStu
   //:   WHERE mFAStu.Student.ID = mCurrentFAProf.Student.ID 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mCurrentFAProf, "Student", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   omFAProf_fnLocalBuildQual_13( mCurrentFAProf, vTempViewVar_2, lTempInteger_1 );
   RESULT = ActivateObjectInstance( mFAStu, "mFAStu", mCurrentFAProf, vTempViewVar_2, zSINGLE );
   DropView( vTempViewVar_2 );

   //:ACTIVATE mFANdPro MULTIPLE 
   //:   WHERE mFANdPro.AdministrativeDivision.ID = mAdmDiv.AdministrativeDivision.ID 
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mAdmDiv, "AdministrativeDivision", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   omFAProf_fnLocalBuildQual_14( mCurrentFAProf, vTempViewVar_3, lTempInteger_2 );
   RESULT = ActivateObjectInstance( mFANdPro, "mFANdPro", mCurrentFAProf, vTempViewVar_3, zMULTIPLE );
   DropView( vTempViewVar_3 );

   //:SET CURSOR FIRST mPerson.FinAidProfile 
   //:   WHERE mPerson.CollegeYear.ID = mFAAdmin.CollegeYear.ID 
   RESULT = mPerson.cursor( "FinAidProfile" ).setFirst().toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mPerson, "CollegeYear", "ID", mFAAdmin, "CollegeYear", "ID" ) != 0 ) )
      { 
         RESULT = mPerson.cursor( "FinAidProfile" ).setNextContinue().toInt();;
      } 

   } 


   //:IF RESULT >= zCURSOR_SET 
   if ( RESULT >= zCURSOR_SET )
   { 
      //:ACTIVATE mFAProf 
      //:   WHERE mFAProf.FinAidProfile.ID = mPerson.FinAidProfile.ID 
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
             GetIntegerFromAttribute( mi_lTempInteger_3, mPerson, "FinAidProfile", "ID" );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );}
      omFAProf_fnLocalBuildQual_15( mCurrentFAProf, vTempViewVar_4, lTempInteger_3 );
      RESULT = ActivateObjectInstance( mFAProf, "mFAProf", mCurrentFAProf, vTempViewVar_4, zSINGLE );
      DropView( vTempViewVar_4 );
      //:ELSE 
   } 
   else
   { 
      //:ACTIVATE mFAProf EMPTY
      RESULT = ActivateEmptyObjectInstance( mFAProf, "mFAProf", mCurrentFAProf, zSINGLE );
      //:CREATE ENTITY mFAProf.FinAidProfile 
      RESULT = CreateEntity( mFAProf, "FinAidProfile", zPOS_AFTER );
      //://n IF mPerson.CurrentFinAidProfile EXISTS 
      //://n    EXCLUDE mPerson.CurrentFinAidProfile   
      //://n END
      //://n IF mFAProf.CurrentPerson EXISTS 
      //://n    EXCLUDE mFAProf.CurrentPerson  
      //://n END

      //:INCLUDE mFAProf.Person         FROM mPerson.Person
      RESULT = IncludeSubobjectFromSubobject( mFAProf, "Person", mPerson, "Person", zPOS_AFTER );
      //://n INCLUDE mFAProf.CurrentPerson  FROM mPerson.Person
      //:INCLUDE mFAProf.FinAidAdmin    FROM mFAAdmin.FinAidAdmin 
      RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAdmin", mFAAdmin, "FinAidAdmin", zPOS_AFTER );
   } 


   //:END

   //:// The following 4 lines were delete by DonC on 3/11/11 because they often created an erroneous FinAidProfile without Periods and
   //:// the code did not seem to be necessary.
   //://COMMIT mFAProf
   //://nID = mFAProf.FinAidProfile.ID
   //://DropView( mFAProf )
   //://ACTIVATE  mFAProf WHERE mFAProf.FinAidProfile.ID = nID 

   //:IF mFAStu.Student.CurrentLevel = "1" OR mFAStu.Student.CurrentLevel = "8"
   if ( CompareAttributeToString( mFAStu, "Student", "CurrentLevel", "1" ) == 0 || CompareAttributeToString( mFAStu, "Student", "CurrentLevel", "8" ) == 0 )
   { 
      //:mFAProf.FinAidProfile.AnticipatedClassification = "2"
      SetAttributeFromString( mFAProf, "FinAidProfile", "AnticipatedClassification", "2" );
      //:ELSE    
   } 
   else
   { 
      //:IF mFAStu.Student.CurrentLevel = "2" 
      if ( CompareAttributeToString( mFAStu, "Student", "CurrentLevel", "2" ) == 0 )
      { 
         //:mFAProf.FinAidProfile.AnticipatedClassification = "3"
         SetAttributeFromString( mFAProf, "FinAidProfile", "AnticipatedClassification", "3" );
         //:ELSE 
      } 
      else
      { 
         //:IF mFAStu.Student.CurrentLevel = "3" 
         if ( CompareAttributeToString( mFAStu, "Student", "CurrentLevel", "3" ) == 0 )
         { 
            //:mFAProf.FinAidProfile.AnticipatedClassification = "4"
            SetAttributeFromString( mFAProf, "FinAidProfile", "AnticipatedClassification", "4" );
            //:ELSE 
         } 
         else
         { 
            //:mFAProf.FinAidProfile.AnticipatedClassification = mFAStu.Student.CurrentLevel 
            SetAttributeFromAttribute( mFAProf, "FinAidProfile", "AnticipatedClassification", mFAStu, "Student", "CurrentLevel" );
         } 

         //:END 
      } 

      //:END 
   } 

   //:END 

   //:// if this is for a student, do the work on the profile object 
   //://   not the student object.
   //:ACTIVATE  mStudenC  
   //:   WHERE  mStudenC.Student.ID = mPerson.Student.ID 
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
       GetIntegerFromAttribute( mi_lTempInteger_4, mPerson, "Student", "ID" );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );}
   omFAProf_fnLocalBuildQual_16( mCurrentFAProf, vTempViewVar_5, lTempInteger_4 );
   RESULT = ActivateObjectInstance( mStudenC, "mStudenC", mCurrentFAProf, vTempViewVar_5, zSINGLE );
   DropView( vTempViewVar_5 );
   //:SET CURSOR FIRST mStudenC.ScheduleCollegeYear 
   //:   WHERE mStudenC.ScheduleCollegeYear.ID = mFAAdmin.CollegeYear.ID 
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
       GetIntegerFromAttribute( mi_lTempInteger_5, mFAAdmin, "CollegeYear", "ID" );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );}
   RESULT = mStudenC.cursor( "ScheduleCollegeYear" ).setFirst( "ID", lTempInteger_5 ).toInt();
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:INCLUDE mStudenC.ScheduleCollegeYear FROM  mFAAdmin.CollegeYear 
      RESULT = IncludeSubobjectFromSubobject( mStudenC, "ScheduleCollegeYear", mFAAdmin, "CollegeYear", zPOS_AFTER );
   } 

   //:END

   //:GET VIEW lTermLST NAMED "lTermLST"
   RESULT = GetViewByName( lTermLST, "lTermLST", mCurrentFAProf, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE lTermLST MULTIPLE 
      //:   WHERE lTermLST.CollegeYear.ID = mFAAdmin.CollegeYear.ID // mYearLST.CollegeYear.ID
      {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
             GetIntegerFromAttribute( mi_lTempInteger_6, mFAAdmin, "CollegeYear", "ID" );
      lTempInteger_6 = mi_lTempInteger_6.intValue( );}
      omFAProf_fnLocalBuildQual_17( mCurrentFAProf, vTempViewVar_6, lTempInteger_6 );
      RESULT = ActivateObjectInstance( lTermLST, "lTermLST", mCurrentFAProf, vTempViewVar_6, zMULTIPLE );
      DropView( vTempViewVar_6 );
      //:NAME VIEW lTermLST "lTermLST"
      SetNameForView( lTermLST, "lTermLST", null, zLEVEL_TASK );
   } 

   //:END 
   //:      
   //:BuildCurrentSchedule( mStudenC )

   //:nTotalReg = 0
   nTotalReg = 0;
   //:FOR EACH mStudenC.CurrentScheduleEntry 
   RESULT = mStudenC.cursor( "CurrentScheduleEntry" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mStudenC.CurrentClass EXISTS 
      lTempInteger_7 = CheckExistenceOfEntity( mStudenC, "CurrentClass" );
      if ( lTempInteger_7 == 0 )
      { 
         //:// get the largets value as it will be the 
         //:OrderEntityForView( mStudenC, "CurrentClass", "wStudentCreditHours  D" )
         OrderEntityForView( mStudenC, "CurrentClass", "wStudentCreditHours  D" );
         //:IF  mStudenC.CurrentClass.ID > 0 // the total lines do not have an ID so jut grab those.
         if ( CompareAttributeToInteger( mStudenC, "CurrentClass", "ID", 0 ) > 0 )
         { 
            //:ELSE 
         } 
         else
         { 
            //:nTotalReg = nTotalReg + mStudenC.CurrentClass.wStudentCreditHours 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mStudenC, "CurrentClass", "wStudentCreditHours" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            nTotalReg = nTotalReg + dTempDecimal_0;
         } 

         //:END
      } 

      RESULT = mStudenC.cursor( "CurrentScheduleEntry" ).setNextContinue().toInt();;
      //:END 
   } 

   //:END
   //:DropView( mStudenC )
   DropView( mStudenC );
   //:IF nTotalReg = 0
   if ( nTotalReg == 0 )
   { 
      //:nTotalReg = mFAAdmin.FinAidAdmin.FullTimeMinCredits 
      {MutableDouble md_nTotalReg = new MutableDouble( nTotalReg );
             GetDecimalFromAttribute( md_nTotalReg, mFAAdmin, "FinAidAdmin", "FullTimeMinCredits" );
      nTotalReg = md_nTotalReg.doubleValue( );}
   } 

   //:END
   //:IF nTotalReg >= mFAAdmin.FinAidAdmin.FullTimeMinCredits 
   if ( CompareAttributeToDecimal( mFAAdmin, "FinAidAdmin", "FullTimeMinCredits", nTotalReg ) <= 0 )
   { 
      //:mFAProf.FinAidProfile.FullPartTime = "F"
      SetAttributeFromString( mFAProf, "FinAidProfile", "FullPartTime", "F" );
      //:ELSE 
   } 
   else
   { 
      //:mFAProf.FinAidProfile.FullPartTime = "P"
      SetAttributeFromString( mFAProf, "FinAidProfile", "FullPartTime", "P" );
   } 

   //:END
   //:mFAProf.FinAidProfile.ExpectedCreditLoad = nTotalReg
   SetAttributeFromDecimal( mFAProf, "FinAidProfile", "ExpectedCreditLoad", nTotalReg );


   //:IF mFAAdmin.AdministrativeDivision.UsesCohortsFlag != "Y" 
   if ( CompareAttributeToString( mFAAdmin, "AdministrativeDivision", "UsesCohortsFlag", "Y" ) != 0 )
   { 
      //:IF mFAAdmin.FinAidAdmin.FullTimeCalculated = "S" 
      //:      OR mFAAdmin.FinAidAdmin.FullTimeCalculated = "Q"
      if ( CompareAttributeToString( mFAAdmin, "FinAidAdmin", "FullTimeCalculated", "S" ) == 0 || CompareAttributeToString( mFAAdmin, "FinAidAdmin", "FullTimeCalculated", "Q" ) == 0 )
      { 
         //:FOR EACH mFAAdmin.FinAidAwardPeriod 
         RESULT = mFAAdmin.cursor( "FinAidAwardPeriod" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY mFAProf.PerProfileFinAidAwardPeriod 
            RESULT = CreateEntity( mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
            //:mFAProf.PerProfileFinAidAwardPeriod.PeriodDesignator = mFAAdmin.FinAidAwardPeriod.PeriodDesignator 
            SetAttributeFromAttribute( mFAProf, "PerProfileFinAidAwardPeriod", "PeriodDesignator", mFAAdmin, "FinAidAwardPeriod", "PeriodDesignator" );
            //:mFAProf.PerProfileFinAidAwardPeriod.BeginDate = mFAAdmin.FinAidAwardPeriod.BeginDate 
            SetAttributeFromAttribute( mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate", mFAAdmin, "FinAidAwardPeriod", "BeginDate" );
            //:mFAProf.PerProfileFinAidAwardPeriod.EndDate = mFAAdmin.FinAidAwardPeriod.EndDate 
            SetAttributeFromAttribute( mFAProf, "PerProfileFinAidAwardPeriod", "EndDate", mFAAdmin, "FinAidAwardPeriod", "EndDate" );
            //:FOR EACH mFAAdmin.DisbCollegeTerm
            RESULT = mFAAdmin.cursor( "DisbCollegeTerm" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:INCLUDE mFAProf.CollegeTerm FROM mFAAdmin.DisbCollegeTerm 
               RESULT = IncludeSubobjectFromSubobject( mFAProf, "CollegeTerm", mFAAdmin, "DisbCollegeTerm", zPOS_AFTER );
               RESULT = mFAAdmin.cursor( "DisbCollegeTerm" ).setNextContinue().toInt();;
            } 

            RESULT = mFAAdmin.cursor( "FinAidAwardPeriod" ).setNextContinue().toInt();;
            //:END 
         } 

         //:END
      } 

      //:END
      //:ELSE 
   } 
   else
   { 
      //:FOR EACH mFAAdmin.FinAidAwardPeriod 
      RESULT = mFAAdmin.cursor( "FinAidAwardPeriod" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mFAProf.PerProfileFinAidAwardPeriod 
         RESULT = CreateEntity( mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
         //:mFAProf.PerProfileFinAidAwardPeriod.PeriodDesignator = mFAAdmin.FinAidAwardPeriod.PeriodDesignator 
         SetAttributeFromAttribute( mFAProf, "PerProfileFinAidAwardPeriod", "PeriodDesignator", mFAAdmin, "FinAidAwardPeriod", "PeriodDesignator" );
         RESULT = mFAAdmin.cursor( "FinAidAwardPeriod" ).setNextContinue().toInt();;
      } 

      //:END
   } 

   //:END

   //:   
   //:// set up the disb periods
   //:/*IF mFAAdmin.FinAidAdmin.FullTimeCalculated = "S" 
   //:      OR mFAAdmin.FinAidAdmin.FullTimeCalculated = "Q"
   //:   FOR EACH mFAAdmin.FinAidAwardPeriod 
   //:      SET CURSOR FIRST  mFAProf.PerProfileFinAidAwardPeriod 
   //:         WHERE mFAProf.PerProfileFinAidAwardPeriod.PeriodDesignator = mFAAdmin.FinAidAwardPeriod.PeriodDesignator 
   //:      IF RESULT < zCURSOR_SET  
   //:         SET CURSOR LAST mFAProf.PerProfileFinAidAwardPeriod 
   //:         CREATE ENTITY mFAProf.PerProfileFinAidAwardPeriod 
   //:         mFAProf.PerProfileFinAidAwardPeriod.PeriodDesignator = mFAAdmin.FinAidAwardPeriod.PeriodDesignator 
   //:         mFAProf.PerProfileFinAidAwardPeriod.BeginDate = mFAAdmin.FinAidAwardPeriod.BeginDate 
   //:         mFAProf.PerProfileFinAidAwardPeriod.EndDate = mFAAdmin.FinAidAwardPeriod.EndDate 
   //:         FOR EACH mFAAdmin.DisbCollegeTerm
   //:            INCLUDE mFAProf.CollegeTerm FROM mFAAdmin.DisbCollegeTerm 
   //:         END 
   //:         IF mAdmDiv.AdministrativeDivision.UsesCohortsFlag = "Y" 
   //:            szBeginDate = mFAProf.PerProfileFinAidAwardPeriod.BeginDate 
   //:            szEndDate = mFAProf.PerProfileFinAidAwardPeriod.EndDate 
   //:            OrderEntityForView( mFAProf, "Enrolled", "Class.ClassStartDate A" )
   //:            FOR EACH mFAProf.Enrolled
   //:               WHERE mFAProf.Class.ClassStartDate >= szBeginDate
   //:                 AND mFAProf.Class.ClassStartDate <= szEndDate
   //:               INCLUDE mFAProf.PeriodEnrolled  FROM mFAProf.Enrolled 
   //:            END
   //:         END
   //:      END
   //:   END
   //:END*/

   //:mFAProf.FinAidProfile.DependencyStatus = "D"
   SetAttributeFromString( mFAProf, "FinAidProfile", "DependencyStatus", "D" );
   //:szDate = wXferO.Root.dCurrentDate 
   {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
   StringBuilder sb_szDate;
   if ( szDate == null )
      sb_szDate = new StringBuilder( 32 );
   else
      sb_szDate = new StringBuilder( szDate );
       GetVariableFromAttribute( sb_szDate, mi_lTempInteger_8, 'S', 19, wXferO, "Root", "dCurrentDate", "", 0 );
   lTempInteger_8 = mi_lTempInteger_8.intValue( );
   szDate = sb_szDate.toString( );}
   //:mFAProf.FinAidProfile.DependencyStatus = mCurrentFAProf.FinAidProfile.DependencyStatus
   SetAttributeFromAttribute( mFAProf, "FinAidProfile", "DependencyStatus", mCurrentFAProf, "FinAidProfile", "DependencyStatus" );
   //:mFAProf.FinAidProfile.ResidencyStatus  = mCurrentFAProf.FinAidProfile.ResidencyStatus
   SetAttributeFromAttribute( mFAProf, "FinAidProfile", "ResidencyStatus", mCurrentFAProf, "FinAidProfile", "ResidencyStatus" );
   //:mFAProf.FinAidProfile.MasterPromissorySignedDate = mCurrentFAProf.FinAidProfile.MasterPromissorySignedDate
   SetAttributeFromAttribute( mFAProf, "FinAidProfile", "MasterPromissorySignedDate", mCurrentFAProf, "FinAidProfile", "MasterPromissorySignedDate" );

   //:IF mFAProf.FinAidProfile.ResidencyStatus = ""
   if ( CompareAttributeToString( mFAProf, "FinAidProfile", "ResidencyStatus", "" ) == 0 )
   { 
      //:mFAProf.FinAidProfile.ResidencyStatus = "R" 
      SetAttributeFromString( mFAProf, "FinAidProfile", "ResidencyStatus", "R" );
   } 

   //:END

   //:// Deleted by DonC on 3/16/2010
   //:/*IF mFAProf.FinAidCOAItemAssigned DOES NOT EXIST
   //:   FOR EACH mFANdPro.FinAidCOA
   //:      IF nFound = 0 
   //:         IF mFANdPro.QualifyingFinAidRuleSet EXISTS 
   //:            ACTIVATE mFARuleS 
   //:               WHERE mFARuleS.FinAidRuleSet.ID = mFANdPro.QualifyingFinAidRuleSet.ID 
   //:            IF RESULT >= 0 
   //:               nRC = QualifyRuleSet( mFARuleS, mFAProf, 0, mPerson )
   //:               IF  nRC > 0
   //:                  // Include each selected Requirement into the target FinAid Track.
   //:                  FOR EACH mFANdPro.FinAidCOAItem   
   //:                     CREATE ENTITY  mFAProf.FinAidCOAItemAssigned  
   //:                     INCLUDE mFAProf.FinAidCOAItem  FROM mFANdPro.FinAidCOAItem
   //:                     mFAProf.FinAidCOAItemAssigned.RevenueAmount = mFAProf.FinAidCOAItem.FixedRevenueAmount  
   //:                  END 
   //:                  nFound = 1
   //:                  SET CURSOR LAST mFANdPro.FinAidCOA
   //:               END 
   //:            END 
   //:            DropView( mFARuleS ) 
   //:         END
   //:      END
   //:   END
   //:END*/

   //:IF mFAProf.FinAidTrack DOES NOT EXIST 
   lTempInteger_9 = CheckExistenceOfEntity( mFAProf, "FinAidTrack" );
   if ( lTempInteger_9 != 0 )
   { 
      //:// If a default Admission Track exists, include it here and initial Admissions Requirements.
      //:SET CURSOR FIRST mAdmDiv.FinAidTrack 
      //:           WHERE mAdmDiv.FinAidTrack.DefaultFlag = "Y" 
      //:             AND mAdmDiv.FinAidTrack.ActiveFlag  = "Y" 
      RESULT = mAdmDiv.cursor( "FinAidTrack" ).setFirst().toInt();
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mAdmDiv, "FinAidTrack", "DefaultFlag", "Y" ) != 0 || CompareAttributeToString( mAdmDiv, "FinAidTrack", "ActiveFlag", "Y" ) != 0 ) )
         { 
            RESULT = mAdmDiv.cursor( "FinAidTrack" ).setNextContinue().toInt();;
         } 

      } 

      //:IF RESULT >= zCURSOR_SET 
      if ( RESULT >= zCURSOR_SET )
      { 
         //:INCLUDE mFAProf.FinAidTrack    FROM mAdmDiv.FinAidTrack 
         RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidTrack", mAdmDiv, "FinAidTrack", zPOS_AFTER );
      } 

      //:END 
   } 

   //:END
   //:// apply awards
   //:FOR EACH mCurrentFAProf.FinAidAward 
   RESULT = mCurrentFAProf.cursor( "FinAidAward" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:/*IF mCurrentFAProf.FinAidAward.Amount = ""
      //:   AddReturningAward( mFAProf, mCurrentFAProf.FinAidSource.ID, mCurrentFAProf.FinAidAward.AmountOffered )
      //:ELSE
      //:   AddReturningAward( mFAProf, mCurrentFAProf.FinAidSource.ID, mCurrentFAProf.FinAidAward.Amount )
      //:END*/

      //:// We will determine the award as the total of the original award disbursements.
      //:TotalAwardAmount = 0
      TotalAwardAmount = 0;
      //:FOR EACH mCurrentFAProf.FinAidAwardDisbursement 
      RESULT = mCurrentFAProf.cursor( "FinAidAwardDisbursement" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:TotalAwardAmount = TotalAwardAmount + mCurrentFAProf.FinAidAwardDisbursement.OriginalAmountExpected 
         {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                   GetDecimalFromAttribute( md_dTempDecimal_1, mCurrentFAProf, "FinAidAwardDisbursement", "OriginalAmountExpected" );
         dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
         TotalAwardAmount = TotalAwardAmount + dTempDecimal_1;
         RESULT = mCurrentFAProf.cursor( "FinAidAwardDisbursement" ).setNextContinue().toInt();;
      } 

      //:END

      //:AddReturningAward( mFAProf, mCurrentFAProf.FinAidSource.ID, TotalAwardAmount )
      {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
             GetIntegerFromAttribute( mi_lTempInteger_10, mCurrentFAProf, "FinAidSource", "ID" );
      lTempInteger_10 = mi_lTempInteger_10.intValue( );}
      omFAProf_AddReturningAward( mFAProf, lTempInteger_10, TotalAwardAmount );
      RESULT = mCurrentFAProf.cursor( "FinAidAward" ).setNextContinue().toInt();;
   } 

   //:END

   //:NAME VIEW  mFAProf  "mReturningFAProf"  
   SetNameForView( mFAProf, "mReturningFAProf", null, zLEVEL_TASK );
   //:DropView( mFAAdmin )
   DropView( mFAAdmin );
   //:DropView( mFANdPro )
   DropView( mFANdPro );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dTotalUnmetNeed( VIEW mFAProf BASED ON LOD mFAProf,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure, 
//:                 SHORT GetOrSetFlag )
//:   DECIMAL dAmount 
public int 
omFAProf_dTotalUnmetNeed( View     mFAProf,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   double  dAmount = 0.0;
   //:DECIMAL dAmountAward 
   double  dAmountAward = 0.0;
   //:DECIMAL dAmountOfNeed
   double  dAmountOfNeed = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:dAmountOfNeed = mFAProf.FinAidProfile.dTotalNeed
         {MutableDouble md_dAmountOfNeed = new MutableDouble( dAmountOfNeed );
                   GetDecimalFromAttribute( md_dAmountOfNeed, mFAProf, "FinAidProfile", "dTotalNeed" );
         dAmountOfNeed = md_dAmountOfNeed.doubleValue( );}
         //:dAmountAward  = mFAProf.FinAidProfile.dTotalAward 
         {MutableDouble md_dAmountAward = new MutableDouble( dAmountAward );
                   GetDecimalFromAttribute( md_dAmountAward, mFAProf, "FinAidProfile", "dTotalAward" );
         dAmountAward = md_dAmountAward.doubleValue( );}
         //:dAmount = dAmountOfNeed - dAmountAward
         dAmount = dAmountOfNeed - dAmountAward;
         //:IF  dAmount < 0 
         if ( dAmount < 0 )
         { 
            //:dAmount = 0 
            dAmount = 0;
         } 

         //:END
         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dTotalUnmetNeedCalc( VIEW mFAProf BASED ON LOD mFAProf,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure, 
//:                     SHORT GetOrSetFlag )
//:   DECIMAL dAmount 
public int 
omFAProf_dTotalUnmetNeedCalc( View     mFAProf,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   double  dAmount = 0.0;
   //:DECIMAL dAmountAward 
   double  dAmountAward = 0.0;
   //:DECIMAL dAmountOfNeed
   double  dAmountOfNeed = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Same as dTotalUnmetNeed, except it doesn't have the check to make negative amounts zero.
         //:dAmountOfNeed = mFAProf.FinAidProfile.dTotalNeed
         {MutableDouble md_dAmountOfNeed = new MutableDouble( dAmountOfNeed );
                   GetDecimalFromAttribute( md_dAmountOfNeed, mFAProf, "FinAidProfile", "dTotalNeed" );
         dAmountOfNeed = md_dAmountOfNeed.doubleValue( );}
         //:dAmountAward  = mFAProf.FinAidProfile.dTotalAward 
         {MutableDouble md_dAmountAward = new MutableDouble( dAmountAward );
                   GetDecimalFromAttribute( md_dAmountAward, mFAProf, "FinAidProfile", "dTotalAward" );
         dAmountAward = md_dAmountAward.doubleValue( );}
         //:dAmount = dAmountOfNeed - dAmountAward
         dAmount = dAmountOfNeed - dAmountAward;
         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dTotalWorkStudy( VIEW mFAProf BASED ON LOD mFAProf,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure, 
//:                 SHORT GetOrSetFlag )
//:   DECIMAL dTotal
public int 
omFAProf_dTotalWorkStudy( View     mFAProf,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   double  dTotal = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:FOR EACH mFAProf.WorkStudyAllocation
         RESULT = mFAProf.cursor( "WorkStudyAllocation" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:dTotal = dTotal + mFAProf.WorkStudyAllocation.Amount 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "WorkStudyAllocation", "Amount" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dTotal = dTotal + dTempDecimal_0;
            RESULT = mFAProf.cursor( "WorkStudyAllocation" ).setNextContinue().toInt();;
         } 

         //:END
         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dTotal, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dTotal, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dTGiftNeedBasedAwards( VIEW mFAProf BASED ON LOD mFAProf,
//:             STRING ( 32 ) InternalEntityStructure,
//:             STRING ( 32 ) InternalAttribStructure,
//:             SHORT GetOrSetFlag )
//:   VIEW mFAProf2 BASED ON LOD mFAProf
public int 
omFAProf_dTGiftNeedBasedAwards( View     mFAProf,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   zVIEW    mFAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView( mFAProf2, mFAProf )
         CreateViewFromView( mFAProf2, mFAProf );
         //:dAmount = 0 
         dAmount = 0;
         //:FOR EACH mFAProf2.FinAidAward  
         //:      WHERE mFAProf2.FinAidAward.AwardType != "S" OR mFAProf2.FinAidSource.IsNeedBased = "Y" 
         RESULT = mFAProf2.cursor( "FinAidAward" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( mFAProf2, "FinAidAward", "AwardType", "S" ) != 0 || CompareAttributeToString( mFAProf2, "FinAidSource", "IsNeedBased", "Y" ) == 0 )
            { 
               //:IF mFAProf2.FinAidAward.AwardStatus = "A" OR 
               //:mFAProf2.FinAidAward.AwardStatus = "Y" OR 
               //:mFAProf2.FinAidAward.AwardStatus = "P"
               if ( CompareAttributeToString( mFAProf2, "FinAidAward", "AwardStatus", "A" ) == 0 || CompareAttributeToString( mFAProf2, "FinAidAward", "AwardStatus", "Y" ) == 0 ||
                    CompareAttributeToString( mFAProf2, "FinAidAward", "AwardStatus", "P" ) == 0 )
               { 

                  //:dAmount = dAmount + mFAProf2.FinAidAward.Amount
                  {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                     GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf2, "FinAidAward", "Amount" );
                  dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                  dAmount = dAmount + dTempDecimal_0;
                  //:ELSE 
               } 
               else
               { 
               } 

            } 

            RESULT = mFAProf2.cursor( "FinAidAward" ).setNextContinue().toInt();;
            //:   // dont do anything
            //:END 
         } 

         //:   /*IF mFAProf2.FinAidAward.AwardStatus = "A" 
         //:      dAmount = dAmount + mFAProf2.FinAidAward.AmountOffered   
         //:   ELSE 
         //:      IF mFAProf2.FinAidAward.AwardStatus = "Y" 
         //:         dAmount = dAmount + mFAProf2.FinAidAward.Amount
         //:      ELSE 
         //:         // dont do anything
         //:      END 
         //:   END*/
         //:END
         //:DropView( mFAProf2 ) 
         DropView( mFAProf2 );
         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
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
//:dTotalSHAward( VIEW mFAProf BASED ON LOD mFAProf,
//:             STRING ( 32 ) InternalEntityStructure,
//:             STRING ( 32 ) InternalAttribStructure,
//:             SHORT GetOrSetFlag )
//:   VIEW mFAProf2 BASED ON LOD mFAProf
public int 
omFAProf_dTotalSHAward( View     mFAProf,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   zVIEW    mFAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView( mFAProf2, mFAProf )
         CreateViewFromView( mFAProf2, mFAProf );
         //:dAmount = 0 
         dAmount = 0;
         //:FOR EACH mFAProf2.FinAidAward 
         //:      WHERE mFAProf2.FinAidAward.AwardType = "S" AND mFAProf2.FinAidSource.IsNeedBased != "Y"
         RESULT = mFAProf2.cursor( "FinAidAward" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( mFAProf2, "FinAidAward", "AwardType", "S" ) == 0 && CompareAttributeToString( mFAProf2, "FinAidSource", "IsNeedBased", "Y" ) != 0 )
            { 
               //:IF mFAProf2.FinAidAward.AwardStatus = "A" OR mFAProf2.FinAidAward.AwardStatus = "Y" 
               if ( CompareAttributeToString( mFAProf2, "FinAidAward", "AwardStatus", "A" ) == 0 || CompareAttributeToString( mFAProf2, "FinAidAward", "AwardStatus", "Y" ) == 0 )
               { 
                  //:dAmount = dAmount + mFAProf2.FinAidAward.Amount
                  {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                     GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf2, "FinAidAward", "Amount" );
                  dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                  dAmount = dAmount + dTempDecimal_0;
                  //:ELSE 
               } 
               else
               { 
               } 

            } 

            RESULT = mFAProf2.cursor( "FinAidAward" ).setNextContinue().toInt();;
            //:   // dont do anything
            //:END
         } 

         //:   /*IF mFAProf2.FinAidAward.AwardStatus = "A" 
         //:      dAmount = dAmount + mFAProf2.FinAidAward.AmountOffered   
         //:   ELSE 
         //:      IF mFAProf2.FinAidAward.AwardStatus = "Y" 
         //:         dAmount = dAmount + mFAProf2.FinAidAward.Amount
         //:      ELSE 
         //:         // dont do anything
         //:      END 
         //:   END*/
         //:END
         //:DropView( mFAProf2 ) 
         DropView( mFAProf2 );
         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dTotalCOA( VIEW mFAProf BASED ON LOD mFAProf,
//:             STRING ( 32 ) InternalEntityStructure,
//:             STRING ( 32 ) InternalAttribStructure,
//:             SHORT GetOrSetFlag )
//:   VIEW mFAProf2 BASED ON LOD mFAProf
public int 
omFAProf_dTotalCOA( View     mFAProf,
                    String InternalEntityStructure,
                    String InternalAttribStructure,
                    Integer   GetOrSetFlag )
{
   zVIEW    mFAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Use Global Operation to compute COA.
         //:CalculateTotalCOA( mFAProf, dAmount )

         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dTotalNeed( VIEW mFAProf BASED ON LOD mFAProf,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure, 
//:                 SHORT GetOrSetFlag )
//:   DECIMAL dAmount 
public int 
omFAProf_dTotalNeed( View     mFAProf,
                     String InternalEntityStructure,
                     String InternalAttribStructure,
                     Integer   GetOrSetFlag )
{
   double  dAmount = 0.0;
   //:DECIMAL dAmountEFC 
   double  dAmountEFC = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:IF mFAProf.FinAidProfile.EFC = ""
         if ( CompareAttributeToString( mFAProf, "FinAidProfile", "EFC", "" ) == 0 )
         { 
            //:dAmount = 0 
            dAmount = 0;
            //:ELSE  
         } 
         else
         { 
            //:dAmountEFC = mFAProf.FinAidProfile.EFC
            {MutableDouble md_dAmountEFC = new MutableDouble( dAmountEFC );
                         GetDecimalFromAttribute( md_dAmountEFC, mFAProf, "FinAidProfile", "EFC" );
            dAmountEFC = md_dAmountEFC.doubleValue( );}
            //:dAmount = mFAProf.FinAidProfile.dTotalCOA
            {MutableDouble md_dAmount = new MutableDouble( dAmount );
                         GetDecimalFromAttribute( md_dAmount, mFAProf, "FinAidProfile", "dTotalCOA" );
            dAmount = md_dAmount.doubleValue( );}
            //:dAmount = dAmount  - dAmountEFC
            dAmount = dAmount - dAmountEFC;
            //:IF dAmount < 0
            if ( dAmount < 0 )
            { 
               //:dAmount = 0
               dAmount = 0;
            } 

            //:END
         } 

         //:END

         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dTotalRemainingNeed( VIEW mFAProf BASED ON LOD mFAProf,
//:             STRING ( 32 ) InternalEntityStructure,
//:             STRING ( 32 ) InternalAttribStructure,
//:             SHORT GetOrSetFlag )
//:   DECIMAL dAmount 
public int 
omFAProf_dTotalRemainingNeed( View     mFAProf,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   double  dAmount = 0.0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:dAmount = mFAProf.FinAidProfile.dTotalCOA - 
         //:          mFAProf.FinAidProfile.dTotalAward - 
         //:          mFAProf.FinAidProfile.dTotalSelfHelpAward 
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "FinAidProfile", "dTotalCOA" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                   GetDecimalFromAttribute( md_dTempDecimal_1, mFAProf, "FinAidProfile", "dTotalAward" );
         dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
         {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                   GetDecimalFromAttribute( md_dTempDecimal_2, mFAProf, "FinAidProfile", "dTotalSelfHelpAward" );
         dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
         dAmount = dTempDecimal_0 - dTempDecimal_1 - dTempDecimal_2;

         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
public int 
omFAProf_FindFinAidProfile( zVIEW    mFAProf,
                            View     ViewToSubtask,
                            String   szStudentOrProspect,
                            int      nID,
                            String   szYear )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );

   //:FindFinAidProfile( VIEW mFAProf  BASED ON LOD mFAProf,
   //:             VIEW ViewToSubtask,
   //:             STRING ( 32 ) szStudentOrProspect,
   //:             INTEGER nID,
   //:             STRING ( 32 ) szYear )

   //:IF szStudentOrProspect = "S" 
   if ( ZeidonStringCompare( szStudentOrProspect, 1, 0, "S", 1, 0, 33 ) == 0 )
   { 
      //:ACTIVATE mFAProf 
      //:   WHERE mFAProf.Student.ID = nID 
      //:     AND mFAProf.CollegeYear.Year = szYear
      omFAProf_fnLocalBuildQual_18( ViewToSubtask, vTempViewVar_0, nID, szYear );
      RESULT = ActivateObjectInstance( mFAProf, "mFAProf", ViewToSubtask, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
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

   //:IF szStudentOrProspect = "P" 
   if ( ZeidonStringCompare( szStudentOrProspect, 1, 0, "P", 1, 0, 33 ) == 0 )
   { 
      //:ACTIVATE mFAProf 
      //:   WHERE mFAProf.Prospect.ID = nID 
      //:     AND mFAProf.CollegeYear.Year = szYear
      omFAProf_fnLocalBuildQual_19( ViewToSubtask, vTempViewVar_1, nID, szYear );
      RESULT = ActivateObjectInstance( mFAProf, "mFAProf", ViewToSubtask, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
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

   //:RETURN -1
   return( -1 );
//    
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dCollegeYear( VIEW mFAProf BASED ON LOD mFAProf,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )

//:   STRING ( 20 ) szString
public int 
omFAProf_dCollegeYear( View     mFAProf,
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
         //:szString = mFAProf.CollegeYear.Year 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetVariableFromAttribute( sb_szString, mi_lTempInteger_0, 'S', 21, mFAProf, "CollegeYear", "Year", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szString = sb_szString.toString( );}
         //:StoreStringInRecord ( mFAProf,
         //:                      InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, szString );
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
//:dTotalPerPeriodExpect( VIEW mFAProf BASED ON LOD mFAProf,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )
//:   DECIMAL dAmount 
public int 
omFAProf_dTotalPerPeriodExpect( View     mFAProf,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
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
         //:FOR EACH mFAProf.PerPeriodFinAidAwardDisbursement 
         RESULT = mFAProf.cursor( "PerPeriodFinAidAwardDisbursement" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:dAmount = dAmount + mFAProf.PerPeriodFinAidAwardDisbursement.AmountExpected 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "PerPeriodFinAidAwardDisbursement", "AmountExpected" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dAmount = dAmount + dTempDecimal_0;
            RESULT = mFAProf.cursor( "PerPeriodFinAidAwardDisbursement" ).setNextContinue().toInt();;
         } 

         //:END
         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
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
//:dTotalPerPeriodRecv( VIEW mFAProf BASED ON LOD mFAProf,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )
//:   DECIMAL dAmount 
public int 
omFAProf_dTotalPerPeriodRecv( View     mFAProf,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
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
         //:FOR EACH mFAProf.PerPeriodFinAidAwardDisbursement 
         RESULT = mFAProf.cursor( "PerPeriodFinAidAwardDisbursement" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:dAmount = dAmount + mFAProf.PerPeriodFinAidAwardDisbursement.Amount 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "PerPeriodFinAidAwardDisbursement", "Amount" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dAmount = dAmount + dTempDecimal_0;
            RESULT = mFAProf.cursor( "PerPeriodFinAidAwardDisbursement" ).setNextContinue().toInt();;
         } 

         //:END
         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
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
//:dTotalCreditsPerPeriod( VIEW mFAProf BASED ON LOD mFAProf,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )
//:   DECIMAL dCredits 
public int 
omFAProf_dTotalCreditsPerPeriod( View     mFAProf,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   double  dCredits = 0.0;
   //:VIEW mFAProf2 BASED ON LOD mFAProf
   zVIEW    mFAProf2 = new zVIEW( );
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:dCredits = 0
         dCredits = 0;
         //:CreateViewFromView( mFAProf2, mFAProf )
         CreateViewFromView( mFAProf2, mFAProf );
         //:FOR EACH mFAProf2.DisplayEnrolled 
         RESULT = mFAProf2.cursor( "DisplayEnrolled" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mFAProf2.DisplayEnrolled.Status = "C" OR 
            //:   mFAProf2.DisplayEnrolled.Status = "T" OR 
            //:   mFAProf2.DisplayEnrolled.Status = "W"
            if ( CompareAttributeToString( mFAProf2, "DisplayEnrolled", "Status", "C" ) == 0 || CompareAttributeToString( mFAProf2, "DisplayEnrolled", "Status", "T" ) == 0 || CompareAttributeToString( mFAProf2, "DisplayEnrolled", "Status", "W" ) == 0 )
            { 

               //:dCredits = dCredits + mFAProf2.DisplayEnrolled.CreditHours  
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf2, "DisplayEnrolled", "CreditHours" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dCredits = dCredits + dTempDecimal_0;
            } 

            RESULT = mFAProf2.cursor( "DisplayEnrolled" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:DropView( mFAProf2 )
         DropView( mFAProf2 );
         //:StoreValueInRecord( mFAProf,InternalEntityStructure,
         //:                    InternalAttribStructure, dCredits, 0 )
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dCredits, 0 );
         break ;
      //:                       
      //:/* end zDERIVED_GET */
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
//:AddScholarshipAward( VIEW mFAProf BASED ON LOD mFAProf,
//:                     INTEGER nSchShipID, DECIMAL dAwardAmount )
//:   VIEW mFASrc BASED ON LOD  mFASrc 
public int 
omFAProf_AddScholarshipAward( View     mFAProf,
                              int      nSchShipID,
                              double  dAwardAmount )
{
   zVIEW    mFASrc = new zVIEW( );
   //:VIEW wXferO REGISTERED AS wXferO 
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:INTEGER nDisb
   int      nDisb = 0;
   //:DECIMAL dAmountDiv
   double  dAmountDiv = 0.0;
   //:DECIMAL dAmount
   double  dAmount = 0.0;
   //:DECIMAL dAward 
   double  dAward = 0.0;
   //:DECIMAL dDiff
   double  dDiff = 0.0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;

   RESULT = GetViewByName( wXferO, "wXferO", mFAProf, zLEVEL_TASK );

   //:// activate the scholarship needed.
   //:// activate the finaidsrc that goes with it. 
   //:ACTIVATE mFASrc  
   //:   WHERE mFASrc.Scholarship.ID = nSchShipID
   //:   RESTRICTING mFASrc.FinAidAwardAssigned TO mFASrc.FinAidAwardAssigned.ID < 0 
   omFAProf_fnLocalBuildQual_20( mFAProf, vTempViewVar_0, nSchShipID );
   RESULT = ActivateObjectInstance( mFASrc, "mFASrc", mFAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:// if there is no finaidsrc then skip this.who operation
   //:IF RESULT < 0  OR mFASrc.FinAidSource DOES NOT EXIST 
   lTempInteger_0 = CheckExistenceOfEntity( mFASrc, "FinAidSource" );
   if ( RESULT < 0 || lTempInteger_0 != 0 )
   { 
      //:RETURN -1    
      if(8==8)return( -1 );
   } 

   //:END
   //:SET CURSOR FIRST mFAProf.FinAidAward WHERE mFAProf.Scholarship.ID = nSchShipID
   RESULT = mFAProf.cursor( "FinAidAward" ).setFirst().toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mFAProf, "Scholarship", "ID", nSchShipID ) != 0 ) )
      { 
         RESULT = mFAProf.cursor( "FinAidAward" ).setNextContinue().toInt();;
      } 

   } 

   //:IF RESULT >= zCURSOR_SET // already there.
   if ( RESULT >= zCURSOR_SET )
   { 
      //:RETURN -1    
      if(8==8)return( -1 );
   } 

   //:END
   //:  
   //:    
   //:CREATE ENTITY mFAProf.FinAidAward
   RESULT = CreateEntity( mFAProf, "FinAidAward", zPOS_AFTER );
   //:INCLUDE mFAProf.FinAidSource FROM mFASrc.FinAidSource 
   RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );
   //:mFAProf.FinAidAward.DateOffered = wXferO.Root.dCurrentDate 
   SetAttributeFromAttribute( mFAProf, "FinAidAward", "DateOffered", wXferO, "Root", "dCurrentDate" );
   //:mFAProf.FinAidAward.AwardType = mFASrc.FinAidSource.AidType 
   SetAttributeFromAttribute( mFAProf, "FinAidAward", "AwardType", mFASrc, "FinAidSource", "AidType" );
   //:mFAProf.FinAidAward.Amount = dAwardAmount /// mFASrc.FinAidSource.AwardAmount 
   SetAttributeFromDecimal( mFAProf, "FinAidAward", "Amount", dAwardAmount );
   //:dAward = mFAProf.FinAidAward.Amount
   {MutableDouble md_dAward = new MutableDouble( dAward );
       GetDecimalFromAttribute( md_dAward, mFAProf, "FinAidAward", "Amount" );
   dAward = md_dAward.doubleValue( );}
   //:mFAProf.FinAidAward.AwardStatus = "A"
   SetAttributeFromString( mFAProf, "FinAidAward", "AwardStatus", "A" );
   //:IF mFASrc.FinAidSource.Disbursements = ""
   //:      OR mFASrc.FinAidSource.Disbursements = 0
   if ( CompareAttributeToString( mFASrc, "FinAidSource", "Disbursements", "" ) == 0 || CompareAttributeToInteger( mFASrc, "FinAidSource", "Disbursements", 0 ) == 0 )
   { 
      //:nDisb = 1
      nDisb = 1;
      //:ELSE 
   } 
   else
   { 
      //:nDisb = mFASrc.FinAidSource.Disbursements 
      {MutableInt mi_nDisb = new MutableInt( nDisb );
             GetIntegerFromAttribute( mi_nDisb, mFASrc, "FinAidSource", "Disbursements" );
      nDisb = mi_nDisb.intValue( );}
   } 

   //:END 

   //:dAmountDiv = 0
   dAmountDiv = 0;
   //:dAmount    = 0
   dAmount = 0;
   //:dAmountDiv = dAward / nDisb 
   dAmountDiv = dAward / nDisb;
   //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod   
   RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:LOOP WHILE nDisb > 0
      while ( nDisb > 0 )
      { 
         //:CREATE ENTITY  mFAProf.FinAidAwardDisbursement 
         RESULT = CreateEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
         //:INCLUDE mFAProf.FinAidAwardPeriod FROM mFAProf.PerProfileFinAidAwardPeriod  
         RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod", mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
         //:mFAProf.FinAidAwardDisbursement.AmountExpected =  dAmountDiv 
         SetAttributeFromDecimal( mFAProf, "FinAidAwardDisbursement", "AmountExpected", dAmountDiv );
         //:mFAProf.FinAidAwardDisbursement.DisbursementDate = mFAProf.FinAidAwardPeriod.BeginDate 
         SetAttributeFromAttribute( mFAProf, "FinAidAwardDisbursement", "DisbursementDate", mFAProf, "FinAidAwardPeriod", "BeginDate" );
         //:dAmount = dAmount + dAmountDiv 
         dAmount = dAmount + dAmountDiv;
         //:SET CURSOR NEXT mFAProf.PerProfileFinAidAwardPeriod   
         RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
         //:nDisb = nDisb - 1
         nDisb = nDisb - 1;
      } 

      //:END
      //:IF dAmount < dAward 
      if ( dAmount < dAward )
      { 
         //:dDiff = dAward - dAmount
         dDiff = dAward - dAmount;
         //:SET CURSOR FIRST mFAProf.FinAidAwardDisbursement 
         RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setFirst().toInt();
         //:mFAProf.FinAidAwardDisbursement.AmountExpected = mFAProf.FinAidAwardDisbursement.AmountExpected + dDiff 
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "FinAidAwardDisbursement", "AmountExpected" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         dTempDecimal_1 = dTempDecimal_0 + dDiff;
         SetAttributeFromDecimal( mFAProf, "FinAidAwardDisbursement", "AmountExpected", dTempDecimal_1 );
      } 

      //:END   
   } 

   //:END 
   //:DropView( mFASrc )
   DropView( mFASrc );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:AddReturningAward( VIEW mFAProf BASED ON LOD mFAProf,
//:                   INTEGER nSrcID, 
//:                   DECIMAL dAwardAmount )
//:   
//:   VIEW mFASrc BASED ON LOD  mFASrc 
public int 
omFAProf_AddReturningAward( View     mFAProf,
                            int      nSrcID,
                            double  dAwardAmount )
{
   zVIEW    mFASrc = new zVIEW( );
   //:VIEW wXferO REGISTERED AS wXferO 
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:INTEGER nDisb
   int      nDisb = 0;
   //:DECIMAL dAmountDiv
   double  dAmountDiv = 0.0;
   //:DECIMAL dAmount
   double  dAmount = 0.0;
   //:DECIMAL dAward 
   double  dAward = 0.0;
   //:DECIMAL dDiff
   double  dDiff = 0.0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;

   RESULT = GetViewByName( wXferO, "wXferO", mFAProf, zLEVEL_TASK );

   //:// activate the scholarship needed.
   //:// activate the finaidsrc that goes with it. 
   //:ACTIVATE mFASrc  
   //:   WHERE mFASrc.FinAidSource.ID = nSrcID
   //:   RESTRICTING mFASrc.FinAidAwardAssigned TO mFASrc.FinAidAwardAssigned.ID < 0 
   omFAProf_fnLocalBuildQual_21( mFAProf, vTempViewVar_0, nSrcID );
   RESULT = ActivateObjectInstance( mFASrc, "mFASrc", mFAProf, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:// if there is no finaidsrc then skip this.who operation
   //:IF RESULT < 0  OR mFASrc.FinAidSource DOES NOT EXIST 
   lTempInteger_0 = CheckExistenceOfEntity( mFASrc, "FinAidSource" );
   if ( RESULT < 0 || lTempInteger_0 != 0 )
   { 
      //:RETURN -1    
      if(8==8)return( -1 );
   } 

   //:END
   //:SET CURSOR FIRST mFAProf.FinAidAward WHERE mFAProf.FinAidSource.ID = nSrcID
   RESULT = mFAProf.cursor( "FinAidAward" ).setFirst().toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( mFAProf, "FinAidSource", "ID", nSrcID ) != 0 ) )
      { 
         RESULT = mFAProf.cursor( "FinAidAward" ).setNextContinue().toInt();;
      } 

   } 

   //:IF RESULT >= zCURSOR_SET // already there.
   if ( RESULT >= zCURSOR_SET )
   { 
      //:RETURN -1    
      if(8==8)return( -1 );
   } 

   //:END
   //:  
   //:    
   //:CREATE ENTITY mFAProf.FinAidAward
   RESULT = CreateEntity( mFAProf, "FinAidAward", zPOS_AFTER );
   //:INCLUDE mFAProf.FinAidSource FROM mFASrc.FinAidSource 
   RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );
   //:mFAProf.FinAidAward.DateOffered = wXferO.Root.dCurrentDate 
   SetAttributeFromAttribute( mFAProf, "FinAidAward", "DateOffered", wXferO, "Root", "dCurrentDate" );
   //:mFAProf.FinAidAward.AwardType = mFASrc.FinAidSource.AidType 
   SetAttributeFromAttribute( mFAProf, "FinAidAward", "AwardType", mFASrc, "FinAidSource", "AidType" );
   //:mFAProf.FinAidAward.Amount = dAwardAmount /// mFASrc.FinAidSource.AwardAmount 
   SetAttributeFromDecimal( mFAProf, "FinAidAward", "Amount", dAwardAmount );
   //:dAward = mFAProf.FinAidAward.Amount
   {MutableDouble md_dAward = new MutableDouble( dAward );
       GetDecimalFromAttribute( md_dAward, mFAProf, "FinAidAward", "Amount" );
   dAward = md_dAward.doubleValue( );}
   //:mFAProf.FinAidAward.AwardStatus = "A"
   SetAttributeFromString( mFAProf, "FinAidAward", "AwardStatus", "A" );
   //:IF mFASrc.FinAidSource.Disbursements = ""
   //:      OR mFASrc.FinAidSource.Disbursements = 0
   if ( CompareAttributeToString( mFASrc, "FinAidSource", "Disbursements", "" ) == 0 || CompareAttributeToInteger( mFASrc, "FinAidSource", "Disbursements", 0 ) == 0 )
   { 
      //:nDisb = 1
      nDisb = 1;
      //:ELSE 
   } 
   else
   { 
      //:nDisb = mFASrc.FinAidSource.Disbursements 
      {MutableInt mi_nDisb = new MutableInt( nDisb );
             GetIntegerFromAttribute( mi_nDisb, mFASrc, "FinAidSource", "Disbursements" );
      nDisb = mi_nDisb.intValue( );}
   } 

   //:END 

   //:dAmountDiv = 0
   dAmountDiv = 0;
   //:dAmount    = 0
   dAmount = 0;
   //:dAmountDiv = dAward / nDisb 
   dAmountDiv = dAward / nDisb;
   //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod   
   RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:LOOP WHILE nDisb > 0
      while ( nDisb > 0 )
      { 
         //:CREATE ENTITY  mFAProf.FinAidAwardDisbursement 
         RESULT = CreateEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
         //:INCLUDE mFAProf.FinAidAwardPeriod FROM mFAProf.PerProfileFinAidAwardPeriod  
         RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod", mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
         //:mFAProf.FinAidAwardDisbursement.AmountExpected =  dAmountDiv 
         SetAttributeFromDecimal( mFAProf, "FinAidAwardDisbursement", "AmountExpected", dAmountDiv );
         //:mFAProf.FinAidAwardDisbursement.DisbursementDate = mFAProf.FinAidAwardPeriod.BeginDate 
         SetAttributeFromAttribute( mFAProf, "FinAidAwardDisbursement", "DisbursementDate", mFAProf, "FinAidAwardPeriod", "BeginDate" );
         //:dAmount = dAmount + dAmountDiv 
         dAmount = dAmount + dAmountDiv;
         //:SET CURSOR NEXT mFAProf.PerProfileFinAidAwardPeriod   
         RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
         //:nDisb = nDisb - 1
         nDisb = nDisb - 1;
      } 

      //:END
      //:IF dAmount < dAward 
      if ( dAmount < dAward )
      { 
         //:dDiff = dAward - dAmount
         dDiff = dAward - dAmount;
         //:SET CURSOR FIRST mFAProf.FinAidAwardDisbursement 
         RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setFirst().toInt();
         //:mFAProf.FinAidAwardDisbursement.AmountExpected = mFAProf.FinAidAwardDisbursement.AmountExpected + dDiff 
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "FinAidAwardDisbursement", "AmountExpected" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         dTempDecimal_1 = dTempDecimal_0 + dDiff;
         SetAttributeFromDecimal( mFAProf, "FinAidAwardDisbursement", "AmountExpected", dTempDecimal_1 );
      } 

      //:END   
   } 

   //:END 
   //:DropView( mFASrc )
   DropView( mFASrc );
   return( 0 );
// END
} 


//:ENTITY CONSTRAINT OPERATION
//:ValidateFinAidAward( VIEW mFAProf BASED ON LOD mFAProf,
//:                STRING ( 32 ) NameOfEntity,
//:                SHORT Event,
//:                SHORT State )

//:   DECIMAL TotalExpected 
public int 
omFAProf_ValidateFinAidAward( View     mFAProf,
                              String   NameOfEntity,
                              Integer   Event,
                              Integer   State )
{
   double  TotalExpected = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_0 = 0;


   //:CASE Event
   switch( Event )
   { 
      //:OF zECE_ACCEPT:
      case zECE_ACCEPT :

         //:// Make sure that the total of the "Amount Offered" Editbox is the sum of the "Expected Amount" for
         //:// each FinAidAwardDisbursement.
         //:FOR EACH mFAProf.FinAidAwardDisbursement
         RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:TotalExpected = TotalExpected + mFAProf.FinAidAwardDisbursement.AmountExpected 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "FinAidAwardDisbursement", "AmountExpected" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            TotalExpected = TotalExpected + dTempDecimal_0;
            RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setNextContinue().toInt();;
         } 

         //:END
         //:IF TotalExpected != mFAProf.FinAidAward.Amount
         if ( CompareAttributeToDecimal( mFAProf, "FinAidAward", "Amount", TotalExpected ) != 0 )
         { 
            //:MessageSend( mFAProf, "", "Accept Award",
            //:             "The Amount Offered must be equal to the total of the Expected Amounts.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mFAProf, "", "Accept Award", "The Amount Offered must be equal to the total of the Expected Amounts.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END

         //:IF mFAProf.FinAidAward.AwardStatus = "Y" // accepted
         if ( CompareAttributeToString( mFAProf, "FinAidAward", "AwardStatus", "Y" ) == 0 )
         { 
            //:IF mFAProf.FinAidAward.DateAccepted = ""     // OR mFAProf.FinAidAward.Amount = ""
            if ( CompareAttributeToString( mFAProf, "FinAidAward", "DateAccepted", "" ) == 0 )
            { 
               //:MessageSend( mFAProf, "", "Accept Award",
               //:          "The date accepted must be filled in if the Status is Accepted.",
               //:          zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               MessageSend( mFAProf, "", "Accept Award", "The date accepted must be filled in if the Status is Accepted.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               //:RETURN -1
               if(8==8)return( -1 );
            } 

            //:END 
         } 

         //:END
         break ;

      //:/* end zECE_ACCEPT */
      //:OF   zECE_CANCEL:
      case zECE_CANCEL :
         break ;

      //:/* end zECE_CANCEL */
      //:OF   zECE_CREATE:
      case zECE_CREATE :
         break ;

      //:/* end zECE_CREATE */
      //:OF zECE_DELETE:
      case zECE_DELETE :
         break ;
      //:/* end zECE_DELETE */
      //:OF   zECE_EXCLUDE:
      case zECE_EXCLUDE :
         break ;

      //:/* end zECE_EXCLUDE */
      //:OF   zECE_INCLUDE:
      case zECE_INCLUDE :
         break ;
   } 


   //:   /* end zECE_INCLUDE */
   //:END  /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omFAProf_CopyProfile( View     mFAProf,
                      zVIEW    mNewFAProf )
{
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   //:CopyProfile( VIEW mFAProf BASED ON LOD mFAProf,
   //:          VIEW mNewFAProf BASED ON LOD mFAProf )

   //:ACTIVATE mNewFAProf EMPTY
   RESULT = ActivateEmptyObjectInstance( mNewFAProf, "mFAProf", mFAProf, zSINGLE );
   //:CREATE ENTITY mNewFAProf.FinAidProfile  
   RESULT = CreateEntity( mNewFAProf, "FinAidProfile", zPOS_AFTER );
   //:mNewFAProf.FinAidProfile.FullPartTime = mFAProf.FinAidProfile.FullPartTime 
   SetAttributeFromAttribute( mNewFAProf, "FinAidProfile", "FullPartTime", mFAProf, "FinAidProfile", "FullPartTime" );
   //:mNewFAProf.FinAidProfile.DependencyStatus = mFAProf.FinAidProfile.DependencyStatus 
   SetAttributeFromAttribute( mNewFAProf, "FinAidProfile", "DependencyStatus", mFAProf, "FinAidProfile", "DependencyStatus" );
   //:mNewFAProf.FinAidProfile.ResidencyStatus = mFAProf.FinAidProfile.ResidencyStatus 
   SetAttributeFromAttribute( mNewFAProf, "FinAidProfile", "ResidencyStatus", mFAProf, "FinAidProfile", "ResidencyStatus" );
   //:mNewFAProf.FinAidProfile.MonthsEnrolled = mFAProf.FinAidProfile.MonthsEnrolled 
   SetAttributeFromAttribute( mNewFAProf, "FinAidProfile", "MonthsEnrolled", mFAProf, "FinAidProfile", "MonthsEnrolled" );
   //:mNewFAProf.FinAidProfile.MasterPromissorySignedDate = mFAProf.FinAidProfile.MasterPromissorySignedDate 
   SetAttributeFromAttribute( mNewFAProf, "FinAidProfile", "MasterPromissorySignedDate", mFAProf, "FinAidProfile", "MasterPromissorySignedDate" );
   //:IF mFAProf.Student EXISTS 
   lTempInteger_0 = CheckExistenceOfEntity( mFAProf, "Student" );
   if ( lTempInteger_0 == 0 )
   { 
      //:INCLUDE mNewFAProf.Student  FROM mFAProf.Student 
      RESULT = IncludeSubobjectFromSubobject( mNewFAProf, "Student", mFAProf, "Student", zPOS_AFTER );
   } 

   //:END 
   //:IF mFAProf.Prospect EXISTS 
   lTempInteger_1 = CheckExistenceOfEntity( mFAProf, "Prospect" );
   if ( lTempInteger_1 == 0 )
   { 
      //:INCLUDE mNewFAProf.Prospect FROM mFAProf.Prospect 
      RESULT = IncludeSubobjectFromSubobject( mNewFAProf, "Prospect", mFAProf, "Prospect", zPOS_AFTER );
   } 

   //:END 
   //:INCLUDE mNewFAProf.FinAidAdmin FROM mFAProf.FinAidAdmin 
   RESULT = IncludeSubobjectFromSubobject( mNewFAProf, "FinAidAdmin", mFAProf, "FinAidAdmin", zPOS_AFTER );
   //:COMMIT  mNewFAProf
   RESULT = CommitObjectInstance( mNewFAProf );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dCommentCodes( VIEW mFAProf BASED ON LOD mFAProf,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )
//:   STRING (  120  ) szDelimitedString
public int 
omFAProf_dCommentCodes( View     mFAProf,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   String   szDelimitedString = null;
   //:STRING (  120  ) szSourceString
   String   szSourceString = null;
   //:STRING (  120  ) szShortString
   String   szShortString = null;
   //:STRING (  3  ) szDelim
   String   szDelim = null;
   //:INTEGER nIndex
   int      nIndex = 0;
   //:INTEGER nLen 
   int      nLen = 0;
   //:INTEGER nTemp
   int      nTemp = 0;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:szDelim = " / "
          {StringBuilder sb_szDelim;
         if ( szDelim == null )
            sb_szDelim = new StringBuilder( 32 );
         else
            sb_szDelim = new StringBuilder( szDelim );
                  ZeidonStringCopy( sb_szDelim, 1, 0, " / ", 1, 0, 4 );
         szDelim = sb_szDelim.toString( );}
         //:szDelimitedString = ""
          {StringBuilder sb_szDelimitedString;
         if ( szDelimitedString == null )
            sb_szDelimitedString = new StringBuilder( 32 );
         else
            sb_szDelimitedString = new StringBuilder( szDelimitedString );
                  ZeidonStringCopy( sb_szDelimitedString, 1, 0, "", 1, 0, 121 );
         szDelimitedString = sb_szDelimitedString.toString( );}
         //:szSourceString = mFAProf.FAISIREligibility.CommentCodes 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szSourceString;
         if ( szSourceString == null )
            sb_szSourceString = new StringBuilder( 32 );
         else
            sb_szSourceString = new StringBuilder( szSourceString );
                   GetVariableFromAttribute( sb_szSourceString, mi_lTempInteger_0, 'S', 121, mFAProf, "FAISIREligibility", "CommentCodes", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szSourceString = sb_szSourceString.toString( );}
         //:nLen = zGetStringLen( szSourceString )
         nLen = zGetStringLen( szSourceString );
         //:nIndex = 1
         nIndex = 1;
         //:LOOP WHILE nIndex <= nLen
         while ( nIndex <= nLen )
         { 
            //:nTemp = nLen - nIndex 
            nTemp = nLen - nIndex;
            //:IF nTemp >= 3 
            if ( nTemp >= 3 )
            { 
               //:szShortString = szSourceString [ nIndex : 3 ]
                {StringBuilder sb_szShortString;
               if ( szShortString == null )
                  sb_szShortString = new StringBuilder( 32 );
               else
                  sb_szShortString = new StringBuilder( szShortString );
                              ZeidonStringCopy( sb_szShortString, 1, 0, szSourceString, nIndex, 3, 121 );
               szShortString = sb_szShortString.toString( );}
               //:nTemp = nIndex + 3 
               nTemp = nIndex + 3;
               //:IF nTemp  >= nLen // last one 
               if ( nTemp >= nLen )
               { 
                  //:szDelimitedString = szDelimitedString + szShortString 
                   {StringBuilder sb_szDelimitedString;
                  if ( szDelimitedString == null )
                     sb_szDelimitedString = new StringBuilder( 32 );
                  else
                     sb_szDelimitedString = new StringBuilder( szDelimitedString );
                                    ZeidonStringConcat( sb_szDelimitedString, 1, 0, szShortString, 1, 0, 121 );
                  szDelimitedString = sb_szDelimitedString.toString( );}
                  //:ELSE 
               } 
               else
               { 
                  //:szDelimitedString = szDelimitedString + szShortString + szDelim
                   {StringBuilder sb_szDelimitedString;
                  if ( szDelimitedString == null )
                     sb_szDelimitedString = new StringBuilder( 32 );
                  else
                     sb_szDelimitedString = new StringBuilder( szDelimitedString );
                                    ZeidonStringConcat( sb_szDelimitedString, 1, 0, szShortString, 1, 0, 121 );
                  szDelimitedString = sb_szDelimitedString.toString( );}
                   {StringBuilder sb_szDelimitedString;
                  if ( szDelimitedString == null )
                     sb_szDelimitedString = new StringBuilder( 32 );
                  else
                     sb_szDelimitedString = new StringBuilder( szDelimitedString );
                                    ZeidonStringConcat( sb_szDelimitedString, 1, 0, szDelim, 1, 0, 121 );
                  szDelimitedString = sb_szDelimitedString.toString( );}
               } 

               //:END
            } 

            //:END
            //:nIndex = nIndex + 3
            nIndex = nIndex + 3;
         } 

         //:END

         //:StoreStringInRecord ( mFAProf,
         //:                      InternalEntityStructure, InternalAttribStructure, szDelimitedString )
         StoreStringInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, szDelimitedString );
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
//:CreateBefAftEnrolled( VIEW mFAProf BASED ON LOD mFAProf )
//:   VIEW mFAProf2 BASED ON LOD mFAProf 
public int 
omFAProf_CreateBefAftEnrolled( View     mFAProf )
{
   zVIEW    mFAProf2 = new zVIEW( );
   //:STRING (  18  ) szBeginDate
   String   szBeginDate = null;
   //:STRING (  18  ) szEndDate
   String   szEndDate = null;
   int      RESULT = 0;


   //:CreateViewFromView( mFAProf2, mFAProf )
   CreateViewFromView( mFAProf2, mFAProf );
   //:/* 
   //:szBeginDate = mFAProf2.PerProfileFinAidAwardPeriod.BeginDate 
   //:szEndDate = mFAProf2.PerProfileFinAidAwardPeriod.EndDate 
   //:OrderEntityForView( mFAProf2, "Enrolled", "Class.ClassStartDate A" )
   //:FOR EACH mFAProf2.Enrolled
   //:   IF mFAProf2.Class EXISTS
   //:        AND mFAProf2.Class.ClassStartDate < szBeginDate
   //:     INCLUDE mFAProf2.PriorToEnrolled  FROM mFAProf2.Enrolled 
   //:   END
   //:END
   //:FOR EACH mFAProf2.Enrolled
   //:   IF mFAProf2.Class EXISTS
   //:        AND mFAProf2.Class.ClassStartDate > szEndDate
   //:      INCLUDE mFAProf2.AfterEnrolled  FROM mFAProf2.Enrolled 
   //:   END  
   //:END
   //:FOR EACH mFAProf2.Enrolled
   //:   IF mFAProf2.Class EXISTS
   //:         AND mFAProf2.Class.ClassStartDate >= szBeginDate 
   //:         AND mFAProf2.Class.ClassStartDate <= szEndDate
   //:      IF  mFAProf2.EnrolledFinAidAwardPeriod DOES not EXIST  
   //:         INCLUDE mFAProf2.PeriodEnrolled FROM mFAProf2.Enrolled 
   //:      END 
   //:   END 
   //:END
   //:*/

   //:FOR EACH mFAProf2.Enrolled
   RESULT = mFAProf2.cursor( "Enrolled" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:INCLUDE mFAProf2.WorkEnrolled FROM mFAProf2.Enrolled 
      RESULT = IncludeSubobjectFromSubobject( mFAProf2, "WorkEnrolled", mFAProf2, "Enrolled", zPOS_AFTER );
      RESULT = mFAProf2.cursor( "Enrolled" ).setNextContinue().toInt();;
   } 

   //:END
   //:DropView( mFAProf2 ) 
   DropView( mFAProf2 );
   return( 0 );
//    
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dTotalCredits( VIEW mFAProf BASED ON LOD mFAProf,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   VIEW mFAProf2 BASED ON LOD mFAProf
public int 
omFAProf_dTotalCredits( View     mFAProf,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   zVIEW    mFAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView( mFAProf2, mFAProf )
         CreateViewFromView( mFAProf2, mFAProf );
         //:dAmount =0
         dAmount = 0;
         //:FOR EACH  mFAProf2.PerProfileFinAidAwardPeriod 
         RESULT = mFAProf2.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:dAmount = dAmount + mFAProf2.PerProfileFinAidAwardPeriod.dCreditsPerDisbCohort 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf2, "PerProfileFinAidAwardPeriod", "dCreditsPerDisbCohort" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dAmount = dAmount + dTempDecimal_0;
            RESULT = mFAProf2.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
         } 

         //:END
         //:DropView( mFAProf2 )
         DropView( mFAProf2 );
         //:StoreValueInRecord ( mFAProf,
         //:                      InternalEntityStructure, InternalAttribStructure, dAmount, 0)
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
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
//:dTotalClasses( VIEW mFAProf BASED ON LOD mFAProf,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   VIEW mFAProf2 BASED ON LOD mFAProf
public int 
omFAProf_dTotalClasses( View     mFAProf,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   zVIEW    mFAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView( mFAProf2, mFAProf )
         CreateViewFromView( mFAProf2, mFAProf );
         //:dAmount = 0
         dAmount = 0;
         //:FOR EACH mFAProf2.PeriodEnrolled WITHIN mFAProf2.FinAidProfile  
         RESULT = mFAProf2.cursor( "PeriodEnrolled" ).setFirst( "FinAidProfile" ).toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:dAmount = dAmount + 1
            dAmount = dAmount + 1;
            RESULT = mFAProf2.cursor( "PeriodEnrolled" ).setNextContinue().toInt();;
         } 

         //:END
         //:DropView( mFAProf2 )
         DropView( mFAProf2 );
         //:StoreValueInRecord ( mFAProf,
         //:                      InternalEntityStructure, InternalAttribStructure, dAmount, 0)
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
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
//:dPeriodEnrolledOLDisp( VIEW mFAProf BASED ON LOD mFAProf,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )
//:   STRING (  254  ) szString
public int 
omFAProf_dPeriodEnrolledOLDisp( View     mFAProf,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   String   szString = null;
   //:STRING (  254  ) szShortString
   String   szShortString = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:GetStringFromAttributeByContext( szShortString, mFAProf, "PeriodEnrolled", "Status", "", 20 )
         {StringBuilder sb_szShortString;
         if ( szShortString == null )
            sb_szShortString = new StringBuilder( 32 );
         else
            sb_szShortString = new StringBuilder( szShortString );
                   GetStringFromAttributeByContext( sb_szShortString, mFAProf, "PeriodEnrolled", "Status", "", 20 );
         szShortString = sb_szShortString.toString( );}
         //:AppendSpacesToString( szShortString, 10 )
         omFAProf_AppendSpacesToString( szShortString, 10 );
         //:szString = szShortString + "-" 
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringCopy( sb_szString, 1, 0, szShortString, 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, "-", 1, 0, 255 );
         szString = sb_szString.toString( );}
         //:szShortString = mFAProf.PeriodCourse.Number 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szShortString;
         if ( szShortString == null )
            sb_szShortString = new StringBuilder( 32 );
         else
            sb_szShortString = new StringBuilder( szShortString );
                   GetVariableFromAttribute( sb_szShortString, mi_lTempInteger_0, 'S', 255, mFAProf, "PeriodCourse", "Number", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szShortString = sb_szShortString.toString( );}
         //:AppendSpacesToString( szShortString, 6 )
         omFAProf_AppendSpacesToString( szShortString, 6 );
         //:szString = szString + szShortString + " "
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, szShortString, 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
         szString = sb_szString.toString( );}
         //:szShortString = mFAProf.PeriodCourse.Title 
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szShortString;
         if ( szShortString == null )
            sb_szShortString = new StringBuilder( 32 );
         else
            sb_szShortString = new StringBuilder( szShortString );
                   GetVariableFromAttribute( sb_szShortString, mi_lTempInteger_1, 'S', 255, mFAProf, "PeriodCourse", "Title", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szShortString = sb_szShortString.toString( );}
         //:AppendSpacesToString( szShortString , 20 )
         omFAProf_AppendSpacesToString( szShortString, 20 );
         //:szString = szString + szShortString + " "
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, szShortString, 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
         szString = sb_szString.toString( );}
         //:GetStringFromAttributeByContext( szShortString, mFAProf, "PeriodClass", "ClassStartDate", "", 10 )
         {StringBuilder sb_szShortString;
         if ( szShortString == null )
            sb_szShortString = new StringBuilder( 32 );
         else
            sb_szShortString = new StringBuilder( szShortString );
                   GetStringFromAttributeByContext( sb_szShortString, mFAProf, "PeriodClass", "ClassStartDate", "", 10 );
         szShortString = sb_szShortString.toString( );}
         //:szString = szString + "{" + szShortString + "-"
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, "{", 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, szShortString, 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, "-", 1, 0, 255 );
         szString = sb_szString.toString( );}
         //:GetStringFromAttributeByContext( szShortString, mFAProf, "PeriodClass", "ClassEndDate", "", 10 )
         {StringBuilder sb_szShortString;
         if ( szShortString == null )
            sb_szShortString = new StringBuilder( 32 );
         else
            sb_szShortString = new StringBuilder( szShortString );
                   GetStringFromAttributeByContext( sb_szShortString, mFAProf, "PeriodClass", "ClassEndDate", "", 10 );
         szShortString = sb_szShortString.toString( );}
         //:szString = szString + szShortString + "} "
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, szShortString, 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, "} ", 1, 0, 255 );
         szString = sb_szString.toString( );}
         //:szShortString = mFAProf.PeriodEnrolled.CreditHours  
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szShortString;
         if ( szShortString == null )
            sb_szShortString = new StringBuilder( 32 );
         else
            sb_szShortString = new StringBuilder( szShortString );
                   GetVariableFromAttribute( sb_szShortString, mi_lTempInteger_2, 'S', 255, mFAProf, "PeriodEnrolled", "CreditHours", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szShortString = sb_szShortString.toString( );}
         //:szString = szString + "[" + szShortString + "]"
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, "[", 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, szShortString, 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, "]", 1, 0, 255 );
         szString = sb_szString.toString( );}
         //:StoreStringInRecord ( mFAProf,
         //:                      InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, szString );
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


//:LOCAL OPERATION
//:AppendSpacesToString( STRING ( 1024 ) szString,
//:                      INTEGER nStringMaxLen )
//:   STRING (  1024  ) szTempString
private int 
omFAProf_AppendSpacesToString( String   szString,
                               int      nStringMaxLen )
{
   String   szTempString = null;
   //:INTEGER nCurrentLength
   int      nCurrentLength = 0;
   //:INTEGER i
   int      i = 0;


   //:szTempString = szString
    {StringBuilder sb_szTempString;
   if ( szTempString == null )
      sb_szTempString = new StringBuilder( 32 );
   else
      sb_szTempString = new StringBuilder( szTempString );
      ZeidonStringCopy( sb_szTempString, 1, 0, szString, 1, 0, 1025 );
   szTempString = sb_szTempString.toString( );}
   //:nCurrentLength = zGetStringLen( szString )
   nCurrentLength = zGetStringLen( szString );
   //:i = nCurrentLength + 1
   i = nCurrentLength + 1;
   //:LOOP WHILE i <=  nStringMaxLen
   while ( i <= nStringMaxLen )
   { 
      //:szTempString[ i : 1 ] = " "
       {StringBuilder sb_szTempString;
      if ( szTempString == null )
         sb_szTempString = new StringBuilder( 32 );
      else
         sb_szTempString = new StringBuilder( szTempString );
            ZeidonStringCopy( sb_szTempString, i, 1, " ", 1, 0, 1025 );
      szTempString = sb_szTempString.toString( );}
      //:i = i + 1
      i = i + 1;
   } 

   //:END
   //:szString = szTempString
    {StringBuilder sb_szString;
   if ( szString == null )
      sb_szString = new StringBuilder( 32 );
   else
      sb_szString = new StringBuilder( szString );
      ZeidonStringCopy( sb_szString, 1, 0, szTempString, 1, 0, 1025 );
   szString = sb_szString.toString( );}
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
public int 
omFAProf_dFullNameLFM( View     mFAProf,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{

   //:dFullNameLFM( VIEW mFAProf BASED ON LOD mFAProf,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_LastFirstMiddle( mFAProf, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mFAProf );
          m_ZGLOBAL1_Operation.PersonName_LastFirstMiddle( mFAProf, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
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
//:BuildCDS_ReptValues( VIEW mFAProf BASED ON LOD mFAProf )

//:   INTEGER lProcessCount
public int 
omFAProf_BuildCDS_ReptValues( View     mFAProf )
{
   int      lProcessCount = 0;
   //:DECIMAL dTotalNeedAmount
   double  dTotalNeedAmount = 0.0;
   //:DECIMAL dCurrentTotalNeedBasedAmount
   double  dCurrentTotalNeedBasedAmount = 0.0;
   //:DECIMAL dOverflowNonNeedBasedAmount
   double  dOverflowNonNeedBasedAmount = 0.0;
   //:DECIMAL dOverflowNeedBasedAmount
   double  dOverflowNeedBasedAmount = 0.0;
   //:DECIMAL dAwardAmount
   double  dAwardAmount = 0.0;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   int      lTempInteger_0 = 0;
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
   double  dTempDecimal_60 = 0.0;
   double  dTempDecimal_61 = 0.0;
   double  dTempDecimal_62 = 0.0;
   double  dTempDecimal_63 = 0.0;
   double  dTempDecimal_64 = 0.0;
   double  dTempDecimal_65 = 0.0;
   double  dTempDecimal_66 = 0.0;
   double  dTempDecimal_67 = 0.0;
   double  dTempDecimal_68 = 0.0;
   double  dTempDecimal_69 = 0.0;
   double  dTempDecimal_70 = 0.0;
   double  dTempDecimal_71 = 0.0;
   double  dTempDecimal_72 = 0.0;
   double  dTempDecimal_73 = 0.0;
   double  dTempDecimal_74 = 0.0;
   double  dTempDecimal_75 = 0.0;
   double  dTempDecimal_76 = 0.0;
   double  dTempDecimal_77 = 0.0;
   double  dTempDecimal_78 = 0.0;
   double  dTempDecimal_79 = 0.0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   double  dTempDecimal_80 = 0.0;
   double  dTempDecimal_81 = 0.0;
   double  dTempDecimal_82 = 0.0;
   double  dTempDecimal_83 = 0.0;
   double  dTempDecimal_84 = 0.0;
   double  dTempDecimal_85 = 0.0;
   double  dTempDecimal_86 = 0.0;
   double  dTempDecimal_87 = 0.0;
   double  dTempDecimal_88 = 0.0;
   double  dTempDecimal_89 = 0.0;
   double  dTempDecimal_90 = 0.0;
   double  dTempDecimal_91 = 0.0;
   double  dTempDecimal_92 = 0.0;
   double  dTempDecimal_93 = 0.0;
   double  dTempDecimal_94 = 0.0;
   double  dTempDecimal_95 = 0.0;
   double  dTempDecimal_96 = 0.0;
   double  dTempDecimal_97 = 0.0;
   double  dTempDecimal_98 = 0.0;
   double  dTempDecimal_99 = 0.0;
   double  dTempDecimal_100 = 0.0;
   double  dTempDecimal_101 = 0.0;
   double  dTempDecimal_102 = 0.0;
   double  dTempDecimal_103 = 0.0;


   //:// Build the CDS Report work entity and values.

   //:// Initialize work entity
   //:IF mFAProf.StuHistSummary EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mFAProf, "StuHistSummary" );
   if ( lTempInteger_0 == 0 )
   { 
      //:DELETE ENTITY mFAProf.StuHistSummary 
      RESULT = DeleteEntity( mFAProf, "StuHistSummary", zPOS_NEXT );
   } 

   //:END
   //:CREATE ENTITY mFAProf.StuHistSummary 
   RESULT = CreateEntity( mFAProf, "StuHistSummary", zPOS_AFTER );

   //:// Set Order in which awards will be processed to determine non-need based moneys applied to need-based totals.
   //:FOR EACH mFAProf.FinAidAward 
   RESULT = mFAProf.cursor( "FinAidAward" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mFAProf.FinAidSource.IsNeedBased = ""
      if ( CompareAttributeToString( mFAProf, "FinAidSource", "IsNeedBased", "" ) == 0 )
      { 
         //:// If it's not need-based, we'll make its order lower in priority.
         //:lProcessCount = 20
         lProcessCount = 20;
         //:ELSE
      } 
      else
      { 
         //:lProcessCount = 0
         lProcessCount = 0;
      } 

      //:END
      //:IF mFAProf.FinAidSource.CDS_ReportingType = "SchGrInst"    // Institutional Grants
      if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "SchGrInst" ) == 0 )
      { 
         //:lProcessCount = lProcessCount + 1
         lProcessCount = lProcessCount + 1;
         //:ELSE
      } 
      else
      { 
         //:IF mFAProf.FinAidSource.CDS_ReportingType = "TuitionWav"   // Tuition Waivers
         if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "TuitionWav" ) == 0 )
         { 
            //:lProcessCount = lProcessCount + 2
            lProcessCount = lProcessCount + 2;
            //:ELSE
         } 
         else
         { 
            //:IF mFAProf.FinAidSource.CDS_ReportingType = "SchGrFed"     // Federal Grants
            if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "SchGrFed" ) == 0 )
            { 
               //:lProcessCount = lProcessCount + 3
               lProcessCount = lProcessCount + 3;
               //:ELSE
            } 
            else
            { 
               //:IF mFAProf.FinAidSource.CDS_ReportingType = "SchGrState"   // State Grants
               if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "SchGrState" ) == 0 )
               { 
                  //:lProcessCount = lProcessCount + 4
                  lProcessCount = lProcessCount + 4;
                  //:ELSE
               } 
               else
               { 
                  //:IF mFAProf.FinAidSource.CDS_ReportingType = "SchGrOther"   // Outside Grants
                  if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "SchGrOther" ) == 0 )
                  { 
                     //:lProcessCount = lProcessCount + 5
                     lProcessCount = lProcessCount + 5;
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF mFAProf.FinAidSource.CDS_ReportingType = "SH_StuLoan"    // Student Loans
                     if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "SH_StuLoan" ) == 0 )
                     { 
                        //:lProcessCount = lProcessCount + 6
                        lProcessCount = lProcessCount + 6;
                        //:ELSE
                     } 
                     else
                     { 
                        //:IF mFAProf.FinAidSource.CDS_ReportingType = "ParLoan"      // Parent Loans
                        if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "ParLoan" ) == 0 )
                        { 
                           //:lProcessCount = lProcessCount + 7
                           lProcessCount = lProcessCount + 7;
                           //:ELSE                                                       // Anything Else
                        } 
                        else
                        { 
                           //:lProcessCount = lProcessCount + 8
                           lProcessCount = lProcessCount + 8;
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
      //:mFAProf.FinAidAward.wNeedBasedReportProcessingOrder = lProcessCount
      SetAttributeFromInteger( mFAProf, "FinAidAward", "wNeedBasedReportProcessingOrder", lProcessCount );
      RESULT = mFAProf.cursor( "FinAidAward" ).setNextContinue().toInt();;
   } 

   //:END
   //:OrderEntityForView( mFAProf, "FinAidAward", "wNeedBasedReportProcessingOrder A" )
   OrderEntityForView( mFAProf, "FinAidAward", "wNeedBasedReportProcessingOrder A" );

   //:// Total values in each CDS and IPEDS category. We need to assign CDS non-need based awards to need-based totals,
   //:// if the total need-based moneys haven't reached the maximum need amount as specified in the
   //:// attribute, dTotalNeed.
   //:// Note that the award entries are in the order of the need-based entries first and then the non-need
   //:// based entries in the proper processing order.
   //:// IPEDS Totals are easier, as we just add them based on Category.
   //:dTotalNeedAmount = mFAProf.FinAidProfile.dTotalNeed
   {MutableDouble md_dTotalNeedAmount = new MutableDouble( dTotalNeedAmount );
       GetDecimalFromAttribute( md_dTotalNeedAmount, mFAProf, "FinAidProfile", "dTotalNeed" );
   dTotalNeedAmount = md_dTotalNeedAmount.doubleValue( );}
   //:dCurrentTotalNeedBasedAmount = 0
   dCurrentTotalNeedBasedAmount = 0;
   //:FOR EACH mFAProf.FinAidAward 
   RESULT = mFAProf.cursor( "FinAidAward" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// CDS TOTALS

      //:// Only process Awarded or Accepted entries.
      //:IF mFAProf.FinAidAward.AwardStatus = "Y" OR mFAProf.FinAidAward.AwardStatus = "A"
      if ( CompareAttributeToString( mFAProf, "FinAidAward", "AwardStatus", "Y" ) == 0 || CompareAttributeToString( mFAProf, "FinAidAward", "AwardStatus", "A" ) == 0 )
      { 
         //:dAwardAmount = mFAProf.FinAidAward.Amount
         {MutableDouble md_dAwardAmount = new MutableDouble( dAwardAmount );
                   GetDecimalFromAttribute( md_dAwardAmount, mFAProf, "FinAidAward", "Amount" );
         dAwardAmount = md_dAwardAmount.doubleValue( );}

         //:IF mFAProf.FinAidSource.CDS_ReportingType = "SchGrInst"    // INSTITUTIONAL GRANTS
         if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "SchGrInst" ) == 0 )
         { 
            //:IF mFAProf.FinAidSource.IsNeedBased = "Y"
            if ( CompareAttributeToString( mFAProf, "FinAidSource", "IsNeedBased", "Y" ) == 0 )
            { 
               //:mFAProf.StuHistSummary.NB_SchGrInstAmount = mFAProf.StuHistSummary.NB_SchGrInstAmount + dAwardAmount
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "StuHistSummary", "NB_SchGrInstAmount" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dTempDecimal_1 = dTempDecimal_0 + dAwardAmount;
               SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SchGrInstAmount", dTempDecimal_1 );
               //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
               dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
               //:ELSE
            } 
            else
            { 
               //:IF dCurrentTotalNeedBasedAmount = dTotalNeedAmount
               if ( dCurrentTotalNeedBasedAmount == dTotalNeedAmount )
               { 
                  //:// Need has been met, so just add amount to non-need.
                  //:mFAProf.StuHistSummary.NNB_SchGrInstAmount = mFAProf.StuHistSummary.NNB_SchGrInstAmount + dAwardAmount
                  {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                                     GetDecimalFromAttribute( md_dTempDecimal_2, mFAProf, "StuHistSummary", "NNB_SchGrInstAmount" );
                  dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
                  dTempDecimal_3 = dTempDecimal_2 + dAwardAmount;
                  SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_SchGrInstAmount", dTempDecimal_3 );
                  //:ELSE
               } 
               else
               { 
                  //:// Need has NOT been met, so add at least some to need-based bucket.
                  //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                  dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                  //:IF dCurrentTotalNeedBasedAmount < dTotalNeedAmount
                  if ( dCurrentTotalNeedBasedAmount < dTotalNeedAmount )
                  { 
                     //:// Put all of it in need-based bucket.
                     //:mFAProf.StuHistSummary.NB_SchGrInstAmount = mFAProf.StuHistSummary.NB_SchGrInstAmount + dAwardAmount
                     {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                                           GetDecimalFromAttribute( md_dTempDecimal_4, mFAProf, "StuHistSummary", "NB_SchGrInstAmount" );
                     dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
                     dTempDecimal_5 = dTempDecimal_4 + dAwardAmount;
                     SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SchGrInstAmount", dTempDecimal_5 );
                     //:ELSE
                  } 
                  else
                  { 
                     //:// Split it between need-based on non-need based buckets.
                     //:dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount
                     dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount;
                     //:dOverflowNeedBasedAmount    = dAwardAmount - dOverflowNonNeedBasedAmount
                     dOverflowNeedBasedAmount = dAwardAmount - dOverflowNonNeedBasedAmount;
                     //:dCurrentTotalNeedBasedAmount = dTotalNeedAmount    // Indicate the need-based amount is fully met.
                     dCurrentTotalNeedBasedAmount = dTotalNeedAmount;
                     //:mFAProf.StuHistSummary.NB_SchGrInstAmount = mFAProf.StuHistSummary.NB_SchGrInstAmount + dOverflowNeedBasedAmount
                     {MutableDouble md_dTempDecimal_6 = new MutableDouble( dTempDecimal_6 );
                                           GetDecimalFromAttribute( md_dTempDecimal_6, mFAProf, "StuHistSummary", "NB_SchGrInstAmount" );
                     dTempDecimal_6 = md_dTempDecimal_6.doubleValue( );}
                     dTempDecimal_7 = dTempDecimal_6 + dOverflowNeedBasedAmount;
                     SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SchGrInstAmount", dTempDecimal_7 );
                     //:mFAProf.StuHistSummary.NNB_SchGrInstAmount = mFAProf.StuHistSummary.NNB_SchGrInstAmount + dOverflowNonNeedBasedAmount
                     {MutableDouble md_dTempDecimal_8 = new MutableDouble( dTempDecimal_8 );
                                           GetDecimalFromAttribute( md_dTempDecimal_8, mFAProf, "StuHistSummary", "NNB_SchGrInstAmount" );
                     dTempDecimal_8 = md_dTempDecimal_8.doubleValue( );}
                     dTempDecimal_9 = dTempDecimal_8 + dOverflowNonNeedBasedAmount;
                     SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_SchGrInstAmount", dTempDecimal_9 );
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

            //:IF mFAProf.FinAidSource.CDS_ReportingType = "TuitionWav"   // TUITION WAIVERS
            if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "TuitionWav" ) == 0 )
            { 
               //:IF mFAProf.FinAidSource.IsNeedBased = "Y"
               if ( CompareAttributeToString( mFAProf, "FinAidSource", "IsNeedBased", "Y" ) == 0 )
               { 
                  //:mFAProf.StuHistSummary.NB_TuitionWavTotalAmount = mFAProf.StuHistSummary.NB_TuitionWavTotalAmount + dAwardAmount
                  {MutableDouble md_dTempDecimal_10 = new MutableDouble( dTempDecimal_10 );
                                     GetDecimalFromAttribute( md_dTempDecimal_10, mFAProf, "StuHistSummary", "NB_TuitionWavTotalAmount" );
                  dTempDecimal_10 = md_dTempDecimal_10.doubleValue( );}
                  dTempDecimal_11 = dTempDecimal_10 + dAwardAmount;
                  SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_TuitionWavTotalAmount", dTempDecimal_11 );
                  //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                  dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                  //:ELSE
               } 
               else
               { 
                  //:IF dCurrentTotalNeedBasedAmount = dTotalNeedAmount
                  if ( dCurrentTotalNeedBasedAmount == dTotalNeedAmount )
                  { 
                     //:// Need has been met, so just add amount to non-need.
                     //:mFAProf.StuHistSummary.NNB_TuitionWavTotalAmount = mFAProf.StuHistSummary.NNB_TuitionWavTotalAmount + dAwardAmount
                     {MutableDouble md_dTempDecimal_12 = new MutableDouble( dTempDecimal_12 );
                                           GetDecimalFromAttribute( md_dTempDecimal_12, mFAProf, "StuHistSummary", "NNB_TuitionWavTotalAmount" );
                     dTempDecimal_12 = md_dTempDecimal_12.doubleValue( );}
                     dTempDecimal_13 = dTempDecimal_12 + dAwardAmount;
                     SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_TuitionWavTotalAmount", dTempDecimal_13 );
                     //:ELSE
                  } 
                  else
                  { 
                     //:// Need has NOT been met, so add at least some to need-based bucket.
                     //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                     dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                     //:IF dCurrentTotalNeedBasedAmount < dTotalNeedAmount
                     if ( dCurrentTotalNeedBasedAmount < dTotalNeedAmount )
                     { 
                        //:// Put all of it in need-based bucket.
                        //:mFAProf.StuHistSummary.NB_TuitionWavTotalAmount = mFAProf.StuHistSummary.NB_TuitionWavTotalAmount + dAwardAmount
                        {MutableDouble md_dTempDecimal_14 = new MutableDouble( dTempDecimal_14 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_14, mFAProf, "StuHistSummary", "NB_TuitionWavTotalAmount" );
                        dTempDecimal_14 = md_dTempDecimal_14.doubleValue( );}
                        dTempDecimal_15 = dTempDecimal_14 + dAwardAmount;
                        SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_TuitionWavTotalAmount", dTempDecimal_15 );
                        //:ELSE
                     } 
                     else
                     { 
                        //:// Split it between need-based on non-need based buckets.
                        //:dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount
                        dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount;
                        //:dOverflowNeedBasedAmount    = dAwardAmount - dOverflowNonNeedBasedAmount
                        dOverflowNeedBasedAmount = dAwardAmount - dOverflowNonNeedBasedAmount;
                        //:dCurrentTotalNeedBasedAmount = dTotalNeedAmount    // Indicate the need-based amount is fully met.
                        dCurrentTotalNeedBasedAmount = dTotalNeedAmount;
                        //:mFAProf.StuHistSummary.NB_TuitionWavTotalAmount = mFAProf.StuHistSummary.NB_TuitionWavTotalAmount + dOverflowNeedBasedAmount
                        {MutableDouble md_dTempDecimal_16 = new MutableDouble( dTempDecimal_16 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_16, mFAProf, "StuHistSummary", "NB_TuitionWavTotalAmount" );
                        dTempDecimal_16 = md_dTempDecimal_16.doubleValue( );}
                        dTempDecimal_17 = dTempDecimal_16 + dOverflowNeedBasedAmount;
                        SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_TuitionWavTotalAmount", dTempDecimal_17 );
                        //:mFAProf.StuHistSummary.NNB_TuitionWavTotalAmount = mFAProf.StuHistSummary.NNB_TuitionWavTotalAmount + dOverflowNonNeedBasedAmount
                        {MutableDouble md_dTempDecimal_18 = new MutableDouble( dTempDecimal_18 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_18, mFAProf, "StuHistSummary", "NNB_TuitionWavTotalAmount" );
                        dTempDecimal_18 = md_dTempDecimal_18.doubleValue( );}
                        dTempDecimal_19 = dTempDecimal_18 + dOverflowNonNeedBasedAmount;
                        SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_TuitionWavTotalAmount", dTempDecimal_19 );
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

               //:IF mFAProf.FinAidSource.CDS_ReportingType = "SchGrFed"     // FEDERAL GRANTS
               if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "SchGrFed" ) == 0 )
               { 
                  //:IF mFAProf.FinAidSource.IsNeedBased = "Y"
                  if ( CompareAttributeToString( mFAProf, "FinAidSource", "IsNeedBased", "Y" ) == 0 )
                  { 
                     //:mFAProf.StuHistSummary.NB_SchGrFedAmount = mFAProf.StuHistSummary.NB_SchGrFedAmount + dAwardAmount
                     {MutableDouble md_dTempDecimal_20 = new MutableDouble( dTempDecimal_20 );
                                           GetDecimalFromAttribute( md_dTempDecimal_20, mFAProf, "StuHistSummary", "NB_SchGrFedAmount" );
                     dTempDecimal_20 = md_dTempDecimal_20.doubleValue( );}
                     dTempDecimal_21 = dTempDecimal_20 + dAwardAmount;
                     SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SchGrFedAmount", dTempDecimal_21 );
                     //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                     dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF dCurrentTotalNeedBasedAmount = dTotalNeedAmount
                     if ( dCurrentTotalNeedBasedAmount == dTotalNeedAmount )
                     { 
                        //:// Need has been met, so just add amount to non-need.
                        //:mFAProf.StuHistSummary.NNB_SchGrFedAmount = mFAProf.StuHistSummary.NNB_SchGrFedAmount + dAwardAmount
                        {MutableDouble md_dTempDecimal_22 = new MutableDouble( dTempDecimal_22 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_22, mFAProf, "StuHistSummary", "NNB_SchGrFedAmount" );
                        dTempDecimal_22 = md_dTempDecimal_22.doubleValue( );}
                        dTempDecimal_23 = dTempDecimal_22 + dAwardAmount;
                        SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_SchGrFedAmount", dTempDecimal_23 );
                        //:ELSE
                     } 
                     else
                     { 
                        //:// Need has NOT been met, so add at least some to need-based bucket.
                        //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                        dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                        //:IF dCurrentTotalNeedBasedAmount < dTotalNeedAmount
                        if ( dCurrentTotalNeedBasedAmount < dTotalNeedAmount )
                        { 
                           //:// Put all of it in need-based bucket.
                           //:mFAProf.StuHistSummary.NB_SchGrFedAmount = mFAProf.StuHistSummary.NB_SchGrFedAmount + dAwardAmount
                           {MutableDouble md_dTempDecimal_24 = new MutableDouble( dTempDecimal_24 );
                                                       GetDecimalFromAttribute( md_dTempDecimal_24, mFAProf, "StuHistSummary", "NB_SchGrFedAmount" );
                           dTempDecimal_24 = md_dTempDecimal_24.doubleValue( );}
                           dTempDecimal_25 = dTempDecimal_24 + dAwardAmount;
                           SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SchGrFedAmount", dTempDecimal_25 );
                           //:ELSE
                        } 
                        else
                        { 
                           //:// Split it between need-based on non-need based buckets.
                           //:dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount
                           dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount;
                           //:dOverflowNeedBasedAmount    = dAwardAmount - dOverflowNonNeedBasedAmount
                           dOverflowNeedBasedAmount = dAwardAmount - dOverflowNonNeedBasedAmount;
                           //:dCurrentTotalNeedBasedAmount = dTotalNeedAmount    // Indicate the need-based amount is fully met.
                           dCurrentTotalNeedBasedAmount = dTotalNeedAmount;
                           //:mFAProf.StuHistSummary.NB_SchGrFedAmount = mFAProf.StuHistSummary.NB_SchGrFedAmount + dOverflowNeedBasedAmount
                           {MutableDouble md_dTempDecimal_26 = new MutableDouble( dTempDecimal_26 );
                                                       GetDecimalFromAttribute( md_dTempDecimal_26, mFAProf, "StuHistSummary", "NB_SchGrFedAmount" );
                           dTempDecimal_26 = md_dTempDecimal_26.doubleValue( );}
                           dTempDecimal_27 = dTempDecimal_26 + dOverflowNeedBasedAmount;
                           SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SchGrFedAmount", dTempDecimal_27 );
                           //:mFAProf.StuHistSummary.NNB_SchGrFedAmount = mFAProf.StuHistSummary.NNB_SchGrFedAmount + dOverflowNonNeedBasedAmount
                           {MutableDouble md_dTempDecimal_28 = new MutableDouble( dTempDecimal_28 );
                                                       GetDecimalFromAttribute( md_dTempDecimal_28, mFAProf, "StuHistSummary", "NNB_SchGrFedAmount" );
                           dTempDecimal_28 = md_dTempDecimal_28.doubleValue( );}
                           dTempDecimal_29 = dTempDecimal_28 + dOverflowNonNeedBasedAmount;
                           SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_SchGrFedAmount", dTempDecimal_29 );
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

                  //:IF mFAProf.FinAidSource.CDS_ReportingType = "SchGrState"   // STATE GRANTS
                  if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "SchGrState" ) == 0 )
                  { 
                     //:IF mFAProf.FinAidSource.IsNeedBased = "Y"
                     if ( CompareAttributeToString( mFAProf, "FinAidSource", "IsNeedBased", "Y" ) == 0 )
                     { 
                        //:mFAProf.StuHistSummary.NB_SchGrStateAmount = mFAProf.StuHistSummary.NB_SchGrStateAmount + dAwardAmount
                        {MutableDouble md_dTempDecimal_30 = new MutableDouble( dTempDecimal_30 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_30, mFAProf, "StuHistSummary", "NB_SchGrStateAmount" );
                        dTempDecimal_30 = md_dTempDecimal_30.doubleValue( );}
                        dTempDecimal_31 = dTempDecimal_30 + dAwardAmount;
                        SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SchGrStateAmount", dTempDecimal_31 );
                        //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                        dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                        //:ELSE
                     } 
                     else
                     { 
                        //:IF dCurrentTotalNeedBasedAmount = dTotalNeedAmount
                        if ( dCurrentTotalNeedBasedAmount == dTotalNeedAmount )
                        { 
                           //:// Need has been met, so just add amount to non-need.
                           //:mFAProf.StuHistSummary.NNB_SchGrStateAmount = mFAProf.StuHistSummary.NNB_SchGrStateAmount + dAwardAmount
                           {MutableDouble md_dTempDecimal_32 = new MutableDouble( dTempDecimal_32 );
                                                       GetDecimalFromAttribute( md_dTempDecimal_32, mFAProf, "StuHistSummary", "NNB_SchGrStateAmount" );
                           dTempDecimal_32 = md_dTempDecimal_32.doubleValue( );}
                           dTempDecimal_33 = dTempDecimal_32 + dAwardAmount;
                           SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_SchGrStateAmount", dTempDecimal_33 );
                           //:ELSE
                        } 
                        else
                        { 
                           //:// Need has NOT been met, so add at least some to need-based bucket.
                           //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                           dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                           //:IF dCurrentTotalNeedBasedAmount < dTotalNeedAmount
                           if ( dCurrentTotalNeedBasedAmount < dTotalNeedAmount )
                           { 
                              //:// Put all of it in need-based bucket.
                              //:mFAProf.StuHistSummary.NB_SchGrStateAmount = mFAProf.StuHistSummary.NB_SchGrStateAmount + dAwardAmount
                              {MutableDouble md_dTempDecimal_34 = new MutableDouble( dTempDecimal_34 );
                                                             GetDecimalFromAttribute( md_dTempDecimal_34, mFAProf, "StuHistSummary", "NB_SchGrStateAmount" );
                              dTempDecimal_34 = md_dTempDecimal_34.doubleValue( );}
                              dTempDecimal_35 = dTempDecimal_34 + dAwardAmount;
                              SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SchGrStateAmount", dTempDecimal_35 );
                              //:ELSE
                           } 
                           else
                           { 
                              //:// Split it between need-based on non-need based buckets.
                              //:dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount
                              dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount;
                              //:dOverflowNeedBasedAmount    = dAwardAmount - dOverflowNonNeedBasedAmount
                              dOverflowNeedBasedAmount = dAwardAmount - dOverflowNonNeedBasedAmount;
                              //:dCurrentTotalNeedBasedAmount = dTotalNeedAmount    // Indicate the need-based amount is fully met.
                              dCurrentTotalNeedBasedAmount = dTotalNeedAmount;
                              //:mFAProf.StuHistSummary.NB_SchGrStateAmount = mFAProf.StuHistSummary.NB_SchGrStateAmount + dOverflowNeedBasedAmount
                              {MutableDouble md_dTempDecimal_36 = new MutableDouble( dTempDecimal_36 );
                                                             GetDecimalFromAttribute( md_dTempDecimal_36, mFAProf, "StuHistSummary", "NB_SchGrStateAmount" );
                              dTempDecimal_36 = md_dTempDecimal_36.doubleValue( );}
                              dTempDecimal_37 = dTempDecimal_36 + dOverflowNeedBasedAmount;
                              SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SchGrStateAmount", dTempDecimal_37 );
                              //:mFAProf.StuHistSummary.NNB_SchGrStateAmount = mFAProf.StuHistSummary.NNB_SchGrStateAmount + dOverflowNonNeedBasedAmount
                              {MutableDouble md_dTempDecimal_38 = new MutableDouble( dTempDecimal_38 );
                                                             GetDecimalFromAttribute( md_dTempDecimal_38, mFAProf, "StuHistSummary", "NNB_SchGrStateAmount" );
                              dTempDecimal_38 = md_dTempDecimal_38.doubleValue( );}
                              dTempDecimal_39 = dTempDecimal_38 + dOverflowNonNeedBasedAmount;
                              SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_SchGrStateAmount", dTempDecimal_39 );
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

                     //:IF mFAProf.FinAidSource.CDS_ReportingType = "SchGrOther"   // OUTSIDE GRANTS
                     if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "SchGrOther" ) == 0 )
                     { 
                        //:IF mFAProf.FinAidSource.IsNeedBased = "Y"
                        if ( CompareAttributeToString( mFAProf, "FinAidSource", "IsNeedBased", "Y" ) == 0 )
                        { 
                           //:mFAProf.StuHistSummary.NB_SchGrOtherAmount = mFAProf.StuHistSummary.NB_SchGrOtherAmount + dAwardAmount
                           {MutableDouble md_dTempDecimal_40 = new MutableDouble( dTempDecimal_40 );
                                                       GetDecimalFromAttribute( md_dTempDecimal_40, mFAProf, "StuHistSummary", "NB_SchGrOtherAmount" );
                           dTempDecimal_40 = md_dTempDecimal_40.doubleValue( );}
                           dTempDecimal_41 = dTempDecimal_40 + dAwardAmount;
                           SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SchGrOtherAmount", dTempDecimal_41 );
                           //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                           dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                           //:ELSE
                        } 
                        else
                        { 
                           //:IF dCurrentTotalNeedBasedAmount = dTotalNeedAmount
                           if ( dCurrentTotalNeedBasedAmount == dTotalNeedAmount )
                           { 
                              //:// Need has been met, so just add amount to non-need.
                              //:mFAProf.StuHistSummary.NNB_SchGrOtherAmount = mFAProf.StuHistSummary.NNB_SchGrOtherAmount + dAwardAmount
                              {MutableDouble md_dTempDecimal_42 = new MutableDouble( dTempDecimal_42 );
                                                             GetDecimalFromAttribute( md_dTempDecimal_42, mFAProf, "StuHistSummary", "NNB_SchGrOtherAmount" );
                              dTempDecimal_42 = md_dTempDecimal_42.doubleValue( );}
                              dTempDecimal_43 = dTempDecimal_42 + dAwardAmount;
                              SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_SchGrOtherAmount", dTempDecimal_43 );
                              //:ELSE
                           } 
                           else
                           { 
                              //:// Need has NOT been met, so add at least some to need-based bucket.
                              //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                              dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                              //:IF dCurrentTotalNeedBasedAmount < dTotalNeedAmount
                              if ( dCurrentTotalNeedBasedAmount < dTotalNeedAmount )
                              { 
                                 //:// Put all of it in need-based bucket.
                                 //:mFAProf.StuHistSummary.NB_SchGrOtherAmount = mFAProf.StuHistSummary.NB_SchGrOtherAmount + dAwardAmount
                                 {MutableDouble md_dTempDecimal_44 = new MutableDouble( dTempDecimal_44 );
                                                                   GetDecimalFromAttribute( md_dTempDecimal_44, mFAProf, "StuHistSummary", "NB_SchGrOtherAmount" );
                                 dTempDecimal_44 = md_dTempDecimal_44.doubleValue( );}
                                 dTempDecimal_45 = dTempDecimal_44 + dAwardAmount;
                                 SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SchGrOtherAmount", dTempDecimal_45 );
                                 //:ELSE
                              } 
                              else
                              { 
                                 //:// Split it between need-based on non-need based buckets.
                                 //:dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount
                                 dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount;
                                 //:dOverflowNeedBasedAmount    = dAwardAmount - dOverflowNonNeedBasedAmount
                                 dOverflowNeedBasedAmount = dAwardAmount - dOverflowNonNeedBasedAmount;
                                 //:dCurrentTotalNeedBasedAmount = dTotalNeedAmount    // Indicate the need-based amount is fully met.
                                 dCurrentTotalNeedBasedAmount = dTotalNeedAmount;
                                 //:mFAProf.StuHistSummary.NB_SchGrOtherAmount = mFAProf.StuHistSummary.NB_SchGrOtherAmount + dOverflowNeedBasedAmount
                                 {MutableDouble md_dTempDecimal_46 = new MutableDouble( dTempDecimal_46 );
                                                                   GetDecimalFromAttribute( md_dTempDecimal_46, mFAProf, "StuHistSummary", "NB_SchGrOtherAmount" );
                                 dTempDecimal_46 = md_dTempDecimal_46.doubleValue( );}
                                 dTempDecimal_47 = dTempDecimal_46 + dOverflowNeedBasedAmount;
                                 SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SchGrOtherAmount", dTempDecimal_47 );
                                 //:mFAProf.StuHistSummary.NNB_SchGrOtherAmount = mFAProf.StuHistSummary.NNB_SchGrOtherAmount + dOverflowNonNeedBasedAmount
                                 {MutableDouble md_dTempDecimal_48 = new MutableDouble( dTempDecimal_48 );
                                                                   GetDecimalFromAttribute( md_dTempDecimal_48, mFAProf, "StuHistSummary", "NNB_SchGrOtherAmount" );
                                 dTempDecimal_48 = md_dTempDecimal_48.doubleValue( );}
                                 dTempDecimal_49 = dTempDecimal_48 + dOverflowNonNeedBasedAmount;
                                 SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_SchGrOtherAmount", dTempDecimal_49 );
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

                        //:IF mFAProf.FinAidSource.CDS_ReportingType = "SHStuLoan"    // STUDENT LOANS
                        if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "SHStuLoan" ) == 0 )
                        { 
                           //:IF mFAProf.FinAidSource.IsNeedBased = "Y"
                           if ( CompareAttributeToString( mFAProf, "FinAidSource", "IsNeedBased", "Y" ) == 0 )
                           { 
                              //:mFAProf.StuHistSummary.NB_SH_StuLoanAmount = mFAProf.StuHistSummary.NB_SH_StuLoanAmount + dAwardAmount
                              {MutableDouble md_dTempDecimal_50 = new MutableDouble( dTempDecimal_50 );
                                                             GetDecimalFromAttribute( md_dTempDecimal_50, mFAProf, "StuHistSummary", "NB_SH_StuLoanAmount" );
                              dTempDecimal_50 = md_dTempDecimal_50.doubleValue( );}
                              dTempDecimal_51 = dTempDecimal_50 + dAwardAmount;
                              SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SH_StuLoanAmount", dTempDecimal_51 );
                              //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                              dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                              //:ELSE
                           } 
                           else
                           { 
                              //:IF dCurrentTotalNeedBasedAmount = dTotalNeedAmount
                              if ( dCurrentTotalNeedBasedAmount == dTotalNeedAmount )
                              { 
                                 //:// Need has been met, so just add amount to non-need.
                                 //:mFAProf.StuHistSummary.NNB_SH_StuLoanAmount = mFAProf.StuHistSummary.NNB_SH_StuLoanAmount + dAwardAmount
                                 {MutableDouble md_dTempDecimal_52 = new MutableDouble( dTempDecimal_52 );
                                                                   GetDecimalFromAttribute( md_dTempDecimal_52, mFAProf, "StuHistSummary", "NNB_SH_StuLoanAmount" );
                                 dTempDecimal_52 = md_dTempDecimal_52.doubleValue( );}
                                 dTempDecimal_53 = dTempDecimal_52 + dAwardAmount;
                                 SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_SH_StuLoanAmount", dTempDecimal_53 );
                                 //:ELSE
                              } 
                              else
                              { 
                                 //:// Need has NOT been met, so add at least some to need-based bucket.
                                 //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                                 dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                                 //:IF dCurrentTotalNeedBasedAmount < dTotalNeedAmount
                                 if ( dCurrentTotalNeedBasedAmount < dTotalNeedAmount )
                                 { 
                                    //:// Put all of it in need-based bucket.
                                    //:mFAProf.StuHistSummary.NB_SH_StuLoanAmount = mFAProf.StuHistSummary.NB_SH_StuLoanAmount + dAwardAmount
                                    {MutableDouble md_dTempDecimal_54 = new MutableDouble( dTempDecimal_54 );
                                                                         GetDecimalFromAttribute( md_dTempDecimal_54, mFAProf, "StuHistSummary", "NB_SH_StuLoanAmount" );
                                    dTempDecimal_54 = md_dTempDecimal_54.doubleValue( );}
                                    dTempDecimal_55 = dTempDecimal_54 + dAwardAmount;
                                    SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SH_StuLoanAmount", dTempDecimal_55 );
                                    //:ELSE
                                 } 
                                 else
                                 { 
                                    //:// Split it between need-based on non-need based buckets.
                                    //:dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount
                                    dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount;
                                    //:dOverflowNeedBasedAmount    = dAwardAmount - dOverflowNonNeedBasedAmount
                                    dOverflowNeedBasedAmount = dAwardAmount - dOverflowNonNeedBasedAmount;
                                    //:dCurrentTotalNeedBasedAmount = dTotalNeedAmount    // Indicate the need-based amount is fully met.
                                    dCurrentTotalNeedBasedAmount = dTotalNeedAmount;
                                    //:mFAProf.StuHistSummary.NB_SH_StuLoanAmount = mFAProf.StuHistSummary.NB_SH_StuLoanAmount + dOverflowNeedBasedAmount
                                    {MutableDouble md_dTempDecimal_56 = new MutableDouble( dTempDecimal_56 );
                                                                         GetDecimalFromAttribute( md_dTempDecimal_56, mFAProf, "StuHistSummary", "NB_SH_StuLoanAmount" );
                                    dTempDecimal_56 = md_dTempDecimal_56.doubleValue( );}
                                    dTempDecimal_57 = dTempDecimal_56 + dOverflowNeedBasedAmount;
                                    SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SH_StuLoanAmount", dTempDecimal_57 );
                                    //:mFAProf.StuHistSummary.NNB_SH_StuLoanAmount = mFAProf.StuHistSummary.NNB_SH_StuLoanAmount + dOverflowNonNeedBasedAmount
                                    {MutableDouble md_dTempDecimal_58 = new MutableDouble( dTempDecimal_58 );
                                                                         GetDecimalFromAttribute( md_dTempDecimal_58, mFAProf, "StuHistSummary", "NNB_SH_StuLoanAmount" );
                                    dTempDecimal_58 = md_dTempDecimal_58.doubleValue( );}
                                    dTempDecimal_59 = dTempDecimal_58 + dOverflowNonNeedBasedAmount;
                                    SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_SH_StuLoanAmount", dTempDecimal_59 );
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

                           //:IF mFAProf.FinAidSource.CDS_ReportingType = "ParLoan"      // PARENT LOANS
                           if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "ParLoan" ) == 0 )
                           { 
                              //:IF mFAProf.FinAidSource.IsNeedBased = "Y"
                              if ( CompareAttributeToString( mFAProf, "FinAidSource", "IsNeedBased", "Y" ) == 0 )
                              { 
                                 //:mFAProf.StuHistSummary.NB_ParLoanTotalAmount = mFAProf.StuHistSummary.NB_ParLoanTotalAmount + dAwardAmount
                                 {MutableDouble md_dTempDecimal_60 = new MutableDouble( dTempDecimal_60 );
                                                                   GetDecimalFromAttribute( md_dTempDecimal_60, mFAProf, "StuHistSummary", "NB_ParLoanTotalAmount" );
                                 dTempDecimal_60 = md_dTempDecimal_60.doubleValue( );}
                                 dTempDecimal_61 = dTempDecimal_60 + dAwardAmount;
                                 SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_ParLoanTotalAmount", dTempDecimal_61 );
                                 //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                                 dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                                 //:ELSE
                              } 
                              else
                              { 
                                 //:IF dCurrentTotalNeedBasedAmount = dTotalNeedAmount
                                 if ( dCurrentTotalNeedBasedAmount == dTotalNeedAmount )
                                 { 
                                    //:// Need has been met, so just add amount to non-need.
                                    //:mFAProf.StuHistSummary.NNB_ParLoanTotalAmount = mFAProf.StuHistSummary.NNB_ParLoanTotalAmount + dAwardAmount
                                    {MutableDouble md_dTempDecimal_62 = new MutableDouble( dTempDecimal_62 );
                                                                         GetDecimalFromAttribute( md_dTempDecimal_62, mFAProf, "StuHistSummary", "NNB_ParLoanTotalAmount" );
                                    dTempDecimal_62 = md_dTempDecimal_62.doubleValue( );}
                                    dTempDecimal_63 = dTempDecimal_62 + dAwardAmount;
                                    SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_ParLoanTotalAmount", dTempDecimal_63 );
                                    //:ELSE
                                 } 
                                 else
                                 { 
                                    //:// Need has NOT been met, so add at least some to need-based bucket.
                                    //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                                    dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                                    //:IF dCurrentTotalNeedBasedAmount < dTotalNeedAmount
                                    if ( dCurrentTotalNeedBasedAmount < dTotalNeedAmount )
                                    { 
                                       //:// Put all of it in need-based bucket.
                                       //:mFAProf.StuHistSummary.NB_ParLoanTotalAmount = mFAProf.StuHistSummary.NB_ParLoanTotalAmount + dAwardAmount
                                       {MutableDouble md_dTempDecimal_64 = new MutableDouble( dTempDecimal_64 );
                                                                               GetDecimalFromAttribute( md_dTempDecimal_64, mFAProf, "StuHistSummary", "NB_ParLoanTotalAmount" );
                                       dTempDecimal_64 = md_dTempDecimal_64.doubleValue( );}
                                       dTempDecimal_65 = dTempDecimal_64 + dAwardAmount;
                                       SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_ParLoanTotalAmount", dTempDecimal_65 );
                                       //:ELSE
                                    } 
                                    else
                                    { 
                                       //:// Split it between need-based on non-need based buckets.
                                       //:dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount
                                       dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount;
                                       //:dOverflowNeedBasedAmount    = dAwardAmount - dOverflowNonNeedBasedAmount
                                       dOverflowNeedBasedAmount = dAwardAmount - dOverflowNonNeedBasedAmount;
                                       //:dCurrentTotalNeedBasedAmount = dTotalNeedAmount    // Indicate the need-based amount is fully met.
                                       dCurrentTotalNeedBasedAmount = dTotalNeedAmount;
                                       //:mFAProf.StuHistSummary.NB_ParLoanTotalAmount = mFAProf.StuHistSummary.NB_ParLoanTotalAmount + dOverflowNeedBasedAmount
                                       {MutableDouble md_dTempDecimal_66 = new MutableDouble( dTempDecimal_66 );
                                                                               GetDecimalFromAttribute( md_dTempDecimal_66, mFAProf, "StuHistSummary", "NB_ParLoanTotalAmount" );
                                       dTempDecimal_66 = md_dTempDecimal_66.doubleValue( );}
                                       dTempDecimal_67 = dTempDecimal_66 + dOverflowNeedBasedAmount;
                                       SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_ParLoanTotalAmount", dTempDecimal_67 );
                                       //:mFAProf.StuHistSummary.NNB_ParLoanTotalAmount = mFAProf.StuHistSummary.NNB_ParLoanTotalAmount + dOverflowNonNeedBasedAmount
                                       {MutableDouble md_dTempDecimal_68 = new MutableDouble( dTempDecimal_68 );
                                                                               GetDecimalFromAttribute( md_dTempDecimal_68, mFAProf, "StuHistSummary", "NNB_ParLoanTotalAmount" );
                                       dTempDecimal_68 = md_dTempDecimal_68.doubleValue( );}
                                       dTempDecimal_69 = dTempDecimal_68 + dOverflowNonNeedBasedAmount;
                                       SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_ParLoanTotalAmount", dTempDecimal_69 );
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

                              //:IF mFAProf.FinAidSource.CDS_ReportingType = "SHFedWS"      // WORK STUDY
                              if ( CompareAttributeToString( mFAProf, "FinAidSource", "CDS_ReportingType", "SHFedWS" ) == 0 )
                              { 
                                 //:IF mFAProf.FinAidSource.IsNeedBased = "Y"
                                 if ( CompareAttributeToString( mFAProf, "FinAidSource", "IsNeedBased", "Y" ) == 0 )
                                 { 
                                    //:mFAProf.StuHistSummary.NB_SH_FedWSAmount = mFAProf.StuHistSummary.NB_SH_FedWSAmount + dAwardAmount
                                    {MutableDouble md_dTempDecimal_70 = new MutableDouble( dTempDecimal_70 );
                                                                         GetDecimalFromAttribute( md_dTempDecimal_70, mFAProf, "StuHistSummary", "NB_SH_FedWSAmount" );
                                    dTempDecimal_70 = md_dTempDecimal_70.doubleValue( );}
                                    dTempDecimal_71 = dTempDecimal_70 + dAwardAmount;
                                    SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SH_FedWSAmount", dTempDecimal_71 );
                                    //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                                    dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                                    //:ELSE
                                 } 
                                 else
                                 { 
                                    //:IF dCurrentTotalNeedBasedAmount = dTotalNeedAmount
                                    if ( dCurrentTotalNeedBasedAmount == dTotalNeedAmount )
                                    { 
                                       //:// Need has been met, so just add amount to non-need.
                                       //:mFAProf.StuHistSummary.NNB_SH_FedWSAmount = mFAProf.StuHistSummary.NNB_SH_FedWSAmount + dAwardAmount
                                       {MutableDouble md_dTempDecimal_72 = new MutableDouble( dTempDecimal_72 );
                                                                               GetDecimalFromAttribute( md_dTempDecimal_72, mFAProf, "StuHistSummary", "NNB_SH_FedWSAmount" );
                                       dTempDecimal_72 = md_dTempDecimal_72.doubleValue( );}
                                       dTempDecimal_73 = dTempDecimal_72 + dAwardAmount;
                                       SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_SH_FedWSAmount", dTempDecimal_73 );
                                       //:ELSE
                                    } 
                                    else
                                    { 
                                       //:// Need has NOT been met, so add at least some to need-based bucket.
                                       //:dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount
                                       dCurrentTotalNeedBasedAmount = dCurrentTotalNeedBasedAmount + dAwardAmount;
                                       //:IF dCurrentTotalNeedBasedAmount < dTotalNeedAmount
                                       if ( dCurrentTotalNeedBasedAmount < dTotalNeedAmount )
                                       { 
                                          //:// Put all of it in need-based bucket.
                                          //:mFAProf.StuHistSummary.NB_SH_FedWSAmount = mFAProf.StuHistSummary.NB_SH_FedWSAmount + dAwardAmount
                                          {MutableDouble md_dTempDecimal_74 = new MutableDouble( dTempDecimal_74 );
                                                                                     GetDecimalFromAttribute( md_dTempDecimal_74, mFAProf, "StuHistSummary", "NB_SH_FedWSAmount" );
                                          dTempDecimal_74 = md_dTempDecimal_74.doubleValue( );}
                                          dTempDecimal_75 = dTempDecimal_74 + dAwardAmount;
                                          SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SH_FedWSAmount", dTempDecimal_75 );
                                          //:ELSE
                                       } 
                                       else
                                       { 
                                          //:// Split it between need-based on non-need based buckets.
                                          //:dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount
                                          dOverflowNonNeedBasedAmount = dCurrentTotalNeedBasedAmount - dTotalNeedAmount;
                                          //:dOverflowNeedBasedAmount    = dAwardAmount - dOverflowNonNeedBasedAmount
                                          dOverflowNeedBasedAmount = dAwardAmount - dOverflowNonNeedBasedAmount;
                                          //:dCurrentTotalNeedBasedAmount = dTotalNeedAmount    // Indicate the need-based amount is fully met.
                                          dCurrentTotalNeedBasedAmount = dTotalNeedAmount;
                                          //:mFAProf.StuHistSummary.NB_SH_FedWSAmount = mFAProf.StuHistSummary.NB_SH_FedWSAmount + dOverflowNeedBasedAmount
                                          {MutableDouble md_dTempDecimal_76 = new MutableDouble( dTempDecimal_76 );
                                                                                     GetDecimalFromAttribute( md_dTempDecimal_76, mFAProf, "StuHistSummary", "NB_SH_FedWSAmount" );
                                          dTempDecimal_76 = md_dTempDecimal_76.doubleValue( );}
                                          dTempDecimal_77 = dTempDecimal_76 + dOverflowNeedBasedAmount;
                                          SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SH_FedWSAmount", dTempDecimal_77 );
                                          //:mFAProf.StuHistSummary.NNB_SH_FedWSAmount = mFAProf.StuHistSummary.NNB_SH_FedWSAmount + dOverflowNonNeedBasedAmount
                                          {MutableDouble md_dTempDecimal_78 = new MutableDouble( dTempDecimal_78 );
                                                                                     GetDecimalFromAttribute( md_dTempDecimal_78, mFAProf, "StuHistSummary", "NNB_SH_FedWSAmount" );
                                          dTempDecimal_78 = md_dTempDecimal_78.doubleValue( );}
                                          dTempDecimal_79 = dTempDecimal_78 + dOverflowNonNeedBasedAmount;
                                          SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_SH_FedWSAmount", dTempDecimal_79 );
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
                                 //:szMsg = "Invalid CDS Reporting Type for FinAid Source, " + mFAProf.FinAidSource.Name 
                                 {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                                 StringBuilder sb_szTempString_0;
                                 if ( szTempString_0 == null )
                                    sb_szTempString_0 = new StringBuilder( 32 );
                                 else
                                    sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                                                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 51, mFAProf, "FinAidSource", "Name", "", 0 );
                                 lTempInteger_1 = mi_lTempInteger_1.intValue( );
                                 szTempString_0 = sb_szTempString_0.toString( );}
                                  {StringBuilder sb_szMsg;
                                 if ( szMsg == null )
                                    sb_szMsg = new StringBuilder( 32 );
                                 else
                                    sb_szMsg = new StringBuilder( szMsg );
                                                                  ZeidonStringCopy( sb_szMsg, 1, 0, "Invalid CDS Reporting Type for FinAid Source, ", 1, 0, 201 );
                                 szMsg = sb_szMsg.toString( );}
                                  {StringBuilder sb_szMsg;
                                 if ( szMsg == null )
                                    sb_szMsg = new StringBuilder( 32 );
                                 else
                                    sb_szMsg = new StringBuilder( szMsg );
                                                                  ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_0, 1, 0, 201 );
                                 szMsg = sb_szMsg.toString( );}
                                 //:MessageSend( mFAProf, "", "Build CDS Report Values", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                                 MessageSend( mFAProf, "", "Build CDS Report Values", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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

         //:END
      } 

      //:END

      //:// IPEDS TOTALS
      //:IF mFAProf.FinAidSource.IPEDS_ReportingType = "Federal"      // FEDERAL
      if ( CompareAttributeToString( mFAProf, "FinAidSource", "IPEDS_ReportingType", "Federal" ) == 0 )
      { 
         //:mFAProf.StuHistSummary.IPEDS_FederalGrant = mFAProf.StuHistSummary.IPEDS_FederalGrant + dAwardAmount
         {MutableDouble md_dTempDecimal_80 = new MutableDouble( dTempDecimal_80 );
                   GetDecimalFromAttribute( md_dTempDecimal_80, mFAProf, "StuHistSummary", "IPEDS_FederalGrant" );
         dTempDecimal_80 = md_dTempDecimal_80.doubleValue( );}
         dTempDecimal_81 = dTempDecimal_80 + dAwardAmount;
         SetAttributeFromDecimal( mFAProf, "StuHistSummary", "IPEDS_FederalGrant", dTempDecimal_81 );
         //:ELSE
      } 
      else
      { 
         //:IF mFAProf.FinAidSource.IPEDS_ReportingType = "State"      // STATE
         if ( CompareAttributeToString( mFAProf, "FinAidSource", "IPEDS_ReportingType", "State" ) == 0 )
         { 
            //:mFAProf.StuHistSummary.IPEDS_StateGrant = mFAProf.StuHistSummary.IPEDS_StateGrant + dAwardAmount
            {MutableDouble md_dTempDecimal_82 = new MutableDouble( dTempDecimal_82 );
                         GetDecimalFromAttribute( md_dTempDecimal_82, mFAProf, "StuHistSummary", "IPEDS_StateGrant" );
            dTempDecimal_82 = md_dTempDecimal_82.doubleValue( );}
            dTempDecimal_83 = dTempDecimal_82 + dAwardAmount;
            SetAttributeFromDecimal( mFAProf, "StuHistSummary", "IPEDS_StateGrant", dTempDecimal_83 );
            //:ELSE
         } 
         else
         { 
            //:IF mFAProf.FinAidSource.IPEDS_ReportingType = "Instit"      // INSTITUTIONAL
            if ( CompareAttributeToString( mFAProf, "FinAidSource", "IPEDS_ReportingType", "Instit" ) == 0 )
            { 
               //:mFAProf.StuHistSummary.IPEDS_InstGrant = mFAProf.StuHistSummary.IPEDS_InstGrant + dAwardAmount
               {MutableDouble md_dTempDecimal_84 = new MutableDouble( dTempDecimal_84 );
                               GetDecimalFromAttribute( md_dTempDecimal_84, mFAProf, "StuHistSummary", "IPEDS_InstGrant" );
               dTempDecimal_84 = md_dTempDecimal_84.doubleValue( );}
               dTempDecimal_85 = dTempDecimal_84 + dAwardAmount;
               SetAttributeFromDecimal( mFAProf, "StuHistSummary", "IPEDS_InstGrant", dTempDecimal_85 );
               //:ELSE
            } 
            else
            { 
               //:IF mFAProf.FinAidSource.IPEDS_ReportingType = "Loans"      // LOANS
               if ( CompareAttributeToString( mFAProf, "FinAidSource", "IPEDS_ReportingType", "Loans" ) == 0 )
               { 
                  //:mFAProf.StuHistSummary.IPEDS_Loan = mFAProf.StuHistSummary.IPEDS_Loan + dAwardAmount
                  {MutableDouble md_dTempDecimal_86 = new MutableDouble( dTempDecimal_86 );
                                     GetDecimalFromAttribute( md_dTempDecimal_86, mFAProf, "StuHistSummary", "IPEDS_Loan" );
                  dTempDecimal_86 = md_dTempDecimal_86.doubleValue( );}
                  dTempDecimal_87 = dTempDecimal_86 + dAwardAmount;
                  SetAttributeFromDecimal( mFAProf, "StuHistSummary", "IPEDS_Loan", dTempDecimal_87 );
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      RESULT = mFAProf.cursor( "FinAidAward" ).setNextContinue().toInt();;
      //:END
   } 

   //:   
   //:END

   //:// Set totals.
   //:mFAProf.StuHistSummary.NB_SchGrTotalAmount = mFAProf.StuHistSummary.NB_SchGrFedAmount +
   //:                                             mFAProf.StuHistSummary.NB_SchGrStateAmount +
   //:                                             mFAProf.StuHistSummary.NB_SchGrInstAmount +
   //:                                             mFAProf.StuHistSummary.NB_SchGrOtherAmount 
   {MutableDouble md_dTempDecimal_88 = new MutableDouble( dTempDecimal_88 );
       GetDecimalFromAttribute( md_dTempDecimal_88, mFAProf, "StuHistSummary", "NB_SchGrFedAmount" );
   dTempDecimal_88 = md_dTempDecimal_88.doubleValue( );}
   {MutableDouble md_dTempDecimal_89 = new MutableDouble( dTempDecimal_89 );
       GetDecimalFromAttribute( md_dTempDecimal_89, mFAProf, "StuHistSummary", "NB_SchGrStateAmount" );
   dTempDecimal_89 = md_dTempDecimal_89.doubleValue( );}
   {MutableDouble md_dTempDecimal_90 = new MutableDouble( dTempDecimal_90 );
       GetDecimalFromAttribute( md_dTempDecimal_90, mFAProf, "StuHistSummary", "NB_SchGrInstAmount" );
   dTempDecimal_90 = md_dTempDecimal_90.doubleValue( );}
   {MutableDouble md_dTempDecimal_91 = new MutableDouble( dTempDecimal_91 );
       GetDecimalFromAttribute( md_dTempDecimal_91, mFAProf, "StuHistSummary", "NB_SchGrOtherAmount" );
   dTempDecimal_91 = md_dTempDecimal_91.doubleValue( );}
   dTempDecimal_92 = dTempDecimal_88 + dTempDecimal_89 + dTempDecimal_90 + dTempDecimal_91;
   SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SchGrTotalAmount", dTempDecimal_92 );

   //:mFAProf.StuHistSummary.NB_SH_TotalAmount   = mFAProf.StuHistSummary.NB_SH_StuLoanAmount +
   //:                                             mFAProf.StuHistSummary.NB_SH_FedWSAmount 
   {MutableDouble md_dTempDecimal_93 = new MutableDouble( dTempDecimal_93 );
       GetDecimalFromAttribute( md_dTempDecimal_93, mFAProf, "StuHistSummary", "NB_SH_StuLoanAmount" );
   dTempDecimal_93 = md_dTempDecimal_93.doubleValue( );}
   {MutableDouble md_dTempDecimal_94 = new MutableDouble( dTempDecimal_94 );
       GetDecimalFromAttribute( md_dTempDecimal_94, mFAProf, "StuHistSummary", "NB_SH_FedWSAmount" );
   dTempDecimal_94 = md_dTempDecimal_94.doubleValue( );}
   dTempDecimal_95 = dTempDecimal_93 + dTempDecimal_94;
   SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NB_SH_TotalAmount", dTempDecimal_95 );

   //:mFAProf.StuHistSummary.NNB_SchGrTotalAmount = mFAProf.StuHistSummary.NNB_SchGrFedAmount +
   //:                                              mFAProf.StuHistSummary.NNB_SchGrStateAmount +
   //:                                              mFAProf.StuHistSummary.NNB_SchGrInstAmount +
   //:                                              mFAProf.StuHistSummary.NNB_SchGrOtherAmount 
   {MutableDouble md_dTempDecimal_96 = new MutableDouble( dTempDecimal_96 );
       GetDecimalFromAttribute( md_dTempDecimal_96, mFAProf, "StuHistSummary", "NNB_SchGrFedAmount" );
   dTempDecimal_96 = md_dTempDecimal_96.doubleValue( );}
   {MutableDouble md_dTempDecimal_97 = new MutableDouble( dTempDecimal_97 );
       GetDecimalFromAttribute( md_dTempDecimal_97, mFAProf, "StuHistSummary", "NNB_SchGrStateAmount" );
   dTempDecimal_97 = md_dTempDecimal_97.doubleValue( );}
   {MutableDouble md_dTempDecimal_98 = new MutableDouble( dTempDecimal_98 );
       GetDecimalFromAttribute( md_dTempDecimal_98, mFAProf, "StuHistSummary", "NNB_SchGrInstAmount" );
   dTempDecimal_98 = md_dTempDecimal_98.doubleValue( );}
   {MutableDouble md_dTempDecimal_99 = new MutableDouble( dTempDecimal_99 );
       GetDecimalFromAttribute( md_dTempDecimal_99, mFAProf, "StuHistSummary", "NNB_SchGrOtherAmount" );
   dTempDecimal_99 = md_dTempDecimal_99.doubleValue( );}
   dTempDecimal_100 = dTempDecimal_96 + dTempDecimal_97 + dTempDecimal_98 + dTempDecimal_99;
   SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_SchGrTotalAmount", dTempDecimal_100 );

   //:mFAProf.StuHistSummary.NNB_SH_TotalAmount   = mFAProf.StuHistSummary.NNB_SH_StuLoanAmount +
   //:                                              mFAProf.StuHistSummary.NNB_SH_FedWSAmount 
   {MutableDouble md_dTempDecimal_101 = new MutableDouble( dTempDecimal_101 );
       GetDecimalFromAttribute( md_dTempDecimal_101, mFAProf, "StuHistSummary", "NNB_SH_StuLoanAmount" );
   dTempDecimal_101 = md_dTempDecimal_101.doubleValue( );}
   {MutableDouble md_dTempDecimal_102 = new MutableDouble( dTempDecimal_102 );
       GetDecimalFromAttribute( md_dTempDecimal_102, mFAProf, "StuHistSummary", "NNB_SH_FedWSAmount" );
   dTempDecimal_102 = md_dTempDecimal_102.doubleValue( );}
   dTempDecimal_103 = dTempDecimal_101 + dTempDecimal_102;
   SetAttributeFromDecimal( mFAProf, "StuHistSummary", "NNB_SH_TotalAmount", dTempDecimal_103 );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateAwardsByGroup( VIEW mFAProf BASED ON LOD mFAProf )

//:   VIEW mAdmDiv  BASED ON LOD  mAdmDiv
public int 
omFAProf_GenerateAwardsByGroup( View     mFAProf )
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
   //:STRING ( 14 ) szAmount
   String   szAmount = null;
   //:STRING ( 14 ) szFootnoteNumber
   String   szFootnoteNumber = null;
   //:STRING ( 14 ) szSomeNumber
   String   szSomeNumber = null;
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
   //:STRING ( 1 )   szProvider
   String   szProvider = null;
   //:STRING ( 1 )   szLastProvider
   String   szLastProvider = null;
   //:INTEGER nFNNumber
   int      nFNNumber = 0;
   //:INTEGER nLen
   int      nLen = 0;
   //:DECIMAL dParentStudentLoan
   double  dParentStudentLoan = 0.0;
   //:DECIMAL dParentStudentLoanHalf
   double  dParentStudentLoanHalf = 0.0;
   //:DECIMAL dGroupTotal
   double  dGroupTotal = 0.0;
   //:DECIMAL dSpringTotal
   double  dSpringTotal = 0.0;
   //:DECIMAL dFallTotal
   double  dFallTotal = 0.0;
   //:DECIMAL dGroupAllTotal
   double  dGroupAllTotal = 0.0;
   //:DECIMAL dSpringAllTotal
   double  dSpringAllTotal = 0.0;
   //:DECIMAL dFallAllTotal
   double  dFallAllTotal = 0.0;
   //:DECIMAL dTotalFinAidAwardAmount
   double  dTotalFinAidAwardAmount = 0.0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   String   szTempString_0 = null;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;
   double  dTempDecimal_4 = 0.0;
   double  dTempDecimal_5 = 0.0;
   double  dTempDecimal_6 = 0.0;
   double  dTempDecimal_7 = 0.0;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   double  dTempDecimal_8 = 0.0;
   double  dTempDecimal_9 = 0.0;
   double  dTempDecimal_10 = 0.0;
   double  dTempDecimal_11 = 0.0;
   double  dTempDecimal_12 = 0.0;
   double  dTempDecimal_13 = 0.0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   String   szTempString_3 = null;
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   String   szTempString_6 = null;
   String   szTempString_7 = null;
   String   szTempString_8 = null;
   double  dTempDecimal_14 = 0.0;
   int      lTempInteger_5 = 0;
   String   szTempString_9 = null;

   RESULT = GetViewByName( wXferO, "wXferO", mFAProf, zLEVEL_TASK );

   //:IF  mFAProf.AwardList EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mFAProf, "AwardList" );
   if ( lTempInteger_0 == 0 )
   { 
      //:DELETE ENTITY  mFAProf.AwardList NONE  
      RESULT = DeleteEntity( mFAProf, "AwardList", zREPOS_NONE );
   } 

   //:END

   //:IF  mFAProf.AwardDisbursement EXISTS
   lTempInteger_1 = CheckExistenceOfEntity( mFAProf, "AwardDisbursement" );
   if ( lTempInteger_1 == 0 )
   { 
      //:DELETE ENTITY  mFAProf.AwardDisbursement NONE  
      RESULT = DeleteEntity( mFAProf, "AwardDisbursement", zREPOS_NONE );
   } 

   //:END

   //:// Generate a list of Requirements that have been met and that are yet to be accomplished.
   //:GET VIEW mAdmDiv NAMED "mAdmDiv"
   RESULT = GetViewByName( mAdmDiv, "mAdmDiv", mFAProf, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 

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

      //://CREATE ENTITY mFAProf.AwardLetters 
      //:OrderEntityForView( mFAProf, "FinAidAward", "FinAidSource.Provider A" )
      OrderEntityForView( mFAProf, "FinAidAward", "FinAidSource.Provider A" );
      //:szLastProvider = ""
       {StringBuilder sb_szLastProvider;
      if ( szLastProvider == null )
         sb_szLastProvider = new StringBuilder( 32 );
      else
         sb_szLastProvider = new StringBuilder( szLastProvider );
            ZeidonStringCopy( sb_szLastProvider, 1, 0, "", 1, 0, 2 );
      szLastProvider = sb_szLastProvider.toString( );}
      //: 
      //:// Generate Awards as either awarded or declined.
      //:FOR EACH mFAProf.FinAidAward 
      RESULT = mFAProf.cursor( "FinAidAward" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 

         //:// If the award is declined, create a FinAidDeclined entity instead.
         //://IF mFAProf.FinAidAward.AwardStatus = "N"
         //://
         //://   // KJS 04/29/09 - Do we care about this?????  I'm taking this code out for now.
         //://   // The Award IS declined.
         //://   CREATE ENTITY mFAProf.FinAidDeclined  
         //://   mFAProf.FinAidDeclined.AwardName = mFAProf.FinAidSource.Name
         //://   mFAProf.FinAidDeclined.Amount    = mFAProf.FinAidAward.Amount
         //://   
         //://ELSE
         //:// KJS 04/29/09 - I have deleted FinAidAwards where the awardstatus is not "Y" or "A" in wStudntD as well.  But
         //:// I don't want to take this "IF" out yet in case this would get called from somewhere else.
         //:IF mFAProf.FinAidAward.AwardStatus = "Y" OR  mFAProf.FinAidAward.AwardStatus = "A"
         if ( CompareAttributeToString( mFAProf, "FinAidAward", "AwardStatus", "Y" ) == 0 || CompareAttributeToString( mFAProf, "FinAidAward", "AwardStatus", "A" ) == 0 )
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

            //:CREATE ENTITY mFAProf.FinAidAwarded 
            RESULT = CreateEntity( mFAProf, "FinAidAwarded", zPOS_AFTER );
            //:szAwardName = mFAProf.FinAidSource.Name
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szAwardName;
            if ( szAwardName == null )
               sb_szAwardName = new StringBuilder( 32 );
            else
               sb_szAwardName = new StringBuilder( szAwardName );
                         GetVariableFromAttribute( sb_szAwardName, mi_lTempInteger_2, 'S', 255, mFAProf, "FinAidSource", "Name", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szAwardName = sb_szAwardName.toString( );}
            //:mFAProf.FinAidAwarded.AwardName = szAwardName 
            SetAttributeFromString( mFAProf, "FinAidAwarded", "AwardName", szAwardName );

            //:// Compute Award Amount to take into account that some parts of the Award may be swapped, but we
            //:// want those swapped entries added back into the total, since we're not showing swapped entries in
            //:// this award structure.
            //:// Changed from using OriginalAmountOffered on 8/3/2010 by DonC
            //:dTotalFinAidAwardAmount = 0
            dTotalFinAidAwardAmount = 0;
            //:FOR EACH mFAProf.FinAidAwardDisbursement 
            RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:dTotalFinAidAwardAmount = dTotalFinAidAwardAmount + mFAProf.FinAidAwardDisbursement.AmountExpected 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "FinAidAwardDisbursement", "AmountExpected" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dTotalFinAidAwardAmount = dTotalFinAidAwardAmount + dTempDecimal_0;
               //:FOR EACH mFAProf.SwappedToDisbursement 
               RESULT = mFAProf.cursor( "SwappedToDisbursement" ).setFirst().toInt();
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:dTotalFinAidAwardAmount = dTotalFinAidAwardAmount + mFAProf.SwappedToDisbursement.AmountExpected
                  {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                                     GetDecimalFromAttribute( md_dTempDecimal_1, mFAProf, "SwappedToDisbursement", "AmountExpected" );
                  dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
                  dTotalFinAidAwardAmount = dTotalFinAidAwardAmount + dTempDecimal_1;
                  RESULT = mFAProf.cursor( "SwappedToDisbursement" ).setNextContinue().toInt();;
               } 

               RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setNextContinue().toInt();;
               //:END
            } 

            //:END
            //:mFAProf.FinAidAwarded.Amount = dTotalFinAidAwardAmount
            SetAttributeFromDecimal( mFAProf, "FinAidAwarded", "Amount", dTotalFinAidAwardAmount );

            //://mFAProf.FinAidAwarded.Amount = mFAProf.FinAidAward.OriginalAmountOffered  
            //:GetStringFromAttributeByContext( szAmount, mFAProf, "FinAidAwarded", "Amount", "Revenue",10)
            {StringBuilder sb_szAmount;
            if ( szAmount == null )
               sb_szAmount = new StringBuilder( 32 );
            else
               sb_szAmount = new StringBuilder( szAmount );
                         GetStringFromAttributeByContext( sb_szAmount, mFAProf, "FinAidAwarded", "Amount", "Revenue", 10 );
            szAmount = sb_szAmount.toString( );}
            //://GetStringFromAttributeByContext( szSomeNumber, mFAProf, "FinAidAwarded", "Amount", "Revenue",10)
            //://nLen = zstrlen( szSomeNumber ) //szAmount
            //://ConvertToString10( szSomeNumber, szAmount )

            //:// Compute totals for each Period and then set Fall and Spring values from those totals.
            //:FOR EACH mFAProf.FinAidAwardDisbursement
            RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:SET CURSOR FIRST mFAProf.PeriodTotals 
               //:           WHERE mFAProf.PeriodTotals.PeriodDesignator = mFAProf.FinAidAwardPeriod.PeriodDesignator  
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, mFAProf, "FinAidAwardPeriod", "PeriodDesignator" );
               szTempString_0 = sb_szTempString_0.toString( );}
               RESULT = mFAProf.cursor( "PeriodTotals" ).setFirst( "PeriodDesignator", szTempString_0 ).toInt();
               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:CREATE ENTITY mFAProf.PeriodTotals 
                  RESULT = CreateEntity( mFAProf, "PeriodTotals", zPOS_AFTER );
                  //:mFAProf.PeriodTotals.PeriodDesignator = mFAProf.FinAidAwardPeriod.PeriodDesignator   
                  SetAttributeFromAttribute( mFAProf, "PeriodTotals", "PeriodDesignator", mFAProf, "FinAidAwardPeriod", "PeriodDesignator" );
               } 

               //:END
               //://mFAProf.PeriodTotals.TotalAmountExp = mFAProf.PeriodTotals.TotalAmountExp + 
               //://                                      mFAProf.FinAidAwardDisbursement.OriginalAmountExpected  
               //:mFAProf.PeriodTotals.TotalAmountExp = mFAProf.PeriodTotals.TotalAmountExp + 
               //:                                      mFAProf.FinAidAwardDisbursement.dTotalAmountExpected  
               {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                               GetDecimalFromAttribute( md_dTempDecimal_2, mFAProf, "PeriodTotals", "TotalAmountExp" );
               dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
               {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                               GetDecimalFromAttribute( md_dTempDecimal_3, mFAProf, "FinAidAwardDisbursement", "dTotalAmountExpected" );
               dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
               dTempDecimal_4 = dTempDecimal_2 + dTempDecimal_3;
               SetAttributeFromDecimal( mFAProf, "PeriodTotals", "TotalAmountExp", dTempDecimal_4 );
               //://mFAProf.PeriodTotals.TotalAmount = mFAProf.PeriodTotals.TotalAmount + 
               //://                                   mFAProf.FinAidAwardDisbursement.Amount 
               //:mFAProf.PeriodTotals.TotalAmount = mFAProf.PeriodTotals.TotalAmount + 
               //:                                   mFAProf.FinAidAwardDisbursement.dTotalDisbursment 
               {MutableDouble md_dTempDecimal_5 = new MutableDouble( dTempDecimal_5 );
                               GetDecimalFromAttribute( md_dTempDecimal_5, mFAProf, "PeriodTotals", "TotalAmount" );
               dTempDecimal_5 = md_dTempDecimal_5.doubleValue( );}
               {MutableDouble md_dTempDecimal_6 = new MutableDouble( dTempDecimal_6 );
                               GetDecimalFromAttribute( md_dTempDecimal_6, mFAProf, "FinAidAwardDisbursement", "dTotalDisbursment" );
               dTempDecimal_6 = md_dTempDecimal_6.doubleValue( );}
               dTempDecimal_7 = dTempDecimal_5 + dTempDecimal_6;
               SetAttributeFromDecimal( mFAProf, "PeriodTotals", "TotalAmount", dTempDecimal_7 );
               RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setNextContinue().toInt();;
            } 

            //:END
            //:OrderEntityForView( mFAProf, "PeriodTotals", "PeriodDesignator A" )
            OrderEntityForView( mFAProf, "PeriodTotals", "PeriodDesignator A" );
            //:SET CURSOR FIRST mFAProf.PeriodTotals
            RESULT = mFAProf.cursor( "PeriodTotals" ).setFirst().toInt();
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:mFAProf.FinAidAwarded.FallAmountExp = mFAProf.PeriodTotals.TotalAmountExp
               SetAttributeFromAttribute( mFAProf, "FinAidAwarded", "FallAmountExp", mFAProf, "PeriodTotals", "TotalAmountExp" );
               //:mFAProf.FinAidAwarded.FallAmount = mFAProf.PeriodTotals.TotalAmount
               SetAttributeFromAttribute( mFAProf, "FinAidAwarded", "FallAmount", mFAProf, "PeriodTotals", "TotalAmount" );
               //:GetStringFromAttributeByContext( szFallAmount, mFAProf, "FinAidAwarded", "FallAmountExp", "Revenue",10)
               {StringBuilder sb_szFallAmount;
               if ( szFallAmount == null )
                  sb_szFallAmount = new StringBuilder( 32 );
               else
                  sb_szFallAmount = new StringBuilder( szFallAmount );
                               GetStringFromAttributeByContext( sb_szFallAmount, mFAProf, "FinAidAwarded", "FallAmountExp", "Revenue", 10 );
               szFallAmount = sb_szFallAmount.toString( );}
               //://GetStringFromAttributeByContext( szSomeNumber, mFAProf, "FinAidAwarded", "FallAmount", "Revenue",10)
               //://nLen = zstrlen( szSomeNumber ) //szFallAmount
               //://ConvertToString10( szSomeNumber, szFallAmount )
               //:SET CURSOR NEXT mFAProf.PeriodTotals 
               RESULT = mFAProf.cursor( "PeriodTotals" ).setNextContinue().toInt();;
               //:IF RESULT >= zCURSOR_SET 
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:mFAProf.FinAidAwarded.SpringAmountExp = mFAProf.PeriodTotals.TotalAmountExp
                  SetAttributeFromAttribute( mFAProf, "FinAidAwarded", "SpringAmountExp", mFAProf, "PeriodTotals", "TotalAmountExp" );
                  //:mFAProf.FinAidAwarded.SpringAmount = mFAProf.PeriodTotals.TotalAmount
                  SetAttributeFromAttribute( mFAProf, "FinAidAwarded", "SpringAmount", mFAProf, "PeriodTotals", "TotalAmount" );
                  //:GetStringFromAttributeByContext( szSpringAmount, mFAProf, "FinAidAwarded", "SpringAmountExp", "Revenue",10)
                  {StringBuilder sb_szSpringAmount;
                  if ( szSpringAmount == null )
                     sb_szSpringAmount = new StringBuilder( 32 );
                  else
                     sb_szSpringAmount = new StringBuilder( szSpringAmount );
                                     GetStringFromAttributeByContext( sb_szSpringAmount, mFAProf, "FinAidAwarded", "SpringAmountExp", "Revenue", 10 );
                  szSpringAmount = sb_szSpringAmount.toString( );}
               } 

               //:   //GetStringFromAttributeByContext( szSomeNumber, mFAProf, "FinAidAwarded", "SpringAmount", "Revenue",10)
               //:   //nLen = zstrlen( szSomeNumber ) //szSpringAmount
               //:   //ConvertToString10( szSomeNumber, szSpringAmount )
               //:END
            } 

            //:END
            //:  
            //:/*    
            //:szFootnote = mFAProf.FinAidSource.SourceFootnote
            //:IF mFAProf.ReceivedItems EXISTS AND mAdmDiv.FA_FinAidRequirement EXISTS
            //:   mFAProf.ReceivedItems.Description = mAdmDiv.FA_FinAidRequirement.Name
            //:END
            //:IF szFootnote != ""
            //:   nFNNumber = nFNNumber + 1
            //:   zIntegerToString( szFootnoteNumber, 10, nFNNumber )
            //:   szAwardName = szAwardName + "* " + szFootnoteNumber
            //:   mFAProf.FinAidAwarded.AwardName = szAwardName 
            //:   CREATE ENTITY mFAProf.Footnote
            //:   mFAProf.Footnote.SourceFootnote = szFootnoteNumber + ": " + szFootnote
            //:   mFAProf.Footnote.AwardName = mFAProf.FinAidAwarded.AwardName  
            //:   szFootnote = ""
            //:END
            //:*/

            //:// Build Award Groups by Federal Aid, Institutional Aid and Other Aid.
            //:IF mFAProf.FinAidAward.AwardStatus = "Y" 
            if ( CompareAttributeToString( mFAProf, "FinAidAward", "AwardStatus", "Y" ) == 0 )
            { 
               //:szAcceptedDeclinedText = "Accepted"
                {StringBuilder sb_szAcceptedDeclinedText;
               if ( szAcceptedDeclinedText == null )
                  sb_szAcceptedDeclinedText = new StringBuilder( 32 );
               else
                  sb_szAcceptedDeclinedText = new StringBuilder( szAcceptedDeclinedText );
                              ZeidonStringCopy( sb_szAcceptedDeclinedText, 1, 0, "Accepted", 1, 0, 21 );
               szAcceptedDeclinedText = sb_szAcceptedDeclinedText.toString( );}
               //:ELSE
            } 
            else
            { 
               //:IF mFAProf.FinAidAward.AwardStatus = "N" 
               if ( CompareAttributeToString( mFAProf, "FinAidAward", "AwardStatus", "N" ) == 0 )
               { 
                  //:szAcceptedDeclinedText = "         Declined"
                   {StringBuilder sb_szAcceptedDeclinedText;
                  if ( szAcceptedDeclinedText == null )
                     sb_szAcceptedDeclinedText = new StringBuilder( 32 );
                  else
                     sb_szAcceptedDeclinedText = new StringBuilder( szAcceptedDeclinedText );
                                    ZeidonStringCopy( sb_szAcceptedDeclinedText, 1, 0, "         Declined", 1, 0, 21 );
                  szAcceptedDeclinedText = sb_szAcceptedDeclinedText.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szAcceptedDeclinedText = "_______  _______"
                   {StringBuilder sb_szAcceptedDeclinedText;
                  if ( szAcceptedDeclinedText == null )
                     sb_szAcceptedDeclinedText = new StringBuilder( 32 );
                  else
                     sb_szAcceptedDeclinedText = new StringBuilder( szAcceptedDeclinedText );
                                    ZeidonStringCopy( sb_szAcceptedDeclinedText, 1, 0, "_______  _______", 1, 0, 21 );
                  szAcceptedDeclinedText = sb_szAcceptedDeclinedText.toString( );}
               } 

               //:END
            } 

            //:END

            //:IF  szLastProvider != mFAProf.FinAidSource.Provider 
            if ( CompareAttributeToString( mFAProf, "FinAidSource", "Provider", szLastProvider ) != 0 )
            { 

               //:CREATE ENTITY mFAProf.AwardList LAST
               RESULT = CreateEntity( mFAProf, "AwardList", zPOS_LAST );
               //:mFAProf.AwardList.Provider = mFAProf.FinAidSource.Provider
               SetAttributeFromAttribute( mFAProf, "AwardList", "Provider", mFAProf, "FinAidSource", "Provider" );

               //:IF mFAProf.FinAidSource.Provider = "G"
               if ( CompareAttributeToString( mFAProf, "FinAidSource", "Provider", "G" ) == 0 )
               { 
                  //:mFAProf.AwardList.GroupName = "Federal Aid"
                  SetAttributeFromString( mFAProf, "AwardList", "GroupName", "Federal Aid" );
               } 

               //:END 
               //:IF mFAProf.FinAidSource.Provider = "I"
               if ( CompareAttributeToString( mFAProf, "FinAidSource", "Provider", "I" ) == 0 )
               { 
                  //:mFAProf.AwardList.GroupName = "Institutional Aid"
                  SetAttributeFromString( mFAProf, "AwardList", "GroupName", "Institutional Aid" );
               } 

               //:END 
               //:IF mFAProf.FinAidSource.Provider = "O"
               if ( CompareAttributeToString( mFAProf, "FinAidSource", "Provider", "O" ) == 0 )
               { 
                  //:mFAProf.AwardList.GroupName = "Other Aid"
                  SetAttributeFromString( mFAProf, "AwardList", "GroupName", "Other Aid" );
               } 

               //:END 

               //:IF  szLastProvider != ""
               if ( ZeidonStringCompare( szLastProvider, 1, 0, "", 1, 0, 2 ) != 0 )
               { 

                  //:SET CURSOR  FIRST mFAProf.AwardList WHERE mFAProf.AwardList.Provider = szLastProvider
                  RESULT = mFAProf.cursor( "AwardList" ).setFirst( "Provider", szLastProvider ).toInt();
                  //:mFAProf.AwardList.Amount = dGroupTotal
                  SetAttributeFromDecimal( mFAProf, "AwardList", "Amount", dGroupTotal );
                  //:mFAProf.AwardList.SpringAmount = dSpringTotal
                  SetAttributeFromDecimal( mFAProf, "AwardList", "SpringAmount", dSpringTotal );
                  //:mFAProf.AwardList.FallAmount = dFallTotal
                  SetAttributeFromDecimal( mFAProf, "AwardList", "FallAmount", dFallTotal );

                  //:GetStringFromAttributeByContext( szString, mFAProf, "AwardList", "Amount", "Revenue", 30 )  
                  {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                     GetStringFromAttributeByContext( sb_szString, mFAProf, "AwardList", "Amount", "Revenue", 30 );
                  szString = sb_szString.toString( );}
                  //:mFAProf.AwardList.AmountTxt = "<b>" + szString + "</b>"
                   {StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                    ZeidonStringCopy( sb_szTempString_0, 1, 0, "<b>", 1, 0, 255 );
                  szTempString_0 = sb_szTempString_0.toString( );}
                   {StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                    ZeidonStringConcat( sb_szTempString_0, 1, 0, szString, 1, 0, 255 );
                  szTempString_0 = sb_szTempString_0.toString( );}
                   {StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                    ZeidonStringConcat( sb_szTempString_0, 1, 0, "</b>", 1, 0, 255 );
                  szTempString_0 = sb_szTempString_0.toString( );}
                  SetAttributeFromString( mFAProf, "AwardList", "AmountTxt", szTempString_0 );
                  //:GetStringFromAttributeByContext( szString, mFAProf, "AwardList", "FallAmount", "Revenue", 30 ) 
                  {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                     GetStringFromAttributeByContext( sb_szString, mFAProf, "AwardList", "FallAmount", "Revenue", 30 );
                  szString = sb_szString.toString( );}
                  //:mFAProf.AwardList.FallTxt = "<b>" + szString + "</b>"
                   {StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                    ZeidonStringCopy( sb_szTempString_1, 1, 0, "<b>", 1, 0, 255 );
                  szTempString_1 = sb_szTempString_1.toString( );}
                   {StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                    ZeidonStringConcat( sb_szTempString_1, 1, 0, szString, 1, 0, 255 );
                  szTempString_1 = sb_szTempString_1.toString( );}
                   {StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                    ZeidonStringConcat( sb_szTempString_1, 1, 0, "</b>", 1, 0, 255 );
                  szTempString_1 = sb_szTempString_1.toString( );}
                  SetAttributeFromString( mFAProf, "AwardList", "FallTxt", szTempString_1 );
                  //:GetStringFromAttributeByContext( szString, mFAProf, "AwardList", "SpringAmount", "Revenue", 30 ) 
                  {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                     GetStringFromAttributeByContext( sb_szString, mFAProf, "AwardList", "SpringAmount", "Revenue", 30 );
                  szString = sb_szString.toString( );}
                  //:mFAProf.AwardList.SpringTxt = "<b>" + szString + "</b>"
                   {StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                    ZeidonStringCopy( sb_szTempString_2, 1, 0, "<b>", 1, 0, 255 );
                  szTempString_2 = sb_szTempString_2.toString( );}
                   {StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                    ZeidonStringConcat( sb_szTempString_2, 1, 0, szString, 1, 0, 255 );
                  szTempString_2 = sb_szTempString_2.toString( );}
                   {StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                    ZeidonStringConcat( sb_szTempString_2, 1, 0, "</b>", 1, 0, 255 );
                  szTempString_2 = sb_szTempString_2.toString( );}
                  SetAttributeFromString( mFAProf, "AwardList", "SpringTxt", szTempString_2 );
                  //:      
                  //:dGroupTotal = 0
                  dGroupTotal = 0;
                  //:dSpringTotal = 0
                  dSpringTotal = 0;
                  //:dFallTotal = 0
                  dFallTotal = 0;
               } 

               //:END
            } 

            //:END

            //:CREATE ENTITY  mFAProf.AwardList LAST
            RESULT = CreateEntity( mFAProf, "AwardList", zPOS_LAST );
            //:mFAProf.AwardList.AwardName = mFAProf.FinAidSource.Name  
            SetAttributeFromAttribute( mFAProf, "AwardList", "AwardName", mFAProf, "FinAidSource", "Name" );
            //:mFAProf.AwardList.Amount   = dTotalFinAidAwardAmount      // Computed above.
            SetAttributeFromDecimal( mFAProf, "AwardList", "Amount", dTotalFinAidAwardAmount );
            //:mFAProf.AwardList.SpringAmount  = mFAProf.FinAidAwarded.SpringAmountExp 
            SetAttributeFromAttribute( mFAProf, "AwardList", "SpringAmount", mFAProf, "FinAidAwarded", "SpringAmountExp" );
            //:mFAProf.AwardList.FallAmount    = mFAProf.FinAidAwarded.FallAmountExp 
            SetAttributeFromAttribute( mFAProf, "AwardList", "FallAmount", mFAProf, "FinAidAwarded", "FallAmountExp" );
            //:mFAProf.AwardList.AwardStatus = mFAProf.FinAidAward.AwardStatus 
            SetAttributeFromAttribute( mFAProf, "AwardList", "AwardStatus", mFAProf, "FinAidAward", "AwardStatus" );
            //:mFAProf.AwardList.AcceptedDeclinedText = szAcceptedDeclinedText
            SetAttributeFromString( mFAProf, "AwardList", "AcceptedDeclinedText", szAcceptedDeclinedText );

            //:GetStringFromAttributeByContext( szString, mFAProf, "AwardList", "Amount", "Revenue", 30 )  
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttributeByContext( sb_szString, mFAProf, "AwardList", "Amount", "Revenue", 30 );
            szString = sb_szString.toString( );}
            //:mFAProf.AwardList.AmountTxt = szString
            SetAttributeFromString( mFAProf, "AwardList", "AmountTxt", szString );
            //:GetStringFromAttributeByContext( szString, mFAProf, "AwardList", "FallAmount", "Revenue", 30 )
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttributeByContext( sb_szString, mFAProf, "AwardList", "FallAmount", "Revenue", 30 );
            szString = sb_szString.toString( );}
            //:mFAProf.AwardList.FallTxt = szString
            SetAttributeFromString( mFAProf, "AwardList", "FallTxt", szString );
            //:GetStringFromAttributeByContext( szString, mFAProf, "AwardList", "SpringAmount", "Revenue", 30 )
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttributeByContext( sb_szString, mFAProf, "AwardList", "SpringAmount", "Revenue", 30 );
            szString = sb_szString.toString( );}
            //:mFAProf.AwardList.SpringTxt = szString
            SetAttributeFromString( mFAProf, "AwardList", "SpringTxt", szString );

            //:// Create display entities to show the dispersment showing both fall and spring numbers together
            //:// on the same row.
            //:CREATE ENTITY  mFAProf.AwardDisbursement LAST
            RESULT = CreateEntity( mFAProf, "AwardDisbursement", zPOS_LAST );
            //:mFAProf.AwardDisbursement.AwardName = mFAProf.FinAidSource.Name  
            SetAttributeFromAttribute( mFAProf, "AwardDisbursement", "AwardName", mFAProf, "FinAidSource", "Name" );
            //:mFAProf.AwardDisbursement.SpringAmount  = mFAProf.FinAidAwarded.SpringAmount 
            SetAttributeFromAttribute( mFAProf, "AwardDisbursement", "SpringAmount", mFAProf, "FinAidAwarded", "SpringAmount" );
            //:mFAProf.AwardDisbursement.FallAmount    = mFAProf.FinAidAwarded.FallAmount 
            SetAttributeFromAttribute( mFAProf, "AwardDisbursement", "FallAmount", mFAProf, "FinAidAwarded", "FallAmount" );
            //:mFAProf.AwardDisbursement.SpringAmountExp  = mFAProf.FinAidAwarded.SpringAmountExp 
            SetAttributeFromAttribute( mFAProf, "AwardDisbursement", "SpringAmountExp", mFAProf, "FinAidAwarded", "SpringAmountExp" );
            //:mFAProf.AwardDisbursement.FallAmountExp    = mFAProf.FinAidAwarded.FallAmountExp 
            SetAttributeFromAttribute( mFAProf, "AwardDisbursement", "FallAmountExp", mFAProf, "FinAidAwarded", "FallAmountExp" );
            //:mFAProf.AwardDisbursement.AwardStatus = mFAProf.FinAidAward.AwardStatus 
            SetAttributeFromAttribute( mFAProf, "AwardDisbursement", "AwardStatus", mFAProf, "FinAidAward", "AwardStatus" );
            //:mFAProf.AwardDisbursement.AcceptedDeclinedText = szAcceptedDeclinedText
            SetAttributeFromString( mFAProf, "AwardDisbursement", "AcceptedDeclinedText", szAcceptedDeclinedText );

            //:GetStringFromAttributeByContext( szString, mFAProf, "AwardDisbursement", "FallAmount", "Revenue", 30 )
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttributeByContext( sb_szString, mFAProf, "AwardDisbursement", "FallAmount", "Revenue", 30 );
            szString = sb_szString.toString( );}
            //:mFAProf.AwardDisbursement.FallTxt = szString
            SetAttributeFromString( mFAProf, "AwardDisbursement", "FallTxt", szString );
            //:GetStringFromAttributeByContext( szString, mFAProf, "AwardDisbursement", "SpringAmount", "Revenue", 30 )
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttributeByContext( sb_szString, mFAProf, "AwardDisbursement", "SpringAmount", "Revenue", 30 );
            szString = sb_szString.toString( );}
            //:mFAProf.AwardDisbursement.SpringTxt = szString
            SetAttributeFromString( mFAProf, "AwardDisbursement", "SpringTxt", szString );

            //:dGroupTotal   = dGroupTotal + mFAProf.FinAidAwarded.Amount 
            {MutableDouble md_dTempDecimal_8 = new MutableDouble( dTempDecimal_8 );
                         GetDecimalFromAttribute( md_dTempDecimal_8, mFAProf, "FinAidAwarded", "Amount" );
            dTempDecimal_8 = md_dTempDecimal_8.doubleValue( );}
            dGroupTotal = dGroupTotal + dTempDecimal_8;
            //:dSpringTotal  = dSpringTotal + mFAProf.FinAidAwarded.SpringAmountExp 
            {MutableDouble md_dTempDecimal_9 = new MutableDouble( dTempDecimal_9 );
                         GetDecimalFromAttribute( md_dTempDecimal_9, mFAProf, "FinAidAwarded", "SpringAmountExp" );
            dTempDecimal_9 = md_dTempDecimal_9.doubleValue( );}
            dSpringTotal = dSpringTotal + dTempDecimal_9;
            //:dFallTotal    = dFallTotal + mFAProf.FinAidAwarded.FallAmountExp 
            {MutableDouble md_dTempDecimal_10 = new MutableDouble( dTempDecimal_10 );
                         GetDecimalFromAttribute( md_dTempDecimal_10, mFAProf, "FinAidAwarded", "FallAmountExp" );
            dTempDecimal_10 = md_dTempDecimal_10.doubleValue( );}
            dFallTotal = dFallTotal + dTempDecimal_10;

            //:dGroupAllTotal   = dGroupAllTotal + mFAProf.FinAidAwarded.Amount 
            {MutableDouble md_dTempDecimal_11 = new MutableDouble( dTempDecimal_11 );
                         GetDecimalFromAttribute( md_dTempDecimal_11, mFAProf, "FinAidAwarded", "Amount" );
            dTempDecimal_11 = md_dTempDecimal_11.doubleValue( );}
            dGroupAllTotal = dGroupAllTotal + dTempDecimal_11;
            //:dSpringAllTotal  = dSpringAllTotal + mFAProf.FinAidAwarded.SpringAmountExp 
            {MutableDouble md_dTempDecimal_12 = new MutableDouble( dTempDecimal_12 );
                         GetDecimalFromAttribute( md_dTempDecimal_12, mFAProf, "FinAidAwarded", "SpringAmountExp" );
            dTempDecimal_12 = md_dTempDecimal_12.doubleValue( );}
            dSpringAllTotal = dSpringAllTotal + dTempDecimal_12;
            //:dFallAllTotal    = dFallAllTotal + mFAProf.FinAidAwarded.FallAmountExp 
            {MutableDouble md_dTempDecimal_13 = new MutableDouble( dTempDecimal_13 );
                         GetDecimalFromAttribute( md_dTempDecimal_13, mFAProf, "FinAidAwarded", "FallAmountExp" );
            dTempDecimal_13 = md_dTempDecimal_13.doubleValue( );}
            dFallAllTotal = dFallAllTotal + dTempDecimal_13;

            //:szLastProvider = mFAProf.FinAidSource.Provider
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szLastProvider;
            if ( szLastProvider == null )
               sb_szLastProvider = new StringBuilder( 32 );
            else
               sb_szLastProvider = new StringBuilder( szLastProvider );
                         GetVariableFromAttribute( sb_szLastProvider, mi_lTempInteger_3, 'S', 2, mFAProf, "FinAidSource", "Provider", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szLastProvider = sb_szLastProvider.toString( );}
         } 

         RESULT = mFAProf.cursor( "FinAidAward" ).setNextContinue().toInt();;

         //:END  //if award is declined
      } 

      //:END

      //:IF mFAProf.AwardList EXISTS 
      lTempInteger_4 = CheckExistenceOfEntity( mFAProf, "AwardList" );
      if ( lTempInteger_4 == 0 )
      { 

         //:SET CURSOR  FIRST mFAProf.AwardList WHERE mFAProf.AwardList.Provider = szLastProvider
         RESULT = mFAProf.cursor( "AwardList" ).setFirst( "Provider", szLastProvider ).toInt();
         //:mFAProf.AwardList.Amount = dGroupTotal
         SetAttributeFromDecimal( mFAProf, "AwardList", "Amount", dGroupTotal );
         //:mFAProf.AwardList.SpringAmount = dSpringTotal
         SetAttributeFromDecimal( mFAProf, "AwardList", "SpringAmount", dSpringTotal );
         //:mFAProf.AwardList.FallAmount = dFallTotal
         SetAttributeFromDecimal( mFAProf, "AwardList", "FallAmount", dFallTotal );
         //:                 
         //:GetStringFromAttributeByContext( szString, mFAProf, "AwardList", "Amount", "Revenue", 30 )  
         {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetStringFromAttributeByContext( sb_szString, mFAProf, "AwardList", "Amount", "Revenue", 30 );
         szString = sb_szString.toString( );}
         //:mFAProf.AwardList.AmountTxt = "<b>" + szString + "</b>"
          {StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                  ZeidonStringCopy( sb_szTempString_3, 1, 0, "<b>", 1, 0, 255 );
         szTempString_3 = sb_szTempString_3.toString( );}
          {StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                  ZeidonStringConcat( sb_szTempString_3, 1, 0, szString, 1, 0, 255 );
         szTempString_3 = sb_szTempString_3.toString( );}
          {StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                  ZeidonStringConcat( sb_szTempString_3, 1, 0, "</b>", 1, 0, 255 );
         szTempString_3 = sb_szTempString_3.toString( );}
         SetAttributeFromString( mFAProf, "AwardList", "AmountTxt", szTempString_3 );
         //:GetStringFromAttributeByContext( szString, mFAProf, "AwardList", "FallAmount", "Revenue", 30 )
         {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetStringFromAttributeByContext( sb_szString, mFAProf, "AwardList", "FallAmount", "Revenue", 30 );
         szString = sb_szString.toString( );}
         //:mFAProf.AwardList.FallTxt = "<b>" + szString + "</b>"
          {StringBuilder sb_szTempString_4;
         if ( szTempString_4 == null )
            sb_szTempString_4 = new StringBuilder( 32 );
         else
            sb_szTempString_4 = new StringBuilder( szTempString_4 );
                  ZeidonStringCopy( sb_szTempString_4, 1, 0, "<b>", 1, 0, 255 );
         szTempString_4 = sb_szTempString_4.toString( );}
          {StringBuilder sb_szTempString_4;
         if ( szTempString_4 == null )
            sb_szTempString_4 = new StringBuilder( 32 );
         else
            sb_szTempString_4 = new StringBuilder( szTempString_4 );
                  ZeidonStringConcat( sb_szTempString_4, 1, 0, szString, 1, 0, 255 );
         szTempString_4 = sb_szTempString_4.toString( );}
          {StringBuilder sb_szTempString_4;
         if ( szTempString_4 == null )
            sb_szTempString_4 = new StringBuilder( 32 );
         else
            sb_szTempString_4 = new StringBuilder( szTempString_4 );
                  ZeidonStringConcat( sb_szTempString_4, 1, 0, "</b>", 1, 0, 255 );
         szTempString_4 = sb_szTempString_4.toString( );}
         SetAttributeFromString( mFAProf, "AwardList", "FallTxt", szTempString_4 );
         //:GetStringFromAttributeByContext( szString, mFAProf, "AwardList", "SpringAmount", "Revenue", 30 )
         {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetStringFromAttributeByContext( sb_szString, mFAProf, "AwardList", "SpringAmount", "Revenue", 30 );
         szString = sb_szString.toString( );}
         //:mFAProf.AwardList.SpringTxt = "<b>" + szString + "</b>"
          {StringBuilder sb_szTempString_5;
         if ( szTempString_5 == null )
            sb_szTempString_5 = new StringBuilder( 32 );
         else
            sb_szTempString_5 = new StringBuilder( szTempString_5 );
                  ZeidonStringCopy( sb_szTempString_5, 1, 0, "<b>", 1, 0, 255 );
         szTempString_5 = sb_szTempString_5.toString( );}
          {StringBuilder sb_szTempString_5;
         if ( szTempString_5 == null )
            sb_szTempString_5 = new StringBuilder( 32 );
         else
            sb_szTempString_5 = new StringBuilder( szTempString_5 );
                  ZeidonStringConcat( sb_szTempString_5, 1, 0, szString, 1, 0, 255 );
         szTempString_5 = sb_szTempString_5.toString( );}
          {StringBuilder sb_szTempString_5;
         if ( szTempString_5 == null )
            sb_szTempString_5 = new StringBuilder( 32 );
         else
            sb_szTempString_5 = new StringBuilder( szTempString_5 );
                  ZeidonStringConcat( sb_szTempString_5, 1, 0, "</b>", 1, 0, 255 );
         szTempString_5 = sb_szTempString_5.toString( );}
         SetAttributeFromString( mFAProf, "AwardList", "SpringTxt", szTempString_5 );

         //:CREATE ENTITY  mFAProf.AwardList FIRST
         RESULT = CreateEntity( mFAProf, "AwardList", zPOS_FIRST );
         //:mFAProf.AwardList.GroupName    = "<b>Total</b>"
         SetAttributeFromString( mFAProf, "AwardList", "GroupName", "<b>Total</b>" );
         //:mFAProf.AwardList.Amount       = dGroupAllTotal 
         SetAttributeFromDecimal( mFAProf, "AwardList", "Amount", dGroupAllTotal );
         //:mFAProf.AwardList.SpringAmount = dSpringAllTotal 
         SetAttributeFromDecimal( mFAProf, "AwardList", "SpringAmount", dSpringAllTotal );
         //:mFAProf.AwardList.FallAmount   = dFallAllTotal 
         SetAttributeFromDecimal( mFAProf, "AwardList", "FallAmount", dFallAllTotal );
         //:                 
         //:GetStringFromAttributeByContext( szString, mFAProf, "AwardList", "Amount", "Revenue", 30 )  
         {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetStringFromAttributeByContext( sb_szString, mFAProf, "AwardList", "Amount", "Revenue", 30 );
         szString = sb_szString.toString( );}
         //:mFAProf.AwardList.AmountTxt = "<b>" + szString + "</b>"
          {StringBuilder sb_szTempString_6;
         if ( szTempString_6 == null )
            sb_szTempString_6 = new StringBuilder( 32 );
         else
            sb_szTempString_6 = new StringBuilder( szTempString_6 );
                  ZeidonStringCopy( sb_szTempString_6, 1, 0, "<b>", 1, 0, 255 );
         szTempString_6 = sb_szTempString_6.toString( );}
          {StringBuilder sb_szTempString_6;
         if ( szTempString_6 == null )
            sb_szTempString_6 = new StringBuilder( 32 );
         else
            sb_szTempString_6 = new StringBuilder( szTempString_6 );
                  ZeidonStringConcat( sb_szTempString_6, 1, 0, szString, 1, 0, 255 );
         szTempString_6 = sb_szTempString_6.toString( );}
          {StringBuilder sb_szTempString_6;
         if ( szTempString_6 == null )
            sb_szTempString_6 = new StringBuilder( 32 );
         else
            sb_szTempString_6 = new StringBuilder( szTempString_6 );
                  ZeidonStringConcat( sb_szTempString_6, 1, 0, "</b>", 1, 0, 255 );
         szTempString_6 = sb_szTempString_6.toString( );}
         SetAttributeFromString( mFAProf, "AwardList", "AmountTxt", szTempString_6 );
         //:GetStringFromAttributeByContext( szString, mFAProf, "AwardList", "FallAmount", "Revenue", 30 )
         {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetStringFromAttributeByContext( sb_szString, mFAProf, "AwardList", "FallAmount", "Revenue", 30 );
         szString = sb_szString.toString( );}
         //:mFAProf.AwardList.FallTxt = "<b>" + szString + "</b>"
          {StringBuilder sb_szTempString_7;
         if ( szTempString_7 == null )
            sb_szTempString_7 = new StringBuilder( 32 );
         else
            sb_szTempString_7 = new StringBuilder( szTempString_7 );
                  ZeidonStringCopy( sb_szTempString_7, 1, 0, "<b>", 1, 0, 255 );
         szTempString_7 = sb_szTempString_7.toString( );}
          {StringBuilder sb_szTempString_7;
         if ( szTempString_7 == null )
            sb_szTempString_7 = new StringBuilder( 32 );
         else
            sb_szTempString_7 = new StringBuilder( szTempString_7 );
                  ZeidonStringConcat( sb_szTempString_7, 1, 0, szString, 1, 0, 255 );
         szTempString_7 = sb_szTempString_7.toString( );}
          {StringBuilder sb_szTempString_7;
         if ( szTempString_7 == null )
            sb_szTempString_7 = new StringBuilder( 32 );
         else
            sb_szTempString_7 = new StringBuilder( szTempString_7 );
                  ZeidonStringConcat( sb_szTempString_7, 1, 0, "</b>", 1, 0, 255 );
         szTempString_7 = sb_szTempString_7.toString( );}
         SetAttributeFromString( mFAProf, "AwardList", "FallTxt", szTempString_7 );
         //:GetStringFromAttributeByContext( szString, mFAProf, "AwardList", "SpringAmount", "Revenue", 30 )
         {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetStringFromAttributeByContext( sb_szString, mFAProf, "AwardList", "SpringAmount", "Revenue", 30 );
         szString = sb_szString.toString( );}
         //:mFAProf.AwardList.SpringTxt = "<b>" + szString + "</b>"
          {StringBuilder sb_szTempString_8;
         if ( szTempString_8 == null )
            sb_szTempString_8 = new StringBuilder( 32 );
         else
            sb_szTempString_8 = new StringBuilder( szTempString_8 );
                  ZeidonStringCopy( sb_szTempString_8, 1, 0, "<b>", 1, 0, 255 );
         szTempString_8 = sb_szTempString_8.toString( );}
          {StringBuilder sb_szTempString_8;
         if ( szTempString_8 == null )
            sb_szTempString_8 = new StringBuilder( 32 );
         else
            sb_szTempString_8 = new StringBuilder( szTempString_8 );
                  ZeidonStringConcat( sb_szTempString_8, 1, 0, szString, 1, 0, 255 );
         szTempString_8 = sb_szTempString_8.toString( );}
          {StringBuilder sb_szTempString_8;
         if ( szTempString_8 == null )
            sb_szTempString_8 = new StringBuilder( 32 );
         else
            sb_szTempString_8 = new StringBuilder( szTempString_8 );
                  ZeidonStringConcat( sb_szTempString_8, 1, 0, "</b>", 1, 0, 255 );
         szTempString_8 = sb_szTempString_8.toString( );}
         SetAttributeFromString( mFAProf, "AwardList", "SpringTxt", szTempString_8 );
      } 


      //:END

      //:dGroupAllTotal = 0
      dGroupAllTotal = 0;

      //:FOR EACH mFAProf.FinAidCOAItemAssigned 
      RESULT = mFAProf.cursor( "FinAidCOAItemAssigned" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 

         //:CREATE ENTITY  mFAProf.COAList 
         RESULT = CreateEntity( mFAProf, "COAList", zPOS_AFTER );

         //:mFAProf.COAList.Description = mFAProf.FinAidCOAItem.Description 
         SetAttributeFromAttribute( mFAProf, "COAList", "Description", mFAProf, "FinAidCOAItem", "Description" );
         //:mFAProf.COAList.COAItemType = mFAProf.FinAidCOAItem.COAItemType 
         SetAttributeFromAttribute( mFAProf, "COAList", "COAItemType", mFAProf, "FinAidCOAItem", "COAItemType" );
         //:mFAProf.COAList.RevenueAmount = mFAProf.FinAidCOAItemAssigned.RevenueAmount 
         SetAttributeFromAttribute( mFAProf, "COAList", "RevenueAmount", mFAProf, "FinAidCOAItemAssigned", "RevenueAmount" );
         //:dGroupAllTotal = dGroupAllTotal + mFAProf.FinAidCOAItemAssigned.RevenueAmount
         {MutableDouble md_dTempDecimal_14 = new MutableDouble( dTempDecimal_14 );
                   GetDecimalFromAttribute( md_dTempDecimal_14, mFAProf, "FinAidCOAItemAssigned", "RevenueAmount" );
         dTempDecimal_14 = md_dTempDecimal_14.doubleValue( );}
         dGroupAllTotal = dGroupAllTotal + dTempDecimal_14;
         //:GetStringFromAttributeByContext( szCOANumber, mFAProf, "FinAidCOAItemAssigned", "RevenueAmount", "Revenue",10)
         {StringBuilder sb_szCOANumber;
         if ( szCOANumber == null )
            sb_szCOANumber = new StringBuilder( 32 );
         else
            sb_szCOANumber = new StringBuilder( szCOANumber );
                   GetStringFromAttributeByContext( sb_szCOANumber, mFAProf, "FinAidCOAItemAssigned", "RevenueAmount", "Revenue", 10 );
         szCOANumber = sb_szCOANumber.toString( );}
         //:mFAProf.COAList.RevenueTxt = szCOANumber
         SetAttributeFromString( mFAProf, "COAList", "RevenueTxt", szCOANumber );

         //://szCOAString = szCOAName + szCOANumber
         //://mFAProf.FinAidCOAItemAssigned.COAString = szCOAString 

         //:// Add to Totals
         //:IF mFAProf.FinAidCOAItem.COAItemType = "EDU" OR mFAProf.FinAidCOAItem.COAItemType = "FEE"
         if ( CompareAttributeToString( mFAProf, "FinAidCOAItem", "COAItemType", "EDU" ) == 0 || CompareAttributeToString( mFAProf, "FinAidCOAItem", "COAItemType", "FEE" ) == 0 )
         { 
         } 

         //:   //mFAProf.AwardLetters.TuitionAndFeesTotal = mFAProf.AwardLetters.TuitionAndFeesTotal + mFAProf.FinAidCOAItemAssigned.RevenueAmount 
         //:END
         //:IF mFAProf.FinAidCOAItem.COAItemType = "COL"
         if ( CompareAttributeToString( mFAProf, "FinAidCOAItem", "COAItemType", "COL" ) == 0 )
         { 
         } 

         RESULT = mFAProf.cursor( "FinAidCOAItemAssigned" ).setNextContinue().toInt();;
         //:   //mFAProf.AwardLetters.CostOfLivingTotal = mFAProf.AwardLetters.CostOfLivingTotal + mFAProf.FinAidCOAItemAssigned.RevenueAmount 
         //:END
      } 

      //:   
      //:END

      //:IF mFAProf.COAList EXISTS 
      lTempInteger_5 = CheckExistenceOfEntity( mFAProf, "COAList" );
      if ( lTempInteger_5 == 0 )
      { 
         //:CREATE ENTITY  mFAProf.COAList first
         RESULT = CreateEntity( mFAProf, "COAList", zPOS_FIRST );
         //:mFAProf.COAList.Description = "Total" 
         SetAttributeFromString( mFAProf, "COAList", "Description", "Total" );
         //:mFAProf.COAList.RevenueAmount = dGroupAllTotal 
         SetAttributeFromDecimal( mFAProf, "COAList", "RevenueAmount", dGroupAllTotal );
         //:GetStringFromAttributeByContext( szCOANumber, mFAProf, "COAList", "RevenueAmount", "Revenue",10)
         {StringBuilder sb_szCOANumber;
         if ( szCOANumber == null )
            sb_szCOANumber = new StringBuilder( 32 );
         else
            sb_szCOANumber = new StringBuilder( szCOANumber );
                   GetStringFromAttributeByContext( sb_szCOANumber, mFAProf, "COAList", "RevenueAmount", "Revenue", 10 );
         szCOANumber = sb_szCOANumber.toString( );}
         //:mFAProf.COAList.RevenueTxt = "<b>" + szCOANumber + "</b>"
          {StringBuilder sb_szTempString_9;
         if ( szTempString_9 == null )
            sb_szTempString_9 = new StringBuilder( 32 );
         else
            sb_szTempString_9 = new StringBuilder( szTempString_9 );
                  ZeidonStringCopy( sb_szTempString_9, 1, 0, "<b>", 1, 0, 255 );
         szTempString_9 = sb_szTempString_9.toString( );}
          {StringBuilder sb_szTempString_9;
         if ( szTempString_9 == null )
            sb_szTempString_9 = new StringBuilder( 32 );
         else
            sb_szTempString_9 = new StringBuilder( szTempString_9 );
                  ZeidonStringConcat( sb_szTempString_9, 1, 0, szCOANumber, 1, 0, 255 );
         szTempString_9 = sb_szTempString_9.toString( );}
          {StringBuilder sb_szTempString_9;
         if ( szTempString_9 == null )
            sb_szTempString_9 = new StringBuilder( 32 );
         else
            sb_szTempString_9 = new StringBuilder( szTempString_9 );
                  ZeidonStringConcat( sb_szTempString_9, 1, 0, "</b>", 1, 0, 255 );
         szTempString_9 = sb_szTempString_9.toString( );}
         SetAttributeFromString( mFAProf, "COAList", "RevenueTxt", szTempString_9 );


         //://mFAProf.AwardLetters.CostOfAttendanceTotal = mFAProf.AwardLetters.TuitionAndFeesTotal + mFAProf.AwardLetters.CostOfLivingTotal  
         //://CREATE ENTITY mFAProf.FinAidCOAItemAssigned  
         //://mFAProf.FinAidCOAItemAssigned.COAString = zTAB + zTAB + zTAB + zTAB + zTAB + "____________"

         //://CREATE ENTITY mFAProf.FinAidCOAItemAssigned  
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
         //://                               mFAProf.FinAidProfile.dTotalCOA, "D")
         //:wXferO.Root.WorkRevenue = mFAProf.FinAidProfile.dTotalCOA
         SetAttributeFromAttribute( wXferO, "Root", "WorkRevenue", mFAProf, "FinAidProfile", "dTotalCOA" );
         //:GetStringFromAttributeByContext( szCOANumber, wXferO, "Root", "WorkRevenue", "Revenue",10)
         {StringBuilder sb_szCOANumber;
         if ( szCOANumber == null )
            sb_szCOANumber = new StringBuilder( 32 );
         else
            sb_szCOANumber = new StringBuilder( szCOANumber );
                   GetStringFromAttributeByContext( sb_szCOANumber, wXferO, "Root", "WorkRevenue", "Revenue", 10 );
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
      } 

      //:   //mFAProf.FinAidCOAItemAssigned.COAString = szCOAString
      //:END

      //:// On the "Summary" tab we want to show disbursments that have not yet been dispersed.
      //:// I don't think we use this path for anything else so hide those that have been dispersed.
      //:// Not sure if I should put this here or in wStudntD.vml.
      //:FOR mFAProf.PerPeriodFinAidAwardDisbursement WITHIN mFAProf.FinAidProfile 
      RESULT = CheckExistenceOfEntity( mFAProf, "PerPeriodFinAidAwardDisbursement" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //://IF mFAProf.PerPeriodFinAidAwardDisbursement.AppliedToAccount = "" OR mFAProf.PerPeriodFinAidAwardDisbursement.Amount  = ""
         //:IF mFAProf.PerPeriodFinAidAwardDisbursement.Amount  = ""
         if ( CompareAttributeToString( mFAProf, "PerPeriodFinAidAwardDisbursement", "Amount", "" ) == 0 )
         { 
            //:mFAProf.PerPeriodFinAidAwardDisbursement.wHideTableRow = ""
            SetAttributeFromString( mFAProf, "PerPeriodFinAidAwardDisbursement", "wHideTableRow", "" );
            //:ELSE
         } 
         else
         { 
            //:mFAProf.PerPeriodFinAidAwardDisbursement.wHideTableRow = "Y" 
            SetAttributeFromString( mFAProf, "PerPeriodFinAidAwardDisbursement", "wHideTableRow", "Y" );
         } 

         RESULT = mFAProf.cursor( "PerPeriodFinAidAwardDisbursement" ).setNextContinue().toInt();;
         //:END 
      } 

      //:END
   } 


   //:END
   return( 0 );
//    
//    /*  This is code from zencas and I currently don't use but will keep just in case...
//    // Compute Student remaining need as COA - Total Fin Aid
//    mFAProf.AwardLetters.COA_MinusFinAidTotal = mFAProf.AwardLetters.CostOfAttendanceTotal - mFAProf.AwardLetters.FinAidFullYearTotal 
//    IF mFAProf.AwardLetters.COA_MinusFinAidTotal < 0 
//       mFAProf.AwardLetters.COA_MinusFinAidTotal = 0    
//    END
//    
//    // If there is any remaining amount, generate Option Funding entities.
//    IF mFAProf.AwardLetters.COA_MinusFinAidTotal > 4000
//       dParentStudentLoan = mFAProf.AwardLetters.COA_MinusFinAidTotal - 4000
//       dParentStudentLoanHalf = dParentStudentLoan / 2
//       CREATE ENTITY mFAProf.OptionalFundingGroup 
//       mFAProf.OptionalFundingGroup.AwardName    = "Summer Earnings"
//       mFAProf.OptionalFundingGroup.FallAmount   = 1000
//       mFAProf.OptionalFundingGroup.SpringAmount = 1000
//       mFAProf.OptionalFundingGroup.Amount       = 2000
//       szAwardValue = "Summer Earnings" + zTAB + "  1,000.00" + zTAB + "  1,000.00" + zTAB + "  2,000.00"
//       mFAProf.OptionalFundingGroup.AwardValue = szAwardValue
//       CREATE ENTITY mFAProf.OptionalFundingGroup 
//       mFAProf.OptionalFundingGroup.AwardName    = "Student Earnings or Federal Work Study"
//       mFAProf.OptionalFundingGroup.FallAmount   = 1000
//       mFAProf.OptionalFundingGroup.SpringAmount = 1000
//       mFAProf.OptionalFundingGroup.Amount       = 2000
//       szAwardValue = "Student Earnings/Federal Work Study" + zTAB + "  1,000.00" + zTAB + "  1,000.00" + zTAB + "  2,000.00"
//       mFAProf.OptionalFundingGroup.AwardValue = szAwardValue
//       CREATE ENTITY mFAProf.OptionalFundingGroup 
//       mFAProf.OptionalFundingGroup.AwardName    = "Parent PLUS Loan / Student Loan"
//       mFAProf.OptionalFundingGroup.FallAmount   = dParentStudentLoanHalf
//       mFAProf.OptionalFundingGroup.SpringAmount = dParentStudentLoanHalf
//       mFAProf.OptionalFundingGroup.Amount       = dParentStudentLoan
//       GetStringFromAttributeByContext( szFallAmount, mFAProf, "OptionalFundingGroup", "FallAmount", "Revenue",10)
//       //GetStringFromAttributeByContext( szSomeNumber, mFAProf, "OptionalFundingGroup", "FallAmount", "Revenue",10)
//       //ConvertToString10( szSomeNumber, szFallAmount )
//       GetStringFromAttributeByContext( szAmount, mFAProf, "OptionalFundingGroup", "Amount", "Revenue",10)
//       //GetStringFromAttributeByContext( szSomeNumber, mFAProf, "OptionalFundingGroup", "Amount", "Revenue",10)
//       //ConvertToString10( szSomeNumber, szAmount )
//       szAwardValue = "Parent PLUS Loan/Student Loan" + zTAB + szFallAmount + zTAB + szFallAmount + zTAB + szAmount
//       mFAProf.OptionalFundingGroup.AwardValue = szAwardValue
//    END
//    */
// END
} 


//:TRANSFORMATION OPERATION
//:GenerateRequirements( VIEW mFAProf BASED ON LOD mFAProf,
//:                      SHORT iYearID )

//:   VIEW lFAReqYr BASED ON LOD lFAReqYr
public int 
omFAProf_GenerateRequirements( View     mFAProf,
                               int      iYearID )
{
   zVIEW    lFAReqYr = new zVIEW( );
   //:STRING ( 8 ) LatestDate
   String   LatestDate = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;

   //:   
   //:ACTIVATE lFAReqYr MULTIPLE WHERE lFAReqYr.CollegeYear.ID = iYearID
   omFAProf_fnLocalBuildQual_22( mFAProf, vTempViewVar_0, iYearID );
   RESULT = ActivateObjectInstance( lFAReqYr, "lFAReqYr", mFAProf, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 

      //:// Generate a list of Requirements that have been met and that are yet to be accomplished.
      //:// Don't generate for Requirements that say "hide on interface".
      //:FOR EACH mFAProf.FinAidProfileRequirement 
      RESULT = mFAProf.cursor( "FinAidProfileRequirement" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 

         //:SET CURSOR FIRST lFAReqYr.FinAidRequirement WHERE lFAReqYr.FinAidRequirement.ID = mFAProf.FinAidProfileRequirementReq.ID 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mFAProf, "FinAidProfileRequirementReq", "ID" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         RESULT = lFAReqYr.cursor( "FinAidRequirement" ).setFirst( "ID", lTempInteger_0 ).toInt();

         //:IF mFAProf.FinAidProfileRequirement.CurrentStatus = "M"
         if ( CompareAttributeToString( mFAProf, "FinAidProfileRequirement", "CurrentStatus", "M" ) == 0 )
         { 

            //:IF lFAReqYr.FinAidRequirement.HideOnInterface = "" 
            if ( CompareAttributeToString( lFAReqYr, "FinAidRequirement", "HideOnInterface", "" ) == 0 )
            { 

               //:// Add Received Item.
               //:CREATE ENTITY mFAProf.ReceivedItems 
               RESULT = CreateEntity( mFAProf, "ReceivedItems", zPOS_AFTER );
               //:mFAProf.ReceivedItems.Name             = lFAReqYr.FinAidRequirement.Name 
               SetAttributeFromAttribute( mFAProf, "ReceivedItems", "Name", lFAReqYr, "FinAidRequirement", "Name" );
               //:mFAProf.ReceivedItems.Description      = lFAReqYr.FinAidRequirement.Description 
               SetAttributeFromAttribute( mFAProf, "ReceivedItems", "Description", lFAReqYr, "FinAidRequirement", "Description" );
               //:mFAProf.ReceivedItems.PrintDescription = lFAReqYr.FinAidRequirement.PrintDescription 
               SetAttributeFromAttribute( mFAProf, "ReceivedItems", "PrintDescription", lFAReqYr, "FinAidRequirement", "PrintDescription" );
               //:mFAProf.ReceivedItems.DateReceived     = mFAProf.FinAidProfileRequirement.DateReceived 
               SetAttributeFromAttribute( mFAProf, "ReceivedItems", "DateReceived", mFAProf, "FinAidProfileRequirement", "DateReceived" );
            } 

            //:   
            //:END

            //:ELSE
         } 
         else
         { 
            //:IF mFAProf.FinAidProfileRequirement.CurrentStatus != "N" AND   // Not Required
            //:   mFAProf.FinAidProfileRequirement.CurrentStatus != "F" AND   // Final
            //:   mFAProf.FinAidProfileRequirement.CurrentStatus != "W"       // Waived
            if ( CompareAttributeToString( mFAProf, "FinAidProfileRequirement", "CurrentStatus", "N" ) != 0 && CompareAttributeToString( mFAProf, "FinAidProfileRequirement", "CurrentStatus", "F" ) != 0 &&
                 CompareAttributeToString( mFAProf, "FinAidProfileRequirement", "CurrentStatus", "W" ) != 0 )
            { 

               //:IF lFAReqYr.FinAidRequirement.HideOnInterface = ""
               if ( CompareAttributeToString( lFAReqYr, "FinAidRequirement", "HideOnInterface", "" ) == 0 )
               { 

                  //:// Add Missing Item.
                  //:CREATE ENTITY mFAProf.MissingItems 
                  RESULT = CreateEntity( mFAProf, "MissingItems", zPOS_AFTER );
                  //:mFAProf.MissingItems.Name             = lFAReqYr.FinAidRequirement.Name 
                  SetAttributeFromAttribute( mFAProf, "MissingItems", "Name", lFAReqYr, "FinAidRequirement", "Name" );
                  //:mFAProf.MissingItems.Description      = lFAReqYr.FinAidRequirement.Description 
                  SetAttributeFromAttribute( mFAProf, "MissingItems", "Description", lFAReqYr, "FinAidRequirement", "Description" );
                  //:mFAProf.MissingItems.PrintDescription = lFAReqYr.FinAidRequirement.PrintDescription 
                  SetAttributeFromAttribute( mFAProf, "MissingItems", "PrintDescription", lFAReqYr, "FinAidRequirement", "PrintDescription" );
               } 


               //:END
            } 

            //:END
         } 

         RESULT = mFAProf.cursor( "FinAidProfileRequirement" ).setNextContinue().toInt();;
         //:   
         //:END
      } 

      //:END
   } 


   //:   // Set up the requirements received on the latest date.
   //:   //OrderEntityForView( mFAProf, "FinAidRequirementMet", "DateReceived A" )
   //:   //SET CURSOR LAST mFAProf.FinAidRequirementMet 
   //:   //IF RESULT >= zCURSOR_SET
   //:   //   LatestDate = mFAProf.FinAidRequirementMet.DateReceived 
   //:   //   FOR EACH mFAProf.FinAidRequirementMet
   //:   //      IF mFAProf.FinAidRequirementMet.DateReceived = LatestDate AND
   //:   //         mFAProf.FinAidRequirementMet.Type = "M"
   //:   //      
   //:   //         CREATE ENTITY mFAProf.LastReceivedItem 
   //:   //         mFAProf.LastReceivedItem.Name             = mFAProf.FinAidRequirement.Name 
   //:   //         mFAProf.LastReceivedItem.Description      = mFAProf.FinAidRequirement.Description 
   //:   //         mFAProf.LastReceivedItem.PrintDescription = mFAProf.FinAidRequirement.PrintDescription 
   //:   //         mFAProf.LastReceivedItem.DateReceived = mFAProf.FinAidRequirementMet.DateReceived 
   //:   //      END
   //:   //   END
   //:   //END
   //:END

   //:DropObjectInstance ( lFAReqYr )
   DropObjectInstance( lFAReqYr );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ShowHidePeriodAwards( VIEW mFAProf BASED ON LOD mFAProf )

//:   VIEW mFAProfT BASED ON LOD mFAProf
public int 
omFAProf_ShowHidePeriodAwards( View     mFAProf )
{
   zVIEW    mFAProfT = new zVIEW( );
   int      RESULT = 0;


   //:// Based on the value of wHidePeriodDeclinedAwards, build the subobject DisplayAward to contain
   //:// either all awards or all awards except "declined" and "inactive".

   //:// First remove existing entries.
   //:CreateViewFromView( mFAProfT, mFAProf )
   CreateViewFromView( mFAProfT, mFAProf );
   //:FOR EACH mFAProfT.PerProfileFinAidAwardPeriod
   RESULT = mFAProfT.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH mFAProfT.DisplayAward 
      RESULT = mFAProfT.cursor( "DisplayAward" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:DELETE ENTITY mFAProfT.DisplayAward NONE 
         RESULT = DeleteEntity( mFAProfT, "DisplayAward", zREPOS_NONE );
         RESULT = mFAProfT.cursor( "DisplayAward" ).setNextContinue().toInt();;
      } 

      RESULT = mFAProfT.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:IF mFAProf.FinAidProfile.wHidePeriodDeclinedAwards = ""
   if ( CompareAttributeToString( mFAProf, "FinAidProfile", "wHidePeriodDeclinedAwards", "" ) == 0 )
   { 
      //:// Current setting is for show all, so change to hide declined, inactive and pending entries, meaning
      //:// build display entries for everything except declined and inactive.
      //:mFAProf.FinAidProfile.wHidePeriodDeclinedAwards = "Y"
      SetAttributeFromString( mFAProf, "FinAidProfile", "wHidePeriodDeclinedAwards", "Y" );
      //:FOR EACH mFAProfT.PerProfileFinAidAwardPeriod
      RESULT = mFAProfT.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:FOR EACH mFAProfT.PerPeriodFinAidAwardDisbursement
         RESULT = mFAProfT.cursor( "PerPeriodFinAidAwardDisbursement" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mFAProfT.DisbFinAidAwardAssigned.AwardStatus != "N" AND 
            //:   mFAProfT.DisbFinAidAwardAssigned.AwardStatus != "I" AND 
            //:   mFAProfT.DisbFinAidAwardAssigned.AwardStatus != "P"
            if ( CompareAttributeToString( mFAProfT, "DisbFinAidAwardAssigned", "AwardStatus", "N" ) != 0 && CompareAttributeToString( mFAProfT, "DisbFinAidAwardAssigned", "AwardStatus", "I" ) != 0 &&
                 CompareAttributeToString( mFAProfT, "DisbFinAidAwardAssigned", "AwardStatus", "P" ) != 0 )
            { 

               //:CREATE ENTITY mFAProfT.DisplayAward 
               RESULT = CreateEntity( mFAProfT, "DisplayAward", zPOS_AFTER );
               //:mFAProfT.DisplayAward.AwardID        = mFAProfT.PerPeriodFinAidAwardDisbursement.ID 
               SetAttributeFromAttribute( mFAProfT, "DisplayAward", "AwardID", mFAProfT, "PerPeriodFinAidAwardDisbursement", "ID" );
               //:mFAProfT.DisplayAward.AwardName      = mFAProfT.DisbFinAidSource.Name 
               SetAttributeFromAttribute( mFAProfT, "DisplayAward", "AwardName", mFAProfT, "DisbFinAidSource", "Name" );
               //:mFAProfT.DisplayAward.AmountExpected = mFAProfT.PerPeriodFinAidAwardDisbursement.AmountExpected 
               SetAttributeFromAttribute( mFAProfT, "DisplayAward", "AmountExpected", mFAProfT, "PerPeriodFinAidAwardDisbursement", "AmountExpected" );
               //:mFAProfT.DisplayAward.AmountAwarded  = mFAProfT.PerPeriodFinAidAwardDisbursement.Amount 
               SetAttributeFromAttribute( mFAProfT, "DisplayAward", "AmountAwarded", mFAProfT, "PerPeriodFinAidAwardDisbursement", "Amount" );
            } 

            RESULT = mFAProfT.cursor( "PerPeriodFinAidAwardDisbursement" ).setNextContinue().toInt();;
            //:END
         } 

         RESULT = mFAProfT.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// Current setting is for hide, so show all.
      //:mFAProf.FinAidProfile.wHidePeriodDeclinedAwards = ""
      SetAttributeFromString( mFAProf, "FinAidProfile", "wHidePeriodDeclinedAwards", "" );
      //:FOR EACH mFAProfT.PerProfileFinAidAwardPeriod
      RESULT = mFAProfT.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:FOR EACH mFAProfT.PerPeriodFinAidAwardDisbursement 
         RESULT = mFAProfT.cursor( "PerPeriodFinAidAwardDisbursement" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY mFAProfT.DisplayAward 
            RESULT = CreateEntity( mFAProfT, "DisplayAward", zPOS_AFTER );
            //:mFAProfT.DisplayAward.AwardID        = mFAProfT.PerPeriodFinAidAwardDisbursement.ID 
            SetAttributeFromAttribute( mFAProfT, "DisplayAward", "AwardID", mFAProfT, "PerPeriodFinAidAwardDisbursement", "ID" );
            //:mFAProfT.DisplayAward.AwardName      = mFAProfT.DisbFinAidSource.Name 
            SetAttributeFromAttribute( mFAProfT, "DisplayAward", "AwardName", mFAProfT, "DisbFinAidSource", "Name" );
            //:mFAProfT.DisplayAward.AmountExpected = mFAProfT.PerPeriodFinAidAwardDisbursement.AmountExpected 
            SetAttributeFromAttribute( mFAProfT, "DisplayAward", "AmountExpected", mFAProfT, "PerPeriodFinAidAwardDisbursement", "AmountExpected" );
            //:mFAProfT.DisplayAward.AmountAwarded  = mFAProfT.PerPeriodFinAidAwardDisbursement.Amount 
            SetAttributeFromAttribute( mFAProfT, "DisplayAward", "AmountAwarded", mFAProfT, "PerPeriodFinAidAwardDisbursement", "Amount" );
            RESULT = mFAProfT.cursor( "PerPeriodFinAidAwardDisbursement" ).setNextContinue().toInt();;
         } 

         RESULT = mFAProfT.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
         //:END
      } 

      //:END
   } 

   //:END
   //:DropView( mFAProfT )
   DropView( mFAProfT );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dTotalDisbursement( VIEW mFAProf BASED ON LOD mFAProf,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   VIEW mFAProf2 BASED ON LOD mFAProf
public int 
omFAProf_dTotalDisbursement( View     mFAProf,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   zVIEW    mFAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   //:DECIMAL dSwappedAmount
   double  dSwappedAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:// If there are SwappedToDisbursement, then the total disbursement is a total of the Amount in all
   //:// the SwappedToDisbursements plus Amount in FinAidAwardDisbursement.    
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView( mFAProf2, mFAProf )
         CreateViewFromView( mFAProf2, mFAProf );
         //:dAmount = 0
         dAmount = 0;
         //:dSwappedAmount = 0
         dSwappedAmount = 0;
         //:FOR EACH  mFAProf2.SwappedToDisbursement  
         RESULT = mFAProf2.cursor( "SwappedToDisbursement" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:dSwappedAmount = dSwappedAmount + mFAProf2.SwappedToDisbursement.Amount  
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf2, "SwappedToDisbursement", "Amount" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dSwappedAmount = dSwappedAmount + dTempDecimal_0;
            RESULT = mFAProf2.cursor( "SwappedToDisbursement" ).setNextContinue().toInt();;
         } 

         //:END
         //:dAmount = mFAProf.FinAidAwardDisbursement.Amount
         {MutableDouble md_dAmount = new MutableDouble( dAmount );
                   GetDecimalFromAttribute( md_dAmount, mFAProf, "FinAidAwardDisbursement", "Amount" );
         dAmount = md_dAmount.doubleValue( );}
         //:dAmount = dAmount + dSwappedAmount  
         dAmount = dAmount + dSwappedAmount;
         //:DropView( mFAProf2 )
         DropView( mFAProf2 );
         //:StoreValueInRecord ( mFAProf,
         //:                     InternalEntityStructure, InternalAttribStructure, dAmount, 0)
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
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
//:dTotalAmountExp( VIEW mFAProf BASED ON LOD mFAProf,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )

//:   VIEW mFAProf2 BASED ON LOD mFAProf
public int 
omFAProf_dTotalAmountExp( View     mFAProf,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   zVIEW    mFAProf2 = new zVIEW( );
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   //:DECIMAL dSwappedAmount
   double  dSwappedAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:// If there are SwappedToDisbursement, then the total amount expected is a total of the AmountExpected in all
   //:// the SwappedToDisbursements plus AmountExpected in FinAidAwardDisbursement.    
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView( mFAProf2, mFAProf )
         CreateViewFromView( mFAProf2, mFAProf );
         //:dAmount =0
         dAmount = 0;
         //:dSwappedAmount = 0
         dSwappedAmount = 0;
         //:FOR EACH  mFAProf2.SwappedToDisbursement  
         RESULT = mFAProf2.cursor( "SwappedToDisbursement" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:dSwappedAmount = dSwappedAmount + mFAProf2.SwappedToDisbursement.AmountExpected  
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf2, "SwappedToDisbursement", "AmountExpected" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dSwappedAmount = dSwappedAmount + dTempDecimal_0;
            RESULT = mFAProf2.cursor( "SwappedToDisbursement" ).setNextContinue().toInt();;
         } 

         //:END
         //:dAmount = mFAProf.FinAidAwardDisbursement.AmountExpected  
         {MutableDouble md_dAmount = new MutableDouble( dAmount );
                   GetDecimalFromAttribute( md_dAmount, mFAProf, "FinAidAwardDisbursement", "AmountExpected" );
         dAmount = md_dAmount.doubleValue( );}
         //:dAmount = dAmount + dSwappedAmount  
         dAmount = dAmount + dSwappedAmount;
         //:DropView( mFAProf2 )
         DropView( mFAProf2 );
         //:StoreValueInRecord ( mFAProf,
         //:                     InternalEntityStructure, InternalAttribStructure, dAmount, 0)
         StoreValueInRecord( mFAProf, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
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
//:AddAwardByAidSource( VIEW mFAProf BASED ON LOD mFAProf,
//:                     VIEW mFASrc  BASED ON LOD mFASrc )

//:   VIEW wXferO REGISTERED AS wXferO
public int 
omFAProf_AddAwardByAidSource( View     mFAProf,
                              View     mFASrc )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL dAward
   double  dAward = 0.0;
   //:DECIMAL dAmountDiv
   double  dAmountDiv = 0.0;
   //:DECIMAL dAmount
   double  dAmount = 0.0;
   //:DECIMAL dDiff
   double  dDiff = 0.0;
   //:INTEGER nDisb
   int      nDisb = 0;
   //:INTEGER nDisbPerStudent
   int      nDisbPerStudent = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;

   RESULT = GetViewByName( wXferO, "wXferO", mFAProf, zLEVEL_TASK );

   //:// Add a new FinAidAward entry
   //:CREATE ENTITY mFAProf.FinAidAward
   RESULT = CreateEntity( mFAProf, "FinAidAward", zPOS_AFTER );
   //:OrderEntityForView( mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate A" )
   OrderEntityForView( mFAProf, "PerProfileFinAidAwardPeriod", "BeginDate A" );
   //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod 
   RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
   //:INCLUDE mFAProf.FinAidSource FROM mFASrc.FinAidSource
   RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidSource", mFASrc, "FinAidSource", zPOS_AFTER );
   //:mFAProf.FinAidAward.DateOffered = wXferO.Root.dCurrentDate
   SetAttributeFromAttribute( mFAProf, "FinAidAward", "DateOffered", wXferO, "Root", "dCurrentDate" );
   //:mFAProf.FinAidAward.AwardType   = mFASrc.FinAidSource.AidType  
   SetAttributeFromAttribute( mFAProf, "FinAidAward", "AwardType", mFASrc, "FinAidSource", "AidType" );
   //:mFAProf.FinAidAward.Amount      = mFASrc.FinAidSource.AwardAmount 
   SetAttributeFromAttribute( mFAProf, "FinAidAward", "Amount", mFASrc, "FinAidSource", "AwardAmount" );
   //:dAward = mFAProf.FinAidAward.Amount
   {MutableDouble md_dAward = new MutableDouble( dAward );
       GetDecimalFromAttribute( md_dAward, mFAProf, "FinAidAward", "Amount" );
   dAward = md_dAward.doubleValue( );}
   //:mFAProf.FinAidAward.AwardStatus = "A"
   SetAttributeFromString( mFAProf, "FinAidAward", "AwardStatus", "A" );
   //:IF mFASrc.FinAidSource.Disbursements = "" OR mFASrc.FinAidSource.Disbursements = 0
   if ( CompareAttributeToString( mFASrc, "FinAidSource", "Disbursements", "" ) == 0 || CompareAttributeToInteger( mFASrc, "FinAidSource", "Disbursements", 0 ) == 0 )
   { 
      //:nDisb = 1
      nDisb = 1;
      //:ELSE 
   } 
   else
   { 
      //:nDisb = mFASrc.FinAidSource.Disbursements 
      {MutableInt mi_nDisb = new MutableInt( nDisb );
             GetIntegerFromAttribute( mi_nDisb, mFASrc, "FinAidSource", "Disbursements" );
      nDisb = mi_nDisb.intValue( );}
   } 

   //:END 
   //:dAmountDiv = 0
   dAmountDiv = 0;
   //:dAmount    = 0
   dAmount = 0;
   //:dAmountDiv = dAward / nDisb 
   dAmountDiv = dAward / nDisb;
   //:// see how many periods are defined for the student in case they are enrolled for less than the default
   //:nDisbPerStudent = 0 
   nDisbPerStudent = 0;
   //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod   
   RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
   //:LOOP WHILE RESULT >= zCURSOR_SET
   while ( RESULT >= zCURSOR_SET )
   { 
      //:nDisbPerStudent = nDisbPerStudent + 1
      nDisbPerStudent = nDisbPerStudent + 1;
      //:SET CURSOR NEXT mFAProf.PerProfileFinAidAwardPeriod   
      RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
   } 

   //:END
   //:mFAProf.FinAidAward.wTextMessage = ""
   SetAttributeFromString( mFAProf, "FinAidAward", "wTextMessage", "" );
   //:IF nDisbPerStudent < nDisb 
   if ( nDisbPerStudent < nDisb )
   { 
      //:nDisb = nDisbPerStudent 
      nDisb = nDisbPerStudent;
      //:dAward = dAmountDiv * nDisb
      dAward = dAmountDiv * nDisb;
      //:mFAProf.FinAidAward.Amount       = dAward 
      SetAttributeFromDecimal( mFAProf, "FinAidAward", "Amount", dAward );
      //:mFAProf.FinAidAward.wTextMessage = "Award Reduced for partial year enrollment"
      SetAttributeFromString( mFAProf, "FinAidAward", "wTextMessage", "Award Reduced for partial year enrollment" );
   } 

   //:END

   //:SET CURSOR FIRST mFAProf.PerProfileFinAidAwardPeriod   
   RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setFirst().toInt();
   //:LOOP WHILE nDisb > 0
   while ( nDisb > 0 )
   { 
      //:CREATE ENTITY  mFAProf.FinAidAwardDisbursement 
      RESULT = CreateEntity( mFAProf, "FinAidAwardDisbursement", zPOS_AFTER );
      //:INCLUDE mFAProf.FinAidAwardPeriod FROM mFAProf.PerProfileFinAidAwardPeriod  
      RESULT = IncludeSubobjectFromSubobject( mFAProf, "FinAidAwardPeriod", mFAProf, "PerProfileFinAidAwardPeriod", zPOS_AFTER );
      //:mFAProf.FinAidAwardDisbursement.AmountExpected   =  dAmountDiv 
      SetAttributeFromDecimal( mFAProf, "FinAidAwardDisbursement", "AmountExpected", dAmountDiv );
      //:mFAProf.FinAidAwardDisbursement.DisbursementDate = mFAProf.FinAidAwardPeriod.BeginDate 
      SetAttributeFromAttribute( mFAProf, "FinAidAwardDisbursement", "DisbursementDate", mFAProf, "FinAidAwardPeriod", "BeginDate" );
      //:dAmount = dAmount + dAmountDiv 
      dAmount = dAmount + dAmountDiv;
      //:SET CURSOR NEXT mFAProf.PerProfileFinAidAwardPeriod   
      RESULT = mFAProf.cursor( "PerProfileFinAidAwardPeriod" ).setNextContinue().toInt();;
      //:nDisb = nDisb - 1
      nDisb = nDisb - 1;
   } 

   //:END
   //:IF dAmount < dAward 
   if ( dAmount < dAward )
   { 
      //:dDiff = dAward - dAmount
      dDiff = dAward - dAmount;
      //:SET CURSOR FIRST mFAProf.FinAidAwardDisbursement 
      RESULT = mFAProf.cursor( "FinAidAwardDisbursement" ).setFirst().toInt();
      //:mFAProf.FinAidAwardDisbursement.AmountExpected = mFAProf.FinAidAwardDisbursement.AmountExpected + dDiff 
      {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
             GetDecimalFromAttribute( md_dTempDecimal_0, mFAProf, "FinAidAwardDisbursement", "AmountExpected" );
      dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
      dTempDecimal_1 = dTempDecimal_0 + dDiff;
      SetAttributeFromDecimal( mFAProf, "FinAidAwardDisbursement", "AmountExpected", dTempDecimal_1 );
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dAddressLabelFull( VIEW mFAProf BASED ON LOD mFAProf,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   SHORT nRC
public int 
omFAProf_dAddressLabelFull( View     mFAProf,
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

         //:nRC = dAdressLabelFull( mFAProf /* vAnyObject */,
         //:   InternalEntityStructure /* szInternalEntityStructure */,
         //:   InternalAttribStructure /* szInternalAttribStructure */,
         //:   GetOrSetFlag /* nGetOrSetFlag */ )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mFAProf );
          nRC = m_ZGLOBAL1_Operation.dAdressLabelFull( mFAProf, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:RETURN nRC
         if(8==8)return( nRC );


         //:/* end zDERIVED_GET */
         //:OF   zDERIVED_SET:
         case zDERIVED_SET :
            break ;
      } 


      //:  /* end zDERIVED_SET */
      //:END  /* case */
      return( 0 );
   } 



}
