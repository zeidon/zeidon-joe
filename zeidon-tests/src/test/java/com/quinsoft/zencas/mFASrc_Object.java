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

import com.quinsoft.zencas.ZGLOBAL1_Operation;

/**
   @author QuinSoft
**/

public class mFASrc_Object extends VmlObjectOperations
{
   public mFASrc_Object( View view )
   {
      super( view );
   }


//:TRANSFORMATION OPERATION
public int 
omFASrc_ActivateSourceList( zVIEW    mFASrc,
                            View     zAnyView,
                            String   szListType )
{
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   //:ActivateSourceList( VIEW mFASrc BASED ON LOD mFASrc,
   //:                 VIEW zAnyView,
   //:                 STRING ( 32 ) szListType )

   //:IF szListType = "" 
   if ( ZeidonStringCompare( szListType, 1, 0, "", 1, 0, 33 ) == 0 )
   { 
      //:ACTIVATE  mFASrc ROOTONLYMULTIPLE 
      RESULT = ActivateObjectInstance( mFASrc, "mFASrc", zAnyView, 0, zACTIVATE_ROOTONLY_MULTIPLE );
      //:ELSE 
   } 
   else
   { 
      //:ACTIVATE  mFASrc ROOTONLYMULTIPLE 
      //:   WHERE mFASrc.FinAidSource.SourceType = szListType
      omFASrc_fnLocalBuildQual_0( zAnyView, vTempViewVar_0, szListType );
      RESULT = ActivateObjectInstance( mFASrc, "mFASrc", zAnyView, vTempViewVar_0, zACTIVATE_ROOTONLY_MULTIPLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:RETURN 0 
   return( 0 );
// END
} 


private int 
omFASrc_fnLocalBuildQual_0( View     vSubtask,
                            zVIEW    vQualObject,
                            String   szListType )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidSource" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidSource" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "SourceType" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListType.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFASrc_fnLocalBuildQual_1( View     vSubtask,
                            zVIEW    vQualObject,
                            int      nID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidSource" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidSource" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", nID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidAwardAssigned" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidAwardAssigned" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "<" );
   return( 0 );
} 


private int 
omFASrc_fnLocalBuildQual_2( View     vSubtask,
                            zVIEW    vQualObject,
                            int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidRuleSet" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidRuleSet" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omFASrc_fnLocalBuildQual_3( View     vSubtask,
                            zVIEW    vQualObject,
                            int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FinAidRuleSet" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FinAidRuleSet" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DERIVED ATTRIBUTE OPERATION
//:dTotalNAcceptedByYear( VIEW mFASrc BASED ON LOD mFASrc,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   VIEW lFAAdmin REGISTERED AS lFAAdmin
public int 
omFASrc_dTotalNAcceptedByYear( View     mFASrc,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   zVIEW    lFAAdmin = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   //:DECIMAL dRejected 
   double  dRejected = 0.0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;

   RESULT = GetViewByName( lFAAdmin, "lFAAdmin", mFASrc, zLEVEL_TASK );

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:dAmount = 0
         dAmount = 0;
         //:dRejected = 0
         dRejected = 0;
         //:FOR EACH mFASrc.FinAidAwardAssigned 
         //:      WHERE mFASrc.AwardedCollegeYear.ID = lFAAdmin.CollegeYear.ID   
         RESULT = SetCursorFirstEntity( mFASrc, "FinAidAwardAssigned", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToAttribute( mFASrc, "AwardedCollegeYear", "ID", lFAAdmin, "CollegeYear", "ID" ) == 0 )
            { 
               //:IF mFASrc.FinAidAwardAssigned.Amount != "" OR mFASrc.FinAidAwardAssigned.AwardStatus = "N"
               if ( CompareAttributeToString( mFASrc, "FinAidAwardAssigned", "Amount", "" ) != 0 || CompareAttributeToString( mFASrc, "FinAidAwardAssigned", "AwardStatus", "N" ) == 0 )
               { 
                  //:dRejected = mFASrc.FinAidAwardAssigned.AmountOffered - mFASrc.FinAidAwardAssigned.Amount
                  {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                     GetDecimalFromAttribute( md_dTempDecimal_0, mFASrc, "FinAidAwardAssigned", "AmountOffered" );
                  dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                  {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                                     GetDecimalFromAttribute( md_dTempDecimal_1, mFASrc, "FinAidAwardAssigned", "Amount" );
                  dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
                  dRejected = dTempDecimal_0 - dTempDecimal_1;
               } 

               //:END
               //:dAmount = dAmount + dRejected 
               dAmount = dAmount + dRejected;
               //:dRejected = 0
               dRejected = 0;
            } 

            RESULT = SetCursorNextEntity( mFASrc, "FinAidAwardAssigned", "" );
         } 

         //:END
         //:StoreValueInRecord( mFASrc,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFASrc, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dBalanceToSwap( VIEW mFASrc BASED ON LOD mFASrc,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   DECIMAL dAmount 
public int 
omFASrc_dBalanceToSwap( View     mFASrc,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   double  dAmount = 0.0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:dAmount = mFASrc.FinAidSource.wAmountBudgeted - mFASrc.FinAidSource.wTotalAwarded 
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, mFASrc, "FinAidSource", "wAmountBudgeted" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                   GetDecimalFromAttribute( md_dTempDecimal_1, mFASrc, "FinAidSource", "wTotalAwarded" );
         dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
         dAmount = dTempDecimal_0 - dTempDecimal_1;
         //:StoreValueInRecord( mFASrc,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFASrc, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dTRemainAwardByYear( VIEW mFASrc BASED ON LOD mFASrc,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )
//:   VIEW lFAAdmin REGISTERED AS lFAAdmin
public int 
omFASrc_dTRemainAwardByYear( View     mFASrc,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   zVIEW    lFAAdmin = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;

  // RESULT = GetViewByName( lFAAdmin, "lFAAdmin", mFASrc, zLEVEL_TASK );

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:END

         //:StoreValueInRecord( mFASrc,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFASrc, InternalEntityStructure, InternalAttribStructure, 1000, 0 );
         break ;
      //:                       
      //:/* end zDERIVED_GET */
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
//:dTotalBudgetedByYear( VIEW mFASrc BASED ON LOD mFASrc,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )
//:   VIEW lFAAdmin REGISTERED AS lFAAdmin
public int 
omFASrc_dTotalBudgetedByYear( View     mFASrc,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   zVIEW    lFAAdmin = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL dAmount 
   double  dAmount = 0.0;

 
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:dAmount = 0
         dAmount = 0;
         
         //:END
         //:StoreValueInRecord( mFASrc,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFASrc, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dTotalAcceptedByYear( VIEW mFASrc BASED ON LOD mFASrc,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )
//:   VIEW lFAAdmin REGISTERED AS lFAAdmin
public int 
omFASrc_dTotalAcceptedByYear( View     mFASrc,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   zVIEW    lFAAdmin = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   double  dTempDecimal_0 = 0.0;

 
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:dAmount = 0
         dAmount = 0;

         //:END
         //:StoreValueInRecord( mFASrc,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFASrc, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dTotalAwardedByYear( VIEW mFASrc BASED ON LOD mFASrc,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )
//:   VIEW lFAAdmin REGISTERED AS lFAAdmin
public int 
omFASrc_dTotalAwardedByYear( View     mFASrc,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   zVIEW    lFAAdmin = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:dAmount = 0
         dAmount = 0;
         //:FOR EACH mFASrc.FinAidAwardAssigned 
         //:      WHERE mFASrc.AwardedCollegeYear.ID = lFAAdmin.CollegeYear.ID   

         //:END
         //:StoreValueInRecord( mFASrc,InternalEntityStructure,
         //:                    InternalAttribStructure, dAmount, 0 )
         StoreValueInRecord( mFASrc, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dTotalTransmittedByYear( VIEW mFASrc BASED ON LOD mFASrc,
//:                         STRING ( 32 ) InternalEntityStructure,
//:                         STRING ( 32 ) InternalAttribStructure,
//:                         SHORT GetOrSetFlag )
//:   VIEW lFAAdmin REGISTERED AS lFAAdmin
public int 
omFASrc_dTotalTransmittedByYear( View     mFASrc,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   zVIEW    lFAAdmin = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:dAmount = 0
         dAmount = 0;
         //:FOR EACH mFASrc.FinAidAwardAssigned 
         StoreValueInRecord( mFASrc, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:dTotalDisbByYear( VIEW mFASrc BASED ON LOD mFASrc,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )
//:   VIEW lFAAdmin REGISTERED AS lFAAdmin
public int 
omFASrc_dTotalDisbByYear( View     mFASrc,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   zVIEW    lFAAdmin = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL dAmount 
   double  dAmount = 0.0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:dAmount = 0
         dAmount = 0;
         StoreValueInRecord( mFASrc, InternalEntityStructure, InternalAttribStructure, dAmount, 0 );
         break ;
      //:/* end zDERIVED_GET */
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
//:ActivateSourceByID( VIEW mFASrc BASED ON LOD mFASrc,
//:                    VIEW zAnyView,
//:                    INTEGER nID )
//:   VIEW mFARuleS BASED ON LOD mFARuleS
public int 
omFASrc_ActivateSourceByID( zVIEW    mFASrc,
                            View     zAnyView,
                            int      nID )
{
   zVIEW    mFARuleS = new zVIEW( );
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );



   //:RETURN 0 
   return( 0 );
// END
} 


//:OBJECT CONSTRAINT OPERATION
//:ObjectConstraints( VIEW mFASrc BASED ON LOD mFASrc,
//:                   SHORT Event,
//:                   SHORT State )

//:   VIEW lFAAdmin BASED ON LOD mYear
public int 
omFASrc_ObjectConstraints( View     mFASrc,
                           Integer   Event,
                           Integer   State )
{
   zVIEW    lFAAdmin = new zVIEW( );
   //:VIEW mUser    BASED ON LOD mUser
   zVIEW    mUser = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:GET VIEW lFAAdmin NAMED "lFAAdmin"
         //:END
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


//:TRANSFORMATION OPERATION
//:BuildWorkTotals( VIEW mFASrc BASED ON LOD mFASrc )

//:   VIEW lFAAdmin REGISTERED AS lFAAdmin
public int 
omFASrc_BuildWorkTotals( View     mFASrc )
{
   zVIEW    lFAAdmin = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;
   double  dTempDecimal_4 = 0.0;
   double  dTempDecimal_5 = 0.0;
   double  dTempDecimal_6 = 0.0;
   double  dTempDecimal_7 = 0.0;
   double  dTempDecimal_8 = 0.0;
   double  dTempDecimal_9 = 0.0;
   double  dTempDecimal_10 = 0.0;
   double  dTempDecimal_11 = 0.0;
   int      lTempInteger_2 = 0;
   double  dTempDecimal_12 = 0.0;
   double  dTempDecimal_13 = 0.0;
   double  dTempDecimal_14 = 0.0;
   double  dTempDecimal_15 = 0.0;
   double  dTempDecimal_16 = 0.0;
   double  dTempDecimal_17 = 0.0;
   double  dTempDecimal_18 = 0.0;
   double  dTempDecimal_19 = 0.0;
   double  dTempDecimal_20 = 0.0;
   double  dTempDecimal_21 = 0.0;
   double  dTempDecimal_22 = 0.0;
   double  dTempDecimal_23 = 0.0;
   double  dTempDecimal_24 = 0.0;
   double  dTempDecimal_25 = 0.0;
   double  dTempDecimal_26 = 0.0;
   int      lTempInteger_3 = 0;
   double  dTempDecimal_27 = 0.0;
   double  dTempDecimal_28 = 0.0;
   double  dTempDecimal_29 = 0.0;

   RESULT = GetViewByName( lFAAdmin, "lFAAdmin", mFASrc, zLEVEL_TASK );

   //:// Build the work totals for the FinAid Source.

   //:FOR EACH mFASrc.PeriodTotals 
   RESULT = SetCursorFirstEntity( mFASrc, "PeriodTotals", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mFASrc.PeriodTotals NONE 
      RESULT = DeleteEntity( mFASrc, "PeriodTotals", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mFASrc, "PeriodTotals", "" );
   } 

   //:END

   //:// wAmountBudgeted
   //:SET CURSOR FIRST mFASrc.FinAidAnnualBudgeted  
   //:           WHERE mFASrc.CollegeYear.ID = lFAAdmin.CollegeYear.ID 
   RESULT = SetCursorFirstEntity( mFASrc, "FinAidAnnualBudgeted", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mFASrc, "CollegeYear", "ID", lFAAdmin, "CollegeYear", "ID" ) != 0 ) )
      { 
         RESULT = SetCursorNextEntity( mFASrc, "FinAidAnnualBudgeted", "" );
      } 

   } 

   //:IF RESULT >= zCURSOR_SET 
   if ( RESULT >= zCURSOR_SET )
   { 
      //:mFASrc.FinAidSource.wAmountBudgeted = mFASrc.FinAidAnnualBudgeted.Amount    
      SetAttributeFromAttribute( mFASrc, "FinAidSource", "wAmountBudgeted", mFASrc, "FinAidAnnualBudgeted", "Amount" );
      //:ELSE
   } 
   else
   { 
      //:mFASrc.FinAidSource.wAmountBudgeted = 0
      SetAttributeFromInteger( mFASrc, "FinAidSource", "wAmountBudgeted", 0 );
   } 

   //:END

   //:// Generate Totals by Source and Period.
   //:FOR EACH mFASrc.FinAidAwardAssigned 
   RESULT = SetCursorFirstEntity( mFASrc, "FinAidAwardAssigned", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mFASrc.AwardedCollegeYear EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( mFASrc, "AwardedCollegeYear" );
      if ( lTempInteger_0 == 0 )
      { 
         //:IF mFASrc.AwardedCollegeYear.ID = lFAAdmin.CollegeYear.ID 
         if ( CompareAttributeToAttribute( mFASrc, "AwardedCollegeYear", "ID", lFAAdmin, "CollegeYear", "ID" ) == 0 )
         { 
            //:IF mFASrc.FinAidAwardAssigned.AwardStatus != "N"  
            if ( CompareAttributeToString( mFASrc, "FinAidAwardAssigned", "AwardStatus", "N" ) != 0 )
            { 
               //:IF mFASrc.FinAidAwardPeriod EXISTS
               lTempInteger_1 = CheckExistenceOfEntity( mFASrc, "FinAidAwardPeriod" );
               if ( lTempInteger_1 == 0 )
               { 
                  //:SET CURSOR FIRST mFASrc.PeriodTotals 
                  //:           WHERE mFASrc.PeriodTotals.PeriodDesignator = mFASrc.FinAidAwardPeriod.PeriodDesignator 
                  {StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                     GetStringFromAttribute( sb_szTempString_0, mFASrc, "FinAidAwardPeriod", "PeriodDesignator" );
                  szTempString_0 = sb_szTempString_0.toString( );}
                  RESULT = SetCursorFirstEntityByString( mFASrc, "PeriodTotals", "PeriodDesignator", szTempString_0, "" );
                  //:IF RESULT < zCURSOR_SET
                  if ( RESULT < zCURSOR_SET )
                  { 
                     //:CREATE ENTITY mFASrc.PeriodTotals 
                     RESULT = CreateEntity( mFASrc, "PeriodTotals", zPOS_AFTER );
                     //:mFASrc.PeriodTotals.PeriodDesignator = mFASrc.FinAidAwardPeriod.PeriodDesignator
                     SetAttributeFromAttribute( mFASrc, "PeriodTotals", "PeriodDesignator", mFASrc, "FinAidAwardPeriod", "PeriodDesignator" );
                  } 

                  //:END
               } 

               //:END

               //:// TotalAwarded
               //:mFASrc.FinAidSource.wTotalAwarded = mFASrc.FinAidSource.wTotalAwarded +
               //:                                    mFASrc.FinAidAwardAssigned.Amount
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mFASrc, "FinAidSource", "wTotalAwarded" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                               GetDecimalFromAttribute( md_dTempDecimal_1, mFASrc, "FinAidAwardAssigned", "Amount" );
               dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
               dTempDecimal_2 = dTempDecimal_0 + dTempDecimal_1;
               SetAttributeFromDecimal( mFASrc, "FinAidSource", "wTotalAwarded", dTempDecimal_2 );
               //:mFASrc.PeriodTotals.wTotalAwarded = mFASrc.PeriodTotals.wTotalAwarded +
               //:                                    mFASrc.FinAidAwardAssigned.Amount
               {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                               GetDecimalFromAttribute( md_dTempDecimal_3, mFASrc, "PeriodTotals", "wTotalAwarded" );
               dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
               {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                               GetDecimalFromAttribute( md_dTempDecimal_4, mFASrc, "FinAidAwardAssigned", "Amount" );
               dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
               dTempDecimal_5 = dTempDecimal_3 + dTempDecimal_4;
               SetAttributeFromDecimal( mFASrc, "PeriodTotals", "wTotalAwarded", dTempDecimal_5 );

               //:// wTotalAccepted
               //:mFASrc.FinAidSource.wTotalAccepted = mFASrc.FinAidSource.wTotalAccepted +
               //:                                     mFASrc.FinAidAwardAssigned.Amount  
               {MutableDouble md_dTempDecimal_6 = new MutableDouble( dTempDecimal_6 );
                               GetDecimalFromAttribute( md_dTempDecimal_6, mFASrc, "FinAidSource", "wTotalAccepted" );
               dTempDecimal_6 = md_dTempDecimal_6.doubleValue( );}
               {MutableDouble md_dTempDecimal_7 = new MutableDouble( dTempDecimal_7 );
                               GetDecimalFromAttribute( md_dTempDecimal_7, mFASrc, "FinAidAwardAssigned", "Amount" );
               dTempDecimal_7 = md_dTempDecimal_7.doubleValue( );}
               dTempDecimal_8 = dTempDecimal_6 + dTempDecimal_7;
               SetAttributeFromDecimal( mFASrc, "FinAidSource", "wTotalAccepted", dTempDecimal_8 );
               //:mFASrc.PeriodTotals.wTotalAccepted = mFASrc.PeriodTotals.wTotalAccepted +
               //:                                     mFASrc.FinAidAwardAssigned.Amount 
               {MutableDouble md_dTempDecimal_9 = new MutableDouble( dTempDecimal_9 );
                               GetDecimalFromAttribute( md_dTempDecimal_9, mFASrc, "PeriodTotals", "wTotalAccepted" );
               dTempDecimal_9 = md_dTempDecimal_9.doubleValue( );}
               {MutableDouble md_dTempDecimal_10 = new MutableDouble( dTempDecimal_10 );
                               GetDecimalFromAttribute( md_dTempDecimal_10, mFASrc, "FinAidAwardAssigned", "Amount" );
               dTempDecimal_10 = md_dTempDecimal_10.doubleValue( );}
               dTempDecimal_11 = dTempDecimal_9 + dTempDecimal_10;
               SetAttributeFromDecimal( mFASrc, "PeriodTotals", "wTotalAccepted", dTempDecimal_11 );

               //:IF mFASrc.Student EXISTS
               lTempInteger_2 = CheckExistenceOfEntity( mFASrc, "Student" );
               if ( lTempInteger_2 == 0 )
               { 
                  //:// wTotalAwardedActiveStudents
                  //:IF mFASrc.Student.Status = "A"
                  if ( CompareAttributeToString( mFASrc, "Student", "Status", "A" ) == 0 )
                  { 
                     //:mFASrc.FinAidSource.wTotalAwardedActiveStudents = mFASrc.FinAidSource.wTotalAwardedActiveStudents +
                     //:                                                  mFASrc.FinAidAwardAssigned.Amount  
                     {MutableDouble md_dTempDecimal_12 = new MutableDouble( dTempDecimal_12 );
                                           GetDecimalFromAttribute( md_dTempDecimal_12, mFASrc, "FinAidSource", "wTotalAwardedActiveStudents" );
                     dTempDecimal_12 = md_dTempDecimal_12.doubleValue( );}
                     {MutableDouble md_dTempDecimal_13 = new MutableDouble( dTempDecimal_13 );
                                           GetDecimalFromAttribute( md_dTempDecimal_13, mFASrc, "FinAidAwardAssigned", "Amount" );
                     dTempDecimal_13 = md_dTempDecimal_13.doubleValue( );}
                     dTempDecimal_14 = dTempDecimal_12 + dTempDecimal_13;
                     SetAttributeFromDecimal( mFASrc, "FinAidSource", "wTotalAwardedActiveStudents", dTempDecimal_14 );
                     //:mFASrc.PeriodTotals.wTotalAwardedActiveStudents = mFASrc.PeriodTotals.wTotalAwardedActiveStudents +
                     //:                                                  mFASrc.FinAidAwardAssigned.Amount 
                     {MutableDouble md_dTempDecimal_15 = new MutableDouble( dTempDecimal_15 );
                                           GetDecimalFromAttribute( md_dTempDecimal_15, mFASrc, "PeriodTotals", "wTotalAwardedActiveStudents" );
                     dTempDecimal_15 = md_dTempDecimal_15.doubleValue( );}
                     {MutableDouble md_dTempDecimal_16 = new MutableDouble( dTempDecimal_16 );
                                           GetDecimalFromAttribute( md_dTempDecimal_16, mFASrc, "FinAidAwardAssigned", "Amount" );
                     dTempDecimal_16 = md_dTempDecimal_16.doubleValue( );}
                     dTempDecimal_17 = dTempDecimal_15 + dTempDecimal_16;
                     SetAttributeFromDecimal( mFASrc, "PeriodTotals", "wTotalAwardedActiveStudents", dTempDecimal_17 );
                  } 

                  //:END

                  //:// wTotalAcceptedActiveStudents
                  //:IF mFASrc.Student.Status = "A"
                  if ( CompareAttributeToString( mFASrc, "Student", "Status", "A" ) == 0 )
                  { 
                     //:mFASrc.FinAidSource.wTotalAcceptedActiveStudents = mFASrc.FinAidSource.wTotalAcceptedActiveStudents +
                     //:                                                   mFASrc.FinAidAwardAssigned.Amount  
                     {MutableDouble md_dTempDecimal_18 = new MutableDouble( dTempDecimal_18 );
                                           GetDecimalFromAttribute( md_dTempDecimal_18, mFASrc, "FinAidSource", "wTotalAcceptedActiveStudents" );
                     dTempDecimal_18 = md_dTempDecimal_18.doubleValue( );}
                     {MutableDouble md_dTempDecimal_19 = new MutableDouble( dTempDecimal_19 );
                                           GetDecimalFromAttribute( md_dTempDecimal_19, mFASrc, "FinAidAwardAssigned", "Amount" );
                     dTempDecimal_19 = md_dTempDecimal_19.doubleValue( );}
                     dTempDecimal_20 = dTempDecimal_18 + dTempDecimal_19;
                     SetAttributeFromDecimal( mFASrc, "FinAidSource", "wTotalAcceptedActiveStudents", dTempDecimal_20 );
                     //:mFASrc.PeriodTotals.wTotalAcceptedActiveStudents = mFASrc.PeriodTotals.wTotalAcceptedActiveStudents +
                     //:                                                   mFASrc.FinAidAwardAssigned.Amount 
                     {MutableDouble md_dTempDecimal_21 = new MutableDouble( dTempDecimal_21 );
                                           GetDecimalFromAttribute( md_dTempDecimal_21, mFASrc, "PeriodTotals", "wTotalAcceptedActiveStudents" );
                     dTempDecimal_21 = md_dTempDecimal_21.doubleValue( );}
                     {MutableDouble md_dTempDecimal_22 = new MutableDouble( dTempDecimal_22 );
                                           GetDecimalFromAttribute( md_dTempDecimal_22, mFASrc, "FinAidAwardAssigned", "Amount" );
                     dTempDecimal_22 = md_dTempDecimal_22.doubleValue( );}
                     dTempDecimal_23 = dTempDecimal_21 + dTempDecimal_22;
                     SetAttributeFromDecimal( mFASrc, "PeriodTotals", "wTotalAcceptedActiveStudents", dTempDecimal_23 );
                  } 

                  //:END
               } 

               //:END
            } 


            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mFASrc, "FinAidAwardAssigned", "" );
      //:END
   } 

   //:END

   //:// wTotalDisbursed
   //:FOR EACH mFASrc.FinAidAwardDisbursement WITHIN mFASrc.FinAidSource
   RESULT = SetCursorFirstEntity( mFASrc, "FinAidAwardDisbursement", "FinAidSource" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:mFASrc.FinAidSource.wTotalDisbursed = mFASrc.FinAidSource.wTotalDisbursed + 
      //:                                      mFASrc.FinAidAwardDisbursement.Amount   
      {MutableDouble md_dTempDecimal_24 = new MutableDouble( dTempDecimal_24 );
             GetDecimalFromAttribute( md_dTempDecimal_24, mFASrc, "FinAidSource", "wTotalDisbursed" );
      dTempDecimal_24 = md_dTempDecimal_24.doubleValue( );}
      {MutableDouble md_dTempDecimal_25 = new MutableDouble( dTempDecimal_25 );
             GetDecimalFromAttribute( md_dTempDecimal_25, mFASrc, "FinAidAwardDisbursement", "Amount" );
      dTempDecimal_25 = md_dTempDecimal_25.doubleValue( );}
      dTempDecimal_26 = dTempDecimal_24 + dTempDecimal_25;
      SetAttributeFromDecimal( mFASrc, "FinAidSource", "wTotalDisbursed", dTempDecimal_26 );

      //:IF mFASrc.FinAidAwardPeriod EXISTS
      lTempInteger_3 = CheckExistenceOfEntity( mFASrc, "FinAidAwardPeriod" );
      if ( lTempInteger_3 == 0 )
      { 
         //:SET CURSOR FIRST mFASrc.PeriodTotals 
         //:           WHERE mFASrc.PeriodTotals.PeriodDesignator = mFASrc.FinAidAwardPeriod.PeriodDesignator
         {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetStringFromAttribute( sb_szTempString_0, mFASrc, "FinAidAwardPeriod", "PeriodDesignator" );
         szTempString_0 = sb_szTempString_0.toString( );}
         RESULT = SetCursorFirstEntityByString( mFASrc, "PeriodTotals", "PeriodDesignator", szTempString_0, "" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:CREATE ENTITY mFASrc.PeriodTotals 
            RESULT = CreateEntity( mFASrc, "PeriodTotals", zPOS_AFTER );
            //:mFASrc.PeriodTotals.PeriodDesignator = mFASrc.FinAidAwardPeriod.PeriodDesignator  
            SetAttributeFromAttribute( mFASrc, "PeriodTotals", "PeriodDesignator", mFASrc, "FinAidAwardPeriod", "PeriodDesignator" );
         } 

         //:END
      } 

      //:END
      //:mFASrc.PeriodTotals.wTotalDisbursed = mFASrc.PeriodTotals.wTotalDisbursed +
      //:                                      mFASrc.FinAidAwardDisbursement.Amount 
      {MutableDouble md_dTempDecimal_27 = new MutableDouble( dTempDecimal_27 );
             GetDecimalFromAttribute( md_dTempDecimal_27, mFASrc, "PeriodTotals", "wTotalDisbursed" );
      dTempDecimal_27 = md_dTempDecimal_27.doubleValue( );}
      {MutableDouble md_dTempDecimal_28 = new MutableDouble( dTempDecimal_28 );
             GetDecimalFromAttribute( md_dTempDecimal_28, mFASrc, "FinAidAwardDisbursement", "Amount" );
      dTempDecimal_28 = md_dTempDecimal_28.doubleValue( );}
      dTempDecimal_29 = dTempDecimal_27 + dTempDecimal_28;
      SetAttributeFromDecimal( mFASrc, "PeriodTotals", "wTotalDisbursed", dTempDecimal_29 );
      RESULT = SetCursorNextEntity( mFASrc, "FinAidAwardDisbursement", "FinAidSource" );
   } 

   //:END
   return( 0 );
// END
} 



}
