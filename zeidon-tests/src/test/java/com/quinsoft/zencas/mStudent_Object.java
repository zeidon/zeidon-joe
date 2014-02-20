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

/**
 * @author QuinSoft
 *
 */

public class mStudent_Object extends VmlObjectOperations
{
   public mStudent_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
public int 
omStudent_dFullName( View     mStudent,
                     String InternalEntityStructure,
                     String InternalAttribStructure,
                     Integer   GetOrSetFlag )
{

   //:dFullName( VIEW mStudent BASED ON LOD mStudent,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_FirstMiddleLast( mStudent, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
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
omStudent_fnLocalBuildQual_1( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "DegreeTrack" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "DegreeTrack" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omStudent_fnLocalBuildQual_2( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "DegreeTrack" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "DegreeTrack" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omStudent_fnLocalBuildQual_0( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_21 )
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
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_21 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DERIVED ATTRIBUTE OPERATION
//:dHighACT_Score( VIEW mStudent BASED ON LOD mStudent,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   DECIMAL dTotalComposite
public int 
omStudent_dHighACT_Score( View     mStudent,
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

         //:ComputeHighExamScoreACT( dTotalComposite, mStudent )
         //:StoreValueInRecord ( mStudent,
         //:                     InternalEntityStructure, InternalAttribStructure, dTotalComposite, 0 )
         StoreValueInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, dTotalComposite, 0 );
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
omStudent_dBuckleyFullName( View     mStudent,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{

   //:dBuckleyFullName( VIEW mStudent BASED ON LOD mStudent,
   //:               STRING ( 32 ) InternalEntityStructure,
   //:               STRING ( 32 ) InternalAttribStructure,
   //:               SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_FirstMiddleLast( mStudent, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
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
public int 
omStudent_dEducSpecFullName( View     mStudent,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{

   //:dEducSpecFullName( VIEW mStudent BASED ON LOD mStudent,
   //:                STRING ( 32 ) InternalEntityStructure,
   //:                STRING ( 32 ) InternalAttribStructure,
   //:                SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_FirstMiddleLast( mStudent, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
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


//:OBJECT CONSTRAINT OPERATION
//:ObjectConstraints( VIEW mStudent BASED ON LOD mStudent,
//:                   SHORT Event,
//:                   SHORT State )

//:   VIEW wXferO    REGISTERED AS wXferO
public int 
omStudent_ObjectConstraints( View     mStudent,
                             Integer   Event,
                             Integer   State )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lTermLST  BASED ON LOD  lTermLST
   zVIEW    lTermLST = new zVIEW( );
   //:VIEW lTermLST2 BASED ON LOD  lTermLST
   zVIEW    lTermLST2 = new zVIEW( );
   //:VIEW mUser     BASED ON LOD  mUser
   zVIEW    mUser = new zVIEW( );
   //:VIEW mStudentT BASED ON LOD  mStudent
   zVIEW    mStudentT = new zVIEW( );
   //:VIEW sAppMgr   BASED ON LOD  sAppMgr
   zVIEW    sAppMgr = new zVIEW( );
   //:VIEW mStuChgH  BASED ON LOD  mStuChgH
   zVIEW    mStuChgH = new zVIEW( );
   //:VIEW mStuChgH2 BASED ON LOD  mStuChgH
   zVIEW    mStuChgH2 = new zVIEW( );
   //:VIEW mStuChgL  BASED ON LOD  mStuChgL
   zVIEW    mStuChgL = new zVIEW( );
   //:STRING ( 20 ) szCollegeTerm
   String   szCollegeTerm = null;
   //:STRING ( 50 ) szAnticipatedGradPeriod
   String   szAnticipatedGradPeriod = null;
   //:STRING ( 50 ) szOriginalValue
   String   szOriginalValue = null;
   //:STRING ( 50 ) szNewValue
   String   szNewValue = null;
   //:STRING ( 1 )  szChangeFlag
   String   szChangeFlag = null;
   //:STRING ( 8 )  szCurrentDate
   String   szCurrentDate = null;
   //:STRING ( 1 )  szCurrentAcademicLevel
   String   szCurrentAcademicLevel = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_1 = null;
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
   int      lTempInteger_21 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_22 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mStudent, zLEVEL_TASK );

   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

//          if ( lTempInteger_16 == 0 )
//              return 0;
//          
//         View v = mStudent.activateOiFromFile( "mStudent", "testdata/ZENCAs/mstudent_ac.por" );
//         boolean c = v.equalsOi( mStudent );
         
         //:// Order the Retention, Transcript and Residency data in reverse date order.
         //:OrderEntityForView( mStudent, "RetentionRisk", "CreationDate D" )
         OrderEntityForView( mStudent, "RetentionRisk", "CreationDate D" );
         //:OrderEntityForView( mStudent, "TranscriptRequest", "CreationDate D" )
         OrderEntityForView( mStudent, "TranscriptRequest", "CreationDate D" );
         //:OrderEntityForView( mStudent, "TermOfResidence", "BeginDate D" )
         OrderEntityForView( mStudent, "TermOfResidence", "BeginDate D" );

         //:// Order Student Major/Degree Track according to Ranking.
         //:IF mStudent.StudentMajorDegreeTrack EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mStudent, "StudentMajorDegreeTrack" );
         if ( lTempInteger_0 == 0 )
         { 
            //:IF mStudent.StudentMajorDegreeTrack.Ranking > 0
            if ( CompareAttributeToInteger( mStudent, "StudentMajorDegreeTrack", "Ranking", 0 ) > 0 )
            { 
               //:OrderEntityForView( mStudent, "StudentMajorDegreeTrack", "Ranking A" )
               OrderEntityForView( mStudent, "StudentMajorDegreeTrack", "Ranking A" );
            } 

            //:END
         } 

         //:END

         //:// Build Chapel Attendance Summary entries.
         //:GET VIEW lTermLST NAMED "lTermLST"
         RESULT = GetViewByName( lTermLST, "lTermLST", mStudent, zLEVEL_TASK );
         //:CreateViewFromView( lTermLST2, lTermLST )
         CreateViewFromView( lTermLST2, lTermLST );
         //:FOR EACH mStudent.ChapelSession 
         RESULT = mStudent.cursor( "ChapelSession" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST mStudent.ChapelAttendanceByTerm 
            //:     WHERE mStudent.ChapelAttendanceByTerm.CollegeTerm = mStudent.CollegeTerm.YearSemester   
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, mStudent, "CollegeTerm", "YearSemester" );
            szTempString_0 = sb_szTempString_0.toString( );}
            RESULT = mStudent.cursor( "ChapelAttendanceByTerm" ).setFirst( "CollegeTerm", szTempString_0 ).toInt();
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:CREATE ENTITY mStudent.ChapelAttendanceByTerm
               RESULT = CreateEntity( mStudent, "ChapelAttendanceByTerm", zPOS_AFTER );
               //:// We keep CollegeYear and Semester for sorting purposes.
               //:szCollegeTerm = mStudent.CollegeTerm.YearSemester
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_szCollegeTerm;
               if ( szCollegeTerm == null )
                  sb_szCollegeTerm = new StringBuilder( 32 );
               else
                  sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                               GetVariableFromAttribute( sb_szCollegeTerm, mi_lTempInteger_1, 'S', 21, mStudent, "CollegeTerm", "YearSemester", "", 0 );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );
               szCollegeTerm = sb_szCollegeTerm.toString( );}
               //:mStudent.ChapelAttendanceByTerm.CollegeTerm          = szCollegeTerm
               SetAttributeFromString( mStudent, "ChapelAttendanceByTerm", "CollegeTerm", szCollegeTerm );
               //:mStudent.ChapelAttendanceByTerm.CollegeYear          = szCollegeTerm[1:9]
                {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                              ZeidonStringCopy( sb_szTempString_0, 1, 0, szCollegeTerm, 1, 9, 21 );
               szTempString_0 = sb_szTempString_0.toString( );}
               SetAttributeFromString( mStudent, "ChapelAttendanceByTerm", "CollegeYear", szTempString_0 );
               //:mStudent.ChapelAttendanceByTerm.Semester             = mStudent.CollegeTerm.Semester
               SetAttributeFromAttribute( mStudent, "ChapelAttendanceByTerm", "Semester", mStudent, "CollegeTerm", "Semester" );
               //:SET CURSOR FIRST lTermLST2.CollegeTerm WHERE lTermLST2.CollegeTerm.YearSemester = mStudent.CollegeTerm.YearSemester 
               {StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetStringFromAttribute( sb_szTempString_1, mStudent, "CollegeTerm", "YearSemester" );
               szTempString_1 = sb_szTempString_1.toString( );}
               RESULT = lTermLST2.cursor( "CollegeTerm" ).setFirst( "YearSemester", szTempString_1 ).toInt();
               //:mStudent.ChapelAttendanceByTerm.TotalChapelsRequired = lTermLST2.CollegeTerm.NumberOfChapelsRequired
               SetAttributeFromAttribute( mStudent, "ChapelAttendanceByTerm", "TotalChapelsRequired", lTermLST2, "CollegeTerm", "NumberOfChapelsRequired" );
            } 

            //:END
            //:mStudent.ChapelAttendanceByTerm.TotalChapelsAttended = mStudent.ChapelAttendanceByTerm.TotalChapelsAttended + 1
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, mStudent, "ChapelAttendanceByTerm", "TotalChapelsAttended" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            lTempInteger_3 = lTempInteger_2 + 1;
            SetAttributeFromInteger( mStudent, "ChapelAttendanceByTerm", "TotalChapelsAttended", lTempInteger_3 );
            RESULT = mStudent.cursor( "ChapelSession" ).setNext().toInt();
         } 

         //:END
         //:FOR EACH mStudent.OverrideChapelRequirement
         RESULT = mStudent.cursor( "OverrideChapelRequirement" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST lTermLST2.CollegeTerm WHERE lTermLST2.CollegeTerm.ID = mStudent.OverrideChapelReqCollegeTerm.ID 
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                         GetIntegerFromAttribute( mi_lTempInteger_4, mStudent, "OverrideChapelReqCollegeTerm", "ID" );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );}
            RESULT = lTermLST2.cursor( "CollegeTerm" ).setFirst( "ID", lTempInteger_4 ).toInt();
            //:SET CURSOR FIRST mStudent.ChapelAttendanceByTerm WHERE mStudent.ChapelAttendanceByTerm.CollegeTerm = lTermLST2.CollegeTerm.YearSemester
            {StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetStringFromAttribute( sb_szTempString_1, lTermLST2, "CollegeTerm", "YearSemester" );
            szTempString_1 = sb_szTempString_1.toString( );}
            RESULT = mStudent.cursor( "ChapelAttendanceByTerm" ).setFirst( "CollegeTerm", szTempString_1 ).toInt();
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:// A ChapelAttendanceByTerm entity already exists for the Term, just add the Override value.
               //:mStudent.ChapelAttendanceByTerm.TotalChapelsRequiredOverride = mStudent.OverrideChapelRequirement.NumberOfChapelsRequiredForTerm
               SetAttributeFromAttribute( mStudent, "ChapelAttendanceByTerm", "TotalChapelsRequiredOverride", mStudent, "OverrideChapelRequirement", "NumberOfChapelsRequiredForTerm" );
               //:ELSE
            } 
            else
            { 
               //:// We need to add the ChapelAttendanceByTerm for the Override Term.
               //:CREATE ENTITY mStudent.ChapelAttendanceByTerm
               RESULT = CreateEntity( mStudent, "ChapelAttendanceByTerm", zPOS_AFTER );
               //:szCollegeTerm                                                = lTermLST2.CollegeTerm.YearSemester 
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szCollegeTerm;
               if ( szCollegeTerm == null )
                  sb_szCollegeTerm = new StringBuilder( 32 );
               else
                  sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                               GetVariableFromAttribute( sb_szCollegeTerm, mi_lTempInteger_5, 'S', 21, lTermLST2, "CollegeTerm", "YearSemester", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szCollegeTerm = sb_szCollegeTerm.toString( );}
               //:mStudent.ChapelAttendanceByTerm.CollegeTerm                  = szCollegeTerm
               SetAttributeFromString( mStudent, "ChapelAttendanceByTerm", "CollegeTerm", szCollegeTerm );
               //:mStudent.ChapelAttendanceByTerm.CollegeYear                  = szCollegeTerm[1:9]
                {StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                              ZeidonStringCopy( sb_szTempString_1, 1, 0, szCollegeTerm, 1, 9, 21 );
               szTempString_1 = sb_szTempString_1.toString( );}
               SetAttributeFromString( mStudent, "ChapelAttendanceByTerm", "CollegeYear", szTempString_1 );
               //:mStudent.ChapelAttendanceByTerm.Semester                     = lTermLST2.CollegeTerm.Semester 
               SetAttributeFromAttribute( mStudent, "ChapelAttendanceByTerm", "Semester", lTermLST2, "CollegeTerm", "Semester" );
               //:mStudent.ChapelAttendanceByTerm.TotalChapelsRequired         = lTermLST2.CollegeTerm.NumberOfChapelsRequired 
               SetAttributeFromAttribute( mStudent, "ChapelAttendanceByTerm", "TotalChapelsRequired", lTermLST2, "CollegeTerm", "NumberOfChapelsRequired" );
               //:mStudent.ChapelAttendanceByTerm.TotalChapelsRequiredOverride = mStudent.OverrideChapelRequirement.NumberOfChapelsRequiredForTerm
               SetAttributeFromAttribute( mStudent, "ChapelAttendanceByTerm", "TotalChapelsRequiredOverride", mStudent, "OverrideChapelRequirement", "NumberOfChapelsRequiredForTerm" );
            } 

            RESULT = mStudent.cursor( "OverrideChapelRequirement" ).setNext().toInt();
            //:END
         } 

         //:END
         //:DropView( lTermLST2 )
         DropView( lTermLST2 );
         //:OrderEntityForView( mStudent, "ChapelAttendanceByTerm", "CollegeYear D Semester D" )
         OrderEntityForView( mStudent, "ChapelAttendanceByTerm", "CollegeYear D Semester D" );
         //:OrderEntityForView( mStudent, "ChapelSession", "Date D" )
         OrderEntityForView( mStudent, "ChapelSession", "Date D" );

         //:// Generate Degree1 and Degree2 relationships.
         //:FOR EACH mStudent.StudentMajorDegreeTrack 
         RESULT = mStudent.cursor( "StudentMajorDegreeTrack" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mStudent.DegreeTrackCollegeDegree EXISTS
            lTempInteger_6 = CheckExistenceOfEntity( mStudent, "DegreeTrackCollegeDegree" );
            if ( lTempInteger_6 == 0 )
            { 
               //:IF mStudent.CollegeDegree1 EXISTS
               lTempInteger_7 = CheckExistenceOfEntity( mStudent, "CollegeDegree1" );
               if ( lTempInteger_7 == 0 )
               { 
                  //:IF mStudent.CollegeDegree1.ID != mStudent.DegreeTrackCollegeDegree.ID 
                  if ( CompareAttributeToAttribute( mStudent, "CollegeDegree1", "ID", mStudent, "DegreeTrackCollegeDegree", "ID" ) != 0 )
                  { 
                     //:IF mStudent.CollegeDegree2 DOES NOT EXIST
                     lTempInteger_8 = CheckExistenceOfEntity( mStudent, "CollegeDegree2" );
                     if ( lTempInteger_8 != 0 )
                     { 
                        //:INCLUDE mStudent.CollegeDegree2 FROM mStudent.DegreeTrackCollegeDegree
                        RESULT = IncludeSubobjectFromSubobject( mStudent, "CollegeDegree2", mStudent, "DegreeTrackCollegeDegree", zPOS_AFTER );
                     } 

                     //:END
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:INCLUDE mStudent.CollegeDegree1 FROM mStudent.DegreeTrackCollegeDegree
                  RESULT = IncludeSubobjectFromSubobject( mStudent, "CollegeDegree1", mStudent, "DegreeTrackCollegeDegree", zPOS_AFTER );
               } 

               //:END
            } 

            RESULT = mStudent.cursor( "StudentMajorDegreeTrack" ).setNext().toInt();
            //:END 
         } 

         //:END

         //:// Build display subobject of Transcript Requests.
         //:FOR EACH mStudent.TranscriptRequest
         RESULT = mStudent.cursor( "TranscriptRequest" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:INCLUDE mStudent.DisplayTranscriptRequest FROM mStudent.TranscriptRequest 
            RESULT = IncludeSubobjectFromSubobject( mStudent, "DisplayTranscriptRequest", mStudent, "TranscriptRequest", zPOS_AFTER );
            RESULT = mStudent.cursor( "TranscriptRequest" ).setNext().toInt();
         } 

         //:END
         //:OrderEntityForView( mStudent, "DisplayTranscriptRequest", "ID A" )
         OrderEntityForView( mStudent, "DisplayTranscriptRequest", "ID A" );

         //:// Build derived paths for Degree Candidacy relationship to StudentMajorDegreeTrack and CurrentDegreeCandidacy.
         //:FOR EACH mStudent.StudentMajorDegreeTrack 
         RESULT = mStudent.cursor( "StudentMajorDegreeTrack" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mStudent.SMDT_DegreeCandidacy EXISTS
            lTempInteger_9 = CheckExistenceOfEntity( mStudent, "SMDT_DegreeCandidacy" );
            if ( lTempInteger_9 == 0 )
            { 
               //:SET CURSOR FIRST mStudent.DegreeCandidacy WHERE mStudent.DegreeCandidacy.ID = mStudent.SMDT_DegreeCandidacy.ID 
               {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
                               GetIntegerFromAttribute( mi_lTempInteger_10, mStudent, "SMDT_DegreeCandidacy", "ID" );
               lTempInteger_10 = mi_lTempInteger_10.intValue( );}
               RESULT = mStudent.cursor( "DegreeCandidacy" ).setFirst( "ID", lTempInteger_10 ).toInt();
               //:INCLUDE mStudent.MajorTrackDegreeCandidacy FROM mStudent.DegreeCandidacy 
               RESULT = IncludeSubobjectFromSubobject( mStudent, "MajorTrackDegreeCandidacy", mStudent, "DegreeCandidacy", zPOS_AFTER );
               //:INCLUDE mStudent.DC_StudentMajorDegreeTrack FROM mStudent.StudentMajorDegreeTrack 
               RESULT = IncludeSubobjectFromSubobject( mStudent, "DC_StudentMajorDegreeTrack", mStudent, "StudentMajorDegreeTrack", zPOS_AFTER );
            } 

            RESULT = mStudent.cursor( "StudentMajorDegreeTrack" ).setNext().toInt();
            //:END
         } 

         //:END
         //:IF mStudent.DegreeCandidacy EXISTS
         lTempInteger_11 = CheckExistenceOfEntity( mStudent, "DegreeCandidacy" );
         if ( lTempInteger_11 == 0 )
         { 
            //:// Se need to set a work date to be used in sorting DegreeCandidacy entries in descending date order, so that the
            //:// most recent entry will be first. If there is a null entry, make it first.
            //:FOR EACH mStudent.DegreeCandidacy 
            RESULT = mStudent.cursor( "DegreeCandidacy" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF mStudent.DC_GraduationDate EXISTS
               lTempInteger_12 = CheckExistenceOfEntity( mStudent, "DC_GraduationDate" );
               if ( lTempInteger_12 == 0 )
               { 
                  //:mStudent.DegreeCandidacy.wSortDate = mStudent.DC_GraduationDate.Date 
                  SetAttributeFromAttribute( mStudent, "DegreeCandidacy", "wSortDate", mStudent, "DC_GraduationDate", "Date" );
                  //:ELSE
               } 
               else
               { 
                  //:IF mStudent.DC_AnticipatedGraduationDate EXISTS
                  lTempInteger_13 = CheckExistenceOfEntity( mStudent, "DC_AnticipatedGraduationDate" );
                  if ( lTempInteger_13 == 0 )
                  { 
                     //:mStudent.DegreeCandidacy.wSortDate = mStudent.DC_AnticipatedGraduationDate.Date 
                     SetAttributeFromAttribute( mStudent, "DegreeCandidacy", "wSortDate", mStudent, "DC_AnticipatedGraduationDate", "Date" );
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF mStudent.DegreeCandidacy.ReceivedDate != ""
                     if ( CompareAttributeToString( mStudent, "DegreeCandidacy", "ReceivedDate", "" ) != 0 )
                     { 
                        //:mStudent.DegreeCandidacy.wSortDate = mStudent.DegreeCandidacy.ReceivedDate
                        SetAttributeFromAttribute( mStudent, "DegreeCandidacy", "wSortDate", mStudent, "DegreeCandidacy", "ReceivedDate" );
                        //:ELSE
                     } 
                     else
                     { 
                        //:mStudent.DegreeCandidacy.wSortDate = "21000101"
                        SetAttributeFromString( mStudent, "DegreeCandidacy", "wSortDate", "21000101" );
                     } 

                     //:END
                  } 

                  //:END
               } 

               RESULT = mStudent.cursor( "DegreeCandidacy" ).setNext().toInt();
               //:END
            } 

            //:END
            //:OrderEntityForView( mStudent, "DegreeCandidacy", "wSortDate D" )
            OrderEntityForView( mStudent, "DegreeCandidacy", "wSortDate D" );
            //:SET CURSOR FIRST mStudent.DegreeCandidacy 
            RESULT = mStudent.cursor( "DegreeCandidacy" ).setFirst().toInt();
            //:INCLUDE mStudent.CurrentDegreeCandidacy FROM mStudent.DegreeCandidacy 
            RESULT = IncludeSubobjectFromSubobject( mStudent, "CurrentDegreeCandidacy", mStudent, "DegreeCandidacy", zPOS_AFTER );
            //:IF mStudent.DC_StudentMajorDegreeTrack EXISTS 
            lTempInteger_14 = CheckExistenceOfEntity( mStudent, "DC_StudentMajorDegreeTrack" );
            if ( lTempInteger_14 == 0 )
            { 
               //:INCLUDE mStudent.CDC_StudentMajorDegreeTrack FROM mStudent.DC_StudentMajorDegreeTrack 
               RESULT = IncludeSubobjectFromSubobject( mStudent, "CDC_StudentMajorDegreeTrack", mStudent, "DC_StudentMajorDegreeTrack", zPOS_AFTER );
            } 

            //:END
         } 

         //:END

         //:// Drop any Private Categories that are not from this Functional Area.
         //:nRC = GetViewByName( sAppMgr, "sAppMgr", mStudent, zLEVEL_APPLICATION )
         nRC = GetViewByName( sAppMgr, "sAppMgr", mStudent, zLEVEL_APPLICATION );
         //:IF  nRC >= 0 
         if ( nRC >= 0 )
         { 
            //:FOR EACH mStudent.Category 
            RESULT = mStudent.cursor( "Category" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF mStudent.Category.PrivateToFunctionalAreaFlag = "Y"
               if ( CompareAttributeToString( mStudent, "Category", "PrivateToFunctionalAreaFlag", "Y" ) == 0 )
               { 
                  //:IF mStudent.Category.FunctionalArea != sAppMgr.FunctionalArea.FunctionalAreaName 
                  if ( CompareAttributeToAttribute( mStudent, "Category", "FunctionalArea", sAppMgr, "FunctionalArea", "FunctionalAreaName" ) != 0 )
                  { 
                     //:DropEntity( mStudent, "Category", zREPOS_NONE )
                     DropEntity( mStudent, "Category", zREPOS_NONE );
                  } 

                  //:END
               } 

               RESULT = mStudent.cursor( "Category" ).setNext().toInt();
               //:END
            } 

            //:END
         } 

         //:END

         //:// Keep Legal Name for change comparison on COMMIT.
         //:mStudent.Student.wOriginalLegalName = mStudent.Student.LegalName
         SetAttributeFromAttribute( mStudent, "Student", "wOriginalLegalName", mStudent, "Student", "LegalName" );
         break ;

      //:  /* end zOCE_ACTIVATE */
      //:OF   zOCE_ACTIVATE_EMPTY:
      case zOCE_ACTIVATE_EMPTY :
         break ;

      //:  /* end zOCE_ACTIVATE_EMPTY */
      //:OF   zOCE_COMMIT:
      case zOCE_COMMIT :

    	  /*
         //:IF mStudent.Student EXISTS
         lTempInteger_15 = CheckExistenceOfEntity( mStudent, "Student" );
         if ( lTempInteger_15 == 0 )
         { 

            //:// If the Legal Name has been changed, create change entry.
            //:IF mStudent.Student.wOriginalLegalName != mStudent.Student.LegalName
            if ( CompareAttributeToAttribute( mStudent, "Student", "wOriginalLegalName", mStudent, "Student", "LegalName" ) != 0 )
            { 
               //:CREATE ENTITY mStudent.PersonalDataModification 
               RESULT = CreateEntity( mStudent, "PersonalDataModification", zPOS_AFTER );
               //:mStudent.PersonalDataModification.OriginalLegalName = mStudent.Student.wOriginalLegalName 
               SetAttributeFromAttribute( mStudent, "PersonalDataModification", "OriginalLegalName", mStudent, "Student", "wOriginalLegalName" );
               //:mStudent.PersonalDataModification.NewLegalName      = mStudent.Student.LegalName 
               SetAttributeFromAttribute( mStudent, "PersonalDataModification", "NewLegalName", mStudent, "Student", "LegalName" );
               //:SetAttributeFromCurrentDateTime( mStudent, "PersonalDataModification", "ModifiedDateTime" )
               //:GetViewByName( mUser, "mUser", mStudent, zLEVEL_APPLICATION )
               GetViewByName( mUser, "mUser", mStudent, zLEVEL_APPLICATION );
               //:INCLUDE mStudent.PersonalDataModificationUser FROM mUser.User
               RESULT = IncludeSubobjectFromSubobject( mStudent, "PersonalDataModificationUser", mUser, "User", zPOS_AFTER );
               //:mStudent.Student.wOriginalLegalName = mStudent.Student.LegalName
               SetAttributeFromAttribute( mStudent, "Student", "wOriginalLegalName", mStudent, "Student", "LegalName" );
            } 

            //:END

            //:// Set Graduation Dates in StudentMajorDegreeTrack entries, if a Graduation Date has been set in associated Degree Candidacy.
            //:CreateViewFromView( mStudentT, mStudent )
            CreateViewFromView( mStudentT, mStudent );
            //:FOR EACH mStudentT.StudentMajorDegreeTrack 
            RESULT = mStudentT.cursor( "StudentMajorDegreeTrack" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF mStudentT.SMDT_GraduationDate EXISTS
               lTempInteger_16 = CheckExistenceOfEntity( mStudentT, "SMDT_GraduationDate" );
               if ( lTempInteger_16 == 0 )
               { 
                  //:IF mStudentT.StudentMajorDegreeTrack.GraduationDate != mStudentT.SMDT_GraduationDate.Date 
                  if ( CompareAttributeToAttribute( mStudentT, "StudentMajorDegreeTrack", "GraduationDate", mStudentT, "SMDT_GraduationDate", "Date" ) != 0 )
                  { 
                     //:mStudentT.StudentMajorDegreeTrack.GraduationDate = mStudentT.SMDT_GraduationDate.Date
                     SetAttributeFromAttribute( mStudentT, "StudentMajorDegreeTrack", "GraduationDate", mStudentT, "SMDT_GraduationDate", "Date" );
                  } 

                  //:END
               } 

               RESULT = mStudentT.cursor( "StudentMajorDegreeTrack" ).setNext().toInt();
               //:END
            } 

            //:END
            //:DropView( mStudentT )
            DropView( mStudentT );

            //:// For students that ARE NOT Undergraduate Main Campus, always set ResidencyStatus to Commuter.
            //:szCurrentAcademicLevel = mStudent.Student.CurrentLevel
            {MutableInt mi_lTempInteger_17 = new MutableInt( lTempInteger_17 );
            StringBuilder sb_szCurrentAcademicLevel;
            if ( szCurrentAcademicLevel == null )
               sb_szCurrentAcademicLevel = new StringBuilder( 32 );
            else
               sb_szCurrentAcademicLevel = new StringBuilder( szCurrentAcademicLevel );
                         GetVariableFromAttribute( sb_szCurrentAcademicLevel, mi_lTempInteger_17, 'S', 2, mStudent, "Student", "CurrentLevel", "", 0 );
            lTempInteger_17 = mi_lTempInteger_17.intValue( );
            szCurrentAcademicLevel = sb_szCurrentAcademicLevel.toString( );}
            //:IF mStudent.AdministrativeDivision.ID = 1 AND
            //:( szCurrentAcademicLevel = "1" OR     // Freshman
            //:szCurrentAcademicLevel = "2" OR     // Sophomore
            //:szCurrentAcademicLevel = "3" OR     // Junior
            //:szCurrentAcademicLevel = "4" OR     // Senior
            //:szCurrentAcademicLevel = "7" OR     // Special
            //:szCurrentAcademicLevel = "8" OR     // Freshman-2
            //:szCurrentAcademicLevel = "9" )      // 5-Yr Senior
            if ( CompareAttributeToInteger( mStudent, "AdministrativeDivision", "ID", 1 ) == 0 && ( ZeidonStringCompare( szCurrentAcademicLevel, 1, 0, "1", 1, 0, 2 ) == 0 || ZeidonStringCompare( szCurrentAcademicLevel, 1, 0, "2", 1, 0, 2 ) == 0 ||
                 ZeidonStringCompare( szCurrentAcademicLevel, 1, 0, "3", 1, 0, 2 ) == 0 || ZeidonStringCompare( szCurrentAcademicLevel, 1, 0, "4", 1, 0, 2 ) == 0 || ZeidonStringCompare( szCurrentAcademicLevel, 1, 0, "7", 1, 0, 2 ) == 0 ||
                 ZeidonStringCompare( szCurrentAcademicLevel, 1, 0, "8", 1, 0, 2 ) == 0 || ZeidonStringCompare( szCurrentAcademicLevel, 1, 0, "9", 1, 0, 2 ) == 0 ) )
            { 

               //:// This is case where student is Main Campus undergraduate.

               //:ELSE
            } 
            else
            { 
               //:IF mStudent.Student.ResidencyStatus != "C"
               if ( CompareAttributeToString( mStudent, "Student", "ResidencyStatus", "C" ) != 0 )
               { 
                  //:mStudent.Student.ResidencyStatus = "C"
                  SetAttributeFromString( mStudent, "Student", "ResidencyStatus", "C" );
               } 

               //:END
            } 

            //:END

            //:// Automatically set Student graduation date attributes.
            //:IF mStudent.Student.UndergradGraduationDate = ""
            if ( CompareAttributeToString( mStudent, "Student", "UndergradGraduationDate", "" ) == 0 )
            { 
               //:CreateViewFromView( mStudentT, mStudent )
               CreateViewFromView( mStudentT, mStudent );
               //:SET CURSOR FIRST mStudentT.DegreeMajorCollege WHERE mStudentT.DegreeMajorCollege.Type = "U"
               RESULT = mStudentT.cursor( "DegreeMajorCollege" ).setFirst( "Type", "U" ).toInt();
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:mStudent.Student.UndergradGraduationDate = mStudentT.StudentMajorDegreeTrack.GraduationDate 
                  SetAttributeFromAttribute( mStudent, "Student", "UndergradGraduationDate", mStudentT, "StudentMajorDegreeTrack", "GraduationDate" );
               } 

               //:END
               //:DropView( mStudentT )
               DropView( mStudentT );
            } 

            //:END
            //:IF mStudent.Student.GradGraduationDate = ""
            if ( CompareAttributeToString( mStudent, "Student", "GradGraduationDate", "" ) == 0 )
            { 
               //:CreateViewFromView( mStudentT, mStudent )
               CreateViewFromView( mStudentT, mStudent );
               //:SET CURSOR FIRST mStudentT.DegreeMajorCollege WHERE mStudentT.DegreeMajorCollege.Type = "G"
               RESULT = mStudentT.cursor( "DegreeMajorCollege" ).setFirst( "Type", "G" ).toInt();
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:mStudent.Student.GradGraduationDate = mStudentT.StudentMajorDegreeTrack.GraduationDate 
                  SetAttributeFromAttribute( mStudent, "Student", "GradGraduationDate", mStudentT, "StudentMajorDegreeTrack", "GraduationDate" );
               } 

               //:END
               //:DropView( mStudentT )
               DropView( mStudentT );
            } 

            //:END

            //:// If the Anticipated Grad Date exists, force the Clearing House Grad Date to that value.
            //:IF mStudent.DC_AnticipatedGraduationDate EXISTS
            lTempInteger_18 = CheckExistenceOfEntity( mStudent, "DC_AnticipatedGraduationDate" );
            if ( lTempInteger_18 == 0 )
            { 
               //:mStudent.Student.ClearingHouseGradDate = mStudent.DC_AnticipatedGraduationDate.Date  
               SetAttributeFromAttribute( mStudent, "Student", "ClearingHouseGradDate", mStudent, "DC_AnticipatedGraduationDate", "Date" );
            } 

            //:END

            //:// REGULAR CHANGE LOG
            //:// Create the Change Log entry.
            //:GenerateTransactionEntryWDesc( mStudent, "General Student Data" )

            //:// STUDENT CHANGE LOG AND HISTORY
            //:// Generate Student Change Log entries, if something has changed. We will determine this by looking at
            //:// the last StudentChangeHistory entry and comparing its values.
            //:szAnticipatedGradPeriod = ""
             {StringBuilder sb_szAnticipatedGradPeriod;
            if ( szAnticipatedGradPeriod == null )
               sb_szAnticipatedGradPeriod = new StringBuilder( 32 );
            else
               sb_szAnticipatedGradPeriod = new StringBuilder( szAnticipatedGradPeriod );
                        ZeidonStringCopy( sb_szAnticipatedGradPeriod, 1, 0, "", 1, 0, 51 );
            szAnticipatedGradPeriod = sb_szAnticipatedGradPeriod.toString( );}
            //:IF mStudent.DC_AnticipatedGraduationDate EXISTS
            lTempInteger_19 = CheckExistenceOfEntity( mStudent, "DC_AnticipatedGraduationDate" );
            if ( lTempInteger_19 == 0 )
            { 
               //:szAnticipatedGradPeriod = mStudent.DC_AnticipatedGraduationDate.Name 
               {MutableInt mi_lTempInteger_20 = new MutableInt( lTempInteger_20 );
               StringBuilder sb_szAnticipatedGradPeriod;
               if ( szAnticipatedGradPeriod == null )
                  sb_szAnticipatedGradPeriod = new StringBuilder( 32 );
               else
                  sb_szAnticipatedGradPeriod = new StringBuilder( szAnticipatedGradPeriod );
                               GetVariableFromAttribute( sb_szAnticipatedGradPeriod, mi_lTempInteger_20, 'S', 51, mStudent, "DC_AnticipatedGraduationDate", "Name", "", 0 );
               lTempInteger_20 = mi_lTempInteger_20.intValue( );
               szAnticipatedGradPeriod = sb_szAnticipatedGradPeriod.toString( );}
            } 

            //:END
            //:ACTIVATE mStuChgH WHERE mStuChgH.StudentChangeHistory.LastDate = ""
            //:              AND mStuChgH.Student.ID = mStudent.Student.ID 
            {MutableInt mi_lTempInteger_21 = new MutableInt( lTempInteger_21 );
                         GetIntegerFromAttribute( mi_lTempInteger_21, mStudent, "Student", "ID" );
            lTempInteger_21 = mi_lTempInteger_21.intValue( );}
            omStudent_fnLocalBuildQual_0( mStudent, vTempViewVar_0, lTempInteger_21 );
            RESULT = ActivateObjectInstance( mStuChgH, "mStuChgH", mStudent, vTempViewVar_0, zSINGLE );
            DropView( vTempViewVar_0 );
            //:IF RESULT < 0
            if ( RESULT < 0 )
            { 
               //:// Since no history record has yet been created, create it here.
               //:ACTIVATE mStuChgH EMPTY 
               RESULT = ActivateEmptyObjectInstance( mStuChgH, "mStuChgH", mStudent, zSINGLE );
               //:CREATE ENTITY mStuChgH.StudentChangeHistory 
               RESULT = CreateEntity( mStuChgH, "StudentChangeHistory", zPOS_AFTER );
               //:mStuChgH.StudentChangeHistory.StudentStatus              = mStudent.Student.Status 
               SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "StudentStatus", mStudent, "Student", "Status" );
               //:mStuChgH.StudentChangeHistory.Classification             = mStudent.Student.CurrentLevel 
               SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "Classification", mStudent, "Student", "CurrentLevel" );
               //:mStuChgH.StudentChangeHistory.ResidencyStatus            = mStudent.Student.ResidencyStatus 
               SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "ResidencyStatus", mStudent, "Student", "ResidencyStatus" );
               //:mStuChgH.StudentChangeHistory.FinancialHoldFlag          = mStudent.Student.FinancialHoldFlag 
               SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "FinancialHoldFlag", mStudent, "Student", "FinancialHoldFlag" );
               //:mStuChgH.StudentChangeHistory.AdministrativeDivisionName = mStudent.AdministrativeDivision.Name 
               SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "AdministrativeDivisionName", mStudent, "AdministrativeDivision", "Name" );
               //:mStuChgH.StudentChangeHistory.AnticipatedGradPeriod      = szAnticipatedGradPeriod
               SetAttributeFromString( mStuChgH, "StudentChangeHistory", "AnticipatedGradPeriod", szAnticipatedGradPeriod );
               //:SetAttributeFromCurrentDateTime( mStuChgH, "StudentChangeHistory", "FirstDate" ) 
               //:INCLUDE mStuChgH.Student FROM mStudent.Student
               RESULT = IncludeSubobjectFromSubobject( mStuChgH, "Student", mStudent, "Student", zPOS_AFTER );
               //:ELSE
            } 
            else
            { 
               //:// There is a History entry, so see if anything has changed.
               //:szChangeFlag = ""
                {StringBuilder sb_szChangeFlag;
               if ( szChangeFlag == null )
                  sb_szChangeFlag = new StringBuilder( 32 );
               else
                  sb_szChangeFlag = new StringBuilder( szChangeFlag );
                              ZeidonStringCopy( sb_szChangeFlag, 1, 0, "", 1, 0, 2 );
               szChangeFlag = sb_szChangeFlag.toString( );}
               //:GetViewByName( mUser, "mUser", mStudent, zLEVEL_APPLICATION )
               GetViewByName( mUser, "mUser", mStudent, zLEVEL_APPLICATION );

               //:// Student Status
               //:IF mStuChgH.StudentChangeHistory.StudentStatus != mStudent.Student.Status 
               if ( CompareAttributeToAttribute( mStuChgH, "StudentChangeHistory", "StudentStatus", mStudent, "Student", "Status" ) != 0 )
               { 
                  //:ACTIVATE mStuChgL EMPTY 
                  RESULT = ActivateEmptyObjectInstance( mStuChgL, "mStuChgL", mStudent, zSINGLE );
                  //:CREATE ENTITY mStuChgL.StudentChangeLog 
                  RESULT = CreateEntity( mStuChgL, "StudentChangeLog", zPOS_AFTER );
                  //:GetStringFromAttributeByContext( szOriginalValue, mStuChgH, "StudentChangeHistory", "StudentStatus", "", 50 )
                  {StringBuilder sb_szOriginalValue;
                  if ( szOriginalValue == null )
                     sb_szOriginalValue = new StringBuilder( 32 );
                  else
                     sb_szOriginalValue = new StringBuilder( szOriginalValue );
                                     GetStringFromAttributeByContext( sb_szOriginalValue, mStuChgH, "StudentChangeHistory", "StudentStatus", "", 50 );
                  szOriginalValue = sb_szOriginalValue.toString( );}
                  //:GetStringFromAttributeByContext( szNewValue,      mStudent, "Student", "Status", "", 50 )
                  {StringBuilder sb_szNewValue;
                  if ( szNewValue == null )
                     sb_szNewValue = new StringBuilder( 32 );
                  else
                     sb_szNewValue = new StringBuilder( szNewValue );
                                     GetStringFromAttributeByContext( sb_szNewValue, mStudent, "Student", "Status", "", 50 );
                  szNewValue = sb_szNewValue.toString( );}
                  //:mStuChgL.StudentChangeLog.AttributeName = "StudentStatus"
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "AttributeName", "StudentStatus" );
                  //:mStuChgL.StudentChangeLog.OriginalValue = szOriginalValue
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "OriginalValue", szOriginalValue );
                  //:mStuChgL.StudentChangeLog.NewValue      = szNewValue
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "NewValue", szNewValue );
                  //:mStuChgL.StudentChangeLog.UserName      = mUser.User.UserName 
                  SetAttributeFromAttribute( mStuChgL, "StudentChangeLog", "UserName", mUser, "User", "UserName" );
                  //:SetAttributeFromCurrentDateTime( mStuChgL, "StudentChangeLog", "ModificationDate" )
                 //:INCLUDE mStuChgL.Student FROM mStudent.Student
                  RESULT = IncludeSubobjectFromSubobject( mStuChgL, "Student", mStudent, "Student", zPOS_AFTER );
                  //:COMMIT mStuChgL
                  RESULT = CommitObjectInstance( mStuChgL );
                  //:DropObjectInstance( mStuChgL )
                  DropObjectInstance( mStuChgL );
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

               //:// Classification (CurrentLevel)
               //:IF mStuChgH.StudentChangeHistory.Classification != mStudent.Student.CurrentLevel 
               if ( CompareAttributeToAttribute( mStuChgH, "StudentChangeHistory", "Classification", mStudent, "Student", "CurrentLevel" ) != 0 )
               { 
                  //:ACTIVATE mStuChgL EMPTY 
                  RESULT = ActivateEmptyObjectInstance( mStuChgL, "mStuChgL", mStudent, zSINGLE );
                  //:CREATE ENTITY mStuChgL.StudentChangeLog 
                  RESULT = CreateEntity( mStuChgL, "StudentChangeLog", zPOS_AFTER );
                  //:GetStringFromAttributeByContext( szOriginalValue, mStuChgH, "StudentChangeHistory", "Classification", "", 50 )
                  {StringBuilder sb_szOriginalValue;
                  if ( szOriginalValue == null )
                     sb_szOriginalValue = new StringBuilder( 32 );
                  else
                     sb_szOriginalValue = new StringBuilder( szOriginalValue );
                                     GetStringFromAttributeByContext( sb_szOriginalValue, mStuChgH, "StudentChangeHistory", "Classification", "", 50 );
                  szOriginalValue = sb_szOriginalValue.toString( );}
                  //:GetStringFromAttributeByContext( szNewValue,      mStudent, "Student", "CurrentLevel", "", 50 )
                  {StringBuilder sb_szNewValue;
                  if ( szNewValue == null )
                     sb_szNewValue = new StringBuilder( 32 );
                  else
                     sb_szNewValue = new StringBuilder( szNewValue );
                                     GetStringFromAttributeByContext( sb_szNewValue, mStudent, "Student", "CurrentLevel", "", 50 );
                  szNewValue = sb_szNewValue.toString( );}
                  //:mStuChgL.StudentChangeLog.AttributeName = "Classification"
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "AttributeName", "Classification" );
                  //:mStuChgL.StudentChangeLog.OriginalValue = szOriginalValue
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "OriginalValue", szOriginalValue );
                  //:mStuChgL.StudentChangeLog.NewValue      = szNewValue
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "NewValue", szNewValue );
                  //:mStuChgL.StudentChangeLog.UserName      = mUser.User.UserName 
                  SetAttributeFromAttribute( mStuChgL, "StudentChangeLog", "UserName", mUser, "User", "UserName" );
                  //:SetAttributeFromCurrentDateTime( mStuChgL, "StudentChangeLog", "ModificationDate" )
                  //:INCLUDE mStuChgL.Student FROM mStudent.Student
                  RESULT = IncludeSubobjectFromSubobject( mStuChgL, "Student", mStudent, "Student", zPOS_AFTER );
                  //:COMMIT mStuChgL
                  RESULT = CommitObjectInstance( mStuChgL );
                  //:DropObjectInstance( mStuChgL )
                  DropObjectInstance( mStuChgL );
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

               //:// Residency Status
               //:IF mStuChgH.StudentChangeHistory.ResidencyStatus != mStudent.Student.ResidencyStatus 
               if ( CompareAttributeToAttribute( mStuChgH, "StudentChangeHistory", "ResidencyStatus", mStudent, "Student", "ResidencyStatus" ) != 0 )
               { 
                  //:ACTIVATE mStuChgL EMPTY 
                  RESULT = ActivateEmptyObjectInstance( mStuChgL, "mStuChgL", mStudent, zSINGLE );
                  //:CREATE ENTITY mStuChgL.StudentChangeLog 
                  RESULT = CreateEntity( mStuChgL, "StudentChangeLog", zPOS_AFTER );
                  //:GetStringFromAttributeByContext( szOriginalValue, mStuChgH, "StudentChangeHistory", "ResidencyStatus", "", 50 )
                  {StringBuilder sb_szOriginalValue;
                  if ( szOriginalValue == null )
                     sb_szOriginalValue = new StringBuilder( 32 );
                  else
                     sb_szOriginalValue = new StringBuilder( szOriginalValue );
                                     GetStringFromAttributeByContext( sb_szOriginalValue, mStuChgH, "StudentChangeHistory", "ResidencyStatus", "", 50 );
                  szOriginalValue = sb_szOriginalValue.toString( );}
                  //:GetStringFromAttributeByContext( szNewValue,      mStudent, "Student", "ResidencyStatus", "", 50 )
                  {StringBuilder sb_szNewValue;
                  if ( szNewValue == null )
                     sb_szNewValue = new StringBuilder( 32 );
                  else
                     sb_szNewValue = new StringBuilder( szNewValue );
                                     GetStringFromAttributeByContext( sb_szNewValue, mStudent, "Student", "ResidencyStatus", "", 50 );
                  szNewValue = sb_szNewValue.toString( );}
                  //:mStuChgL.StudentChangeLog.AttributeName = "ResidencyStatus"
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "AttributeName", "ResidencyStatus" );
                  //:mStuChgL.StudentChangeLog.OriginalValue = szOriginalValue
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "OriginalValue", szOriginalValue );
                  //:mStuChgL.StudentChangeLog.NewValue      = szNewValue
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "NewValue", szNewValue );
                  //:mStuChgL.StudentChangeLog.UserName      = mUser.User.UserName 
                  SetAttributeFromAttribute( mStuChgL, "StudentChangeLog", "UserName", mUser, "User", "UserName" );
                  //:SetAttributeFromCurrentDateTime( mStuChgL, "StudentChangeLog", "ModificationDate" )
                  //:INCLUDE mStuChgL.Student FROM mStudent.Student
                  RESULT = IncludeSubobjectFromSubobject( mStuChgL, "Student", mStudent, "Student", zPOS_AFTER );
                  //:COMMIT mStuChgL
                  RESULT = CommitObjectInstance( mStuChgL );
                  //:DropObjectInstance( mStuChgL )
                  DropObjectInstance( mStuChgL );
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

               //:// Financial Hold Flag
               //:IF mStuChgH.StudentChangeHistory.FinancialHoldFlag != mStudent.Student.FinancialHoldFlag 
               if ( CompareAttributeToAttribute( mStuChgH, "StudentChangeHistory", "FinancialHoldFlag", mStudent, "Student", "FinancialHoldFlag" ) != 0 )
               { 
                  //:ACTIVATE mStuChgL EMPTY 
                  RESULT = ActivateEmptyObjectInstance( mStuChgL, "mStuChgL", mStudent, zSINGLE );
                  //:CREATE ENTITY mStuChgL.StudentChangeLog 
                  RESULT = CreateEntity( mStuChgL, "StudentChangeLog", zPOS_AFTER );
                  //:GetStringFromAttributeByContext( szOriginalValue, mStuChgH, "StudentChangeHistory", "FinancialHoldFlag", "", 50 )
                  {StringBuilder sb_szOriginalValue;
                  if ( szOriginalValue == null )
                     sb_szOriginalValue = new StringBuilder( 32 );
                  else
                     sb_szOriginalValue = new StringBuilder( szOriginalValue );
                                     GetStringFromAttributeByContext( sb_szOriginalValue, mStuChgH, "StudentChangeHistory", "FinancialHoldFlag", "", 50 );
                  szOriginalValue = sb_szOriginalValue.toString( );}
                  //:GetStringFromAttributeByContext( szNewValue,      mStudent, "Student", "FinancialHoldFlag", "", 50 )
                  {StringBuilder sb_szNewValue;
                  if ( szNewValue == null )
                     sb_szNewValue = new StringBuilder( 32 );
                  else
                     sb_szNewValue = new StringBuilder( szNewValue );
                                     GetStringFromAttributeByContext( sb_szNewValue, mStudent, "Student", "FinancialHoldFlag", "", 50 );
                  szNewValue = sb_szNewValue.toString( );}
                  //:mStuChgL.StudentChangeLog.AttributeName = "FinancialHoldFlag"
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "AttributeName", "FinancialHoldFlag" );
                  //:mStuChgL.StudentChangeLog.OriginalValue = szOriginalValue
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "OriginalValue", szOriginalValue );
                  //:mStuChgL.StudentChangeLog.NewValue      = szNewValue
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "NewValue", szNewValue );
                  //:mStuChgL.StudentChangeLog.UserName      = mUser.User.UserName 
                  SetAttributeFromAttribute( mStuChgL, "StudentChangeLog", "UserName", mUser, "User", "UserName" );
                  //:SetAttributeFromCurrentDateTime( mStuChgL, "StudentChangeLog", "ModificationDate" )
                  //:INCLUDE mStuChgL.Student FROM mStudent.Student
                  RESULT = IncludeSubobjectFromSubobject( mStuChgL, "Student", mStudent, "Student", zPOS_AFTER );
                  //:COMMIT mStuChgL
                  RESULT = CommitObjectInstance( mStuChgL );
                  //:DropObjectInstance( mStuChgL )
                  DropObjectInstance( mStuChgL );
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

               //:// Administrative Division
               //:IF mStuChgH.StudentChangeHistory.AdministrativeDivisionName != mStudent.AdministrativeDivision.Name 
               if ( CompareAttributeToAttribute( mStuChgH, "StudentChangeHistory", "AdministrativeDivisionName", mStudent, "AdministrativeDivision", "Name" ) != 0 )
               { 
                  //:ACTIVATE mStuChgL EMPTY 
                  RESULT = ActivateEmptyObjectInstance( mStuChgL, "mStuChgL", mStudent, zSINGLE );
                  //:CREATE ENTITY mStuChgL.StudentChangeLog 
                  RESULT = CreateEntity( mStuChgL, "StudentChangeLog", zPOS_AFTER );
                  //:mStuChgL.StudentChangeLog.AttributeName = "AdministrativeDivisionName"
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "AttributeName", "AdministrativeDivisionName" );
                  //:mStuChgL.StudentChangeLog.OriginalValue = mStuChgH.StudentChangeHistory.AdministrativeDivisionName
                  SetAttributeFromAttribute( mStuChgL, "StudentChangeLog", "OriginalValue", mStuChgH, "StudentChangeHistory", "AdministrativeDivisionName" );
                  //:mStuChgL.StudentChangeLog.NewValue      = mStudent.AdministrativeDivision.Name 
                  SetAttributeFromAttribute( mStuChgL, "StudentChangeLog", "NewValue", mStudent, "AdministrativeDivision", "Name" );
                  //:mStuChgL.StudentChangeLog.UserName      = mUser.User.UserName 
                  SetAttributeFromAttribute( mStuChgL, "StudentChangeLog", "UserName", mUser, "User", "UserName" );
                  //:SetAttributeFromCurrentDateTime( mStuChgL, "StudentChangeLog", "ModificationDate" )
                  //:INCLUDE mStuChgL.Student FROM mStudent.Student
                  RESULT = IncludeSubobjectFromSubobject( mStuChgL, "Student", mStudent, "Student", zPOS_AFTER );
                  //:COMMIT mStuChgL
                  RESULT = CommitObjectInstance( mStuChgL );
                  //:DropObjectInstance( mStuChgL )
                  DropObjectInstance( mStuChgL );
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

               //:// Anticipated Graduation Period
               //:IF mStuChgH.StudentChangeHistory.AnticipatedGradPeriod != szAnticipatedGradPeriod
               if ( CompareAttributeToString( mStuChgH, "StudentChangeHistory", "AnticipatedGradPeriod", szAnticipatedGradPeriod ) != 0 )
               { 
                  //:ACTIVATE mStuChgL EMPTY 
                  RESULT = ActivateEmptyObjectInstance( mStuChgL, "mStuChgL", mStudent, zSINGLE );
                  //:CREATE ENTITY mStuChgL.StudentChangeLog 
                  RESULT = CreateEntity( mStuChgL, "StudentChangeLog", zPOS_AFTER );
                  //:mStuChgL.StudentChangeLog.AttributeName = "AnticipatedGradPeriod"
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "AttributeName", "AnticipatedGradPeriod" );
                  //:mStuChgL.StudentChangeLog.OriginalValue = mStuChgH.StudentChangeHistory.AnticipatedGradPeriod
                  SetAttributeFromAttribute( mStuChgL, "StudentChangeLog", "OriginalValue", mStuChgH, "StudentChangeHistory", "AnticipatedGradPeriod" );
                  //:mStuChgL.StudentChangeLog.NewValue      = szAnticipatedGradPeriod
                  SetAttributeFromString( mStuChgL, "StudentChangeLog", "NewValue", szAnticipatedGradPeriod );
                  //:mStuChgL.StudentChangeLog.UserName      = mUser.User.UserName 
                  SetAttributeFromAttribute( mStuChgL, "StudentChangeLog", "UserName", mUser, "User", "UserName" );
                  //:SetAttributeFromCurrentDateTime( mStuChgL, "StudentChangeLog", "ModificationDate" )
                  //:INCLUDE mStuChgL.Student FROM mStudent.Student
                  RESULT = IncludeSubobjectFromSubobject( mStuChgL, "Student", mStudent, "Student", zPOS_AFTER );
                  //:COMMIT mStuChgL
                  RESULT = CommitObjectInstance( mStuChgL );
                  //:DropObjectInstance( mStuChgL )
                  DropObjectInstance( mStuChgL );
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
                  {MutableInt mi_lTempInteger_22 = new MutableInt( lTempInteger_22 );
                  StringBuilder sb_szCurrentDate;
                  if ( szCurrentDate == null )
                     sb_szCurrentDate = new StringBuilder( 32 );
                  else
                     sb_szCurrentDate = new StringBuilder( szCurrentDate );
                                     GetVariableFromAttribute( sb_szCurrentDate, mi_lTempInteger_22, 'S', 9, wXferO, "Root", "dCurrentDate", "", 0 );
                  lTempInteger_22 = mi_lTempInteger_22.intValue( );
                  szCurrentDate = sb_szCurrentDate.toString( );}
                  //:IF mStuChgH.StudentChangeHistory.FirstDate = szCurrentDate
                  if ( CompareAttributeToString( mStuChgH, "StudentChangeHistory", "FirstDate", szCurrentDate ) == 0 )
                  { 
                     //:// The entry was created today, so just update it.
                     //:// Note that we can just set all the attributes, without regard to which ones changed,
                     //:// since this entity is a record of attribute values as of this point in time.
                     //:mStuChgH.StudentChangeHistory.StudentStatus              = mStudent.Student.Status 
                     SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "StudentStatus", mStudent, "Student", "Status" );
                     //:mStuChgH.StudentChangeHistory.Classification             = mStudent.Student.CurrentLevel 
                     SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "Classification", mStudent, "Student", "CurrentLevel" );
                     //:mStuChgH.StudentChangeHistory.ResidencyStatus            = mStudent.Student.ResidencyStatus 
                     SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "ResidencyStatus", mStudent, "Student", "ResidencyStatus" );
                     //:mStuChgH.StudentChangeHistory.FinancialHoldFlag          = mStudent.Student.FinancialHoldFlag 
                     SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "FinancialHoldFlag", mStudent, "Student", "FinancialHoldFlag" );
                     //:mStuChgH.StudentChangeHistory.AdministrativeDivisionName = mStudent.AdministrativeDivision.Name 
                     SetAttributeFromAttribute( mStuChgH, "StudentChangeHistory", "AdministrativeDivisionName", mStudent, "AdministrativeDivision", "Name" );
                     //:mStuChgH.StudentChangeHistory.AnticipatedGradPeriod      = szAnticipatedGradPeriod
                     SetAttributeFromString( mStuChgH, "StudentChangeHistory", "AnticipatedGradPeriod", szAnticipatedGradPeriod );
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
                     RESULT = ActivateEmptyObjectInstance( mStuChgH2, "mStuChgH", mStudent, zSINGLE );
                     //:CREATE ENTITY mStuChgH2.StudentChangeHistory 
                     RESULT = CreateEntity( mStuChgH2, "StudentChangeHistory", zPOS_AFTER );
                     //:mStuChgH2.StudentChangeHistory.StudentStatus              = mStudent.Student.Status 
                     SetAttributeFromAttribute( mStuChgH2, "StudentChangeHistory", "StudentStatus", mStudent, "Student", "Status" );
                     //:mStuChgH2.StudentChangeHistory.Classification             = mStudent.Student.CurrentLevel 
                     SetAttributeFromAttribute( mStuChgH2, "StudentChangeHistory", "Classification", mStudent, "Student", "CurrentLevel" );
                     //:mStuChgH2.StudentChangeHistory.ResidencyStatus            = mStudent.Student.ResidencyStatus 
                     SetAttributeFromAttribute( mStuChgH2, "StudentChangeHistory", "ResidencyStatus", mStudent, "Student", "ResidencyStatus" );
                     //:mStuChgH2.StudentChangeHistory.FinancialHoldFlag          = mStudent.Student.FinancialHoldFlag 
                     SetAttributeFromAttribute( mStuChgH2, "StudentChangeHistory", "FinancialHoldFlag", mStudent, "Student", "FinancialHoldFlag" );
                     //:mStuChgH2.StudentChangeHistory.AdministrativeDivisionName = mStudent.AdministrativeDivision.Name 
                     SetAttributeFromAttribute( mStuChgH2, "StudentChangeHistory", "AdministrativeDivisionName", mStudent, "AdministrativeDivision", "Name" );
                     //:mStuChgH2.StudentChangeHistory.AnticipatedGradPeriod      = szAnticipatedGradPeriod
                     SetAttributeFromString( mStuChgH2, "StudentChangeHistory", "AnticipatedGradPeriod", szAnticipatedGradPeriod );
                     //:SetAttributeFromCurrentDateTime( mStuChgH2, "StudentChangeHistory", "FirstDate" )
                     //:INCLUDE mStuChgH2.Student FROM mStudent.Student
                     RESULT = IncludeSubobjectFromSubobject( mStuChgH2, "Student", mStudent, "Student", zPOS_AFTER );

                     //:// Set the LastDate attribute in the previous history entry to yesterday.
                     //:SetAttributeFromCurrentDateTime( wXferO, "Root", "WorkDate" )
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
*/

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
//:dAdvisorFullName( VIEW mStudent BASED ON LOD mStudent,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING ( 100 ) szFullName
public int 
omStudent_dAdvisorFullName( View     mStudent,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szFullName = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:GetPersonFullName( szFullName, mStudent, "AdvisorPerson" )
         //:StoreStringInRecord ( mStudent,
         //:                      InternalEntityStructure, InternalAttribStructure, szFullName )
         StoreStringInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, szFullName );
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
//:dLatestRoomAssignment( VIEW mStudent BASED ON LOD mStudent,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )
//:   
//:   STRING ( 100 ) szRoomAssignment
public int 
omStudent_dLatestRoomAssignment( View     mStudent,
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
         //:IF mStudent.TermOfResidence EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mStudent, "TermOfResidence" );
         if ( lTempInteger_0 == 0 )
         { 
            //:// Make sure residence is sorted in reverse date order.
            //:OrderEntityForView( mStudent, "TermOfResidence", "BeginDate D" )
            OrderEntityForView( mStudent, "TermOfResidence", "BeginDate D" );
            //:SET CURSOR FIRST mStudent.TermOfResidence 
            RESULT = mStudent.cursor( "TermOfResidence" ).setFirst().toInt();
            //:szRoomAssignment = mStudent.Building.Name + " Room " + mStudent.Room.Number 
            {StringBuilder sb_szRoomAssignment;
            if ( szRoomAssignment == null )
               sb_szRoomAssignment = new StringBuilder( 32 );
            else
               sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                         GetStringFromAttribute( sb_szRoomAssignment, mStudent, "Building", "Name" );
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
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 255, mStudent, "Room", "Number", "", 0 );
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

         //:StoreStringInRecord ( mStudent,
         //:                InternalEntityStructure, InternalAttribStructure, szRoomAssignment )
         StoreStringInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, szRoomAssignment );
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
//:dMajorGPA( VIEW mStudentOrig BASED ON LOD mStudent,
//:           STRING ( 32 ) InternalEntityStructure,
//:           STRING ( 32 ) InternalAttribStructure,
//:           SHORT GetOrSetFlag )

//:   VIEW mStudent BASED ON LOD mStudent
public int 
omStudent_dMajorGPA( View     mStudentOrig,
                     String InternalEntityStructure,
                     String InternalAttribStructure,
                     Integer   GetOrSetFlag )
{
   zVIEW    mStudent = new zVIEW( );
   //:VIEW lDegTrkM  BASED ON LOD lDegTrkM
   zVIEW    lDegTrkM = new zVIEW( );
   //:VIEW mStudenC  BASED ON LOD mStudenC
   zVIEW    mStudenC = new zVIEW( );
   //:VIEW mStudenCO BASED ON LOD mStudenC
   zVIEW    mStudenCO = new zVIEW( );
   //:DECIMAL MajorGPA
   double  MajorGPA = 0.0;
   //:DECIMAL GPA_Divisor
   double  GPA_Divisor = 0.0;
   //:DECIMAL GPA_Points
   double  GPA_Points = 0.0;
   //:DECIMAL FinalGrade
   double  FinalGrade = 0.0;
   //:DECIMAL CreditHours
   double  CreditHours = 0.0;
   //:INTEGER CourseID
   int      CourseID = 0;
   //:STRING ( 20 ) szDecimalString
   String   szDecimalString = null;
   //:STRING ( 2 )  szFinalGrade
   String   szFinalGrade = null;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// This is a calculation of the major GPA based on the list of Degree Track major courses and
         //:// the Student's academic record.
         //:// We try to use the derived DegreeTrack path in mStudent. If it's not there, we will activate
         //:// lDegTrkM and include it.

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         //:IF szEntityName = "StudentMajorDegreeTrack"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "StudentMajorDegreeTrack", 1, 0, 33 ) == 0 )
         { 
            //:// We're already positioned on the correct entity.
            //:mStudent = mStudentOrig
            SetViewFromView( mStudent, mStudentOrig );
            //:ELSE
         } 
         else
         { 
            //:// Position on the correct DegreeTrack entity.
            //:CreateViewFromView( mStudent, mStudentOrig )
            CreateViewFromView( mStudent, mStudentOrig );
            //:IF szEntityName = "mStudent.DC_StudentMajorDegreeTrack"
            if ( ZeidonStringCompare( szEntityName, 1, 0, "mStudent.DC_StudentMajorDegreeTrack", 1, 0, 33 ) == 0 )
            { 
               //:SET CURSOR FIRST mStudent.StudentMajorDegreeTrack WHERE mStudent.StudentMajorDegreeTrack.ID = mStudent.DC_StudentMajorDegreeTrack.ID
               {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                               GetIntegerFromAttribute( mi_lTempInteger_0, mStudent, "DC_StudentMajorDegreeTrack", "ID" );
               lTempInteger_0 = mi_lTempInteger_0.intValue( );}
               RESULT = mStudent.cursor( "StudentMajorDegreeTrack" ).setFirst( "ID", lTempInteger_0 ).toInt();

               //:ELSE
            } 
            else
            { 
               //:SET CURSOR FIRST mStudent.StudentMajorDegreeTrack WHERE mStudent.StudentMajorDegreeTrack.ID = mStudent.CDC_StudentMajorDegreeTrack.ID
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                               GetIntegerFromAttribute( mi_lTempInteger_1, mStudent, "CDC_StudentMajorDegreeTrack", "ID" );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );}
               RESULT = mStudent.cursor( "StudentMajorDegreeTrack" ).setFirst( "ID", lTempInteger_1 ).toInt();
            } 

            //:END
         } 

         //:END

         //:SET CURSOR FIRST mStudent.MajorGPA_DegreeTrack 
         //:        WHERE mStudent.MajorGPA_DegreeTrack.ID = mStudent.MajorDegreeTrack.ID 
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                   GetIntegerFromAttribute( mi_lTempInteger_2, mStudent, "MajorDegreeTrack", "ID" );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );}
         RESULT = mStudent.cursor( "MajorGPA_DegreeTrack" ).setFirst( "ID", lTempInteger_2 ).toInt();
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:ACTIVATE lDegTrkM WHERE lDegTrkM.DegreeTrack.ID = mStudent.MajorDegreeTrack.ID 
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                         GetIntegerFromAttribute( mi_lTempInteger_3, mStudent, "MajorDegreeTrack", "ID" );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );}
            omStudent_fnLocalBuildQual_1( mStudentOrig, vTempViewVar_0, lTempInteger_3 );
            RESULT = ActivateObjectInstance( lDegTrkM, "lDegTrkM", mStudentOrig, vTempViewVar_0, zSINGLE );
            DropView( vTempViewVar_0 );
            //:INCLUDE mStudent.MajorGPA_DegreeTrack FROM lDegTrkM.DegreeTrack 
            RESULT = IncludeSubobjectFromSubobject( mStudent, "MajorGPA_DegreeTrack", lDegTrkM, "DegreeTrack", zPOS_AFTER );
            //:DropObjectInstance( lDegTrkM )
            DropObjectInstance( lDegTrkM );
         } 

         //:END 
         //:MajorGPA = 0
         MajorGPA = 0;
         //:GET VIEW mStudenCO NAMED "mStudenC"
         RESULT = GetViewByName( mStudenCO, "mStudenC", mStudentOrig, zLEVEL_TASK );
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 
            //:CreateViewFromView( mStudenC, mStudenCO )
            CreateViewFromView( mStudenC, mStudenCO );
            //:GPA_Divisor = 0
            GPA_Divisor = 0;
            //:GPA_Points  = 0
            GPA_Points = 0;
            //:FOR EACH mStudenC.Registration WHERE ( mStudenC.Registration.Status = "C"    // Completed
            //:                                 OR mStudenC.Registration.Status = "F"    // Transferred
            //:                                 OR mStudenC.Registration.Status = "X" )  // L. Transferred
            //:                                AND mStudenC.Registration.wRepeatedClass != "R"
            RESULT = mStudenC.cursor( "Registration" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               if ( ( CompareAttributeToString( mStudenC, "Registration", "Status", "C" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "F" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "X" ) == 0 ) &&
                    CompareAttributeToString( mStudenC, "Registration", "wRepeatedClass", "R" ) != 0 )
               { 

                  //:IF mStudenC.RegistrationCourse EXISTS
                  lTempInteger_4 = CheckExistenceOfEntity( mStudenC, "RegistrationCourse" );
                  if ( lTempInteger_4 == 0 )
                  { 
                     //:CourseID = mStudenC.RegistrationCourse.ID 
                     {MutableInt mi_CourseID = new MutableInt( CourseID );
                                           GetIntegerFromAttribute( mi_CourseID, mStudenC, "RegistrationCourse", "ID" );
                     CourseID = mi_CourseID.intValue( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF mStudenC.EquivalentCourse EXISTS
                     lTempInteger_5 = CheckExistenceOfEntity( mStudenC, "EquivalentCourse" );
                     if ( lTempInteger_5 == 0 )
                     { 
                        //:CourseID = mStudenC.EquivalentCourse.ID 
                        {MutableInt mi_CourseID = new MutableInt( CourseID );
                                                 GetIntegerFromAttribute( mi_CourseID, mStudenC, "EquivalentCourse", "ID" );
                        CourseID = mi_CourseID.intValue( );}
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
                  //:SET CURSOR FIRST mStudent.MajorGPA_Course WHERE mStudent.MajorGPA_Course.ID = CourseID
                  RESULT = mStudent.cursor( "MajorGPA_Course" ).setFirst( "ID", CourseID ).toInt();
                  //:IF RESULT >= zCURSOR_SET 
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:szFinalGrade = mStudenC.Registration.FinalGrade
                     {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                     StringBuilder sb_szFinalGrade;
                     if ( szFinalGrade == null )
                        sb_szFinalGrade = new StringBuilder( 32 );
                     else
                        sb_szFinalGrade = new StringBuilder( szFinalGrade );
                                           GetVariableFromAttribute( sb_szFinalGrade, mi_lTempInteger_6, 'S', 3, mStudenC, "Registration", "FinalGrade", "", 0 );
                     lTempInteger_6 = mi_lTempInteger_6.intValue( );
                     szFinalGrade = sb_szFinalGrade.toString( );}
                     //:GetStringFromAttributeByContext( szDecimalString,
                     //:                           mStudenC, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 )
                     {StringBuilder sb_szDecimalString;
                     if ( szDecimalString == null )
                        sb_szDecimalString = new StringBuilder( 32 );
                     else
                        sb_szDecimalString = new StringBuilder( szDecimalString );
                                           GetStringFromAttributeByContext( sb_szDecimalString, mStudenC, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 );
                     szDecimalString = sb_szDecimalString.toString( );}
                     //:IF szFinalGrade = "F"
                     if ( ZeidonStringCompare( szFinalGrade, 1, 0, "F", 1, 0, 3 ) == 0 )
                     { 
                        //:// Failed Course
                        //:CreditHours = mStudenC.Registration.CreditHours
                        {MutableDouble md_CreditHours = new MutableDouble( CreditHours );
                                                 GetDecimalFromAttribute( md_CreditHours, mStudenC, "Registration", "CreditHours" );
                        CreditHours = md_CreditHours.doubleValue( );}
                        //:GPA_Divisor = GPA_Divisor + CreditHours
                        GPA_Divisor = GPA_Divisor + CreditHours;
                        //:// GPA_Points are not impacted for this case.
                        //:ELSE
                     } 
                     else
                     { 
                        //:FinalGrade = StrToDecimal( szDecimalString )
                        //:IF FinalGrade > 0
                        if ( FinalGrade > 0 )
                        { 
                           //:// Passed Course
                           //:// The only other entries considered are those with grades D through A, in which case FinalGrade is > 0.
                           //:CreditHours = mStudenC.Registration.CreditHours
                           {MutableDouble md_CreditHours = new MutableDouble( CreditHours );
                                                       GetDecimalFromAttribute( md_CreditHours, mStudenC, "Registration", "CreditHours" );
                           CreditHours = md_CreditHours.doubleValue( );}
                           //:GPA_Divisor = GPA_Divisor + CreditHours
                           GPA_Divisor = GPA_Divisor + CreditHours;
                           //:GPA_Points = GPA_Points + (FinalGrade * CreditHours)
                           GPA_Points = GPA_Points + ( FinalGrade * CreditHours );
                        } 

                        //:END
                     } 

                     //:END
                  } 

               } 

               RESULT = mStudenC.cursor( "Registration" ).setNext().toInt();
               //:END
            } 


            //:END
            //:MajorGPA = GPA_Points / GPA_Divisor
            MajorGPA = GPA_Points / GPA_Divisor;
            //:DropView( mStudenC )
            DropView( mStudenC );
         } 

         //:END
         //:IF mStudent != mStudentOrig
         if ( mStudent != mStudentOrig )
         { 
            //:DropView( mStudent )
            DropView( mStudent );
         } 

         //:END

         //:StoreValueInRecord ( mStudentOrig,
         //:                  InternalEntityStructure, InternalAttribStructure, MajorGPA, 0 )
         StoreValueInRecord( mStudentOrig, InternalEntityStructure, InternalAttribStructure, MajorGPA, 0 );
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
//:dTotalReqChapelsCurTrm( VIEW mStudent BASED ON LOD mStudent,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   VIEW lTermLST  BASED ON LOD lTermLST
public int 
omStudent_dTotalReqChapelsCurTrm( View     mStudent,
                                  String InternalEntityStructure,
                                  String InternalAttribStructure,
                                  Integer   GetOrSetFlag )
{
   zVIEW    lTermLST = new zVIEW( );
   //:VIEW lTermLST2 BASED ON LOD lTermLST
   zVIEW    lTermLST2 = new zVIEW( );
   //:INTEGER CurrentRequiredChapels
   int      CurrentRequiredChapels = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Get the number of Chapels Required for the current Term from the lTermLST object.
         //:IF mStudent.Student.NumberOfChapelsRequired != "" 
         if ( CompareAttributeToString( mStudent, "Student", "NumberOfChapelsRequired", "" ) != 0 )
         { 
            //:// The number of Chapels required is set specifically for the Student.
            //:CurrentRequiredChapels = mStudent.Student.NumberOfChapelsRequired 
            {MutableInt mi_CurrentRequiredChapels = new MutableInt( CurrentRequiredChapels );
                         GetIntegerFromAttribute( mi_CurrentRequiredChapels, mStudent, "Student", "NumberOfChapelsRequired" );
            CurrentRequiredChapels = mi_CurrentRequiredChapels.intValue( );}
            //:ELSE
         } 
         else
         { 
            //:// The number of Chapels required is from the value in the College Term.
            //:GET VIEW lTermLST NAMED "lTermLST"
            RESULT = GetViewByName( lTermLST, "lTermLST", mStudent, zLEVEL_TASK );
            //:IF RESULT >= 0
            if ( RESULT >= 0 )
            { 
               //:CreateViewFromView( lTermLST2, lTermLST )
               CreateViewFromView( lTermLST2, lTermLST );
               //:SET CURSOR FIRST lTermLST2.CollegeTerm WHERE lTermLST2.CollegeTerm.CurrentTermFlag = "Y" 
               RESULT = lTermLST2.cursor( "CollegeTerm" ).setFirst( "CurrentTermFlag", "Y" ).toInt();
               //:CurrentRequiredChapels = lTermLST2.CollegeTerm.NumberOfChapelsRequired 
               {MutableInt mi_CurrentRequiredChapels = new MutableInt( CurrentRequiredChapels );
                               GetIntegerFromAttribute( mi_CurrentRequiredChapels, lTermLST2, "CollegeTerm", "NumberOfChapelsRequired" );
               CurrentRequiredChapels = mi_CurrentRequiredChapels.intValue( );}
               //:DropView( lTermLST2 )
               DropView( lTermLST2 );
               //:ELSE
            } 
            else
            { 
               //:CurrentRequiredChapels = 0
               CurrentRequiredChapels = 0;
            } 

            //:END
         } 

         //:END

         //:StoreValueInRecord ( mStudent,
         //:                  InternalEntityStructure, InternalAttribStructure, CurrentRequiredChapels, 0 )
         StoreValueInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, CurrentRequiredChapels, 0 );
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
//:dNoChapelsAttCurTrm( VIEW mStudent BASED ON LOD mStudent,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   INTEGER CurrentAttendedChapels
public int 
omStudent_dNoChapelsAttCurTrm( View     mStudent,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   int      CurrentAttendedChapels = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Add up the number of Chapels attended for the current Term.
         //:CurrentAttendedChapels = 0
         CurrentAttendedChapels = 0;
         //:FOR EACH mStudent.ChapelSession
         RESULT = mStudent.cursor( "ChapelSession" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mStudent.CollegeTerm.CurrentTermFlag = "Y"
            if ( CompareAttributeToString( mStudent, "CollegeTerm", "CurrentTermFlag", "Y" ) == 0 )
            { 
               //:CurrentAttendedChapels = CurrentAttendedChapels + 1 
               CurrentAttendedChapels = CurrentAttendedChapels + 1;
            } 

            RESULT = mStudent.cursor( "ChapelSession" ).setNext().toInt();
            //:END
         } 

         //:END

         //:StoreValueInRecord ( mStudent,
         //:                  InternalEntityStructure, InternalAttribStructure, CurrentAttendedChapels, 0 )
         StoreValueInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, CurrentAttendedChapels, 0 );
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
//:dNoChapelsReqCurTrm( VIEW mStudent BASED ON LOD mStudent,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   INTEGER CurrentRequiredChapels
public int 
omStudent_dNoChapelsReqCurTrm( View     mStudent,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   int      CurrentRequiredChapels = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Compute the number of Chapels still required for the Student to attend during the current Term.
         //:CurrentRequiredChapels = mStudent.Student.dTotalChapelsRequiredCurrentTerm -
         //:                      mStudent.Student.dNoChapelsAttendedCurrentTerm 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mStudent, "Student", "dTotalChapelsRequiredCurrentTerm" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                   GetIntegerFromAttribute( mi_lTempInteger_1, mStudent, "Student", "dNoChapelsAttendedCurrentTerm" );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
         CurrentRequiredChapels = lTempInteger_0 - lTempInteger_1;
         //:IF CurrentRequiredChapels < 0
         if ( CurrentRequiredChapels < 0 )
         { 
            //:CurrentRequiredChapels = 0 
            CurrentRequiredChapels = 0;
         } 

         //:END
         //:StoreValueInRecord ( mStudent,
         //:                  InternalEntityStructure, InternalAttribStructure, CurrentRequiredChapels, 0 )
         StoreValueInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, CurrentRequiredChapels, 0 );
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
//:BuildMajorGPA_Report( VIEW mStudent BASED ON LOD mStudent )

//:   VIEW lDegTrkM  BASED ON LOD lDegTrkM
public int 
omStudent_BuildMajorGPA_Report( View     mStudent )
{
   zVIEW    lDegTrkM = new zVIEW( );
   //:VIEW mDegree   BASED ON LOD mDegree
   zVIEW    mDegree = new zVIEW( );
   //:VIEW mStudenC  BASED ON LOD mStudenC
   zVIEW    mStudenC = new zVIEW( );
   //:VIEW mStudenCO BASED ON LOD mStudenC
   zVIEW    mStudenCO = new zVIEW( );
   //:INTEGER CourseID
   int      CourseID = 0;
   //:STRING ( 2 )  szFinalGrade
   String   szFinalGrade = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;


   //:// Build the subobject for the Major GPA Report for the current StudentMajorDegreeTrack.
   //:// We try to use the derived DegreeTrack path in mStudent. If it's not there, we will activate
   //:// lDegTrkM and include it.

   //:SET CURSOR FIRST mStudent.MajorGPA_DegreeTrack 
   //:           WHERE mStudent.MajorGPA_DegreeTrack.ID = mStudent.MajorDegreeTrack.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mStudent, "MajorDegreeTrack", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   RESULT = mStudent.cursor( "MajorGPA_DegreeTrack" ).setFirst( "ID", lTempInteger_0 ).toInt();
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE lDegTrkM WHERE lDegTrkM.DegreeTrack.ID = mStudent.MajorDegreeTrack.ID 
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, mStudent, "MajorDegreeTrack", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      omStudent_fnLocalBuildQual_2( mStudent, vTempViewVar_0, lTempInteger_1 );
      RESULT = ActivateObjectInstance( lDegTrkM, "lDegTrkM", mStudent, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:INCLUDE mStudent.MajorGPA_DegreeTrack FROM lDegTrkM.DegreeTrack 
      RESULT = IncludeSubobjectFromSubobject( mStudent, "MajorGPA_DegreeTrack", lDegTrkM, "DegreeTrack", zPOS_AFTER );
      //:DropObjectInstance( lDegTrkM )
      DropObjectInstance( lDegTrkM );
   } 

   //:END 

   //:GET VIEW mStudenCO NAMED "mStudenC"
   RESULT = GetViewByName( mStudenCO, "mStudenC", mStudent, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:CreateViewFromView( mStudenC, mStudenCO )
      CreateViewFromView( mStudenC, mStudenCO );
      //:FOR EACH mStudenC.Registration WHERE ( mStudenC.Registration.Status = "C"    // Completed
      //:                                    OR mStudenC.Registration.Status = "F"    // Transferred
      //:                                    OR mStudenC.Registration.Status = "X" )  // L. Transferred
      //:                                 AND mStudenC.Registration.wRepeatedClass != "R"
      RESULT = mStudenC.cursor( "Registration" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         if ( ( CompareAttributeToString( mStudenC, "Registration", "Status", "C" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "F" ) == 0 || CompareAttributeToString( mStudenC, "Registration", "Status", "X" ) == 0 ) &&
              CompareAttributeToString( mStudenC, "Registration", "wRepeatedClass", "R" ) != 0 )
         { 

            //:IF mStudenC.RegistrationCourse EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mStudenC, "RegistrationCourse" );
            if ( lTempInteger_2 == 0 )
            { 
               //:CourseID = mStudenC.RegistrationCourse.ID 
               {MutableInt mi_CourseID = new MutableInt( CourseID );
                               GetIntegerFromAttribute( mi_CourseID, mStudenC, "RegistrationCourse", "ID" );
               CourseID = mi_CourseID.intValue( );}
               //:ELSE
            } 
            else
            { 
               //:IF mStudenC.EquivalentCourse EXISTS
               lTempInteger_3 = CheckExistenceOfEntity( mStudenC, "EquivalentCourse" );
               if ( lTempInteger_3 == 0 )
               { 
                  //:CourseID = mStudenC.EquivalentCourse.ID 
                  {MutableInt mi_CourseID = new MutableInt( CourseID );
                                     GetIntegerFromAttribute( mi_CourseID, mStudenC, "EquivalentCourse", "ID" );
                  CourseID = mi_CourseID.intValue( );}
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
            //:SET CURSOR FIRST mStudent.MajorGPA_Course WHERE mStudent.MajorGPA_Course.ID = CourseID
            RESULT = mStudent.cursor( "MajorGPA_Course" ).setFirst( "ID", CourseID ).toInt();
            //:IF RESULT >= zCURSOR_SET 
            if ( RESULT >= zCURSOR_SET )
            { 
               //:szFinalGrade = mStudenC.Registration.FinalGrade
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szFinalGrade;
               if ( szFinalGrade == null )
                  sb_szFinalGrade = new StringBuilder( 32 );
               else
                  sb_szFinalGrade = new StringBuilder( szFinalGrade );
                               GetVariableFromAttribute( sb_szFinalGrade, mi_lTempInteger_4, 'S', 3, mStudenC, "Registration", "FinalGrade", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szFinalGrade = sb_szFinalGrade.toString( );}
               //:mStudent.MajorGPA_Course.wFinalGrade = szFinalGrade
               SetAttributeFromString( mStudent, "MajorGPA_Course", "wFinalGrade", szFinalGrade );
            } 

         } 

         RESULT = mStudenC.cursor( "Registration" ).setNext().toInt();
         //:END
      } 

      //:END
      //:DropView( mStudenC )
      DropView( mStudenC );
   } 

   //:END

   //:// Position on correct DegreeYearData for display of Minimum Credits Required.
   //:SET CURSOR FIRST mStudent.DegreeYearData 
   //:           WHERE mStudent.DegreeYearData.FromYear    <= mStudent.MajorDegreeTrackCollegeYear.Year
   //:             AND mStudent.DegreeYearData.ThroughYear >= mStudent.MajorDegreeTrackCollegeYear.Year
   RESULT = mStudent.cursor( "DegreeYearData" ).setFirst().toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mStudent, "DegreeYearData", "FromYear", mStudent, "MajorDegreeTrackCollegeYear", "Year" ) > 0 ||
              CompareAttributeToAttribute( mStudent, "DegreeYearData", "ThroughYear", mStudent, "MajorDegreeTrackCollegeYear", "Year" ) < 0 ) )
      { 
         RESULT = mStudent.cursor( "DegreeYearData" ).setNext().toInt();
      } 

   } 

   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dHighSAT_Score( VIEW mStudent BASED ON LOD mStudent,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   DECIMAL dTotalComposite 
public int 
omStudent_dHighSAT_Score( View     mStudent,
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

         //:ComputeHighExamScoreSAT( dTotalComposite, mStudent )
        //:StoreValueInRecord ( mStudent,
         //:                     InternalEntityStructure, InternalAttribStructure, dTotalComposite, 0 )
         StoreValueInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, dTotalComposite, 0 );
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
//:dNewHighSAT_Score( VIEW mStudent BASED ON LOD mStudent,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   DECIMAL dTotalComposite 
public int 
omStudent_dNewHighSAT_Score( View     mStudent,
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

         //:ComputeNewHighExamScoreSAT( dTotalComposite, mStudent )
         //:StoreValueInRecord ( mStudent,
         //:                     InternalEntityStructure, InternalAttribStructure, dTotalComposite, 0 )
         StoreValueInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, dTotalComposite, 0 );
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
//:dCurrentRoomAssignment( VIEW mStudent BASED ON LOD mStudent,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )
//:   
//:   VIEW wXferO REGISTERED AS wXferO
public int 
omStudent_dCurrentRoomAssignment( View     mStudent,
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

   RESULT = GetViewByName( wXferO, "wXferO", mStudent, zLEVEL_TASK );

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
         //:IF mStudent.Student.ResidencyStatus = "R"
         if ( CompareAttributeToString( mStudent, "Student", "ResidencyStatus", "R" ) == 0 )
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
            //:SET CURSOR FIRST mStudent.TermOfResidence 
            //:     WHERE mStudent.TermOfResidence.BeginDate <= szCurrentDate
            //:       AND mStudent.TermOfResidence.EndDate   >= szCurrentDate 
            RESULT = mStudent.cursor( "TermOfResidence" ).setFirst().toInt();
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mStudent, "TermOfResidence", "BeginDate", szCurrentDate ) > 0 || CompareAttributeToString( mStudent, "TermOfResidence", "EndDate", szCurrentDate ) < 0 ) )
               { 
                  RESULT = mStudent.cursor( "TermOfResidence" ).setNext().toInt();
               } 

            } 

            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:szRoomAssignment = mStudent.Building.Name + " Room " + mStudent.Room.Number 
               {StringBuilder sb_szRoomAssignment;
               if ( szRoomAssignment == null )
                  sb_szRoomAssignment = new StringBuilder( 32 );
               else
                  sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                               GetStringFromAttribute( sb_szRoomAssignment, mStudent, "Building", "Name" );
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
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 255, mStudent, "Room", "Number", "", 0 );
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
               //:SET CURSOR FIRST mStudent.TermOfResidence WHERE mStudent.TermOfResidence.BeginDate > szCurrentDate
               RESULT = mStudent.cursor( "TermOfResidence" ).setFirst().toInt();
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mStudent, "TermOfResidence", "BeginDate", szCurrentDate ) <= 0 ) )
                  { 
                     RESULT = mStudent.cursor( "TermOfResidence" ).setNext().toInt();
                  } 

               } 

               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:szRoomAssignment = mStudent.Building.Name + " Room " + mStudent.Room.Number 
                  {StringBuilder sb_szRoomAssignment;
                  if ( szRoomAssignment == null )
                     sb_szRoomAssignment = new StringBuilder( 32 );
                  else
                     sb_szRoomAssignment = new StringBuilder( szRoomAssignment );
                                     GetStringFromAttribute( sb_szRoomAssignment, mStudent, "Building", "Name" );
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
                                     GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 255, mStudent, "Room", "Number", "", 0 );
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

         //:StoreStringInRecord ( mStudent,
         //:                InternalEntityStructure, InternalAttribStructure, szRoomAssignment )
         StoreStringInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, szRoomAssignment );
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
//:dProspectEntryDate( VIEW mStudent BASED ON LOD mStudent,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )
//:   
//:   STRING ( 4 )  szEntryYear
public int 
omStudent_dProspectEntryDate( View     mStudent,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szEntryYear = null;
   //:STRING ( 30 ) szEntryMonthYear
   String   szEntryMonthYear = null;
   //:STRING ( 20 ) szEntryMonth
   String   szEntryMonth = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Format the expected entry date and year into a combined format.
         //:szEntryYear = mStudent.Prospect.ExpectedEntryYear 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szEntryYear;
         if ( szEntryYear == null )
            sb_szEntryYear = new StringBuilder( 32 );
         else
            sb_szEntryYear = new StringBuilder( szEntryYear );
                   GetVariableFromAttribute( sb_szEntryYear, mi_lTempInteger_0, 'S', 5, mStudent, "Prospect", "ExpectedEntryYear", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szEntryYear = sb_szEntryYear.toString( );}
         //:szEntryMonth = mStudent.Prospect.ExpectedEntryTerm 
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szEntryMonth;
         if ( szEntryMonth == null )
            sb_szEntryMonth = new StringBuilder( 32 );
         else
            sb_szEntryMonth = new StringBuilder( szEntryMonth );
                   GetVariableFromAttribute( sb_szEntryMonth, mi_lTempInteger_1, 'S', 21, mStudent, "Prospect", "ExpectedEntryTerm", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szEntryMonth = sb_szEntryMonth.toString( );}
         //:szEntryMonthYear = szEntryMonth + " " + szEntryYear
          {StringBuilder sb_szEntryMonthYear;
         if ( szEntryMonthYear == null )
            sb_szEntryMonthYear = new StringBuilder( 32 );
         else
            sb_szEntryMonthYear = new StringBuilder( szEntryMonthYear );
                  ZeidonStringCopy( sb_szEntryMonthYear, 1, 0, szEntryMonth, 1, 0, 31 );
         szEntryMonthYear = sb_szEntryMonthYear.toString( );}
          {StringBuilder sb_szEntryMonthYear;
         if ( szEntryMonthYear == null )
            sb_szEntryMonthYear = new StringBuilder( 32 );
         else
            sb_szEntryMonthYear = new StringBuilder( szEntryMonthYear );
                  ZeidonStringConcat( sb_szEntryMonthYear, 1, 0, " ", 1, 0, 31 );
         szEntryMonthYear = sb_szEntryMonthYear.toString( );}
          {StringBuilder sb_szEntryMonthYear;
         if ( szEntryMonthYear == null )
            sb_szEntryMonthYear = new StringBuilder( 32 );
         else
            sb_szEntryMonthYear = new StringBuilder( szEntryMonthYear );
                  ZeidonStringConcat( sb_szEntryMonthYear, 1, 0, szEntryYear, 1, 0, 31 );
         szEntryMonthYear = sb_szEntryMonthYear.toString( );}

         //:StoreStringInRecord ( mStudent,
         //:                InternalEntityStructure, InternalAttribStructure, szEntryMonthYear )
         StoreStringInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, szEntryMonthYear );
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
//:dGradOrCompletionDate( VIEW mStudent BASED ON LOD mStudent,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   STRING ( 100 ) szDate
public int 
omStudent_dGradOrCompletionDate( View     mStudent,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   String   szDate = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mStudent.StudentMajorDegreeTrack.GraduationDate = ""
         if ( CompareAttributeToString( mStudent, "StudentMajorDegreeTrack", "GraduationDate", "" ) == 0 )
         { 
            //:szDate = mStudent.StudentMajorDegreeTrack.CertificateCompletionDate 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szDate;
            if ( szDate == null )
               sb_szDate = new StringBuilder( 32 );
            else
               sb_szDate = new StringBuilder( szDate );
                         GetVariableFromAttribute( sb_szDate, mi_lTempInteger_0, 'S', 101, mStudent, "StudentMajorDegreeTrack", "CertificateCompletionDate", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szDate = sb_szDate.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szDate = mStudent.StudentMajorDegreeTrack.GraduationDate 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szDate;
            if ( szDate == null )
               sb_szDate = new StringBuilder( 32 );
            else
               sb_szDate = new StringBuilder( szDate );
                         GetVariableFromAttribute( sb_szDate, mi_lTempInteger_1, 'S', 101, mStudent, "StudentMajorDegreeTrack", "GraduationDate", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szDate = sb_szDate.toString( );}
         } 

         //:END
         //:StoreStringInRecord ( mStudent,
         //:                      InternalEntityStructure, InternalAttribStructure, szDate )
         StoreStringInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, szDate );
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
//:dLastAuditDateTime( VIEW mStudent BASED ON LOD mStudent,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )
//:                    
//:   STRING ( 100 ) szDate  
public int 
omStudent_dLastAuditDateTime( View     mStudent,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szDate = null;
   //:VIEW  mStudenC BASED ON LOD mStudenC                 
   zVIEW    mStudenC = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GET VIEW mStudenC NAMED "mStudenC"
         RESULT = GetViewByName( mStudenC, "mStudenC", mStudent, zLEVEL_TASK );
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 

            //:SET CURSOR FIRST mStudenC.SavedDegreeAudit WITHIN mStudenC.Student
            RESULT = mStudenC.cursor( "SavedDegreeAudit" ).setFirst( "Student" ).toInt();

            //:// KJS 01/13/10 - There is an error when running degree audits (which will soon
            //:// be fixed where when the degree audit is run as an "Auto Group" the degree track is
            //:// not being created for the saveddegreeaudit.  To get around this, we will first check
            //:// if the most recent run degree audit has no degree track.  If not then use the
            //:// CreatedDateTime for this latest audit.  If not then use the latest saved audit that
            //:// has the correct degree track.
            //:IF mStudenC.DegreeTrack EXISTS 
            lTempInteger_0 = CheckExistenceOfEntity( mStudenC, "DegreeTrack" );
            if ( lTempInteger_0 == 0 )
            { 
               //:SET CURSOR FIRST mStudenC.DegreeTrack WITHIN mStudenC.Student
               //:        WHERE mStudenC.DegreeTrack.ID = mStudent.MajorDegreeTrack.ID
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                               GetIntegerFromAttribute( mi_lTempInteger_1, mStudent, "MajorDegreeTrack", "ID" );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );}
               RESULT = mStudenC.cursor( "DegreeTrack" ).setFirst( "ID", lTempInteger_1, "Student" ).toInt();
            } 

            //:END                    
            //:        
            //:            
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:szDate = mStudenC.SavedDegreeAudit.CreatedDateTime
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szDate;
               if ( szDate == null )
                  sb_szDate = new StringBuilder( 32 );
               else
                  sb_szDate = new StringBuilder( szDate );
               GetVariableFromAttribute( sb_szDate, mi_lTempInteger_2, 'S', 101, mStudenC, "SavedDegreeAudit", "CreatedDateTime", "", 0 );
               //GetVariableFromAttribute( sb_szDate, mi_lTempInteger_2, 'S', 101, mStudenC, "SavedDegreeAudit", "CreatedDateTime", "YYYYMMDDHHMMSSS", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szDate = sb_szDate.toString( );}
            } 
            TraceLineS( "*** !!!dLastAuditDateTime *** ", szDate );

            //:END
            //:StoreStringInRecord ( mStudent,
            //:                   InternalEntityStructure, InternalAttribStructure, szDate )
            StoreStringInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, szDate );
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
//:dCurrentCampusPhone( VIEW mStudent BASED ON LOD mStudent,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )
//:   VIEW wXferO REGISTERED AS wXferO
public int 
omStudent_dCurrentCampusPhone( View     mStudent,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 10 )  szCampusPhone
   String   szCampusPhone = null;
   //:STRING ( 8 )   szCurrentDate
   String   szCurrentDate = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mStudent, zLEVEL_TASK );

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Display the latest Dorm room information, if there is any.
         //:szCampusPhone = ""
          {StringBuilder sb_szCampusPhone;
         if ( szCampusPhone == null )
            sb_szCampusPhone = new StringBuilder( 32 );
         else
            sb_szCampusPhone = new StringBuilder( szCampusPhone );
                  ZeidonStringCopy( sb_szCampusPhone, 1, 0, "", 1, 0, 11 );
         szCampusPhone = sb_szCampusPhone.toString( );}
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
         //:SET CURSOR FIRST mStudent.TermOfResidence 
         //:     WHERE mStudent.TermOfResidence.BeginDate <= szCurrentDate
         //:       AND mStudent.TermOfResidence.EndDate   >= szCurrentDate 
         RESULT = mStudent.cursor( "TermOfResidence" ).setFirst().toInt();
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mStudent, "TermOfResidence", "BeginDate", szCurrentDate ) > 0 || CompareAttributeToString( mStudent, "TermOfResidence", "EndDate", szCurrentDate ) < 0 ) )
            { 
               RESULT = mStudent.cursor( "TermOfResidence" ).setNext().toInt();
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:szCampusPhone = mStudent.OccupancyUnit.PhoneNo  
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szCampusPhone;
            if ( szCampusPhone == null )
               sb_szCampusPhone = new StringBuilder( 32 );
            else
               sb_szCampusPhone = new StringBuilder( szCampusPhone );
                         GetVariableFromAttribute( sb_szCampusPhone, mi_lTempInteger_1, 'S', 11, mStudent, "OccupancyUnit", "PhoneNo", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szCampusPhone = sb_szCampusPhone.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mStudent,
         //:                InternalEntityStructure, InternalAttribStructure, szCampusPhone )
         StoreStringInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, szCampusPhone );
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
//:dActualAntGradDateName( VIEW mStudent BASED ON LOD mStudent,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   VIEW mStudentT BASED ON LOD mStudent
public int 
omStudent_dActualAntGradDateName( View     mStudent,
                                  String InternalEntityStructure,
                                  String InternalAttribStructure,
                                  Integer   GetOrSetFlag )
{
   zVIEW    mStudentT = new zVIEW( );
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:STRING ( 50 )  szGradDateName
   String   szGradDateName = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      RESULT = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         //:IF szEntityName = "DegreeCandidacy"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "DegreeCandidacy", 1, 0, 33 ) == 0 )
         { 
            //:// Derived attribute in on DegreeCandidacy.
            //:IF mStudent.DC_GraduationDate EXISTS
            lTempInteger_0 = CheckExistenceOfEntity( mStudent, "DC_GraduationDate" );
            if ( lTempInteger_0 == 0 )
            { 
               //:szGradDateName = mStudent.DC_GraduationDate.Name 
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_szGradDateName;
               if ( szGradDateName == null )
                  sb_szGradDateName = new StringBuilder( 32 );
               else
                  sb_szGradDateName = new StringBuilder( szGradDateName );
                               GetVariableFromAttribute( sb_szGradDateName, mi_lTempInteger_1, 'S', 51, mStudent, "DC_GraduationDate", "Name", "", 0 );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );
               szGradDateName = sb_szGradDateName.toString( );}
               //:ELSE
            } 
            else
            { 
               //:IF mStudent.DC_AnticipatedGraduationDate EXISTS
               lTempInteger_2 = CheckExistenceOfEntity( mStudent, "DC_AnticipatedGraduationDate" );
               if ( lTempInteger_2 == 0 )
               { 
                  //:szGradDateName = mStudent.DC_AnticipatedGraduationDate.Name 
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szGradDateName;
                  if ( szGradDateName == null )
                     sb_szGradDateName = new StringBuilder( 32 );
                  else
                     sb_szGradDateName = new StringBuilder( szGradDateName );
                                     GetVariableFromAttribute( sb_szGradDateName, mi_lTempInteger_3, 'S', 51, mStudent, "DC_AnticipatedGraduationDate", "Name", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szGradDateName = sb_szGradDateName.toString( );}
               } 

               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:// Derived attribute in on mStudent.StudentMajorDegreeTrack.
            //:CreateViewFromView( mStudentT, mStudent )
            CreateViewFromView( mStudentT, mStudent );
            //:SET CURSOR FIRST mStudentT.DegreeCandidacy WHERE mStudentT.DegreeCandidacy.ID = mStudentT.MajorTrackDegreeCandidacy.ID 
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                         GetIntegerFromAttribute( mi_lTempInteger_4, mStudentT, "MajorTrackDegreeCandidacy", "ID" );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );}
            RESULT = mStudentT.cursor( "DegreeCandidacy" ).setFirst( "ID", lTempInteger_4 ).toInt();
            //:IF mStudentT.DC_GraduationDate EXISTS
            lTempInteger_5 = CheckExistenceOfEntity( mStudentT, "DC_GraduationDate" );
            if ( lTempInteger_5 == 0 )
            { 
               //:szGradDateName = mStudentT.DC_GraduationDate.Name 
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
               StringBuilder sb_szGradDateName;
               if ( szGradDateName == null )
                  sb_szGradDateName = new StringBuilder( 32 );
               else
                  sb_szGradDateName = new StringBuilder( szGradDateName );
                               GetVariableFromAttribute( sb_szGradDateName, mi_lTempInteger_6, 'S', 51, mStudentT, "DC_GraduationDate", "Name", "", 0 );
               lTempInteger_6 = mi_lTempInteger_6.intValue( );
               szGradDateName = sb_szGradDateName.toString( );}
               //:ELSE
            } 
            else
            { 
               //:IF mStudentT.DC_AnticipatedGraduationDate EXISTS
               lTempInteger_7 = CheckExistenceOfEntity( mStudentT, "DC_AnticipatedGraduationDate" );
               if ( lTempInteger_7 == 0 )
               { 
                  //:szGradDateName = mStudentT.DC_AnticipatedGraduationDate.Name 
                  {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                  StringBuilder sb_szGradDateName;
                  if ( szGradDateName == null )
                     sb_szGradDateName = new StringBuilder( 32 );
                  else
                     sb_szGradDateName = new StringBuilder( szGradDateName );
                                     GetVariableFromAttribute( sb_szGradDateName, mi_lTempInteger_8, 'S', 51, mStudentT, "DC_AnticipatedGraduationDate", "Name", "", 0 );
                  lTempInteger_8 = mi_lTempInteger_8.intValue( );
                  szGradDateName = sb_szGradDateName.toString( );}
               } 

               //:END
            } 

            //:END
            //:DropView( mStudentT )
            DropView( mStudentT );
         } 

         //:END

         //:StoreStringInRecord ( mStudent,
         //:                   InternalEntityStructure, InternalAttribStructure, szGradDateName )
         StoreStringInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, szGradDateName );
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
//:BuildGradInfoRels( VIEW mStudent BASED ON LOD mStudent )

