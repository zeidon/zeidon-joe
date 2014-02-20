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

public class lClsLstC_Object extends VmlObjectOperations
{
   public lClsLstC_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dMeetingSchedule( VIEW lClsLstC BASED ON LOD lClsLstC,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING ( 254 ) szMeetingSchedule
public int 
olClsLstC_dMeetingSchedule( View     lClsLstC,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szMeetingSchedule = null;
   //:STRING ( 10 )  szMeetingDay
   String   szMeetingDay = null;
   //:STRING ( 10 )  szMeetingStartTime
   String   szMeetingStartTime = null;
   //:STRING ( 10 )  szMeetingEndTime
   String   szMeetingEndTime = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Build the Class Schedule attribute.
         //:// If a standard schedule exists. simply use it.
         //:// If a custom schedule exists, we will have to build the attribute.
         //:IF lClsLstC.ClassRoomStandardSchedule EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( lClsLstC, "ClassRoomStandardSchedule" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szMeetingSchedule = lClsLstC.ClassRoomStandardSchedule.Title 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szMeetingSchedule;
            if ( szMeetingSchedule == null )
               sb_szMeetingSchedule = new StringBuilder( 32 );
            else
               sb_szMeetingSchedule = new StringBuilder( szMeetingSchedule );
                         GetVariableFromAttribute( sb_szMeetingSchedule, mi_lTempInteger_1, 'S', 255, lClsLstC, "ClassRoomStandardSchedule", "Title", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szMeetingSchedule = sb_szMeetingSchedule.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szMeetingSchedule = ""
             {StringBuilder sb_szMeetingSchedule;
            if ( szMeetingSchedule == null )
               sb_szMeetingSchedule = new StringBuilder( 32 );
            else
               sb_szMeetingSchedule = new StringBuilder( szMeetingSchedule );
                        ZeidonStringCopy( sb_szMeetingSchedule, 1, 0, "", 1, 0, 255 );
            szMeetingSchedule = sb_szMeetingSchedule.toString( );}
            //:FOR EACH lClsLstC.ClassRoomSession 
            RESULT = lClsLstC.cursor( "ClassRoomSession" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:GetStringFromAttributeByContext( szMeetingDay,
               //:                              lClsLstC, "ClassRoomSession", "MeetingDate", "", 10 )
               {StringBuilder sb_szMeetingDay;
               if ( szMeetingDay == null )
                  sb_szMeetingDay = new StringBuilder( 32 );
               else
                  sb_szMeetingDay = new StringBuilder( szMeetingDay );
                               GetStringFromAttributeByContext( sb_szMeetingDay, lClsLstC, "ClassRoomSession", "MeetingDate", "", 10 );
               szMeetingDay = sb_szMeetingDay.toString( );}
               //://There is either a "MeetingDate" or a "MeetingDay".  If one doesn't exist, then choose the other.
               //:IF  szMeetingDay = ""
               if ( ZeidonStringCompare( szMeetingDay, 1, 0, "", 1, 0, 11 ) == 0 )
               { 
                  //:GetStringFromAttributeByContext( szMeetingDay,
                  //:                              lClsLstC, "ClassRoomSession", "MeetingDay", "", 10 )
                  {StringBuilder sb_szMeetingDay;
                  if ( szMeetingDay == null )
                     sb_szMeetingDay = new StringBuilder( 32 );
                  else
                     sb_szMeetingDay = new StringBuilder( szMeetingDay );
                                     GetStringFromAttributeByContext( sb_szMeetingDay, lClsLstC, "ClassRoomSession", "MeetingDay", "", 10 );
                  szMeetingDay = sb_szMeetingDay.toString( );}
               } 

               //: 
               //:END                                             
               //:GetStringFromAttributeByContext( szMeetingStartTime,
               //:                              lClsLstC, "ClassRoomSession", "StartTime", "", 10 )
               {StringBuilder sb_szMeetingStartTime;
               if ( szMeetingStartTime == null )
                  sb_szMeetingStartTime = new StringBuilder( 32 );
               else
                  sb_szMeetingStartTime = new StringBuilder( szMeetingStartTime );
                               GetStringFromAttributeByContext( sb_szMeetingStartTime, lClsLstC, "ClassRoomSession", "StartTime", "", 10 );
               szMeetingStartTime = sb_szMeetingStartTime.toString( );}
               //:GetStringFromAttributeByContext( szMeetingEndTime,
               //:                              lClsLstC, "ClassRoomSession", "EndTime", "", 10 )
               {StringBuilder sb_szMeetingEndTime;
               if ( szMeetingEndTime == null )
                  sb_szMeetingEndTime = new StringBuilder( 32 );
               else
                  sb_szMeetingEndTime = new StringBuilder( szMeetingEndTime );
                               GetStringFromAttributeByContext( sb_szMeetingEndTime, lClsLstC, "ClassRoomSession", "EndTime", "", 10 );
               szMeetingEndTime = sb_szMeetingEndTime.toString( );}
               //:szMeetingSchedule = szMeetingSchedule + szMeetingDay + ":" +
               //:                 szMeetingStartTime  + "-" + szMeetingEndTime + " "
                {StringBuilder sb_szMeetingSchedule;
               if ( szMeetingSchedule == null )
                  sb_szMeetingSchedule = new StringBuilder( 32 );
               else
                  sb_szMeetingSchedule = new StringBuilder( szMeetingSchedule );
                              ZeidonStringConcat( sb_szMeetingSchedule, 1, 0, szMeetingDay, 1, 0, 255 );
               szMeetingSchedule = sb_szMeetingSchedule.toString( );}
                {StringBuilder sb_szMeetingSchedule;
               if ( szMeetingSchedule == null )
                  sb_szMeetingSchedule = new StringBuilder( 32 );
               else
                  sb_szMeetingSchedule = new StringBuilder( szMeetingSchedule );
                              ZeidonStringConcat( sb_szMeetingSchedule, 1, 0, ":", 1, 0, 255 );
               szMeetingSchedule = sb_szMeetingSchedule.toString( );}
                {StringBuilder sb_szMeetingSchedule;
               if ( szMeetingSchedule == null )
                  sb_szMeetingSchedule = new StringBuilder( 32 );
               else
                  sb_szMeetingSchedule = new StringBuilder( szMeetingSchedule );
                              ZeidonStringConcat( sb_szMeetingSchedule, 1, 0, szMeetingStartTime, 1, 0, 255 );
               szMeetingSchedule = sb_szMeetingSchedule.toString( );}
                {StringBuilder sb_szMeetingSchedule;
               if ( szMeetingSchedule == null )
                  sb_szMeetingSchedule = new StringBuilder( 32 );
               else
                  sb_szMeetingSchedule = new StringBuilder( szMeetingSchedule );
                              ZeidonStringConcat( sb_szMeetingSchedule, 1, 0, "-", 1, 0, 255 );
               szMeetingSchedule = sb_szMeetingSchedule.toString( );}
                {StringBuilder sb_szMeetingSchedule;
               if ( szMeetingSchedule == null )
                  sb_szMeetingSchedule = new StringBuilder( 32 );
               else
                  sb_szMeetingSchedule = new StringBuilder( szMeetingSchedule );
                              ZeidonStringConcat( sb_szMeetingSchedule, 1, 0, szMeetingEndTime, 1, 0, 255 );
               szMeetingSchedule = sb_szMeetingSchedule.toString( );}
                {StringBuilder sb_szMeetingSchedule;
               if ( szMeetingSchedule == null )
                  sb_szMeetingSchedule = new StringBuilder( 32 );
               else
                  sb_szMeetingSchedule = new StringBuilder( szMeetingSchedule );
                              ZeidonStringConcat( sb_szMeetingSchedule, 1, 0, " ", 1, 0, 255 );
               szMeetingSchedule = sb_szMeetingSchedule.toString( );}
               RESULT = lClsLstC.cursor( "ClassRoomSession" ).setNextContinue().toInt();;
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( lClsLstC,
         //:                   InternalEntityStructure, InternalAttribStructure, szMeetingSchedule )
         StoreStringInRecord( lClsLstC, InternalEntityStructure, InternalAttribStructure, szMeetingSchedule );
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
//:dClassTitle( VIEW lClsLstC BASED ON LOD lClsLstC,
//:             STRING ( 32 ) InternalEntityStructure,
//:             STRING ( 32 ) InternalAttribStructure,
//:             SHORT GetOrSetFlag )

//:   STRING ( 100 ) szClassTitle
public int 
olClsLstC_dClassTitle( View     lClsLstC,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{
   String   szClassTitle = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:IF lClsLstC.CorrespondingCrossListedCourse EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( lClsLstC, "CorrespondingCrossListedCourse" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szClassTitle = lClsLstC.CorrespondingCrossListedCourse.Title
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szClassTitle;
            if ( szClassTitle == null )
               sb_szClassTitle = new StringBuilder( 32 );
            else
               sb_szClassTitle = new StringBuilder( szClassTitle );
                         GetVariableFromAttribute( sb_szClassTitle, mi_lTempInteger_1, 'S', 101, lClsLstC, "CorrespondingCrossListedCourse", "Title", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szClassTitle = sb_szClassTitle.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF lClsLstC.ClassCourseTopic EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( lClsLstC, "ClassCourseTopic" );
            if ( lTempInteger_2 == 0 )
            { 
               //:szClassTitle = lClsLstC.ClassCourseTopic.Title 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szClassTitle;
               if ( szClassTitle == null )
                  sb_szClassTitle = new StringBuilder( 32 );
               else
                  sb_szClassTitle = new StringBuilder( szClassTitle );
                               GetVariableFromAttribute( sb_szClassTitle, mi_lTempInteger_3, 'S', 101, lClsLstC, "ClassCourseTopic", "Title", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szClassTitle = sb_szClassTitle.toString( );}
               //:ELSE
            } 
            else
            { 
                GetStringFromAttribute( szClassTitle, lClsLstC, "Class", "ID" );
                lTempInteger_2 = CheckExistenceOfEntity( lClsLstC, "Course" );
              //:szClassTitle = lClsLstC.Course.Title 
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szClassTitle;
               if ( szClassTitle == null )
                  sb_szClassTitle = new StringBuilder( 32 );
               else
                  sb_szClassTitle = new StringBuilder( szClassTitle );
                               GetVariableFromAttribute( sb_szClassTitle, mi_lTempInteger_4, 'S', 101, lClsLstC, "Course", "Title", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szClassTitle = sb_szClassTitle.toString( );}
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( lClsLstC,
         //:                   InternalEntityStructure, InternalAttribStructure, szClassTitle )
         StoreStringInRecord( lClsLstC, InternalEntityStructure, InternalAttribStructure, szClassTitle );
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
public int 
olClsLstC_ObjectConstraints( View     lClsLstC,
                             Integer   Event,
                             Integer   State )
{
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_3 = null;
   String   szTempString_4 = null;
   int      lTempInteger_3 = 0;

   //:ObjectConstraints( VIEW lClsLstC BASED ON LOD lClsLstC,
   //:                SHORT Event,
   //:                SHORT State )

   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:// Set the work field that combines Class Number and Section.
         //:FOR EACH lClsLstC.Class
         RESULT = lClsLstC.cursor( "Class" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lClsLstC.ClassCourseTopic EXISTS
            lTempInteger_0 = CheckExistenceOfEntity( lClsLstC, "ClassCourseTopic" );
            if ( lTempInteger_0 == 0 )
            { 
               //:lClsLstC.Class.wClassNumberTopicSection = lClsLstC.Course.Number + "-" +
               //:                                    lClsLstC.ClassCourseTopic.Number +
               //:                                    lClsLstC.Class.Section 
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, lClsLstC, "Course", "Number" );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                              ZeidonStringConcat( sb_szTempString_0, 1, 0, "-", 1, 0, 255 );
               szTempString_0 = sb_szTempString_0.toString( );}
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 11, lClsLstC, "ClassCourseTopic", "Number", "", 0 );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );
               szTempString_1 = sb_szTempString_1.toString( );}
                {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                              ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 255 );
               szTempString_0 = sb_szTempString_0.toString( );}
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szTempString_2;
               if ( szTempString_2 == null )
                  sb_szTempString_2 = new StringBuilder( 32 );
               else
                  sb_szTempString_2 = new StringBuilder( szTempString_2 );
                               GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_2, 'S', 3, lClsLstC, "Class", "Section", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szTempString_2 = sb_szTempString_2.toString( );}
                {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                              ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_2, 1, 0, 255 );
               szTempString_0 = sb_szTempString_0.toString( );}
               SetAttributeFromString( lClsLstC, "Class", "wClassNumberTopicSection", szTempString_0 );
               //:ELSE
            } 
            else
            { 
               //:IF lClsLstC.Class.Section = ""
               if ( CompareAttributeToString( lClsLstC, "Class", "Section", "" ) == 0 )
               { 
                  //:lClsLstC.Class.wClassNumberTopicSection = lClsLstC.Course.Number
                  SetAttributeFromAttribute( lClsLstC, "Class", "wClassNumberTopicSection", lClsLstC, "Course", "Number" );
                  //:ELSE
               } 
               else
               { 
                  //:lClsLstC.Class.wClassNumberTopicSection = lClsLstC.Course.Number + "-" +
                  //:                                 lClsLstC.Class.Section 
                  {StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                     GetStringFromAttribute( sb_szTempString_3, lClsLstC, "Course", "Number" );
                  szTempString_3 = sb_szTempString_3.toString( );}
                   {StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                    ZeidonStringConcat( sb_szTempString_3, 1, 0, "-", 1, 0, 255 );
                  szTempString_3 = sb_szTempString_3.toString( );}
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szTempString_4;
                  if ( szTempString_4 == null )
                     sb_szTempString_4 = new StringBuilder( 32 );
                  else
                     sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                     GetVariableFromAttribute( sb_szTempString_4, mi_lTempInteger_3, 'S', 3, lClsLstC, "Class", "Section", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szTempString_4 = sb_szTempString_4.toString( );}
                   {StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                    ZeidonStringConcat( sb_szTempString_3, 1, 0, szTempString_4, 1, 0, 255 );
                  szTempString_3 = sb_szTempString_3.toString( );}
                  SetAttributeFromString( lClsLstC, "Class", "wClassNumberTopicSection", szTempString_3 );
               } 

               //:END
            } 

            //:END
            //:IF  lClsLstC.Class.NoFinalExamFlag = "Y"
            if ( CompareAttributeToString( lClsLstC, "Class", "NoFinalExamFlag", "Y" ) == 0 )
            { 
               //:lClsLstC.Class.wNoFinalExam = "No Exam"
               SetAttributeFromString( lClsLstC, "Class", "wNoFinalExam", "No Exam" );
               //:ELSE
            } 
            else
            { 
               //:lClsLstC.Class.wNoFinalExam = "" 
               SetAttributeFromString( lClsLstC, "Class", "wNoFinalExam", "" );
            } 

            RESULT = lClsLstC.cursor( "Class" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:SET CURSOR FIRST lClsLstC.Class 
         RESULT = lClsLstC.cursor( "Class" ).setFirst().toInt();
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


//:DERIVED ATTRIBUTE OPERATION
//:dTotalEnrolled( VIEW lClsLstC BASED ON LOD lClsLstC,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )
//:   
//:   INTEGER TotalEnrolled
public int 
olClsLstC_dTotalEnrolled( View     lClsLstC,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   int      TotalEnrolled = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:TotalEnrolled = 0
         TotalEnrolled = 0;
         //:FOR EACH lClsLstC.EnrollmentOrForeignEquivalent
         RESULT = lClsLstC.cursor( "EnrollmentOrForeignEquivalent" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF lClsLstC.EnrollmentOrForeignEquivalent.Status = "C" OR
            //:lClsLstC.EnrollmentOrForeignEquivalent.Status = "T"
            if ( CompareAttributeToString( lClsLstC, "EnrollmentOrForeignEquivalent", "Status", "C" ) == 0 || CompareAttributeToString( lClsLstC, "EnrollmentOrForeignEquivalent", "Status", "T" ) == 0 )
            { 

               //:TotalEnrolled = TotalEnrolled + 1
               TotalEnrolled = TotalEnrolled + 1;
            } 

            RESULT = lClsLstC.cursor( "EnrollmentOrForeignEquivalent" ).setNextContinue().toInt();;
            //:END 
         } 

         //:END
         //:StoreValueInRecord ( lClsLstC,
         //:                  InternalEntityStructure, InternalAttribStructure, TotalEnrolled, 0 )
         StoreValueInRecord( lClsLstC, InternalEntityStructure, InternalAttribStructure, TotalEnrolled, 0 );
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
//:dTotalWaitlisted( VIEW lClsLstC BASED ON LOD lClsLstC,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )
//:   
//:   INTEGER TotalWaitlisted
public int 
olClsLstC_dTotalWaitlisted( View     lClsLstC,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   int      TotalWaitlisted = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:TotalWaitlisted = 0
         TotalWaitlisted = 0;
         //:FOR EACH lClsLstC.StudentWaitlisted 
         RESULT = lClsLstC.cursor( "StudentWaitlisted" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:TotalWaitlisted = TotalWaitlisted + 1
            TotalWaitlisted = TotalWaitlisted + 1;
            RESULT = lClsLstC.cursor( "StudentWaitlisted" ).setNextContinue().toInt();;
         } 

         //:END
         //:StoreValueInRecord ( lClsLstC,
         //:                  InternalEntityStructure, InternalAttribStructure, TotalWaitlisted, 0 )
         StoreValueInRecord( lClsLstC, InternalEntityStructure, InternalAttribStructure, TotalWaitlisted, 0 );
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
//:dClassStatus( VIEW lClsLstC BASED ON LOD lClsLstC,
//:             STRING ( 32 ) InternalEntityStructure,
//:             STRING ( 32 ) InternalAttribStructure,
//:             SHORT GetOrSetFlag )

//:   STRING ( 100 ) szClassStatus
public int 
olClsLstC_dClassStatus( View     lClsLstC,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   String   szClassStatus = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:IF lClsLstC.Class.Status = "L" OR
         //:lClsLstC.Class.dTotalEnrolled >=lClsLstC.Class.EnrollmentLimit
         if ( CompareAttributeToString( lClsLstC, "Class", "Status", "L" ) == 0 || CompareAttributeToAttribute( lClsLstC, "Class", "dTotalEnrolled", lClsLstC, "Class", "EnrollmentLimit" ) >= 0 )
         { 

            //:szClassStatus = "Closed"
             {StringBuilder sb_szClassStatus;
            if ( szClassStatus == null )
               sb_szClassStatus = new StringBuilder( 32 );
            else
               sb_szClassStatus = new StringBuilder( szClassStatus );
                        ZeidonStringCopy( sb_szClassStatus, 1, 0, "Closed", 1, 0, 101 );
            szClassStatus = sb_szClassStatus.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szClassStatus = "Open"
             {StringBuilder sb_szClassStatus;
            if ( szClassStatus == null )
               sb_szClassStatus = new StringBuilder( 32 );
            else
               sb_szClassStatus = new StringBuilder( szClassStatus );
                        ZeidonStringCopy( sb_szClassStatus, 1, 0, "Open", 1, 0, 101 );
            szClassStatus = sb_szClassStatus.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( lClsLstC,
         //:                   InternalEntityStructure, InternalAttribStructure, szClassStatus )
         StoreStringInRecord( lClsLstC, InternalEntityStructure, InternalAttribStructure, szClassStatus );
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
//:dClassRoomNumber( VIEW lClsLstC BASED ON LOD lClsLstC,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING ( 100 ) szClassRoom
public int 
olClsLstC_dClassRoomNumber( View     lClsLstC,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szClassRoom = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:IF lClsLstC.Building EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( lClsLstC, "Building" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szClassRoom = lClsLstC.Building.Name + " " + lClsLstC.Room.Number 
            {StringBuilder sb_szClassRoom;
            if ( szClassRoom == null )
               sb_szClassRoom = new StringBuilder( 32 );
            else
               sb_szClassRoom = new StringBuilder( szClassRoom );
                         GetStringFromAttribute( sb_szClassRoom, lClsLstC, "Building", "Name" );
            szClassRoom = sb_szClassRoom.toString( );}
             {StringBuilder sb_szClassRoom;
            if ( szClassRoom == null )
               sb_szClassRoom = new StringBuilder( 32 );
            else
               sb_szClassRoom = new StringBuilder( szClassRoom );
                        ZeidonStringConcat( sb_szClassRoom, 1, 0, " ", 1, 0, 101 );
            szClassRoom = sb_szClassRoom.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 255, lClsLstC, "Room", "Number", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szClassRoom;
            if ( szClassRoom == null )
               sb_szClassRoom = new StringBuilder( 32 );
            else
               sb_szClassRoom = new StringBuilder( szClassRoom );
                        ZeidonStringConcat( sb_szClassRoom, 1, 0, szTempString_0, 1, 0, 101 );
            szClassRoom = sb_szClassRoom.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szClassRoom = lClsLstC.Room.Number
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szClassRoom;
            if ( szClassRoom == null )
               sb_szClassRoom = new StringBuilder( 32 );
            else
               sb_szClassRoom = new StringBuilder( szClassRoom );
                         GetVariableFromAttribute( sb_szClassRoom, mi_lTempInteger_2, 'S', 101, lClsLstC, "Room", "Number", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szClassRoom = sb_szClassRoom.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( lClsLstC,
         //:                   InternalEntityStructure, InternalAttribStructure, szClassRoom )
         StoreStringInRecord( lClsLstC, InternalEntityStructure, InternalAttribStructure, szClassRoom );
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
//:dShortClassRoomNumber( VIEW lClsLstC BASED ON LOD lClsLstC,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   STRING ( 100 ) szClassRoom
public int 
olClsLstC_dShortClassRoomNumber( View     lClsLstC,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   String   szClassRoom = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:IF lClsLstC.Building EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( lClsLstC, "Building" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szClassRoom = lClsLstC.Building.ShortName + " " + lClsLstC.Room.Number 
            {StringBuilder sb_szClassRoom;
            if ( szClassRoom == null )
               sb_szClassRoom = new StringBuilder( 32 );
            else
               sb_szClassRoom = new StringBuilder( szClassRoom );
                         GetStringFromAttribute( sb_szClassRoom, lClsLstC, "Building", "ShortName" );
            szClassRoom = sb_szClassRoom.toString( );}
             {StringBuilder sb_szClassRoom;
            if ( szClassRoom == null )
               sb_szClassRoom = new StringBuilder( 32 );
            else
               sb_szClassRoom = new StringBuilder( szClassRoom );
                        ZeidonStringConcat( sb_szClassRoom, 1, 0, " ", 1, 0, 101 );
            szClassRoom = sb_szClassRoom.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 255, lClsLstC, "Room", "Number", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szClassRoom;
            if ( szClassRoom == null )
               sb_szClassRoom = new StringBuilder( 32 );
            else
               sb_szClassRoom = new StringBuilder( szClassRoom );
                        ZeidonStringConcat( sb_szClassRoom, 1, 0, szTempString_0, 1, 0, 101 );
            szClassRoom = sb_szClassRoom.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szClassRoom = lClsLstC.Room.Number
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szClassRoom;
            if ( szClassRoom == null )
               sb_szClassRoom = new StringBuilder( 32 );
            else
               sb_szClassRoom = new StringBuilder( szClassRoom );
                         GetVariableFromAttribute( sb_szClassRoom, mi_lTempInteger_2, 'S', 101, lClsLstC, "Room", "Number", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szClassRoom = sb_szClassRoom.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( lClsLstC,
         //:                   InternalEntityStructure, InternalAttribStructure, szClassRoom )
         StoreStringInRecord( lClsLstC, InternalEntityStructure, InternalAttribStructure, szClassRoom );
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
//:CreateCrossListEntries( VIEW lClsLstC BASED ON LOD lClsLstC )

//:   VIEW lClsLstCT BASED ON LOD lClsLstC
public int 
olClsLstC_CreateCrossListEntries( View     lClsLstC )
{
   zVIEW    lClsLstCT = new zVIEW( );
   int      RESULT = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;


   //:// Create the additional Cross Listed Class entries.
   //:// Also make sure that wCourseID points to either the primary Course or the Crosslisted Course.
   //:CreateViewFromView( lClsLstCT, lClsLstC )
   CreateViewFromView( lClsLstCT, lClsLstC );
   //:NAME VIEW lClsLstCT "lClsLstCT"
   SetNameForView( lClsLstCT, "lClsLstCT", null, zLEVEL_TASK );
   //:FOR EACH lClsLstC.Class
   RESULT = lClsLstC.cursor( "Class" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF lClsLstC.Class.wCourseID = ""
      if ( CompareAttributeToString( lClsLstC, "Class", "wCourseID", "" ) == 0 )
      { 
         //:lClsLstC.Class.wCourseID     = lClsLstC.Course.ID
         SetAttributeFromAttribute( lClsLstC, "Class", "wCourseID", lClsLstC, "Course", "ID" );
         //:lClsLstC.Class.wCourseNumber = lClsLstC.Course.Number
         SetAttributeFromAttribute( lClsLstC, "Class", "wCourseNumber", lClsLstC, "Course", "Number" );
      } 

      //:END
      //:IF lClsLstC.Class.wCrossListedFlag != "Y"
      if ( CompareAttributeToString( lClsLstC, "Class", "wCrossListedFlag", "Y" ) != 0 )
      { 
         //:FOR EACH lClsLstC.CrossListedCourse
         RESULT = lClsLstC.cursor( "CrossListedCourse" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST lClsLstCT.Class WHERE lClsLstCT.Course.ID = lClsLstC.CrossListedCourse.ID 
            RESULT = lClsLstCT.cursor( "Class" ).setFirst().toInt();
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( lClsLstCT, "Course", "ID", lClsLstC, "CrossListedCourse", "ID" ) != 0 ) )
               { 
                  RESULT = lClsLstCT.cursor( "Class" ).setNextContinue().toInt();;
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:INCLUDE lClsLstCT.Class FROM lClsLstC.Class
               RESULT = IncludeSubobjectFromSubobject( lClsLstCT, "Class", lClsLstC, "Class", zPOS_AFTER );
               //:lClsLstCT.Class.wClassNumberTopicSection = lClsLstC.CrossListedCourse.Number + lClsLstCT.Class.Section 
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, lClsLstC, "CrossListedCourse", "Number" );
               szTempString_0 = sb_szTempString_0.toString( );}
               {StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetStringFromAttribute( sb_szTempString_1, lClsLstCT, "Class", "Section" );
               szTempString_1 = sb_szTempString_1.toString( );}
                {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                              ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 255 );
               szTempString_0 = sb_szTempString_0.toString( );}
               SetAttributeFromString( lClsLstCT, "Class", "wClassNumberTopicSection", szTempString_0 );
               //:lClsLstCT.Class.wCrossListedFlag = "Y"
               SetAttributeFromString( lClsLstCT, "Class", "wCrossListedFlag", "Y" );
               //:INCLUDE lClsLstCT.CorrespondingCrossListedCourse FROM lClsLstC.CrossListedCourse
               RESULT = IncludeSubobjectFromSubobject( lClsLstCT, "CorrespondingCrossListedCourse", lClsLstC, "CrossListedCourse", zPOS_AFTER );
               //:lClsLstCT.Class.wCourseID     = lClsLstC.CrossListedCourse.ID
               SetAttributeFromAttribute( lClsLstCT, "Class", "wCourseID", lClsLstC, "CrossListedCourse", "ID" );
               //:lClsLstCT.Class.wCourseNumber = lClsLstC.CrossListedCourse.Number
               SetAttributeFromAttribute( lClsLstCT, "Class", "wCourseNumber", lClsLstC, "CrossListedCourse", "Number" );
            } 

            RESULT = lClsLstC.cursor( "CrossListedCourse" ).setNextContinue().toInt();;
            //:END  
         } 

         //:END
      } 

      RESULT = lClsLstC.cursor( "Class" ).setNextContinue().toInt();;
      //:END
   } 

   //:END
   //:DropView( lClsLstCT )
   DropView( lClsLstCT );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildClassSeqNumbers( VIEW lClsLstC BASED ON LOD lClsLstC )

//:   INTEGER Count
public int 
olClsLstC_BuildClassSeqNumbers( View     lClsLstC )
{
   int      Count = 0;
   int      RESULT = 0;


   //:// Create the sequence number used by JSP to position on the correct Class entry. This is needed
   //:// as the Class.ID may not be unique do to Cross Listed Classes.
   //:FOR EACH lClsLstC.Class 
   RESULT = lClsLstC.cursor( "Class" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:Count = Count + 1
      Count = Count + 1;
      //:lClsLstC.Class.WebSeqID = Count
      SetAttributeFromInteger( lClsLstC, "Class", "WebSeqID", Count );
      RESULT = lClsLstC.cursor( "Class" ).setNextContinue().toInt();;
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dClassTermCohort( VIEW lClsLstC BASED ON LOD lClsLstC,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING ( 50 ) szTermCohort
public int 
olClsLstC_dClassTermCohort( View     lClsLstC,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szTermCohort = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Display either the Term or the Cohort, depending on which exists.
         //:IF lClsLstC.CollegeTerm EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( lClsLstC, "CollegeTerm" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szTermCohort = lClsLstC.CollegeTerm.YearSemester
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTermCohort;
            if ( szTermCohort == null )
               sb_szTermCohort = new StringBuilder( 32 );
            else
               sb_szTermCohort = new StringBuilder( szTermCohort );
                         GetVariableFromAttribute( sb_szTermCohort, mi_lTempInteger_1, 'S', 51, lClsLstC, "CollegeTerm", "YearSemester", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTermCohort = sb_szTermCohort.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF lClsLstC.Cohort EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( lClsLstC, "Cohort" );
            if ( lTempInteger_2 == 0 )
            { 
               //:szTermCohort = lClsLstC.Cohort.Name 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szTermCohort;
               if ( szTermCohort == null )
                  sb_szTermCohort = new StringBuilder( 32 );
               else
                  sb_szTermCohort = new StringBuilder( szTermCohort );
                               GetVariableFromAttribute( sb_szTermCohort, mi_lTempInteger_3, 'S', 51, lClsLstC, "Cohort", "Name", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szTermCohort = sb_szTermCohort.toString( );}
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( lClsLstC,
         //:                   InternalEntityStructure, InternalAttribStructure, szTermCohort )
         StoreStringInRecord( lClsLstC, InternalEntityStructure, InternalAttribStructure, szTermCohort );
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
//:ActivateClassSelectLST( VIEW lClsLstC BASED ON LOD lClsLstC,
//:                         VIEW mStudenC BASED ON LOD mStudenC )

//:   VIEW lClsLstCT BASED ON LOD lClsLstC
public int 
olClsLstC_ActivateClassSelectLST( zVIEW    lClsLstC,
                                  View     mStudenC )
{
   zVIEW    lClsLstCT = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:// Activate the Class Select List as a concatenation of the regular list of Classes for the Term,
   //:// plus extra entries for each Cross Listed Course.
   //:ACTIVATE lClsLstC Multiple WHERE lClsLstC.CollegeTerm.ID = mStudenC.UpdateSchedule.CollegeTermID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mStudenC, "UpdateSchedule", "CollegeTermID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   olClsLstC_fnLocalBuildQual_0( mStudenC, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( lClsLstC, "lClsLstC", mStudenC, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lClsLstC "lClsLstC"
   SetNameForView( lClsLstC, "lClsLstC", null, zLEVEL_TASK );
   //:CreateCrossListEntries( lClsLstC )
   olClsLstC_CreateCrossListEntries( lClsLstC );
   //:OrderEntityForView( lClsLstC, "Class", "wClassNumberTopicSection A" )
   OrderEntityForView( lClsLstC, "Class", "wClassNumberTopicSection A" );
   //:SET CURSOR FIRST lClsLstC.Class 
   RESULT = lClsLstC.cursor( "Class" ).setFirst().toInt();
   return( 0 );
//    
// END
} 


private int 
olClsLstC_fnLocalBuildQual_0( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Class" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeTerm" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 



}
