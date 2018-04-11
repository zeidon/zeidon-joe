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

public class mClass_Object extends VmlObjectOperations
{
   public mClass_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dClassName( VIEW mClass BASED ON LOD mClass,
//:            STRING ( 32 ) InternalEntityStructure,
//:            STRING ( 32 ) InternalAttribStructure,
//:            SHORT GetOrSetFlag )
//:            
//:   STRING ( 100 ) szName
public int 
omClass_dClassName( View     mClass,
                    String InternalEntityStructure,
                    String InternalAttribStructure,
                    Integer   GetOrSetFlag )
{
   String   szName = null;
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


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mClass.Course EXISTS 
         lTempInteger_0 = CheckExistenceOfEntity( mClass, "Course" );
         if ( lTempInteger_0 == 0 )
         { 
            //:IF mClass.CourseTopic EXISTS
            lTempInteger_1 = CheckExistenceOfEntity( mClass, "CourseTopic" );
            if ( lTempInteger_1 == 0 )
            { 
               //:IF mClass.Class.Section = ""
               if ( CompareAttributeToString( mClass, "Class", "Section", "" ) == 0 )
               { 
                  //:szName = mClass.Course.Number + ", " + mClass.CourseTopic.Number 
                  {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                     GetStringFromAttribute( sb_szName, mClass, "Course", "Number" );
                  szName = sb_szName.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, ", ", 1, 0, 101 );
                  szName = sb_szName.toString( );}
                  {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                  StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                     GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 6, mClass, "CourseTopic", "Number", "", 0 );
                  lTempInteger_2 = mi_lTempInteger_2.intValue( );
                  szTempString_0 = sb_szTempString_0.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, szTempString_0, 1, 0, 101 );
                  szName = sb_szName.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szName = mClass.Course.Number + ", " + mClass.CourseTopic.Number + " - Section: " + mClass.Class.Section
                  {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                     GetStringFromAttribute( sb_szName, mClass, "Course", "Number" );
                  szName = sb_szName.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, ", ", 1, 0, 101 );
                  szName = sb_szName.toString( );}
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_3, 'S', 11, mClass, "CourseTopic", "Number", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szTempString_1 = sb_szTempString_1.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, szTempString_1, 1, 0, 101 );
                  szName = sb_szName.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, " - Section: ", 1, 0, 101 );
                  szName = sb_szName.toString( );}
                  {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                  StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                     GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_4, 'S', 3, mClass, "Class", "Section", "", 0 );
                  lTempInteger_4 = mi_lTempInteger_4.intValue( );
                  szTempString_2 = sb_szTempString_2.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, szTempString_2, 1, 0, 101 );
                  szName = sb_szName.toString( );}
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF mClass.Class.Section = ""
               if ( CompareAttributeToString( mClass, "Class", "Section", "" ) == 0 )
               { 
                  //:szName = mClass.Course.Number
                  {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                  StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                     GetVariableFromAttribute( sb_szName, mi_lTempInteger_5, 'S', 101, mClass, "Course", "Number", "", 0 );
                  lTempInteger_5 = mi_lTempInteger_5.intValue( );
                  szName = sb_szName.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szName = mClass.Course.Number + " - Section: " + mClass.Class.Section
                  {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                     GetStringFromAttribute( sb_szName, mClass, "Course", "Number" );
                  szName = sb_szName.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, " - Section: ", 1, 0, 101 );
                  szName = sb_szName.toString( );}
                  {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                  StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                     GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_6, 'S', 3, mClass, "Class", "Section", "", 0 );
                  lTempInteger_6 = mi_lTempInteger_6.intValue( );
                  szTempString_3 = sb_szTempString_3.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, szTempString_3, 1, 0, 101 );
                  szName = sb_szName.toString( );}
               } 

               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:szName = ""
             {StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                        ZeidonStringCopy( sb_szName, 1, 0, "", 1, 0, 101 );
            szName = sb_szName.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mClass,
         //:                InternalEntityStructure, InternalAttribStructure, szName )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szName );
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
//:ObjectConstraints( VIEW mClass BASED ON LOD mClass,
//:                   SHORT Event,
//:                   SHORT State )

//:   VIEW lClsLstC BASED ON LOD lClsLstC
public int 
omClass_ObjectConstraints( View     mClass,
                           Integer   Event,
                           Integer   State )
{
   zVIEW    lClsLstC = new zVIEW( );
   //:VIEW mClassT  BASED ON LOD mClass
   zVIEW    mClassT = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:// Set Original Final Grade for each Enrollment entity so that we can check later if they have been modified.
         //:CreateViewFromView( mClassT, mClass )
         CreateViewFromView( mClassT, mClass );
         //:FOR EACH mClassT.Enrollment 
         RESULT = mClassT.cursor( "Enrollment" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:mClassT.Enrollment.wOriginalFinalGrade = mClassT.Enrollment.FinalGrade
            SetAttributeFromAttribute( mClassT, "Enrollment", "wOriginalFinalGrade", mClassT, "Enrollment", "FinalGrade" );
            //:mClassT.Enrollment.wOriginalStatus     = mClassT.Enrollment.Status
            SetAttributeFromAttribute( mClassT, "Enrollment", "wOriginalStatus", mClassT, "Enrollment", "Status" );
            RESULT = mClassT.cursor( "Enrollment" ).setNextContinue().toInt();;
         } 

         //:END
         //:DropView( mClassT )
         DropView( mClassT );

         //:// Order the Enrollment records by Student name.
         //:OrderEntityForView( mClass, "Enrollment", "EnrolledStudentPerson.LastName A EnrolledStudentPerson.FirstName A" )
         OrderEntityForView( mClass, "Enrollment", "EnrolledStudentPerson.LastName A EnrolledStudentPerson.FirstName A" );

         //:// Order the Waitlisted records by Ranking.
         //:OrderEntityForView( mClass, "StudentWaitlisted", "WaitListRanking A" )
         OrderEntityForView( mClass, "StudentWaitlisted", "WaitListRanking A" );

         //:// Order the ClassRoomSessions by Day or Date.
         //:OrderEntityForView( mClass, "ClassRoomSession", "MeetingDay A MeetingDate A" )
         OrderEntityForView( mClass, "ClassRoomSession", "MeetingDay A MeetingDate A" );

         //:// Save Original Credit value.
         //:mClass.Class.wOrigCreditHours = mClass.Class.CreditHours 
         SetAttributeFromAttribute( mClass, "Class", "wOrigCreditHours", mClass, "Class", "CreditHours" );

         //:// Create List of Active Topics.
         //:FOR EACH mClass.CourseTopic 
         RESULT = mClass.cursor( "CourseTopic" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mClass.CourseTopic.Status = "A" OR mClass.CourseTopic.Status = ""
            if ( CompareAttributeToString( mClass, "CourseTopic", "Status", "A" ) == 0 || CompareAttributeToString( mClass, "CourseTopic", "Status", "" ) == 0 )
            { 
               //:INCLUDE mClass.ActiveCourseTopic FROM mClass.CourseTopic 
               RESULT = IncludeSubobjectFromSubobject( mClass, "ActiveCourseTopic", mClass, "CourseTopic", zPOS_AFTER );
            } 

            RESULT = mClass.cursor( "CourseTopic" ).setNextContinue().toInt();;
            //:END 
         } 

         //:END
         //:SET CURSOR FIRST mClass.CourseTopic 
         RESULT = mClass.cursor( "CourseTopic" ).setFirst().toInt();
         //:SET CURSOR FIRST mClass.ActiveCourseTopic   
         RESULT = mClass.cursor( "ActiveCourseTopic" ).setFirst().toInt();
         break ;

      //:  /* end zOCE_ACTIVATE */
      //:OF   zOCE_ACTIVATE_EMPTY:
      case zOCE_ACTIVATE_EMPTY :
         break ;

      //:  /* end zOCE_ACTIVATE_EMPTY */
      //:OF   zOCE_COMMIT:
      case zOCE_COMMIT :

         //:// Set the CourseTitle attribute in mClass to match the current value in Course or Course Topic.
         //:IF mClass.Class EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mClass, "Class" );
         if ( lTempInteger_0 == 0 )
         { 
            //:IF mClass.CourseTopic EXISTS
            lTempInteger_1 = CheckExistenceOfEntity( mClass, "CourseTopic" );
            if ( lTempInteger_1 == 0 )
            { 
               //:IF mClass.Class.CourseTitle != mClass.CourseTopic.Title 
               if ( CompareAttributeToAttribute( mClass, "Class", "CourseTitle", mClass, "CourseTopic", "Title" ) != 0 )
               { 
                  //:mClass.Class.CourseTitle = mClass.CourseTopic.Title 
                  SetAttributeFromAttribute( mClass, "Class", "CourseTitle", mClass, "CourseTopic", "Title" );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF mClass.Class.CourseTitle != mClass.Course.Title 
               if ( CompareAttributeToAttribute( mClass, "Class", "CourseTitle", mClass, "Course", "Title" ) != 0 )
               { 
                  //:mClass.Class.CourseTitle = mClass.Course.Title 
                  SetAttributeFromAttribute( mClass, "Class", "CourseTitle", mClass, "Course", "Title" );
               } 

               //:END
            } 

            //:END

            //:// Set the Waitlisted date for any null entries.
            //:FOR EACH mClass.StudentWaitlisted 
            RESULT = mClass.cursor( "StudentWaitlisted" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF mClass.StudentWaitlisted.CreatedDateTime = ""
               if ( CompareAttributeToString( mClass, "StudentWaitlisted", "CreatedDateTime", "" ) == 0 )
               { 
                  //:SetAttributeFromCurrentDateTime( mClass, "StudentWaitlisted", "CreatedDateTime" )
                  {
                   ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mClass );
                   m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mClass, "StudentWaitlisted", "CreatedDateTime" );
                   // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                  }
               } 

               RESULT = mClass.cursor( "StudentWaitlisted" ).setNextContinue().toInt();;
               //:END 
            } 

            //:END

            //:// Create the Change Log entry.
            //:GenerateTransactionEntry( mClass )
            {
             ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mClass );
             m_ZGLOBAL2_Operation.GenerateTransactionEntry( mClass );
             // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
            }
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
//:dInstructorFullName( VIEW mClass BASED ON LOD mClass,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   STRING ( 100 ) szFullName
public int 
omClass_dInstructorFullName( View     mClass,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szFullName = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mClass );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "LeadInstructor"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "LeadInstructor", 1, 0, 33 ) == 0 )
         { 
            //:// The derived attribute is on LeadInstructor entity because of the auto include.
            //:GetPersonFullName( szFullName, mClass, "LeadInstructorPerson" )
            {
             ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mClass );
             {StringBuilder sb_szFullName;
            if ( szFullName == null )
               sb_szFullName = new StringBuilder( 32 );
            else
               sb_szFullName = new StringBuilder( szFullName );
                         m_ZGLOBAL2_Operation.GetPersonFullName( sb_szFullName, mClass, "LeadInstructorPerson" );
            szFullName = sb_szFullName.toString( );}
             // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
            }
            //:ELSE
         } 
         else
         { 
            //:GetPersonFullName( szFullName, mClass, szEntityName )
            {
             ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mClass );
             {StringBuilder sb_szFullName;
            if ( szFullName == null )
               sb_szFullName = new StringBuilder( 32 );
            else
               sb_szFullName = new StringBuilder( szFullName );
                         m_ZGLOBAL2_Operation.GetPersonFullName( sb_szFullName, mClass, szEntityName );
            szFullName = sb_szFullName.toString( );}
             // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
            }
         } 

         //:END

         //:StoreStringInRecord ( mClass,
         //:                      InternalEntityStructure, InternalAttribStructure, szFullName )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szFullName );
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
//:dStudentFullNameLFM( VIEW mClass BASED ON LOD mClass,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   STRING ( 32 )  szEntityName
public int 
omClass_dStudentFullNameLFM( View     mClass,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szEntityName = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mClass );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:PersonName_LastFirstMiddle( mClass, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mClass );
          m_ZGLOBAL1_Operation.PersonName_LastFirstMiddle( mClass, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
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
//:dBuildingRoom( VIEW mClass BASED ON LOD mClass,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   STRING ( 100 ) szBuildingRoom
public int 
omClass_dBuildingRoom( View     mClass,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{
   String   szBuildingRoom = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mClass );
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
            //:szBuildingRoom = mClass.Building.Name + " ,  Room: " + mClass.Room.Number
            {StringBuilder sb_szBuildingRoom;
            if ( szBuildingRoom == null )
               sb_szBuildingRoom = new StringBuilder( 32 );
            else
               sb_szBuildingRoom = new StringBuilder( szBuildingRoom );
                         GetStringFromAttribute( sb_szBuildingRoom, mClass, "Building", "Name" );
            szBuildingRoom = sb_szBuildingRoom.toString( );}
             {StringBuilder sb_szBuildingRoom;
            if ( szBuildingRoom == null )
               sb_szBuildingRoom = new StringBuilder( 32 );
            else
               sb_szBuildingRoom = new StringBuilder( szBuildingRoom );
                        ZeidonStringConcat( sb_szBuildingRoom, 1, 0, " ,  Room: ", 1, 0, 101 );
            szBuildingRoom = sb_szBuildingRoom.toString( );}
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 255, mClass, "Room", "Number", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szBuildingRoom;
            if ( szBuildingRoom == null )
               sb_szBuildingRoom = new StringBuilder( 32 );
            else
               sb_szBuildingRoom = new StringBuilder( szBuildingRoom );
                        ZeidonStringConcat( sb_szBuildingRoom, 1, 0, szTempString_0, 1, 0, 101 );
            szBuildingRoom = sb_szBuildingRoom.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF szEntityName = "FinalExamRoom"
            if ( ZeidonStringCompare( szEntityName, 1, 0, "FinalExamRoom", 1, 0, 33 ) == 0 )
            { 
               //:szBuildingRoom = mClass.FinalExamBuilding.Name + " ,  Room: " + mClass.FinalExamRoom.Number
               {StringBuilder sb_szBuildingRoom;
               if ( szBuildingRoom == null )
                  sb_szBuildingRoom = new StringBuilder( 32 );
               else
                  sb_szBuildingRoom = new StringBuilder( szBuildingRoom );
                               GetStringFromAttribute( sb_szBuildingRoom, mClass, "FinalExamBuilding", "Name" );
               szBuildingRoom = sb_szBuildingRoom.toString( );}
                {StringBuilder sb_szBuildingRoom;
               if ( szBuildingRoom == null )
                  sb_szBuildingRoom = new StringBuilder( 32 );
               else
                  sb_szBuildingRoom = new StringBuilder( szBuildingRoom );
                              ZeidonStringConcat( sb_szBuildingRoom, 1, 0, " ,  Room: ", 1, 0, 101 );
               szBuildingRoom = sb_szBuildingRoom.toString( );}
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 255, mClass, "FinalExamRoom", "Number", "", 0 );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );
               szTempString_1 = sb_szTempString_1.toString( );}
                {StringBuilder sb_szBuildingRoom;
               if ( szBuildingRoom == null )
                  sb_szBuildingRoom = new StringBuilder( 32 );
               else
                  sb_szBuildingRoom = new StringBuilder( szBuildingRoom );
                              ZeidonStringConcat( sb_szBuildingRoom, 1, 0, szTempString_1, 1, 0, 101 );
               szBuildingRoom = sb_szBuildingRoom.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szBuildingRoom = mClass.StudyOptionGroupBuilding.Name + " ,  Room: " + mClass.StudyOptionGroupRoom.Number
               {StringBuilder sb_szBuildingRoom;
               if ( szBuildingRoom == null )
                  sb_szBuildingRoom = new StringBuilder( 32 );
               else
                  sb_szBuildingRoom = new StringBuilder( szBuildingRoom );
                               GetStringFromAttribute( sb_szBuildingRoom, mClass, "StudyOptionGroupBuilding", "Name" );
               szBuildingRoom = sb_szBuildingRoom.toString( );}
                {StringBuilder sb_szBuildingRoom;
               if ( szBuildingRoom == null )
                  sb_szBuildingRoom = new StringBuilder( 32 );
               else
                  sb_szBuildingRoom = new StringBuilder( szBuildingRoom );
                              ZeidonStringConcat( sb_szBuildingRoom, 1, 0, " ,  Room: ", 1, 0, 101 );
               szBuildingRoom = sb_szBuildingRoom.toString( );}
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szTempString_2;
               if ( szTempString_2 == null )
                  sb_szTempString_2 = new StringBuilder( 32 );
               else
                  sb_szTempString_2 = new StringBuilder( szTempString_2 );
                               GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_2, 'S', 255, mClass, "StudyOptionGroupRoom", "Number", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szTempString_2 = sb_szTempString_2.toString( );}
                {StringBuilder sb_szBuildingRoom;
               if ( szBuildingRoom == null )
                  sb_szBuildingRoom = new StringBuilder( 32 );
               else
                  sb_szBuildingRoom = new StringBuilder( szBuildingRoom );
                              ZeidonStringConcat( sb_szBuildingRoom, 1, 0, szTempString_2, 1, 0, 101 );
               szBuildingRoom = sb_szBuildingRoom.toString( );}
            } 

            //:END
         } 

         //:END

         //:StoreStringInRecord ( mClass,
         //:                      InternalEntityStructure, InternalAttribStructure, szBuildingRoom )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szBuildingRoom );
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
//:ValidateGrade( VIEW mClass BASED ON LOD mClass )

