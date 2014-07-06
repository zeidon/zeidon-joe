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
import com.quinsoft.zencas.ZGLOBAL2_Operation;

/**
   @author QuinSoft
**/

public class mEmploy_Object extends VmlObjectOperations
{
   public mEmploy_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
public int 
omEmploy_dFullNameLFM( View     mEmploy,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{

   //:dFullNameLFM( VIEW mEmploy BASED ON LOD mEmploy,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_LastFirstMiddle( mEmploy, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmploy );
          m_ZGLOBAL1_Operation.PersonName_LastFirstMiddle( mEmploy, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
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
//:dWorkersCompBenefit( VIEW mEmployOrig BASED ON LOD mEmploy,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   VIEW mEmploy    BASED ON LOD mEmploy
public int 
omEmploy_dWorkersCompBenefit( View     mEmployOrig,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   zVIEW    mEmploy = new zVIEW( );
   //:VIEW mStdSalLST BASED ON LOD mStdSal
   zVIEW    mStdSalLST = new zVIEW( );
   //:DECIMAL BenefitAmount
   double  BenefitAmount = 0.0;
   //:DECIMAL SalaryAmount
   double  SalaryAmount = 0.0;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 1 )  szSkipFlag
   String   szSkipFlag = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GET VIEW mStdSalLST NAMED "mStdSalLST"
         RESULT = GetViewByName( mStdSalLST, "mStdSalLST", mEmployOrig, zLEVEL_TASK );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:ACTIVATE mStdSalLST RootOnlyMultiple
            RESULT = ActivateObjectInstance( mStdSalLST, "mStdSal", mEmployOrig, 0, zACTIVATE_ROOTONLY_MULTIPLE );
            //:NAME VIEW mStdSalLST "mStdSalLST"
            SetNameForView( mStdSalLST, "mStdSalLST", null, zLEVEL_TASK );
         } 

         //:END

         //:// If the attribute is on the root then the request is for Active Contract. Otherwise the request is for HistoricalContract.
         //:CreateViewFromView( mEmploy, mEmployOrig )
         CreateViewFromView( mEmploy, mEmployOrig );
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmployOrig );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "Employee"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "Employee", 1, 0, 33 ) == 0 )
         { 
            //:SET CURSOR FIRST mEmploy.HistoricalContract WHERE mEmploy.HistoricalContract.Status = "A"
            RESULT = SetCursorFirstEntityByString( mEmploy, "HistoricalContract", "Status", "A", "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:szSkipFlag = "Y"
                {StringBuilder sb_szSkipFlag;
               if ( szSkipFlag == null )
                  sb_szSkipFlag = new StringBuilder( 32 );
               else
                  sb_szSkipFlag = new StringBuilder( szSkipFlag );
                              ZeidonStringCopy( sb_szSkipFlag, 1, 0, "Y", 1, 0, 2 );
               szSkipFlag = sb_szSkipFlag.toString( );}
               //:ELSE
            } 
            else
            { 
               //:// "Contracted Employee" contracts don't have any benefits.
               //:IF mEmploy.HistoricalContract.EmployeeClassification = "C"
               if ( CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "C" ) == 0 )
               { 
                  //:szSkipFlag = "Y"
                   {StringBuilder sb_szSkipFlag;
                  if ( szSkipFlag == null )
                     sb_szSkipFlag = new StringBuilder( 32 );
                  else
                     sb_szSkipFlag = new StringBuilder( szSkipFlag );
                                    ZeidonStringCopy( sb_szSkipFlag, 1, 0, "Y", 1, 0, 2 );
                  szSkipFlag = sb_szSkipFlag.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:IF szSkipFlag = ""
         if ( ZeidonStringCompare( szSkipFlag, 1, 0, "", 1, 0, 2 ) == 0 )
         { 
            //:SET CURSOR FIRST mStdSalLST.StandardSalaryData WHERE mStdSalLST.StandardSalaryData.ID = mEmploy.HistContractStandardSalaryData.ID 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mEmploy, "HistContractStandardSalaryData", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mStdSalLST, "StandardSalaryData", "ID", lTempInteger_0, "" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:SalaryAmount = mEmploy.HistoricalContract.dTotalSalaryAmount
               {MutableDouble md_SalaryAmount = new MutableDouble( SalaryAmount );
                               GetDecimalFromAttribute( md_SalaryAmount, mEmploy, "HistoricalContract", "dTotalSalaryAmount" );
               SalaryAmount = md_SalaryAmount.doubleValue( );}
               //:BenefitAmount = ( SalaryAmount * mStdSalLST.StandardSalaryData.WorkersCompensationRate ) / 100 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mStdSalLST, "StandardSalaryData", "WorkersCompensationRate" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               BenefitAmount = ( SalaryAmount * dTempDecimal_0 ) / 100;
               //:StoreValueInRecord( mEmploy,InternalEntityStructure, InternalAttribStructure, BenefitAmount, 0 )
               StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, BenefitAmount, 0 );
               //:ELSE
            } 
            else
            { 
               //:StoreStringInRecord ( mEmploy,
               //:                   InternalEntityStructure, InternalAttribStructure, "" )
               StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, "" );
            } 

            //:END
         } 

         //:END
         //:DropView( mEmploy )
         DropView( mEmploy );
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
//:dTIAA_RetiremntBenefit( VIEW mEmployOrig BASED ON LOD mEmploy,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   VIEW mEmploy    BASED ON LOD mEmploy
public int 
omEmploy_dTIAA_RetiremntBenefit( View     mEmployOrig,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   zVIEW    mEmploy = new zVIEW( );
   //:VIEW mStdSalLST BASED ON LOD mStdSal
   zVIEW    mStdSalLST = new zVIEW( );
   //:DECIMAL BenefitAmount
   double  BenefitAmount = 0.0;
   //:DECIMAL SalaryAmount
   double  SalaryAmount = 0.0;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 1 )  szSkipFlag
   String   szSkipFlag = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GET VIEW mStdSalLST NAMED "mStdSalLST"
         RESULT = GetViewByName( mStdSalLST, "mStdSalLST", mEmployOrig, zLEVEL_TASK );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:ACTIVATE mStdSalLST RootOnlyMultiple
            RESULT = ActivateObjectInstance( mStdSalLST, "mStdSal", mEmployOrig, 0, zACTIVATE_ROOTONLY_MULTIPLE );
            //:NAME VIEW mStdSalLST "mStdSalLST"
            SetNameForView( mStdSalLST, "mStdSalLST", null, zLEVEL_TASK );
         } 

         //:END

         //:// If the attribute is on the root then the request is for Active Contract. Otherwise the request is for HistoricalContract.
         //:CreateViewFromView( mEmploy, mEmployOrig )
         CreateViewFromView( mEmploy, mEmployOrig );
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmployOrig );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "Employee"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "Employee", 1, 0, 33 ) == 0 )
         { 
            //:SET CURSOR FIRST mEmploy.HistoricalContract WHERE mEmploy.HistoricalContract.Status = "A"
            RESULT = SetCursorFirstEntityByString( mEmploy, "HistoricalContract", "Status", "A", "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:szSkipFlag = "Y"
                {StringBuilder sb_szSkipFlag;
               if ( szSkipFlag == null )
                  sb_szSkipFlag = new StringBuilder( 32 );
               else
                  sb_szSkipFlag = new StringBuilder( szSkipFlag );
                              ZeidonStringCopy( sb_szSkipFlag, 1, 0, "Y", 1, 0, 2 );
               szSkipFlag = sb_szSkipFlag.toString( );}
               //:ELSE
            } 
            else
            { 
               //:// "Contracted Employee" contracts don't have any benefits.
               //:IF mEmploy.HistoricalContract.EmployeeClassification = "C"
               if ( CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "C" ) == 0 )
               { 
                  //:szSkipFlag = "Y"
                   {StringBuilder sb_szSkipFlag;
                  if ( szSkipFlag == null )
                     sb_szSkipFlag = new StringBuilder( 32 );
                  else
                     sb_szSkipFlag = new StringBuilder( szSkipFlag );
                                    ZeidonStringCopy( sb_szSkipFlag, 1, 0, "Y", 1, 0, 2 );
                  szSkipFlag = sb_szSkipFlag.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:IF szSkipFlag = ""
         if ( ZeidonStringCompare( szSkipFlag, 1, 0, "", 1, 0, 2 ) == 0 )
         { 
            //:SET CURSOR FIRST mStdSalLST.StandardSalaryData WHERE mStdSalLST.StandardSalaryData.ID = mEmploy.HistContractStandardSalaryData.ID 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mEmploy, "HistContractStandardSalaryData", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mStdSalLST, "StandardSalaryData", "ID", lTempInteger_0, "" );
            //:IF RESULT >= zCURSOR_SET AND 
            //:mEmploy.HistoricalContract.RetirementBenefitsType != "" AND 
            //:mEmploy.HistoricalContract.RetirementBenefitsType != "None"
            if ( RESULT >= zCURSOR_SET && CompareAttributeToString( mEmploy, "HistoricalContract", "RetirementBenefitsType", "" ) != 0 && CompareAttributeToString( mEmploy, "HistoricalContract", "RetirementBenefitsType", "None" ) != 0 )
            { 

               //:SalaryAmount = mEmploy.HistoricalContract.dTotalSalaryAmount
               {MutableDouble md_SalaryAmount = new MutableDouble( SalaryAmount );
                               GetDecimalFromAttribute( md_SalaryAmount, mEmploy, "HistoricalContract", "dTotalSalaryAmount" );
               SalaryAmount = md_SalaryAmount.doubleValue( );}
               //:BenefitAmount = SalaryAmount * mStdSalLST.StandardSalaryData.WithholdingRetirementRate 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mStdSalLST, "StandardSalaryData", "WithholdingRetirementRate" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               BenefitAmount = SalaryAmount * dTempDecimal_0;
               //:StoreValueInRecord( mEmploy,InternalEntityStructure, InternalAttribStructure, BenefitAmount, 0 )
               StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, BenefitAmount, 0 );
               //:ELSE
            } 
            else
            { 
               //:StoreStringInRecord ( mEmploy,
               //:                   InternalEntityStructure, InternalAttribStructure, "" )
               StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, "" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:StoreStringInRecord ( mEmploy,
            //:                   InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, "" );
         } 

         //:END
         //:DropView( mEmploy )
         DropView( mEmploy );
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
//:dLifeDisabilityBenefit( VIEW mEmployOrig BASED ON LOD mEmploy,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   VIEW mEmploy    BASED ON LOD mEmploy
public int 
omEmploy_dLifeDisabilityBenefit( View     mEmployOrig,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   zVIEW    mEmploy = new zVIEW( );
   //:VIEW mStdSalLST BASED ON LOD mStdSal
   zVIEW    mStdSalLST = new zVIEW( );
   //:VIEW wXferO REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL BenefitAmount
   double  BenefitAmount = 0.0;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 1 )  szSkipFlag
   String   szSkipFlag = null;
   //:STRING ( 20 ) szDecimal
   String   szDecimal = null;
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;

   RESULT = GetViewByName( wXferO, "wXferO", mEmployOrig, zLEVEL_TASK );

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GET VIEW mStdSalLST NAMED "mStdSalLST"
         RESULT = GetViewByName( mStdSalLST, "mStdSalLST", mEmployOrig, zLEVEL_TASK );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:ACTIVATE mStdSalLST RootOnlyMultiple
            RESULT = ActivateObjectInstance( mStdSalLST, "mStdSal", mEmployOrig, 0, zACTIVATE_ROOTONLY_MULTIPLE );
            //:NAME VIEW mStdSalLST "mStdSalLST"
            SetNameForView( mStdSalLST, "mStdSalLST", null, zLEVEL_TASK );
         } 

         //:END

         //:// If the attribute is on the root then the request is for Active Contract. Otherwise the request is for HistoricalContract.
         //:CreateViewFromView( mEmploy, mEmployOrig )
         CreateViewFromView( mEmploy, mEmployOrig );
         //:NAME VIEW mEmploy "mEmployDisability"
         SetNameForView( mEmploy, "mEmployDisability", null, zLEVEL_TASK );
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmployOrig );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "Employee"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "Employee", 1, 0, 33 ) == 0 )
         { 
            //:SET CURSOR FIRST mEmploy.HistoricalContract WHERE mEmploy.HistoricalContract.Status = "A"
            RESULT = SetCursorFirstEntityByString( mEmploy, "HistoricalContract", "Status", "A", "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:szSkipFlag = "Y"
                {StringBuilder sb_szSkipFlag;
               if ( szSkipFlag == null )
                  sb_szSkipFlag = new StringBuilder( 32 );
               else
                  sb_szSkipFlag = new StringBuilder( szSkipFlag );
                              ZeidonStringCopy( sb_szSkipFlag, 1, 0, "Y", 1, 0, 2 );
               szSkipFlag = sb_szSkipFlag.toString( );}
               //:ELSE
            } 
            else
            { 
               //:// "Contracted Employee" contracts don't have any benefits.
               //:IF mEmploy.HistoricalContract.EmployeeClassification = "C"
               if ( CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "C" ) == 0 )
               { 
                  //:szSkipFlag = "Y"
                   {StringBuilder sb_szSkipFlag;
                  if ( szSkipFlag == null )
                     sb_szSkipFlag = new StringBuilder( 32 );
                  else
                     sb_szSkipFlag = new StringBuilder( szSkipFlag );
                                    ZeidonStringCopy( sb_szSkipFlag, 1, 0, "Y", 1, 0, 2 );
                  szSkipFlag = sb_szSkipFlag.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:IF szSkipFlag = ""
         if ( ZeidonStringCompare( szSkipFlag, 1, 0, "", 1, 0, 2 ) == 0 )
         { 
            //:SET CURSOR FIRST mStdSalLST.StandardSalaryData WHERE mStdSalLST.StandardSalaryData.ID = mEmploy.HistContractStandardSalaryData.ID 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mEmploy, "HistContractStandardSalaryData", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mStdSalLST, "StandardSalaryData", "ID", lTempInteger_0, "" );
            //:IF RESULT >= zCURSOR_SET AND mEmploy.HistoricalContract.FullPartTimeFlag = "F"
            if ( RESULT >= zCURSOR_SET && CompareAttributeToString( mEmploy, "HistoricalContract", "FullPartTimeFlag", "F" ) == 0 )
            { 
               //:BenefitAmount = mEmploy.HistoricalContract.dTotalSalaryAmount *
               //:             ( mStdSalLST.StandardSalaryData.DisabilityInsuranceRate / 100 ) +
               //:               mStdSalLST.StandardSalaryData.LifeInsuranceAmount 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mStdSalLST, "StandardSalaryData", "DisabilityInsuranceRate" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                               GetDecimalFromAttribute( md_dTempDecimal_1, mEmploy, "HistoricalContract", "dTotalSalaryAmount" );
               dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
               {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                               GetDecimalFromAttribute( md_dTempDecimal_2, mStdSalLST, "StandardSalaryData", "LifeInsuranceAmount" );
               dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
               BenefitAmount = dTempDecimal_1 * ( dTempDecimal_0 / 100 ) + dTempDecimal_2;
               //:StoreValueInRecord( mEmploy,InternalEntityStructure, InternalAttribStructure, BenefitAmount, 0 )
               StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, BenefitAmount, 0 );
               //:ELSE
            } 
            else
            { 
               //:StoreStringInRecord ( mEmploy,
               //:                   InternalEntityStructure, InternalAttribStructure, "" )
               StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, "" );
            } 

            //:END
         } 

         //:END
         //:DropView( mEmploy )
         DropView( mEmploy );
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
//:dMedPlanCostBenefit( VIEW mEmployOrig BASED ON LOD mEmploy,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   VIEW mEmploy    BASED ON LOD mEmploy
public int 
omEmploy_dMedPlanCostBenefit( View     mEmployOrig,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   zVIEW    mEmploy = new zVIEW( );
   //:VIEW mMedCodeLST BASED ON LOD mMedCode
   zVIEW    mMedCodeLST = new zVIEW( );
   //:DECIMAL BenefitAmount
   double  BenefitAmount = 0.0;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 1 )  szSkipFlag
   String   szSkipFlag = null;
   //:STRING ( 4 )  szCurrentMedicalYear
   String   szCurrentMedicalYear = null;
   //:STRING ( 9 )  szFiscalYearLabel
   String   szFiscalYearLabel = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GET VIEW mMedCodeLST NAMED "mAllMedCodeLST"
         RESULT = GetViewByName( mMedCodeLST, "mAllMedCodeLST", mEmployOrig, zLEVEL_TASK );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:ACTIVATE mMedCodeLST RootOnlyMultiple WHERE mMedCodeLST.MedicalCode.FunctionalAreaFlag = ""
            omEmploy_fnLocalBuildQual_0( mEmployOrig, vTempViewVar_0 );
            RESULT = ActivateObjectInstance( mMedCodeLST, "mMedCode", mEmployOrig, vTempViewVar_0, zACTIVATE_ROOTONLY_MULTIPLE );
            DropView( vTempViewVar_0 );
            //:NAME VIEW mMedCodeLST "mAllMedCodeLST"
            SetNameForView( mMedCodeLST, "mAllMedCodeLST", null, zLEVEL_TASK );
         } 

         //:END

         //:// If the attribute is on the root then the request is for Active Contract. Otherwise the request is for HistoricalContract.
         //:CreateViewFromView( mEmploy, mEmployOrig )
         CreateViewFromView( mEmploy, mEmployOrig );
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmployOrig );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "Employee"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "Employee", 1, 0, 33 ) == 0 )
         { 
            //:SET CURSOR FIRST mEmploy.HistoricalContract WHERE mEmploy.HistoricalContract.Status = "A"
            RESULT = SetCursorFirstEntityByString( mEmploy, "HistoricalContract", "Status", "A", "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:szSkipFlag = "Y"
                {StringBuilder sb_szSkipFlag;
               if ( szSkipFlag == null )
                  sb_szSkipFlag = new StringBuilder( 32 );
               else
                  sb_szSkipFlag = new StringBuilder( szSkipFlag );
                              ZeidonStringCopy( sb_szSkipFlag, 1, 0, "Y", 1, 0, 2 );
               szSkipFlag = sb_szSkipFlag.toString( );}
               //:ELSE
            } 
            else
            { 
               //:// "Contracted Employee" contracts don't have any benefits.
               //:IF mEmploy.HistoricalContract.EmployeeClassification = "C"
               if ( CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "C" ) == 0 )
               { 
                  //:szSkipFlag = "Y"
                   {StringBuilder sb_szSkipFlag;
                  if ( szSkipFlag == null )
                     sb_szSkipFlag = new StringBuilder( 32 );
                  else
                     sb_szSkipFlag = new StringBuilder( szSkipFlag );
                                    ZeidonStringCopy( sb_szSkipFlag, 1, 0, "Y", 1, 0, 2 );
                  szSkipFlag = sb_szSkipFlag.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:IF szSkipFlag = ""
         if ( ZeidonStringCompare( szSkipFlag, 1, 0, "", 1, 0, 2 ) == 0 )
         { 
            //:IF mEmploy.HistoricalContract.MedicalCode != ""
            if ( CompareAttributeToString( mEmploy, "HistoricalContract", "MedicalCode", "" ) != 0 )
            { 
               //:// Changed by DonC on 4/23/2010 to use last 4 characters of label.
               //:szFiscalYearLabel = mEmploy.HistoricalContractGLFiscalYear.FiscalYearLabel
               {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
               StringBuilder sb_szFiscalYearLabel;
               if ( szFiscalYearLabel == null )
                  sb_szFiscalYearLabel = new StringBuilder( 32 );
               else
                  sb_szFiscalYearLabel = new StringBuilder( szFiscalYearLabel );
                               GetVariableFromAttribute( sb_szFiscalYearLabel, mi_lTempInteger_0, 'S', 10, mEmploy, "HistoricalContractGLFiscalYear", "FiscalYearLabel", "", 0 );
               lTempInteger_0 = mi_lTempInteger_0.intValue( );
               szFiscalYearLabel = sb_szFiscalYearLabel.toString( );}
               //:szCurrentMedicalYear = szFiscalYearLabel[6:4]
                {StringBuilder sb_szCurrentMedicalYear;
               if ( szCurrentMedicalYear == null )
                  sb_szCurrentMedicalYear = new StringBuilder( 32 );
               else
                  sb_szCurrentMedicalYear = new StringBuilder( szCurrentMedicalYear );
                              ZeidonStringCopy( sb_szCurrentMedicalYear, 1, 0, szFiscalYearLabel, 6, 4, 5 );
               szCurrentMedicalYear = sb_szCurrentMedicalYear.toString( );}
               //://szCurrentYear = mEmploy.HistoricalContractGLFiscalYear.FiscalYearLabel   // The MedicalCode.Year is the first 4 chars of FiscalYearLabel 
               //:SET CURSOR FIRST mMedCodeLST.MedicalCode 
               //:        WHERE mMedCodeLST.MedicalCode.Year = szCurrentMedicalYear
               //:          AND mMedCodeLST.MedicalCode.Code = mEmploy.HistoricalContract.MedicalCode
               RESULT = SetCursorFirstEntity( mMedCodeLST, "MedicalCode", "" );
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mMedCodeLST, "MedicalCode", "Year", szCurrentMedicalYear ) != 0 ||
                        CompareAttributeToAttribute( mMedCodeLST, "MedicalCode", "Code", mEmploy, "HistoricalContract", "MedicalCode" ) != 0 ) )
                  { 
                     RESULT = SetCursorNextEntity( mMedCodeLST, "MedicalCode", "" );
                  } 

               } 

               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:BenefitAmount = mMedCodeLST.MedicalCode.MedicalPlanCost
                  {MutableDouble md_BenefitAmount = new MutableDouble( BenefitAmount );
                                     GetDecimalFromAttribute( md_BenefitAmount, mMedCodeLST, "MedicalCode", "MedicalPlanCost" );
                  BenefitAmount = md_BenefitAmount.doubleValue( );}
                  //:StoreValueInRecord( mEmploy,InternalEntityStructure, InternalAttribStructure, BenefitAmount, 0 )
                  StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, BenefitAmount, 0 );
                  //:ELSE
               } 
               else
               { 
                  //:StoreStringInRecord ( mEmploy,
                  //:                   InternalEntityStructure, InternalAttribStructure, "" )
                  StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, "" );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:StoreStringInRecord ( mEmploy,
               //:                   InternalEntityStructure, InternalAttribStructure, "" )
               StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, "" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:StoreStringInRecord ( mEmploy,
            //:                   InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, "" );
         } 

         //:END
         //:DropView( mEmploy )
         DropView( mEmploy );
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
//:dFICA_MedicareBenefit( VIEW mEmployOrig BASED ON LOD mEmploy,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   VIEW mEmploy    BASED ON LOD mEmploy
public int 
omEmploy_dFICA_MedicareBenefit( View     mEmployOrig,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   zVIEW    mEmploy = new zVIEW( );
   //:VIEW mStdSalLST BASED ON LOD mStdSal
   zVIEW    mStdSalLST = new zVIEW( );
   //:DECIMAL BenefitAmount
   double  BenefitAmount = 0.0;
   //:DECIMAL SalaryAmount
   double  SalaryAmount = 0.0;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 1 )  szSkipFlag
   String   szSkipFlag = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GET VIEW mStdSalLST NAMED "mStdSalLST"
         RESULT = GetViewByName( mStdSalLST, "mStdSalLST", mEmployOrig, zLEVEL_TASK );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:ACTIVATE mStdSalLST RootOnlyMultiple
            RESULT = ActivateObjectInstance( mStdSalLST, "mStdSal", mEmployOrig, 0, zACTIVATE_ROOTONLY_MULTIPLE );
            //:NAME VIEW mStdSalLST "mStdSalLST"
            SetNameForView( mStdSalLST, "mStdSalLST", null, zLEVEL_TASK );
         } 

         //:END

         //:// If the attribute is on the root then the request is for Active Contract. Otherwise the request is for HistoricalContract.
         //:CreateViewFromView( mEmploy, mEmployOrig )
         CreateViewFromView( mEmploy, mEmployOrig );
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmployOrig );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "Employee"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "Employee", 1, 0, 33 ) == 0 )
         { 
            //:SET CURSOR FIRST mEmploy.HistoricalContract WHERE mEmploy.HistoricalContract.Status = "A"
            RESULT = SetCursorFirstEntityByString( mEmploy, "HistoricalContract", "Status", "A", "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:szSkipFlag = "Y"
                {StringBuilder sb_szSkipFlag;
               if ( szSkipFlag == null )
                  sb_szSkipFlag = new StringBuilder( 32 );
               else
                  sb_szSkipFlag = new StringBuilder( szSkipFlag );
                              ZeidonStringCopy( sb_szSkipFlag, 1, 0, "Y", 1, 0, 2 );
               szSkipFlag = sb_szSkipFlag.toString( );}
               //:ELSE
            } 
            else
            { 
               //:// "Contracted Employee" contracts don't have any benefits.
               //:IF mEmploy.HistoricalContract.EmployeeClassification = "C"
               if ( CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "C" ) == 0 )
               { 
                  //:szSkipFlag = "Y"
                   {StringBuilder sb_szSkipFlag;
                  if ( szSkipFlag == null )
                     sb_szSkipFlag = new StringBuilder( 32 );
                  else
                     sb_szSkipFlag = new StringBuilder( szSkipFlag );
                                    ZeidonStringCopy( sb_szSkipFlag, 1, 0, "Y", 1, 0, 2 );
                  szSkipFlag = sb_szSkipFlag.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:IF szSkipFlag = ""
         if ( ZeidonStringCompare( szSkipFlag, 1, 0, "", 1, 0, 2 ) == 0 )
         { 
            //:SET CURSOR FIRST mStdSalLST.StandardSalaryData WHERE mStdSalLST.StandardSalaryData.ID = mEmploy.HistContractStandardSalaryData.ID 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mEmploy, "HistContractStandardSalaryData", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mStdSalLST, "StandardSalaryData", "ID", lTempInteger_0, "" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:SalaryAmount = mEmploy.HistoricalContract.dTotalSalaryAmount
               {MutableDouble md_SalaryAmount = new MutableDouble( SalaryAmount );
                               GetDecimalFromAttribute( md_SalaryAmount, mEmploy, "HistoricalContract", "dTotalSalaryAmount" );
               SalaryAmount = md_SalaryAmount.doubleValue( );}
               //:IF SalaryAmount > mStdSalLST.StandardSalaryData.FICA_MEDICARE_MaxAmount 
               if ( CompareAttributeToDecimal( mStdSalLST, "StandardSalaryData", "FICA_MEDICARE_MaxAmount", SalaryAmount ) < 0 )
               { 
                  //:SalaryAmount = mStdSalLST.StandardSalaryData.FICA_MEDICARE_MaxAmount
                  {MutableDouble md_SalaryAmount = new MutableDouble( SalaryAmount );
                                     GetDecimalFromAttribute( md_SalaryAmount, mStdSalLST, "StandardSalaryData", "FICA_MEDICARE_MaxAmount" );
                  SalaryAmount = md_SalaryAmount.doubleValue( );}
               } 

               //:END
               //:BenefitAmount = SalaryAmount * mStdSalLST.StandardSalaryData.FICA_MEDICARE_Rate 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mStdSalLST, "StandardSalaryData", "FICA_MEDICARE_Rate" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               BenefitAmount = SalaryAmount * dTempDecimal_0;
               //:StoreValueInRecord( mEmploy,InternalEntityStructure, InternalAttribStructure, BenefitAmount, 0 )
               StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, BenefitAmount, 0 );
               //:ELSE
            } 
            else
            { 
               //:StoreStringInRecord ( mEmploy,
               //:                   InternalEntityStructure, InternalAttribStructure, "" )
               StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, "" );
            } 

            //:END
         } 

         //:END
         //:DropView( mEmploy )
         DropView( mEmploy );
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
//:dFICA_OASDI_Benefit( VIEW mEmployOrig BASED ON LOD mEmploy,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   VIEW mEmploy    BASED ON LOD mEmploy
public int 
omEmploy_dFICA_OASDI_Benefit( View     mEmployOrig,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   zVIEW    mEmploy = new zVIEW( );
   //:VIEW mStdSalLST BASED ON LOD mStdSal
   zVIEW    mStdSalLST = new zVIEW( );
   //:DECIMAL BenefitAmount
   double  BenefitAmount = 0.0;
   //:DECIMAL SalaryAmount
   double  SalaryAmount = 0.0;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 1 )  szSkipFlag
   String   szSkipFlag = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GET VIEW mStdSalLST NAMED "mStdSalLST"
         RESULT = GetViewByName( mStdSalLST, "mStdSalLST", mEmployOrig, zLEVEL_TASK );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:ACTIVATE mStdSalLST RootOnlyMultiple
            RESULT = ActivateObjectInstance( mStdSalLST, "mStdSal", mEmployOrig, 0, zACTIVATE_ROOTONLY_MULTIPLE );
            //:NAME VIEW mStdSalLST "mStdSalLST"
            SetNameForView( mStdSalLST, "mStdSalLST", null, zLEVEL_TASK );
         } 

         //:END

         //:// If the attribute is on the root then the request is for Active Contract. Otherwise the request is for HistoricalContract.
         //:CreateViewFromView( mEmploy, mEmployOrig )
         CreateViewFromView( mEmploy, mEmployOrig );
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmployOrig );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "Employee"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "Employee", 1, 0, 33 ) == 0 )
         { 
            //:SET CURSOR FIRST mEmploy.HistoricalContract WHERE mEmploy.HistoricalContract.Status = "A"
            RESULT = SetCursorFirstEntityByString( mEmploy, "HistoricalContract", "Status", "A", "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:szSkipFlag = "Y"
                {StringBuilder sb_szSkipFlag;
               if ( szSkipFlag == null )
                  sb_szSkipFlag = new StringBuilder( 32 );
               else
                  sb_szSkipFlag = new StringBuilder( szSkipFlag );
                              ZeidonStringCopy( sb_szSkipFlag, 1, 0, "Y", 1, 0, 2 );
               szSkipFlag = sb_szSkipFlag.toString( );}
               //:ELSE
            } 
            else
            { 
               //:// "Contracted Employee" contracts don't have any benefits.
               //:IF mEmploy.HistoricalContract.EmployeeClassification = "C"
               if ( CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "C" ) == 0 )
               { 
                  //:szSkipFlag = "Y"
                   {StringBuilder sb_szSkipFlag;
                  if ( szSkipFlag == null )
                     sb_szSkipFlag = new StringBuilder( 32 );
                  else
                     sb_szSkipFlag = new StringBuilder( szSkipFlag );
                                    ZeidonStringCopy( sb_szSkipFlag, 1, 0, "Y", 1, 0, 2 );
                  szSkipFlag = sb_szSkipFlag.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:IF szSkipFlag = ""
         if ( ZeidonStringCompare( szSkipFlag, 1, 0, "", 1, 0, 2 ) == 0 )
         { 
            //:SET CURSOR FIRST mStdSalLST.StandardSalaryData WHERE mStdSalLST.StandardSalaryData.ID = mEmploy.HistContractStandardSalaryData.ID 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mEmploy, "HistContractStandardSalaryData", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mStdSalLST, "StandardSalaryData", "ID", lTempInteger_0, "" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:SalaryAmount = mEmploy.HistoricalContract.dTotalSalaryAmount
               {MutableDouble md_SalaryAmount = new MutableDouble( SalaryAmount );
                               GetDecimalFromAttribute( md_SalaryAmount, mEmploy, "HistoricalContract", "dTotalSalaryAmount" );
               SalaryAmount = md_SalaryAmount.doubleValue( );}
               //:IF SalaryAmount > mStdSalLST.StandardSalaryData.FICA_OASDI_MaxAmount
               if ( CompareAttributeToDecimal( mStdSalLST, "StandardSalaryData", "FICA_OASDI_MaxAmount", SalaryAmount ) < 0 )
               { 
                  //:SalaryAmount = mStdSalLST.StandardSalaryData.FICA_OASDI_MaxAmount
                  {MutableDouble md_SalaryAmount = new MutableDouble( SalaryAmount );
                                     GetDecimalFromAttribute( md_SalaryAmount, mStdSalLST, "StandardSalaryData", "FICA_OASDI_MaxAmount" );
                  SalaryAmount = md_SalaryAmount.doubleValue( );}
               } 

               //:END
               //:BenefitAmount = SalaryAmount * mStdSalLST.StandardSalaryData.FICA_OASDI_Rate 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mStdSalLST, "StandardSalaryData", "FICA_OASDI_Rate" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               BenefitAmount = SalaryAmount * dTempDecimal_0;
               //:StoreValueInRecord( mEmploy,InternalEntityStructure, InternalAttribStructure, BenefitAmount, 0 )
               StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, BenefitAmount, 0 );
               //:ELSE
            } 
            else
            { 
               //:StoreStringInRecord ( mEmploy,
               //:                   InternalEntityStructure, InternalAttribStructure, "" )
               StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, "" );
            } 

            //:END
         } 

         //:END
         //:DropView( mEmploy )
         DropView( mEmploy );
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
//:ObjectConstraints( VIEW mEmploy BASED ON LOD mEmploy,
//:                   SHORT Event,
//:                   SHORT State )

