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

package com.quinsoft.mmcrm;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.quinsoft.mmcrm.icaGlobal1_Operation;

/**
   @author QuinSoft
**/

public class mOrganization_Object extends VmlObjectOperations
{
   public mOrganization_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dDisplayName( VIEW mOrganization BASED ON LOD mOrganization,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )

//:   STRING ( 254 ) szDisplay
public int 
omOrganization_dDisplayName( View     mOrganization,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szDisplay = null;
   //:STRING ( 254 ) szPrefix
   String   szPrefix = null;
   //:STRING ( 254 ) szFirstName
   String   szFirstName = null;
   //:STRING ( 254 ) szLastName
   String   szLastName = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:GetStringFromAttribute( szPrefix, mOrganization, "Person", "Prefix" )
         {StringBuilder sb_szPrefix;
         if ( szPrefix == null )
            sb_szPrefix = new StringBuilder( 32 );
         else
            sb_szPrefix = new StringBuilder( szPrefix );
                   GetStringFromAttribute( sb_szPrefix, mOrganization, "Person", "Prefix" );
         szPrefix = sb_szPrefix.toString( );}
         //:IF szPrefix != ""
         if ( ZeidonStringCompare( szPrefix, 1, 0, "", 1, 0, 255 ) != 0 )
         { 
            //:szPrefix = szPrefix + " "
             {StringBuilder sb_szPrefix;
            if ( szPrefix == null )
               sb_szPrefix = new StringBuilder( 32 );
            else
               sb_szPrefix = new StringBuilder( szPrefix );
                        ZeidonStringConcat( sb_szPrefix, 1, 0, " ", 1, 0, 255 );
            szPrefix = sb_szPrefix.toString( );}
         } 

         //:END 
         //:GetStringFromAttribute( szFirstName, mOrganization, "Person", "FirstName" )
         {StringBuilder sb_szFirstName;
         if ( szFirstName == null )
            sb_szFirstName = new StringBuilder( 32 );
         else
            sb_szFirstName = new StringBuilder( szFirstName );
                   GetStringFromAttribute( sb_szFirstName, mOrganization, "Person", "FirstName" );
         szFirstName = sb_szFirstName.toString( );}
         //:szFirstName = szFirstName + " "
          {StringBuilder sb_szFirstName;
         if ( szFirstName == null )
            sb_szFirstName = new StringBuilder( 32 );
         else
            sb_szFirstName = new StringBuilder( szFirstName );
                  ZeidonStringConcat( sb_szFirstName, 1, 0, " ", 1, 0, 255 );
         szFirstName = sb_szFirstName.toString( );}
         //:GetStringFromAttribute( szLastName, mOrganization, "Person", "LastName" )
         {StringBuilder sb_szLastName;
         if ( szLastName == null )
            sb_szLastName = new StringBuilder( 32 );
         else
            sb_szLastName = new StringBuilder( szLastName );
                   GetStringFromAttribute( sb_szLastName, mOrganization, "Person", "LastName" );
         szLastName = sb_szLastName.toString( );}
         //:szDisplay = szPrefix + szFirstName + szLastName
          {StringBuilder sb_szDisplay;
         if ( szDisplay == null )
            sb_szDisplay = new StringBuilder( 32 );
         else
            sb_szDisplay = new StringBuilder( szDisplay );
                  ZeidonStringCopy( sb_szDisplay, 1, 0, szPrefix, 1, 0, 255 );
         szDisplay = sb_szDisplay.toString( );}
          {StringBuilder sb_szDisplay;
         if ( szDisplay == null )
            sb_szDisplay = new StringBuilder( 32 );
         else
            sb_szDisplay = new StringBuilder( szDisplay );
                  ZeidonStringConcat( sb_szDisplay, 1, 0, szFirstName, 1, 0, 255 );
         szDisplay = sb_szDisplay.toString( );}
          {StringBuilder sb_szDisplay;
         if ( szDisplay == null )
            sb_szDisplay = new StringBuilder( 32 );
         else
            sb_szDisplay = new StringBuilder( szDisplay );
                  ZeidonStringConcat( sb_szDisplay, 1, 0, szLastName, 1, 0, 255 );
         szDisplay = sb_szDisplay.toString( );}
         //:StoreStringInRecord( mOrganization,
         //:                  InternalEntityStructure,
         //:                  InternalAttribStructure,
         //:                  szDisplay )
         StoreStringInRecord( mOrganization, InternalEntityStructure, InternalAttribStructure, szDisplay );
         break ;

      //:  /* end zDE        /* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:END  /* case */
   return( 0 );
//       
// END
} 


public int 
omOrganization_fnLocalBuildQual_0( View     vSubtask,
                                   zVIEW    vQualObject,
                                   int      lTempInteger_8 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Organization" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Organization" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "OrganizationID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_8 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DERIVED ATTRIBUTE OPERATION
//:dAccountRoleName( VIEW mOrganization BASED ON LOD mOrganization,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING ( 254 ) szDisplay
public int 
omOrganization_dAccountRoleName( View     mOrganization,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   String   szDisplay = null;
   //:STRING ( 254 ) szPrefix
   String   szPrefix = null;
   //:STRING ( 254 ) szFirstName
   String   szFirstName = null;
   //:STRING ( 254 ) szLastName
   String   szLastName = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:szLastName = mOrganization.AccountRolePerson.LastName 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szLastName;
         if ( szLastName == null )
            sb_szLastName = new StringBuilder( 32 );
         else
            sb_szLastName = new StringBuilder( szLastName );
                   GetVariableFromAttribute( sb_szLastName, mi_lTempInteger_0, 'S', 255, mOrganization, "AccountRolePerson", "LastName", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szLastName = sb_szLastName.toString( );}
         //:szFirstName = mOrganization.AccountRolePerson.FirstName
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szFirstName;
         if ( szFirstName == null )
            sb_szFirstName = new StringBuilder( 32 );
         else
            sb_szFirstName = new StringBuilder( szFirstName );
                   GetVariableFromAttribute( sb_szFirstName, mi_lTempInteger_1, 'S', 255, mOrganization, "AccountRolePerson", "FirstName", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szFirstName = sb_szFirstName.toString( );}
         //:szDisplay = szFirstName + " " + szLastName
          {StringBuilder sb_szDisplay;
         if ( szDisplay == null )
            sb_szDisplay = new StringBuilder( 32 );
         else
            sb_szDisplay = new StringBuilder( szDisplay );
                  ZeidonStringCopy( sb_szDisplay, 1, 0, szFirstName, 1, 0, 255 );
         szDisplay = sb_szDisplay.toString( );}
          {StringBuilder sb_szDisplay;
         if ( szDisplay == null )
            sb_szDisplay = new StringBuilder( 32 );
         else
            sb_szDisplay = new StringBuilder( szDisplay );
                  ZeidonStringConcat( sb_szDisplay, 1, 0, " ", 1, 0, 255 );
         szDisplay = sb_szDisplay.toString( );}
          {StringBuilder sb_szDisplay;
         if ( szDisplay == null )
            sb_szDisplay = new StringBuilder( 32 );
         else
            sb_szDisplay = new StringBuilder( szDisplay );
                  ZeidonStringConcat( sb_szDisplay, 1, 0, szLastName, 1, 0, 255 );
         szDisplay = sb_szDisplay.toString( );}
         //:StoreStringInRecord( mOrganization,
         //:                  InternalEntityStructure, InternalAttribStructure, szDisplay )
         StoreStringInRecord( mOrganization, InternalEntityStructure, InternalAttribStructure, szDisplay );
         break ;

      //:  /* end zDE        /* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:END  /* case */
   return( 0 );
//       
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dAddress( VIEW mOrganization BASED ON LOD mOrganization,
//:          STRING ( 32 ) InternalEntityStructure,
//:          STRING ( 32 ) InternalAttribStructure,
//:          SHORT GetOrSetFlag )
//:   VIEW mOrg BASED ON LOD mOrganization
public int 
omOrganization_dAddress( View     mOrganization,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   zVIEW    mOrg = new zVIEW( );
   //:STRING ( 254 ) szDisplay
   String   szDisplay = null;
   //:STRING ( 254 ) szString
   String   szString = null;
   //:STRING ( 254 ) szFirstName
   String   szFirstName = null;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:CreateViewFromView( mOrg, mOrganization )
         CreateViewFromView( mOrg, mOrganization );
         //:SET CURSOR FIRST mOrg.Address WHERE mOrg.Address.IsPrimary = "Y" 
         RESULT = SetCursorFirstEntityByString( mOrg, "Address", "IsPrimary", "Y", "" );
         //:szDisplay = ""
          {StringBuilder sb_szDisplay;
         if ( szDisplay == null )
            sb_szDisplay = new StringBuilder( 32 );
         else
            sb_szDisplay = new StringBuilder( szDisplay );
                  ZeidonStringCopy( sb_szDisplay, 1, 0, "", 1, 0, 255 );
         szDisplay = sb_szDisplay.toString( );}
         //:GetStringFromAttribute( szString, mOrg, "Address", "Line1" )
         {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetStringFromAttribute( sb_szString, mOrg, "Address", "Line1" );
         szString = sb_szString.toString( );}
         //:IF szString != ""
         if ( ZeidonStringCompare( szString, 1, 0, "", 1, 0, 255 ) != 0 )
         { 
            //:szDisplay = szDisplay + szString + ", "
             {StringBuilder sb_szDisplay;
            if ( szDisplay == null )
               sb_szDisplay = new StringBuilder( 32 );
            else
               sb_szDisplay = new StringBuilder( szDisplay );
                        ZeidonStringConcat( sb_szDisplay, 1, 0, szString, 1, 0, 255 );
            szDisplay = sb_szDisplay.toString( );}
             {StringBuilder sb_szDisplay;
            if ( szDisplay == null )
               sb_szDisplay = new StringBuilder( 32 );
            else
               sb_szDisplay = new StringBuilder( szDisplay );
                        ZeidonStringConcat( sb_szDisplay, 1, 0, ", ", 1, 0, 255 );
            szDisplay = sb_szDisplay.toString( );}
         } 

         //:END 
         //:GetStringFromAttribute( szString, mOrg, "Address", "Line2" )
         {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetStringFromAttribute( sb_szString, mOrg, "Address", "Line2" );
         szString = sb_szString.toString( );}
         //:IF szString != ""
         if ( ZeidonStringCompare( szString, 1, 0, "", 1, 0, 255 ) != 0 )
         { 
            //:szDisplay = szDisplay + szString + ", "
             {StringBuilder sb_szDisplay;
            if ( szDisplay == null )
               sb_szDisplay = new StringBuilder( 32 );
            else
               sb_szDisplay = new StringBuilder( szDisplay );
                        ZeidonStringConcat( sb_szDisplay, 1, 0, szString, 1, 0, 255 );
            szDisplay = sb_szDisplay.toString( );}
             {StringBuilder sb_szDisplay;
            if ( szDisplay == null )
               sb_szDisplay = new StringBuilder( 32 );
            else
               sb_szDisplay = new StringBuilder( szDisplay );
                        ZeidonStringConcat( sb_szDisplay, 1, 0, ", ", 1, 0, 255 );
            szDisplay = sb_szDisplay.toString( );}
         } 

         //:END 
         //:GetStringFromAttribute( szString, mOrg, "Address", "City" )
         {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetStringFromAttribute( sb_szString, mOrg, "Address", "City" );
         szString = sb_szString.toString( );}
         //:IF szString != ""
         if ( ZeidonStringCompare( szString, 1, 0, "", 1, 0, 255 ) != 0 )
         { 
            //:szDisplay = szDisplay + szString + ", "
             {StringBuilder sb_szDisplay;
            if ( szDisplay == null )
               sb_szDisplay = new StringBuilder( 32 );
            else
               sb_szDisplay = new StringBuilder( szDisplay );
                        ZeidonStringConcat( sb_szDisplay, 1, 0, szString, 1, 0, 255 );
            szDisplay = sb_szDisplay.toString( );}
             {StringBuilder sb_szDisplay;
            if ( szDisplay == null )
               sb_szDisplay = new StringBuilder( 32 );
            else
               sb_szDisplay = new StringBuilder( szDisplay );
                        ZeidonStringConcat( sb_szDisplay, 1, 0, ", ", 1, 0, 255 );
            szDisplay = sb_szDisplay.toString( );}
         } 

         //:END 
         //:GetStringFromAttribute( szString, mOrg, "StateProvince", "StateProvinceCode" )
         {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetStringFromAttribute( sb_szString, mOrg, "StateProvince", "StateProvinceCode" );
         szString = sb_szString.toString( );}
         //:IF szString != ""
         if ( ZeidonStringCompare( szString, 1, 0, "", 1, 0, 255 ) != 0 )
         { 
            //:szDisplay = szDisplay + szString + " "
             {StringBuilder sb_szDisplay;
            if ( szDisplay == null )
               sb_szDisplay = new StringBuilder( 32 );
            else
               sb_szDisplay = new StringBuilder( szDisplay );
                        ZeidonStringConcat( sb_szDisplay, 1, 0, szString, 1, 0, 255 );
            szDisplay = sb_szDisplay.toString( );}
             {StringBuilder sb_szDisplay;
            if ( szDisplay == null )
               sb_szDisplay = new StringBuilder( 32 );
            else
               sb_szDisplay = new StringBuilder( szDisplay );
                        ZeidonStringConcat( sb_szDisplay, 1, 0, " ", 1, 0, 255 );
            szDisplay = sb_szDisplay.toString( );}
         } 

         //:END 
         //:GetStringFromAttribute( szString, mOrg, "Address", "PostalCode" )
         {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetStringFromAttribute( sb_szString, mOrg, "Address", "PostalCode" );
         szString = sb_szString.toString( );}
         //:IF szString != ""
         if ( ZeidonStringCompare( szString, 1, 0, "", 1, 0, 255 ) != 0 )
         { 
            //:szDisplay = szDisplay + szString + " "
             {StringBuilder sb_szDisplay;
            if ( szDisplay == null )
               sb_szDisplay = new StringBuilder( 32 );
            else
               sb_szDisplay = new StringBuilder( szDisplay );
                        ZeidonStringConcat( sb_szDisplay, 1, 0, szString, 1, 0, 255 );
            szDisplay = sb_szDisplay.toString( );}
             {StringBuilder sb_szDisplay;
            if ( szDisplay == null )
               sb_szDisplay = new StringBuilder( 32 );
            else
               sb_szDisplay = new StringBuilder( szDisplay );
                        ZeidonStringConcat( sb_szDisplay, 1, 0, " ", 1, 0, 255 );
            szDisplay = sb_szDisplay.toString( );}
         } 

         //:END 
         //:StoreStringInRecord( mOrganization,
         //:                  InternalEntityStructure,
         //:                  InternalAttribStructure,
         //:                  szDisplay )
         StoreStringInRecord( mOrganization, InternalEntityStructure, InternalAttribStructure, szDisplay );
         break ;

      //:  /* end zDE        /* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:END  /* case */
   return( 0 );
//       
// END
} 


//:OBJECT CONSTRAINT OPERATION
//:OContraintAC( VIEW mOrganization BASED ON LOD mOrganization,
//:              SHORT Event, SHORT State )
//:   VIEW mOrgOrig     BASED ON LOD mOrgSmall
public int 
omOrganization_OContraintAC( View     mOrganization,
                             Integer   Event,
                             Integer   State )
{
   zVIEW    mOrgOrig = new zVIEW( );
   //:VIEW mCurrentUser REGISTERED AS mCurrentUser 
   zVIEW    mCurrentUser = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wXferO       REGISTERED AS wXferO 
   zVIEW    wXferO = new zVIEW( );
   //:INTEGER nRCE
   int      nRCE = 0;
   //:INTEGER nRCA
   int      nRCA = 0;
   //:INTEGER nRC 
   int      nRC = 0;
   //:STRING ( 255 ) szOriginalValue
   String   szOriginalValue = null;
   //:STRING ( 255 ) szNewValue
   String   szNewValue = null;
   //:STRING ( 50 ) szAttributeName
   String   szAttributeName = null;
   //:STRING ( 50 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 50 ) szNow
   String   szNow = null;
   //:STRING ( 50 ) szUser
   String   szUser = null;
   //:STRING ( 50 ) szPhoneA
   String   szPhoneA = null;
   //:STRING ( 50 ) szPhoneB
   String   szPhoneB = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_5 = 0;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   int      lTempInteger_6 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_7 = 0;
   String   szTempString_4 = null;
   int      lTempInteger_8 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_9 = 0;

   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", mOrganization, zLEVEL_TASK );
   RESULT = GetViewByName( wXferO, "wXferO", mOrganization, zLEVEL_TASK );

   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :
         //:TraceLineI( "----------->TRACE STATE IN ACTIVATE::", State )
         TraceLineI( "----------->TRACE STATE IN ACTIVATE::", State );
         //:IF mOrganization.Organization EXISTS 
         lTempInteger_0 = CheckExistenceOfEntity( mOrganization, "Organization" );
         if ( lTempInteger_0 == 0 )
         { 
            //:CREATE ENTITY mOrganization.OriginalOrg 
            RESULT = CreateEntity( mOrganization, "OriginalOrg", zPOS_AFTER );
            //:SetMatchingAttributesByName( mOrganization, "OriginalOrg",
            //:                          mOrganization, "Organization", zSET_ALL )
            SetMatchingAttributesByName( mOrganization, "OriginalOrg", mOrganization, "Organization", zSET_ALL );
         } 

         //:END 
         //:IF mOrganization.ChangeLog EXISTS 
         lTempInteger_1 = CheckExistenceOfEntity( mOrganization, "ChangeLog" );
         if ( lTempInteger_1 == 0 )
         { 
            //:OrderEntityForView( mOrganization, "ChangeLog", "ChangeDateTime D")
            OrderEntityForView( mOrganization, "ChangeLog", "ChangeDateTime D" );
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
         //:szNow = wXferO.Root.dCurrentDateTime 
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szNow;
         if ( szNow == null )
            sb_szNow = new StringBuilder( 32 );
         else
            sb_szNow = new StringBuilder( szNow );
                   GetVariableFromAttribute( sb_szNow, mi_lTempInteger_2, 'S', 51, wXferO, "Root", "dCurrentDateTime", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szNow = sb_szNow.toString( );}
         //:szUser = mCurrentUser.User.UserName 
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szUser;
         if ( szUser == null )
            sb_szUser = new StringBuilder( 32 );
         else
            sb_szUser = new StringBuilder( szUser );
                   GetVariableFromAttribute( sb_szUser, mi_lTempInteger_3, 'S', 51, mCurrentUser, "User", "UserName", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         szUser = sb_szUser.toString( );}
         //:GET VIEW mOrgOrig NAMED "mOrgOrig"
         RESULT = GetViewByName( mOrgOrig, "mOrgOrig", mOrganization, zLEVEL_TASK );
         //:IF RESULT > 0 
         if ( RESULT > 0 )
         { 
            //:// Check for changes 
            //:// OrganizationName 
            //:OrderEntityForView( mOrganization, "ChangeLog", "ChangeDateTime D")
            OrderEntityForView( mOrganization, "ChangeLog", "ChangeDateTime D" );
            //:// check for changes to organization
            //:nRCA = zGetFirstAttributeNameForEntity( mOrgOrig, "Organization", szAttributeName )
            {StringBuilder sb_szAttributeName;
            if ( szAttributeName == null )
               sb_szAttributeName = new StringBuilder( 32 );
            else
               sb_szAttributeName = new StringBuilder( szAttributeName );
                         nRCA = zGetFirstAttributeNameForEntity( mOrgOrig, "Organization", sb_szAttributeName );
            szAttributeName = sb_szAttributeName.toString( );}
            //:LOOP WHILE nRCA >= 0
            while ( nRCA >= 0 )
            { 
               //:GetStringFromAttributeByContext( szOriginalValue, mOrgOrig, "Organization", szAttributeName, "",255 )
               {StringBuilder sb_szOriginalValue;
               if ( szOriginalValue == null )
                  sb_szOriginalValue = new StringBuilder( 32 );
               else
                  sb_szOriginalValue = new StringBuilder( szOriginalValue );
                               GetStringFromAttributeByContext( sb_szOriginalValue, mOrgOrig, "Organization", szAttributeName, "", 255 );
               szOriginalValue = sb_szOriginalValue.toString( );}
               //:GetStringFromAttributeByContext( szNewValue, mOrganization, "Organization", szAttributeName, "",255 )
               {StringBuilder sb_szNewValue;
               if ( szNewValue == null )
                  sb_szNewValue = new StringBuilder( 32 );
               else
                  sb_szNewValue = new StringBuilder( szNewValue );
                               GetStringFromAttributeByContext( sb_szNewValue, mOrganization, "Organization", szAttributeName, "", 255 );
               szNewValue = sb_szNewValue.toString( );}
               //:IF szNewValue != szOriginalValue
               if ( ZeidonStringCompare( szNewValue, 1, 0, szOriginalValue, 1, 0, 256 ) != 0 )
               { 
                  //:CreateChangeLog( mOrganization, szUser, szNow, "Organization", szAttributeName, szOriginalValue, szNewValue )
                  omOrganization_CreateChangeLog( mOrganization, szUser, szNow, "Organization", szAttributeName, szOriginalValue, szNewValue );
               } 

               //:END 
               //:nRCA = zGetNextAttributeNameForEntity( mOrgOrig, "Organization", szAttributeName )
               {StringBuilder sb_szAttributeName;
               if ( szAttributeName == null )
                  sb_szAttributeName = new StringBuilder( 32 );
               else
                  sb_szAttributeName = new StringBuilder( szAttributeName );
                               nRCA = zGetNextAttributeNameForEntity( mOrgOrig, "Organization", sb_szAttributeName );
               szAttributeName = sb_szAttributeName.toString( );}
            } 

            //:END
            //:// check for changes to Address
            //:FOR EACH mOrgOrig.Address 
            RESULT = SetCursorFirstEntity( mOrgOrig, "Address", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:SET CURSOR FIRST mOrganization.Address 
               //:WHERE mOrganization.Address.AddressID = mOrgOrig.Address.AddressID 
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                               GetIntegerFromAttribute( mi_lTempInteger_4, mOrgOrig, "Address", "AddressID" );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mOrganization, "Address", "AddressID", lTempInteger_4, "" );
               //:IF RESULT >= zCURSOR_SET 
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:nRCA = zGetFirstAttributeNameForEntity( mOrgOrig, "Address", szAttributeName )
                  {StringBuilder sb_szAttributeName;
                  if ( szAttributeName == null )
                     sb_szAttributeName = new StringBuilder( 32 );
                  else
                     sb_szAttributeName = new StringBuilder( szAttributeName );
                                     nRCA = zGetFirstAttributeNameForEntity( mOrgOrig, "Address", sb_szAttributeName );
                  szAttributeName = sb_szAttributeName.toString( );}
                  //:LOOP WHILE nRCA >= 0 
                  while ( nRCA >= 0 )
                  { 
                     //:IF szAttributeName != "AddressID" 
                     if ( ZeidonStringCompare( szAttributeName, 1, 0, "AddressID", 1, 0, 51 ) != 0 )
                     { 
                        //:GetStringFromAttributeByContext( szOriginalValue, mOrgOrig, "Address", szAttributeName, "",255 )
                        {StringBuilder sb_szOriginalValue;
                        if ( szOriginalValue == null )
                           sb_szOriginalValue = new StringBuilder( 32 );
                        else
                           sb_szOriginalValue = new StringBuilder( szOriginalValue );
                                                 GetStringFromAttributeByContext( sb_szOriginalValue, mOrgOrig, "Address", szAttributeName, "", 255 );
                        szOriginalValue = sb_szOriginalValue.toString( );}
                        //:GetStringFromAttributeByContext( szNewValue, mOrganization, "Address", szAttributeName, "",255 )
                        {StringBuilder sb_szNewValue;
                        if ( szNewValue == null )
                           sb_szNewValue = new StringBuilder( 32 );
                        else
                           sb_szNewValue = new StringBuilder( szNewValue );
                                                 GetStringFromAttributeByContext( sb_szNewValue, mOrganization, "Address", szAttributeName, "", 255 );
                        szNewValue = sb_szNewValue.toString( );}
                        //:IF szNewValue != szOriginalValue
                        if ( ZeidonStringCompare( szNewValue, 1, 0, szOriginalValue, 1, 0, 256 ) != 0 )
                        { 
                           //:CreateChangeLog( mOrganization, szUser, szNow, "Address", szAttributeName, szOriginalValue, szNewValue )
                           omOrganization_CreateChangeLog( mOrganization, szUser, szNow, "Address", szAttributeName, szOriginalValue, szNewValue );
                        } 

                        //:END 
                     } 

                     //:END
                     //:nRCA = zGetNextAttributeNameForEntity( mOrgOrig, "Address", szAttributeName )
                     {StringBuilder sb_szAttributeName;
                     if ( szAttributeName == null )
                        sb_szAttributeName = new StringBuilder( 32 );
                     else
                        sb_szAttributeName = new StringBuilder( szAttributeName );
                                           nRCA = zGetNextAttributeNameForEntity( mOrgOrig, "Address", sb_szAttributeName );
                     szAttributeName = sb_szAttributeName.toString( );}
                  } 

                  //:END
                  //:ELSE 
               } 
               else
               { 
                  //:CreateChangeLog( mOrganization, szUser, szNow, "Address", mOrgOrig.Address.Line1, "", "Deleted")
                  {StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                     GetStringFromAttribute( sb_szTempString_0, mOrgOrig, "Address", "Line1" );
                  szTempString_0 = sb_szTempString_0.toString( );}
                  omOrganization_CreateChangeLog( mOrganization, szUser, szNow, "Address", szTempString_0, "", "Deleted" );
               } 

               RESULT = SetCursorNextEntity( mOrgOrig, "Address", "" );
               //:END
            } 

            //:END
            //:// check for changes to ClientContact
            //:FOR EACH mOrgOrig.ClientContact
            RESULT = SetCursorFirstEntity( mOrgOrig, "ClientContact", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:SET CURSOR FIRST mOrganization.ClientContact
               //:WHERE mOrganization.ClientContact.ClientContactID = mOrgOrig.ClientContact.ClientContactID 
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                               GetIntegerFromAttribute( mi_lTempInteger_5, mOrgOrig, "ClientContact", "ClientContactID" );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mOrganization, "ClientContact", "ClientContactID", lTempInteger_5, "" );
               //:IF RESULT >= zCURSOR_SET 
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:nRCA = zGetFirstAttributeNameForEntity( mOrgOrig, "ClientContact", szAttributeName )
                  {StringBuilder sb_szAttributeName;
                  if ( szAttributeName == null )
                     sb_szAttributeName = new StringBuilder( 32 );
                  else
                     sb_szAttributeName = new StringBuilder( szAttributeName );
                                     nRCA = zGetFirstAttributeNameForEntity( mOrgOrig, "ClientContact", sb_szAttributeName );
                  szAttributeName = sb_szAttributeName.toString( );}
                  //:LOOP WHILE nRCA >= 0 
                  while ( nRCA >= 0 )
                  { 
                     //:IF szAttributeName != "ClientContactID"
                     if ( ZeidonStringCompare( szAttributeName, 1, 0, "ClientContactID", 1, 0, 51 ) != 0 )
                     { 
                        //:GetStringFromAttributeByContext( szOriginalValue, mOrgOrig, "ClientContact", szAttributeName, "",255 )
                        {StringBuilder sb_szOriginalValue;
                        if ( szOriginalValue == null )
                           sb_szOriginalValue = new StringBuilder( 32 );
                        else
                           sb_szOriginalValue = new StringBuilder( szOriginalValue );
                                                 GetStringFromAttributeByContext( sb_szOriginalValue, mOrgOrig, "ClientContact", szAttributeName, "", 255 );
                        szOriginalValue = sb_szOriginalValue.toString( );}
                        //:GetStringFromAttributeByContext( szNewValue, mOrganization, "ClientContact", szAttributeName, "",255 )
                        {StringBuilder sb_szNewValue;
                        if ( szNewValue == null )
                           sb_szNewValue = new StringBuilder( 32 );
                        else
                           sb_szNewValue = new StringBuilder( szNewValue );
                                                 GetStringFromAttributeByContext( sb_szNewValue, mOrganization, "ClientContact", szAttributeName, "", 255 );
                        szNewValue = sb_szNewValue.toString( );}
                        //:IF szNewValue != szOriginalValue
                        if ( ZeidonStringCompare( szNewValue, 1, 0, szOriginalValue, 1, 0, 256 ) != 0 )
                        { 
                           //:CreateChangeLog( mOrganization, szUser, szNow, "ClientContact", szAttributeName, szOriginalValue, szNewValue )
                           omOrganization_CreateChangeLog( mOrganization, szUser, szNow, "ClientContact", szAttributeName, szOriginalValue, szNewValue );
                        } 

                        //:END 
                     } 

                     //:END 
                     //:nRCA = zGetNextAttributeNameForEntity( mOrgOrig, "ClientContact", szAttributeName )
                     {StringBuilder sb_szAttributeName;
                     if ( szAttributeName == null )
                        sb_szAttributeName = new StringBuilder( 32 );
                     else
                        sb_szAttributeName = new StringBuilder( szAttributeName );
                                           nRCA = zGetNextAttributeNameForEntity( mOrgOrig, "ClientContact", sb_szAttributeName );
                     szAttributeName = sb_szAttributeName.toString( );}
                  } 

                  //:END
                  //:nRCA = zGetFirstAttributeNameForEntity( mOrgOrig, "Person", szAttributeName )
                  {StringBuilder sb_szAttributeName;
                  if ( szAttributeName == null )
                     sb_szAttributeName = new StringBuilder( 32 );
                  else
                     sb_szAttributeName = new StringBuilder( szAttributeName );
                                     nRCA = zGetFirstAttributeNameForEntity( mOrgOrig, "Person", sb_szAttributeName );
                  szAttributeName = sb_szAttributeName.toString( );}
                  //:LOOP WHILE nRCA >= 0
                  while ( nRCA >= 0 )
                  { 
                     //:IF szAttributeName != "PersonID"
                     if ( ZeidonStringCompare( szAttributeName, 1, 0, "PersonID", 1, 0, 51 ) != 0 )
                     { 
                        //:GetStringFromAttributeByContext( szOriginalValue, mOrgOrig, "Person", szAttributeName, "",255 )
                        {StringBuilder sb_szOriginalValue;
                        if ( szOriginalValue == null )
                           sb_szOriginalValue = new StringBuilder( 32 );
                        else
                           sb_szOriginalValue = new StringBuilder( szOriginalValue );
                                                 GetStringFromAttributeByContext( sb_szOriginalValue, mOrgOrig, "Person", szAttributeName, "", 255 );
                        szOriginalValue = sb_szOriginalValue.toString( );}
                        //:GetStringFromAttributeByContext( szNewValue, mOrganization, "Person", szAttributeName, "",255 )
                        {StringBuilder sb_szNewValue;
                        if ( szNewValue == null )
                           sb_szNewValue = new StringBuilder( 32 );
                        else
                           sb_szNewValue = new StringBuilder( szNewValue );
                                                 GetStringFromAttributeByContext( sb_szNewValue, mOrganization, "Person", szAttributeName, "", 255 );
                        szNewValue = sb_szNewValue.toString( );}
                        //:IF szNewValue != szOriginalValue
                        if ( ZeidonStringCompare( szNewValue, 1, 0, szOriginalValue, 1, 0, 256 ) != 0 )
                        { 
                           //:CreateChangeLog( mOrganization, szUser, szNow, "Person", szAttributeName, szOriginalValue, szNewValue )
                           omOrganization_CreateChangeLog( mOrganization, szUser, szNow, "Person", szAttributeName, szOriginalValue, szNewValue );
                        } 

                        //:END 
                     } 

                     //:END 
                     //:nRCA = zGetNextAttributeNameForEntity( mOrgOrig, "Person", szAttributeName )
                     {StringBuilder sb_szAttributeName;
                     if ( szAttributeName == null )
                        sb_szAttributeName = new StringBuilder( 32 );
                     else
                        sb_szAttributeName = new StringBuilder( szAttributeName );
                                           nRCA = zGetNextAttributeNameForEntity( mOrgOrig, "Person", sb_szAttributeName );
                     szAttributeName = sb_szAttributeName.toString( );}
                  } 

                  //:END
                  //:ELSE 
               } 
               else
               { 
                  //:CreateChangeLog( mOrganization, szUser, szNow, "ClientContact", mOrgOrig.Person.LastName, mOrgOrig.Person.FirstName, "Deleted")
                  {StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetStringFromAttribute( sb_szTempString_1, mOrgOrig, "Person", "LastName" );
                  szTempString_1 = sb_szTempString_1.toString( );}
                  {StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                     GetStringFromAttribute( sb_szTempString_2, mOrgOrig, "Person", "FirstName" );
                  szTempString_2 = sb_szTempString_2.toString( );}
                  omOrganization_CreateChangeLog( mOrganization, szUser, szNow, "ClientContact", szTempString_1, szTempString_2, "Deleted" );
               } 

               RESULT = SetCursorNextEntity( mOrgOrig, "ClientContact", "" );
               //:END
            } 

            //:END
            //:// check for changes to services
            //:FOR EACH mOrgOrig.Service
            RESULT = SetCursorFirstEntity( mOrgOrig, "Service", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:SET CURSOR FIRST mOrganization.Service 
               //:WHERE mOrganization.Service.ServiceID = mOrgOrig.Service.ServiceID 
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                               GetIntegerFromAttribute( mi_lTempInteger_6, mOrgOrig, "Service", "ServiceID" );
               lTempInteger_6 = mi_lTempInteger_6.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mOrganization, "Service", "ServiceID", lTempInteger_6, "" );
               //:IF RESULT >= zCURSOR_SET 
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:nRCA = zGetFirstAttributeNameForEntity( mOrgOrig, "Service", szAttributeName )
                  {StringBuilder sb_szAttributeName;
                  if ( szAttributeName == null )
                     sb_szAttributeName = new StringBuilder( 32 );
                  else
                     sb_szAttributeName = new StringBuilder( szAttributeName );
                                     nRCA = zGetFirstAttributeNameForEntity( mOrgOrig, "Service", sb_szAttributeName );
                  szAttributeName = sb_szAttributeName.toString( );}
                  //:LOOP WHILE nRCA >= 0
                  while ( nRCA >= 0 )
                  { 
                     //:GetStringFromAttributeByContext( szOriginalValue, mOrgOrig, "Service", szAttributeName, "",255 )
                     {StringBuilder sb_szOriginalValue;
                     if ( szOriginalValue == null )
                        sb_szOriginalValue = new StringBuilder( 32 );
                     else
                        sb_szOriginalValue = new StringBuilder( szOriginalValue );
                                           GetStringFromAttributeByContext( sb_szOriginalValue, mOrgOrig, "Service", szAttributeName, "", 255 );
                     szOriginalValue = sb_szOriginalValue.toString( );}
                     //:GetStringFromAttributeByContext( szNewValue, mOrganization, "Service", szAttributeName, "",255 )
                     {StringBuilder sb_szNewValue;
                     if ( szNewValue == null )
                        sb_szNewValue = new StringBuilder( 32 );
                     else
                        sb_szNewValue = new StringBuilder( szNewValue );
                                           GetStringFromAttributeByContext( sb_szNewValue, mOrganization, "Service", szAttributeName, "", 255 );
                     szNewValue = sb_szNewValue.toString( );}
                     //:IF szNewValue != szOriginalValue
                     if ( ZeidonStringCompare( szNewValue, 1, 0, szOriginalValue, 1, 0, 256 ) != 0 )
                     { 
                        //:CreateChangeLog( mOrganization, szUser, szNow, "Service", szAttributeName, szOriginalValue, szNewValue )
                        omOrganization_CreateChangeLog( mOrganization, szUser, szNow, "Service", szAttributeName, szOriginalValue, szNewValue );
                     } 

                     //:END 
                     //:nRCA = zGetNextAttributeNameForEntity( mOrgOrig, "Service", szAttributeName )
                     {StringBuilder sb_szAttributeName;
                     if ( szAttributeName == null )
                        sb_szAttributeName = new StringBuilder( 32 );
                     else
                        sb_szAttributeName = new StringBuilder( szAttributeName );
                                           nRCA = zGetNextAttributeNameForEntity( mOrgOrig, "Service", sb_szAttributeName );
                     szAttributeName = sb_szAttributeName.toString( );}
                  } 

                  //:END
                  //:ELSE 
               } 
               else
               { 
                  //:CreateChangeLog( mOrganization, szUser, szNow, "Service", "ServiceName", mOrgOrig.Service.ServiceName, "Deleted")
                  {StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                     GetStringFromAttribute( sb_szTempString_3, mOrgOrig, "Service", "ServiceName" );
                  szTempString_3 = sb_szTempString_3.toString( );}
                  omOrganization_CreateChangeLog( mOrganization, szUser, szNow, "Service", "ServiceName", szTempString_3, "Deleted" );
               } 

               RESULT = SetCursorNextEntity( mOrgOrig, "Service", "" );
               //:END
            } 

