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

import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;

/**
   @author QuinSoft
**/

public class mProspct_Object extends VmlObjectOperations
{
   private final ZDRVROPR m_ZDRVROPR;
   public mProspct_Object( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
   }


//:OBJECT CONSTRAINT OPERATION
//:ObjectConstraint( VIEW mProspct BASED ON LOD mProspct,
//:                  SHORT Event,
//:                  SHORT State )

//:   VIEW mAdmDiv   BASED ON LOD mAdmDiv
public int 
omProspct_ObjectConstraint( View     mProspct,
                            Integer   Event,
                            Integer   State )
{
   zVIEW    mAdmDiv = new zVIEW( );
   //:VIEW mUser     BASED ON LOD mUser
   zVIEW    mUser = new zVIEW( );
   //:VIEW mProspct2 BASED ON LOD mProspct
   zVIEW    mProspct2 = new zVIEW( );
   //:VIEW mProspct3 BASED ON LOD mProspct
   zVIEW    mProspct3 = new zVIEW( );
   //:VIEW mPerson   BASED ON LOD mPerson
   zVIEW    mPerson = new zVIEW( );
   //:STRING ( 1 ) szRole
   String   szRole = null;
   //:STRING ( 9 ) szFullYear
   String   szFullYear = null;
   //:STRING ( 4 ) szNextYear
   String   szNextYear = null;
   //:STRING ( 4 ) szPreviousYear
   String   szPreviousYear = null;
   //:INTEGER      NextYear
   int      NextYear = 0;
   //:INTEGER      PreviousYear
   int      PreviousYear = 0;
   //:SHORT        nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;
   int      RESULT = 0;
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


   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :


         //:END
         break ;

      //:  /* end zOCE_ACTIVATE */
      //:OF   zOCE_ACTIVATE_EMPTY:
      case zOCE_ACTIVATE_EMPTY :
         break ;

      //:  /* end zOCE_ACTIVATE_EMPTY */
      //:OF   zOCE_COMMIT:
      case zOCE_COMMIT :

 
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


private int 
omProspct_fnLocalBuildQual_0( View     vSubtask,
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
omProspct_fnLocalBuildQual_1( View     vSubtask,
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
omProspct_fnLocalBuildQual_2( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Prospect" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Prospect" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omProspct_fnLocalBuildQual_3( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szTempString_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Prospect" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "SSN" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omProspct_fnLocalBuildQual_4( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_7 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "StudentChangeHistory" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "StudentChangeHistory" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "LastDate" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Student" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_7 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omProspct_fnLocalBuildQual_5( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "AdministrativeDivision" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidRequirement" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidRequirement" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omProspct_fnLocalBuildQual_6( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_1,
                              String   szTempString_0,
                              String   szTempString_1 )
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
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Prospect" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ExpectedEntryYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Prospect" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ExpectedEntryTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_1.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omProspct_fnLocalBuildQual_7( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szTempString_2,
                              int      lTempInteger_4 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "SSN" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_2.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_4 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "!=" );
   return( 0 );
} 


private int 
omProspct_fnLocalBuildQual_8( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szTempString_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "School" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "School" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Code" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DERIVED ATTRIBUTE OPERATION
//:dCohortName( VIEW mProspct BASED ON LOD mProspct,
//:             STRING ( 32 ) InternalEntityStructure,
//:             STRING ( 32 ) InternalAttribStructure,
//:             SHORT GetOrSetFlag )

//:   STRING ( 54 ) szString
public int 
omProspct_dCohortName( View     mProspct,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{
   String   szString = null;
   //:STRING ( 10 ) szDate
   String   szDate = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_3 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_4 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:szString = ""
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringCopy( sb_szString, 1, 0, "", 1, 0, 55 );
         szString = sb_szString.toString( );}
         //:IF mProspct.AssignedCohortProgram EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mProspct, "AssignedCohortProgram" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = szString + mProspct.AssignedCohortProgram.Name + " / "
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 51, mProspct, "AssignedCohortProgram", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 55 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, " / ", 1, 0, 55 );
            szString = sb_szString.toString( );}
         } 

         //:END
         //:IF mProspct.AssignedCohortLocation EXISTS
         lTempInteger_2 = CheckExistenceOfEntity( mProspct, "AssignedCohortLocation" );
         if ( lTempInteger_2 == 0 )
         { 
            //:szString = szString + mProspct.AssignedCohortLocation.Name + " / "
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_3, 'S', 51, mProspct, "AssignedCohortLocation", "Name", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_1, 1, 0, 55 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, " / ", 1, 0, 55 );
            szString = sb_szString.toString( );}
         } 

         //:END
         //:IF mProspct.AssignedCohort.EstimatedStartDate = ""
         if ( CompareAttributeToString( mProspct, "AssignedCohort", "EstimatedStartDate", "" ) == 0 )
         { 
            //:szString = szString + mProspct.AssignedCohort.EstimatedYearMonth
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_4, 'S', 7, mProspct, "AssignedCohort", "EstimatedYearMonth", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_2, 1, 0, 55 );
            szString = sb_szString.toString( );}
            //:ELSE
         } 
         else
         { 
            //:GetStringFromAttributeByContext( szDate,
            //:                                 mProspct, "AssignedCohort", "EstimatedStartDate",
            //:                                 "", 10 )
            {StringBuilder sb_szDate;
            if ( szDate == null )
               sb_szDate = new StringBuilder( 32 );
            else
               sb_szDate = new StringBuilder( szDate );
                         GetStringFromAttributeByContext( sb_szDate, mProspct, "AssignedCohort", "EstimatedStartDate", "", 10 );
            szDate = sb_szDate.toString( );}
            //:szString = szString + szDate
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szDate, 1, 0, 55 );
            szString = sb_szString.toString( );}
         } 

         //:END
         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mProspct,
         //:                      InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mProspct, InternalEntityStructure, InternalAttribStructure, szString );
         break ;

      //:/* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:   /* end zDERIVED_SET */
   //:   END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dCounselorName( VIEW mProspct BASED ON LOD mProspct,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   STRING ( 50 ) szFullName
