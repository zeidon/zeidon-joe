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
 * @author QuinSoft
 *
 */

public class sHost_Object extends VmlObjectOperations
{
   public sHost_Object( View view )
   {
      super( view );
   }


//:TRANSFORMATION OPERATION
//:ActivateHostObject( VIEW sHost BASED ON LOD sHost,
//:                    VIEW zAnyView )
//:   VIEW mUser BASED ON LOD mUser
public int 
osHost_ActivateHostObject( zVIEW    sHost,
                           View     zAnyView )
{
   zVIEW    mUser = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      RESULT = 0;


   //:// ACTIVATE sHost
   //:ActivateObjectInstance( sHost, "sHost", zAnyView, 0,
   //:                        zSINGLE + zLEVEL_APPLICATION )
   ActivateObjectInstance( sHost, "sHost", zAnyView, null, zSINGLE + zLEVEL_APPLICATION );
   //:IF sHost.Host DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( sHost, "Host" );
   if ( lTempInteger_0 != 0 )
   { 
      //:CREATE ENTITY sHost.Host
      RESULT = CreateEntity( sHost, "Host", zPOS_AFTER );
      //:sHost.Host.HostName  = "QuinSoft"
      SetAttributeFromString( sHost, "Host", "HostName", "QuinSoft" );
      //:COMMIT sHost
      RESULT = CommitObjectInstance( sHost );
      //:RETURN 0
      if(8==8)return( 0 );
   } 

   //:END
   //:CREATE ENTITY sHost.Currency 
   RESULT = CreateEntity( sHost, "Currency", zPOS_AFTER );
   //:sHost.Currency.CurrencySymbol = "$"
   SetAttributeFromString( sHost, "Currency", "CurrencySymbol", "$" );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dAddressLabelFull( VIEW sHost BASED ON LOD sHost,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )
//:   SHORT nRC
public int 
osHost_dAddressLabelFull( View     sHost,
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

         //:nRC = dAdressLabelFull( sHost /* vAnyObject */,
         //:   InternalEntityStructure /* szInternalEntityStructure */,
         //:   InternalAttribStructure /* szInternalAttribStructure */,
         //:   GetOrSetFlag /* nGetOrSetFlag */ )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( sHost );
          nRC = m_ZGLOBAL1_Operation.dAdressLabelFull( sHost, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
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


   //:DERIVED ATTRIBUTE OPERATION
   //:dBudgetVarCC_Pct( VIEW sHost BASED ON LOD sHost,
   //:               STRING ( 32 ) InternalEntityStructure,
   //:               STRING ( 32 ) InternalAttribStructure,
   //:               SHORT GetOrSetFlag )
   //:               
   //:DECIMAL dPct                  
public int 
osHost_dBudgetVarCC_Pct( View     sHost,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   double  dPct = 0.0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:dPct = sHost.Host.BudgetVarianceCC_Percent * 100
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, sHost, "Host", "BudgetVarianceCC_Percent" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         dPct = dTempDecimal_0 * 100;

         //:StoreValueInRecord ( sHost,
         //:                  InternalEntityStructure, InternalAttribStructure, dPct, 0 )
         StoreValueInRecord( sHost, InternalEntityStructure, InternalAttribStructure, dPct, 0 );
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
//:GetFiscalYearForDate( VIEW sHost BASED ON LOD sHost,
//:                      STRING ( 32 ) szDateOut,
//:                      STRING ( 32 ) szDate )
//:                      
//:   INTEGER nFiscalYearMonth
public int 
osHost_GetFiscalYearForDate( View     sHost,
                             StringBuilder   szDateOut,
                             String   szDate )
{
   int      nFiscalYearMonth = 0;
   //:INTEGER nFiscalYearDay
   int      nFiscalYearDay = 0;
   //:INTEGER nCurrentMonth
   int      nCurrentMonth = 0;
   //:INTEGER nCurrentDay
   int      nCurrentDay = 0;
   //:INTEGER nCurrentYear
   int      nCurrentYear = 0;
   //:INTEGER nNextYear
   int      nNextYear = 0;
   //:STRING ( 32 ) szDateTime
   String   szDateTime = null;
   //:STRING ( 2 )  szCurrentMonth
   String   szCurrentMonth = null;
   //:STRING ( 2 )  szCurrentDay
   String   szCurrentDay = null;
   //:STRING ( 4 )  szCurrentYear
   String   szCurrentYear = null;


   //:szDateTime = szDate
    {StringBuilder sb_szDateTime;
   if ( szDateTime == null )
      sb_szDateTime = new StringBuilder( 32 );
   else
      sb_szDateTime = new StringBuilder( szDateTime );
      ZeidonStringCopy( sb_szDateTime, 1, 0, szDate, 1, 0, 33 );
   szDateTime = sb_szDateTime.toString( );}
   //:// Start by getting the current Month, Year and the FiscalYearMonth from mHostO
   //:nFiscalYearMonth = sHost.AccountingProfile.FiscalYearEndMonth 
   {MutableInt mi_nFiscalYearMonth = new MutableInt( nFiscalYearMonth );
       GetIntegerFromAttribute( mi_nFiscalYearMonth, sHost, "AccountingProfile", "FiscalYearEndMonth" );
   nFiscalYearMonth = mi_nFiscalYearMonth.intValue( );}
   //:nFiscalYearDay = sHost.AccountingProfile.FiscalYearEndDay 
   {MutableInt mi_nFiscalYearDay = new MutableInt( nFiscalYearDay );
       GetIntegerFromAttribute( mi_nFiscalYearDay, sHost, "AccountingProfile", "FiscalYearEndDay" );
   nFiscalYearDay = mi_nFiscalYearDay.intValue( );}
   //:// Get the Year
   //:ZeidonStringCopy( szCurrentYear, 1, 4, szDateTime, 1, 4, 4 )
   {StringBuilder sb_szCurrentYear;
   if ( szCurrentYear == null )
      sb_szCurrentYear = new StringBuilder( 32 );
   else
      sb_szCurrentYear = new StringBuilder( szCurrentYear );
       ZeidonStringCopy( sb_szCurrentYear, 1, 4, szDateTime, 1, 4, 4 );
   szCurrentYear = sb_szCurrentYear.toString( );}
   //:nCurrentYear  = zStringToInteger( szCurrentYear  )
   nCurrentYear = zStringToInteger( szCurrentYear );
   //://nCurrentYear = StrToInt( szCurrentYear )
   //:nNextYear = nCurrentYear + 1
   nNextYear = nCurrentYear + 1;
   //:// Get the Month
   //:ZeidonStringCopy( szCurrentMonth, 1, 2, szDateTime, 5, 2, 2 )
   {StringBuilder sb_szCurrentMonth;
   if ( szCurrentMonth == null )
      sb_szCurrentMonth = new StringBuilder( 32 );
   else
      sb_szCurrentMonth = new StringBuilder( szCurrentMonth );
       ZeidonStringCopy( sb_szCurrentMonth, 1, 2, szDateTime, 5, 2, 2 );
   szCurrentMonth = sb_szCurrentMonth.toString( );}
   //:nCurrentMonth = zStringToInteger( szCurrentMonth )
   nCurrentMonth = zStringToInteger( szCurrentMonth );
   //:// nCurrentMonth = StrToInt( szCurrentMonth )
   //:// Get the Day         
   //:ZeidonStringCopy( szCurrentDay, 1, 2, szDateTime, 7, 2, 2 )
   {StringBuilder sb_szCurrentDay;
   if ( szCurrentDay == null )
      sb_szCurrentDay = new StringBuilder( 32 );
   else
      sb_szCurrentDay = new StringBuilder( szCurrentDay );
       ZeidonStringCopy( sb_szCurrentDay, 1, 2, szDateTime, 7, 2, 2 );
   szCurrentDay = sb_szCurrentDay.toString( );}
   //:nCurrentDay = zStringToInteger( szCurrentDay )
   nCurrentDay = zStringToInteger( szCurrentDay );
   //://nCurrentDay = StrToInt( szCurrentDay )
   //:IF nCurrentMonth < nFiscalYearMonth 
   if ( nCurrentMonth < nFiscalYearMonth )
   { 
      //://TraceLineI("nCurrentMonth < nFiscalYearMonth", nCurrentMonth )
      //:zIntegerToString( szDateOut, 4, nCurrentYear )
      zIntegerToString( szDateOut, 4, nCurrentYear );
      //:RETURN nCurrentYear 
      if(8==8)return( nCurrentYear );
      //:ELSE 
   } 
   else
   { 
      //:IF nCurrentMonth > nFiscalYearMonth 
      if ( nCurrentMonth > nFiscalYearMonth )
      { 
         //://TraceLineI("nCurrentMonth > nFiscalYearMonth ", nCurrentMonth )
         //:zIntegerToString( szDateOut, 4, nNextYear )
         zIntegerToString( szDateOut, 4, nNextYear );
         //:RETURN nNextYear 
         if(8==8)return( nNextYear );
         //:ELSE 
      } 
      else
      { 
         //:IF nCurrentDay <= nFiscalYearDay 
         if ( nCurrentDay <= nFiscalYearDay )
         { 
            //://TraceLineI("nCurrentDay <= nFiscalYearDay", nCurrentDay )
            //://TraceLineI("nCurrentDay <= nFiscalYearDay", nFiscalYearDay )
            //:zIntegerToString( szDateOut, 4, nCurrentYear )
            zIntegerToString( szDateOut, 4, nCurrentYear );
            //:RETURN nCurrentYear
            if(8==8)return( nCurrentYear );
            //:ELSE 
         } 
         else
         { 
            //://TraceLineI("nCurrentDay > nFiscalYearDay", nCurrentDay )
            //://TraceLineI("nCurrentDay > nFiscalYearDay", nFiscalYearDay )
            //:zIntegerToString( szDateOut, 4, nNextYear )
            zIntegerToString( szDateOut, 4, nNextYear );
            //:RETURN nNextYear
            if(8==8)return( nNextYear );
         } 

         //:END 
      } 

      //:END 
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dCurrentFiscalYear( VIEW sHost BASED ON LOD sHost,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )
//:   
//:   VIEW wXferO       REGISTERED AS wXferO
public int 
osHost_dCurrentFiscalYear( View     sHost,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mGLFYCurrent BASED ON LOD  mGLFY   
   zVIEW    mGLFYCurrent = new zVIEW( );
   //:VIEW sHost2       BASED ON LOD  sHost
   zVIEW    sHost2 = new zVIEW( );
   //:STRING ( 8 ) szCurrentDate
   String   szCurrentDate = null;
   //:STRING ( 4 ) szFiscalYear
   String   szFiscalYear = null;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", sHost, zLEVEL_TASK );

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// We are going to skip this code if sHost is not named at the Task level because the routine crashes
         //:// trying to get an address for wXferO in that case.
         //:GET VIEW sHost2 NAMED "sHost"
         RESULT = GetViewByName( sHost2, "sHost", sHost, zLEVEL_TASK );
         //:IF RESULT >= 0 
         if ( RESULT >= 0 )
         { 

            //:GET VIEW mGLFYCurrent NAMED "mGLFYCurrentHost"
            RESULT = GetViewByName( mGLFYCurrent, "mGLFYCurrentHost", sHost, zLEVEL_TASK );
            //:IF RESULT < 0
            if ( RESULT < 0 )
            { 
               //:szCurrentDate = wXferO.Root.dCurrentDate 
               {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
               StringBuilder sb_szCurrentDate;
               if ( szCurrentDate == null )
                  sb_szCurrentDate = new StringBuilder( 32 );
               else
                  sb_szCurrentDate = new StringBuilder( szCurrentDate );
                               GetVariableFromAttribute( sb_szCurrentDate, mi_lTempInteger_0, 'S', 9, wXferO, "Root", "dCurrentDate", "", 0 );
               lTempInteger_0 = mi_lTempInteger_0.intValue( );
               szCurrentDate = sb_szCurrentDate.toString( );}
               //:ACTIVATE mGLFYCurrent WHERE mGLFYCurrent.GLFiscalYear.FYBeginDate <= szCurrentDate
               //:                     AND mGLFYCurrent.GLFiscalYear.FYEndDate   >= szCurrentDate
               osHost_fnLocalBuildQual_0( sHost, vTempViewVar_0, szCurrentDate );
               RESULT = ActivateObjectInstance( mGLFYCurrent, "mGLFY", sHost, vTempViewVar_0, zSINGLE );
               DropView( vTempViewVar_0 );
               //:NAME VIEW mGLFYCurrent "mGLFYCurrentHost"
               SetNameForView( mGLFYCurrent, "mGLFYCurrentHost", null, zLEVEL_TASK );
            } 

            //:END
            //:IF RESULT >= 0
            if ( RESULT >= 0 )
            { 

               //:// Get Fiscal Year from current year.
               //:szFiscalYear = mGLFYCurrent.GLFiscalYear.FiscalYear
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_szFiscalYear;
               if ( szFiscalYear == null )
                  sb_szFiscalYear = new StringBuilder( 32 );
               else
                  sb_szFiscalYear = new StringBuilder( szFiscalYear );
                               GetVariableFromAttribute( sb_szFiscalYear, mi_lTempInteger_1, 'S', 5, mGLFYCurrent, "GLFiscalYear", "FiscalYear", "", 0 );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );
               szFiscalYear = sb_szFiscalYear.toString( );}
               //:StoreStringInRecord( sHost, InternalEntityStructure, InternalAttribStructure, szFiscalYear )
               StoreStringInRecord( sHost, InternalEntityStructure, InternalAttribStructure, szFiscalYear );

               //:ELSE
            } 
            else
            { 

               //:// Give error if we can't find current Fiscal Year.
               //:MessageSend( sHost, "", "Determine Fiscal Year",
               //:          "No Fiscal Year has been set up for current date.",
               //:          zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               MessageSend( sHost, "", "Determine Fiscal Year", "No Fiscal Year has been set up for current date.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               //:StoreStringInRecord( sHost, InternalEntityStructure, InternalAttribStructure, "" )
               StoreStringInRecord( sHost, InternalEntityStructure, InternalAttribStructure, "" );
            } 


            //:END
         } 

         //:END
         break ;
      //:  
      //:/* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:   /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dBudgetVarAccountPct( VIEW sHost BASED ON LOD sHost,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )
//:   DECIMAL dPct                  
public int 
osHost_dBudgetVarAccountPct( View     sHost,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   double  dPct = 0.0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:dPct = sHost.Host.BudgetVarianceAccountPercent * 100
         {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                   GetDecimalFromAttribute( md_dTempDecimal_0, sHost, "Host", "BudgetVarianceAccountPercent" );
         dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
         dPct = dTempDecimal_0 * 100;

         //:StoreValueInRecord ( sHost,
         //:                  InternalEntityStructure, InternalAttribStructure, dPct, 0 )
         StoreValueInRecord( sHost, InternalEntityStructure, InternalAttribStructure, dPct, 0 );
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


private int 
osHost_fnLocalBuildQual_0( View     vSubtask,
                           zVIEW    vQualObject,
                           String   szCurrentDate )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "GLFiscalYear" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "GLFiscalYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "FYBeginDate" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szCurrentDate.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "<=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "GLFiscalYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "FYEndDate" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szCurrentDate.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ">=" );
   return( 0 );
} 



}
