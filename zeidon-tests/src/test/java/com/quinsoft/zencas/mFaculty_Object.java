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

    Copyright 2009-2014 QuinSoft
**/

package com.quinsoft.zencas;

import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;

/**
 * @author QuinSoft
 *
 */

public class mFaculty_Object extends VmlObjectOperations
{
   public mFaculty_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
public int 
omFaculty_dFullNameFML( View     mFaculty,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{

   //:dFullNameFML( VIEW mFaculty BASED ON LOD mFaculty,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_FirstMiddleLast( mFaculty, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
//    	 ZGlobal1_Operation oGlobal = new ZGlobal1_Operation( mFaculty );
//    	 oGlobal.PersonName_FirstMiddleLast( mFaculty, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
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
//:dFacultyFullName( VIEW mFaculty BASED ON LOD mFaculty,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING ( 100 ) szFullName
public int 
omFaculty_dFacultyFullName( View     mFaculty,
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

         //:szFullName = mFaculty.Person.dFullNameFML 
         szFullName = GetVariableFromAttribute( szFullName, 0, 'S', 101, mFaculty, "Person", "dFullNameFML", "", 0 );

         //:StoreStringInRecord ( mFaculty,
         //:                   InternalEntityStructure, InternalAttribStructure, szFullName )
         StoreStringInRecord( mFaculty, InternalEntityStructure, InternalAttribStructure, szFullName );
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
omFaculty_dFullNameLFM( View     mFaculty,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{

   //:dFullNameLFM( VIEW mFaculty BASED ON LOD mFaculty,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_LastFirstMiddle( mFaculty, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
//     	 ZGlobal1_Operation oGlobal = new ZGlobal1_Operation( mFaculty );
//    	 oGlobal.PersonName_LastFirstMiddle( mFaculty, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
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
omFaculty_ObjectConstraint( View     mFaculty,
                            Integer  Event,
                            Integer  State )
{
   int RESULT;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;

   //:ObjectConstraint( VIEW mFaculty BASED ON LOD mFaculty,
   //:               SHORT Event,
   //:               SHORT State )

   //:CASE Event
   switch( Event )
   {
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :
          
          if ( State == 0 )
              return 0;

         //:// In case this is a list, loop for each Faculty entry.
         //:FOR EACH mFaculty.Faculty  
         RESULT = mFaculty.cursor( "Faculty" ).setFirst().toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 

            //:// Build derived paths ChairedDivisionFaculty and ChairedDepartmentFaculty.
            //:// If the root faculty person is a department or division head, this would list all the 
            //:// faculty members inside the department/division.           
            //:FOR  EACH mFaculty.ChairedDivisionFaculty WITHIN mFaculty.Faculty  
            RESULT = mFaculty.cursor( "ChairedDivisionFaculty" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //://Only add the faculty if they are active
               //:IF  mFaculty.ChairedDivisionEmployee  EXISTS 
               lTempInteger_0 = CheckExistenceOfEntity( mFaculty, "ChairedDivisionEmployee" );
               if ( lTempInteger_0 == 0 )
               { 
                  //:IF  mFaculty.ChairedDivisionEmployee.Status  = "A"  
                  if ( CompareAttributeToString( mFaculty, "ChairedDivisionEmployee", "Status", "A" ) == 0 )
                  { 
                     //:SET CURSOR FIRST mFaculty.AssignableFaculty  WHERE 
                     //:              mFaculty.AssignableFaculty.ID = mFaculty.ChairedDivisionFaculty.ID 
                     lTempInteger_1 = GetIntegerFromAttribute( lTempInteger_1, mFaculty, "ChairedDivisionFaculty", "ID" );
                     RESULT = mFaculty.cursor( "AssignableFaculty" ).setFirst( "ID", lTempInteger_1 ).toInt();
                     //:IF  RESULT < zCURSOR_SET
                     if ( RESULT < zCURSOR_SET )
                     { 
                        //:INCLUDE  mFaculty.AssignableFaculty FROM  mFaculty.ChairedDivisionFaculty 
                        RESULT = IncludeSubobjectFromSubobject( mFaculty, "AssignableFaculty", mFaculty, "ChairedDivisionFaculty", zPOS_AFTER );
                     } 

                     //:END 
                  } 

                  //:END
               } 

               RESULT = mFaculty.cursor( "ChairedDivisionFaculty" ).setNext().toInt();
               //:END
            } 

            //:END

            //:FOR  EACH mFaculty.ChairedDepartmentFaculty WITHIN mFaculty.Faculty 
            RESULT = mFaculty.cursor( "ChairedDepartmentFaculty" ).setFirst().toInt();
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //://Only add the faculty if they are active
               //:IF  mFaculty.ChairedDepartmentEmployee  EXISTS 
               lTempInteger_2 = CheckExistenceOfEntity( mFaculty, "ChairedDepartmentEmployee" );
               if ( lTempInteger_2 == 0 )
               { 
                  //:IF  mFaculty.ChairedDepartmentEmployee.Status = "A" 
                  if ( CompareAttributeToString( mFaculty, "ChairedDepartmentEmployee", "Status", "A" ) == 0 )
                  { 
                     //:SET CURSOR FIRST mFaculty.AssignableFaculty  WHERE 
                     //:              mFaculty.AssignableFaculty.ID = mFaculty.ChairedDepartmentFaculty.ID 
                     lTempInteger_3 = GetIntegerFromAttribute( lTempInteger_3, mFaculty, "ChairedDepartmentFaculty", "ID" );
                     RESULT = mFaculty.cursor( "AssignableFaculty" ).setFirst( "ID", lTempInteger_3 ).toInt();
                     //:IF  RESULT < zCURSOR_SET
                     if ( RESULT < zCURSOR_SET )
                     { 
                        //:INCLUDE  mFaculty.AssignableFaculty FROM  mFaculty.ChairedDepartmentFaculty 
                        RESULT = IncludeSubobjectFromSubobject( mFaculty, "AssignableFaculty", mFaculty, "ChairedDepartmentFaculty", zPOS_AFTER );
                     } 

                     //:END 
                  } 

                  //:END         
               } 

               RESULT = mFaculty.cursor( "ChairedDepartmentFaculty" ).setNext().toInt();
               //:END
            } 

            //:END

            //:// This path is a duplicate list of AssignableFaculty/AssignableFacultyPerson for listing purposes only.         
            //:IF  mFaculty.AssignableFaculty EXISTS 
            lTempInteger_4 = CheckExistenceOfEntity( mFaculty, "AssignableFaculty" );
            if ( lTempInteger_4 == 0 )
            { 
               //:FOR EACH mFaculty.AssignableFaculty 
               RESULT = mFaculty.cursor( "AssignableFaculty" ).setFirst().toInt();
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:INCLUDE  mFaculty.ListAssignableFaculty FROM  mFaculty.AssignableFaculty 
                  RESULT = IncludeSubobjectFromSubobject( mFaculty, "ListAssignableFaculty", mFaculty, "AssignableFaculty", zPOS_AFTER );
                  RESULT = mFaculty.cursor( "AssignableFaculty" ).setNext().toInt();
               } 

               //:END 
               //:OrderEntityForView( mFaculty, "ListAssignableFaculty", "ListAssignablePerson.dFullNameLFM A" )
               OrderEntityForView( mFaculty, "ListAssignableFaculty", "ListAssignablePerson.dFullNameLFM A" );
            } 


            //:END

            //://Create an entry in CanSeePersonData, for the current faculty member.  Even
            //://if they can not see other people's data they can see their own.
            //://Don't add if this person is not a faculty member (for cases of administrative assistants)
            //://since they shouldn't have a faculty ID.
            //:// CanSeePersonData entity is to list all the people in the ViewableFacultyPerson path.  The only reason that we have created this 
            //:// instead of using the ViewableFacultyPerson entity is because we want to add "--All in List--" as an option in the 
            //:// dropdown list (so user can see data for everyone in the list).         
            //:IF mFaculty.Faculty.ID > 0  
            if ( CompareAttributeToInteger( mFaculty, "Faculty", "ID", 0 ) > 0 )
            { 
               //:INCLUDE  mFaculty.CanSeePersonData   FROM  mFaculty.Person
               RESULT = IncludeSubobjectFromSubobject( mFaculty, "CanSeePersonData", mFaculty, "Person", zPOS_AFTER );
               //:mFaculty.CanSeePersonData.wFacultyID = mFaculty.Faculty.ID 
               SetAttributeFromAttribute( mFaculty, "CanSeePersonData", "wFacultyID", mFaculty, "Faculty", "ID" );
            } 

            //:END

            //:// ViewableFaculty are the faculty that have been assigned to this person.  They can see
            //:// classes and advisees for these faculty members.
            //:IF mFaculty.ViewableFaculty EXISTS 
            lTempInteger_5 = CheckExistenceOfEntity( mFaculty, "ViewableFaculty" );
            if ( lTempInteger_5 == 0 )
            { 
               //:FOR  EACH mFaculty.ViewableFaculty 
               RESULT = mFaculty.cursor( "ViewableFaculty" ).setFirst().toInt();
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:INCLUDE  mFaculty.CanSeePersonData   FROM  mFaculty.ViewableFacultyPerson 
                  RESULT = IncludeSubobjectFromSubobject( mFaculty, "CanSeePersonData", mFaculty, "ViewableFacultyPerson", zPOS_AFTER );
                  //:mFaculty.CanSeePersonData.wFacultyID = mFaculty.ViewableFaculty.ID 
                  SetAttributeFromAttribute( mFaculty, "CanSeePersonData", "wFacultyID", mFaculty, "ViewableFaculty", "ID" );
                  RESULT = mFaculty.cursor( "ViewableFaculty" ).setNext().toInt();
               } 

               //:END

               //://Create an entry that will allow the user to 
               //://show advisees/classes for all faculty that they can
               //://see data for.
               //:CREATE ENTITY  mFaculty.CanSeePersonData FIRST
               RESULT = CreateEntity( mFaculty, "CanSeePersonData", zPOS_FIRST );
               //:mFaculty.CanSeePersonData.ID = -5
               SetAttributeFromInteger( mFaculty, "CanSeePersonData", "ID", -5 );
               //:mFaculty.CanSeePersonData.wFacultyID = -5
               SetAttributeFromInteger( mFaculty, "CanSeePersonData", "wFacultyID", -5 );
               //:mFaculty.CanSeePersonData.LastName = "-- All in List --" 
               SetAttributeFromString( mFaculty, "CanSeePersonData", "LastName", "-- All in List --" );
               //:SET CURSOR FIRST  mFaculty.CanSeePersonData
               RESULT = mFaculty.cursor( "CanSeePersonData" ).setFirst().toInt();
            } 

            RESULT = mFaculty.cursor( "Faculty" ).setNext().toInt();
            //:END
         } 


         //:END

         //:// Set cursor on first Faculty in list.
         //:SET CURSOR FIRST mFaculty.Faculty  
         RESULT = mFaculty.cursor( "Faculty" ).setFirst().toInt();

         //://The user can always see data for him/herself.  Position on this when first coming to the list.
         //:SET CURSOR FIRST mFaculty.CanSeePersonData WHERE mFaculty.CanSeePersonData.ID = mFaculty.Person.ID 
         lTempInteger_6 = GetIntegerFromAttribute( lTempInteger_6, mFaculty, "Person", "ID" );
         RESULT = mFaculty.cursor( "CanSeePersonData" ).setFirst( "ID", lTempInteger_6 ).toInt();
         break ;
      //:   


      //:  /* end zOCE_ACTIVATE */
      //:OF   zOCE_ACTIVATE_EMPTY:
      case zOCE_ACTIVATE_EMPTY :
         break ;

      //:  /* end zOCE_ACTIVATE_EMPTY */
      //:OF   zOCE_COMMIT:
      case zOCE_COMMIT :

         //:// Include or remove the ChairedDivision entity based on the Faculty.DivisionChairFlag value.
         //:/*IF mFaculty.Faculty.DivisionChairFlag = "Y"
         //:// A Department must exist if the Faculty member is a Chair of a Division.
         //:IF mFaculty.Department DOES NOT EXIST
         //:   MessageSend( mFaculty, "", "Save Faculty", 
         //:                "A Department must be selected if the Faculty member is a Division Chair.",
         //:                zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         //:   RETURN -1
         //:END
         //:IF mFaculty.Division DOES NOT EXIST
         //:   MessageSend( mFaculty, "", "Save Faculty", 
         //:                "The Department selected does not have a Division specified, which is required if the Faculty member is a Division Chair.",
         //:                zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         //:   RETURN -1
         //:END

         //:// Make sure that the entity is included.
         //:IF mFaculty.ChairedDivision DOES NOT EXIST
         //:   INCLUDE mFaculty.ChairedDivision FROM mFaculty.Division 
         //:END
         //:ELSE
         //:// Make sure that the entity in NOT included.
         //:IF mFaculty.ChairedDivision EXISTS
         //:   EXCLUDE mFaculty.ChairedDivision  
         //:END
         //:END*/

         //:// Set Faculty Department and Division flags based on existence/nonexistence of ChairedDepartment and
         //:// ChairedDivision entities.
         //:IF mFaculty.Faculty EXISTS
         lTempInteger_7 = CheckExistenceOfEntity( mFaculty, "Faculty" );
         if ( lTempInteger_7 == 0 )
         { 
            //:SET CURSOR FIRST mFaculty.ChairedDepartment 
            RESULT = mFaculty.cursor( "ChairedDepartment" ).setFirst().toInt();
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:mFaculty.Faculty.DepartmentHeadFlag = "Y"
               SetAttributeFromString( mFaculty, "Faculty", "DepartmentHeadFlag", "Y" );
               //:ELSE
            } 
            else
            { 
               //:mFaculty.Faculty.DepartmentHeadFlag = ""
               SetAttributeFromString( mFaculty, "Faculty", "DepartmentHeadFlag", "" );
            } 

            //:END 
            //:SET CURSOR FIRST mFaculty.ChairedDivision 
            RESULT = mFaculty.cursor( "ChairedDivision" ).setFirst().toInt();
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:mFaculty.Faculty.DivisionChairFlag = "Y"
               SetAttributeFromString( mFaculty, "Faculty", "DivisionChairFlag", "Y" );
               //:ELSE
            } 
            else
            { 
               //:mFaculty.Faculty.DivisionChairFlag = ""
               SetAttributeFromString( mFaculty, "Faculty", "DivisionChairFlag", "" );
            } 

            //:END
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



}
