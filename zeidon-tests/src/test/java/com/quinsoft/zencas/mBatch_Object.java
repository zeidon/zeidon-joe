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

import com.quinsoft.zencas.ZGLOBAL2_Operation;

/**
   @author QuinSoft
**/

public class mBatch_Object extends VmlObjectOperations
{
   public mBatch_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dPersonFullName( VIEW mBatch BASED ON LOD mBatch,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )

//:   STRING ( 100 ) szFullName
public int 
omBatch_dPersonFullName( View     mBatch,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   String   szFullName = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetPersonFullName( szFullName, mBatch, "Person" )
         {
          ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mBatch );
          {StringBuilder sb_szFullName;
         if ( szFullName == null )
            sb_szFullName = new StringBuilder( 32 );
         else
            sb_szFullName = new StringBuilder( szFullName );
                   m_ZGLOBAL2_Operation.GetPersonFullName( sb_szFullName, mBatch, "Person" );
         szFullName = sb_szFullName.toString( );}
          // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
         }

         //:StoreStringInRecord ( mBatch,
         //:                InternalEntityStructure, InternalAttribStructure, szFullName )
         StoreStringInRecord( mBatch, InternalEntityStructure, InternalAttribStructure, szFullName );
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
//:dDuplicateFullName( VIEW mBatch BASED ON LOD mBatch,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   STRING ( 100 ) szFullName
public int 
omBatch_dDuplicateFullName( View     mBatch,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szFullName = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetPersonFullName( szFullName, mBatch, "PotentialDuplicatePerson" )
         {
          ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mBatch );
          {StringBuilder sb_szFullName;
         if ( szFullName == null )
            sb_szFullName = new StringBuilder( 32 );
         else
            sb_szFullName = new StringBuilder( szFullName );
                   m_ZGLOBAL2_Operation.GetPersonFullName( sb_szFullName, mBatch, "PotentialDuplicatePerson" );
         szFullName = sb_szFullName.toString( );}
          // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
         }

         //:StoreStringInRecord ( mBatch,
         //:                InternalEntityStructure, InternalAttribStructure, szFullName )
         StoreStringInRecord( mBatch, InternalEntityStructure, InternalAttribStructure, szFullName );
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
