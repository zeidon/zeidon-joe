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

public class mUser_Object extends VmlObjectOperations
{
   public mUser_Object( View view )
   {
      super( view );
   }


//:TRANSFORMATION OPERATION
public int 
omUser_ActivateUser( zVIEW    mUser,
                     View     ViewToSubtask,
                     int      UserID )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   //:ActivateUser( VIEW mUser BASED ON LOD mUser,
   //:           VIEW ViewToSubtask,
   //:           INTEGER UserID )

   //:ACTIVATE  mUser WHERE mUser.User.ID = UserID
   //:   RESTRICTING mUser.Activity TO mUser.Activity.CompletedDate != ""
   omUser_fnLocalBuildQual_0( ViewToSubtask, vTempViewVar_0, UserID );
   RESULT = ActivateObjectInstance( mUser, "mUser", ViewToSubtask, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF mUser.PreferedAdministrativeDivision EXISTS 
   lTempInteger_0 = CheckExistenceOfEntity( mUser, "PreferedAdministrativeDivision" );
   if ( lTempInteger_0 == 0 )
   { 
      //:INCLUDE mUser.CurrentAdministrativeDivision 
      //:  FROM mUser.PreferedAdministrativeDivision 
      RESULT = IncludeSubobjectFromSubobject( mUser, "CurrentAdministrativeDivision", mUser, "PreferedAdministrativeDivision", zPOS_AFTER );
      //:ELSE 
   } 
   else
   { 
      //:IF  mUser.DefaultAdministrativeDivision EXISTS 
      lTempInteger_1 = CheckExistenceOfEntity( mUser, "DefaultAdministrativeDivision" );
      if ( lTempInteger_1 == 0 )
      { 
         //:INCLUDE mUser.CurrentAdministrativeDivision 
         //:   FROM mUser.DefaultAdministrativeDivision 
         RESULT = IncludeSubobjectFromSubobject( mUser, "CurrentAdministrativeDivision", mUser, "DefaultAdministrativeDivision", zPOS_AFTER );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


private int 
omUser_fnLocalBuildQual_0( View     vSubtask,
                           zVIEW    vQualObject,
                           int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "User" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Activity" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Activity" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "CompletedDate" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "!=" );
   return( 0 );
} 


private int 
omUser_fnLocalBuildQual_1( View     vSubtask,
                           zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "User" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Status" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "A" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "UserGroup" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
   return( 0 );
} 


//:TRANSFORMATION OPERATION
public int 
omUser_ActivateUserLST( zVIEW    mUser,
                        View     ViewToSubtask )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateUserLST( VIEW mUser BASED ON LOD mUser,
   //:              VIEW ViewToSubtask )

   //:ACTIVATE mUser RootOnlyMultiple 
   //:         WHERE ( mUser.User.Status = "A" )
   //:           AND mUser.UserGroup EXISTS
   omUser_fnLocalBuildQual_1( ViewToSubtask, vTempViewVar_0 );
   RESULT = ActivateObjectInstance( mUser, "mUser", ViewToSubtask, vTempViewVar_0, zACTIVATE_ROOTONLY_MULTIPLE );
   DropView( vTempViewVar_0 );

   //://NAME VIEW  mUser  "mUserLST"
   //:SetNameForView( mUser, "mUserLST", ViewToSubtask, zLEVEL_APPLICATION )
   SetNameForView( mUser, "mUserLST", ViewToSubtask, zLEVEL_APPLICATION );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dFullPersonNameLFM( VIEW mUser BASED ON LOD mUser,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )
//:   STRING (  254  ) szString
public int 
omUser_dFullPersonNameLFM( View     mUser,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   String   szString = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:// Note that you can't use the reusable global operation because the derived attribute is
         //:// on User instead of Person.
         //:IF mUser.Person EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mUser, "Person" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mUser.Person.LastName + ", " +
            //:           mUser.Person.FirstName + " " +
            //:           mUser.Person.MiddleName
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttribute( sb_szString, mUser, "Person", "LastName" );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, ", ", 1, 0, 255 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 51, mUser, "Person", "FirstName", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 255 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 51, mUser, "Person", "MiddleName", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_1, 1, 0, 255 );
            szString = sb_szString.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szString = mUser.User.UserName
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_3, 'S', 255, mUser, "User", "UserName", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szString = sb_szString.toString( );}
         } 

         //:END
         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mUser,
         //:                      InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mUser, InternalEntityStructure, InternalAttribStructure, szString );
         break ;

      //:  /* end zDERIVED_GET */
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
//:dEmailAddress( VIEW mUser BASED ON LOD mUser,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )
//:   STRING (  254  ) szEmailAddress
public int 
omUser_dEmailAddress( View     mUser,
                      String InternalEntityStructure,
                      String InternalAttribStructure,
                      Integer   GetOrSetFlag )
{
   String   szEmailAddress = null;
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

         //:// The derived Email Address is set from the Employee, Student or Person Email address as
         //:// they exist in that order.
         //:IF mUser.Employee EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mUser, "Employee" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szEmailAddress = mUser.Employee.eMailAddress 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szEmailAddress;
            if ( szEmailAddress == null )
               sb_szEmailAddress = new StringBuilder( 32 );
            else
               sb_szEmailAddress = new StringBuilder( szEmailAddress );
                         GetVariableFromAttribute( sb_szEmailAddress, mi_lTempInteger_1, 'S', 255, mUser, "Employee", "eMailAddress", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szEmailAddress = sb_szEmailAddress.toString( );}
         } 

         //:END
         //:IF szEmailAddress = ""
         if ( ZeidonStringCompare( szEmailAddress, 1, 0, "", 1, 0, 255 ) == 0 )
         { 
            //:IF mUser.Student EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mUser, "Student" );
            if ( lTempInteger_2 == 0 )
            { 
               //:szEmailAddress = mUser.Student.eMailAddress
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szEmailAddress;
               if ( szEmailAddress == null )
                  sb_szEmailAddress = new StringBuilder( 32 );
               else
                  sb_szEmailAddress = new StringBuilder( szEmailAddress );
                               GetVariableFromAttribute( sb_szEmailAddress, mi_lTempInteger_3, 'S', 255, mUser, "Student", "eMailAddress", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szEmailAddress = sb_szEmailAddress.toString( );}
            } 

            //:END
         } 

         //:END
         //:IF szEmailAddress = ""
         if ( ZeidonStringCompare( szEmailAddress, 1, 0, "", 1, 0, 255 ) == 0 )
         { 
            //:szEmailAddress = mUser.Person.eMailAddress
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szEmailAddress;
            if ( szEmailAddress == null )
               sb_szEmailAddress = new StringBuilder( 32 );
            else
               sb_szEmailAddress = new StringBuilder( szEmailAddress );
                         GetVariableFromAttribute( sb_szEmailAddress, mi_lTempInteger_4, 'S', 255, mUser, "Person", "eMailAddress", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szEmailAddress = sb_szEmailAddress.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mUser,
         //:                      InternalEntityStructure, InternalAttribStructure, szEmailAddress )
         StoreStringInRecord( mUser, InternalEntityStructure, InternalAttribStructure, szEmailAddress );
         break ;

      //:  /* end zDERIVED_GET */
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
//:dPersonRoles( VIEW mUser BASED ON LOD mUser,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )

//:   STRING ( 50 ) szType
public int 
omUser_dPersonRoles( View     mUser,
                     String InternalEntityStructure,
                     String InternalAttribStructure,
                     Integer   GetOrSetFlag )
{
   String   szType = null;
   //:STRING ( 49 ) szTypeN
   String   szTypeN = null;
   //:STRING ( 20 ) szAdminDiv
   String   szAdminDiv = null;
   //:SHORT         StringLength
   int      StringLength = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;



   //:szType = ""
    {StringBuilder sb_szType;
   if ( szType == null )
      sb_szType = new StringBuilder( 32 );
   else
      sb_szType = new StringBuilder( szType );
      ZeidonStringCopy( sb_szType, 1, 0, "", 1, 0, 51 );
   szType = sb_szType.toString( );}
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:szType = ""
          {StringBuilder sb_szType;
         if ( szType == null )
            sb_szType = new StringBuilder( 32 );
         else
            sb_szType = new StringBuilder( szType );
                  ZeidonStringCopy( sb_szType, 1, 0, "", 1, 0, 51 );
         szType = sb_szType.toString( );}
         //:IF mUser.Faculty EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mUser, "Faculty" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szType = szType + "Instructor, "
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, "Instructor, ", 1, 0, 51 );
            szType = sb_szType.toString( );}
         } 

         //:END
         //:IF mUser.Staff EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mUser, "Staff" );
         if ( lTempInteger_1 == 0 )
         { 
            //:szType = szType + "Staff, "
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, "Staff, ", 1, 0, 51 );
            szType = sb_szType.toString( );}
         } 

         //:END
         //:IF mUser.Donor EXISTS
         lTempInteger_2 = CheckExistenceOfEntity( mUser, "Donor" );
         if ( lTempInteger_2 == 0 )
         { 
            //:szType = szType + "Donor, "
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, "Donor, ", 1, 0, 51 );
            szType = sb_szType.toString( );}
         } 

         //:END
         //:IF mUser.Student EXISTS
         lTempInteger_3 = CheckExistenceOfEntity( mUser, "Student" );
         if ( lTempInteger_3 == 0 )
         { 
            //:szType = szType + mUser.StudentAdministrativeDivision.NamePrefix + " Student, "
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_4, 'S', 6, mUser, "StudentAdministrativeDivision", "NamePrefix", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, szTempString_0, 1, 0, 51 );
            szType = sb_szType.toString( );}
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, " Student, ", 1, 0, 51 );
            szType = sb_szType.toString( );}
         } 

         //:END
         //:IF mUser.Alumni EXISTS
         lTempInteger_5 = CheckExistenceOfEntity( mUser, "Alumni" );
         if ( lTempInteger_5 == 0 )
         { 
            //:szType = szType + "Alumni, "
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, "Alumni, ", 1, 0, 51 );
            szType = sb_szType.toString( );}
         } 

         //:END
         //:IF mUser.Employee EXISTS
         lTempInteger_6 = CheckExistenceOfEntity( mUser, "Employee" );
         if ( lTempInteger_6 == 0 )
         { 
            //:szType = szType + "Employee, "
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, "Employee, ", 1, 0, 51 );
            szType = sb_szType.toString( );}
         } 

         //:END
         //:StringLength = zGetStringLen( szType )
         StringLength = zGetStringLen( szType );
         //:StringLength = StringLength - 2
         StringLength = StringLength - 2;
         //:szTypeN = szType[1:StringLength]
          {StringBuilder sb_szTypeN;
         if ( szTypeN == null )
            sb_szTypeN = new StringBuilder( 32 );
         else
            sb_szTypeN = new StringBuilder( szTypeN );
                  ZeidonStringCopy( sb_szTypeN, 1, 0, szType, 1, StringLength, 50 );
         szTypeN = sb_szTypeN.toString( );}
         //:StoreStringInRecord ( mUser,
         //:                      InternalEntityStructure, InternalAttribStructure, szTypeN )
         StoreStringInRecord( mUser, InternalEntityStructure, InternalAttribStructure, szTypeN );
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
public int 
omUser_AccessAllowed( View     mUser,
                      String   szAdminDiv,
                      String   szFunctionalArea )
{
   int      RESULT = 0;

   //:AccessAllowed( VIEW mUser BASED ON LOD mUser,
   //:            STRING ( 32 ) szAdminDiv,
   //:            STRING ( 32 ) szFunctionalArea )

   //:// Always allow admin!
   //:IF  mUser.User.ID = 1
   if ( CompareAttributeToInteger( mUser, "User", "ID", 1 ) == 0 )
   { 
      //:RETURN 1 
      if(8==8)return( 1 );
   } 

   //:END

   //:// Each user may be assigned to multiple groups.  The user should get the maximum
   //:// access privileges granted within any assigned group for the current Administrative Division.
   //:// So if the user has been granted access privileges to a security area from any group for the
   //:// current Administrative Division, return 1 to indicate that the user has access privileges.
   //:// Note that FunctionalArea can be set to values such as:  "Admissions" or
   //:// "Development" or "Alumni" or "Registrar" or ...
   //:// Also note that we always require a match on current Administrative Division, even for Functional
   //:// Areas like "Constituency" that don't normally care about Administrative Division. This is necessary
   //:// to be consistent across the board.

   //:SET CURSOR FIRST mUser.SecuritySection WITHIN mUser.User
   //:      WHERE mUser.SecurityAdministrativeDivision.ID = mUser.CurrentAdministrativeDivision.ID 
   //:        AND mUser.SecurityArea.FunctionalArea       = szFunctionalArea 
   //:        AND mUser.SecurityArea.AllowAccess          = "Y"
   RESULT = mUser.cursor( "SecuritySection" ).setFirst( "User" ).toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mUser, "SecurityAdministrativeDivision", "ID", mUser, "CurrentAdministrativeDivision", "ID" ) != 0 ||
              CompareAttributeToString( mUser, "SecurityArea", "FunctionalArea", szFunctionalArea ) != 0 || CompareAttributeToString( mUser, "SecurityArea", "AllowAccess", "Y" ) != 0 ) )
      { 
         RESULT = mUser.cursor( "SecuritySection" ).setNextContinue().toInt();;
      } 

   } 

   //:IF RESULT >= zCURSOR_SET 
   if ( RESULT >= zCURSOR_SET )
   { 
      //:RETURN 1 
      if(8==8)return( 1 );
   } 

   //:END

   //:// This is the original code to locate the SecurityArea.
   //:// SET CURSOR FIRST mUser2.SecuritySet WITHIN mUser2.User
   //://    WHERE mUser2.SecurityAdministrativeDivision.Name = szDivision 
   //://
   //:// IF RESULT < zCURSOR_SET 
   //://    DropView( mUser2 )
   //://    RETURN -1
   //:// END
   //:// FOR EACH mUser2.SecurityArea WITHIN mUser2.User
   //://    WHERE mUser2.SecurityAdministrativeDivision.Name = szDivision 
   //://      AND mUser2.SecurityArea.FunctionalArea = szArea 
   //://    IF mUser2.SecurityArea.AllowAccess = "Y"
   //://       SetViewFromView( mUser, mUser2 )
   //://       DropView( mUser2 )
   //://       RETURN 1 
   //://    END
   //:// END

   //:RETURN -1
   return( -1 );
