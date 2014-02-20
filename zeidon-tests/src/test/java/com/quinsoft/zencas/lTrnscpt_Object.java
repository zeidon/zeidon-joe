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

public class lTrnscpt_Object extends VmlObjectOperations
{
   public lTrnscpt_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dGradePointAverage( VIEW lTrnscpt BASED ON LOD lTrnscpt,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )
//:   
//:   VIEW lTrnscptT BASED ON LOD lTrnscpt
public int 
olTrnscpt_dGradePointAverage( View     lTrnscpt,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   zVIEW    lTrnscptT = new zVIEW( );
   //:DECIMAL dGPA
   double  dGPA = 0.0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView( lTrnscptT, lTrnscpt )
         CreateViewFromView( lTrnscptT, lTrnscpt );
         //:SET CURSOR LAST lTrnscptT.PrintGroup 
         //:       WHERE lTrnscptT.PrintGroup.CumulativeEarnedCredits != ""
         RESULT = lTrnscptT.cursor( "PrintGroup" ).setLast().toInt();;
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( lTrnscptT, "PrintGroup", "CumulativeEarnedCredits", "" ) == 0 ) )
            { 
               RESULT = lTrnscptT.cursor( "PrintGroup" ).setPrevContinue( ).toInt();
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:dGPA = lTrnscptT.PrintGroup.CumulativeGPA
            {MutableDouble md_dGPA = new MutableDouble( dGPA );
                         GetDecimalFromAttribute( md_dGPA, lTrnscptT, "PrintGroup", "CumulativeGPA" );
            dGPA = md_dGPA.doubleValue( );}
            //:StoreValueInRecord ( lTrnscpt,
            //:                  InternalEntityStructure, InternalAttribStructure, dGPA, 0 )
            StoreValueInRecord( lTrnscpt, InternalEntityStructure, InternalAttribStructure, dGPA, 0 );
            //:ELSE
         } 
         else
         { 
            //:StoreStringInRecord ( lTrnscpt,
            //:                   InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( lTrnscpt, InternalEntityStructure, InternalAttribStructure, "" );
         } 

         //:END
         //:DropView( lTrnscptT )
         DropView( lTrnscptT );
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
//:dAddressLabelFull( VIEW lTrnscpt BASED ON LOD lTrnscpt,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   SHORT nRC
public int 
olTrnscpt_dAddressLabelFull( View     lTrnscpt,
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

         //:nRC = dAdressLabelFull( lTrnscpt /* vAnyObject */,
         //:   InternalEntityStructure /* szInternalEntityStructure */,
         //:   InternalAttribStructure /* szInternalAttribStructure */,
         //:   GetOrSetFlag /* nGetOrSetFlag */ )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( lTrnscpt );
          nRC = m_ZGLOBAL1_Operation.dAdressLabelFull( lTrnscpt, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
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
//:dEarnedCredits( VIEW lTrnscpt BASED ON LOD lTrnscpt,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   VIEW lTrnscptT BASED ON LOD lTrnscpt
public int 
olTrnscpt_dEarnedCredits( View     lTrnscpt,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   zVIEW    lTrnscptT = new zVIEW( );
   //:DECIMAL TotalCredits
   double  TotalCredits = 0.0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView( lTrnscptT, lTrnscpt )
         CreateViewFromView( lTrnscptT, lTrnscpt );
         //:SET CURSOR LAST lTrnscptT.PrintGroup 
         //:       WHERE lTrnscptT.PrintGroup.CumulativeEarnedCredits != ""
         RESULT = lTrnscptT.cursor( "PrintGroup" ).setLast().toInt();;
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( lTrnscptT, "PrintGroup", "CumulativeEarnedCredits", "" ) == 0 ) )
            { 
               RESULT = lTrnscptT.cursor( "PrintGroup" ).setPrevContinue( ).toInt();
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:TotalCredits = lTrnscptT.PrintGroup.CumulativeEarnedCredits  
            {MutableDouble md_TotalCredits = new MutableDouble( TotalCredits );
                         GetDecimalFromAttribute( md_TotalCredits, lTrnscptT, "PrintGroup", "CumulativeEarnedCredits" );
            TotalCredits = md_TotalCredits.doubleValue( );}
            //:ELSE
         } 
         else
         { 
            //:TotalCredits = 0
            TotalCredits = 0;
         } 

         //:END
         //:DropView( lTrnscptT )
         DropView( lTrnscptT );
         //:StoreValueInRecord ( lTrnscpt,
         //:                  InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 )
         StoreValueInRecord( lTrnscpt, InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 );
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
//:dAttemptedCredits( VIEW lTrnscpt BASED ON LOD lTrnscpt,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   VIEW lTrnscptT BASED ON LOD lTrnscpt
public int 
olTrnscpt_dAttemptedCredits( View     lTrnscpt,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   zVIEW    lTrnscptT = new zVIEW( );
   //:DECIMAL TotalCredits
   double  TotalCredits = 0.0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView( lTrnscptT, lTrnscpt )
         CreateViewFromView( lTrnscptT, lTrnscpt );
         //:SET CURSOR LAST lTrnscptT.PrintGroup 
         //:       WHERE lTrnscptT.PrintGroup.CumulativeAttemptedCredits != ""
         RESULT = lTrnscptT.cursor( "PrintGroup" ).setLast().toInt();;
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( lTrnscptT, "PrintGroup", "CumulativeAttemptedCredits", "" ) == 0 ) )
            { 
               RESULT = lTrnscptT.cursor( "PrintGroup" ).setPrevContinue( ).toInt();
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:TotalCredits = lTrnscptT.PrintGroup.CumulativeAttemptedCredits  
            {MutableDouble md_TotalCredits = new MutableDouble( TotalCredits );
                         GetDecimalFromAttribute( md_TotalCredits, lTrnscptT, "PrintGroup", "CumulativeAttemptedCredits" );
            TotalCredits = md_TotalCredits.doubleValue( );}
            //:ELSE
         } 
         else
         { 
            //:TotalCredits = 0
            TotalCredits = 0;
         } 

         //:END
         //:DropView( lTrnscptT )
         DropView( lTrnscptT );
         //:StoreValueInRecord ( lTrnscpt,
         //:                  InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 )
         StoreValueInRecord( lTrnscpt, InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 );
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
//:dDivisorCredits( VIEW lTrnscpt BASED ON LOD lTrnscpt,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )
//:   
//:   VIEW lTrnscptT BASED ON LOD lTrnscpt
public int 
olTrnscpt_dDivisorCredits( View     lTrnscpt,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   zVIEW    lTrnscptT = new zVIEW( );
   //:DECIMAL TotalCredits
   double  TotalCredits = 0.0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView( lTrnscptT, lTrnscpt )
         CreateViewFromView( lTrnscptT, lTrnscpt );
         //:SET CURSOR LAST lTrnscptT.PrintGroup 
         //:       WHERE lTrnscptT.PrintGroup.CumulativeEarnedCredits != ""
         RESULT = lTrnscptT.cursor( "PrintGroup" ).setLast().toInt();;
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( lTrnscptT, "PrintGroup", "CumulativeEarnedCredits", "" ) == 0 ) )
            { 
               RESULT = lTrnscptT.cursor( "PrintGroup" ).setPrevContinue( ).toInt();
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:TotalCredits = lTrnscptT.PrintGroup.CumulativeGPA_Divisor 
            {MutableDouble md_TotalCredits = new MutableDouble( TotalCredits );
                         GetDecimalFromAttribute( md_TotalCredits, lTrnscptT, "PrintGroup", "CumulativeGPA_Divisor" );
            TotalCredits = md_TotalCredits.doubleValue( );}
            //:ELSE
         } 
         else
         { 
            //:TotalCredits = 0
            TotalCredits = 0;
         } 

         //:END
         //:DropView( lTrnscptT )
         DropView( lTrnscptT );
         //:StoreValueInRecord ( lTrnscpt,
         //:                  InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 )
         StoreValueInRecord( lTrnscpt, InternalEntityStructure, InternalAttribStructure, TotalCredits, 0 );
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
//:ObjectConstraints( VIEW lTrnscpt BASED ON LOD lTrnscpt,
//:                   SHORT Event,
//:                   SHORT State )
//:   
//:   VIEW lTrnscptT   BASED ON LOD lTrnscpt
public int 
olTrnscpt_ObjectConstraints( View     lTrnscpt,
                             Integer   Event,
                             Integer   State )
{
   zVIEW    lTrnscptT = new zVIEW( );
   //:VIEW lTermLST    BASED ON LOD mTerm
   zVIEW    lTermLST = new zVIEW( );
   //:VIEW mCollegeLST BASED ON LOD mCollege
   zVIEW    mCollegeLST = new zVIEW( );
   //:DECIMAL EarlierGrade
   double  EarlierGrade = 0.0;
   //:DECIMAL LaterGrade
   double  LaterGrade = 0.0;
   //:STRING ( 20 ) szDecimalString
   String   szDecimalString = null;
   //:STRING ( 4 )  szYear
   String   szYear = null;
   //:INTEGER CourseID
   int      CourseID = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   int      lTempInteger_12 = 0;


   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:// We need to be sure that the College list exists as we need it below.
         //:GET VIEW mCollegeLST NAMED "mCollegeLST"
         RESULT = GetViewByName( mCollegeLST, "mCollegeLST", lTrnscpt, zLEVEL_TASK );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:ACTIVATE mCollegeLST Multiple
            RESULT = ActivateObjectInstance( mCollegeLST, "mCollege", lTrnscpt, 0, zMULTIPLE );
            //:NAME VIEW mCollegeLST "mCollegeLST"
            SetNameForView( mCollegeLST, "mCollegeLST", null, zLEVEL_TASK );
         } 

         //:END
         //:/*
         //:IF RESULT < 0
         //:MessageSend( lTrnscpt, "", "Activate Transcript", 
         //:          "The mCollegeLST object needs to exist to process a Transcript.", 
         //:          zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         //:RETURN -1
         //:END
         //:*/

         //:FOR EACH lTrnscpt.Student
         RESULT = lTrnscpt.cursor( "Student" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 

            //:// Order any "Completed" Registration entries by College Term, which is necessary to handle complex
            //:// "repeated" entries correctly.
            //:FOR EACH lTrnscpt.Registration WITHIN lTrnscpt.Student
            //:                 WHERE lTrnscpt.Registration.Status = "C"    // Completed
            //:                    OR lTrnscpt.Registration.Status = "F"    // Transferred
            //:                    OR lTrnscpt.Registration.Status = "X"    // L. Transferred
            //:                    OR lTrnscpt.Registration.Status = "W"    // Withdrawn
            //:                    OR lTrnscpt.Registration.Status = "T"    // Taking (Enrolled) for Midterm Grades
            RESULT = lTrnscpt.cursor( "Registration" ).setFirst( "Student" ).toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               if ( CompareAttributeToString( lTrnscpt, "Registration", "Status", "C" ) == 0 || CompareAttributeToString( lTrnscpt, "Registration", "Status", "F" ) == 0 || CompareAttributeToString( lTrnscpt, "Registration", "Status", "X" ) == 0 ||
                    CompareAttributeToString( lTrnscpt, "Registration", "Status", "W" ) == 0 || CompareAttributeToString( lTrnscpt, "Registration", "Status", "T" ) == 0 )
               { 
                  //:IF lTrnscpt.RegistrationClass EXISTS
                  lTempInteger_0 = CheckExistenceOfEntity( lTrnscpt, "RegistrationClass" );
                  if ( lTempInteger_0 == 0 )
                  { 
                     //:IF lTrnscpt.RegistrationCohort EXISTS
                     lTempInteger_1 = CheckExistenceOfEntity( lTrnscpt, "RegistrationCohort" );
                     if ( lTempInteger_1 == 0 )
                     { 
                        //:GetStringFromAttributeByContext( szYear, lTrnscpt, "RegistrationClass", "ClassEndDate", "YYYY", 4 )
                        {StringBuilder sb_szYear;
                        if ( szYear == null )
                           sb_szYear = new StringBuilder( 32 );
                        else
                           sb_szYear = new StringBuilder( szYear );
                                                 GetStringFromAttributeByContext( sb_szYear, lTrnscpt, "RegistrationClass", "ClassEndDate", "YYYY", 4 );
                        szYear = sb_szYear.toString( );}
                        //:lTrnscpt.Registration.wCompletedClassYear     = szYear
                        SetAttributeFromString( lTrnscpt, "Registration", "wCompletedClassYear", szYear );
                        //:lTrnscpt.Registration.wCompletedClassSemester = ""
                        SetAttributeFromString( lTrnscpt, "Registration", "wCompletedClassSemester", "" );
                        //:lTrnscpt.Registration.wCohortClassEndDate     = lTrnscpt.RegistrationCohort.EndDate 
                        SetAttributeFromAttribute( lTrnscpt, "Registration", "wCohortClassEndDate", lTrnscpt, "RegistrationCohort", "EndDate" );
                        //:ELSE
                     } 
                     else
                     { 
                        //:lTrnscpt.Registration.wCompletedClassYear     = lTrnscpt.RegistrationCollegeYear.Year 
                        SetAttributeFromAttribute( lTrnscpt, "Registration", "wCompletedClassYear", lTrnscpt, "RegistrationCollegeYear", "Year" );
                        //:lTrnscpt.Registration.wCompletedClassSemester = lTrnscpt.RegistrationClassCollegeTerm.Semester
                        SetAttributeFromAttribute( lTrnscpt, "Registration", "wCompletedClassSemester", lTrnscpt, "RegistrationClassCollegeTerm", "Semester" );
                        //:lTrnscpt.Registration.wCohortClassEndDate     = ""
                        SetAttributeFromString( lTrnscpt, "Registration", "wCohortClassEndDate", "" );
                     } 

                     //:END
                     //:lTrnscpt.Registration.wSortOrder = "1"  // Regular entries are sorted before Transfer entries.
                     SetAttributeFromString( lTrnscpt, "Registration", "wSortOrder", "1" );
                     //:IF lTrnscpt.CrossListedCourse EXISTS
                     lTempInteger_2 = CheckExistenceOfEntity( lTrnscpt, "CrossListedCourse" );
                     if ( lTempInteger_2 == 0 )
                     { 
                        //:lTrnscpt.Registration.wCourseID                      = lTrnscpt.CrossListedCourse.ID
                        SetAttributeFromAttribute( lTrnscpt, "Registration", "wCourseID", lTrnscpt, "CrossListedCourse", "ID" );
                        //:lTrnscpt.Registration.wCourseNumber                  = lTrnscpt.CrossListedCourse.Number 
                        SetAttributeFromAttribute( lTrnscpt, "Registration", "wCourseNumber", lTrnscpt, "CrossListedCourse", "Number" );
                        //:lTrnscpt.Registration.wCourseTitle                   = lTrnscpt.CrossListedCourse.Title 
                        SetAttributeFromAttribute( lTrnscpt, "Registration", "wCourseTitle", lTrnscpt, "CrossListedCourse", "Title" );
                        //:lTrnscpt.Registration.wCourseRepeatableForCreditFlag = lTrnscpt.CrossListedCourse.RepeatableForCreditFlag 
                        SetAttributeFromAttribute( lTrnscpt, "Registration", "wCourseRepeatableForCreditFlag", lTrnscpt, "CrossListedCourse", "RepeatableForCreditFlag" );
                        //:// If the CrossListedCourse college is different from the Registered college AND the override flag for grad/undergrad credit is not set,
                        //:// then set it from the CrossListedCourse college. This will make sure the course appears on the proper grad/undergrad transcript.
                        //:SET CURSOR FIRST mCollegeLST.College WHERE mCollegeLST.College.ID = lTrnscpt.CrossListedCourseCollege.ID 
                        {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                                                 GetIntegerFromAttribute( mi_lTempInteger_3, lTrnscpt, "CrossListedCourseCollege", "ID" );
                        lTempInteger_3 = mi_lTempInteger_3.intValue( );}
                        RESULT = mCollegeLST.cursor( "College" ).setFirst( "ID", lTempInteger_3 ).toInt();
                        //:IF lTrnscpt.Registration.GradUndergradOverrideFlag = "" 
                        if ( CompareAttributeToString( lTrnscpt, "Registration", "GradUndergradOverrideFlag", "" ) == 0 )
                        { 
                           //:lTrnscpt.Registration.GradUndergradOverrideFlag = mCollegeLST.College.Type 
                           SetAttributeFromAttribute( lTrnscpt, "Registration", "GradUndergradOverrideFlag", mCollegeLST, "College", "Type" );
                        } 

                        //:END
                        //:ELSE
                     } 
                     else
                     { 
                        //:lTrnscpt.Registration.wCourseID                      = lTrnscpt.RegistrationCourse.ID
                        SetAttributeFromAttribute( lTrnscpt, "Registration", "wCourseID", lTrnscpt, "RegistrationCourse", "ID" );
                        //:lTrnscpt.Registration.wCourseTitle                   = lTrnscpt.RegistrationCourse.Title 
                        SetAttributeFromAttribute( lTrnscpt, "Registration", "wCourseTitle", lTrnscpt, "RegistrationCourse", "Title" );
                        //:lTrnscpt.Registration.wCourseRepeatableForCreditFlag = lTrnscpt.RegistrationCourse.RepeatableForCreditFlag
                        SetAttributeFromAttribute( lTrnscpt, "Registration", "wCourseRepeatableForCreditFlag", lTrnscpt, "RegistrationCourse", "RepeatableForCreditFlag" );
                        //:IF lTrnscpt.RegistrationCourseTopic EXISTS
                        lTempInteger_4 = CheckExistenceOfEntity( lTrnscpt, "RegistrationCourseTopic" );
                        if ( lTempInteger_4 == 0 )
                        { 
                           //:lTrnscpt.Registration.wCourseNumber = lTrnscpt.RegistrationCourse.Number + lTrnscpt.RegistrationCourseTopic.Number
                           {StringBuilder sb_szTempString_0;
                           if ( szTempString_0 == null )
                              sb_szTempString_0 = new StringBuilder( 32 );
                           else
                              sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                                       GetStringFromAttribute( sb_szTempString_0, lTrnscpt, "RegistrationCourse", "Number" );
                           szTempString_0 = sb_szTempString_0.toString( );}
                           {StringBuilder sb_szTempString_1;
                           if ( szTempString_1 == null )
                              sb_szTempString_1 = new StringBuilder( 32 );
                           else
                              sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                                       GetStringFromAttribute( sb_szTempString_1, lTrnscpt, "RegistrationCourseTopic", "Number" );
                           szTempString_1 = sb_szTempString_1.toString( );}
                            {StringBuilder sb_szTempString_0;
                           if ( szTempString_0 == null )
                              sb_szTempString_0 = new StringBuilder( 32 );
                           else
                              sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                                      ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 255 );
                           szTempString_0 = sb_szTempString_0.toString( );}
                           SetAttributeFromString( lTrnscpt, "Registration", "wCourseNumber", szTempString_0 );
                           //:ELSE
                        } 
                        else
                        { 
                           //:lTrnscpt.Registration.wCourseNumber = lTrnscpt.RegistrationCourse.Number 
                           SetAttributeFromAttribute( lTrnscpt, "Registration", "wCourseNumber", lTrnscpt, "RegistrationCourse", "Number" );
                        } 

                        //:END
                     } 

                     //:END
                     //:IF lTrnscpt.RegistrationCourseTopic EXISTS
                     lTempInteger_5 = CheckExistenceOfEntity( lTrnscpt, "RegistrationCourseTopic" );
                     if ( lTempInteger_5 == 0 )
                     { 
                        //:// Override the Title with the Topic, if one exists.
                        //:lTrnscpt.Registration.wCourseTitle  = lTrnscpt.RegistrationCourseTopic.Title 
                        SetAttributeFromAttribute( lTrnscpt, "Registration", "wCourseTitle", lTrnscpt, "RegistrationCourseTopic", "Title" );
                     } 

                     //:END
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF lTrnscpt.EquivalentCourse EXISTS
                     lTempInteger_6 = CheckExistenceOfEntity( lTrnscpt, "EquivalentCourse" );
                     if ( lTempInteger_6 == 0 )
                     { 
                        //:IF lTrnscpt.TransferRecordTerm EXISTS
                        lTempInteger_7 = CheckExistenceOfEntity( lTrnscpt, "TransferRecordTerm" );
                        if ( lTempInteger_7 == 0 )
                        { 
                           //:lTrnscpt.Registration.wCompletedClassYear     = lTrnscpt.TransferRecordYear.Year 
                           SetAttributeFromAttribute( lTrnscpt, "Registration", "wCompletedClassYear", lTrnscpt, "TransferRecordYear", "Year" );
                           //:lTrnscpt.Registration.wCompletedClassSemester = lTrnscpt.TransferRecordTerm.Semester 
                           SetAttributeFromAttribute( lTrnscpt, "Registration", "wCompletedClassSemester", lTrnscpt, "TransferRecordTerm", "Semester" );
                           //:ELSE
                        } 
                        else
                        { 
                           //:IF lTrnscpt.Registration.Status = "X"
                           if ( CompareAttributeToString( lTrnscpt, "Registration", "Status", "X" ) == 0 )
                           { 
                              //:SET CURSOR FIRST lTrnscpt.LegacyTranscripRegistration WITHIN lTrnscpt.Student
                              //:  WHERE lTrnscpt.LegacyTranscripRegistration.ID = lTrnscpt.Registration.ID 
                              {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                                                             GetIntegerFromAttribute( mi_lTempInteger_8, lTrnscpt, "Registration", "ID" );
                              lTempInteger_8 = mi_lTempInteger_8.intValue( );}
                              RESULT = lTrnscpt.cursor( "LegacyTranscripRegistration" ).setFirst( "ID", lTempInteger_8, "Student" ).toInt();
                              //:IF RESULT < zCURSOR_SET
                              if ( RESULT < zCURSOR_SET )
                              { 
                                 //:IssueError( lTrnscpt,0,0, "Programming Error. No match on Legacy reg." )
                                 IssueError( lTrnscpt, 0, 0, "Programming Error. No match on Legacy reg." );
                              } 

                              //:END
                              //:IF lTrnscpt.LegacyTranscriptTerm EXISTS
                              lTempInteger_9 = CheckExistenceOfEntity( lTrnscpt, "LegacyTranscriptTerm" );
                              if ( lTempInteger_9 == 0 )
                              { 
                                 //:lTrnscpt.Registration.wCompletedClassYear     = lTrnscpt.LegacyTranscriptYear.Year 
                                 SetAttributeFromAttribute( lTrnscpt, "Registration", "wCompletedClassYear", lTrnscpt, "LegacyTranscriptYear", "Year" );
                                 //:lTrnscpt.Registration.wCompletedClassSemester = lTrnscpt.LegacyTranscriptTerm.Semester 
                                 SetAttributeFromAttribute( lTrnscpt, "Registration", "wCompletedClassSemester", lTrnscpt, "LegacyTranscriptTerm", "Semester" );
                              } 

                              //:END
                           } 

                           //:END
                        } 

                        //:END
                        //:lTrnscpt.Registration.wCourseID                      = lTrnscpt.EquivalentCourse.ID 
                        SetAttributeFromAttribute( lTrnscpt, "Registration", "wCourseID", lTrnscpt, "EquivalentCourse", "ID" );
                        //:lTrnscpt.Registration.wCourseRepeatableForCreditFlag = lTrnscpt.EquivalentCourse.RepeatableForCreditFlag 
                        SetAttributeFromAttribute( lTrnscpt, "Registration", "wCourseRepeatableForCreditFlag", lTrnscpt, "EquivalentCourse", "RepeatableForCreditFlag" );
                        //:lTrnscpt.Registration.wSortOrder                     = "2"  // Transfer entries are sorted after Regular entries.
                        SetAttributeFromString( lTrnscpt, "Registration", "wSortOrder", "2" );
                     } 

                     //:END
                  } 

               } 

               RESULT = lTrnscpt.cursor( "Registration" ).setNextContinue().toInt();;
               //:END
            } 

            //:END
            //:OrderEntityForView( lTrnscpt, "Registration", "wCompletedClassYear A wCohortClassEndDate A wCompletedClassSemester A wSortOrder A" )
            OrderEntityForView( lTrnscpt, "Registration", "wCompletedClassYear A wCohortClassEndDate A wCompletedClassSemester A wSortOrder A" );

            //:// This section processes Repeated entries. Where repeated entries exist, we will mark the "repeated" entry with either an "R"
            //:// or a "V" and the "replacing" entry with a "P". The attribute used for this is wRepeatedClass and its values are
            //:// defined as follows:
            //:// R - This entry is repeated and has a repeating entry, which appears LATER in the Transcript.
            //:// V - This entry is repeated, but is considered VOID, because the repeating entry appears EARLIER in the Transcript. A VOIDed
            //://     entry has no impact on cumulative entries in the Trascript.
            //:// P - This entry repeats another entry (a repeated entry) which appears EARLIER or LATER in the Transcript.
            //:// The reason for the last two entries being different is that accumulative values are handled differently for the two cases.

            //:// The wRepeatedClass values are set based on the following logic.
            //:// 1. If a "manual repeating" specification exists, the two manually repeating attributes determine the "repeated" and "repeating" entries.
            //:// 2. Otherwise, we will search the Registration entries to look for repeated entries and determine the "repeated" and "repeating" entries
            //://    based on grade.

            //:// To handle cumulative values properly, we will have to make adjusting entries in the Repeated entries. What adjusting entries are made
            //:// depend on whether the repeated entry appears before or after the repeating entry and on the grade, as follows. 
            //:// 1. If the repeated entry appears after, the repeating entry has no impact on cumulative values and no adjusting values are created. 
            //:// 2. If the repeated entry appears before, adjustments are always made for Attempted Credits.
            //:// 3. If the repeated entry appears before and the grade has grade point values or a grade of "S" or null (for transferred entries), 
            //://    then adjustment entries are made for Earned Credits.
            //:// 4. If the repeated entry appears before and the grade is not U, S or W, then adjustment entries are made for all GPA values.

            //:// We'll only consider Registration entries that are "Completed" or transferred with an Equivalent Course, which
            //:// had the wCourseID attribute set in the loop above.
            //:FOR EACH lTrnscpt.Registration WHERE lTrnscpt.Registration.wCourseID != ""
            RESULT = lTrnscpt.cursor( "Registration" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               if ( CompareAttributeToString( lTrnscpt, "Registration", "wCourseID", "" ) != 0 )
               { 
                  //:IF lTrnscpt.Registration.ManuallyRepeatedRegistrationID != ""
                  if ( CompareAttributeToString( lTrnscpt, "Registration", "ManuallyRepeatedRegistrationID", "" ) != 0 )
                  { 
                     //:CreateViewFromView( lTrnscptT, lTrnscpt )
                     CreateViewFromView( lTrnscptT, lTrnscpt );
                     //:SET CURSOR NEXT lTrnscptT.Registration WHERE lTrnscptT.Registration.ID = lTrnscpt.Registration.ManuallyRepeatedRegistrationID
                     {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
                                           GetIntegerFromAttribute( mi_lTempInteger_10, lTrnscpt, "Registration", "ManuallyRepeatedRegistrationID" );
                     lTempInteger_10 = mi_lTempInteger_10.intValue( );}
                     RESULT = lTrnscptT.cursor( "Registration" ).setNext( "ID", lTempInteger_10 ).toInt();
                     //:IF RESULT >= zCURSOR_SET
                     if ( RESULT >= zCURSOR_SET )
                     { 
                        //:// The Repeated Course is LATER in the Transcript.
                        //:lTrnscptT.Registration.wRepeatedClass  = "V"     // This Class is "repeated".
                        SetAttributeFromString( lTrnscptT, "Registration", "wRepeatedClass", "V" );
                        //:lTrnscpt.Registration.wRepeatedClass   = "P"     // This Class "replaces" the "repeated" Class.
                        SetAttributeFromString( lTrnscpt, "Registration", "wRepeatedClass", "P" );
                        //:ELSE
                     } 
                     else
                     { 
                        //:// The Repeated Course is EARLIER in the Transcript.
                        //:SET CURSOR FIRST lTrnscptT.Registration WHERE lTrnscptT.Registration.ID = lTrnscpt.Registration.ManuallyRepeatedRegistrationID
                        {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
                                                 GetIntegerFromAttribute( mi_lTempInteger_11, lTrnscpt, "Registration", "ManuallyRepeatedRegistrationID" );
                        lTempInteger_11 = mi_lTempInteger_11.intValue( );}
                        RESULT = lTrnscptT.cursor( "Registration" ).setFirst( "ID", lTempInteger_11 ).toInt();
                        //:lTrnscptT.Registration.wRepeatedClass  = "R"     // This Class is "repeated".
                        SetAttributeFromString( lTrnscptT, "Registration", "wRepeatedClass", "R" );
                        //:lTrnscpt.Registration.wRepeatedClass   = "P"     // This Class "replaces" the "repeated" Class.
                        SetAttributeFromString( lTrnscpt, "Registration", "wRepeatedClass", "P" );
                        //:// Attempted Credits
                        //:lTrnscpt.Registration.wReplacedAttemptedCredits = lTrnscptT.Registration.CreditHours 
                        SetAttributeFromAttribute( lTrnscpt, "Registration", "wReplacedAttemptedCredits", lTrnscptT, "Registration", "CreditHours" );
                        //:// Earned Credits
                        //:IF lTrnscptT.Registration.OverridingGradePointValue = "" 
                        if ( CompareAttributeToString( lTrnscptT, "Registration", "OverridingGradePointValue", "" ) == 0 )
                        { 
                           //:GetStringFromAttributeByContext( szDecimalString,
                           //:                        lTrnscptT, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 )
                           {StringBuilder sb_szDecimalString;
                           if ( szDecimalString == null )
                              sb_szDecimalString = new StringBuilder( 32 );
                           else
                              sb_szDecimalString = new StringBuilder( szDecimalString );
                                                       GetStringFromAttributeByContext( sb_szDecimalString, lTrnscptT, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 );
                           szDecimalString = sb_szDecimalString.toString( );}
                           //:EarlierGrade = StrToDecimal( szDecimalString )
                           {
                            ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( lTrnscpt );
                            EarlierGrade = m_ZGLOBAL1_Operation.StrToDecimal( szDecimalString );
                            // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                           }
                           //:ELSE
                        } 
                        else
                        { 
                           //:EarlierGrade = lTrnscptT.Registration.OverridingGradePointValue 
                           {MutableDouble md_EarlierGrade = new MutableDouble( EarlierGrade );
                                                       GetDecimalFromAttribute( md_EarlierGrade, lTrnscptT, "Registration", "OverridingGradePointValue" );
                           EarlierGrade = md_EarlierGrade.doubleValue( );}
                        } 

                        //:END
                        //:IF EarlierGrade > 0 OR 
                        //:lTrnscptT.Registration.FinalGrade = "S" OR
                        //:lTrnscptT.Registration.FinalGrade = ""
                        if ( EarlierGrade > 0 || CompareAttributeToString( lTrnscptT, "Registration", "FinalGrade", "S" ) == 0 || CompareAttributeToString( lTrnscptT, "Registration", "FinalGrade", "" ) == 0 )
                        { 

                           //:// Earned Credits are only adjusted if the Repeated Class grade had grade points or the grade was "S".
                           //:lTrnscpt.Registration.wReplacedEarnedCredits = lTrnscptT.Registration.CreditHours 
                           SetAttributeFromAttribute( lTrnscpt, "Registration", "wReplacedEarnedCredits", lTrnscptT, "Registration", "CreditHours" );
                        } 

                        //:END
                        //:// GPA Values
                        //:// Grade of U, S or W does not impact GPA values.
                        //:IF lTrnscptT.Registration.FinalGrade != "U" AND 
                        //:lTrnscptT.Registration.FinalGrade != "S" AND 
                        //:lTrnscptT.Registration.FinalGrade != "W" AND 
                        //:lTrnscptT.Registration.FinalGrade != ""
                        if ( CompareAttributeToString( lTrnscptT, "Registration", "FinalGrade", "U" ) != 0 && CompareAttributeToString( lTrnscptT, "Registration", "FinalGrade", "S" ) != 0 &&
                        CompareAttributeToString( lTrnscptT, "Registration", "FinalGrade", "W" ) != 0 && CompareAttributeToString( lTrnscptT, "Registration", "FinalGrade", "" ) != 0 )
                        { 

                           //:lTrnscpt.Registration.wReplacedGPA_DivisorCredits = lTrnscptT.Registration.CreditHours
                           SetAttributeFromAttribute( lTrnscpt, "Registration", "wReplacedGPA_DivisorCredits", lTrnscptT, "Registration", "CreditHours" );
                           //:lTrnscpt.Registration.wReplacedGPA_Points = EarlierGrade * lTrnscptT.Registration.CreditHours
                           {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                                       GetDecimalFromAttribute( md_dTempDecimal_0, lTrnscptT, "Registration", "CreditHours" );
                           dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                           dTempDecimal_1 = EarlierGrade * dTempDecimal_0;
                           SetAttributeFromDecimal( lTrnscpt, "Registration", "wReplacedGPA_Points", dTempDecimal_1 );
                        } 

                        //:END
                     } 

                     //:END
                     //:DropView( lTrnscptT )
                     DropView( lTrnscptT );
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF lTrnscpt.Registration.wCourseRepeatableForCreditFlag != "Y" AND 
                     //:lTrnscpt.Registration.wRepeatedClass = ""
                     if ( CompareAttributeToString( lTrnscpt, "Registration", "wCourseRepeatableForCreditFlag", "Y" ) != 0 && CompareAttributeToString( lTrnscpt, "Registration", "wRepeatedClass", "" ) == 0 )
                     { 

                        //:// Go to look for repeating entries recursively.
                        //:ProcessRepeatRecur( lTrnscpt, lTrnscpt )
                        olTrnscpt_ProcessRepeatRecur( lTrnscpt, lTrnscpt );
                     } 

                     //:END
                  } 

               } 

               RESULT = lTrnscpt.cursor( "Registration" ).setNextContinue().toInt();;
               //:END
            } 

            //:END

            //:// Set up College Type. We're setting it from mCollegeLST so as not to have to access the College entity in Transcript.
            //:FOR EACH lTrnscpt.RegistrationCourseCollege WITHIN lTrnscpt.Student 
            RESULT = lTrnscpt.cursor( "RegistrationCourseCollege" ).setFirst( "Student" ).toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF lTrnscpt.Registration.GradUndergradOverrideFlag = ""
               if ( CompareAttributeToString( lTrnscpt, "Registration", "GradUndergradOverrideFlag", "" ) == 0 )
               { 
                  //:SET CURSOR FIRST mCollegeLST.College WHERE mCollegeLST.College.ID = lTrnscpt.RegistrationCourseCollege.ID  
                  {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
                                     GetIntegerFromAttribute( mi_lTempInteger_12, lTrnscpt, "RegistrationCourseCollege", "ID" );
                  lTempInteger_12 = mi_lTempInteger_12.intValue( );}
                  RESULT = mCollegeLST.cursor( "College" ).setFirst( "ID", lTempInteger_12 ).toInt();
                  //:lTrnscpt.Registration.wCollegeType = mCollegeLST.College.Type 
                  SetAttributeFromAttribute( lTrnscpt, "Registration", "wCollegeType", mCollegeLST, "College", "Type" );
                  //:ELSE
               } 
               else
               { 
                  //:lTrnscpt.Registration.wCollegeType = lTrnscpt.Registration.GradUndergradOverrideFlag
                  SetAttributeFromAttribute( lTrnscpt, "Registration", "wCollegeType", lTrnscpt, "Registration", "GradUndergradOverrideFlag" );
               } 

               RESULT = lTrnscpt.cursor( "RegistrationCourseCollege" ).setNextContinue().toInt();;
               //:END
            } 

            RESULT = lTrnscpt.cursor( "Student" ).setNextContinue().toInt();;
            //:END
         } 


         //:// Create the derived path of Registration entries organized around College Term.
         //://BuildTranscript( lTrnscpt, "" )

         //:END

         //:SET CURSOR FIRST lTrnscpt.Student
         RESULT = lTrnscpt.cursor( "Student" ).setFirst().toInt();
         break ;


      //:  /* end zOCE_ACTIVATE */
      //:OF   zOCE_ACTIVATE_EMPTY:
      case zOCE_ACTIVATE_EMPTY :
         break ;

      //:  /* end zOCE_ACTIVATE_EMPTY */
      //:OF   zOCE_COMMIT:
      case zOCE_COMMIT :
         break ;

      //:// Create the Change Log entry.
      //:// We need to change the following with a special academic record.
      //:/*IF lTrnscpt.Student EXISTS
      //:GenerateTransactionEntry( lTrnscpt )
      //:END*/

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
//:BuildTranscript( VIEW lTrnscpt BASED ON LOD lTrnscpt,
//:                 STRING ( 1 ) GradUndergradFlag )

