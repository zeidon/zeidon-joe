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

public class wConList_Object extends VmlObjectOperations
{
   public wConList_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
public int 
owConList_dFullNameLFM( View     wConList,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{

   //:dFullNameLFM( VIEW wConList BASED ON LOD wConList,
   //:           STRING ( 32 ) InternalEntityStructure,
   //:           STRING ( 32 ) InternalAttribStructure,
   //:           SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:PersonName_LastFirstMiddle( wConList, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wConList );
          m_ZGLOBAL1_Operation.PersonName_LastFirstMiddle( wConList, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
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
//:dHighACTComposite( VIEW wConList BASED ON LOD wConList,
//:                   STRING ( 32 ) InternalEntityStructure,
//:                   STRING ( 32 ) InternalAttribStructure,
//:                   SHORT GetOrSetFlag )

//:   DECIMAL dTotalComposite
public int 
owConList_dHighACTComposite( View     wConList,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   double  dTotalComposite = 0.0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Compute the highest total score for SAT exams for any object that has the ExamHistory subobject.
         //:dTotalComposite = 0
         dTotalComposite = 0;
         //:FOR EACH  wConList.ExamHistory 
         RESULT = SetCursorFirstEntity( wConList, "ExamHistory", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF  wConList.ExamHistory.ExamType = "ACT" 
            if ( CompareAttributeToString( wConList, "ExamHistory", "ExamType", "ACT" ) == 0 )
            { 
               //:IF wConList.ExamHistory.TotalComposite  > dTotalComposite
               if ( CompareAttributeToDecimal( wConList, "ExamHistory", "TotalComposite", dTotalComposite ) > 0 )
               { 
                  //:dTotalComposite = wConList.ExamHistory.TotalComposite 
                  {MutableDouble md_dTotalComposite = new MutableDouble( dTotalComposite );
                                     GetDecimalFromAttribute( md_dTotalComposite, wConList, "ExamHistory", "TotalComposite" );
                  dTotalComposite = md_dTotalComposite.doubleValue( );}
               } 

               //:END 
            } 

            RESULT = SetCursorNextEntity( wConList, "ExamHistory", "" );
            //:END
         } 

         //:END

         //:IF dTotalComposite = 0
         if ( dTotalComposite == 0 )
         { 
            //:StoreStringInRecord ( wConList,
            //:                   InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( wConList, InternalEntityStructure, InternalAttribStructure, "" );
            //:ELSE
         } 
         else
         { 
            //:StoreValueInRecord ( wConList,
            //:                  InternalEntityStructure, InternalAttribStructure, dTotalComposite, 0 )
            StoreValueInRecord( wConList, InternalEntityStructure, InternalAttribStructure, dTotalComposite, 0 );
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
//:dHighSATWriting( VIEW wConList BASED ON LOD wConList,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )
//:                 
//:   DECIMAL dHighWriting
public int 
owConList_dHighSATWriting( View     wConList,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   double  dHighWriting = 0.0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:// Compute the highest total score for SAT exams for any object that has the ExamHistory subobject.
         //:dHighWriting    = 0
         dHighWriting = 0;
         //:FOR EACH  wConList.ExamHistory 
         RESULT = SetCursorFirstEntity( wConList, "ExamHistory", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF  wConList.ExamHistory.ExamType = "SAT"
            if ( CompareAttributeToString( wConList, "ExamHistory", "ExamType", "SAT" ) == 0 )
            { 
               //:IF wConList.ExamHistory.Writing > dHighWriting  
               if ( CompareAttributeToDecimal( wConList, "ExamHistory", "Writing", dHighWriting ) > 0 )
               { 
                  //:dHighWriting = wConList.ExamHistory.Writing 
                  {MutableDouble md_dHighWriting = new MutableDouble( dHighWriting );
                                     GetDecimalFromAttribute( md_dHighWriting, wConList, "ExamHistory", "Writing" );
                  dHighWriting = md_dHighWriting.doubleValue( );}
               } 

               //:END 
            } 

            RESULT = SetCursorNextEntity( wConList, "ExamHistory", "" );
            //:END
         } 

         //:END

         //:IF dHighWriting = 0
         if ( dHighWriting == 0 )
         { 
            //:StoreStringInRecord ( wConList,
            //:                   InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( wConList, InternalEntityStructure, InternalAttribStructure, "" );
            //:ELSE
         } 
         else
         { 
            //:StoreValueInRecord ( wConList,
            //:                  InternalEntityStructure, InternalAttribStructure, dHighWriting, 0 )
            StoreValueInRecord( wConList, InternalEntityStructure, InternalAttribStructure, dHighWriting, 0 );
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
//:dHighSATVerbal( VIEW wConList BASED ON LOD wConList,
//:             STRING ( 32 ) InternalEntityStructure,
//:             STRING ( 32 ) InternalAttribStructure,
//:             SHORT GetOrSetFlag )
//:             
//:   DECIMAL dHighVerbal 
public int 
owConList_dHighSATVerbal( View     wConList,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   double  dHighVerbal = 0.0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:// Compute the highest total score for SAT exams for any object that has the ExamHistory subobject.
         //:dHighVerbal    = 0
         dHighVerbal = 0;
         //:FOR EACH  wConList.ExamHistory 
         RESULT = SetCursorFirstEntity( wConList, "ExamHistory", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF  wConList.ExamHistory.ExamType = "SAT"
            if ( CompareAttributeToString( wConList, "ExamHistory", "ExamType", "SAT" ) == 0 )
            { 
               //:IF wConList.ExamHistory.Verbal > dHighVerbal  
               if ( CompareAttributeToDecimal( wConList, "ExamHistory", "Verbal", dHighVerbal ) > 0 )
               { 
                  //:dHighVerbal = wConList.ExamHistory.Verbal 
                  {MutableDouble md_dHighVerbal = new MutableDouble( dHighVerbal );
                                     GetDecimalFromAttribute( md_dHighVerbal, wConList, "ExamHistory", "Verbal" );
                  dHighVerbal = md_dHighVerbal.doubleValue( );}
               } 

               //:END 
            } 

            RESULT = SetCursorNextEntity( wConList, "ExamHistory", "" );
            //:END
         } 

         //:END

         //:IF dHighVerbal = 0
         if ( dHighVerbal == 0 )
         { 
            //:StoreStringInRecord ( wConList,
            //:                   InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( wConList, InternalEntityStructure, InternalAttribStructure, "" );
            //:ELSE
         } 
         else
         { 
            //:StoreValueInRecord ( wConList,
            //:                  InternalEntityStructure, InternalAttribStructure, dHighVerbal, 0 )
            StoreValueInRecord( wConList, InternalEntityStructure, InternalAttribStructure, dHighVerbal, 0 );
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
//:dHighSATReading( VIEW wConList BASED ON LOD wConList,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )
//:             
//:   DECIMAL dHighReading 
public int 
owConList_dHighSATReading( View     wConList,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   double  dHighReading = 0.0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:// Compute the highest total score for SAT exams for any object that has the ExamHistory subobject.
         //:dHighReading    = 0
         dHighReading = 0;
         //:FOR EACH  wConList.ExamHistory 
         RESULT = SetCursorFirstEntity( wConList, "ExamHistory", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF  wConList.ExamHistory.ExamType = "SAT"
            if ( CompareAttributeToString( wConList, "ExamHistory", "ExamType", "SAT" ) == 0 )
            { 
               //:IF wConList.ExamHistory.ReadingComprehension > dHighReading  
               if ( CompareAttributeToDecimal( wConList, "ExamHistory", "ReadingComprehension", dHighReading ) > 0 )
               { 
                  //:dHighReading = wConList.ExamHistory.ReadingComprehension 
                  {MutableDouble md_dHighReading = new MutableDouble( dHighReading );
                                     GetDecimalFromAttribute( md_dHighReading, wConList, "ExamHistory", "ReadingComprehension" );
                  dHighReading = md_dHighReading.doubleValue( );}
               } 

               //:END 
            } 

            RESULT = SetCursorNextEntity( wConList, "ExamHistory", "" );
            //:END
         } 

         //:END

         //:IF dHighReading = 0
         if ( dHighReading == 0 )
         { 
            //:StoreStringInRecord ( wConList,
            //:                   InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( wConList, InternalEntityStructure, InternalAttribStructure, "" );
            //:ELSE
         } 
         else
         { 
            //:StoreValueInRecord ( wConList,
            //:                  InternalEntityStructure, InternalAttribStructure, dHighReading, 0 )
            StoreValueInRecord( wConList, InternalEntityStructure, InternalAttribStructure, dHighReading, 0 );
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
//:dContentCount( VIEW wConList BASED ON LOD wConList,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   INTEGER ContentCount
public int 
owConList_dContentCount( View     wConList,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   int      ContentCount = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
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
//:dEMailAddress( VIEW wConList BASED ON LOD wConList,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )
//:               
//:   STRING (  128  ) sEMailAddress               
public int 
owConList_dEMailAddress( View     wConList,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   String   sEMailAddress = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:sEMailAddress = ""
          {StringBuilder sb_sEMailAddress;
         if ( sEMailAddress == null )
            sb_sEMailAddress = new StringBuilder( 32 );
         else
            sb_sEMailAddress = new StringBuilder( sEMailAddress );
                  ZeidonStringCopy( sb_sEMailAddress, 1, 0, "", 1, 0, 129 );
         sEMailAddress = sb_sEMailAddress.toString( );}

         //://To decide which email address to use, first check if this person is
         //://a valid employee.  If so, use the employee email, if not check if they are
         //://a valid student.  If so, use the student email and if not then use the
         //://email on the Person entity.
         //:IF  wConList.YearlyContract EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( wConList, "YearlyContract" );
         if ( lTempInteger_0 == 0 )
         { 
            //:IF wConList.Employee.eMailAddress != ""
            if ( CompareAttributeToString( wConList, "Employee", "eMailAddress", "" ) != 0 )
            { 
               //:sEMailAddress =  wConList.Employee.eMailAddress
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_sEMailAddress;
               if ( sEMailAddress == null )
                  sb_sEMailAddress = new StringBuilder( 32 );
               else
                  sb_sEMailAddress = new StringBuilder( sEMailAddress );
                               GetVariableFromAttribute( sb_sEMailAddress, mi_lTempInteger_1, 'S', 129, wConList, "Employee", "eMailAddress", "", 0 );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );
               sEMailAddress = sb_sEMailAddress.toString( );}
            } 

            //:END 
         } 

         //:END

         //:IF  sEMailAddress = "" AND wConList.Student EXISTS
         lTempInteger_2 = CheckExistenceOfEntity( wConList, "Student" );
         if ( ZeidonStringCompare( sEMailAddress, 1, 0, "", 1, 0, 129 ) == 0 && lTempInteger_2 == 0 )
         { 
            //:IF  wConList.Student.Status = "A" AND wConList.Student.eMailAddress != ""
            if ( CompareAttributeToString( wConList, "Student", "Status", "A" ) == 0 && CompareAttributeToString( wConList, "Student", "eMailAddress", "" ) != 0 )
            { 
               //:sEMailAddress = wConList.Student.eMailAddress 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_sEMailAddress;
               if ( sEMailAddress == null )
                  sb_sEMailAddress = new StringBuilder( 32 );
               else
                  sb_sEMailAddress = new StringBuilder( sEMailAddress );
                               GetVariableFromAttribute( sb_sEMailAddress, mi_lTempInteger_3, 'S', 129, wConList, "Student", "eMailAddress", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               sEMailAddress = sb_sEMailAddress.toString( );}
            } 

            //:END 
         } 

         //:END

         //:IF  sEMailAddress = ""
         if ( ZeidonStringCompare( sEMailAddress, 1, 0, "", 1, 0, 129 ) == 0 )
         { 
            //:sEMailAddress = wConList.Person.eMailAddress   
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_sEMailAddress;
            if ( sEMailAddress == null )
               sb_sEMailAddress = new StringBuilder( 32 );
            else
               sb_sEMailAddress = new StringBuilder( sEMailAddress );
                         GetVariableFromAttribute( sb_sEMailAddress, mi_lTempInteger_4, 'S', 129, wConList, "Person", "eMailAddress", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            sEMailAddress = sb_sEMailAddress.toString( );}
         } 

         //:END

         //:StoreValueInRecord ( wConList,
         //:                  InternalEntityStructure, InternalAttribStructure, sEMailAddress, 0 )
         StoreValueInRecord( wConList, InternalEntityStructure, InternalAttribStructure, sEMailAddress, 0 );
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
//:dHighSATMath( VIEW wConList BASED ON LOD wConList,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )
//:              
//:   DECIMAL dHighMath
public int 
owConList_dHighSATMath( View     wConList,
                        String InternalEntityStructure,
                        String InternalAttribStructure,
                        Integer   GetOrSetFlag )
{
   double  dHighMath = 0.0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Compute the highest total score for SAT exams for any object that has the ExamHistory subobject.
         //:dHighMath    = 0
         dHighMath = 0;
         //:FOR EACH  wConList.ExamHistory 
         RESULT = SetCursorFirstEntity( wConList, "ExamHistory", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF  wConList.ExamHistory.ExamType = "SAT"
            if ( CompareAttributeToString( wConList, "ExamHistory", "ExamType", "SAT" ) == 0 )
            { 
               //:IF  wConList.ExamHistory.MathQuantitative > dHighMath  
               if ( CompareAttributeToDecimal( wConList, "ExamHistory", "MathQuantitative", dHighMath ) > 0 )
               { 
                  //:dHighMath = wConList.ExamHistory.MathQuantitative 
                  {MutableDouble md_dHighMath = new MutableDouble( dHighMath );
                                     GetDecimalFromAttribute( md_dHighMath, wConList, "ExamHistory", "MathQuantitative" );
                  dHighMath = md_dHighMath.doubleValue( );}
               } 

               //:END 
            } 

            RESULT = SetCursorNextEntity( wConList, "ExamHistory", "" );
            //:END
         } 

         //:END

         //:IF dHighMath = 0
         if ( dHighMath == 0 )
         { 
            //:StoreStringInRecord ( wConList,
            //:                   InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( wConList, InternalEntityStructure, InternalAttribStructure, "" );
            //:ELSE
         } 
         else
         { 
            //:StoreValueInRecord ( wConList,
            //:                  InternalEntityStructure, InternalAttribStructure, dHighMath, 0 )
            StoreValueInRecord( wConList, InternalEntityStructure, InternalAttribStructure, dHighMath, 0 );
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
//:dProspectCount( VIEW wConList BASED ON LOD wConList,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   INTEGER ContentCount
public int 
owConList_dProspectCount( View     wConList,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   int      ContentCount = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:FOR EACH wConList.Prospect 
         RESULT = SetCursorFirstEntity( wConList, "Prospect", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( wConList, "Prospect", "" );
         } 

         //:END


         //:StoreValueInRecord ( wConList,
         //:                  InternalEntityStructure, InternalAttribStructure, ContentCount, 0 )
         StoreValueInRecord( wConList, InternalEntityStructure, InternalAttribStructure, ContentCount, 0 );
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
owConList_dRecurringList( View     wConList,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   int      lTempInteger_0 = 0;

   //:dRecurringList( VIEW wConList BASED ON LOD wConList,
   //:             STRING ( 32 ) InternalEntityStructure,
   //:             STRING ( 32 ) InternalAttribStructure,
   //:             SHORT GetOrSetFlag )

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF wConList.DynamicCL_StoredQuery exists
         lTempInteger_0 = CheckExistenceOfEntity( wConList, "DynamicCL_StoredQuery" );
         if ( lTempInteger_0 == 0 )
         { 
            //:StoreStringInRecord ( wConList,
            //:                   InternalEntityStructure, InternalAttribStructure, "Y" )
            StoreStringInRecord( wConList, InternalEntityStructure, InternalAttribStructure, "Y" );
            //:ELSE                        
         } 
         else
         { 
            //:StoreStringInRecord ( wConList,
            //:                   InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( wConList, InternalEntityStructure, InternalAttribStructure, "" );
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
