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

package com.quinsoft.swauopencuas;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.quinsoft.swauopencuas.ZGLOBAL1_Operation;
import com.quinsoft.swauopencuas.ZGLOBAL2_Operation;
//import com.quinsoft.swauopencuas.mProspct_Object;

/**
   @author QuinSoft
**/

public class mPerson_Object extends VmlObjectOperations
{
   public mPerson_Object( View view )
   {
      super( view );
   }


//:TRANSFORMATION OPERATION
public int 
omPerson_ActivateAllFaculty( zVIEW    mPerson,
                             View     ViewToWindow )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateAllFaculty( VIEW mPerson BASED ON LOD mPerson,
   //:                 VIEW ViewToWindow )

   //:ACTIVATE mPerson MULTIPLE
   //:   WHERE mPerson.Faculty EXISTS
   omPerson_fnLocalBuildQual_0( ViewToWindow, vTempViewVar_0 );
   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );

   //:OrderEntityForView( mPerson, "Person", "LastName A, FirstName A" )
   OrderEntityForView( mPerson, "Person", "LastName A, FirstName A" );
   return( 0 );
// END
} 


private int 
omPerson_fnLocalBuildQual_0( View     vSubtask,
                             zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Faculty" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
   return( 0 );
} 


private int 
omPerson_fnLocalBuildQual_1( View     vSubtask,
                             zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Prospect" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
   return( 0 );
} 


private int 
omPerson_fnLocalBuildQual_2( View     vSubtask,
                             zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Staff" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
   return( 0 );
} 


private int 
omPerson_fnLocalBuildQual_3( View     vSubtask,
                             zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Staff" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Faculty" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
   return( 0 );
} 


