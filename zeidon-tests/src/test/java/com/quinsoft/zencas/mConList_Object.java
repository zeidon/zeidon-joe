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

public class mConList_Object extends VmlObjectOperations
{
   public mConList_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dFullPersonNameLFM( VIEW mConList BASED ON LOD mConList,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   STRING (  254  ) szString
public int 
omConList_dFullPersonNameLFM( View     mConList,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szString = null;
   //:STRING ( 4 )     szYear
   String   szYear = null;
   //:STRING ( 5 )     szTermYear
   String   szTermYear = null;
   //:STRING ( 1 )     szShortTerm
   String   szShortTerm = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_7 = 0;
   String   szTempString_4 = null;
   int      lTempInteger_8 = 0;
   String   szTempString_5 = null;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   String   szTempString_6 = null;
   int      lTempInteger_11 = 0;
   String   szTempString_7 = null;
   int      lTempInteger_12 = 0;
   String   szTempString_8 = null;
   int      lTempInteger_13 = 0;
   int      lTempInteger_14 = 0;
   String   szTempString_9 = null;
   int      lTempInteger_15 = 0;
   String   szTempString_10 = null;
   int      lTempInteger_16 = 0;
   String   szTempString_11 = null;
   int      lTempInteger_17 = 0;
   int      lTempInteger_18 = 0;
   String   szTempString_12 = null;
   int      lTempInteger_19 = 0;
   String   szTempString_13 = null;
   int      lTempInteger_20 = 0;
   String   szTempString_14 = null;
   int      lTempInteger_21 = 0;
   int      lTempInteger_22 = 0;
   String   szTempString_15 = null;
   int      lTempInteger_23 = 0;
   String   szTempString_16 = null;
   int      lTempInteger_24 = 0;
   String   szTempString_17 = null;
   int      lTempInteger_25 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:IF mConList.ProspectPerson EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mConList, "ProspectPerson" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szYear = mConList.Prospect.ExpectedEntryYear
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szYear;
            if ( szYear == null )
               sb_szYear = new StringBuilder( 32 );
            else
               sb_szYear = new StringBuilder( szYear );
                         GetVariableFromAttribute( sb_szYear, mi_lTempInteger_1, 'S', 5, mConList, "Prospect", "ExpectedEntryYear", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szYear = sb_szYear.toString( );}

            //:szShortTerm = mConList.Prospect.ExpectedEntryTerm
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szShortTerm;
            if ( szShortTerm == null )
               sb_szShortTerm = new StringBuilder( 32 );
            else
               sb_szShortTerm = new StringBuilder( szShortTerm );
                         GetVariableFromAttribute( sb_szShortTerm, mi_lTempInteger_2, 'S', 2, mConList, "Prospect", "ExpectedEntryTerm", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szShortTerm = sb_szShortTerm.toString( );}
            //:IF szYear != "" AND szShortTerm != "" 
            if ( ZeidonStringCompare( szYear, 1, 0, "", 1, 0, 5 ) != 0 && ZeidonStringCompare( szShortTerm, 1, 0, "", 1, 0, 2 ) != 0 )
            { 
               //:szTermYear = " " + szShortTerm + "-" + szYear[3:2]
                {StringBuilder sb_szTermYear;
               if ( szTermYear == null )
                  sb_szTermYear = new StringBuilder( 32 );
               else
                  sb_szTermYear = new StringBuilder( szTermYear );
                              ZeidonStringCopy( sb_szTermYear, 1, 0, " ", 1, 0, 6 );
               szTermYear = sb_szTermYear.toString( );}
                {StringBuilder sb_szTermYear;
               if ( szTermYear == null )
                  sb_szTermYear = new StringBuilder( 32 );
               else
                  sb_szTermYear = new StringBuilder( szTermYear );
                              ZeidonStringConcat( sb_szTermYear, 1, 0, szShortTerm, 1, 0, 6 );
               szTermYear = sb_szTermYear.toString( );}
                {StringBuilder sb_szTermYear;
               if ( szTermYear == null )
                  sb_szTermYear = new StringBuilder( 32 );
               else
                  sb_szTermYear = new StringBuilder( szTermYear );
                              ZeidonStringConcat( sb_szTermYear, 1, 0, "-", 1, 0, 6 );
               szTermYear = sb_szTermYear.toString( );}
                {StringBuilder sb_szTermYear;
               if ( szTermYear == null )
                  sb_szTermYear = new StringBuilder( 32 );
               else
                  sb_szTermYear = new StringBuilder( szTermYear );
                              ZeidonStringConcat( sb_szTermYear, 1, 0, szYear, 3, 2, 6 );
               szTermYear = sb_szTermYear.toString( );}
               //:ELSE szTermYear = ""
            } 
            else
            { 
                {StringBuilder sb_szTermYear;
               if ( szTermYear == null )
                  sb_szTermYear = new StringBuilder( 32 );
               else
                  sb_szTermYear = new StringBuilder( szTermYear );
                              ZeidonStringCopy( sb_szTermYear, 1, 0, "", 1, 0, 6 );
               szTermYear = sb_szTermYear.toString( );}
            } 

            //:END

            //:szString = mConList.ProspectPerson.LastName + ", " +
            //:           mConList.ProspectPerson.FirstName + " " +
            //:           mConList.ProspectPerson.MiddleName + " " + mConList.ProspectPerson.Suffix + szTermYear         
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttribute( sb_szString, mConList, "ProspectPerson", "LastName" );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, ", ", 1, 0, 255 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_3, 'S', 51, mConList, "ProspectPerson", "FirstName", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 255 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_4, 'S', 51, mConList, "ProspectPerson", "MiddleName", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_1, 1, 0, 255 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
            StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_5, 'S', 51, mConList, "ProspectPerson", "Suffix", "", 0 );
            lTempInteger_5 = mi_lTempInteger_5.intValue( );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_2, 1, 0, 255 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTermYear, 1, 0, 255 );
            szString = sb_szString.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mConList.DonorPerson EXISTS
            lTempInteger_6 = CheckExistenceOfEntity( mConList, "DonorPerson" );
            if ( lTempInteger_6 == 0 )
            { 
               //: szString = mConList.DonorPerson.LastName + ", " +
               //:            mConList.DonorPerson.FirstName + " " +
               //:            mConList.DonorPerson.MiddleName + " " + mConList.DonorPerson.Suffix 
               {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                               GetStringFromAttribute( sb_szString, mConList, "DonorPerson", "LastName" );
               szString = sb_szString.toString( );}
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, ", ", 1, 0, 255 );
               szString = sb_szString.toString( );}
               {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
               StringBuilder sb_szTempString_3;
               if ( szTempString_3 == null )
                  sb_szTempString_3 = new StringBuilder( 32 );
               else
                  sb_szTempString_3 = new StringBuilder( szTempString_3 );
                               GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_7, 'S', 51, mConList, "DonorPerson", "FirstName", "", 0 );
               lTempInteger_7 = mi_lTempInteger_7.intValue( );
               szTempString_3 = sb_szTempString_3.toString( );}
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, szTempString_3, 1, 0, 255 );
               szString = sb_szString.toString( );}
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
               szString = sb_szString.toString( );}
               {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
               StringBuilder sb_szTempString_4;
               if ( szTempString_4 == null )
                  sb_szTempString_4 = new StringBuilder( 32 );
               else
                  sb_szTempString_4 = new StringBuilder( szTempString_4 );
                               GetVariableFromAttribute( sb_szTempString_4, mi_lTempInteger_8, 'S', 51, mConList, "DonorPerson", "MiddleName", "", 0 );
               lTempInteger_8 = mi_lTempInteger_8.intValue( );
               szTempString_4 = sb_szTempString_4.toString( );}
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, szTempString_4, 1, 0, 255 );
               szString = sb_szString.toString( );}
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
               szString = sb_szString.toString( );}
               {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
               StringBuilder sb_szTempString_5;
               if ( szTempString_5 == null )
                  sb_szTempString_5 = new StringBuilder( 32 );
               else
                  sb_szTempString_5 = new StringBuilder( szTempString_5 );
                               GetVariableFromAttribute( sb_szTempString_5, mi_lTempInteger_9, 'S', 51, mConList, "DonorPerson", "Suffix", "", 0 );
               lTempInteger_9 = mi_lTempInteger_9.intValue( );
               szTempString_5 = sb_szTempString_5.toString( );}
                {StringBuilder sb_szString;
               if ( szString == null )
                  sb_szString = new StringBuilder( 32 );
               else
                  sb_szString = new StringBuilder( szString );
                              ZeidonStringConcat( sb_szString, 1, 0, szTempString_5, 1, 0, 255 );
               szString = sb_szString.toString( );}
               //:ELSE
            } 
            else
            { 
               //: IF mConList.Person EXISTS
               lTempInteger_10 = CheckExistenceOfEntity( mConList, "Person" );
               if ( lTempInteger_10 == 0 )
               { 
                  //: szString = mConList.Person.LastName + ", " +
                  //:            mConList.Person.FirstName + " " +
                  //:            mConList.Person.MiddleName + " " + mConList.Person.Suffix 
                  {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                     GetStringFromAttribute( sb_szString, mConList, "Person", "LastName" );
                  szString = sb_szString.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, ", ", 1, 0, 255 );
                  szString = sb_szString.toString( );}
                  {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
                  StringBuilder sb_szTempString_6;
                  if ( szTempString_6 == null )
                     sb_szTempString_6 = new StringBuilder( 32 );
                  else
                     sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                     GetVariableFromAttribute( sb_szTempString_6, mi_lTempInteger_11, 'S', 51, mConList, "Person", "FirstName", "", 0 );
                  lTempInteger_11 = mi_lTempInteger_11.intValue( );
                  szTempString_6 = sb_szTempString_6.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, szTempString_6, 1, 0, 255 );
                  szString = sb_szString.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
                  szString = sb_szString.toString( );}
                  {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
                  StringBuilder sb_szTempString_7;
                  if ( szTempString_7 == null )
                     sb_szTempString_7 = new StringBuilder( 32 );
                  else
                     sb_szTempString_7 = new StringBuilder( szTempString_7 );
                                     GetVariableFromAttribute( sb_szTempString_7, mi_lTempInteger_12, 'S', 51, mConList, "Person", "MiddleName", "", 0 );
                  lTempInteger_12 = mi_lTempInteger_12.intValue( );
                  szTempString_7 = sb_szTempString_7.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, szTempString_7, 1, 0, 255 );
                  szString = sb_szString.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
                  szString = sb_szString.toString( );}
                  {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
                  StringBuilder sb_szTempString_8;
                  if ( szTempString_8 == null )
                     sb_szTempString_8 = new StringBuilder( 32 );
                  else
                     sb_szTempString_8 = new StringBuilder( szTempString_8 );
                                     GetVariableFromAttribute( sb_szTempString_8, mi_lTempInteger_13, 'S', 51, mConList, "Person", "Suffix", "", 0 );
                  lTempInteger_13 = mi_lTempInteger_13.intValue( );
                  szTempString_8 = sb_szTempString_8.toString( );}
                   {StringBuilder sb_szString;
                  if ( szString == null )
                     sb_szString = new StringBuilder( 32 );
                  else
                     sb_szString = new StringBuilder( szString );
                                    ZeidonStringConcat( sb_szString, 1, 0, szTempString_8, 1, 0, 255 );
                  szString = sb_szString.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //: IF mConList.AlumniPerson EXISTS
                  lTempInteger_14 = CheckExistenceOfEntity( mConList, "AlumniPerson" );
                  if ( lTempInteger_14 == 0 )
                  { 
                     //: szString = mConList.AlumniPerson.LastName + ", " +
                     //:            mConList.AlumniPerson.FirstName + " " +
                     //:            mConList.AlumniPerson.MiddleName + " " + mConList.AlumniPerson.Suffix 
                     {StringBuilder sb_szString;
                     if ( szString == null )
                        sb_szString = new StringBuilder( 32 );
                     else
                        sb_szString = new StringBuilder( szString );
                                           GetStringFromAttribute( sb_szString, mConList, "AlumniPerson", "LastName" );
                     szString = sb_szString.toString( );}
                      {StringBuilder sb_szString;
                     if ( szString == null )
                        sb_szString = new StringBuilder( 32 );
                     else
                        sb_szString = new StringBuilder( szString );
                                          ZeidonStringConcat( sb_szString, 1, 0, ", ", 1, 0, 255 );
                     szString = sb_szString.toString( );}
                     {MutableInt mi_lTempInteger_15 = new MutableInt( lTempInteger_15 );
                     StringBuilder sb_szTempString_9;
                     if ( szTempString_9 == null )
                        sb_szTempString_9 = new StringBuilder( 32 );
                     else
                        sb_szTempString_9 = new StringBuilder( szTempString_9 );
                                           GetVariableFromAttribute( sb_szTempString_9, mi_lTempInteger_15, 'S', 51, mConList, "AlumniPerson", "FirstName", "", 0 );
                     lTempInteger_15 = mi_lTempInteger_15.intValue( );
                     szTempString_9 = sb_szTempString_9.toString( );}
                      {StringBuilder sb_szString;
                     if ( szString == null )
                        sb_szString = new StringBuilder( 32 );
                     else
                        sb_szString = new StringBuilder( szString );
                                          ZeidonStringConcat( sb_szString, 1, 0, szTempString_9, 1, 0, 255 );
                     szString = sb_szString.toString( );}
                      {StringBuilder sb_szString;
                     if ( szString == null )
                        sb_szString = new StringBuilder( 32 );
                     else
                        sb_szString = new StringBuilder( szString );
                                          ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
                     szString = sb_szString.toString( );}
                     {MutableInt mi_lTempInteger_16 = new MutableInt( lTempInteger_16 );
                     StringBuilder sb_szTempString_10;
                     if ( szTempString_10 == null )
                        sb_szTempString_10 = new StringBuilder( 32 );
                     else
                        sb_szTempString_10 = new StringBuilder( szTempString_10 );
                                           GetVariableFromAttribute( sb_szTempString_10, mi_lTempInteger_16, 'S', 51, mConList, "AlumniPerson", "MiddleName", "", 0 );
                     lTempInteger_16 = mi_lTempInteger_16.intValue( );
                     szTempString_10 = sb_szTempString_10.toString( );}
                      {StringBuilder sb_szString;
                     if ( szString == null )
                        sb_szString = new StringBuilder( 32 );
                     else
                        sb_szString = new StringBuilder( szString );
                                          ZeidonStringConcat( sb_szString, 1, 0, szTempString_10, 1, 0, 255 );
                     szString = sb_szString.toString( );}
                      {StringBuilder sb_szString;
                     if ( szString == null )
                        sb_szString = new StringBuilder( 32 );
                     else
                        sb_szString = new StringBuilder( szString );
                                          ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
                     szString = sb_szString.toString( );}
                     {MutableInt mi_lTempInteger_17 = new MutableInt( lTempInteger_17 );
                     StringBuilder sb_szTempString_11;
                     if ( szTempString_11 == null )
                        sb_szTempString_11 = new StringBuilder( 32 );
                     else
                        sb_szTempString_11 = new StringBuilder( szTempString_11 );
                                           GetVariableFromAttribute( sb_szTempString_11, mi_lTempInteger_17, 'S', 51, mConList, "AlumniPerson", "Suffix", "", 0 );
                     lTempInteger_17 = mi_lTempInteger_17.intValue( );
                     szTempString_11 = sb_szTempString_11.toString( );}
                      {StringBuilder sb_szString;
                     if ( szString == null )
                        sb_szString = new StringBuilder( 32 );
                     else
                        sb_szString = new StringBuilder( szString );
                                          ZeidonStringConcat( sb_szString, 1, 0, szTempString_11, 1, 0, 255 );
                     szString = sb_szString.toString( );}
                     //:ELSE
                  } 
                  else
                  { 

                     //:       IF mConList.EmployeePerson EXISTS
                     lTempInteger_18 = CheckExistenceOfEntity( mConList, "EmployeePerson" );
                     if ( lTempInteger_18 == 0 )
                     { 
                        //:       szString = mConList.EmployeePerson.LastName + ", " +
                        //:                  mConList.EmployeePerson.FirstName + " " +
                        //:                  mConList.EmployeePerson.MiddleName + " " + mConList.EmployeePerson.Suffix  
                        {StringBuilder sb_szString;
                        if ( szString == null )
                           sb_szString = new StringBuilder( 32 );
                        else
                           sb_szString = new StringBuilder( szString );
                                                 GetStringFromAttribute( sb_szString, mConList, "EmployeePerson", "LastName" );
                        szString = sb_szString.toString( );}
                         {StringBuilder sb_szString;
                        if ( szString == null )
                           sb_szString = new StringBuilder( 32 );
                        else
                           sb_szString = new StringBuilder( szString );
                                                ZeidonStringConcat( sb_szString, 1, 0, ", ", 1, 0, 255 );
                        szString = sb_szString.toString( );}
                        {MutableInt mi_lTempInteger_19 = new MutableInt( lTempInteger_19 );
                        StringBuilder sb_szTempString_12;
                        if ( szTempString_12 == null )
                           sb_szTempString_12 = new StringBuilder( 32 );
                        else
                           sb_szTempString_12 = new StringBuilder( szTempString_12 );
                                                 GetVariableFromAttribute( sb_szTempString_12, mi_lTempInteger_19, 'S', 51, mConList, "EmployeePerson", "FirstName", "", 0 );
                        lTempInteger_19 = mi_lTempInteger_19.intValue( );
                        szTempString_12 = sb_szTempString_12.toString( );}
                         {StringBuilder sb_szString;
                        if ( szString == null )
                           sb_szString = new StringBuilder( 32 );
                        else
                           sb_szString = new StringBuilder( szString );
                                                ZeidonStringConcat( sb_szString, 1, 0, szTempString_12, 1, 0, 255 );
                        szString = sb_szString.toString( );}
                         {StringBuilder sb_szString;
                        if ( szString == null )
                           sb_szString = new StringBuilder( 32 );
                        else
                           sb_szString = new StringBuilder( szString );
                                                ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
                        szString = sb_szString.toString( );}
                        {MutableInt mi_lTempInteger_20 = new MutableInt( lTempInteger_20 );
                        StringBuilder sb_szTempString_13;
                        if ( szTempString_13 == null )
                           sb_szTempString_13 = new StringBuilder( 32 );
                        else
                           sb_szTempString_13 = new StringBuilder( szTempString_13 );
                                                 GetVariableFromAttribute( sb_szTempString_13, mi_lTempInteger_20, 'S', 51, mConList, "EmployeePerson", "MiddleName", "", 0 );
                        lTempInteger_20 = mi_lTempInteger_20.intValue( );
                        szTempString_13 = sb_szTempString_13.toString( );}
                         {StringBuilder sb_szString;
                        if ( szString == null )
                           sb_szString = new StringBuilder( 32 );
                        else
                           sb_szString = new StringBuilder( szString );
                                                ZeidonStringConcat( sb_szString, 1, 0, szTempString_13, 1, 0, 255 );
                        szString = sb_szString.toString( );}
                         {StringBuilder sb_szString;
                        if ( szString == null )
                           sb_szString = new StringBuilder( 32 );
                        else
                           sb_szString = new StringBuilder( szString );
                                                ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
                        szString = sb_szString.toString( );}
                        {MutableInt mi_lTempInteger_21 = new MutableInt( lTempInteger_21 );
                        StringBuilder sb_szTempString_14;
                        if ( szTempString_14 == null )
                           sb_szTempString_14 = new StringBuilder( 32 );
                        else
                           sb_szTempString_14 = new StringBuilder( szTempString_14 );
                                                 GetVariableFromAttribute( sb_szTempString_14, mi_lTempInteger_21, 'S', 51, mConList, "EmployeePerson", "Suffix", "", 0 );
                        lTempInteger_21 = mi_lTempInteger_21.intValue( );
                        szTempString_14 = sb_szTempString_14.toString( );}
                         {StringBuilder sb_szString;
                        if ( szString == null )
                           sb_szString = new StringBuilder( 32 );
                        else
                           sb_szString = new StringBuilder( szString );
                                                ZeidonStringConcat( sb_szString, 1, 0, szTempString_14, 1, 0, 255 );
                        szString = sb_szString.toString( );}
                        //:    ELSE
                     } 
                     else
                     { 
                        //:       IF mConList.ContactListItemStudentPerson EXISTS
                        lTempInteger_22 = CheckExistenceOfEntity( mConList, "ContactListItemStudentPerson" );
                        if ( lTempInteger_22 == 0 )
                        { 
                           //:       szString = mConList.ContactListItemStudentPerson.LastName + ", " +
                           //:                  mConList.ContactListItemStudentPerson.FirstName + " " +
                           //:                  mConList.ContactListItemStudentPerson.MiddleName + " " + mConList.ContactListItemStudentPerson.Suffix  
                           {StringBuilder sb_szString;
                           if ( szString == null )
                              sb_szString = new StringBuilder( 32 );
                           else
                              sb_szString = new StringBuilder( szString );
                                                       GetStringFromAttribute( sb_szString, mConList, "ContactListItemStudentPerson", "LastName" );
                           szString = sb_szString.toString( );}
                            {StringBuilder sb_szString;
                           if ( szString == null )
                              sb_szString = new StringBuilder( 32 );
                           else
                              sb_szString = new StringBuilder( szString );
                                                      ZeidonStringConcat( sb_szString, 1, 0, ", ", 1, 0, 255 );
                           szString = sb_szString.toString( );}
                           {MutableInt mi_lTempInteger_23 = new MutableInt( lTempInteger_23 );
                           StringBuilder sb_szTempString_15;
                           if ( szTempString_15 == null )
                              sb_szTempString_15 = new StringBuilder( 32 );
                           else
                              sb_szTempString_15 = new StringBuilder( szTempString_15 );
                                                       GetVariableFromAttribute( sb_szTempString_15, mi_lTempInteger_23, 'S', 51, mConList, "ContactListItemStudentPerson", "FirstName", "", 0 );
                           lTempInteger_23 = mi_lTempInteger_23.intValue( );
                           szTempString_15 = sb_szTempString_15.toString( );}
                            {StringBuilder sb_szString;
                           if ( szString == null )
                              sb_szString = new StringBuilder( 32 );
                           else
                              sb_szString = new StringBuilder( szString );
                                                      ZeidonStringConcat( sb_szString, 1, 0, szTempString_15, 1, 0, 255 );
                           szString = sb_szString.toString( );}
                            {StringBuilder sb_szString;
                           if ( szString == null )
                              sb_szString = new StringBuilder( 32 );
                           else
                              sb_szString = new StringBuilder( szString );
                                                      ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
                           szString = sb_szString.toString( );}
                           {MutableInt mi_lTempInteger_24 = new MutableInt( lTempInteger_24 );
                           StringBuilder sb_szTempString_16;
                           if ( szTempString_16 == null )
                              sb_szTempString_16 = new StringBuilder( 32 );
                           else
                              sb_szTempString_16 = new StringBuilder( szTempString_16 );
                                                       GetVariableFromAttribute( sb_szTempString_16, mi_lTempInteger_24, 'S', 51, mConList, "ContactListItemStudentPerson", "MiddleName", "", 0 );
                           lTempInteger_24 = mi_lTempInteger_24.intValue( );
                           szTempString_16 = sb_szTempString_16.toString( );}
                            {StringBuilder sb_szString;
                           if ( szString == null )
                              sb_szString = new StringBuilder( 32 );
                           else
                              sb_szString = new StringBuilder( szString );
                                                      ZeidonStringConcat( sb_szString, 1, 0, szTempString_16, 1, 0, 255 );
                           szString = sb_szString.toString( );}
                            {StringBuilder sb_szString;
                           if ( szString == null )
                              sb_szString = new StringBuilder( 32 );
                           else
                              sb_szString = new StringBuilder( szString );
                                                      ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
                           szString = sb_szString.toString( );}
                           {MutableInt mi_lTempInteger_25 = new MutableInt( lTempInteger_25 );
                           StringBuilder sb_szTempString_17;
                           if ( szTempString_17 == null )
                              sb_szTempString_17 = new StringBuilder( 32 );
                           else
                              sb_szTempString_17 = new StringBuilder( szTempString_17 );
                                                       GetVariableFromAttribute( sb_szTempString_17, mi_lTempInteger_25, 'S', 51, mConList, "ContactListItemStudentPerson", "Suffix", "", 0 );
                           lTempInteger_25 = mi_lTempInteger_25.intValue( );
                           szTempString_17 = sb_szTempString_17.toString( );}
                            {StringBuilder sb_szString;
                           if ( szString == null )
                              sb_szString = new StringBuilder( 32 );
                           else
                              sb_szString = new StringBuilder( szString );
                                                      ZeidonStringConcat( sb_szString, 1, 0, szTempString_17, 1, 0, 255 );
                           szString = sb_szString.toString( );}
                           //:    ELSE
                        } 
                        else
                        { 
                           //:       szString = ""
                            {StringBuilder sb_szString;
                           if ( szString == null )
                              sb_szString = new StringBuilder( 32 );
                           else
                              sb_szString = new StringBuilder( szString );
                                                      ZeidonStringCopy( sb_szString, 1, 0, "", 1, 0, 255 );
                           szString = sb_szString.toString( );}
                        } 

                        //:       END
                     } 

                     //:       END
                  } 

                  //: END
               } 

               //: END
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mConList,
         //:                      InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mConList, InternalEntityStructure, InternalAttribStructure, szString );
         break ;

      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END /* case */
   return( 0 );
