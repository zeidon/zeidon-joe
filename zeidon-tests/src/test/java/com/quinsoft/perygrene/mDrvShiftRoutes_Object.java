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

package com.quinsoft.perygrene;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

//import com.apex.perygrene.ZGLOBAL2_Operation;

/**
   @author QuinSoft
**/

public class mDrvShiftRoutes_Object extends VmlObjectOperations
{
   public mDrvShiftRoutes_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dToLegLoc( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:           STRING ( 32 ) InternalEntityStructure,
//:           STRING ( 32 ) InternalAttribStructure,
//:           SHORT GetOrSetFlag )

//:   STRING ( 500 ) szExistingLocation
public int 
omDrvShiftRoutes_dToLegLoc( View     mDrvShiftRoutes,
                            String InternalEntityStructure,
                            String InternalAttribStructure,
                            Integer   GetOrSetFlag )
{
   String   szExistingLocation = null;
   //:STRING ( 500 ) szAddress
   String   szAddress = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   String   szTempString_4 = null;
   int      lTempInteger_7 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Set the Location Name from whichever To Location exists.
         //:IF mDrvShiftRoutes.ToDeliveryLegTerminal EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mDrvShiftRoutes, "ToDeliveryLegTerminal" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szExistingLocation = "<img src='./images/terminal.png' alt='Terminal' class='iconstd'>"  +  mDrvShiftRoutes.ToDeliveryLegTerminal.Name + ", " + mDrvShiftRoutes.ToDeliveryLegTerminalAdd.City 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 255, mDrvShiftRoutes, "ToDeliveryLegTerminal", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szExistingLocation;
            if ( szExistingLocation == null )
               sb_szExistingLocation = new StringBuilder( 32 );
            else
               sb_szExistingLocation = new StringBuilder( szExistingLocation );
                        ZeidonStringCopy( sb_szExistingLocation, 1, 0, "<img src='./images/terminal.png' alt='Terminal' class='iconstd'>", 1, 0, 501 );
            szExistingLocation = sb_szExistingLocation.toString( );}
             {StringBuilder sb_szExistingLocation;
            if ( szExistingLocation == null )
               sb_szExistingLocation = new StringBuilder( 32 );
            else
               sb_szExistingLocation = new StringBuilder( szExistingLocation );
                        ZeidonStringConcat( sb_szExistingLocation, 1, 0, szTempString_0, 1, 0, 501 );
            szExistingLocation = sb_szExistingLocation.toString( );}
             {StringBuilder sb_szExistingLocation;
            if ( szExistingLocation == null )
               sb_szExistingLocation = new StringBuilder( 32 );
            else
               sb_szExistingLocation = new StringBuilder( szExistingLocation );
                        ZeidonStringConcat( sb_szExistingLocation, 1, 0, ", ", 1, 0, 501 );
            szExistingLocation = sb_szExistingLocation.toString( );}
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 101, mDrvShiftRoutes, "ToDeliveryLegTerminalAdd", "City", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szExistingLocation;
            if ( szExistingLocation == null )
               sb_szExistingLocation = new StringBuilder( 32 );
            else
               sb_szExistingLocation = new StringBuilder( szExistingLocation );
                        ZeidonStringConcat( sb_szExistingLocation, 1, 0, szTempString_1, 1, 0, 501 );
            szExistingLocation = sb_szExistingLocation.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mDrvShiftRoutes.ToDeliveryLegLocation EXISTS
            lTempInteger_3 = CheckExistenceOfEntity( mDrvShiftRoutes, "ToDeliveryLegLocation" );
            if ( lTempInteger_3 == 0 )
            { 
               //:szExistingLocation = "<img src='./images/deliverysite.png' alt='Terminal' class='iconstd'>"  +  mDrvShiftRoutes.ToDeliveryLegLocation.Name  + ", " + mDrvShiftRoutes.ToDeliveryLegLocationAdd.City  
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szTempString_2;
               if ( szTempString_2 == null )
                  sb_szTempString_2 = new StringBuilder( 32 );
               else
                  sb_szTempString_2 = new StringBuilder( szTempString_2 );
                               GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_4, 'S', 255, mDrvShiftRoutes, "ToDeliveryLegLocation", "Name", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szTempString_2 = sb_szTempString_2.toString( );}
                {StringBuilder sb_szExistingLocation;
               if ( szExistingLocation == null )
                  sb_szExistingLocation = new StringBuilder( 32 );
               else
                  sb_szExistingLocation = new StringBuilder( szExistingLocation );
                              ZeidonStringCopy( sb_szExistingLocation, 1, 0, "<img src='./images/deliverysite.png' alt='Terminal' class='iconstd'>", 1, 0, 501 );
               szExistingLocation = sb_szExistingLocation.toString( );}
                {StringBuilder sb_szExistingLocation;
               if ( szExistingLocation == null )
                  sb_szExistingLocation = new StringBuilder( 32 );
               else
                  sb_szExistingLocation = new StringBuilder( szExistingLocation );
                              ZeidonStringConcat( sb_szExistingLocation, 1, 0, szTempString_2, 1, 0, 501 );
               szExistingLocation = sb_szExistingLocation.toString( );}
                {StringBuilder sb_szExistingLocation;
               if ( szExistingLocation == null )
                  sb_szExistingLocation = new StringBuilder( 32 );
               else
                  sb_szExistingLocation = new StringBuilder( szExistingLocation );
                              ZeidonStringConcat( sb_szExistingLocation, 1, 0, ", ", 1, 0, 501 );
               szExistingLocation = sb_szExistingLocation.toString( );}
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szTempString_3;
               if ( szTempString_3 == null )
                  sb_szTempString_3 = new StringBuilder( 32 );
               else
                  sb_szTempString_3 = new StringBuilder( szTempString_3 );
                               GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_5, 'S', 101, mDrvShiftRoutes, "ToDeliveryLegLocationAdd", "City", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szTempString_3 = sb_szTempString_3.toString( );}
                {StringBuilder sb_szExistingLocation;
               if ( szExistingLocation == null )
                  sb_szExistingLocation = new StringBuilder( 32 );
               else
                  sb_szExistingLocation = new StringBuilder( szExistingLocation );
                              ZeidonStringConcat( sb_szExistingLocation, 1, 0, szTempString_3, 1, 0, 501 );
               szExistingLocation = sb_szExistingLocation.toString( );}
               //:ELSE
            } 
            else
            { 
               //:IF mDrvShiftRoutes.Final_HB_Carrier EXISTS
               lTempInteger_6 = CheckExistenceOfEntity( mDrvShiftRoutes, "Final_HB_Carrier" );
               if ( lTempInteger_6 == 0 )
               { 
                  //:szAddress = mDrvShiftRoutes.ToDeliveryLegAddress.Line1 + ", " + mDrvShiftRoutes.ToDeliveryLegAddress.City
                  {StringBuilder sb_szAddress;
                  if ( szAddress == null )
                     sb_szAddress = new StringBuilder( 32 );
                  else
                     sb_szAddress = new StringBuilder( szAddress );
                                     GetStringFromAttribute( sb_szAddress, mDrvShiftRoutes, "ToDeliveryLegAddress", "Line1" );
                  szAddress = sb_szAddress.toString( );}
                   {StringBuilder sb_szAddress;
                  if ( szAddress == null )
                     sb_szAddress = new StringBuilder( 32 );
                  else
                     sb_szAddress = new StringBuilder( szAddress );
                                    ZeidonStringConcat( sb_szAddress, 1, 0, ", ", 1, 0, 501 );
                  szAddress = sb_szAddress.toString( );}
                  {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                  StringBuilder sb_szTempString_4;
                  if ( szTempString_4 == null )
                     sb_szTempString_4 = new StringBuilder( 32 );
                  else
                     sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                     GetVariableFromAttribute( sb_szTempString_4, mi_lTempInteger_7, 'S', 101, mDrvShiftRoutes, "ToDeliveryLegAddress", "City", "", 0 );
                  lTempInteger_7 = mi_lTempInteger_7.intValue( );
                  szTempString_4 = sb_szTempString_4.toString( );}
                   {StringBuilder sb_szAddress;
                  if ( szAddress == null )
                     sb_szAddress = new StringBuilder( 32 );
                  else
                     sb_szAddress = new StringBuilder( szAddress );
                                    ZeidonStringConcat( sb_szAddress, 1, 0, szTempString_4, 1, 0, 501 );
                  szAddress = sb_szAddress.toString( );}
                  //:szExistingLocation = "<img src='./images/homebase.png' alt='Home Base' class='iconstd'>"  + "HB - " + szAddress
                   {StringBuilder sb_szExistingLocation;
                  if ( szExistingLocation == null )
                     sb_szExistingLocation = new StringBuilder( 32 );
                  else
                     sb_szExistingLocation = new StringBuilder( szExistingLocation );
                                    ZeidonStringCopy( sb_szExistingLocation, 1, 0, "<img src='./images/homebase.png' alt='Home Base' class='iconstd'>", 1, 0, 501 );
                  szExistingLocation = sb_szExistingLocation.toString( );}
                   {StringBuilder sb_szExistingLocation;
                  if ( szExistingLocation == null )
                     sb_szExistingLocation = new StringBuilder( 32 );
                  else
                     sb_szExistingLocation = new StringBuilder( szExistingLocation );
                                    ZeidonStringConcat( sb_szExistingLocation, 1, 0, "HB - ", 1, 0, 501 );
                  szExistingLocation = sb_szExistingLocation.toString( );}
                   {StringBuilder sb_szExistingLocation;
                  if ( szExistingLocation == null )
                     sb_szExistingLocation = new StringBuilder( 32 );
                  else
                     sb_szExistingLocation = new StringBuilder( szExistingLocation );
                                    ZeidonStringConcat( sb_szExistingLocation, 1, 0, szAddress, 1, 0, 501 );
                  szExistingLocation = sb_szExistingLocation.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szExistingLocation = ""
                   {StringBuilder sb_szExistingLocation;
                  if ( szExistingLocation == null )
                     sb_szExistingLocation = new StringBuilder( 32 );
                  else
                     sb_szExistingLocation = new StringBuilder( szExistingLocation );
                                    ZeidonStringCopy( sb_szExistingLocation, 1, 0, "", 1, 0, 501 );
                  szExistingLocation = sb_szExistingLocation.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szExistingLocation )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szExistingLocation );
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
omDrvShiftRoutes_fnLocalBuildQual_0( View     vSubtask,
                                     zVIEW    vQualObject,
                                     int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "VehiclePowerUnit" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "VehiclePowerUnit" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DERIVED ATTRIBUTE OPERATION
//:dFromLegLoc( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:             STRING ( 32 ) InternalEntityStructure,
//:             STRING ( 32 ) InternalAttribStructure,
//:             SHORT GetOrSetFlag )

//:   STRING ( 500 ) szExistingLocation
public int 
omDrvShiftRoutes_dFromLegLoc( View     mDrvShiftRoutes,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szExistingLocation = null;
   //:STRING ( 500 ) szAddress
   String   szAddress = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   String   szTempString_4 = null;
   int      lTempInteger_7 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Set the Location Name from whichever From Location exists.
         //:IF mDrvShiftRoutes.FromDeliveryLegTerminal EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mDrvShiftRoutes, "FromDeliveryLegTerminal" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szExistingLocation = "<img src='./images/terminal.png' alt='Terminal' class='iconstd'>"  + mDrvShiftRoutes.FromDeliveryLegTerminal.Name + ", " + mDrvShiftRoutes.FromDeliveryLegTerminalAdd.City  
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 255, mDrvShiftRoutes, "FromDeliveryLegTerminal", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szExistingLocation;
            if ( szExistingLocation == null )
               sb_szExistingLocation = new StringBuilder( 32 );
            else
               sb_szExistingLocation = new StringBuilder( szExistingLocation );
                        ZeidonStringCopy( sb_szExistingLocation, 1, 0, "<img src='./images/terminal.png' alt='Terminal' class='iconstd'>", 1, 0, 501 );
            szExistingLocation = sb_szExistingLocation.toString( );}
             {StringBuilder sb_szExistingLocation;
            if ( szExistingLocation == null )
               sb_szExistingLocation = new StringBuilder( 32 );
            else
               sb_szExistingLocation = new StringBuilder( szExistingLocation );
                        ZeidonStringConcat( sb_szExistingLocation, 1, 0, szTempString_0, 1, 0, 501 );
            szExistingLocation = sb_szExistingLocation.toString( );}
             {StringBuilder sb_szExistingLocation;
            if ( szExistingLocation == null )
               sb_szExistingLocation = new StringBuilder( 32 );
            else
               sb_szExistingLocation = new StringBuilder( szExistingLocation );
                        ZeidonStringConcat( sb_szExistingLocation, 1, 0, ", ", 1, 0, 501 );
            szExistingLocation = sb_szExistingLocation.toString( );}
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_2, 'S', 101, mDrvShiftRoutes, "FromDeliveryLegTerminalAdd", "City", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szExistingLocation;
            if ( szExistingLocation == null )
               sb_szExistingLocation = new StringBuilder( 32 );
            else
               sb_szExistingLocation = new StringBuilder( szExistingLocation );
                        ZeidonStringConcat( sb_szExistingLocation, 1, 0, szTempString_1, 1, 0, 501 );
            szExistingLocation = sb_szExistingLocation.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mDrvShiftRoutes.FromDeliveryLegLocation EXISTS
            lTempInteger_3 = CheckExistenceOfEntity( mDrvShiftRoutes, "FromDeliveryLegLocation" );
            if ( lTempInteger_3 == 0 )
            { 
               //:szExistingLocation = "<img src='./images/deliverysite.png' alt='Delivery Site' class='iconstd'>"  + mDrvShiftRoutes.FromDeliveryLegLocation.Name + ", " + mDrvShiftRoutes.FromDeliveryLegLocAddress.City 
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szTempString_2;
               if ( szTempString_2 == null )
                  sb_szTempString_2 = new StringBuilder( 32 );
               else
                  sb_szTempString_2 = new StringBuilder( szTempString_2 );
                               GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_4, 'S', 255, mDrvShiftRoutes, "FromDeliveryLegLocation", "Name", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szTempString_2 = sb_szTempString_2.toString( );}
                {StringBuilder sb_szExistingLocation;
               if ( szExistingLocation == null )
                  sb_szExistingLocation = new StringBuilder( 32 );
               else
                  sb_szExistingLocation = new StringBuilder( szExistingLocation );
                              ZeidonStringCopy( sb_szExistingLocation, 1, 0, "<img src='./images/deliverysite.png' alt='Delivery Site' class='iconstd'>", 1, 0, 501 );
               szExistingLocation = sb_szExistingLocation.toString( );}
                {StringBuilder sb_szExistingLocation;
               if ( szExistingLocation == null )
                  sb_szExistingLocation = new StringBuilder( 32 );
               else
                  sb_szExistingLocation = new StringBuilder( szExistingLocation );
                              ZeidonStringConcat( sb_szExistingLocation, 1, 0, szTempString_2, 1, 0, 501 );
               szExistingLocation = sb_szExistingLocation.toString( );}
                {StringBuilder sb_szExistingLocation;
               if ( szExistingLocation == null )
                  sb_szExistingLocation = new StringBuilder( 32 );
               else
                  sb_szExistingLocation = new StringBuilder( szExistingLocation );
                              ZeidonStringConcat( sb_szExistingLocation, 1, 0, ", ", 1, 0, 501 );
               szExistingLocation = sb_szExistingLocation.toString( );}
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szTempString_3;
               if ( szTempString_3 == null )
                  sb_szTempString_3 = new StringBuilder( 32 );
               else
                  sb_szTempString_3 = new StringBuilder( szTempString_3 );
                               GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_5, 'S', 101, mDrvShiftRoutes, "FromDeliveryLegLocAddress", "City", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szTempString_3 = sb_szTempString_3.toString( );}
                {StringBuilder sb_szExistingLocation;
               if ( szExistingLocation == null )
                  sb_szExistingLocation = new StringBuilder( 32 );
               else
                  sb_szExistingLocation = new StringBuilder( szExistingLocation );
                              ZeidonStringConcat( sb_szExistingLocation, 1, 0, szTempString_3, 1, 0, 501 );
               szExistingLocation = sb_szExistingLocation.toString( );}
               //:ELSE
            } 
            else
            { 
               //:IF mDrvShiftRoutes.Initial_HB_Carrier EXISTS
               lTempInteger_6 = CheckExistenceOfEntity( mDrvShiftRoutes, "Initial_HB_Carrier" );
               if ( lTempInteger_6 == 0 )
               { 
                  //:szAddress = mDrvShiftRoutes.FromDeliveryLegAddress.Line1 + ", " + mDrvShiftRoutes.FromDeliveryLegAddress.City
                  {StringBuilder sb_szAddress;
                  if ( szAddress == null )
                     sb_szAddress = new StringBuilder( 32 );
                  else
                     sb_szAddress = new StringBuilder( szAddress );
                                     GetStringFromAttribute( sb_szAddress, mDrvShiftRoutes, "FromDeliveryLegAddress", "Line1" );
                  szAddress = sb_szAddress.toString( );}
                   {StringBuilder sb_szAddress;
                  if ( szAddress == null )
                     sb_szAddress = new StringBuilder( 32 );
                  else
                     sb_szAddress = new StringBuilder( szAddress );
                                    ZeidonStringConcat( sb_szAddress, 1, 0, ", ", 1, 0, 501 );
                  szAddress = sb_szAddress.toString( );}
                  {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                  StringBuilder sb_szTempString_4;
                  if ( szTempString_4 == null )
                     sb_szTempString_4 = new StringBuilder( 32 );
                  else
                     sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                     GetVariableFromAttribute( sb_szTempString_4, mi_lTempInteger_7, 'S', 101, mDrvShiftRoutes, "FromDeliveryLegAddress", "City", "", 0 );
                  lTempInteger_7 = mi_lTempInteger_7.intValue( );
                  szTempString_4 = sb_szTempString_4.toString( );}
                   {StringBuilder sb_szAddress;
                  if ( szAddress == null )
                     sb_szAddress = new StringBuilder( 32 );
                  else
                     sb_szAddress = new StringBuilder( szAddress );
                                    ZeidonStringConcat( sb_szAddress, 1, 0, szTempString_4, 1, 0, 501 );
                  szAddress = sb_szAddress.toString( );}
                  //:szExistingLocation = "<img src='./images/homebase.png' alt='Home Base' class='iconstd'>"  + "HB - " + szAddress
                   {StringBuilder sb_szExistingLocation;
                  if ( szExistingLocation == null )
                     sb_szExistingLocation = new StringBuilder( 32 );
                  else
                     sb_szExistingLocation = new StringBuilder( szExistingLocation );
                                    ZeidonStringCopy( sb_szExistingLocation, 1, 0, "<img src='./images/homebase.png' alt='Home Base' class='iconstd'>", 1, 0, 501 );
                  szExistingLocation = sb_szExistingLocation.toString( );}
                   {StringBuilder sb_szExistingLocation;
                  if ( szExistingLocation == null )
                     sb_szExistingLocation = new StringBuilder( 32 );
                  else
                     sb_szExistingLocation = new StringBuilder( szExistingLocation );
                                    ZeidonStringConcat( sb_szExistingLocation, 1, 0, "HB - ", 1, 0, 501 );
                  szExistingLocation = sb_szExistingLocation.toString( );}
                   {StringBuilder sb_szExistingLocation;
                  if ( szExistingLocation == null )
                     sb_szExistingLocation = new StringBuilder( 32 );
                  else
                     sb_szExistingLocation = new StringBuilder( szExistingLocation );
                                    ZeidonStringConcat( sb_szExistingLocation, 1, 0, szAddress, 1, 0, 501 );
                  szExistingLocation = sb_szExistingLocation.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szExistingLocation = ""
                   {StringBuilder sb_szExistingLocation;
                  if ( szExistingLocation == null )
                     sb_szExistingLocation = new StringBuilder( 32 );
                  else
                     sb_szExistingLocation = new StringBuilder( szExistingLocation );
                                    ZeidonStringCopy( sb_szExistingLocation, 1, 0, "", 1, 0, 501 );
                  szExistingLocation = sb_szExistingLocation.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szExistingLocation )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szExistingLocation );
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
//:dToLegOrderNo( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   STRING ( 20 ) szToLocationOrderNumber
public int 
omDrvShiftRoutes_dToLegOrderNo( View     mDrvShiftRoutes,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   String   szToLocationOrderNumber = null;
   //:STRING ( 1 )  szRouteType
   String   szRouteType = null;
   int      RESULT = 0;
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

         //:// The derived Order No. depends on the Order Type.
         //:// For LTL Orders, which can have multiple Orders for a Route:
         //://    If this Leg is to a Delivery Location, get the Order No from any Fuel Request for the corresponding Fuel Stop for that Location.
         //:// For Regular Orders, which have only a single Order for the whole Route:
         //://    This is the Order No. under the Load Request.
         //:SET CURSOR FIRST mDrvShiftRoutes.Order WITHIN mDrvShiftRoutes.DeliveryRoute 
         RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "Order", "DeliveryRoute" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:szRouteType = "LTL"
             {StringBuilder sb_szRouteType;
            if ( szRouteType == null )
               sb_szRouteType = new StringBuilder( 32 );
            else
               sb_szRouteType = new StringBuilder( szRouteType );
                        ZeidonStringCopy( sb_szRouteType, 1, 0, "LTL", 1, 0, 2 );
            szRouteType = sb_szRouteType.toString( );}
            //:ELSE 
         } 
         else
         { 
            //:szRouteType = mDrvShiftRoutes.Order.Type
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szRouteType;
            if ( szRouteType == null )
               sb_szRouteType = new StringBuilder( 32 );
            else
               sb_szRouteType = new StringBuilder( szRouteType );
                         GetVariableFromAttribute( sb_szRouteType, mi_lTempInteger_0, 'S', 2, mDrvShiftRoutes, "Order", "Type", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szRouteType = sb_szRouteType.toString( );}
         } 

         //:END
         //:IF szRouteType = "LTL"     // Any Order will have the LTL value set for LTL Orders
         if ( ZeidonStringCompare( szRouteType, 1, 0, "LTL", 1, 0, 2 ) == 0 )
         { 
            //:szToLocationOrderNumber = ""
             {StringBuilder sb_szToLocationOrderNumber;
            if ( szToLocationOrderNumber == null )
               sb_szToLocationOrderNumber = new StringBuilder( 32 );
            else
               sb_szToLocationOrderNumber = new StringBuilder( szToLocationOrderNumber );
                        ZeidonStringCopy( sb_szToLocationOrderNumber, 1, 0, "", 1, 0, 21 );
            szToLocationOrderNumber = sb_szToLocationOrderNumber.toString( );}
            //:IF mDrvShiftRoutes.ToDeliveryLegLocation EXISTS
            lTempInteger_1 = CheckExistenceOfEntity( mDrvShiftRoutes, "ToDeliveryLegLocation" );
            if ( lTempInteger_1 == 0 )
            { 
               //:SET CURSOR FIRST mDrvShiftRoutes.FuelStopDeliveryLocation WITHIN mDrvShiftRoutes.DeliveryRoute 
               //:     WHERE mDrvShiftRoutes.FuelStopDeliveryLocation.ID = mDrvShiftRoutes.ToDeliveryLegLocation.ID
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                               GetIntegerFromAttribute( mi_lTempInteger_2, mDrvShiftRoutes, "ToDeliveryLegLocation", "ID" );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mDrvShiftRoutes, "FuelStopDeliveryLocation", "ID", lTempInteger_2, "DeliveryRoute" );
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:szToLocationOrderNumber = mDrvShiftRoutes.FuelStopOrder.InternalOrderNumber 
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szToLocationOrderNumber;
                  if ( szToLocationOrderNumber == null )
                     sb_szToLocationOrderNumber = new StringBuilder( 32 );
                  else
                     sb_szToLocationOrderNumber = new StringBuilder( szToLocationOrderNumber );
                                     GetVariableFromAttribute( sb_szToLocationOrderNumber, mi_lTempInteger_3, 'S', 21, mDrvShiftRoutes, "FuelStopOrder", "InternalOrderNumber", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szToLocationOrderNumber = sb_szToLocationOrderNumber.toString( );}
               } 

               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:szToLocationOrderNumber = mDrvShiftRoutes.Order.InternalOrderNumber 
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szToLocationOrderNumber;
            if ( szToLocationOrderNumber == null )
               sb_szToLocationOrderNumber = new StringBuilder( 32 );
            else
               sb_szToLocationOrderNumber = new StringBuilder( szToLocationOrderNumber );
                         GetVariableFromAttribute( sb_szToLocationOrderNumber, mi_lTempInteger_4, 'S', 21, mDrvShiftRoutes, "Order", "InternalOrderNumber", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szToLocationOrderNumber = sb_szToLocationOrderNumber.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szToLocationOrderNumber )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szToLocationOrderNumber );
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
//:dRouteSortNo( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )

//:   VIEW mDrvShiftRoutes2 BASED ON LOD mDrvShiftRoutes
public int 
omDrvShiftRoutes_dRouteSortNo( View     mDrvShiftRoutes,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   zVIEW    mDrvShiftRoutes2 = new zVIEW( );
   //:INTEGER Count
   int      Count = 0;
   //:INTEGER CurrentRouteID
   int      CurrentRouteID = 0;
   int      RESULT = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Determine the relative number of this Route within the Shift.
         //:Count = 1
         Count = 1;
         //:CurrentRouteID = mDrvShiftRoutes.DeliveryRoute.ID 
         {MutableInt mi_CurrentRouteID = new MutableInt( CurrentRouteID );
                   GetIntegerFromAttribute( mi_CurrentRouteID, mDrvShiftRoutes, "DeliveryRoute", "ID" );
         CurrentRouteID = mi_CurrentRouteID.intValue( );}
         //:CreateViewFromView( mDrvShiftRoutes2, mDrvShiftRoutes )
         CreateViewFromView( mDrvShiftRoutes2, mDrvShiftRoutes );
         //:SET CURSOR FIRST mDrvShiftRoutes2.DeliveryRoute 
         RESULT = SetCursorFirstEntity( mDrvShiftRoutes2, "DeliveryRoute", "" );
         //:LOOP WHILE RESULT >= zCURSOR_SET AND mDrvShiftRoutes2.DeliveryRoute.ID != CurrentRouteID
         while ( RESULT >= zCURSOR_SET && CompareAttributeToInteger( mDrvShiftRoutes2, "DeliveryRoute", "ID", CurrentRouteID ) != 0 )
         { 
            //:Count = Count + 1
            Count = Count + 1;
            //:SET CURSOR NEXT mDrvShiftRoutes2.DeliveryRoute  
            RESULT = SetCursorNextEntity( mDrvShiftRoutes2, "DeliveryRoute", "" );
         } 

         //:END 
         //:DropView( mDrvShiftRoutes2 )
         DropView( mDrvShiftRoutes2 );

         //:// Store the calculated value in the object.
         //:StoreValueInRecord ( mDrvShiftRoutes,
         //:               InternalEntityStructure, InternalAttribStructure, Count, 0 )
         StoreValueInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, Count, 0 );
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
//:dLegType( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:          STRING ( 32 ) InternalEntityStructure,
//:          STRING ( 32 ) InternalAttribStructure,
//:          SHORT GetOrSetFlag )

//:   STRING ( 500 ) szType
public int 
omDrvShiftRoutes_dLegType( View     mDrvShiftRoutes,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   String   szType = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Set Type from existance of subentities.
         //:szType = ""
          {StringBuilder sb_szType;
         if ( szType == null )
            sb_szType = new StringBuilder( 32 );
         else
            sb_szType = new StringBuilder( szType );
                  ZeidonStringCopy( sb_szType, 1, 0, "", 1, 0, 501 );
         szType = sb_szType.toString( );}
         //:// From part of Type
         //:IF mDrvShiftRoutes.Initial_HB_Carrier EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mDrvShiftRoutes, "Initial_HB_Carrier" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szType = "H-"
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringCopy( sb_szType, 1, 0, "H-", 1, 0, 501 );
            szType = sb_szType.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mDrvShiftRoutes.FromDeliveryLegTerminal EXISTS
            lTempInteger_1 = CheckExistenceOfEntity( mDrvShiftRoutes, "FromDeliveryLegTerminal" );
            if ( lTempInteger_1 == 0 )
            { 
               //:szType = "T-"
                {StringBuilder sb_szType;
               if ( szType == null )
                  sb_szType = new StringBuilder( 32 );
               else
                  sb_szType = new StringBuilder( szType );
                              ZeidonStringCopy( sb_szType, 1, 0, "T-", 1, 0, 501 );
               szType = sb_szType.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szType = "D-"
                {StringBuilder sb_szType;
               if ( szType == null )
                  sb_szType = new StringBuilder( 32 );
               else
                  sb_szType = new StringBuilder( szType );
                              ZeidonStringCopy( sb_szType, 1, 0, "D-", 1, 0, 501 );
               szType = sb_szType.toString( );}
            } 

            //:END 
         } 

         //:END

         //:// To part of Type
         //:IF mDrvShiftRoutes.ToDeliveryLegTerminal EXISTS
         lTempInteger_2 = CheckExistenceOfEntity( mDrvShiftRoutes, "ToDeliveryLegTerminal" );
         if ( lTempInteger_2 == 0 )
         { 
            //:szType = szType + "T"
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringConcat( sb_szType, 1, 0, "T", 1, 0, 501 );
            szType = sb_szType.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mDrvShiftRoutes.ToDeliveryLegLocation EXISTS
            lTempInteger_3 = CheckExistenceOfEntity( mDrvShiftRoutes, "ToDeliveryLegLocation" );
            if ( lTempInteger_3 == 0 )
            { 
               //:szType = szType + "D"
                {StringBuilder sb_szType;
               if ( szType == null )
                  sb_szType = new StringBuilder( 32 );
               else
                  sb_szType = new StringBuilder( szType );
                              ZeidonStringConcat( sb_szType, 1, 0, "D", 1, 0, 501 );
               szType = sb_szType.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szType = szType + "H"
                {StringBuilder sb_szType;
               if ( szType == null )
                  sb_szType = new StringBuilder( 32 );
               else
                  sb_szType = new StringBuilder( szType );
                              ZeidonStringConcat( sb_szType, 1, 0, "H", 1, 0, 501 );
               szType = sb_szType.toString( );}
            } 

            //:END 
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szType )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szType );
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
//:dLegStatus( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:          STRING ( 32 ) InternalEntityStructure,
//:          STRING ( 32 ) InternalAttribStructure,
//:          SHORT GetOrSetFlag )

//:   STRING ( 500 ) szType
public int 
omDrvShiftRoutes_dLegStatus( View     mDrvShiftRoutes,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szType = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Set Type from existance of subentities.
         //:szType = ""
          {StringBuilder sb_szType;
         if ( szType == null )
            sb_szType = new StringBuilder( 32 );
         else
            sb_szType = new StringBuilder( szType );
                  ZeidonStringCopy( sb_szType, 1, 0, "", 1, 0, 501 );
         szType = sb_szType.toString( );}

         //:    IF  mDrvShiftRoutes.DeliveryLeg.ActualEndDateTime != ""
         if ( CompareAttributeToString( mDrvShiftRoutes, "DeliveryLeg", "ActualEndDateTime", "" ) != 0 )
         { 
            //: szType =  "<img src='./images/leg_complete.png' alt='Complete' class='iconstd'>"
             {StringBuilder sb_szType;
            if ( szType == null )
               sb_szType = new StringBuilder( 32 );
            else
               sb_szType = new StringBuilder( szType );
                        ZeidonStringCopy( sb_szType, 1, 0, "<img src='./images/leg_complete.png' alt='Complete' class='iconstd'>", 1, 0, 501 );
            szType = sb_szType.toString( );}
            //: ELSE    
         } 
         else
         { 
            //:     IF  mDrvShiftRoutes.DeliveryLeg.ActualEndDateTime = "" AND mDrvShiftRoutes.DeliveryLeg.ActualStartDateTime != ""
            if ( CompareAttributeToString( mDrvShiftRoutes, "DeliveryLeg", "ActualEndDateTime", "" ) == 0 && CompareAttributeToString( mDrvShiftRoutes, "DeliveryLeg", "ActualStartDateTime", "" ) != 0 )
            { 
               //:  szType =  "<img src='./images/leg_inprocess.png' alt='In Process' class='iconstd'>"             
                {StringBuilder sb_szType;
               if ( szType == null )
                  sb_szType = new StringBuilder( 32 );
               else
                  sb_szType = new StringBuilder( szType );
                              ZeidonStringCopy( sb_szType, 1, 0, "<img src='./images/leg_inprocess.png' alt='In Process' class='iconstd'>", 1, 0, 501 );
               szType = sb_szType.toString( );}
               //:  ELSE
            } 
            else
            { 
               //:  szType =  "<img src='./images/leg_pending.png' alt='Pending' class='iconstd'>"
                {StringBuilder sb_szType;
               if ( szType == null )
                  sb_szType = new StringBuilder( 32 );
               else
                  sb_szType = new StringBuilder( szType );
                              ZeidonStringCopy( sb_szType, 1, 0, "<img src='./images/leg_pending.png' alt='Pending' class='iconstd'>", 1, 0, 501 );
               szType = sb_szType.toString( );}
            } 

            //:     END
         } 

         //:    END



         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szType )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szType );
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
//:dLoadUnloadTime( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )

//:   VIEW mDrvShiftRoutes2 BASED ON LOD mDrvShiftRoutes
public int 
omDrvShiftRoutes_dLoadUnloadTime( View     mDrvShiftRoutes,
                                  String InternalEntityStructure,
                                  String InternalAttribStructure,
                                  Integer   GetOrSetFlag )
{
   zVIEW    mDrvShiftRoutes2 = new zVIEW( );
   //:INTEGER LoadUnloadTime
   int      LoadUnloadTime = 0;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Position on the associated Fuel Stop for the Leg and get the Load/Unload times.
         //:CreateViewFromView( mDrvShiftRoutes2, mDrvShiftRoutes )
         CreateViewFromView( mDrvShiftRoutes2, mDrvShiftRoutes );
         //:LoadUnloadTime = 0
         LoadUnloadTime = 0;
         //:IF mDrvShiftRoutes.ToDeliveryLegTerminal EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mDrvShiftRoutes, "ToDeliveryLegTerminal" );
         if ( lTempInteger_0 == 0 )
         { 
            //:SET CURSOR FIRST mDrvShiftRoutes2.FuelStopTerminal WITHIN mDrvShiftRoutes2.DeliveryRoute 
            //:     WHERE mDrvShiftRoutes2.FuelStopTerminal.ID = mDrvShiftRoutes2.ToDeliveryLegTerminal.ID  
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                         GetIntegerFromAttribute( mi_lTempInteger_1, mDrvShiftRoutes2, "ToDeliveryLegTerminal", "ID" );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mDrvShiftRoutes2, "FuelStopTerminal", "ID", lTempInteger_1, "DeliveryRoute" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:IF mDrvShiftRoutes2.FuelStop.ActualLoadTimeMinutes != ""
               if ( CompareAttributeToString( mDrvShiftRoutes2, "FuelStop", "ActualLoadTimeMinutes", "" ) != 0 )
               { 
                  //:LoadUnloadTime = mDrvShiftRoutes2.FuelStop.ActualLoadTimeMinutes 
                  {MutableInt mi_LoadUnloadTime = new MutableInt( LoadUnloadTime );
                                     GetIntegerFromAttribute( mi_LoadUnloadTime, mDrvShiftRoutes2, "FuelStop", "ActualLoadTimeMinutes" );
                  LoadUnloadTime = mi_LoadUnloadTime.intValue( );}
                  //:ELSE
               } 
               else
               { 
                  //:LoadUnloadTime = mDrvShiftRoutes2.FuelStop.ExpectedLoadTimeMinutes 
                  {MutableInt mi_LoadUnloadTime = new MutableInt( LoadUnloadTime );
                                     GetIntegerFromAttribute( mi_LoadUnloadTime, mDrvShiftRoutes2, "FuelStop", "ExpectedLoadTimeMinutes" );
                  LoadUnloadTime = mi_LoadUnloadTime.intValue( );}
               } 

               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF mDrvShiftRoutes.ToDeliveryLegLocation EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mDrvShiftRoutes, "ToDeliveryLegLocation" );
            if ( lTempInteger_2 == 0 )
            { 
               //:SET CURSOR FIRST mDrvShiftRoutes2.FuelStopDeliveryLocation WITHIN mDrvShiftRoutes2.DeliveryRoute 
               //:     WHERE mDrvShiftRoutes2.FuelStopDeliveryLocation.ID = mDrvShiftRoutes2.ToDeliveryLegLocation.ID  
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                               GetIntegerFromAttribute( mi_lTempInteger_3, mDrvShiftRoutes2, "ToDeliveryLegLocation", "ID" );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mDrvShiftRoutes2, "FuelStopDeliveryLocation", "ID", lTempInteger_3, "DeliveryRoute" );
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:IF mDrvShiftRoutes2.FuelStop.ActualUnloadTimeMinutes != ""
                  if ( CompareAttributeToString( mDrvShiftRoutes2, "FuelStop", "ActualUnloadTimeMinutes", "" ) != 0 )
                  { 
                     //:LoadUnloadTime = mDrvShiftRoutes2.FuelStop.ActualUnloadTimeMinutes 
                     {MutableInt mi_LoadUnloadTime = new MutableInt( LoadUnloadTime );
                                           GetIntegerFromAttribute( mi_LoadUnloadTime, mDrvShiftRoutes2, "FuelStop", "ActualUnloadTimeMinutes" );
                     LoadUnloadTime = mi_LoadUnloadTime.intValue( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:LoadUnloadTime = mDrvShiftRoutes2.FuelStop.ExpectedUnloadTimeMinutes 
                     {MutableInt mi_LoadUnloadTime = new MutableInt( LoadUnloadTime );
                                           GetIntegerFromAttribute( mi_LoadUnloadTime, mDrvShiftRoutes2, "FuelStop", "ExpectedUnloadTimeMinutes" );
                     LoadUnloadTime = mi_LoadUnloadTime.intValue( );}
                  } 

                  //:END
               } 

               //:END
            } 

            //:END 
         } 

         //:END
         //:DropView( mDrvShiftRoutes2 )
         DropView( mDrvShiftRoutes2 );

         //:StoreValueInRecord( mDrvShiftRoutes, InternalEntityStructure, 
         //:              InternalAttribStructure, LoadUnloadTime, 0 )
         StoreValueInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, LoadUnloadTime, 0 );
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
//:dLegTravelTime( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   INTEGER TravelTimeMinutes
public int 
omDrvShiftRoutes_dLegTravelTime( View     mDrvShiftRoutes,
                                 String InternalEntityStructure,
                                 String InternalAttribStructure,
                                 Integer   GetOrSetFlag )
{
   int      TravelTimeMinutes = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Compute the Travel Time in minutes between the start and end data/times.
         //:IF mDrvShiftRoutes.DeliveryLeg.ActualStartDateTime != "" AND mDrvShiftRoutes.DeliveryLeg.ActualEndDateTime != ""
         if ( CompareAttributeToString( mDrvShiftRoutes, "DeliveryLeg", "ActualStartDateTime", "" ) != 0 && CompareAttributeToString( mDrvShiftRoutes, "DeliveryLeg", "ActualEndDateTime", "" ) != 0 )
         { 
            //:TravelTimeMinutes = GetTimeDifferenceInMinutes( mDrvShiftRoutes, "DeliveryLeg", "ActualStartDateTime", 
            //:                                          mDrvShiftRoutes, "DeliveryLeg", "ActualEndDateTime" )
            {
             //ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mDrvShiftRoutes );
             //TravelTimeMinutes = m_ZGLOBAL2_Operation.GetTimeDifferenceInMinutes( mDrvShiftRoutes, "DeliveryLeg", "ActualStartDateTime", mDrvShiftRoutes, "DeliveryLeg", "ActualEndDateTime" );
             // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
            }
            //:StoreValueInRecord( mDrvShiftRoutes, InternalEntityStructure, 
            //:              InternalAttribStructure, TravelTimeMinutes, 0 )
            StoreValueInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, TravelTimeMinutes, 0 );
            //:ELSE
         } 
         else
         { 
            //:// Store null.
            //:StoreStringInRecord ( mDrvShiftRoutes,
            //:                InternalEntityStructure, InternalAttribStructure, "" )
            StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, "" );
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
//:dFS_Description( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )

//:   STRING ( 500 ) szDescription
public int 
omDrvShiftRoutes_dFS_Description( View     mDrvShiftRoutes,
                                  String InternalEntityStructure,
                                  String InternalAttribStructure,
                                  Integer   GetOrSetFlag )
{
   String   szDescription = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Set the Description from the Terminal or DeliveryLocation name.
         //:szDescription = ""
          {StringBuilder sb_szDescription;
         if ( szDescription == null )
            sb_szDescription = new StringBuilder( 32 );
         else
            sb_szDescription = new StringBuilder( szDescription );
                  ZeidonStringCopy( sb_szDescription, 1, 0, "", 1, 0, 501 );
         szDescription = sb_szDescription.toString( );}
         //:IF mDrvShiftRoutes.FuelStopTerminal EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminal" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szDescription = "<img src='./images/terminal.png' alt='Terminal' class='iconstd'>"  +  "  Load At: " +mDrvShiftRoutes.FuelStopTerminal.Name 
             {StringBuilder sb_szDescription;
            if ( szDescription == null )
               sb_szDescription = new StringBuilder( 32 );
            else
               sb_szDescription = new StringBuilder( szDescription );
                        ZeidonStringCopy( sb_szDescription, 1, 0, "<img src='./images/terminal.png' alt='Terminal' class='iconstd'>", 1, 0, 501 );
            szDescription = sb_szDescription.toString( );}
             {StringBuilder sb_szDescription;
            if ( szDescription == null )
               sb_szDescription = new StringBuilder( 32 );
            else
               sb_szDescription = new StringBuilder( szDescription );
                        ZeidonStringConcat( sb_szDescription, 1, 0, "  Load At: ", 1, 0, 501 );
            szDescription = sb_szDescription.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 255, mDrvShiftRoutes, "FuelStopTerminal", "Name", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szDescription;
            if ( szDescription == null )
               sb_szDescription = new StringBuilder( 32 );
            else
               sb_szDescription = new StringBuilder( szDescription );
                        ZeidonStringConcat( sb_szDescription, 1, 0, szTempString_0, 1, 0, 501 );
            szDescription = sb_szDescription.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mDrvShiftRoutes.FuelStopDeliveryLocation EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopDeliveryLocation" );
            if ( lTempInteger_2 == 0 )
            { 
               //:szDescription = "<img src='./images/deliverysite.png' alt='Delivery Site' class='iconstd'>"  + "  Delivery To: " + mDrvShiftRoutes.FuelStopDeliveryLocation.Name 
                {StringBuilder sb_szDescription;
               if ( szDescription == null )
                  sb_szDescription = new StringBuilder( 32 );
               else
                  sb_szDescription = new StringBuilder( szDescription );
                              ZeidonStringCopy( sb_szDescription, 1, 0, "<img src='./images/deliverysite.png' alt='Delivery Site' class='iconstd'>", 1, 0, 501 );
               szDescription = sb_szDescription.toString( );}
                {StringBuilder sb_szDescription;
               if ( szDescription == null )
                  sb_szDescription = new StringBuilder( 32 );
               else
                  sb_szDescription = new StringBuilder( szDescription );
                              ZeidonStringConcat( sb_szDescription, 1, 0, "  Delivery To: ", 1, 0, 501 );
               szDescription = sb_szDescription.toString( );}
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_3, 'S', 255, mDrvShiftRoutes, "FuelStopDeliveryLocation", "Name", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szTempString_1 = sb_szTempString_1.toString( );}
                {StringBuilder sb_szDescription;
               if ( szDescription == null )
                  sb_szDescription = new StringBuilder( 32 );
               else
                  sb_szDescription = new StringBuilder( szDescription );
                              ZeidonStringConcat( sb_szDescription, 1, 0, szTempString_1, 1, 0, 501 );
               szDescription = sb_szDescription.toString( );}
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szDescription )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szDescription );
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
//:dFS_Type( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )

//:   STRING ( 500 ) szDescription
public int 
omDrvShiftRoutes_dFS_Type( View     mDrvShiftRoutes,
                           String InternalEntityStructure,
                           String InternalAttribStructure,
                           Integer   GetOrSetFlag )
{
   String   szDescription = null;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Set the Description from the Terminal or DeliveryLocation name.
         //:szDescription = ""
          {StringBuilder sb_szDescription;
         if ( szDescription == null )
            sb_szDescription = new StringBuilder( 32 );
         else
            sb_szDescription = new StringBuilder( szDescription );
                  ZeidonStringCopy( sb_szDescription, 1, 0, "", 1, 0, 501 );
         szDescription = sb_szDescription.toString( );}
         //:IF mDrvShiftRoutes.FuelStopTerminal EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminal" );
         if ( lTempInteger_0 == 0 )
         { 
            //:IF mDrvShiftRoutes.FuelStop.LoadStartDateTime != "" AND mDrvShiftRoutes.FuelStop.LoadEndDateTime != "" 
            if ( CompareAttributeToString( mDrvShiftRoutes, "FuelStop", "LoadStartDateTime", "" ) != 0 && CompareAttributeToString( mDrvShiftRoutes, "FuelStop", "LoadEndDateTime", "" ) != 0 )
            { 
               //:szDescription = "<img src='./images/fs_loading_complete.png' alt='Delivery Site' class='iconstd'>"
                {StringBuilder sb_szDescription;
               if ( szDescription == null )
                  sb_szDescription = new StringBuilder( 32 );
               else
                  sb_szDescription = new StringBuilder( szDescription );
                              ZeidonStringCopy( sb_szDescription, 1, 0, "<img src='./images/fs_loading_complete.png' alt='Delivery Site' class='iconstd'>", 1, 0, 501 );
               szDescription = sb_szDescription.toString( );}
            } 

            //:END
            //:IF mDrvShiftRoutes.FuelStop.LoadStartDateTime != "" AND mDrvShiftRoutes.FuelStop.LoadEndDateTime = "" 
            if ( CompareAttributeToString( mDrvShiftRoutes, "FuelStop", "LoadStartDateTime", "" ) != 0 && CompareAttributeToString( mDrvShiftRoutes, "FuelStop", "LoadEndDateTime", "" ) == 0 )
            { 
               //:szDescription = "<img src='./images/fs_loading.png' alt='Delivery Site' class='iconstd'>"
                {StringBuilder sb_szDescription;
               if ( szDescription == null )
                  sb_szDescription = new StringBuilder( 32 );
               else
                  sb_szDescription = new StringBuilder( szDescription );
                              ZeidonStringCopy( sb_szDescription, 1, 0, "<img src='./images/fs_loading.png' alt='Delivery Site' class='iconstd'>", 1, 0, 501 );
               szDescription = sb_szDescription.toString( );}
            } 

            //:END
            //:IF mDrvShiftRoutes.FuelStop.LoadStartDateTime = "" AND mDrvShiftRoutes.FuelStop.LoadEndDateTime = "" 
            if ( CompareAttributeToString( mDrvShiftRoutes, "FuelStop", "LoadStartDateTime", "" ) == 0 && CompareAttributeToString( mDrvShiftRoutes, "FuelStop", "LoadEndDateTime", "" ) == 0 )
            { 
               //:szDescription = "<img src='./images/fs_scheduled.png' alt='Delivery Site' class='iconstd'>"
                {StringBuilder sb_szDescription;
               if ( szDescription == null )
                  sb_szDescription = new StringBuilder( 32 );
               else
                  sb_szDescription = new StringBuilder( szDescription );
                              ZeidonStringCopy( sb_szDescription, 1, 0, "<img src='./images/fs_scheduled.png' alt='Delivery Site' class='iconstd'>", 1, 0, 501 );
               szDescription = sb_szDescription.toString( );}
            } 

            //:END

            //:ELSE
         } 
         else
         { 
            //:IF mDrvShiftRoutes.FuelStop.UnloadStartDateTime != "" AND mDrvShiftRoutes.FuelStop.UnloadEndDateTime != "" 
            if ( CompareAttributeToString( mDrvShiftRoutes, "FuelStop", "UnloadStartDateTime", "" ) != 0 && CompareAttributeToString( mDrvShiftRoutes, "FuelStop", "UnloadEndDateTime", "" ) != 0 )
            { 
               //:szDescription = "<img src='./images/fs_unloading_complete.png' alt='Delivery Site' class='iconstd'>"
                {StringBuilder sb_szDescription;
               if ( szDescription == null )
                  sb_szDescription = new StringBuilder( 32 );
               else
                  sb_szDescription = new StringBuilder( szDescription );
                              ZeidonStringCopy( sb_szDescription, 1, 0, "<img src='./images/fs_unloading_complete.png' alt='Delivery Site' class='iconstd'>", 1, 0, 501 );
               szDescription = sb_szDescription.toString( );}
            } 

            //:END
            //:IF mDrvShiftRoutes.FuelStop.UnloadStartDateTime != "" AND mDrvShiftRoutes.FuelStop.UnloadEndDateTime = "" 
            if ( CompareAttributeToString( mDrvShiftRoutes, "FuelStop", "UnloadStartDateTime", "" ) != 0 && CompareAttributeToString( mDrvShiftRoutes, "FuelStop", "UnloadEndDateTime", "" ) == 0 )
            { 
               //:szDescription = "<img src='./images/fs_unloading.png' alt='Delivery Site' class='iconstd'>"
                {StringBuilder sb_szDescription;
               if ( szDescription == null )
                  sb_szDescription = new StringBuilder( 32 );
               else
                  sb_szDescription = new StringBuilder( szDescription );
                              ZeidonStringCopy( sb_szDescription, 1, 0, "<img src='./images/fs_unloading.png' alt='Delivery Site' class='iconstd'>", 1, 0, 501 );
               szDescription = sb_szDescription.toString( );}
            } 

            //:END
            //:IF mDrvShiftRoutes.FuelStop.UnloadStartDateTime = "" AND mDrvShiftRoutes.FuelStop.UnloadEndDateTime = "" 
            if ( CompareAttributeToString( mDrvShiftRoutes, "FuelStop", "UnloadStartDateTime", "" ) == 0 && CompareAttributeToString( mDrvShiftRoutes, "FuelStop", "UnloadEndDateTime", "" ) == 0 )
            { 
               //:szDescription = "<img src='./images/fs_scheduled.png' alt='Delivery Site' class='iconstd'>"
                {StringBuilder sb_szDescription;
               if ( szDescription == null )
                  sb_szDescription = new StringBuilder( 32 );
               else
                  sb_szDescription = new StringBuilder( szDescription );
                              ZeidonStringCopy( sb_szDescription, 1, 0, "<img src='./images/fs_scheduled.png' alt='Delivery Site' class='iconstd'>", 1, 0, 501 );
               szDescription = sb_szDescription.toString( );}
            } 

            //:END
         } 


         //:END
         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szDescription )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szDescription );
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
//:dFuelStopAddr( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   STRING ( 500 ) szAddress
public int 
omDrvShiftRoutes_dFuelStopAddr( View     mDrvShiftRoutes,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   String   szAddress = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// This is the derived Address of either the Terminal or the Delivery Location for the Fuel Stop.
         //:IF mDrvShiftRoutes.FuelStopTerminal EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminal" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szAddress = mDrvShiftRoutes.FuelStopTerminalAddress.Line1 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szAddress;
            if ( szAddress == null )
               sb_szAddress = new StringBuilder( 32 );
            else
               sb_szAddress = new StringBuilder( szAddress );
                         GetVariableFromAttribute( sb_szAddress, mi_lTempInteger_1, 'S', 501, mDrvShiftRoutes, "FuelStopTerminalAddress", "Line1", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szAddress = sb_szAddress.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF mDrvShiftRoutes.FuelStopDeliveryLocation EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopDeliveryLocation" );
            if ( lTempInteger_2 == 0 )
            { 
               //:szAddress = mDrvShiftRoutes.FuelStopDeliveryLocationAddress.Line1 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szAddress;
               if ( szAddress == null )
                  sb_szAddress = new StringBuilder( 32 );
               else
                  sb_szAddress = new StringBuilder( szAddress );
                               GetVariableFromAttribute( sb_szAddress, mi_lTempInteger_3, 'S', 501, mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", "Line1", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szAddress = sb_szAddress.toString( );}
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szAddress )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szAddress );
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
//:dFuelStopCity( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   STRING ( 500 ) szAddress
public int 
omDrvShiftRoutes_dFuelStopCity( View     mDrvShiftRoutes,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   String   szAddress = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// This is the derived Address of either the Terminal or the Delivery Location for the Fuel Stop.
         //:IF mDrvShiftRoutes.FuelStopTerminal EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminal" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szAddress = mDrvShiftRoutes.FuelStopTerminalAddress.Line1 + ", " +  mDrvShiftRoutes.FuelStopTerminalAddress.City ELSE
            {StringBuilder sb_szAddress;
            if ( szAddress == null )
               sb_szAddress = new StringBuilder( 32 );
            else
               sb_szAddress = new StringBuilder( szAddress );
                         GetStringFromAttribute( sb_szAddress, mDrvShiftRoutes, "FuelStopTerminalAddress", "Line1" );
            szAddress = sb_szAddress.toString( );}
             {StringBuilder sb_szAddress;
            if ( szAddress == null )
               sb_szAddress = new StringBuilder( 32 );
            else
               sb_szAddress = new StringBuilder( szAddress );
                        ZeidonStringConcat( sb_szAddress, 1, 0, ", ", 1, 0, 501 );
            szAddress = sb_szAddress.toString( );}
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 101, mDrvShiftRoutes, "FuelStopTerminalAddress", "City", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szAddress;
            if ( szAddress == null )
               sb_szAddress = new StringBuilder( 32 );
            else
               sb_szAddress = new StringBuilder( szAddress );
                        ZeidonStringConcat( sb_szAddress, 1, 0, szTempString_0, 1, 0, 501 );
            szAddress = sb_szAddress.toString( );}
         } 
         else
         { 
            //:IF mDrvShiftRoutes.FuelStopDeliveryLocation EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopDeliveryLocation" );
            if ( lTempInteger_2 == 0 )
            { 
               //:szAddress = mDrvShiftRoutes.FuelStopDeliveryLocationAddress.Line1 + ", " +  mDrvShiftRoutes.FuelStopDeliveryLocationAddress.City
               {StringBuilder sb_szAddress;
               if ( szAddress == null )
                  sb_szAddress = new StringBuilder( 32 );
               else
                  sb_szAddress = new StringBuilder( szAddress );
                               GetStringFromAttribute( sb_szAddress, mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", "Line1" );
               szAddress = sb_szAddress.toString( );}
                {StringBuilder sb_szAddress;
               if ( szAddress == null )
                  sb_szAddress = new StringBuilder( 32 );
               else
                  sb_szAddress = new StringBuilder( szAddress );
                              ZeidonStringConcat( sb_szAddress, 1, 0, ", ", 1, 0, 501 );
               szAddress = sb_szAddress.toString( );}
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_3, 'S', 101, mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", "City", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szTempString_1 = sb_szTempString_1.toString( );}
                {StringBuilder sb_szAddress;
               if ( szAddress == null )
                  sb_szAddress = new StringBuilder( 32 );
               else
                  sb_szAddress = new StringBuilder( szAddress );
                              ZeidonStringConcat( sb_szAddress, 1, 0, szTempString_1, 1, 0, 501 );
               szAddress = sb_szAddress.toString( );}
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szAddress )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szAddress );
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
//:ObjConstraints( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:                SHORT Event,
//:                SHORT State )

//:   VIEW mDrvShiftRoutes2 BASED ON LOD mDrvShiftRoutes
public int 
omDrvShiftRoutes_ObjConstraints( View     mDrvShiftRoutes,
                                 Integer   Event,
                                 Integer   State )
{
   zVIEW    mDrvShiftRoutes2 = new zVIEW( );
   //:INTEGER Count                
   int      Count = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:// Delete any LoadRequest entries without Orders.
         //:FOR EACH mDrvShiftRoutes.DeliveryRoute 
         RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:FOR EACH mDrvShiftRoutes.LoadRequest 
            RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "LoadRequest", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF mDrvShiftRoutes.Order DOES NOT EXIST
               lTempInteger_0 = CheckExistenceOfEntity( mDrvShiftRoutes, "Order" );
               if ( lTempInteger_0 != 0 )
               { 
                  //:DELETE ENTITY mDrvShiftRoutes.LoadRequest NONE 
                  RESULT = DeleteEntity( mDrvShiftRoutes, "LoadRequest", zREPOS_NONE );
               } 

               RESULT = SetCursorNextEntity( mDrvShiftRoutes, "LoadRequest", "" );
               //:END
            } 

            RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
            //:END
         } 

         //:END

         //:// Sort the Routes by the ScheduledDateTime value to make sure they are in the original correct order.
         //://OrderEntityForView( mDrvShiftRoutes, "DeliveryRoute", "ScheduledDateTime A" )
         //:// Changed by DonC on4/11/2017 because ScheduledDateTime may not be correct on an LTL Scheduled Route.
         //:OrderEntityForView( mDrvShiftRoutes, "DeliveryRoute", "ExpectedStartDateTime A" )
         OrderEntityForView( mDrvShiftRoutes, "DeliveryRoute", "ExpectedStartDateTime A" );
         //:SET CURSOR FIRST mDrvShiftRoutes.DeliveryRoute  
         RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );

         //:// If either the DeliveryLeg or FuelStop SortOrder values have been set, make sure the entities are sorted in that order.
         //:CreateViewFromView( mDrvShiftRoutes2, mDrvShiftRoutes )
         CreateViewFromView( mDrvShiftRoutes2, mDrvShiftRoutes );
         //:FOR EACH mDrvShiftRoutes2.DriverShift 
         RESULT = SetCursorFirstEntity( mDrvShiftRoutes2, "DriverShift", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:FOR EACH mDrvShiftRoutes2.DeliveryRoute 
            RESULT = SetCursorFirstEntity( mDrvShiftRoutes2, "DeliveryRoute", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:// If the FuelStop entities have SortOrder set, sort the entities in that order.
               //:IF mDrvShiftRoutes2.FuelStop.SortOrder != ""
               if ( CompareAttributeToString( mDrvShiftRoutes2, "FuelStop", "SortOrder", "" ) != 0 )
               { 
                  //:OrderEntityForView( mDrvShiftRoutes2, "FuelStop", "SortOrder A" )
                  OrderEntityForView( mDrvShiftRoutes2, "FuelStop", "SortOrder A" );
                  //:SET CURSOR FIRST mDrvShiftRoutes2.FuelStop  
                  RESULT = SetCursorFirstEntity( mDrvShiftRoutes2, "FuelStop", "" );
               } 

               //:END

               //:// If the DeliveryLeg entities have SortOrder set, sort the entities in that order.
               //:IF mDrvShiftRoutes2.DeliveryLeg.SortOrder != ""
               if ( CompareAttributeToString( mDrvShiftRoutes2, "DeliveryLeg", "SortOrder", "" ) != 0 )
               { 
                  //:OrderEntityForView( mDrvShiftRoutes2, "DeliveryLeg", "SortOrder A" )
                  OrderEntityForView( mDrvShiftRoutes2, "DeliveryLeg", "SortOrder A" );
                  //:SET CURSOR FIRST mDrvShiftRoutes2.DeliveryLeg  
                  RESULT = SetCursorFirstEntity( mDrvShiftRoutes2, "DeliveryLeg", "" );
               } 

               RESULT = SetCursorNextEntity( mDrvShiftRoutes2, "DeliveryRoute", "" );
               //:END
            } 

            RESULT = SetCursorNextEntity( mDrvShiftRoutes2, "DriverShift", "" );
            //:END
         } 

         //:END

         //:// Initialize sequencing attributes for DeliveryRoute.
         //:Count = 0
         Count = 0;
         //:FOR EACH mDrvShiftRoutes.DeliveryRoute 
         RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:Count = Count + 1
            Count = Count + 1;
            //:mDrvShiftRoutes.DeliveryRoute.wChangeOrderNumber = Count
            SetAttributeFromInteger( mDrvShiftRoutes, "DeliveryRoute", "wChangeOrderNumber", Count );
            RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
         } 

         //:END
         //:SET CURSOR FIRST mDrvShiftRoutes.DeliveryRoute  
         RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );

         //:// If any Delivery Leg has started, make sure the start date/time is also set in the Route.
         //:FOR EACH mDrvShiftRoutes2.DriverShift 
         RESULT = SetCursorFirstEntity( mDrvShiftRoutes2, "DriverShift", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:FOR EACH mDrvShiftRoutes2.DeliveryRoute 
            RESULT = SetCursorFirstEntity( mDrvShiftRoutes2, "DeliveryRoute", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF mDrvShiftRoutes2.DeliveryRoute.ActualStartDateTime = "" AND mDrvShiftRoutes2.DeliveryLeg.ActualStartDateTime != ""
               if ( CompareAttributeToString( mDrvShiftRoutes2, "DeliveryRoute", "ActualStartDateTime", "" ) == 0 && CompareAttributeToString( mDrvShiftRoutes2, "DeliveryLeg", "ActualStartDateTime", "" ) != 0 )
               { 
                  //:mDrvShiftRoutes2.DeliveryRoute.ActualStartDateTime = mDrvShiftRoutes2.DeliveryLeg.ActualStartDateTime 
                  SetAttributeFromAttribute( mDrvShiftRoutes2, "DeliveryRoute", "ActualStartDateTime", mDrvShiftRoutes2, "DeliveryLeg", "ActualStartDateTime" );
               } 

               RESULT = SetCursorNextEntity( mDrvShiftRoutes2, "DeliveryRoute", "" );
               //:END
            } 

            RESULT = SetCursorNextEntity( mDrvShiftRoutes2, "DriverShift", "" );
            //:END
         } 

         //:END
         //:DropView( mDrvShiftRoutes2 )
         DropView( mDrvShiftRoutes2 );
         break ;

      //:  /* end zOCE_ACTIVATE */
      //:OF   zOCE_ACTIVATE_EMPTY:
      case zOCE_ACTIVATE_EMPTY :
         break ;

      //:  /* end zOCE_ACTIVATE_EMPTY */
      //:OF   zOCE_COMMIT:
      case zOCE_COMMIT :

         //:// Set the SortOrder attributes for DeliveryLeg and FuelStop entities according to their current order.
         //:CreateViewFromView( mDrvShiftRoutes2, mDrvShiftRoutes )
         CreateViewFromView( mDrvShiftRoutes2, mDrvShiftRoutes );
         //:FOR EACH mDrvShiftRoutes2.DeliveryRoute 
         RESULT = SetCursorFirstEntity( mDrvShiftRoutes2, "DeliveryRoute", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:Count = 0
            Count = 0;
            //:FOR EACH mDrvShiftRoutes2.FuelStop 
            RESULT = SetCursorFirstEntity( mDrvShiftRoutes2, "FuelStop", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:Count = Count + 1
               Count = Count + 1;
               //:mDrvShiftRoutes2.FuelStop.SortOrder = Count
               SetAttributeFromInteger( mDrvShiftRoutes2, "FuelStop", "SortOrder", Count );
               RESULT = SetCursorNextEntity( mDrvShiftRoutes2, "FuelStop", "" );
            } 

            //:END
            //:SET CURSOR FIRST mDrvShiftRoutes2.FuelStop
            RESULT = SetCursorFirstEntity( mDrvShiftRoutes2, "FuelStop", "" );

            //:Count = 0
            Count = 0;
            //:FOR EACH mDrvShiftRoutes2.DeliveryLeg 
            RESULT = SetCursorFirstEntity( mDrvShiftRoutes2, "DeliveryLeg", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:Count = Count + 1
               Count = Count + 1;
               //:mDrvShiftRoutes2.DeliveryLeg.SortOrder = Count
               SetAttributeFromInteger( mDrvShiftRoutes2, "DeliveryLeg", "SortOrder", Count );
               RESULT = SetCursorNextEntity( mDrvShiftRoutes2, "DeliveryLeg", "" );
            } 

            //:END
            //:SET CURSOR FIRST mDrvShiftRoutes2.DeliveryLeg 
            RESULT = SetCursorFirstEntity( mDrvShiftRoutes2, "DeliveryLeg", "" );
            RESULT = SetCursorNextEntity( mDrvShiftRoutes2, "DeliveryRoute", "" );
         } 

         //:END
         //:DropView( mDrvShiftRoutes2 )
         DropView( mDrvShiftRoutes2 );

         //:// If Route is for LTL Orders, set Route and Order Statuses based on what functions of the Route and Orders have been completed.
         //:IF mDrvShiftRoutes.Order.Type = "LTL"
         if ( CompareAttributeToString( mDrvShiftRoutes, "Order", "Type", "LTL" ) == 0 )
         { 
            //:SetDeliveryRouteStatus( mDrvShiftRoutes )
            {
             //ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mDrvShiftRoutes );
             //m_ZGLOBAL2_Operation.SetDeliveryRouteStatus( mDrvShiftRoutes );
             // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
            }
         } 

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
//:ResetRouteLegs( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes )

