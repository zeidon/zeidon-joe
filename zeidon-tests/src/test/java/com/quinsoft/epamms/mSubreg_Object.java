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

import com.quinsoft.epamms.ZGlobal1_Operation;

/**
   @author QuinSoft
**/

public class mSubreg_Object extends VmlObjectOperations
{
   public mSubreg_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
public int 
omSubreg_dFullNameLFM( View     mSubreg,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{

   //:dFullNameLFM( VIEW mSubreg BASED ON LOD mSubreg ,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:PersonName_LastFirstMiddle( mSubreg , InternalEntityStructure,
         //:                           InternalAttribStructure, GetOrSetFlag )
         {
          ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSubreg );
          m_ZGlobal1_Operation.PersonName_LastFirstMiddle( mSubreg, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
          // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
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
public int 
omSubreg_dFullNameFML( View     mSubreg,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{

   //:dFullNameFML( VIEW mSubreg BASED ON LOD mSubreg ,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:PersonName_FirstMiddleLast( mSubreg , InternalEntityStructure,
         //:                           InternalAttribStructure, GetOrSetFlag )
         {
          ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSubreg );
          m_ZGlobal1_Operation.PersonName_FirstMiddleLast( mSubreg, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
          // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
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
//:dPrimRegNameID( VIEW mSubreg BASED ON LOD mSubreg,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   STRING ( 1000 ) szString
public int 
omSubreg_dPrimRegNameID( View     mSubreg,
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

         //:IF mSubreg.Organization  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSubreg, "Organization" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSubreg.Organization.Name
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 1001, mSubreg, "Organization", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
            //:IF mSubreg.PrimaryRegistrant.EPA_CompanyNumber != ""
            if ( CompareAttributeToString( mSubreg, "PrimaryRegistrant", "EPA_CompanyNumber", "" ) != 0 )
            { 
               //:szString = szString + " (" +
               //:          mSubreg.PrimaryRegistrant.EPA_CompanyNumber + ")"
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
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 7, mSubreg, "PrimaryRegistrant", "EPA_CompanyNumber", "", 0 );
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
         //:StoreStringInRecord( mSubreg,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mSubreg, InternalEntityStructure, InternalAttribStructure, szString );
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
//:dSubregNameID( VIEW mSubreg BASED ON LOD mSubreg,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   STRING ( 1000 ) szString
public int 
omSubreg_dSubregNameID( View     mSubreg,
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

         //:IF mSubreg.SubregOrganization EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSubreg, "SubregOrganization" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSubreg.SubregOrganization.Name
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 1001, mSubreg, "SubregOrganization", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
            //:IF mSubreg.Subregistrant.EPA_CompanyNumber != ""
            if ( CompareAttributeToString( mSubreg, "Subregistrant", "EPA_CompanyNumber", "" ) != 0 )
            { 
               //:szString = szString + " (" +
               //:           mSubreg.Subregistrant.EPA_CompanyNumber + ")"
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
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 7, mSubreg, "Subregistrant", "EPA_CompanyNumber", "", 0 );
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
         //:StoreStringInRecord( mSubreg,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mSubreg, InternalEntityStructure, InternalAttribStructure, szString );
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
   //:dSubregProductNameNbr( VIEW mSubreg BASED ON LOD mSubreg,
   //:                    STRING ( 32 ) InternalEntityStructure,
   //:                    STRING ( 32 ) InternalAttribStructure,
   //:                    SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
omSubreg_dSubregProductNameNbr( View     mSubreg,
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

         //:szString = mSubreg.SubregProduct.Name
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetVariableFromAttribute( sb_szString, mi_lTempInteger_0, 'S', 1001, mSubreg, "SubregProduct", "Name", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szString = sb_szString.toString( );}
         //:IF mSubreg.SubregProduct.Number != 0
         if ( CompareAttributeToInteger( mSubreg, "SubregProduct", "Number", 0 ) != 0 )
         { 
            //:szString = szString + " (" + mSubreg.SubregProduct.Number + ")"
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
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 6, mSubreg, "SubregProduct", "Number", "", 0 );
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
         //:StoreStringInRecord( mSubreg,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mSubreg, InternalEntityStructure, InternalAttribStructure, szString );
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
   //:dMasterProductNameNbr( VIEW mSubreg BASED ON LOD mSubreg,
   //:                    STRING ( 32 ) InternalEntityStructure,
   //:                    STRING ( 32 ) InternalAttribStructure,
   //:                    SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
omSubreg_dMasterProductNameNbr( View     mSubreg,
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

         //:IF mSubreg.MasterProduct  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSubreg, "MasterProduct" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSubreg.MasterProduct.Name + " (" +
            //:        mSubreg.PrimaryRegistrant.EPA_CompanyNumber + "-" +
            //:        mSubreg.MasterProduct.Number + ")"
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttribute( sb_szString, mSubreg, "MasterProduct", "Name" );
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
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 7, mSubreg, "PrimaryRegistrant", "EPA_CompanyNumber", "", 0 );
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
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 6, mSubreg, "MasterProduct", "Number", "", 0 );
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
         //:StoreStringInRecord( mSubreg,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mSubreg, InternalEntityStructure, InternalAttribStructure, szString );
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