            //:END
            //:// check for changes to AccountRole 
            //:FOR EACH mOrgOrig.AccountRole
            RESULT = SetCursorFirstEntity( mOrgOrig, "AccountRole", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:SET CURSOR FIRST mOrganization.AccountRole
               //:WHERE mOrganization.AccountRole.AccountRoleID = mOrgOrig.AccountRole.AccountRoleID 
               {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                               GetIntegerFromAttribute( mi_lTempInteger_7, mOrgOrig, "AccountRole", "AccountRoleID" );
               lTempInteger_7 = mi_lTempInteger_7.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mOrganization, "AccountRole", "AccountRoleID", lTempInteger_7, "" );
               //:IF RESULT >= zCURSOR_SET 
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:nRCA = zGetFirstAttributeNameForEntity( mOrgOrig, "AccountRole", szAttributeName )
                  {StringBuilder sb_szAttributeName;
                  if ( szAttributeName == null )
                     sb_szAttributeName = new StringBuilder( 32 );
                  else
                     sb_szAttributeName = new StringBuilder( szAttributeName );
                                     nRCA = zGetFirstAttributeNameForEntity( mOrgOrig, "AccountRole", sb_szAttributeName );
                  szAttributeName = sb_szAttributeName.toString( );}
                  //:LOOP WHILE nRCA >= 0
                  while ( nRCA >= 0 )
                  { 
                     //:GetStringFromAttributeByContext( szOriginalValue, mOrgOrig, "AccountRole", szAttributeName, "",255 )
                     {StringBuilder sb_szOriginalValue;
                     if ( szOriginalValue == null )
                        sb_szOriginalValue = new StringBuilder( 32 );
                     else
                        sb_szOriginalValue = new StringBuilder( szOriginalValue );
                                           GetStringFromAttributeByContext( sb_szOriginalValue, mOrgOrig, "AccountRole", szAttributeName, "", 255 );
                     szOriginalValue = sb_szOriginalValue.toString( );}
                     //:GetStringFromAttributeByContext( szNewValue, mOrganization, "AccountRole", szAttributeName, "",255 )
                     {StringBuilder sb_szNewValue;
                     if ( szNewValue == null )
                        sb_szNewValue = new StringBuilder( 32 );
                     else
                        sb_szNewValue = new StringBuilder( szNewValue );
                                           GetStringFromAttributeByContext( sb_szNewValue, mOrganization, "AccountRole", szAttributeName, "", 255 );
                     szNewValue = sb_szNewValue.toString( );}
                     //:IF szNewValue != szOriginalValue
                     if ( ZeidonStringCompare( szNewValue, 1, 0, szOriginalValue, 1, 0, 256 ) != 0 )
                     { 
                        //:CreateChangeLog( mOrganization, szUser, szNow, "AccountRole", szAttributeName, szOriginalValue, szNewValue )
                        omOrganization_CreateChangeLog( mOrganization, szUser, szNow, "AccountRole", szAttributeName, szOriginalValue, szNewValue );
                     } 

                     //:END 
                     //:nRCA = zGetNextAttributeNameForEntity( mOrgOrig, "AccountRole", szAttributeName )
                     {StringBuilder sb_szAttributeName;
                     if ( szAttributeName == null )
                        sb_szAttributeName = new StringBuilder( 32 );
                     else
                        sb_szAttributeName = new StringBuilder( szAttributeName );
                                           nRCA = zGetNextAttributeNameForEntity( mOrgOrig, "AccountRole", sb_szAttributeName );
                     szAttributeName = sb_szAttributeName.toString( );}
                  } 

                  //:END
                  //:nRCA = zGetFirstAttributeNameForEntity( mOrgOrig, "AccountRoleUser", szAttributeName )
                  {StringBuilder sb_szAttributeName;
                  if ( szAttributeName == null )
                     sb_szAttributeName = new StringBuilder( 32 );
                  else
                     sb_szAttributeName = new StringBuilder( szAttributeName );
                                     nRCA = zGetFirstAttributeNameForEntity( mOrgOrig, "AccountRoleUser", sb_szAttributeName );
                  szAttributeName = sb_szAttributeName.toString( );}
                  //:LOOP WHILE nRCA >= 0
                  while ( nRCA >= 0 )
                  { 
                     //:IF szAttributeName != "UserID" AND szAttributeName != "eMailAddress" 
                     if ( ZeidonStringCompare( szAttributeName, 1, 0, "UserID", 1, 0, 51 ) != 0 && ZeidonStringCompare( szAttributeName, 1, 0, "eMailAddress", 1, 0, 51 ) != 0 )
                     { 
                        //:GetStringFromAttributeByContext( szOriginalValue, mOrgOrig, "AccountRoleUser", szAttributeName, "",255 )
                        {StringBuilder sb_szOriginalValue;
                        if ( szOriginalValue == null )
                           sb_szOriginalValue = new StringBuilder( 32 );
                        else
                           sb_szOriginalValue = new StringBuilder( szOriginalValue );
                                                 GetStringFromAttributeByContext( sb_szOriginalValue, mOrgOrig, "AccountRoleUser", szAttributeName, "", 255 );
                        szOriginalValue = sb_szOriginalValue.toString( );}
                        //:GetStringFromAttributeByContext( szNewValue, mOrganization, "AccountRoleUser", szAttributeName, "",255 )
                        {StringBuilder sb_szNewValue;
                        if ( szNewValue == null )
                           sb_szNewValue = new StringBuilder( 32 );
                        else
                           sb_szNewValue = new StringBuilder( szNewValue );
                                                 GetStringFromAttributeByContext( sb_szNewValue, mOrganization, "AccountRoleUser", szAttributeName, "", 255 );
                        szNewValue = sb_szNewValue.toString( );}
                        //:IF szNewValue != szOriginalValue
                        if ( ZeidonStringCompare( szNewValue, 1, 0, szOriginalValue, 1, 0, 256 ) != 0 )
                        { 
                           //:CreateChangeLog( mOrganization, szUser, szNow, "AccountRoleUser", szAttributeName, szOriginalValue, szNewValue )
                           omOrganization_CreateChangeLog( mOrganization, szUser, szNow, "AccountRoleUser", szAttributeName, szOriginalValue, szNewValue );
                        } 

                        //:END 
                     } 

                     //:end 
                     //:nRCA = zGetNextAttributeNameForEntity( mOrgOrig, "AccountRoleUser", szAttributeName )
                     {StringBuilder sb_szAttributeName;
                     if ( szAttributeName == null )
                        sb_szAttributeName = new StringBuilder( 32 );
                     else
                        sb_szAttributeName = new StringBuilder( szAttributeName );
                                           nRCA = zGetNextAttributeNameForEntity( mOrgOrig, "AccountRoleUser", sb_szAttributeName );
                     szAttributeName = sb_szAttributeName.toString( );}
                  } 

                  //:END
                  //:ELSE 
               } 
               else
               { 
                  //:CreateChangeLog( mOrganization, szUser, szNow, "AccountRoleUser", "UserName", mOrgOrig.AccountRoleUser.UserName, "Deleted")
                  {StringBuilder sb_szTempString_4;
                  if ( szTempString_4 == null )
                     sb_szTempString_4 = new StringBuilder( 32 );
                  else
                     sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                     GetStringFromAttribute( sb_szTempString_4, mOrgOrig, "AccountRoleUser", "UserName" );
                  szTempString_4 = sb_szTempString_4.toString( );}
                  omOrganization_CreateChangeLog( mOrganization, szUser, szNow, "AccountRoleUser", "UserName", szTempString_4, "Deleted" );
               } 

