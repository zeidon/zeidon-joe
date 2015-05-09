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
public int 
owWebXfer_dFullNameLFM( View     wWebXfer,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{

   //:dFullNameLFM( VIEW wWebXfer BASED ON LOD wWebXfer,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:PersonName_LastFirstMiddle( wWebXfer, InternalEntityStructure,
         //:                           InternalAttribStructure, GetOrSetFlag )
         {
          ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( wWebXfer );
          m_ZGlobal1_Operation.PersonName_LastFirstMiddle( wWebXfer, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
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
owWebXfer_dFullNameFML( View     wWebXfer,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{

   //:dFullNameFML( VIEW wWebXfer BASED ON LOD wWebXfer,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:PersonName_FirstMiddleLast( wWebXfer, InternalEntityStructure,
         //:                           InternalAttribStructure, GetOrSetFlag )
         {
          ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( wWebXfer );
          m_ZGlobal1_Operation.PersonName_FirstMiddleLast( wWebXfer, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
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

         //:szDate = wWebXfer.Root.dCurrentDateTime
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szDate;
         if ( szDate == null )
            sb_szDate = new StringBuilder( 32 );
         else
            sb_szDate = new StringBuilder( szDate );
                   GetVariableFromAttribute( sb_szDate, mi_lTempInteger_0, 'S', 19, wWebXfer, "Root", "dCurrentDateTime", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szDate = sb_szDate.toString( );}
         //:StoreStringInRecord( wWebXfer, InternalEntityStructure, InternalAttribStructure, szDate )
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
         //:StoreStringInRecord( wWebXfer, InternalEntityStructure, InternalAttribStructure, szDateTime )
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



}