private int 
omPerson_fnLocalBuildQual_4( View     vSubtask,
                             zVIEW    vQualObject,
                             String   szTempString_1,
                             String   szTempString_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "LastName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_1.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "FirstName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_2.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DERIVED ATTRIBUTE OPERATION
//:dPersonType( VIEW mPerson BASED ON LOD mPerson,
//:             STRING ( 32 ) InternalEntityStructure,
//:             STRING ( 32 ) InternalAttribStructure,
//:             SHORT GetOrSetFlag )

//:   STRING ( 100 ) szType
public int 
omPerson_dPersonType( View     mPerson,
                      String InternalEntityStructure,
                      String InternalAttribStructure,
                      Integer   GetOrSetFlag )
{
   String   szType = null;
   //:STRING ( 100 ) szTypeN
   String   szTypeN = null;
   //:STRING ( 20 )  szAdminDiv
   String   szAdminDiv = null;
   //:SHORT          StringLength
   int      StringLength = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;



   //:szType = ""
    {StringBuilder sb_szType;
   if ( szType == null )
      sb_szType = new StringBuilder( 32 );
   else
      sb_szType = new StringBuilder( szType );
      ZeidonStringCopy( sb_szType, 1, 0, "", 1, 0, 101 );
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
                  ZeidonStringCopy( sb_szType, 1, 0, "", 1, 0, 101 );
         szType = sb_szType.toString( );}
         //:FOR EACH mPerson.Prospect
         RESULT = SetCursorFirstEntity( mPerson, "Prospect", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mPerson.AdministrativeDivision.ID = 1
            if ( CompareAttributeToInteger( mPerson, "AdministrativeDivision", "ID", 1 ) == 0 )
            { 
               //:szType = szType + "MC Prospect, "
                {StringBuilder sb_szType;
               if ( szType == null )
                  sb_szType = new StringBuilder( 32 );
               else
                  sb_szType = new StringBuilder( szType );
                              ZeidonStringConcat( sb_szType, 1, 0, "MC Prospect, ", 1, 0, 101 );
               szType = sb_szType.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szType = szType + "AS Prospect, "
                {StringBuilder sb_szType;
               if ( szType == null )
                  sb_szType = new StringBuilder( 32 );
               else
                  sb_szType = new StringBuilder( szType );
                              ZeidonStringConcat( sb_szType, 1, 0, "AS Prospect, ", 1, 0, 101 );
               szType = sb_szType.toString( );}
            } 

            RESULT = SetCursorNextEntity( mPerson, "Prospect", "" );
            //:END
         } 

         //:END
         //:IF mPerson.Faculty EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mPerson, "Faculty" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szType = szType + "Instructor, "
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, "Instructor, ", 1, 0, 101 );
            szType = sb_szType.toString( );}
         } 

         //:END
         //:IF mPerson.Staff EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mPerson, "Staff" );
         if ( lTempInteger_1 == 0 )
         { 
            //:szType = szType + "Staff, "
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, "Staff, ", 1, 0, 101 );
            szType = sb_szType.toString( );}
         } 

         //:END
         //:IF mPerson.Donor EXISTS
         lTempInteger_2 = CheckExistenceOfEntity( mPerson, "Donor" );
         if ( lTempInteger_2 == 0 )
         { 
            //:szType = szType + "Donor, "
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, "Donor, ", 1, 0, 101 );
            szType = sb_szType.toString( );}
         } 

         //:END
         //:IF mPerson.Student EXISTS
         lTempInteger_3 = CheckExistenceOfEntity( mPerson, "Student" );
         if ( lTempInteger_3 == 0 )
         { 
            //:szType = szType + mPerson.StudentAdministrativeDivision.NamePrefix + " Student, "
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_4, 'S', 6, mPerson, "StudentAdministrativeDivision", "NamePrefix", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, szTempString_0, 1, 0, 101 );
            szType = sb_szType.toString( );}
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, " Student, ", 1, 0, 101 );
            szType = sb_szType.toString( );}
         } 

         //:END
         //:IF mPerson.Alumni EXISTS
         lTempInteger_5 = CheckExistenceOfEntity( mPerson, "Alumni" );
         if ( lTempInteger_5 == 0 )
         { 
            //:szType = szType + "Alumni, "
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, "Alumni, ", 1, 0, 101 );
            szType = sb_szType.toString( );}
         } 

         //:END
         //:IF mPerson.Employee EXISTS
         lTempInteger_6 = CheckExistenceOfEntity( mPerson, "Employee" );
         if ( lTempInteger_6 == 0 )
         { 
            //:szType = szType + "Employee, "
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, "Employee, ", 1, 0, 101 );
            szType = sb_szType.toString( );}
         } 

         //:END
         //:IF mPerson.FinAidProfile EXISTS
         lTempInteger_7 = CheckExistenceOfEntity( mPerson, "FinAidProfile" );
         if ( lTempInteger_7 == 0 )
         { 
            //:szType = szType + "Fin Aid, "
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, "Fin Aid, ", 1, 0, 101 );
            szType = sb_szType.toString( );}
         } 

         //:END
         //:IF mPerson.Vendor EXISTS
         lTempInteger_8 = CheckExistenceOfEntity( mPerson, "Vendor" );
         if ( lTempInteger_8 == 0 )
         { 
            //:szType = szType + "Vendor, "
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, "Vendor, ", 1, 0, 101 );
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
                  ZeidonStringCopy( sb_szTypeN, 1, 0, szType, 1, StringLength, 101 );
         szTypeN = sb_szTypeN.toString( );}
         //:StoreStringInRecord ( mPerson,
         //:                      InternalEntityStructure, InternalAttribStructure, szTypeN )
         StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, szTypeN );
         break ;

      //:/* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:   /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omPerson_ActivateFacultyStaff( zVIEW    mPerson,
                               View     ViewToWindow )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateFacultyStaff( VIEW mPerson BASED ON LOD mPerson,
   //:                   VIEW ViewToWindow )

   //:ACTIVATE mPerson MULTIPLE
   //:   WHERE mPerson.Staff EXISTS OR mPerson.Faculty EXISTS
   omPerson_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0 );
   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );

   //:OrderEntityForView( mPerson, "Person", "LastName A, FirstName A" )
   OrderEntityForView( mPerson, "Person", "LastName A, FirstName A" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omPerson_ActivateAllProspects( zVIEW    mPerson,
                               View     ViewToWindow )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateAllProspects( VIEW mPerson BASED ON LOD mPerson,
   //:                   VIEW ViewToWindow )

   //:ACTIVATE mPerson MULTIPLE
   //:   WHERE mPerson.Prospect EXISTS
   omPerson_fnLocalBuildQual_1( ViewToWindow, vTempViewVar_0 );
   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );

   //:OrderEntityForView( mPerson, "Person", "LastName A, FirstName A" )
   OrderEntityForView( mPerson, "Person", "LastName A, FirstName A" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omPerson_ActivateAllStaff( zVIEW    mPerson,
                           View     ViewToWindow )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateAllStaff( VIEW mPerson BASED ON LOD mPerson,
   //:               VIEW ViewToWindow )


   //:ACTIVATE mPerson MULTIPLE
   //:   WHERE mPerson.Staff EXISTS
   omPerson_fnLocalBuildQual_2( ViewToWindow, vTempViewVar_0 );
   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );

   //:OrderEntityForView( mPerson, "Person", "LastName A, FirstName A" )
   OrderEntityForView( mPerson, "Person", "LastName A, FirstName A" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omPerson_ActivateAllPersonList( zVIEW    mPerson,
                                View     ViewToWindow )
{
   int      RESULT = 0;

   //:ActivateAllPersonList( VIEW mPerson BASED ON LOD mPerson,
   //:                    VIEW ViewToWindow )

   //:ACTIVATE mPerson MULTIPLE
   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, 0, zMULTIPLE );

   //:OrderEntityForView( mPerson, "Person", "LastName A, FirstName A" )
   OrderEntityForView( mPerson, "Person", "LastName A, FirstName A" );
   return( 0 );
// END
} 


//:OBJECT CONSTRAINT OPERATION
//:ObjectConstraint( VIEW mPerson BASED ON LOD mPerson,
//:                  SHORT Event,
//:                  SHORT State )

//:   VIEW mPerson2   BASED ON LOD  mPerson
public int 
omPerson_ObjectConstraint( View     mPerson,
                           Integer   Event,
                           Integer   State )
{
   zVIEW    mPerson2 = new zVIEW( );
   //:VIEW mPerson3   BASED ON LOD  mPerson
   zVIEW    mPerson3 = new zVIEW( );
   //:VIEW mDupPerson BASED ON LOD  mPerson
   zVIEW    mDupPerson = new zVIEW( );
   //:VIEW mDonor     BASED ON LOD  mDonor
   zVIEW    mDonor = new zVIEW( );
   //:VIEW mStudent   BASED ON LOD  mStudent
   zVIEW    mStudent = new zVIEW( );
   //:VIEW mProspct   BASED ON LOD  mProspct
   zVIEW    mProspct = new zVIEW( );
   //:VIEW mFaculty   BASED ON LOD  mFaculty
   zVIEW    mFaculty = new zVIEW( );
   //:VIEW mAdmDiv    BASED ON LOD  mAdmDiv
   zVIEW    mAdmDiv = new zVIEW( );
   //:VIEW sAppMgr    BASED ON LOD  sAppMgr
   zVIEW    sAppMgr = new zVIEW( );
   //:VIEW mUser      BASED ON LOD  mUser
   zVIEW    mUser = new zVIEW( );
   //:STRING ( 1 )   szRole
   String   szRole = null;
   //:STRING ( 32 )  szFunctionalArea
   String   szFunctionalArea = null;
   //:STRING ( 5 )   szDupCount
   String   szDupCount = null;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   //:INTEGER nRC 
   int      nRC = 0;
   //:INTEGER DupCount
   int      DupCount = 0;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_11 = 0;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   String   szTempString_3 = null;
   int      lTempInteger_12 = 0;
   String   szTempString_4 = null;
   int      lTempInteger_13 = 0;
   int      lTempInteger_14 = 0;


   //:CASE Event
   switch( Event )
   { 
      //: OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //: IF State != 0
         if ( State != 0 )
         { 
            //: // Return if this is not a regular Activate OI. (For example, an ActivateOI_FromOI)
            //: RETURN 0
            if(8==8)return( 0 );
         } 

         //: END
         //: 
         //: // Make sure Demographics entity exists.
         //: IF mPerson.Demographics DOES NOT EXIST
         lTempInteger_0 = CheckExistenceOfEntity( mPerson, "Demographics" );
         if ( lTempInteger_0 != 0 )
         { 
            //: CREATE ENTITY mPerson.Demographics 
            RESULT = CreateEntity( mPerson, "Demographics", zPOS_AFTER );
         } 

         //: END

         //: CreateViewFromView( mPerson2, mPerson)
         CreateViewFromView( mPerson2, mPerson );

         //: // Relink PrimaryAddress with Address.
         //: IF mPerson2.PrimaryAddress EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mPerson2, "PrimaryAddress" );
         if ( lTempInteger_1 == 0 )
         { 
            //: SET CURSOR FIRST mPerson2.Address WHERE mPerson2.Address.ID = mPerson2.PrimaryAddress.ID
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, mPerson2, "PrimaryAddress", "ID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mPerson2, "Address", "ID", lTempInteger_2, "" );
            //: RelinkInstanceToInstance( mPerson2, "PrimaryAddress",
            //:                           mPerson2, "Address" )
            RelinkInstanceToInstance( mPerson2, "PrimaryAddress", mPerson2, "Address" );
         } 

         //: END
         //: 
         //: FOR EACH mPerson2.Category 
         RESULT = SetCursorFirstEntity( mPerson2, "Category", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //: INCLUDE  mPerson2.ListedCategory FROM  mPerson2.Category 
            RESULT = IncludeSubobjectFromSubobject( mPerson2, "ListedCategory", mPerson2, "Category", zPOS_AFTER );
            RESULT = SetCursorNextEntity( mPerson2, "Category", "" );
         } 

         //: END
         //: FOR EACH mPerson2.FacultyCategory 
         RESULT = SetCursorFirstEntity( mPerson2, "FacultyCategory", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //: INCLUDE  mPerson2.ListedCategory FROM  mPerson2.FacultyCategory 
            RESULT = IncludeSubobjectFromSubobject( mPerson2, "ListedCategory", mPerson2, "FacultyCategory", zPOS_AFTER );
            RESULT = SetCursorNextEntity( mPerson2, "FacultyCategory", "" );
         } 

         //: END
         //: FOR EACH mPerson2.ProspectCategory 
         RESULT = SetCursorFirstEntity( mPerson2, "ProspectCategory", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //: INCLUDE  mPerson2.ListedCategory FROM  mPerson2.ProspectCategory 
            RESULT = IncludeSubobjectFromSubobject( mPerson2, "ListedCategory", mPerson2, "ProspectCategory", zPOS_AFTER );
            RESULT = SetCursorNextEntity( mPerson2, "ProspectCategory", "" );
         } 

         //: END
         //: FOR EACH mPerson2.StudentCategory 
         RESULT = SetCursorFirstEntity( mPerson2, "StudentCategory", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //: INCLUDE  mPerson2.ListedCategory FROM  mPerson2.StudentCategory 
            RESULT = IncludeSubobjectFromSubobject( mPerson2, "ListedCategory", mPerson2, "StudentCategory", zPOS_AFTER );
            RESULT = SetCursorNextEntity( mPerson2, "StudentCategory", "" );
         } 

         //: END
         //: FOR EACH mPerson2.StaffCategory 
         RESULT = SetCursorFirstEntity( mPerson2, "StaffCategory", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //: INCLUDE  mPerson2.ListedCategory FROM  mPerson2.StaffCategory 
            RESULT = IncludeSubobjectFromSubobject( mPerson2, "ListedCategory", mPerson2, "StaffCategory", zPOS_AFTER );
            RESULT = SetCursorNextEntity( mPerson2, "StaffCategory", "" );
         } 

         //: END
         //: 
         //: // Set the work Callback Date, if a Callback entry exists for the current User.
         //: nRC = GetViewByName( mUser, "mUser", mPerson, zLEVEL_APPLICATION )
         nRC = GetViewByName( mUser, "mUser", mPerson, zLEVEL_APPLICATION );
         //: IF nRC >= 0 
         if ( nRC >= 0 )
         { 
            //: SET CURSOR FIRST mPerson.CallbackUser WITHIN mPerson.Person 
            //:            WHERE mPerson.CallbackUser.ID = mUser.User.ID 
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                         GetIntegerFromAttribute( mi_lTempInteger_3, mUser, "User", "ID" );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mPerson, "CallbackUser", "ID", lTempInteger_3, "Person" );
            //: IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //: mPerson.Person.wCallbackDate   = mPerson.Callback.Date 
               SetAttributeFromAttribute( mPerson, "Person", "wCallbackDate", mPerson, "Callback", "Date" );
            } 

            //: END 
         } 

         //: END
         //: 
         //: // Set the work Emailback Date, if a Emailback entry exists for the current User.
         //: nRC = GetViewByName( mUser, "mUser", mPerson, zLEVEL_APPLICATION )
         nRC = GetViewByName( mUser, "mUser", mPerson, zLEVEL_APPLICATION );
         //: IF nRC >= 0 
         if ( nRC >= 0 )
         { 
            //: SET CURSOR FIRST mPerson.EmailbackUser WITHIN mPerson.Person 
            //:            WHERE mPerson.EmailbackUser.ID = mUser.User.ID 
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                         GetIntegerFromAttribute( mi_lTempInteger_4, mUser, "User", "ID" );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mPerson, "EmailbackUser", "ID", lTempInteger_4, "Person" );
            //: IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //: mPerson.Person.wEmailbackDate   = mPerson.Emailback.Date 
               SetAttributeFromAttribute( mPerson, "Person", "wEmailbackDate", mPerson, "Emailback", "Date" );
            } 

            //: END 
         } 

         //: END

         //: // Build the derived entity path FamilyPerson
         //: BuildFamilyRole( mPerson )
         {
          ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mPerson );
          m_ZGLOBAL2_Operation.BuildFamilyRole( mPerson );
          // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
         }
         //: 
         //: nRC = GetViewByName( sAppMgr, "sAppMgr", mPerson, zLEVEL_APPLICATION )
         nRC = GetViewByName( sAppMgr, "sAppMgr", mPerson, zLEVEL_APPLICATION );
         //: IF  nRC > 0  
         if ( nRC > 0 )
         { 
            //: szFunctionalArea = sAppMgr.FunctionalArea.FunctionalAreaName 
            {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
            StringBuilder sb_szFunctionalArea;
            if ( szFunctionalArea == null )
               sb_szFunctionalArea = new StringBuilder( 32 );
            else
               sb_szFunctionalArea = new StringBuilder( szFunctionalArea );
                         GetVariableFromAttribute( sb_szFunctionalArea, mi_lTempInteger_5, 'S', 33, sAppMgr, "FunctionalArea", "FunctionalAreaName", "", 0 );
            lTempInteger_5 = mi_lTempInteger_5.intValue( );
            szFunctionalArea = sb_szFunctionalArea.toString( );}
            //:ELSE 
         } 
         else
         { 
            //: szFunctionalArea = ""
             {StringBuilder sb_szFunctionalArea;
            if ( szFunctionalArea == null )
               sb_szFunctionalArea = new StringBuilder( 32 );
            else
               sb_szFunctionalArea = new StringBuilder( szFunctionalArea );
                        ZeidonStringCopy( sb_szFunctionalArea, 1, 0, "", 1, 0, 33 );
            szFunctionalArea = sb_szFunctionalArea.toString( );}
         } 

         //: END 
         //: // Build the combined Contact Activity list of Person and Donor, if Donor exists, or
         //: // Person and Student, if Student exists.
         //: IF mPerson.Donor EXISTS
         lTempInteger_6 = CheckExistenceOfEntity( mPerson, "Donor" );
         if ( lTempInteger_6 == 0 )
         { 
            //: GET VIEW mDonor NAMED "mDonor"
            RESULT = GetViewByName( mDonor, "mDonor", mPerson, zLEVEL_TASK );
            //: IF RESULT >= 0
            if ( RESULT >= 0 )
            { 
               //: IF mPerson.Donor.ID = mDonor.Donor.ID  
               if ( CompareAttributeToAttribute( mPerson, "Donor", "ID", mDonor, "Donor", "ID" ) == 0 )
               { 
                  //: FOR EACH mDonor.ContactActivity
                  RESULT = SetCursorFirstEntity( mDonor, "ContactActivity", "" );
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //: INCLUDE mPerson.ListedContactActivity FROM mDonor.ContactActivity 
                     RESULT = IncludeSubobjectFromSubobject( mPerson, "ListedContactActivity", mDonor, "ContactActivity", zPOS_AFTER );
                     //: mPerson.ListedContactActivity.wObjectType = "Donor"
                     SetAttributeFromString( mPerson, "ListedContactActivity", "wObjectType", "Donor" );
                     RESULT = SetCursorNextEntity( mDonor, "ContactActivity", "" );
                  } 

                  //: END
               } 

               //: END
            } 

            //: END
         } 

         //: END
         //: IF mPerson.Student EXISTS
         lTempInteger_7 = CheckExistenceOfEntity( mPerson, "Student" );
         if ( lTempInteger_7 == 0 )
         { 
            //: GET VIEW mStudent NAMED "mStudent"
            RESULT = GetViewByName( mStudent, "mStudent", mPerson, zLEVEL_TASK );
            //: IF RESULT >= 0
            if ( RESULT >= 0 )
            { 
               //: IF mPerson.Student.ID = mStudent.Student.ID  
               if ( CompareAttributeToAttribute( mPerson, "Student", "ID", mStudent, "Student", "ID" ) == 0 )
               { 
                  //: FOR EACH mStudent.ContactActivity
                  RESULT = SetCursorFirstEntity( mStudent, "ContactActivity", "" );
                  while ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     //: IF mStudent.ContactActivity.PrivateToArea = "Y"
                     if ( CompareAttributeToString( mStudent, "ContactActivity", "PrivateToArea", "Y" ) == 0 )
                     { 
                        //: IF  mStudent.ContactActivity.FunctionalArea =  szFunctionalArea
                        if ( CompareAttributeToString( mStudent, "ContactActivity", "FunctionalArea", szFunctionalArea ) == 0 )
                        { 
                           //: INCLUDE mPerson.ListedContactActivity FROM mStudent.ContactActivity 
                           RESULT = IncludeSubobjectFromSubobject( mPerson, "ListedContactActivity", mStudent, "ContactActivity", zPOS_AFTER );
                           //: mPerson.ListedContactActivity.wObjectType = "Student"
                           SetAttributeFromString( mPerson, "ListedContactActivity", "wObjectType", "Student" );
                        } 

                        //: END 
                        //:ELSE 
                     } 
                     else
                     { 
                        //: INCLUDE mPerson.ListedContactActivity FROM mStudent.ContactActivity 
                        RESULT = IncludeSubobjectFromSubobject( mPerson, "ListedContactActivity", mStudent, "ContactActivity", zPOS_AFTER );
                        //: mPerson.ListedContactActivity.wObjectType = "Student"
                        SetAttributeFromString( mPerson, "ListedContactActivity", "wObjectType", "Student" );
                     } 

                     RESULT = SetCursorNextEntity( mStudent, "ContactActivity", "" );
                     //: END
                  } 

                  //: END
               } 

               //: END
               //:ELSE 
            } 
            else
            { 
               //: GET VIEW mStudent NAMED "mFAStu"
               RESULT = GetViewByName( mStudent, "mFAStu", mPerson, zLEVEL_TASK );
               //: IF RESULT >= 0
               if ( RESULT >= 0 )
               { 
                  //: IF mPerson.Student.ID = mStudent.Student.ID  
                  if ( CompareAttributeToAttribute( mPerson, "Student", "ID", mStudent, "Student", "ID" ) == 0 )
                  { 
                     //: FOR EACH mStudent.ContactActivity
                     RESULT = SetCursorFirstEntity( mStudent, "ContactActivity", "" );
                     while ( RESULT > zCURSOR_UNCHANGED )
                     { 
                        //: IF mStudent.ContactActivity.PrivateToArea = "Y"
                        if ( CompareAttributeToString( mStudent, "ContactActivity", "PrivateToArea", "Y" ) == 0 )
                        { 
                           //: IF mStudent.ContactActivity.FunctionalArea =  szFunctionalArea
                           if ( CompareAttributeToString( mStudent, "ContactActivity", "FunctionalArea", szFunctionalArea ) == 0 )
                           { 
                              //: INCLUDE mPerson.ListedContactActivity FROM mStudent.ContactActivity 
                              RESULT = IncludeSubobjectFromSubobject( mPerson, "ListedContactActivity", mStudent, "ContactActivity", zPOS_AFTER );
                              //: mPerson.ListedContactActivity.wObjectType = "Student"
                              SetAttributeFromString( mPerson, "ListedContactActivity", "wObjectType", "Student" );
                           } 

                           //: END 
                           //:ELSE 
                        } 
                        else
                        { 
                           //: INCLUDE mPerson.ListedContactActivity FROM mStudent.ContactActivity 
                           RESULT = IncludeSubobjectFromSubobject( mPerson, "ListedContactActivity", mStudent, "ContactActivity", zPOS_AFTER );
                           //: mPerson.ListedContactActivity.wObjectType = "Student"
                           SetAttributeFromString( mPerson, "ListedContactActivity", "wObjectType", "Student" );
                        } 

                        RESULT = SetCursorNextEntity( mStudent, "ContactActivity", "" );
                        //: END
                     } 

                     //: END
                  } 

                  //: END
                  //:ELSE 
               } 
               else
               { 
                  //: GET VIEW mStudent NAMED "mSAStu"
                  RESULT = GetViewByName( mStudent, "mSAStu", mPerson, zLEVEL_TASK );
                  //: IF RESULT >= 0
                  if ( RESULT >= 0 )
                  { 
                     //: IF mPerson.Student.ID = mStudent.Student.ID  
                     if ( CompareAttributeToAttribute( mPerson, "Student", "ID", mStudent, "Student", "ID" ) == 0 )
                     { 
                        //: FOR EACH mStudent.ContactActivity
                        RESULT = SetCursorFirstEntity( mStudent, "ContactActivity", "" );
                        while ( RESULT > zCURSOR_UNCHANGED )
                        { 
                           //: IF mStudent.ContactActivity.PrivateToArea = "Y"
                           if ( CompareAttributeToString( mStudent, "ContactActivity", "PrivateToArea", "Y" ) == 0 )
                           { 
                              //: IF mStudent.ContactActivity.FunctionalArea =  szFunctionalArea
                              if ( CompareAttributeToString( mStudent, "ContactActivity", "FunctionalArea", szFunctionalArea ) == 0 )
                              { 
                                 //: INCLUDE mPerson.ListedContactActivity FROM mStudent.ContactActivity 
                                 RESULT = IncludeSubobjectFromSubobject( mPerson, "ListedContactActivity", mStudent, "ContactActivity", zPOS_AFTER );
                                 //: mPerson.ListedContactActivity.wObjectType = "Student"
                                 SetAttributeFromString( mPerson, "ListedContactActivity", "wObjectType", "Student" );
                              } 

                              //: END 
                              //:ELSE 
                           } 
                           else
                           { 
                              //: INCLUDE mPerson.ListedContactActivity FROM mStudent.ContactActivity 
                              RESULT = IncludeSubobjectFromSubobject( mPerson, "ListedContactActivity", mStudent, "ContactActivity", zPOS_AFTER );
                              //: mPerson.ListedContactActivity.wObjectType = "Student"
                              SetAttributeFromString( mPerson, "ListedContactActivity", "wObjectType", "Student" );
                           } 

                           RESULT = SetCursorNextEntity( mStudent, "ContactActivity", "" );
                           //: END
                        } 

                        //: END
                     } 

                     //: END
                  } 

                  //: END
               } 

               //: END
            } 

            //: END
         } 

         //: END
         //: IF mPerson.Prospect EXISTS
         lTempInteger_8 = CheckExistenceOfEntity( mPerson, "Prospect" );
         if ( lTempInteger_8 == 0 )
         { 
            //: GET VIEW mProspct NAMED "mProspct"
            RESULT = GetViewByName( mProspct, "mProspct", mPerson, zLEVEL_TASK );
            //: IF RESULT >= 0
            if ( RESULT >= 0 )
            { 
               //: FOR EACH mProspct.StudentContactActivity WHERE mProspct.StudentContactActivity.FunctionalArea = "FinancialAid" 
               RESULT = SetCursorFirstEntityByString( mProspct, "StudentContactActivity", "FunctionalArea", "FinancialAid", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //: INCLUDE mPerson.ListedContactActivity FROM mProspct.StudentContactActivity 
                  RESULT = IncludeSubobjectFromSubobject( mPerson, "ListedContactActivity", mProspct, "StudentContactActivity", zPOS_AFTER );
                  //: mPerson.ListedContactActivity.wObjectType = "Fin Aid"
                  SetAttributeFromString( mPerson, "ListedContactActivity", "wObjectType", "Fin Aid" );
                  RESULT = SetCursorNextEntityByString( mProspct, "StudentContactActivity", "FunctionalArea", "FinancialAid", "" );
               } 

               //: END
            } 

            //: END
         } 

         //: END
         //: OrderEntityForView( mPerson, "ContactActivity", "CompletedDateTime D" )
         OrderEntityForView( mPerson, "ContactActivity", "CompletedDateTime D" );
         //: FOR EACH mPerson2.ContactActivity
         RESULT = SetCursorFirstEntity( mPerson2, "ContactActivity", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //: // If the entry is Private, only include for the proper area.
            //: IF mPerson2.ContactActivity.PrivateToArea = "Y"
            if ( CompareAttributeToString( mPerson2, "ContactActivity", "PrivateToArea", "Y" ) == 0 )
            { 
               //: // The only current use of Private is Faculty.
               //: GET VIEW mFaculty NAMED "mFaculty"
               RESULT = GetViewByName( mFaculty, "mFaculty", mPerson, zLEVEL_TASK );
               //: IF RESULT >= 0
               if ( RESULT >= 0 )
               { 
                  //: IF mFaculty.Person EXISTS    // If the Faculty entry is being created, there may be no Person yet.
                  lTempInteger_9 = CheckExistenceOfEntity( mFaculty, "Person" );
                  if ( lTempInteger_9 == 0 )
                  { 
                     //: IF mPerson2.Person.ID = mFaculty.Person.ID
                     if ( CompareAttributeToAttribute( mPerson2, "Person", "ID", mFaculty, "Person", "ID" ) == 0 )
                     { 
                        //: INCLUDE mPerson.ListedContactActivity FROM mPerson2.ContactActivity 
                        RESULT = IncludeSubobjectFromSubobject( mPerson, "ListedContactActivity", mPerson2, "ContactActivity", zPOS_AFTER );
                        //: mPerson.ListedContactActivity.wObjectType = "Person"
                        SetAttributeFromString( mPerson, "ListedContactActivity", "wObjectType", "Person" );
                     } 

                     //: END
                  } 

                  //: END
               } 

               //: END
               //:ELSE
            } 
            else
            { 
               //: INCLUDE mPerson.ListedContactActivity FROM mPerson2.ContactActivity 
               RESULT = IncludeSubobjectFromSubobject( mPerson, "ListedContactActivity", mPerson2, "ContactActivity", zPOS_AFTER );
               //: mPerson.ListedContactActivity.wObjectType = "Person"
               SetAttributeFromString( mPerson, "ListedContactActivity", "wObjectType", "Person" );
            } 

            RESULT = SetCursorNextEntity( mPerson2, "ContactActivity", "" );
            //: END
         } 

         //: END
         //: SET CURSOR FIRST mPerson.ListedContactActivity  
         RESULT = SetCursorFirstEntity( mPerson, "ListedContactActivity", "" );
         //: OrderEntityForView( mPerson, "ListedContactActivity", "CompletedDateTime D" )
         OrderEntityForView( mPerson, "ListedContactActivity", "CompletedDateTime D" );
         //: 
         //: OrderEntityForView( mPerson, "VehicleRegistration", "VehicleRegistrationCollegeYear.Year D" )
         OrderEntityForView( mPerson, "VehicleRegistration", "VehicleRegistrationCollegeYear.Year D" );
         //: FOR EACH mPerson.VehicleRegistration
         RESULT = SetCursorFirstEntity( mPerson, "VehicleRegistration", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //: INCLUDE mPerson.DisplayVehicleRegistration FROM mPerson.VehicleRegistration 
            RESULT = IncludeSubobjectFromSubobject( mPerson, "DisplayVehicleRegistration", mPerson, "VehicleRegistration", zPOS_AFTER );
            RESULT = SetCursorNextEntity( mPerson, "VehicleRegistration", "" );
         } 

         //: END
         //: SET CURSOR FIRST mPerson.VehicleRegistration
         RESULT = SetCursorFirstEntity( mPerson, "VehicleRegistration", "" );
         //: 
         //: DropView( mPerson2 )
         DropView( mPerson2 );
         //: 
         //: // Set up original SSN for later check on commit.
         //: mPerson.Person.wOriginalSSN = mPerson.Person.SSN 
         SetAttributeFromAttribute( mPerson, "Person", "wOriginalSSN", mPerson, "Person", "SSN" );
         break ;
      //:    
      //:    // Create a copy of the original Person to be used during save at commit time.
      //:    /*ActivateOI_FromOI( mPerson2, mPerson, zSINGLE )
      //:    CREATE ENTITY mPerson.CurrentTransaction
      //:    SetBlobFromOI( mPerson, "CurrentTransaction", "OriginalOI", mPerson2, zCOMPRESSED )
      //:    DropObjectInstance( mPerson2 )*/
      //:    
      //:    /* end zOCE_ACTIVATE */
      //: OF   zOCE_ACTIVATE_EMPTY:
      case zOCE_ACTIVATE_EMPTY :
         break ;

      //:  /* end zOCE_ACTIVATE_EMPTY */
      //: OF   zOCE_COMMIT:
      case zOCE_COMMIT :

         //:IF mPerson.Person EXISTS
         lTempInteger_10 = CheckExistenceOfEntity( mPerson, "Person" );
         if ( lTempInteger_10 == 0 )
         { 
            //:SetAttributeFromCurrentDateTime( mPerson, "Person", "ModifiedDateTime" )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mPerson );
             m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mPerson, "Person", "ModifiedDateTime" );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
         } 

         //:END

         //:// If Address has changed, set Modified flag.
         //:CreateViewFromView( mPerson2, mPerson)
         CreateViewFromView( mPerson2, mPerson );
         //:FOR EACH mPerson2.Address  
         RESULT = SetCursorFirstEntity( mPerson2, "Address", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:nRC = GetIncrementalUpdateFlags( mPerson2, "Address", zSET_INCR_UPDATED )
            nRC = GetIncrementalUpdateFlags( mPerson2, "Address", zSET_INCR_UPDATED );
            //:IF nRC = 1
            if ( nRC == 1 )
            { 
               //:SetAttributeFromCurrentDateTime( mPerson2, "Address", "ModifiedDateTime" )
               {
                ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mPerson2 );
                m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mPerson2, "Address", "ModifiedDateTime" );
                // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
               }
            } 

            RESULT = SetCursorNextEntity( mPerson2, "Address", "" );
            //:END
         } 

         //:END

         //:// Make sure Longitude and Latitude are correctly set.
         //:FOR EACH mPerson2.Address WHERE mPerson2.Address.PostalCode != ""
         RESULT = SetCursorFirstEntity( mPerson2, "Address", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( mPerson2, "Address", "PostalCode", "" ) != 0 )
            { 
               //:SetAddressLongitudeLatitude( mPerson2, "Address", mPerson2.Address.PostalCode )
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, mPerson2, "Address", "PostalCode" );
               szTempString_0 = sb_szTempString_0.toString( );}
               {
                ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mPerson2 );
                m_ZGLOBAL2_Operation.SetAddressLongitudeLatitude( mPerson2, "Address", szTempString_0 );
                // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
               }
            } 

            RESULT = SetCursorNextEntity( mPerson2, "Address", "" );
         } 

         //:END
         //:DropView( mPerson2 )
         DropView( mPerson2 );
         //: 
         //:// Create the Change Log entry.
         //:// For Prospects, create a blob of the before and after images of the object.
         //:// Drop any current Transaction entries (because we don't want past OI blobs taking up space in this OI blob)
         //:// and store a copy (blob) of the current OI in the new transaction entry.
         //:// Also create a copy of the original OI.
         //:IF mPerson.Person EXISTS
         lTempInteger_11 = CheckExistenceOfEntity( mPerson, "Person" );
         if ( lTempInteger_11 == 0 )
         { 

            //:// Always generate log entry.
            //:GenerateTransactionEntry( mPerson )
            {
             ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mPerson );
             m_ZGLOBAL2_Operation.GenerateTransactionEntry( mPerson );
             // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
            }

            //:// If SSN has been changed, create a modification entry.
            //:IF mPerson.Person.wOriginalSSN != ""
            if ( CompareAttributeToString( mPerson, "Person", "wOriginalSSN", "" ) != 0 )
            { 
               //:IF mPerson.Person.wOriginalSSN != mPerson.Person.SSN
               if ( CompareAttributeToAttribute( mPerson, "Person", "wOriginalSSN", mPerson, "Person", "SSN" ) != 0 )
               { 
                  //:CREATE ENTITY mPerson.PersonalDataModification 
                  RESULT = CreateEntity( mPerson, "PersonalDataModification", zPOS_AFTER );
                  //:mPerson.PersonalDataModification.OriginalSSN = mPerson.Person.wOriginalSSN
                  SetAttributeFromAttribute( mPerson, "PersonalDataModification", "OriginalSSN", mPerson, "Person", "wOriginalSSN" );
                  //:mPerson.PersonalDataModification.NewSSN      = mPerson.Person.SSN
                  SetAttributeFromAttribute( mPerson, "PersonalDataModification", "NewSSN", mPerson, "Person", "SSN" );
                  //:SetAttributeFromCurrentDateTime( mPerson, "PersonalDataModification", "ModifiedDateTime" )
                  {
                   ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mPerson );
                   m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mPerson, "PersonalDataModification", "ModifiedDateTime" );
                   // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                  }
                  //:GetViewByName( mUser, "mUser", mPerson, zLEVEL_APPLICATION )
                  GetViewByName( mUser, "mUser", mPerson, zLEVEL_APPLICATION );
                  //:INCLUDE mPerson.PersonalDataModificationUser FROM mUser.User 
                  RESULT = IncludeSubobjectFromSubobject( mPerson, "PersonalDataModificationUser", mUser, "User", zPOS_AFTER );
                  //:// The following 3 statements were added by DonC on 11/19/10 to correct the bug where a Modification entry
                  //:// was not created when an SSN was changed to another value and then back to the same value without refreshing mPerson.
                  //:// It also makes sure the entries are in reverse order by date.
                  //:mPerson.Person.wOriginalSSN = mPerson.Person.SSN
                  SetAttributeFromAttribute( mPerson, "Person", "wOriginalSSN", mPerson, "Person", "SSN" );
                  //:OrderEntityForView( mPerson, "PersonalDataModification", "ModifiedDateTime D" )
                  OrderEntityForView( mPerson, "PersonalDataModification", "ModifiedDateTime D" );
                  //:SET CURSOR FIRST mPerson.PersonalDataModification
                  RESULT = SetCursorFirstEntity( mPerson, "PersonalDataModification", "" );
               } 

               //:END
            } 

            //:END

            //:// If this is a new Person, give the operator a warning if there is already a Person with the
            //:// same first and last names.
            //:IF mPerson.Person.ID = ""
            if ( CompareAttributeToString( mPerson, "Person", "ID", "" ) == 0 )
            { 
               //:ACTIVATE mDupPerson RootOnlyMultiple WHERE mDupPerson.Person.LastName  = mPerson.Person.LastName 
               //:                                       AND mDupPerson.Person.FirstName = mPerson.Person.FirstName 
               {StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetStringFromAttribute( sb_szTempString_1, mPerson, "Person", "LastName" );
               szTempString_1 = sb_szTempString_1.toString( );}
               {StringBuilder sb_szTempString_2;
               if ( szTempString_2 == null )
                  sb_szTempString_2 = new StringBuilder( 32 );
               else
                  sb_szTempString_2 = new StringBuilder( szTempString_2 );
                               GetStringFromAttribute( sb_szTempString_2, mPerson, "Person", "FirstName" );
               szTempString_2 = sb_szTempString_2.toString( );}
               omPerson_fnLocalBuildQual_4( mPerson, vTempViewVar_0, szTempString_1, szTempString_2 );
               RESULT = ActivateObjectInstance( mDupPerson, "mPerson", mPerson, vTempViewVar_0, zACTIVATE_ROOTONLY_MULTIPLE );
               DropView( vTempViewVar_0 );
               //:NAME VIEW mDupPerson "mDupPerson"
               SetNameForView( mDupPerson, "mDupPerson", null, zLEVEL_TASK );
               //:DupCount = 0
               DupCount = 0;
               //:FOR EACH mDupPerson.Person 
               RESULT = SetCursorFirstEntity( mDupPerson, "Person", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:DupCount = DupCount + 1
                  DupCount = DupCount + 1;
                  RESULT = SetCursorNextEntity( mDupPerson, "Person", "" );
               } 

               //:END
               //:IF DupCount > 0
               if ( DupCount > 0 )
               { 
                  //:szDupCount = DupCount
                   {StringBuilder sb_szDupCount;
                  if ( szDupCount == null )
                     sb_szDupCount = new StringBuilder( 32 );
                  else
                     sb_szDupCount = new StringBuilder( szDupCount );
                                    ZeidonStringConvertFromNumber( sb_szDupCount, 1, 0, 5, DupCount, (double) 0.0, "I" );
                  szDupCount = sb_szDupCount.toString( );}
                  //:szMsg = "Caution: There are already " + szDupCount + " person entries in the database with the name, " +
                  //:        mPerson.Person.FirstName + " " + mPerson.Person.LastName + "."
                   {StringBuilder sb_szMsg;
                  if ( szMsg == null )
                     sb_szMsg = new StringBuilder( 32 );
                  else
                     sb_szMsg = new StringBuilder( szMsg );
                                    ZeidonStringCopy( sb_szMsg, 1, 0, "Caution: There are already ", 1, 0, 201 );
                  szMsg = sb_szMsg.toString( );}
                   {StringBuilder sb_szMsg;
                  if ( szMsg == null )
                     sb_szMsg = new StringBuilder( 32 );
                  else
                     sb_szMsg = new StringBuilder( szMsg );
                                    ZeidonStringConcat( sb_szMsg, 1, 0, szDupCount, 1, 0, 201 );
                  szMsg = sb_szMsg.toString( );}
                   {StringBuilder sb_szMsg;
                  if ( szMsg == null )
                     sb_szMsg = new StringBuilder( 32 );
                  else
                     sb_szMsg = new StringBuilder( szMsg );
                                    ZeidonStringConcat( sb_szMsg, 1, 0, " person entries in the database with the name, ", 1, 0, 201 );
                  szMsg = sb_szMsg.toString( );}
                  {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
                  StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                     GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_12, 'S', 51, mPerson, "Person", "FirstName", "", 0 );
                  lTempInteger_12 = mi_lTempInteger_12.intValue( );
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
                                    ZeidonStringConcat( sb_szMsg, 1, 0, " ", 1, 0, 201 );
                  szMsg = sb_szMsg.toString( );}
                  {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
                  StringBuilder sb_szTempString_4;
                  if ( szTempString_4 == null )
                     sb_szTempString_4 = new StringBuilder( 32 );
                  else
                     sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                     GetVariableFromAttribute( sb_szTempString_4, mi_lTempInteger_13, 'S', 51, mPerson, "Person", "LastName", "", 0 );
                  lTempInteger_13 = mi_lTempInteger_13.intValue( );
                  szTempString_4 = sb_szTempString_4.toString( );}
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
                                    ZeidonStringConcat( sb_szMsg, 1, 0, ".", 1, 0, 201 );
                  szMsg = sb_szMsg.toString( );}
                  //:MessageSend( mPerson, "", "Save Person Information", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
                  MessageSend( mPerson, "", "Save Person Information", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
               } 

               //:END
               //:DropObjectInstance( mDupPerson )
               DropObjectInstance( mDupPerson );

               //:// Check for null name or null CampusID, since we are having problems withn name and CampusID being null.
               //:IF mPerson.Person.LastName = ""
               if ( CompareAttributeToString( mPerson, "Person", "LastName", "" ) == 0 )
               { 
                  //:szMsg = "Error: The Person last name is null, which should never happen. Please report this error immediately to Systems Support."
                   {StringBuilder sb_szMsg;
                  if ( szMsg == null )
                     sb_szMsg = new StringBuilder( 32 );
                  else
                     sb_szMsg = new StringBuilder( szMsg );
                                    ZeidonStringCopy( sb_szMsg, 1, 0, "Error: The Person last name is null, which should never happen. Please report this error immediately to Systems Support.", 1, 0, 201 );
                  szMsg = sb_szMsg.toString( );}
                  //:MessageSend( mPerson, "", "Save Person Information", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
                  MessageSend( mPerson, "", "Save Person Information", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
               } 

               //:END
            } 

            //:   /*IF mPerson.Person.CampusID = ""
            //:      szMsg = "Error: The Campus ID for person, " + mPerson.Person.dFullNameFML + 
            //:              ", is null, which should never happen. Please report this error immediately to Systems Support."
            //:      MessageSend( mPerson, "", "Save Person Information", szMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
            //:   END*/
            //:END

            //:// If this Person is a Prospect, see if we need to update Territory information.
            //:IF mPerson.Prospect EXISTS
            lTempInteger_14 = CheckExistenceOfEntity( mPerson, "Prospect" );
            if ( lTempInteger_14 == 0 )
            { 

               //:// If the Administrative Division requests that Counselor and Territory be automatically
               //:// determined, include the proper values here.
               //:GET VIEW mProspct NAMED "mProspct"
               RESULT = GetViewByName( mProspct, "mProspct", mPerson, zLEVEL_TASK );
               //:IF RESULT >= 0 
               if ( RESULT >= 0 )
               { 
                  //:GET VIEW mAdmDiv NAMED "mAdmDiv"
                  RESULT = GetViewByName( mAdmDiv, "mAdmDiv", mPerson, zLEVEL_TASK );
                  //:IF RESULT >= 0
                  if ( RESULT >= 0 )
                  { 
                     //:IF mAdmDiv.AdministrativeDivision.AutoAssignCounselorFlag = "Y"
                     if ( CompareAttributeToString( mAdmDiv, "AdministrativeDivision", "AutoAssignCounselorFlag", "Y" ) == 0 )
                     { 
                        //:AssignTerritoryValues( mProspct, mAdmDiv )
                        {
                        // mProspct_Object m_mProspct_Object = new mProspct_Object( mProspct );
                        // m_mProspct_Object.omProspct_AssignTerritoryValues( mProspct, mAdmDiv );
                         // m_mProspct_Object = null;  // permit gc  (unnecessary)
                        }
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            //:   
            //:   // Generate before and after Person blobs for Prospects.
            //:   /*IF mPerson.CurrentTransaction EXISTS 
            //:      SetOI_FromBlob( mPerson3, "mPerson", mPerson, 
            //:                      mPerson, "CurrentTransaction", "OriginalOI", zSINGLE )   // Original OI
            //:      FOR EACH mPerson3.Transaction 
            //:         DropEntity( mPerson3, "Transaction", zREPOS_NONE ) 
            //:      END
            //:      FOR EACH mPerson3.CurrentTransaction 
            //:         DropEntity( mPerson3, "CurrentTransaction", zREPOS_NONE ) 
            //:      END
            //:      FOR EACH mPerson3.ListedContactActivity 
            //:         DropEntity( mPerson3, "ListedContactActivity", zREPOS_NONE )
            //:      END
            //:      FOR EACH mPerson3.ListedCategory 
            //:         DropEntity( mPerson3, "ListedCategory", zREPOS_NONE )
            //:      END
            //:      FOR EACH mPerson3.FamilyRole 
            //:         DropEntity( mPerson3, "FamilyRole", zREPOS_NONE )
            //:      END
            //:      SetBlobFromOI( mPerson, "Transaction", "OriginalOI", mPerson3, zCOMPRESSED )
            //:      DropObjectInstance( mPerson3 )
            //:   END
            //:   ActivateOI_FromOI( mPerson2, mPerson, zSINGLE )                         // New OI
            //:   FOR EACH mPerson2.Transaction 
            //:      DropEntity( mPerson2, "Transaction", zREPOS_NONE ) 
            //:   END
            //:   FOR EACH mPerson2.CurrentTransaction 
            //:      DropEntity( mPerson2, "CurrentTransaction", zREPOS_NONE ) 
            //:   END
            //:   FOR EACH mPerson2.ListedContactActivity 
            //:      DropEntity( mPerson2, "ListedContactActivity", zREPOS_NONE )
            //:   END
            //:   FOR EACH mPerson2.ListedCategory 
            //:      DropEntity( mPerson2, "ListedCategory", zREPOS_NONE )
            //:   END
            //:   FOR EACH mPerson2.FamilyRole 
            //:      DropEntity( mPerson2, "FamilyRole", zREPOS_NONE )
            //:   END
            //:   SetBlobFromOI( mPerson, "Transaction", "StoredOI", mPerson2, zCOMPRESSED )
            //:   DropObjectInstance( mPerson2 )*/
            //:END
         } 

         //:END
         break ;
      //:   
      //: /* end zOCE_COMMIT */
      //: OF   zOCE_DROPOI:
      case zOCE_DROPOI :
         break ;
   } 


   //:    /* end zOCE_DROPOI */
   //:END  /* case */
   return( 0 );
// END
} 


//:ENTITY CONSTRAINT OPERATION
public int 
omPerson_PersonEntityConstraint( View     mPerson,
                                 String   NameOfEntity,
                                 Integer   Event,
                                 Integer   State )
{
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;

   //:PersonEntityConstraint( VIEW mPerson BASED ON LOD mPerson,
   //:                     STRING ( 32 ) NameOfEntity,
   //:                     SHORT Event,
   //:                     SHORT State )

   //:CASE Event
   switch( Event )
   { 
      //:OF   zECE_ACCEPT:
      case zECE_ACCEPT :

         //:// Validate that Person name is entered.
         //:IF mPerson.Person.LastName = ""
         if ( CompareAttributeToString( mPerson, "Person", "LastName", "" ) == 0 )
         { 
            //:MessageSend( mPerson, "", "Save Person Information",
            //:       "A Person Last Name must be entered.",
            //:       zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mPerson, "", "Save Person Information", "A Person Last Name must be entered.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END

         //:// Validate that a Primary Address has been selected if at least one Address exits.
         //:IF mPerson.PrimaryAddress DOES NOT EXIST AND
         lTempInteger_0 = CheckExistenceOfEntity( mPerson, "PrimaryAddress" );
         //:mPerson.Address EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mPerson, "Address" );
         if ( lTempInteger_0 != 0 && lTempInteger_1 == 0 )
         { 

            //:MessageSend( mPerson, "", "Save Person Information",
            //:       "If an Address exists, a Primary Address must be selected.",
            //:       zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mPerson, "", "Save Person Information", "If an Address exists, a Primary Address must be selected.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END

         //:// If a DeceasedDate is specified, make sure that the Deceased flag is also specified.
         //:IF mPerson.Person.DeceasedDate != "" AND mPerson.Person.Deceased != "Y"
         if ( CompareAttributeToString( mPerson, "Person", "DeceasedDate", "" ) != 0 && CompareAttributeToString( mPerson, "Person", "Deceased", "Y" ) != 0 )
         { 
            //:mPerson.Person.Deceased = "Y"
            SetAttributeFromString( mPerson, "Person", "Deceased", "Y" );
         } 

         //:END

         //:// Delete any PersonalDataModification entries that don't have a User. (This handles the error situation 
         //:// where a User entity was deleted.)
         //:FOR EACH mPerson.PersonalDataModification 
         RESULT = SetCursorFirstEntity( mPerson, "PersonalDataModification", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mPerson.PersonalDataModificationUser DOES NOT EXIST
            lTempInteger_2 = CheckExistenceOfEntity( mPerson, "PersonalDataModificationUser" );
            if ( lTempInteger_2 != 0 )
            { 
               //:DELETE ENTITY mPerson.PersonalDataModification NONE 
               RESULT = DeleteEntity( mPerson, "PersonalDataModification", zREPOS_NONE );
            } 

            RESULT = SetCursorNextEntity( mPerson, "PersonalDataModification", "" );
            //:END
         } 

         //:END
         break ;

      //:  /* end zECE_ACCEPT */
      //:OF   zECE_CANCEL:
      case zECE_CANCEL :
         break ;

      //:  /* end zECE_CANCEL */
      //:OF   zECE_CREATE:
      case zECE_CREATE :
         break ;

      //:  /* end zECE_CREATE */
      //:OF   zECE_DELETE:
      case zECE_DELETE :
         break ;

      //:  /* end zECE_DELETE */
      //:OF   zECE_EXCLUDE:
      case zECE_EXCLUDE :
         break ;

      //:  /* end zECE_EXCLUDE */
      //:OF   zECE_INCLUDE:
      case zECE_INCLUDE :
         break ;
   } 


   //:     /* end zECE_INCLUDE */
   //:END  /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:RemoveFamilyRole( VIEW mPerson BASED ON LOD mPerson )

//:   VIEW mPerson2 BASED ON LOD mPerson
public int 
omPerson_RemoveFamilyRole( View     mPerson )
{
   zVIEW    mPerson2 = new zVIEW( );
   int      RESULT = 0;


   //:// Because of an error in the Terminal Services environment at ENC, we must remove a derived subobject
   //:// before commiting it.
   //:CreateViewFromView( mPerson2, mPerson)
   CreateViewFromView( mPerson2, mPerson );
   //:FOR EACH mPerson2.FamilyRole 
   RESULT = SetCursorFirstEntity( mPerson2, "FamilyRole", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mPerson2.FamilyRole NONE 
      RESULT = DeleteEntity( mPerson2, "FamilyRole", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mPerson2, "FamilyRole", "" );
   } 

   //:END
   //:DropView( mPerson2 )
   DropView( mPerson2 );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
public int 
omPerson_dPersonFullNameFML( View     mPerson,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{

   //:dPersonFullNameFML( VIEW mPerson BASED ON LOD mPerson,
   //:                 STRING ( 32 ) InternalEntityStructure,
   //:                 STRING ( 32 ) InternalAttribStructure,
   //:                 SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_FirstMiddleLast( mPerson, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mPerson );
          m_ZGLOBAL1_Operation.PersonName_FirstMiddleLast( mPerson, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         break ;

      //:/* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:   /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
public int 
omPerson_dPersonFullNameLFM( View     mPerson,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{

   //:dPersonFullNameLFM( VIEW mPerson BASED ON LOD mPerson,
   //:                 STRING ( 32 ) InternalEntityStructure,
   //:                 STRING ( 32 ) InternalAttribStructure,
   //:                 SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_LastFirstMiddle( mPerson, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mPerson );
          m_ZGLOBAL1_Operation.PersonName_LastFirstMiddle( mPerson, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         break ;

      //:/* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:   /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dAddressLabelFull( VIEW mPerson BASED ON LOD mPerson,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )
//:   SHORT nRC
public int 
omPerson_dAddressLabelFull( View     mPerson,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   int      nRC = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:nRC = dAdressLabelFull( mPerson /* vAnyObject */,
         //:   InternalEntityStructure /* szInternalEntityStructure */,
         //:   InternalAttribStructure /* szInternalAttribStructure */,
         //:   GetOrSetFlag /* nGetOrSetFlag */ )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mPerson );
          nRC = m_ZGLOBAL1_Operation.dAdressLabelFull( mPerson, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:RETURN nRC
         if(8==8)return( nRC );
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
   //:ValidateDeletePerson( VIEW mPerson BASED ON LOD mPerson )

   //:STRING ( 1 ) szConstraintError
public int 
omPerson_ValidateDeletePerson( View     mPerson )
{
   String   szConstraintError = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;


   //:// Validate that important information tied to the person does not exist.
   //:     
   //:// Don't allow delete if the Person is still a Prospect, Student, Donor or Alumni.
   //:szConstraintError = ""
    {StringBuilder sb_szConstraintError;
   if ( szConstraintError == null )
      sb_szConstraintError = new StringBuilder( 32 );
   else
      sb_szConstraintError = new StringBuilder( szConstraintError );
      ZeidonStringCopy( sb_szConstraintError, 1, 0, "", 1, 0, 2 );
   szConstraintError = sb_szConstraintError.toString( );}
   //:IF mPerson.Faculty EXISTS 
   lTempInteger_0 = CheckExistenceOfEntity( mPerson, "Faculty" );
   if ( lTempInteger_0 == 0 )
   { 
      //:szConstraintError = "Y" 
       {StringBuilder sb_szConstraintError;
      if ( szConstraintError == null )
         sb_szConstraintError = new StringBuilder( 32 );
      else
         sb_szConstraintError = new StringBuilder( szConstraintError );
            ZeidonStringCopy( sb_szConstraintError, 1, 0, "Y", 1, 0, 2 );
      szConstraintError = sb_szConstraintError.toString( );}
   } 

   //:END
   //:IF mPerson.Prospect EXISTS 
   lTempInteger_1 = CheckExistenceOfEntity( mPerson, "Prospect" );
   if ( lTempInteger_1 == 0 )
   { 
      //:szConstraintError = "Y" 
       {StringBuilder sb_szConstraintError;
      if ( szConstraintError == null )
         sb_szConstraintError = new StringBuilder( 32 );
      else
         sb_szConstraintError = new StringBuilder( szConstraintError );
            ZeidonStringCopy( sb_szConstraintError, 1, 0, "Y", 1, 0, 2 );
      szConstraintError = sb_szConstraintError.toString( );}
   } 

   //:END
   //:IF mPerson.Staff EXISTS 
   lTempInteger_2 = CheckExistenceOfEntity( mPerson, "Staff" );
   if ( lTempInteger_2 == 0 )
   { 
      //:szConstraintError = "Y" 
       {StringBuilder sb_szConstraintError;
      if ( szConstraintError == null )
         sb_szConstraintError = new StringBuilder( 32 );
      else
         sb_szConstraintError = new StringBuilder( szConstraintError );
            ZeidonStringCopy( sb_szConstraintError, 1, 0, "Y", 1, 0, 2 );
      szConstraintError = sb_szConstraintError.toString( );}
   } 

   //:END
   //:IF mPerson.Student EXISTS 
   lTempInteger_3 = CheckExistenceOfEntity( mPerson, "Student" );
   if ( lTempInteger_3 == 0 )
   { 
      //:szConstraintError = "Y" 
       {StringBuilder sb_szConstraintError;
      if ( szConstraintError == null )
         sb_szConstraintError = new StringBuilder( 32 );
      else
         sb_szConstraintError = new StringBuilder( szConstraintError );
            ZeidonStringCopy( sb_szConstraintError, 1, 0, "Y", 1, 0, 2 );
      szConstraintError = sb_szConstraintError.toString( );}
   } 

   //:END
   //:IF mPerson.Donor EXISTS 
   lTempInteger_4 = CheckExistenceOfEntity( mPerson, "Donor" );
   if ( lTempInteger_4 == 0 )
   { 
      //:szConstraintError = "Y" 
       {StringBuilder sb_szConstraintError;
      if ( szConstraintError == null )
         sb_szConstraintError = new StringBuilder( 32 );
      else
         sb_szConstraintError = new StringBuilder( szConstraintError );
            ZeidonStringCopy( sb_szConstraintError, 1, 0, "Y", 1, 0, 2 );
      szConstraintError = sb_szConstraintError.toString( );}
   } 

   //:END
   //:IF mPerson.Alumni EXISTS 
   lTempInteger_5 = CheckExistenceOfEntity( mPerson, "Alumni" );
   if ( lTempInteger_5 == 0 )
   { 
      //:szConstraintError = "Y" 
       {StringBuilder sb_szConstraintError;
      if ( szConstraintError == null )
         sb_szConstraintError = new StringBuilder( 32 );
      else
         sb_szConstraintError = new StringBuilder( szConstraintError );
            ZeidonStringCopy( sb_szConstraintError, 1, 0, "Y", 1, 0, 2 );
      szConstraintError = sb_szConstraintError.toString( );}
   } 

   //:END
   //:IF mPerson.Vendor EXISTS 
   lTempInteger_6 = CheckExistenceOfEntity( mPerson, "Vendor" );
   if ( lTempInteger_6 == 0 )
   { 
      //:szConstraintError = "Y" 
       {StringBuilder sb_szConstraintError;
      if ( szConstraintError == null )
         sb_szConstraintError = new StringBuilder( 32 );
      else
         sb_szConstraintError = new StringBuilder( szConstraintError );
            ZeidonStringCopy( sb_szConstraintError, 1, 0, "Y", 1, 0, 2 );
      szConstraintError = sb_szConstraintError.toString( );}
   } 

   //:END
   //:IF mPerson.Trustee EXISTS 
   lTempInteger_7 = CheckExistenceOfEntity( mPerson, "Trustee" );
   if ( lTempInteger_7 == 0 )
   { 
      //:szConstraintError = "Y" 
       {StringBuilder sb_szConstraintError;
      if ( szConstraintError == null )
         sb_szConstraintError = new StringBuilder( 32 );
      else
         sb_szConstraintError = new StringBuilder( szConstraintError );
            ZeidonStringCopy( sb_szConstraintError, 1, 0, "Y", 1, 0, 2 );
      szConstraintError = sb_szConstraintError.toString( );}
   } 

   //:END
   //:IF szConstraintError = "Y"
   if ( ZeidonStringCompare( szConstraintError, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:MessageSend( mPerson, "", "Delete Person",
      //:             "The person being deleted has active roles (eg., Prospect, Student, Alumni) and cannot be deleted.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mPerson, "", "Delete Person", "The person being deleted has active roles (eg., Prospect, Student, Alumni) and cannot be deleted.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// A Person with Categories cannot be deleted.
   //:IF mPerson.Category EXISTS
   lTempInteger_8 = CheckExistenceOfEntity( mPerson, "Category" );
   if ( lTempInteger_8 == 0 )
   { 
      //:MessageSend( mPerson, "", "Delete Person",
      //:             "The person cannot be deleted because he/she has Categories assigned.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mPerson, "", "Delete Person", "The person cannot be deleted because he/she has Categories assigned.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dCityStateZipCountry( VIEW mPerson BASED ON LOD mPerson,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )

//:   STRING ( 60 ) szCityStateZipCountry
public int 
omPerson_dCityStateZipCountry( View     mPerson,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   String   szCityStateZipCountry = null;
   //:STRING ( 20 ) szCountry
   String   szCountry = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_3 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_4 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:szCountry = mPerson.PrimaryAddress.Country
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szCountry;
         if ( szCountry == null )
            sb_szCountry = new StringBuilder( 32 );
         else
            sb_szCountry = new StringBuilder( szCountry );
                   GetVariableFromAttribute( sb_szCountry, mi_lTempInteger_0, 'S', 21, mPerson, "PrimaryAddress", "Country", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szCountry = sb_szCountry.toString( );}
         //:IF szCountry = "" OR szCountry = "US"
         if ( ZeidonStringCompare( szCountry, 1, 0, "", 1, 0, 21 ) == 0 || ZeidonStringCompare( szCountry, 1, 0, "US", 1, 0, 21 ) == 0 )
         { 
            //:szCityStateZipCountry = mPerson.PrimaryAddress.City + ", " +
            //:                     mPerson.PrimaryAddress.StateProvince + " " +
            //:                     mPerson.PrimaryAddress.PostalCode
            {StringBuilder sb_szCityStateZipCountry;
            if ( szCityStateZipCountry == null )
               sb_szCityStateZipCountry = new StringBuilder( 32 );
            else
               sb_szCityStateZipCountry = new StringBuilder( szCityStateZipCountry );
                         GetStringFromAttribute( sb_szCityStateZipCountry, mPerson, "PrimaryAddress", "City" );
            szCityStateZipCountry = sb_szCityStateZipCountry.toString( );}
             {StringBuilder sb_szCityStateZipCountry;
            if ( szCityStateZipCountry == null )
               sb_szCityStateZipCountry = new StringBuilder( 32 );
            else
               sb_szCityStateZipCountry = new StringBuilder( szCityStateZipCountry );
                        ZeidonStringConcat( sb_szCityStateZipCountry, 1, 0, ", ", 1, 0, 61 );
            szCityStateZipCountry = sb_szCityStateZipCountry.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 3, mPerson, "PrimaryAddress", "StateProvince", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szCityStateZipCountry;
            if ( szCityStateZipCountry == null )
               sb_szCityStateZipCountry = new StringBuilder( 32 );
            else
               sb_szCityStateZipCountry = new StringBuilder( szCityStateZipCountry );
                        ZeidonStringConcat( sb_szCityStateZipCountry, 1, 0, szTempString_0, 1, 0, 61 );
            szCityStateZipCountry = sb_szCityStateZipCountry.toString( );}
             {StringBuilder sb_szCityStateZipCountry;
            if ( szCityStateZipCountry == null )
               sb_szCityStateZipCountry = new StringBuilder( 32 );
            else
               sb_szCityStateZipCountry = new StringBuilder( szCityStateZipCountry );
                        ZeidonStringConcat( sb_szCityStateZipCountry, 1, 0, " ", 1, 0, 61 );
            szCityStateZipCountry = sb_szCityStateZipCountry.toString( );}
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 11, mPerson, "PrimaryAddress", "PostalCode", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szCityStateZipCountry;
            if ( szCityStateZipCountry == null )
               sb_szCityStateZipCountry = new StringBuilder( 32 );
            else
               sb_szCityStateZipCountry = new StringBuilder( szCityStateZipCountry );
                        ZeidonStringConcat( sb_szCityStateZipCountry, 1, 0, szTempString_1, 1, 0, 61 );
            szCityStateZipCountry = sb_szCityStateZipCountry.toString( );}
            //:                     
            //:ELSE
         } 
         else
         { 
            //:szCityStateZipCountry = mPerson.PrimaryAddress.City + ", " +
            //:                     mPerson.PrimaryAddress.StateProvince + " " +
            //:                     mPerson.PrimaryAddress.PostalCode + " " +
            //:                     szCountry
            {StringBuilder sb_szCityStateZipCountry;
            if ( szCityStateZipCountry == null )
               sb_szCityStateZipCountry = new StringBuilder( 32 );
            else
               sb_szCityStateZipCountry = new StringBuilder( szCityStateZipCountry );
                         GetStringFromAttribute( sb_szCityStateZipCountry, mPerson, "PrimaryAddress", "City" );
            szCityStateZipCountry = sb_szCityStateZipCountry.toString( );}
             {StringBuilder sb_szCityStateZipCountry;
            if ( szCityStateZipCountry == null )
               sb_szCityStateZipCountry = new StringBuilder( 32 );
            else
               sb_szCityStateZipCountry = new StringBuilder( szCityStateZipCountry );
                        ZeidonStringConcat( sb_szCityStateZipCountry, 1, 0, ", ", 1, 0, 61 );
            szCityStateZipCountry = sb_szCityStateZipCountry.toString( );}
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_3, 'S', 3, mPerson, "PrimaryAddress", "StateProvince", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szCityStateZipCountry;
            if ( szCityStateZipCountry == null )
               sb_szCityStateZipCountry = new StringBuilder( 32 );
            else
               sb_szCityStateZipCountry = new StringBuilder( szCityStateZipCountry );
                        ZeidonStringConcat( sb_szCityStateZipCountry, 1, 0, szTempString_2, 1, 0, 61 );
            szCityStateZipCountry = sb_szCityStateZipCountry.toString( );}
             {StringBuilder sb_szCityStateZipCountry;
            if ( szCityStateZipCountry == null )
               sb_szCityStateZipCountry = new StringBuilder( 32 );
            else
               sb_szCityStateZipCountry = new StringBuilder( szCityStateZipCountry );
                        ZeidonStringConcat( sb_szCityStateZipCountry, 1, 0, " ", 1, 0, 61 );
            szCityStateZipCountry = sb_szCityStateZipCountry.toString( );}
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                         GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_4, 'S', 11, mPerson, "PrimaryAddress", "PostalCode", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szTempString_3 = sb_szTempString_3.toString( );}
             {StringBuilder sb_szCityStateZipCountry;
            if ( szCityStateZipCountry == null )
               sb_szCityStateZipCountry = new StringBuilder( 32 );
            else
               sb_szCityStateZipCountry = new StringBuilder( szCityStateZipCountry );
                        ZeidonStringConcat( sb_szCityStateZipCountry, 1, 0, szTempString_3, 1, 0, 61 );
            szCityStateZipCountry = sb_szCityStateZipCountry.toString( );}
             {StringBuilder sb_szCityStateZipCountry;
            if ( szCityStateZipCountry == null )
               sb_szCityStateZipCountry = new StringBuilder( 32 );
            else
               sb_szCityStateZipCountry = new StringBuilder( szCityStateZipCountry );
                        ZeidonStringConcat( sb_szCityStateZipCountry, 1, 0, " ", 1, 0, 61 );
            szCityStateZipCountry = sb_szCityStateZipCountry.toString( );}
             {StringBuilder sb_szCityStateZipCountry;
            if ( szCityStateZipCountry == null )
               sb_szCityStateZipCountry = new StringBuilder( 32 );
            else
               sb_szCityStateZipCountry = new StringBuilder( szCityStateZipCountry );
                        ZeidonStringConcat( sb_szCityStateZipCountry, 1, 0, szCountry, 1, 0, 61 );
            szCityStateZipCountry = sb_szCityStateZipCountry.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mPerson,
         //:                   InternalEntityStructure, InternalAttribStructure, szCityStateZipCountry )
         StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, szCityStateZipCountry );
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
//:dTrusteeNextElectDate( VIEW mPerson BASED ON LOD mPerson,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   VIEW wXferO REGISTERED AS wXferO
public int 
omPerson_dTrusteeNextElectDate( View     mPerson,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 8 ) szNextElectionDate
   String   szNextElectionDate = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mPerson, zLEVEL_TASK );

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mPerson.Trustee.LastElectionDate = ""
         if ( CompareAttributeToString( mPerson, "Trustee", "LastElectionDate", "" ) == 0 )
         { 
            //:szNextElectionDate = ""
             {StringBuilder sb_szNextElectionDate;
            if ( szNextElectionDate == null )
               sb_szNextElectionDate = new StringBuilder( 32 );
            else
               sb_szNextElectionDate = new StringBuilder( szNextElectionDate );
                        ZeidonStringCopy( sb_szNextElectionDate, 1, 0, "", 1, 0, 9 );
            szNextElectionDate = sb_szNextElectionDate.toString( );}
            //:ELSE
         } 
         else
         { 
            //:wXferO.Root.WorkDate = mPerson.Trustee.LastElectionDate
            SetAttributeFromAttribute( wXferO, "Root", "WorkDate", mPerson, "Trustee", "LastElectionDate" );
            //:AddMonthsToDate( wXferO, "Root", "WorkDate", 36 )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
             m_ZGLOBAL1_Operation.AddMonthsToDate( wXferO, "Root", "WorkDate", 36 );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:szNextElectionDate = wXferO.Root.WorkDate 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szNextElectionDate;
            if ( szNextElectionDate == null )
               sb_szNextElectionDate = new StringBuilder( 32 );
            else
               sb_szNextElectionDate = new StringBuilder( szNextElectionDate );
                         GetVariableFromAttribute( sb_szNextElectionDate, mi_lTempInteger_0, 'S', 9, wXferO, "Root", "WorkDate", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szNextElectionDate = sb_szNextElectionDate.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mPerson,
         //:                   InternalEntityStructure, InternalAttribStructure, szNextElectionDate )
         StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, szNextElectionDate );
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
omPerson_IsPrimaryAddress( View     mPerson,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   int      lTempInteger_0 = 0;

   //:IsPrimaryAddress( VIEW mPerson BASED ON LOD mPerson,
   //:               STRING ( 32 ) InternalEntityStructure,
   //:               STRING ( 32 ) InternalAttribStructure,
   //:               SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:IF mPerson.PrimaryForPerson  EXISTS  
         lTempInteger_0 = CheckExistenceOfEntity( mPerson, "PrimaryForPerson" );
         if ( lTempInteger_0 == 0 )
         { 
            //:StoreStringInRecord ( mPerson,
            //:                      InternalEntityStructure, InternalAttribStructure, "Y" )
            StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, "Y" );
            //:ELSE 
         } 
         else
         { 
            //:StoreStringInRecord ( mPerson,
            //:                      InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, "" );
         } 

         //:END
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
public int 
omPerson_IsRemitAddress( View     mPerson,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   int      lTempInteger_0 = 0;

   //:IsRemitAddress( VIEW mPerson BASED ON LOD mPerson,
   //:               STRING ( 32 ) InternalEntityStructure,
   //:               STRING ( 32 ) InternalAttribStructure,
   //:               SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:IF mPerson.RemitToVendor EXISTS  
         lTempInteger_0 = CheckExistenceOfEntity( mPerson, "RemitToVendor" );
         if ( lTempInteger_0 == 0 )
         { 
            //:StoreStringInRecord ( mPerson,
            //:                      InternalEntityStructure, InternalAttribStructure, "Y" )
            StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, "Y" );
            //:ELSE 
         } 
         else
         { 
            //:StoreStringInRecord ( mPerson,
            //:                      InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, "" );
         } 

         //:END
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
//:dDocumentOrSubject( VIEW mPerson BASED ON LOD mPerson,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   STRING ( 32 )  szEntityName
public int 
omPerson_dDocumentOrSubject( View     mPerson,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szEntityName = null;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_3 = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mPerson );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "ContactActivity"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "ContactActivity", 1, 0, 33 ) == 0 )
         { 
            //:IF mPerson.ContactActivity.EmailSubjectLine != ""
            if ( CompareAttributeToString( mPerson, "ContactActivity", "EmailSubjectLine", "" ) != 0 )
            { 
               //:StoreStringInRecord ( mPerson,
               //:                   InternalEntityStructure, InternalAttribStructure, mPerson.ContactActivity.EmailSubjectLine )
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, mPerson, "ContactActivity", "EmailSubjectLine" );
               szTempString_0 = sb_szTempString_0.toString( );}
               StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, szTempString_0 );
               //:ELSE
            } 
            else
            { 
               //:IF mPerson.Document EXISTS
               lTempInteger_0 = CheckExistenceOfEntity( mPerson, "Document" );
               if ( lTempInteger_0 == 0 )
               { 
                  //:StoreStringInRecord ( mPerson,
                  //:                   InternalEntityStructure, InternalAttribStructure, mPerson.Document.Name )
                  {StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetStringFromAttribute( sb_szTempString_1, mPerson, "Document", "Name" );
                  szTempString_1 = sb_szTempString_1.toString( );}
                  StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, szTempString_1 );
                  //:ELSE
               } 
               else
               { 
                  //:StoreStringInRecord ( mPerson,
                  //:                   InternalEntityStructure, InternalAttribStructure, "" )
                  StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, "" );
               } 

               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF mPerson.ListedContactActivity.EmailSubjectLine != ""
            if ( CompareAttributeToString( mPerson, "ListedContactActivity", "EmailSubjectLine", "" ) != 0 )
            { 
               //:StoreStringInRecord ( mPerson,
               //:                   InternalEntityStructure, InternalAttribStructure, mPerson.ListedContactActivity.EmailSubjectLine )
               {StringBuilder sb_szTempString_2;
               if ( szTempString_2 == null )
                  sb_szTempString_2 = new StringBuilder( 32 );
               else
                  sb_szTempString_2 = new StringBuilder( szTempString_2 );
                               GetStringFromAttribute( sb_szTempString_2, mPerson, "ListedContactActivity", "EmailSubjectLine" );
               szTempString_2 = sb_szTempString_2.toString( );}
               StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, szTempString_2 );
               //:ELSE
            } 
            else
            { 
               //:IF mPerson.ListedContactActivityDocument EXISTS
               lTempInteger_1 = CheckExistenceOfEntity( mPerson, "ListedContactActivityDocument" );
               if ( lTempInteger_1 == 0 )
               { 
                  //:StoreStringInRecord ( mPerson,
                  //:                   InternalEntityStructure, InternalAttribStructure, mPerson.ListedContactActivityDocument.Name )
                  {StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                     GetStringFromAttribute( sb_szTempString_3, mPerson, "ListedContactActivityDocument", "Name" );
                  szTempString_3 = sb_szTempString_3.toString( );}
                  StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, szTempString_3 );
                  //:ELSE
               } 
               else
               { 
                  //:StoreStringInRecord ( mPerson,
                  //:                   InternalEntityStructure, InternalAttribStructure, "" )
                  StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, "" );
               } 

               //:END
            } 

            //:END
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
//:dEventDescOrRecipients( VIEW mPerson BASED ON LOD mPerson,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   STRING ( 32 )  szEntityName
public int 
omPerson_dEventDescOrRecipients( View     mPerson,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   String   szEntityName = null;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_3 = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mPerson );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "ContactActivity"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "ContactActivity", 1, 0, 33 ) == 0 )
         { 
            //:IF mPerson.ContactActivity.UserEmailRecipients != ""
            if ( CompareAttributeToString( mPerson, "ContactActivity", "UserEmailRecipients", "" ) != 0 )
            { 
               //:StoreStringInRecord ( mPerson,
               //:                   InternalEntityStructure, InternalAttribStructure, mPerson.ContactActivity.UserEmailRecipients )
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, mPerson, "ContactActivity", "UserEmailRecipients" );
               szTempString_0 = sb_szTempString_0.toString( );}
               StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, szTempString_0 );
               //:ELSE
            } 
            else
            { 
               //:IF mPerson.ContactActivityEvent EXISTS
               lTempInteger_0 = CheckExistenceOfEntity( mPerson, "ContactActivityEvent" );
               if ( lTempInteger_0 == 0 )
               { 
                  //:StoreStringInRecord ( mPerson,
                  //:                   InternalEntityStructure, InternalAttribStructure, mPerson.ContactActivityEvent.Description )
                  {StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetStringFromAttribute( sb_szTempString_1, mPerson, "ContactActivityEvent", "Description" );
                  szTempString_1 = sb_szTempString_1.toString( );}
                  StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, szTempString_1 );
                  //:ELSE
               } 
               else
               { 
                  //:StoreStringInRecord ( mPerson,
                  //:                   InternalEntityStructure, InternalAttribStructure, "" )
                  StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, "" );
               } 

               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF mPerson.ListedContactActivity.UserEmailRecipients != ""
            if ( CompareAttributeToString( mPerson, "ListedContactActivity", "UserEmailRecipients", "" ) != 0 )
            { 
               //:StoreStringInRecord ( mPerson,
               //:                   InternalEntityStructure, InternalAttribStructure, mPerson.ListedContactActivity.UserEmailRecipients )
               {StringBuilder sb_szTempString_2;
               if ( szTempString_2 == null )
                  sb_szTempString_2 = new StringBuilder( 32 );
               else
                  sb_szTempString_2 = new StringBuilder( szTempString_2 );
                               GetStringFromAttribute( sb_szTempString_2, mPerson, "ListedContactActivity", "UserEmailRecipients" );
               szTempString_2 = sb_szTempString_2.toString( );}
               StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, szTempString_2 );
               //:ELSE
            } 
            else
            { 
               //:IF mPerson.ListedContactActivityEvent EXISTS
               lTempInteger_1 = CheckExistenceOfEntity( mPerson, "ListedContactActivityEvent" );
               if ( lTempInteger_1 == 0 )
               { 
                  //:StoreStringInRecord ( mPerson,
                  //:                   InternalEntityStructure, InternalAttribStructure, mPerson.ListedContactActivityEvent.Description )
                  {StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                     GetStringFromAttribute( sb_szTempString_3, mPerson, "ListedContactActivityEvent", "Description" );
                  szTempString_3 = sb_szTempString_3.toString( );}
                  StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, szTempString_3 );
                  //:ELSE
               } 
               else
               { 
                  //:StoreStringInRecord ( mPerson,
                  //:                   InternalEntityStructure, InternalAttribStructure, "" )
                  StoreStringInRecord( mPerson, InternalEntityStructure, InternalAttribStructure, "" );
               } 

               //:END
            } 

            //:END
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



}