               RESULT = SetCursorNextEntity( mOrgOrig, "AccountRole", "" );
               //:END
            } 

            //:END
            //:DropView( mOrgOrig )
            DropView( mOrgOrig );
            //:ACTIVATE  mOrgOrig  
            //:WHERE mOrgOrig.Organization.OrganizationID = mOrganization.Organization.OrganizationID 
            {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                         GetIntegerFromAttribute( mi_lTempInteger_8, mOrganization, "Organization", "OrganizationID" );
            lTempInteger_8 = mi_lTempInteger_8.intValue( );}
            omOrganization_fnLocalBuildQual_0( mOrganization, vTempViewVar_0, lTempInteger_8 );
            RESULT = ActivateObjectInstance( mOrgOrig, "mOrgSmall", mOrganization, vTempViewVar_0, zSINGLE );
            DropView( vTempViewVar_0 );
            //:NAME VIEW  mOrgOrig  "mOrgOrig"
            SetNameForView( mOrgOrig, "mOrgOrig", null, zLEVEL_TASK );

            //:IF mOrganization.ChangeLog EXISTS 
            lTempInteger_9 = CheckExistenceOfEntity( mOrganization, "ChangeLog" );
            if ( lTempInteger_9 == 0 )
            { 
               //:OrderEntityForView( mOrganization, "ChangeLog", "ChangeDateTime D")
               OrderEntityForView( mOrganization, "ChangeLog", "ChangeDateTime D" );
            } 

            //:END
         } 

         //:   
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