public int 
omProspct_dCounselorName( View     mProspct,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   String   szFullName = null;
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

         //:IF mProspct.CounselorPerson.MiddleName = ""
         if ( CompareAttributeToString( mProspct, "CounselorPerson", "MiddleName", "" ) == 0 )
         { 
            //:szFullName = mProspct.CounselorPerson.FirstName + " " + mProspct.CounselorPerson.LastName
            {StringBuilder sb_szFullName;
            if ( szFullName == null )
               sb_szFullName = new StringBuilder( 32 );
            else
               sb_szFullName = new StringBuilder( szFullName );
                         GetStringFromAttribute( sb_szFullName, mProspct, "CounselorPerson", "FirstName" );
            szFullName = sb_szFullName.toString( );}
             {StringBuilder sb_szFullName;
            if ( szFullName == null )
               sb_szFullName = new StringBuilder( 32 );
            else
               sb_szFullName = new StringBuilder( szFullName );
                        ZeidonStringConcat( sb_szFullName, 1, 0, " ", 1, 0, 51 );
            szFullName = sb_szFullName.toString( );}
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 51, mProspct, "CounselorPerson", "LastName", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szFullName;
            if ( szFullName == null )
               sb_szFullName = new StringBuilder( 32 );
            else
               sb_szFullName = new StringBuilder( szFullName );
                        ZeidonStringConcat( sb_szFullName, 1, 0, szTempString_0, 1, 0, 51 );
            szFullName = sb_szFullName.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szFullName = mProspct.CounselorPerson.FirstName + " " +
            //:          mProspct.CounselorPerson.MiddleName + " " +
            //:          mProspct.CounselorPerson.LastName
            {StringBuilder sb_szFullName;
            if ( szFullName == null )
               sb_szFullName = new StringBuilder( 32 );
            else
               sb_szFullName = new StringBuilder( szFullName );
                         GetStringFromAttribute( sb_szFullName, mProspct, "CounselorPerson", "FirstName" );
            szFullName = sb_szFullName.toString( );}
             {StringBuilder sb_szFullName;
            if ( szFullName == null )
               sb_szFullName = new StringBuilder( 32 );
            else
               sb_szFullName = new StringBuilder( szFullName );
                        ZeidonStringConcat( sb_szFullName, 1, 0, " ", 1, 0, 51 );
            szFullName = sb_szFullName.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 51, mProspct, "CounselorPerson", "MiddleName", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szFullName;
            if ( szFullName == null )
               sb_szFullName = new StringBuilder( 32 );
            else
               sb_szFullName = new StringBuilder( szFullName );
                        ZeidonStringConcat( sb_szFullName, 1, 0, szTempString_1, 1, 0, 51 );
            szFullName = sb_szFullName.toString( );}
             {StringBuilder sb_szFullName;
            if ( szFullName == null )
               sb_szFullName = new StringBuilder( 32 );
            else
               sb_szFullName = new StringBuilder( szFullName );
                        ZeidonStringConcat( sb_szFullName, 1, 0, " ", 1, 0, 51 );
            szFullName = sb_szFullName.toString( );}
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_2, 'S', 51, mProspct, "CounselorPerson", "LastName", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szFullName;
            if ( szFullName == null )
               sb_szFullName = new StringBuilder( 32 );
            else
               sb_szFullName = new StringBuilder( szFullName );
                        ZeidonStringConcat( sb_szFullName, 1, 0, szTempString_2, 1, 0, 51 );
            szFullName = sb_szFullName.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mProspct,
         //:                   InternalEntityStructure, InternalAttribStructure, szFullName )
         StoreStringInRecord( mProspct, InternalEntityStructure, InternalAttribStructure, szFullName );
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


//:ENTITY CONSTRAINT OPERATION
public int 
omProspct_WkRequirementDateSet( View     mProspct,
                                String   NameOfEntity,
                                Integer   Event,
                                Integer   State )
{

   //:WkRequirementDateSet( VIEW mProspct BASED ON LOD mProspct,
   //:                   STRING ( 32 ) NameOfEntity,
   //:                   SHORT Event,
   //:                   SHORT State )

   //:CASE Event
   switch( Event )
   { 
      //:OF   zECE_ACCEPT:
      case zECE_ACCEPT :
         //:IF mProspct.WkAdmissionRequirement.DateReceived = "" 
         //:   AND mProspct.WkAdmissionRequirement.CurrentStatus != ""
         if ( CompareAttributeToString( mProspct, "WkAdmissionRequirement", "DateReceived", "" ) == 0 && CompareAttributeToString( mProspct, "WkAdmissionRequirement", "CurrentStatus", "" ) != 0 )
         { 
            //:MessageSend( mProspct, "", "Admissions Requirement Date", 
            //:          "A Requirement with a status MUST have a date set.", 
            //:          zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mProspct, "", "Admissions Requirement Date", "A Requirement with a status MUST have a date set.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
         //:IF mProspct.WkAdmissionRequirement.CurrentStatus = ""
         if ( CompareAttributeToString( mProspct, "WkAdmissionRequirement", "CurrentStatus", "" ) == 0 )
         { 
            //:mProspct.WkAdmissionRequirement.DateReceived = "" 
            SetAttributeFromString( mProspct, "WkAdmissionRequirement", "DateReceived", "" );
         } 

         //:END 
         //:RETURN 0
         if(8==8)return( 0 );

         //:/* end zECE_ACCEPT */
         //:OF   zECE_CANCEL:
         case zECE_CANCEL :
            break ;

         //:/* end zECE_CANCEL */
         //:OF   zECE_CREATE:
         case zECE_CREATE :
            break ;

         //:/* end zECE_CREATE */
         //:OF   zECE_DELETE:
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


      //:  /* end zECE_INCLUDE */
      //:END  /* case */
      return( 0 );
   } 


   //:TRANSFORMATION OPERATION
   //:AssignTerritoryValues( VIEW mProspct BASED ON LOD mProspct,
   //:                    VIEW mAdmDiv  BASED ON LOD mAdmDiv )

   //:VIEW mTertryLST  BASED ON LOD mTertry
public int 
omProspct_AssignTerritoryValues( View     mProspct,
                                 View     mAdmDiv )
{
   zVIEW    mTertryLST = new zVIEW( );
   //:VIEW mTertryLST2 BASED ON LOD mTertry
   zVIEW    mTertryLST2 = new zVIEW( );
   //:VIEW lZipTer     BASED ON LOD lZipTer
   zVIEW    lZipTer = new zVIEW( );
   //:VIEW mPerson     BASED ON LOD mPerson
   zVIEW    mPerson = new zVIEW( );
   //:STRING ( 5 ) szZipCode
   String   szZipCode = null;
   //:STRING ( 3 ) szZipCode3
   String   szZipCode3 = null;
   //:STRING ( 4 ) szZipCode4
   String   szZipCode4 = null;
   //:STRING ( 1 ) szLastNameCharacter
   String   szLastNameCharacter = null;
   //:STRING ( 1 ) szLastNameCharacterUpper
   String   szLastNameCharacterUpper = null;
   //:STRING ( 2 ) szCitizenshipStatus
   String   szCitizenshipStatus = null;
   //:STRING ( 3 ) szChurchDenomination
   String   szChurchDenomination = null;
   //:STRING ( 50 ) szTerritoryName
   String   szTerritoryName = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;


   //:// Assign the Territory and Counselor automatically according to the following rules:
   //:// 1. If the Prospect is a "Transfer", assign it to the "Transfer" Territory.
   //:// 2. If the Prospect has a Primary Address that is out of the US, assign it to the "International" Territory.
   //:// 3. Otherwise assign the Prospect to a Territory by Zip Code.
   //:// If none of the above occur, no Territory or Counselor will be assigned.

   //:GET VIEW mTertryLST NAMED "mTertryLST"
   RESULT = GetViewByName( mTertryLST, "mTertryLST", mProspct, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mTertryLST Multiple
      RESULT = ActivateObjectInstance( mTertryLST, "mTertry", mProspct, 0, zMULTIPLE );
      //:NAME VIEW mTertryLST "mTertryLST"
      SetNameForView( mTertryLST, "mTertryLST", null, zLEVEL_TASK );
   } 

   //:END

   //:GET VIEW mPerson NAMED "mPerson"
   RESULT = GetViewByName( mPerson, "mPerson", mProspct, zLEVEL_TASK );
   //:IF RESULT >= 0 
   if ( RESULT >= 0 )
   { 
      //:// If the Prospect is a foreign citizen, make the Territory International.
      //:szCitizenshipStatus = ""
       {StringBuilder sb_szCitizenshipStatus;
      if ( szCitizenshipStatus == null )
         sb_szCitizenshipStatus = new StringBuilder( 32 );
      else
         sb_szCitizenshipStatus = new StringBuilder( szCitizenshipStatus );
            ZeidonStringCopy( sb_szCitizenshipStatus, 1, 0, "", 1, 0, 3 );
      szCitizenshipStatus = sb_szCitizenshipStatus.toString( );}
      //:IF mPerson.Demographics EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( mPerson, "Demographics" );
      if ( lTempInteger_0 == 0 )
      { 
         //:szCitizenshipStatus = mPerson.Demographics.CitizenshipCode 
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szCitizenshipStatus;
         if ( szCitizenshipStatus == null )
            sb_szCitizenshipStatus = new StringBuilder( 32 );
         else
            sb_szCitizenshipStatus = new StringBuilder( szCitizenshipStatus );
                   GetVariableFromAttribute( sb_szCitizenshipStatus, mi_lTempInteger_1, 'S', 3, mPerson, "Demographics", "CitizenshipCode", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szCitizenshipStatus = sb_szCitizenshipStatus.toString( );}
      } 

      //:END
      //:IF mPerson.HomeChurch EXISTS
      lTempInteger_2 = CheckExistenceOfEntity( mPerson, "HomeChurch" );
      if ( lTempInteger_2 == 0 )
      { 
         //:szChurchDenomination = mPerson.HomeChurch.Denomination
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szChurchDenomination;
         if ( szChurchDenomination == null )
            sb_szChurchDenomination = new StringBuilder( 32 );
         else
            sb_szChurchDenomination = new StringBuilder( szChurchDenomination );
                   GetVariableFromAttribute( sb_szChurchDenomination, mi_lTempInteger_3, 'S', 4, mPerson, "HomeChurch", "Denomination", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         szChurchDenomination = sb_szChurchDenomination.toString( );}
      } 

      //:END

      //:// The following rule was added on 5/1/2015 by DonC because Ashley Rudeen asked that all prospects with a 
      //:// EntranceClassification of Readmitted be assigned the Readmits Territory.
      //:IF mProspct.Prospect.EntranceClassification = "RD"
      if ( CompareAttributeToString( mProspct, "Prospect", "EntranceClassification", "RD" ) == 0 )
      { 
         //:SET CURSOR FIRST mTertryLST.Territory WHERE mTertryLST.Territory.Name = "Readmits"
         RESULT = SetCursorFirstEntityByString( mTertryLST, "Territory", "Name", "Readmits", "" );
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:AddUpdateTerritory( mProspct, mTertryLST )
            omProspct_AddUpdateTerritory( mProspct, mTertryLST );
         } 

         //:END
         //:ELSE
      } 
      else
      { 

         //:IF szCitizenshipStatus = "FS"
         if ( ZeidonStringCompare( szCitizenshipStatus, 1, 0, "FS", 1, 0, 3 ) == 0 )
         { 
            //:IF mProspct.Prospect.EntranceClassification = "TR"
            if ( CompareAttributeToString( mProspct, "Prospect", "EntranceClassification", "TR" ) == 0 )
            { 
               //:// International AND Transfer
               //:SET CURSOR FIRST mTertryLST.Territory WHERE mTertryLST.Territory.Name = "International - TR"
               RESULT = SetCursorFirstEntityByString( mTertryLST, "Territory", "Name", "International - TR", "" );
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:AddUpdateTerritory( mProspct, mTertryLST )
                  omProspct_AddUpdateTerritory( mProspct, mTertryLST );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// International, but NOT Transfer
               //:SET CURSOR FIRST mTertryLST.Territory WHERE mTertryLST.Territory.Name = "International"
               RESULT = SetCursorFirstEntityByString( mTertryLST, "Territory", "Name", "International", "" );
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:AddUpdateTerritory( mProspct, mTertryLST )
                  omProspct_AddUpdateTerritory( mProspct, mTertryLST );
               } 

               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:// We will only perform the rest of the code if Person Address exists.
            //:IF mPerson.PrimaryAddress EXISTS
            lTempInteger_4 = CheckExistenceOfEntity( mPerson, "PrimaryAddress" );
            if ( lTempInteger_4 == 0 )
            { 
               //:IF ( mPerson.PrimaryAddress.Country != "" AND mPerson.PrimaryAddress.Country != "US" ) OR
               //:   mPerson.PrimaryAddress.StateProvince = "AA" OR mPerson.PrimaryAddress.StateProvince = "AE" OR mPerson.PrimaryAddress.StateProvince = "AP"
               if ( ( CompareAttributeToString( mPerson, "PrimaryAddress", "Country", "" ) != 0 && CompareAttributeToString( mPerson, "PrimaryAddress", "Country", "US" ) != 0 ) ||
                    CompareAttributeToString( mPerson, "PrimaryAddress", "StateProvince", "AA" ) == 0 || CompareAttributeToString( mPerson, "PrimaryAddress", "StateProvince", "AE" ) == 0 ||
                    CompareAttributeToString( mPerson, "PrimaryAddress", "StateProvince", "AP" ) == 0 )
               { 

                  //:SET CURSOR FIRST mTertryLST.Territory WHERE mTertryLST.Territory.Name = "Off Zone"
                  RESULT = SetCursorFirstEntityByString( mTertryLST, "Territory", "Name", "Off Zone", "" );
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:AddUpdateTerritory( mProspct, mTertryLST )
                     omProspct_AddUpdateTerritory( mProspct, mTertryLST );
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:IF mProspct.Prospect.EntranceClassification = "TR"
                  if ( CompareAttributeToString( mProspct, "Prospect", "EntranceClassification", "TR" ) == 0 )
                  { 
                     //:SET CURSOR FIRST mTertryLST.Territory WHERE mTertryLST.Territory.Name = "Transfer"
                     RESULT = SetCursorFirstEntityByString( mTertryLST, "Territory", "Name", "Transfer", "" );
                     //:IF RESULT >= zCURSOR_SET
                     if ( RESULT >= zCURSOR_SET )
                     { 
                        //:AddUpdateTerritory( mProspct, mTertryLST )
                        omProspct_AddUpdateTerritory( mProspct, mTertryLST );
                     } 

                     //:END
                     //:/*szLastNameCharacter = mPerson.Person.LastName
                     //:zToUpper( szLastNameCharacter, szLastNameCharacterUpper )
                     //:IF szLastNameCharacterUpper <= "L"
                     //:   SET CURSOR FIRST mTertryLST.Territory WHERE mTertryLST.Territory.Name = "Transfer (A-L)"
                     //:   IF RESULT >= zCURSOR_SET
                     //:      AddUpdateTerritory( mProspct, mTertryLST )
                     //:   END
                     //:ELSE
                     //:   SET CURSOR FIRST mTertryLST.Territory WHERE mTertryLST.Territory.Name = "Transfer (M-Z)"
                     //:   IF RESULT >= zCURSOR_SET
                     //:      AddUpdateTerritory( mProspct, mTertryLST )
                     //:   END
                     //:END*/
                     //:   
                     //:ELSE
                  } 
                  else
                  { 
                     //:szZipCode = mPerson.PrimaryAddress.PostalCode
                     {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                     StringBuilder sb_szZipCode;
                     if ( szZipCode == null )
                        sb_szZipCode = new StringBuilder( 32 );
                     else
                        sb_szZipCode = new StringBuilder( szZipCode );
                                           GetVariableFromAttribute( sb_szZipCode, mi_lTempInteger_5, 'S', 6, mPerson, "PrimaryAddress", "PostalCode", "", 0 );
                     lTempInteger_5 = mi_lTempInteger_5.intValue( );
                     szZipCode = sb_szZipCode.toString( );}
                     //:IF szZipCode != ""
                     if ( ZeidonStringCompare( szZipCode, 1, 0, "", 1, 0, 6 ) != 0 )
                     { 
                        //:ACTIVATE lZipTer WHERE lZipTer.ZipCodeLookUpTable.ZipCode = szZipCode 
                        omProspct_fnLocalBuildQual_0( mProspct, vTempViewVar_0, szZipCode );
                        RESULT = ActivateObjectInstance( lZipTer, "lZipTer", mProspct, vTempViewVar_0, zSINGLE );
                        DropView( vTempViewVar_0 );
                        //:IF RESULT < 0
                        if ( RESULT < 0 )
                        { 
                           //:DropObjectInstance( lZipTer )
                           DropObjectInstance( lZipTer );
                           //:szZipCode3 = szZipCode
                            {StringBuilder sb_szZipCode3;
                           if ( szZipCode3 == null )
                              sb_szZipCode3 = new StringBuilder( 32 );
                           else
                              sb_szZipCode3 = new StringBuilder( szZipCode3 );
                                                      ZeidonStringCopy( sb_szZipCode3, 1, 0, szZipCode, 1, 0, 4 );
                           szZipCode3 = sb_szZipCode3.toString( );}
                           //:szZipCode4 = szZipCode3 + "%"
                            {StringBuilder sb_szZipCode4;
                           if ( szZipCode4 == null )
                              sb_szZipCode4 = new StringBuilder( 32 );
                           else
                              sb_szZipCode4 = new StringBuilder( szZipCode4 );
                                                      ZeidonStringCopy( sb_szZipCode4, 1, 0, szZipCode3, 1, 0, 5 );
                           szZipCode4 = sb_szZipCode4.toString( );}
                            {StringBuilder sb_szZipCode4;
                           if ( szZipCode4 == null )
                              sb_szZipCode4 = new StringBuilder( 32 );
                           else
                              sb_szZipCode4 = new StringBuilder( szZipCode4 );
                                                      ZeidonStringConcat( sb_szZipCode4, 1, 0, "%", 1, 0, 5 );
                           szZipCode4 = sb_szZipCode4.toString( );}
                           //:ACTIVATE lZipTer Multiple WHERE lZipTer.ZipCodeLookUpTable.ZipCode LIKE szZipCode4
                           omProspct_fnLocalBuildQual_1( mProspct, vTempViewVar_1, szZipCode4 );
                           RESULT = ActivateObjectInstance( lZipTer, "lZipTer", mProspct, vTempViewVar_1, zMULTIPLE );
                           DropView( vTempViewVar_1 );
                           //:OrderEntityForView( lZipTer, "ZipCodeLookUpTable", "ZipCode A" )
                           OrderEntityForView( lZipTer, "ZipCodeLookUpTable", "ZipCode A" );
                           //:SET CURSOR LAST lZipTer.ZipCodeLookUpTable WHERE lZipTer.ZipCodeLookUpTable.ZipCode < szZipCode
                           RESULT = SetCursorLastEntity( lZipTer, "ZipCodeLookUpTable", "" );
                           if ( RESULT > zCURSOR_UNCHANGED )
                           { 
                              while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( lZipTer, "ZipCodeLookUpTable", "ZipCode", szZipCode ) >= 0 ) )
                              { 
                                 RESULT = SetCursorPrevEntity( lZipTer, "ZipCodeLookUpTable", "" );
                              } 

                           } 

                           //:IF lZipTer.ZipCodeLookUpTable EXISTS
                           lTempInteger_6 = CheckExistenceOfEntity( lZipTer, "ZipCodeLookUpTable" );
                           if ( lTempInteger_6 == 0 )
                           { 
                              //:RESULT = 0
                              RESULT = 0;
                              //:ELSE
                           } 
                           else
                           { 
                              //:RESULT = -1
                              RESULT = -1;
                           } 

                           //:END
                        } 

                        //:END
                        //:IF RESULT >= 0
                        if ( RESULT >= 0 )
                        { 
                           //:IF lZipTer.Territory EXISTS 
                           lTempInteger_7 = CheckExistenceOfEntity( lZipTer, "Territory" );
                           if ( lTempInteger_7 == 0 )
                           { 
                              //:SET CURSOR FIRST mTertryLST.Territory 
                              //:           WHERE mTertryLST.Territory.ID = lZipTer.Territory.ID 
                              {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                                                             GetIntegerFromAttribute( mi_lTempInteger_8, lZipTer, "Territory", "ID" );
                              lTempInteger_8 = mi_lTempInteger_8.intValue( );}
                              RESULT = SetCursorFirstEntityByInteger( mTertryLST, "Territory", "ID", lTempInteger_8, "" );
                              //:IF RESULT >= zCURSOR_SET
                              if ( RESULT >= zCURSOR_SET )
                              { 
                                 //:// The following additional rule was added by Don Christensen on 2/13/2009, so that any 
                                 //:// Prospects from Vermont, New Hampshire, Massachusetts, Connecticut or Rhode Island that 
                                 //:// were Nazarene would be assigned to the Territory, "NE-Nazarene".
                                 //:// However, we will only do this if the "NE-Nazarene" Territory exists.
                                 //:CreateViewFromView( mTertryLST2, mTertryLST )
                                 CreateViewFromView( mTertryLST2, mTertryLST );
                                 //:SET CURSOR FIRST mTertryLST2.Territory WHERE mTertryLST2.Territory.Name = "NE-Nazarene"
                                 RESULT = SetCursorFirstEntityByString( mTertryLST2, "Territory", "Name", "NE-Nazarene", "" );
                                 //:IF RESULT >= zCURSOR_SET
                                 if ( RESULT >= zCURSOR_SET )
                                 { 
                                    //:IF szChurchDenomination = "NA" AND 
                                    //:   ( mProspct.Address.StateProvince = "MA" OR 
                                    //:     mProspct.Address.StateProvince = "VT" OR
                                    //:     mProspct.Address.StateProvince = "NH" OR
                                    //:     mProspct.Address.StateProvince = "CT" OR
                                    //:     mProspct.Address.StateProvince = "RI" )
                                    if ( ZeidonStringCompare( szChurchDenomination, 1, 0, "NA", 1, 0, 4 ) == 0 && ( CompareAttributeToString( mProspct, "Address", "StateProvince", "MA" ) == 0 ||
                        CompareAttributeToString( mProspct, "Address", "StateProvince", "VT" ) == 0 || CompareAttributeToString( mProspct, "Address", "StateProvince", "NH" ) == 0 ||
                        CompareAttributeToString( mProspct, "Address", "StateProvince", "CT" ) == 0 || CompareAttributeToString( mProspct, "Address", "StateProvince", "RI" ) == 0 ) )
                                    { 
                                       //:// The NE-Nazarene territory exists and the Student is from New England, so set
                                       //:// position on the NE Nazarene territory.
                                       //:SET CURSOR FIRST mTertryLST.Territory WHERE mTertryLST.Territory.Name = "NE-Nazarene"
                                       RESULT = SetCursorFirstEntityByString( mTertryLST, "Territory", "Name", "NE-Nazarene", "" );
                                    } 

                                    //:END
                                 } 

                                 //:END
                                 //:DropView( mTertryLST2)
                                 DropView( mTertryLST2 );

                                 //:AddUpdateTerritory( mProspct, mTertryLST )
                                 omProspct_AddUpdateTerritory( mProspct, mTertryLST );
                              } 

                              //:END
                           } 

                           //:END
                        } 

                        //:END
                        //:DropObjectInstance( lZipTer )
                        DropObjectInstance( lZipTer );
                     } 

                     //:END
                  } 

                  //:   /*IF mPerson.PrimaryAddress.StateProvince = "MA"
                  //:      szLastNameCharacter = mPerson.Person.LastName
                  //:      zToUpper( szLastNameCharacter, szLastNameCharacterUpper )
                  //:      IF szLastNameCharacterUpper <= "O"
                  //:         SET CURSOR FIRST mTertryLST.Territory WHERE mTertryLST.Territory.Name = "NE-MA (A-O)"
                  //:         IF RESULT >= zCURSOR_SET
                  //:            AddUpdateTerritory( mProspct, mTertryLST )
                  //:         END
                  //:      ELSE 
                  //:         SET CURSOR FIRST mTertryLST.Territory WHERE mTertryLST.Territory.Name = "NE-MA (P-Z)"
                  //:         IF RESULT >= zCURSOR_SET
                  //:            AddUpdateTerritory( mProspct, mTertryLST )
                  //:         END
                  //:      END
                  //:   ELSE
                  //:      szZipCode = mPerson.PrimaryAddress.PostalCode
                  //:      IF szZipCode != ""
                  //:         ACTIVATE lZipTer WHERE lZipTer.ZipCodeLookUpTable.ZipCode = szZipCode 
                  //:         IF RESULT < 0
                  //:            szZipCode3 = szZipCode
                  //:            ACTIVATE lZipTer WHERE lZipTer.ZipCodeLookUpTable.ZipCode >= szZipCode3
                  //:         END
                  //:         IF RESULT >= 0
                  //:            IF lZipTer.Territory EXISTS 
                  //:               SET CURSOR FIRST mTertryLST.Territory 
                  //:                          WHERE mTertryLST.Territory.ID = lZipTer.Territory.ID 
                  //:               IF RESULT >= zCURSOR_SET
                  //:                  AddUpdateTerritory( mProspct, mTertryLST )
                  //:               END
                  //:            END
                  //:            DropObjectInstance( lZipTer )
                  //:         END
                  //:      END
                  //:   END*/
                  //:END
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:// If we couldn't locate a Territory in the code above, we will try to tie the Prospect to an Unknown Territory
      //:// with an ID of 24.
      //:IF mProspct.Territory DOES NOT EXIST
      lTempInteger_9 = CheckExistenceOfEntity( mProspct, "Territory" );
      if ( lTempInteger_9 != 0 )
      { 
         //:SET CURSOR FIRST mTertryLST.Territory WHERE mTertryLST.Territory.ID = 24
         RESULT = SetCursorFirstEntityByInteger( mTertryLST, "Territory", "ID", 24, "" );
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:AddUpdateTerritory( mProspct, mTertryLST )
            omProspct_AddUpdateTerritory( mProspct, mTertryLST );
         } 

         //:END
      } 

      //:END

      //:// If after the above, no Territory or Counselor could be assigned, give a warning error.
      //:IF mProspct.Territory DOES NOT EXIST
      lTempInteger_10 = CheckExistenceOfEntity( mProspct, "Territory" );
      if ( lTempInteger_10 != 0 )
      { 
         //:MessageSend( mProspct, "", "Assign Territory and Counselor", 
         //:             "Note: A Territory/Counselor could not be assigned to the Prospect.", 
         //:             zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
         MessageSend( mProspct, "", "Assign Territory and Counselor", "Note: A Territory/Counselor could not be assigned to the Prospect.", zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
         //:ELSE
      } 
      else
      { 
         //:IF mProspct.Counselor DOES NOT EXIST
         lTempInteger_11 = CheckExistenceOfEntity( mProspct, "Counselor" );
         if ( lTempInteger_11 != 0 )
         { 
            //:MessageSend( mProspct, "", "Assign Territory and Counselor", 
            //:             "Note: A Counselor could not be assigned to the Prospect.", 
            //:             zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
            MessageSend( mProspct, "", "Assign Territory and Counselor", "Note: A Counselor could not be assigned to the Prospect.", zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
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
private int 
omProspct_AddUpdateTerritory( View     mProspct,
                              View     mTertryLST )
{
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   //:AddUpdateTerritory( VIEW mProspct   BASED ON LOD mProspct,
   //:                 VIEW mTertryLST BASED ON LOD mTertry )

   //:SET CURSOR FIRST mProspct.Territory
   RESULT = SetCursorFirstEntity( mProspct, "Territory", "" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:IF mProspct.Territory.ID != mTertryLST.Territory.ID
      if ( CompareAttributeToAttribute( mProspct, "Territory", "ID", mTertryLST, "Territory", "ID" ) != 0 )
      { 
         //:EXCLUDE mProspct.Territory NONE
         RESULT = ExcludeEntity( mProspct, "Territory", zREPOS_NONE );
         //:INCLUDE mProspct.Territory FROM mTertryLST.Territory
         RESULT = IncludeSubobjectFromSubobject( mProspct, "Territory", mTertryLST, "Territory", zPOS_AFTER );
         //:IF mProspct.Counselor EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mProspct, "Counselor" );
         if ( lTempInteger_0 == 0 )
         { 
            //:EXCLUDE mProspct.Counselor 
            RESULT = ExcludeEntity( mProspct, "Counselor", zREPOS_AFTER );
         } 

         //:END
         //:INCLUDE mProspct.Counselor FROM mTertryLST.Staff 
         RESULT = IncludeSubobjectFromSubobject( mProspct, "Counselor", mTertryLST, "Staff", zPOS_AFTER );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:INCLUDE mProspct.Territory FROM mTertryLST.Territory
      RESULT = IncludeSubobjectFromSubobject( mProspct, "Territory", mTertryLST, "Territory", zPOS_AFTER );
      //:IF mProspct.Counselor EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( mProspct, "Counselor" );
      if ( lTempInteger_1 == 0 )
      { 
         //:EXCLUDE mProspct.Counselor 
         RESULT = ExcludeEntity( mProspct, "Counselor", zREPOS_AFTER );
      } 

      //:END
      //:INCLUDE mProspct.Counselor FROM mTertryLST.Staff 
      RESULT = IncludeSubobjectFromSubobject( mProspct, "Counselor", mTertryLST, "Staff", zPOS_AFTER );
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dHighSAT( VIEW mProspct BASED ON LOD mProspct,
//:          STRING ( 32 ) InternalEntityStructure,
//:          STRING ( 32 ) InternalAttribStructure,
//:          SHORT GetOrSetFlag )

//:   DECIMAL dTotalComposite 
public int 
omProspct_dHighSAT( View     mProspct,
                    String InternalEntityStructure,
                    String InternalAttribStructure,
                    Integer   GetOrSetFlag )
{
   double  dTotalComposite = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:ComputeHighExamScoreSAT( dTotalComposite, mProspct )
         {
          ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mProspct );
          {MutableDouble md_dTotalComposite = new MutableDouble( dTotalComposite );
                   m_ZGLOBAL2_Operation.ComputeHighExamScoreSAT( md_dTotalComposite, mProspct );
         dTotalComposite = md_dTotalComposite.doubleValue( );}
          // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF dTotalComposite = 0
         if ( dTotalComposite == 0 )
         { 
            //:StoreStringInRecord ( mProspct,
            //:                      InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( mProspct, InternalEntityStructure, InternalAttribStructure, "" );
            //:ELSE
         } 
         else
         { 
            //:StoreValueInRecord ( mProspct,
            //:                  InternalEntityStructure, InternalAttribStructure, dTotalComposite, 0 )
            StoreValueInRecord( mProspct, InternalEntityStructure, InternalAttribStructure, dTotalComposite, 0 );
         } 

         //:END
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
//:dHighACT( VIEW mProspct BASED ON LOD mProspct,
//:          STRING ( 32 ) InternalEntityStructure,
//:          STRING ( 32 ) InternalAttribStructure,
//:          SHORT GetOrSetFlag )

//:   DECIMAL dTotalComposite
public int 
omProspct_dHighACT( View     mProspct,
                    String InternalEntityStructure,
                    String InternalAttribStructure,
                    Integer   GetOrSetFlag )
{
   double  dTotalComposite = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:ComputeHighExamScoreACT( dTotalComposite, mProspct )
         {
          ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mProspct );
          {MutableDouble md_dTotalComposite = new MutableDouble( dTotalComposite );
                   m_ZGLOBAL2_Operation.ComputeHighExamScoreACT( md_dTotalComposite, mProspct );
         dTotalComposite = md_dTotalComposite.doubleValue( );}
          // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF dTotalComposite = 0
         if ( dTotalComposite == 0 )
         { 
            //:StoreStringInRecord ( mProspct,
            //:                      InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( mProspct, InternalEntityStructure, InternalAttribStructure, "" );
            //:ELSE
         } 
         else
         { 
            //:StoreValueInRecord ( mProspct,
            //:                  InternalEntityStructure, InternalAttribStructure, dTotalComposite, 0 )
            StoreValueInRecord( mProspct, InternalEntityStructure, InternalAttribStructure, dTotalComposite, 0 );
         } 

         //:END
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
//:dNewHighSAT( VIEW mProspct BASED ON LOD mProspct,
//:             STRING ( 32 ) InternalEntityStructure,
//:             STRING ( 32 ) InternalAttribStructure,
//:             SHORT GetOrSetFlag )

//:   DECIMAL dTotalComposite 
public int 
omProspct_dNewHighSAT( View     mProspct,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{
   double  dTotalComposite = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:ComputeNewHighExamScoreSAT( dTotalComposite, mProspct )
         {
          ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mProspct );
           {MutableDouble md_dTotalComposite = new MutableDouble( dTotalComposite );
                  m_ZGLOBAL2_Operation.ComputeNewHighExamScoreSAT( md_dTotalComposite, mProspct );
         dTotalComposite = md_dTotalComposite.doubleValue( );}
          // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF dTotalComposite = 0
         if ( dTotalComposite == 0 )
         { 
            //:StoreStringInRecord ( mProspct,
            //:                      InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( mProspct, InternalEntityStructure, InternalAttribStructure, "" );
            //:ELSE
         } 
         else
         { 
            //:StoreValueInRecord ( mProspct,
            //:                  InternalEntityStructure, InternalAttribStructure, dTotalComposite, 0 )
            StoreValueInRecord( mProspct, InternalEntityStructure, InternalAttribStructure, dTotalComposite, 0 );
         } 

         //:END
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
public int 
omProspct_dFullNameLFM( View     mProspct,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{

   //:dFullNameLFM( VIEW mProspct BASED ON LOD mProspct,
   //:                 STRING ( 32 ) InternalEntityStructure,
   //:                 STRING ( 32 ) InternalAttribStructure,
   //:                 SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_LastFirstMiddle( mProspct, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mProspct );
          m_ZGLOBAL1_Operation.PersonName_LastFirstMiddle( mProspct, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
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
//:dLatestISIR_Date( VIEW mProspct BASED ON LOD mProspct,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )
//:                  
//:   STRING ( 8 ) szLatestDate
public int 
omProspct_dLatestISIR_Date( View     mProspct,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szLatestDate = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// This is the latest Transaction Processed Date of all ISIRs for the Prospect. It gives us
         //:// an indication of how old the ISIR is. We're using this attribute instead of ApplicationReceiptDate 
         //:// because it is filled in more often.

         //:FOR EACH mProspct.FAISIR WITHIN mProspct.Prospect 
         RESULT = SetCursorFirstEntity( mProspct, "FAISIR", "Prospect" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF szLatestDate < mProspct.FAISIR.TransactionProcessedDate
            if ( CompareAttributeToString( mProspct, "FAISIR", "TransactionProcessedDate", szLatestDate ) > 0 )
            { 
               //:szLatestDate = mProspct.FAISIR.TransactionProcessedDate
               {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
               StringBuilder sb_szLatestDate;
               if ( szLatestDate == null )
                  sb_szLatestDate = new StringBuilder( 32 );
               else
                  sb_szLatestDate = new StringBuilder( szLatestDate );
                               GetVariableFromAttribute( sb_szLatestDate, mi_lTempInteger_0, 'S', 9, mProspct, "FAISIR", "TransactionProcessedDate", "", 0 );
               lTempInteger_0 = mi_lTempInteger_0.intValue( );
               szLatestDate = sb_szLatestDate.toString( );}
            } 

            RESULT = SetCursorNextEntity( mProspct, "FAISIR", "Prospect" );
            //:END
         } 

         //:END

         //:StoreStringInRecord ( mProspct,
         //:                   InternalEntityStructure, InternalAttribStructure, szLatestDate )
         StoreStringInRecord( mProspct, InternalEntityStructure, InternalAttribStructure, szLatestDate );
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
//:GenerateStudentRecord( VIEW mProspct BASED ON LOD mProspct )

//:   VIEW wXferO    REGISTERED AS wXferO
public int 
omProspct_GenerateStudentRecord( View     mProspct )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mProsStu  BASED ON LOD  mProsStu
   zVIEW    mProsStu = new zVIEW( );
   //:VIEW mProsStu2 BASED ON LOD  mProsStu
   zVIEW    mProsStu2 = new zVIEW( );
   //:VIEW mStuChgH  BASED ON LOD  mStuChgH
   zVIEW    mStuChgH = new zVIEW( );
   //:VIEW mStuChgH2 BASED ON LOD  mStuChgH
   zVIEW    mStuChgH2 = new zVIEW( );
   //:VIEW mUser     BASED ON LOD  mUser
   zVIEW    mUser = new zVIEW( );
   //:STRING ( 1 )  MiddleInitial
   String   MiddleInitial = null;
   //:STRING ( 50 ) eMailAddress
   String   eMailAddress = null;
   //:STRING ( 50 ) eMailAddressTemp
   String   eMailAddressTemp = null;
   //:STRING ( 2 )  szCount
   String   szCount = null;
   //:STRING ( 9 )  szCampusID
   String   szCampusID = null;
   //:STRING ( 6 )  szPersonID
   String   szPersonID = null;
   //:STRING ( 1 )  szModifiedFlag
   String   szModifiedFlag = null;
   //:STRING ( 1 )  szStudentDidExistFlag
   String   szStudentDidExistFlag = null;
   //:STRING ( 1 )  szChangeFlag
   String   szChangeFlag = null;
   //:STRING ( 8 )  szCurrentDate
   String   szCurrentDate = null;
   //:SHORT         StringLength
   int      StringLength = 0;
   //:INTEGER       Count
   int      Count = 0;
   //:INTEGER       FillerBytes
   int      FillerBytes = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   int      lTempInteger_8 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mProspct, zLEVEL_TASK );

   //:// Generate a new Student record for the Prospect, generating CampusID and Student EmailAddress.

   //:// The following was removed by DonC on 8/12/09 because we decided to generate a Student record before
   //:// a Prospect was admitted and before SSN was required.
   //:// We will not generate a Student record if the Prospect does not have an SSN.
   //://IF mProspct.Person.SSN = ""
   //://   MessageSend( mProspct, "", "Generate Student Record", 
   //://                "A student record will not be generated because the Prospect does not have an SSN specified.", 
   //://                zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //://   RETURN -1
   //://END

   //:ACTIVATE mProsStu WHERE mProsStu.Prospect.ID = mProspct.Prospect.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mProspct, "Prospect", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omProspct_fnLocalBuildQual_2( mProspct, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mProsStu, "mProsStu", mProspct, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mProsStu "mProsStu"
   SetNameForView( mProsStu, "mProsStu", null, zLEVEL_TASK );
   //:szModifiedFlag = ""
    {StringBuilder sb_szModifiedFlag;
   if ( szModifiedFlag == null )
      sb_szModifiedFlag = new StringBuilder( 32 );
   else
      sb_szModifiedFlag = new StringBuilder( szModifiedFlag );
      ZeidonStringCopy( sb_szModifiedFlag, 1, 0, "", 1, 0, 2 );
   szModifiedFlag = sb_szModifiedFlag.toString( );}
   //:szStudentDidExistFlag = ""
    {StringBuilder sb_szStudentDidExistFlag;
   if ( szStudentDidExistFlag == null )
      sb_szStudentDidExistFlag = new StringBuilder( 32 );
   else
      sb_szStudentDidExistFlag = new StringBuilder( szStudentDidExistFlag );
      ZeidonStringCopy( sb_szStudentDidExistFlag, 1, 0, "", 1, 0, 2 );
   szStudentDidExistFlag = sb_szStudentDidExistFlag.toString( );}

   //:// Try to find a Student with the same SSN. If so, we will use that mProsStu object for
   //:// generating email address and Campus ID.
   //:IF mProsStu.Person.SSN != ""
   if ( CompareAttributeToString( mProsStu, "Person", "SSN", "" ) != 0 )
   { 
      //:ACTIVATE mProsStu2 WHERE mProsStu2.Person.SSN = mProsStu.Person.SSN
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, mProsStu, "Person", "SSN" );
      szTempString_0 = sb_szTempString_0.toString( );}
      omProspct_fnLocalBuildQual_3( mProspct, vTempViewVar_1, szTempString_0 );
      RESULT = ActivateObjectInstance( mProsStu2, "mProsStu", mProspct, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:DropObjectInstance( mProsStu )
         DropObjectInstance( mProsStu );
         //:mProsStu = mProsStu2
         SetViewFromView( mProsStu, mProsStu2 );
         //:NAME VIEW mProsStu "mProsStu"
         SetNameForView( mProsStu, "mProsStu", null, zLEVEL_TASK );
         //:IF mProsStu.Student EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mProsStu, "Student" );
         if ( lTempInteger_1 == 0 )
         { 
            //:szStudentDidExistFlag = "Y"
             {StringBuilder sb_szStudentDidExistFlag;
            if ( szStudentDidExistFlag == null )
               sb_szStudentDidExistFlag = new StringBuilder( 32 );
            else
               sb_szStudentDidExistFlag = new StringBuilder( szStudentDidExistFlag );
                        ZeidonStringCopy( sb_szStudentDidExistFlag, 1, 0, "Y", 1, 0, 2 );
            szStudentDidExistFlag = sb_szStudentDidExistFlag.toString( );}
         } 

         //:END 
         //:ELSE
      } 
      else
      { 
         //:DropObjectInstance( mProsStu2 )
         DropObjectInstance( mProsStu2 );
      } 

      //:END
   } 

   //:END

   //:IF mProsStu.Student DOES NOT EXIST
   lTempInteger_2 = CheckExistenceOfEntity( mProsStu, "Student" );
   if ( lTempInteger_2 != 0 )
   { 
      //:CREATE ENTITY mProsStu.Student 
      RESULT = CreateEntity( mProsStu, "Student", zPOS_AFTER );
      //:SetAttributeFromCurrentDateTime( mProsStu, "Student", "CreationDate" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mProsStu );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mProsStu, "Student", "CreationDate" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:IF mProspct.Prospect.DepositDate != ""
      if ( CompareAttributeToString( mProspct, "Prospect", "DepositDate", "" ) != 0 )
      { 
         //:mProsStu.Student.Status = "D"
         SetAttributeFromString( mProsStu, "Student", "Status", "D" );
         //:ELSE
      } 
      else
      { 
         //:mProsStu.Student.Status = "P"
         SetAttributeFromString( mProsStu, "Student", "Status", "P" );
      } 

      //:END
      //:IF mProspct.CollegeAdministrativeDivision EXISTS
      lTempInteger_3 = CheckExistenceOfEntity( mProspct, "CollegeAdministrativeDivision" );
      if ( lTempInteger_3 == 0 )
      { 
         //:INCLUDE  mProsStu.AdministrativeDivision FROM mProspct.CollegeAdministrativeDivision 
         RESULT = IncludeSubobjectFromSubobject( mProsStu, "AdministrativeDivision", mProspct, "CollegeAdministrativeDivision", zPOS_AFTER );
         //:ELSE
      } 
      else
      { 
         //:INCLUDE  mProsStu.AdministrativeDivision FROM mProspct.AdministrativeDivision 
         RESULT = IncludeSubobjectFromSubobject( mProsStu, "AdministrativeDivision", mProspct, "AdministrativeDivision", zPOS_AFTER );
      } 

      //:END
      //:GetViewByName( mUser, "mUser", mProspct, zLEVEL_APPLICATION )
      GetViewByName( mUser, "mUser", mProspct, zLEVEL_APPLICATION );
      //:mProsStu.Student.DefaultPrintTranscriptType = "N"
      SetAttributeFromString( mProsStu, "Student", "DefaultPrintTranscriptType", "N" );
      //:IF mProsStu.Student.CurrentLevel = ""
      if ( CompareAttributeToString( mProsStu, "Student", "CurrentLevel", "" ) == 0 )
      { 
         //:mProsStu.Student.CurrentLevel = "1"
         SetAttributeFromString( mProsStu, "Student", "CurrentLevel", "1" );
      } 

      //:END
      //:mProsStu.Student.CreatedDateTime  = mProsStu.Student.CreationDate 
      SetAttributeFromAttribute( mProsStu, "Student", "CreatedDateTime", mProsStu, "Student", "CreationDate" );
      //:mProsStu.Student.ModifiedDateTime = mProsStu.Student.CreationDate 
      SetAttributeFromAttribute( mProsStu, "Student", "ModifiedDateTime", mProsStu, "Student", "CreationDate" );
      //:mProsStu.Student.CreatedBy        = mUser.User.UserName 
      SetAttributeFromAttribute( mProsStu, "Student", "CreatedBy", mUser, "User", "UserName" );
      //:mProsStu.Student.LastModifiedBy   = mUser.User.UserName
      SetAttributeFromAttribute( mProsStu, "Student", "LastModifiedBy", mUser, "User", "UserName" );
      //:szModifiedFlag = "Y"
       {StringBuilder sb_szModifiedFlag;
      if ( szModifiedFlag == null )
         sb_szModifiedFlag = new StringBuilder( 32 );
      else
         sb_szModifiedFlag = new StringBuilder( szModifiedFlag );
            ZeidonStringCopy( sb_szModifiedFlag, 1, 0, "Y", 1, 0, 2 );
      szModifiedFlag = sb_szModifiedFlag.toString( );}

      //:// Default Residency Status to "Resident" for main campus undergraduate students.
      //:// Otherwise, the default is Commuter.
      //:IF mProspct.College EXISTS AND mProspct.AdministrativeDivision.ID = 1
      lTempInteger_4 = CheckExistenceOfEntity( mProspct, "College" );
      if ( lTempInteger_4 == 0 && CompareAttributeToInteger( mProspct, "AdministrativeDivision", "ID", 1 ) == 0 )
      { 
         //:IF mProspct.College.Type = "U"
         if ( CompareAttributeToString( mProspct, "College", "Type", "U" ) == 0 )
         { 
            //:mProsStu.Student.ResidencyStatus  = "R"
            SetAttributeFromString( mProsStu, "Student", "ResidencyStatus", "R" );
            //:ELSE
         } 
         else
         { 
            //:mProsStu.Student.ResidencyStatus  = "C"
            SetAttributeFromString( mProsStu, "Student", "ResidencyStatus", "C" );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:mProsStu.Student.ResidencyStatus  = "C"
         SetAttributeFromString( mProsStu, "Student", "ResidencyStatus", "C" );
      } 

      //:END
      //:ELSE
   } 
   else
   { 

      //:// Set Student Status if Prospect has deposited.
      //:IF mProspct.Prospect.DepositDate != "" AND mProsStu.Student.Status = "P"
      if ( CompareAttributeToString( mProspct, "Prospect", "DepositDate", "" ) != 0 && CompareAttributeToString( mProsStu, "Student", "Status", "P" ) == 0 )
      { 
         //:mProsStu.Student.Status = "D"
         SetAttributeFromString( mProsStu, "Student", "Status", "D" );
      } 

      //:END
   } 

   //:END

   //:// Set Graduate Level if a Graduate College has been selected.
   //:IF mProspct.CollegeAdministrativeDivision EXISTS
   lTempInteger_5 = CheckExistenceOfEntity( mProspct, "CollegeAdministrativeDivision" );
   if ( lTempInteger_5 == 0 )
   { 
      //:IF mProspct.College.Type = "G" AND mProsStu.Student.CurrentLevel != "6"
      if ( CompareAttributeToString( mProspct, "College", "Type", "G" ) == 0 && CompareAttributeToString( mProsStu, "Student", "CurrentLevel", "6" ) != 0 )
      { 
         //:mProsStu.Student.CurrentLevel = "6"
         SetAttributeFromString( mProsStu, "Student", "CurrentLevel", "6" );
      } 

      //:END
   } 

   //:END

   //:// emailAddress  (Removed on 11/10/2005 because IT wanted addresses set by hand.)
   //:/*IF mProsStu.Student.eMailAddress = ""
   //:   // Go to generate and set the student's email address.
   //:   GenAndSetStudentEmailAddress( mProsStu, "Student", "Person" )
   //:   szModifiedFlag = "Y"
   //:END */

   //:// CampusID
   //:// The ID will be generated by the prefix, "182", plus the Person ID. This will generate a unique
   //:// ID because no non-ZENCAS ID's start with 182 and, within ZENCAS, the Person ID is unique.
   //:IF mProsStu.Person.CampusID = ""
   if ( CompareAttributeToString( mProsStu, "Person", "CampusID", "" ) == 0 )
   { 
      //:szPersonID = mProsStu.Person.ID 
      {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
      StringBuilder sb_szPersonID;
      if ( szPersonID == null )
         sb_szPersonID = new StringBuilder( 32 );
      else
         sb_szPersonID = new StringBuilder( szPersonID );
             GetVariableFromAttribute( sb_szPersonID, mi_lTempInteger_6, 'S', 7, mProsStu, "Person", "ID", "", 0 );
      lTempInteger_6 = mi_lTempInteger_6.intValue( );
      szPersonID = sb_szPersonID.toString( );}
      //:StringLength = zstrlen( szPersonID )
      StringLength = zstrlen( szPersonID );
      //:FillerBytes = 6 - StringLength
      FillerBytes = 6 - StringLength;
      //:szCampusID = "182"
       {StringBuilder sb_szCampusID;
      if ( szCampusID == null )
         sb_szCampusID = new StringBuilder( 32 );
      else
         sb_szCampusID = new StringBuilder( szCampusID );
            ZeidonStringCopy( sb_szCampusID, 1, 0, "182", 1, 0, 10 );
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
                  ZeidonStringConcat( sb_szCampusID, 1, 0, "0", 1, 0, 10 );
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
            ZeidonStringConcat( sb_szCampusID, 1, 0, szPersonID, 1, 0, 10 );
      szCampusID = sb_szCampusID.toString( );}
      //:mProsStu.Person.CampusID = szCampusID
      SetAttributeFromString( mProsStu, "Person", "CampusID", szCampusID );
      //:szModifiedFlag = "Y"
       {StringBuilder sb_szModifiedFlag;
      if ( szModifiedFlag == null )
         sb_szModifiedFlag = new StringBuilder( 32 );
      else
         sb_szModifiedFlag = new StringBuilder( szModifiedFlag );
            ZeidonStringCopy( sb_szModifiedFlag, 1, 0, "Y", 1, 0, 2 );
      szModifiedFlag = sb_szModifiedFlag.toString( );}
   } 

   //:END

   //:// COMMIT AND STUDENT CHANGE LOG HISTORY
   //:IF szModifiedFlag = "Y" 
   if ( ZeidonStringCompare( szModifiedFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:// First commit the Prospect/Student record.
      //:COMMIT mProsStu
      RESULT = CommitObjectInstance( mProsStu );

      //:// If Student record is anything but Pre-Deposit, see if we need to update the Student History entry.
      //:IF mProsStu.Student.Status != "P"
      if ( CompareAttributeToString( mProsStu, "Student", "Status", "P" ) != 0 )
      { 
         //:ACTIVATE mStuChgH WHERE mStuChgH.StudentChangeHistory.LastDate = ""
         //:                    AND mStuChgH.Student.ID = mProsStu.Student.ID 
         {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                   GetIntegerFromAttribute( mi_lTempInteger_7, mProsStu, "Student", "ID" );
         lTempInteger_7 = mi_lTempInteger_7.intValue( );}
         omProspct_fnLocalBuildQual_4( mProspct, vTempViewVar_2, lTempInteger_7 );
         RESULT = ActivateObjectInstance( mStuChgH, "mStuChgH", mProspct, vTempViewVar_2, zSINGLE );
         DropView( vTempViewVar_2 );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:// Since no history record has yet been created, create it here.
            //:ACTIVATE mStuChgH EMPTY 
            RESULT = ActivateEmptyObjectInstance( mStuChgH, "mStuChgH", mProspct, zSINGLE );
            //:CREATE ENTITY mStuChgH.StudentChangeHistory 
            RESULT = CreateEntity( mStuChgH, "StudentChangeHistory", zPOS_AFTER );
            //:mStuChgH.StudentChangeHistory.StudentStatus              = mProsStu.Student.Status 
            SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "StudentStatus", mProsStu, "Student", "Status" );
            //:mStuChgH.StudentChangeHistory.Classification             = mProsStu.Student.CurrentLevel 
            SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "Classification", mProsStu, "Student", "CurrentLevel" );
            //:mStuChgH.StudentChangeHistory.ResidencyStatus            = mProsStu.Student.ResidencyStatus
            SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "ResidencyStatus", mProsStu, "Student", "ResidencyStatus" );
            //:mStuChgH.StudentChangeHistory.AdministrativeDivisionName = mProsStu.AdministrativeDivision.Name 
            SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "AdministrativeDivisionName", mProsStu, "AdministrativeDivision", "Name" );
            //:SetAttributeFromCurrentDateTime( mStuChgH, "StudentChangeHistory", "FirstDate" ) 
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mStuChgH );
             m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mStuChgH, "StudentChangeHistory", "FirstDate" );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:INCLUDE mStuChgH.Student FROM mProsStu.Student
            RESULT = IncludeSubobjectFromSubobject( mStuChgH, "Student", mProsStu, "Student", zPOS_AFTER );
            //:COMMIT mStuChgH
            RESULT = CommitObjectInstance( mStuChgH );
            //:DropObjectInstance( mStuChgH )
            DropObjectInstance( mStuChgH );
            //:ELSE
         } 
         else
         { 
            //:IF mProsStu.Student.Status != mStuChgH.StudentChangeHistory.StudentStatus 
            if ( CompareAttributeToAttribute( mProsStu, "Student", "Status", mStuChgH, "StudentChangeHistory", "StudentStatus" ) != 0 )
            { 
               //:szChangeFlag = "Y"
                {StringBuilder sb_szChangeFlag;
               if ( szChangeFlag == null )
                  sb_szChangeFlag = new StringBuilder( 32 );
               else
                  sb_szChangeFlag = new StringBuilder( szChangeFlag );
                              ZeidonStringCopy( sb_szChangeFlag, 1, 0, "Y", 1, 0, 2 );
               szChangeFlag = sb_szChangeFlag.toString( );}
            } 

            //:END
            //:IF mProsStu.Student.CurrentLevel != mStuChgH.StudentChangeHistory.Classification 
            if ( CompareAttributeToAttribute( mProsStu, "Student", "CurrentLevel", mStuChgH, "StudentChangeHistory", "Classification" ) != 0 )
            { 
               //:szChangeFlag = "Y"
                {StringBuilder sb_szChangeFlag;
               if ( szChangeFlag == null )
                  sb_szChangeFlag = new StringBuilder( 32 );
               else
                  sb_szChangeFlag = new StringBuilder( szChangeFlag );
                              ZeidonStringCopy( sb_szChangeFlag, 1, 0, "Y", 1, 0, 2 );
               szChangeFlag = sb_szChangeFlag.toString( );}
            } 

            //:END
            //:IF mProsStu.AdministrativeDivision.Name != mStuChgH.StudentChangeHistory.AdministrativeDivisionName 
            if ( CompareAttributeToAttribute( mProsStu, "AdministrativeDivision", "Name", mStuChgH, "StudentChangeHistory", "AdministrativeDivisionName" ) != 0 )
            { 
               //:szChangeFlag = "Y"
                {StringBuilder sb_szChangeFlag;
               if ( szChangeFlag == null )
                  sb_szChangeFlag = new StringBuilder( 32 );
               else
                  sb_szChangeFlag = new StringBuilder( szChangeFlag );
                              ZeidonStringCopy( sb_szChangeFlag, 1, 0, "Y", 1, 0, 2 );
               szChangeFlag = sb_szChangeFlag.toString( );}
            } 

            //:END
            //:IF mProsStu.Student.ResidencyStatus != mStuChgH.StudentChangeHistory.ResidencyStatus 
            if ( CompareAttributeToAttribute( mProsStu, "Student", "ResidencyStatus", mStuChgH, "StudentChangeHistory", "ResidencyStatus" ) != 0 )
            { 
               //:szChangeFlag = "Y"
                {StringBuilder sb_szChangeFlag;
               if ( szChangeFlag == null )
                  sb_szChangeFlag = new StringBuilder( 32 );
               else
                  sb_szChangeFlag = new StringBuilder( szChangeFlag );
                              ZeidonStringCopy( sb_szChangeFlag, 1, 0, "Y", 1, 0, 2 );
               szChangeFlag = sb_szChangeFlag.toString( );}
            } 

            //:END

            //:// If any of the key attributes have changed, then we will:
            //:// 1. Create a new Student Change History entry, if the current one was not created today.
            //:// 2. Update the current Student Change History entry, if it was created today.
            //:IF szChangeFlag = "Y"
            if ( ZeidonStringCompare( szChangeFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
            { 
               //:szCurrentDate = wXferO.Root.dCurrentDate
               {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
               StringBuilder sb_szCurrentDate;
               if ( szCurrentDate == null )
                  sb_szCurrentDate = new StringBuilder( 32 );
               else
                  sb_szCurrentDate = new StringBuilder( szCurrentDate );
                               GetVariableFromAttribute( sb_szCurrentDate, mi_lTempInteger_8, 'S', 9, wXferO, "Root", "dCurrentDate", "", 0 );
               lTempInteger_8 = mi_lTempInteger_8.intValue( );
               szCurrentDate = sb_szCurrentDate.toString( );}
               //:IF mStuChgH.StudentChangeHistory.FirstDate = szCurrentDate
               if ( CompareAttributeToString( mStuChgH, "StudentChangeHistory", "FirstDate", szCurrentDate ) == 0 )
               { 
                  //:// The entry was created today, so just update it.
                  //:// Note that we can just set all the attributes, without regard to which ones changed,
                  //:// since this entity is a record of attribute values as of this point in time.
                  //:mStuChgH2.StudentChangeHistory.StudentStatus              = mProsStu.Student.Status 
                  SetAttributeFromAttribute( mStuChgH2, "StudentChangeHistory", "StudentStatus", mProsStu, "Student", "Status" );
                  //:mStuChgH2.StudentChangeHistory.Classification             = mProsStu.Student.CurrentLevel 
                  SetAttributeFromAttribute( mStuChgH2, "StudentChangeHistory", "Classification", mProsStu, "Student", "CurrentLevel" );
                  //:mStuChgH2.StudentChangeHistory.ResidencyStatus            = mProsStu.Student.ResidencyStatus
                  SetAttributeFromAttribute( mStuChgH2, "StudentChangeHistory", "ResidencyStatus", mProsStu, "Student", "ResidencyStatus" );
                  //:mStuChgH2.StudentChangeHistory.AdministrativeDivisionName = mProsStu.AdministrativeDivision.Name 
                  SetAttributeFromAttribute( mStuChgH2, "StudentChangeHistory", "AdministrativeDivisionName", mProsStu, "AdministrativeDivision", "Name" );
                  //:COMMIT mStuChgH
                  RESULT = CommitObjectInstance( mStuChgH );
                  //:DropObjectInstance( mStuChgH )
                  DropObjectInstance( mStuChgH );
                  //:ELSE
               } 
               else
               { 
                  //:// The entry was created earlier, so create a new entry with the new values
                  //:ACTIVATE mStuChgH2 EMPTY 
                  RESULT = ActivateEmptyObjectInstance( mStuChgH2, "mStuChgH", mProspct, zSINGLE );
                  //:CREATE ENTITY mStuChgH2.StudentChangeHistory 
                  RESULT = CreateEntity( mStuChgH2, "StudentChangeHistory", zPOS_AFTER );
                  //:SetMatchingAttributesByName( mStuChgH2, "StudentChangeHistory", mStuChgH, "StudentChangeHistory", zSET_NULL )
                  SetMatchingAttributesByName( mStuChgH2, "StudentChangeHistory", mStuChgH, "StudentChangeHistory", zSET_NULL );
                  //:mStuChgH2.StudentChangeHistory.StudentStatus              = mProsStu.Student.Status 
                  SetAttributeFromAttribute( mStuChgH2, "StudentChangeHistory", "StudentStatus", mProsStu, "Student", "Status" );
                  //:mStuChgH2.StudentChangeHistory.Classification             = mProsStu.Student.CurrentLevel 
                  SetAttributeFromAttribute( mStuChgH2, "StudentChangeHistory", "Classification", mProsStu, "Student", "CurrentLevel" );
                  //:mStuChgH2.StudentChangeHistory.ResidencyStatus            = mProsStu.Student.ResidencyStatus
                  SetAttributeFromAttribute( mStuChgH2, "StudentChangeHistory", "ResidencyStatus", mProsStu, "Student", "ResidencyStatus" );
                  //:mStuChgH2.StudentChangeHistory.AdministrativeDivisionName = mProsStu.AdministrativeDivision.Name
                  SetAttributeFromAttribute( mStuChgH2, "StudentChangeHistory", "AdministrativeDivisionName", mProsStu, "AdministrativeDivision", "Name" );
                  //:SetAttributeFromCurrentDateTime( mStuChgH2, "StudentChangeHistory", "FirstDate" )
                  {
                   ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mStuChgH2 );
                   m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mStuChgH2, "StudentChangeHistory", "FirstDate" );
                   // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                  }
                  //:INCLUDE mStuChgH2.Student FROM mProsStu.Student
                  RESULT = IncludeSubobjectFromSubobject( mStuChgH2, "Student", mProsStu, "Student", zPOS_AFTER );

                  //:// Set the LastDate attribute in the previous history entry to yesterday.
                  //:SetAttributeFromCurrentDateTime( wXferO, "Root", "WorkDate" )
                  {
                   ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
                   m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( wXferO, "Root", "WorkDate" );
                   // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                  }
                  //:AddDaysToDate( wXferO, "Root", "WorkDate", -1 )
                  {
                   ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
                   m_ZGLOBAL1_Operation.AddDaysToDate( wXferO, "Root", "WorkDate", -1 );
                   // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                  }
                  //:mStuChgH.StudentChangeHistory.LastDate = wXferO.Root.WorkDate 
                  SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "LastDate", wXferO, "Root", "WorkDate" );

                  //:COMMIT mStuChgH
                  RESULT = CommitObjectInstance( mStuChgH );
                  //:COMMIT mStuChgH2
                  RESULT = CommitObjectInstance( mStuChgH2 );
                  //:DropObjectInstance( mStuChgH2 )
                  DropObjectInstance( mStuChgH2 );
                  //:DropObjectInstance( mStuChgH )
                  DropObjectInstance( mStuChgH );
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
   //:DropObjectInstance( mProsStu )
   DropObjectInstance( mProsStu );

   //:IF szStudentDidExistFlag = "Y"
   if ( ZeidonStringCompare( szStudentDidExistFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:RETURN -1
      if(8==8)return( -1 );
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
//:SetAutoProspectData( VIEW mProspct BASED ON LOD mProspct,
//:                     VIEW mPerson  BASED ON LOD mPerson )

//:   VIEW mProspct2 BASED ON LOD mProspct
public int 
omProspct_SetAutoProspectData( View     mProspct,
                               View     mPerson )
{
   zVIEW    mProspct2 = new zVIEW( );
   //:VIEW mAdmDiv   BASED ON LOD mAdmDiv
   zVIEW    mAdmDiv = new zVIEW( );
   //:STRING ( 2 ) szProspectType
   String   szProspectType = null;
   //:STRING ( 5 ) szEntranceClassification
   String   szEntranceClassification = null;
   //:STRING ( 1 ) szSetTrackFlag
   String   szSetTrackFlag = null;
   //:INTEGER      AdmissionsTrackID
   int      AdmissionsTrackID = 0;
   //:INTEGER      HighSATReading
   int      HighSATReading = 0;
   //:INTEGER      HighSATMath
   int      HighSATMath = 0;
   //:INTEGER      HighSATWriting
   int      HighSATWriting = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;


   //:GET VIEW mAdmDiv NAMED "mAdmDivTracks"
   RESULT = GetViewByName( mAdmDiv, "mAdmDivTracks", mProspct, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mAdmDiv WHERE mAdmDiv.AdministrativeDivision.ID = mProspct.AdministrativeDivision.ID 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, mProspct, "AdministrativeDivision", "ID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      //:      RESTRICTING mAdmDiv.FinAidRequirement TO mAdmDiv.FinAidRequirement.ID = 0
      omProspct_fnLocalBuildQual_5( mProspct, vTempViewVar_0, lTempInteger_0 );
      RESULT = ActivateObjectInstance( mAdmDiv, "mAdmDiv", mProspct, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mAdmDiv "mAdmDivTracks"
      SetNameForView( mAdmDiv, "mAdmDivTracks", null, zLEVEL_TASK );
   } 

   //:END
   //:szProspectType = mProspct.Prospect.Type
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szProspectType;
   if ( szProspectType == null )
      sb_szProspectType = new StringBuilder( 32 );
   else
      sb_szProspectType = new StringBuilder( szProspectType );
       GetVariableFromAttribute( sb_szProspectType, mi_lTempInteger_1, 'S', 3, mProspct, "Prospect", "Type", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szProspectType = sb_szProspectType.toString( );}

   //:// Only do for Main Campus (ie., when Admin Div has no Cohorts).
   //:IF mProspct.AdministrativeDivision.UsesCohortsFlag != "Y"
   if ( CompareAttributeToString( mProspct, "AdministrativeDivision", "UsesCohortsFlag", "Y" ) != 0 )
   { 
      //:// Set certain Prospect Data automatically, such as DepositDate.
      //:IF szProspectType = "6"
      if ( ZeidonStringCompare( szProspectType, 1, 0, "6", 1, 0, 3 ) == 0 )
      { 
         //:IF mProspct.Prospect.DepositDate = ""
         if ( CompareAttributeToString( mProspct, "Prospect", "DepositDate", "" ) == 0 )
         { 
            //:SetAttributeFromCurrentDateTime( mProspct, "Prospect", "DepositDate" )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mProspct );
             m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mProspct, "Prospect", "DepositDate" );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
         } 

         //:END
         //:IF mProspct.Prospect.AdmissionDate = ""
         if ( CompareAttributeToString( mProspct, "Prospect", "AdmissionDate", "" ) == 0 )
         { 
            //:mProspct.Prospect.AdmissionDate = mProspct.Prospect.DepositDate 
            SetAttributeFromAttribute( mProspct, "Prospect", "AdmissionDate", mProspct, "Prospect", "DepositDate" );
         } 

         //:END
         //:IF mProspct.Prospect.ApplicationDate = ""
         if ( CompareAttributeToString( mProspct, "Prospect", "ApplicationDate", "" ) == 0 )
         { 
            //:mProspct.Prospect.ApplicationDate = mProspct.Prospect.DepositDate 
            SetAttributeFromAttribute( mProspct, "Prospect", "ApplicationDate", mProspct, "Prospect", "DepositDate" );
         } 

         //:END
         //:IF mProspct.Prospect.LeadDate = ""
         if ( CompareAttributeToString( mProspct, "Prospect", "LeadDate", "" ) == 0 )
         { 
            //:mProspct.Prospect.LeadDate = mProspct.Prospect.DepositDate 
            SetAttributeFromAttribute( mProspct, "Prospect", "LeadDate", mProspct, "Prospect", "DepositDate" );
         } 

         //:END
         //:IF mProspct.Prospect.InquiryDate = ""
         if ( CompareAttributeToString( mProspct, "Prospect", "InquiryDate", "" ) == 0 )
         { 
            //:mProspct.Prospect.InquiryDate = mProspct.Prospect.DepositDate 
            SetAttributeFromAttribute( mProspct, "Prospect", "InquiryDate", mProspct, "Prospect", "DepositDate" );
         } 

         //:END
      } 

      //:END
      //:IF szProspectType = "5"
      if ( ZeidonStringCompare( szProspectType, 1, 0, "5", 1, 0, 3 ) == 0 )
      { 
         //:IF mProspct.Prospect.AdmissionDate = ""
         if ( CompareAttributeToString( mProspct, "Prospect", "AdmissionDate", "" ) == 0 )
         { 
            //:SetAttributeFromCurrentDateTime( mProspct, "Prospect", "AdmissionDate" )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mProspct );
             m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mProspct, "Prospect", "AdmissionDate" );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
         } 

         //:END
         //:IF mProspct.Prospect.ApplicationDate = ""
         if ( CompareAttributeToString( mProspct, "Prospect", "ApplicationDate", "" ) == 0 )
         { 
            //:mProspct.Prospect.ApplicationDate = mProspct.Prospect.AdmissionDate 
            SetAttributeFromAttribute( mProspct, "Prospect", "ApplicationDate", mProspct, "Prospect", "AdmissionDate" );
         } 

         //:END
         //:IF mProspct.Prospect.LeadDate = ""
         if ( CompareAttributeToString( mProspct, "Prospect", "LeadDate", "" ) == 0 )
         { 
            //:mProspct.Prospect.LeadDate = mProspct.Prospect.AdmissionDate 
            SetAttributeFromAttribute( mProspct, "Prospect", "LeadDate", mProspct, "Prospect", "AdmissionDate" );
         } 

         //:END
         //:IF mProspct.Prospect.InquiryDate = ""
         if ( CompareAttributeToString( mProspct, "Prospect", "InquiryDate", "" ) == 0 )
         { 
            //:mProspct.Prospect.InquiryDate = mProspct.Prospect.AdmissionDate 
            SetAttributeFromAttribute( mProspct, "Prospect", "InquiryDate", mProspct, "Prospect", "AdmissionDate" );
         } 

         //:END
      } 

      //:END
      //:IF szProspectType = "4"
      if ( ZeidonStringCompare( szProspectType, 1, 0, "4", 1, 0, 3 ) == 0 )
      { 
         //:IF mProspct.Prospect.ApplicationDate = ""
         if ( CompareAttributeToString( mProspct, "Prospect", "ApplicationDate", "" ) == 0 )
         { 
            //:SetAttributeFromCurrentDateTime( mProspct, "Prospect", "ApplicationDate" )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mProspct );
             m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mProspct, "Prospect", "ApplicationDate" );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
         } 

         //:END
         //:IF mProspct.Prospect.LeadDate = ""
         if ( CompareAttributeToString( mProspct, "Prospect", "LeadDate", "" ) == 0 )
         { 
            //:mProspct.Prospect.LeadDate = mProspct.Prospect.ApplicationDate 
            SetAttributeFromAttribute( mProspct, "Prospect", "LeadDate", mProspct, "Prospect", "ApplicationDate" );
         } 

         //:END
         //:IF mProspct.Prospect.InquiryDate = ""
         if ( CompareAttributeToString( mProspct, "Prospect", "InquiryDate", "" ) == 0 )
         { 
            //:mProspct.Prospect.InquiryDate = mProspct.Prospect.ApplicationDate 
            SetAttributeFromAttribute( mProspct, "Prospect", "InquiryDate", mProspct, "Prospect", "ApplicationDate" );
         } 

         //:END
      } 

      //:END
      //:IF szProspectType = "2"
      if ( ZeidonStringCompare( szProspectType, 1, 0, "2", 1, 0, 3 ) == 0 )
      { 
         //:IF mProspct.Prospect.InquiryDate = ""
         if ( CompareAttributeToString( mProspct, "Prospect", "InquiryDate", "" ) == 0 )
         { 
            //:mProspct.Prospect.InquiryDate = mProspct.Prospect.LeadDate 
            SetAttributeFromAttribute( mProspct, "Prospect", "InquiryDate", mProspct, "Prospect", "LeadDate" );
         } 

         //:END
      } 

      //:END

      //:// If Lead, Applied, Admitted or Deposit dates are set, make sure that the Type is at least at that level.
      //:IF mProspct.Prospect.DepositDate != "" AND
      //:   ( szProspectType = "1" OR szProspectType = "2" OR szProspectType = "4" OR szProspectType = "5" )
      if ( CompareAttributeToString( mProspct, "Prospect", "DepositDate", "" ) != 0 && ( ZeidonStringCompare( szProspectType, 1, 0, "1", 1, 0, 3 ) == 0 || ZeidonStringCompare( szProspectType, 1, 0, "2", 1, 0, 3 ) == 0 ||
           ZeidonStringCompare( szProspectType, 1, 0, "4", 1, 0, 3 ) == 0 || ZeidonStringCompare( szProspectType, 1, 0, "5", 1, 0, 3 ) == 0 ) )
      { 

         //:mProspct.Prospect.Type = "6"
         SetAttributeFromString( mProspct, "Prospect", "Type", "6" );
      } 

      //:END
      //:IF mProspct.Prospect.AdmissionDate != "" AND
      //:   ( szProspectType = "1" OR szProspectType = "2" OR szProspectType = "4" )
      if ( CompareAttributeToString( mProspct, "Prospect", "AdmissionDate", "" ) != 0 && ( ZeidonStringCompare( szProspectType, 1, 0, "1", 1, 0, 3 ) == 0 || ZeidonStringCompare( szProspectType, 1, 0, "2", 1, 0, 3 ) == 0 ||
           ZeidonStringCompare( szProspectType, 1, 0, "4", 1, 0, 3 ) == 0 ) )
      { 

         //:mProspct.Prospect.Type = "5"
         SetAttributeFromString( mProspct, "Prospect", "Type", "5" );
      } 

      //:END
      //:IF mProspct.Prospect.ApplicationDate != "" AND
      //:   ( szProspectType = "1" OR szProspectType = "2" )
      if ( CompareAttributeToString( mProspct, "Prospect", "ApplicationDate", "" ) != 0 && ( ZeidonStringCompare( szProspectType, 1, 0, "1", 1, 0, 3 ) == 0 || ZeidonStringCompare( szProspectType, 1, 0, "2", 1, 0, 3 ) == 0 ) )
      { 

         //:mProspct.Prospect.Type = "4"
         SetAttributeFromString( mProspct, "Prospect", "Type", "4" );
      } 

      //:END
      //:IF mProspct.Prospect.LeadDate != "" AND szProspectType = "1"
      if ( CompareAttributeToString( mProspct, "Prospect", "LeadDate", "" ) != 0 && ZeidonStringCompare( szProspectType, 1, 0, "1", 1, 0, 3 ) == 0 )
      { 
         //:mProspct.Prospect.Type = "2"
         SetAttributeFromString( mProspct, "Prospect", "Type", "2" );
      } 

      //:END

      //:// Set Admission Tracks for Main Campus based on Entrance Classification and some other data.
      //:// Freshman Rules
      //://    Freshman and International:                       Freshman International (ID = 20)
      //://    Freshman National with ACT < 18 and SAT < 480:    Placement Testing Required (ID = 16)
      //://    Freshman National with OK scores:                 Freshman Track  (ID = 7)
      //:// Transfer Rules
      //://    Transfer and International:                       Transfer International (ID = 8)
      //://    Transfer National                                 Transfer (ID = 15)
      //:// Re-admitted                                          Re-Admit (ID = 14)
      //:// Special Student                                      Non-Degree Seeking (ID = 19)
      //:szEntranceClassification = mProspct.Prospect.EntranceClassification  
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szEntranceClassification;
      if ( szEntranceClassification == null )
         sb_szEntranceClassification = new StringBuilder( 32 );
      else
         sb_szEntranceClassification = new StringBuilder( szEntranceClassification );
             GetVariableFromAttribute( sb_szEntranceClassification, mi_lTempInteger_2, 'S', 6, mProspct, "Prospect", "EntranceClassification", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szEntranceClassification = sb_szEntranceClassification.toString( );}
      //:mProspct.Prospect.CommitteeReviewNeededFlag = ""   // Initialize flag which will be reset later if condition still exists.
      SetAttributeFromString( mProspct, "Prospect", "CommitteeReviewNeededFlag", "" );
      //:IF szEntranceClassification = "FR"
      if ( ZeidonStringCompare( szEntranceClassification, 1, 0, "FR", 1, 0, 6 ) == 0 )
      { 
         //:IF mPerson.Demographics.CitizenshipCode = "FS"  // Nonresident Alien
         if ( CompareAttributeToString( mPerson, "Demographics", "CitizenshipCode", "FS" ) == 0 )
         { 

            //:// International Freshman
            //:AdmissionsTrackID = 20
            AdmissionsTrackID = 20;
            //:ELSE
         } 
         else
         { 
            //:// National Freshman
            //:// Check if SAT or ACT scores are high enough to NOT need placement testing.
            //:CreateViewFromView( mProspct2, mProspct )
            CreateViewFromView( mProspct2, mProspct );

            //:// First try SAT scores.
            //:FOR EACH mProspct2.ExamHistory WHERE mProspct2.ExamHistory.ExamType = "SAT"
            RESULT = SetCursorFirstEntityByString( mProspct2, "ExamHistory", "ExamType", "SAT", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF mProspct2.ExamHistory.ReadingComprehension >= HighSATReading
               if ( CompareAttributeToInteger( mProspct2, "ExamHistory", "ReadingComprehension", HighSATReading ) >= 0 )
               { 
                  //:HighSATReading = mProspct2.ExamHistory.ReadingComprehension 
                  {MutableInt mi_HighSATReading = new MutableInt( HighSATReading );
                                     GetIntegerFromAttribute( mi_HighSATReading, mProspct2, "ExamHistory", "ReadingComprehension" );
                  HighSATReading = mi_HighSATReading.intValue( );}
               } 

               //:END
               //:IF mProspct2.ExamHistory.MathQuantitative >= HighSATMath
               if ( CompareAttributeToInteger( mProspct2, "ExamHistory", "MathQuantitative", HighSATMath ) >= 0 )
               { 
                  //:HighSATMath = mProspct2.ExamHistory.MathQuantitative 
                  {MutableInt mi_HighSATMath = new MutableInt( HighSATMath );
                                     GetIntegerFromAttribute( mi_HighSATMath, mProspct2, "ExamHistory", "MathQuantitative" );
                  HighSATMath = mi_HighSATMath.intValue( );}
               } 

               //:END
               //:IF mProspct2.ExamHistory.Writing >= HighSATWriting
               if ( CompareAttributeToInteger( mProspct2, "ExamHistory", "Writing", HighSATWriting ) >= 0 )
               { 
                  //:HighSATWriting = mProspct2.ExamHistory.Writing
                  {MutableInt mi_HighSATWriting = new MutableInt( HighSATWriting );
                                     GetIntegerFromAttribute( mi_HighSATWriting, mProspct2, "ExamHistory", "Writing" );
                  HighSATWriting = mi_HighSATWriting.intValue( );}
               } 

               RESULT = SetCursorNextEntityByString( mProspct2, "ExamHistory", "ExamType", "SAT", "" );
               //:END
            } 

            //:END
            //:IF HighSATReading >= 480 AND HighSATMath >= 480 AND HighSATWriting >= 480
            if ( HighSATReading >= 480 && HighSATMath >= 480 && HighSATWriting >= 480 )
            { 
               //:// Freshman Track
               //:AdmissionsTrackID = 7
               AdmissionsTrackID = 7;
               //:ELSE
            } 
            else
            { 
               //:// Next try ACT score.
               //:IF mProspct.Prospect.dHighACT >= 18
               if ( CompareAttributeToInteger( mProspct, "Prospect", "dHighACT", 18 ) >= 0 )
               { 

                  //:// Freshman Track
                  //:AdmissionsTrackID = 7
                  AdmissionsTrackID = 7;
                  //:ELSE
               } 
               else
               { 

                  //:// Needs Placement Testing
                  //:AdmissionsTrackID = 16
                  AdmissionsTrackID = 16;
                  //:mProspct.Prospect.CommitteeReviewNeededFlag = "Y"
                  SetAttributeFromString( mProspct, "Prospect", "CommitteeReviewNeededFlag", "Y" );
               } 

               //:END
            } 

            //:END
            //:DropView( mProspct2 )
            DropView( mProspct2 );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF szEntranceClassification = "TR"
         if ( ZeidonStringCompare( szEntranceClassification, 1, 0, "TR", 1, 0, 6 ) == 0 )
         { 
            //:IF mPerson.Demographics.CitizenshipCode = "FS"  // Nonresident Alien
            if ( CompareAttributeToString( mPerson, "Demographics", "CitizenshipCode", "FS" ) == 0 )
            { 
               //:// Transfer International
               //:AdmissionsTrackID = 8
               AdmissionsTrackID = 8;
               //:ELSE
            } 
            else
            { 
               //:// Transfer Regular
               //:AdmissionsTrackID = 15
               AdmissionsTrackID = 15;
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF szEntranceClassification = "RD"
            if ( ZeidonStringCompare( szEntranceClassification, 1, 0, "RD", 1, 0, 6 ) == 0 )
            { 
               //:// Re-Admit
               //:AdmissionsTrackID = 14
               AdmissionsTrackID = 14;
               //:ELSE
            } 
            else
            { 
               //:IF szEntranceClassification = "OS"
               if ( ZeidonStringCompare( szEntranceClassification, 1, 0, "OS", 1, 0, 6 ) == 0 )
               { 
                  //:// Older Student
                  //:AdmissionsTrackID = 0   // We're going to do nothing in this case.
                  AdmissionsTrackID = 0;
                  //:ELSE
               } 
               else
               { 
                  //:// Default is Non-Degree Seeking
                  //:AdmissionsTrackID = 19
                  AdmissionsTrackID = 19;
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      //:END
      //:IF AdmissionsTrackID > 0
      if ( AdmissionsTrackID > 0 )
      { 
         //:IF mProspct.AdmissionTrack EXISTS
         lTempInteger_3 = CheckExistenceOfEntity( mProspct, "AdmissionTrack" );
         if ( lTempInteger_3 == 0 )
         { 
            //:IF mProspct.AdmissionTrack.ID != AdmissionsTrackID
            if ( CompareAttributeToInteger( mProspct, "AdmissionTrack", "ID", AdmissionsTrackID ) != 0 )
            { 
               //:szSetTrackFlag = "Y"
                {StringBuilder sb_szSetTrackFlag;
               if ( szSetTrackFlag == null )
                  sb_szSetTrackFlag = new StringBuilder( 32 );
               else
                  sb_szSetTrackFlag = new StringBuilder( szSetTrackFlag );
                              ZeidonStringCopy( sb_szSetTrackFlag, 1, 0, "Y", 1, 0, 2 );
               szSetTrackFlag = sb_szSetTrackFlag.toString( );}
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:szSetTrackFlag = "Y"
             {StringBuilder sb_szSetTrackFlag;
            if ( szSetTrackFlag == null )
               sb_szSetTrackFlag = new StringBuilder( 32 );
            else
               sb_szSetTrackFlag = new StringBuilder( szSetTrackFlag );
                        ZeidonStringCopy( sb_szSetTrackFlag, 1, 0, "Y", 1, 0, 2 );
            szSetTrackFlag = sb_szSetTrackFlag.toString( );}
         } 

         //:END
      } 

      //:END
      //:IF szSetTrackFlag = "Y"
      if ( ZeidonStringCompare( szSetTrackFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:SET CURSOR FIRST mAdmDiv.AdmissionTrack WHERE mAdmDiv.AdmissionTrack.ID = AdmissionsTrackID
         RESULT = SetCursorFirstEntityByInteger( mAdmDiv, "AdmissionTrack", "ID", AdmissionsTrackID, "" );
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:IF mProspct.AdmissionTrack EXISTS
            lTempInteger_4 = CheckExistenceOfEntity( mProspct, "AdmissionTrack" );
            if ( lTempInteger_4 == 0 )
            { 
               //:EXCLUDE mProspct.AdmissionTrack
               RESULT = ExcludeEntity( mProspct, "AdmissionTrack", zREPOS_AFTER );
            } 

            //:END
            //:INCLUDE mProspct.AdmissionTrack FROM mAdmDiv.AdmissionTrack 
            RESULT = IncludeSubobjectFromSubobject( mProspct, "AdmissionTrack", mAdmDiv, "AdmissionTrack", zPOS_AFTER );
            //:ELSE
         } 
         else
         { 
            //:IssueError( mProspct,0,0, "Error: Admission Track missing on auto assignment." )
            IssueError( mProspct, 0, 0, "Error: Admission Track missing on auto assignment." );
         } 

         //:END
      } 

      //:END

      //:// If SAT or ACT scores exist, set that SAT/ACT requirement is meant.
      //:CreateViewFromView( mProspct2, mProspct )
      CreateViewFromView( mProspct2, mProspct );
      //:SET CURSOR FIRST mProspct2.AdmissionRequirement WITHIN mProspct2.Prospect 
      //:           WHERE mProspct2.AdmissionRequirement.ID = 29
      RESULT = SetCursorFirstEntityByInteger( mProspct2, "AdmissionRequirement", "ID", 29, "Prospect" );
      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:SET CURSOR FIRST mProspct2.ExamHistory WHERE mProspct2.ExamHistory.ExamType = "SAT"
         //:                                          OR mProspct2.ExamHistory.ExamType = "ACT"
         RESULT = SetCursorFirstEntity( mProspct2, "ExamHistory", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mProspct2, "ExamHistory", "ExamType", "SAT" ) != 0 && CompareAttributeToString( mProspct2, "ExamHistory", "ExamType", "ACT" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( mProspct2, "ExamHistory", "" );
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:SET CURSOR FIRST mAdmDiv.AdmissionRequirement WHERE mAdmDiv.AdmissionRequirement.ID = 29
            RESULT = SetCursorFirstEntityByInteger( mAdmDiv, "AdmissionRequirement", "ID", 29, "" );
            //:CREATE ENTITY mProspct.AdmissionRequirementMet
            RESULT = CreateEntity( mProspct, "AdmissionRequirementMet", zPOS_AFTER );
            //:mProspct.AdmissionRequirementMet.Type = "M"
            SetAttributeFromString( mProspct, "AdmissionRequirementMet", "Type", "M" );
            //:SetAttributeFromCurrentDateTime( mProspct, "AdmissionRequirementMet", "DateReceived" )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mProspct );
             m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mProspct, "AdmissionRequirementMet", "DateReceived" );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:INCLUDE mProspct.AdmissionRequirement FROM mAdmDiv.AdmissionRequirement  
            RESULT = IncludeSubobjectFromSubobject( mProspct, "AdmissionRequirement", mAdmDiv, "AdmissionRequirement", zPOS_AFTER );
         } 

         //:END
      } 

      //:END
      //:DropView( mProspct2 )
      DropView( mProspct2 );

      //:// Set Enrollment Category for Main Campus based on Entrance Classification.
      //:// Freshman         Degree Seeking First Time
      //:// Transfer         Degree Seeking Transfer
      //:// Re-admitted      Degree Seeking Other  
      //:// Special Student  Non-Degree Seeking
      //:szEntranceClassification = mProspct.Prospect.EntranceClassification  
      {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
      StringBuilder sb_szEntranceClassification;
      if ( szEntranceClassification == null )
         sb_szEntranceClassification = new StringBuilder( 32 );
      else
         sb_szEntranceClassification = new StringBuilder( szEntranceClassification );
             GetVariableFromAttribute( sb_szEntranceClassification, mi_lTempInteger_5, 'S', 6, mProspct, "Prospect", "EntranceClassification", "", 0 );
      lTempInteger_5 = mi_lTempInteger_5.intValue( );
      szEntranceClassification = sb_szEntranceClassification.toString( );}
      //:IF szEntranceClassification = "FR"
      if ( ZeidonStringCompare( szEntranceClassification, 1, 0, "FR", 1, 0, 6 ) == 0 )
      { 
         //:// Freshman
         //:mProspct.Prospect.EnrollmentCategory = "DegreeSeekingFirst"
         SetAttributeFromString( mProspct, "Prospect", "EnrollmentCategory", "DegreeSeekingFirst" );
         //:ELSE
      } 
      else
      { 
         //:IF szEntranceClassification = "TR"
         if ( ZeidonStringCompare( szEntranceClassification, 1, 0, "TR", 1, 0, 6 ) == 0 )
         { 
            //://Transfer
            //:mProspct.Prospect.EnrollmentCategory = "DegreeSeekTransfer"
            SetAttributeFromString( mProspct, "Prospect", "EnrollmentCategory", "DegreeSeekTransfer" );
            //:ELSE
         } 
         else
         { 
            //:IF szEntranceClassification = "RD"
            if ( ZeidonStringCompare( szEntranceClassification, 1, 0, "RD", 1, 0, 6 ) == 0 )
            { 
               //:// Re-Admit
               //:mProspct.Prospect.EnrollmentCategory = "DegreeSeekingOther"
               SetAttributeFromString( mProspct, "Prospect", "EnrollmentCategory", "DegreeSeekingOther" );
               //:ELSE
            } 
            else
            { 
               //:IF szEntranceClassification = "OS"
               if ( ZeidonStringCompare( szEntranceClassification, 1, 0, "OS", 1, 0, 6 ) == 0 )
               { 
                  //:// Older Student (legacy value)
                  //:mProspct.Prospect.EnrollmentCategory = "DegreeSeekingOther"
                  SetAttributeFromString( mProspct, "Prospect", "EnrollmentCategory", "DegreeSeekingOther" );
                  //:ELSE
               } 
               else
               { 
                  //:// Default is Non-Degree Seeking
                  //:mProspct.Prospect.EnrollmentCategory = "NonDegreeSeeking"
                  SetAttributeFromString( mProspct, "Prospect", "EnrollmentCategory", "NonDegreeSeeking" );
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:   
   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ValidateProspectData( VIEW mProspct BASED ON LOD mProspct,
//:                      VIEW mPerson  BASED ON LOD  mPerson )

//:   VIEW mPerson2  BASED ON LOD  mPerson
public int 
omProspct_ValidateProspectData( View     mProspct,
                                View     mPerson )
{
   zVIEW    mPerson2 = new zVIEW( );
   //:VIEW mProspct2 BASED ON LOD  mProspct
   zVIEW    mProspct2 = new zVIEW( );
   //:STRING ( 1000 ) Msg
   String   Msg = null;
   //:STRING ( 1 )    ValidationFlag
   String   ValidationFlag = null;
   //:STRING ( 2 )    szWithdrawnType
   String   szWithdrawnType = null;
   //:SHORT           nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_4 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   String   szTempString_3 = null;
   int      lTempInteger_5 = 0;


   //:// Common routine for Application Validation.

   //:// If a Prospect has applied, the following attributes must be set:
   //:// 1. SSN
   //:// 2. Ethnicity
   //:// 3. Gender
   //:// 4. Date of Birth
   //:// 5. Enrollment Category
   //:// 6. Expected Entry Year and Term
   //:// 7. Data Source
   //:// 8. College
   //:// 9. Program (for Lead students)
   //://
   //:IF mProspct.Prospect.ApplicationDate != ""
   if ( CompareAttributeToString( mProspct, "Prospect", "ApplicationDate", "" ) != 0 )
   { 
      //:ValidationFlag = ""
       {StringBuilder sb_ValidationFlag;
      if ( ValidationFlag == null )
         sb_ValidationFlag = new StringBuilder( 32 );
      else
         sb_ValidationFlag = new StringBuilder( ValidationFlag );
            ZeidonStringCopy( sb_ValidationFlag, 1, 0, "", 1, 0, 2 );
      ValidationFlag = sb_ValidationFlag.toString( );}
      //:Msg = "The following prospect fields are required for 'Applied Prospects' but have not been entered:" + NEW_LINE
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringCopy( sb_Msg, 1, 0, "The following prospect fields are required for 'Applied Prospects' but have not been entered:", 1, 0, 1001 );
      Msg = sb_Msg.toString( );}
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 1001 );
      Msg = sb_Msg.toString( );}
      //://IF mPerson.Person.SSN = ""
      //://   ValidationFlag = "Y"
      //://   Msg = Msg + "   SSN" + NEW_LINE
      //://END
      //://IF mPerson.Demographics.Ethnicity = "" AND mPerson.Demographics.NRAlien = ""
      //://   ValidationFlag = "Y"
      //://   Msg = Msg + "   Ethnicity" + NEW_LINE
      //://END
      //:IF mPerson.Person.Gender = ""
      if ( CompareAttributeToString( mPerson, "Person", "Gender", "" ) == 0 )
      { 
         //:ValidationFlag = "Y"
          {StringBuilder sb_ValidationFlag;
         if ( ValidationFlag == null )
            sb_ValidationFlag = new StringBuilder( 32 );
         else
            sb_ValidationFlag = new StringBuilder( ValidationFlag );
                  ZeidonStringCopy( sb_ValidationFlag, 1, 0, "Y", 1, 0, 2 );
         ValidationFlag = sb_ValidationFlag.toString( );}
         //:Msg = Msg + "   Gender" + NEW_LINE
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, "   Gender", 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
      } 

      //:END
      //:IF mPerson.Person.DateOfBirth = ""
      if ( CompareAttributeToString( mPerson, "Person", "DateOfBirth", "" ) == 0 )
      { 
         //:ValidationFlag = "Y"
          {StringBuilder sb_ValidationFlag;
         if ( ValidationFlag == null )
            sb_ValidationFlag = new StringBuilder( 32 );
         else
            sb_ValidationFlag = new StringBuilder( ValidationFlag );
                  ZeidonStringCopy( sb_ValidationFlag, 1, 0, "Y", 1, 0, 2 );
         ValidationFlag = sb_ValidationFlag.toString( );}
         //:Msg = Msg + "   Date of Birth" + NEW_LINE
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, "   Date of Birth", 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
      } 

      //:END
      //:IF mProspct.Prospect.EnrollmentCategory = ""
      if ( CompareAttributeToString( mProspct, "Prospect", "EnrollmentCategory", "" ) == 0 )
      { 
         //:ValidationFlag = "Y"
          {StringBuilder sb_ValidationFlag;
         if ( ValidationFlag == null )
            sb_ValidationFlag = new StringBuilder( 32 );
         else
            sb_ValidationFlag = new StringBuilder( ValidationFlag );
                  ZeidonStringCopy( sb_ValidationFlag, 1, 0, "Y", 1, 0, 2 );
         ValidationFlag = sb_ValidationFlag.toString( );}
         //:Msg = Msg + "   Enrollment Category" + NEW_LINE
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, "   Enrollment Category", 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
      } 

      //:END
      //:IF mProspct.Prospect.ExpectedEntryYear = ""
      if ( CompareAttributeToString( mProspct, "Prospect", "ExpectedEntryYear", "" ) == 0 )
      { 
         //:ValidationFlag = "Y"
          {StringBuilder sb_ValidationFlag;
         if ( ValidationFlag == null )
            sb_ValidationFlag = new StringBuilder( 32 );
         else
            sb_ValidationFlag = new StringBuilder( ValidationFlag );
                  ZeidonStringCopy( sb_ValidationFlag, 1, 0, "Y", 1, 0, 2 );
         ValidationFlag = sb_ValidationFlag.toString( );}
         //:Msg = Msg + "   Expected Entry Year" + NEW_LINE
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, "   Expected Entry Year", 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
      } 

      //:END
      //:IF mProspct.Prospect.ExpectedEntryTerm = ""
      if ( CompareAttributeToString( mProspct, "Prospect", "ExpectedEntryTerm", "" ) == 0 )
      { 
         //:ValidationFlag = "Y"
          {StringBuilder sb_ValidationFlag;
         if ( ValidationFlag == null )
            sb_ValidationFlag = new StringBuilder( 32 );
         else
            sb_ValidationFlag = new StringBuilder( ValidationFlag );
                  ZeidonStringCopy( sb_ValidationFlag, 1, 0, "Y", 1, 0, 2 );
         ValidationFlag = sb_ValidationFlag.toString( );}
         //:Msg = Msg + "   Expected Entry Term" + NEW_LINE
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, "   Expected Entry Term", 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
      } 

      //:END
      //:IF mProspct.Prospect.ProspectDataSource = ""
      if ( CompareAttributeToString( mProspct, "Prospect", "ProspectDataSource", "" ) == 0 )
      { 
         //:ValidationFlag = "Y"
          {StringBuilder sb_ValidationFlag;
         if ( ValidationFlag == null )
            sb_ValidationFlag = new StringBuilder( 32 );
         else
            sb_ValidationFlag = new StringBuilder( ValidationFlag );
                  ZeidonStringCopy( sb_ValidationFlag, 1, 0, "Y", 1, 0, 2 );
         ValidationFlag = sb_ValidationFlag.toString( );}
         //:Msg = Msg + "   Data Source" + NEW_LINE
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, "   Data Source", 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
      } 

      //:END
      //:IF mProspct.College DOES NOT EXIST
      lTempInteger_0 = CheckExistenceOfEntity( mProspct, "College" );
      if ( lTempInteger_0 != 0 )
      { 
         //:ValidationFlag = "Y"
          {StringBuilder sb_ValidationFlag;
         if ( ValidationFlag == null )
            sb_ValidationFlag = new StringBuilder( 32 );
         else
            sb_ValidationFlag = new StringBuilder( ValidationFlag );
                  ZeidonStringCopy( sb_ValidationFlag, 1, 0, "Y", 1, 0, 2 );
         ValidationFlag = sb_ValidationFlag.toString( );}
         //:Msg = Msg + "   College" + NEW_LINE
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, "   College", 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
      } 

      //:END
      //:/*IF mProspct.PotentialCohortProgram DOES NOT EXIST AND mProspct.AdministrativeDivision.Name = "Lead"
      //:   ValidationFlag = "Y"
      //:   Msg = Msg + "   Program" + NEW_LINE
      //:END*/
      //:IF ValidationFlag = "Y"
      if ( ZeidonStringCompare( ValidationFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:MessageSend( mProspct, "", "Save Prospect Entry", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mProspct, "", "Save Prospect Entry", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
   } 

   //:END

   //:// If the Withdrawal Date is set, make sure that the Withdrawal Reason is also set.
   //:// (The exception is if the Prospect is an Inquiry or a Lead, in which case the Reason isn't required.)
   //:// Also, set the Prospect Type to Withdrawn, if the date is set.
   //:IF mProspct.AdministrativeDivision.Name = "Main Campus"
   if ( CompareAttributeToString( mProspct, "AdministrativeDivision", "Name", "Main Campus" ) == 0 )
   { 
      //:szWithdrawnType = "10"
       {StringBuilder sb_szWithdrawnType;
      if ( szWithdrawnType == null )
         sb_szWithdrawnType = new StringBuilder( 32 );
      else
         sb_szWithdrawnType = new StringBuilder( szWithdrawnType );
            ZeidonStringCopy( sb_szWithdrawnType, 1, 0, "10", 1, 0, 3 );
      szWithdrawnType = sb_szWithdrawnType.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szWithdrawnType = "8"
       {StringBuilder sb_szWithdrawnType;
      if ( szWithdrawnType == null )
         sb_szWithdrawnType = new StringBuilder( 32 );
      else
         sb_szWithdrawnType = new StringBuilder( szWithdrawnType );
            ZeidonStringCopy( sb_szWithdrawnType, 1, 0, "8", 1, 0, 3 );
      szWithdrawnType = sb_szWithdrawnType.toString( );}
   } 

   //:END
   //:IF mProspct.Prospect.WithdrawDate != ""
   if ( CompareAttributeToString( mProspct, "Prospect", "WithdrawDate", "" ) != 0 )
   { 
      //:IF mProspct.Prospect.wOriginalProspectType != "1" AND mProspct.Prospect.wOriginalProspectType != "2"
      if ( CompareAttributeToString( mProspct, "Prospect", "wOriginalProspectType", "1" ) != 0 && CompareAttributeToString( mProspct, "Prospect", "wOriginalProspectType", "2" ) != 0 )
      { 
         //:IF mProspct.Prospect.WithdrawReason = ""
         if ( CompareAttributeToString( mProspct, "Prospect", "WithdrawReason", "" ) == 0 )
         { 
            //:MessageSend( mProspct, "", "Save Prospect Entry",
            //:             "Because the 'Date of Withdrawal' is set, the 'Withdrawal Reason' must also be set.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mProspct, "", "Save Prospect Entry", "Because the 'Date of Withdrawal' is set, the 'Withdrawal Reason' must also be set.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
      } 

      //:END
      //:IF mProspct.Prospect.Type != szWithdrawnType
      if ( CompareAttributeToString( mProspct, "Prospect", "Type", szWithdrawnType ) != 0 )
      { 
         //:mProspct.Prospect.Type = szWithdrawnType
         SetAttributeFromString( mProspct, "Prospect", "Type", szWithdrawnType );
      } 

      //:END
   } 

   //:END

   //:// Make sure the Withdrawal Date is set if the Prospect Type is withdrawn
   //:IF mProspct.Prospect.Type = szWithdrawnType AND mProspct.Prospect.WithdrawDate = ""
   if ( CompareAttributeToString( mProspct, "Prospect", "Type", szWithdrawnType ) == 0 && CompareAttributeToString( mProspct, "Prospect", "WithdrawDate", "" ) == 0 )
   { 
      //:MessageSend( mProspct, "", "Save Prospect Entry",
      //:             "Because the Prospect Type is 'Withdrawn', the 'Date of Withdrawal' must be set.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mProspct, "", "Save Prospect Entry", "Because the Prospect Type is 'Withdrawn', the 'Date of Withdrawal' must be set.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// For Main Campus, if a Prospect is Lead, Applied, Addmitted or Deposited, the ProspectRanking attribute must be set.
   //:/*IF mProspct.AdministrativeDivision.Name = "Main Campus" AND mProspct.Prospect.Ranking = ""
   //:   ProspectType = mProspct.Prospect.Type 
   //:   IF ProspectType = "2" OR
   //:      ProspectType = "4" OR
   //:      ProspectType = "5" OR
   //:      ProspectType = "6"
   //:   
   //:      MessageSend( mProspct, "", "Save Prospect Entry",
   //:                   "The Prospect Ranking must be set when the Type is 'Lead', 'Applied', 'Admitted' or 'Desposited'.",
   //:                   zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //:      RETURN -1
   //:   END
   //:END*/

   //:// If a Student has been Admitted or Deposited, then SSN must be specified, unless they are an international student.
   //:IF ( mProspct.Prospect.Type = "5" OR mProspct.Prospect.Type = "6" ) AND 
   //:   mPerson.Person.SSN = "" AND mPerson.Demographics.CitizenshipCode != "FS"
   if ( ( CompareAttributeToString( mProspct, "Prospect", "Type", "5" ) == 0 || CompareAttributeToString( mProspct, "Prospect", "Type", "6" ) == 0 ) && CompareAttributeToString( mPerson, "Person", "SSN", "" ) == 0 &&
        CompareAttributeToString( mPerson, "Demographics", "CitizenshipCode", "FS" ) != 0 )
   { 

      //:Msg = "If a Prospect has been admitted, then an SSN value is required."
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringCopy( sb_Msg, 1, 0, "If a Prospect has been admitted, then an SSN value is required.", 1, 0, 1001 );
      Msg = sb_Msg.toString( );}
      //:MessageSend( mProspct, "", "Save Prospect Entry", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mProspct, "", "Save Prospect Entry", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Make sure we are not adding a Prospect entry for a Person that is already a Prospect in the same Admin Div.
   //:IF mProspct.Prospect.ID = "" AND mProspct.Person.ID != ""
   if ( CompareAttributeToString( mProspct, "Prospect", "ID", "" ) == 0 && CompareAttributeToString( mProspct, "Person", "ID", "" ) != 0 )
   { 
      //:ACTIVATE mProspct2 Multiple WHERE mProspct2.Person.ID = mProspct.Person.ID
      //:                              AND mProspct2.Prospect.ExpectedEntryYear = mProspct.Prospect.ExpectedEntryYear 
      //:                              AND mProspct2.Prospect.ExpectedEntryTerm = mProspct.Prospect.ExpectedEntryTerm 
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, mProspct, "Person", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, mProspct, "Prospect", "ExpectedEntryYear" );
      szTempString_0 = sb_szTempString_0.toString( );}
      {StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
             GetStringFromAttribute( sb_szTempString_1, mProspct, "Prospect", "ExpectedEntryTerm" );
      szTempString_1 = sb_szTempString_1.toString( );}
      omProspct_fnLocalBuildQual_6( mProspct, vTempViewVar_0, lTempInteger_1, szTempString_0, szTempString_1 );
      RESULT = ActivateObjectInstance( mProspct2, "mProspct", mProspct, vTempViewVar_0, zMULTIPLE );
      DropView( vTempViewVar_0 );
      //:SET CURSOR FIRST mProspct2.Prospect WHERE mProspct2.AdministrativeDivision.ID = mProspct.AdministrativeDivision.ID 
      RESULT = SetCursorFirstEntity( mProspct2, "Prospect", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mProspct2, "AdministrativeDivision", "ID", mProspct, "AdministrativeDivision", "ID" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( mProspct2, "Prospect", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:DropObjectInstance( mProspct2 )
         DropObjectInstance( mProspct2 );
         //:MessageSend( mProspct, "", "Create Prospect Entry",
         //:             "The person already exists as a Prospect entry in the same Administrative Division for the same Entry Year.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mProspct, "", "Create Prospect Entry", "The person already exists as a Prospect entry in the same Administrative Division for the same Entry Year.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END

      //:// Prompt the operator if the Person is a Prospect in another Administrative Division.
      //:IF mProspct2.Prospect EXISTS
      lTempInteger_2 = CheckExistenceOfEntity( mProspct2, "Prospect" );
      if ( lTempInteger_2 == 0 )
      { 
         //:Msg = "The Person is already a prospect for another Administrative Division." + NEW_LINE +
         //:      "Do you want to make the person a prospect for the current Administrative Division, as well?"
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringCopy( sb_Msg, 1, 0, "The Person is already a prospect for another Administrative Division.", 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, "Do you want to make the person a prospect for the current Administrative Division, as well?", 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
         //:nRC = MessagePrompt( mProspct, "", "Create Prospect Entry",
         //:                     Msg, 0, zBUTTONS_YESNO, zRESPONSE_YES, 0 )
         nRC = MessagePrompt( mProspct, "", "Create Prospect Entry", Msg, 0, zBUTTONS_YESNO, zRESPONSE_YES, 0 );
         //:IF nRC = zRESPONSE_NO
         if ( nRC == zRESPONSE_NO )
         { 
            //:DropObjectInstance( mProspct2 )
            DropObjectInstance( mProspct2 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
      } 

      //:END
      //:DropObjectInstance( mProspct2 )
      DropObjectInstance( mProspct2 );
   } 

   //:END  

   //:// Don't allow an Prospect to be saved without a Person assigned.
   //:IF mProspct.Person DOES NOT EXIST
   lTempInteger_3 = CheckExistenceOfEntity( mProspct, "Person" );
   if ( lTempInteger_3 != 0 )
   { 
      //:MessageSend( mProspct, "", "Save Prospect Entry",
      //:             "A Prospect entry cannot be saved without assigning a person.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mProspct, "", "Save Prospect Entry", "A Prospect entry cannot be saved without assigning a person.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// For Main Campus, make sure that Entry Year, Entry Term and Prospect Data Source are entered.
   //:IF mProspct.AdministrativeDivision.Name = "Main Campus" AND mProspct.Prospect.Type != "10"  // 10 is Withdrawn
   if ( CompareAttributeToString( mProspct, "AdministrativeDivision", "Name", "Main Campus" ) == 0 && CompareAttributeToString( mProspct, "Prospect", "Type", "10" ) != 0 )
   { 
      //:IF mProspct.Prospect.ExpectedEntryTerm = "" OR mProspct.Prospect.ExpectedEntryYear = ""
      if ( CompareAttributeToString( mProspct, "Prospect", "ExpectedEntryTerm", "" ) == 0 || CompareAttributeToString( mProspct, "Prospect", "ExpectedEntryYear", "" ) == 0 )
      { 
         //:MessageSend( mProspct, "", "Save Entry",
         //:             "Both Entry Term and Entry Year must be specified.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mProspct, "", "Save Entry", "Both Entry Term and Entry Year must be specified.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( mProspct, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( mProspct, zWAB_StayOnWindow, "", "" );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
      //:IF mProspct.Prospect.ProspectDataSource = ""
      if ( CompareAttributeToString( mProspct, "Prospect", "ProspectDataSource", "" ) == 0 )
      { 
         //:MessageSend( mProspct, "", "Save Entry",
         //:             "Prospect Data Source must be specified.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mProspct, "", "Save Entry", "Prospect Data Source must be specified.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( mProspct, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( mProspct, zWAB_StayOnWindow, "", "" );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
   } 

   //:END

   //:// If the SSN has been specified, warn the operator if it is not unique.
   //:IF mPerson.Person.SSN != ""
   if ( CompareAttributeToString( mPerson, "Person", "SSN", "" ) != 0 )
   { 
      //:ACTIVATE mPerson2 RootOnlyMultiple WHERE mPerson2.Person.SSN = mPerson.Person.SSN 
      //:                                     AND mPerson2.Person.ID != mPerson.Person.ID 
      {StringBuilder sb_szTempString_2;
      if ( szTempString_2 == null )
         sb_szTempString_2 = new StringBuilder( 32 );
      else
         sb_szTempString_2 = new StringBuilder( szTempString_2 );
             GetStringFromAttribute( sb_szTempString_2, mPerson, "Person", "SSN" );
      szTempString_2 = sb_szTempString_2.toString( );}
      {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
             GetIntegerFromAttribute( mi_lTempInteger_4, mPerson, "Person", "ID" );
      lTempInteger_4 = mi_lTempInteger_4.intValue( );}
      omProspct_fnLocalBuildQual_7( mProspct, vTempViewVar_1, szTempString_2, lTempInteger_4 );
      RESULT = ActivateObjectInstance( mPerson2, "mPerson", mProspct, vTempViewVar_1, zACTIVATE_ROOTONLY_MULTIPLE );
      DropView( vTempViewVar_1 );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:Msg = "The SSN for this Prospect is duplicated for the following people:" + NEW_LINE
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringCopy( sb_Msg, 1, 0, "The SSN for this Prospect is duplicated for the following people:", 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
         //:FOR EACH mPerson2.Person 
         RESULT = SetCursorFirstEntity( mPerson2, "Person", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:Msg = Msg + "   " + mPerson2.Person.dFullNameLFM + NEW_LINE
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringConcat( sb_Msg, 1, 0, "   ", 1, 0, 1001 );
            Msg = sb_Msg.toString( );}
            {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
            StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                         GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_5, 'S', 255, mPerson2, "Person", "dFullNameLFM", "", 0 );
            lTempInteger_5 = mi_lTempInteger_5.intValue( );
            szTempString_3 = sb_szTempString_3.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringConcat( sb_Msg, 1, 0, szTempString_3, 1, 0, 1001 );
            Msg = sb_Msg.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 1001 );
            Msg = sb_Msg.toString( );}
            RESULT = SetCursorNextEntity( mPerson2, "Person", "" );
         } 

         //:END
         //:Msg = Msg + "The Prospect will be saved but you should resolve issues around the duplicates."
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, "The Prospect will be saved but you should resolve issues around the duplicates.", 1, 0, 1001 );
         Msg = sb_Msg.toString( );}
         //:MessageSend( mProspct, "", "Save Prospect Entry", Msg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
         MessageSend( mProspct, "", "Save Prospect Entry", Msg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
      } 

      //:END
      //:DropObjectInstance( mPerson2 )
      DropObjectInstance( mPerson2 );
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dFAISIR_SchoolName( VIEW mProspct BASED ON LOD mProspct,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   VIEW lSchool BASED ON LOD lSchool
public int 
omProspct_dFAISIR_SchoolName( View     mProspct,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   zVIEW    lSchool = new zVIEW( );
   //:VIEW sHost   BASED ON LOD sHost
   zVIEW    sHost = new zVIEW( );
   //:STRING ( 100 ) szSchoolName
   String   szSchoolName = null;
   //:STRING ( 20 )  szSchoolCode
   String   szSchoolCode = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Get the School Name via the FederalSchoolCode, if it exists.
         //:szSchoolCode = mProspct.FAISIRSchool.FederalSchoolCode
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szSchoolCode;
         if ( szSchoolCode == null )
            sb_szSchoolCode = new StringBuilder( 32 );
         else
            sb_szSchoolCode = new StringBuilder( szSchoolCode );
                   GetVariableFromAttribute( sb_szSchoolCode, mi_lTempInteger_0, 'S', 21, mProspct, "FAISIRSchool", "FederalSchoolCode", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szSchoolCode = sb_szSchoolCode.toString( );}
         //:IF szSchoolCode != "" 
         if ( ZeidonStringCompare( szSchoolCode, 1, 0, "", 1, 0, 21 ) != 0 )
         { 
            //:GetViewByName( sHost, "sHost", mProspct, zLEVEL_APPLICATION )
            GetViewByName( sHost, "sHost", mProspct, zLEVEL_APPLICATION );
            //:IF sHost.Host.ClearinghouseFICE_Code = szSchoolCode
            if ( CompareAttributeToString( sHost, "Host", "ClearinghouseFICE_Code", szSchoolCode ) == 0 )
            { 
               //:szSchoolName = sHost.Host.HostName 
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_szSchoolName;
               if ( szSchoolName == null )
                  sb_szSchoolName = new StringBuilder( 32 );
               else
                  sb_szSchoolName = new StringBuilder( szSchoolName );
                               GetVariableFromAttribute( sb_szSchoolName, mi_lTempInteger_1, 'S', 101, sHost, "Host", "HostName", "", 0 );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );
               szSchoolName = sb_szSchoolName.toString( );}
               //:ELSE
            } 
            else
            { 
               //:ACTIVATE lSchool WHERE lSchool.School.Code = mProspct.FAISIRSchool.FederalSchoolCode 
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, mProspct, "FAISIRSchool", "FederalSchoolCode" );
               szTempString_0 = sb_szTempString_0.toString( );}
               omProspct_fnLocalBuildQual_8( mProspct, vTempViewVar_0, szTempString_0 );
               RESULT = ActivateObjectInstance( lSchool, "lSchool", mProspct, vTempViewVar_0, zSINGLE );
               DropView( vTempViewVar_0 );
               //:NAME VIEW lSchool "lSchoolName"
               SetNameForView( lSchool, "lSchoolName", null, zLEVEL_TASK );
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:szSchoolName = lSchool.School.Name 
                  {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                  StringBuilder sb_szSchoolName;
                  if ( szSchoolName == null )
                     sb_szSchoolName = new StringBuilder( 32 );
                  else
                     sb_szSchoolName = new StringBuilder( szSchoolName );
                                     GetVariableFromAttribute( sb_szSchoolName, mi_lTempInteger_2, 'S', 101, lSchool, "School", "Name", "", 0 );
                  lTempInteger_2 = mi_lTempInteger_2.intValue( );
                  szSchoolName = sb_szSchoolName.toString( );}
               } 

               //:END
               //:DropObjectInstance( lSchool )
               DropObjectInstance( lSchool );
            } 

            //:END

            //:StoreStringInRecord ( mProspct,
            //:                   InternalEntityStructure, InternalAttribStructure, szSchoolName )
            StoreStringInRecord( mProspct, InternalEntityStructure, InternalAttribStructure, szSchoolName );
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
