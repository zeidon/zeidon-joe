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

public class mMasLC_Object extends VmlObjectOperations
{
   public mMasLC_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dIngredientName( VIEW mMasLC BASED ON LOD mMasLC,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )

//:   STRING ( 1000 ) szString
public int 
omMasLC_dIngredientName( View     mMasLC,
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

         //:IF mMasLC.M_IngredientsStatement EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "M_IngredientsStatement" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mMasLC.M_IngredientsStatement.CommonName
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 1001, mMasLC, "M_IngredientsStatement", "CommonName", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
            //:IF szString = ""
            if ( ZeidonStringCompare( szString, 1, 0, "", 1, 0, 1001 ) == 0 )
            { 
               //:szString = mMasLC.M_IngredientsStatement.ChemicalName
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                               GetVariableFromAttribute( sb_szString, mi_lTempInteger_2, 'S', 1001, mMasLC, "M_IngredientsStatement", "ChemicalName", "", 0 );
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
         //:StoreStringInRecord( mMasLC,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mMasLC, InternalEntityStructure, InternalAttribStructure, szString );
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
   //:dMasterProductNameNbr( VIEW mMasLC BASED ON LOD mMasLC,
   //:                    STRING ( 32 ) InternalEntityStructure,
   //:                    STRING ( 32 ) InternalAttribStructure,
   //:                    SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
omMasLC_dMasterProductNameNbr( View     mMasLC,
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

         //:IF mMasLC.MasterProduct  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "MasterProduct" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mMasLC.MasterProduct.Name + " (" +
            //:        mMasLC.PrimaryRegistrant.EPA_CompanyNumber + "-" +
            //:        mMasLC.MasterProduct.Number + ")"
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttribute( sb_szString, mMasLC, "MasterProduct", "Name" );
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
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 129, mMasLC, "PrimaryRegistrant", "EPA_CompanyNumber", "", 0 );
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
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 129, mMasLC, "MasterProduct", "Number", "", 0 );
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
         //:StoreStringInRecord( mMasLC,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mMasLC, InternalEntityStructure, InternalAttribStructure, szString );
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
   //:dRegistrantNameID( VIEW mMasLC BASED ON LOD mMasLC,
   //:                STRING ( 32 ) InternalEntityStructure,
   //:                STRING ( 32 ) InternalAttribStructure,
   //:                SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
omMasLC_dRegistrantNameID( View     mMasLC,
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

         //:IF mMasLC.Organization  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "Organization" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mMasLC.Organization.Name
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 1001, mMasLC, "Organization", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
            //:IF mMasLC.PrimaryRegistrant.EPA_CompanyNumber = ""
            if ( CompareAttributeToString( mMasLC, "PrimaryRegistrant", "EPA_CompanyNumber", "" ) == 0 )
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
               //:     mMasLC.PrimaryRegistrant.EPA_CompanyNumber + ")"
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
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 129, mMasLC, "PrimaryRegistrant", "EPA_CompanyNumber", "", 0 );
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
         //:StoreStringInRecord( mMasLC,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mMasLC, InternalEntityStructure, InternalAttribStructure, szString );
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
   //:BuildNewMLC_Version( VIEW NewMLC      BASED ON LOD mMasLC,
   //:                  VIEW PreviousMLC BASED ON LOD mMasLC )

   //:STRING ( 10 ) szVersion
public int 
omMasLC_BuildNewMLC_Version( View     NewMLC,
                             View     PreviousMLC )
{
   String   szVersion = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:// Create a new MLC from a previous MLC, tieing the new back to the original.

   //:// Root and single includes
   //:CREATE ENTITY NewMLC.MasterLabelContent
   RESULT = CreateEntity( NewMLC, "MasterLabelContent", zPOS_AFTER );
   //:SetMatchingAttributesByName( NewMLC, "MasterLabelContent", PreviousMLC, "MasterLabelContent", zSET_NULL )
   SetMatchingAttributesByName( NewMLC, "MasterLabelContent", PreviousMLC, "MasterLabelContent", zSET_NULL );
   //:SetAttributeFromCurrentDateTime( NewMLC, "MasterLabelContent", "RevisionDate" )
   {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( NewMLC );
    m_ZGlobal1_Operation.SetAttributeFromCurrentDateTime( NewMLC, "MasterLabelContent", "RevisionDate" );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   }
   //:szVersion = NewMLC.MasterLabelContent.RevisionDate
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szVersion;
   if ( szVersion == null )
      sb_szVersion = new StringBuilder( 32 );
   else
      sb_szVersion = new StringBuilder( szVersion );
       GetVariableFromAttribute( sb_szVersion, mi_lTempInteger_0, 'S', 11, NewMLC, "MasterLabelContent", "RevisionDate", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szVersion = sb_szVersion.toString( );}
   //:NewMLC.MasterLabelContent.Version = szVersion
   SetAttributeFromString( NewMLC, "MasterLabelContent", "Version", szVersion );
   //:INCLUDE NewMLC.MasterProduct FROM PreviousMLC.MasterProduct
   RESULT = IncludeSubobjectFromSubobject( NewMLC, "MasterProduct", PreviousMLC, "MasterProduct", zPOS_AFTER );
   //:INCLUDE NewMLC.MP_MasterLabelContent FROM PreviousMLC.MasterLabelContent
   RESULT = IncludeSubobjectFromSubobject( NewMLC, "MP_MasterLabelContent", PreviousMLC, "MasterLabelContent", zPOS_AFTER );

   //:// M_Usage Subobject
   //:// Check to make sure we are not copying duplicates.
   //:FOR EACH PreviousMLC.M_Usage
   RESULT = SetCursorFirstEntity( PreviousMLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST NewMLC.M_Usage WHERE NewMLC.M_Usage.UsageType = PreviousMLC.M_Usage.UsageType
      //:                                  AND NewMLC.M_Usage.Name = PreviousMLC.M_Usage.Name
      RESULT = SetCursorFirstEntity( NewMLC, "M_Usage", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewMLC, "M_Usage", "UsageType", PreviousMLC, "M_Usage", "UsageType" ) != 0 || CompareAttributeToAttribute( NewMLC, "M_Usage", "Name", PreviousMLC, "M_Usage", "Name" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( NewMLC, "M_Usage", "" );
         } 

      } 

      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:CREATE ENTITY NewMLC.M_Usage
         RESULT = CreateEntity( NewMLC, "M_Usage", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewMLC, "M_Usage", PreviousMLC, "M_Usage", zSET_NULL )
         SetMatchingAttributesByName( NewMLC, "M_Usage", PreviousMLC, "M_Usage", zSET_NULL );
         //:INCLUDE NewMLC.MP_Usage FROM PreviousMLC.M_Usage
         RESULT = IncludeSubobjectFromSubobject( NewMLC, "MP_Usage", PreviousMLC, "M_Usage", zPOS_AFTER );
      } 

      RESULT = SetCursorNextEntity( PreviousMLC, "M_Usage", "" );
      //:END
   } 