//:   VIEW mCollegeLST BASED ON LOD mCollege
public int 
olTrnscpt_BuildTranscript( View     lTrnscpt,
                           String   GradUndergradFlag )
{
   zVIEW    mCollegeLST = new zVIEW( );
   //:VIEW lTrnscpt2   BASED ON LOD lTrnscpt
   zVIEW    lTrnscpt2 = new zVIEW( );
   //:DECIMAL AttemptedCredits
   double  AttemptedCredits = 0.0;
   //:DECIMAL EarnedCredits
   double  EarnedCredits = 0.0;
   //:DECIMAL GPA_Points
   double  GPA_Points = 0.0;
   //:DECIMAL GPA_Divisor
   double  GPA_Divisor = 0.0;
   //:STRING ( 10 ) szOldTerm
   String   szOldTerm = null;
   //:STRING ( 10 ) szCollegeYear
   String   szCollegeYear = null;
   //:STRING ( 3 )  szOldTermPrefix
   String   szOldTermPrefix = null;
   //:STRING ( 3 )  szOldTermPrefixUpper
   String   szOldTermPrefixUpper = null;
   //:STRING ( 2 )  szLS_Year
   String   szLS_Year = null;
   //:STRING ( 2 )  szLS_Semester
   String   szLS_Semester = null;
   //:STRING ( 2 )  szSemester
   String   szSemester = null;
   //:STRING ( 20 ) szExternalSemester
   String   szExternalSemester = null;
   //:STRING ( 10 ) szAttemptedCredits
   String   szAttemptedCredits = null;
   //:STRING ( 10 ) szAttemptedCreditsOrig
   String   szAttemptedCreditsOrig = null;
   //:STRING ( 4 )  szStartYear
   String   szStartYear = null;
   //:STRING ( 4 )  szEndYear
   String   szEndYear = null;
   //:STRING ( 20 ) szCollegeType
   String   szCollegeType = null;
   //:STRING ( 30 ) szMonthDay1
   String   szMonthDay1 = null;
   //:STRING ( 30 ) szMonthDay2
   String   szMonthDay2 = null;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   //:STRING ( 50 ) szPrompt
   String   szPrompt = null;
   //:STRING ( 30 ) szCollegeDegreeName
   String   szCollegeDegreeName = null;
   //:STRING ( 2 )  szMajorMinorCount
   String   szMajorMinorCount = null;
   //:STRING ( 2 )  szTransferGroupCount
   String   szTransferGroupCount = null;
   //:STRING ( 4 )  szPromptSuffix
   String   szPromptSuffix = null;
   //:STRING ( 8 )  LastGraduationDate
   String   LastGraduationDate = null;
   //:STRING ( 10 ) ClassCollegeYear
   String   ClassCollegeYear = null;
   //:STRING ( 20 ) ClassCollegeYearSemester
   String   ClassCollegeYearSemester = null;
   //:STRING ( 2 )  ClassCollegeTermSemester
   String   ClassCollegeTermSemester = null;
   //:STRING ( 20 ) ClassCollegeTermDisplay
   String   ClassCollegeTermDisplay = null;
   //:STRING ( 1 )  szDeleteEntryFlag
   String   szDeleteEntryFlag = null;
   //:INTEGER       ClassCollegeTermID
   int      ClassCollegeTermID = 0;
   //:INTEGER       YearStart
   int      YearStart = 0;
   //:INTEGER       YearEnd
   int      YearEnd = 0;
   //:INTEGER       MajorCount
   int      MajorCount = 0;
   //:INTEGER       MinorCount
   int      MinorCount = 0;
   //:INTEGER       TotalCount
   int      TotalCount = 0;
   //:INTEGER       TransferGroupCount
   int      TransferGroupCount = 0;
   int      RESULT = 0;
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
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_13 = 0;
   int      lTempInteger_14 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_15 = 0;
   int      lTempInteger_16 = 0;
   int      lTempInteger_17 = 0;
   int      lTempInteger_18 = 0;
   String   szTempString_3 = null;
   String   szTempString_4 = null;
   int      lTempInteger_19 = 0;
   int      lTempInteger_20 = 0;
   int      lTempInteger_21 = 0;
   int      lTempInteger_22 = 0;
   int      lTempInteger_23 = 0;
   int      lTempInteger_24 = 0;
   int      lTempInteger_25 = 0;
   int      lTempInteger_26 = 0;
   int      lTempInteger_27 = 0;
   String   szTempString_5 = null;
   String   szTempString_6 = null;
   int      lTempInteger_28 = 0;
   int      lTempInteger_29 = 0;
   int      lTempInteger_30 = 0;
   int      lTempInteger_31 = 0;
   int      lTempInteger_32 = 0;
   String   szTempString_7 = null;
   int      lTempInteger_33 = 0;
   int      lTempInteger_34 = 0;
   int      lTempInteger_35 = 0;
   int      lTempInteger_36 = 0;
   int      lTempInteger_37 = 0;
   int      lTempInteger_38 = 0;
   String   szTempString_8 = null;
   int      lTempInteger_39 = 0;
   int      lTempInteger_40 = 0;
   String   szTempString_9 = null;
   int      lTempInteger_41 = 0;
   int      lTempInteger_42 = 0;
   int      lTempInteger_43 = 0;
   int      lTempInteger_44 = 0;
   String   szTempString_10 = null;
   String   szTempString_11 = null;
   int      lTempInteger_45 = 0;
   String   szTempString_12 = null;
   String   szTempString_13 = null;
   String   szTempString_14 = null;
   int      lTempInteger_46 = 0;
   String   szTempString_15 = null;
   String   szTempString_16 = null;
   int      lTempInteger_47 = 0;
   int      lTempInteger_48 = 0;


   //:// Build the two Transcript work subobjects, first the TranscriptGroup subobject, which compiles the
   //:// basic values, and then the PrintGroup subobject, which puts together the format required to print.

   //:FOR EACH lTrnscpt.TranscriptGroup
   RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY lTrnscpt.TranscriptGroup NONE 
      RESULT = DeleteEntity( lTrnscpt, "TranscriptGroup", zREPOS_NONE );
      RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setNextContinue().toInt();;
   } 

   //:END
   //:FOR EACH lTrnscpt.FinalEntries
   RESULT = lTrnscpt.cursor( "FinalEntries" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY lTrnscpt.FinalEntries NONE 
      RESULT = DeleteEntity( lTrnscpt, "FinalEntries", zREPOS_NONE );
      RESULT = lTrnscpt.cursor( "FinalEntries" ).setNextContinue().toInt();;
   } 

   //:END
   //:FOR EACH lTrnscpt.PrintGroup 
   RESULT = lTrnscpt.cursor( "PrintGroup" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY lTrnscpt.PrintGroup NONE  
      RESULT = DeleteEntity( lTrnscpt, "PrintGroup", zREPOS_NONE );
      RESULT = lTrnscpt.cursor( "PrintGroup" ).setNextContinue().toInt();;
   } 

   //:END

   //:// Make sure that mCollegeLST exists.
   //:GET VIEW mCollegeLST NAMED "mCollegeLST"
   RESULT = GetViewByName( mCollegeLST, "mCollegeLST", lTrnscpt, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mCollegeLST Multiple
      RESULT = ActivateObjectInstance( mCollegeLST, "mCollege", lTrnscpt, 0, zMULTIPLE );
      //:NAME VIEW mCollegeLST "mCollegeLST"
      SetNameForView( mCollegeLST, "mCollegeLST", null, zLEVEL_TASK );
   } 

   //:END

   //:// If the GradUndergradFlag has been specified, drop the other type of entities.
   //:IF GradUndergradFlag != ""
   if ( ZeidonStringCompare( GradUndergradFlag, 1, 0, "", 1, 0, 2 ) != 0 )
   { 

      //:// For Major/Minor track entries, drop all entries except for the Type of College being processed.
      //:// Types are Graduate (G), Undergraduate (U) and Certificate (C).
      //:FOR EACH lTrnscpt.StudentMajorDegreeTrack
      RESULT = lTrnscpt.cursor( "StudentMajorDegreeTrack" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF lTrnscpt.MajorCollege DOES NOT EXIST 
         lTempInteger_0 = CheckExistenceOfEntity( lTrnscpt, "MajorCollege" );
         if ( lTempInteger_0 != 0 )
         { 
            //:DropEntity( lTrnscpt, "StudentMajorDegreeTrack", zREPOS_NONE )
            DropEntity( lTrnscpt, "StudentMajorDegreeTrack", zREPOS_NONE );
            //:ELSE
         } 
         else
         { 
            //:IF lTrnscpt.MajorCollege.Type != GradUndergradFlag
            if ( CompareAttributeToString( lTrnscpt, "MajorCollege", "Type", GradUndergradFlag ) != 0 )
            { 
               //:DropEntity( lTrnscpt, "StudentMajorDegreeTrack", zREPOS_NONE )
               DropEntity( lTrnscpt, "StudentMajorDegreeTrack", zREPOS_NONE );
            } 

            //:END
         } 

         RESULT = lTrnscpt.cursor( "StudentMajorDegreeTrack" ).setNextContinue().toInt();;
         //:END 
      } 

      //:END
      //:FOR EACH lTrnscpt.StudentMinorDegreeTrack
      RESULT = lTrnscpt.cursor( "StudentMinorDegreeTrack" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF lTrnscpt.MinorCollege DOES NOT EXIST 
         lTempInteger_1 = CheckExistenceOfEntity( lTrnscpt, "MinorCollege" );
         if ( lTempInteger_1 != 0 )
         { 
            //:DropEntity( lTrnscpt, "StudentMinorDegreeTrack", zREPOS_NONE )
            DropEntity( lTrnscpt, "StudentMinorDegreeTrack", zREPOS_NONE );
            //:ELSE
         } 
         else
         { 
            //:IF lTrnscpt.MinorCollege.Type != GradUndergradFlag
            if ( CompareAttributeToString( lTrnscpt, "MinorCollege", "Type", GradUndergradFlag ) != 0 )
            { 
               //:DropEntity( lTrnscpt, "StudentMinorDegreeTrack", zREPOS_NONE )
               DropEntity( lTrnscpt, "StudentMinorDegreeTrack", zREPOS_NONE );
            } 

            //:END
         } 

         RESULT = lTrnscpt.cursor( "StudentMinorDegreeTrack" ).setNextContinue().toInt();;
         //:END 
      } 

      //:END

      //:// Drop all Transfer, Legacy Transfer and Certification Statements that are not tied to the appropriate College level, G, U or C.

      //:IF GradUndergradFlag = "G"      // College Level is Graduate.
      if ( ZeidonStringCompare( GradUndergradFlag, 1, 0, "G", 1, 0, 2 ) == 0 )
      { 

         //:szCollegeType = "G"
          {StringBuilder sb_szCollegeType;
         if ( szCollegeType == null )
            sb_szCollegeType = new StringBuilder( 32 );
         else
            sb_szCollegeType = new StringBuilder( szCollegeType );
                  ZeidonStringCopy( sb_szCollegeType, 1, 0, "G", 1, 0, 21 );
         szCollegeType = sb_szCollegeType.toString( );}
         //:FOR EACH lTrnscpt.TransferGroup
         RESULT = lTrnscpt.cursor( "TransferGroup" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:// Converted by DonC from GraduateCollegeFlag to ApplyToCollegeLevel on 1/13/2008.
            //://IF lTrnscpt.TransferGroup.GraduateCollegeFlag != "Y"
            //:IF lTrnscpt.TransferGroup.ApplyToCollegeLevel != "G"
            if ( CompareAttributeToString( lTrnscpt, "TransferGroup", "ApplyToCollegeLevel", "G" ) != 0 )
            { 
               //:DropEntity( lTrnscpt, "TransferGroup", zREPOS_NONE )
               DropEntity( lTrnscpt, "TransferGroup", zREPOS_NONE );
            } 

            RESULT = lTrnscpt.cursor( "TransferGroup" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:FOR EACH lTrnscpt.LegacyTranscriptGroup 
         RESULT = lTrnscpt.cursor( "LegacyTranscriptGroup" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lTrnscpt.LegacyTranscriptGroup.GraduateCollegeFlag != "Y"     // Legacy Transfers use old flag.
            if ( CompareAttributeToString( lTrnscpt, "LegacyTranscriptGroup", "GraduateCollegeFlag", "Y" ) != 0 )
            { 
               //:DropEntity( lTrnscpt, "LegacyTranscriptGroup", zREPOS_NONE )
               DropEntity( lTrnscpt, "LegacyTranscriptGroup", zREPOS_NONE );
            } 

            RESULT = lTrnscpt.cursor( "LegacyTranscriptGroup" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:FOR EACH lTrnscpt.StudentCertification
         RESULT = lTrnscpt.cursor( "StudentCertification" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:// Converted by DonC from GraduateCollegeFlag to ApplyToCollegeLevel on 1/13/2008.
            //://IF lTrnscpt.StudentCertification.GraduateCollegeFlag != "Y"
            //:IF lTrnscpt.StudentCertification.ApplyToCollegeLevel != "G"
            if ( CompareAttributeToString( lTrnscpt, "StudentCertification", "ApplyToCollegeLevel", "G" ) != 0 )
            { 
               //:DropEntity( lTrnscpt, "StudentCertification", zREPOS_NONE )
               DropEntity( lTrnscpt, "StudentCertification", zREPOS_NONE );
            } 

            RESULT = lTrnscpt.cursor( "StudentCertification" ).setNextContinue().toInt();;
            //:END
         } 

         //:END

         //:ELSE
      } 
      else
      { 
         //:IF GradUndergradFlag = "C"      // College Level is Certificate.
         if ( ZeidonStringCompare( GradUndergradFlag, 1, 0, "C", 1, 0, 2 ) == 0 )
         { 

            //:szCollegeType = "C"
             {StringBuilder sb_szCollegeType;
            if ( szCollegeType == null )
               sb_szCollegeType = new StringBuilder( 32 );
            else
               sb_szCollegeType = new StringBuilder( szCollegeType );
                        ZeidonStringCopy( sb_szCollegeType, 1, 0, "C", 1, 0, 21 );
            szCollegeType = sb_szCollegeType.toString( );}
            //:FOR EACH lTrnscpt.TransferGroup
            RESULT = lTrnscpt.cursor( "TransferGroup" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF lTrnscpt.TransferGroup.ApplyToCollegeLevel != "C"
               if ( CompareAttributeToString( lTrnscpt, "TransferGroup", "ApplyToCollegeLevel", "C" ) != 0 )
               { 
                  //:DropEntity( lTrnscpt, "TransferGroup", zREPOS_NONE )
                  DropEntity( lTrnscpt, "TransferGroup", zREPOS_NONE );
               } 

               RESULT = lTrnscpt.cursor( "TransferGroup" ).setNextContinue().toInt();;
               //:END
            } 

            //:END
            //:// There are no Legacy Tranfers for Certificate, so drop all of them.
            //:FOR EACH lTrnscpt.LegacyTranscriptGroup      
            RESULT = lTrnscpt.cursor( "LegacyTranscriptGroup" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:DropEntity( lTrnscpt, "LegacyTranscriptGroup", zREPOS_NONE )
               DropEntity( lTrnscpt, "LegacyTranscriptGroup", zREPOS_NONE );
               RESULT = lTrnscpt.cursor( "LegacyTranscriptGroup" ).setNextContinue().toInt();;
            } 

            //:END
            //:FOR EACH lTrnscpt.StudentCertification
            RESULT = lTrnscpt.cursor( "StudentCertification" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF lTrnscpt.StudentCertification.ApplyToCollegeLevel != "C"
               if ( CompareAttributeToString( lTrnscpt, "StudentCertification", "ApplyToCollegeLevel", "C" ) != 0 )
               { 
                  //:DropEntity( lTrnscpt, "StudentCertification", zREPOS_NONE )
                  DropEntity( lTrnscpt, "StudentCertification", zREPOS_NONE );
               } 

               RESULT = lTrnscpt.cursor( "StudentCertification" ).setNextContinue().toInt();;
               //:END
            } 

            //:END

            //:ELSE
         } 
         else
         { 

            //:szCollegeType = "U"      // College Level is Undergraduate.
             {StringBuilder sb_szCollegeType;
            if ( szCollegeType == null )
               sb_szCollegeType = new StringBuilder( 32 );
            else
               sb_szCollegeType = new StringBuilder( szCollegeType );
                        ZeidonStringCopy( sb_szCollegeType, 1, 0, "U", 1, 0, 21 );
            szCollegeType = sb_szCollegeType.toString( );}
            //:FOR EACH lTrnscpt.TransferGroup
            RESULT = lTrnscpt.cursor( "TransferGroup" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF lTrnscpt.TransferGroup.ApplyToCollegeLevel != "U"
               if ( CompareAttributeToString( lTrnscpt, "TransferGroup", "ApplyToCollegeLevel", "U" ) != 0 )
               { 
                  //:DropEntity( lTrnscpt, "TransferGroup", zREPOS_NONE )
                  DropEntity( lTrnscpt, "TransferGroup", zREPOS_NONE );
               } 

               RESULT = lTrnscpt.cursor( "TransferGroup" ).setNextContinue().toInt();;
               //:END
            } 

            //:END
            //:FOR EACH lTrnscpt.LegacyTranscriptGroup 
            RESULT = lTrnscpt.cursor( "LegacyTranscriptGroup" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF lTrnscpt.LegacyTranscriptGroup.GraduateCollegeFlag = "Y"
               if ( CompareAttributeToString( lTrnscpt, "LegacyTranscriptGroup", "GraduateCollegeFlag", "Y" ) == 0 )
               { 
                  //:DropEntity( lTrnscpt, "LegacyTranscriptGroup", zREPOS_NONE )
                  DropEntity( lTrnscpt, "LegacyTranscriptGroup", zREPOS_NONE );
               } 

               RESULT = lTrnscpt.cursor( "LegacyTranscriptGroup" ).setNextContinue().toInt();;
               //:END
            } 

            //:END
            //:FOR EACH lTrnscpt.StudentCertification
            RESULT = lTrnscpt.cursor( "StudentCertification" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF lTrnscpt.StudentCertification.ApplyToCollegeLevel != "U"
               if ( CompareAttributeToString( lTrnscpt, "StudentCertification", "ApplyToCollegeLevel", "U" ) != 0 )
               { 
                  //:DropEntity( lTrnscpt, "StudentCertification", zREPOS_NONE )
                  DropEntity( lTrnscpt, "StudentCertification", zREPOS_NONE );
               } 

               RESULT = lTrnscpt.cursor( "StudentCertification" ).setNextContinue().toInt();;
               //:END
            } 

            //:END
         } 

         //:END
      } 

      //:END
      //:FOR EACH lTrnscpt.Registration 
      RESULT = lTrnscpt.cursor( "Registration" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF lTrnscpt.Registration.GradUndergradOverrideFlag != ""
         if ( CompareAttributeToString( lTrnscpt, "Registration", "GradUndergradOverrideFlag", "" ) != 0 )
         { 
            //:IF lTrnscpt.Registration.GradUndergradOverrideFlag != GradUndergradFlag
            if ( CompareAttributeToString( lTrnscpt, "Registration", "GradUndergradOverrideFlag", GradUndergradFlag ) != 0 )
            { 
               //:DropEntity( lTrnscpt, "Registration", zREPOS_NONE )
               DropEntity( lTrnscpt, "Registration", zREPOS_NONE );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF lTrnscpt.RegistrationCourseCollege EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( lTrnscpt, "RegistrationCourseCollege" );
            if ( lTempInteger_2 == 0 )
            { 
               //:SET CURSOR FIRST mCollegeLST.College WHERE mCollegeLST.College.ID = lTrnscpt.RegistrationCourseCollege.ID 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                               GetIntegerFromAttribute( mi_lTempInteger_3, lTrnscpt, "RegistrationCourseCollege", "ID" );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );}
               RESULT = mCollegeLST.cursor( "College" ).setFirst( "ID", lTempInteger_3 ).toInt();
               //:IF mCollegeLST.College.Type != szCollegeType
               if ( CompareAttributeToString( mCollegeLST, "College", "Type", szCollegeType ) != 0 )
               { 
                  //:DropEntity( lTrnscpt, "Registration", zREPOS_NONE )
                  DropEntity( lTrnscpt, "Registration", zREPOS_NONE );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF lTrnscpt.EquivalentCourseCollege EXISTS
               lTempInteger_4 = CheckExistenceOfEntity( lTrnscpt, "EquivalentCourseCollege" );
               if ( lTempInteger_4 == 0 )
               { 
                  //:SET CURSOR FIRST mCollegeLST.College WHERE mCollegeLST.College.ID = lTrnscpt.EquivalentCourseCollege.ID 
                  {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                                     GetIntegerFromAttribute( mi_lTempInteger_5, lTrnscpt, "EquivalentCourseCollege", "ID" );
                  lTempInteger_5 = mi_lTempInteger_5.intValue( );}
                  RESULT = mCollegeLST.cursor( "College" ).setFirst( "ID", lTempInteger_5 ).toInt();
                  //:IF mCollegeLST.College.Type != szCollegeType
                  if ( CompareAttributeToString( mCollegeLST, "College", "Type", szCollegeType ) != 0 )
                  { 
                     //:DropEntity( lTrnscpt, "Registration", zREPOS_NONE )
                     DropEntity( lTrnscpt, "Registration", zREPOS_NONE );
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
         } 

         RESULT = lTrnscpt.cursor( "Registration" ).setNextContinue().toInt();;
         //:END
      } 

      //:END
   } 

   //:END

   //:// Process Legacy Group entries.
   //:FOR EACH lTrnscpt.LegacyTranscriptGroup 
   RESULT = lTrnscpt.cursor( "LegacyTranscriptGroup" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// Add Legacy Group for Term, if it is correct graduate/undergraduate match.
      //:IF ( lTrnscpt.LegacyTranscriptGroup.GraduateCollegeFlag = "Y" AND GradUndergradFlag = "G" ) OR
      //:   ( lTrnscpt.LegacyTranscriptGroup.GraduateCollegeFlag = "" AND GradUndergradFlag = "U" )
      if ( ( CompareAttributeToString( lTrnscpt, "LegacyTranscriptGroup", "GraduateCollegeFlag", "Y" ) == 0 && ZeidonStringCompare( GradUndergradFlag, 1, 0, "G", 1, 0, 2 ) == 0 ) ||
           ( CompareAttributeToString( lTrnscpt, "LegacyTranscriptGroup", "GraduateCollegeFlag", "" ) == 0 && ZeidonStringCompare( GradUndergradFlag, 1, 0, "U", 1, 0, 2 ) == 0 ) )
      { 

         //:szOldTerm = lTrnscpt.LegacyTranscriptGroup.TermCode 
         {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
         StringBuilder sb_szOldTerm;
         if ( szOldTerm == null )
            sb_szOldTerm = new StringBuilder( 32 );
         else
            sb_szOldTerm = new StringBuilder( szOldTerm );
                   GetVariableFromAttribute( sb_szOldTerm, mi_lTempInteger_6, 'S', 11, lTrnscpt, "LegacyTranscriptGroup", "TermCode", "", 0 );
         lTempInteger_6 = mi_lTempInteger_6.intValue( );
         szOldTerm = sb_szOldTerm.toString( );}
         //:szOldTermPrefix = szOldTerm
          {StringBuilder sb_szOldTermPrefix;
         if ( szOldTermPrefix == null )
            sb_szOldTermPrefix = new StringBuilder( 32 );
         else
            sb_szOldTermPrefix = new StringBuilder( szOldTermPrefix );
                  ZeidonStringCopy( sb_szOldTermPrefix, 1, 0, szOldTerm, 1, 0, 4 );
         szOldTermPrefix = sb_szOldTermPrefix.toString( );}
         //:zToUpper( szOldTermPrefix, szOldTermPrefixUpper )
         {StringBuilder sb_szOldTermPrefixUpper;
         if ( szOldTermPrefixUpper == null )
            sb_szOldTermPrefixUpper = new StringBuilder( 32 );
         else
            sb_szOldTermPrefixUpper = new StringBuilder( szOldTermPrefixUpper );
                   zToUpper( szOldTermPrefix, sb_szOldTermPrefixUpper );
         szOldTermPrefixUpper = sb_szOldTermPrefixUpper.toString( );}
         //:IF szOldTermPrefixUpper = "LAS"
         if ( ZeidonStringCompare( szOldTermPrefixUpper, 1, 0, "LAS", 1, 0, 4 ) == 0 )
         { 
            //:// Add Final Group
            //:IF lTrnscpt.FinalEntries DOES NOT EXIST
            lTempInteger_7 = CheckExistenceOfEntity( lTrnscpt, "FinalEntries" );
            if ( lTempInteger_7 != 0 )
            { 
               //:CREATE ENTITY lTrnscpt.FinalEntries  
               RESULT = CreateEntity( lTrnscpt, "FinalEntries", zPOS_AFTER );
            } 

            //:END
            //:INCLUDE lTrnscpt.FinalLegacyGroup FROM lTrnscpt.LegacyTranscriptGroup 
            RESULT = IncludeSubobjectFromSubobject( lTrnscpt, "FinalLegacyGroup", lTrnscpt, "LegacyTranscriptGroup", zPOS_AFTER );
            //:ELSE
         } 
         else
         { 
            //:CREATE ENTITY lTrnscpt.TranscriptGroup 
            RESULT = CreateEntity( lTrnscpt, "TranscriptGroup", zPOS_AFTER );
            //:FOR EACH lTrnscpt.LegacyTranscriptGroupLine 
            RESULT = lTrnscpt.cursor( "LegacyTranscriptGroupLine" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:CREATE ENTITY lTrnscpt.TTG_HeaderLine
               RESULT = CreateEntity( lTrnscpt, "TTG_HeaderLine", zPOS_AFTER );
               //:lTrnscpt.TTG_HeaderLine.LineText = lTrnscpt.LegacyTranscriptGroupLine.LineText
               SetAttributeFromAttribute( lTrnscpt, "TTG_HeaderLine", "LineText", lTrnscpt, "LegacyTranscriptGroupLine", "LineText" );
               RESULT = lTrnscpt.cursor( "LegacyTranscriptGroupLine" ).setNextContinue().toInt();;
            } 

            //:END
            //:IF lTrnscpt.LegacyTranscriptTerm EXISTS
            lTempInteger_8 = CheckExistenceOfEntity( lTrnscpt, "LegacyTranscriptTerm" );
            if ( lTempInteger_8 == 0 )
            { 
               //:// Add Legacy Group for Term
               //:IF lTrnscpt.LegacyTranscriptTerm.TranscriptTermDisplay = ""
               if ( CompareAttributeToString( lTrnscpt, "LegacyTranscriptTerm", "TranscriptTermDisplay", "" ) == 0 )
               { 
                  //:lTrnscpt.TranscriptGroup.YearSemester = lTrnscpt.LegacyTranscriptTerm.YearSemester
                  SetAttributeFromAttribute( lTrnscpt, "TranscriptGroup", "YearSemester", lTrnscpt, "LegacyTranscriptTerm", "YearSemester" );
                  //:ELSE
               } 
               else
               { 
                  //:lTrnscpt.TranscriptGroup.YearSemester = lTrnscpt.LegacyTranscriptTerm.TranscriptTermDisplay
                  SetAttributeFromAttribute( lTrnscpt, "TranscriptGroup", "YearSemester", lTrnscpt, "LegacyTranscriptTerm", "TranscriptTermDisplay" );
               } 

               //:END
               //:lTrnscpt.TranscriptGroup.Semester       = lTrnscpt.LegacyTranscriptTerm.Semester 
               SetAttributeFromAttribute( lTrnscpt, "TranscriptGroup", "Semester", lTrnscpt, "LegacyTranscriptTerm", "Semester" );
               //:lTrnscpt.TranscriptGroup.wTranscriptGroupID = lTrnscpt.LegacyTranscriptTerm.ID 
               SetAttributeFromAttribute( lTrnscpt, "TranscriptGroup", "wTranscriptGroupID", lTrnscpt, "LegacyTranscriptTerm", "ID" );
               //:lTrnscpt.TranscriptGroup.wCollegeYear   = lTrnscpt.LegacyTranscriptYear.Year
               SetAttributeFromAttribute( lTrnscpt, "TranscriptGroup", "wCollegeYear", lTrnscpt, "LegacyTranscriptYear", "Year" );
               //:lTrnscpt.TranscriptGroup.wSortOrder     = "2"   // Transfer entries are ordered after Regular entries.
               SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wSortOrder", "2" );
               //:lTrnscpt.TranscriptGroup.wRecordType    = "L" 
               SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wRecordType", "L" );
               //:ELSE
            } 
            else
            { 
               //:lTrnscpt.TranscriptGroup.wLegacyTerm    = szOldTerm
               SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wLegacyTerm", szOldTerm );
            } 

            //:END
            //:FOR EACH lTrnscpt.LegacyTranscripRegistration 
            RESULT = lTrnscpt.cursor( "LegacyTranscripRegistration" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:SET CURSOR FIRST lTrnscpt.Registration 
               //:           WHERE lTrnscpt.Registration.ID = lTrnscpt.LegacyTranscripRegistration.ID   
               {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
                               GetIntegerFromAttribute( mi_lTempInteger_9, lTrnscpt, "LegacyTranscripRegistration", "ID" );
               lTempInteger_9 = mi_lTempInteger_9.intValue( );}
               RESULT = lTrnscpt.cursor( "Registration" ).setFirst( "ID", lTempInteger_9 ).toInt();
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:INCLUDE lTrnscpt.TermRegistration FROM lTrnscpt.Registration 
                  RESULT = IncludeSubobjectFromSubobject( lTrnscpt, "TermRegistration", lTrnscpt, "Registration", zPOS_AFTER );
                  //:IF lTrnscpt.EquivalentCourse EXISTS
                  lTempInteger_10 = CheckExistenceOfEntity( lTrnscpt, "EquivalentCourse" );
                  if ( lTempInteger_10 == 0 )
                  { 
                     //:lTrnscpt.TermRegistration.wCourseNumber = lTrnscpt.EquivalentCourse.Number 
                     SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCourseNumber", lTrnscpt, "EquivalentCourse", "Number" );
                  } 

                  //:END
                  //:lTrnscpt.TermRegistration.wCourseTitle                = lTrnscpt.Registration.ForeignClassName 
                  SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCourseTitle", lTrnscpt, "Registration", "ForeignClassName" );
                  //:lTrnscpt.TermRegistration.wRepeatedClass              = lTrnscpt.Registration.wRepeatedClass
                  SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wRepeatedClass", lTrnscpt, "Registration", "wRepeatedClass" );
                  //:lTrnscpt.TermRegistration.wReplacedAttemptedCredits   = lTrnscpt.Registration.wReplacedAttemptedCredits 
                  SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedAttemptedCredits", lTrnscpt, "Registration", "wReplacedAttemptedCredits" );
                  //:lTrnscpt.TermRegistration.wReplacedEarnedCredits      = lTrnscpt.Registration.wReplacedEarnedCredits 
                  SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedEarnedCredits", lTrnscpt, "Registration", "wReplacedEarnedCredits" );
                  //:lTrnscpt.TermRegistration.wReplacedGPA_DivisorCredits = lTrnscpt.Registration.wReplacedGPA_DivisorCredits
                  SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedGPA_DivisorCredits", lTrnscpt, "Registration", "wReplacedGPA_DivisorCredits" );
                  //:lTrnscpt.TermRegistration.wReplacedGPA_Points         = lTrnscpt.Registration.wReplacedGPA_Points 
                  SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedGPA_Points", lTrnscpt, "Registration", "wReplacedGPA_Points" );
               } 

               RESULT = lTrnscpt.cursor( "LegacyTranscripRegistration" ).setNextContinue().toInt();;
               //:END
            } 

            //:END
         } 

         //:END
      } 

      RESULT = lTrnscpt.cursor( "LegacyTranscriptGroup" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:// Process Transfer/Waiver Group entries.
   //:TransferGroupCount = 0
   TransferGroupCount = 0;
   //:FOR EACH lTrnscpt.TransferGroup 
   RESULT = lTrnscpt.cursor( "TransferGroup" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Converted by DonC from GraduateCollegeFlag to ApplyToCollegeLevel on 1/13/2008.
      //:// Add Transfer/Waiver Group for Term, if it is correct graduate/undergraduate match.
      //://IF ( lTrnscpt.TransferGroup.GraduateCollegeFlag = "Y" AND GradUndergradFlag = "G" ) OR
      //://   ( lTrnscpt.TransferGroup.GraduateCollegeFlag = "" AND GradUndergradFlag = "U" ) OR
      //://   ( lTrnscpt.TransferGroup.GraduateCollegeFlag = "" AND GradUndergradFlag = "C" )

      //:// Add Transfer/Waiver Group for Term, if it matches on Apply To College Level.
      //:IF lTrnscpt.TransferGroup.ApplyToCollegeLevel = GradUndergradFlag
      if ( CompareAttributeToString( lTrnscpt, "TransferGroup", "ApplyToCollegeLevel", GradUndergradFlag ) == 0 )
      { 

         //:CREATE ENTITY lTrnscpt.TranscriptGroup 
         RESULT = CreateEntity( lTrnscpt, "TranscriptGroup", zPOS_AFTER );
         //:IF lTrnscpt.TG_College EXISTS
         lTempInteger_11 = CheckExistenceOfEntity( lTrnscpt, "TG_College" );
         if ( lTempInteger_11 == 0 )
         { 
            //:IF lTrnscpt.TG_CollegeAddress EXISTS
            lTempInteger_12 = CheckExistenceOfEntity( lTrnscpt, "TG_CollegeAddress" );
            if ( lTempInteger_12 == 0 )
            { 
               //:lTrnscpt.TranscriptGroup.wForeignCollegeName = lTrnscpt.TG_College.Name +
               //:", " + lTrnscpt.TG_CollegeAddress.StateProvince
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, lTrnscpt, "TG_College", "Name" );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                              ZeidonStringConcat( sb_szTempString_0, 1, 0, ", ", 1, 0, 255 );
               szTempString_0 = sb_szTempString_0.toString( );}
               {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
               StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_13, 'S', 3, lTrnscpt, "TG_CollegeAddress", "StateProvince", "", 0 );
               lTempInteger_13 = mi_lTempInteger_13.intValue( );
               szTempString_1 = sb_szTempString_1.toString( );}
                {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                              ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 255 );
               szTempString_0 = sb_szTempString_0.toString( );}
               SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wForeignCollegeName", szTempString_0 );
               //:ELSE
            } 
            else
            { 
               //:lTrnscpt.TranscriptGroup.wForeignCollegeName = lTrnscpt.TG_College.Name
               SetAttributeFromAttribute( lTrnscpt, "TranscriptGroup", "wForeignCollegeName", lTrnscpt, "TG_College", "Name" );
            } 

            //:END
         } 

         //:END
         //:FOR EACH lTrnscpt.TG_HeaderLine 
         RESULT = lTrnscpt.cursor( "TG_HeaderLine" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lTrnscpt.TTG_HeaderLine
            RESULT = CreateEntity( lTrnscpt, "TTG_HeaderLine", zPOS_AFTER );
            //:lTrnscpt.TTG_HeaderLine.LineText = lTrnscpt.TG_HeaderLine.LineText 
            SetAttributeFromAttribute( lTrnscpt, "TTG_HeaderLine", "LineText", lTrnscpt, "TG_HeaderLine", "LineText" );
            RESULT = lTrnscpt.cursor( "TG_HeaderLine" ).setNextContinue().toInt();;
         } 

         //:END
         //:IF lTrnscpt.TG_CollegeTerm EXISTS
         lTempInteger_14 = CheckExistenceOfEntity( lTrnscpt, "TG_CollegeTerm" );
         if ( lTempInteger_14 == 0 )
         { 
            //:IF lTrnscpt.TG_CollegeTerm.TranscriptTermDisplay = ""
            if ( CompareAttributeToString( lTrnscpt, "TG_CollegeTerm", "TranscriptTermDisplay", "" ) == 0 )
            { 
               //:lTrnscpt.TranscriptGroup.YearSemester   = lTrnscpt.TG_CollegeTerm.YearSemester
               SetAttributeFromAttribute( lTrnscpt, "TranscriptGroup", "YearSemester", lTrnscpt, "TG_CollegeTerm", "YearSemester" );
               //:ELSE
            } 
            else
            { 
               //:lTrnscpt.TranscriptGroup.YearSemester   = lTrnscpt.TG_CollegeTerm.TranscriptTermDisplay
               SetAttributeFromAttribute( lTrnscpt, "TranscriptGroup", "YearSemester", lTrnscpt, "TG_CollegeTerm", "TranscriptTermDisplay" );
            } 

            //:END
            //:lTrnscpt.TranscriptGroup.Semester       = lTrnscpt.TG_CollegeTerm.Semester 
            SetAttributeFromAttribute( lTrnscpt, "TranscriptGroup", "Semester", lTrnscpt, "TG_CollegeTerm", "Semester" );
            //:lTrnscpt.TranscriptGroup.wCollegeYear   = lTrnscpt.TG_CollegeYear.Year
            SetAttributeFromAttribute( lTrnscpt, "TranscriptGroup", "wCollegeYear", lTrnscpt, "TG_CollegeYear", "Year" );
            //:lTrnscpt.TranscriptGroup.wSortOrder     = "2"   // Transfer entries are ordered after Regular entries.
            SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wSortOrder", "2" );
            //:lTrnscpt.TranscriptGroup.wRecordType    = "T"
            SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wRecordType", "T" );
            //:ELSE
         } 
         else
         { 
            //:// For sorting, create a dummy wCollegeYear.
            //:TransferGroupCount = TransferGroupCount + 1
            TransferGroupCount = TransferGroupCount + 1;
            //:szTransferGroupCount = TransferGroupCount
             {StringBuilder sb_szTransferGroupCount;
            if ( szTransferGroupCount == null )
               sb_szTransferGroupCount = new StringBuilder( 32 );
            else
               sb_szTransferGroupCount = new StringBuilder( szTransferGroupCount );
                        ZeidonStringConvertFromNumber( sb_szTransferGroupCount, 1, 0, 2, TransferGroupCount, (double) 0.0, "I" );
            szTransferGroupCount = sb_szTransferGroupCount.toString( );}
            //:lTrnscpt.TranscriptGroup.wCollegeYear = " " + szTransferGroupCount
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringCopy( sb_szTempString_2, 1, 0, " ", 1, 0, 11 );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                        ZeidonStringConcat( sb_szTempString_2, 1, 0, szTransferGroupCount, 1, 0, 11 );
            szTempString_2 = sb_szTempString_2.toString( );}
            SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wCollegeYear", szTempString_2 );
         } 

         //:END

         //:FOR EACH lTrnscpt.TG_Registration WHERE lTrnscpt.TG_Registration.Status != "N" AND lTrnscpt.TG_Registration.Status != "P"
         RESULT = lTrnscpt.cursor( "TG_Registration" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( lTrnscpt, "TG_Registration", "Status", "N" ) != 0 && CompareAttributeToString( lTrnscpt, "TG_Registration", "Status", "P" ) != 0 )
            { 
               //:SET CURSOR FIRST lTrnscpt.Registration 
               //:        WHERE lTrnscpt.Registration.ID = lTrnscpt.TG_Registration.ID 
               {MutableInt mi_lTempInteger_15 = new MutableInt( lTempInteger_15 );
                               GetIntegerFromAttribute( mi_lTempInteger_15, lTrnscpt, "TG_Registration", "ID" );
               lTempInteger_15 = mi_lTempInteger_15.intValue( );}
               RESULT = lTrnscpt.cursor( "Registration" ).setFirst( "ID", lTempInteger_15 ).toInt();
               //:IF RESULT >= zCURSOR_SET 
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:INCLUDE lTrnscpt.TermRegistration FROM lTrnscpt.Registration 
                  RESULT = IncludeSubobjectFromSubobject( lTrnscpt, "TermRegistration", lTrnscpt, "Registration", zPOS_AFTER );
                  //:IF lTrnscpt.EquivalentCourse EXISTS
                  lTempInteger_16 = CheckExistenceOfEntity( lTrnscpt, "EquivalentCourse" );
                  if ( lTempInteger_16 == 0 )
                  { 
                     //:lTrnscpt.TermRegistration.wCourseNumber = lTrnscpt.EquivalentCourse.Number 
                     SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCourseNumber", lTrnscpt, "EquivalentCourse", "Number" );
                  } 

                  //:END
                  //:IF lTrnscpt.TG_Registration.Status = "V"
                  if ( CompareAttributeToString( lTrnscpt, "TG_Registration", "Status", "V" ) == 0 )
                  { 
                     //:lTrnscpt.TermRegistration.wCourseTitle = lTrnscpt.TG_EquivalentCourse.Title 
                     SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCourseTitle", lTrnscpt, "TG_EquivalentCourse", "Title" );
                     //:ELSE
                  } 
                  else
                  { 
                     //:lTrnscpt.TermRegistration.wCourseTitle = lTrnscpt.Registration.ForeignClassName 
                     SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCourseTitle", lTrnscpt, "Registration", "ForeignClassName" );
                  } 

                  //:END
                  //:lTrnscpt.TermRegistration.wRepeatedClass              = lTrnscpt.Registration.wRepeatedClass
                  SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wRepeatedClass", lTrnscpt, "Registration", "wRepeatedClass" );
                  //:lTrnscpt.TermRegistration.wReplacedAttemptedCredits   = lTrnscpt.Registration.wReplacedAttemptedCredits 
                  SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedAttemptedCredits", lTrnscpt, "Registration", "wReplacedAttemptedCredits" );
                  //:lTrnscpt.TermRegistration.wReplacedEarnedCredits      = lTrnscpt.Registration.wReplacedEarnedCredits 
                  SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedEarnedCredits", lTrnscpt, "Registration", "wReplacedEarnedCredits" );
                  //:lTrnscpt.TermRegistration.wReplacedGPA_DivisorCredits = lTrnscpt.Registration.wReplacedGPA_DivisorCredits
                  SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedGPA_DivisorCredits", lTrnscpt, "Registration", "wReplacedGPA_DivisorCredits" );
                  //:lTrnscpt.TermRegistration.wReplacedGPA_Points         = lTrnscpt.Registration.wReplacedGPA_Points 
                  SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedGPA_Points", lTrnscpt, "Registration", "wReplacedGPA_Points" );
               } 

            } 

            RESULT = lTrnscpt.cursor( "TG_Registration" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
      } 

      RESULT = lTrnscpt.cursor( "TransferGroup" ).setNextContinue().toInt();;
      //:END
   } 


   //:END

   //:// Initialize Cumulative Values.
   //://CumulativeAttemptedCredits = 0
   //://CumulativeEarnedCredits    = 0
   //://CumulativeGPA_Points       = 0
   //://CumulativeGPA_Divisor      = 0

   //:// Process the Registration entries.
   //:FOR EACH lTrnscpt.Registration 
   //:          WHERE  lTrnscpt.Registration.Status = "C"   // Completed
   //:             OR  lTrnscpt.Registration.Status = "W"   // Withdrawn
   RESULT = lTrnscpt.cursor( "Registration" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( lTrnscpt, "Registration", "Status", "C" ) == 0 || CompareAttributeToString( lTrnscpt, "Registration", "Status", "W" ) == 0 )
      { 

         //:IF lTrnscpt.RegistrationCohort EXISTS AND lTrnscpt.RegisteredCollegeTerm DOES NOT EXIST
         lTempInteger_17 = CheckExistenceOfEntity( lTrnscpt, "RegistrationCohort" );
         lTempInteger_18 = CheckExistenceOfEntity( lTrnscpt, "RegisteredCollegeTerm" );
         if ( lTempInteger_17 == 0 && lTempInteger_18 != 0 )
         { 

            //:// COHORT ENTRY
            //:IF lTrnscpt.Registration.Status = "W"
            if ( CompareAttributeToString( lTrnscpt, "Registration", "Status", "W" ) == 0 )
            { 
               //:// Withdrawn
               //:SET CURSOR FIRST lTrnscpt.TranscriptGroup 
               //:        WHERE lTrnscpt.TranscriptGroup.wCollegeYear = lTrnscpt.Registration.wCompletedClassYear 
               //:          AND lTrnscpt.TranscriptGroup.wRecordType = "C"
               RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setFirst().toInt();
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lTrnscpt, "TranscriptGroup", "wCollegeYear", lTrnscpt, "Registration", "wCompletedClassYear" ) != 0 ||
                        CompareAttributeToString( lTrnscpt, "TranscriptGroup", "wRecordType", "C" ) != 0 ) )
                  { 
                     RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setNextContinue().toInt();;
                  } 

               } 

               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:CREATE ENTITY lTrnscpt.TranscriptGroup 
                  RESULT = CreateEntity( lTrnscpt, "TranscriptGroup", zPOS_AFTER );
                  //:lTrnscpt.TranscriptGroup.YearSemester   = lTrnscpt.Registration.wCompletedClassYear
                  SetAttributeFromAttribute( lTrnscpt, "TranscriptGroup", "YearSemester", lTrnscpt, "Registration", "wCompletedClassYear" );
                  //:lTrnscpt.TranscriptGroup.Semester       = ""
                  SetAttributeFromString( lTrnscpt, "TranscriptGroup", "Semester", "" );
                  //:lTrnscpt.TranscriptGroup.wCollegeYear   = lTrnscpt.Registration.wCompletedClassYear
                  SetAttributeFromAttribute( lTrnscpt, "TranscriptGroup", "wCollegeYear", lTrnscpt, "Registration", "wCompletedClassYear" );
                  //:lTrnscpt.TranscriptGroup.wSortOrder     = "0"   // Cohort entries are ordered before both Regular and Transfer entries.
                  SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wSortOrder", "0" );
                  //:lTrnscpt.TranscriptGroup.wRecordType    = "C"
                  SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wRecordType", "C" );
               } 

               //:END
               //:SET CURSOR LAST lTrnscpt.TermRegistration
               RESULT = lTrnscpt.cursor( "TermRegistration" ).setLast().toInt();;
               //:INCLUDE lTrnscpt.TermRegistration FROM lTrnscpt.Registration
               RESULT = IncludeSubobjectFromSubobject( lTrnscpt, "TermRegistration", lTrnscpt, "Registration", zPOS_AFTER );
               //:lTrnscpt.TermRegistration.wCohortClassStartDate = lTrnscpt.RegistrationClass.ClassStartDate 
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCohortClassStartDate", lTrnscpt, "RegistrationClass", "ClassStartDate" );
               //:lTrnscpt.TermRegistration.wCourseNumber         = lTrnscpt.Registration.wCourseNumber 
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCourseNumber", lTrnscpt, "Registration", "wCourseNumber" );
               //:lTrnscpt.TermRegistration.wCourseTitle          = lTrnscpt.Registration.wCourseTitle
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCourseTitle", lTrnscpt, "Registration", "wCourseTitle" );
               //:lTrnscpt.TermRegistration.FinalGrade            = "W"
               SetAttributeFromString( lTrnscpt, "TermRegistration", "FinalGrade", "W" );
               //:// Only Attempted Credits are totaled.
               //:lTrnscpt.TermRegistration.wRepeatedClass              = lTrnscpt.Registration.wRepeatedClass
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wRepeatedClass", lTrnscpt, "Registration", "wRepeatedClass" );
               //:lTrnscpt.TermRegistration.wReplacedAttemptedCredits   = lTrnscpt.Registration.wReplacedAttemptedCredits 
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedAttemptedCredits", lTrnscpt, "Registration", "wReplacedAttemptedCredits" );
               //:ELSE
            } 
            else
            { 
               //:// Completed
               //:SET CURSOR FIRST lTrnscpt.TranscriptGroup 
               //:        WHERE lTrnscpt.TranscriptGroup.wCollegeYear = lTrnscpt.Registration.wCompletedClassYear 
               //:          AND lTrnscpt.TranscriptGroup.wRecordType = "C"
               RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setFirst().toInt();
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lTrnscpt, "TranscriptGroup", "wCollegeYear", lTrnscpt, "Registration", "wCompletedClassYear" ) != 0 ||
                        CompareAttributeToString( lTrnscpt, "TranscriptGroup", "wRecordType", "C" ) != 0 ) )
                  { 
                     RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setNextContinue().toInt();;
                  } 

               } 

               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:CREATE ENTITY lTrnscpt.TranscriptGroup 
                  RESULT = CreateEntity( lTrnscpt, "TranscriptGroup", zPOS_AFTER );
                  //:lTrnscpt.TranscriptGroup.YearSemester   = lTrnscpt.Registration.wCompletedClassYear
                  SetAttributeFromAttribute( lTrnscpt, "TranscriptGroup", "YearSemester", lTrnscpt, "Registration", "wCompletedClassYear" );
                  //:lTrnscpt.TranscriptGroup.Semester       = ""
                  SetAttributeFromString( lTrnscpt, "TranscriptGroup", "Semester", "" );
                  //:lTrnscpt.TranscriptGroup.wCollegeYear   = lTrnscpt.Registration.wCompletedClassYear
                  SetAttributeFromAttribute( lTrnscpt, "TranscriptGroup", "wCollegeYear", lTrnscpt, "Registration", "wCompletedClassYear" );
                  //:lTrnscpt.TranscriptGroup.wSortOrder     = "1"   // Cohort entries are ordered before both Regular and Transfer entries.
                  SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wSortOrder", "1" );
                  //:lTrnscpt.TranscriptGroup.wRecordType    = "C"
                  SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wRecordType", "C" );
               } 

               //:END
               //:SET CURSOR LAST lTrnscpt.TermRegistration
               RESULT = lTrnscpt.cursor( "TermRegistration" ).setLast().toInt();;
               //:INCLUDE lTrnscpt.TermRegistration FROM lTrnscpt.Registration
               RESULT = IncludeSubobjectFromSubobject( lTrnscpt, "TermRegistration", lTrnscpt, "Registration", zPOS_AFTER );
               //:lTrnscpt.TermRegistration.wCohortClassStartDate = lTrnscpt.RegistrationClass.ClassStartDate
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCohortClassStartDate", lTrnscpt, "RegistrationClass", "ClassStartDate" );
               //:lTrnscpt.TermRegistration.wCourseNumber         = lTrnscpt.Registration.wCourseNumber 
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCourseNumber", lTrnscpt, "Registration", "wCourseNumber" );
               //:IF lTrnscpt.TermRegistration.TakingClassType    = "A"
               if ( CompareAttributeToString( lTrnscpt, "TermRegistration", "TakingClassType", "A" ) == 0 )
               { 
                  //:lTrnscpt.TermRegistration.wCourseTitle = lTrnscpt.Registration.wCourseTitle + " (Audited)"
                  {StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                     GetStringFromAttribute( sb_szTempString_3, lTrnscpt, "Registration", "wCourseTitle" );
                  szTempString_3 = sb_szTempString_3.toString( );}
                   {StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                    ZeidonStringConcat( sb_szTempString_3, 1, 0, " (Audited)", 1, 0, 255 );
                  szTempString_3 = sb_szTempString_3.toString( );}
                  SetAttributeFromString( lTrnscpt, "TermRegistration", "wCourseTitle", szTempString_3 );
                  //:ELSE
               } 
               else
               { 
                  //:IF lTrnscpt.TermRegistration.TakingClassType = "H" OR lTrnscpt.TermClass.HonorsFlag = "Y"
                  if ( CompareAttributeToString( lTrnscpt, "TermRegistration", "TakingClassType", "H" ) == 0 || CompareAttributeToString( lTrnscpt, "TermClass", "HonorsFlag", "Y" ) == 0 )
                  { 
                     //:lTrnscpt.TermRegistration.wCourseTitle = lTrnscpt.Registration.wCourseTitle + " (Honors)"
                     {StringBuilder sb_szTempString_4;
                     if ( szTempString_4 == null )
                        sb_szTempString_4 = new StringBuilder( 32 );
                     else
                        sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                           GetStringFromAttribute( sb_szTempString_4, lTrnscpt, "Registration", "wCourseTitle" );
                     szTempString_4 = sb_szTempString_4.toString( );}
                      {StringBuilder sb_szTempString_4;
                     if ( szTempString_4 == null )
                        sb_szTempString_4 = new StringBuilder( 32 );
                     else
                        sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                          ZeidonStringConcat( sb_szTempString_4, 1, 0, " (Honors)", 1, 0, 255 );
                     szTempString_4 = sb_szTempString_4.toString( );}
                     SetAttributeFromString( lTrnscpt, "TermRegistration", "wCourseTitle", szTempString_4 );
                     //:ELSE
                  } 
                  else
                  { 
                     //:lTrnscpt.TermRegistration.wCourseTitle = lTrnscpt.Registration.wCourseTitle
                     SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCourseTitle", lTrnscpt, "Registration", "wCourseTitle" );
                  } 

                  //:END
               } 

               //:END
               //:lTrnscpt.TermRegistration.wRepeatedClass              = lTrnscpt.Registration.wRepeatedClass
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wRepeatedClass", lTrnscpt, "Registration", "wRepeatedClass" );
               //:lTrnscpt.TermRegistration.wReplacedAttemptedCredits   = lTrnscpt.Registration.wReplacedAttemptedCredits 
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedAttemptedCredits", lTrnscpt, "Registration", "wReplacedAttemptedCredits" );
               //:lTrnscpt.TermRegistration.wReplacedEarnedCredits      = lTrnscpt.Registration.wReplacedEarnedCredits 
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedEarnedCredits", lTrnscpt, "Registration", "wReplacedEarnedCredits" );
               //:lTrnscpt.TermRegistration.wReplacedGPA_DivisorCredits = lTrnscpt.Registration.wReplacedGPA_DivisorCredits
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedGPA_DivisorCredits", lTrnscpt, "Registration", "wReplacedGPA_DivisorCredits" );
               //:lTrnscpt.TermRegistration.wReplacedGPA_Points         = lTrnscpt.Registration.wReplacedGPA_Points 
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedGPA_Points", lTrnscpt, "Registration", "wReplacedGPA_Points" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 

            //:// COLLEGE TERM ENTRY

            //:// Set College Term and Year values base on whether a Term override is specified.
            //:IF lTrnscpt.RegisteredCollegeTerm EXISTS
            lTempInteger_19 = CheckExistenceOfEntity( lTrnscpt, "RegisteredCollegeTerm" );
            if ( lTempInteger_19 == 0 )
            { 
               //:ClassCollegeTermID       = lTrnscpt.RegisteredCollegeTerm.ID 
               {MutableInt mi_ClassCollegeTermID = new MutableInt( ClassCollegeTermID );
                               GetIntegerFromAttribute( mi_ClassCollegeTermID, lTrnscpt, "RegisteredCollegeTerm", "ID" );
               ClassCollegeTermID = mi_ClassCollegeTermID.intValue( );}
               //:ClassCollegeYear         = lTrnscpt.RegisteredCollegeYear.Year 
               {MutableInt mi_lTempInteger_20 = new MutableInt( lTempInteger_20 );
               StringBuilder sb_ClassCollegeYear;
               if ( ClassCollegeYear == null )
                  sb_ClassCollegeYear = new StringBuilder( 32 );
               else
                  sb_ClassCollegeYear = new StringBuilder( ClassCollegeYear );
                               GetVariableFromAttribute( sb_ClassCollegeYear, mi_lTempInteger_20, 'S', 11, lTrnscpt, "RegisteredCollegeYear", "Year", "", 0 );
               lTempInteger_20 = mi_lTempInteger_20.intValue( );
               ClassCollegeYear = sb_ClassCollegeYear.toString( );}
               //:ClassCollegeYearSemester = lTrnscpt.RegisteredCollegeTerm.YearSemester
               {MutableInt mi_lTempInteger_21 = new MutableInt( lTempInteger_21 );
               StringBuilder sb_ClassCollegeYearSemester;
               if ( ClassCollegeYearSemester == null )
                  sb_ClassCollegeYearSemester = new StringBuilder( 32 );
               else
                  sb_ClassCollegeYearSemester = new StringBuilder( ClassCollegeYearSemester );
                               GetVariableFromAttribute( sb_ClassCollegeYearSemester, mi_lTempInteger_21, 'S', 21, lTrnscpt, "RegisteredCollegeTerm", "YearSemester", "", 0 );
               lTempInteger_21 = mi_lTempInteger_21.intValue( );
               ClassCollegeYearSemester = sb_ClassCollegeYearSemester.toString( );}
               //:ClassCollegeTermDisplay  = lTrnscpt.RegisteredCollegeTerm.TranscriptTermDisplay
               {MutableInt mi_lTempInteger_22 = new MutableInt( lTempInteger_22 );
               StringBuilder sb_ClassCollegeTermDisplay;
               if ( ClassCollegeTermDisplay == null )
                  sb_ClassCollegeTermDisplay = new StringBuilder( 32 );
               else
                  sb_ClassCollegeTermDisplay = new StringBuilder( ClassCollegeTermDisplay );
                               GetVariableFromAttribute( sb_ClassCollegeTermDisplay, mi_lTempInteger_22, 'S', 21, lTrnscpt, "RegisteredCollegeTerm", "TranscriptTermDisplay", "", 0 );
               lTempInteger_22 = mi_lTempInteger_22.intValue( );
               ClassCollegeTermDisplay = sb_ClassCollegeTermDisplay.toString( );}
               //:ClassCollegeTermSemester = lTrnscpt.RegisteredCollegeTerm.Semester
               {MutableInt mi_lTempInteger_23 = new MutableInt( lTempInteger_23 );
               StringBuilder sb_ClassCollegeTermSemester;
               if ( ClassCollegeTermSemester == null )
                  sb_ClassCollegeTermSemester = new StringBuilder( 32 );
               else
                  sb_ClassCollegeTermSemester = new StringBuilder( ClassCollegeTermSemester );
                               GetVariableFromAttribute( sb_ClassCollegeTermSemester, mi_lTempInteger_23, 'S', 3, lTrnscpt, "RegisteredCollegeTerm", "Semester", "", 0 );
               lTempInteger_23 = mi_lTempInteger_23.intValue( );
               ClassCollegeTermSemester = sb_ClassCollegeTermSemester.toString( );}
               //:ELSE
            } 
            else
            { 
               //:ClassCollegeTermID       = lTrnscpt.RegistrationClassCollegeTerm.ID
               {MutableInt mi_ClassCollegeTermID = new MutableInt( ClassCollegeTermID );
                               GetIntegerFromAttribute( mi_ClassCollegeTermID, lTrnscpt, "RegistrationClassCollegeTerm", "ID" );
               ClassCollegeTermID = mi_ClassCollegeTermID.intValue( );}
               //:ClassCollegeYear         = lTrnscpt.RegistrationCollegeYear.Year
               {MutableInt mi_lTempInteger_24 = new MutableInt( lTempInteger_24 );
               StringBuilder sb_ClassCollegeYear;
               if ( ClassCollegeYear == null )
                  sb_ClassCollegeYear = new StringBuilder( 32 );
               else
                  sb_ClassCollegeYear = new StringBuilder( ClassCollegeYear );
                               GetVariableFromAttribute( sb_ClassCollegeYear, mi_lTempInteger_24, 'S', 11, lTrnscpt, "RegistrationCollegeYear", "Year", "", 0 );
               lTempInteger_24 = mi_lTempInteger_24.intValue( );
               ClassCollegeYear = sb_ClassCollegeYear.toString( );}
               //:ClassCollegeYearSemester = lTrnscpt.RegistrationClassCollegeTerm.YearSemester
               {MutableInt mi_lTempInteger_25 = new MutableInt( lTempInteger_25 );
               StringBuilder sb_ClassCollegeYearSemester;
               if ( ClassCollegeYearSemester == null )
                  sb_ClassCollegeYearSemester = new StringBuilder( 32 );
               else
                  sb_ClassCollegeYearSemester = new StringBuilder( ClassCollegeYearSemester );
                               GetVariableFromAttribute( sb_ClassCollegeYearSemester, mi_lTempInteger_25, 'S', 21, lTrnscpt, "RegistrationClassCollegeTerm", "YearSemester", "", 0 );
               lTempInteger_25 = mi_lTempInteger_25.intValue( );
               ClassCollegeYearSemester = sb_ClassCollegeYearSemester.toString( );}
               //:ClassCollegeTermDisplay  = lTrnscpt.RegistrationClassCollegeTerm.TranscriptTermDisplay
               {MutableInt mi_lTempInteger_26 = new MutableInt( lTempInteger_26 );
               StringBuilder sb_ClassCollegeTermDisplay;
               if ( ClassCollegeTermDisplay == null )
                  sb_ClassCollegeTermDisplay = new StringBuilder( 32 );
               else
                  sb_ClassCollegeTermDisplay = new StringBuilder( ClassCollegeTermDisplay );
                               GetVariableFromAttribute( sb_ClassCollegeTermDisplay, mi_lTempInteger_26, 'S', 21, lTrnscpt, "RegistrationClassCollegeTerm", "TranscriptTermDisplay", "", 0 );
               lTempInteger_26 = mi_lTempInteger_26.intValue( );
               ClassCollegeTermDisplay = sb_ClassCollegeTermDisplay.toString( );}
               //:ClassCollegeTermSemester = lTrnscpt.RegistrationClassCollegeTerm.Semester
               {MutableInt mi_lTempInteger_27 = new MutableInt( lTempInteger_27 );
               StringBuilder sb_ClassCollegeTermSemester;
               if ( ClassCollegeTermSemester == null )
                  sb_ClassCollegeTermSemester = new StringBuilder( 32 );
               else
                  sb_ClassCollegeTermSemester = new StringBuilder( ClassCollegeTermSemester );
                               GetVariableFromAttribute( sb_ClassCollegeTermSemester, mi_lTempInteger_27, 'S', 3, lTrnscpt, "RegistrationClassCollegeTerm", "Semester", "", 0 );
               lTempInteger_27 = mi_lTempInteger_27.intValue( );
               ClassCollegeTermSemester = sb_ClassCollegeTermSemester.toString( );}
            } 

            //:END
            //:IF lTrnscpt.Registration.Status = "W"
            if ( CompareAttributeToString( lTrnscpt, "Registration", "Status", "W" ) == 0 )
            { 
               //:// Withdrawn
               //:SET CURSOR FIRST lTrnscpt.TranscriptGroup 
               //:        WHERE lTrnscpt.TranscriptGroup.wTranscriptGroupID = ClassCollegeTermID 
               //:          AND lTrnscpt.TranscriptGroup.wRecordType = "R"
               RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setFirst().toInt();
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( lTrnscpt, "TranscriptGroup", "wTranscriptGroupID", ClassCollegeTermID ) != 0 || CompareAttributeToString( lTrnscpt, "TranscriptGroup", "wRecordType", "R" ) != 0 ) )
                  { 
                     RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setNextContinue().toInt();;
                  } 

               } 

               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:CREATE ENTITY lTrnscpt.TranscriptGroup 
                  RESULT = CreateEntity( lTrnscpt, "TranscriptGroup", zPOS_AFTER );
                  //:IF ClassCollegeTermDisplay = ""
                  if ( ZeidonStringCompare( ClassCollegeTermDisplay, 1, 0, "", 1, 0, 21 ) == 0 )
                  { 
                     //:lTrnscpt.TranscriptGroup.YearSemester = ClassCollegeYearSemester
                     SetAttributeFromString( lTrnscpt, "TranscriptGroup", "YearSemester", ClassCollegeYearSemester );
                     //:ELSE
                  } 
                  else
                  { 
                     //:lTrnscpt.TranscriptGroup.YearSemester = ClassCollegeTermDisplay
                     SetAttributeFromString( lTrnscpt, "TranscriptGroup", "YearSemester", ClassCollegeTermDisplay );
                  } 

                  //:END
                  //:lTrnscpt.TranscriptGroup.Semester       = ClassCollegeTermSemester 
                  SetAttributeFromString( lTrnscpt, "TranscriptGroup", "Semester", ClassCollegeTermSemester );
                  //:lTrnscpt.TranscriptGroup.wTranscriptGroupID = ClassCollegeTermID 
                  SetAttributeFromInteger( lTrnscpt, "TranscriptGroup", "wTranscriptGroupID", ClassCollegeTermID );
                  //:lTrnscpt.TranscriptGroup.wCollegeYear   = ClassCollegeYear
                  SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wCollegeYear", ClassCollegeYear );
                  //:lTrnscpt.TranscriptGroup.wSortOrder     = "1"   // Regular entries are ordered before Transfer entries.
                  SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wSortOrder", "1" );
                  //:lTrnscpt.TranscriptGroup.wRecordType    = "R"
                  SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wRecordType", "R" );
               } 

               //:END
               //:SET CURSOR LAST lTrnscpt.TermRegistration
               RESULT = lTrnscpt.cursor( "TermRegistration" ).setLast().toInt();;
               //:INCLUDE lTrnscpt.TermRegistration FROM lTrnscpt.Registration
               RESULT = IncludeSubobjectFromSubobject( lTrnscpt, "TermRegistration", lTrnscpt, "Registration", zPOS_AFTER );
               //:lTrnscpt.TermRegistration.wCourseNumber = lTrnscpt.Registration.wCourseNumber 
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCourseNumber", lTrnscpt, "Registration", "wCourseNumber" );
               //:lTrnscpt.TermRegistration.wCourseTitle  = lTrnscpt.Registration.wCourseTitle
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCourseTitle", lTrnscpt, "Registration", "wCourseTitle" );
               //:lTrnscpt.TermRegistration.FinalGrade = "W"
               SetAttributeFromString( lTrnscpt, "TermRegistration", "FinalGrade", "W" );
               //:// Only Attempted Credits are totaled.
               //:lTrnscpt.TermRegistration.wRepeatedClass              = lTrnscpt.Registration.wRepeatedClass
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wRepeatedClass", lTrnscpt, "Registration", "wRepeatedClass" );
               //:lTrnscpt.TermRegistration.wReplacedAttemptedCredits   = lTrnscpt.Registration.wReplacedAttemptedCredits
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedAttemptedCredits", lTrnscpt, "Registration", "wReplacedAttemptedCredits" );
               //:ELSE
            } 
            else
            { 
               //:// Completed
               //:SET CURSOR FIRST lTrnscpt.TranscriptGroup 
               //:        WHERE lTrnscpt.TranscriptGroup.wTranscriptGroupID = ClassCollegeTermID
               //:          AND lTrnscpt.TranscriptGroup.wRecordType = "R"
               RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setFirst().toInt();
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToInteger( lTrnscpt, "TranscriptGroup", "wTranscriptGroupID", ClassCollegeTermID ) != 0 || CompareAttributeToString( lTrnscpt, "TranscriptGroup", "wRecordType", "R" ) != 0 ) )
                  { 
                     RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setNextContinue().toInt();;
                  } 

               } 

               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:CREATE ENTITY lTrnscpt.TranscriptGroup 
                  RESULT = CreateEntity( lTrnscpt, "TranscriptGroup", zPOS_AFTER );
                  //:IF ClassCollegeTermDisplay = ""
                  if ( ZeidonStringCompare( ClassCollegeTermDisplay, 1, 0, "", 1, 0, 21 ) == 0 )
                  { 
                     //:lTrnscpt.TranscriptGroup.YearSemester = ClassCollegeYearSemester
                     SetAttributeFromString( lTrnscpt, "TranscriptGroup", "YearSemester", ClassCollegeYearSemester );
                     //:ELSE
                  } 
                  else
                  { 
                     //:lTrnscpt.TranscriptGroup.YearSemester = ClassCollegeTermDisplay
                     SetAttributeFromString( lTrnscpt, "TranscriptGroup", "YearSemester", ClassCollegeTermDisplay );
                  } 

                  //:END
                  //:lTrnscpt.TranscriptGroup.Semester       = ClassCollegeTermSemester
                  SetAttributeFromString( lTrnscpt, "TranscriptGroup", "Semester", ClassCollegeTermSemester );
                  //:lTrnscpt.TranscriptGroup.wTranscriptGroupID = ClassCollegeTermID 
                  SetAttributeFromInteger( lTrnscpt, "TranscriptGroup", "wTranscriptGroupID", ClassCollegeTermID );
                  //:lTrnscpt.TranscriptGroup.wCollegeYear   = ClassCollegeYear
                  SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wCollegeYear", ClassCollegeYear );
                  //:lTrnscpt.TranscriptGroup.wSortOrder     = "1"   // Regular entries are ordered before Transfer entries.
                  SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wSortOrder", "1" );
                  //:lTrnscpt.TranscriptGroup.wRecordType    = "R"
                  SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wRecordType", "R" );
               } 

               //:END
               //:SET CURSOR LAST lTrnscpt.TermRegistration
               RESULT = lTrnscpt.cursor( "TermRegistration" ).setLast().toInt();;
               //:INCLUDE lTrnscpt.TermRegistration FROM lTrnscpt.Registration
               RESULT = IncludeSubobjectFromSubobject( lTrnscpt, "TermRegistration", lTrnscpt, "Registration", zPOS_AFTER );
               //:lTrnscpt.TermRegistration.wCourseNumber = lTrnscpt.Registration.wCourseNumber 
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCourseNumber", lTrnscpt, "Registration", "wCourseNumber" );
               //:IF lTrnscpt.TermRegistration.TakingClassType = "A"
               if ( CompareAttributeToString( lTrnscpt, "TermRegistration", "TakingClassType", "A" ) == 0 )
               { 
                  //:lTrnscpt.TermRegistration.wCourseTitle = lTrnscpt.Registration.wCourseTitle + " (Audited)"
                  {StringBuilder sb_szTempString_5;
                  if ( szTempString_5 == null )
                     sb_szTempString_5 = new StringBuilder( 32 );
                  else
                     sb_szTempString_5 = new StringBuilder( szTempString_5 );
                                     GetStringFromAttribute( sb_szTempString_5, lTrnscpt, "Registration", "wCourseTitle" );
                  szTempString_5 = sb_szTempString_5.toString( );}
                   {StringBuilder sb_szTempString_5;
                  if ( szTempString_5 == null )
                     sb_szTempString_5 = new StringBuilder( 32 );
                  else
                     sb_szTempString_5 = new StringBuilder( szTempString_5 );
                                    ZeidonStringConcat( sb_szTempString_5, 1, 0, " (Audited)", 1, 0, 255 );
                  szTempString_5 = sb_szTempString_5.toString( );}
                  SetAttributeFromString( lTrnscpt, "TermRegistration", "wCourseTitle", szTempString_5 );
                  //:ELSE
               } 
               else
               { 
                  //:IF lTrnscpt.TermRegistration.TakingClassType = "H" OR lTrnscpt.TermClass.HonorsFlag = "Y"
                  if ( CompareAttributeToString( lTrnscpt, "TermRegistration", "TakingClassType", "H" ) == 0 || CompareAttributeToString( lTrnscpt, "TermClass", "HonorsFlag", "Y" ) == 0 )
                  { 
                     //:lTrnscpt.TermRegistration.wCourseTitle = lTrnscpt.Registration.wCourseTitle + " (Honors)"
                     {StringBuilder sb_szTempString_6;
                     if ( szTempString_6 == null )
                        sb_szTempString_6 = new StringBuilder( 32 );
                     else
                        sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                           GetStringFromAttribute( sb_szTempString_6, lTrnscpt, "Registration", "wCourseTitle" );
                     szTempString_6 = sb_szTempString_6.toString( );}
                      {StringBuilder sb_szTempString_6;
                     if ( szTempString_6 == null )
                        sb_szTempString_6 = new StringBuilder( 32 );
                     else
                        sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                          ZeidonStringConcat( sb_szTempString_6, 1, 0, " (Honors)", 1, 0, 255 );
                     szTempString_6 = sb_szTempString_6.toString( );}
                     SetAttributeFromString( lTrnscpt, "TermRegistration", "wCourseTitle", szTempString_6 );
                     //:ELSE
                  } 
                  else
                  { 
                     //:lTrnscpt.TermRegistration.wCourseTitle = lTrnscpt.Registration.wCourseTitle
                     SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wCourseTitle", lTrnscpt, "Registration", "wCourseTitle" );
                  } 

                  //:END
               } 

               //:END
               //:lTrnscpt.TermRegistration.wRepeatedClass              = lTrnscpt.Registration.wRepeatedClass
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wRepeatedClass", lTrnscpt, "Registration", "wRepeatedClass" );
               //:lTrnscpt.TermRegistration.wReplacedAttemptedCredits   = lTrnscpt.Registration.wReplacedAttemptedCredits 
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedAttemptedCredits", lTrnscpt, "Registration", "wReplacedAttemptedCredits" );
               //:lTrnscpt.TermRegistration.wReplacedEarnedCredits      = lTrnscpt.Registration.wReplacedEarnedCredits 
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedEarnedCredits", lTrnscpt, "Registration", "wReplacedEarnedCredits" );
               //:lTrnscpt.TermRegistration.wReplacedGPA_DivisorCredits = lTrnscpt.Registration.wReplacedGPA_DivisorCredits
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedGPA_DivisorCredits", lTrnscpt, "Registration", "wReplacedGPA_DivisorCredits" );
               //:lTrnscpt.TermRegistration.wReplacedGPA_Points         = lTrnscpt.Registration.wReplacedGPA_Points 
               SetAttributeFromAttribute( lTrnscpt, "TermRegistration", "wReplacedGPA_Points", lTrnscpt, "Registration", "wReplacedGPA_Points" );
            } 

            //:END
         } 

      } 

      RESULT = lTrnscpt.cursor( "Registration" ).setNextContinue().toInt();;
      //:END
   } 


   //:END

   //:// Order the Terms and Registration entries.
   //:OrderEntityForView( lTrnscpt, "TranscriptGroup", "wCollegeYear A Semester A wLegacyTerm A wSortOrder A" )
   OrderEntityForView( lTrnscpt, "TranscriptGroup", "wCollegeYear A Semester A wLegacyTerm A wSortOrder A" );
   //:FOR EACH lTrnscpt.TranscriptGroup WHERE lTrnscpt.TranscriptGroup.wRecordType = "R" OR
   //:                                        lTrnscpt.TranscriptGroup.wRecordType = "C"
   RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( lTrnscpt, "TranscriptGroup", "wRecordType", "R" ) == 0 || CompareAttributeToString( lTrnscpt, "TranscriptGroup", "wRecordType", "C" ) == 0 )
      { 
         //:IF lTrnscpt.TranscriptGroup.wRecordType = "C"
         if ( CompareAttributeToString( lTrnscpt, "TranscriptGroup", "wRecordType", "C" ) == 0 )
         { 
            //:// Cohort entries are sorted by Class date and then by Course.
            //:OrderEntityForView( lTrnscpt, "TermRegistration", "wCourseNumber A wCohortClassStartDate A" )
            OrderEntityForView( lTrnscpt, "TermRegistration", "wCourseNumber A wCohortClassStartDate A" );
            //:ELSE
         } 
         else
         { 
            //:// Regular entries are sorted by Course.
            //:OrderEntityForView( lTrnscpt, "TermRegistration", "wCourseNumber A" )
            OrderEntityForView( lTrnscpt, "TermRegistration", "wCourseNumber A" );
         } 

      } 

      RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:// Eliminate any Groups that only have Pre-Transfer entries.
   //:FOR EACH lTrnscpt.TranscriptGroup
   RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST lTrnscpt.TermRegistration WHERE lTrnscpt.TermRegistration.Status != "P"
      RESULT = lTrnscpt.cursor( "TermRegistration" ).setFirst().toInt();
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( lTrnscpt, "TermRegistration", "Status", "P" ) == 0 ) )
         { 
            RESULT = lTrnscpt.cursor( "TermRegistration" ).setNextContinue().toInt();;
         } 

      } 

      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:DELETE ENTITY lTrnscpt.TranscriptGroup NONE
         RESULT = DeleteEntity( lTrnscpt, "TranscriptGroup", zREPOS_NONE );
      } 

      RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setNextContinue().toInt();;
      //:END 
   } 

   //:END 

   //:// Set up Birth Date as only Month and Day.
   //:GetStringFromAttributeByContext( szMonthDay1, lTrnscpt, "Person", "DateOfBirth", "MonthOfYear", 30 )
   {StringBuilder sb_szMonthDay1;
   if ( szMonthDay1 == null )
      sb_szMonthDay1 = new StringBuilder( 32 );
   else
      sb_szMonthDay1 = new StringBuilder( szMonthDay1 );
       GetStringFromAttributeByContext( sb_szMonthDay1, lTrnscpt, "Person", "DateOfBirth", "MonthOfYear", 30 );
   szMonthDay1 = sb_szMonthDay1.toString( );}
   //:GetStringFromAttributeByContext( szMonthDay2, lTrnscpt, "Person", "DateOfBirth", "DayOfMonth", 30 )
   {StringBuilder sb_szMonthDay2;
   if ( szMonthDay2 == null )
      sb_szMonthDay2 = new StringBuilder( 32 );
   else
      sb_szMonthDay2 = new StringBuilder( szMonthDay2 );
       GetStringFromAttributeByContext( sb_szMonthDay2, lTrnscpt, "Person", "DateOfBirth", "DayOfMonth", 30 );
   szMonthDay2 = sb_szMonthDay2.toString( );}
   //:szMonthDay1 = szMonthDay1 + " " + szMonthDay2
    {StringBuilder sb_szMonthDay1;
   if ( szMonthDay1 == null )
      sb_szMonthDay1 = new StringBuilder( 32 );
   else
      sb_szMonthDay1 = new StringBuilder( szMonthDay1 );
      ZeidonStringConcat( sb_szMonthDay1, 1, 0, " ", 1, 0, 31 );
   szMonthDay1 = sb_szMonthDay1.toString( );}
    {StringBuilder sb_szMonthDay1;
   if ( szMonthDay1 == null )
      sb_szMonthDay1 = new StringBuilder( 32 );
   else
      sb_szMonthDay1 = new StringBuilder( szMonthDay1 );
      ZeidonStringConcat( sb_szMonthDay1, 1, 0, szMonthDay2, 1, 0, 31 );
   szMonthDay1 = sb_szMonthDay1.toString( );}
   //:lTrnscpt.Student.wBirthMonthDay = szMonthDay1
   SetAttributeFromString( lTrnscpt, "Student", "wBirthMonthDay", szMonthDay1 );

   //:// Finally total TranscriptGroups and build the Print subobjects and Final Entries.
   //:BuildPrintGroup( lTrnscpt )
   olTrnscpt_BuildPrintGroup( lTrnscpt );
   //:FOR EACH lTrnscpt.FinalEntries 
   RESULT = lTrnscpt.cursor( "FinalEntries" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY lTrnscpt.PrintGroup
      RESULT = CreateEntity( lTrnscpt, "PrintGroup", zPOS_AFTER );
      //:FOR EACH lTrnscpt.FinalLegacyLine 
      RESULT = lTrnscpt.cursor( "FinalLegacyLine" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY lTrnscpt.PrintHeaderLine 
         RESULT = CreateEntity( lTrnscpt, "PrintHeaderLine", zPOS_AFTER );
         //:lTrnscpt.PrintHeaderLine.TextValue = lTrnscpt.FinalLegacyLine.LineText 
         SetAttributeFromAttribute( lTrnscpt, "PrintHeaderLine", "TextValue", lTrnscpt, "FinalLegacyLine", "LineText" );
         RESULT = lTrnscpt.cursor( "FinalLegacyLine" ).setNextContinue().toInt();;
      } 

      //:END
      //:FOR EACH lTrnscpt.FinalLegacyEquivalent 
      RESULT = lTrnscpt.cursor( "FinalLegacyEquivalent" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:INCLUDE lTrnscpt.PrintRegistration FROM lTrnscpt.FinalLegacyEquivalent 
         RESULT = IncludeSubobjectFromSubobject( lTrnscpt, "PrintRegistration", lTrnscpt, "FinalLegacyEquivalent", zPOS_AFTER );
         //:SetMatchingAttributesByName( lTrnscpt, "PrintRegistration", lTrnscpt, "FinalLegacyEquivalent", zSET_NULL )
         SetMatchingAttributesByName( lTrnscpt, "PrintRegistration", lTrnscpt, "FinalLegacyEquivalent", zSET_NULL );
         //:lTrnscpt.PrintRegistration.wCourseTitle = lTrnscpt.FinalLegacyEquivalent.ForeignClassName 
         SetAttributeFromAttribute( lTrnscpt, "PrintRegistration", "wCourseTitle", lTrnscpt, "FinalLegacyEquivalent", "ForeignClassName" );
         //:IF lTrnscpt.FinalLegacyCourse EXISTS
         lTempInteger_28 = CheckExistenceOfEntity( lTrnscpt, "FinalLegacyCourse" );
         if ( lTempInteger_28 == 0 )
         { 
            //:lTrnscpt.PrintRegistration.wCourseNumber = lTrnscpt.FinalLegacyCourse.Number 
            SetAttributeFromAttribute( lTrnscpt, "PrintRegistration", "wCourseNumber", lTrnscpt, "FinalLegacyCourse", "Number" );
         } 

         RESULT = lTrnscpt.cursor( "FinalLegacyEquivalent" ).setNextContinue().toInt();;
         //:END
      } 

      RESULT = lTrnscpt.cursor( "FinalEntries" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:// Create GroupSummary entities for each group with PrintRegistration or PrintRegistration2
   //:// entries. We have to do this because the Report Writer was not formatting the group footer for
   //:// PrintRegistration before the PrintCertificationStatements. This is probably because the Report
   //:// Writer code does not look for the condition when we're transferring to another entity at the
   //:// same level.
   //:FOR EACH lTrnscpt.PrintGroup 
   RESULT = lTrnscpt.cursor( "PrintGroup" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF lTrnscpt.PrintRegistration EXISTS OR 
      lTempInteger_29 = CheckExistenceOfEntity( lTrnscpt, "PrintRegistration" );
      //:   lTrnscpt.PrintRegistration2 EXISTS
      lTempInteger_30 = CheckExistenceOfEntity( lTrnscpt, "PrintRegistration2" );
      if ( lTempInteger_29 == 0 || lTempInteger_30 == 0 )
      { 

         //:CREATE ENTITY lTrnscpt.GroupSummary   
         RESULT = CreateEntity( lTrnscpt, "GroupSummary", zPOS_AFTER );
      } 

      RESULT = lTrnscpt.cursor( "PrintGroup" ).setNextContinue().toInt();;
      //:END 
   } 

   //:END

   //:// Create Certification Statements.
   //:// If a CollegeTerm is specified for a statement, put it on the last group for that Term.
   //:// Otherwise, put it on the last PrintGroup.
   //:// Make sure that PrintGroup exists, in case we're executing this for a Student without Enrollment Entries or if
   //:// we're trying to insert this to a Term that doesn't yet exist.
   //:IF lTrnscpt.PrintGroup DOES NOT EXIST
   lTempInteger_31 = CheckExistenceOfEntity( lTrnscpt, "PrintGroup" );
   if ( lTempInteger_31 != 0 )
   { 
      //:CREATE ENTITY lTrnscpt.PrintGroup  
      RESULT = CreateEntity( lTrnscpt, "PrintGroup", zPOS_AFTER );
   } 

   //:END
   //:FOR EACH lTrnscpt.StudentCertification
   RESULT = lTrnscpt.cursor( "StudentCertification" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF lTrnscpt.CollegeTerm EXISTS
      lTempInteger_32 = CheckExistenceOfEntity( lTrnscpt, "CollegeTerm" );
      if ( lTempInteger_32 == 0 )
      { 
         //:SET CURSOR LAST lTrnscpt.PrintGroup WHERE lTrnscpt.PrintGroup.wCollegeYear = lTrnscpt.CollegeYear.Year 
         //:                                      AND lTrnscpt.PrintGroup.Semester     = lTrnscpt.CollegeTerm.Semester 
         RESULT = lTrnscpt.cursor( "PrintGroup" ).setLast().toInt();;
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lTrnscpt, "PrintGroup", "wCollegeYear", lTrnscpt, "CollegeYear", "Year" ) != 0 ||
                    CompareAttributeToAttribute( lTrnscpt, "PrintGroup", "Semester", lTrnscpt, "CollegeTerm", "Semester" ) != 0 ) )
            { 
               RESULT = lTrnscpt.cursor( "PrintGroup" ).setPrevContinue( ).toInt();
            } 

         } 

         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:// Insert the Group in the correct Term sequence.
            //:SET CURSOR FIRST lTrnscpt.PrintGroup WHERE lTrnscpt.PrintGroup.wCollegeYear = lTrnscpt.CollegeYear.Year
            {StringBuilder sb_szTempString_7;
            if ( szTempString_7 == null )
               sb_szTempString_7 = new StringBuilder( 32 );
            else
               sb_szTempString_7 = new StringBuilder( szTempString_7 );
                         GetStringFromAttribute( sb_szTempString_7, lTrnscpt, "CollegeYear", "Year" );
            szTempString_7 = sb_szTempString_7.toString( );}
            RESULT = lTrnscpt.cursor( "PrintGroup" ).setFirst( "wCollegeYear", szTempString_7 ).toInt();
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:SET CURSOR LAST lTrnscpt.PrintGroup WHERE lTrnscpt.PrintGroup.wCollegeYear = lTrnscpt.CollegeYear.Year
               //:                                      AND lTrnscpt.PrintGroup.Semester    <= lTrnscpt.CollegeTerm.Semester
               RESULT = lTrnscpt.cursor( "PrintGroup" ).setLast().toInt();;
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lTrnscpt, "PrintGroup", "wCollegeYear", lTrnscpt, "CollegeYear", "Year" ) != 0 ||
                        CompareAttributeToAttribute( lTrnscpt, "PrintGroup", "Semester", lTrnscpt, "CollegeTerm", "Semester" ) > 0 ) )
                  { 
                     RESULT = lTrnscpt.cursor( "PrintGroup" ).setPrevContinue( ).toInt();
                  } 

               } 

               //:CREATE ENTITY lTrnscpt.PrintGroup AFTER
               RESULT = CreateEntity( lTrnscpt, "PrintGroup", zPOS_AFTER );
               //:ELSE
            } 
            else
            { 
               //:SET CURSOR LAST lTrnscpt.PrintGroup WHERE lTrnscpt.PrintGroup.wCollegeYear <= lTrnscpt.CollegeYear.Year
               RESULT = lTrnscpt.cursor( "PrintGroup" ).setLast().toInt();;
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lTrnscpt, "PrintGroup", "wCollegeYear", lTrnscpt, "CollegeYear", "Year" ) > 0 ) )
                  { 
                     RESULT = lTrnscpt.cursor( "PrintGroup" ).setPrevContinue( ).toInt();
                  } 

               } 

               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:CREATE ENTITY lTrnscpt.PrintGroup AFTER
                  RESULT = CreateEntity( lTrnscpt, "PrintGroup", zPOS_AFTER );
                  //:ELSE
               } 
               else
               { 
                  //:SET CURSOR FIRST lTrnscpt.PrintGroup
                  RESULT = lTrnscpt.cursor( "PrintGroup" ).setFirst().toInt();
                  //:CREATE ENTITY lTrnscpt.PrintGroup BEFORE
                  RESULT = CreateEntity( lTrnscpt, "PrintGroup", zPOS_BEFORE );
               } 

               //:END
            } 

            //:END
            //:lTrnscpt.PrintGroup.wCollegeYear = lTrnscpt.CollegeYear.Year
            SetAttributeFromAttribute( lTrnscpt, "PrintGroup", "wCollegeYear", lTrnscpt, "CollegeYear", "Year" );
            //:lTrnscpt.PrintGroup.Semester     = lTrnscpt.CollegeTerm.Semester 
            SetAttributeFromAttribute( lTrnscpt, "PrintGroup", "Semester", lTrnscpt, "CollegeTerm", "Semester" );
            //:IF lTrnscpt.CollegeTerm.TranscriptTermDisplay = ""
            if ( CompareAttributeToString( lTrnscpt, "CollegeTerm", "TranscriptTermDisplay", "" ) == 0 )
            { 
               //:lTrnscpt.PrintGroup.YearSemester = lTrnscpt.CollegeTerm.YearSemester
               SetAttributeFromAttribute( lTrnscpt, "PrintGroup", "YearSemester", lTrnscpt, "CollegeTerm", "YearSemester" );
               //:ELSE
            } 
            else
            { 
               //:lTrnscpt.PrintGroup.YearSemester = lTrnscpt.CollegeTerm.TranscriptTermDisplay
               SetAttributeFromAttribute( lTrnscpt, "PrintGroup", "YearSemester", lTrnscpt, "CollegeTerm", "TranscriptTermDisplay" );
            } 

            //:END
            //:CREATE ENTITY lTrnscpt.PrintHeaderLine
            RESULT = CreateEntity( lTrnscpt, "PrintHeaderLine", zPOS_AFTER );
            //:IF lTrnscpt.CollegeTerm.TranscriptTermDisplay = ""
            if ( CompareAttributeToString( lTrnscpt, "CollegeTerm", "TranscriptTermDisplay", "" ) == 0 )
            { 
               //:lTrnscpt.PrintHeaderLine.TextValue = lTrnscpt.CollegeTerm.YearSemester
               SetAttributeFromAttribute( lTrnscpt, "PrintHeaderLine", "TextValue", lTrnscpt, "CollegeTerm", "YearSemester" );
               //:ELSE
            } 
            else
            { 
               //:lTrnscpt.PrintHeaderLine.TextValue = lTrnscpt.CollegeTerm.TranscriptTermDisplay
               SetAttributeFromAttribute( lTrnscpt, "PrintHeaderLine", "TextValue", lTrnscpt, "CollegeTerm", "TranscriptTermDisplay" );
            } 

            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:SET CURSOR LAST lTrnscpt.PrintGroup
         RESULT = lTrnscpt.cursor( "PrintGroup" ).setLast().toInt();;
      } 

      //:END
      //:IF lTrnscpt.PrintCertificationStatements DOES NOT EXIST
      lTempInteger_33 = CheckExistenceOfEntity( lTrnscpt, "PrintCertificationStatements" );
      if ( lTempInteger_33 != 0 )
      { 
         //:CREATE ENTITY lTrnscpt.PrintCertificationStatements  
         RESULT = CreateEntity( lTrnscpt, "PrintCertificationStatements", zPOS_AFTER );
         //:ELSE
      } 
      else
      { 
         //:SET CURSOR LAST lTrnscpt.PrintCertificationStatement  
         RESULT = lTrnscpt.cursor( "PrintCertificationStatement" ).setLast().toInt();;
      } 

      //:END
      //:CREATE ENTITY lTrnscpt.PrintCertificationStatement
      RESULT = CreateEntity( lTrnscpt, "PrintCertificationStatement", zPOS_AFTER );
      //:lTrnscpt.PrintCertificationStatement.TextValue = lTrnscpt.CertificationStatement.TranscriptText 
      SetAttributeFromAttribute( lTrnscpt, "PrintCertificationStatement", "TextValue", lTrnscpt, "CertificationStatement", "TranscriptText" );
      RESULT = lTrnscpt.cursor( "StudentCertification" ).setNextContinue().toInt();;
   } 

   //:END

   //:// Create Custom Statements for UNDERGRAD transcripts only.
   //:// If a CollegeTerm is specified for a statement, put it on the last group for that Term.
   //:// Otherwise, create a group for that Term.
   //:IF GradUndergradFlag != "G"
   if ( ZeidonStringCompare( GradUndergradFlag, 1, 0, "G", 1, 0, 2 ) != 0 )
   { 
      //:FOR EACH lTrnscpt.CustomTransciptTerm 
      RESULT = lTrnscpt.cursor( "CustomTransciptTerm" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF lTrnscpt.CustomTranscriptTermTerm EXISTS
         lTempInteger_34 = CheckExistenceOfEntity( lTrnscpt, "CustomTranscriptTermTerm" );
         if ( lTempInteger_34 == 0 )
         { 
            //:SET CURSOR LAST lTrnscpt.PrintGroup WHERE lTrnscpt.PrintGroup.wCollegeYear = lTrnscpt.CustomTranscriptTermYear.Year AND 
            //:                                          lTrnscpt.PrintGroup.Semester     = lTrnscpt.CustomTranscriptTermTerm.Semester 
            RESULT = lTrnscpt.cursor( "PrintGroup" ).setLast().toInt();;
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lTrnscpt, "PrintGroup", "wCollegeYear", lTrnscpt, "CustomTranscriptTermYear", "Year" ) != 0 ||
                       CompareAttributeToAttribute( lTrnscpt, "PrintGroup", "Semester", lTrnscpt, "CustomTranscriptTermTerm", "Semester" ) != 0 ) )
               { 
                  RESULT = lTrnscpt.cursor( "PrintGroup" ).setPrevContinue( ).toInt();
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:SET CURSOR LAST lTrnscpt.PrintGroup WHERE lTrnscpt.PrintGroup.wCollegeYear = lTrnscpt.CustomTranscriptTermYear.Year AND 
               //:                                          lTrnscpt.PrintGroup.Semester     <= lTrnscpt.CustomTranscriptTermTerm.Semester 
               RESULT = lTrnscpt.cursor( "PrintGroup" ).setLast().toInt();;
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lTrnscpt, "PrintGroup", "wCollegeYear", lTrnscpt, "CustomTranscriptTermYear", "Year" ) != 0 ||
                        CompareAttributeToAttribute( lTrnscpt, "PrintGroup", "Semester", lTrnscpt, "CustomTranscriptTermTerm", "Semester" ) > 0 ) )
                  { 
                     RESULT = lTrnscpt.cursor( "PrintGroup" ).setPrevContinue( ).toInt();
                  } 

               } 

               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:SET CURSOR LAST lTrnscpt.PrintGroup WHERE lTrnscpt.PrintGroup.wCollegeYear <= lTrnscpt.CustomTranscriptTermYear.Year
                  RESULT = lTrnscpt.cursor( "PrintGroup" ).setLast().toInt();;
                  if ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lTrnscpt, "PrintGroup", "wCollegeYear", lTrnscpt, "CustomTranscriptTermYear", "Year" ) > 0 ) )
                     { 
                        RESULT = lTrnscpt.cursor( "PrintGroup" ).setPrevContinue( ).toInt();
                     } 

                  } 

                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:SET CURSOR FIRST lTrnscpt.PrintGroup
                     RESULT = lTrnscpt.cursor( "PrintGroup" ).setFirst().toInt();
                     //:CREATE ENTITY lTrnscpt.PrintGroup BEFORE
                     RESULT = CreateEntity( lTrnscpt, "PrintGroup", zPOS_BEFORE );
                     //:ELSE
                  } 
                  else
                  { 
                     //:CREATE ENTITY lTrnscpt.PrintGroup BEFORE
                     RESULT = CreateEntity( lTrnscpt, "PrintGroup", zPOS_BEFORE );
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:CREATE ENTITY lTrnscpt.PrintGroup BEFORE
                  RESULT = CreateEntity( lTrnscpt, "PrintGroup", zPOS_BEFORE );
               } 

               //:END
               //:lTrnscpt.PrintGroup.wCollegeYear   = lTrnscpt.CustomTranscriptTermYear.Year
               SetAttributeFromAttribute( lTrnscpt, "PrintGroup", "wCollegeYear", lTrnscpt, "CustomTranscriptTermYear", "Year" );
               //:lTrnscpt.PrintGroup.Semester       = lTrnscpt.CustomTranscriptTermTerm.Semester 
               SetAttributeFromAttribute( lTrnscpt, "PrintGroup", "Semester", lTrnscpt, "CustomTranscriptTermTerm", "Semester" );
               //:lTrnscpt.PrintGroup.YearSemester   = lTrnscpt.CustomTranscriptTermTerm.YearSemester
               SetAttributeFromAttribute( lTrnscpt, "PrintGroup", "YearSemester", lTrnscpt, "CustomTranscriptTermTerm", "YearSemester" );
               //:CREATE ENTITY lTrnscpt.PrintHeaderLine
               RESULT = CreateEntity( lTrnscpt, "PrintHeaderLine", zPOS_AFTER );
               //:lTrnscpt.PrintHeaderLine.TextValue = lTrnscpt.CustomTranscriptTermTerm.TranscriptTermDisplay 
               SetAttributeFromAttribute( lTrnscpt, "PrintHeaderLine", "TextValue", lTrnscpt, "CustomTranscriptTermTerm", "TranscriptTermDisplay" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:SET CURSOR LAST lTrnscpt.PrintGroup
            RESULT = lTrnscpt.cursor( "PrintGroup" ).setLast().toInt();;
         } 

         //:END
         //:CREATE ENTITY lTrnscpt.CustomStatements 
         RESULT = CreateEntity( lTrnscpt, "CustomStatements", zPOS_AFTER );
         //:FOR EACH lTrnscpt.CustomTranscriptLine
         RESULT = lTrnscpt.cursor( "CustomTranscriptLine" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY lTrnscpt.CustomStatement 
            RESULT = CreateEntity( lTrnscpt, "CustomStatement", zPOS_AFTER );
            //:lTrnscpt.CustomStatement.LineText = lTrnscpt.CustomTranscriptLine.LineText  
            SetAttributeFromAttribute( lTrnscpt, "CustomStatement", "LineText", lTrnscpt, "CustomTranscriptLine", "LineText" );
            RESULT = lTrnscpt.cursor( "CustomTranscriptLine" ).setNextContinue().toInt();;
         } 

         RESULT = lTrnscpt.cursor( "CustomTransciptTerm" ).setNextContinue().toInt();;
         //:END
      } 

      //:END
   } 

   //:END

   //:// Create Degree, Major and Minor entities. Create only if there is at least one Degree Track for the Student.
   //:// The rules are different for Undergrad and Grad transcripts versus Certificate transcripts.
   //:IF GradUndergradFlag != "C"
   if ( ZeidonStringCompare( GradUndergradFlag, 1, 0, "C", 1, 0, 2 ) != 0 )
   { 
      //:// Grad or Undergrad Transcript.
      //:// If there is at least one Degree Track, we will create one print group for all track entries that do NOT have a
      //:// GraduationDate and one for all that do HAVE a GraduationDate The group for non-GraduationDate entries will be
      //:// "Major/Minor". The group for GraduationDate entries will be "Degree(s) Awarded".
      //:lTrnscpt.Student.wTranscriptCreditCEU_Unit = "CR"
      SetAttributeFromString( lTrnscpt, "Student", "wTranscriptCreditCEU_Unit", "CR" );
      //:IF lTrnscpt.StudentMajorDegreeTrack EXISTS AND lTrnscpt.PrintGroup EXISTS
      lTempInteger_35 = CheckExistenceOfEntity( lTrnscpt, "StudentMajorDegreeTrack" );
      lTempInteger_36 = CheckExistenceOfEntity( lTrnscpt, "PrintGroup" );
      if ( lTempInteger_35 == 0 && lTempInteger_36 == 0 )
      { 
         //:// Sort entries by Graduation Date, except that those with no Graduation Date are at the end. We thus need to
         //:// create a work field to sort null entries to the end.
         //:FOR EACH lTrnscpt.StudentMajorDegreeTrack 
         RESULT = lTrnscpt.cursor( "StudentMajorDegreeTrack" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lTrnscpt.StudentMajorDegreeTrack.GraduationDate = ""
            if ( CompareAttributeToString( lTrnscpt, "StudentMajorDegreeTrack", "GraduationDate", "" ) == 0 )
            { 
               //:lTrnscpt.StudentMajorDegreeTrack.wPrimarySortOrder = "2"
               SetAttributeFromString( lTrnscpt, "StudentMajorDegreeTrack", "wPrimarySortOrder", "2" );
               //:ELSE
            } 
            else
            { 
               //:lTrnscpt.StudentMajorDegreeTrack.wPrimarySortOrder = "1"
               SetAttributeFromString( lTrnscpt, "StudentMajorDegreeTrack", "wPrimarySortOrder", "1" );
            } 

            RESULT = lTrnscpt.cursor( "StudentMajorDegreeTrack" ).setNextContinue().toInt();;
            //:END 
         } 

         //:END
         //:OrderEntityForView( lTrnscpt, "StudentMajorDegreeTrack", "wPrimarySortOrder A GraduationDate A" )
         OrderEntityForView( lTrnscpt, "StudentMajorDegreeTrack", "wPrimarySortOrder A GraduationDate A" );
         //:LastGraduationDate = "19000101"    // Initialize to a date that we are sure will not come up to force the first entry.
          {StringBuilder sb_LastGraduationDate;
         if ( LastGraduationDate == null )
            sb_LastGraduationDate = new StringBuilder( 32 );
         else
            sb_LastGraduationDate = new StringBuilder( LastGraduationDate );
                  ZeidonStringCopy( sb_LastGraduationDate, 1, 0, "19000101", 1, 0, 9 );
         LastGraduationDate = sb_LastGraduationDate.toString( );}
         //:FOR EACH lTrnscpt.StudentMajorDegreeTrack 
         RESULT = lTrnscpt.cursor( "StudentMajorDegreeTrack" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lTrnscpt.StudentMajorDegreeTrack.GraduationDate != LastGraduationDate
            if ( CompareAttributeToString( lTrnscpt, "StudentMajorDegreeTrack", "GraduationDate", LastGraduationDate ) != 0 )
            { 
               //:LastGraduationDate = lTrnscpt.StudentMajorDegreeTrack.GraduationDate 
               {MutableInt mi_lTempInteger_37 = new MutableInt( lTempInteger_37 );
               StringBuilder sb_LastGraduationDate;
               if ( LastGraduationDate == null )
                  sb_LastGraduationDate = new StringBuilder( 32 );
               else
                  sb_LastGraduationDate = new StringBuilder( LastGraduationDate );
                               GetVariableFromAttribute( sb_LastGraduationDate, mi_lTempInteger_37, 'S', 9, lTrnscpt, "StudentMajorDegreeTrack", "GraduationDate", "", 0 );
               lTempInteger_37 = mi_lTempInteger_37.intValue( );
               LastGraduationDate = sb_LastGraduationDate.toString( );}
               //:// Determine how many entries there are for this group, because any multiple entries will be numbered.
               //:TotalCount = 0
               TotalCount = 0;
               //:CreateViewFromView( lTrnscpt2, lTrnscpt )
               CreateViewFromView( lTrnscpt2, lTrnscpt );
               //:FOR EACH lTrnscpt2.StudentMajorDegreeTrack 
               //:          WHERE lTrnscpt2.StudentMajorDegreeTrack.GraduationDate = LastGraduationDate
               RESULT = lTrnscpt2.cursor( "StudentMajorDegreeTrack" ).setFirst( "GraduationDate", LastGraduationDate ).toInt();
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:TotalCount = TotalCount + 1
                  TotalCount = TotalCount + 1;
                  RESULT = lTrnscpt2.cursor( "StudentMajorDegreeTrack" ).setNextContinue().toInt();;
               } 

               //:END
               //:DropView( lTrnscpt2 )
               DropView( lTrnscpt2 );
               //:MajorCount  = 0
               MajorCount = 0;
               //:MinorCount  = 0
               MinorCount = 0;
               //:SET CURSOR LAST lTrnscpt.PrintGroup
               RESULT = lTrnscpt.cursor( "PrintGroup" ).setLast().toInt();;
               //:CREATE ENTITY lTrnscpt.PrintDegreeMajorMinorGroup
               RESULT = CreateEntity( lTrnscpt, "PrintDegreeMajorMinorGroup", zPOS_AFTER );
               //:CREATE ENTITY lTrnscpt.DegreesAwardedTitle
               RESULT = CreateEntity( lTrnscpt, "DegreesAwardedTitle", zPOS_AFTER );
               //:IF LastGraduationDate = ""
               if ( ZeidonStringCompare( LastGraduationDate, 1, 0, "", 1, 0, 9 ) == 0 )
               { 
                  //:lTrnscpt.DegreesAwardedTitle.Title = "Major/Minor"
                  SetAttributeFromString( lTrnscpt, "DegreesAwardedTitle", "Title", "Major/Minor" );
                  //:ELSE
               } 
               else
               { 
                  //:lTrnscpt.DegreesAwardedTitle.Title = "Degree(s) Awarded"
                  SetAttributeFromString( lTrnscpt, "DegreesAwardedTitle", "Title", "Degree(s) Awarded" );
               } 

               //:END
            } 

            //:END

            //:// Process Degrees and Majors
            //:IF lTrnscpt.MajorCollege.Type = GradUndergradFlag
            if ( CompareAttributeToString( lTrnscpt, "MajorCollege", "Type", GradUndergradFlag ) == 0 )
            { 
               //:IF lTrnscpt.DegreeMajor DOES NOT EXIST
               lTempInteger_38 = CheckExistenceOfEntity( lTrnscpt, "DegreeMajor" );
               if ( lTempInteger_38 != 0 )
               { 
                  //:szMsg = "A Degree Major is missing for Degree Track, " + lTrnscpt.MajorDegreeTrack.Name + "."
                  {MutableInt mi_lTempInteger_39 = new MutableInt( lTempInteger_39 );
                  StringBuilder sb_szTempString_8;
                  if ( szTempString_8 == null )
                     sb_szTempString_8 = new StringBuilder( 32 );
                  else
                     sb_szTempString_8 = new StringBuilder( szTempString_8 );
                                     GetVariableFromAttribute( sb_szTempString_8, mi_lTempInteger_39, 'S', 51, lTrnscpt, "MajorDegreeTrack", "Name", "", 0 );
                  lTempInteger_39 = mi_lTempInteger_39.intValue( );
                  szTempString_8 = sb_szTempString_8.toString( );}
                   {StringBuilder sb_szMsg;
                  if ( szMsg == null )
                     sb_szMsg = new StringBuilder( 32 );
                  else
                     sb_szMsg = new StringBuilder( szMsg );
                                    ZeidonStringCopy( sb_szMsg, 1, 0, "A Degree Major is missing for Degree Track, ", 1, 0, 201 );
                  szMsg = sb_szMsg.toString( );}
                   {StringBuilder sb_szMsg;
                  if ( szMsg == null )
                     sb_szMsg = new StringBuilder( 32 );
                  else
                     sb_szMsg = new StringBuilder( szMsg );
                                    ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_8, 1, 0, 201 );
                  szMsg = sb_szMsg.toString( );}
                   {StringBuilder sb_szMsg;
                  if ( szMsg == null )
                     sb_szMsg = new StringBuilder( 32 );
                  else
                     sb_szMsg = new StringBuilder( szMsg );
                                    ZeidonStringConcat( sb_szMsg, 1, 0, ".", 1, 0, 201 );
                  szMsg = sb_szMsg.toString( );}
                  //:MessageSend( lTrnscpt, "", "Build Transcript", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( lTrnscpt, "", "Build Transcript", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:ELSE
               } 
               else
               { 
                  //:IF lTrnscpt.CollegeDegree DOES NOT EXIST AND szCollegeType != "C"
                  lTempInteger_40 = CheckExistenceOfEntity( lTrnscpt, "CollegeDegree" );
                  if ( lTempInteger_40 != 0 && ZeidonStringCompare( szCollegeType, 1, 0, "C", 1, 0, 21 ) != 0 )
                  { 
                     //:szMsg = "A College Degree is missing for Degree Track, " + lTrnscpt.MajorDegreeTrack.Name + "."
                     {MutableInt mi_lTempInteger_41 = new MutableInt( lTempInteger_41 );
                     StringBuilder sb_szTempString_9;
                     if ( szTempString_9 == null )
                        sb_szTempString_9 = new StringBuilder( 32 );
                     else
                        sb_szTempString_9 = new StringBuilder( szTempString_9 );
                                           GetVariableFromAttribute( sb_szTempString_9, mi_lTempInteger_41, 'S', 51, lTrnscpt, "MajorDegreeTrack", "Name", "", 0 );
                     lTempInteger_41 = mi_lTempInteger_41.intValue( );
                     szTempString_9 = sb_szTempString_9.toString( );}
                      {StringBuilder sb_szMsg;
                     if ( szMsg == null )
                        sb_szMsg = new StringBuilder( 32 );
                     else
                        sb_szMsg = new StringBuilder( szMsg );
                                          ZeidonStringCopy( sb_szMsg, 1, 0, "A College Degree is missing for Degree Track, ", 1, 0, 201 );
                     szMsg = sb_szMsg.toString( );}
                      {StringBuilder sb_szMsg;
                     if ( szMsg == null )
                        sb_szMsg = new StringBuilder( 32 );
                     else
                        sb_szMsg = new StringBuilder( szMsg );
                                          ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_9, 1, 0, 201 );
                     szMsg = sb_szMsg.toString( );}
                      {StringBuilder sb_szMsg;
                     if ( szMsg == null )
                        sb_szMsg = new StringBuilder( 32 );
                     else
                        sb_szMsg = new StringBuilder( szMsg );
                                          ZeidonStringConcat( sb_szMsg, 1, 0, ".", 1, 0, 201 );
                     szMsg = sb_szMsg.toString( );}
                     //://MessageSend( lTrnscpt, "", "Build Transcript", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF lTrnscpt.CollegeDegree EXISTS
                     lTempInteger_42 = CheckExistenceOfEntity( lTrnscpt, "CollegeDegree" );
                     if ( lTempInteger_42 == 0 )
                     { 
                        //:// Set up PrintDegreeGroup subobject.
                        //:IF lTrnscpt.CollegeDegree.TranscriptDisplayName = ""
                        if ( CompareAttributeToString( lTrnscpt, "CollegeDegree", "TranscriptDisplayName", "" ) == 0 )
                        { 
                           //:szCollegeDegreeName = lTrnscpt.CollegeDegree.Name 
                           {MutableInt mi_lTempInteger_43 = new MutableInt( lTempInteger_43 );
                           StringBuilder sb_szCollegeDegreeName;
                           if ( szCollegeDegreeName == null )
                              sb_szCollegeDegreeName = new StringBuilder( 32 );
                           else
                              sb_szCollegeDegreeName = new StringBuilder( szCollegeDegreeName );
                                                       GetVariableFromAttribute( sb_szCollegeDegreeName, mi_lTempInteger_43, 'S', 31, lTrnscpt, "CollegeDegree", "Name", "", 0 );
                           lTempInteger_43 = mi_lTempInteger_43.intValue( );
                           szCollegeDegreeName = sb_szCollegeDegreeName.toString( );}
                           //:ELSE
                        } 
                        else
                        { 
                           //:szCollegeDegreeName = lTrnscpt.CollegeDegree.TranscriptDisplayName 
                           {MutableInt mi_lTempInteger_44 = new MutableInt( lTempInteger_44 );
                           StringBuilder sb_szCollegeDegreeName;
                           if ( szCollegeDegreeName == null )
                              sb_szCollegeDegreeName = new StringBuilder( 32 );
                           else
                              sb_szCollegeDegreeName = new StringBuilder( szCollegeDegreeName );
                                                       GetVariableFromAttribute( sb_szCollegeDegreeName, mi_lTempInteger_44, 'S', 31, lTrnscpt, "CollegeDegree", "TranscriptDisplayName", "", 0 );
                           lTempInteger_44 = mi_lTempInteger_44.intValue( );
                           szCollegeDegreeName = sb_szCollegeDegreeName.toString( );}
                        } 

                        //:END
                        //:SET CURSOR FIRST lTrnscpt.PrintDegreeGroup 
                        //:           WHERE lTrnscpt.PrintDegreeGroup.DegreeName = szCollegeDegreeName
                        RESULT = lTrnscpt.cursor( "PrintDegreeGroup" ).setFirst( "DegreeName", szCollegeDegreeName ).toInt();
                        //:IF RESULT < zCURSOR_SET
                        if ( RESULT < zCURSOR_SET )
                        { 
                           //:CREATE ENTITY lTrnscpt.PrintDegreeGroup 
                           RESULT = CreateEntity( lTrnscpt, "PrintDegreeGroup", zPOS_AFTER );
                           //:lTrnscpt.PrintDegreeGroup.DegreeName = szCollegeDegreeName
                           SetAttributeFromString( lTrnscpt, "PrintDegreeGroup", "DegreeName", szCollegeDegreeName );
                           //:IF lTrnscpt.StudentMajorDegreeTrack.GraduationDate != ""
                           if ( CompareAttributeToString( lTrnscpt, "StudentMajorDegreeTrack", "GraduationDate", "" ) != 0 )
                           { 
                              //:CREATE ENTITY lTrnscpt.PrintDegree 
                              RESULT = CreateEntity( lTrnscpt, "PrintDegree", zPOS_AFTER );
                              //:lTrnscpt.PrintDegree.DegreeValue = szCollegeDegreeName
                              SetAttributeFromString( lTrnscpt, "PrintDegree", "DegreeValue", szCollegeDegreeName );
                              //:CREATE ENTITY lTrnscpt.PrintGraduationDate 
                              RESULT = CreateEntity( lTrnscpt, "PrintGraduationDate", zPOS_AFTER );
                              //:lTrnscpt.PrintGraduationDate.Date = lTrnscpt.StudentMajorDegreeTrack.GraduationDate
                              SetAttributeFromAttribute( lTrnscpt, "PrintGraduationDate", "Date", lTrnscpt, "StudentMajorDegreeTrack", "GraduationDate" );
                           } 

                           //:END
                        } 

                        //:END
                        //:ELSE
                     } 
                     else
                     { 
                        //:CREATE ENTITY lTrnscpt.PrintDegreeGroup
                        RESULT = CreateEntity( lTrnscpt, "PrintDegreeGroup", zPOS_AFTER );
                     } 

                     //:END

                     //:// Add Major and Track.
                     //:SET CURSOR LAST lTrnscpt.PrintMajorEntry 
                     RESULT = lTrnscpt.cursor( "PrintMajorEntry" ).setLast().toInt();;
                     //:CREATE ENTITY lTrnscpt.PrintMajorEntry
                     RESULT = CreateEntity( lTrnscpt, "PrintMajorEntry", zPOS_AFTER );
                     //:// Set up suffix
                     //:IF TotalCount > 1
                     if ( TotalCount > 1 )
                     { 
                        //:// There are multiple tracks, so create suffix.
                        //:MajorCount = MajorCount + 1
                        MajorCount = MajorCount + 1;
                        //:szMajorMinorCount = MajorCount
                         {StringBuilder sb_szMajorMinorCount;
                        if ( szMajorMinorCount == null )
                           sb_szMajorMinorCount = new StringBuilder( 32 );
                        else
                           sb_szMajorMinorCount = new StringBuilder( szMajorMinorCount );
                                                ZeidonStringConvertFromNumber( sb_szMajorMinorCount, 1, 0, 2, MajorCount, (double) 0.0, "I" );
                        szMajorMinorCount = sb_szMajorMinorCount.toString( );}
                        //:szPromptSuffix = " " + szMajorMinorCount + ":"
                         {StringBuilder sb_szPromptSuffix;
                        if ( szPromptSuffix == null )
                           sb_szPromptSuffix = new StringBuilder( 32 );
                        else
                           sb_szPromptSuffix = new StringBuilder( szPromptSuffix );
                                                ZeidonStringCopy( sb_szPromptSuffix, 1, 0, " ", 1, 0, 5 );
                        szPromptSuffix = sb_szPromptSuffix.toString( );}
                         {StringBuilder sb_szPromptSuffix;
                        if ( szPromptSuffix == null )
                           sb_szPromptSuffix = new StringBuilder( 32 );
                        else
                           sb_szPromptSuffix = new StringBuilder( szPromptSuffix );
                                                ZeidonStringConcat( sb_szPromptSuffix, 1, 0, szMajorMinorCount, 1, 0, 5 );
                        szPromptSuffix = sb_szPromptSuffix.toString( );}
                         {StringBuilder sb_szPromptSuffix;
                        if ( szPromptSuffix == null )
                           sb_szPromptSuffix = new StringBuilder( 32 );
                        else
                           sb_szPromptSuffix = new StringBuilder( szPromptSuffix );
                                                ZeidonStringConcat( sb_szPromptSuffix, 1, 0, ":", 1, 0, 5 );
                        szPromptSuffix = sb_szPromptSuffix.toString( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:// There is only a single track, so suffix is null.
                        //:szPromptSuffix = ":"
                         {StringBuilder sb_szPromptSuffix;
                        if ( szPromptSuffix == null )
                           sb_szPromptSuffix = new StringBuilder( 32 );
                        else
                           sb_szPromptSuffix = new StringBuilder( szPromptSuffix );
                                                ZeidonStringCopy( sb_szPromptSuffix, 1, 0, ":", 1, 0, 5 );
                        szPromptSuffix = sb_szPromptSuffix.toString( );}
                     } 

                     //:END
                     //:// Set value and prompt. 
                     //:IF lTrnscpt.MajorDegreeTrack.TranscriptDisplayName = ""
                     if ( CompareAttributeToString( lTrnscpt, "MajorDegreeTrack", "TranscriptDisplayName", "" ) == 0 )
                     { 
                        //:// Use regular Track name.
                        //:lTrnscpt.PrintMajorEntry.MajorValue  = lTrnscpt.DegreeMajor.Name + "/" + lTrnscpt.MajorDegreeTrack.Name
                        {StringBuilder sb_szTempString_10;
                        if ( szTempString_10 == null )
                           sb_szTempString_10 = new StringBuilder( 32 );
                        else
                           sb_szTempString_10 = new StringBuilder( szTempString_10 );
                                                 GetStringFromAttribute( sb_szTempString_10, lTrnscpt, "DegreeMajor", "Name" );
                        szTempString_10 = sb_szTempString_10.toString( );}
                         {StringBuilder sb_szTempString_10;
                        if ( szTempString_10 == null )
                           sb_szTempString_10 = new StringBuilder( 32 );
                        else
                           sb_szTempString_10 = new StringBuilder( szTempString_10 );
                                                ZeidonStringConcat( sb_szTempString_10, 1, 0, "/", 1, 0, 255 );
                        szTempString_10 = sb_szTempString_10.toString( );}
                        {MutableInt mi_lTempInteger_45 = new MutableInt( lTempInteger_45 );
                        StringBuilder sb_szTempString_11;
                        if ( szTempString_11 == null )
                           sb_szTempString_11 = new StringBuilder( 32 );
                        else
                           sb_szTempString_11 = new StringBuilder( szTempString_11 );
                                                 GetVariableFromAttribute( sb_szTempString_11, mi_lTempInteger_45, 'S', 51, lTrnscpt, "MajorDegreeTrack", "Name", "", 0 );
                        lTempInteger_45 = mi_lTempInteger_45.intValue( );
                        szTempString_11 = sb_szTempString_11.toString( );}
                         {StringBuilder sb_szTempString_10;
                        if ( szTempString_10 == null )
                           sb_szTempString_10 = new StringBuilder( 32 );
                        else
                           sb_szTempString_10 = new StringBuilder( szTempString_10 );
                                                ZeidonStringConcat( sb_szTempString_10, 1, 0, szTempString_11, 1, 0, 255 );
                        szTempString_10 = sb_szTempString_10.toString( );}
                        SetAttributeFromString( lTrnscpt, "PrintMajorEntry", "MajorValue", szTempString_10 );
                        //:lTrnscpt.PrintMajorEntry.PromptValue = "Major/Track" + szPromptSuffix
                         {StringBuilder sb_szTempString_12;
                        if ( szTempString_12 == null )
                           sb_szTempString_12 = new StringBuilder( 32 );
                        else
                           sb_szTempString_12 = new StringBuilder( szTempString_12 );
                                                ZeidonStringCopy( sb_szTempString_12, 1, 0, "Major/Track", 1, 0, 255 );
                        szTempString_12 = sb_szTempString_12.toString( );}
                         {StringBuilder sb_szTempString_12;
                        if ( szTempString_12 == null )
                           sb_szTempString_12 = new StringBuilder( 32 );
                        else
                           sb_szTempString_12 = new StringBuilder( szTempString_12 );
                                                ZeidonStringConcat( sb_szTempString_12, 1, 0, szPromptSuffix, 1, 0, 255 );
                        szTempString_12 = sb_szTempString_12.toString( );}
                        SetAttributeFromString( lTrnscpt, "PrintMajorEntry", "PromptValue", szTempString_12 );
                        //:ELSE
                     } 
                     else
                     { 
                        //:// Use special display Track name.
                        //:lTrnscpt.PrintMajorEntry.MajorValue  = lTrnscpt.DegreeMajor.Name + "/" + lTrnscpt.MajorDegreeTrack.TranscriptDisplayName
                        {StringBuilder sb_szTempString_13;
                        if ( szTempString_13 == null )
                           sb_szTempString_13 = new StringBuilder( 32 );
                        else
                           sb_szTempString_13 = new StringBuilder( szTempString_13 );
                                                 GetStringFromAttribute( sb_szTempString_13, lTrnscpt, "DegreeMajor", "Name" );
                        szTempString_13 = sb_szTempString_13.toString( );}
                         {StringBuilder sb_szTempString_13;
                        if ( szTempString_13 == null )
                           sb_szTempString_13 = new StringBuilder( 32 );
                        else
                           sb_szTempString_13 = new StringBuilder( szTempString_13 );
                                                ZeidonStringConcat( sb_szTempString_13, 1, 0, "/", 1, 0, 255 );
                        szTempString_13 = sb_szTempString_13.toString( );}
                        {MutableInt mi_lTempInteger_46 = new MutableInt( lTempInteger_46 );
                        StringBuilder sb_szTempString_14;
                        if ( szTempString_14 == null )
                           sb_szTempString_14 = new StringBuilder( 32 );
                        else
                           sb_szTempString_14 = new StringBuilder( szTempString_14 );
                                                 GetVariableFromAttribute( sb_szTempString_14, mi_lTempInteger_46, 'S', 51, lTrnscpt, "MajorDegreeTrack", "TranscriptDisplayName", "", 0 );
                        lTempInteger_46 = mi_lTempInteger_46.intValue( );
                        szTempString_14 = sb_szTempString_14.toString( );}
                         {StringBuilder sb_szTempString_13;
                        if ( szTempString_13 == null )
                           sb_szTempString_13 = new StringBuilder( 32 );
                        else
                           sb_szTempString_13 = new StringBuilder( szTempString_13 );
                                                ZeidonStringConcat( sb_szTempString_13, 1, 0, szTempString_14, 1, 0, 255 );
                        szTempString_13 = sb_szTempString_13.toString( );}
                        SetAttributeFromString( lTrnscpt, "PrintMajorEntry", "MajorValue", szTempString_13 );
                        //:lTrnscpt.PrintMajorEntry.PromptValue = "Major/Track" + szPromptSuffix
                         {StringBuilder sb_szTempString_15;
                        if ( szTempString_15 == null )
                           sb_szTempString_15 = new StringBuilder( 32 );
                        else
                           sb_szTempString_15 = new StringBuilder( szTempString_15 );
                                                ZeidonStringCopy( sb_szTempString_15, 1, 0, "Major/Track", 1, 0, 255 );
                        szTempString_15 = sb_szTempString_15.toString( );}
                         {StringBuilder sb_szTempString_15;
                        if ( szTempString_15 == null )
                           sb_szTempString_15 = new StringBuilder( 32 );
                        else
                           sb_szTempString_15 = new StringBuilder( szTempString_15 );
                                                ZeidonStringConcat( sb_szTempString_15, 1, 0, szPromptSuffix, 1, 0, 255 );
                        szTempString_15 = sb_szTempString_15.toString( );}
                        SetAttributeFromString( lTrnscpt, "PrintMajorEntry", "PromptValue", szTempString_15 );
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            RESULT = lTrnscpt.cursor( "StudentMajorDegreeTrack" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
      } 

      //:END

      //:// Process Minors
      //:TotalCount = 0
      TotalCount = 0;
      //:FOR EACH lTrnscpt.StudentMinorDegreeTrack
      RESULT = lTrnscpt.cursor( "StudentMinorDegreeTrack" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:TotalCount = TotalCount + 1
         TotalCount = TotalCount + 1;
         RESULT = lTrnscpt.cursor( "StudentMinorDegreeTrack" ).setNextContinue().toInt();;
      } 

      //:END
      //:MinorCount = 0
      MinorCount = 0;
      //:FOR EACH lTrnscpt.StudentMinorDegreeTrack
      RESULT = lTrnscpt.cursor( "StudentMinorDegreeTrack" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY lTrnscpt.PrintMinorEntry 
         RESULT = CreateEntity( lTrnscpt, "PrintMinorEntry", zPOS_AFTER );
         //:lTrnscpt.PrintMinorEntry.MinorValue  = lTrnscpt.DegreeMinor.Name
         SetAttributeFromAttribute( lTrnscpt, "PrintMinorEntry", "MinorValue", lTrnscpt, "DegreeMinor", "Name" );
         //:IF TotalCount > 0
         if ( TotalCount > 0 )
         { 
            //:lTrnscpt.PrintMinorEntry.PromptValue = "Minor:"
            SetAttributeFromString( lTrnscpt, "PrintMinorEntry", "PromptValue", "Minor:" );
            //:ELSE
         } 
         else
         { 
            //:MinorCount = MinorCount + 1
            MinorCount = MinorCount + 1;
            //:szMajorMinorCount = MinorCount
             {StringBuilder sb_szMajorMinorCount;
            if ( szMajorMinorCount == null )
               sb_szMajorMinorCount = new StringBuilder( 32 );
            else
               sb_szMajorMinorCount = new StringBuilder( szMajorMinorCount );
                        ZeidonStringConvertFromNumber( sb_szMajorMinorCount, 1, 0, 2, MinorCount, (double) 0.0, "I" );
            szMajorMinorCount = sb_szMajorMinorCount.toString( );}
            //:lTrnscpt.PrintMinorEntry.PromptValue = "Minor " + szMajorMinorCount + ":"
             {StringBuilder sb_szTempString_16;
            if ( szTempString_16 == null )
               sb_szTempString_16 = new StringBuilder( 32 );
            else
               sb_szTempString_16 = new StringBuilder( szTempString_16 );
                        ZeidonStringCopy( sb_szTempString_16, 1, 0, "Minor ", 1, 0, 255 );
            szTempString_16 = sb_szTempString_16.toString( );}
             {StringBuilder sb_szTempString_16;
            if ( szTempString_16 == null )
               sb_szTempString_16 = new StringBuilder( 32 );
            else
               sb_szTempString_16 = new StringBuilder( szTempString_16 );
                        ZeidonStringConcat( sb_szTempString_16, 1, 0, szMajorMinorCount, 1, 0, 255 );
            szTempString_16 = sb_szTempString_16.toString( );}
             {StringBuilder sb_szTempString_16;
            if ( szTempString_16 == null )
               sb_szTempString_16 = new StringBuilder( 32 );
            else
               sb_szTempString_16 = new StringBuilder( szTempString_16 );
                        ZeidonStringConcat( sb_szTempString_16, 1, 0, ":", 1, 0, 255 );
            szTempString_16 = sb_szTempString_16.toString( );}
            SetAttributeFromString( lTrnscpt, "PrintMinorEntry", "PromptValue", szTempString_16 );
         } 

         RESULT = lTrnscpt.cursor( "StudentMinorDegreeTrack" ).setNextContinue().toInt();;
         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// Certificate Transcript
      //:// Create a group for each degree.
      //:SET CURSOR FIRST lTrnscpt.StudentMajorDegreeTrack 
      RESULT = lTrnscpt.cursor( "StudentMajorDegreeTrack" ).setFirst().toInt();
      //:IF RESULT >= zCURSOR_SET 
      if ( RESULT >= zCURSOR_SET )
      { 
         //:lTrnscpt.Student.wTranscriptCreditCEU_Unit = "Units"
         SetAttributeFromString( lTrnscpt, "Student", "wTranscriptCreditCEU_Unit", "Units" );
         //:CREATE ENTITY lTrnscpt.PrintDegreeMajorMinorGroup
         RESULT = CreateEntity( lTrnscpt, "PrintDegreeMajorMinorGroup", zPOS_AFTER );
         //:IF lTrnscpt.StudentMajorDegreeTrack.CertificateCompletionDate != ""
         if ( CompareAttributeToString( lTrnscpt, "StudentMajorDegreeTrack", "CertificateCompletionDate", "" ) != 0 )
         { 
            //:// Completion Date exists so format as "Certificate(s) Awarded".
            //:CREATE ENTITY lTrnscpt.DegreesAwardedTitle 
            RESULT = CreateEntity( lTrnscpt, "DegreesAwardedTitle", zPOS_AFTER );
            //:lTrnscpt.DegreesAwardedTitle.Title = "Certificate(s) Awarded" 
            SetAttributeFromString( lTrnscpt, "DegreesAwardedTitle", "Title", "Certificate(s) Awarded" );
            //:FOR EACH lTrnscpt.StudentMajorDegreeTrack
            RESULT = lTrnscpt.cursor( "StudentMajorDegreeTrack" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:CREATE ENTITY lTrnscpt.PrintCertificateGroup 
               RESULT = CreateEntity( lTrnscpt, "PrintCertificateGroup", zPOS_AFTER );
               //:lTrnscpt.PrintCertificateGroup.CompletionDate = lTrnscpt.StudentMajorDegreeTrack.CertificateCompletionDate 
               SetAttributeFromAttribute( lTrnscpt, "PrintCertificateGroup", "CompletionDate", lTrnscpt, "StudentMajorDegreeTrack", "CertificateCompletionDate" );
               //:IF lTrnscpt.MajorDegreeTrack.TranscriptDisplayName = ""
               if ( CompareAttributeToString( lTrnscpt, "MajorDegreeTrack", "TranscriptDisplayName", "" ) == 0 )
               { 
                  //:lTrnscpt.PrintCertificateGroup.CertificateName = lTrnscpt.MajorDegreeTrack.Name 
                  SetAttributeFromAttribute( lTrnscpt, "PrintCertificateGroup", "CertificateName", lTrnscpt, "MajorDegreeTrack", "Name" );
                  //:ELSE
               } 
               else
               { 
                  //:lTrnscpt.PrintCertificateGroup.CertificateName = lTrnscpt.MajorDegreeTrack.TranscriptDisplayName
                  SetAttributeFromAttribute( lTrnscpt, "PrintCertificateGroup", "CertificateName", lTrnscpt, "MajorDegreeTrack", "TranscriptDisplayName" );
               } 

               //:END
               //:IF lTrnscpt.CollegeDegree.TranscriptCreditCEU_Unit = "U"
               if ( CompareAttributeToString( lTrnscpt, "CollegeDegree", "TranscriptCreditCEU_Unit", "U" ) == 0 )
               { 
                  //:lTrnscpt.PrintCertificateGroup.Units = "CEU's"
                  SetAttributeFromString( lTrnscpt, "PrintCertificateGroup", "Units", "CEU's" );
                  //:ELSE
               } 
               else
               { 
                  //:lTrnscpt.PrintCertificateGroup.Units = "Credits"
                  SetAttributeFromString( lTrnscpt, "PrintCertificateGroup", "Units", "Credits" );
               } 

               RESULT = lTrnscpt.cursor( "StudentMajorDegreeTrack" ).setNextContinue().toInt();;
               //:END 
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:// No Completion Date exists so format as "Certificate(s) Pursued".
            //:CREATE ENTITY lTrnscpt.DegreesAwardedTitle 
            RESULT = CreateEntity( lTrnscpt, "DegreesAwardedTitle", zPOS_AFTER );
            //:lTrnscpt.DegreesAwardedTitle.Title = "Certificate(s) Pursued"
            SetAttributeFromString( lTrnscpt, "DegreesAwardedTitle", "Title", "Certificate(s) Pursued" );
            //:FOR EACH lTrnscpt.StudentMajorDegreeTrack
            RESULT = lTrnscpt.cursor( "StudentMajorDegreeTrack" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:CREATE ENTITY lTrnscpt.PrintDegreeGroup 
               RESULT = CreateEntity( lTrnscpt, "PrintDegreeGroup", zPOS_AFTER );
               //:CREATE ENTITY lTrnscpt.PrintMajorEntry 
               RESULT = CreateEntity( lTrnscpt, "PrintMajorEntry", zPOS_AFTER );
               //:lTrnscpt.PrintMajorEntry.PromptValue = "Certificate:"
               SetAttributeFromString( lTrnscpt, "PrintMajorEntry", "PromptValue", "Certificate:" );
               //:IF lTrnscpt.MajorDegreeTrack.TranscriptDisplayName = ""
               if ( CompareAttributeToString( lTrnscpt, "MajorDegreeTrack", "TranscriptDisplayName", "" ) == 0 )
               { 
                  //:lTrnscpt.PrintMajorEntry.MajorValue = lTrnscpt.MajorDegreeTrack.Name 
                  SetAttributeFromAttribute( lTrnscpt, "PrintMajorEntry", "MajorValue", lTrnscpt, "MajorDegreeTrack", "Name" );
                  //:ELSE
               } 
               else
               { 
                  //:lTrnscpt.PrintMajorEntry.MajorValue = lTrnscpt.MajorDegreeTrack.TranscriptDisplayName
                  SetAttributeFromAttribute( lTrnscpt, "PrintMajorEntry", "MajorValue", lTrnscpt, "MajorDegreeTrack", "TranscriptDisplayName" );
               } 

               //:END
               //:CREATE ENTITY lTrnscpt.PrintMajorEntry 
               RESULT = CreateEntity( lTrnscpt, "PrintMajorEntry", zPOS_AFTER );
               //:lTrnscpt.PrintMajorEntry.PromptValue = "Units:"
               SetAttributeFromString( lTrnscpt, "PrintMajorEntry", "PromptValue", "Units:" );
               //:IF lTrnscpt.CollegeDegree.TranscriptCreditCEU_Unit = "U"
               if ( CompareAttributeToString( lTrnscpt, "CollegeDegree", "TranscriptCreditCEU_Unit", "U" ) == 0 )
               { 
                  //:lTrnscpt.PrintMajorEntry.MajorValue = "CEU's"
                  SetAttributeFromString( lTrnscpt, "PrintMajorEntry", "MajorValue", "CEU's" );
                  //:ELSE
               } 
               else
               { 
                  //:lTrnscpt.PrintMajorEntry.MajorValue = "Credits"
                  SetAttributeFromString( lTrnscpt, "PrintMajorEntry", "MajorValue", "Credits" );
               } 

               RESULT = lTrnscpt.cursor( "StudentMajorDegreeTrack" ).setNextContinue().toInt();;
               //:END 
            } 

            //:END 
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:// Create Transcript Summary values.
   //:IF lTrnscpt.TranscriptSummary DOES NOT EXIST
   lTempInteger_47 = CheckExistenceOfEntity( lTrnscpt, "TranscriptSummary" );
   if ( lTempInteger_47 != 0 )
   { 
      //:IF lTrnscpt.PrintDegreeMajorMinorGroup DOES NOT EXIST
      lTempInteger_48 = CheckExistenceOfEntity( lTrnscpt, "PrintDegreeMajorMinorGroup" );
      if ( lTempInteger_48 != 0 )
      { 
         //:CREATE ENTITY lTrnscpt.PrintDegreeMajorMinorGroup  
         RESULT = CreateEntity( lTrnscpt, "PrintDegreeMajorMinorGroup", zPOS_AFTER );
      } 

      //:END
      //:CREATE ENTITY lTrnscpt.TranscriptSummary  
      RESULT = CreateEntity( lTrnscpt, "TranscriptSummary", zPOS_AFTER );
   } 

   //:END

   //:SET CURSOR FIRST lTrnscpt.Registration
   RESULT = lTrnscpt.cursor( "Registration" ).setFirst().toInt();
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dStudentFullName( VIEW lTrnscpt BASED ON LOD lTrnscpt,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING ( 100 ) szFullName
public int 
olTrnscpt_dStudentFullName( View     lTrnscpt,
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

         //:GetPersonFullName( szFullName, lTrnscpt, "Person" )
         {
          ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( lTrnscpt );
          {StringBuilder sb_szFullName;
         if ( szFullName == null )
            sb_szFullName = new StringBuilder( 32 );
         else
            sb_szFullName = new StringBuilder( szFullName );
                   m_ZGLOBAL2_Operation.GetPersonFullName( sb_szFullName, lTrnscpt, "Person" );
         szFullName = sb_szFullName.toString( );}
          // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
         }

         //:StoreStringInRecord ( lTrnscpt,
         //:                   InternalEntityStructure, InternalAttribStructure, szFullName )
         StoreStringInRecord( lTrnscpt, InternalEntityStructure, InternalAttribStructure, szFullName );
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
//:ProcessRepeatRecur( VIEW lTrnscptRepeating BASED ON LOD lTrnscpt,
//:                    VIEW lTrnscptNextOrig  BASED ON LOD lTrnscpt )

//:   VIEW lTrnscptNext BASED ON LOD lTrnscpt
private int 
olTrnscpt_ProcessRepeatRecur( View     lTrnscptRepeating,
                              View     lTrnscptNextOrig )
{
   zVIEW    lTrnscptNext = new zVIEW( );
   //:DECIMAL EarlierGrade
   double  EarlierGrade = 0.0;
   //:DECIMAL LaterGrade
   double  LaterGrade = 0.0;
   //:STRING ( 20 ) szDecimalString
   String   szDecimalString = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;


   //:// This is a recursive operation for looking for entries that repeat a Class. It may be called by the
   //:// initial check for a Registration entry to see if it is repeated, or it may be called for an additional
   //:// check to see if an entry is repeated more than once.
   //:// If the operation is being called for the initial entry to see if it is repeated, both lTrnscptRepeating
   //:// and lTrnscptNext are positioned on the same entity.
   //:// If the operation is being called to look for an additional repeating entry, lTrnscptRepeating is positioned
   //:// on the "repeating" Registration entry (or the latest one if the grade for both entries is the same), and
   //:// lTrnscptNext will be positioned to look for the next repeating entry.

   //:CreateViewFromView( lTrnscptNext, lTrnscptNextOrig )
   CreateViewFromView( lTrnscptNext, lTrnscptNextOrig );
   //:SET CURSOR NEXT lTrnscptNext.Registration 
   //:          WHERE lTrnscptNext.Registration.wCourseID = lTrnscptRepeating.Registration.wCourseID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lTrnscptRepeating, "Registration", "wCourseID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   RESULT = lTrnscptNext.cursor( "Registration" ).setNext( "wCourseID", lTempInteger_0 ).toInt();
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:IF lTrnscptRepeating.Registration.OverridingGradePointValue = "" 
      if ( CompareAttributeToString( lTrnscptRepeating, "Registration", "OverridingGradePointValue", "" ) == 0 )
      { 
         //:GetStringFromAttributeByContext( szDecimalString,
         //:                                 lTrnscptRepeating, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 )
         {StringBuilder sb_szDecimalString;
         if ( szDecimalString == null )
            sb_szDecimalString = new StringBuilder( 32 );
         else
            sb_szDecimalString = new StringBuilder( szDecimalString );
                   GetStringFromAttributeByContext( sb_szDecimalString, lTrnscptRepeating, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 );
         szDecimalString = sb_szDecimalString.toString( );}
         //:EarlierGrade = StrToDecimal( szDecimalString )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( lTrnscptRepeating );
          EarlierGrade = m_ZGLOBAL1_Operation.StrToDecimal( szDecimalString );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:ELSE
      } 
      else
      { 
         //:EarlierGrade = lTrnscptRepeating.Registration.OverridingGradePointValue
         {MutableDouble md_EarlierGrade = new MutableDouble( EarlierGrade );
                   GetDecimalFromAttribute( md_EarlierGrade, lTrnscptRepeating, "Registration", "OverridingGradePointValue" );
         EarlierGrade = md_EarlierGrade.doubleValue( );}
      } 

      //:END
      //:IF lTrnscptNext.Registration.OverridingGradePointValue = "" 
      if ( CompareAttributeToString( lTrnscptNext, "Registration", "OverridingGradePointValue", "" ) == 0 )
      { 
         //:GetStringFromAttributeByContext( szDecimalString,
         //:                                 lTrnscptNext, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 )
         {StringBuilder sb_szDecimalString;
         if ( szDecimalString == null )
            sb_szDecimalString = new StringBuilder( 32 );
         else
            sb_szDecimalString = new StringBuilder( szDecimalString );
                   GetStringFromAttributeByContext( sb_szDecimalString, lTrnscptNext, "Registration", "FinalGrade", "DegreeTrackGradePointValue", 20 );
         szDecimalString = sb_szDecimalString.toString( );}
         //:LaterGrade = StrToDecimal( szDecimalString )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( lTrnscptRepeating );
          LaterGrade = m_ZGLOBAL1_Operation.StrToDecimal( szDecimalString );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:ELSE
      } 
      else
      { 
         //:LaterGrade = lTrnscptNext.Registration.OverridingGradePointValue
         {MutableDouble md_LaterGrade = new MutableDouble( LaterGrade );
                   GetDecimalFromAttribute( md_LaterGrade, lTrnscptNext, "Registration", "OverridingGradePointValue" );
         LaterGrade = md_LaterGrade.doubleValue( );}
      } 

      //:END
      //:// We will only consider entries with final grades.
      //:IF lTrnscptNext.Registration.FinalGrade != ""
      if ( CompareAttributeToString( lTrnscptNext, "Registration", "FinalGrade", "" ) != 0 )
      { 
         //:IF LaterGrade >= EarlierGrade
         if ( LaterGrade >= EarlierGrade )
         { 
            //:lTrnscptRepeating.Registration.wRepeatedClass       = "R"     // This Class is "repeated" by another Class.
            SetAttributeFromString( lTrnscptRepeating, "Registration", "wRepeatedClass", "R" );
            //:lTrnscptNext.Registration.wRepeatedClass            = "P"     // This Class "replaces" the "repeated" Class.
            SetAttributeFromString( lTrnscptNext, "Registration", "wRepeatedClass", "P" );
            //:// Set the hours that will be replaced for CumulativeAttempted for the entry when the Class is retaken.
            //:lTrnscptNext.Registration.wReplacedAttemptedCredits = lTrnscptRepeating.Registration.CreditHours 
            SetAttributeFromAttribute( lTrnscptNext, "Registration", "wReplacedAttemptedCredits", lTrnscptRepeating, "Registration", "CreditHours" );
            //:IF EarlierGrade > 0 OR 
            //:   lTrnscptRepeating.Registration.FinalGrade = "S" OR
            //:   lTrnscptRepeating.Registration.FinalGrade = ""
            if ( EarlierGrade > 0 || CompareAttributeToString( lTrnscptRepeating, "Registration", "FinalGrade", "S" ) == 0 || CompareAttributeToString( lTrnscptRepeating, "Registration", "FinalGrade", "" ) == 0 )
            { 
               //:// Earned Credits are only adjusted if the Repeated Class grade had grade points.
               //:lTrnscptNext.Registration.wReplacedEarnedCredits = lTrnscptRepeating.Registration.CreditHours 
               SetAttributeFromAttribute( lTrnscptNext, "Registration", "wReplacedEarnedCredits", lTrnscptRepeating, "Registration", "CreditHours" );
            } 

            //:END
            //:// Grade of U, S or W does not impact GPA values.
            //:// The following IF was replaced on 10/11/06 by DonC to correct the setting of wReplacedGPA_DivisorCredits for
            //:// "W" grades.
            //:/*IF lTrnscptNext.Registration.FinalGrade != "U" AND 
            //:   lTrnscptNext.Registration.FinalGrade != "S" AND 
            //:   lTrnscptNext.Registration.FinalGrade != "W" AND 
            //:   lTrnscptNext.Registration.FinalGrade != ""*/
            //:IF lTrnscptRepeating.Registration.FinalGrade != "U" AND 
            //:   lTrnscptRepeating.Registration.FinalGrade != "S" AND 
            //:   lTrnscptRepeating.Registration.FinalGrade != "W" AND 
            //:   lTrnscptRepeating.Registration.FinalGrade != ""  AND 
            //:   lTrnscptNext.Registration.FinalGrade != "U" AND 
            //:   lTrnscptNext.Registration.FinalGrade != "S" AND 
            //:   lTrnscptNext.Registration.FinalGrade != "W" AND 
            //:   lTrnscptNext.Registration.FinalGrade != ""
            if ( CompareAttributeToString( lTrnscptRepeating, "Registration", "FinalGrade", "U" ) != 0 && CompareAttributeToString( lTrnscptRepeating, "Registration", "FinalGrade", "S" ) != 0 &&
                 CompareAttributeToString( lTrnscptRepeating, "Registration", "FinalGrade", "W" ) != 0 && CompareAttributeToString( lTrnscptRepeating, "Registration", "FinalGrade", "" ) != 0 &&
                 CompareAttributeToString( lTrnscptNext, "Registration", "FinalGrade", "U" ) != 0 && CompareAttributeToString( lTrnscptNext, "Registration", "FinalGrade", "S" ) != 0 &&
                 CompareAttributeToString( lTrnscptNext, "Registration", "FinalGrade", "W" ) != 0 && CompareAttributeToString( lTrnscptNext, "Registration", "FinalGrade", "" ) != 0 )
            { 

               //:lTrnscptNext.Registration.wReplacedGPA_DivisorCredits = lTrnscptRepeating.Registration.CreditHours
               SetAttributeFromAttribute( lTrnscptNext, "Registration", "wReplacedGPA_DivisorCredits", lTrnscptRepeating, "Registration", "CreditHours" );
               //:lTrnscptNext.Registration.wReplacedGPA_Points = EarlierGrade * lTrnscptRepeating.Registration.CreditHours
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, lTrnscptRepeating, "Registration", "CreditHours" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dTempDecimal_1 = EarlierGrade * dTempDecimal_0;
               SetAttributeFromDecimal( lTrnscptNext, "Registration", "wReplacedGPA_Points", dTempDecimal_1 );
            } 

            //:END

            //:// Look for next repeating entry.
            //:ProcessRepeatRecur( lTrnscptNext, lTrnscptNext )
            olTrnscpt_ProcessRepeatRecur( lTrnscptNext, lTrnscptNext );

            //:ELSE
         } 
         else
         { 
            //:lTrnscptNext.Registration.wRepeatedClass      = "V"     // This Class record is considered "void", as the earlier Class is used.
            SetAttributeFromString( lTrnscptNext, "Registration", "wRepeatedClass", "V" );
            //:lTrnscptRepeating.Registration.wRepeatedClass = "P"     // This Class "replaces" the "voided" Class.
            SetAttributeFromString( lTrnscptRepeating, "Registration", "wRepeatedClass", "P" );
            //:// Attempted Credits, Earned Credits, Divisor Credits and GPA Points aren't affected in the "voided" Class for this case.

            //:// Look for next repeating entry.
            //:ProcessRepeatRecur( lTrnscptRepeating, lTrnscptNext )
            olTrnscpt_ProcessRepeatRecur( lTrnscptRepeating, lTrnscptNext );
         } 

         //:END
      } 

      //:END
   } 

   //:END
   //:DropView( lTrnscptNext )
   DropView( lTrnscptNext );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dDisplayTranscriptName( VIEW lTrnscpt BASED ON LOD lTrnscpt,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   STRING ( 100 ) szTranscriptName
public int 
olTrnscpt_dDisplayTranscriptName( View     lTrnscpt,
                                  String InternalEntityStructure,
                                  String InternalAttribStructure,
                                  Integer   GetOrSetFlag )
{
   String   szTranscriptName = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:IF lTrnscpt.Student.LegalName = ""
         if ( CompareAttributeToString( lTrnscpt, "Student", "LegalName", "" ) == 0 )
         { 
            //:szTranscriptName = lTrnscpt.Person.dFullName 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTranscriptName;
            if ( szTranscriptName == null )
               sb_szTranscriptName = new StringBuilder( 32 );
            else
               sb_szTranscriptName = new StringBuilder( szTranscriptName );
                         GetVariableFromAttribute( sb_szTranscriptName, mi_lTempInteger_0, 'S', 101, lTrnscpt, "Person", "dFullName", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTranscriptName = sb_szTranscriptName.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szTranscriptName = lTrnscpt.Student.LegalName 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTranscriptName;
            if ( szTranscriptName == null )
               sb_szTranscriptName = new StringBuilder( 32 );
            else
               sb_szTranscriptName = new StringBuilder( szTranscriptName );
                         GetVariableFromAttribute( sb_szTranscriptName, mi_lTempInteger_1, 'S', 101, lTrnscpt, "Student", "LegalName", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTranscriptName = sb_szTranscriptName.toString( );}
         } 

         //:END
         //:StoreStringInRecord ( lTrnscpt,
         //:                      InternalEntityStructure, InternalAttribStructure, szTranscriptName )
         StoreStringInRecord( lTrnscpt, InternalEntityStructure, InternalAttribStructure, szTranscriptName );
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
//:BuildPrintGroup( VIEW lTrnscpt BASED ON LOD lTrnscpt )

//:   STRING ( 10 ) szAttemptedCredits
public int 
olTrnscpt_BuildPrintGroup( View     lTrnscpt )
{
   String   szAttemptedCredits = null;
   //:STRING ( 10 ) szAttemptedCreditsOrig
   String   szAttemptedCreditsOrig = null;
   //:STRING ( 20 ) szFinalGrade
   String   szFinalGrade = null;
   //:STRING ( 1 )  szGPA_PointsTwoDecimalDigitsFlag
   String   szGPA_PointsTwoDecimalDigitsFlag = null;
   //:DECIMAL GPA_Points
   double  GPA_Points = 0.0;
   //:DECIMAL GPA_Divisor
   double  GPA_Divisor = 0.0;
   //:DECIMAL TermAttemptedCredits
   double  TermAttemptedCredits = 0.0;
   //:DECIMAL TermEarnedCredits
   double  TermEarnedCredits = 0.0;
   //:DECIMAL TermGPA_Points
   double  TermGPA_Points = 0.0;
   //:DECIMAL TermGPA_Divisor
   double  TermGPA_Divisor = 0.0;
   //:DECIMAL TermGPA
   double  TermGPA = 0.0;
   //:DECIMAL CumulativeAttemptedCredits
   double  CumulativeAttemptedCredits = 0.0;
   //:DECIMAL CumulativeEarnedCredits
   double  CumulativeEarnedCredits = 0.0;
   //:DECIMAL CumulativeGPA_Points
   double  CumulativeGPA_Points = 0.0;
   //:DECIMAL CumulativeGPA_Divisor
   double  CumulativeGPA_Divisor = 0.0;
   //:DECIMAL CumulativeGPA
   double  CumulativeGPA = 0.0;
   //:DECIMAL CreditHours
   double  CreditHours = 0.0;
   //:DECIMAL FinalGrade
   double  FinalGrade = 0.0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
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
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_2 = null;
   String   szTempString_3 = null;


   //:// Initialize Cumulative Values.
   //:CumulativeAttemptedCredits = 0
   CumulativeAttemptedCredits = 0;
   //:CumulativeEarnedCredits    = 0
   CumulativeEarnedCredits = 0;
   //:CumulativeGPA_Points       = 0
   CumulativeGPA_Points = 0;
   //:CumulativeGPA_Divisor      = 0
   CumulativeGPA_Divisor = 0;

   //:// Loop throught Term and Class entries to create totals and set repeat indicators.
   //:FOR EACH lTrnscpt.TranscriptGroup 
   RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Cumulative for regular entries
      //:TermAttemptedCredits = 0
      TermAttemptedCredits = 0;
      //:TermEarnedCredits    = 0
      TermEarnedCredits = 0;
      //:TermGPA_Points       = 0
      TermGPA_Points = 0;
      //:TermGPA_Divisor      = 0
      TermGPA_Divisor = 0;

      //:// If any Registration entries in this group have a OverridingGradePointValue value, then flag the group
      //:// as needing to carry GPA_Points to two digits.
      //:SET CURSOR FIRST lTrnscpt.TermRegistration 
      //:           WHERE lTrnscpt.TermRegistration.OverridingGradePointValue != ""
      RESULT = lTrnscpt.cursor( "TermRegistration" ).setFirst().toInt();
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( lTrnscpt, "TermRegistration", "OverridingGradePointValue", "" ) == 0 ) )
         { 
            RESULT = lTrnscpt.cursor( "TermRegistration" ).setNextContinue().toInt();;
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:szGPA_PointsTwoDecimalDigitsFlag = "Y"
          {StringBuilder sb_szGPA_PointsTwoDecimalDigitsFlag;
         if ( szGPA_PointsTwoDecimalDigitsFlag == null )
            sb_szGPA_PointsTwoDecimalDigitsFlag = new StringBuilder( 32 );
         else
            sb_szGPA_PointsTwoDecimalDigitsFlag = new StringBuilder( szGPA_PointsTwoDecimalDigitsFlag );
                  ZeidonStringCopy( sb_szGPA_PointsTwoDecimalDigitsFlag, 1, 0, "Y", 1, 0, 2 );
         szGPA_PointsTwoDecimalDigitsFlag = sb_szGPA_PointsTwoDecimalDigitsFlag.toString( );}
         //:lTrnscpt.TranscriptGroup.wGPA_PointsTwoDecimalDigitsFlag = "Y"
         SetAttributeFromString( lTrnscpt, "TranscriptGroup", "wGPA_PointsTwoDecimalDigitsFlag", "Y" );
      } 

      //:END 

      //:FOR EACH lTrnscpt.TermRegistration
      RESULT = lTrnscpt.cursor( "TermRegistration" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 

         //:// Set parens around the credits of any Class that has been repeated.
         //:IF lTrnscpt.TermRegistration.wRepeatedClass = "R" OR lTrnscpt.TermRegistration.wRepeatedClass = "V"
         if ( CompareAttributeToString( lTrnscpt, "TermRegistration", "wRepeatedClass", "R" ) == 0 || CompareAttributeToString( lTrnscpt, "TermRegistration", "wRepeatedClass", "V" ) == 0 )
         { 
            //:lTrnscpt.TermRegistration.wDisplayLeftParen = "("
            SetAttributeFromString( lTrnscpt, "TermRegistration", "wDisplayLeftParen", "(" );
            //:lTrnscpt.TermRegistration.wDisplayRightParen = ")"
            SetAttributeFromString( lTrnscpt, "TermRegistration", "wDisplayRightParen", ")" );
         } 

         //:END

         //:// Set an asterisk for any Class that "replaces" a "repeated" Class, unless it is also a "repeated" Class.
         //:IF lTrnscpt.TermRegistration.wRepeatedClass = "P" AND lTrnscpt.TermRegistration.wRepeatedClass != "R"
         if ( CompareAttributeToString( lTrnscpt, "TermRegistration", "wRepeatedClass", "P" ) == 0 && CompareAttributeToString( lTrnscpt, "TermRegistration", "wRepeatedClass", "R" ) != 0 )
         { 
            //:lTrnscpt.TermRegistration.wDisplayAsterisk = "*"
            SetAttributeFromString( lTrnscpt, "TermRegistration", "wDisplayAsterisk", "*" );
         } 

         //:END

         //:CreditHours  = lTrnscpt.TermRegistration.CreditHours 
         {MutableDouble md_CreditHours = new MutableDouble( CreditHours );
                   GetDecimalFromAttribute( md_CreditHours, lTrnscpt, "TermRegistration", "CreditHours" );
         CreditHours = md_CreditHours.doubleValue( );}
         //:szFinalGrade = lTrnscpt.TermRegistration.FinalGrade 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szFinalGrade;
         if ( szFinalGrade == null )
            sb_szFinalGrade = new StringBuilder( 32 );
         else
            sb_szFinalGrade = new StringBuilder( szFinalGrade );
                   GetVariableFromAttribute( sb_szFinalGrade, mi_lTempInteger_0, 'S', 21, lTrnscpt, "TermRegistration", "FinalGrade", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szFinalGrade = sb_szFinalGrade.toString( );}

         //:IF szFinalGrade = "F"
         if ( ZeidonStringCompare( szFinalGrade, 1, 0, "F", 1, 0, 21 ) == 0 )
         { 

            //:// Failed Course
            //:lTrnscpt.TermRegistration.AttemptedCredits = CreditHours
            SetAttributeFromDecimal( lTrnscpt, "TermRegistration", "AttemptedCredits", CreditHours );
            //:lTrnscpt.TermRegistration.EarnedCredits    = 0
            SetAttributeFromInteger( lTrnscpt, "TermRegistration", "EarnedCredits", 0 );
            //:lTrnscpt.TermRegistration.GPA_Points       = 0
            SetAttributeFromInteger( lTrnscpt, "TermRegistration", "GPA_Points", 0 );
            //:lTrnscpt.TermRegistration.GPA_Divisor      = CreditHours
            SetAttributeFromDecimal( lTrnscpt, "TermRegistration", "GPA_Divisor", CreditHours );

            //:TermAttemptedCredits = TermAttemptedCredits + CreditHours
            TermAttemptedCredits = TermAttemptedCredits + CreditHours;
            //:TermGPA_Divisor      = TermGPA_Divisor      + CreditHours
            TermGPA_Divisor = TermGPA_Divisor + CreditHours;

            //:IF lTrnscpt.TermRegistration.wRepeatedClass != "V"   // Repeats with "V" don't impact cumulative entries.
            if ( CompareAttributeToString( lTrnscpt, "TermRegistration", "wRepeatedClass", "V" ) != 0 )
            { 
               //:CumulativeAttemptedCredits = CumulativeAttemptedCredits + CreditHours - lTrnscpt.TermRegistration.wReplacedAttemptedCredits
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, lTrnscpt, "TermRegistration", "wReplacedAttemptedCredits" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               CumulativeAttemptedCredits = CumulativeAttemptedCredits + CreditHours - dTempDecimal_0;
               //:CumulativeGPA_Divisor      = CumulativeGPA_Divisor      + CreditHours - lTrnscpt.TermRegistration.wReplacedGPA_DivisorCredits
               {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                               GetDecimalFromAttribute( md_dTempDecimal_1, lTrnscpt, "TermRegistration", "wReplacedGPA_DivisorCredits" );
               dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
               CumulativeGPA_Divisor = CumulativeGPA_Divisor + CreditHours - dTempDecimal_1;
            } 

            //:END

            //:ELSE
         } 
         else
         { 

            //:IF szFinalGrade = "U"
            if ( ZeidonStringCompare( szFinalGrade, 1, 0, "U", 1, 0, 21 ) == 0 )
            { 

               //:// Unsatisfactory for Course
               //:lTrnscpt.TermRegistration.AttemptedCredits = CreditHours
               SetAttributeFromDecimal( lTrnscpt, "TermRegistration", "AttemptedCredits", CreditHours );
               //:lTrnscpt.TermRegistration.EarnedCredits    = 0
               SetAttributeFromInteger( lTrnscpt, "TermRegistration", "EarnedCredits", 0 );
               //:lTrnscpt.TermRegistration.GPA_Points       = 0
               SetAttributeFromInteger( lTrnscpt, "TermRegistration", "GPA_Points", 0 );
               //:lTrnscpt.TermRegistration.GPA_Divisor      = 0
               SetAttributeFromInteger( lTrnscpt, "TermRegistration", "GPA_Divisor", 0 );

               //:TermAttemptedCredits = TermAttemptedCredits + CreditHours
               TermAttemptedCredits = TermAttemptedCredits + CreditHours;

               //:IF lTrnscpt.TermRegistration.wRepeatedClass != "V"   // Repeats with "V" don't impact cumulative entries.
               if ( CompareAttributeToString( lTrnscpt, "TermRegistration", "wRepeatedClass", "V" ) != 0 )
               { 
                  //:CumulativeAttemptedCredits = CumulativeAttemptedCredits + CreditHours - lTrnscpt.TermRegistration.wReplacedAttemptedCredits
                  {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                                     GetDecimalFromAttribute( md_dTempDecimal_2, lTrnscpt, "TermRegistration", "wReplacedAttemptedCredits" );
                  dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
                  CumulativeAttemptedCredits = CumulativeAttemptedCredits + CreditHours - dTempDecimal_2;
               } 

               //:END

               //:ELSE
            } 
            else
            { 

               //:IF szFinalGrade = "IP" OR
               //:szFinalGrade = "I" OR
               //:szFinalGrade = "NC" OR
               //:szFinalGrade = "NG" OR
               //:szFinalGrade = "W" OR
               //:szFinalGrade = "X" OR
               //:szFinalGrade = "AI"
               if ( ZeidonStringCompare( szFinalGrade, 1, 0, "IP", 1, 0, 21 ) == 0 || ZeidonStringCompare( szFinalGrade, 1, 0, "I", 1, 0, 21 ) == 0 || ZeidonStringCompare( szFinalGrade, 1, 0, "NC", 1, 0, 21 ) == 0 ||
                    ZeidonStringCompare( szFinalGrade, 1, 0, "NG", 1, 0, 21 ) == 0 || ZeidonStringCompare( szFinalGrade, 1, 0, "W", 1, 0, 21 ) == 0 || ZeidonStringCompare( szFinalGrade, 1, 0, "X", 1, 0, 21 ) == 0 ||
                    ZeidonStringCompare( szFinalGrade, 1, 0, "AI", 1, 0, 21 ) == 0 )
               { 

                  //:// Only Attempted Credits are considered for these Grade values.
                  //:lTrnscpt.TermRegistration.AttemptedCredits = CreditHours
                  SetAttributeFromDecimal( lTrnscpt, "TermRegistration", "AttemptedCredits", CreditHours );
                  //:IF lTrnscpt.TermRegistration.wRepeatedClass != "V"   // Repeats with "V" don't impact cumulative entries.
                  if ( CompareAttributeToString( lTrnscpt, "TermRegistration", "wRepeatedClass", "V" ) != 0 )
                  { 
                     //:TermAttemptedCredits = TermAttemptedCredits + CreditHours
                     TermAttemptedCredits = TermAttemptedCredits + CreditHours;
                     //:CumulativeAttemptedCredits = CumulativeAttemptedCredits + CreditHours - lTrnscpt.TermRegistration.wReplacedAttemptedCredits
                     {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                                           GetDecimalFromAttribute( md_dTempDecimal_3, lTrnscpt, "TermRegistration", "wReplacedAttemptedCredits" );
                     dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
                     CumulativeAttemptedCredits = CumulativeAttemptedCredits + CreditHours - dTempDecimal_3;
                  } 

                  //:END
                  //:/*IF szFinalGrade = "W"
                  //:// For Withdrawn, Attempted are also 0.
                  //:lTrnscpt.TermRegistration.AttemptedCredits = 0
                  //:ELSE
                  //:lTrnscpt.TermRegistration.AttemptedCredits = CreditHours
                  //:IF lTrnscpt.TermRegistration.wRepeatedClass != "V"   // Repeats with "V" don't impact cumulative entries.
                  //:TermAttemptedCredits = TermAttemptedCredits + CreditHours
                  //:CumulativeAttemptedCredits = CumulativeAttemptedCredits + CreditHours - lTrnscpt.TermRegistration.wReplacedAttemptedCredits
                  //:END
                  //:END*/
                  //:lTrnscpt.TermRegistration.EarnedCredits    = 0
                  SetAttributeFromInteger( lTrnscpt, "TermRegistration", "EarnedCredits", 0 );
                  //:lTrnscpt.TermRegistration.GPA_Points       = 0
                  SetAttributeFromInteger( lTrnscpt, "TermRegistration", "GPA_Points", 0 );
                  //:lTrnscpt.TermRegistration.GPA_Divisor      = 0
                  SetAttributeFromInteger( lTrnscpt, "TermRegistration", "GPA_Divisor", 0 );

                  //:ELSE
               } 
               else
               { 

                  //:IF szFinalGrade = "S" OR szFinalGrade = ""
                  if ( ZeidonStringCompare( szFinalGrade, 1, 0, "S", 1, 0, 21 ) == 0 || ZeidonStringCompare( szFinalGrade, 1, 0, "", 1, 0, 21 ) == 0 )
                  { 

                     //:// Passed or transferred Course WITHOUT Grade
                     //:GetStringFromAttributeByContext( szFinalGrade,
                     //:                        lTrnscpt, "TermRegistration", "FinalGrade", "DegreeTrackGradePointValue", 20 )
                     {StringBuilder sb_szFinalGrade;
                     if ( szFinalGrade == null )
                        sb_szFinalGrade = new StringBuilder( 32 );
                     else
                        sb_szFinalGrade = new StringBuilder( szFinalGrade );
                                           GetStringFromAttributeByContext( sb_szFinalGrade, lTrnscpt, "TermRegistration", "FinalGrade", "DegreeTrackGradePointValue", 20 );
                     szFinalGrade = sb_szFinalGrade.toString( );}
                     //:FinalGrade = StrToDecimal( szFinalGrade )
                     {
                      ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( lTrnscpt );
                      FinalGrade = m_ZGLOBAL1_Operation.StrToDecimal( szFinalGrade );
                      // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                     }

                     //:lTrnscpt.TermRegistration.AttemptedCredits = CreditHours
                     SetAttributeFromDecimal( lTrnscpt, "TermRegistration", "AttemptedCredits", CreditHours );
                     //:lTrnscpt.TermRegistration.EarnedCredits    = CreditHours
                     SetAttributeFromDecimal( lTrnscpt, "TermRegistration", "EarnedCredits", CreditHours );

                     //:TermAttemptedCredits = TermAttemptedCredits + CreditHours
                     TermAttemptedCredits = TermAttemptedCredits + CreditHours;
                     //:TermEarnedCredits    = TermEarnedCredits    + CreditHours
                     TermEarnedCredits = TermEarnedCredits + CreditHours;

                     //:IF lTrnscpt.TermRegistration.wRepeatedClass != "V"   // Repeats with "V" don't impact cumulative entries.
                     if ( CompareAttributeToString( lTrnscpt, "TermRegistration", "wRepeatedClass", "V" ) != 0 )
                     { 
                        //:CumulativeAttemptedCredits = CumulativeAttemptedCredits + CreditHours - lTrnscpt.TermRegistration.wReplacedAttemptedCredits
                        {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_4, lTrnscpt, "TermRegistration", "wReplacedAttemptedCredits" );
                        dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
                        CumulativeAttemptedCredits = CumulativeAttemptedCredits + CreditHours - dTempDecimal_4;
                        //:CumulativeEarnedCredits    = CumulativeEarnedCredits    + CreditHours - lTrnscpt.TermRegistration.wReplacedEarnedCredits
                        {MutableDouble md_dTempDecimal_5 = new MutableDouble( dTempDecimal_5 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_5, lTrnscpt, "TermRegistration", "wReplacedEarnedCredits" );
                        dTempDecimal_5 = md_dTempDecimal_5.doubleValue( );}
                        CumulativeEarnedCredits = CumulativeEarnedCredits + CreditHours - dTempDecimal_5;
                        //:CumulativeGPA_Points       = CumulativeGPA_Points       - lTrnscpt.TermRegistration.wReplacedGPA_Points 
                        {MutableDouble md_dTempDecimal_6 = new MutableDouble( dTempDecimal_6 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_6, lTrnscpt, "TermRegistration", "wReplacedGPA_Points" );
                        dTempDecimal_6 = md_dTempDecimal_6.doubleValue( );}
                        CumulativeGPA_Points = CumulativeGPA_Points - dTempDecimal_6;
                        //:CumulativeGPA_Divisor      = CumulativeGPA_Divisor      - lTrnscpt.TermRegistration.wReplacedGPA_DivisorCredits
                        {MutableDouble md_dTempDecimal_7 = new MutableDouble( dTempDecimal_7 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_7, lTrnscpt, "TermRegistration", "wReplacedGPA_DivisorCredits" );
                        dTempDecimal_7 = md_dTempDecimal_7.doubleValue( );}
                        CumulativeGPA_Divisor = CumulativeGPA_Divisor - dTempDecimal_7;
                     } 

                     //:END

                     //:ELSE
                  } 
                  else
                  { 

                     //:// Passed Course WITH Grade
                     //:GetStringFromAttributeByContext( szFinalGrade,
                     //:                        lTrnscpt, "TermRegistration", "FinalGrade", "DegreeTrackGradePointValue", 20 )
                     {StringBuilder sb_szFinalGrade;
                     if ( szFinalGrade == null )
                        sb_szFinalGrade = new StringBuilder( 32 );
                     else
                        sb_szFinalGrade = new StringBuilder( szFinalGrade );
                                           GetStringFromAttributeByContext( sb_szFinalGrade, lTrnscpt, "TermRegistration", "FinalGrade", "DegreeTrackGradePointValue", 20 );
                     szFinalGrade = sb_szFinalGrade.toString( );}
                     //:IF lTrnscpt.TermRegistration.OverridingGradePointValue = ""
                     if ( CompareAttributeToString( lTrnscpt, "TermRegistration", "OverridingGradePointValue", "" ) == 0 )
                     { 
                        //:FinalGrade = StrToDecimal( szFinalGrade )
                        {
                         ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( lTrnscpt );
                         FinalGrade = m_ZGLOBAL1_Operation.StrToDecimal( szFinalGrade );
                         // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                        }
                        //:ELSE
                     } 
                     else
                     { 
                        //:FinalGrade = lTrnscpt.TermRegistration.OverridingGradePointValue 
                        {MutableDouble md_FinalGrade = new MutableDouble( FinalGrade );
                                                 GetDecimalFromAttribute( md_FinalGrade, lTrnscpt, "TermRegistration", "OverridingGradePointValue" );
                        FinalGrade = md_FinalGrade.doubleValue( );}
                     } 

                     //:END

                     //:IF szGPA_PointsTwoDecimalDigitsFlag = "Y"
                     if ( ZeidonStringCompare( szGPA_PointsTwoDecimalDigitsFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
                     { 
                        //:// If two-decimal-digit GPA_Points is requested, make sure we round to two-decimal digits.
                        //:GPA_Points = FinalGrade * CreditHours
                        GPA_Points = FinalGrade * CreditHours;

                        //:ELSE
                     } 
                     else
                     { 
                        //:GPA_Points = FinalGrade * CreditHours
                        GPA_Points = FinalGrade * CreditHours;
                     } 

                     //:END

                     //:lTrnscpt.TermRegistration.AttemptedCredits = CreditHours
                     SetAttributeFromDecimal( lTrnscpt, "TermRegistration", "AttemptedCredits", CreditHours );
                     //:lTrnscpt.TermRegistration.EarnedCredits    = CreditHours
                     SetAttributeFromDecimal( lTrnscpt, "TermRegistration", "EarnedCredits", CreditHours );
                     //:lTrnscpt.TermRegistration.GPA_Points       = GPA_Points
                     SetAttributeFromDecimal( lTrnscpt, "TermRegistration", "GPA_Points", GPA_Points );
                     //:lTrnscpt.TermRegistration.GPA_Divisor      = CreditHours
                     SetAttributeFromDecimal( lTrnscpt, "TermRegistration", "GPA_Divisor", CreditHours );

                     //:TermAttemptedCredits = TermAttemptedCredits + CreditHours
                     TermAttemptedCredits = TermAttemptedCredits + CreditHours;
                     //:TermEarnedCredits    = TermEarnedCredits    + CreditHours
                     TermEarnedCredits = TermEarnedCredits + CreditHours;
                     //:TermGPA_Points       = TermGPA_Points       + lTrnscpt.TermRegistration.GPA_Points
                     {MutableDouble md_dTempDecimal_8 = new MutableDouble( dTempDecimal_8 );
                                           GetDecimalFromAttribute( md_dTempDecimal_8, lTrnscpt, "TermRegistration", "GPA_Points" );
                     dTempDecimal_8 = md_dTempDecimal_8.doubleValue( );}
                     TermGPA_Points = TermGPA_Points + dTempDecimal_8;
                     //:TermGPA_Divisor      = TermGPA_Divisor      + CreditHours
                     TermGPA_Divisor = TermGPA_Divisor + CreditHours;

                     //:IF lTrnscpt.TermRegistration.wRepeatedClass != "V"   // Repeats with "V" don't impact cumulative entries.
                     if ( CompareAttributeToString( lTrnscpt, "TermRegistration", "wRepeatedClass", "V" ) != 0 )
                     { 
                        //:CumulativeAttemptedCredits = CumulativeAttemptedCredits + CreditHours - lTrnscpt.TermRegistration.wReplacedAttemptedCredits
                        {MutableDouble md_dTempDecimal_9 = new MutableDouble( dTempDecimal_9 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_9, lTrnscpt, "TermRegistration", "wReplacedAttemptedCredits" );
                        dTempDecimal_9 = md_dTempDecimal_9.doubleValue( );}
                        CumulativeAttemptedCredits = CumulativeAttemptedCredits + CreditHours - dTempDecimal_9;
                        //:CumulativeEarnedCredits    = CumulativeEarnedCredits    + CreditHours - lTrnscpt.TermRegistration.wReplacedEarnedCredits
                        {MutableDouble md_dTempDecimal_10 = new MutableDouble( dTempDecimal_10 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_10, lTrnscpt, "TermRegistration", "wReplacedEarnedCredits" );
                        dTempDecimal_10 = md_dTempDecimal_10.doubleValue( );}
                        CumulativeEarnedCredits = CumulativeEarnedCredits + CreditHours - dTempDecimal_10;
                        //:CumulativeGPA_Points       = CumulativeGPA_Points       + lTrnscpt.TermRegistration.GPA_Points - lTrnscpt.TermRegistration.wReplacedGPA_Points 
                        {MutableDouble md_dTempDecimal_11 = new MutableDouble( dTempDecimal_11 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_11, lTrnscpt, "TermRegistration", "GPA_Points" );
                        dTempDecimal_11 = md_dTempDecimal_11.doubleValue( );}
                        {MutableDouble md_dTempDecimal_12 = new MutableDouble( dTempDecimal_12 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_12, lTrnscpt, "TermRegistration", "wReplacedGPA_Points" );
                        dTempDecimal_12 = md_dTempDecimal_12.doubleValue( );}
                        CumulativeGPA_Points = CumulativeGPA_Points + dTempDecimal_11 - dTempDecimal_12;
                        //:CumulativeGPA_Divisor      = CumulativeGPA_Divisor      + CreditHours - lTrnscpt.TermRegistration.wReplacedGPA_DivisorCredits
                        {MutableDouble md_dTempDecimal_13 = new MutableDouble( dTempDecimal_13 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_13, lTrnscpt, "TermRegistration", "wReplacedGPA_DivisorCredits" );
                        dTempDecimal_13 = md_dTempDecimal_13.doubleValue( );}
                        CumulativeGPA_Divisor = CumulativeGPA_Divisor + CreditHours - dTempDecimal_13;
                     } 

                     //:END
                  } 


                  //:END
               } 

               //:END
            } 

            //:END
         } 

         RESULT = lTrnscpt.cursor( "TermRegistration" ).setNextContinue().toInt();;
         //:END
      } 

      //:   
      //:END
      //:   
      //:IF TermGPA_Divisor = 0
      if ( TermGPA_Divisor == 0 )
      { 
         //:TermGPA = 0
         TermGPA = 0;
         //:ELSE
      } 
      else
      { 
         //:TermGPA = TermGPA_Points / TermGPA_Divisor
         TermGPA = TermGPA_Points / TermGPA_Divisor;
      } 

      //:END
      //:IF CumulativeGPA_Divisor = 0
      if ( CumulativeGPA_Divisor == 0 )
      { 
         //:CumulativeGPA = 0
         CumulativeGPA = 0;
         //:ELSE
      } 
      else
      { 
         //:CumulativeGPA = CumulativeGPA_Points / CumulativeGPA_Divisor
         CumulativeGPA = CumulativeGPA_Points / CumulativeGPA_Divisor;
      } 

      //:END

      //:lTrnscpt.TranscriptGroup.SumAttemptedCredits = TermAttemptedCredits
      SetAttributeFromDecimal( lTrnscpt, "TranscriptGroup", "SumAttemptedCredits", TermAttemptedCredits );
      //:lTrnscpt.TranscriptGroup.SumEarnedCredits    = TermEarnedCredits
      SetAttributeFromDecimal( lTrnscpt, "TranscriptGroup", "SumEarnedCredits", TermEarnedCredits );
      //:lTrnscpt.TranscriptGroup.SumGradePoints      = TermGPA_Points
      SetAttributeFromDecimal( lTrnscpt, "TranscriptGroup", "SumGradePoints", TermGPA_Points );
      //:lTrnscpt.TranscriptGroup.SumGPA_Divisor      = TermGPA_Divisor
      SetAttributeFromDecimal( lTrnscpt, "TranscriptGroup", "SumGPA_Divisor", TermGPA_Divisor );
      //:lTrnscpt.TranscriptGroup.SumGPA              = TermGPA
      SetAttributeFromDecimal( lTrnscpt, "TranscriptGroup", "SumGPA", TermGPA );

      //:lTrnscpt.TranscriptGroup.CumulativeAttemptedCredits = CumulativeAttemptedCredits
      SetAttributeFromDecimal( lTrnscpt, "TranscriptGroup", "CumulativeAttemptedCredits", CumulativeAttemptedCredits );
      //:lTrnscpt.TranscriptGroup.CumulativeEarnedCredits    = CumulativeEarnedCredits
      SetAttributeFromDecimal( lTrnscpt, "TranscriptGroup", "CumulativeEarnedCredits", CumulativeEarnedCredits );
      //:lTrnscpt.TranscriptGroup.CumulativeGPA_Points       = CumulativeGPA_Points
      SetAttributeFromDecimal( lTrnscpt, "TranscriptGroup", "CumulativeGPA_Points", CumulativeGPA_Points );
      //:lTrnscpt.TranscriptGroup.CumulativeGPA_Divisor      = CumulativeGPA_Divisor
      SetAttributeFromDecimal( lTrnscpt, "TranscriptGroup", "CumulativeGPA_Divisor", CumulativeGPA_Divisor );
      //:lTrnscpt.TranscriptGroup.CumulativeGPA              = CumulativeGPA
      SetAttributeFromDecimal( lTrnscpt, "TranscriptGroup", "CumulativeGPA", CumulativeGPA );

      //:IF lTrnscpt.TranscriptGroup.CumulativeGPA_Divisor != 0
      if ( CompareAttributeToInteger( lTrnscpt, "TranscriptGroup", "CumulativeGPA_Divisor", 0 ) != 0 )
      { 
         //:lTrnscpt.TranscriptGroup.SumGPA = lTrnscpt.TranscriptGroup.SumGradePoints / lTrnscpt.TranscriptGroup.SumGPA_Divisor 
         {MutableDouble md_dTempDecimal_14 = new MutableDouble( dTempDecimal_14 );
                   GetDecimalFromAttribute( md_dTempDecimal_14, lTrnscpt, "TranscriptGroup", "SumGradePoints" );
         dTempDecimal_14 = md_dTempDecimal_14.doubleValue( );}
         {MutableDouble md_dTempDecimal_15 = new MutableDouble( dTempDecimal_15 );
                   GetDecimalFromAttribute( md_dTempDecimal_15, lTrnscpt, "TranscriptGroup", "SumGPA_Divisor" );
         dTempDecimal_15 = md_dTempDecimal_15.doubleValue( );}
         dTempDecimal_16 = dTempDecimal_14 / dTempDecimal_15;
         SetAttributeFromDecimal( lTrnscpt, "TranscriptGroup", "SumGPA", dTempDecimal_16 );
      } 

      RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setNextContinue().toInt();;
      //:END
   } 

   //:   
   //:END

   //:// Build the Print Group
   //:FOR EACH lTrnscpt.TranscriptGroup
   RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY lTrnscpt.PrintGroup
      RESULT = CreateEntity( lTrnscpt, "PrintGroup", zPOS_AFTER );
      //:SetMatchingAttributesByName( lTrnscpt, "PrintGroup", lTrnscpt, "TranscriptGroup", zSET_ALL ) 
      SetMatchingAttributesByName( lTrnscpt, "PrintGroup", lTrnscpt, "TranscriptGroup", zSET_ALL );
      //:IF lTrnscpt.TermRegistration EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( lTrnscpt, "TermRegistration" );
      if ( lTempInteger_1 == 0 )
      { 
         //:IF lTrnscpt.TranscriptGroup.wRecordType = "T"
         if ( CompareAttributeToString( lTrnscpt, "TranscriptGroup", "wRecordType", "T" ) == 0 )
         { 
            //:CREATE ENTITY lTrnscpt.PrintHeaderLine 
            RESULT = CreateEntity( lTrnscpt, "PrintHeaderLine", zPOS_AFTER );
            //:lTrnscpt.PrintHeaderLine.TextValue = "Transferred from " + lTrnscpt.TranscriptGroup.wForeignCollegeName 
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 255, lTrnscpt, "TranscriptGroup", "wForeignCollegeName", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringCopy( sb_szTempString_0, 1, 0, "Transferred from ", 1, 0, 255 );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 255 );
            szTempString_0 = sb_szTempString_0.toString( );}
            SetAttributeFromString( lTrnscpt, "PrintHeaderLine", "TextValue", szTempString_0 );
            //:CREATE ENTITY lTrnscpt.PrintHeaderLine 
            RESULT = CreateEntity( lTrnscpt, "PrintHeaderLine", zPOS_AFTER );
            //:lTrnscpt.PrintHeaderLine.TextValue = lTrnscpt.TranscriptGroup.YearSemester 
            SetAttributeFromAttribute( lTrnscpt, "PrintHeaderLine", "TextValue", lTrnscpt, "TranscriptGroup", "YearSemester" );
            //:ELSE
         } 
         else
         { 
            //:CREATE ENTITY lTrnscpt.PrintHeaderLine 
            RESULT = CreateEntity( lTrnscpt, "PrintHeaderLine", zPOS_AFTER );
            //:lTrnscpt.PrintHeaderLine.TextValue = lTrnscpt.TranscriptGroup.YearSemester
            SetAttributeFromAttribute( lTrnscpt, "PrintHeaderLine", "TextValue", lTrnscpt, "TranscriptGroup", "YearSemester" );
         } 

         //:END
      } 

      //:END
      //:FOR EACH lTrnscpt.TTG_HeaderLine 
      RESULT = lTrnscpt.cursor( "TTG_HeaderLine" ).setFirst().toInt();
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY lTrnscpt.PrintHeaderLine 
         RESULT = CreateEntity( lTrnscpt, "PrintHeaderLine", zPOS_AFTER );
         //:lTrnscpt.PrintHeaderLine.TextValue = lTrnscpt.TTG_HeaderLine.LineText 
         SetAttributeFromAttribute( lTrnscpt, "PrintHeaderLine", "TextValue", lTrnscpt, "TTG_HeaderLine", "LineText" );
         RESULT = lTrnscpt.cursor( "TTG_HeaderLine" ).setNextContinue().toInt();;
      } 

      //:END
      //:IF lTrnscpt.TranscriptGroup.wGPA_PointsTwoDecimalDigitsFlag = "Y"
      if ( CompareAttributeToString( lTrnscpt, "TranscriptGroup", "wGPA_PointsTwoDecimalDigitsFlag", "Y" ) == 0 )
      { 
         //:FOR EACH lTrnscpt.TermRegistration 
         RESULT = lTrnscpt.cursor( "TermRegistration" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lTrnscpt.TermRegistration.Status = "V" OR lTrnscpt.TermRegistration.Status = "Y"  // Waived or L. Waived
            if ( CompareAttributeToString( lTrnscpt, "TermRegistration", "Status", "V" ) == 0 || CompareAttributeToString( lTrnscpt, "TermRegistration", "Status", "Y" ) == 0 )
            { 
               //:CREATE ENTITY lTrnscpt.PrintWaivedEntry 
               RESULT = CreateEntity( lTrnscpt, "PrintWaivedEntry", zPOS_AFTER );
               //:lTrnscpt.PrintWaivedEntry.wCourseNumber = lTrnscpt.TermRegistration.wCourseNumber 
               SetAttributeFromAttribute( lTrnscpt, "PrintWaivedEntry", "wCourseNumber", lTrnscpt, "TermRegistration", "wCourseNumber" );
               //:lTrnscpt.PrintWaivedEntry.wCourseTitle  = lTrnscpt.TermRegistration.wCourseTitle 
               SetAttributeFromAttribute( lTrnscpt, "PrintWaivedEntry", "wCourseTitle", lTrnscpt, "TermRegistration", "wCourseTitle" );
               //:IF lTrnscpt.TermRegistration.CreditHours > 0
               if ( CompareAttributeToInteger( lTrnscpt, "TermRegistration", "CreditHours", 0 ) > 0 )
               { 
                  //:lTrnscpt.PrintWaivedEntry.wCreditHours = lTrnscpt.TermRegistration.CreditHours 
                  SetAttributeFromAttribute( lTrnscpt, "PrintWaivedEntry", "wCreditHours", lTrnscpt, "TermRegistration", "CreditHours" );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:INCLUDE lTrnscpt.PrintRegistration2 FROM lTrnscpt.TermRegistration 
               RESULT = IncludeSubobjectFromSubobject( lTrnscpt, "PrintRegistration2", lTrnscpt, "TermRegistration", zPOS_AFTER );
               //:SetMatchingAttributesByName( lTrnscpt, "PrintRegistration2", lTrnscpt, "TermRegistration", zSET_NULL )
               SetMatchingAttributesByName( lTrnscpt, "PrintRegistration2", lTrnscpt, "TermRegistration", zSET_NULL );
               //:IF lTrnscpt.PrintRegistration2.wDisplayAsterisk = ""
               if ( CompareAttributeToString( lTrnscpt, "PrintRegistration2", "wDisplayAsterisk", "" ) == 0 )
               { 
                  //:lTrnscpt.PrintRegistration2.wDisplayGrade = lTrnscpt.PrintRegistration2.FinalGrade
                  SetAttributeFromAttribute( lTrnscpt, "PrintRegistration2", "wDisplayGrade", lTrnscpt, "PrintRegistration2", "FinalGrade" );
                  //:ELSE
               } 
               else
               { 
                  //:lTrnscpt.PrintRegistration2.wDisplayGrade = lTrnscpt.PrintRegistration2.FinalGrade + " *"                                        
                  {StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                     GetStringFromAttribute( sb_szTempString_2, lTrnscpt, "PrintRegistration2", "FinalGrade" );
                  szTempString_2 = sb_szTempString_2.toString( );}
                   {StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                    ZeidonStringConcat( sb_szTempString_2, 1, 0, " *", 1, 0, 255 );
                  szTempString_2 = sb_szTempString_2.toString( );}
                  SetAttributeFromString( lTrnscpt, "PrintRegistration2", "wDisplayGrade", szTempString_2 );
               } 

               //:END
               //:IF lTrnscpt.PrintRegistration2.wDisplayRightParen = ""
               if ( CompareAttributeToString( lTrnscpt, "PrintRegistration2", "wDisplayRightParen", "" ) == 0 )
               { 
                  //:GetStringFromAttributeByContext( szAttemptedCredits,
                  //:                                 lTrnscpt, "PrintRegistration2", "AttemptedCredits", "Decimal1", 10 )
                  {StringBuilder sb_szAttemptedCredits;
                  if ( szAttemptedCredits == null )
                     sb_szAttemptedCredits = new StringBuilder( 32 );
                  else
                     sb_szAttemptedCredits = new StringBuilder( szAttemptedCredits );
                                     GetStringFromAttributeByContext( sb_szAttemptedCredits, lTrnscpt, "PrintRegistration2", "AttemptedCredits", "Decimal1", 10 );
                  szAttemptedCredits = sb_szAttemptedCredits.toString( );}
                  //:szAttemptedCredits = szAttemptedCredits + " "
                   {StringBuilder sb_szAttemptedCredits;
                  if ( szAttemptedCredits == null )
                     sb_szAttemptedCredits = new StringBuilder( 32 );
                  else
                     sb_szAttemptedCredits = new StringBuilder( szAttemptedCredits );
                                    ZeidonStringConcat( sb_szAttemptedCredits, 1, 0, " ", 1, 0, 11 );
                  szAttemptedCredits = sb_szAttemptedCredits.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:GetStringFromAttributeByContext( szAttemptedCredits,
                  //:                                 lTrnscpt, "PrintRegistration2", "AttemptedCredits", "Decimal1", 10 )
                  {StringBuilder sb_szAttemptedCredits;
                  if ( szAttemptedCredits == null )
                     sb_szAttemptedCredits = new StringBuilder( 32 );
                  else
                     sb_szAttemptedCredits = new StringBuilder( szAttemptedCredits );
                                     GetStringFromAttributeByContext( sb_szAttemptedCredits, lTrnscpt, "PrintRegistration2", "AttemptedCredits", "Decimal1", 10 );
                  szAttemptedCredits = sb_szAttemptedCredits.toString( );}
                  //:szAttemptedCreditsOrig = "(" + szAttemptedCredits + ")"
                   {StringBuilder sb_szAttemptedCreditsOrig;
                  if ( szAttemptedCreditsOrig == null )
                     sb_szAttemptedCreditsOrig = new StringBuilder( 32 );
                  else
                     sb_szAttemptedCreditsOrig = new StringBuilder( szAttemptedCreditsOrig );
                                    ZeidonStringCopy( sb_szAttemptedCreditsOrig, 1, 0, "(", 1, 0, 11 );
                  szAttemptedCreditsOrig = sb_szAttemptedCreditsOrig.toString( );}
                   {StringBuilder sb_szAttemptedCreditsOrig;
                  if ( szAttemptedCreditsOrig == null )
                     sb_szAttemptedCreditsOrig = new StringBuilder( 32 );
                  else
                     sb_szAttemptedCreditsOrig = new StringBuilder( szAttemptedCreditsOrig );
                                    ZeidonStringConcat( sb_szAttemptedCreditsOrig, 1, 0, szAttemptedCredits, 1, 0, 11 );
                  szAttemptedCreditsOrig = sb_szAttemptedCreditsOrig.toString( );}
                   {StringBuilder sb_szAttemptedCreditsOrig;
                  if ( szAttemptedCreditsOrig == null )
                     sb_szAttemptedCreditsOrig = new StringBuilder( 32 );
                  else
                     sb_szAttemptedCreditsOrig = new StringBuilder( szAttemptedCreditsOrig );
                                    ZeidonStringConcat( sb_szAttemptedCreditsOrig, 1, 0, ")", 1, 0, 11 );
                  szAttemptedCreditsOrig = sb_szAttemptedCreditsOrig.toString( );}
                  //:szAttemptedCredits = szAttemptedCreditsOrig
                   {StringBuilder sb_szAttemptedCredits;
                  if ( szAttemptedCredits == null )
                     sb_szAttemptedCredits = new StringBuilder( 32 );
                  else
                     sb_szAttemptedCredits = new StringBuilder( szAttemptedCredits );
                                    ZeidonStringCopy( sb_szAttemptedCredits, 1, 0, szAttemptedCreditsOrig, 1, 0, 11 );
                  szAttemptedCredits = sb_szAttemptedCredits.toString( );}
               } 

               //:END
               //:lTrnscpt.PrintRegistration2.wDisplayAttemptedCredits = szAttemptedCredits
               SetAttributeFromString( lTrnscpt, "PrintRegistration2", "wDisplayAttemptedCredits", szAttemptedCredits );
            } 

            RESULT = lTrnscpt.cursor( "TermRegistration" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:FOR EACH lTrnscpt.TermRegistration 
         RESULT = lTrnscpt.cursor( "TermRegistration" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lTrnscpt.TermRegistration.Status = "V" OR lTrnscpt.TermRegistration.Status = "Y"  // Waived or L. Waived
            if ( CompareAttributeToString( lTrnscpt, "TermRegistration", "Status", "V" ) == 0 || CompareAttributeToString( lTrnscpt, "TermRegistration", "Status", "Y" ) == 0 )
            { 
               //:CREATE ENTITY lTrnscpt.PrintWaivedEntry 
               RESULT = CreateEntity( lTrnscpt, "PrintWaivedEntry", zPOS_AFTER );
               //:lTrnscpt.PrintWaivedEntry.wCourseNumber = lTrnscpt.TermRegistration.wCourseNumber 
               SetAttributeFromAttribute( lTrnscpt, "PrintWaivedEntry", "wCourseNumber", lTrnscpt, "TermRegistration", "wCourseNumber" );
               //:lTrnscpt.PrintWaivedEntry.wCourseTitle  = lTrnscpt.TermRegistration.wCourseTitle 
               SetAttributeFromAttribute( lTrnscpt, "PrintWaivedEntry", "wCourseTitle", lTrnscpt, "TermRegistration", "wCourseTitle" );
               //:IF lTrnscpt.TermRegistration.CreditHours > 0
               if ( CompareAttributeToInteger( lTrnscpt, "TermRegistration", "CreditHours", 0 ) > 0 )
               { 
                  //:lTrnscpt.PrintWaivedEntry.wCreditHours = lTrnscpt.TermRegistration.CreditHours 
                  SetAttributeFromAttribute( lTrnscpt, "PrintWaivedEntry", "wCreditHours", lTrnscpt, "TermRegistration", "CreditHours" );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:INCLUDE lTrnscpt.PrintRegistration FROM lTrnscpt.TermRegistration 
               RESULT = IncludeSubobjectFromSubobject( lTrnscpt, "PrintRegistration", lTrnscpt, "TermRegistration", zPOS_AFTER );
               //:SetMatchingAttributesByName( lTrnscpt, "PrintRegistration", lTrnscpt, "TermRegistration", zSET_NULL )
               SetMatchingAttributesByName( lTrnscpt, "PrintRegistration", lTrnscpt, "TermRegistration", zSET_NULL );
               //:IF lTrnscpt.PrintRegistration.wDisplayAsterisk = ""
               if ( CompareAttributeToString( lTrnscpt, "PrintRegistration", "wDisplayAsterisk", "" ) == 0 )
               { 
                  //:lTrnscpt.PrintRegistration.wDisplayGrade = lTrnscpt.PrintRegistration.FinalGrade
                  SetAttributeFromAttribute( lTrnscpt, "PrintRegistration", "wDisplayGrade", lTrnscpt, "PrintRegistration", "FinalGrade" );
                  //:ELSE
               } 
               else
               { 
                  //:lTrnscpt.PrintRegistration.wDisplayGrade = lTrnscpt.PrintRegistration.FinalGrade + " *"                                        
                  {StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                     GetStringFromAttribute( sb_szTempString_3, lTrnscpt, "PrintRegistration", "FinalGrade" );
                  szTempString_3 = sb_szTempString_3.toString( );}
                   {StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                    ZeidonStringConcat( sb_szTempString_3, 1, 0, " *", 1, 0, 255 );
                  szTempString_3 = sb_szTempString_3.toString( );}
                  SetAttributeFromString( lTrnscpt, "PrintRegistration", "wDisplayGrade", szTempString_3 );
               } 

               //:END
               //:IF lTrnscpt.PrintRegistration.wDisplayRightParen = ""
               if ( CompareAttributeToString( lTrnscpt, "PrintRegistration", "wDisplayRightParen", "" ) == 0 )
               { 
                  //:GetStringFromAttributeByContext( szAttemptedCredits,
                  //:                                 lTrnscpt, "PrintRegistration", "AttemptedCredits", "Decimal1", 10 )
                  {StringBuilder sb_szAttemptedCredits;
                  if ( szAttemptedCredits == null )
                     sb_szAttemptedCredits = new StringBuilder( 32 );
                  else
                     sb_szAttemptedCredits = new StringBuilder( szAttemptedCredits );
                                     GetStringFromAttributeByContext( sb_szAttemptedCredits, lTrnscpt, "PrintRegistration", "AttemptedCredits", "Decimal1", 10 );
                  szAttemptedCredits = sb_szAttemptedCredits.toString( );}
                  //:szAttemptedCredits = szAttemptedCredits + " "
                   {StringBuilder sb_szAttemptedCredits;
                  if ( szAttemptedCredits == null )
                     sb_szAttemptedCredits = new StringBuilder( 32 );
                  else
                     sb_szAttemptedCredits = new StringBuilder( szAttemptedCredits );
                                    ZeidonStringConcat( sb_szAttemptedCredits, 1, 0, " ", 1, 0, 11 );
                  szAttemptedCredits = sb_szAttemptedCredits.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:GetStringFromAttributeByContext( szAttemptedCredits,
                  //:                                 lTrnscpt, "PrintRegistration", "AttemptedCredits", "Decimal1", 10 )
                  {StringBuilder sb_szAttemptedCredits;
                  if ( szAttemptedCredits == null )
                     sb_szAttemptedCredits = new StringBuilder( 32 );
                  else
                     sb_szAttemptedCredits = new StringBuilder( szAttemptedCredits );
                                     GetStringFromAttributeByContext( sb_szAttemptedCredits, lTrnscpt, "PrintRegistration", "AttemptedCredits", "Decimal1", 10 );
                  szAttemptedCredits = sb_szAttemptedCredits.toString( );}
                  //:szAttemptedCreditsOrig = "(" + szAttemptedCredits + ")"
                   {StringBuilder sb_szAttemptedCreditsOrig;
                  if ( szAttemptedCreditsOrig == null )
                     sb_szAttemptedCreditsOrig = new StringBuilder( 32 );
                  else
                     sb_szAttemptedCreditsOrig = new StringBuilder( szAttemptedCreditsOrig );
                                    ZeidonStringCopy( sb_szAttemptedCreditsOrig, 1, 0, "(", 1, 0, 11 );
                  szAttemptedCreditsOrig = sb_szAttemptedCreditsOrig.toString( );}
                   {StringBuilder sb_szAttemptedCreditsOrig;
                  if ( szAttemptedCreditsOrig == null )
                     sb_szAttemptedCreditsOrig = new StringBuilder( 32 );
                  else
                     sb_szAttemptedCreditsOrig = new StringBuilder( szAttemptedCreditsOrig );
                                    ZeidonStringConcat( sb_szAttemptedCreditsOrig, 1, 0, szAttemptedCredits, 1, 0, 11 );
                  szAttemptedCreditsOrig = sb_szAttemptedCreditsOrig.toString( );}
                   {StringBuilder sb_szAttemptedCreditsOrig;
                  if ( szAttemptedCreditsOrig == null )
                     sb_szAttemptedCreditsOrig = new StringBuilder( 32 );
                  else
                     sb_szAttemptedCreditsOrig = new StringBuilder( szAttemptedCreditsOrig );
                                    ZeidonStringConcat( sb_szAttemptedCreditsOrig, 1, 0, ")", 1, 0, 11 );
                  szAttemptedCreditsOrig = sb_szAttemptedCreditsOrig.toString( );}
                  //:szAttemptedCredits = szAttemptedCreditsOrig
                   {StringBuilder sb_szAttemptedCredits;
                  if ( szAttemptedCredits == null )
                     sb_szAttemptedCredits = new StringBuilder( 32 );
                  else
                     sb_szAttemptedCredits = new StringBuilder( szAttemptedCredits );
                                    ZeidonStringCopy( sb_szAttemptedCredits, 1, 0, szAttemptedCreditsOrig, 1, 0, 11 );
                  szAttemptedCredits = sb_szAttemptedCredits.toString( );}
               } 

               //:END
               //:lTrnscpt.PrintRegistration.wDisplayAttemptedCredits = szAttemptedCredits
               SetAttributeFromString( lTrnscpt, "PrintRegistration", "wDisplayAttemptedCredits", szAttemptedCredits );
            } 

            RESULT = lTrnscpt.cursor( "TermRegistration" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
      } 

      RESULT = lTrnscpt.cursor( "TranscriptGroup" ).setNextContinue().toInt();;
      //:END
   } 

   //:END
   return( 0 );
// END
} 



}