//:   VIEW mEmployT BASED ON LOD mEmploy
public int 
omEmploy_ObjectConstraints( View     mEmploy,
                            Integer   Event,
                            Integer   State )
{
   zVIEW    mEmployT = new zVIEW( );
   //:VIEW mUser    BASED ON LOD mUser
   zVIEW    mUser = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;
   //:DECIMAL dTotalSalary
   double  dTotalSalary = 0.0;
   //:INTEGER lTotalSalary
   int      lTotalSalary = 0;
   //:STRING ( 10 )  szExternalString
   String   szExternalString = null;
   //:STRING ( 200 ) szMsg
   String   szMsg = null;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;


   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:// Error check put in by DonC on 3/18/09 because we found Employees with a Historical Contract marked Active but no active contract relationship.
         //:CreateViewFromView( mEmployT, mEmploy )
         CreateViewFromView( mEmployT, mEmploy );
         //:SET CURSOR FIRST mEmployT.HistoricalContract WHERE mEmployT.HistoricalContract.Status = "A"
         RESULT = SetCursorFirstEntityByString( mEmployT, "HistoricalContract", "Status", "A", "" );
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:SET CURSOR FIRST mEmployT.ActiveYearlyContract WHERE mEmployT.ActiveYearlyContract.Status = "A"
            RESULT = SetCursorFirstEntityByString( mEmployT, "ActiveYearlyContract", "Status", "A", "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:szMsg = "An active contract for " + mEmploy.EmployeePerson.dFullNameLFM + " does not have an active relationship. Please report to Systems Support."
               {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 255, mEmploy, "EmployeePerson", "dFullNameLFM", "", 0 );
               lTempInteger_0 = mi_lTempInteger_0.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringCopy( sb_szMsg, 1, 0, "An active contract for ", 1, 0, 201 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_0, 1, 0, 201 );
               szMsg = sb_szMsg.toString( );}
                {StringBuilder sb_szMsg;
               if ( szMsg == null )
                  sb_szMsg = new StringBuilder( 32 );
               else
                  sb_szMsg = new StringBuilder( szMsg );
                              ZeidonStringConcat( sb_szMsg, 1, 0, " does not have an active relationship. Please report to Systems Support.", 1, 0, 201 );
               szMsg = sb_szMsg.toString( );}
               //:IssueError( mEmploy,0,0, szMsg )
               IssueError( mEmploy, 0, 0, szMsg );
            } 

            //:END
         } 

         //:END 
         //:DropView( mEmployT )
         DropView( mEmployT );

         //:// Process for each Employee.
         //:FOR EACH mEmploy.Employee 
         RESULT = SetCursorFirstEntity( mEmploy, "Employee", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 

            //:// Create the active Derived Entity, YearlyContract.
            //:SET CURSOR FIRST mEmploy.HistoricalContract WHERE mEmploy.HistoricalContract.Status = "A"
            RESULT = SetCursorFirstEntityByString( mEmploy, "HistoricalContract", "Status", "A", "" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:INCLUDE mEmploy.YearlyContract FROM mEmploy.HistoricalContract 
               RESULT = IncludeSubobjectFromSubobject( mEmploy, "YearlyContract", mEmploy, "HistoricalContract", zPOS_AFTER );
            } 

            //:END 
            //:SET CURSOR FIRST mEmploy.HistoricalContract
            RESULT = SetCursorFirstEntity( mEmploy, "HistoricalContract", "" );
            //:OrderEntityForView( mEmploy, "FinAidProfile", "ProfileYearBegin D" )
            OrderEntityForView( mEmploy, "FinAidProfile", "ProfileYearBegin D" );
            //:SET CURSOR FIRST mEmploy.FinAidProfile
            RESULT = SetCursorFirstEntity( mEmploy, "FinAidProfile", "" );

            //:// Order Historical Contracts.
            //:OrderEntityForView( mEmploy, "HistoricalContract", "HistoricalContractGLFiscalYear.FiscalYear D EffectiveDate D" )
            OrderEntityForView( mEmploy, "HistoricalContract", "HistoricalContractGLFiscalYear.FiscalYear D EffectiveDate D" );
            //:SET CURSOR FIRST mEmploy.HistoricalContract 
            RESULT = SetCursorFirstEntity( mEmploy, "HistoricalContract", "" );
            RESULT = SetCursorNextEntity( mEmploy, "Employee", "" );
         } 


         //:END

         //:// Set up Callback and Emailback work attributes for a single update employee.
         //:nRC = GetViewByName( mUser, "mUser", mEmploy, zLEVEL_APPLICATION )
         nRC = GetViewByName( mUser, "mUser", mEmploy, zLEVEL_APPLICATION );
         //:IF nRC >= 0 
         if ( nRC >= 0 )
         { 
            //:// Set the work Callback Date, if a Callback entry exists for the current User.
            //:SET CURSOR FIRST mEmploy.CallbackUser WITHIN mEmploy.EmployeePerson 
            //:     WHERE mEmploy.CallbackUser.ID = mUser.User.ID 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                         GetIntegerFromAttribute( mi_lTempInteger_1, mUser, "User", "ID" );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mEmploy, "CallbackUser", "ID", lTempInteger_1, "EmployeePerson" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:mEmploy.Employee.wCallbackDate = mEmploy.Callback.Date 
               SetAttributeFromAttribute( mEmploy, "Employee", "wCallbackDate", mEmploy, "Callback", "Date" );
            } 

            //:END 

            //:// Set the work Emailback Date, if a Emailback entry exists for the current User.
            //:SET CURSOR FIRST mEmploy.EmailbackUser WITHIN mEmploy.EmployeePerson 
            //:     WHERE mEmploy.EmailbackUser.ID = mUser.User.ID 
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, mUser, "User", "ID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mEmploy, "EmailbackUser", "ID", lTempInteger_2, "EmployeePerson" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:mEmploy.Employee.wEmailbackDate = mEmploy.Emailback.Date 
               SetAttributeFromAttribute( mEmploy, "Employee", "wEmailbackDate", mEmploy, "Emailback", "Date" );
            } 

            //:END 
         } 

         //:END

         //:// Save the current Employee Status so we can check later to see if it has changed.
         //:mEmploy.Employee.wOriginalStatus = mEmploy.Employee.Status 
         SetAttributeFromAttribute( mEmploy, "Employee", "wOriginalStatus", mEmploy, "Employee", "Status" );
         break ;


      //:  /* end zOCE_ACTIVATE */
      //:OF   zOCE_ACTIVATE_EMPTY:
      case zOCE_ACTIVATE_EMPTY :
         break ;

      //:  /* end zOCE_ACTIVATE_EMPTY */
      //:OF   zOCE_COMMIT:
      case zOCE_COMMIT :

         //:// Maintain the Active relationship based on the Active Status of a YearlyContract entity.
         //:CreateViewFromView( mEmployT, mEmploy )
         CreateViewFromView( mEmployT, mEmploy );
         //:SET CURSOR FIRST mEmployT.HistoricalContract WHERE mEmployT.HistoricalContract.Status = "A"
         RESULT = SetCursorFirstEntityByString( mEmployT, "HistoricalContract", "Status", "A", "" );
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:// There is an active Contract, so make sure it is the active relationship.
            //:IF mEmployT.ActiveYearlyContract EXISTS
            lTempInteger_3 = CheckExistenceOfEntity( mEmployT, "ActiveYearlyContract" );
            if ( lTempInteger_3 == 0 )
            { 
               //:IF mEmployT.HistoricalContract.ID != mEmployT.ActiveYearlyContract.ID 
               if ( CompareAttributeToAttribute( mEmployT, "HistoricalContract", "ID", mEmployT, "ActiveYearlyContract", "ID" ) != 0 )
               { 
                  //:EXCLUDE mEmployT.ActiveYearlyContract 
                  RESULT = ExcludeEntity( mEmployT, "ActiveYearlyContract", zREPOS_AFTER );
                  //:INCLUDE mEmployT.ActiveYearlyContract FROM mEmployT.HistoricalContract
                  RESULT = IncludeSubobjectFromSubobject( mEmployT, "ActiveYearlyContract", mEmployT, "HistoricalContract", zPOS_AFTER );
               } 

               //:END
               //:ELSE 
            } 
            else
            { 
               //:INCLUDE mEmployT.ActiveYearlyContract FROM mEmployT.HistoricalContract 
               RESULT = IncludeSubobjectFromSubobject( mEmployT, "ActiveYearlyContract", mEmployT, "HistoricalContract", zPOS_AFTER );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:// There is no active Contract, so remove any relationship that exists.
            //:IF mEmployT.ActiveYearlyContract EXISTS
            lTempInteger_4 = CheckExistenceOfEntity( mEmployT, "ActiveYearlyContract" );
            if ( lTempInteger_4 == 0 )
            { 
               //:EXCLUDE mEmployT.ActiveYearlyContract  
               RESULT = ExcludeEntity( mEmployT, "ActiveYearlyContract", zREPOS_AFTER );
            } 

            //:END
         } 

         //:END

         //:// Make sure that AnnualSalaryAmount for Faculty members and Hourly employees is set.
         //:FOR EACH mEmployT.HistoricalContract 
         RESULT = SetCursorFirstEntity( mEmployT, "HistoricalContract", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mEmployT.HistoricalContract.EmployeeClassification = "F" OR 
            //:mEmployT.HistoricalContract.EmployeeClassification = "AF"
            if ( CompareAttributeToString( mEmployT, "HistoricalContract", "EmployeeClassification", "F" ) == 0 || CompareAttributeToString( mEmployT, "HistoricalContract", "EmployeeClassification", "AF" ) == 0 )
            { 

               //:IF mEmployT.HistoricalContract.FacultyOverrideSalaryAmount != ""
               if ( CompareAttributeToString( mEmployT, "HistoricalContract", "FacultyOverrideSalaryAmount", "" ) != 0 )
               { 
                  //:IF mEmployT.HistoricalContract.AnnualSalaryAmount != mEmployT.HistoricalContract.FacultyOverrideSalaryAmount
                  if ( CompareAttributeToAttribute( mEmployT, "HistoricalContract", "AnnualSalaryAmount", mEmployT, "HistoricalContract", "FacultyOverrideSalaryAmount" ) != 0 )
                  { 
                     //:mEmployT.HistoricalContract.AnnualSalaryAmount = mEmployT.HistoricalContract.FacultyOverrideSalaryAmount
                     SetAttributeFromAttribute( mEmployT, "HistoricalContract", "AnnualSalaryAmount", mEmployT, "HistoricalContract", "FacultyOverrideSalaryAmount" );
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:IF mEmployT.HistoricalContract.AnnualSalaryAmount != mEmployT.HistoricalContract.dFacultyComputedSalary
                  if ( CompareAttributeToAttribute( mEmployT, "HistoricalContract", "AnnualSalaryAmount", mEmployT, "HistoricalContract", "dFacultyComputedSalary" ) != 0 )
                  { 
                     //:mEmployT.HistoricalContract.AnnualSalaryAmount = mEmployT.HistoricalContract.dFacultyComputedSalary
                     SetAttributeFromAttribute( mEmployT, "HistoricalContract", "AnnualSalaryAmount", mEmployT, "HistoricalContract", "dFacultyComputedSalary" );
                  } 

                  //:END
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF mEmployT.HistoricalContract.EmployeeClassification = "H" 
               if ( CompareAttributeToString( mEmployT, "HistoricalContract", "EmployeeClassification", "H" ) == 0 )
               { 
                  //:IF mEmployT.HistoricalContract.AnnualSalaryAmount != mEmployT.HistoricalContract.dHourlyAnnualAmount
                  if ( CompareAttributeToAttribute( mEmployT, "HistoricalContract", "AnnualSalaryAmount", mEmployT, "HistoricalContract", "dHourlyAnnualAmount" ) != 0 )
                  { 
                     //:mEmployT.HistoricalContract.AnnualSalaryAmount = mEmployT.HistoricalContract.dHourlyAnnualAmount
                     SetAttributeFromAttribute( mEmployT, "HistoricalContract", "AnnualSalaryAmount", mEmployT, "HistoricalContract", "dHourlyAnnualAmount" );
                  } 

                  //:END
               } 

               //:END
            } 

            RESULT = SetCursorNextEntity( mEmployT, "HistoricalContract", "" );
            //:END
         } 

         //:END

         //:// Add Transaction History.
         //:IF mEmploy.Employee EXISTS
         lTempInteger_5 = CheckExistenceOfEntity( mEmploy, "Employee" );
         if ( lTempInteger_5 == 0 )
         { 
            //:GenerateTransactionEntry( mEmploy )
            {
             ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mEmploy );
             m_ZGLOBAL2_Operation.GenerateTransactionEntry( mEmploy );
             // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
            }
         } 

         //:END

         //:DropView( mEmployT )
         DropView( mEmployT );
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
//:dTotalSalaryAmount( VIEW mEmployOrig BASED ON LOD mEmploy,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   VIEW mEmploy BASED ON LOD mEmploy
public int 
omEmploy_dTotalSalaryAmount( View     mEmployOrig,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   zVIEW    mEmploy = new zVIEW( );
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:DECIMAL dTotalSalary
   double  dTotalSalary = 0.0;
   //:INTEGER lTotalSalary
   int      lTotalSalary = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
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


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView( mEmploy, mEmployOrig )
         CreateViewFromView( mEmploy, mEmployOrig );
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmployOrig );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "YearlyContract"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "YearlyContract", 1, 0, 33 ) == 0 )
         { 
            //:SET CURSOR FIRST mEmploy.HistoricalContract 
            //:        WHERE mEmploy.HistoricalContract.ID = mEmploy.YearlyContract.ID  
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mEmploy, "YearlyContract", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mEmploy, "HistoricalContract", "ID", lTempInteger_0, "" );
         } 

         //:END

         //:// Hourly Wage
         //:IF mEmploy.HistoricalContract.EmployeeClassification = "H"    // Hourly
         if ( CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "H" ) == 0 )
         { 
            //:// Hours per Week X Weeks per Year X Hourly Rate
            //:dTotalSalary = mEmploy.HistoricalContract.dHourlyAnnualAmount +
            //:            mEmploy.HistoricalContract.AdditionalSalaryAmount 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mEmploy, "HistoricalContract", "dHourlyAnnualAmount" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                         GetDecimalFromAttribute( md_dTempDecimal_1, mEmploy, "HistoricalContract", "AdditionalSalaryAmount" );
            dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
            dTotalSalary = dTempDecimal_0 + dTempDecimal_1;
         } 

         //:END

         //:// Annual Salary
         //:IF mEmploy.HistoricalContract.EmployeeClassification = "S" OR   // Salaried Staff
         //:mEmploy.HistoricalContract.EmployeeClassification = "AS" OR  // Administrative Staff
         //:mEmploy.HistoricalContract.EmployeeClassification = "A"      // Administrator
         if ( CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "S" ) == 0 || CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "AS" ) == 0 ||
              CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "A" ) == 0 )
         { 

            //:dTotalSalary = mEmploy.HistoricalContract.AnnualSalaryAmount +
            //:            mEmploy.HistoricalContract.AdditionalSalaryAmount
            {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                         GetDecimalFromAttribute( md_dTempDecimal_2, mEmploy, "HistoricalContract", "AnnualSalaryAmount" );
            dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
            {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                         GetDecimalFromAttribute( md_dTempDecimal_3, mEmploy, "HistoricalContract", "AdditionalSalaryAmount" );
            dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
            dTotalSalary = dTempDecimal_2 + dTempDecimal_3;
         } 

         //:END

         //:// Faculty
         //:IF mEmploy.HistoricalContract.EmployeeClassification = "F" OR   // Faculty
         //:mEmploy.HistoricalContract.EmployeeClassification = "AF"     // Administrative Faculty
         if ( CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "F" ) == 0 || CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "AF" ) == 0 )
         { 

            //:IF mEmploy.HistoricalContract.FacultyOverrideSalaryAmount = ""
            if ( CompareAttributeToString( mEmploy, "HistoricalContract", "FacultyOverrideSalaryAmount", "" ) == 0 )
            { 
               //:// For Faculty, the AnnualSalaryAmount is normally set on the commit of mEmploy from the derived attribute.
               //:// However, in some cases (ie., Generate Budget), it is not set and we need to use the derived value.
               //:IF mEmploy.HistoricalContract.AnnualSalaryAmount = 0
               if ( CompareAttributeToInteger( mEmploy, "HistoricalContract", "AnnualSalaryAmount", 0 ) == 0 )
               { 
                  //:dTotalSalary = mEmploy.HistoricalContract.dFacultyComputedSalary +
                  //:            mEmploy.HistoricalContract.AdditionalSalaryAmount +
                  //:            mEmploy.HistoricalContract.dFacultyAddMonthAmount
                  {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                                     GetDecimalFromAttribute( md_dTempDecimal_4, mEmploy, "HistoricalContract", "dFacultyComputedSalary" );
                  dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
                  {MutableDouble md_dTempDecimal_5 = new MutableDouble( dTempDecimal_5 );
                                     GetDecimalFromAttribute( md_dTempDecimal_5, mEmploy, "HistoricalContract", "AdditionalSalaryAmount" );
                  dTempDecimal_5 = md_dTempDecimal_5.doubleValue( );}
                  {MutableDouble md_dTempDecimal_6 = new MutableDouble( dTempDecimal_6 );
                                     GetDecimalFromAttribute( md_dTempDecimal_6, mEmploy, "HistoricalContract", "dFacultyAddMonthAmount" );
                  dTempDecimal_6 = md_dTempDecimal_6.doubleValue( );}
                  dTotalSalary = dTempDecimal_4 + dTempDecimal_5 + dTempDecimal_6;
                  //:ELSE
               } 
               else
               { 
                  //:dTotalSalary = mEmploy.HistoricalContract.AnnualSalaryAmount +
                  //:            mEmploy.HistoricalContract.AdditionalSalaryAmount +
                  //:            mEmploy.HistoricalContract.dFacultyAddMonthAmount 
                  {MutableDouble md_dTempDecimal_7 = new MutableDouble( dTempDecimal_7 );
                                     GetDecimalFromAttribute( md_dTempDecimal_7, mEmploy, "HistoricalContract", "AnnualSalaryAmount" );
                  dTempDecimal_7 = md_dTempDecimal_7.doubleValue( );}
                  {MutableDouble md_dTempDecimal_8 = new MutableDouble( dTempDecimal_8 );
                                     GetDecimalFromAttribute( md_dTempDecimal_8, mEmploy, "HistoricalContract", "AdditionalSalaryAmount" );
                  dTempDecimal_8 = md_dTempDecimal_8.doubleValue( );}
                  {MutableDouble md_dTempDecimal_9 = new MutableDouble( dTempDecimal_9 );
                                     GetDecimalFromAttribute( md_dTempDecimal_9, mEmploy, "HistoricalContract", "dFacultyAddMonthAmount" );
                  dTempDecimal_9 = md_dTempDecimal_9.doubleValue( );}
                  dTotalSalary = dTempDecimal_7 + dTempDecimal_8 + dTempDecimal_9;
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:dTotalSalary = mEmploy.HistoricalContract.FacultyOverrideSalaryAmount +
               //:            mEmploy.HistoricalContract.AdditionalSalaryAmount
               {MutableDouble md_dTempDecimal_10 = new MutableDouble( dTempDecimal_10 );
                               GetDecimalFromAttribute( md_dTempDecimal_10, mEmploy, "HistoricalContract", "FacultyOverrideSalaryAmount" );
               dTempDecimal_10 = md_dTempDecimal_10.doubleValue( );}
               {MutableDouble md_dTempDecimal_11 = new MutableDouble( dTempDecimal_11 );
                               GetDecimalFromAttribute( md_dTempDecimal_11, mEmploy, "HistoricalContract", "AdditionalSalaryAmount" );
               dTempDecimal_11 = md_dTempDecimal_11.doubleValue( );}
               dTotalSalary = dTempDecimal_10 + dTempDecimal_11;
            } 

            //:END
         } 

         //:END

         //:// Contractor Salary (which is simply the salary amount.
         //:IF mEmploy.HistoricalContract.EmployeeClassification = "C"      // Administrator
         if ( CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "C" ) == 0 )
         { 
            //:dTotalSalary = mEmploy.HistoricalContract.AnnualSalaryAmount
            {MutableDouble md_dTotalSalary = new MutableDouble( dTotalSalary );
                         GetDecimalFromAttribute( md_dTotalSalary, mEmploy, "HistoricalContract", "AnnualSalaryAmount" );
            dTotalSalary = md_dTotalSalary.doubleValue( );}
         } 

         //:END

         //:// Round Salary Amount up to the next whole dollar.
         //:// First convert Total Salary to a whole number.
         //:lTotalSalary = dTotalSalary
         lTotalSalary = (int) dTotalSalary;
         //:IF dTotalSalary > lTotalSalary
         if ( dTotalSalary > lTotalSalary )
         { 
            //:// Total Salary is not a whole number, so add a dollar to the whole dollar amount.
            //:lTotalSalary = lTotalSalary + 1
            lTotalSalary = lTotalSalary + 1;
            //:dTotalSalary = lTotalSalary
            dTotalSalary = lTotalSalary;
         } 

         //:END

         //:StoreValueInRecord ( mEmploy,
         //:                  InternalEntityStructure, InternalAttribStructure, dTotalSalary, 0 )
         StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, dTotalSalary, 0 );
         //:DropView( mEmploy )
         DropView( mEmploy );
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
//:dHourlyAnnualAmount( VIEW mEmployOrig BASED ON LOD mEmploy,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   VIEW mEmploy BASED ON LOD mEmploy
public int 
omEmploy_dHourlyAnnualAmount( View     mEmployOrig,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   zVIEW    mEmploy = new zVIEW( );
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:DECIMAL dAnnualSalary
   double  dAnnualSalary = 0.0;
   //:INTEGER lAnnualSalary
   int      lAnnualSalary = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_1 = 0;
   double  dTempDecimal_1 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView( mEmploy, mEmployOrig )
         CreateViewFromView( mEmploy, mEmployOrig );
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmployOrig );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "YearlyContract"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "YearlyContract", 1, 0, 33 ) == 0 )
         { 
            //:SET CURSOR FIRST mEmploy.HistoricalContract 
            //:        WHERE mEmploy.HistoricalContract.ID = mEmploy.YearlyContract.ID  
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mEmploy, "YearlyContract", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mEmploy, "HistoricalContract", "ID", lTempInteger_0, "" );
         } 

         //:END

         //:// Hourly Wage
         //:IF mEmploy.HistoricalContract.EmployeeClassification = "H"    // Hourly
         if ( CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "H" ) == 0 )
         { 
            //:// Hours per Week X Weeks per Year X Hourly Rate
            //:dAnnualSalary = ( mEmploy.HistoricalContract.HourlyHoursPerWeek *
            //:              mEmploy.HistoricalContract.HourlyWeeksPerYear *
            //:              mEmploy.HistoricalContract.HourlyRate )
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mEmploy, "HistoricalContract", "HourlyHoursPerWeek" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                         GetIntegerFromAttribute( mi_lTempInteger_1, mEmploy, "HistoricalContract", "HourlyWeeksPerYear" );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );}
            {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                         GetDecimalFromAttribute( md_dTempDecimal_1, mEmploy, "HistoricalContract", "HourlyRate" );
            dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
            dAnnualSalary = ( dTempDecimal_0 * lTempInteger_1 * dTempDecimal_1 );
            //:// Always round up. We'll do this by adding 99 cents and then removing the cents.
            //:dAnnualSalary = dAnnualSalary + .99
            dAnnualSalary = dAnnualSalary + .99;
            //:lAnnualSalary = dAnnualSalary
            lAnnualSalary = (int) dAnnualSalary;
            //:dAnnualSalary = lAnnualSalary
            dAnnualSalary = lAnnualSalary;
            //:StoreValueInRecord ( mEmploy,
            //:                  InternalEntityStructure, InternalAttribStructure, dAnnualSalary, 0 )
            StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, dAnnualSalary, 0 );
            //:ELSE
         } 
         else
         { 
            //:StoreStringInRecord ( mEmploy,
            //:                   InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, "" );
         } 

         //:END

         //:DropView( mEmploy )
         DropView( mEmploy );
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
//:dFacultyComputedSalary( VIEW mEmployOrig BASED ON LOD mEmploy,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   VIEW mEmploy BASED ON LOD mEmploy
public int 
omEmploy_dFacultyComputedSalary( View     mEmployOrig,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   zVIEW    mEmploy = new zVIEW( );
   //:VIEW mStdSalLST  BASED ON LOD mStdSal
   zVIEW    mStdSalLST = new zVIEW( );
   //:VIEW mFacCompLST BASED ON LOD mFacComp
   zVIEW    mFacCompLST = new zVIEW( );
   //:STRING ( 1 )   szFacultyRank
   String   szFacultyRank = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:DECIMAL        dSalaryAmount
   double  dSalaryAmount = 0.0;
   //:INTEGER        lSalaryAmount
   int      lSalaryAmount = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Compute faculty salary using the Faculty Salary Base and the Faculty Compensation Schedule.

         //:CreateViewFromView( mEmploy, mEmployOrig )
         CreateViewFromView( mEmploy, mEmployOrig );
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmployOrig );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "YearlyContract"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "YearlyContract", 1, 0, 33 ) == 0 )
         { 
            //:SET CURSOR FIRST mEmploy.HistoricalContract WHERE mEmploy.HistoricalContract.ID = mEmploy.YearlyContract.ID  
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mEmploy, "YearlyContract", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mEmploy, "HistoricalContract", "ID", lTempInteger_0, "" );
         } 

         //:END
         //:dSalaryAmount = 0
         dSalaryAmount = 0;
         //:IF ( mEmploy.HistoricalContract.EmployeeClassification = "F" OR mEmploy.HistoricalContract.EmployeeClassification = "AF" ) AND
         //:mEmploy.HistoricalContract.FacultyDegree        != "" AND 
         //:mEmploy.HistoricalContract.FacultyYearsOfService > 0 AND 
         //:mEmploy.HistoricalContract.FacultyRank          != ""
         if ( ( CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "F" ) == 0 || CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "AF" ) == 0 ) &&
              CompareAttributeToString( mEmploy, "HistoricalContract", "FacultyDegree", "" ) != 0 && CompareAttributeToInteger( mEmploy, "HistoricalContract", "FacultyYearsOfService", 0 ) > 0 &&
              CompareAttributeToString( mEmploy, "HistoricalContract", "FacultyRank", "" ) != 0 )
         { 

            //:GET VIEW mStdSalLST NAMED "mStdSalLST"
            RESULT = GetViewByName( mStdSalLST, "mStdSalLST", mEmployOrig, zLEVEL_TASK );
            //:IF RESULT >= 0
            if ( RESULT >= 0 )
            { 
               //:SET CURSOR FIRST mStdSalLST.StandardSalaryData WHERE mStdSalLST.StandardSalaryData.ID = mEmploy.HistContractStandardSalaryData.ID 
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                               GetIntegerFromAttribute( mi_lTempInteger_1, mEmploy, "HistContractStandardSalaryData", "ID" );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mStdSalLST, "StandardSalaryData", "ID", lTempInteger_1, "" );
               //:IF RESULT >= zCURSOR_SET 
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:GET VIEW mFacCompLST NAMED "mFacCompLST"
                  RESULT = GetViewByName( mFacCompLST, "mFacCompLST", mEmployOrig, zLEVEL_TASK );
                  //:IF RESULT >= 0
                  if ( RESULT >= 0 )
                  { 
                     //:szFacultyRank = mEmploy.HistoricalContract.FacultyRank
                     {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                     StringBuilder sb_szFacultyRank;
                     if ( szFacultyRank == null )
                        sb_szFacultyRank = new StringBuilder( 32 );
                     else
                        sb_szFacultyRank = new StringBuilder( szFacultyRank );
                                           GetVariableFromAttribute( sb_szFacultyRank, mi_lTempInteger_2, 'S', 2, mEmploy, "HistoricalContract", "FacultyRank", "", 0 );
                     lTempInteger_2 = mi_lTempInteger_2.intValue( );
                     szFacultyRank = sb_szFacultyRank.toString( );}
                     //:IF szFacultyRank = "I"
                     if ( ZeidonStringCompare( szFacultyRank, 1, 0, "I", 1, 0, 2 ) == 0 )
                     { 
                        //:szFacultyRank = "A"   // We treat an Instructor the same as an Assistant Professor.
                         {StringBuilder sb_szFacultyRank;
                        if ( szFacultyRank == null )
                           sb_szFacultyRank = new StringBuilder( 32 );
                        else
                           sb_szFacultyRank = new StringBuilder( szFacultyRank );
                                                ZeidonStringCopy( sb_szFacultyRank, 1, 0, "A", 1, 0, 2 );
                        szFacultyRank = sb_szFacultyRank.toString( );}
                     } 

                     //:END
                     //:SET CURSOR FIRST mFacCompLST.StandardFacultyCompensationTable 
                     //:        WHERE mFacCompLST.StandardFacultyCompensationTable.FacultyDegree         = mEmploy.HistoricalContract.FacultyDegree 
                     //:          AND mFacCompLST.StandardFacultyCompensationTable.FacultyYearsOfService = mEmploy.HistoricalContract.FacultyYearsOfService 
                     //:          AND mFacCompLST.StandardFacultyCompensationTable.FacultyPayType        = szFacultyRank
                     RESULT = SetCursorFirstEntity( mFacCompLST, "StandardFacultyCompensationTable", "" );
                     if ( RESULT > zCURSOR_UNCHANGED )
                     { 
                        while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mFacCompLST, "StandardFacultyCompensationTable", "FacultyDegree", mEmploy, "HistoricalContract", "FacultyDegree" ) != 0 ||
                        CompareAttributeToAttribute( mFacCompLST, "StandardFacultyCompensationTable", "FacultyYearsOfService", mEmploy, "HistoricalContract", "FacultyYearsOfService" ) != 0 ||
                        CompareAttributeToString( mFacCompLST, "StandardFacultyCompensationTable", "FacultyPayType", szFacultyRank ) != 0 ) )
                        { 
                           RESULT = SetCursorNextEntity( mFacCompLST, "StandardFacultyCompensationTable", "" );
                        } 

                     } 

                     //:IF RESULT >= zCURSOR_SET
                     if ( RESULT >= zCURSOR_SET )
                     { 
                        //:dSalaryAmount = mStdSalLST.StandardSalaryData.FacultyBaseSalary * mFacCompLST.StandardFacultyCompensationTable.SalaryRatio
                        {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_0, mStdSalLST, "StandardSalaryData", "FacultyBaseSalary" );
                        dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                        {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                                                 GetDecimalFromAttribute( md_dTempDecimal_1, mFacCompLST, "StandardFacultyCompensationTable", "SalaryRatio" );
                        dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
                        dSalaryAmount = dTempDecimal_0 * dTempDecimal_1;
                        //:// Always round up. We'll do this by adding 99 cents and then removing the cents.
                        //:dSalaryAmount = dSalaryAmount + .99
                        dSalaryAmount = dSalaryAmount + .99;
                        //:lSalaryAmount = dSalaryAmount
                        lSalaryAmount = (int) dSalaryAmount;
                        //:dSalaryAmount = lSalaryAmount
                        dSalaryAmount = lSalaryAmount;
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:IF dSalaryAmount = 0
         if ( dSalaryAmount == 0 )
         { 
            //:StoreStringInRecord ( mEmploy,
            //:                   InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, "" );
            //:ELSE
         } 
         else
         { 
            //:StoreValueInRecord ( mEmploy,
            //:                  InternalEntityStructure, InternalAttribStructure, dSalaryAmount, 0 )
            StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, dSalaryAmount, 0 );
         } 

         //:END
         //:DropView( mEmploy )
         DropView( mEmploy );
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
//:dFacultyAddMonthAmount( VIEW mEmployOrig BASED ON LOD mEmploy,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   VIEW mEmploy  BASED ON LOD mEmploy
public int 
omEmploy_dFacultyAddMonthAmount( View     mEmployOrig,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   zVIEW    mEmploy = new zVIEW( );
   //:DECIMAL dAdditionMonthsAmount
   double  dAdditionMonthsAmount = 0.0;
   //:DECIMAL dAnnualSalaryAmount
   double  dAnnualSalaryAmount = 0.0;
   //:INTEGER lAdditionMonthsAmount
   int      lAdditionMonthsAmount = 0;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView( mEmploy, mEmployOrig )
         CreateViewFromView( mEmploy, mEmployOrig );
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmployOrig );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "YearlyContract"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "YearlyContract", 1, 0, 33 ) == 0 )
         { 
            //:SET CURSOR FIRST mEmploy.HistoricalContract WHERE mEmploy.HistoricalContract.ID = mEmploy.YearlyContract.ID  
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mEmploy, "YearlyContract", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mEmploy, "HistoricalContract", "ID", lTempInteger_0, "" );
         } 

         //:END
         //:// On 3/4/2008, DonC changed the computation of dAdditionMonthsAmount to use the derived Faculty salary instead
         //:// of the actual salary at the request of Fran.
         //:dAnnualSalaryAmount = mEmploy.HistoricalContract.dFacultyComputedSalary
         {MutableDouble md_dAnnualSalaryAmount = new MutableDouble( dAnnualSalaryAmount );
                   GetDecimalFromAttribute( md_dAnnualSalaryAmount, mEmploy, "HistoricalContract", "dFacultyComputedSalary" );
         dAnnualSalaryAmount = md_dAnnualSalaryAmount.doubleValue( );}
         //:dAdditionMonthsAmount = dAnnualSalaryAmount * mEmploy.HistoricalContract.FacultyAdditionalMonths / 9
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                   GetIntegerFromAttribute( mi_lTempInteger_1, mEmploy, "HistoricalContract", "FacultyAdditionalMonths" );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
         dAdditionMonthsAmount = dAnnualSalaryAmount * lTempInteger_1 / 9;

         //:IF dAdditionMonthsAmount = 0
         if ( dAdditionMonthsAmount == 0 )
         { 
            //:StoreStringInRecord ( mEmploy,
            //:                   InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, "" );
            //:ELSE
         } 
         else
         { 
            //:// Always round up. We'll do this by adding 99 cents and then removing the cents.
            //:dAdditionMonthsAmount = dAdditionMonthsAmount + .99
            dAdditionMonthsAmount = dAdditionMonthsAmount + .99;
            //:lAdditionMonthsAmount = dAdditionMonthsAmount
            lAdditionMonthsAmount = (int) dAdditionMonthsAmount;
            //:dAdditionMonthsAmount = lAdditionMonthsAmount
            dAdditionMonthsAmount = lAdditionMonthsAmount;
            //:StoreValueInRecord ( mEmploy,
            //:                  InternalEntityStructure, InternalAttribStructure, dAdditionMonthsAmount, 0 )
            StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, dAdditionMonthsAmount, 0 );
         } 

         //:END
         //:DropView( mEmploy )
         DropView( mEmploy );
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
//:dEmployeeType( VIEW mEmploy BASED ON LOD mEmploy,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   STRING ( 50 ) szEmployeeType
public int 
omEmploy_dEmployeeType( View     mEmploy,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   String   szEmployeeType = null;
   //:STRING ( 50 ) szEmployeeTypeN
   String   szEmployeeTypeN = null;
   //:SHORT         StringLength
   int      StringLength = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mEmploy.Faculty EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mEmploy, "Faculty" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szEmployeeType = szEmployeeType + "Faculty, "
             {StringBuilder sb_szEmployeeType;
            if ( szEmployeeType == null )
               sb_szEmployeeType = new StringBuilder( 32 );
            else
               sb_szEmployeeType = new StringBuilder( szEmployeeType );
                        ZeidonStringConcat( sb_szEmployeeType, 1, 0, "Faculty, ", 1, 0, 51 );
            szEmployeeType = sb_szEmployeeType.toString( );}
         } 

         //:END
         //:IF mEmploy.Staff EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mEmploy, "Staff" );
         if ( lTempInteger_1 == 0 )
         { 
            //:szEmployeeType = szEmployeeType + "Staff, "
             {StringBuilder sb_szEmployeeType;
            if ( szEmployeeType == null )
               sb_szEmployeeType = new StringBuilder( 32 );
            else
               sb_szEmployeeType = new StringBuilder( szEmployeeType );
                        ZeidonStringConcat( sb_szEmployeeType, 1, 0, "Staff, ", 1, 0, 51 );
            szEmployeeType = sb_szEmployeeType.toString( );}
         } 

         //:END
         //:IF mEmploy.Student EXISTS
         lTempInteger_2 = CheckExistenceOfEntity( mEmploy, "Student" );
         if ( lTempInteger_2 == 0 )
         { 
            //:IF mEmploy.Student.Status = "A"
            if ( CompareAttributeToString( mEmploy, "Student", "Status", "A" ) == 0 )
            { 
               //:szEmployeeType = szEmployeeType + "Student, "
                {StringBuilder sb_szEmployeeType;
               if ( szEmployeeType == null )
                  sb_szEmployeeType = new StringBuilder( 32 );
               else
                  sb_szEmployeeType = new StringBuilder( szEmployeeType );
                              ZeidonStringConcat( sb_szEmployeeType, 1, 0, "Student, ", 1, 0, 51 );
               szEmployeeType = sb_szEmployeeType.toString( );}
            } 

            //:END
         } 

         //:END
         //:StringLength = zGetStringLen( szEmployeeType )
         StringLength = zGetStringLen( szEmployeeType );
         //:StringLength = StringLength - 2
         StringLength = StringLength - 2;
         //:szEmployeeTypeN = szEmployeeType[1:StringLength]
          {StringBuilder sb_szEmployeeTypeN;
         if ( szEmployeeTypeN == null )
            sb_szEmployeeTypeN = new StringBuilder( 32 );
         else
            sb_szEmployeeTypeN = new StringBuilder( szEmployeeTypeN );
                  ZeidonStringCopy( sb_szEmployeeTypeN, 1, 0, szEmployeeType, 1, StringLength, 51 );
         szEmployeeTypeN = sb_szEmployeeTypeN.toString( );}
         //:StoreStringInRecord ( mEmploy,
         //:                   InternalEntityStructure, InternalAttribStructure, szEmployeeTypeN )
         StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, szEmployeeTypeN );
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
//:dTotalWorkStudy( VIEW mEmploy BASED ON LOD mEmploy,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )

