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

package com.arksoft.epamms;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.arksoft.epamms.ZGlobal1_Operation;

/**
   @author QuinSoft
**/

public class mSubLC_Object extends VmlObjectOperations
{
   public mSubLC_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dRegistrantNameID( VIEW mSubLC BASED ON LOD mSubLC,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   STRING ( 1000 ) szString
public int 
omSubLC_dRegistrantNameID( View     mSubLC,
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

         //:IF mSubLC.Organization EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSubLC, "Organization" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSubLC.Organization.Name
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 1001, mSubLC, "Organization", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
            //:IF mSubLC.PrimaryRegistrant.EPA_CompanyNumber = ""
            if ( CompareAttributeToString( mSubLC, "PrimaryRegistrant", "EPA_CompanyNumber", "" ) == 0 )
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
               //:     mSubLC.PrimaryRegistrant.EPA_CompanyNumber + ")"
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
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 129, mSubLC, "PrimaryRegistrant", "EPA_CompanyNumber", "", 0 );
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
         //:StoreStringInRecord( mSubLC,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mSubLC, InternalEntityStructure, InternalAttribStructure, szString );
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
   //:dIngredientName( VIEW mSubLC BASED ON LOD mSubLC,
   //:              STRING ( 32 ) InternalEntityStructure,
   //:              STRING ( 32 ) InternalAttribStructure,
   //:              SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
omSubLC_dIngredientName( View     mSubLC,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   String   szString = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mSubLC.S_IngredientsStatement EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSubLC, "S_IngredientsStatement" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSubLC.S_IngredientsStatement.CommonName
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 1001, mSubLC, "S_IngredientsStatement", "CommonName", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
            //:IF szString = ""
            if ( ZeidonStringCompare( szString, 1, 0, "", 1, 0, 1001 ) == 0 )
            { 
               //:szString = mSubLC.S_IngredientsStatement.ChemicalName
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                               GetVariableFromAttribute( sb_szString, mi_lTempInteger_2, 'S', 1001, mSubLC, "S_IngredientsStatement", "ChemicalName", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
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
         //:StoreStringInRecord( mSubLC,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mSubLC, InternalEntityStructure, InternalAttribStructure, szString );
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
public int 
omSubLC_dFullNameLFM( View     mSubLC,
                      String InternalEntityStructure,
                      String InternalAttribStructure,
                      Integer   GetOrSetFlag )
{

   //:dFullNameLFM( VIEW mSubLC BASED ON LOD mSubLC,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:PersonName_LastFirstMiddle( mSubLC, InternalEntityStructure,
         //:                           InternalAttribStructure, GetOrSetFlag )
         {
          ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSubLC );
          m_ZGlobal1_Operation.PersonName_LastFirstMiddle( mSubLC, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
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
omSubLC_dFullNameFML( View     mSubLC,
                      String InternalEntityStructure,
                      String InternalAttribStructure,
                      Integer   GetOrSetFlag )
{

   //:dFullNameFML( VIEW mSubLC BASED ON LOD mSubLC,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:PersonName_FirstMiddleLast( mSubLC, InternalEntityStructure,
         //:                           InternalAttribStructure, GetOrSetFlag )
         {
          ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSubLC );
          m_ZGlobal1_Operation.PersonName_FirstMiddleLast( mSubLC, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
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
//:dMasterProductNameNbr( VIEW mSubLC BASED ON LOD mSubLC,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   STRING ( 1000 ) szString
public int 
omSubLC_dMasterProductNameNbr( View     mSubLC,
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

         //:IF mSubLC.MasterProduct  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSubLC, "MasterProduct" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSubLC.MasterProduct.Name + " (" +
            //:        mSubLC.PrimaryRegistrant.EPA_CompanyNumber + "-" +
            //:        mSubLC.MasterProduct.Number + ")"
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttribute( sb_szString, mSubLC, "MasterProduct", "Name" );
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
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 129, mSubLC, "PrimaryRegistrant", "EPA_CompanyNumber", "", 0 );
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
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 129, mSubLC, "MasterProduct", "Number", "", 0 );
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
         //:StoreStringInRecord( mSubLC,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mSubLC, InternalEntityStructure, InternalAttribStructure, szString );
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
   //:dSubregProductNameNbr( VIEW mSubLC BASED ON LOD mSubLC,
   //:                    STRING ( 32 ) InternalEntityStructure,
   //:                    STRING ( 32 ) InternalAttribStructure,
   //:                    SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
omSubLC_dSubregProductNameNbr( View     mSubLC,
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

         //:szString = mSubLC.SubregProduct.Name
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                   GetVariableFromAttribute( sb_szString, mi_lTempInteger_0, 'S', 1001, mSubLC, "SubregProduct", "Name", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szString = sb_szString.toString( );}
         //:IF mSubLC.SubregProduct.Number != 0
         if ( CompareAttributeToInteger( mSubLC, "SubregProduct", "Number", 0 ) != 0 )
         { 
            //:szString = szString + " (" + mSubLC.SubregProduct.Number + ")"
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
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 129, mSubLC, "SubregProduct", "Number", "", 0 );
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
         //:StoreStringInRecord( mSubLC,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mSubLC, InternalEntityStructure, InternalAttribStructure, szString );
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


   //:TRANSFORMATION OPERATION
   //:BuildNewSLC_Version( VIEW NewSLC      BASED ON LOD mSubLC,
   //:                  VIEW PreviousSLC BASED ON LOD mSubLC,
   //:                  VIEW SourceMLC   BASED ON LOD mMasLC,
   //:                  VIEW PreviousMLC BASED ON LOD lMLCASrc )

   //:// STRING ( 10 ) szVersion
   //:STRING ( 1 )  szEntityFoundFlag
public int 
omSubLC_BuildNewSLC_Version( View     NewSLC,
                             View     PreviousSLC,
                             View     SourceMLC,
                             View     PreviousMLC )
{
   String   szEntityFoundFlag = null;
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


   //:// Create a new SLC from a previous SLC, tying the new back to the original.

   //:// Root and single includes
   //:CREATE ENTITY NewSLC.SubregLabelContent
   RESULT = CreateEntity( NewSLC, "SubregLabelContent", zPOS_AFTER );
   //:SetMatchingAttributesByName( NewSLC, "SubregLabelContent", PreviousSLC, "SubregLabelContent", zSET_NULL )
   SetMatchingAttributesByName( NewSLC, "SubregLabelContent", PreviousSLC, "SubregLabelContent", zSET_NULL );
   //:SetAttributeFromCurrentDateTime( NewSLC, "SubregLabelContent", "RevisionDate" )
   {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( NewSLC );
    m_ZGlobal1_Operation.SetAttributeFromCurrentDateTime( NewSLC, "SubregLabelContent", "RevisionDate" );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   }
   //:// szVersion = NewSLC.SubregLabelContent.RevisionDate
   //:// NewSLC.SubregLabelContent.Version = szVersion  already done in calling routine
   //:INCLUDE NewSLC.SubregProduct FROM PreviousSLC.SubregProduct
   RESULT = IncludeSubobjectFromSubobject( NewSLC, "SubregProduct", PreviousSLC, "SubregProduct", zPOS_AFTER );
   //:INCLUDE NewSLC.SP_SubregLabelContent FROM PreviousSLC.SubregLabelContent
   RESULT = IncludeSubobjectFromSubobject( NewSLC, "SP_SubregLabelContent", PreviousSLC, "SubregLabelContent", zPOS_AFTER );
   //:INCLUDE NewSLC.MasterLabelContent FROM SourceMLC.MasterLabelContent
   RESULT = IncludeSubobjectFromSubobject( NewSLC, "MasterLabelContent", SourceMLC, "MasterLabelContent", zPOS_AFTER );

   //:// S_Usage
   //:// Usages come from the MLC.
   //:FOR EACH SourceMLC.M_Usage
   RESULT = SetCursorFirstEntity( SourceMLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSLC.S_Usage
      RESULT = CreateEntity( NewSLC, "S_Usage", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSLC, "S_Usage", SourceMLC, "M_Usage", zSET_NULL )
      SetMatchingAttributesByName( NewSLC, "S_Usage", SourceMLC, "M_Usage", zSET_NULL );
      RESULT = SetCursorNextEntity( SourceMLC, "M_Usage", "" );
   } 

   //:END

   //:// S_GeneralSection
   //:// General Sections must come entirely from the source MLC.
   //:FOR EACH SourceMLC.M_GeneralSection
   RESULT = SetCursorFirstEntity( SourceMLC, "M_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSLC.S_GeneralSection
      RESULT = CreateEntity( NewSLC, "S_GeneralSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSLC, "S_GeneralSection", SourceMLC, "M_GeneralSection", zSET_NULL )
      SetMatchingAttributesByName( NewSLC, "S_GeneralSection", SourceMLC, "M_GeneralSection", zSET_NULL );
      //:INCLUDE NewSLC.M_GeneralSection FROM SourceMLC.M_GeneralSection
      RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_GeneralSection", SourceMLC, "M_GeneralSection", zPOS_AFTER );
      //:FOR EACH SourceMLC.M_GeneralStatement
      RESULT = SetCursorFirstEntity( SourceMLC, "M_GeneralStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSLC.S_GeneralStatement
         RESULT = CreateEntity( NewSLC, "S_GeneralStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSLC, "S_GeneralStatement", SourceMLC, "M_GeneralStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSLC, "S_GeneralStatement", SourceMLC, "M_GeneralStatement", zSET_NULL );
         //:INCLUDE NewSLC.M_GeneralStatement FROM SourceMLC.M_GeneralStatement
         RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_GeneralStatement", SourceMLC, "M_GeneralStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceMLC, "M_GeneralStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceMLC, "M_GeneralSection", "" );
      //:END
   } 

   //:END

   //:// S_IngredientsSection
   //:// The Ingredients Sections must come entirely from the source MLC.
   //:FOR EACH SourceMLC.M_IngredientsSection
   RESULT = SetCursorFirstEntity( SourceMLC, "M_IngredientsSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSLC.S_IngredientsSection
      RESULT = CreateEntity( NewSLC, "S_IngredientsSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSLC, "S_IngredientsSection", SourceMLC, "M_IngredientsSection", zSET_NULL )
      SetMatchingAttributesByName( NewSLC, "S_IngredientsSection", SourceMLC, "M_IngredientsSection", zSET_NULL );
      //:INCLUDE NewSLC.M_IngredientsSection FROM SourceMLC.M_IngredientsSection
      RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_IngredientsSection", SourceMLC, "M_IngredientsSection", zPOS_AFTER );
      //:FOR EACH SourceMLC.M_IngredientsStatement
      RESULT = SetCursorFirstEntity( SourceMLC, "M_IngredientsStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSLC.S_IngredientsStatement
         RESULT = CreateEntity( NewSLC, "S_IngredientsStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSLC, "S_IngredientsStatement", SourceMLC, "M_IngredientsStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSLC, "S_IngredientsStatement", SourceMLC, "M_IngredientsStatement", zSET_NULL );
         //:INCLUDE NewSLC.M_IngredientsStatement FROM SourceMLC.M_IngredientsStatement
         RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_IngredientsStatement", SourceMLC, "M_IngredientsStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceMLC, "M_IngredientsStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceMLC, "M_IngredientsSection", "" );
      //:END
   } 

   //:END

   //:// S_StorageDisposalSection
   //:// StorageDisposal Sections must come from the source MLC, except that, which Sections to use come from the previous SLC.
   //:FOR EACH PreviousSLC.S_StorageDisposalSection
   RESULT = SetCursorFirstEntity( PreviousSLC, "S_StorageDisposalSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST PreviousMLC.M_StorageDisposalSection WHERE PreviousMLC.M_StorageDisposalSection.ID = PreviousSLC.M_StorageDisposalSection.ID
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, PreviousSLC, "M_StorageDisposalSection", "ID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( PreviousMLC, "M_StorageDisposalSection", "ID", lTempInteger_0, "" );
      //:IF PreviousMLC.MN_StorageDisposalSection EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( PreviousMLC, "MN_StorageDisposalSection" );
      if ( lTempInteger_1 == 0 )
      { 
         //:SET CURSOR FIRST SourceMLC.M_StorageDisposalSection WHERE SourceMLC.M_StorageDisposalSection.ID = PreviousMLC.MN_StorageDisposalSection.ID
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                   GetIntegerFromAttribute( mi_lTempInteger_2, PreviousMLC, "MN_StorageDisposalSection", "ID" );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( SourceMLC, "M_StorageDisposalSection", "ID", lTempInteger_2, "" );
         //:// Now just copy the entries from the MLC StorageDisposal Section.
         //:CREATE ENTITY NewSLC.S_StorageDisposalSection
         RESULT = CreateEntity( NewSLC, "S_StorageDisposalSection", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSLC, "S_StorageDisposalSection", SourceMLC, "M_StorageDisposalSection", zSET_NULL )
         SetMatchingAttributesByName( NewSLC, "S_StorageDisposalSection", SourceMLC, "M_StorageDisposalSection", zSET_NULL );
         //:INCLUDE NewSLC.M_StorageDisposalSection FROM SourceMLC.M_StorageDisposalSection
         RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_StorageDisposalSection", SourceMLC, "M_StorageDisposalSection", zPOS_AFTER );
         //:FOR EACH SourceMLC.M_StorageDisposalStatement
         RESULT = SetCursorFirstEntity( SourceMLC, "M_StorageDisposalStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY NewSLC.S_StorageDisposalStatement
            RESULT = CreateEntity( NewSLC, "S_StorageDisposalStatement", zPOS_AFTER );
            //:SetMatchingAttributesByName( NewSLC, "S_StorageDisposalStatement", SourceMLC, "M_StorageDisposalStatement", zSET_NULL )
            SetMatchingAttributesByName( NewSLC, "S_StorageDisposalStatement", SourceMLC, "M_StorageDisposalStatement", zSET_NULL );
            //:INCLUDE NewSLC.M_StorageDisposalStatement FROM SourceMLC.M_StorageDisposalStatement
            RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_StorageDisposalStatement", SourceMLC, "M_StorageDisposalStatement", zPOS_AFTER );
            RESULT = SetCursorNextEntity( SourceMLC, "M_StorageDisposalStatement", "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( PreviousSLC, "S_StorageDisposalSection", "" );
      //:END
   } 

   //:END

   //:// S_DirectionsForUseSection
   //:// DirectionsForUse Sections must come from the source MLC, except for the Usages, which come from the previous SLC.
   //:FOR EACH SourceMLC.M_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( SourceMLC, "M_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSLC.S_DirectionsForUseSection
      RESULT = CreateEntity( NewSLC, "S_DirectionsForUseSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSLC, "S_DirectionsForUseSection", SourceMLC, "M_DirectionsForUseSection", zSET_NULL )
      SetMatchingAttributesByName( NewSLC, "S_DirectionsForUseSection", SourceMLC, "M_DirectionsForUseSection", zSET_NULL );
      //:INCLUDE NewSLC.M_DirectionsForUseSection FROM SourceMLC.M_DirectionsForUseSection
      RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_DirectionsForUseSection", SourceMLC, "M_DirectionsForUseSection", zPOS_AFTER );
      //:FOR EACH SourceMLC.M_DirectionsForUseStatement
      RESULT = SetCursorFirstEntity( SourceMLC, "M_DirectionsForUseStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSLC.S_DirectionsForUseStatement
         RESULT = CreateEntity( NewSLC, "S_DirectionsForUseStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSLC, "S_DirectionsForUseStatement", SourceMLC, "M_DirectionsForUseStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSLC, "S_DirectionsForUseStatement", SourceMLC, "M_DirectionsForUseStatement", zSET_NULL );
         //:INCLUDE NewSLC.M_DirectionsForUseStatement FROM SourceMLC.M_DirectionsForUseStatement
         RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_DirectionsForUseStatement", SourceMLC, "M_DirectionsForUseStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceMLC, "M_DirectionsForUseStatement", "" );
      } 

      //:END

      //:// Proceed back to PreviousMLC and then to previous SLC to get DirectionsUsages.
      //:// If a DirectionsForUse section is not in either the previous MLC or the SLC, we'll use the intersection of Usages from the
      //:// source MLC DirectionsForUse and the SLC.
      //:szEntityFoundFlag = ""
       {StringBuilder sb_szEntityFoundFlag;
      if ( szEntityFoundFlag == null )
         sb_szEntityFoundFlag = new StringBuilder( 32 );
      else
         sb_szEntityFoundFlag = new StringBuilder( szEntityFoundFlag );
            ZeidonStringCopy( sb_szEntityFoundFlag, 1, 0, "", 1, 0, 2 );
      szEntityFoundFlag = sb_szEntityFoundFlag.toString( );}
      //:IF SourceMLC.MP_DirectionsForUseSection EXISTS
      lTempInteger_3 = CheckExistenceOfEntity( SourceMLC, "MP_DirectionsForUseSection" );
      if ( lTempInteger_3 == 0 )
      { 
         //:SET CURSOR FIRST PreviousSLC.M_DirectionsForUseStatement WITHIN PreviousSLC.SubregLabelContent
         //:           WHERE PreviousSLC.M_DirectionsForUseSection.ID = SourceMLC.MP_DirectionsForUseSection.ID
         RESULT = SetCursorFirstEntity( PreviousSLC, "M_DirectionsForUseStatement", "SubregLabelContent" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( PreviousSLC, "M_DirectionsForUseSection", "ID", SourceMLC, "MP_DirectionsForUseSection", "ID" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( PreviousSLC, "M_DirectionsForUseStatement", "SubregLabelContent" );
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:szEntityFoundFlag = "Y"
             {StringBuilder sb_szEntityFoundFlag;
            if ( szEntityFoundFlag == null )
               sb_szEntityFoundFlag = new StringBuilder( 32 );
            else
               sb_szEntityFoundFlag = new StringBuilder( szEntityFoundFlag );
                        ZeidonStringCopy( sb_szEntityFoundFlag, 1, 0, "Y", 1, 0, 2 );
            szEntityFoundFlag = sb_szEntityFoundFlag.toString( );}
         } 

         //:END
      } 

      //:END
      //:IF szEntityFoundFlag = "Y"
      if ( ZeidonStringCompare( szEntityFoundFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:// The previous DirectionsForUse section exists, so get the Usages from the corresponding SLC DirectionsForUse entry that are
         //:// also in the NewSLC and thereby also in the Source MLC. .
         //:// The OriginalDirectionsUsage entries, however, will just come directly from the MLC.
         //:FOR EACH PreviousSLC.S_DirectionsUsage WITHIN PreviousSLC.S_DirectionsForUseSection
         RESULT = SetCursorFirstEntity( PreviousSLC, "S_DirectionsUsage", "S_DirectionsForUseSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST NewSLC.S_Usage WHERE NewSLC.S_Usage.UsageType = PreviousSLC.S_DirectionsUsage.UsageType
            //:                                  AND NewSLC.S_Usage.Name = PreviousSLC.S_DirectionsUsage.Name
            RESULT = SetCursorFirstEntity( NewSLC, "S_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSLC, "S_Usage", "UsageType", PreviousSLC, "S_DirectionsUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( NewSLC, "S_Usage", "Name", PreviousSLC, "S_DirectionsUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( NewSLC, "S_Usage", "" );
               } 

            } 

            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:CREATE ENTITY NewSLC.S_DirectionsUsageOrdering
               RESULT = CreateEntity( NewSLC, "S_DirectionsUsageOrdering", zPOS_AFTER );
               //:INCLUDE NewSLC.S_DirectionsUsage FROM NewSLC.S_Usage
               RESULT = IncludeSubobjectFromSubobject( NewSLC, "S_DirectionsUsage", NewSLC, "S_Usage", zPOS_AFTER );
            } 

            RESULT = SetCursorNextEntity( PreviousSLC, "S_DirectionsUsage", "S_DirectionsForUseSection" );
            //:END
         } 

         //:END
         //:FOR EACH SourceMLC.M_DirectionsUsage WITHIN SourceMLC.M_DirectionsForUseSection
         RESULT = SetCursorFirstEntity( SourceMLC, "M_DirectionsUsage", "M_DirectionsForUseSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST NewSLC.S_Usage WHERE NewSLC.S_Usage.UsageType = SourceMLC.M_DirectionsUsage.UsageType
            //:                                  AND NewSLC.S_Usage.Name = SourceMLC.M_DirectionsUsage.Name
            RESULT = SetCursorFirstEntity( NewSLC, "S_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSLC, "S_Usage", "UsageType", SourceMLC, "M_DirectionsUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( NewSLC, "S_Usage", "Name", SourceMLC, "M_DirectionsUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( NewSLC, "S_Usage", "" );
               } 

            } 

            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:CREATE ENTITY NewSLC.S_OriginalDirectionUsageOrdering
               RESULT = CreateEntity( NewSLC, "S_OriginalDirectionUsageOrdering", zPOS_AFTER );
               //:INCLUDE NewSLC.S_OriginalDirectionsUsage FROM NewSLC.S_Usage
               RESULT = IncludeSubobjectFromSubobject( NewSLC, "S_OriginalDirectionsUsage", NewSLC, "S_Usage", zPOS_AFTER );
            } 

            RESULT = SetCursorNextEntity( SourceMLC, "M_DirectionsUsage", "M_DirectionsForUseSection" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// The previous DirectionsForUse section does not exist, so use the DirectionsUsages that are defined to the MLC for both the new
         //:// DirectionsForUse entries and the OriginalDirectionsUsage entries.
         //:FOR EACH SourceMLC.M_DirectionsUsage WITHIN SourceMLC.M_DirectionsForUseSection
         RESULT = SetCursorFirstEntity( SourceMLC, "M_DirectionsUsage", "M_DirectionsForUseSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST NewSLC.S_Usage WHERE NewSLC.S_Usage.UsageType = SourceMLC.M_DirectionsUsage.UsageType
            //:                                  AND NewSLC.S_Usage.Name = SourceMLC.M_DirectionsUsage.Name
            RESULT = SetCursorFirstEntity( NewSLC, "S_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSLC, "S_Usage", "UsageType", SourceMLC, "M_DirectionsUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( NewSLC, "S_Usage", "Name", SourceMLC, "M_DirectionsUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( NewSLC, "S_Usage", "" );
               } 

            } 

            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:CREATE ENTITY NewSLC.S_DirectionsUsageOrdering
               RESULT = CreateEntity( NewSLC, "S_DirectionsUsageOrdering", zPOS_AFTER );
               //:INCLUDE NewSLC.S_DirectionsUsage         FROM NewSLC.S_Usage
               RESULT = IncludeSubobjectFromSubobject( NewSLC, "S_DirectionsUsage", NewSLC, "S_Usage", zPOS_AFTER );
               //:CREATE ENTITY NewSLC.S_OriginalDirectionUsageOrdering
               RESULT = CreateEntity( NewSLC, "S_OriginalDirectionUsageOrdering", zPOS_AFTER );
               //:INCLUDE NewSLC.S_OriginalDirectionsUsage FROM NewSLC.S_Usage
               RESULT = IncludeSubobjectFromSubobject( NewSLC, "S_OriginalDirectionsUsage", NewSLC, "S_Usage", zPOS_AFTER );
            } 

            RESULT = SetCursorNextEntity( SourceMLC, "M_DirectionsUsage", "M_DirectionsForUseSection" );
            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( SourceMLC, "M_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// S_MarketingSection
   //:// Marketing Sections come from the previous SLC.
   //:// However, we only build sections and statements that are also in the source MLC.
   //:FOR EACH PreviousSLC.S_MarketingSection
   RESULT = SetCursorFirstEntity( PreviousSLC, "S_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST PreviousMLC.M_MarketingSection WHERE PreviousMLC.M_MarketingSection.ID = PreviousSLC.M_MarketingSection.ID
      {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
             GetIntegerFromAttribute( mi_lTempInteger_4, PreviousSLC, "M_MarketingSection", "ID" );
      lTempInteger_4 = mi_lTempInteger_4.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( PreviousMLC, "M_MarketingSection", "ID", lTempInteger_4, "" );
      //:IF PreviousMLC.MN_MarketingSection EXISTS
      lTempInteger_5 = CheckExistenceOfEntity( PreviousMLC, "MN_MarketingSection" );
      if ( lTempInteger_5 == 0 )
      { 
         //:SET CURSOR FIRST SourceMLC.M_MarketingSection WHERE SourceMLC.M_MarketingSection.ID = PreviousMLC.MN_MarketingSection.ID
         {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                   GetIntegerFromAttribute( mi_lTempInteger_6, PreviousMLC, "MN_MarketingSection", "ID" );
         lTempInteger_6 = mi_lTempInteger_6.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( SourceMLC, "M_MarketingSection", "ID", lTempInteger_6, "" );
         //:CREATE ENTITY NewSLC.S_MarketingSection
         RESULT = CreateEntity( NewSLC, "S_MarketingSection", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSLC, "S_MarketingSection", SourceMLC, "M_MarketingSection", zSET_NULL )
         SetMatchingAttributesByName( NewSLC, "S_MarketingSection", SourceMLC, "M_MarketingSection", zSET_NULL );
         //:INCLUDE NewSLC.M_MarketingSection FROM SourceMLC.M_MarketingSection
         RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_MarketingSection", SourceMLC, "M_MarketingSection", zPOS_AFTER );
         //:INCLUDE NewSLC.SP_MarketingSection FROM PreviousSLC.S_MarketingSection
         RESULT = IncludeSubobjectFromSubobject( NewSLC, "SP_MarketingSection", PreviousSLC, "S_MarketingSection", zPOS_AFTER );

         //:// Statements
         //:FOR EACH PreviousSLC.S_MarketingStatement
         RESULT = SetCursorFirstEntity( PreviousSLC, "S_MarketingStatement", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST PreviousMLC.M_MarketingStatement WHERE PreviousMLC.M_MarketingStatement.ID = PreviousSLC.M_MarketingStatement.ID
            {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                         GetIntegerFromAttribute( mi_lTempInteger_7, PreviousSLC, "M_MarketingStatement", "ID" );
            lTempInteger_7 = mi_lTempInteger_7.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( PreviousMLC, "M_MarketingStatement", "ID", lTempInteger_7, "" );
            //:IF PreviousMLC.MN_MarketingStatement EXISTS
            lTempInteger_8 = CheckExistenceOfEntity( PreviousMLC, "MN_MarketingStatement" );
            if ( lTempInteger_8 == 0 )
            { 
               //:SET CURSOR FIRST SourceMLC.M_MarketingStatement WHERE SourceMLC.M_MarketingStatement.ID = PreviousMLC.MN_MarketingStatement.ID
               {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
                               GetIntegerFromAttribute( mi_lTempInteger_9, PreviousMLC, "MN_MarketingStatement", "ID" );
               lTempInteger_9 = mi_lTempInteger_9.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( SourceMLC, "M_MarketingStatement", "ID", lTempInteger_9, "" );
               //:CREATE ENTITY NewSLC.S_MarketingStatement
               RESULT = CreateEntity( NewSLC, "S_MarketingStatement", zPOS_AFTER );
               //:SetMatchingAttributesByName( NewSLC, "S_MarketingStatement", SourceMLC, "M_MarketingStatement", zSET_NULL )
               SetMatchingAttributesByName( NewSLC, "S_MarketingStatement", SourceMLC, "M_MarketingStatement", zSET_NULL );
               //:INCLUDE NewSLC.M_MarketingStatement FROM SourceMLC.M_MarketingStatement
               RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_MarketingStatement", SourceMLC, "M_MarketingStatement", zPOS_AFTER );
               //:INCLUDE NewSLC.SP_MarketingStatement FROM PreviousSLC.S_MarketingStatement
               RESULT = IncludeSubobjectFromSubobject( NewSLC, "SP_MarketingStatement", PreviousSLC, "S_MarketingStatement", zPOS_AFTER );
            } 

            RESULT = SetCursorNextEntity( PreviousSLC, "S_MarketingStatement", "" );
            //:END
         } 

         //:END

         //:// Usages
         //:// Recreate only MarketingUsages that are in the NewSLC and thereby also in the Source MLC.
         //:// The OriginalMarketingUsage entries, however, will just come directly from the MLC.
         //:FOR EACH PreviousSLC.S_MarketingUsage WITHIN PreviousSLC.S_MarketingSection
         RESULT = SetCursorFirstEntity( PreviousSLC, "S_MarketingUsage", "S_MarketingSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST NewSLC.S_Usage WHERE NewSLC.S_Usage.UsageType = PreviousSLC.S_MarketingUsage.UsageType
            //:                                  AND NewSLC.S_Usage.Name = PreviousSLC.S_MarketingUsage.Name
            RESULT = SetCursorFirstEntity( NewSLC, "S_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSLC, "S_Usage", "UsageType", PreviousSLC, "S_MarketingUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( NewSLC, "S_Usage", "Name", PreviousSLC, "S_MarketingUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( NewSLC, "S_Usage", "" );
               } 

            } 

            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:CREATE ENTITY NewSLC.S_MarketingUsageOrdering
               RESULT = CreateEntity( NewSLC, "S_MarketingUsageOrdering", zPOS_AFTER );
               //:INCLUDE NewSLC.S_MarketingUsage FROM NewSLC.S_Usage
               RESULT = IncludeSubobjectFromSubobject( NewSLC, "S_MarketingUsage", NewSLC, "S_Usage", zPOS_AFTER );
            } 

            RESULT = SetCursorNextEntity( PreviousSLC, "S_MarketingUsage", "S_MarketingSection" );
            //:END
         } 

         //:END
         //:FOR EACH SourceMLC.M_MarketingUsage WITHIN SourceMLC.M_MarketingSection
         RESULT = SetCursorFirstEntity( SourceMLC, "M_MarketingUsage", "M_MarketingSection" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST NewSLC.S_Usage WHERE NewSLC.S_Usage.UsageType = SourceMLC.M_MarketingUsage.UsageType
            //:                                  AND NewSLC.S_Usage.Name = SourceMLC.M_MarketingUsage.Name
            RESULT = SetCursorFirstEntity( NewSLC, "S_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSLC, "S_Usage", "UsageType", SourceMLC, "M_MarketingUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( NewSLC, "S_Usage", "Name", SourceMLC, "M_MarketingUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( NewSLC, "S_Usage", "" );
               } 

            } 

            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:CREATE ENTITY NewSLC.S_OriginalMarketingUsageOrdering
               RESULT = CreateEntity( NewSLC, "S_OriginalMarketingUsageOrdering", zPOS_AFTER );
               //:INCLUDE NewSLC.S_OriginalMarketingUsage FROM NewSLC.S_Usage
               RESULT = IncludeSubobjectFromSubobject( NewSLC, "S_OriginalMarketingUsage", NewSLC, "S_Usage", zPOS_AFTER );
            } 

            RESULT = SetCursorNextEntity( SourceMLC, "M_MarketingUsage", "M_MarketingSection" );
            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( PreviousSLC, "S_MarketingSection", "" );
      //:END
   } 

   //:END

   //:// S_HumanHazardSection
   //:// The HumanHazard Sections must come entirely from the source MLC.
   //:FOR EACH SourceMLC.M_HumanHazardSection
   RESULT = SetCursorFirstEntity( SourceMLC, "M_HumanHazardSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSLC.S_HumanHazardSection
      RESULT = CreateEntity( NewSLC, "S_HumanHazardSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSLC, "S_HumanHazardSection", SourceMLC, "M_HumanHazardSection", zSET_NULL )
      SetMatchingAttributesByName( NewSLC, "S_HumanHazardSection", SourceMLC, "M_HumanHazardSection", zSET_NULL );
      //:INCLUDE NewSLC.M_HumanHazardSection FROM SourceMLC.M_HumanHazardSection
      RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_HumanHazardSection", SourceMLC, "M_HumanHazardSection", zPOS_AFTER );
      RESULT = SetCursorNextEntity( SourceMLC, "M_HumanHazardSection", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildSLC_FromMLC( VIEW NewSLC    BASED ON LOD mSubLC,
//:                  VIEW SourceMLC BASED ON LOD mMasLC )

//:   STRING ( 32 ) szVersion
public int 
omSubLC_BuildSLC_FromMLC( View     NewSLC,
                          View     SourceMLC )
{
   String   szVersion = null;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;


   //:// IssueError( NewSLC, 0, 0, "Start of Build SLC" )

   //:// Create a new SLC from an MLC and tie it back to the MLC.

   //:// It is assumed that the root entity (SubregLabelContent) and that Subreg Product
   //:// includes have been set up prior to execution of this operation.
   //:IF NewSLC.MasterLabelContent DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( NewSLC, "MasterLabelContent" );
   if ( lTempInteger_0 != 0 )
   { 
      //:INCLUDE NewSLC.MasterLabelContent FROM SourceMLC.MasterLabelContent
      RESULT = IncludeSubobjectFromSubobject( NewSLC, "MasterLabelContent", SourceMLC, "MasterLabelContent", zPOS_AFTER );
   } 

   //:END

   //:// Usage Entries ... Surface, Application Type, Area of Use, Organism Claim
   //:FOR EACH SourceMLC.M_Usage
   RESULT = SetCursorFirstEntity( SourceMLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// Check for duplicates.
      //:SET CURSOR FIRST NewSLC.S_Usage WHERE NewSLC.S_Usage.UsageType = SourceMLC.M_Usage.UsageType
      //:                                  AND NewSLC.S_Usage.Name = SourceMLC.M_Usage.Name
      RESULT = SetCursorFirstEntity( NewSLC, "S_Usage", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSLC, "S_Usage", "UsageType", SourceMLC, "M_Usage", "UsageType" ) != 0 || CompareAttributeToAttribute( NewSLC, "S_Usage", "Name", SourceMLC, "M_Usage", "Name" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( NewSLC, "S_Usage", "" );
         } 

      } 

      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:SET CURSOR LAST NewSLC.S_Usage
         RESULT = SetCursorLastEntity( NewSLC, "S_Usage", "" );
         //:CREATE ENTITY NewSLC.S_Usage
         RESULT = CreateEntity( NewSLC, "S_Usage", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSLC, "S_Usage", SourceMLC, "M_Usage", zSET_NULL )
         SetMatchingAttributesByName( NewSLC, "S_Usage", SourceMLC, "M_Usage", zSET_NULL );
      } 

      RESULT = SetCursorNextEntity( SourceMLC, "M_Usage", "" );
      //:END
   } 

   //:END

   //:// General Section ... Precautionary, First Aid, Other Hazard
   //:FOR EACH SourceMLC.M_GeneralSection
   RESULT = SetCursorFirstEntity( SourceMLC, "M_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSLC.S_GeneralSection
      RESULT = CreateEntity( NewSLC, "S_GeneralSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSLC, "S_GeneralSection", SourceMLC, "M_GeneralSection", zSET_NULL )
      SetMatchingAttributesByName( NewSLC, "S_GeneralSection", SourceMLC, "M_GeneralSection", zSET_NULL );
      //:INCLUDE NewSLC.M_GeneralSection FROM SourceMLC.M_GeneralSection
      RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_GeneralSection", SourceMLC, "M_GeneralSection", zPOS_AFTER );
      //:FOR EACH SourceMLC.M_GeneralStatement
      RESULT = SetCursorFirstEntity( SourceMLC, "M_GeneralStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSLC.S_GeneralStatement
         RESULT = CreateEntity( NewSLC, "S_GeneralStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSLC, "S_GeneralStatement", SourceMLC, "M_GeneralStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSLC, "S_GeneralStatement", SourceMLC, "M_GeneralStatement", zSET_NULL );
         //:INCLUDE NewSLC.M_GeneralStatement FROM SourceMLC.M_GeneralStatement
         RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_GeneralStatement", SourceMLC, "M_GeneralStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceMLC, "M_GeneralStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceMLC, "M_GeneralSection", "" );
      //:END
   } 

   //:END

   //:// Ingredients Section
   //:FOR EACH SourceMLC.M_IngredientsSection
   RESULT = SetCursorFirstEntity( SourceMLC, "M_IngredientsSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSLC.S_IngredientsSection
      RESULT = CreateEntity( NewSLC, "S_IngredientsSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSLC, "S_IngredientsSection", SourceMLC, "M_IngredientsSection", zSET_NULL )
      SetMatchingAttributesByName( NewSLC, "S_IngredientsSection", SourceMLC, "M_IngredientsSection", zSET_NULL );
      //:INCLUDE NewSLC.M_IngredientsSection FROM SourceMLC.M_IngredientsSection
      RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_IngredientsSection", SourceMLC, "M_IngredientsSection", zPOS_AFTER );
      //:FOR EACH SourceMLC.M_IngredientsStatement
      RESULT = SetCursorFirstEntity( SourceMLC, "M_IngredientsStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSLC.S_IngredientsStatement
         RESULT = CreateEntity( NewSLC, "S_IngredientsStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSLC, "S_IngredientsStatement", SourceMLC, "M_IngredientsStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSLC, "S_IngredientsStatement", SourceMLC, "M_IngredientsStatement", zSET_NULL );
         //:INCLUDE NewSLC.M_IngredientsStatement FROM SourceMLC.M_IngredientsStatement
         RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_IngredientsStatement", SourceMLC, "M_IngredientsStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceMLC, "M_IngredientsStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceMLC, "M_IngredientsSection", "" );
      //:END
   } 

   //:END

   //:// StorageDisposal Section
   //:FOR EACH SourceMLC.M_StorageDisposalSection
   RESULT = SetCursorFirstEntity( SourceMLC, "M_StorageDisposalSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSLC.S_StorageDisposalSection
      RESULT = CreateEntity( NewSLC, "S_StorageDisposalSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSLC, "S_StorageDisposalSection", SourceMLC, "M_StorageDisposalSection", zSET_NULL )
      SetMatchingAttributesByName( NewSLC, "S_StorageDisposalSection", SourceMLC, "M_StorageDisposalSection", zSET_NULL );
      //:INCLUDE NewSLC.M_StorageDisposalSection FROM SourceMLC.M_StorageDisposalSection
      RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_StorageDisposalSection", SourceMLC, "M_StorageDisposalSection", zPOS_AFTER );
      //:FOR EACH SourceMLC.M_StorageDisposalStatement
      RESULT = SetCursorFirstEntity( SourceMLC, "M_StorageDisposalStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSLC.S_StorageDisposalStatement
         RESULT = CreateEntity( NewSLC, "S_StorageDisposalStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSLC, "S_StorageDisposalStatement", SourceMLC, "M_StorageDisposalStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSLC, "S_StorageDisposalStatement", SourceMLC, "M_StorageDisposalStatement", zSET_NULL );
         //:INCLUDE NewSLC.M_StorageDisposalStatement FROM SourceMLC.M_StorageDisposalStatement
         RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_StorageDisposalStatement", SourceMLC, "M_StorageDisposalStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceMLC, "M_StorageDisposalStatement", "" );
      } 

      RESULT = SetCursorNextEntity( SourceMLC, "M_StorageDisposalSection", "" );
      //:END
   } 

   //:END

   //:// DirectionsForUse Section
   //:FOR EACH SourceMLC.M_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( SourceMLC, "M_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSLC.S_DirectionsForUseSection
      RESULT = CreateEntity( NewSLC, "S_DirectionsForUseSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSLC, "S_DirectionsForUseSection", SourceMLC, "M_DirectionsForUseSection", zSET_NULL )
      SetMatchingAttributesByName( NewSLC, "S_DirectionsForUseSection", SourceMLC, "M_DirectionsForUseSection", zSET_NULL );
      //:INCLUDE NewSLC.M_DirectionsForUseSection FROM SourceMLC.M_DirectionsForUseSection
      RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_DirectionsForUseSection", SourceMLC, "M_DirectionsForUseSection", zPOS_AFTER );
      //:FOR EACH SourceMLC.M_DirectionsForUseStatement
      RESULT = SetCursorFirstEntity( SourceMLC, "M_DirectionsForUseStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSLC.S_DirectionsForUseStatement
         RESULT = CreateEntity( NewSLC, "S_DirectionsForUseStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSLC, "S_DirectionsForUseStatement", SourceMLC, "M_DirectionsForUseStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSLC, "S_DirectionsForUseStatement", SourceMLC, "M_DirectionsForUseStatement", zSET_NULL );
         //:INCLUDE NewSLC.M_DirectionsForUseStatement FROM SourceMLC.M_DirectionsForUseStatement
         RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_DirectionsForUseStatement", SourceMLC, "M_DirectionsForUseStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceMLC, "M_DirectionsForUseStatement", "" );
      } 

      //:END

      //:FOR EACH SourceMLC.M_DirectionsUsage WITHIN SourceMLC.M_DirectionsForUseSection
      RESULT = SetCursorFirstEntity( SourceMLC, "M_DirectionsUsage", "M_DirectionsForUseSection" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:SET CURSOR FIRST NewSLC.S_Usage WHERE NewSLC.S_Usage.UsageType = SourceMLC.M_DirectionsUsage.UsageType
         //:                                  AND NewSLC.S_Usage.Name = SourceMLC.M_DirectionsUsage.Name
         RESULT = SetCursorFirstEntity( NewSLC, "S_Usage", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSLC, "S_Usage", "UsageType", SourceMLC, "M_DirectionsUsage", "UsageType" ) != 0 ||
                    CompareAttributeToAttribute( NewSLC, "S_Usage", "Name", SourceMLC, "M_DirectionsUsage", "Name" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( NewSLC, "S_Usage", "" );
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:// Check duplicates.
            //:SET CURSOR FIRST NewSLC.S_OriginalDirectionsUsage WHERE NewSLC.S_OriginalDirectionsUsage.UsageType = SourceMLC.M_DirectionsUsage.UsageType
            //:                                                    AND NewSLC.S_OriginalDirectionsUsage.Name = SourceMLC.M_DirectionsUsage.Name
            RESULT = SetCursorFirstEntity( NewSLC, "S_OriginalDirectionsUsage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSLC, "S_OriginalDirectionsUsage", "UsageType", SourceMLC, "M_DirectionsUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( NewSLC, "S_OriginalDirectionsUsage", "Name", SourceMLC, "M_DirectionsUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( NewSLC, "S_OriginalDirectionsUsage", "" );
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:CREATE ENTITY NewSLC.S_DirectionsUsageOrdering
               RESULT = CreateEntity( NewSLC, "S_DirectionsUsageOrdering", zPOS_AFTER );
               //:INCLUDE NewSLC.S_DirectionsUsage         FROM NewSLC.S_Usage
               RESULT = IncludeSubobjectFromSubobject( NewSLC, "S_DirectionsUsage", NewSLC, "S_Usage", zPOS_AFTER );
               //:CREATE ENTITY NewSLC.S_OriginalDirectionUsageOrdering
               RESULT = CreateEntity( NewSLC, "S_OriginalDirectionUsageOrdering", zPOS_AFTER );
               //:INCLUDE NewSLC.S_OriginalDirectionsUsage FROM NewSLC.S_Usage
               RESULT = IncludeSubobjectFromSubobject( NewSLC, "S_OriginalDirectionsUsage", NewSLC, "S_Usage", zPOS_AFTER );
            } 

            //:END
         } 

         RESULT = SetCursorNextEntity( SourceMLC, "M_DirectionsUsage", "M_DirectionsForUseSection" );
         //:END
      } 

      RESULT = SetCursorNextEntity( SourceMLC, "M_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// Marketing Section
   //:FOR EACH SourceMLC.M_MarketingSection
   RESULT = SetCursorFirstEntity( SourceMLC, "M_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSLC.S_MarketingSection
      RESULT = CreateEntity( NewSLC, "S_MarketingSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSLC, "S_MarketingSection", SourceMLC, "M_MarketingSection", zSET_NULL )
      SetMatchingAttributesByName( NewSLC, "S_MarketingSection", SourceMLC, "M_MarketingSection", zSET_NULL );
      //:INCLUDE NewSLC.M_MarketingSection FROM SourceMLC.M_MarketingSection
      RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_MarketingSection", SourceMLC, "M_MarketingSection", zPOS_AFTER );
      //:FOR EACH SourceMLC.M_MarketingStatement
      RESULT = SetCursorFirstEntity( SourceMLC, "M_MarketingStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewSLC.S_MarketingStatement
         RESULT = CreateEntity( NewSLC, "S_MarketingStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewSLC, "S_MarketingStatement", SourceMLC, "M_MarketingStatement", zSET_NULL )
         SetMatchingAttributesByName( NewSLC, "S_MarketingStatement", SourceMLC, "M_MarketingStatement", zSET_NULL );
         //:INCLUDE NewSLC.M_MarketingStatement FROM SourceMLC.M_MarketingStatement
         RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_MarketingStatement", SourceMLC, "M_MarketingStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( SourceMLC, "M_MarketingStatement", "" );
      } 

      //:END

      //:FOR EACH SourceMLC.M_MarketingUsage WITHIN SourceMLC.M_MarketingSection
      RESULT = SetCursorFirstEntity( SourceMLC, "M_MarketingUsage", "M_MarketingSection" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:SET CURSOR FIRST NewSLC.S_Usage WHERE NewSLC.S_Usage.UsageType = SourceMLC.M_MarketingUsage.UsageType
         //:                                  AND NewSLC.S_Usage.Name = SourceMLC.M_MarketingUsage.Name
         RESULT = SetCursorFirstEntity( NewSLC, "S_Usage", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSLC, "S_Usage", "UsageType", SourceMLC, "M_MarketingUsage", "UsageType" ) != 0 ||
                    CompareAttributeToAttribute( NewSLC, "S_Usage", "Name", SourceMLC, "M_MarketingUsage", "Name" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( NewSLC, "S_Usage", "" );
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:// Check duplicates.
            //:SET CURSOR FIRST NewSLC.S_OriginalMarketingUsage WHERE NewSLC.S_OriginalMarketingUsage.UsageType = SourceMLC.M_MarketingUsage.UsageType
            //:                                                   AND NewSLC.S_OriginalMarketingUsage.Name = SourceMLC.M_MarketingUsage.Name
            RESULT = SetCursorFirstEntity( NewSLC, "S_OriginalMarketingUsage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewSLC, "S_OriginalMarketingUsage", "UsageType", SourceMLC, "M_MarketingUsage", "UsageType" ) != 0 ||
                       CompareAttributeToAttribute( NewSLC, "S_OriginalMarketingUsage", "Name", SourceMLC, "M_MarketingUsage", "Name" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( NewSLC, "S_OriginalMarketingUsage", "" );
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:CREATE ENTITY NewSLC.S_MarketingUsageOrdering
               RESULT = CreateEntity( NewSLC, "S_MarketingUsageOrdering", zPOS_AFTER );
               //:INCLUDE NewSLC.S_MarketingUsage         FROM NewSLC.S_Usage
               RESULT = IncludeSubobjectFromSubobject( NewSLC, "S_MarketingUsage", NewSLC, "S_Usage", zPOS_AFTER );
               //:CREATE ENTITY NewSLC.S_OriginalMarketingUsageOrdering
               RESULT = CreateEntity( NewSLC, "S_OriginalMarketingUsageOrdering", zPOS_AFTER );
               //:INCLUDE NewSLC.S_OriginalMarketingUsage FROM NewSLC.S_Usage
               RESULT = IncludeSubobjectFromSubobject( NewSLC, "S_OriginalMarketingUsage", NewSLC, "S_Usage", zPOS_AFTER );
            } 

            //:END
         } 

         RESULT = SetCursorNextEntity( SourceMLC, "M_MarketingUsage", "M_MarketingSection" );
         //:END
      } 

      RESULT = SetCursorNextEntity( SourceMLC, "M_MarketingSection", "" );
      //:END
   } 

   //:END

   //:// HumanHazard Section
   //:FOR EACH SourceMLC.M_HumanHazardSection
   RESULT = SetCursorFirstEntity( SourceMLC, "M_HumanHazardSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewSLC.S_HumanHazardSection
      RESULT = CreateEntity( NewSLC, "S_HumanHazardSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewSLC, "S_HumanHazardSection", SourceMLC, "M_HumanHazardSection", zSET_NULL )
      SetMatchingAttributesByName( NewSLC, "S_HumanHazardSection", SourceMLC, "M_HumanHazardSection", zSET_NULL );
      //:INCLUDE NewSLC.M_HumanHazardSection FROM SourceMLC.M_HumanHazardSection
      RESULT = IncludeSubobjectFromSubobject( NewSLC, "M_HumanHazardSection", SourceMLC, "M_HumanHazardSection", zPOS_AFTER );
      RESULT = SetCursorNextEntity( SourceMLC, "M_HumanHazardSection", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dSubregNameID( VIEW mSubLC BASED ON LOD mSubLC,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   STRING ( 1000 ) szString
public int 
omSubLC_dSubregNameID( View     mSubLC,
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

         //:IF mSubLC.SubregOrganization EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSubLC, "SubregOrganization" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mSubLC.SubregOrganization.Name
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 1001, mSubLC, "SubregOrganization", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
            //:IF mSubLC.Subregistrant.EPA_CompanyNumber = ""
            if ( CompareAttributeToString( mSubLC, "Subregistrant", "EPA_CompanyNumber", "" ) == 0 )
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
               //:szString = szString + " (" + mSubLC.Subregistrant.EPA_CompanyNumber + ")"
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
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 129, mSubLC, "Subregistrant", "EPA_CompanyNumber", "", 0 );
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
         //:StoreStringInRecord( mSubLC,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mSubLC, InternalEntityStructure, InternalAttribStructure, szString );
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
   //:dFullHazardStatement( VIEW mSubLC BASED ON LOD mSubLC,
   //:                   STRING ( 32 ) InternalEntityStructure,
   //:                   STRING ( 32 ) InternalAttribStructure,
   //:                   SHORT GetOrSetFlag )

   //:STRING ( 256 ) szString
public int 
omSubLC_dFullHazardStatement( View     mSubLC,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szString = null;
   //:STRING ( 256 ) szReplaceString
   String   szReplaceString = null;
   //:STRING ( 256 ) szLocation
   String   szLocation = null;
   //:SHORT nPosStart
   int      nPosStart = 0;
   //:SHORT nPosEnd
   int      nPosEnd = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mSubLC.S_HumanHazardSection  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mSubLC, "S_HumanHazardSection" );
         if ( lTempInteger_0 == 0 )
         { 

            //:szString = mSubLC.S_HumanHazardSection.PrecautionaryStatement
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 257, mSubLC, "S_HumanHazardSection", "PrecautionaryStatement", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
            //:nPosStart  = zSearchSubString( szString, "{{Precautionary Position}}", "f", 0 )
            nPosStart = zSearchSubString( szString, "{{Precautionary Position}}", "f", 0 );
            //:IF nPosStart >= 0
            if ( nPosStart >= 0 )
            { 

               //:nPosEnd = nPosStart + 26 // length of "{{Precautionary Position}}"
               nPosEnd = nPosStart + 26;
               //:szReplaceString = ""
                {StringBuilder sb_szReplaceString;
               if ( szReplaceString == null )
                  sb_szReplaceString = new StringBuilder( 32 );
               else
                  sb_szReplaceString = new StringBuilder( szReplaceString );
                              ZeidonStringCopy( sb_szReplaceString, 1, 0, "", 1, 0, 257 );
               szReplaceString = sb_szReplaceString.toString( );}
               //:szLocation = mSubLC.S_HumanHazardSection.Location1
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_2, 'S', 257, mSubLC, "S_HumanHazardSection", "Location1", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + "[" + szLocation + "]"
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "[", 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "]", 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:szLocation = mSubLC.S_HumanHazardSection.Location2
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_3, 'S', 257, mSubLC, "S_HumanHazardSection", "Location2", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + "[" + szLocation + "]"
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "[", 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "]", 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:szLocation = mSubLC.S_HumanHazardSection.Location3
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_4, 'S', 257, mSubLC, "S_HumanHazardSection", "Location3", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + "[" + szLocation + "]"
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "[", 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "]", 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:szLocation = mSubLC.S_HumanHazardSection.Location4
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_5, 'S', 257, mSubLC, "S_HumanHazardSection", "Location4", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + "[" + szLocation + "]"
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "[", 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "]", 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:szLocation = mSubLC.S_HumanHazardSection.Location5
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_6, 'S', 257, mSubLC, "S_HumanHazardSection", "Location5", "", 0 );
               lTempInteger_6 = mi_lTempInteger_6.intValue( );
               szLocation = sb_szLocation.toString( );}
               //:IF szLocation != ""
               if ( ZeidonStringCompare( szLocation, 1, 0, "", 1, 0, 257 ) != 0 )
               { 
                  //:szReplaceString = szReplaceString + "[" + szLocation + "]"
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "[", 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, szLocation, 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
                   {StringBuilder sb_szReplaceString;
                  if ( szReplaceString == null )
                     sb_szReplaceString = new StringBuilder( 32 );
                  else
                     sb_szReplaceString = new StringBuilder( szReplaceString );
                                    ZeidonStringConcat( sb_szReplaceString, 1, 0, "]", 1, 0, 257 );
                  szReplaceString = sb_szReplaceString.toString( );}
               } 

               //:END

               //:zReplaceSubString( szString, nPosStart, nPosEnd, szReplaceString )
               {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                               zReplaceSubString( sb_szString, nPosStart, nPosEnd, szReplaceString );
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
                        ZeidonStringCopy( sb_szString, 1, 0, "", 1, 0, 257 );
            szString = sb_szString.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mSubLC,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mSubLC, InternalEntityStructure, InternalAttribStructure, szString );
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