//:   DECIMAL FinalGrade
public int 
omClass_ValidateGrade( View     mClass )
{
   double  FinalGrade = 0.0;
   //:STRING ( 10 )  szFinalGrade
   String   szFinalGrade = null;
   //:STRING ( 20 )  szDecimalString
   String   szDecimalString = null;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_3 = 0;
   String   szTempString_4 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_5 = null;
   int      lTempInteger_5 = 0;


   //:// Perform validation of Grade for the current enrollment record.
   //:// Note this code does not actually return an error code, but only gives the user a warning message.

   //:// If a Class is pass/fail and the grade is not S or U, then convert the grade to an S or a U
   //:// depending on the grade. Less than a C- is a U.
   //:// Make sure we don't do this if the Student has withdrawn.
   //:IF mClass.Enrollment.Status != "W"
   if ( CompareAttributeToString( mClass, "Enrollment", "Status", "W" ) != 0 )
   { 
      //:IF mClass.Enrollment.TakingClassType = "P" AND mClass.Enrollment.FinalGrade != ""
      if ( CompareAttributeToString( mClass, "Enrollment", "TakingClassType", "P" ) == 0 && CompareAttributeToString( mClass, "Enrollment", "FinalGrade", "" ) != 0 )
      { 
         //:IF mClass.Enrollment.FinalGrade != "S" AND mClass.Enrollment.FinalGrade != "U"
         if ( CompareAttributeToString( mClass, "Enrollment", "FinalGrade", "S" ) != 0 && CompareAttributeToString( mClass, "Enrollment", "FinalGrade", "U" ) != 0 )
         { 
            //:GetStringFromAttributeByContext( szDecimalString,
            //:                                 mClass, "Enrollment", "FinalGrade", "DegreeTrackGradePointValue", 20 )
            {StringBuilder sb_szDecimalString;
            if ( szDecimalString == null )
               sb_szDecimalString = new StringBuilder( 32 );
            else
               sb_szDecimalString = new StringBuilder( szDecimalString );
                         GetStringFromAttributeByContext( sb_szDecimalString, mClass, "Enrollment", "FinalGrade", "DegreeTrackGradePointValue", 20 );
            szDecimalString = sb_szDecimalString.toString( );}
            //:FinalGrade = StrToDecimal( szDecimalString )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mClass );
             FinalGrade = m_ZGLOBAL1_Operation.StrToDecimal( szDecimalString );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:IF FinalGrade < 1.7
            if ( FinalGrade < 1.7 )
            { 
               //:szFinalGrade = "U"
                {StringBuilder sb_szFinalGrade;
               if ( szFinalGrade == null )
                  sb_szFinalGrade = new StringBuilder( 32 );
               else
                  sb_szFinalGrade = new StringBuilder( szFinalGrade );
                              ZeidonStringCopy( sb_szFinalGrade, 1, 0, "U", 1, 0, 11 );
               szFinalGrade = sb_szFinalGrade.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szFinalGrade = "S"
                {StringBuilder sb_szFinalGrade;
               if ( szFinalGrade == null )
                  sb_szFinalGrade = new StringBuilder( 32 );
               else
                  sb_szFinalGrade = new StringBuilder( szFinalGrade );
                              ZeidonStringCopy( sb_szFinalGrade, 1, 0, "S", 1, 0, 11 );
               szFinalGrade = sb_szFinalGrade.toString( );}
            } 

            //:END
            //:mClass.Enrollment.FinalGrade = szFinalGrade
            SetAttributeFromString( mClass, "Enrollment", "FinalGrade", szFinalGrade );
            //:szMsg = "The final grade for student, " + 
            //:        mClass.EnrolledStudentPerson.FirstName + " " + mClass.EnrolledStudentPerson.LastName +
            //:        ", has been automatically changed to " + szFinalGrade + "." 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 51, mClass, "EnrolledStudentPerson", "FirstName", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringCopy( sb_szMsg, 1, 0, "The final grade for student, ", 1, 0, 201 );
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
                        ZeidonStringConcat( sb_szMsg, 1, 0, " ", 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 51, mClass, "EnrolledStudentPerson", "LastName", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
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
                        ZeidonStringConcat( sb_szMsg, 1, 0, ", has been automatically changed to ", 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, szFinalGrade, 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, ".", 1, 0, 201 );
            szMsg = sb_szMsg.toString( );}
            //:MessageSend( mClass, "", "Grade Validation",
            //:             szMsg,
            //:             zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
            MessageSend( mClass, "", "Grade Validation", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
         } 

         //:END 
      } 

      //:END
   } 

   //:END

   //:// For an Audited Class, make sure FinalGrade is blank and CreditHours are 0.
   //:IF mClass.Enrollment.TakingClassType = "A"
   if ( CompareAttributeToString( mClass, "Enrollment", "TakingClassType", "A" ) == 0 )
   { 
      //:IF mClass.Enrollment.FinalGrade != ""
      if ( CompareAttributeToString( mClass, "Enrollment", "FinalGrade", "" ) != 0 )
      { 
         //:szMsg = "The final grade for student, " + 
         //:        mClass.EnrolledStudentPerson.FirstName + " " + mClass.EnrolledStudentPerson.LastName +
         //:        ", has been automatically deleted because the class is being audited." 
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szTempString_2;
         if ( szTempString_2 == null )
            sb_szTempString_2 = new StringBuilder( 32 );
         else
            sb_szTempString_2 = new StringBuilder( szTempString_2 );
                   GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_2, 'S', 51, mClass, "EnrolledStudentPerson", "FirstName", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szTempString_2 = sb_szTempString_2.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "The final grade for student, ", 1, 0, 201 );
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
                  ZeidonStringConcat( sb_szMsg, 1, 0, " ", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                   GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_3, 'S', 51, mClass, "EnrolledStudentPerson", "LastName", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         szTempString_3 = sb_szTempString_3.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_3, 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, ", has been automatically deleted because the class is being audited.", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
         //:MessageSend( mClass, "", "Grade Validation",
         //:             szMsg,
         //:             zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
         MessageSend( mClass, "", "Grade Validation", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
         //:mClass.Enrollment.FinalGrade = ""
         SetAttributeFromString( mClass, "Enrollment", "FinalGrade", "" );
      } 

      //:END
      //:IF mClass.Enrollment.CreditHours > 0
      if ( CompareAttributeToInteger( mClass, "Enrollment", "CreditHours", 0 ) > 0 )
      { 
         //:szMsg = "The credit hours for student, " + 
         //:        mClass.EnrolledStudentPerson.FirstName + " " + mClass.EnrolledStudentPerson.LastName +
         //:        ", are being set to zero because the class is being audited." 
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
         StringBuilder sb_szTempString_4;
         if ( szTempString_4 == null )
            sb_szTempString_4 = new StringBuilder( 32 );
         else
            sb_szTempString_4 = new StringBuilder( szTempString_4 );
                   GetVariableFromAttribute( sb_szTempString_4, mi_lTempInteger_4, 'S', 51, mClass, "EnrolledStudentPerson", "FirstName", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );
         szTempString_4 = sb_szTempString_4.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "The credit hours for student, ", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_4, 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, " ", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
         StringBuilder sb_szTempString_5;
         if ( szTempString_5 == null )
            sb_szTempString_5 = new StringBuilder( 32 );
         else
            sb_szTempString_5 = new StringBuilder( szTempString_5 );
                   GetVariableFromAttribute( sb_szTempString_5, mi_lTempInteger_5, 'S', 51, mClass, "EnrolledStudentPerson", "LastName", "", 0 );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );
         szTempString_5 = sb_szTempString_5.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_5, 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, ", are being set to zero because the class is being audited.", 1, 0, 201 );
         szMsg = sb_szMsg.toString( );}
         //:MessageSend( mClass, "", "Grade Validation",
         //:             szMsg,
         //:             zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
         MessageSend( mClass, "", "Grade Validation", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
         //:mClass.Enrollment.CreditHours = 0
         SetAttributeFromInteger( mClass, "Enrollment", "CreditHours", 0 );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:ENTITY CONSTRAINT OPERATION
//:EnrollmentConstraint( VIEW mClass BASED ON LOD mClass,
//:                      STRING ( 32 ) NameOfEntity,
//:                      SHORT Event,
//:                      SHORT State )

//:   SHORT nRC
public int 
omClass_EnrollmentConstraint( View     mClass,
                              String   NameOfEntity,
                              Integer   Event,
                              Integer   State )
{
   int      nRC = 0;


   //:CASE Event
   switch( Event )
   { 
      //:OF   zECE_ACCEPT:
      case zECE_ACCEPT :

         //:nRC = ValidateGrade(  mClass )
         //nRC = omClass_ValidateGrade( mClass );
         //:RETURN nRC
         if(8==8)return( nRC );

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


   //:DERIVED ATTRIBUTE OPERATION
   //:dTotalEnrolled( VIEW mClass BASED ON LOD mClass,
   //:             STRING ( 32 ) InternalEntityStructure,
   //:             STRING ( 32 ) InternalAttribStructure,
   //:             SHORT GetOrSetFlag )

   //:INTEGER Total
public int 
omClass_dTotalEnrolled( View     mClass,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   int      Total = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:Total = 0
         Total = 0;
         //:FOR EACH mClass.Enrollment 
         RESULT = mClass.cursor( "Enrollment" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mClass.Enrollment.Status = "T" OR mClass.Enrollment.Status = "C"
            if ( CompareAttributeToString( mClass, "Enrollment", "Status", "T" ) == 0 || CompareAttributeToString( mClass, "Enrollment", "Status", "C" ) == 0 )
            { 
               //:Total = Total + 1
               Total = Total + 1;
            } 

            RESULT = mClass.cursor( "Enrollment" ).setNextContinue().toInt();;
            //:END
         } 

         //:END

         //:StoreValueInRecord ( mClass,
         //:                  InternalEntityStructure, InternalAttribStructure, Total, 0 )
         StoreValueInRecord( mClass, InternalEntityStructure, InternalAttribStructure, Total, 0 );
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
//:dTotalWaitlisted( VIEW mClass BASED ON LOD mClass,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   INTEGER Total
public int 
omClass_dTotalWaitlisted( View     mClass,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   int      Total = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:Total = 0
         Total = 0;
         //:FOR EACH mClass.StudentWaitlisted 
         RESULT = mClass.cursor( "StudentWaitlisted" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:Total = Total + 1 
            Total = Total + 1;
            RESULT = mClass.cursor( "StudentWaitlisted" ).setNextContinue().toInt();;
         } 

         //:END

         //:StoreValueInRecord ( mClass,
         //:                  InternalEntityStructure, InternalAttribStructure, Total, 0 )
         StoreValueInRecord( mClass, InternalEntityStructure, InternalAttribStructure, Total, 0 );
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
//:dClassTopicNumberTitle( VIEW mClass BASED ON LOD mClass,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   STRING ( 30 ) szTopicTitle
public int 
omClass_dClassTopicNumberTitle( View     mClass,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   String   szTopicTitle = null;
   //:STRING ( 10 ) szTopicNumber
   String   szTopicNumber = null;
   //:STRING ( 40 ) szTopicNumberTitle
   String   szTopicNumberTitle = null;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mClass );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:GetStringFromAttribute( szTopicNumber, mClass, szEntityName, "Number" )
         {StringBuilder sb_szTopicNumber;
         if ( szTopicNumber == null )
            sb_szTopicNumber = new StringBuilder( 32 );
         else
            sb_szTopicNumber = new StringBuilder( szTopicNumber );
                   GetStringFromAttribute( sb_szTopicNumber, mClass, szEntityName, "Number" );
         szTopicNumber = sb_szTopicNumber.toString( );}
         //:GetStringFromAttribute( szTopicTitle, mClass, szEntityName, "Title" )
         {StringBuilder sb_szTopicTitle;
         if ( szTopicTitle == null )
            sb_szTopicTitle = new StringBuilder( 32 );
         else
            sb_szTopicTitle = new StringBuilder( szTopicTitle );
                   GetStringFromAttribute( sb_szTopicTitle, mClass, szEntityName, "Title" );
         szTopicTitle = sb_szTopicTitle.toString( );}
         //:IF szTopicNumber = ""
         if ( ZeidonStringCompare( szTopicNumber, 1, 0, "", 1, 0, 11 ) == 0 )
         { 
            //:szTopicNumberTitle = szTopicTitle
             {StringBuilder sb_szTopicNumberTitle;
            if ( szTopicNumberTitle == null )
               sb_szTopicNumberTitle = new StringBuilder( 32 );
            else
               sb_szTopicNumberTitle = new StringBuilder( szTopicNumberTitle );
                        ZeidonStringCopy( sb_szTopicNumberTitle, 1, 0, szTopicTitle, 1, 0, 41 );
            szTopicNumberTitle = sb_szTopicNumberTitle.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szTopicNumberTitle = szTopicNumber + " - " + szTopicTitle
             {StringBuilder sb_szTopicNumberTitle;
            if ( szTopicNumberTitle == null )
               sb_szTopicNumberTitle = new StringBuilder( 32 );
            else
               sb_szTopicNumberTitle = new StringBuilder( szTopicNumberTitle );
                        ZeidonStringCopy( sb_szTopicNumberTitle, 1, 0, szTopicNumber, 1, 0, 41 );
            szTopicNumberTitle = sb_szTopicNumberTitle.toString( );}
             {StringBuilder sb_szTopicNumberTitle;
            if ( szTopicNumberTitle == null )
               sb_szTopicNumberTitle = new StringBuilder( 32 );
            else
               sb_szTopicNumberTitle = new StringBuilder( szTopicNumberTitle );
                        ZeidonStringConcat( sb_szTopicNumberTitle, 1, 0, " - ", 1, 0, 41 );
            szTopicNumberTitle = sb_szTopicNumberTitle.toString( );}
             {StringBuilder sb_szTopicNumberTitle;
            if ( szTopicNumberTitle == null )
               sb_szTopicNumberTitle = new StringBuilder( 32 );
            else
               sb_szTopicNumberTitle = new StringBuilder( szTopicNumberTitle );
                        ZeidonStringConcat( sb_szTopicNumberTitle, 1, 0, szTopicTitle, 1, 0, 41 );
            szTopicNumberTitle = sb_szTopicNumberTitle.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mClass,
         //:                      InternalEntityStructure, InternalAttribStructure, szTopicNumberTitle )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szTopicNumberTitle );
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
//:ValidateClassData( VIEW mClass BASED ON LOD mClass )

//:   SHORT nRC
public int 
omClass_ValidateClassData( View     mClass )
{
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      RESULT = 0;
   int      lTempInteger_3 = 0;


   //:// Perform standard Class validations and return a -1 if there is an error.

   //:// Both a Course and a College Term must be selected.
   //:IF mClass.Course DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mClass, "Course" );
   if ( lTempInteger_0 != 0 )
   { 
      //:MessageSend( mClass, "", "Class Validations",
      //:             "A Course must be selected for the Class.",
      //:             zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
      MessageSend( mClass, "", "Class Validations", "A Course must be selected for the Class.", zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   //:IF mClass.CollegeTerm DOES NOT EXIST AND mClass.Cohort DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( mClass, "CollegeTerm" );
   lTempInteger_2 = CheckExistenceOfEntity( mClass, "Cohort" );
   if ( lTempInteger_1 != 0 && lTempInteger_2 != 0 )
   { 
      //:MessageSend( mClass, "", "Class Validations",
      //:             "A College Term must be selected for the Class.",
      //:             zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
      MessageSend( mClass, "", "Class Validations", "A College Term must be selected for the Class.", zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Prompt the operator if the Section is not numeric.
   //:// Possibly temporary code as not every customer would require a Section to be numeric.
   //:IF mClass.Class.Section != ""
   if ( CompareAttributeToString( mClass, "Class", "Section", "" ) != 0 )
   { 
      //:IF mClass.Class.Section < "0" OR mClass.Class.Section > "9"
      if ( CompareAttributeToString( mClass, "Class", "Section", "0" ) < 0 || CompareAttributeToString( mClass, "Class", "Section", "9" ) > 0 )
      { 
         //:nRC = MessagePrompt( mClass, "", "Contact List Update",
         //:                     "A Section should be made up of numeric characters. Do you want to continue the save of the Class with its current Section value?", 
         //:                     0, zBUTTONS_YESNO, zRESPONSE_YES, 0 )
         nRC = MessagePrompt( mClass, "", "Contact List Update", "A Section should be made up of numeric characters. Do you want to continue the save of the Class with its current Section value?", 0, zBUTTONS_YESNO, zRESPONSE_YES, 0 );
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

   //:// If the Course for the Class has Topics, make sure that one is chosen.
   //:SET CURSOR FIRST mClass.CourseTopic WHERE mClass.CourseTopic.Status = "A" 
   RESULT = mClass.cursor( "CourseTopic" ).setFirst( "Status", "A" ).toInt();
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:IF mClass.ClassCourseTopic DOES NOT EXIST
      lTempInteger_3 = CheckExistenceOfEntity( mClass, "ClassCourseTopic" );
      if ( lTempInteger_3 != 0 )
      { 
         //:MessageSend( mClass, "", "Class Validations",
         //:             "Since the Course for this Class has Topics, a Topic must be chosen.",
         //:             zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
         MessageSend( mClass, "", "Class Validations", "Since the Course for this Class has Topics, a Topic must be chosen.", zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:ENTITY CONSTRAINT OPERATION
//:RoomSessionConstraint( VIEW mClass BASED ON LOD mClass,
//:                       STRING ( 32 ) NameOfEntity,
//:                       SHORT Event,
//:                       SHORT State )

//:   SHORT nRC
public int 
omClass_RoomSessionConstraint( View     mClass,
                               String   NameOfEntity,
                               Integer   Event,
                               Integer   State )
{
   int      nRC = 0;


   //:CASE Event
   switch( Event )
   { 
      //:OF   zECE_ACCEPT:
      case zECE_ACCEPT :

         //:IF mClass.ClassRoomSession.StartTime = "" OR mClass.ClassRoomSession.EndTime = ""
         if ( CompareAttributeToString( mClass, "ClassRoomSession", "StartTime", "" ) == 0 || CompareAttributeToString( mClass, "ClassRoomSession", "EndTime", "" ) == 0 )
         { 
            //:MessageSend( mClass, "", "Room Session Validations",
            //:       "Both a Start Time and an End Time must be specified.",
            //:       zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mClass, "", "Room Session Validations", "Both a Start Time and an End Time must be specified.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
         //:IF mClass.ClassRoomSession.MeetingDay = "" AND mClass.ClassRoomSession.MeetingDate = ""
         if ( CompareAttributeToString( mClass, "ClassRoomSession", "MeetingDay", "" ) == 0 && CompareAttributeToString( mClass, "ClassRoomSession", "MeetingDate", "" ) == 0 )
         { 
            //:MessageSend( mClass, "", "Room Session Validations",
            //:       "Either a Meeting Day or Date must be specified.",
            //:       zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mClass, "", "Room Session Validations", "Either a Meeting Day or Date must be specified.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
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


   //:DERIVED ATTRIBUTE OPERATION
   //:dMeetingDateDayOfWeek( VIEW mClass BASED ON LOD mClass,
   //:                    STRING ( 32 ) InternalEntityStructure,
   //:                    STRING ( 32 ) InternalAttribStructure,
   //:                    SHORT GetOrSetFlag )

   //:STRING ( 20 ) szDayOfWeek
public int 
omClass_dMeetingDateDayOfWeek( View     mClass,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   String   szDayOfWeek = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetStringFromAttributeByContext( szDayOfWeek, mClass, "ClassRoomSession", "MeetingDate", "DayOfWeek", 20 )
         {StringBuilder sb_szDayOfWeek;
         if ( szDayOfWeek == null )
            sb_szDayOfWeek = new StringBuilder( 32 );
         else
            sb_szDayOfWeek = new StringBuilder( szDayOfWeek );
                   GetStringFromAttributeByContext( sb_szDayOfWeek, mClass, "ClassRoomSession", "MeetingDate", "DayOfWeek", 20 );
         szDayOfWeek = sb_szDayOfWeek.toString( );}

         //:StoreStringInRecord ( mClass,
         //:                   InternalEntityStructure, InternalAttribStructure, szDayOfWeek )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szDayOfWeek );
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
//:dMeetingDateAndDayLST( VIEW mClass BASED ON LOD mClass,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   STRING ( 20 ) szDayOfWeek
public int 
omClass_dMeetingDateAndDayLST( View     mClass,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   String   szDayOfWeek = null;
   //:STRING ( 10 ) szDate
   String   szDate = null;
   //:STRING ( 30 ) szDisplayWeek
   String   szDisplayWeek = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetStringFromAttributeByContext( szDayOfWeek, mClass, "ClassRoomSession", "MeetingDate", "DayOfWeek", 20 )
         {StringBuilder sb_szDayOfWeek;
         if ( szDayOfWeek == null )
            sb_szDayOfWeek = new StringBuilder( 32 );
         else
            sb_szDayOfWeek = new StringBuilder( szDayOfWeek );
                   GetStringFromAttributeByContext( sb_szDayOfWeek, mClass, "ClassRoomSession", "MeetingDate", "DayOfWeek", 20 );
         szDayOfWeek = sb_szDayOfWeek.toString( );}
         //:GetStringFromAttributeByContext( szDate, mClass, "ClassRoomSession", "MeetingDate", "", 10 )
         {StringBuilder sb_szDate;
         if ( szDate == null )
            sb_szDate = new StringBuilder( 32 );
         else
            sb_szDate = new StringBuilder( szDate );
                   GetStringFromAttributeByContext( sb_szDate, mClass, "ClassRoomSession", "MeetingDate", "", 10 );
         szDate = sb_szDate.toString( );}
         //:szDisplayWeek = szDayOfWeek + ", " + szDate
          {StringBuilder sb_szDisplayWeek;
         if ( szDisplayWeek == null )
            sb_szDisplayWeek = new StringBuilder( 32 );
         else
            sb_szDisplayWeek = new StringBuilder( szDisplayWeek );
                  ZeidonStringCopy( sb_szDisplayWeek, 1, 0, szDayOfWeek, 1, 0, 31 );
         szDisplayWeek = sb_szDisplayWeek.toString( );}
          {StringBuilder sb_szDisplayWeek;
         if ( szDisplayWeek == null )
            sb_szDisplayWeek = new StringBuilder( 32 );
         else
            sb_szDisplayWeek = new StringBuilder( szDisplayWeek );
                  ZeidonStringConcat( sb_szDisplayWeek, 1, 0, ", ", 1, 0, 31 );
         szDisplayWeek = sb_szDisplayWeek.toString( );}
          {StringBuilder sb_szDisplayWeek;
         if ( szDisplayWeek == null )
            sb_szDisplayWeek = new StringBuilder( 32 );
         else
            sb_szDisplayWeek = new StringBuilder( szDisplayWeek );
                  ZeidonStringConcat( sb_szDisplayWeek, 1, 0, szDate, 1, 0, 31 );
         szDisplayWeek = sb_szDisplayWeek.toString( );}
         //:TraceLineS( "**** Display Week: ", szDisplayWeek )
         TraceLineS( "**** Display Week: ", szDisplayWeek );

         //:StoreStringInRecord ( mClass,
         //:                   InternalEntityStructure, InternalAttribStructure, szDisplayWeek )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szDisplayWeek );
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
//:dMeetingDateAndDayDSP( VIEW mClass BASED ON LOD mClass,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   STRING ( 20 ) szDayOfWeek
public int 
omClass_dMeetingDateAndDayDSP( View     mClass,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   String   szDayOfWeek = null;
   //:STRING ( 10 ) szDate
   String   szDate = null;
   //:STRING ( 30 ) szDisplayWeek
   String   szDisplayWeek = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetStringFromAttributeByContext( szDayOfWeek, mClass, "AttendanceSession", "Date", "DayOfWeek", 20 )
         {StringBuilder sb_szDayOfWeek;
         if ( szDayOfWeek == null )
            sb_szDayOfWeek = new StringBuilder( 32 );
         else
            sb_szDayOfWeek = new StringBuilder( szDayOfWeek );
                   GetStringFromAttributeByContext( sb_szDayOfWeek, mClass, "AttendanceSession", "Date", "DayOfWeek", 20 );
         szDayOfWeek = sb_szDayOfWeek.toString( );}
         //:GetStringFromAttributeByContext( szDate, mClass, "AttendanceSession", "Date", "", 10 )
         {StringBuilder sb_szDate;
         if ( szDate == null )
            sb_szDate = new StringBuilder( 32 );
         else
            sb_szDate = new StringBuilder( szDate );
                   GetStringFromAttributeByContext( sb_szDate, mClass, "AttendanceSession", "Date", "", 10 );
         szDate = sb_szDate.toString( );}
         //:szDisplayWeek = szDayOfWeek + ", " + szDate
          {StringBuilder sb_szDisplayWeek;
         if ( szDisplayWeek == null )
            sb_szDisplayWeek = new StringBuilder( 32 );
         else
            sb_szDisplayWeek = new StringBuilder( szDisplayWeek );
                  ZeidonStringCopy( sb_szDisplayWeek, 1, 0, szDayOfWeek, 1, 0, 31 );
         szDisplayWeek = sb_szDisplayWeek.toString( );}
          {StringBuilder sb_szDisplayWeek;
         if ( szDisplayWeek == null )
            sb_szDisplayWeek = new StringBuilder( 32 );
         else
            sb_szDisplayWeek = new StringBuilder( szDisplayWeek );
                  ZeidonStringConcat( sb_szDisplayWeek, 1, 0, ", ", 1, 0, 31 );
         szDisplayWeek = sb_szDisplayWeek.toString( );}
          {StringBuilder sb_szDisplayWeek;
         if ( szDisplayWeek == null )
            sb_szDisplayWeek = new StringBuilder( 32 );
         else
            sb_szDisplayWeek = new StringBuilder( szDisplayWeek );
                  ZeidonStringConcat( sb_szDisplayWeek, 1, 0, szDate, 1, 0, 31 );
         szDisplayWeek = sb_szDisplayWeek.toString( );}
         //:TraceLineS( "**** Display Week: ", szDisplayWeek )
         TraceLineS( "**** Display Week: ", szDisplayWeek );

         //:StoreStringInRecord ( mClass,
         //:                   InternalEntityStructure, InternalAttribStructure, szDisplayWeek )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szDisplayWeek );
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
//:dClassTermOrCohort( VIEW mClass BASED ON LOD mClass,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )
//:            
//:   STRING ( 100 ) szName
public int 
omClass_dClassTermOrCohort( View     mClass,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szName = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mClass.CollegeTerm EXISTS 
         lTempInteger_0 = CheckExistenceOfEntity( mClass, "CollegeTerm" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szName = mClass.CollegeTerm.YearSemester 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                         GetVariableFromAttribute( sb_szName, mi_lTempInteger_1, 'S', 101, mClass, "CollegeTerm", "YearSemester", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szName = sb_szName.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mClass.Cohort EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mClass, "Cohort" );
            if ( lTempInteger_2 == 0 )
            { 
               //:szName = mClass.Cohort.Name 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szName;
               if ( szName == null )
                  sb_szName = new StringBuilder( 32 );
               else
                  sb_szName = new StringBuilder( szName );
                               GetVariableFromAttribute( sb_szName, mi_lTempInteger_3, 'S', 101, mClass, "Cohort", "Name", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szName = sb_szName.toString( );}
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mClass,
         //:                InternalEntityStructure, InternalAttribStructure, szName )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szName );
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
//:dStudentTrackMajor( VIEW mClass BASED ON LOD mClass,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   VIEW mClassT BASED ON LOD mClass
public int 
omClass_dStudentTrackMajor( View     mClass,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   zVIEW    mClassT = new zVIEW( );
   //:STRING ( 100 ) szMajorTrack
   String   szMajorTrack = null;
   //:INTEGER        Count
   int      Count = 0;
   int      RESULT = 0;
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

         //:// Combine the Degree Track and Major into a single string.
         //:// If the are multiple Tracks, combine them.
         //:CreateViewFromView( mClassT, mClass )
         CreateViewFromView( mClassT, mClass );
         //:FOR EACH mClassT.DisplayStudentMajorDegreeTrack
         RESULT = mClassT.cursor( "DisplayStudentMajorDegreeTrack" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:Count = Count + 1
            Count = Count + 1;
            //:IF Count > 1
            if ( Count > 1 )
            { 
               //:szMajorTrack = szMajorTrack + ", " + mClassT.DisplayDegreeMajor.Name + "/" + mClassT.DisplayDegreeTrack.Name 
                {StringBuilder sb_szMajorTrack;
               if ( szMajorTrack == null )
                  sb_szMajorTrack = new StringBuilder( 32 );
               else
                  sb_szMajorTrack = new StringBuilder( szMajorTrack );
                              ZeidonStringConcat( sb_szMajorTrack, 1, 0, ", ", 1, 0, 101 );
               szMajorTrack = sb_szMajorTrack.toString( );}
               {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 51, mClassT, "DisplayDegreeMajor", "Name", "", 0 );
               lTempInteger_0 = mi_lTempInteger_0.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szMajorTrack;
               if ( szMajorTrack == null )
                  sb_szMajorTrack = new StringBuilder( 32 );
               else
                  sb_szMajorTrack = new StringBuilder( szMajorTrack );
                              ZeidonStringConcat( sb_szMajorTrack, 1, 0, szTempString_0, 1, 0, 101 );
               szMajorTrack = sb_szMajorTrack.toString( );}
                {StringBuilder sb_szMajorTrack;
               if ( szMajorTrack == null )
                  sb_szMajorTrack = new StringBuilder( 32 );
               else
                  sb_szMajorTrack = new StringBuilder( szMajorTrack );
                              ZeidonStringConcat( sb_szMajorTrack, 1, 0, "/", 1, 0, 101 );
               szMajorTrack = sb_szMajorTrack.toString( );}
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 51, mClassT, "DisplayDegreeTrack", "Name", "", 0 );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );
               szTempString_1 = sb_szTempString_1.toString( );}
                {StringBuilder sb_szMajorTrack;
               if ( szMajorTrack == null )
                  sb_szMajorTrack = new StringBuilder( 32 );
               else
                  sb_szMajorTrack = new StringBuilder( szMajorTrack );
                              ZeidonStringConcat( sb_szMajorTrack, 1, 0, szTempString_1, 1, 0, 101 );
               szMajorTrack = sb_szMajorTrack.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szMajorTrack = mClassT.DisplayDegreeMajor.Name + "/" + mClassT.DisplayDegreeTrack.Name 
               {StringBuilder sb_szMajorTrack;
               if ( szMajorTrack == null )
                  sb_szMajorTrack = new StringBuilder( 32 );
               else
                  sb_szMajorTrack = new StringBuilder( szMajorTrack );
                               GetStringFromAttribute( sb_szMajorTrack, mClassT, "DisplayDegreeMajor", "Name" );
               szMajorTrack = sb_szMajorTrack.toString( );}
                {StringBuilder sb_szMajorTrack;
               if ( szMajorTrack == null )
                  sb_szMajorTrack = new StringBuilder( 32 );
               else
                  sb_szMajorTrack = new StringBuilder( szMajorTrack );
                              ZeidonStringConcat( sb_szMajorTrack, 1, 0, "/", 1, 0, 101 );
               szMajorTrack = sb_szMajorTrack.toString( );}
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szTempString_2;
               if ( szTempString_2 == null )
                  sb_szTempString_2 = new StringBuilder( 32 );
               else
                  sb_szTempString_2 = new StringBuilder( szTempString_2 );
                               GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_2, 'S', 51, mClassT, "DisplayDegreeTrack", "Name", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szTempString_2 = sb_szTempString_2.toString( );}
                {StringBuilder sb_szMajorTrack;
               if ( szMajorTrack == null )
                  sb_szMajorTrack = new StringBuilder( 32 );
               else
                  sb_szMajorTrack = new StringBuilder( szMajorTrack );
                              ZeidonStringConcat( sb_szMajorTrack, 1, 0, szTempString_2, 1, 0, 101 );
               szMajorTrack = sb_szMajorTrack.toString( );}
            } 

            RESULT = mClassT.cursor( "DisplayStudentMajorDegreeTrack" ).setNextContinue().toInt();;
            //:END
         } 

         //:END
         //:DropView( mClassT )
         DropView( mClassT );

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mClass,
         //:                InternalEntityStructure, InternalAttribStructure, szMajorTrack )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szMajorTrack );
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
//:dDisplayFacultyGrade( VIEW mClass BASED ON LOD mClass,
//:                           STRING ( 32 ) InternalEntityStructure,
//:                           STRING ( 32 ) InternalAttribStructure,
//:                           SHORT GetOrSetFlag )
//:            
//:   STRING ( 10 ) szDisplayFacultyGrade
public int 
omClass_dDisplayFacultyGrade( View     mClass,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szDisplayFacultyGrade = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mClass.Class.FacultyHasSubmittedGradesFlag = "Y"
         if ( CompareAttributeToString( mClass, "Class", "FacultyHasSubmittedGradesFlag", "Y" ) == 0 )
         { 
            //:GetStringFromAttributeByContext( szDisplayFacultyGrade, mClass, "DisplayEnrollment", "FacultyEnteredFinalGrade", "", 10 )
            {StringBuilder sb_szDisplayFacultyGrade;
            if ( szDisplayFacultyGrade == null )
               sb_szDisplayFacultyGrade = new StringBuilder( 32 );
            else
               sb_szDisplayFacultyGrade = new StringBuilder( szDisplayFacultyGrade );
                         GetStringFromAttributeByContext( sb_szDisplayFacultyGrade, mClass, "DisplayEnrollment", "FacultyEnteredFinalGrade", "", 10 );
            szDisplayFacultyGrade = sb_szDisplayFacultyGrade.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szDisplayFacultyGrade = ""
             {StringBuilder sb_szDisplayFacultyGrade;
            if ( szDisplayFacultyGrade == null )
               sb_szDisplayFacultyGrade = new StringBuilder( 32 );
            else
               sb_szDisplayFacultyGrade = new StringBuilder( szDisplayFacultyGrade );
                        ZeidonStringCopy( sb_szDisplayFacultyGrade, 1, 0, "", 1, 0, 11 );
            szDisplayFacultyGrade = sb_szDisplayFacultyGrade.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mClass,
         //:                InternalEntityStructure, InternalAttribStructure, szDisplayFacultyGrade )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szDisplayFacultyGrade );
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
//:dTakingClassType( VIEW mClass BASED ON LOD mClass,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING ( 20 ) szTakingClassType
public int 
omClass_dTakingClassType( View     mClass,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   String   szTakingClassType = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:szTakingClassType = ""
          {StringBuilder sb_szTakingClassType;
         if ( szTakingClassType == null )
            sb_szTakingClassType = new StringBuilder( 32 );
         else
            sb_szTakingClassType = new StringBuilder( szTakingClassType );
                  ZeidonStringCopy( sb_szTakingClassType, 1, 0, "", 1, 0, 21 );
         szTakingClassType = sb_szTakingClassType.toString( );}
         //:IF mClass.GradeEnrollment.TakingClassType != "P"
         if ( CompareAttributeToString( mClass, "GradeEnrollment", "TakingClassType", "P" ) != 0 )
         { 
            //:GetStringFromAttributeByContext( szTakingClassType, mClass, "GradeEnrollment", "TakingClassType", "", 20 )
            {StringBuilder sb_szTakingClassType;
            if ( szTakingClassType == null )
               sb_szTakingClassType = new StringBuilder( 32 );
            else
               sb_szTakingClassType = new StringBuilder( szTakingClassType );
                         GetStringFromAttributeByContext( sb_szTakingClassType, mClass, "GradeEnrollment", "TakingClassType", "", 20 );
            szTakingClassType = sb_szTakingClassType.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mClass,
         //:                InternalEntityStructure, InternalAttribStructure, szTakingClassType )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szTakingClassType );
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
//:dCurrentCampusPhone( VIEW mClass BASED ON LOD mClass,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   VIEW wXferO REGISTERED AS wXferO
public int 
omClass_dCurrentCampusPhone( View     mClass,
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

   RESULT = GetViewByName( wXferO, "wXferO", mClass, zLEVEL_TASK );

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
         //:SET CURSOR FIRST mClass.DisplayTermOfResidence 
         //:     WHERE mClass.DisplayTermOfResidence.BeginDate <= szCurrentDate
         //:       AND mClass.DisplayTermOfResidence.EndDate   >= szCurrentDate 
         RESULT = mClass.cursor( "DisplayTermOfResidence" ).setFirst().toInt();
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mClass, "DisplayTermOfResidence", "BeginDate", szCurrentDate ) > 0 || CompareAttributeToString( mClass, "DisplayTermOfResidence", "EndDate", szCurrentDate ) < 0 ) )
            { 
               RESULT = mClass.cursor( "DisplayTermOfResidence" ).setNextContinue().toInt();;
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:szCampusPhone = mClass.DisplayOccupantUnit.PhoneNo  
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szCampusPhone;
            if ( szCampusPhone == null )
               sb_szCampusPhone = new StringBuilder( 32 );
            else
               sb_szCampusPhone = new StringBuilder( szCampusPhone );
                         GetVariableFromAttribute( sb_szCampusPhone, mi_lTempInteger_1, 'S', 11, mClass, "DisplayOccupantUnit", "PhoneNo", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szCampusPhone = sb_szCampusPhone.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mClass,
         //:                InternalEntityStructure, InternalAttribStructure, szCampusPhone )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szCampusPhone );
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
//:dAdvisorFullName( VIEW mClass BASED ON LOD mClass,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING ( 100 ) szFullName
public int 
omClass_dAdvisorFullName( View     mClass,
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

         //:GetPersonFullName( szFullName, mClass, "AdvisorPerson" )
         {
          ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mClass );
          {StringBuilder sb_szFullName;
         if ( szFullName == null )
            sb_szFullName = new StringBuilder( 32 );
         else
            sb_szFullName = new StringBuilder( szFullName );
                   m_ZGLOBAL2_Operation.GetPersonFullName( sb_szFullName, mClass, "AdvisorPerson" );
         szFullName = sb_szFullName.toString( );}
          // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
         }
         //:StoreStringInRecord ( mClass,
         //:                      InternalEntityStructure, InternalAttribStructure, szFullName )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szFullName );
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
omClass_dStudentCheckedIn( View     mClass,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{

   //:dStudentCheckedIn( VIEW mClass BASED ON LOD mClass,
   //:                STRING ( 32 ) InternalEntityStructure,
   //:                STRING ( 32 ) InternalAttribStructure,
   //:                SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //://Check if the student has checked in for the semester.
         //:IF  mClass.DisplayStudent.StudentLifeClearedDate != "" 
         if ( CompareAttributeToString( mClass, "DisplayStudent", "StudentLifeClearedDate", "" ) != 0 )
         { 
            //:StoreStringInRecord ( mClass,
            //:                   InternalEntityStructure, InternalAttribStructure, "Y" )
            StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, "Y" );
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


//:DERIVED ATTRIBUTE OPERATION
//:dStudentDegreeMajor( VIEW mClass BASED ON LOD mClass,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   VIEW mClassT  BASED ON LOD mClass
public int 
omClass_dStudentDegreeMajor( View     mClass,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   zVIEW    mClassT = new zVIEW( );
   //:VIEW mClassT2 BASED ON LOD mClass
   zVIEW    mClassT2 = new zVIEW( );
   //:STRING ( 100 ) szDegreeMajors
   String   szDegreeMajors = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Create a comma deliminated list of the unique DegreeMajor values.

         //:CreateViewFromView( mClassT, mClass )
         CreateViewFromView( mClassT, mClass );
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mClass );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "DisplayStudent"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "DisplayStudent", 1, 0, 33 ) == 0 )
         { 
            //:FOR EACH mClassT.DisplayStudentMajorDegreeTrack
            RESULT = mClassT.cursor( "DisplayStudentMajorDegreeTrack" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:CreateViewFromView( mClassT2, mClassT )
               CreateViewFromView( mClassT2, mClassT );
               //:SET CURSOR PREVIOUS mClassT2.DisplayDegreeMajor WITHIN mClassT2.DisplayStudent 
               //:    WHERE mClassT2.DisplayDegreeMajor.Name = mClassT.DisplayDegreeMajor.Name 
               RESULT = mClassT2.cursor( "DisplayDegreeMajor" ).setPrevContinue( ).toInt();
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mClassT2, "DisplayDegreeMajor", "Name", mClassT, "DisplayDegreeMajor", "Name" ) != 0 ) )
                  { 
                     RESULT = mClassT2.cursor( "DisplayDegreeMajor" ).setPrevContinue( ).toInt();
                  } 

               } 

               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:IF szDegreeMajors = ""
                  if ( ZeidonStringCompare( szDegreeMajors, 1, 0, "", 1, 0, 101 ) == 0 )
                  { 
                     //:szDegreeMajors = mClassT.DisplayDegreeMajor.Name 
                     {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                     StringBuilder sb_szDegreeMajors;
                     if ( szDegreeMajors == null )
                        sb_szDegreeMajors = new StringBuilder( 32 );
                     else
                        sb_szDegreeMajors = new StringBuilder( szDegreeMajors );
                                           GetVariableFromAttribute( sb_szDegreeMajors, mi_lTempInteger_0, 'S', 101, mClassT, "DisplayDegreeMajor", "Name", "", 0 );
                     lTempInteger_0 = mi_lTempInteger_0.intValue( );
                     szDegreeMajors = sb_szDegreeMajors.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:szDegreeMajors = szDegreeMajors + ", " + mClassT.DisplayDegreeMajor.Name 
                      {StringBuilder sb_szDegreeMajors;
                     if ( szDegreeMajors == null )
                        sb_szDegreeMajors = new StringBuilder( 32 );
                     else
                        sb_szDegreeMajors = new StringBuilder( szDegreeMajors );
                                          ZeidonStringConcat( sb_szDegreeMajors, 1, 0, ", ", 1, 0, 101 );
                     szDegreeMajors = sb_szDegreeMajors.toString( );}
                     {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                     StringBuilder sb_szTempString_0;
                     if ( szTempString_0 == null )
                        sb_szTempString_0 = new StringBuilder( 32 );
                     else
                        sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                           GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 51, mClassT, "DisplayDegreeMajor", "Name", "", 0 );
                     lTempInteger_1 = mi_lTempInteger_1.intValue( );
                     szTempString_0 = sb_szTempString_0.toString( );}
                      {StringBuilder sb_szDegreeMajors;
                     if ( szDegreeMajors == null )
                        sb_szDegreeMajors = new StringBuilder( 32 );
                     else
                        sb_szDegreeMajors = new StringBuilder( szDegreeMajors );
                                          ZeidonStringConcat( sb_szDegreeMajors, 1, 0, szTempString_0, 1, 0, 101 );
                     szDegreeMajors = sb_szDegreeMajors.toString( );}
                  } 

                  //:END
               } 

               //:END
               //:DropView( mClassT2 )
               DropView( mClassT2 );
               RESULT = mClassT.cursor( "DisplayStudentMajorDegreeTrack" ).setNextContinue().toInt();;
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:FOR EACH mClassT.WaitlistStudentMajorDegreeTrack
            RESULT = mClassT.cursor( "WaitlistStudentMajorDegreeTrack" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:CreateViewFromView( mClassT2, mClassT )
               CreateViewFromView( mClassT2, mClassT );
               //:SET CURSOR PREVIOUS mClassT2.WaitlistDegreeMajor WITHIN mClassT2.WaitlistedStudent 
               //:    WHERE mClassT2.WaitlistDegreeMajor.Name = mClassT.WaitlistDegreeMajor.Name 
               RESULT = mClassT2.cursor( "WaitlistDegreeMajor" ).setPrevContinue( ).toInt();
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mClassT2, "WaitlistDegreeMajor", "Name", mClassT, "WaitlistDegreeMajor", "Name" ) != 0 ) )
                  { 
                     RESULT = mClassT2.cursor( "WaitlistDegreeMajor" ).setPrevContinue( ).toInt();
                  } 

               } 

               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:IF szDegreeMajors = ""
                  if ( ZeidonStringCompare( szDegreeMajors, 1, 0, "", 1, 0, 101 ) == 0 )
                  { 
                     //:szDegreeMajors = mClassT.WaitlistDegreeMajor.Name 
                     {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                     StringBuilder sb_szDegreeMajors;
                     if ( szDegreeMajors == null )
                        sb_szDegreeMajors = new StringBuilder( 32 );
                     else
                        sb_szDegreeMajors = new StringBuilder( szDegreeMajors );
                                           GetVariableFromAttribute( sb_szDegreeMajors, mi_lTempInteger_2, 'S', 101, mClassT, "WaitlistDegreeMajor", "Name", "", 0 );
                     lTempInteger_2 = mi_lTempInteger_2.intValue( );
                     szDegreeMajors = sb_szDegreeMajors.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:szDegreeMajors = szDegreeMajors + ", " + mClassT.WaitlistDegreeMajor.Name 
                      {StringBuilder sb_szDegreeMajors;
                     if ( szDegreeMajors == null )
                        sb_szDegreeMajors = new StringBuilder( 32 );
                     else
                        sb_szDegreeMajors = new StringBuilder( szDegreeMajors );
                                          ZeidonStringConcat( sb_szDegreeMajors, 1, 0, ", ", 1, 0, 101 );
                     szDegreeMajors = sb_szDegreeMajors.toString( );}
                     {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                     StringBuilder sb_szTempString_1;
                     if ( szTempString_1 == null )
                        sb_szTempString_1 = new StringBuilder( 32 );
                     else
                        sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                           GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_3, 'S', 51, mClassT, "WaitlistDegreeMajor", "Name", "", 0 );
                     lTempInteger_3 = mi_lTempInteger_3.intValue( );
                     szTempString_1 = sb_szTempString_1.toString( );}
                      {StringBuilder sb_szDegreeMajors;
                     if ( szDegreeMajors == null )
                        sb_szDegreeMajors = new StringBuilder( 32 );
                     else
                        sb_szDegreeMajors = new StringBuilder( szDegreeMajors );
                                          ZeidonStringConcat( sb_szDegreeMajors, 1, 0, szTempString_1, 1, 0, 101 );
                     szDegreeMajors = sb_szDegreeMajors.toString( );}
                  } 

                  //:END
               } 

               //:END
               //:DropView( mClassT2 )
               DropView( mClassT2 );
               RESULT = mClassT.cursor( "WaitlistStudentMajorDegreeTrack" ).setNextContinue().toInt();;
            } 

            //:END
         } 

         //:END
         //:DropView( mClassT )
         DropView( mClassT );

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mClass,
         //:                InternalEntityStructure, InternalAttribStructure, szDegreeMajors )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szDegreeMajors );
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
//:dMeetingSchedule( VIEW mClass BASED ON LOD mClass,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING ( 254 ) szMeetingSchedule
public int 
omClass_dMeetingSchedule( View     mClass,
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
         //:IF mClass.ClassRoomStandardSchedule EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mClass, "ClassRoomStandardSchedule" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szMeetingSchedule = mClass.ClassRoomStandardSchedule.Title 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szMeetingSchedule;
            if ( szMeetingSchedule == null )
               sb_szMeetingSchedule = new StringBuilder( 32 );
            else
               sb_szMeetingSchedule = new StringBuilder( szMeetingSchedule );
                         GetVariableFromAttribute( sb_szMeetingSchedule, mi_lTempInteger_1, 'S', 255, mClass, "ClassRoomStandardSchedule", "Title", "", 0 );
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
            //:FOR EACH mClass.ClassRoomSession 
            RESULT = mClass.cursor( "ClassRoomSession" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:GetStringFromAttributeByContext( szMeetingDay,
               //:                              mClass, "ClassRoomSession", "MeetingDate", "", 10 )
               {StringBuilder sb_szMeetingDay;
               if ( szMeetingDay == null )
                  sb_szMeetingDay = new StringBuilder( 32 );
               else
                  sb_szMeetingDay = new StringBuilder( szMeetingDay );
                               GetStringFromAttributeByContext( sb_szMeetingDay, mClass, "ClassRoomSession", "MeetingDate", "", 10 );
               szMeetingDay = sb_szMeetingDay.toString( );}
               //://There is either a "MeetingDate" or a "MeetingDay".  If one doesn't exist, then choose the other.
               //:IF  szMeetingDay = ""
               if ( ZeidonStringCompare( szMeetingDay, 1, 0, "", 1, 0, 11 ) == 0 )
               { 
                  //:GetStringFromAttributeByContext( szMeetingDay,
                  //:                              mClass, "ClassRoomSession", "MeetingDay", "", 10 )
                  {StringBuilder sb_szMeetingDay;
                  if ( szMeetingDay == null )
                     sb_szMeetingDay = new StringBuilder( 32 );
                  else
                     sb_szMeetingDay = new StringBuilder( szMeetingDay );
                                     GetStringFromAttributeByContext( sb_szMeetingDay, mClass, "ClassRoomSession", "MeetingDay", "", 10 );
                  szMeetingDay = sb_szMeetingDay.toString( );}
               } 

               //: 
               //:END                                             
               //:GetStringFromAttributeByContext( szMeetingStartTime,
               //:                              mClass, "ClassRoomSession", "StartTime", "", 10 )
               {StringBuilder sb_szMeetingStartTime;
               if ( szMeetingStartTime == null )
                  sb_szMeetingStartTime = new StringBuilder( 32 );
               else
                  sb_szMeetingStartTime = new StringBuilder( szMeetingStartTime );
                               GetStringFromAttributeByContext( sb_szMeetingStartTime, mClass, "ClassRoomSession", "StartTime", "", 10 );
               szMeetingStartTime = sb_szMeetingStartTime.toString( );}
               //:GetStringFromAttributeByContext( szMeetingEndTime,
               //:                              mClass, "ClassRoomSession", "EndTime", "", 10 )
               {StringBuilder sb_szMeetingEndTime;
               if ( szMeetingEndTime == null )
                  sb_szMeetingEndTime = new StringBuilder( 32 );
               else
                  sb_szMeetingEndTime = new StringBuilder( szMeetingEndTime );
                               GetStringFromAttributeByContext( sb_szMeetingEndTime, mClass, "ClassRoomSession", "EndTime", "", 10 );
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
               RESULT = mClass.cursor( "ClassRoomSession" ).setNextContinue().toInt();;
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mClass,
         //:                   InternalEntityStructure, InternalAttribStructure, szMeetingSchedule )
         StoreStringInRecord( mClass, InternalEntityStructure, InternalAttribStructure, szMeetingSchedule );
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
