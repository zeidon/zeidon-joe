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

public class mSubProd_Object extends VmlObjectOperations
{
   public mSubProd_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dMasterProductNameNbr( VIEW mSubProd BASED ON LOD mSubProd,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   STRING ( 1000 ) szString
public int 
omSubProd_dMasterProductNameNbr( View     mSubProd,
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


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mSubProd.MasterProduct  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSubProd, "MasterProduct" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSubProd.MasterProduct.Name + " (" +
            //:        mSubProd.PrimaryRegistrant.EPA_CompanyNumber + "-" +
            //:        mSubProd.MasterProduct.Number + ")"
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttribute( sb_szString, mSubProd, "MasterProduct", "Name" );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, " (", 1, 0, 1001 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 7, mSubProd, "PrimaryRegistrant", "EPA_CompanyNumber", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 1001 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, "-", 1, 0, 1001 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 6, mSubProd, "MasterProduct", "Number", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
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
         //:StoreStringInRecord( mSubProd,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mSubProd, InternalEntityStructure, InternalAttribStructure, szString );
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
   //:dSubregNameID( VIEW mSubProd BASED ON LOD mSubProd,
   //:            STRING ( 32 ) InternalEntityStructure,
   //:            STRING ( 32 ) InternalAttribStructure,
   //:            SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
omSubProd_dSubregNameID( View     mSubProd,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   String   szString = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mSubProd.SubregOrganization EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSubProd, "SubregOrganization" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSubProd.SubregOrganization.Name
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 1001, mSubProd, "SubregOrganization", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
            //:IF mSubProd.Subregistrant.EPA_CompanyNumber = ""
            if ( CompareAttributeToString( mSubProd, "Subregistrant", "EPA_CompanyNumber", "" ) == 0 )
            { 
               //:szString = szString + " (Non EPA)"
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, " (Non EPA)", 1, 0, 1001 );
               szString = sb_szString.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szString = szString + " (" +
               //:     mSubProd.Subregistrant.EPA_CompanyNumber + ")"
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, " (", 1, 0, 1001 );
               szString = sb_szString.toString( );}
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 7, mSubProd, "Subregistrant", "EPA_CompanyNumber", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 1001 );
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
         //:StoreStringInRecord( mSubProd,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mSubProd, InternalEntityStructure, InternalAttribStructure, szString );
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
   //:dPrimRegNameID( VIEW mSubProd BASED ON LOD mSubProd,
   //:             STRING ( 32 ) InternalEntityStructure,
   //:             STRING ( 32 ) InternalAttribStructure,
   //:             SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
omSubProd_dPrimRegNameID( View     mSubProd,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   String   szString = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mSubProd.SubregOrganization  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSubProd, "SubregOrganization" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSubProd.SubregOrganization.Name
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 1001, mSubProd, "SubregOrganization", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
            //:IF mSubProd.PrimaryRegistrant.EPA_CompanyNumber != ""
            if ( CompareAttributeToString( mSubProd, "PrimaryRegistrant", "EPA_CompanyNumber", "" ) != 0 )
            { 
               //:szString = szString + " (" +
               //:          mSubProd.PrimaryRegistrant.EPA_CompanyNumber + ")"
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, " (", 1, 0, 1001 );
               szString = sb_szString.toString( );}
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 7, mSubProd, "PrimaryRegistrant", "EPA_CompanyNumber", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 1001 );
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
         //:StoreStringInRecord( mSubProd,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mSubProd, InternalEntityStructure, InternalAttribStructure, szString );
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
   //:dSubregProductNameNbr( VIEW mSubProd BASED ON LOD mSubProd,
   //:                    STRING ( 32 ) InternalEntityStructure,
   //:                    STRING ( 32 ) InternalAttribStructure,
   //:                    SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
omSubProd_dSubregProductNameNbr( View     mSubProd,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   String   szString = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:szString = mSubProd.SubregProduct.Name
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetVariableFromAttribute( sb_szString, mi_lTempInteger_0, 'S', 1001, mSubProd, "SubregProduct", "Name", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szString = sb_szString.toString( );}
         //:IF mSubProd.SubregProduct.Number != 0
         if ( CompareAttributeToInteger( mSubProd, "SubregProduct", "Number", 0 ) != 0 )
         { 
            //:szString = szString + " (" + mSubProd.SubregProduct.Number + ")"
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, " (", 1, 0, 1001 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 6, mSubProd, "SubregProduct", "Number", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 1001 );
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

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSubProd,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mSubProd, InternalEntityStructure, InternalAttribStructure, szString );
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
