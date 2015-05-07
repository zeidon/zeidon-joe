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

package com.quinsoft.epamms;

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
   @author QuinSoft
**/

public class qOrganiz_Object extends VmlObjectOperations
{
   public qOrganiz_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dLoginUserName( VIEW qOrganiz BASED ON LOD qOrganiz,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   STRING ( 1000 ) szString
public int 
oqOrganiz_dLoginUserName( View     qOrganiz,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   String   szString = null;
   //:STRING (   32 ) szRole
   String   szRole = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_5 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF qOrganiz.Organization  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( qOrganiz, "Organization" );
         if ( lTempInteger_0 == 0 )
         { 
            //:GetStringFromAttributeByContext( szString, qOrganiz, "Organization", "Role", "RegistrantRole", 32 )
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttributeByContext( sb_szString, qOrganiz, "Organization", "Role", "RegistrantRole", 32 );
            szString = sb_szString.toString( );}
            //:szString = szString + ": " + qOrganiz.Organization.Name
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, ": ", 1, 0, 1001 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 255, qOrganiz, "Organization", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 1001 );
            szString = sb_szString.toString( );}
            //:IF qOrganiz.PrimaryRegistrant EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( qOrganiz, "PrimaryRegistrant" );
            if ( lTempInteger_2 == 0 )
            { 
               //:IF qOrganiz.PrimaryRegistrant.EPA_CompanyNumber != ""
               if ( CompareAttributeToString( qOrganiz, "PrimaryRegistrant", "EPA_CompanyNumber", "" ) != 0 )
               { 
                  //:szString = szString + " (" +
                  //:     qOrganiz.PrimaryRegistrant.EPA_CompanyNumber + ")"
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, " (", 1, 0, 1001 );
                  szString = sb_szString.toString( );}
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_3, 'S', 7, qOrganiz, "PrimaryRegistrant", "EPA_CompanyNumber", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szTempString_1 = sb_szTempString_1.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, szTempString_1, 1, 0, 1001 );
                  szString = sb_szString.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, ")", 1, 0, 1001 );
                  szString = sb_szString.toString( );}
               } 

               //:END
            } 

            //:END

            //:IF qOrganiz.Subregistrant EXISTS
            lTempInteger_4 = CheckExistenceOfEntity( qOrganiz, "Subregistrant" );
            if ( lTempInteger_4 == 0 )
            { 
               //:IF qOrganiz.Subregistrant.EPA_CompanyNumber != ""
               if ( CompareAttributeToString( qOrganiz, "Subregistrant", "EPA_CompanyNumber", "" ) != 0 )
               { 
                  //:szString = szString + " (" +
                  //:     qOrganiz.Subregistrant.EPA_CompanyNumber + ")"
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, " (", 1, 0, 1001 );
                  szString = sb_szString.toString( );}
                  {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                  StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                     GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_5, 'S', 7, qOrganiz, "Subregistrant", "EPA_CompanyNumber", "", 0 );
                  lTempInteger_5 = mi_lTempInteger_5.intValue( );
                  szTempString_2 = sb_szTempString_2.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, szTempString_2, 1, 0, 1001 );
                  szString = sb_szString.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, ")", 1, 0, 1001 );
                  szString = sb_szString.toString( );}
               } 

               //:END
            } 

            //:END

            //:ELSE
         } 
         else
         { 
            //:szString = ""
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringCopy( sb_szString, 1, 0, "", 1, 0, 1001 );
            szString = sb_szString.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( qOrganiz,
         //:                  InternalEntityStructure,
         //:                  InternalAttribStructure, szString )
         StoreStringInRecord( qOrganiz, InternalEntityStructure, InternalAttribStructure, szString );
         //:RETURN 0
         if(8==8)return( 0 );
         //:/* end zDERIVED_GET */
         //:OF   zDERIVED_SET:
         case zDERIVED_SET :
            break ;
      } 


      //:  /* end zDERIVED_SET */
      //:END  /* case */
      return( 0 );
   } 


   //:DERIVED ATTRIBUTE OPERATION
   //:dLoginUserRole( VIEW qOrganiz BASED ON LOD qOrganiz,
   //:             STRING ( 32 ) InternalEntityStructure,
   //:             STRING ( 32 ) InternalAttribStructure,
   //:             SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
