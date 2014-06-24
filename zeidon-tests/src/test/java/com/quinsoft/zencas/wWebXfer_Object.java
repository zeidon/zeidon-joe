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

import com.quinsoft.zeidon.zeidonoperations.KZOEP1AA;

/**
   @author QuinSoft
**/

public class wWebXfer_Object extends VmlObjectOperations
{
   private final KZOEP1AA m_KZOEP1AA;
   public wWebXfer_Object( View view )
   {
      super( view );
      m_KZOEP1AA = new KZOEP1AA( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dCurrentDate( VIEW wWebXfer BASED ON LOD wWebXfer,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )

//:   STRING (  18  ) szDate
public int 
owWebXfer_dCurrentDate( View     wWebXfer,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   String   szDate = null;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:szDate = wWebXfer.Work.dCurrentDateTime
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szDate;
         if ( szDate == null )
            sb_szDate = new StringBuilder( 32 );
         else
            sb_szDate = new StringBuilder( szDate );
                   GetVariableFromAttribute( sb_szDate, mi_lTempInteger_0, 'S', 19, wWebXfer, "Work", "dCurrentDateTime", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szDate = sb_szDate.toString( );}
         //:TraceLineS("*** !!!dCurrentDateTime *** ", szDate)
         TraceLineS( "*** !!!dCurrentDateTime *** ", szDate );
         //:StoreStringInRecord ( wWebXfer,
         //:                      InternalEntityStructure, InternalAttribStructure, szDate )
         StoreStringInRecord( wWebXfer, InternalEntityStructure, InternalAttribStructure, szDate );
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
//:dTotalBudgetedAmount( VIEW wWebXfer BASED ON LOD wWebXfer,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )

//:   VIEW mGLCCURLST  BASED ON LOD mGLCCUR
public int 
owWebXfer_dTotalBudgetedAmount( View     wWebXfer,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   zVIEW    mGLCCURLST = new zVIEW( );
   //:VIEW mGLCCURT    BASED ON LOD mGLCCUR
   zVIEW    mGLCCURT = new zVIEW( );
   //:DECIMAL dTotalAmount
   double  dTotalAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Compute total amounts for PO's listed in lPOList.
         //:GET VIEW mGLCCURLST NAMED "mGLCCURLST"
         RESULT = GetViewByName( mGLCCURLST, "mGLCCURLST", wWebXfer, zLEVEL_TASK );
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 
            //:CreateViewFromView( mGLCCURT, mGLCCURLST )
            CreateViewFromView( mGLCCURT, mGLCCURLST );
            //:dTotalAmount = 0
            dTotalAmount = 0;
            //:FOR EACH mGLCCURT.GLCostCenter  
            RESULT = SetCursorFirstEntity( mGLCCURT, "GLCostCenter", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:dTotalAmount = dTotalAmount + mGLCCURT.GLCostCenter.dTotalBudgetedAmount  
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mGLCCURT, "GLCostCenter", "dTotalBudgetedAmount" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dTotalAmount = dTotalAmount + dTempDecimal_0;
               RESULT = SetCursorNextEntity( mGLCCURT, "GLCostCenter", "" );
            } 

            //:END
            //:DropView( mGLCCURT )
            DropView( mGLCCURT );

            //:StoreValueInRecord( wWebXfer, InternalEntityStructure, 
            //:                 InternalAttribStructure, dTotalAmount, 0)
            StoreValueInRecord( wWebXfer, InternalEntityStructure, InternalAttribStructure, dTotalAmount, 0 );

            //:ELSE
         } 
         else
         { 
            //:// No Purchase Order list exists.
            //:StoreStringInRecord( wWebXfer,
            //:                  InternalEntityStructure,
            //:                  InternalAttribStructure,
            //:                  "" )
            StoreStringInRecord( wWebXfer, InternalEntityStructure, InternalAttribStructure, "" );
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
//:dCurrentDateTime( VIEW wWebXfer BASED ON LOD wWebXfer,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING (  18  ) szDateTime
public int 
owWebXfer_dCurrentDateTime( View     wWebXfer,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szDateTime = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :

         //:SysGetDateTime( szDateTime )
          {StringBuilder sb_szDateTime;
         if ( szDateTime == null )
            sb_szDateTime = new StringBuilder( 32 );
         else
            sb_szDateTime = new StringBuilder( szDateTime );
                  m_KZOEP1AA.SysGetDateTime( sb_szDateTime );
         szDateTime = sb_szDateTime.toString( );}
         //:StoreStringInRecord ( wWebXfer,
         //:                      InternalEntityStructure, InternalAttribStructure, szDateTime )
         StoreStringInRecord( wWebXfer, InternalEntityStructure, InternalAttribStructure, szDateTime );
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
//:dPagePosition( VIEW wWebXfer BASED ON LOD wWebXfer,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )
//:               
//:   STRING (  256  )  szPathPos
public int 
owWebXfer_dPagePosition( View     wWebXfer,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   String   szPathPos = null;
   //:INTEGER           iCount
   int      iCount = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;


   //:iCount = 0
   iCount = 0;
   //:szPathPos = ""
    {StringBuilder sb_szPathPos;
   if ( szPathPos == null )
      sb_szPathPos = new StringBuilder( 32 );
   else
      sb_szPathPos = new StringBuilder( szPathPos );
      ZeidonStringCopy( sb_szPathPos, 1, 0, "", 1, 0, 257 );
   szPathPos = sb_szPathPos.toString( );}

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:FOR EACH wWebXfer.PagePath 
         RESULT = SetCursorFirstEntity( wWebXfer, "PagePath", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF  iCount > 0
            if ( iCount > 0 )
            { 
               //:szPathPos = szPathPos + "->" 
                {StringBuilder sb_szPathPos;
               if ( szPathPos == null )
                  sb_szPathPos = new StringBuilder( 32 );
               else
                  sb_szPathPos = new StringBuilder( szPathPos );
                              ZeidonStringConcat( sb_szPathPos, 1, 0, "->", 1, 0, 257 );
               szPathPos = sb_szPathPos.toString( );}
            } 

            //:END
            //:szPathPos = szPathPos + wWebXfer.PagePath.DisplayPageName 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 255, wWebXfer, "PagePath", "DisplayPageName", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szPathPos;
            if ( szPathPos == null )
               sb_szPathPos = new StringBuilder( 32 );
            else
               sb_szPathPos = new StringBuilder( szPathPos );
                        ZeidonStringConcat( sb_szPathPos, 1, 0, szTempString_0, 1, 0, 257 );
            szPathPos = sb_szPathPos.toString( );}
            //:iCount = iCount + 1
            iCount = iCount + 1;
            RESULT = SetCursorNextEntity( wWebXfer, "PagePath", "" );
         } 

         //:END 

         //:StoreStringInRecord ( wWebXfer,
         //:                      InternalEntityStructure, InternalAttribStructure, szPathPos )
         StoreStringInRecord( wWebXfer, InternalEntityStructure, InternalAttribStructure, szPathPos );
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
//:dTotalListedPO_Amounts( VIEW wWebXfer BASED ON LOD wWebXfer,
//:                        STRING ( 32 ) InternalEntityStructure,
//:                        STRING ( 32 ) InternalAttribStructure,
//:                        SHORT GetOrSetFlag )

//:   VIEW lPOList  BASED ON LOD lPOList
public int 
owWebXfer_dTotalListedPO_Amounts( View     wWebXfer,
                                  String InternalEntityStructure,
                                  String InternalAttribStructure,
                                  Integer   GetOrSetFlag )
{
   zVIEW    lPOList = new zVIEW( );
   //:VIEW lPOListT BASED ON LOD lPOList
   zVIEW    lPOListT = new zVIEW( );
   //:DECIMAL dTotalAmount
   double  dTotalAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Compute total amounts for PO's listed in lPOList.
         //:GET VIEW lPOList NAMED "lPOList"
         RESULT = GetViewByName( lPOList, "lPOList", wWebXfer, zLEVEL_TASK );
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 
            //:CreateViewFromView( lPOListT, lPOList )
            CreateViewFromView( lPOListT, lPOList );
            //:dTotalAmount = 0
            dTotalAmount = 0;
            //:FOR EACH lPOListT.PurchaseOrder 
            RESULT = SetCursorFirstEntity( lPOListT, "PurchaseOrder", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:dTotalAmount = dTotalAmount + lPOListT.PurchaseOrder.TotalAmount 
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, lPOListT, "PurchaseOrder", "TotalAmount" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dTotalAmount = dTotalAmount + dTempDecimal_0;
               RESULT = SetCursorNextEntity( lPOListT, "PurchaseOrder", "" );
            } 

            //:END
            //:DropView( lPOListT )
            DropView( lPOListT );

            //:StoreValueInRecord( wWebXfer, InternalEntityStructure, 
            //:                 InternalAttribStructure, dTotalAmount, 0)
            StoreValueInRecord( wWebXfer, InternalEntityStructure, InternalAttribStructure, dTotalAmount, 0 );

            //:ELSE
         } 
         else
         { 
            //:// No Purchase Order list exists.
            //:StoreStringInRecord( wWebXfer,
            //:                  InternalEntityStructure,
            //:                  InternalAttribStructure,
            //:                  "" )
            StoreStringInRecord( wWebXfer, InternalEntityStructure, InternalAttribStructure, "" );
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
//:dTotalRequestedAmount( VIEW wWebXfer BASED ON LOD wWebXfer,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   VIEW mGLCCURLST  BASED ON LOD mGLCCUR
public int 
owWebXfer_dTotalRequestedAmount( View     wWebXfer,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   zVIEW    mGLCCURLST = new zVIEW( );
   //:VIEW mGLCCURT    BASED ON LOD mGLCCUR
   zVIEW    mGLCCURT = new zVIEW( );
   //:DECIMAL dTotalAmount
   double  dTotalAmount = 0.0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Compute total amounts for PO's listed in lPOList.
         //:GET VIEW mGLCCURLST NAMED "mGLCCURLST"
         RESULT = GetViewByName( mGLCCURLST, "mGLCCURLST", wWebXfer, zLEVEL_TASK );
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 
            //:CreateViewFromView( mGLCCURT, mGLCCURLST )
            CreateViewFromView( mGLCCURT, mGLCCURLST );
            //:dTotalAmount = 0
            dTotalAmount = 0;
            //:FOR EACH mGLCCURT.GLCostCenter  
            RESULT = SetCursorFirstEntity( mGLCCURT, "GLCostCenter", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:dTotalAmount = dTotalAmount + mGLCCURT.GLCostCenter.dTotalRequestedAmount  
               {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                               GetDecimalFromAttribute( md_dTempDecimal_0, mGLCCURT, "GLCostCenter", "dTotalRequestedAmount" );
               dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
               dTotalAmount = dTotalAmount + dTempDecimal_0;
               RESULT = SetCursorNextEntity( mGLCCURT, "GLCostCenter", "" );
            } 

            //:END
            //:DropView( mGLCCURT )
            DropView( mGLCCURT );

            //:StoreValueInRecord( wWebXfer, InternalEntityStructure, 
            //:                 InternalAttribStructure, dTotalAmount, 0)
            StoreValueInRecord( wWebXfer, InternalEntityStructure, InternalAttribStructure, dTotalAmount, 0 );

            //:ELSE
         } 
         else
         { 
            //:// No Purchase Order list exists.
            //:StoreStringInRecord( wWebXfer,
            //:                  InternalEntityStructure,
            //:                  InternalAttribStructure,
            //:                  "" )
            StoreStringInRecord( wWebXfer, InternalEntityStructure, InternalAttribStructure, "" );
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