// END
} 


//:TRANSFORMATION OPERATION
//:SectionAccessAllowed( VIEW mUser BASED ON LOD mUser,
//:                      STRING ( 128 ) szAdminDiv,
//:                      STRING ( 128 ) szArea,
//:                      STRING ( 128 ) szSection,
//:                      STRING ( 6 ) szPermissionString )
//:   VIEW mUser2 BASED ON LOD mUser
public int 
omUser_SectionAccessAllowed( View     mUser,
                             String   szAdminDiv,
                             String   szArea,
                             String   szSection,
                             String   szPermissionString )
{
   zVIEW    mUser2 = new zVIEW( );
   //:VIEW mUser3 BASED ON LOD mUser
   zVIEW    mUser3 = new zVIEW( );
   //:STRING ( 32 ) szDivision
   String   szDivision = null;
   //:INTEGER nRC 
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;


   //:// Always allow admin!
   //:IF mUser.User.ID = 1
   if ( CompareAttributeToInteger( mUser, "User", "ID", 1 ) == 0 )
   { 
      //:szPermissionString = "CRUDS"
       {StringBuilder sb_szPermissionString;
      if ( szPermissionString == null )
         sb_szPermissionString = new StringBuilder( 32 );
      else
         sb_szPermissionString = new StringBuilder( szPermissionString );
            ZeidonStringCopy( sb_szPermissionString, 1, 0, "CRUDS", 1, 0, 7 );
      szPermissionString = sb_szPermissionString.toString( );}
      //:RETURN 1 
      if(8==8)return( 1 );
   } 

   //:END

   //:szPermissionString = "     "
    {StringBuilder sb_szPermissionString;
   if ( szPermissionString == null )
      sb_szPermissionString = new StringBuilder( 32 );
   else
      sb_szPermissionString = new StringBuilder( szPermissionString );
      ZeidonStringCopy( sb_szPermissionString, 1, 0, "     ", 1, 0, 7 );
   szPermissionString = sb_szPermissionString.toString( );}

   //:CreateViewFromView( mUser2, mUser )
   CreateViewFromView( mUser2, mUser );
   //:NAME VIEW mUser2 "mUser2"
   SetNameForView( mUser2, "mUser2", null, zLEVEL_TASK );
   //:nRC = AccessAllowed( mUser2, szAdminDiv, szArea )
   nRC = omUser_AccessAllowed( mUser2, szAdminDiv, szArea );
   //:IF  nRC < 0 
   if ( nRC < 0 )
   { 
      //:DropView( mUser2 )
      DropView( mUser2 );
      //:RETURN nRC 
      if(8==8)return( nRC );
   } 

   //:END
   //:IF szAdminDiv = ""
   if ( ZeidonStringCompare( szAdminDiv, 1, 0, "", 1, 0, 129 ) == 0 )
   { 
      //:IF mUser.CurrentAdministrativeDivision EXISTS  
      lTempInteger_0 = CheckExistenceOfEntity( mUser, "CurrentAdministrativeDivision" );
      if ( lTempInteger_0 == 0 )
      { 
         //:szDivision = mUser.CurrentAdministrativeDivision.Name  
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szDivision;
         if ( szDivision == null )
            sb_szDivision = new StringBuilder( 32 );
         else
            sb_szDivision = new StringBuilder( szDivision );
                   GetVariableFromAttribute( sb_szDivision, mi_lTempInteger_1, 'S', 33, mUser, "CurrentAdministrativeDivision", "Name", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szDivision = sb_szDivision.toString( );}
         //:ELSE 
      } 
      else
      { 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
      //:ELSE 
   } 
   else
   { 
      //:szDivision = szAdminDiv
       {StringBuilder sb_szDivision;
      if ( szDivision == null )
         sb_szDivision = new StringBuilder( 32 );
      else
         sb_szDivision = new StringBuilder( szDivision );
            ZeidonStringCopy( sb_szDivision, 1, 0, szAdminDiv, 1, 0, 33 );
      szDivision = sb_szDivision.toString( );}
   } 

   //:END

   //://////////////////////////////////////////////////////////////////////////
   //://
   //:// AccessAllowed has established position for mUser2 on a SecurityArea
   //:// entity.  That is not good enough since different groups to which the
   //:// user is assigned may provide different access permissions.  In order
   //:// to "maximize" the user's permissions, we need to process all entities
   //:// (if necessary) to determine if access is permitted from ANY group.
   //://
   //:// In the end, we want to "maximize" the security settings on the current
   //:// SecurityArea entity so that we do not have to continually traverse
   //:// all entities for the permissions, but we cannot since we cannot update
   //:// the SecuritySection entity.
   //://
   //://////////////////////////////////////////////////////////////////////////

   //:SET CURSOR FIRST mUser2.SecuritySection WITHIN mUser2.SecuritySet 
   //:   WHERE mUser2.SecuritySection.SectionName = szSection
   //:     AND mUser2.SecurityAdministrativeDivision.Name = szDivision 
   //:     AND mUser2.SecurityArea.FunctionalArea = szArea 
   //:     AND mUser2.SecurityArea.AllowAccess = "Y"
   RESULT = mUser2.cursor( "SecuritySection" ).setFirst( "SecuritySet" ).toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mUser2, "SecuritySection", "SectionName", szSection ) != 0 || CompareAttributeToString( mUser2, "SecurityAdministrativeDivision", "Name", szDivision ) != 0 ||
              CompareAttributeToString( mUser2, "SecurityArea", "FunctionalArea", szArea ) != 0 || CompareAttributeToString( mUser2, "SecurityArea", "AllowAccess", "Y" ) != 0 ) )
      { 
         RESULT = mUser2.cursor( "SecuritySection" ).setNextContinue().toInt();;
      } 

   } 


   //:SetViewFromView( mUser, mUser2 )
   SetViewFromView( mUser, mUser2 );
   //:CreateViewFromView( mUser3, mUser2 )
   CreateViewFromView( mUser3, mUser2 );
   //:FOR EACH mUser3.UserGroup WITHIN mUser3.User
   RESULT = mUser3.cursor( "UserGroup" ).setFirst( "User" ).toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:IF szPermissionString = "CRUDS"
      if ( ZeidonStringCompare( szPermissionString, 1, 0, "CRUDS", 1, 0, 7 ) == 0 )
      { 
         //:DropView( mUser2 )
         DropView( mUser2 );
         //:DropView( mUser3 )
         DropView( mUser3 );
         //:RETURN 1 
         if(8==8)return( 1 );
      } 

      //:END

      //:SET CURSOR FIRST mUser3.SecuritySet WITHIN mUser3.User
      //:   WHERE mUser3.SecurityAdministrativeDivision.Name = szDivision 
      RESULT = mUser3.cursor( "SecuritySet" ).setFirst( "User" ).toInt();
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mUser3, "SecurityAdministrativeDivision", "Name", szDivision ) != 0 ) )
         { 
            RESULT = mUser3.cursor( "SecuritySet" ).setNextContinue().toInt();;
         } 

      } 


      //:IF RESULT >= zCURSOR_SET 
      if ( RESULT >= zCURSOR_SET )
      { 
         //:FOR EACH mUser3.SecurityArea WITHIN mUser3.User
         //:   WHERE mUser3.SecurityAdministrativeDivision.Name = szDivision 
         //:     AND mUser3.SecurityArea.FunctionalArea = szArea 
         RESULT = mUser3.cursor( "SecurityArea" ).setFirst( "User" ).toInt();
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( mUser3, "SecurityAdministrativeDivision", "Name", szDivision ) == 0 && CompareAttributeToString( mUser3, "SecurityArea", "FunctionalArea", szArea ) == 0 )
            { 
               //:IF mUser3.SecurityArea.AllowAccess = "Y"
               if ( CompareAttributeToString( mUser3, "SecurityArea", "AllowAccess", "Y" ) == 0 )
               { 

                  //:SET CURSOR FIRST mUser3.SecuritySection WITHIN mUser3.SecuritySet 
                  //:WHERE mUser3.SecuritySection.SectionName = szSection
                  //:  AND mUser3.SecurityAdministrativeDivision.Name = szDivision 
                  //:  AND mUser3.SecurityArea.FunctionalArea = szArea 
                  //:  AND mUser3.SecurityArea.AllowAccess = "Y"
                  RESULT = mUser3.cursor( "SecuritySection" ).setFirst( "SecuritySet" ).toInt();
                  if ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mUser3, "SecuritySection", "SectionName", szSection ) != 0 || CompareAttributeToString( mUser3, "SecurityAdministrativeDivision", "Name", szDivision ) != 0 ||
                        CompareAttributeToString( mUser3, "SecurityArea", "FunctionalArea", szArea ) != 0 || CompareAttributeToString( mUser3, "SecurityArea", "AllowAccess", "Y" ) != 0 ) )
                     { 
                        RESULT = mUser3.cursor( "SecuritySection" ).setNextContinue().toInt();;
                     } 

                  } 


                  //:IF mUser3.SecuritySection.CreateAuthority = "Y"
                  if ( CompareAttributeToString( mUser3, "SecuritySection", "CreateAuthority", "Y" ) == 0 )
                  { 
                     //:// mUser2.SecuritySection.CreateAuthority = "Y"
                     //:szPermissionString[ 1 ]  = "C" 
                      {StringBuilder sb_szPermissionString;
                     if ( szPermissionString == null )
                        sb_szPermissionString = new StringBuilder( 32 );
                     else
                        sb_szPermissionString = new StringBuilder( szPermissionString );
                                          ZeidonStringCopy( sb_szPermissionString, 1, -1, "C", 1, 0, 7 );
                     szPermissionString = sb_szPermissionString.toString( );}
                  } 

                  //:END
                  //:IF mUser3.SecuritySection.ReadAuthority = "Y"
                  if ( CompareAttributeToString( mUser3, "SecuritySection", "ReadAuthority", "Y" ) == 0 )
                  { 
                     //:// mUser2.SecuritySection.ReadAuthority = "Y"
                     //:szPermissionString[ 2 ]  = "R" 
                      {StringBuilder sb_szPermissionString;
                     if ( szPermissionString == null )
                        sb_szPermissionString = new StringBuilder( 32 );
                     else
                        sb_szPermissionString = new StringBuilder( szPermissionString );
                                          ZeidonStringCopy( sb_szPermissionString, 2, -1, "R", 1, 0, 7 );
                     szPermissionString = sb_szPermissionString.toString( );}
                  } 

                  //:END
                  //:IF mUser3.SecuritySection.UpdateAuthority = "Y"
                  if ( CompareAttributeToString( mUser3, "SecuritySection", "UpdateAuthority", "Y" ) == 0 )
                  { 
                     //:// mUser2.SecuritySection.UpdateAuthority = "Y"
                     //:szPermissionString[ 3 ]  = "U" 
                      {StringBuilder sb_szPermissionString;
                     if ( szPermissionString == null )
                        sb_szPermissionString = new StringBuilder( 32 );
                     else
                        sb_szPermissionString = new StringBuilder( szPermissionString );
                                          ZeidonStringCopy( sb_szPermissionString, 3, -1, "U", 1, 0, 7 );
                     szPermissionString = sb_szPermissionString.toString( );}
                  } 

                  //:END
                  //:IF mUser3.SecuritySection.DeleteAuthority = "Y"
                  if ( CompareAttributeToString( mUser3, "SecuritySection", "DeleteAuthority", "Y" ) == 0 )
                  { 
                     //:// mUser2.SecuritySection.DeleteAuthority = "Y"
                     //:szPermissionString[ 4 ]  = "D" 
                      {StringBuilder sb_szPermissionString;
                     if ( szPermissionString == null )
                        sb_szPermissionString = new StringBuilder( 32 );
                     else
                        sb_szPermissionString = new StringBuilder( szPermissionString );
                                          ZeidonStringCopy( sb_szPermissionString, 4, -1, "D", 1, 0, 7 );
                     szPermissionString = sb_szPermissionString.toString( );}
                  } 

                  //:END
                  //:IF mUser3.SecuritySection.SpecialAuthority = "Y"
                  if ( CompareAttributeToString( mUser3, "SecuritySection", "SpecialAuthority", "Y" ) == 0 )
                  { 
                     //:// mUser2.SecuritySection.SpecialAuthority = "Y"
                     //:szPermissionString[ 5 ]  = "S" 
                      {StringBuilder sb_szPermissionString;
                     if ( szPermissionString == null )
                        sb_szPermissionString = new StringBuilder( 32 );
                     else
                        sb_szPermissionString = new StringBuilder( szPermissionString );
                                          ZeidonStringCopy( sb_szPermissionString, 5, -1, "S", 1, 0, 7 );
                     szPermissionString = sb_szPermissionString.toString( );}
                  } 

                  //:END
               } 

            } 

            RESULT = mUser3.cursor( "SecurityArea" ).setNextContinue().toInt();;

            //:END
         } 

         //:END
      } 

      RESULT = mUser3.cursor( "UserGroup" ).setNextContinue().toInt();;
      //:END
   } 


   //:END

   //:DropView( mUser2 )
   DropView( mUser2 );
   //:DropView( mUser3 )
   DropView( mUser3 );

   //://////////////////////////////////////////////////////////////////////////
   //://////////////////////////////////////////////////////////////////////////
   //://
   //:// This is the original code to set the permission string.
   //://
   //:// /*FOR EACH mUser2.SecuritySection WITHIN mUser2.User
   //://    WHERE mUser2.SecuritySection.SectionName = szSection
   //://      AND mUser2.SecurityAdministrativeDivision.Name = szDivision 
   //://      AND mUser2.SecurityArea.FunctionalArea = szArea 
   //://      AND mUser2.SecurityArea.AllowAccess = "Y"*/
   //:// SET CURSOR FIRST mUser2.SecuritySection WITHIN mUser2.SecuritySet 
   //://    WHERE mUser2.SecuritySection.SectionName = szSection
   //://      AND mUser2.SecurityAdministrativeDivision.Name = szDivision 
   //://      AND mUser2.SecurityArea.FunctionalArea = szArea 
   //://      AND mUser2.SecurityArea.AllowAccess = "Y"
   //://
   //://    IF mUser2.SecuritySection.CreateAuthority = "Y"
   //://       szPermissionString[ 1 ]  = "C" 
   //://    END
   //://    IF mUser2.SecuritySection.ReadAuthority = "Y"
   //://       szPermissionString[ 2 ]  = "R" 
   //://    END
   //://    IF mUser2.SecuritySection.UpdateAuthority = "Y"
   //://       szPermissionString[ 3 ]  = "U" 
   //://    END
   //://    IF mUser2.SecuritySection.DeleteAuthority = "Y"
   //://       szPermissionString[ 4 ]  = "D" 
   //://    END
   //://    IF mUser2.SecuritySection.SpecialAuthority = "Y"
   //://       szPermissionString[ 5 ]  = "S" 
   //://    END
   //:// //END
   //://
   //:// //////////////////////////
   //:// //TraceLineS("Done With SectionAccessAllowed:",szPermissionString )
   //://
   //:// SetViewFromView( mUser, mUser2 )
   //:// DropView( mUser2 )

   //:RETURN 1
   return( 1 );