// END
} 


private int 
omConList_fnLocalBuildQual_29( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "N" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_30( View     vSubtask,
                               zVIEW    vQualObject,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_31( View     vSubtask,
                               zVIEW    vQualObject,
                               int      lTempInteger_4 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Event" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Event" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_4 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_32( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "I" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_33( View     vSubtask,
                               zVIEW    vQualObject,
                               int      lTempInteger_0,
                               int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "I" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_34( View     vSubtask,
                               zVIEW    vQualObject,
                               int      lTempInteger_1,
                               int      UserID,
                               int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "T" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactListCategory" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_35( View     vSubtask,
                               zVIEW    vQualObject,
                               int      lTempInteger_3,
                               int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "T" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactListCategory" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "NOT EXISTS" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_36( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "T" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_26( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_27( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "X" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_28( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "F" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_23( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "D" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_24( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "L" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_25( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "U" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_20( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "C" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_21( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "S" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_22( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "O" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_17( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "R" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_18( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "E" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_19( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "A" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_0( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_1,
                              int      UserID,
                              int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "P" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactListCategory" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_1( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_3,
                              int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "P" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactListCategory" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "NOT EXISTS" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_2( View     vSubtask,
                              zVIEW    vQualObject,
                              int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "R" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_3( View     vSubtask,
                              zVIEW    vQualObject,
                              int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "E" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_4( View     vSubtask,
                              zVIEW    vQualObject,
                              int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "A" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_5( View     vSubtask,
                              zVIEW    vQualObject,
                              int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "C" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_6( View     vSubtask,
                              zVIEW    vQualObject,
                              int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "S" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_7( View     vSubtask,
                              zVIEW    vQualObject,
                              int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "O" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_8( View     vSubtask,
                              zVIEW    vQualObject,
                              int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "D" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_9( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_1,
                              int      UserID,
                              int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "X" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactListCategory" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_10( View     vSubtask,
                               zVIEW    vQualObject,
                               int      lTempInteger_3,
                               int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "X" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactListCategory" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "NOT EXISTS" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_11( View     vSubtask,
                               zVIEW    vQualObject,
                               int      lTempInteger_0,
                               int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "L" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_12( View     vSubtask,
                               zVIEW    vQualObject,
                               int      lTempInteger_0,
                               int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "U" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_13( View     vSubtask,
                               zVIEW    vQualObject,
                               int      lTempInteger_0,
                               int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "AdministrativeDivision" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_14( View     vSubtask,
                               zVIEW    vQualObject,
                               int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "F" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_15( View     vSubtask,
                               zVIEW    vQualObject,
                               int      UserID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "N" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", UserID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omConList_fnLocalBuildQual_16( View     vSubtask,
                               zVIEW    vQualObject,
                               String   szListName,
                               int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ContactList" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ListName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szListName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ContactList" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Type" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "P" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DERIVED ATTRIBUTE OPERATION
//:dSortName( VIEW mConList BASED ON LOD mConList,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   STRING (  254  ) szString
public int 
omConList_dSortName( View     mConList,
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
   String   szTempString_2 = null;
   int      lTempInteger_3 = 0;



   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:IF mConList.ContactListItemStudentPerson EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mConList, "ContactListItemStudentPerson" );
         if ( lTempInteger_0 == 0 )
         { 
            //:                      szString = mConList.ContactListItemStudentPerson.LastName + ", " +
            //:                                 mConList.ContactListItemStudentPerson.FirstName + " " +
            //:                                 mConList.ContactListItemStudentPerson.MiddleName + " " + mConList.ContactListItemStudentPerson.Suffix  
            {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetStringFromAttribute( sb_szString, mConList, "ContactListItemStudentPerson", "LastName" );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, ", ", 1, 0, 255 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 51, mConList, "ContactListItemStudentPerson", "FirstName", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 255 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 51, mConList, "ContactListItemStudentPerson", "MiddleName", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_1, 1, 0, 255 );
            szString = sb_szString.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, " ", 1, 0, 255 );
            szString = sb_szString.toString( );}
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_3, 'S', 51, mConList, "ContactListItemStudentPerson", "Suffix", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringConcat( sb_szString, 1, 0, szTempString_2, 1, 0, 255 );
            szString = sb_szString.toString( );}
            //:ELSE szString=""
         } 
         else
         { 
             {StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                        ZeidonStringCopy( sb_szString, 1, 0, "", 1, 0, 255 );
            szString = sb_szString.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mConList,
         //:                      InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mConList, InternalEntityStructure, InternalAttribStructure, szString );
         break ;

      //:   /* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:      /* end zDERIVED_SET */
   //:END /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListI( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser    BASED ON LOD mUser,
//:                        STRING ( 50 ) szListName,
//:                        STRING ( 32 ) szFindString )
//:   VIEW mConListMain BASED ON LOD mConList
public int 
omConList_ActivateCreateConListI( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   zVIEW    mConListMain = new zVIEW( );
   //:INTEGER nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mConList
   //:   WHERE mConList.ContactList.ListName = szListName
   //:     AND mConList.ContactList.Type = "I"
   //:     AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_32( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "I"
      SetAttributeFromString( mConList, "ContactList", "Type", "I" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dDonorName( VIEW mConList BASED ON LOD mConList,
//:            STRING ( 32 ) InternalEntityStructure,
//:            STRING ( 32 ) InternalAttribStructure,
//:            SHORT GetOrSetFlag )

//:   STRING (  254  ) szString
public int 
omConList_dDonorName( View     mConList,
                      String InternalEntityStructure,
                      String InternalAttribStructure,
                      Integer   GetOrSetFlag )
{
   String   szString = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:szString = "(Empty)"
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringCopy( sb_szString, 1, 0, "(Empty)", 1, 0, 255 );
         szString = sb_szString.toString( );}
         //:IF mConList.DonorPerson EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mConList, "DonorPerson" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szString = mConList.DonorPerson.dFullNameLFM
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_1, 'S', 255, mConList, "DonorPerson", "dFullNameLFM", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szString = sb_szString.toString( );}
         } 

         //:END
         //:IF mConList.DonorChurch EXISTS
         lTempInteger_2 = CheckExistenceOfEntity( mConList, "DonorChurch" );
         if ( lTempInteger_2 == 0 )
         { 
            //:szString = mConList.DonorChurch.Name
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_3, 'S', 255, mConList, "DonorChurch", "Name", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szString = sb_szString.toString( );}
         } 

         //:END
         //:IF mConList.DonorOrganization EXISTS
         lTempInteger_4 = CheckExistenceOfEntity( mConList, "DonorOrganization" );
         if ( lTempInteger_4 == 0 )
         { 
            //:szString = mConList.DonorOrganization.Name
            {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
            StringBuilder sb_szString;
            if ( szString == null )
               sb_szString = new StringBuilder( 32 );
            else
               sb_szString = new StringBuilder( szString );
                         GetVariableFromAttribute( sb_szString, mi_lTempInteger_5, 'S', 255, mConList, "DonorOrganization", "Name", "", 0 );
            lTempInteger_5 = mi_lTempInteger_5.intValue( );
            szString = sb_szString.toString( );}
         } 

         //:END
         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mConList,
         //:                      InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mConList, InternalEntityStructure, InternalAttribStructure, szString );
         break ;

      //:/* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:   /* end zDERIVED_SET */
   //:   END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dClassName( VIEW mConList BASED ON LOD mConList,
//:            STRING ( 32 ) InternalEntityStructure,
//:            STRING ( 32 ) InternalAttribStructure,
//:            SHORT GetOrSetFlag )

//:   STRING ( 100 ) szName
public int 
omConList_dClassName( View     mConList,
                      String InternalEntityStructure,
                      String InternalAttribStructure,
                      Integer   GetOrSetFlag )
{
   String   szName = null;
   //:STRING ( 50 )  szBaseName
   String   szBaseName = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_3 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_5 = 0;
   String   szTempString_4 = null;
   int      lTempInteger_6 = 0;
   String   szTempString_5 = null;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF mConList.CollegeTerm EXISTS 
         lTempInteger_0 = CheckExistenceOfEntity( mConList, "CollegeTerm" );
         if ( lTempInteger_0 == 0 )
         { 
            //:// Class is for Term.
            //:GetStringFromAttributeByContext( szBaseName, mConList, "CollegeTerm", "YearSemester", "", 50 )
            {StringBuilder sb_szBaseName;
            if ( szBaseName == null )
               sb_szBaseName = new StringBuilder( 32 );
            else
               sb_szBaseName = new StringBuilder( szBaseName );
                         GetStringFromAttributeByContext( sb_szBaseName, mConList, "CollegeTerm", "YearSemester", "", 50 );
            szBaseName = sb_szBaseName.toString( );}
            //:IF mConList.CourseTopic EXISTS
            lTempInteger_1 = CheckExistenceOfEntity( mConList, "CourseTopic" );
            if ( lTempInteger_1 == 0 )
            { 
               //:szName = szBaseName + " - " + mConList.ClassCourse.Number + "-" + mConList.CourseTopic.Number + mConList.Class.Section
                {StringBuilder sb_szName;
               if ( szName == null )
                  sb_szName = new StringBuilder( 32 );
               else
                  sb_szName = new StringBuilder( szName );
                              ZeidonStringCopy( sb_szName, 1, 0, szBaseName, 1, 0, 101 );
               szName = sb_szName.toString( );}
                {StringBuilder sb_szName;
               if ( szName == null )
                  sb_szName = new StringBuilder( 32 );
               else
                  sb_szName = new StringBuilder( szName );
                              ZeidonStringConcat( sb_szName, 1, 0, " - ", 1, 0, 101 );
               szName = sb_szName.toString( );}
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 11, mConList, "ClassCourse", "Number", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szName;
               if ( szName == null )
                  sb_szName = new StringBuilder( 32 );
               else
                  sb_szName = new StringBuilder( szName );
                              ZeidonStringConcat( sb_szName, 1, 0, szTempString_0, 1, 0, 101 );
               szName = sb_szName.toString( );}
                {StringBuilder sb_szName;
               if ( szName == null )
                  sb_szName = new StringBuilder( 32 );
               else
                  sb_szName = new StringBuilder( szName );
                              ZeidonStringConcat( sb_szName, 1, 0, "-", 1, 0, 101 );
               szName = sb_szName.toString( );}
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_3, 'S', 11, mConList, "CourseTopic", "Number", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szTempString_1 = sb_szTempString_1.toString( );}
                {StringBuilder sb_szName;
               if ( szName == null )
                  sb_szName = new StringBuilder( 32 );
               else
                  sb_szName = new StringBuilder( szName );
                              ZeidonStringConcat( sb_szName, 1, 0, szTempString_1, 1, 0, 101 );
               szName = sb_szName.toString( );}
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szTempString_2;
               if ( szTempString_2 == null )
                  sb_szTempString_2 = new StringBuilder( 32 );
               else
                  sb_szTempString_2 = new StringBuilder( szTempString_2 );
                               GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_4, 'S', 3, mConList, "Class", "Section", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szTempString_2 = sb_szTempString_2.toString( );}
                {StringBuilder sb_szName;
               if ( szName == null )
                  sb_szName = new StringBuilder( 32 );
               else
                  sb_szName = new StringBuilder( szName );
                              ZeidonStringConcat( sb_szName, 1, 0, szTempString_2, 1, 0, 101 );
               szName = sb_szName.toString( );}
               //:ELSE
            } 
            else
            { 
               //:IF mConList.Class.Section = ""
               if ( CompareAttributeToString( mConList, "Class", "Section", "" ) == 0 )
               { 
                  //:szName = szBaseName + " - " + mConList.ClassCourse.Number
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringCopy( sb_szName, 1, 0, szBaseName, 1, 0, 101 );
                  szName = sb_szName.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, " - ", 1, 0, 101 );
                  szName = sb_szName.toString( );}
                  {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                  StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                     GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_5, 'S', 11, mConList, "ClassCourse", "Number", "", 0 );
                  lTempInteger_5 = mi_lTempInteger_5.intValue( );
                  szTempString_3 = sb_szTempString_3.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, szTempString_3, 1, 0, 101 );
                  szName = sb_szName.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szName = szBaseName + " - " + mConList.ClassCourse.Number + "-" + mConList.Class.Section
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringCopy( sb_szName, 1, 0, szBaseName, 1, 0, 101 );
                  szName = sb_szName.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, " - ", 1, 0, 101 );
                  szName = sb_szName.toString( );}
                  {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                  StringBuilder sb_szTempString_4;
                  if ( szTempString_4 == null )
                     sb_szTempString_4 = new StringBuilder( 32 );
                  else
                     sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                     GetVariableFromAttribute( sb_szTempString_4, mi_lTempInteger_6, 'S', 11, mConList, "ClassCourse", "Number", "", 0 );
                  lTempInteger_6 = mi_lTempInteger_6.intValue( );
                  szTempString_4 = sb_szTempString_4.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, szTempString_4, 1, 0, 101 );
                  szName = sb_szName.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, "-", 1, 0, 101 );
                  szName = sb_szName.toString( );}
                  {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                  StringBuilder sb_szTempString_5;
                  if ( szTempString_5 == null )
                     sb_szTempString_5 = new StringBuilder( 32 );
                  else
                     sb_szTempString_5 = new StringBuilder( szTempString_5 );
                                     GetVariableFromAttribute( sb_szTempString_5, mi_lTempInteger_7, 'S', 3, mConList, "Class", "Section", "", 0 );
                  lTempInteger_7 = mi_lTempInteger_7.intValue( );
                  szTempString_5 = sb_szTempString_5.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, szTempString_5, 1, 0, 101 );
                  szName = sb_szName.toString( );}
               } 

               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 

            //:szName = mConList.ClassCourse.Number
            {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
            StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
            GetVariableFromAttribute( sb_szName, mi_lTempInteger_8, 'S', 101, mConList, "ClassCourse", "Number", "", 0 );
            lTempInteger_8 = mi_lTempInteger_8.intValue( );
            szName = sb_szName.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mConList,
         //:                   InternalEntityStructure, InternalAttribStructure, szName )
         StoreStringInRecord( mConList, InternalEntityStructure, InternalAttribStructure, szName );
         break ;

      //:/* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:   /* end zDERIVED_SET */
   //:   END  /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateConListMainP( VIEW mConList BASED ON LOD mConList,
//:                      VIEW ViewToWindow,
//:                      INTEGER UserID )
//:                      
//:   VIEW mUserCat REGISTERED AS mUserCat
public int 
omConList_ActivateConListMainP( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    mUserCat = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( mUserCat, "mUserCat", ViewToWindow, zLEVEL_TASK );

   //:// mUserCat is the object that contains the user's ContactList Categories. There is an
   //:// activate for when a User has Categories and another for when she doesn't.
   //:IF mUserCat.CurrentContactListCategory EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mUserCat, "CurrentContactListCategory" );
   if ( lTempInteger_0 == 0 )
   { 
      //:ACTIVATE mConList MULTIPLE
      //:        WHERE mConList.ContactList.Type = "P"
      //:          AND mConList.AdministrativeDivision.ID = mUserCat.CurrentAdministrativeDivision.ID
      //:          AND mConList.User.ID = UserID
      //:          AND mConList.ContactListCategory.ID = mUserCat.CurrentContactListCategory.ID 
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, mUserCat, "CurrentAdministrativeDivision", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
             GetIntegerFromAttribute( mi_lTempInteger_2, mUserCat, "CurrentContactListCategory", "ID" );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
      omConList_fnLocalBuildQual_0( ViewToWindow, vTempViewVar_0, lTempInteger_1, UserID, lTempInteger_2 );
      RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
      DropView( vTempViewVar_0 );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE mConList MULTIPLE
      //:        WHERE mConList.ContactList.Type = "P"
      //:          AND mConList.AdministrativeDivision.ID = mUserCat.CurrentAdministrativeDivision.ID
      //:          AND mConList.User.ID = UserID
      //:          AND mConList.ContactListCategory DOES NOT EXIST
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
             GetIntegerFromAttribute( mi_lTempInteger_3, mUserCat, "CurrentAdministrativeDivision", "ID" );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );}
      omConList_fnLocalBuildQual_1( ViewToWindow, vTempViewVar_1, lTempInteger_3, UserID );
      RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_1, zMULTIPLE );
      DropView( vTempViewVar_1 );
   } 

   //:END
   //:NAME VIEW mConList "mConListP"
   SetNameForView( mConList, "mConListP", null, zLEVEL_TASK );

   //:FOR EACH mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mConList, "Prospect", "ProspectPerson.LastName A ProspectPerson.FirstName A"  )
      OrderEntityForView( mConList, "Prospect", "ProspectPerson.LastName A ProspectPerson.FirstName A" );
      RESULT = SetCursorNextEntity( mConList, "ContactList", "" );
   } 

   //:END
   //:OrderEntityForView( mConList, "ContactList", "ListName A"  )
   OrderEntityForView( mConList, "ContactList", "ListName A" );
   //:SET CURSOR FIRST mConList.ContactList  
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omConList_ActivateConListMainR( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateConListMainR( VIEW mConList BASED ON LOD mConList,
   //:                   VIEW ViewToWindow,
   //:                   INTEGER UserID )

   //:ACTIVATE mConList MULTIPLE
   //:        WHERE mConList.ContactList.Type = "R"
   //:          AND mConList.User.ID = UserID
   omConList_fnLocalBuildQual_2( ViewToWindow, vTempViewVar_0, UserID );
   RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:      
   //:NAME VIEW mConList "mConListR"
   SetNameForView( mConList, "mConListR", null, zLEVEL_TASK );

   //:FOR EACH mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mConList, "Person", "dFullNameLFM A"  )
      OrderEntityForView( mConList, "Person", "dFullNameLFM A" );
      RESULT = SetCursorNextEntity( mConList, "ContactList", "" );
   } 

   //:END
   //:OrderEntityForView( mConList, "ContactList", "ListName A"  )
   OrderEntityForView( mConList, "ContactList", "ListName A" );
   //:SET CURSOR FIRST mConList.ContactList
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omConList_ActivateConListMainE( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateConListMainE( VIEW mConList BASED ON LOD mConList,
   //:                   VIEW ViewToWindow,
   //:                   INTEGER UserID )

   //:ACTIVATE mConList MULTIPLE
   //:        WHERE mConList.ContactList.Type = "E"
   //:          AND mConList.User.ID = UserID
   omConList_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, UserID );
   RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mConList "mConListE"
   SetNameForView( mConList, "mConListE", null, zLEVEL_TASK );

   //:OrderEntityForView( mConList, "ContactList", "ListName A"  )
   OrderEntityForView( mConList, "ContactList", "ListName A" );
   //:SET CURSOR FIRST mConList.ContactList
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omConList_ActivateConListMainA( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateConListMainA( VIEW mConList BASED ON LOD mConList,
   //:                   VIEW ViewToWindow,
   //:                   INTEGER UserID )

   //:ACTIVATE mConList MULTIPLE
   //:        WHERE mConList.ContactList.Type = "A"
   //:          AND mConList.User.ID = UserID
   omConList_fnLocalBuildQual_4( ViewToWindow, vTempViewVar_0, UserID );
   RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mConList "mConListA"
   SetNameForView( mConList, "mConListA", null, zLEVEL_TASK );

   //:FOR EACH mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mConList, "Alumni", "dName A"  )
      OrderEntityForView( mConList, "Alumni", "dName A" );
      RESULT = SetCursorNextEntity( mConList, "ContactList", "" );
   } 

   //:END
   //:OrderEntityForView( mConList, "ContactList", "ListName A"  )
   OrderEntityForView( mConList, "ContactList", "ListName A" );
   //:SET CURSOR FIRST mConList.ContactList
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omConList_ActivateConListMainC( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateConListMainC( VIEW mConList BASED ON LOD mConList,
   //:                   VIEW ViewToWindow,
   //:                   INTEGER UserID )

   //:ACTIVATE mConList MULTIPLE
   //:        WHERE mConList.ContactList.Type = "C"
   //:          AND mConList.User.ID = UserID
   omConList_fnLocalBuildQual_5( ViewToWindow, vTempViewVar_0, UserID );
   RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mConList "mConListC"
   SetNameForView( mConList, "mConListC", null, zLEVEL_TASK );

   //:FOR EACH mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mConList, "Church", "Name A"  )
      OrderEntityForView( mConList, "Church", "Name A" );
      RESULT = SetCursorNextEntity( mConList, "ContactList", "" );
   } 

   //:END
   //:OrderEntityForView( mConList, "ContactList", "ListName A"  )
   OrderEntityForView( mConList, "ContactList", "ListName A" );
   //:SET CURSOR FIRST mConList.ContactList
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omConList_ActivateConListMainS( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateConListMainS( VIEW mConList BASED ON LOD mConList,
   //:                   VIEW ViewToWindow,
   //:                   INTEGER UserID )

   //:ACTIVATE mConList MULTIPLE
   //:        WHERE mConList.ContactList.Type = "S"
   //:          AND mConList.User.ID = UserID
   omConList_fnLocalBuildQual_6( ViewToWindow, vTempViewVar_0, UserID );
   RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mConList "mConListS"
   SetNameForView( mConList, "mConListS", null, zLEVEL_TASK );

   //:FOR EACH mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mConList, "School", "Name A"  )
      OrderEntityForView( mConList, "School", "Name A" );
      RESULT = SetCursorNextEntity( mConList, "ContactList", "" );
   } 

   //:END
   //:OrderEntityForView( mConList, "ContactList", "ListName A"  )
   OrderEntityForView( mConList, "ContactList", "ListName A" );
   //:SET CURSOR FIRST mConList.ContactList
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omConList_ActivateConListMainO( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateConListMainO( VIEW mConList BASED ON LOD mConList,
   //:                   VIEW ViewToWindow,
   //:                   INTEGER UserID )

   //:ACTIVATE mConList MULTIPLE
   //:        WHERE mConList.ContactList.Type = "O"
   //:          AND mConList.User.ID = UserID
   omConList_fnLocalBuildQual_7( ViewToWindow, vTempViewVar_0, UserID );
   RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mConList "mConListO"
   SetNameForView( mConList, "mConListO", null, zLEVEL_TASK );

   //:FOR EACH mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mConList, "Organization", "Name A"  )
      OrderEntityForView( mConList, "Organization", "Name A" );
      RESULT = SetCursorNextEntity( mConList, "ContactList", "" );
   } 

   //:END
   //:OrderEntityForView( mConList, "ContactList", "ListName A"  )
   OrderEntityForView( mConList, "ContactList", "ListName A" );
   //:SET CURSOR FIRST mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omConList_ActivateConListMainD( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateConListMainD( VIEW mConList BASED ON LOD mConList,
   //:                   VIEW ViewToWindow,
   //:                   INTEGER UserID )

   //:ACTIVATE mConList MULTIPLE
   //:        WHERE mConList.ContactList.Type = "D"
   //:          AND mConList.User.ID = UserID
   omConList_fnLocalBuildQual_8( ViewToWindow, vTempViewVar_0, UserID );
   RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mConList "mConListD"
   SetNameForView( mConList, "mConListD", null, zLEVEL_TASK );

   //:FOR EACH mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mConList, "Donor", "dDonorName A"  )
      OrderEntityForView( mConList, "Donor", "dDonorName A" );
      RESULT = SetCursorNextEntity( mConList, "ContactList", "" );
   } 

   //:END
   //:OrderEntityForView( mConList, "ContactList", "ListName A"  )
   OrderEntityForView( mConList, "ContactList", "ListName A" );
   //:SET CURSOR FIRST mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateConListMainX( VIEW mConList BASED ON LOD mConList,
//:                      VIEW ViewToWindow,
//:                      INTEGER UserID )
//:   
//:   VIEW mUserCat REGISTERED AS mUserCat
public int 
omConList_ActivateConListMainX( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    mUserCat = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( mUserCat, "mUserCat", ViewToWindow, zLEVEL_TASK );
   //:   
   //:// mUserCat is the object that contains the user's ContactList Categories. There is an
   //:// activate for when a User has Categories and another for when she doesn't.
   //:IF mUserCat.CurrentContactListCategory EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mUserCat, "CurrentContactListCategory" );
   if ( lTempInteger_0 == 0 )
   { 
      //:ACTIVATE mConList MULTIPLE
      //:        WHERE mConList.ContactList.Type = "X"
      //:          AND mConList.AdministrativeDivision.ID = mUserCat.CurrentAdministrativeDivision.ID
      //:          AND mConList.User.ID = UserID
      //:          AND mConList.ContactListCategory.ID = mUserCat.CurrentContactListCategory.ID 
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, mUserCat, "CurrentAdministrativeDivision", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
             GetIntegerFromAttribute( mi_lTempInteger_2, mUserCat, "CurrentContactListCategory", "ID" );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
      omConList_fnLocalBuildQual_9( ViewToWindow, vTempViewVar_0, lTempInteger_1, UserID, lTempInteger_2 );
      RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
      DropView( vTempViewVar_0 );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE mConList MULTIPLE
      //:        WHERE mConList.ContactList.Type = "X"
      //:          AND mConList.AdministrativeDivision.ID = mUserCat.CurrentAdministrativeDivision.ID
      //:          AND mConList.User.ID = UserID
      //:          AND mConList.ContactListCategory DOES NOT EXIST
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
             GetIntegerFromAttribute( mi_lTempInteger_3, mUserCat, "CurrentAdministrativeDivision", "ID" );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );}
      omConList_fnLocalBuildQual_10( ViewToWindow, vTempViewVar_1, lTempInteger_3, UserID );
      RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_1, zMULTIPLE );
      DropView( vTempViewVar_1 );
   } 

   //:END
   //:NAME VIEW mConList "mConListX"
   SetNameForView( mConList, "mConListX", null, zLEVEL_TASK );

   //:OrderEntityForView( mConList, "ContactList", "ListName A"  )
   OrderEntityForView( mConList, "ContactList", "ListName A" );
   //:SET CURSOR FIRST mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateConListMainT( VIEW mConList BASED ON LOD mConList,
//:                      VIEW ViewToWindow,
//:                      INTEGER UserID )
//: 
//:   VIEW mUserCat REGISTERED AS mUserCat
public int 
omConList_ActivateConListMainT( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    mUserCat = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( mUserCat, "mUserCat", ViewToWindow, zLEVEL_TASK );

   //:// mUserCat is the object that contains the user's ContactList Categories. There is an
   //:// activate for when a User has Categories and another for when she doesn't.
   //:IF mUserCat.CurrentContactListCategory EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mUserCat, "CurrentContactListCategory" );
   if ( lTempInteger_0 == 0 )
   { 
      //:ACTIVATE mConList MULTIPLE
      //:        WHERE mConList.ContactList.Type = "T"
      //:          AND mConList.AdministrativeDivision.ID = mUserCat.CurrentAdministrativeDivision.ID
      //:          AND mConList.User.ID = UserID
      //:          AND mConList.ContactListCategory.ID = mUserCat.CurrentContactListCategory.ID 
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, mUserCat, "CurrentAdministrativeDivision", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
             GetIntegerFromAttribute( mi_lTempInteger_2, mUserCat, "CurrentContactListCategory", "ID" );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
      omConList_fnLocalBuildQual_34( ViewToWindow, vTempViewVar_0, lTempInteger_1, UserID, lTempInteger_2 );
      RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
      DropView( vTempViewVar_0 );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE mConList MULTIPLE
      //:        WHERE mConList.ContactList.Type = "T"
      //:          AND mConList.AdministrativeDivision.ID = mUserCat.CurrentAdministrativeDivision.ID
      //:          AND mConList.User.ID = UserID
      //:          AND mConList.ContactListCategory DOES NOT EXIST
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
             GetIntegerFromAttribute( mi_lTempInteger_3, mUserCat, "CurrentAdministrativeDivision", "ID" );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );}
      omConList_fnLocalBuildQual_35( ViewToWindow, vTempViewVar_1, lTempInteger_3, UserID );
      RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_1, zMULTIPLE );
      DropView( vTempViewVar_1 );
   } 

   //:END
   //:NAME VIEW mConList "mConListT"
   SetNameForView( mConList, "mConListT", null, zLEVEL_TASK );

   //:FOR EACH mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mConList, "Person", "Person.LastName A Person.FirstName A"  )
      OrderEntityForView( mConList, "Person", "Person.LastName A Person.FirstName A" );
      RESULT = SetCursorNextEntity( mConList, "ContactList", "" );
   } 

   //:END
   //:OrderEntityForView( mConList, "ContactList", "ListName A"  )
   OrderEntityForView( mConList, "ContactList", "ListName A" );
   //:SET CURSOR FIRST mConList.ContactList  
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateConListMainL( VIEW mConList BASED ON LOD mConList,
//:                      VIEW ViewToWindow,
//:                      INTEGER UserID )
//:   VIEW mUser BASED ON LOD mUser
public int 
omConList_ActivateConListMainL( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    mUser = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_TASK )
   GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_TASK );
   //:ACTIVATE mConList MULTIPLE
   //:        WHERE mConList.ContactList.Type = "L"
   //:          AND mConList.AdministrativeDivision.ID = mUser.CurrentAdministrativeDivision.ID
   //:          AND mConList.User.ID = UserID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "CurrentAdministrativeDivision", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_11( ViewToWindow, vTempViewVar_0, lTempInteger_0, UserID );
   RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mConList "mConListL"
   SetNameForView( mConList, "mConListL", null, zLEVEL_TASK );

   //:FOR EACH mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mConList, "Class", "dName A"  )
      OrderEntityForView( mConList, "Class", "dName A" );
      RESULT = SetCursorNextEntity( mConList, "ContactList", "" );
   } 

   //:END
   //:OrderEntityForView( mConList, "ContactList", "ListName A"  )
   OrderEntityForView( mConList, "ContactList", "ListName A" );
   //:SET CURSOR FIRST mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateConListMainU( VIEW mConList BASED ON LOD mConList,
//:                      VIEW ViewToWindow,
//:                      INTEGER UserID )
//:   VIEW mUser BASED ON LOD mUser
public int 
omConList_ActivateConListMainU( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    mUser = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_TASK )
   GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_TASK );
   //:ACTIVATE mConList MULTIPLE
   //:        WHERE mConList.ContactList.Type = "U"
   //:          AND mConList.AdministrativeDivision.ID = mUser.CurrentAdministrativeDivision.ID
   //:          AND mConList.User.ID = UserID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "CurrentAdministrativeDivision", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_12( ViewToWindow, vTempViewVar_0, lTempInteger_0, UserID );
   RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mConList "mConListU"
   SetNameForView( mConList, "mConListU", null, zLEVEL_TASK );

   //:FOR EACH mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mConList, "Course", "Number A"  )
      OrderEntityForView( mConList, "Course", "Number A" );
      RESULT = SetCursorNextEntity( mConList, "ContactList", "" );
   } 

   //:END
   //:SET CURSOR FIRST mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateConListMainY( VIEW mConList BASED ON LOD mConList,
//:                      VIEW ViewToWindow,
//:                      INTEGER UserID )
//:   VIEW mUser BASED ON LOD mUser
public int 
omConList_ActivateConListMainY( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    mUser = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_TASK )
   GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_TASK );
   //:ACTIVATE mConList MULTIPLE
   //:        WHERE mConList.ContactList.Type = "Y"
   //:          AND mConList.AdministrativeDivision.ID = mUser.CurrentAdministrativeDivision.ID
   //:          AND mConList.User.ID = UserID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "CurrentAdministrativeDivision", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_13( ViewToWindow, vTempViewVar_0, lTempInteger_0, UserID );
   RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mConList "mConListY"
   SetNameForView( mConList, "mConListY", null, zLEVEL_TASK );

   //:FOR EACH mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mConList, "Course", "Number A"  )
      OrderEntityForView( mConList, "Course", "Number A" );
      RESULT = SetCursorNextEntity( mConList, "ContactList", "" );
   } 

   //:END
   //:SET CURSOR FIRST mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omConList_ActivateConListMainF( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateConListMainF( VIEW mConList BASED ON LOD mConList,
   //:                   VIEW ViewToWindow,
   //:                   INTEGER UserID )

   //:ACTIVATE mConList MULTIPLE
   //:        WHERE mConList.ContactList.Type = "F"
   //:          AND mConList.User.ID = UserID
   omConList_fnLocalBuildQual_14( ViewToWindow, vTempViewVar_0, UserID );
   RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mConList "mConListF"
   SetNameForView( mConList, "mConListF", null, zLEVEL_TASK );

   //:FOR EACH mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mConList, "Fund", "Name A"  )
      OrderEntityForView( mConList, "Fund", "Name A" );
      RESULT = SetCursorNextEntity( mConList, "ContactList", "" );
   } 

   //:END
   //:OrderEntityForView( mConList, "ContactList", "ListName A"  )
   OrderEntityForView( mConList, "ContactList", "ListName A" );
   //:SET CURSOR FIRST mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omConList_ActivateConListMainN( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateConListMainN( VIEW mConList BASED ON LOD mConList,
   //:                   VIEW ViewToWindow,
   //:                   INTEGER UserID )

   //:ACTIVATE mConList MULTIPLE
   //:        WHERE mConList.ContactList.Type = "N"
   //:          AND mConList.User.ID = UserID
   omConList_fnLocalBuildQual_15( ViewToWindow, vTempViewVar_0, UserID );
   RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );

   //:NAME VIEW mConList "mConListN"
   SetNameForView( mConList, "mConListN", null, zLEVEL_TASK );

   //:FOR EACH mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mConList, "Donation", "dListDisplay A"  )
      OrderEntityForView( mConList, "Donation", "dListDisplay A" );
      RESULT = SetCursorNextEntity( mConList, "ContactList", "" );
   } 

   //:END
   //:OrderEntityForView( mConList, "ContactList", "ListName A"  )
   OrderEntityForView( mConList, "ContactList", "ListName A" );
   //:SET CURSOR FIRST mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListP( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                        STRING ( 1024 ) szFindString )