//:   VIEW mVehicle            BASED ON LOD  mVehicle
public int 
omDrvShiftRoutes_ResetRouteLegs( View     mDrvShiftRoutes )
{
   zVIEW    mVehicle = new zVIEW( );
   //:VIEW mDrvShiftRoutesPrev BASED ON LOD  mDrvShiftRoutes
   zVIEW    mDrvShiftRoutesPrev = new zVIEW( );
   //:STRING ( 50 ) szTrace
   String   szTrace = null;
   //:STRING ( 1 )  szPreviousViewExists
   String   szPreviousViewExists = null;
   //:INTEGER Count
   int      Count = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
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
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   int      lTempInteger_13 = 0;
   int      lTempInteger_14 = 0;
   int      lTempInteger_15 = 0;
   int      lTempInteger_16 = 0;


   //:// Reset all Legs from the current order of Fuel Stops.
   //:// Delete all Legs first and then recreate them from the Fuel Stops 
   //:// Then recompute all Start/End date/time values.

   //:// Except for the first and last Leg of each Route, a Leg will be created from one Fuel Stop to the next Fuel Stop.
   //:// The first and last Legs of each Route will be generated as follows:
   //:// 1. If this is the first Route of the Shift, create an Initial Leg from Home Base to the first Stop.
   //:// 2. If the previous Route is for the same Vehicle, create a first Leg from the last Delivery Leg Address of the previous Route to the first
   //://    Fuel Stop of this Route..
   //:// 3. If the previous Route is for a different Vehicle, create a Final Leg to the previous Route from the last Fuel Stop tback to HB and 
   //://    create an Initial Leg to this Route from Home Base to the First Fuel Stop.

   //:// If all Routes have been deleted, just return.
   //:SET CURSOR FIRST mDrvShiftRoutes.DeliveryRoute 
   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:RETURN
      if(8==8)return( 0 );
   } 

   //:END 

   //:// Get Vehicle information to determine Home Base.
   //:ACTIVATE mVehicle WHERE mVehicle.VehiclePowerUnit.ID = mDrvShiftRoutes.VehiclePowerUnit.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mDrvShiftRoutes, "VehiclePowerUnit", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   omDrvShiftRoutes_fnLocalBuildQual_0( mDrvShiftRoutes, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mVehicle, "mVehicle", mDrvShiftRoutes, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mVehicle "mVehicle"
   SetNameForView( mVehicle, "mVehicle", null, zLEVEL_TASK );

   //:// Because there's been a data error where a Route is empty, delete any such Route.
   //:FOR EACH mDrvShiftRoutes.DeliveryRoute 
   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mDrvShiftRoutes.FuelStop DOES NOT EXIST
      lTempInteger_1 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStop" );
      if ( lTempInteger_1 != 0 )
      { 
         //:DELETE ENTITY mDrvShiftRoutes.DeliveryRoute NONE 
         RESULT = DeleteEntity( mDrvShiftRoutes, "DeliveryRoute", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
      //:END
   } 

   //:END

   //:// Make sure the Routes are in date/time order and the Legs and Routes are in Sort order.
   //:OrderEntityForView( mDrvShiftRoutes, "DeliveryRoute", "ExpectedStartDateTime A" )
   OrderEntityForView( mDrvShiftRoutes, "DeliveryRoute", "ExpectedStartDateTime A" );
   //:FOR EACH mDrvShiftRoutes.DeliveryRoute 
   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mDrvShiftRoutes, "DeliveryLeg", "SortOrder A" )
      OrderEntityForView( mDrvShiftRoutes, "DeliveryLeg", "SortOrder A" );
      //:OrderEntityForView( mDrvShiftRoutes, "FuelStop", "SortOrder A" )
      OrderEntityForView( mDrvShiftRoutes, "FuelStop", "SortOrder A" );
      RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
   } 

   //:END

   //:// Initialize Delivery Legs and Fuel Stops as follows:
   //:// For any Delivery Legs that have started, flag the corresponding Fuel Stop so that it won't create a new Delivery Leg.
   //:// For any Delivery Legs that have NOT started, delete them, because they will be recreated from Fuel Stops.
   //:FOR EACH mDrvShiftRoutes.DeliveryRoute 
   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH mDrvShiftRoutes.DeliveryLeg 
      RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryLeg", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mDrvShiftRoutes.DeliveryLeg.ActualStartDateTime = "" 
         if ( CompareAttributeToString( mDrvShiftRoutes, "DeliveryLeg", "ActualStartDateTime", "" ) == 0 )
         { 
            //:DELETE ENTITY mDrvShiftRoutes.DeliveryLeg NONE
            RESULT = DeleteEntity( mDrvShiftRoutes, "DeliveryLeg", zREPOS_NONE );
            //:ELSE
         } 
         else
         { 
            //:// Position on corresponding Fuel Stop and flag it.
            //:IF mDrvShiftRoutes.ToDeliveryLegTerminal EXISTS
            lTempInteger_2 = CheckExistenceOfEntity( mDrvShiftRoutes, "ToDeliveryLegTerminal" );
            if ( lTempInteger_2 == 0 )
            { 
               //:// Flag Terminal Fuel Stop.
               //:SET CURSOR FIRST mDrvShiftRoutes.FuelStopTerminal WITHIN mDrvShiftRoutes.DeliveryRoute 
               //:           WHERE mDrvShiftRoutes.FuelStopTerminal.ID = mDrvShiftRoutes.ToDeliveryLegTerminal.ID
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                               GetIntegerFromAttribute( mi_lTempInteger_3, mDrvShiftRoutes, "ToDeliveryLegTerminal", "ID" );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mDrvShiftRoutes, "FuelStopTerminal", "ID", lTempInteger_3, "DeliveryRoute" );
               //:mDrvShiftRoutes.FuelStop.wStartedDeliveryLegID = mDrvShiftRoutes.DeliveryLeg.ID 
               SetAttributeFromAttribute( mDrvShiftRoutes, "FuelStop", "wStartedDeliveryLegID", mDrvShiftRoutes, "DeliveryLeg", "ID" );
               //:ELSE
            } 
            else
            { 
               //:IF mDrvShiftRoutes.ToDeliveryLegLocation EXISTS
               lTempInteger_4 = CheckExistenceOfEntity( mDrvShiftRoutes, "ToDeliveryLegLocation" );
               if ( lTempInteger_4 == 0 )
               { 
                  //:// Flag Delivery Location Fuel Stop.
                  //:SET CURSOR FIRST mDrvShiftRoutes.FuelStopDeliveryLocation WITHIN mDrvShiftRoutes.DeliveryRoute 
                  //:           WHERE mDrvShiftRoutes.FuelStopDeliveryLocation.ID = mDrvShiftRoutes.ToDeliveryLegLocation.ID 
                  {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                                     GetIntegerFromAttribute( mi_lTempInteger_5, mDrvShiftRoutes, "ToDeliveryLegLocation", "ID" );
                  lTempInteger_5 = mi_lTempInteger_5.intValue( );}
                  RESULT = SetCursorFirstEntityByInteger( mDrvShiftRoutes, "FuelStopDeliveryLocation", "ID", lTempInteger_5, "DeliveryRoute" );
                  //:mDrvShiftRoutes.FuelStop.wStartedDeliveryLegID = mDrvShiftRoutes.DeliveryLeg.ID 
                  SetAttributeFromAttribute( mDrvShiftRoutes, "FuelStop", "wStartedDeliveryLegID", mDrvShiftRoutes, "DeliveryLeg", "ID" );
               } 

               //:END
            } 

            //:END
         } 

         RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryLeg", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
      //:END
   } 

   //:END 

   //:// Recreate all unstarted Delivery Legs according to the rules above.
   //:FOR EACH mDrvShiftRoutes.DeliveryRoute
   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:Count = 0
      Count = 0;
      //:// Start any new Legs for the Route after the last one that currently exists.
      //:SET CURSOR LAST mDrvShiftRoutes.DeliveryLeg  
      RESULT = SetCursorLastEntity( mDrvShiftRoutes, "DeliveryLeg", "" );
      //:FOR EACH mDrvShiftRoutes.FuelStop
      RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "FuelStop", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:Count = Count + 1
         Count = Count + 1;
         //:// Only process Fuel Stops for Legs that have been deleted.
         //:IF mDrvShiftRoutes.FuelStop.wStartedDeliveryLegID = ""
         if ( CompareAttributeToString( mDrvShiftRoutes, "FuelStop", "wStartedDeliveryLegID", "" ) == 0 )
         { 
            //:IF Count = 1
            if ( Count == 1 )
            { 
               //:// This is the first Fuel Stop in the Route, so create the Initial Leg.
               //:CreateViewFromView( mDrvShiftRoutesPrev, mDrvShiftRoutes )
               CreateViewFromView( mDrvShiftRoutesPrev, mDrvShiftRoutes );
               //:NAME VIEW mDrvShiftRoutesPrev "mDrvShiftRoutesPrev"
               SetNameForView( mDrvShiftRoutesPrev, "mDrvShiftRoutesPrev", null, zLEVEL_TASK );
               //:SET CURSOR PREVIOUS mDrvShiftRoutesPrev.DeliveryRoute
               RESULT = SetCursorPrevEntity( mDrvShiftRoutesPrev, "DeliveryRoute", "" );
               //:IF RESULT < zCURSOR_SET
               if ( RESULT < zCURSOR_SET )
               { 
                  //:// This is the first Route in the Shift, so create an Initial Leg from Home Base to the Fuel Stop.
                  //:CREATE ENTITY mDrvShiftRoutes.DeliveryLeg  
                  RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
                  //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mVehicle.HomeBaseAddress 
                  RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mVehicle, "HomeBaseAddress", zPOS_AFTER );
                  //:IF mDrvShiftRoutes.FuelStopTerminalAddress EXISTS
                  lTempInteger_6 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminalAddress" );
                  if ( lTempInteger_6 == 0 )
                  { 
                     //:// Terminal Address
                     //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopTerminalAddress 
                     RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mDrvShiftRoutes, "FuelStopTerminalAddress", zPOS_AFTER );
                     //:ELSE
                  } 
                  else
                  { 
                     //:// Delivery Location Address
                     //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopDeliveryLocationAddress 
                     RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:// Check if the previous Route has been complete (ie. the last Leg back to Home Base is finished, or at least started.)
                  //:SET CURSOR LAST mDrvShiftRoutesPrev.DeliveryLeg 
                  RESULT = SetCursorLastEntity( mDrvShiftRoutesPrev, "DeliveryLeg", "" );
                  //:IF mDrvShiftRoutesPrev.DeliveryLeg.ActualStartDateTime != "" AND 
                  //:   mDrvShiftRoutesPrev.Final_HB_Carrier EXISTS
                  lTempInteger_7 = CheckExistenceOfEntity( mDrvShiftRoutesPrev, "Final_HB_Carrier" );
                  if ( CompareAttributeToString( mDrvShiftRoutesPrev, "DeliveryLeg", "ActualStartDateTime", "" ) != 0 && lTempInteger_7 == 0 )
                  { 

                     //:// Since the previous Route has ended, start this Route from Home Base.
                     //:CREATE ENTITY mDrvShiftRoutes.DeliveryLeg  
                     RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
                     //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mVehicle.HomeBaseAddress 
                     RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mVehicle, "HomeBaseAddress", zPOS_AFTER );
                     //:IF mDrvShiftRoutes.FuelStopTerminalAddress EXISTS
                     lTempInteger_8 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminalAddress" );
                     if ( lTempInteger_8 == 0 )
                     { 
                        //:// Terminal Address
                        //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopTerminalAddress 
                        RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mDrvShiftRoutes, "FuelStopTerminalAddress", zPOS_AFTER );
                        //:ELSE
                     } 
                     else
                     { 
                        //:// Delivery Location Address
                        //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopDeliveryLocationAddress 
                        RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
                     } 

                     //:END
                     //:ELSE
                  } 
                  else
                  { 
                     //:// Check if the previous Route is for a different Vehicle.
                     //:IF mDrvShiftRoutesPrev.RouteVehiclePowerUnit.ID != mDrvShiftRoutes.RouteVehiclePowerUnit.ID  
                     if ( CompareAttributeToAttribute( mDrvShiftRoutesPrev, "RouteVehiclePowerUnit", "ID", mDrvShiftRoutes, "RouteVehiclePowerUnit", "ID" ) != 0 )
                     { 
                        //:// The previous Route is for a different Vehicle, so create a Final Leg on that Route from the last Fuel Stop to Home Base 
                        //:// and create an Initial Leg on this Route from Home Base to the first Fuel Stop.

                        //:// Final Leg and Stop on previous Route.
                        //:SET CURSOR LAST mDrvShiftRoutesPrev.FuelStop 
                        RESULT = SetCursorLastEntity( mDrvShiftRoutesPrev, "FuelStop", "" );
                        //:SET CURSOR LAST mDrvShiftRoutesPrev.DeliveryLeg 
                        RESULT = SetCursorLastEntity( mDrvShiftRoutesPrev, "DeliveryLeg", "" );
                        //:CREATE ENTITY mDrvShiftRoutesPrev.DeliveryLeg  
                        RESULT = CreateEntity( mDrvShiftRoutesPrev, "DeliveryLeg", zPOS_AFTER );
                        //:// To Home Base
                        //:INCLUDE mDrvShiftRoutesPrev.ToDeliveryLegAddress FROM mVehicle.HomeBaseAddress
                        RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutesPrev, "ToDeliveryLegAddress", mVehicle, "HomeBaseAddress", zPOS_AFTER );
                        //:IF mDrvShiftRoutesPrev.FuelStopTerminalAddress EXISTS
                        lTempInteger_9 = CheckExistenceOfEntity( mDrvShiftRoutesPrev, "FuelStopTerminalAddress" );
                        if ( lTempInteger_9 == 0 )
                        { 
                           //:// From Previous Stop Terminal Address
                           //:INCLUDE mDrvShiftRoutesPrev.FromDeliveryLegAddress FROM mDrvShiftRoutesPrev.FuelStopTerminalAddress 
                           RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutesPrev, "FromDeliveryLegAddress", mDrvShiftRoutesPrev, "FuelStopTerminalAddress", zPOS_AFTER );
                           //:ELSE
                        } 
                        else
                        { 
                           //:// From Previous Stop Delivery Location Address
                           //:INCLUDE mDrvShiftRoutesPrev.FromDeliveryLegAddress FROM mDrvShiftRoutesPrev.FuelStopDeliveryLocationAddress 
                           RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutesPrev, "FromDeliveryLegAddress", mDrvShiftRoutesPrev, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
                        } 

                        //:END

                        //:// Initial Leg on this Route.
                        //:CREATE ENTITY mDrvShiftRoutes.DeliveryLeg
                        RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
                        //:// From Home Base
                        //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mVehicle.HomeBaseAddress
                        RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mVehicle, "HomeBaseAddress", zPOS_AFTER );
                        //:IF mDrvShiftRoutes.FuelStopTerminalAddress EXISTS
                        lTempInteger_10 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminalAddress" );
                        if ( lTempInteger_10 == 0 )
                        { 
                           //:// To This Stop Terminal Address
                           //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopTerminalAddress 
                           RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mDrvShiftRoutes, "FuelStopTerminalAddress", zPOS_AFTER );
                           //:ELSE
                        } 
                        else
                        { 
                           //:// To This Stop Delivery Location Address
                           //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopDeliveryLocationAddress 
                           RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
                        } 

                        //:END

                        //:ELSE
                     } 
                     else
                     { 
                        //:// Since this is a continuing Leg from the last Leg of the previous Route, create a Leg from the last Fuel Stop of the
                        //:// previous Route to the first Fuel Stop in this Route (which we're currently positioned on.
                        //:SET CURSOR LAST mDrvShiftRoutesPrev.FuelStop 
                        RESULT = SetCursorLastEntity( mDrvShiftRoutesPrev, "FuelStop", "" );
                        //:CREATE ENTITY mDrvShiftRoutes.DeliveryLeg  
                        RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
                        //:// From Previous Stop
                        //:IF mDrvShiftRoutesPrev.FuelStopTerminalAddress EXISTS
                        lTempInteger_11 = CheckExistenceOfEntity( mDrvShiftRoutesPrev, "FuelStopTerminalAddress" );
                        if ( lTempInteger_11 == 0 )
                        { 
                           //:// From Previous Stop Terminal Address
                           //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mDrvShiftRoutesPrev.FuelStopTerminalAddress 
                           RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mDrvShiftRoutesPrev, "FuelStopTerminalAddress", zPOS_AFTER );
                           //:ELSE
                        } 
                        else
                        { 
                           //:// From Previous Stop Delivery Location Address
                           //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mDrvShiftRoutesPrev.FuelStopDeliveryLocationAddress 
                           RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mDrvShiftRoutesPrev, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
                        } 

                        //:END
                        //:// To This Stop
                        //:IF mDrvShiftRoutes.FuelStopTerminalAddress EXISTS
                        lTempInteger_12 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminalAddress" );
                        if ( lTempInteger_12 == 0 )
                        { 
                           //:// To This Terminal Address
                           //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopTerminalAddress 
                           RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mDrvShiftRoutes, "FuelStopTerminalAddress", zPOS_AFTER );
                           //:ELSE
                        } 
                        else
                        { 
                           //:// To This Delivery Location Address
                           //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopDeliveryLocationAddress 
                           RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
                        } 

                        //:END
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
               //:DropView( mDrvShiftRoutesPrev )
               DropView( mDrvShiftRoutesPrev );
               //:ELSE
            } 
            else
            { 
               //:// This is a continuation from Fuel Stop to Fuel Stop within a Route, so create a Leg from the previous Fuel Stop to the this Fuel Stop.
               //:CreateViewFromView( mDrvShiftRoutesPrev, mDrvShiftRoutes )
               CreateViewFromView( mDrvShiftRoutesPrev, mDrvShiftRoutes );
               //:NAME VIEW mDrvShiftRoutesPrev "mDrvShiftRoutesPrev"
               SetNameForView( mDrvShiftRoutesPrev, "mDrvShiftRoutesPrev", null, zLEVEL_TASK );
               //:SET CURSOR PREVIOUS mDrvShiftRoutesPrev.FuelStop 
               RESULT = SetCursorPrevEntity( mDrvShiftRoutesPrev, "FuelStop", "" );
               //:CREATE ENTITY mDrvShiftRoutes.DeliveryLeg  
               RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
               //:// From Previous Stop
               //:IF mDrvShiftRoutesPrev.FuelStopTerminalAddress EXISTS
               lTempInteger_13 = CheckExistenceOfEntity( mDrvShiftRoutesPrev, "FuelStopTerminalAddress" );
               if ( lTempInteger_13 == 0 )
               { 
                  //:// From Previous Stop Terminal Address
                  //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mDrvShiftRoutesPrev.FuelStopTerminalAddress 
                  RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mDrvShiftRoutesPrev, "FuelStopTerminalAddress", zPOS_AFTER );
                  //:ELSE
               } 
               else
               { 
                  //:// From Previous Stop Delivery Location Address
                  //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mDrvShiftRoutesPrev.FuelStopDeliveryLocationAddress 
                  RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mDrvShiftRoutesPrev, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
               } 

               //:END
               //:// To This Stop
               //:IF mDrvShiftRoutes.FuelStopTerminalAddress EXISTS
               lTempInteger_14 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminalAddress" );
               if ( lTempInteger_14 == 0 )
               { 
                  //:// To This Terminal Address
                  //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopTerminalAddress 
                  RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mDrvShiftRoutes, "FuelStopTerminalAddress", zPOS_AFTER );
                  //:ELSE
               } 
               else
               { 
                  //:// To This Delivery Location Address
                  //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopDeliveryLocationAddress 
                  RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
               } 

               //:END
               //:DropView( mDrvShiftRoutesPrev )
               DropView( mDrvShiftRoutesPrev );
            } 

            //:END
         } 

         RESULT = SetCursorNextEntity( mDrvShiftRoutes, "FuelStop", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
      //:END
   } 

   //:END

   //:// Add the Final Delivery Leg to the last Route, on which we're currently positioned.
   //:// Note that we are positioned on the last Delivery Leg and last Fuel Stop in the Shift.
   //:// However, we will NOT do this if the last leg is back to Home Base.
   //:IF mDrvShiftRoutes.Final_HB_Carrier DOES NOT EXIST
   lTempInteger_15 = CheckExistenceOfEntity( mDrvShiftRoutes, "Final_HB_Carrier" );
   if ( lTempInteger_15 != 0 )
   { 
      //:CREATE ENTITY mDrvShiftRoutes.DeliveryLeg  
      RESULT = CreateEntity( mDrvShiftRoutes, "DeliveryLeg", zPOS_AFTER );
      //:// To Home Base
      //:INCLUDE mDrvShiftRoutes.ToDeliveryLegAddress FROM mVehicle.HomeBaseAddress 
      RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "ToDeliveryLegAddress", mVehicle, "HomeBaseAddress", zPOS_AFTER );
      //:// From last Stop
      //:IF mDrvShiftRoutes.FuelStopTerminalAddress EXISTS
      lTempInteger_16 = CheckExistenceOfEntity( mDrvShiftRoutes, "FuelStopTerminalAddress" );
      if ( lTempInteger_16 == 0 )
      { 
         //:// From last Stop Terminal Address
         //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopTerminalAddress 
         RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mDrvShiftRoutes, "FuelStopTerminalAddress", zPOS_AFTER );
         //:ELSE
      } 
      else
      { 
         //:// From last Stop Delivery Location Address
         //:INCLUDE mDrvShiftRoutes.FromDeliveryLegAddress FROM mDrvShiftRoutes.FuelStopDeliveryLocationAddress 
         RESULT = IncludeSubobjectFromSubobject( mDrvShiftRoutes, "FromDeliveryLegAddress", mDrvShiftRoutes, "FuelStopDeliveryLocationAddress", zPOS_AFTER );
      } 

      //:END
   } 

   //:END

   //:DropObjectInstance( mVehicle )
   DropObjectInstance( mVehicle );

   //:// Make sure that Delivery Leg Types are correctly set.
   //:FOR EACH mDrvShiftRoutes.DriverShift 
   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DriverShift", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH mDrvShiftRoutes.DeliveryLeg 
      RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryLeg", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:mDrvShiftRoutes.DeliveryLeg.Type = mDrvShiftRoutes.DeliveryLeg.dType 
         SetAttributeFromAttribute( mDrvShiftRoutes, "DeliveryLeg", "Type", mDrvShiftRoutes, "DeliveryLeg", "dType" );
         RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryLeg", "" );
      } 

      RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DriverShift", "" );
      //:END
   } 

   //:END

   //:// Set Sort Order for Delivery Routes.
   //:Count = 0
   Count = 0;
   //:FOR EACH mDrvShiftRoutes.DeliveryRoute 
   RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:Count = Count + 1
      Count = Count + 1;
      //:mDrvShiftRoutes.DeliveryRoute.SortOrderWithinShift = Count
      SetAttributeFromInteger( mDrvShiftRoutes, "DeliveryRoute", "SortOrderWithinShift", Count );
      RESULT = SetCursorNextEntity( mDrvShiftRoutes, "DeliveryRoute", "" );
   } 

   //:END

   //:// Finally, recompute the Leg times.
   //:RecomputeDriverShiftTimes( mDrvShiftRoutes )
   {
    //ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mDrvShiftRoutes );
    //m_ZGLOBAL2_Operation.RecomputeDriverShiftTimes( mDrvShiftRoutes );
    // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dDriverTruck( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING ( 500 ) szName
public int 
omDrvShiftRoutes_dDriverTruck( View     mDrvShiftRoutes,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   String   szName = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Combine the Driver Truck Name.
         //:IF mDrvShiftRoutes.VehiclePowerUnit EXISTS AND mDrvShiftRoutes.DriverPerson EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mDrvShiftRoutes, "VehiclePowerUnit" );
         lTempInteger_1 = CheckExistenceOfEntity( mDrvShiftRoutes, "DriverPerson" );
         if ( lTempInteger_0 == 0 && lTempInteger_1 == 0 )
         { 
            //:szName = mDrvShiftRoutes.DriverPerson.FirstName + "  " + mDrvShiftRoutes.DriverPerson.LastName + " / " + mDrvShiftRoutes.VehiclePowerUnit.TruckIdentifier 
            {StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                         GetStringFromAttribute( sb_szName, mDrvShiftRoutes, "DriverPerson", "FirstName" );
            szName = sb_szName.toString( );}
             {StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                        ZeidonStringConcat( sb_szName, 1, 0, "  ", 1, 0, 501 );
            szName = sb_szName.toString( );}
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 51, mDrvShiftRoutes, "DriverPerson", "LastName", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                        ZeidonStringConcat( sb_szName, 1, 0, szTempString_0, 1, 0, 501 );
            szName = sb_szName.toString( );}
             {StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                        ZeidonStringConcat( sb_szName, 1, 0, " / ", 1, 0, 501 );
            szName = sb_szName.toString( );}
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_3, 'S', 51, mDrvShiftRoutes, "VehiclePowerUnit", "TruckIdentifier", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                        ZeidonStringConcat( sb_szName, 1, 0, szTempString_1, 1, 0, 501 );
            szName = sb_szName.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szName )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szName );
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
//:dRouteName( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:            STRING ( 32 ) InternalEntityStructure,
//:            STRING ( 32 ) InternalAttribStructure,
//:            SHORT GetOrSetFlag )