//:   VIEW mStudentT BASED ON LOD mStudent 
public int 
omStudent_BuildGradInfoRels( View     mStudent )
{
   zVIEW    mStudentT = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:// If the StudentMajorDegreeTrack to DegreeCandidacy relationships have NOT been built, build
   //:// them here for each StudentMajorDegreeTrack that does not have a GraduationDate already set.
   //:CreateViewFromView( mStudentT, mStudent )
   CreateViewFromView( mStudentT, mStudent );
   //:FOR EACH mStudentT.StudentMajorDegreeTrack 
   RESULT = mStudentT.cursor( "StudentMajorDegreeTrack" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mStudentT.MajorTrackDegreeCandidacy DOES NOT EXIST AND
      lTempInteger_0 = CheckExistenceOfEntity( mStudentT, "MajorTrackDegreeCandidacy" );
      //:   mStudentT.StudentMajorDegreeTrack.GraduationDate = "" 
      if ( lTempInteger_0 != 0 && CompareAttributeToString( mStudentT, "StudentMajorDegreeTrack", "GraduationDate", "" ) == 0 )
      { 

         //:INCLUDE mStudentT.SMDT_DegreeCandidacy      FROM mStudentT.DegreeCandidacy 
         RESULT = IncludeSubobjectFromSubobject( mStudentT, "SMDT_DegreeCandidacy", mStudentT, "DegreeCandidacy", zPOS_AFTER );
         //:INCLUDE mStudentT.MajorTrackDegreeCandidacy FROM mStudentT.DegreeCandidacy 
         RESULT = IncludeSubobjectFromSubobject( mStudentT, "MajorTrackDegreeCandidacy", mStudentT, "DegreeCandidacy", zPOS_AFTER );
      } 

      RESULT = mStudentT.cursor( "StudentMajorDegreeTrack" ).setNext().toInt();
      //:END
   } 

   //:END
   //:DropView( mStudentT )
   DropView( mStudentT );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dTotalStudReqChapels( VIEW mStudent BASED ON LOD mStudent,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )

//:   INTEGER nChapelsRequired
public int 
omStudent_dTotalStudReqChapels( View     mStudent,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   int      nChapelsRequired = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:TraceLineS("*** mStudent Checking Chapels Required ***", "")
         TraceLineS( "*** mStudent Checking Chapels Required ***", "" );
         //:IF mStudent.ChapelAttendanceByTerm.TotalChapelsRequiredOverride != ""
         if ( CompareAttributeToString( mStudent, "ChapelAttendanceByTerm", "TotalChapelsRequiredOverride", "" ) != 0 )
         { 
            //:TraceLineS("*** mStudent Yes, override ***", "")
            TraceLineS( "*** mStudent Yes, override ***", "" );
            //:nChapelsRequired = mStudent.ChapelAttendanceByTerm.TotalChapelsRequiredOverride 
            {MutableInt mi_nChapelsRequired = new MutableInt( nChapelsRequired );
                         GetIntegerFromAttribute( mi_nChapelsRequired, mStudent, "ChapelAttendanceByTerm", "TotalChapelsRequiredOverride" );
            nChapelsRequired = mi_nChapelsRequired.intValue( );}
            //:ELSE
         } 
         else
         { 
            //:TraceLineS("*** mStudent No, override ***", "")
            TraceLineS( "*** mStudent No, override ***", "" );
            //:nChapelsRequired = mStudent.ChapelAttendanceByTerm.TotalChapelsRequired  
            {MutableInt mi_nChapelsRequired = new MutableInt( nChapelsRequired );
                         GetIntegerFromAttribute( mi_nChapelsRequired, mStudent, "ChapelAttendanceByTerm", "TotalChapelsRequired" );
            nChapelsRequired = mi_nChapelsRequired.intValue( );}
         } 

         //:END

         //:StoreValueInRecord ( mStudent,
         //:                     InternalEntityStructure, InternalAttribStructure, nChapelsRequired, 0 )
         StoreValueInRecord( mStudent, InternalEntityStructure, InternalAttribStructure, nChapelsRequired, 0 );
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