// END
} 


//:TRANSFORMATION OPERATION
//:DocumentAccessAllowed( VIEW mUser BASED ON LOD mUser,
//:                       STRING ( 128 ) szAdminDiv,
//:                       STRING ( 128 ) szArea )
//:   VIEW mUser2 BASED ON LOD mUser
public int 
omUser_DocumentAccessAllowed( View     mUser,
                              String   szAdminDiv,
                              String   szArea )
{
   zVIEW    mUser2 = new zVIEW( );
   //:STRING ( 32 ) szDivision
   String   szDivision = null;
   //:INTEGER nRC 
   int      nRC = 0;
   //:INTEGER nAllowAll
   int      nAllowAll = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;


   //:// Always allow admin!
   //:IF mUser.User.ID = 1
   if ( CompareAttributeToInteger( mUser, "User", "ID", 1 ) == 0 )
   { 
      //:RETURN 2 // all
      if(8==8)return( 2 );
   } 

   //:END

   //:CreateViewFromView( mUser2, mUser )
   CreateViewFromView( mUser2, mUser );
   //:nRC = AccessAllowed( mUser2, szAdminDiv, szArea )
   nRC = omUser_AccessAllowed( mUser2, szAdminDiv, szArea );
   //:IF  nRC < 0 
   if ( nRC < 0 )
   { 
      //:DropView( mUser2 )
      DropView( mUser2 );
      //:RETURN -1 
      if(8==8)return( -1 );
   } 

   //:END
   //:IF szAdminDiv = ""
   if ( ZeidonStringCompare( szAdminDiv, 1, 0, "", 1, 0, 129 ) == 0 )
   { 
      //:IF mUser.CurrentAdministrativeDivision EXISTS  
      lTempInteger_0 = CheckExistenceOfEntity( mUser, "CurrentAdministrativeDivision" );
      if ( lTempInteger_0 == 0 )
      { 
         //:szDivision = mUser.CurrentAdministrativeDivision.Name  
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szDivision;
         if ( szDivision == null )
            sb_szDivision = new StringBuilder( 32 );
         else
            sb_szDivision = new StringBuilder( szDivision );
                   GetVariableFromAttribute( sb_szDivision, mi_lTempInteger_1, 'S', 33, mUser, "CurrentAdministrativeDivision", "Name", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szDivision = sb_szDivision.toString( );}
         //:ELSE 
      } 
      else
      { 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
      //:ELSE 
   } 
   else
   { 
      //:szDivision = szAdminDiv
       {StringBuilder sb_szDivision;
      if ( szDivision == null )
         sb_szDivision = new StringBuilder( 32 );
      else
         sb_szDivision = new StringBuilder( szDivision );
            ZeidonStringCopy( sb_szDivision, 1, 0, szAdminDiv, 1, 0, 33 );
      szDivision = sb_szDivision.toString( );}
   } 

   //:END

   //://////////////////////////
   //:nAllowAll = 0
   nAllowAll = 0;
   //:FOR EACH mUser2.SecuritySection WITHIN mUser2.User
   //:   WHERE mUser2.SecuritySection.SectionName = "Documents"
   //:     AND mUser2.SecurityAdministrativeDivision.Name = szDivision 
   //:     AND mUser2.SecurityArea.FunctionalArea = szArea 
   //:     AND mUser2.SecurityArea.AllowAccess = "Y"
   RESULT = mUser2.cursor( "SecuritySection" ).setFirst( "User" ).toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( mUser2, "SecuritySection", "SectionName", "Documents" ) == 0 && CompareAttributeToString( mUser2, "SecurityAdministrativeDivision", "Name", szDivision ) == 0 &&
           CompareAttributeToString( mUser2, "SecurityArea", "FunctionalArea", szArea ) == 0 && CompareAttributeToString( mUser2, "SecurityArea", "AllowAccess", "Y" ) == 0 )
      { 

         //:IF mUser2.SecuritySection.CreateAuthority = "Y"
         if ( CompareAttributeToString( mUser2, "SecuritySection", "CreateAuthority", "Y" ) == 0 )
         { 
            //:nAllowAll = nAllowAll + 2
            nAllowAll = nAllowAll + 2;
         } 

         //:END
         //:IF mUser2.SecuritySection.ReadAuthority = "Y"
         if ( CompareAttributeToString( mUser2, "SecuritySection", "ReadAuthority", "Y" ) == 0 )
         { 
            //:nAllowAll = nAllowAll + 1
            nAllowAll = nAllowAll + 1;
         } 

         //:END
         //:IF mUser2.SecuritySection.UpdateAuthority = "Y"
         if ( CompareAttributeToString( mUser2, "SecuritySection", "UpdateAuthority", "Y" ) == 0 )
         { 
            //:nAllowAll = nAllowAll + 2
            nAllowAll = nAllowAll + 2;
         } 

      } 

      RESULT = mUser2.cursor( "SecuritySection" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:SetViewFromView( mUser, mUser2 )
   SetViewFromView( mUser, mUser2 );
   //:DropView( mUser2 )
   DropView( mUser2 );

   //:RETURN nAllowAll 
   return( nAllowAll );
// END
} 


//:OBJECT CONSTRAINT OPERATION
public int 
omUser_ObjectConstraints( View     mUser,
                          Integer   Event,
                          Integer   State )
{
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;

   //:ObjectConstraints( VIEW mUser BASED ON LOD mUser,
   //:                SHORT Event,
   //:                SHORT State )

   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:// Set the Faculty and Staff work attributes.
         //:IF mUser.Faculty EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mUser, "Faculty" );
         if ( lTempInteger_0 == 0 )
         { 
            //:mUser.User.wIsFacultyMember = "Y" 
            SetAttributeFromString( mUser, "User", "wIsFacultyMember", "Y" );
         } 

         //:END
         //:IF mUser.Staff EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mUser, "Staff" );
         if ( lTempInteger_1 == 0 )
         { 
            //:mUser.User.wIsStaffMember = "Y"
            SetAttributeFromString( mUser, "User", "wIsStaffMember", "Y" );
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

         //:// Make sure that if EmailAddress is specified for the User, then the same address exists
         //:// for any associated Student or Employee entity.
         //:IF mUser.User.eMailAddress != ""
         if ( CompareAttributeToString( mUser, "User", "eMailAddress", "" ) != 0 )
         { 
            //:IF mUser.Student EXISTS 
            lTempInteger_2 = CheckExistenceOfEntity( mUser, "Student" );
            if ( lTempInteger_2 == 0 )
            { 
               //:IF mUser.Student.eMailAddress != mUser.User.eMailAddress
               if ( CompareAttributeToAttribute( mUser, "Student", "eMailAddress", mUser, "User", "eMailAddress" ) != 0 )
               { 
                  //:mUser.Student.eMailAddress = mUser.User.eMailAddress
                  SetAttributeFromAttribute( mUser, "Student", "eMailAddress", mUser, "User", "eMailAddress" );
               } 

               //:END
            } 

            //:END
            //:IF mUser.Employee EXISTS 
            lTempInteger_3 = CheckExistenceOfEntity( mUser, "Employee" );
            if ( lTempInteger_3 == 0 )
            { 
               //:IF mUser.Employee.eMailAddress != mUser.User.eMailAddress
               if ( CompareAttributeToAttribute( mUser, "Employee", "eMailAddress", mUser, "User", "eMailAddress" ) != 0 )
               { 
                  //:mUser.Employee.eMailAddress = mUser.User.eMailAddress
                  SetAttributeFromAttribute( mUser, "Employee", "eMailAddress", mUser, "User", "eMailAddress" );
               } 

               //:END
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


//:TRANSFORMATION OPERATION
//:SubSectAccessAllowed( VIEW mUser BASED ON LOD mUser,
//:                      STRING ( 128 ) szAdminDiv,
//:                      STRING ( 128 ) szArea,
//:                      STRING ( 128 ) szSection,
//:                      STRING ( 50 )  szSubSectionName )

//:   VIEW mUser2 BASED ON LOD mUser
public int 
omUser_SubSectAccessAllowed( View     mUser,
                             String   szAdminDiv,
                             String   szArea,
                             String   szSection,
                             String   szSubSectionName )
{
   zVIEW    mUser2 = new zVIEW( );
   //:STRING ( 32 ) szDivision
   String   szDivision = null;
   //:INTEGER nRC 
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;


   //:// Always allow admin!
   //:IF mUser.User.ID = 1
   if ( CompareAttributeToInteger( mUser, "User", "ID", 1 ) == 0 )
   { 
      //:// TraceLineS( "SectionAccessAllowed:", " as Admins" )
      //:RETURN 1 
      if(8==8)return( 1 );
   } 

   //:END

   //:IF szAdminDiv = ""
   if ( ZeidonStringCompare( szAdminDiv, 1, 0, "", 1, 0, 129 ) == 0 )
   { 
      //:IF mUser.CurrentAdministrativeDivision EXISTS  
      lTempInteger_0 = CheckExistenceOfEntity( mUser, "CurrentAdministrativeDivision" );
      if ( lTempInteger_0 == 0 )
      { 
         //:szDivision = mUser.CurrentAdministrativeDivision.Name  
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szDivision;
         if ( szDivision == null )
            sb_szDivision = new StringBuilder( 32 );
         else
            sb_szDivision = new StringBuilder( szDivision );
                   GetVariableFromAttribute( sb_szDivision, mi_lTempInteger_1, 'S', 33, mUser, "CurrentAdministrativeDivision", "Name", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szDivision = sb_szDivision.toString( );}
         //:ELSE 
      } 
      else
      { 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
      //:ELSE 
   } 
   else
   { 
      //:szDivision = szAdminDiv
       {StringBuilder sb_szDivision;
      if ( szDivision == null )
         sb_szDivision = new StringBuilder( 32 );
      else
         sb_szDivision = new StringBuilder( szDivision );
            ZeidonStringCopy( sb_szDivision, 1, 0, szAdminDiv, 1, 0, 33 );
      szDivision = sb_szDivision.toString( );}
   } 

   //:END

   //://////////////////////////////////////////////////////////////////////////
   //://
   //:// AccessAllowed has established position for mUser2 on a SecurityArea
   //:// entity.  That is not good enough since different groups to which the
   //:// user is assigned may provide different access permissions.  In order
   //:// to "maximize" the user's permissions, we need to process all entities
   //:// (if necessary) to determine if access is permitted from ANY group.
   //://
   //://////////////////////////////////////////////////////////////////////////

   //:CreateViewFromView( mUser2, mUser )
   CreateViewFromView( mUser2, mUser );
   //:FOR EACH mUser2.UserGroup WITHIN mUser2.User
   RESULT = mUser2.cursor( "UserGroup" ).setFirst( "User" ).toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:SET CURSOR FIRST mUser2.SecuritySection WITHIN mUser2.SecuritySet 
      //:   WHERE mUser2.SecuritySection.SectionName = szSection
      //:     AND mUser2.SecurityAdministrativeDivision.Name = szDivision 
      //:     AND mUser2.SecurityArea.FunctionalArea = szArea 
      //:     AND mUser2.SecurityArea.AllowAccess = "Y"
      RESULT = mUser2.cursor( "SecuritySection" ).setFirst( "SecuritySet" ).toInt();
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mUser2, "SecuritySection", "SectionName", szSection ) != 0 || CompareAttributeToString( mUser2, "SecurityAdministrativeDivision", "Name", szDivision ) != 0 ||
                 CompareAttributeToString( mUser2, "SecurityArea", "FunctionalArea", szArea ) != 0 || CompareAttributeToString( mUser2, "SecurityArea", "AllowAccess", "Y" ) != 0 ) )
         { 
            RESULT = mUser2.cursor( "SecuritySection" ).setNextContinue().toInt();;
         } 

      } 


      //:// This assumes that we already have a valid position.
      //:IF RESULT > 0
      if ( RESULT > 0 )
      { 
         //:SET CURSOR FIRST mUser.SecuritySubSection 
         //:   WHERE mUser.SecuritySubSection.SubSectionName = szSubSectionName
         RESULT = mUser.cursor( "SecuritySubSection" ).setFirst( "SubSectionName", szSubSectionName ).toInt();
         //:IF RESULT > 0
         if ( RESULT > 0 )
         { 
            //:IF mUser.SecuritySubSection.AllowAccess = "Y" 
            if ( CompareAttributeToString( mUser, "SecuritySubSection", "AllowAccess", "Y" ) == 0 )
            { 
               //:DropView( mUser2 )
               DropView( mUser2 );
               //:RETURN 1
               if(8==8)return( 1 );
            } 

            //:END
         } 

         //:END
      } 

      RESULT = mUser2.cursor( "UserGroup" ).setNextContinue().toInt();;
      //:END
   } 

   //:END

   //:DropView( mUser2 )
   DropView( mUser2 );

   //:// This is the original code to locate the SubSection access.
   //://
   //:// SET CURSOR FIRST mUser.SecuritySection WITHIN mUser.User
   //://    WHERE mUser.SecuritySection.SectionName = szSection
   //://      AND mUser.SecurityAdministrativeDivision.Name = szDivision 
   //://      AND mUser.SecurityArea.FunctionalArea = szArea 
   //:// 
   //:// // this operation assumes that we already have a valid position
   //:// IF RESULT > 0
   //://    SET CURSOR FIRST mUser.SecuritySubSection 
   //://       WHERE mUser.SecuritySubSection.SubSectionName = szSubSectionName
   //://    IF RESULT > 0
   //://       IF mUser.SecuritySubSection.AllowAccess = "Y" 
   //://          RETURN 1
   //://       ELSE 
   //://          RETURN 0 
   //://       END
   //://    ELSE 
   //://       RETURN 0 
   //://    END
   //:// ELSE 
   //://    RETURN 0 
   //:// END

   //:RETURN 0 
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ValidateEmailValues( VIEW mUser BASED ON LOD mUser,
//:                     STRING ( 255 ) szSMTPServer,
//:                     STRING ( 255 ) szUserEmailAddress,
//:                     STRING ( 255 ) szEmailUserName,
//:                     STRING ( 255 ) szEmailPassword )
//:   
//:   VIEW sHost BASED ON LOD sHost
public int 
omUser_ValidateEmailValues( View     mUser,
                            StringBuilder   szSMTPServer,
                            StringBuilder   szUserEmailAddress,
                            StringBuilder   szEmailUserName,
                            StringBuilder   szEmailPassword )
{
   zVIEW    sHost = new zVIEW( );
   //:STRING ( 200 ) Msg
   String   Msg = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:// Make sure Email information has been specified and return values.

   //:GetViewByName( sHost, "sHost", mUser, zLEVEL_APPLICATION )
   GetViewByName( sHost, "sHost", mUser, zLEVEL_APPLICATION );

   //:szSMTPServer = mUser.User.SMTPServer
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetVariableFromAttribute( szSMTPServer, mi_lTempInteger_0, 'S', 256, mUser, "User", "SMTPServer", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   //:IF szSMTPServer = ""
   if ( ZeidonStringCompare( szSMTPServer.toString( ), 1, 0, "", 1, 0, 256 ) == 0 )
   { 
      //:szSMTPServer = sHost.Host.SMTPServer
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetVariableFromAttribute( szSMTPServer, mi_lTempInteger_1, 'S', 256, sHost, "Host", "SMTPServer", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   } 

   //:END
   //:IF szSMTPServer = ""
   if ( ZeidonStringCompare( szSMTPServer.toString( ), 1, 0, "", 1, 0, 256 ) == 0 )
   { 
      //:Msg = "An SMTP server must be specified on the Host before email can be used."
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringCopy( sb_Msg, 1, 0, "An SMTP server must be specified on the Host before email can be used.", 1, 0, 201 );
      Msg = sb_Msg.toString( );}
      //:MessageSend( mUser, "", "Send Email", Msg,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mUser, "", "Send Email", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" )
//      SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:szUserEmailAddress = mUser.User.eMailUserName
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetVariableFromAttribute( szUserEmailAddress, mi_lTempInteger_2, 'S', 256, mUser, "User", "eMailUserName", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   //:IF szUserEmailAddress = ""
   if ( ZeidonStringCompare( szUserEmailAddress.toString( ), 1, 0, "", 1, 0, 256 ) == 0 )
   { 
      //:Msg = "The sender's email address must be specified on the person record before email can be used."
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringCopy( sb_Msg, 1, 0, "The sender's email address must be specified on the person record before email can be used.", 1, 0, 201 );
      Msg = sb_Msg.toString( );}
      //:MessageSend( mUser, "", "Send Email", Msg,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mUser, "", "Send Email", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" )
//      SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:szEmailUserName = mUser.User.eMailUserName
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetVariableFromAttribute( szEmailUserName, mi_lTempInteger_3, 'S', 256, mUser, "User", "eMailUserName", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   //:IF szEmailUserName = ""
   if ( ZeidonStringCompare( szEmailUserName.toString( ), 1, 0, "", 1, 0, 256 ) == 0 )
   { 
      //:Msg = "The sender's email user name must be specified on the System Admin USER record before email can be used."
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringCopy( sb_Msg, 1, 0, "The sender's email user name must be specified on the System Admin USER record before email can be used.", 1, 0, 201 );
      Msg = sb_Msg.toString( );}
      //:MessageSend( mUser, "", "Send Email", Msg,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mUser, "", "Send Email", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" )
//      SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// szEMailPassword = mUser.User.eMailPassword
   //:GetStringFromAttributeByContext( szEmailPassword, mUser, "User",
   //:                                 "eMailPassword", "PasswordUL", 256 )
   GetStringFromAttributeByContext( szEmailPassword, mUser, "User", "eMailPassword", "PasswordUL", 256 );
   //:IF szEmailPassword = ""
   if ( ZeidonStringCompare( szEmailPassword.toString( ), 1, 0, "", 1, 0, 256 ) == 0 )
   { 
      //:Msg = "The sender's email password must be specified on the System Admin USER record before email can be used."
       {StringBuilder sb_Msg;
      if ( Msg == null )
         sb_Msg = new StringBuilder( 32 );
      else
         sb_Msg = new StringBuilder( Msg );
            ZeidonStringCopy( sb_Msg, 1, 0, "The sender's email password must be specified on the System Admin USER record before email can be used.", 1, 0, 201 );
      Msg = sb_Msg.toString( );}
      //:MessageSend( mUser, "", "Send Email", Msg,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mUser, "", "Send Email", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" )
//      SetWindowActionBehavior( mUser, zWAB_StayOnWindow, "", "" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omUser_SecuritySectionPos( View     mUser,
                           View     sAppMgr )
{
   int      RESULT = 0;

   //:SecuritySectionPos( VIEW mUser   BASED ON LOD mUser,
   //:                 VIEW sAppMgr BASED ON LOD sAppMgr )

   //:// Position on the correct security section for use in determining which tabs the User will be able to access.
   //:// In case the User has access to multiple security groups, position on the first FunctionalArea/SubArea 
   //:// combination to which the User has any Access.
   //:// If the User is Admin, we will return a 1 to indicate that Tab security is not to be processed.

   //:// User is Admin.
   //:IF mUser.User.ID = 1
   if ( CompareAttributeToInteger( mUser, "User", "ID", 1 ) == 0 )
   { 
      //:RETURN 1 
      if(8==8)return( 1 );
   } 

   //:END

   //:// Position on Section/SubSection.
   //:// Note that there must always be a match on current Administrative Division.
   //:   
   //:SET CURSOR FIRST mUser.SecuritySection WITHIN mUser.User
   //:      WHERE mUser.SecuritySection.SectionName       = sAppMgr.FunctionalSubArea.FunctionalSubAreaName
   //:        AND mUser.SecurityAdministrativeDivision.ID = mUser.CurrentAdministrativeDivision.ID 
   //:        AND mUser.SecurityArea.FunctionalArea       = sAppMgr.FunctionalArea.FunctionalAreaName 
   //:        AND mUser.SecurityArea.AllowAccess          = "Y"
   RESULT = mUser.cursor( "SecuritySection" ).setFirst( "User" ).toInt();
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mUser, "SecuritySection", "SectionName", sAppMgr, "FunctionalSubArea", "FunctionalSubAreaName" ) != 0 ||
              CompareAttributeToAttribute( mUser, "SecurityAdministrativeDivision", "ID", mUser, "CurrentAdministrativeDivision", "ID" ) != 0 ||
              CompareAttributeToAttribute( mUser, "SecurityArea", "FunctionalArea", sAppMgr, "FunctionalArea", "FunctionalAreaName" ) != 0 || CompareAttributeToString( mUser, "SecurityArea", "AllowAccess", "Y" ) != 0 ) )
      { 
         RESULT = mUser.cursor( "SecuritySection" ).setNextContinue().toInt();;
      } 

   } 

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //://MessageSend( mUser, "", "Security", 
      //://             "There is no security access for this User, which is a programming error in this situation.",
      //://             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
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


//:DERIVED ATTRIBUTE OPERATION
//:dWelcomeMsg( VIEW mUser BASED ON LOD mUser,
//:             STRING ( 32 ) InternalEntityStructure,
//:             STRING ( 32 ) InternalAttribStructure,
//:             SHORT GetOrSetFlag )

//:   STRING (  254  ) szWebMsg
public int 
omUser_dWelcomeMsg( View     mUser,
                    String InternalEntityStructure,
                    String InternalAttribStructure,
                    Integer   GetOrSetFlag )
{
   String   szWebMsg = null;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:      
         //:IF mUser.Person.PreferedFirstName = ""
         if ( CompareAttributeToString( mUser, "Person", "PreferedFirstName", "" ) == 0 )
         { 
            //:szWebMsg = "Welcome, " + mUser.Person.FirstName + " " + mUser.Person.LastName 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 51, mUser, "Person", "FirstName", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szWebMsg;
            if ( szWebMsg == null )
               sb_szWebMsg = new StringBuilder( 32 );
            else
               sb_szWebMsg = new StringBuilder( szWebMsg );
                        ZeidonStringCopy( sb_szWebMsg, 1, 0, "Welcome, ", 1, 0, 255 );
            szWebMsg = sb_szWebMsg.toString( );}
             {StringBuilder sb_szWebMsg;
            if ( szWebMsg == null )
               sb_szWebMsg = new StringBuilder( 32 );
            else
               sb_szWebMsg = new StringBuilder( szWebMsg );
                        ZeidonStringConcat( sb_szWebMsg, 1, 0, szTempString_0, 1, 0, 255 );
            szWebMsg = sb_szWebMsg.toString( );}
             {StringBuilder sb_szWebMsg;
            if ( szWebMsg == null )
               sb_szWebMsg = new StringBuilder( 32 );
            else
               sb_szWebMsg = new StringBuilder( szWebMsg );
                        ZeidonStringConcat( sb_szWebMsg, 1, 0, " ", 1, 0, 255 );
            szWebMsg = sb_szWebMsg.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 51, mUser, "Person", "LastName", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szWebMsg;
            if ( szWebMsg == null )
               sb_szWebMsg = new StringBuilder( 32 );
            else
               sb_szWebMsg = new StringBuilder( szWebMsg );
                        ZeidonStringConcat( sb_szWebMsg, 1, 0, szTempString_1, 1, 0, 255 );
            szWebMsg = sb_szWebMsg.toString( );}
            //:ELSE  
         } 
         else
         { 
            //:szWebMsg = "Welcome, " + mUser.Person.PreferedFirstName + " " + mUser.Person.LastName 
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_2, 'S', 51, mUser, "Person", "PreferedFirstName", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szWebMsg;
            if ( szWebMsg == null )
               sb_szWebMsg = new StringBuilder( 32 );
            else
               sb_szWebMsg = new StringBuilder( szWebMsg );
                        ZeidonStringCopy( sb_szWebMsg, 1, 0, "Welcome, ", 1, 0, 255 );
            szWebMsg = sb_szWebMsg.toString( );}
             {StringBuilder sb_szWebMsg;
            if ( szWebMsg == null )
               sb_szWebMsg = new StringBuilder( 32 );
            else
               sb_szWebMsg = new StringBuilder( szWebMsg );
                        ZeidonStringConcat( sb_szWebMsg, 1, 0, szTempString_2, 1, 0, 255 );
            szWebMsg = sb_szWebMsg.toString( );}
             {StringBuilder sb_szWebMsg;
            if ( szWebMsg == null )
               sb_szWebMsg = new StringBuilder( 32 );
            else
               sb_szWebMsg = new StringBuilder( szWebMsg );
                        ZeidonStringConcat( sb_szWebMsg, 1, 0, " ", 1, 0, 255 );
            szWebMsg = sb_szWebMsg.toString( );}
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                         GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_3, 'S', 51, mUser, "Person", "LastName", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szTempString_3 = sb_szTempString_3.toString( );}
             {StringBuilder sb_szWebMsg;
            if ( szWebMsg == null )
               sb_szWebMsg = new StringBuilder( 32 );
            else
               sb_szWebMsg = new StringBuilder( szWebMsg );
                        ZeidonStringConcat( sb_szWebMsg, 1, 0, szTempString_3, 1, 0, 255 );
            szWebMsg = sb_szWebMsg.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mUser,
         //:                      InternalEntityStructure, InternalAttribStructure, szWebMsg )
         StoreStringInRecord( mUser, InternalEntityStructure, InternalAttribStructure, szWebMsg );
         break ;

      //:  /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:     /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 



}