//:   VIEW mEmployT BASED ON LOD mEmploy
public int 
omEmploy_dTotalWorkStudy( View     mEmploy,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   zVIEW    mEmployT = new zVIEW( );
   //:DECIMAL dTotal
   double  dTotal = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:CreateViewFromView( mEmployT, mEmploy )
         CreateViewFromView( mEmployT, mEmploy );
         //:FOR EACH mEmployT.FA_WorkStudyAllocation
         RESULT = SetCursorFirstEntity( mEmployT, "FA_WorkStudyAllocation", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:dTotal = dTotal + mEmployT.FA_WorkStudyAllocation.Amount 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mEmployT, "FA_WorkStudyAllocation", "Amount" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            dTotal = dTotal + dTempDecimal_0;
            RESULT = SetCursorNextEntity( mEmployT, "FA_WorkStudyAllocation", "" );
         } 

         //:END
         //:DropView( mEmployT )
         DropView( mEmployT );
         //:StoreValueInRecord( mEmploy,InternalEntityStructure,
         //:                    InternalAttribStructure, dTotal, 0 )
         StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, dTotal, 0 );
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
//:dWorkStudyEligible( VIEW mEmploy BASED ON LOD mEmploy,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   STRING ( 10 ) szEligibilityText
public int 
omEmploy_dWorkStudyEligible( View     mEmploy,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szEligibilityText = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Set the elibility text for Work Study.
         //:// We start by assuming the Student is eligible
         //:szEligibilityText = "Eligible"
          {StringBuilder sb_szEligibilityText;
         if ( szEligibilityText == null )
            sb_szEligibilityText = new StringBuilder( 32 );
         else
            sb_szEligibilityText = new StringBuilder( szEligibilityText );
                  ZeidonStringCopy( sb_szEligibilityText, 1, 0, "Eligible", 1, 0, 11 );
         szEligibilityText = sb_szEligibilityText.toString( );}
         //:IF mEmploy.FAISIR DOES NOT EXIST
         lTempInteger_0 = CheckExistenceOfEntity( mEmploy, "FAISIR" );
         if ( lTempInteger_0 != 0 )
         { 
            //:szEligibilityText = "Ineligible"
             {StringBuilder sb_szEligibilityText;
            if ( szEligibilityText == null )
               sb_szEligibilityText = new StringBuilder( 32 );
            else
               sb_szEligibilityText = new StringBuilder( szEligibilityText );
                        ZeidonStringCopy( sb_szEligibilityText, 1, 0, "Ineligible", 1, 0, 11 );
            szEligibilityText = sb_szEligibilityText.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mEmploy.FAISIR.TransactionNumber = ""
            if ( CompareAttributeToString( mEmploy, "FAISIR", "TransactionNumber", "" ) == 0 )
            { 
               //:szEligibilityText = "Ineligible"
                {StringBuilder sb_szEligibilityText;
               if ( szEligibilityText == null )
                  sb_szEligibilityText = new StringBuilder( 32 );
               else
                  sb_szEligibilityText = new StringBuilder( szEligibilityText );
                              ZeidonStringCopy( sb_szEligibilityText, 1, 0, "Ineligible", 1, 0, 11 );
               szEligibilityText = sb_szEligibilityText.toString( );}
               //:ELSE
            } 
            else
            { 
               //:IF mEmploy.FAISIREligibility EXISTS
               lTempInteger_1 = CheckExistenceOfEntity( mEmploy, "FAISIREligibility" );
               if ( lTempInteger_1 == 0 )
               { 
                  //:IF mEmploy.FAISIREligibility.VerificationSelectedCode = "Y"
                  if ( CompareAttributeToString( mEmploy, "FAISIREligibility", "VerificationSelectedCode", "Y" ) == 0 )
                  { 
                     //:IF mEmploy.FinAidRequirementMet DOES NOT EXIST
                     lTempInteger_2 = CheckExistenceOfEntity( mEmploy, "FinAidRequirementMet" );
                     if ( lTempInteger_2 != 0 )
                     { 
                        //:szEligibilityText = "Ineligible"
                         {StringBuilder sb_szEligibilityText;
                        if ( szEligibilityText == null )
                           sb_szEligibilityText = new StringBuilder( 32 );
                        else
                           sb_szEligibilityText = new StringBuilder( szEligibilityText );
                                                ZeidonStringCopy( sb_szEligibilityText, 1, 0, "Ineligible", 1, 0, 11 );
                        szEligibilityText = sb_szEligibilityText.toString( );}
                     } 

                     //:END
                  } 

                  //:END
                  //:IF mEmploy.FAISIREligibility.SARC = "Y" AND
                  //:mEmploy.FinAidProfile.CCodeResolved = ""
                  if ( CompareAttributeToString( mEmploy, "FAISIREligibility", "SARC", "Y" ) == 0 && CompareAttributeToString( mEmploy, "FinAidProfile", "CCodeResolved", "" ) == 0 )
                  { 

                     //:szEligibilityText = "Ineligible"
                      {StringBuilder sb_szEligibilityText;
                     if ( szEligibilityText == null )
                        sb_szEligibilityText = new StringBuilder( 32 );
                     else
                        sb_szEligibilityText = new StringBuilder( szEligibilityText );
                                          ZeidonStringCopy( sb_szEligibilityText, 1, 0, "Ineligible", 1, 0, 11 );
                     szEligibilityText = sb_szEligibilityText.toString( );}
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:szEligibilityText = "Ineligible"
                   {StringBuilder sb_szEligibilityText;
                  if ( szEligibilityText == null )
                     sb_szEligibilityText = new StringBuilder( 32 );
                  else
                     sb_szEligibilityText = new StringBuilder( szEligibilityText );
                                    ZeidonStringCopy( sb_szEligibilityText, 1, 0, "Ineligible", 1, 0, 11 );
                  szEligibilityText = sb_szEligibilityText.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:StoreStringInRecord ( mEmploy,
         //:                   InternalEntityStructure, InternalAttribStructure, szEligibilityText )
         StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, szEligibilityText );
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
//:dWorkStudyUnmedNeed( VIEW mEmploy BASED ON LOD mEmploy,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   DECIMAL dDisplayUnmetNeed
public int 
omEmploy_dWorkStudyUnmedNeed( View     mEmploy,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   double  dDisplayUnmetNeed = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// The Unmet Need value to be displayed for Work Study is $250.00 less than it actually is.
         //:// This was requested 8/5/08 by Aadit.
         //:dDisplayUnmetNeed = mEmploy.FinAidProfile.TotalUnmetNeed 
         {MutableDouble md_dDisplayUnmetNeed = new MutableDouble( dDisplayUnmetNeed );
                   GetDecimalFromAttribute( md_dDisplayUnmetNeed, mEmploy, "FinAidProfile", "TotalUnmetNeed" );
         dDisplayUnmetNeed = md_dDisplayUnmetNeed.doubleValue( );}
         //:dDisplayUnmetNeed = dDisplayUnmetNeed - 250
         dDisplayUnmetNeed = dDisplayUnmetNeed - 250;
         //:IF dDisplayUnmetNeed < 0
         if ( dDisplayUnmetNeed < 0 )
         { 
            //:dDisplayUnmetNeed = 0
            dDisplayUnmetNeed = 0;
         } 

         //:END

         //:StoreValueInRecord ( mEmploy,
         //:                  InternalEntityStructure, InternalAttribStructure, dDisplayUnmetNeed, 0 )
         StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, dDisplayUnmetNeed, 0 );
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
//:dDisabilityInsBenefit( VIEW mEmployOrig BASED ON LOD mEmploy,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   VIEW mEmploy    BASED ON LOD mEmploy
public int 
omEmploy_dDisabilityInsBenefit( View     mEmployOrig,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   zVIEW    mEmploy = new zVIEW( );
   //:VIEW mStdSalLST BASED ON LOD mStdSal
   zVIEW    mStdSalLST = new zVIEW( );
   //:DECIMAL BenefitAmount
   double  BenefitAmount = 0.0;
   //:DECIMAL SalaryAmount
   double  SalaryAmount = 0.0;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 1 )  szSkipFlag
   String   szSkipFlag = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GET VIEW mStdSalLST NAMED "mStdSalLST"
         RESULT = GetViewByName( mStdSalLST, "mStdSalLST", mEmployOrig, zLEVEL_TASK );
         //:IF RESULT < 0
         if ( RESULT < 0 )
         { 
            //:ACTIVATE mStdSalLST RootOnlyMultiple
            RESULT = ActivateObjectInstance( mStdSalLST, "mStdSal", mEmployOrig, 0, zACTIVATE_ROOTONLY_MULTIPLE );
            //:NAME VIEW mStdSalLST "mStdSalLST"
            SetNameForView( mStdSalLST, "mStdSalLST", null, zLEVEL_TASK );
         } 

         //:END

         //:// If the attribute is on the root then the request is for Active Contract. Otherwise the request is for HistoricalContract.
         //:CreateViewFromView( mEmploy, mEmployOrig )
         CreateViewFromView( mEmploy, mEmployOrig );
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmployOrig );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "Employee"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "Employee", 1, 0, 33 ) == 0 )
         { 
            //:SET CURSOR FIRST mEmploy.HistoricalContract WHERE mEmploy.HistoricalContract.Status = "A"
            RESULT = SetCursorFirstEntityByString( mEmploy, "HistoricalContract", "Status", "A", "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:szSkipFlag = "Y"
                {StringBuilder sb_szSkipFlag;
               if ( szSkipFlag == null )
                  sb_szSkipFlag = new StringBuilder( 32 );
               else
                  sb_szSkipFlag = new StringBuilder( szSkipFlag );
                              ZeidonStringCopy( sb_szSkipFlag, 1, 0, "Y", 1, 0, 2 );
               szSkipFlag = sb_szSkipFlag.toString( );}
               //:ELSE
            } 
            else
            { 
               //:// "Contracted Employee" contracts don't have any benefits.
               //:IF mEmploy.HistoricalContract.EmployeeClassification = "C"
               if ( CompareAttributeToString( mEmploy, "HistoricalContract", "EmployeeClassification", "C" ) == 0 )
               { 
                  //:szSkipFlag = "Y"
                   {StringBuilder sb_szSkipFlag;
                  if ( szSkipFlag == null )
                     sb_szSkipFlag = new StringBuilder( 32 );
                  else
                     sb_szSkipFlag = new StringBuilder( szSkipFlag );
                                    ZeidonStringCopy( sb_szSkipFlag, 1, 0, "Y", 1, 0, 2 );
                  szSkipFlag = sb_szSkipFlag.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:IF szSkipFlag = ""
         if ( ZeidonStringCompare( szSkipFlag, 1, 0, "", 1, 0, 2 ) == 0 )
         { 
            //:SET CURSOR FIRST mStdSalLST.StandardSalaryData WHERE mStdSalLST.StandardSalaryData.ID = mEmploy.HistContractStandardSalaryData.ID 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mEmploy, "HistContractStandardSalaryData", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mStdSalLST, "StandardSalaryData", "ID", lTempInteger_0, "" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:SalaryAmount = mEmploy.HistoricalContract.dTotalSalaryAmount
               {MutableDouble md_SalaryAmount = new MutableDouble( SalaryAmount );
                               GetDecimalFromAttribute( md_SalaryAmount, mEmploy, "HistoricalContract", "dTotalSalaryAmount" );
               SalaryAmount = md_SalaryAmount.doubleValue( );}
               //:BenefitAmount = ( SalaryAmount * mStdSalLST.StandardSalaryData.DisabilityInsuranceRate * 12 ) / 1000 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mStdSalLST, "StandardSalaryData", "DisabilityInsuranceRate" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               BenefitAmount = ( SalaryAmount * dTempDecimal_0 * 12 ) / 1000;
               //:StoreValueInRecord( mEmploy,InternalEntityStructure, InternalAttribStructure, BenefitAmount, 0 )
               StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, BenefitAmount, 0 );
               //:ELSE
            } 
            else
            { 
               //:StoreStringInRecord ( mEmploy,
               //:                   InternalEntityStructure, InternalAttribStructure, "" )
               StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, "" );
            } 

            //:END
         } 

         //:END
         //:DropView( mEmploy )
         DropView( mEmploy );
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
//:dTotalCollegeContrib( VIEW mEmploy BASED ON LOD mEmploy,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )

//:   DECIMAL TotalAmount
public int 
omEmploy_dTotalCollegeContrib( View     mEmploy,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   double  TotalAmount = 0.0;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
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


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// If the attribute is on the root then the request is for Active Contract. Otherwise the request is for HistoricalContract.
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmploy );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "Employee"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "Employee", 1, 0, 33 ) == 0 )
         { 
            //:TotalAmount = mEmploy.Employee.dFICA_OASDI_Benefit +
            //:           mEmploy.Employee.dFICA_MedicareBenefit +
            //:           mEmploy.Employee.dMedicalPlanCostBenefit +
            //:           mEmploy.Employee.dLifeAndDisabilityBenefit +
            //:           mEmploy.Employee.dTIAA_RetirementBenefit +
            //:           mEmploy.Employee.dWorkersCompensationBenefit 
            {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                         GetDecimalFromAttribute( md_dTempDecimal_0, mEmploy, "Employee", "dFICA_OASDI_Benefit" );
            dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
            {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                         GetDecimalFromAttribute( md_dTempDecimal_1, mEmploy, "Employee", "dFICA_MedicareBenefit" );
            dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
            {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                         GetDecimalFromAttribute( md_dTempDecimal_2, mEmploy, "Employee", "dMedicalPlanCostBenefit" );
            dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
            {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                         GetDecimalFromAttribute( md_dTempDecimal_3, mEmploy, "Employee", "dLifeAndDisabilityBenefit" );
            dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
            {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                         GetDecimalFromAttribute( md_dTempDecimal_4, mEmploy, "Employee", "dTIAA_RetirementBenefit" );
            dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
            {MutableDouble md_dTempDecimal_5 = new MutableDouble( dTempDecimal_5 );
                         GetDecimalFromAttribute( md_dTempDecimal_5, mEmploy, "Employee", "dWorkersCompensationBenefit" );
            dTempDecimal_5 = md_dTempDecimal_5.doubleValue( );}
            TotalAmount = dTempDecimal_0 + dTempDecimal_1 + dTempDecimal_2 + dTempDecimal_3 + dTempDecimal_4 + dTempDecimal_5;
            //:ELSE
         } 
         else
         { 
            //:TotalAmount = mEmploy.HistoricalContract.dFICA_OASDI_Benefit +
            //:           mEmploy.HistoricalContract.dFICA_MedicareBenefit +
            //:           mEmploy.HistoricalContract.dMedicalPlanCostBenefit +
            //:           mEmploy.HistoricalContract.dLifeAndDisabilityBenefit +
            //:           mEmploy.HistoricalContract.dTIAA_RetirementBenefit +
            //:           mEmploy.HistoricalContract.dWorkersCompensationBenefit 
            {MutableDouble md_dTempDecimal_6 = new MutableDouble( dTempDecimal_6 );
                         GetDecimalFromAttribute( md_dTempDecimal_6, mEmploy, "HistoricalContract", "dFICA_OASDI_Benefit" );
            dTempDecimal_6 = md_dTempDecimal_6.doubleValue( );}
            {MutableDouble md_dTempDecimal_7 = new MutableDouble( dTempDecimal_7 );
                         GetDecimalFromAttribute( md_dTempDecimal_7, mEmploy, "HistoricalContract", "dFICA_MedicareBenefit" );
            dTempDecimal_7 = md_dTempDecimal_7.doubleValue( );}
            {MutableDouble md_dTempDecimal_8 = new MutableDouble( dTempDecimal_8 );
                         GetDecimalFromAttribute( md_dTempDecimal_8, mEmploy, "HistoricalContract", "dMedicalPlanCostBenefit" );
            dTempDecimal_8 = md_dTempDecimal_8.doubleValue( );}
            {MutableDouble md_dTempDecimal_9 = new MutableDouble( dTempDecimal_9 );
                         GetDecimalFromAttribute( md_dTempDecimal_9, mEmploy, "HistoricalContract", "dLifeAndDisabilityBenefit" );
            dTempDecimal_9 = md_dTempDecimal_9.doubleValue( );}
            {MutableDouble md_dTempDecimal_10 = new MutableDouble( dTempDecimal_10 );
                         GetDecimalFromAttribute( md_dTempDecimal_10, mEmploy, "HistoricalContract", "dTIAA_RetirementBenefit" );
            dTempDecimal_10 = md_dTempDecimal_10.doubleValue( );}
            {MutableDouble md_dTempDecimal_11 = new MutableDouble( dTempDecimal_11 );
                         GetDecimalFromAttribute( md_dTempDecimal_11, mEmploy, "HistoricalContract", "dWorkersCompensationBenefit" );
            dTempDecimal_11 = md_dTempDecimal_11.doubleValue( );}
            TotalAmount = dTempDecimal_6 + dTempDecimal_7 + dTempDecimal_8 + dTempDecimal_9 + dTempDecimal_10 + dTempDecimal_11;
         } 

         //:END


         //:StoreValueInRecord( mEmploy,InternalEntityStructure, InternalAttribStructure, TotalAmount, 0 )
         StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, TotalAmount, 0 );
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
//:dTotalCostOfContract( VIEW mEmploy BASED ON LOD mEmploy,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )

//:   DECIMAL TotalAmount
public int 
omEmploy_dTotalCostOfContract( View     mEmploy,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   double  TotalAmount = 0.0;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   int      lTempInteger_0 = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// If the attribute is on the root then the request is for Active Contract. Otherwise the request is for HistoricalContract.
         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmploy );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "Employee"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "Employee", 1, 0, 33 ) == 0 )
         { 
            //:IF mEmploy.YearlyContract EXISTS
            lTempInteger_0 = CheckExistenceOfEntity( mEmploy, "YearlyContract" );
            if ( lTempInteger_0 == 0 )
            { 
               //:TotalAmount = mEmploy.YearlyContract.dTotalSalaryAmount +
               //:           mEmploy.Employee.dTotalCollegeContributions 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mEmploy, "YearlyContract", "dTotalSalaryAmount" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                               GetDecimalFromAttribute( md_dTempDecimal_1, mEmploy, "Employee", "dTotalCollegeContributions" );
               dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
               TotalAmount = dTempDecimal_0 + dTempDecimal_1;
               //:StoreValueInRecord( mEmploy,InternalEntityStructure, InternalAttribStructure, TotalAmount, 0 )
               StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, TotalAmount, 0 );
               //:ELSE
            } 
            else
            { 
               //:StoreStringInRecord ( mEmploy,
               //:                      InternalEntityStructure, InternalAttribStructure, "" )
               StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, "" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:TotalAmount = mEmploy.HistoricalContract.dTotalSalaryAmount +
            //:           mEmploy.HistoricalContract.dTotalCollegeContributions 
            {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                         GetDecimalFromAttribute( md_dTempDecimal_2, mEmploy, "HistoricalContract", "dTotalSalaryAmount" );
            dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
            {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                         GetDecimalFromAttribute( md_dTempDecimal_3, mEmploy, "HistoricalContract", "dTotalCollegeContributions" );
            dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
            TotalAmount = dTempDecimal_2 + dTempDecimal_3;
            //:StoreValueInRecord( mEmploy,InternalEntityStructure, InternalAttribStructure, TotalAmount, 0 )
            StoreValueInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, TotalAmount, 0 );
         } 

         //:END
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
//:dSalaryDataName( VIEW mEmploy BASED ON LOD mEmploy,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )

//:   STRING ( 20 ) szDisplayName
public int 
omEmploy_dSalaryDataName( View     mEmploy,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   String   szDisplayName = null;
   //:STRING ( 10 ) szDate
   String   szDate = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmploy );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "HistContractStandardSalaryData" 
         if ( ZeidonStringCompare( szEntityName, 1, 0, "HistContractStandardSalaryData", 1, 0, 33 ) == 0 )
         { 
            //:GetStringFromAttributeByContext( szDate, mEmploy, "HistContractStandardSalaryData", "EffectiveBeginDate", "", 10 )
            {StringBuilder sb_szDate;
            if ( szDate == null )
               sb_szDate = new StringBuilder( 32 );
            else
               sb_szDate = new StringBuilder( szDate );
                         GetStringFromAttributeByContext( sb_szDate, mEmploy, "HistContractStandardSalaryData", "EffectiveBeginDate", "", 10 );
            szDate = sb_szDate.toString( );}
            //:szDisplayName = mEmploy.HistContractStandardSalaryData.Year + " - " + szDate
            {StringBuilder sb_szDisplayName;
            if ( szDisplayName == null )
               sb_szDisplayName = new StringBuilder( 32 );
            else
               sb_szDisplayName = new StringBuilder( szDisplayName );
                         GetStringFromAttribute( sb_szDisplayName, mEmploy, "HistContractStandardSalaryData", "Year" );
            szDisplayName = sb_szDisplayName.toString( );}
             {StringBuilder sb_szDisplayName;
            if ( szDisplayName == null )
               sb_szDisplayName = new StringBuilder( 32 );
            else
               sb_szDisplayName = new StringBuilder( szDisplayName );
                        ZeidonStringConcat( sb_szDisplayName, 1, 0, " - ", 1, 0, 21 );
            szDisplayName = sb_szDisplayName.toString( );}
             {StringBuilder sb_szDisplayName;
            if ( szDisplayName == null )
               sb_szDisplayName = new StringBuilder( 32 );
            else
               sb_szDisplayName = new StringBuilder( szDisplayName );
                        ZeidonStringConcat( sb_szDisplayName, 1, 0, szDate, 1, 0, 21 );
            szDisplayName = sb_szDisplayName.toString( );}
            //:ELSE
         } 
         else
         { 
            //:GetStringFromAttributeByContext( szDate, mEmploy, "YearlyContractStandardSalaryData", "EffectiveBeginDate", "", 10 )
            {StringBuilder sb_szDate;
            if ( szDate == null )
               sb_szDate = new StringBuilder( 32 );
            else
               sb_szDate = new StringBuilder( szDate );
                         GetStringFromAttributeByContext( sb_szDate, mEmploy, "YearlyContractStandardSalaryData", "EffectiveBeginDate", "", 10 );
            szDate = sb_szDate.toString( );}
            //:szDisplayName = mEmploy.YearlyContractStandardSalaryData.Year + " - " + szDate
            {StringBuilder sb_szDisplayName;
            if ( szDisplayName == null )
               sb_szDisplayName = new StringBuilder( 32 );
            else
               sb_szDisplayName = new StringBuilder( szDisplayName );
                         GetStringFromAttribute( sb_szDisplayName, mEmploy, "YearlyContractStandardSalaryData", "Year" );
            szDisplayName = sb_szDisplayName.toString( );}
             {StringBuilder sb_szDisplayName;
            if ( szDisplayName == null )
               sb_szDisplayName = new StringBuilder( 32 );
            else
               sb_szDisplayName = new StringBuilder( szDisplayName );
                        ZeidonStringConcat( sb_szDisplayName, 1, 0, " - ", 1, 0, 21 );
            szDisplayName = sb_szDisplayName.toString( );}
             {StringBuilder sb_szDisplayName;
            if ( szDisplayName == null )
               sb_szDisplayName = new StringBuilder( 32 );
            else
               sb_szDisplayName = new StringBuilder( szDisplayName );
                        ZeidonStringConcat( sb_szDisplayName, 1, 0, szDate, 1, 0, 21 );
            szDisplayName = sb_szDisplayName.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mEmploy,
         //:                   InternalEntityStructure, InternalAttribStructure, szDisplayName )
         StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, szDisplayName );
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
//:dPositionOfficeName( VIEW mEmploy BASED ON LOD mEmploy,
//:                     STRING ( 32 ) InternalEntityStructure,
//:                     STRING ( 32 ) InternalAttribStructure,
//:                     SHORT GetOrSetFlag )

//:   STRING ( 50 ) szDisplayName
public int 
omEmploy_dPositionOfficeName( View     mEmploy,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szDisplayName = null;
   //:STRING ( 50 ) szBuildingName
   String   szBuildingName = null;
   //:STRING ( 50 ) szRoomNumber
   String   szRoomNumber = null;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetEntityNameFromStructure( InternalEntityStructure, szEntityName )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mEmploy );
          {StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   m_ZGLOBAL1_Operation.GetEntityNameFromStructure( InternalEntityStructure, sb_szEntityName );
         szEntityName = sb_szEntityName.toString( );}
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF szEntityName = "YearlyOffice" 
         if ( ZeidonStringCompare( szEntityName, 1, 0, "YearlyOffice", 1, 0, 33 ) == 0 )
         { 
            //:GetStringFromAttributeByContext( szBuildingName, mEmploy, "YearlyOfficeBuilding", "Name", "", 50 )
            {StringBuilder sb_szBuildingName;
            if ( szBuildingName == null )
               sb_szBuildingName = new StringBuilder( 32 );
            else
               sb_szBuildingName = new StringBuilder( szBuildingName );
                         GetStringFromAttributeByContext( sb_szBuildingName, mEmploy, "YearlyOfficeBuilding", "Name", "", 50 );
            szBuildingName = sb_szBuildingName.toString( );}
            //:GetStringFromAttributeByContext( szRoomNumber,   mEmploy, "YearlyOffice", "Number", "", 50 )
            {StringBuilder sb_szRoomNumber;
            if ( szRoomNumber == null )
               sb_szRoomNumber = new StringBuilder( 32 );
            else
               sb_szRoomNumber = new StringBuilder( szRoomNumber );
                         GetStringFromAttributeByContext( sb_szRoomNumber, mEmploy, "YearlyOffice", "Number", "", 50 );
            szRoomNumber = sb_szRoomNumber.toString( );}
            //:szDisplayName = szBuildingName + " - " + szRoomNumber
             {StringBuilder sb_szDisplayName;
            if ( szDisplayName == null )
               sb_szDisplayName = new StringBuilder( 32 );
            else
               sb_szDisplayName = new StringBuilder( szDisplayName );
                        ZeidonStringCopy( sb_szDisplayName, 1, 0, szBuildingName, 1, 0, 51 );
            szDisplayName = sb_szDisplayName.toString( );}
             {StringBuilder sb_szDisplayName;
            if ( szDisplayName == null )
               sb_szDisplayName = new StringBuilder( 32 );
            else
               sb_szDisplayName = new StringBuilder( szDisplayName );
                        ZeidonStringConcat( sb_szDisplayName, 1, 0, " - ", 1, 0, 51 );
            szDisplayName = sb_szDisplayName.toString( );}
             {StringBuilder sb_szDisplayName;
            if ( szDisplayName == null )
               sb_szDisplayName = new StringBuilder( 32 );
            else
               sb_szDisplayName = new StringBuilder( szDisplayName );
                        ZeidonStringConcat( sb_szDisplayName, 1, 0, szRoomNumber, 1, 0, 51 );
            szDisplayName = sb_szDisplayName.toString( );}
            //:ELSE
         } 
         else
         { 
            //:GetStringFromAttributeByContext( szBuildingName, mEmploy, "HistoricalOfficeBuilding", "Name", "", 50 )
            {StringBuilder sb_szBuildingName;
            if ( szBuildingName == null )
               sb_szBuildingName = new StringBuilder( 32 );
            else
               sb_szBuildingName = new StringBuilder( szBuildingName );
                         GetStringFromAttributeByContext( sb_szBuildingName, mEmploy, "HistoricalOfficeBuilding", "Name", "", 50 );
            szBuildingName = sb_szBuildingName.toString( );}
            //:GetStringFromAttributeByContext( szRoomNumber,   mEmploy, "HistoricalOffice", "Number", "", 50 )
            {StringBuilder sb_szRoomNumber;
            if ( szRoomNumber == null )
               sb_szRoomNumber = new StringBuilder( 32 );
            else
               sb_szRoomNumber = new StringBuilder( szRoomNumber );
                         GetStringFromAttributeByContext( sb_szRoomNumber, mEmploy, "HistoricalOffice", "Number", "", 50 );
            szRoomNumber = sb_szRoomNumber.toString( );}
            //:szDisplayName = szBuildingName + " - " + szRoomNumber
             {StringBuilder sb_szDisplayName;
            if ( szDisplayName == null )
               sb_szDisplayName = new StringBuilder( 32 );
            else
               sb_szDisplayName = new StringBuilder( szDisplayName );
                        ZeidonStringCopy( sb_szDisplayName, 1, 0, szBuildingName, 1, 0, 51 );
            szDisplayName = sb_szDisplayName.toString( );}
             {StringBuilder sb_szDisplayName;
            if ( szDisplayName == null )
               sb_szDisplayName = new StringBuilder( 32 );
            else
               sb_szDisplayName = new StringBuilder( szDisplayName );
                        ZeidonStringConcat( sb_szDisplayName, 1, 0, " - ", 1, 0, 51 );
            szDisplayName = sb_szDisplayName.toString( );}
             {StringBuilder sb_szDisplayName;
            if ( szDisplayName == null )
               sb_szDisplayName = new StringBuilder( 32 );
            else
               sb_szDisplayName = new StringBuilder( szDisplayName );
                        ZeidonStringConcat( sb_szDisplayName, 1, 0, szRoomNumber, 1, 0, 51 );
            szDisplayName = sb_szDisplayName.toString( );}
         } 

         //:END

         //:StoreStringInRecord ( mEmploy,
         //:                   InternalEntityStructure, InternalAttribStructure, szDisplayName )
         StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, szDisplayName );
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


//:TRANSFORMATION OPERATION
//:ValidateDistribValues( VIEW mEmploy BASED ON LOD mEmploy )
//:   VIEW mEmployT BASED ON LOD mEmploy
public int 
omEmploy_ValidateDistribValues( View     mEmploy )
{
   zVIEW    mEmployT = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;
   //:DECIMAL TotalPercent
   double  TotalPercent = 0.0;
   //:DECIMAL TotalAmount
   double  TotalAmount = 0.0;
   //:STRING ( 100 ) Msg
   String   Msg = null;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;
   double  dTempDecimal_1 = 0.0;
   double  dTempDecimal_2 = 0.0;
   double  dTempDecimal_3 = 0.0;
   double  dTempDecimal_4 = 0.0;
   double  dTempDecimal_5 = 0.0;


   //:// Validate that the distribution values of each Type add up to 100% when Percents are used and that they
   //:// equal the specified amount in the Contract when Amounts are used.
   //:// Also don't allow a mixture of Percents and Amounts.
   //:// When Amounts are specified, compute the Percents.
   //:// Also, make sure that a General distribution always exists and that Additional Salary and Addition Months
   //:// are specified when the corresponding Amount attributes are not zero.

   //:// General distribution values.
   //:TotalPercent = 0
   TotalPercent = 0;
   //:TotalAmount  = 0
   TotalAmount = 0;
   //:FOR EACH mEmploy.HistDeptContractDistribution WHERE mEmploy.HistDeptContractDistribution.DistributionType = "G"
   RESULT = SetCursorFirstEntityByString( mEmploy, "HistDeptContractDistribution", "DistributionType", "G", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEmploy.HistDeptContractDistribution.DistributionAmount = ""
      if ( CompareAttributeToString( mEmploy, "HistDeptContractDistribution", "DistributionAmount", "" ) == 0 )
      { 
         //:TotalPercent = TotalPercent + mEmploy.HistDeptContractDistribution.DistributionPercent 
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, mEmploy, "HistDeptContractDistribution", "DistributionPercent" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         TotalPercent = TotalPercent + dTempDecimal_0;
         //:ELSE
      } 
      else
      { 
         //:TotalAmount = TotalAmount + mEmploy.HistDeptContractDistribution.DistributionAmount 
         {MutableDouble md_dTempDecimal_1 = new MutableDouble( dTempDecimal_1 );
                   GetDecimalFromAttribute( md_dTempDecimal_1, mEmploy, "HistDeptContractDistribution", "DistributionAmount" );
         dTempDecimal_1 = md_dTempDecimal_1.doubleValue( );}
         TotalAmount = TotalAmount + dTempDecimal_1;
      } 

      RESULT = SetCursorNextEntityByString( mEmploy, "HistDeptContractDistribution", "DistributionType", "G", "" );
      //:END
   } 

   //:END
   //:IF TotalPercent > 0 AND TotalAmount > 0
   if ( TotalPercent > 0 && TotalAmount > 0 )
   { 
      //:MessageSend( mEmploy, "", "Validate Distributions", 
      //:             "A mixture of Percents and Amounts exists for General Distribution entries.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mEmploy, "", "Validate Distributions", "A mixture of Percents and Amounts exists for General Distribution entries.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   //:IF TotalAmount > 0
   if ( TotalAmount > 0 )
   { 
      //:// Distribution is defined in Amounts.
      //:IF TotalAmount != mEmploy.HistoricalContract.AnnualSalaryAmount
      if ( CompareAttributeToDecimal( mEmploy, "HistoricalContract", "AnnualSalaryAmount", TotalAmount ) != 0 )
      { 
         //:MessageSend( mEmploy, "", "Validate Distributions", 
         //:             "General Distribution Amounts do not add up to 'Total Salary Amount'.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mEmploy, "", "Validate Distributions", "General Distribution Amounts do not add up to 'Total Salary Amount'.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// Distribution is defined in Percents.
      //:IF TotalPercent != 0 AND TotalPercent != 100
      if ( TotalPercent != 0 && TotalPercent != 100 )
      { 
         //:MessageSend( mEmploy, "", "Validate Distributions", 
         //:             "General Distribution Percents do not add up to 100%.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mEmploy, "", "Validate Distributions", "General Distribution Percents do not add up to 100%.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
   } 

   //:END

   //:// Additional Salary distribution values.
   //:TotalPercent = 0
   TotalPercent = 0;
   //:TotalAmount  = 0
   TotalAmount = 0;
   //:FOR EACH mEmploy.HistDeptContractDistribution WHERE mEmploy.HistDeptContractDistribution.DistributionType = "SA"
   RESULT = SetCursorFirstEntityByString( mEmploy, "HistDeptContractDistribution", "DistributionType", "SA", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEmploy.HistDeptContractDistribution.DistributionAmount = ""
      if ( CompareAttributeToString( mEmploy, "HistDeptContractDistribution", "DistributionAmount", "" ) == 0 )
      { 
         //:TotalPercent = TotalPercent + mEmploy.HistDeptContractDistribution.DistributionPercent 
         {MutableDouble md_dTempDecimal_2 = new MutableDouble( dTempDecimal_2 );
                   GetDecimalFromAttribute( md_dTempDecimal_2, mEmploy, "HistDeptContractDistribution", "DistributionPercent" );
         dTempDecimal_2 = md_dTempDecimal_2.doubleValue( );}
         TotalPercent = TotalPercent + dTempDecimal_2;
         //:ELSE
      } 
      else
      { 
         //:TotalAmount = TotalAmount + mEmploy.HistDeptContractDistribution.DistributionAmount 
         {MutableDouble md_dTempDecimal_3 = new MutableDouble( dTempDecimal_3 );
                   GetDecimalFromAttribute( md_dTempDecimal_3, mEmploy, "HistDeptContractDistribution", "DistributionAmount" );
         dTempDecimal_3 = md_dTempDecimal_3.doubleValue( );}
         TotalAmount = TotalAmount + dTempDecimal_3;
      } 

      RESULT = SetCursorNextEntityByString( mEmploy, "HistDeptContractDistribution", "DistributionType", "SA", "" );
      //:END
   } 

   //:END
   //:IF TotalPercent > 0 AND TotalAmount > 0
   if ( TotalPercent > 0 && TotalAmount > 0 )
   { 
      //:MessageSend( mEmploy, "", "Validate Distributions", 
      //:             "A mixture of Percents and Amounts exists for Additional Salary Distribution entries.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mEmploy, "", "Validate Distributions", "A mixture of Percents and Amounts exists for Additional Salary Distribution entries.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   //:IF TotalAmount > 0
   if ( TotalAmount > 0 )
   { 
      //:// Distribution is defined in Amounts.
      //:IF TotalAmount != mEmploy.HistoricalContract.AdditionalSalaryAmount
      if ( CompareAttributeToDecimal( mEmploy, "HistoricalContract", "AdditionalSalaryAmount", TotalAmount ) != 0 )
      { 
         //:MessageSend( mEmploy, "", "Validate Distributions", 
         //:             "Additional Salary Distribution Amounts do not add up to 'Additional Months Amount'.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mEmploy, "", "Validate Distributions", "Additional Salary Distribution Amounts do not add up to 'Additional Months Amount'.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// Distribution is defined in Percents.
      //:IF TotalPercent != 0 AND TotalPercent != 100
      if ( TotalPercent != 0 && TotalPercent != 100 )
      { 
         //:MessageSend( mEmploy, "", "Validate Distributions", 
         //:             "Additional Salary Distribution Percents do not add up to 100%.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mEmploy, "", "Validate Distributions", "Additional Salary Distribution Percents do not add up to 100%.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
   } 

   //:END

   //:// Additional Salary Month distribution values.
   //:TotalPercent = 0
   TotalPercent = 0;
   //:TotalAmount  = 0
   TotalAmount = 0;
   //:FOR EACH mEmploy.HistDeptContractDistribution WHERE mEmploy.HistDeptContractDistribution.DistributionType = "SM"
   RESULT = SetCursorFirstEntityByString( mEmploy, "HistDeptContractDistribution", "DistributionType", "SM", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEmploy.HistDeptContractDistribution.DistributionAmount = ""
      if ( CompareAttributeToString( mEmploy, "HistDeptContractDistribution", "DistributionAmount", "" ) == 0 )
      { 
         //:TotalPercent = TotalPercent + mEmploy.HistDeptContractDistribution.DistributionPercent 
         {MutableDouble md_dTempDecimal_4 = new MutableDouble( dTempDecimal_4 );
                   GetDecimalFromAttribute( md_dTempDecimal_4, mEmploy, "HistDeptContractDistribution", "DistributionPercent" );
         dTempDecimal_4 = md_dTempDecimal_4.doubleValue( );}
         TotalPercent = TotalPercent + dTempDecimal_4;
         //:ELSE
      } 
      else
      { 
         //:TotalAmount = TotalAmount + mEmploy.HistDeptContractDistribution.DistributionAmount 
         {MutableDouble md_dTempDecimal_5 = new MutableDouble( dTempDecimal_5 );
                   GetDecimalFromAttribute( md_dTempDecimal_5, mEmploy, "HistDeptContractDistribution", "DistributionAmount" );
         dTempDecimal_5 = md_dTempDecimal_5.doubleValue( );}
         TotalAmount = TotalAmount + dTempDecimal_5;
      } 

      RESULT = SetCursorNextEntityByString( mEmploy, "HistDeptContractDistribution", "DistributionType", "SM", "" );
      //:END
   } 

   //:END
   //:IF TotalPercent > 0 AND TotalAmount > 0
   if ( TotalPercent > 0 && TotalAmount > 0 )
   { 
      //:MessageSend( mEmploy, "", "Validate Distributions", 
      //:             "A mixture of Percents and Amounts exists for Salary Months Distribution entries.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( mEmploy, "", "Validate Distributions", "A mixture of Percents and Amounts exists for Salary Months Distribution entries.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   //:IF TotalAmount > 0
   if ( TotalAmount > 0 )
   { 
      //:// Distribution is defined in Amounts.
      //:IF TotalAmount != mEmploy.HistoricalContract.dFacultyAddMonthAmount
      if ( CompareAttributeToDecimal( mEmploy, "HistoricalContract", "dFacultyAddMonthAmount", TotalAmount ) != 0 )
      { 
         //:MessageSend( mEmploy, "", "Validate Distributions", 
         //:             "Salary Months Distribution Amounts do not add up to 'Additional Months Amount'.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mEmploy, "", "Validate Distributions", "Salary Months Distribution Amounts do not add up to 'Additional Months Amount'.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// Distribution is defined in Percents.
      //:IF TotalPercent != 0 AND TotalPercent != 100
      if ( TotalPercent != 0 && TotalPercent != 100 )
      { 
         //:MessageSend( mEmploy, "", "Validate Distributions", 
         //:             "Salary Months Distribution Percents do not add up to 100%.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mEmploy, "", "Validate Distributions", "Salary Months Distribution Percents do not add up to 100%.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
   } 

   //:END

   //:// Make sure distribution entries exist as required.
   //:IF mEmploy.HistoricalContract.dTotalSalaryAmount > 0
   if ( CompareAttributeToInteger( mEmploy, "HistoricalContract", "dTotalSalaryAmount", 0 ) > 0 )
   { 
      //:SET CURSOR FIRST mEmploy.HistDeptContractDistribution WHERE mEmploy.HistDeptContractDistribution.DistributionType = "G"
      RESULT = SetCursorFirstEntityByString( mEmploy, "HistDeptContractDistribution", "DistributionType", "G", "" );
      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:MessageSend( mEmploy, "", "Validate Distributions", 
         //:             "General Distribution Entries are always required but are currently not specified.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mEmploy, "", "Validate Distributions", "General Distribution Entries are always required but are currently not specified.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
   } 

   //:END
   //:IF mEmploy.HistoricalContract.AdditionalSalaryAmount > 0
   if ( CompareAttributeToInteger( mEmploy, "HistoricalContract", "AdditionalSalaryAmount", 0 ) > 0 )
   { 
      //:SET CURSOR FIRST mEmploy.HistDeptContractDistribution WHERE mEmploy.HistDeptContractDistribution.DistributionType = "SA"
      RESULT = SetCursorFirstEntityByString( mEmploy, "HistDeptContractDistribution", "DistributionType", "SA", "" );
      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:MessageSend( mEmploy, "", "Validate Distributions", 
         //:             "Because an 'Additional Salary' amount is specified, a corresponding 'Salary Distribution' entry must also be specified.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( mEmploy, "", "Validate Distributions", "Because an 'Additional Salary' amount is specified, a corresponding 'Salary Distribution' entry must also be specified.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
   } 

   //:END
   //:IF mEmploy.HistoricalContract.FacultyAdditionalMonths > 0
   if ( CompareAttributeToInteger( mEmploy, "HistoricalContract", "FacultyAdditionalMonths", 0 ) > 0 )
   { 
      //:SET CURSOR FIRST mEmploy.HistDeptContractDistribution WHERE mEmploy.HistDeptContractDistribution.DistributionType = "SM"
      RESULT = SetCursorFirstEntityByString( mEmploy, "HistDeptContractDistribution", "DistributionType", "SM", "" );
      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:MessageSend( mEmploy, "", "Validate Distributions", 
            //:          "Because an 'Additional Salary Months' value is specified, a corresponding 'Salary Distribution' entry must also be specified.",
            //:          zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( mEmploy, "", "Validate Distributions", "Because an 'Additional Salary Months' value is specified, a corresponding 'Salary Distribution' entry must also be specified.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END  
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:ENTITY CONSTRAINT OPERATION
//:EntityConstraint( VIEW mEmploy BASED ON LOD mEmploy,
//:                  STRING ( 32 ) NameOfEntity,
//:                  SHORT Event,
//:                  SHORT State )
//:   
//:   STRING ( 10 ) szExternalString
public int 
omEmploy_EntityConstraint( View     mEmploy,
                           String   NameOfEntity,
                           Integer   Event,
                           Integer   State )
{
   String   szExternalString = null;


   //:CASE Event
   switch( Event )
   { 
      //:OF   zECE_ACCEPT:
      case zECE_ACCEPT :
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


//:DERIVED ATTRIBUTE OPERATION
//:dSOC_CodeInternalValue( VIEW mEmploy BASED ON LOD mEmploy,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   STRING ( 7 ) szSOC_CODE
public int 
omEmploy_dSOC_CodeInternalValue( View     mEmploy,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   String   szSOC_CODE = null;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Get the Internal value of SOC_Code
         //:szSOC_CODE = mEmploy.YearlyContract.SOC_Code 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szSOC_CODE;
         if ( szSOC_CODE == null )
            sb_szSOC_CODE = new StringBuilder( 32 );
         else
            sb_szSOC_CODE = new StringBuilder( szSOC_CODE );
                   GetVariableFromAttribute( sb_szSOC_CODE, mi_lTempInteger_0, 'S', 8, mEmploy, "YearlyContract", "SOC_Code", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szSOC_CODE = sb_szSOC_CODE.toString( );}

         //:StoreStringInRecord ( mEmploy,
         //:                   InternalEntityStructure, InternalAttribStructure, szSOC_CODE )
         StoreStringInRecord( mEmploy, InternalEntityStructure, InternalAttribStructure, szSOC_CODE );
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


private int 
omEmploy_fnLocalBuildQual_0( View     vSubtask,
                             zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MedicalCode" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MedicalCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "FunctionalAreaFlag" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 



}