//:   STRING ( 500 ) szRouteName
public int 
omDrvShiftRoutes_dRouteName( View     mDrvShiftRoutes,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szRouteName = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Set the Delivery Route Name from a reusable operation.
         //:GetDeliveryRouteName( mDrvShiftRoutes, szRouteName )
         {
          //ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( mDrvShiftRoutes );
          {StringBuilder sb_szRouteName;
         if ( szRouteName == null )
            sb_szRouteName = new StringBuilder( 32 );
         else
            sb_szRouteName = new StringBuilder( szRouteName );
                   //m_ZGLOBAL2_Operation.GetDeliveryRouteName( mDrvShiftRoutes, sb_szRouteName );
         szRouteName = sb_szRouteName.toString( );}
          // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
         }

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szRouteName )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szRouteName );
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
//:dRouteNumber( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )

//:   STRING ( 10 ) szRouteID
public int 
omDrvShiftRoutes_dRouteNumber( View     mDrvShiftRoutes,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   String   szRouteID = null;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// The Route Number is must the ID.
         //:szRouteID = mDrvShiftRoutes.DeliveryRoute.ID 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szRouteID;
         if ( szRouteID == null )
            sb_szRouteID = new StringBuilder( 32 );
         else
            sb_szRouteID = new StringBuilder( szRouteID );
                   GetVariableFromAttribute( sb_szRouteID, mi_lTempInteger_0, 'S', 11, mDrvShiftRoutes, "DeliveryRoute", "ID", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szRouteID = sb_szRouteID.toString( );}

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szRouteID )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szRouteID );
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
//:dOrderStatus( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )

