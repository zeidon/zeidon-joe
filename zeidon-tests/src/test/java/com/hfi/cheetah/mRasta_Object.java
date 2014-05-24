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

package com.hfi.cheetah;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

/**
   @author QuinSoft
**/

public class mRasta_Object extends VmlObjectOperations
{
   public mRasta_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
public int 
omRasta_dHasValidPhone( View     mRasta,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{

   //:dHasValidPhone( VIEW mRasta BASED ON LOD mRasta,
   //:             STRING ( 32 ) InternalEntityStructure,
   //:             STRING ( 32 ) InternalAttribStructure,
   //:             SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:IF ( mRasta.Person.Phone1Type = "I" OR ( mRasta.Person.Phone1Type = "" AND mRasta.Person.Phone1 = "") ) AND  
         //:( mRasta.Person.Phone2Type = "I" OR ( mRasta.Person.Phone2Type = "" AND mRasta.Person.Phone2 = "") ) AND  
         //:( mRasta.Person.Phone3Type = "I" OR ( mRasta.Person.Phone3Type = "" AND mRasta.Person.Phone3 = "") )
         if ( ( CompareAttributeToString( mRasta, "Person", "Phone1Type", "I" ) == 0 || ( CompareAttributeToString( mRasta, "Person", "Phone1Type", "" ) == 0 && CompareAttributeToString( mRasta, "Person", "Phone1", "" ) == 0 ) ) &&
              ( CompareAttributeToString( mRasta, "Person", "Phone2Type", "I" ) == 0 || ( CompareAttributeToString( mRasta, "Person", "Phone2Type", "" ) == 0 && CompareAttributeToString( mRasta, "Person", "Phone2", "" ) == 0 ) ) &&
              ( CompareAttributeToString( mRasta, "Person", "Phone3Type", "I" ) == 0 || ( CompareAttributeToString( mRasta, "Person", "Phone3Type", "" ) == 0 && CompareAttributeToString( mRasta, "Person", "Phone3", "" ) == 0 ) ) )
         { 
            //:StoreStringInRecord( mRasta, InternalEntityStructure, 
            //:                  InternalAttribStructure, "N" )
            StoreStringInRecord( mRasta, InternalEntityStructure, InternalAttribStructure, "N" );
            //:ELSE 
         } 
         else
         { 
            //:StoreStringInRecord( mRasta, InternalEntityStructure, 
            //:                  InternalAttribStructure, "Y" )
            StoreStringInRecord( mRasta, InternalEntityStructure, InternalAttribStructure, "Y" );
         } 

         //:END
         break ;
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
//:dTotalCharges( VIEW mRasta BASED ON LOD mRasta,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )
//:   DECIMAL dValue 
public int 
omRasta_dTotalCharges( View     mRasta,
                       String InternalEntityStructure,
                       String InternalAttribStructure,
                       Integer   GetOrSetFlag )
{
   double  dValue = 0.0;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   double  dTempDecimal_0 = 0.0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF  zDERIVED_GET:
      case zDERIVED_GET :
         //:dValue = 0
         dValue = 0;
         //:IF mRasta.ApplicantVisit EXISTS 
         lTempInteger_0 = CheckExistenceOfEntity( mRasta, "ApplicantVisit" );
         if ( lTempInteger_0 == 0 )
         { 
            //:FOR EACH mRasta.ApplicantVisit 
            RESULT = SetCursorFirstEntity( mRasta, "ApplicantVisit", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:FOR EACH mRasta.VisitBillingLine  
               RESULT = SetCursorFirstEntity( mRasta, "VisitBillingLine", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:IF  mRasta.VisitBillingLine.TotalCharges != ""
                  if ( CompareAttributeToString( mRasta, "VisitBillingLine", "TotalCharges", "" ) != 0 )
                  { 
                     //:dValue = dValue + mRasta.VisitBillingLine.TotalCharges  
                     {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
                                           GetDecimalFromAttribute( md_dTempDecimal_0, mRasta, "VisitBillingLine", "TotalCharges" );
                     dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
                     dValue = dValue + dTempDecimal_0;
                  } 

                  RESULT = SetCursorNextEntity( mRasta, "VisitBillingLine", "" );
                  //:END
               } 

               RESULT = SetCursorNextEntity( mRasta, "ApplicantVisit", "" );
               //:END  
            } 

            //:END  
         } 

         //:END         
         //:StoreValueInRecord( mRasta, InternalEntityStructure, 
         //:                   InternalAttribStructure, dValue ,0  )
         StoreValueInRecord( mRasta, InternalEntityStructure, InternalAttribStructure, dValue, 0 );
         break ;
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



}