//:LOCAL OPERATION
public void 
omOrganization_CreateChangeLog( View     mOrganization,
                                String   szUser,
                                String   szDateTime,
                                String   szEntityName,
                                String   szAttributeName,
                                String   szOldValue,
                                String   szNewValue )
{
   int      RESULT = 0;

   //:CreateChangeLog( VIEW mOrganization BASED ON LOD mOrganization,
   //:              STRING ( 32 ) szUser,
   //:              STRING ( 32 ) szDateTime,
   //:              STRING ( 32 ) szEntityName,
   //:              STRING ( 32 ) szAttributeName,
   //:              STRING ( 32 ) szOldValue,
   //:              STRING ( 32 ) szNewValue )

   //:                  
   //: CREATE ENTITY  mOrganization.ChangeLog BEFORE 
   RESULT = CreateEntity( mOrganization, "ChangeLog", zPOS_BEFORE );
   //: //mOrganization.ChangeLog.ChangeDateTime = szDateTime 
   //: SetAttributeFromCurrentDateTime( mOrganization, "ChangeLog", "ChangeDateTime")
   {
    icaGlobal1_Operation m_icaGlobal1_Operation = new icaGlobal1_Operation( mOrganization );
    m_icaGlobal1_Operation.SetAttributeFromCurrentDateTime( mOrganization, "ChangeLog", "ChangeDateTime" );
    // m_icaGlobal1_Operation = null;  // permit gc  (unnecessary)
   }
   //: mOrganization.ChangeLog.ChangeUser = szUser
   SetAttributeFromString( mOrganization, "ChangeLog", "ChangeUser", szUser );
   //: mOrganization.ChangeLog.EntityName = szEntityName
   SetAttributeFromString( mOrganization, "ChangeLog", "EntityName", szEntityName );
   //: mOrganization.ChangeLog.AttributeName = szAttributeName
   SetAttributeFromString( mOrganization, "ChangeLog", "AttributeName", szAttributeName );
   //: mOrganization.ChangeLog.OldValue = szOldValue
   SetAttributeFromString( mOrganization, "ChangeLog", "OldValue", szOldValue );
   //: mOrganization.ChangeLog.NewValue = szNewValue
   SetAttributeFromString( mOrganization, "ChangeLog", "NewValue", szNewValue );
   return;
//     
// END
} 



}