//:   STRING ( 30 ) szOrderStatus
public int 
omDrvShiftRoutes_dOrderStatus( View     mDrvShiftRoutes,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   String   szOrderStatus = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// If this is for an FTL Order, then the result is the LoadRequest.Status
         //:// If this is for an LTL Order, then the result depends on the DeliveryRoute Status.
         //:IF mDrvShiftRoutes.DeliveryRoute.dOrderType = "FTL" 
         if ( CompareAttributeToString( mDrvShiftRoutes, "DeliveryRoute", "dOrderType", "FTL" ) == 0 )
         { 
            //:GetStringFromAttributeByContext( szOrderStatus, mDrvShiftRoutes, "LoadRequest", "Status", "", 30 )
            {StringBuilder sb_szOrderStatus;
            if ( szOrderStatus == null )
               sb_szOrderStatus = new StringBuilder( 32 );
            else
               sb_szOrderStatus = new StringBuilder( szOrderStatus );
                         GetStringFromAttributeByContext( sb_szOrderStatus, mDrvShiftRoutes, "LoadRequest", "Status", "", 30 );
            szOrderStatus = sb_szOrderStatus.toString( );}
            //:ELSE
         } 
         else
         { 
            //:GetStringFromAttributeByContext( szOrderStatus, mDrvShiftRoutes, "DeliveryRoute", "Status", "", 30 )
            {StringBuilder sb_szOrderStatus;
            if ( szOrderStatus == null )
               sb_szOrderStatus = new StringBuilder( 32 );
            else
               sb_szOrderStatus = new StringBuilder( szOrderStatus );
                         GetStringFromAttributeByContext( sb_szOrderStatus, mDrvShiftRoutes, "DeliveryRoute", "Status", "", 30 );
            szOrderStatus = sb_szOrderStatus.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szOrderStatus )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szOrderStatus );
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
//:dOrderType( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:            STRING ( 32 ) InternalEntityStructure,
//:            STRING ( 32 ) InternalAttribStructure,
//:            SHORT GetOrSetFlag )

//:   STRING ( 10 ) szOrderType
public int 
omDrvShiftRoutes_dOrderType( View     mDrvShiftRoutes,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szOrderType = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Either LTL or FTL, depending on the Order.Type, if there is one.
         //:// If there isn't one, the result will be LTL.
         //:IF mDrvShiftRoutes.Order EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mDrvShiftRoutes, "Order" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szOrderType = mDrvShiftRoutes.Order.Type 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szOrderType;
            if ( szOrderType == null )
               sb_szOrderType = new StringBuilder( 32 );
            else
               sb_szOrderType = new StringBuilder( szOrderType );
                         GetVariableFromAttribute( sb_szOrderType, mi_lTempInteger_1, 'S', 11, mDrvShiftRoutes, "Order", "Type", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szOrderType = sb_szOrderType.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szOrderType = "LTL"
             {StringBuilder sb_szOrderType;
            if ( szOrderType == null )
               sb_szOrderType = new StringBuilder( 32 );
            else
               sb_szOrderType = new StringBuilder( szOrderType );
                        ZeidonStringCopy( sb_szOrderType, 1, 0, "LTL", 1, 0, 11 );
            szOrderType = sb_szOrderType.toString( );}
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szOrderType )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szOrderType );
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
//:dTruckTankID( VIEW mDrvShiftRoutes BASED ON LOD mDrvShiftRoutes,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )

//:   STRING ( 500 ) szName
public int 
omDrvShiftRoutes_dTruckTankID( View     mDrvShiftRoutes,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   String   szName = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:// Build a Combination Name made up of the Truck and Trailer Identifiers.
         //:IF mDrvShiftRoutes.RouteVehicleCombination EXISTS
         lTempInteger_0 = CheckExistenceOfEntity( mDrvShiftRoutes, "RouteVehicleCombination" );
         if ( lTempInteger_0 == 0 )
         { 
            //:szName = mDrvShiftRoutes.RouteVehiclePowerUnit.TruckIdentifier 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szName;
            if ( szName == null )
               sb_szName = new StringBuilder( 32 );
            else
               sb_szName = new StringBuilder( szName );
                         GetVariableFromAttribute( sb_szName, mi_lTempInteger_1, 'S', 501, mDrvShiftRoutes, "RouteVehiclePowerUnit", "TruckIdentifier", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szName = sb_szName.toString( );}
            //:// Note we only consider Semi Trailer and Bobtail Trailer tanks (not Bobtail tanks).
            //:FOR EACH mDrvShiftRoutes.RouteVehicleDeliveryTank 
            RESULT = SetCursorFirstEntity( mDrvShiftRoutes, "RouteVehicleDeliveryTank", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF mDrvShiftRoutes.RouteVehicleDeliveryTank.Type = "S" OR mDrvShiftRoutes.RouteVehicleDeliveryTank.Type = "T"  
               if ( CompareAttributeToString( mDrvShiftRoutes, "RouteVehicleDeliveryTank", "Type", "S" ) == 0 || CompareAttributeToString( mDrvShiftRoutes, "RouteVehicleDeliveryTank", "Type", "T" ) == 0 )
               { 
                  //:szName = szName + "-" + mDrvShiftRoutes.RouteVehicleDeliveryTank.TruckTrailerIdentifier 
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, "-", 1, 0, 501 );
                  szName = sb_szName.toString( );}
                  {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                  StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                     GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 51, mDrvShiftRoutes, "RouteVehicleDeliveryTank", "TruckTrailerIdentifier", "", 0 );
                  lTempInteger_2 = mi_lTempInteger_2.intValue( );
                  szTempString_0 = sb_szTempString_0.toString( );}
                   {StringBuilder sb_szName;
                  if ( szName == null )
                     sb_szName = new StringBuilder( 32 );
                  else
                     sb_szName = new StringBuilder( szName );
                                    ZeidonStringConcat( sb_szName, 1, 0, szTempString_0, 1, 0, 501 );
                  szName = sb_szName.toString( );}
               } 

               RESULT = SetCursorNextEntity( mDrvShiftRoutes, "RouteVehicleDeliveryTank", "" );
               //:END
            } 

            //:END
         } 

         //:END

         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( mDrvShiftRoutes,
         //:                InternalEntityStructure, InternalAttribStructure, szName )
         StoreStringInRecord( mDrvShiftRoutes, InternalEntityStructure, InternalAttribStructure, szName );
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