//:   VIEW mConListMain BASED ON LOD mConList
public int 
omConList_ActivateCreateConListP( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   zVIEW    mConListMain = new zVIEW( );
   //:INTEGER nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mConList
   //:   WHERE mConList.ContactList.ListName = szListName
   //:     AND mConList.ContactList.Type = "P"
   //:     AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_16( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "P"
      SetAttributeFromString( mConList, "ContactList", "Type", "P" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 

   //:   
   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListR( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                        STRING ( 1024 ) szFindString )

//:   VIEW mConListMain BASED ON LOD mConList
public int 
omConList_ActivateCreateConListR( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   zVIEW    mConListMain = new zVIEW( );
   //:INTEGER nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mConList
   //:   WHERE mConList.ContactList.ListName = szListName
   //:     AND mConList.ContactList.Type = "R"
   //:     AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_17( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "R"
      SetAttributeFromString( mConList, "ContactList", "Type", "R" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:      INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 


   //: 
   //:END
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListE( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                        STRING ( 1024 ) szFindString )

//:   VIEW mConListMain BASED ON LOD mConList
public int 
omConList_ActivateCreateConListE( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   zVIEW    mConListMain = new zVIEW( );
   //:INTEGER nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mConList
   //:   WHERE mConList.ContactList.ListName = szListName
   //:     AND mConList.ContactList.Type = "E"
   //:     AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_18( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "E"
      SetAttributeFromString( mConList, "ContactList", "Type", "E" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:      INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 


   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListA( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser    BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                        STRING ( 2048 ) szFindString )


//:   INTEGER nRC
public int 
omConList_ActivateCreateConListA( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:// mUserCat is the object that contains the user's ContactList Categories. There is an
   //:// activate for when a User has Categories and another for when she doesn't.

   //:   ACTIVATE mConList
   //:      WHERE mConList.ContactList.ListName = szListName
   //:        AND mConList.ContactList.Type = "A"
   //:        AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_19( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );


   //:     IF RESULT < 0 
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "A"
      SetAttributeFromString( mConList, "ContactList", "Type", "A" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision 
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 


   //:END 
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListC( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                        STRING ( 1024 ) szFindString )

//:   VIEW mConListMain BASED ON LOD mConList
public int 
omConList_ActivateCreateConListC( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   zVIEW    mConListMain = new zVIEW( );
   //:INTEGER nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mConList
   //:   WHERE mConList.ContactList.ListName = szListName
   //:     AND mConList.ContactList.Type = "C"
   //:     AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_20( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "C"
      SetAttributeFromString( mConList, "ContactList", "Type", "C" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:      INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 


   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListS( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                        STRING ( 1024 ) szFindString )

//:   VIEW mConListMain BASED ON LOD mConList
public int 
omConList_ActivateCreateConListS( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   zVIEW    mConListMain = new zVIEW( );
   //:INTEGER nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mConList
   //:   WHERE mConList.ContactList.ListName = szListName
   //:     AND mConList.ContactList.Type = "S"
   //:     AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_21( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "S"
      SetAttributeFromString( mConList, "ContactList", "Type", "S" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:      INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 


   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListO( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                        STRING ( 1024 ) szFindString )

//:   VIEW mConListMain BASED ON LOD mConList
public int 
omConList_ActivateCreateConListO( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   zVIEW    mConListMain = new zVIEW( );
   //:INTEGER nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:// activate using not others to get a unique name but empty in case first entity does not yet exist
   //:ACTIVATE mConList
   //:   WHERE mConList.ContactList.ListName = szListName
   //:     AND mConList.ContactList.Type = "O"
   //:     AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_22( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "O"
      SetAttributeFromString( mConList, "ContactList", "Type", "O" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:      INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListD( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser    BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                        STRING ( 1024 ) szFindString )

//:   VIEW mConListMain BASED ON LOD mConList
public int 
omConList_ActivateCreateConListD( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   zVIEW    mConListMain = new zVIEW( );
   //:INTEGER nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mConList
   //:   WHERE mConList.ContactList.ListName = szListName
   //:     AND mConList.ContactList.Type = "D"
   //:     AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_23( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "D"
      SetAttributeFromString( mConList, "ContactList", "Type", "D" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:      INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );

      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 


   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListL( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser    BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                        STRING ( 1024 ) szFindString )

//:   VIEW mConListMain BASED ON LOD mConList
public int 
omConList_ActivateCreateConListL( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   zVIEW    mConListMain = new zVIEW( );
   //:INTEGER nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mConList
   //:   WHERE mConList.ContactList.ListName = szListName
   //:     AND mConList.ContactList.Type = "L"
   //:     AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_24( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "L"
      SetAttributeFromString( mConList, "ContactList", "Type", "L" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 


   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListU( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser    BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                        STRING ( 1024 ) szFindString )

//:   VIEW mConListMain BASED ON LOD mConList
public int 
omConList_ActivateCreateConListU( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   zVIEW    mConListMain = new zVIEW( );
   //:INTEGER nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mConList
   //:   WHERE mConList.ContactList.ListName = szListName
   //:     AND mConList.ContactList.Type = "U"
   //:     AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_25( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "U"
      SetAttributeFromString( mConList, "ContactList", "Type", "U" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 


   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListY( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser    BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                        STRING ( 1024 ) szFindString )

//:   VIEW mConListMain BASED ON LOD mConList
public int 
omConList_ActivateCreateConListY( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   zVIEW    mConListMain = new zVIEW( );
   //:INTEGER nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mConList
   //:   WHERE mConList.ContactList.ListName = szListName
   //:     AND mConList.ContactList.Type = "Y"
   //:     AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_26( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 

      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );

      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "Y"
      SetAttributeFromString( mConList, "ContactList", "Type", "Y" );

      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );

      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }

      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 


   //:END

   //:   OrderEntityForView( mConList, "Person", "Person.LastName A, Person.FirstName A" )
   OrderEntityForView( mConList, "Person", "Person.LastName A, Person.FirstName A" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListX( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser    BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                        STRING ( 2048 ) szFindString )

//:   
//:   INTEGER nRC
public int 
omConList_ActivateCreateConListX( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:// mUserCat is the object that contains the user's ContactList Categories. There is an
   //:// activate for when a User has Categories and another for when she doesn't.

   //:   ACTIVATE mConList
   //:      WHERE mConList.ContactList.ListName = szListName
   //:        AND mConList.ContactList.Type = "X"
   //:        AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_27( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );


   //:     IF RESULT < 0 
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "X"
      SetAttributeFromString( mConList, "ContactList", "Type", "X" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision       
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 



   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListT( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser    BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                        STRING ( 2048 ) szFindString )


//:   INTEGER nRC
public int 
omConList_ActivateCreateConListT( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:// mUserCat is the object that contains the user's ContactList Categories. There is an
   //:// activate for when a User has Categories and another for when she doesn't.

   //:   ACTIVATE mConList
   //:      WHERE mConList.ContactList.ListName = szListName
   //:        AND mConList.ContactList.Type = "T"
   //:        AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_36( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );


   //:     IF RESULT < 0 
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "T"
      SetAttributeFromString( mConList, "ContactList", "Type", "T" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision 
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 


   //:END 
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListF( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                         STRING ( 1024 ) szFindString )

//:   VIEW mConListMain BASED ON LOD mConList
public int 
omConList_ActivateCreateConListF( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   zVIEW    mConListMain = new zVIEW( );
   //:INTEGER nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mConList
   //:   WHERE mConList.ContactList.ListName = szListName
   //:     AND mConList.ContactList.Type = "F"
   //:     AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_28( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "F"
      SetAttributeFromString( mConList, "ContactList", "Type", "F" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:      INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 


   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateCreateConListN( VIEW mConList BASED ON LOD mConList,
//:                        VIEW mUser BASED ON LOD mUser,
//:                        STRING (  50  ) szListName,
//:                        STRING ( 1024 ) szFindString )

//:   VIEW mConListMain BASED ON LOD mConList
public int 
omConList_ActivateCreateConListN( zVIEW    mConList,
                                  View     mUser,
                                  String   szListName,
                                  String   szFindString )
{
   zVIEW    mConListMain = new zVIEW( );
   //:INTEGER nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mConList
   //:   WHERE mConList.ContactList.ListName = szListName
   //:     AND mConList.ContactList.Type = "N"
   //:     AND mConList.User.ID = mUser.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_29( mUser, vTempViewVar_0, szListName, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mConList, "mConList", mUser, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mConList EMPTY
      RESULT = ActivateEmptyObjectInstance( mConList, "mConList", mUser, zSINGLE );
      //:CREATE ENTITY mConList.ContactList
      RESULT = CreateEntity( mConList, "ContactList", zPOS_AFTER );
      //:mConList.ContactList.Type = "N"
      SetAttributeFromString( mConList, "ContactList", "Type", "N" );
      //:INCLUDE mConList.User FROM mUser.User
      RESULT = IncludeSubobjectFromSubobject( mConList, "User", mUser, "User", zPOS_AFTER );
      //:      INCLUDE mConList.AdministrativeDivision FROM mUser.CurrentAdministrativeDivision
      RESULT = IncludeSubobjectFromSubobject( mConList, "AdministrativeDivision", mUser, "CurrentAdministrativeDivision", zPOS_AFTER );
      //:mConList.ContactList.ListName = szListName
      SetAttributeFromString( mConList, "ContactList", "ListName", szListName );
      //:SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mConList );
       m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( mConList, "ContactList", "DateGenerated" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:mConList.ContactList.FindCriteria = szFindString
      SetAttributeFromString( mConList, "ContactList", "FindCriteria", szFindString );
   } 


   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:AddToConListP( VIEW mConList BASED ON LOD mConList,
//:               VIEW mProspct  BASED ON LOD mProspct,
//:               STRING ( 50 ) szListName )



//:TRANSFORMATION OPERATION
//:GetConListName( VIEW mConList,
//:                STRING ( 50 ) szMainViewName,
//:                STRING ( 50 ) szPotentialListName,
//:                STRING ( 50 ) szFinalListName )

//:   STRING (  64  ) szListName
public int 
omConList_GetConListName( View     mConList,
                          String   szMainViewName,
                          String   szPotentialListName,
                          StringBuilder   szFinalListName )
{
   String   szListName = null;
   //:STRING (  5  ) szNumber
   String   szNumber = null;
   //:VIEW mConListMain BASED ON LOD mConList
   zVIEW    mConListMain = new zVIEW( );
   //:INTEGER i
   int      i = 0;
   int      RESULT = 0;


   //:i = 0
   i = 0;
   //:szListName = ""
    {StringBuilder sb_szListName;
   if ( szListName == null )
      sb_szListName = new StringBuilder( 32 );
   else
      sb_szListName = new StringBuilder( szListName );
      ZeidonStringCopy( sb_szListName, 1, 0, "", 1, 0, 65 );
   szListName = sb_szListName.toString( );}
   //:   szFinalListName = szPotentialListName
   ZeidonStringCopy( szFinalListName, 1, 0, szPotentialListName, 1, 0, 51 );

   //:GET VIEW mConListMain NAMED szMainViewName
   RESULT = GetViewByName( mConListMain, szMainViewName, mConList, zLEVEL_TASK );

   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:SET CURSOR FIRST mConListMain.ContactList
      //:   WHERE mConListMain.ContactList.ListName = szPotentialListName
      RESULT = SetCursorFirstEntityByString( mConListMain, "ContactList", "ListName", szPotentialListName, "" );
      //:   
      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:szFinalListName = szPotentialListName
         ZeidonStringCopy( szFinalListName, 1, 0, szPotentialListName, 1, 0, 51 );
         //:ELSE
      } 
      else
      { 
         //:LOOP WHILE  RESULT >= zCURSOR_SET
         while ( RESULT >= zCURSOR_SET )
         { 
            //:i = i + 1
            i = i + 1;
            //:zIntegerToString( szNumber, 5, i )
            {StringBuilder sb_szNumber;
            if ( szNumber == null )
               sb_szNumber = new StringBuilder( 32 );
            else
               sb_szNumber = new StringBuilder( szNumber );
                         zIntegerToString( sb_szNumber, 5, i );
            szNumber = sb_szNumber.toString( );}
            //:szListName = szPotentialListName + "_" + szNumber
             {StringBuilder sb_szListName;
            if ( szListName == null )
               sb_szListName = new StringBuilder( 32 );
            else
               sb_szListName = new StringBuilder( szListName );
                        ZeidonStringCopy( sb_szListName, 1, 0, szPotentialListName, 1, 0, 65 );
            szListName = sb_szListName.toString( );}
             {StringBuilder sb_szListName;
            if ( szListName == null )
               sb_szListName = new StringBuilder( 32 );
            else
               sb_szListName = new StringBuilder( szListName );
                        ZeidonStringConcat( sb_szListName, 1, 0, "_", 1, 0, 65 );
            szListName = sb_szListName.toString( );}
             {StringBuilder sb_szListName;
            if ( szListName == null )
               sb_szListName = new StringBuilder( 32 );
            else
               sb_szListName = new StringBuilder( szListName );
                        ZeidonStringConcat( sb_szListName, 1, 0, szNumber, 1, 0, 65 );
            szListName = sb_szListName.toString( );}
            //:SET CURSOR FIRST mConListMain.ContactList
            //:   WHERE mConListMain.ContactList.ListName = szListName
            RESULT = SetCursorFirstEntityByString( mConListMain, "ContactList", "ListName", szListName, "" );
         } 

         //:END
         //:szFinalListName = szListName
         ZeidonStringCopy( szFinalListName, 1, 0, szListName, 1, 0, 51 );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
omConList_SetUpDocumentGen( View     mConList,
                            String   szRootEntityName,
                            String   szQueryObjectName,
                            String   CL_Flag )
{

   //:SetUpDocumentGen( VIEW mConList BASED ON LOD mConList,
   //:               STRING ( 32 ) szRootEntityName,
   //:               STRING ( 32 ) szQueryObjectName,
   //:               STRING ( 32 ) CL_Flag )

   //:// Execute the main operation, requesting the ResultSet to be activated. This is the default situation.
   //:SetUpDocumentGenWAFlg( mConList, szRootEntityName, szQueryObjectName, CL_Flag, "Y", "M" )
   omConList_SetUpDocumentGenWAFlg( mConList, szRootEntityName, szQueryObjectName, CL_Flag, "Y", "M" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:SetUpDocumentGenWAFlg( VIEW mConList BASED ON LOD mConList,
//:                       STRING ( 32 ) szRootEntityName,
//:                       STRING ( 32 ) szQueryObjectName,
//:                       STRING ( 1 )  CL_Flag,
//:                       STRING ( 1 )  ActivateFlag,
//:                       STRING ( 1 )  DocumentType )

//:   VIEW wXferO     REGISTERED AS wXferO
public int 
omConList_SetUpDocumentGenWAFlg( View     mConList,
                                 String   szRootEntityName,
                                 String   szQueryObjectName,
                                 String   CL_Flag,
                                 String   ActivateFlag,
                                 String   DocumentType )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEvent     BASED ON LOD  mEvent
   zVIEW    mEvent = new zVIEW( );
   //:VIEW ResultSet
   zVIEW    ResultSet = new zVIEW( );
   //:VIEW zqMDocOLST// BASED ON LOD  zqMDocO
   zVIEW    zqMDocOLST = new zVIEW( );
   //:VIEW vQualObject
   zVIEW    vQualObject = new zVIEW( );
   //:STRING ( 1 )  EventDocsExistFlag
   String   EventDocsExistFlag = null;
   //:STRING ( 32 ) szSelectEntityName
   String   szSelectEntityName = null;
   //:STRING ( 32 ) szConListRootEntityName
   String   szConListRootEntityName = null;
   //:STRING ( 32 ) szConListScopingEntityName
   String   szConListScopingEntityName = null;
   //:STRING ( 32 ) szRS_RootEntityName
   String   szRS_RootEntityName = null;
   //:SHORT  nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_5 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", mConList, zLEVEL_TASK );

   //:// Activate the Result Set with root entity, szRootEntityName, and set up the Document list for
   //:// selecting the Document to used in generation.
   //:// This routine supports Result Sets with root entities such as: Person, Church, School, Organization,
   //:// Donor. It also supports ProspectPerson
   //:// If the CL_Flag is "Y", then all children named szRootEntityName will be used to activate the
   //:// Result Set.
   //:// If the CL_Flag is "N", then only SELECTED children named szRootEntityName will be used to
   //:// activate the Result Set.
   //:// If the ActivateFlag is "Y", then this operation will Activate the ResultSet.
   //:// Otherwise, it will only set up ActivateID_List entries in wXferO for use in Activating the ResultSet by the caller.

   //:// First, set up szRootEntityName ID entities for Activate.
   //:// For Prospect, the select entity is Person.
   //:IF szRootEntityName = "Prospect"
   if ( ZeidonStringCompare( szRootEntityName, 1, 0, "Prospect", 1, 0, 33 ) == 0 )
   { 
      //:szSelectEntityName         = "ProspectPerson"
       {StringBuilder sb_szSelectEntityName;
      if ( szSelectEntityName == null )
         sb_szSelectEntityName = new StringBuilder( 32 );
      else
         sb_szSelectEntityName = new StringBuilder( szSelectEntityName );
            ZeidonStringCopy( sb_szSelectEntityName, 1, 0, "ProspectPerson", 1, 0, 33 );
      szSelectEntityName = sb_szSelectEntityName.toString( );}
      //:szRS_RootEntityName        = szRootEntityName
       {StringBuilder sb_szRS_RootEntityName;
      if ( szRS_RootEntityName == null )
         sb_szRS_RootEntityName = new StringBuilder( 32 );
      else
         sb_szRS_RootEntityName = new StringBuilder( szRS_RootEntityName );
            ZeidonStringCopy( sb_szRS_RootEntityName, 1, 0, szRootEntityName, 1, 0, 33 );
      szRS_RootEntityName = sb_szRS_RootEntityName.toString( );}
      //:szConListRootEntityName    = szRootEntityName
       {StringBuilder sb_szConListRootEntityName;
      if ( szConListRootEntityName == null )
         sb_szConListRootEntityName = new StringBuilder( 32 );
      else
         sb_szConListRootEntityName = new StringBuilder( szConListRootEntityName );
            ZeidonStringCopy( sb_szConListRootEntityName, 1, 0, szRootEntityName, 1, 0, 33 );
      szConListRootEntityName = sb_szConListRootEntityName.toString( );}
      //:szConListScopingEntityName = "ContactList"
       {StringBuilder sb_szConListScopingEntityName;
      if ( szConListScopingEntityName == null )
         sb_szConListScopingEntityName = new StringBuilder( 32 );
      else
         sb_szConListScopingEntityName = new StringBuilder( szConListScopingEntityName );
            ZeidonStringCopy( sb_szConListScopingEntityName, 1, 0, "ContactList", 1, 0, 33 );
      szConListScopingEntityName = sb_szConListScopingEntityName.toString( );}
      //:ELSE
   } 
   else
   { 
      //:IF wXferO.QueryObject.ContactListIncludeEntityName != "" AND 
      //:   wXferO.QueryObject.ContactListCreateEntityName  != ""
      if ( CompareAttributeToString( wXferO, "QueryObject", "ContactListIncludeEntityName", "" ) != 0 && CompareAttributeToString( wXferO, "QueryObject", "ContactListCreateEntityName", "" ) != 0 )
      { 

         //:szSelectEntityName         = wXferO.QueryObject.ContactListIncludeEntityName
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szSelectEntityName;
         if ( szSelectEntityName == null )
            sb_szSelectEntityName = new StringBuilder( 32 );
         else
            sb_szSelectEntityName = new StringBuilder( szSelectEntityName );
                   GetVariableFromAttribute( sb_szSelectEntityName, mi_lTempInteger_0, 'S', 33, wXferO, "QueryObject", "ContactListIncludeEntityName", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szSelectEntityName = sb_szSelectEntityName.toString( );}
         //:szRS_RootEntityName        = szRootEntityName
          {StringBuilder sb_szRS_RootEntityName;
         if ( szRS_RootEntityName == null )
            sb_szRS_RootEntityName = new StringBuilder( 32 );
         else
            sb_szRS_RootEntityName = new StringBuilder( szRS_RootEntityName );
                  ZeidonStringCopy( sb_szRS_RootEntityName, 1, 0, szRootEntityName, 1, 0, 33 );
         szRS_RootEntityName = sb_szRS_RootEntityName.toString( );}
         //:szConListRootEntityName    = wXferO.QueryObject.ContactListIncludeEntityName
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szConListRootEntityName;
         if ( szConListRootEntityName == null )
            sb_szConListRootEntityName = new StringBuilder( 32 );
         else
            sb_szConListRootEntityName = new StringBuilder( szConListRootEntityName );
                   GetVariableFromAttribute( sb_szConListRootEntityName, mi_lTempInteger_1, 'S', 33, wXferO, "QueryObject", "ContactListIncludeEntityName", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szConListRootEntityName = sb_szConListRootEntityName.toString( );}
         //:szConListScopingEntityName = "ContactList"
          {StringBuilder sb_szConListScopingEntityName;
         if ( szConListScopingEntityName == null )
            sb_szConListScopingEntityName = new StringBuilder( 32 );
         else
            sb_szConListScopingEntityName = new StringBuilder( szConListScopingEntityName );
                  ZeidonStringCopy( sb_szConListScopingEntityName, 1, 0, "ContactList", 1, 0, 33 );
         szConListScopingEntityName = sb_szConListScopingEntityName.toString( );}
         //:ELSE
      } 
      else
      { 

         //:      szSelectEntityName         = szRootEntityName
          {StringBuilder sb_szSelectEntityName;
         if ( szSelectEntityName == null )
            sb_szSelectEntityName = new StringBuilder( 32 );
         else
            sb_szSelectEntityName = new StringBuilder( szSelectEntityName );
                  ZeidonStringCopy( sb_szSelectEntityName, 1, 0, szRootEntityName, 1, 0, 33 );
         szSelectEntityName = sb_szSelectEntityName.toString( );}
         //:      szRS_RootEntityName        = szRootEntityName
          {StringBuilder sb_szRS_RootEntityName;
         if ( szRS_RootEntityName == null )
            sb_szRS_RootEntityName = new StringBuilder( 32 );
         else
            sb_szRS_RootEntityName = new StringBuilder( szRS_RootEntityName );
                  ZeidonStringCopy( sb_szRS_RootEntityName, 1, 0, szRootEntityName, 1, 0, 33 );
         szRS_RootEntityName = sb_szRS_RootEntityName.toString( );}
         //:      szConListRootEntityName    = szRootEntityName
          {StringBuilder sb_szConListRootEntityName;
         if ( szConListRootEntityName == null )
            sb_szConListRootEntityName = new StringBuilder( 32 );
         else
            sb_szConListRootEntityName = new StringBuilder( szConListRootEntityName );
                  ZeidonStringCopy( sb_szConListRootEntityName, 1, 0, szRootEntityName, 1, 0, 33 );
         szConListRootEntityName = sb_szConListRootEntityName.toString( );}
         //:      szConListScopingEntityName = "ContactList"
          {StringBuilder sb_szConListScopingEntityName;
         if ( szConListScopingEntityName == null )
            sb_szConListScopingEntityName = new StringBuilder( 32 );
         else
            sb_szConListScopingEntityName = new StringBuilder( szConListScopingEntityName );
                  ZeidonStringCopy( sb_szConListScopingEntityName, 1, 0, "ContactList", 1, 0, 33 );
         szConListScopingEntityName = sb_szConListScopingEntityName.toString( );}
      } 

      //:     
      //:END
   } 

   //:END

   //:FOR EACH wXferO.ActivateID_List
   RESULT = SetCursorFirstEntity( wXferO, "ActivateID_List", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY wXferO.ActivateID_List NONE
      RESULT = DeleteEntity( wXferO, "ActivateID_List", zREPOS_NONE );
      RESULT = SetCursorNextEntity( wXferO, "ActivateID_List", "" );
   } 

   //:END
   //:nRC = SetCursorFirstEntity( mConList, szConListRootEntityName, szConListScopingEntityName )
   nRC = SetCursorFirstEntity( mConList, szConListRootEntityName, szConListScopingEntityName );
   //:LOOP WHILE nRC >= zCURSOR_SET
   while ( nRC >= zCURSOR_SET )
   { 
      //:// If we're generating docs for ContactList, include ALL szRootEntityName entities under
      //:// the ContactList.
      //:// Otherwise, only include the selected szRootEntityName entities.
      //:IF CL_Flag = "Y"
      if ( ZeidonStringCompare( CL_Flag, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:CREATE ENTITY wXferO.ActivateID_List
         RESULT = CreateEntity( wXferO, "ActivateID_List", zPOS_AFTER );
         //:SetAttributeFromAttribute( wXferO,   "ActivateID_List", "ID",
         //:                           mConList, szConListRootEntityName,  "ID" )
         SetAttributeFromAttribute( wXferO, "ActivateID_List", "ID", mConList, szConListRootEntityName, "ID" );
         //:ELSE
      } 
      else
      { 
         //:nRC = GetSelectStateOfEntity( mConList, szSelectEntityName )
         nRC = GetSelectStateOfEntity( mConList, szSelectEntityName );
         //:IF nRC = 1
         if ( nRC == 1 )
         { 
            //:CREATE ENTITY wXferO.ActivateID_List
            RESULT = CreateEntity( wXferO, "ActivateID_List", zPOS_AFTER );
            //:SetAttributeFromAttribute( wXferO,   "ActivateID_List", "ID",
            //:                           mConList, szConListRootEntityName,  "ID" )
            SetAttributeFromAttribute( wXferO, "ActivateID_List", "ID", mConList, szConListRootEntityName, "ID" );
         } 

         //:END
      } 

      //:END
      //:nRC = SetCursorNextEntity( mConList, szConListRootEntityName, szConListScopingEntityName )
      nRC = SetCursorNextEntity( mConList, szConListRootEntityName, szConListScopingEntityName );
   } 

   //:END

   //:IF ActivateFlag = "Y"
   if ( ZeidonStringCompare( ActivateFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:// Create a Result Set from those szRS_RootEntityName IDs specified in wXferO.
      //:// Set select state of each for generate routine.
      //:GenerateQualFromEntityList( vQualObject, wXferO, "ActivateID_List", "", 0 )
      GenerateQualFromEntityList( vQualObject, wXferO, "ActivateID_List", "", 0 );
      //:ActivateObjectInstance( ResultSet, szQueryObjectName, mConList, vQualObject, zMULTIPLE )
      ActivateObjectInstance( ResultSet, szQueryObjectName, mConList, vQualObject, zMULTIPLE );
      //:NAME VIEW ResultSet "ResultSet"
      SetNameForView( ResultSet, "ResultSet", null, zLEVEL_TASK );
      //:nRC = SetCursorFirstEntity( ResultSet, szRS_RootEntityName, "" )
      nRC = SetCursorFirstEntity( ResultSet, szRS_RootEntityName, "" );
      //:LOOP WHILE nRC >= zCURSOR_SET
      while ( nRC >= zCURSOR_SET )
      { 
         //:SetSelectStateOfEntity( ResultSet, szRS_RootEntityName, 1 )
         SetSelectStateOfEntity( ResultSet, szRS_RootEntityName, 1 );
         //:nRC = SetCursorNextEntity( ResultSet, szRS_RootEntityName, "" )
         nRC = SetCursorNextEntity( ResultSet, szRS_RootEntityName, "" );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// Make sure that any existing Result Set is dropped.
      //:GET VIEW ResultSet NAMED "ResultSet"
      RESULT = GetViewByName( ResultSet, "ResultSet", mConList, zLEVEL_TASK );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:DropObjectInstance( ResultSet )
         DropObjectInstance( ResultSet );
      } 

      //:END
   } 

   //:END

   //:// Set up the list of Documents.
   //:// If there is an Event tied to the ContactList that has Documents tied to it, the Document
   //:// list will only have those documents in it.
   //:// Otherwise, we will create a list of all Documents for the object.
   //:EventDocsExistFlag = ""
    {StringBuilder sb_EventDocsExistFlag;
   if ( EventDocsExistFlag == null )
      sb_EventDocsExistFlag = new StringBuilder( 32 );
   else
      sb_EventDocsExistFlag = new StringBuilder( EventDocsExistFlag );
      ZeidonStringCopy( sb_EventDocsExistFlag, 1, 0, "", 1, 0, 2 );
   EventDocsExistFlag = sb_EventDocsExistFlag.toString( );}
   //:IF wXferO.DocumentGenerationEvent EXISTS
   lTempInteger_2 = CheckExistenceOfEntity( wXferO, "DocumentGenerationEvent" );
   if ( lTempInteger_2 == 0 )
   { 
      //:EXCLUDE wXferO.DocumentGenerationEvent
      RESULT = ExcludeEntity( wXferO, "DocumentGenerationEvent", zREPOS_AFTER );
   } 

   //:END
   //:IF mConList.Event EXISTS
   lTempInteger_3 = CheckExistenceOfEntity( mConList, "Event" );
   if ( lTempInteger_3 == 0 )
   { 
      //:ACTIVATE mEvent WHERE mEvent.Event.ID = mConList.Event.ID
      {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
             GetIntegerFromAttribute( mi_lTempInteger_4, mConList, "Event", "ID" );
      lTempInteger_4 = mi_lTempInteger_4.intValue( );}
      omConList_fnLocalBuildQual_31( mConList, vTempViewVar_0, lTempInteger_4 );
      RESULT = ActivateObjectInstance( mEvent, "mEvent", mConList, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:INCLUDE wXferO.DocumentGenerationEvent FROM mEvent.Event
      RESULT = IncludeSubobjectFromSubobject( wXferO, "DocumentGenerationEvent", mEvent, "Event", zPOS_AFTER );
      //:IF mEvent.Document EXISTS
      lTempInteger_5 = CheckExistenceOfEntity( mEvent, "Document" );
      if ( lTempInteger_5 == 0 )
      { 
         //:EventDocsExistFlag = "Y"
          {StringBuilder sb_EventDocsExistFlag;
         if ( EventDocsExistFlag == null )
            sb_EventDocsExistFlag = new StringBuilder( 32 );
         else
            sb_EventDocsExistFlag = new StringBuilder( EventDocsExistFlag );
                  ZeidonStringCopy( sb_EventDocsExistFlag, 1, 0, "Y", 1, 0, 2 );
         EventDocsExistFlag = sb_EventDocsExistFlag.toString( );}
         //:ELSE
      } 
      else
      { 
         //:DropObjectInstance( mEvent )
         DropObjectInstance( mEvent );
      } 

      //:END
   } 

   //:END

   //:wXferO.Root.CurrentFunction = "FormatDocumentFile"
   SetAttributeFromString( wXferO, "Root", "CurrentFunction", "FormatDocumentFile" );
   //:wXferO.QueryValues.RootEntityName  = szRS_RootEntityName
   SetAttributeFromString( wXferO, "QueryValues", "RootEntityName", szRS_RootEntityName );
   //:wXferO.QueryValues.QueryObjectName = szQueryObjectName
   SetAttributeFromString( wXferO, "QueryValues", "QueryObjectName", szQueryObjectName );
   //:IF EventDocsExistFlag = "Y"
   if ( ZeidonStringCompare( EventDocsExistFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:GenerateQualFromEntityList( vQualObject, mEvent, "Document", "", 0 )
      GenerateQualFromEntityList( vQualObject, mEvent, "Document", "", 0 );
      //:ActivateObjectInstance( zqMDocOLST, "zqMDocO", mConList, vQualObject, zMULTIPLE )
      ActivateObjectInstance( zqMDocOLST, "zqMDocO", mConList, vQualObject, zMULTIPLE );
      //:DropObjectInstance( mEvent )
      DropObjectInstance( mEvent );
      //:wXferO.Root.DocumentGenerationAllFlag = "N"
      SetAttributeFromString( wXferO, "Root", "DocumentGenerationAllFlag", "N" );
      //:NAME VIEW zqMDocOLST "zqMDocOLST"
      SetNameForView( zqMDocOLST, "zqMDocOLST", null, zLEVEL_TASK );
      //:ELSE
   } 
   else
   { 
      //:ActivateDocumentsForDivByType( mConList, DocumentType )
      {
       ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mConList );
       m_ZGLOBAL2_Operation.ActivateDocumentsForDivByType( mConList, DocumentType );
       // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
      }
      //:wXferO.Root.DocumentGenerationAllFlag = "Y"
      SetAttributeFromString( wXferO, "Root", "DocumentGenerationAllFlag", "Y" );
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dDonateListDisplay( VIEW mConList BASED ON LOD mConList,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )
//:   STRING (  254  ) szString
public int 
omConList_dDonateListDisplay( View     mConList,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szString = null;
   //:STRING (  254  ) szType
   String   szType = null;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;



   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:GetStringFromAttributeByContext( szType, mConList, "Donation", "Type", "", 254 )
         {StringBuilder sb_szType;
         if ( szType == null )
            sb_szType = new StringBuilder( 32 );
         else
            sb_szType = new StringBuilder( szType );
                   GetStringFromAttributeByContext( sb_szType, mConList, "Donation", "Type", "", 254 );
         szType = sb_szType.toString( );}
         //://szString = mConList.Donation.Type
         //:szString = "["+ mConList.DonationDonor.Type + "]" + szType + " - "
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 2, mConList, "DonationDonor", "Type", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringCopy( sb_szString, 1, 0, "[", 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, szTempString_0, 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, "]", 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, szType, 1, 0, 255 );
         szString = sb_szString.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, " - ", 1, 0, 255 );
         szString = sb_szString.toString( );}
         //:szString = szString + mConList.Donation.AmountReceived
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 1026, mConList, "Donation", "AmountReceived", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szString;
         if ( szString == null )
            sb_szString = new StringBuilder( 32 );
         else
            sb_szString = new StringBuilder( szString );
                  ZeidonStringConcat( sb_szString, 1, 0, szTempString_1, 1, 0, 255 );
         szString = sb_szString.toString( );}
         //:StoreStringInRecord ( mConList,
         //:                      InternalEntityStructure, InternalAttribStructure, szString )
         StoreStringInRecord( mConList, InternalEntityStructure, InternalAttribStructure, szString );
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


//:TRANSFORMATION OPERATION
//:fnAddConListEntry( VIEW mConListMain BASED ON LOD mConList,
//:                   VIEW mConList     BASED ON LOD mConList,
//:                   STRING ( 50 ) szListName,
//:                   STRING ( 32 ) szNewListName )

//:   VIEW mConList3    BASED ON LOD mConList
private int 
omConList_fnAddConListEntry( View     mConListMain,
                             View     mConList,
                             String   szListName,
                             String   szNewListName )
{
   zVIEW    mConList3 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:// If this is a Find (the szListName value is not null), replace the last Find that
   //:// is not to be saved.
   //:// Otherwise, add it (unless the name is the same).
   //:IF szListName != ""
   if ( ZeidonStringCompare( szListName, 1, 0, "", 1, 0, 51 ) != 0 )
   { 
      //:SET CURSOR FIRST mConListMain.ContactList
      //:           WHERE mConListMain.ContactList.FindCriteria != "" AND 
      //:                 mConListMain.ContactList.SaveFindFlag != "Y"
      RESULT = SetCursorFirstEntity( mConListMain, "ContactList", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mConListMain, "ContactList", "FindCriteria", "" ) == 0 || CompareAttributeToString( mConListMain, "ContactList", "SaveFindFlag", "Y" ) == 0 ) )
         { 
            RESULT = SetCursorNextEntity( mConListMain, "ContactList", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// There is a Find that should be replaced, so remove it.
         //:ACTIVATE mConList3 WHERE mConList3.ContactList.ID = mConListMain.ContactList.ID 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mConListMain, "ContactList", "ID" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         omConList_fnLocalBuildQual_30( mConListMain, vTempViewVar_0, lTempInteger_0 );
         RESULT = ActivateObjectInstance( mConList3, "mConList", mConListMain, vTempViewVar_0, zSINGLE );
         DropView( vTempViewVar_0 );
         //:DELETE ENTITY mConList3.ContactList 
         RESULT = DeleteEntity( mConList3, "ContactList", zPOS_NEXT );
         //:COMMIT mConList3
         RESULT = CommitObjectInstance( mConList3 );
         //:DropObjectInstance( mConList3 )
         DropObjectInstance( mConList3 );
         //:DropEntity( mConListMain, "ContactList", zREPOS_NONE )
         DropEntity( mConListMain, "ContactList", zREPOS_NONE );
         //:ELSE
      } 
      else
      { 
         //:SET CURSOR FIRST mConListMain.ContactList
         RESULT = SetCursorFirstEntity( mConListMain, "ContactList", "" );
      } 

      //:END
      //:INCLUDE mConListMain.ContactList FROM mConList.ContactList BEFORE
      RESULT = IncludeSubobjectFromSubobject( mConListMain, "ContactList", mConList, "ContactList", zPOS_BEFORE );
      //:ELSE
   } 
   else
   { 
      //:SET CURSOR FIRST mConListMain.ContactList
      //:           WHERE mConListMain.ContactList.ListName = szNewListName
      RESULT = SetCursorFirstEntityByString( mConListMain, "ContactList", "ListName", szNewListName, "" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:DropEntity( mConListMain, "ContactList", zREPOS_NONE )
         DropEntity( mConListMain, "ContactList", zREPOS_NONE );
         //:ELSE
      } 
      else
      { 
         //:SET CURSOR FIRST mConListMain.ContactList
         RESULT = SetCursorFirstEntity( mConListMain, "ContactList", "" );
      } 

      //:END
      //:INCLUDE mConListMain.ContactList FROM mConList.ContactList BEFORE
      RESULT = IncludeSubobjectFromSubobject( mConListMain, "ContactList", mConList, "ContactList", zPOS_BEFORE );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateConListMainI( VIEW mConList BASED ON LOD mConList,
//:                      VIEW ViewToWindow,
//:                      INTEGER UserID )
//:   VIEW mUser BASED ON LOD mUser
public int 
omConList_ActivateConListMainI( zVIEW    mConList,
                                View     ViewToWindow,
                                int      UserID )
{
   zVIEW    mUser = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //:GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_TASK )
   GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_TASK );
   //:ACTIVATE mConList MULTIPLE
   //:        WHERE mConList.ContactList.Type = "I"
   //:          AND mConList.AdministrativeDivision.ID = mUser.CurrentAdministrativeDivision.ID
   //:          AND mConList.User.ID = UserID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mUser, "CurrentAdministrativeDivision", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omConList_fnLocalBuildQual_33( ViewToWindow, vTempViewVar_0, lTempInteger_0, UserID );
   RESULT = ActivateObjectInstance( mConList, "mConList", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );

   //:NAME VIEW mConList "mConListI"
   SetNameForView( mConList, "mConListI", null, zLEVEL_TASK );
   //:FOR EACH mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mConList, "FinAidSource", "FinAidSource.Name A"  )
      OrderEntityForView( mConList, "FinAidSource", "FinAidSource.Name A" );
      RESULT = SetCursorNextEntity( mConList, "ContactList", "" );
   } 

   //:END
   //:SET CURSOR FIRST mConList.ContactList 
   RESULT = SetCursorFirstEntity( mConList, "ContactList", "" );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dContentCount( VIEW mConList BASED ON LOD mConList,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   INTEGER ContentCount
public int 
omConList_dContentCount( View     mConList,
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

         //:// Note that only one of the loops below should have any entities to loop through, since a
         //:// ContactList instance is for only one type of entity.
         //:ContentCount = 0
         ContentCount = 0;
         //:FOR EACH mConList.Prospect 
         RESULT = SetCursorFirstEntity( mConList, "Prospect", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( mConList, "Prospect", "" );
         } 

         //:END
         //:FOR EACH mConList.Church 
         RESULT = SetCursorFirstEntity( mConList, "Church", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( mConList, "Church", "" );
         } 

         //:END
         //:FOR EACH mConList.School 
         RESULT = SetCursorFirstEntity( mConList, "School", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( mConList, "School", "" );
         } 

         //:END
         //:FOR EACH mConList.Organization 
         RESULT = SetCursorFirstEntity( mConList, "Organization", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( mConList, "Organization", "" );
         } 

         //:END
         //:FOR EACH mConList.Person 
         RESULT = SetCursorFirstEntity( mConList, "Person", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( mConList, "Person", "" );
         } 

         //:END
         //:FOR EACH mConList.Donor 
         RESULT = SetCursorFirstEntity( mConList, "Donor", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( mConList, "Donor", "" );
         } 

         //:END
         //:FOR EACH mConList.Fund 
         RESULT = SetCursorFirstEntity( mConList, "Fund", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( mConList, "Fund", "" );
         } 

         //:END
         //:FOR EACH mConList.Donation 
         RESULT = SetCursorFirstEntity( mConList, "Donation", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( mConList, "Donation", "" );
         } 

         //:END
         //:FOR EACH mConList.Alumni 
         RESULT = SetCursorFirstEntity( mConList, "Alumni", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( mConList, "Alumni", "" );
         } 

         //:END
         //:FOR EACH mConList.ContactListItem
         RESULT = SetCursorFirstEntity( mConList, "ContactListItem", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( mConList, "ContactListItem", "" );
         } 

         //:END
         //:FOR EACH mConList.Class 
         RESULT = SetCursorFirstEntity( mConList, "Class", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( mConList, "Class", "" );
         } 

         //:END

         //:FOR EACH mConList.Course 
         RESULT = SetCursorFirstEntity( mConList, "Course", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( mConList, "Course", "" );
         } 

         //:END
         //:FOR EACH mConList.FinAidSource 
         RESULT = SetCursorFirstEntity( mConList, "FinAidSource", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( mConList, "FinAidSource", "" );
         } 

         //:END
         //:FOR EACH mConList.CL_ItemEmployee 
         RESULT = SetCursorFirstEntity( mConList, "CL_ItemEmployee", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:ContentCount = ContentCount + 1
            ContentCount = ContentCount + 1;
            RESULT = SetCursorNextEntity( mConList, "CL_ItemEmployee", "" );
         } 

         //:END

         //:StoreValueInRecord ( mConList,
         //:                  InternalEntityStructure, InternalAttribStructure, ContentCount, 0 )
         StoreValueInRecord( mConList, InternalEntityStructure, InternalAttribStructure, ContentCount, 0 );
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
//:dHasProfileForYear( VIEW mConList BASED ON LOD mConList,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   STRING ( 1 ) szHasProfileForYear  
public int 
omConList_dHasProfileForYear( View     mConList,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szHasProfileForYear = null;
   //:VIEW  lFAAdmin BASED ON LOD lFAAdmin
   zVIEW    lFAAdmin = new zVIEW( );
   //:VIEW  mConList2 BASED ON LOD mConList
   zVIEW    mConList2 = new zVIEW( );
   int      RESULT = 0;

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:szHasProfileForYear="N"
          {StringBuilder sb_szHasProfileForYear;
         if ( szHasProfileForYear == null )
            sb_szHasProfileForYear = new StringBuilder( 32 );
         else
            sb_szHasProfileForYear = new StringBuilder( szHasProfileForYear );
                  ZeidonStringCopy( sb_szHasProfileForYear, 1, 0, "N", 1, 0, 2 );
         szHasProfileForYear = sb_szHasProfileForYear.toString( );}

         //:CreateViewFromView(mConList2,mConList)
         CreateViewFromView( mConList2, mConList );

         //:GET VIEW lFAAdmin NAMED "lFAAdmin"
         RESULT = GetViewByName( lFAAdmin, "lFAAdmin", mConList, zLEVEL_TASK );
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 
            //:FOR EACH mConList2.FinAidProfile
            RESULT = SetCursorFirstEntity( mConList2, "FinAidProfile", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:if mConList2.FinAidAdmin.ID = lFAAdmin.FinAidAdmin.ID 
               if ( CompareAttributeToAttribute( mConList2, "FinAidAdmin", "ID", lFAAdmin, "FinAidAdmin", "ID" ) == 0 )
               { 
                  //:szHasProfileForYear="Y"
                   {StringBuilder sb_szHasProfileForYear;
                  if ( szHasProfileForYear == null )
                     sb_szHasProfileForYear = new StringBuilder( 32 );
                  else
                     sb_szHasProfileForYear = new StringBuilder( szHasProfileForYear );
                                    ZeidonStringCopy( sb_szHasProfileForYear, 1, 0, "Y", 1, 0, 2 );
                  szHasProfileForYear = sb_szHasProfileForYear.toString( );}
               } 

               RESULT = SetCursorNextEntity( mConList2, "FinAidProfile", "" );
               //:END
            } 

            //:END
         } 

         //:END
         //:DropView(mConList2)
         DropView( mConList2 );
         //:StoreStringInRecord ( mConList,
         //:                      InternalEntityStructure, InternalAttribStructure, szHasProfileForYear )
         StoreStringInRecord( mConList, InternalEntityStructure, InternalAttribStructure, szHasProfileForYear );
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
//:dHasProfileForYearPros( VIEW mConList BASED ON LOD mConList,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   STRING ( 1 ) szHasProfileForYear  
public int 
omConList_dHasProfileForYearPros( View     mConList,
                                  String InternalEntityStructure,
                                  String InternalAttribStructure,
                                  Integer   GetOrSetFlag )
{
   String   szHasProfileForYear = null;
   //:VIEW  lFAAdmin BASED ON LOD lFAAdmin
   zVIEW    lFAAdmin = new zVIEW( );
   //:VIEW  mConList2 BASED ON LOD mConList
   zVIEW    mConList2 = new zVIEW( );
   int      RESULT = 0;

   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:szHasProfileForYear="N"
          {StringBuilder sb_szHasProfileForYear;
         if ( szHasProfileForYear == null )
            sb_szHasProfileForYear = new StringBuilder( 32 );
         else
            sb_szHasProfileForYear = new StringBuilder( szHasProfileForYear );
                  ZeidonStringCopy( sb_szHasProfileForYear, 1, 0, "N", 1, 0, 2 );
         szHasProfileForYear = sb_szHasProfileForYear.toString( );}

         //:CreateViewFromView(mConList2,mConList)
         CreateViewFromView( mConList2, mConList );

         //:GET VIEW lFAAdmin NAMED "lFAAdmin"
         RESULT = GetViewByName( lFAAdmin, "lFAAdmin", mConList, zLEVEL_TASK );
         //:IF RESULT >= 0
         if ( RESULT >= 0 )
         { 
            //:FOR EACH mConList2.ProspectFinAidProfile
            RESULT = SetCursorFirstEntity( mConList2, "ProspectFinAidProfile", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:if mConList2.ProspectFinAidAdmin.ID = lFAAdmin.FinAidAdmin.ID 
               if ( CompareAttributeToAttribute( mConList2, "ProspectFinAidAdmin", "ID", lFAAdmin, "FinAidAdmin", "ID" ) == 0 )
               { 
                  //:szHasProfileForYear="Y"
                   {StringBuilder sb_szHasProfileForYear;
                  if ( szHasProfileForYear == null )
                     sb_szHasProfileForYear = new StringBuilder( 32 );
                  else
                     sb_szHasProfileForYear = new StringBuilder( szHasProfileForYear );
                                    ZeidonStringCopy( sb_szHasProfileForYear, 1, 0, "Y", 1, 0, 2 );
                  szHasProfileForYear = sb_szHasProfileForYear.toString( );}
               } 

               RESULT = SetCursorNextEntity( mConList2, "ProspectFinAidProfile", "" );
               //:END
            } 

            //:END
         } 

         //:END
         //:DropView(mConList2)
         DropView( mConList2 );
         //:StoreStringInRecord ( mConList,
         //:                      InternalEntityStructure, InternalAttribStructure, szHasProfileForYear )
         StoreStringInRecord( mConList, InternalEntityStructure, InternalAttribStructure, szHasProfileForYear );
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
public int 
omConList_ObjectConstraints( View     mConList,
                             Integer   Event,
                             Integer   State )
{
   int      lTempInteger_0 = 0;
   int      RESULT = 0;

   //:ObjectConstraints( VIEW mConList BASED ON LOD mConList,
   //:                SHORT Event,
   //:                SHORT State )

   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:// Initialize the Selected User subobject.
         //:IF mConList.User EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mConList, "User" );
         if ( lTempInteger_0 == 0 )
         { 
            //:INCLUDE mConList.SelectedUser FROM mConList.User 
            RESULT = IncludeSubobjectFromSubobject( mConList, "SelectedUser", mConList, "User", zPOS_AFTER );
         } 

         //:END
         break ;

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
//:dGovIdSSNDisplay( VIEW mConList BASED ON LOD mConList,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )
//: VIEW  sHost REGISTERED AS sHost
public int 
omConList_dGovIdSSNDisplay( View     mConList,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   zVIEW    sHost = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 254 ) szID  
   String   szID = null;

   RESULT = GetViewByName( sHost, "sHost", mConList, zLEVEL_TASK );
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF sHost.Host.UsesSSN="Y"
         if ( CompareAttributeToString( sHost, "Host", "UsesSSN", "Y" ) == 0 )
         { 
            //:GetStringFromAttributeByContext( szID,
            //:                          mConList,
            //:                          "Person",
            //:                          "GovernmentID",
            //:                          "SSN_SecurityDisplay",
            //:                          254 ) 
            {StringBuilder sb_szID;
            if ( szID == null )
               sb_szID = new StringBuilder( 32 );
            else
               sb_szID = new StringBuilder( szID );
                         GetStringFromAttributeByContext( sb_szID, mConList, "Person", "GovernmentID", "SSN_SecurityDisplay", 254 );
            szID = sb_szID.toString( );}
            //:ELSE
         } 
         else
         { 
            //:GetStringFromAttributeByContext( szID,
            //:                          mConList,
            //:                          "Person",
            //:                          "GovernmentID",
            //:                          "",
            //:                          254 ) 
            {StringBuilder sb_szID;
            if ( szID == null )
               sb_szID = new StringBuilder( 32 );
            else
               sb_szID = new StringBuilder( szID );
                         GetStringFromAttributeByContext( sb_szID, mConList, "Person", "GovernmentID", "", 254 );
            szID = sb_szID.toString( );}
         } 


         //:END
         //:StoreStringInRecord ( mConList,
         //:                         InternalEntityStructure, InternalAttribStructure, szID )
         StoreStringInRecord( mConList, InternalEntityStructure, InternalAttribStructure, szID );
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
//:dGovIdSSNDisplayA( VIEW mConList BASED ON LOD mConList,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:VIEW  sHost REGISTERED AS sHost
public int 
omConList_dGovIdSSNDisplayA( View     mConList,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   zVIEW    sHost = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 254 ) szID  
   String   szID = null;

   RESULT = GetViewByName( sHost, "sHost", mConList, zLEVEL_TASK );
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF sHost.Host.UsesSSN="Y"
         if ( CompareAttributeToString( sHost, "Host", "UsesSSN", "Y" ) == 0 )
         { 
            //:GetStringFromAttributeByContext( szID,
            //:                          mConList,
            //:                          "AlumniPerson",
            //:                          "GovernmentID",
            //:                          "SSN_SecurityDisplay",
            //:                          254 ) 
            {StringBuilder sb_szID;
            if ( szID == null )
               sb_szID = new StringBuilder( 32 );
            else
               sb_szID = new StringBuilder( szID );
                         GetStringFromAttributeByContext( sb_szID, mConList, "AlumniPerson", "GovernmentID", "SSN_SecurityDisplay", 254 );
            szID = sb_szID.toString( );}
            //:ELSE
         } 
         else
         { 
            //:GetStringFromAttributeByContext( szID,
            //:                          mConList,
            //:                          "AlumniPerson",
            //:                          "GovernmentID",
            //:                          "",
            //:                          254 ) 
            {StringBuilder sb_szID;
            if ( szID == null )
               sb_szID = new StringBuilder( 32 );
            else
               sb_szID = new StringBuilder( szID );
                         GetStringFromAttributeByContext( sb_szID, mConList, "AlumniPerson", "GovernmentID", "", 254 );
            szID = sb_szID.toString( );}
         } 


         //:END
         //:StoreStringInRecord ( mConList,
         //:                         InternalEntityStructure, InternalAttribStructure, szID )
         StoreStringInRecord( mConList, InternalEntityStructure, InternalAttribStructure, szID );
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
//:dGovIdSSNDisplaySP( VIEW mConList BASED ON LOD mConList,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:VIEW  sHost REGISTERED AS sHost
public int 
omConList_dGovIdSSNDisplaySP( View     mConList,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   zVIEW    sHost = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 254 ) szID  
   String   szID = null;

   RESULT = GetViewByName( sHost, "sHost", mConList, zLEVEL_TASK );
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:IF sHost.Host.UsesSSN="Y"
         if ( CompareAttributeToString( sHost, "Host", "UsesSSN", "Y" ) == 0 )
         { 
            //:GetStringFromAttributeByContext( szID,
            //:                          mConList,
            //:                          "ContactListItemStudentPerson",
            //:                          "GovernmentID",
            //:                          "SSN_SecurityDisplay",
            //:                          254 ) 
            {StringBuilder sb_szID;
            if ( szID == null )
               sb_szID = new StringBuilder( 32 );
            else
               sb_szID = new StringBuilder( szID );
                         GetStringFromAttributeByContext( sb_szID, mConList, "ContactListItemStudentPerson", "GovernmentID", "SSN_SecurityDisplay", 254 );
            szID = sb_szID.toString( );}
            //:ELSE
         } 
         else
         { 
            //:GetStringFromAttributeByContext( szID,
            //:                          mConList,
            //:                          "ContactListItemStudentPerson",
            //:                          "GovernmentID",
            //:                          "",
            //:                          254 ) 
            {StringBuilder sb_szID;
            if ( szID == null )
               sb_szID = new StringBuilder( 32 );
            else
               sb_szID = new StringBuilder( szID );
                         GetStringFromAttributeByContext( sb_szID, mConList, "ContactListItemStudentPerson", "GovernmentID", "", 254 );
            szID = sb_szID.toString( );}
         } 


         //:END
         //:StoreStringInRecord ( mConList,
         //:                         InternalEntityStructure, InternalAttribStructure, szID )
         StoreStringInRecord( mConList, InternalEntityStructure, InternalAttribStructure, szID );
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