oqOrganiz_dLoginUserRole( View     qOrganiz,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   String   szString = null;
   //:STRING (   32 ) szRole
   String   szRole = null;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF qOrganiz.Organization  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( qOrganiz, "Organization" );
         if ( lTempInteger_0 == 0 )
         { 
            //:GetStringFromAttributeByContext( szRole, qOrganiz, "Organization", "Role", "RegistrantRole", 32 )
            {StringBuilder sb_szRole;
            if ( szRole == null )
               sb_szRole = new StringBuilder( 32 );
            else
               sb_szRole = new StringBuilder( szRole );
                         GetStringFromAttributeByContext( sb_szRole, qOrganiz, "Organization", "Role", "RegistrantRole", 32 );
            szRole = sb_szRole.toString( );}
            //:szString = "Role: " + szRole
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringCopy( sb_szString, 1, 0, "Role: ", 1, 0, 1001 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szRole, 1, 0, 1001 );
            szString = sb_szString.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szString = ""
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringCopy( sb_szString, 1, 0, "", 1, 0, 1001 );
            szString = sb_szString.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( qOrganiz,
         //:                  InternalEntityStructure,
         //:                  InternalAttribStructure, szString )
         StoreStringInRecord( qOrganiz, InternalEntityStructure, InternalAttribStructure, szString );
         //:RETURN 0
         if(8==8)return( 0 );
         //:/* end zDERIVED_GET */
         //:OF   zDERIVED_SET:
         case zDERIVED_SET :
            break ;
      } 


      //:  /* end zDERIVED_SET */
      //:END  /* case */
      return( 0 );
   } 


   //:DERIVED ATTRIBUTE OPERATION
   //:dLoginUserNameRole( VIEW qOrganiz BASED ON LOD qOrganiz,
   //:                 STRING ( 32 ) InternalEntityStructure,
   //:                 STRING ( 32 ) InternalAttribStructure,
   //:                 SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
oqOrganiz_dLoginUserNameRole( View     qOrganiz,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szString = null;
   //:STRING (   32 ) szRole
   String   szRole = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_7 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF qOrganiz.Organization  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( qOrganiz, "Organization" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = "Registrant: " + qOrganiz.Organization.Name
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 255, qOrganiz, "Organization", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringCopy( sb_szString, 1, 0, "Registrant: ", 1, 0, 1001 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 1001 );
            szString = sb_szString.toString( );}
            //:IF qOrganiz.PrimaryRegistrant EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( qOrganiz, "PrimaryRegistrant" );
            if ( lTempInteger_2 == 0 )
            { 
               //:IF qOrganiz.PrimaryRegistrant.EPA_CompanyNumber != ""
               if ( CompareAttributeToString( qOrganiz, "PrimaryRegistrant", "EPA_CompanyNumber", "" ) != 0 )
               { 
                  //:szString = szString + " (" +
                  //:     qOrganiz.PrimaryRegistrant.EPA_CompanyNumber + ")"
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, " (", 1, 0, 1001 );
                  szString = sb_szString.toString( );}
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_3, 'S', 7, qOrganiz, "PrimaryRegistrant", "EPA_CompanyNumber", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szTempString_1 = sb_szTempString_1.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, szTempString_1, 1, 0, 1001 );
                  szString = sb_szString.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, ")", 1, 0, 1001 );
                  szString = sb_szString.toString( );}
               } 

               //:END
            } 

            //:END

            //:IF qOrganiz.Subregistrant EXISTS
            lTempInteger_4 = CheckExistenceOfEntity( qOrganiz, "Subregistrant" );
            if ( lTempInteger_4 == 0 )
            { 
               //:IF qOrganiz.Subregistrant.EPA_CompanyNumber != ""
               if ( CompareAttributeToString( qOrganiz, "Subregistrant", "EPA_CompanyNumber", "" ) != 0 )
               { 
                  //:szString = szString + " (" + qOrganiz.Subregistrant.EPA_CompanyNumber + ")"
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, " (", 1, 0, 1001 );
                  szString = sb_szString.toString( );}
                  {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                  StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                     GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_5, 'S', 7, qOrganiz, "Subregistrant", "EPA_CompanyNumber", "", 0 );
                  lTempInteger_5 = mi_lTempInteger_5.intValue( );
                  szTempString_2 = sb_szTempString_2.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, szTempString_2, 1, 0, 1001 );
                  szString = sb_szString.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, ")", 1, 0, 1001 );
                  szString = sb_szString.toString( );}
               } 

               //:END
            } 


            //:END

            //:GetStringFromAttributeByContext( szRole, qOrganiz, "Organization", "Role", "RegistrantRole", 32 )
            {StringBuilder sb_szRole;
            if ( szRole == null )
               sb_szRole = new StringBuilder( 32 );
            else
               sb_szRole = new StringBuilder( szRole );
                         GetStringFromAttributeByContext( sb_szRole, qOrganiz, "Organization", "Role", "RegistrantRole", 32 );
            szRole = sb_szRole.toString( );}
            //:szString = szString + "    Role: " + szRole
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, "    Role: ", 1, 0, 1001 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szRole, 1, 0, 1001 );
            szString = sb_szString.toString( );}
            //:IF szRole = "Subregistrant"
            if ( ZeidonStringCompare( szRole, 1, 0, "Subregistrant", 1, 0, 33 ) == 0 )
            { 
               //:IF qOrganiz.RelatedPrimaryRegistrant EXISTS
               lTempInteger_6 = CheckExistenceOfEntity( qOrganiz, "RelatedPrimaryRegistrant" );
               if ( lTempInteger_6 == 0 )
               { 
                  //:szString = szString + "   -- for Primary Registrant: " + qOrganiz.PrimaryOrganization.Name
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, "   -- for Primary Registrant: ", 1, 0, 1001 );
                  szString = sb_szString.toString( );}
                  {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                  StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                     GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_7, 'S', 255, qOrganiz, "PrimaryOrganization", "Name", "", 0 );
                  lTempInteger_7 = mi_lTempInteger_7.intValue( );
                  szTempString_3 = sb_szTempString_3.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, szTempString_3, 1, 0, 1001 );
                  szString = sb_szString.toString( );}
               } 

               //:END
            } 


            //:END

            //:ELSE
         } 
         else
         { 
            //:szString = ""
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringCopy( sb_szString, 1, 0, "", 1, 0, 1001 );
            szString = sb_szString.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( qOrganiz,
         //:                  InternalEntityStructure,
         //:                  InternalAttribStructure, szString )
         StoreStringInRecord( qOrganiz, InternalEntityStructure, InternalAttribStructure, szString );
         //:RETURN 0
         if(8==8)return( 0 );
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
