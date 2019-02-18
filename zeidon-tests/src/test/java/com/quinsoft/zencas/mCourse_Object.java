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
   @author QuinSoft
**/

public class mCourse_Object extends VmlObjectOperations
{
   public mCourse_Object( View view )
   {
      super( view );
   }


//:OBJECT CONSTRAINT OPERATION
//:ObjectConstraints( VIEW mCourse BASED ON LOD mCourse,
//:                   SHORT Event,
//:                   SHORT State )

//:   VIEW lClsLstC BASED ON LOD lClsLstC
public int 
omCourse_ObjectConstraints( View     mCourse,
                            Integer   Event,
                            Integer   State )
{
   zVIEW    lClsLstC = new zVIEW( );
   //:VIEW lCrsReqL BASED ON LOD lCrsReqL
   zVIEW    lCrsReqL = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:// Order Classes.
         //:OrderEntityForView( mCourse, "Class", "SchoolTerm.YearSemester D" )
         OrderEntityForView( mCourse, "Class", "SchoolTerm.YearSemester D" );
         //:FOR EACH mCourse.Course 
         RESULT = SetCursorFirstEntity( mCourse, "Course", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:OrderEntityForView( mCourse, "CourseTopic", "Number A" )
            OrderEntityForView( mCourse, "CourseTopic", "Number A" );
            RESULT = SetCursorNextEntity( mCourse, "Course", "" );
         } 

         //:END

         //:// Set the work attributes for the requisiteBooleanOperators. This is done so that the radio button
         //:// group on the CourseDetail window does not modify the object accidently by its mapping, which can't be
         //:// turned off.
         //:mCourse.Course.wPrerequisiteBooleanOperator = mCourse.Course.PrerequisiteBooleanOperator 
         SetAttributeFromAttribute( mCourse, "Course", "wPrerequisiteBooleanOperator", mCourse, "Course", "PrerequisiteBooleanOperator" );
         //:mCourse.Course.wCorequisiteBooleanOperator  = mCourse.Course.CorequisiteBooleanOperator 
         SetAttributeFromAttribute( mCourse, "Course", "wCorequisiteBooleanOperator", mCourse, "Course", "CorequisiteBooleanOperator" );
         //:SET CURSOR FIRST mCourse.Course 
         RESULT = SetCursorFirstEntity( mCourse, "Course", "" );
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


//:DERIVED ATTRIBUTE OPERATION
//:dBuildingRoom( VIEW mCourse BASED ON LOD mCourse,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   STRING ( 100 ) szBuildingRoom
public int 
omCourse_dBuildingRoom( View     mCourse,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   String   szBuildingRoom = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   String   szTempString_0 = null;
   String   szTempString_1 = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mCourse );
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
            //:szBuildingRoom = mCourse.Building.ShortName + mCourse.Room.Number
            {StringBuilder sb_szBuildingRoom;
            if ( szBuildingRoom == null )
               sb_szBuildingRoom = new StringBuilder( 32 );
            else
               sb_szBuildingRoom = new StringBuilder( szBuildingRoom );
                         GetStringFromAttribute( sb_szBuildingRoom, mCourse, "Building", "ShortName" );
            szBuildingRoom = sb_szBuildingRoom.toString( );}
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, mCourse, "Room", "Number" );
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
            //:szBuildingRoom = mCourse.CourseTopicBuilding.ShortName + mCourse.CourseTopicRoom.Number
            {StringBuilder sb_szBuildingRoom;
            if ( szBuildingRoom == null )
               sb_szBuildingRoom = new StringBuilder( 32 );
            else
               sb_szBuildingRoom = new StringBuilder( szBuildingRoom );
                         GetStringFromAttribute( sb_szBuildingRoom, mCourse, "CourseTopicBuilding", "ShortName" );
            szBuildingRoom = sb_szBuildingRoom.toString( );}
            {StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetStringFromAttribute( sb_szTempString_1, mCourse, "CourseTopicRoom", "Number" );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szBuildingRoom;
            if ( szBuildingRoom == null )
               sb_szBuildingRoom = new StringBuilder( 32 );
            else
               sb_szBuildingRoom = new StringBuilder( szBuildingRoom );
                        ZeidonStringConcat( sb_szBuildingRoom, 1, 0, szTempString_1, 1, 0, 101 );
            szBuildingRoom = sb_szBuildingRoom.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mCourse,
         //:                      InternalEntityStructure, InternalAttribStructure, szBuildingRoom )
         StoreStringInRecord( mCourse, InternalEntityStructure, InternalAttribStructure, szBuildingRoom );
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
//:dClassTopicNumberTitle( VIEW mCourse BASED ON LOD mCourse,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   STRING ( 30 ) szTopicTitle
public int 
omCourse_dClassTopicNumberTitle( View     mCourse,
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
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mCourse );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:GetStringFromAttribute( szTopicNumber, mCourse, szEntityName, "Number" )
         {StringBuilder sb_szTopicNumber;
         if ( szTopicNumber == null )
            sb_szTopicNumber = new StringBuilder( 32 );
         else
            sb_szTopicNumber = new StringBuilder( szTopicNumber );
                   GetStringFromAttribute( sb_szTopicNumber, mCourse, szEntityName, "Number" );
         szTopicNumber = sb_szTopicNumber.toString( );}
         //:GetStringFromAttribute( szTopicTitle, mCourse, szEntityName, "Title" )
         {StringBuilder sb_szTopicTitle;
         if ( szTopicTitle == null )
            sb_szTopicTitle = new StringBuilder( 32 );
         else
            sb_szTopicTitle = new StringBuilder( szTopicTitle );
                   GetStringFromAttribute( sb_szTopicTitle, mCourse, szEntityName, "Title" );
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

         //:StoreStringInRecord ( mCourse,
         //:                      InternalEntityStructure, InternalAttribStructure, szTopicNumberTitle )
         StoreStringInRecord( mCourse, InternalEntityStructure, InternalAttribStructure, szTopicNumberTitle );
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
//:dDisplayCourseOrGroup( VIEW mCourse BASED ON LOD mCourse,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   STRING ( 100 ) szDisplayValue
public int 
omCourse_dDisplayCourseOrGroup( View     mCourse,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   String   szDisplayValue = null;
   //:STRING ( 3 )   szBooleanOperator
   String   szBooleanOperator = null;
   //:SHORT          nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mCourse.CoursePrerequisiteGroup.PrerequisiteBooleanOperator = ""
         if ( CompareAttributeToString( mCourse, "CoursePrerequisiteGroup", "PrerequisiteBooleanOperator", "" ) == 0 )
         { 
            //:szDisplayValue = mCourse.PrerequisiteCourse.Number 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                         GetVariableFromAttribute( sb_szDisplayValue, mi_lTempInteger_0, 'S', 101, mCourse, "PrerequisiteCourse", "Number", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szDisplayValue = sb_szDisplayValue.toString( );}
            //:ELSE
         } 
         else
         { 
            //:SetCursorFirstEntity( mCourse, "PrerequisiteSubCourse", "" )
            SetCursorFirstEntity( mCourse, "PrerequisiteSubCourse", "" );
            //:szDisplayValue = "(" + mCourse.PrerequisiteSubCourse.Number 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 11, mCourse, "PrerequisiteSubCourse", "Number", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringCopy( sb_szDisplayValue, 1, 0, "(", 1, 0, 101 );
            szDisplayValue = sb_szDisplayValue.toString( );}
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringConcat( sb_szDisplayValue, 1, 0, szTempString_0, 1, 0, 101 );
            szDisplayValue = sb_szDisplayValue.toString( );}
            //:nRC = SetCursorNextEntity( mCourse, "PrerequisiteSubCourse", "" )
            nRC = SetCursorNextEntity( mCourse, "PrerequisiteSubCourse", "" );
            //:LOOP WHILE nRC >= zCURSOR_SET
            while ( nRC >= zCURSOR_SET )
            { 
               //:IF mCourse.CoursePrerequisiteGroup.PrerequisiteBooleanOperator = "O"
               if ( CompareAttributeToString( mCourse, "CoursePrerequisiteGroup", "PrerequisiteBooleanOperator", "O" ) == 0 )
               { 
                  //:szDisplayValue = szDisplayValue + " OR " + mCourse.PrerequisiteSubCourse.Number
                   {StringBuilder sb_szDisplayValue;
                  if ( szDisplayValue == null )
                     sb_szDisplayValue = new StringBuilder( 32 );
                  else
                     sb_szDisplayValue = new StringBuilder( szDisplayValue );
                                    ZeidonStringConcat( sb_szDisplayValue, 1, 0, " OR ", 1, 0, 101 );
                  szDisplayValue = sb_szDisplayValue.toString( );}
                  {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                  StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 11, mCourse, "PrerequisiteSubCourse", "Number", "", 0 );
                  lTempInteger_2 = mi_lTempInteger_2.intValue( );
                  szTempString_1 = sb_szTempString_1.toString( );}
                   {StringBuilder sb_szDisplayValue;
                  if ( szDisplayValue == null )
                     sb_szDisplayValue = new StringBuilder( 32 );
                  else
                     sb_szDisplayValue = new StringBuilder( szDisplayValue );
                                    ZeidonStringConcat( sb_szDisplayValue, 1, 0, szTempString_1, 1, 0, 101 );
                  szDisplayValue = sb_szDisplayValue.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szDisplayValue = szDisplayValue + " AND " + mCourse.PrerequisiteSubCourse.Number
                   {StringBuilder sb_szDisplayValue;
                  if ( szDisplayValue == null )
                     sb_szDisplayValue = new StringBuilder( 32 );
                  else
                     sb_szDisplayValue = new StringBuilder( szDisplayValue );
                                    ZeidonStringConcat( sb_szDisplayValue, 1, 0, " AND ", 1, 0, 101 );
                  szDisplayValue = sb_szDisplayValue.toString( );}
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                     GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_3, 'S', 11, mCourse, "PrerequisiteSubCourse", "Number", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szTempString_2 = sb_szTempString_2.toString( );}
                   {StringBuilder sb_szDisplayValue;
                  if ( szDisplayValue == null )
                     sb_szDisplayValue = new StringBuilder( 32 );
                  else
                     sb_szDisplayValue = new StringBuilder( szDisplayValue );
                                    ZeidonStringConcat( sb_szDisplayValue, 1, 0, szTempString_2, 1, 0, 101 );
                  szDisplayValue = sb_szDisplayValue.toString( );}
               } 

               //:END
               //:nRC = SetCursorNextEntity( mCourse, "PrerequisiteSubCourse", "" )
               nRC = SetCursorNextEntity( mCourse, "PrerequisiteSubCourse", "" );
            } 

            //:END
            //:szDisplayValue = szDisplayValue + ")"
             {StringBuilder sb_szDisplayValue;
            if ( szDisplayValue == null )
               sb_szDisplayValue = new StringBuilder( 32 );
            else
               sb_szDisplayValue = new StringBuilder( szDisplayValue );
                        ZeidonStringConcat( sb_szDisplayValue, 1, 0, ")", 1, 0, 101 );
            szDisplayValue = sb_szDisplayValue.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mCourse,
         //:                      InternalEntityStructure, InternalAttribStructure, szDisplayValue )
         StoreStringInRecord( mCourse, InternalEntityStructure, InternalAttribStructure, szDisplayValue );
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
//:dCollegeTermCohort( VIEW mCourse BASED ON LOD mCourse,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   STRING ( 20 ) szCollegeTerm
public int 
omCourse_dCollegeTermCohort( View     mCourse,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szCollegeTerm = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// The CollegeTerm or Cohort.
         //:IF mCourse.SchoolTerm EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mCourse, "SchoolTerm" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szCollegeTerm = mCourse.SchoolTerm.YearSemester 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szCollegeTerm;
            if ( szCollegeTerm == null )
               sb_szCollegeTerm = new StringBuilder( 32 );
            else
               sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                         GetVariableFromAttribute( sb_szCollegeTerm, mi_lTempInteger_1, 'S', 21, mCourse, "SchoolTerm", "YearSemester", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szCollegeTerm = sb_szCollegeTerm.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mCourse.Cohort EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mCourse, "Cohort" );
            if ( lTempInteger_2 == 0 )
            { 
               //:szCollegeTerm = mCourse.Cohort.Name 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szCollegeTerm;
               if ( szCollegeTerm == null )
                  sb_szCollegeTerm = new StringBuilder( 32 );
               else
                  sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                               GetVariableFromAttribute( sb_szCollegeTerm, mi_lTempInteger_3, 'S', 21, mCourse, "Cohort", "Name", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szCollegeTerm = sb_szCollegeTerm.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szCollegeTerm = ""
                {StringBuilder sb_szCollegeTerm;
               if ( szCollegeTerm == null )
                  sb_szCollegeTerm = new StringBuilder( 32 );
               else
                  sb_szCollegeTerm = new StringBuilder( szCollegeTerm );
                              ZeidonStringCopy( sb_szCollegeTerm, 1, 0, "", 1, 0, 21 );
               szCollegeTerm = sb_szCollegeTerm.toString( );}
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mCourse,
         //:                InternalEntityStructure, InternalAttribStructure, szCollegeTerm )
         StoreStringInRecord( mCourse, InternalEntityStructure, InternalAttribStructure, szCollegeTerm );
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