   //:END

   //:// M_GeneralSection Subobject
   //:FOR EACH PreviousMLC.M_GeneralSection
   RESULT = SetCursorFirstEntity( PreviousMLC, "M_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewMLC.M_GeneralSection
      RESULT = CreateEntity( NewMLC, "M_GeneralSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewMLC, "M_GeneralSection", PreviousMLC, "M_GeneralSection", zSET_NULL )
      SetMatchingAttributesByName( NewMLC, "M_GeneralSection", PreviousMLC, "M_GeneralSection", zSET_NULL );
      //:INCLUDE NewMLC.MP_GeneralSection FROM PreviousMLC.M_GeneralSection
      RESULT = IncludeSubobjectFromSubobject( NewMLC, "MP_GeneralSection", PreviousMLC, "M_GeneralSection", zPOS_AFTER );
      //:FOR EACH PreviousMLC.M_GeneralStatement
      RESULT = SetCursorFirstEntity( PreviousMLC, "M_GeneralStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewMLC.M_GeneralStatement
         RESULT = CreateEntity( NewMLC, "M_GeneralStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewMLC, "M_GeneralStatement", PreviousMLC, "M_GeneralStatement", zSET_NULL )
         SetMatchingAttributesByName( NewMLC, "M_GeneralStatement", PreviousMLC, "M_GeneralStatement", zSET_NULL );
         //:INCLUDE NewMLC.MP_GeneralStatement FROM PreviousMLC.M_GeneralStatement
         RESULT = IncludeSubobjectFromSubobject( NewMLC, "MP_GeneralStatement", PreviousMLC, "M_GeneralStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( PreviousMLC, "M_GeneralStatement", "" );
      } 

      RESULT = SetCursorNextEntity( PreviousMLC, "M_GeneralSection", "" );
      //:END
   } 

   //:END

   //:// M_IngredientsSection Subobject
   //:FOR EACH PreviousMLC.M_IngredientsSection
   RESULT = SetCursorFirstEntity( PreviousMLC, "M_IngredientsSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewMLC.M_IngredientsSection
      RESULT = CreateEntity( NewMLC, "M_IngredientsSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewMLC, "M_IngredientsSection", PreviousMLC, "M_IngredientsSection", zSET_NULL )
      SetMatchingAttributesByName( NewMLC, "M_IngredientsSection", PreviousMLC, "M_IngredientsSection", zSET_NULL );
      //:INCLUDE NewMLC.MP_IngredientsSection FROM PreviousMLC.M_IngredientsSection
      RESULT = IncludeSubobjectFromSubobject( NewMLC, "MP_IngredientsSection", PreviousMLC, "M_IngredientsSection", zPOS_AFTER );
      //:FOR EACH PreviousMLC.M_IngredientsStatement
      RESULT = SetCursorFirstEntity( PreviousMLC, "M_IngredientsStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewMLC.M_IngredientsStatement
         RESULT = CreateEntity( NewMLC, "M_IngredientsStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewMLC, "M_IngredientsStatement", PreviousMLC, "M_IngredientsStatement", zSET_NULL )
         SetMatchingAttributesByName( NewMLC, "M_IngredientsStatement", PreviousMLC, "M_IngredientsStatement", zSET_NULL );
         //:INCLUDE NewMLC.MP_IngredientsStatement FROM PreviousMLC.M_IngredientsStatement
         RESULT = IncludeSubobjectFromSubobject( NewMLC, "MP_IngredientsStatement", PreviousMLC, "M_IngredientsStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( PreviousMLC, "M_IngredientsStatement", "" );
      } 

      RESULT = SetCursorNextEntity( PreviousMLC, "M_IngredientsSection", "" );
      //:END
   } 

   //:END

   //:// M_StorageDisposalSection Subobject
   //:FOR EACH PreviousMLC.M_StorageDisposalSection
   RESULT = SetCursorFirstEntity( PreviousMLC, "M_StorageDisposalSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewMLC.M_StorageDisposalSection
      RESULT = CreateEntity( NewMLC, "M_StorageDisposalSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewMLC, "M_StorageDisposalSection", PreviousMLC, "M_StorageDisposalSection", zSET_NULL )
      SetMatchingAttributesByName( NewMLC, "M_StorageDisposalSection", PreviousMLC, "M_StorageDisposalSection", zSET_NULL );
      //:INCLUDE NewMLC.MP_StorageDisposalSection FROM PreviousMLC.M_StorageDisposalSection
      RESULT = IncludeSubobjectFromSubobject( NewMLC, "MP_StorageDisposalSection", PreviousMLC, "M_StorageDisposalSection", zPOS_AFTER );
      //:FOR EACH PreviousMLC.M_StorageDisposalStatement
      RESULT = SetCursorFirstEntity( PreviousMLC, "M_StorageDisposalStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewMLC.M_StorageDisposalStatement
         RESULT = CreateEntity( NewMLC, "M_StorageDisposalStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewMLC, "M_StorageDisposalStatement", PreviousMLC, "M_StorageDisposalStatement", zSET_NULL )
         SetMatchingAttributesByName( NewMLC, "M_StorageDisposalStatement", PreviousMLC, "M_StorageDisposalStatement", zSET_NULL );
         //:INCLUDE NewMLC.MP_StorageDisposalStatement FROM PreviousMLC.M_StorageDisposalStatement
         RESULT = IncludeSubobjectFromSubobject( NewMLC, "MP_StorageDisposalStatement", PreviousMLC, "M_StorageDisposalStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( PreviousMLC, "M_StorageDisposalStatement", "" );
      } 

      RESULT = SetCursorNextEntity( PreviousMLC, "M_StorageDisposalSection", "" );
      //:END
   } 

   //:END

   //:// M_DirectionsForUseSection Subobject
   //:FOR EACH PreviousMLC.M_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( PreviousMLC, "M_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewMLC.M_DirectionsForUseSection
      RESULT = CreateEntity( NewMLC, "M_DirectionsForUseSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewMLC, "M_DirectionsForUseSection", PreviousMLC, "M_DirectionsForUseSection", zSET_NULL )
      SetMatchingAttributesByName( NewMLC, "M_DirectionsForUseSection", PreviousMLC, "M_DirectionsForUseSection", zSET_NULL );
      //:INCLUDE NewMLC.MP_DirectionsForUseSection FROM PreviousMLC.M_DirectionsForUseSection
      RESULT = IncludeSubobjectFromSubobject( NewMLC, "MP_DirectionsForUseSection", PreviousMLC, "M_DirectionsForUseSection", zPOS_AFTER );
      //:FOR EACH PreviousMLC.M_DirectionsForUseStatement
      RESULT = SetCursorFirstEntity( PreviousMLC, "M_DirectionsForUseStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewMLC.M_DirectionsForUseStatement
         RESULT = CreateEntity( NewMLC, "M_DirectionsForUseStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewMLC, "M_DirectionsForUseStatement", PreviousMLC, "M_DirectionsForUseStatement", zSET_NULL )
         SetMatchingAttributesByName( NewMLC, "M_DirectionsForUseStatement", PreviousMLC, "M_DirectionsForUseStatement", zSET_NULL );
         //:INCLUDE NewMLC.MP_DirectionsForUseStatement FROM PreviousMLC.M_DirectionsForUseStatement
         RESULT = IncludeSubobjectFromSubobject( NewMLC, "MP_DirectionsForUseStatement", PreviousMLC, "M_DirectionsForUseStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( PreviousMLC, "M_DirectionsForUseStatement", "" );
      } 

      //:END

      //:// Include appropriate Usage statements.
      //:FOR EACH PreviousMLC.M_DirectionsUsage WITHIN PreviousMLC.M_DirectionsForUseSection
      RESULT = SetCursorFirstEntity( PreviousMLC, "M_DirectionsUsage", "M_DirectionsForUseSection" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:// Check to see if the Usage already exists. This can happen if two Usages have the same name.
         //:SET CURSOR FIRST NewMLC.M_DirectionsUsage WHERE NewMLC.M_DirectionsUsage.Name = PreviousMLC.M_DirectionsUsage.Name
         //:                                            AND NewMLC.M_DirectionsUsage.UsageType = PreviousMLC.M_DirectionsUsage.UsageType
         RESULT = SetCursorFirstEntity( NewMLC, "M_DirectionsUsage", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewMLC, "M_DirectionsUsage", "Name", PreviousMLC, "M_DirectionsUsage", "Name" ) != 0 ||
                    CompareAttributeToAttribute( NewMLC, "M_DirectionsUsage", "UsageType", PreviousMLC, "M_DirectionsUsage", "UsageType" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( NewMLC, "M_DirectionsUsage", "" );
            } 

         } 

         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:SET CURSOR FIRST NewMLC.M_Usage WHERE NewMLC.M_Usage.Name = PreviousMLC.M_DirectionsUsage.Name
            //:                                  AND NewMLC.M_Usage.UsageType = PreviousMLC.M_DirectionsUsage.UsageType
            RESULT = SetCursorFirstEntity( NewMLC, "M_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewMLC, "M_Usage", "Name", PreviousMLC, "M_DirectionsUsage", "Name" ) != 0 ||
                       CompareAttributeToAttribute( NewMLC, "M_Usage", "UsageType", PreviousMLC, "M_DirectionsUsage", "UsageType" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( NewMLC, "M_Usage", "" );
               } 

            } 

            //:CREATE ENTITY NewMLC.M_DirectionsUsageOrdering
            RESULT = CreateEntity( NewMLC, "M_DirectionsUsageOrdering", zPOS_AFTER );
            //:INCLUDE NewMLC.M_DirectionsUsage FROM NewMLC.M_Usage
            RESULT = IncludeSubobjectFromSubobject( NewMLC, "M_DirectionsUsage", NewMLC, "M_Usage", zPOS_AFTER );
         } 

         RESULT = SetCursorNextEntity( PreviousMLC, "M_DirectionsUsage", "M_DirectionsForUseSection" );
         //:END
      } 

      RESULT = SetCursorNextEntity( PreviousMLC, "M_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// M_MarketingSection Subobject
   //:FOR EACH PreviousMLC.M_MarketingSection
   RESULT = SetCursorFirstEntity( PreviousMLC, "M_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewMLC.M_MarketingSection
      RESULT = CreateEntity( NewMLC, "M_MarketingSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewMLC, "M_MarketingSection", PreviousMLC, "M_MarketingSection", zSET_NULL )
      SetMatchingAttributesByName( NewMLC, "M_MarketingSection", PreviousMLC, "M_MarketingSection", zSET_NULL );
      //:INCLUDE NewMLC.MP_MarketingSection FROM PreviousMLC.M_MarketingSection
      RESULT = IncludeSubobjectFromSubobject( NewMLC, "MP_MarketingSection", PreviousMLC, "M_MarketingSection", zPOS_AFTER );
      //:FOR EACH PreviousMLC.M_MarketingStatement
      RESULT = SetCursorFirstEntity( PreviousMLC, "M_MarketingStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY NewMLC.M_MarketingStatement
         RESULT = CreateEntity( NewMLC, "M_MarketingStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( NewMLC, "M_MarketingStatement", PreviousMLC, "M_MarketingStatement", zSET_NULL )
         SetMatchingAttributesByName( NewMLC, "M_MarketingStatement", PreviousMLC, "M_MarketingStatement", zSET_NULL );
         //:INCLUDE NewMLC.MP_MarketingStatement FROM PreviousMLC.M_MarketingStatement
         RESULT = IncludeSubobjectFromSubobject( NewMLC, "MP_MarketingStatement", PreviousMLC, "M_MarketingStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( PreviousMLC, "M_MarketingStatement", "" );
      } 

      //:END

      //: // Include appropriate Usage statements.
      //:FOR EACH PreviousMLC.M_MarketingUsage WITHIN PreviousMLC.M_MarketingSection
      RESULT = SetCursorFirstEntity( PreviousMLC, "M_MarketingUsage", "M_MarketingSection" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:// Check to see if the Usage already exists. This can happen if two Usages have the same name.
         //:SET CURSOR FIRST NewMLC.M_MarketingUsage WHERE NewMLC.M_MarketingUsage.Name = PreviousMLC.M_MarketingUsage.Name
         //:                                           AND NewMLC.M_MarketingUsage.UsageType = PreviousMLC.M_MarketingUsage.UsageType
         RESULT = SetCursorFirstEntity( NewMLC, "M_MarketingUsage", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewMLC, "M_MarketingUsage", "Name", PreviousMLC, "M_MarketingUsage", "Name" ) != 0 ||
                    CompareAttributeToAttribute( NewMLC, "M_MarketingUsage", "UsageType", PreviousMLC, "M_MarketingUsage", "UsageType" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( NewMLC, "M_MarketingUsage", "" );
            } 

         } 

         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:SET CURSOR FIRST NewMLC.M_Usage WHERE NewMLC.M_Usage.Name = PreviousMLC.M_MarketingUsage.Name
            //:                                  AND NewMLC.M_Usage.UsageType = PreviousMLC.M_MarketingUsage.UsageType
            RESULT = SetCursorFirstEntity( NewMLC, "M_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( NewMLC, "M_Usage", "Name", PreviousMLC, "M_MarketingUsage", "Name" ) != 0 ||
                       CompareAttributeToAttribute( NewMLC, "M_Usage", "UsageType", PreviousMLC, "M_MarketingUsage", "UsageType" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( NewMLC, "M_Usage", "" );
               } 

            } 

            //:CREATE ENTITY NewMLC.M_MarketingUsageOrdering
            RESULT = CreateEntity( NewMLC, "M_MarketingUsageOrdering", zPOS_AFTER );
            //:INCLUDE NewMLC.M_MarketingUsage FROM NewMLC.M_Usage
            RESULT = IncludeSubobjectFromSubobject( NewMLC, "M_MarketingUsage", NewMLC, "M_Usage", zPOS_AFTER );
         } 

         RESULT = SetCursorNextEntity( PreviousMLC, "M_MarketingUsage", "M_MarketingSection" );
         //:END
      } 

      RESULT = SetCursorNextEntity( PreviousMLC, "M_MarketingSection", "" );
      //:END
   } 

   //:END

   //:// M_HumanHazardSection Subobject
   //:FOR EACH PreviousMLC.M_HumanHazardSection
   RESULT = SetCursorFirstEntity( PreviousMLC, "M_HumanHazardSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY NewMLC.M_HumanHazardSection
      RESULT = CreateEntity( NewMLC, "M_HumanHazardSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( NewMLC, "M_HumanHazardSection", PreviousMLC, "M_HumanHazardSection", zSET_NULL )
      SetMatchingAttributesByName( NewMLC, "M_HumanHazardSection", PreviousMLC, "M_HumanHazardSection", zSET_NULL );
      //:INCLUDE NewMLC.MP_HumanHazardSection FROM PreviousMLC.M_HumanHazardSection
      RESULT = IncludeSubobjectFromSubobject( NewMLC, "MP_HumanHazardSection", PreviousMLC, "M_HumanHazardSection", zPOS_AFTER );
      RESULT = SetCursorNextEntity( PreviousMLC, "M_HumanHazardSection", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:CopyMLCToNewProduct( VIEW TargetMLC BASED ON LOD mMasLC,
//:                     VIEW SourceMLC BASED ON LOD mMasLC )

//:   VIEW TargetMLC2  BASED ON LOD mMasLC
public int 
omMasLC_CopyMLCToNewProduct( View     TargetMLC,
                             View     SourceMLC )
{
   zVIEW    TargetMLC2 = new zVIEW( );
   int      RESULT = 0;
   String   szTempString_0 = null;


   //:// Copy the Source MLC to the new Target MLC, which is for a new Product.
   //:// The root entity of the new Target MLC will have been created and linked to the appropriate Product
   //:// prior to entering this operation.

   //:// For root, only set attributes.
   //:SetMatchingAttributesByName( TargetMLC, "MasterLabelContent", SourceMLC, "MasterLabelContent", zSET_NULL )
   SetMatchingAttributesByName( TargetMLC, "MasterLabelContent", SourceMLC, "MasterLabelContent", zSET_NULL );

   //:// M_Usage Subobject
   //:// Check to make sure we are not copying duplicates.
   //:FOR EACH SourceMLC.M_Usage
   RESULT = SetCursorFirstEntity( SourceMLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST TargetMLC.M_Usage WHERE TargetMLC.M_Usage.UsageType = SourceMLC.M_Usage.UsageType
      //:                                     AND TargetMLC.M_Usage.Name = SourceMLC.M_Usage.Name
      RESULT = SetCursorFirstEntity( TargetMLC, "M_Usage", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( TargetMLC, "M_Usage", "UsageType", SourceMLC, "M_Usage", "UsageType" ) != 0 ||
                 CompareAttributeToAttribute( TargetMLC, "M_Usage", "Name", SourceMLC, "M_Usage", "Name" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( TargetMLC, "M_Usage", "" );
         } 

      } 

      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:CREATE ENTITY TargetMLC.M_Usage
         RESULT = CreateEntity( TargetMLC, "M_Usage", zPOS_AFTER );
         //:SetMatchingAttributesByName( TargetMLC, "M_Usage", SourceMLC, "M_Usage", zSET_NULL )
         SetMatchingAttributesByName( TargetMLC, "M_Usage", SourceMLC, "M_Usage", zSET_NULL );
      } 

      RESULT = SetCursorNextEntity( SourceMLC, "M_Usage", "" );
      //:END
   } 

   //:END

   //:// General Section
   //:FOR EACH SourceMLC.M_GeneralSection
   RESULT = SetCursorFirstEntity( SourceMLC, "M_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY TargetMLC.M_GeneralSection
      RESULT = CreateEntity( TargetMLC, "M_GeneralSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( TargetMLC, "M_GeneralSection", SourceMLC, "M_GeneralSection", zSET_NULL )
      SetMatchingAttributesByName( TargetMLC, "M_GeneralSection", SourceMLC, "M_GeneralSection", zSET_NULL );
      //:FOR EACH SourceMLC.M_GeneralStatement
      RESULT = SetCursorFirstEntity( SourceMLC, "M_GeneralStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY TargetMLC.M_GeneralStatement
         RESULT = CreateEntity( TargetMLC, "M_GeneralStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( TargetMLC, "M_GeneralStatement", SourceMLC, "M_GeneralStatement", zSET_NULL )
         SetMatchingAttributesByName( TargetMLC, "M_GeneralStatement", SourceMLC, "M_GeneralStatement", zSET_NULL );
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
      //:CREATE ENTITY TargetMLC.M_IngredientsSection
      RESULT = CreateEntity( TargetMLC, "M_IngredientsSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( TargetMLC, "M_IngredientsSection", SourceMLC, "M_IngredientsSection", zSET_NULL )
      SetMatchingAttributesByName( TargetMLC, "M_IngredientsSection", SourceMLC, "M_IngredientsSection", zSET_NULL );
      //:FOR EACH SourceMLC.M_IngredientsStatement
      RESULT = SetCursorFirstEntity( SourceMLC, "M_IngredientsStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY TargetMLC.M_IngredientsStatement
         RESULT = CreateEntity( TargetMLC, "M_IngredientsStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( TargetMLC, "M_IngredientsStatement", SourceMLC, "M_IngredientsStatement", zSET_NULL )
         SetMatchingAttributesByName( TargetMLC, "M_IngredientsStatement", SourceMLC, "M_IngredientsStatement", zSET_NULL );
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
      //:CREATE ENTITY TargetMLC.M_StorageDisposalSection
      RESULT = CreateEntity( TargetMLC, "M_StorageDisposalSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( TargetMLC, "M_StorageDisposalSection", SourceMLC, "M_StorageDisposalSection", zSET_NULL )
      SetMatchingAttributesByName( TargetMLC, "M_StorageDisposalSection", SourceMLC, "M_StorageDisposalSection", zSET_NULL );
      //:FOR EACH SourceMLC.M_StorageDisposalStatement
      RESULT = SetCursorFirstEntity( SourceMLC, "M_StorageDisposalStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY TargetMLC.M_StorageDisposalStatement
         RESULT = CreateEntity( TargetMLC, "M_StorageDisposalStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( TargetMLC, "M_StorageDisposalStatement", SourceMLC, "M_StorageDisposalStatement", zSET_NULL )
         SetMatchingAttributesByName( TargetMLC, "M_StorageDisposalStatement", SourceMLC, "M_StorageDisposalStatement", zSET_NULL );
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
      //:CREATE ENTITY TargetMLC.M_DirectionsForUseSection
      RESULT = CreateEntity( TargetMLC, "M_DirectionsForUseSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( TargetMLC, "M_DirectionsForUseSection", SourceMLC, "M_DirectionsForUseSection", zSET_NULL )
      SetMatchingAttributesByName( TargetMLC, "M_DirectionsForUseSection", SourceMLC, "M_DirectionsForUseSection", zSET_NULL );
      //:FOR EACH SourceMLC.M_DirectionsForUseStatement
      RESULT = SetCursorFirstEntity( SourceMLC, "M_DirectionsForUseStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY TargetMLC.M_DirectionsForUseStatement
         RESULT = CreateEntity( TargetMLC, "M_DirectionsForUseStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( TargetMLC, "M_DirectionsForUseStatement", SourceMLC, "M_DirectionsForUseStatement", zSET_NULL )
         SetMatchingAttributesByName( TargetMLC, "M_DirectionsForUseStatement", SourceMLC, "M_DirectionsForUseStatement", zSET_NULL );
         RESULT = SetCursorNextEntity( SourceMLC, "M_DirectionsForUseStatement", "" );
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
      //:CREATE ENTITY TargetMLC.M_MarketingSection
      RESULT = CreateEntity( TargetMLC, "M_MarketingSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( TargetMLC, "M_MarketingSection", SourceMLC, "M_MarketingSection", zSET_NULL )
      SetMatchingAttributesByName( TargetMLC, "M_MarketingSection", SourceMLC, "M_MarketingSection", zSET_NULL );
      //:FOR EACH SourceMLC.M_MarketingStatement
      RESULT = SetCursorFirstEntity( SourceMLC, "M_MarketingStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY TargetMLC.M_MarketingStatement
         RESULT = CreateEntity( TargetMLC, "M_MarketingStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( TargetMLC, "M_MarketingStatement", SourceMLC, "M_MarketingStatement", zSET_NULL )
         SetMatchingAttributesByName( TargetMLC, "M_MarketingStatement", SourceMLC, "M_MarketingStatement", zSET_NULL );
         RESULT = SetCursorNextEntity( SourceMLC, "M_MarketingStatement", "" );
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
      //:CREATE ENTITY TargetMLC.M_HumanHazardSection
      RESULT = CreateEntity( TargetMLC, "M_HumanHazardSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( TargetMLC, "M_HumanHazardSection", SourceMLC, "M_HumanHazardSection", zSET_NULL )
      SetMatchingAttributesByName( TargetMLC, "M_HumanHazardSection", SourceMLC, "M_HumanHazardSection", zSET_NULL );
      RESULT = SetCursorNextEntity( SourceMLC, "M_HumanHazardSection", "" );
   } 

   //:END

   //:// DirectionsForUse Usage
   //:FOR EACH SourceMLC.M_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( SourceMLC, "M_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST TargetMLC.M_DirectionsForUseSection
      //:           WHERE TargetMLC.M_DirectionsForUseSection.Title = SourceMLC.M_DirectionsForUseSection.Title
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, SourceMLC, "M_DirectionsForUseSection", "Title" );
      szTempString_0 = sb_szTempString_0.toString( );}
      RESULT = SetCursorFirstEntityByString( TargetMLC, "M_DirectionsForUseSection", "Title", szTempString_0, "" );
      //:FOR EACH SourceMLC.M_DirectionsUsage WITHIN SourceMLC.M_DirectionsForUseSection
      RESULT = SetCursorFirstEntity( SourceMLC, "M_DirectionsUsage", "M_DirectionsForUseSection" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CreateViewFromView( TargetMLC2, TargetMLC )
         CreateViewFromView( TargetMLC2, TargetMLC );
         //:SET CURSOR FIRST TargetMLC2.M_DirectionsUsage
         //:           WHERE TargetMLC2.M_DirectionsUsage.UsageType = SourceMLC.M_DirectionsUsage.UsageType
         //:             AND TargetMLC2.M_DirectionsUsage.Name = SourceMLC.M_DirectionsUsage.Name
         RESULT = SetCursorFirstEntity( TargetMLC2, "M_DirectionsUsage", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( TargetMLC2, "M_DirectionsUsage", "UsageType", SourceMLC, "M_DirectionsUsage", "UsageType" ) != 0 ||
                    CompareAttributeToAttribute( TargetMLC2, "M_DirectionsUsage", "Name", SourceMLC, "M_DirectionsUsage", "Name" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( TargetMLC2, "M_DirectionsUsage", "" );
            } 

         } 

         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:SET CURSOR FIRST TargetMLC.M_Usage WHERE TargetMLC.M_Usage.Name = SourceMLC.M_DirectionsUsage.Name
            //:                                     AND TargetMLC.M_Usage.UsageType = SourceMLC.M_DirectionsUsage.UsageType
            RESULT = SetCursorFirstEntity( TargetMLC, "M_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( TargetMLC, "M_Usage", "Name", SourceMLC, "M_DirectionsUsage", "Name" ) != 0 ||
                       CompareAttributeToAttribute( TargetMLC, "M_Usage", "UsageType", SourceMLC, "M_DirectionsUsage", "UsageType" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( TargetMLC, "M_Usage", "" );
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:IssueError( TargetMLC, 0, 0, "Programming Error 1" )
               IssueError( TargetMLC, 0, 0, "Programming Error 1" );
            } 

            //:END

            //:CREATE ENTITY TargetMLC.M_DirectionsUsageOrdering
            RESULT = CreateEntity( TargetMLC, "M_DirectionsUsageOrdering", zPOS_AFTER );
            //:INCLUDE TargetMLC.M_DirectionsUsage FROM TargetMLC.M_Usage
            RESULT = IncludeSubobjectFromSubobject( TargetMLC, "M_DirectionsUsage", TargetMLC, "M_Usage", zPOS_AFTER );
         } 

         //:END
         //:DropView( TargetMLC2 )
         DropView( TargetMLC2 );
         RESULT = SetCursorNextEntity( SourceMLC, "M_DirectionsUsage", "M_DirectionsForUseSection" );
      } 

      RESULT = SetCursorNextEntity( SourceMLC, "M_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// Marketing Usage
   //:FOR EACH SourceMLC.M_MarketingSection
   RESULT = SetCursorFirstEntity( SourceMLC, "M_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST TargetMLC.M_MarketingSection
      //:           WHERE TargetMLC.M_MarketingSection.Title = SourceMLC.M_MarketingSection.Title
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, SourceMLC, "M_MarketingSection", "Title" );
      szTempString_0 = sb_szTempString_0.toString( );}
      RESULT = SetCursorFirstEntityByString( TargetMLC, "M_MarketingSection", "Title", szTempString_0, "" );
      //:FOR EACH SourceMLC.M_MarketingUsage WITHIN SourceMLC.M_MarketingSection
      RESULT = SetCursorFirstEntity( SourceMLC, "M_MarketingUsage", "M_MarketingSection" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CreateViewFromView( TargetMLC2, TargetMLC )
         CreateViewFromView( TargetMLC2, TargetMLC );
         //:SET CURSOR FIRST TargetMLC2.M_MarketingUsage
         //:           WHERE TargetMLC2.M_MarketingUsage.UsageType = SourceMLC.M_MarketingUsage.UsageType
         //:             AND TargetMLC2.M_MarketingUsage.Name = SourceMLC.M_MarketingUsage.Name
         RESULT = SetCursorFirstEntity( TargetMLC2, "M_MarketingUsage", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( TargetMLC2, "M_MarketingUsage", "UsageType", SourceMLC, "M_MarketingUsage", "UsageType" ) != 0 ||
                    CompareAttributeToAttribute( TargetMLC2, "M_MarketingUsage", "Name", SourceMLC, "M_MarketingUsage", "Name" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( TargetMLC2, "M_MarketingUsage", "" );
            } 

         } 

         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:SET CURSOR FIRST TargetMLC.M_Usage WHERE TargetMLC.M_Usage.Name = SourceMLC.M_MarketingUsage.Name
            //:                                     AND TargetMLC.M_Usage.UsageType = SourceMLC.M_MarketingUsage.UsageType
            RESULT = SetCursorFirstEntity( TargetMLC, "M_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( TargetMLC, "M_Usage", "Name", SourceMLC, "M_MarketingUsage", "Name" ) != 0 ||
                       CompareAttributeToAttribute( TargetMLC, "M_Usage", "UsageType", SourceMLC, "M_MarketingUsage", "UsageType" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( TargetMLC, "M_Usage", "" );
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:IssueError( TargetMLC,0,0, "Programming Error 1" )
               IssueError( TargetMLC, 0, 0, "Programming Error 1" );
            } 

            //:END
            //:CREATE ENTITY TargetMLC.M_MarketingUsageOrdering
            RESULT = CreateEntity( TargetMLC, "M_MarketingUsageOrdering", zPOS_AFTER );
            //:INCLUDE TargetMLC.M_MarketingUsage FROM TargetMLC.M_Usage
            RESULT = IncludeSubobjectFromSubobject( TargetMLC, "M_MarketingUsage", TargetMLC, "M_Usage", zPOS_AFTER );
         } 

         //:END
         //:DropView( TargetMLC2 )
         DropView( TargetMLC2 );
         RESULT = SetCursorNextEntity( SourceMLC, "M_MarketingUsage", "M_MarketingSection" );
      } 

      RESULT = SetCursorNextEntity( SourceMLC, "M_MarketingSection", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:OBJECT CONSTRAINT OPERATION
//:ObjectConstraints( VIEW mMasLC BASED ON LOD mMasLC,
//:                   SHORT Event,
//:                   SHORT State )
public int 
omMasLC_ObjectConstraints( View     mMasLC,
                           Integer   Event,
                           Integer   State )
{


   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :
         break ;

      //:// No current code.

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
//:dFullHazardStatement( VIEW mMasLC BASED ON LOD mMasLC,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )

//:   STRING ( 256 ) szString
public int 
omMasLC_dFullHazardStatement( View     mMasLC,
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

         //:IF mMasLC.M_HumanHazardSection  EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "M_HumanHazardSection" );
         if ( lTempInteger_0 == 0 )
         { 

            //:szString = mMasLC.M_HumanHazardSection.PrecautionaryStatement
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 257, mMasLC, "M_HumanHazardSection", "PrecautionaryStatement", "", 0 );
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
               //:szLocation = mMasLC.M_HumanHazardSection.Location1
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_2, 'S', 257, mMasLC, "M_HumanHazardSection", "Location1", "", 0 );
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

               //:szLocation = mMasLC.M_HumanHazardSection.Location2
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_3, 'S', 257, mMasLC, "M_HumanHazardSection", "Location2", "", 0 );
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

               //:szLocation = mMasLC.M_HumanHazardSection.Location3
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_4, 'S', 257, mMasLC, "M_HumanHazardSection", "Location3", "", 0 );
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

               //:szLocation = mMasLC.M_HumanHazardSection.Location4
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_5, 'S', 257, mMasLC, "M_HumanHazardSection", "Location4", "", 0 );
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

               //:szLocation = mMasLC.M_HumanHazardSection.Location5
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
               StringBuilder sb_szLocation;
               if ( szLocation == null )
                  sb_szLocation = new StringBuilder( 32 );
               else
                  sb_szLocation = new StringBuilder( szLocation );
                               GetVariableFromAttribute( sb_szLocation, mi_lTempInteger_6, 'S', 257, mMasLC, "M_HumanHazardSection", "Location5", "", 0 );
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
         //:StoreStringInRecord( mMasLC,
         //:                   InternalEntityStructure,
         //:                   InternalAttribStructure, szString )
         StoreStringInRecord( mMasLC, InternalEntityStructure, InternalAttribStructure, szString );
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
   //:dEPA_RegistrationNbr( VIEW mMasLC BASED ON LOD mMasLC,
   //:                   STRING ( 32 ) InternalEntityStructure,
   //:                   STRING ( 32 ) InternalAttribStructure,
   //:                   SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
omMasLC_dEPA_RegistrationNbr( View     mMasLC,
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

         //:IF mMasLC.PrimaryRegistrant EXISTS AND mMasLC.MasterProduct EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "PrimaryRegistrant" );
         lTempInteger_1 = CheckExistenceOfEntity( mMasLC, "MasterProduct" );
         if ( lTempInteger_0 == 0 && lTempInteger_1 == 0 )
         { 
            //:szString = mMasLC.PrimaryRegistrant.EPA_CompanyNumber + "-" + mMasLC.MasterProduct.Number
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttribute( sb_szString, mMasLC, "PrimaryRegistrant", "EPA_CompanyNumber" );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, "-", 1, 0, 1001 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 129, mMasLC, "MasterProduct", "Number", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 1001 );
            szString = sb_szString.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szString = "EPA Registration Number unknown"
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringCopy( sb_szString, 1, 0, "EPA Registration Number unknown", 1, 0, 1001 );
            szString = sb_szString.toString( );}
         } 

         //:END


         //:// Store the calculated value in the object.
         //:StoreStringInRecord( mMasLC,
         //:                  InternalEntityStructure,
         //:                  InternalAttribStructure, szString )
         StoreStringInRecord( mMasLC, InternalEntityStructure, InternalAttribStructure, szString );
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
   //:dTitleText( VIEW mMasLC BASED ON LOD mMasLC,
   //:         STRING ( 32 ) InternalEntityStructure,
   //:         STRING ( 32 ) InternalAttribStructure,
   //:         SHORT GetOrSetFlag )

   //:STRING ( 1000 ) szString
public int 
omMasLC_dTitleText( View     mMasLC,
                    String InternalEntityStructure,
                    String InternalAttribStructure,
                    Integer   GetOrSetFlag )
{
   String   szString = null;
   //:STRING ( 256 ) szTitle
   String   szTitle = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mMasLC.M_IngredientsStatement EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "M_IngredientsStatement" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szTitle = mMasLC.M_StorageDisposalStatement.Title
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTitle;
            if ( szTitle == null )
               sb_szTitle = new StringBuilder( 32 );
            else
               sb_szTitle = new StringBuilder( szTitle );
                         GetVariableFromAttribute( sb_szTitle, mi_lTempInteger_1, 'S', 257, mMasLC, "M_StorageDisposalStatement", "Title", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTitle = sb_szTitle.toString( );}
            //:IF szTitle != ""
            if ( ZeidonStringCompare( szTitle, 1, 0, "", 1, 0, 257 ) != 0 )
            { 
               //:szString = "<strong>" + szTitle + "</strong>" 
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringCopy( sb_szString, 1, 0, "<strong>", 1, 0, 1001 );
               szString = sb_szString.toString( );}
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, szTitle, 1, 0, 1001 );
               szString = sb_szString.toString( );}
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, "</strong>", 1, 0, 1001 );
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

            //:szString = szString + mMasLC.M_StorageDisposalStatement.Text
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 32001, mMasLC, "M_StorageDisposalStatement", "Text", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 1001 );
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
         //:StoreStringInRecord( mMasLC,
         //:                  InternalEntityStructure,
         //:                  InternalAttribStructure, szString )
         StoreStringInRecord( mMasLC, InternalEntityStructure, InternalAttribStructure, szString );
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
